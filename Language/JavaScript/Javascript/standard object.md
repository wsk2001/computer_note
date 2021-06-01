# 표준 객체

### 전역 객체와 래퍼 객체

------

#### 전역 객체(global object)

전역 객체란 자바스크립트에 미리 정의된 객체로 전역 프로퍼티나 전역 함수를 담는 공간의 역할을 합니다.

전역 객체 그 자체는 전역 범위(global scope)에서 this 연산자를 통해 접근할 수 있습니다.

 

자바스크립트에서 모든 객체는 전역 객체의 프로퍼티가 됩니다.

웹 브라우저가 새로운 페이지를 로드(load)하면, 자바스크립트는 새로운 전역 개체를 만들고 해당 프로퍼티들을 초기화합니다.

------

#### 래퍼 객체(wrapper object)

##### 예제

``` js
var str = "문자열";   // 문자열 생성
var len = str.length; // 문자열 프로퍼티인 length 사용
```

위의 예제에서 생성한 문자열 리터럴 str은 객체가 아닌데도 length 프로퍼티를 사용할 수 있습니다.

프로그램이 문자열 리터럴 str의 프로퍼티를 참조하려고 하면, 자바스크립트는 new String(str)을 호출한 것처럼 문자열 리터럴을 객체로 자동 변환해주기 때문입니다.

 이렇게 생성된 임시 객체는 String 객체의 메소드를 상속받아 프로퍼티를 참조하는 데 사용됩니다.

이후 프로퍼티의 참조가 끝나면 사용된 임시 객체는 자동으로 삭제됩니다.

이렇게 숫자, 문자열, 불리언 등 원시 타입의 프로퍼티에 접근하려고 할 때 생성되는 임시 객체를 래퍼 객체(wrapper object)라고 합니다.

 ##### 예제

``` js
var str = "문자열";           // 문자열 리터럴 생성
var strObj = new String(str); // 문자열 객체 생성
str.length;                   // 리터럴 값은 내부적으로 래퍼 객체를 생성한 후에 length 프로퍼티를 참조함.
str == strObj;                // 동등 연산자는 리터럴 값과 해당 래퍼 객체를 동일하게 봄.
str === strObj;               // 일치 연산자는 리터럴 값과 해당 래퍼 객체를 구별함.
typeof str;                   // string 타입
typeof strObj;                // object 타입
```



#### 표준 객체(Standard Object)

자바스크립트에서 표준 객체(standard object)는 다른 객체의 기초가 되는 핵심적인 객체입니다.

 자주 사용되는 대표적인 자바스크립트 표준 객체는 다음과 같습니다.

- 1. Number 객체

- 2. Math 객체

- 3. Date 객체

- 4. String 객체

- 5. Array 객체



---

### Number 객체

------

#### 자바스크립트에서의 수 표현

자바스크립트에서는 정수와 실수를 따로 구분하지 않고, 모든 수를 실수 하나로만 표현합니다.

자바스크립트에서 모든 숫자는 IEEE 754 국제 표준에서 정의한 64비트 부동 소수점 수로 저장됩니다.

 64비트 부동 소수점 수(double precision floating point numbers)는 메모리에 다음과 같은 형태로 저장됩니다.

|      0 ~ 51 비트      |     52 ~ 62 비트      |       63 비트        |
| :-------------------: | :-------------------: | :------------------: |
| 총 52비트의 가수 부분 | 총 11비트의 지수 부분 | 총 1비트의 부호 부분 |

 이러한 64비트 부동 소수점 수의 정밀도는 정수부는 15자리까지, 소수부는 17자리까지만 유효합니다.

 다음 예제는 64비트 부동 소수점 수의 정밀도를 알아보는 예제입니다.

##### 예제

``` js
var x = 999999999999999;  // 15자리의 정수부
var y = 9999999999999999; // 16자리의 정수부
var z = 0.1 + 0.2
x; // 999999999999999
y; // 10000000000000000
z; // 0.30000000000000004
```

위의 예제에서 변수 z의 값을 살펴보면 오차가 발생했음을 알 수 있습니다.

이렇게 부동 소수점 수를 가지고 수행하는 산술 연산의 결괏값은 언제나 오차가 발생할 가능성을 가지고 있습니다.

이것은 자바스크립트만의 문제가 아닌 부동 소수점 수를 가지고 실수를 표현하는 모든 프로그래밍 언어에서의 문제점입니다.

 

자바스크립트에서는 이러한 오차를 없애기 위해 정수의 형태로 먼저 변환하여 계산을 수행하고, 다시 실수의 형태로 재변환하는 방법을 사용할 수도 있습니다.

##### 예제

```js
var z = (0.2 * 10 + 0.1 * 10) / 10; // 0.3
```



#### 진법 표현

자바스크립트에서는 기본적으로 10진법을 사용하여 수를 표현합니다.

하지만 0x 접두사를 사용하여 16진법으로 수를 표현할 수도 있습니다.

##### 예제

``` js
var x = 0xAB; // 16진법으로 표현된 10진수 171
var y = 29;   // 10진법으로 표현된 10진수 29
x + y;        // 두 수 모두 10진법으로 자동으로 변환되어 계산됨. -> 200
```



위의 예제처럼 자바스크립트에서는 산술 연산 시 모든 수가 10진수로 자동 변환되어 계산됩니다.

 또한, 숫자에 toString() 메소드를 사용하여 해당 숫자를 여러 진법의 형태로 변환할 수 있습니다.

##### 예제

``` js
var num = 256;
num.toString(2);       //  2진법으로 변환 : 100000000
num.toString(8);       //  8진법으로 변환 : 400
num.toString(10);      // 10진법으로 변환 : 256
num.toString(16);      // 16진법으로 변환 : 100

// 2진수로 변환한 결괏값을 문자열로 반환함.
num.toString(2);       // 100000000

// 문자열을 숫자로 나눴기 때문에 자동으로 10진수로 변환되어 산술 연산된 결괏값
(num.toString(2) / 2); // 50000000
```

toString() 메소드는 해당 숫자의 진법을 실제로 바꿔주는 것이 아닌, 전달된 진법으로 변환된 형태의 문자열을 반환해 주는 것입니다.



------

#### Infinity

자바스크립트에서는 양의 무한대를 의미하는 Infinity 값과 음의 무한대를 의미하는 -Infinity 값을 사용할 수 있습니다.

Infinity 값은 사용자가 임의로 수정할 수 없는 읽기 전용 값이며, 자바스크립트의 어떤 수보다도 큰 수로 취급됩니다.

##### 예제

``` js
var x = 10 / 0;         // 숫자를 0으로 나누면 Infinity를 반환함.
var y = Infinity * 1000 // Infinity에 어떠한 수를 산술 연산해도 Infinity를 반환함.
var z = 1 / Infinity    // Infinity의 역수는 0을 반환함.

x;                      // Infinity
y;                      // Infinity
z;                      // 0
```



#### NaN

NaN(Not A Number)는 숫자가 아니라는 의미로, 정의되지 않은 값이나 표현할 수 없는 값을 가리킵니다.

0을 0으로 나누거나, 숫자로 변환할 수 없는 피연산자로 산술 연산을 시도하는 경우에 반환되는 읽기 전용 값입니다.

##### 예제

```js
var x = 100 - "10";     // "10"은 자동으로 숫자로 변환되어 계산됨.
var y = 100 - "문자열"; // "문자열"은 숫자로 변환할 수 없기 때문에 NaN을 반환함.
var z = 0 / 0;          // 0을 0으로 나눌 수 없기 때문에 NaN을 반환함.

x;                      // 90
y;                      // NaN
z;                      // NaN
```



자바스크립트의 전역 함수 중 하나인 isNaN() 함수를 사용하면, 전달받은 값이 숫자인지 아닌지를 판단해 줍니다.

##### 예제

``` js
var x = 100 * "문자열";
if(isNaN(x)) { // 전달된 값이 숫자인지 아닌지를 검사함.
    document.write("변수 x의 값은 숫자가 아닙니다.");
} else {
    document.write("변수 x의 값은 숫자입니다.");
}
```



#### null, undefined, NaN, Infinity에 대한 비교

자바스크립트에서는 약간은 비슷한 것 같으면서도 전혀 다른 4가지 값을 제공하고 있습니다.

 

\- null은 object 타입이며, 아직 '값'이 정해지지 않은 것을 의미하는 값입니다.

\- undefined는 null과는 달리 하나의 타입이며, '타입'이 정해지지 않은 것을 의미하는 값이기도 합니다.

\- NaN은 number 타입이며, '숫자가 아님'을 의미하는 숫자입니다.

\- Infinity는 number 타입이며, '무한대'를 의미하는 숫자입니다.

 

자바스크립트는 타입 검사가 매우 유연한 언어입니다.

따라서 위의 값들 또한 문맥에 따라 다음과 같이 자동으로 타입 변환이 이루어집니다.

|    값     | Boolean 문맥 | Number 문맥 | String 문맥 |
| :-------: | :----------: | :---------: | :---------: |
|   null    |    false     |      0      |   "null"    |
| undefined |    false     |     NaN     | "undefined" |
|    NaN    |    false     |     NaN     |    "NaN"    |
| Infinity  |     true     |  Infinity   | "Infinity"  |

 

##### 예제

``` js
typeof null;        // object
typeof undefined;   // undefined
typeof NaN;         // number
typeof Infinity;    // number
 
Boolean(null);      // false
Boolean(undefined); // false
Boolean(NaN);       // false
Boolean(Infinity);  // true
 
Number(null);       // 0
Number(undefined);  // NaN
Number(NaN);        // NaN
Number(Infinity);   // Infinity
 
String(null);       // null
String(undefined);  // undefined
String(NaN);        // NaN
String(Infinity);   // Infinity
```



#### Number 객체

자바스크립트에서 숫자는 보통 숫자 리터럴을 사용하여 표현합니다.

 

하지만 수를 나타낼 때 new 연산자를 사용하여 명시적으로 Number 객체를 생성할 수도 있습니다.

