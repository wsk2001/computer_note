# Java ByteBuffer와 무시무시한 NoSuchMethodError

출처: https://www.morling.dev/blog/bytebuffer-and-the-dreaded-nosuchmethoderror/

얼마 전에 Debezium 커뮤니티의 한 사용자가 흥미로운 문제를 보고했습니다. 그들은 Java 1.8과 함께 Debezium을 사용하고 있었고 이상한 NoSuchMethodError를 얻었습니다.

``` bash
java.lang.NoSuchMethodError: java.nio.ByteBuffer.position(I)Ljava/nio/ByteBuffer;
  at io.debezium.connector.postgresql.connection.Lsn.valueOf(Lsn.java:86)
  at io.debezium.connector.postgresql.connection.PostgresConnection.tryParseLsn(PostgresConnection.java:270)
  at io.debezium.connector.postgresql.connection.PostgresConnection.parseConfirmedFlushLsn(PostgresConnection.java:235)
  ...
```

NoSuchMethodError는 일반적으로 일부 코드를 컴파일하는 데 사용되는 Java 버전과 이를 실행하는 데 사용되는 Java 버전의 불일치를 나타냅니다. 일부 메서드는 컴파일 시간에 존재하지만 런타임에는 사용할 수 없습니다.

이제 우리는 실제로 Debezium 코드 기반을 구축하기 위해 JDK 11을 사용하는 동시에 런타임 시 최소 필수 버전으로 Java 1.8을 대상으로 합니다. 그러나 Java 1.8에서도 Buffer 클래스(ByteBuffer가 확장하는)에 정의된 메서드 position(int)가 있습니다. 사실 Debezium 코드는 해당 버전에서도 잘 컴파일됩니다. 그러면 왜 사용자가 이 오류를 겪게 될까요?

무슨 일이 일어나고 있는지 이해하기 위해 문제를 재현하기 위한 매우 간단한 클래스를 만들어 보겠습니다.

``` java
import java.nio.ByteBuffer;

public class ByteBufferTest {
  public static void main(String... args) {
    ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
    buffer.position(1); 
    System.out.println(buffer.get());
  }
}
```

> JDK 9 이상으로 컴파일할 때 Java 1.8에서 작동하지 않는 이유는 무엇입니까?

현재 JDK로 이것을 컴파일하십시오.

``` bash
$ javac --source 1.8 --target 1.8 ByteBufferTest.java
```

아니나 다를까 NoSuchMethodError는 Java 1.8에서 실행할 때 나타납니다.

``` bash
$ java ByteBufferTest

Exception in thread "main" java.lang.NoSuchMethodError: java.nio.ByteBuffer.position(I)Ljava/nio/ByteBuffer;
	at ByteBufferTest.main(ByteBufferTest.java:6)
```



반면 1.8을 사용하여 이 코드를 컴파일하고 실행하면 제대로 작동합니다. 이제 오류 메시지를 다시 자세히 살펴보면 빠진 메서드가 ByteBuffer position(int)로 정의되어 있습니다. 즉. position()과 같은 호출된 메서드의 경우 해당 이름, 매개 변수 유형 및 선언 클래스의 이름은 해당 호출에 대한 바이트 코드의 일부일 뿐만 아니라 메서드의 반환 유형입니다. javap를 사용하여 클래스의 바이트 코드를 보면 다음을 확인할 수 있습니다.

``` bash
$ javap -p -c -s -v -l -constants ByteBufferTest

...
public static void main(java.lang.String...);
  descriptor: ([Ljava/lang/String;)V
  flags: ACC_PUBLIC, ACC_STATIC, ACC_VARARGS
  Code:
    stack=4, locals=2, args_size=1
...
      19: aload_1
      20: iconst_1
      21: invokevirtual #13 // Method java/nio/ByteBuffer.position:(I)Ljava/nio/ByteBuffer;
...
```

그리고 이것은 우리에게 올바른 방향을 알려줍니다. Java 1.8에는 실제로 그러한 메서드가 없으며 Buffer에 있는 position() 메서드만 있습니다. 물론 ByteBuffer가 아닌 Buffer를 반환합니다. 반면 Java 9부터는 이 메서드(및 기타 여러 메서드)가 ByteBuffer에서 재정의되어— 공변 반환 유형에 대한 Java의 지원을 활용하여 — ByteBuffer를 반환합니다. Java 컴파일러는 이제 해당 메서드인 ByteBuffer 위치(int)를 선택하고 호출자 클래스의 바이트 코드에 호출된 메서드 시그니처로 기록합니다.

