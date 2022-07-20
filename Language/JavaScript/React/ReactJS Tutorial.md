# ReactJS Tutorial

출처: https://www.tutorialspoint.com/reactjs/index.htm

React는 웹 애플리케이션에서 사용자 인터페이스(UI)를 개발하기 위한 오픈 소스, JavaScript 라이브러리입니다. React는 Facebook에서 개발 및 출시합니다. Facebook은 지속적으로 React 라이브러리를 작업하고 있으며 버그를 수정하고 새로운 기능을 도입하여 개선하고 있습니다. 이 튜토리얼은 React의 아키텍처, 프로젝트 설정, 컴포넌트 생성, JSX 방법 안내로 시작하여 상태 관리, 양식 프로그래밍, 라우팅과 같은 고급 개념을 살펴보고 마지막으로 단계별 작업 예제로 마무리합니다.



### 대상

이 튜토리얼은 프론트엔드 웹 애플리케이션 개발 분야에서 경력을 쌓고자 하는 전문가를 위해 준비되었습니다. 이 튜토리얼은 예제와 함께 React 개념을 편안하게 시작할 수 있도록 하기 위한 것입니다.



### 전제 조건

이 튜토리얼에서 제공하는 다양한 유형의 개념을 진행하기 전에 독자가 HTML, CSS 및 OOPS 개념에 대한 기본 지식이 있다고 가정합니다. 이 외에도 독자가 JavaScript에 대한 건전한 지식을 가지고 있다면 매우 도움이 될 것입니다.





## ReactJS - 소개

https://www.tutorialspoint.com/reactjs/reactjs_introduction.htm

ReactJS는 단순하고 기능이 풍부한 컴포넌트 기반 JavaScript UI 라이브러리입니다. 크고 복잡한 응용 프로그램뿐만 아니라 작은 응용 프로그램을 개발하는 데 사용할 수 있습니다. ReactJS는 웹 애플리케이션을 시작하기 위한 최소한의 견고한 기능 세트를 제공합니다. React 커뮤니티는 기록적인 시간에 웹 애플리케이션을 개발할 수 있는 기성 컴포넌트의 큰 세트를 제공하여 React 라이브러리를 보완합니다. React 커뮤니티는 또한 React 라이브러리 위에 상태 관리, 라우팅 등과 같은 고급 개념을 제공합니다.



### React versions

React의 초기 버전인 0.3.0은 2013년 5월에 출시되었으며 최신 버전인 17.0.1은 2020년 10월에 출시되었습니다. 메이저 버전은 브레이킹 체인지를 도입하고 마이너 버전은 기존 기능을 손상시키지 않고 새로운 기능을 도입합니다. 버그 수정은 필요할 때마다 릴리스됩니다. React는 Sematic Versioning(semver) 원칙을 따릅니다.



### Features

React 라이브러리의 두드러진 기능은 다음과 같습니다.

- 견고한 기본 아키텍처
- 확장 가능한 아키텍처
- 컴포넌트 기반 라이브러리
- JSX 기반 설계 아키텍처
- 선언적 UI 라이브러리



### Benefits

React 라이브러리를 사용하는 몇 가지 이점은 다음과 같습니다.

- 배우기 쉬움
- 현대 및 레거시 애플리케이션에 쉽게 적응
- 기능을 코딩하는 더 빠른 방법
- 많은 수의 기성 컴포넌트 가용성
- 크고 활동적인 커뮤니티



### Applications

React 라이브러리로 구동되는 몇 가지 인기 있는 웹사이트는 다음과 같습니다.

- 페이스북, 인기 있는 소셜 미디어 애플리케이션
- 인스타그램, 인기 있는 사진 공유 애플리케이션
- 인기 있는 미디어 스트리밍 애플리케이션인 Netflix
- 코드 아카데미, 인기 있는 온라인 교육 애플리케이션
- Reddit, 인기 콘텐츠 공유 애플리케이션



보시다시피 모든 분야에서 가장 많이 사용되는 응용 프로그램은 React Library에서 개발되고 있습니다.



## ReactJS - Installation

https://www.tutorialspoint.com/reactjs/reactjs_installation.htm

이 장에서는 시스템에 React 라이브러리 및 관련 도구를 설치하는 방법을 설명합니다. 설치로 이동하기 전에 먼저 전제 조건을 확인하겠습니다.

React는 개발자가 React 기반 웹 애플리케이션의 생성, 개발 및 배포를 빠르게 진행할 수 있도록 CLI 도구를 제공합니다. React CLI 도구는 Node.js에 따라 다르며 시스템에 설치해야 합니다. 시스템에 Node.js를 설치했으면 합니다. 아래 명령을 사용하여 확인할 수 있습니다 -

``` bash
node --version
```

설치했을 수도 있는 Nodejs 버전을 볼 수 있습니다. 나를 위해 아래와 같이 표시됩니다.

``` bash
v14.2.0
```

Nodejs가 설치되어 있지 않다면 https://nodejs.org/en/download/를 방문하여 다운로드하여 설치할 수 있습니다.



### Toolchain

양식 유효성 검사, 모델 대화 상자 등과 같은 가벼운 기능을 개발하기 위해 CDN(콘텐츠 전달 네트워크)을 통해 React 라이브러리를 웹 애플리케이션에 직접 포함할 수 있습니다. 웹 애플리케이션에서 jQuery 라이브러리를 사용하는 것과 유사합니다. 중대형 애플리케이션의 경우 애플리케이션을 여러 파일로 작성한 다음 웹팩, 소포, 롤업 등과 같은 번들러를 사용하여 코드를 배포하기 전에 애플리케이션을 컴파일 및 번들링하는 것이 좋습니다.

React 툴체인은 React 애플리케이션을 생성, 빌드, 실행 및 배포하는 데 도움이 됩니다. React 툴체인은 기본적으로 애플리케이션을 부트스트랩하는 데 필요한 모든 코드가 포함된 스타터 프로젝트 템플릿을 제공합니다.

React 애플리케이션을 개발하는 데 인기 있는 도구 체인 중 일부는 다음과 같습니다.

- Create React App − SPA 지향 툴체인
- Next.js − 서버 측 렌더링 지향 툴체인
- Gatsby − 정적 콘텐츠 지향 툴체인



React 애플리케이션을 개발하는 데 필요한 도구는 다음과 같습니다.

- 개발하는 동안 애플리케이션을 제공하기 위한 정적 서버인 serve
- 바벨 컴파일러
- Create React App CLI



이 장에서 위에서 언급한 도구의 기본 사항과 설치 방법에 대해 알아보겠습니다.



### The *serve* static server

serve는 경량 웹 서버입니다. 정적 사이트 및 단일 페이지 응용 프로그램을 제공합니다. 빠르게 로드되고 최소 메모리를 소모합니다. React 애플리케이션을 제공하는 데 사용할 수 있습니다. 시스템에 npm 패키지 관리자를 사용하여 도구를 설치해 보겠습니다.

``` sh
npm install serve -g
```

간단한 정적 사이트를 만들고 serve 앱을 사용하여 애플리케이션을 서비스해 보겠습니다.

명령 프롬프트를 열고 작업 공간으로 이동합니다.

``` sh
cd /go/to/your/workspace
```

새 폴더인 static_site를 만들고 디렉터리를 새로 만든 폴더로 변경합니다.

``` sh
mkdir static_site
cd static_site
```

다음으로 좋아하는 html 편집기를 사용하여 폴더 안에 간단한 웹 페이지를 만듭니다.

``` html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Static website</title>
</head>
<body>
<div><h1>Hello!</h1></div>
</body>
</html>
```

`index.html`

다음으로, serve 명령을 실행합니다.

``` sh
serve .
```

전체 폴더 대신 단일 파일인 index.html을 제공할 수도 있습니다.

``` sh
serve ./index.html
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다. 서버 응용 프로그램은 아래와 같이 웹 페이지를 제공합니다.

![Hello](.\images\hello.jpg)

서버는 기본 포트 3000을 사용하여 응용 프로그램을 제공합니다. 사용할 수 없는 경우 임의의 포트를 선택하여 지정합니다.

   ┌───────────────────────────────┐
   │                                                                                     │
   │   Serving!                                                                    │
   │                                                                                     │
   │   - Local:            http://localhost:3000                    │
   │   - On Your Network:  http://172.31.32.1:3000    │
   │                                                                                     │
   │   Copied local address to clipboard!                     │
   │                                                                                     │
   └───────────────────────────────┘



### Babel compiler

Babel은 JavaScript의 많은 변형(es2015, es6 등)을 모든 브라우저에서 지원하는 표준 JavaScript 코드로 컴파일하는 JavaScript 컴파일러입니다. React는 JavaScript의 확장인 JSX를 사용하여 사용자 인터페이스 코드를 디자인합니다. Babel은 JSX 코드를 JavaScript 코드로 컴파일하는 데 사용됩니다.

Babel과 React companion을 설치하려면 아래 명령을 실행하십시오.

``` bash
npm install babel-cli@6 babel-preset-react-app@3 -g
... 
... 
+ babel-cli@6.26.0 
+ babel-preset-react-app@3.1.2 
updated 2 packages in 8.685s
```

Babel은 차세대 고급 JavaScript 구문으로 애플리케이션을 작성하는 데 도움이 됩니다.



### Create React App toolchain

Create React App은 단일 페이지 React 애플리케이션을 생성하는 최신 CLI 도구입니다. React 커뮤니티에서 지원하는 표준 도구입니다. babel 컴파일러도 처리합니다. 로컬 시스템에 Create React App을 설치해 보겠습니다.

``` bash
> npm install -g create-react-app
+ create-react-app@4.0.1 
added 6 packages from 4 contributors, removed 37 packages and updated 12 packages in 4.693s
```

#### Updating the toolchain

React Create App 툴체인은 react-scripts 패키지를 사용하여 애플리케이션을 빌드하고 실행합니다. 애플리케이션 작업을 시작하면 npm 패키지 관리자를 사용하여 언제든지 반응 스크립트를 최신 버전으로 업데이트할 수 있습니다.

``` bash
npm install react-scripts@latest
```



### React 툴체인 사용의 장점

React 툴체인은 기본적으로 많은 기능을 제공합니다. React 툴체인을 사용하면 다음과 같은 이점이 있습니다.

- 애플리케이션의 사전 정의 및 표준 구조.
- 다양한 유형의 응용 프로그램을 위한 기성품 프로젝트 템플릿입니다.
- 개발 웹 서버가 포함되어 있습니다.
- 타사 React 컴포넌트를 포함하는 쉬운 방법입니다.
- 애플리케이션 테스트를 위한 기본 설정





## ReactJS - 아키텍처

https://www.tutorialspoint.com/reactjs/reactjs_architecture.htm

React 라이브러리는 견고한 기반 위에 구축되었습니다. 간단하고 유연하며 확장 가능합니다. 앞에서 배웠듯이 React는 웹 애플리케이션에서 사용자 인터페이스를 생성하기 위한 라이브러리입니다. React의 주요 목적은 개발자가 순수한 JavaScript를 사용하여 사용자 인터페이스를 만들 수 있도록 하는 것입니다. 일반적으로 모든 사용자 인터페이스 라이브러리는 사용자 인터페이스를 설계하기 위해 새로운 템플릿 언어(학습해야 함)를 도입하고 템플릿 내부 또는 개별적으로 논리를 작성하는 옵션을 제공합니다.

새로운 템플릿 언어를 도입하는 대신 React는 다음과 같은 세 가지 간단한 개념을 도입합니다.

#### React elements

HTML DOM의 JavaScript 표현. React는 React Element를 생성하기 위한 API인 React.createElement를 제공합니다.

#### JSX

사용자 인터페이스를 디자인하기 위한 JavaScript 확장입니다. JSX는 약간의 수정으로 HTML 구문을 지원하는 XML 기반의 확장 가능한 언어입니다. JSX는 React Elements로 컴파일하고 사용자 인터페이스를 만드는 데 사용할 수 있습니다.

#### React component

React 컴포넌트는 React 애플리케이션의 기본 빌딩 블록입니다. React 요소와 JSX를 사용하여 사용자 인터페이스를 디자인합니다. React 컴포넌트는 기본적으로 JavaScript 클래스(React.component 클래스 확장) 또는 순수 JavaScript 함수입니다. React 컴포넌트에는 속성, 상태 관리, 라이프 사이클 및 이벤트 핸들러가 있습니다. React 컴포넌트는 고급 로직뿐만 아니라 단순하게도 할 수 있습니다.

React Component 장에서 컴포넌트에 대해 더 자세히 알아보겠습니다.



### React 애플리케이션의 워크플로

이 장에서는 간단한 React 애플리케이션을 만들고 분석하여 React 애플리케이션의 워크플로를 이해하도록 합시다.

명령 프롬프트를 열고 작업 공간으로 이동합니다.

``` bash
cd /go/to/your/workspace
```

다음으로 static_site라는 폴더를 생성하고 디렉터리를 새로 생성된 폴더로 변경합니다.

#### Example

다음으로 hello.html 파일을 만들고 간단한 React 애플리케이션을 작성합니다.

``` html
<!DOCTYPE html> 
<html> 
   <head> 
      <meta charset="UTF-8" /> 
      <title>React Application</title> 
   </head> 
   <body> 
      <div id="react-app"></div> 
      <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script> 
      <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script> 
      <script language="JavaScript"> 
         element = React.createElement('h1', {}, 'Hello React!') 
         ReactDOM.render(element, document.getElementById('react-app')); 
      </script> 
   </body> 
</html>
```

다음으로, 웹 서버 serve를 사용하여 애플리케이션을 제공합니다.

``` bash
serve ./hello.html
```

##### Output

다음으로 즐겨찾는 브라우저를 엽니다. 주소 표시줄에 http://localhost:3000을 입력하고 엔터를 누릅니다.

![React Hello](.\images\react_hello.jpg)

코드를 분석하고 React 애플리케이션을 더 잘 이해하기 위해 약간의 수정을 가해 보겠습니다.

여기서는 React 라이브러리에서 제공하는 두 가지 API를 사용합니다.

##### React.createElement

React 요소를 만드는 데 사용됩니다. 세 가지 매개변수가 필요합니다.

- Element tag
- Element attributes as object
- Element content -  중첩된 React 요소도 포함할 수 있습니다.

##### ReactDOM.render

요소를 컨테이너에 렌더링하는 데 사용됩니다. 두 개의 매개변수가 필요합니다.

- React Element OR JSX
- 웹페이지의 루트 요소



#### 중첩된 React 요소

React.createElement는 중첩된 React 요소를 허용하므로 아래와 같이 중첩된 요소를 추가해 보겠습니다.

##### Example

``` html
<script language="JavaScript">
   element = React.createElement('div', {}, React.createElement('h1', {}, 'Hello React!'));
   ReactDOM.render(element, document.getElementById('react-app')); 
</script>
```

##### Output

아래 내용을 생성합니다 -

``` js
<div><h1> Hello React!</h1></div>
```

#### JSX 사용

다음으로 React 요소를 완전히 제거하고 아래와 같이 JSX 구문을 도입하겠습니다.

``` html
<!DOCTYPE html> 
<html> 
   <head> 
      <meta charset="UTF-8" /> 
      <title>React Application</title> 
   </head> 
   <body> 
      <div id="react-app"></div> 
      <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script> 
      <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script> 
      <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> 
      <script type="text/babel"> 
         ReactDOM.render(
            <div><h1>Hello React!</h1></div>, 
            document.getElementById('react-app') 
         ); 
     </script> 
   </body> 
</html>
```

여기에 JSX를 JavaScript로 변환하는 babel을 포함하고 스크립트 태그에 type=“text/babel”을 추가했습니다.

``` html
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">
...
...
</script>
```

그런 다음 응용 프로그램을 실행하고 브라우저를 엽니다. 응용 프로그램의 출력은 다음과 같습니다.

![Hello Jsx](.\images\hello_jsx.jpg)

다음으로 새로운 React 컴포넌트인 Greeting을 생성하여 웹페이지에서 사용해보도록 하겠습니다.

``` js
<script type="text/babel">
function Greeting() {
return <div><h1>Hello JSX!</h1></div>
}
ReactDOM.render(<Greeting />, document.getElementById('react-app') );
</script>
```

결과는 동일하며 아래와 같습니다 -

![Hello Jsx](.\images\hello_jsx.jpg)

응용 프로그램을 분석하여 아래 다이어그램과 같이 React 응용 프로그램의 워크플로를 시각화할 수 있습니다.

![Workflow Jsx](.\images\workflow_jsx.jpg)

React 앱은 React 컴포넌트(JSX 또는 React 요소 형식으로 코딩됨)를 사용하여 만든 사용자 인터페이스와 사용자 인터페이스를 렌더링할 컨테이너를 전달하여 ReactDOM.render 메서드를 호출합니다.

ReactDOM.render는 JSX 또는 React 요소를 처리하고 가상 DOM을 내보냅니다.

가상 DOM이 병합되어 컨테이너에 렌더링됩니다.



#### React 애플리케이션의 아키텍처

React 라이브러리는 UI 라이브러리일 뿐이며 복잡한 애플리케이션을 작성하기 위해 특정 패턴을 강요하지 않습니다. 개발자는 원하는 디자인 패턴을 자유롭게 선택할 수 있습니다. React 커뮤니티는 특정 디자인 패턴을 옹호합니다. 패턴 중 하나는 Flux 패턴입니다. React 라이브러리는 더 나은 코드를 작성하기 위해 Higher Order component, Context, Render props, Refs 등과 같은 많은 개념을 제공합니다. React Hooks는 큰 프로젝트에서 상태 관리를 수행하기 위해 개념을 발전시키고 있습니다. React 애플리케이션의 높은 수준의 아키텍처를 이해하려고 노력합시다.

![React App](.\images\react_app.jpg)

- React 앱은 단일 루트 component로 시작합니다.
- 루트 component는 하나 이상의 component를 사용하여 빌드됩니다.
- 각 component는 모든 수준에서 다른 component와 중첩될 수 있습니다.
- component는 React 라이브러리의 핵심 개념 중 하나입니다. 따라서 각 component는 다른 component에서 하나의 component를 상속하는 대신 더 작은 component를 구성하여 빌드됩니다.
- 대부분의 component는 사용자 인터페이스 component입니다.
- React 앱은 routing, animation, state management 등과 같은 특정 목적을 위한 타사 component를 포함할 수 있습니다.



## ReactJS - React 애플리케이션 만들기

https://www.tutorialspoint.com/reactjs/reactjs_creating_application.htm

앞에서 배웠듯이 React 라이브러리는 단순 응용 프로그램과 복잡한 응용 프로그램 모두에서 사용할 수 있습니다. 간단한 애플리케이션은 일반적으로 스크립트 섹션에 React 라이브러리를 포함합니다. 복잡한 애플리케이션에서 개발자는 코드를 여러 파일로 분할하고 코드를 표준 구조로 구성해야 합니다. 여기서 React 툴체인은 애플리케이션을 부트스트랩하기 위해 미리 정의된 구조를 제공합니다. 또한 개발자는 자신의 프로젝트 구조를 사용하여 코드를 구성할 수 있습니다.

간단하면서도 복잡한 React 애플리케이션을 만드는 방법을 살펴보겠습니다.

- [CDN을 사용한 간단한 애플리케이션](https://www.tutorialspoint.com/reactjs/reactjs_using_cdn.htm)
- [React Create App cli를 사용하는 복잡한 애플리케이션](https://www.tutorialspoint.com/reactjs/reactjs_using_create_react_app_tool.htm)
- [맞춤형 방식을 통한 복잡한 적용](https://www.tutorialspoint.com/reactjs/reactjs_customized_code.htm)

### 롤업 번들러 사용

롤업은 작고 빠른 JavaScript 번들러 중 하나입니다. 이번 장에서는 롤업 번들러를 어떻게 사용하는지 알아보자.

터미널을 열고 작업 공간으로 이동합니다.

``` bash
cd /go/to/your/workspace
```

다음으로, 비용 관리자 롤업 폴더를 생성하고 새로 생성된 폴더로 이동합니다. 또한 즐겨 사용하는 편집기나 IDE에서 폴더를 엽니다.

``` bash
mkdir expense-manager-rollup
cd expense-manager-rollup
```

다음으로 프로젝트를 생성하고 초기화합니다.

``` bash
npm init -y
```

다음으로 React 라이브러리(react 및 react-dom)를 설치합니다.

``` bash
npm install react@^17.0.0 react-dom@^17.0.0 --save
```

다음으로 개발 종속성으로 babel 및 사전 설정 라이브러리를 설치합니다.

``` bash
npm install @babel/preset-env @babel/preset-react
@babel/core @babel/plugin-proposal-class-properties -D
```

다음으로 개발 종속성으로 롤업 및 해당 플러그인 라이브러리를 설치합니다.

``` bash
npm i -D rollup postcss@8.1 @rollup/plugin-babel
@rollup/plugin-commonjs @rollup/plugin-node-resolve
@rollup/plugin-replace rollup-plugin-livereload
rollup-plugin-postcss rollup-plugin-serve postcss@8.1
postcss-modules@4 rollup-plugin-postcss
```

다음으로 비동기 프로그래밍을 위한 corejs 및 재생기 런타임을 설치합니다.

``` bash
npm i regenerator-runtime core-js
```

그런 다음 루트 폴더 아래에 babel 구성 파일 .babelrc를 만들어 babel 컴파일러를 구성합니다.

``` json
{
   "presets": [
      [
         "@babel/preset-env",
         {
            "useBuiltIns": "usage",
            "corejs": 3,
            "targets": "> 0.25%, not dead"
         }
      ],
      "@babel/preset-react"
   ],
   "plugins": [
      "@babel/plugin-proposal-class-properties"
   ]
}
```

그런 다음 루트 폴더에 rollup.config.js 파일을 생성하여 롤업 번들러를 구성합니다.

``` js
import babel from '@rollup/plugin-babel';
import resolve from '@rollup/plugin-node-resolve';
import commonjs from '@rollup/plugin-commonjs';
import replace from '@rollup/plugin-replace';
import serve from 'rollup-plugin-serve';
import livereload from 'rollup-plugin-livereload';
import postcss from 'rollup-plugin-postcss'

