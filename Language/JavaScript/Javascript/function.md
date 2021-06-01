# 함수

### 함수의 기초

------

#### 함수(function)란?

함수(function)란 하나의 특별한 목적의 작업을 수행하도록 설계된 독립적인 블록을 의미합니다.

이러한 함수는 필요할 때마다 호출하여 해당 작업을 반복해서 수행할 수 있습니다.

##### 예제

``` js
function addNum(x, y) {
    return x + y;
}

document.write(addNum(2, 3));
```

>  자바스크립트에서 블록이란 함수나 실행문의 중괄호({})로 묶여진 부분을 가리킵니다.



#### 자바스크립트 함수

자바스크립트에서는 함수도 하나의 타입(datatype)입니다.

 

따라서 함수를 변수에 대입하거나, 함수에 프로퍼티를 지정하는 것도 가능합니다.

또한, 자바스크립트 함수는 다른 함수 내에 중첩되어 정의될 수도 있습니다.

------

#### 함수의 정의

자바스크립트에서 함수의 정의는 function 키워드로 시작되며, 다음과 같은 구성요소를 가집니다.

- 함수의 이름

- 괄호 안에 쉼표(,)로 구분되는 함수의 매개변수(parameter)

- 중괄호({})로 둘러싸인 자바스크립트 실행문

 

자바스크립트에서 함수를 정의하는 문법은 다음과 같습니다.

##### 문법

``` js
function 함수이름(매개변수1, 매개변수2,...) {
    함수가 호출되었을 때 실행하고자 하는 실행문;
}
```

함수 이름(function name)은 함수를 구분하는 식별자(identifier)입니다.

매개변수(parameter)란 함수를 호출할 때 인수(argument)로 전달된 값을 함수 내부에서 사용할 수 있게 해주는 변수입니다.

 

##### 예제

``` js
// addNum라는 이름의 함수를 정의함.
function addNum(x, y) {    // x, y는 이 함수의 매개변수임.
    document.write(x + y);
}

addNum(2, 3);              // addNum() 함수에 인수로 2와 3을 전달하여 호출함.
```

위의 예제에서 매개변수 x에는 인수 2가 저장되고, y에는 인수 3이 저장되어 사용됩니다.

이렇게 인수와 매개변수는 개수뿐만 아니라 순서 또한 매우 중요하게 적용됩니다.

> 함수의 인수(argument)란 함수가 호출될 때 함수로 값을 전달해주는 변수나 상수를 의미합니다.



#### 반환(return)문

자바스크립트에서 함수는 반환(return)문을 포함할 수 있습니다.

이러한 반환문을 통해 호출자는 함수에서 실행된 결과를 전달받을 수 있습니다.

 

반환문은 함수의 실행을 중단하고, return 키워드 다음에 명시된 표현식의 값을 호출자에게 반환합니다.

반환문은 배열이나 객체를 포함한 모든 타입의 값을 반환할 수 있습니다.

##### 예제

``` js
function multiNum(x, y) {
    return x * y;         // x와 y를 곱한 결과를 반환함.
}
var num = multiNum(3, 4); // multiNum() 함수가 호출된 후, 그 반환값이 변수 num에 저장됨.

document.write(num);
```



#### 함수의 호출

정의된 함수는 프로그램 내에서 호출되어야 비로소 실행됩니다.

일반적인 함수의 호출은 함수의 정의문과 같은 형태로 호출할 수 있습니다.

##### 함수의 정의

``` js
function addNum(x, y) {
    return x + y;
}
```

##### 함수의 호출

``` js
var sum = addNum(3, 5); // 함수 addNum()을 호출하면서, 인수로 3과 5를 전달합니다.
                        // 함수의 호출이 끝난 뒤에는 그 반환값을 변수 sum에 대입합니다.
```

위의 예제에서 인수(argument)로 전달된 숫자 3과 5는 함수에서 정의된 매개변수(parameter) x와 y에 각각 대입됩니다.

따라서 호출된 함수의 내부에서는 매개변수 x와 y에 각각 3과 5가 대입되어 계산됩니다.

------

#### 값으로서의 함수

자바스크립트에서 함수는 문법적 구문일뿐만 아니라 값(value)이기도 합니다.

따라서 함수가 변수에 대입될 수도 있으며, 다른 함수의 인수로 전달될 수도 있습니다.

 

