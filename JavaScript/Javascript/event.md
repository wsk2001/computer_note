# 이벤트

### 이벤트의 개념

------

#### 이벤트(event)란?

이벤트(event)란 웹 브라우저가 알려주는 HTML 요소에 대한 사건의 발생을 의미합니다.

 

웹 페이지에 사용된 자바스크립트는 이렇게 발생한 이벤트에 반응하여 특정 동작을 수행할 수 있습니다.

따라서 클라이언트 측 자바스크립트를 비동기식 이벤트 중심(event-driven)의 프로그래밍 모델이라고 합니다.

------

#### 이벤트 타입(event type)

이벤트 타입(event type)은 발생한 이벤트의 종류를 나타내는 문자열로, 이벤트 명(event name)이라고도 합니다.

가장 많이 사용하는 키보드, 마우스, HTML DOM, Window 객체 등을 처리하는 이벤트가 폭넓게 제공되고 있습니다.

 

다음 예제는 HTML 문서의 특정 단락을 클릭하면 발생하는 이벤트를 처리하는 예제입니다.

##### 예제

``` js
<p onclick="changeText(this)">이 문자열을 클릭해 보세요!</p>
...
<script>
function changeText(element) {
    element.innerHTML = "문자열의 내용이 바뀌었습니다!";
}
</script>
```



#### 이벤트 명세(event specification)

예전에는 onload, onclick, onmouseover와 같이 기본적이고도 단순한 이벤트만을 사용했습니다.

하지만 웹 기술의 발전에 따라 touch나 gesture와 같은 새로운 이벤트들이 빠르게 늘어났습니다.

따라서 하나의 표준만으로는 이벤트의 전체 목록을 정의할 수 없는 상황이 되었습니다.

 

이렇게 방대해진 이벤트를 위한 명세는 현재 다음과 같이 나누어져 정의되어 있습니다.

- 1. DOM Level 3 이벤트 명세

- 2. HTML5 관련 이벤트 명세

- 3. 모바일 장치를 위한 이벤트 명세



### 이벤트 리스너 등록

------

#### 이벤트 리스너(event listener)

이벤트 리스너란 이벤트가 발생했을 때 그 처리를 담당하는 함수를 가리키며, 이벤트 핸들러(event handler)라고도 합니다.

지정된 타입의 이벤트가 특정 요소에서 발생하면, 웹 브라우저는 그 요소에 등록된 이벤트 리스너를 실행시킵니다.

------

#### 이벤트 리스너 등록

작성된 이벤트 리스너는 먼저 해당 객체나 요소에 등록되어야만 호출될 수 있습니다.

객체나 요소에 이벤트 리스너를 등록하는 방법은 다음과 같습니다.

- 1. 이벤트의 대상이 되는 객체나 요소에 프로퍼티로 등록하는 방법

- 2. 객체나 요소의 메소드에 이벤트 리스너를 전달하는 방법

------

#### 객체나 요소에 프로퍼티로 등록하는 방법

객체나 요소에 프로퍼티로 이벤트 리스너를 등록할 때는 다음과 같은 방법을 사용할 수 있습니다.

- 1. 자바스크립트 코드에서 프로퍼티로 등록

- 2. HTML 태그에 속성으로 등록

자바스크립트 코드에서 프로퍼티로 등록하는 방법은 거의 모든 브라우저가 대부분의 이벤트 타입을 지원하고 있습니다.

이 방법의 단점은 이벤트 타입별로 오직 하나의 이벤트 리스너만을 등록할 수 있다는 점입니다.

##### 예제

``` js
window.onload = function() {                    // 이 함수는 HTML 문서가 로드될 때 실행됨.
    var text = document.getElementById("text"); // 아이디가 "text"인 요소를 선택함.
    text.innerHTML = "HTML 문서가 로드되었습니다.";
}
```

또한, 다음과 같이 HTML 태그에 속성으로 이벤트 리스너를 등록할 수도 있습니다.

