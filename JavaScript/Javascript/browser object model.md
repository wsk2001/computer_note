# 브라우저 객체 모델

### Window 객체

------

#### 브라우저 객체 모델(BOM)이란?

자바스크립트를 이용하면 브라우저의 정보에 접근하거나 브라우저의 여러 기능들을 제어할 수 있습니다.

이때 사용할 수 있는 객체 모델이 바로 브라우저 객체 모델(BOM, Browser Object Model)입니다.

 

브라우저 객체 모델(BOM)은 문서 객체 모델(DOM)과는 달리 W3C의 표준 객체 모델은 아닙니다.

하지만 이 모델은 자바스크립트가 브라우저의 기능적인 요소들을 직접 제어하고 관리할 방법을 제공해 줍니다.

자바스크립트에서는 이러한 BOM 모델의 객체들을 전역 객체(global object)로 사용할 수 있습니다.

------

#### Window 객체

window 객체는 웹 브라우저의 창(window)을 나타내는 객체로, 대부분의 웹 브라우저에서 지원하고 있습니다.

 

자바스크립트의 모든 객체, 전역 함수, 전역 변수들은 자동으로 window 객체의 프로퍼티가 됩니다.

window 객체의 메소드는 전역 함수이며, window 객체의 프로퍼티는 전역 변수가 됩니다.

문서 객체 모델(DOM)의 요소들도 모두 window 객체의 프로퍼티가 됩니다.

------

#### 브라우저 창 크기 조절

window 객체의 innerHeight와 innerWidth 프로퍼티를 이용하면, 브라우저의 창 크기를 설정할 수 있습니다.

여기서 브라우저 창이란 웹 브라우저의 뷰포트(viewport)를 의미하며, 브라우저의 툴바나 스크롤 바는 포함되지 않습니다.

##### 문법

``` js
// 기본 문법
window.innerHeight
window.innerWidth

// 익스플로러 5부터 7버전만을 위한 문법 1
document.documentElement.clientHeight
document.documentElement.clientWidth

// 익스플로러 5부터 7버전만을 위한 문법 2
document.body.clientHeight
document.body.clientWidth
```

다음 예제는 위의 문법들을 이용하여 모든 브라우저에서 창의 크기를 반환하는 예제입니다.

##### 예제

``` js
var windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

var windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

document.write("웹 브라우저의 너비는 " + windowWidth + "픽셀이고, 높이는 " + windowHeight + "픽셀입니다.");
```

window 객체의 모든 메소드나 프로퍼티를 사용할 때는 window라는 접두사를 생략할 수 있습니다.

따라서 위의 예제에서 window.innerWidth 대신 그냥 innerWidth를 사용해도 정상적으로 동작합니다.

 

다음 예제는 window 접두사를 생략할 수 있는 다양한 예를 보여줍니다.

##### 예제

``` js
alert("전역 함수 호출시 window 접두사 생략 가능함!");                     // 전역 함수
document.write("현재 브라우저의 수평 위치는 " + screenX + "입니다.<br>"); // 전역 변수
document.write("현재 브라우저의 수직 위치는 " + screenY + "입니다.<br>"); // 전역 변수
document.write(document.title);                                           // 전역 객체
```

위의 예제처럼 자바스크립트의 모든 전역 객체, 전역 함수, 전역 변수를 사용할 때는 window 접두사를 생략할 수 있습니다.

> screenX는 해당 브라우저 창의 왼쪽 모서리와 사용자 스크린의 왼쪽 모서리 사이의 거리를 반환합니다.
> 또한, screenY는 해당 브라우저 창의 위쪽 모서리와 사용자 스크린의 위쪽 모서리 사이의 거리를 반환합니다.

#### 브라우저 새 창 열기

window 객체의 open() 메소드를 이용하면, 새로운 브라우저 창을 열 수 있습니다.

또한, 새로운 브라우저 창의 세부적인 옵션들도 일일이 설정할 수 있습니다.

 

이 옵션에서 사용할 수 있는 프로퍼티는 다음 그림과 같습니다.

 

