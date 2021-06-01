# JavaScript 코스

출처: https://ofcourse.kr/js-course/JavaScript-%EC%9E%85%EB%AC%B8



# JavaScript 입문

## 개요

 이 강의는 기본적인 프로그래밍 언어를 다룰 수 있는 것을 전제로 합니다.
 프로그래밍 언어를 처음 배우신다면 다른 강의를 보고 이 강의를 보는 것을 추천드립니다. 

`HTML`은 웹 사이트에서 화면에 표시되는 정보를 **약속** 한 것이며, `CSS`는 구체적으로 어떤 **스타일**로 요소가 표시 되는지를 정하는 규격이라고 할 수 있습니다.
 위 둘은 정적인 것만 할 수 있는 언어인데요, 대표적으로 사용자의 반응에 따라 유동적으로 화면과 내용이 바뀌는 일은 처리할 수 없다는 것입니다.

`JavaScript`는 웹페이지를 동적으로 만들어주는 언어로, **객체** 기반의 **스크립트** 프로그래밍 언어입니다.
 단순히 규격을 나타내는 `HTML`과 `CSS`와 달리, 변수와 함수 등이 존재하는 프로그래밍 언어이며, 현재도 활발한 발전이 이루어지고 있습니다.

`Java`와는 거의 관계가 없습니다. (이름만 따왔으며, 설계 근본부터 다름)
 브라우저(Chrome, Internet Explorer 등) 안에서 실행되는 `클라이언트` 언어로, 엔드유저 단에서 동작합니다.

`JavaScript`를 줄여서 `js`라고 하며 파일 확장자 또한 `.js`를 사용합니다.

## 사용법

