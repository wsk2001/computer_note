# React와 Node.js로 만드는 고객 관리 시스템 개발 

출처: https://ndb796.tistory.com/211?category=1030599 [안경잡이개발자]



1강 Create React App으로 리액트 프로젝트 시작하기 [React와 Node.js로 만드는 고객 관리 시스템 개발 강좌]

React와 Node.js로 만드는 고객 관리 시스템 개발 강좌의 첫 번째 시간입니다. 이번 시간에는 **Create React App**을 이용해 리액트 프로젝트를 시작하는 방법에 대해서 소개하고자 합니다. CRA(Create React App)을 이용하면 별다른 환경 설정을 수행하지 않아도 매우 빠르고 간단하게 리액트 프로젝트를 생성할 수 있습니다. 이번 시간에는 빠르게 Create React App을 이용해 프로젝트를 구축해보도록 하겠습니다.



 기본적으로 리액트(React) 개발환경을 위해서는 node.js가 설치되어 있어야 합니다.



 **▶node.js 사이트:** https://nodejs.org/



 저는 최신 버전의 노드인 11.6.0 버전을 설치했습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E38E4B5C293C1517)



 기본적인 설정으로 설치를 진행하시면 됩니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E3E04B5C293C1617)



 설치 이후에는 **node -v** 명령어로 설치된 노드의 버전을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E3E44B5C293C1617)



 node.js를 설치하면 패키지 매니저 도구 NPM이 자동으로 설치됩니다. 이를 이용해서 create-react-app를 설치하시면 됩니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E4404B5C293C1617)



 이제 특정한 폴더로 이동해서 React 프로젝트를 생성해주겠습니다. 명령어는 **create-react-app {프로젝트 이름}**입니다.



 리액트 앱을 만든 이후에는 해당 프로젝트 폴더로 이동하여 **yarn start** 명령어로 실행할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/997E81375C293DF617)



 실행 이후에는 다음과 같이 개발 서버가 구동 중인 것을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E4954B5C293C1717)



 기본 포트 번호는 3000번이므로 브라우저에서 localhost:3000으로 접속하시면 다음과 같이 리액트 앱의 내용이 출력됩니다.



![img](https://t1.daumcdn.net/cfile/tistory/99E4994B5C293C1817)



2강 Visual Studio Code를 이용한 소스코드 작성 [React와 Node.js로 만드는 고객 관리 시스템 개발 강좌]

 리액트(React) 개발에 있어서 가장 효과적인 **통합 개발 환경(IDE)** 중 하나는 바로 비주얼 스튜디오 코드(Visual Studio Code)입니다. 일단 지난 시간에 만들었던 리액트 프로젝트를 실행(Start) 시켜주세요. 기본적으로 서버가 구동 중인 상태에서 소스코드를 수정하면 알아서, 수정된 내역에 맞게 다시 컴파일이 이루어지면서 실제 웹 서비스에 반영된다는 특징이 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99A3DF3F5C29416317)



 이제 이 상태에서 비주얼 스튜디오 코드를 설치해서 프로젝트 소스코드를 비주얼 스튜디오로 열어 보도록 하겠습니다. 혹시 비주얼 스튜디오 코드가 설치되어 있지 않으신 분은 다음의 사이트에서 설치를 진행하시면 됩니다.



 **▶ 비주얼 스튜디오 코드(Visual Studio Code) 사이트:** https://code.visualstudio.com/



 접속 이후에는 자신의 운영체제에 맞는 응용 프로그램으로 설치를 진행할 수 있습니다. 설치를 하실 때에는 기본 설정으로 설치를 진행해주셔도 별다른 어려움 없이 따라오실 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99F65E365C2940DA17)



 설치 이후에는 비주얼 스튜디오 코드를 실행해주세요. 이후에 **[File] - [Open Folder]** 자신의 리액트 프로젝트를 열어주세요.



![img](https://t1.daumcdn.net/cfile/tistory/99F6BA365C2940DA17)



 기본적으로 리액트(React)는 **App.js**라는 이름으로 메인 자바스크립트를 관리할 수 있습니다. 실제로 HTML 문서에서 <body> 태그에 해당하는 내용은 이 App.js가 채우게 됩니다. 따라서 한 번 이 App.js를 수정해 봅시다.



![img](https://t1.daumcdn.net/cfile/tistory/99F662365C2940DB17)



 저는 다음과 같이 내용을 대략 아무렇게나 바꾸어 보도록 하겠습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99F6B6365C2940DB17)



 이후에 저장(Ctrl + S)을 해주시면 자동으로 소스코드가 컴파일 되어 다음과 같이 웹 사이트에서 수정 내역을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/9908A1505C29455E1C)



3강 깃(Git)을 이용해 소스코드 관리하기 [React와 Node.js로 만드는 고객 관리 시스템 개발 강좌]

지난 시간까지 해서 리액트(React) 프로젝트를 생성하고 **비주얼 스튜디오 코드(VSC)**를 이용해서 개발 환경을 구축하는 시간을 가졌습니다. 이번 시간에는 깃(Git)을 이용해서 소스코드를 관리하는 방법에 대해서 알아보도록 하겠습니다. 우리는 향후 프로젝트의 효과적인 관리를 위해서 Git 저장소(Repository)를 생성한 뒤에 해당 저장소에 소스코드를 올리게 될 것입니다.

