# 예외 처리

### 예외 처리

------

#### 예외(exception)

예외(exception)란 프로그램이 실행 중에 발생하는 런타임 오류를 의미합니다.

이러한 예외가 발생하지 않도록 미리 방지하는 것도 중요하지만, 발생한 예외를 처리하는 방법 또한 매우 중요합니다.

> 오류(error)란 프로그램 구문의 문법적인 오류를 의미합니다.



#### 예외 발생

예외를 발생시킨다는 것은 명시적으로 오류를 발생시킨다는 의미뿐만 아니라 예외 상황을 알린다는 의미도 있습니다. 

자바스크립트에서는 throw 키워드를 사용하여 예외를 발생시킬 수 있습니다.

##### 문법

``` JS
throw 표현식;
```

표현식에는 예외 코드를 나타내는 숫자나 오류 메시지를 담고 있는 문자열, Error 객체 등이 올 수 있습니다.

------

#### 예외 처리(exception handling)

자바스크립트에서는 프로그램이 실행되는 도중 발생하는 예외를 처리하기 위해 try / catch / finally 문을 사용합니다.

##### 문법

```js
try {
    예외를 처리하길 원하는 실행 코드;
} catch (ex) {
    예외가 발생할 경우에 실행될 코드;
} finally {
    try 블록이 종료되면 무조건 실행될 코드;
}
```

- 1. try 블록 : 기본적으로 맨 먼저 실행되는 코드이며, 여기에서 발생한 예외는 catch 블록에서 처리될 수 있습니다.

- 2. catch 블록 : try 블록에서 발생한 예외 코드나 Error 객체를 인수로 전달받아 그 처리를 담당합니다.

- 3. finally 블록 : 이 블록은 try 블록에서 예외가 발생하건 안 하건 맨 마지막에 무조건 실행됩니다.

catch 블록과 finally 블록은 선택적인 옵션으로 반드시 사용할 필요는 없습니다.

따라서 사용할 수 있는 모든 적합한 try 구문은 다음과 같습니다.

##### 적합한 try 구문

``` js
1. try / catch
2. try / finally
3. try / catch / finally
```



#### Error 객체

자바스크립트에서는 런타임 오류가 발생할 때마다 Error 객체의 인스턴스가 만들어져 해당 오류의 정보를 저장합니다.

##### 예제

``` js
var btn = document.getElementById("throwExBtn");      // 아이디가 "throwExBtn"인 요소를 선택함.
btn.addEventListener("click", throwEx);               // 선택한 요소에 click 이벤트 리스너를 등록함.
function throwEx() {
    try {
        throw new Error("직접 발생시킨 오류입니다!"); // Error 객체를 사용해 명시적으로 오류를 발생시킴.
    } catch (ex) { // 발생된 오류를 매개변수 ex로 접근할 수 있음.
        document.getElementById("text").innerHTML = ex.name + "<br>"; // name 프로퍼티는 오류의 타입을 저장함.
        document.getElementById("text").innerHTML += ex.message;   // message 프로퍼티는 오류 메시지를 저장함.
    }
}
```



### Strict 모드

------

#### strict 모드란?

ECMAScript 5에서 처음으로 소개된 strict 모드는 자바스크립트 코드에 더욱 엄격한 오류 검사를 적용해 줍니다.

 

strict 모드는 스크립트나 함수의 맨 처음에 "use strict" 지시어를 사용하여 선언할 수 있습니다.

##### 예제

``` js
"use strict"    // 전체 스크립트를 strict 모드로 설정함.

try {
    num = 3.14; // 선언되지 않은 변수를 사용했기 때문에 오류를 발생시킴.
} catch (ex) {
    document.getElementById("text").innerHTML = ex.name + "<br>";
    document.getElementById("text").innerHTML += ex.message;
}
```

이렇게 선언된 strict 모드는 해당 블록의 코드를 strict 모드의 문법에 따라 엄격하게 검사합니다.

##### 예제

``` js
str = "실수!";    // 선언되지 않은 변수를 사용했지만 자동으로 전역 변수로 선언됨.
document.getElementById("noStrict").innerHTML = str + "<br>";
function StrictBlock() {
    "use strict"  // 함수 블록만을 strict 모드로 설정함.
    try {
        num = 123 // 선언되지 않은 변수를 사용했기 때문에 오류를 발생시킴.
    } catch (ex) {
        document.getElementById("funcStrict").innerHTML = ex.name + "<br>";
        document.getElementById("funcStrict").innerHTML += ex.message;
    }
}
StrictBlock();
```



위의 예제에서 strict 모드가 아닌 전역 영역에서는 선언되지 않은 변수를 사용해도 자동으로 전역 변수로 인식합니다.

하지만 strict 모드로 선언된 함수 블록에서는 선언되지 않은 변수를 사용하면 오류를 발생시킵니다.

 

strict 모드를 지원하는 주요 웹 브라우저의 버전은 다음과 같습니다.

|   지시어   | ![ie](http://tcpschool.com/img/icon-ie.png) | ![chrome](http://tcpschool.com/img/icon-chrome.png) | ![firefox](http://tcpschool.com/img/icon-firefox.png) | ![safari](http://tcpschool.com/img/icon-safari.png) | ![opera](http://tcpschool.com/img/icon-opera.png) |
| :--------: | :-----------------------------------------: | :-------------------------------------------------: | :---------------------------------------------------: | :-------------------------------------------------: | :-----------------------------------------------: |
| use strict |                    10.0                     |                        13.0                         |                          4.0                          |                         5.1                         |                       12.0                        |

------

#### strict 모드의 특징

자바스크립트의 strict 모드는 기존 자바스크립트 언어의 일부 기능을 제한한 문법을 사용합니다.

또한, 몇 가지 중요한 기능을 수정하여 강력한 오류 검사와 함께 향상된 보안 기능을 제공합니다.

 

기존 자바스크립트의 문법과 다른 strict 모드의 문법은 다음과 같습니다.

|                           대상                            |                          제한 사항                           |
| :-------------------------------------------------------: | :----------------------------------------------------------: |
|                           변수                            |         선언되지 않은 변수나 객체를 사용할 수 없음.          |
| eval() 함수 내에서 선언된 변수는 외부에서 사용할 수 없음. |                                                              |
|                         프로퍼티                          |            읽기 전용 프로퍼티에는 대입할 수 없음.            |
|           한 프로퍼티를 여러 번 정의할 수 없음.           |                                                              |
|                           함수                            |         함수를 구문이나 블록 내에서 선언할 수 없음.          |
|                         매개변수                          |             매개변수의 이름이 중복되어서는 안됨.             |
|        arguments 객체의 요소 값을 변경할 수 없음.         |                                                              |
|                          문자열                           |        문자열 "eval"과 "arguments"는 사용할 수 없음.         |
|                           8진수                           |           숫자 리터럴에 8진수 값을 대입할 수 없음.           |
|                           this                            | this 포인터가 가르키는 값이 null이나 undefined인 경우 전역 객체로 변환되지 않음. |
|                          delete                           |               delete 키워드를 사용할 수 없음.                |
|                           with                            |                  with 문을 사용할 수 없음.                   |
|                          예약어                           | 다음 예약어들은 사용할 수 없음.(implements, interface, let, package, private, protected, public, static, yield) |