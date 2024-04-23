# React + Springboot 게시판 만들기

출처: https://m.blog.naver.com/rudnfskf2/222148407272



## 1 : React 프로젝트 생성 및 개발 환경설정

https://ko.reactjs.org/docs/getting-started.html

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Freactjs.org%2Flogo-og.png%22&type=ff500_300)](https://ko.reactjs.org/docs/getting-started.html)

**시작하기 – React**

프론트엔드 프레임워크를 각잡고 공부한 경험도 없고, 난 이해력도 부족하니까

공부한 내용을 이해 했는지 확인 할 겸 게시판을 만들어 보려한다.

따라서 아래에 포스팅의 설명은 **정확하지 못할 가능성이 높음.**



### 1.0 개요 

이번 포스팅에서는 리엑트 프로젝트를 생성하고, 환경설정 하는법에 대해서 포스팅하려한다.



### 1.1. 개발환경 설정

 개발환경은 Windows10에서 VS Code를 사용했다.



#### 1.1.1 ※ Vs Code 설치

아직 VS Code가 설치 되어있지 않다면 아래 URL에서 VS Code를 설치.

https://code.visualstudio.com/

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fcode.visualstudio.com%2Fopengraphimg%2Fopengraph-home.png%22&type=ff500_300)](https://code.visualstudio.com/)

**Visual Studio Code - Code Editing. Redefined**

Visual Studio Code is a code editor redefined and optimized for building and debugging modern web and cloud applications. Visual Studio Code is free and available on your favorite platform - Linux, macOS, and Windows.

code.visualstudio.com

#### 1.1.2 ※ Node.js설치 

 React를 개발하기 위해선 Node.js가 반드시 필요하다.



**왜 필요할까?**

 \- 크롬,IE와 같은 웹 브라우져가 아닌 환경에서도 Java Script를 사용할수 있게해줌.

 \- 패키지 관리를 위한 npm사용. (리엑트 자체도 하나의 패키지)



아직 설치가 되어있지 않다면 아래의 URL에서 LTS버젼을 다운받아 설치(설치는 Next버튼만 눌러줘도 괜찮음)

https://nodejs.org/ko/download/

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fnodejs.org%2Fstatic%2Fimages%2Flogo-hexagon-card.png%22&type=ff120)](https://nodejs.org/ko/download/)

 

**다운로드 | Node.js**

Node.js® is a JavaScript runtime built on Chrome's V8 JavaScript engine.

nodejs.org

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMjM1/MDAxNjA1NjYyNzEyODcx.SUyBUMjUIwqpqKfxp9uUVHZi7ttVCRNc2Ti_KJtjGxsg.lzMnMOoyQUchOkXgtoTgHiHz39Ch_U81WOL2mp2pFbUg.PNG.rudnfskf2/01_nodejs_install.PNG?type=w800)

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMjAg/MDAxNjA1NjYyOTQ2NzE1.SjjlA_xIWqG3Uh4QVrP7-6oTSlcSoocOUQhJKywFUbAg.xKPRFkt1AHLGi4ndT4vHgZAVokzoxY0Lr-sfs_VS7mYg.PNG.rudnfskf2/01_nodejs_install_02.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMTEx/MDAxNjA1NjYyOTQ2NzYx.XpZTwwM5QEyR-76F8iAjDlvF900Px-ZYGJFZkudfU2Yg.lQPE6kgCEWXI3UEF6bX61DrTDSYSG2E7GFsdeChfdCAg.PNG.rudnfskf2/01_nodejs_install_03.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMjY0/MDAxNjA1NjYyOTQ2Nzg1.Dxbk1hQPCyGmH425ZpJQOngcsQP4C4lKvNgVBF3zzU0g.1_Z6hIRNb93_-SFj1IbtbVYRzTmFJEltCt_makQm7kUg.PNG.rudnfskf2/01_nodejs_install_04.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMjk2/MDAxNjA1NjYyOTQ2Nzg5.a9rUcTbw_RGOBjDZ7ofPhgo0_-vEx5bK1VauT3X0tGgg.yfwPIxhX1GgYWY6IkIZ2cmOnk4bSIXRAJLmaFj3jgAsg.PNG.rudnfskf2/01_nodejs_install_05.PNG?type=w80_blur)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfNDgg/MDAxNjA1NjYyOTQ2Nzk5.pUkVo5yH5FtB25qSaDfUDElaGze1az_TwqPaOyL6-Cgg.TDxY0R6Cb6oDSb0qwCkSMHai8mAwXYGfH-GwEDIVltQg.PNG.rudnfskf2/01_nodejs_install_06.PNG?type=w80_blur)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMzUg/MDAxNjA1NjYyOTQ2ODAy.I0hcEn0NiB_2WZsNy4YsAbNoYoGN3rhFiG6jgABSasIg.7Lv7wmfWfek6WoazCBONF5CKQof-hT6xNY3rDCVdqWMg.PNG.rudnfskf2/01_nodejs_install_07.PNG?type=w80_blur)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfMTcg/MDAxNjA1NjYyOTQ3MDM3.djBIOyasmwzcozzf6Zpy6_Y0HfIpOm0sfzgwvq6dFlAg.JMinhxXa6fovtuxzwSGjOQuIzSr8b4unTTuF3loVPtcg.PNG.rudnfskf2/01_nodejs_install_08.PNG?type=w80_blur)

Previous imageNext image

\- 설치가 완료 되었는지 cmd 창을 열어서 아래의 커맨드를 입력해 node.js의 버젼이 출력된다면 정상설치된것.

node -v

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMThfNjUg/MDAxNjA1NjYzMDc4NDM4.CafRNeFK55-fi57HOdKQMfwHASL3pJ9NpImekQzcdlcg.X1JscUtQYj6imqWX2bpo2XhdVLIqEA8gOkryI2sjgIsg.PNG.rudnfskf2/00_node.PNG?type=w800)



#### 1.1.3 ※ 리엑트 프로젝트 생성하기

\- VS Code를 실행한 후 상단의 'File' -> 'Open Folder...' 를 클릭.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjk5/MDAxNjA1NzQyMTI5OTA3.n2Acnjk1vCesC2I-BhDKPoi8AkH1WC0MYUPXHi9Slvog.awxhyn-M16XogXQwkWjYUko9qkauSCYvNKf4DbQTjDIg.PNG.rudnfskf2/00_new_folder1.PNG?type=w800)

\- 리액트 프로젝트를 생성할 폴더를 만들고 선택하기.( board-front폴더 선택)

★**이 시리즈에서는 ./board-fullstack이라는 폴더하에** 

 **- board-front폴더에는 리액트 프로젝트, board-back에는 springboot프로젝트가 위치함.**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfODUg/MDAxNjA1NzQxOTE1NDM1.AHUeK9b3bFdiKfY2xp1k5QtoLpNASomOHeO6cXnfOx0g.3Mll-8h1YVdpxBdcGvkUW0Q-y1TVFocGsTsmeMCt748g.PNG.rudnfskf2/00_new_folder.PNG?type=w800)

\- VS Code의 하단에 위치한 TERMINAL 에서 아래의 명령어를 실행하여 리엑트 프로젝트를 생성한다.

\# 프로젝트 경로로 이동; cd {프로젝트경로} cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front # 리엑트 프로젝트 생성; npx create-react-app {생성할 프로젝트명} npx create-react-app board-app

\- 프로젝트 생성시 아래와 같은 메시지가 출력된다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfNTYg/MDAxNjA1NzQyMjk5MzQx.9oMtcqroHGJOD7O7Ip7i2Y_qwwyXtMdVefQcvC9thmwg.6rvVJQUPwKkOQatGkO7JOSO2Iuqb6mN8IBB5KD3MdcEg.PNG.rudnfskf2/01_createProject.PNG?type=w800)

\- 혹시 VS Code를 실행하고 하단에 TERMINAL이 없다면 상단의 Terminal -> New Terminal로 터미널을 열어주면됨.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMTE5/MDAxNjA1NzQyMjg4NTYx.5tLH_F79M-qyKw0LcBIT_SKiapMLQr6k3f-lfuY8LHcg.MNCpDZYuMZfyeaEHY9plW1av4_Walx4_sQijCx67vvMg.PNG.rudnfskf2/SE-5c4936db-b23f-459a-849d-90eb07d708cd.png?type=w800)

\- 리엑트 프로젝트가 문제없이 설치 되었다면 아래와 같은 메시지를 출력한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjM1/MDAxNjA1NzQyNzU1Mzc4.iaNv-yoiaPUBkzpHtRtoEWvUC92Ft_EsnXMTc9ls-n8g.vQc303t0Oh_p226h8EZJF8-bn9U3sSPgP0TS4xx5-Tcg.PNG.rudnfskf2/02_startRunProject_01.PNG?type=w800)



#### 1.1.4 ※ 리엑트 프로젝트 실행해보기

 \- 설치 완료후메시지에도 나와 있는대로 아래 명령어로 리엑트 어플리케이션을 실행해볼 수 있다.

\# 프로젝트 경로로 이동; cd {프로젝트경로} cd board-app # 절대경로라면; cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front\board-app # 실행하기 npm start

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfODkg/MDAxNjA1NzQzMDE2MTIy.npFG7asoJfJ2qGc8dmJbL772UykLvS9pPeUdcmFzL3Yg.JSIcaaKNun_zb_cPoB5nAJRcpw7VXAS7ig0D8BIczdEg.PNG.rudnfskf2/02_startRunProject_02.PNG?type=w800)

\- 프로젝트 실행시에 아래와 같은 메시지를 출력함.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjM0/MDAxNjA1NzQzMDI1ODI2.2Idrf8M5Dp8LIAixqlvxjeux0cKTEc3I_bmAiK8SNIgg.JT3LblGanTy2OCGRFNv__bQ-g3b7UTjeRbHlRwEkW3Qg.PNG.rudnfskf2/02_startRunProject_03.PNG?type=w800)

\- 정상 실행된다면 브라우져를 열어서 리엑트 어플리케이션이 실행된것을 확인 할수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfNTQg/MDAxNjA1NzQzMjU4MTEy.zn6-eP7JFJRnfgyXkilDY1NpSys8uHHWRuQTcaeRaqYg.12bmyBwTUhUtFIDIod4wrBoSXM-nIhslOk_LSphe69og.PNG.rudnfskf2/02_react-app_create_04.PNG?type=w800)

**★실행중에 아래와 같은 경고창이 출력된다면 '엑세스 허용'을 클릭해주면됨.**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMzgg/MDAxNjA1NzQzMjM1NzEw.1JxMdV8SoSnzTS3XZb-0krKBlZNE3yjxwnvKXvHDwQgg.vljBoGVRJa_vOJuc7CZ1R4CUK8_Eiu8kKJSzb0LRtfYg.PNG.rudnfskf2/02_react-app_create_03.PNG?type=w800)



여기까지 완료했다면 리엑트 개발을 할 수 있는 환경은 갖추어 진것.



### 1.2 디렉토리와 파일설명 

\- VS Code를 열어서 리엑트 프로젝트를 확인해보면 아래와 같은 형태로 되어있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMTg5/MDAxNjA1NzQzNzQwNDIx._F7AXL98u0MMiUiBoe1ZaajoxVkLkyuKNHMbzW5_x7Qg.yHDbmPmdpszvU7w_5rT6mBwJnDWpFeTMq_SjoEVWjuwg.PNG.rudnfskf2/02_startRunProject_05.PNG?type=w800)



#### 1.2.1 node_modules 디렉토리

  -> 리엑트 앱이 가지는 의존성 라이브러리 파일들이 위치함. (내부적으로 상당히 많은 의존성을 가지고 있는것을 알 수 있음)



#### 1.2.2 public 

 -> 정적파일이 위치함. 

  (1) index.html  : 컴포넌트의 내용이 렌더링되는 곳.

 

#### 1.2.3 src 

 -> **소스코드를 작성하는 디렉토리** 중요한 파일이 모여져 있음.

 (1) App.js : public/index.html에 렌더링할 내용을 기술하는 곳

 (2) index.js : App.js에 작성된 내용을 public/index.html의 특정 dom(id='root')에 매핑 시켜준다. 



#### 1.2.4 package.json 

  -> 리액트의 버젼 , 의존성등이 기재되어 있는 파일. 

   패키지를 추가하면 추가한 패키지의 내용이 이 파일에 기재됨.



### 1.3. Hello World 띄워보기 

