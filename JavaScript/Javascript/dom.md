# 문서 객체 모델

### DOM의 개념

------

#### 문서 객체 모델(DOM)이란?

문서 객체 모델(DOM, Document Object Model)은 XML이나 HTML 문서에 접근하기 위한 일종의 인터페이스입니다.

이 객체 모델은 문서 내의 모든 요소를 정의하고, 각각의 요소에 접근하는 방법을 제공합니다.

 

이러한 DOM은 W3C의 표준 객체 모델이며, 다음과 같이 계층 구조로 표현됩니다.

![](./images/img_js_htmldom.png)

자바스크립트는 이러한 객체 모델을 이용하여 다음과 같은 작업을 할 수 있습니다.

- 자바스크립트는 새로운 HTML 요소나 속성을 추가할 수 있습니다.

- 자바스크립트는 존재하는 HTML 요소나 속성을 제거할 수 있습니다.

- 자바스크립트는 HTML 문서의 모든 HTML 요소를 변경할 수 있습니다.

- 자바스크립트는 HTML 문서의 모든 HTML 속성을 변경할 수 있습니다.

- 자바스크립트는 HTML 문서의 모든 CSS 스타일을 변경할 수 있습니다.

- 자바스크립트는 HTML 문서에 새로운 HTML 이벤트를 추가할 수 있습니다.

- 자바스크립트는 HTML 문서의 모든 HTML 이벤트에 반응할 수 있습니다.

------

#### DOM의 종류

W3C DOM 표준은 세 가지 모델로 구분됩니다.

1. **Core DOM** : 모든 문서 타입을 위한 DOM 모델

2. **HTML DOM** : HTML 문서를 위한 DOM 모델

3. **XML DOM** : XML 문서를 위한 DOM 모델

------



#### HTML DOM

HTML DOM은 HTML 문서를 조작하고 접근하는 표준화된 방법을 정의합니다.

모든 HTML 요소는 HTML DOM를 통해 접근할 수 있습니다.

------



#### XML DOM

XML DOM은 XML 문서에 접근하여, 그 문서를 다루는 표준화된 방법을 정의합니다.

모든 XML 요소는 XML DOM를 통해 접근할 수 있습니다.

 

XML DOM에 대한 더 자세한 사항은 XML DOM 수업에서 확인할 수 있습니다.

