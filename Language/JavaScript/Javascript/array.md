# 배열

### 배열의 기초

------

#### 배열(array)이란?

자바스크립트에서 배열(array)은 이름과 인덱스로 참조되는 정렬된 값의 집합으로 정의됩니다.

배열을 구성하는 각각의 값을 배열 요소(element)라고 하며, 배열에서의 위치를 가리키는 숫자를 인덱스(index)라고 합니다.

 

자바스크립트에서 배열의 특징은 다음과 같습니다.

 

- 1. 배열 요소의 타입이 고정되어 있지 않으므로, 같은 배열에 있는 배열 요소끼리의 타입이 서로 다를 수도 있습니다.

- 2. 배열 요소의 인덱스가 연속적이지 않아도 되며, 따라서 특정 배열 요소가 비어 있을 수도 있습니다.

- 3. 자바스크립트에서 배열은 Array 객체로 다뤄집니다.

------

#### 배열의 생성

자바스크립트에서 배열을 만드는 방법은 다음과 같습니다.

##### 문법

``` js
1. var arr = [배열요소1, 배열요소2,...];          // 배열 리터럴을 이용하는 방법
2. var arr = Array(배열요소1, 배열요소2,...);     // Array 객체의 생성자를 이용하는 방법
3. var arr = new Array(배열요소1, 배열요소2,...); // new 연산자를 이용한 Array 객체 생성 방법
```

위의 세 가지 방법은 모두 같은 결과의 배열을 만들어 줍니다.

배열 리터럴은 대괄호([]) 안에 배열 요소를 쉼표로 구분하여 나열하는 방법으로 생성합니다.

 

##### 예제

``` js
var arrLit = [1, true, "JavaScript"];             // 배열 리터럴을 이용하는 방법
var arrObj = Array(1, true, "JavaScript");        // Array 객체의 생성자를 이용하는 방법
var arrNewObj = new Array(1, true, "JavaScript"); // new 연산자를 이용한 Array 객체 생성 방법
 
document.write(arrLit + "<br>");                  // 1,true,JavaScript
document.write(arrObj + "<br>");                  // 1,true,JavaScript 
document.write(arrNewObj);                        // 1,true,JavaScript
```



#### 배열의 참조

자바스크립트에서 배열의 각 요소를 참조하고 싶을 때는 [] 연산자를 사용합니다.

##### 문법

``` js
배열이름[인덱스]
```



자바스크립트에서는 배열 요소의 개수를 배열의 길이라고 합니다.

이러한 배열의 길이는 length 프로퍼티에 자동으로 갱신됩니다.

 

자바스크립트에서 인덱스는 언제나 0부터 시작합니다.

또한, 인덱스에는 음이 아닌 정수를 반환하는 임의의 표현식도 사용할 수 있습니다.

이러한 인덱스에는 232보다 작은 양수만을 사용할 수 있습니다.

 

다음 예제는 배열을 생성하고, 생성된 배열에 요소를 추가하고 삭제하는 예제입니다.

##### 예제

``` js
var arr = ["JavaScript"]; // 요소가 하나뿐인 배열을 생성함.
var element = arr[0];     // 배열의 첫 번째 요소를 읽어서 대입함.
 
arr[1] = 10;      // 배열의 두 번째 요소에 숫자 10을 대입함. 배열의 길이는 1에서 2로 늘어남.
arr[2] = element; // 배열의 세 번째 요소에 변수 element의 값을 대입함. 배열의 길이는 2에서 3으로 늘어남.
 
document.write("배열 arr의 요소에는 [" + arr + "]가 있습니다.<br>"); // 배열의 요소를 모두 출력함.
document.write("배열 arr의 길이는 " + arr.length + "입니다.<br>");   // 배열의 길이를 출력함.
 
delete arr[2];    // 배열의 세 번째 요소를 삭제함. 하지만 배열의 길이는 변하지 않음.
 
document.write("배열 arr의 요소에는 [" + arr + "]가 있습니다.<br>"); // 배열의 요소를 모두 출력함.
document.write("배열 arr의 길이는 " + arr.length + "입니다.");       // 배열의 길이를 출력함.
```