이러한 Number 객체는 숫자 값을 감싸고 있는 래퍼(wrapper) 객체입니다.

##### 예제

``` js
var x = 100;             // 숫자 리터럴
var y = new Number(100); // Number 객체

x;                       // 100
y;                       // 100
typeof x;                // number 타입
typeof y;                // object 타입
```



동등 연산자(==)는 리터럴 값과 객체의 값이 같으면 true를 반환합니다.

하지만 일치 연산자(===)는 숫자 리터럴과 Number 객체의 타입이 다르므로, 언제나 false를 반환합니다.

##### 예제

``` js
var x = 100;             // 숫자 리터럴 100
var y = new Number(100); // Number 객체 100

x == y;                  // 값이 같으므로 true
x === y;                 // 서로 다른 객체이므로 false
```

> new 연산자를 사용하여 객체를 생성할 때에는 매우 많은 추가 연산을 해야만 합니다.
>
> 따라서 가능한 숫자 리터럴을 사용하여 수를 표현하고, Number 객체는 래퍼 객체로만 활용하는 것이 좋습니다.



---

### Number 메소드

------

#### Number 메소드

Number 메소드는 Number 객체에 정의되어 있는 숫자와 관련된 작업을 할 때 사용하는 메소드입니다.

가장 많이 사용되는 대표적인 Number 메소드는 다음과 같습니다.

- 1. Number.parseFloat()

- 2. Number.parseInt()

- 3. Number.isNaN()

- 4. Number.isFinite()

- 5. Number.isInteger()

- 6. Number.isSafeInteger()

------

#### Number.parseFloat() 메소드

Number.parseFloat() 메소드는 문자열을 파싱(parsing)하여, 문자열에 포함된 숫자 부분을 실수 형태로 반환합니다.문자열에 여러 개의 숫자가 존재하면, 그중에서 첫 번째 숫자만을 실수 형태로 반환합니다.

 

이 메소드는 전역 함수인 parseFloat() 함수와 완전히 같은 동작을 수행합니다.

##### 예제

``` js
Number.parseFloat("12");         // 12
Number.parseFloat("12.34");      // 12.34
Number.parseFloat("12문자열");   // 12
Number.parseFloat("12 34 56");   // 12
Number.parseFloat("문자열 56")); // NaN
```

> 이 메소드는 사파리와 익스플로러에서 지원하지 않습니다.



#### Number.parseInt() 메소드

Number.parseInt() 메소드는 문자열을 파싱하여, 문자열에 포함된 숫자 부분을 정수 형태로 반환합니다.

문자열에 여러 개의 숫자가 존재하면, 그중에서 첫 번째 숫자만을 정수 형태로 반환합니다.

 

이 메소드는 전역 함수인 parseInt() 함수와 완전히 같은 동작을 수행합니다.

##### 예제

``` js
Number.parseInt("12");         // 12
Number.parseInt("12.34");      // 12
Number.parseInt("12문자열");   // 12
Number.parseInt("12 34 56");   // 12
Number.parseInt("문자열 56")); // NaN
```

> 이 메소드는 사파리와 익스플로러에서 지원하지 않습니다.



#### Number.isNaN() 메소드

Number.isNaN() 메소드는 전달된 값이 NaN인지 아닌지를 검사합니다.

 

이 메소드는 전역 함수인 isNaN() 함수가 가지고 있던 숫자로의 강제 변환에 따라 발생하는 문제를 더는 겪지 않게 해줍니다.

이 메소드는 오직 숫자인 값에서만 동작하며, 그 값이 NaN인 경우에만 true를 반환합니다.

##### 예제

``` js
Number.isNaN(NaN);       // true
Number.isNaN(0 / 0);     // true

// 다음은 전역 함수인 isNaN()에서 잘못된 결과를 반환하는 예제임.
isNaN("NaN");            // true
isNaN(undefined);        // true
isNaN("문자열");         // true

// Number.isNaN() 메소드에서 맞는 결과를 반환하고 있음.
Number.isNaN("NaN");     // false
Number.isNaN(undefined); // false
Number.isNaN("문자열");  // false
```

> 이 메소드는 익스플로러에서 지원하지 않습니다.



#### Number.isFinite() 메소드

Number.isFinite() 메소드는 전달된 값이 유한한 수인지 아닌지를 검사합니다.

 

이 메소드는 전역 함수인 isFinite() 함수처럼 전달된 값을 숫자로 강제 변환하지 않습니다.

이 메소드는 오직 셀 수 있는 값에서만 동작하며, 그 값이 유한한 경우에만 true를 반환합니다.

##### 예제

``` js
Number.isFinite(0);        // true
Number.isFinite(3e45);     // true
Number.isFinite(Infinity); // false
Number.isFinite(NaN);      // false

// 다음은 전역 함수인 isFinite()에서 잘못된 결과를 반환하는 예제임.
isFinite("0");             // true
isFinite(null);            // true

// Number.isFinite() 메소드에서는 맞는 결과를 반환하고 있음.
Number.isFinite("0");      // false
Number.isFinite(null);     // false
```

> 이 메소드는 익스플로러에서 지원하지 않습니다.



#### Number.isInteger() 메소드

Number.isInteger() 메소드는 전달된 값이 정수인지 아닌지를 검사합니다.

전달된 값이 정수이면 true를 반환하며, 정수가 아니거나 NaN, Infinity와 같은 값은 모두 false를 반환합니다.

##### 예제

``` js
Number.isInteger(0);        // true
Number.isInteger(-100);     // true
Number.isInteger(0.1);      // false
Number.isInteger("문자열"); // false
Number.isInteger(Infinity); // false
Number.isInteger(true);     // false
```

> 이 메소드는 사파리와 익스플로러에서 지원하지 않습니다.



#### Number.isSafeInteger() 메소드

Number.isSafeInteger() 메소드는 전달된 값이 안전한 정수(safe integer)인지 아닌지를 검사합니다.

 

안전한 정수(safe integer)란 IEEE 754 국제 표준에서 정의한 64비트 부동 소수점 수로 정확히 표현되는 정수를 의미합니다.

-(2<sup>53</sup> - 1)부터 (2<sup>53</sup> - 1)까지의 모든 정수가 안전한 정수에 포함됩니다.

##### 예제

``` js
Number.isSafeInteger(10);                  // true
Number.isSafeInteger(Math.pow(2, 53) - 1); // true
Number.isSafeInteger(Math.pow(2, 53));     // false
Number.isSafeInteger(Infinity);            // false
Number.isSafeInteger(NaN);                 // false
Number.isSafeInteger(3.14);                // false
```

> Math.pow()는 거듭제곱 연산을 수행하는 Math 객체의 메소드입니다.
> 예를 들어 Math.pow(2, 53)은 2<sup>53</sup>을 반환합니다.

> 이 메소드는 사파리와 익스플로러에서 지원하지 않습니다.



#### 자바스크립트 Number 메소드

|         메소드         |                             설명                             |
| :--------------------: | :----------------------------------------------------------: |
|  Number.parseFloat()   | 문자열을 파싱하여, 문자열에 포함된 숫자 부분을 실수 형태로 반환함. |
|   Number.parseInt()    | 문자열을 파싱하여, 문자열에 포함된 숫자 부분을 정수 형태로 반환함. |
|     Number.isNaN()     |             전달된 값이 NaN인지 아닌지를 검사함.             |
|   Number.isFinite()    |          전달된 값이 유한한 수인지 아닌지를 검사함.          |
|   Number.isInteger()   |            전달된 값이 정수인지 아닌지를 검사함.             |
| Number.isSafeInteger() |  전달된 값이 안전한 정수(safe integer)인지 아닌지를 검사함.  |

------

#### Number.prototype 메소드

모든 Number 인스턴스는 Number.prototype으로부터 메소드와 프로퍼티를 상속받습니다.

가장 많이 사용되는 대표적인 Number.prototype 메소드는 다음과 같습니다.

- 1. Number.prototype.toExponential()

- 2. Number.prototype.toFixed()

- 3. Number.prototype.toPrecision()

- 4. Number.prototype.toString()

- 5. Number.prototype.valueOf()

------

#### toExponential() 메소드

이 메소드는 Number 인스턴스의 값을 지수 표기법으로 변환한 후, 그 값을 문자열로 반환합니다.

이때 전달받은 값은 지수 표기법에서 소수 부분의 자릿수로 사용됩니다.

##### 원형

``` js
numObj.toExponential([소수부분의자릿수])
```

##### 예제

``` js
var num = 12.3456;       // Number 인스턴스를 생성함.
num.toExponential();     // 1.23456e+1
num.toExponential(2);    // 1.23e+1
num.toExponential(4);    // 1.2346e+1
12.3456.toExponential(); // 1.23456e+1
```



#### toFixed() 메소드

이 메소드는 Number 인스턴스의 소수 부분 자릿수를 전달받은 값으로 고정한 후, 그 값을 문자열로 반환합니다.

##### 원형

``` js
numObj.toFixed([소수부분의자릿수])
```

##### 예제

``` js
var num = 3.14159265;  // Number 인스턴스를 생성함.
num.toFixed();         // 3
num.toFixed(2);        // 3.14
num.toFixed(4);        // 3.1416
3.14159265.toFixed(6); // 3.141593
```



#### toPrecision() 메소드

이 메소드는 Number 인스턴스의 가수와 소수 부분을 합친 자릿수를 전달받은 값으로 고정한 후, 그 값을 문자열로 반환합니다.

##### 원형

``` js
numObj.toPrecision([전체자릿수])
```



##### 예제

``` js
var num = 3.14159265;      // Number 인스턴스를 생성함.
num.toPrecision();         // 3.14159265
num.toPrecision(2);        // 3.1
num.toPrecision(4);        // 3.142
3.14159265.toPrecision(6); // 3.14159
```



#### toString() 메소드

이 메소드는 Number 인스턴스의 값을 문자열로 반환합니다.

