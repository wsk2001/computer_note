# Java Queue 의 종류와 용법-자바 병렬 프로그래밍

출처: https://202psj.tistory.com/1609



멀티 스레드 환경에서 Queue는 생산 및 소비의 구조에 필수적인 자료구조이다.  여기서 우리는 BlockingQueue라는 interface를 구현한 객체를 가져다 쓸 수 있다.

**Block** 이라는 것은 먼저 무엇일까? '**막는다**'는 뜻이다. 그럼 무엇을 막는 다는 것인가? 그것은 바로! **Queue가 꽉찼을때의 삽입 시도 / Queue가 비어있을때의 추출 시도** 를 막는 다는 것이다. 이 자동으로 '막는' 기능이 있어 BlockingQueue 의 구현체는 모두 **Thread-safe** 하다.

 

그럼 이제부터 본격적인 BlockingQueue에 대한 포스팅을 시작한다.

## BlockingQueue의 종류와 특징

### 1. ArrayBlockingQueue
- 고정배열에 일반적인 Queue를 구현한 클래스, 생성 후 크기변경 불가
- 꽉찼을때 추가 block, 비었을때 추출 block
- 선택적으로 공평성 정책을 두어 block한 thread들의 순차적 대기열 생성

(대기열 처리에 대해 정확한 순서 보장 X, 공평성 따짐 - throughput 감소되나 variability를 줄이고 starvation을 해소한다.)



### 2. LinkedBlockingQueue

- 선택적으로 Bound가 가능한 Linked list로 구현한 Queue
- capacity를 초기에 정해주지 않는경우 integer.MAX_VALUE로 자동설정
- 용량을 초과하지 않는 한에서 node는 동적으로 삽입시마다 생성되며 초과 시 block.
- Linked queue는 일반적으로 배열 기반 큐 보다 동시성 App에서 높은 throughput을 가짐.



### 3. PriorityBlockingQueue

- PriorityQueue와 같은 정렬방식을 지니는 용량제한이 없는 Queue. 하지만 Element 추출에 대해 Block기능을 제공.
- 입력무제한(unbounded)으로 기본 설계가 되었기 때문에 추가작업 수행중 fail이 나면 이것은 자원고갈이 났다는 뜻이다. (OutOfMemoryError 발생)
- null element 및 non-comparable object 를 수용하지 않으며 natural ordering을 지원한다.(ClassCastException 발생)



### 4. SynchronousQueue

- Queue 내부로의 insert 작업이 다른 스레드의 remove 작업과 반드시 동시에 일어나야한다.
  (서로 대칭되는 작업이 없을경우 생길때까지 wait)
- 이 queue는 내부용량을 지니지 않는다. (단 1개의 공간도...) / null 값 수용 X
- remove될때만 새로운 element가 insert되므로 추출(peek이라는 함수)을 할 수 없다.
 반대로, 다른 스레드가 remove를 시도하지 않는이상 삽입을 할 수도 없다.
- Queue의 head는 최초 삽입 시도를 한 Thread의 삽입 element가 된다.
- poll()을 수행하였을 시 Queue에 삽입시도를 한 thread가 없다면 null을 리턴한다.
- Collection 합수들에 대해 SynchronousQueue는 empty collection으로서의 목적성을 지닌다.
- 이 Queue는 CSP나 Ada의 rendezvous channel과 비슷하다.
  수행중인 thread의 object의 queue에 대한 동작이 다른 살아있는 스레드 object의 queue에 대한 동작과  sync-up 되어야 하는 handoff design에 적합하며 주로 information, event, task를 전달한다.


마지막 2가지는 SynchronousQueue가 유용하게 쓰이는 곳에 대한 이야기 인데, 해석에 자신이 없어서 원문을 덧붙인다.

For purposes of other Collection methods (for example contains), a SynchronousQueue acts as an empty collection. 

Synchronous queues are similar to rendezvous channels used in CSP and Ada. They are well suited for handoff designs, in which an object  running in one thread must sync up with an object running in another  thread in order to hand it some information, event, or task.



## BlockingQueue의 Producer-Consumer 구조 기본 용법

다음의 구조에서 빨간 implement부분에 상위에서 다룬 4개의 Queue중 적절한 동작방식을 가진 것을 택하면 된다.

element의 **입력에 put(), 추출에 take()**가 사용되었다는 점에 유의하자

 ``` java
 class Producer implements Runnable {
   private final LinkedBlockingQueue queue;
   Producer(BlockingQueue q) { queue = q; }
   public void run() {
    try {
     while (true) { queue.put(produce()); }
    } catch (InterruptedException ex) { ... handle ...}
   }
 
   Object produce() { ... }
  }
 
  class Consumer implements Runnable {
   private final LinkedBlockingQueue queue;
   Consumer(BlockingQueue q) { queue = q; }
   public void run() {
    try {
     while (true) { consume(queue.take()); }
    } catch (InterruptedException ex) { ... handle ...}
   }
 
   void consume(Object x) { ... }
  }
 
  class Setup {
   void main() {
    BlockingQueue q = new SomeQueueImplementation();
 
    // 여기서 q는 공유자원이다. Thread생성 시 매개변수로 포인터를 넘겨준다.
    Producer p  = new Producer(q);
    Consumer c1 = new Consumer(q);
    Consumer c2 = new Consumer(q);
    new Thread(p).start();
    new Thread(c1).start();
    new Thread(c2).start();
   }
  }
 }
 ```



요즘 회사에서 RabbitMQ를 사용하여 메시지 처리 프로그래밍을 하고 있습니다. 다른 개발자분과 함께 개발 중인데 저는 Consumer 역할을 하는 데몬을 구현중이네요.

생산자-소비자라는 것이 상대적인 부분이 있어서 MQ를 중심으로 제가 개발 중인 데몬은 소비자가 되지만... 제 데몬을 중심으로는 이 소비자가 다시 생산자가 되는 그런 개념이 됩니다.

아무튼 MQ로부터 메시지를 가져오고 그것을 내부 Queue에 저장하여 이런저런 할 일들을 구현을 해야하다보니 데몬안에서도 또 다시 생산자-소비자 패턴의 모델이 되어버렸습니다.

이를위해 Java에서 제공되는 BlockingQueue를 사용하였습니다.

"자바 병렬처리 프로그래밍"이란 책에서는 이 Queue가 생산자-소비자 패턴을 구현하는데 아주 좋은 Queue라고 소개가 되어있습니다.

그래서 기본적인 사용법을 작성해보려고 합니다.

 **Producer.java** 

```java
package blockingqueue;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue queue;
	
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				Date d = new Date();
				String msg = "메시지"+d.toString();
				
				queue.add(msg);
				
				System.out.println("메시지를 생성합니다. [" + queue.size() + "]");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
```

