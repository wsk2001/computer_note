# 모던 JavaScript 튜토리얼 - 2 브라우저



## <span style="color:green">브라우저: 문서, 이벤트, 인터페이스 (파트2)</span>

파트2에선 브라우저 내 페이지를 다루는 방법에 대해 학습합니다. 요소 추가, 요소의 사이즈와 위치를 조정하는 방법을 비롯하여 동적으로 인터페이스를 생성하는 방법, 인터페이스를 기반으로 사용자와 상호작용 하는 방법 등에 대해 학습할 예정입니다.

[문서](https://ko.javascript.info/document)

- [브라우저 환경과 다양한 명세서](https://ko.javascript.info/browser-environment)
- [DOM 트리](https://ko.javascript.info/dom-nodes)
- [DOM 탐색하기](https://ko.javascript.info/dom-navigation)
- [getElement*, querySelector*로 요소 검색하기](https://ko.javascript.info/searching-elements-dom)
- [주요 노드 프로퍼티](https://ko.javascript.info/basic-dom-node-properties)
- [속성과 프로퍼티](https://ko.javascript.info/dom-attributes-and-properties)
- [문서 수정하기](https://ko.javascript.info/modifying-document)
- [스타일과 클래스](https://ko.javascript.info/styles-and-classes)
- [요소 사이즈와 스크롤](https://ko.javascript.info/size-and-scroll)
- [브라우저 창 사이즈와 스크롤](https://ko.javascript.info/size-and-scroll-window)
- [좌표](https://ko.javascript.info/coordinates)
- 

[이벤트 기초](https://ko.javascript.info/events)

- [브라우저 이벤트 소개](https://ko.javascript.info/introduction-browser-events)
- [버블링과 캡처링](https://ko.javascript.info/bubbling-and-capturing)
- [이벤트 위임](https://ko.javascript.info/event-delegation)
- [브라우저 기본 동작](https://ko.javascript.info/default-browser-action)
- [커스텀 이벤트 디스패치](https://ko.javascript.info/dispatch-events)

[UI 이벤트](https://ko.javascript.info/event-details)

- [마우스 이벤트](https://ko.javascript.info/mouse-events-basics)
- [Moving the mouse: mouseover/out, mouseenter/leave](https://ko.javascript.info/mousemove-mouseover-mouseout-mouseenter-mouseleave)
- [드래그 앤 드롭과 마우스 이벤트](https://ko.javascript.info/mouse-drag-and-drop)
- [Pointer events](https://ko.javascript.info/pointer-events)
- [Keyboard: keydown and keyup](https://ko.javascript.info/keyboard-events)
- [Scrolling](https://ko.javascript.info/onscroll)

[폼과 폼 조작](https://ko.javascript.info/forms-controls)

- [폼 프로퍼티와 메서드](https://ko.javascript.info/form-elements)
- [focus와 blur](https://ko.javascript.info/focus-blur)
- [이벤트: change, input, cut, copy, paste](https://ko.javascript.info/events-change-input)
- [submit 이벤트와 메서드](https://ko.javascript.info/forms-submit)

[문서와 리소스 로딩](https://ko.javascript.info/loading)

- [DOMContentLoaded, load, beforeunload, unload 이벤트](https://ko.javascript.info/onload-ondomcontentloaded)
- [defer, async 스크립트](https://ko.javascript.info/script-async-defer)
- [Resource loading: onload and onerror](https://ko.javascript.info/onload-onerror)

[기타](https://ko.javascript.info/ui-misc)

- [Mutation observer](https://ko.javascript.info/mutation-observer)
- [Selection and Range](https://ko.javascript.info/selection-range)
- [이벤트 루프와 매크로태스크, 마이크로태스크](https://ko.javascript.info/event-loop)





# 1.1 브라우저 환경과 다양한 명세서

자바스크립트는 본래 웹 브라우저에서 사용하려고 만든 언어입니다. 이후 진화를 거쳐 다양한 사용처와 플랫폼을 지원하는 언어로 변모하였습니다.

자바스크립트가 돌아가는 플랫폼은 *호스트(host)* 라고 불립니다. 호스트는 브라우저, 웹서버, 심지어는 커피 머신이 될 수도 있습니다. 각 플랫폼은 해당 플랫폼에 특정되는 기능을 제공하는데, 자바스크립트 명세서에선 이를 *호스트 환경(host environment)* 이라고 부릅니다.

호스트 환경은 랭귀지 코어(ECMAScript – 옮긴이)에 더하여 플랫폼에 특정되는 객체와 함수를 제공합니다. 웹브라우저는 웹페이지를 제어하기 위한 수단을 제공하고, Node.js는 서버 사이드 기능을 제공해주죠.

아래 그림은 호스트 환경이 웹 브라우저일 때 사용할 수 있는 기능을 개괄적으로 보여줍니다.

![](.\Images\02-01.브라우저-DOM.jpg)

최상단엔 `window`라 불리는 ‘루트’ 객체가 있습니다. `window` 객체는 2가지 역할을 합니다.

1. [전역 객체](https://ko.javascript.info/global-object) 챕터에서 설명한 바와 같이, 자바스크립트 코드의 전역 객체입니다.
2. '브라우저 창(browser window)'을 대변하고, 이를 제어할 수 있는 메서드를 제공합니다.

아래 예시에선 `window` 객체를 전역 객체로 사용하고 있습니다.

```javascript
function sayHi() {
  alert("안녕하세요.");
}

// 전역 함수는 전역 객체(window)의 메서드임
window.sayHi();
```

아래 예시에선 `window` 객체가 브라우저 창을 대변하고 있으며, 이를 이용해 창의 높이를 출력합니다.

```javascript
alert(window.innerHeight); // 창 내부(inner window) 높이
```

`window` 객체엔 다양한 메서드와 프로퍼티가 있는데, 추후 자세히 살펴보도록 하겠습니다.

## [문서 객체 모델(DOM)](https://ko.javascript.info/browser-environment#ref-879)

문서 객체 모델(Document Object Model, DOM)은 웹 페이지 내의 모든 콘텐츠를 객체로 나타내줍니다. 이 객체는 수정 가능합니다.

`document` 객체는 페이지의 기본 ‘진입점’ 역할을 합니다. `document` 객체를 이용해 페이지 내 그 무엇이든 변경할 수 있고, 원하는 것을 만들 수도 있습니다.

예시:

```javascript
// 배경을 붉은색으로 변경하기
document.body.style.background = "red";

// 1초 후 원상태로 복구하기
setTimeout(() => document.body.style.background = "", 1000);
```

문서 객체 모델은 예시에서 소개한 `document.body.style` 외에도 수많은 기능을 제공합니다. 관련 프로퍼티와 메서드에 대한 정보는 WHATWG의 [DOM Living Standard](https://dom.spec.whatwg.org/)에서 찾을 수 있습니다.

**DOM은 브라우저만을 위한 모델이 아닙니다.**

DOM 명세서엔 문서의 구조와 이를 조작할 수 있는 객체에 대한 설명이 담겨있습니다. 그런데 브라우저가 아닌 곳에서도 DOM을 사용하는 경우가 있습니다.

HTML 페이지를 다운로드하고 이를 가공해주는 서버 사이드 스크립트에서도 DOM을 사용합니다. 이런 스크립트에선 명세서 일부만을 지원하겠지만요.

**스타일링을 위한 CSSOM**

CSS 규칙과 스타일시트(stylesheet)는 HTML과는 다른 구조를 띱니다. 따라서 CSS 규칙과 스타일시트를 객체로 나타내고 이 객체를 어떻게 읽고 쓸 수 있을지에 대한 설명을 담은 별도의 명세서, [CSS 객체 모델(CSS Object Model, CSSOM)](https://www.w3.org/TR/cssom-1/)이 존재합니다.

CSSOM은 문서에 쓰이는 스타일 규칙을 수정할 때 DOM과 함께 쓰입니다. 그런데 CSS 규칙은 대부분 정적이기 때문에 CSSOM을 실무에서 자주 접하지는 않을 겁니다. 자바스크립트를 이용해 CSS 규칙을 추가 혹은 제거해야 하는 경우는 극히 드물긴 하지만, 이때 CSSOM이 사용됩니다.

## [브라우저 객체 모델(BOM)](https://ko.javascript.info/browser-environment#ref-880)

브라우저 객체 모델(Browser Object Model, BOM)은 문서 이외의 모든 것을 제어하기 위해 브라우저(호스트 환경)가 제공하는 추가 객체를 나타냅니다.

예시:

- [navigator](https://developer.mozilla.org/ko/docs/Web/API/Window/navigator) 객체는 브라우저와 운영체제에 대한 정보를 제공합니다. 객체엔 다양한 프로퍼티가 있는데, 가장 잘 알려진 프로퍼티는 현재 사용 중인 브라우저 정보를 알려주는 `navigator.userAgent`와 브라우저가 실행 중인 운영체제(Windows, Linux, Mac 등) 정보를 알려주는 `navigator.platform`입니다.
- [location](https://developer.mozilla.org/ko/docs/Web/API/Window/location) 객체는 현재 URL을 읽을 수 있게 해주고 새로운 URL로 변경(redirect)할 수 있게 해줍니다.

아래 예시는 `location` 객체를 어떻게 활용할 수 있을지 보여줍니다.

```javascript
alert(location.href); // 현재 URL을 보여줌
if (confirm("위키피디아 페이지로 가시겠습니까?")) {
  location.href = "https://wikipedia.org"; // 새로운 페이지로 넘어감
}
```

`alert/confirm/prompt` 역시 BOM의 일부입니다. 문서와 직접 연결되어 있지 않지만, 사용자와 브라우저 사이의 커뮤니케이션을 도와주는 순수 브라우저 메서드이죠.

**다양한 명세**

BOM은 [HTML 명세서](https://html.spec.whatwg.org/)의 일부입니다.

BOM에 관련된 명세가 따로 있지는 않습니다. [https://html.spec.whatwg.org](https://html.spec.whatwg.org/)에서 볼 수 있는 HTML 명세서는 태그, HTML 속성(attribute) 같은 ‘HTML’ 뿐만 아니라 다양한 객체와 메서드, 브라우저에서만 사용되는 DOM 확장을 다룹니다. 이 모든 것이 HTML 기술에 속하기 때문입니다. HTML 명세서엔 이 외에도 [https://spec.whatwg.org](https://spec.whatwg.org/)에 있는 내용도 들어갑니다.

## [요약](https://ko.javascript.info/browser-environment#ref-881)

표준에 대하여 이야기하면서 다음 명세서들을 알아보았습니다.

- DOM 명세서

  문서 구조, 조작, 이벤트에 관한 설명이 담겨있고, [https://dom.spec.whatwg.org](https://dom.spec.whatwg.org/)에서 볼 수 있습니다.

- CSSOM 명세서

  스타일시트와 스타일 규칙, 이 둘을 어떻게 조작할 수 있는지, 이 둘과 문서 사이의 관계를 어떻게 조작할 수 있는지에 대한 설명이 담겨있고, https://www.w3.org/TR/cssom-1/에서 볼 수 있습니다.

- HTML 명세서

  태그 등의 HTML 언어, `setTimeout`, `alert`, `location` 등의 다양한 브라우저 기능을 정의한 BOM에 대한 설명이 담겨있고, [https://html.spec.whatwg.org](https://html.spec.whatwg.org/)에서 볼 수 있습니다. DOM 명세서에 다양한 프로퍼티와 메서드를 추가해 확장한 명세서입니다.

몇몇 클래스에 대한 설명은 https://spec.whatwg.org/에서 확인할 수 있습니다.

배울 게 많지만, 모든 걸 한꺼번에 다루고 기억하기엔 그 양이 너무 많기 때문에 지금까지 소개해 드린 링크를 잘 기록해 놓으시기 바랍니다.

프로퍼티나 메서드에 대한 설명을 읽고 싶을 때 Mozilla 재단의 매뉴얼 https://developer.mozilla.org/en-US/search을 찾아보는 것도 좋긴 하지만, 명세서에서 관련 설명을 찾는 게 더 나을 때도 있기 때문입니다. 명세서에 있는 설명은 복잡하고 내용도 더 많긴 하지만 명세서를 읽는 습관을 들이다 보면 기본 지식을 탄탄하게 쌓을 수 있습니다.

검색창에 ‘WHATWG [용어]’ 혹은 'MDN [용어]'로 검색하면 명세서나 MDN문서에서 원하는 내용을 쉽게 찾을 수 있습니다. [https://google.com?q=whatwg+localstorage](https://google.com/?q=whatwg+localstorage), [https://google.com?q=mdn+localstorage](https://google.com/?q=mdn+localstorage) 처럼 말이죠.

자 이제 UI에서 핵심적인 역할을 하는 DOM에 대해 본격적으로 살펴보도록 합시다.



# 1.2 DOM 트리

HTML을 지탱하는 것은 태그(tag)입니다.

문서 객체 모델(DOM)에 따르면, 모든 HTML 태그는 객체입니다. 태그 하나가 감싸고 있는 ‘자식’ 태그는 중첩 태그(nested tag)라고 부릅니다. 태그 내의 문자(text) 역시 객체입니다.

이런 모든 객체는 자바스크립트를 통해 접근할 수 있고, 페이지를 조작할 때 이 객체를 사용합니다.

`document.body`는 `<body>` 태그를 객체로 나타낸 것이죠.

아래 예시를 실행하면 `<body>`가 3초간 붉은색으로 변경됩니다.

```javascript
document.body.style.background = 'red'; // 배경을 붉은색으로 변경하기

setTimeout(() => document.body.style.background = '', 3000); // 원상태로 복구하기
```

위 예시에선 `document.body`의 배경색을 바꾸기 위해 `style.background`을 사용했는데, 이 외에도 다양한 프로퍼티가 존재합니다.

- `innerHTML` – 해당 노드의 HTML 콘텐츠
- `offsetWidth` – 해당 노드의 너비(픽셀)
- 기타 등등

이어지는 챕터에서 DOM을 조작하는 방법에 대해 배울 예정인데, 그 전에 먼저 DOM의 구조에 대해 알아야 합니다.

## [DOM 예제](https://ko.javascript.info/dom-nodes#ref-217)

간단한 문서를 이용해 DOM 구조에 대해 알아봅시다.

```markup
<!DOCTYPE HTML>
<html>
<head>
  <title>사슴에 관하여</title>
</head>
<body>
  사슴에 관한 진실.
</body>
</html>
```

DOM은 HTML을 아래와 같이 태그 트리 구조로 표현합니다.

![image-20231231103210012](.\Images\image-20231231103210012.png)

▾HTML▾HEAD#text ↵␣␣␣␣▾TITLE#text 사슴에 관하여#text ↵␣␣#text ↵␣␣▾BODY#text ↵␣␣사슴에 관한 진실.

위 그림에서 요소 노드를 클릭하면 그 자식들을 보거나 숨길 수 있습니다.

트리에 있는 노드는 모두 객체입니다.

태그는 *요소 노드(element node)* (혹은 그냥 요소)이고, 트리 구조를 구성합니다. `<html>`은 루트 노드가 되고, `<head>`와 `<body>`는 `루트 노드의 자식이 됩니다.

요소 내의 문자는 *텍스트(text) 노드*가 됩니다. 위 그림에서 `#text`를 확인해보세요. 텍스트 노드는 문자열만 담습니다. 자식 노드를 가질 수 없고, 트리의 끝에서 잎 노드(leaf node)가 됩니다.

위 그림에서 `<title>` 태그는 `"사슴에 관하여"`라는 텍스트 노드를 자식으로 갖습니다.

텍스트 노드에 있는 특수문자를 눈여겨보시기 바랍니다.

- 새 줄(newline): `↵` (자바스크립트에선 `\n`로 표시)
- 공백(space): `␣`

새 줄과 공백은 글자나 숫자처럼 항상 유효한 문자로 취급됩니다. 따라서 이 두 특수문자는 텍스트 노드가 되고, DOM의 일부가 됩니다. 위 HTML 문서를 보면 `<head>`와 `<title>`사이에 새 줄과 약간의 공백이 있는 것을 볼 수 있는데, 이런 특수문자 역시 `#text` 노드가 됩니다.

텍스트 노드 생성엔 두 가지 예외가 있습니다.

1. 역사적인 이유로, `<head>` 이전의 공백과 새 줄은 무시됩니다.
2. HTML 명세서에서 모든 콘텐츠는 `body` 안쪽에 있어야 한다고 했으므로, `</body>` 뒤에 무언가를 넣더라도 그 콘텐츠는 자동으로 `body` 안쪽으로 옮겨집니다. 따라서 `</body>` 뒤엔 공백이 있을 수 없습니다.

두 예외를 제외하곤 아주 간단합니다. 문서 내에 공백이 있다면 다른 문자와 마찬가지로 텍스트 노드가 됩니다. 그리고 공백을 지우면 텍스트 노드도 사라집니다.

공백이 없는 텍스트 노드만으로 HTML 문서를 구성하려면 HTML을 아래와 같이 만들어야 합니다.

```markup
<!DOCTYPE HTML>
<html><head><title>사슴에 관하여</title></head><body>사슴에 관한 진실.</body></html>
```

![image-20231231103505009](.\Images\image-20231231103505009.png)



**문자열 양 끝 공백과 공백만 있는 텍스트 노드는 개발자 도구에서 보이지 않습니다.**

DOM을 다룰 때 키게 되는 브라우저 개발자 도구(곧 다룰 예정임)에선 문자 맨 앞이나 끝쪽의 공백과 태그 사이의 새 줄 때문에 만들어지는 비어있는 텍스트 노드가 나타나지 않습니다.

개발자 도구는 이런 방식으로 화면을 덜 차지하게 만들어졌습니다.

본 튜토리얼에서도 중요하지 않은 텍스트 노드는 그림에서 생략하도록 하겠습니다. 공백이나 새 줄이 만들어내는 공간은 HTML 문서가 브라우저상에 어떻게 표현되는지 대개는 영향을 끼치지 않습니다.

## [자동 교정](https://ko.javascript.info/dom-nodes#ref-218)

기형적인 HTML을 만나면 브라우저는 DOM 생성과정에서 HTML을 자동으로 교정합니다.

예를 들어 가장 최상위 태그는 항상 `<html>`이어야 하는데 문서에 `<html>` 태그가 없는 경우, 문서 최상위에 이를 자동으로 넣어주죠. 따라서 DOM에는 `<html>`에 대응하는 노드가 항상 있습니다. `<body>`도 같은 방식이 적용됩니다.

만약 HTML 파일에 `"안녕하세요."`라는 문장 하나만 저장된 상황이라면, 브라우저가 자동으로 이 문장을 `<html>` 과 `<body>`로 감싸줍니다. 그리고 `<head>`도 더해줘서 아래와 같은 DOM이 만들어집니다.

![image-20231231103610697](.\Images\image-20231231103610697.png)

DOM 생성과정에서 브라우저는 문서에 있는 에러 등, 닫는 태그가 없는 에러 등을 자동으로 처리합니다.

닫는 태그가 없는 경우:

```markup
<p>안녕하세요
<li>엄마
<li>그리고
<li>아빠
```

이렇게 태그 짝이 안 맞아도 브라우저는 태그를 읽고, 자동으로 빠진 부분을 채워 넣어 줍니다. 따라서 최종 결과물은 정상적인 DOM이 됩니다.

![image-20231231103711212](.\Images\image-20231231103711212.png)

**테이블엔 언제나 `<tbody>`가 있습니다.**

테이블은 조금 흥미롭습니다. DOM 명세서에선 테이블에 반드시 `<tbody>`가 있어야 한다고 못 박아 놓았지만, HTML에선 `<tbody>`를 생략하곤 합니다. 이때, 브라우저는 자동으로 DOM에 `<tbody>`를 만들어줍니다.

HTML:

```markup
<table id="table"><tr><td>1</td></tr></table>
```

브라우저가 만들어 낸 DOM 구조:

![image-20231231103856503](.\Images\image-20231231103856503.png)

보이시죠? `<tbody>`가 어디선가 나타났습니다. 테이블을 다룰 땐 위 내용을 상기해 갑자기 나타난 `<tbody>`때문에 놀라지 않도록 합시다.

## [기타 노드 타입](https://ko.javascript.info/dom-nodes#ref-219)

요소와 텍스트 노드 외에도 다양한 노드 타입이 있습니다.

주석도 노드가 됩니다.

```markup
<!DOCTYPE HTML>
<html>
<body>
  사슴에 관한 진실.
  <ol>
    <li>사슴은 똑똑합니다.</li>
    <!-- comment -->
    <li>그리고 잔꾀를 잘 부리죠!</li>
  </ol>
</body>
</html>
```

![image-20231231104001117](.\Images\image-20231231104001117.png)

트리에 *주석 노드(comment node)* 라는 새로운 노드 타입이 등장했습니다. 현재 `#comment`로 표현되는 주석 노드는 두 텍스트 노드 사이에 존재합니다.

주석은 화면 출력물에 영향을 주지 않는데, 왜 DOM에는 추가되는지 의아해할 수도 있습니다. 주석 노드는 HTML에 뭔가 있다면 반드시 DOM 트리에 추가되어야 한다는 규칙 때문에 DOM에 추가된 것입니다.

**HTML 안의 모든 것은 (심지어 그것이 주석이더라도) DOM을 구성합니다.**

HTML 문서 최상단에 위치하는 `<!DOCTYPE...>` 지시자 또한 DOM 노드가 됩니다. 이 노드는 DOM 트리의 `<html>` 바로 위에 위치합니다. 본 튜토리얼에선 이 노드를 다루지 않을 예정이라 다이어그램에도 표시는 하지 않을 것입니다. 하지만 존재하는 노드입니다.

문서 전체를 나타내는 `document` 객체 또한 DOM 노드입니다.

노드 타입은 총 [열두 가지](https://dom.spec.whatwg.org/#node) 인데, 실무에선 주로 다음 네 가지 노드를 다룹니다.

1. DOM의 '진입점"이 되는 `문서(document)` 노드
2. HTML 태그에서 만들어지며, DOM 트리를 구성하는 블록인 요소 노드(element node)
3. 텍스트를 포함하는 텍스트 노드(text node)
4. 화면에 보이지는 않지만, 정보를 기록하고 자바스크립트를 사용해 이 정보를 DOM으로부터 읽을 수 있는 주석(comment) 노드

## [DOM 구조 직접 보기](https://ko.javascript.info/dom-nodes#ref-220)

[Live DOM Viewer](http://software.hixie.ch/utilities/js/live-dom-viewer/)에 들어가면 실시간으로 DOM 구조를 볼 수 있습니다. 사이트에 들어가 DOM 구조를 보고 싶은 HTML 문서를 입력하면 HTML이 바로 DOM으로 바뀌어 출력됩니다.

Live DOM Viewer를 이용하는 것 말고도 브라우저 개발자 도구를 사용해 DOM을 탐색할 수 있습니다. 실제론 대부분의 개발자가 개발자 도구를 사용합니다.

[elk.html](https://ko.javascript.info/article/dom-nodes/elk.html) 페이지를 열고, 개발자 도구를 연 다음 Elements 패널으로 이동해봅시다.

아래와 같은 화면이 보일 겁니다.

![image-20231231104106591](.\Images\image-20231231104106591.png)

이제 개발자 도구에서 DOM을 볼 수 있게 되었네요. 요소를 클릭하면 자세한 내용을 볼 수도 있습니다.

개발자 도구를 이용해 DOM 구조를 볼 땐, 생략된 부분이 있다는 점에 유의하시길 바랍니다. 텍스트 노드는 그냥 텍스트로만 표시되고, 띄어쓰기만 있는 빈 텍스트 노드는 나타나지 않습니다. 개발 중엔 대부분 요소 노드만 다루기 때문에 이 점이 문제가 되지는 않지만 말이죠.

좌측 상단의 버튼을 클릭한 후, 마우스 등의 포인터 장비로 웹페이지 상의 노드를 클릭하면, Elements 패널의 해당 노드로 이동하게 되어 노드를 자세히 살펴볼 수 있습니다. 특정 요소가 DOM 트리 내 어디에 있는지 바로 확인할 수 있기 때문에 방대한 HTML을 다룰 때 아주 유용합니다.

검사 하고 싶은 요소에 포인터를 댄 후에 마우스 오른쪽 버튼 클릭했을 때 나타나는 컨텍스트 메뉴에서 'Inspect’를 클릭해도 같은 기능을 사용할 수 있습니다.

![image-20231231104205568](.\Images\image-20231231104205568.png)

Elements 패널 우측엔 여러 하위 패널이 있습니다.

- **Styles** – 내장 규칙(회색 배경)을 포함하여 현재 선택한 요소에 적용된 CSS 규칙을 일률적으로 보여줍니다. 하단부 박스에 있는 크기(dimension), 마진(margin), 패딩(padding)을 비롯한 스타일 대부분을 Styles 패널에서 바로 수정해 볼 수 있습니다.
- **Computed** – 현재 선택한 요소에 적용된 CSS 규칙을 프로퍼티 기준으로 보여줍니다. CSS 상속 등을 통해 적용된 규칙도 볼 수 있습니다.
- **Event Listeners** – DOM 요소에 붙은 이벤트 리스너를 볼 수 있습니다. 자세한 내용은 다음 파트에서 다룰 예정입니다.
- 기타 등등

각 패널이 무슨 역할을 하는지 알아보려면 직접 클릭해 보고 이리저리 살펴보는 게 제일 좋습니다. 대부분의 값은 바로 수정해 볼 수 있습니다.

## [콘솔을 사용해 DOM 다루기](https://ko.javascript.info/dom-nodes#ref-221)

개발자 도구를 이용해 DOM을 탐색하다 보면, DOM에 자바스크립트를 적용해 보고 싶어질 때가 생깁니다. 노드를 가져와서 코드로 해당 노드를 수정하고, 브라우저상에서 결과물을 바로 볼 수 있게 말이죠. 이럴 때 쓸 수 있는 몇 가지 팁을 알려드리겠습니다.

먼저 아래 작업을 해주세요.

- Elements 패널에서 첫 번째 `<li>`를 선택하세요.
- Esc를 눌러 Elements 패널 아래에 콘솔을 띄우세요.

이제 가장 마지막에 선택했던 요소는 `$0`으로, 그 이전에 선택했던 요소는 `$1`로 접근할 수 있습니다.

예를 들어 콘솔 창에 `$0.style.background = 'red'`을 입력하면 아래와 같이 첫 번째 list 아이템이 붉은색으로 표시되는 걸 볼 수 있습니다.

![image-20231231104319153](.\Images\image-20231231104319153.png)

이 방법을 사용하면 Elements 패널에 있는 노드를 콘솔창으로 가져올 수 있습니다.

또 다른 방법도 있습니다. DOM 노드를 참조하는 변수가 있는 경우 콘솔에 `inspect(node)` 명령어를 입력하면 Elements 패널에서 해당 요소가 강조되는 것을 확인할 수 있습니다.

아니면 아래에서 `document.body`를 입력해서 한 것처럼, DOM 노드를 콘솔 창에 출력해서 탐색해볼 수도 있습니다.

![image-20231231104418609](.\Images\image-20231231104418609.png)

지금까지 소개해 드린 팁들은 디버깅 용도입니다. 자바스크립트를 이용해 DOM에 접근하고 수정하는 방법은 다음 챕터부터 배우도록 하겠습니다.

브라우저의 개발자 도구는 개발을 도와주는 훌륭한 도구입니다. 개발자 도구를 사용하면 DOM을 탐색할 수 있고, 새로운 것을 적용해 본 후 어떤 변화나 버그가 생기는지 바로 확인할 수 있습니다.

## [요약](https://ko.javascript.info/dom-nodes#ref-222)

HTML/XML 문서는 브라우저 안에서 DOM 트리로 표현됩니다.

- 태그는 요소 노드가 되고 트리 구조를 형성합니다.
- 문자는 텍스트 노드가 됩니다.
- 이 외에 HTML 내의 모든 것은 DOM을 구성합니다. 주석까지도 말이죠.

개발자 도구를 사용하면 DOM을 검사하고, 바로 수정해 볼 수 있습니다.

지금까지 소개해 드린 개발자 도구 사용법은 가장 많이 사용되고, 중요한 기능 위주였습니다. Chrome 개발자 도구 문서 사이트 https://developers.google.com/web/tools/chrome-devtools로 가시면 다양한 기능을 살펴볼 수 있습니다. 툴을 배울 때 가장 좋은 방법은 이리저리 클릭해보고 메뉴를 직접 열어보는 것입니다. 다양한 옵션을 확인하는 것 또한 잊으면 안 되죠. 이런 과정을 통해 도구가 어느 정도 손에 익으면 문서를 정독해 부족한 나머지를 채우면 됩니다.

DOM 노드는 노드 간 이동, 수정 등을 가능하게 해주는 프로퍼티와 메서드를 지원합니다. 다음 챕터에서 이에 대해 다뤄보도록 하겠습니다.



# 1.3 DOM 탐색하기

DOM을 이용하면 요소와 요소의 콘텐츠에 무엇이든 할 수 있습니다. 하지만 무언가를 하기 전엔, 당연히 조작하고자 하는 DOM 객체에 접근하는 것이 선행되어야 합니다.

DOM에 수행하는 모든 연산은 `document` 객체에서 시작합니다. `document` 객체는 DOM에 접근하기 위한 '진입점’이죠. 진입점을 통과하면 어떤 노드에도 접근할 수 있습니다.

아래 그림은 DOM 노드 탐색이 어떤 관계를 통해 이루어지는지를 보여줍니다.

![image-20231231104718634](.\Images\image-20231231104718634.png)

화살표로 나타낸 관계에 대하여 좀 더 자세히 알아봅시다.

## [트리 상단의 documentElement와 body](https://ko.javascript.info/dom-navigation#ref-626)

DOM 트리 상단의 노드들은 `document`가 제공하는 프로퍼티를 사용해 접근할 수 있습니다.

- `<html>` = `document.documentElement`

  `document`를 제외하고 DOM 트리 꼭대기에 있는 문서 노드는 `<html>` 태그에 해당하는 `document.documentElement`입니다.

- `<body>` = `document.body`

  `document.body`는 `<body>` 요소에 해당하는 DOM 노드로, 자주 쓰이는 노드 중 하나입니다.

- `<head>` = `document.head`

  `<head>` 태그는 `document.head`로 접근할 수 있습니다.

**`document.body`가 `null`일 수도 있으니 주의하세요.**

스크립트를 읽는 도중에 존재하지 않는 요소는 스크립트에서 접근할 수 없습니다.

브라우저가 아직 `document.body`를 읽지 않았기 때문에 `<head>` 안에 있는 스크립트에선 `document.body`에 접근하지 못하죠.

따라서 아래 예시에서 첫 번째 `alert` 창엔 `null`이 출력됩니다.

```markup
<html>

<head>
  <script>
    alert( "HEAD: " + document.body ); // null, 아직 <body>에 해당하는 노드가 생성되지 않았음
  </script>
</head>

<body>

  <script>
    alert( "BODY: " + document.body ); // HTMLBodyElement, 지금은 노드가 존재하므로 읽을 수 있음
  </script>

</body>
</html>
```

**DOM의 나라에서 `null`은 '존재하지 않음’을 의미합니다.**

DOM에서 `null` 값은 '존재하지 않음’이나 '해당하는 노드가 없음’을 의미합니다.

## [childNodes, firstChild, lastChild로 자식 노드 탐색하기](https://ko.javascript.info/dom-navigation#ref-627)

앞으로 사용할 두 가지 용어를 먼저 정의하고 설명을 이어나가도록 하겠습니다.

- **자식 노드(child node, children)** 는 바로 아래의 자식 요소를 나타냅니다. 자식 노드는 부모 노드의 바로 아래에서 중첩 관계를 만듭니다. `<head>`와 `<body>`는 `<html>`요소의 자식 노드입니다.
- **후손 노드(descendants)** 는 중첩 관계에 있는 모든 요소를 의미합니다. 자식 노드, 자식 노드의 모든 자식 노드 등이 후손 노드가 됩니다.

아래 예시에서 `<body>`는 `<div>`와 `<ul>`, 몇 개의 빈 텍스트 노드를 자식 노드로 갖습니다.

```markup
<html>
<body>
  <div>시작</div>

  <ul>
    <li>
      <b>항목</b>
    </li>
  </ul>
</body>
</html>
```

<div>나 <ul>같은 <body>의 자식 요소뿐만 아니라 <ul>의 자식 노드인 <li>와 <b>같이 더 깊은 곳에 있는 중첩 요소도 <body>의 후손 노드가 됩니다.

**`childNodes` 컬렉션은 텍스트 노드를 포함한 모든 자식 노드를 담고 있습니다.**

아래 예시를 실행하면 `document.body`의 자식 노드가 출력됩니다.

```markup
<html>
<body>
  <div>시작</div>

  <ul>
    <li>항목</li>
  </ul>

  <div>끝</div>

  <script>
    for (let i = 0; i < document.body.childNodes.length; i++) {
      alert( document.body.childNodes[i] ); // Text, DIV, Text, UL, ... , SCRIPT
    }
  </script>
  ...추가 내용...
</body>
</html>
```

예시를 실행하면 흥미로운 점이 하나 발견됩니다. 마지막에 `<script>`가 출력되죠. `<script>` 아래 더 많은 내용(…추가 내용…)이 있지만, 스크립트 실행 시점엔 브라우저가 추가 내용은 읽지 못한 상태이기 때문에 스크립트 역시 추가 내용을 보지 못해서 이런 결과가 나타났습니다.

**`firstChild`와 `lastChild` 프로퍼티를 이용하면 첫 번째, 마지막 자식 노드에 빠르게 접근할 수 있습니다.**

이 프로퍼티들은 단축키 같은 역할을 합니다. 자식 노드가 존재하면 아래 비교문은 항상 참이 됩니다.

```javascript
elem.childNodes[0] === elem.firstChild
elem.childNodes[elem.childNodes.length - 1] === elem.lastChild
```

참고로 자식 노드의 존재 여부를 검사할 땐 함수 `elem.hasChildNodes()`를 사용할 수도 있습니다.

### [DOM 컬렉션](https://ko.javascript.info/dom-navigation#ref-628)

위에서 살펴본 `childNodes`는 마치 배열 같아 보입니다. 하지만 `childNodes`는 배열이 아닌 반복 가능한(iterable, 이터러블) 유사 배열 객체인 *컬렉션(collection)* 입니다.

`childNodes`는 컬렉션이기 때문에 아래와 같은 특징을 가집니다.

1. `for..of`를 사용할 수 있습니다.

```javascript
for (let node of document.body.childNodes) {
  alert(node); // 컬렉션 내의 모든 노드를 보여줍니다.
}
```

이터러블이기 때문에 `Symbol.iterator` 프로퍼티가 구현되어 있어서 `for..of`를 사용하는 것이 가능하죠.

1. 배열이 아니기 때문에 배열 메서드를 쓸 수 없습니다.

```javascript
alert(document.body.childNodes.filter); // undefined (filter 메서드가 없습니다.)
```

첫 번째 특징은 장점으로 작용합니다. 두 번째 특징은 썩 좋지는 않지만 `Array.from`을 사용하면 ‘진짜’ 배열을 만들 수 있기 때문에 참을 만합니다. 컬렉션에 배열 메서드를 사용하고 싶다면 `Array.from`을 적용합시다.

```javascript
alert( Array.from(document.body.childNodes).filter ); // function
```

**DOM 컬렉션은 읽는 것만 가능합니다.**

DOM 컬렉션을 비롯해 이번 챕터에서 설명하는 *모든* 탐색용 프로퍼티는 읽기 전용입니다.

`childNodes[i] = ...`를 이용해 자식 노드를 교체하는 게 불가능하죠.

DOM을 변경하려면 다른 메서드가 필요합니다. 다음 챕터에서 이 메서드에 대해 살펴보겠습니다.

**DOM 컬렉션은 살아있습니다.**

몇몇 예외사항을 제외하고 거의 모든 DOM 컬렉션은 *살아있습니다*. DOM의 현재 상태를 반영한다는 말이죠.

`elem.childNodes`를 참조하고 있는 도중에 DOM에 새로운 노드가 추가되거나 삭제되면, 변경사항이 컬렉션에도 자동으로 반영됩니다.

**컬렉션에 `for..in` 반복문을 사용하지 마세요,**

컬렉션은 `for..of`를 이용해 순회할 수 있습니다. 그런데 가끔 `for..in`을 사용하려는 사람들이 있죠.

`for..in`은 절대 사용하지 마세요. `for..in` 반복문은 객체의 모든 열거 가능한 프로퍼티를 순회합니다. 컬렉션엔 거의 사용되지 않는 ‘추가’ 프로퍼티가 있는데, 이 프로퍼티까지 순회 대상에 포함하길 원하지 않으실 거니까요.

```markup
<body>
<script>
  // 0, 1, length, item, values 등 불필요한 프로퍼티까지도 출력됩니다.
  for (let prop in document.body.childNodes) alert(prop);
</script>
</body>
```

## [형제와 부모 노드](https://ko.javascript.info/dom-navigation#ref-629)

같은 부모를 가진 노드는 *형제(sibling) 노드* 라고 부릅니다.

`<head>`와 `<body>`는 대표적인 형제 노드입니다.

```markup
<html>
  <head>...</head><body>...</body>
</html>
```

- `<body>`는 `<head>`의 ‘다음(next)’ 혹은 '우측(right)'에 있는 형제 노드입니다.
- `<head>`는 `<body>`의 ‘이전(previous)’ 혹은 '좌측(left)'에 있는 형제 노드입니다.

다음 형제 노드에 대한 정보는 `nextSibling`, 이전 형제 노드에 대한 정보는 `previousSibling` 프로퍼티에서 찾을 수 있습니다.

부모 노드에 대한 정보는 `parentNode` 프로퍼티를 이용해 참조할 수 있습니다.

예시:

```javascript
// <body>의 부모 노드는 <html>입니다
alert( document.body.parentNode === document.documentElement ); // true

// <head>의 다음 형제 노드는 <body>입니다.
alert( document.head.nextSibling ); // HTMLBodyElement

// <body>의 이전 형제 노드는 <head>입니다.
alert( document.body.previousSibling ); // HTMLHeadElement
```

## [요소 간 이동](https://ko.javascript.info/dom-navigation#ref-630)

지금까지 언급한 탐색 관련 프로퍼티는 *모든* 종류의 노드를 참조합니다. `childNodes`를 이용하면 텍스트 노드, 요소 노드, 심지어 주석 노드까지 참조할 수 있죠.

하지만 실무에서 텍스트 노드나 주석 노드는 잘 다루지 않습니다. 웹 페이지를 구성하는 태그의 분신인 요소 노드를 조작하는 작업이 대다수이죠.

이런 실제 상황을 토대로 DOM *요소 노드* 탐색이 어떻게 이루어지는지 알아봅시다.

![image-20231231104825724](.\Images\image-20231231104825724.png)

위 그림 속 관계는 챕터 앞쪽에서 다뤘던 관계와 유사해 보입니다. `Element`라는 단어가 추가된 점만 다르네요.

- `children` 프로퍼티는 해당 요소의 자식 노드 중 요소 노드만을 가리킵니다.
- `firstElementChild`와 `lastElementChild` 프로퍼티는 각각 첫 번째 자식 요소 노드와 마지막 자식 요소 노드를 가리킵니다.
- `previousElementSibling`과 `nextElementSibling`은 형제 요소 노드를 가리킵니다.
- `parentElement` 는 부모 요소 노드를 가리킵니다.

**부모가 요소가 \*아니라면\* `parentElement`는 어떻게 되나요?**

`parentElement` 프로퍼티는 부모 '요소 노드’를 반환하는 반면 `parentNode` 프로퍼티는 '종류에 상관없이 부모 노드’를 반환합니다. 대개 두 프로퍼티는 같은 노드를 반환합니다.

그런데 `document.documentElement`아래와 같은 상황에서는 다른 노드를 반환합니다.

```javascript
alert( document.documentElement.parentNode ); // document
alert( document.documentElement.parentElement ); // null
```

반환 값이 다른 이유는 `<html>`에 해당하는 `document.documentElement`의 부모는 `document`인데, `document` 노드는 요소 노드가 아니기 때문입니다. 따라서 위 예시에서 `parentNode`는 의도한 대로 `document` 노드를 반환하지만, `parentElement`는 `null`을 반환합니다.

이런 사소한 차이는 임의의 요소 노드 `elem`에서 시작해 `<html>`까지 거슬러 올라가고 싶은데, `document`까지는 가고 싶지 않을 때 유용하게 활용할 수 있습니다.

```javascript
while(elem = elem.parentElement) { // <html>까지 거슬러 올라갑니다.
  alert( elem );
}
```

앞서 보았던 예시에서 `childNodes`를 `children`으로 대체해봅시다. 요소 노드만 출력되는 것을 확인할 수 있습니다.

```markup
<html>
<body>
  <div>시작</div>

  <ul>
    <li>항목</li>
  </ul>

  <div>끝</div>

  <script>
    for (let elem of document.body.children) {
      alert(elem); // DIV, UL, DIV, SCRIPT
    }
  </script>
  ...
</body>
</html>
```

## [테이블 탐색하기](https://ko.javascript.info/dom-navigation#dom-navigation-tables)

지금까지 DOM 탐색 기본 프로퍼티를 알아보았습니다.

그런데 일부 DOM 요소 노드는 편의를 위해 기본 프로퍼티 외에 추가적인 프로퍼티를 지원합니다.

테이블이 가장 대표적입니다. 좀 더 자세히 알아봅시다.

<table> 요소는 기본 프로퍼티 이외에 다음과 같은 프로퍼티를 지원합니다.

- `table.rows`는 `<tr>`요소를 담은 컬렉션을 참조합니다.
- `table.caption/tHead/tFoot`은 각각 `<caption>`, `<thead>`, `<tfoot>` 요소를 참조합니다.
- `table.tBodies`는 `<tbody>` 요소를 담은 컬렉션을 참조합니다. 표준에 따르면, 테이블 내에 여러 개의 `<tbody>`가 존재하는 게 가능한데, 최소한 하나는 무조건 있어야 합니다. HTML 문서에는 `<tbody>`가 없더라도 브라우저는 `<tbody>` 노드를 DOM에 자동으로 추가합니다.

**`<thead>`, `<tfoot>`, `<tbody>`** 요소는 `rows` 프로퍼티를 지원합니다.

- `tbody.rows`는 tbody 내 `<tr>` 요소 컬렉션을 참조합니다.

**`<tr>` 요소는 다음 프로퍼티를 지원합니다.**

- `tr.cells`는 주어진 `<tr>` 안의 모든 `<td>`, `<th>`을 담은 컬렉션을 반환합니다.
- `tr.sectionRowIndex`는 주어진 `<tr>`이 `<thead>/<tbody>/<tfoot>`안쪽에서 몇 번째 줄에 위치하는지를 나타내는 인덱스를 반환합니다.
- `tr.rowIndex`는 `<table>`내에서 해당 `<tr>`이 몇 번째 줄인 지를 나타내는 숫자를 반환합니다.

**`<td>`와 `<th>` 요소는 다음 프로퍼티를 지원합니다.**

- `td.cellIndex`는 `<td>`나 `<th>`가 속한 `<tr>`에서 해당 셀이 몇 번째인지를 나타내는 숫자를 반환합니다.

용례:

```html
<table id="table">
  <tr>
    <td>일</td><td>이</td>
  </tr>
  <tr>
    <td>삼</td><td>사</td>
  </tr>
</table>

<script>
  // '이'가 적힌 td를 가져옴(첫 번째 줄, 두 번째 칸)
  let td = table.rows[0].cells[1];
  td.style.backgroundColor = "red"; // 강조
</script>
```

표에 관련한 공식 명세서는 [tabular data](https://html.spec.whatwg.org/multipage/tables.html)에서 찾아볼 수 있습니다.

테이블과 마찬가지로, HTML 폼(form)에만 쓸 수 있는 탐색 프로퍼티도 있습니다. 폼을 배우면서 이 프로퍼티에 대해서도 살펴보도록 하겠습니다.

## [요약](https://ko.javascript.info/dom-navigation#ref-631)

탐색 프로퍼티를 사용하면 이웃 노드로 바로 이동할 수 있습니다.

탐색 프로퍼티는 크게 두 개의 집합으로 나뉩니다.

- 모든 노드에 적용 가능한 `parentNode`, `childNodes`, `firstChild`, `lastChild`, `previousSibling`, `nextSibling`
- 요소 노드에만 적용 가능한 `parentElement`, `children`, `firstElementChild`, `lastElementChild`, `previousElementSibling`, `nextElementSibling`

테이블과 같은 몇몇 DOM 요소는 추가 프로퍼티와 콘텐츠에 접속할 수 있게 해주는 컬렉션을 제공합니다.

## [과제](https://ko.javascript.info/dom-navigation#tasks)

### [자식 DOM](https://ko.javascript.info/dom-navigation#ref-632)



중요도: 5

아래 페이지를 살펴봅시다.

```html
<html>
<body>
  <div>사용자:</div>
  <ul>
    <li>John</li>
    <li>Pete</li>
  </ul>
</body>
</html>
```

아래 DOM 노드에 접근할 방법을 최소 한 가지 이상씩 생각해보세요.

- `<div>` DOM 노드
- `<ul>` DOM 노드
- 두 번째 `<li>` (Pete)

해답

### [형제 노드에 관한 질문](https://ko.javascript.info/dom-navigation#ref-633)



중요도: 5

임의의 DOM 요소 노드 `elem`이 있다고 가정해봅시다.

- `elem.lastChild.nextSibling`은 항상 `null`일까요?
- `elem.children[0].previousSibling`은 항상 `null`일까요?

해답

### [모든 대각선 셀 선택하기](https://ko.javascript.info/dom-navigation#ref-634)



중요도: 5

테이블의 모든 대각선 셀을 빨간색으로 칠하는 코드를 작성해보세요.

<table>에서 모든 대각선 <td>를 가져와 아래 코드를 이용해 칠해야 합니다.

```javascript
// td는 테이블 셀에 대한 참조가 되어야 합니다.
td.style.backgroundColor = 'red';
```

결과는 아래와 같습니다.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/select-diagonal-cells/solution/" style="display: block; border: 0px; width: 798px; height: 180px; background: var(--iframeBg);"></iframe>

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/aqCGMJnpfkoazXMv?p=preview)

해답



# 1.4 getElement*, querySelector*로 요소 검색하기

요소들이 가까이 붙어있다면 앞서 학습한 DOM 탐색 프로퍼티를 사용해 목표 요소에 쉽게 접근할 수 있습니다. 그런데, 요소들이 가까이 붙어있지 않은 경우도 있기 마련입니다. 상대 위치를 이용하지 않으면서 웹 페이지 내에서 원하는 요소 노드에 접근하는 방법은 없는 걸까요?

이번 챕터에선 이를 가능하게 해주는 메서드에 대해 알아보겠습니다.

## [document.getElementById 혹은 id를 사용해 요소 검색하기](https://ko.javascript.info/searching-elements-dom#ref-990)

요소에 `id` 속성이 있으면 위치에 상관없이 메서드 `document.getElementById(id)`를 이용해 접근할 수 있습니다.

예시:

```markup
<div id="elem">
  <div id="elem-content">Element</div>
</div>

<script>
  // 요소 얻기
  let elem = document.getElementById('elem');

  // 배경색 변경하기
  elem.style.background = 'red';
</script>
```

이에 더하여 `id` 속성값을 그대로 딴 전역 변수를 이용해 접근할 수도 있습니다.

```markup
<div id="elem">
  <div id="elem-content">Element</div>
</div>

<script>
  // 변수 elem은 id가 'elem'인 요소를 참조합니다.
  elem.style.background = 'red';

  // id가 elem-content인 요소는 중간에 하이픈(-)이 있기 때문에 변수 이름으로 쓸 수 없습니다.
  // 이럴 땐 대괄호(`[...]`)를 사용해서 window['elem-content']로 접근하면 됩니다.
</script>
```

그런데 이렇게 요소 id를 따서 자동으로 선언된 전역변수는 동일한 이름을 가진 변수가 선언되면 무용지물이 됩니다.

```markup
<div id="elem"></div>

<script>
  let elem = 5; // elem은 더이상 <div id="elem">를 참조하지 않고 5가 됩니다.

  alert(elem); // 5
</script>
```

**id를 따서 만들어진 전역변수를 요소 접근 시 사용하지 마세요.**

`id`에 대응하는 전역변수는 [명세서](http://www.whatwg.org/specs/web-apps/current-work/#dom-window-nameditem)의 내용을 구현해 만들어진 것으로 표준이긴 하지만 하위 호환성을 위해 남겨둔 동작입니다.

브라우저는 스크립트의 네임스페이스와 DOM의 네임스페이스를 함께 사용할 수 있도록 해서 개발자의 편의를 도모합니다. 그런데 이런 방식은 스크립트가 간단할 땐 괜찮지만, 이름이 충돌할 가능성이 있기 때문에 추천하는 방식은 아닙니다. HTML을 보지 않은 상황에서 코드만 보고 변수의 출처를 알기 힘들다는 단점도 있습니다.

본 튜토리얼에선 간결성을 위해 요소의 출처가 명확한 경우, `id`를 사용해 요소에 직접 접근하는 방법을 사용할 예정입니다.

실무에선 `document.getElementById`를 사용하시길 바랍니다.

**id는 중복되면 안 됩니다.**

`id`는 유일무이해야 합니다. 문서 내 요소의 `id` 속성값은 중복되어선 안 됩니다.

같은 `id`를 가진 요소가 여러 개 있으면 `document.getElementById`같이 `id`를 이용해 요소를 검색하는 메서드의 동작이 예측 불가능해집니다. 검색된 여러 요소 중 어떤 요소를 반환할지 판단하지 못해 임의의 요소가 반환되죠. 문서 내 동일 `id`가 없도록 해 이런 일을 방지하도록 합시다.

**`anyNode.getElementById`가 아닌 `document.getElementById`**

`getElementById`는 `document` 객체를 대상으로 해당 `id`를 가진 요소 노드를 찾아 줍니다. 문서 노드가 아닌 다른 노드엔 호출할 수 없습니다.

## [querySelectorAll](https://ko.javascript.info/searching-elements-dom#querySelectorAll)

`elem.querySelectorAll(css)`은 다재다능한 요소 검색 메서드입니다. 이 메서드는 `elem`의 자식 요소 중 주어진 CSS 선택자에 대응하는 요소 모두를 반환합니다.

아래 예시는 마지막 `<li>`요소 모두를 반환합니다.

```markup
<ul>
  <li>1-1</li>
  <li>1-2</li>
</ul>
<ul>
  <li>2-1</li>
  <li>2-2</li>
</ul>
<script>
  let elements = document.querySelectorAll('ul > li:last-child');

  for (let elem of elements) {
    alert(elem.innerHTML); // "1-2", "2-2"
  }
</script>
```

`querySelectorAll`은 CSS 선택자를 활용할 수 있다는 점에서 아주 유용합니다.

**가상 클래스도 사용할 수 있습니다.**

querySelectorAll에는 `:hover`나 `:active` 같은 CSS 선택자의 가상 클래스(pseudo-class)도 사용할 수 있습니다. `document.querySelectorAll(':hover')`을 사용하면 마우스 포인터가 위에 있는(hover 상태인) 요소 모두를 담은 컬렉션이 반환됩니다. 이때 컬렉션은 DOM 트리 최상단에 위치한 `<html>`부터 가장 하단의 요소 순으로 채워집니다.

## [querySelector](https://ko.javascript.info/searching-elements-dom#querySelector)

`elem.querySelector(css)`는 주어진 CSS 선택자에 대응하는 요소 중 첫 번째 요소를 반환합니다.

`elem.querySelectorAll(css)[0]`과 동일하죠. `elem.querySelectorAll(css)[0]`은 선택자에 해당하는 *모든* 요소를 검색해 첫 번째 요소만을 반환하고, `elem.querySelector`는 해당하는 요소를 찾으면 검색을 멈춘다는 점에서 차이가 있습니다. `elem.querySelector`가 더 빠른 이유이죠. `querySelector`는 `querySelectorAll`에 비해 코드의 길이가 짧다는 장점도 있습니다.

## [matches](https://ko.javascript.info/searching-elements-dom#ref-991)

지금까지 소개한 모든 메서드는 DOM 검색에 쓰입니다.

[elem.matches(css)](http://dom.spec.whatwg.org/#dom-element-matches)는 DOM을 검색하는 일이 아닌 조금 다른 일을 합니다. 이 메서드는 요소 `elem`이 주어진 CSS 선택자와 일치하는지 여부를 판단해줍니다. 일치한다면 `true`, 아니라면 `false`를 반환하죠.

요소가 담겨있는 배열 등을 순회해 원하는 요소만 걸러내고자 할 때 유용합니다.

예시:

```markup
<a href="http://example.com/file.zip">...</a>
<a href="http://ya.ru">...</a>

<script>
  // document.body.children가 아니더라도 컬렉션이라면 이 메서드를 적용할 수 있습니다.
  for (let elem of document.body.children) {
    if (elem.matches('a[href$="zip"]')) {
      alert("주어진 CSS 선택자와 일치하는 요소: " + elem.href );
    }
  }
</script>
```

## [closest](https://ko.javascript.info/searching-elements-dom#ref-992)

부모 요소, 부모 요소의 부모 요소 등 DOM 트리에서 특정 요소의 상위에 있는 요소들은 *조상(ancestor)* 요소라고 합니다.

메서드 `elem.closest(css)`는 `elem` 자기 자신을 포함하여 CSS 선택자와 일치하는 가장 가까운 조상 요소를 찾을 수 있게 도와줍니다.

`closest`메서드는 해당 요소부터 시작해 DOM 트리를 한 단계씩 거슬러 올라가면서 원하는 요소를 찾습니다. CSS 선택자와 일치하는 요소를 찾으면, 검색을 중단하고 해당 요소를 반환합니다.

예시:

```markup
<h1>목차</h1>

<div class="contents">
  <ul class="book">
    <li class="chapter">1장</li>
    <li class="chapter">2장</li>
  </ul>
</div>

<script>
  let chapter = document.querySelector('.chapter'); // LI

  alert(chapter.closest('.book')); // UL
  alert(chapter.closest('.contents')); // DIV

  alert(chapter.closest('h1')); // null(h1은 li의 조상 요소가 아님)
</script>
```

## [getElementsBy*](https://ko.javascript.info/searching-elements-dom#ref-993)

태그나 클래스 등을 이용해 원하는 노드를 찾아주는 메서드도 있습니다.

`querySelector`를 이용하는 게 더 편리하고 문법도 짧아서, 요즘은 이런 메서드들을 잘 쓰진 않습니다.

튜토리얼의 완성도를 높이고 오래된 스크립트에서 해당 메서드들을 만날 때 당황하지 않으시길 바라면서 이 메서드들을 잠시 언급하도록 하겠습니다.

- `elem.getElementsByTagName(tag)` – 주어진 태그에 해당하는 요소를 찾고, 대응하는 요소를 담은 컬렉션을 반환합니다. 매개변수 `tag`에 `"*"`이 들어가면, '모든 태그’가 검색됩니다.
- `elem.getElementsByClassName(className)` – class 속성값을 기준으로 요소를 찾고, 대응하는 요소를 담은 컬렉션을 반환합니다.
- `document.getElementsByName(name)` – 아주 드물게 쓰이는 메서드로, 문서 전체를 대상으로 검색을 수행합니다. 검색 기준은 `name` 속성값이고, 이 메서드 역시 검색 결과를 담은 컬렉션을 반환합니다.

예시:

```javascript
// 문서 내 모든 div를 얻습니다.
let divs = document.getElementsByTagName('div');
```

다음 예시는 표 안의 모든 `input` 태그를 찾습니다.

```markup
<table id="table">
  <tr>
    <td>나이:</td>

    <td>
      <label>
        <input type="radio" name="age" value="young" checked> 18세 미만
      </label>
      <label>
        <input type="radio" name="age" value="mature"> 18세 이상, 60세 미만
      </label>
      <label>
        <input type="radio" name="age" value="senior"> 60세 이상
      </label>
    </td>
  </tr>
</table>

<script>
  let inputs = table.getElementsByTagName('input');

  for (let input of inputs) {
    alert( input.value + ': ' + input.checked );
  }
</script>
```

**`'s'`를 절대 빠트리지 마세요!**

초보 개발자들은 가끔 `'s'`를 빼먹는 실수를 하곤 합니다. `getElement**s**ByTagName`를 써야 하는데 `getElementByTagName`을 입력하곤 하죠.

`getElementById`는 요소 하나만을 반환하기 때문에 `s`가 없습니다. `getElementsByTagName` 등의 메서드는 대응하는 요소를 담은 컬렉션을 반환하기 때문에 메서드 중간에 `"s"`가 들어갑니다.

**요소 하나가 아닌, 컬렉션을 반환합니다!**

초보자들이 자주 저지르는 실수가 하나 더 있습니다.

```javascript
// 동작하지 않는 코드
document.getElementsByTagName('input').value = 5;
```

input 요소 전체를 담은 *컬렉션*에 5를 할당하는 위 코드는 동작하지 않습니다. 아마도 본래 의도는 컬렉션 내 요소에 값을 할당하는 것이었을 겁니다.

컬렉션을 순회하거나 인덱스를 사용해 요소를 얻고 그 요소에 값을 할당하면 기존 의도대로 동작합니다. 아래와 같이 말이죠.

```javascript
// (문서에 input 요소가 있다면) 아래 코드는 잘 동작합니다.
document.getElementsByTagName('input')[0].value = 5;
```

아래는 클래스 속성의 값이 `article`인 요소를 검색해주는 예시입니다.

```markup
<form name="my-form">
  <div class="article">글</div>
  <div class="long article">내용이 긴 글</div>
</form>

<script>
  // name 속성을 이용해 검색
  let form = document.getElementsByName('my-form')[0];

  // form 내에서 클래스 이름을 이용해 검색
  let articles = form.getElementsByClassName('article');
  alert(articles.length); // 2. 클래스 속성값이 'article'인 요소는 2개입니다.
</script>
```

## [살아있는 컬렉션](https://ko.javascript.info/searching-elements-dom#ref-994)

`'getElementsBy'`로 시작하는 모든 메서드는 *살아있는* 컬렉션을 반환합니다. 문서에 변경이 있을 때마다 컬렉션이 '자동 갱신’되어 최신 상태를 유지합니다.

예시 내엔 스크립트 두 개가 있습니다.

1. 첫 번째 스크립트는 `<div>`에 상응하는 요소를 담은 컬렉션에 대한 참조를 만듭니다. 스크립트가 실행되는 시점에 이 컬렉션의 길이는 `1`입니다.
2. 두 번째 스크립트는 문서에 `<div>`가 하나 더 추가된 이후에 실행됩니다. 따라서 컬렉션의 길이는 `2`가 됩니다.

```markup
<div>첫 번째 div</div>

<script>
  let divs = document.getElementsByTagName('div');
  alert(divs.length); // 1
</script>

<div>두 번째 div</div>

<script>
  alert(divs.length); // 2
</script>
```

반면, `querySelectorAll`은 *정적인* 컬렉션을 반환합니다. 컬렉션이 한 번 확정되면 더는 늘어나지 않습니다.

`querySelectorAll`을 사용하면 두 스크립트가 동일하게 `1`을 출력합니다.

```markup
<div>첫 번째 div</div>

<script>
  let divs = document.querySelectorAll('div');
  alert(divs.length); // 1
</script>

<div>두 번째 div</div>

<script>
  alert(divs.length); // 1
</script>
```

예시를 통해 두 방식의 차이를 살펴보았습니다. 문서에 새로운 `div`가 추가되어도 `querySelectorAll`이 반환한 컬렉션은 이를 반영하지 못합니다.

## [요약](https://ko.javascript.info/searching-elements-dom#ref-995)

DOM에서 원하는 노드를 검색하게 해주는 주요 메서드 6가지는 다음과 같습니다.

| 메서드                   | 검색 기준    | 호출 대상이 요소가 될 수 있는지에 대한 여부 | 컬렉션 갱신 여부 |
| ------------------------ | ------------ | ------------------------------------------- | ---------------- |
| `querySelector`          | CSS 선택자   | ✔                                           | -                |
| `querySelectorAll`       | CSS 선택자   | ✔                                           | -                |
| `getElementById`         | `id`         | -                                           | -                |
| `getElementsByName`      | `name`       | -                                           | ✔                |
| `getElementsByTagName`   | 태그나 `'*'` | ✔                                           | ✔                |
| `getElementsByClassName` | class        | ✔                                           | ✔                |

아마 실무에선 `querySelector`나 `querySelectorAll`을 가장 많이 사용하실 겁니다. `getElementBy`로 시작하는 메서드는 대개 오래된 스크립트에서 만날 수 있는데, 일부 이 메서드가 꼭 필요한 상황에서 쓰이는 경우도 있습니다.

이 외에 알아두면 좋을 만한 메서드는 아래와 같습니다.

- `elem.matches(css)`는 `elem`이 해당 CSS 선택자와 일치하는지 여부를 검사합니다.
- `elem.closest(css)`는 해당 CSS 선택자와 일치하는 가장 가까운 조상 요소를 탐색합니다. 이때, `elem` 자기 자신도 검색 대상에 포함됩니다.

위에선 언급하지 않았지만, 노드의 부모-자식 관계를 확인할 수 있도록 도와주는 유용한 메서드 하나를 더 소개해 드리고 마무리하겠습니다.

- `elemA.contains(elemB)`는 `elemB`가 `elemA`에 속하거나(`elemB`가 `elemA`의 후손인 경우) `elemA==elemB`일 때, 참을 반환합니다.

## [과제](https://ko.javascript.info/searching-elements-dom#tasks)

### [요소 검색하기](https://ko.javascript.info/searching-elements-dom#ref-996)



중요도: 4

테이블과 폼이 있는 문서가 있다고 가정합시다.

아래 조건에 맞는 요소는 어떻게 찾을 수 있을까요?

1. `id="age-table"`인 테이블
2. 테이블 내의 `label` 요소 모두(총 3개)
3. 테이블 내의 첫 번째 `td`(Age가 적힌 곳)
4. `name="search"`인 `form`
5. 폼의 첫 번째 `input`
6. 폼의 마지막 `input`

별도의 창에서 [table.html](https://ko.javascript.info/task/find-elements/table.html)을 열어 브라우저 내 도구를 사용해 문제를 풀어보세요.

해답



# 1.5 주요 노드 프로퍼티

DOM 노드에 대해 좀 더 알아봅시다.

이번 챕터에선 DOM 노드란 무엇인지, DOM 노드의 주요 프로퍼티는 무엇이 있는지 학습하겠습니다.

## [DOM 노드 클래스](https://ko.javascript.info/basic-dom-node-properties#ref-997)

DOM 노드는 종류에 따라 각각 다른 프로퍼티를 지원합니다. 태그 `<a>`에 대응하는 요소 노드엔 링크 관련된 프로퍼티를, `<input>`에 대응하는 요소 노드엔 입력 관련프로퍼티를 제공하죠. 텍스트 노드는 요소 노드와 다른 프로퍼티를 지원하는 것은 말할 필요도 없습니다. 그런데 모든 DOM 노드는 공통 조상으로부터 만들어지기 때문에 노드 종류는 다르지만, 모든 DOM 노드는 공통된 프로퍼티와 메서드를 지원합니다.

DOM 노드는 종류에 따라 대응하는 내장 클래스가 다릅니다.

계층 구조 꼭대기엔 [EventTarget](https://dom.spec.whatwg.org/#eventtarget)이 있는데, [Node](http://dom.spec.whatwg.org/#interface-node)는 EventTarget을, 다른 DOM 노드들은 Node 클래스를 상속받습니다.

이런 관계를 그림으로 나타내면 다음과 같습니다.

![image-20231231105346074](.\Images\image-20231231105346074.png)



각 클래스는 다음과 같은 특징을 지닙니다.

- [EventTarget](https://dom.spec.whatwg.org/#eventtarget) – 루트에 있는 ‘추상(abstract)’ 클래스로, 이 클래스에 대응하는 객체는 실제로 만들어지지 않습니다. EventTarget가 모든 DOM 노드의 베이스에 있기때문에 DOM 노드에서 '이벤트’를 사용할 수 있습니다. 자세한 내용은 곧 다룰 예정입니다.

- [Node](http://dom.spec.whatwg.org/#interface-node) – 역시 ‘추상’ 클래스로, DOM 노드의 베이스 역할을 합니다. getter 역할을 하는 `parentNode`, `nextSibling`, `childNodes` 등의 주요 트리 탐색 기능을 제공합니다. `Node` 클래스의 객체는 절대 생성되지 않습니다. 하지만 이 클래스를 상속받는 클래스는 여럿 있습니다. 텍스트 노드를 위한 `Text` 클래스와 요소 노드를 위한 `Element` 클래스, 주석 노드를 위한 `Comment`클래스는 `Node`클래스를 상속받습니다.

- [Element](http://dom.spec.whatwg.org/#interface-element) – DOM 요소를 위한 베이스 클래스입니다. `nextElementSibling`, `children` 이나 `getElementsByTagName`, `querySelector` 같이 요소 전용 탐색을 도와주는 프로퍼티나 메서드가 이를 기반으로 합니다. 브라우저는 HTML뿐만 아니라 XML, SVG도 지원하는데 `Element` 클래스는 이와 관련된 `SVGElement`, `XMLElement`, `HTMLElement` 클래스의 베이스 역할을 합니다.

- HTMLElement

   

  – HTML 요소 노드의 베이스 역할을 하는 클래스입니다. 아래 나열한 클래스들은 실제 HTML 요소에 대응하고

   

  ```
  HTMLElement
  ```

  를 상속받습니다.

  - [HTMLInputElement](https://html.spec.whatwg.org/multipage/forms.html#htmlinputelement) – `<input>` 요소에 대응하는 클래스
  - [HTMLBodyElement](https://html.spec.whatwg.org/multipage/semantics.html#htmlbodyelement) – `<body>` 요소에 대응하는 클래스
  - [HTMLAnchorElement](https://html.spec.whatwg.org/multipage/semantics.html#htmlanchorelement) – `<a>` 요소에 대응하는 클래스
  - 이외에도 다른 클래스가 많은데, 각 태그에 해당하는 클래스는 고유한 프로퍼티와 메서드를 지원합니다.

이렇게 특정 노드에서 사용할 수 있는 프로퍼티와 메서드는 상속을 기반으로 결정됩니다.

`<input>` 요소에 대응하는 DOM 객체를 예로 들어봅시다. 이 객체는 [HTMLInputElement](https://html.spec.whatwg.org/multipage/forms.html#htmlinputelement) 클래스를 기반으로 만들어집니다.

객체엔 아래에 나열한 클래스에서 상속받은 프로퍼티와 메서드가 있을 겁니다.

- `HTMLInputElement` – 입력 관련 프로퍼티를 제공하는 클래스
- `HTMLElement` – HTML 요소 메서드와 getter, setter를 제공하는 클래스
- `Element` – 요소 노드 메서드를 제공하는 클래스
- `Node` – 공통 DOM 노드 프로퍼티를 제공하는 클래스
- `EventTarget` – 이벤트 관련 기능을 제공하는 클래스
- `Object` – `hasOwnProperty`같이 ‘일반 객체’ 메서드를 제공하는 클래스

우리는 앞서 객체는 `constructor` 프로퍼티를 가진다는 걸 배운 바 있습니다. 이런 특징을 이용하면 DOM 노드 클래스 이름을 확인할 수 있습니다. `constructor` 프로퍼티는 클래스 생성자를 참조하고 이름은 `constructor.name`에 저장되어있다는 점을 이용하면 되죠.

```javascript
alert( document.body.constructor.name ); // HTMLBodyElement
```

`toString`을 사용해도 됩니다.

```javascript
alert( document.body ); // [object HTMLBodyElement]
```

상속 여부는 `instanceof`를 사용해 확인할 수 있습니다.

```javascript
alert( document.body instanceof HTMLBodyElement ); // true
alert( document.body instanceof HTMLElement ); // true
alert( document.body instanceof Element ); // true
alert( document.body instanceof Node ); // true
alert( document.body instanceof EventTarget ); // true
```

지금까지 살펴본 바와 같이 DOM 노드는 프로토타입을 기반으로 상속 관계를 갖는 일반 자바스크립트 객체입니다.

브라우저 콘솔에 `console.dir(elem)`를 입력하면 이런 관계를 쉽게 확인할 수 있습니다. `HTMLElement.prototype`, `Element.prototype`등이 콘솔에 출력될 겁니다.

**`console.dir(elem)`과 `console.log(elem)`의 차이**

브라우저 개발자 도구 대부분은 `console.log`와 `console.dir` 명령어를 지원합니다. 이 명령어들은 콘솔에 인수를 출력해줍니다. 인수가 자바스크립트 객체라면 두 명령어는 대개 같은 결과를 보여줍니다.

하지만 인수가 DOM 요소일 때는 결과가 다릅니다.

- `console.log(elem)`는 요소의 DOM 트리를 출력합니다.
- `console.dir(elem)`는 요소를 DOM 객체처럼 취급하여 출력합니다. 따라서 프로퍼티를 확인하기 쉽다는 장점이 있습니다.

`document.body`를 인수로 넘겨서 그 차이를 직접 확인해보세요.

**명세서에서 쓰이는 IDL**

명세서에선 DOM 클래스를 자바스크립트를 사용해 설명하지 않습니다. 대신 [Interface Description Language(IDL)](https://en.wikipedia.org/wiki/Interface_description_language)를 이용해 설명합니다.

IDL에선 모든 프로퍼티 앞에 타입을 붙입니다. `DOMString`과 `boolean` 같은 타입이 프로퍼티 앞에 붙죠.

명세서 일부에 주석을 달아놓았으니 함께 살펴봅시다.

```javascript
// HTMLInputElement 정의 시작
// 콜론(:)은 HTMLInputElement가 HTMLElement로 부터 상속되었다는 것을 의미합니다.
interface HTMLInputElement: HTMLElement {
  // <input> 요소와 관련된 프로퍼티와 메서드가 나열되기 시작합니다.

  // 'DOMString'은 프로퍼티 값이 문자열이라는 것을 의미합니다.
  attribute DOMString accept;
  attribute DOMString alt;
  attribute DOMString autocomplete;
  attribute DOMString value;

  // 불린 값(true/false)을 가지는 프로퍼티
  attribute boolean autofocus;
  ...
  // 'void'는 메서드의 리턴값이 없음을 의미합니다.
  void select();
  ...
}
```

## [‘nodeType’ 프로퍼티](https://ko.javascript.info/basic-dom-node-properties#ref-998)

`nodeType` 프로퍼티는 DOM 노드의 '타입’을 알아내고자 할 때 쓰이는 구식 프로퍼티입니다.

각 노드 타입은 상숫값을 가집니다.

- `elem.nodeType == 1` – 요소 노드
- `elem.nodeType == 3` – 텍스트 노드
- `elem.nodeType == 9` – 문서 객체
- 기타 노드 타입에 대한 값은 [명세서](https://dom.spec.whatwg.org/#node)에서 확인할 수 있습니다.

예시:

```markup
<body>
  <script>
  let elem = document.body;

  // 타입을 알아봅시다.
  alert(elem.nodeType); // 1 => 요소 노드

  // 첫 번째 자식 노드
  alert(elem.firstChild.nodeType); // 3 => 텍스트 노드

  // 문서 객체의 타입 확인
  alert( document.nodeType ); // 9 => 문서 객체
  </script>
</body>
```

모던 자바스크립트에선 노드의 타입을 `instanceof`나 클래스 기반의 테스트를 이용해 확인하는데, 가끔은 `nodeType`를 쓰는 게 간단할 때도 있습니다. `nodeType`은 타입 확인 하는 데만 쓸 수 있고 바꾸지는 못합니다.

## [nodeName과 tagName으로 태그 이름 확인하기](https://ko.javascript.info/basic-dom-node-properties#ref-999)

`nodeName`이나 `tagName` 프로퍼티를 사용하면 DOM 노드의 태그 이름을 알아낼 수 있습니다.

예시:

```javascript
alert( document.body.nodeName ); // BODY
alert( document.body.tagName ); // BODY
```

그럼 `tagName`과 `nodeName`의 차이는 없는 걸까요?

물론 있습니다. 미묘하지만 이름에서 그 차이를 유추할 수 있죠.

- `tagName` 프로퍼티는 `요소` 노드에만 존재합니다.

- ```
  nodeName
  ```

  은 모든

   

  ```
  Node
  ```

  에 있습니다.

  - 요소 노드를 대상으로 호출하면 `tagName`과 같은 역할을 합니다.
  - 텍스트 노드, 주석 노드 등에선 노드 타입을 나타내는 문자열을 반환합니다.

`nodeName`은 모든 노드에서 지원되지만, `tagName`은 `Element` 클래스로부터 유래되었기 때문에 요소 노드에서만 지원됩니다.

`document`와 주석 노드를 사용해 `tagName`과 `nodeName`의 차이점을 확인해 봅시다.

```markup
<body><!-- 주석 -->

  <script>
    // 주석 노드를 대상으로 두 프로퍼티 비교
    alert( document.body.firstChild.tagName ); // undefined (요소가 아님)
    alert( document.body.firstChild.nodeName ); // #comment

    // 문서 노드를 대상으로 두 프로퍼티 비교
    alert( document.tagName ); // undefined (요소가 아님)
    alert( document.nodeName ); // #document
  </script>
</body>
```

요소 노드만 다루고 있다면 `tagName`과 `nodeName`에는 차이가 없으므로 둘 다 사용할 수 있습니다.

**태그 이름은 XML 모드를 제외하고 항상 대문자입니다.**

브라우저에서 HTML과 XML을 처리하는 모드는 다릅니다. 웹페이지는 대개 HTML 모드로 처리됩니다. 헤더가 `Content-Type: application/xml+xhtml`인 XML 문서를 받으면 XML 모드로 문서를 처리합니다.

HTML 모드에선 `tagName`과 `nodeName`이 모두 대문자로 변경됩니다. `<body>` 이든 `<BoDy>`이든 `BODY`가 되죠.

XML 모드에선 케이스가 ‘그대로’ 유지됩니다. XML 모드는 요즘엔 거의 사용되지 않습니다.

## [innerHTML로 내용 조작하기](https://ko.javascript.info/basic-dom-node-properties#ref-1000)

[innerHTML](https://w3c.github.io/DOM-Parsing/#widl-Element-innerHTML) 프로퍼티를 사용하면 요소 안의 HTML을 문자열 형태로 받아올 수 있습니다.

요소 안 HTML을 수정하는 것도 가능합니다. innerHTML은 페이지를 수정하는 데 쓰이는 강력한 방법의 하나입니다.

`document.body` 안의 내용을 출력하고 완전히 바꾸는 예시를 살펴봅시다.

```markup
<body>
  <p>p 태그</p>
  <div>div 태그</div>

  <script>
    alert( document.body.innerHTML ); // 내용 읽기
    document.body.innerHTML = '새로운 BODY!'; // 교체
  </script>

</body>
```

문법이 틀린 HTML을 넣으면 브라우저가 자동으로 고쳐 줍니다.

```markup
<body>

  <script>
    document.body.innerHTML = '<b>test'; // 닫는 태그를 잊음
    alert( document.body.innerHTML ); // <b>test</b> (자동으로 수정됨)
  </script>

</body>
```

**스크립트는 실행되지 않습니다.**

`innerHTML`을 사용해 문서에 `<script>` 태그를 삽입하면 해당 태그는 HTML의 일부가 되긴 하지만 실행은 되지 않습니다.

### [‘innerHTML+=’ 사용 시 주의점](https://ko.javascript.info/basic-dom-node-properties#ref-1001)

`elem.innerHTML+="추가 html"`을 사용하면 요소에 HTML을 추가할 수 있습니다.

아래와 같이 말이죠.

```javascript
chatDiv.innerHTML += "<div>안녕하세요<img src='smile.gif'/> !</div>";
chatDiv.innerHTML += "잘 지내죠?";
```

그런데 'innerHTML+='은 추가가 *아니라* 내용을 덮어쓰기 때문에 주의해서 사용해야 합니다.

기술적으로 아래 두 줄의 코드는 동일한 역할을 합니다.

```javascript
elem.innerHTML += "...";
// 위 코드는 아래 코드의 축약 버전입니다.
elem.innerHTML = elem.innerHTML + "..."
```

즉, `innerHTML+=`는 아래와 같은 일을 합니다.

1. 기존 내용 삭제
2. 기존 내용과 새로운 내용을 합친 새로운 내용을 씀

**기존 내용이 '완전히 삭제’된 후 밑바닥부터 다시 내용이 쓰여지기 때문에 이미지 등의 리소스 전부가 다시 로딩됩니다**.

`chatDiv` 예시의 `chatDiv.innerHTML+="잘 지내죠?"` 윗줄의 HTML 내용이 재생성되고 `smile.gif` 역시 다시 로딩되는 것이죠. 어딘가에 이런 리소스들을 캐싱해 놓았다면 좋았을 거라는 생각이 드는 순간이네요. `chatDiv`에 텍스트와 이미지가 많이 있었다면 내용을 다시 불러올 때 버벅임이 생기는걸 눈으로 확인하실 수 있을 겁니다.

이 외에도 `innerHTML+=`은 여러 부작용이 있습니다. 기존에 있던 텍스트를 마우스로 드래그한 상황이라면 내용을 다시 써야 하기 때문에 드래그가 해제될 겁니다. `<input>` 태그에서 사용자가 입력한 값이 사라지기도 하죠. 부작용 사례는 다양합니다.

`innerHTML` 말고 HTML을 추가하는 방법을 사용하면 이런 부작용 없이 원하는 HTML을 추가할 수 있는데, 이 방법은 곧 배우도록 하겠습니다.

## [outerHTML로 요소의 전체 HTML 보기](https://ko.javascript.info/basic-dom-node-properties#ref-1002)

`outerHTML` 프로퍼티엔 요소 전체 HTML이 담겨있습니다. `outerHTML`은 `innerHTML`에 요소 자체를 더한 것이라고 생각하시면 됩니다.

예시를 살펴봅시다.

```markup
<div id="elem">Hello <b>World</b></div>

<script>
  alert(elem.outerHTML); // <div id="elem">Hello <b>World</b></div>
</script>
```

**`innerHTML`과 달리 `outerHTML`을 사용해서 HTML을 쓸땐 요소 자체가 바뀌지 않습니다. 대신 `outerHTML`은 DOM 안의 요소를 교체합니다.**

네, 뭔가 이상하게 들리실 겁니다. 실제로도 이상하고요. 그럴 것을 예상하고 구체적인 예시를 준비해 놓았습니다.

코드를 보며 이해해 봅시다.

```markup
<div>Hello, world!</div>

<script>
  let div = document.querySelector('div');

  // div.outerHTML를 사용해 <p>...</p>로 교체
  div.outerHTML = '<p>새로운 요소</p>'; // (*)

  // 어! div가 그대로네요!
  alert(div.outerHTML); // <div>Hello, world!</div> (**)
</script>
```

뭔가 이상합니다.

`(*)`로 표시한 줄에서 `div`를 `<p>새로운 요소</p>`로 교체했기 때문에 예시를 실행하면 의도한 대로 문서(DOM)에 `<div>`가 아닌 새로운 내용이 보입니다. 그런데 `(**)`에서 기존의 `div`를 출력하네요!

이런 결과가 나타난 이유는 `outerHTML`에 하는 할당 연산이 DOM 요소(outerHTML 연산의 대상으로, 위 예시에선 변수 `div`)를 수정하지 않기 때문입니다. 할당 연산은 요소를 DOM에서 제거하고 새로운 HTML 조각을 넣습니다.

즉, `div.outerHTML=...`는 아래와 같은 일을 합니다.

- '문서’에서 `div`를 삭제
- 새로운 HTML 조각인 `<p>A new element</p>`을 삭제 후 생긴 공간에 삽입
- `div`엔 여전히 기존 값이 저장되어 있고 새로운 HTML 조각은 어디에도 저장되어있지 않음

`outerHTML`의 이런 동작 방식 때문에 `outerHTML`을 사용할 땐 실수 할 여지가 많습니다. `div.outerHTML`을 수정한 후 `div`에 새로운 내용이 들어갔다고 착각하며 작업하는 경우가 많죠. 정리하자면 이렇습니다. `innerHTML`은 `div`를 수정하지만 `outerHTML`은 `div`를 수정하지 않습니다.

그렇기 때문에 `elem.outerHTML`에 무언가를 쓸 때는 `elem`이 수정되지 않는다는 점을 꼭 명심하고 있어야 합니다. 할당받은 HTML은 `elem`이 있던 공간에 들어갑니다. 새롭게 만들어진 요소를 참조하려면 DOM 쿼리 메서드를 사용합시다.

## [nodeValue/data로 텍스트 노드 내용 조작하기](https://ko.javascript.info/basic-dom-node-properties#ref-1003)

`innerHTML` 프로퍼티는 요소 노드에만 사용할 수 있습니다.

텍스트 노드 같은 다른 타입의 노드에는 `innerHTML`과 유사한 역할을 해주는 프로퍼티인 `nodeValue`와 `data`를 사용해야 합니다. 이 두 프로퍼티는 아주 유사하고, 실무에서도 구분 없이 쓰긴 하지만 명세서상에 작은 차이가 있긴 합니다만 `data`가 좀 더 짧기 때문에 여기선 `data`를 사용하겠습니다.

텍스트 노드와 주석 노드의 내용을 읽는 예시를 실행해 봅시다.

```markup
<body>
  안녕하세요.
  <!-- 주석 -->
  <script>
    let text = document.body.firstChild;
    alert(text.data); // 안녕하세요.

    let comment = text.nextSibling;
    alert(comment.data); // 주석
  </script>
</body>
```

그런데 이쯤되면 이런 의문이 들 수 있습니다. 텍스트 노드의 내용을 읽거나 수정하는 일은 일어날 법 한데 주석 노드는 왜 이런 기능이 필요한건지 라는 의문이죠.

개발자들은 종종 아래와 같은 방식으로 정보나 지시사항을 HTML에 삽입합니다.

```markup
<!-- if isAdmin -->
  <div>관리자로 로그인하였습니다!</div>
<!-- /if -->
```

이럴 때 `data` 프로퍼티 기능을 사용해 주석 노드의 내용을 읽고 삽입된 지시사항을 처리하면 유용합니다.

## [textContent로 순수한 텍스트만](https://ko.javascript.info/basic-dom-node-properties#ref-1004)

`textContent`를 사용하면 요소 내의 *텍스트*에 접근할 수 있습니다. `<태그>`는 제외하고 오로지 텍스트만 추출할 수 있죠.

예시:

```markup
<div id="news">
  <h1>주요 뉴스!</h1>
  <p>화성인이 지구를 침공하였습니다!</p>
</div>

<script>
  // 주요 뉴스! 화성인이 지구를 침공하였습니다!
  alert(news.textContent);
</script>
```

예시를 실행하면 원래부터 `<태그>`가 없었던 것처럼 텍스트만 반환되는 것을 확인할 수 있습니다.

그런데 실무에선 텍스트 읽기를 단독으로 쓰는 경우는 흔치 않습니다.

**`textContent`를 사용하면 텍스트를 '안전한 방법’으로 쓸 수 있기 때문에 실무에선 `textContent`를 쓰기 용으로 유용하게 사용합니다.**

사용자가 입력한 임의의 문자열을 다시 출력해주는 경우를 생각해 봅시다.

- `innerHTML`을 사용하면 사용자가 입력한 문자열이 ‘HTML 형태로’ 태그와 함께 저장됩니다.
- `textContent`를 사용하면 사용자가 입력한 문자열이 ‘순수 텍스트 형태로’ 저장되기 때문에 태그를 구성하는 특수문자들이 문자열로 처리됩니다.

두 프로퍼티를 비교해봅시다.

```markup
<div id="elem1"></div>
<div id="elem2"></div>

<script>
  let name = prompt("이름을 알려주세요.", "<b>이보라</b>");

  elem1.innerHTML = name;
  elem2.textContent = name;
</script>
```

1. 첫 번째 `<div>`엔 이름이 'HTML 형태’로 저장됩니다. 입력한 태그는 태그로 해석되어 굵은 글씨가 출력되네요.
2. 두 번째 `<div>`엔 이름이 '텍스트 형태’로 저장됩니다. 따라서 입력한 값 그대로 `<b>이보라</b>`가 출력되는 것을 확인할 수 있습니다.

개발을 하다보면 사용자의 입력값을 받아 처리해야 하는 경우가 많습니다. 이때 사용자가 입력한 값은 텍스트로 처리되어야 합니다. 예상치 못한 HTML이 사이트에 침투하는 것을 막으려면 `textContent`를 사용합시다.

## [hidden 프로퍼티](https://ko.javascript.info/basic-dom-node-properties#ref-1005)

hidden 속성과 hidden 프로퍼티는 요소를 보여줄지 말지 지정할 때 사용할 수 있습니다.

`hidden`은 HTML 안에서 쓸 수도 있고 자바스크립트에서도 쓸 수 있습니다.

```markup
<div>아래 두 div를 숨겨봅시다.</div>

<div hidden>HTML의 hidden 속성 사용하기</div>

<div id="elem">자바스크립트의 hidden 프로퍼티 사용하기</div>

<script>
  elem.hidden = true;
</script>
```

`hidden`은 기술적으로 `style="display:none"`와 동일합니다. 짧다는 점만 다르죠.

`hidden`을 사용해 요소를 깜빡이게 해봅시다.

```markup
<div id="elem">깜빡이는 요소</div>

<script>
  setInterval(() => elem.hidden = !elem.hidden, 1000);
</script>
```

## [기타 프로퍼티](https://ko.javascript.info/basic-dom-node-properties#ref-1006)

지금까지 소개한 프로퍼티 외에도 DOM 요소엔 다양한 프로퍼티가 있는데, 클래스마다 특징적인 프로퍼티 몇 가지를 소개해드리겠습니다.

- `value` – `<input>`과 `<select>`, `<textarea>`의 값이 저장됩니다. 대응하는 클래스는 `HTMLInputElement`, `HTMLSelectElement` 등입니다.
- `href` – `<a href="...">`의 href 속성 값이 저장됩니다. 대응하는 클래스는 `HTMLAnchorElement`입니다.
- `id` – id 속성 값이 저장됩니다. 모든 요소 노드에서 사용할 수 있으며, 대응하는 클래스는 `HTMLElement`입니다.
- 기타 등등

예시:

```markup
<input type="text" id="elem" value="value">

<script>
  alert(elem.type); // "text"
  alert(elem.id); // "elem"
  alert(elem.value); // value
</script>
```

대부분의 표준 HTML 속성은 그에 대응하는 DOM 프로퍼티를 가지고 있는데, 위 예시와 같은 방식으로 프로퍼티에 접근할 수 있습니다.

특정 클래스에서 지원하는 프로퍼티 전체를 보고 싶다면 명세서를 읽어보면 됩니다. 예를 들어 `HTMLInputElement`에서 지원하는 프로퍼티 목록은 https://html.spec.whatwg.org/#htmlinputelement에서 찾아볼 수 있습니다.

명세서를 읽지 않고도 개발자 도구의 콘솔 창에 `console.dir(elem)`를 입력하면 해당 요소에서 지원하는 프로퍼티 목록을 빠르게 확인할 수 있습니다. 개발자 도구의 Elements 패널의 하위 패널 중 'Properties’를 선택해도 동일한 목록을 확인할 수 있습니다.

## [요약](https://ko.javascript.info/basic-dom-node-properties#ref-1007)

각 DOM 노드는 고유한 클래스에 속합니다. 클래스들은 계층 구조를 형성합니다. DOM 노드에서 지원하는 프로퍼티와 메서드는 계층 구조에서 어떤 클래스를 상속받느냐에 따라 결정됩니다.

주요 DOM 노드 프로퍼티는 다음과 같습니다.

- `nodeType`

  요소 타입을 알고 싶을 때 사용합니다. 요소 노드라면 `1`을, 텍스트 노드라면 `3`을 반환합니다. 두 타입 외에도 각 노드 타입엔 대응하는 상숫값이 있습니다. 읽기 전용입니다.

- `nodeName/tagName`

  요소 노드의 태그 이름을 알아낼 때 사용합니다. XML 모드일 때를 제외하고 태그 이름은 항상 대문자로 변환됩니다. 요소 노드가 아닌 노드에는 `nodeName`을 사용하면 됩니다. 읽기 전용입니다.

- `innerHTML`

  요소 안의 HTML을 알아낼 수 있습니다. 이 프로퍼티를 사용하면 요소 안의 HTML을 수정할 수도 있습니다.

- `outerHTML`

  요소의 전체 HTML을 알아낼 수 있습니다. `elem.outerHTML`에 무언가를 할당해도 `elem` 자체는 바뀌지 않습니다. 대신 새로운 HTML이 외부 컨텍스트에서 만들어지고, `elem`이 삭제된 자리를 채웁니다.

- `nodeValue/data`

  요소가 아닌 노드(텍스트, 주석 노드 등)의 내용을 읽을 때 쓰입니다. 두 프로퍼티는 거의 동일하게 동작합니다. 주로 `data`를 많이 사용하는 편이며 내용을 수정할 때도 이 프로퍼티를 쓸 수 있습니다.

- `textContent`

  HTML에서 모든 `<태그>`를 제외한 텍스트만 읽을 때 사용합니다. 할당 연산을 통해 무언가를 쓸 수도 있는데 이때 태그를 포함한 모든 특수문자는 문자열로 처리됩니다. 사용자가 입력한 문자를 안전한 방법으로 처리하기 때문에 원치 않는 HTML이 사이트에 삽입되는 것을 예방할 수 있습니다.

- `hidden`

  `true`로 설정하면 CSS에서 `display:none`을 설정한 것과 동일하게 동작합니다.

DOM 노드는 클래스에 따라 이 외에도 다른 프로퍼티를 가집니다. `<input>` 요소(`HTMLInputElement`)는 `value`, `type` 프로퍼티를, `<a>` 요소(`HTMLAnchorElement`)는 `href` 프로퍼티를 지원하는 것 같이 말이죠. 대부분의 표준 HTML 속성은 대응하는 DOM 프로퍼티를 가집니다.

그런데 HTML 요소와 DOM 프로퍼티가 항상 같은 것은 아닙니다. 관련 내용은 다음 챕터에서 살펴보도록 하겠습니다.

## [과제](https://ko.javascript.info/basic-dom-node-properties#tasks)

### [후손 노드 개수 세기](https://ko.javascript.info/basic-dom-node-properties#ref-1008)



중요도: 5

`ul`과 `li` 노드로 구성된 트리 구조 문서가 있다고 가정해 봅시다.

`li` 노드 전체를 대상으로 아래와 같은 작업을 하려 합니다. 조건을 만족시킬 수 있는 코드를 작성해 보세요.

1. `li` 노드 안에 있는 텍스트를 출력
2. `li` 노드 아래에 있는 모든 `<li>` 태그의 개수를 출력

[새 창에서 데모 보기](https://ko.js.cx/task/tree-info/solution/)

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/xPUwjgp34Zrh5G4r?p=preview)

해답

### [노드 타입 맞추기](https://ko.javascript.info/basic-dom-node-properties#ref-1009)



중요도: 5

스크립트를 실행 결과를 예측해보세요.

```markup
<html>

<body>
  <script>
    alert(document.body.lastChild.nodeType);
  </script>
</body>

</html>
```

해답

### [주석 안의 태그](https://ko.javascript.info/basic-dom-node-properties#ref-1010)



중요도: 3

스크립트를 실행 결과를 예측해보세요.

```markup
<script>
  let body = document.body;

  body.innerHTML = "<!--" + body.tagName + "-->";

  alert( body.firstChild.data ); // 얼럿 창엔 어떤 내용이 출력될까요?
</script>
```

해답

### [DOM 계층 구조와 'document'](https://ko.javascript.info/basic-dom-node-properties#ref-1011)



중요도: 4

`document`는 어떤 클래스에 속할까요?

DOM 계층 구조에서 `document`이 속한 클래스는 어디에 위치해 있을까요?

이 클래스는 `Node`, `Element`, `HTMLElement` 중 어떤 클래스를 상속받을까요?

해답



# 1.6 속성과 프로퍼티

브라우저는 웹페이지를 만나면 HTML을 읽어(파싱(parsing)) DOM 객체를 생성합니다. 요소 노드(element node)에서 대부분의 표준 HTML 속성(attribute)은 DOM 객체의 프로퍼티(property)가 됩니다.

태그 `<body id="page">`가 있을 때, DOM 객체에서 `body.id="page"`를 사용할 수 있는 것 같이 말이죠.

그런데 속성-프로퍼티가 항상 일대일로 매핑되지는 않습니다! 이번 챕터에선 속성과 프로퍼티를 어떻게 다룰 수 있는지, 두 가지가 언제 일대일로 매핑되는지, 언제는 매핑되지 않는지에 주의하면서 두 개념을 알아보겠습니다.

## [DOM 프로퍼티](https://ko.javascript.info/dom-attributes-and-properties#ref-1058)

앞서 내장 DOM 프로퍼티에 대해 살펴본 바 있습니다. DOM 프로퍼티의 종류는 엄청나게 많습니다. 하지만 이런 내장 프로퍼티만으로 충분하지 않은 경우 자신만의 프로퍼티를 만들 수도 있습니다.

DOM 노드(DOM node)는 자바스크립트 객체입니다. 객체를 바꿔보겠습니다.

`document.body`에 새로운 프로퍼티를 만들어봅시다.

```javascript
document.body.myData = {
  name: 'Caesar',
  title: 'Imperator'
};

alert(document.body.myData.title); // Imperator
```

메서드도 하나 추가해 보겠습니다.

```javascript
document.body.sayTagName = function() {
  alert(this.tagName);
};

document.body.sayTagName(); // BODY (sayTagName의 'this'엔 document.body가 저장됩니다.)
```

`Element.prototype` 같은 내장 프로토타입을 수정해 모든 요소 노드에서 이 메서드를 사용하게 할 수도 있습니다.

```javascript
Element.prototype.sayHi = function() {
  alert(`Hello, I'm ${this.tagName}`);
};

document.documentElement.sayHi(); // Hello, I'm HTML
document.body.sayHi(); // Hello, I'm BODY
```

예시에서 살펴본 바와 같이 DOM 프로퍼티와 메서드는 일반 자바스크립트 객체처럼 행동하므로 아래와 같은 특징을 보입니다.

- 어떤 값이든 가질 수 있습니다.
- 대·소문자를 가립니다. `elem.nodeType`는 동작하지만 `elem.NoDeTyPe`는 동작하지 않습니다.

## [HTML 속성](https://ko.javascript.info/dom-attributes-and-properties#ref-1059)

HTML에서 태그는 복수의 속성을 가질 수 있습니다. 브라우저는 HTML을 파싱해 DOM 객체를 만들 때 HTML *표준* 속성을 인식하고, 이 표준 속성을 사용해 DOM 프로퍼티를 만듭니다.

따라서 요소가 `id` 같은 *표준* 속성으로만 구성되어 있다면, 이에 해당하는 프로퍼티가 자연스레 만들어집니다. 하지만 표준이 아닌 속성일 때는 상황이 달라집니다.

예시:

```markup
<body id="test" something="non-standard">
  <script>
    alert(document.body.id); // test
    // 비표준 속성은 프로퍼티로 전환되지 않습니다.
    alert(document.body.something); // undefined
  </script>
</body>
```

한 요소에선 표준인 속성이 다른 요소에선 표준이 아닐 수 있다는 점에도 주의해야 합니다. `"type"`은 `<input>` 요소([HTMLInputElement](https://html.spec.whatwg.org/#htmlinputelement))에선 표준이지만, `<body>`([HTMLBodyElement](https://html.spec.whatwg.org/#htmlbodyelement))에선 아닙니다. 요소에 어떤 표준 속성이 있는지 알아보려면 해당 요소의 명세서에 정보를 찾을 수 있습니다.

아래 예시를 실행해봅시다.

```markup
<body id="body" type="...">
  <input id="input" type="text">
  <script>
    alert(input.type); // text
    alert(body.type); // type은 body의 표준 속성이 아니므로 DOM 프로퍼티가 생성되지 않아 undefined가 출력됩니다.
  </script>
</body>
```

이처럼 표준 속성이 아닌 경우, 이에 매핑하는 DOM 프로퍼티가 생성되지 않습니다. 그렇다면 비표준 속성은 접근할 수 없는 걸까요?

물론 방법이 있습니다. 모든 속성은 아래의 메서드를 사용해 접근할 수 있습니다.

- `elem.hasAttribute(name)` – 속성 존재 여부 확인
- `elem.getAttribute(name)` – 속성값을 가져옴
- `elem.setAttribute(name, value)` – 속성값을 변경함
- `elem.removeAttribute(name)` – 속성값을 지움

위 메서드들은 HTML에서 명시한 속성을 대상으로 동작합니다.

여기에 더하여 `elem.attributes`을 사용하면 모든 속성값을 읽을 수도 있습니다. `elem.attributes`을 호출하면 내장 클래스 [Attr](https://dom.spec.whatwg.org/#attr)를 구현한 객체들을 담은 컬렉션이 반환되는데, 객체엔 `name`과 `value` 프로퍼티가 존재합니다.

비표준 프로퍼티를 읽는 예시를 살펴봅시다.

```markup
<body something="non-standard">
  <script>
    alert(document.body.getAttribute('something')); // 비표준 속성에 접근
  </script>
</body>
```

HTML 속성은 아래와 같은 특징을 보입니다.

- 대·소문자를 가리지 않습니다. `id`와 `ID`는 동일합니다.
- 값은 항상 문자열입니다.

HTML 속성을 어떻게 다루는지에 대한 예시를 살펴봅시다.

```markup
<body>
  <div id="elem" about="Elephant"></div>

  <script>
    alert( elem.getAttribute('About') ); // (1) 'Elephant', 속성 읽기

    elem.setAttribute('Test', 123); // (2) 속성 추가하기

    alert( elem.outerHTML ); // (3) 추가된 속성 확인하기

    for (let attr of elem.attributes) { // (4) 속성 전체 나열하기
      alert( `${attr.name} = ${attr.value}` );
    }
  </script>
</body>
```

주의해서 볼 점은 다음과 같습니다.

1. `getAttribute('About')` – 첫 번째 글자가 대문자 A이지만, HTML 안에서는 모두 소문자가 됩니다. 속성은 대·소문자를 구분하지 않으므로 괜찮습니다.
2. 어떤 값이든 속성에 대입할 수 있지만, 최종적으론 문자열로 바뀝니다. 숫자 123이 문자열 `"123"` 으로 바뀌었습니다.
3. `outerHTML`을 사용하면 직접 추가한 속성을 비롯한 모든 속성을 볼 수 있습니다.
4. `attributes`가 반환하는 컬렉션은 열거 가능(iterable)합니다. 컬렉션에 담긴 각 객체의 `name`, `value` 프로퍼티를 사용하면 속성 전체에 접근할 수 있습니다.

## [프로퍼티-속성 동기화](https://ko.javascript.info/dom-attributes-and-properties#ref-1060)

표준 속성이 변하면 대응하는 프로퍼티는 자동으로 갱신됩니다. 몇몇 경우를 제외하고 프로퍼티가 변하면 속성 역시 마찬가지로 갱신됩니다.

아래 예시에서 속성 `id`가 수정되면 이에 대응하는 프로퍼티가 갱신되는 것을 확인할 수 있습니다. 그 반대도 마찬가지입니다.

```markup
<input>

<script>
  let input = document.querySelector('input');

  // 속성 추가 => 프로퍼티 갱신
  input.setAttribute('id', 'id');
  alert(input.id); // id (갱신)

  // 프로퍼티 변경 => 속성 갱신
  input.id = 'newId';
  alert(input.getAttribute('id')); // newId (갱신)
</script>
```

그런데 아래 예시의 `input.value`처럼 동기화가 속성에서 프로퍼티 방향으로만 일어나는 예외상황도 존재합니다.

```markup
<input>

<script>
  let input = document.querySelector('input');

  // 속성 추가 => 프로퍼티 갱신
  input.setAttribute('value', 'text');
  alert(input.value); // text (갱신)

  // 프로퍼티를 변경해도 속성이 갱신되지 않음
  input.value = 'newValue';
  alert(input.getAttribute('value')); // text (갱신 안됨!)
</script>
```

위 예시에선 다음을 확인할 수 있습니다.

- 속성 `value`를 수정하면 프로퍼티도 수정됩니다.
- 하지만 프로퍼티를 수정해도 속성은 수정되지 않습니다.

이런 '기능’은 유용하게 사용될 수도 있습니다. 유저의 어떤 행동 때문에 `value`가 수정되었는데 수정 전의 ‘원래’ 값으로 복구하고 싶은 경우, 속성에 저장된 값을 가지고 오면 되기 때문입니다.

## [DOM 프로퍼티 값의 타입](https://ko.javascript.info/dom-attributes-and-properties#ref-1061)

DOM 프로퍼티는 항상 문자열이 아닙니다. 체크 박스에 사용되는 `input.checked` 프로퍼티의 경우 불린 값을 가집니다.

```markup
<input id="input" type="checkbox" checked> checkbox

<script>
  alert(input.getAttribute('checked')); // 속성 값: 빈 문자열
  alert(input.checked); // 프로퍼티 값: true
</script>
```

몇 가지 다른 예를 살펴봅시다. `style` 속성의 경우 문자열이지만, `style` 프로퍼티는 객체입니다.

```markup
<div id="div" style="color:red;font-size:120%">Hello</div>

<script>
  // string
  alert(div.getAttribute('style')); // color:red;font-size:120%

  // object
  alert(div.style); // [object CSSStyleDeclaration]
  alert(div.style.color); // red
</script>
```

대부분의 프로퍼티의 값은 문자열입니다.

아주 드물긴 하지만, DOM 프로퍼티 값이 문자열이더라도 속성값과 다른 경우도 있습니다. `href` 속성이 상대 URL이나 `#hash`이더라도 `href` DOM 프로퍼티엔 항상 URL *전체* 가 저장되는 경우가 대표적인 예입니다.

예시:

```markup
<a id="a" href="#hello">link</a>
<script>
  // 속성
  alert(a.getAttribute('href')); // #hello

  // 프로퍼티
  alert(a.href ); // 실행되는 사이트 주소에 따라 http://site.com/page#hello 형태로 값이 저장됨
</script>
```

HTML에 내에 명시된 `href` 속성의 값을 정확하게 얻고 싶다면 `getAttribute`을 사용하면 됩니다.

## [비표준 속성, dataset](https://ko.javascript.info/dom-attributes-and-properties#ref-1062)

HTML을 작성할 때 우리는 대부분 표준 속성을 사용합니다. 하지만 표준이 아닌 속성도 존재합니다. 이런 비표준이 유용한 지 아닌지, 그리고 어떤 경우에 비표준 속성을 사용해야 하는지 알아봅시다.

비표준 속성은 사용자가 직접 지정한 데이터를 HTML에서 자바스크립트로 넘기고 싶은 경우나 자바스크립트를 사용해 조작할 HTML 요소를 표시하기 위해 사용할 수 있습니다.

예시:

```markup
<!-- 이름(name) 정보를 보여주는 div라고 표시 -->
<div show-info="name"></div>
<!-- 나이(age) 정보를 보여주는 div라고 표시 -->
<div show-info="age"></div>

<script>
  // 표시한 요소를 찾고, 그 자리에 원하는 정보를 보여주는 코드
  let user = {
    name: "Pete",
    age: 25
  };

  for(let div of document.querySelectorAll('[show-info]')) {
    // 원하는 정보를 필드 값에 입력해 줌
    let field = div.getAttribute('show-info');
    div.innerHTML = user[field]; // Pete가 'name'에, 25가 'age'에 삽입됨
  }
</script>
```

비표준 속성은 요소에 스타일을 적용할 때 사용되기도 합니다.

아래 예시에선 주문 상태(order state)를 나타내는 커스텀 속성 `order-state`를 사용해 주문 상태에 따라 스타일을 변경합니다.

```markup
<style>
  /* 스타일이 커스텀 속성 'order-state'에 따라 변합니다. */
  .order[order-state="new"] {
    color: green;
  }

  .order[order-state="pending"] {
    color: blue;
  }

  .order[order-state="canceled"] {
    color: red;
  }
</style>

<div class="order" order-state="new">
  A new order.
</div>

<div class="order" order-state="pending">
  A pending order.
</div>

<div class="order" order-state="canceled">
  A canceled order.
</div>
```

이렇게 커스텀 속성을 사용하는 게 `.order-state-new`, `.order-state-pending`, `order-state-canceled`같은 클래스를 사용하는 것보다 왜 선호될까요?

이유는 속성은 클래스보다 다루기 편리하다는 점 때문입니다. 속성의 상태는 아래와 같이 쉽게 변경할 수 있습니다.

```javascript
// 새 클래스를 추가하거나 지우는 것보다 더 쉽게 상태(state)를 바꿀 수 있습니다
div.setAttribute('order-state', 'canceled');
```

물론 커스텀 속성에도 문제가 발생할 수 있습니다. 비표준 속성을 사용해 코드를 작성했는데 나중에 그 속성이 표준으로 등록되게 되면 문제가 발생합니다. HTML은 살아있는 언어이기 때문에 개발자들의 요구를 반영하기 위해 지속해서 발전합니다. 따라서 이런 경우 예기치 못한 부작용이 발생할 수 있습니다.

이런 충돌 상황을 방지하기 위한 속성인 [data-*](https://html.spec.whatwg.org/#embedding-custom-non-visible-data-with-the-data-*-attributes) 가 있습니다.

**’data-'로 시작하는 속성 전체는 개발자가 용도에 맞게 사용하도록 별도로 예약됩니다. `dataset` 프로퍼티를 사용하면 이 속성에 접근할 수 있습니다.**

요소 `elem`에 이름이 `"data-about"`인 속성이 있다면 `elem.dataset.about`을 사용해 그 값을 얻을 수 있죠.

예시:

```markup
<body data-about="Elephants">
<script>
  alert(document.body.dataset.about); // Elephants
</script>
```

`data-order-state` 같이 여러 단어로 구성된 속성은 카멜 표기법(camel-cased)을 사용해 `dataset.orderState`으로 변환됩니다.

이런 특징을 사용해 주문 상태에 관한 예시를 다시 작성하면 다음과 같습니다.

```markup
<style>
  .order[data-order-state="new"] {
    color: green;
  }

  .order[data-order-state="pending"] {
    color: blue;
  }

  .order[data-order-state="canceled"] {
    color: red;
  }
</style>

<div id="order" class="order" data-order-state="new">
  A new order.
</div>

<script>
  // 읽기
  alert(order.dataset.orderState); // new

  // 수정하기
  order.dataset.orderState = "pending"; // (*)
</script>
```

`data-*` 속성은 커스텀 데이터를 안전하고 유효하게 전달해줍니다.

`data-*` 속성을 사용하면 읽기 뿐만 아니라 수정도 가능하다는 점에 유의해주세요. 속성이 수정되면 CSS가 해당 뷰를 자동으로 업데이트해 줍니다. 위 예시에서 `(*)`로 표시한 줄에서 색이 파란색으로 바뀝니다.

## [요약](https://ko.javascript.info/dom-attributes-and-properties#ref-1063)

- 속성 – HTML 안에 쓰임
- 프로퍼티 – DOM 객체 안에 쓰임

비교표:

|      | 프로퍼티                                                     | 속성                    |
| :--- | :----------------------------------------------------------- | :---------------------- |
| 타입 | 모든 타입 가능, 각 표준 프로퍼티의 타입은 명세서에 설명되어 있음 | 문자열                  |
| 이름 | 대·소문자 구분                                               | 대·소문자 구분하지 않음 |

속성과 함께 쓰이는 메서드:

- `elem.hasAttribute(name)` – 속성 존재 여부 확인
- `elem.getAttribute(name)` – 속성값을 가져옴
- `elem.setAttribute(name, value)` – 속성값을 변경함
- `elem.removeAttribute(name)` – 속성값을 지움
- `elem.attributes` – 속성의 컬렉션을 반환함

거의 모든 상황에서 속성보다는 프로퍼티를 사용하는 게 더 낫습니다. 다만 아래 사례같이 정확한 HTML 속성 값이 필요한 경우에는 프로퍼티가 적절치 않으므로 속성을 사용해야 합니다.

- 비표준 속성이 필요한 경우. 다만 속성이 `data-`로 시작하는 경우엔 `dataset`을 사용해야 합니다.
- HTML에 적힌 값 '그대로’를 읽고 싶은 경우. `href` 프로퍼티에 항상 전체 URL이 저장되는 것 같이 DOM 프로퍼티의 값과 속성 값이 다른데, '원본’값을 얻고 싶은 경우엔 속성을 사용해야 합니다.

## [과제](https://ko.javascript.info/dom-attributes-and-properties#tasks)

### [속성 가져오기](https://ko.javascript.info/dom-attributes-and-properties#ref-1064)



중요도: 5

문서에서 `data-widget-name`이라는 속성을 가진 요소를 찾고, 해당 요소의 값을 읽는 코드를 작성해 보세요.

```markup
<!DOCTYPE html>
<html>
<body>

  <div data-widget-name="menu">Choose the genre</div>

  <script>
    /* your code */
  </script>
</body>
</html>
```

해답

### [외부 링크를 주황색으로 만들기](https://ko.javascript.info/dom-attributes-and-properties#ref-1065)



중요도: 3

`style` 프로퍼티를 변경해 모든 외부 링크를 주황색으로 만들어 보세요.

외부 링크가 되기 위한 조건은 아래와 같습니다.

- `href`에 `://`가 포함되어 있어야 합니다.
- 하지만 `http://internal.com`으로 시작하지 않아야 합니다.

예시:

```markup
<a name="list">the list</a>
<ul>
  <li><a href="http://google.com">http://google.com</a></li>
  <li><a href="/tutorial">/tutorial.html</a></li>
  <li><a href="local/path">local/path</a></li>
  <li><a href="ftp://ftp.com/my.zip">ftp://ftp.com/my.zip</a></li>
  <li><a href="http://nodejs.org">http://nodejs.org</a></li>
  <li><a href="http://internal.com/test">http://internal.com/test</a></li>
</ul>

<script>
  // setting style for a single link
  let link = document.querySelector('a');
  link.style.color = 'orange';
</script>
```

결과:

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/yellow-links/solution/" style="display: block; border: 0px; width: 798px; height: 180px; background: var(--iframeBg);"></iframe>

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/mxBSv2NeKiA1PdhG?p=preview)

해답



# 1.7 문서 수정하기

‘생동감 있는’ 웹페이지를 만들기 위한 핵심은 DOM 조작에 있습니다.

이번 챕터에선 적시에 요소를 새롭게 생성하는 방법과 페이지에 있는 기존 콘텐츠를 어떻게 수정할 수 있는지 알아보겠습니다.

## [예제: 메시지 보여주기](https://ko.javascript.info/modifying-document#ref-824)

`alert` 창보다 보기 좋은 메시지 창을 출력해주는 예시를 살펴보겠습니다.

예시:

```html
<style>
.alert {
  padding: 15px;
  border: 1px solid #d6e9c6;
  border-radius: 4px;
  color: #3c763d;
  background-color: #dff0d8;
}
</style>

<div class="alert">
  <strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.
</div>
```

<iframe class="code-result__iframe" name="test-33wpvmpl1w" src="about:blank" style="display: block; border: 0px; width: 798px; height: 80px; background: var(--iframeBg);"></iframe>

위 예시에선 HTML을 사용해 메시지 창을 만들었습니다. 이번엔 같은 창을 자바스크립트를 사용해 만들어봅시다. 스타일은 HTML이나 외부 CSS 파일에 저장되어 있다고 가정하겠습니다.

## [요소 생성하기](https://ko.javascript.info/modifying-document#ref-825)

DOM 노드를 만들때 사용하는 메서드는 두 가지입니다.

- `document.createElement(tag)`

  태그 이름(`tag`)을 사용해 새로운 요소 노드를 만듦`let div = document.createElement('div');`

- `document.createTextNode(text)`

  주어진 텍스트(`text`)를 사용해 새로운 *텍스트 노드*를 만듦`let textNode = document.createTextNode('안녕하세요.');`

개발을 할 땐 위 예시(메시지가 들어가는 `div`)처럼 주로 요소 노드를 만들게 됩니다.

### [메시지 생성하기](https://ko.javascript.info/modifying-document#ref-826)

메시지가 들어갈 `div`는 세 단계로 만들 수 있습니다.

```javascript
// 1. <div> 요소 만들기
let div = document.createElement('div');

// 2. 만든 요소의 클래스를 'alert'로 설정
div.className = "alert";

// 3. 내용 채워넣기
div.innerHTML = "<strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.";
```

이렇게 세 단계를 거치면 요소가 만들어집니다. 그런데 요소를 만들긴 했지만, 아직 이 요소는 `div`라는 이름을 가진 변수에 불과하기 때문에 페이지엔 나타나지 않습니다.

## [삽입 메서드](https://ko.javascript.info/modifying-document#ref-827)

`div`가 페이지에 나타나게 하려면 `document` 내 어딘가에 `div`를 넣어줘야 합니다. `document.body`로 참조할 수 있는 `<body>`안 같은 곳에 말이죠.

요소 삽입 메서드 `append`를 사용한 코드 `document.body.append(div)`를 사용해 직접 새롭게 만든 요소 노드를 페이지에 나타나도록 해봅시다.

전체 코드는 다음과 같습니다.

```markup
<style>
.alert {
  padding: 15px;
  border: 1px solid #d6e9c6;
  border-radius: 4px;
  color: #3c763d;
  background-color: #dff0d8;
}
</style>

<script>
  let div = document.createElement('div');
  div.className = "alert";
  div.innerHTML = "<strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.";

  document.body.append(div);
</script>
```

여기서는 `document.body`에서 `append`를 호출했지만 다른 요소에도 `append` 메서드를 호출해 요소를 넣을 수 있습니다. `div.append(anotherElement)`를 호출해 `<div>`에 무언가를 추가하는 것처럼 말이죠.

자바스크립트에서 지원하는 노드 삽입 메서드는 다음과 같습니다. 적절한 메서드를 선택하면 직접 삽입 위치를 지정할 수 있습니다.

- `node.append(노드나 문자열)` – 노드나 문자열을 `node` *끝*에 삽입합니다.
- `node.prepend(노드나 문자열)` – 노드나 문자열을 `node` *맨 앞*에 삽입합니다.
- `node.before(노드나 문자열)` –- 노드나 문자열을 `node` *이전*에 삽입합니다.
- `node.after(노드나 문자열)` –- 노드나 문자열을 `node` *다음*에 삽입합니다.
- `node.replaceWith(노드나 문자열)` –- `node`를 새로운 노드나 문자열로 대체합니다.

삽입 메서드엔 임의의 노드 목록이나 문자열을 넘겨줄 수 있습니다. 문자열을 넘겨주면 자동으로 텍스트 노드가 만들어집니다.

예시를 살펴봅시다.

스크립트가 실행되면 0, 1, 2에 더하여 새로운 글자들이 화면에 추가됩니다.

```markup
<ol id="ol">
  <li>0</li>
  <li>1</li>
  <li>2</li>
</ol>

<script>
  ol.before('before'); // <ol> 앞에 문자열 'before'를 삽입함
  ol.after('after'); // <ol> 뒤에 문자열 'after를 삽입함

  let liFirst = document.createElement('li');
  liFirst.innerHTML = 'prepend';
  ol.prepend(liFirst); // <ol>의 첫 항목으로 liFirst를 삽입함

  let liLast = document.createElement('li');
  liLast.innerHTML = 'append';
  ol.append(liLast); // <ol>의 마지막 항목으로 liLast를 삽입함
</script>
```

<iframe class="code-result__iframe" name="test-2bao4uc53y" src="about:blank" style="display: block; border: 0px; width: 798px; height: 195px; background: var(--iframeBg);"></iframe>

위 예시에서 사용된 각 메서드들의 역할을 그림으로 표현하면 다음과 같습니다.

![image-20231231110022318](.\Images\image-20231231110022318.png)

최종 결과는 다음과 같아질 겁니다.

```markup
before
<ol id="ol">
  <li>prepend</li>
  <li>0</li>
  <li>1</li>
  <li>2</li>
  <li>append</li>
</ol>
after
```

앞서 언급하긴 했지만, 이 메서드들을 사용하면 '복수’의 노드와 문자열을 한 번에 넣을 수도 있습니다.

문자열과 요소를 한 번에 삽입하는 예시를 살펴봅시다.

```markup
<div id="div"></div>
<script>
  div.before('<p>안녕하세요</p>', document.createElement('hr'));
</script>
```

삽입 메서드에 문자열을 넘겨 텍스트 노드를 만들 땐 인수로 넘긴 문자열이 'HTML’이 아닌 ‘문자열’ 그 형태로 삽입된다는 점에 주의해야 합니다. `<`나 `>`같은 특수문자는 이스케이프 처리됩니다.

따라서 최종 HTML은 다음과 같습니다.

```markup
&lt;p&gt;안녕하세요&lt;/p&gt;
<hr>
<div id="div"></div>
```

삽입 메서드를 사용하면 문자열은 `elem.textContent`를 사용한 것처럼 안전한 방법으로 삽입되는 것이죠.

따라서 노드 삽입 메서드는 DOM 노드나 문자열을 삽입할 때만 사용할 수 있습니다.

그런데 만약 `elem.innerHTML`을 사용한 것처럼 태그가 정상적으로 동작할 수 있게 문자열 형태의 'HTML 그 자체’를 삽입하고 싶다면 어떻게 해야 할까요?

## [insertAdjacentHTML/Text/Element](https://ko.javascript.info/modifying-document#ref-828)

또 다른 다재다능한 메서드 `elem.insertAdjacentHTML(where, html)`를 사용하면 가능합니다.

`elem.insertAdjacentHTML(where, html)`에서 첫 번째 매개변수는 `elem`을 기준으로 하는 상대 위치로, 다음 값 중 하나여야 합니다.

- `'beforebegin'` – `elem` 바로 앞에 `html`을 삽입합니다.
- `'afterbegin'` – `elem`의 첫 번째 자식 요소 바로 앞에 `html`을 삽입합니다.
- `'beforeend'` – `elem`의 마지막 자식 요소 바로 다음에 `html`을 삽입합니다.
- `'afterend'` – `elem` 바로 다음에 `html`을 삽입합니다.

두 번째 매개변수는 HTML 문자열로, 이 HTML은 이스케이프 처리되지 않고 ‘그대로’ 삽입됩니다.

예시:

```markup
<div id="div"></div>
<script>
  div.insertAdjacentHTML('beforebegin', '<p>안녕하세요.</p>');
  div.insertAdjacentHTML('afterend', '<p>안녕히 가세요.</p>');
</script>
```

최종 HTML은 다음과 같습니다.

```markup
<p>안녕하세요.</p>
<div id="div"></div>
<p>안녕히 가세요.</p>
```

임의의 HTML을 페이지에 삽입할 땐 이 방법을 사용하면 됩니다.

아래는 매개변수에 따라 삽입 위치가 어떻게 변하는지를 표현한 그림입니다.

![image-20231231110120772](.\Images\image-20231231110120772.png)

이 그림과 위쪽 그림이 꽤 유사하게 생겼네요. HTML을 삽입한다는 점만 다르고, 삽입 지점은 정확히 같다는 것을 관찰할 수 있습니다.

`elem.insertAdjacentHTML(where, html)`은 두 가지 형제 메서드가 있습니다.

- `elem.insertAdjacentText(where, text)` – `insertAdjacentHTML`과 문법은 같은데, HTML 대신 `text`를 ‘문자 그대로’ 삽입한다는 점이 다릅니다.
- `elem.insertAdjacentElement(where, elem)` – 역시 같은 문법인데, 요소를 삽입한다는 점이 다릅니다.

`insertAdjacentText`과 `insertAdjacentElement`는 메서드 구색을 갖추려는 목적으로 만들어졌습니다. 요소나 문자 노드를 삽입할 땐 `append/prepend/before/after`메서드를 사용하면 되므로 실무에선 대부분 `insertAdjacentHTML`을 사용합니다. 요소 삽입 메서드를 사용하면 코드 길이가 줄어들고, 노드나 텍스트 조각을 쉽게 삽입할 수 있습니다.

새롭게 배운 메서드 `insertAdjacentHTML`를 사용해 메시지 창 예시를 다시 작성하면 다음과 같습니다.

```markup
<style>
.alert {
  padding: 15px;
  border: 1px solid #d6e9c6;
  border-radius: 4px;
  color: #3c763d;
  background-color: #dff0d8;
}
</style>

<script>
  document.body.insertAdjacentHTML("afterbegin", `<div class="alert">
    <strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.
  </div>`);
</script>
```

## [노드 삭제하기](https://ko.javascript.info/modifying-document#ref-829)

`node.remove()` 사용하면 노드를 삭제할 수 있습니다.

일 초 후 메시지가 사라지게 해봅시다.

```markup
<style>
.alert {
  padding: 15px;
  border: 1px solid #d6e9c6;
  border-radius: 4px;
  color: #3c763d;
  background-color: #dff0d8;
}
</style>

<script>
  let div = document.createElement('div');
  div.className = "alert";
  div.innerHTML = "<strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.";

  document.body.append(div);
  setTimeout(() => div.remove(), 1000);
</script>
```

참고로, 요소 노드를 다른 곳으로 *옮길 때* 기존에 있던 노드를 지울 필요가 없습니다.

**모든 노드 삽입 메서드는 자동으로 기존에 있던 노드를 삭제하고 새로운 곳으로 노드를 옮기기 때문입니다.**

요소 위치를 바꾸는 예시를 살펴봅시다.

```markup
<div id="first">First</div>
<div id="second">Second</div>
<script>
  // remove 메서드를 호출할 필요가 없습니다.
  second.after(first); // id가 second인 노드를 가져오고, 해당 노드의 뒤에 id가 first인 노드를 삽입
</script>
```

## [cloneNode로 노드 복제하기](https://ko.javascript.info/modifying-document#ref-830)

위 예시에서 기존 메시지 창과 유사한 메시지 창을 하나 더 띄워달라는 요구사항이 추가되었다고 가정해봅시다.

'메시지 창을 만들어주는 함수를 하나 만들면 되지 않을까?'라는 생각이 떠오를 겁니다. 그런데 이 방법 말고도 기존에 만들어 놨던 `div`를 *복제*하고 (필요하다면) 안에 있는 텍스트를 수정하는 방법도 가능합니다.

복제하려는 요소가 클 때는 함수를 만드는 대신 복제를 사용하는 방법이 빠르고 간단할 수 있습니다.

- `elem.cloneNode(true)`을 호출하면 `elem`의 ‘깊은’ 복제본이 만들어집니다. 속성 전부와 자손 요소 전부가 복사됩니다. `elem.cloneNode(false)`을 호출하면 후손 노드 복사 없이 `elem`만 복제됩니다.

`cloneNode`를 이용해 메시지 창을 하나 더 띄워봅시다.

```markup
<style>
.alert {
  padding: 15px;
  border: 1px solid #d6e9c6;
  border-radius: 4px;
  color: #3c763d;
  background-color: #dff0d8;
}
</style>

<div class="alert" id="div">
  <strong>안녕하세요!</strong> 중요 메시지를 확인하셨습니다.
</div>

<script>
  let div2 = div.cloneNode(true); // 메시지 창 복제
  div2.querySelector('strong').innerHTML = '안녕히 가세요!'; // 복제한 메시지 창 내용 수정

  div.after(div2); // 복제한 메시지 창을 기존 메시지 창 다음에 보여줌
</script>
```

## [DocumentFragment](https://ko.javascript.info/modifying-document#document-fragment)

`DocumentFragment`는 특별한 DOM 노드 타입으로, 여러 노드로 구성된 그룹을 감싸 다른 곳으로 전달하게 해주는 래퍼(wrapper)처럼 동작합니다.

문서에 있는 다른 노드를 `DocumentFragment`에 추가할 수 있는데, `DocumentFragment`를 문서 어딘가에 삽입하면 `DocumentFragment`는 사라지고 `DocumentFragment`에 추가한 노드만 남습니다.

예시를 살펴봅시다. 아래 함수 `getListContent`는 `<li>` 노드로 구성된 fragment를 만들어 줍니다. 이 fragment를 `<ul>`에 추가해 보겠습니다.

```markup
<ul id="ul"></ul>

<script>
function getListContent() {
  let fragment = new DocumentFragment();

  for(let i=1; i<=3; i++) {
    let li = document.createElement('li');
    li.append(i);
    fragment.append(li);
  }

  return fragment;
}

ul.append(getListContent()); // (*)
</script>
```

`(*)`로 표시한 줄에서 `<ul>`에 `DocumentFragment`를 추가했습니다. 추가한 fragment는 문서에 ‘녹아들었기’ 때문에 최종 결과물은 아래와 같아집니다.

```markup
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
</ul>
```

`DocumentFragment`를 직접 사용할 일은 흔지 않습니다. 아래 에시처럼 노드가 담긴 배열을 직접 만들어 반환할 수 있기 때문입니다.

```markup
<ul id="ul"></ul>

<script>
function getListContent() {
  let result = [];

  for(let i=1; i<=3; i++) {
    let li = document.createElement('li');
    li.append(i);
    result.push(li);
  }

  return result;
}

ul.append(...getListContent()); // append 메서드와 ...(전개 구문)은 궁합이 잘 맞습니다.
</script>
```

여기서 `DocumentFragment`를 언급한 이유는 [template](https://ko.javascript.info/template-element) 요소같이 `DocumentFragment`를 기반으로하는 문법이 있기 때문입니다. template 요소는 추후 다루도록 하겠습니다.

## [구식 삽입·삭제 메서드](https://ko.javascript.info/modifying-document#ref-831)

**오래된 문법**

이 정보는 개발된 지 얼마 안 된 스크립트가 아닌 오래된 스크립트를 이해하는 데 필요합니다.

하위 호환성을 유지하기 위해 남아있는 ‘구식(old school)’ DOM 조작 메서드도 있습니다.

아주 오래전에 만들어진 이 메서드들은 요즘에는 잘 사용하지 않습니다. 앞서 배운 모던한 메서드 `append`, `prepend`, `before`, `after`, `remove`, `replaceWith`를 사용하는 것이 좀 더 유연하기 때문입니다.

그럼에도 불구하고 구식 메서드를 소개해드리는 이유는 작성된 지 오래된 스크립트에서 이런 메서드들을 발견할 수 있기 때문입니다.

- `parentElem.appendChild(node)`

  `parentElem`의 마지막 자식으로 `node`를 추가함아래 예시를 실행하면 새로운 `<li>`가 `<ol>` 마지막에 추가됩니다.`<ol id="list">  <li>0</li>  <li>1</li>  <li>2</li> </ol> <script>  let newLi = document.createElement('li');  newLi.innerHTML = 'Hello, world!';   list.appendChild(newLi); </script>`

- `parentElem.insertBefore(node, nextSibling)`

  `node`를 `parentElem`안의 `nextSibling`앞에 추가함아래 예시를 실행하면 새로운 `<li>`가 두 번째 `<li>` 앞에 추가됩니다.`<ol id="list">  <li>0</li>  <li>1</li>  <li>2</li> </ol> <script>  let newLi = document.createElement('li');  newLi.innerHTML = 'Hello, world!';   *list`.``insertBefore``(`newLi`,` list`.`children`[``1``]``)``;`* </script>``newLi`를 첫 번째 항목으로 추가하고 싶다면 아래와 같이 `firstChild`를 사용하면 됩니다.`list.insertBefore(newLi, list.firstChild);`

- `parentElem.replaceChild(node, oldChild)`

  `parentElem`의 자식 노드 중 `oldChild`를 `node`로 교체함

- `parentElem.removeChild(node)`

  `node`가 `parentElem`의 자식 노드라는 가정하에 `parentElem`에서 `node`를 삭제함아래 예시를 실행하면 `<ol>`에서 첫 번째 `<li>`가 사라집니다.`<ol id="list">  <li>0</li>  <li>1</li>  <li>2</li> </ol> <script>  let li = list.firstElementChild;  list.removeChild(li); </script>`

이 메서드들은 모두 삽입하거나 삭제한 노드를 반환합니다. `parentElem.appendChild(node)`는 `node`를 반환하죠. 그런데 반환된 값을 사용할 일이 거의 없기 때문에 메서드를 그냥 실행만 하는 편입니다.

## ['document.write’에 대한 첨언](https://ko.javascript.info/modifying-document#ref-832)

`document.write`는 웹페이지에 뭔가를 더할 때 쓰는 아주 오래된 메서드입니다.

사용법은 다음과 같습니다.

```markup
<p>페이지 어딘가...</p>
<script>
  document.write('<b>자바스크립트를 사용해 Hello 입력</b>');
</script>
<p>끝</p>
```

`document.write(html)`를 호출하면 `html`이 페이지 ‘그 자리에 즉시’ 추가됩니다. `html` 형식의 문자열을 동적으로 만들어 사용할 수 있기 때문에 `document.write(html)`는 꽤나 유연합니다. 날개를 단 새처럼 동적인 웹페이지를 만드는데 이 메서드를 사용할 수 있죠.

`document.write`는 DOM도 없고 그 어떤 표준도 존재하지 않았던 과거에 만들어졌습니다. 표준에 정의된 메서드는 아니지만 아직 다양한 곳에서 쓰이고 있어서 살아남은 것이죠.

근래에 작성된 스크립트에선 이 메서드를 찾기 힘듭니다.

왜냐하면 **`document.write`는 페이지를 불러오는 도중에만 작동하기 때문입니다.**

페이지가 다 로드되고 나서 `document.write`를 호출하면 기존에 있던 문서 내용이 사라집니다.

예시를 살펴봅시다.

```markup
<p>일 초 후, 이 페이지의 내용은 전부 교체됩니다.</p>
<script>
  // 일초 후 document.write 호출
  // 페이지 로드가 끝난 후이므로 기존 내용이 사라집니다.
  setTimeout(() => document.write('<b>...사라졌습니다.</b>'), 1000);
</script>
```

이런 이유 때문에 `document.write`는 지금까지 배운 DOM 메서드와는 달리 '페이지 로드가 다 끝난 후’엔 사용할 수 없습니다.

아주 큰 단점이죠.

장점도 있습니다. 브라우저는 HTML을 ‘읽는(파싱하는)’ 도중에 `document.write(HTML)`를 만나면 텍스트 형식의 HTML을 마치 원래 페이지에 있었던 것 마냥 해석합니다.

중간에 *DOM 조작을 하지 않기 때문에* 속도가 아주 빨라지죠. DOM 구조가 완성되기 전에 페이지에 내용이 삽입되기 때문입니다.

엄청나게 많은 글자를 HTML에 동적으로 추가해야 하는데 아직 페이지를 불러오는 중이고, 속도가 중요한 상황이라면 `document.write`가 유용할 수 있습니다. 하지만 실제 이런 요구 사항들이 한 번에 충족되어야 하는 상황은 흔치 않습니다. `document.write`가 눈에 띈다면 그건 그냥 오래된 스크립트라서 그럴 확률이 높습니다.

## [요약](https://ko.javascript.info/modifying-document#ref-833)

- 노드 생성 메서드:

  - `document.createElement(tag)` – 태그 이름을 사용해 새로운 요소를 만듦
  - `document.createTextNode(value)` – 텍스트 노드를 만듦(잘 쓰이지 않음)
  - `elem.cloneNode(deep)` – 요소를 복제함. `deep==true`일 경우 모든 자손 요소도 복제됨

- 노드 삽입, 삭제 메서드:

  - `node.append(노드나 문자열)` – `node` 끝에 노드를 삽입
  - `node.prepend(노드나 문자열)` – `node` 맨 앞에 노드를 삽입
  - `node.before(노드나 문자열)` –- `node` 이전에 노드를 삽입
  - `node.after(노드나 문자열)` –- `node` 다음에 노드를 삽입
  - `node.replaceWith(노드나 문자열)` –- `node`를 대체
  - `node.remove()` –- `node`를 제거

  문자열을 삽입, 삭제할 땐 문자열을 ‘그대로’ 넣으면 됩니다.

- ‘구식’ 메서드:

  - `parent.appendChild(node)`
  - `parent.insertBefore(node, nextSibling)`
  - `parent.removeChild(node)`
  - `parent.replaceChild(newElem, node)`

  이 메서드들은 전부 `node`를 반환합니다.

- `html`에 HTML을 넣으면 메서드 `elem.insertAdjacentHTML(where, html)`은 `where` 값에 따라 특정 위치에 HTML을 삽입함

  - `"beforebegin"` – `elem` 바로 앞에 `html`을 삽입
  - `"afterbegin"` – `elem`의 첫 번째 자식 요소 바로 앞에 `html`을 삽입
  - `"beforeend"` – `elem`의 마지막 자식 요소 바로 다음에 `html`을 삽입
  - `"afterend"` – `elem` 바로 다음에 `html`을 삽입

  문자열이나 요소 삽입에 쓰이는 유사 메서드 `elem.insertAdjacentText`와 `elem.insertAdjacentElement`도 있는데, 잘 쓰이지는 않음

- 페이지 로딩이 끝나기 전에 HTML을 삽입해주는 메서드:

  - `document.write(html)`

  문서 로딩이 끝난 상태에서 이 메서드를 호출하면 문서 내용이 지워짐. 오래된 스크립트에서 볼 수 있음

## [과제](https://ko.javascript.info/modifying-document#tasks)

### [createTextNode vs innerHTML vs textContent](https://ko.javascript.info/modifying-document#ref-834)



중요도: 5

빈 DOM 요소 `elem`과 `text`라는 문자열이 있습니다.

셋 중에서 같은 동작을 수행하는 명령어는 무엇일까요?

1. `elem.append(document.createTextNode(text))`
2. `elem.innerHTML = text`
3. `elem.textContent = text`

해답

### [요소 삭제하기](https://ko.javascript.info/modifying-document#ref-835)



중요도: 5

요소의 모든 하위 요소들을 삭제하는 `clear(elem)` 이라는 함수를 만들어 보세요.

```markup
<ol id="elem">
  <li>Hello</li>
  <li>World</li>
</ol>

<script>
  function clear(elem) { /* 여기에 코드를 작성하세요. */ }

  clear(elem); // elem 내부 리스트를 삭제합니다.
</script>
```

해답

### [왜 'aaa' 가 남아 있을까요](https://ko.javascript.info/modifying-document#ref-836)



중요도: 1

아래 예시에서는 `table.remove()` 가 호출되었으니 문서에서 표를 삭제해야만 합니다.

그러나 이 코드를 실행해보면, 텍스트 `'aaa'` 가 여전히 나타나는 것을 확인할 수 있습니다.

왜 이런 일이 일어나는 걸까요?

```markup
<table id="table">
  aaa
  <tr>
    <td>Test</td>
  </tr>
</table>

<script>
  alert(table); // table 은 삭제할 표의 id 입니다.

  table.remove();
  // 왜 문서 안에 aaa가 남아 있을까요?
</script>
```

해답

### [리스트 생성하기](https://ko.javascript.info/modifying-document#ref-837)



중요도: 4

사용자로부터 값을 입력받아 리스트를 생성하는 인터페이스를 만들어 보세요.

리스트의 모든 요소는 아래 방법으로 생성합니다.

1. `prompt`를 사용해 사용자로부터 리스트의 내용을 입력받습니다.
2. 1번에서 입력받은 내용을 갖는 `<li>` 를 생성한 후 `<ul>` 에 추가합니다.
3. 사용자가 입력을 취소할 때까지 계속합니다 (`ESC` 키나 프롬프트 창의 취소 버튼을 누를 때까지).

모든 요소는 동적으로 생성되어야 합니다.

사용자가 HTML 태그를 입력하더라도 텍스트로 처리되어야 합니다.

[새 창에서 데모 보기](https://ko.js.cx/task/create-list/solution/)

해답

### [객체로부터 트리(tree) 생성하기](https://ko.javascript.info/modifying-document#ref-838)



중요도: 5

중첩된 객체의 데이터로 `ul·li` 리스트를 생성하는 `createTree` 함수를 만들어 보세요.

예시:

```javascript
let data = {
  "Fish": {
    "trout": {},
    "salmon": {}
  },

  "Tree": {
    "Huge": {
      "sequoia": {},
      "oak": {}
    },
    "Flowering": {
      "apple tree": {},
      "magnolia": {}
    }
  }
};
```

코드 형식:

```javascript
let container = document.getElementById('container');
createTree(container, data); // container 요소 내에 트리를 생성합니다.
```

결과물이 될 트리는 이런 모습이어야 합니다.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/create-object-tree/build-tree-dom/" style="display: block; border: 0px; width: 798px; height: 300px; background: var(--iframeBg);"></iframe>

두 방법 중 원하는 방법으로 과제를 해결해 보세요.

1. 전체 트리를 생성한 후 `container.innerHTML` 로 컨테이너에 추가합니다.
2. 노드를 각각 생성한 후 DOM 메서드를 사용해 컨테이너에 추가합니다.

두 방법을 모두 사용해보면 더 좋습니다.

내용이 없는 `<ul></ul>`처럼 ‘불필요한’ 요소가 트리에 존재해서는 안된다는 점을 참고하세요.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/wArJpjbSjaJWcIQE?p=preview)

해답

### [트리의 자손 수 나타내기](https://ko.javascript.info/modifying-document#ref-839)



중요도: 5

중첩된 `ul·li` 로 이루어진 트리가 있습니다.

각 `<li>` 가 가진 자손 요소들의 수를 표시하는 코드를 작성해 보세요. (자식이 없는 노드는 생략하세요.)

결과는 아래와 같습니다.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/tree-count/solution/" style="display: block; border: 0px; width: 798px; height: 300px; background: var(--iframeBg);"></iframe>

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/wRohWK9wTaZtpRFc?p=preview)

해답

### [달력 만들기](https://ko.javascript.info/modifying-document#ref-840)



중요도: 4

`createCalender(elem, year, month)` 라는 함수를 작성해 보세요.

이 함수는 주어진 연도/월의 달력을 생성하고 `elem` 안에 추가해야 합니다.

달력은 각 주를 `<tr>`, 날짜는 `<td>`로 나타내는 표여야 하며 표의 최상단은 월요일로 시작해서 일요일로 끝나는 요일이 적힌 `<th>` 여야 합니다.

예를 들면 `createCalender(cal, 2012, 9)` 는 `cal` 요소 안에 아래 달력을 생성합니다.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/calendar-table/solution/" style="display: block; border: 0px; width: 798px; height: 210px; background: var(--iframeBg);"></iframe>

참고로 이 과제는 달력을 생성하는 것만으로 충분하며 클릭을 비롯한 동작은 구현하지 않아도 좋습니다.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/Faat2xLewDFdOv8n?p=preview)

해답

### [setInterval을 사용한 알록달록한 시계](https://ko.javascript.info/modifying-document#ref-841)



중요도: 4

아래처럼 알록달록한 시계를 만들어 보세요.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/clock-setinterval/solution/" style="display: block; border: 0px; width: 798px; height: 60px; background: var(--iframeBg);"></iframe>

자바스크립트는 요소 내부 시간을 업데이트할 때만 사용됩니다. 스타일을 꾸미려면 HTML·CSS를 사용하세요.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/kmen9Puxh5SAEEk7?p=preview)

해답

### [리스트에 HTML 삽입하기](https://ko.javascript.info/modifying-document#ref-842)



중요도: 5

두 `<li>` 사이에 `<li>2</li><li>3</li>`을 추가하는 코드를 작성해 보세요.

```markup
<ul id="ul">
  <li id="one">1</li>
  <li id="two">4</li>
</ul>
```

해답

### [표 정렬하기](https://ko.javascript.info/modifying-document#ref-843)



중요도: 5

주어진 표가 하나 있습니다.

```markup
<table>
<thead>
  <tr>
    <th>Name</th><th>Surname</th><th>Age</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>John</td><td>Smith</td><td>10</td>
  </tr>
  <tr>
    <td>Pete</td><td>Brown</td><td>15</td>
  </tr>
  <tr>
    <td>Ann</td><td>Lee</td><td>5</td>
  </tr>
  <tr>
    <td>...</td><td>...</td><td>...</td>
  </tr>
</tbody>
</table>
```

표에는 이보다 더 많은 행들이 있을 수 있습니다.

`'name'` 열을 기준으로 표를 정렬하는 코드를 작성해 보세요.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/JUSOSm37yndU9aod?p=preview)

해답



# 1.8 스타일과 클래스

자바스크립트를 사용해 어떻게 스타일과 클래스를 다룰 수 있는지 알아보기 전에, 중요한 규칙을 하나 집고 넘어가야 할 것 같습니다. 핵심만 요약했기 때문에 충분할진 모르겠지만 꼭 언급하고 넘어가야 하기 때문입니다.

요소에 스타일을 적용할 수 있는 방법은 두 가지가 있습니다.

1. CSS에 클래스를 만들고, 요소에 `<div class="...">`처럼 클래스 추가하기
2. `<div style="...">`처럼 프로퍼티를 `style`에 바로 써주기

자바스크립트를 사용하면 클래스와 `style` 프로퍼티 둘 다를 수정할 수 있습니다.

두 방법 중 하나를 택하라면 `style` 보다 CSS 클래스를 수정하는 것을 더 우선시해야 합니다. `style`은 클래스를 '다룰 수 없을 때’만 사용해야 합니다.

`style`은 아래와 같이 자바스크립트를 사용해 요소의 좌표를 동적으로 계산하고, 계산한 좌표를 설정해주고자 할 때 사용하면 좋습니다.

```javascript
let top = /* 복잡한 계산식 */;
let left = /* 복잡한 계산식 */;

elem.style.left = left; // 예시: '123px', 런타임으로 좌표를 계산할 수 있습니다.
elem.style.top = top; // 예시: '456px'
```

텍스트를 빨간색으로 만든다거나 배경에 이미지를 추가하는 등의 작업은 CSS에 관련 스타일을 명시한 클래스를 만들고, 자바스크립트를 사용해 이 클래스를 요소에 추가해 주는 방식이 좋습니다. 이렇게 하면 유연성이 확보돼 유지보수가 쉬워집니다.

## [className과 classList](https://ko.javascript.info/styles-and-classes#ref-706)

클래스 변경은 스크립트를 통해 자주 하게 되는 동작 중 하나입니다.

아주 오래전 자바스크립트엔 `"class"`같은 예약어는 객체의 프로퍼티가 될 수 없다는 제약사항이 있었습니다. 지금은 이런 제약사항이 사라졌지만, 과거엔 `"class"` 프로퍼티를 사용할 수 없었기 때문에 `elem.class`를 사용하는 것 역시 불가능했습니다.

이런 배경 때문에 클래스를 위한 프로퍼티 `"className"`가 도입되게 되었습니다. `elem.className`는 `"class"` 속성에 대응합니다.

예시:

```markup
<body class="main page">
  <script>
    alert(document.body.className); // main page
  </script>
</body>
```

`elem.className`에 무언가를 대입하면 클래스 문자열 전체가 바뀝니다. 그런데 이렇게 속성값 전체를 바꾸는 게 아니고 클래스 하나만 추가하거나 제거하고 싶은 경우도 있기 마련입니다.

이럴 때 `elem.classList`라는 프로퍼티를 사용할 수 있습니다.

`elem.classList`엔 클래스 하나만 조작하게 해주는 메서드인 `add/remove/toggle`가 구현되어 있습니다.

예시:

```markup
<body class="main page">
  <script>
    // 클래스 추가
    document.body.classList.add('article');

    alert(document.body.className); // main page article
  </script>
</body>
```

이렇게 클래스 속성값 전체를 바꾸고 싶을 때는 `className`으로, 개별 클래스를 조작하고 싶을 때는 `classList`를 사용하면 됩니다. 필요에 따라 취사선택하면 되죠.

`classList`에 구현된 메서드는 다음과 같습니다.

- `elem.classList.add/remove("class")` – `class`를 추가하거나 제거
- `elem.classList.toggle("class")` – `class`가 존재할 경우 `class`를 제거하고, 그렇지 않은 경우엔 추가
- `elem.classList.contains("class")` – `class` 존재 여부에 따라 `true/false`를 반환

이 외에 `classList`는 이터러블 객체이기 때문에 아래 예시와 같이 `for..of`를 사용해 클래스를 나열할 수 있다는 특징이 있습니다.

```markup
<body class="main page">
  <script>
    for (let name of document.body.classList) {
      alert(name); // main과 page가 출력됨
    }
  </script>
</body>
```

## [요소의 스타일](https://ko.javascript.info/styles-and-classes#ref-707)

프로퍼티 `elem.style`은 속성 `"style"`에 쓰인 값에 대응되는 객체입니다. `elem.style.width="100px"`은 `style` 속성값을 문자열 `width:100px`로 설정한 것과 같죠.

여러 단어를 이어서 만든 프로퍼티는 다음와 같이 카멜 표기법을 사용해 이름 짓습니다.

```javascript
background-color  => elem.style.backgroundColor
z-index           => elem.style.zIndex
border-left-width => elem.style.borderLeftWidth
```

예시:

```javascript
document.body.style.backgroundColor = prompt('배경을 무슨 색으로 바꿀까요?', 'green');
```

**특정 브라우저 전용 프로퍼티**

`-moz-border-radius`, `-webkit-border-radius`같이 브라우저 관련 접두사가 붙은 프로퍼티(browser-prefixed property) 역시 카멜 표기법을 사용합니다. 대시(-)는 대문자를 의미합니다.

예시:

```javascript
button.style.MozBorderRadius = '5px';
button.style.WebkitBorderRadius = '5px';
```

## [style 프로퍼티 재지정하기](https://ko.javascript.info/styles-and-classes#ref-708)

`style` 프로퍼티에 값을 할당했다가 시간이 지나 이를 제거해야 할 때가 종종 있습니다.

요소를 숨기기 위해 `elem.style.display = "none"`를 적용했다고 가정해봅시다.

시간이 지나 처음부터 `style.display`를 설정하지 않았던 것처럼 되돌리고 싶어졌습니다. 이럴 땐 `delete elem.style.display`를 사용해 프로퍼티를 삭제하는 대신 `elem.style.display = ""`같이 빈 문자열을 할당해주어야 합니다.

```javascript
// 예시를 실행하면 페이지의 <body>가 깜빡입니다.
document.body.style.display = "none"; // hide

setTimeout(() => document.body.style.display = "", 1000); // 1초 후 다시 원래 상태로 돌아옵니다.
```

이렇게 `style.display`에 빈 문자열을 할당하면 브라우저는 마치 처음부터 `style.display` 프로퍼티가 없었던 것처럼 CSS 클래스와 브라우저 내장 스타일을 페이지에 적용합니다.

**`style.cssText`로 완전히 다시 쓰기**

개별 스타일 프로퍼티를 적용할 때는 보통 `style.*`를 사용합니다. 그런데 `div.style` 은 객체이고 읽기 전용이기 때문에 `div.style="color: red; width: 100px"`같은 방식으론 전체 스타일을 설정할 수 없습니다.

문자열을 사용해 전체 스타일을 설정하려면 프로퍼티 `style.cssText`를 사용해야 합니다.

```markup
<div id="div">버튼</div>

<script>
  // cssText를 사용하면 'important' 같은 규칙도 설정할 수 있습니다.
  div.style.cssText=`color: red !important;
    background-color: yellow;
    width: 100px;
    text-align: center;
  `;

  alert(div.style.cssText);
</script>
```

`style.cssText`를 사용하면 기존 스타일에 스타일을 추가하는 게 아니라 전체를 교체해버리기 때문에 잘 쓰이지 않습니다. 잘 사용하고 있는 스타일이 실수로 지워진다는 위험이 있죠. 그렇지만 요소를 새로 만들고, 여기에 스타일을 적용할 때는 기존 스타일이 없기 때문에 `style.cssText`를 사용할 수 있습니다.

`div.setAttribute('style', 'color: red...')`를 사용해 속성을 설정해도 `style.cssText`과 같은 효과를 볼 수 있습니다.

## [단위에 주의하기](https://ko.javascript.info/styles-and-classes#ref-709)

자바스크립트를 사용해 스타일 값을 설정할 때는 단위를 붙여주는 걸 잊지 말아야 합니다.

`elem.style.top`에 값을 설정할 때 `10px`이 아닌 `10`을 설정하면 제대로 동작하지 않습니다.

```markup
<body>
  <script>
    // 동작하지 않습니다!
    document.body.style.margin = 20;
    alert(document.body.style.margin); // '' (값이 제대로 할당되지 않았기 때문에 빈 문자열이 출력됩니다.)

    // CSS 단위(px)를 추가해봅시다. 제대로 동작하네요!
    document.body.style.margin = '20px';
    alert(document.body.style.margin); // 20px

    alert(document.body.style.marginTop); // 20px
    alert(document.body.style.marginLeft); // 20px
  </script>
</body>
```

참고로 브라우저는 위 예시와 같이 `style.margin`를 ‘분석’ 및 추론한 결과를 `style.marginLeft`와 `style.marginTop`에 할당해줍니다.

## [getComputedStyle로 계산된 스타일 얻기](https://ko.javascript.info/styles-and-classes#ref-710)

지금까지 배운 내용을 응용하면 스타일을 쉽게 수정할 수 있습니다. 그럼 어떻게 하면 스타일을 *읽을 수* 있을까요?

요소의 크기와 마진, 색을 알고 싶다면 어떻게 해야 할까요?

`style` 프로퍼티 값을 읽으면 되지 않을까 생각할 수 있겠지만, **`style` 프로퍼티는 `"style"` 속성의 값을 읽을 때만 사용할 수 있습니다. `style` 프로퍼티만으론 CSS 종속(CSS cascade)값을 다루지 못합니다.**

이런 특징 때문에 `elem.style`만으로는 CSS 클래스를 사용해 적용한 스타일을 읽을 수 없습니다.

`style` 프로퍼티를 사용해도 마진값을 읽을 수 없다는 것을 직접 확인해봅시다.

```markup
<head>
  <style> body { color: red; margin: 5px } </style>
</head>
<body>

  붉은 글씨
  <script>
    alert(document.body.style.color); // 빈 문자열
    alert(document.body.style.marginTop); // 빈 문자열
  </script>
</body>
```

그렇다면 마진을 현재 크기보다 `20px` 더 크게 하려면 어떻게 해야 할까요? 원하는 작업을 하려먼 먼저 현재 크기를 알 수 있어야 합니다.

이럴 때 `getComputedStyle` 메서드를 사용할 수 있습니다.

`getComputedStyle` 메서드의 문법은 다음과 같습니다.

```javascript
getComputedStyle(element, [pseudo])
```

- element

  값을 읽을 요소

- pseudo

  `::before`같이 의사 요소(pseudo-element)가 필요한 경우 명시해줌. 빈 문자열을 넘겨주거나 아무런 값을 입력하지 않은 경우 요소 자체를 의미함

`getComputedStyle`을 호출하면 `elem.style`같이 스타일 정보가 들어 있는 객체가 반환되는데, 여기엔 `elem.style`과는 달리 전체 CSS 클래스 정보도 함께 담기게 됩니다.

예시:

```markup
<head>
  <style> body { color: red; margin: 5px } </style>
</head>
<body>

  <script>
    let computedStyle = getComputedStyle(document.body);

    // 이제 마진과 색 정보를 얻을 수 있습니다.

    alert( computedStyle.marginTop ); // 5px
    alert( computedStyle.color ); // rgb(255, 0, 0)
  </script>

</body>
```

**계산 값과 결정 값**

[CSS](https://drafts.csswg.org/cssom/#resolved-values)에는 속성과 관련된 두 가지 개념이 있습니다.

1. *계산 값(computed style value)* – CSS 규칙과 CSS 상속이 모두 적용된 후의 값을 의미합니다. 값의 형태는 `height:1em`나 `font-size:125%` 같이 생겼습니다.
2. *결정 값(resolved style value)* – 요소에 최종적으로 적용되는 값을 의미합니다. 계산 값에서 사용한 `1em`나 `125%`은 상대 단위를 사용하는 상댓값인데, 브라우저는 계산 값을 받아 단위를 전환해 `height:20px`나 `font-size:16px`같이 고정 단위를 사용하는 값(절댓값)으로 값을 변환합니다. 기하 관련 프로퍼티의 결정 값에는 `width:50.5px`같이 소수점 단위가 있을 수 있습니다.

`getComputedStyle`은 계산 값을 얻기 위해서 만들어진 아주 오래된 메서드입니다. 그런데 계산 값보다는 결정 값을 사용하는 게 훨씬 편리하기 때문에 표준이 개정되었습니다.

따라서 지금은 `getComputedStyle`을 호출하면 프로퍼티의 결정 값이 반환됩니다. 기하 프로퍼티의 경우 주로 `px`가 단위로 사용됩니다.

**`getComputedStyle`엔 프로퍼티 전체 이름이 필요합니다.**

`getComputedStyle`을 사용할 때는 `paddingLeft`, `marginTop`, `borderTopWidth`같이 프로퍼티 이름 전체를 정확히 알고 있어야 합니다. 그렇지 않으면 원하는 값을 얻을 수 없는 경우가 생깁니다.

`paddingLeft`나 `paddingTop`엔 값이 지정되어있는데 `getComputedStyle(elem).padding`을 사용해 값을 얻으려 하는 경우를 생각해 봅시다. 어떤 값을 얻을 수 있을까요? 아무것도 얻지 못할까요 아니면 값이 설정되어 있는 `paddingLeft`나 `paddingTop`에서 값을 가져올까요? 이런 상황에 적용할만한 표준은 아직 제정되어있지 않습니다.

따라서 브라우저마다 동작 방식이 다릅니다. Chrome 같은 몇몇 브라우저는 아래 예시와 같이 `10px`을 출력해주는데 Firefox에서 빈 문자열이 출력됩니다.

```markup
<style>
  body {
    margin: 10px;
  }
</style>
<script>
  let style = getComputedStyle(document.body);
  alert(style.margin); // Firefox에선 빈 문자열이 출력됩니다.
</script>
```

**`:visited` 링크 관련 스타일은 숨겨져 있습니다.**

방문한 적이 있는 링크엔 `:visited`라는 CSS 의사 클래스를 사용해 색을 입힐 수 있습니다.

그런데 `getComputedStyle`을 사용해서 이 색을 얻을 수 없습니다. 색을 얻을 수 있도록 하면 임의의 페이지에서 사용자가 해당 링크를 방문했는지 아닌지를 `getComputedStyle`을 사용해 알아낼 수 있기 때문입니다.

자바스크립트로는 `:visited`에 적용된 스타일을 얻지 못합니다. 여기에 더하여 CSS에는 `:visited`에 기하학적 변화를 가져오는 스타일을 적용하는 것을 금지하는 제약도 있습니다. 이런 제약들은 악의를 가진 페이지가 사용자가 링크를 방문했는지 여부를 테스트 하고, 방문 여부에 따라 사생활을 침해할 만한 어떠한 것도 하지 못하도록 하려고 만들어졌습니다.

## [요약](https://ko.javascript.info/styles-and-classes#ref-711)

클래스를 관리할 수 있게 해주는 DOM 프로퍼티:

- `className` – 클래스 전체를 문자열 형태로 반환해주는 프로퍼티로 클래스 전체를 관리할 때 유용합니다.
- `classList` – 클래스 하나를 관리할 수 있게 해주는 메서드입니다. `add/remove/toggle/contains`가 구현된 객체를 반환합니다. 개별 클래스를 조작할 때 유용합니다.

스타일 변경 방법:

- `style` 프로퍼티 – 카멜 표기법을 이용해 변경한 스타일이 있는 객체로, 이 객체를 조작하는 것은 `"style"` 속성과 대응하는 개별 프로퍼티를 조작하는 것과 같습니다. `important` 등의 규칙을 어떻게 적용할 수 있는지 알아보려면 [MDN](https://developer.mozilla.org/ko/docs/Web/API/CSSStyleDeclaration)에서 관련 메서드를 살펴보시기 바랍니다.
- `style.cssText` 프로퍼티는 `"style"` 속성 전체에 대응하므로 스타일 전체에 대한 문자열이 저장됩니다.

요소의 스타일 결정 값을 읽는 방법:

- 스타일 정보가 들어 있는 객체를 반환해주는 메서드 `getComputedStyle(elem, [pseudo])`를 사용합니다. 이 메서드는 읽기 전용입니다.

## [과제](https://ko.javascript.info/styles-and-classes#tasks)

### [알림 만들기](https://ko.javascript.info/styles-and-classes#ref-712)



중요도: 5

샌드박스를 열었을 때 보이는 콘텐츠를 기반으로 `<div class="notification">` 알림을 만들어주는 함수 `showNotification(options)`를 작성해봅시다. 이 알림은 1.5초 후에 자동으로 사라집니다.

옵션은 다음과 같습니다.

```javascript
// 창의 오른쪽 상단에 "Hello"라는 텍스트가 담긴 요소를 보여줍니다.
showNotification({
  top: 10, // 창의 상단에서 10px 떨어진 위치에 표시합니다(기본값은 0px).
  right: 10, // 창의 오른쪽 가장자리에서 10px 떨어진 위치에 표시합니다(기본값은 0px).
  html: "Hello!", // 알림의 HTML입니다.
  className: "welcome" // div에 대한 추가 클래스입니다(선택 사항).
});
```

[새 창에서 데모 보기](https://ko.js.cx/task/create-notification/solution/)

CSS 포지셔닝을 사용하여 주어진 좌표(오른쪽 상단)에 알림을 표시하세요. 필요한 스타일들은 링크를 열면 찾아볼 수 있습니다.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/abhVpO4tcriWiHPj?p=preview)

해답



# 1.9 요소 사이즈와 스크롤

자바스크립트는 요소의 너비나 높이 같은 기하 정보 관련 프로퍼티를 지원합니다.

이런 프로퍼티는 요소를 움직이거나 특정 좌표에 위치시킬 때 사용할 수 있습니다.

## [샘플 요소](https://ko.javascript.info/size-and-scroll#ref-909)

프로퍼티 사용법을 알아보기 위해 아래와 같은 샘플 요소를 사용할 예정입니다.

```markup
<div id="example">
  ...텍스트...
</div>
<style>
  #example {
    width: 300px;
    height: 200px;
    border: 25px solid #E8C48F;
    padding: 20px;
    overflow: auto;
  }
</style>
```

`example`이라는 id가 붙은 이 요소에 border와 padding 프로퍼티 값을 주고, 스크롤바도 생기게 만들어두었습니다. 기하 프로퍼티를 실습해 보기 적절한 조합이죠. 참고로 margin은 요소 자체에 포함되지 않고, 관련한 특수 자바스크립트 프로퍼티도 없어서 CSS 프로퍼티에 추가하지 않았습니다.

요소의 생김새는 다음과 같습니다.

[샌드박스](https://plnkr.co/edit/sSVXtcaSEX2qGs6h?p=preview)를 열어 요소를 직접 확인해 보세요.

**스크롤바를 잊지 마세요.**

요소에 스크롤바가 생기면 복잡한 상황이 생깁니다. 모든 브라우저가 그런 건 아니지만 몇몇 브라우저는 콘텐츠 영역 너비('content width’로 표시한 영역) 일부를 빌려 스크롤바를 위치시키기 때문입니다.

샘플 예시에서 스크롤바가 없었다면 콘텐츠 영역 너비는 `300px`이었을 겁니다. 그런데 스크롤바가 `16px`을 차지하기 때문에 콘텐츠 영역 너비가 `284px`(300 – 16)이 되었습니다(스크롤바 너비는 브라우저나 디바이스마다 다릅니다). 스크롤바가 없었다면 계산이 매우 쉬웠겠지만, 연습을 위해 샘플 요소에 스크롤바를 일부러 포함했습니다. 요소를 다룰 때는 스크롤바가 차지하는 공간을 항상 염두에 두세요.

**`padding-bottom` 영역으로 텍스트가 넘칠 수 있습니다.**

그림에선 패딩에 아무것도 보이지 않게 해두었긴 하지만 요소 내 텍스트가 길어 넘치게 될 경우엔 브라우저가 이 텍스트들을 `padding-bottom`에 표시합니다. 이는 정상적인 동작입니다.

## [기하 프로퍼티](https://ko.javascript.info/size-and-scroll#ref-910)

기하 프로퍼티(geometry property)를 그림으로 나타내면 다음과 같습니다.

기하 프로퍼티의 값은 숫자인데 그 단위는 '픽셀’입니다. 기하 프로퍼티는 픽셀 단위로 측정된다고 보시면 됩니다.

이제 요소 제일 밖부터 시작해 차근차근 기하 프로퍼티를 살펴보도록 합시다.

## [offsetParent와 offsetLeft, offsetTop](https://ko.javascript.info/size-and-scroll#ref-911)

잘 쓰이는 프로퍼티는 아니지만 가장 바깥에 있는 기하 프로퍼티이므로 offsetParent와 offsetLeft, offsetTop부터 알아보겠습니다(offset은 요소가 화면에서 차지하는 영역 전체 크기를 나타내는데, 요소의 너비와 높이에 패딩, 스크롤바, 테두리를 합친 크기이며 마진은 포함되지 않습니다 – 옮긴이).

`offsetParent` 프로퍼티는 해당 요소를 렌더링할 때, 좌표 계산에 사용되는 가장 가까운 조상 요소(the closest positioned ancestor element)의 참조를 반환합니다.

CSS `position` 프로퍼티가 설정되어있는 조상 요소가 없는 경우엔 가장 가까운 조상 `<td>`나 `<th>`, 혹은 `<table>`, 아니면 `<body>`가 반환되기 때문에 `offsetParent`에 의해 반환되는 가장 가까운 조상 요소는 아래 셋 중 하나에 속하게 됩니다.

1. CSS `position` 프로퍼티가 `absolute`나 `relative`, `fixed`, `sticky`인 가장 가까운 조상 요소
2. `<td>`나 `<th>`, 혹은 `<table>`
3. `<body>`

`offsetLeft`와 `offsetTop` 프로퍼티는 `offsetParent`를 기준으로 각각 요소가 오른쪽으로, 아래쪽으로 얼마나 떨어져 있는지를 나타냅니다.

예시를 살펴봅시다. 안쪽에 있는 `<div>`의 `offsetParent`는 `<main>`이고 `offsetLeft`와 `offsetTop`은 각각 `180`입니다.

```markup
<main style="position: relative" id="main">
  <article>
    <div id="example" style="position: absolute; left: 180px; top: 180px">...</div>
  </article>
</main>
<script>
  alert(example.offsetParent.id); // main
  alert(example.offsetLeft); // 180 (주의: 문자열 '180px'이 아닌 숫자 180이 반환됩니다.)
  alert(example.offsetTop); // 180
</script>
```

다음 같은 경우엔 `offsetParent`가 `null`이 될 수 있습니다.

1. 화면에 보이지 않는 요소(CSS `display` 프로퍼티가 `none`이거나 문서 내에 있지 않은 요소)
2. `<body>`와 `<html>`
3. `position` 프로퍼티가 `fixed`인 요소

## [offsetWidth와 offsetHeight](https://ko.javascript.info/size-and-scroll#ref-912)

이제 본격적으로 요소 자체에 집중해 봅시다.

`offsetWidth`와 `offsetHeight`는 가장 간단한 프로퍼티입니다. 두 프로퍼티는 각각 요소 '가장 바깥 부분(outer)'이 차지하는 너비와 높이 정보를 제공합니다. 테두리를 포함한 요소 '전체’의 사이즈 정보를 제공한다고 보시면 됩니다.

샘플 요소를 대상으로 `offsetWidth`와 `offsetHeight`를 계산하면 다음과 같습니다.

- `offsetWidth = 390` – CSS width 프로퍼티(`300px`)를 사용해 얻을 수 있는 바깥 너비에 패딩(`2 * 20px`)과 테두리(`2 * 25px`) 너비를 더한 값
- `offsetHeight = 290` – 바깥 높이

**화면에 표시되지 않는 요소의 기하 프로퍼티 값은 0 또는 null입니다.**

기하 프로퍼티는 보이는 요소(displayed element)를 대상으로만 계산됩니다.

따라서 요소(혹은 이 요소의 조상 요소 중 어떤 것이든)의 CSS `display` 프로퍼티가 `none`이거나 문서 내에 해당 요소가 없으면 모든 기하 프로퍼티 값이 0이 됩니다(`offsetParent` 프로퍼티의 값은 `null`).

요소를 만들긴 했지만 아직 문서에 삽입하기 전이라던가, 새롭게 만든 요소의 `display` 프로퍼티가 `none`이면 기하 프로퍼티 값은 0, `offsetParent` 프로퍼티의 값은 `null`이 되는 것이죠.

이런 특징을 이용하면 요소의 숨김 상태 여부를 아래 같은 방법으로 확인할 수 있습니다.

```javascript
function isHidden(elem) {
  return !elem.offsetWidth && !elem.offsetHeight;
}
```

참고로 `isHidden`은 요소가 화면에 있긴 하지만 사이즈가 0일 때(비어있는 `<div>` 등)도 `true`를 반환하기 때문에 주의해서 사용해야 합니다.

## [clientTop과 clientLeft](https://ko.javascript.info/size-and-scroll#ref-913)

테두리(border)는 요소 내에 있습니다.

`clientTop`과 `clientLeft`를 사용하면 테두리 두께를 측정할 수 있습니다.

샘플 예시에서 테두리 두께를 계산하면 다음과 같습니다.

- `clientLeft = 25` – 왼쪽 테두리 너비
- `clientTop = 25` – 위쪽 테두리 높이

그런데 사실 `clientTop`과 `clientLeft` 프로퍼티는 테두리 높이, 너비와 정확히 일치하지 않습니다. 정확히는 테두리 바깥을 기준으로 한 테두리 안 상대 좌표를 나타냅니다.

조금 헷갈릴 수 있으니 자세히 설명해보겠습니다.

`clientTop`과 `clientLeft`의 차이는 아랍어나 히브리어처럼 오른쪽에서 왼쪽으로 글이 전개되는 언어일 때 드러납니다. 아랍어가 세팅된 브라우저에선 스크롤바가 오른쪽이 아닌 왼쪽에 나타나게 되는데, 그럼 `clientLeft`에 스크롤바의 너비가 포함됩니다.

`clientLeft`가 `25`가 아닌 스크롤바 너비를 포함한 `41(25 + 16)`이 되는 거죠.

히브리어를 예시로 직접 살펴봅시다.

## [clientWidth와 clientHeight](https://ko.javascript.info/size-and-scroll#ref-914)

clientWidth와 clientHeight 프로퍼티는 테두리 안 영역의 사이즈 정보를 제공합니다.

테두리 안에는 콘텐츠 너비와 패딩이 포함되는데, 스크롤바 너비는 포함되지 않습니다.

그림에서 `clientHeight`로 표시된 부분을 먼저 살펴봅시다.

가로 스크롤바가 없기 때문에 `clientHeight`는 테두리 안 영역 전체를 더한 값이 됩니다. 높이 `200px`에 위, 아래 패딩(`2 * 20px`)을 더한 값인 `240px`이 되죠.

이제 `clientWidth`를 계산해 봅시다. `clientWidth`를 계산할 때 주의할 점은 세로 스크롤바가 차지하는 너비 `16px` 때문에 콘텐츠 너비는 `300px`이 아닌 `284px`이 된다는 점입니다. 따라서 `clientWidth`는 콘텐츠 너비 `284px`에 왼쪽, 오른쪽 패딩(`2 * 20px`)을 더한 값인 `324px`가 됩니다.

**패딩이 없었다면 `clientWidth`와 `clientHeight`는 테두리와 스크롤바 안쪽에 있는 콘텐츠 영역의 너비, 높이와 정확히 일치했을 겁니다.**

따라서 패딩이 없는 경우엔 `clientWidth`와 `clientHeight`를 사용해 콘텐츠 영역 크기를 구할 수 있습니다.

## [scrollWidth와 scrollHeight](https://ko.javascript.info/size-and-scroll#ref-915)

`scrollWidth`와 `scrollHeight` 프로퍼티는 `clientWidth`와 `clientHeight` 유사한데, 스크롤바에 의해 감춰진 영역도 포함한다는 점에서 차이가 있습니다.

그림을 살펴봅시다.

- `scrollWidth = 324` – 수평 스크롤바가 없기 때문에 안쪽 영역 전체를 나타내는 `clientWidth`와 동일합니다.
- `scrollWidth = 324` – 스크롤 때문에 가려진 영역을 포함한 콘텐츠 영역 높이 전체
- `scrollHeight = 723` – 세로 스크롤바에 가려진 부분을 포함하는 콘텐츠 영역 안쪽 전체의 높이
- `scrollWidth = 324` – 콘텐츠 영역 안쪽 전체의 너비. 그림은 가로 스크롤바가 없기 때문에 `clientWidth`와 동일합니다.

`scrollWidth`와 `scrollHeight`는 요소 크기를 콘텐츠가 차지하는 만큼 늘리고자 할 때 사용할 수 있습니다.

예시:

```javascript
// 콘텐츠가 차지하는 높이만큼 요소 높이를 늘림
element.style.height = `${element.scrollHeight}px`;
```

버튼을 눌러 직접 요소 크기를 확장해 봅시다.

text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text

element.style.height = `${element.scrollHeight}px`

## [scrollLeft와 scrollTop](https://ko.javascript.info/size-and-scroll#ref-916)

`scrollLeft`와 `scrollTop`은 가로 스크롤이 오른쪽, 세로 스크롤이 아래로 움직임에 따라 가려진 영역의 너비와 높이를 나타냅니다.

세로 스크롤바를 아래로 조금 내린 경우를 가정한 그림을 예시로 들어봅시다. `scrollHeight`에서 `scrollTop`이 얼마만큼의 영역을 차지하는지를 살펴볼 수 있습니다.

이렇게 `scrollTop`은 ‘세로 스크롤바에 의해 가려져 보이지 않는’ 위쪽 콘텐츠의 높이가 됩니다.

**`scrollLeft`와 `scrollTop`은 수정 가능합니다.**

기하 프로퍼티 대부분은 읽기전용이지만 `scrollLeft`와 `scrollTop`은 변경이 가능합니다. 스크립트로 프로퍼티를 수정하면 자동으로 요소 내 스크롤이 움직입니다.

요소를 클릭하면 스크롤바가 `10px` 아래로 내려가도록 `elem.scrollTop += 10`을 스크립트에 추가해 놓았습니다.

여기를
클릭
1
2
3
4
5
6
7
8
9

이런 특징을 이용하면 `scrollTop`을 `0`이나 `1e9`같은 아주 큰 숫자로 설정해 스크롤바를 최상단이나 최하단으로 옮길 수 있습니다.

## [CSS를 사용해 너비와 높이를 얻지 마세요](https://ko.javascript.info/size-and-scroll#ref-917)

지금까지 요소 너비와 높이, 요소 포지셔닝 관련 거리를 구하는 데 사용되는 기하 프로퍼티를 살펴보았습니다.

그런데 우리는 [스타일과 클래스](https://ko.javascript.info/styles-and-classes) 챕터에서 `getComputedStyle`를 사용해 CSS가 적용된 요소의 높이와 너비를 구할 수 있다는 것을 알아본 바 있습니다.

그렇다면 왜 `getComputedStyle`를 사용해 요소 너비와 높이를 얻지 말라고 하는 걸까요?

```javascript
let elem = document.body;

alert( getComputedStyle(elem).width ); // CSS가 적용된 elem의 너비
```

`getComputedStyle`가 아닌 기하 프로퍼티를 사용해 너비와 높이 정보를 얻어야 하는 데는 다음과 같은 이유가 있습니다.

1. CSS `width`와 `height`는 다른 CSS 프로퍼티의 영향을 받습니다. 요소의 너비와 높이 계산 방법을 '지정’하는 `box-sizing`이 이런 프로퍼티의 대표적인 예이죠. `box-sizing`을 변경하면 `getComputedStyle`로 구한 값이 부정확 할 수 있습니다.

2. CSS `width`와 `height`는 `auto`일 수 있습니다. 인라인 요소(inline element)가 이런 경우에 속합니다.

   ```markup
   <span id="elem">안녕하세요!</span>
   
   <script>
     alert( getComputedStyle(elem).width ); // auto
   </script>
   ```

   CSS 관점에서 보면 `width:auto`는 전혀 이상할 게 없어 보입니다. 그런데 자바스크립트 입장에선 정확한 `px`값이 있어야 계산을 할 수 있기 때문에 auto라는 값은 쓸모가 없습니다.

다른 한 가지 이유는 스크롤바때문입니다. 스크롤바가 없으면 정상 동작하는데, 스크롤바가 생기면 의도한 대로 동작하지 않는 코드가 있습니다. 이런 일은 스크롤바가 콘텐츠 영역을 차지하는 몇몇 브라우저에서 발생합니다. 이들 브라우저에선 콘텐츠가 실제 차지하는 영역이 CSS로 설정한 너비보다 *좁은데*, `clientWidth`와 `clientHeight`는 이를 고려해 클라이언트 요소가 차지하는 공간을 계산합니다.

그런데 `getComputedStyle(elem).width`를 사용하면 상황이 달라집니다. Chrome 같은 브라우저는 스크롤바 너비를 제외한 진짜 내부 너비를 반환하는데 Firefox 같은 브라우저는 스크롤바를 무시하고 CSS로 설정한 너비를 반환합니다. 이런 브라우저 간 차이 때문에 `getComputedStyle`이 아닌 기하 프로퍼티를 사용해야 합니다.

스크롤바가 차지하는 영역을 따로 두는 브라우저(Windows에서 돌아가는 대부분의 브라우저)에서 아래 예시를 실행하면 300px이 아닌 다른 값이 출력되는 것을 확인할 수 있습니다.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/article/size-and-scroll/cssWidthScroll/" style="display: block; border: 0px; width: 798px; height: 300px; background: var(--iframeBg);"></iframe>

텍스트가 들어있는 위 요소의 CSS엔 `width:300px`가 설정되어 있는 상태입니다.

Windows가 설치된 데스크톱 PC의 Firefox, Chrome, Edge 브라우저는 모두 스크롤바 영역을 따로 떼어놓습니다. 그런데 Windows 이외의 OS가 설치된 PC의 Firefox에서 예시를 실행하면 `300px`이 출력되는 것을 확인할 수 있습니다. 다른 브라우저들은 ‘진짜’ 너비를 출력하는 반면 Firefox는 CSS로 설정한 너비를 출력하기 때문입니다.

`getComputedStyle`과 기하 프로퍼티의 차이는 자바스크립트를 사용해 `getComputedStyle(...).width`로 값을 얻고자 할 때만 발생합니다. 눈으로 봤을 땐 전혀 문제가 없으니 이 점에 유의하시기 바랍니다.

## [요약](https://ko.javascript.info/size-and-scroll#ref-918)

요소는 다음과 같은 기하 프로퍼티를 지원합니다.

- `offsetParent` – 위치 계산에 사용되는 가장 가까운 조상 요소나 `td`, `th`, `table`, `body`
- `offsetLeft`와 `offsetTop` – `offsetParent` 기준으로 요소가 각각 오른쪽, 아래쪽으로 얼마나 떨어져 있는지를 나타내는 값
- `offsetWidth`와 `offsetHeight` – 테두리를 포함 요소 '전체’가 차지하는 너비와 높이
- `clientLeft`와 `clientTop` – 요소 제일 밖을 감싸는 영역과 요소 안(콘텐츠 + 패딩)을 감싸는 영역 사이의 거리를 나타냄. 대부분의 경우 왼쪽, 위쪽 테두리 두께와 일치하지만, 오른쪽에서 왼쪽으로 글을 쓰는 언어가 세팅된 OS에선 `clientLeft`에 스크롤바 두께가 포함됨
- `clientWidth`와 `clientHeight` – 콘텐츠와 패딩을 포함한 영역의 너비와 높이로, 스크롤바는 포함되지 않음
- `scrollWidth`와 `scrollHeight` – `clientWidth`, `clientHeight` 같이 콘텐츠와 패딩을 포함한 영역의 너비와 높이를 나타내는데, 스크롤바에 의해 숨겨진 콘텐츠 영역까지 포함됨
- `scrollLeft`와 `scrollTop` – 스크롤바가 오른쪽, 아래로 움직임에 따라 가려지게 되는 요소 콘텐츠의 너비와 높이

스크롤바를 움직일 수 있게 해주는 `scrollLeft`와 `scrollTop`을 제외한 모든 프로퍼티는 읽기 전용입니다.

## [과제](https://ko.javascript.info/size-and-scroll#tasks)

### [What's the scroll from the bottom?](https://ko.javascript.info/size-and-scroll#ref-919)



중요도: 5

The `elem.scrollTop` property is the size of the scrolled out part from the top. How to get the size of the bottom scroll (let’s call it `scrollBottom`)?

Write the code that works for an arbitrary `elem`.

P.S. Please check your code: if there’s no scroll or the element is fully scrolled down, then it should return `0`.

해답

### [What is the scrollbar width?](https://ko.javascript.info/size-and-scroll#ref-920)



중요도: 3

Write the code that returns the width of a standard scrollbar.

For Windows it usually varies between `12px` and `20px`. If the browser doesn’t reserve any space for it (the scrollbar is half-translucent over the text, also happens), then it may be `0px`.

P.S. The code should work for any HTML document, do not depend on its content.

해답

### [Place the ball in the field center](https://ko.javascript.info/size-and-scroll#ref-921)



중요도: 5

Here’s how the source document looks:

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/put-ball-in-center/source/" style="display: block; border: 0px; width: 798px; height: 180px; background: var(--iframeBg);"></iframe>

What are coordinates of the field center?

Calculate them and use to place the ball into the center of the green field:

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/put-ball-in-center/solution/" style="display: block; border: 0px; width: 798px; height: 180px; background: var(--iframeBg);"></iframe>

- The element should be moved by JavaScript, not CSS.
- The code should work with any ball size (`10`, `20`, `30` pixels) and any field size, not be bound to the given values.

P.S. Sure, centering could be done with CSS, but here we want exactly JavaScript. Further we’ll meet other topics and more complex situations when JavaScript must be used. Here we do a “warm-up”.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/tR1ZPKlEvu2CteSj?p=preview)

해답

### [CSS width와 clientWidth의 차이](https://ko.javascript.info/size-and-scroll#ref-922)



중요도: 5

`getComputedStyle(elem).width`와 `elem.clientWidth`의 차이점은 무엇일까요?

최소 세 가지 차이점을 설명해주세요. 더 많을수록 좋습니다.

해답



# 1.10 브라우저 창 사이즈와 스크롤

브라우저 창이 차지하는 너비와 높이를 어떻게 구할 수 있을까요? 스크롤 때문에 보이지 않는 영역을 포함하여 문서 전체가 차지하는 너비와 높이는 어떻게 구할 수 있을까요? 자바스크립트를 사용해서 페이지를 스크롤 할 수 있을까요?

이번 챕터에선 위와 같은 물음에 답을 주는 루트 문서 요소인 `document.documentElement`를 살펴볼 예정입니다. `document.documentElement`는 `<html>` 태그와 상응하는 요소로 다양한 메서드를 지원합니다. 유용한 메서드이긴 하지만 몇 가지 주의할 점이 있어 같이 살펴봅시다.

## [브라우저 창의 너비와 높이](https://ko.javascript.info/size-and-scroll-window#ref-632)

창이 차지하는 너비와 높이를 알려면 `document.documentElement`의 `clientWidth`와 `clientHeight`를 사용하면 됩니다.

아래 버튼을 눌러 직접 창 높이를 출력해보세요.

alert(document.documentElement.clientHeight)

**`window` 객체가 아닌 `document.documentElement`를 쓰는 이유**

브라우저의 `window` 객체 역시 `innerWidth`와 `innerHeight` 프로퍼티를 지원합니다. 이 프로퍼티를 써도 원하는 대로 창 크기를 구할 수 있을 것 같은데 왜 `document.documentElement`의 `clientWidth`나 `clientHeight`를 쓰는 걸까요?

스크롤바가 생기면 스크롤바 역시 공간을 차지하는데, `clientWidth`나 `clientHeight`는 스크롤바가 차지하는 공간을 제외해서 너비나 높이 값을 계산합니다. 눈에 보이는 문서에서 콘텐츠가 실제로 들어가게 될 영역의 너비와 높이 값을 반환하는 것이죠.

그런데 `window.innerWidth/innerHeight`는 스크롤바가 차지하는 영역을 포함해 값을 계산합니다.

스크롤바가 있는 경우 스크롤 바 역시 공간을 차지하는데, 이럴 때 `window`객체와 `document.documentElement`의 해당 프로퍼티들은 다른 값을 반환합니다.

```javascript
alert( window.innerWidth ); // 전체 창 너비
alert( document.documentElement.clientWidth ); // 스크롤바가 차지하는 영역을 제외한 창 너비
```

창 사이즈가 필요한 경우는 스크롤 바 안쪽에 무언가를 그리거나 위치시킬 때가 대다수입니다. 따라서 `documentElement`의 `clientHeight/clientWidth`를 써야 합니다.

**`DOCTYPE`을 꼭 써주세요.**

기하 관련 프로퍼티는 HTML에 문서에 `<!DOCTYPE HTML>`이 명시되어있지 않으면 이상하게 동작할 때가 있습니다. 정확하지 않거나 근거를 알 수 없는 값이 툭 튀어나올 수 있죠.

그러니 앞으로는 항상 HTML에 `DOCTYPE`을 명시하도록 합시다.

## [문서의 너비와 높이](https://ko.javascript.info/size-and-scroll-window#ref-633)

이론상 `document.documentElement`는 문서의 루트 요소에 상응하고, 루트 요소엔 콘텐츠 전부가 들어가기 때문에 우리는 문서의 전체 크기를 `document.documentElement`의 `scrollWidth`와 `scrollHeight`를 사용해 재면 되지 않냐고 생각합니다.

그런데 전체 페이지를 대상으로 했을 때, `document.documentElement`의 프로피터들은 우리가 예상한 대로 동작하지 않습니다. Chrome이나 Safari, Opera에서 스크롤이 없는 경우 `documentElement.scrollHeight`는 `documentElement.clientHeight`보다 작을 때가 있죠. 예상하기엔 같은 값이어야 하는데도 말입니다.

정확한 문서 전체 높이 값을 얻으려면 아래 여섯 프로퍼티가 반환하는 값 중 최댓값을 골라야 합니다.

```javascript
let scrollHeight = Math.max(
  document.body.scrollHeight, document.documentElement.scrollHeight,
  document.body.offsetHeight, document.documentElement.offsetHeight,
  document.body.clientHeight, document.documentElement.clientHeight
);

alert('스크롤에 의해 가려진 분을 포함한 전체 문서 높이: ' + scrollHeight);
```

그렇다면 왜 이런 방식으로 문서 전체 높이를 구해야 하는 걸까요? 이유는 알아보지 않는 게 낫습니다. 이런 이상한 계산법은 아주 오래 전부터 있었고 그다지 논리적이지 않은 이유로 만들어졌기 때문입니다.

## [스크롤 정보 얻기](https://ko.javascript.info/size-and-scroll-window#page-scroll)

DOM 요소의 현재 스크롤 상태(스크롤에 의해 가려진 영역에 대한 정보)는 `scrollLeft`와 `scrollTop` 프로퍼티를 통해 구할 수 있습니다.

대부분의 브라우저에서 문서의 스크롤 상태는 `document.documentElement`의 `scrollLeft`나 `scrollTop`을 이용해 구할 수 있습니다. 다만 구버전 WebKit을 기반으로 하는 브라우저에선 버그([5991](https://bugs.webkit.org/show_bug.cgi?id=5991)) 때문에 `document.documentElement`가 아닌 `document.body`를 사용해야 원하는 값을 구할 수 있습니다.

이쯤 되면 스크롤 포지션 정보를 구하기 위해 브라우저별 예외처리까지 다 해야 하나 라는 생각이 들 수 있을 겁니다. 다행히도 `window`객체의 `pageXOffset`과 `pageYOffset`을 사용하면 브라우저 상관없이 스크롤 정보를 구할 수 있어서 이런 예외 상황을 외워두지 않아도 됩니다.

```javascript
alert('세로 스크롤에 의해 가려진 위쪽 영역 높이: ' + window.pageYOffset);
alert('가로 스크롤에 의해 가려진 왼쪽 영역 너비: ' + window.pageXOffset);
```

참고로 이 두 프로퍼티는 읽기만 가능합니다.

## [scrollTo, scrollBy로 스크롤 상태 변경하기](https://ko.javascript.info/size-and-scroll-window#window-scroll)

**중요:**

자바스크립트를 사용해 스크롤을 움직이려면 DOM이 완전히 만들어진 상태이어야 합니다.

`<head>`에 있는 스크립트에서 페이지 전체의 스크롤을 움직이려 하면 잘 동작하지 않을 수 있습니다.

일반 요소의 스크롤 상태는 `scrollTop`이나 `scrollLeft`로 쉽게 변경할 수 있습니다.

페이지 전체의 스크롤 상태 역시 `document.documentElement`의 `scrollTop/scrollLeft`를 사용해 변경 가능하죠(다만, Safari는 `document.body`의 `scrollTop/scrollLeft`를 써야 합니다).

그런데 이보다 더 편하고 브라우저 상관없이 쓸 수 있는 대안이 있긴합니다. 바로 [window.scrollBy(x,y)](https://developer.mozilla.org/ko/docs/Web/API/Window/scrollBy)와 [window.scrollTo(pageX,pageY)](https://developer.mozilla.org/ko/docs/Web/API/Window/scrollTo)입니다.

- `scrollBy(x,y)`메서드를 사용하면 페이지의 스크롤 상태를 현재 포지션을 기준으로 상대적으로 조정합니다. `scrollBy(0,10)`는 문서의 스크롤 상태를 현재를 기준으로 스크롤을 `10px`아래로 내린것 처럼 움직여주죠.

  아래 버튼을 눌러 직접 실습해봅시다.

  window.scrollBy(0,10)

- 반면 `scrollTo(pageX,pageY)`메서드는 *절대 좌표*를 기준으로 페이지 스크롤 상태를 변경합니다. 따라서 눈에 보이는 영역의 왼쪽 위 모서리의 좌표가 문서 전체의 왼쪽 위 모서리를 기준으로 `(pageX, pageY)`가 됩니다. 마치 `scrollLeft`와 `scrollTop` 값을 변경한 것처럼 움직이는 거죠.

  그래서 `scrollTo(0,0)`을 호출하면 문서 스크롤 상태를 처음 상태로 되돌릴 수 있습니다.

  window.scrollTo(0,0)

그리고 이 두 메서드는 브라우저 종류에 상관없이 동일한 동작을 보장합니다.

## [scrollIntoView](https://ko.javascript.info/size-and-scroll-window#ref-634)

추가 메서드 [elem.scrollIntoView(top)](https://developer.mozilla.org/ko/docs/Web/API/Element/scrollIntoView)를 머릿속에 추가해 스크롤 상태를 완벽히 마스터 해봅시다.

`elem.scrollIntoView(top)`를 호출하면 전체 페이지 스크롤이 움직여 `elem`이 눈에 보이는 상태로 변경됩니다. `elem.scrollIntoView`는 인수를 하나 받는데, 인수에 따라 다음과 같이 동작합니다.

- `top`이 `true`(디폴트)인 경우, `elem`이 창 제일 위에 보이도록 스크롤 상태가 변경됩니다. `elem`의 위쪽 모서리가 창의 위쪽 모서리와 일치하게 되죠.
- `top`이 `false`인 경우, `elem`이 창 가장 아래에 보이도록 스크롤 상태가 변경됩니다. `elem`의 아래쪽 모서리가 창의 아래쪽 모서리와 일치하게 변합니다.

버튼을 눌러 직접 실습해봅시다. 첫 번째 버튼을 누르면 버튼 상단이 창 제일 꼭대기로 붙는 것을 확인할 수 있습니다.

this.scrollIntoView()

두 번째 버튼을 누르면 버튼의 아래 모서리가 창 밑으로 붙는 것을 확인할 수 있습니다.

this.scrollIntoView(false)

## [스크롤 막기](https://ko.javascript.info/size-and-scroll-window#ref-635)

때에 따라 문서 스크롤바를 ‘고정’ 해야 하는 경우가 생기곤 합니다. 사용자에게 반드시 전달해야 하는 중요한 메시지가 있어서 이 메시지를 화면에 크게 띄우고, 사용자가 스크롤을 움직여 다른 콘텐츠를 보지 못하게 한 상태에서 메시지를 읽게 하려는 경우가 대표적인 예가 될 수 있습니다.

이럴 때 `document.body.style.overflow = "hidden"`를 사용할 수 있습니다. 해당 스크립트가 동작하면 페이지의 스크롤바 위치가 ‘고정’ 됩니다.

직접 실습해봅시다.

document.body.style.overflow = ‘hidden’

document.body.style.overflow = ‘’

위쪽 버튼을 누르면 스크롤바가 고정되었다가, 아래 버튼을 누르면 고정이 해제되는 것을 확인할 수 있습니다.

이 방법은 `document.body`요소 뿐만 아니라 다른 요소의 스크롤을 고정시킬 때도 사용할 수 있습니다.

그런데 이 방법은 스크롤바가 사라진다는 단점이 있습니다. 스크롤바는 일정 공간을 차지하는데, 스크롤바가 사라지면 해당 공간을 채우기 위해 콘텐츠가 갑자기 ‘움직이는’ 현상이 발생합니다.

이렇게 페이지 전체의 스크롤 상태가 갑자기 변경되면 사용자 입장에선 이상해 보일 수 있기 때문에 개발자는 스크롤바를 고정시키기 전과 후의 `clientWidth`값을 비교해서 해당 증상을 보정해야 합니다. 스크롤바가 사라질 땐 `clientWidth`값이 커지는데 이때 스크롤바가 차지했던 영역만큼 `document.body`에 `padding`을 줘서 콘텐츠 전체의 너비를 스크롤바가 사라지기 전과 같은 값으로 유지할 수 있습니다.

## [요약](https://ko.javascript.info/size-and-scroll-window#ref-636)

기하 프로퍼티:

- 사용자 눈에 보이는 문서(콘텐츠가 실제 보여지는 영역)의 너비와 높이: `document.documentElement.clientWidth/clientHeight`

- 스크롤에 의해 가려진 영역을 포함한 문서 전체의 너비와 높이:

  ```javascript
  let scrollHeight = Math.max(
    document.body.scrollHeight, document.documentElement.scrollHeight,
    document.body.offsetHeight, document.documentElement.offsetHeight,
    document.body.clientHeight, document.documentElement.clientHeight
  );
  ```

스크롤 관련 프로퍼티:

- 현재 스크롤 정보 읽기: `window.pageYOffset/pageXOffset`.
- 스크롤 상태 변경시키기:
  - `window.scrollTo(pageX,pageY)` – 절대 좌표
  - `window.scrollBy(x,y)` – 현재 스크롤 상태를 기준으로 상대적으로 스크롤 정보 변경
  - `elem.scrollIntoView(top)` – `elem`이 눈에 보이도록 스크롤 상태 변경(인수에 따라 창 최상단, 최하단에 해당 요소가 노출되도록 처리)





# 1.11 좌표

요소를 움직이려면 좌표(coordinates)에 익숙해져야 합니다.

대부분의 자바스크립트 메서드는 다음 두 좌표 체계 중 하나를 이용합니다.

1. 창 기준

    

   –

    

   ```
   position:fixed
   ```

   와 유사하게 창(window) 맨 위 왼쪽 모서리를 기준으로 좌표를 계산합니다.

   - 앞으로 우리는 이 좌표를 `clientX/clientY`로 표시할 예정인데, 왜 이런 이름을 쓰는지는 나중에 event 프로퍼티를 공부할 때 명확해집니다.

2. 문서 기준

    

   – 문서(document) 최상단(root)에서

    

   ```
   position:absolute
   ```

   를 사용하는 것과 비슷하게 문서 맨 위 왼쪽 모서리를 기준으로 좌표를 계산합니다.

   - 이 좌표는 `pageX/pageY`로 표시할 예정입니다.

스크롤을 움직이기 전에는 창의 맨 위 왼쪽 모서리가 문서의 맨 위 왼쪽 모서리와 정확히 일치합니다. 그런데 스크롤이 움직이면서 문서가 이동하면 문서 기준 좌표는 변경되지 않지만, 창 내 요소는 움직이기 때문에 창 기준 요소 좌표가 변경됩니다.

다음 그림은 문서 내 한 지점의 스크롤이 움직이기 전(왼쪽)과 후(오른쪽) 좌표를 보여줍니다.

문서가 스크롤 되었을 때:

- `pageY` – 문서 기준 좌표는 문서 맨 위(오른쪽 그림에선 스크롤 되어 보이지 않음)부터 계산되기 때문에 스크롤 후 값은 전과 동일합니다.
- `clientY` – 문서가 스크롤 되면서 해당 지점이 창 상단과 가까워졌기 때문에 창 기준 좌표가 변했습니다(화살표가 짧아짐).

## [getBoundingClientRect로 요소 좌표 얻기](https://ko.javascript.info/coordinates#ref-212)

`elem.getBoundingClientRect()` 메서드는 `elem`을 감싸는 가장 작은 네모의 창 기준 좌표를 [DOMRect](https://www.w3.org/TR/geometry-1/#domrect) 클래스의 객체 형태로 반환합니다.

`DOMRect`의 주요 프로퍼티는 다음과 같습니다.

- `x`와 `y` – 요소를 감싸는 네모의 창 기준 X, Y 좌표
- `width`와 `height` – 요소를 감싸는 네모의 너비, 높이(음수도 가능)

`x`와 `y`, `width`와 `height` 이외에 다음과 같은 파생 프로퍼티도 있습니다.

- `top`과 `bottom` – 요소를 감싸는 네모의 위쪽 모서리, 아래쪽 모서리의 Y 좌표
- `left`와 `right` – 요소를 감싸는 네모의 왼쪽 모서리, 오른쪽 모서리의 X 좌표

아래 버튼을 눌러 창 기준 버튼 좌표를 확인해봅시다.



페이지를 조금씩 스크롤 하면서 창 기준 버튼 위치를 변경하고 버튼을 누르면 좌푯값이 바뀌는 것을 확인할 수 있습니다(수직 스크롤 시 `y`, `top`, `bottom` 값이 변함).

`elem.getBoundingClientRect()`의 각 프로퍼티를 그림으로 표현하면 다음과 같습니다.

그림을 통해 우리는 `x`, `y`와 `width`, `height` 만으로 네모 영역을 완전히 묘사할 수 있다는 사실을 알 수 있습니다. 아래 파생 프로퍼티들은 `x`, `y`, `width`, `height`를 사용해 쉽게 계산 가능합니다.

- `left = x`
- `top = y`
- `right = x + width`
- `bottom = y + height`

`elem.getBoundingClientRect()`를 사용할 때 주의사항은 다음과 같습니다.

- 좌표는 `10.5`처럼 소수일 수 있습니다. 브라우저는 좌표 계산에 소수를 사용하기 때문에 이는 정상입니다. 따라서 `style.left/top`을 사용할 때 값을 반올림할 필요가 없습니다.
- 좌표는 음수일 수 있습니다. 페이지가 스크롤 되어 `elem`이 window 위로 밀려났을 때 `elem.getBoundingClientRect().top`은 음수가 됩니다.

**왜 파생 프로퍼티가 필요한가요? `x`, `y`가 있는데 `top`, `left`는 왜 존재하나요?**

수학적으로 사각형은 시작 지점인 `(x,y)`와 방향 벡터 `(width,height)`만으로도 정의할 수 있는데, 파생 프로퍼티는 편의를 위해 존재합니다.

이론상 `width`와 `height`는 ‘방향이 있는’ 사각형을 나타낼 때 음수가 될 수 있습니다(예시: 시작과 끝 지점을 지정하고 마우스로 드래그해 영역을 표시할 때).

사각형이 오른쪽 아래에서 시작해 왼쪽 위로 ‘올라가면’ `width`와 `height`는 음수가 되죠.

`width`과 `height`가 음수인 사각형을 그림으로 나타내면 다음과 같습니다(`width=-200`, `height=-100`).

그림과 같은 사례에서 `left`와 `top`은 `x`나 `y`와 다릅니다.

이론상 차이가 있긴 하지만 실제 `elem.getBoundingClientRect()`의 `width`와 `height`는 항상 양수입니다. 여기선 파생 프로퍼티가 왜 따로 존재하는지를 설명하기 위해 `width`와 `height`가 음수인 사례를 살펴보았습니다.

**Internet Explorer는 `x`, `y`를 지원하지 않습니다.**

Internet Explorer는 예전부터 `x`, `y` 프로퍼티를 지원하지 않았습니다.

Internet Explorer에선 `DomRect.prototype`에 getter를 추가해 폴리필을 만들거나 `elem.getBoundingClientRect()`의 `width`, `height`가 양수인 경우에 `top`, `left`는 `x`, `y`와 같다는 사실을 이용해 대신 `x`, `y` 대신 `top`, `left`를 사용합니다.

**right, bottom 좌표는 CSS position 프로퍼티와 다릅니다.**

창 기준 좌표와 CSS `position:fixed` 사이에는 명백한 유사점이 있습니다.

그러나 CSS에서 `right` 프로퍼티는 오른쪽 모서리로부터의 거리, `bottom` 프로퍼티는 아래 모서리로부터의 거리를 의미합니다.

위 그림을 보면 그 차이를 한 번에 볼 수 있죠. 그러니 `right`, `bottom`을 포함한 창 기준 좌표를 사용할 땐 측정 기준이 왼쪽 위 모서리라는 사실에 주의해야 합니다.

## [elementFromPoint(x, y)](https://ko.javascript.info/coordinates#elementFromPoint)

`document.elementFromPoint(x, y)`을 호출하면 창 기준 좌표 `(x, y)`에서 가장 가까운 중첩 요소를 반환합니다.

문법은 다음과 같습니다.

```javascript
let elem = document.elementFromPoint(x, y);
```

아래 예시를 실행하면 창 정중앙에 있는 요소의 태그가 얼럿창에 출력되고, 해당 요소가 붉은색으로 강조됩니다.

```javascript
let centerX = document.documentElement.clientWidth / 2;
let centerY = document.documentElement.clientHeight / 2;

let elem = document.elementFromPoint(centerX, centerY);

elem.style.background = "red";
alert(elem.tagName);
```

`document.elementFromPoint(x, y)`는 창 기준 좌표를 사용하기 때문에 현재 스크롤 위치에 강조되는 요소는 다를 수 있습니다.

**창밖 좌표를 대상으로 `elementFromPoint`를 호출하면 `null`이 반환됩니다.**

`document.elementFromPoint(x,y)` 메서드는 `(x,y)`가 보이는 영역 안(창 안)에 있을 때만 동작합니다.

좌표 중 하나라도 음수이거나 창의 너비, 높이를 벗어나면 `null`이 반환됩니다.

이런 특징을 모르고 코드를 짜면 다음과 같은 전형적인 실수를 하게 됩니다.

```javascript
let elem = document.elementFromPoint(x, y);
// 요소가 창 밖으로 나가면 lem = null
elem.style.background = ''; // 에러!
```

## [요소를 창 내 특정 좌표에 고정하기](https://ko.javascript.info/coordinates#ref-213)

좌표는 대부분 무언가를 위치시키려는 목적으로 사용합니다.

요소 근처에 무언가를 표시할 때에는 `getBoundingClientRect`를 사용해 요소의 좌표를 얻고 CSS `position`을 `left/top`(또는 `right/bottom`)과 함께 사용해서 표시하죠.

예를 들어 아래 `createMessageUnder(elem, html)` 함수는 `elem` 아래쪽에 메시지를 표시합니다.

```javascript
let elem = document.getElementById("coords-show-mark");

function createMessageUnder(elem, html) {
  // 메시지가 담길 요소를 만듭니다.
  let message = document.createElement('div');
  // 요소를 스타일링 할 땐 css 클래스를 사용하는 게 좋습니다.
  message.style.cssText = "position:fixed; color: red";

  // 좌표를 지정합니다. 이때 "px"을 함께 써주는 걸 잊지 마세요!
  let coords = elem.getBoundingClientRect();

  message.style.left = coords.left + "px";
  message.style.top = coords.bottom + "px";

  message.innerHTML = html;

  return message;
}

// 사용법:
// 문서 안에 메시지를 띄우고, 5초 동안만 보여줍니다.
let message = createMessageUnder(elem, '독도는 우리땅!');
document.body.append(message);
setTimeout(() => message.remove(), 5000);
```

직접 버튼을 눌러 위 예시를 실행해 봅시다.

id가 "coords-show-mark"인 버튼이 여기 있고, 메시지는 그 아래에 나타납니다.

위 예시를 응용하면 메시지를 왼쪽 이나 오른쪽, 아래에 표시할 수도 있고 CSS 애니메이션을 적용하면 ‘fade-in’ 등의 효과도 줄 수 있습니다. 좌푯값과 요소의 크기만 알면 손쉽게 원하는 것을 할 수 있죠.

그런데 예시에서 뭔가 부자연스러운 게 보입니다. 페이지를 스크롤 하면 메시지가 버튼에서 떨어지네요.

메시지가 버튼에서 떨어지는 이유는 아주 명확합니다. 메시지 요소가 `position:fixed`이기 때문에 페이지가 스크롤 되어도 창 기준 동일한 위치에 있기 때문입니다.

이런 부자연스러운 현상을 개선하려면 문서 기준 좌표와 `position:absolute`를 함께 사용해야 합니다.

## [문서 기준 좌표](https://ko.javascript.info/coordinates#getCoords)

문서 기준 좌표는 창이 아닌 문서 왼쪽 위 모서리부터 시작합니다.

CSS와 비교하자면 창 기준 좌표는 `position:fixed`에 해당하고 문서 기준 좌표는 맨 위 기준 `position:absolute`와 비슷합니다.

문서 내 특정 좌표에 무언가를 위치시키고 싶을 땐 `position:absolute`와 `top,`left`를 사용하면 스크롤 이동에 상관없이 해당 요소를 한 좌표에 머물게 할 수 있습니다. 그러려면 우선 정확한 좌표가 필요합니다.

그런데 요소의 문서 기준 좌표를 제공하는 표준 메서드가 아직 없습니다. 하지만 아주 쉽게 코드를 작성할 수 있습니다.

두 좌표 체계(창 기준 좌표와 문서 기준 좌표)는 다음 수식을 통해 연관시킬 수 있습니다.

- `pageY` = `clientY` + 문서에서 세로 방향 스크롤에 의해 밀려난 부분의 높이
- `pageX` = `clientX` + 문서에서 가로 방향 스크롤에 의해 밀려난 부분의 너비

다음 함수 `getCoords(elem)`는 `elem.getBoundingClientRect()`을 사용해 창 기준 좌표를 얻고 여기에 스크롤에 의해 가려진 영역의 너비나 높이를 더합니다.

```javascript
// 요소의 문서 기준 좌표를 얻습니다.
function getCoords(elem) {
  let box = elem.getBoundingClientRect();

  return {
    top: box.top + window.pageYOffset,
    right: box.right + window.pageXOffset,
    bottom: box.bottom + window.pageYOffset,
    left: box.left + window.pageXOffset
  };
}
```

그런데 위 예시에서 `position:absolute`을 사용했다면 스크롤을 해도 메시지가 버튼 요소 근처에 머물렀을 겁니다.

이를 반영한 함수 `createMessageUnder`를 같이 살펴봅시다.

```javascript
function createMessageUnder(elem, html) {
  let message = document.createElement('div');
  message.style.cssText = "position:absolute; color: red";

  let coords = getCoords(elem);

  message.style.left = coords.left + "px";
  message.style.top = coords.bottom + "px";

  message.innerHTML = html;

  return message;
}
```

## [요약](https://ko.javascript.info/coordinates#ref-214)

페이지 내 모든 점은 다음과 같은 좌표를 갖습니다.

1. 창 기준 – `elem.getBoundingClientRect()`
2. 문서 기준 – `elem.getBoundingClientRect()`와 현재 스크롤 상태

창 기준 좌표는 `position:fixed`와 사용하면 좋고 문서 기준 좌표는 `position:absolute`와 사용하면 좋습니다.

두 좌표 체계 모두 장단점이 있습니다. CSS의 `position`, `absolute`, `fixed`처럼 이게 필요할 때도 있고 저게 필요할 때도 있습니다.

## [과제](https://ko.javascript.info/coordinates#tasks)

### [Find window coordinates of the field](https://ko.javascript.info/coordinates#ref-218)



중요도: 5

In the iframe below you can see a document with the green “field”.

Use JavaScript to find window coordinates of corners pointed by with arrows.

There’s a small feature implemented in the document for convenience. A click at any place shows coordinates there.

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/find-point-coordinates/source/" style="display: block; border: 0px; width: 798px; height: 360px; background: var(--iframeBg);"></iframe>

Your code should use DOM to get window coordinates of:

1. Upper-left, outer corner (that’s simple).
2. Bottom-right, outer corner (simple too).
3. Upper-left, inner corner (a bit harder).
4. Bottom-right, inner corner (there are several ways, choose one).

The coordinates that you calculate should be the same as those returned by the mouse click.

P.S. The code should also work if the element has another size or border, not bound to any fixed values.

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/4isGS6WyrqGxmxuT?p=preview)

해답

### [Show a note near the element](https://ko.javascript.info/coordinates#ref-219)



중요도: 5

Create a function `positionAt(anchor, position, elem)` that positions `elem`, depending on `position` near `anchor` element.

The `position` must be a string with any one of 3 values:

- `"top"` – position `elem` right above `anchor`
- `"right"` – position `elem` immediately at the right of `anchor`
- `"bottom"` – position `elem` right below `anchor`

It’s used inside function `showNote(anchor, position, html)`, provided in the task source code, that creates a “note” element with given `html` and shows it at the given `position` near the `anchor`.

Here’s the demo of notes:

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/position-at/solution/" style="display: block; border: 0px; width: 798px; height: 350px; background: var(--iframeBg);"></iframe>

[샌드박스를 열어 정답을 작성해보세요.](https://plnkr.co/edit/q6lK1jl2Qak6T0JT?p=preview)

해답

### [Show a note near the element (absolute)](https://ko.javascript.info/coordinates#ref-220)



중요도: 5

Modify the solution of the [previous task](https://ko.javascript.info/task/position-at) so that the note uses `position:absolute` instead of `position:fixed`.

That will prevent its “runaway” from the element when the page scrolls.

Take the solution of that task as a starting point. To test the scroll, add the style `<body style="height: 2000px">`.

해답

### [Position the note inside (absolute)](https://ko.javascript.info/coordinates#ref-221)



중요도: 5

Extend the previous task [Show a note near the element (absolute)](https://ko.javascript.info/task/position-at-absolute): teach the function `positionAt(anchor, position, elem)` to insert `elem` inside the `anchor`.

New values for `position`:

- `top-out`, `right-out`, `bottom-out` – work the same as before, they insert the `elem` over/right/under `anchor`.
- `top-in`, `right-in`, `bottom-in` – insert `elem` inside the `anchor`: stick it to the upper/right/bottom edge.

For instance:

```javascript
// shows the note above blockquote
positionAt(blockquote, "top-out", note);

// shows the note inside blockquote, at the top
positionAt(blockquote, "top-in", note);
```

The result:

<iframe class="code-result__iframe" data-trusted="1" src="https://ko.js.cx/task/position-inside-absolute/solution/" style="display: block; border: 0px; width: 798px; height: 310px; background: var(--iframeBg);"></iframe>

As the source code, take the solution of the task [Show a note near the element (absolute)](https://ko.javascript.info/task/position-at-absolute).

해답