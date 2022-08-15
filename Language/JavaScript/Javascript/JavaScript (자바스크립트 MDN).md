# JavaScript (자바스크립트 MDN)

출처 : https://developer.mozilla.org/ko/docs/Web/JavaScript



# JavaScript

**JavaScript** (**JS**)는 가벼운, 인터프리터 혹은 [just-in-time](https://ko.wikipedia.org/wiki/JIT_컴파일) 컴파일 프로그래밍 언어로, [일급 함수](https://developer.mozilla.org/ko/docs/Glossary/First-class_Function)를 지원합니다. 웹 페이지를 위한 스크립트 언어로 잘 알려져 있지만, [Node.js](https://developer.mozilla.org/ko/docs/Glossary/Node.js), [Apache CouchDB](https://couchdb.apache.org/), [Adobe Acrobat](https://www.adobe.com/devnet/acrobat/javascript.html)처럼 [많은 비 브라우저 환경](https://en.wikipedia.org/wiki/JavaScript#Other_usage)에서도 사용하고 있습니다. JavaScript는 [프로토타입 기반](https://developer.mozilla.org/ko/docs/Glossary/Prototype-based_programming), 다중 패러다임, 단일 스레드, 동적 언어로, 객체지향형, 명령형, 선언형(함수형 프로그래밍 등) 스타일을 지원합니다. 자세한 내용은 [JavaScript에 대하여](https://developer.mozilla.org/ko/docs/Web/JavaScript/About_JavaScript)를 참고하세요.

해당 섹션은 JavaScript 언어 자체를 다루며 웹 페이지 또는 다른 사용 환경에 대해 다루지 않습니다. 웹 페이지의 특정 [API](https://developer.mozilla.org/ko/docs/Glossary/API)에 대한 정보를 알고 싶다면, [웹 API](https://developer.mozilla.org/ko/docs/Web/API)와 [DOM](https://developer.mozilla.org/ko/docs/Glossary/DOM)을 참고하시기 바랍니다.

JavaScript의 표준은 [ECMAScript 언어 사양](https://tc39.es/ecma262/) (ECMA-262) 및 [ECMAScript 국제화 API 사양](https://tc39.es/ecma402/) (ECMA-402)입니다. MDN에서 제공하는 JavaScript 문서는 ECMA-262 및 ECMA-402의 최신 초안 버전을 기반으로합니다. [새로운 ECMAScript 기능에 대한 일부 제안](https://github.com/tc39/proposals)이 이미 브라우저에 구현 된 경우, MDN의 문서 및 예제에서 이러한 새로운 기능 중 일부를 사용할 수 있습니다.

JavaScript와 [Java 프로그래밍 언어](https://ko.wikipedia.org/wiki/자바_(프로그래밍_언어))를 혼동해서는 안 됩니다. "Java"와 "JavaScript" 는 모두 상표이자 미국 및 기타 국가에 등록된 Oracle의 상표입니다. 다만 두 프로그래밍 언어의 구문, 시맨틱 및 사용 방법이 매우 다릅니다.

#### 프론트 엔드 웹 개발자가되고 싶으신가요?

목표 달성에 필요한 모든 필수 정보가 포함된 과정을 마련했습니다.

[**시작하기**](https://developer.mozilla.org/en-US/docs/Learn/Front-end_web_developer)

## [자습서](https://developer.mozilla.org/ko/docs/Web/JavaScript#tutorials)

가이드 및 자습서를 통해 JavaScript로 프로그래밍하는 방법을 배워보세요.

### [입문자용](https://developer.mozilla.org/ko/docs/Web/JavaScript#for_complete_beginners)

JavaScript를 배우고 싶지만 JavaScript이나 프로그래밍에 대한 이전 경험이 없는 경우 [JavaScript 주제에 대한 학습 영역](https://developer.mozilla.org/ko/docs/Learn/JavaScript)을 방문하세요. 전체 과정은 다음과 같습니다.

- [JavaScript 첫 걸음](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps)

  변수, 문자열, 숫자 및 배열과 같은 주요 JavaScript 기능에 대해 논의하면서 "JavaScript 는 무엇인가요?", "어떤 모습인가요?", "무엇을 할 수 있나요?"와 같은 몇 가지 기본적인 질문에 답합니다.

- [JavaScript 구성 요소](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Building_blocks)

  JavaScript의 핵심 기본 기능에 대한 이해를 넓히기 위해 조건문, 반복문, 함수, 이벤트와 같이 흔히 찾을 수 코드 블록의 형태에 대해서 알아봅니다.

- [JavaScript 객체 소개](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Objects)

  JavaScript의 객체 지향적 특성에 대한 이해는 언어의 이해도를 높이며 효율적인 코드를 작성하려는 경우 중요하므로, 이 과정이 도움이 될 것입니다.

- [비동기 JavaScript](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Asynchronous)

  비동기 JavaScript가 중요한 이유와, 비동기적 코드를 사용해 서버에서 리소스 가져오기 등 블록킹 연산을 효율적으로 처리하는 방법에 대해 알아봅니다.

- [클라이언트측 웹 API](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Client-side_web_APIs)

  API란 무엇인지 탐색해보고, 개발 작업에서 자주 접하게 될 가장 일반적인 API를 사용하는 방법을 알아봅니다.

- JavaScript 안내서

- [JavaScript 가이드](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide)

  JavaScript 또는 다른 언어로 프로그래밍 경험이 있는 독자들을 대상으로한 JavaScript 언어에 대한 상세 가이드입니다.

### [중급](https://developer.mozilla.org/ko/docs/Web/JavaScript#intermediate)

- [클라이언트 측 JavaScript 프레임워크 이해](https://developer.mozilla.org/ko/docs/Learn/Tools_and_testing/Client-side_JavaScript_frameworks)

  JavaScript 프레임워크는 현대 프런트 엔드 웹 개발의 필수 부분으로, 개발자에게 확장 가능한 상호작용형 웹 응용 프로그램을 구축하기위한 입증된 도구를 제공합니다. 이 과정에서는 오늘날 가장 인기있는 일부 프레임워크를 다루는 자습서 시리즈로 이동하기 전에 클라이언트 측 프레임워크가 작동하는 방식과 도구 집합에 맞는 방식에 대한 기본적인 배경 지식을 제공합니다.

- [JavaScript 재입문](https://developer.mozilla.org/ko/docs/Web/JavaScript/A_re-introduction_to_JavaScript)

  JavaScript에 대해 알고 있다고 *생각하는* 사람들을 위한 개요.

- [JavaScript 데이터 구조](https://developer.mozilla.org/ko/docs/Web/JavaScript/Data_structures)

  JavaScript에서 이용 가능한 데이터 구조 개요.

- [동등성 비교 및 동일성](https://developer.mozilla.org/ko/docs/Web/JavaScript/Equality_comparisons_and_sameness)

  JavaScript는 세 가지 값 비교 연산을 제공합니다: `===`를 사용한 엄격한(strict) 동등성, `==`를 사용한 느슨한 동등성 및 [`Object.is()`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Object/is) 메서드.

- [클로저](https://developer.mozilla.org/ko/docs/Web/JavaScript/Closures)

  클로저는 함수와 그 함수가 선언된 어휘 (lexical) 환경의 조합입니다.

### [고급](https://developer.mozilla.org/ko/docs/Web/JavaScript#advanced)

- [상속 및 프로토타입 체인](https://developer.mozilla.org/ko/docs/Web/JavaScript/Inheritance_and_the_prototype_chain)

  널리 오해 받고 과소 평가된 프로토타입 기반 상속의 설명.

- [엄격 모드](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Strict_mode)

  엄격 모드는 초기화 전에 변수를 사용할 수 없음을 정의합니다. 이는 빠른 성능 및 쉬운 디버깅을 위한 ECMAScript 5의 제한된 변형(variant)입니다.

- [JavaScript 형식화된 배열](https://developer.mozilla.org/ko/docs/Web/JavaScript/Typed_arrays)

  JavaScript 형식화된 배열은 원시 이진 데이터에 접근하기 위한 메커니즘을 제공합니다.

- [메모리 관리](https://developer.mozilla.org/ko/docs/Web/JavaScript/Memory_Management)

  JavaScript의 메모리 라이프 사이클 및 가비지 컬렉션.

- [동시성 모델 및 이벤트 루프](https://developer.mozilla.org/ko/docs/Web/JavaScript/EventLoop)

  JavaScript에는 "이벤트 루프"에 기반을 둔 동시성 모델이 있습니다.

## [참고서](https://developer.mozilla.org/ko/docs/Web/JavaScript#reference)

전체 [JavaScript 참고서](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference)를 훑어보세요.

- [표준 객체](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects)

  [`Array`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array), [`Boolean`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Boolean), [`Date`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date), [`Error`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Error), [`Function`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Function), [`JSON`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/JSON), [`Math`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Math), [`Number`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Number), [`Object`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Object), [`RegExp`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/RegExp), [`String`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/String), `Map`, [`Set`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Set), [`WeakMap`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/WeakMap), [`WeakSet`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/WeakSet) 등 표준 내장 객체 알아가기.

- [표현식 및 연산자](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators)

  JavaScript 연산자 [`instanceof`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/instanceof), [`typeof`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/typeof), [`new`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/new), [`this`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/this)의 동작, [연산자 우선순위](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Operator_Precedence) 등에 대해 더 알아보기.

- [명령문 및 선언문](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements)

  [`do-while`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/do...while), [`for-in`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/for...in), [`for-of`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/for...of), [`try-catch`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/try...catch), [`let`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/let), [`var`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/var), [`const`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/const), [`if-else`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/if...else), [`switch`](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/switch) 등의 JavaScript의 구문 및 키워드 작동법 배우기.

- [함수](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions)

  애플리케이션 개발을 위한 JavaScript 함수로 작업하는 법 배우기.

## [도구 & 자원](https://developer.mozilla.org/ko/docs/Web/JavaScript#도구_자원)

**JavaScript** 코드 작성과 디버깅을 돕는 유용한 도구 모음입니다.

- [Firefox 개발자 도구](https://firefox-source-docs.mozilla.org/devtools-user/index.html)

  [Web Console](https://firefox-source-docs.mozilla.org/devtools-user/web_console/index.html), [JavaScript Profiler](https://firefox-source-docs.mozilla.org/devtools-user/performance/index.html), [Debugger](https://firefox-source-docs.mozilla.org/devtools-user/debugger/index.html) 등.

- [JavaScript 쉘](https://developer.mozilla.org/ko/docs/Web/JavaScript/Shells)

  JavaScript 쉘을 사용하면 JavaScript 코드 스니펫을 빠르게 테스트 할 수 있습니다.

- [JavaScript 배우기](https://learnjavascript.online/)

  야심찬 웹 개발자를 위한 훌륭한 리소스 — 짧은 강의와 상호작용형 환경에서 JavaScript를 배우세요. 처음 40 개의 강의는 무료이며 전체 과정은 일회성 소액의 금액으로 제공됩니다.

- [TogetherJS](https://togetherjs.com/)

  협업이 쉬워졌습니다. 사이트에 TogetherJS를 추가하면 사용자가 웹 사이트에서 실시간으로 서로를 도울 수 있습니다!

- [Stack Overflow](https://stackoverflow.com/questions/tagged/javascript)

  "JavaScript" 태그가 달린 Stack Overflow 질문들 입니다.

- [JSFiddle](https://jsfiddle.net/)

  JavaScript, CSS, HTML 편집 및 실시간 결과를 얻을 수 있으며, 외부 자원(resource)을 사용하며 온라인으로 팀과 협업이 가능합니다.

- [Plunker](https://plnkr.co/)

  Plunker는 온라인에서 여러분의 웹 개발 아이디어를 실제로 만들거나 다른사람과 공유 협업하는 커뮤니티 입니다. JavaScript, CSS, HTML 파일을 편집하고 실시간 결과와 파일 구조를 얻으세요.

- [JSBin](https://jsbin.com/)

  JS Bin은 오픈 소스 협업 웹 개발 디버깅 도구입니다.

- [Codepen](https://codepen.io/)

  Codepen은 실시간으로 실행 결과를 볼 수 있으며 사용되는 또 다른 협업 웹 개발 도구입니다.

- [StackBlitz](https://stackblitz.com/)

  StackBlitz는 React, Angular 등을 사용하여 풀 스택 애플리케이션을 호스팅하고 배포 할 수 있는 또 다른 온라인 플레이 그라운드/디버깅 도구입니다.

- [RunJS](https://runjs.app/)

  RunJS는 데스크톱 플레이그라운드/스크래치 패드 도구로, 실시간 결과를 제공하고 노드 및 브라우저 API에 대한 액세스를 제공합니다.

### Found a problem with this page?

- [Edit on **GitHub**](https://github.com/mdn/translated-content/edit/main/files/ko/web/javascript/index.html)
- [Source on **GitHub**](https://github.com/mdn/translated-content/blob/main/files/ko/web/javascript/index.html?plain=1)
- [Report a problem with this content on **GitHub**](https://github.com/mdn/translated-content/issues/new?body=MDN+URL%3A+https%3A%2F%2Fdeveloper.mozilla.org%2Fko%2Fdocs%2FWeb%2FJavaScript %23%23%23%23+What+information+was+incorrect%2C+unhelpful%2C+or+incomplete%3F  %23%23%23%23+Specific+section+or+headline%3F  %23%23%23%23+What+did+you+expect+to+see%3F  %23%23%23%23+Did+you+test+this%3F+If+so%2C+how%3F   Page+report+details<%2Fsummary> *+Folder%3A+`ko%2Fweb%2Fjavascript` *+MDN+URL%3A+https%3A%2F%2Fdeveloper.mozilla.org%2Fko%2Fdocs%2FWeb%2FJavaScript *+GitHub+URL%3A+https%3A%2F%2Fgithub.com%2Fmdn%2Ftranslated-content%2Fblob%2Fmain%2Ffiles%2Fko%2Fweb%2Fjavascript%2Findex.html *+Last+commit%3A+https%3A%2F%2Fgithub.com%2Fmdn%2Ftranslated-content%2Fcommit%2Fc5e82bf41f336a59704ee8c429e3147f73fa8a48 *+Document+last+modified%3A+2022-02-16T08%3A11%3A01.000Z <%2Fdetails>&title=Issue+with+"JavaScript"%3A+(short+summary+here+please))
- Want to fix the problem yourself? See [our Contribution guide](https://github.com/mdn/content/blob/main/README.md).

**Last modified:** 2022



## [입문자용](https://developer.mozilla.org/ko/docs/Web/JavaScript#for_complete_beginners)

JavaScript를 배우고 싶지만 JavaScript이나 프로그래밍에 대한 이전 경험이 없는 경우 [JavaScript 주제에 대한 학습 영역](https://developer.mozilla.org/ko/docs/Learn/JavaScript)을 방문하세요. 전체 과정은 다음과 같습니다.

[JavaScript 첫 걸음](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps)

변수, 문자열, 숫자 및 배열과 같은 주요 JavaScript 기능에 대해 논의하면서 "JavaScript 는 무엇인가요?", "어떤 모습인가요?", "무엇을 할 수 있나요?"와 같은 몇 가지 기본적인 질문에 답합니다.

[JavaScript 구성 요소](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Building_blocks)

JavaScript의 핵심 기본 기능에 대한 이해를 넓히기 위해 조건문, 반복문, 함수, 이벤트와 같이 흔히 찾을 수 코드 블록의 형태에 대해서 알아봅니다.

[JavaScript 객체 소개](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Objects)

JavaScript의 객체 지향적 특성에 대한 이해는 언어의 이해도를 높이며 효율적인 코드를 작성하려는 경우 중요하므로, 이 과정이 도움이 될 것입니다.

[비동기 JavaScript](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Asynchronous)

비동기 JavaScript가 중요한 이유와, 비동기적 코드를 사용해 서버에서 리소스 가져오기 등 블록킹 연산을 효율적으로 처리하는 방법에 대해 알아봅니다.

[클라이언트측 웹 API](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Client-side_web_APIs)

API란 무엇인지 탐색해보고, 개발 작업에서 자주 접하게 될 가장 일반적인 API를 사용하는 방법을 알아봅니다.

### JavaScript 안내서

[JavaScript 가이드](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide)

JavaScript 또는 다른 언어로 프로그래밍 경험이 있는 독자들을 대상으로한 JavaScript 언어에 대한 상세 가이드입니다.



---

# JavaScript 첫걸음

우리의 첫번째 JavaScript 모듈에서는 JavaScript를 써보는 첫번째 현실적인 경험을 하기에 앞서, "JavaScript란 무엇인가", "그것은 어떻게 생겼는가", "그것은 무엇을 할 수 있는가"와 같은 핵심적인 질문에 답을 할 것입니다. 그 이후에 우리는 예컨대 변수, 문자열, 숫자, 배열과 같은 핵심적인 단위를 상세히 논의해볼 것입니다.

## [준비 과정](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps#준비_과정)

이번 과정을 시작하기 전에 JavaScript에 대해 몰라도 되지만, HTML, CSS와는 어느 정도 친숙해야 합니다. JavaScript를 시작하기 이전에 우선 다음 과정부터 진행하시는게 좋습니다.

- [Web으로 시작하기](https://developer.mozilla.org/ko/docs/Learn/Getting_started_with_the_web) (아주 기본적인 [JavaScript 소개](https://developer.mozilla.org/ko/docs/Learn/Getting_started_with_the_web/JavaScript_basics)도 포함)
- [HTML 소개](https://developer.mozilla.org/ko/docs/Learn/HTML/Introduction_to_HTML)
- [CSS 소개](https://developer.mozilla.org/en-US/docs/Learn/CSS/First_steps)

**참고:** 파일을 만들 수 없는 컴퓨터나 태블릿, 기타 장치에서 작업을 하신다면 예제 코드의 대부분을 [JSBin](https://jsbin.com/)이나 [Thimble](https://thimble.mozilla.org/) 같은 온라인 코딩 프로그램에서도 시험할 수 있습니다.

## [구성](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps#구성)

- [ JavaScript가 뭔가요?](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript)

  MDN의 JavaScript 초급자 과정에 오신 걸 환영합니다! 첫 글은 JavaScript를 넓게 보면서 "뭔가요?", "뭘 하나요?"와 같은 질문을 답변하고, 여러분이 JavaScript에 친숙해지도록 도와드립니다.

- [JavaScript에 발 담그기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash)

  JavaScript에 관한 이론과 용도를 약간 배우셨으니, 실용적인 따라해보기와 함께 하는 기초과정 단기 특강을 제공해드리겠습니다. 여기서는 간단한 "숫자 알아맞히기" 게임을 단계별로 차근차근 만들어봅니다.

- [뭐가 잘못됐을까요? JavaScript 문제 해결](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_went_wrong)

  이전 글에서 "숫자 알아맞히기" 게임을 만든 뒤에, 프로그램이 돌아가지 않는다는 것을 발견하실 겁니다. 두려워하지 마세요. 세 번째 글에서 JavaScript 프로그램의 오류를 찾고 고치는 팁을 배울 수 있습니다.

- [필요한 정보 저장하기 — 변수](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Variables)

  앞선 글을 모두 읽으셨으면 이제 JavaScript가 뭔지, 뭘 할 수 있는지, 다른 웹 기술과 함께 어떻게 사용하는지, 주요 기능이 어떻게 생겼는지 등 넓은 윤곽을 그리실 수 있을겁니다. 이제 진짜 기초로 돌아가, JavaScript의 제일 기본적인 구성 요소를 어떻게 사용하는지 알아보겠습니다. 바로 변수입니다.

- [JavaScript의 기본 수학 — 숫자와 연산자](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Math)

  여기서는 JavaScript에서의 수학을 이야기하면서, 연산자와 다른 기능을 이리 저리 조합하여 숫자를 여러분의 뜻에 맞게 바꾸는 법을 배웁니다.

- [텍스트 다루기 — 문자열](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Strings)

  우리의 시선을 이제 문자열로 돌릴 차례입니다. 프로그래밍에서 텍스트를 일컫는 단어죠. 이번 글에서는 JavaScript를 배울 때 문자열에 대해 알아둬야 하는 것, 예를 들어 문자열 생성, 문자열 안의 따옴표 처리, 문자열 합치기 등을 알아봅니다.

- [유용한 문자열 메서드](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Useful_string_methods)

  문자열의 기초를 탐구했으니, 한 단계 더 나아가 문자열의 길이 찾기, 문자열 합치고 나누기, 문자를 다른 문자로 치환하기 등 내장 메서드로 할 수 있는 유용한 작업에 대해 생각해봅니다.

- [배열](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Arrays)

  본 과정의 마지막 글에서는 배열에 대해 알아봅니다. 배열은 하나의 변수 이름 아래에 여러 데이터 목록을 저장하는 깔끔한 방법입니다. 배열이 왜 유용한지, 배열을 어떻게 생성하는지, 저장한 데이터를 회수하고, 추가하고, 제거하는 법 등을 배웁니다.

## [평가](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps#평가)

다음 평가를 통해 JavaScript의 기초를 얼마나 이해했는지 시험할 수 있습니다.

- [바보같은 이야기 생성기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Silly_story_generator)

  각자 배운 내용을 돌이켜보면서, 바보같은 이야기를 무작위로 만들어내는 앱을 제작해야 합니다. 행운을 빌어요!

## [See also](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps#see_also)

- [Learn JavaScript](https://learnjavascript.online/)

  야망있는 웹 개발자를 위한 완벽한 자료입니다. JavaScript를 자동화된 평가에 의해 안내되는 짧은 강의와 인터렉티브한 테스트와 함께 상호작용하는 환경에서 배워보세요. 처음 40개의 레슨은 무료이며, 전체 코스는 적은 비용을 지불하면 가능합니다.



# JavaScript가 뭔가요?

MDN의 JavaScript 초급자 과정에 오신 걸 환영합니다! 이 글은 JavaScript를 넓게 보면서 "뭔가요?", "뭘 하나요?"와 같은 질문을 답변하고, 여러분이 JavaScript에 친숙해지도록 도와드립니다.



| 구분              | 내용                                                         |
| :---------------- | ------------------------------------------------------------ |
| 필요한 사전 지식: | 기본적인 컴퓨터 이해 능력, HTML과 CSS 기초.                  |
| 목표:             | JavaScript가 뭔지, 어떤 일을 할 수 있는지, 웹 사이트에 어떻게 적용하는지 알아보기 알기. |

## [둘러보기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#둘러보기)

JavaScript는 웹 페이지에서 복잡한 기능을 구현할 수 있도록 하는 스크립팅 언어 또는 프로그래밍 언어입니다. 페이지의 내용이 — 가만히 정적인 정보만 보여주는 것이 아니라 — 주기적으로 갱신되거나, 사용자와 상호작용이 가능하거나, 애니메이션이 적용된 2D/3D 그래픽을 볼 수 있다면 JavaScript가 관여하고 있을 거라고 생각해도 좋습니다. JavaScript는 표준 웹 기술이라는 케이크의 세 번째 층입니다. 다른 두 개([HTML](https://developer.mozilla.org/ko/docs/Learn/HTML)과 [CSS](https://developer.mozilla.org/ko/docs/Learn/CSS))는 학습장의 다른 곳에서 자세히 알아봤습니다.

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/cake.png)

- [HTML](https://developer.mozilla.org/ko/docs/Glossary/HTML)은 웹 콘텐츠의 구조를 짜고 의미를 부여하는 마크업 언어입니다. 예를 들어 페이지의 어디가 문단이고, 헤딩이고, 데이터 표와 외부 이미지/비디오인지 정의합니다.
- [CSS](https://developer.mozilla.org/ko/docs/Glossary/CSS)는 HTML 콘텐츠에 스타일을 적용할 수 있는 스타일 규칙 언어입니다. 배경색을 추가하고, 글꼴을 바꾸고, 콘텐츠를 신문처럼 다열 레이아웃으로 배치할 수 있습니다.
- [JavaScript](https://developer.mozilla.org/ko/docs/Glossary/JavaScript)는 동적으로 콘텐츠를 바꾸고, 멀티미디어를 제어하고, 애니메이션을 추가하는 등 거의 모든 것을 만들 수 있는 스크립팅 언어입니다. (정말 모든게 가능하지는 않겠지만, JavaScript 코드 몇 줄만으로도 놀라운 결과를 이룰 수 있습니다)

이렇게 세 층은 서로의 위에 유기적으로 잘 올라가있습니다. 연습 삼아 간단한 텍스트 레이블을 만들어봅시다. HTML로, 아래처럼 텍스트에 구조와 목적을 부여할 수 있습니다.

```
<p>Player 1: Chris</p>
```

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/just-html.png)

CSS를 추가하면 보기 좋게 꾸밀 수 있습니다.

```
p {
  font-family: 'helvetica neue', helvetica, sans-serif;
  letter-spacing: 1px;
  text-transform: uppercase;
  text-align: center;
  border: 2px solid rgba(0, 0, 200, 0.6);
  background: rgba(0, 0, 200, 0.3);
  color: rgba(0, 0, 200, 0.6);
  box-shadow: 1px 1px 2px rgba(0, 0, 200, 0.4);
  border-radius: 10px;
  padding: 3px 10px;
  display: inline-block;
  cursor: pointer;
}
```

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/html-and-css.png)

마지막으로는 JavaScript를 곁들여 동적인 기능을 추가할 수 있습니다.

```
const para = document.querySelector('p');

para.addEventListener('click', updateName);

function updateName() {
  const name = prompt('Enter a new name');
  para.textContent = `Player 1: ${name}`;
}
```

<iframe class="sample-code-frame" title="둘러보기 sample" id="frame_둘러보기" width="100%" height="80" src="https://yari-demos.prod.mdn.mozit.cloud/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript/_sample_.%EB%91%98%EB%9F%AC%EB%B3%B4%EA%B8%B0.html" loading="lazy" style="box-sizing: border-box; border: 1px solid var(--border-primary); max-width: 100%; width: 639.438px; background: rgb(255, 255, 255); border-radius: var(--elem-radius);"></iframe>

바로 위의 텍스트 레이블을 클릭해서 어떤 일이 일어나는지 확인해보세요. (이 데모는 GitHub에서도 볼 수 있습니다. [소스코드](https://github.com/mdn/learning-area/blob/main/javascript/introduction-to-js-1/what-is-js/javascript-label.html)를 읽거나 [실행된 모습](https://mdn.github.io/learning-area/javascript/introduction-to-js-1/what-is-js/javascript-label.html)을 확인해보세요)

JavaScript는 이것보다 훨씬 많은 일을 할 수 있습니다. 어떤 일이 가능한지 자세히 알아봅시다.

## [그래서 어떤 일을 할 수 있나요?](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#그래서_어떤_일을_할_수_있나요)

클라이언트 사이드 JavaScript 언어의 핵심은 다음과 같은 일을 가능하게 해주는 프로그래밍 기능들로 구성됩니다.

- 변수에 값을 저장할 수 있습니다. 바로 위의 예제를 보면, 요청해서 받은 새로운 이름을 `name`이라는 변수에 저장합니다.
- 프로그래밍에서 "문자열"(string)이라고 부르는, 텍스트 조각을 조작합니다. 위 예제에서는 문자열 "플레이어 1: "과 `name` 변수의 값을 합쳐 온전한 텍스트 레이블("Player 1: Chris")을 생성합니다.
- 웹 페이지에서 발생하는 어떤 이벤트에 코드가 응답하도록 합니다. 예제에서는 `click` 이벤트를 사용해서 레이블을 클릭하는 순간을 탐지하고, 그 후에 텍스트 레이블을 업데이트하고 있습니다.
- 그밖에 더 많은 것들!

하지만 위 목록보다 더 흥미진진한건 클라이언트 사이드 JavaScript 언어 위에 올라가있는 기능들입니다. **애플리케이션 프로그래밍 인터페이스**(**API**)라고 부르는 이 기능들은 여러분의 JavaScript 코드에서 사용할 수 있는 강력한 마법을 추가로 제공합니다.

API는 개발자가 직접 구현하기는 어렵거나 불가능한 기능들을 미리 만들어서 제공하는 것입니다. 가구 DIY 키트 같은 거죠. 미리 재단된 판과 나사로 책장을 조립하는 게, 디자인하고 적합한 목재를 찾아서 올바른 크기와 모양으로 자른 후 적합한 크기의 나사를 찾아서 마침내 책장으로 만드는 것보다 훨씬 쉬운 법입니다.

API는 일반적으로 두 개의 범주로 분류할 수 있습니다.

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/browser.png)

**브라우저 API**는 웹 브라우저에 내장된 API로, 현재 컴퓨터 환경에 관한 데이터를 제공하거나 여러가지 유용하고 복잡한 일을 수행합니다.

- [DOM (Document Object Model) API](https://developer.mozilla.org/ko/docs/Web/API/Document_Object_Model)로 HTML 콘텐츠를 추가, 제거, 변경하고, 동적으로 페이지에 스타일을 추가하는 등 HTML/CSS를 조작할 수 있습니다. 페이지 위에 뜨는 팝업창이나, (위쪽의 간단한 예제처럼) 새로운 콘텐츠가 나타나는 것을 본 적이 있으면 DOM이 동작한 겁니다.
- [Geolocation API](https://developer.mozilla.org/ko/docs/Web/API/Geolocation)로 지리정보를 가져올 수 있습니다. [Google 지도](https://www.google.com/maps)에서 여러분의 위치를 찾아 지도에 그릴 수 있는 이유가 바로 이 API입니다.
- [Canvas](https://developer.mozilla.org/ko/docs/Web/API/Canvas_API)와 [WebGL API](https://developer.mozilla.org/ko/docs/Web/API/WebGL_API)로 애니메이션을 적용한 2D와 3D 그래픽을 만들 수 있습니다. 두 웹 기술을 사용해서 만들 수 있는 놀라운 결과를 엿보려면 [Chrome Experiments](https://experiments.withgoogle.com/collection/chrome)와 [webglsamples](https://webglsamples.org/)를 방문하세요.
- [`HTMLMediaElement`](https://developer.mozilla.org/ko/docs/Web/API/HTMLMediaElement)와 [`WebRTC`](https://developer.mozilla.org/ko/docs/Web/API/WebRTC_API)를 포함하는 [오디오와 비디오 API](https://developer.mozilla.org/ko/docs/Web/Guide/Audio_and_video_delivery)로는 멀티미디어를 사용한 흥미로운 일을 할 수 있습니다. 예를 들어 오디오나 비디오를 웹 페이지에서 바로 재생하거나, 여러분의 웹캠으로 비디오를 찍어 다른 사람의 화면에 보여줄 수 있습니다. (간단한 [스냅샷 데모](https://chrisdavidmills.github.io/snapshot/)를 방문해서 감을 잡아보세요)

**참고:** 구형 브라우저에서는 위 예제 중 많은 수가 동작하지 않습니다. 웹 기술을 시험할 땐 Firefox, Chrome, Edge, Safari처럼 모던 브라우저를 사용하는 편이 좋습니다. 이후에 프로덕션 코드 (고객이 사용하게 될 코드) 배포에 다가가게 되면 [크로스 브라우저 테스트 (en-US)](https://developer.mozilla.org/en-US/docs/Learn/Tools_and_testing/Cross_browser_testing)를 고려하세요.

**서드파티 API**는 브라우저에 탑재되지 않은 API로, 웹의 어딘가에서 직접 코드와 정보를 찾아야 합니다.

- [Twitter API](https://developer.twitter.com/en/docs)로 여러분의 최신 트윗을 웹 사이트가 보여주도록 구현할 수 있습니다.
- [Google 지도 API](https://developers.google.com/maps/)와 [OpenStreetMap API](https://wiki.openstreetmap.org/wiki/API)로 웹 사이트에 지도를 삽입하고, 지도 관련 기능을 추가할 수 있습니다.

**참고:** 위의 서드파티 API는 고급 기능으로, 이 과정에서는 다루지 않습니다. [클라이언트 사이드 웹 API 과정](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Client-side_web_APIs)에서 알아보세요.

여기 나열된 항목 말고도 훨씬 다양한 API들이 존재합니다! 하지만 아직 너무 앞서나가진 마세요. JavaScript를 하루 배운 것으로 차세대 Facebook, Google 지도, Instagram을 만들 수는 없는 법입니다. 아직 배워야 할 기본이 많이 남았고, 기본을 배우는 것이 이 과정을 보는 이유이니, 시작해 봅시다!

## [웹 페이지에서 JavaScript는 어떤 일을 하나요?](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#웹_페이지에서_javascript는_어떤_일을_하나요)

이제부턴 코드를 살펴보면서, 페이지에서 JavaScript를 돌리면 어떤 일이 일어나는지도 알아보겠습니다.

브라우저가 웹 페이지를 불러오면 어떤 일이 발생하는지 간단하게 복습해봅시다([CSS 동작 방식](https://developer.mozilla.org/ko/docs/Learn/CSS/First_steps/How_CSS_works#css_는_실제로_어떻게_작동합니까)에서 먼저 알아봤습니다). 웹 페이지를 브라우저로 불러오면, 브라우저는 여러분의 코드(HTML, CSS, JavaScript)를 실행 환경(브라우저 탭)에서 실행합니다. 원자재(코드)를 가져와서 상품(웹 페이지)을 생산하는 공장처럼 생각할 수 있습니다.

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/execution.png)

JavaScript는 DOM (Document Object Model) API를 통해 HTML과 CSS를 동적으로 수정, 사용자 인터페이스를 업데이트하는 일에 가장 많이 쓰입니다. 참고로 웹 문서(페이지)의 코드는 보통 문서 상에 나타나는 순서 그대로 불러와 실행합니다. 수정하려는 HTML과 CSS 코드보다 JavaScript를 먼저 불러와 실행해버리면 오류가 발생할 수 있습니다. 아래의 [스크립트 로딩 전략](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#스크립트_로딩_전략)에서 이 문제의 해결법을 알아보겠습니다.

### [브라우저 보안](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#브라우저_보안)

각각의 브라우저 탭은 코드를 실행하기 위한 독립적인 그릇(기술 용어로 "실행 환경"이라고 부릅니다)입니다. 독립적이라는 것은 대부분의 탭이 서로에게서 완전히 분리되어 한 탭의 코드가 다른 탭의 코드, 또는 다른 사이트에 직접적인 영향을 줄 수 없다는 뜻입니다. 이건 중요한 보안 절차입니다. 만약 이런 제약이 없으면 해커들이 다른 웹 사이트에서 정보를 훔치는 것을 포함해 여러가지 나쁜 일을 하는 코드를 작성할 것입니다.

**참고:** 두 개의 다른 웹 사이트/탭 사이에서 코드와 정보를 안전하게 주고받을 수 있는 방법이 있지만, 지금 과정과는 거리가 멀기 때문에 여기서는 다루지 않겠습니다.

### [JavaScript 실행 순서](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#javascript_실행_순서)

브라우저가 JavaScript 블록을 마주치면, 일반적으로는 순서대로 위에서 아래로 실행합니다. 따라서 코드 배치 순서에도 주의를 기울여야 합니다. 예를 들기 위해 맨 위의 첫 예제 코드로 돌아가봅시다.

```js
const para = document.querySelector('p');

para.addEventListener('click', updateName);

function updateName() {
  const name = prompt('Enter a new name');
  para.textContent = `Player 1: ${name}`;
}
```

위 코드는 텍스트 문단을 선택(1번 줄)해서 이벤트 수신기를 부착(3번 줄)하여, 사용자가 문단을 클릭하면 `updateName()` 코드 블록(5번 ~ 8번 줄)을 실행하도록 합니다. `updateName()` 코드 블록(이렇게 재사용 가능하도록 나눠놓은 코드 블록을 "함수"라고 합니다)은 사용자에게 새로운 이름을 물어보고, 그 이름을 문단에 삽입해서 화면을 업데이트합니다.

만약 1번 줄의 코드와 3번 줄의 코드 순서를 서로 바꿔서 실행했으면 원하는 동작 대신 [브라우저 개발자 콘솔 (en-US)](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/What_are_browser_developer_tools)에 오류, `TypeError: para is undefined`가 나타나며, `para` 객체가 아직 존재하지 않으므로 이벤트 수신기를 부착할 수도 없다는 뜻입니다.

**참고:** 이 오류는 아주 흔하게 볼 수 있는 오류 중 하나입니다. 여러분의 코드 안에서 참조하는 객체가 존재하는지 주의하세요.

### [인터프리터와 컴파일러](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#인터프리터와_컴파일러)

프로그래밍에서의 **인터프리터**와 **컴파일러**라는 단어를 들어본 적이 있는지 생각해보세요. 인터프리터를 사용하는 언어에서는 코드를 위에서 아래로 실행하고, 코드 구동 결과는 즉시 반환됩니다. 브라우저에서 JavaScript 코드를 실행하기 전에 다른 형태로 변환할 필요가 없다는 점을 기억하세요. 코드는 프로그래머가 읽을 수 있는 형태로 입력되고, 별도의 처리 없이 그대로 실행됩니다.

반면, 컴파일러를 사용하는 컴파일 언어에서는 컴퓨터가 코드를 실행하기 전에 다른 형태로 변환(컴파일)해야 합니다. 예를 들어, C/C++에서는 코드를 컴파일러로 기계언어로 변환하여, 그 결과를 컴퓨터가 실행합니다. 프로그램은 프로그램의 원본 소스 코드에서 생성한 이진 형식(바이너리)으로부터 실행됩니다.

JavaScript는 가볍고, 인터프리터를 사용하는 프로그래밍 언어입니다. 웹 브라우저는 JavaScript 코드를 원문 텍스트 형식으로 입력받아 실행합니다. 기술적인 측면으로 따지자면, 대부분의 모던 JavaScript 인터프리터들은 사실 **JIT 컴파일**(just-in-time 컴파일)이라는 기술을 사용해 성능을 향상하기는 합니다. 스크립트의 실행과 동시에 소스 코드를 더 빠르게 실행할 수 있는 이진 형태로 변환하여 최대한 높은 실행 속도를 얻는 방법입니다. 하지만 JavaScript는 여전히 인터프리터 언어로 분류됩니다. 컴파일을 먼저 해놔야 하는 것이 아니라 런타임에 일어나기 때문입니다.

두 방법 모두 각자의 장점을 가지고 있으나, 여기서 그 부분까지 논하지는 않겠습니다.

### [서버 사이드와 클라이언트 사이드 코드](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#서버_사이드와_클라이언트_사이드_코드)

웹 개발에 관련한 또 다른 용어로 **서버 사이드 코드**와 **클라이언트 사이드 코드**를 들어봤나요? 클라이언트 사이드 코드는 사용자의 컴퓨터에서 동작하는 코드입니다. 웹 페이지를 방문하면 브라우저가 페이지 내의 클라이언트 사이드 코드를 다운로드하고 실행해서 화면에 띄웁니다. 바로 이 과정에서 다루는 것은, 정확히는 **클라이언트 사이드 JavaScript**입니다.

반대로 서버 사이드 코드는 서버에서 동작하는 코드로, 그 실행 결과를 브라우저가 다운로드해서 화면에 띄우게 됩니다. 유명한 서버 사이드 웹 언어로는 PHP, Python, Ruby, ASP.NET, 그리고... JavaScript가 있습니다! JavaScript는 브라우저 뿐만 아니라, 많은 사람들이 사용하는 Node.js 환경처럼 서버 사이드 언어로도 사용할 수 있습니다. 서버 사이드 JavaScript에 관해서는 [동적 웹 사이트 – 서버 사이드 프로그래밍](https://developer.mozilla.org/ko/docs/Learn/Server-side) 과정에서 더 알아보세요.

### [동적 코드와 정적 코드](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#동적_코드와_정적_코드)

클라이언트 사이드 JavaScript와 서버 사이드 언어들 모두 **동적**이라는 단어로 설명할 수 있습니다. 동적인 이유는 웹 페이지/웹 앱의 서로 다른 상황에 서로 다른 화면을 보여줄 수 있고, 필요하면 새로운 콘텐츠를 생성할 수 있기 때문입니다. 서버 사이드 코드는 서버에서 새로운 콘텐츠를 생성 — 데이터베이스에서 데이터를 가져오는 등 — 합니다. 클라이언트 사이드 JavaScript는 클라이언트의 브라우저 내에서 새로운 콘텐츠를 생성 — 새로운 HTML 표를 생성하고, 서버에서 받아온 데이터로 채운 후, 사용자가 보고 있는 웹 페이지에 표시 — 합니다. 두 맥락 내에서 '동적'이라는 단어의 정확한 뜻은 약간 다르지만, 그럼에도 서로 연관되어 있으며, 두 방법(서버와 클라이언트 사이드)을 보통 함께 사용합니다.

동적으로 바뀌는 내용을 포함하지 않는 웹 페이지를 **정적**인 페이지라고 합니다. 정적인 페이지는 항상 같은 콘텐츠만 보여줍니다.

## [웹 페이지에 JavaScript를 어떻게 넣나요?

CSS와 비슷한 방법으로 JavaScript를 HTML 코드에 적용할 수 있습니다. CSS가 `<link>` 요소로 외부 스타일 시트를 적용하고,`<style>`요소로 내부 스타일 시트를 적용했다면, JavaScript는 한 종류의 HTML 친구만 요구합니다. 바로 `<script>`요소입니다. 어떻게 사용하는지 알아보겠습니다.



### [내부 JavaScript](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#내부_javascript)

1. 먼저, 예제 파일인 [apply-javascript.html](https://github.com/mdn/learning-area/blob/main/javascript/introduction-to-js-1/what-is-js/apply-javascript.html)을 여러분의 컴퓨터로 복사하세요. 적당한 폴더에 저장하면 됩니다.

   ``` html
   <!DOCTYPE html>
   <html lang="en-US">
     <head>
       <meta charset="utf-8">
       <meta name="viewport" content="width=device-width">
       <title>Apply JavaScript example</title>
     </head>
     <body>
       <button>Click me</button>
     </body>
   </html>
   ```

2. 브라우저와 텍스트 편집기로 1번의 파일을 여세요. HTML 코드가 클릭 가능한 버튼 하나를 포함하는 간단한 웹 페이지를 만드는 것을 확인할 수 있습니다.

3. 텍스트 편집기로 가서 아래의 코드를 추가하세요. 닫는 `</head>` 태그 바로 앞에 넣어주세요.

``` js
<script>

  // JavaScript goes here

</script>
```

4. 이제 `<script>` 요소 안에 JavaScript를 넣어서 웹 페이지를 조금 흥미롭게 만들어보겠습니다. 밑의 코드를 "// JavaScript goes here" 바로 아래에 넣어주세요.

   ``` js
   document.addEventListener('DOMContentLoaded', () => {
     function createParagraph() {
       const para = document.createElement('p');
       para.textContent = 'You clicked the button!';
       document.body.appendChild(para);
     }
   
     const buttons = document.querySelectorAll('button');
   
     for (const button of buttons) {
       button.addEventListener('click', createParagraph);
     }
   });
   ```

5. 파일을 저장하고 브라우저에서 페이지를 새로고침하세요. 이제 버튼을 클릭하는 순간 새로운 문단이 생성돼 밑에 추가되는 모습을 볼 수 있습니다.



### [외부 JavaScript](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#외부_javascript)

내부 JavaScript는 잘 동작했지만, JavaScript를 외부 파일로 분리하고 싶으면 어떡할까요? 지금 알아보겠습니다.

1. 위의 HTML 파일과 같은 폴더에 새로운 파일을 만드세요. `script.js`라는 이름을 붙여줍니다. 확장자를 .js로 지정했는지 다시 확인하세요. JavaScript 파일을 인식하는 방법입니다.

2. 현재 `<script>` 요소를 다음 코드로 대체하세요.

   ``` js
   <script src="script.js" defer></script>
   ```

3.  `script.js` 파일 안에 아래 내용을 입력하세요

   ``` js
   function createParagraph() {
     const para = document.createElement('p');
     para.textContent = 'You clicked the button!';
     document.body.appendChild(para);
   }
   
   const buttons = document.querySelectorAll('button');
   
   for (const button of buttons) {
     button.addEventListener('click', createParagraph);
   }
   ```

   

4. 저장하고 브라우저를 새로고침하세요. 아까와 같은 모습을 볼 수 있습니다! 분명히 동작은 같지만, JavaScript 파일을 외부 파일로 분리할 수 있었습니다. 보통은, 코드도 정리하고 여러 HTML 파일에서 같은 코드를 재사용할 수 있도록, 스크립트를 외부 파일로 분리하는 것이 좋습니다.



### [인라인 JavaScript 처리기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#인라인_javascript_처리기)

가끔은 HTML에 JavaScript 코드가 포함된 모습을 볼 수도 있습니다. 아래 코드처럼요.

``` js
function createParagraph() {
  const para = document.createElement('p');
  para.textContent = 'You clicked the button!';
  document.body.appendChild(para);
}
```

``` js
<button onclick="createParagraph()">Click me!</button>
```



예제로 확인해보세요.

<iframe class="sample-code-frame" title="인라인 JavaScript 처리기 sample" id="frame_인라인_javascript_처리기" width="100%" height="150" src="https://yari-demos.prod.mdn.mozit.cloud/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript/_sample_.%EC%9D%B8%EB%9D%BC%EC%9D%B8_javascript_%EC%B2%98%EB%A6%AC%EA%B8%B0.html" loading="lazy" style="box-sizing: border-box; border: 1px solid var(--border-primary); max-width: 100%; width: 639.438px; background: rgb(255, 255, 255); border-radius: var(--elem-radius); color: rgb(27, 27, 27); font-family: Inter, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-sans; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"></iframe>



위 예제는 이전의 두 예제와 완전히 같은 기능을 가지고 있지만, '<button>' 요소가 버튼 클릭 동작을 `onclick` 인라인 처리기로 포함한다는 점만 다릅니다.

**하지만, 이 방법을 사용하지 말아주세요.** JavaScript로 HTML 코드를 물들이는 것은 나쁜 방법일 뿐더러 비효율적입니다. JavaScript를 적용하려는 모든 버튼마다 일일히 `onclick="createParagraph()"`를 추가해야 하니까요.



### [대신 addEventListener 사용하기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#대신_addeventlistener_사용하기)

HTML에 JavaScript를 직접 넣는 대신, 순수한 JavaScript 방법을 사용하세요. `querySelectorAll()` 함수를 사용하면 현재 페이지 내의 모든 버튼을 선택할 수 있습니다. 그 후 반복과 `addEventListener()`로 버튼 하나씩 처리기를 부착하세요. 다음과 같은 모습이 될 것입니다.

``` js
const buttons = document.querySelectorAll('button');

for (const button of buttons) {
  button.addEventListener('click', createParagraph);
}
```

`onclick` 특성을 사용하는 방법보다 좀 길긴 하지만, 대신 페이지 내에 버튼이 몇 개가 있어도, 아니면 몇 개가 추가되고 제거됐는지 상관하지 않고, 그리고 JavaScript를 바꿔줄 필요 없이 모든 버튼에 대해 동작합니다.



> **참고:** 여러분 컴퓨터의 `apply-javascript.html`을 수정해서 버튼 몇 개를 추가해보세요. 브라우저를 새로고침해보면, 아무 버튼이나 눌러도 새로운 문단이 생성되는 모습을 볼 수 있습니다. 멋지지 않나요?



### [스크립트 로딩 전략](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#스크립트_로딩_전략)

스크립트를 적절한 시점에 불러오는 일에는 몇 가지 문제가 있습니다. 뭐든지 보이는 것만큼 간단하지는 않은 법이죠. 흔히 마주치게 되는 문제는, 페이지의 모든 HTML은 순서 그대로 불러온다는 점입니다. JavaScript를 사용해서 페이지 내의 요소 — 더 정확하게는 [Document Object Model](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Client-side_web_APIs/Manipulating_documents#the_document_object_model) — 를 조작하려고 할 때, 해당 요소를 포함한 HTML 코드보다 JavaScript를 먼저 불러와버리면 코드가 올바르게 동작하지 못할 것입니다.

앞선 내부와 외부 JavaScript 예제에서는 HTML 본문을 읽기 전에 문서의 헤드에서 JavaScript를 불러와 실행합니다. 여기서 문제가 생길 여지가 있으므로, 예방할 수 있는 방법을 사용했었습니다.

내부 예제에서는 코드 주위에 이런 구조를 볼 수 있습니다.

``` js
document.addEventListener('DOMContentLoaded', () => {
  ...
});

```

이건 이벤트, 그중에서도 HTML 본문 전체를 불러와 읽었다는 것을 나타내는 브라우저 `DOMContentLoaded` 이벤트를 수신하는 수신기입니다. 이 블록 내부의 JavaScript는 이벤트가 발생하기 전에는 실행되지 않으므로 로딩 시점으로 인한 오류를 예방할 수 있습니다. (이후 과정에서 [이벤트에 대해](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Building_blocks/Events) 알아보겠습니다)

외부 예제에서는 더 최신 기법을 사용해 문제 발생을 억제합니다. `defer` 특성은 브라우저가 `<script>` 태그를 마주쳐도 그 이후의 HTML 콘텐츠를 계속 불러오도록 지정합니다.

``` html
<script src="script.js" defer></script>
```

이렇게 하면 스크립트와 HTML을 동시에 불러오므로 오류가 발생하지 않습니다.

> **참고:** `defer` 특성이 오류를 예방하므로, 외부 예제에서는 `DOMContentLoaded`를 사용하지 않았습니다. 내부 예제에서는 `defer` 방법을 사용하지 않았는데, `defer`는 외부 스크립트에만 사용할 수 있기 때문입니다.



고전적인 방법은 스크립트 요소를 본문의 맨 마지막(`</body>` 태그 바로 앞)에 배치하는 것입니다. 그러면 모든 HTML을 읽은 후에 스크립트를 불러오게 됩니다. 이 방법의 문제는 HTML DOM을 모두 불러오기 전에는 스크립트의 로딩과 분석이 완전히 중단된다는 점입니다. 그래서 많은 스크립트를 포함하는 대형 사이트에서는 성능이 저하될 수 있습니다.

#### async와 defer

스크립트 중단 문제를 해결할 수 있는 기능에는 사실 두 가지가 있습니다. `async`와 (위에서 본) `defer`입니다. 어떤 차이점이 있는지 알아봅시다.

`async` 특성을 지정하면 스크립트를 가져오는 동안 페이지 로딩을 중단하지 않습니다. 그러나 스크립트 다운로드가 끝나면 바로 실행되는데, 실행 도중에는 페이지 렌더링이 중단됩니다. 스크립트의 실행 순서를 보장할 방법은 없습니다. 따라서 `async`는 다른 스크립트에 의존하지 않는 독립 스크립트에 사용해야 합니다.

`defer` 특성을 지정한 스크립트는 페이지 내에 배치한 순서대로 불러오게 됩니다. 또한 페이지 콘텐츠를 모두 불러오기 전까지는 실행하지 않으므로, 페이지 요소를 수정하거나 추가하는 등 DOM 작업을 기대하는 스크립트에 유용합니다.

다음은 세 개의 스크립트 로딩 전략을 적용했을 때 페이지에 어떤 영향을 주는지 시각적으로 표현한 이미지입니다.

![img](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/What_is_JavaScript/async-defer.jpg)

*[HTML 명세](https://html.spec.whatwg.org/images/asyncdefer.svg) 에서 가져와 [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) 라이선스 하에서 축소한 이미지입니다.* 

예를 들어, 아래와 같이 세 개의 스크립트 요소를 가지고 있다고 가정해 보겠습니다.

``` js
<script async src="js/vendor/jquery.js"></script>

<script async src="js/script2.js"></script>

<script async src="js/script3.js"></script>
```

위 코드로는 스크립트가 HTML의 순서대로 불러와질 것이라고 확실하게 예측할 수 없습니다. `jquery.js`를 `script2.js`와 `script3.js`보다 먼저 불러올 수도 있지만 이후에 불러올 수도 있습니다. 후자의 경우, `script2.js`와 `script3.js`에서 `jquery`에 의존하는 스크립트에서는 오류가 발생할 것입니다. `jquery`가 아직 정의되지 않았으니까요.

`async`는 다수의 백그라운드 스크립트를 최대한 빠르게 불러와야 할 때 사용하세요. 예를 들면 웹 게임을 시작했을 때 사용할 게임 데이터는 곧 필요하긴 하지만 지금 당장 필요한 것은 아니므로, 게임 데이터를 불러온다고 인트로 화면과 타이틀, 메인 메뉴 표시를 중단하지 않을 수 있습니다.

`defer` 특성으로 가져오는 스크립트(아래)는 페이지에 나타난 순서 그대로 불러와서, 페이지 콘텐츠와 위쪽 스크립트 로딩이 끝나면 실행합니다.

``` js
<script defer src="js/vendor/jquery.js"></script>

<script defer src="js/script2.js"></script>

<script defer src="js/script3.js"></script>
```

위 코드에서는 `script2.js`와 `script3.js`보다 `jquery.js`를 먼저 불러올 것이라고 확신할 수 있습니다. 세 스크립트 모두 페이지 콘텐츠를 모두 불러오기 전엔 실행하지 않으므로, 페이지 요소를 수정해야 하는 등 DOM 조작이 필요하다면 더 유용합니다.

요약하자면,

- `async`와 `defer` 모두, 브라우저가 페이지의 다른 내용(DOM 등등)을 불러오는 동안 스크립트를 별도 스레드에서 불러오게 만듭니다. 덕분에 스크립트를 가져오는 동안 페이지 로딩이 중단되지 않습니다.
- `async` 특성을 지정한 스크립트는 다운로드가 끝나는 즉시 실행합니다. 실행은 현재 페이지 렌더링을 중단하며, 실행 순서는 보장되지 않습니다.
- `defer` 특성을 지정한 스크립트는 순서를 유지한 채로 가져오며 모든 콘텐츠를 다 불러온 이후에 실행합니다.
- 의존성 없는 스크립트를 불러온 즉시 실행하려면 `async`를 사용하세요.
- 다른 스크립트에 의존하거나 DOM 로딩이 필요한 스크립트에는 `defer`를 사용하고, 원하는 순서에 맞춰서 `<script>` 요소를 배치하세요.



## [주석](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#주석)

HTML 및 CSS와 동일하게, JavaScript에서도 브라우저는 무시하는 주석을 작성해서 다른 개발자들(아니면 무슨 일을 했었는지 기억하지 못하는 미래의 여러분)에게 안내를 제공할 수 있습니다. 주석은 엄청 유용합니다. 자주 사용하세요. 특히 대규모 애플리케이션에서는요. 주석에는 두 종류가 있습니다.

- 한 줄 짜리 주석은 맨 앞에 이중 빗금(//)으로 작성합니다.

  ``` js
  // 나, 주석
  ```

- 여러 줄 주석은 /*과 */의 사이에 작성합니다

  ``` js
  /*
    나 또한
    주석
  */
  ```

그래서, 마지막으로 봤던 JavaScript 예제 코드에 주석을 추가한다면 이런 모습이 되겠죠.

``` js
// 함수: 새로운 문단을 생성해서 HTML 본문 끝에 붙입니다.

function createParagraph() {
  const para = document.createElement('p');
  para.textContent = 'You clicked the button!';
  document.body.appendChild(para);
}

/*
  1. 페이지 내의 모든 버튼에 대한 참조를 배열 형태로 가져옵니다.
  2. 버튼 각각을 순회하면서 클릭 이벤트 수신기를 추가합니다.

  아무 버튼이나 클릭하면 createParagraph() 함수가 실행됩니다.
*/

const buttons = document.querySelectorAll('button');

for (const button of buttons) {
  button.addEventListener('click', createParagraph);
}
```

> **참고:** 보통은 많은 주석이 적은 주석보다 낫지만, 너무 많은 주석으로 이 변수가 어떤 변수인지 설명(변수 이름을 더 직관적으로 나타낼 수 있을 수도)하고 있거나 매우 간단한 작업의 설명(코드가 과하게 복잡할지도)에 투자하고 있지는 않은지 주의하세요.

## [정리](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/What_is_JavaScript#정리)

이로써 JavaScript의 세계로 한 걸음 내딛었습니다. 왜 JavaScript를 써야 하는지, 그리고 어떤 일을 할 수 있는지 먼저 알아보기 위해 이론으로 시작했고, 약간의 코드 예제를 봤고, 여러분의 웹 사이트 어디에 JavaScript가 들어갈 수 있는지 등등을 알아봤습니다.

지금 당장은 JavaScript가 약간 버겁게 느껴질지도 모르겠지만, 너무 걱정하지 마세요. 이 과정에 걸친 간단한 단계들을 밟다 보면 전체적인 모습이 보일겁니다. 다음 글에서는 [바로 실전으로 들어가서](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash) 스스로 JavaScript 예제를 만들어보도록 합시다.



---

# JavaScript에 발 담그기

JavaScript에 관한 약간의 이론과, JavaScript가 할 수 있는 일을 알아봤으니 이제 간단한 JavaScript 프로그램을 만들어보면서 개발 과정이 어떤 느낌인지 알아보겠습니다. "숫자 알아맞히기" 게임을 차근차근 따라 만들어봅시다.

| 구분              | 내용                                                         |
| :---------------- | ------------------------------------------------------------ |
| 필요한 사전 지식: | 기본적인 컴퓨터 이해 능력, HTML과 CSS 기초, JavaScript 기본 개념. |
| 목표:             | JavaScript 코드를 직접 작성해보고, JavaScript 프로그램 개발에 어떤 것이 필요한지 느껴보기. |

시작하기 전에, 우리의 기대 목표를 먼저 확실하게 짚고 넘어가겠습니다. 이 글을 끝낸 뒤에도, 함께 따라서 작성한 코드를 이해하지 못할 수 있습니다. 그래도 괜찮습니다. 이 시간은 JavaScript의 기능들이 함께 어떻게 동작하는지, JavaScript 코드를 작성하는 게 어떤 느낌인지 알려드리기 위한 시간일 뿐입니다. 여기에서 사용한 기능들은 이후의 다른 여러 글에서 자세하게 짚어볼 테니, 지금 당장은 굳이 모두 이해하려고 애쓰지 마세요.

> **참고:** 함수와 반복문처럼 JavaScript의 많은 코드 기능은 다른 프로그래밍 언어에도 존재합니다. 코드 구문은 다르더라도, 그 개념은 많은 부분 동일합니다.



## [프로그래머처럼 사고하기](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash#프로그래머처럼_사고하기)

프로그래밍에서 가장 배우기 어려운 부분은 학습할 구문이 아니라, 그 구문을 어떻게 현실 세계의 문제에 적용할까 하는 문제입니다. 그러니 프로그래머처럼 생각해야 합니다. 프로그램의 동작 방식에 대한 설명을 보고, 어떤 코드 기능이 필요한지 파악하고, 이 둘을 어떻게 함께 활용할지 고민하는 과정이죠.

노력과 프로그래밍 구문에 대한 경험, 연습, 그리고 약간의 창의성이 필요한 일입니다. 하면 할수록 더 잘 하게 될 겁니다. 단 5분 만에 "프로그래머 뇌"를 갖게 될 것이라고 약속할 수는 없겠지만, 프로그래머 사고법을 연습할 수 있는 많은 기회를 이 과정 안에서 제공하겠습니다.

그러면, 이 글에서 만들게 될 프로그램을 직접 볼 시간입니다. 주어진 과제를 해결하기 쉬운 더 작은 단위의 작업으로 쪼개는 일반적인 과정도 살펴보겠습니다.

## [예제 — 숫자 맞히기 게임](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash#예제_—_숫자_맞히기_게임)

간단한 게임을 만들겠습니다. 바로 아래에서 결과물을 먼저 확인해 보세요.

<iframe width="900" height="300" src="https://mdn.github.io/learning-area/javascript/introduction-to-js-1/first-splash/number-guessing-game" loading="lazy" style="box-sizing: border-box; border: 1px solid var(--border-primary); max-width: 100%; width: 639.438px; background: rgb(255, 255, 255); border-radius: var(--elem-radius);"></iframe>

한 번 플레이해 보세요. 다음 단계로 넘어가기 전에 먼저 게임에 익숙해져 보세요.

이 게임을 만들기 위해서, 여러분의 직장 상사가 게임의 기능을 안내해 주고 가는 상황을 상상해 봅시다.

>간단한 숫자 맞히기 게임을 만들어주세요. 무작위로 선택한 1과 100 사이의 수를 플레이어가 10턴 내에 알아내는 게임입니다. 각 턴 후에는 플레이어가 고른 숫자가 맞았는지 아니면 틀렸는지 알려줘야 하고, 만약 틀린 경우에는 플레이어의 숫자가 너무 낮았는지, 아니면 너무 높았는지를 알려줘야 합니다. 플레이어가 이전에 추측한 숫자들의 기록도 보여줘야 합니다. 게임은 플레이어가 숫자를 맞히거나, 턴을 모두 소모하면 끝납니다. 게임이 끝난 뒤에는 다시 게임을 시작할 수 있는 옵션을 제공해야 합니다.



상사의 안내 이후에 해야 할 일은, 최대한 프로그래머의 마음가짐을 갖추고, 이 과제를 간단하고 작은 작업으로 나눠보는 것입니다.

1. 1과 100 사이의 무작위 숫자를 생성하기.
2. 플레이어의 현재 턴을 기록하기. 1부터 시작.
3. 플레이어가 정답을 추측할 수 있는 방법을 제공.
4. 플레이어가 자신의 추측 기록을 확인할 수 있도록, 제출하는 수를 먼저 어딘가에 기록하기.
5. 그 후, 제출한 수가 정답인지 확인.
6. 만약 정답이라면...
   1. 축하 메시지 보여주기.
   2. 이후에 플레이어가 다른 숫자를 제출하면 게임이 망가질 수 있으므로 제출을 막기.
   3. 게임을 다시 시작할 수 있는 컨트롤 보여주기.
7. 만약 오답이고 아직 플레이어 턴이 남아있다면...
   1. 오답임을 알려주고 제출한 숫자가 너무 높았는지 아니면 너무 낮았는지 보여주기.
   2. 다른 숫자의 제출을 허용하기.
   3. 턴 숫자를 1 늘리기.
8. 만약 오답인데 플레이어 턴이 남아있지 않다면...
   1. 플레이어에게 게임이 끝났음을 알려주기.
   2. 이후에 플레이어가 다른 숫자를 제출하면 게임이 망가질 수 있으므로 제출을 막기.
   3. 게임을 다시 시작할 수 있는 컨트롤 보여주기.
9. 게임을 다시 시작하면 게임 로직과 UI를 완전히 초기화하고 1번으로 돌아가기.

이제 다음 단계로, 위의 작업들을 코드로 작성해서 예제 프로그램으로 구성하는 법과, 이에 필요한 JavaScript 기능들을 알아보겠습니다.

### [초기 설정](https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash#초기_설정)

자습서를 시작하기 전에 [number-guessing-game-start.html](https://github.com/mdn/learning-area/blob/main/javascript/introduction-to-js-1/first-splash/number-guessing-game-start.html) 파일의 로컬 복사본을 만들어 주세요([실행 결과 미리보기](https://mdn.github.io/learning-area/javascript/introduction-to-js-1/first-splash/number-guessing-game-start.html)). 저장한 파일을 텍스트 에디터와 웹 브라우저에서 각자 열어주세요. 지금 당장은 헤딩과 게임 플레이 방법을 알려주는 문단, 숫자를 입력할 양식을 볼 수는 있지만 아무런 동작도 하지 않을 겁니다.

``` html
<!DOCTYPE html>
<html lang="en-us">
  <head>
    <meta charset="utf-8">

    <title>Number guessing game</title>

    <style>
      html {
        font-family: sans-serif;
      }

      body {
        width: 50%;
        max-width: 800px;
        min-width: 480px;
        margin: 0 auto;
      }
      
      .form input[type="number"] {
        width: 200px;
      }

      .lastResult {
        color: white;
        padding: 3px;
      }
    </style>
  </head>

  <body>
    <h1>Number guessing game</h1>

    <p>We have selected a random number between 1 and 100. See if you can guess it in 10 turns or fewer. We'll tell you if your guess was too high or too low.</p>

    <div class="form">
      <label for="guessField">Enter a guess: </label>
      <input type="number" min="1" max="100" required id="guessField" class="guessField">
      <input type="submit" value="Submit guess" class="guessSubmit">
    </div>

    <div class="resultParas">
      <p class="guesses"></p>
      <p class="lastResult"></p>
      <p class="lowOrHi"></p>
    </div>

    <script>

      // Your JavaScript goes here

    </script>
  </body>
</html>
```



우리가 코드를 추가할 부분은 HTML의 아래쪽, [`<script>`](https://developer.mozilla.org/ko/docs/Web/HTML/Element/script) 요소 안입니다.