전달받은 값에 해당하는 진법으로 우선 값을 변환한 후, 그 값을 문자열로 반환합니다.

##### 원형

``` js
numObj.toString([진법])
```

##### 예제

``` js
var num = 255;       // Number 인스턴스를 생성함.
num.toString();      // 255
(255).toString();    // 255
(3.14).toString();   // 3.14
num.toString(2);     // 11111111
(100).toString(16);  // 64
(-0xff).toString(2); // -11111111
```

> 숫자 리터럴에 toString() 메소드를 사용할 때에는 반드시 괄호(())를 사용하여 숫자 리터럴을 감싸줘야 합니다.
> 그렇지 않으면 자바스크립트는 SyntaxError를 발생한 후, 프로그램을 중지시킬 것입니다.



#### valueOf() 메소드

이 메소드는 Number 인스턴스가 가지고 있는 값을 반환합니다.

##### 원형

``` js
numObj.valueOf()
```

##### 예제

``` js
var numObj = new Number(123); // 123의 값을 가지는 Number 인스턴스를 생성함.
typeof numObj;                // object
var num = numObj.valueOf();
num;                          // 123
typeof num;                   // number
```



#### 자바스크립트 Number.prototype 메소드

|     메소드      |                             설명                             |
| :-------------: | :----------------------------------------------------------: |
| toExponential() | Number 인스턴스를 지수 표기법으로 변환한 후, 그 값을 문자열로 반환함. |
|    toFixed()    | Number 인스턴스의 소수 부분 자릿수를 전달받은 값으로 고정한 후, 그 값을 문자열로 반환함. |
|  toPrecision()  | Number 인스턴스의 가수와 소수 부분의 합친 자릿수를 전달받은 값으로 고정한 후, 그 값을 문자열로 반환함. |
|   toString()    |           Number 인스턴스의 값을 문자열로 반환함.            |
|    valueOf()    |          Number 인스턴스가 가지고 있는 값을 반환함.          |



---

### Math 객체

------

#### Math 객체

Math 객체는 수학에서 자주 사용하는 상수와 함수들을 미리 구현해 놓은 자바스크립트 표준 내장 객체입니다.

 

Math 객체는 다른 전역 객체와는 달리 생성자(constructor)가 존재하지 않습니다.

따라서 따로 인스턴스를 생성하지 않아도 Math 객체의 모든 메소드나 프로퍼티를 바로 사용할 수 있습니다.

------

#### Math 메소드

자바스크립트는 웹 페이지에서 수학적 작업을 손쉽게 할 수 있도록 다양한 Math 메소드를 제공하고 있습니다.

가장 많이 사용되는 대표적인 Math 메소드는 다음과 같습니다. 

- 1. Math.min()

- 2. Math.max()

- 3. Math.random()

- 4. Math.round()

- 5. Math.floor()

- 6. Math.ceil()

- 7. Math.sin()

 

대부분의 Math 메소드는 웹 브라우저마다 다른 결괏값을 얻을 가능성이 높습니다.

심지어 같은 자바스크립트 인터프리터라도 운영체제가 다르면 다른 결괏값을 반환할 수 있습니다.

따라서 아주 정확한 결괏값이 필요한 작업에는 Math 메소드는 사용하지 않는 것이 좋습니다.

------

#### Math.min() 메소드

Math.min() 메소드는 인수로 전달받은 값 중에서 가장 작은 수를 반환합니다.

인수가 전달되지 않으면 Infinity를 반환하며, 인수 중에 비교할 수 없는 값이 포함되어 있으면 NaN을 반환합니다.

##### 예제

``` js
Math.min();                              // Infinity
Math.min(1, 10, -100, -10, 1000, 0);     // -100
Math.min(1, 10, -100, -10, "-1000", 0);  // -1000
Math.min(1, 10, -100, -10, "문자열", 0); // NaN
```



#### Math.max() 메소드

Math.max() 메소드는 인수로 전달받은 값 중에서 가장 큰 수를 반환합니다.

인수가 전달되지 않으면 -Infinity를 반환하며, 인수 중에 비교할 수 없는 값이 포함되어 있으면 NaN을 반환합니다.

##### 예제

``` js
Math.max();                              // -Infinity
Math.max(1, 10, -100, -10, 100, 0);      // 100
Math.max(1, 10, -100, -10, "1000", 0);   // 1000
Math.max(1, 10, -100, -10, "문자열", 0); // NaN
```



#### Math.random() 메소드

Math.random() 메소드는 0보다 크거나 같고 1보다 작은 무작위 숫자(random number)를 반환합니다.

##### 예제

``` js
var min = 10, max = 20;
Math.random();                     // [0, 1)
Math.random() * (max - min) + min; // [min, max)
```

> 위의 예제에서 사용된 '['기호는 '크거나 같은'을 나타내며, ']'기호는 '작거나 같은'을 나타내는 기호입니다.
> 또한, '('기호는 '보다 큰'을 나타내며, ')'기호는 '보다 작은'을 나타내는 기호입니다.



#### Math.round() 메소드

Math.round() 메소드는 인수로 전달받은 값을 소수점 첫 번째 자리에서 반올림하여 그 결괏값을 반환합니다.

##### 예제

``` js
Math.round(10.49);  // 10
Math.round(10.5);   // 11
Math.round(-10.5);  // -10
Math.round(-10.51); // -11
```



#### Math.floor() 메소드

Math.floor() 메소드는 인수로 전달받은 값과 같거나 작은 수 중에서 가장 큰 정수를 반환합니다.

##### 예제

``` js
Math.floor(10.95);  // 10
Math.floor(11.01);  // 11
Math.floor(-10.95); // -11
Math.floor(-11.01); // -12
```



#### Math.ceil() 메소드

Math.ceil() 메소드는 인수로 전달받은 값과 같거나 큰 수 중에서 가장 작은 정수를 반환합니다.

##### 예제

``` js
Math.ceil(10.95);  // 11
Math.ceil(11.01);  // 12
Math.ceil(11);     // 11
Math.ceil(-10.95); // -10
Math.ceil(-11.01); // -11
```



#### Math.sin() 메소드

Math.sin() 메소드는 인수로 전달받은 값의 사인(sine) 함숫값을 반환합니다.

##### 예제

``` js
Math.sin(0);           // 0
Math.sin(Math.PI / 2); // 1
```



자바스크립트에서 제공하는 삼각 함수에 관한 모든 메소드는 각도의 단위로 라디안(radian)을 사용합니다.

이때 라디안 단위와 60분법 단위를 서로 변환하기 위해서는 다음과 같은 공식을 사용합니다.

##### 문법

``` js
라디안값 = 60분법값 * (Math.PI / 180)
```

> Math.PI는 수학에서 사용하는 파이(π)값을 나타내는 자바스크립트 상수입니다.
> 따라서 대략적인 값으로 3.145159를 나타냅니다.



#### 자바스크립트 Math 메소드

|                            메소드                            |                             설명                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|                     Math.min(x, y, ...)                      |       인수로 전달받은 값 중에서 가장 작은 수를 반환함.       |
|                     Math.max(x, y, ...)                      |        인수로 전달받은 값 중에서 가장 큰 수를 반환함.        |
|                        Math.random()                         | 0보다 크거나 같고 1보다 작은 랜덤 숫자(random number)를 반환함. |
|                        Math.round(x)                         |   x를 소수점 첫 번째 자리에서 반올림하여 그 결과를 반환함.   |
|                        Math.floor(x)                         |       x와 같거나 작은 수 중에서 가장 큰 정수를 반환함.       |
|                         Math.ceil(x)                         |       x와 같거나 큰 수 중에서 가장 작은 정수를 반환함.       |
|                         Math.abs(x)                          |                     x의 절댓값을 반환함.                     |
|                         Math.cbrt(x)                         |                    x의 세제곱근을 반환함.                    |
|                         Math.sqrt(x)                         |                     x의 제곱근을 반환함.                     |
|                        Math.clz32(x)                         | x을 32비트 이진수로 변환한 후, 0이 아닌 비트의 개수를 반환함. |
|                         Math.exp(x)                          |             ex 의 값을 반환함. (e : 오일러의 수)             |
|                        Math.expm1(x)                         |                    1 - ex 의 값을 반환함.                    |
|                        Math.fround(x)                        | x와 가장 근접한 32비트 부동 소수점 수(single precision float)를 반환함. |
|                    Math.hypot(x, y, ...)                     | 인수로 전달받은 값들을 각각 제곱한 후 더한 총합의 제곱근을 반환함. |
|                       Math.imul(x, y)                        |     인수로 전달받은 두 값의 32비트 곱셈의 결과를 반환함.     |
|                         Math.log(x)                          |               x의 자연로그 값을 반환함. (ln x)               |
|                        Math.log1p(x)                         |                   ln(1 + x)의 값을 반환함.                   |
|                        Math.log10(x)                         |           x의 10을 밑으로 가지는 로그 값을 반환함.           |
|                         Math.log2(x)                         |           x의 2를 밑으로 가지는 로그 값을 반환함.            |
|                        Math.pow(x, y)                        |                      x의 y승을 반환함.                       |
|                         Math.sign(x)                         |                    x의 부호 값을 반환함.                     |
|                        Math.trunc(x)                         |     x의 모든 소수 부분을 삭제하고 정수 부분만을 반환함.      |
| Math.sin(x), Math.cos(x), Math.tan(x),Math.asin(x), Math.acos(x), Math.atan(x), Math.asinh(x), Math.acosh(x), Math.atanh(x), Math.atan2(x) |                x의 해당 삼각 함숫값을 반환함.                |

------

#### 자바스크립트 Math 프로퍼티

자바스크립트는 수학에서 사용하는 다양한 상수들을 Math 프로퍼티를 이용해 제공하고 있습니다.