export default {
   input: 'src/index.js',
   output: {
      file: 'public/index.js',
      format: 'iife',
   },
   plugins: [
      commonjs({
         include: [
            'node_modules/**',
         ],
         exclude: [
            'node_modules/process-es6/**',
         ],
      }),
      resolve(),
      babel({
         exclude: 'node_modules/**'
      }),
      replace({
         'process.env.NODE_ENV': JSON.stringify('production'),
      }),
      postcss({
         autoModules: true
      }),
      livereload('public'),
      serve({
         contentBase: 'public',
         port: 3000,
         open: true,
      }), // index.html should be in root of project
   ]
}
```

다음으로 package.json을 업데이트하고 진입점(public/index.js 및 public/styles.css)과 애플리케이션을 빌드하고 실행하는 명령을 포함합니다.

``` json
...
"main": "public/index.js",
"style": "public/styles.css",
"files": [
   "public"
],
"scripts": {
   "start": "rollup -c -w",
   "build": "rollup"
},
...
```

다음으로, 응용 프로그램의 모든 소스 코드를 저장할 응용 프로그램의 루트 디렉터리에 src 폴더를 만듭니다.

다음으로 React 컴포넌트를 포함할 src 아래에 components라는 폴더를 만듭니다. 아이디어는 두 개의 파일을 만드는 것입니다. component .js는 컴포넌트 논리를 작성하고 component.css는 컴포넌트별 스타일을 포함합니다.

응용 프로그램의 최종 구조는 다음과 같습니다.

``` bash
|-- package-lock.json
|-- package.json
|-- rollup.config.js
|-- .babelrc
`-- public
   |-- index.html
`-- src
   |-- index.js
   `-- components
   |  |-- mycom.js
   |  |-- mycom.css
```

새 컴포넌트인 HelloWorld를 만들어 설정이 제대로 작동하는지 확인하겠습니다. Components 폴더 아래에 HelloWorld.js 파일을 생성하고 Hello World 메시지를 내보내는 간단한 컴포넌트를 작성합니다.

``` js
import React from "react";

class HelloWorld extends React.Component {
   render() {
      return (
         <div>
            <h1>Hello World!</h1>
         </div>
      );
   }
}
export default HelloWorld;
```

다음으로 src 폴더 아래에 기본 파일인 index.js를 만들고 새로 만든 컴포넌트를 호출합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import HelloWorld from './components/HelloWorld';

ReactDOM.render(
   <React.StrictMode>
      <HelloWorld />
   </React.StrictMode>,
   document.getElementById('root')
);
```

그런 다음 루트 디렉터리에 공용 폴더를 만듭니다.

다음으로, 애플리케이션의 진입점이 될 index.html(공용 폴더* 아래) html 파일을 만듭니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>Expense Manager :: Rollup version</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="./index.js"></script>
   </body>
</html>
```

다음으로 애플리케이션을 빌드하고 실행합니다.

``` bash
npm start
```

npm build 명령은 롤업을 실행하고 애플리케이션을 단일 파일인 dist/index.js 파일로 묶고 애플리케이션 서비스를 시작합니다. dev 명령은 소스 코드가 변경될 때마다 코드를 다시 컴파일하고 브라우저에서 변경 사항을 다시 로드합니다.

``` bash
> expense-manager-rollup@1.0.0 build /path/to/your/workspace/expense-manager-rollup 
> rollup -c 
rollup v2.36.1 
bundles src/index.js → dist\index.js... 
LiveReload enabled 
http://localhost:10001 -> /path/to/your/workspace/expense-manager-rollup/dist 
created dist\index.js in 4.7s 

waiting for changes...
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다. 서버 응용 프로그램은 아래와 같이 웹 페이지를 제공합니다.

![Hello World](.\images\hello_world.jpg)



## Parcel  번들러 사용

Parcel은 구성이 없는 빠른 번들러입니다. 애플리케이션의 진입점만 예상하고 종속성 자체를 해결하고 애플리케이션을 번들로 제공합니다. 이 장에서는 Parcel 번들러를 사용하는 방법을 알아보겠습니다.

먼저 Parcel 번들러를 설치합니다.

``` bash
npm install -g parcel-bundler
```

터미널을 열고 작업 공간으로 이동합니다.

``` bash
cd /go/to/your/workspace
```

다음으로 cost-manager-parcel 폴더를 생성하고 새로 생성된 폴더로 이동합니다. 또한 즐겨 사용하는 편집기나 IDE에서 폴더를 엽니다.

``` bash
mkdir expense-manager-parcel
cd expense-manager-parcel
```

다음으로 프로젝트를 생성하고 초기화합니다.

``` bash
npm init -y
```

다음으로 React 라이브러리(react 및 react-dom)를 설치합니다.

``` bash
npm install react@^17.0.0 react-dom@^17.0.0 --save
```

다음으로 개발 종속성으로 babel 및 사전 설정 라이브러리를 설치합니다.

``` bash
npm install @babel/preset-env @babel/preset-react @babel/core @babel/plugin-proposal-class-properties -D
```

그런 다음 루트 폴더 아래에 babel 구성 파일 .babelrc를 만들어 babel 컴파일러를 구성합니다.

``` json
{
"presets": [
"@babel/preset-env",
"@babel/preset-react"
],
"plugins": [
"@babel/plugin-proposal-class-properties"
]
}
```

다음으로 package.json을 업데이트하고 진입점(src/index.js)과 명령을 포함하여 애플리케이션을 빌드하고 실행합니다.

``` json
...
"main": "src/index.js",
"scripts": {
"start": "parcel public/index.html",
"build": "parcel build public/index.html --out-dir dist"
},
...
```

다음으로, 응용 프로그램의 모든 소스 코드를 저장할 응용 프로그램의 루트 디렉터리에 src 폴더를 만듭니다.

다음으로 React 컴포넌트를 포함할 src 아래에 components라는 폴더를 만듭니다. 아이디어는 두 개의 파일을 만드는 것입니다. component .js는 컴포넌트 논리를 작성하고 component.css는 컴포넌트별 스타일을 포함합니다.

응용 프로그램의 최종 구조는 다음과 같습니다.

``` bash
|-- package-lock.json
|-- package.json
|-- .babelrc
`-- public
|-- index.html
`-- src
|-- index.js
`-- components
| |-- mycom.js
| |-- mycom.css
```

새 컴포넌트인 HelloWorld를 만들어 설정이 제대로 작동하는지 확인하겠습니다. Components 폴더 아래에 HelloWorld.js 파일을 생성하고 Hello World 메시지를 내보내는 간단한 컴포넌트를 작성합니다.

``` js
import React from "react";

class HelloWorld extends React.Component {
   render() {
      return (
         <div>
            <h1>Hello World!</h1>
         </div>
      );
   }
}
export default HelloWorld;
```

다음으로 src 폴더 아래에 기본 파일인 index.js를 만들고 새로 만든 컴포넌트를 호출합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import HelloWorld from './components/HelloWorld';

ReactDOM.render(
   <React.StrictMode>
      <HelloWorld />
   </React.StrictMode>,
   document.getElementById('root')
);
```

그런 다음 루트 디렉터리에 공용 폴더를 만듭니다.

다음으로, 애플리케이션의 진입점이 될 html 파일 index.html(공용 폴더에 있음)을 만듭니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>Expense Manager :: Parcel version</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="../src/index.js"></script>
   </body>
</html>
```

다음으로 애플리케이션을 빌드하고 실행합니다.

``` bash
npm start
```

npm build 명령은 Parcel 명령을 실행합니다. 즉석에서 응용 프로그램을 번들로 제공하고 제공합니다. 소스 코드가 변경될 때마다 다시 컴파일되고 브라우저에서 변경 사항도 다시 로드합니다.

``` bash
> expense-manager-parcel@1.0.0 dev /go/to/your/workspace/expense-manager-parcel
> parcel index.html Server running at http://localhost:1234
√ Built in 10.41s.
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:1234를 입력하고 Enter 키를 누릅니다.

![Hello World](.\images\hello_world.jpg)



애플리케이션의 프로덕션 번들을 생성하여 프로덕션 서버에 배포하려면 빌드 명령을 사용합니다. dist 폴더 아래에 번들로 제공되는 모든 소스 코드가 포함된 index.js 파일을 생성합니다.

``` bash
npm run build
> expense-manager-parcel@1.0.0 build /go/to/your/workspace/expense-manager-parcel
> parcel build index.html --out-dir dist

√ Built in 6.42s.

