# DOM과 BOM의 차이점

출처: https://goddino.tistory.com/71



**DOM(Document Object Model, 문서 객체 구조)**

브라우저들은 사용자가 띄운 웹문서를 그 구성과 내용에 맞게 객체화하여
각 요소별(텍스트, 버튼, 이미지 등)으로 구조화 합니다.
이 요소들은 상,하위 구조나 병렬구조로 체계화 되는데
이 웹문서(document)에 대한 모든 내용을 담고있는 객체를 DOM 이라고 합니다.

**document는 window의 document (html 문서)** 입니다. 
**document object model(돔 객체)는 html과 javascript를 연결해주는 통역사 같은 역할**입니다. 

웹페이지 구조는 <html>이 모든 태그들을 감싸고 있으며,
그내부는 다시 <head>와 <body>로 나누에 집니다.
<body>태그에는 <header><main><section><footer>의 구조로 나뉘고,
그 하위 구조에 <div>, <p>, <ul>, <form> 등의 요소들이 배치됩니다.
이렇게 문서의 모든 요소들의 정보를 따로 객체화 하여 관리가 가능합니다.

DOM은 HTML 문서를 노트트리(node tree)로 나타내는데
노드(node)란 트리구조에서 데이터의 상하위 계층을 나타내는 위치의 항목입니다.



![img](https://blog.kakaocdn.net/dn/yerc0/btqKiaMCrth/InczdxiQ1E2H53zgQIxzkk/img.jpg)



 

자바스크립트를 이용하여 각 요소에 접근하여
HTML 소스를 제어, 변경, CSS(style)의 내용 변경이 가능합니다.

요소에 대한 접근 방식은,

· getElementById
· getElementByTagName
· getElementByClassName
· querySelector

**예제**

```js
<button id="btn">
  click
</button>

<script>
  let btn = document.getElementById("btn");
  	btn.addEventListener('click', function() {
    btn.innerHTML = "HEY";
  });

</script>
```

**한번에 보기**

 ``` html
 <button id="btn">
   click
 </button>
 
 <script>
   let btn = document.getElementById("btn");
   btn.addEventListener('click', function() {
     btn.innerHTML = "HEY";
   });
 
 </script>
 ```

getElementById 를 이용해 button에 접근하여,
click 이벤트를 등록하여, button의 text를 click -> hey 로 변경하였습니다.

***이렇게 DOM은 웹페이지의 내용을 제어합니다***

 

**BOM(Browser Object Model, 브라우저 객체 모델)**

웹페이지의 내용을 제외한 브라우저창에 포함된 모든 객체 요소들을 의미합니다.



중요한 차이점은,


DOM은 document, 현재 눈에 보이는 웹문서에 대한 제어와 변경을 하였다면,
BOM은 window 속성에 속하여 document가 아닌, window를 제어 합니다.

 

window 객체를 통하여 접근 방식은,

· location 객체: url 주소에 대한 정보를 제공
· window 객체: 최상위 객체로 각 프레임별로 하나씩 존재
· document 객체: 현재문서에 대한 정보
· navigator 객체: 브라우저명, 코드명, 버전정보 제공
· history 객체: 브라우저의 방문기록 정보를 제공
· screen 객체: 브라우저의 외부환경에 대한 정보를 제공

여기서 브라우저라는것은 결국 window안에서 실행되는 프로그램이므로,
위의 모든 개별 객체들은 최상위 객체인 window 아래에 존재합니다.