\- src/App.js에 아래와 같이 코드를 변경해보자.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjI4/MDAxNjA1NzQ0ODk4OTYy.Aox9nkzkqh-wOG3rdlLE8n484iKRqpRyR9lFWeSeKh4g.YkQ-_qMzco8klGgHqDMLPL_Cbd4cBREjIrQp_YD4awgg.PNG.rudnfskf2/SE-f760a22d-97da-4995-8c77-cbf4de35fc36.png?type=w800)

\- 브라우져에서 Hello World가 출력된 것을 확인 할 수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjE0/MDAxNjA1NzQ0OTQ1NzQx.hECoc9gFUvZJTGeo4uTu4TIm9Gx5HznMjfiuijLZEAMg.4bcFbOaymjPod4YCnv-Nzu8nEBPLWOfsBfp5AqejfRUg.PNG.rudnfskf2/02_react-app_create_06.PNG?type=w800)



### 1.4. 패키지를 추가해보기

 #### 1.4.1 부트스트랩 패키지를 설치

이 시리즈에서의 디자인은 부트스트랩을 사용할 것이므로 패키지를 설치해준다.



패키지 설치가 아닌 CDN을 사용하고자 한다면 public/index.html의 head태그에 아래의 cdn을 추가해준다.

**★ cdn이 갱신될 수도 있으니 구글링을 하셔서 최신버젼을 사용할 것.(아래는 참고만)**

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">



->  터미널에서 아래 명령어로 패키지 설치가 가능하다.

\# 프로젝트 디렉토리로 이동 cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front\board-app # 패키지 설치 npm install bootstrap --save

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfNCAg/MDAxNjA1NzQ1MjQyNDk1.vOZx169W8UzwGa6lnsbSixzBjprif5o3kQSVA5pnqxgg.I-LuwOmezG45GUQ10SdP0NDd1CSoHSUKEhhoCm0mB4wg.PNG.rudnfskf2/03_addingBootstrap_02.PNG?type=w800)

\- 설치가 완료되면 아래와 같은 메시지가 출력됨.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMTgz/MDAxNjA1NzQ1MjU1MjQ4.rxQKhnmLaiwA1dNIVw7mJqDLLl7fZMPZqXpbt4CXypog.829OM7DvXqPn-NHn_onNznqDhovflcX7HXBiUv_GtdAg.PNG.rudnfskf2/03_addingBootstrap_03.PNG?type=w800)

\- 정상설치가 되었다면 package.json에 부트스트랩 패키지가 추가된것을 알 수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMyAg/MDAxNjA1NzQ1ODY4Nzcw.uQSn9_nLDsSh9ViUH45UrbnTT-sWmMRU0fDpNesLGQIg.WsCUlcl6vSVWEzdExNHr9932B4tHty15GeJiEeCHI00g.PNG.rudnfskf2/03_addingBootstrap_04.PNG?type=w800)

#### 1.4.2 axios패키지 설치

react와 spring boot api의 통신은 axios를 사용할 것이므로  패키지를 설치해준다.



->  터미널에서 아래 명령어로 패키지 설치가 가능하다.

\# 프로젝트 디렉토리로 이동 cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front\board-app # 패키지 설치 npm install axios --save

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMTAg/MDAxNjA1NzQ1NDY5NDk1.mpx6CT6LRuR3yZ9IkY7434ik_XR8q85w6rMj3yZbKAgg.zNDchNnMoRdozPTXo4R5CI2BSZrtvJ4Eq0_4M74mnK8g.PNG.rudnfskf2/05_addingBootstrap_01.PNG?type=w800)

\- 정상설치가 되었다면 package.json에 axios패키지가 추가된 것을 알 수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjU5/MDAxNjA1NzQ1NzkwNjE3.ELqOw97hlc_5_JxDEhT_zBRmAYrkc2BPnxzYtZ12jI8g.Z28wKpf4bKKu-ASIKQr2auSUMfYSI_nx4MsiCoAb-9Ag.PNG.rudnfskf2/05_addingBootstrap_02.PNG?type=w800)

#### 1.4.3 react-route-dom 패키지 설치

각 페이지의 구분을 react-route-dom을 사용할 것이므로 패키지를 설치해준다.



->  터미널에서 아래 명령어로 패키지 설치가 가능하다.

\# 프로젝트 디렉토리로 이동 cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front\board-app # 패키지 설치 npm install react-router-dom --save

\- 정상설치가 되었다면 package.json에  react-route-dom패키지가 추가된 것을 알 수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfNTgg/MDAxNjA4NTk5NDM3MDI2.YtZVJk1Kvgg4gajJwhWysJI3GlF7cq5O30oEFcnwE1Mg.8rGaKKu6uhlqsRajrVEgXORfkG136NtBniNZIhzhGrAg.PNG.rudnfskf2/06_route_01.PNG?type=w800)



### 1.4.5. VS Code의 유용한 툴 설치 (선택)

내가 리액트 공부를 하면서 이해한것은 

 **'리엑트 앱 코딩' ==  '상호 작용하는 다수의 컴포넌트를 만들고 재사용 가능하도록 배치하는 것'**이므로 

컴포넌트를 만들때 자동완성 시켜주는 툴이 있으면 작업속도가 빠르고 실수가 없겠다라고 생각했기에,

VS Code 의 컴포넌트 자동완성 툴을 설치해보았다. 



\- 좌측 'Extensions' -> 'react snippets' 검색 -> 설치하기

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMjYw/MDAxNjA1NzQ2MzIzNjgw.5uBPmQ6miAKasN0BMotOhtz6V6NLhvceRHGFf4yh2tkg.xxYe-iuz8uu1xG7J6PUZro7WLI4FqyXNy6nbC1K-39Eg.PNG.rudnfskf2/04_create_list_02.PNG?type=w800)

\- 설치가 완료된후에는 컴포넌트를 작성시 'rcc'만을 입력해도 기본적인 컴포넌트 내용을 자동완성 해준다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMTlfMTE4/MDAxNjA1NzQ2NTE1NTUy.fUo7F_Ly-iijhgmYOnfEyr91x_DKj7ypgPsljQTr6kwg.Gmgc3FR4wHnNQU_Uetcw85dhWTG-KNPlVmCjhbO3uXIg.PNG.rudnfskf2/04_create_list_03.PNG?type=w800)





\-----------------------------------------------------------------------------------------------------------------------

리엑트의 환경설정은 여기까지 다음 포스팅은 **Spring boot api의 설정**이 될 예정이다. 





## 2 : Spring boot 프로젝트 생성 및 개발 환경설정

**개요** 

이번 포스팅에서는 스프링부트 프로젝트를 생성하고, 환경설정 하는법에 대한 포스팅이다.



### 2.1. 개발환경 설정

개발환경은 아래와 같다.

OS : Windows 10

IDE : 이클립스 2018-12 (4.10.0)

Java : 1.8

DB : XAMPP v3.2.3버젼의 MySQL



벡엔드 쪽의 개발환경설정은 미리 정리해둔 포스팅대로 했다.

아래 URL참고

https://blog.naver.com/rudnfskf2/221490709675

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fblogthumb.pstatic.net%2FMjAxOTAzMTdfMTA4%2FMDAxNTUyODMyMTY0MDA5.5Zqh9mtcv9bnZ2nElbOrbi3ucGeekL0eY7bf0cMJ93Ug.E39tn5qtC2NLKF8RXBgHCHAE8ZT18cBGlvqgzwS2aLQg.PNG.rudnfskf2%2F1.PNG%3Ftype%3Dw2%22&type=ff500_300)](https://blog.naver.com/rudnfskf2/221490709675)

#### 2.1.1 개발 환경 설정 (Windows 10 - Java) 01. Open JDK 1.8 설치 및 환경 변수 설정

이번달 전직 축하선물로 새로운 컴퓨터를 장만한 김에 전에 사용하던 개발용 PC에서 설정했었던 내용과, ...

blog.naver.com

https://blog.naver.com/rudnfskf2/221492566262

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fblogthumb.pstatic.net%2FMjAxOTAzMTlfNDkg%2FMDAxNTUzMDA3NDY1NTY1.sjvgLVrYvDXVkHItK2t8xytJZRsYYDCspS9gaD-ImS4g.NMDS6riItd60H00mMlVUviQbl6-V7njjf9mHDDlQyWkg.PNG.rudnfskf2%2F1.PNG%3Ftype%3Dw2%22&type=ff500_300)](https://blog.naver.com/rudnfskf2/221492566262)

#### 2.1.2 개발 환경 설정 (Windows 10 - Java) 02. Eclipse설치 및 git, github연동

이번 포스팅은 Windows 10 운영체제에서 Java개발환경설정의 2번째 Eclipse설치 및 git, github연동이 ...

blog.naver.com

https://blog.naver.com/rudnfskf2/221526085453

[![img](https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fblogthumb.pstatic.net%2FMjAxOTA0MzBfMSAg%2FMDAxNTU2NTg4NTc1MjQ5.mhDfynvMQWpbkPrWE19iLTQHDZz650tonPzExWf1waYg.ZeeeUEdPZr25vraUdkwQFzWTtfP4u8T502CakNPGaW8g.PNG.rudnfskf2%2F0.PNG%3Ftype%3Dw2%22&type=ff500_300)](https://blog.naver.com/rudnfskf2/221526085453)

**0.SpringBoot로 간단한 CRUD 게시판+JPA MySQL연동(환경설정)**

이번 포스트는 스프링 부트를 사용해서 CRUD기능이 제공되는 간단한 게시판을 만드는 과정을 포스팅 하...

blog.naver.com

**※ DB 테이블 생성**

\- xampp control panel에서 MySQL 관리자 페이지에 이동

 **★ xampp의 MySQL관리자 페이지를 사용하기 위해선** **xampp의 Apache도 실행중**이어야한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjI5/MDAxNjA1ODg3MTYzOTQw.LBn9-6cica1A-U_92zLUe7UM1zD4gvYerbvybIKW0s0g.mkf9Hw7h4JtHDpsD5cnqXFZZbSFfEHqwctV0T_6RfjAg.PNG.rudnfskf2/db01.PNG?type=w800)

**- 새로운 데이터베이스를 생성**

 -> 관리자 페이지에서 '데이터 베이스'클릭

 ->'새 데이터 베이스 만들기'에서 원하는 데이터베이스 명 입력  

 -> 만들기 버튼을 클릭



 내 경우 board-back라는 이름의 데이터 베이스를  MySQL에 추가했다. 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfNDAg/MDAxNjA1ODg3MjY1MzMx.cWB9D304pbPYHFc9i7ZXsGb6T3uSUhGDGvfsk110otgg.YSSHVIeCEEoI1J2u6OpdU9vu-HhqxYkDvzwoMz23so4g.PNG.rudnfskf2/SE-d8d0bb8b-aa29-4889-b8f5-66f9bd593380.png?type=w800)

데이터 베이스 목록에 새로 작성한 데이터베이스 'board-back'이 출력되면 성공한것.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjM0/MDAxNjA1ODg3NjQ3MDU3.od9xCpm8hXPPrRNzuykXwhWmbdn8fcOlmTY_x1ePxLAg.jBIWRka6tPEZbmBbwH09gMo1MA2_0zF2_awDuwVDV-kg.PNG.rudnfskf2/03-02.PNG?type=w800)

**- 테이블을 추가**

-> 데이터 베이스 목록에서 새로 작성한 데이터 베이스인 'board-back'을 클릭 

-> '새 테이블 만들기'의 '이름'란에 테이블명을 입력하고 '컬럼수'를 지정후 실행 버튼을 클릭

-> 컬럼의 상세설정내용(자료형, AI, Not Null)등등을 지정한 후 저장버튼클릭 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfNzkg/MDAxNjA1ODg3OTkxNTYz.cli8vNbGE0PgTHFZgLCh4xbp470gQstV-xH51zM19wMg.rP5IUAUlZo5n2TltRPKvRUFZTX8N4Xz-iwUNFJnTCfEg.PNG.rudnfskf2/04.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjE5/MDAxNjA1ODg3OTkxNjkx.JIVG9OHE1vFPOFzrKZEoYvKXcmG_MTRhu0yZi87LkCAg.wzOzMJVEIdvZuivO-L5ptyN18sCexBkgZTFRsJdPq7Mg.PNG.rudnfskf2/05.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTMx/MDAxNjA1ODg3OTkxNjM2.oazJ-Tc_hCiOcg6hakLbPrW5iY2kGJOQmuhbskh-2log.KQdq-91o05dWk2oAZpE2S5XhHfMta99X032SP6eMGs8g.PNG.rudnfskf2/06.PNG?type=w80_blur)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfOSAg/MDAxNjA1ODg3OTkxNjk2.QYQrXCuadQVG6ZgptfPJzqjUNsasuHqWkULFHdJ73c4g.SzdUEoATqGGceIMp3GByQ7wCR9GYzR0tMAoifOVpGfog.PNG.rudnfskf2/07.PNG?type=w80_blur)