다음 예제는 함수를 변수에 저장하여 사용하는 예제입니다.

##### 예제

``` js
function sqr(x) {                // 제곱의 값을 구하는 함수 sqr를 정의함.
    return x * x;
}

var sqrNum = sqr;                // 변수 sqrNum에 함수 sqr을 대입함.
document.write(sqr(4) + "<br>"); // 함수 sqr을 호출함.
document.write(sqrNum(4));       // 변수 sqrNum를 함수처럼 호출함.
```



---

### 변수의 유효 범위

------

#### 변수의 유효 범위(variable scope)

자바스크립트에서 객체나 함수는 모두 변수(variable)입니다.

변수의 유효 범위(scope)란 해당 변수가 접근할 수 있는 변수, 객체 그리고 함수의 집합을 의미합니다.

 

자바스크립트에서 변수는 유효 범위에 따라 다음과 같이 구분됩니다.

- 지역 변수(local variable)

- 전역 변수(global variable)

------

#### 지역 변수(local variable)

지역 변수(local variable)란 함수 내에서 선언된 변수를 가리킵니다.

이러한 지역 변수는 변수가 선언된 함수 내에서만 유효하며, 함수가 종료되면 메모리에서 사라집니다.

함수의 매개변수 또한 함수 내에서 정의되는 지역 변수처럼 동작합니다.

##### 예제

``` js
function localNum() {
    var num = 10; // 지역 변수 num에 숫자 10을 대입함.
    document.write("함수 내부에서 변수 num의 타입은 " + typeof num + "입니다.<br>"); // number
}

localNum();       // 함수 localNum()을 호출함.
document.write("함수의 호출이 끝난 뒤 변수 num의 타입은 " + typeof num + "입니다."); // undefined
```



> 자바스크립트에서는 선언되지 않은 변수를 사용하려고 하거나 접근하려고 하면 오류를 발생시킵니다.
>
> 하지만 선언되지 않은 변수에 대한 typeof 연산자의 결괏값은 undefined 값을 반환합니다.



#### 전역 변수(global variable)

전역 변수(global variable)란 함수의 외부에서 선언된 변수를 가리킵니다.

이러한 전역 변수는 프로그램의 어느 영역에서나 접근할 수 있으며, 웹 페이지가 닫혀야만 메모리에서 사라집니다.

##### 예제

``` js
var num = 10; // 전역 변수 num을 선언함.
function globalNum() {
    document.write("함수 내부에서 변수 num의 값은 " + num + "입니다.<br>"); // 10
    num = 20; // 전역 변수 num의 값을 함수 내부에서 변경함.
}

globalNum();  // 함수 globalNum()을 호출함.
document.write("함수의 호출이 끝난 뒤 변수 num의 값은 " + num + "입니다."); // 20
```



위의 예제처럼 전역 변수는 함수 외부뿐만 아니라 내부에서도 접근하여 변경할 수 있습니다. 

 

자바스크립트에서 지역 변수를 선언할 때에는 반드시 var 키워드를 사용하여 선언해야 합니다.

함수 내부에서 var 키워드를 사용하지 않고 변수를 선언하면, 해당 변수는 지역 변수가 아닌 전역 변수로 선언됩니다.

##### 예제

``` js
function globalNum() {
    num = 10; // var 키워드를 사용하지 않고 변수 num을 선언함.
    document.write("함수 내부에서 변수 num의 값은 " + num + "입니다.<br>"); // 10
}

globalNum();  // 함수 globalNum()을 호출함.
document.write("함수의 호출이 끝난 뒤 변수 num의 값은 " + num + "입니다."); // 10
```



또한, 전역 변수와 같은 이름의 지역 변수를 선언하면, 해당 블록에서는 해당 이름으로 지역 변수만을 호출할 수 있습니다.

##### 예제

``` js
var num = 10; // 전역 변수 num을 선언함.
function globalNum() {
    var num = 20; // 같은 이름의 지역 변수 num을 선언함.
    document.write("함수 내부에서 변수 num의 값은 " + num + "입니다.<br>"); // 20
}

globalNum(); // 함수 globalNum()을 호출함.
document.write("함수의 호출이 끝난 뒤 변수 num의 값은 " + num + "입니다."); // 10
```

