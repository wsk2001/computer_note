#  javascript 최적화 기술 34 선

출처: https://qiita.com/baby-degu/items/396edbaefea64140a5d0?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=21e8c4d370-Qiita_newsletter_467_06_23_2021&utm_medium=email&utm_term=0_e44feaa081-21e8c4d370-34467781

개발자의 생활이라는 것은 항상 새로운 것을 배움으로써 그 변화에 따라가는 것은 결코 어려운 일이 아닙니다. 나는 프런트 엔드 개발자로 알고 있어야하는 약어 나 기능과 같은 일부 JavaScript 모든 모범 사례를 소개하고, 2021 년의 생활을보다 쾌적하고 싶다고 생각하고 있습니다.

JavaScript 개발에 오랫동안 종사하고있는 사람도 코드를 추가하지 않아도 문제 해결 할 수있는 최신 기능을 모르는 것도 있을지도 모릅니다. 여기서 소개하는 것은 깨끗하고 최적화 된 JavaScript 코드 작성도 2021 년의 JavaScript 면접 준비에도 도움이됩니다.

이것은 새로운 시리즈로, 2021 년 버전의 JavaScript 코딩 치트 시트입니다.

### 1. 여러 조건을 가진 if

배열에 여러 값을 저장하고 includes 메소드를 사용합니다.

``` javascript
//longhand
if (x === 'abc' || x === 'def' || x === 'ghi' || x ==='jkl') {
   //logic
}

//shorthand
if (['abc', 'def', 'ghi', 'jkl'].includes(x)) {
   //logic
}
```



### 2. if true ... else 약어

If-else 조건에 대량의 논리가없는 경우는 매우 줄일 수 있습니다. 삼항 연산자를 사용뿐입니다.

``` javascript
// Longhand
let test: boolean;

if (x > 100) {
   test = true;
} else {
   test = false;
}

// Shorthand
let test = (x > 10) ? true : false;
//or we can use directly
let test = x > 10;

console.log(test);
```

중첩 된 조건은 다음과 같이합니다.

``` javascript
let x = 300,
test2 = (x > 100) ? 'greater 100' : (x < 50) ? 'less 50' : 'between 50 and 100';
console.log(test2); // "greater than 100"
```



### 3. 변수 선언

일반적인 값 또는 형태를 가지는 2 개의 변수를 선언 할 경우,이 약어를 사용합니다.

``` javascript
//Longhand
let test1;
let test2 = 1;

//Shorthand
let test1, test2 = 1;
```



### 4. null / 미정 / 빈 판정

새로운 변수를 만들 때 그 값을 참조하는 변수가 null 또는 undefined 아닌지를 판정 JavaScript를 뛰어난 약어입니다.

``` javascript
// Longhand
if (test1 !== null || test1 !== undefined || test1 !== '') {
   let test2 = test1;
}

// Shorthand
let test2 = test1 || '';
```



### 5. null 값의 판정 및 기본값 할당

``` javascript
let test1 = null,
    test2 = test1 || '';
console.log("null check", test2); // output will be ""
```



### 6. 정의되지 않은 값의 판정 및 기본값 할당

``` javascript
let test1 = undefined,
    test2 = test1 || '';
console.log("undefined check", test2); // output will be ""
```

정상치의 판정

``` javascript
let test1 = 'test',
    test2 = test1 || '';
console.log(test2); // output: 'test'
```

(덤 : 이제 위의 4,5,6라도 ?? 사용할 수 있습니다.)

#### 널 결합 미적분

Null 합체 연산자 ??은 왼쪽이 null이거나 정의되지 않은 경우 우변의 값을 돌려줍니다. 기본적으로 왼쪽의 값을 돌려줍니다.

``` javascript
const test= null ?? 'default';
console.log(test);
// expected output: "default"const test1 = 0 ?? 2;
console.log(test1);
// expected output: 0
```



### 7. 여러 변수에 값 할당

``` javascript
//Longhand
let test1, test2, test3;
test1 = 1;
test2 = 2;
test3 = 3;

//Shorthand
let [test1, test2, test3] = [1, 2, 3];
```



### 8. 대입 연산자의 약어

프로그래밍은 많은 산술 연산자를 처리합니다. 이것은 JavaScript 변수에 대입 연산자의 유용한 기술입니다.

``` javascript
// Longhand
test1 = test1 + 1;
test2 = test2 - 1;
test3 = test3 * 20;

// Shorthand
test1++;
test2--;
test3 *= 20;
```



### 9. if 의한 존재 확인의 약어

누구나 사용하는 일반적인 약어이지만, 여전히 언급 할 가치가 있습니다.

``` javascript
// Longhand
if (test1 === true) or if (test1 !== "") or if (test1 !== null)

// Shorthand //it will check empty string,null and undefined too
if (test1)
```