Previous imageNext image

아래와 같이 데이터베이스에 테이블이 표시된다면 성공

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjk2/MDAxNjA1ODg4MTQ4MTM0.WlIegvpkR4WODi7QlT2tGGqOLrzQ01ZNsw4rWmVCcxAg.-y4VWUomNUCddAj8JWmr9M5p49xaCxCg5DVhgxvQLfcg.PNG.rudnfskf2/db02.PNG?type=w800)



**※ Spring boot 프로젝트 생성**

이클립스를 실행한 상태에서 

-> 'File'클릭 -> 'New'클릭 -> 'Other...'클릭

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTM3/MDAxNjA1ODg5MDY4OTM2.na-UuRUEjiC8_byE2xfzxOf6YRk-4cP_CK1LC4onpWMg.2IMys-jsuikkaNxLMY09wehG-DtKg1YlnfcCLArF0fwg.PNG.rudnfskf2/00.PNG?type=w800)

-> Spring으로 검색 -> 'Spring Starter Project' 클릭해서 선택 -> 'Next >' 버튼 클릭.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTEw/MDAxNjA1ODg5MDc1ODIx.md4IKWJ5WtkP_v3s3Ly-B3xh86jfesp5N6cx1g8OPVwg.Bbt8gQOnHlLO2wHWHxSYtwrvRSkuuL9vOI05OkPzmNog.PNG.rudnfskf2/00-02.PNG?type=w800)

-> 'board-back'로 프로젝트 설정

Location (: 프로젝트 위치) 설정

(Use default location은 나의경우 리엑트 프로젝트와 같은 폴더 아래로 하나로 묶어 관리하기위해 선택하지 않음.)

Gradle 과 Java의 버젼 설정등의 설정 후 'Next > '버튼을 클릭

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjQx/MDAxNjA1ODg5MDgzNTg3.hlyJA3QkLkrSvhAT5osaIC742kkTCHDyz59scEb3x24g.kTvHb3IopyxQnegA7cP3_5YuISk91-HjWITbY9MHc08g.PNG.rudnfskf2/01.PNG?type=w800)

-> New Spring Starter Project Dependencies (프로젝트에 사용할 의존성) 설정 후 'Finish 버튼'클릭하기 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTA1/MDAxNjA1ODg5MDg3NjYx.cZvnXdqQlfZp7t2KBrU1JGpzmgSJ7va7VuzbM5bpEjIg.c6MFiBnKfaH7XpGCZhD4lIJSsfWBAJbBvMpC8ihxBU0g.PNG.rudnfskf2/02.PNG?type=w800)

아래 사진처럼 프로젝트가 생성되면 성공.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTcx/MDAxNjA1ODg5MjM3ODY2.JX3-ClgNxThfY6f-N19UWDD3xKZ4R1QGIaHoYQZty2Mg.i4_kzH01lhJVW3Cotg1eKKsktvKsHXxk9CFAnEiEiGYg.PNG.rudnfskf2/02-02.PNG?type=w800)



**※ Gradle로 의존성 설정**

board-back프로젝트의 의존성 관리는 build.gradle 파일에 기재된대로 설정됨.

이하는 ./build.gradle의 내용

plugins { id 'org.springframework.boot' version '2.3.4.RELEASE' id 'io.spring.dependency-management' version '1.0.10.RELEASE' id 'java' } group = 'com.board' version = '0.0.1-SNAPSHOT' sourceCompatibility = '1.8' repositories { mavenCentral() } dependencies { implementation 'org.springframework.boot:spring-boot-starter-data-jpa' implementation 'org.springframework.boot:spring-boot-starter-web' developmentOnly 'org.springframework.boot:spring-boot-devtools' runtimeOnly 'mysql:mysql-connector-java' testImplementation('org.springframework.boot:spring-boot-starter-test') { 	exclude group: 'org.junit.vintage', module: 'junit-vintage-engine' } } test { useJUnitPlatform() }

**- dependencies관련설명** 

| implementation     | - 컴파일시에 의존하는 라이브러리를 지정함.- 라이브러리를 추가할때 사용 |
| ------------------ | ------------------------------------------------------------ |
| developmentOnly    | - 개발시 의존하는 라이브러리를 지정                          |
| runtimeOnly        | - 런타임시 의존하는 라이브러리를 지정                        |
| testImplementation | - 의존하는 라이브러리를 지정                                 |

**- 프로젝트에 사용된 라이브러리 설명** 

| spring-boot-starter-data-jpa | - jpa사용을 위한 라이브러리                                  |
| ---------------------------- | ------------------------------------------------------------ |
| spring-boot-starter-web      | - 웹 어플리케이션 제작을 위한 라이브러리Spring boot의 내장형 Tomcat도 이녀석이 불러온다. |
| spring-boot-devtools         | - 소스코드에 수정된 것이 있다면 자동으로 변경사항을 반영해주는 등 개발시 유용한 라이브러리 |

build.gradle파일의 작성이 완료되면 반드시 'Refresh Gradle Project'를 실행해야한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfNzkg/MDAxNjA1ODkxOTk4Nzk4.lwDdxBqu81VOV0Z2J49MEw4wmV0BhS9nNzExjpFqJosg.3gTIH2m12Y4CLJ77doDUL__8gJeblhcAzoDH4hnYOugg.PNG.rudnfskf2/start04.PNG?type=w800)

Project and External Dependencies에 라이브러리가 추가되어 있다면 성공.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTY0/MDAxNjA1ODkyMDg1NTE0.8-LQqhI7J8x1QEze1UJhGNB3nn5lcCI3eDvKWMSOH3Eg.1lE_WAC8ZS1AmDqjoWUfeSNfDoUwjzv2Y8wI4Vu3Afcg.PNG.rudnfskf2/start05.PNG?type=w800)

※ **src/main/resources/application.properties**파일의 값 설정

아래와 같이 설정해주면됨.

\# DB접속을 위한 설정값 spring.datasource.url=jdbc:mysql://localhost:3306/board-back?useSSL=false&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull spring.datasource.username=root spring.datasource.password=1234 spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLInnoDBDialect # spring.jpa.hibernate.ddl-auto= update # jpa에서 실행되는 쿼리문을 볼 수 있게하는 설정값 spring.jpa.properties.hibernate.show_sql=true spring.jpa.properties.hibernate.format_sql=true spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true logging.level.org.hibernate.type.descriptor.sql=trace



**2. Hello World 출력해보기**

\- 프로젝트 생성시 기본적으로 생성되어있는 컨트롤러를 사용해서 'Hello World'라는 문자데이터를 반환하도록 한다.



아래 파일(BoardBackApplication.java)을 수정한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjAy/MDAxNjA1ODkxMTk2Mzg0.fxM7JH0s59QP0yRg3yfbuJRLuwUENAnHCL03KUV2PpMg.mdCfxLo99XOZo90z7il8G7IKmSXwp3bGFZITXk8-7hEg.PNG.rudnfskf2/start01.PNG?type=w800)

수정 내용은 아래와 같다.

package com.board.back; import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.web.bind.annotation.RequestMapping; import org.springframework.web.bind.annotation.RestController; @RestController @SpringBootApplication public class BoardBackApplication { 	public static void main(String[] args) { 	SpringApplication.run(BoardBackApplication.class, args); } 	@RequestMapping("/") public String home() { 	return "hello world spring"; } }

**- 실행하기**

 -> 프로젝트에서 마우스 우클릭

 -> Run As -> Spring Boot App을 클릭한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjMz/MDAxNjA1ODkxNDM5NTQ5.vrDU1rmDwce7TNlqgh_Lo5FDcfHHRKfKL9F1wexIQPMg.WkEVQPSPVZygsFB75KUeEdxjvSBew69xVqzzf7zu2gMg.PNG.rudnfskf2/start02.PNG?type=w800)

\- 브라우져에서 아래와 같이 문자열이 출력되면 성공이다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfODUg/MDAxNjA1ODkxNjEzMDYz.IniEL3YJ74Bqr-_MSAYQIrqlHOnk9HHqyKlj7shaPKsg.d1zkUJtP2wzPlLLDw6erwl-eyjmFQM3hHePI19EI3gEg.PNG.rudnfskf2/start03.PNG?type=w800)



\------------------------------------------------------------------------------------------------------------

스프링 부트의 환경설정은 여기까지 

다음 포스팅은 **글 목록 출력하기 구현**이될 예정이다.



## 3 : 게시판 프로젝트 구조와 글 목록 구현

**개요** 

이번 포스팅은 **게시판 프로젝트 구조설명과 글 목록 구현**에 대한 내용이 되겠다.



**1. 프로젝트 구조에 대해서** 

React + Spring boot 게시판 프로젝트의 구조에 대해서 간단히 설명하고 나서 구현에 들어가려한다. 



※ 구조도

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTUx/MDAxNjA1OTIzNjYyMDAw.xe7BJAMCXu9Zh_UR73qwhP20Vhgj4Rn2MGWX-ZdIS2Yg.6OQEsq1_E0XYqQ0L4guqTx8Z52ZZYn2yP6cjnxcsA7Ig.PNG.rudnfskf2/%EA%B5%AC%EC%A1%B0.PNG?type=w800)

지금 부터 구현할 게시판의 구조는 대략 위의 캡쳐와 같다. 



**※ 데이터 통신이 일어나는 순서**

**(1) 'Web Browser'에서 React 어플리케이션의 URL로 접속**

**(2)** **'Web Browser'에서 요청한 URL에 따라** **'React-Router'에서 해당 URL에 해당하는 페이지의 내용을 렌더링하는 최상위 'Component'를 불러온다.**

**(3) 'Component'가 렌더링 될때 'Service'에 미리 정의해둔 함수를 사용해서 데이터를 가져온다.**

**(4) 'Service'에서는 axios 패키지의 기능을 사용해서 'Spring boot Api'와 http 통신을 주고 받는다.**

**(5)**  **React쪽의 'Service'에서 요청한 http request를 'Rest Controller'에서 수신한다.**

**(6)  'Rest Controller'에서 'Service'를 호출하여  "React쪽의 'Service'에서 요청한 것"에 해당하는 기능을 수행한다.**

**(7)  'Service'에서는 'Repository'를 호출하여 데이터 crud작업을 처리한다.**

**(8) 'Repository'에서 'DB'를 호출하여  데이터 crud작업을 처리한다.**





유저가 웹 브라우져에서 특정페이지에 접속해서 페이지의 내용을 볼 때 대략 위에 기재한 '※ 데이터 통신이 일어나는 순서' 대로  동작한다. 

게시판을 구현할때도 이 기본구성과 통신의 흐름을 생각하며 코딩하면, 큰 문제없이 원하는 기능을 구현할 수 있을것이다. 

이번 포스팅에서는  '데이터 통신이 일어나는 순서'의 **정방향으로 구현해** 나가기로 했다.

 ※ 프론트나 백엔드 어느쪽을 먼저 구현하든 순서는 상관없다고 생각한다. **흐름을 보는게 가장 중요**.

**2. React쪽 작업**

**1) src/App.js 의 내용수정** 

**(1) 어떤 흐름으로 웹페이지에 컴포넌트의 내용이 출력되는지??**

public/index.html에 컴포넌트의 내용이 출력되기 까지 아래와 같은 흐름이다.



※ 흐름

 a. 구현한 컴포넌트를 src/App.js에 정의한다.

 b. index.js에서는 index.html에서 id속성값이 root인 dom에 src/App.js에 정의된 내용을 렌더링

``` js
ReactDOM.render(
  <React.StrictMode>
    <App />                       # ﻿index.js에서 App.js의 내용을  
  </React.StrictMode>,
  document.getElementById('root') # id값이 root인 곳에 렌더링
);
```

index.js에서는 src/App.js를 렌더링하는 역할만 할것이므로, src/App.js에 컴포넌트를 추가하는 것으로 새로운 내용을 index.html에 렌더링 할 수 있다. 