dist\src.80621d09.js.map 270.23 KB 79ms
dist\src.80621d09.js 131.49 KB 4.67s
dist\index.html 221 B 1.63s
```



## ReactJS - JSX

https://www.tutorialspoint.com/reactjs/reactjs_jsx.htm

앞에서 배웠듯이 React JSX는 JavaScript의 확장입니다. 개발자는 XML 구문을 사용하여 가상 DOM을 생성할 수 있습니다. 순수한 JavaScript(React.createElement 함수 호출)로 컴파일됩니다. JavaScript로 컴파일되기 때문에 모든 유효한 JavaScript 코드 내에서 사용할 수 있습니다. 예를 들어 아래 코드는 완벽하게 유효합니다.

- 변수에 할당합니다.

  ``` js
  var greeting = <h1>Hello React!</h1>
  ```

- 조건에 따라 변수에 할당합니다.

  ``` js
  var canGreet = true;
  if(canGreet) {
  greeting = <h1>Hello React!</h1>
  }
  ```

- 함수의 반환 값으로 사용할 수 있습니다.

  ``` js
  function Greeting() {
  return <h1>Hello React!</h1>
  
  }
  greeting = Greeting()
  ```

- 함수의 인수로 사용할 수 있습니다.

  ``` js
  function Greet(message) {
  ReactDOM.render(message, document.getElementById('react-app')
  }
  Greet(<h1>Hello React!</h1>)
  ```



#### Expressions

JSX는 순수 JavaScript 구문으로 표현을 지원합니다. 표현식은 중괄호 { } 안에 넣어야 합니다. 표현식은 JSX가 정의된 컨텍스트에서 사용 가능한 모든 변수를 포함할 수 있습니다. 표현식을 사용하여 간단한 JSX를 만들어 보겠습니다.

##### Example

``` html
<script type="text/babel">
   var cTime = new Date().toTimeString();
   ReactDOM.render(
      <div><p>The current time is {cTime}</p></div>, 
      document.getElementById('react-app') );
</script>
```

##### Output

여기서 cTime은 JSX에서 표현식을 사용하여 사용합니다. 위 코드의 출력은 다음과 같습니다.

``` bash
The Current time is 21:19:56 GMT+0530(India Standard Time)
```

JSX에서 표현식을 사용할 때의 긍정적인 부작용 중 하나는 모든 문자열을 html 안전한 문자열로 변환하기 때문에 주입 공격을 방지한다는 것입니다.

#### 함수

JSX는 사용자 정의 JavaScript 기능을 지원합니다. 함수 사용법은 표현식과 유사합니다. 간단한 함수를 만들어 JSX 내부에서 사용해보자.

##### Example

``` html
<script type="text/babel">
   var cTime = new Date().toTimeString();
   ReactDOM.render(
      <div><p>The current time is {cTime}</p></div>, 
      document.getElementById('react-app') 
   );
</script>
```

##### Output

여기서 getCurrentTime()은 현재 시간을 가져오는 데 사용되며 출력은 아래에 지정된 것과 유사합니다.

``` bash
The Current time is 21:19:56 GMT+0530(India Standard Time)
```



#### Attributes

JSX는 속성과 같은 HTML을 지원합니다. 모든 HTML 태그 및 해당 속성이 지원됩니다. 속성은 일반 HTML 속성 이름 대신 camelCase 규칙을 사용하여 지정해야 하며 JavaScript DOM API를 따릅니다. 예를 들어 HTML의 class 속성은 className으로 정의되어야 합니다. 다음은 몇 가지 다른 예입니다.

- *htmlFor* instead of *for*
- *tabIndex* instead of *tabindex*
- *onClick* instead of *onclick*

##### Example

``` html
<style>
   .red { color: red }
</style>
<script type="text/babel">
   function getCurrentTime() {
      return new Date().toTimeString();
   }
   ReactDOM.render(
      <div>
         <p>The current time is <span className="red">{getCurrentTime()}</span></p>
      </div>,
      document.getElementById('react-app') 
   );
</script>
```

##### Output

출력은 다음과 같습니다 -

``` bash
The Current time is 22:36:55 GMT+0530(India Standard Time)
```



#### 속성의 표현

JSX는 속성 내에서 지정되는 표현식을 지원합니다. 속성에서 표현식과 함께 큰따옴표를 사용하면 안 됩니다. 큰따옴표를 사용하는 표현식 또는 문자열을 사용해야 합니다. 위의 예는 속성에서 표현식을 사용하도록 변경할 수 있습니다.

``` html
<style>
   .red { color: red }
</style>

<script type="text/babel">
   function getCurrentTime() {
      return new Date().toTimeString();
   }
   var class_name = "red";
   ReactDOM.render(
      <div>
         <p>The current time is <span className={class_name}>{getCurrentTime()}</span></p>
      </div>, 
      document.getElementById('react-app') 
   );
</script>
```



## ReactJS - Component

출처: https://www.tutorialspoint.com/reactjs/reactjs_components.htm

React 컴포넌트는 React 애플리케이션의 빌딩 블록입니다. 이번 장에서는 새로운 React 컴포넌트를 생성하는 방법과 React 컴포넌트의 기능에 대해 알아보겠습니다.

React 컴포넌트는 웹 페이지에서 사용자 인터페이스의 작은 청크를 나타냅니다. React 컴포넌트의 주요 작업은 사용자 인터페이스를 렌더링하고 내부 상태가 변경될 때마다 업데이트하는 것입니다. UI를 렌더링하는 것 외에도 사용자 인터페이스에 속한 이벤트를 관리합니다. 요약하자면 React 컴포넌트는 다음과 같은 기능을 제공합니다.

- 사용자 인터페이스의 초기 렌더링.
- 이벤트 관리 및 처리.
- 내부 상태가 변경될 때마다 사용자 인터페이스를 업데이트합니다.

React 컴포넌트는 세 가지 개념을 사용하여 이러한 기능을 수행합니다.

- **Properties** −  컴포넌트가 입력을 수신할 수 있도록 합니다.
- **Events** − 컴포넌트를 활성화하여 DOM 이벤트 및 최종 사용자 상호 작용을 관리합니다.
- **State** − 컴포넌트가 상태를 유지하도록 합니다. Stateful 컴포넌트는 상태와 관련하여 UI를 업데이트합니다.

다음 장에서 모든 개념을 하나씩 배우도록 합시다.



### React 컴포넌트 만들기

React 라이브러리에는 두 가지 컴포넌트 유형이 있습니다. 유형은 생성 방식에 따라 분류됩니다.

- **Function component** − 일반 JavaScript 기능을 사용합니다.
- **ES6 class component** − ES6 클래스를 사용합니다.

기능과 클래스 컴포넌트의 핵심 차이점은 다음과 같습니다.

- 기능 컴포넌트는 본질적으로 매우 최소화됩니다. 유일한 요구 사항은 React 요소를 반환하는 것입니다.

  ``` js
  function Hello() { 
     return '<div>Hello</div>' 
  }
  ```

- 약간의 추가 코딩으로 ES6 클래스 컴포넌트를 사용하여 동일한 기능을 수행할 수 있습니다.

  ``` js
  class ExpenseEntryItem extends React.Component {         
     render() { 
        return ( 
           <div>Hello</div> 
        ); 
     }
  }
  ```

- 클래스 컴포넌트는 기본적으로 상태 관리를 지원하지만 기능 컴포넌트는 상태 관리를 지원하지 않습니다. 그러나 React는 함수 컴포넌트가 상태를 유지하기 위해 후크인 useState()를 제공합니다.
- 클래스 컴포넌트는 라이프 사이클을 가지며 전용 콜백 API를 통해 각 라이프 사이클 이벤트에 액세스합니다. 함수 컴포넌트에는 수명 주기가 없습니다. 다시 말하지만, React는 함수 컴포넌트가 컴포넌트의 다른 단계에 액세스할 수 있도록 후크인 useEffect()를 제공합니다.



### 클래스 컴포넌트 만들기

비용 관리 앱에서 새로운 React 컴포넌트인 ExpenseEntryItem을 만들어 비용 입력 항목을 보여드리겠습니다. 경비 입력 항목은 이름, 금액, 날짜 및 범주로 구성됩니다. 비용 입력 항목의 개체 표현은 다음과 같습니다.

``` json
{ 
   'name': 'Mango juice', 
   'amount': 30.00, 
   'spend_date': '2020-10-10' 
   'category': 'Food', 
}
```

즐겨 사용하는 편집기에서 경비 관리 애플리케이션을 엽니다.

그런 다음 src/components 폴더 아래에 ExpenseEntryItem.css 파일을 만들어 컴포넌트의 스타일을 지정합니다.

그런 다음 React.Component를 확장하여 src/components 폴더 아래에 ExpenseEntryItem.js 파일을 생성합니다.

``` js
import React from 'react'; 
import './ExpenseEntryItem.css'; 
class ExpenseEntryItem extends React.Component { 
}
```

다음으로 ExpenseEntryItem 클래스 내에서 렌더링 메서드를 만듭니다.

``` js
class ExpenseEntryItem extends React.Component { 
   render() { 
   } 
}
```

다음으로 JSX를 사용하여 사용자 인터페이스를 만들고 render 메소드에서 반환합니다.

``` js
class ExpenseEntryItem extends React.Component {
   render() {
      return (
         <div>
            <div><b>Item:</b> <em>Mango Juice</em></div>
            <div><b>Amount:</b> <em>30.00</em></div>
            <div><b>Spend Date:</b> <em>2020-10-10</em></div>
            <div><b>Category:</b> <em>Food</em></div>
         </div>
      );
   }
}
```

그런 다음 컴포넌트를 기본 내보내기 클래스로 지정합니다.

``` js
import React from 'react';
import './ExpenseEntryItem.css';

class ExpenseEntryItem extends React.Component {
   render() {
      return (
         <div>
            <div><b>Item:</b> <em>Mango Juice</em></div>
            <div><b>Amount:</b> <em>30.00</em></div>
            <div><b>Spend Date:</b> <em>2020-10-10</em></div>
            <div><b>Category:</b> <em>Food</em></div>
         </div>
      );
   }
}
export default ExpenseEntryItem;
```

이제 첫 번째 React 컴포넌트를 성공적으로 만들었습니다. index.js에서 새로 만든 컴포넌트를 사용하겠습니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import ExpenseEntryItem from './components/ExpenseEntryItem'

ReactDOM.render(
   <React.StrictMode>
      <ExpenseEntryItem />
   </React.StrictMode>,
   document.getElementById('root')
);
```



### Example

다음과 같이 CDN을 사용하여 웹 페이지에서 동일한 기능을 수행할 수 있습니다.

``` html
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>React application :: ExpenseEntryItem component</title>
   </head>
   <body>
      <div id="react-app"></div>
       
      <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
      <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
      <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
      <script type="text/babel">
         class ExpenseEntryItem extends React.Component {
            render() {
               return (
                  <div>
                     <div><b>Item:</b> <em>Mango Juice</em></div>
                     <div><b>Amount:</b> <em>30.00</em></div>
                     <div><b>Spend Date:</b> <em>2020-10-10</em></div>
                     <div><b>Category:</b> <em>Food</em></div>
                  </div>
               );
            }
         }
         ReactDOM.render(
            <ExpenseEntryItem />,
            document.getElementById('react-app') );
      </script>
   </body>
</html>
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

#### Output

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

``` sh
Item: Mango Juice
Amount: 30.00
Spend Date: 2020-10-10
Category: Food
```



### 함수 컴포넌트 만들기

React 컴포넌트는 일반 JavaScript 기능을 사용하여 만들 수도 있지만 기능이 제한적입니다. 기능 기반 React 컴포넌트는 상태 관리 및 기타 고급 기능을 지원하지 않습니다. 간단한 컴포넌트를 빠르게 만드는 데 사용할 수 있습니다.

위의 ExpenseEntryItem은 아래에 지정된 대로 함수에서 다시 작성할 수 있습니다.

``` js
function ExpenseEntryItem() {
   return (
      <div>
         <div><b>Item:</b> <em>Mango Juice</em></div>
         <div><b>Amount:</b> <em>30.00</em></div>
         <div><b>Spend Date:</b> <em>2020-10-10</em></div>
         <div><b>Category:</b> <em>Food</em></div>
      </div>
   );
}
```

여기에서는 렌더링 기능만 포함했으며 간단한 React 컴포넌트를 만드는 것으로 충분합니다.



## ReactJS - Styling

일반적으로 React에서는 className 속성을 통해 CSS 클래스를 사용하여 컴포넌트의 스타일을 지정할 수 있습니다. React JSX는 JavaScript 표현을 지원하기 때문에 일반적인 CSS 방법론을 많이 사용할 수 있습니다. 상위 옵션 중 일부는 다음과 같습니다.

- **CSS stylesheet** − className과 함께 일반 CSS 스타일
- **Inline styling** − CSS 스타일은 camelCase 속성과 함께 JavaScript 개체입니다.
- **CSS Modules** − 로컬 범위 CSS 스타일.
- **Styled component** − 컴포넌트 수준 스타일.
- **Sass stylesheet** − 빌드 시 스타일을 일반 CSS로 변환하여 Sass 기반 CSS 스타일을 지원합니다.
- **Post processing stylesheet** − 빌드 시 스타일을 일반 CSS로 변환하여 사후 처리 스타일을 지원합니다.

이 장에서 컴포넌트의 스타일을 지정하기 위해 세 가지 중요한 방법론을 적용하는 방법을 알아보겠습니다.



### CSS Stylesheet

CSS 스타일시트는 일반적이고 오랜 시간 테스트를 거친 방법론입니다. 컴포넌트에 대한 CSS 스타일시트를 만들고 해당 특정 컴포넌트에 대한 모든 스타일을 입력하기만 하면 됩니다. 그런 다음 컴포넌트에서 className을 사용하여 스타일을 참조합니다.

ExpenseEntryItem 컴포넌트의 스타일을 지정해 보겠습니다.

즐겨 사용하는 편집기에서 경비 관리 애플리케이션을 엽니다.

다음으로 ExpenseEntryItem.css 파일을 열고 몇 가지 스타일을 추가합니다.

``` css
div.itemStyle {
color: brown;
font-size: 14px;
}
```

다음으로 ExpenseEntryItem.js를 열고 기본 컨테이너에 className을 추가합니다.

``` js
import React from 'react';
import './ExpenseEntryItem.css';

class ExpenseEntryItem extends React.Component {
   render() {
      return (
         <div className="itemStyle">
            <div><b>Item:</b> <em>Mango Juice</em></div>
            <div><b>Amount:</b> <em>30.00</em></div>
            <div><b>Spend Date:</b> <em>2020-10-10</em></div>
            <div><b>Category:</b> <em>Food</em></div>
         </div>
      );
   }
}
export default ExpenseEntryItem;
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![CSS Stylesheet](.\images\css_stylesheet.jpg)

CSS 스타일시트는 이해하고 사용하기 쉽습니다. 하지만 프로젝트 크기가 커지면 CSS 스타일도 늘어나 결국 클래스 이름에 많은 충돌이 발생합니다. 또한 CSS 파일을 직접 로드하는 것은 Webpack 번들러에서만 지원되며 다른 도구에서는 지원되지 않을 수 있습니다.



### Inline Styling

nline 스타일링은 React 컴포넌트의 스타일을 지정하는 가장 안전한 방법 중 하나입니다. DOM 기반 CSS 속성을 사용하여 모든 스타일을 JavaScript 객체로 선언하고 스타일 속성을 통해 컴포넌트에 설정합니다.

컴포넌트에 인라인 스타일을 추가해 보겠습니다.

즐겨 사용하는 편집기에서 비용 관리 애플리케이션을 열고 src 폴더에서 ExpenseEntryItem.js 파일을 수정합니다. 객체 유형의 변수를 선언하고 스타일을 설정합니다.

``` js
itemStyle = {
color: 'brown',
fontSize: '14px'
}
```

여기서 fontSize는 CSS 속성인 font-size를 나타냅니다. 모든 css 속성은 camelCase 형식으로 표현하여 사용할 수 있습니다.

다음으로 중괄호 {}를 사용하여 컴포넌트에서 itemStyle 스타일을 설정합니다.

``` js
render() {
   return (
      <div style={ this.itemStyle }>
         <div><b>Item:</b> <em>Mango Juice</em></div>
         <div><b>Amount:</b> <em>30.00</em></div>
         <div><b>Spend Date:</b> <em>2020-10-10</em></div>
         <div><b>Category:</b> <em>Food</em></div>
      </div>
   );
}
```

또한 스타일은 컴포넌트 내부에서 직접 설정할 수 있습니다.

``` js
render() {
   return (
      <div style={
         {
            color: 'brown',
            fontSize: '14px'
         }         
      }>
         <div><b>Item:</b> <em>Mango Juice</em></div>
         <div><b>Amount:</b> <em>30.00</em></div>
         <div><b>Spend Date:</b> <em>2020-10-10</em></div>
         <div><b>Category:</b> <em>Food</em></div>
      </div>
   );
}
```

이제 애플리케이션에서 인라인 스타일을 성공적으로 사용했습니다.

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![CSS Stylesheet](.\images\css_stylesheet.jpg)



### CSS Modules

CSS 모듈은 스타일을 정의하는 가장 안전하고 쉬운 방법을 제공합니다. 일반 구문과 함께 일반 CSS 스타일시트를 사용합니다. 스타일을 가져오는 동안 CSS 모듈은 이름 충돌이 발생하지 않도록 모든 스타일을 로컬 범위 스타일로 변환합니다. CSS 모듈을 사용하도록 컴포넌트를 변경해 보겠습니다.

즐겨 사용하는 편집기에서 경비 관리 애플리케이션을 엽니다.

그런 다음 src/components 폴더 아래에 새 스타일시트인 ExpenseEntryItem.module.css 파일을 만들고 일반 CSS 스타일을 작성합니다.

``` css
div.itemStyle {
color: 'brown';
font-size: 14px;
}
```

여기서 파일 명명 규칙은 매우 중요합니다. React 툴체인은 CSS 모듈을 통해 .module.css로 끝나는 CSS 파일을 사전 처리합니다. 그렇지 않으면 일반 스타일시트로 간주됩니다.

그런 다음 src/component 폴더에 있는 ExpenseEntryItem.js 파일을 열고 스타일을 가져옵니다.

``` js
import styles from './ExpenseEntryItem.module.css'
```

그런 다음 컴포넌트에서 스타일을 JavaScript 표현식으로 사용합니다.

``` js
<div className={styles.itemStyle}>
```

이제 애플리케이션에서 CSS 모듈을 성공적으로 사용했습니다.

최종적이고 완전한 코드는 -

``` js
import React from 'react';
import './ExpenseEntryItem.css';
import styles from './ExpenseEntryItem.module.css'

class ExpenseEntryItem extends React.Component {
   render() {
      return (
         <div className={styles.itemStyle} >
            <div><b>Item:</b> <em>Mango Juice</em></div>
            <div><b>Amount:</b> <em>30.00</em></div>
            <div><b>Spend Date:</b> <em>2020-10-10</em></div>
            <div><b>Category:</b> <em>Food</em></div>
         </div>
      );
   }
}
export default ExpenseEntryItem;
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![CSS Stylesheet](.\images\css_stylesheet.jpg)



## ReactJS - Properties (props)

출처: https://www.tutorialspoint.com/reactjs/reactjs_props.htm

React를 사용하면 개발자가 속성을 사용하여 동적 및 고급 컴포넌트를 만들 수 있습니다. 모든 컴포넌트는 HTML 속성과 유사한 속성을 가질 수 있으며 각 속성의 값은 속성(props)을 사용하여 컴포넌트 내부에서 액세스할 수 있습니다.

예를 들어, name 속성을 가진 Hello 컴포넌트는 this.props.name 변수를 통해 컴포넌트 내부에 접근할 수 있습니다.

``` html
<Hello name="React" />
// value of name will be "Hello* const name = this.props.name
```

React 속성은 다양한 유형의 속성 값을 지원합니다. 그들은 다음과 같습니다,

- String
- Number
- Datetime
- Array
- List
- Objects

이 장에서 하나씩 배워봅시다.

[속성을 사용하여 컴포넌트 만들기](https://www.tutorialspoint.com/reactjs/reactjs_create_component_using_properties.htm)

[내포된 컴포넌트](https://www.tutorialspoint.com/reactjs/reactjs_nested_components.htm)

[컴포넌트 사용](https://www.tutorialspoint.com/reactjs/reactjs_use_components.htm)

[컴포넌트 컬렉션](https://www.tutorialspoint.com/reactjs/reactjs_component_collection.htm)



## ReactJS - Event management

출처: https://www.tutorialspoint.com/reactjs/reactjs_events.htm

이벤트 관리는 웹 애플리케이션의 중요한 기능 중 하나입니다. 사용자가 응용 프로그램과 상호 작용할 수 있습니다. React는 웹 애플리케이션에서 사용 가능한 모든 이벤트를 지원합니다. React 이벤트 처리는 약간의 변경이 있는 DOM 이벤트와 매우 유사합니다. 이 장에서는 React 애플리케이션에서 이벤트를 처리하는 방법을 알아보겠습니다.

React 컴포넌트에서 이벤트를 처리하는 단계별 프로세스를 살펴보겠습니다.

- 주어진 이벤트를 처리할 이벤트 핸들러 메소드를 정의합니다.

  ``` js
  log() { 
     cosole.log("Event is fired"); 
  }
  ```

React는 이벤트 핸들러를 정의하기 위해 람다 함수를 사용하는 대체 구문을 제공합니다. 람다 구문은 -

``` js
log = () => { 
   cosole.log("Event is fired"); 
}
```

이벤트의 대상을 알고 싶다면 핸들러 메소드에 인수 e를 추가하십시오. React는 이벤트 대상 세부 정보를 핸들러 메서드로 보냅니다.

``` js
log(e) { 
   cosole.log("Event is fired"); 
   console.log(e.target); 
}
```

대체 람다 구문은 -

``` js
log = (e) => { 
   cosole.log("Event is fired"); 
   console.log(e.target); 
}
```

이벤트 중 추가 정보를 보내고 싶다면 추가 정보를 초기 인수로 추가한 다음 이벤트 대상에 대한 인수(e)를 추가합니다.

``` js
log(extra, e) { 
   cosole.log("Event is fired"); 
   console.log(e.target); 
   console.log(extra); 
   console.log(this); 
}
```

컴포넌트의 생성자에서 이벤트 처리기 메서드를 바인딩합니다. 이렇게 하면 이벤트 처리기 메서드에서 이를 사용할 수 있습니다.

``` js
constructor(props) { 
   super(props); 
   this.logContent = this.logContent.bind(this); 
}
```

이벤트 처리기가 대체 람다 구문으로 정의된 경우 바인딩이 필요하지 않습니다. 이 키워드는 이벤트 핸들러 메서드에 자동으로 바인딩됩니다.

아래에 지정된 대로 특정 이벤트에 대한 이벤트 핸들러 방법을 설정하십시오.

``` js
<div onClick={this.log}> ... </div>
```

추가 인수를 설정하려면 이벤트 핸들러 메서드를 바인딩한 다음 추가 정보를 두 번째 인수로 전달합니다.

``` js
<div onClick={this.log.bind(this, extra)}> ... </div>
```

대체 람다 구문은 다음과 같습니다.

``` js
<div onClick={this.log(extra, e)}> ... </div>
```

여기,

- [이벤트 인식 Component 만들기](https://www.tutorialspoint.com/reactjs/reactjs_create_event_aware_component.htm)
- [Expense Manager 앱에서 이벤트를 소개합니다.](https://www.tutorialspoint.com/reactjs/reactjs_introduce_events_expense_manager_app.htm)



## ReactJS - State Management

출처: https://www.tutorialspoint.com/reactjs/reactjs_state.htm

상태 관리는 모든 동적 응용 프로그램의 중요하고 피할 수 없는 기능 중 하나입니다. React는 React 컴포넌트에서 상태 관리를 지원하는 간단하고 유연한 API를 제공합니다. 이 장에서는 React 애플리케이션에서 상태를 유지하는 방법을 이해하도록 합시다.



### 상태란 무엇입니까?

State는 주어진 인스턴스에서 React 컴포넌트의 동적 속성 값을 나타냅니다. React는 각 component에 대한 동적 데이터 저장소를 제공합니다. 내부 데이터는 React component의 상태를 나타내며 component의 this.state 멤버 변수를 사용하여 액세스할 수 있습니다. component의 상태가 변경될 때마다 component는 새 상태와 함께 render() 메서드를 호출하여 자체적으로 다시 렌더링됩니다.

상태 관리를 더 잘 이해하기 위한 간단한 예는 실시간 클록 component를 분석하는 것입니다. 시계 component의 기본 작업은 주어진 인스턴스에서 위치의 날짜와 시간을 표시하는 것입니다. 현재 시간이 1초마다 바뀌므로 시계 component는 현재 날짜와 시간을 그 상태로 유지해야 합니다. 시계 component의 상태가 1초마다 변경되므로 시계의 render() 메서드가 1초마다 호출되고 render() 메서드는 현재 상태를 사용하여 현재 시간을 표시합니다.

상태의 간단한 표현은 다음과 같습니다 -

``` json
{
date: '2020-10-10 10:10:10'
}
```

이 장의 뒷부분에서 새 Clock component를 만들겠습니다.

여기,

[상태 관리 API](https://www.tutorialspoint.com/reactjs/reactjs_state_management_api.htm)

[상태 비저장 component](https://www.tutorialspoint.com/reactjs/reactjs_stateless_component.htm)

[React Hooks를 사용한 상태 관리](https://www.tutorialspoint.com/reactjs/reactjs_state_management_react_hooks.htm)

[컴포넌트 수명 주기](https://www.tutorialspoint.com/reactjs/reactjs_component_life_cycle.htm)

[React Hooks를 사용한 컴포넌트 라이프 사이클](https://www.tutorialspoint.com/reactjs/reactjs_component_life_cycle_react_hooks.htm)

[컴포넌트의 레이아웃](https://www.tutorialspoint.com/reactjs/reactjs_layout_component.htm)

[쪽수 매기기](https://www.tutorialspoint.com/reactjs/reactjs_pagination.htm)

[머티리얼 UI](https://www.tutorialspoint.com/reactjs/reactjs_material_ui.htm)



## ReactJS - Http Client Programming

출처: https://www.tutorialspoint.com/reactjs/reactjs_http_client.htm

Http 클라이언트 프로그래밍을 사용하면 응용 프로그램이 JavaScript를 통해 http 서버에 연결하고 데이터를 가져올 수 있습니다. 전체 디자인이 아닌 필요한 데이터만 가져오기 때문에 클라이언트와 서버 간의 데이터 전송을 줄이고 네트워크 속도를 향상시킵니다. 사용자 경험을 개선하고 모든 최신 웹 애플리케이션의 필수 기능이 되었습니다.

오늘날 많은 서버 측 응용 프로그램은 REST API(HTTP 프로토콜을 통한 기능)를 통해 기능을 노출하고 모든 클라이언트 응용 프로그램이 기능을 사용할 수 있도록 합니다.

React는 자체 http 프로그래밍 API를 제공하지 않지만 브라우저의 내장 fetch() API와 axios와 같은 타사 클라이언트 라이브러리를 지원하여 클라이언트 측 프로그래밍을 수행합니다. 이번 장에서는 React 애플리케이션에서 http 프로그래밍을 하는 방법에 대해 알아보겠습니다. 개발자는 이 장을 이해하기 위해 Http 프로그래밍에 대한 기본 지식이 있어야 합니다.



### Expense Rest API 서버

Http 프로그래밍을 하기 위한 전제 조건은 Http 프로토콜과 REST API 기술에 대한 기본 지식입니다. Http 프로그래밍에는 서버와 클라이언트의 두 부분이 포함됩니다. React는 클라이언트 측 애플리케이션 생성을 지원합니다. 널리 사용되는 웹 프레임워크인 Express는 서버 측 애플리케이션 생성을 지원합니다.

먼저 익스프레스 프레임워크를 사용하여 Expense Rest Api 서버를 만든 다음 브라우저의 내장 가져오기 API를 사용하여 ExpenseManager 애플리케이션에서 액세스해 보겠습니다.

명령 프롬프트를 열고 express-rest-api라는 새 폴더를 만듭니다.

``` sh
cd /go/to/workspace
mkdir apiserver
cd apiserver
```

아래 명령을 사용하여 새 노드 응용 프로그램을 초기화하십시오.

``` sh
npm init
```

npm init는 기본 프로젝트 세부 정보를 입력하라는 메시지를 표시합니다. 프로젝트 이름으로 apiserver를 입력하고 진입점으로 server.js를 입력합시다. 다른 구성은 기본 옵션으로 둡니다.

``` sh
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help json` for definitive documentation on these fields and exactly what they do.

Use `npm install <pkg>` afterwards to install a package and
save it as a dependency in the package.json file.

Press ^C at any time to quit.
package name: (apiserver)
version: (1.0.0)
description: Rest api for Expense Application
entry point: (index.js) server.js
test command:
git repository:
keywords:
author:
license: (ISC)
About to write to \path\to\workspace\expense-rest-api\package.json:
{
   "name": "expense-rest-api",
   "version": "1.0.0",
   "description": "Rest api for Expense Application",
   "main": "server.js",
   "scripts": {
      "test": "echo \"Error: no test specified\" && exit 1"
   },
   "author": "",
   "license": "ISC"
}
Is this OK? (yes) yes
```

다음으로 아래 명령을 사용하여 express, nedb 및 cors 모듈을 설치하십시오.

``` sh
npm install express nedb cors
```

- express는 서버 측 응용 프로그램을 만드는 데 사용됩니다.
- nedb는 비용 데이터를 저장하는 데 사용되는 데이터 저장소입니다.
- cors는 클라이언트 액세스 세부 정보를 구성하는 익스프레스 프레임워크용 미들웨어입니다.

다음으로 data.csv 파일을 만들고 테스트 목적으로 초기 비용 데이터로 채우겠습니다. 파일의 구조는 한 줄에 하나의 비용 항목을 포함하는 것입니다.

``` csv
Pizza,80,2020-10-10,Food
Grape Juice,30,2020-10-12,Food
Cinema,210,2020-10-16,Entertainment
Java Programming book,242,2020-10-15,Academic
Mango Juice,35,2020-10-16,Food
Dress,2000,2020-10-25,Cloth
Tour,2555,2020-10-29,Entertainment
Meals,300,2020-10-30,Food
Mobile,3500,2020-11-02,Gadgets
Exam Fees,1245,2020-11-04,Academic
```

다음으로, costdb.js 파일을 만들고 초기 비용 데이터를 데이터 저장소에 로드하는 코드를 포함합니다. 코드는 데이터 저장소에서 초기 데이터를 확인하고 데이터를 저장소에서 사용할 수 없는 경우에만 로드합니다.

``` js
var store = require("nedb")
var fs = require('fs');
var expenses = new store({ filename: "expense.db", autoload: true })
expenses.find({}, function (err, docs) {
   if (docs.length == 0) {
      loadExpenses();
   }
})
function loadExpenses() {
   readCsv("data.csv", function (data) {
      console.log(data);

      data.forEach(function (rec, idx) {
         item = {}
         item.name = rec[0];
         item.amount = parseFloat(rec[1]);
         item.spend_date = new Date(rec[2]);
         item.category = rec[3];

         expenses.insert(item, function (err, doc) {
            console.log('Inserted', doc.item_name, 'with ID', doc._id);
         })
      })
   })
}
function readCsv(file, callback) {
   fs.readFile(file, 'utf-8', function (err, data) {
      if (err) throw err;
      var lines = data.split('\r\n');
      var result = lines.map(function (line) {
         return line.split(',');
      });
      callback(result);
   });
}
module.exports = expenses
```

다음으로, server.js 파일을 만들고 비용 항목을 나열, 추가, 업데이트 및 삭제할 실제 코드를 포함합니다.

``` js
var express = require("express")
var cors = require('cors')
var expenseStore = require("./expensedb.js")
var app = express()
app.use(cors());
var bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
var HTTP_PORT = 8000
app.listen(HTTP_PORT, () => {
   console.log("Server running on port %PORT%".replace("%PORT%", HTTP_PORT))
});
app.get("/", (req, res, next) => {
   res.json({ "message": "Ok" })
});
app.get("/api/expenses", (req, res, next) => {
   expenseStore.find({}, function (err, docs) {
      res.json(docs);
   });
});
app.get("/api/expense/:id", (req, res, next) => {
   var id = req.params.id;
   expenseStore.find({ _id: id }, function (err, docs) {
      res.json(docs);
   })
});
app.post("/api/expense/", (req, res, next) => {
   var errors = []
   if (!req.body.item) {
      errors.push("No item specified");
   }
   var data = {
      name: req.body.name,
      amount: req.body.amount,
      category: req.body.category,
      spend_date: req.body.spend_date,
   }
   expenseStore.insert(data, function (err, docs) {
      return res.json(docs);
   });
})
app.put("/api/expense/:id", (req, res, next) => {
   var id = req.params.id;
   var errors = []
   if (!req.body.item) {
      errors.push("No item specified");
   }
   var data = {
      _id: id,
      name: req.body.name,
      amount: req.body.amount,
      category: req.body.category,
      spend_date: req.body.spend_date,
   }
   expenseStore.update( { _id: id }, data, function (err, docs) {
      return res.json(data);
   });
})
app.delete("/api/expense/:id", (req, res, next) => {
   var id = req.params.id;
   expenseStore.remove({ _id: id }, function (err, numDeleted) {
      res.json({ "message": "deleted" })
   });
})
app.use(function (req, res) {
   res.status(404);
});
```

이제 애플리케이션을 실행할 차례입니다.

``` sh
npm run start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:8000/을 입력합니다.

``` json
{
"message": "Ok"
}
```

응용 프로그램이 제대로 작동하는지 확인합니다.

마지막으로 URL을 http://localhost:8000/api/expense로 변경하고 Enter 키를 누릅니다. 브라우저는 초기 비용 항목을 JSON 형식으로 표시합니다.

``` json
[
   ...
   {
      "name": "Pizza",
      "amount": 80,
      "spend_date": "2020-10-10T00:00:00.000Z",
      "category": "Food",
      "_id": "5H8rK8lLGJPVZ3gD"
   },
   ...
]
```

다음 섹션에서 fetch() API를 통해 지출 관리자 애플리케이션에서 새로 생성된 비용 서버를 사용하겠습니다.



### The fetch() api

React에서 클라이언트 측 프로그래밍을 보여주는 새 애플리케이션을 만들어 보겠습니다.

먼저 React 애플리케이션 생성 장의 지침에 따라 Create React App 또는 Rollup 번들러를 사용하여 새로운 react 애플리케이션 react-http-app을 생성합니다.

그런 다음 즐겨 사용하는 편집기에서 응용 프로그램을 엽니다.

그런 다음 응용 프로그램의 루트 디렉터리 아래에 src 폴더를 만듭니다.

다음으로 src 폴더 아래에 components 폴더를 생성합니다.

그런 다음 src/components 폴더 아래에 ExpenseEntryItemList.css 파일을 만들고 일반 테이블 스타일을 포함합니다.

``` css
html {
   font-family: sans-serif;
}
table {
   border-collapse: collapse;
   border: 2px solid rgb(200,200,200);
   letter-spacing: 1px;
   font-size: 0.8rem;
}
td, th {
   border: 1px solid rgb(190,190,190);
   padding: 10px 20px;
}
th {
   background-color: rgb(235,235,235);
}
td, th {
   text-align: left;
}
tr:nth-child(even) td {
   background-color: rgb(250,250,250);
}
tr:nth-child(odd) td {
   background-color: rgb(245,245,245);
}
caption {
   padding: 10px;
}
tr.highlight td { 
    background-color: #a6a8bd;
}
```

다음으로 src/components 폴더 아래에 ExpenseEntryItemList.js 파일을 생성하고 편집을 시작합니다.

다음으로 React 라이브러리를 가져옵니다.

``` js
import React from 'react';
```

다음으로 ExpenseEntryItemList라는 클래스를 만들고 props를 사용하여 생성자를 호출합니다.

``` js
class ExpenseEntryItemList extends React.Component {
   constructor(props) {
      super(props);
   }
}
```

다음으로 생성자에서 빈 목록으로 상태를 초기화합니다.

``` js
this.state = {
   isLoaded: false,
   items: []
}
```

다음으로, 원격 서버에서 수신한 항목의 형식을 지정하는 setItems 메서드를 만든 다음 component의 상태로 설정합니다.

``` js
setItems(remoteItems) {
   var items = [];
   remoteItems.forEach((item) => {
      let newItem = {
         id: item._id,
         name: item.name,
         amount: item.amount,
         spendDate: item.spend_date,
         category: item.category
      }
      items.push(newItem)
   });
   this.setState({
      isLoaded: true,
      items: items
   });
}
```

다음으로 fetchRemoteItems 메서드를 추가하여 서버에서 항목을 가져옵니다.

``` js
fetchRemoteItems() {
   fetch("http://localhost:8000/api/expenses")
      .then(res => res.json())
      .then(
         (result) => {
            this.setItems(result);
         },
         (error) => {
            this.setState({
               isLoaded: false,
               error
            });
         }
      )
}
```

여기,

- fetch api는 원격 서버에서 항목을 가져오는 데 사용됩니다.

- setItems는 상태의 항목을 형식화하고 저장하는 데 사용됩니다.

그런 다음 deleteRemoteItem 메서드를 추가하여 원격 서버에서 항목을 삭제합니다.

``` js
deleteRemoteItem(id) {
   fetch('http://localhost:8000/api/expense/' + id, { method: 'DELETE' })
      .then(res => res.json())
      .then(
         () => {
            this.fetchRemoteItems()
         }
      )
}
```

여기,

- fetch api는 원격 서버에서 항목을 삭제하고 가져오는 데 사용됩니다.

- setItems는 상태의 항목을 형식화하고 저장하는 데 다시 사용됩니다.

다음으로 componentDidMount 수명 주기 API를 호출하여 탑재 단계에서 component에 항목을 로드합니다.

``` js
componentDidMount() { 
   this.fetchRemoteItems(); 
}
```

다음으로, 목록에서 항목을 제거하는 이벤트 핸들러를 작성하십시오.

``` js
handleDelete = (id, e) => { 
   e.preventDefault(); 
   console.log(id); 

   this.deleteRemoteItem(id); 
}
```

다음으로 render 메소드를 작성합니다.

``` js
render() {
   let lists = [];
   if (this.state.isLoaded) {
      lists = this.state.items.map((item) =>
         <tr key={item.id} onMouseEnter={this.handleMouseEnter} onMouseLeave={this.handleMouseLeave}>
            <td>{item.name}</td>
            <td>{item.amount}</td>
            <td>{new Date(item.spendDate).toDateString()}</td>
            <td>{item.category}</td>
            <td><a href="#" onClick={(e) => this.handleDelete(item.id, e)}>Remove</a></td>
         </tr>
      );
   }
   return (
      <div>
         <table onMouseOver={this.handleMouseOver}>
            <thead>
               <tr>
                  <th>Item</th>
                  <th>Amount</th>
                  <th>Date</th>
                  <th>Category</th>
                  <th>Remove</th>
               </tr>
            </thead>
            <tbody>
               {lists}
            </tbody>
         </table>
      </div>
   );
}
```

마지막으로 component를 내보냅니다.

``` js
export default ExpenseEntryItemList;
```

다음으로 src 폴더 아래에 index.js 파일을 생성하고 ExpenseEntryItemList 컴포넌트를 사용합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import ExpenseEntryItemList from './components/ExpenseEntryItemList';

ReactDOM.render(
   <React.StrictMode>
         <ExpenseEntryItemList />
   </React.StrictMode>,
   document.getElementById('root')
);
```

마지막으로 루트 폴더 아래에 공용 폴더를 생성하고 index.html 파일을 생성합니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>React App</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="./index.js"></script>
   </body>
</html>
```

다음으로 새 터미널 창을 열고 서버 애플리케이션을 시작합니다.

``` sh
cd /go/to/server/application
npm start
```

다음으로 npm 명령을 사용하여 클라이언트 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![Material](.\images\material.jpg)

제거 링크를 클릭하여 항목을 제거해 보십시오.

![Materials](.\images\materials.jpg)



## ReactJS - Form Programming

출처: https://www.tutorialspoint.com/reactjs/reactjs_form.htm

양식 프로그래밍의 특성은 상태를 유지해야 합니다. 사용자가 양식과 상호 작용할 때 입력 필드 정보가 변경되기 때문입니다. 그러나 앞서 배웠듯이 React 라이브러리는 자체적으로 상태 정보를 저장하거나 유지하지 않으며 component는 state를 관리하기 위해 state management API를 사용해야 합니다. 이를 고려하여 React는 폼 프로그래밍을 지원하기 위해 두 가지 유형의 컴포넌트를 제공합니다.

- **Controlled component** - controlled component에서 React는 모든 입력 요소에 대한 특별한 속성, 값을 제공하고 입력 요소를 제어합니다. value 속성은 입력 요소의 값을 가져오고 설정하는 데 사용할 수 있습니다. component 의 state 와 동기화되어야 합니다.
- **Uncontrolled component** − uncontrolled component 에서 React는 form 프로그래밍에 대한 최소한의 지원을 제공합니다. form 프로그래밍을 수행하려면 Ref 개념(런타임 동안 React component에서 DOM 요소를 가져오는 또 다른 반응 개념)을 사용해야 합니다.

이 장에서는 제어된 component와 제어되지 않는 component를 모두 사용하여 형식 프로그래밍에 대해 알아보겠습니다.

- [Controlled component](https://www.tutorialspoint.com/reactjs/reactjs_controlled_component.htm)
- [Uncontrolled Component](https://www.tutorialspoint.com/reactjs/reactjs_uncontrolled_component.htm)
- [Formik](https://www.tutorialspoint.com/reactjs/reactjs_formik.htm)



## ReactJS - Routing

웹 애플리케이션에서 라우팅은 웹 URL을 웹 애플리케이션의 특정 리소스에 바인딩하는 프로세스입니다. React에서는 URL을 component에 바인딩합니다. React는 기본적으로 사용자 인터페이스 라이브러리이기 때문에 기본적으로 라우팅을 지원하지 않습니다. React 커뮤니티는 React 애플리케이션에서 라우팅을 처리하기 위해 많은 타사 component를 제공합니다. React 애플리케이션을 위한 최고의 라우팅 라이브러리인 React Router에 대해 알아보겠습니다.



### Install React Router

Expense Manager 애플리케이션에 React Router component를 설치하는 방법을 알아보겠습니다.

명령 프롬프트를 열고 애플리케이션의 루트 폴더로 이동합니다.

``` sh
cd /go/to/expense/manager
```

아래 명령을 사용하여 react 라우터를 설치합니다.

``` sh
npm install react-router-dom --save
```

#### Concept

React 라우터는 React 애플리케이션에서 탐색을 관리하기 위해 네 가지 component를 제공합니다.

- **Router** − 라우터는 최상위 component입니다. 여기에는 전체 응용 프로그램이 포함됩니다.

- **Link** −  html의 앵커 태그와 유사합니다. 참조 텍스트와 함께 대상 URL을 설정합니다.

  ``` html
  <Link to="/">Home</Link>
  ```

  여기서 to 속성은 대상 URL을 설정하는 데 사용됩니다.

- **Switch & Route** − 둘 다 함께 사용됩니다. 대상 URL을 component에 매핑합니다. Switch는 상위 component이고 Route는 하위 component입니다. 스위치 component에는 여러 경로 component가 있을 수 있으며 각 경로 component는 특정 URL을 component에 매핑합니다.

  ``` html
  <Switch>
     <Route exact path="/">
        <Home />
     </Route>
     <Route path="/home">
        <Home />
     </Route>
     <Route path="/list">
        <ExpenseEntryItemList />
     </Route>
  </Switch>
  ```

  여기에서 경로 속성은 URL과 일치하는 데 사용됩니다. 기본적으로 Switch는 프로그래밍 언어의 기존 switch 문과 유사하게 작동합니다. 대상 url을 각 자식 경로(경로 속성)와 순서대로 하나씩 일치시키고 첫 번째 일치 경로를 호출합니다.

  라우터 component와 함께 React 라우터는 url에서 동적 정보를 설정하고 가져오는 옵션을 제공합니다. 예를 들어, 기사 웹사이트에서 URL에 기사 유형이 첨부되어 있을 수 있으며 기사 유형은 동적으로 추출되어야 하며 특정 유형의 기사를 가져오는 데 사용해야 합니다.

  ``` html
  <Link to="/article/c">C Programming</Link>
  <Link to="/article/java">Java Programming</Link>
  
  ...
  ...
  
  <Switch>
    <Route path="article/:tag" children={<ArticleList />} />
  </Switch>
  ```

  그런 다음 자식 component(클래스 component)에서

  ``` js
  import { withRouter } from "react-router"
  
  class ArticleList extends React.Component {
     ...
     ...
     static getDerivedStateFromProps(props, state) {
        let newState = {
           tag: props.match.params.tag
        }
        return newState;
     }
     ...
     ...
  }
  export default WithRouter(ArticleList)
  ```

  여기에서 WithRouter는 ArticleList 컴포넌트가 props를 통해 태그 정보에 접근할 수 있도록 합니다.

  기능적 component에서 동일한 작업을 다르게 수행할 수 있습니다.

  ``` js
  function ArticleList() {
     let { tag } = useParams();
     return (
        <div>
           <h3>ID: {id}</h3>
        </div>
     );
  }
  ```

  여기서 useParams는 React Router 컴포넌트에서 제공하는 커스텀 React Hooks입니다.



### Nested routing

React 라우터는 중첩 라우팅도 지원합니다. React 라우터는 다른 React Hooks인 useRouteMatch()를 제공하여 중첩 경로에서 상위 경로 정보를 추출합니다.

``` js
function ArticleList() {
   // 상위 URL과 일치하는 경로를 가져옵니다.
   let { path, url } = useRouteMatch();

   return (
      <div>
         <h2>Articles</h2>
         <ul>
            <li>
               <Link to={`${url}/pointer`}>C with pointer</Link>
            </li>
            <li>
               <Link to={`${url}/basics`}>C basics</Link>
            </li>
         </ul>

         <Switch>
            <Route exact path={path}>
               <h3>Please select an article.</h3>
            </Route>
            <Route path={`${path}/:article`}>
               <Article />
            </Route>
         </Switch>
      </div>
   );
}

function Article() {
   let { article } = useParams();
   return (
      <div>
         <h3>The select article is {article}</h3>
      </div>
   );
}
```

여기서 useRouteMatch는 일치하는 경로와 대상 URL을 반환합니다. url은 다음 수준의 링크를 만드는 데 사용할 수 있고 경로는 다음 수준의 component/화면을 매핑하는 데 사용할 수 있습니다.



### Creating navigation

비용 관리자 응용 프로그램에서 가능한 라우팅을 만들어 라우팅을 수행하는 방법을 알아보겠습니다. 응용 프로그램의 최소 화면은 다음과 같습니다.

- **Home screen** − 애플리케이션의 랜딩 또는 초기 화면
- **Expense list screen** −  비용 항목을 표 형식으로 표시
- **Expense add screen** −  비용 항목을 추가하는 인터페이스 추가

먼저 React 애플리케이션 생성 장의 지침에 따라 Create React App 또는 Rollup 번들러를 사용하여 새로운 react 애플리케이션 react-router-app을 생성합니다.

그런 다음 즐겨 사용하는 편집기에서 응용 프로그램을 엽니다.

그런 다음 응용 프로그램의 루트 디렉터리 아래에 src 폴더를 만듭니다.

다음으로 src 폴더 아래에 components 폴더를 생성합니다.

다음으로 src/components 폴더에 Home.js 파일을 생성하고 편집을 시작합니다.



다음으로 React 라이브러리를 가져옵니다.

``` js
import React from 'react';
```



다음으로 React 라우터 라이브러리에서 Link를 가져옵니다.

``` js
import { Link } from 'react-router-dom'
```



다음으로 props를 사용하여 클래스 Home 및 호출 생성자를 만듭니다.

``` js
class Home extends React.Component {
   constructor(props) {
      super(props);
   }
}
```



다음으로 render() 메소드를 추가하고 환영 메시지와 비용 추가 및 나열 화면에 대한 링크를 표시합니다.

``` js
render() {
   return (
      <div>
         <p>Welcome to the React tutorial</p>
         <p><Link to="/list">Click here</Link> to view expense list</p>
         <p><Link to="/add">Click here</Link> to add new expenses</p>
      </div>
   )
}
```



마지막으로 component를 내보냅니다.

``` js
export default Home;
```



Home component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from 'react';
import { Link } from 'react-router-dom'

class Home extends React.Component {
   constructor(props) {
      super(props);
   }
   render() {
      return (
         <div>
            <p>Welcome to the React tutorial</p>
            <p><Link to="/list">Click here</Link> to view expense list</p>
            <p><Link to="/add">Click here</Link> to add new expenses</p>
         </div>
      )
   }
}
export default Home;
```



다음으로 src/components 폴더 아래에 ExpenseEntryItemList.js 파일을 생성하고 ExpenseEntryItemList 컴포넌트를 생성합니다.

``` js
import React from 'react';
import { Link } from 'react-router-dom'

class ExpenseEntryItemList extends React.Component {
   constructor(props) {
      super(props);
   }
   render() {
      return (
         <div>
            <h1>Expenses</h1>
            <p><Link to="/add">Click here</Link> to add new expenses</p>
            <div>
               Expense list
            </div>
         </div>
      )
   }
}
export default ExpenseEntryItemList;
```



다음으로 src/components 폴더 아래에 ExpenseEntryItemForm.js 파일을 생성하고 ExpenseEntryItemForm 컴포넌트를 생성합니다.

``` js
import React from 'react';
import { Link } from 'react-router-dom'

class ExpenseEntryItemForm extends React.Component {
   constructor(props) {
      super(props);
   }
   render() {
      return (
         <div>
            <h1>Add Expense item</h1>
            <p><Link to="/list">Click here</Link> to view new expense list</p>
            <div>
               Expense form
            </div>
         </div>
      )
   }
}
export default ExpenseEntryItemForm;
```



다음으로 src/components 폴더 아래에 App.css 파일을 만들고 일반 CSS 스타일을 추가합니다.

``` css
html {
   font-family: sans-serif;
}
a{
   text-decoration: none;
}
p, li, a{
   font-size: 14px;
}
nav ul {
   width: 100%;
   list-style-type: none;
   margin: 0;
   padding: 0;
   overflow: hidden;
   background-color: rgb(235,235,235);
}
nav li {
   float: left;
}
nav li a {
   display: block;
   color: black;
   text-align: center;
   padding: 14px 16px;
   text-decoration: none;
   font-size: 16px;
}
nav li a:hover {
   background-color: rgb(187, 202, 211);
}
```

다음으로 src/components 폴더에 App.js 파일을 생성하고 편집을 시작합니다. App component의 목적은 하나의 component에서 모든 화면을 처리하는 것입니다. 라우팅을 구성하고 다른 모든 component에 대한 탐색을 활성화합니다.



다음으로 React 라이브러리 및 기타 component를 가져옵니다.

``` js
import React from 'react';

import Home from './Home'
import ExpenseEntryItemList from './ExpenseEntryItemList'
import ExpenseEntryItemForm from './ExpenseEntryItemForm'

import './App.css'
```



다음으로 React 라우터 component를 가져옵니다.

``` js
import {
   BrowserRouter as Router,
   Link,
   Switch,
   Route
} from 'react-router-dom'
```



다음으로 render() 메서드를 작성하고 라우팅을 구성합니다.

``` js
function App() {
   return (
      <Router>
         <div>
            <nav>
               <ul>
                  <li>
                     <Link to="/">Home</Link>
                  </li>
                  <li>
                     <Link to="/list">List Expenses</Link>
                  </li>
                  <li>
                     <Link to="/add">Add Expense</Link>
                  </li>
               </ul>
            </nav>

            <Switch>
               <Route path="/list">
                  <ExpenseEntryItemList />
               </Route>
               <Route path="/add">
                  <ExpenseEntryItemForm />
               </Route>
               <Route path="/">
                  <Home />
               </Route>
            </Switch>
         </div>
      </Router>
   );
}
```



다음으로 src 폴더 아래에 index.js 파일을 생성하고 App 컴포넌트를 사용합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';

ReactDOM.render(
   <React.StrictMode>
      <App />
   </React.StrictMode>,
   document.getElementById('root')
);
```



마지막으로 루트 폴더 아래에 공용 폴더를 생성하고 index.html 파일을 생성합니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>React router app</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="./index.js"></script>
   </body>
</html>
```



다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

링크를 탐색하고 라우팅이 작동하는지 확인하십시오.

![Navigate Links](.\images\navigate_links.jpg)



## ReactJS - Redux

출처: https://www.tutorialspoint.com/reactjs/reactjs_redux.htm

React redux는 React용 고급 상태 관리 라이브러리입니다. 앞에서 배운 것처럼 React는 component 수준 상태 관리만 지원합니다. 크고 복잡한 응용 프로그램에서는 많은 수의 component가 사용됩니다. React는 상태를 최상위 component로 이동하고 속성을 사용하여 상태를 중첩 component에 전달할 것을 권장합니다. 어느 정도 도움이 되지만 component가 증가하면 복잡해집니다.

redux 칩에 반응하고 애플리케이션 수준에서 상태를 유지하는 데 도움이 됩니다. React redux를 사용하면 모든 component가 언제든지 상태에 액세스할 수 있습니다. 또한 모든 component가 언제든지 응용 프로그램의 상태를 변경할 수 있습니다.

이번 장에서는 React redux를 사용하여 React 애플리케이션을 작성하는 방법에 대해 알아보겠습니다.



### Concepts

React redux는 Redux 저장소라는 단일 장소에서 애플리케이션의 상태를 유지합니다. React 컴포넌트는 스토어에서 최신 상태를 얻을 수 있을 뿐만 아니라 언제든지 상태를 변경할 수 있습니다. Redux는 애플리케이션의 현재 상태를 가져오고 설정하는 간단한 프로세스를 제공하며 아래 개념을 포함합니다.

**Store** − 애플리케이션의 상태를 저장하는 중앙 위치입니다.

**Actions** − Action 은 수행할 작업의 유형과 작업을 수행하는 데 필요한 입력(페이로드라고 함)이 있는 일반 개체입니다. 예를 들어 상점에 항목을 추가하는 작업에는 ADD_ITEM 유형이 포함되고 항목 세부 정보가 있는 개체가 페이로드로 포함됩니다. 동작은 다음과 같이 나타낼 수 있습니다.

``` js
{
type: 'ADD_ITEM',
payload: { name: '..', ... }
}
```

***Reducers\*** −  리듀서는 기존 상태와 현재 작업을 기반으로 새 상태를 만드는 데 사용되는 순수 함수입니다. 새로 생성된 상태를 반환합니다. 예를 들어 항목 추가 시나리오에서 새 항목 목록을 만들고 상태와 새 항목의 항목을 병합하고 새로 만든 목록을 반환합니다.

***Action creators\*** −  액션 생성자는 액션에 필요한 적절한 액션 유형과 데이터로 액션을 생성하고 액션을 반환합니다. 예를 들어, addItem 작업 생성자는 아래 객체를 반환합니다.

``` js
{
type: 'ADD_ITEM',
payload: { name: '..', ... }
}
```

**Component** −  component는 저장소에 연결하여 현재 상태를 가져오고 저장소에 작업을 전달하여 저장소가 작업을 실행하고 현재 상태를 업데이트하도록 할 수 있습니다.

일반적인 redux 스토어의 작업 흐름은 아래와 같이 나타낼 수 있습니다.

![Redux Store](.\images\redux_store.jpg)

- React component는 저장소를 구독하고 응용 프로그램을 초기화하는 동안 최신 상태를 가져옵니다.
- 상태를 변경하기 위해 React 컴포넌트는 필요한 액션을 생성하고 액션을 전달합니다.
- Reducer는 작업을 기반으로 새 상태를 만들고 반환합니다. 새로운 상태로 업데이트 자체를 저장합니다.
- 상태가 변경되면 store는 업데이트된 상태를 구독된 모든 component에 보냅니다.



### Redux API

Redux는 component를 저장소에 연결하고 component가 저장소의 상태를 가져오고 설정할 수 있도록 하는 단일 API를 제공합니다.

연결 API의 서명은 -

``` js
function connect(mapStateToProps?, mapDispatchToProps?, mergeProps?, options?)
```

모든 매개변수는 선택 사항이며 HOC(고차 component)를 반환합니다. 고차 component는 component를 래핑하고 새 component를 반환하는 함수입니다.

``` js
let hoc = connect(mapStateToProps, mapDispatchToProps)
let connectedComponent = hoc(component)
```

대부분의 경우에 충분할 처음 두 매개변수를 살펴보겠습니다.

- **mapStateToProps** − 아래 서명이 있는 기능을 수락합니다.

  ``` js
  (state, ownProps?) => Object
  ```

  여기서 state는 저장소의 현재 상태를 나타내고 Object는 component의 새 props를 나타냅니다. 스토어 상태가 업데이트될 때마다 호출됩니다.

  ``` js
  (state) => { prop1: this.state.anyvalue }
  ```

- **mapDispatchToProps** − 아래 서명이 있는 기능을 수락합니다.

  ``` js
  Object | (dispatch, ownProps?) => Object
  ```

  여기에서 dispatch는 redux 저장소에서 작업을 디스패치하는 데 사용되는 디스패치 객체를 참조하고 Object는 하나 이상의 디스패치 기능을 component의 props로 참조합니다.

  ``` js
  (dispatch) => {
  addDispatcher: (dispatch) => dispatch({ type: 'ADD_ITEM', payload: { } }),
  removeispatcher: (dispatch) => dispatch({ type: 'REMOVE_ITEM', payload: { } }),
  }
  ```

  

### Provider component

React Redux는 연결 API를 사용하여 저장소에 연결된 모든 중첩 component에서 Redux 저장소를 사용할 수 있도록 하는 Provider component와 유일한 목적을 제공합니다. 샘플 코드는 다음과 같습니다.

``` js
import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import { App } from './App'
import createStore from './createReduxStore'

const store = createStore()

ReactDOM.render(
   <Provider store={store}>
      <App />
   </Provider>,
   document.getElementById('root')
)
```

이제 앱 component 내부의 모든 component는 연결 API를 사용하여 Redux 스토어에 액세스할 수 있습니다.



### 작업 예

비용 관리자 애플리케이션을 다시 만들고 React redux 개념을 사용하여 애플리케이션의 상태를 유지해 보겠습니다.

먼저 Create a React 애플리케이션 장의 지침에 따라 Create React App 또는 Rollup 번들러를 사용하여 새로운 react 애플리케이션 react-message-app을 생성합니다.

다음으로 Redux 및 React redux 라이브러리를 설치합니다.

``` sh
npm install redux react-redux --save
```

다음으로 uuid 라이브러리를 설치하여 새로운 비용에 대한 고유 식별자를 생성합니다.

``` sh
npm install uuid --save
```

그런 다음 즐겨 사용하는 편집기에서 응용 프로그램을 엽니다.

그런 다음 응용 프로그램의 루트 디렉터리 아래에 src 폴더를 만듭니다.

다음으로 src 폴더 아래에 actions 폴더를 생성합니다.

다음으로 src/actions 폴더에 types.js 파일을 생성하고 편집을 시작합니다.

다음으로 두 가지 작업 유형을 추가합니다. 하나는 비용 추가용이고 다른 하나는 비용 제거용입니다.

``` js
export const ADD_EXPENSE = 'ADD_EXPENSE';
export const DELETE_EXPENSE = 'DELETE_EXPENSE';
```

다음으로 src/actions 폴더에 index.js 파일을 생성하여 액션을 추가하고 편집을 시작합니다.

그런 다음 uuid를 가져와 고유 식별자를 만듭니다.

``` js
import { v4 as uuidv4 } from 'uuid';
```

다음으로 작업 유형을 가져옵니다.

``` js
import { ADD_EXPENSE, DELETE_EXPENSE } from './types';
```

다음으로 비용 추가를 위한 액션 유형을 반환하는 새 함수를 추가하고 내보냅니다.

``` js
export const addExpense = ({ name, amount, spendDate, category }) => ({
   type: ADD_EXPENSE,
   payload: {
      id: uuidv4(),
      name,
      amount,
      spendDate,
      category
   }
});
```

여기에서 함수는 비용 정보의 페이로드와 함께 ADD_EXPENSE의 비용 개체 및 반환 작업 유형을 예상합니다.

다음으로, 비용 삭제를 위한 액션 타입을 반환하는 새로운 함수를 추가하고 내보냅니다.

``` js
export const deleteExpense = id => ({
   type: DELETE_EXPENSE,
   payload: {
      id
   }
});
```

여기서 함수는 비용 항목의 id가 삭제되고 비용 id의 페이로드와 함께 'DELETE_EXPENSE'의 작업 유형을 반환할 것으로 예상합니다.

작업의 전체 소스 코드는 다음과 같습니다.

``` js
import { v4 as uuidv4 } from 'uuid';
import { ADD_EXPENSE, DELETE_EXPENSE } from './types';

export const addExpense = ({ name, amount, spendDate, category }) => ({
   type: ADD_EXPENSE,
   payload: {
      id: uuidv4(),
      name,
      amount,
      spendDate,
      category
   }
});
export const deleteExpense = id => ({
   type: DELETE_EXPENSE,
   payload: {
      id
   }
});
```

그런 다음 src 폴더 아래에 새 폴더인 reducers를 만듭니다.

그런 다음 src/reducers 아래에 index.js 파일을 만들어 reducer 함수를 작성하고 편집을 시작합니다.

다음으로 작업 유형을 가져옵니다.

``` js
import { ADD_EXPENSE, DELETE_EXPENSE } from '../actions/types';
```

다음으로, redux 스토어에서 비용을 추가하고 업데이트하는 실제 기능을 수행하기 위해 함수 비용 지출을 추가합니다.

``` js
export default function expensesReducer(state = [], action) {
   switch (action.type) {
      case ADD_EXPENSE:
         return [...state, action.payload];
      case DELETE_EXPENSE:
         return state.filter(expense => expense.id !== action.payload.id);
      default:
         return state;
   }
}
```

감속기의 전체 소스 코드는 다음과 같습니다.

``` js
import { ADD_EXPENSE, DELETE_EXPENSE } from '../actions/types';

export default function expensesReducer(state = [], action) {
   switch (action.type) {
      case ADD_EXPENSE:
         return [...state, action.payload];
      case DELETE_EXPENSE:
         return state.filter(expense => expense.id !== action.payload.id);
      default:
         return state;
   }
}
```

여기서 리듀서는 액션 유형을 확인하고 해당 코드를 실행합니다.

다음으로 src 폴더 아래에 components 폴더를 생성합니다.

그런 다음 src/components 폴더 아래에 ExpenseEntryItemList.css 파일을 만들고 html 테이블에 대한 일반 스타일을 추가합니다.

``` css
html {
   font-family: sans-serif;
}
table {
   border-collapse: collapse;
   border: 2px solid rgb(200,200,200);
   letter-spacing: 1px;
   font-size: 0.8rem;
}
td, th {
   border: 1px solid rgb(190,190,190);
   padding: 10px 20px;
}
th {
   background-color: rgb(235,235,235);
}
td, th {
   text-align: left;
}
tr:nth-child(even) td {
   background-color: rgb(250,250,250);
}
tr:nth-child(odd) td {
   background-color: rgb(245,245,245);
}
caption {
   padding: 10px;
}
tr.highlight td { 
   background-color: #a6a8bd;
}
```

그런 다음 src/components 폴더 아래에 ExpenseEntryItemList.js 파일을 만들고 편집을 시작합니다.

다음으로 React 및 React redux 라이브러리를 가져옵니다.

``` js
import React from 'react';
import { connect } from 'react-redux';
```

다음으로 ExpenseEntryItemList.css 파일을 가져옵니다.

``` js
import './ExpenseEntryItemList.css';
```

다음으로 작업 생성자를 가져옵니다.

``` js
import { deleteExpense } from '../actions';
import { addExpense } from '../actions';
```

다음으로 ExpenseEntryItemList라는 클래스를 만들고 **prop**을 사용하여 생성자를 호출합니다.

``` js
class ExpenseEntryItemList extends React.Component {
   constructor(props) {
      super(props);
   }
}
```

다음으로 mapStateToProps 함수를 생성합니다.

``` js
const mapStateToProps = state => {
   return {
      expenses: state
   };
};
```

여기에서 입력 상태를 component의 비용 소품에 복사했습니다.

다음으로 mapDispatchToProps 함수를 생성합니다.

``` js
const mapDispatchToProps = dispatch => {
   return {
      onAddExpense: expense => {
         dispatch(addExpense(expense));
      },
      onDelete: id => {
         dispatch(deleteExpense(id));
      }
   };
};
```

여기에서 우리는 비용 추가(addExpense) 함수를 전달하는 함수와 비용 삭제(deleteExpense) 함수를 전달하는 함수를 만들고 해당 함수를 component의 props에 매핑했습니다.

그런 다음 연결 API를 사용하여 component를 내보냅니다.

``` js
export default connect(
   mapStateToProps,
   mapDispatchToProps
)(ExpenseEntryItemList);
```

이제 component는 아래에 주어진 세 가지 새로운 속성을 얻습니다.

- expenses − 비용 목록
- onAddExpense − addExpense 함수를 전달하는 함수
- onDelete − deleteExpense 함수를 전달하는 함수

다음으로 onAddExpense 속성을 사용하여 생성자의 redux 저장소에 약간의 비용을 추가합니다.

``` js
if (this.props.expenses.length == 0)
{
   const items = [
      { id: 1, name: "Pizza", amount: 80, spendDate: "2020-10-10", category: "Food" },
      { id: 2, name: "Grape Juice", amount: 30, spendDate: "2020-10-12", category: "Food" },
      { id: 3, name: "Cinema", amount: 210, spendDate: "2020-10-16", category: "Entertainment" },
      { id: 4, name: "Java Programming book", amount: 242, spendDate: "2020-10-15", category: "Academic" },
      { id: 5, name: "Mango Juice", amount: 35, spendDate: "2020-10-16", category: "Food" },
      { id: 6, name: "Dress", amount: 2000, spendDate: "2020-10-25", category: "Cloth" },
      { id: 7, name: "Tour", amount: 2555, spendDate: "2020-10-29", category: "Entertainment" },
      { id: 8, name: "Meals", amount: 300, spendDate: "2020-10-30", category: "Food" },
      { id: 9, name: "Mobile", amount: 3500, spendDate: "2020-11-02", category: "Gadgets" },
      { id: 10, name: "Exam Fees", amount: 1245, spendDate: "2020-11-04", category: "Academic" }
   ]
   items.forEach((item) => {
      this.props.onAddExpense(
         { 
            name: item.name, 
            amount: item.amount, 
            spendDate: item.spendDate, 
            category: item.category 
         }
      );
   })
}
```

다음으로 비용 ID를 사용하여 비용 항목을 삭제하는 이벤트 핸들러를 추가합니다.

``` js
handleDelete = (id,e) => {
   e.preventDefault();
   this.props.onDelete(id);
}
```

여기서 이벤트 핸들러는 비용 ID와 함께 deleteExpense를 호출하는 onDelete 디스패처를 호출합니다.

다음으로 모든 비용의 총액을 계산하는 메서드를 추가합니다.

``` js
getTotal() {
   let total = 0;
   for (var i = 0; i < this.props.expenses.length; i++) {
      total += this.props.expenses[i].amount
   }
   return total;
}
```

다음으로 render() 메서드를 추가하고 비용 항목을 표 형식으로 나열합니다.

``` js
render() {
   const lists = this.props.expenses.map(
      (item) =>
      <tr key={item.id}>
         <td>{item.name}</td>
         <td>{item.amount}</td>
         <td>{new Date(item.spendDate).toDateString()}</td>
         <td>{item.category}</td>
         <td><a href="#"
            onClick={(e) => this.handleDelete(item.id, e)}>Remove</a></td>
      </tr>
   );
   return (
      <div>
         <table>
            <thead>
               <tr>
                  <th>Item</th>
                  <th>Amount</th>
                  <th>Date</th>
                  <th>Category</th>
                  <th>Remove</th>
               </tr>
            </thead>
            <tbody>
               {lists}
               <tr>
                  <td colSpan="1" style={{ textAlign: "right" }}>Total Amount</td>
                  <td colSpan="4" style={{ textAlign: "left" }}>
                     {this.getTotal()}
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   );
}
```

여기에서 이벤트 핸들러 handleDelete를 설정하여 상점에서 비용을 제거합니다.

ExpenseEntryItemList component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from 'react';
import { connect } from 'react-redux';
import './ExpenseEntryItemList.css';
import { deleteExpense } from '../actions';
import { addExpense } from '../actions';

class ExpenseEntryItemList extends React.Component {
   constructor(props) {
      super(props);

      if (this.props.expenses.length == 0){
         const items = [
            { id: 1, name: "Pizza", amount: 80, spendDate: "2020-10-10", category: "Food" },
            { id: 2, name: "Grape Juice", amount: 30, spendDate: "2020-10-12", category: "Food" },
            { id: 3, name: "Cinema", amount: 210, spendDate: "2020-10-16", category: "Entertainment" },
            { id: 4, name: "Java Programming book", amount: 242, spendDate: "2020-10-15", category: "Academic" },
            { id: 5, name: "Mango Juice", amount: 35, spendDate: "2020-10-16", category: "Food" },
            { id: 6, name: "Dress", amount: 2000, spendDate: "2020-10-25", category: "Cloth" },
            { id: 7, name: "Tour", amount: 2555, spendDate: "2020-10-29", category: "Entertainment" },
            { id: 8, name: "Meals", amount: 300, spendDate: "2020-10-30", category: "Food" },
            { id: 9, name: "Mobile", amount: 3500, spendDate: "2020-11-02", category: "Gadgets" },
            { id: 10, name: "Exam Fees", amount: 1245, spendDate: "2020-11-04", category: "Academic" }
         ]
         items.forEach((item) => {
            this.props.onAddExpense(
               { 
                  name: item.name, 
                  amount: item.amount, 
                  spendDate: item.spendDate, 
                  category: item.category 
               }
            );
         })
      }
   }
   handleDelete = (id,e) => {
      e.preventDefault();
      this.props.onDelete(id);
   }
   getTotal() {
      let total = 0;
      for (var i = 0; i < this.props.expenses.length; i++) {
         total += this.props.expenses[i].amount
      }
      return total;
   }
   render() {
      const lists = this.props.expenses.map((item) =>
         <tr key={item.id}>
            <td>{item.name}</td>
            <td>{item.amount}</td>
            <td>{new Date(item.spendDate).toDateString()}</td>
            <td>{item.category}</td>
            <td><a href="#"
               onClick={(e) => this.handleDelete(item.id, e)}>Remove</a></td>
         </tr>
      );
      return (
         <div>
            <table>
               <thead>
                  <tr>
                     <th>Item</th>
                     <th>Amount</th>
                     <th>Date</th>
                     <th>Category</th>
                     <th>Remove</th>
                  </tr>
               </thead>
               <tbody>
                  {lists}
                  <tr>
                     <td colSpan="1" style={{ textAlign: "right" }}>Total Amount</td>
                     <td colSpan="4" style={{ textAlign: "left" }}>
                        {this.getTotal()}
                     </td>
                  </tr>
               </tbody>
            </table>
         </div>
      );
   }
}
const mapStateToProps = state => {
   return {
      expenses: state
   };
};
const mapDispatchToProps = dispatch => {
   return {
      onAddExpense: expense => {
         dispatch(addExpense(expense));
      },
      onDelete: id => {
         dispatch(deleteExpense(id));
      }
   };
};
export default connect(
   mapStateToProps,
   mapDispatchToProps
)(ExpenseEntryItemList);
```

다음으로 src/components 폴더 아래에 App.js 파일을 생성하고 ExpenseEntryItemList 컴포넌트를 사용합니다.

``` js
import React, { Component } from 'react';
import ExpenseEntryItemList from './ExpenseEntryItemList';

class App extends Component {
   render() {
      return (
         <div>
            <ExpenseEntryItemList />
         </div>
      );
   }
}
export default App;
```

다음으로 src 폴더 아래에 index.js 파일을 생성합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import rootReducer from './reducers';
import App from './components/App';

const store = createStore(rootReducer);

ReactDOM.render(
   <Provider store={store}>
      <App />
   </Provider>,
   document.getElementById('root')
);
```

여기,

- 리듀서를 연결하여 createStore를 사용하여 스토어를 생성합니다.
- React redux 라이브러리의 Provider component를 사용하고 저장소를 props로 설정하면 모든 중첩 component가 connect api를 사용하여 저장소에 연결할 수 있습니다.

마지막으로 루트 폴더 아래에 공용 폴더를 생성하고 index.html 파일을 생성합니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>React Containment App</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="./index.js"></script>
   </body>
</html>
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

제거 링크를 클릭하면 redux 스토어에서 항목이 제거됩니다.

![Redux](.\images\redux.jpg)



## ReactJS - Animation

출처: https://www.tutorialspoint.com/reactjs/reactjs_animation.htm

애니메이션은 현대 웹 애플리케이션의 흥미로운 기능입니다. 그것은 응용 프로그램에 상쾌한 느낌을줍니다. React 커뮤니티는 React Motion, React Reveal, react-animations 등과 같은 많은 우수한 반응 기반 애니메이션 라이브러리를 제공합니다. React 자체는 이전에 추가 옵션으로 애니메이션 라이브러리인 React Transition Group을 제공합니다. 라이브러리의 이전 버전을 향상시키는 독립 라이브러리입니다. 이번 장에서는 React Transition Group 애니메이션 라이브러리에 대해 알아보겠습니다.



### React Transition Group

React Transition Group 라이브러리는 애니메이션의 간단한 구현입니다. 기본적으로 애니메이션을 수행하지 않습니다. 대신 핵심 애니메이션 관련 정보를 노출합니다. 모든 애니메이션은 기본적으로 한 상태에서 다른 상태로 요소의 전환입니다. 라이브러리는 모든 요소의 가능한 최소 상태를 노출하며 다음과 같습니다.

- Entering
- Entered
- Exiting
- Exited

라이브러리는 각 상태에 대해 CSS 스타일을 설정하고 요소가 한 상태에서 다른 상태로 이동할 때 스타일을 기반으로 요소에 애니메이션을 적용하는 옵션을 제공합니다. 라이브러리는 요소의 현재 상태를 설정하는 props를 제공합니다. in props 값이 true이면 요소가 진입 상태에서 종료 상태로 이동하고 있음을 의미합니다. in props 값이 false이면 요소가 종료에서 종료로 이동하고 있음을 의미합니다.



### Transition

Transition은 React Transition Group에서 요소에 애니메이션 효과를 주기 위해 제공하는 기본 component입니다. 간단한 응용 프로그램을 만들고 Transition 요소를 사용하여 요소를 페이드 인/페이드 아웃해 보겠습니다.

먼저 React 애플리케이션 생성 장의 지침에 따라 Create React App 또는 Rollup 번들러를 사용하여 새로운 반응 애플리케이션인 react-animation-app을 생성합니다.

다음으로 React Transition Group 라이브러리를 설치합니다.

``` sh
cd /go/to/project
npm install react-transition-group --save
```

그런 다음 즐겨 사용하는 편집기에서 응용 프로그램을 엽니다.

그런 다음 응용 프로그램의 루트 디렉터리 아래에 src 폴더를 만듭니다.

다음으로 src 폴더 아래에 components 폴더를 생성합니다.

다음으로 src/components 폴더에 HelloWorld.js 파일을 생성하고 편집을 시작합니다.

다음으로 React 및 애니메이션 라이브러리를 가져옵니다.

``` js
import React from 'react';
import { Transition } from 'react-transition-group'
```

다음으로 HelloWorld component를 만듭니다.

``` js
class HelloWorld extends React.Component {
   constructor(props) {
      super(props);
   }
}
```

다음으로 생성자에서 전환 관련 스타일을 JavaScript 객체로 정의합니다.

``` js
this.duration = 2000;
this.defaultStyle = {
   transition: `opacity ${this.duration}ms ease-in-out`,
   opacity: 0,
}
this.transitionStyles = {
   entering: { opacity: 1 },
   entered: { opacity: 1 },
   exiting: { opacity: 0 },
   exited: { opacity: 0 },
};
```

Here,

- *defaultStyles* 전환 애니메이션을 설정합니다
- *transitionStyles* 다양한 상태에 대한 스타일 설정

다음으로 생성자에서 element 의 초기 상태를 설정합니다.

``` js
this.state = { 
   inProp: true 
}
```

다음으로 3초마다 inProp 값을 변경하여 애니메이션을 시뮬레이션합니다.

``` js
setInterval(() => {
   this.setState((state, props) => {
      let newState = {
         inProp: !state.inProp
      };
      return newState;
   })
}, 3000);
```

다음으로 렌더링 함수를 만듭니다.

``` js
render() { 
   return ( 
   ); 
}
```

다음으로 Transition component를 추가합니다. in prop에 this.state.inProp을 사용하고 timeout prop에 this.duration을 사용합니다. 전환 component에는 사용자 인터페이스를 반환하는 함수가 필요합니다. 기본적으로 Render props입니다.

``` js
render() {
   return (
      <Transition in={this.state.inProp} timeout={this.duration}>
         {state => ({
            ... component's user interface.
         })
      </Transition>
   );
}
```

그런 다음 컨테이너 내부에 component 사용자 인터페이스를 작성하고 컨테이너에 대한 defaultStyle 및 transitionStyles를 설정합니다.

``` js
render() {
   return (
      <Transition in={this.state.inProp} timeout={this.duration}>
         {state => (
            <div style={{
               ...this.defaultStyle,
               ...this.transitionStyles[state]
            }}>
               <h1>Hello World!</h1>
            </div>
         )}
      </Transition>
   );
}
```

마지막으로 component를 노출합니다.

``` js
export default HelloWorld
```

component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from "react";
import { Transition } from 'react-transition-group';

class HelloWorld extends React.Component {
   constructor(props) {
      super(props);
      this.duration = 2000;
      this.defaultStyle = {
         transition: `opacity ${this.duration}ms ease-in-out`,
         opacity: 0,
      }
      this.transitionStyles = {
         entering: { opacity: 1 },
         entered: { opacity: 1 },
         exiting: { opacity: 0 },
         exited: { opacity: 0 },
      };
      this.state = {
         inProp: true
      }
      setInterval(() => {
         this.setState((state, props) => {
            let newState = {
               inProp: !state.inProp
            };
            return newState;
         })
      }, 3000);
   }
   render() {
      return (
         <Transition in={this.state.inProp} timeout={this.duration}>
            {state => (
               <div style={{
                  ...this.defaultStyle,
                  ...this.transitionStyles[state]
               }}>
                  <h1>Hello World!</h1>
               </div>
            )}
         </Transition>
      );
   }
}
export default HelloWorld;
```

다음으로 src 폴더 아래에 index.js 파일을 생성하고 HelloWorld 컴포넌트를 사용합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import HelloWorld from './components/HelloWorld';

ReactDOM.render(
   <React.StrictMode   
      <HelloWorld /   
   </React.StrictMode   ,
   document.getElementById('root')
);
```

마지막으로 루트 폴더 아래에 공용 폴더를 생성하고 index.html 파일을 생성합니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>React Containment App</title>
   </head>
   <body>
      <div id="root"></div>
      <script type="text/JavaScript" src="./index.js"></script>
   </body>
</html>
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

```sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

제거 링크를 클릭하면 redux 스토어에서 항목이 제거됩니다.

![Animation](.\images\animation.jpg)

### CSSTransition

CSSTransition은 Transition 컴포넌트 위에 구축되었으며 classNames prop을 도입하여 Transition 컴포넌트를 개선합니다. classNames prop은 요소의 다양한 상태에 사용되는 CSS 클래스 이름을 참조합니다.

예를 들어 classNames=hello prop은 CSS 클래스 아래를 참조합니다.

``` css
.hello-enter {
   opacity: 0;
}
.hello-enter-active {
   opacity: 1;
   transition: opacity 200ms;
}
.hello-exit {
   opacity: 1;
}
.hello-exit-active {
   opacity: 0;
   transition: opacity 200ms;
}
```

CSSTransition component를 사용하여 새 component HelloWorldCSSTransition을 만들어 보겠습니다.

먼저 좋아하는 편집기에서 react-animation-app 애플리케이션을 엽니다.

그런 다음 src/components 폴더 아래에 HelloWorldCSSTransition.css라는 새 파일을 만들고 전환 클래스를 입력합니다.

``` css
.hello-enter {
   opacity: 1;
   transition: opacity 2000ms ease-in-out;
}
.hello-enter-active {
   opacity: 1;
   transition: opacity 2000ms ease-in-out;
}
.hello-exit {
   opacity: 0;
   transition: opacity 2000ms ease-in-out;
}
.hello-exit-active {
   opacity: 0;
   transition: opacity 2000ms ease-in-out;
}
```

그런 다음 src/components 폴더 아래에 HelloWorldCSSTransition.js라는 새 파일을 만들고 편집을 시작합니다.

다음으로 React 및 애니메이션 라이브러리를 가져옵니다.

``` js
import React from 'react'; 
import { CSSTransition } from 'react-transition-group'
```

다음으로 HelloWorldCSSTransition.css를 가져옵니다.

``` js
import './HelloWorldCSSTransition.css'
```

다음으로 HelloWorld component를 만듭니다.

``` js
class HelloWorldCSSTransition extends React.Component {
   constructor(props) {
      super(props);
   }
}
```

다음으로 생성자에서 전환 기간을 정의합니다.

``` js
this.duration = 2000;
```

다음으로 생성자에서 요소의 초기 상태를 설정합니다.

``` js
this.state = { 
   inProp: true 
}
```

다음으로 3초마다 inProp 값을 변경하여 애니메이션을 시뮬레이션합니다.

``` js
setInterval(() => {
   this.setState((state, props) => {
      let newState = {
         inProp: !state.inProp
      };
      return newState;
   })
}, 3000);
```

다음으로 렌더링 함수를 만듭니다.

``` js
render() { 
   return (
   ); 
}
```

다음으로 CSSTransition 컴포넌트를 추가합니다. in prop에는 this.state.inProp, timeout prop에는 this.duration, classNames prop에는 hello를 사용하세요. CSSTransition component는 사용자 인터페이스를 자식 소품으로 예상합니다.

``` js
render() {
   return (
      <CSSTransition in={this.state.inProp} timeout={this.duration} 
         classNames="hello">
         // ... user interface code ...   
      </CSSTransition>
   );
}
```

다음으로 component 사용자 인터페이스를 작성합니다.

``` js
render() {
   return (
       <CSSTransition in={this.state.inProp} timeout={this.duration} 
      classNames="hello">
      <div>
          <h1>Hello World!</h1>
      </div>
       </CSSTransition>
   );
}
```

마지막으로 component를 노출합니다.

``` js
export default HelloWorldCSSTransition;
```

component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from 'react';
import { CSSTransition } from 'react-transition-group'
import './HelloWorldCSSTransition.css' 

class HelloWorldCSSTransition extends React.Component {
   constructor(props) {
      super(props);
      this.duration = 2000;
      this.state = {
         inProp: true
      }
      setInterval(() => {
         this.setState((state, props) => {
            let newState = {
               inProp: !state.inProp
            };
            return newState;
         })
      }, 3000);
   }
   render() {
      return (
         <CSSTransition in={this.state.inProp} timeout={this.duration} 
            classNames="hello">
            <div>
               <h1>Hello World!</h1>
            </div>
         </CSSTransition>
      );
   }
}
export default HelloWorldCSSTransition;
```

다음으로 src 폴더 아래에 index.js 파일을 생성하고 HelloWorld 컴포넌트를 사용합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import HelloWorldCSSTransition from './components/HelloWorldCSSTransition';

ReactDOM.render(
   <React.StrictMode>
      <HelloWorldCSSTransition />
   </React.StrictMode>,
   document.getElementById('root')
);
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```

그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

메시지는 3초마다 페이드 인 및 페이드 아웃됩니다.

![Animation](.\images\animation.jpg)



### TransitionGroup

TransitionGroup은 목록에서 여러 전환 component를 관리하는 컨테이너 component입니다. 예를 들어 목록의 각 항목은 CSSTransition을 사용하지만 TransitionGroup은 적절한 애니메이션을 위해 모든 항목을 그룹화하는 데 사용할 수 있습니다.

``` js
<TransitionGroup>
   {items.map(({ id, text }) => (
      <CSSTransition key={id} timeout={500} classNames="item" >
         <Button
            onClick={() =>
               setItems(items =>
                  items.filter(item => item.id !== id)
               )
            }
            >
            &times;
         </Button>
         {text}
      </CSSTransition>
   ))}
</TransitionGroup>
```



## ReactJS - Testing

출처: https://www.tutorialspoint.com/reactjs/reactjs_testing.htm

테스트는 모든 애플리케이션에서 생성된 기능이 비즈니스 로직 및 코딩 사양에 따라 작동하는지 확인하는 프로세스 중 하나입니다. React는 React component를 테스트하기 위해 React 테스트 라이브러리를 권장하고 테스트를 실행하기 위해 테스트 러너를 사용 합니다. react-testing-library를 사용하면 component를 개별적으로 확인할 수 있습니다.

아래 명령을 사용하여 응용 프로그램에 설치할 수 있습니다 -

``` sh
npm install --save @testing-library/react @testing-library/jest-dom
```



### Create React app

Create React 앱은 기본적으로 React 테스트 라이브러리와 jest 테스트 러너를 구성합니다. 따라서 Create React App을 사용하여 생성된 React 애플리케이션을 테스트하는 것은 명령으로 가능합니다.

``` sh
cd /go/to/react/application
npm test
```

npm test 명령은 npm build 명령과 유사합니다. 개발자가 코드를 변경할 때 둘 다 다시 컴파일됩니다. 명령 프롬프트에서 명령을 실행하면 다음과 같은 질문이 발생합니다.

``` sh
No tests found related to files changed since last commit.
Press `a` to run all tests, or run Jest with `--watchAll`.

Watch Usage
› Press a to run all tests.
› Press f to run only failed tests.
› Press q to quit watch mode.
› Press p to filter by a filename regex pattern.
› Press t to filter by a test name regex pattern.
› Press Enter to trigger a test run.
```

a를 누르면 모든 테스트 스크립트를 실행하려고 시도하고 마지막으로 아래와 같이 결과를 요약합니다.

``` sh
Test Suites: 1 passed, 1 total
Tests: 1 passed, 1 total
Snapshots: 0 total
Time: 4.312 s, estimated 12 s
Ran all test suites.

Watch Usage: Press w to show more.
```



### 커스텀 애플리케이션에서 테스트

Rollup 번들러를 사용하여 사용자 정의 React 애플리케이션을 작성하고 이 장에서 React 테스트 라이브러리와 jest 테스트 러너를 사용하여 테스트해 보겠습니다.

먼저 React 응용 프로그램 만들기 장의 지침에 따라 Rollup 번들러를 사용하여 새로운 반응 응용 프로그램 react-test-app을 만듭니다.

다음으로 테스트 라이브러리를 설치합니다.

``` sh
cd /go/to/react-test-app
npm install --save @testing-library/react @testing-library/jest-dom
```

그런 다음 즐겨 사용하는 편집기에서 응용 프로그램을 엽니다.

다음으로 src/components 폴더 아래에 HelloWorld.test.js 파일을 생성하여 HelloWorld 컴포넌트에 대한 테스트를 작성하고 편집을 시작합니다.

다음으로 반응 라이브러리를 가져옵니다.

``` js
import React from 'react';
```

다음으로 테스트 라이브러리를 가져옵니다.

``` js
import { render, screen } from '@testing-library/react'; import '@testing-library/jest-dom';
```

다음으로 HelloWorld component를 가져옵니다.

``` js
import HelloWorld from './HelloWorld';
```

다음으로 문서에 Hello World 텍스트가 있는지 확인하는 테스트를 작성합니다.

``` js
test('test scenario 1', () => {
   render(<HelloWorld />);
   const element = screen.getByText(/Hello World/i);
   expect(element).toBeInTheDocument();
});
```

테스트 코드의 전체 소스 코드는 다음과 같습니다.

``` js
import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import HelloWorld from './HelloWorld';

test('test scenario 1', () => {
   render(<HelloWorld />);
   const element = screen.getByText(/Hello World/i);
   expect(element).toBeInTheDocument();
});
```

다음으로, 시스템에 아직 설치되지 않은 경우 jest 테스트 러너를 설치합니다.

``` sh
npm install jest -g
```

그런 다음 응용 프로그램의 루트 폴더에서 jest 명령을 실행합니다.

``` sh
jest
```

``` sh
PASS src/components/HelloWorld.test.js
√ test scenario 1 (29 ms)

Test Suites: 1 passed, 1 total
Tests: 1 passed, 1 total
Snapshots: 0 total
Time: 5.148 s
Ran all test suites.
```



## ReactJS - CLI Commands

출처: https://www.tutorialspoint.com/reactjs/reactjs_cli_commands.htm

이 장에서는 Create React App 명령줄 응용 프로그램에서 사용할 수 있는 기본 명령에 대해 알아보겠습니다.



### Creating a new application

Create React App은 React 애플리케이션을 생성하는 다양한 방법을 제공합니다.



**npx 스크립트를 사용하는방법 .**

``` sh
npx create-react-app <react-app-name>
npx create-react-app hello-react-app
```



**npm 패키지 관리자를 사용하는방법 .**

``` sh
npm init react-app <react-app-name>
npm init react-app hello-react-app
```



**yarn 패키지 관리자를 사용하는방법 .**

``` sh
yarn init react-app <react-app-name>
yarn init react-app hello-react-app
```



### 템플릿 선택

Create React App은 기본 템플릿을 사용하여 React 애플리케이션을 생성합니다. 템플릿은 특정 내장 기능이 있는 초기 코드를 참조합니다. npm 패키지 서버에는 많은 고급 기능이 포함된 수백 개의 템플릿이 있습니다. Create React App을 사용하면 사용자가 –template 명령줄 스위치를 통해 템플릿을 선택할 수 있습니다.

``` sh
create-react-app my-app --template typescript
```

위 명령은 npm 서버에서 cra-template-typescript 패키지를 사용하여 반응 앱을 생성합니다.



### 종속성 설치

React는 npm 및 yarn에서 권장하는 프로젝트 구조를 사용하므로 React 의존성 패키지는 일반 npm 또는 yarn package 명령을 사용하여 설치할 수 있습니다.



npm 패키지 관리자를 사용하는방법 .

``` sh
npm install --save react-router-dom
```



yarn 패키지 관리자를 사용하는방법 .

``` sh
yarn add react-router-dom
```



### 애플리케이션 실행

React 애플리케이션은 프로젝트에서 사용하는 패키지 관리자에 따라 npm 또는 yarn 명령을 사용하여 시작할 수 있습니다.

npm 패키지 관리자를 사용하는방법.

``` sh
npm start
```



yarn 패키지 관리자를 사용하는방법.

``` sh
yarn start
```



보안 모드(HTTPS)에서 애플리케이션을 실행하려면 애플리케이션을 시작하기 전에 환경 변수 HTTPS를 설정하고 true로 설정하십시오. 예를 들어, Windows 명령 프롬프트(cmd.exe)에서 아래 명령은 HTTPS를 설정하고 애플리케이션을 시작하는 것은 HTTPS 모드입니다.

``` sh
set HTTPS=true && npm start
```



## ReactJS - Building & Deployment

출처: https://www.tutorialspoint.com/reactjs/reactjs_building_deployment.htm

이번 장에서는 React 애플리케이션의 프로덕션 빌드 및 배포 방법을 알아보겠습니다.



### Building

React 애플리케이션 개발이 완료되면 애플리케이션을 번들링하여 프로덕션 서버에 배포해야 합니다. 이 장에서 응용 프로그램을 빌드하고 배포하는 데 사용할 수 있는 명령을 알아보겠습니다.

단일 명령으로 애플리케이션의 프로덕션 빌드를 생성하기에 충분합니다.

``` sh
npm run build
> expense-manager@0.1.0 build path\to\expense-manager
> react-scripts build

Creating an optimized production build...
Compiled with warnings.

File sizes after gzip:

41.69 KB build\static\js\2.a164da11.chunk.js
2.24 KB build\static\js\main.de70a883.chunk.js
1.4 KB build\static\js\3.d8a9fc85.chunk.js
1.17 KB build\static\js\runtime-main.560bee6e.js
493 B build\static\css\main.e75e7bbe.chunk.css

The project was built assuming it is hosted at /.
You can control this with the homepage field in your package.json.

The build folder is ready to be deployed.
You may serve it with a static server:

npm install -g serve
serve -s build

Find out more about deployment here:

https://cra.link/deployment
```

애플리케이션이 빌드되면 build/static 폴더에서 애플리케이션을 사용할 수 있습니다.

기본적으로 프로파일링 옵션은 비활성화되어 있으며 –profile 명령줄 옵션을 통해 활성화할 수 있습니다. –profile은 코드에 프로파일링 정보를 포함합니다. 프로파일링 정보는 React DevTools와 함께 사용하여 애플리케이션을 분석할 수 있습니다.

``` bash
npm run build -- --profile
```



### Deployment

애플리케이션이 빌드되면 모든 웹 서버에 배포할 수 있습니다. 이 장에서 React 애플리케이션을 배포하는 방법을 알아보겠습니다.



#### 로컬 배포

서버 패키지를 사용하여 로컬 배포를 수행할 수 있습니다. 먼저 아래 명령을 사용하여 serve 패키지를 설치해 보겠습니다.

``` sh
npm install -g server
```

serve를 사용하여 응용 프로그램을 시작하려면 아래 명령을 사용하십시오.

``` sh
cd /go/to/app/root/folder
serve -s build
```

기본적으로 포트 5000을 사용하여 애플리케이션을 제공합니다. 애플리케이션은 @ http://localhost:5000에서 볼 수 있습니다.



#### 프로덕션 배포

프로덕션 배포는 빌드/정적 폴더 아래에 있는 파일을 프로덕션 애플리케이션의 루트 디렉터리에 복사하여 쉽게 수행할 수 있습니다. Apache, IIS, Nginx 등을 포함한 모든 웹 서버에서 작동합니다.



#### 상대 경로

기본적으로 프로덕션 빌드는 애플리케이션이 웹 애플리케이션의 루트 폴더에서 호스팅된다고 가정하여 생성됩니다. 애플리케이션을 하위 폴더에서 호스팅해야 하는 경우 package.json에서 아래 구성을 사용한 다음 애플리케이션을 빌드합니다.

``` sh
{ ... "homepage": "http://domainname.com/path/to/subfolder", ... }
```



## ReactJS - Example

이 자습서에서 배운 개념을 적용하여 샘플 비용 관리자 응용 프로그램을 만들어 보겠습니다. 일부 개념은 다음과 같습니다.

- React 기본 사항(component, jsx, props and state)
- react-router를 사용하는 라우터
- Http 클라이언트 프로그래밍(웹 API)
- Formik을 사용한 폼 프로그래밍
- Redux를 사용한 고급 상태 관리
- 비동기/프로그래밍 대기



### 특징

샘플 비용 관리자 응용 프로그램의 일부 기능은 다음과 같습니다.

- 서버의 모든 비용 나열
- 비용 항목 추가
- 비용 항목 삭제



여기,

- [비용 관리자 API 문서](#비용 관리자 API)
- [필요한 모듈 설치  문서](#필요한 모듈 설치)
- [상태 관리자  문서](상태 관리자)
- [비용 열람 문서](#비용 열람)
- [비용 추가 문서](#비용 추가)



### 비용 관리자 API

출처: https://www.tutorialspoint.com/reactjs/reactjs_expense_manager_api.htm

#### ReactJS - Expense Manager API

먼저 Http 클라이언트 프로그래밍 - Expense Rest API 서버의 지시에 따라 새로운 비용 Rest API 애플리케이션을 만들고 서버를 시작합니다. 비용 서버는 http://localhost:8000에서 실행됩니다.



#### 스켈레톤 애플리케이션 생성

터미널을 열고 작업 공간으로 이동합니다.

``` js
> cd /go/to/your/workspace
```



다음으로 Create React App 도구를 사용하여 새로운 React 애플리케이션을 생성합니다.

``` sh
> create-react-app react-expense-app
```

시작 템플릿 코드로 새 폴더 react-expense-app을 만듭니다.



다음으로 비용 관리 폴더로 이동하여 필요한 라이브러리를 설치합니다.

``` sh
cd react-expense-app
npm install
```

npm install은 node_modules 폴더 아래에 필요한 라이브러리를 설치합니다.

src 및 공용 폴더 아래의 모든 파일을 삭제합니다.

다음으로 React component를 포함할 src 아래에 components라는 폴더를 만듭니다. 응용 프로그램의 최종 구조는 다음과 같습니다.

``` sh
|-- package-lock.json
|-- package.json
`-- public
|-- index.html
`-- src
|-- index.js
`-- components
| |-- mycom.js
| |-- mycom.css
```

전체 응용 프로그램을 렌더링할 루트 component인 App을 만들어 보겠습니다.

Components 폴더 아래에 **App.js**라는 파일을 생성하고 Hello World 메시지를 내보내는 간단한 컴포넌트를 작성합니다.

``` sh
import React from "react";

class App extends React.Component {
   render() {
      return (
         <div>
            <h1>Hello World!</h1>
         </div>
      );
   }
}
export default App;
```



다음으로 src 폴더 아래에 기본 파일인 **index.js**를 만들고 새로 만든 component를 호출합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';

import App from './components/App'

ReactDOM.render(
   <React.StrictMode>
      <App />
   </React.StrictMode>,
   document.getElementById('root')
);
```



다음으로, 애플리케이션의 진입점이 될 **index.html**(public 폴더 아래)이라는 html 파일을 만듭니다.

``` html
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>Expense App</title>
   </head>
   <body>
      <div id="root"></div>
   </body>
</html>
```



다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```



그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![Animation](.\images\animation.jpg)



### 필요한 모듈 설치

출처: https://www.tutorialspoint.com/reactjs/reactjs_install_necessary_modules.htm

응용 프로그램은 아래에 제공된 타사 React 라이브러리를 사용합니다.

- Redux
- React Redux
- React Router
- Formik
- Redux Thunk (비동기 가져오기 API의 경우)



아래 명령을 사용하여 npm 패키지 관리자를 사용하여 모든 라이브러리를 설치하십시오 -

``` sh
npm install --save redux react-redux react-router-dom formik redux-thunk
```



#### 라우터 구성

다음으로 src/components 폴더 아래에 Home.js라는 새 파일을 만들고 기본적인 Home 컴포넌트를 작성합니다.

``` js
import React from "react";

class Home extends React.Component {
   render() {
      return (
         <div>
            <h1>Home</h1>
         </div>
      );
   }
}
export default Home;
```



그런 다음 src/components 폴더 아래에 ExpenseEntryItemForm.js라는 새 파일을 만들고 기본 ExpenseEntryItemForm component를 작성합니다.

``` js
import React from "react";

class ExpenseEntryItemForm extends React.Component {
   render() {
      return (
         <div>
            <h1>Expense list</h1>
         </div>
      );
   }
}
export default ExpenseEntryItemForm;
```



그런 다음 src/components 폴더 아래에 ExpenseEntryItemList.js라는 새 파일을 만들고 기본 ExpenseEntryItemList component를 작성합니다.

``` js
import React from "react";

class ExpenseEntryItemList extends React.Component {
   render() {
      return (
         <div>
            <h1>Expense form</h1>
         </div>
      );
   }
}
export default ExpenseEntryItemList;
```



src/components 폴더 아래에 새 파일 App.css를 만들고 응용 프로그램에 대한 일반 스타일을 추가합니다.

``` css
html {
   font-family: sans-serif;
}
a{
   text-decoration: none;
}
p, li, a{
   font-size: 14px;
}
nav ul {
   width: 100%;
   list-style-type: none;
   margin: 0;
   padding: 0;
   overflow: hidden;
   background-color: rgb(235,235,235);
}
nav li {
   float: left;
}
nav li a {
   display: block;
   color: black;
   text-align: center;
   padding: 14px 16px;
   text-decoration: none;
   font-size: 16px;
}
nav li a:hover {
   background-color: rgb(187, 202, 211);
}
input[type=text], input[type=number], input[type=date], select {
   width: 100%;
   padding: 12px 20px;
   margin: 8px 0;
   display: inline-block;
   border: 1px solid #ccc;
   border-radius: 4px;
   box-sizing: border-box;
}
input[type=submit] {
   width: 100%;
   background-color: #4CAF50;
   color: white;
   padding: 14px 20px;
   margin: 8px 0;
   border: none;
   border-radius: 4px;
   cursor: pointer;
}
input[type=submit]:hover {
   background-color: #45a049;
}
input:focus {
   border: 1px solid #d9d5e0;
}
#expenseForm div {
   border-radius: 5px;
   background-color: #f2f2f2;
   padding: 20px;
}
#expenseForm span {
   color: red;
}
html {
   font-family: sans-serif;
}
table {
   border-collapse: collapse;
   border: 2px solid rgb(200,200,200);
   letter-spacing: 1px;
   font-size: 0.8rem;
}
td, th {
   border: 1px solid rgb(190,190,190);
   padding: 10px 20px;
}
th {
   background-color: rgb(235,235,235);
}
td, th {
   text-align: left;
}
tr:nth-child(even) td {
   background-color: rgb(250,250,250);
}
tr:nth-child(odd) td {
   background-color: rgb(245,245,245);
}
caption {
   padding: 10px;
}
tr.highlight td { 
   background-color: #a6a8bd;
}
```



그런 다음 App.js를 열고 라우터 종속성을 가져옵니다.

``` js
import {
   BrowserRouter as Router,
   Link,
   Switch,
   Route
} from 'react-router-dom';
```



다음으로 App.css를 가져옵니다.

``` js
import './App.css';
```



다음으로 새로 생성된 component를 가져옵니다.

``` js
import Home from './Home';
import ExpenseEntryItemList from './ExpenseEntryItemList';
import ExpenseEntryItemForm from './ExpenseEntryItemForm';
```



그런 다음 앱 component에서 라우터를 구성합니다.

``` js
class App extends React.Component {
   render() {
      return (
         <Router>
            <div>
               <nav>
                  <ul>
                     <li><Link to="/">Home</Link></li>
                     <li><Link to="/list">List Expenses</Link>lt;/li>
                     <li><Link to="/add">Add Expense</Link></li>
                  </ul>
               </nav>
               <Switch>
                  <Route path="/list">
                     <div style={   { padding: "10px 0px" }   }>
                        <ExpenseEntryItemList />
                     </div>
                  </Route>
                  <Route path="/add">
                     <div style={   { padding: "10px 0px" }   }>
                        <ExpenseEntryItemForm />
                     </div>
                  </Route>
                  <Route path="/">
                        <div>
                     <Home />
                     </div>
                  </Route>
               </Switch>
            </div>
         </Router>
      );
   }
}
```



앱 component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from "react";
import {
   BrowserRouter as Router,
   Link,
   Switch,
   Route
} from 'react-router-dom';

import './App.css';
import Home from './Home';
import ExpenseEntryItemList from './ExpenseEntryItemList';
import ExpenseEntryItemForm from './ExpenseEntryItemForm';

class App extends React.Component {
   render() {
      return (
         <Router>
            <div>
               <nav>
                  <ul>
                     <li><Link to="/">Home</Link></li>
                     <li><Link to="/list">List Expenses</Link></li>
                     <li><Link to="/add">Add Expense</Link></li>
                  </ul>
               </nav>

               <Switch>
                  <Route path="/list">
                     <div style={{ padding: "10px 0px" }}>
                        <ExpenseEntryItemList />
                     </div>
                  </Route>
                  <Route path="/add">
                     <div style={{ padding: "10px 0px" }}>
                        <ExpenseEntryItemForm />
                     </div>
                  </Route>
                  <Route path="/">
                     <div>
                        <Home />
                     </div>
                  </Route>
               </Switch>
            </div>
         </Router>
      );
   }
}
export default App;
```

다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```



그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![Modules](.\images\modules.jpg)



### 상태 관리자

출처: https://www.tutorialspoint.com/reactjs/reactjs_state_management.htm

우리는 redux 스토어를 관리하기 위해 아래의 작업을 수행할 것입니다.

- async fetch api를 통해 서버에서 비용을 가져와 Redux 스토어에 설정합니다.
- 비동기 가져오기 프로그래밍을 통해 서버에 새로운 비용을 추가하고 Redux 스토어에 새 비용을 추가하도록 설정합니다.
- async fetch api를 통해 서버에서 기존 비용을 삭제하고 Redux 스토어를 업데이트합니다.

Redux 상태를 관리하기 위한 액션 유형, 액션 생성자, 액션 및 리듀서를 생성해 보겠습니다.

src 폴더 아래에 폴더 *actions* 을 만듭니다.

다음으로 작업 유형을 생성하기 위해 types.js 파일을 생성합니다.

``` js
export const LIST_EXPENSE_STARTED = 'LIST_EXPENSE_STARTED';
export const LIST_EXPENSE_SUCCESS = 'LIST_EXPENSE_SUCCESS';
export const LIST_EXPENSE_FAILURE = 'LIST_EXPENSE_FAILURE';

export const ADD_EXPENSE_STARTED = 'ADD_EXPENSE_STARTED';
export const ADD_EXPENSE_SUCCESS = 'ADD_EXPENSE_SUCCESS';
export const ADD_EXPENSE_FAILURE = 'ADD_EXPENSE_FAILURE';

export const DELETE_EXPENSE_STARTED = 'DELETE_EXPENSE_STARTED';
export const DELETE_EXPENSE_SUCCESS = 'DELETE_EXPENSE_SUCCESS';
export const DELETE_EXPENSE_FAILURE = 'DELETE_EXPENSE_FAILURE';
```



다음으로 action 폴더 아래에 index.js 파일을 생성하여 action Creator를 생성합니다.

``` js
import {
   LIST_EXPENSE_STARTED, LIST_EXPENSE_SUCCESS, LIST_EXPENSE_FAILURE,
   ADD_EXPENSE_STARTED, ADD_EXPENSE_SUCCESS, ADD_EXPENSE_FAILURE,
   DELETE_EXPENSE_STARTED, DELETE_EXPENSE_SUCCESS, DELETE_EXPENSE_FAILURE,
} from "./types";
export const getExpenseListStarted = () => {
   return {
      type: LIST_EXPENSE_STARTED
   }
}
export const getExpenseListSuccess = data => {
   return {
      type: LIST_EXPENSE_SUCCESS,
      payload: {
         data
      }
   }
}
export const getExpenseListFailure = error => {
   return {
      type: LIST_EXPENSE_FAILURE,
      payload: {
         error
      }
   }
}
export const addExpenseStarted = () => {
   return {
      type: ADD_EXPENSE_STARTED
   }
}
export const addExpenseSuccess = data => {
   return {
      type: ADD_EXPENSE_SUCCESS,
      payload: {
         data
      }
   }
}
export const addExpenseFailure = error => {
   return {
      type: ADD_EXPENSE_FAILURE,
      payload: {
         error
      }
   }
}
export const deleteExpenseStarted = () => {
   return {
      type: DELETE_EXPENSE_STARTED
   }
}
export const deleteExpenseSuccess = data => {
   return {
      type: DELETE_EXPENSE_SUCCESS,
      payload: {
         data
      }
   }
}
export const deleteExpenseFailure = error => {
   return {
      type: DELETE_EXPENSE_FAILURE,
      payload: {
         error
      }
   }
}
```



여기에서 fetch api의 모든 가능한 결과(성공, 실패 및 오류)에 대해 하나의 작업 생성기를 만들었습니다. 3개의 웹 API 호출을 사용할 것이고 각 호출에는 3개의 가능한 결과가 있으므로 9명의 작업 생성자를 사용합니다.

다음으로 작업 폴더 아래에 expenseActions.js 파일을 만들고 비용을 가져오고 추가 및 삭제하고 상태 변경 사항을 전달하는 세 가지 기능을 만듭니다.

``` js
import {
   getExpenseListStarted, getExpenseListSuccess, getExpenseListFailure,
   addExpenseStarted, addExpenseSuccess, addExpenseFailure,
   deleteExpenseStarted, deleteExpenseSuccess, deleteExpenseFailure
} from "./index";
export const getExpenseList = () => async dispatch => {
   dispatch(getExpenseListStarted());
   try {
      const res = await fetch('http://localhost:8000/api/expenses');
      const data = await res.json();
      var items = [];
      data.forEach((item) => {
         let newItem = {
            id: item._id,
            name: item.name,
            amount: item.amount,
            spendDate: item.spend_date,
            category: item.category
         }
         items.push(newItem)
      });
      dispatch(getExpenseListSuccess(items));
   } catch (err) {
      dispatch(getExpenseListFailure(err.message));
   }
}
export const addExpense = (data) => async dispatch => {
   dispatch(addExpenseStarted());

   let newItem = {
      name: data.name,
      amount: data.amount,
      spend_date: data.spendDate,
      category: data.category
   }
   console.log(newItem);
   try {
      const res = await fetch('http://localhost:8000/api/expense', {
         method: 'POST',
         body: JSON.stringify(newItem),
         headers: {
            "Content-type": "application/json; charset=UTF-8"
         } 
      });
      const data = await res.json();
      newItem.id = data._id;
      dispatch(addExpenseSuccess(newItem));
   } catch (err) {
      console.log(err);
      dispatch(addExpenseFailure(err.message));
   }
}
export const deleteExpense = (id) => async dispatch => {
   dispatch(deleteExpenseStarted());
   try {
      const res = await fetch('http://localhost:8000/api/expense/' + id, {
         method: 'DELETE'
      });
      const data = await res.json();
      dispatch(deleteExpenseSuccess(id));
   } catch (err) {
      dispatch(deleteExpenseFailure(err.message));
   }
}
```

여기,

- 웹 API 호출을 수행하기 위해 비동기 가져오기 API를 사용했습니다.

- 성공, 실패 및 오류 이벤트 동안 적절한 조치를 전달하기 위해 디스패치 기능을 사용했습니다.



src 폴더 아래에 reducers 폴더를 생성하고, reducers 폴더 아래에 index.js 파일을 생성하여 Redux 리듀서를 생성합니다.

``` js
import {
   LIST_EXPENSE_STARTED, LIST_EXPENSE_SUCCESS, LIST_EXPENSE_FAILURE,
   ADD_EXPENSE_STARTED, ADD_EXPENSE_SUCCESS, ADD_EXPENSE_FAILURE,
   DELETE_EXPENSE_STARTED, DELETE_EXPENSE_SUCCESS, DELETE_EXPENSE_FAILURE
} from "../actions/types";

// define initial state of user
const initialState = {
   data: null,
   loading: false,
   error: null
}
export default function expenseReducer(state = initialState, action) {
   switch (action.type) {
      case LIST_EXPENSE_STARTED:
         return {
            ...state,
            loading: true
         }
      case LIST_EXPENSE_SUCCESS:
         const { data } = action.payload;
         return {
            ...state,
            data,
            loading: false
         }
      case LIST_EXPENSE_FAILURE:
         const { error } = action.payload;
         return {
            ...state,
            error
         }
      case ADD_EXPENSE_STARTED:
         return {
            ...state,
            loading: true
         }
      case ADD_EXPENSE_SUCCESS:
         return {
            ...state,
            loading: false
         }
      case ADD_EXPENSE_FAILURE:
         const { expenseError } = action.payload;
         return {
            ...state,
            expenseError
         }
      case DELETE_EXPENSE_STARTED:
         return {
            ...state,
            loading: true
         }
      case DELETE_EXPENSE_SUCCESS:
         return {
            ...state,
            data: state.data.filter(expense => expense.id !== action.payload.data),
            loading: false
         }
      case DELETE_EXPENSE_FAILURE:
         const { deleteError } = action.payload;
         return {
            ...state,
            deleteError
         }
      default:
         return state
   }
}
```

여기에서 각 작업 유형에 대한 redux 저장소 상태를 업데이트했습니다.

그런 다음 src 폴더 아래 index.js 파일을 열고 모든 component가 redux 스토어에 연결하여 작동할 수 있도록 Provider component를 포함합니다.

``` js
import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import rootReducer from './reducers';
import App from './components/App';

const store = createStore(rootReducer, applyMiddleware(thunk));

ReactDOM.render(
   <Provider store={store}>
      <App />
   </Provider>,
   document.getElementById('root')
);
```

여기,

- 가져온 createStore 및 applyMiddleware
- redux-thunk 라이브러리에서 가져온 썽크(비동기 가져오기 API용)
- redux 라이브러리에서 가져온 공급자
- 리듀서 및 썽크 미들웨어를 구성하여 createStore를 사용하여 newstore 생성
- redux 저장소를 사용하여 Provider component를 최상위 component로 연결했습니다.



### 비용 열람

출처: https://www.tutorialspoint.com/reactjs/reactjs_list_expenses.htm

ExpenseEntryItemList.js를 열고 redux 라이브러리에서 연결을 가져옵니다.

``` js
import { connect } from 'react-redux';
```



다음으로 addExpenseList 및 deleteExpense 작업을 가져옵니다.

``` js
import { getExpenseList, deleteExpense } from '../actions/expenseActions';
```



다음으로 props가 있는 생성자를 추가합니다.

``` js
constructor(props) {
super(props);
}
```



다음으로 componentDidMount() 라이프 사이클에서 getExpenseList를 호출합니다.

``` js
componentDidMount() {
this.props.getExpenseList();
}
```



다음으로 비용 제거 옵션을 처리하는 메서드를 작성합니다.

``` js
handleDelete = (id,e) => {
e.preventDefault();
this.props.deleteExpense(id);
}
```



이제 총 비용을 계산하는 함수 getTotal을 작성해 보겠습니다.

``` js
getTotal() {
   let total = 0;
   if (this.props.expenses != null) {
      for (var i = 0; i < this.props.expenses.length; i++) {
         total += this.props.expenses[i].amount
      }
   }
   return total;
}
```



다음으로, 렌더링 방법을 업데이트하고 비용 항목을 나열합니다.

``` js
render() {
   let lists = [];

   if (this.props.expenses != null) {
      lists = this.props.expenses.map((item) =>
         <tr key={item.id}>
            <td>{item.name}</td>
            <td>{item.amount}</td>
            <td>{new Date(item.spendDate).toDateString()}</td>
            <td>{item.category}</td>
            <td><a href="#"
               onClick={(e) => this.handleDelete(item.id, e)}>Remove</a></td>
         </tr>
      );
   }
   return (
      <div>
         <table>
            <thead>
               <tr>
                  <th>Item</th>
                  <th>Amount</th>
                  <th>Date</th>
                  <th>Category</th>
                  <th>Remove</th>
               </tr>
            </thead>
            <tbody>
               {lists}
               <tr>
                  <td colSpan="1" style={{ textAlign: "right" }}>Total Amount</td>
                  <td colSpan="4" style={{ textAlign: "left" }}>
                     {this.getTotal()}
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   );
}
```



다음으로 mapStateToProps 및 mapDispatchToProps 메소드를 작성하십시오.

``` js
const mapStateToProps = state => {
   return {      
      expenses: state.data
   };
};
const mapDispatchToProps = {
   getExpenseList,
   deleteExpense
};
```

여기서는 redux store의 비용 항목을 비용 속성에 매핑하고 distpatcher, getExpenseList 및 deleteExpense를 component 속성에 연결합니다.



마지막으로 connect api를 사용하여 컴포넌트를 Redux 스토어에 연결합니다.

``` js
export default connect(
   mapStateToProps,
   mapDispatchToProps
)(ExpenseEntryItemList);
```



응용 프로그램의 전체 소스 코드는 다음과 같습니다.

``` js
import React from "react";
import { connect } from 'react-redux';
import { getExpenseList, deleteExpense } from '../actions/expenseActions';

class ExpenseEntryItemList extends React.Component {
   constructor(props) {
      super(props);
   }
   componentDidMount() {
      this.props.getExpenseList();
   }
   handleDelete = (id, e) => {
      e.preventDefault();
      this.props.deleteExpense(id);
   }
   getTotal() {
      let total = 0;
      if (this.props.expenses != null) {
         for (var i = 0; i < this.props.expenses.length; i++) {
            total += this.props.expenses[i].amount
         }
      }
      return total;
   }
   render() {
      let lists = [];
      if (this.props.expenses != null) {
         lists = this.props.expenses.map((item) =>
            <tr key={item.id}>
               <td>{item.name}</td>
               <td>{item.amount}</td>
               <td>{new Date(item.spendDate).toDateString()}</td>
               <td>{item.category}</td>
               <td><a href="#"
                  onClick={(e) => this.handleDelete(item.id, e)}>Remove</a>
               </td>
            </tr>
         );
      }
      return (
         <div>
            <table>
               <thead>
                  <tr>
                     <th>Item</th>
                     <th>Amount</th>
                     <th>Date</th>
                     <th>Category</th>
                     <th>Remove</th>
                  </tr>
               </thead>
               <tbody>
                  {lists}
                  <tr>
                     <td colSpan="1" style={{ textAlign: "right" }}>Total Amount</td>
                     <td colSpan="4" style={{ textAlign: "left" }}>
                        {this.getTotal()}
                     </td>
                  </tr>
               </tbody>
            </table>
         </div>
      );
   }
}
const mapStateToProps = state => {
   return {
      expenses: state.data
   };
};
const mapDispatchToProps = {
   getExpenseList,
   deleteExpense
};
export default connect(
   mapStateToProps,
   mapDispatchToProps
)(ExpenseEntryItemList);
```



다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```



그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![List Expenses](.\images\list_expenses.jpg)





### 비용 추가

출처: https://www.tutorialspoint.com/reactjs/reactjs_add_expense.htm

ExpenseEntryItemList.js를 열고 redux 라이브러리에서 연결을 가져옵니다.

``` js
import { connect } from 'react-redux';
```



다음으로 Formik 라이브러리를 가져옵니다.

``` js
import { Formik } from 'formik';
```



다음으로 라우터 라이브러리에서 withRouter 메소드를 가져옵니다.

``` js
import { withRouter } from "react-router-dom";
```



다음으로 액션 라이브러리에서 addExpense를 가져옵니다.

``` js
import { addExpense } from '../actions/expenseActions';
```



다음으로 비용에 대한 초기 값으로 생성자를 만듭니다.

``` js
constructor(props) {
super(props);
this.initialValues = { name: '', amount: '', spend_date: '', category: '' }
}
```



다음으로 validate 메소드를 작성하십시오.

``` js
validate = (values) => {
   const errors = {};
   if (!values.name) {
      errors.name = 'Required';
   }
   if (!values.amount) {
      errors.amount = 'Required';
   }
   if (!values.spend_date) {
      errors.spend_date = 'Required';
   }
   if (!values.category) {
      errors.category = 'Required';
   }
   return errors;
}
```



다음으로 이벤트 핸들러 메소드를 추가합니다.

```js
handleSubmit = (values, setSubmitting) =< {
   setTimeout(() =< {
      let newItem = {
         name: values.name,
         amount: values.amount,
         spendDate: values.spend_date,
         category: values.category
      }
      this.props.addExpense(newItem);
      setSubmitting(false);
      this.props.history.push("/list");
   }, 400);
}
```

여기,

- 비용 항목을 추가하기 위해 addExpense 메소드 사용
- 라우터 히스토리 방식을 사용하여 경비 목록 페이지로 이동합니다.

다음으로 Formik 라이브러리를 사용하여 생성된 양식으로 렌더링 방법을 업데이트합니다.

``` js
render() {
   return (
      <div id="expenseForm">
         <Formik
            initialValues={this.initialValues}
            validate={values => this.validate(values)}
            onSubmit={(values, { setSubmitting }) => this.handleSubmit(values, setSubmitting)}>
            {
               ({
                  values,
                  errors,
                  touched,
                  handleChange,
                  handleBlur,
                  handleSubmit,
                  isSubmitting,
                  /* and other goodies */
               }) => (
                  <form onSubmit={handleSubmit}>
                     <label for="name">Title <span>{errors.name && touched.name && errors.name}</span></label>
                     <input type="text" id="name" name="name" placeholder="Enter expense title"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.name} />

                     <label for="amount">Amount <span>{errors.amount && touched.amount && errors.amount}</span></label>
                     <input type="number" id="amount" name="amount" placeholder="Enter expense amount"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.amount} />

                     <label for="spend_date">Spend Date <span>{errors.spend_date && touched.spend_date && errors.spend_date}</span></label>
                     <input type="date" id="spend_date" name="spend_date" placeholder="Enter date"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.spend_date} />

                     <label for="category">Category <span>{errors.category && touched.category && errors.category}</span></label>
                     <select id="category" name="category"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.category}>
                        <option value="">Select</option>
                        <option value="Food">Food</option>
                        <option value="Entertainment">Entertainment</option>
                        <option value="Academic">Academic</option>
                     </select>
                     <input type="submit" value="Submit" disabled={isSubmitting} />
                  </form>
               )
            }
         </Formik>
      </div>
   )
}
```



그런 다음 디스패치 메서드를 component 속성에 매핑합니다.

``` js
const mapDispatchToProps = { 
   addExpense, 
};
```



마지막으로 component를 연결하여 저장하고 WithRouter로 component를 래핑하여 라우터 링크에 프로그래밍 방식으로 액세스합니다.

``` js
export default withRouter(connect(
   null,
   mapDispatchToProps
)(ExpenseEntryItemForm));
```



component의 전체 소스 코드는 다음과 같습니다.

``` js
import React from "react";

