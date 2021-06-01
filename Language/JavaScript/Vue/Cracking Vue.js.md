# Cracking Vue.js

출처: https://joshua1988.github.io/vue-camp/textbook.html



이 사이트는 Vue.js로 웹 서비스를 개발할 때 필요한 지식을 모아놓은 사이트입니다. [기본적인 자바스크립트 개념](https://joshua1988.github.io/vue-camp/js/object.html)부터 [Vue.js 기본](https://joshua1988.github.io/vue-camp/vue/instance.html), [실무 지식](https://joshua1988.github.io/vue-camp/reuse/slots.html)까지 모두 정리해 놓았습니다.

Vue.js로 재밌게 개발하시길 바랍니다 😄

이 사이트는 인프런의 [Vue.js 온라인 강의](https://www.inflearn.com/course/Age-of-Vuejs) 교안으로도 활용됩니다 😄

### [입문](https://joshua1988.github.io/vue-camp/textbook.html#📖-입문)📖 입문

- [자바스크립트 기본](https://joshua1988.github.io/vue-camp/js/object.html)
- [ES6](https://joshua1988.github.io/vue-camp/es6/const-let.html)
- [Vue.js 기본](https://joshua1988.github.io/vue-camp/vue/instance.html)

### [중급](https://joshua1988.github.io/vue-camp/textbook.html#📖-중급)📖 중급

- [Vue.js 실용 문법](https://joshua1988.github.io/vue-camp/syntax/methods.html)
- [재사용성을 높이는 개발 방법](https://joshua1988.github.io/vue-camp/reuse/slots.html)
- [레거시 프로젝트에서의 Vue.js 사용법](https://joshua1988.github.io/vue-camp/legacy/jquery-to-vue.html)
- [Vuex를 이용한 상태 관리](https://joshua1988.github.io/vue-camp/vuex/concept.html)

### [고급](https://joshua1988.github.io/vue-camp/textbook.html#📖-고급)📖 고급

- [실무에서 알아야 하는 Vue.js 테크닉](https://joshua1988.github.io/vue-camp/advanced/folder-structure.html)
- [고급 컴포넌트 디자인 패턴](https://joshua1988.github.io/vue-camp/design/pattern1.html)
- [테스트 도구와 테스트 방법](https://joshua1988.github.io/vue-camp/testing/overview.html)
- [애플리케이션 배포 환경 변수 파일](https://joshua1988.github.io/vue-camp/deploy/intro.html)

### [기타](https://joshua1988.github.io/vue-camp/textbook.html#📖-기타)📖 기타

- [Typescript](https://joshua1988.github.io/vue-camp/ts/intro.html)
- [Nuxt](https://joshua1988.github.io/vue-camp/nuxt/intro.html)
- [VuePress](https://joshua1988.github.io/vue-camp/vuepress/learning-note.html)



## <span style="color:blue">**Javascript Fundamentals**</span> 

# 변수(Variable)

## [#](https://joshua1988.github.io/vue-camp/js/variable.html#소개)소개

프로그래밍 언어에서 변수는 가장 기본적인 개념입니다. 프로그래밍 로직의 대부분의 처리는 변수를 기반으로 한다고 해도 과언이 아닙니다. 단순한 덧셈 등의 사칙 연산부터 시작하여 복잡한 함수의 계산까지 모두 변수가 관여합니다.

자바스크립트에서 변수는 아래와 같이 정의할 수 있습니다.

```js
var a;
```

위는 `a`라는 변수를 선언한 코드입니다. 변수를 선언하고 아무 값도 할당하지 않으면 기본적으로 `undefined` 라는 초기값을 가집니다.

## [#](https://joshua1988.github.io/vue-camp/js/variable.html#변수에-값-할당하기)변수에 값 할당하기

일반적으로 변수를 선언하고 나면 변수 안에 원하는 값을 정의할 수 있습니다.

```js
var a;
a = 10;
```

위 코드는 `a`라는 변수를 정의하고 이 변수에 `10`이라는 숫자를 할당한 코드입니다. 다른 프로그래밍 언어와 달리 자바스크립트가 입문자에게 좋은 이유는 위와 같이 변수의 타입(종류)을 정의하지 않아도 코드를 실행할 때 자동으로 타입이 결정된다는 점입니다.

아래와 같이 유연하게 코드의 값을 바꿀 수 있습니다.

```js
var a;
a = 10;
console.log(a); // 10

a = 'hi';
console.log(a); // hi

a = false;
console.log(a); // false
```



## [#](https://joshua1988.github.io/vue-camp/js/variable.html#변수의-타입-확인하기-typeof)변수의 타입 확인하기 - typeof

앞에서 본 것처럼 `a`라는 변수의 값은 숫자, 문자열, 진위 값 순서로 변경되었습니다. 여기서는 다행히 할당한 값을 쉽게 구분할 수 있었지만 실제로 코딩하다보면 아래와 같이 쉽게 타입을 추측하기가 어렵습니다.

```html
<input value="100" />
var divElement = document.querySelector('input').value;
console.log(divElement);
```

이 때 `divElement` 변수의 타입을 확인할 수 있는 방법은 아래와 같습니다.

```js
console.log(typeof divElement);
```

이처럼 변수의 타입은 typeof 라는 예약어로 확인할 수 있습니다.

> 예약어 : 언어에서 미리 지정해놓은 키워드, 단어



# [#](https://joshua1988.github.io/vue-camp/js/string.html#문자열-string)문자열(String)

문자열은 변수의 여러 타입 중 하나입니다. 일반적으로 스트링이라고도 부르며 아래와 같이 선언합니다.

```js
var a = 'hello';
```

위 코드는 `a`라는 변수에 `hello`라는 문자열을 할당한 코드입니다. 문자열은 이와 같이 작은 따옴표(') 또는 큰 따옴표(")를 이용하여 정의할 수 있습니다.

## [#](https://joshua1988.github.io/vue-camp/js/string.html#숫자와의-구분)숫자와의 구분

자바스크립트는 코드를 실행하는 시점에 변수의 타입을 결정하는 언어입니다. 이러한 특징이 초심자들에게는 편하지만 아래와 같이 타입을 헷갈리게 하는 경우가 생깁니다.

```js
var a = 10;
var b = '10';
console.log(a); // 10
console.log(b); // 10
```

위 코드에서 변수 `a`는 숫자고 변수 `b`는 문자열입니다. 콘솔 로그 코드를 이용하여 콘솔에 변수의 값을 출력해보면 육안으로는 구분하기가 어렵다는 사실을 알 수 있습니다.

여기서 `typeof`를 이용하여 변수의 타입을 확인할 수도 있겠지만 실무 관점에서는 아래와 같은 방법으로 구분할 수 있습니다.

```js
console.log(a.length); // undefined
console.log(b.length); // 2
```

여기서 `length` 라는 예약어는 자바스크립트에서 변수의 타입에 따라 제공하는 기능입니다. length는 문자열, 배열의 길이를 숫자 형태로 확인할 수 있습니다. 따라서 `b`는 문자열이고 `a`는 숫자임을 추측할 수가 있습니다.

위와 같이 타입에 따라 기본적으로 제공되는 예약어 및 기능들을 자바스크립트 내장 함수(Javascript Built-in API / Javascript Native API)라고 합니다. 내장 함수에 대해서는 [prototype](https://joshua1988.github.io/vue-camp/js/prototype.html)에서 자세히 설명하겠습니다.



# [#](https://joshua1988.github.io/vue-camp/js/object.html#object)Object

자바스크립트는 객체 기반 언어입니다. 객체는 키(key) - 값(value) 형태로 이루어져 있으며 아래와 같은 형태를 갖습니다.

```js
var obj = {
  // 객체 내용
};
```

위 코드는 obj라는 변수에 객체를 새로 할당한 코드입니다. 여기서 `{}`라는 기호가 객체를 의미하며 이를 객체 리터럴이라고 부릅니다. 일반적으로 객체를 생성할 때는 객체 리터럴을 사용하여 위와 같은 방식으로 선언합니다.

## [#](https://joshua1988.github.io/vue-camp/js/object.html#속성-추가)속성 추가

객체를 생성하고 나면 아래와 같은 방식으로 속성(property)를 추가할 수 있습니다.

```js
// 객체 정의
var obj = {};

// num 속성을 추가하고 숫자 10을 할당
obj.num = 10;
```

위와 같은 방법 이외에도 아래와 같이 속성을 추가할 수 있습니다.

```js
// 객체 정의
var obj = {};

// num 속성을 추가하고 숫자 20을 할당
obj['num'] = 20;
```

## [#](https://joshua1988.github.io/vue-camp/js/object.html#속성-값-변경)속성 값 변경

이미 정의한 속성을 변경하는 방법은 해당 속성을 다시 접근하여 값을 할당하는 것입니다.

```js
// 객체 정의
var obj = {};

// num 속성을 추가하고 숫자 10을 할당
obj.num = 10;

// num 속성의 값에 숫자 20을 다시 할당
obj.num = 20;
```



# [#](https://joshua1988.github.io/vue-camp/js/array.html#배열-array)배열(Array)

배열(Array)은 객체와 더불어 실제로 웹 애플리케이션을 구현할 때 가장 많이 쓰이는 변수 타입입니다. 배열을 선언하는 방식은 객체와 유사합니다.

```js
var arr = [];
```

여기서 `[]`를 배열 리터럴이라고 하며 배열을 정의할 때 사용합니다.

## [#](https://joshua1988.github.io/vue-camp/js/array.html#배열의-요소)배열의 요소

객체는 `속성 / 값`의 조합으로 데이터를 저장하지만 배열은 `인덱스 / 값`의 조합으로 데이터를 저장합니다.

```js
// arr 변수에 빈 배열을 선언
var arr = [];

// 배열의 0번째 인덱스에 10을 대입
arr[0] = 10;

console.log(arr); // [10]
```

배열의 인덱스는 0부터 시작합니다. 빈 배열에 최초로 값을 추가하면 0번째 인덱스에 값이 추가됩니다.

## [#](https://joshua1988.github.io/vue-camp/js/array.html#배열-조작하기)배열 조작하기

배열을 조작하는 방법은 아래와 같이 직접 인덱스에 접근해서 조작하는 방법이 있습니다.

```js
// 인덱스를 직접 접근해서 사용하는 경우
var arr = [];
arr[0] = 100;
arr[1] = 20;
arr[0] = 10;
console.log(arr); // [10, 20]
```

하지만 위와 같이 인덱스에 직접 접근해서 조작하는 것보다 자바스크립트 내장 API를 사용하는 것을 추천합니다. 그리고 이 방식은 뷰 개발자 뿐만 아니라 자바스크립트 개발자들에게도 권장되는 방식입니다.

```js
// 자바스크립트 내장 API를 사용하는 경우
var arr = [];
arr.push(100); // [100]
arr.push(20); // [100, 20]
arr.splice(0, 1, 10); // [10, 20]
console.log(arr); // [10, 20]
```

## [#](https://joshua1988.github.io/vue-camp/js/array.html#자주-사용하는-배열-api)자주 사용하는 배열 API

배열을 조작할 때 주로 사용하는 API는 다음과 같습니다.

- [push()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/push) : 배열에 데이터 추가 (맨 끝 인덱스부터 추가됨)
- [slice()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/slice) : 배열의 특정 인덱스에 있는 값을 반환 (배열의 내용이 변환되지 않음)
- [splice()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/splice) : 배열의 특정 인덱스에 있는 값을 변경 또는 삭제 (배열의 내용이 변경됨)
- [pop()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/pop) : 배열의 마지막 인덱스의 값을 꺼냄 (배열의 내용이 변경됨)
- [shift()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/shift) : 배열의 첫번째 인덱스의 값을 꺼냄 (배열의 내용이 변경됨)



# [#](https://joshua1988.github.io/vue-camp/js/loop.html#반복문-loop)반복문(Loop)

반복문은 특정 로직을 반복할 때 사용하는 문법입니다. 프로그래밍 언어에서 가장 흔하게 활용되는 문법입니다. 아래는 전형적인 for 반복문을 자바스크립트 코드로 표현해봤습니다.

```js
for (var i = 0; i < 10; i++) {
  console.log(i); // 0부터 9까지 출력
}
```

앞에서 살펴본 for 반복문의 기본 문법은 다음과 같습니다.

```js
for (반복할 변수 초기화, 반복 조건, 반복할 변수의 증감식) {
  // 반복할 연산
}
```

## [#](https://joshua1988.github.io/vue-camp/js/loop.html#자바스크립트의-반복문)자바스크립트의 반복문

자바스크립트에도 위와 같은 반복문을 많이 사용할까요? 정답은 No입니다. 최적의 성능 튜닝을 위해서 고의로 일반 for문을 사용하는 경우를 제외하고는 다음과 같은 반복문을 주로 사용합니다.

- `forEach` 반복문
- `for in` 반복문
- `for of` 반복문

그럼 위의 문법들을 하나씩 살펴보겠습니다.

## [#](https://joshua1988.github.io/vue-camp/js/loop.html#foreach)forEach()

forEach 반복문은 배열에 사용하기 좋은 반복문입니다. 아래 코드를 보겠습니다.

```js
var arr = [10, 20, 30];
arr.forEach(function(value, index) {
  console.log('array index: ' + index + ' value : ' + value);
});
```

위 코드의 결과는 아래와 같습니다.

```text
array index: 0 value : 10
array index: 1 value : 20
array index: 2 value : 30
```

## [#](https://joshua1988.github.io/vue-camp/js/loop.html#for-in)for in

for in 반복문은 배열과 객체에 모두 사용할 수 있는 반복문입니다. 뷰의 v-for 디렉티브의 모티브가 되는 문법입니다. 아래와 같은 코드가 있다고 합시다.

```js
var arr = [10, 20, 30];
var obj = {
  num: 10,
  str: 'hi',
  arr: [],
};
```

위 배열과 객체를 for in 반복문으로 순회하면 아래와 같습니다.

```js
// 배열의 인덱스를 1개씩 접근하여 순차적으로 순회하는 경우
for (var key in arr) {
  console.log(arr[key]);
}

// 객체의 키를 1개씩 접근하여 순차적으로 순회하는 경우
for (var key in obj) {
  console.log(obj[key]);
}
```

각 코드의 결과는 아래와 같습니다.

```js
// 배열 반복문의 콘솔
10
20
30

// 객체 반복문의 콘솔
10
hi
[]
```



# [#](https://joshua1988.github.io/vue-camp/js/operator.html#연산자-operator)연산자(Operator)

연산자는 프로그래밍 로직을 구현할 때 논리식이나 산술식을 표현하기 위해 사용하는 기호들을 의미합니다. 주로 코드에서 흔하게 마주치는 아래와 같은 기호들을 의미합니다.

- 산술 연산자 : `+`, `-`, `*`, `/`, `%`
- 논리 연산자 : `||`, `&&`
- 조건 연산자 : `? :`
- 관계 연산자 : `>`, `<`, `===`

> 각 연산자의 의미에 대해서는 MDN 문서 또는 자바스크립트 기초 책을 참고하세요.

## [#](https://joshua1988.github.io/vue-camp/js/operator.html#연산자의-활용-1-변수-초기화)연산자의 활용 1 - 변수 초기화

논리 연산자를 이용해 변수 초기화 하는 방법에 대해서 알아보겠습니다. 일반적으로 변수의 초기화는 다음과 같은 방법으로 많이 합니다.

```js
function fetchData(data) {
  var receivedData;
  if (data === undefined) {
    receivedData = localStorage.getItem('item');
  }
}
```

함수의 인자로 넘어온 값을 if 문으로 확인한 뒤 추가 로직을 실행하는게 대부분의 초기화 문법입니다. 여기서 논리 연산자를 활용하면 아래와 같이 깔끔하게 코드를 구현할 수 있습니다.

```js
function fetchData(data) {
  var receivedData;
  receivedData = data || localStorage.getItem('item');
}
```

## [#](https://joshua1988.github.io/vue-camp/js/operator.html#연산자의-활용-2-조건문-대신-삼항-연산자)연산자의 활용 2 - 조건문 대신 삼항 연산자

로직을 구현하다가 보면 if 문을 중첩해서 활용하는 경우가 많습니다. 아래와 같이 말이죠.

```js
if (data !== undefined) {
  num = 50;
  if (num > 10) {
    num = 100;
  } else {
    num = 0;
  }
}
```

이럴 때 if 문 대신 삼항 연산자를 활용하면 더 코드를 간결하게 짤 수 있습니다.

```js
if (data !== undefined) {
  num = 50;
  num = num > 10 ? 100 : 0;
}
```



# [#](https://joshua1988.github.io/vue-camp/js/function.html#function)Function

함수란 특정 기능을 수행하는 코드의 단위입니다. 좀 더 쉽게 설명하자면 코드 여러 줄이 모여 있는 코드 모음 정도가 되겠네요. 이 코드의 모음은 반드시 1개 이상의 목적이 있어야 합니다.

함수는 아래와 같이 생겼습니다.

```js
function sumNumbers(a, b) {
  return a + b;
}
```

위의 `sumNumbers` 라는 함수는 두 개의 숫자를 넘겨 받아 합을 반환해주는 역할을 하고 있습니다. 사용하려면 아래와 같이 호출합니다.

```js
sumNumbers(10, 20); // 30
```

## [#](https://joshua1988.github.io/vue-camp/js/function.html#함수-표현식과-함수-선언문)함수 표현식과 함수 선언문

다른 프로그래밍 언어와 다르게 자바스크립트는 함수를 선언하는 방법이 2가지가 있습니다.

- 함수 표현식
- 함수 선언문

각각의 형식을 코드로 살펴보겠습니다.

```js
// 함수 표현식
var sumNumbers = function(a, b) {
  return a + b;
};

// 함수 선언문
function sumNumbers(a, b) {
  return a + b;
}
```

일반적으로 많은 프로그래밍 가이드에서 함수 선언문을 더 추천하고 있지만, 개인 스타일에 따라서 얼마든지 선택하여 사용할 수 있습니다.

>  함수 표현식과 같이 함수를 정의할 수 있는 이유는 자바스크립트에서는 함수도 변수나 인자로 취급할 수 있기 때문입니다. [일급 객체(first-class citizens)](https://developer.mozilla.org/en-US/docs/Glossary/First-class_Function)라는 특징에 대해서 알아보시면 좋을 것 같네요!



## [#](https://joshua1988.github.io/vue-camp/js/function.html#함수형-사고와-함수형-프로그래밍)함수형 사고와 함수형 프로그래밍

함수를 작성하실 때는 가급적 [단일 책임 원칙(Single Responsibility Principle)](https://en.wikipedia.org/wiki/Single_responsibility_principle)을 지켜주시면 좋습니다. 단일 책임 원칙이란 1개의 함수는 1개의 기능만 담당해야 한다는 프로그래밍 원칙입니다. 함수에 여러 가지 기능이 들어가면 들어갈수록 재사용하기가 어려워지며 이는 Vue.js 컴포넌트를 설계할 때도 사고 방식에 영향을 줄 수 있습니다.

단일 책임 원칙의 관점에서 잘 설계된 함수와 그렇지 않은 함수를 비교해보겠습니다.

```js
// 잘 설계된 함수
function sumNumbers(a, b) {
  return a + b;
}
```

위 함수는 함수 명에서도 알 수 있지만 두 수의 합을 구하는 간단한 함수입니다. 두 개의 숫자를 더하는 행위에만 관심을 가지고 있고, 덧셈 로직을 제외한 나머지 코드는 넣지 않았습니다.

아래는 같은 함수 명으로 다른 로직을 수행하는 함수입니다.

```js
// 단일 책임 원칙에 벗어나는 함수
function sumNumbers(a, b) {
  var num = 1000;
  var data = {};

  $.get('domain.com/products/1').then(function(response) {
    data = response.data;
  });

  var total = a + b + num;
  return total;
}
```

위 함수의 이름은 `sumNumbers`라는 함수의 이름을 갖고 있지만, 실제로 두 수를 더하는 로직 이외에도 데이터 요청이나 다른 숫자를 더하는 로직들이 뒤엉켜 있습니다. 위와 같은 코드는 단일 책임 원칙에 벗어나는 코드이며 재사용하기가 쉽지 않습니다.

좀 더 나아가서 앞의 함수를 단일 책임 원칙의 관점에서 리팩토링 해보면 아래와 같이 바뀔 수 있습니다.

```js
// 함수 리팩토링
function sumNumbers(a, b) {
  return a + b;
}

function sumAll() {
  var num = 1000;
  var total = sumNumbers(0, 0) + num;
  return total;
}

function fetchData() {
  var data = {};
  $.get('domain.com/products/1').then(function(response) {
    data = response.data;
    return data;
  });
}
```

위와 같은 함수 설계에 관심을 갖다 보면 자연스럽게 함수형 프로그래밍에 대해 관심을 갖게 됩니다. 함수형 프로그래밍을 하기 위해서는 클로져(closure)라는 개념을 정확히 이해해야 하기 때문에 클로져 챕터에서 간략히 소개하겠습니다.



# 유효 범위(Scope)

스코프란 변수의 유효 범위를 의미합니다. 다른 프로그래밍 언어와는 다르게 자바스크립트의 변수는 유효 범위가 전역으로 시작합니다. 예를 들어 아래와 같은 변수 선언은 자바스크립트로 접근할 수 있는 모든 영역에서 같은 값을 같습니다.

```js
var a = 10;
```

만약 함수를 만들어 아래와 같이 접근하더라도 동일한 값을 출력하게 됩니다.

```js
var a = 10;

function getA() {
  console.log(a);
}

getA(); // 10
```

## [#](https://joshua1988.github.io/vue-camp/js/scope.html#함수-단위로-구분되는-변수-범위)함수 단위로 구분되는 변수 범위

기본적으로 변수의 유효 범위는 전역 범위를 갖는다고 하지만, 함수 안에서 새로 선언하는 경우 함수 단위의 새로운 유효 범위를 갖습니다. 아래 코드를 보겠습니다.

```js
var a = 10;

function getA() {
  var a = 20;
  console.log(a);
}

getA(); // 20
console.log(a); // 10
```

위 코드는 함수 바깥에서 변수 `a`를 선언하고 10을 대입한 뒤, `getA()`라는 함수를 선언하면서 함수 안에 변수 `a`를 새로 선언하고 20을 대입한 코드입니다. `getA()` 함수를 실행하면 함수 안의 변수인 `a`가 20의 값으로 콘솔에 출력됩니다. 함수의 실행이 끝나고 나서 `console.log(a);`로 다시 `a`의 값을 출력하면 10이 출력됩니다.

여기서 변수의 유효 범위는 함수 단위(function)로 한정된다는 것을 알 수 있습니다.



# this

this는 함수의 실행 컨텍스트를 가리키는 예약어입니다. 여기서 실행 컨텍스트는 사전적인 정의로 '**함수가 실행되는 환경**'이며 좀 더 쉽게 접근하기 위해서는 '**함수가 실행될 때의 컨텍스트**'로 이해하시는 게 좋겠습니다.

다른 언어와 다르게 자바스크립트의 this는 상황에 따라 다른 값들을 가르킵니다. 이 때문에 자바스크립트가 조금 어렵게 느껴지는 부분도 있습니다.

여기서는 this가 가장 많이 활용되는 상황들에 대해서 알아보겠습니다.

## [#](https://joshua1988.github.io/vue-camp/js/this.html#첫-번째-this)첫 번째 this

아래의 코드를 브라우저 콘솔에서 실행하면 어떻게 될까요?

```js
console.log(this); // window
```

this의 가장 기본적인 컨텍스트는 **글로벌(전역) 컨텍스트**입니다. 여기서 출력된 window는 자바스크립트의 최상위 객체를 가리킵니다.

## [#](https://joshua1988.github.io/vue-camp/js/this.html#두-번째-this)두 번째 this

아래와 같은 객체가 있다고 가정하겠습니다.

```js
var obj = {
  num: 10,
  printNum: function() {
    console.log(this.num);
  }
};
```

여기서 `obj.printNum()`을 실행하면 어떻게 될까요?

```js
obj.printNum(); // 10
```

**객체 속성 함수 안에서의 this는 기본적으로 해당 객체를 가리킵니다.**

## [#](https://joshua1988.github.io/vue-camp/js/this.html#세-번째-this)세 번째 this

세 번째로 살펴볼 this는 먼저 [함수 선언문](https://joshua1988.github.io/vue-camp/js/this.html#함수-선언문), [생성자 함수](https://joshua1988.github.io/vue-camp/js/this.html#생성자-함수)를 알아야 합니다. 혹시 아직 개념이 낯선 분들은 링크를 클릭해서 간단하게 살펴보시고 계속 읽어나가시는 것을 추천드립니다.

자 그럼 일반 함수(함수 선언문)에서의 this는 어떤 것을 가리킬까요?

```js
function showComment() {
  console.log(this);
}
```

위 함수를 아래와 같이 실행시키면 `window` 객체를 가리키고 있다는 것을 알 수 있습니다. 결론적으로 **일반 함수의 this는 전역 컨텍스트입니다.**

```js
showComment(); // window
```

그럼 이번엔 생성자 함수의 this를 확인해보겠습니다.

```js
function Developer() {
  console.log(this);
}
var dev = new Developer();
```

위 코드는 실행하자마자 바로 아래와 같은 결과를 콘솔에 출력합니다.

```js
Developer {}
```

그 이유는 `new`로 인스턴스를 생성하는 순간 함수가 실행되기 때문입니다. 그리고 여기서 알 수 있는 사실은 **생성자 함수의 this는 함수의 내부를 가리킨다는 것**입니다.

## [#](https://joshua1988.github.io/vue-camp/js/this.html#네-번째-this)네 번째 this

네 번째로 살펴볼 this는 실제로 웹 개발을 할 때 가장 많이 마주하게 되는 this입니다. 바로 데이터를 받아올 때 사용하는 HTTP 요청과 같은 비동기 처리 코드입니다.

```js
function fetchData() {
  axios.get('domain.com/products').then(function() {
    console.log(this);
  });
}
```

위 함수를 실행하면 어떤 결과가 나타날까요? 정답은 window입니다.

```js
fetchData(); // window
```

기본적으로 HTTP 요청과 같은 비동기 처리 코드는 전역 컨텍스트를 갖습니다. 정리해서 **비동기 처리 코드의 콜백 함수는 전역 컨텍스트를 가리킨다**고 보면 되겠습니다.

## [#](https://joshua1988.github.io/vue-camp/js/this.html#부록)부록

위 본문을 이해하는데 도움이 되는 자료들입니다.

### [#](https://joshua1988.github.io/vue-camp/js/this.html#함수-선언문)함수 선언문

함수 선언문은 아래와 같은 함수 정의 방식을 의미합니다.

```js
// 함수 선언문
function functionName() {
  // ...
}
```

### [#](https://joshua1988.github.io/vue-camp/js/this.html#생성자-함수)생성자 함수

그리고 생성자 함수는 아래와 같이 함수를 이용해 새 인스턴스를 선언하는 함수를 의미합니다.

```js
function Developer() {
  // ...
}
var dev = new Developer();
```

> 자바스크립트는 프로토타입 기반 언어입니다. 클래스 기반 언어가 아니기 때문에 위와 같이 함수를 이용하여 인스턴스를 생성할 수 있습니다. 자바 개발자 분들은 클래스가 없다고 당황하지 마세요 😃



# 클로져(Closure)

클로져는 **함수의 실행이 끝난 뒤에도 함수에 선언된 변수의 값을 접근할 수 있는 자바스크립트의 성질**입니다. 자바스크립트를 다른 언어와 비교했을 때 차별화되는 유일한 특징인데요. 코드로 바로 살펴보겠습니다.

```js
function addCounter() {
  var counter = 0;

  return function() {
    return counter++;
  };
}
```

위 코드는 `addCounter()`라는 함수를 하나 생성하고 `counter` 변수를 하나 선언한 코드입니다. 앞 챕턴에서 배운 [유효 범위(스코프)](https://joshua1988.github.io/vue-camp/js/scope.html)를 상기시켜보면 `counter`라는 변수는 현재 함수 안에 선언되어 있기 때문에 함수 안에서만 유효한 유효 범위를 갖게 됩니다. 아래와 같이 말이죠.

```js
function addCounter() {
  var counter = 0;
}

addCounter();
console.log(counter); // Uncaught ReferenceError: counter is not defined
```

함수 밖에서 `counter` 변수를 참조하려고 하면 오류가 발생됩니다. 그 이유는 함수 밖에서 `counter`라는 변수가 선언된 적이 없기 때문입니다. 자 그럼 이 관점에서 다시 클로져 코드로 돌아가보겠습니다.

```js
function addCounter() {
  var counter = 0;

  return function() {
    return counter++;
  };
}
```

`counter` 변수 다음으로 주목할 부분은 함수를 반환하는 부분(코드 강조된 부분)입니다. 여기서 이렇게 함수를 반환할 수 있는 이유는 '**함수를 변수나 인자로 넘길 수 있는 자바스크립트의 성질(일급 객체)**' 때문입니다.

자 그럼 이제 아래와 같이 `addCounter()` 함수를 실행해보겠습니다.

```js
addCounter();
```

위의 결과는 무엇일까요? 콘솔에 한번 찍어보겠습니다.

```js
console.log(addCounter());
```

출력된 결과는 아래와 같습니다.

```text
ƒ () {
    return counter++;
  }
```

여기서 갸우뚱 하시는 분들은 다시 한번 `addCounter()` 함수 코드를 살펴보시기 바랍니다. 애시당초 `addCounter()` 함수의 역할은 `addCounter()` 함수를 실행했을 때 함수를 반환하는 것 이었습니다.

그럼 이제 반환된 함수를 살펴보면 `counter++`라는 코드가 보일 겁니다. 그리고 그 변수를 아래와 같이 접근하면 당연히 또 오류가 납니다.

```js
function addCounter() {
  var counter = 0;

  return function() {
    return counter++;
  };
}

addCounter();
console.log(counter); // Uncaught ReferenceError: counter is not defined
```

코드를 찬찬히 살펴보면 `addCounter()`함수의 실행이 끝난 시점에서는 `counter`라는 변수는 더이상 접근할 수 없는 상태가 됩니다. **함수 안에 선언한 변수는 함수 안에서만 유효 범위를 갖게 때문이죠**.

자 그럼 이제 아래 코드를 실행해보겠습니다.

```js
function addCounter() {
  var counter = 0;

  return function() {
    return counter++;
  };
}

var add = addCounter();
add(); // 0
add(); // 1
add(); // 2
```

위와 같이 코드를 실행했을 때 동작하는 이유가 무엇일까요? 그건 바로 `addCounter()`라는 함수가 반환한 함수를 `add`라는 변수에 담아놨기 때문에 `add` 변수 자체가 함수처럼 동작하는 것입니다. 기술 용어로 정확히 표현하자면 "**add 변수가 addCounter()가 반환한 함수를 참조하고 있다**" 입니다.

이처럼 함수의 실행이 끝나고 나서도 함수 안의 변수를 참조할 수 있는게 바로 클로져입니다. 이러한 패턴을 응용하면 자바스크립트에 없는 private 변수나 함수형 프로그래밍을 할 수 있습니다.

## [#](https://joshua1988.github.io/vue-camp/js/closure.html#함수형-프로그래밍)함수형 프로그래밍

함수형 프로그래밍이란 특정 기능을 구현하기 위해서 **함수의 내부 로직은 변경하지 않은 상태로 여러 개의 함수를 조합하여 결과 값을 도출하는 프로그래밍 패턴**을 의미합니다. 커링(currying)이 함수형 프로그래밍의 대표적인 예입니다. 코드로 보겠습니다.

```js
function add(num1, num2) {
  return num1 + num2;
}

function curry(fn, a) {
  return function(b) {
    return fn(a, b);
  };
}

var add3 = curry(add, 3);
add3(4); // 7
```

위와 같이 클로져를 활용하면 함수를 조합하여 기능을 구현해나갈 수 있습니다.



## <span style="color:blue">**ES6**</span>

# const & let

const와 let 예약어는 ES6에서 사용하는 변수 선언 방식입니다.

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#let)let

let 예약어는 한번 선언하면 다시 선언할 수 없습니다.

```js
// 똑같은 변수를 재선언할 때 오류
let a = 10;
let a = 20; // Uncaught SyntaxError: Identifier 'a' has already been declared
```

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#const)const

const 예약어는 한번 할당한 값을 변경할 수 없습니다.

```js
// 값을 다시 할당했을 때 오류
const a = 10;
a = 20; // Uncaught TypeError: Assignment to constant variable.
```

단, 객체 `{}`또는 배열 `[]`로 선언했을 때는 객체의 속성(property)과 배열의 요소(element)를 변경할 수 있습니다.

```js
// 객체로 선언하고 속성 값을 변경
const b = {
    num: 10,
    text: 'hi'
};
console.log(b.num); // 10

b.num = 20;
console.log(b.num); // 20
// 배열로 선언하고 배열 요소를 추가
const c = [];
console.log(c); // []

c.push(10);
console.log(c); // [10]
```

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#블록-유효범위)블록 유효범위

ES5의 var를 이용한 변수 선언 방식과 let & const를 이용한 변수 선언 방식의 가장 큰 차이점은 블록 유효범위입니다.

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#var의-유효-범위)var의 유효 범위

var의 유효 범위는 함수의 블록 단위로 제한됩니다. 흔히 함수 스코프(function scope)라고 표현합니다.

```js
var a = 100;
function print() {
    var a = 10;
    console.log(a);
}
print(); // 10
```

print 함수 앞에 선언한 `a`와 print 함수 안에 선언한 `a`는 각자 다른 유효 범위를 갖습니다. `var a = 100;` 는 자바스크립트 전역 객체인 window에 추가가 되고 `var a = 10;`는 `print()` 함수 안에서만 유효한 범위를 갖습니다.

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#for-반복문에서의-var-유효-범위)for 반복문에서의 var 유효 범위

위의 예제를 보다보면 흔히 헷갈릴 수 있는 부분이 "var의 유효 범위가 {}에 제한되나?" 입니다. 아래 예제를 살펴보겠습니다.

```js
var a = 10;
for (var a = 0; a < 5; a++) {
    console.log(a); // 0 1 2 3 4 5
}
console.log(a); // 6
```

`var a = 10;`로 변수 a를 선언한 상태에서 for 반복문에 동일한 변수 이름 a를 사용했습니다. 이렇게 되면 `{}` 으로 변수의 유효 범위가 제한되지 않기 때문에 for 반복문이 끝나고 나서 `console.log(a);` 를 출력하면 for 반복문의 마지막 결과 값이 출력됩니다. 아마 자바나 다른 언어의 개발자들에게는 이 부분이 가장 헷갈릴 것입니다.

이러한 문제점을 해결하고 다른 언어와 통일감을 주기 위해 ES6에서는 const와 let의 변수 유효 범위를 블록`{}`으로 제한하였습니다.

## [#](https://joshua1988.github.io/vue-camp/es6/const-let.html#const와-let의-블록-유효범위)const와 let의 블록 유효범위

이번엔 위 반복문 코드에 var 대신 let을 적용해보겠습니다.

```js
var a = 10;
for (let a = 0; a < 5; a++) {
    console.log(a); // 0 1 2 3 4 5
}
console.log(a); // 10
```

반복문의 조건 변수 a를 let으로 선언하니 변수의 유효 범위가 for 반복문의 {} 블록 안으로 제한되었습니다.



# 화살표 함수(Arrow Function)

화살표 함수(Arrow Function)는 ES6의 새로운 함수 정의 방식입니다.

## [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#기존의-함수-정의-방식)기존의 함수 정의 방식

기존 자바스크립트의 함수 정의 방식은 아래와 같습니다.

```js
var a = function() {
    // ...
};
```

## [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#화살표-함수를-이용한-함수-정의)화살표 함수를 이용한 함수 정의

화살표 함수를 이용한 함수 정의 방식은 아래와 같습니다.

```js
var a = () => {
    // ...
};
```

## [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#화살표-함수의-다양한-문법)화살표 함수의 다양한 문법

화살표 함수를 정의하는 방식은 간단한 자바스크립트 표현식부터 {} 를 이용한 선언 방식까지 여러 방법이 있습니다.

### [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#_1-단순한-자바스크립트-표현식)1. 단순한 자바스크립트 표현식

단순한 자바스크립트 표현식의 경우 아래와 같이 간소화 문법을 사용할 수 있습니다.

```js
() => 10 + 20; // {} 필요 없음
```

### [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#_2-함수-선언-방식)2. 함수 선언 방식

복잡한 자바스크립트 선언문이 들어갈 경우에는 {}를 사용하여 아래와 같이 구현합니다.

```js
() => {
    print();
    log();
    return 10 + 20;
};
```

### [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#_3-전달-인자-parameter-가-하나인-경우)3. 전달 인자(parameter)가 하나인 경우

인자를 1개만 선언하는 경우 인자를 받을 때 사용하는 소괄호() 를 생략할 수 있습니다.

```js
const a = (num) => { return num * 10 };
const b = num => num * 10;
a(10); // 100
b(10); // 100
```

## [#](https://joshua1988.github.io/vue-camp/es6/fat-arrow.html#this-바인딩의-변화)this 바인딩의 변화

업데이트 예정



# 향상된 객체 리터럴(Enhanced Object Literal)

향상된 객체 리터럴이란 기존 자바스크립트에서 사용하던 객체 정의 방식을 개선한 문법입니다. 자주 사용하던 문법들을 좀 더 간결하게 사용할 수 있도록 객체 정의 형식을 바꿨습니다.

## [#](https://joshua1988.github.io/vue-camp/es6/enhanced-object-literals.html#기존-객체-정의-방식)기존 객체 정의 방식

기존 자바스크립트의 객체 정의 방식은 다음과 같습니다.

```js
var josh = {
  // 속성: 값
  language: 'javascript',
  coding: function() {
    console.log('Hello World');
  }
};
```

## [#](https://joshua1988.github.io/vue-camp/es6/enhanced-object-literals.html#축약-문법-1-속성과-값이-같으면-1개만-기입)축약 문법 1 - 속성과 값이 같으면 1개만 기입

객체를 정의할 때 속성(property)와 값(value)이 같으면 아래와 같이 축약이 가능합니다.

```js
var language = 'javascript';

var josh = {
  // language: language,
  language
};

console.log(josh); // {language: "javascript"}
```

위와 같은 축약 문법을 뷰 컴포넌트 등록 방식과 뷰 라우터 등록 방식에 대입해보면 아래와 같습니다.

```js
// #1 - 컴포넌트 등록 방식에서의 축약 문법
const myComponent = {
  template: '<p>My Component</p>'
};

new Vue({
  components: {
    // myComponent: myComponent
    myComponent
  }
});
// #2 - 라우터 등록 방식에서의 축약 문법
const router = new VueRouter({
  // ...
});

new Vue({
  // router: router,
  router
});
```

## [#](https://joshua1988.github.io/vue-camp/es6/enhanced-object-literals.html#축약-문법-2-속성에-함수를-정의할-때-function-예약어-생략)축약 문법 2 - 속성에 함수를 정의할 때 function 예약어 생략

기존에 객체를 정의할 때 객체의 속성에 함수를 연결하여 사용하는 경우가 많았습니다. 예를 들면 아래와 같이 말이죠.

```js
const josh = {
  // 속성: 함수
  coding: function() {
    console.log('Hello World');
  }
};
josh.coding(); // Hello World
```

실제로 기능을 구현하다보면 위와 같이 정의해야 할 때가 너무 많습니다. 따라서, ES6에서는 아래와 같이 축약하여 코딩하는 것을 추천합니다.

```js
const josh = {
  coding() {
    console.log('Hello World');
  }
};
josh.coding(); // Hello World
```

이렇게 `function` 예약어를 생략해도 동일하게 동작하기 때문에 타이핑 하는 시간이 줄어듭니다.

그럼 이번에는 뷰 코드에 적용해서 얼마나 타이핑이 줄어드는지 확인해볼까요?

```js
new Vue({
  // ...
  methods: {
    fetchData: function() {
      // ...
    },
    showAlert: function() {
      // ...
    }
  }
});
```

위의 코드를 아래와 같이 생략할 수 있습니다.

```js
new Vue({
  // ...
  methods: {
    fetchData() {
      // ...
    },
    showAlert() {
      // ...
    }
  }
});
```



https://joshua1988.github.io/vue-camp/es6/enhanced-object-literals.html#%EA%B8%B0%EC%A1%B4-%EA%B0%9D%EC%B2%B4-%EC%A0%95%EC%9D%98-%EB%B0%A9%EC%8B%9D