**(2) src/App.js 소스코드 설명**

``` js
# 1.
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'; 
import ListBoardComponent from './components/ListBoardComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

# 2.
function App() {
  return (
    <div> 
      <Router>             # 3.
        <HeaderComponent/> # 4.
          <div className="container">
            <Switch>       # 5.
              <Route path = "/" exact component = {ListBoardComponent}></Route>
              <Route path = "/board" component = {ListBoardComponent}></Route>
            </Switch>
          </div>
        <FooterComponent/> # 6.
      </Router>
    </div>
  );
}

export default App;
```

**# 1. 부분** 

 \- 의존하는 패키지등을 정의한 곳이다. react-router-dom과 최상위 컴포넌트등을 정의.



**# 2. 부분** 

\- App()함수에 최상위 컴포넌트들을 정의함. 



**# 3. 부분**

 \- react-router의 적용대상의  컴포넌트를 <Router>로 감싼다.



**# 4. 부분** 

 \- 웹 페이지의 헤더부분을 표시하는 컴포넌트를 정의. 



**# 5. 부분** 

 \-  URL별로 페이지를 전환하기 위한 최상위 컴포넌트들을 <Switch>로 감싼다.

  <Route>에 컴포넌트에 대응하는 URL과 컴포넌트를 정의.

   ★ 헤더와 푸터의 경우 <Switch>로 감싸지지 않았으므로 페이지가 전환되도 계속출력됨.



**# 6. 부분** 

 \- 웹 페이지의 푸터부분을 표시하는 컴포넌트를 정의. 



**2) 컴포넌트 만들기**

※ React 어플리케이션에서는 웹 페이지에서 출력을 'Component'를 상속한 ~Component.jsx로 구현한다.

이 파트에선 헤더와 푸터 , 글목록 출력을 위한 컴포넌트를 구현한다. 



**(1) 글목록 컴포넌트 구현**

  **a. src/components 폴더생성 후 'ListBoardComponent.jsx'파일을 생성.**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTc1/MDAxNjA1OTQ0MzU0Mjkz.XLFhH88RgvOqiLHqCKpilg10Wflb487uGehlqEqrVdUg.IHVSBjp2Za6rqYm_Ox7wRAfcAFMMQpxPtNyt3omGViYg.PNG.rudnfskf2/01.PNG?type=w800)

 ※ 시냅 모듈을 설치한 경우 'rcc' 입력시 자동완성 기능을 사용 할 수 있다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjEg/MDAxNjA1OTQ0NjI0OTk4.6FAuce9AyIzEnnn-_vagS1wX-G2cR_o_4bUTtfKlCY4g.Fa2KpVWXRsonU1jCGPb5BFgd5Y5hBppzD8CDJxfcb-8g.PNG.rudnfskf2/02.PNG?type=w800)

 **b. 'ListBoardComponent.jsx 소스코드와 설명** 

``` js
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)
    // # 1. 
        this.state = { 
            boards: []
        }
		
    }
    // # 2. 
    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }

    // # 3.
    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.no}>
                                        <td> {board.no} </td>
                                        <td> {board.title} </td>
                                        <td> {board.memberNo} </td>
                                        <td> {board.createdTime} </td>
                                        <td> {board.updatedTime} </td>
                                        <td> {board.likes} </td>
                                        <td> {board.counts} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
        );
    }
}

export default ListBoardComponent;
```

**# 1. 부분**

 \- 페이지에 표시될 글 목록데이터를 넣기위한 변수 'boards'를 this.state에 선언



**# 2. 부분** 

\- 리액트의 생명주기 메소드인 'componentDidMount'에서 'BoardService'의 메소드를 호출해서 데이터를 가져온다.

 **★this.state에 선언한 변수의 값을 변경하기 위해선 setState를 사용해야함.**



**# 3. 부분**

 \- render() 함수의 내용이 실제 웹페이지에 표시된다. 

 \- maps() 함수를 사용해서 'boards'의 데이터를 출력한다.



**(2) 헤더와 푸터 컴포넌트 구현**

 src/components폴더에 "HeaderComponent.jsx","FooterComponent.jsx를 생성.



**a. HeaderComponent.jsx 소스코드 (단순 헤더출력이므로 설명X)**

``` js
import React, { Component } from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:3000" className="navbar-brand"> Board-FullStack-App</a></div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;
```



**b. FooterComponent.jsx 소스코드 (단순 푸터출력이므로 설명X)**

``` js
import React, { Component } from 'react';

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <footer className="footer">
                    <span className="text-muted">Started 2020 </span>

                </footer>
            </div>
        );
    }
}

export default FooterComponent;
```

**3) 데이터 통신을 위한 Service 만들기**  

\- axios를 사용해서 spring boot api와 데이터통신을 담당할 모듈인 'Service'를 구현하는 파트. 



**(1)**  **BoardService구현**

**a. src/service디렉토리 생성 후 , BoardService.js파일생성** 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMTk4/MDAxNjA1OTQ3ODkyNTA4.D1FhiwRU8sjLvEkwrD2Ihee6I_ZRGm0eHzSpCNzQPZ8g.BVF1UjLXKb7LjNyzvASQi057-NWyHd0uYIXbfx6dmNsg.PNG.rudnfskf2/03.PNG?type=w800)

**b. BoardService.js 소스코드와 설명**

``` js
import axios from 'axios'; # 1. 

# 2. 
const BOARD_API_BASE_URL = "http://localhost:8080/api/board"; 

class BoardService {

# 3.
    getBoards() {
        return axios.get(BOARD_API_BASE_URL);
    }
}

export default new BoardService();
```



**# 1. 부분**

 \- axios를 사용하는 것을 정의.



**# 2. 부분**

 \- spring boot api의 URL을 정의.



**# 3. 부분** 

\- 글목록 데이터를 가져오는 함수

**3. Spring Boot쪽 작업**

1) Entity

\- 2장에서 MySQL에 생성한 테이블에 대응하는 .java 파일을 생성.



(1) Board.java소스코드와 설명 

``` java
package com.board.back.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "board")
@DynamicInsert  # 1.
@DynamicUpdate 
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "member_no")
	private Integer memberNo;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "likes")
	private Integer likes;
	
	@Column(name = "counts")
	private Integer counts;

// ---Getter/Setter ---

}
```

\# 1. 부분 

 @DynamicInsert    : Insert시 Null인 필드를 제외하기위해 사용했음.

 @DynamicUpdate : update시 Null인 필드를 제외하기위해 사용했음.



2) Controller

``` java
package com.board.back.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;
# 1.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

    # 2.
	// get all board 
	@GetMapping("/board")
	public List<Board> getAllBoards() {
		
		return boardService.getAllBoard();
	}
	
}
```

\# 1. 부분

 \- @CrossOrigin : CORS 문제를 해결하기 위해 추가.

 \# 2. 부분

 \- Service를 호출해서 글목록의 데이터를 리턴하는 메소드.



3) Service

``` java
package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
    # 1. 
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

}
```

**# 1. 부분**

\- Repository를 호출해서 글목록 데이터를 리턴하는 메소드.



4) Repository

``` java
package com.board.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.board.back.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> { # 1.

}
```

\# 1. 부분

\- 간단한 CRUD기능은 JpaRepository를 상속하는 것으로 구현이 가능하다.



5) Exception

``` java
package com.board.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
```

\- 예외처리가 발생할 경우 메시지를 리턴하기위한 예외처리 클래스이다.





**※ Spring boot 프로젝트에 아래와 같이 파일들이 생성되었다면 작업완료이다.**

  

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjFfMjgw/MDAxNjA1OTUwOTI5NTg3.gRVdSG6xwYWE2BpaGv75Q467U-W0BClARG2rvPYJLckg.itDiHZmqEyf0jFT1ml24kGzc4i1PCX0EODPdVOBQU_gg.PNG.rudnfskf2/04.PNG?type=w800)

**4. 동작확인**

**1) Spring boot api 실행**

-> 프로젝트에서 마우스 우클릭

-> Run As -> Spring Boot App을 클릭한다.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjJfMTY0/MDAxNjA1OTczODYyOTY3.b4VMgr4_OGNSdp44w0V2tnCLAnxUVdhj5lYng-hxEBEg.j0jCjTZsSj-H8fHEhcONOPqTmH11O992DGlvhGjc6Vgg.PNG.rudnfskf2/start02.PNG?type=w800)

★ spring boot 실행전에 **xampp의 apache와 MySQL을 먼저 실행**할것



2) React 프로젝트 실행하기

-> VS Code의 터미널에서 npm start로 실행

cd C:\Users\leeyoungseung\project_source\board-fullstack\board-front\board-app # 실행하기 npm start

\- 다음과같이 글목록이 출력되면 성공.

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjJfMzAg/MDAxNjA1OTc0NDU0MTg4.oj_uupM7KATc8K0y0HdmTMykjGxuizf_vX7Oo-Z3_k0g.0ClhhA5Rt0RF9Hw1DIzJ6depwae6suVLnzaApQXKf6Qg.PNG.rudnfskf2/05.PNG?type=w800)

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDExMjJfMjMx/MDAxNjA1OTc0NDU0MTI0.WdpczP5PM0XSxQ15cT0DTftM61OEDHnRKJn1KlVEdSog.PvLtX8CRdBD4lhfX6WLpFs1vg3EZyUGWYhbunmZwTc4g.PNG.rudnfskf2/05-2.PNG?type=w800)

Previous imageNext image

프로젝트 구조 설명과 글목록 구현하기는 여기까지.

다음 포스팅은 **글 작성하기 구현**이 될 예정이다.



## 4 : 글 작성 구현

**개요** 

이번 포스팅은 **글 작성 구현**에 대한 내용이 되겠다.



**1. React쪽 작업**

 글 작성 기능의 흐름을 서술하자면 다음과 같다.

 **a. 글 목록페이지 에서 글 작성 버튼을 클릭한다.**

 **b. 글 작성 페이지가 로딩된다.**

 **c. 글 작성 페이지의 폼을 작성한 후 'Save'버튼을 클릭**

 **d. 글 목록으로 이동해서 작성된 글을 확인한다.** 

****

위에서 서술한 기능의 흐름대로 구현. 



**1) src/App.js의 내용수정**

**(1) src/App.js 소스코드 설명**

``` js
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListBoardComponent from './components/ListBoardComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateBoardComponent from './components/CreateBoardComponent'; # 1

function App() {
  return (
    <div> 
      <Router>
        <HeaderComponent/>
          <div className="container">
            <Switch>
              <Route path = "/" exact component = {ListBoardComponent}></Route>
              <Route path = "/board" component = {ListBoardComponent}></Route>
              <Route path = "/create-board" component = {CreateBoardComponent}></Route> # 2
            </Switch>
          </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;
```

※ 이전 포스팅에서 변경된 부분만 설명함!

\# 1. 부분

 \- 글 작성 페이지의 컴포넌트를 import



\# 2. 부분

 \- 글 작성 페이지의 <Route>를 작성



**2) 글 목록 컴포넌트 수정 및 글 작성 컴포넌트 만들기**



※ 이 파트에선 글 목록 페이지에서 글 작성 페이지로 **이동하기위한 버튼을 추가**하고, 

   **글 작성 페이지 출력을 위한 컴포넌트를 구현**한다.

**(1) 글 목록 컴포넌트(ListBoardComponent.jsx) 수정내용 설명** 

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = { 
            boards: []
        }
		# 1. 
		this.createBoard = this.createBoard.bind(this);
    }

    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }
	
	# 2
	createBoard() {
        this.props.history.push('/create-board/');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
				# 3
				<div className = "row">
                    <button className="btn btn-primary" onClick={this.createBoard}> 글 작성</button>
                </div>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.no}>
                                        <td> {board.no} </td>
                                        <td> {board.title} </td>
                                        <td> {board.memberNo} </td>
                                        <td> {board.createdTime} </td>
                                        <td> {board.updatedTime} </td>
                                        <td> {board.likes} </td>
                                        <td> {board.counts} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
        );
    }
}