import { connect } from 'react-redux';
import { Formik } from 'formik';
import { withRouter } from "react-router-dom";
import { addExpense } from '../actions/expenseActions';

class ExpenseEntryItemForm extends React.Component {
   constructor(props) {
      super(props);

      this.initialValues = { name: '', amount: '', spend_date: '', category: '' }
   }
   validate = (values) => {
      const errors = {};
      if (!values.name) {
         errors.name = 'Required';
      }
      if (!values.amount) {
         errors.amount = 'Required';
      }
      if (!values.spend_date) {
         errors.spend_date = 'Required';
      }
      if (!values.category) {
         errors.category = 'Required';
      }
      return errors;
   }
   handleSubmit = (values, setSubmitting) => {
      setTimeout(() => {
         let newItem = {
            name: values.name,
            amount: values.amount,
            spendDate: values.spend_date,
            category: values.category
         }
         this.props.addExpense(newItem);
         setSubmitting(false);
         this.props.history.push("/list");
      }, 400);
   }
   render() {
      return (
         <div id="expenseForm">
            <Formik
               initialValues={this.initialValues}
               validate={values => this.validate(values)}
               onSubmit={(values, { setSubmitting }) => this.handleSubmit(values, setSubmitting)}>
               {
                  ({
                     values,
                     errors,
                     touched,
                     handleChange,
                     handleBlur,
                     handleSubmit,
                     isSubmitting,
                     /* and other goodies */
                  }) => (
                     <form onSubmit={handleSubmit}>
                        <label for="name">Title <span>{errors.name && touched.name && errors.name}</span></label>
                        <input type="text" id="name" name="name" placeholder="Enter expense title"
                           onChange={handleChange}
                           onBlur={handleBlur}
                           value={values.name} />

                        <label for="amount">Amount <span>{errors.amount && touched.amount && errors.amount}</span></label>
                        <input type="number" id="amount" name="amount" placeholder="Enter expense amount"
                           onChange={handleChange}
                           onBlur={handleBlur}
                           value={values.amount} />

                        <label for="spend_date">Spend Date <span>{errors.spend_date && touched.spend_date && errors.spend_date}</span></label>
                        <input type="date" id="spend_date" name="spend_date" placeholder="Enter date"
                           onChange={handleChange}
                           onBlur={handleBlur}
                           value={values.spend_date} />

                        <label for="category">Category <span>{errors.category && touched.category && errors.category}</span></label>
                        <select id="category" name="category"
                           onChange={handleChange}
                           onBlur={handleBlur}
                           value={values.category}>
                           <option value="">Select</option>
                           <option value="Food">Food</option>
                           <option value="Entertainment">Entertainment</option>
                           <option value="Academic">Academic</option>
                        </select>
                        <input type="submit" value="Submit" disabled={isSubmitting} />
                     </form>
                  )
               }
            </Formik>
         </div>
      )
   }
}
const mapDispatchToProps = {
   addExpense,
};
export default withRouter(connect(
   null,
   mapDispatchToProps
)(ExpenseEntryItemForm));
```



다음으로 npm 명령을 사용하여 애플리케이션을 제공합니다.

``` sh
npm start
```



그런 다음 브라우저를 열고 주소 표시줄에 http://localhost:3000을 입력하고 Enter 키를 누릅니다.

![List Expenses](.\images\list_expenses.jpg)

마지막으로 기본 기능을 갖춘 간단한 React 응용 프로그램을 성공적으로 만들었습니다.



### Conclusion

React는 가장 인기 있고 적극 권장되는 UI 프레임워크 중 하나입니다. 그 인기에 걸맞게 아주 오랫동안 개발되어 활발히 유지되고 있습니다. React 프레임워크를 배우는 것은 프론트 엔드 개발자에게 좋은 출발점이며 전문 경력을 향상시키는 데 확실히 도움이 될 것입니다.



### - 끝 -

