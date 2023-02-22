# Svelte강좌

출처: https://ossam5.tistory.com/327

## 인트로- 스벨트는 무엇인가?

## **1. Svelte의 현 위치**

------



![img](.\images\B2Pa07ycUKtOoJEVNIkSCK_img.png)2022년 9월 - 웹프론트엔드 언어 npm 트렌드



\- 아직까지 웹프론트엔드 프레임워크의 대세는 react입니다.

\- 위의 트렌드 그래프에서도 보다시피 주황색 라인인 react조회수가 월등한 것을 확인할 수 있습니다.

\- 그 다음이 vue, 그리고 angular, svelte 순서로 볼 수 있습니다.

\- 그럼에도 불구하고 제가 Svelte강의를 열게 된 것은 easy, easy, easy하기 때문입니다.

\- 웹프론트엔드를 처음 배우거나, 웹퍼블리셔에서 프런트엔드 개발자로 넘어갈 때 React부터 한다면 매우 어렵게 느껴질 것입니다.

\- 좀 쉬운 Svelte로 웹프론트엔드 프레임워크들을 이런 것이구나를 익히도 다른 언어를 가는 것도 좋을 듯합니다.

\- 그리고 십 년 전 제이쿼리가 대세가 될 수 있었던 것은 사실 쉬웠기 때문이거든요. 

\- 그래서 웹 프런트엔드 프레임워크도 쉬운 Svelte이 대세가되지 않을까하는 혼자만의 상상으로 강의를 진행해보고자 합니다. 



 

## **2. Svelte의 소개**

------



![img](.\images\lvU1Cbkg44E9ITxKrONq9k_img.png)



\- Svelte는 2016년에 출시한 오픈 소스, 웹프론트엔드 프레임워크입니다. 2019년 3번째 버전이 나오면서 자리를 잡고 개발자들에게 많은 관심을 갖게 되었습니다.

\- svelte 영어를 해석해보자면, [날씬한, 호리호리한] 이런 뜻입니다. 

\- 한마디로, 기존에 웹프론트엔드 프레임워크들을 플러그인 설치해서 무겁게 갔다면, 스벨트는 그런 것 다 빼고 순수 자바스크립트를 이용해서 가볍게 가겠다는 것을 이름에 담은 겁니다. 

\- Svelte 공식 사이트의 블로그 첫 번째 포스트에는 다음과 같은 문구가 있습니다. 

```js
"Frameworks without the framework"
```

\- Svelte는 프레임워크 없는 프레임워크다라는 뜻입니다. 

\- Svelte는 자바스크립트를 사용하기 때문에 굳이 프레임워크까지 써가면서 코딩해야 하는 걸까부터 시작한 것 같습니다.

\- 이 부분은 매우 동의를 합니다. 자바스크립트만으로 충분히 웹을 만들 수 있는데 굳이 프레임워크를 배워야 할까 가 저도 가끔 하는 생각이었습니다.

 

#### **## 프로그래밍 용어 설명**

| **용어**       | **설명**                                                     |
| -------------- | ------------------------------------------------------------ |
| **라이브러리** | 컴퓨터 프로그램에서 자주 사용되는 부분 프로그램들을 모아 놓은 것. (재사용 가능한 명령의 모음) |
| **프레임워크** | 소프트웨어 어플리케이션이나 솔루션의 개발을 수월하게 하기 위해 소프트웨어의 구체적 기능들에 해당하는 부분의 설계와 구현을 재사용 가능하도록 협업화된 형태로 제공하는 소프트웨어 환경 (재사용 가능한 명령을 규칙과 패턴으로 강제화시켜 틀에 넣어 제공하는 프로그램) |

\- 위의 사전적 용어를 퍼블리셔나 프런트엔드 개발자가 쉽게 이해하기 위해 설명해보겠습니다.

\- 제이쿼리는 프레임워크입니다. JS에서 문서 객체 선택이 힘들던 것을 [$('선택자')]로 쉽게 선택할 수 있도록 규칙과 패턴을 만들어 놓은 프로그램입니다. 그래서 이걸 사용하려면 제이쿼리 기본 플러그인을 붙여야 그 언어를 사용할 수 있습니다.

\- slick slider는 제이쿼리 라이브러리입니다.(물론, 현재는 리액트나 다른 프레임워크도 지원합니다.) 제이쿼리 언어를 기반으로 슬라이더 콘텐츠를 쉽게 메서드를 구현해서 명령만 해주면 사용 가능하도록 설정합니다. 물론 한 페이지에 여러 슬라이드를 재사용할 수 있습니다.

 

#### **## 프레임워크 없는 프레임워크다?**

\- React는 프레임워크입니다. Hooks, JSX, Redux 등을 사용하기 위해 일정한 규칙과 패턴이 제공됩니다. 

\- 원래 브라우저는 HTML, CSS, JS만 인식합니다. 그럼 React가 사용하는 위의 기능을 사용하려면 프레임워크 플러그인 붙여야 한다는 이야기입니다.

```js
<script src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
```

\- 물론 우리가 React를 개발할 때 바벨을 사용하기 때문에 직접 붙이거나 하진 않죠?

 

```js
import React from 'react';
import ReactDOM from 'react-dom';
```

\- 이런 식으로 프로젝트 생성하면 자동으로 생성돼서 붙여져서 나옵니다.

\- 이런 것들이 붙여져서 나오기 때문에 React구문들을 사용할 수 있습니다.

  

\- 하지만 Svelte은 프레임워크의 틀을 지우는 것부터 시작했습니다. 그래서 외부 CDN으로 연결하는 방식은 따로 없죠.

\- Svelte은 이렇게 생각한 것 같습니다. 우리가 자바스크립트를 잘 사용한다면 굳이 저런 규칙과 패턴이 필요할까?라는 거죠.

\- 그렇다고 Svelte에 비해 React나 Vue가 나쁘다는 이야기는 아닙니다. 각각의 프레임워크는 각각의 장점을 갖고 있습니다.

\- Svelte은 결국 우리가 사용해야 는 것은 자바스크립트다!라고 말하는 것으로 보입니다.

 

 

## **3. Svelte의 장점**

------



![img](.\images\g9B6to9UgAwnSTUhAFUXw1_img.png)출처 :&nbsp;https://svelte.dev/



\- 위의 이미지는 Svelte 공식 사이트의 첫 화면을 가져온 것입니다.

\- 첫 화면에 보면 자신만의 장점을 저렇게 배너로 잘 구현해 놓았습니다.

1. Write less code : 보다 적은 코드
2. No Virtual DOM : 가상 돔 없이
3. Truly reactive : 진정한 반응성

\- 이렇게 3가지로 잘 정리되어 있습니다. 

  

### **1) (다른 프레임워크) 보다 적은 코드**



![img](.\images\V4KMkrzrnNz76onNbliFk0_img.png)

출처 :&nbsp;https://svelte.dev/



\- 여러 가지 이유로 코드를 줄일 수 있지만, 간단히 설명하자면 변수 선언부터 간결합니다. 그냥 자바스크립트랑 똑같습니다.

\- 리액트는 값을 useState를 통해 관리하고, Vue는 data를 통해 관리하기 때문에 일단 무조건 자바스크립트보단 깁니다.

\- 근데 Svelte는 자바스크립트 변수 선언과 같게 사용하므로 코드가 간결합니다.



![img](.\images\4ZTeQJLSx3XFOPH6UKwzG0_img.png)

왼쪽부터 Svelte, React, Vue 순서로 코드 작성



\- 어떤 가요? Svelte이 확실히 간단하게 작성하죠?

 

### **2) 가상돔 없이 사용**

\- Virtual DOM은 DOM에 직접 접근하여 조작하는 것이 아닌, DOM을 자바스크립트 객체 형태로 만들어 메모리에 두고 필요할 때 꺼내서 조작하는 방법입니다. 이것을 우린 가상 돔이라고 합니다.

\- 이 가상 돔은 React가 나오면서 많은 개발자들에게 각광받아 온 개념입니다. 

\- 가상 돔을 만들어도 속도도 나쁘지 않고, React나 Vue를 사용하다보면 자연스럽게 받아들이는 개념이라 어렵지 않습니다.

\- 제이쿼리가 DOM을 무겁게 사용해서 다른 프레임워크들이 가상돔을 만들어서 더 빠르게 사용했던 것입니다.

\- 하지만 가상돔 없이도 DOM을 무겁지 않게 비슷한 성능과 속도를 낸다면, 과연 가상돔을 쓸 필요가 있을까요?

\- Svelte이 가상돔을 사용하지 않고, DOM을 가볍게 제어합니다. 

\- 그럼 Svelte은 가상돔을 사용하지 않고, 어떻게 DOM을 제어할까요?

 

 

 



![img](.\images\1mmFEuXlcAmoERDb6k7VFK_img.png)출처 : https://webpack.js.org/



\- 위는 요즘 프런트엔드 언어들이 웹팩[webpack]을 이용해서 번들링 하는 과정을 그림으로 표시한 것입니다.

\- 실제 사이트로 들어가면 애니메이션으로 멋지게 돌아가며 설명하고 있습니다.

\- 번들링이란 복잡한 DOM이나 CSS, 요소들을 나눠 Node.js로 작업하고 배포 시 하나의 파일로 모이게 만드는 것을 말합니다. 

\- 이때 위에 그림에 있는 js들은 하나의 js파일로 모이고, sass나 css로 만든 것은 하나의 css로 모이는 것입니다.

\- 리액트나 뷰는 이때 js에 자신의 규칙과 패턴으로 이루어진 자신의 언어로 js파일로 들어가게 됩니다.

\- 대신 브라우저가 인식하게끔 라이브러리도 같이 붙여져서 들어갑니다.

\- Svelte은 이 과정에서 자신의 언어를 컴파일만 하고, 순수 자바스크립트로 들어가게 됩니다. 

\- 그래서 Svelte은 자신은 프레임워크라고 소개하지 않고, 최적화된 자바스크립트로 변환하는 컴파일러라고 소개합니다.

\- 그리고 라이브러리 없이 순수 자바스크립트로 들어가기 때문에 빌드될 때 용량이 작아진다는 장점도 생기게 됩니다.

 

### **3) 진정한 반응성**

\- Svelte의 마지막 장점은 반응성이 좋다는 점입니다. 

\- 프런트엔드 개발은 보통 하나의 동작으로 여러 가지를 처리하는 일이 많이 있습니다. 

\- 메인이벤트가 일어났을 때, 나머지 이벤트들이 자동으로 발생하게 할 수 있다면 개발이 훨씬 편해집니다.

\- Svelte은 이 작업을 쉽게 하도록 설계되어 있습니다. 

 

#### **## 클릭할 때마다 숫자가 증가하는 이벤트 제작, 자동으로 제곱 값을 구해주는 코드**

```
<script>
	let count = 0;
    $: square = count * count;
    const handleClick = () => count = count + 1;
</script>

<button on:click={handleClick}>
	클릭횟수 : {count}
</button>
<p>제곱 값: {square}</p>
```

\- 스크립트 안의 코드는 현재 3줄이라는 것은 잘 보면 됩니다. 

 

#### **## 결과 확인**



![img](.\images\Ee4s9t5HBr22r51HxiZyek_img.gif)



##  

  

#### **마치며...**

\- 모든 언어는 다 장단점이 있다고 생각합니다. 그래서 어떤 것이 좋다고 나쁘다고 말하는 것은 아닙니다.

\- Svelte는 아직 사용하고 있는 개발자가 많지 않습니다. 그래서 다양한 소스나 깃, 플러그인들이 많지 않다는 것을 단점이라고 볼 수 있겠습니다. 

\- 그래도 다른 프레임워크에 비해 많이 쉽기 때문에 프런트엔드를 입문한다면 Svelte로 배우면 다른 프레임워크도 쉽게 배우지 않을까요? 그리고 미리 다른 프레임워크를 배웠다면 Svelte는 쉽게 배울 수 있을 것입니다.

\- 그리고 계속 변화되는 프런트엔드 환경에서 프론트엔드 개발자라면 어떻게 변화하고 있는지 계속해서 관심 갖고 공부하는 것이 좋을 것입니다. 



---

## 1강 스벨트 개발환경 구축(설치)

## **1. REPL(Read-Eval-Print-Loop)**

------

\- Svelte는 공식 사이트에서 REPL을 통해, 별도의 설치 과정이나 컴파일 없이 코딩하고 바로 결과를 확인할 수 있는 서비스를 제공하고 있습니다. 

\- 이 기능은 코딩은 좀 해봤다면 들어 본, CodeSandbox같이 코드만 입력해도 바로 결과가 보입니다.

 

### **1) Svelte사이트 접속**

\- 우선 아래 주소를 클릭해서 Svelte사이트에 접속해주세요.

https://svelte.dev/

 

Svelte • Cybernetically enhanced web apps

Svelte is a radical new approach to building user interfaces. Whereas traditional frameworks like React and Vue do the bulk of their work in the browser, Svelte shifts that work into a compile step that happens when you build your app. Instead of using tec

svelte.dev

 

### **2) 상단 메뉴 중 [REPL] 글자 클릭**



![img](.\images\PIZqLJVJXUAjRw4qd5ffr1_img.png)

### **3) REPL 화면 보기**



![img](.\images\TrTzulQlBEmkRnkPPJ2jI1_img.png)



\- 이렇게 왼쪽에는 직접 코드를 쓸 수 있는 파트와 오른쪽에는 결과를 볼 수 있게 뜹니다. 

 

### **4) 코딩하면서 바뀌는 것 보기**

\- [world]라는 것을 [ossam]으로 바꿔보세요.

 

#### **## 결과 확인**



![img](.\images\vq3AXa4K8iXkH0OeHXOhZ1_img.gif)