export default ListBoardComponent;
```

\# 1. 부분 

 \- 글 작성 버튼을 클릭 했을 때 동작하는 'createBoard' 함수를 바인드.



\# 2. 부분

 \- 글 작성 버튼을 클릭시 글작성 페이지로 이동하게 해주는 함수를 정의한것.



\# 3. 부분

 \- 글 작성 버튼을 추가.



**(2) 글 작성 컴포넌트 구현**

a. src/components폴더에 "CreateBoardComponent.jsx"파일을 생성

b. "CreateBoardComponent.jsx" 소스코드 설명

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class CreateBoardComponent extends Component {
    constructor(props) {
        super(props);

        # 1. 
        this.state = {
            type: '',
            title: '',
            contents: '',
            memberNo: ''
        }

        # 2.
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeContentsHandler = this.changeContentsHandler.bind(this);
        this.changeMemberNoHandler = this.changeMemberNoHandler.bind(this);
        this.createBoard = this.createBoard.bind(this);
    }

    # 3. 
    changeTypeHandler = (event) => {
        this.setState({type: event.target.value});
    }
    # 3. 
    changeTitleHandler = (event) => {
        this.setState({title: event.target.value});
    }
    # 3. 
    changeContentsHandler = (event) => {
        this.setState({contents: event.target.value});
    }
    # 3. 
    changeMemberNoHandler = (event) => {
        this.setState({memberNo: event.target.value});
    }

    # 4. 
    createBoard = (event) => {
        event.preventDefault();
        let board = {
            type: this.state.type,
            title: this.state.title,
            contents: this.state.contents,
            memberNo: this.state.memberNo
        };
        console.log("board => "+ JSON.stringify(board));
        BoardService.createBoard(board).then(res => {
            this.props.history.push('/board');
        });
    }

    # 5. 
    cancel() {
        this.props.history.push('/board');
    }


    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">새글을 작성해주세요</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Type </label>
                                        <select placeholder="type" name="type" className="form-control" 
                                        value={this.state.type} onChange={this.changeTypeHandler}>
                                            <option value="1">자유게시판</option>
                                            <option value="2">질문과 답변</option>
                                        </select>
                                    </div>
                                    <div className = "form-group">
                                        <label> Title </label>
                                        <input type="text" placeholder="title" name="title" className="form-control" 
                                        value={this.state.title} onChange={this.changeTitleHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Contents  </label>
                                        <textarea placeholder="contents" name="contents" className="form-control" 
                                        value={this.state.contents} onChange={this.changeContentsHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> MemberNo  </label>
                                        <input placeholder="memberNo" name="memberNo" className="form-control" 
                                        value={this.state.memberNo} onChange={this.changeMemberNoHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.createBoard}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

export default CreateBoardComponent;
```

\# 1. 부분 

 \- this.state에 폼 양식에서 사용될 파라미터를 정의.



\# 2. 부분 

 \- 폼 양식에 값이 입력되면 this.state에 정의 된 변수의 값을 변경하도록 바인드

 \- 'Save' 버튼을 클릭시 API에 글 작성 리퀘스트를 보내는 함수를 바인드



\# 3. 부분 

 \- this.setState로 this.state에 정의된 변수에 값을 대입하게 해주는 함수를 선언.



\# 4. 부분 

 \-  'Save' 버튼을 클릭시 API에 글 작성 리퀘스트를 보내는 함수를 선언



\# 5. 부분

\- 글 작성 취소버튼이 클릭되었을때 글목록 페이지로 이동하는 함수를 선언



**3) 데이터 통신을 위한 Service 수정하기**

\- src/service/BoardService.js에 글작성을 위한 함수를 추가한다.

``` js
import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {
    getBoards() {
       return axios.get(BOARD_API_BASE_URL);
    }

# 1. 
    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

}

export default new BoardService();
```

\# 1. 부분 

 \- 글 작성 함수를 추가; axios의 post함수를 사용해서 통신한다.



2. Spring Boot쪽 작업

**1) Controller**

``` java
package com.board.back.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// get all board 
	@GetMapping("/board")
	public List<Board> getAllBoards() {
		return boardService.getAllBoard();
	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}
	
}
```



**2) Service**

``` java
package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

}
```

**3. 동작확인**

※ 아래의 순서대로 실행할것, 

 자세한 실행 방법은 이전 포스팅을 참조 

 

**React + Springboot 게시판 만들기 3 : 게시판 프로젝트 구조와 글 목록 구현**

개요 이번 포스팅은 게시판 프로젝트 구조설명과 글 목록 구현에 대한 내용이 되겠다.1. 프로젝트 구조에...

blog.naver.com

\- xampp에서 apache와 MySQL실행

\- 이클립스에서 API실행

\- VS Code의 터미널에서 npm start



**1) 글 목록에서 글 작성 버튼 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjBfMTg3/MDAxNjA4Mzk5NTQ5MjEy.saaf8S3-ewIOAWWtxxptK5x8WPnTB5Jo2j5NeQOPmO0g.7dykAQrHWiSd3smcWD8cRYj8jl_1PIh7CkbVJ2iKtlEg.PNG.rudnfskf2/01.PNG?type=w800)

**2) 글작성 페이지로 이동된것 확인**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjBfNzQg/MDAxNjA4Mzk5NTcxNzk3.1NSBI_qRM5lBZKKCoo7DJz77QEIMa9s7_P8p0HfwiI8g.td7sKUJQEg9enn5ZJUx1ktzGShzAQ_j55sW35u3CZ0Ug.PNG.rudnfskf2/02.PNG?type=w800)

**3) 폼 작성 후 "Save" 버튼 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjBfMTMg/MDAxNjA4Mzk5NjU2MzA2.mqeHuSXauj6xCpBrqyCeRq_IHOXRJuYF6BRqJPNPYQIg.sZbzverQVvn1PXPf9i9NDfkiK6EIKP7efNPX6ZA6hkMg.PNG.rudnfskf2/03.PNG?type=w800)



**4) 글 목록 페이지에서 작성된 글이 목록에 포함되어있는지 확인**

 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjBfMjg3/MDAxNjA4Mzk5NjgyMDk2.vIr49i_CqHY6oSwqIeNtf2AY7wMZXAd7NluwfJBPNm0g.K21la8di1zmKM6sUxYlsPFFvbgerbpmAjpMdOa7MV7wg.PNG.rudnfskf2/04.PNG?type=w800)

글 작성 구현하기는 여기까지

다음 포스팅은 **글 상세보기 구현**이 될 예정이다.



## 5 : 글 상세보기 구현

**개요** 

이번 포스팅은 **글 상세보기 구현**에 대한 내용이 되겠다.



**1. React쪽 작업**

글 상세보기 기능의 흐름을 서술하자면 다음과 같다.

**a. 글 목록페이지 에서 글 제목을 클릭한다.**

**b. 글 상세보기 페이지가 표시된다.**

****

위에서 서술한 기능의 흐름대로 구현. 



**1) src/App.js의 내용수정**

**(1) src/App.js 소스코드 설명**

``` js
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListBoardComponent from './components/ListBoardComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateBoardComponent from './components/CreateBoardComponent';
import ReadBoardComponent from './components/ReadBoardComponent'; # 1.

function App() {
  return (
    <div> 
      <Router>
        <HeaderComponent/>
          <div className="container">
            <Switch>
              <Route path = "/" exact component = {ListBoardComponent}></Route>
              <Route path = "/board" component = {ListBoardComponent}></Route>
              <Route path = "/create-board" component = {CreateBoardComponent}></Route>
              <Route path = "/read-board/:no" component = {ReadBoardComponent}></Route> # 2.
            </Switch>
          </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;
```

※ 이전 포스팅에서 변경된 부분만 설명함!

\# 1. 부분

\- 글 상세보기 페이지의 컴포넌트를 import



\# 2. 부분

\- 글 상세보기 페이지의 <Route>를 작성



**2) 글 목록 컴포넌트 수정 및 글 상세보기 컴포넌트 만들기**

※ 이 파트에선 글 목록 페이지에서 글 상세보기 페이지로 **이동하기위해 글 제목에 링크를 추가**하고, 

**글 상세보기 페이지 출력을 위한 컴포넌트를 구현**한다.



**(1) 글 목록 컴포넌트(ListBoardComponent.jsx) 수정내용 설명** 

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = { 
            boards: []
        }
		
		this.createBoard = this.createBoard.bind(this);
    }

    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }
	
	
	createBoard() {
        this.props.history.push('/create-board/');
    }
	
	# 1.
	readBoard(no) {
        this.props.history.push(`/read-board/${no}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
				# 3
				<div className = "row">
                    <button className="btn btn-primary" onClick={this.createBoard}> 글 작성</button>
                </div>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.no}>
                                        <td> {board.no} </td>
										# 2.
                                        <td> <a onClick = {() => this.readBoard(board.no)}>{board.title} </a></td>
                                        <td> {board.memberNo} </td>
                                        <td> {board.createdTime} </td>
                                        <td> {board.updatedTime} </td>
                                        <td> {board.likes} </td>
                                        <td> {board.counts} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
        );
    }
}

export default ListBoardComponent;
```

\# 1. 부분 

\- 글 제목을 클릭 했을 때 글 상세보기 페이지로 이동하게 해주는 함수를 정의한것.



\# 2. 부분

\- 글 제목을 클릭시 글 상세보기 페이지로 이동할 수 있도록 함수를 바인드.



**(2) 글 상세보기 컴포넌트 구현**

a. src/components폴더에 "ReadBoardComponent.jsx"파일을 생성

b. "ReadBoardComponent.jsx" 소스코드 설명



``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ReadBoardComponent extends Component {
    constructor(props) {
        super(props);

        # 1. 
        this.state = { 
            no: this.props.match.params.no,
            board: {}
        }

    }

    # 2.
    componentDidMount() {
        BoardService.getOneBoard(this.state.no).then( res => {
            this.setState({board: res.data});
        });
    }

    # 3.
    returnBoardType(typeNo) {
        let type = null;
        if (typeNo == 1) {
            type = "자유게시판";

        } else if (typeNo == 2 ) {
            type = "질문과 답변 게시판";

        } else {
            type = "타입 미지정";
        }

        return (
            <div className = "row">
                <label> Board Type : </label> {type}
            </div>
        )

    }

    returnDate(cTime, uTime) {
        return (
            <div className = "row">
                <label>생성일 : [ {cTime} ] / 최종 수정일 : [ {uTime} ] </label>
            </div>
        )
    }

    # 4. 
    goToList() {
        this.props.history.push('/board');
    }

    render() {
        return (
            <div>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className ="text-center"> Read Detail</h3>
                    <div className = "card-body">
                            # 5. 
                            {this.returnBoardType(this.state.board.type)} 
                            <div className = "row">      
                                # 6.
                                <label> Title </label> : {this.state.board.title}
                            </div>

                            <div className = "row">
                                <label> Contents </label> : <br></br>
                                <textarea value={this.state.board.contents} readOnly/> 
                            </div >

                            <div className = "row">
                                <label> MemberNo  </label>: 
                                {this.state.board.memberNo}
                            </div>

                            {this.returnDate(this.state.board.createdTime, this.state.board.updatedTime) }
                            # 7.
                            <button className="btn btn-primary" onClick={this.goToList.bind(this)} style={{marginLeft:"10px"}}>글 목록으로 이동</button>
                    </div>
                </div>

            </div>
        );
    }
}

export default ReadBoardComponent;
```

\# 1. 부분 

\- this.state에 글 상세보기에 사용될 파라미터 정의



\# 2. 부분 

\- 페이지가 로딩될때 API와 통신하여 글 객체를 가져온다.



\# 3. 부분 

\- 파라미터 값에 따라 페이지에 표시할 내용을 변경

\- 게시판 타입별로 표시를 다르게 함



\# 4. 부분 

\- 글 목록으로 이동하는 함수를 정의



\# 5. 부분

\- 게시판 타입별로 표시를 다르게 하기



\# 6. 부분

 \- 파라미터 값을 그대로 표시



\# 7. 부분

 \- 글 목록으로 이동하는 함수 바인드.



**3) 데이터 통신을 위한 Service 수정하기**

\- src/service/BoardService.js에 글 상세보기를 위한 함수를 추가한다.

``` js
import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {
    getBoards() {
       return axios.get(BOARD_API_BASE_URL);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

# 1.
    getOneBoard(no) {
        return axios.get(BOARD_API_BASE_URL + "/" + no);
    }

}

export default new BoardService();
```

\# 1. 부분

 \- 글 상세보기 함수 추가; 경로 파라미터로 글 번호를 설정하여 통신한다.

2. Spring Boot쪽 작업

**1) Controller**

``` java
package com.board.back.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// get all board 
	@GetMapping("/board")
	public List<Board> getAllBoards() {
		return boardService.getAllBoard();
	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}

	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}
	
}
```

**2) Service**

``` java
package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

	// get board one by id
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

}
```



**3. 동작확인**

※ 아래의 순서대로 실행할것, 

자세한 실행 방법은 이전 포스팅을 참조 

 

**React + Springboot 게시판 만들기 3 : 게시판 프로젝트 구조와 글 목록 구현**

개요 이번 포스팅은 게시판 프로젝트 구조설명과 글 목록 구현에 대한 내용이 되겠다.1. 프로젝트 구조에...

blog.naver.com

\- xampp에서 apache와 MySQL실행

\- 이클립스에서 API실행

\- VS Code의 터미널에서 npm start

**1) 글 목록에서 글 제목을 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjFfMjg3/MDAxNjA4NTYxMDk3MTUz.kQzrtxhUcfz4KyWfi-E4_fRkFwUaigXZqsY8-9jn79Yg.YTEx45-Xkrn0gsKUA-jukaoiJxn1gV-mN4-U5gLGZt4g.PNG.rudnfskf2/01.PNG?type=w800)

**2) 글 상세보기 페이지로 이동된것 확인**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjFfMTc0/MDAxNjA4NTYxMTE3Njg2.Xhm7b-g593YjfvJDGQowySTR8Zowxa-bXtVaxBhyuwQg.mukLNw6Bgzponfxvhokh-YO3pPnZTb8ROwXlwQPz4R8g.PNG.rudnfskf2/02.PNG?type=w800)

글 상세보기 구현은 여기까지

다음 포스팅은 **글 수정하기 구현**이 될 예정이다.





## 6 : 글 수정하기 구현

**개요** 

이번 포스팅은 **글 수정하기 구현**에 대한 내용이 되겠다.



**1. React쪽 작업**

글 수정하기 기능의 흐름을 서술하자면 다음과 같다.

**a. 글 목록페이지 에서 글 제목을 클릭한다.**

**b. 글 상세보기 페이지가 표시된다.**

**c. 글 상세보기 페이지에서 글 수정페이지 이동버튼을 클릭한다.**

**d. 글 수정 페이지에서 글의 내용을 수정한후 저장버튼을 클릭한다.**

**e. 성공하면 글 목록으로 이동**

**f. 글 상세보기 페이지에서 수정이 반영되었는지 확인**

****

위에서 서술한 기능의 흐름대로 구현. 



**1) src/App.js의 내용수정**

\- 글 수정하기 기능은 기존의 글 작성하기 컴포넌트를 수정해서 구현.

****

**(1) src/App.js 소스코드 설명**

``` js
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListBoardComponent from './components/ListBoardComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateBoardComponent from './components/CreateBoardComponent';
import ReadBoardComponent from './components/ReadBoardComponent';

