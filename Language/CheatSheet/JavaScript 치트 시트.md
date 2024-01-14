# JavaScript 치트 시트

JavaScript의 언어 기능에 관한 치트 시트입니다.

- 언어 기능
  - [코멘트](https://jsprimer.net/cheatsheet/#comments)
  - [데이터](https://jsprimer.net/cheatsheet/#data-structures)
  - [리터럴](https://jsprimer.net/cheatsheet/#literal)
  - [문자열](https://jsprimer.net/cheatsheet/#string)
  - [데이터 액세스](https://jsprimer.net/cheatsheet/#data-access)
  - [연산자](https://jsprimer.net/cheatsheet/#operator)
  - [함수와 거동](https://jsprimer.net/cheatsheet/#function-behavior)
  - [제어 흐름](https://jsprimer.net/cheatsheet/#control-flow)
  - [모듈](https://jsprimer.net/cheatsheet/#module)
  - [기타](https://jsprimer.net/cheatsheet/#miscellaneous)
- 가이드
  - [프로젝트 구조](https://jsprimer.net/cheatsheet/#project-anatomy)

## 언어 기능

### 코멘트

코멘트를 작성하는 방법에 대해.

| 코드 예        | 설명                    | 해설                                           | MDN                                                          |
| -------------- | ----------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| `// xxx`       | 한 줄 댓글              | [코멘트](https://jsprimer.net/basic/comments/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#コメント) |
| `/* xxx */`    | 여러 줄 주석            | [코멘트](https://jsprimer.net/basic/comments/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#コメント) |
| `<!-- xxx -->` | [ES2015] HTML-like 댓글 | [코멘트](https://jsprimer.net/basic/comments/) |                                                              |

### 데이터 구조

변수 선언에 대해.

| 코드 예   | 설명                                                         | 해설                                                 | MDN                                                          |
| --------- | ------------------------------------------------------------ | ---------------------------------------------------- | ------------------------------------------------------------ |
| `const x` | [ES2015] **변수 선언** . `x`값을 재 할당 할 수 없습니다.     | [변수와 선언](https://jsprimer.net/basic/variables/) | [const](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/const) |
| `let x`   | [ES2015] **변수 선언** . `const`비슷하지만 `x`값을 재 할당 할 수 있습니다. | [변수와 선언](https://jsprimer.net/basic/variables/) | [let](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/let) |
| `var x`   | **변수 선언** . 레거시 변수 선언 방법                        | [변수와 선언](https://jsprimer.net/basic/variables/) | [var](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/var) |

### 리터럴

데이터 구조를 표현하는 리터럴에 대해.

| 코드 예           | 설명                                                       | 해설                                                         | MDN                                                          |
| ----------------- | ---------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `true`또는`false` | **진위값**                                                 | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#論理値リテラル) |
| `123`             | **십진수 정수** 리터럴                                     | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `123n`            | [ES2020] 거대한 정수를 나타내는 BigInt 리터럴              | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `0b10`            | [ES2015] **2진수** 정수 리터럴                             | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `0o777`           | [ES2015] **8진수** 정수 리터럴                             | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `0x30A2`          | **16진수** 정수 리터럴                                     | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `123_456`         | [ES2021] 수치 리터럴의 **Numeric Separators**              | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#数値リテラル) |
| `{ k: v }`        | 속성 이름이 속성 `k`값인 **개체를** 만듭니다.`v`           | [개체](https://jsprimer.net/basic/object/)                   | [문법 및 데이터 형식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Grammar_and_types#object_literals) |
| `{ n }`           | [ES2015] 속성 이름이 `n`속성 값인 `n`개체 **를** 만듭니다. | [개체](https://jsprimer.net/basic/object/)                   | [객체 이니셜라이저](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Object_initializer#プロパティの定義) |
| `[x, y]`          | `x`및 `y`초기 값으로 **배열 객체를** 만듭니다.             | [배열](https://jsprimer.net/basic/array/)                    | [문법 및 데이터 형식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Grammar_and_types#array_literals) |
| `/pattern/`       | `pattern`로 **정규식 객체** 만들기                         | [문자열](https://jsprimer.net/basic/string/)                 | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#正規表現リテラル) |
| `null`            | `null`값                                                   | [데이터 유형 및 리터럴](https://jsprimer.net/basic/data-type/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#null_リテラル) |

### 문자열

문자열에 대해.

| 코드 예   | 설명                                                         | 해설                                         | MDN                                                          |
| --------- | ------------------------------------------------------------ | -------------------------------------------- | ------------------------------------------------------------ |
| `"xxx"`   | 더블 따옴표 **문자열 리터럴** . 개행 등은 `\`과 특정 문자를 조합한 이스케이프 시퀀스로서 표현합니다. | [문자열](https://jsprimer.net/basic/string/) | [문법 및 데이터 형식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Grammar_and_types#string_literals) |
| `'xxx'`   | 단일 따옴표 **문자열 리터럴** . `"xxx"`그리고 의미는 동일합니다. | [문자열](https://jsprimer.net/basic/string/) | [문법 및 데이터 형식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Grammar_and_types#string_literals) |
| ``xxx` `  | [ES2015] 템플릿 문자열 리터럴. 개행을 포함한 입력이 가능     | [문자열](https://jsprimer.net/basic/string/) | [템플릿 리터럴 (템플릿 문자열)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Template_literals) |
| ``${x}` ` | [ES2015] 템플릿 문자열 리터럴의 변수 `x`값 확장              | [문자열](https://jsprimer.net/basic/string/) | [템플릿 리터럴 (템플릿 문자열)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Template_literals) |

### 데이터 액세스

데이터에 대한 액세스 정보.

| 코드 예                | 설명                                                         | 해설                                       | MDN                                                          |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------ | ------------------------------------------------------------ |
| `array[0]`             | 배열에 대한 **인덱스 액세스**                                | [배열](https://jsprimer.net/basic/array/)  | [Array](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Global_Objects/Array#添字による配列の要素へのアクセス) |
| `obj["x"]`             | 객체에 대한 **속성 액세스** (브라켓 표기법)                  | [개체](https://jsprimer.net/basic/object/) | [속성 액세서](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Property_Accessors) |
| `obj.x`                | 객체에 대한 **속성 액세스** (점 표기법)                      | [개체](https://jsprimer.net/basic/object/) | [속성 액세서](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Property_Accessors) |
| `obj?.x`               | [ES2020] 개체에 대한 **속성 액세스** (Optional chaining 연산자) | [개체](https://jsprimer.net/basic/object/) | [선택적 체인 (?.)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Optional_chaining) |
| `const { x } = obj;`   | [ES2015] 객체 **분할 할당**                                  | [개체](https://jsprimer.net/basic/object/) | [분할 대입](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment) |
| `const [ x ] = array;` | [ES2015] 배열 **분할 대입**                                  | [배열](https://jsprimer.net/basic/array/)  | [분할 대입](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment) |
| `f(...array)`          | [ES2015] **Spread 구문** 에서 배열 배포                      | [배열](https://jsprimer.net/basic/array/)  | [스프레드 구문](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Spread_syntax) |
| `f({ ...obj })`        | [ES2018] **Spread 구문** 으로 개체 배포                      | [개체](https://jsprimer.net/basic/object/) | [스프레드 구문](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Spread_syntax) |

### 연산자

연산자에 대해.

| 코드 예 | 설명                               | 해설                                           | MDN                                                          |
| ------- | ---------------------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| `+`     | 더하기 연산자, 문자열 결합 연산자  | [연산자](https://jsprimer.net/basic/operator/) | [더하기 (+)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Addition) |
| `-`     | 마이너스 연산자                    | [연산자](https://jsprimer.net/basic/operator/) | [빼기 (-)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Subtraction) |
| `*`     | 곱셈 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [곱하기 (*)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Multiplication) |
| `/`     | 나눗셈 연산자                      | [연산자](https://jsprimer.net/basic/operator/) | [나눗셈 (/)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Division) |
| `%`     | 나머지 연산자                      | [연산자](https://jsprimer.net/basic/operator/) | [나머지(%)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Remainder) |
| `**`    | [ES2016] 거듭제곱 연산자           | [연산자](https://jsprimer.net/basic/operator/) | [거듭제곱 (**)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Exponentiation) |
| `++`    | 증분 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [증분](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Increment) |
| `--`    | 감소 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [감소 (--)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Decrement) |
| `===`   | 엄밀한 등가 연산자                 | [연산자](https://jsprimer.net/basic/operator/) | [엄밀한 등가 (===)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Strict_equality) |
| `!==`   | 엄밀한 부등가 연산자               | [연산자](https://jsprimer.net/basic/operator/) | [엄밀한 부등가 (!==)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Strict_inequality) |
| `==`    | 등가 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [등가(==)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Equality) |
| `!=`    | 부등가 연산자                      | [연산자](https://jsprimer.net/basic/operator/) | [부등가 (!=)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Inequality) |
| `>`     | 대형 연산자/보다 큰                | [연산자](https://jsprimer.net/basic/operator/) | [큰 (>)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Greater_than) |
| `>=`    | 매우 이콜 연산자 / 이상            | [연산자](https://jsprimer.net/basic/operator/) | [매우 이콜 (>=)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Greater_than_or_equal) |
| `<`     | 작음 연산자/작음                   | [연산자](https://jsprimer.net/basic/operator/) | [작음 (<)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Less_than) |
| `<=`    | 작고 이콜 연산자/이하              | [연산자](https://jsprimer.net/basic/operator/) | [작고 이콜 (<=)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Less_than_or_equal) |
| `&`     | 비트 논리 곱                       | [연산자](https://jsprimer.net/basic/operator/) | [비트 논리 곱 (&)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Bitwise_AND) |
| `|`     | 비트 논리합                        | [연산자](https://jsprimer.net/basic/operator/) | [비트 논리합(\|)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Bitwise_OR) |
| `^`     | 비트 배타적 논리합                 | [연산자](https://jsprimer.net/basic/operator/) | [비트 배타적 논리합(^)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Bitwise_XOR) |
| `~`     | 비트 부정                          | [연산자](https://jsprimer.net/basic/operator/) | [비트 부정 (~)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Bitwise_NOT) |
| `<<`    | 왼쪽 시프트 연산자                 | [연산자](https://jsprimer.net/basic/operator/) | [왼쪽 시프트(<<)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Left_shift) |
| `>>`    | 오른쪽 시프트 연산자               | [연산자](https://jsprimer.net/basic/operator/) | [오른쪽 시프트 (>>)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Right_shift) |
| `>>>`   | 제로 채우기 오른쪽 시프트 연산자   | [연산자](https://jsprimer.net/basic/operator/) | [부호없는 오른쪽 시프트 (>>>)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Unsigned_right_shift) |
| `&&`    | AND 연산자                         | [연산자](https://jsprimer.net/basic/operator/) | [논리 곱 (&&)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Logical_AND) |
| `??`    | [ES2020] Nullish coalescing 연산자 | [연산자](https://jsprimer.net/basic/operator/) | [Null 합체 연산자 (??)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Nullish_coalescing) |
| `||`    | OR 연산자                          | [연산자](https://jsprimer.net/basic/operator/) | [논리합 (\|\|)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Logical_OR) |
| `(x)`   | 그룹 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [그룹화 연산자 ()](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Grouping) |
| `x, y`  | 쉼표 연산자                        | [연산자](https://jsprimer.net/basic/operator/) | [쉼표 연산자(,)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Comma_Operator) |

### 함수와 거동

함수의 정의와 거동에 대해.

| 샘플                                  | 설명                                                         | 해설                                                         | MDN                                                          |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `function f(){}`                      | **함수** 선언                                                | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [함수 선언](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/function) |
| `const f = function(){};`             | **함수식** _                                                 | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [함수식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/function) |
| `const f = () => {};`                 | [ES2015] **Arrow Function** 선언                             | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [화살표 함수 표현](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Functions/Arrow_functions) |
| `async function f(){}`                | [ES2017] **Async Function** 선언                             | [비동기 처리](https://jsprimer.net/basic/async/)             | [비동기 함수](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/async_function) |
| `const f = async function(){};`       | [ES2017] 함수 식을 사용한 **Async Function** 선언            | [비동기 처리](https://jsprimer.net/basic/async/)             | [비동기 함수 식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/async_function) |
| `const f = async () => {};`           | [ES2017] Arrow Function을 사용한 **Async Function** 선언     | [비동기 처리](https://jsprimer.net/basic/async/)             | [비동기 함수 식](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/async_function) |
| `function f(x, y){}`                  | 함수에서 임시 인수 선언                                      | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [함수 선언](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/function) |
| `function f(x = 1, y = 2){}`          | **디폴트 인수** , 인수가 건네받지 않은 경우의 초기치를 지정한다. | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [기본 인수](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Functions/Default_parameters) |
| `function f([x, y]){}`                | [ES2015] 함수 인수에서 배열의 분할 **할당** . 인수의 배열로부터 인덱스가 `0`의 값을 `x`에, 인덱스가 `1`의 값을 `y`에 대입한다. | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [분할 대입](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#配列の分割代入) |
| `function f({ x, y }){}`              | [ES2015] 함수 인수에서 개체의 **분할 할당** . 인수 개체에서 `x`및 `y`속성을받습니다. | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [분할 대입](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#オブジェクトの分割代入) |
| `function f(...args)){}`              | [ES2015] **가변 길이 인수** / **Rest parameters** . 인수에 전달된 값을 배열로 수신 | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [잔여 인수](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Functions/rest_parameters) |
| `const o = { method: function(){} };` | **메소드 정의**                                              | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [메소드 정의](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Functions/Method_definitions#解説) |
| `const o = { method(){} };`           | [ES2015] **메소드 정의** 단축기법                            | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [메소드 정의](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Functions/Method_definitions#解説) |
| `class X{}`                           | [ES2015] **클래스** 선언                                     | [수업](https://jsprimer.net/basic/class/)                    | [클래스](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/class) |
| `const X = class X{};`                | [ES2015] **클래스** 식                                       | [수업](https://jsprimer.net/basic/class/)                    | [클래스 표현](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/class) |
| `class X{ method(){} }`               | [ES2015] 클래스의 **인스턴스 메서드** 정의                   | [수업](https://jsprimer.net/basic/class/)                    | [수업](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Classes#プロトタイプメソッド) |
| `class X{ get x(){}, set x(v){} }`    | [ES2015] 클래스의 **접근자 메서드** 정의                     | [수업](https://jsprimer.net/basic/class/)                    | [개체에서 작업](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Working_with_Objects#defining_getters_and_setters) |
| `class X{ property = 1; }`            | [ES2022] 클래스의 **Public 클래스 필드** 정의                | [수업](https://jsprimer.net/basic/class/)                    | [퍼블릭 클래스 필드](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Classes/Public_class_fields) |
| `class X{ #privateField = 1; }`       | [ES2022] 클래스의 **Private 클래스 필드** 정의               | [수업](https://jsprimer.net/basic/class/)                    | [프라이빗 클래스 기능](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Classes/Private_class_fields) |
| `class X{ static method(){} }`        | [ES2015] 클래스의 **정적 메서드** 정의                       | [수업](https://jsprimer.net/basic/class/)                    | [static](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Classes/static) |
| `class C extends P{}`                 | [ES2015] 클래스 **상속**                                     | [수업](https://jsprimer.net/basic/class/)                    | [extends](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Classes/extends) |
| `super`                               | [ES2015] **부모 클래스** 참조                                | [수업](https://jsprimer.net/basic/class/)                    | [슈퍼](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/super) |
| `fn()`                                | 함수 호출                                                    | [함수와 선언](https://jsprimer.net/basic/function-declaration/) | [기능](https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Functions#calling_functions) |
| `fn`xxx` `                            | [ES2015] 태그 함수 호출                                      | [문자열](https://jsprimer.net/basic/string/)                 | [템플릿 리터럴 (템플릿 문자열)](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Template_literals#タグ付きテンプレート) |
| `new X()`                             | `new`연산자의 클래스 ( 생성자 함수가있는 객체)에서 인스턴스 만들기 | [수업](https://jsprimer.net/basic/class/)                    | [new 연산자](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/new) |

### 제어 흐름

제어 흐름 제어 구문 정보.

| 예                                        | 설명                                                         | 해설                                                     | MDN                                                          |
| ----------------------------------------- | ------------------------------------------------------------ | -------------------------------------------------------- | ------------------------------------------------------------ |
| `while(x){}`                              | **while 루프** . `x`가라 `true`반복 처리                     | [루프 및 반복 처리](https://jsprimer.net/basic/loop/)    | [while](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/while) |
| `do{}while(x);`                           | **do...while 루프** . `x`이라면 `true`반복 처리를 행한다. `x`에 관계없이 반드시 처음에는 처리가 수행됩니다. | [루프 및 반복 처리](https://jsprimer.net/basic/loop/)    | [do...while](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/do...while) |
| `for(let x=0;x < y ;x++){}`               | **for 루프** . `x < y`가라 `true`반복 처리                   | [루프 및 반복 처리](https://jsprimer.net/basic/loop/)    | [for](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/for) |
| `for(const p in o){}`                     | **for...in 루프** . 객체 ( `o`)의 속성 ( `p`)에 반복 처리    | [루프 및 반복 처리](https://jsprimer.net/basic/loop/)    | [for...in](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/for...in) |
| `for(const x of iter){}`                  | [ES2015] **for...of 루프** . 반복자 ( `iter`) 반복 처리      | [루프 및 반복 처리](https://jsprimer.net/basic/loop/)    | [for...of](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/for...of) |
| `if(x){/*A*/}else{/*B*/}`                 | **조건식** . `x`A의 처리를, 그렇지 `true`않으면 B의 처리     | [조건 분기](https://jsprimer.net/basic/condition/)       | [if...else](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/if...else) |
| `switch(x){case "A":{/*A*/} "B":{/*B*/}}` | **switch 문** . `x`A의 처리 , `"A"`"B"의 경우 B의 처리       | [조건 분기](https://jsprimer.net/basic/condition/)       | [switch](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/switch) |
| `x ? A: B`                                | **조건 (삼항) 연산자** . `x`가라 나의 처리를, 그렇지 않으면의 `true`처리 를 실시한다`A``B` | [조건 분기](https://jsprimer.net/basic/condition/)       | [조건 (삼항) 연산자](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Operators/Conditional_Operator) |
| `break`                                   | **break 문** . 현재 반복 처리를 종료하고 루프에서 빠져 나간다. | [조건 분기](https://jsprimer.net/basic/condition/)       | [break](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/break) |
| `continue`                                | **continue 문** . 현재 반복 처리를 종료하고 다음 루프로 간다. | [조건 분기](https://jsprimer.net/basic/condition/)       | [continue](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/continue) |
| `try{}catch(e){}finally{}`                | `try...catch`구문                                            | [예외 처리](https://jsprimer.net/basic/error-try-catch/) | [try...catch](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/try...catch) |
| `throw new Error("xxx")`                  | `throw`문                                                    | [예외 처리](https://jsprimer.net/basic/error-try-catch/) | [throw](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/throw) |

### 모듈

ECMAScript 모듈 정보.

| 코드                              | 설명                                                         | 해설                                                  | MDN                                                          |
| --------------------------------- | ------------------------------------------------------------ | ----------------------------------------------------- | ------------------------------------------------------------ |
| `import x from "./x.js"`          | [ES2015] **기본 가져오기**                                   | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [가져오기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/import) |
| `import { x } from "./x.js"`      | [ES2015] **명명된 가져오기**                                 | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [가져오기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/import) |
| `import { x as y } from "./x.js"` | [ES2015] 명명된 가져오기 **별칭**                            | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [가져오기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/import) |
| `import * as x from "./x.js"`     | [ES2015] **모든 명명된 내보내기를 가져와** 별칭              | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [가져오기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/import) |
| `import "./x.js"`                 | [ES2015] 부작용을 위한 **가져오기**                          | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [가져오기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/import) |
| `export default x`                | [ES2015] **기본 내보내기**                                   | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |
| `export { x }`                    | [ES2015] **명명된 내보내기**                                 | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |
| `export { x as y }`               | [ES2015] 명명된 내보내기 **별칭**                            | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |
| `export { x } from "./x.js"`      | [ES2015] 명명된 내보내기 **다시 내보내기**                   | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |
| `export * from "./x.js"`          | [ES2015] 모든 명명된 내보내기 **다시 내보내기**              | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |
| `export * as ns from "./x.js"`    | [ES2020] **모든 명명된 내보내기를 가져오고 이름** 으로 `ns`다시 **내보내기** | [ECMAScript 모듈](https://jsprimer.net/basic/module/) | [내보내기](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/export) |

### 기타

| 코드 예 | 설명   | 해설                                                         | MDN                                                          |
| ------- | ------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `x;`    | **문** | [문장과 표현](https://jsprimer.net/basic/statement-expression/) | [어휘 문법](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Lexical_grammar#自動セミコロン挿入) |
| `{ }`   | 블록문 | [문장과 표현](https://jsprimer.net/basic/statement-expression/) | [블록](https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Statements/block) |

## 가이드

### 프로젝트 구조

JavaScript의 기본 프로젝트 레이아웃, 파일, 폴더 구조에 대해.

| 이름              | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| src/              | 프로젝트 소스 코드                                           |
| index.js          | 애플리케이션의 기본 진입점                                   |
| test/             | 테스트 코드. `src/`에 대한 단위 테스트를 배치하는 경우가 많습니다. |
| index.test.js     | 응용 프로그램 단위 테스트 파일. 예) `index-test.js`, `indexSpec.js`등 |
| node_modules/     | 프로젝트가 의존하는 npm 모듈을 설치할 위치                   |
| package.json      | 프로젝트 설정 파일. 이름, 설명, 스크립트, 종속 모듈 등       |
| package-lock.json | npm이 처리하는 종속 모듈 잠금 파일                           |

**참고**