[XML DOM 수업 확인 =>](http://tcpschool.com/xml/xml_dom_concept)



### Document 객체

------

#### Document 객체

Document 객체는 웹 페이지 그 자체를 의미합니다.

웹 페이지에 존재하는 HTML 요소에 접근하고자 할 때는 반드시 Document 객체부터 시작해야 합니다.

------

#### Document 메소드

Document 객체는 HTML 요소와 관련된 작업을 도와주는 다양한 메소드를 제공합니다. 

- HTML 요소의 선택

- HTML 요소의 생성

- HTML 이벤트 핸들러 추가

- HTML 객체의 선택

------

#### HTML 요소의 선택

HTML 요소를 선택하기 위해 제공되는 메소드는 다음과 같습니다.

|                     메소드                      |                     설명                      |
| :---------------------------------------------: | :-------------------------------------------: |
|   **document.getElementsByTagName(태그이름)**   |     해당 태그 이름의 요소를 모두 선택함.      |
|       **document.getElementById(아이디)**       |         해당 아이디의 요소를 선택함.          |
| **document.getElementsByClassName(클래스이름)** |    해당 클래스에 속한 요소를 모두 선택함.     |
|    **document.getElementByName(name속성값)**    | 해당 name 속성값을 가지는 요소를 모두 선택함. |
|      **document.querySelectorAll(선택자)**      |  해당 선택자로 선택되는 요소를 모두 선택함.   |

 

이 중에서 document.querySelectorAll() 메소드는 브라우저별로 지원 여부가 다릅니다.

document.querySelectorAll() 메소드를 지원하는 주요 웹 브라우저의 버전은 다음과 같습니다.

|           메소드            | ![ie](http://tcpschool.com/img/icon-ie.png) | ![chrome](http://tcpschool.com/img/icon-chrome.png) | ![firefox](http://tcpschool.com/img/icon-firefox.png) | ![safari](http://tcpschool.com/img/icon-safari.png) | ![opera](http://tcpschool.com/img/icon-opera.png) |
| :-------------------------: | :-----------------------------------------: | :-------------------------------------------------: | :---------------------------------------------------: | :-------------------------------------------------: | :-----------------------------------------------: |
| document.querySelectorAll() |                     9.0                     |                         1.0                         |                          3.5                          |                         3.2                         |                       10.0                        |

------

#### HTML 요소의 생성

새로운 HTML 요소를 생성하기 위해 제공되는 메소드는 다음과 같습니다.

|                메소드                |                   설명                   |
| :----------------------------------: | :--------------------------------------: |
| **document.createElement(HTML요소)** |        지정된 HTML 요소를 생성함.        |
|      **document.write(텍스트)**      | HTML 출력 스트림을 통해 텍스트를 출력함. |

------

#### HTML 이벤트 핸들러 추가

HTML 요소에 이벤트 핸들러를 추가하기 위해 제공되는 메소드는 다음과 같습니다.

|                            메소드                            |                           설명                           |
| :----------------------------------------------------------: | :------------------------------------------------------: |
| document.getElementById(아이디).onclick = function(){ 실행할 코드 } | 마우스 클릭 이벤트와 연결될 이벤트 핸들러 코드를 추가함. |

------

#### HTML 객체의 선택

HTML DOM Level 1은 1998년에 정의되어, HTML5에서도 계속 사용되고 있습니다.

그 후 2004년에는 HTML DOM Level 3가 새롭게 정의되어, Level 1과 같이 사용되고 있습니다.

 

이러한 HTML DOM에서 제공하는 객체 집합(object collection)을 이용하면 HTML 객체를 손쉽게 선택할 수 있습니다.

|          객체 집합           |                             설명                             | DOM Level |
| :--------------------------: | :----------------------------------------------------------: | :-------: |
|       document.anchors       |          name 속성을 가지는 <a>요소를 모두 반환함.           |     1     |
|       document.applets       |        applet 요소를 모두 반환함. (HTML5에서 제외됨)         |     1     |
|        document.body         |                     <body>요소를 반환함.                     |     1     |
|       document.cookie        |             HTML 문서의 쿠키 (cookie)를 반환함.              |     1     |
|       document.domain        | HTML 문서가 위치한 서버의 도메인 네임 (domain name)을 반환함. |     1     |
|        document.forms        |                  <form>요소를 모두 반환함.                   |     1     |
|       document.images        |                 `<img>` 요소를 모두 반환함.                  |     1     |
|        document.links        |    href 속성을 가지는 <area>요소와 <a>요소를 모두 반환함.    |     1     |
|      document.referrer       |         링크 (linking)되어 있는 문서의 URI를 반환함.         |     1     |
|        document.title        |                    <title>요소를 반환함.                     |     1     |
|         document.URL         |          **HTML 문서의 완전한 URL 주소를 반환함.**           |     1     |
|       document.baseURI       |      HTML 문서의 절대 URI(absolute base URI)를 반환함.       |     3     |
|       document.doctype       |          HTML 문서의 문서 타입 (doctype)을 반환함.           |     3     |
|   document.documentElement   |                     <html>요소를 반환함.                     |     3     |
|    document.documentMode     |          웹 브라우저가 사용하고 있는 모드를 반환함.          |     3     |
|     document.documentURI     |                  HTML 문서의 URI를 반환함.                   |     3     |
|      document.domConfig      |        HTML DOM 설정을 반환함.  - 더는 사용하지 않음         |     3     |
|       document.embeds        |                  <embed>요소를 모두 반환함.                  |     3     |
|        document.head         |                     <head>요소를 반환함.                     |     3     |
|   document.implementation    |           HTML DOM 구현 (implementation)을 반환함.           |     3     |
|    document.inputEncoding    |    HTML 문서의 문자 인코딩 (character set) 형식을 반환함.    |     3     |
|    document.lastModified     |        HTML 문서의 마지막 갱신 날짜 및 시간을 반환함         |     3     |
|     document.readyState      |       HTML 문서의 로딩 상태 (loading status)를 반환함.       |     3     |
|       document.scripts       |                 <script>요소를 모두 반환함.                  |     3     |
| document.strictErrorChecking |               오류의 강제 검사 여부를 반환함.                |     3     |



### DOM 요소

------

#### DOM 요소의 선택

HTML 요소를 다루기 위해서는 우선 해당 요소를 선택해야만 합니다.

자바스크립트에서 특정 HTML 요소를 선택하는 방법은 다음과 같습니다.

- 1. HTML 태그 이름(tag name)을 이용한 선택

- 2. 아이디(id)를 이용한 선택

- 3. 클래스(class)를 이용한 선택

- 4. name 속성(attribute)을 이용한 선택

- 5. CSS 선택자(selector)를 이용한 선택

- 6. HTML 객체 집합(object collection)을 이용한 선택

------

#### HTML 태그 이름(tag name)을 이용한 선택

getElementsByTagName() 메소드는 HTML 태그 이름을 이용하여 HTML 요소를 선택합니다.

##### 예제

```js
var selectedItem = document.getElementsByTagName("li"); // 모든 <li> 요소를 선택함.

for (var i = 0; i < selectedItem.length; i++) {
    selectedItem.item(i).style.color = "red"; // 선택된 모든 요소의 텍스트 색상을 변경함.
}
```

> item() 메소드는 해당 HTML 객체 집합(obejct collection)에서 전달받은 인덱스에 해당하는 요소를 반환합니다.

> HTML 요소의 style 프로퍼티를 이용하면, 해당 요소의 CSS 스타일을 변경할 수 있습니다.



#### 아이디(id)를 이용한 선택

getElementById() 메소드는 아이디를 이용하여 HTML 요소를 선택합니다.

##### 예제

``` js
var selectedItem = document.getElementById("even"); // 아이디가 "even"인 요소를 선택함.
selectedItem.style.color = "red"; // 선택된 요소의 텍스트 색상을 변경함.
```

자바스크립트에서 아이디(id)를 이용한 선택은 해당 아이디를 가지고 있는 요소 중에서 첫 번째 요소 단 하나만을 선택합니다.

따라서 여러 요소를 선택하고 싶을 때는 태그 이름이나 클래스와 같은 다른 방법을 사용해야 합니다.

------



#### 클래스(class)를 이용한 선택

getElementsByClassName() 메소드는 클래스 이름을 이용하여 HTML 요소를 선택합니다.

##### 예제

``` js
var selectedItem = document.getElementsByClassName("odd"); // 클래스가 "odd"인 모든 요소를 선택함.

for (var i = 0; i < selectedItem.length; i++) {
    selectedItem.item(i).style.color = "red"; // 선택된 모든 요소의 텍스트 색상을 변경함.
}
```



#### name 속성을 이용한 선택

getElementByName() 메소드는 HTML 요소의 name 속성을 이용하여 HTML 요소를 선택합니다.

##### 예제

``` js
var selectedItem = document.getElementsByName("first"); // name 속성값이 "first"인 모든 요소를 선택함.

for (var i = 0; i < selectedItem.length; i++) {
    selectedItem.item(i).style.color = "red"; // 선택된 모든 요소의 텍스트 색상을 변경함.
}
```



#### CSS 선택자(selector)를 이용한 선택

querySelectorAll() 메소드는 CSS 선택자(아이디, 클래스, 속성, 속성값 등)를 이용하여 HTML 요소를 선택합니다.

##### 예제

``` js
var selectedItem = document.querySelectorAll("li.odd"); // 클래스가 "odd"인 요소 중에서 <li> 요소만을 선택함.

for (var i = 0; i < selectedItem.length; i++) {
    selectedItem.item(i).style.color = "red"; // 선택된 모든 요소의 텍스트 색상을 변경함.
}
```

querySelectorAll() 메소드는 익스플로러 8과 그 이전 버전에서 지원하지 않습니다.



#### HTML 객체 집합(object collection)을 이용한 선택

HTML DOM에서 제공하는 객체 집합(object collection)을 이용하여 HTML 요소를 선택할 수 있습니다.

##### 예제

``` js
var title = document.title; // <title> 요소를 선택함.
document.write(title);
```



#### DOM 요소의 내용 변경

HTML DOM을 이용하면 HTML 요소의 내용(content)이나 속성값 등을 손쉽게 변경할 수 있습니다.

 

HTML 요소의 내용을 변경하는 가장 쉬운 방법은 innerHTML 프로퍼티를 이용하는 것입니다.

##### 예제

``` js
var str = document.getElementById("text");
str.innerHTML = "이 문장으로 바뀌었습니다!";
```



HTML 요소의 속성 이름을 이용하면 속성값도 변경할 수 있습니다.

##### 예제

``` js
var link = document.getElementById("link");          // 아이디가 "link"인 요소를 선택함.
link.href = "/javascript/intro"; // 해당 요소의 href 속성값을 변경함.
link.innerHTML = "자바스크립트 수업 바로 가기!";     // 해당 요소의 내용을 변경함.
```



#### DOM 요소의 스타일 변경

HTML DOM을 이용하면 HTML 요소의 스타일(style)도 손쉽게 변경할 수 있습니다.

 

style 프로퍼티를 이용하여 HTML 요소에 CSS 스타일을 적용합니다.

##### 예제

``` js
var str = document.getElementById("text");                 // 아이디가 "str"인 요소를 선택함.
function changeRedColor() { str.style.color = "red"; }     // 해당 요소의 글자색을 빨간색으로 변경함.
function changeBlackColor() { str.style.color = "black"; } // 해당 요소의 글자색을 검정색으로 변경함.
```



### 노드

------

#### 노드(node)

HTML DOM은 노드(node)라고 불리는 계층적 단위에 정보를 저장하고 있습니다.

HTML DOM은 이러한 노드들을 정의하고, 그들 사이의 관계를 설명해 주는 역할을 합니다.

 ![](./images/node.png)



HTML 문서의 정보는 노드 트리(node tree)라고 불리는 계층적 구조에 저장됩니다.

이러한 노드 트리는 노드들의 집합이며, 노드 간의 관계를 보여줍니다.

 

노드 트리는 최상위 레벨인 루트 노드(root node)로부터 시작하여, 가장 낮은 레벨인 텍스트 노드까지 뻗어 내려갑니다.

자바스크립트에서는 HTML DOM을 이용하여 노드 트리에 포함된 모든 노드에 접근할 수 있습니다.

------

#### 노드의 종류

W3C HTML DOM 표준에 따르면, HTML 문서의 모든 것은 노드입니다.

 

HTML 문서를 구성하는 대표적인 노드의 종류는 다음과 같습니다.

|           노드            |                             설명                             |
| :-----------------------: | :----------------------------------------------------------: |
| 문서 노드(document node)  |              HTML 문서 전체를 나타내는 노드임.               |
|  요소 노드(element node)  | 모든 HTML 요소는 요소 노드이며, 속성 노드를 가질 수 있는 유일한 노드임. |
| 속성 노드(attribute node) | 모든 HTML 요소의 속성은 속성 노드이며, 요소 노드에 관한 정보를 가지고 있음.하지만 해당 요소 노드의 자식 노드(child node)에는 포함되지 않음. |
|  텍스트 노드(text node)   |           HTML 문서의 모든 텍스트는 텍스트 노드임.           |
|  주석 노드(comment node)  |             HTML 문서의 모든 주석은 주석 노드임.             |

------

#### 노드 간의 관계

노드 트리의 모든 노드는 서로 계층적 관계를 맺고 있습니다.

![](./images/img_js_node_relationship.png)



노드 트리의 가장 상위에는 단 하나의 루트 노드(root node)가 존재합니다.

 루트 노드를 제외한 모든 노드는 단 하나의 부모 노드(parent node)만을 가집니다.

모든 요소 노드는 자식 노드(child node)를 가질 수 있습니다.

 형제 노드(sibling node)란 같은 부모 노드를 가지는 모든 노드를 가리킵니다.

조상 노드(ancestor node)란 부모 노드를 포함해 계층적으로 현재 노드보다 상위에 존재하는 모든 노드를 가리킵니다.

자손 노드(descendant node)란 자식 노드를 포함해 계층적으로 현재 노드보다 하위에 존재하는 모든 노드를 가리킵니다.



### 노드로의 접근

------

#### 노드로의 접근

HTML 문서에서 HTML DOM 노드에 접근하는 방법은 다음과 같습니다.

- 1. getElementsByTagName() 메소드를 이용하는 방법

- 2. 노드 간의 관계를 이용하여 접근하는 방법

------

#### getElementsByTagName() 메소드를 이용하는 방법

getElementsByTagName() 메소드는 특정 태그 이름을 가지는 모든 요소를 노드 리스트의 형태로 반환합니다.

따라서 이 메소드가 반환하는 노드 리스트를 이용하면 원하는 노드에 접근할 수 있습니다.

이 메소드에 대한 더 자세한 사항은 자바스크립트 DOM 요소 수업에서 확인할 수 있습니다.

[자바스크립트 DOM 요소 수업 확인 =>](http://tcpschool.com/javascript/js_dom_element)

------

#### 노드 간의 관계를 이용하여 접근하는 방법

HTML DOM에서 노드 간의 관계는 다음과 같은 프로퍼티로 정의됩니다.

- 1. parentNode : 부모 노드

- 2. childNodes : 자식 노드 리스트

- 3. firstChild : 첫 번째 자식 노드

- 4. lastChild : 마지막 자식 노드

- 5. nextSibling : 다음 형제 노드

- 6. previousSibling : 이전 형제 노드

 

위와 같은 프로퍼티를 이용하여 원하는 노드에 손쉽게 접근할 수 있습니다.

------

#### 노드에 대한 정보

노드에 대한 정보는 다음과 같은 프로퍼티를 통해 접근할 수 있습니다.

- 1. nodeName

- 2. nodeValue

- 3. nodeType

 

이 프로퍼티들은 특별히 다른 인터페이스를 이용하지 않고도, 해당 노드의 정보에 직접 접근할 수 있는 방법을 제공합니다.

------

#### nodeName

nodeName 프로퍼티는 노드 고유의 이름을 저장하므로, 수정할 수 없는 읽기 전용 프로퍼티입니다.

 

요소 노드의 nodeName 프로퍼티는 언제나 해당 HTML 요소의 태그 이름을 대문자로 저장합니다.

|           노드            |         프로퍼티 값         |
| :-----------------------: | :-------------------------: |
| 문서 노드(document node)  |          #document          |
|  요소 노드(element node)  | 태그 이름 (영문자로 대문자) |
| 속성 노드(attribute node) |          속성 이름          |
|  텍스트 노드(text node)   |            #text            |

 

##### 예제

``` js
// HTML 문서의 모든 자식 노드 중에서 두 번째 노드의 이름을 선택함.
document.getElementById("document").innerHTML = document.childNodes[1].nodeName;           // HTML
 
// html 노드의 모든 자식 노드 중에서 첫 번째 노드의 이름을 선택함.
document.getElementById("html").innerHTML = document.childNodes[1].childNodes[0].nodeName; // HEAD
```

위의 예제에서 HTML 문서의 첫 번째 자식 노드는 <!DOCTYPE html>이며, 두 번째 자식 노드는 <html>입니다.

또한, <html>노드의 첫 번째 자식 노드는 <head>이며, 세 번째 자식 노드는 <body>입니다.

------

#### nodeValue

nodeValue 프로퍼티는 노드의 값을 저장합니다.

|           노드            |    프로퍼티 값     |
| :-----------------------: | :----------------: |
|  요소 노드(element node)  |     undefined      |
| 속성 노드(attribute node) | 해당 속성의 속성값 |
|  텍스트 노드(text node)   | 해당 텍스트 문자열 |

##### 예제

``` js
// 아이디가 "heading"인 요소의 첫 번째 자식 노드의 노드값을 선택함.
var headingText = document.getElementById("heading").firstChild.nodeValue;
 
document.getElementById("text1").innerHTML = headingText;
document.getElementById("text1").firstChild.nodeValue = headingText;
```

지금까지 innerHTML 프로퍼티를 사용하여 손쉽게 HTML 요소의 내용(content)에 접근하거나, 내용을 변경할 수 있었습니다.

이러한 innerHTML 프로퍼티 대신에 firstChild.nodeValue를 사용해도 같은 결과를 얻을 수 있습니다.

------

#### nodeType

nodeType 프로퍼티는 노드 고유의 타입을 저장하므로, 수정할 수 없는 읽기 전용 프로퍼티입니다.

 

대표적인 HTML 노드별 nodeType 프로퍼티 값은 다음과 같습니다.

|           노드            | 프로퍼티 값 |
| :-----------------------: | :---------: |
|  요소 노드(element node)  |      1      |
| 속성 노드(attribute node) |      2      |
|  텍스트 노드(text node)   |      3      |
|  주석 노드(comment node)  |      8      |
| 문서 노드(document node)  |      9      |

##### 예제

``` js
// 아이디가 "heading"인 요소의 첫 번째 자식 노드의 타입을 선택함.
var headingType = document.getElementById("heading").firstChild.nodeType;
 
document.getElementById("head").innerHTML = headingType;           // 3
document.getElementById("document").innerHTML = document.nodeType; // 9
```



#### 빈 텍스트 노드의 처리

현재 대부분의 주요 웹 브라우저는 모두 W3C DOM 모델을 지원하지만, 그 처리 방식에 있어 약간씩의 차이가 있습니다.

그중에서도 가장 큰 차이점은 띄어쓰기와 줄 바꿈을 처리하는 방식입니다.

 

파이어폭스나 기타 브라우저들은 띄어쓰기나 줄 바꿈을 텍스트 노드(text node)로 취급합니다.

하지만 익스플로러는 띄어쓰기나 줄 바꿈을 텍스트 노드로 취급하지 않습니다.

 

따라서 자식 노드나 형제 노드를 이용하여 원하는 노드에 접근하려고 하면 브라우저 간에 차이가 발생하게 됩니다.

이 차이를 없애는 가장 손쉬운 방법은 nodeType 프로퍼티를 사용하여 선택된 요소의 타입을 검사하는 것입니다.

##### 예제

``` js
var lastNode;

function findLastChild(parentNode) {
    lastNode = parentNode.lastChild;
    while (lastNode.nodeType != 1) {
        lastNode = lastNode.previousSibling;
    }
}

function printLastChild() {
    var parentNode = document.getElementById("parentNode");
    findLastChild(parentNode);
    document.getElementById("nodename").innerHTML = lastNode.nodeName;
}
```



위의 예제에서 마지막 자식 노드를 찾은 후에, 찾은 노드의 타입이 요소 노드가 아니면 그 앞의 노드를 다시 검사합니다.

이 방식을 사용하면 모든 브라우저에서 마지막 자식 노드로 같은 요소 노드를 선택할 수 있게 됩니다.



### 노드 리스트

------

#### 노드 리스트(node list)

노드 리스트는 getElementsByTagName() 메소드나 childNodes 프로퍼티의 값으로 반환되는 객체입니다.

이 객체는 HTML 문서와 같은 순서로 문서 내의 모든 노드를 리스트 형태로 저장하고 있습니다.

 

리스트의 각 노드는 0부터 시작하는 인덱스를 이용하여 접근할 수 있습니다.

![](./images/img_js_node_list.png)

##### 예제

``` js
// 아이디가 "list"인 요소의 모든 자식 노드들을 선택함.
var listItems = document.getElementById("list").childNodes;

// 자식 노드들 중 첫 번째 li 요소의 내용을 변경함.
listItems[1].firstChild.nodeValue = "HTML 요소의 내용을 변경했어요!";
```



위의 예제에서 자식 노드 중 첫 번째 <li>요소를 선택할 때 인덱스로 0이 아닌 1을 사용합니다.

그 이유는 HTML DOM에서 각 요소 노드 다음에는 별도의 텍스트 노드가 존재하기 때문입니다.

따라서 아이디가 "list"인 요소의 자식 노드 리스트의 첫 번째 노드에는 아이디가 "list"인 요소 다음에 존재하는 텍스트 노드가 저장됩니다.

 

다음 예제는 이러한 요소 노드 사이에 존재하는 텍스트 노드를 확인하는 예제입니다.

##### 예제

``` js
// 아이디가 "list"인 요소의 모든 자식 노드들을 선택함.
var listItems = document.getElementById("list").childNodes;
 
// 자식 노드들 중 첫 번째 노드의 값을 출력함.
document.write(listItems[0].nodeValue + "<br>");

// 자식 노드들 중 두 번째 노드의 자식 노드 중 첫 번째 노드의 값을 출력함.
document.write(listItems[1].firstChild.nodeValue + "<br>");

// 자식 노드들 중 세 번째 노드의 값을 출력함.
document.write(listItems[2].nodeValue);
```



위의 예제는 각 요소 노드 다음에 또 다른 텍스트 노드가 존재함을 보여줍니다.

따라서 노드 리스트에 인덱스를 이용하여 접근할 때는 이러한 텍스트 노드의 존재를 반드시 염두에 두어야 합니다.

 

노드 리스트 객체는 리스트에 노드를 추가하거나 삭제되면 자신의 상태 정보를 스스로 갱신합니다.

따라서 이 객체의 length 프로퍼티 값은 언제나 노드 리스트가 저장하고 있는 노드들의 총 개수를 나타냅니다.

##### 예제

``` js
var listItems = document.getElementsByTagName("li");              // 모든 <li> 요소들을 선택함.

document.getElementById("text").innerHTML = 
"이 노드 리스트의 길이는 " + listItems.length + "개 입니다.<br>"; // 모든 자식 노드들의 개수를 반환함.

function changeTextColor() {
    for (var i = 0; i < listItems.length; i++) {
        listItems[i].style.color = "orange";                      // 모든 자식 노드들의 글자색을 변경함.
    }
}
```



### 노드의 관리

------

#### 노드의 추가

다음 메소드를 사용하면 특정 위치에 새로운 노드를 추가할 수 있습니다.

- 1. appendChild()

- 2. insertBefore()

- 3. insertData()

------

#### appendChild() 메소드

appendChild() 메소드는 새로운 노드를 해당 노드의 자식 노드 리스트(child node list)의 맨 마지막에 추가합니다.

##### 예제

``` js
function appendNode() {
    var parent = document.getElementById("list");  // 아이디가 "list"인 요소를 선택함.
    var newItem = document.getElementById("item"); // 아이디가 "item"인 요소를 선택함.
    parent.appendChild(newItem);                   // 해당 요소의 맨 마지막 자식 노드로 추가함.
}
```



#### insertBefore() 메소드

insertBefore() 메소드는 새로운 노드를 특정 자식 노드 바로 앞에 추가합니다.

 

insertBefore() 메소드의 원형은 다음과 같습니다.

##### 원형

``` js
부모노드.insertBefore(새로운자식노드, 기준자식노드);
```

 

- 1. 새로운 자식 노드 : 자식 노드 리스트(child node list)에 새롭게 추가할 자식 노드를 전달합니다.

- 2. 기준 자식 노드 : 새로운 노드를 삽입할 때 기준이 되는 노드로, 이 노드 바로 앞에 새로운 노드가 추가됩니다.

##### 예제

``` js
function appendNode() {
    var parent = document.getElementById("list");           // 아이디가 "list"인 요소를 선택함.
    var criteriaItem = document.getElementById("criteria"); // 아이디가 "criteria"인 요소를 선택함.
    var newItem = document.getElementById("item");          // 아이디가 "item"인 요소를 선택함.
    parent.insertBefore(newItem, criteriaItem); // 해당 노드를 기준이 되는 자식 노드의 바로 앞에 추가함.
}
```

> 기준 자식 노드에 null 값을 전달하면 새로운 노드는 자식 노드 리스트의 맨 마지막 노드로 추가됩니다.
>
> 즉, appendChild() 메소드와 완전히 같은 동작을 하게 됩니다.



#### insertData() 메소드

insertData() 메소드는 텍스트 노드의 텍스트 데이터에 새로운 텍스트를 추가합니다.

 

insertData() 메소드의 원형은 다음과 같습니다.

##### 원형

``` js
텍스트노드.insertData(오프셋, 새로운데이터);
```

- 1. 오프셋(offset) : 오프셋 값은 0부터 시작하며, 기존 텍스트 데이터의 몇 번째 위치부터 추가할지를 전달합니다.

- 2. 새로운 데이터 : 새로이 삽입할 텍스트 데이터를 전달합니다.

##### 예제

``` js
var text = document.getElementById("text").firstChild; // 아이디가 "text"인 요소의 텍스트 노드를 선택함.

function appendText() {
    text.insertData(6, " 나른한 "); // 텍스트 노드의 6번째 문자부터 " 나른한 "이란 텍스트를 추가함.
}
```



#### 노드의 생성

생성할 노드의 종류에 따라 다음과 같은 메소드를 사용할 수 있습니다.

- 1. createElement()

- 2. createAttribute()

- 3. createTextNode()

------

#### 요소 노드의 생성

createElement() 메소드를 사용하여 새로운 요소 노드를 만들 수 있습니다.

##### 예제

``` js
function createNode() {
    var criteriaNode = document.getElementById("text"); // 기준이 되는 요소로 아이디가 "text"인 요소를 선택함.
    var newNode = document.createElement("p");          // 새로운 <p> 요소를 생성함.
    newNode.innerHTML = "새로운 단락입니다.";
    document.body.insertBefore(newNode, criteriaNode);  // 새로운 요소를 기준이 되는 요소 바로 앞에 추가함.
}
```

#### 속성 노드의 생성

createAttribute() 메소드를 사용하여 새로운 속성 노드를 만들 수 있습니다.

 

만약 같은 이름의 속성 노드가 이미 존재하면, 기존의 속성 노드는 새로운 속성 노드로 대체됩니다.

이미 존재하는 요소 노드에 속성 노드를 생성하고자 할 때에는 setAttribute() 메소드를 사용할 수 있습니다.

##### 예제

``` js
function createNode() {
    var text = document.getElementById("text");           // 아이디가 "text"인 요소를 선택함.
    var newAttribute = document.createAttribute("style"); // 새로운 style 속성 노드를 생성함.
    newAttribute.value = "color:red";
    text.setAttributeNode(newAttribute);                  // 해당 요소의 속성 노드로 추가함.
}
```



#### 텍스트 노드의 생성

createTextNode() 메소드를 사용하여 새로운 텍스트 노드를 만들 수 있습니다.

##### 예제

``` js
function createNode() {
    var elementNode = document.getElementById("text");           // 아이디가 "text"인 요소를 선택함.
    var newText = document.createTextNode("새로운 텍스트에요!"); // 새로운 텍스트 노드를 생성함.
    elementNode.appendChild(newText);                            // 해당 요소의 자식 노드로 추가함.
}
```



#### 노드의 제거

다음 메소드를 사용하면 특정 노드를 제거할 수 있습니다. 

- 1. removeChild()

- 2. removeAttribute()

------

#### removeChild() 메소드

removeChild() 메소드는 자식 노드 리스트에서 특정 자식 노드를 제거합니다.

 

이 메소드는 성공적으로 노드가 제거되면 제거된 노드를 반환합니다.

노드가 제거될 때에는 제거되는 노드의 모든 자식 노드들도 다 같이 제거됩니다.

##### 예제

``` js
var parent = document.getElementById("list");      // 아이디가 "list"인 요소를 선택함.
var removedItem = document.getElementById("item"); // 아이디가 "item"인 요소를 선택함.
parent.removeChild(removedItem);                   // 지정된 요소를 삭제함.
```



#### removeAttribute() 메소드

removeAttribute() 메소드는 속성의 이름을 이용하여 특정 속성 노드를 제거합니다.

##### 예제

``` js
var text = document.getElementById("text"); // 아이디가 "text"인 요소를 선택함.

text.removeAttribute("style");              // 해당 요소의 "style" 속성을 제거함.
```



#### 노드의 복제

cloneNode() 메소드를 사용하면 특정 노드를 복제할 수 있습니다.

------

#### cloneNode() 메소드

cloneNode() 메소드는 기존의 존재하는 노드와 똑같은 새로운 노드를 생성하여 반환합니다.

 

cloneNode() 메소드의 원형은 다음과 같습니다.

##### 원형

``` js
복제할노드.cloneNode(자식노드복제여부);
```

\- 자식 노드 복제 여부 : 전달된 값이 true이면 복제되는 노드의 모든 속성 노드와 자식 노드도 같이 복제하며, false이면 속성 노드만 복제하고 자식 노드는 복제하지 않습니다.

 

##### 예제

``` js
function cloneElement() {
    var parent = document.getElementById("list");     // 아이디가 "list"인 요소를 선택함.
    var originItem = document.getElementById("item"); // 아이디가 "item"인 요소를 선택함.
    parent.appendChild(originItem.cloneNode(true));   // 해당 노드를 복제하여 리스트의 맨 마지막에 추가함.
}
```



### 노드의 조작

------

#### 노드의 값 변경

nodeValue 프로퍼티를 사용하면 특정 노드의 값을 변경할 수 있습니다.

또한, setAttribute() 메소드는 속성 노드의 속성값을 변경할 수 있게 해줍니다.

------

#### 요소 노드의 텍스트

요소 노드는 자신이 직접 텍스트값을 가지지는 않습니다.

요소 노드의 텍스트는 요소 노드의 자식 노드인 텍스트 노드(text node)에 저장됩니다. 

따라서 요소 노드의 텍스트 값을 확인하거나 변경하고자 할 때는 요소 노드에 포함된 텍스트 노드에 접근해야 합니다.

------

#### 텍스트 노드의 값 변경

nodeValue 프로퍼티를 사용하여 텍스트 노드의 값을 변경할 수 있습니다.

##### 예제

``` js
var para = document.getElementById("text"); // 아이디가 "text"인 요소를 선택함.

function changeText() {
    para.firstChild.nodeValue = "텍스트 변경 완료!";
}
```



#### 속성 노드의 값 변경

속성 노드는 nodeValue 프로퍼티뿐만 아니라 setAttribute() 메소드를 사용하여 값을 변경할 수 있습니다.

setAttribute() 메소드는 속성값을 변경하려는 속성이 존재하지 않으면, 먼저 해당 속성을 생성한 후에 속성값을 설정합니다.

##### 예제

``` js
var para;

function changeAttribute() {
    // 모든 <p> 요소중에서 첫 번째 요소에 클래스 속성값으로 "para"를 설정함.
    document.getElementsByTagName("p")[0].setAttribute("class", "para");
    // 클래스가 설정되면 해당 클래스에 설정되어 있던 스타일이 자동으로 적용됨.
}
```



#### 요소 노드의 교체

replaceChild() 메소드를 사용하면 기존의 요소 노드를 새로운 요소 노드로 교체할 수 있습니다.

 

replaceChild() 메소드의 원형은 다음과 같습니다.

##### 원형

``` js
교체할노드 = 부모노드.replaceChild(새로운자식노드, 기존자식노드);
```

- 1. 새로운 자식 노드 : 자식 노드 리스트에 새롭게 추가할 요소 노드를 전달합니다.

- 2. 기존 자식 노드 : 자식 노드 리스트에서 제거할 요소 노드를 전달합니다.

 

다음 예제는 자식 노드 중에서 첫 번째 요소를 삭제하고, 그 대신에 세 번째 요소를 첫 번째 요소 자리에 삽입하는 예제입니다.

##### 예제

``` js
var parent = document.getElementById("parent"); // 부모 노드를 선택함.
var first = document.getElementById("first");
var third = document.getElementById("third");

function changeNode() {
    parent.replaceChild(third, first);          // first 요소를 삭제하고, 그 대신 third 요소를 삽입함.
}
```



#### 텍스트 노드의 데이터 교체

replaceData() 메소드를 사용하면 텍스트 노드의 텍스트 데이터를 바꿀 수 있습니다.

 

replaceData() 메소드의 원형은 다음과 같습니다.

##### 원형

``` js
텍스트노드.replaceData(오프셋, 교체할문자수, 새로운데이터);
```

- 1. 오프셋(offset) : 오프셋 값은 0부터 시작하며, 기존 텍스트 데이터의 몇 번째 문자부터 교체할지를 전달합니다.

- 2. 교체할 문자 수 : 기존 텍스트 노드의 데이터로부터 교체할 총 문자 수를 전달합니다.

- 3. 새로운 데이터 : 새로이 삽입할 텍스트 데이터를 전달합니다.



##### 예제

``` js
var text = document.getElementById("text").firstChild; // 아이디가 "text"인 요소의 텍스트 노드를 선택함.

function changeText() {
    text.replaceData(7, 4, "저녁 7"); // 텍스트 노드의 7번째 문자부터 4개의 문자를 "저녁 7"로 교체함.
}
```