function App() {
  return (
    <div> 
      <Router>
        <HeaderComponent/>
          <div className="container">
            <Switch>
              <Route path = "/" exact component = {ListBoardComponent}></Route>
              <Route path = "/board" component = {ListBoardComponent}></Route>
# 1.
              <Route path = "/create-board/:no" component = {CreateBoardComponent}></Route>
              <Route path = "/read-board/:no" component = {ReadBoardComponent}></Route>
            </Switch>
          </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;
```

※ 이전 포스팅에서 변경된 부분만 설명함!

\# 1. 부분

\- 글 작성하기 <Route>에서 path의 값을 경로 파라미터를 받도록 수정한다.

\- 경로 파라미터의 값을 통해서 글 작성 기능인지 글 수정 기능인지를 구분.



**2) 글 목록 컴포넌트 수정 및 글 작성 컴포넌트 수정**

※ 이 파트에선 글 목록 페이지에서 글 작성 페이지로 **이동하기위해 글작성 페이지 이동 버튼을 클릭할 시의 path를 수정**하고, 

**파라미터에 값에 따라 글 작성 페이지와 글 수정페이지 출력을 구분하기위해 글 작성 컴포넌트를 수정**한다.



**(1) 글 목록 컴포넌트(ListBoardComponent.jsx) 수정내용 설명** 

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = { 
            boards: []
        }
		
		this.createBoard = this.createBoard.bind(this);
    }

    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }
	
   # 1. 
	createBoard() {
        this.props.history.push('/create-board/_create');
    }
	
	readBoard(no) {
        this.props.history.push(`/read-board/${no}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
				# 3
				<div className = "row">
                    <button className="btn btn-primary" onClick={this.createBoard}> 글 작성</button>
                </div>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.no}>
                                        <td> {board.no} </td>
										# 2.
                                        <td> <a onClick = {() => this.readBoard(board.no)}>{board.title} </a></td>
                                        <td> {board.memberNo} </td>
                                        <td> {board.createdTime} </td>
                                        <td> {board.updatedTime} </td>
                                        <td> {board.likes} </td>
                                        <td> {board.counts} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
        );
    }
}

export default ListBoardComponent;
```

\# 1. 부분 

\- 경로에 파라미터를 추가.



**(2) 글 작성 컴포넌트 수정**

a. src/components폴더에 "CreateBoardComponent.jsx"파일을 생성

b. "CreateBoardComponent.jsx" 소스코드 설명

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class CreateBoardComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            no: this.props.match.params.no, #1
            type: '',
            title: '',
            contents: '',
            memberNo: ''
        }

        this.changeTypeHandler = this.changeTypeHandler.bind(this);
        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeContentsHandler = this.changeContentsHandler.bind(this);
        this.changeMemberNoHandler = this.changeMemberNoHandler.bind(this);
        this.createBoard = this.createBoard.bind(this);
    }
    changeTypeHandler = (event) => {
        this.setState({type: event.target.value});
    }
    changeTitleHandler = (event) => {
        this.setState({title: event.target.value});
    }
    changeContentsHandler = (event) => {
        this.setState({contents: event.target.value});
    }
    changeMemberNoHandler = (event) => {
        this.setState({memberNo: event.target.value});
    }
#2
    createBoard = (event) => {
        event.preventDefault();
        let board = {
            type: this.state.type,
            title: this.state.title,
            contents: this.state.contents,
            memberNo: this.state.memberNo
        };
        console.log("board => "+ JSON.stringify(board));
        if (this.state.no === '_create') {
            BoardService.createBoard(board).then(res => {
                this.props.history.push('/board');
            });
        } else {
            BoardService.updateBoard(this.state.no, board).then(res => {
                this.props.history.push('/board');
            });
        }
    }

    cancel() {
        this.props.history.push('/board');
    }
#3
    getTitle() {
        if (this.state.no === '_create') {
            return <h3 className="text-center">새글을 작성해주세요</h3>
        } else {
            return <h3 className="text-center">{this.state.no}글을 수정 합니다.</h3>
        }
    }

#4
    componentDidMount() {
        if (this.state.no === '_create') {
            return
        } else {
            BoardService.getOneBoard(this.state.no).then( (res) => {
                let board = res.data;
                console.log("board => "+ JSON.stringify(board));
                
                this.setState({
                        type: board.type,
                        title: board.title,
                        contents: board.contents,
                        memberNo: board.memberNo
                    });
            });
        }
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            {
                               this.getTitle()
                            }
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Type </label>
                                        <select placeholder="type" name="type" className="form-control" 
                                        value={this.state.type} onChange={this.changeTypeHandler}>
                                            <option value="1">자유게시판</option>
                                            <option value="2">질문과 답변</option>
                                        </select>
                                    </div>
                                    <div className = "form-group">
                                        <label> Title </label>
                                        <input type="text" placeholder="title" name="title" className="form-control" 
                                        value={this.state.title} onChange={this.changeTitleHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Contents  </label>
                                        <textarea placeholder="contents" name="contents" className="form-control" 
                                        value={this.state.contents} onChange={this.changeContentsHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> MemberNo  </label>
                                        <input placeholder="memberNo" name="memberNo" className="form-control" 
                                        value={this.state.memberNo} onChange={this.changeMemberNoHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.createBoard}>Save</button>
                     
```

\# 1. 부분 

\- 글 작성인지 수정인지 구분하기위한 파라미터를 선언하기



\# 2. 부분 

\- 'Save' 버튼을 클릭시 API에 글 작성 또는 글 수정 리퀘스트를 보내도록 수정



\# 3. 부분 

\- 페이지 타이틀을 글 작성인지 수정인지에 따라서 구분해서 출력하도록 수정 



\# 4. 부분 

\- 페이지 로딩시 글 작성이면 비어있는 폼을, 글 수정이면 글의 객체 값을 가져와서 바인딩해주도록 수정



**3) 데이터 통신을 위한 Service 수정하기**

\- src/service/BoardService.js에 글 수정을 위한 함수를 추가한다.

``` js
import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {
    getBoards() {
       return axios.get(BOARD_API_BASE_URL);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

    getOneBoard(no) {
        return axios.get(BOARD_API_BASE_URL + "/" + no);
    }

# 1.
    updateBoard(no, board) {
        return axios.put(BOARD_API_BASE_URL + "/" + no, board);
    }
}

export default new BoardService();
```

\# 1. 부분

\- 글 수정 함수 추가; 경로 파라미터로 글 번호를 설정, 수정할 객체정보를 body에 담아 통신한다.



2. Spring Boot쪽 작업

**1) Controller**

``` java
package com.board.back.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// get all board 
	@GetMapping("/board")
	public List<Board> getAllBoards() {
		return boardService.getAllBoard();
	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}

	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}

	// update board
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Board board){
		
		return boardService.updateBoard(no, board);
	}
}
```



**2) Service**

``` java
package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

	// get board one by id
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

	// update board 
	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}

}
```



**3. 동작확인**

※ 아래의 순서대로 실행할것, 

자세한 실행 방법은 이전 포스팅을 참조 

 

**React + Springboot 게시판 만들기 3 : 게시판 프로젝트 구조와 글 목록 구현**

개요 이번 포스팅은 게시판 프로젝트 구조설명과 글 목록 구현에 대한 내용이 되겠다.1. 프로젝트 구조에...

blog.naver.com

\- xampp에서 apache와 MySQL실행

\- 이클립스에서 API실행

\- VS Code의 터미널에서 npm start



**1) 글 목록에서 글 제목을 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfOTIg/MDAxNjA4NTk2ODg4MTI2._8-c6tTgg9HGP0bXU4stTq0qoM0MZSshYklBHcwBvtsg.ucd1rCw2w3XPBoDZOYUWKj-74SXyFrGk6jYWFjvzKoEg.PNG.rudnfskf2/01.PNG?type=w800)

**2) 글 상세보기 페이지로 이동된것 확인후 글 수정 버튼클릭** 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjUy/MDAxNjA4NTk2ODk3MTQ4.TP6iaTnotz-NaWFMdKASi5DJ_MNr6tJW4oAVN74k61cg.IJi-0min5e4OHs2ImShkbmHQXIuyoQ4wLVVlJNr-peAg.PNG.rudnfskf2/02.PNG?type=w800)

**3) 글 수정 페이지로 이동된것 확인**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfOCAg/MDAxNjA4NTk2OTA4NjE2.RtvP_aSUgNXbhxhSAxYeSTRGDsr60eK1nYuxhrZL7rcg.hy4ZCcZ-Vzlm_7ex_XbbukzJg4FYY9xgxdlHErlWHaEg.PNG.rudnfskf2/03.PNG?type=w800)

**4) 글 내용 수정한 후 저장버튼을 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjk0/MDAxNjA4NTk2OTE5MTg5.ujk-K6qbaSJr0_tkcYVXmteevT_p2hWqfSh3606YaREg.LUMqhtn7riUHE4uHgdCTcPlqACTy8aUd4Uw6pXm2Lbgg.PNG.rudnfskf2/04.PNG?type=w800)

**6) 글 목록 -> 글 상세보기 페이지에서 수정되었는지 확인**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMzcg/MDAxNjA4NTk3MDEzOTQ1.pQrY61bZlXNVLh28Ig4nROBPcaEgCDq36w9DzKD_70Eg.3mPRgQ61VaOW6OAPoYeoOi1ttA8MLr-HTTv-Ap2CjCYg.PNG.rudnfskf2/05.PNG?type=w800)

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMTEz/MDAxNjA4NTk3MDE0MTA1.wCZWgOtY1PxyigY_LeFRmsK8Esa2163MeB82ePmOadUg.g3m4SRh9m_DVwaPaOG4E1NWA7XBbriM1nyv3PelrSusg.PNG.rudnfskf2/06.PNG?type=w800)