|   프로퍼티   |                             설명                             | 대략값  |
| :----------: | :----------------------------------------------------------: | :-----: |
|    Math.E    | 오일러의 수(Euler's constant)라고 불리며, 자연로그(natural logarithms)의 밑(base) 값 |  2.718  |
|   Math.LN2   |                       2의 자연로그 값                        |  0.693  |
|  Math.LN10   |                       10의 자연로그 값                       |  2.303  |
|  Math.LOG2E  |              오일러 수(e)의 밑 값이 2인 로그 값              |  1.443  |
| Math.LOG10E  |             오일러 수(e)의 밑 값이 10인 로그 값              |  0.434  |
|   Math.PI    |          원의 원주를 지름으로 나눈 비율(원주율) 값           | 3.14159 |
| Math.SQRT1_2 |                     2의 제곱근의 역수 값                     |  0.707  |
|  Math.SQRT2  |                        2의 제곱근 값                         |  1.414  |



---

### Date 객체

------

#### 자바스크립트에서의 날짜 표현

자바스크립트에서는 Date 객체를 사용하여 매 순간 변화하는 시간과 날짜에 관한 정보를 손쉽게 얻을 수 있습니다.

Date 객체는 연월일, 시분초의 정보와 함께 밀리초(millisecond)의 정보도 함께 제공합니다.

자바스크립트에서 날짜와 시간을 나타내기 위한 값의 범위는 다음과 같습니다.

- 1. 연도(year) : 1900년(00) ~ 1999년(99)

- 2. 월(month) : 1월(0) ~ 12월(11) 

- 3. 일(day) : 1일(1) ~ 31일(31)

- 4. 시(hours) : 0시(0) ~ 23시(23)

- 5. 분(minutes) : 0분(0) ~ 59분(59)

- 6. 초(seconds) : 0초(0) ~ 59초(59)

> 자바스크립트에서 월(month)을 나타낼 때는 1월이 0으로 표현되고, 12월이 11로 표현된다는 사실에 유의해야 합니다.

#### Date 객체

자바스크립트에서 Date 객체를 초기화하는 방법은 다음과 같습니다.

- 1. new Date()

- 2. new Date("날짜를 나타내는 문자열")

- . new Date(밀리초)

- 4. new Date(년, 월, 일, 시, 분, 초, 밀리초)

 

Date 객체를 생성할 때 어떠한 인수도 전달하지 않으면, 현재 날짜와 시간을 가지고 Date 객체를 생성합니다.

##### 예제

``` js
var date = new Date(); // Date 객체 생성
document.write(date);
```



Date 객체를 생성할 때 인수가 전달되면, 그 형태에 따라 특정 날짜와 시간을 가리키는 Date 객체를 생성합니다.

##### 예제

``` js
new Date("December 14, 1977 13:30:00"); // 날짜를 나타내는 문자열
new Date(80000000);               // 1970년 1월 1일 0시부터 해당 밀리초만큼 지난 날짜
new Date(16, 5, 25);              // 3개의 숫자로 나타내는 날짜이며, 시간은 자동으로 0시 0분 0초로 설정됨.
new Date(16, 5, 25, 15, 40, 0);   // 7개의 숫자로 나타내는 날짜
new Date(2016, 5, 25, 15, 40, 0); // 2000년대를 표기하고자 할 때에는 연도를 전부 표기해야 함.
```

> 자바스크립트에서 날짜 계산의 모든 기준은 1970년 1월 1일 00:00:00(UTC, 협정세계시)부터입니다.
> 또한, 하루는 86,400,000 밀리초(millisecond)로 계산됩니다.



#### 자바스크립트 날짜 양식(date format)

자바스크립트에서 날짜를 표현할 수 있는 양식은 다음과 같습니다.

- 1. ISO 날짜 양식

- 2. Long 날짜 양식

- 3. Short 날짜 양식 

- 4. Full 날짜 양식

------

#### ISO 날짜 양식

ISO 8601은 날짜와 시간을 나타내는 국제 표준 양식입니다.

##### 문법

``` js
YYYY-MM-DDTHH:MM:SS // T는 UTC(협정세계시)를 나타내는 문자로 시간까지 표현할 때에는 반드시 사용해야 함.
YYYY-MM-DD
YYYY-MM
YYYY
```

##### 예제

``` js
new Date("1977-12-14T13:30:00"); // 날짜와 시간까지 표현함.
new Date("1977-12-14");          // 시간이 생략되면 자동으로 09:00:00으로 설정됨.
new Date("1977-12");             // 일이 생략되면 자동으로 1일로 설정됨.
new Date("1977");                // 월이 생략되면 자동으로 1월로 설정됨.
```



#### Long 날짜 양식

##### 문법

``` js
MMM DD YYYY
DD MMM YYYY
```

##### 예제

``` js
new Date("Feb 19 1982");        // MMM DD YYYY
new Date("19 Feb 1982");        // DD MMM YYYY
new Date("February 19 1982");   // 월의 축약형 뿐만 아니라 전체 단어도 인식함.
new Date("FEBRUARY, 19, 1982"); // 쉼표는 무시되며, 대소문자의 구분은 없음.
```



#### Short 날짜 양식

##### 문법

``` js
MM/DD/YYYY
YYYY/MM/DD
```

##### 예제

``` js
new Date("02/19/1982"); // MM/DD/YYYY
new Date("1982/02/19"); // YYYY/MM/DD
```

> ISO 날짜 양식과 short 날짜 양식에서는 반드시 월일 순서로 날짜가 나와야 하며, 일월 순서로 나온 날짜는 자바스크립트가 제대로 인식하지 못합니다.



#### Full 날짜 양식

자바스크립트에서 사용하는 날짜 양식으로 표현된 문자열도 날짜로 인식합니다.

##### 예제

``` js
Wed May 25 2016 17:00:31 GMT+0900
```

##### 예제

``` js
new Date("Wed May 25 2016 17:00:00 GMT+0900 (Seoul Time)");
// GMT가 현재 국가와 다른 시간은 현재 국가의 GMT로 변환되어 표현됨.
new Date("Wed May 25 2016 17:00:00 GMT-0500 (New York Time)");
```



---

### Date 메소드

------

#### Date.now() 메소드

Date.now() 메소드는 1970년 1월 1일 0시 0분 0초부터 현재까지의 시간을 밀리초(millisecond) 단위의 정수로 반환합니다.

##### 예제

``` js
var nowMiliSec = Date.now();
nowMiliSec;           // 1970년 1월 1일 00:00:00부터 현재까지의 밀리초
new Date(nowMiliSec); // new Date()와 같은 결과를 반환함.
new Date();
```

#### Date.prototype getter 메소드

모든 Date 인스턴스는 Date.prototype으로부터 메소드와 프로퍼티를 상속받습니다.

 

Date.prototype getter 메소드는 날짜와 관련된 정보를 받아오기 위한 메소드입니다.

가장 많이 사용되는 대표적인 getter 메소드는 다음과 같습니다. 

- 1. getFullYear()

- 2. getDate()

- 3. getDay()

- 4. getTime()

------

#### getFullYear() 메소드

getFullYear() 메소드는 현재 연도를 4비트의 숫자(YYYY)로 반환합니다.

##### 예제

``` js
var date = new Date();
document.write("올해는 " + date.getFullYear() + "년입니다."); // 현재 연도를 반환함.
```



#### getDate() 메소드

getDate() 메소드는 현재 날짜에 해당하는 숫자를 반환합니다.

##### 예제

``` js
var date = new Date();
document.write("오늘은 " + date.getMonth() + "월 " + date.getDate() + "일입니다."); // 현재 날짜를 반환함.
```



#### getDay() 메소드

getDay() 메소드는 현재 요일에 해당하는 숫자를 반환합니다.

 

자바스크립트에서 일주일은 일요일(0)부터 시작하여 토요일(6)로 끝납니다.

##### 예제

``` js
var date = new Date();
var day;
switch (date.getDay()) { // 현재 요일을 반환함.
    case 0:
        day = "일";
        break;
    case 1:
        day = "월";
        break;
    ...
    case 6:
        day = "토";
        break;
}
document.write("오늘은 " + day + "요일입니다.");
```

배열을 사용하면 더욱 간단하게 요일을 출력할 수 있습니다.

##### 예제

``` js
var date = new Date();
var days = ["일", "월", "화", "수", "목", "금", "토"];
document.write("오늘은 " + days[date.getDay()] + "요일입니다.");
```

#### getTime() 메소드

getTime() 메소드는 1970년 1월 1일 0시 0분 0초부터 현재까지의 시간을 밀리초 단위로 환산한 값을 숫자로 반환합니다.

##### 예제

``` js
var date = new Date();
var period = date.getTime() / 86400000 // 하루는 86,400,000 밀리초로 계산됨.
document.write("1970년 1월 1일부터 오늘까지 " + period.toFixed() + "일이 지났습니다."); // 소수 부분은 생략함.
```



#### 자바스크립트 Date.prototype getter 메소드

|       메소드        |                             설명                             | 값의 범위 |
| :-----------------: | :----------------------------------------------------------: | :-------: |
|      getDate()      |      현지 시각으로 현재 일자에 해당하는 숫자를 반환함.       |  1 ~ 31   |
|      getDay()       |      현지 시각으로 현재 요일에 해당하는 숫자를 반환함.       |   0 ~ 6   |
|     getMonth()      |       현지 시각으로 현재 월에 해당하는 숫자를 반환함.        |  0 ~ 11   |
|    getFullYear()    |    현지 시각으로 현재 연도를 4비트의 숫자(YYYY)로 반환함.    |   YYYY    |
|     getHours()      |      현지 시각으로 현재 시각에 해당하는 숫자를 반환함.       |  0 ~ 23   |
|  getMilliseconds()  |  현지 시각으로 현재 시각의 밀리초에 해당하는 숫자를 반환함.  |  0 ~ 999  |
|    getMinutes()     |    현지 시각으로 현재 시각의 분에 해당하는 숫자를 반환함.    |  0 ~ 59   |
|    getSeconds()     |    현지 시각으로 현재 시각의 초에 해당하는 숫자를 반환함.    |  0 ~ 59   |
|      getTime()      | 1970년 1월 1일 0시 0분 0초부터 현재까지의 시간을 밀리초 단위로 환산한 값을 숫자로 반환함. |     -     |
| getTimezoneOffset() | UTC로부터 현재 시각까지의 시간차를 분 단위로 환산한 값을 숫자로 반환함. |     -     |

------

#### 자바스크립트 Date.prototype UTC getter 메소드

|        메소드        |                             설명                             |
| :------------------: | :----------------------------------------------------------: |
|     getUTCDate()     |    협정세계시(UTC)로 현재 일자에 해당하는 숫자를 반환함.     |
|     getUTCDay()      |    협정세계시(UTC)로 현재 요일에 해당하는 숫자를 반환함.     |
|    getUTCMonth()     |     협정세계시(UTC)로 현재 월에 해당하는 숫자를 반환함.      |
|   getUTCFullYear()   |  협정세계시(UTC)로 현재 연도를 4비트의 숫자(YYYY)로 반환함.  |
|    getUTCHours()     |    협정세계시(UTC)로 현재 시각에 해당하는 숫자를 반환함.     |
| getUTCMilliseconds() | 협정세계시(UTC)로 현재 시각의 밀리초에 해당하는 숫자를 반환함. |
|   getUTCMinutes()    |  협정세계시(UTC)로 현재 시각의 분에 해당하는 숫자를 반환함.  |
|   getUTCSeconds()    |  협정세계시(UTC)로 현재 시각의 초에 해당하는 숫자를 반환함.  |

------

#### Date.prototype setter 메소드

Date.prototype setter 메소드는 날짜와 관련된 정보를 설정하기 위한 메소드입니다.

가장 많이 사용되는 대표적인 setter 메소드는 다음과 같습니다.

- 1. setFullYear()

- 2. setDate()

------

#### setFullYear() 메소드

setFullYear() 메소드는 Date 객체의 값을 특정 날짜로 설정합니다.

##### 예제

``` js
var date = new Date();
date.setFullYear(1982, 1, 19); // 자바스크립트에서 2월은 1임.
date.getFullYear();            // 1982
date.getMonth();               // 1
date.getDate();                // 19
```



#### setDate() 메소드

setDate() 메소드는 Date 객체의 일자 값을 특정 일자로 설정합니다.

##### 예제

``` js
var date = new Date();
date.setDate(10);              // Date 객체의 일자 값을 10일로 설정함.
document.write(date + "<br>");
date.setDate(40);              // 40일을 설정하면, 초과되는 일수만큼 다음달로 넘어감.
document.write(date);
```



#### 자바스크립트 Date.prototype setter 메소드

|      메소드       |                             설명                             |  값의 범위   |
| :---------------: | :----------------------------------------------------------: | :----------: |
|     setDate()     |              현지 시각으로 특정 일자를 설정함.               |    1 ~ 31    |
|    setMonth()     |               현지 시각으로 특정 월을 설정함.                |    0 ~ 11    |
|   setFullYear()   | 현지 시각으로 특정 연도를 설정함. (연도뿐만 아니라 월과 일자도 설정할 수 있음) | YYYY, MM, DD |
|    setHours()     |              현지 시각으로 특정 시간을 설정함.               |    0 ~ 23    |
| setMilliseconds() |             현지 시각으로 특정 밀리초를 설정함.              |   0 ~ 999    |
|   setMinutes()    |               현지 시각으로 특정 분을 설정함.                |    0 ~ 59    |
|   setSeconds()    |               현지 시각으로 특정 초를 설정함.                |    0 ~ 59    |
|     setTime()     | 1970년 1월 1일 0시 0분 0초부터 밀리초 단위로 표현되는 특정 시간을 설정함. |      -       |

> 자바스크립트에서 setDay() 메소드는 존재하지 않습니다.



#### 자바스크립트 Date.prototype UTC setter 메소드

|        메소드        |                             설명                             |  값의 범위   |
| :------------------: | :----------------------------------------------------------: | :----------: |
|     setUTCDate()     |            협정세계시(UTC)로 특정 일자를 설정함.             |    1 ~ 31    |
|    setUTCMonth()     |             협정세계시(UTC)로 특정 월을 설정함.              |    0 ~ 11    |
|   setUTCFullYear()   | 협정세계시(UTC)로 특정 연도를 설정함. (연도뿐만 아니라 월과 일자도 설정할 수 있음) | YYYY, MM, DD |
|    setUTCHours()     |            협정세계시(UTC)로 특정 시간을 설정함.             |    0 ~ 23    |
| setUTCMilliseconds() |           협정세계시(UTC)로 특정 밀리초를 설정함.            |   0 ~ 999    |
|   setUTCMinutes()    |             협정세계시(UTC)로 특정 분을 설정함.              |    0 ~ 59    |
|   setUTCSeconds()    |             협정세계시(UTC)로 특정 초를 설정함.              |    0 ~ 59    |





### String 객체

------

#### 자바스크립트에서의 문자열 표현

자바스크립트에서 문자열 리터럴은 큰따옴표("")나 작은따옴표('')를 사용하여 손쉽게 만들 수 있습니다.

##### 예제

``` js
var firstStr = "이것도 문자열입니다.";      // 큰따옴표를 사용한 문자열
var secondStr = '이것도 문자열입니다.';     // 작은따옴표를 사용한 문자열
var thirdStr = "나의 이름은 '홍길동'이야."  // 작은따옴표는 큰따옴표로 둘러싸인 문자열에만 포함될 수 있음.
var fourthStr = '나의 이름은 "홍길동"이야.' // 큰따옴표는 작은따옴표로 둘러싸인 문자열에만 포함될 수 있음.
```



#### 문자열의 길이

자바스크립트에서 문자열의 길이는 length 프로퍼티에 저장됩니다.

##### 예제

``` js
var strKor = "한글";
var strEng = "abcABC";
strKor.length; // 2
strEng.length; // 6
```

오랫동안 사용되어 온 아스키(ASCII) 인코딩 환경에서 영문자는 한 글자당 1바이트, 한글은 한 글자당 2바이트로 표현됩니다.

하지만 UTF-8 인코딩 환경에서는 영문자는 한 글자당 1바이트, 한글은 한 문자당 3바이트로 표현됩니다.

 

자바스크립트의 length 프로퍼티는 해당 문자열의 총 바이트 수를 저장하는 것이 아닌 글자의 개수만을 저장합니다.

------

#### 이스케이프 시퀀스(escape sequence)

자바스크립트는 더욱 다양한 문자 표현을 위해 여러 가지 이스케이프 시퀀스(escape sequence) 방식을 제공합니다.

자바스크립트에서 제공하는 이스케이프 시퀀스 방식은 다음과 같습니다.

- 1. 16진수 이스케이프 시퀀스(hexadecimal escape sequence)

- 2. 유니코드 이스케이프 시퀀스(unicode escape sequence)

- 3. 유니코드 코드 포인트 이스케이프(unicode code point escape)

##### 예제

``` js
// 16진수 이스케이프 시퀀스로 \x 다음은 16진수 수로 인식됨.
'\xA2';   
// 유니코드 이스케이프 시퀀스로 \u 다음은 유니코드로 인식됨.
'\u00A2';
// ECMAScript 6부터 새롭게 추가된 유니코드 코드 포인트 이스케이프 방식임.
String.fromCodePoint(0x00A2);
```

> String.fromCodePoint() 메소드는 사파리, 익스플로러에서 지원하지 않습니다.



#### 긴 문자열 리터럴을 나누어 표현하기

자바스크립트에서는 길이가 긴 문자열 리터럴을 보기 좋게 표현하기 위해서 역 슬래시(\)나 결합(+) 연산자를 사용할 수 있습니다.

##### 예제

``` js
document.write("이 문자열은 아주 긴 문자열입니다. \
따라서 몇 번에 걸친 줄 나누기가 필요합니다. \
자바스크립트에서는 역슬래시와 문자 결합 연산자를 사용하여 줄을 나눌 수 있습니다.<br>");

document.write("이 문자열은 아주 긴 문자열입니다." + 
" 따라서 몇 번에 걸친 줄 나누기가 필요합니다." + 
" 자바스크립트에서는 역슬래시와 문자 결합 연산자를 사용하여 줄을 나눌 수 있습니다.");
```

> 역 슬래시(\)를 사용한 방식은 ECMAScript의 표준 방식이 아닙니다.
> 따라서 특정 웹 브라우저에서는 정상적으로 표현되지 않을 수도 있습니다.



#### String 객체

자바스크립트에서 문자열은 보통 문자열 리터럴을 사용하여 표현합니다.

 

하지만 문자열을 나타낼 때 new 연산자를 사용하여 명시적으로 String 객체를 생성할 수도 있습니다.

이러한 String 객체는 문자열 값을 감싸고 있는 래퍼(wrapper) 객체입니다.

##### 예제

``` js
var str = "JavaScript";
var strObj = new String("JavaScript");
str;              // "JavaScript"
strObj;           // "JavaScript"
typeof str;       // string
typeof strObj;    // object
(str == strObj);  // 문자열 값이 같으므로, true를 반환함.
(str === strObj); // 문자열 값은 같지만 타입이 다르므로, false를 반환함.
```



### String 메소드

------

#### String 메소드

String 메소드는 String 객체에 정의된 문자열과 관련된 작업을 할 때 사용하는 메소드입니다.

- 1. String.fromCharCode()

- 2. String.fromCodePoint()

------

#### String.fromCharCode() 메소드

이 메소드는 쉼표로 구분되는 일련의 유니코드에 해당하는 문자들로 구성된 문자열을 반환합니다.

##### 예제

``` js
String.fromCharCode(65, 66, 67); // "ABC"
```



#### String.fromCodePoint() 메소드

이 메소드는 쉼표로 구분되는 일련의 코드 포인트(code point)에 해당하는 문자들로 구성된 문자열을 반환합니다.

##### 예제

``` js
String.fromCodePoint(65, 66, 67); // "ABC"
String.fromCodePoint(0x2F804);    // "\uD87E\uDC04"
String.fromCodePoint(194564);     // "\uD87E\uDC04"
```

> String.fromCodePoint() 메소드는 사파리, 익스플로러에서 지원하지 않습니다.



#### 자바스크립트 String 메소드

|         메소드         |                             설명                             |
| :--------------------: | :----------------------------------------------------------: |
| String.fromCharCode()  | 쉼표로 구분되는 일련의 유니코드에 해당하는 문자들로 구성된 문자열을 반환함. |
| String.fromCodePoint() | 쉼표로 구분되는 일련의 코드 포인트(code point)에 해당하는 문자들로 구성된 문자열을 반환함. |
|      String.raw()      |       템플릿 문자열(template string)의 원형을 반환함.        |

------

#### String.prototype 메소드

모든 String 인스턴스는 String.prototype으로부터 메소드와 프로퍼티를 상속받습니다.

이렇게 상속받은 String.prototype 메소드를 이용하면, 다음과 같은 문자열 작업을 할 수 있습니다.

- 1. 문자열에서의 위치 반환

- 2. 문자열에서 지정된 위치에 있는 문자 반환

- 3. 문자열 추출

- 4. 문자열 분리

- 5. 문자열 결합

- 6. 문자열의 대소문자 변환

- 7. 문자열 주위의 공백 제거

- 8. 정규 표현식을 이용한 문자열 조작

> String 인스턴스의 값은 변경될 수(immutable) 없으므로, 모든 String 메소드는 결괏값으로 새로운 문자열을 생성하여 반환합니다.



#### 문자열에서의 위치 찾기

다음 메소드는 String 인스턴스에서 특정 문자나 문자열이 처음으로 등장하는 위치나 마지막으로 등장하는 위치를 반환합니다.

- indexOf()

- lastIndexOf()

이 메소드들은 문자열을 찾기 시작할 String 인스턴스의 위치를 두 번째 인수로 전달할 수 있습니다.

만약 전달받은 특정 문자나 문자열을 찾을 수 없을 때는 -1을 반환합니다.

##### 예제

```js
var str = "abcDEFabc";
str.indexOf('abc');     // 0  -> 자바스크립트에서 인덱스는 0부터 시작함.
str.indexOf('abcd');    // -1 -> 문자열을 비교할 때 문자의 대소문자를 구분함.
str.indexOf('abc', 3);  // 6  -> 인덱스 3부터 'abc'를 찾기 시작함.
str.lastIndexOf('abc'); // 6
str.lastIndexOf('d');   // -1
str.lastIndexOf('c');   // 8
```



#### 문자열에서 지정된 위치에 있는 문자 반환

다음 메소드는 String 인스턴스에서 전달받은 인덱스에 위치한 문자나 문자 코드를 반환합니다.

-  charAt()

-  charCodeAt()

-  charPointAt()

##### 예제

``` js
var str = "abcDEFabc";
str.charAt(0);      // a
str.charAt(10);     // 빈 문자열 -> 전달받은 인덱스가 문자열의 길이보다 클 경우에는 빈 문자열을 반환함.
str.charCodeAt(0);  // 97        -> 'a'에 해당하는 UTF-16 코드를 반환함.
str.codePointAt(0); // 97        -> 'a'에 해당하는 유니코드 코드 포인트를 반환함.
```

> codePointAt() 메소드는 사파리, 익스플로러에서 지원하지 않습니다.



#### 문자열 추출

다음 메소드는 String 인스턴스에서 전달받은 시작 인덱스부터 종료 인덱스 바로 앞까지의 문자열만을 추출하여 만든 새로운 문자열을 반환합니다.

- slice()

- substring()

- substr()

##### 예제

``` js
var str = "abcDEFabc";
str.slice(2, 6);     // cDEF     -> 인덱스 2부터 인덱스 5까지의 문자열을 추출함.
str.slice(-4, -2);   // Fa       -> 음수로 전달된 인덱스는 문자열의 뒤에서부터 시작함.
str.slice(2);        // abcDEFab -> 인수로 종료 인덱스가 전달되지 않으면 문자열의 마지막까지 추출함.
str.substring(2, 6); // cDEF
str.substr(2, 4);    // cDEF
```



#### 문자열 분리

다음 메소드는 String 인스턴스를 구분자(separator)를 기준으로 나눈 후, 나뉜 문자열을 하나의 배열로 반환합니다.

- split()

split() 메소드는 인수로 구분자를 전달하지 않으면, 전체 문자열을 하나의 배열 요소로 가지는 길이가 1인 배열을 반환합니다.

##### 예제

``` js
var str = "자바스크립트는 너무 멋져요! 그리고 유용해요.";
str.split();    // 구분자를 명시하지 않으면 아무런 동작도 하지 않음.
str.split("");  // 한 문자("")씩 나눔.
str.split(" "); // 띄어쓰기(" ")를 기준으로 나눔.
str.split("!"); // 느낌표("!")를 기준으로 나눔.
```



#### 문자열 결합

다음 메소드는 String 인스턴스에 전달받은 문자열을 결합한 새로운 문자열을 반환합니다.

- concat()

##### 예제

``` js
var str = "자바스크립트";
str;                                                // 자바스크립트
str.concat("는 너무 멋져요!");                      // 자바스크립트는 너무 멋져요!
str.concat("는 너무 멋져요!", " 그리고 유용해요."); // 자바스크립트는 너무 멋져요! 그리고 유용해요!
str;                                                // 자바스크립트
```

위의 예제에서 여러 번 concat() 메소드를 실행한 후의 변수 str의 문자열은 여전히 처음과 같습니다.

이처럼 자바스크립트에서 String 인스턴스의 값은 변경될 수(immutable) 없습니다.

따라서 모든 String 메소드는 결괏값으로 새로운 문자열을 생성하여 반환합니다.



#### 문자열의 대소문자 변환

다음 메소드는 String 인스턴스의 모든 문자를 대문자나 소문자로 변환한 새로운 문자열을 반환합니다.

- toUpperCase()

- toLowerCase()

##### 예제

``` js
var str = "JavaScript";
str.toUpperCase(); // JAVASCRIPT
str.toLowerCase(); // javascript
```



#### 문자열 주위의 공백 제거

다음 메소드는 String 인스턴스의 양 끝에 존재하는 모든 공백과 줄 바꿈 문자(LF, CR 등)를 제거한 새로운 문자열을 반환합니다.

- trim()

trim() 메소드는 String 인스턴스의 문자열 값 그 자체에는 영향을 주지 않습니다.

##### 예제

``` js
var str = "      JavaScript    ";
str.trim();
```



#### 정규 표현식을 이용한 문자열 조작

다음 메소드는 인수로 전달받은 정규 표현식을 이용하여, String 인스턴스 값에서 검색, 대조, 대체 등의 작업을 수행합니다.

- search()

- match()

- replace()

정규 표현식에 대한 더 자세한 사항은 자바스크립트 정규 표현식 수업에서 확인할 수 있습니다.

[자바스크립트 정규 표현식 수업 확인 =>](http://tcpschool.com/javascript/js_regularExpression_concept)

------

#### 자바스크립트 String.prototype 메소드

|       메소드        |                             설명                             |
| :-----------------: | :----------------------------------------------------------: |
|      indexOf()      | String 인스턴스에서 특정 문자나 문자열이 처음으로 등장하는 위치의 인덱스를 반환함. |
|    lastIndexOf()    | String 인스턴스에서 특정 문자나 문자열이 마지막으로 등장하는 위치의 인덱스를 반환함. |
|      charAt()       | String 인스턴스에서 전달받은 인덱스에 위치한 문자를 반환함.  |
|    charCodeAt()     | String 인스턴스에서 전달받은 인덱스에 위치한 문자의 UTF-16 코드를 반환함. (0 ~ 65535) |
|    charPointAt()    | String 인스턴스에서 전달받은 인덱스에 위치한 문자의 유니코드 코드 포인트(unicode code point)를 반환함. |
|       slice()       | String 인스턴스에서 전달받은 시작 인덱스부터 종료 인덱스 바로 앞까지의 문자열을 추출한 새 문자열을 반환함. |
|     substring()     | String 인스턴스에서 전달받은 시작 인덱스부터 종료 인덱스 바로 앞까지의 문자열을 추출한 새 문자열을 반환함. |
|      substr()       | String 인스턴스에서 전달받은 시작 인덱스부터 길이만큼의 문자열을 추출한 새로운 문자열을 반환함. |
|       split()       | String 인스턴스에서 구분자(separator)를 기준으로 나눈 후, 나뉜 문자열을 하나의 배열로 반환함. |
|      concat()       | String 인스턴스에 전달받은 문자열을 결합한 새로운 문자열을 반환함. |
|    toUpperCase()    | String 인스턴스의 모든 문자를 대문자로 변환한 새로운 문자열을 반환함. |
|    toLowerCase()    | String 인스턴스의 모든 문자를 소문자로 변환한 새로운 문자열을 반환함. |
|       trim()        | String 인스턴스의 양 끝에 존재하는 공백과 모든 줄 바꿈 문자(LF, CR 등)를 제거한 새로운 문자열을 반환함. |
|      search()       | 인수로 전달받은 정규 표현식에 맞는 문자나 문자열이 처음으로 등장하는 위치의 인덱스를 반환함. |
|      replace()      | 인수로 전달받은 패턴에 맞는 문자열을 대체 문자열로 변환한 새 문자열을 반환함. |
|       match()       | 인수로 전달받은 정규 표현식에 맞는 문자열을 찾아서 하나의 배열로 반환함. |
|     includes()      | 인수로 전달받은 문자나 문자열이 포함되어 있는지를 검사한 후 그 결과를 불리언 값으로 반환함. |
|    startsWith()     | 인수로 전달받은 문자나 문자열로 시작되는지를 검사한 후 그 결과를 불리언 값으로 반환함. |
|     endsWith()      | 인수로 전달받은 문자나 문자열로 끝나는지를 검사한 후 그 결과를 불리언 값으로 반환함. |
| toLocaleUpperCase() | 영문자뿐만 아니라 모든 언어의 문자를 대문자로 변환한 새로운 문자열을 반환함. |
| toLocaleLowerCase() | 영문자뿐만 아니라 모든 언어의 문자를 소문자로 변환한 새로운 문자열을 반환함. |
|   localeCompare()   | 인수로 전달받은 문자열과 정렬 순서로 비교하여 그 결과를 정수 값으로 반환함. |
|     normalize()     | 해당 문자열의 유니코드 표준화 양식(Unicode Normalization Form)을 반환함. |
|      repeat()       | 해당 문자열을 인수로 전달받은 횟수만큼 반복하여 결합한 새로운 문자열을 반환함. |
|     toString()      |           String 인스턴스의 값을 문자열로 반환함.            |
|      valueOf()      |           String 인스턴스의 값을 문자열로 반환함.            |





### Array 객체

------

#### Array 객체

자바스크립트에서 배열(array)은 정렬된 값들의 집합으로 정의되며, Array 객체로 다뤄집니다.

 

자바스크립트 배열 표현에 대한 더 자세한 사항은 자바스크립트 배열 수업에서 확인할 수 있습니다.

[자바스크립트 배열 수업 확인 =>](http://tcpschool.com/javascript/js_array_basic)

------

#### Array 메소드

자바스크립트는 사용자가 배열과 관련된 작업을 손쉽게 할 수 있도록 다양한 메소드를 제공하고 있습니다.

Array 메소드는 Array 객체에 정의된 배열과 관련된 작업을 할 때 사용하는 메소드입니다.

- 1. Array.isArray()

- 2. Array.from()

- 3. Array.of()

------

#### Array.isArray() 메소드

Array.isArray() 메소드는 전달받은 값이 Array 객체인지 아닌지를 검사합니다.

##### 예제

``` js
Array.isArray([]);          // true
Array.isArray(new Array()); // true
Array.isArray(123);         // false
Array.isArray("Array");     // false
Array.isArray(true);        // false
```



#### Array.from() 메소드

ECMAScript 6부터 추가된 Array.from() 메소드는 다음 객체들을 배열처럼 변환시켜 줍니다.

- 1. 배열과 비슷한 객체(array-like objects) : length 프로퍼티와 인덱스 된 요소를 가지고 있는 객체

- 2. 반복할 수 있는 객체(iterable objects) : Map과 Set 객체 및 문자열과 같이 해당 요소를 개별적으로 선택할 수 있는 객체

하지만 이렇게 생성된 객체는 정확히 말하면 Array 객체는 아니며, Array 객체의 자식 클래스(child class)입니다.

##### 예제

``` js
function arrayFrom() {
    return Array.from(arguments);
}

Array.from(arrayFrom(1, 2, 3));        // [1, 2, 3]
var myMap = new Map([[1, 2], [3, 4]]);
Array.from(myMap);                     // [1, 2, 3, 4]
Array.from("JavaScript");              // [J,a,v,a,S,c,r,i,p,t]
```

> Array.from() 메소드는 파이어폭스 32.0 이상의 버전에서만 지원합니다.



#### Array.of() 메소드

ECMAScript 6부터 추가된 Array.of() 메소드는 인수의 수나 타입에 상관없이 인수로 전달받은 값을 가지고 새로운 Array 인스턴스를 생성합니다.

이때 Array.of() 메소드와 Array 객체 생성자와의 차이로는 정수로 전달된 인수의 처리 방식에 있습니다.

##### 예제

``` js
new Array(10); // [,,,,,,,,,] -> 10개의 배열 요소를 가지는 빈 배열을 생성함.
Array.of(10);  // [10] -> 한 개(숫자 10)의 배열 요소를 가지는 배열을 생성함.
```



위의 예제에서 Array 객체 생성자에 인수로 정수 10을 전달하면, 생성자는 길이가 10인 빈 배열을 생성합니다.

하지만 Array.of() 메소드에 인수로 정수 10을 전달하면, 정수 10을 배열 요소로 가지는 길이가 1인 배열을 생성합니다.

> Array.of() 메소드는 익스플로러, 오페라, 사파리에서 지원하지 않습니다.



#### 자바스크립트 Array 메소드

|     메소드      |                             설명                             |
| :-------------: | :----------------------------------------------------------: |
| Array.isArray() |         전달된 값이 Array 객체인지 아닌지를 검사함.          |
|  Array.from()   | 배열과 비슷한 객체와 반복할 수 있는 객체를 배열처럼 변환함.  |
|   Array.of()    | 인수의 수나 타입에 상관없이 인수로 전달받은 값을 가지고 새로운 Array 인스턴스를 생성함. |



### Array 메소드

------

#### Array.prototype 메소드

모든 Array 인스턴스는 Array.prototype으로부터 메소드와 프로퍼티를 상속받습니다.

이렇게 상속받은 Array.prototype 메소드는 크게 다음과 같이 구분할 수 있습니다.

- 1. 원본 배열을 변경하는 메소드

- 2. 원본 배열은 변경하지 않고 참조만 하는 메소드

- 3. 원본 배열을 반복적으로 참조하는 메소드

------

#### 원본 배열을 변경하는 Array.prototype 메소드

다음 메소드는 원본 배열을 직접 변경하는 메소드입니다.

- 1. push()

- 2. pop()

- 3. shift()

- 4. unshift()

- 5. reverse()

- 6. sort()

- 7. splice()

------

#### push() 메소드

push() 메소드는 하나 이상의 요소를 배열의 가장 마지막에 추가합니다.

원본 배열은 추가한 요소의 수만큼 길이(length)가 늘어나게 되며, 요소를 성공적으로 추가하면 배열의 총 길이를 반환합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
arr.length;               // 3
arr.push("자바스크립트");
arr.length;               // 4
arr;                      // [1,true,JavaScript,자바스크립트]
arr.push(2, "거짓");
arr.length;               // 6
arr;                      // [1,true,JavaScript,자바스크립트,2,거짓]
```



#### pop() 메소드

pop() 메소드는 배열의 가장 마지막 요소를 제거하고, 그 제거된 요소를 반환합니다.

따라서 pop() 메소드를 실행할 때마다 배열의 길이는 1씩 줄어들게 됩니다.

##### 예제

``` js
var arr = [1, true, "JavaScript", "자바스크립트"];
arr.length;  // 4
arr.pop();   // 자바스크립트
arr.length;  // 3
arr.pop();   // JavaScript
arr.length); // 2
arr;         // [1,true]
```



#### shift() 메소드

shift() 메소드는 pop() 메소드와는 달리 배열의 가장 마지막 요소가 아닌 첫 요소를 제거하고, 그 제거된 요소를 반환합니다.

따라서 shift() 메소드도 실행할 때마다 배열의 길이가 1씩 줄어들게 됩니다.

##### 예제

``` js
var arr = [1, true, "JavaScript", "자바스크립트"];
arr.length;  // 4
arr.shift(); // 1
arr.length;  // 3
arr.shift(); // true
arr.length;  // 2
arr;         // [JavaScript,자바스크립트]
```



#### unshift() 메소드

unshift() 메소드는 하나 이상의 요소를 배열의 가장 앞에 추가합니다.

원본 배열은 추가한 요소의 수만큼 길이(length)가 늘어나게 되며, 요소를 성공적으로 추가하면 배열의 총 길이를 반환합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
arr.length;                  // 3
arr.unshift("자바스크립트");
arr.length;                  // 4
arr;                         // [자바스크립트,1,true,JavaScript]
arr.unshift(2, "거짓");
arr.length;                  // 6
arr;                         // [2,거짓,자바스크립트,1,true,JavaScript]
```

> pop()과 push() 메소드를 사용하면 배열을 스택(stack)이라는 데이터 구조처럼 사용할 수 있으며,
> shift()와 push() 메소드를 사용하면 배열을 큐(queue)라는 데이터 구조처럼 사용할 수 있습니다.



#### reverse() 메소드

reverse() 메소드는 배열 요소의 순서를 전부 반대로 교체합니다.

즉, 가장 앞에 있던 요소가 가장 뒤에 위치하며, 가장 뒤에 있던 요소는 가장 앞에 위치하게 됩니다.

##### 예제

``` js
var arr = [1, true, "JavaScript", "자바스크립트"];
arr.reverse(); // [자바스크립트,JavaScript,true,1]]
```



#### sort() 메소드

sort() 메소드는 해당 배열의 배열 요소들을 알파벳 순서에 따라 정렬합니다.

이 메소드는 배열 요소를 모두 문자열로 보고 정렬하므로, 숫자나 불리언과 같은 타입의 요소들은 잘못 정렬될 수도 있습니다.

##### 예제

``` js
var strArr = ["로마", "나라", "감자", "다람쥐"]; // 한글은 ㄱ,ㄴ,ㄷ순으로 정렬됨.
var numArr = [10, 21, 1, 2, 3];                  // 숫자는 각 자릿수 별로 비교된 후 정렬됨.
strArr.sort(); // [감자,나라,다람쥐,로마]
numArr.sort(); // [1,10,2,21,3]
```



#### splice() 메소드

splice() 메소드는 기존의 배열 요소를 제거하거나 새로운 배열 요소를 추가하여 배열의 내용을 변경합니다.

 

첫 번째 인수는 새로운 요소가 삽입될 위치의 인덱스이며, 두 번째 인수는 제거할 요소의 개수입니다.

그 이후의 인수들은 모두 배열 요소로서 지정된 인덱스부터 차례대로 삽입됩니다.

이 메소드는 배열에서 제거된 요소를 배열의 형태로 반환하며, 아무 요소도 제거되지 않았으면 빈 배열을 반환합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript", "자바스크립트"];
// 인덱스 1의 요소부터 2개의 요소를 제거한 후, false와 "C언어"를 그 자리에 삽입함.
var removedElement = arr.splice(1, 2, false, "C언어");
arr;            // [1,false,C언어,자바스크립트]
removedElement; // [true,JavaScript]
```



#### 자바스크립트 Array.prototype 메소드

|    메소드    |                             설명                             |
| :----------: | :----------------------------------------------------------: |
|    push()    | 하나 이상의 요소를 배열의 가장 마지막에 추가하고, 배열의 총 길이를 반환함. |
|    pop()     | 배열의 가장 마지막 요소를 제거하고, 그 제거된 요소를 반환함. |
|   shift()    |   배열의 가장 첫 요소를 제거하고, 그 제거된 요소를 반환함.   |
|  unshift()   | 하나 이상의 요소를 배열의 가장 앞에 추가하고, 배열의 총 길이를 반환함. |
|  reverse()   |            배열 요소의 순서를 전부 반대로 교체함.            |
|    sort()    |     해당 배열의 배열 요소들을 알파벳 순서에 따라 정렬함.     |
|   splice()   | 기존의 배열 요소를 제거하거나 새로운 배열 요소를 추가하여 배열의 내용을 변경함. |
| copyWithin() | 해당 배열에서 일련의 요소들을 복사하여, 명시된 위치의 요소들을 교체함. |
|    fill()    | 시작 인덱스부터 종료 인덱스 바로 앞까지의 모든 배열 요소를 특정 값으로 교체함. |

------

#### 원본 배열은 변경하지 않고 참조만 하는 메소드

다음 메소드는 원본 배열을 직접 변경하지는 않고, 참조만 하는 메소드입니다.

- 1. join()

- 2. slice()

- 3. concat()

- 4. toString()

------

#### join() 메소드

join() 메소드는 배열의 모든 요소를 하나의 문자열로 반환합니다.

 

인수로 전달받은 문자열은 배열 요소 사이를 구분 짓는 구분자로 사용됩니다.

만약 인수를 전달받지 않으면 기본값으로 쉼표(,)를 구분자로 사용합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
arr.join();      // 1,true,JavaScript
arr.join(' + '); // 1 + true + JavaScript
arr.join(' ');   // 1 true JavaScript
arr.join('');    // 1trueJavaScript
```



#### slice() 메소드

slice() 메소드는 전달받은 시작 인덱스부터 종료 인덱스 바로 앞까지의 모든 배열 요소를 추출하여 새로운 배열을 반환합니다.

인수로 종료 인덱스가 전달되지 않으면 마지막 배열 요소까지 모두 추출합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript", "자바스크립트"];
arr.slice(1, 3); // [true,JavaScript]
arr.slice(1);    // [true,JavaScript,자바스크립트 ]
```



#### concat() 메소드

concat() 메소드는 해당 배열의 뒤에 인수로 전달받은 배열을 합쳐서 만든 새로운 배열을 반환합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
arr.concat([2, false, "문자열"]); // [1,true,JavaScript,2,false,문자열]
arr.concat([2], [3, 4]);          // [1,true,JavaScript,2,3,4] -> 2개 이상의 배열도 한 번에 합칠 수 있음.
arr.concat("다섯", [6, 7]);       // [1,true,JavaScript,다섯,6,7] -> 값과 배열도 한 번에 합칠 수 있음.
```



#### toString() 메소드

toString() 메소드는 해당 배열의 모든 요소를 하나의 문자열로 반환합니다.

이때 배열 요소의 사이에는 자동으로 쉼표(,)가 삽입됩니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
arr.toString(); // '1,true,JavaScript'
```



#### 자바스크립트 Array.prototype 메소드

|      메소드      |                             설명                             |
| :--------------: | :----------------------------------------------------------: |
|      join()      |          배열의 모든 요소를 하나의 문자열로 반환함.          |
|     slice()      | 전달받은 시작 인덱스부터 종료 인덱스 바로 앞까지의 모든 배열 요소를 추출하여 만든 새로운 배열을 반환함. |
|     concat()     | 해당 배열의 뒤에 인수로 전달받은 배열을 합쳐서 만든 새로운 배열을 반환함. |
|    toString()    |       해당 배열의 모든 요소를 하나의 문자열로 반환함.        |
| toLocaleString() |       해당 배열의 모든 요소를 하나의 문자열로 반환함.        |
|    indexOf()     | 전달받은 값과 동일한 배열 요소가 처음으로 등장하는 위치의 인덱스를 반환함. |
|  lastIndexOf()   | 전달받은 값과 동일한 배열 요소가 마지막으로 등장하는 위치의 인덱스를 반환함. |

------

#### 원본 배열을 반복적으로 참조하는 메소드

다음 메소드는 원본 배열을 변경하지 않고, 반복적으로 참조만 하는 메소드입니다.

- 1. forEach()

- 2. map()

- 3. filter()

- 4. every()

- 5. some()

- 6. reduce()

- 7. reduceRight()

- 8. entries()

- 9. keys()

- 10. values()

------

#### forEach() 메소드

forEach() 메소드는 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
function printArr(value, index, array) {
    document.write("arr[" + index + "] = " + value + "<br>");
}
arr.forEach(printArr); // 배열 arr의 각 요소마다 printArr() 함수가 호출됨.
```



#### map() 메소드

map() 메소드는 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 실행 결과를 새로운 배열에 담아 반환합니다.

##### 예제

``` js
var arr = [1, -2, 3, -4];
// 배열 arr의 각 요소마다 Math.abs() 함수가 호출되고 그 결괏값이 배열로 저장됨.
var absoluteValues = arr.map(Math.abs);
absoluteValues; // [1,2,3,4]
```



#### filter() 메소드

filter() 메소드는 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 true인 요소들만을 새로운 배열에 담아 반환합니다.

##### 예제

``` js
var arr = [-10, 5, 100, -20, 40];
function compareValue(value) {
    return value < 10;
}

var lessTen = arr.filter(compareValue);
lessTen; // [-10,5,-20]
```



#### every() 메소드

every() 메소드는 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 모두 true일 때에만 true를 반환합니다.

##### 예제

``` js
var arr = [-10, 5, -20, 4];

function compareValue(value) {
    return value < 10; // 배열의 모든 요소가 10보다 작음.
}

arr.every(compareValue); // true
```



#### some() 메소드

some() 메소드는 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 하나라도 true이면 true를 반환합니다.

##### 예제

``` js
var arr = [10, 25, -20, 14];

function compareValue(value) {
    return value < 10; // 배열 요소 중 -20만이 10보다 작음.
}

arr.some(compareValue); // true
```



#### reduce() 메소드

reduce() 메소드는 해당 배열의 모든 요소를 하나의 값으로 줄이기 위해, 두 개의 인수를 전달받는 콜백 함수를 실행합니다.

이때 명시된 콜백 함수에 배열의 첫 번째 요소와 두 번째 요소를 인수로 전달하고 실행합니다.

그 결과 반환된 결괏값과 세 번째 요소를 다시 인수로 전달하고 실행합니다.

이러한 동작을 반복하여 모든 배열 요소를 인수로 전달하고, 마지막으로 반환된 결괏값을 반환합니다.

##### 예제

``` js
var arr = [1, 2, 3, 4, 5];

function sumOfValues(x, y) {
    return x - y;
}

arr.reduce(sumOfValues); // 1 - 2 - 3 - 4 - 5 = -13
```



#### reduceRight() 메소드

reduceRight() 메소드는 reduce() 메소드와 같은 방식으로 실행되며, 배열의 마지막 요소부터 줄이기 시작합니다.

##### 예제

``` js
var arr = [1, 2, 3, 4, 5];

function sumOfValues(x, y) {
    return x - y;
}

arr.reduceRight(sumOfValues); // 5 - 4 - 3 - 2 - 1 = -5
```



#### entries() 메소드

entries() 메소드는 배열 요소별로 키(key)와 값(value)의 한 쌍으로 이루어진 새로운 배열 반복자 객체(Array Iterator Object)를 배열 형태로 반환합니다.

이때 키에는 인덱스가 저장되며, 값에는 배열 요소의 값이 저장됩니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
var arrEntries = arr.entries();

for (var entry of arrEntries) {
    document.write(entry + "<br>"); // 배열의 인덱스별로 키(key)와 값(value)의 한 쌍을 출력함.
}
```

> for / of 문은 익스플로러에서 지원하지 않습니다.



#### 자바스크립트 Array.prototype 메소드

|    메소드     |                             설명                             |
| :-----------: | :----------------------------------------------------------: |
|   forEach()   | 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행함. |
|     map()     | 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 실행 결과를 새로운 배열로 반환함. |
|   filter()    | 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 true인 요소들만을 새로운 배열에 담아 반환함. |
|    every()    | 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 모두 true일 때에만 true를 반환함. |
|    some()     | 해당 배열의 모든 요소에 대하여 반복적으로 명시된 콜백 함수를 실행한 후, 그 결괏값이 하나라도 true이면 true를 반환함. |
|   reduce()    | 해당 배열의 모든 요소를 하나의 값으로 줄이기 위해, 두 개의 인수를 전달받는 콜백 함수를 실행함.(배열의 첫 번째 요소부터 시작함.) |
| reduceRight() | 해당 배열의 모든 요소를 하나의 값으로 줄이기 위해, 두 개의 인수를 전달받는 콜백 함수를 실행함.(배열의 마지막 요소부터 시작함.) |
|   entries()   | 배열 요소별로 키와 값의 한 쌍으로 이루어진 새로운 배열 반복자 객체(Array Iterator Object)를 배열 형태로 반환함. |
|    keys()     | 배열 요소별로 키(key)만 포함하는 새로운 배열 반복자 객체를 배열 형태로 반환함. |
|   values()    | 배열 요소별로 값(value)만 포함하는 새로운 배열 반복자 객체를 배열 형태로 반환함. |
|    find()     | 검사를 위해 전달받은 함수를 만족하는 배열 요소의 값을 반환함. 만족하는 값이 없으면 undefined를 반환함. |
|  findIndex()  | 검사를 위해 전달받은 함수를 만족하는 배열 요소의 인덱스를 반환함. 만족하는 값이 없으면 -1을 반환함. |