위의 예제와 같은 경우 해당 블록에서 전역 변수를 호출하려면, 전역 변수가 window 객체의 프로퍼티임을 명시하면 됩니다.

##### 예제

``` js
var num = 10; // 전역 변수 num을 선언함.
function globalNum() {
    var num = 20; // 같은 이름의 지역 변수 num을 선언함.
    document.write("함수 내부에서 지역 변수 num의 값은 " + num + "입니다.<br>");
    document.write("함수 내부에서 전역 변수 num의 값은 " + window.num + "입니다.<br>");
}

globalNum(); // 함수 globalNum()을 호출함.
```

window 객체에 대한 더 자세한 사항은 자바스크립트 window 객체 수업에서 확인할 수 있습니다.

 [자바스크립트 window 객체 수업 확인 =>](http://tcpschool.com/javascript/js_bom_window)



---

### 함수의 유효 범위

------

#### 함수의 유효 범위(function scope)

대부분의 프로그래밍 언어에서는 블록 내에서 정의된 변수를 블록 외부에서는 접근할 수 없습니다.

블록(block)이란 코드 내에서 중괄호({})로 둘러싸인 부분을 가리킵니다.

이러한 블록을 기준으로 하는 유효 범위를 블록 단위의 유효 범위라고 합니다.

 

하지만 자바스크립트는 다른 언어와는 달리 함수를 블록 대신 사용합니다.

자바스크립트에서 함수는 자신이 정의된 범위 안에서 정의된 모든 변수 및 함수에 접근할 수 있습니다.

 

'전역 함수'는 모든 전역 변수와 전역 함수에 접근할 수 있습니다.

반면, 다른 함수 내에 정의된 '내부 함수'는 그 함수의 부모 함수(parent function)에서 정의된 모든 변수 및 부모 함수가 접근할 수 있는 모든 다른 변수까지도 접근할 수 있습니다.

 

##### 예제

``` js
// x, y, name을 전역 변수로 선언함.
var x = 10, y = 20;

// sub()를 전역 함수로 선언함.
function sub() {
    return x - y;     // 전역 변수인 x, y에 접근함.
}

document.write(sub() + "<br>");

// parentFunc()을 전역 함수로 선언함.
function parentFunc() {
    var x = 1, y = 2; // 전역 변수와 같은 이름으로 선언하여 전역 변수의 범위를 제한함.
    function add() {  // add() 함수는 내부 함수로 선언됨.
        return x + y; // 전역 변수가 아닌 지역 변수 x, y에 접근함.
    }
    return add();
}

document.write(parentFunc());
```



#### 함수 호이스팅(hoisting)

자바스크립트에서 함수의 유효 범위라는 것은 함수 안에서 선언된 모든 변수는 함수 전체에 걸쳐 유효하다는 의미입니다.

 

그런데 이 유효 범위의 적용이 변수가 선언되기 전에도 똑같이 적용됩니다.

이러한 자바스크립트의 특징을 함수 호이스팅(hoisting)이라고 합니다.

즉, 자바스크립트 함수 안에 있는 모든 변수의 선언은 함수의 맨 처음으로 이동된 것처럼 동작합니다.

##### 예제

``` js
var globalNum = 10;     // globalNum을 전역 변수로 선언함.
function printNum() {
    document.write("지역 변수 globalNum 선언 전의 globalNum의 값은 " + globalNum + "입니다.<br>"); // ①
    var globalNum = 20; // globalNum을 지역 변수로 선언함. // ②
    document.write("지역 변수 globalNum 선언 후의 globalNum의 값은 " + globalNum + "입니다.<br>");
}

printNum();
```

위의 예제 ①의 시점에서는 변수 globalNum가 전역 변수를 가리킨다고 생각하기 쉽습니다.

하지만 자바스크립트 내부에서는 함수 호이스팅에 의해 다음과 같이 코드가 변경되어 처리됩니다.

 

##### 호이스팅 후의 코드

``` js
var globalNum = 10;

function printNum() {
    var globalNum; // 함수 호이스팅에 의해 변수의 선언 부분이 함수의 맨 처음 부분으로 이동됨.
    document.write("지역 변수 globalNum 선언 전의 globalNum의 값은 " + globalNum + "입니다.<br>");
    globalNum = 20;
    document.write("지역 변수 globalNum 선언 후의 globalNum의 값은 " + globalNum + "입니다.<br>");
}

printNum();
```

위의 예제 ①의 시점에서는 globalNum라는 지역 변수가 선언만 되어 있고, 아직 초기화만 안 된 상태입니다.

따라서 이때 globalNum 변수에 접근하면 아직 초기화되지 않은 변수에 접근했으므로, undefined 값을 반환하게 됩니다.

실제로 변수가 초기화되는 시점은 원래 코드에서 변수가 선언된 ②의 시점입니다.

> 자바스크립트에서는 함수 호이스팅이 자동으로 수행되지만, 항상 함수 블록의 첫 부분에 변수를 선언하는 것이 좋습니다.



---

### 매개변수와 인수

------

#### 매개변수(parameter)

자바스크립트에서는 함수를 정의할 때는 매개변수의 타입을 따로 명시하지 않습니다.

함수를 호출할 때에도 인수(argument)로 전달된 값에 대해 어떠한 타입 검사도 하지 않습니다.

 

함수를 호출할 때 함수의 정의보다 적은 수의 인수가 전달되더라도, 다른 언어와는 달리 오류를 발생시키지 않습니다.

이 같은 경우 자바스크립트는 전달되지 않은 나머지 매개변수에 자동으로 undefined 값을 설정합니다.

> 매개변수(parameter)란 함수의 정의에서 전달받은 인수를 함수 내부로 전달하기 위해 사용하는 변수를 의미합니다.
>
> 인수(argument)란 함수가 호출될 때 함수로 값을 전달해주는 값을 말합니다.



다음 예제는 3개의 매개변수를 가지는 함수에 각각 다른 수의 인수를 전달하는 예제입니다.

##### 예제

``` js
function addNum(x, y, z) { // x, y, z라는 3개의 매개변수를 가지는 함수 addNum()을 정의함.
    return x + y + z;
}

addNum(1, 2, 3); // 인수로 1, 2, 3을 전달하여 함수를 호출함. -> 6
addNum(1, 2);    // 인수로 1, 2을 전달하여 함수를 호출함. -> NaN
addNum(1);       // 인수로 1을 전달하여 함수를 호출함. -> NaN
addNum();        // 인수로 아무것도 전달하지 않고 함수를 호출함. -> NaN
```



위의 예제에서 addNum() 함수를 호출할 때 인수가 세 개보다 적게 전달되면, 계산할 수 없다는 의미인 NaN을 반환합니다.

그 이유는 전달되지 않은 나머지 값이 자동으로 undefined 값으로 설정되어, 산술 연산을 수행할 수 없기 때문입니다.

 

하지만 다음 예제처럼 하면 NaN을 반환하지 않고 전달된 인수만을 가지고 정상적으로 계산하는 함수를 작성할 수 있습니다.

##### 예제

``` js
function addNum(x, y, z) {
    if(x === undefined) // 함수 호출시 x에 해당하는 인수가 전달되지 않은 경우
        x = 0;          // 변수 x의 값을 undefined에서 0으로 변경함.
    if(y === undefined) // 함수 호출시 y에 해당하는 인수가 전달되지 않은 경우
        y = 0;          // 변수 y의 값을 undefined에서 0으로 변경함.
    if(z === undefined) // 함수 호출시 z에 해당하는 인수가 전달되지 않은 경우
        z = 0;          // 변수 z의 값을 undefined에서 0으로 변경함.
    return x + y + z;
}

addNum(1, 2, 3); // 6
addNum(1, 2);    // 3
addNum(1);       // 1
addNum();        // 0
```



#### arguments 객체

만약 함수의 정의보다 더 많은 수의 인수가 전달되면, 매개변수에 대입되지 못한 인수들은 참조할 방법이 없게 됩니다.

하지만 arguments 객체를 이용하면, 함수로 전달된 인수의 총 개수를 확인하거나, 각각의 인수에도 바로 접근할 수 있습니다.

 

arguments 객체는 함수가 호출될 때 전달된 인수를 배열의 형태로 저장하고 있습니다.

첫 번째 인수는 arguments[0]에 저장되며, 다음 인수는 arguments[1]에 저장됩니다.

또한, 인수의 총 개수는 arguments 객체의 length 프로퍼티에 저장됩니다.

 

다음 예제의 addNum() 함수는 전달받는 인수의 개수에 상관없이 언제나 정상적인 계산을 수행합니다.

##### 예제

``` js
function addNum() {
    var sum = 0;                                // 합을 저장할 변수 sum을 선언함.
    for(var i = 0; i < arguments.length; i++) { // 전달받은 인수의 총 수만큼 반복함.
        sum += arguments[i];                    // 전달받은 각각의 인수를 sum에 더함.
    }
    return sum;
}

addNum(1, 2, 3); // 6
addNum(1, 2);    // 3
addNum(1);       // 1
addNum();        // 0
addNum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // 55
```

> arguments 객체는 배열과 비슷할 뿐, 실제로 Array 객체는 아닙니다.
>
> 숫자로 된 인덱스와 length 프로퍼티만을 가지고 있을 뿐, 모든 것을 배열처럼 다룰 수는 없습니다.



#### 디폴트 매개변수와 나머지 매개변수

ECMAScript 6부터 새롭게 정의된 매개변수는 다음과 같습니다.

- 1. 디폴트 매개변수(default parameter)

- 2. 나머지 매개변수(rest parameter)

------

#### 디폴트 매개변수(default parameter)

디폴트 매개변수란 함수를 호출할 때 명시된 인수를 전달하지 않았을 경우에 사용하게 될 기본값을 의미합니다.

 

자바스크립트에서 매개변수의 기본값은 undefined 값으로 설정되어 있습니다.

##### 예제

``` js
function mul(a, b) {
    // 인수가 한 개만 전달되었을 때 나머지 매개변수의 값을 undefined 값이 아닌 1로 설정함.
    b = (typeof b !== 'undefined')  ? b : 1;
    return a * b;
}

mul(3, 4); // 12
mul(3);    // 3
```

하지만 디폴트 매개변수를 이용하면 이러한 매개변수의 기본값을 바꿀 수 있습니다.

##### 예제

``` js
function mul(a, b = 1) { // 인수가 한 개만 전달되면 나머지 매개변수의 값을 언제나 1로 설정해 줌.
    return a * b;
}

mul(3, 4); // 12
mul(3);    // 3
```

> 디폴트 매개변수는 익스플로러, 사파리, 오페라에서 지원하지 않습니다.



#### 나머지 매개변수(rest parameter)

나머지 매개변수는 생략 접두사(...)를 사용하여 특정 위치의 인수부터 마지막 인수까지를 한 번에 지정할 수 있습니다.

 

다음 예제는 첫 번째 인수에서 두 번째 인수부터 마지막 인수까지를 뺀 후 그 결과를 반환하는 예제입니다.

##### 예제

``` js
function sub() {
    var firstNum = arguments[0];                  // 첫 번째 인수에서
    for(var i = 0; i < arguments.length-1; i++) { // 두 번째부터 마지막 인수까지를
        firstNum -= arguments[i+1];               // 뺌.
    }
    return firstNum;
}

sub(10, 2, 3);    // 10 - 2 - 3 = 5
sub(10, 1, 5, 8); // 10 - 1 - 5 - 8 = -4
```

하지만 나머지 매개변수를 이용하면 sub() 함수를 좀 더 직관적으로 정의할 수 있습니다.

##### 예제

``` js
// 첫 번째 인수를 변수 firstNum에 저장하고 나머지 인수들은 배열 restArgs에 저장함.
function sub(firstNum, ...restArgs) {
    for(var i = 0; i < restArgs.length; i++) {
        firstNum -= restArgs[i];
    }
    return firstNum;
}

sub(10, 2, 3);    // 10 - 2 - 3 = 5
sub(10, 1, 5, 8); // 10 - 1 - 5 - 8 = -4
```

> 나머지 매개변수는 익스플로러, 사파리에서 지원하지 않습니다.

#### 웹 브라우저 호환성

자바스크립트의 디폴트 매개변수와 나머지 매개변수를 지원하는 주요 웹 브라우저의 버전은 다음과 같습니다.

|     매개변수      | ![ie](http://tcpschool.com/img/icon-ie.png) | ![edge](http://tcpschool.com/img/icon-edge.png) | ![chrome](http://tcpschool.com/img/icon-chrome.png) | ![firefox](http://tcpschool.com/img/icon-firefox.png) | ![safari](http://tcpschool.com/img/icon-safari.png) | ![opera](http://tcpschool.com/img/icon-opera.png) |
| :---------------: | :-----------------------------------------: | :---------------------------------------------: | :-------------------------------------------------: | :---------------------------------------------------: | :-------------------------------------------------: | :-----------------------------------------------: |
| default parameter |                지원하지 않음                |                  지원하지 않음                  |                         49                          |                         15.0                          |                    지원하지 않음                    |                   지원하지 않음                   |
|  rest parameter   |                지원하지 않음                |                     지원함                      |                         47                          |                         15.0                          |                    지원하지 않음                    |                        34                         |



---

### 미리 정의된 전역 함수

------

#### 미리 정의된 전역 함수(predefined functions)

자바스크립트는 사용자의 편의를 위해 다양한 기능의 여러 전역 함수를 미리 정의하여 제공합니다.

이러한 전역 함수는 자바스크립트의 어떤 타입의 객체에서도 바로 사용할 수 있습니다.

 

자바스크립트에서 미리 정의되어 있는 전역 함수는 다음과 같습니다.

 

- 1. eval()
- 2. isFinite()
- 3. isNaN()
- 4. parseFloat()
- 5. parseInt()
- 6. decodeURI()
- 7. decodeURIComponent()
- 8. encodeURI()
- 9. encodeURIComponent()
- 10. escape()
- 11. unescape()
- 12. Number()
- 13. String()

------

#### eval()

eval() 함수는 문자열로 표현된 자바스크립트 코드를 실행하는 함수입니다.

##### 문법

``` js
eval("문자열");
```

##### 예제

``` js
var x = 10, y = 20;
var a = eval("x + y"); // 30
var b = eval("y * 3"); // 60
document.write(a + "<br>" + b);
```



#### isFinite()

isFinite() 함수는 전달된 값이 유한한 수인지를 검사하여 그 결과를 반환합니다.

만약 인수로 전달된 값이 숫자가 아니라면, 숫자로 변환하여 검사합니다.

##### 문법

``` js
isFinite(검사할값);
```

##### 예제

``` js
isFinite(123);       // true
isFinite(123e100);   // true
isFinite(0);         // true
isFinite(true);      // true
isFinite(false);     // true
isFinite(null);      // true
isFinite("123");     // true
isFinite("");        // true
 
isFinite("문자열");  // false
isFinite(undefined); // false
isFinite(NaN);       // false
```



#### isNaN()

isNaN() 함수는 전달된 값이 NaN인지를 검사하여 그 결과를 반환합니다.

만약 인수로 전달된 값이 숫자가 아니라면, 숫자로 강제 변환하여 검사합니다.

 

전달된 값이 숫자인지 아닌지를 확인하기 위하여 typeof 연산자를 대신 사용할 수도 있습니다.

##### 문법

``` js
isNaN(검사할값);
```

##### 예제

``` js
isNaN(123);       // false
isNaN(123e100);   // false
isNaN(0);         // false
isNaN(true);      // false
isNaN(false);     // false
isNaN(null);      // false
isNaN("123");     // false
isNaN("");        // false
 
isNaN("문자열");  // true
isNaN(undefined); // true
isNaN(NaN);       // true
```

> 이 함수는 숫자로의 강제 변환에 따라 예상치 못한 결과를 얻을 수 있으므로 ECMAScript 6부터는 Number.isNaN() 메소드의 사용을 권장하고 있습니다.



#### parseFloat()

parseFloat() 함수는 문자열을 파싱하여 부동 소수점 수(floating point number)로 반환합니다.

##### 문법

``` js
parseFloat("문자열");
```

##### 예제

``` js
parseFloat("123");        // 123
parseFloat("123.000");    // 123
parseFloat("123.456");    // 123.456
parseFloat("12 34 56");   // 12
parseFloat(" 123 ");      // 123
parseFloat("123 초콜릿"); // 123
parseFloat("초콜릿 123"); // NaN
```



#### parseInt()

parseInt() 함수는 문자열을 파싱하여 정수로 반환합니다.

##### 문법

``` js
parseInt("문자열");
```

##### 예제

``` js
parseInt("123");        // 123
parseInt("123.000");    // 123
parseInt("123.456");    // 123
parseInt("12 34 56");   // 12
parseInt(" 123 ");      // 123
parseInt("123 초콜릿"); // 123
parseInt("초콜릿 123"); // NaN
 
parseInt("10", 10);     // 10
parseInt("10", 8);      // 8
parseInt("10", 16);     // 16
parseInt("0x10");       // 16
```

위의 예제처럼 parseInt() 함수에 두 번째 인수로 특정 진법을 전달하면, 해당 진법에 맞는 정수로 반환합니다.

또한, 전달받은 문자열의 시작이 "0x"로 시작하면, parseInt() 함수는 해당 문자열을 16진수로 인식합니다.

> 지금까지 parseInt() 함수는 전달받은 문자열의 시작이 "0"로 시작하면, 해당 문자열을 8진수로 인식했습니다.
> 하지만 자바스크립트는 더 이상 이 문법을 지원하지 않습니다.



#### encodeURI()와 encodeURIComponent()

encodeURI() 함수는 URI에서 주소를 표시하는 특수문자를 제외하고, 모든 문자를 이스케이프 시퀀스(escape sequences) 처리하여 부호화합니다.

하지만 encodeURIComponent() 함수는 URI에서 encodeURI() 함수에서 부호화하지 않은 모든 문자까지 포함하여 이스케이프 시퀀스 처리합니다.

##### 문법

``` js
encodeURI(부호화할URI);
encodeURIComponent(부호화할URI);
```

##### 예제

``` js
var uri = "http://google.com/search.php?name=홍길동&city=서울";
 
var enc1 = encodeURI(uri);
var enc2 = encodeURIComponent(uri);
document.write(enc1 + "<br>" + enc2);
```



#### decodeURI()와 decodeURIComponent()

decodeURI() 함수는 encodeURI() 함수나 다른 방법으로 만들어진 URI(Uniform Resource Identifier)를 해독합니다.

decodeURIComponent() 함수는 encodeURIComponent() 함수나 다른 방법으로 만들어진 URI 컴포넌트를 해독합니다.

##### 문법

``` js
decodeURI(해독할URI);
decodeURIComponent(해독할URI);
```

##### 예제

``` js
var uri = "http://google.com/search.php?name=홍길동&city=서울";
 
var enc1 = encodeURI(uri);
var enc2 = encodeURIComponent(uri);
document.write(enc1 + "<br>" + enc2 + "<br><br>");
 
var dec1 = decodeURI(enc1);
var dec2 = decodeURIComponent(enc2);
document.write(dec1 + "<br>" + dec2);
```



#### escape()와 unescape()

escape() 함수는 전달받은 문자열에서 특정 문자들을 16진법 이스케이프 시퀀스 문자로 변환합니다.

unescape() 함수는 전달받은 문자열에서 escape() 함수나 다른 방법으로 만들어진 16진법 이스케이프 시퀀스 문자를 원래의 문자로 변환합니다.

##### 문법

``` js
escape("변환할문자열");
unescape("원래대로변환할문자열");
```

##### 예제

``` js
var str = "Hello! World ?#$";
 
var esc = escape(str);
var une = unescape(esc);
document.write(esc + "<br>" + une);
```

> escape() 함수는 자바스크립트 1.5버전부터 더는 지원하지 않으므로, encodeURI() 함수나
> encodeURIComponent() 함수를 대신 사용해야 합니다.

> unescape() 함수는 자바스크립트 1.5버전부터 더는 지원하지 않으므로, decodeURI() 함수나
> decodeURIComponent() 함수를 대신 사용해야 합니다.



#### Number()

Number() 함수는 전달받은 객체의 값을 숫자로 반환합니다.

##### 문법

``` js
Number(객체);
```

##### 예제

``` js
Number("123");        // 123
Number("123.000");    // 123
Number("123.456");    // 123.456
Number("12 34 56");   // NaN
Number("123 초콜릿"); // NaN
 
Number(true);         // 1
Number(false);        // 0
Number(new Date());   // 현재 날짜에 해당하는 숫자를 반환함.
Number(null);         // 0
```



#### String()

String() 함수는 전달받은 객체의 값을 문자열로 반환합니다.

##### 문법

``` js
String(객체);
```

##### 예제

```js
String(123);        // 123
String(123.456);    // 123.456
String("123");      // 123
String(new Date()); // 현재 날짜에 해당하는 문자열을 반환함.
String(null);       // null
 
String(true);       // true
String(false);      // false
String(Boolean(1)); // true
String(Boolean(0)); // false
```