글 수정하기 구현은 여기까지

다음 포스팅은 **글 삭제 구현**이 될 예정이다.



## 7 : 글 삭제 구현

**개요** 

이번 포스팅은 **글 삭제 구현**에 대한 내용이 되겠다.



**1. React쪽 작업**

글 수정하기 기능의 흐름을 서술하자면 다음과 같다.

**a. 글 목록페이지 에서 글 제목을 클릭한다.**

**b. 글 상세보기 페이지가 표시된다.**

**c. 글 상세보기 페이지에서 글 삭제버튼을 클릭한다.**

**d. 삭제 확인 메시지에서 확인 클릭**

**e. 삭제에 성공하면 글 목록으로 이동**

**f. 글 목록 페이지에서 삭제되었는지 확인**

****

위에서 서술한 기능의 흐름대로 구현. 



**1) 글 상세보기() 컴포넌트 수정**

 \- 글 삭제 기능은 페이지 이동이 없으므로, src/App.js는 수정하지 않음

``` js
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ReadBoardComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            no: this.props.match.params.no,
            board: {}
        }

        this.goToUpdate = this.goToUpdate.bind(this);

    }

    componentDidMount() {
        BoardService.getOneBoard(this.state.no).then( res => {
            this.setState({board: res.data});
            console.log("get result => "+ JSON.stringify(res.data));
        });

        
    }

    returnBoardType(typeNo) {
        let type = null;
        if (typeNo == 1) {
            type = "자유게시판";

        } else if (typeNo == 2 ) {
            type = "질문과 답변 게시판";

        } else {
            type = "타입 미지정";
        }

        return (
            <div className = "row">
                <label> Board Type : </label> {type}
            </div>
        )

    }

    returnDate(cTime, uTime) {
        return (
            <div className = "row">
                <label>생성일 : [ {cTime} ] / 최종 수정일 : [ {uTime} ] </label>
            </div>
        )
    }

    goToList() {
        this.props.history.push('/board');
    }

    goToUpdate = (event) => {
        event.preventDefault();
        this.props.history.push(`/create-board/${this.state.no}`);
    }

# 1.
    deleteView = async function () {
        if(window.confirm("정말로 글을 삭제하시겠습니까?\n삭제된 글은 복구 할 수 없습니다.")) {
            BoardService.deleteBoard(this.state.no).then( res => {
                console.log("delete result => "+ JSON.stringify(res));
                if (res.status == 200) {
                    this.props.history.push('/board');
                } else {
                    alert("글 삭제가 실패했습니다.");
                }
            });

        }
    }

    render() {
        return (
            <div>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className ="text-center"> Read Detail</h3>
                    <div className = "card-body">
                            {this.returnBoardType(this.state.board.type)} 
                            <div className = "row">
                                <label> Title </label> : {this.state.board.title}
                            </div>

                            <div className = "row">
                                <label> Contents </label> : <br></br>
                                <textarea value={this.state.board.contents} readOnly/> 
                            </div >

                            <div className = "row">
                                <label> MemberNo  </label>: 
                                {this.state.board.memberNo}
                            </div>

                            {this.returnDate(this.state.board.createdTime, this.state.board.updatedTime) }

                            <button className="btn btn-primary" onClick={this.goToList.bind(this)} style={{marginLeft:"10px"}}>글 목록으로 이동</button>
                            <button className="btn btn-info" onClick={this.goToUpdate} style={{marginLeft:"10px"}}>글 수정</button>
                            # 2.
                            <button className="btn btn-danger" onClick={() => this.deleteView()} style={{marginLeft:"10px"}}>글 삭제</button>
                    </div>
                </div>

            </div>
        );
    }
}


export default ReadBoardComponent;
```



\# 1. 부분 

\- 글삭제 함수 추가. alert창을 띄워서 삭제할지를 결정.

\- alert창에서 확인 버튼이 클릭되면, API와 통신하여 글을 삭제한후 성공하면 글 목록으로 이동



\# 2. 글 삭제 함수버튼 추가, 글 삭제 함수와 바인드.



**2) 데이터 통신을 위한 Service 수정하기**

\- src/service/BoardService.js에 글 삭제를 위한 함수를 추가한다.

``` js
import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {
    getBoards() {
       return axios.get(BOARD_API_BASE_URL);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

    getOneBoard(no) {
        return axios.get(BOARD_API_BASE_URL + "/" + no);
    }

    updateBoard(no, board) {
        return axios.put(BOARD_API_BASE_URL + "/" + no, board);
    }
# 1
    deleteBoard(no) {
        return axios.delete(BOARD_API_BASE_URL + "/" + no);
    }

}

export default new BoardService();
```

\# 1. 부분

\- 글 수정 함수 추가; 경로 파라미터로 글 번호를 설정, 글 번호에 해당하는 글을 삭제.



2. Spring Boot쪽 작업

**1) Controller**

``` java
package com.board.back.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// get all board 
	@GetMapping("/board")
	public List<Board> getAllBoards() {
		return boardService.getAllBoard();
	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}

	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}

	// update board
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Board board){
		
		return boardService.updateBoard(no, board);
	}

	// delete board
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
}
```



**2) Service**

``` java
package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

	// get board one by id
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

	// update board 
	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}

	// delete board
	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
```

**3. 동작확인**

※ 아래의 순서대로 실행할것, 

자세한 실행 방법은 이전 포스팅을 참조 

 

**React + Springboot 게시판 만들기 3 : 게시판 프로젝트 구조와 글 목록 구현**

개요 이번 포스팅은 게시판 프로젝트 구조설명과 글 목록 구현에 대한 내용이 되겠다.1. 프로젝트 구조에...

blog.naver.com

\- xampp에서 apache와 MySQL실행

\- 이클립스에서 API실행

\- VS Code의 터미널에서 npm start



**1) 글 목록에서 글 제목을 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfNzAg/MDAxNjA4NTk4NTk3MjMy.1fDVSfndF6mxfOW4HZIEPJ_d1SnOjuWOs3PzRWNg6MQg.IYq8dwFKMm9OQvZ9JjfCk5r-hTXeXbwlMRQpHM7H3cQg.PNG.rudnfskf2/05.PNG?type=w800)

**2) 글 상세보기 페이지로 이동된것 확인후 글 삭제 버튼클릭** 

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjM4/MDAxNjA4NTk4NjEyMDk5.AoZkmTEvMGmaAJLxaf54nx1oVIgog-BzI89r6coicRMg.Dj-iRyuSpepb7Mc9IoC71PWiAFT_DVmE5O_49KiP21cg.PNG.rudnfskf2/06.PNG?type=w800)

**3) Alert에서 확인 클릭**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjky/MDAxNjA4NTk4NjI2MDc4.pu7gJKar8Yx4xGj56Iy_Jfl718skJORhwymHR_q4MfAg.NBK7sgW9ySuPxYrfSB6iD0OQI8ZQntcMM40y0KJ2nI4g.PNG.rudnfskf2/01.PNG?type=w800)

**4) 글 목록에서 해당 글이 삭제되었는지 확인**

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMTQw/MDAxNjA4NTk4NjM0MDgy.ox0deFN0u6spe3TkczpLFYHzrUFtg3NgasdPxRs1vOMg.74gW9yyRpO4IVobpAKMpgwlSkjcjWeV7rzMe1A7VJ7Mg.PNG.rudnfskf2/02.PNG?type=w800)

글 삭제 구현은 여기까지

다음 포스팅은 **글 목록 페이징 처리**가 될 예정이다.



## 8 : 글 목록 페이징 처리

**개요** 

이번 포스팅은 **글 목록 페이징처리**에 대한 내용이 되겠다.





**1. React쪽 작업**

 \- 이 프로젝트에서 페이징 처리에 필요한 계산은 API에서 한 후에 계산된 값을 프로트로 넘겨서 사용하는 것으로 구현했다.

리엑트 쪽에서 해야하는 작업은 

글 목록 컴포넌트를 수정하는 것과, src/service/BoardService.js의 글목록 호출 함수를 수정하는 것이 되겠다.



**1) 글 목록(ListComponent.jsx) 컴포넌트 수정**

\- 네이버 블로그에서 소스코드는 5000줄 제한이 있으므로 한개 파일을 몇개 부분으로 쪼개서 설명하겠다.

(1) 생성자, 함수선언부분 

``` jsx
import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)
# 1.
        this.state = {
            p_num: 1,
            paging: {},
            boards: []
        }

        this.createBoard = this.createBoard.bind(this);
    }
# 2.
    componentDidMount() {
        BoardService.getBoards(this.state.p_num).then((res) => {
            this.setState({ 
                p_num: res.data.pagingData.currentPageNum,
                paging: res.data.pagingData,
                boards: res.data.list});
        });
    }

    createBoard() {
        this.props.history.push('/create-board/_create');
    }

    readBoard(no) {
        this.props.history.push(`/read-board/${no}`);
    }

# 3.
    listBoard(p_num) {
        console.log("pageNum : "+ p_num);
        BoardService.getBoards(p_num).then((res) => {
            console.log(res.data);
            this.setState({ 
                p_num: res.data.pagingData.currentPageNum,
                paging: res.data.pagingData,
                boards: res.data.list});
        });
    }
# 4.
    viewPaging() {
        const pageNums = [];

        for (let i = this.state.paging.pageNumStart; i <= this.state.paging.pageNumEnd; i++ ) {
            pageNums.push(i);
        }

        return (pageNums.map((page) => 
        <li className="page-item" key={page.toString()} >
            <a className="page-link" onClick = {() => this.listBoard(page)}>{page}</a>
        </li>
        ));
        
    }
# 5.
    isPagingPrev(){
        if (this.state.paging.prev) {
            return (
                <li className="page-item">
                    <a className="page-link" onClick = {() => this.listBoard( (this.state.paging.currentPageNum - 1) )} tabindex="-1">Previous</a>
                </li>
            );
        }
    }
# 6.
    isPagingNext(){
        if (this.state.paging.next) {
            return (
                <li className="page-item">
                    <a className="page-link" onClick = {() => this.listBoard( (this.state.paging.currentPageNum + 1) )} tabIndex="-1">Next</a>
                </li>
            );
        }
    }
# 7.
    isMoveToFirstPage() {
        if (this.state.p_num != 1) {
            return (
                <li className="page-item">
                    <a className="page-link" onClick = {() => this.listBoard(1)} tabIndex="-1">Move to First Page</a>
                </li>
            );
        }
    }
# 8.
    isMoveToLastPage() {
        if (this.state.p_num != this.state.paging.pageNumCountTotal) {
            return (
                <li className="page-item">
                    <a className="page-link" onClick = {() => this.listBoard( (this.state.paging.pageNumCountTotal) )} tabIndex="-1">LastPage({this.state.paging.pageNumCountTotal})</a>
                </li>
            );
        }
    }
```

\# 1. 부분

\- this.state에 현재 페이지 번호를 저장하기 위한 p_num 파라미터, 페이징 처리를 위한 paging 객체를 선언



\# 2. 부분

\- 페이지가 로딩될 때 , 글 목록만 가져오던 것을 , 페이징 객체도 같이 가져오도록 수정



\# 3. 부분

\- 지정한 페이지에 해당하는 글목록과 페이지 객체를 가져오는 함수



\# 4. 부분

\- 페이지 버튼을 표시하는 함수, 

 

\# 5. 부분

\- 이전 페이지 이동버튼을 출력하는 함수



\# 6. 부분

\- 다음 페이지 이동 버튼을 출력하는 함수



\# 7. 부분 

\- 첫페이지 이동 버튼을 출력하는 함수



\# 8. 부분

\- 마지막 페이지 이동 버튼을 출력하는 함수



(2) render()

``` jsx
    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.createBoard}> 글 작성</button>
                </div>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.no}>
                                        <td> {board.no} </td>
                                        <td> <a onClick = {() => this.readBoard(board.no)}>{board.title} </a></td>
                                        <td> {board.memberNo} </td>
                                        <td> {board.createdTime} </td>
                                        <td> {board.updatedTime} </td>
                                        <td> {board.likes} </td>
                                        <td> {board.counts} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div className ="row">
                    <nav aria-label="Page navigation example">
                        <ul className="pagination justify-content-center">
# 1.
                            {
                                this.isMoveToFirstPage()
                            }
                            {
                                this.isPagingPrev()
                            }
                            {
                                this.viewPaging()
                            }
                            {
                                this.isPagingNext()
                            }
                            {
                                this.isMoveToLastPage()
                            }
                        </ul>
                    </nav>
                </div>
            </div>
        );
    }

}

export default ListBoardComponent;
```