\- 코드를 바꾸니까 실시간으로 변경되는 것이 보입니다.

\- 태그를 추가해도 바로바로 적용됩니다.

\- 문법이나 코드 사용법만 본다면 여기서 공부해도 되지만, 실제 프로젝트를 진행하려면 컴퓨터에 개발환경을 구축해야겠죠? 아래부터는 컴퓨터에 직접 개발환경을 구축하는 것을 보도록 하겠습니다. 

 

 

## **2. 로컬 개발환경 구축**

------

\- 이번에는 직접 컴퓨터에 개발환경을 구축해보도록 하겠습니다.

\- Node.js와 VS Code를 가지고 구축해보도록 하겠습니다. 

 

#### **## 왜 Node.js를 설정해야 하는가?**

\- Node.js란 V8엔진을 이용해 브라우저 이외의 환경에서도 자바스크립트를 실행할 수 있게 해주는 런타임 도구입니다.

\- 자바스크립트는 원래 브라우저에 사용되는 컴퓨터언어입니다. 다른 곳에 사용하려고 쓰진 않습니다.

\- 브라우저는 원래 HTML, CSS, JS만 인식합니다. 거기에 플러그인을 붙여 프레임워크를 쓰는 겁니다.

\- 지난 인트로 강좌에서 번들링에 대한 이야기를 했습니다. HTML, CSS, JS, 플러그인, 이미지 등 소스들을 한 번에 쓰면 너무 복잡해집니다. 그래서 나눠서 작업한 후 브라우저가 인식할 수 있게 코드를 빌드해서 하나로 모아 서버에 올려 작업하게 됩니다. 이 모든 과정이 바로 Node.js 환경에서 발생이 되는 것입니다.

\- 다른 프레임워크(React, Vue 등)를 배우고, Svelte을 작업한다면 이런 개발환경을 구축하는 것이 어렵지 않을 수 있지만 처음 배우거나 웹퍼블리싱으로 코딩했던 분들을 조금 어려운 파트가 될 수 있습니다.

\- 하지만 Svelte을 사용하기 위해 Node.js를 깊이 알아야 하는 것이 아니라, Svelte이 실행되는 환경에 Node.js가 필요하고 몇 가지 명령어만 기억하시면 됩니다.

 

### **1) Node.js 설치**

\- 아래 주소를 클릭해서 Node.js를 먼저 설치해줘야 합니다.

\- https://nodejs.org/ko/

 

Node.js

Node.js® is a JavaScript runtime built on Chrome's V8 JavaScript engine.

nodejs.org



![img](.\images\xiyWl39BlkN2JiOBGxhB81_img.png)



\- 들어가서 초록색 버튼을 눌러 다운로드 후 설치해주세요.

\- 보통 안정적이고 신뢰도가 높은 LTS버전을 설치하는 것이 좋습니다.

 

### **2) Node.js 설치 확인**

\- Node.js가 잘 설치되었는지 확인해봐야겠죠?

\- 설치 완료 후에는 cmd창을 열어주세요.

\- windows OS환경이라면 [윈도우로고키]+[R]을 입력 후 [cmd]라고 칩니다.

\- 그러고 나서 아래 명령어를 쳐보면 잘 설치되어 있는지 확인할 수 있습니다. 

 

```bash
node -v
```

 

![img](.\images\YGMvUCQXZ0qxQkLUeks961_img.png)



  

### **3) Visual Studio Code - VS Code 설치**

https://code.visualstudio.com/

 

Visual Studio Code - Code Editing. Redefined

Visual Studio Code is a code editor redefined and optimized for building and debugging modern web and cloud applications. Visual Studio Code is free and available on your favorite platform - Linux, macOS, and Windows.

code.visualstudio.com

\- VS CODE로 진행할 예정입니다. 

\- 프로그램 다운로드하여 설치해주세요.

 

 

#### **## 확장 플러그인 설치**



![img](.\images\kz30HlpCPFFVvoAmbbKMx0_img.png)



\- 확장 플러그인은 많은 것이 많지만 저는 위의 두 개를 설치해서 사용하도록 하겠습니다.

\- [Svelte for vs code], [Svelte 3 Snippets]를 설치해주세요.

 

## **3. Svelte 프로젝트 설치**

------

\- 환경을 다 구축했으면 실제 프로젝트를 만들어보겠습니다.

\- 방법은 두 가지입니다. 실제 사이트에서 다운로드하여서 실행하는 방법과 degit를 이용해 설치하는 방법이 있습니다.

 

### **1) 사이트에서 다운받아 사용하기**



![img](.\images\PO8piQ6Arqk4cpXL1ZkC40_img.png)



\- 사이트에서 [REPL]페이지로 가서 다운로드 아이콘을 누르면 파일[svelte-app.zip]이 다운로드됩니다.

\- 진행하고자 하는 폴더에 가서 압축을 풉니다.

 



![img](.\images\BFchxxBnXNkBJwCI58Y7M1_img.png)



\- 위와 같이 설치가 되었으면, [test]폴더를 루트 폴더로 VS CODE를 열어 줍니다.

###  

![img](.\images\AwtZiffQ58y2mPdyLGhCMk_img.gif)



\- [터미널] 메뉴- [새 터미널]을 누릅니다.

\- 그럼 하단에 터미널 영역에 생성됩니다.

  

#### **## 패키지 파일 설치 코드**

```bash
npm install
```

 



![img](.\images\FUP1mgc0PzxMhkzyaj8IVk_img.gif)



 

####  

#### **## 개발 모드 실행**

```bash
npm run dev
```

 

![img](.\images\CRpiiB3WEiQENOpUcPX9SK_img.gif)



 

#### **## 브라우저 실행**

\- 보통 다른 주소가 [localhost:5000]였으나, 상황에 따라 다른 것 같습니다.