참고 : 만약 test1에 값이 있으면 if 루프 후에 로직에 들어갑니다. 이 연산자는 주로 null 또는 undefined의 확인에 사용됩니다.



### 10. 여러 조건의 AND (&&) 연산자

변수가 true 일 때만 함수를 호출하는 경우는 && 연산자를 사용합니다.

``` javascript
//Longhand
if (test1) {
   callMethod();
}

//Shorthand
test1 && callMethod();
```



### 11. foreach 루프

루프의 일반적인 약어입니다.

``` javascript
// Longhand
for (var i = 0; i < testData.length; i++)

// Shorthand
for (let i in testData) or for (let i of testData)
```

각 변수의 배열

``` javascript
function testData(element, index, array) {
   console.log('test[' + index + '] = ' + element);
}

[11, 24, 32].forEach(testData);
// logs: test[0] = 11, test[1] = 24, test[2] = 32
```



### 12. 비교 return

비교는 return 문에서 사용할 수 있습니다. 5 줄의 코드가 한 줄에 줄어 듭니다.

``` javascript
// Longhand
let test;

function checkReturn() {
   if (!(test === undefined)) {
      return test;
   } else {
      return callMe('test');
   }
}
var data = checkReturn();
console.log(data); //output test

function callMe(val) {
   console.log(val);
}

// Shorthand
function checkReturn() {
   return test || callMe('test');
}
```



### 13. 애로우 함수

예 1

``` javascript
//Longhand
function add(a, b) {
   return a + b;
}

//Shorthand
const add = (a, b) => a + b;
```

예 2

``` javascript
function callMe(name) {
   console.log('Hello', name);
}
callMe = name => console.log('Hello', name);
```



### 14. 짧은 함수 호출

삼항 연산자를 사용합니다.

``` javascript
// Longhand
function test1() {
   console.log('test1');
};
function test2() {
   console.log('test2');
};

var test3 = 1;
if (test3 == 1) {
   test1();
} else {
   test2();
}

// Shorthand
(test3 === 1? test1:test2)();
```



### 15. switch 약어

Key-value object에 조건을 저장하고 그 조건에 따라 사용합니다.

``` javascript
// Longhand
switch (data) {
   case 1:
      test1();
   break;
   case 2:
      test2();
   break;
   case 3:
      test();
   break;
   // And so on...
}

// Shorthand
var data = {
   1: test1,
   2: test2,
   3: test
};

data[something] && data[something]();
```



### 16. 암시 반환

애로우 함수를 사용하면 return 문없이 직접 값을 반환합니다.

``` javascript
//longhand
function calculate(diameter) {
   return Math.PI * diameter
}

//shorthand
calculate = diameter => (
   Math.PI * diameter;
)
```



### 17. 10 진수 기반 지수

``` javascript
// Longhand
for (var i = 0; i < 10000; i++) { ... }

// Shorthand
for (var i = 0; i < 1e4; i++) {
```



### 18. 매개 변수의 기본값

``` javascript
//Longhand
function add(test1, test2) {
   if (test1 === undefined)
      test1 = 1;
   if (test2 === undefined)
      test2 = 2;
   return test1 + test2;
}

//shorthand
add = (test1 = 1, test2 = 2) => (test1 + test2);

add() //output: 3
```



### 19. 스프레드 연산자

``` javascript
//longhand
// joining arrays using concat
const data = [1, 2, 3];
const test = [4 ,5 , 6].concat(data);

//shorthand
// joining arrays
const data = [1, 2, 3];
const test = [4 ,5 , 6, ...data];
console.log(test); // [ 4, 5, 6, 1, 2, 3]
```

스프레드 연산자는 복제에 사용할 수 있습니다.

``` javascript
//longhand
// cloning arrays
const test1 = [1, 2, 3];
const test2 = test1.slice()

//shorthand
// cloning arrays
const test1 = [1, 2, 3];
const test2 = [...test1];
```



### 20. 템플릿 리터럴

+로 여러 변수를 하나의 문자열로 연결하는 것이 귀찮은 경우 이 생략 형으로 편해집니다.

``` javascript
//longhand
const welcome = 'Hi ' + test1 + ' ' + test2 + '.'

//shorthand
const welcome = `Hi ${test1} ${test2}`;
```



### 21. 여러 줄의 문자열의 약어

코드에서 여러 줄의 문자열을 처리 할 때이 함수를 사용할 수 있습니다.

``` javascript
//longhand
const data = 'abc abc abc abc abc abc\n\t'
   + 'test test,test test test test\n\t'

//shorthand
const data = `abc abc abc abc abc abc
   test test,test test test test`
```



### 22. 개체 속성 할당

``` javascript
let test1 = 'a';
let test2 = 'b';

//Longhand
let obj = {test1: test1, test2: test2};

//Shorthand
let obj = {test1, test2};
```