\# 1. 부분

\- 각 버튼을 출력하는 함수를 추가해 주었다.



**2) 데이터 통신을 위한 Service 수정하기**

\- src/service/BoardService.js에 페이징 처리를 위해 글목록 함수를 수정한다.

``` js
import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {
# 1.
    getBoards(p_num) {
        return axios.get(BOARD_API_BASE_URL + "?p_num="+ p_num);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

    getOneBoard(no) {
        return axios.get(BOARD_API_BASE_URL + "/" + no);
    }

    updateBoard(no, board) {
        return axios.put(BOARD_API_BASE_URL + "/" + no, board);
    }

    deleteBoard(no) {
        return axios.delete(BOARD_API_BASE_URL + "/" + no);
    }


}

export default new BoardService();
```

\# 1. 부분

\- 표시할 페이지 번호를 파라미터로 설정하여 통신하도록 수정



2. Spring Boot쪽 작업

**1) 페이징 유틸 클래스 추가**

``` java
package com.board.back.util;

public class PagingUtil {

	Integer currentPageNum;         // 현재 페이지 번호

	Integer objectCountTotal;       // 전체 글 수
	Integer objectCountPerPage;     // 한 화면에 출력할 오브젝트 수
	Integer objectStartNum;         // 한 화면에 표시되는 오브젝트의 시작
	Integer objectEndNum;           // 한 화면에 표시되는 오브젝트의 마지막

	Integer pageNumCountTotal;      // 전체 페이지 수
	Integer pageNumCountPerPage;    // 한 화면에 출력할 페이지 번호 수
	Integer pageNumStart;           // 한 화면에 출력되는 페이지 번호의 시작
	Integer pageNumEnd;             // 한 화면에 출력되는 페이지 번호의 마지막

	Boolean isPrev;                 // 이전 페이지 표시 여부
	Boolean isNext;                 // 다음 페이지 표시여부

	
	/**
	 * 생성자 1; 
	 * 1) 현재 페이지 번호 : 1, 한 화면에 출력할 오브젝트 수: 10, 한 화면에 출력할 페이지 번호 수 : 10으로 기본설정한다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 */
	public PagingUtil() {
		this.currentPageNum = 1;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	
	/**
	 * 생성자 2;
	 * 1) '현재 페이지 번호'를 변수로 받는다. 한 화면에 출력할 오브젝트 수: 10, 한 화면에 출력할 페이지 번호 수 : 10으로 기본설정한다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 * @param currentPageNum
	 */
	public PagingUtil(int currentPageNum) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	/**
	 * 생성자 3;
	 * 1) '현재 페이지 번호','한 화면에 출력할 오브젝트 수','한 화면에 출력할 페이지 번호 수'를 변수로 받는다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 * @param currentPageNum
	 * @param objectCountPerPage
	 * @param pageNumCountPerPage
	 */
	public PagingUtil(int currentPageNum, int objectCountPerPage, int pageNumCountPerPage) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = (0 < objectCountPerPage) ? objectCountPerPage : 10 ;
		this.pageNumCountPerPage = (0 < pageNumCountPerPage) ? pageNumCountPerPage : 10 ;

		setObjectStartAndEnd();
	}

	/**
	 * 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 */
	public void setObjectStartAndEnd() {
		this.objectEndNum = currentPageNum * objectCountPerPage;
		this.objectStartNum = (objectEndNum - 1) - (objectCountPerPage - 1);

	}

	public boolean setCalcForPaging(Integer objectCountTotal) {
		if (objectCountTotal == null) {
			return false;
		}
		
		try {
			
			this.objectCountTotal = objectCountTotal;
			this.pageNumCountTotal = (int) Math.ceil((double)objectCountTotal / objectCountPerPage);
			
			int tmpPageNumStart = ((int) Math.ceil(currentPageNum / pageNumCountPerPage) 
					* pageNumCountPerPage);
			int tmpPageNumEnd = 0;
					
			if (tmpPageNumStart == 0) {
				this.pageNumStart = 1;
				tmpPageNumEnd = tmpPageNumStart + pageNumCountPerPage;		
			} else if (tmpPageNumStart == currentPageNum) {
				this.pageNumStart = tmpPageNumStart - (pageNumCountPerPage - 1);
				tmpPageNumEnd = currentPageNum;
			} else {
				this.pageNumStart = tmpPageNumStart + 1;
				tmpPageNumEnd = pageNumStart + pageNumCountPerPage;
			}
			
			
			this.pageNumEnd = (pageNumCountTotal < tmpPageNumEnd) ? pageNumCountTotal : tmpPageNumEnd;
			
			this.isPrev = (currentPageNum > pageNumCountPerPage) ? true : false;
			this.isNext = (pageNumEnd < pageNumCountTotal || (pageNumStart < pageNumEnd && currentPageNum < pageNumCountTotal)  ) ? true : false;
			
			this.objectEndNum = (objectCountTotal < objectEndNum) ? objectCountTotal : objectEndNum;
		    return true;
		    
		} catch (Exception e) {e.printStackTrace(); return false;}
		
	}
	
	
	public boolean setCalcForPaging() {
		return setCalcForPaging(this.objectCountTotal);
	}

	
	// -- Getter & Setter --
	
	public void setObjectCountTotal(Integer objectCountTotal) {
		this.objectCountTotal = objectCountTotal;
		
	}
	
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		setObjectStartAndEnd();
	}

	public Integer getCurrentPageNum() {
		return currentPageNum;
	}

	public Integer getObjectCountTotal() {
		return objectCountTotal;
	}

	public Integer getObjectCountPerPage() {
		return objectCountPerPage;
	}

	public Integer getObjectStartNum() {
		return objectStartNum;
	}

	public Integer getObjectEndNum() {
		return objectEndNum;
	}

	public Integer getPageNumCountTotal() {
		return pageNumCountTotal;
	}

	public Integer getPageNumCountPerPage() {
		return pageNumCountPerPage;
	}

	public Integer getPageNumStart() {
		return pageNumStart;
	}

	public Integer getPageNumEnd() {
		return pageNumEnd;
	}

	public boolean isPrev() {
		return isPrev;
	}

	public boolean isNext() {
		return isNext;
	}
}
```



**2) Controller**

``` java
package com.board.back.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// get paging board # 페이징 처리를 할 수 있도록 수정
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;
		
		return boardService.getPagingBoard(p_num);
	}
	
	// get all board 
//	@GetMapping("/board")
//	public List<Board> getAllBoards() {
//		
//		return boardService.getAllBoard();
//	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		System.out.println("@PostMapping(\"/board\")");
		System.out.println(board.toString());
		return boardService.createBoard(board);
	}
	
	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}
	
	// update board
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Board board){
		
		return boardService.updateBoard(no, board);
	}
	
	// delete board
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
}
```



**3) Service**

``` java
package com.board.back.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.board.back.exception.ResourceNotFoundException;
import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;
import com.board.back.util.PagingUtil;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public int findAllCount() {
		return (int) boardRepository.count();
	}
	
	// get paging boards data
	public ResponseEntity<Map> getPagingBoard(Integer p_num) {
		Map result = null;
		
		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		pu.setObjectCountTotal(findAllCount());
		pu.setCalcForPaging();
		
		System.out.println("p_num : "+p_num);
		System.out.println(pu.toString());
		
		if (list == null || list.size() == 0) {
			return null;
		}
		
		result = new HashMap<>();
		result.put("pagingData", pu);
		result.put("list", list);
		
		return ResponseEntity.ok(result);
	}	
	
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}
	
	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}
	
	// get board one by id
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		return ResponseEntity.ok(board);
	}
	
	// update board 
	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}
	
	// delete board
	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
```



**3. 동작확인**

※ 아래의 순서대로 실행할것, 

자세한 실행 방법은 이전 포스팅을 참조 

 

**React + Springboot 게시판 만들기 3 : 게시판 프로젝트 구조와 글 목록 구현**

개요 이번 포스팅은 게시판 프로젝트 구조설명과 글 목록 구현에 대한 내용이 되겠다.1. 프로젝트 구조에...

blog.naver.com

\- xampp에서 apache와 MySQL실행

\- 이클립스에서 API실행

\- VS Code의 터미널에서 npm start

**1) 첫 페이지** 

 **※ 글 30개 기준**

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | X        |
| 이전 페이지 이동 버튼   | X        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | O        |
| 마지막 페이지 이동 버튼 | O        |

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMTAy/MDAxNjA4NjQ2MzA4NDU1.r_o_mDGsWrfJoM3eVeKNs94m23LjR31jq9kqZjXcvRwg.hlksQrGLIgAR3YHEjCDSSoKjf6Y0twED_-QLEcSAfscg.PNG.rudnfskf2/01.PNG?type=w800)

**2) 2~5 페이지** 

 **※ 글 30개 기준**

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | O        |
| 이전 페이지 이동 버튼   | X        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | O        |
| 마지막 페이지 이동 버튼 | O        |

****

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMTMx/MDAxNjA4NjQ2NDIyNjAx.f5UQujO3LQwN29e_doMpFC_ip_K9yRJ9vEigq8Ssv5Ag.b8OeMBMwV0_zlaQzuYs84IDUBPDgonywew7pUfUqZWog.PNG.rudnfskf2/02.PNG?type=w800)

**3) 6 페이지(마지막 페이지)** 

 **※ 글 30개 기준**

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | O        |
| 이전 페이지 이동 버튼   | O        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | X        |
| 마지막 페이지 이동 버튼 | X        |

****

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjY1/MDAxNjA4NjQ2NTcwMDY1.DbjXg8LF9yFV5q6pJ3YMprg_-B5Xv7_bApnohP_kHdog.AbZNQ--Lj3_SXn4Itg_nGUF8sfLAzcgWU9fB2SWpkhsg.PNG.rudnfskf2/03.PNG?type=w800)

**4) 6 페이지**

 **※ 글 30개에서 1개를 추가하여 31개가 되었다.** 

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | O        |
| 이전 페이지 이동 버튼   | O        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | O        |
| 마지막 페이지 이동 버튼 | O        |

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMjEz/MDAxNjA4NjQ3ODI0MDY4.4WleuzBZYt3k4TWfDcXWvOVmBSb_HRl3vXfy3WMFdUMg.zjnte0rcU2DSLVBb2JHH-IfhwSJLv2JFvvuoyx9Jfm4g.PNG.rudnfskf2/04.PNG?type=w800)

**5) 7 페이지(마지막 페이지)**

 **※ 글 30개에서 1개를 추가하여 31개가 되었다.** 

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | O        |
| 이전 페이지 이동 버튼   | O        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | X        |
| 마지막 페이지 이동 버튼 | X        |

****

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfNTAg/MDAxNjA4NjQ3OTI5MDQw.Ov4r10trHAQBlKDuD2T4m9QSS60LvAfCFbZsi8zFNGgg.mKIZHRfE2ZQONHUckbH8ZYHKL7FdJSAoMXKpRBQds88g.PNG.rudnfskf2/05.PNG?type=w800)

**5) 5 페이지(마지막 페이지)**

 **※ 글 31개에서 6개를 지워서 25개가 되었다.**

| 요소                    | 표시여부 |
| ----------------------- | -------- |
| 첫 페이지 이동 버튼     | O        |
| 이전 페이지 이동 버튼   | O        |
| 페이지 버튼             | O        |
| 다음 페이지 이동 버튼   | X        |
| 마지막 페이지 이동 버튼 | X        |

![img](https://mblogthumb-phinf.pstatic.net/MjAyMDEyMjJfMTU0/MDAxNjA4NjQ4MTQyNDk0.KKw7GCYVA2bWnbrzZ2D_Oa4MJyDta9qSlyj16CqWiaUg.hRU3sAtBOJT3NaLCOh9LGPYHLlCOoeC_R7CFijTkYJIg.PNG.rudnfskf2/06.PNG?type=w800)

페이징 처리 구현은 여기까지

다음 포스팅은 **회원가입 구현**이 될 예정이다.



일단 이 포스팅까지가 Part1의 마지막이므로 github주소공개

 https://github.com/leeyoungseung/board-fullstack