위의 예제에서 세 번째 실행문은 배열의 현재 길이보다 더 큰 인덱스에 요소를 저장하려고 합니다.

자바스크립트에서는 이렇게 배열의 길이를 넘는 인덱스에 요소를 저장하는 것을 허용합니다.

이때 배열의 길이는 자동으로 해당 인덱스까지 늘어나게 됩니다.

------

#### 배열 요소의 추가

자바스크립트에서 배열에 새로운 배열 요소를 추가하는 방법은 다음과 같습니다.

##### 문법

``` js
1. arr.push(추가할 요소);         // push() 메소드를 이용하는 방법
2. arr[arr.length] = 추가할 요소; // length 프로퍼티를 이용하는 방법
3. arr[특정인덱스] = 추가할 요소; // 특정 인덱스를 지정하여 추가하는 방법
```



push() 메소드와 length 프로퍼티를 이용한 방법은 모두 배열의 제일 끝에 새로운 요소를 추가합니다.

##### 예제

``` js
var arr = [1, true, "Java"];
 
arr.push("Script");           // push() 메소드를 이용하는 방법
document.write(arr + "<br>"); // 1,true,Java,Script
 
arr[arr.length] = 100;        // length 프로퍼티를 이용하는 방법
document.write(arr + "<br>"); // 1,true,Java,Script,100
 
arr[10] = "자바스크립트";     // 특정 인덱스를 지정하여 추가하는 방법
document.write(arr + "<br>"); // 1,true,Java,Script,100,,,,,,자바스크립트
document.write(arr[7]);       // undefined
```



위의 예제에서 배열 arr의 길이는 최종적으로 11이 됩니다.

이때 배열 요소가 존재하는 인덱스는 0, 1, 2, 3, 4, 10뿐이며, 나머지 인덱스에는 배열 요소가 존재하지 않습니다.

 

이렇게 인덱스에 대응하는 배열 요소가 없는 부분을 배열의 홀(hole)이라고 합니다.

자바스크립트에서는 이러한 배열의 홀(hole)을 undefined 값을 가지는 요소처럼 취급합니다.

따라서 위의 예제에서처럼 배열의 홀을 참조하게 되면 undefined 값을 반환하게 됩니다.

------

#### 배열의 순회(iteration)

배열의 모든 요소에 차례대로 접근하고 싶을 때는 for 문과 같은 반복문을 사용하여 접근할 수 있습니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"];
 
var result = "<table><tr>";
for (var idx in arr) {
    result += "<td>" + arr[idx] + "</td>";
}

result += "</tr></table>";
 
document.write(result);
```



#### Array 객체

자바스크립트에서 배열(array)은 정렬된 값들의 집합으로 정의되며, Array 객체로 다뤄집니다.

또한, 자바스크립트는 사용자가 배열과 관련된 작업을 손쉽게 할 수 있도록 다양한 메소드도 제공하고 있습니다.

 

다음 예제는 배열과 각 배열 요소의 typeof 연산 결과를 보여주는 예제입니다.

##### 예제

``` js
var arr = new Array(10, "문자열", false);
 