이 방법의 단점으로는 HTML 코드에 자바스크립트 코드가 추가됨으로써 가독성이 안 좋아지며, 유지보수도 힘들어집니다.

##### 예제

``` js
<p onclick="alert('문자열을 클릭했어요!')">이 문자열을 클릭해 보세요!</p>
```



#### 객체나 요소의 메소드에 이벤트 리스너를 전달하는 방법

객체나 요소의 메소드에 이벤트 리스너를 전달할 때는 다음 메소드를 사용할 수 있습니다. 

- 1. addEventListener()

- 2. attachEvent() 

addEventListener() 메소드는 거의 모든 브라우저에서 지원하는 이벤트 리스너 등록을 위한 메소드입니다.

이 메소드의 원형은 다음과 같습니다.

##### 원형

``` JS
대상객체.addEventListener(이벤트명, 실행할이벤트리스너, 이벤트전파방식)
```

- 1. 이벤트 명 : 이벤트 리스너를 등록할 이벤트 타입을 문자열로 전달합니다.

- 2. 실행할 이벤트 리스너 : 지정된 이벤트가 발생했을 때 실행할 이벤트 리스너를 전달합니다.

- 3. 이벤트 전파 방식 : false면 버블링(bubbling) 방식으로, true면 캡처링(capturing) 방식으로 이벤트를 전파합니다.


이벤트 전파 방식에 대한 더 자세한 사항은 자바스크립트 이벤트 리스너 호출 수업에서 확인할 수 있습니다.