Buffer에 의해 선언된 메서드 대신 반환 값에 대해 추가 ByteBuffer 메서드를 호출할 수 있으므로 이것은 그 자체로 좋은 사용성 개선입니다. 그러나 우리가 본 것처럼 이전 Java 버전과의 호환성을 유지하면서 JDK 9 이상에서 코드를 컴파일할 때 약간의 놀라움이 있습니다. 알고 보니 우리가 이 문제에 직면한 최초의 사람도, 유일한 사람도 아니었습니다. 꽤 많은 오픈 소스 프로젝트가 이에 부딪쳤습니다. Eclipse Jetty, Apache Pulsar, Eclipse Vert.x, Apache Thrift, Yugabyte DB 클라이언트 및 기타 몇 가지.



### 이 상황을 방지하는 방법?

그렇다면 이 문제가 발생하지 않도록 하려면 어떻게 해야 할까요? 첫 번째 아이디어는 Buffer로 캐스팅하여 올바른 메서드를 선택하도록 하는 것입니다.

``` java
((java.nio.Buffer) buffer).position(1);
```

> 역자(나): 는 이 방법을 선호함. (별도의 함수를 만들어서 대체)



그러나 이렇게 하면 실제로 원하는 바이트 코드가 생성되지만 이것이 최선의 방법은 아닙니다. 영향을 받는 ByteBuffer 메서드를 호출할 때마다 그렇게 해야 한다는 점을 기억해야 하며, 불필요한 캐스트가 우리 팀의 순진한 동료에 의한 일부 '정리'의 손쉬운 대상이 될 수 있습니다.

운 좋게도 훨씬 더 나은 방법이 있으며 이는 JEP 247('이전 플랫폼 버전용 컴파일')을 통해 도입된 Java 컴파일러의 --release 매개변수에 의존하는 것이며 JDK 9에서도 플랫폼에 추가되었습니다. 보다 널리 알려진 --source 및 --target 쌍인 --release 스위치는 지정된 Java 버전에서 실제로 사용할 수 있는 바이트 코드만 생성되도록 합니다. 이를 위해 JDK에는 지원되는 모든 Java 버전의 서명 데이터가 포함되어 있습니다($JAVA_HOME/lib/ct.sym 파일에 저장됨).

따라서 실제로 필요한 것은 --release=8로 코드를 컴파일하는 것입니다.

``` bash
$ javac --release=8 ByteBufferTest.java
```

javap를 사용하여 바이트 코드를 다시 검사하면 이제 예상 서명이 제자리에 있습니다.

``` bash
21: invokevirtual #13 // Method java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
```

Java 1.8에서 실행될 때 이 가상 메서드 호출은 런타임 시 Buffer#position(int)로 확인되는 반면 Java 9 이상에서는 다음으로 인해 컴파일러가 ByteBuffer의 클래스 파일에 삽입한 브리지 메서드로 확인됩니다. 자체적으로 재정의 ByteBuffer#position(int) 메서드를 호출하는 공변형 반환 유형입니다.

이제 결과를 재할당하여 ByteBuffer에서 재정의 메서드 버전을 실제로 사용하려고 하면 어떤 일이 발생하는지 살펴보겠습니다.

``` java
...
ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
buffer = buffer.position(1);
...
```

Et voilà, JDK 1.8 메서드 Buffer#position(int)의 반환 유형을 ByteBuffer에 할당할 수 없기 때문에 Java 1.8을 대상으로 할 때 컴파일러에서 거부됩니다.

``` bash
$ javac --release=8 ByteBufferTest.java

ByteBufferTest.java:6: error: incompatible types: Buffer cannot be converted to ByteBuffer
        buffer = buffer.position(1);
```

간단히 말해서 우리는———————— ————— --source/--target 대신 --release 스위치를 사용해야 했습니다. 그러면 사용자에게 이러한 문제가 발생하지 않았을 것입니다. Maven 기반 빌드에서 동일한 결과를 얻으려면 pom.xml에 다음 속성을 지정하기만 하면 됩니다.

``` xml
...
<properties>
  <maven.compiler.release>8</maven.compiler.release>
</properties>
...
```

이론적으로 --source 및 --target을 사용할 때도 동일한 효과를 얻을 수 있습니다. --boot-class-path 옵션을 사용하면 컴파일에 사용되는 JDK의 파일 대신 특정 부트스트랩 클래스 파일 집합을 사용하도록 컴파일러에 조언할 수 있습니다. 그러나 --release는 현재 사용되는 JDK 자체와 함께 제공되는 서명 데이터를 사용하는 반면 대상 Java 버전의 클래스를 실제로 제공해야 하므로 상당히 번거롭습니다.