document.write((typeof arr) + "<br>");    // object
document.write((typeof arr[0]) + "<br>"); // number
document.write((typeof arr[1]) + "<br>"); // string
document.write(typeof arr[2]);            // boolean
```

자바스크립트 Array 객체에 대한 더 자세한 사항은 자바스크립트 Array 객체 수업에서 확인할 수 있습니다.

[자바스크립트 Array 객체 수업 확인 =>](http://tcpschool.com/javascript/js_standard_array)



---

### 배열의 활용

------

#### 희소 배열

희소 배열이란 배열에 속한 요소의 위치가 연속적이지 않은 배열을 의미합니다.

따라서 희소 배열의 경우 배열의 length 프로퍼티 값보다 배열 요소의 개수가 언제나 적습니다.

##### 예제

``` js
var arr = new Array(); // 빈 배열 객체를 생성함.
arr[99] = "JavaScript" // 배열 arr의 100번째 위치에 문자열을 삽입함.
// 100번째 요소를 삽입했기 때문에 배열의 길이는 100으로 늘어남.
document.write("배열의 길이는 " + arr.length + "입니다.");
```



#### 다차원 배열

다차원 배열이란 배열 요소가 또 다른 배열인 배열을 의미합니다.

- 지금까지 우리가 살펴본 배열은 1차원 배열입니다.

- 2차원 배열이란 배열 요소가 1차원 배열인 배열을 의미합니다. 

- 3차원 배열이란 배열 요소가 2차원 배열인 배열을 의미합니다.

 

2차원 배열을 이해하면 그 이상의 배열 또한 같은 방식으로 이해할 수 있습니다.

##### 예제

``` js
var arr = new Array(3);      // 3개의 요소를 가지는 배열을 생성함.
for (var row = 0; row < 3; row++) {
    arr[row] = new Array(4); // 각각의 요소마다 또다시 4개의 요소를 가지는 배열을 생성함.
    for (var column = 0; column < 4; column++) {
        arr[row][column] = "[" + row + "," + column + "]"; // 각각의 배열 요소를 생성함.
        document.write(arr[row][column] + " ");            // 각 배열 요소에 접근함.
    }
}
```

> 2차원 배열의 배열 요소는 [] 연산자를 두 번 사용하여 참조할 수 있습니다.

#### 연관 배열(associative array)

자바스크립트에서 배열의 인덱스에는 0을 포함한 양의 정수만을 사용할 수 있습니다.

이렇게 숫자로 된 인덱스 대신에 문자열로 된 키(key)를 사용하는 배열을 연관 배열(associative array)이라고 합니다.

 

대부분의 프로그래밍 언어가 지원하는 연관 배열을 자바스크립트는 별도로 제공하지는 않습니다.

대신에 인덱스로 문자열을 사용하여 연관 배열처럼 사용할 수 있는 객체(object)를 만들 수 있습니다.

 

하지만 이렇게 생성된 배열은 자바스크립트 내부적으로 Array 객체에서 기본 객체로 재선언됩니다.

따라서 기존에 사용할 수 있었던 모든 Array 메소드와 프로퍼티가 정확하지 않은 결괏값을 반환하게 될 것입니다.

##### 예제

``` js
var arr = [];     // 비어있는 배열을 생성함.
arr["하나"] = 1;  // 숫자 인덱스 대신에 문자열을 인덱스로 배열 요소를 추가함.
arr["참"] = true;
arr["자바스크립트"] = "JavaScript";
document.write(arr["참"]);  // 문자열을 인덱스로 배열 요소에 접근할 수 있음.
document.write(arr.length); // 연관 배열은 Array 객체가 아니므로 length 프로퍼티의 값이 0임.
document.write(arr[0]);     // undefined
```

위의 예제에서 연관 배열인 arr은 length 프로퍼티의 값으로 0을 반환합니다.

이처럼 자바스크립트에서 연관 배열은 Array 객체가 아닌 기본 객체이므로, 정확히 말하면 배열이 아닙니다.

 

ECMAScript 6부터는 이러한 불편함을 해결하기 위해 새로운 데이터 구조인 Map 객체를 별도로 제공하고 있습니다.

------

#### 문자열을 배열처럼 접근하기

자바스크립트에서 문자열은 변하지 않는 값이므로, 읽기 전용 배열로서 다룰 수 있습니다.

 

따라서 배열처럼 [] 연산자를 사용하여 문자열을 구성하는 각 문자에 바로 접근할 수 있습니다.

또한, Array 객체가 제공하는 모든 범용 메소드도 사용할 수 있습니다.

문자열의 각 문자는 String 객체에서 제공하는 charAt() 메소드를 사용해도 접근할 수 있습니다.

##### 예제

``` js
var str = "안녕하세요!";       // 문자열 생성
document.write(str.charAt(2)); // 하 
document.write(str[2]);        // 하
```

하지만 이렇게 문자열을 배열처럼 접근하는 방법은 인터넷 익스플로러 7과 그 이전 버전에서는 동작하지 않습니다.

또한, 문자열을 배열처럼 착각하게 하여, 다음과 같은 실수를 유발할 수도 있습니다.

##### 예제

``` js
var str = "안녕하세요!"; // 문자열 생성
str[0] = "";             // 자바스크립트의 문자열은 읽기 전용이므로, 이 문장은 오류를 발생시킵니다.
```



따라서 문자열을 바로 배열처럼 사용하지 말고, split() 메소드 등을 이용해 먼저 배열로 변환한 후 사용하는 것이 좋습니다.

split() 메소드에 대한 더 자세한 사항은 자바스크립트 String 메소드 수업에서 확인할 수 있습니다.

[자바스크립트 String 메소드 수업 확인 =>](http://tcpschool.com/javascript/js_standard_stringMethod)



#### 자바스크립트에서 배열 여부 확인

자바스크립트에서는 배열이라는 타입(type)을 별도로 제공하지 않습니다.

자바스크립트 배열은 객체(object) 타입이 되며, typeof 연산자를 사용하면 'object'를 반환합니다.

##### 예제

``` js
var arr = [1, true, "JavaScript"]; // 배열 생성
document.write(typeof arr);        // object
```



따라서 자바스크립트에서는 해당 변수가 배열인지 여부를 확인할 수 있도록 다음과 같은 방법들을 제공하고 있습니다.

- 1. Array.isArray() 메소드

- 2. instanceof 연산자

- 3. constructor 프로퍼티

 

ECMAScript 5부터는 Array 클래스에 isArray()라는 배열 여부를 확인할 수 있는 메소드를 추가하였습니다.

##### 예제

``` js
document.write(Array.isArray(arr));      // true
document.write(Array.isArray("문자열")); // false
```



하지만 구형 버전의 브라우저는 ECMAScript 5를 지원하지 않으므로, Array.isArray() 메소드가 정상적으로 동작하지 않을 수도 있습니다.

따라서 이때는 instanceof 연산자를 사용하여 해당 변수가 Array 객체인지를 판단하여 배열 여부를 확인할 수 있습니다.

##### 예제

``` js
document.write(arr instanceof Array); // true
document.write(123 instanceof Array); // false
```



또한, Array 객체의 constructor 프로퍼티를 사용하여 배열 여부를 확인할 수도 있습니다.

자바스크립트 배열에 대해 constructor 프로퍼티는 다음과 같은 값을 반환합니다.

##### 문법

``` js
function Array() {[native code]}
```



따라서 다음 예제와 같이 toString() 메소드와 indexOf() 메소드를 함께 사용하면 해당 변수의 배열 여부를 확인할 수 있습니다.

##### 예제

``` js
function isArray(a) {
    return a.constructor.toString().indexOf("Array") > -1;
}
 
var arr = [1, true, "JavaScript"];          // 배열 생성
document.write(arr.constructor);            // constructor 프로퍼티의 값 출력
document.write(arr.constructor.toString()); // function Array() {[native code]}
document.write(arr.constructor.toString().indexOf("Array")); // 10
document.write(isArray(arr))                // true
```



위 예제 ②번 라인에서는 toString() 메소드를 사용하여 constructor 프로퍼티의 값을 문자열로 변환합니다.

그리고 ③번 라인에서는 indexOf() 메소드를 사용하여 해당 문자열에서 "Array"라는 부분 문자열이 시작하는 인덱스를 구하고 있습니다.

indexOf() 메소드는 인수로 전달받은 문자열을 해당 문자열에서 찾지 못하면, 언제나 -1을 반환합니다.

따라서 만약 변수 arr가 배열이라면 "Array"라는 부분 문자열을 언제나 포함하고 있을 것이므로, ①번 라인의 결과는 언제나 true를 반환하게 됩니다.