**
**

 일단 깃에 소스코드를 올리기 전에 전체 소스코드의 윤곽을 잡고 나서 올리는 것이 좋습니다.



 따라서 간단히 소스코드를 수정하며 프로젝트의 윤곽을 이해하는 시간을 가져보도록 하겠습니다.



**※ App.js ※**



 App.js는 실질적인 웹 사이트 화면에 대한 내용 출력을 담당하는 부분입니다.

**
**

![img](https://t1.daumcdn.net/cfile/tistory/9910C6505C294BE21E)



import React, { Component } from 'react';

import logo from './logo.svg';

import './App.css';



class App extends Component {

  render() {

​    return (

      <div className="gray-background">

​        <img src={logo} alt="logo" />

​        <h2>Let's develop management system!</h2>

​      </div>

​    );

  }

}



export default App;



**※ App.css ※**



 App.css 파일은 App.js와 연동되어 웹 사이트의 메인 부분에 해당하는 내용의 디자인(Design)을 담당합니다. 저는 간단히 기존의 내용을 지운 뒤에 .gray-background라는 이름의 클래스(Class)를 만들어 보았습니다. 클래스는 웹 문서 내 각 요소의 디자인을 구분하기 위한 문법입니다.



![img](https://t1.daumcdn.net/cfile/tistory/990823505C294BE31F)



.gray-background {

  background: darkgray;

}



**※ index.html ※**



 이후에 index.html을 수정합니다. 기존의 index.html 파일은 온갖 주석으로 가득 차 있었을 거예요. 그러한 주석을 모두 지워주시면 됩니다. 참고로 index.html 소스코드 내에는 <div id="root"></div>라는 부분이 있는데, 이 root 영역에 실제로 App.js의 내용이 출력됩니다.



<!DOCTYPE html>

<html lang="en">

  <head>

    <meta charset="utf-8" />

​    <link rel="shortcut icon" href="%PUBLIC_URL%/favicon.ico" />

​    <meta

​      name="viewport"

​      content="width=device-width, initial-scale=1, shrink-to-fit=no"

​    />

    <meta name="theme-color" content="#000000" />

​    <link rel="manifest" href="%PUBLIC_URL%/manifest.json" />

​    <title>React App</title>

  </head>

  <body>

​    <noscript>You need to enable JavaScript to run this app.</noscript>

    <div id="root"></div>

  </body>

</html>



**※ README.md ※**



 이어서 README.md 파일을 수정합니다. 이것은 깃 허브(Git Hub)에 올라가 출력될 내용입니다. 프로젝트의 소개, 설치 방법, 레퍼런스 등의 문구가 들어가는 공간이라고 이해하시면 돼요.



![img](https://t1.daumcdn.net/cfile/tistory/9907F8505C294BE31F)



**## Management System**



본 프로젝트는 React 강의 목적으로 만들어진 고객 관리 시스템(Management System)입니다.



 모든 소스코드를 저장해주신 뒤에 웹 사이트를 새로고침(F5) 해보시면 정상적으로 적용된 것을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/9907B6505C294BE31F)



**※ 깃으로 프로젝트 관리하기 ※**

**
**

 이제 깃 허브에 저장소를 생성하여 우리 프로젝트를 관리해 봅시다.



 **▶ 깃 허브(Git Hub) 주소:** https://github.com/



 깃 허브에 회원가입 및 로그인을 해주신 뒤에 **[Create Repository]**를 진행해주시면 됩니다. 말 그대로 소스코드가 올라갈 수 있는 하나의 저장 공간을 만들어 준다고 이해하시면 됩니다.



![img](https://t1.daumcdn.net/cfile/tistory/99087B505C294BE41F)



 위와 같이 프로젝트의 이름만 지어 주신 뒤에 생성 버튼을 눌러주시면 다음과 같이 저장소가 생성됩니다.



![img](https://t1.daumcdn.net/cfile/tistory/99103D505C294BE41D)



 이후에 저장소의 주소를 복사해서 가지고 있어주세요. 이제 **VSC**로 돌아와서 깃(Git) 탭으로 가보시면 우리가 수정한 소스코드의 내용이 모두 왼쪽 화면에 드러나는 것을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/9908A1505C294BE51F)



 먼저 우리가 수정한 소스코드의 내역을 우리 컴퓨터의 로컬 저장소에 반영하기 위해서는 커밋(Commit)을 해주시면 됩니다. 따라서 커밋을 하고자 하는 소스코드를 먼저 추가해주세요.



![img](https://t1.daumcdn.net/cfile/tistory/9908DB505C294BE51F)



 그리고 커밋을 진행하시면 됩니다. 저는 커밋 메시지로 'Create React Project'라고 지어주었어요.



![img](https://t1.daumcdn.net/cfile/tistory/993F1F475C294BE520)



 이후에 우리가 만든 깃 허브(Git Hub)의 저장소로 푸시(Push)하기 위해서 터미널을 열어줍시다.



![img](https://t1.daumcdn.net/cfile/tistory/993F71475C294BE620)



 이제 아까 복사했던 저장소의 주소를 원격지 주소로 설정한 뒤에 푸시(Push)를 진행해주시면, 소스코드가 깃 허브에 업로드 됩니다.



 **▶ 깃 허브 원격지 설정:** git remote add origin https://github.com/ndb796/React-Management-System.git

 **▶ 깃 허브 원격지로 푸시:** git push --set-upstream origin master



![img](https://t1.daumcdn.net/cfile/tistory/99BAEE3B5C294FF71E)



 푸시 이후에 깃 허브 저장소를 새로고침 해보시면 다음과 같이 소스코드가 모두 업로드 되어 있는 것을 확인할 수 있습니다.



![img](https://t1.daumcdn.net/cfile/tistory/993F79475C294BE620)





4강 고객 컴포넌트(Component) 만들기 [React와 Node.js로 만드는 고객 관리 시스템 개발 강좌]

이번 시간에는 **고객 컴포넌트(Customer Component)**를 만들어 보는 시간을 가져보도록 하겠습니다. 말 그대로 한 명의 고객에 대한 정보를 보여주기(View) 위한 기능입니다. 기본적으로 React는 HTML 웹 문서를 효과적으로 보여주기 위한 라이브러입니다. 그래서 컴포넌트란 말 그대로 웹 문서에서 어떠한 내용을 보여주기 위한 기본적인 단위라고 이해하시면 됩니다.



**※ Customer 컴포넌트 ※**



 Customer 컴포넌트를 만들어 주도록 하겠습니다. 지금부터 만들 컴포넌트(Component)들은 하나의 공간에 모아 놓으려고 해요. 그래서 src 폴더에서 **[New Folder]**를 눌러 새로운 폴더를 만들어 주도록 하겠습니다. 이름은 **components**에요.



![img](https://t1.daumcdn.net/cfile/tistory/992D9D4E5C2954C225) 



 이후에 다음과 같이 components 폴더에서 **[New File]**을 눌러서 **Customer.js** 컴포넌트를 생성해주도록 합시다.



![img](https://t1.daumcdn.net/cfile/tistory/992EBD4E5C2954C224)



 이후에 소스코드를 작성해주시면 됩니다.



 **▶ import:** 특정한 라이브러리를 불러오기 할 때 사용

 **▶ export:** 특정한 라이브러리를 내보내기 할 때 사용



 말 그대로 React Component 라이브러리를 사용하기 위해서 import 구문이 먼저 나와야 합니다. 그리고 나중에 다른 컴포넌트에서 지금 우리가 만든 컴포넌트를 사용하기 위해서는 export를 해주셔야 하는 거예요.



![img](https://t1.daumcdn.net/cfile/tistory/992EB94E5C2954C325)



import React from 'react';



class Customer extends React.Component {

​    render() {

​        return (

            <div>

​                <h2>홍길동</h2>

                <p>961222</p>

                <p>남자</p>

                <p>대학생</p>

​            </div>

​        )

​    }

}



export default Customer;



**※ App.js ※**



 이제 App.js에서 방금 우리가 만든 Customer 컴포넌트를 사용하도록 해볼게요.



import React, { Component } from 'react';

import Customer from './components/Customer'

import './App.css';



class App extends Component {

  render() {

​    return (

​      <Customer/>

​    );

  }

}



export default App;



 이제 웹 브라우저를 확인해 보시면 다음과 같이 한 명의 고객에 대한 정보가 출력되는 것을 확인할 수 있어요.



![img](https://t1.daumcdn.net/cfile/tistory/992E974E5C2954C326)



**※ Props를 이용해 구조화하기 ※**



 이제 Props를 이용해서 App.js에서 데이터(Data)를 가진 상태에서 고객에 대한 정보를 출력하도록 구조화하도록 하겠습니다.



**▶ App.js**



import React, { Component } from 'react';

import Customer from './components/Customer'

import './App.css';



const customer = {

  'name': '홍길동',

  'birthday': '961222',

  'gender': '남자',

  'job': '대학생'

}



class App extends Component {

  render() {

​    return (

​      <Customer

​        name={customer.name}

​        birthday={customer.birthday}

​        gender={customer.gender}

​        job={customer.job}

​      />

​    );

  }

}



export default App;



**▶ Customer.js**



import React from 'react';



class Customer extends React.Component {

​    render() {

​        return (

            <div>

​                <h2>{this.props.name}</h2>

                <p>{this.props.birthday}</p>

                <p>{this.props.gender}</p>

                <p>{this.props.job}</p>

​            </div>

​        )

​    }

}



export default Customer;



 실행 결과는 동일합니다. 기존에 객체 지향 프로그래밍을 해보신 분들이라면 현재의 소스코드가 그러한 객체 지향 프로그래밍의 구조를 정확히 잘 따르고 있다는 사실을 확인할 수 있을 거예요.



출처: https://ndb796.tistory.com/214?category=1030599 [안경잡이개발자]



작성중 ~~ 필요시추가 복사