[view raw](https://gist.github.com/need4spd/4165874/raw/4a87962985edd799c7c6c6ee38cfb19025353d34/Producer.java)[Producer.java](https://gist.github.com/need4spd/4165874#file-producer-java) hosted with ❤ by [GitHub](https://github.com/)


위 소스가 생산자 역할을 하는 소스입니다. queue를 가지고 있고 이 queue에 900ms에 한번씩 msg를 생성하여 집어 넣습니다.


**Consumer.java** 

```java
package blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue queue;
	
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				String msg = queue.take();
				
				System.out.println("메시지를 꺼냅니다. : " + msg + "[" + queue.size() + "]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}
}
```

[view raw](https://gist.github.com/need4spd/4165874/raw/4a87962985edd799c7c6c6ee38cfb19025353d34/Consumer.java)[Consumer.java](https://gist.github.com/need4spd/4165874#file-consumer-java) hosted with ❤ by [GitHub](https://github.com/)


**Starter.java**

```java
package blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Starter {
	public static void main(String[] args) {
		
		BlockingQueue queue = new ArrayBlockingQueue(50);
		//BlockingQueue queue = new LinkedBlockingQueue();
		Thread p = new Thread(new Producer(queue));
		Thread c = new Thread(new Consumer(queue));
		
		p.start();
		c.start();
		
	}
}
```

 

[view raw](https://gist.github.com/need4spd/4165874/raw/4a87962985edd799c7c6c6ee38cfb19025353d34/Starter.java)[Starter.java](https://gist.github.com/need4spd/4165874#file-starter-java) hosted with ❤ by [GitHub](https://github.com/)

실행소스입니다.
두 스레드가 사용 할 queue를 생성 후 스레드를 실행합니다.

각 스레드의 loop 간격을 조절해가면서 이런저런 테스트를 해보시면
좋을 것 같습니다.

몇가지 특징을 소개해드리면...

\1. BlockingQueue를 생성 할 때 인자로 들어가는 int 파라메터는 fixed capacity를 뜻 합니다.
producer가 queue에 msg를 넣는 속도가 너무 빠르거나 해서 저 한계를 넘어가버리면 exception이 발생합니다.
다만, BlockingQueue의 다른 구현 클래스인 LinkedBlockingQueue()를 사용하시면 capacity와 상관없이
계속 queue를 채울 수 있지만.. 무한대로 queue가 쌓이는 것은 피하는 것이 좋겠죠..

\####### 3월 2일 내용추가 #########
커멘트로 풍주형님께서 지적해주신 내용입니다.

api 문서에서는 아래와 같이..



```
BlockingQueue.add 
Inserts the specified element into this queue if it is possible to do so immediately without 
violating capacity restrictions,
returning true upon success and throwing an IllegalStateException if no space is currently 
available.

BlockingQueue.put
Inserts the specified element into this queue, waiting if necessary for space to become 
available.
```



add와 put의 동작 방식이 다르게 설명이 되어있습니다. 위 예제에서도 Linked... 를 사용하시는 대신
put 메서드를 사용하시게 되면 queue에 자리가 생길때까지 기다리게 된다고 하네요.


\2. Consumer에서 사용하는 take() 메서드는 queue가 비어있으면 기다립니다.
그리고, queue가 채워지면 실행이 되는 구조 입니다. Socket의 accept를 생각하시면 되겠네요..

 

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 

출처: http://aroundck.tistory.com/867

 

이 자료는 **"에어콘" 사의 "자바 병렬 프로그래밍" 이라는 도서의 내용을 학습하면서 정리한 내용**입니다. 예제로 제시된 소스코드 및 자세한 설명은 책을 참조하세요~

 

**05. 구성 단위**





**5.1. 동기화된 컬랙션 클래스**

\- 동기화되어 있는 컬렉션 클래스의 대표 주자는 바로 Vector 와 HashTable이다.  Collections.synchronizedXxx 메소드를 사용해 이와 비슷하게 동기화되어 있는 몇 가지 클래스가 제공된다. 이  클래스는 모두 public 으로 선언된 모든 메소드를 클래스 내부에 캡슐화해 내부의 값을 한 스레드만 사용할 수 있도록 제어하면서 스레드 안전성을 확보하고 있다.



**5.1.1. 동기화된 컬렉션 클래스의 문제점.**


\- 동기화된 컬렉션 클레스는 스레드 안전성을 확보하고 있기는 하다. 하지만 여러 개의 연산을 묶어 하나의  단일 연산처럼 활용해야 할 필요성이 항상 발생한다. 두 개 이상의 연산을 묶어 사용해야 하는 예는 반복(iteration),  이동(navigation), 없는 경우에만 추가( add if absent ) 등이 있다. 동기화된 컬렉션을 사용하면 따로 락이나 동기화 기법을 사용하지 않는다 해도 이런 대부분의 기능이 모두 스레드 안전하다. 하지만 여러 스레드가 해당 컬렉션 하나를 놓고  동시에 그 내용을 변경하려 한다면 컬렉션 클래스가 상식적으로 올바른 방법으로 동작하지 않을 수도 있다.

\- 동기화된 컬렉션 클래스는 대부분 클라이언트 측 락을 사용할 수 있도록 만들어져 있기 때문에, 컬렉션 클래스가 사용하는 락을 함께 사용한다면 새로 추가하는 기능을 컬렉션 클래스에 들어 있는 다른 메소드와 같은 수준으로 동기화시킬 수 있다. 동기화된 컬렉션  클래스는 컬렉션 클래스 자체를 락으로 사용해 내부의 전체 메소드를 동기화시키고 있다.



**5.1.2. Iterator 와 ConcurrentModificationException**


\- Collection 클래스에 들어 있는 값을 차례로 반복시켜 읽어내는 가장 표준적인 방법은 바로 **Iterator** 를 사용하는 방법이다. ( 이런 방법은 Iterator를 직접 사용하건, 아니면 자바 5.0부터 사용할 수 있는 특별한 문법의  for each 문을 사용하건 동일하다.) Iterator를 사용해 컬렉션 클래스 내부의 값을 차례로 읽어다 사용한다 해도  반복문이 실행되는 동안 다른 스레드가 컬렉션 클래스 내부의 값을 추가하거나 제거하는 등의 변경 작업을 시도할 때 발생할 수 있는  문제를 막아주지는 못한다. 다시 말해 동기화된 컬렉션 클래스에서 만들어낸 Iterator를 사용한다 해도 다른 스레드가 같은  시점에 컬렉션 클래스 내부의 갓을 변경하는 작업을 처리하지는 못하게 만들어져 있고, 대신 즉시 멈춤( fail-fast ) 의  형태로 반응하도록 되어 있다. 즉시 멈춤이란 **반복문을 실행하는 도중에 컬렉션 클래스 내부의 값을 변경하는 상황이 포착되면 그 즉시 ConcurrentModificationException 예외를 발생**시키고 멈추는 처리 방법이다.

\- 컬렉션 클래스는 내부에 값 변경 횟수를 카운트하는 변수를 마련해두고, 반복문이 실행되는 동안 변경 횟수 값이 바뀌면  hasNext 메소드나 next 메소드에서 ConcurrentModificationException을 발생시킨다. 더군다나 **변경 횟수를 확인하는 부분이 적절하게 동기화되어 있지 않기 때문에 반복문에서 변경 횟수를 세는 과정에서 스테일 값을 사용하게 될 가능성**도 있고, 따라서 변경 작업이 있었다는 것을 모를 수도 있다는 말이다. 이렇게 구현한 모습이 문제가 있기는 하지만 전체적인 성능을  떨어뜨릴 수 있기 때문에 변경 작업이 있었다는 상황을 확인하는 기능에 정확한 동기화 기법을 적용하지 않았다고 볼 수 있다.

\- **단일 스레드 환경의 프로그램에서도 ConcurrentModificationException이 발생할 수** 있다. 반복문 내부에서 Iterator.remove 등의 메소드를 사용하지 않고 해당하는 컬렉션의 값을 직접 제거하는 등의 작업을 하려 하면 예외 상황이 발생한다.

\- **for-each 반복문**을 사용해 컬렉션 클래스의 값을 차례로 읽어들이는 코드는 **컴파일할 때, 자동으로 Iterator를 사용하면서 hasNext 나 ext 메소드를 매번 호출하면서 반복하는 방법으로 변경**한다. 따라서 반복문을 실행할 때 ConcurrentModificationException이 발생하지 않도록 미연에 방지하는 방법은  Vector에서 반복문을 사용할 때처럼 반복문 전체를 적절한 락으로 동기화를 시키는 방법밖에 없다.

\- **반복문을 실행하는 코드 전체를 동시화시키는 방법이 그다지 훌륭한 방법이 아니라**고 주장하는 이유는, 컬렉셔넹 엄청나게 많은 수의 값이 들어 있거나 값마다 반복하면서 실행해야 하는 작업이 시간이 많이 소모되는  작업일 수 있는데, 이런 경우에는 컬렉션 클래스 내부의 값을 사용하고자 하는 스레드가 상당히 오랜 시간을 대기 상태에서 기다려야 할 수 있다는 말이다. 또한 반복문에서 락을 잡고 있는 상황에서 또 다른 락을 확보해야 한다면, 데드락(deadlock)이 발생할  가능성도 높아진다. 

\- 소모상태(starvation)이나 데드락의 위험이 있는 상태에서  컬렉션 클래스를 오랜 시간 동안 락으로 막아두고 있는 상태라면 전체 애플리케이션의 확장성을 해칠 수도 있다. 반복문에서 락을 오래 잡고 있으면 있을수록, 락을 확보하고자 하는 스레드가 대기 상태에 많이 쌓일 수 있고, 대기 상태에 스레드가 적체되면 될수록  CPU 사용량이 급격하게 증가할 가능성이 높다.

\-  반복문을 실행하는 동안 컬렉션 클래스에  들어 있는 내용에 락을 걸어둔 것과 비슷한 효과를 내려면 clone 메소드로 복사본을 만들어 복사본을 대상으로 반복문을 사용할 수 있다. 이렇게 clone 메소드로 복사한 사본은 특정 스레드에 한정되어 있으므로 반복문이 실행되는 동안 다른 스레드에서 컬렉션  사본을 건드리기 어렵기 때문에 ConcurrentModificationException이 발생하지 않는다. ( 물론 최소한  clone 메소드를 실행하는 동안에는 컬렉션의 내용을 변경할 수 없도록 동기화시켜야 한다. ) 

\- clone 메소드로 복사본을 만드는 작업에도 시간은 필요하기 마련이다. 따라서 반복문에서 사용할 목적으로 복사본을 만드는 방법도 컬렉션에 들어 있는 항목의 개수, 반복문에서 개별 항목마다 실행해야 할 작업이 얼마나 걸리는지, 컬렉션의 여러 가지 기능에 비해 반복 기능을 얼마나 빈번하게 사용하는지, 그리고 응답성과 실행 속도 등의 여러가지 요구 사항을 충분히 고려해서 적절하게 적용해야 한다.





**5.1.3. 숨겨진 Iteraotr**


\- **컬렉션 클래스의 toString 메소드 소스코드를 들여다 보면 해당 컬렉션 클래스의 tierator 메소드를 호출**해 내용으로 보관하고 있는 개별 클래스의 toString 메소드를 호출해 출력할 문자열을 만들어 내도록 되어 있다.

\- 개발자는 상태 변수와 상태 변수의 동기화를 맞춰주는 락이 멀리 있을수록 동기화를 맞춰야 한다는 필요성을 잊기 쉽다.



클래스 내부에서 필요한 변수를 모두 캡슐화하면 그 상태를 보존하기가 훨씬 편리한 것처럼, 동기화 기법을 클래스 내부에 캡슐화하면 동기화 정책을 적용하기가 쉽다.


\- toString 메소드뿐만 아니라 컬렉션 클래스의 **hashCode 메소드나 equals 메소드도** **내부적으로 iterator 를 사용**한다. c**ontainsAll, removeAll, retainAll 등의 메소드, 컬렉션 클래스를 넘겨받는 생성 메소드 등도 모두 내부적으로 iterator 를 사용**한다. 이렇게 내부적으로 iterator를 사용하는 모든 메소드에서 ConcurrentModificationException 이 발생할 가능성이 있다.








**5.2. 병렬 컬렉션.**

\- 동기화된 컬렉션 클래스는 컬렉션의 내부 변수에 접근하는 통로를 일련화해서 스레드 안전성을 확보했다. 여러 스레드가 한꺼번에 동기화된 컬렉션을 사용하려고 하면 동시 사용성은 상당 부분 손해를 볼 수밖에 없다.

\- **병렬 컬렉션은 여러 스레드에서 동시에 사용할 수 있도록 설계되**어 있다. HashMap 을 대치하면서 병렬성을 확보한 **ConcurrentHashMap** 과 List 클래스의 하위 클래스이며 객체 목록을 반복시키며 열람하는 연산의 성능을 최우선으로 구현한  **CopyOnWriteArrayList** 도 병렬 컬렉션이다. **ConcurrentMap** 도 병렬 컬렉션인데, 인터페이스를 보면 추가하려는 항목이 기존에 없는 경우에만 새로 추가하는 put-if-absent, replace, conditional remove 연산 등이 정의되어 있다.



기존에 사용하던 동기화 컬렉션 클래스를 병렬 컬렉션으로 교체하는 것만으로도 별다른 위험 요소 없이 전체적인 성능을 상당히 끌어 올릴 수 있다.


\- Queue 인터페이스는 작업할 내용을 순서대로 쌓아둘 수 있는 구조이고, **ConcurrentLinkedQueue 는 FIFO 방식 Queue** 이며, **PriorityQueue 는 우선 순위에 따라 큐에 쌓여 있는 항목이 추출되는 순서가 바뀌는 특성**을 가지고 있다. Queue 인터페이스에 정의되어 있는 연산은 동기화를 맞추느라 대기 상태에서 기다리는 부분이 없다.

\- Queue 를 상속받은 **BlockingQueue** 클래스는 큐에 항목을 추가하거나 뽑아낼 때 상황에 따라 대기할 수 있도록 구현되어 있다. 예를 들어 **큐가 비어 있다면 큐에서 항목을 봅아내는 연산은 새로운 항목이 추가될 때까지 대기한다. 반대로 큐에 크기가 지정되어 있는 경우에 큐가 지정한 크기만큼 가득 차 있다면, 큐에 새로운 항목을 추가하는 연산은 큐에 빈 자리가 생길 때까지 대기**한다. BlockingQueue 클래스는 **프로듀서-컨슈머( producer-consumer ) 패턴**을 구현할 때 굉장히 편리하게 사용할 수 잇다.

\- **ConcurrentSkipListMap** 과 **ConcurrentSkipListSet** 은 **각각 SortedMap 과 SortedSet 클래스의 병렬성을 높이도록 발전된 형태**이다. ( SortedMap 과 SortedSet 은 treeMap 과 treeSet을 synchronizedMap 으로 처리해 동기화시킨 컬렉션과 같다. ) 





**5.2.1. ConcurrentHashMap**


\- **ConcurrentHashMap** 은 HashMap 과 같이 해시를 기반으로 하는 Map이다. 하지만 내부적으로는 이전에 사용하던 것과 **전혀 다른 동기화 기법을 채택해 병렬성과 확장성이 훨씬 나아졌다.** 이전에는 모든 연산에서 하나의 락을 사용했기 때문에 특정 시점에 하나의 스레드만이 해당 컬렉션을 사용할 수 있었다. 하지만 ConcurrentHashMap 은 **락스트라이핑( Lock striping )** 이라 부르는 굉장히 세밀한 동기화 방법을 사용해 여러 스레드에서 공유하는 상태에 훨씬 잘 대응할 수 있다. 

\- 값을 읽어가는 연산은 많은 수의 스레드라도 얼마든지 동시에 처리할 수 있고, 읽기 연산과 쓰기 연산도 동시에 처리할 수 있으며, 쓰기 연산은 제한된 개수만큼 동시에 수행할 수 있다. 속도를 보자면 여러 스레드가 동시에 동작하는 환경에서 일반적으로 훨씬 높은 성능 결과를 볼 수 있으며, 이와 함꼐 단일 스레드 환경에서도 성능상의 단점을 찾아볼 수 없다. 

\- 다른 병렬 컬렉션 클래스와 비슷하게 **ConcurrentHashMap 클래스도 Iterator를 만들어 내는 부분에서 많이 발전**했는데, **ConcurrentHashMap 이 만들어 낸 iterator 는 ConcurrrentModificationException 을 발생시키지 않는다.** 따라서 ConcurrentHashMap의 항목을 대상으로 반복문을 실행하는 경우에는 따로 락을 걸어 동기화해야 할 필요가 없다.

\- ConcurrrnetHashMap 에서 만들어 낸 iterator 는 즉시 멈춤(fail-fast) 대신 미약한 일관성 전략을 취한다. 미약한 일관성 전략은 반**복문과 동시에 컬렉션의 내용을 변경한다 해도 Iterator 를 만들었던 시점의 상황대로 반복을 계속**할 수 있다. 게다가 Iterator를 만든 시점 이후에 변경된 내용을 반영해 동작할 수도 있다.( 이 부분은 반드시 보장되지는 않는다. ) 

\- 병렬성 문제때문에 Map의 모든 하위 클래스에서 공통적으로 사용하는 size 메소드나 isEmpty 메소드의 의미가 약간  약해졌다. 예를 들어 size 메소드는 그 결과를 리턴하는 시점에 이미 실제 객체의 수가 바뀌었을 수 있기 때문에 정확히 말하자면 size 메소드의 결과는 정확한 값일 수 없고, 단지 추정 값일 뿐이다.

\- 동기화된 Map 에서는 지원하지만 ConcurrentHashMap에서는 지원하지 않는 기능이 있는데, 바로 맵을 독점적으로 사용할 수 있도록  막아버리는 기능이다. HashTable 과 SynchronizedMap 메소드를 사용하면 Map 에 대한 락을 잡아 다른  스레드에서 사용하지 못하도록 막을 수 있다. 

\- ConcurrentHashMap 을 사용하면 HashTable 이나 SynchronizedMap 메소드를 사용하는 것에 비해 단점이 있기는 하지만, 훨씬 많은 장점을 얻을 수 있기 때문에 대부분의 경우에는 **HashTable 이나** **SynchronizedMap 을 사용하던 부분에 ConcurrentHashMap 을 대신 사용하기만 해도 별 문제 없이 많은 장점을 얻을 수** 있다. 만약 작업 중인 애플리케이션에서 특정 Map 을 완전히 독점해서 사용하는 경우가 있다면, 그 부분에 ConcurrentHashMap 을 적용할 때는 충분히 신경을 기울여야 한다. 





**5.2.2. Map 기반의 또 다른 단일 연산**


\- ConcurrentHashMap 클래스에는 일반적으로 많이 사용하는 put-if-absent, remove-if-equals, replace-if-equal 과 같이 자주 필요한 몇 가지의 연산이 이미 구현되어 있다.





**5.2.3. CopyOnWriteArrayList**


\- **CopyOnWriteArrayList** 클래스는 동기화된 List 클래스보다 **병렬성을 훨씬 높이고**자 만들어졌다. 병렬성이 향상됐고, 특히 **List에 들어있는 값을 Iterator로 불러다 사용하려 할 때 List 전체에 락을 걸거나 List 를 복제할 필요가 없다.** ( CopyOnWriteArrayList 와 비슷하게 Set인터페이스를 구현하는 **CopyOnWriteArraySet** 도 있다. )

\- '변경할 때마다 복사'하는 컬렉션 클래스는 불변 객체를 외부에 공개하면 여러 스레드가 동시에 사용하려는 환경에서도 별다른 동기화 작업이 필요 없다는 개념을 바탕으로 스레드 안전성을 확보하고 있다. 하지만 컬렉션이라면 항상 내용이 바귀어야 하기 때문에,  컬렉션의 내용이 변경될 때마다 복사본을 새로 만들어 내는 전략을 취한다. 만약 CopyOnWriteArrayList 컬렉션에서  iterator 를 뽑아내 사용한다면 Iterator  를 뽑아내는 시점의 컬렉션 데이터를 기준으로 반복하며, 반복하는 동안  컬렉션에 추가되거나 삭제되는 내용은 반복문과 상관 없는 복사본을 대상으로 반영하기 때문에 동시 사용성에 문제가 없다. 

\- 반복문에서 락을 걸어야 할 필요가 있기는 하지만, 반복할 대상 전체를 한번에 거는 대신 개별 항목마다 가시성을 확보하려는 목적으로 잠깐씩 락을 거는 정도면 충분하다.

\- 변경할 때마다 복사하는 컬렉션에서 뽑아낸 Iterator를 사용할 때는  ConcurrentModificationException이 발생하지 않으며, 컬렉션에 어떤 변경 작업을 가한다 해도  Iteraotr를 뽑아내던 그 시점에 컬렉션에 들어 있던 데이터를 정확하게 활용할 수 있다.

\- 물론 컬렉션의 데이터가 변경될 때마다 복사본을 만들어내기 때문에 성능의 측면에서 손해를 볼 수 있고, 특히나 컬렉션에 많은 양의 자료가 들어 있다면 손실이 클 수 있다. 따라서 변경할 때마다 복사하는 컬렉션은 변경 작업보다 반복문으로 읽어내는 일이 훨씬  빈번한 경우에 효과적이다. 







**5.3.. 블로킹 큐와 프로듀서-컨슈머 패턴**

\- **블로킹 큐(blocking queue)는 put과 take라는 핵심 메소드**를 갖고 있고, 더불어 **offer 와 poll 이라는 메소드**도 갖고 있다. 만약 큐가 가득 차 있다면 put 메소드는 값을 추가할 공간이 생길 때까지 대기한다. 반대로 큐가 비어 있는  상태라면 take 메소드는 뽑아낼 값이 들어올 때까지 대기한다. 큐는 그 크기를 제한할 수도 있고, 제한하지 않을 수도 있다.

\- **블로킹 큐는 프로듀서-컨슈머(producer-consumer)패턴을 구현할 때 사용하기에 좋다.** 프로듀서-컨슈머 패턴은 **'해야 할 일' 목록을 가운데에 두고 작업을 만들어 내는 주체와 작업을 처리하는 주체를 분리시키는 설계 방법**이다. 프로듀서-컨슈머 패턴을 사용하면 작업을 만들어 내는 부분과 작업을 처리하는 부분을 완전히 분리할 수 있기 때문에 개발 과정을 좀 더 명확하게 단순화시킬 수 있다.

\-  프로듀서-컨슈머 패턴을 적용해 프로그램을 구현할 때 블로킹 큐를 사용하는 경우가 많은데, 예를 들어 프로듀서는 작업을 새로  만들어 큐에 쌓아두고, 컨슈머는 큐에 쌓여 있는 작업을 가져다 처리하는 구조다. 프로듀서는 어떤 컨슈머가 몇 개나 동작하고  있는지에 대해 전혀 신경 쓰지 않을 수 있다. 단지 새로운 작업 내용을 만들어 큐에 쌓아두기만 하면 된다. 반대로 컨슈머 역시  프로듀서에 대해서 뭔가를 알고 있어야 할 필요가 없다. 프로듀서가 몇 개이건, 얼마나 많은 작업을 만들어 내고 있건 상관이 없다. 단지 큐에 쌓여 있는 작업을 가져다 처리하기만 하면 된다. 블로킹 큐를 사용하면 여러 개의 프로듀서와 여러 개의 컨슈머가  작동하는 프로듀서-컨슈머 패턴을 손쉽게 구현할 수 있다. 큐와 함께 스레드 풀을 사용하는 경우가 바로 프로듀서-컨슈머 패턴을  활용하는 가장 흔한 경우이다.

\- 프로듀서가 컨슈머가 감당할 수 잇는 것보다 많은 양의 작업을 만들어 내면 해당 애플리케이션의 큐에는 계속해서 작업이 누적되어 결국에는 메모리 오류가 발생하게 된다. 하지만 큐의 크기에  제한을 두면 큐에 빈 공간이 생길 때까지 put 메소드가 대기하기 때문에 프로듀서 코드를 작성하기가 훨씬 간편해진다. 그러면  컨슈머가 작업을 처리하는 속도에 프로듀서가 맞춰야 하며, 컨슈머가 처리하는 양보다 많은 작업을 만들어 낼 수 없다.

\-  블로킹 큐에는 offer 메소드가 있는데, **offer 메소드는 큐에 값을 넣을 수 없을 때 대기하지 않고 바로 공간이 모자라 추가할 수 없다는 오류를 알려준다.** offer 메소드를 잘 활용하면 프로듀서가 작업을 많이 만들어 과부하에 이르는 상태를 좀 더 효과적으로 처리할 수 있다.



블로킹 큐는 애플리케이션이 안정적으로 동작하도록 만들고자 할 때 요긴하게 사용할 수 있는 도구이다. 블로킹 큐를  사용하면 처리할 수 있는 양보다 훨씬 많은 작업이 생겨 부하가 걸리는 상황에서 작업량을 조절해 애플리케이션이 안정적으로 동작하도록 유도할 수 있다.


\- 생각하기에는 컨슈머가 항상 밀리지 않고 작업을 마쳐준다고 가정하고, 따라서 작업 큐에 제한을 둘 필요가 없을 것이라고 마음 편하게 넘어갈 수도 있다. 이런 가정을 하는 순간 나중에 프로그램 구조를 뒤집어 엎어야 하는 원인을 하나  남겨두는 것뿐이니 주의하자. 블로킹 큐를 사용해 설계 과정에서부터 프로그램에 자원 관리 기능을 추가하자.

\- **LinkedBlockingQueue** 와 **ArrayBlockingQueue** 는 **FIFO 형태의 큐**인데, LinkedList 나 ArrayList 에서 동기화된 List 인스턴스를 뽑아 사용하는 것보다 성능이 좋다. **PriorityBlockingQueue** 클래스는 우선 순위를 기준으로 동작하는 큐이고, FIFO 가 아닌 다른 순서로 큐의 항목을 처리해야 하는 경우에 손쉽게 사용할 수 있다.

\- **SynchronousQueue 클래스도 BlockingQueue 인터페이스를 구현하는데, 큐에 항목이 쌓이지 않으며, 따라서 큐 내부에 값을 저장할 수 있도록 공간을 할당하지도 않는다.** 대신 큐에 값을 추가하려는 스레드나 값을 읽어가려는 스레드의 큐를 관리한다.

\- 프로듀서와 컨슈머가 직접 데이터를 주고받을 때까지 대기하기 때문에 프로듀서에서 컨슈머로 데이터가 넘어가는 순간은 굉장히  짧아진다는 특징이 있다. 컨슈머에게 데이터를 직접 넘겨주기 때문에 넘겨준 데이터와 관련되어 컨슈머가 갖고 있는 정보를 프로듀서가  쉽게 넘겨 받을 수도 있다.

\- SynchronousQueue는 데이터를 넘겨 받을 수 있는 충분한 개수의 컨슈머가 대기하고 있는 경우에 사용하기 좋다.



**5.3.1. 예제 : 데스크탑 검색**

 

**5.3.2. 직렬 스레드 한정**


\- 프로듀서-컨슈머 패턴과 블로킹 큐는 가변 객체(mutable object)를 사용할 때 **객체의 소유권을 프로듀서에서 컨슈머로 넘기는 과정에서 직렬 스레드 한정(serial thread confinement)기법**을 사용한다. 스레드에 한정된 객체는 특정 스레드 하나만이 소유권을 가질 수 있는데, 객체를 안전한 방법으로 공개하면 객체에 대한  소유권을 이전(transfer)할 수 있다. 이렇게 소유권을 이전하고 나면 이전받은 컨슈머 스레드가 객체에 대한 유일한 소유권을  가지며, 프로듀서 스레드는 이전된 객체에 대한 소유권을 완전히 잃는다. 이렇게 안전한 공개 방법을 사용하면 새로운 소유자로 지정된 스레드는 객체의 상태를 완벽하게 볼 수 있지만 원래 소유권을 갖고 있던 스레드는 전혀 상태를 알 수 없게 되어, 새로운 스레드  내부에 객체가 완전히 한정된다.

\- **객체 풀(object pool)은 직렬 스레드 한정 기법을 잘 활용하는 예**인데, 풀에서 소유하고 있던 객체를 외부 스레드에게 '빌려주는' 일이 본업이기 때문이다. 풀 내부에 소유하고 있던 객체를 외부에 공개할 떄 적절한 동기화 작업이 되어 있고, 그와 함게 풀에서 객체를 빌려다 사용하는 스레드 역시 빌려온 객체를 외부에 공개하거나 풀에 반납한 이후에 계속해서 사용하는 등의 일을 하지 않는다면 풀 스레드와 사용자 스레드 간에 소유권이 원활하게 이전되는 모습을 볼 수 있다.

\- 가변 객체의 소유권을 이전해야 할 필요가 있다면, 위에서 설명한 것과 다른 객체 공개 방법을 사용할 수도 있다. 하지만 항상 소유권을 이전받는 스레드는 단 하나여야 한다는 점을 주의해야 한다.





**5.3.3. 덱, 작업 가로채기**


\- **Deque(덱) 과 BlockingDeque 은 각각 Queue 와 Blockingqueue 를 상속받은 인터페이스**이다. **Deque는 앞과 뒤 어느 쪽에도 객체를 쉽게 삽입하거나 제거할 수 있도록 준비된 큐**이며, Deque을 상속받은 실제 클래스로는 **ArrayDeque**과 **LinkedBlockingDeque** 이 있다.

\- **작업 가로채기(work stealing) 이라는 패턴을 적용할 때에는 덱을 그대로 가져다 사용할 수 있다.** **작업 가로채기 패턴에서는 모든 컨슈머가 각자의 덱을 갖는다. 만약 특정 컨슈머가 자신의 덱에 들어 있던 작업을 모두 처리하고 나면 다른 컨슈머의 덱에 쌓여있는 작업 가운데 맨 뒤에 추가된 작업을 가로채 가져올 수 있다.** 작업 가로채기 패턴은 그 특성상 컨슈머가 하나의 큐를 바라보면서 서로 작업을 가져가려고 경쟁하지 않기 때문에 일반적인 프로듀서-컨슈머 패턴보다 규모가 큰 시스템을 구현하기에 적당하다. 더군다나 컨슈머가 다른 컨슈머의 큐에서 작업을 가져오려 하는 경우에도 앞이  아닌 맨 뒤의 작업을 가져오기 때문에 맨 앞의 작업을 가져가려는 원래 소유자와 경쟁이 일어나지 않는다.

\- 작업 가로채기 패턴은 또한 컨슈머가 프로듀서의 역할도 갖고 있는 경우에 적용하기에 좋은데, 스레드가 작업을 진행하는 도중에 새로 처리해야 할 작업이 생기면 자신의 덱에 새로운 작업을 추가한다. ( 작업을 서로 공유하도록 구성하는 경우에는 다른 작업 스레드의 덱에 추가하기도 한다.) 만약 자신의 덱이 비었다면 다른 작업 스레드의 덱을 살펴보고 밀린 작업이 있다면 가져다 처리해 자신의  덱이 비었다고 쉬는 스레드가 없도록 관리한다. 







**5.4. 블로킹 메소드, 인터럽터블 메소드**

\- 스레드는 여러 가지 원인에 의해 블록 당하거나, 멈춰질 수 있다. 예를 들어 I/O 작업이 끝나기를 기다리는  경우도 있고, 락을 확보하기 위해 기다리는 경우도 있고, Thread.sleep 메소드가 끝나기를 기다리는 경우도 있고, 다른  스레드가 작업 중인 내용의 결과를 확인하기 위해 기다리는 경우도 있다.

\- 스레드가 블록되면 동작이 멈춰진 다음 **블록된 상태(BLOCKED, WAITING, TIMED_WAITING)** 가운데 하나를 갖게 된다. 블로킹 연산은 단순히 실행 시간이 오래 걸리는 일반 연산과는 달리 멈춘 상테에서 특정한 신호를 받아야 계속해서 실행할 수 있는 연산을 말한다.

\- 기다리던 외부 신호가 확인되면 스레드의 상태가 다시 **RUNNABLE 상태**로 넘어가고 다시 시스템 스케줄러를 통해 CPU 를 사용할 수 있게 된다.

\- Thread 클래스는 해당 스레드를 중단시킬 수 있도록 **interrupt 메소드를 제공**하며, 해당 스레드에 인터럽트가 걸려 중단된 상태인지를 확인할 수 있는 메소드도 있다. 모든 스레드에는 인터럽트가 걸린 상태인지를 알려주는 불린 값이 있으며, 외부에서 인터럽트를 걸면 불린 변수에 true 가 설정된다.

\-  **스레드 A가 스레드 B에 인터럽트를 건다는 것은 스레드 B에게 실행을 멈추라고 '요청'하는 것일 뿐**이며, 인터럽트가 걸린 스레드 B는 정상적인 종료 시점 이전에 적절한 때를 잡아 실행 중인 작업을 멈추면 된다.

\- **프로그램이 호출하는 메소드 가운데 InterruptedException 이 발생할 수 있는 메소드가 있다면 그 메소드를 호출하는 메소드 역시 블로킹 메소드**이다. 따라서  InterruptedException이 발생했을 때 그에 대처할 수 있는 방법을 마련해둬야 한다. 라이브러리 형태의 코드라면 일반적으로 두 가지 방법을 사용할 수 있다.

 **1. InterruptedException 을 전달** : 받아낸 InterruptedException 을 그대로 호출한 메소드에게 넘긴다.
 **2. 인터럽트를 무시하고 복구** : InterruptedException 을 throw 할 수 없을 수 있는데, 이 경우는 예외를 catch 한 다음, 현재  스레드의 interrupt 메소드를 호출해 인터럽트 상태를 설정해 상위 호출 메소드가 인터럽트 상황이 발생했음을 알 수 있도록  해야 한다.

\- **InterruptedException을 처리함에 있어서** **하지 말아야 할 일이 한 가지** 있다. 바로 **InterruptedException 을 cath 하고는 무시하고 아무 대응도 하지 않는 일**이다. 이렇게 아무런 대응을 하지 않으면 인터럽트가 발생했었다는 증거를 인멸하는 것이며, 호출 스택의 상위 메소드가 인터럽트에 대응해 조치를 취할 수 있는 기회를 주지 않는다.

\- 발생한 InterruptedException 을 먹어버리고 더 이상 전파하지 않을 수 있는 경우는 Thread 클래스를 직접 상속하는 경우뿐이며, 이럴 때는 인터럽트에 필요한 대응 조치를 모두 취했다고 간주한다.








**5.5. 동기화 클래스.**

\- **상태 정보를 사용해 스레드 간의 작업 흐름을 조절할 수 있도록 만들어진 모든 클래스륻 동기화 클래스( synchronizer )** 라고 한다. 동기화 클래스의 예로는 **세마포어(semaphore), 배리어(barrier), 래치(latch) 등**이 있다.

\- 모든 동기화 클래스는 구조적인 특징을 갖고 있다. 모두 동기화 클래스에 접근하려는 스레드가 어느 경우에 통과하고 어느 경우에는  대기하도록 멈추게 해야 하는지를 결정하는 상태 정보를 갖고 있고, 그 상태를 변경할 수 있는 메소드를 제공하고, 동기화 클래스가  특정 상태에 진입할 때가지 효과적으로 대기할 수 있는 메소드도 제공한다.



**5.5.1. 래치**


\- 래치는 스스로가 **터미널(terminal)상태에 이를 때까지의 스레드가 동작하는 과정을 늦출 수 있도록 해주는 동기화 클래스**이다. 일종의 관문과 같은 형태로, 래치가 터미널 상태에 이르기 전에는 관문이 닫혀 있고, 어떤 스레드도 통과할 수 없다. 그리고 래치가 터미널 상태에 다다르면 관문이 열리고 모든 스레드가 통과한다. **래치가 한 번 터미널 상태에 다다르면 그 상태를 다시 이전으로 되돌릴 수는 없으며, 따라서 한 번 열린 관문은 계속해서 열린 상태로 유지**된다.

\- 특정한 단일 동작이 완료되기 이전에는 어떤 기능도 동작하지 않도록 막아야 하는 경우에 요긴하게 사용할 수 있다.
 \* 특정 자원을 확보하기 전에는 작업을 시작하지 말아야 하는 경우.
 \* 의존성을 갖고 있는 다른 서비스가 시작하기 전에는 특정 서비스가 실행되지 않도록 막아야 하는 경우.
 \* 특정 작업에 필요한 모든 객체가 실행할 준비를 갖출 때까지 기다리는 경우.

\- **CountDownLatch**는 하나 또는 둘 이상의 스레드가 여러 개의 이벤트가 일어날 때까지 대기할 수 있도록 되어 있다. 래치의 상태는 양의 정수 값으로 카운터를 초기화하며, 이 값은 대기하는 동안 발생해야 하는 이벤트의 건수를 의미한다.

\- CountDownLatch 의 **countDown** 메소드는 대기하던 이벤트가 발생했을 때 내부에 갖고 있는 이벤트 카운터를 하나 낮춰주고, **await** 메소드는 래치 내부의 카운터가 0 이 될 때까지 대기하던 이벤트가 모두 발생했을 때까지 대기하도록 하는 메소드이다. 외부 스레드가  awiat 메소드를 호출할 때 래치 내부의 카운터가 0보다 큰 값이었다면, await 메소드는 카운터가 0이 되거나, 대기하던  스레드에 인터럽트가 걸리거나, 대기 시간이 길어 타임아웃이 걸릴 때까지 대기한다.





**5.5.2. FutureTask**


\- **FutureTask 가 나타내는 연산 작업은 Callable 인터페이스를 구현**하도록 되어 있는데, 시**작 전 대기, 시작됨, 종료됨과 같은 세 가지 상태**를 가질 수 있다. 종료된 상태는 정상적인 종료, 취소, 예외 상황발생과 같이 연산이 끝나는 모든 종류의 상태를 의미한다. **FutureTask 가 한 번 종료됨 상태에 이르고 나면 더 이상 상태가 바뀌는 일은 없다.**

\- FutureTask 는 Executor 프레임웍에서 비동기적인 작업을 실행하고자 할 때 사용하며, 기타 **시간이 많이 필요한 모든 작업이 있을 때 실제 결과가 필요한 시점 이전에 미리 작업을 실행시켜두는 용도**로 사용한다. 





**5.5.3. 세마포어 ( Semaphore )**


\- **카운팅 세마포어(counting semaphore)는 특정 자원이나 특정 연산을 동시에 사용하거나 호출할 수 있는 스레드의 수를 제한하고자 할 때 사용**한다. 카운팅 세마포어의 이런 기능을 사용하면 **자원 풀(pool)이나 컬렉션의 크기에 제한을 두고자 할 때** 유용한다.

\- **Semaphore 클래스는 가상의 퍼밋(permit)을 만들어 내부 상태를 관리**하며, Semaphore 를 생성할 때 생성 메소드에 최초로 생성할 퍼밋의 수를 넘겨준다. 외부 스레드는 퍼밋을 요청해 확보( 남은  퍼밋이 있는 경우 )하거나, 이전에 확보한 퍼밋을 반납할 수도 있다. 현재 사용할 수 잇는 남은 퍼밋이 없는 경우, acquire 메소드는 남는 퍼밋이 생기거나, 인터럽트가 걸리거나, 지정한 시간을 넘겨 타임아웃이 걸리기 전까지 대기한다. release 는  확보했던 퍼밋을 다시 세마포어에게 반납하는 기능을 한다.

\- 세마포어는 데이터베이스 연결 풀과 같은 자원 풀에서 요긴하게 사용할 수 있다.





**5.5.4. 배리어**


\- **배리어( barrier ) 는 특정 이벤트가 발생할 때까지 여러 개의 스레드를 대기 상태로 잡아둘 수 있다는 측면에서 래치와 비슷**하다고 볼 수 있다. **래치와의 차이점은 모든 스레드가 배리어 위치에 동시에 이르러야 관문이 열리고 계속해서 실행할 수 있다는 점**이 다르다. 래치는 '이벤트'를 기다리기 위한 동기화 클래스이고, 배리어는 '다른 스레드'를 기다리기 위한 동기화 클래스이다. 

\- **CyclicBarrier** 클래스를 사용하면 여러 스레드가 특정한 배리어 포인트에서 반복적으로 서로 만나는 기능을 모델링할 수 있고, 커다란 문제 하나를 여러 개의 작은 부분 문제로 분리해 반복적으로 병렬 처리하는 알고리즘을 구현하고자 할 때 적용하기 좋다.

\- 스레드는 각자 배리어 포인트에 다다르면 await 메소드를 호출하며, await 메소드는 모든 스레드가 배리어 포인트에 도달할  떄까지 대기한다. 모든 스레드가 배리어 포인트에 도달하면 배리어는 모든 스레드를 통과시키며, await 메소드에서 대기하고 있던  스레드는 대기 상태가 모두 풀려 실행되고, 배리어는 다시 초기상태로 돌아가 다음 배리어 포인트를 준비한다. **만약 await 를 호출하고 시간이 너무 오래 지나 타임아웃이 걸리거나 await 메소드에서 대기하던 스레드에 인터럽트가 걸리면 배리어는 깨진  것으로 간주하고, await 에서 대기하던 모든 스레드에 BrokenBarrierException 이 발생**한다.

\- 배리어가 성공적으로 통과하면 await 메소드는 각 스레드별로 배리어 포인트에 도착한 순서를 알려주며, 다음 배리어 포인트로  반복 작업을 하는 동안 뭔가 특별한 작업을 진행할 일종의 리더를 선출하는 데 이 값을 사용할 수 있다.

\- 배리어와 약간 다른 형태로 **Exchanger** 클래스가 있는데 Exchanger 는 **두 개의 스레드가 연결되는 배리어**이며, 배리어 포인트에 도달하면 양쪽의 스레드가 서로 갖고 있던 값을 교환한다. Exchanger 클래스는 양쪽 스레드가 서로 대칭되는 작업을 수행할 때 유용하다. 

\- Exchanger 객체를 통해 양쪽의 스레드가 각자의 값을 교환하는 과정에서 서로 넘겨지는 객체는 안전한 공개 방법으로 넘겨주기 때문에 동기화 문제를 걱정할 필요가 없다.

 

**5.6. 효율적이고 확장성 있는 결과 캐시 구현.**

 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 

출처: http://www.concretepage.com/java/linkedblockingqueue_java

 

[Home](http://www.concretepage.com/) > [Core Java](http://www.concretepage.com/java)

# LinkedBlockingQueue Java Example

By Arvind Rai, February 15, 2013

On this page we will provide example of LinkedBlockingQueue in java.  LinkedBlockingQueue has been introduced in JDK 1.5. It belongs to  java.util.concurrent package. LinkedBlockingQueue is a BlockingQueue  which uses linked node. LinkedBlockingQueue can be instantiated as  bounded as well unbounded. We will discuss here LinkedBlockingQueue with example.

### BlockingQueue

BlockingQueue is a Queue but with some different features. While  retrieving element from BlockingQueue, thread waits if it is empty. Once the element is added in BlockingQueue, thread is unblocked retrieving  element successfully. Same happens when thread is trying to add element  in BlockingQueue. If the size of BlockingQueue is limited and it is  already full, then thread waits until it gets space to add element.

### LinkedBlockingQueue

LinkedBlockingQueue implements BlockingQueue and works on the basis  of linked node. It can be instantiated as bounded as well as unbounded.  The elements in LinkedBlockingQueue are managed as First-In First-Out  (FIFO) rule. Find the details of some methods of this class. 

**offer(E e)**: It inserts the element at the tail of queue without  exceeding LinkedBlockingQueue size. On success it returns true otherwise false. 
**put(E e)**: Inserts the element at the tail of the queue and waits for space if necessary. 
**peek()**: It retrieves the head of the queue without deleting it and returns null if empty. 
**poll()**: It retrieves and removes the head of the queue and returns null if empty. 
**remove(Object o)**: Removes the specified element from the queue. 
**take()**: Retrieves and removes the head of the queue and waits if necessary.

 

### Complete Example of LinkedBlockingQueue


**LinkedBlockingQueueTest.java**

```
package com.concretepage.concurrent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
public class LinkedBlockingQueueTest {
    private final LinkedBlockingQueue<String> lbqueue = new LinkedBlockingQueue<String>();
    class ThreadA implements Runnable {
        @Override
        public void run() {
        	lbqueue.offer("AAAA");
        	lbqueue.offer("BBBB");
        	lbqueue.offer("CCCC");
        	lbqueue.offer("DDDD");
        	lbqueue.offer("EEEE");
        }
    }
    class ThreadB implements Runnable {
        @Override
        public void run() {
    		try {
    			for (int i=0; i < 5; i++) {
    				System.out.println(lbqueue.take());
    			}
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
        }
    }
    public static void main(String... args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		LinkedBlockingQueueTest linkedBlockingQueueTest = new LinkedBlockingQueueTest();
		ThreadA threadA = linkedBlockingQueueTest.new ThreadA();
		ThreadB threadB  = linkedBlockingQueueTest.new ThreadB();		
		service.execute(threadA);
		service.execute(threadB);		
		service.shutdown();        
    }
} 
```

 

Find the output.

AAAA BBBB CCCC DDDD EEEE 

 

 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////

 

출처: http://forum.falinux.com/zbxe/index.php?document_srl=661569&mid=lecture_tip

 

BlockingQueue라는 아주 유용한 클래스가 있습니다.

몰랐을땐 큐 클래스를 만들어 synchronized를 사용하여 동기화 시켜주는 작업을 했었는데...... 

진작에 알았으면 생성자, 소비자 패턴을 쉽게 구현 했을텐데...ㅡㅜ

 

이제 부터라도 잘 사용하도록 까먹지 않도록 하기 위해 샘플을 만들어 봤습니다.

 

[샘플소스]

```
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
 
public class TestBlockingQ {
 
	/**
	 * 생산자 클래스
	 * @author falbb
	 *
	 */
	static class Producer implements Runnable {
		private BlockingQueue<String> queue;
 
		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
 
		@Override
		public void run() {
 
			// 날짜 포멧
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
 
			Date date = null;
 
			while(true) {
				try {
 
					date = new Date();
					// 큐에 넣기
					queue.put(dateFormat.format(date));
 
					// 큐 사이즈 출력
					System.out.printf("[%s] : size = %d \n", 
                    Thread.currentThread().getName(), queue.size());
 
					// 대기시간
					Thread.sleep(200);					
 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
 
	}
 
	/**
	 * 소비자 클래스
	 * @author falbb
	 *
	 */
	static class Consumer implements Runnable {
 
		private BlockingQueue<String> queue;
		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
 
		@Override
		public void run() {
 
			while(true) {
				try {
 
					System.out.printf("[%s] : %s \n",Thread.currentThread().getName(), 
                    queue.take());
 
					// 대기 시간
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
 
	}
 
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		// BlockingQueue사용 방법
		// ex1)BlockingQueue<String> queue = new ArrayBlockingQueue<String>(큐의 맥스 사이즈);
		// ex2)BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(50);
 
		Thread producer = new Thread(new Producer(queue));
		producer.setName("생산자1");
		producer.start();
 
		for (int i = 0; i < 5; i++) {
			Thread consum = new Thread(new Consumer(queue));
			consum.setName("소비자"+(i+1));
			consum.start();
		}
 
	}
 
}
```

 

 

[실행 결과]



![img](https://blog.kakaocdn.net/dn/Yyc2k/btqI5zscCPt/7DVlGFkjIGVHMWJS6pMcek/img.png)



 

 

 

BlockingQueue사용 방법에 대해 좀더 설명을 부가 하자면.....

 ArrayBlockingQueue로생성시 큐사이즈를 50으로 하고 큐에 넣을때 queue.put()를 사용할때와 queue.add()를 사용할때

틀린 점이 있습니다. 예제 소스에서는 queue.put()을 사용 했는데....

 

put의 경우는 큐사이즈가 50을 넣어가면 넣을수 있는 자리가 비일 때까지 기다리지만, add의 경우는 Exception이 발생합니다.

그건 여러분이 한번 바꿔서 해보세요.

 

LinkedBlockingQueue로 생성시에는 큐사이즈를 지정할 수 도 있지만,  지정 하지 않아도 되니...

어떤 차이가 있는지 여러분이 한번 테스트 해보세요. ^^;

 

감사합니다.

 

 

 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 

출처: http://blog.ggaman.com/926

 



![img](https://blog.kakaocdn.net/dn/bblngs/btqI9Hv0vgU/UwicWA1QPyNAkA1Sys3Kk1/img.jpg)



 

 

 

아무튼 이 책에는 BlockingQueue에 대한 설명이 잠시 나온다.
이를 이용하면 Producer/Consumer 패턴을 만들기 쉽다고 나와서 직접 코드를 짜 본다.

synchronized block를 이용한 생산자/소비자 패턴은 아래와 같다.
( 간단하게 짜 본거라.. 뭐.. 잘못 되었을 수도 있다. 책임 못짐. ㅎㅎ )

```
import java.util.ArrayList;  
import java.util.Random;  
 
 
public class PlainProsumer {  
    private static ArrayList<Integer> queue = new ArrayList<Integer>();  
      
    public static void main(String[] args) {  
        Consumer c1 = new Consumer("1", queue); c1.start();  
        Consumer c2 = new Consumer("2", queue); c2.start();  
        Consumer c3 = new Consumer("3", queue); c3.start();  
          
        Producer p1 = new Producer(queue);  p1.start();  
    }  
      
    // 생산자. - 무언가를 열심히 만들어 낸다.  
    static class Producer extends Thread {  
        // INDEX  
        private volatile static int i = 1;  
          
        private ArrayList<Integer> queue;  
          
        public Producer(ArrayList<Integer> queue) {  
            this.queue = queue;  
        }  
          
        public void run() {  
            // 0.5초씩 기다렸다가 데이터를 하나씩 넣어 주자.  
            while(true) {  
                try {  
                    Thread.sleep(new Random().nextInt(1000));  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
 
                synchronized (queue) {  
                    // 데이터를 집어 넣고 나면, 데이터가 들어 갔다고 notify 시켜 줘야 한다.  
                    // 그래야 소비자들 중에서 wait하고 있는 놈들을 깨울 수 있다.  
                    queue.add(i++);  
                    queue.notify();  
                }  
            }  
        }  
    }  
      
    // 소비자.. 생산해 낸 것을 열심히 사용하자.  
    static class Consumer extends Thread {  
        private ArrayList<Integer> queue;  
        private String name;  
        public Consumer(String name, ArrayList<Integer> queue) {  
            this.name = name;  
            this.queue = queue;  
        }  
          
        public void run() {  
            while ( true ) {  
                synchronized (queue) {  
                    try {  
                        // 데이터가 들어 있지 않고 비었다면 데이터가 올때까지 기다리자.   
                        if ( queue.isEmpty() ) {  
                                queue.wait();  
                        }  
                          
                        // 생산자에서 데이터를 집어 넣고 notify해 줘서 wait를 벗어나 아래의 코드가 수행된다.  
                        Integer index = queue.remove(0);  
                        System.err.println("Consumer : " + name + "\tCount : " + index);  
                          
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
    }  
}  
```

위의 코드를 확인해 보면 알 수 있다시피, queue를 사용할때 synchronized block를 사용하여 queue에  대한 권한을 획득한 뒤에, notify 및 wait를 해 주어야 한다. 이렇게 하면 괜히 코드가 복잡해 지고  synchronized block를 사용하게 되므로 하나의 block를 더 만들어 주어야 해서 코드에 점차 { } 가 많아져서  코드가 보기 어렵게 된다.

하지만 BlockingQueue를 사용하면 synchronized block를 사용하지 않고도 똑같은 구현을 할 수 있다.

```
import java.util.Random;  
import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.BlockingQueue;  
 
 
public class BlockingProsumer {  
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);  
      
    public static void main(String[] args) {  
        Consumer c1 = new Consumer("1", queue); c1.start();  
        Consumer c2 = new Consumer("2", queue); c2.start();  
        Consumer c3 = new Consumer("3", queue); c3.start();  
          
        Producer p1 = new Producer(queue);  p1.start();  
    }  
      
    // 생산자. - 무언가를 열심히 만들어 낸다.  
    static class Producer extends Thread {  
        // INDEX  
        private volatile static int i = 1;  
          
        private BlockingQueue<Integer> queue;  
          
        public Producer(BlockingQueue<Integer> queue) {  
            this.queue = queue;  
        }  
          
        public void run() {  
            // 임의의 시간마다 데이터를 넣어 준다.  
            while(true) {  
                try {  
                    Thread.sleep(new Random().nextInt(500));  
                    // 수정사항 - offer에서 put으로 변경
                    // 데이터를 넣고 나면 알아서 notify시켜 준다. 
                    queue.put(i++); 
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
  
            }  
        }  
    }  
      
      
    // 소비자.. 생산해 낸 것을 열심히 사용하자.  
    static class Consumer extends Thread {  
        private BlockingQueue<Integer> queue;  
        private String name;  
        public Consumer(String name, BlockingQueue<Integer> queue) {  
            this.name = name;  
            this.queue = queue;  
        }  
          
        public void run() {  
            while ( true ) {  
                try {  
                    // queue에 data가 없으면 알아서 wait하고 있다.  
                    Integer index = queue.take();  
                    System.err.println("Consumer : " + name + "\tIndex : " + index);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
}  
```

 

보다시피 BlockingQueue는 자기가 알아서 wait 상태로 들어 가고 notify를 하게 된다.
이러한 BlockingQueue의 기능을 이용하면 생산자 소비자 패턴을 좀 더 쉽게 만들 수 있다.

BlockingQueue는 대략 아래와 같은 기능을 가지고 있다.

\1. queue에 data를 넣을때 가득 차 있으면, queue에 빈칸이 생길때까지 대기
boolean put(E o) throws InterruptedException;
**boolean** offer(E o)

\2. queue에 data를 넣을때 가득 차 있으면, queue에 빈칸이 생길때까지 시간을 두고 대기
**boolean** offer(E o, **long** timeout, TimeUnit unit) **throws** InterruptedException;

\3. queue에 data가 없을 경우, 데이터가 들어 올때까지 대기
E take() **throws** InterruptedException;

\4. queue에 data가 없을 경우, 데이터가 들어 올때까지 시간을 두고 대기
E poll(**long** timeout, TimeUnit unit) **throws** InterruptedException;


사실은 BlockingQueue를 사용해서 생산자/소비자 패턴을 만드는 예제는 이미 BlockingQueue의 API문서에 소개 되고 있다 ^^ ( 즉, 나는 이미 있는 예제를 만든다고 삽질한거다. ㅎㅎ )
http://java.sun.com/j2se/1.5.0/docs/api/java/util/concurrent/BlockingQueue.html


그리고 아래의 링크를 따라 가면 적당한 예제 및 사용법을 볼 수 있다. ( 한글임 )
Core Java Technologies Tech Tips - [QUEUE와 DELAYED 프로세싱](http://kr.sun.com/developers/techtips/c2004_1019.html#1)
http://kr.sun.com/developers/techtips/c2004_1019.html#1

 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 

출처: https://examples.javacodegeeks.com/core-java/util/concurrent/linkedblockingqueue/java-util-concurrent-linkedblockingqueue-example/

## 1. ArrayBlockingQueue vs LinkedBlockingQueue

In a previous article ([java.util.concurrent.ArrayBlockingQueue Example](http://examples.javacodegeeks.com/core-java/util/concurrent/arrayblockingqueue/java-util-concurrent-arrayblockingqueue/)), we talked about ArrayBlockingQueue and its usage. Here, we will try to  make some comparisons  between ArrayBlockingQueue and LinkedBlockingQueue to make clear in  which cases we should prefer each one. It is important to make clear  distinctions, as both data structures serve very similar needs, but  performance and implementation varies.

### 1.1 Performance

- ArrayBlockingQueue: It uses an **internal array** in which the  elements are kept, and the Queue interface imposes certain rules (like  the FIFO rule, which is essential to any queue). Because it uses an  array, it has a **fixed size** which is given in the constructor.
- LinkedBlocking Queue: It uses **nodes** (like a linked list), to  keep track of the order of the elements, which increases the complexity  of the data structure. It can have a fixed-size limit as well, but if we don’t define one the limit is Integer.MAX_VALUE by default.

According to the previous information, you can clearly see why ArrayBlockingQueue is **faster** than LinkedBlockingQueue, which is backed by a **benchmark that was published in an older JavaCodeGeeks article**. The benchmark specifics and results can be found [here](http://www.javacodegeeks.com/2010/09/java-best-practices-queue-battle-and.html). In every case, the performance of ArrayBlockingQueue is better.

### 1.2 Implementation in synchronization

The major implementation difference between the two data structures  (synchronization-wise) is that because ArrayBlockingQueue keeps the  elements in an array it needs **only one lock** to keep everything synchronized. On the other hand, LinkedBlockingQueue uses **two locks, one for insertion and one for extraction**. That happens because while ArrayBlockingQueue contains just an  array, LinkedBlockingQueue contains a series of connected nodes, so it  doesn’t need to keep track of insertion and extraction at the same time.

## 2. LinkedBlockingQueue Example

Like in our previous example about ArrayBlockingQueue, we are going to use a **Producer-Consumer** model in order  to check the functionality of our LinkedBlockingQueue. This time however, we are going to use a system of **multiple consumers**, to make the distinction more clear. One of the consumers will just look into the data, and the other will remove them. The producer will insert elements as usual.

 

 

 

**ArrayBlockingQueueExample.java**

```
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(10);

        Producer producer = new Producer(queue);

        ObservingConsumer obsConsumer = new ObservingConsumer(queue, producer);

        RemovingConsumer remConsumer = new RemovingConsumer(queue, producer);

        Thread producerThread = new Thread(producer);

        Thread obsConsumerThread = new Thread(obsConsumer);

        Thread remConsumerThread = new Thread(remConsumer);

        producerThread.start();

        obsConsumerThread.start();

        remConsumerThread.start();

    }

}
```

 

 

**Producer.java**

```
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {

    private LinkedBlockingQueue queue;

    private boolean running;

    public Producer(LinkedBlockingQueue queue) {

        this.queue = queue;

        running = true;

    }

    // We need to check if the producer thread is

    // Still running, and this method will return

    // the state (running/stopped).

    public boolean isRunning() {

        return running;

    }

    @Override

    public void run() {

        // We are adding elements using put() which waits

        // until it can actually insert elements if there is

        // not space in the queue.

        for (int i = 0; i < 15; i++) {

            String element = "String" + i;

            try {

                queue.put(element);

                System.out.println("P\tAdding element: " + element);

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        System.out.println("P Completed.");

        running = false;

    }

}
```

 

 

**ObservingConsumer.java**

```
import java.util.concurrent.LinkedBlockingQueue;

public class ObservingConsumer implements Runnable {

    private LinkedBlockingQueue queue;

    private Producer producer;

    public ObservingConsumer(LinkedBlockingQueue queue, Producer producer) {

        this.queue = queue;

        this.producer = producer;

    }

    @Override

    public void run() {

        // As long as the producer is running,

        // we want to check for elements.

        while (producer.isRunning()) {

            System.out.println("OC\tElements right now: " + queue);

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        System.out.println("OC Completed.");

        System.out.println("Final elements in the queue: " + queue);

    }

}
```

 

 

**RemovingConsumer.java**

```
import java.util.concurrent.LinkedBlockingQueue;

public class RemovingConsumer implements Runnable {

    private LinkedBlockingQueue queue;

    private Producer producer;

    public RemovingConsumer(LinkedBlockingQueue queue, Producer producer) {

        this.queue = queue;

        this.producer = producer;

    }

    @Override

    public void run() {

        // As long as the producer is running,

        // we remove elements from the queue.

        while (producer.isRunning()) {

            try {

                System.out.println("RC\tRemoving element: " + queue.take());

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        System.out.println("RC completed.");

    }

}
```

 

 

### Output

```
P   Adding element: String0

RC  Removing element: String0

OC  Elements right now: []

P   Adding element: String1

P   Adding element: String2

RC  Removing element: String1

OC  Elements right now: [String2]

P   Adding element: String3

P   Adding element: String4

RC  Removing element: String2

OC  Elements right now: [String3, String4]

P   Adding element: String5

RC  Removing element: String3

OC  Elements right now: [String4, String5]

P   Adding element: String6

P   Adding element: String7

RC  Removing element: String4

P   Adding element: String8

OC  Elements right now: [String5, String6, String7, String8]

P   Adding element: String9

RC  Removing element: String5

OC  Elements right now: [String6, String7, String8, String9]

P   Adding element: String10

P   Adding element: String11

RC  Removing element: String6

P   Adding element: String12

OC  Elements right now: [String7, String8, String9, String10, String11, String12]

P   Adding element: String13

RC  Removing element: String7

P   Adding element: String14

OC  Elements right now: [String8, String9, String10, String11, String12, String13, String14]

P Completed.

RC completed.

OC Completed.

Final elements in the queue: [String8, String9, String10, String11, String12, String13, String14]
```

 

 

As you can see, by running 3 threads simultaneously, we took  advantage of the concurrency capabilities  of LinkedBlockingQueue completely. The only thing that we had to do is **keep it track whether or not the Producer thread was still running**, and the rest of the implementation was **thread-safe by default**. By checking the output you can clearly see the effect of every thread,  and the final result (which had less elements than the queue could  actually accomodate, because we were removing the older ones in  intervals).

## 3. Download the example

This was an example of LinkedBlockingQueue.

**Download**
You can download the full source code of this example here : **[LinkedBlockingQueueExample](http://examples.javacodegeeks.com/wp-content/uploads/2014/10/LinkedBlockingQueueExample.rar)**

 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 

출처: http://javapapers.com/java/java-linkedblockingqueue/

 

# Java LinkedBlockingQueue

This Java tutorial is to learn about the collection LinkedBlockingQueue which is an implementation of Java [BlockingQueue](http://javapapers.com/java/java-blockingqueue/). LinkedBlockingQueue order elements first-in-first-out (FIFO). With  respect to bounds of the LinkedBlockingQueue, it stands in between [ArrayBlockingQueue](http://javapapers.com/java/java-arrayblockingqueue/) and [DelayQueue](http://javapapers.com/java/java-delayqueue/).

- ArrayBlockingQueue is a bounded collection.
- DelayQueue is an unbounded collection.
- LinkedBlocingQueue is an optionally bounded collection.

LinkeBlockingQueue has an aditional constructor which provides the  capability to instantiate with fixed capacity. If not the other regular  argument-less constructor creates instance  with Integer.MAX_VALUE capacity.

poll and offer method behaves as defined in BlockingQueue. poll  method without argument retrieves the head element from the queue. poll  method with timeout argument removes the head element by waiting for the time specified if the element is not available.

## LinkedBlocingQueue Example

We will see a standard producer-consumer scenario example to understand the LinkeBlockingQueue.

### LinkedBlockingQueueProducer.java

```
package com.javapapers.java.collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class LinkedBlockingQueueProducer implements Runnable {

	protected BlockingQueue<String> blockingQueue;
	final Random random = new Random();

	public LinkedBlockingQueueProducer(BlockingQueue<String> queue) {
		this.blockingQueue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String data = UUID.randomUUID().toString();
				System.out.println("Put: " + data);
				blockingQueue.put(data);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
```

###  

### LinkedBlockingQueueConsumer.java

```
package com.javapapers.java.collections;

import java.util.concurrent.BlockingQueue;

public class LinkedBlockingQueueConsumer implements Runnable {

	protected BlockingQueue<String> blockingQueue;

	public LinkedBlockingQueueConsumer(BlockingQueue<String> queue) {
		this.blockingQueue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String data = blockingQueue.take();
				System.out.println(Thread.currentThread().getName()
						+ " take(): " + data);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
```

###  

### LinkedBlockingQueueExample.java

```
package com.javapapers.java.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
	public static void main(String[] args) {
		final BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();

		LinkedBlockingQueueProducer queueProducer = new LinkedBlockingQueueProducer(
				linkedBlockingQueue);
		new Thread(queueProducer).start();

		LinkedBlockingQueueConsumer queueConsumer1 = new LinkedBlockingQueueConsumer(
				linkedBlockingQueue);
		new Thread(queueConsumer1).start();

		LinkedBlockingQueueConsumer queueConsumer2 = new LinkedBlockingQueueConsumer(
				linkedBlockingQueue);
		new Thread(queueConsumer2).start();

	}
}
```

###  

### LinkedBlockingQueue Example Output

```
Put: f1ef7505-6fe5-436c-893a-dd6ab8ecdba4
Thread-1 take(): f1ef7505-6fe5-436c-893a-dd6ab8ecdba4
Put: 8c86787c-a7bf-4124-b0c4-c62a195f9096
Thread-2 take(): 8c86787c-a7bf-4124-b0c4-c62a195f9096
Put: c5248f9b-17c0-41f6-a071-fecaa1dd21d3
Thread-1 take(): c5248f9b-17c0-41f6-a071-fecaa1dd21d3
Put: 05e7243e-62d4-4f32-9c9b-4b811245c571
Thread-2 take(): 05e7243e-62d4-4f32-9c9b-4b811245c571
Put: 7848bb81-a0f4-423c-b093-21db9d4412b1
Thread-1 take(): 7848bb81-a0f4-423c-b093-21db9d4412b1
```

This Java tutorial was added on 05/10/2014.