### 23. 문자열 숫자로 변환

``` javascript
//Longhand
let test1 = parseInt('123');
let test2 = parseFloat('12.3');

//Shorthand
let test1 = +'123';
let test2 = +'12.3';
```



### 24. 분할 할당 약어

``` javascript
//longhand
const test1 = this.data.test1;
const test2 = this.data.test2;
const test2 = this.data.test3;

//shorthand
const { test1, test2, test3 } = this.data;
```



### 25. Array.find

개체의 배열에서 객체의 속성에 따라 특정 개체를 찾으려면, find 메서드는 매우 편리합니다.

``` javascript
const data = [{
      type: 'test1',
      name: 'abc'
   },
   {
      type: 'test2',
      name: 'cde'
   },
   {
      type: 'test1',
      name: 'fgh'
   },
]

function findtest1(name) {
   for (let i = 0; i < data.length; ++i) {
      if (data[i].type === 'test1' && data[i].name === name) {
         return data[i];
      }
   }
}

//Shorthand
filteredData = data.find(data => data.type === 'test1' && data.name === 'fgh');
console.log(filteredData); // { type: 'test1', name: 'fgh' }
```



### 26. 검색 조건 약어

형태를 체크하는 코드로, 형태에 따라 다른 메소드를 호출하면 여러 else if 나 switch 옵션이 있지만, 그보다 뛰어난 약어가 있습니다.

``` javascript
// Longhand
if (type === 'test1') {
   test1();
}
else if (type === 'test2') {
   test2();
}
else if (type === 'test3') {
   test3();
}
else if (type === 'test4') {
   test4();
} else {
   throw new Error('Invalid value ' + type);
}

// Shorthand
var types = {
   test1: test1,
   test2: test2,
   test3: test3,
   test4: test4
};
var func = types[type];
(!func) && throw new Error('Invalid value ' + type); func();
```



### 27. 비트 연산자에 의한 IndexOf 약어

배열을 반복하여 특정 값을 검색하면 indexOf () 메소드를 사용합니다. 더 좋은 방법의 예를 살펴 보자.

``` javascript
//longhand
if(arr.indexOf(item) > -1) { // item found }
if(arr.indexOf(item) === -1) { // item not found }

//shorthand
if(~arr.indexOf(item)) { // item found }
if(!~arr.indexOf(item)) { // item not found }
```

비트 단위 연산자
-1 이외의 값으로 실제 값을 반환합니다. 부정은 간단하고!
라고하면됩니다. Include () 함수도 사용할 수 있습니다.

``` javascript
if (arr.includes(item)) {
// true if the item found
}
```



### 28. Object.entries()

이 기능은 객체를 객체 배열로 변환하는 데 유용합니다.

``` javascript
const data = { test1: 'abc', test2: 'cde', test3: 'efg' };
const arr = Object.entries(data);
console.log(arr);

/** Output:
[ [ 'test1', 'abc' ],
  [ 'test2', 'cde' ],
  [ 'test3', 'efg' ]
]
**/
```



### 29. Object.values()

이것도 ES8에 도입 된 새로운 기능으로 Object.entries ()와 비슷하지만 키가 없습니다.

``` javascript
const data = { test1: 'abc', test2: 'cde' };
const arr = Object.values(data);
console.log(arr);

/** Output:
[ 'abc', 'cde']
**/
```



### 30. 더블 비트 단위의 약어

``` javascript
/ Longhand
Math.floor(1.9) === 1 // true

// Shorthand
~~1.9 === 1 // true
```



### 31. 문자열의 반복

같은 문자를 여러 번 반복하면 for 루프에서 같은 루프에 추가 할 수 있지만, 약어가 있습니다.

``` javascript
//longhand
let test = '';
for(let i = 0; i < 5; i ++) {
   test += 'test ';
}
console.log(str); // test test test test test

//shorthand
'test '.repeat(5);
```



### 32. 배열의 최대 값과 최소값을 가져오기

``` javascript
const arr = [1, 2, 3];
Math.max(...arr); // 3
Math.min(...arr); // 1
```



### 33. 문자열에서 문자를 가져오기

``` javascript
let str = 'abc';

//Longhand
str.charAt(2); // c

//Shorthand
str[2]; // c
```

주 : 배열의 인덱스를 알고있는 경우 문자 대신 직접 인덱스를 사용할 수 있습니다. 인덱스를 모르는 경우 정의되지 않은 오류가 발생합니다.



### 34. 지수 함수 약어

Math의 지수  함수의 약어입니다.

``` javascript
//longhand
Math.pow(2,3); // 8

//shorthand
2**3 // 8
```



### 결론

최신 JavaScript 기술로 코드를 최적화하는 34 가지 방법이었습니다.