[자바스크립트 이벤트 리스너 호출 수업 확인 =>](http://tcpschool.com/javascript/js_event_eventListenerCall)

##### 예제

``` JS
var showBtn = document.getElementById("btn"); // 아이디가 "btn"인 요소를 선택함.
showBtn.addEventListener("click", showText);  // 선택한 요소에 click 이벤트 리스너를 등록함.

function showText() {
    document.getElementById("text").innerHTML = "짜잔~!! 텍스트가 나타났어요!!";
}
```

> 이벤트 리스너를 프로퍼티로 등록할 때는 "on"이 붙은 이벤트 타입을 사용하지만, addEventListener() 메소드에서는 "on"이 붙지 않은 이벤트 타입을 사용해야 합니다.



addEventListener() 메소드는 익스플로러 8과 그 이전 버전, 오페라 6과 그 이전 버전에서는 지원하지 않으므로, 대신에 이와 유사한 동작을 하는 attachEvent() 메소드와 detachEvent() 메소드를 사용해야 합니다.

------

#### 여러 개의 이벤트 리스너 등록

addEventListener() 메소드를 사용하면, 하나의 객체에 여러 개의 이벤트 리스너를 등록할 수 있습니다.

##### 예제

``` JS
var btn = document.getElementById("btn");        // 아이디가 "btn"인 요소를 선택함.
btn.addEventListener("click", clickBtn);         // 선택한 요소에 click 이벤트 리스너를 등록함.
btn.addEventListener("mouseover", mouseoverBtn); // 선택한 요소에 mouseover 이벤트 리스너를 등록함.
btn.addEventListener("mouseout", mouseoutBtn);   // 선택한 요소에 mouseout 이벤트 리스너를 등록함.

function clickBtn() {
    document.getElementById("text").innerHTML = "버튼이 클릭됐어요!";
}

function mouseoverBtn() {
    document.getElementById("text").innerHTML = "버튼 위에 마우스가 있네요!";
}

function mouseoutBtn() {
    document.getElementById("text").innerHTML = "버튼 밖으로 마우스가 나갔어요!";
}
```



#### 이벤트 리스너 삭제

removeEventListener() 메소드를 사용하면, 등록된 이벤트 리스너를 손쉽게 삭제할 수 있습니다.

##### 예제

``` JS
function clickBtn() {
    btn.removeEventListener("mouseover", mouseoverBtn);
    btn.removeEventListener("mouseout", mouseoutBtn);
    document.getElementById("text").innerHTML = "이벤트 리스너가 삭제되었어요!";
}
```



### 이벤트 리스너 호출

------

#### 이벤트 리스너 호출

이벤트 리스너가 등록되고 해당 객체나 요소에 지정된 타입의 이벤트가 발생하면, 브라우저는 자동으로 등록된 이벤트 리스너를 호출합니다.

이때 이벤트 리스너는 인수로 이벤트 객체(event object)를 전달받으며, 식별자를 통해 전달받은 이벤트 객체를 참조합니다.

------

#### 이벤트 객체(event object)

이벤트 객체(event object)란 특정 타입의 이벤트와 관련이 있는 객체입니다.

 

이벤트 객체는 해당 타입의 이벤트에 대한 상세 정보를 저장하고 있습니다.

모든 이벤트 객체는 이벤트의 타입을 나타내는 type 프로퍼티와 이벤트의 대상을 나타내는 target 프로퍼티를 가집니다.

이러한 이벤트 객체는 이벤트 리스너가 호출될 때 인수로 전달됩니다.

##### 예제

``` JS
var btn = document.getElementById("btn"); // 아이디가 "btn"인 요소를 선택함.
btn.addEventListener("click", clickBtn);  // 선택한 요소에 click 이벤트 리스너를 등록함.

function clickBtn(event) {
    document.getElementById("text").innerHTML =
    "이 이벤트의 타입은 " + event.type + "이며, 이벤트의 대상은 " + event.target + "입니다.";
}
```



#### 이벤트 호출 순서

addEventListener() 메소드를 사용하면 하나의 이벤트 타입에 여러 개의 이벤트 리스너를 등록할 수 있습니다. 

이때 특정 타입의 이벤트가 발생하면 브라우저는 다음과 같은 순서로 이벤트를 호출하게 됩니다.

- 1. 이벤트의 대상이 되는 객체나 요소에 프로퍼티로 등록한 이벤트 리스너를 가장 먼저 호출합니다.

- 2. 그 후 addEventListener() 메소드를 사용하여 등록한 이벤트 리스너를 등록한 순서대로 호출합니다.

------



#### 이벤트 전파(event propagation)

이벤트 전파란 이벤트가 발생했을 때, 브라우저가 이벤트 리스너를 실행시킬 대상 요소를 결정하는 과정을 의미합니다.

이벤트의 대상이 Window 객체와 같은 단일 객체라면 이벤트의 전파는 일어나지 않습니다.

하지만 Document 객체나 HTML 문서의 요소에서 이벤트가 일어나면 대상 요소를 결정하기 위해 이벤트의 전파가 일어납니다.

이벤트의 전파 방식은 크게 다음 두 가지 방식으로 구분됩니다.

- 1. 버블링(bubbling) 전파 방식

- 2. 캡쳐링(capturing) 전파 방식

------

#### 버블링(bubbling) 전파 방식

버블링 전파 방식은 이벤트가 발생한 요소부터 시작해서, DOM 트리를 따라 위쪽으로 올라가며 전파되는 방식입니다.

이 전파 방식은 해당 요소의 리스너가 실행된 후, 그 부모 요소에 등록된 리스너가 실행되고, 또다시 그 부모 요소에 등록된 리스너가 실행됩니다.

이러한 이벤트의 전파는 Document 객체뿐만 아니라 가장 마지막에는 Window 객체까지 계속 이어집니다.

버블링 전파 방식은 다수의 요소에 공통으로 적용되는 이벤트 리스너를 각각의 요소마다 따로 등록할 필요 없이 공통된 조상 요소에 한 번만 등록하면 처리할 수 있다는 장점을 가집니다.

##### 예제

``` JS
// 각 요소마다 버블링 방식으로 click 이벤트 리스너를 등록함.
document.getElementById("divBox").addEventListener("click", clickDiv);
document.getElementById("paraBox").addEventListener("click", clickPara);
document.getElementById("spanBox").addEventListener("click", clickSpan);
function clickDiv(event)  { document.getElementById("text").innerHTML += "div 요소를 click 하셨네요!<br>"; }
function clickPara(event) { document.getElementById("text").innerHTML += "p 요소를 click 하셨네요!<br>"; }
function clickSpan(event) { document.getElementById("text").innerHTML += "span 요소를 click 하셨네요!<br>"; }
```



#### 캡쳐링(capturing) 전파 방식

캡쳐링 전파 방식은 이벤트가 발생한 요소까지 DOM 트리의 최상위부터 아래쪽으로 내려가면 전파되는 방식입니다.

 

이 전파 방식은 맨 먼저 Window 객체의 리스너가 실행된 후, Document 객체에 등록된 리스너가 실행되고, 또다시 그 자식 요소에 등록된 리스너가 실행됩니다.

이러한 이벤트의 전파는 이벤트가 발생한 요소까지 이어집니다.

이 전파 방식을 사용하기 위해서는 addEventListener() 메소드의 세 번째 인수에 true를 전달하면 됩니다.

 

캡쳐링 전파 방식은 실제 이벤트의 대상이 되는 요소에 이벤트가 전달되기 전에 상위 요소에 등록된 이벤트 리스너가 이를 가로채거나 잡아낼 수 있습니다.

이렇게 이벤트를 걸러내어 해당 이벤트 리스너가 호출되지 않도록 하는 기법을 이벤트 취소 기법이라고 합니다.

##### 예제

``` JS
// 각 요소마다 캡쳐링 방식으로 click 이벤트 리스너를 등록함.
document.getElementById("divBox").addEventListener("click", clickDiv, true);
document.getElementById("paraBox").addEventListener("click", clickPara, true);
document.getElementById("spanBox").addEventListener("click", clickSpan, true);
```

> 캡쳐링 전파 방식은 addEventListener() 메소드를 지원하지 않는 익스플로러 8과 그 이전 버전, 오페라 6과 그 이전 버전에서는 사용할 수 없습니다.



#### 이벤트 기본 동작의 취소

HTML <a>요소에 클릭(click) 이벤트가 발생하면 브라우저는 지정된 주소를 따라가 새로운 웹 페이지를 열게 됩니다.

이렇게 특정 이벤트는 미리 지정된 기본 동작을 가지고 있습니다.

하지만 preventDefalult() 메소드나 returnValue 프로퍼티를 이용하면, 이러한 기본 동작의 실행을 취소할 수 있습니다.

##### 예제

``` JS
// 각 요소마다 버블링 방식으로 click 이벤트 리스너를 등록함.
document.getElementById("divBox").addEventListener("click", clickDiv);
document.getElementById("paraBox").addEventListener("click", clickPara);
document.getElementById("linkBox").addEventListener("click", clickLink);

function clickDiv(event) { document.getElementById("text").innerHTML += "div 요소를 click 하셨네요!<br>"; }
function clickPara(event) { document.getElementById("text").innerHTML += "p 요소를 click 하셨네요!<br>"; }

function clickLink(event) {
    event.preventDefault(); // 링크의 기본 동작을 취소함.
    document.getElementById("text").innerHTML += "링크의 기본 동작을 막았어요!<br>";
    document.getElementById("text").innerHTML += "a 요소를 click 하셨네요!<br>";
}
```



#### 이벤트 전파의 취소

앞서 살펴본 이벤트의 기본 동작뿐만 아니라 이벤트의 전파도 취소할 수 있습니다.

stopPropagation() 메소드나 cancelBubble 프로퍼티를 이용하면, 이러한 이벤트의 전파를 취소할 수 있습니다.

##### 예제

``` JS
function clickLink(event) {
    event.preventDefault();  // 링크의 기본 동작을 취소함.
    document.getElementById("text").innerHTML += "링크의 기본 동작을 막았어요!<br>";
    event.stopPropagation(); // 이벤트의 전파를 취소함.
    document.getElementById("text").innerHTML += "이벤트의 전파를 막았어요!<br>";
}
```



