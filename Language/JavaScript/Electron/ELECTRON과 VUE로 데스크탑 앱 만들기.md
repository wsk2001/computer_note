# **ELECTRON과 VUE로 데스크탑 앱 만들기**

출처: https://fkkmemi.github.io/electron/electron-00-intro/



- [0. 기초](https://fkkmemi.github.io/electron/electron-00-intro/)
- [1. 데이터 처리](https://fkkmemi.github.io/electron/electron-01-data/)
- [2. UI 이해](https://fkkmemi.github.io/electron/electron-02-ui/)
- [3. todo 앱 제작](https://fkkmemi.github.io/electron/electron-03-todo/)
- [5. vue-cli3 업그레이드](https://fkkmemi.github.io/electron/electron-05-vuecli3-update/)



## [0. 기초](https://fkkmemi.github.io/electron/electron-00-intro/)



# 설치 관련 업데이트(중요)

vue-cli3 업데이트로 인해 설치 방법이 변경되었으니 아래 링크를 참고하세요

[새로운 설치 방법](https://fkkmemi.github.io/electron/electron-05-vuecli3-update/)

위 링크로 설치하고 아래는 참고로 보시면 됩니다.

# 개요

javascript는 정말 편리한 언어입니다.

하지만 브라우저 안의 개구리였습니다.

브라우저 안에서는 보안 측면등의 이유로 많은 시스템자원 엑세스가 힘듭니다.

특히 파일, 위치정보를 브라우저가 가져가면 곤란하기 때문입니다.

node.js의 V8엔진은 javascript가 브라우저 밖으로 나가게 도와주었습니다.

script 주제에 파일은 기본이고 웹서버를 구동할 수 있게했습니다.

서버용도 외에도 사용이 가능하지만 데스크탑에서는 패키징이 불편해서 주로 콘솔 테스트용으로 사용했습니다.(*$ node go.js 이런식으로..*)

그리고 처음 배울 때 서버부터 만들기가 여건이 어렵죠

서버 로컬테스트 잘 해봐야 어딘가에 배포해야할 실제서버 작업 및 튜닝이 또 다른 복잡도가 따라오기 때문입니다.

서버 쪽을 해보고 싶으신 분은 [nemvv 강좌](https://fkkmemi.github.io/categories/#nemvv)를 참고하세요

서버에 비해 데스크탑앱은 단순하고 직관적입니다.

node로 데스크탑앱을 만들기위해 nw.js라는 것을 써본적도 있습니다.

자세히는 설명이 어렵지만 제 경험으로는 일렉트론이 4배이상 좋습니다.

이제 V8의 자유도로 브라우저와 서버를 넘어 일렉트론뷰로 우아한 데스크탑 앱을 만들어 보겠습니다.



# 시작하게된 이유

저는 잡개발자입니다.

펌웨어, 데스크탑앱, 웹서버, 디비, 모바일등 대부분 혼자 만듭니다.

닥치는 대로 필요한 것을 취하기 때문에..

어짜피 새로 만드는것 대부분 신기술을 취합니다.

어찌보면 풀스택 개발자라고 불리울 수도 있지만.. 반대로는 뭐하나 제대로 내세울 것이 없습니다.

잡스러운 기술 중 그나마 진득하게 잘하고 오래했던 것은 역시나 윈도우 프로그램인데요..

일반적인 VS로 만들지 않고, c++builder/delphi라는 기묘한 물건으로 윈도우 프로그램은 뚝딱 만들었죠..

회사 리포지토리에 등록된 앱만 300개가 넘을 정도로 많이 만들었습니다.(*지그앱, 파일분석툴, 관리툴등..*)

그 중 디비 연동하면서 인스톨 패키지 까지 입혀서 배포 하는 서비스형 앱들도 서너가지 있습니다.(*5년이 흐른 지금도 잘들 쓰고 있다는..*)

갑자기 뜬금 없는 윈도우 프로그램 개발 자랑 같은 얘기를 하는 이유는..

윈도우 프로그램을 잘 만드는데도 불구하고 굳이 일렉트론으로 데스크탑을 만들어야 되는 이유를 설명하기 위해서입니다.

# 일렉트론으로 전향한 이유

1. 크로스플랫폼: 윈도우, 맥, 리눅스(크롬 돌아가는 os 전부) 지원
   윈도우 프로그램(.exe)은 맥에서 돌아가지 않습니다.
   이부분이 제일 주효했습니다.
   뱅킹 외에 윈도우는 사용도 안하는데(*뱅킹마저 거의 모바일*) 윈도우 프로그램 개발자라니…
   이젠 윈도우 프로그램 개발자 -> 데스크탑 앱 개발자가 되겠네요
2. 언어: javascript(vue.js)
   C/C++, java등의 전통 언어는 여전히 우수한 언어이지만 대부분의 용도에서는 javascript에 비해 생산성이 떨어집니다.(*물론 코어나 정밀 모듈쪽은 당연히 c/c++같은 네이티브 언어가 좋다고 봅니다. mongoDB도 c기반입니다*)
   javascript는 처음 접하는 사람도 익히기 쉽고 웹개발하시던 분은 보너스죠
   특히나 비동기 방식이라 C/C++때 늘 고민했던 블러킹 되서 프리징 걸리지 않게 하기 위한 쓰레드 처리가 거의 필요가 없습니다.
   사실 javascript자체가 익히기 쉽다기보다는 진화된 javascript프레임웤인 vue.js로 작업할 것이기 때문이기 때문입니다.
   vue.js를 완벽하게 다루는 것은 당연히 너무 어렵지만(react, angular도 마찬가지) 제 철학은 딱 필요한 만큼 쉽게 접근할 부분만 쓰기 때문에 괜찮습니다.
3. 라이브러리: npm의 수십만개의 라이브러리가 공짜!(*물론 상용 배포시에는 전 공짜가 아닙니다 주의..*)
   예를 들면 윈도우 프로그램할 때 기초적인 232통신을 하려해도 상용 컴포넌트를 돈주고 사야했습니다.(*물론 공짜도 있지만..*)
   단순히 npm i serialport 하면 그냥 바로 사용됩니다. 그 밖에도 chartjs나 axios등은 정말 훌륭합니다.
4. 디자인: 제목에 적혀 있는 것 처럼 우아한 것이 중요합니다.
   제 개발 스타일은 무조건 표현이 우선입니다.(*펌웨어를 하더라도 콘솔보다는 액정에 찍어서 디버그..*) 클라이언트나 협력업체를 대할 때도 산출물을 보고 얘기하는 것이 몇장의 도큐보다 나았습니다.
   내실이 알차게 작성된 코드도 중요하지만 끌리는 디자인이 여러모로(*영업적으로도..*) 더 도움이 된다고 생각했습니다.
   그래서 자칭 **비쥬얼디벨로퍼** 입니다
   예전에 윈도우 프로그램할 때는 상용콤포넌트(tms)등을 이용해서 버튼등을 우아하게 했습니다..
   관점의 차이는 있겠지만 더 우아하게 만들 수 있습니다.
   이유는 지난 강좌 nemvv에서 사용했던 <span style="color:blue">vuetify</span>라는 프레임웤을 그대로 사용할 수 있기 때문입니다.
   당연히 데스크탑앱이지만 웹이고 웹이지만 데스크탑앱이고 이도저도 아니라 그런지 아주 오묘하고 아름답습니다.

# 목표

저의 경우 생산 공정용(지그, 디비 저장), 각종 기기 파일 분석, 개인용 장난감등으로 사용중입니다.

특정 파일을 읽고 로컬디비(nedb:mongodb의 작은 버전)를 이용해 소트가 가능한 게시판을 만들어 보겠습니다.

디비는 뭔가 복잡해보여서 기피하시는 분들이 많이 보이는데요 데이터를 처리하는 앱이라면 디비는 꼭 하시는 것이 여러모로 편리합니다.(*두뇌건강에도 좋아보입니다..*)

모바일 앱같은 것도 대부분 작은 로컬디비(sqlite같은)를 사용합니다.

저 같은 경우와 비슷한 분들이 도움이 되었으면 좋겠습니다.

# 준비물

node.js 만 홈페이지 가서 다운로드 받아서 설치하면 됩니다. 1분도 안걸립니다.

> 부가 준비물로는 yarn 정도를 깔아두면 좋습니다.
> npm을 좀 더 편리하게 써주게 합니다.
> npm보다 많이 쓰는 추새입니다.

# 설치

그냥 일렉트론이 아닌 vuetify화된 일렉트론으로 설치합니다.

https://vuetifyjs.com/ko/getting-started/quick-start

설치 방법은 위 링크에 자세하게 나와있지만 처음 만든다고 생각하고 한땀 한땀 구현하며 작성하겠습니다.

위 링크를 타고 가면 어떤 형태로 구현할 것인지 템플릿들이 나온 표가 있습니다.

webpack도 있고 nuxt도 있고 다양한데 그중 electron이 있네요

깃헙 고양이 버튼을 클릭하면 자세한 사용법이 나옵니다.

https://github.com/vuetifyjs/electron

프로젝트 명만 변경하고 공식페이지의 내용 대로 해봅니다.

## 공식페이지의 설치 방법

```
# Install vue-cli and scaffold boilerplate
npm install -g vue-cli
vue init vuetifyjs/electron elecapp

# Install dependencies and run your app
cd elecapp
yarn # or npm install
yarn run dev # or npm run dev
```

## 실제 해본 결과

`npm install -g vue-cli` 는 vue-cli가 이미 설치되어 있어서 패스했습니다.

```
$ vue init vuetifyjs/electron elecapp # 엔터만 계속 쳤습니다 저는
? Application Name elecapp
? Project description An electron-vue project
? Select which Vue plugins to install axios, vue-electron, vue-router, vuex
? Use linting with ESLint? Yes
? Which eslint config would you like to use? Standard
? Setup unit testing with Karma + Mocha? Yes
? Setup end-to-end testing with Spectron + Mocha? Yes
? What build tool would you like to use? builder
? author fkkmemi <fkkmemi@gmail.com>

   vue-cli · Generated "elecapp".

---

All set. Welcome to your new electron-vue project!

Make sure to check out the documentation for this boilerplate at
https://simulatedgreg.gitbooks.io/electron-vue/content/.

Next Steps: # 다음 스텝까지 친절하게 나오네요

  $ cd elecapp
  $ yarn (or `npm install`)
  $ yarn run dev (or `npm run dev`)
  
$ yarn
# 어쩌고 저쩌고 깔림..
Done in 75.04s. # 75초가 걸렸네요

$ yarn run dev
```

![alt start](https://fkkmemi.github.io/images/electron/2018-06-22%2013-18-52%20elecapp.png)

자 보이시나요 정말 앱이 둥 하고 떠있을 것입니다.

elecapp 이라는 프로그램을 만든 것입니다.

기본은 긴 서론이 허무하게? 끝나버렸습니다.

> yarn이 설치하기 귀찮으면 위의 과정을 아래와 같이 바꾸시면 됩니다.(*물론 yarn이 훨씬 빠릅니다 병렬로 내려받아서*)
> yarn -> npm i
> yarn run dev -> npm run dev

그런데 옆에 무슨 개발자 모드 같은 콘솔창이 있네요..

개발을 위한 용도라는 거죠 dev(development) 크롬의 개발자 창과 같습니다.

앱 안에 크롬부라우저가 떠있다고 생각하면 됩니다.

비즈니스 로직을 넣을 때 뭔가 궁금할때 console.log()를 이용하면 옆에 표시 됩니다.

그 밖에도 스타일이나 이벤트등도 전부 보이며.. 직접 코드를 수정하면 화면에도 반영됩니다.

개발할 때 너무 고마운 창이죠..

하지만 실사용자는 안보이게 해야겠죠..

이제 실사용자를 위한 앱을 만들어 보겠습니다.

# 배포

배포(deploy) 관련 처리를 먼저 알려드립니다.

배포는 일반적으로는 매뉴얼 이나 레퍼런스 사이트등에서 맨 마지막에 언급되는 부분이지만..

이 일렉트론 프로젝트가 실제 사용자가 쓸 수 있을까? 진짜 되는거야? 라는 고민이 먼저될 것 같아서입니다.

윈도우 프로그램을 제작할 때 제일 신경 썼던 부분이 배포입니다.

실제 사용자는 yarn run dev 같은 걸 하는 게 아닌 exe파일이나 dmg등으로 빠른 접근을 원합니다..

실행파일 하나로 할 수도 있고 이런저런 dll덩어리를 묶은 패키지 매니져로 만든 적도 있습니다.

일렉트론은 맥, 윈도우32~64비트, 리눅스 로 배포가 가능합니다.

vuetifyjs&electron 세트메뉴라 배포용 툴(electron-builder)까지 다 완비되어 있으니 걱정할 필요가 없습니다.

```
$ yarn build
```

![alt build](https://fkkmemi.github.io/images/electron/2018-06-22%2013-31-14%20elecapp.png)

기나긴 과정(약 1~2분?)이 빌드 과정이 끝나면 build 디렉토리에 셋업본이 나옵니다.

셋업본이라는 말이 좀 어색하긴 한데 install shield 이런거 보신 적 있을 겁니다. 인스톨러인데요..

저 같은 경우 윈도우 프로그램을 제작할때 dll이나 리소스등을 exe파일에 죄다 집어넣은 단일 파일 빌드를 즐겨 사용했는데요

그러면 실행파일 하나만 받아도 바로 실행이 가능했습니다.

하지만 사실 몰래 레지스트리에 뭐도 잔뜩 써놓고 local/appdata 같이 엑세스 가능한 곳에 파일도 왕창 저작한 앱도 만들어 봤습니다.(*그야말로 똥덩어리들…*)

그래서 인스톨보다는 언인스톨이 더 중요하기 때문에 스크립트등을 작성해서 인스톨러를 배포했습니다.

나름 인스톨 패키징하는 것도 정성과 시간이 필요한 것인데…

그 정성과 시간이 없이 인스톨러 형태로 빌드가 되었다는 것입니다.

저는 맥에서 빌드 했기 때문에 .dmg 라는 파일이 나왔습니다.

윈도우에서 빌드하면 .exe가 나오는데.. 그렇다고 귀찮게 윈도우 켜서 빌드할 수는 없겠죠?

그래서 빌드 방법을 추가해보도록 하겠습니다.

우선 빌드를 위한 것을 간단하게 알아보겠습니다.

**package.json**

```
{
  "name": "elecapp",
  "version": "0.0.0",
  "author": "fkkmemi <fkkmemi@gmail.com>",
  "description": "An electron-vue project",
  "license": null,
  "main": "./dist/electron/main.js",
  "scripts": {
    "build": "node .electron-vue/build.js && electron-builder",
    "build:dir": "node .electron-vue/build.js && electron-builder --dir",
    "build:clean": "cross-env BUILD_TARGET=clean node .electron-vue/build.js",
    "build:web": "cross-env BUILD_TARGET=web node .electron-vue/build.js",
    "dev": "node .electron-vue/dev-runner.js",
    "watch": "cross-env BUILD_TARGET=web node .electron-vue/dev-runner.js",
    "e2e": "npm run pack && mocha test/e2e",
    "lint": "eslint --ext .js,.vue -f ./node_modules/eslint-friendly-formatter src test",
    "lint:fix": "eslint --ext .js,.vue -f ./node_modules/eslint-friendly-formatter --fix src test",
    "pack": "npm run pack:main && npm run pack:renderer",
    "pack:main": "cross-env NODE_ENV=production webpack --progress --colors --config .electron-vue/webpack.main.config.js",
    "pack:renderer": "cross-env NODE_ENV=production webpack --progress --colors --config .electron-vue/webpack.renderer.config.js",
    "test": "npm run unit && npm run e2e",
    "unit": "karma start test/unit/karma.conf.js",
    "postinstall": "npm run lint:fix"
  },
  // ...
}
```

빌드, 런등 모든 행위는 package.json이라는 파일의 정의에 의해서 일어납니다.

yarn run dev 는 곧 위의 정의 대로 `node .electron-vue/dev-runner.js` 라는 명령을 줄여준 것이고

yarn run build 는 `node .electron-vue/build.js && electron-builder` 라는 명령을 줄인 것이죠

그래서 왠지 윈도우로 빌드 하라는 것은 뒤에 옵션을 넣으면 될 것 같은 느낌이 들죠

아래 링크에 자세히 나와있지만 뭐가 뭔지 잘 모르겠습니다. 찾은 것은 –w를 넣으면 될 것 같은 판단 뿐…

https://www.electron.build/configuration/target#targetconfiguration

```
  "scripts": {
    "build": "node .electron-vue/build.js && electron-builder",
    "build:win": "node .electron-vue/build.js && electron-builder --w", // 추가
    "build:dir": "node .electron-vue/build.js && electron-builder --dir",
    ...
```

build:win이라는 것을 하나 만들어봤습니다. 달랑 –w 추가한 것이죠

```
$ yarn build:win
```

![alt build:win](https://fkkmemi.github.io/images/electron/2018-06-22%2014-00-52%20elecapp.png)

> yarn run build:win 이라고 쳐도 됩니다. build할 때는 run을 생략해도 되도록 정의를 해놨습니다.

# 실행

정말 잘 빌드 된 것인지 확인해봅니다.

## 맥용

### 더블클릭: elecapp-0.0.0.dmg

![alt build:mac](https://fkkmemi.github.io/images/electron/2018-06-22%2014-07-37%20build.png)

### 드래그 앤 드랍

![alt build:drag](https://fkkmemi.github.io/images/electron/2018-06-22%2014-08-13%20elecapp%200.0.0.png)

### 설치된 파일 더블 클릭

런치 패드에서는 처음에는 안됩니다 사인이 안된 앱이라.. 파인더에서 직접 클릭하고 나서 됩니다.
120메가나 되네요

![alt build:app](https://fkkmemi.github.io/images/electron/2018-06-22%2014-08-49%20app.png)

### 실행 결과

잘 되는 것을 확인 했습니다.

![alt build:drag](https://fkkmemi.github.io/images/electron/2018-06-22%2014-12-34%20elecapp.png)

### 제거

맥은 그냥 휴지통에 드래그엔 드랍하면 지워집니다.

## 윈도우용

저는 맥에서 윈도우를 간혹 쓸 때 페러럴즈라는 가상화 앱을 이용합니다.

페러럴즈로 윈도우에서 시험해보겠습니다.

### 더블클릭: elecapp Setup 0.0.0.exe

![alt win click](https://fkkmemi.github.io/images/electron/2018-06-22%2014-27-26%20Windows.png)

### 설치 진행

프로그레스바가 올라가며 설치가 진행됩니다.

![alt win install](https://fkkmemi.github.io/images/electron/2018-06-22%2014-28-00%20Windows.png)

### 설치 끝나자 마자 바로 실행

설치가 끝나고 바로 실행되버리네요.. 잘 되는 것을 확인 했습니다.

![alt win run](https://fkkmemi.github.io/images/electron/2018-06-22%2014-28-57%20Windows.png)

### 제거

제어판의 프로그램 추가/삭제에서 잘 지워지는 것 확인되었습니다.

![alt win remove](https://fkkmemi.github.io/images/electron/2018-06-22%2014-33-01%20Windows.png)

자 이제 정말 되는 것을 확인했습니다.

이제 해야할 일은 기본 헬로월드 같은 화면이 아닌 내가 원하는 화면을 넣는 것입니다.

안의 내용을 변형/추가 하면 되는 것이죠

# 구조 간단히 알아보기

우선 메뉴는 어디있고 메뉴를 누를 때의 화면은 어디있는지 알아야 변형이 가능하니 딱 필요한 정도로만 알아보겠습니다.

![alt struct](https://fkkmemi.github.io/images/electron/2018-06-22%2014-56-52%20elecapp.png)

위 그림중 src/renderer 요 폴더만 주의 깊게 보시면 됩니다.

- App.vue: 이곳에 메뉴등 전체 틀(레이아웃)이 있습니다.
- components/* : 이곳에 각 메뉴를 눌렀을때의 화면입니다.
- router/index.js: 이곳에 등록되어 있는 파일만 작동합니다.

이정도만 알고 변형을 하러 가봅시다.

# 간단한 프로그램 체험

## 변형

이제 안의 내용을 바꿔 보도록 하겠습니다.

글자 하나 수정하는데 2,3분이나 걸리면 정말 개발할 맛 안나겠죠? 그래서 dev 모드가 있는 것입니다.

dev 모드에서는 수정후 저장(cmd+s or ctrl+s) 만 해주면 바로 반영된 결과가 나옵니다.

```
$ yarn run dev
```

바로 앱이 뜹니다.

Welcome 과 Inspire라는 메뉴가 있네요..

글자를 수정해 보겠습니다.

**App.vue**

```
// 파일 하단의 내용 수정
  export default {
    name: 'elecapp',
    data: () => ({
      clipped: false,
      drawer: true,
      fixed: false,
      items: [
        { icon: 'cloud', title: '메뉴바꿈!', to: '/' }, // 이부분 수정
        { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' }
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'Vuetify.js'
    })
  }
```

수정후 저장을 하면 바로 적용이 됩니다.(*참 쉽죠?*)

![alt change](https://fkkmemi.github.io/images/electron/2018-06-22%2015-07-27%20elecapp.png)

> icon은 https://material.io/tools/icons/?icon=attachment&style=baseline 기서 확인 가능합니다.
> 사용법은 https://vuetifyjs.com/ko/components/icons 서 확인하면 됩니다.

이제 뭔가를 수정하면 잘 수정이 되는 것을 확인했습니다.

남이 만들어 놓은 뼈다구 말고 실제를 만들어 볼 차례입니다.

## 추가

위에 구조도에 언급한 것처럼 3단계가 필요합니다.

1. 표시될 파일 만들기
2. 라우터에 등록
3. 메뉴에 추가

### 표시될 파일 만들기

연습장으로 쓸 빈 깡통을 만들어 보겠습니다.

**src/renderer/components/test.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm4>
      <v-chip color="red" text-color="white">
        {{msg1}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange">
        {{msg2}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange">
        {{msg3}}
      </v-chip>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: 'test',
    data: () => ({
      msg1: 'vuetify 해보자',
      msg2: 'vue 해보자',
      msg3: 'electron 해보자'
    }),
    methods: {
    }
  }
</script>

<style scoped>
</style>
```

이제부터는 vuetify와 vue를 접해야 할 상황입니다.

구체적인 것은 아래 링크를 확인해보시기 바랍니다.

vuetify 그리드 시스템: https://vuetifyjs.com/ko/layout/grid

vuetify chip: https://vuetifyjs.com/ko/components/chips

vue 간단 렌더링 관련: https://kr.vuejs.org/v2/guide/index.html

> vuetify 링크를 보고 혹시 눈치 채셨나요? 콤포넌트와 색상 배치 모두 위 링크 참고해서 카피엔 페이스트 해가면서 하면 쉽습니다.
> 문제는 vue.js 자체의 스터디가 조금 더 중요합니다.

**src/renderer/router/index.js**

```
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'welcome-view',
      component: require('@/components/WelcomeView').default
    },
    {
      path: '/inspire',
      name: 'inspire',
      component: require('@/components/InspireView').default
    },
    { // 추가
      path: '/test',
      name: 'test',
      component: require('@/components/test').default
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
```

**src/renderer/App.js**

```
// 하단 스크립트 부분에 추가
export default {
    name: 'elecapp',
    data: () => ({
      clipped: false,
      drawer: true,
      fixed: false,
      items: [
        { icon: 'cloud', title: '메뉴바꿈!', to: '/' },
        { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' }, 
        { icon: 'title', title: 'test', to: '/test' } // 추가
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'Vuetify.js'
    })
  }
```

### 추가 실행 결과

![alt change](https://fkkmemi.github.io/images/electron/2018-06-22%2015-41-18%20elecapp.png)

이런 식으로 페이지를 추가하고 원하는 기능을 넣으면 되겠습니다.

하지만 이런 건 웹에서도 다 되던 것들이죠.. 아직까진 웹 이상의 의미가 있어보이지 않습니다.

이제 시스템 자원에 엑세스를 해보도록 하겠습니다.

## 데스크탑 앱으로서 의미 있는 기능 시험

파일을 로드해서 읽어보도록 하겠습니다.

파일을 읽으려면 고정된 패스 보다는 아무래도 다이얼로그로 선택하게 하는 것이 편하겠죠?

구현 순서는

1. 다이얼로그 띄울 버튼 추가
2. 버튼에 반응할 이벤트 추가
3. 선택후 가져온 패스대로 읽는 기능 추가
4. 읽고나서 표현되는 ui 추가

### button 추가

코드를 작성한 후 저장을 눌러보도록 합시다.

**src/renderer/components/test.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm4>
      <v-chip color="red" text-color="white">
        {{msg1}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange" text-color="white">
        {{msg2}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange" text-color="white">
        {{msg3}}
      </v-chip>
    </v-flex>
    <v-flex xs12>
      <v-card>
        <v-card-title>
          {{fileContent}}
        </v-card-title>
        <v-card-actions>
          <v-btn color="primary" @click="dialogOpen">
            <v-icon left>save</v-icon>
            파일 선택
          </v-btn>
        </v-card-actions>
      </v-card>

    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: 'test',
    data: () => ({
      msg1: 'vuetify 해보자',
      msg2: 'vue 해보자',
      msg3: 'electron 해보자',
      fileContent: '파일을 보고 싶네요'
    }),
    methods: {
      dialogOpen () {
        this.fileContent = '봤니?'
      }
    }
  }
</script>

<style scoped>
</style>
```

![alt change](https://fkkmemi.github.io/images/electron/2018-06-22%2015-54-30%20elecapp.png)

우아한 데스크탑의 의미중 큰 역활을 차지하는 버튼입니다.

자세히 버튼을 누르고 보면 얼마나 vuetify button이 디테일하게 스타일링 되었는지 알 수 있습니다.

버튼의 좌측 눌렀을 때와 우측 눌렀을 때의 트랜지션 효과가 다릅니다.

v-card를 사용했는데 일반적으로 panel 같은 개념이라고 보시면 됩니다. 지저분하게 나열되지 않게 잘 정돈해주는 용도라고 보면 됩니다.

@click=”dialogOpen” 으로 methods에 정의된 핸들러로 갑니다.(*이벤트 처리 쉽죠~*)

### 실제 파일 읽기

electron에서 제공하는 다이얼로그와 node.js에 기본 포함되어 있는 fs 모듈로 파일을 읽어보겠습니다.

**파일 내용**
ab,cd,ef,g

**src/renderer/components/test.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm4>
      <v-chip color="red" text-color="white">
        {{msg1}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange" text-color="white">
        {{msg2}}
      </v-chip>
    </v-flex>
    <v-flex xs12 sm4>
      <v-chip color="orange" text-color="white">
        {{msg3}}
      </v-chip>
    </v-flex>
    <v-flex xs12>
      <v-card>
        <v-card-title>
          {{fileContent}}
        </v-card-title>
        <v-card-text>
          <v-chip color="info" v-for="c in chips">
            {{c}}
            <v-icon right>school</v-icon>
          </v-chip>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="dialogOpen">
            <v-icon left>save</v-icon>
            파일 선택
          </v-btn>
        </v-card-actions>
      </v-card>

    </v-flex>
    <v-snackbar
        :timeout="snackbar.timeOut"
        top
        v-model="snackbar.act"
        :color="snackbar.color"
    >
      {{ snackbar.text }}
      <v-spacer></v-spacer>
      <v-btn flat color="white" @click.native="snackbar.act = false">닫기</v-btn>
    </v-snackbar>
  </v-layout>
</template>

<script>
  import * as fs from 'fs'

  export default {
    name: 'test',
    data: () => ({
      msg1: 'vuetify 해보자',
      msg2: 'vue 해보자',
      msg3: 'electron 해보자',
      fileContent: '파일을 보고 싶네요',
      snackbar: {
        act: false,
        text: '',
        color: 'success',
        timeOut: 5000
      },
      chips: []
    }),
    methods: {
      pop (msg, cl, t) {
        this.snackbar.act = true
        this.snackbar.text = msg
        this.snackbar.color = cl
        this.snackbar.timeOut = t
      },
      dialogOpen () {
        this.$electron.remote.dialog.showOpenDialog((r) => {
          if (!r) return this.pop('파일을 선택하세요', 'error', 5000)
          if (!r.length) return this.onError('파일을 선택하세요', 'error', 5000)

          fs.readFile(r[0], (err, fd) => {
            if (err) return this.pop(err.message, 'error', 5000)
            this.fileContent = fd.toString()
            this.chips = this.fileContent.split(',')
            this.pop('파일 로드가 성공적이네요!!', 'success', 3000)
          })
        })
      }
    }
  }
</script>

<style scoped>
</style>
```

#### 실행 결과

![alt change](https://fkkmemi.github.io/images/electron/2018-06-22%2016-16-03%20elecapp.png)

#### html 설명

- v-for 로 배열을 엘리먼트로 한꺼번에 렌더링이 됩니다. 위에서 한번 써먹은 v-chip에 표시 해봤습니다.
- v-snackbar 는 모바일의 토스트 같은 것입니다.
  :timeout, :color등은 스크립트쪽에 선언된 변수와 바인드 되어있다는 것입니다. 값이 바뀌면 화면도 바뀌는 신통방통한 것입니다.

#### script 설명

- pop 이라는 메쏘드를 추가해서 스낵바를 다용도로 쓰게 만들었습니다.
- this.$electron.remote.dialog.showOpenDialog는 콜백으로 경로(들)을 불러옵니다.
  그 얘기는 파일 여러개도 된다는 것이죠.
  선택을 안했을 경우 예외처리가 되어 있고 선택 했을 경우 fs 모듈로 파일을 읽습니다.
- fs로 파일을 읽으면 문자열형태가 아닌 node의 buffer 시스템(binary)으로 읽습니다.
  그러므로 toString()으로 문자열로 변경해서 표현했습니다.
- javascript의 편리한 split함수로 chips 어레이에 [‘ab’, ‘cd’, ‘ef’, ‘g’]로 정리해서 넣습니다.

# 결론

이것으로 일렉트론뷰로 적은 시간을 들여 우아한 데스크탑앱을 만들 수 있다는 가능성을 보여주었다고 생각합니다.

다음에는 외부 라이브러리(chartjs, nedb 등)를 이용해서 더 많은 것을 할 수 있다는 가능성을 다시 보여드리겠습니다.

# 커뮤니티 공개

클리앙 팁과강좌

https://www.clien.net/service/board/lecture/12267795



## [1. 데이터 처리](https://fkkmemi.github.io/electron/electron-01-data/)

이번에는 데스크탑 앱으로 데이터를 다뤄보겠습니다.

예전 윈도우7 이전시절 윈도우 프로그램 만들 때는 아무 걱정 없이 아무데나 파일을 쓸 수 있었습니다.(*7이상 이래봤자 UAC 확인 버튼 한번 누르면 마찬가지긴 합니다..*)

당연히 그런 보안으로는 exe 파일 한번 잘못 눌렀다가 랜섬 걸리기 딱 좋죠

저시절 보안으로 랜섬웨어를 만든다면 아마도

```
const char Keys[] = "this is my secret key 어쩌구 저꺼구 길고 긴 키";

recursiveFiles("C:\\"); // 시작할때

String recursiveFiles(String &p) {
    // 재귀 호출로 하위 디렉토리 다 돌면서 파일만 색출
    changeFile(any_file, &Keys);
}

bool encodeFile(String& o, unsigned char* ks) {
    if (systemPath(o)) return; // "C:\windows" 이런 곳은 걸르는 함수 만듬..
    fileRead(o, rs) { // bytes 단위로 연다.
        fileDelete(o); // 데이터 열람했으니 과감히 지움
        for (int i = 0; i < rs.length; i++) rs[i] += ks[i % sizeof(ks)]; // 데이터 꼬아놓음

        String cpath = chageFileName(o); // 지랄 맞은 파일명으로 변경
        fileWrite(cpath, rs); // 지랄 맞은 파일명과 꼬인 데이터로 파일 생성
    }
}

// bool decodeFile( // ... 푸는 것도 만듬..
```

대충 이렇게 만들 것 같습니다.

> 간만에 느낌대로 c로 휘갈겨 본겁니다.. 파일콘트롤은 매우 위험하다는 예시

윈도우 프로그램 처음 배울 때는 바탕화면에 한가득 이상한 파일을 채운다거나…

> 그 밖에도 재미로.. 창을 닫으면 또생기는 짜증나는 장난감들을 만들어서 주위 친구들에게 선물로 주기도 했었죠..

파일콘트롤은 매우 위험하기 때문에 현대의 앱들은 개념을 명확히 두고 인증되지 않은 앱은 엄청난 경고 패널티가 주어집니다(*물론 예전에도 개념 제시는 했습니다. 강제는 안했지만*)

구동하는 앱의 위치(program files\anyApp 같은..)와 해당 앱이 파일을 쓸 수 있는 위치(%APPDATA%\anyApp)가 정해져 있습니다.

이제 해당 앱이 파일을 쓸 수 있는 위치에 읽고 쓰고 해보겠습니다.

 On This Page[디버그 기초](https://fkkmemi.github.io/electron/electron-01-data/#디버그-기초)[파일 컨트롤](https://fkkmemi.github.io/electron/electron-01-data/#파일-컨트롤)[사용자 데이터 보관 위치](https://fkkmemi.github.io/electron/electron-01-data/#사용자-데이터-보관-위치)[파일 써보기](https://fkkmemi.github.io/electron/electron-01-data/#파일-써보기)[파일 쓰고 읽기 페이지로 최종 테스트](https://fkkmemi.github.io/electron/electron-01-data/#파일-쓰고-읽기-페이지로-최종-테스트)[디비 컨트롤](https://fkkmemi.github.io/electron/electron-01-data/#디비-컨트롤)[설치](https://fkkmemi.github.io/electron/electron-01-data/#설치)[전역 컨트롤 등록](https://fkkmemi.github.io/electron/electron-01-data/#전역-컨트롤-등록)[디비페이지 만들기](https://fkkmemi.github.io/electron/electron-01-data/#디비페이지-만들기)[html](https://fkkmemi.github.io/electron/electron-01-data/#html)[script](https://fkkmemi.github.io/electron/electron-01-data/#script)[결과 확인](https://fkkmemi.github.io/electron/electron-01-data/#결과-확인)[소스 저장소](https://fkkmemi.github.io/electron/electron-01-data/#소스-저장소)[결론](https://fkkmemi.github.io/electron/electron-01-data/#결론)[커뮤니티 공개](https://fkkmemi.github.io/electron/electron-01-data/#커뮤니티-공개)

# 디버그 기초

지난번 프로젝트에서 다시 `yarn run dev` 를 입력해서 앱을 띄어봅니다.

우측에 콘솔창이 있습니다.

이제 콘솔창을 보면서 개발해보도록 하겠습니다.

# 파일 컨트롤

앱 개발자는 사용자 데이터를 보관 할 곳을 알아야합니다.

## 사용자 데이터 보관 위치

https://electronjs.org/docs/api/app

일렉트론의 app 요소에서 찾을 수 있었습니다.

> 참고로 전 구글링을 별로 좋아하지 않습니다.
> 우선은 각 제작자가 만든 도큐를 봅니다. 스택오버플로우등에서 운 좋게 원하는 것을 찾을 수도 있지만..
> 비정규스러운 코드나 당장을 피하기 위한 코드들이 더 많아서 혼란이 가중될 수 있습니다.

바로 app.getPath(name) 인데요

지난번 작성한 test.vue 파일로 어떤 결과가 나오는지 눈으로 확인 해봅시다.

**src/renderer/components/test.vue**

```
  import * as fs from 'fs'

  export default {
    name: 'test',
    data: () => ({
    }),
    mounted () {
      console.log(this.$electron.remote.app.getPath('appData'))
      // 결과: /Users/xxx/Library/Application Support
    },
    methods: {}
  }
```

> mounted 라는 함수는 해당 페이지가 로드된 후 실행 됩니다.
> test.vue를 열어두고 뭔가 수정하고 콘솔로 확인하기가 좋습니다.

우측 콘솔 확인 해보면맥에서는 사용자 계정 폴더에 Library/Application Support 라는 곳을 쓰라는 것인가보네요

해당 디렉토리로 가보겠습니다.

각종 쓰고 있는 앱들이 있습니다.

그중 cd elecapp으로 가보면 웬지 건들고 싶지 않은 폴더, 파일(Cache, Cookies등)이 이것저것 만들어 져있습니다.

![alt ls](https://fkkmemi.github.io/images/electron/2018-06-27%2009-35-40%20bash%20ls.png)

## 파일 써보기

이제 우리가 쓰고 읽을 곳은 저곳이기 때문에 다시한번 조합해서 작성해 봅니다.

**src/renderer/components/test.vue**

```
  import fs from 'fs'
  
  export default {
    name: 'test',
    data: () => ({
    }),
    mounted () {
      const p = this.$electron.remote.app.getPath('appData') + '/elecapp/test.txt'
      fs.writeFile(p, 'abcd', (err) => {  
        if (err) console.log(err)
      })
    },
    methods: {}
  }
```

test.txt에 abcd가 잘 써져있는 것을 확인 할 수 있습니다.

그런데 저 경로는 윈도우에서 먹을까요?

맥, 리눅스등은 슬래쉬(/) 단위지만 윈도우는 역슬래쉬(\)로 되어 있습니다.

그래서 path라는 모듈이 필요한 것입니다.

```
  import fs from 'fs'
  import path from 'path'

  export default {
    name: 'test',
    data: () => ({
      
    }),
    mounted () {
      const p = path.join(this.$electron.remote.app.getPath('appData'), 'elecapp', 'test.txt')
      fs.writeFile(p, 'qwert', (err) => {
        if (err) console.log(err)
      })
    },
    methods: {}
  }
```

이제 윈도우, 맥 호환 가능한 저장소를 얻은 것 같습니다.

## 파일 쓰고 읽기 페이지로 최종 테스트

이제 파일을 하나 더 만들어서 제대로 테스트 해보겠습니다.

지난번 처럼 app.vue, router/index.js 에 testFile을 추가합니다.

**src/renderer/router/index.js**

```
    ...
      component: require('@/components/test').default
    },
    { // 추가
      path: '/testFile',
      name: 'testFile',
      component: require('@/components/testFile').default
    },
```

**src/renderer/app.vue**

```
  { icon: 'title', title: 'test', to: '/test' },
  { icon: 'attachment', title: 'testFile', to: '/testFile' } // 추가
```

**src/renderer/components/testFile.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm6>
      <v-card>
        <v-card-title>test1 Actions</v-card-title>
        <v-card-text>
          <v-text-field
              id="testing"
              name="input-1"
              label="파일에 쓸 내용 작성"
              v-model="test1.text"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="test1save">
            <v-icon left>save</v-icon>
            파일저장
          </v-btn>
          <v-btn color="warning" @click="test1remove">
            <v-icon left>remove</v-icon>
            파일삭제
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-flex xs12 sm6>
      <v-card>
        <v-card-title>test1 Read</v-card-title>
        <v-card-text>
          {{test1.read}}
        </v-card-text>
        <v-card-actions>
          <v-btn color="success" @click="test1read">
            <v-icon left>attachment</v-icon>
            파일읽기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-snackbar
        :timeout="snackbar.timeOut"
        top
        v-model="snackbar.act"
        :color="snackbar.color"
    >
      {{ snackbar.text }}
      <v-spacer></v-spacer>
      <v-btn flat color="white" @click.native="snackbar.act = false">닫기</v-btn>
    </v-snackbar>
  </v-layout>
</template>

<script>
  import fs from 'fs'
  import path from 'path'

  export default {
    name: 'testFile',
    data: () => ({
      snackbar: {
        act: false,
        text: '',
        color: 'success',
        timeOut: 5000
      },
      test1: {
        text: '1234a가나',
        read: ''
      },
      appPath: ''
    }),
    mounted () {
      this.appPath = path.join(this.$electron.remote.app.getPath('appData'), 'elecapp', 'test1.txt')
    },
    methods: {
      pop (msg, cl, t) {
        this.snackbar.act = true
        this.snackbar.text = msg
        this.snackbar.color = cl
        this.snackbar.timeOut = t
      },
      test1save () {
        fs.writeFile(this.appPath, this.test1.text, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.pop('잘 써졌음', 'success', 3000)
        })
      },
      test1remove () {
        fs.unlink(this.appPath, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.pop('잘 지워짐', 'success', 3000)
        })
      },
      test1read () {
        fs.readFile(this.appPath, (err, r) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.test1.read = r
          this.pop('잘 읽음', 'success', 3000)
        })
      }
    }
  }
</script>

<style scoped>
</style>
```

> 고급 개발자분들을 위해 fs.promises(node V10)로 구현하려 했더니 일렉트론은 node V8 버전을 쓰네요.. 아쉽..

**실행 결과**

![alt success](https://fkkmemi.github.io/images/electron/2018-06-27%2011-19-46%20elecapp.png)

파일 저장을 누른 후 파일 읽기를 눌렀더니 잘 읽어집니다.

![alt fault](https://fkkmemi.github.io/images/electron/2018-06-27%2011-21-49%20elecapp.png)

파일 삭제를 누른후 파일 읽기를 누르면 에러가 뜹니다.

- appPath라는 변수를 시작할때 저장해 놓습니다.
- 정해놓은 파일(appPath)을 쓰고 읽고 지우는 행위입니다.
- 추한 알러트(alert())를 대신하여 저번에 썼던 pop method를 그대로 긁어왔습니다.
- vuetify 관련 콤포는 다음에 설명드리겠습니다.

# 디비 컨트롤

사실 파일을 가지고 별로 할 일이 없습니다. 결국 디비를 사용하려 하는 것인데요..

파일 컨트롤을 앞서 구현해 본 이유는 디비도 결국 어딘가의 파일로 쓰기/읽기를 할 뿐인데요..

물리적으로 어디에 저장되어 있는 지 알고 넘어가는 것이 좋기 때문입니다.(*rdbms도, nosql도 결국엔 어딘가에 파일이 덩어리로 있습니다.*)

noSQL lite버전인 neDB라는 것을 사용해서 읽고 쓰기를 시험해보겠습니다.(*rdbms를 주로 쓰시는 분은 sqlite로 테스트 해보시면 됩니다. 이치는 다 같습니다*)

## 설치

https://www.npmjs.com/package/nedb

```
$ yarn add nedb
```

## 전역 컨트롤 등록

여러페이지에서 쓰고 싶기 때문에 핵심부인 main.js에 정의해봅니다.

**src/renderer/main.js**

```
import Vue from 'vue'
import axios from 'axios'
import Vuetify from 'vuetify'
import { remote } from 'electron' // 추가 
import path from 'path' // 추가
import 'vuetify/dist/vuetify.css'

import App from './App'
import router from './router'
import store from './store'

Vue.use(Vuetify)
if (!process.env.IS_WEB) Vue.use(require('vue-electron'))
Vue.http = Vue.prototype.$http = axios
Vue.config.productionTip = false

// 추가 시작
const dbPath = path.join(remote.app.getPath('appData'), 'elecapp', 'test.db')
const Datastore = require('nedb')
const db = new Datastore({ filename: dbPath })
db.loadDatabase((err) => {
  if (err) console.log(err)
})
Vue.prototype.$db = db
// 추가 끝

/* eslint-disable no-new */
new Vue({
  components: { App },
  router,
  store,
  template: '<App/>'
}).$mount('#app')
```

- 디비저장소의 경로를 얻기위해 electron, path등을 임포트했습니다.

- neDB 공식홈에 있는 내용을 기반으로 연결했습니다.

  > 현재 eslint standard를 사용하기 때문에 에러나지 않도록 정도만 바꾼 겁니다.

- new Datastore() 로 파일명을 지정 안하면 저장 없이 인메모리 방식으로 구동됩니다.

- Vue.prototype.$db 를 했으므로 이제부터 어느 페이지에서든 this.$db로 엑세스 가능합니다.

## 디비페이지 만들기

위에 testFile처럼 testDB 라는 페이지를 만듭니다.

라우터와 메뉴에 잘 추가하신후 뷰파일을 만듭니다.

**src/renderer/components/testDB.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm6>
      <v-card>
        <v-card-title>등록 카드</v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid">
            <v-text-field
                v-model="form.name"
                :rules="rule.name"
                :counter="10"
                label="이름"
                required
            ></v-text-field>
            <v-text-field
                v-model="form.email"
                :rules="rule.email"
                :counter="100"
                label="E-mail"
                required
            ></v-text-field>
            <v-btn
                color="primary"
                :disabled="!valid"
                @click="formSubmit"
            >
              <v-icon left>save</v-icon>
              등록
            </v-btn>
            <v-btn @click="formClear">
              <v-icon left>undo</v-icon>
              초기화</v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-flex>
    <v-flex xs12 sm6>
      <v-card>
        <v-card-title>디비 확인 카드</v-card-title>
        <v-card-text>
          {{content}}
        </v-card-text>
        <v-card-actions>
          <v-btn color="success" @click="read">
            <v-icon left>attachment</v-icon>
            파일읽기
          </v-btn>
          <v-btn color="error" @click="remove">
            <v-icon left>clear</v-icon>
            전부 지우기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-snackbar
        :timeout="snackbar.timeOut"
        top
        v-model="snackbar.act"
        :color="snackbar.color"
    >
      {{ snackbar.text }}
      <v-spacer></v-spacer>
      <v-btn flat color="white" @click.native="snackbar.act = false">닫기</v-btn>
    </v-snackbar>
  </v-layout>
</template>

<script>
  export default {
    name: 'testFile',
    data: () => ({
      snackbar: {
        act: false,
        text: '',
        color: 'success',
        timeOut: 5000
      },
      valid: true,
      form: {
        name: '',
        email: '',
        rmk: ''
      },
      rule: {
        name: [
          v => !!v || '이름은 꼭 써야합니다.',
          v => (v && v.length <= 10) || '이름은 10글자 이하여야합니다.'
        ],
        email: [
          v => !!v || '이메일은 꼭 써야합니다.',
          v => (v && v.length <= 100) || '이름은 100글자 이하여야합니다.',
          v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '유효한 이메일 주소가 아닙니다.'
        ]
      },
      content: ''
    }),
    mounted () {
    },
    methods: {
      pop (msg, cl, t) {
        this.snackbar.act = true
        this.snackbar.text = msg
        this.snackbar.color = cl
        this.snackbar.timeOut = t
      },
      formSubmit (e) {
        if (!this.$refs.form.validate()) return this.pop('값이 유효하지 않습니다', 'error', 60000)
        this.$db.insert(this.form, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.pop('저장 성공', 'success', 5000)
          this.formClear()
        })
      },
      formClear () {
        this.$refs.form.reset()
      },
      read () {
        this.$db.find({}, (err, r) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.content = r
        })
      },
      remove () {
        this.$db.remove({}, { multi: true }, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.pop('모두 삭제 성공', 'success', 5000)
        })
      }
    }
  }
</script>

<style scoped>
</style>
```

### html

https://vuetifyjs.com/ko/components/forms

위의 예제를 조금 수정해서 form.name, form.email과 양방향 바인딩되게 하였습니다.

뷰티파이의 폼 밸리데이션 기본 예제도 조금 넣어봤습니다.

유효하지 않으면 등록 버튼에 불이 안들어오고 에러메세지가 써지는 정도죠..

> 기본일 뿐이고 사실 vee-validate등으로 유효성 판단하시면 매우 편리합니다.

그 밖에 ref 라는 태그를 이용해서 스크립트 쪽에서 참조해서 여러가지 용도로 사용 가능합니다.

### script

- formSubmit
  ref tag로 유효성 안맞으면 내뱉고
  this.form(form.name, form.email, form.rmk) 통째로 추가 합니다.

- read
  this.$db.find({}) 라는 것은 전체를 배열로 다 가져온다는 것입니다.
  this.$db.find({ name: ‘가나다’}) 라고 하면 이름이 가나다인 것만 가져옵니다.

- remove

  this.$db.remove({})는 원래 mongoDB에서는 싹다 날리는 것을 의미합니다. {}는 노-필터란 얘기니까…

  > 사실 그래서 데이터 여럿 날렸었죠.. 아마도 저같은 바보들 때문에 여러개는 뒤에 옵션{ multi: true }을 주게 만들었나봅니다. 옵션이 없다면 데이터는 하나만 지워집니다.

## 결과 확인

![alt testDB](https://fkkmemi.github.io/images/electron/2018-06-27%2013-45-41%20elecapp.png)

# 소스 저장소

혹시 따라하며 소스 꼬인 분들이 있을 수도 있어서 깃 리포 주소를 적어 놓습니다..

https://github.com/fkkmemi/elecapp

# 결론

이것으로 시스템 자원(파일)을 이용한 앱을 만들 수 있다는 검증이 되었습니다..

다음에는 우아하게 만들기 위한 UI를 짚어보도록 하겠습니다..

# 커뮤니티 공개

클리앙 팁과강좌

https://www.clien.net/service/board/lecture/12288374



## [2. UI 이해](https://fkkmemi.github.io/electron/electron-02-ui/)

최근 유행하는 UI 흐름에 대해 간단히 설명드립니다.

이번 챕터는 사실 일렉트론이랑 상관은 없습니다. 디자인과 관련되어 있으니 유관련이신 분들은 참고하세요..

예전 NEMBV 프로젝트 진행할때 https://fkkmemi.github.io/nembv/nembv-00-intro/

언급했었지만 다시 정리하면

- 기기해상도 원페이지 프로그래밍:
  모바일, 태블릿, 데스크탑을 한페이지로 만드는 추새 입니다.(*eg: 인스타 같은 거 보면 데탑에선 좌우로 사진이 배치되다가 모바일에서는 스크롤하기 좋게 사진이 하나만 나오죠*)
- 색상 사용의 단일화:
  배경이나 기본 프레임 외에 UI는 상황별 색상 한 8가지(primary, success, warning, error, info, etc..) 정도만 놓고 만듭니다. 쓰잘데 없는 다양한 색깔은 집중도가 떨어지기 때문이죠..
- 메뉴 정리(룩앤필):
  모바일 앱이 사용이 많아지면서 익숙해진 사람들은 아디다스 아이콘이 메뉴 버튼으로 대부분 인지 하기 때문에 메뉴 접근성이 모바일 앱처럼 갑니다. 수평으로 늘어 놓던 옛 스타일은 다른 용도로 쓰죠(소셜, 인포등)
- 위드아웃 디자이너:
  이제 백엔드 개발자나 데탑 개발자도 UI를 다룰 수 있게 되었습니다. 바로 복잡한 css 직접 핸들링이 없기 때문이죠.. 뷰티파이나 부트스트랩등의 css프레임웤들이 도와줍니다.(*일러로 한땀한땀 버튼 만들던 전 좀 씁쓸하네요*)
- 프론트엔드 3대장(angular, react, vue):
  스크립트와 화면과 따로 놀던 시대에서 이제는 데이터 자체가 곧 화면입니다. set과 get 같은 절차 없이 바인딩되서 화면이 꾸며집니다.
- 어떤 요소든 손가락만하게:
  모바일 중심으로 가면서 깨알 같은 버튼을 여기저기 나열한 페이지들은 유행이 지났습니다.
  처음엔 무식하게 큰 버튼들이 위화감을 주었지만..마우스가 아닌 손가락으로 클릭하게 만든 버튼이 포함된 페이지들이 결국 트렌드가 되었습니다.

vuetify를 그냥 쓰기만한다고 해서 공대감성이 해제되지는 않습니다.

모던웹 트렌드는 디자이너가 아니더라도 감각은 필요합니다.(*일명 풀스택 비스무리한 개발자를 실리콘밸리에서도 많이 찾습니다..*)

엔드유저 상황의 접근성과 배치가 중요합니다.(*나란히 놓여 있는 버튼 2개중 하나가 단 1px만 높아도 생각보다 많은 이질감을 줍니다. 그것은 곳 품질의 하락입니다.*)

디자이너로 태어나지 않은 분들도 이제 실체를 보여주는 개발에도 관심을 가져보시기 바랍니다.

> 대부분의 경우 재밌는 만화는 그림체도 좋죠

 On This Page[색상](https://fkkmemi.github.io/electron/electron-02-ui/#색상)[청소](https://fkkmemi.github.io/electron/electron-02-ui/#청소)[배치의 예술](https://fkkmemi.github.io/electron/electron-02-ui/#배치의-예술)[그리드 시스템을 위한 3요소](https://fkkmemi.github.io/electron/electron-02-ui/#그리드-시스템을-위한-3요소)[일렉트론 기본 구성](https://fkkmemi.github.io/electron/electron-02-ui/#일렉트론-기본-구성)[그리드시스템 테스트](https://fkkmemi.github.io/electron/electron-02-ui/#그리드시스템-테스트)[코드 줄여보기](https://fkkmemi.github.io/electron/electron-02-ui/#코드-줄여보기)[콤포넌트 사용](https://fkkmemi.github.io/electron/electron-02-ui/#콤포넌트-사용)[v-card, v-list](https://fkkmemi.github.io/electron/electron-02-ui/#v-card-v-list)[테마 동적으로 변경해보기](https://fkkmemi.github.io/electron/electron-02-ui/#테마-동적으로-변경해보기)[실사용중인 앱 예](https://fkkmemi.github.io/electron/electron-02-ui/#실사용중인-앱-예)[원본 소스](https://fkkmemi.github.io/electron/electron-02-ui/#원본-소스)[결론](https://fkkmemi.github.io/electron/electron-02-ui/#결론)[ps](https://fkkmemi.github.io/electron/electron-02-ui/#ps)[커뮤니티 공개](https://fkkmemi.github.io/electron/electron-02-ui/#커뮤니티-공개)

# 색상

일렉트론 기본 칙칙한 다크 테마부터 바꿔봅시다.

**src/renderer/App.vue**

```
<v-app dark>
```

![alt dark](https://fkkmemi.github.io/images/electron/2018-06-27%2016-02-07%20elecapp.png)

```
<v-app>
```

![alt white](https://fkkmemi.github.io/images/electron/2018-06-27%2016-03-29%20elecapp.png)

테마는 두가지 뿐이네요 어떻게 아냐고요?

개발하는 방법은 늘 레퍼런스 사이트를 꾸준히 보면 됩니다.

https://vuetifyjs.com/ko/components/api-explorer

여기서 v-app을 찾아보면 props라는 곳이 있는데 dark 밖에 없죠

디폴트 false 라는 말은 위의 예시처럼 dark 라는 태그가 없다는 것입니다.

# 청소

기본 레이아웃이 이것저것 보여주려고 쓸데 없는 버튼들이 있는데 불필요한 것들은 청소해봅니다.

**src/renderer/app.vue**

```
<template>
  <div id="app">
    <v-app>
      <v-navigation-drawer
        v-model="drawer"
        app
      >
        <v-list>
          <v-list-tile 
            router
            :to="item.to"
            :key="i"
            v-for="(item, i) in items"
            exact
          >
            <v-list-tile-action>
              <v-icon v-html="item.icon"></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title v-text="item.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
      <v-toolbar app>
        <v-toolbar-side-icon @click.native.stop="drawer = !drawer"></v-toolbar-side-icon>

        <v-toolbar-title v-text="title"></v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn
          icon
        >
          <v-icon>battery_std</v-icon>
        </v-btn>
      </v-toolbar>
      <v-content>
        <v-container fluid fill-height grid-list-md>
          <v-slide-y-transition mode="out-in">
            <router-view></router-view>
          </v-slide-y-transition>
        </v-container>
      </v-content>

      <v-footer app>
        <v-spacer></v-spacer>
        <span>&copy; 2017 everybody go &nbsp; </span>
      </v-footer>
    </v-app>
  </div>
</template>

<script>
  export default {
    name: 'elecapp',
    data: () => ({
      drawer: true,
      items: [
        { icon: 'cloud', title: '메뉴바꿈!', to: '/' },
        { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' },
        { icon: 'title', title: 'test', to: '/test' },
        { icon: 'attachment', title: 'testFile', to: '/testFile' },
        { icon: 'archive', title: 'testDB', to: '/testDB' },
        { icon: 'brightness_low', title: 'testUI', to: '/testUI' }
      ],
      title: 'Electron Test'
    })
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons');
  /* Global CSS */
</style>
```

![alt white](https://fkkmemi.github.io/images/electron/2018-07-06%2009-32-45%20elecapp.png)

깔끔하네요 우선~

# 배치의 예술

https://vuetifyjs.com/ko/layout/grid

모바일시대가 되며 부트스트랩부터 구체화되서 발전하고 있는 그리드 시스템입니다.

이제부터 모바일, 태블릿, 데스크탑등의 사이즈를 바꿔가며 확인해보시기 바랍니다.

## 그리드 시스템을 위한 3요소

v-container / v-layout / v-flex

항상 이런 3 계층으로 이루어져야합니다.

## 일렉트론 기본 구성

위의 소스중 중간쯤 보면 v-container가 있고 router-view라는 곳이 만든 페이지들 표시하는 곳입니다.

모든 페이지가 v-container 안에 들어가 있는거죠..

**src/renderer/app.vue**

```
<v-container fluid fill-height grid-list-md>
  <v-slide-y-transition mode="out-in">
    <router-view></router-view>
  </v-slide-y-transition>
</v-container>
```

- fluid는 좌우로 꽉찬 화면을 의미합니다.
- grid-list-md는 각 요소들간의 여백입니다. 없으면 답답하게 꽉꽉 붙어있죠..

어떻게 테스트하냐고요? 실행시켜놓고 testDB.vue 같은 페이지를 grid-list-md를 지우고/살리고 저장해보면 됩니다.

## 그리드시스템 테스트

테스트를 위한 파일을 추가해보겠습니다.

**src/renderer/components/testUI.vue**

```
<v-layout row wrap>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="red">
        a
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="blue">
        b
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="red">
        c
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="blue">
        d
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="red">
        e
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <v-card color="blue">
        f
      </v-card>
    </v-flex>
  </v-layout>
```

https://vuetifyjs.com/ko/layout/breakpoints

위 링크를 참조해보면.

**12가 너비의 맥스**인 것이고(*bootstrap등 다른 프레임도 마찬가지입니다.*)

xs12의 경우 600px이하(곧 모바일)에서는 12개를 다 쓰겠다는 것 곧 너비를 다쓴다는 것입니다.

xs6로 해두면 12 나누기 6 = 2개가 나오겠죠.

실제 구현 모습..

- xs: mobile
  ![alt xs](https://fkkmemi.github.io/images/electron/2018-07-06%2010-45-14%20xs.png)
- sm: tablet
  ![alt sm](https://fkkmemi.github.io/images/electron/2018-07-06%2010-45-30%20sm.png)
- md: notebook
  ![alt md](https://fkkmemi.github.io/images/electron/2018-07-06%2010-45-46%20md.png)
- lg: desktop
  ![alt lg](https://fkkmemi.github.io/images/electron/2018-07-06%2010-46-01%20lg.png) 특이한 것은 옆에 사이드 메뉴가 있죠? lg사이즈에서 v-navigation-drawer가 살아나는 것을 알수 있습니다.
- xl: 4k monitor
  모니터가 후져서 이건 테스트가 안되네요…

사실 처음 디자인 운운했던것 중 가장 큰 요소가 바로 이 그리드 시스템입니다.

정렬만 제대로 해주면 왠만한 UI 때려 밖아도 그럴싸합니다.

vuetify가 이미 설계가 잘 되어있기 때문에 저 3요소만 잘 이용해주면 배치의 문제는 끝난 것입니다.

# 코드 줄여보기

처음 접하시는 분들은 vuetify와 vue가 뭔지 잘 모르시겁니다. 겁내지마세요.. 저도 둘다 한지 얼마 안되었답니다.

vuetify가 위와 같이 화면을 이쁘게 구성하는 css 프레임웤이라고 하면..

vue.js는 정적인 html을 편리하게 사용가능하게 합니다.

위의 소스를 바꿔 보겠습니다.

**src/renderer/components/testUI.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm6 md4 lg3 xl2 v-for="(value, index) in grids">
      <v-card dark :color="index % 2 ? 'red' : 'blue'">
        {{value}}
      </v-card>
    </v-flex>
    <v-flex xs12>
      <v-btn @click="add">추가</v-btn>
      <v-btn @click="remove">삭제</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: 'testUI',
    data: () => ({
      grids: ['뷰', '티', '파', '이', '일', '렉', '트', '론']
    }),
    mounted () {
    },
    methods: {
      add () {
        this.grids.push('a')
      },
      remove () {
        this.grids.pop()
      }
    }
  }
</script>

<style scoped>
</style>
```

![alt lgvue](https://fkkmemi.github.io/images/electron/2018-07-06%2011-02-09%20lg.png)

html 코드가 비약적으로 줄면서 8개나 표시하네요~

추가 삭제로 데이터를 지우지만(배열 추가: push, 삭제: pop) 화면도 반영됩니다.

vue의 할일은 저런 것입니다.

v-for로 이터레이팅(반복작업)도 해서 값(value)도 얻고 몇번째(index)도 얻고..

인라인 조건으로 색상도 바꾸고..

이런 홀짝 조건 문은

```
index % 2 ? 'red' : 'blue'
```

이런 것과 같습니다.

```
if (index % 2) color = 'red'
else color = 'bule'
```

간단한 조건은 html에 저렇게 쑤셔 넣을 수 있어서 좋죠~

color 앞에 콜론(:)이 어떤 조건이나 값에 바인딩이 되어있다는 것입니다.

더 복잡한 조건은 method나 computed 등에서 할 수 있습니다.

# 콤포넌트 사용

이제 오와 열을 잘 맞출 수 있으니 vuetify 콤포넌트를 섞어서 모양을 내보겠습니다.

## v-card, v-list

v-card는 모양을 낼때 제일 중요한 콤포넌트입니다.

정리해서 넣기 좋은 박스(윈도우 어플이라면 panel 같은..)입니다..

https://vuetifyjs.com/ko/components/cards

위 공식 도큐에 자세히 나와있습니다..

이중 제가 또 강조 하고 싶은 것은

권장하는 공식 도큐의 가이드라인을 지켜라! 입니다.

v-card 아래에는 v-card-title, v-card-text, v-card-action 등으로 해야 이쁘게 나옵니다.

**비정상**

```
<v-card-title>
  <v-card>
    이런식으로하면 당연히 요상하게 나옵니다.
    <div>sfwefw</div>  
  </v-card>
</v-card-title>
```

**권장**

```
<v-card>
  <v-card-title>제목</v-card-title>
  <v-card-text>어쩌고저쩌고 높이가 가변<br>ddd?<br></v-card-text>
  <v-card-action>각종 버튼</v-card-action>
</v-card>
```

괜히 v-card안에 <div style=""> 같은 거 스타일 채워서 꾸역꾸역 맞춰봤자.. 지금 잘나와도 해상도나 특이 상황에 구겨질 수 있습니다..

v-card 안에 v-list를 넣을 수 있습니다.

예제에 잘 나와 있기 때문에 알고 있는 거죠~

공식 예제를 카피엔 페이스트하고 약간 주물럭 거린 결과 입니다.

- sm
  ![alt comsm](https://fkkmemi.github.io/images/electron/2018-07-06%2013-44-50%20sm.png)
- xs
  ![alt comxs](https://fkkmemi.github.io/images/electron/2018-07-06%2013-45-14%20xs.png)

**src/renderer/components/testUI.vue**

```
<template>
  <v-layout row wrap>
    <v-flex xs12 sm6>
      <!--<v-card height="400">-->
      <v-card style="height: 400px">
        <v-toolbar color="cyan" dark>
          <v-icon>mode_comment</v-icon>

          <v-toolbar-title>Inbox</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>search</v-icon>
          </v-btn>
        </v-toolbar>
        <v-list two-line>
          <template v-for="(item, index) in items">
            <v-subheader
                v-if="item.header"
                :key="item.header"
            >
              {{ item.header }}
            </v-subheader>

            <v-divider
                v-else-if="item.divider"
                :inset="item.inset"
                :key="index"
            ></v-divider>

            <v-list-tile
                v-else
                :key="item.title"
                avatar
                @click=""
            >
              <v-list-tile-avatar>
                <img :src="item.avatar">
              </v-list-tile-avatar>

              <v-list-tile-content>
                <v-list-tile-title v-html="item.title"></v-list-tile-title>
                <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary">
            <v-icon left>add</v-icon>
            Add
          </v-btn>
          <v-btn color="error">
            <v-icon left>remove</v-icon>
            Remove
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>

    <v-flex xs12 sm6>
      <v-card height="400">
        <v-toolbar color="orange" dark>
          <v-icon>home</v-icon>

          <v-toolbar-title>Todo list</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>search</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text>
          <v-alert
              :value="true"
              type="info"
          >
            This is a info alert.
          </v-alert>
        </v-card-text>
        <v-list
            subheader
            two-line
        >
          <v-list-tile @click="">
            <v-list-tile-action>
              <v-checkbox v-model="notifications"></v-checkbox>
            </v-list-tile-action>

            <v-list-tile-content @click="notifications = !notifications">
              <v-list-tile-title>Notifications</v-list-tile-title>
              <v-list-tile-sub-title>Allow notifications</v-list-tile-sub-title>
            </v-list-tile-content>
          </v-list-tile>

          <v-list-tile @click="">
            <v-list-tile-action>
              <v-checkbox v-model="sound"></v-checkbox>
            </v-list-tile-action>

            <v-list-tile-content @click="sound = !sound">
              <v-list-tile-title>Sound</v-list-tile-title>
              <v-list-tile-sub-title>Hangouts message</v-list-tile-sub-title>
            </v-list-tile-content>
          </v-list-tile>

          <v-list-tile @click="">
            <v-list-tile-action>
              <v-checkbox v-model="video"></v-checkbox>
            </v-list-tile-action>

            <v-list-tile-content @click="video = !video">
              <v-list-tile-title>Video sounds</v-list-tile-title>
              <v-list-tile-sub-title>Hangouts video call</v-list-tile-sub-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: 'testUI',
    data: () => ({
      items: [
        { header: 'Today' },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
          subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; <span class='hidden-xs-only'> I'll be in your neighborhood doing errands this weekend. Do you want to hang out?</span>"
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; <span class='hidden-xs-only'>Wish I could come, but I'm out of town this weekend.</span>"
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui oui',
          subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; <span class='hidden-xs-only'>Do you have Paris recommendations? Have you ever been?</span>"
        }
      ],
      notifications: false,
      sound: false,
      video: false,
      invites: false
    }),
    mounted () {
    },
    methods: {
    }
  }
</script>

<style scoped>
</style>
```

코드가 장황해 보이긴 하지만 공식도큐에서 긁어온 것이며 실제 별것은 없습니다.

이중 디자인의 키포인트 몇가지만 짚어 보겠습니다.

- v-card의 height: style 대신 해당 콤포넌트의 속성을 따르자..

왠만하면 style 건들지 말라고 하는 것은 이런 것 때문이죠..

```
<v-card height="400">
<v-card style="height: 400px">
```

둘의 효과는 같습니다.

하지만 동적으로 구현할때는 스타일보다 장점이 있습니다.

```
<v-card :height="hv">
```

hv라는 값이 상황에 따라 동적이어야한다면 정수형 변수 하나면 됩니다.

하지만 스타일일 경우 스타일 바인딩이라는 것을 해야하고 정수 + ‘px’를 추가해야하죠..

- v-spacer: 공백의 묘미

그림에서 Inbox와 검색 버튼이 있는데 가운데 공백을 잘 넣어줄 때 쓰입니다.

실제 구현시 상단 혹은 하단 툴바에 좌우 끝에 붙히고 싶을 때 정말 많죠..(*float-right같은 걸로 해결은 되지만..고려할 게 많죠..*)

그림중 ADD REMOVE 버튼도 이걸 이용해서 우측에 잘 깔끔하게 붙어있는 것이죠..

- v-card-action

버튼류는 이안에 채워 넣으면 좋습니다.

- v-card-text

v-card안에 높이가 가변인 컨텐츠가 들어가면 됩니다.

- v-cart-title

느낌처럼 제목 써주는 용도인데 높이가 정해져있어서 큰 컨텐츠는 찐따같이 나옵니다.

# 테마 동적으로 변경해보기

막상 화이트로 해봤는데 그냥 그렇네요..

업체와 얘기할때 구두로 설명하기보다는 보여주면서 설명하는게 어떨까요?

다른 콤포넌트가 다크 테마에서는 어찌 되는지 한번 확인해봅시다..

위에 설명했던 v-app의 dark가 동적이면 끝나겠죠?

**src/renderer/App.vue**

```
<template>
  <div id="app">
    <v-app :dark="dark">
      <v-navigation-drawer
        v-model="drawer"
        app
      >
        <v-list>
          <v-list-tile 
            router
            :to="item.to"
            :key="i"
            v-for="(item, i) in items"
            exact
          >
            <v-list-tile-action>
              <v-icon v-html="item.icon"></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title v-text="item.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
      <v-toolbar app>
        <v-toolbar-side-icon @click.native.stop="drawer = !drawer"></v-toolbar-side-icon>

        <v-toolbar-title v-text="title"></v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn
          icon
          @click="dark = !dark"
        >
          <v-icon>loop</v-icon>
        </v-btn>
      </v-toolbar>
      <v-content>
        <v-container fluid fill-height grid-list-md>
          <v-slide-y-transition mode="out-in">
            <router-view></router-view>
          </v-slide-y-transition>
        </v-container>
      </v-content>

      <v-footer app>
        <v-spacer></v-spacer>
        <span>&copy; 2017 everybody go &nbsp; </span>
      </v-footer>
    </v-app>
  </div>
</template>

<script>
  export default {
    name: 'elecapp',
    data: () => ({
      drawer: true,
      items: [
        { icon: 'cloud', title: '메뉴바꿈!', to: '/' },
        { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' },
        { icon: 'title', title: 'test', to: '/test' },
        { icon: 'attachment', title: 'testFile', to: '/testFile' },
        { icon: 'archive', title: 'testDB', to: '/testDB' },
        { icon: 'brightness_low', title: 'testUI', to: '/testUI' }
      ],
      title: 'Electron Test',
      dark: false
    })
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons');
  /* Global CSS */
</style>
```

![alt darkdyn](https://fkkmemi.github.io/images/electron/2018-07-06%2014-36-41%20dark.png)

우측상단에 버튼을 누르니 테마가 토글되죠?

다크로 변하니 알아서 폰트등이 다크에서 화이트로 변경되네요..

# 실사용중인 앱 예

직접 만드는 중인 앱입니다.. (*만들다 만 흔적이 역력하네요..*)

![alt anal](https://fkkmemi.github.io/images/electron/2018-07-06%2011-49-11%20Analyzer.png)

주로 이런식의 대시보드형에서 그리드 시스템이 쓰이죠..

위와 같은 차트 표등이 하나의 콤포넌트가 되서 이곳 저곳에서 퍼즐조각 맞추듯 사용이 가능합니다.

# 원본 소스

https://github.com/fkkmemi/elecapp

# 결론

**공식도큐를 잘 살펴보고 가이드라인을 지키자!!**

그리고 작업을 하실 때 가급적 레이아웃을 v-layout, v-flex로 전부 짜놓고 채워넣는 것이 좋습니다.

사실 디자이너 수준에서는 얘기가 하나 더 들어가는데..

바로 색상간 배치 문제가 있습니다.

각 콤포넌트들의 대비색도 마케팅 효과와 관련이 있기 때문에 제가 따라갈 수준은 안됩니다.

제가 도와드릴 수 있는 것은 극악의 공대감성 탈피 정도입니다.

다음번에는 디비를 연동해서 Todo 앱 비슷하게 만들어 보겠습니다.

# ps

현재 솔루션으로 모바일 포팅도 성공해서 iOS, android 테스트 중입니다..

흉내내기 수준이긴 하지만.. 결국 한 플랫폼으로 서버, 데탑, 모바일로 가긴 가네요..

다 비슷한 사용자경험의 UI라 사용도 편리하고.. 개발하기도 좋네요~ 만들어둔 콤포넌트를 모바일에서 직접 씀..

해보니 back-end api가 제일 중요하네요.. 사용자 인증이니 푸쉬니 다 담당해야하니…

잡스러운 개발의 극에 달하고 있습니다..

일렉트론이 끝나면 모바일도 한번 강좌 진행해볼 예정입니다.

# 커뮤니티 공개

클리앙 팁과강좌

https://www.clien.net/service/board/lecture/12328153



## [3. todo 앱 제작](https://fkkmemi.github.io/electron/electron-03-todo/)

과정이 지겨우니 뭔가 만들면서 재미를 찾는 것이 훨씬 개발에 득이 된다고 생각합니다..

그래서 일렉트론뷰의 마지막 강좌인 투두앱(일명 GTD 할일관리)을 재미로 만들어보려합니다.

단순 재미입니다. 그저 지난 강좌에서 쓰인 db&ui가 어떻게 쓰일까 정도의 예시 정도입니다.(*당연히 구글이나 애플에서 만들어 둔거 쓰시는 게 좋죠.*)

> GTD는 매우 중요하다고 생각합니다..(나이가 들 수록 더..)
> 시간 되시는 분은 [GTD에 대하여](https://fkkmemi.github.io/talk/gtd/) 를 한번 읽어보시기 바랍니다.

연습앱들을 만들며 숙달된 피지컬, 개발력, 기능을 이용해 좋은 아이디어로 좋은 작품 만드시길 기원합니다..

 On This Page[설치: 또 시작!](https://fkkmemi.github.io/electron/electron-03-todo/#설치-또-시작)[깃 리포 생성 및 다운로드](https://fkkmemi.github.io/electron/electron-03-todo/#깃-리포-생성-및-다운로드)[일렉트론뷰 생성](https://fkkmemi.github.io/electron/electron-03-todo/#일렉트론뷰-생성)[깃 추가](https://fkkmemi.github.io/electron/electron-03-todo/#깃-추가)[의존 요소 설치](https://fkkmemi.github.io/electron/electron-03-todo/#의존-요소-설치)[레이아웃](https://fkkmemi.github.io/electron/electron-03-todo/#레이아웃)[배치](https://fkkmemi.github.io/electron/electron-03-todo/#배치)[데이터 설계](https://fkkmemi.github.io/electron/electron-03-todo/#데이터-설계)[UI 설계](https://fkkmemi.github.io/electron/electron-03-todo/#ui-설계)[데이터 추가](https://fkkmemi.github.io/electron/electron-03-todo/#데이터-추가)[날짜 라이브러리 설치](https://fkkmemi.github.io/electron/electron-03-todo/#날짜-라이브러리-설치)[화면에 데이터 추가 적용](https://fkkmemi.github.io/electron/electron-03-todo/#화면에-데이터-추가-적용)[시연 영상](https://fkkmemi.github.io/electron/electron-03-todo/#시연-영상)[설명](https://fkkmemi.github.io/electron/electron-03-todo/#설명)[디비 연동](https://fkkmemi.github.io/electron/electron-03-todo/#디비-연동)[database 설치](https://fkkmemi.github.io/electron/electron-03-todo/#database-설치)[CRUD](https://fkkmemi.github.io/electron/electron-03-todo/#crud)[시연 영상](https://fkkmemi.github.io/electron/electron-03-todo/#시연-영상-1)[설명](https://fkkmemi.github.io/electron/electron-03-todo/#설명-1)[빌드](https://fkkmemi.github.io/electron/electron-03-todo/#빌드)[원본 소스](https://fkkmemi.github.io/electron/electron-03-todo/#원본-소스)[결론](https://fkkmemi.github.io/electron/electron-03-todo/#결론)[커뮤니티 공개](https://fkkmemi.github.io/electron/electron-03-todo/#커뮤니티-공개)

# 설치: 또 시작!

저는 유난히 새 프로젝트 만들 때 희열을 느끼는데요..

지난날의 과오도 엎고.. 새로운 문법, 언어등으로 기존과의 비교도 할 수 있어서 매우 흥분됩니다.

린트(lint: 코드 검사툴) 같은 경우도 새프로젝트를 진행해봐야 airbnb가 나은지 standard가 나은지 차이를 알 수 있겠죠..

예전에는 기존 제작한 것을 챗바퀴 돌 듯 유지보수 한 적도 많았었는데요.. 차라리 새로 만들고 기존 코드를 새 형식으로 고치는 게 여러모로 편하더라고요..

## 깃 리포 생성 및 다운로드

깃 없이 하실 분은 패스하세요

우선 깃 저장소를 만들어 봅니다.

![alt git](https://fkkmemi.github.io/images/electron/2018-07-09%2013-19-36%20Create%20a%20New%20Repository.png)

```
$ git clone git@github.com:fkkmemi/todo.git
```

todo라는 디렉토리가 생성되며 README.md 파일 달랑 하나 있습니다.

## 일렉트론뷰 생성

챕터1 에서 다루었던 내용 그대로 설치 진행합니다.

```
$ vue init vuetifyjs/electron todo

? Target directory exists. Continue? Yes
? Application Name todo
? Project description An electron-vue project
? Select which Vue plugins to install axios, vue-electron, vue-router, vuex
? Use linting with ESLint? Yes
? Which eslint config would you like to use? Standard
? Setup unit testing with Karma + Mocha? Yes
? Setup end-to-end testing with Spectron + Mocha? Yes
? What build tool would you like to use? builder
? author fkkmemi <fkkmemi@gmail.com>

   vue-cli · Generated "todo".

---

All set. Welcome to your new electron-vue project!

Make sure to check out the documentation for this boilerplate at
https://simulatedgreg.gitbooks.io/electron-vue/content/.

Next Steps:

  $ cd todo
  $ yarn (or `npm install`)
  $ yarn run dev (or `npm run dev`)
```

## 깃 추가

깃 없이 하실 분은 패스하세요

yarn등으로 의존요소 설치나 빌드 전에 깃 추가해 놓는 게 편합니다.

node_modules or build등 디렉토리 깃 예외 걸기 귀찮기 때문이죠.

```
$ git add ./
```

## 의존 요소 설치

뷰티파이 + 일렉트론등 package.json에 지정된 요소들이 깔리죠..

조금 의아한것은 “vuetify”: “1.0.0” 앞에 ^가 없어서 1.0 고정인데요..

이걸 “vuetify”: “^1.0.0” 바꾸고 yarn을 해서 1.1.1이 되었지만.. 호환성 문제가 있는 것 같았습니다.(*종종 콘솔에 에러가 뜸*)

^가 앞에 있다는 것은 x.y.z 라는 버전중 y z는 올라가도 상관 없다는 것입니다.

별수 없이 구닥다리 1.0으로 작업했습니다..

```
$ yarn
```

# 레이아웃

불필요한 기본 요소를 빼고.. 상큼한 색상으로 툴바를 바꿔봤습니다.

https://vuetifyjs.com/ko/style/colors

`#AEEA00` 이런식으로 쓰지 말고 lime accent-4 정해진 네이밍을 사용하세요

색상이나 테마는 위에 색상중 골라야 잘 어울립니다..

**App.vue**

```
<template>
  <div id="app">
    <v-app>
      <v-navigation-drawer
        fixed
        v-model="drawer"
        app
      >
        <v-list>
          <v-list-tile 
            router
            :to="item.to"
            :key="i"
            v-for="(item, i) in items"
            exact
          >
            <v-list-tile-action>
              <v-icon v-html="item.icon"></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title v-text="item.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
      <v-toolbar fixed app dark color="pink lighten-2">
        <v-toolbar-side-icon @click.native.stop="drawer = !drawer"></v-toolbar-side-icon>
        <v-toolbar-title v-text="title"></v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>search</v-icon>
        </v-btn>
        <v-btn
          icon
        >
          <v-icon>more_vert</v-icon>
        </v-btn>
      </v-toolbar>
      <v-content>
        <v-container fluid fill-height>
          <v-slide-y-transition mode="out-in">
            <router-view></router-view>
          </v-slide-y-transition>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  export default {
    name: 'todo',
    data: () => ({
      drawer: false,
      items: [
        { icon: 'apps', title: 'Welcome', to: '/' },
        { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' }
      ],
      title: 'ToDo'
    })
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons');
  /* Global CSS */
</style>
```

![alt toolbar](https://fkkmemi.github.io/images/electron/2018-07-10%2013-46-44%20todo.png)

툴바가 블링블링 하네요

컬러 속성을 color=”pink lighten-2”로 줬기 때문이죠..

## 배치

WelcomeView.vue에 v-list 콤포넌트를 꽉차게 넣어보겠습니다.

https://vuetifyjs.com/ko/components/lists

위에 예제중 아무거나 넣은 것기 때문에 따로 코드는 작성하지 않습니다.

![alt layout](https://fkkmemi.github.io/images/electron/2018-07-10%2014-06-35%20todo.png)

배치를 확인하기 위해 dark list를 넣어봤는데요..

정말 맘에 안드네요 꽉 차지도 않는데다가 리스트 동서남북에 공간이 있네요..

![alt css](https://fkkmemi.github.io/images/electron/2018-07-10%2014-30-26%20Image.png)

우측에 개발자 모드는 요렇게 쓰는 것입니다. container div 까지 마우스로 콕콕 찍어서 오면

패딩이 16이나 있다는 것을 알 수 있습니다.

저도 html css 전문가는 아니지만 어쩔 수 없이 알아야하는 것 2가지는 바로 마진, 패딩 귀찮은 2요소입니다..(*가끔 보더도 신경써야됨*)

어떤 html 요소든 저런 식입니다.

마우스를 가져다가 여기저기 찔러보시면 2요소가 대충 뭔지 아실 겁니다.

어떤 개체의 안쪽이 패딩이고 바깥쪽이 마진인 것 같습니다..

우선 패딩을 빼고 싶네요..

vuetify는 위의 색상 정의 처럼 마진 패딩등을 축약된 단어로 이미 만들어 놨답니다..

https://vuetifyjs.com/ko/layout/spacing

위의 링크에 정말 자세히도 나와있고.. 테스트도 가능하죠~

```
<v-container fluid class="pa-0">
```

![alt pa-0](https://fkkmemi.github.io/images/electron/2018-07-10%2014-39-15%20todo.png)

그중 꽉차게 하기 위해 v-container를 fill-height를 제거하고 패딩 없는 클래스를 입히면 딱 들어 꽉 차게 됩니다.

# 데이터 설계

간단하게 한번 작성해봤습니다.

- rt: new Date(), // regist time: 등록 시간
- t: new Date(), // time: 목표 시간
- type: ‘직장’, //
- title: ‘빨래 널기’, // 제목
- comment: ‘그만하고 싶다..’, // remark: 비고
- done: false //

샘플로 이런 구조를 가지게 되는거죠..

# UI 설계

UI를 대충 구성하고 데이터 하나만 넣고 함 돌려봅니다..

vuetify에서 참조한 콤포넌트는 list, dialog, picker 정도 입니다.

예제에서 가져온 것을 조금 손본 정도 입니다.

**WelcomeView.vue**

```
<template>
  <v-layout>
    <v-flex xs12>
      <v-list two-line>
        <template v-for="(td, i) in todos">
          <v-list-tile
              :key="td.title"
              avatar
              @click=""
          >
            <v-list-tile-avatar>
              <v-icon :color="td.done ? 'success' : 'secondary'">{{td.done ? 'check' : 'hourglass_empty'}}</v-icon>
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title v-html="td.title"></v-list-tile-title>
              <v-list-tile-sub-title v-html="td.content"></v-list-tile-sub-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">info</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>

        </template>
      </v-list>
    </v-flex>

    <v-dialog
        v-model="dialog"
        width="500"
    >
      <v-btn
          color="primary"
          dark
          fab
          fixed
          bottom
          right
          slot="activator"
      >
        <v-icon>edit</v-icon>
      </v-btn>
      <v-card>
        <v-card-title>
          <span class="headline">할일 작성</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field label="제목" required counter="40" prepend-icon="title"></v-text-field>
                </v-flex>
                <v-flex xs12>
                  <v-text-field label="내용" hint="굳이 넣고 싶다면.." counter="40" prepend-icon="note"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuDate"
                      :close-on-content-click="false"
                      v-model="menuDate"
                      :nudge-right="40"
                      :return-value.sync="date"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="date"
                        label="목표 날짜"
                        prepend-icon="event"
                        readonly
                    ></v-text-field>
                    <v-date-picker v-model="date" @input="$refs.menuDate.save(date)"></v-date-picker>

                  </v-menu>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuTime"
                      :close-on-content-click="false"
                      v-model="menuTime"
                      :nudge-right="40"
                      :return-value.sync="time"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      max-width="290px"
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="time"
                        label="목표 시간"
                        prepend-icon="access_time"
                        readonly
                    ></v-text-field>
                    <v-time-picker
                        v-if="menuTime"
                        v-model="time"
                        @change="$refs.menuTime.save(time)"
                    ></v-time-picker>
                  </v-menu>
                </v-flex>

                <v-flex xs12 sm6>
                  <v-select
                      :items="['집', '직장', '밖']"
                      label="종류"
                      required
                  ></v-select>
                </v-flex>

              </v-layout>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click.native="save()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
  export default {
    name: 'welcome',
    data () {
      return {
        todos: [
          {
            rt: new Date(),
            t: new Date(),
            type: 0,
            title: '빨래 널기',
            content: '그만하고 싶다...',
            done: false
          }
        ],
        menuDate: false,
        menuTime: false,
        date: null,
        time: null,
        valid: false,
        dialog: false
      }
    },
    methods: {
      save () {
        this.dialog = false
      }
    }
  }
</script>

<style scoped>
</style>
```

- v-layout으로 기본 골격을 잡고
- v-flex x12로 리스트를 꽉 차게 구현했습니다.
- v-list도 어찌보면 v-card와 비슷한 느낌으로 접근 하시면 됩니다.
  v-list-tile 하나가 v-card 하나 같은 것입니다.
  v-list-tile = v-card
  v-list-tile-avatar = v-card-title
  v-list-tile-content = v-card-text
  v-list-tile-action = v-cart-action
- v-list 안에서 todos 만큼 반복해서 데이터를 찍게 되는데요
  template으로 돌리고 있는데 사실 v-list-tile 에서 v-for 반복 시켜도 됩니다.
  template은 실제 표시되는 html코드가 아닙니다.
  v-list-tile을 돌릴 때 조건에 따라 다르게 표시하거나.. i, j, k로 2중 3중 배열 데이터를 표시할 때..
  template같은 유령 태그를 넣어서 작업하면 됩니다.
- v-list-tile-avatar는 사진이나 아이콘이 들어가기 적합합니다.
- 컬러와 아이콘을 done의 약소한 조건식과 바인딩해서 표시됩니다.
  뭐가 done이 되었을지 어울리는지 아래 아이콘 링크를 보며 직접 화면에 뿌려보면서 확인해보는 것이 좋습니다.
  https://material.io/tools/icons/?icon=hourglass_empty&style=outline
  eg)

```
<v-icon color="primary">schedule</v-icon>
<v-icon color="red">query_builder</v-icon>
```

- dialog는 v-model=”dialog” 의 변수 하나로 켜지고 꺼집니다.
  메쏘드에서는 this.dialog = true 만 해주면 켜집니다.
  특이한 부분은 dialog 아래에 있는 v-btn 속성중 slot=”activator” 부분인데요. 이것은 dialog의 슬롯에 활성버튼이 되겠다는 것입니다.(*dialog=true*)
- dialog 아래에 v-btn은 둥그렇고 항상 하단아래에 둥둥 떠있게 되는데 이런걸 머터리얼 디자인의 핵심인 플로팅 액션 버튼(fab: floating action button)이라고 합니다.
  그저 옵션 몇개만 주면 단순 버튼이 저렇게 쉽게 fab가 되죠..
- dialog 안의 내용은 폼으로 덮었습니다.
  모양새를 위해 v-card로 만들었고요..
  역시 v-card-title에 제목을 넣었고..
  v-card-text에 실제 폼을 넣었습니다.
  v-card-action에 저장이나 취소를 넣어 v-card 정석대로 작업했습니다.
- v-form에 ref라는 태그가 있는데요 v-menu에도 있고요..
  이것은 말 그대로 참조며 메쏘드 등에서 ref명을 참고해서 어떤 행위를 할 수 있습니다.
  (*jquery를 해보신 분은 셀렉터 역활을 한다고 생각하심 됩니다. eg: $(‘#anyid’).show()*)
  v-form 유효성 검사등을 할 때 필요할 것 같네요
- 날짜, 시간 선택기
  https://vuetifyjs.com/ko/components/date-pickers 를 그대로 가져왔습니다.
- 구현한 결과

![alt git](https://fkkmemi.github.io/images/electron/2018-07-11%2008-50-29%20none.png)

![alt git](https://fkkmemi.github.io/images/electron/2018-07-11%2008-51-12%20todo.png)

- 약간의 버그 픽스

플로팅 버튼 안의 아이콘이 가운데 딱 안떨어지고 상단에 붙는 버그가 있네요 1.1.1은 문제 없는데..

원래는 node_modules - vuetify - dist - 어딘가.. - ??.css 에 height/width가 inherit(상속) 으로 되어있는데..

어쩔 수 없이 css 오버라이딩(재정의)을 했습니다.

**App.vue**

```
<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons');
  /* Global CSS */
  .btn--floating .icon {
    height: 32px;
    width: 32px;
  }
</style>
```

나중에 일렉트론뷰가 업뎃될때 지워주면 되겠죠..

아래 링크에서 참고했습니다.. (요럴 때 구글링을 사용해서 간편하게 임시방편 때웁니다~)

https://github.com/vuetifyjs/vuetify/issues/3433

검색 키워드: v-btn fab bug

> 구글링도 중요한데 당연히 검색을 뭘로 하느냐입니다. 디비를 해보면 알겠지만 자연어는 검색이 어렵습니다. 에러코드와 단어 조합이 좋습니다.
> eg)
> X: i have problem with.. 어쩌고
> O: electron build error 14021

# 데이터 추가

## 날짜 라이브러리 설치

우선 날짜관련 라이브러리 모먼트를 설치 해줍니다.

[https://momentjs.com](https://momentjs.com/)

```
$ yarn add moment
```

**main.js**

```
import moment from 'moment'
moment.locale('ko')
Vue.prototype.$moment = moment
```

프로토타입 설정으로 이제 아무 페이지에서나 moment를 사용할 수 있습니다.

moment는 날짜관련 연산, 포멧팅을 편하게 해줍니다.(*eg:2월27에서 3월 2일까지 남은 날짜, 시간, 분, 초등으로 구할 수 있습니다. 윤달도 있어서 2월은 참 곤란하죠*)

> 예전에 펌웨어를 만들때는 gps로 부터 받은 utc 시간에 9시간 더하는 것을 c코드로 일일이 다 구현했던 기억이 납니다.
> 2018 2월 28일 18시 더하기 9시간은? if ((day%4) == 0) day++; 이런식으로 말이죠..
> time.h 같은 거 적용했으면 쉽게 끝날 것을 … 좋은 경험도 했었죠.. 자스 moment(time).add(‘hours’, 9) 면 끝이네요..

## 화면에 데이터 추가 적용

이제 껍데기 뿐인 폼에 실제 바인딩될 데이터를 넣고 화면에 뿌려지게 하겠습니다.

**WelcomeView.vue**

```
<template>
  <v-layout>
    <v-flex xs12>
      <v-list two-line>
        <template v-for="(td, i) in todos">
          <v-list-tile
              :key="td.title"
              avatar
              @click=""
          >
            <v-list-tile-avatar>
              <v-icon :color="td.done ? 'success' : 'secondary'">{{td.done ? 'check' : 'hourglass_empty'}}</v-icon>
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title>
                <v-icon>
                  {{types[td.type].icon}}
                </v-icon>
                <span>
                  {{td.title}}
                </span>
                <small class="text--primary">
                  기한: {{fromNow(td.t)}}
                </small>
              </v-list-tile-title>
              <v-list-tile-sub-title>
                <span>
                  {{td.content}}
                </span>
                <small class="text--primary">
                  작성: {{fromNow(td.rt)}}
                </small>
              </v-list-tile-sub-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">info</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>

        </template>
      </v-list>
    </v-flex>
    <v-btn
        color="primary"
        dark
        fab
        fixed
        bottom
        right
        @click="openDialog"
    >
      <v-icon>edit</v-icon>
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="500"
    >

      <v-card>
        <v-card-title>
          <span class="headline">할일 작성</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid">
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field label="제목" required counter="40" prepend-icon="title" v-model="form.title"></v-text-field>
                </v-flex>
                <v-flex xs12>
                  <v-text-field label="내용" hint="굳이 넣고 싶다면.." counter="40" prepend-icon="note" v-model="form.content"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuDate"
                      :close-on-content-click="false"
                      v-model="menuDate"
                      :nudge-right="40"
                      :return-value.sync="date"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="date"
                        label="목표 날짜"
                        prepend-icon="event"
                        readonly
                    ></v-text-field>
                    <v-date-picker
                        v-model="date"
                        locale="ko"
                        @input="$refs.menuDate.save(date)"
                    ></v-date-picker>
                  </v-menu>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuTime"
                      :close-on-content-click="false"
                      v-model="menuTime"
                      :nudge-right="40"
                      :return-value.sync="time"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      max-width="290px"
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="time"
                        label="목표 시간"
                        prepend-icon="access_time"
                        readonly
                    ></v-text-field>
                    <v-time-picker
                        v-if="menuTime"
                        v-model="time"
                        locale="ko"
                        @change="$refs.menuTime.save(time)"
                    ></v-time-picker>
                  </v-menu>
                </v-flex>

                <v-flex xs12 sm6>
                  <v-select
                      :items="types"
                      label="종류"
                      v-model="form.type"
                      item-text="text"
                      item-value="value"
                      prepend-icon="map"
                      required
                  ></v-select>
                </v-flex>

              </v-layout>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click.native="save()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar
        :timeout="snackbar.timeOut"
        v-model="snackbar.act"
        :color="snackbar.color"
    >
      {{ snackbar.text }}
      <v-spacer></v-spacer>
      <v-btn flat color="white" @click.native="snackbar.act = false">닫기</v-btn>
    </v-snackbar>
  </v-layout>
</template>

<script>
  export default {
    name: 'welcome',
    data () {
      return {
        todos: [],
        form: {
          rt: new Date(),
          t: new Date(),
          type: 0,
          title: '',
          content: '',
          done: false
        },
        menuDate: false,
        menuTime: false,
        date: null,
        time: null,
        valid: false,
        dialog: false,
        types: [
          { value: 0, text: '집', icon: 'home' },
          { value: 1, text: '회사', icon: 'store' },
          { value: 2, text: '밖', icon: 'timeline' }
        ],
        snackbar: {
          act: false,
          text: '',
          color: 'success',
          timeOut: 5000
        }
      }
    },
    computed: {
      dt2date () {
        if (!this.date || !this.time) return null
        return this.$moment(this.date + ' ' + this.time).toDate()
      }
    },
    methods: {
      pop (msg, cl, t) {
        this.snackbar.act = true
        this.snackbar.text = msg
        this.snackbar.color = cl
        this.snackbar.timeOut = t
      },
      save () {
        this.dialog = false
        const td = {
          rt: new Date(),
          t: this.dt2date,
          type: this.form.type,
          title: this.form.title,
          content: this.form.content,
          done: false
        }
        this.todos.push(td)
        this.pop('잘 등록 되었습니다.', 'success', 5000)
      },
      fromNow (rt) {
        return this.$moment(rt).fromNow()
      },
      openDialog () {
        this.$refs.form.reset()
        this.dialog = true
      }
    }
  }
</script>

<style scoped>
</style>
```

## 시연 영상

미완성이지만 대충 어떤 느낌인지 영상으로 보는 게 나을 것 같아서 영상을 넣었습니다.(*대충 보세요*)

<iframe src="https://www.youtube-nocookie.com/embed/Xbuijv2lHk0" frameborder="0" allowfullscreen="" id="fitvid0" style="box-sizing: border-box; position: absolute; top: 0px; left: 0px; width: 636px; height: 357.75px;"></iframe>

## 설명

- 데이터를 보여주기 위해 리스트에 지저분하게 많이 넣은 것입니다.
  리스트는 심플 이즈 베스트이기 때문에 데이터 확인되면 딱 필요한 데이터만 넣는 게 좋습니다.
- v-model로 각 폼요소에 데이터들을 바인딩 시켰습니다. form.x는 이제 화면과 같이 놉니다.
- computed 라는 것이 있는데요 위 처럼 따로 변수를 쓰고 싶지 않으면서 뭔가 조합을 해줄 때 사용하면 됩니다.
  https://kr.vuejs.org/v2/guide/computed.html
- ref 활용
  위에서 언급한 참조를 dialog를 열때 폼을 초기화 해주는데 사용했습니다.
  form.title = ‘’ 이런식으로 초기화 할 수도 있지만 유효성 판단 넣어주면 에러난 것 처럼 보여지기 때문입니다. 초기화를 위해 fab 버튼을 dialog slot에서 바깥으로 뺐습니다.

```
this.$refs.form.reset()
```

# 디비 연동

이제 CRUD(Create Read Update Delete) 를 해서 어플이 다시 켜져도 데이터가 유지되도록 해보겠습니다.

## database 설치

지난 강좌 참고

## CRUD

약간의 UI 변형과 이해가 쉽도록 메쏘드를 작명해봤습니다.

```
<template>
  <v-layout>
    <v-flex xs12>
      <v-list two-line>
        <template v-for="(td, i) in todos">
          <v-list-tile
              :key="td.title"
              avatar
              @click=""
          >
            <v-list-tile-avatar>
              <v-icon color="info">{{types[td.type].icon}}</v-icon>
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title>
                <span>
                  {{td.title}}
                </span>
                <small class="text--primary">
                  기한: {{fromNow(td.t)}}
                </small>
              </v-list-tile-title>
              <v-list-tile-sub-title>
                <span>
                  {{td.content}}
                </span>
                <small class="text--primary">
                  작성: {{fromNow(td.rt)}}
                </small>
              </v-list-tile-sub-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-btn icon ripple @click="dbUpdate(td)">
                <v-icon :color="td.done ? 'success' : 'secondary'">check</v-icon>
              </v-btn>
              <v-btn icon ripple @click="dbDelete(td._id)">
                <v-icon color="error">delete</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>

        </template>
      </v-list>
    </v-flex>
    <v-btn
        color="pink"
        dark
        fab
        fixed
        bottom
        right
        @click="openDialog"
    >
      <v-icon>edit</v-icon>
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="500"
    >

      <v-card>
        <v-card-title>
          <span class="headline">할일 작성</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid">
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field label="제목" required counter="40" prepend-icon="title" v-model="form.title"></v-text-field>
                </v-flex>
                <v-flex xs12>
                  <v-text-field label="내용" hint="굳이 넣고 싶다면.." counter="40" prepend-icon="note" v-model="form.content"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuDate"
                      :close-on-content-click="false"
                      v-model="menuDate"
                      :nudge-right="40"
                      :return-value.sync="date"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="date"
                        label="목표 날짜"
                        prepend-icon="event"
                        readonly
                    ></v-text-field>
                    <v-date-picker
                        v-model="date"
                        locale="ko"
                        @input="$refs.menuDate.save(date)"
                    ></v-date-picker>
                  </v-menu>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-menu
                      ref="menuTime"
                      :close-on-content-click="false"
                      v-model="menuTime"
                      :nudge-right="40"
                      :return-value.sync="time"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      max-width="290px"
                      min-width="290px"
                  >
                    <v-text-field
                        slot="activator"
                        v-model="time"
                        label="목표 시간"
                        prepend-icon="access_time"
                        readonly
                    ></v-text-field>
                    <v-time-picker
                        v-if="menuTime"
                        v-model="time"
                        locale="ko"
                        @change="$refs.menuTime.save(time)"
                    ></v-time-picker>
                  </v-menu>
                </v-flex>

                <v-flex xs12 sm6>
                  <v-select
                      :items="types"
                      label="종류"
                      v-model="form.type"
                      item-text="text"
                      item-value="value"
                      prepend-icon="map"
                      required
                  ></v-select>
                </v-flex>

              </v-layout>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="dialog = false">취소</v-btn>
          <v-btn color="blue darken-1" flat @click.native="dbCreate()">저장</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar
        :timeout="snackbar.timeOut"
        v-model="snackbar.act"
        :color="snackbar.color"
    >
      {{ snackbar.text }}
      <v-spacer></v-spacer>
      <v-btn flat color="white" @click.native="snackbar.act = false">닫기</v-btn>
    </v-snackbar>
  </v-layout>
</template>

<script>
  export default {
    name: 'welcome',
    data () {
      return {
        todos: [],
        form: {
          rt: new Date(),
          t: new Date(),
          type: 0,
          title: '',
          content: '',
          done: false
        },
        menuDate: false,
        menuTime: false,
        date: null,
        time: null,
        valid: false,
        dialog: false,
        types: [
          { value: 0, text: '집', icon: 'home' },
          { value: 1, text: '회사', icon: 'store' },
          { value: 2, text: '밖', icon: 'timeline' }
        ],
        snackbar: {
          act: false,
          text: '',
          color: 'success',
          timeOut: 5000
        }
      }
    },
    mounted () {
      this.dbRead()
    },
    computed: {
      dt2date () {
        if (!this.date || !this.time) return null
        return this.$moment(this.date + ' ' + this.time).toDate()
      }
    },
    methods: {
      pop (msg, cl, t) {
        this.snackbar.act = true
        this.snackbar.text = msg
        this.snackbar.color = cl
        this.snackbar.timeOut = t
      },
      fromNow (rt) {
        return this.$moment(rt).fromNow()
      },
      openDialog () {
        this.$refs.form.reset()
        this.dialog = true
      },
      dbCreate () {
        this.dialog = false
        const td = {
          rt: new Date(),
          t: this.dt2date,
          type: this.form.type,
          title: this.form.title,
          content: this.form.content,
          done: false
        }
        if (!this.$refs.form.validate()) return this.pop('값이 유효하지 않습니다', 'error', 60000)
        this.$db.insert(td, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.dbRead()
          this.pop('저장 성공', 'success', 5000)
        })
      },
      dbRead () {
        this.$db.find({}, (err, rs) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.todos = rs
        })
      },
      dbUpdate (td) {
        this.$db.update({ _id: td._id }, { $set: { done: !td.done } }, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.dbRead()
        })
      },
      dbDelete (_id) {
        this.$db.remove({ _id }, (err) => {
          if (err) return this.pop(err.message, 'error', 60000)
          this.dbRead()
          this.pop('삭제 성공', 'success', 5000)
        })
      }
    }
  }
</script>

<style scoped>
</style>
```

## 시연 영상

CRUD가 잘 되는 지 영상으로 확인해보세요.(*대충 보세요*)

<iframe src="https://www.youtube-nocookie.com/embed/Ad_PMssKw44" frameborder="0" allowfullscreen="" id="fitvid1" style="box-sizing: border-box; position: absolute; top: 0px; left: 0px; width: 636px; height: 357.75px;"></iframe>

## 설명

CRUD를 보여주기 위해 직접 디비 핸들링을하고 읽고 있지만.. 사실 권장사항은 한번 받고 내부적으로 처리하다 변경사항만 패치하는 것이 좋습니다.

1000개의 할일이 있다면 매번 1000개의 할일을 읽어오는 구조입니다.

- dbCreate(): 기존 save() 를 dbCreate()로 변경했습니다.
  쓰고 읽어봅니다.
- dbRead(): 조건이 비었기 때문에 다 읽어옵니다.
- dbUpdate(): item 하나를 인자로 넘겨서 done 만 토글시킵니다.
- dbDelete(): { _id } 는 { _id: _id }와 같습니다. 해당 _id만 찾아서 지우는 것이죠..

CRUD 작업은 허무하지만 이게 다네요

title 이나 content 수정은 직접 dialog 개조해서 테스트 해보시기 바랍니다.

# 빌드

꼭 appId를 유니크하게 변경해야합니다.

저번에 변경 안했더니 기존 설치 앱이 지워져버리고 만든게 깔리더라고요..

**package.json**

```
"build": {
    "productName": "todo",
    "appId": "com.memi.todo",
```

전 대충 이렇게 변경했습니다.

```
$ yarn build
```

# 원본 소스

https://github.com/fkkmemi/todo

# 결론

이것으로 일렉트론뷰 강좌를 마치겠습니다..

정말 미칠듯한 4시간의 작성이었네요…

좌모니터는 글작성

중모니터는 코딩

우모니터는 결과물 확인

뭐 하다보니 재밌어서 더 진행하고 싶긴한데.. 2부로 넘어갈까 하다가.. 적당한 숙제로 남겨놓고 떠납니다.(*다음에는 모바일이 출격 준비중입니다.*)

우아하게 만들기로 제목 지어놓고 우아하지 못한 비쥬얼 죄송합니다…

하지만 머터리얼디자인(https://material.io/design/)으로 구현되어 있기 때문에..

잘만 배치하면 절대 구닥다리 소리는 듣지 않을 겁니다..

어떤 분들이 가끔 초고수라고 말씀해주시는데..

그건 이쪽 생태계 종사하시는 분들을 약간은 모욕하는 평가가 될 수 있습니다..

진정한 고수들은 이미 현업에서 더 복잡하게 구현해서 개발중입니다..

고수가 작성한 강좌도 아니고.. 사실 고수가 되고 싶지도 않고요..

오히려 초보개발자가 ‘처음 접하는 사람도 쉽게 만들 수 있다~’ 를 검증하고 나누고 싶을 뿐입니다..

그 중.. 직접 제가 쓰면서 찾아보면서 개발 하기 때문에…

어느 타이밍에 구글링을 하는 지 레퍼런스 사이트를 얼마나 잘 이용하는지를 알려드리는 게 주 목적입니다.

강좌 보신분들은 느끼겠지만.. 거의 대부분의 참고는 공식사이트 3개입니다.

vuetify, vue, electron 죄다 한글이라 정말 좋습니다.

특히 요새 공식사이트의 예제는 절대 무시하면 안됩니다.(*예전에는 진짜 참고만 했었죠 쓸만한 코드가 없어서*)

나름 엄격한 룰을 적용한 샘플코드들입니다. 기이한 습관 지저분한 배치 같은 게 없는 아주 퓨어하면서도 권장 소스니 잘 배껴쓰시기 바랍니다.

# 커뮤니티 공개

클리앙 팁과강좌

https://www.clien.net/service/board/lecture/12345695



## [5. vue-cli3 업그레이드](https://fkkmemi.github.io/electron/electron-05-vuecli3-update/)

ue-cli3가 업데이트 되어서 기존 설치법(vue-cli2)이 아예 막혀버렸습니다.

> 기존 설치법 지워짐: https://github.com/vuetifyjs/electron

물론 기존 설치법으로도 방법은 있지만 새로운 방법으로 정상적으로 수행되게 만들어보겠습니다.

# vue-cli3 란

vue-cli 라는 것 자체가 뷰로 새로운 프로젝트를 만들어주고 관리할 수 있는 터미널 툴이라고 할 수 있는데요.

2에서 3버전으로 바뀌면서 너무 확 바껴버렸죠..

> USB-C 타입만 달려서 제작년 출시한 맥북 느낌이었습니다.(아직 준비 안되었는데…)

과감하게 바꾼 이유는 희생을 감수하더라도 편의와 효율을 생각했기 때문일 것 같습니다.

# 2 and 3 차이

자세히는 모르지만 간략하게 설명해보면

## vue-cli2

```
$ vue init 어느리포/어느소스 내프로젝트
```

소스 별로 각각 다른 설치 요소들을 가지고 있습니다.

문제는 예를 들어 린트 종류를 고르는 선택문이 일렉트론을 설치할 때도 뷰티파이를 설치할 때도 물어보는 것입니다.

## vue-cli3

```
$ vue create 내프로젝트
```

자질구레한 설정을 깔끔하게 다 내포하고 있습니다.

웹팩 린트 바벨등 신경쓰지 않아도 알아서 다 깔립니다.

> 생산성이 강조되는 시대에 바벨 웹팩 설정 공부하는 시간은 더이상 필요 없는 것이죠.

기본적인게 다 깔리고 플러그인 형태로 설치되는 것입니다.

문제는 핫한 플랫폼이 아니면 아직 플러그인이 없다는 것입니다..

다행히도 일렉트론과 뷰티파이는 핫하기 때문에 있습니다.

```
$ vue add electron-builder
$ vue add vuetify
```

일렉트론 빌더 참고: https://www.npmjs.com/package/vue-cli-plugin-electron-builder

# 설치하기

## 프로젝트 생성하기

```
$ vue create el

Vue CLI v3.2.1
? Please pick a preset: Manually select features
? Check the features needed for your project: Babel, Router, Vuex, Linter
? Use history mode for router? (Requires proper server setup for index fallback 
in production) Yes
? Pick a linter / formatter config: Standard
? Pick additional lint features: Lint on save
? Where do you prefer placing config for Babel, PostCSS, ESLint, etc.? In dedica
ted config files
? Save this as a preset for future projects? No


Vue CLI v3.2.1
✨  Creating project in /Users/fkkmemi/tmp/el.
🗃  Initializing git repository...
⚙  Installing CLI plugins. This might take a while...

yarn install v1.12.3
info No lockfile found.
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...

success Saved lockfile.
✨  Done in 14.71s.
🚀  Invoking generators...
📦  Installing additional dependencies...

yarn install v1.12.3
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...

success Saved lockfile.
✨  Done in 5.60s.
⚓  Running completion hooks...

📄  Generating README.md...

🎉  Successfully created project el.
👉  Get started with the following commands:

 $ cd el
 $ yarn serve
```

Router, Vuex 설치하고 eslint standard로 설정해서 만들었습니다.

여기까지 하면 뷰 기본화면이 뜹니다.

## 일렉트론 설치하기

```
$ cd el
$ vue add electron-builder

📦  Installing vue-cli-plugin-electron-builder...

yarn add v1.12.3
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
success Saved 139 new dependencies.
info Direct dependencies
└─ vue-cli-plugin-electron-builder@1.0.0-rc.10
info All dependencies
├─ align-text@0.1.4
├─ amdefine@1.0.1
# ..
├─ xmldom@0.1.27
├─ yaku@0.16.7
├─ yauzl@2.4.1
└─ zip-stream@1.2.0
✨  Done in 7.29s.
✔  Successfully installed plugin: vue-cli-plugin-electron-builder

? Choose Electron Version ^3.0.0

🚀  Invoking generator for vue-cli-plugin-electron-builder...
📦  Installing additional dependencies...

yarn install v1.12.3
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
$ electron-builder install-app-deps
  • electron-builder version=20.38.4
  • no native production dependencies
✨  Done in 8.49s.
⚓  Running completion hooks...

✔  Successfully invoked generator for plugin: vue-cli-plugin-electron-builder
   The following files have been updated / added:

     src/background.js
     .gitignore
     package.json
     yarn.lock

   You should review these changes with git diff and commit them.
```

별도의 선택 없이 그대로 깔립니다.

## 뷰티파이 설치하기

```
$ vue add vuetify

📦  Installing vue-cli-plugin-vuetify...

yarn add v1.12.3
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
success Saved 1 new dependency.
info Direct dependencies
└─ vue-cli-plugin-vuetify@0.4.6
info All dependencies
└─ vue-cli-plugin-vuetify@0.4.6
$ electron-builder install-app-deps
  • electron-builder version=20.38.4
  • no native production dependencies
✨  Done in 5.89s.
✔  Successfully installed plugin: vue-cli-plugin-vuetify

? Choose a preset: Configure (advanced)
? Use a pre-made template? (will replace App.vue and HelloWorld.vue) Yes
? Use custom theme? No
? Use custom properties (CSS variables)? No
? Select icon font Material Icons
? Use fonts as a dependency (for Electron or offline)? Yes
? Use a-la-carte components? Yes
? Select locale English

🚀  Invoking generator for vue-cli-plugin-vuetify...
📦  Installing additional dependencies...

yarn install v1.12.3
[1/4] 🔍  Resolving packages...
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
$ electron-builder install-app-deps
  • electron-builder version=20.38.4
  • no native production dependencies
✨  Done in 8.21s.
⚓  Running completion hooks...

✔  Successfully invoked generator for plugin: vue-cli-plugin-vuetify
   The following files have been updated / added:

     src/assets/logo.svg
     src/background.js
     src/plugins/vuetify.js
     .gitignore
     package.json
     src/App.vue
     src/components/HelloWorld.vue
     src/main.js
     src/views/Home.vue
     yarn.lock

   You should review these changes with git diff and commit them.
```

일렉트론을 위해 오프라인 폰트저장하는 것만 선택해주고 나머지는 기본값 처리하였습니다.

# 실행하기

## 개발용

```
$ yarn electron:serve
```

## 배포용

```
$ yarn electron:build
```

# 결과

![alt result](https://fkkmemi.github.io/images/electron/2018-12-27_10.01.20.png)

# 마치며

[지난 글](https://fkkmemi.github.io/electron/electron-04-vuecli3/)

이미 지난번에 포스팅 해두었는데 깜박하고 글을 또 썼네요..

뭐.. 둘 다 참고하세요~