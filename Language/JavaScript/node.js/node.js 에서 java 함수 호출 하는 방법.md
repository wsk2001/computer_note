# node.js 에서 java 함수 호출 하는 방법



Node.js에서 Java 함수를 호출하려면 다음과 같은 단계를 수행해야 합니다.

- 먼저 Java 코드를 작성하여 함수를 정의합니다.
- Node.js 코드에서 Java 함수를 호출할 수 있는 모듈을 설치합니다.
- Node.js 코드에서 Java 함수를 호출합니다.



### Java 코드 작성

Java 코드에서 함수를 정의하는 방법은 다음과 같습니다.

`MyClass.java`

``` java
public class MyClass {

    public static int addNumbers(int a, int b) {
        return a + b;
    }
}
```

이 코드는 `MyClass`라는 클래스에 `addNumbers()`라는 함수를 정의합니다. 이 함수는 두 개의 정수 매개변수를 받고 정수 값을 반환합니다.



### 모듈 설치

Node.js에서 Java 함수를 호출하려면 `Java.js` 모듈을 설치해야 합니다. 이 모듈은 `npm`을 사용하여 설치할 수 있습니다.

``` cmd
npm install java
```



### jar 파일 위치

- Node.js 코드가 독립적으로 실행되는 경우, my-jar-file.jar 파일은 Node.js 코드가 있는 디렉터리에 위치해야 합니다.
- Node.js 코드가 다른 프로세스에서 실행되는 경우, my-jar-file.jar 파일은 해당 프로세스의 CLASSPATH 환경 변수에 지정된 위치에 있어야 합니다.



### Node.js 코드 작성

Node.js 코드에서 Java 함수를 호출하려면 다음과 같은 방법을 사용할 수 있습니다.

`JavaScript`

``` js
const java = require("java");

const myClass = java.import("MyClass");

const result = myClass.addNumbers(10, 20);

console.log(result); // 30

```

이 코드는 `MyClass` 클래스를 `myClass`라는 변수에 저장합니다. 그런 다음 `myClass.addNumbers()` 함수를 호출하여 두 개의 정수를 더합니다. 결과는 `console.log()` 함수를 사용하여 출력됩니다.



### 예제

다음은 Node.js와 Java를 사용하여 간단한 덧셈 계산을 수행하는 예입니다.

`JavaScript`

``` js
const java = require("java");

const myClass = java.import("MyClass");

const result = myClass.addNumbers(10, 20);

console.log("10 + 20 = " + result); // 10 + 20 = 30

```

이 코드는 다음과 같은 출력을 생성합니다.

```
10 + 20 = 30
```





### 비동기 함수 호출

Java 함수를 비동기적으로 호출하려면 `java.async()` 함수를 사용할 수 있습니다. 이 함수는 `Promise` 객체를 반환합니다.

`JavaScript`

``` js
const java = require("java");

const myClass = java.import("MyClass");

const promise = java.async("MyClass", "addNumbers", [10, 20]);

promise.then(result => {
    console.log("10 + 20 = " + result);
});

```

이 코드는 다음과 같은 출력을 생성합니다.

```
10 + 20 = 30
```