![strWindowFeatures](http://tcpschool.com/lectures/img_js_browser_window.png)

 

다음 예제는 메뉴바, 주소창, 크기조절 손잡이, 스크롤 바, 상태 바만을 가지는 새로운 브라우저 창을 여는 예제입니다.

##### 예제

``` js
var newWindowObj;
// 변수 strWindowFeatures를 통해 새롭게 여는 브라우저 창의 옵션들을 일일이 설정할 수 있음.

var strWindowFeatures = "menubar = yes,location = yes,resizable = yes,scrollbars = yes,status = yes";

function openWindow() {
    newWindowObj = window.open("/html/intro", "HTML 개요", strWindowFeatures);
}
```



#### 브라우저 창 닫기

window 객체의 close() 메소드를 이용하면, 현재 브라우저나 특정 브라우저 창을 닫을 수 있습니다.

##### 예제

``` js
function openWindow() {
    newWindowObj = window.open("/html/intro", "HTML 개요", strWindowFeatures);
}

function closeNewWindow() { // 새롭게 연 브라우저 창을 window 객체를 이용하여 다시 닫을 수 있음.
    newWindowObj.close();
}
```



#### Document 객체

window 객체의 가장 중요한 프로퍼티 중 하나가 바로 document 객체입니다.

document 객체는 브라우저 창에 표시되는 내용에 해당하는 문서(document)를 나타내는 객체입니다.

 document 객체에 대한 더 자세한 사항은 자바스크립트 Document 수업에서 확인할 수 있습니다.

 [자바스크립트 문서 객체 모델(DOM) 수업 확인 =>](http://tcpschool.com/javascript/js_dom_concept)



### Location 객체

------

#### Location 객체

location 객체는 현재 브라우저에 표시된 HTML 문서의 주소를 얻거나, 브라우저에 새 문서를 불러올 때 사용할 수 있습니다.

 

이 객체는 Window 객체의 location 프로퍼티와 Document 객체의 location 프로퍼티에 같이 연결되어 있습니다.

location 객체의 프로퍼티와 메소드를 이용하면, 현재 문서의 URL 주소를 다양하게 해석하여 처리할 수 있습니다.

------

#### 현재 문서의 URL 주소

location 객체의 href 프로퍼티는 현재 문서의 전체 URL 주소를 문자열로 반환합니다.

##### 예제

``` js
document.write("현재 문서의 주소는 " + location.href + "입니다.");
```



#### 현재 문서의 호스트 이름

location 객체의 hostname 프로퍼티는 현재 문서의 인터넷 호스트 이름을 반환합니다.

##### 예제

``` js
document.write("현재 문서의 호스트 이름은 " + location.hostname + "입니다.");
```

> host, hostname, port, hash와 같은 location 객체의 주요 프로퍼티는 URL 주소의 다양한 특성을 저장하고 있습니다.
> 이와 같은 프로퍼티는 Link 객체를 통해서도 제공됩니다.



#### 현재 문서의 파일 경로명

location 객체의 pathname 프로퍼티는 현재 문서의 파일 경로명을 반환합니다.

##### 예제

``` js
document.write("현재 문서의 파일 경로명은 " + location.pathname + "입니다.");
```



>  호스트 이름(host name)과 파일 경로명(path name)을 합쳐 URL(Uniform Resource Locator)이라고 부릅니다.
>
> 이러한 URL은 브라우저가 웹 서버로 컨텐츠를 요청할 때, 해당 컨텐츠가 어디에 있는지를 알려주기 위한 규약입니다.



#### 현재 창에 문서 불러오기

location 객체의 assign() 메소드는 브라우저 창에 지정된 URL 주소에 존재하는 문서를 불러옵니다.

반면에 replace() 메소드는 새 문서를 불러오기 전에, 현재 문서를 브라우저의 히스토리에서 제거한다는 점이 assign() 메소드와 다릅니다.

location 객체의 reload() 메소드는 브라우저 창에 현재 문서를 다시 불러옵니다.

##### 예제

``` ja
function openDocument() {
    location.assign("/index.php");
}

function openDocumentWithReplace() {
    location.replace("/index.php");
}
```

> 현재 문서를 브라우저의 히스토리에서 제거한다는 의미는 브라우저의 뒤로 가기 버튼을 눌러도 이전 페이지로 다시 돌아갈 수 없다는 의미입니다.



### History 객체

------

#### History 객체

history 객체는 브라우저의 히스토리 정보를 문서와 문서 상태 목록으로 저장하는 객체입니다.

자바스크립트는 사용자의 개인 정보를 보호하기 위해 이 객체에 접근하는 방법을 일부 제한하고 있습니다.

------

#### 히스토리 목록의 개수

history 객체의 length 프로퍼티는 브라우저 히스토리 목록의 개수를 반환합니다. 

##### 예제

``` js
function openDocument() {
    location.assign("/javascript/js_bom_history");
}

document.getElementById("text").innerHTML =
"현재 브라우저의 히스토리 목록의 개수는 " + history.length + "개 입니다.";
```



#### 히스토리 목록 접근하기

history 객체에는 브라우저의 뒤로 가기와 앞으로 가기 버튼과 같은 동작을 하는 back()과 forward() 메소드를 가지고 있습니다.

또한, go() 메소드를 이용하면 인수로 전달받는 정수만큼 히스토리 목록 사이를 이동할 수도 있습니다.

 

다음 예제는 back() 메소드를 이용하여 브라우저의 히스토리 목록에서 바로 이전 URL로 이동하는 예제입니다.

이 버튼은 브라우저의 이전 페이지로 가기 버튼과 같은 동작을 합니다.

##### 예제

``` js
<button onclick="goBack()">이전 페이지로 가기</button>
...
<script>
    function goBack() {
        window.history.back();
    }
</script>
```



다음 예제는 go() 메소드를 이용하여 back() 메소드와 같은 동작을 하도록 만든 예제입니다.

##### 예제

``` js
<button onclick="go()">이전 페이지로 가기</button>
...
<script>
    function go() {
        window.history.go(-1);
    }
</script>
```



위의 예제에서처럼 go() 메소드에 인수로 -1을 전달하면 back() 메소드와 같은 동작을 하게 됩니다.

 

다음 예제는 forward() 메소드를 이용하여 브라우저의 히스토리 목록에서 바로 다음 URL로 이동하는 예제입니다.

이 버튼은 브라우저의 다음 페이지로 가기 버튼과 같은 동작을 합니다.

##### 예제

``` js
<button onclick="goForward()">다음 페이지로 가기</button>
...
<script>
    function goForward() {
        window.history.forward();
    }
</script>
```



### Screen 객체

------

#### Screen 객체

screen 객체는 사용자의 디스플레이 화면에 대한 다양한 정보를 저장하는 객체입니다.

------

#### 사용자의 화면 크기

screen 객체의 width와 height 프로퍼티는 사용자의 디스플레이 화면의 크기를 픽셀 단위로 반환합니다.

##### 예제

``` JS
document.write("현재 사용자의 디스플레이 화면의 너비는 " + screen.width + "픽셀입니다.<br>");
document.write("현재 사용자의 디스플레이 화면의 높이는 " + screen.height + "픽셀입니다.<br>");
 
document.write("현재 브라우저 창의 너비는 " + window.outerWidth + "픽셀입니다.<br>");
document.write("현재 브라우저 창의 높이는 " + window.outerHeight + "픽셀입니다.<br>");
```

> screen.width와 screen.height는 현재 사용자의 모니터 화면의 크기를 반환합니다.
> 하지만 window.outerWidth와 window.outerHeight는 현재 브라우저 창의 크기를 반환합니다.



#### 실제 사용할 수 있는 화면 크기

screen 객체의 availWidth와 availHeight 프로퍼티는 실제 사용할 수 있는 화면의 크기를 픽셀 단위로 반환합니다.

이 프로퍼티는 운영체제의 작업 표시줄과 같은 공간을 모두 제외한 크기를 반환합니다.

##### 예제

``` JS
document.write("실제 사용할 수 있는 화면의 너비는 " + screen.availWidth + "픽셀입니다.<br>");
document.write("실제 사용할 수 있는 화면의 높이는 " + screen.availHeight + "픽셀입니다."); 
```



#### 한 색상당 사용할 수 있는 비트수

screen 객체의 colorDepth 프로퍼티는 사용자 화면에서 한 색상당 사용할 수 있는 비트 수를 반환합니다.

대부분의 컴퓨터는 24비트의 트루 컬러(true colors)나 30/36/48비트의 디프 컬러(deep colors)를 사용합니다.

##### 예제

``` JS
var bitColorDepth = screen.colorDepth;
document.write("사용자 화면에서 한 색상당 사용할 수 있는 비트수는 " + bitColorDepth + "개입니다.<br>");
document.write("즉, 한 색상은 총 " + Math.pow(2, bitColorDepth) + "가지로 표현됩니다.");
```

트루 컬러에서 한 색상당 사용할 수 있는 비트 수는 2<sup>24</sup> = 16,777,216 입니다.



#### 화면 픽셀당 표시할 수 있는 비트수

screen 객체의 pixelDepth 프로퍼티는 사용자 화면에서 픽셀당 표시할 수 있는 비트 수를 반환합니다.

##### 예제

``` js
var bitPixelDepth = screen.pixelDepth;
document.write("사용자 화면의 한 픽셀당 표시할 수 있는 비트수는 " + bitPixelDepth + "개입니다.<br>");
```

> 대부분의 컴퓨터에서 colorDepth와 pixelDepth는 같은 값을 가집니다.



### Navigator 객체

------

#### Navigator 객체

navigator 객체는 브라우저 공급자 및 버전 정보 등을 포함한 브라우저에 대한 다양한 정보를 저장하는 객체입니다.

이 객체의 이름은 넷스케이프(Netscape)의 초기 웹 브라우저였던 네비게이터(Navigator)에서 유래되었습니다.

------

#### 브라우저 스니핑(browser sniffing)

과거에는 방문자의 웹 브라우저의 종류를 미리 파악하여 조치함으로써, 브라우저 간의 호환성을 유지하였습니다.

이렇게 호환성을 유지하는 방법을 브라우저 스니핑(browser sniffing)이라고 합니다.

 

navigator 객체는 이러한 브라우저 스니핑에서 사용할 수 있는 다양한 표준 프로퍼티 및 비표준 프로퍼티를 제공합니다.

하지만 현재는 이 방법보다 필요한 프로퍼티만을 간단하게 테스트하는 기능 테스팅 방법을 더 많이 사용합니다.

------

#### 현재 브라우저의 이름

navigator 객체의 appName과 appCodeName 프로퍼티는 현재 사용하고 있는 브라우저의 전체 이름을 반환합니다.

하지만 브라우저 간의 호환성을 위해 스니핑 코드로 대부분의 브라우저가 브라우저 이름을 "Netscape"로 사용합니다.

또한, 대부분의 브라우저가 브러우저 코드명을 "Mozilla"로 사용합니다.

##### 예제

``` js
document.write("현재 사용 중인 브라우저의 이름은 " + navigator.appName + "입니다.<br>");
document.write("또한, 해당 브라우저의 코드명은 " + navigator.appCodeName + "입니다.");
```

익스플로러 11 버전, 크롬, 파이어폭스와 사파리는 모두 브라우저의 이름을 "Netscape"로 사용합니다.

익스플로러 10 이하 버전, 크롬, 파이어폭스, 사파리와 오페라 모두 브라우저 코드명을 "Mozilla"로 사용합니다.

> 이 프로퍼티는 웹 표준에서 제외되었으므로, 될 수 있으면 사용하지 않는 것이 좋습니다.



#### 현재 브라우저의 버전

navigator 객체의 appVersion과 userAgent 프로퍼티는 현재 사용하고 있는 브라우저의 버전 정보를 문자열로 반환합니다.

 

이 프로퍼티의 결과로 반환되는 문자열에 대한 표준 형식은 따로 명시되어 있지 않습니다.

따라서 브라우저마다 약간씩 다른 형식의 문자열로 결과를 반환합니다.

또한, userAgent 프로퍼티의 결괏값은 appVersion 프로퍼티의 정보뿐만 아니라 상세 정보를 추가로 포함합니다.

##### 예제

``` js
document.write("현재 사용 중인 브라우저의 버전 정보는 " + navigator.appVersion + "입니다.<br><br>");
document.write("userAgent 프로퍼티로 알 수 있는 추가 정보는 " + navigator.userAgent + "입니다.");
```

> 이 프로퍼티는 웹 표준에서 제외되었으므로, 될 수 있으면 사용하지 않는 것이 좋습니다.



#### 현재 브라우저가 실행되고 있는 운영체제

navigator 객체의 platform 프로퍼티는 현재 브라우저가 실행되고 있는 운영체제를 식별하는 문자열을 반환합니다.

##### 예제

``` js
document.write("현재 브라우저가 실행되고 있는 운영체제는 " + navigator.platform + "입니다.");
```



#### 현재 브라우저의 기본 언어 설정

navigator 객체의 language 프로퍼티는 현재 사용 중인 브라우저의 기본 언어 설정을 반환합니다.

##### 예제

``` js
document.write("현재 브라우저의 기본 언어 설정은 " + navigator.language + "입니다.");
```



#### 자바 애플릿 실행 여부

navigator 객체의 javaEnabled() 메소드는 현재 사용 중인 브라우저가 자바 애플릿을 실행할 수 있는지를 검사하는 비표준 메소드입니다.

##### 예제

``` js
document.write("현재 브라우저는 자바 애플릿를 ");
if (navigator.javaEnabled()) {
    document.write("실행할 수 있습니다.");
} else {
    document.write("실행할 수 없습니다.");
}
```



#### 쿠키(cookie) 사용 여부

navigator 객체의 cookieEnabled 프로퍼티는 현재 사용 중인 브라우저가 쿠키를 사용할 수 있는지를 검사하는 비표준 프로퍼티입니다.

##### 예제

``` js
document.write("현재 브라우저는 쿠키를 ");
if (navigator.cookieEnabled) {
    document.write("사용할 수 있습니다.");
} else {
    document.write("사용할 수 없습니다.");
}
```



### 대화 상자

------

#### 대화 상자(dialog box)

사용자에게 보여줄 수 있는 간단한 대화 상자를 만들기 위해 window 객체는 다음과 같은 메소드를 제공합니다. 

- 1. alert()

- 2. confirm()

- 3. prompt()

------

#### alert() 메소드

window 객체의 alert() 메소드는 사용자에게 간단한 메시지를 보여주고, 그에 대한 사용자의 확인을 기다립니다.

##### 문법

``` js
window.alert("간단한 메시지");
```



사용자는 대화 상자의 확인 버튼을 눌러야만 다른 작업을 진행할 수 있습니다.

##### 예제

``` js
function alertDialogBox() {
    alert("확인을 누를 때까지 다른 작업을 할 수 없어요!");
}
```

>  window 객체의 모든 메소드나 프로퍼티를 사용할 때는 window라는 접두사를 생략할 수 있습니다.



#### confirm() 메소드

window 객체의 confirm() 메소드는 사용자에게 간단한 메시지를 보여주고, 사용자가 확인이나 취소를 누르면 그 결과를 불리언 값으로 반환합니다.

##### 문법

``` js
window.confirm("간단한 메시지");
```

사용자가 확인을 누르면 true를 반환하고, 취소를 누르면 false를 반환합니다.

##### 예제

``` js
function confirmDialogBox() {
    var str;
    if (confirm("확인이나 취소를 눌러주세요!") == true) {
        str = "당신은 확인을 눌렀습니다!";
    } else {
        str = "당신은 취소을 눌렀습니다!";
    }
    document.getElementById("text").innerHTML = str;
}
```



*function* confirmDialogBox() {

  *var* str;

  if (confirm("확인이나 취소를 눌러주세요!") == **true**) {

​    str = "당신은 확인을 눌렀습니다!";

  } else {

​    str = "당신은 취소을 눌렀습니다!";

  }

  document.getElementById("text").innerHTML = str;

}

[코딩연습 ▶](http://tcpschool.com/examples/tryit/tryhtml.php?filename=js_bom_dialogBox_02)

------

#### prompt() 메소드

window 객체의 prompt() 메소드는 사용자에게 간단한 메시지를 보여주고, 사용자가 입력한 문자열을 반환합니다.

##### 문법

``` js
window.prompt("간단한 메시지" + "입력란의 기본 메시지");
```

사용자가 대화 상자에 입력한 텍스트를 문자열 타입으로 반환합니다.

##### 예제

``` js
function promptDialogBox() {
    var inputStr = prompt("당신의 이름을 입력해 주세요 : ", "홍길동");
    if (inputStr != null) {
        document.getElementById("text").innerHTML = "당신의 이름은 " + inputStr + "입니다.";
    }
}
```

위에서 살펴본 대화 상자 이외에도 showModalDialog() 메소드를 이용하면, 좀 더 복잡한 대화 상자를 만들 수 있습니다.

 

하지만 이러한 대화 상자는 모두 사용자의 응답이 있을 때까지 브라우저의 실행을 강제로 중단시킵니다.

따라서 사용자 측면에서 보면 불편할 수도 있으므로, 대화 상자는 될 수 있으면 자주 사용하지 않는 것이 좋습니다.



### 타이머

------

#### 타이머(timer)

window 객체는 일정 시간이 지난 뒤에 함수를 호출할 수 있도록 다음 메소드를 제공합니다.

- 1. setTimeout()

- 2. setInterval()

또한, 이렇게 설정된 함수의 호출을 취소할 수 있도록 다음 메소드를 제공합니다.

- 3. clearTimeout()

- 4. clearInterval()

------

#### setTimeout() 메소드

setTimeout() 메소드는 명시된 시간이 지난 뒤에 지정된 함수를 호출합니다.

##### 문법

``` js
window.setTimeout(호출할함수, 지연시간);
```

이 메소드가 성공적으로 호출되면, 설정된 timeoutID를 반환합니다.

이 메소드는 밀리초(milliseconds) 단위로 지연 시간을 설정할 수 있습니다.

##### 예제

``` js
function startTimeout() {
    setTimeout(printCurrentDate, 2000);
}

function printCurrentDate() {
    document.getElementById("date").innerHTML = new Date();
}
```



#### setInterval() 메소드

setInterval() 메소드는 지정된 시간 간격마다 지정된 함수를 반복적으로 호출합니다.

##### 문법

``` js
window.setInterval(호출할함수, 지연시간);
```

이 메소드가 성공적으로 호출되면, 설정된 timeoutID를 반환합니다.

이 메소드는 밀리초(milliseconds) 단위로 시간 간격을 설정할 수 있습니다.

##### 예제

``` js
function startInterval() {
    setInterval(printCurrentDate, 2000);
}

function printCurrentDate() {
    document.getElementById("date").innerHTML += new Date() + "<br>";
}
```



#### clearTimeout() 메소드

setTimeout() 메소드의 반환값을 clearTimeout() 메소드의 인수로 전달하면, 계획된 함수의 호출을 취소할 수 있습니다.

##### 문법

``` js
window.clearTimeout(timeoutID);
```

이 메소드는 setTimeout() 메소드에 의해 함수가 호출되기 전에 실행되어야 호출을 취소할 수 있습니다.

함수가 호출된 이후에 이 메소드를 호출하면 아무런 동작도 하지 않습니다.

##### 예제

``` js
var timeoutId;
function startTimeout() {
    timeoutId = setTimeout(printCurrentDate, 2000);
}

function cancelTimeout() {
    clearTimeout(timeoutId);
}

function printCurrentDate() {
    document.getElementById("date").innerHTML += new Date() + "<br>";
}
```



#### clearInterval() 메소드

setInterval() 메소드의 반환값을 clearInterval() 메소드의 인수로 전달하면, 반복되는 함수의 호출을 취소할 수 있습니다.

##### 문법

``` js
window.clearInterval(timeoutID);
```

##### 예제

``` js
var timeoutId;

function startInterval() {
    timeoutId = setInterval(printCurrentDate, 2000);
}

function cancelInterval() {
    clearInterval(timeoutId);
}

function printCurrentDate() {
    document.getElementById("date").innerHTML += new Date() + "<br>";
}

```

