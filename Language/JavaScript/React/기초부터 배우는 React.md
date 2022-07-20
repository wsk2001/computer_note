# 기초부터 배우는 React

출처: https://berkbach.com/%EA%B8%B0%EC%B4%88%EB%B6%80%ED%84%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-react-js-1531b18f7bb2



# 기초부터 배우는 React — Part 1

## 리액트에 대한 전반적인 설명과 특징

# 🗂 목차

- **기초부터 배우는 React — Part 1 🧑‍💻**
- [기초부터 배우는 React — Part 2](https://medium.com/wasd/기초부터-배우는-react-js-2-f0e8d4a55740)
- [기초부터 배우는 React — Part 3](https://medium.com/wasd/기초부터-배우는-react-part-3-a76a727447d3)
- [기초부터 배우는 React — Part 4](https://medium.com/wasd/기초부터-배우는-react-part-4-64164239179f)
- [기초부터 배우는 React — Part 5](https://medium.com/wasd/기초부터-배우는-react-part-5-77e997cf597)
- [기초부터 배우는 React — Part 6](https://medium.com/wasd/기초부터-배우는-react-part-6-5bb4b072621a)

안녕하세요, 지금부터 리액트 강좌를 시작해보려 합니다.

리액트는 현재 굉장히 핫한 라이브러리 중 하나입니다. 현재 프론트엔드 진영에는 3개의 라이브러리 OR 프레임워크가 경쟁 중인데 바로 React, Angular, Vue 입니다. 깃허브 스타가 이를 보장해주기도 하고, 요새 이 세개 중에 하나 정도는 이름을 들어본 적이 있을 정도로 셋은 프론트엔드계의 끝판왕을 달리고 있습니다 (현재)

현 프론트엔드 3대장: Angular | React | Vue

이 강좌 에서는 **리액트**에 대해서 알아볼 것입니다.

**리액트**는 **페이스북**이 만든 사용자 UI 구축을 위한 라이브러리 입니다. 오직 사용자의 View 에만 초점을 맞추고 있죠. 그래서 **Routing** 같은 기술이 리액트 자체에는 들어가 있지 않습니다. 하지만 개발자들이 여러 가지 리액트에 필요한 것들을 만들어 놓아서 사실상 프레임워크 위치에 있다고 봐도 무방할 정도입니다. 그만큼 리액트의 커뮤니티가 활발하다는 증거이겠죠.

아무튼, 이 리액트는 **3가지 대표적인 특징**을 가지고 있습니다.

## 1. JSX 문법

**JSX**는 자바스크립트 안에서 HTML 문법을 사용해서 **view**를 구성할 수 있게 도와주는 자바스크립트 문법으로, 리액트 개발에 엄청난 도움을 줍니다.

아래 코드를 보세요, 지금은 이해하지 못해도 괜찮습니다.

```js
class HelloMessage extends React.Component {
  render() {
    return (
      <div>
        Hello {this.props.name}
      </div>
    );
  }
}
```

코드를 보면, 뭔가 익숙한 HTML 태그가 보이죠? 이게 바로 **JSX** 입니다.

원래 JS 안에서는 저런 문법 사용이 불가능 하지만, JSX는 이를 가능하게 해줍니다. 그리고 JSX를 사용하지 않고도 리액트 개발을 할 수가 있는데요. 하지만 많은 리액트 개발자들은 JSX를 사용하는데, 아래 코드를 보시면 이 JSX가 얼마나 편한지 알 수 있습니다.

```js
class HelloMessage extends React.Component {
  render() {
    return React.createElement(
      "div",
      null,
      "Hello ",
      this.props.name
    );
  }
}
```

보시면 이제 JS 문법 같아 졌지만, 생각해볼때 마크업 개발은 저렇게 하나의 div만 있는 것도 아니고 복잡할텐데, 지금처럼 리액트를 짜게 되면 개발자는 매우 힘들 것입니다. 하지만 JSX의 사용으로, 리액트의 사용성은 굉장히 증가했다고 할 수 있겠고 *대표적인*  **특징**이 되었습니다.



## 2. Component 기반

리액트는 **컴포넌트 기반** 라이브러리 인데요, 컴포넌트 기반이라 함은 기존의 웹 페이지를 작성할 때 처럼 하나의 HTML 코드를 집어넣고 하는 것이 아닌, **여러 부분을 분할 해서 코드의 재사용성과 유지보수성을 증가**시켜 줍니다.

쉽게 설명해 보자면…

우리가 프론트를 짤 때 아주 긴 HTML 코드를 적을 때가 있을 텐데, 그 코드의 일부를 수정해야 한다고 할 때 그 부분을 찾는 것부터 시작해서 내가 지금 수정한 코드의 부분만 수정되어야 하는데, 잘못하고 다른 부분을 건드리면 문제 해결을 하는게 아니고 도리어 문제를 키우게 됩니다.

컴포넌트 기반인 React로 개발을 하면 HTML 코드를 부분 부분 파일로 담아서 어떤 부분을 수정해야 한다고 하면 **그 부분의 파일만 수정**하면 됩니다. 그렇게 되면 다른 부분을 건드려 문제를 키우는 일이 발생하지 않겠죠?



## 3. Virtual DOM

> [벨로퍼트님의 아주 멋진 포스트](https://velopert.com/3236)를 보고 참고했습니다.

가상 돔은 기존 **DOM**의 한계를 탈피하기 위해서 나온 대안입니다.

***DOM이란? D***ocument ***O***bject ***M***odel 단어 그대로 **문서 객체 모델** 입니다. 우리가 사용하는 HTML 단위 하나하나를 객체로 생각한 모델 이라고 생각하면 될 것 같네요.

![img](.\images\Vvi4_infsE8Q0uAStZmiWw.png)

DOM의 예제

자, 그런데 이 DOM에 무슨 문제가 있느냐?

DOM의 구조는 **트리 구조**로 되어 있습니다. HTML 구조와 같죠. 만약 프로그래머가 어떤 DOM의 요소를 하나 수정하는 함수를 만들고 실행시킬때, 렌더트리를 재생성하고 (그러면 모든 요소들의 스타일이 다시 계산됩니다.) 레이아웃을 만들고 페인팅을 하는 과정이 다시 반복되는거죠.

이게 어떤 문제가 있냐면, 우리가 복잡한 SPA 개발을 통해서 DOM의 요소를 굉장히 많이 수정한다고 할 때, **불필요한 연산이 매번 일어난다는 것**이죠.

우리는 렌더트리가 매번 재생성 되는것을 좋지 않게 생각하기 때문에, 가능하면 한번에 모든 작업이 진행되는 것을 더 선호할 것입니다.

그 문제를 해결하기 위해서 **가상 DOM**이 등장 하였습니다. DOM 자체가 추상화 개념인데, 거기에 한번 더 추상화를 한 것이 가상 DOM 입니다.

***가상 돔은 변화를 가상 돔에서 미리 인지해 변화**시킵니다*. 이 작업은 실제 DOM이 아니기 때문에 렌더링도 되지 않고 연산 비용이 비교적 실제 DOM 보다는 적습니다. 그 가상 DOM의 변화를 마지막에 실제 DOM에게 던져주어, 모든 변화를 한번에 렌더링 되게 합니다.

*그런데 이러한 생각은 우리는 충분히 가상 DOM을 사용하지 않고도 할 수 있었을 텐데, 왜 굳이 가상 DOM을 쓰는걸까요?*

만약 직접 DOM을 업데이트하게 된다면 그 부분만 업데이트하기 위해서 업데이트 하지 않은 부분과 업데이트된 부분을 알고 있어야 하는데, **가상 돔은
바뀌지 않은 부분과 바뀐 부분을 자동으로 감지해서 업데이트 시켜줍니다.**

또한, 변경하려는 DOM이 변경되었는지, 변경되지 않았는지에 대한 각 동기화 정보를 알고 있을 필요가 없음과 동시에 하나로 묶어서 작업해주기 때문이죠.

결론적으로 가상 DOM을 사용하는 이유는, 실제 DOM을 직접 변경할 수는 있지만, 그 작업이 굉장히 값비싼 작업이기 때문에, 가상 돔에서 미리 최적화를 한 번 해준다는 것 입니다.

이번 리액트에 대한 전반적인 설명은 여기까지 하는 것으로 하겠습니다.
다음 시간에는 **리액트 프로젝트를 생성**하고 **기초 문법**을 살펴보도록 하겠습니다.





# 기초부터 배우는 React — Part 2

## Create-React-App & Chrome Extensions

안녕하세요 리액트 강좌 두번째 시간입니다.

이번 시간에는 **리액트 프로젝트를 생성**하고 **크롬 개발자 도구를 설치**해 보도록 하겠습니다. 일단, 리액트 자체를 사용하는 것은 굉장히 간단합니다. Jquery를 사용하듯이 CDN 방식으로 불러와서 사용할 수도 있습니다.

```html
<script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
<script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
```

그런데, 실제로 저는 이렇게 리액트를 사용하는 분은 만나보지를 못했습니다. 제가 리액트를 배울 때도 이렇게 가르쳐 주는 정보도 없었죠.

리액트 자체만 사용하는것이 아닌, 여러 가지 라이브러리를 동시에 사용하기 때문에 CDN 으로 리액트를 사용하면 리액트를 **100% 활용할 수 없습니다.**
lint의 도움도 받지 못하고 에디터의 도움을 **제한적**으로만 받을 수 있기 때문입니다. *( React를 할때 lint 의 도움이 없으면 많이 힘들어집니다.. )*

그래서 많은 사람들은 리액트를 작업할 때 [***webpack\***](https://webpack.js.org/) 이나 [***parcel\***](https://parceljs.org/) 같은 ***번들러\***를 이용해서 작업을 합니다. 모든 모듈이나 여러가지 라이브러리를 동시에 합쳐서 하나의 .js 파일로 내보내는거죠. 또한 리액트 파일은 JSX 문법으로 작성되거나 최신 JS 문법으로 작성되기 때문에, [***babel\***](https://babeljs.io/) 을 사용해서 ***브라우저 호환성\***을 생각해야 합니다.

바로 여기에 바로 리액트의 진입장벽이 존재하죠. 리액트를 위해서 *webpack*이나 *babel*의 사용방식을 배워야 한다는 겁니다.

> “나는 리액트를 배우고 싶었는데, 막상 내가 만지고 있는 건 리액트가 아니라 설정 파일이네?”

그래서 *facebook*이 강력한 **리액트 개발 도구**를 지원해 주고 있는데, 바로[***create-react-app\***](https://github.com/facebook/create-react-app) 입니다.

***create-react-app\***은 리액트 초보자에게는 정말 좋은 스타터 킷이고, 리액트 숙련자 분들도 여기에 자신들이 원하는 설정을 넣어서 사용할 정도로 잘 만들어진 도구입니다. 명령어만 입력하면 자동으로 리액트 앱을 만들어 줍니다.

우리는 *create-react-app*의 사용 방법에 대해서 알아볼 것입니다.
우선 실습에 필요한 준비물이 필요합니다.

1. Node.js 설치 (가능하면 [*최신버전*](https://nodejs.org/en/)으로)
2. 터미널 (windows의 경우에는 git bash를 이용하면 편리)
3. 코드 에디터 (개인취향, 마음에 드는 거 쓰세요)

[Various Web editors책 뿐만 아니라 코드에도 형광펜으로 줄쳐 가독성을 높이자!medium.com](https://medium.com/wasd/web-여러가지-다양한-웹-에디터들-e4450278a75c)



# Create React App 사용해보기

작업환경 폴더에 터미널을 위치시켜 주세요.`npx create-react-app hello` 을 입력합니다.

![img](.\images\FEBgXWw_1zbpqizuUxtZPw.png)

![img](.\images\mLZkpFp-k7k9Pw7OWZICDA.png)

![img](.\images\B-Sw37CMjOIaVNUnZVpV8Q.png)

create-react-app

자 이제 *create-react-app*의 설치가 끝나고 리액트 앱이 만들어졌습니다.
마지막 사진에 보시면 여러가지 명령어가 눈에 보이죠?

- `yarn start` (npm start)
- `yarn build` (npm run build)
- `yarn test` (npm run test)
- `yarn eject` (npm run eject)

이 **네 개의 명령어**로 리액트 앱을 개발모드에서 **개발**하고, 리액트를 최종 배포 버전으로 **배포**하고, **테스트** 하고, **설정을 추가**하는 등의 명령을 할 수 있습니다. 우선 오늘은 리액트 앱을 시작만 해 보도록 할게요.

리액트 앱이 완성된 폴더로 터미널을 이동시켜서 `yarn start`를 입력해 주세요. 그렇게 되면 약간의 로딩 후 브라우저에 리액트 앱이 나타날 것입니다.

![img](.\images\ZQSccSb-14ruJ0YjBc_kug.png)

yarn start 실행결과 브라우저

이 화면이 나오면 첫 리액트앱을 성공적으로 실행하셨네요. 이제 우리는 리액트 앱을 개발하기 위한 준비 과정을 거의 마쳤습니다.

마지막으로, ***chrome\*** *브라우저에는* ***리액트 개발을 쉽게 하기 위한 개발자 도구\****가있습니다.* 이 [링크](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en-US)를 클릭해 크롬 확장 프로그램을 다운받아 주세요.

설치가 완료되면 크롬 메뉴 바에 작은 리액트 도구가 뜰 텐데, 이 색깔이 들어와 있으면 리액트가 ***활성화\*** 되었다는 의미입니다. 리액트가 활성화 되면 개발자 도구에서 **리액트 전용 개발 도구**를 사용하실 수 있습니다.

![img](.\images\oCn6_VxjQ_Z0UzFaFCv22w.png)

리액트 개발자 도구 실행 장면

오늘은 리액트를 개발하기 위한 준비를 하는 시간이였습니다. 다음 시간에는*create-react-app*으로 생성된 ***리액트 폴더 파일 분석\***을 해보도록 하겠습니다.



# 기초부터 배우는 React — Part 3

## CRA 파일 분석

안녕하세요. 리액트 강좌 세 번째 시간입니다.

이번 시간에는 [***Part 2\***](https://medium.com/wasd/기초부터-배우는-react-js-2-f0e8d4a55740)에서 만들었던 Create-React-App 폴더의 ***파일들을 분석\***해 보겠습니다. 먼저 에디터를 통해서 만들었던 CRA 폴더를 열어보세요.

폴더 구조는 다음과 같이 되어 있을 것 입니다. 여기 폴더들 중, **가장 중요한
폴더인 \*public과 src 폴더\***에 대해서 알아보겠습니다.

> 익숙하지 않은 문법에 관해서는 [클래스](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Classes)에 관해서 공부하시면 됩니다.
> 나머지 폴더들이 어떤 역할을 하는지 알고 싶다면 npm과 git에 관해서 공부하시면 됩니다.

![img](.\images\XPyJ5jVwuEw1XTptR7hMmQ.png)

CRA 의 폴더 구조

***public:\*** 가상 DOM을 사용하는 리액트는 실제 DOM이 필요합니다. 즉, 가상 DOM이 들어갈 빈 껍데기 html이 필요하다는 것인데, 바로 그 빈 껍데기가 존재하는 폴더입니다.

***src:\*** 리액트 개발이 이루어지는 **메인 폴더**입니다. 앞으로 우리는 src폴더를 주로 수정하고 src 폴더에 있는 파일들을 작성하는데 많은 시간을 보내게 될 것입니다.

다음은 그 안에 있는 파일에 대해서 알아보아야 하겠죠?



# Public 폴더

![img](.\images\jWFu7G6kV2t-hbQ61FDfTg.png)

public 폴더의 내부 구조

- **favicon.ico:** 페이지 아이콘 이미지 파일입니다.
- **index.html:** 가상 DOM이 들어가기 위한 빈 껍데기 html 파일입니다.
- **manifest.json:** [웹 앱 매니페스트](https://developer.mozilla.org/en-US/docs/Web/Manifest)는 사용자가 앱을 볼 것으로 예상되는
  영역(예: 휴대기기 홈 화면)에 웹 앱이나 사이트를 나타내는 방식을
  개발자가 제어하고, 사용자가 시작할 수 있는 항목을 지시하고, 시작 시의 모습을 정의할 수 있는 JSON 파일 입니다.

> 구글이 만들어놓은 메니페스트에 대한 정의 글을 가져왔습니다.



# src 폴더

![img](.\images\EeZIi4hKKcs-rGUnHeNmUA.png)

src 폴더의 내부 구조

src 폴더의 내부에는 파일이 여러 개가 있는데, 그 중 *index.js*와 *App.js*가 어떻게 동작하는지만 알아도 리액트가 어떻게 동작하는지 알 수 있습니다.

> *registerServiceWorker.js* 에 관해서 알고 싶으시다면 제 포스트 중 [**서비스 워커**](https://medium.com/wasd/service-worker-에-관해서-9c8f9f2f3988)에 관련된 글이 있으니 참고 바랍니다.



## **index.js**

![img](.\images\phHVSE5pw8SdpdYJ8ejBQw.png)

index.js

``` js
import React from 'react'
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServuceWorker from './registerServuceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServuceWorker();
```



`테스트 시점(2022-07-08) 에는 아래 처럼 생성 되었음`

``` js
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

```



이 코드는 *App.js* 에서 생성된 리액트 코드를 *index.js*에서 불러온 후, public에 있는 *index.html* 의 id가 root인 곳에다가 넣는 코드입니다.

> 그 외에 CSS를 넣는 코드나, 아니면 서비스 워커를 사용하는 코드는 없어도 됩니다. 부가사항 이기 때문이죠.



## **App.js**

![img](.\images\Lz8WqVZCV7olm5SVUvqKEQ.png)

App.js

``` js
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
```

```
테스트 시점(2022-07-08) 에는 위 처럼 생성 되었음
render() 함수로 감싸는 부분이 사라졌음
```



이 코드는 리액트 코드를 생성하는 부분인데요.

먼저 App이라는 클래스를 생성한 후, 리액트 컴포넌트를 상속받습니다. 그렇게 되면 리액트 컴포넌트 메소드를 사용할 수가 있게 됩니다.

`render()` 메소드는 리액트 컴포넌트인데, **화면에 html 뷰를 생성해주는 역할**을 합니다. **return** 으로 받는 값들은, 나중에 html 코드로 바뀌게 됩니다. 그렇게 생성된 App 클래스를 **export** 문법을 이용해서 내보냅니다.

더 간단하게 말해보자면,

1. 리액트, 리액트 컴포넌트를 불러옵니다.
2. App 클래스를 만드는데, 그 클래스는 **리액트 컴포넌트를 상속**받습니다.
3. 상속받은 리액트 컴포넌트 메소드 중, `render()` **메소드를 활용해서 html 코드를 작성해** **return** 시켜줍니다.
4. 이렇게 작성된 리액트 코드를 **export** 시켜줍니다.

그런데 가만히 보면 *index.js*에서 App을 불러와 사용할 때에 html 코드를 사용하는 걸 (`<App />`) 볼 수 있는데, 모든 리액트 파일들은 전부 html 코드 처럼 사용할 수 있습니다. 이를 통해서 알 수 있는 것은 **html 코드를 여러 개의 리액트 파일로 분할해서 작업을 할 수가 있다는 것** 입니다. 또한 그렇게 되면 여러 사람이 협업을 하는 것도 쉬워지고, 코드의 수정도 특정 부분만 하면 되기 때문에 이점이 많아지게 됩니다.

오늘은 CRA 로 생성되는 폴더와 파일에 대해서 살펴보았습니다. 다음 시간에는 리액트에서 데이터를 관리하는 부분인 **Props** 와 **State** 에 관해서 알아보도록 하겠습니다.





# 기초부터 배우는 React — Part 4

## Props & State (1)

안녕하세요! 리액트 강좌 네 번째 시간입니다.

오늘은 전 시간에 말씀드렸던 데로 `Props` 와 `State`에 대해서 알아보도록 하겠습니다. 하지만 `Props`와 `State`의 개념은 제가 리액트를 처음 배울 때 헷갈렸던 개념이어서, **오늘은** **State****에 대해서만 알아보도록 하겠습니다.

> `Props`와 `State`는 어려운 개념은 아니지만 우리가 리액트를 하면서
> 항상 사용할 것이고, 중요하기 때문에 2강에 걸쳐서 포스트가 진행됩니다.



`Props`와 `State`는 리액트에서 **데이터를 다룰 때 사용하는 개념**입니다. 둘 다 정말 중요한 개념이고, 이제 우리는 3강에서 CRA 폴더 구성까지 배웠으니, 이제 데이터를 직접 조작할 때가 되었습니다.

우선 기존에 만들었던 프로젝트 폴더를 좀더 알아보기 쉽게 정리하겠습니다. 우선`src` 폴더 안에 있는 파일들 중에서 `App.js`, `index.js`만 남기고 다른 파일들을 모두지워주세요.

![img](.\images\XpzQ13HBiASB3XY0rTmuaw.png)

App.js와 index.js만 남아있어야 합니다.

폴더 구성이 완료 되셨으면 터미널을 열고 `**yarn start**` or `**npm start**`를 해보세요. 잘 작동하나요? 아마도 아닐 것입니다. 왜냐하면 `App.js`와 `index.js`에서는 삭제한 파일들을 `**import**` 해서 사용할텐데, 그 파일을 우리가 지워버렸으니 오류 없이 잘 동작할 리가 없습니다.

그렇기 때문에 ***삭제된 파일을\*** `**import**`***하고 있는 파일인\*** `App.js`***와\*** `index.js`***를 변경해 주어야 합니다.\***

우선은 `App.js` 부터 바꾸어 주도록 하겠습니다. 사실 `App.js`에서 지워야 할 부분은 이미지와 CSS 파일을 `**import**` 하는 부분만 없애면 되는데, 오늘 우리는 이 `App.js` 파일에서 대부분의 시간을 보낼 것이기 때문에 `HTML` 부분도 알아보기 쉽게 하기 위해서 비워주도록 하겠습니다.

![img](https://miro.medium.com/max/3060/1*tqJ_pXtCZb8UhtxaTr01Ng.png)

![img](.\images\sCjQJzQBjEt9Dicl-G6SJQ.png)

App.js 파일을 → 이렇게 변경합니다.

다음은`index.js` 파일을 정리할 차례입니다.

![img](.\images\O2YANqiwmLBq32zU_PX7rw.png)

![img](.\images\brsW9x_8kwWVQ-V9rjx-jA.png)

index.js 파일을 → 이렇게 변경합니다.

이제서야 `Props`와 `State`에 이야기를 해볼 수 있게 되었네요. 먼저`State` 에 대해서 이야기 해 보자면, **State** **는 하나의 컴포넌트가 가질 수 있는 변경 가능한 데이터** 입니다. 컴포넌트를 렌더링 할 때 새로운 데이터를 생성해야 한다던지, 아니면 기존의 데이터를 참고해서 새로운 데이터를 만들어야 할 때 사용할 수 있습니다. 예제를 보여드리도록 하겠습니다. `App.js` 파일을 열어서 밑에 코드처럼 변경해 주세요.

> 아, 그리고 터미널을 열고 작업 디렉터리 경로에서 `**yarn start**` or `**npm start**` 하시고 사용해 주세요. 코드가 변경되어서 저장될 때마다, [http://localhost:3000](http://localhost:3000/) 포트에 들어가게 되면 지금 만들고 있는 리액트 앱이 새로고침 되면서 변경될 겁니다.



코드의 결과가 잘 나타나는지 웹에서 테스트해 보세요.

![img](.\images\5yw8el0LTR9cYIZq4FwRwA.png)

App.js

State를 생성 했습니다.

> 오류가 없다면 브라우저에 hello app js! 라는 문자열이 보일 것입니다.

저기 코드를 보면 `{this.state.hello}` 부분이 보이시나요? 바로 저 부분이 hello app js!라는 문자열이 들어가 있는 `State`변수가 되는 것입니다. 그리고 또 알아야 할 점은, `State` 뿐만 아니라 **JSX에 변수를 넣을 때에는 중괄호 {}에 담아야 한다는 사실입니다.**

이제, `this.state.hello` 값을 변경해 보도록 하는 메소드를 하나 만들어 보도록 하겠습니다. `App.js` 파일을 사진과 같이 수정해 주세요.

![img](.\images\lfasb1q8jKEjZEVhWERa-Q.png)

handleChange 메소드를 생성 했습니다.

오류가 없다면 브라우저에 click me! 라는 버튼이 있고, 클릭을 하면 문자열인 hello app js!가 bye app js! 로 변하게 될 것입니다.

이 코드에 대해서 설명하자면 이렇습니다. `button`을 클릭하게 되면, `handleChange` 라는 메소드가 실행이 되고, `this.setState` 메소드를 통해 `state`인 `hello` 의 데이터가 `bye app js!` 라는 문자열로 바뀌게 됩니다.
그렇게 되면 `JSX`에서 `hello`를 보여 주는 부분이 `bye app js!` 로 보여지게 되겠죠.

`HTML`에서 버튼에 `click` 이벤트를 줄 때에는, `onclick`이벤트를 추가해 함수를 전달하곤 했었는데 리액트 에서도 기본적인 메소드 이름은 같습니다. 한가지 유의해야 할 점은, 앞으로 우리가 사용하게 될 이벤트들의 이름은 **camelCase**를 사용합니다.

그리고 한가지 이해 안될 수 있는 부분이 있는데, `this.handleChange`를 전달하는 부분에 `this.handleChange()` 이렇게 해야 한다고 생각하시는 분들도 계실지 모릅니다. 하지만 **리액트에서 클릭 이벤트를 줄 때에는 저렇게 주면 안 됩니다.** 왜냐하면, 이것은 `**HTML**` **파일이 아닌** `**JSX**` **파일입니다.** 그렇기 때문에`JSX`가 `HTML`로 바뀌는 과정에서 함수가 실행이 되버리기 때문에 `this.handleChange` 를 적어주셔야 합니다.

`State` 예제에 대해서 한 가지만 더 해보도록 하겠습니다. App.js를 다음과 같이 수정해 주세요.

![img](.\images\AHXHOv0QPC3YVOSHYKlbEA.png)

Count 예제

> 오류가 없다면 버튼을 클릭 했을 때 숫자가 0에서 1씩 누를 때마다 증가할 것입니다.

웹 브라우저에서 count up! 버튼을 클릭해 보면 `count`가 잘 작동할 것입니다.

결론은, `**state**`**는 현재 컴포넌트 안에서 새로운 데이터를 만들어 낼 때 사용합니다.** 지금 알 수 있듯이`countUp` 메소드가 실행될 때 마다 `state`인`count`의 숫자는 올라갑니다. 컴포넌트 안에서 `count` 라는 데이터가 생성되어 `countUp` 이라는 메소드에 의해 잘 작동이 된 것이죠.

오늘 `Props`와 `State`에 대해서 전부 설명해 드리려고 했는데, 제가 초보자 일 때는 `Props`와 `State`에 대한 개념이 같이 들어오다 보니 혼란스러웠던 기억이 있습니다. 그래서 2강으로 나누어 오늘은 `State`만 설명했습니다. 다음 강좌는 `Props`에 대해서 알려드리도록 하겠습니다. 감사합니다 😃



# 기초부터 배우는 React — Part 5

## Props & State (2)

안녕하세요. 리액트 강좌 다섯 번째 시간입니다.

오늘은 전 시간에 못하고 끝냈던 Props에 대해서 알아보겠습니다. 우선, Props에 대해 설명을 하기 전에 State와 Props에 대해서 정확히 짚고 넘어가겠습니다.

1. State는 현재 컴포넌트 내에서 변경이 가능하다.
2. Props는 현재 컴포넌트 내에서 변경이 불가능하다.
   (예전 리액트 버전에서는 가능했으나 현재 버전에서는 사용하지 않는 것을 추천)
3. Props와 State 모두 하위 컴포넌트에 상속이 가능하다.

이제 Props에 대해서 알아보도록 하죠. 일단 Props를 만들어 출력해 보도록 하겠습니다. src/index.js 파일을 변경해 주세요.

![img](.\images\ok35avHRWeqT1LfHilBhSA.png)

index.js

index.js 코드를 보면 message라는 변수를 App.js가 Props로 사용할 수 있게 전달해 주고 있습니다. 상속을 시켜준 것이죠. 이렇게 받은 message 라는 문자열을 App.js에서는 이렇게 사용할 수 있습니다.

![img](.\images\l3JGBcaUXkBf52bovvOMTQ.png)

App.js

**this.props.message,** App.js에서는 state를 사용하듯이 사용하면 됩니다. 다만, Props는 State 처럼 변경할 수 없는 점을 꼭 기억해 주세요. 하지만 Props를 절대로 변경할 수 없는 것은 아닙니다. App.js에 다른 컴포넌트를 상속시켜서 Props를 변경해 보도록하는 예제를 작성해 보겠습니다. App.js 파일을 다음과 같이 변경해 주세요.

![img](.\images\sG5wvmt0UXy6GF-fVQ9aHA.png)

App.js

이 코드에 대해 설명해 보자면, 먼저 InsideApp이라는 컴포넌트를 하나 생성했습니다. 그리고 App.js에서 InsideApp 컴포넌트를 받아오고, 그 때 App 컴포넌트의 state와 메소드인 count 와 handleChange를 상속시켜 주었습니다. 크롬을 실행하면 다음과 같은 html이 보이게 됩니다.

![img](.\images\T71ZY0HzszAtuJP8A8avEQ.png)

http://localhost:3000

이제 테스트를 해 보아야 하겠죠? 저 두개의 버튼 중 어떤 버튼을 클릭해도 State의 숫자와 AppProps의 숫자 모두 1씩 증가하게 됩니다. 이게 어떻게 가능한 일 일까요?

코드를 보겠습니다. 우리는 InsideApp컴포넌트에 App컴포넌트의 state인 count와 App컴포넌트의 메소드인 handleChange를 상속시켜 주었습니다. 그렇기 때문에, 두 개의 버튼 중 어떤 버튼을 클릭하던지 App컴포넌트의 state인 count가 증가하게 되어 상속받는 props인 count가 +1 되는 것 입니다.

Props를 직접적으로 변경이 불가능 하지만 이런식으로 상위 컴포넌트에서 state를 변경하는 메소드를 props로 끌어옴으로써 간접적으로 변경이 가능합니다.

Props와 State에 대한 설명이 끝이 났습니다. 다음 시간에는 Props와 State를 더욱 효율적으로 활용하기 위해 리액트 컴포넌트의 **LifeCycle** 메소드를 살펴보도록 하겠습니다.



# 기초부터 배우는 React — Part 6

## LifeCycle에 대해서

CRA로 리액트 프로그램을 작성하셨다면 직접 HTML 파일을 작성할 일이 없었을 것입니다. JSX라는 문법을 통해서 HTML과 비슷한 형태로 작성을 하고, 그 파일을 리액트가 HTML로 변환시켜 렌더링을 시킨다는 사실을 알고 있습니다. 하지만 우리는 그 타이밍을 잘 알지 못해요.

만약 리액트로 프로그램을 만든 뒤, 그 사이트를 접속해 보면 처음에는 아무것도 없는 빈 화면이 반겨줍니다. 그 후 자바스크립트가 로딩이 완료되면서 우리가 작성한 JSX파일이 보이기 시작합니다. 이번 시간에는 그 타이밍을 알아보도록 하는 시간을 가져보겠습니다.



# 왜 내가 그걸 알아야 하는데 ?


이 부분을 학습하지 않고 리액트 개발을 하실 경우 총알 없는 총을 들고 전쟁에 나가는 것과 같은 효과를 보실 수 있을 것입니다. 몇 가지 예시를 알려드릴게요.

- *컴포넌트가 렌더링 된 직후* `***setState\***`*를 하고 싶다면 어디다가 그 로직을 작성해야 할까요?*
- *컴포넌트가 업데이트 된 직후 어떤 부분이 업데이트 되었는지 체크하고 싶다면 어디서 그 로직을 작성해야 할까요?*

저는 이런 부분들이 라이프사이클 없이는 해결할 수 없는 부분이라고 생각하고 있습니다. 실제로 제가 방금 보여드린 예시를 라이프사이클을 사용하지 않고 구현하려고 할 경우 엄청난 스트레스와 많은 개발자들이 권장하지 않는 방법을 사용해서 구현을 했을 것 같습니다. 이는 코드를 지저분하게 만들고, 다른 개발자와의 협업도 망치게 됩니다. 또는 여러분이 작성한 코드를 6개월 뒤에 유지보수 하려고 할 경우 `퇴사` 혹은 프로젝트 포기를 하게 만들 수도 있어요. 그러니, 이제 한번 배워보도록 합시다.

## 사진으로 보는 라이프사이클

![img](.\images\Cab1uJ2twrpfVvsXOONF0g.png)

이거 만든 분 존경합니다. http://projects.wojtekmaj.pl/react-lifecycle-methods-diagram/

사진을 보시면 3가지 부분이 있는데, 3가지 부분에 맞추어서 설명을 해 드리도록 할게요.



## Mounting: 리액트 코드 첫 렌더링의 시작

리액트코드가 HTML로 변환되어서 첫 렌더링이 되는 부분을 나타냅니다. 더 쉽게 말하자면, 리액트로 만들어진 웹 페이지에 접속했을 때 브라우저에 HTML이 나타나기까지의 과정이죠. 리액트 컴포넌트 클래스에서 어떤 일이 일어나는지 순서대로 알아보겠습니다.

1. constructor: 리액트 컴포넌트 클래스를 만들어도, 클래스의 기본 특성은 생성자가 먼저 실행된다는 점입니다. 당연히 첫번째로 생성자가 먼저 동작하죠.
2. render: 렌더링을 위해 작성해둔 JSX를 return해주는 메소드가 작동합니다. 아직 이 부분에서는 브라우저에 아무것도 없어요.
3. componentDidMount: 리액트 코드가 HTML로 변환되어 화면에 나타났습니다. 이제 우리는 document.getElementById를 통해서 선택자를 통해 DOM 요소를 가져올 수 있게 되었습니다. 여기에 DOM에 HTML코드가 업데이트 된 직후 수행해야 하는 작업을 수행하면 됩니다. componentDidMount는 딱 한번만 수행됩니다. (첫 렌더링 직후에만)

> ***⚠️\* \*주의:\*** 리액트에서 document.get~~~ 과 같은 실제 DOM의 요소를 가져오는 메소드를 수행하지 마세요. 이는 정말 해서는 안되는 행동입니다. 리액트에서는 DOM관리를 굉장히 보수적으로 수행합니다.실제 DOM관리를 위해서 ref라는 도구를 제공하니 ref를 사용하세요.



## Updateing: 리액트의 state나 props가 업데이트 되었을 때

컴포넌트가 가지고 있는 props나 state가 업데이트 되었을 때에 수행되는 부분입니다. props나 state가 업데이트 되는것을 리액트는 어떻게 감지할까요?

- setState메소드: state를 업데이트 하려면 반드시 setState를 수행해야 합니다. 리액트는 이 메소드 가 수행되었을 때 컴포넌트를 업데이트 합니다.
- props가 업데이트 되었을 때, 부모 컴포넌트에서 setState를 수행하여 현재 컴포넌트가 가지고 있는 props가 업데이트 되었을 때에도 리액트 컴포넌트는 업데이트 됩니다.

1. render: state나 props, 즉 보여주어야 하는 데이터가 업데이트 되었으면 다시 render를 수행해야 합니다.
2. componentDidUpdate: 컴포넌트가 업데이트 되었을 때에 수행되는 메소드로, 인자를 받습니다. prevProps와 prevState입니다. 현재 업데이트 된 props와 업데이트 되기 전 props, 업데이트 되기 전 state와 현재 업데이트 된 state를 비교해서 원하는 작업을 수행할 수 있습니다.



## Unmounting: 컴포넌트가 사라질 때

컴포넌트에 setInterval과 같은 작업을 진행했을 때에 이 부분은 컴포넌트가 단순히 사라진다고 해서 없어지지 않습니다. 메모리에 좀비처럼 상주할 가능성이 있는 변수들을 여기서 null 을 할당함으로 사용되지 않을 작업들을 해제해줄 수 있습니다. 이 때 사용되는 메소드는 다음과 같습니다.

- componentWillUnMount: 컴포넌트가 사라질 때에만 수행됩니다. 이 때 여러분이 setInterval과 같은 작업을 했을 경우에 여기서 해제시켜 주시면 됩니다.

라이프 사이클 코드 사용 예시

![img](.\images\RLAt_06MxqrjnFXOqCiMGQ.png)

https://codesandbox.io/embed/znr665pvy4?fontsize=14



### 끝