\- 마지막에 뜬 [- Local:   http://localhost:8080]을 Ctrl 눌러 클릭하면 브라우저가 뜹니다. 

\- 리액트처럼 자동으로 뜨면 좋을 것 같지만, 자동으로 뜨지는 않는 것 같습니다.



![img](.\images\eBKNN1MJxK9oDDSWVKSZbk_img.gif)



 

### **2) degit를 통한 github 저장소 사용**

\- degit는 github 저장소를 현재 경로에 clone 하기 위한 패키지입니다.

  

#### **## degit를 전역에서 사용하게 해주는 코드**

```bash
npm install -g degit
```

 



![img](.\images\pEAyO1avKcW9YKhtY8ii3K_img.gif)현재 새폴더 [test2]를 만들어서 다시 시작했습니다.



#### **## Bundler 선택하기**

```bash
//새로운 하위 폴더 생성 후 clone
npx degit sveltejs/template 폴더명

//현재 폴더에 clone
npx degit sveltejs/template ./
```

\- 현재 이방식은 github 쪽에서 막아서 되지 않는 것 같습니다.

 

```bash
//하위폴더로 생성 후 clone
npx degit sveltejs/template-webpack 폴더명

//현재 폴더로 clone
npx degit sveltejs/template-webpack ./
```

\- 2022년 9월 현재 이 방법으로 클론 하는 것이 적용되고 있습니다.

 

![img](.\images\mCTAxxHklv6jaImYwLRkW1_img.gif)현재 폴더로 클론 했습니다.



 

 

 

#### **## 패키지 파일 설치 코드**

```bash
npm install
```

 



![img](.\images\ckuUEQlc5PyzvY38fhcKFK_img.gif)



 

#### **## 개발 모드 실행**

```bash
npm run dev
```

 



![img](.\images\1X2eNSSUO8f5B7Lf9xJW31_img.gif)



 

 

 

#### **## 브라우저 실행**



![img](.\images\eG0On4PTpVLm7sUQgSZx50_img.gif)



\- 위로 올라가서 주소를 클릭하면 역시 브라우저가 실행됩니다.

  

## **4. Svelte 설치 폴더 구조**

------



![img](.\images\QZqV9Sv8YUWA8YDDx3Oba1_img.png)설치하면 이런 폴더 구조로 되어 있습니다.



 

 



![img](.\images\n9hqRd91ppY37i5KFP2KO0_img.png)정리해서 보면 이런 식으로 폴더와 파일들이 들어가 있습니다.



 

| **폴더/파일**        | **설명**                            |
| -------------------- | ----------------------------------- |
| **node_modules**     | 설치된 패키지가 위치한 폴더         |
| **public**           | 빌드 결과가 위치한 폴더             |
| **scripts**          | Typescript 설정 파일이 위치한 폴더  |
| **package.json**     | 설치된 패키지 정보가 들어 있는 파일 |
| **rollup.config.js** | rollup 설정 파일                    |
| **src**              | 실제 프로젝트 코드가 들어갈 폴더    |

\- 다른 폴더들은 건드릴 필요가 거의 없는 곳이라 설명은 위의 표로 대체하겠습니다.

\- src부분이 우리가 Svelte으로 코드를 작성할 파일들이 들어가 있습니다. src부분은 컴포넌트 사용법을 다음 강좌에서 보면서 자세히 설명하도록 하겠습니다.

\- public부분은 우리가 src부분의 코드를 빌드(컴파일)해서 실제적으로 서버에 올릴 파일들이 생성되는 곳입니다.



---

## 2강 컴포넌트 - 기본사용법

## **1. src 폴더 파일 구조**

------

\- 지난 강좌에서 Svelte 설치 폴더 구조를 확인했습니다.



![img](.\images\3UPvIVkgnoHR4XNUkgoPaK_img.png)



\- 여기에 src폴더에 있는 2개의 파일 구조를 보도록 하겠습니다.

 

### **1) App.svelte**

\- [ App.svelte ]는 최상위 컴포넌트 파일입니다. 

\- Svelte에서는 컴포넌트 확장자가 [ js ]가 아닌 [ svelte ]으로 된다는 것을 잘 기억해주시면 됩니다.

 

 

#### **## 설치된 처음 코드 보기**

```js
<script>
	let name = 'world';
</script>

<h1>Hello {name}!</h1>
```

\- 처음엔 간단히 이렇게 작성되어 있습니다.

 

#### **## 컴포넌트 구역 확인하기**

```js
//script 영역
<script>

</script>


//markup 영역


//style 영역
<style>

</style>
```

\- 컴포넌트는 3가지 구역으로 나눕니다. 3개의 작성 순서는 관계없고, 3개 다 없어도 됩니다.

\- 단지 script와 style는 해당 태그 안에 작성한 다는 것만 잘 기억하면 됩니다.

 

### **2) main.js**

\- [ main.js ]는 [ App.svelte ]에 모인 모든 컴포넌트 파일(*.svelte)과 JS파일(*.js)파일들이 작동되게 하는 시작점(entry point)가 되는 파일입니다. 

 

#### **## 설치된 처음 코드 보기**

```js
import App from './App.svelte';

var app = new App({
	target: document.body
});

export default app;
```

\- 앱의 시작점을 정의하기 위해서는 [ new App({ }) ]으로 import한 [ App.svelte ]을 이용해 새로운 인스턴스를 만드는 작업이 필요합니다. 

\- 위에 적혀 있는 [ target ]은 App 컴포넌트가 mount되어 위치할 곳을 설정합니다.

\- 위에 [ document.body ]라고 되어 있죠? [ public ]폴더 안에 [ index.html ] 문서의 body를 가리킵니다. 

 

 **## public의 index.html 코드 보기**

```html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset='utf-8'>
	<meta name='viewport' content='width=device-width,initial-scale=1'>

	<title>Svelte app</title>

	<link rel='icon' type='image/png' href='/favicon.png'>
	<link rel='stylesheet' href='/global.css'>
	<link rel='stylesheet' href='/build/bundle.css'>

	<script defer src='/build/bundle.js'></script>
</head>

<body>
	<!-- 여기에 mount하겠다는 뜻! -->
</body>
</html>
```

  

## **2. 컴포넌트**

------

\- 컴포넌트(Compontent)는 재사용 가능한 UI입니다.

\- 현재 프론트엔드 언어(리액트, 뷰 등)에서 대부분 컴포넌트 기반으로 개발합니다.

\- 그럼 재사용 가능한 UI를 왜 쓰는지를 보도록 하겠습니다.

 

 

### **1) DOM의 문제점을 그림으로 확인**



![img](.\images\BSLIr2iwimRUDxod9atgo0_img.png)



\- 위와 같이 메인페이지와 제품 페이지가 두 개가 있다고 보겠습니다.

\- HTML페이지 구현 시 [header]와 [footer]를 계속 작성해줘야 합니다.

\- 그래서 변경될 때마다 새롭게 구현해야 하기 때문에 속도도 많이 느려집니다.

\- 그리고 제작시 header에 있던 글자 하나만 변경하려 해도 모든 페이지의 글자를 변경해야 하기 때문에 번거로움이 있습니다.

 

### **2) Svelte 컴포넌트로 활용**



![img](.\images\2xfbgfIzamLozRPpzKCUh0_img.png)



\- Svelte에서 해당 페이지를 제작한다면 이런 식으로 컴포넌트를 제작해서 만들면 편합니다.

\- 꼭 이렇게 한다기 보다는 예시를 통해 컴포넌트를 이해해보겠습니다.

 

 

#### **## 컴포넌트를 사용하는 이유**

1. 분류를 통한 관리의 효율성
2. 재사용을 통한 개발의 효율성

\- 위와 같이 2가지 이유로 컴포넌트를 사용합니다.

\- [header]와 [footer]같은 경우는 분류를 통해서 관리를 편하게 하기 위해 작성합니다. 예전과 같은 html작성법이라면 [header]에서 글자를 하나만 바꿔도 모든 페이지에 가서 변경해야 했지만, 위와 같은 방법을 사용하면 한 번만 바꿔도 모든 페이지가 알아서 참조하기 때문에 매우 편합니다.

\- [product]같은 경우는 리스트 목록들은 구성이 비슷합니다. 그래서 한 개를 만들어오고 잘 불러들여오면 됩니다. 그럼 재사용하니 매우 효율적으로 코딩할 수 있습니다.

  

## **3. 하위 컴포넌트 불러오기**

------

### **1) 하위 컴포넌트 불러오는 문법**

#### **## Script 파트 문법**

```js
<script>
	import 컴포넌트명 from 'Svelte파일경로';
</script>
```

\- import는 외부파일을 불러오는 자바스크립트 문법입니다.

\- 잘 모르시겠는 분은 제 블로그의 ES6 카테고리를 공부해 보세요.

  

#### **## Markup 파트 문법**

```js
//markup
<컴포넌트명 /> <!-- 컴포넌트는 꼭 대문자로 시작해야 함 -->
```

\- 불러오는 svelte파일명은 첫 글자가 꼭 대문자일 필요는 없습니다.

\- 하지만 컴포넌트 명은 반드시 대문자로 처리해야 합니다.

 

### **2) 기본 컴포넌트 제작하기**



![img](.\images\SS17piAfa3KwABs9eSfXfK_img.png)



\- 위에서 설명했던 이미지대로 컴포넌트를 활용해보도록 하겠습니다.

 

 

 

#### **## header.svelte - 하위 컴포넌트 제작**

```html
<h1>헤더 구역</h1>
```

\- 새파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [header.svelte]라고 합니다.

 

 

#### **## footer.svelte \**- 하위 컴포넌트 제작\****

```html
<h1>푸터 구역</h1>
```

\- 새파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [footer.svelte]라고 합니다.

 

 

#### **## main.svelte \**- 하위 컴포넌트 제작\****

```html
<h1>메인 구역</h1>
```

\- 새파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [main.svelte]라고 합니다.

 

 

#### **## product.svelte - 하위 컴포넌트 제작**

```html
<h3>제품 구역</h3>
```

\- 새파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [header.svelte]라고 합니다.

 

 

 

#### **## mainpage.svelte - 페이지 컴포넌트 제작**

```js
<script>
    import Header from "./header.svelte";
    import Footer from "./footer.svelte";
    import Main from "./main.svelte";
</script>

<Header />
<main />
<footer />
```

\- 새파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [mainpage.svelte]라고 합니다.

\- 확장 플러그인인 [Svelte for vs code], [Svelte 3 Snippets]가 설치되어 있다면 자동완성 기능이 됩니다. 

 



![img](.\images\afdJ4PlakU1SjLkzN4g3Ik_img.gif)



\- import를 치고 하위 컴포넌트명을 대문자로 치고 선택하면 자동 완성되는 것이 확인됩니다.

\- 태그로 마크업 할때도 대문자를 치면 나오는 것이 확인됩니다.

 

 

 

#### **## productpage.svelte - 페이지 컴포넌트 제작**

```js
<script>
    import Header from "./header.svelte";
    import Footer from "./footer.svelte";
    import Product from "./product.svelte";
</script>

<Header />
<Product />
<Product />
<Product />
<Footer />
```

\- 새 파일을 만들어 [src] 폴더 내에 저장하고, 이름은 [productpage.svelte]라고 합니다.

 

\- 마찬가지로 파일을 불러오고 이번에는 <Product /> 를 세 번 작성해보겠습니다. 

 

 

 

#### **## App.svelte - 최상위 컴포넌트로 페이지 컴포넌트 가져오기**

```js
<script>
	import Mainpage from "./mainpage.svelte";
	import Productpage from "./productpage.svelte";
</script>

<Mainpage />
<hr />
<Productpage />
```

\- [ App.svelte ] 페이지는 미리 만들어져 있으므로 기존 코드를 지우고 위와 같이 작업해주세요.

\- 여기서 필요한 것은 페이지 격인 컴포넌트들입니다. 그래서 그 2개를 불러옵니다.

\- <hr /> 태그는 구분짓기 위해 실제 html태그인 수평선 태그를 작성했습니다. 

 

#### **## 결과 보기**



![img](.\images\i6KhEqAhiMrKLJgJlFVS4k_img.png)



\- 결과 페이지에 가보면 잘 불러와지는 것을 확인할 수 있습니다. 



---

## 3강 State(상태값)

## **1. State(상태 값)란?**

------

\- State란 컴포넌트가 어떤 값을 관리할 때 사용하는 것을 말합니다.

\- 상태 값은 컴포넌트로 표현할 값의 상태나 목록이 되기도 하며, 이벤트를 통해 발생된 마우스나 키보드의 데이터도 상태 값이 될 수 있습니다. 즉, 프런트엔드를 구성하는 모든 데이터가 상태 값입니다.

\- 리액트(React)에서는 useState()라는 함수를 통해 값을 관리하는데, 스벨트(Svelte)는 자바스크립트에서는 변수처럼 사용하고, 마크업에서는 중괄호({ })를 사용하여 상태 값을 표현합니다. 

 

### **1) 텍스트 콘텐츠로 처리**

#### **## 기본 문법**

```js
<script>
	let 상태변수 = 값;
</script>

<태그> { 상태변수 } </태그>
```

 

 

 

\- Svelte 파일들을 처음 실행하면 나오는 코드를 보겠습니다.

#### **## 예시 코드 보기**

```js
<script>
	let name = 'world';
</script>

<h1>Hello {name}!</h1>
```

\- script 파트에서 변수명이 name에 상태 값을 담고, 마크업 쪽에서 { } 중괄호를 통해 값을 표현한 것입니다.

\- 이렇게 관리되는 값을 State, 즉, 상태 값이라고 합니다. 

 

 

 

#### **## 상태값 변경**



![img](.\images\B8nfTuvJlBVWAuo4KYPeSK_img.gif)



\- script파트에서 변숫값을 변경하니까, 마크업 쪽 { } 중괄호 값이 변하는 것이 확인됩니다. 

 

 

### **2) 태그 속성 값에 처리**

\- 태그 속성값에도 State값을 처리할 수 있습니다.

 

#### **## 문법**

```
<script>
	let 변수명 = 값;
</script>

<img src={변수명} />
```

 

 

#### **## 코드 예시**

\- 이번에는 이미지 경로로 작성해보겠습니다.

\- 이미지는 아래 이미지를 사용해주세요.,



![img](.\images\bzaUnvh0SXlevptFhvYfwK_img.png)



\- url : https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbGz29o%2FbtrNgcGtBfI%2FbzaUnvh0SXlevptFhvYfwK%2Fimg.png 

 

```js
<script>
	let url = 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbGz29o%2FbtrNgcGtBfI%2FbzaUnvh0SXlevptFhvYfwK%2Fimg.png';
</script>

<img src={ url } alt="고양이이미지" />
```

 

 

 

#### **## src속성 속기법**

```js
<script>
	let 변수명 = 값;
</script>

<img {변수명} />
```

\- 변수명을 바로 속성명으로 사용 가능합니다.

\- 대신 변수 명의 이름이 속성명과 같아야 합니다.

\- 위에 State 변수명을 url이라고 적었던 것은 되지 않습니다.

 

 

#### **## 코드 보기**

```js
<script>
	let src = 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbGz29o%2FbtrNgcGtBfI%2FbzaUnvh0SXlevptFhvYfwK%2Fimg.png';
</script>

<img { src } alt="고양이이미지" />
```

\- 위 코드와 같이 state 이름을 태그 속성명과 같게 줘야 처리가 됩니다.

 

 

#### **## 결과 보기**



![img](.\images\JyhEwhJrcSCUJCsxgwYxm1_img.png)



  

### **3) State 값의 태그 문자열 인식**

\- 변수에 문자열을 주었을 때 그 문자열에 태그가 있다면 인식시켜 보겠습니다.

 

#### **## 문법**

```js
<script>
	var 변수명 = 값;
</script>

<태그명> {@html 변수명 } </태그명>
```

 

 

#### **## 태그 미인식 코드**

```js
<script>
	let str = '안녕하세요! <strong>오쌤의 니가스터디</strong>입니다.';
</script>

<p>{ str }</p>
```



![img](.\images\kfnOeNwLBqC4JdTpFzPvT1_img.png)



\- 태그가 브라우저에 문자열로 반환이 됩니다.

 

 

 

#### **## 태그 인식 코드**

```js
<script>
	let str = '안녕하세요! <strong>오쌤의 니가스터디</strong>입니다.';
</script>

<p>{ @html str }</p>
```



![img](.\images\pt8GzEnhLBkZjKnjjy79pk_img.png)



\- 오쌤의 니가스터디 부분이 굵게 잘 처리되는 것이 확인됩니다. 

 

## **2. click 이벤트를 이용한 State 값 변경**

------

\- 이번에는 버튼 클릭을 통해 State값을 변경해보도록 하겠습니다.

 

 

#### **## 코드 보기**

```js
<script>
	let num = 0; //state(상태값)

	//기존 변수값에서 1씩 증가
	const increaseNumber = () => num++;
	
	//기존 변수값에서 1씩 증가
	const decreaseNumber = () => num--;
</script>

<button on:click={increaseNumber}>+</button>
<button on:click={decreaseNumber}>-</button>
<hr />
<h1>클릭횟수 : { num }</h1>
```

\- script파트에 [ num ] 변수에는 클릭 시 숫자가 증가될 상태 값을 담습니다.

\- 처음에는 클릭한 적이 없으니까 기본값으로 [ 0 ]을 담아줍니다.

\- [ increaseNumber ]는 함수로 [ num ] 변수에 증가 연산자를 줘서 함수가 실행 시, 기존 변숫값에서 1씩 증가하게 처리합니다.

\- [ decreaseNumber ]는 함수로 [ num ] 변수에 감소 연산자를 줘서 함수가 실행 시, 기존 변숫값에서 1씩 감소하게 처리합니다.

\- 함수들을 화살표 함수로 처리했습니다. 화살표 함수를 잘 모르는 분은 제 블로그 ES6 강좌를 보고 오시기 바랍니다.

\- 마크업 부분에 <button> 태그를 처리하고 클릭이벤트에 [ increaseNumber ] 함수와 [ decreaseNumber ] 함수를 호출합니다. 

\- <h1> 태그에 상태 값인 [ num ] 변수를 { } 중괄호로 처리해줍니다. 

 

 

#### **## 결과 보기**



![img](.\images\5GGLBpgZWA1f7oKgT0AfV1_img.gif)



\- 플러스 버튼을 누르면 숫자가 1씩 증가되고, 마이너스 버튼을 누르면 1씩 감소하는 것을 확인할 수 있습니다.

\- 이렇게 상태 값을 이벤트에 따라 바꿀 수 있습니다. 

 

 

## **3. 객체 속성을 State로 활용**

------

\- 위에서 본 간단한 변수 형태 말고, 객체 방식으로도 데이터 값을 상태 값으로 활용할 수 있습니다.

 

 

#### **## 문법 보기**

```js
<script>
	let 객체명 = {
    	속성명1: 값,
        속성명2: 값
    }
</script>

<태그1> { 객체명.속성명1 } </태그1>
<태그2> { 객체명.속성명2 } </태그2>
```

 

 

#### **## 코드 보기**

```js
<script>
	let artist = {
		name: '진',
		age: 31,
		height: 179,
		group: '방탄소년단',
		img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F8152fad7a07738b7d11b8fe1b44b3cd0db34d5fa5c35abee4deae2fd163a4218f003e753532e1edd20a4db12e88382c0f3342d054cba5a0d38ba8bd9be593138ae4b6404caf7373e92540f2688467b825caa89de860dccc282395d440f6f72db&type=fff264_180'
	}
</script>

<h1><img src="{artist.img}" alt=""></h1>
<h1>이름 : { artist.name }</h1>
<h1>나이 : { artist.age }</h1>
<h1>키 : { artist.height }</h1>
<h1>소속그룹 : { artist.group }</h1>
```

\- script파트에서는 [ artist ]라는 객체를 선언하고, 각각의 속성들에 값을 전부 담았습니다. 

\- img값은 네이버에서 검색해서 나무 위키에 사용된 이미지 주소를 복사해왔습니다. 

\- 마크업 부분에서 각각의 속성 값들을 호출했습니다.

 

 

#### **## 결과 보기**



![img](.\images\f7oh9Mp6XP82wMOdO9YS6k_img.png)



\- 결과가 잘 나오는 것이 확인됩니다.

 

## **4. 객체로 이루어진 배열을 이용해서 상태 값 표시**

------

\- 이번에는 방탄소년단 전부를 반복해서 만들어 보겠습니다.

 

#### **## 코드 보기**

```js
<script>
	let bts = [
		{
			name: 'RM(김남준)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Fbbd9629ef96c284dfca79eb3b26d49e49ba24b78117abba76b3108cd2b5b8b2860f4d7ceeaef314116b608d5b9d7b080d7e7751e385cc03988992088f8b932b5280a1d1075b1c5bd4759e679166564ca9a95b4ca0bdbdaa219462eacbcbd37be&type=fff264_180'
		},
		{
			name: '진(김석진)',
			birth: 1992,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F8152fad7a07738b7d11b8fe1b44b3cd0db34d5fa5c35abee4deae2fd163a4218f003e753532e1edd20a4db12e88382c0f3342d054cba5a0d38ba8bd9be593138ae4b6404caf7373e92540f2688467b825caa89de860dccc282395d440f6f72db&type=fff264_180'
		},
		{
			name: '슈가(민윤기)',
			birth: 1993,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Ff4f55ac286d2de6ead4dcf4ab419ca0ba3c44e3badcffe171f727d2653d9dce70d3a2a016307c21a9345fe53c74f099657bc5c96006acc61dc32eecff8e197818ed47d31fbfffc2e17654634345a357017536656b0258d7d1b26ee6b687095b4&type=fff264_180'
		},
		{
			name: '제이홉(정호석)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F67b1d265064ef6fce5ff59b3cf9195e64afade8493633cbf57087c55d40655c08a9481278bf960455ab4ea434bc3c250d57333d6c48743124de4c20d359684728713f1d0b8b063bc510feece8729186146cdbdba6cba15864bc4582aeebce6d5&type=fff264_180'
		},
		{
			name: '지민(박지민)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F379e369eeb01b0fff2ee569d236747e80764442246f1e00bdbc34f658e04847956c3e323a151ebd6f9306aa2214d3f4b5a7ee7142629a1fc727c0ece80642b8d1774fd7f36f2e7fb997cbc7c8f9e135c80e06dd63905e3d4b0de5b1a4a6e143d&type=fff264_180'
		},
		{
			name: '뷔(김태형)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F6e0bcfbe998a033312f0bf704f9880b48048bd960bacafe27ddc7080a9f3149e15ff59e4182c0436b95eed13012fc28c6d0219557cec1f7f9beb2438d64f36072cf50e4e513bb5ed4f3d62aba528f3b06d3edc89d5011d62961f04dc6d60fdf8&type=fff264_180'
		},
		{
			name: '정국(진정국)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F7619aa7dee9b330bef6e184fdb6f8ae4bdaa49915f6cb516266b5b8a7e814e65b39931cb4baad7cffcd61927d0da0e2fa1950e3776e84a339b8c8d3ff943cc25697f4ec55a52b07869b7a2fe04fbe508b4145fc8b26dcb5d437d34e734ebefed&type=fff264_180'
		},
	];
</script>

{#each bts as item}
<h3><img src="{item.img}" alt=""></h3>
<h3>이름 : { item.name }</h3>
<h3>출생 : { item.birth }</h3>
<hr>
{/each}
```

\- 위의 객체는 배열로 담아 값들을 나열시켰습니다.

\- [ bts ]라는 변수에 값을 전부 담았습니다.

\- img값은 네이버에서 검색해서 나무 위키에 사용된 이미지 주소를 복사해왔습니다. 

\- 위 마크업 부분에 나온 {#each} 부분은 상태 값이 배열일 때 반복해서 출력해주는 문법입니다.

\- 지금은 맛보기로만 확인한 것이니 추후 반복 블록에서 다시 공부하겠습니다.

 

 

 

#### **## 결과 보기**



![img](.\images\lT3SxK07YPsnmszKiWsAM1_img.gif)



\- 배열돼서 브라우저에 잘 표시된 것을 확인할 수 있습니다. 



---

## 4강 Reactivity(반응성)

 

** 이번 강좌에서는 Svelte의 Reactivity(반응성)에 대해서 알아보겠습니다. 반응성이란 값이 변할 때 별다른 호출 없이 값이 같이 변하는 것을 말합니다. 당연한 말같이 들리겠지만, 다른 프레임워크를 통해 이 기능을 쓰려면 훨씬 더 복잡한 코드를 씁니다. 하지만 Svelte는 간단히 사용이 가능해집니다. 거기에는 [ $: ]라는 반응성 코드가 있습니다. 

 

## **1. 삼항연산자를 통한 마크업에서의 반응성**

------

\- 일단 삼항연산자가 무엇인지 보도록 하겠습니다.

 

 

#### **## 삼항연산자 문법**

```js
조건식?참표현식:거짓표현식;
```

\- 연산되는 피연산자가 3개라는 의미로 삼항 연산자라고 부르지만, 실제로는 조건문입니다.

\- 조건식이 맞으면 참표현식을 실행하고, 그렇지 않으면 거짓 표현식을 실행합니다.

 

 

#### **## 문제**

```
버튼을 클릭하면 숫자가 1씩 자동으로 증가되게 처리하시오.
단) 숫자가 1이면 [time]을 뒤에 붙이고, 그렇지 않으면 [times]를 붙이시오.
ex) [clicked : 2 times]
```

\- 숫자에 따라 뒤의 문자가 반응하는 코드를 작성해보겠습니다.

 

 

#### **## 코드 보기**

```js
<script>
	let count = 0;

	function handleClick() {
		count++;
	}
</script>

<button on:click={handleClick}>
	Clicked {count} {count === 1 ? 'time' : 'times'}
</button>
```

 

 

#### **## 결과 보기**



![img](.\images\lkNiDewZ554KUKpEwc35C0_img.gif)



\- 1일때만 time이고 클릭하면 숫자가 바뀌면서 뒤의 문자도 반응하여 바뀌게 됩니다. 

  

## **2. 스크립트 반응성 코드 - $:**

------

\- 변수로 선언된 값을 참조하는 새로운 변수를 만들 때 앞에 붙여주면 됩니다.

\- 이때 var, let, const 등의 키워드를 쓰지 않는다는 것을 기억해두세요.

\- 변수 값이 변경되면 자동으로 감지하여 자동으로 함께 변경이 됩니다.

 

### **1) 변수 선언형 - $:**

#### **## 문법**

```js
$: 반응성변수 = 값;
```

 

 

#### **## 코드 보기**

```js
<script>
	//변수선언
	let count = 1;

	//반응성 변수선언
	$: doubled = count * 2;
	$: square = count * count;


	function handleClick() {
		count++;
	}
</script>

<button on:click={handleClick}>
	클릭 : {count} 
</button>

<h3>두배구하기</h3>
<p>{count} × 2 = {doubled}</p>
<h3>제곱구하기</h3>
<p>{count} × {count} = {square}</p>
```

\- count라는 변수를 참조하는 반응성 변수를 [ double ]과 [ square ]를 선언했습니다. 

\- 그 값들이 마크업되서 문서에 출력되게 처리합니다.

 

 

 

#### **## 결과 보기**



![img](.\images\Cna5PfNkCsRZwHAq6bejbK_img.gif)



\- 클릭과 함께 하단의 p태그의 값들도 반응해서 잘 바뀝니다. 

 

### **2) $: 의 그룹화**

\- 코드 블록을 통해 그룹화할 수도 있습니다. 

\- 블록으로 감싼 부분도 상태 값이 변경되면 자동으로 호출합니다. 

 

#### **## 문법**

```js
$: {
	//그룹화할 코드 작성
}
```

 

 

#### **## 코드 보기**

```js
<script>
	//변수선언
	let count = 1;

	//반응성 변수선언
	$: doubled = count * 2;
	$: square = count * count;

	//그룹화된 명령
	$: {
		console.log('두배값 : ' + doubled);
		console.log('제곱값 : ' + square);
	}

	function handleClick() {
		count++;
	}
</script>

<button on:click={handleClick}>
	클릭 : {count} 
</button>

<h3>두배구하기</h3>
<p>{count} × 2 = {doubled}</p>
<h3>제곱구하기</h3>
<p>{count} × {count} = {square}</p>
```

\- 중간에 그룹화된 명령으로 각각의 반응성 변숫값을 그룹화해서 콘솔 창에 띄워보도록 하겠습니다. 

 

 

#### **## 결과 보기**



![img](.\images\gvEq6Zaj6aVySLhnJSkEnk_img.gif)



\- 콘솔창의 값들도 클릭할 때마다 반응해서 계속 처리되는 것을 확인할 수 있습니다. 

  

### **3) $: 조건절**

\- 반응성 코드에 조건절, 즉 if문을 사용해서 반응하게 처리할 수 있습니다.

 

#### **## 문법**

```js
$: if(조건){ }
```

 

 

 

#### **## 코드 보기**

\- 물품을 1~9개까지만 구매한다고 보도록 하겠습니다.

```js
<script>
	//변수선언
	let count = 1;

	//조건절
	$: if(count >= 10){
		alert('10개이상 구매할 수 없습니다.');
		count = 9;
	}
	$: if(count <= 0){
		alert('최소구매개수는 1개입니다.');
		count = 1;
	}


	function plusHandle() {
		count++;
	}
	function minusHandle() {
		count--;
	}
</script>

<button on:click={minusHandle}>-</button>
<input type="text" value="{count}" style="width: 25px;">
<button on:click={plusHandle}>+</button>
```

\- 마이너스 버튼을 클릭하면 1씩 감소하게 처리, 플러스 버튼을 클릭하면 1씩 증가하게 처리하겠습니다.

\- 10이상으로 증가하지 못하게 반응성 코드로 경고창과 count의 상태 값을 컨트롤했습니다.

\- 0이하로 감소하지 못하게 반응성 코드로 경고창과 count의 상태 값을 컨트롤했습니다. 

 

 

 

#### **## 결과보기**



![img](.\images\qtfjXJoZHL3uLn7xI2BqKK_img.gif)



\- 9에서 플러스 버튼을 누르니, 경고창이 뜨고 증가되지 않는 것이 보입니다.

\- 1에서 마이너스 버튼을 누르니, 경고창이 뜨고 감소되지 않는 것이 보입니다. 

 

---

## 5강 Svelte Event

## **1. Svelte 이벤트 문법**

------

\- 이벤트(event)는 자바스크립트가 명령을 주는 시점을 말합니다. 

\- 보통 마우스를 클릭했을 때, 키보드를 눌렀을 때, 스크롤바를 움직일 때와 같이 ~했을 때가 이벤트입니다.

  

## **1. 바인딩(Binding)이란?**

------

\- 바인딩은 상태 값과 그 값을 제어하는 요소와의 결합을 의미합니다.

\- 보통 데이터를 입력하는 다양한 폼(input, select, textarea 등)과 함께 사용됩니다.

\- svelte는 양방향 바인딩을 이용해서 동시에 값이 변경되게 처리됩니다.

\- 지난 [ 7강 if block ]에서 입력 상자에 값을 입력하면 반환되는 것을 [ keyup ] 이벤트를 통해 강제로 발생시켰는데, 사실 그렇게 작업할 필요가 없이 svelte는 바인딩이 잘 되어 있습니다. 

\- 바인딩은 다른 요소와 컴포넌트에도 가능하지만 이번 강좌에서는 form관련 바인딩만 보도록 하겠습니다. 

 

#### **## 문법**

```js
<태그요소 bind:태그속성={상태값}>
```

\- 보통 위의 형태로 작성한다고 보면 됩니다.

 

### **1) input text value 바인딩**

\- 입력상자에 입력한 값이 p태그에 바로 반환되게 처리해보겠습니다.

 

#### **## 코드 보기**

```js
<script>
	let name = '';
</script>

<input type="text" bind:value={name} placeholder="이름을 입력하세요.">
<p>안녕! {name || '낯선 사람'}!</p>
```

\- <input> 태그에 bind:속성명={ 상태 값} 으로 잘 연결해줬습니다. 

\- 처음부터 값이 비어있는 것을 방지하기 위해 논리합 연산자( || )로 name이 없는 경우 낯선 사람으로 인식하게 처리했습니다.

 

#### **## 결과 보기**



![img](.\images\i2dOAh1MKPlHRomd18tcM0_img.gif)



\- 처음엔 [낯선 사람]으로 되어 있다가 이름을 바꾸면 실시간으로 데이터가 변경되는 것을 확인할 수 있습니다. 

  

### **2) input number/range value 바인딩**

\- 이번에는 숫자를 지정하는 number타입과 범위가 결정되는 range타입으로 데이터가 바인딩되는 것을 보겠습니다.

 

#### **## 코드 보기**

```js
<script>
	let a = 1;
	let b = 2;
</script>

<label>
	<input type=number bind:value={a} min=0 max=10>
	<input type=range bind:value={a} min=0 max=10>
</label>

<label>
	<input type=number bind:value={b} min=0 max=10>
	<input type=range bind:value={b} min=0 max=10>
</label>

<p>{a} + {b} = {a + b}</p>
```

\- 이번에는 변수를 2개 선언해서 상태 값을 2개를 바인딩해보겠습니다.

\- <input> 태그들의 value 속성에 bind 해보겠습니다.

 

#### **## 결과 보기**



![img](.\images\qxyI99z8EUjaIoMkuGaOjk_img.gif)



\- number타입의 숫자를 바꿔도 버튼을 눌러도 데이터가 실시간으로 잘 변경되는 것이 확인됩니다. 

 

### **3) input checkbox checked 바인딩**

\- 이번에는 체크 상황에 따라 바인딩 처리를 해보도록 하겠습니다.

\- [ bind:checked ]를 통해 상태 값에 바인딩 처리가 가능합니다.

  

#### **## 코드 보기**

```js
<script>
	let chk = false;
</script>

<label>
	<input type="checkbox" bind:checked={chk}>
	약관 동의
</label>

{#if chk}
	<p>당신은 약관에 동의했습니다.<br>이제 구독이 가능합니다.</p>
{:else}
	<p>당신은 약관에 동의하지 않았습니다.<br>아직 구독이 불가능합니다.</p>
{/if}

<button disabled={!chk}>
	구독
</button>
```

\- [ chk ] 변수에는 true/false 가 담기도록 설정하고, 초기값은 false로 지정해줍니다. 

\- 그리고 <input type="checkbox"> 에 [ bind:checked={chk} ]로 [ chk ] 를 상태 값으로 지정해줍니다.

\- [ if block ]을 통해서 체크상태에 따라 반환받을 <p> 태그를 따로 지정해줍니다.

\- <button> 태그의 disabled 상태 역시 [ chk ] 상태값으로 지정해줍니다. 대신 부정 연산자를 사용하여 false 일 때 사용 불가능하게 처리해줍니다. 

 

#### **## 결과 보기**



![img](.\images\90yk130Assn2wkDGsrqKk0_img.gif)



\- 약관 동의 옆 체크박스를 체크하면 구독 버튼이 클릭되고, 미체크되면 구독 버튼이 클릭되지 않는 것이 잘 확인됩니다.

 

### **4) input checkbox/radio 그룹 바인딩**

\- 보통 체크박스나 라디오 버튼은 체크되는 요소가 그룹으로 처리됩니다.

\- 특히 라디오버튼 같은 경우는 name 속성을 통해 하나의 그룹으로 체크됩니다.

\- 이때 svelte에서 사용되는 바인딩이 [ bind:group={상태값} ]입니다.

\- 이 바인딩으로 같이 사용되는 폼들의 상태 값을 공유하게 됩니다.

\- 아래 코드로 더 자세히 공부해보도록 하겠습니다. 

 

 

#### **## 코드 보기**

\- 커피 사이즈를 선택하는 라디오 버튼을 만들어 보겠습니다.

```js
<script>
	let choiceSize = 0;
	let sizes = ['Tall','Grande','Venti'];
</script>

<h3>사이즈 선택</h3>
{#each sizes as size, i}
	<label>
		<input type="radio" bind:group={choiceSize} value={i}>
		{ size }
	</label>
{/each}

<p>고객님은 {sizes[choiceSize]}를 선택하셨습니다.</p>
```

\- 일단 [ choiceSize ]는 라디오 버튼이 그룹으로 묶일 상태 값입니다. 초기값은 0으로 줬는데 추후 반복을 통해 인덱스 번호로 value 속성값을 처리할 거라서 그렇습니다.

\- [ sizes ] 는 each block 으로 반복할 거라 배열로 이름을 지정해줬습니다. 

\- each block 안에 라디오버튼들을 반복해줍니다. 이때 같은 그룹화로 상태 값을 바인딩할 거라 [ bind:group={choiceSize} ] 입을 처리해줍니다.

\- 그리고 하단 <p> 태그에 선택한 사이즈를 상태 값으로 출력해줍니다. 

  

#### **## 결과 보기**



![img](.\images\wBc0PB82EHLn2MAL2n8pG0_img.gif)



\- 초기값을 0으로 지정했기 때문에 Tall로 처음에 잘 선택됩니다.

\- 라디오 버튼 체크에 따라 상태 값이 자동으로 변경되는 것이 잘 확인됩니다. 

 

### **5) select value 바인딩**

\- <select> 태그는 보통 옵션 중 하나의 값을 선택하는 선택 상자입니다. 

\- 옵션 중 선택한 값의 value 가 전체 <select> 태그의 value 이기도 합니다. 

\- 그 개념을 잘 이해하면 [ bind:value={상태값} ]에 대한 이해도 쉬울 것 같습니다.

\- 아래 예제를 통해서 더 자세히 배워 보겠습니다. 

 

 

#### **## 코드 보기**

\- 포털사이트를 선택 상자에 담아 옵션을 선택 시 새창으로 띄우는 것을 만들어 보겠습니다.

```js
<script>
	let portals = [
		{ name: '사이트선택', url: null },
		{ name: '네이버', url: 'https://naver.com' },
		{ name: '다음', url: 'https://daum.net' },
		{ name: '구글', url: 'https://google.com' }
	];
	let selected;

	function selectChange(){
		if(selected != null){
			window.open(selected);
		}
	}
</script>

<h3>포털 사이트 바로가기</h3>
<select bind:value={selected} on:change={selectChange}>
	{#each portals as portal}
		<option value={portal.url}>{ portal.name }</option>
	{/each}
</select>
```

\- [ portals ]에 객체 방식으로 데이터를 배열로 담았습니다. 

\- 그중 name 속성은 <option> 태그의 텍스트 콘텐츠로 담길 것이고, url 속성은 <option> 태그의 value값으로 상태 값을 처리하겠습니다. 

\- [ selected ]는 <select> 태그가 선택하는 value값이 담길 상태값 변수입니다. 

\- [ selectChange ] 함수는 값이 null 이 아니라면 선택된 value값으로 새창을 띄울 함수인데, 이것은 <select> 태그의 on:change 이벤트로 적용합니다. 

\- 옵션 태그들을 역시 반복해서 담아줘야겠죠?

  

**## 결과 보기**



![img](.\images\Hew9x5UFSnKUEbQQ5MkqR1_img.gif)



\- 브라우저에서 옵션을 변경하면 그에 맞는 사이트로 잘 바뀝니다.

\- 사이트 선택을 다시 선택하면 새창은 뜨지 않습니다. 

 

 

 

### **1) 이벤트 문법**

#### **## Svelte 이벤트 기본 사용방법**

```
on:이벤트명 = { 이벤트함수 }
```

\- 처음 강좌부터 사실 클릭이벤트를 사용해 봤습니다. 이번 강좌에서는 이벤트를 디테일하게 배워보도록 하겠습니다.

 

### **2) 이벤트의 종류**



![img](.\images\BZDSpEhXvSNk6iS8QFkGqK_img.gif)



\- Svelte 확장 플러그인을 통해 [ on: ]만 작성해도 사용 가능한 이벤트가 쫙~ 뜹니다.

\- 이벤트가 엄청 많은 것이 확인됩니다. 하지만 저것은 모두 자바스크립트 관련 이벤트가 대부분이기 때문에 그쪽에서 공부가 잘 되어 있다면 어렵지 않습니다. 그래도 꼭 기억하면 좋을 이벤트를 정리하고 가겠습니다. 

 

#### **## 꼭 기억해야 할 이벤트 종류**

| **이벤트명**   | **설명**                               |
| -------------- | -------------------------------------- |
| **click**      | 요소에서 마우스를 클릭했을 때          |
| **mouseenter** | 요소에 마우스가 들어갔을 때            |
| **mouseleave** | 요소에서 마우스가 떠났을 때            |
| **mousemove**  | 요소에서 마우스를 움직일 때            |
| **keydown**    | 키보드를 눌렀을 때(특수키 인식)        |
| **keyup**      | 눌렀던 키에서 뗄 때                    |
| **keypress**   | 키보드를 눌렀을 때(특수키 미인식)      |
| **scroll**     | 페이지 스크롤바의 이동이 발생되었을 때 |
| **resize**     | 브라우저 창 크기가 변경되었을 때       |

\- 이외에도 많은 이벤트가 있지만, 최소 위의 이벤트들을 꼭 외워 주세요.

\- 기본적으로 JS을 공부하셨다면, 다 숙지하고 있을 이벤트입니다.

 

### **3) DOM 이벤트 사용**

#### **## 코드 예시**

\- 요소에서 마우스를 움직였을 때 마우스 좌표 구하기

```js
<script>
	let m = { x: 0, y: 0 };

	function handleMousemove(event){
		m.x = event.clientX;
		m.y = event.clientY;
	}
</script>

<div on:mousemove={handleMousemove}>
	x좌표 : { m.x } <br>
	y좌표 : { m.y }
</div>

<style>
	div{ width: 100%; height: 100%; }
</style>
```

\- m이라는 변수에 객체 선언 방식으로 속성에 각각 값을 0으로 초기 상태 값을 담습니다.

\- 함수에 마우스 이벤트 객체인 [ event ]를 매개변수로 작성해줍니다.

\- 각각의 x좌표, y좌표 값을 [ m.x ]와 [ m.y ]에 담아줍니다. 

\- 그리고 div태그에 mousemove 이벤트를 실행해주면 div태그에서 마우스를 움직일 때 좌표값을 처리해줍니다.

\- 스타일에서 div태그가 body영역을 전부 상속받게 처리해줍니다. 

 

 

#### **## 결과 보기**



![img](.\images\oSGfpQtJUJ7U58bqel2EaK_img.gif)



\- 마우스를 움직이면 좌표값이 상태 값으로 실시간으로 잘 바뀌는 것이 확인됩니다. 

 

### **4) inline 이벤트 사용**

\- 이벤트를 제어할 경우 보통 함수를 만들어 이벤트와 연동하는 것이 보통입니다.

\- 하지만 간단한 작업은 화살표 함수를 사용해서 마크업 영역에 인라인 형태로 사용할 수 있습니다. 

\- 위에서 마우스 움직이던 코드를 inline형태로 사용해보겠습니다.

 

#### **## 코드 보기**

```js
<script>
	let m = { x: 0, y: 0 };
</script>

<div on:mousemove={e => m = { x: e.clientX, y: e.clientY }}>
	x좌표 : { m.x } <br>
	y좌표 : { m.y }
</div>

<style>
	div{ width: 100%; height: 100%; }
</style>
```

\- <script> 태그 내부에 넣었던 함수를 화살표 함수로 교체했습니다.

 

 

 

#### **## 결과 보기**



![img](.\images\QZoESNpkTNjAZEQe12zjEk_img.gif)



 

### **5) 함수 매개변수 사용**

\- 함수 매개변수를 이벤트로 전달할 경우 꼭 화살표 함수를 사용해야 합니다.

 

#### **## 문법**

```js
//매개변수가 없을 경우
on:click={함수명}

//매개변수가 있을 경우
on:click={() => 함수명(매개변수)}
```

 

 

#### **## 코드 보기**

```js
<script>
	function handleClick01(text){
		alert(`${ text } 클릭!`);
	};

	function handleClick02(text){
		alert(`${ text } 클릭!`);
	};
</script>

<button on:click={() => handleClick01('1번')}>첫번째 버튼</button>
<button on:click={() => handleClick02('2번')}>두번째 버튼</button>
```

\- 함수에 매개변수 text를 사용했습니다. 

\- 매개변수를 alert() 함수에서 문자열로 받아올 때 따옴표가 아닌 [ ` ](Grave accent-역 엑센트)를 사용해줍니다. 

\- 매개변수를 이벤트로 가져올 때 화살표 함수를 꼭 사용해야 합니다. 

```js
<button on:click={() => handleClick01('1번')}>첫번째 버튼</button> //맞음

<button on:click={handleClick01('1번')}>첫번째 버튼</button> //틀림
```

 

 **## 결과 보기**



![img](.\images\DVkc82UwqGkIdjA6h7rQdK_img.gif)



 

## **2. Event Modifiers(이벤트 수식어)**

------

\- Svelte는 이벤트를 제어할 때 조건을 붙이는 수식어도 함께 사용할 수 있습니다. 

\- 대표적으로 once 가 있는데, 단어 뜻 그대로 이벤트가 한 번만 발생하도록 합니다.

  

#### **## 문법**

```js
on:이벤트명|수식어={ }
```

\- 이벤트명 뒤에 [ | ](파이프문자)를 통해 수식어를 붙일 수 있습니다. 

 

### **1) Event Modifiers의 종류**

| **수식어**          | **설명**                                                     |
| ------------------- | ------------------------------------------------------------ |
| **preventDefault**  | 'e.preventDefault()' 를 호출. 이벤트가 발생한 태그의 기본 이벤트를 막음. |
| **stopPropagation** | 'e.stopPropagation()' 을 호출. 발생한 이벤트가 겹쳐진 상위 요소로 전달되지 않게 막음. |
| **passive**         | 터치 혹은 휠 이벤트로 발생하는 스크롤 성능을 향상시킴.       |
| **capture**         | 버블링 단계가 아닌 캡처 단계에서 이벤트 핸들러를 실행함.     |
| **once**            | 이벤트 핸들러를 단 한번만 실행하도록 함.                     |
| **self**            | 'e.target'과 이벤트 핸들러를 정의한 요소가 같은 때 이벤트 핸들러를 실행하도록 함. |

 

### **2) once 수식어 사용**

\- 위에서도 설명했다시피, once는 이벤트를 한 번만 발생시키는 수식어입니다. 

 

#### **## 코드 보기**

```js
<script>
	function handleClick(){
		alert('클릭은 한번만 제공합니다.');
	};
</script>

<button on:click|once={handleClick}>클릭1</button>
<button on:click|once={handleClick}>클릭2</button>
```

\- 이벤트 타입명 옆에 once 를 붙여 클릭 이벤트를 한 번만 실행하도록 처리합니다. 

 

 

#### **## 결과 보기**



![img](.\images\9JUb2qOJiq84Vrfuok4km0_img.gif)



\- 버튼 한 개당 한 번의 경고창만 반환하는 것을 확인할 수 있습니다. 

 



---

##  6강 Svelte Props 

## **1. Props**

------

\- [**props**]는 [**properties**]를 줄임말입니다.

\- 부모 컴포넌트가 자손컴포넌트에게 전달해주는 값이라고 생각하면 편합니다.

  

### **1) Props 기본 사용방법**

\- 부모컴포넌트는 기본적으로 만들어져 있는 [ App.svelte ]로 사용하겠습니다. 

\- 자손컴포넌트인 [ Child.svelte ]는 새 문서를 만들어서 사용해주세요. 

 

#### **## 코드 보기 - Child.svelte**

```js
<script>
    //여기는 자손컴포넌트

    export let user;
    export let age;
    export let hobby;
</script>

<h3>이름 : { user }</h3>
<h3>나이 : { age }</h3>
<h3>취미 : { hobby }</h3>
<hr>
```

\- 자손 컴포넌트에서 사용한 상태값은 export 를 통해서 부모컴포넌트로 상태값을 내보내줍니다.

 

 

 

#### **## 코드 보기 - App.svelte**

```js
<script>
	//여기는 부모컴포넌트
	import Child from "./Child.svelte";
</script>

<Child user={'김철수'} age={20} hobby={'축구하기'} />
<Child user={'이영희'} age={21} hobby={'넷플릭스보기'} />
```

\- 부모 컴포넌트인 [ App.svelte ]로 자손 컴포넌트를 import 를 통해 불러 옵니다.

\- <Child /> 컴포넌트에 속성처럼 작성해서 자손컴포넌트에 값을 전달해줍니다.

```js
Props이름 = { 전달값 }

Props이름="전달값" //이렇게 리액트처럼 써도 잘 나옵니다.
```

 

  

**## 결과 보기**



![img](.\images\yZhFhc7FgFkBtN60IBilSK_img.png)



\- 그럼 두명이 같은 태그 스타일로 잘 나오는 것이 확인됩니다. 

 

### **2) 기본값\**(Default)\** 설정**

\- 부모 컴포넌트에서 값을 내려주지 않으면 [undefined]가 처리됩니다.

\- 그래서 기본값을 설정해주면 깜박해서 값을 내려주지 않더라도 기본값으로 설정됩니다.

\- 기본값 설정은 자손 컴포넌트에서 export 되는 변수값에 작성하면 됩니다. 

 

 **## 코드 보기 - Child.svelte**

```js
<script>
    //여기는 자손컴포넌트

    export let user = "홍길동";
    export let age = 20;
    export let hobby = "독서";
</script>

<h3>이름 : { user }</h3>
<h3>나이 : { age }</h3>
<h3>취미 : { hobby }</h3>
<hr>
```

\- 변수값에 모두 기본값을 지정했습니다.

 

 

#### **## 코드 보기 - App.svelte**

```js
<script>
	//여기는 부모컴포넌트
	import Child from "./Child.svelte";
</script>

<Child user={'김철수'} age={20} hobby={'축구하기'} />
<Child user={'이영희'} age={21} hobby={'넷플릭스보기'} />
<Child />
```

\- 마지막 컴포넌트에는 Props로 값을 내려주지 않고 컴포넌트명만 작성했습니다. 

 

 

#### **## 결과 보기**



![img](.\images\gY6swzovrSILnDi7DBs8Q0_img.png)



\- 마지막 3번째에 값들이 기본값으로 잘 들어가 있는 것이 확인됩니다. 

 

## **2. Props 데이터 변경**

------

\- Props는 부모컴포넌트가 값을 자손 컴포넌트에 내려줍니다.

\- 즉, 자손 컴포넌트는 전달되는 값을 변경할 수 없고, 오직 부모 컴포넌트에서만 변경이 가능하다는 것을 의미합니다.

\- 그래서 값을 변경할 때는 부모컴포넌트에서 변경한 후 다시 자식 컴포넌트로 넘기면 됩니다. 

 

### **1) 데이터 변경하기**

\- 위의 데이터에서 기존 사람들의 나이를 1씩 감소, 증가해보겠습니다.

 

#### **## Child.svelte**

```svelte
<script>
    //여기는 자손컴포넌트

    export let user = "홍길동";
    export let age = 20;
    export let hobby = "독서";
</script>

<h3>이름 : { user }</h3>
<h3>나이 : { age }</h3>
<h3>취미 : { hobby }</h3>
<hr>
```

\- 자식 컴포넌트는 사람에 대한 데이터 설명을 해두었습니다.

 

 

 

#### **## App.svelte**

```svelte
<script>
	//여기는 부모컴포넌트
	import Child from "./Child.svelte";

	let age01 = 20;
	let age02 = 21;
	
	const agePlus = () => {
		age01++;
		age02++;
	}
	const ageMinus = () => {
		age01--;
		age02--;
	}
</script>

<Child user={'김철수'} age={ age01 } hobby={'축구하기'} />
<Child user={'이영희'} age={ age02 } hobby={'넷플릭스보기'} />

<button on:click={ageMinus}>나이감소-</button>
<button on:click={agePlus}>나이증가+</button>
```

\- 부모 컴포넌트에서는 자손컴포넌트를 가져온 후, 각각의 나이값을 변수에 담아두고 함수를 통해 1씩 증가하거나, 감소하게 처리했습니다. 

\- 위에서 설명했듯이 값은 부모컴포넌트에서 변경해야 합니다.



#### **## 결과보기**



![img](.\images\WgGu8DeSiygKNXOcjT7oQK_img.gif)



 

## **3. Spread Props**

------

#### **## 스프레드 오퍼레이터(Spread Operator)**

\- 한글로 번역해보자면 펼침연산자 정도로 볼수 있습니다. 

\- 특정, 객체나 배열의 값을 복제할 때 사용합니다.

```
var 새로운객체명 = {...기존객체명};
var 새로운배열명 = [...기존배열명];
```

\- 보통 자바스크립트의 비구조화할당과 많이 사용됩니다.

\- 비구조화할당이 익숙하지 않은 개발자라면 아래 주소를 보고 오시길 바랍니다.

https://ossam5.tistory.com/161?category=921603 

 

[ES6강좌] 5강 비구조화 할당(destructuring assignment) - 오쌤의 니가스터디

1. 배열의 비구조화할당(destructuring assignment) - 비구조화할당이란 배열이나 객체의 속성 혹은 값을 해체하여 그 값을 변수에 각각 담아 사용하는 자바스크립트 표현식입니다. 1) 기존 배열 값을

ossam5.tistory.com

 

#### **## 문법**

```svelte
const 객체명 = {
	//Child컴포넌트에서 내보낸 변수를 속성으로 값을 할당
    자손변수: 값
}

<Child컴포넌트명 { ...객체명 } />
```

 

#### **## Child.svelte - 코드보기**

```svelte
<script>
    //여기는 자손컴포넌트

    export let user;
    export let age;
    export let hobby;
</script>

<h3>이름 : { user }</h3>
<h3>나이 : { age }</h3>
<h3>취미 : { hobby }</h3>
<hr>
```

\- 자손컴포넌트는 위와 다르지 않습니다. 각각의 변수로 값을 부모컴포넌트로 내보내줍니다. 

 

 

#### **## App.svelte - 코드보기**

```svelte
<script>
	//여기는 부모컴포넌트
	import Child from "./Child.svelte";

	const allData01 = {
		user: '김철수',
		age: 20,
		hobby: '축구하기'
	}
	const allData02 = {
		user: '김영희',
		age: 21,
		hobby: '넷플릭스보기'
	}
</script>

<Child { ...allData01 } />
<Child { ...allData02 } />
```

\- 원래 태그의 속성으로 처리했던 Props를 객체문법을 통해 담아줍니다. 이때 객체의 속성명은 반드시 자손컴포넌트에서 내보낸 변수명이어야 합니다.

\- 그리고 자손컴포넌트인 <Child /> 의 중괄호{ } 안에 스프레드오퍼레이터를 쓴 후 객체명을 써줍니다. 

  

#### **## 결과 보기**



![img](.\images\Njf9JYYx5c9EQJH4uIbwDk_img.png)



\- 역시 결과 도출이 잘 되는 것을 확인할 수 있습니다. 

 

 

---

## 7강 Logic - if blocks

## **1. if block(논리 블록)** 

------

\- 컴퓨터 프로그램을 이루는 요소 중 대표적인 구문이 바로 조건문과 반복문입니다.

\- 기본적으로 HTML은 위에서 말한 두 구문이 없지만, 자바스크립트는 두 구문을 가지고 있습니다.

\- 반복 관련은 다음 강좌에서 보고, 이번 강좌에서는 조건문 관련만 보도록 하겠습니다.

\- svelte에서는 마크업 영역에서 조건을 사용할 수 있는 [ if block ]이라는 것을 제공합니다. 

 

### **1) if block**

\- [ if block ] 중 기본적인 구문입니다.

\- 조건이 true일 경우에만 마크업 영역을 표시합니다.

 

#### **## 문법**

```svelte
{#if 조건}
	<!--조건이 true일때 마크업 표시-->
{/if}
```

 

 

#### **## 코드 보기**

\- 이번 코드는 버튼을 토글 상태로 만들겠습니다. [로그인/로그아웃]

\- 보통 홈페이지들이 로그인을 하면 로그인한 정보를 볼 수 있습니다. 

\- [로그인]을 누르면 [로그아웃]으로 바뀌고, [로그아웃]을 누르면 [로그아웃]되었다고 만들도록 하겠습니다. 

\- 그리고 하단에 [현재 로그인 상태입니다./현재 로그아웃 상태입니다.]라는 텍스트로 추가해볼게요.

```svelte
<script>
	let user = { loggedIn: false };

	function toggle() {
		user.loggedIn = !user.loggedIn;
	}
</script>

{#if user.loggedIn}
	<button on:click={toggle}>
		로그아웃
	</button>
	<p>현재 로그인상태입니다.</p>
{/if}

{#if !user.loggedIn}
	<button on:click={toggle}>
		로그인
	</button>
	<p>현재 로그아웃상태입니다.</p>
{/if}
```

\- user 에는 객체 형태로 상태값으로 loggedIn 이라는 것을 처리했습니다. 

\- 그리고 loggedIn 은 true/false를 갖는 불 표현식으로 데이터를 처리할 거고, 초기값은 false 로 지정했습니다. 

\- toggle() 메서드를 통해 loggedIn 을 true/false로 변경할 수 있게 작성했습니다.

\- 마크업 영역에는 loggedIn 이 true인 경우와 false인 경우가 다르게 나타나도록 if block 을 사용했습니다. 

 

####  

#### **## 결과 보기**



![img](.\images\HUDkqZ5eKRZeG2QUQS66w0_img.gif)



\- 실행해보면 상태 값에 따라 결과가 달라지는 것이 확인됩니다.

 

### **2) else block**

\- if문은 보통 else문과 같이 많이 사용됩니다. 

\- if파트는 보통 true인 경우를 표시, else인 경우는 false인 것을 표시하죠?

\- svelte의 if block 도 else 문을 지원합니다. 

 

 

#### **## 문법**

```svelte
{#if 조건}
	<!--조건이 true일 때 마크업표시-->
{:else}
	<!--조건이 false일 때 마크업표시-->
{/if}
```

 

 

#### **## 코드 보기**

\- 위의 코드에서는 false 인 경우를 표시하기 위해 논리 연산자 중 부정 연산자(!)를 사용해서 false 를 사용했습니다.

\- 이번에는 else 를 사용해서 한번 [로그인/로그아웃]을 만들어 보겠습니다. 

```svelte
<script>
	let user = { loggedIn: false };

	function toggle() {
		user.loggedIn = !user.loggedIn;
	}
</script>

{#if user.loggedIn}
	<button on:click={toggle}>
		로그아웃
	</button>
	<p>현재 로그인상태입니다.</p>
{:else}
	<button on:click={toggle}>
		로그인
	</button>
	<p>현재 로그아웃상태입니다.</p>
{/if}
```

\- 바뀌 부분은 [ {/if} {#if !user.loggedIn} ] 이 두 줄이, [ {:else} ] 한줄로 바뀌었습니다.

 

 

 

#### **## 결과 보기**



![img](.\images\icLkq63TDmSWVh7kFaA0e1_img.gif)



\- 결과는 위와 크게 다르지 않은 것이 확인됩니다. 

 

### **3) else if block**

\- 결과가 [ true/false ]로만 나뉘는 일은 많이 없습니다.

\- 물론, 남/여, 수신/미수신 같은 것들이 있긴 하지만 대부분은 많은 결과로 나뉩니다. 

\- 그런 구문이 바로 if else if 문입니다. if를 여러 번 쓴다고 해서 다중 if라고도 합니다.

\- svelte도 역시 [ else if block ]을 제공합니다. 

 

#### **## 문법 보기**

```svelte
{#if 조건1}
	<!--조건1이 true일 때 마크업표시-->
{:else if 조건2}
	<!--조건2가 true일 때 마크업표시-->
{:else}
	<!--조건1과 조건2가 false일 때 마크업표시-->
{/if}
```

\- [ {:else if 조건} ]은 여러 개 써도 됩니다. 

 

#### **## 코드 보기**

\- 이번에는 input상자에 입력한 숫자가 양수인지, 음수인지, 0인지, 그 외의 값을 적었는지 보도록 하겠습니다.

```svelte
<script>
	let x = null;

	function numChange(e){
		x = e.target.value;
	}
</script>

<label for="testbBox">양수/음수 테스트</label>
<input type="text" id="testbBox" on:keyup={numChange} placeholder="정수를 입력하세요.">

{#if x > 0}
	<p>작성한 숫자는 양수입니다.</p>
{:else if x < 0}
	<p>작성한 숫자는 음수입니다.</p>
{:else if x === '0'}
	<p>작성한 숫자는 0입니다.</p>
{:else}
	<p>정수로 다시 입력하세요.</p>
{/if}
```

\- 변수 x에는 상태값을 받을 예정입니다. 처음 값은 비워두기 위해 [ null ] 키워드를 사용했습니다.

\- 그리고 numChange() 메서드를 만들어 input상자에 입력한 값으로 상태 값이 바뀌게 처리했습니다.

\- 마크업 부분에 [ on:keyup ]이벤트로 numChange() 메서드를 활성화시켰습니다.

\- 사실 이렇게 쓰지 않아도, svelte는 바인딩(Binding)기능이 있습니다. 하지만 아직 배우지 않았으니 이렇게 해볼게요.

\- 그리고 하단 부분에 [ if block ]들을 사용하여 입력된 상태 값에 따라 결과를 다르게 도출하게 p태그를 작성했습니다.

\- 특히, 조건 중 0은 문자열로 처리해야 하고 완전 항등 연산자( === )를 사용해야 합니다.

\- 안 그러면, 값을 비웠을 때도 0의 결과와 같게 처리되게 때문입니다.

\- 입력 상자에 적는 숫자는 숫자 데이터가 아니란 점도 잘 기억하세요.

 

 

#### **## 결과 보기**



![img](.\images\ReV1qVRqQFwnQkHaxopqw0_img.gif)



\- 입력한 값에 따라 결과가 계속 바뀌는 것이 확인됩니다. 

\- 특히, [ {:else if x === '0'} ]일 때와 [ {:else if x == 0 } ]일 때를 잘 비교해서 보세요. 잘 써야 하겠죠?



---

##  8강 Logic - each blocks 

## **1. each block(반복 블록)** 

\- 컴퓨터 프로그램을 이루는 요소 중 대표적인 구문이 바로 조건문과 반복문입니다.

\- 기본적으로 HTML은 위에서 말한 두 구문이 없지만, 자바스크립트는 두 구문을 가지고 있습니다.

\- 지난 강좌에서는 조건문을 봤고, 이번 강좌에서는 반복문을 보도록 하겠습니다. 

\- 반복은 컴퓨터가 제일 잘하는 일입니다. 마크업 영역에서 정말 반복할 일이 많습니다.

\- 예를 들어, 쇼핑몰 같은 경우 상품이 정말 많죠? 그리고 넷플릭스도 보면 영상 관련 정보가 정말 많습니다. 

\- 하지만 HTML은 조건문과 마찬가지로 반복문을 처리할 수 있는 기능이 없습니다.

\- svelte에는 그래서 [ each block]을 사용합니다. 

 

### **1) 기본 each block**

\- 기본적인 반복 구문부터 확인해보겠습니다.

 

#### **## 문법**

```svelte
{#each datas as data}
	{ data } ...
{/each}
```

\- 위에서는 [ datas ]라고 예시로 작성했는데, 이부분은 상태 값의 복수형 단어라고 생각하시면 됩니다. 그러니까 배열 데이터 전체를 담은 명칭입니다. 

\- [ data ]는 단수형 단어라고 생각하면 됩니다. 이부분은 배열 값 중 한 개를 선택했다고 보면 됩니다. 이 영문들은 개발자가 임의적으로 작성해줍니다.

\- 무슨 의미인지 아래 코드를 보면서 이해해보도록 하겠습니다.

 

#### **## 코드보기**

\- 요일들을 선택 상자(select) 태그에 담아 보도록 하겠습니다.

```svelte
<script>
	let weekdays = ['일','월','화','수','목','금','토'];
</script>

<!-- select는 반복되지 않으므로 밖에 작성 -->
<select>
	<!-- 반복되는 것은 option태그 -->
	{#each weekdays as weekday}
		<option>{weekday}요일</option>
	{/each}
</select>
```

\- 복수형 단어는 배열을 담는 [ weekdays ]라고 설정했습니다. 

\- 단수형 단어는 데이터 한 개 한 개를 의미하는데 한 개이기에 s를 빼고 [ weekday ]라고 설정했습니다. 

\- 주석에도 설명을 써놓은 것처럼 <select> 태그는 반복되지 않으므로 [ each block ] 밖에 작성해줍니다. 

\- <option> 태그는 반복되기 때문에 [ each block ] 안에 작성해줍니다. 

 

#### **## 결과 보기**



![img](.\images\Z7BZmE7ByD4DDLGIikgNwK_img.gif)



\- 요일들이 반복돼서 잘 들어가 있는 것이 확인됩니다.

 

### **2) each block - 인덱스(index) 제공**

\- [ each block ]을 사용해서 인덱스 번호인 숫자를 이용할 수 있습니다.

\- 보통 [ i ]를 사용하면 됩니다. 인덱스 번호는 0번부터 시작됩니다.

 

 

#### **## 문법**

```svelte
{#each datas as data, i}
	{ data } ...
{/each}
```

\- 원래 문법 뒤에 쉼표를 쓴 후 [ i ]를 작성해줍니다.

 

 **## 코드 보기**

\- 이번에는 2022년 야구 정규시즌 순위를 표시해보겠습니다.

```svelte
<script>
	let teams = ['SSG 랜더스','LG 트윈스','키움 히어로즈','KT 위즈','KIA 타이거즈','NC 다이노스','삼성 라이온즈','롯데 자이언츠','두산 베어스','한화 이글스'];
</script>

<h3>2022 KBO 정규리그순위</h3>
{#each teams as team,i}
	<p>{i + 1}위 : {team}</p>
{/each}
```

\- [ teams ] 배열 값에 야구팀들을 전부 담았습니다.

\- 우리는 앞에 순위도 표시할 거라서, 숫자가 필요합니다.

\- 그래서 [ each block ] 뒤에 쉼표를 추가하고, [ i ]를 써줍니다. 

\- <p> 태그에 가져올 때는 1위부터 표시되어야 하니 +1을 추가해줍니다.

 

 

 

#### **## 결과 보기**



![img](.\images\mnfJh6FViKqm45UKpq05jK_img.png)



 

### **3) 객체 데이터 방식으로 반복 처리**

\- 이번에는 객체 데이터로 반복 처리를 해보겠습니다.

 

 

#### **## 문법**

```svelte
{#each datas as data}
	{ data.id } ...
{/each}
```

\- 위에 쓴 [ data.id ]에서 id는 객체의 속성명을 의미합니다. 

 

 

#### **## 다른 문법**

```svelte
{#each datas as { 속성명, 속성명 } }
	{ data.id } ...
{/each}
```

\- 위의 방식은 모든 데이터 속성을 가져왔다면, 아래 문법은 사용될 것만 가져오는 방식입니다. 

 

**## 코드 보기**

\- 이번에는 방탄소년단 멤버들을 소개한다고 보도록 하겠습니다. 

\- 사실 이건 State 상태 값 표시할 때 했었던 적이 있습니다. 

\- 이미지 경로가 길긴 하지만, 데이터를 잘 보도록 하겠습니다. 

```svelte
<script>
	let btss = [
		{
			name: 'RM(김남준)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Fbbd9629ef96c284dfca79eb3b26d49e49ba24b78117abba76b3108cd2b5b8b2860f4d7ceeaef314116b608d5b9d7b080d7e7751e385cc03988992088f8b932b5280a1d1075b1c5bd4759e679166564ca9a95b4ca0bdbdaa219462eacbcbd37be&type=fff264_180'
		},
		{
			name: '진(김석진)',
			birth: 1992,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F8152fad7a07738b7d11b8fe1b44b3cd0db34d5fa5c35abee4deae2fd163a4218f003e753532e1edd20a4db12e88382c0f3342d054cba5a0d38ba8bd9be593138ae4b6404caf7373e92540f2688467b825caa89de860dccc282395d440f6f72db&type=fff264_180'
		},
		{
			name: '슈가(민윤기)',
			birth: 1993,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Ff4f55ac286d2de6ead4dcf4ab419ca0ba3c44e3badcffe171f727d2653d9dce70d3a2a016307c21a9345fe53c74f099657bc5c96006acc61dc32eecff8e197818ed47d31fbfffc2e17654634345a357017536656b0258d7d1b26ee6b687095b4&type=fff264_180'
		},
		{
			name: '제이홉(정호석)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F67b1d265064ef6fce5ff59b3cf9195e64afade8493633cbf57087c55d40655c08a9481278bf960455ab4ea434bc3c250d57333d6c48743124de4c20d359684728713f1d0b8b063bc510feece8729186146cdbdba6cba15864bc4582aeebce6d5&type=fff264_180'
		},
		{
			name: '지민(박지민)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F379e369eeb01b0fff2ee569d236747e80764442246f1e00bdbc34f658e04847956c3e323a151ebd6f9306aa2214d3f4b5a7ee7142629a1fc727c0ece80642b8d1774fd7f36f2e7fb997cbc7c8f9e135c80e06dd63905e3d4b0de5b1a4a6e143d&type=fff264_180'
		},
		{
			name: '뷔(김태형)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F6e0bcfbe998a033312f0bf704f9880b48048bd960bacafe27ddc7080a9f3149e15ff59e4182c0436b95eed13012fc28c6d0219557cec1f7f9beb2438d64f36072cf50e4e513bb5ed4f3d62aba528f3b06d3edc89d5011d62961f04dc6d60fdf8&type=fff264_180'
		},
		{
			name: '정국(진정국)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F7619aa7dee9b330bef6e184fdb6f8ae4bdaa49915f6cb516266b5b8a7e814e65b39931cb4baad7cffcd61927d0da0e2fa1950e3776e84a339b8c8d3ff943cc25697f4ec55a52b07869b7a2fe04fbe508b4145fc8b26dcb5d437d34e734ebefed&type=fff264_180'
		},
	];
</script>

{#each btss as bts}
<h3><img src="{bts.img}" alt=""></h3>
<h3>이름 : { bts.name }</h3>
<h3>출생 : { bts.birth }</h3>
<hr>
{/each}
```

\- 배열 내부의 데이터를 객체 방식으로 작성해주면, 디테일하게 데이터를 담을 수 있습니다.

\- 그래서 각각의 데이터를 호출 시에는 [ 단수형단어.속성명 ]으로 호출해주면 됩니다. 

 

**## 결과 보기**



![img](.\images\9txn3dEkKlQLzqKDzMKWIK_img.gif)



\- 방탄소년단 멤버들이 데이터로 잘 반복돼서 표시되는 것이 확인됩니다.



### **4) 요소 값에 고유한 key를 설정**

\- 위와 같은 데이터를 추가한 상태의 항목(속성)에 키(key) 값, 즉 요소의 고윳값을 줄 수도 있습니다.

\- 특히, 요소를 추가, 삭제해야 하는 경우에는 데이터가 섞이지 않게 꼭 키값을 설정해줍니다.

\- 이유는 키값 없이 추가 작업을 하는 경우에 추가된 값은 무조건 항목의 끝에 위치하게 되는데, 개발자가 이것을 원하지 않을 경우 키값을 주어 위치까지 잘 제어할 수 있게 됩니다.

 

 

#### **## 코드 보기**

\- 위에서 방탄소년단 데이터를 만든 것에 id라는 속성을 줘서 번호를 주도록 하겠습니다. 

\- 버튼을 누르면 멤버가 삭제되게 만들어 보겠습니다. 

(전 세계 아미가 절 욕하는 것 아니겠죠? 오해는 없으셨으면 좋겠습니다. I ♥ BTS )

```svelte
<script>
	let btss = [
		{
			id: 1,
			name: 'RM(김남준)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Fbbd9629ef96c284dfca79eb3b26d49e49ba24b78117abba76b3108cd2b5b8b2860f4d7ceeaef314116b608d5b9d7b080d7e7751e385cc03988992088f8b932b5280a1d1075b1c5bd4759e679166564ca9a95b4ca0bdbdaa219462eacbcbd37be&type=fff264_180'
		},
		{
			id: 2,
			name: '진(김석진)',
			birth: 1992,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F8152fad7a07738b7d11b8fe1b44b3cd0db34d5fa5c35abee4deae2fd163a4218f003e753532e1edd20a4db12e88382c0f3342d054cba5a0d38ba8bd9be593138ae4b6404caf7373e92540f2688467b825caa89de860dccc282395d440f6f72db&type=fff264_180'
		},
		{
			id: 3,
			name: '슈가(민윤기)',
			birth: 1993,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2Ff4f55ac286d2de6ead4dcf4ab419ca0ba3c44e3badcffe171f727d2653d9dce70d3a2a016307c21a9345fe53c74f099657bc5c96006acc61dc32eecff8e197818ed47d31fbfffc2e17654634345a357017536656b0258d7d1b26ee6b687095b4&type=fff264_180'
		},
		{
			id: 4,
			name: '제이홉(정호석)',
			birth: 1994,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F67b1d265064ef6fce5ff59b3cf9195e64afade8493633cbf57087c55d40655c08a9481278bf960455ab4ea434bc3c250d57333d6c48743124de4c20d359684728713f1d0b8b063bc510feece8729186146cdbdba6cba15864bc4582aeebce6d5&type=fff264_180'
		},
		{
			id: 5,
			name: '지민(박지민)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F379e369eeb01b0fff2ee569d236747e80764442246f1e00bdbc34f658e04847956c3e323a151ebd6f9306aa2214d3f4b5a7ee7142629a1fc727c0ece80642b8d1774fd7f36f2e7fb997cbc7c8f9e135c80e06dd63905e3d4b0de5b1a4a6e143d&type=fff264_180'
		},
		{
			id: 6,
			name: '뷔(김태형)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F6e0bcfbe998a033312f0bf704f9880b48048bd960bacafe27ddc7080a9f3149e15ff59e4182c0436b95eed13012fc28c6d0219557cec1f7f9beb2438d64f36072cf50e4e513bb5ed4f3d62aba528f3b06d3edc89d5011d62961f04dc6d60fdf8&type=fff264_180'
		},
		{
			id: 7,
			name: '정국(진정국)',
			birth: 1995,
			img: 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fw.namu.la%2Fs%2F7619aa7dee9b330bef6e184fdb6f8ae4bdaa49915f6cb516266b5b8a7e814e65b39931cb4baad7cffcd61927d0da0e2fa1950e3776e84a339b8c8d3ff943cc25697f4ec55a52b07869b7a2fe04fbe508b4145fc8b26dcb5d437d34e734ebefed&type=fff264_180'
		},
	];

	function deleteMember(){
		btss = btss.slice(1);
	}
</script>

<button on:click={deleteMember}>멤버삭제</button>

{#each btss as bts}
<h3><img src="{bts.img}" alt=""></h3>
<h3>이름 : { bts.name }</h3>
<h3>출생 : { bts.birth }</h3>
<hr>
{/each}
```

\- 각각의 데이터에 [ id ] 속성을 전부 추가했습니다. 

\- 그리고 마크업 부분에 <button> 태그를 추가했습니다.

\- 스크립트 부분에 [ deleteMember ]라는 함수를 추가해서 배열 값 중 첫 번째 한 개를 잘라내도록 [ slice ] 메서드를 추가했습니다.

\- 그리고 버튼을 클릭할 때 명령이 발생되도록 처리했습니다. 

  

#### **## 결과 보기**



![img](.\images\kFcrJYi6kVs3jZNs934fTK_img.gif)



\- 멤버 삭제 버튼을 눌렀을 때 맨 위의 멤버부터 한 명씩 삭제되는 것이 확인됩니다. 



---

##  9강 Bindings(바인딩)

 

## **1. 바인딩(Binding)이란?**

------

\- 바인딩은 상태 값과 그 값을 제어하는 요소와의 결합을 의미합니다.

\- 보통 데이터를 입력하는 다양한 폼(input, select, textarea 등)과 함께 사용됩니다.

\- svelte는 양방향 바인딩을 이용해서 동시에 값이 변경되게 처리됩니다.

\- 지난 [ 7강 if block ]에서 입력 상자에 값을 입력하면 반환되는 것을 [ keyup ] 이벤트를 통해 강제로 발생시켰는데, 사실 그렇게 작업할 필요가 없이 svelte는 바인딩이 잘 되어 있습니다. 

\- 바인딩은 다른 요소와 컴포넌트에도 가능하지만 이번 강좌에서는 form관련 바인딩만 보도록 하겠습니다. 

 

#### **## 문법**

```
<태그요소 bind:태그속성={상태값}>
```

\- 보통 위의 형태로 작성한다고 보면 됩니다.

 

 

### **1) input text value 바인딩**

\- 입력상자에 입력한 값이 p태그에 바로 반환되게 처리해보겠습니다.

 

#### **## 코드 보기**

```svelte
<script>
	let name = '';
</script>

<input type="text" bind:value={name} placeholder="이름을 입력하세요.">
<p>안녕! {name || '낯선 사람'}!</p>
```

\- <input> 태그에 bind:속성명={ 상태 값} 으로 잘 연결해줬습니다. 

\- 처음부터 값이 비어있는 것을 방지하기 위해 논리합 연산자( || )로 name이 없는 경우 낯선 사람으로 인식하게 처리했습니다.

 

#### **## 결과 보기**



![img](.\images\i2dOAh1MsPlHRomd18tcM0_img.gif)



\- 처음엔 [낯선 사람]으로 되어 있다가 이름을 바꾸면 실시간으로 데이터가 변경되는 것을 확인할 수 있습니다. 



### **2) input number/range value 바인딩**

\- 이번에는 숫자를 지정하는 number타입과 범위가 결정되는 range타입으로 데이터가 바인딩되는 것을 보겠습니다.

 

#### **## 코드 보기**

```svelte
<script>
	let a = 1;
	let b = 2;
</script>

<label>
	<input type=number bind:value={a} min=0 max=10>
	<input type=range bind:value={a} min=0 max=10>
</label>

<label>
	<input type=number bind:value={b} min=0 max=10>
	<input type=range bind:value={b} min=0 max=10>
</label>

<p>{a} + {b} = {a + b}</p>
```

\- 이번에는 변수를 2개 선언해서 상태 값을 2개를 바인딩해보겠습니다.

\- <input> 태그들의 value 속성에 bind 해보겠습니다.

 

#### **## 결과 보기**



![img](.\images\qxyI99z8EUjaIoMauGaOjk_img.gif)



\- number타입의 숫자를 바꿔도 버튼을 눌러도 데이터가 실시간으로 잘 변경되는 것이 확인됩니다. 

  

### **3) input checkbox checked 바인딩**

\- 이번에는 체크 상황에 따라 바인딩 처리를 해보도록 하겠습니다.

\- [ bind:checked ]를 통해 상태 값에 바인딩 처리가 가능합니다.

 

**## 코드 보기**

```svelte
<script>
	let chk = false;
</script>

<label>
	<input type="checkbox" bind:checked={chk}>
	약관 동의
</label>

{#if chk}
	<p>당신은 약관에 동의했습니다.<br>이제 구독이 가능합니다.</p>
{:else}
	<p>당신은 약관에 동의하지 않았습니다.<br>아직 구독이 불가능합니다.</p>
{/if}

<button disabled={!chk}>
	구독
</button>
```

\- [ chk ] 변수에는 true/false 가 담기도록 설정하고, 초기값은 false로 지정해줍니다. 

\- 그리고 <input type="checkbox"> 에 [ bind:checked={chk} ]로 [ chk ] 를 상태 값으로 지정해줍니다.

\- [ if block ]을 통해서 체크상태에 따라 반환받을 <p> 태그를 따로 지정해줍니다.

\- <button> 태그의 disabled 상태 역시 [ chk ] 상태값으로 지정해줍니다. 대신 부정 연산자를 사용하여 false 일 때 사용 불가능하게 처리해줍니다. 

 

 

 

 

#### **## 결과 보기**



![img](.\images\90yk130Assn2skDGsrqKk0_img.gif)



\- 약관 동의 옆 체크박스를 체크하면 구독 버튼이 클릭되고, 미체크되면 구독 버튼이 클릭되지 않는 것이 잘 확인됩니다.



### **4) input checkbox/radio 그룹 바인딩**

\- 보통 체크박스나 라디오 버튼은 체크되는 요소가 그룹으로 처리됩니다.

\- 특히 라디오버튼 같은 경우는 name 속성을 통해 하나의 그룹으로 체크됩니다.

\- 이때 svelte에서 사용되는 바인딩이 [ bind:group={상태값} ]입니다.

\- 이 바인딩으로 같이 사용되는 폼들의 상태 값을 공유하게 됩니다.

\- 아래 코드로 더 자세히 공부해보도록 하겠습니다. 

 

 

#### **## 코드 보기**

\- 커피 사이즈를 선택하는 라디오 버튼을 만들어 보겠습니다.

```svelte
<script>
	let choiceSize = 0;
	let sizes = ['Tall','Grande','Venti'];
</script>

<h3>사이즈 선택</h3>
{#each sizes as size, i}
	<label>
		<input type="radio" bind:group={choiceSize} value={i}>
		{ size }
	</label>
{/each}

<p>고객님은 {sizes[choiceSize]}를 선택하셨습니다.</p>
```

\- 일단 [ choiceSize ]는 라디오 버튼이 그룹으로 묶일 상태 값입니다. 초기값은 0으로 줬는데 추후 반복을 통해 인덱스 번호로 value 속성값을 처리할 거라서 그렇습니다.

\- [ sizes ] 는 each block 으로 반복할 거라 배열로 이름을 지정해줬습니다. 

\- each block 안에 라디오버튼들을 반복해줍니다. 이때 같은 그룹화로 상태 값을 바인딩할 거라 [ bind:group={choiceSize} ] 입을 처리해줍니다.

\- 그리고 하단 <p> 태그에 선택한 사이즈를 상태 값으로 출력해줍니다. 

 

#### **## 결과 보기**



![img](.\images\wBc0PB82EHLn2MbL2n8pG0_img.gif)



\- 초기값을 0으로 지정했기 때문에 Tall로 처음에 잘 선택됩니다.

\- 라디오 버튼 체크에 따라 상태 값이 자동으로 변경되는 것이 잘 확인됩니다. 

 

### **5) select value 바인딩**

\- <select> 태그는 보통 옵션 중 하나의 값을 선택하는 선택 상자입니다. 

\- 옵션 중 선택한 값의 value 가 전체 <select> 태그의 value 이기도 합니다. 

\- 그 개념을 잘 이해하면 [ bind:value={상태값} ]에 대한 이해도 쉬울 것 같습니다.

\- 아래 예제를 통해서 더 자세히 배워 보겠습니다. 

 

 

#### **## 코드 보기**

\- 포털사이트를 선택 상자에 담아 옵션을 선택 시 새창으로 띄우는 것을 만들어 보겠습니다.

```svelte
<script>
	let portals = [
		{ name: '사이트선택', url: null },
		{ name: '네이버', url: 'https://naver.com' },
		{ name: '다음', url: 'https://daum.net' },
		{ name: '구글', url: 'https://google.com' }
	];
	let selected;

	function selectChange(){
		if(selected != null){
			window.open(selected);
		}
	}
</script>

<h3>포털 사이트 바로가기</h3>
<select bind:value={selected} on:change={selectChange}>
	{#each portals as portal}
		<option value={portal.url}>{ portal.name }</option>
	{/each}
</select>
```

\- [ portals ]에 객체 방식으로 데이터를 배열로 담았습니다. 

\- 그중 name 속성은 <option> 태그의 텍스트 콘텐츠로 담길 것이고, url 속성은 <option> 태그의 value값으로 상태 값을 처리하겠습니다. 

\- [ selected ]는 <select> 태그가 선택하는 value값이 담길 상태값 변수입니다. 

\- [ selectChange ] 함수는 값이 null 이 아니라면 선택된 value값으로 새창을 띄울 함수인데, 이것은 <select> 태그의 on:change 이벤트로 적용합니다. 

\- 옵션 태그들을 역시 반복해서 담아줘야겠죠?

 

 **## 결과 보기**



![img](.\images\Hew9x5UFSnKUEbQQ7MkqR1_img.gif)



\- 브라우저에서 옵션을 변경하면 그에 맞는 사이트로 잘 바뀝니다.

\- 사이트 선택을 다시 선택하면 새창은 뜨지 않습니다. 



---

### Svelte를 배우는 방법?

Svelte 학습에는 Svelte의 기본 사항 및 구문 이해와 Svelte 컴파일러를 사용하여 반응적이고 효율적인 웹 애플리케이션을 만드는 방법을 배우는 것이 포함됩니다. 시작하기 위한 몇 가지 단계는 다음과 같습니다.

**기본부터 시작**: 복잡한 개념에 뛰어들기 전에 기본을 잘 이해하는 것이 중요합니다. Svelte의 기본 사항과 작동 방식을 다루는 공식 Svelte 튜토리얼부터 시작하세요. Svelte 설명서, 블로그 게시물 및 비디오 자습서와 같은 다른 온라인 리소스를 살펴볼 수도 있습니다.

**개발 환경 설정**: Svelte로 구축을 시작하려면 개발 환경을 설정해야 합니다. Node.js와 VSCode 같은 코드 편집기를 설치한 다음 Svelte CLI를 설치하여 Svelte 애플리케이션을 쉽게 만듭니다.

**Svelte 구문 배우기**: Svelte 구문은 HTML, CSS 및 JavaScript에 대한 경험이 있는 경우 직관적이고 쉽게 배울 수 있습니다. 구성 요소, 소품, 저장소 및 수명 주기 메서드와 같은 주요 개념에 대해 알아봅니다. 또한 온라인 과정 및 자습서를 사용하여 이러한 개념을 더 깊이 이해할 수 있습니다.

**첫 번째 Svelte 앱 빌드**: Svelte 기본 사항을 잘 이해했으면 간단한 Svelte 앱 빌드를 시작하십시오. Svelte 튜토리얼에는 시작할 수 있는 Todo 앱의 좋은 예가 있습니다. 또한 Svelte REPL을 탐색하여 Svelte 구성 요소를 만들고 실험할 수 있습니다.

**Svelte 연습 및 탐색**: Svelte에 능숙해지려면 실제 애플리케이션 빌드를 연습해야 합니다. Svelte 생태계를 탐색하고 사용 가능한 다양한 라이브러리와 도구를 사용하십시오. Svelte 커뮤니티에 가입하고 포럼, 모임 및 이벤트에 참여하세요.

Svelte를 배우려면 상당한 양의 연습과 인내가 필요하지만 현대적이고 효율적이며 반응이 빠른 웹 애플리케이션을 구축하는 데 관심이 있다면 시간을 투자해도 좋습니다.



Learning Svelte involves understanding the fundamentals of Svelte and its syntax, as well as learning how to use the Svelte compiler to create reactive and efficient web applications. Here are some steps to get started:

1. Start with the basics: Before diving into complex concepts, it's essential to have a good grasp of the fundamentals. Start with the official Svelte tutorial, which covers the basics of Svelte and how it works. You can also go through other online resources like the Svelte documentation, blog posts, and video tutorials.
2. Set up your development environment: To start building with Svelte, you'll need to set up your development environment. Install Node.js and a code editor like VSCode, and then install the Svelte CLI to create Svelte applications easily.
3. Learn Svelte syntax: Svelte syntax is intuitive and easy to learn if you have experience with HTML, CSS, and JavaScript. Learn about the key concepts like components, props, stores, and lifecycle methods. You can also use online courses and tutorials to get a deeper understanding of these concepts.
4. Build your first Svelte app: Once you have a good understanding of Svelte basics, start building a simple Svelte app. The Svelte tutorial has a great example of a Todo app that you can start with. You can also explore the Svelte REPL to create and experiment with Svelte components.
5. Practice and explore Svelte: To become proficient in Svelte, you need to practice building real-world applications. Explore the Svelte ecosystem and use the various libraries and tools that are available. Join the Svelte community and participate in forums, meetups, and events.

Learning Svelte requires a significant amount of practice and patience, but it is an excellent investment of your time if you're interested in building modern, efficient, and reactive web applications.



### Svelte sample source ?

다음은 버튼을 렌더링하고 클릭 시 카운터를 업데이트하는 간단한 Svelte 구성 요소입니다.

``` svelte
<script>
  let count = 0;

  function handleClick() {
    count += 1;
  }
</script>

<button on:click={handleClick}>
  Clicked {count} {count === 1 ? 'time' : 'times'}
</button>

```

이 구성 요소는 count 변수와 handleClick 함수를 정의합니다. 버튼을 클릭하면 handleClick 함수가 count 변수를 업데이트하고 구성 요소가 새 값으로 다시 렌더링됩니다.

응용 프로그램에서 이 구성 요소를 사용하려면 확장자가 .svelte인 파일에 저장한 다음 다른 구성 요소 또는 기본 앱 파일로 가져옵니다. 예를 들어:

``` svelte
<script>
  import Button from './Button.svelte';
</script>

<main>
  <Button />
</main>
```

이 예제에서는 Button 구성 요소를 가져온 다음 기본 구성 요소에서 사용합니다. 보다 복잡한 구성 요소를 생성하고 prop, store 및 기타 Svelte 기능을 사용하여 강력하고 동적인 애플리케이션을 구축할 수 있습니다.



### - 끝 -