[CSS](https://ofcourse.kr/css-course/CSS-입문#사용법)와 비슷하게 `HTML`문서 내에 기술하거나 별도의 파일로 분리하여 사용합니다.

```html
<!-- HTML 내에 기술 -->
<html>
<head>
	<script type="text/javascript">
		document.write('Hello World!');
	</script>
</head>
<body>
</body>
</html>
<!-- 외부 파일에서 불러오기 -->
<head>
	<script type="text/javascript" src="JS파일위치.js"></script>
```



## 스크립트 언어

`JavaScript`는 스크립트 언어이자 인터프리터 방식이 사용되어, 컴파일 과정이 필요 없습니다.

브라우저에서 즉시 해석되어 실행되는데, 한 곳에서 만든 엔진을 사용하지 않기 때문에
 브라우저마다 완전 동일한게 동작하다고 하기 어렵습니다.

개발 속도가 빠르고 문법이 간단하지만, 복잡한 프로그램을 만들기는 어렵다는 특징이 있습니다.



## 동적타입 언어

자바스크립트에에서는 개발자가 변수의 **타입**을 지정해주지 않습니다.
 정확히 말하면 변수의 값에 따라 인터프리터가 알아서 변수의 타입을 파악하고 값을 저장합니다.

```c
int a = 10;
char b = 'K';

int main() {
	return 2;
}
```

`C`나 `Java`같은 기존 정적타입 언어에선 위처럼 변수나 함수를 선언했다면
 `JavaScript`에서는 아래처럼 `var`이나 `function`이라는 키워드를 사용하며, 타입을 명시하지 않습니다.

```javascript
var a = 10;
var b = 'K';

function main() {
	return 2;
}
```

자료형에 대해선 다음 강의에서 자세히 다루겠습니다.



## 주석

여러줄짜리 주석은 `/*`로 시작하여 `*/`로 끝납니다.
 한줄짜리 주석은 `//` 를 사용합니다.

```javascript
/*
 author @prev
*/

var a;
// var b;
```



# 자료형

## 개요

앞선 강의에서 `JavaScript`는 동적타입 언어라고 설명했습니다.
 개발자는 변수의 타입을 미리 선언할 필요가 없으며, 브라우저가 자동으로 파악합니다.

하지만 이는 외적으로 타입이 없는 것 뿐이지, 내부적으로는 엄연히 자료형이 존재합니다.

`JavaScript` 자료형은 크게 Primitive(기본형)과 Object(객체) 타입이 존재합니다.



## Primitive 타입 자료형

- Boolean: 논리적인 요소로, `true`와 `false`값이 있음
- `null`: 빈 값의 리터럴 표현
- `undefined`: 값을 할당하지 않은 변수가 가지는 값
- [Number](https://ofcourse.kr/js-course/숫자형-변수): 숫자형으로 정수와 부동 소수점, 무한대 및 NaN(숫자가 아님)값을 포함합니다.
- [String](https://ofcourse.kr/js-course/문자형-변수): 문자열



## Object 타입 자료형

Reference 타입이라고도 합니다.
 `Object` 클래스 뿐만 아니라, **배열**과 **함수**, 사용자 정의 **클래스**도 모두 Object에 포함됩니다.

[객체 강의](https://ofcourse.kr/js-course/객체)에서 자세히 다룹니다.



# 숫자형 변수

## 개요

대부분의 스크립트 언어의 숫자 자료형과 유사합니다.
 **정수**와 **부동 소수점**의 구분 없이 하나의 자료형으로 사용됩니다.

아래와 같은 값들을 사용할 수 있습니다.

```javascript
40
-8
0
0.5
-3.2
0x1af // 16진수 표현법
0ab1  // 8진수 표현법
Infinity
-Infinity
```

### NaN과 Infinity

`NaN`은 숫자가 아님을 의미하며 `Infinity`는 무한대를 의미합니다.

`parseInt('blabla')`, `Math.sqrt(-1)` 등의 함수는 `Nan`을 반환하게 되며, `42 / 0` 처럼 무한대가 나오는 식은 `Infinity`를 반환합니다.



## 연산자

일반적인 언어들처럼, `JavaScript`에서도 연산자를 이용할 수 있습니다.

| +    | 덧셈   |
| ---- | ------ |
| -    | 뺄셈   |
| *    | 곱셈   |
| /    | 나눗셈 |
| %    | 나머지 |



# 문자형 변수

## 개요

`JavaScript`는 일반적인 문자열(String)을 지원하는 대부분의 언어들처럼 문자형 자료형을 지원합니다.
 (C언어는 문자열(String)을 지원하지 않습니다.)

자바스크립트에서는 `char`형이 존재하지 않아 `"` 혹은 `'`중 어떤 것으로 감싸도 문자열로 만들어집니다.

```javascript
var a = "Hello";
var b = 'world';
var c = "Amazing language 'JavaScript'";
```



## 이스케이프 문자

자바스크립트에서는 문자열안에 여러 줄의 글을 입력할 수 없습니다.
 아래처럼 코드를 작성하면 오류가 발생합니다.

```javascript
var myString = "first line
second line
third line";
```

대신 줄바꿈 이스케이프 문자열인 `\n`를 사용하여 표현합니다.

```javascript
var myString = "first line\nsecond line\nthird line";
```

대표적인 이스케이프 문자로는 아래가 있습니다.

| `\n` | 줄바꿈           |
| ---- | ---------------- |
| `\t` | 수평 탭          |
| `\\` | 문자 “`\`”       |
| `\'` | 작은 따옴표(`'`) |
| `\"` | 큰 따옴표(`"`)   |



## 문자열 연결

문자열끼리 이어 붙이거나, 문자열과 숫자 등을 이어 붙일때는 `+` 연산자를 사용합니다.
 엄격한 언어와는 달리, 서로 다른 타입의 변수를 이어 붙여도, 변수를 **강제**로 문자열로 변형하여 이어 붙이게 됩니다.

```javascript
> "a" + "b"
ab

> "my age is " + 20
my age is 20


> "today is " + new Date()
today is Sat Feb 11 2017 21:06:27 GMT+0900 (KST)
```



## 문자열 인덱싱

C언어에서 `char`형 배열에서 `[i]`를 사용해 하나의 문자에 접근했듯, 자바스크립트 문자열도 배열처럼 취급되어 인덱스 접근자(`[i]`)를 사용하여 각 문자에 접근할 수 있습니다.

## 템플릿 문자열

[ECMAScript 6](https://ofcourse.kr/js-course/관련기술#ecmascript-6)에서 새로운 종류의 문자열 표기법이 도입되었습니다.
 브라우저 호환성 등 고려할 것이 많기 때문에 당장 사용하기에는 힘들겠지만, 알고 있으면 좋을 듯 합니다.

[참고 링크](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Template_literals)



# 배열

## 개요

`JavaScript`의 배열은 숫자형이나 문자열과 마찬가지로 일반적인 스크립트 언어와 크게 다른 것이 없습니다.
 `C`나 `Java`와는 상당히 다른데, 동적 타입 언어의 특징상 배열에도 **타입**이 정해져있지 **않**습니다.

배열은 `[]`나 `new Array()`로 생성하며, 크기의 제약이 없고, 하나의 배열에 **서로 다른 타입**의 변수가 들어갈 수 있습니다.

```javascript
var emptyArray = [];
var oddNumbers = [1, 3, 5, 7, 9];
var evenNumbers = new Array(2, 4, 6, 8, 10);
var mixedArr = ['a', 1, 'b', 3, new Date(), "today"];
```

항목에 접근시에는 `fruits[1]` 처럼 사용합니다.



## 자주쓰는 속성과 메소드

#### 길이

```javascript
var colors = ['red', 'blue'];
console.log(colors.length);
// 2
```

#### 맨 뒤에 항목 추가

```javascript
colors.push('green');
// ['red', 'blue', 'green']
```

#### 맨 뒤의 항목 제거

```javascript
colors.pop(); // 함수의 반환값: green
// 배열의 값: ['red', 'blue']
```

#### 맨 앞에 항목 추가

```javascript
colors.unshift('white');
// ['white', 'red', 'blue']
```

#### 맨 앞의 항목 제거

```javascript
colors.shift(); // 함수의 반환값: white
// ['red', 'blue']
```

#### 배열 내부 값의 위치 찾기

```javascript
console.log( colors.indexOf('red') );
// 0
```

#### 인덱스 위치에서부터 n개의 항목 제거

```javascript
fruits = ['red', 'blue', 'green', 'yellow'];
var removedItem = fruits.splice(1, 2); // 1번 인덱스부터 2개 제거

// fruits: ['red', 'yellow']
// removedItem: ['blue', 'green']
```



# 객체

## 개요

자바스크립트에서 **객체**는 매우 중요한 개념입니다. [자료형](https://ofcourse.kr/js-course/자료형) 강의에서 자바스크립트의 자료형은 Primitive 타입과 Object 타입이 있다고 했는데, 다시 말하면 Primitive 타입 이외의 모든 변수는 객체 타입이라는 소리입니다.

객체와 Primitive(원시) 타입의 가장 큰 차이점은 **Reference(참조)**에 있습니다.

원시 타입 변수는 다른 변수에 값을 할당하거나 함수 인자로 넘길 때, 값을 복사하여 전달하지만,
 객체는 메모리 **주소**를 복사시키며 값 자체는 복사되지 않아 같은 객체를 참조하게 됩니다.

**배열**도 **객체**의 일부이며, 함수의 인자로 넘기거나 다른 변수에 참조시킬 수 있습니다.

## 사용법

자바스크립트에서 좁은 의미의 객체는 `키-값` 형태의 쌍을 저장할 수 있는 **딕셔너리**를 의미합니다.
 아래는 샘플 JavaScript Object 입니다.

```javascript
var me = {
	'name': 'young',
	'birth': 1996,
	'sex': 'm'
}
```

중괄호 `{}` 를 이용해 생성하며 `:` 과 `,`를 이용해 중괄호 내부에서 `키-값` 쌍을 설정할 수 있습니다.
 `{}` 대신 `new Object()` 생성자 사용도 가능하며, 인덱스 접근자 `[i]`를 사용하여 `키-값`을 설정할 수도 있습니다.

```javascript
var me = {};
me['name'] = 'young';
me['birth'] = 1996;
me['sex'] = 'm';
```

프로퍼티 접근자 `.`을 사용하여 `property`(키)에 접근할 수도 있습니다.

```javascript
var me = { 'name': 'young' };
me.birth = 1996;
```



# 제어문

## 개요

JavaScript의 제어문은 `C`, `Java` 등의 언어와 매우 유사합니다.
 제어문 사용법에 대해서 간단히 알아봅시다.

- 조건문
  - if
  - else if
  - else
  - switch
- 반복문
  - for
  - while
  - for in



# 조건문

## 개요

JavaScript의 조건문은 `C`, `Java` 등의 언어와 매우 유사합니다.
 간단한 예제를 보며 알아나갑시다.



## if, else 문

```javascript
if (score >= 90)
	alert('A');
else if (score >= 80)
	alert('B');
else if (score >= 70)
	alert('C');
else
	alert('F');
```



## switch 문

```javascript
switch (n) {
	case 0 :
	    // action 0
		break;

	case 1 :
	    // action 1
		break;
}
```



## 비교 연산자

| `a < b`  | a가 b보다 작다        |
| -------- | --------------------- |
| `a > b`  | a가 b보다 크다        |
| `a == b` | a와 b가 같다          |
| `a != b` | a와 b가 같지 않다     |
| `a >= b` | a가 b보다 크거나 같다 |
| `a <= b` | a가 b보다 작거나 같다 |

## 논리 연산자

| `&&` | AND 연산자 |
| ---- | ---------- |
| `||` | OR 연산자  |
| `!`  | NOT 연산자 |

## boolean

자바스크립트에서 비교 연산의 결과나 논리 연산은 0과 1이 아닌, `boolean` 타입으로 이루어집니다.
 `boolean`타입에서는 `true`와 `false`가 존재하는데, 0을 boolean으로 형변환하면 `false`가 되고 1을 형변환하면 `true`가 되기 때문에 조건문 내부에 0이나 1을 넣어도 정상적으로 동작합니다.

몇 가지 값 이외에는 **boolean** 형변환시 모두 `true`가 됩니다.
 `false`가 되는 특정한 값들은 아래가 있습니다.

- `0`
- `""` (빈 문자열)
- `undefined`
- `null`
- `NaN`



# 반복문

## 개요

JavaScript의 반복문 또한 `C`, `Java` 등의 언어와 매우 유사합니다.



## while문

```javascript
var i = 0;
while (true) {
	document.write(i);
	if (++i > 5) break;
}
```



## for문

```javascript
var colors = ['red', 'blue', 'green'];
for (var i = 0; i < 3; i++) {
	document.write( colors[i] );
}
```



## for in문

배열이나 객체의 경우, 내부 값을 간단한 방법으로 순회할 수 있습니다.

```javascript
var colors = ['red', 'blue', 'green'];
var me = {
	'name': 'young',
	'birth': 1996,
	'sex': 'm'
}

document.write('<h3>colors</h3>');

for (var i in colors)
	document.write( i + ': ' + colors[i] + '<br>' );

document.write('<h3>me</h3>');

for (var i in me)
	document.write( i + ': ' + me[i] + '<br>' );
```

출력 결과

```
colors
0: red
1: blue
2: green
me
name: young
birth: 1996
sex: m
```



# 함수

## 개요

[자료형 강의](https://ofcourse.kr/js-course/자료형)에서 말했듯 자바스크립트의 함수는 리턴 타입을 명시하지 않습니다.
 `function` 키워드로 함수를 선언하며, 괄호 안에서 매개변수를 받으며(변수 타입은 명시하지 않음), `return`을 통해 값을 반환합니다.

```javascript
function plus(a, b) {
	var sum = a + b;
	return sum;
}
```

**호출**시에는 함수 이름과 괄호를 사용하여 호출합니다.

```javascript
plus(3, 5);
```



## 함수의 범위

JavaScript 함수 내부에서 선언한 변수는 외부에서 참조할 수 없습니다.
 함수안에 함수 선언또한 가능한데, 이 경우에도 외부의 함수에서 내부의 변수를 참조할 수는 없습니다.

```javascript
function myFunc() {
	var myVar = 10;
	return a;
}
console.log( myFunc() ); // 10
console.log( myVar ); // undefined
function outerFunc() {
	var outerVar = 'a';

	function innerFunc() {
		var innerVar = 'b';
		console.log(outerVar); // a
		console.log(innerVar); // b
	}
	innerFunc();

	console.log(outerVar); // a
	console.log(innerVar); // undefined
}

outerFunc();
```



## first-class object

JavaScript에서 함수는 일반적인 언어와 달리 **독특한 점**을 가지고 있습니다.

> JavaScript에서 함수는 first-class object(citizen)로 취급된다.

`first-class object`란 다음과 같은 조건을 만족하는 객체를 말합니다.

- 변수나 데이터 구조안에 담을 수 있다.
- 파라미터로 전달 할 수 있다.
- 반환값(return value)으로 사용할 수 있다.
- 할당에 사용된 이름과 관계없이 고유한 구별이 가능하다.
- 동적으로 프로퍼티 할당이 가능하다.

자바스크립트에서 일반적으로 사용되는 `객체 변수`는 모두 `first-class object`라고 볼 수 있는데요, **함수**조차 이와 같은 객체로 취급됩니다.

이러한 특성으로 인해 자바스크립트에서 함수 선언을 다음처럼 할 수도 있습니다.

```javascript
var plusFunc = function (a, b) {
	return a + b;
};
// 익명의 함수를 만들고 plusFunc 변수에 대입

plusFunc(3, 5);
```

아래처럼 함수에서 함수 자체를 반환하는 것도 가능합니다.

```javascript
function a(x) {
	var b = function(y) {
		return x + y;
	};
	return b;
}

a(3)(4); // 7 반환
```

함수를 객체의 프로퍼티로도 사용할 수도 있습니다.

```javascript
var jacob = {
	'age': 18,
	'run': function() {
		alert('Jacob is running');
	},
	'sleep': function() {
		alert('Jacob sleeps');
	},
};
```



## 즉시 실행 함수

자바스크립트는 원래 네임스페이스 개념이 없습니다.
 하지만 함수의 클로저(범위) 개념과 first-class object 개념을 이용하면 비슷한 효과를 낼 수 있습니다.

```javascript
(function () {
	var a = 10;
	var b = 20;
	console.log(a + b); // 30
})();

console.log(a); // undefined
```

위와 같은 특이한 코드는 익명 함수를 만들고 즉시 실행하게 하는 코드입니다.
 위 코드를 길게 풀면 다음처럼 쓸 수 있습니다.

```javascript
var tempFunction = (function() {
	var a = 10;
	var b = 20;
	console.log(a + b); // 30
});
tempFunction();
```

변수에 할당해서 재실행하지 않고, 즉각적으로 함수를 바로 실행한 것이라 볼 수 있는데요,
 변수나 함수를 외부에서 접근하지 못하게 숨기거나 충돌을 방지하기 위해 많이 사용되는 기법입니다.

즉시 실행 함수에서 긴 변수를 호출 인자로 넘기고 파라미터를 짧게 쓴다면 다음처럼 코드를 줄이는 효과도 낼 수 있습니다.

```javascript
(function (b) {
	b.getElementById('content');
	...

})(document.body);
```



# DOM

## 개요

> 문서 객체 모델(DOM; Document Object Model)은 객체 지향 모델로써 구조화된 문서를 표현하는 형식이다. DOM은 플랫폼/언어 중립적으로 구조화된 문서를 표현하는 W3C의 공식 표준이다.

동적으로 `HTML` 문서의 내용, 구조, 스타일에 접근하고 변경하는 수단으로, 같은 코드가 여러 브라우저에서 동일하게 동작하도록 설립된 표준입니다.

- [QuerySelector](https://ofcourse.kr/js-course/QuerySelector) (제어 대상 찾기)
- [문서 객체의 속성](https://ofcourse.kr/js-course/문서-객체의-속성)
- [innerHTML 속성](https://ofcourse.kr/js-course/innerHTML-속성)
- [style 속성](https://ofcourse.kr/js-course/style-속성)
- [jQuery](https://ofcourse.kr/js-course/jQuery)



# QuerySelector

## 개요

JavaScript에서 **제어 대상**(문서 객체)를 찾기 위해서 몇 가지 함수를 제공합니다.
 적절한 제어 대상을 정하고 함수 호출을 하면 객체를 반환하게 되는데, 이 객체의 속성과 메소드를 통해 자바스크립트에서 문서를 동적으로 처리할 수 있습니다.

| 함수 이름                                                    | 요약                  | 반환값       | IE 지원 |
| ------------------------------------------------------------ | --------------------- | ------------ | ------- |
| [`getElementById`](https://ofcourse.kr/js-course/QuerySelector#getelementbyid) | `id` 속성으로 검색    | 객체(한개)   | IE 7+   |
| [`getElementsByClassName`](https://ofcourse.kr/js-course/QuerySelector#getelementsbyclassname) | `class` 속성으로 검색 | 배열(여러개) | IE 9+   |
| [`getElementsByTagName`](https://ofcourse.kr/js-course/QuerySelector#getelementsbytagname) | 태그 이름으로 검색    | 배열(여러개) | IE 7+   |
| `querySelector`                                              | jQuery-유사 셀렉터    | 객체(한개)   | IE 8+   |
| `querySelectorAll`                                           | jQuery-유사 셀렉터    | 배열(여러개) | IE 8+   |

위 함수들은 글로벌 함수가 아닌 `객체 모델`에서 사용할 수 있는 함수입니다.
 즉, `getElementById` 함수로 이미 찾은 객체 내부에서 다시 `getElementsByClassName` 함수를 사용하는 식이죠.

문서 전체에서 객체를 찾을 시에는 `document` 전역 변수를 사용합니다.

querySelector 함수와 querySelectorAll 함수는 [jQuery 강의](https://ofcourse.kr/js-course/jQuery)를 참고 바랍니다.

### 사용법

```javascript
var mytext1 = document.getElementById('mytext1');
var containers = document.getElementsByClassName('container');
var images = document.getElementsByTagName('img');
var mytext1_1 = document.querySelector('#mytext1');
var containers = document.querySelector('.container');
```



## getElementById

### 예제

```html
<input id="mytext1" type="text">

<script type="text/javascript">
	var myText1 = document.getElementById('mytext1');
	myText1.value = Math.random();
</script>
```

출력 결과

```
0.045139199581478495
```



## getElementsByClassName

### 예제

```html
<input class="rand-text" type="text">
<input class="rand-text" type="text">
<input class="rand-text" type="text">

<script type="text/javascript">
	var randTexts = document.getElementsByClassName('rand-text');

	for (var i = 0; i < randTexts.length; i++)
		randTexts[i].value = Math.random();

</script>
```



## getElementsByTagName

### 예제

```html
<select id="my-selector">
	<option></option>
	<option></option>
	<option></option>
</select>

<script type="text/javascript">
	var mySelector = document.getElementById('my-selector');
	var mOptions = mySelector.getElementsByTagName('option');

	for (var i = 0; i < mOptions.length; i++)
		mOptions[i].innerHTML = Math.round(Math.random() * 10000);

</script>
```



# 문서 객체의 속성

## 개요

`querySelector`를 이용해 찾은 객체에서 몇 가지 속성을 통해 `DOM`을 제어할 수 있습니다.
 대표적으로 `value` 속성을 사용하면 `HTML`태그의 value 속성을 지정하는 것과 같은 효과를 냅니다.

태그마다 사용할 수 있는 속성은 다르며 공통적이며 자주 사용되는 속성으로는 아래가 있습니다.

| 속성        | 설명             | 예시                            |
| ----------- | ---------------- | ------------------------------- |
| `id`        | id값             | if (myBox.id == “my-box”)       |
| `className` | class값          | myBox.className = “box red-box” |
| `tagName`   | 태그의 이름      | if (myBox.tagName == “div”)     |
| `value`     | value값          | nameInputArea = “홍길동”        |
| `style`     | style 오브젝트   | myBox.style.color = “red”       |
| `innerHTML` | 태그 내부 HTML값 | myBox.innerHTML = “ok”          |



## 사용자 정의 속성 제어

태그마다 특징적인 속성은 바로 제어할 수 있습니다. `<a>` 태그의 경우 `href` 속성을 사용할 수 있죠.

하지만 사용자가 임의로 태그에 속성을 부여하거나, 임의로 부여된 속성에 접근하기 위해서는 메소드를 통해 접근해야합니다.

아래 4개 메소드를 사용하여 사용자 정의 속성을 제어할 수 있습니다.

- `getAttribute(name)`
- `setAttribute(name, value)`
- `hasAttribute(name)`
- `removeAttribute(name)`

#### 예제

```javascript
var user = document.getElementById("user");
var monster = document.getElementById("monster");
user.setAttribute("HP", 100);
user.style.left = "50px";

if (parseInt(user.style.left) < parseInt(monster.style.left))
  user.setAttribute("HP",  user.getAttribute("HP") - 10);
```



# innerHTML 속성

## 개요

[QuerySelector](https://ofcourse.kr/js-course/QuerySelector)로 가져온 도큐먼트 오브젝트의 내용이나, 내부 HTML 코드를 JavaScript 코드에서 변경 할 수 있습니다.

## 예제

```html
<html>
<head>
	<script type= "text/javascript">
		function innerHTMLTest(){
			var randValNode = document.getElementById("rand-val");
			randValNode.innerHTML = "<b><font color='red'>"+Math.random()+"</font></b>";
		}
	</script>
</head>
<body>
	<div id="rand-val">Let's generate random Value</div>
	<button onclick="innerHTMLTest()">Generate</button>
</body>
</html>
```



## outerHTML

`innerHTML`과 비슷하지만, 선택된 노드를 포함하여 HTML를 수정합니다.



## innerText & outerText

`HTML` 코드가 그대로 입력되지 않으며, 텍스트로 입력됩니다.

### 예제

```html
<script type="text/javascript">
	function innerTextTest(target) {
		target.innerText = '<strong>click</strong>';
	}
</script>
<button onclick="innerTextTest(this)">Click This</button>
```



## 기타

`HTML`문자열을 그대로 삽입하는 방법 대신 `createElement` 등의 함수로 도큐먼트 객체를 만들고 `insertBefore` 등의 함수로 노드 객체 안에 다른 노드 객체를 삽입하며 `객체지향`처럼 사용하는 방법도 있습니다.



# style 속성

## 개요

HTML에서 [style 속성](https://ofcourse.kr/html-course/태그의-속성#style-속성)을 이용하면 CSS의 속성을 태그에 직접 설정할 수 있었습니다.
 JavaScript에서도 비슷하게 [QuerySelector](https://ofcourse.kr/js-course/QuerySelector)로 가져온 노드에서 `style`속성을 참조하며 CSS 속성을 적용시킬 수 있습니다.

`CSS`에서 여러 단어로 이어진 속성은 `-`로 구분되었는데(ex. background-color), `JavaScript`에서는 `-`를 사용하지 않고 다음 단어를 대문자로 사용하며(Camel Case) 접근합니다. (ex. backgroundColor)

## 사용법

```javascript
var box = document.getElementById("box");
box.style.backgroundColor = "red";
```

## 예제

```html
<html>
<head>
	<script type="text/javascript">
		function changeColor() {
			var box = document.getElementById("box");
			var r = parseInt(Math.random() * 256);
			var g = parseInt(Math.random() * 256);
			var b = parseInt(Math.random() * 256);

			box.style.backgroundColor = 'rgb(' + r + ',' + g + ',' + b + ')';
		}
	</script>
	<style>
		#box{
			cursor: pointer;
			width:80px;
			height:80px;
			background-color:#ccc;
		}
	</style>
</head>
<body>
	<div>click to change color</div>
	<div id="box" onclick="changeColor()"></div>
</body>
</html>
```



# jQuery

## 개요

`JavaScript`는 하나의 기업에서 혼자 만들고, 혼자 사용 환경까지 배포해주는 언어가 아닙니다.
 표준과 규약이 있고 빠르게 발전하고 있기는 하지만 규약이 제대로 정해지기 전 나왔던 구형 브라우저 (Internet  Explorer 9 이하)에서는 JavaScript 함수를 지원하지 않는 것이 있어 코드가 제대로 동작하지 않는 경우도 많습니다.

`jQuery`는, 이런 브라우저 **호환성**을 해결하고 자주 사용하는 코드를 **재사용**할 수 있도록 만들어진 라이브러리입니다.
 상당히 많은 웹사이트가 현재도 `jQuery`를 사용하고 있습니다.

[jQuery 공식 홈페이지](https://jquery.com/)

이 강의에서 상세하게 다루진 않을 예정이며, JavaScript 작업이 많이 필요한 홈페이지에는 jQuery 사용을 권장드립니다.

## 사용법

```javascript
var spans = $('span'); // 태그이름이 div인 객체 찾기
var box = $('#my-box'); // id가 'my-box'인 객체 찾기
var cb = $('.common-button'); // class가 'common-button'인 객체 찾기

box.html('<strong>content</strong>'); // innerHTML 속성 유사 함수
box.css('background-color', '#ff3300'); // css 적용 (style 속성 유사)
```

## 예제

```html
<html>
<head>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript">
		$(window).ready(function () {
			var box = $('#box');
			box.on('click', function () {
				box.css('width', box.innerWidth() + 20 + 'px');
				box.css('height', box.innerHeight() + 20 + 'px');
			});
		});
		
	</script>
	<style>
		#box{
			cursor: pointer;
			width:80px;
			height:80px;
			background-color:#666;
		}
	</style>
</head>
<body>
	<div>click to exapand</div>
	<div id="box"></div>
</body>
</html>
```



# 이벤트

## 개요

프로그래밍 언어에서 이벤트란 사용자의 동작이나 프로그램상 어떤 일에 의해 발생한 사건을 의미합니다.
 버튼을 클릭하거나 웹페이지가 로드되거나 하는 사건들이 있습니다.

아래는 대표적인 몇 가지 이벤트의 목록입니다.

| 이벤트 이름 | 설명                                       |
| ----------- | ------------------------------------------ |
| change      | 변동이 있을시 발생                         |
| click       | 클릭시 발생                                |
| focus       | 포커스를 얻었을때 발생                     |
| keydown     | 키를 눌렀을때 발생                         |
| keyup       | 키에서 손을 땟을때 발생                    |
| load        | 로드가 완료 되었을때 발생                  |
| mousedown   | 마우스를 클릭 했을때 발생                  |
| mouseout    | 마우스가 특정 객체 밖으로 나갔을 때 발생   |
| mouseover   | 마우스가 특정 객체 위로 올려졌을 시에 발생 |
| mousemove   | 마우스가 움직였을 때 발생                  |
| mouseup     | 마우스에서 손을 땟을때 발생                |
| select      | option 태그 등에서 선택을 했을때 발생      |



## 이벤트 제어

`while(true)`문을 이용해 항시적으로 이벤트를 감지하는 방식이 아닌,
 이벤트에 함수를 등록(바인딩)해두면 이벤트가 발생했을 때, 함수가 실행되는 방식을 통해 이벤트를 제어합니다.

이벤트에 함수를 등록하는 방법으로는 크게 2가지 방법이 있습니다.

- `HTML`상에서 `on` 속성을 이용하여 등록
- `JavaScript`에서 Listener을 사용하여 등록

# 태그의 on 속성

## 개요

`HTML`상에서 이벤트 발생시 실행될 코드나 함수를 바로 등록할 수 있습니다.

```html
<input type="button" value="button" onclick="alert('Hello world')">
```

위는 버튼을 클릭했을 때 “Hello world” 경고창을 띄우는 코드입니다.
 `onclick` 속성은 클릭되었을시 발생되는 이벤트를 의미합니다.

속성 안에서 `this`라는 변수도 사용이 가능한데, 이는 태그 자신을 나타내는 변수입니다.

## 예제

```html
<html>
<head>
	<script type="text/javascript">
		function m_over(target) {
			target.style.color="red";
		}

		function m_out(target) {
			target.style.color="blue";
		}
	</script>
</head>

<body>
	<button id="my-btn" onmouseover="m_over(this)" onmouseout="m_out(this)">
        Mouse Over it!
    </button>
</body>
</html>
```



## DOM의 on 속성

`HTML` 태그에서 바로 속성을 기술할 수도 있지만, [QuerySelector](https://ofcourse.kr/js-course/QuerySelector)를 통해 가져온 객체에서도 on 속성을 사용할 수 있습니다.

### 예제

```html
<button id="my-btn2">Button2</button>

<script type="text/javascript">
	var myBtn2 = document.getElementById("my-btn2");
	myBtn2.onclick = function() {
		alert("hello world");
	};
</script>
```



# EventListener

## 개요

`on 속성` 을 이용하여 이벤트를 등록시에는 이벤트 당 하나의 함수밖에 등록할 수 밖에 없습니다.
 `EventListener`를 이용하면 여러 이벤트를 등록할 수 있으며, 가장 권장되는 방식입니다.

- `addEventListener(eventType, listenerFunction)`
- `removeEventListener(eventType, listenerFunction)`
- `dispatchEventListener(event)`

## 사용법

```javascript
var myBtn = document.getElementById("my-btn");

// 클릭 이벤트 등록 (익명 함수)
myBtn.addEventListener("click", function (e) {
	alert("MyBtn Clicked");
});

function mbMouseOverHandler(e) {
	alert("MyByn MouseOvered");
	myBtn.removeEventListener("mouseover", mbMouseOverHandler); // 이벤트 삭제
}

// 마우스 오버 이벤트 등록
myBtn.addEventListener("mouseover", mbMouseOverHandler);
```



## Internet Explorer 예외처리

`addEventListener` 등의 함수는 IE9 이상부터 지원합니다. 
 IE8은 `attachEvent` 함수가 존재하며, 그 이하 버전은 `onclick` 등의 속성을 사용해야합니다.

```javascript
function addEventListener(obj,evt,func){
	if ('addEventListener' in window)
		obj.addEventListener(evt,func, false);
	else if ('attachEvent' in window)
		obj.attachEvent('on'+evt,func);
    else
    	obj["on"+evt] = func;
}
```

위와 같은 함수로 대체할 수 있으나, IE 구버전 고려가 필요한 웹사이트라면 `jQuery`를 사용하는 것이 가장 편하며 안정적입니다.



# jQuery 이벤트

## 개요

구형 브라우저에서 이벤트 처리는 상당히 까다로운 반면, `jQuery`를 사용하면 이벤트 처리도 간단하게 할 수 있습니다.

`on`이라는 메소드를 통해 바로 이벤트 등록이 가능합니다.

```javascript
$('#some-object').on('click', function(event){
	alert('jQuery event!');
})
```

## 예제

```html
<button id="my-btn">My Button</button>

<script type="text/javascript">
	$('#my-btn').on('mouseover', function (event) {
		$('#my-btn').css('color', '#ee6633');
	});

	$('#my-btn').on('mouseout', function (event) {
		$('#my-btn').css('color', '#333');
	});

	$('#my-btn').on('mousedown', function (event) {
		$('#my-btn').css('color', '#3333ee');
	});

</script>
```



# 클래스

## 개요

JavaScript에서는 정식적으로 클래스를 지원하지 않았습니다.
 ([ECMAScript 6](https://ofcourse.kr/js-course/관련기술#ecmascript-6) 부터 새로 생김)

대신 **함수**나 객체 리터럴을 이용하여, 클래스와 비슷하게 사용을 할 수 있었는데요,
 [자바스크립트 클래스를 정의하는 3가지 방법](http://steadypost.net/post/lecture/id/13/) 포스팅을 보시면 방법을 알 수 있습니다.

## 예제

```html
<html>
<head>
	<script type="text/javascript">
		var RandUtil = (function() {
			return function (posVal, negVal, probability) {
				this.posVal = posVal;
				this.negVal = negVal;
				this.probability = probability;

				this.getRandVar = function() {
					if (Math.random() > probability)
						return this.posVal;
					else
						return this.negVal;
				};
			};

		})();

		function sampleEvent(){
			var randUtil = new RandUtil("당첨", "꽝", 0.5);
			alert( randUtil.getRandVar() );
		}
	</script>
</head>
<body>
	<button onclick="sampleEvent()">복권</button>
</body>
</html>
```



# 관련기술

## Node.js

![NodeJS](./images/220px-Node.js_logo.svg.png)

브라우저에서 동작하는 JavaScript를 서버 사이드에서 동작할 수 있게 하는 기술입니다.
 비동기적으로 처리한다는 특징을 가지며, V8(Chrome에서 사용하는 자바스크트립트 엔진) 위에서 동작합니다.

웹 서버로도 사용되고 있습니다.



## AngularJS

![AngularJS](./images/AngularJS-large.png)

**구글**에서 만든 JavaScript MVC(Model-View-Controller) 프레임워크입니다.
 방대한 클라이언트 코드를 효율적으로 관리하며 동작시키기 위해 만들어졌으며, 현재 1.x 버전과 2.x 버전 나뉘어서 사용되고 있습니다.



## ReactJS

![ReactJS](./images/helloworld-1523-logo-react.png)

AngularJS와 비교되는 **프레임워크**로, 페이스북에서 만들었습니다.
 현재 가장 주목받고 있는 기술이며, React Native를 사용하면 JavaScript로 모바일 애플리케이션 또한 제작할 수 있습니다.

## ECMAScript 6

2015년 공표된 JavaScript의 최신 표준 명세이며, 줄여서 **ES6**라고도 부릅니다.
 기존 자바스크립트에서 문제점이라 지적 되었던 것들, 혹은 필요하다고 생각되는 문법이 많이 추가되어 더 편리한 언어로 거듭되었습니다.

구형 브라우저에서는 바로 사용할 수 없으며, 구형 코드로 변환하는 과정이 필요합니다.
 NodeJS나 Chromium App, Electron 등에서는 바로 사용할 수 있습니다.

