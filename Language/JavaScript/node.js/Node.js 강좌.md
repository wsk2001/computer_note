# Node.js 강좌

출처: https://velopert.com/node-js-tutorials



## [강좌 01편: 소개](https://velopert.com/133)

### **Node.js 가 뭐지?**

NodeJS 는 구글 크롬의 자바스크립트 엔진 (V8 Engine) 에 기반해 만들어진 서버 사이드 플랫폼입니다. 2009년에 Ryan Dahl에 의해 개발되었으며 현시점 (2016-02-07) 최신 버전은 v5.5.0 입니다. NodeJS 공식 사이트에서 제공되는 정보는 다음과 같습니다.

Node.js®는 Chrome V8 JavaScript 엔진으로 빌드된 JavaScript 런타임입니다. Node.js는 이벤트 기반, 논 블로킹 I/O 모델을 사용해 가볍고 효율적입니다. Node.js의 패키지 생태계인 npm은 세계에서 가장 큰 오픈 소스 라이브러리이기도 합니다.
(출처: <https://nodejs.org/ko/>)

### *입문자들의 오해*

Node는 웹서버가 **아니랍니다.** Node 자체로는 아무것도 하지 않습니다 – 아파치 웹서버처럼 HTML 파일 경로를 지정해주고 서버를 열고 그런 설정이 없습니다. 단, HTTP 서버를 직접 작성해야합니다 (일부 라이브러리의 도움을 받으면서). Node.js 는 그저 코드를 실행할 수 있는 하나의 방법에 불과한 그저 JavasScript 런타임일 뿐입니다.

## **Node.js 의 특징**

- **비동기 I/O 처리 / 이벤트 위주:** Node.js 라이브러리의 모든 API는 비동기식입니다, 멈추지 않는다는거죠 (Non-blocking). Node.js 기반 서버는 API가 실행되었을때, 데이터를 반환할때까지 기다리지 않고 다음 API 를 실행합니다. 그리고 이전에 실행했던 API가 결과값을 반환할 시, NodeJS의 이벤트 알림 메커니즘을 통해 결과값을 받아옵니다.
- **빠른 속도:** 구글 크롬의 V8 자바스크립트 엔진을 사용하여 빠른 코드 실행을 제공합니다.
- **단일 쓰레드 / 뛰어난 확장성:** Node.js는 이벤트 루프와 함께 단일 쓰레드 모델을 사용합니다. 이벤트 메커니즘은 서버가 멈추지않고 반응하도록 해주어 서버의 확장성을 키워줍니다.  반면,  일반적인 웹서버는 (Apache) 요청을 처리하기 위하여 제한된 쓰레드를 생성합니다. Node.js 는 쓰레드를 한개만 사용하고  Apache 같은 웹서버보다 훨씬 많은 요청을 처리할 수 있습니다.
- **노  버퍼링:** Node.js 어플리케이션엔 데이터 버퍼링이 없고, 데이터를 chunk로 출력합니다.
- **라이센스:** Node.js 는 MIT License가 적용되어있습니다.

> ### Node.js 는 누가쓸까?

Node.js는 eBay, GoDaddy, Microsoft, Paypal, Yahoo! 등 많은곳에서 사용되고 있답니다.
다음은 Node.js 를 사용하는 프로젝트, 어플리케이션 및 회사의 리스트를 포함하고있는 GitHub 위키 링크입니다.

[Node.js 를 사용하는 프로젝트 / 어플리케이션 / 회사](https://github.com/joyent/node/wiki/projects,-applications,-and-companies-using-node)

> ### Node.js 를 어디에 쓸까?

다음과 같은 분야에 Node.js 가 사용된다면 뛰어난 효율성을 달성 할 수 있습니다.

- 입출력이 잦은 어플리케이션
- 데이터 스트리밍 어플리케이션
- 데이터를 실시간으로 다루는 어플리케이션
- JSON API 기반 어플리케이션
- 싱글페이지 어플리케이션

> ### Node.js 를 쓰지 말아야 할 곳?

CPU 사용률이 높은 어플리케이션에선 Node.js 사용을 권장하지 않습니다.



## [강좌 02편: 작업환경 설정](https://velopert.com/195)

## 로컬 작업환경 설정

지금 사용하고 계신 PC (Windows/MAC) 이나 리눅스 서버에 Node.js 런타임을 설치합니다.
우선 개발 공부가 목적이시라면 스크롤을 아래로 내려 클라우드 IDE 사용란을 읽어주세요.

### Windows / MAC

[![sdf](https://velopert.com/wp-content/uploads/2016/02/sdf.png)](https://nodejs.org/en/download/)

*(위 이미지를 클릭하면 인스톨러 다운로드 페이지로 이동됩니다)*

윈도우와 맥의 경우, 인스톨러를 통해 자동으로 작업환경을 설정 할 수 있습니다.

### LINUX

 

[데비안 계열](https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-an-ubuntu-14-04-server)

```
$ sudo apt-get update
$ sudo apt-get install nodejs
$ sudo apt-get install npm
$ sudo ln -s /usr/bin/nodejs /usr/bin/node
```

[레드햇 계열](https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-a-centos-7-server)

```
$ sudo yum install epel-release
$ sudo yum install nodejs
$ sudo yum install npm
```

 

## 클라우드 IDE 작업환경 설정

 

![이미지 10](https://velopert.com/wp-content/uploads/2016/02/%EC%9D%B4%EB%AF%B8%EC%A7%80-10.png)

유명한 Cloud IDE 중 하나인 Cloud9 을 사용하시면 별도의 작업환경 설정이 필요 없이 바로 코딩을 시작 하실 수 있습니다.
Cloud9 ([http://c9.io](http://c9.io/))

클라우드9 가입 / Workspace 생성하는 과정은 생략하도록 하겠습니다.

Workspace를 만들때 template 는 Custom 으로 설정해주세요.

![이미지 12](https://velopert.com/wp-content/uploads/2016/02/%EC%9D%B4%EB%AF%B8%EC%A7%80-12.png)

## 작업환경 설정 완료: 파일 실행 해보기

작업환경 설정이 완료되었다면 파일을 실행해봅시다.

main.js 파일을 생성해주세요.

```
/* Hello World in NodeJS */
console.log("Hello, World!");
```

그 다음, 콘솔에서 다음 명령어를 입력하여 main.js 를 실행해보세요.

```
$ node main.js
```

작업환경이 성공적으로 설정되었다면 Hello World! 이 출력됩니다.



## [강좌 03편: Node.js 맛보기](https://velopert.com/210)

## Node.js Application 만들기

### 

### **1단계:** 필요한 모듈 import 하기

어플리케이션에 필요한 모듈을 불러올땐 **require** 명령을 사용합니다.
다음 코드는 HTTP 모듈을불러오고 반환되는 HTTP 인스턴스를 http 변수에 저장합니다.

```
var http = require("http");
```

### 2단계: 서버 생성하기

이번 단계에선, 1단계에서 만들은 http 인스턴스를 사용하여 **http.createServer()** 메소드를 실행합니다. 그리고 **listen** 메소드를 사용하여 포트 8081과 bind 해줍니다.
http.createServer() 의 매개변수로는 request와 response를 매개변수로 가지고있는 함수를 넣어줍니다.

다음 코드는 언제나 “Hello World” 를 리턴하는 포트 8081의 웹서버를 생성해줍니다.

```
http.createServer(function(request, response){
    /* 
        HTTP 헤더 전송
        HTTP Status: 200 : OK
        Content Type: text/plain
    */
    response.writeHead(200, {'Content-Type': 'text/plain'});
    
    /*
        Response Body 를 "Hello World" 로 설정
    */
    response.end("Hello World\n");
}).listen(8081);
```

### **3단계: 서버 테스트 해보기**

1단계와 2단계를 파일 main.js에 합쳐서 작성해보세요

```
var http = require("http");

http.createServer(function(request, response){
    /* 
        HTTP 헤더 전송
        HTTP Status: 200 : OK
        Content Type: text/plain
    */
    response.writeHead(200, {'Content-Type': 'text/plain'});
    
    /*
        Response Body 를 "Hello World" 로 설정
    */
    response.end("Hello World\n");
}).listen(8081);

console.log("Server running at http://127.0.0.1:8081");
```

서버를 실행해봅시다.

```
$ node main.js
```

서버가 성공적으로 실행됐다면 다음 텍스트가 출력됩니다.

```
Server running at http://127.0.0.1:8081/
```

브라우저에서[ http://127.0.0.1:8081/](http://127.0.0.1:8081/) 을 열으면 다음과 같은 결과를 확인 할 수 있습니다.

------

**[TIP] Cloud9 을 사용하신다면 아래버튼을 눌러주세요.**







------

## 출력물

 

![이미지 21](https://velopert.com/wp-content/uploads/2016/02/%EC%9D%B4%EB%AF%B8%EC%A7%80-21.png)

축하합니다! 조금은 초라하지만..  첫 HTTP 서버를 완성하셨습니다!



## [강좌 04편: REPL 터미널](https://velopert.com/235)

REPL 은 **R**ead **E**val **P**rint **L**oop의 약자입니다. 이는 윈도우 커맨드, 혹은 UNIX/LINUX Shell 처럼 사용자가 커맨드를 입력하면 시스템이 값을 반환하는 환경을 가르킵니다.

Node.js는 REPL 환경과 함께 제공되며 다음과 같은 기능을 수행 할 수 있습니다.

- **Read** – 유저의 값을 입력 받아 JavaScript 데이터 구조로 메모리에 저장합니다.
- **Eval** – 데이터를 처리(Evaluate) 합니다.
- **Print** – 결과값을 출력합니다.
- **Loop** – Read, Eval, Print 를 유저가 Ctrl+C를 두번 눌러 종료할때까지 반복합니다.

Node.js 의 REPL 환경은 자바스크립트 코드를 테스팅 및 디버깅할때 유용하게 사용됩니다.

 

## REPL 시작하기

REPL 은 쉘/콘솔에 파라미터 없이 node 를 실행하여 실행 할 수 있습니다.

```
$ node
>
```

### 간단한 표현식 사용

Node.js REPL 커맨드 프롬프트에 간단한 연산자를 사용해봅시다.

```
$ node
> 1 + 5
6
> 1 + ( 6 / 2 ) - 3
1
>
```

### 변수 사용

다른 스크립트처럼, 변수에 값을 저장하고 나중에 다시 출력 할 수 있습니다.

만약 **var** 키워드를 사용하면 명령어를 입력했을때 변수의 값이 출력되지 않고, **var** 키워드를 사용하지 않으면 값이 출력됩니다.

또한, console.log() 를 통해 출력 할 수 있습니다.

```
$ node
> x = 10
10
> var y = 5
undefined
> x + y
15
> console.log("Value is " + ( x + y ))
Value is 15
undefined
```

### Multi-Line 표현식 사용

do-while 루프를 REPL에서 실행해봅시다.

```
$ node
> var x = 0
undefined
> do {
... x++;
... console.log("x: " + x);
... } while ( x < 3 );
x: 1
x: 2
x: 3
undefined
>
```

### Underscore ( _ ) 변수

밑줄 _ 변수는 최근 결과값을 지칭합니다.

```
$ node
> var x = 10;
undefined
> var y = 5;
undefined
> x + y;
15
> var sum = _
undefined
> console.log(sum)
15
undefined
>
```

 

## REPL 커맨드

- **Ctrl+C** – 현재 명령어를 종료합니다.

- **Ctrl+C (2번)**  – Node REPL 을 종료합니다.

- **Ctrl+D** – Node REPL을 종료합니다.

- **위/아래 키** – 명령어 히스토리를 탐색하고 이전 명령어를 수정합니다.

- **Tab** – 현재 입력란에 쓴 값으로 시작하는 명령어 / 변수 목록을 확인합니다.

- **.help** – 모든 커맨드 목록을 확인합니다.

- .**break** – 멀티 라인 표현식 입력 도중 입력을 종료합니다.

- **.clear** – .break 와 같습니다.

- **.save filename** – 현재 Node REPL 세션을 파일로 저장합니다.

- **.load filename** – Node REPL 세션을 파일에서 불러옵니다.



## [강좌 05편: NPM](https://velopert.com/241)

Node Package Manager (NPM) 은 두가지의 주요 기능을 지니고 있습니다.

- [NPMSearch](https://npmsearch.com/) 에서 탐색 가능한 Node.js 패키지/모듈 저장소
- Node.js 패키지 설치 및 버전 / 호환성 관리를 할 수 있는 커맨드라인 유틸리티

 

npm이 제대로 설치되있는지 확인하려면 다음 명령어를 입력하세요:

```
$ npm --version
3.7.1
```

npm 버전이 구버전이라면 다음 명령어로 쉽게 최신버전으로 업데이트 할 수 있습니다:

```
$ sudo npm install npm -g
npm http GET https://registry.npmjs.org/npm
npm http 200 https://registry.npmjs.org/npm
npm http GET https://registry.npmjs.org/npm/-/npm-3.7.1.tgz
npm http 200 https://registry.npmjs.org/npm/-/npm-3.7.1.tgz
/usr/local/bin/npm -> /usr/local/lib/node_modules/npm/bin/npm-cli.js
npm@3.7.1 /usr/local/lib/node_modules/npm
```

NPM 에서 일부 패키지를 설치 할 때 python 을 요구하므로 호환성을 맞추기 위하여 파이썬 런타임도 설치하도록 합시다.

##  NPM을 사용하여 모듈 설치하기

```
npm install <모듈 이름>
```

예를들어 유명한 Node.js 웹 프레임워크중 하나인 **express**를 설치한다면 다음 명령어를 입력하면됩니다.

```
$ npm install express
```

설치하면 여러분의 js에서 이렇게 모듈을 사용 할 수 있습니다.

```
var express = require('express');
```

 

## 글로벌 vs 로컬 모듈 설치

기본적으로는, npm은 모듈을 로컬모드로 설치합니다. 로컬모드란건, 패키지를 명령어를 실행한 디렉토리안에 있는 node_modules에 설치하는것을 의미합니다.

글로벌 설치는 시스템 디렉토리에 설치하는것을 의미합니다. 한번 express 를 글로벌 모드로 설치해볼까요?

```
$ sudo npm install express -g
/usr/lib
└─┬ express@4.13.4
 ├─┬ accepts@1.2.13
 │ ├─┬ mime-types@2.1.9
 │ │ └── mime-db@1.21.0
 │ └── negotiator@0.5.3
 .... 길어서 생략....
 │ └── statuses@1.2.1
 ├── utils-merge@1.0.0
 └── vary@1.0.1
```

보이다시피, 현재 경로가 아닌 /usr/lib/node_modules 에 모듈을 설치합니다.
시스템에 저장하므로, 루트 계정이 아니라면 앞에 sudo를 붙여주어야합니다.
글로벌 모드로 설치하였을때는, node 어플리케이션에서 바로 require 할 수는 없습니다.
단, 다음처럼 npm link 명령어를 입력하고나면 해당 모듈을 불러올 수 있습니다.

```
$ npm install -g express
$ cd [local path]/project
$ npm link express
```

## package.json

package.json 은 노드 어플리케이션 / 모듈의 경로에 위치해 있으며 패키지의 속성을 정의합니다.

다음은 express로 프로젝트를 생성했을때 생성되는 package.json 입니다.

```
{
  "name": "myapp",
  "version": "0.0.0",
  "private": true,
  "scripts": {
    "start": "node ./bin/www"
  },
  "dependencies": {
    "body-parser": "~1.13.2",
    "cookie-parser": "~1.3.5",
    "debug": "~2.2.0",
    "express": "~4.13.1",
    "jade": "~1.11.0",
    "morgan": "~1.6.1",
    "serve-favicon": "~2.3.0"
  }
}
```

보시다시피 이 파일은 프로젝트가 의존하는 모듈과 모듈버전의 정보를 담고있습니다.

package.json 에 관한 자세한 내용은[ 감성 프로그래밍 블로그](http://programmingsummaries.tistory.com/385)에서 읽어보실 수 있습니다.

 

## 모듈 제거

다음 명령어로 설치된 모듈을 제거 할 수 있습니다.

```
$ npm uninstall express
```

 

## 모듈 업데이트

다음 명령어로 모듈을 업데이트 할 수 있습니다.

```
$ npm update express
```

 

## 모듈 검색

다음 명령어로 모듈을 검색 할 수 있습니다.

```
$ npm search express
```

이 명령어는 처음 이용 할 때 메모리를 굉장히 많이 잡아먹습니다.

클라우드 IDE를 사용하거나 서버에 램이 1G 정도라면 매우 오래걸리거나 에러가 납니다.

그럴 경우엔 [NPMSearch](https://npmsearch.com/) 에서 검색을 하면 됩니다.



## [강좌 06편: Callback Function 개념](https://velopert.com/255)

## Callback Function 이 뭘까?

자바스크립트에서는, 함수(function)는 일급 객체입니다. 즉, 함수는 *Object* 타입이며 다른 일급객체와 똑같이 사용 될 수 있습니다. (String, Array, Number, 등등..) function 자체가 객체이므로 변수안에 담을 수 도 있고 인수로서 다른 함수에 전달 해 줄수도있고, 함수에서 만들어질수도있고 반환 될수도있습니다.

Callback function은, 특정 함수에 매개변수로서 전달된 함수를 지칭합니다.

그리고 그 Callback function은 그 함수를 전달받은 함수 안에서 호출되게 됩니다.

이해를 돕기 위하여 jQuery에서 사용된 callback function 예제를 살펴봅시다.

```
// 보시면, click 메소드의 인수가 변수가 아니라 함수이죠?
// click 메소드의 인수가 바로 Callback 함수입니다.
$("#btn_1").click(function() {
  alert("Btn 1 Clicked");
});
```

설명: click 메소드에 이름이 없는 callback function을 인수로 전달해줍니다.
그리고 jQuery 안의 click 메소드에서는,  마우스 클릭이 있으면 callback function을 호출하게 설정을 하지요.

> ### 흠.. 이걸 근데 대체 왜 쓸까?

Node.js 에선 Callback 함수가 매우 많이 사용된답니다.
콜백의 개념이 어느정도 이해가 됐다면 Node.js 에서의 예제를 한번 살펴보겠습니다.

## Blocking Code

첫번째 예제는 Callback 함수가 *사용되지 않는*, Blocking Code 예제입니다.

말그대로 어떤 작업을 실행하고 기다리면서 코드가 “막히”게 됩니다.

우선, input.txt 라는 텍스트파일을 생성해줍니다.

```
Let's understand what is a callback function.
What the HELL is it?
```

그 다음, main.js 를 작성하세요.

```
var fs = require("fs");

var data = fs.readFileSync('input.txt');

console.log(data.toString());
console.log("Program has ended");
```

이제 결과값을 확인해볼까요?

```
$ node main.js
Let's understand what is a callback function.
What the HELL is it?
Program has ended
```

보다시피, 텍스트를 출력하고나서 프로그램이 종료되었다는 문구를 출력합니다.

 

## Non-Blocking Code

두번째 예제는 Callback 함수가 사용된 Non-Blocking Code 예제입니다.
위 예제와는 반대로 함수가 실행될 때, 프로그램이 함수가 끝날때까지 기다리지않고
바로 그 아래에있는 코드들을 실행하게 되지요. 그 다음에 함수에있던 작업이 다 끝나면 콜백함수를 호출합니다.

input.txt 는 위 예제에서 사용한 똑같은 파일을 사용합니다.

main.js 를 이렇게 수정해보세요.

```
var fs = require("fs");

fs.readFile('input.txt', function (err, data) {
    if (err) return console.error(err);
    console.log(data.toString());
});

console.log("Program has ended");
```

**모든 Node 어플리케이션의 비동기식 함수에서는 첫번째 매개변수로는 error를, 마지막 매개변수로는 callback 함수를 받습니다.**
fs.readFile() 함수는 비동기식으로 파일을 읽는 함수이고, 도중에 에러가 발생하면 err 객체에 에러 내용을 담고
그렇지 않을 시에는 파일 내용을 다 읽고 출력합니다.

결과는?

```
Program has ended
Let's understand what is a callback function.
What the HELL is it?
```

readFile() 메소드가 실행 된 후, 프로그램이 메소드가 끝날때까지 대기하지 않고 곧바로 다음 명령어로 진행하였기 때문에,
프로그램이 끝났다는 메시지를 출력 한 후에, 텍스트 내용을 출력했습니다.

그렇다고 해서 프로그램이 끝나고나서 텍스트 내용을 출력한것은 아닙니다.
프로그램이 실질적으로 끝난건 텍스트가 출력된 후입니다.

만약에 readFile() 다음에 실행되는 코드가 그냥 console.out() 이 아니라
readFile() 보다 작업시간이 오래걸리는 코드였다면 텍스트 출력을 먼저 하게되겠죠?

------

> **callback 함수를 사용하여 이렇게 프로그램의 흐름을 끊지 않음으로서,****Non-Blocking 코드를 사용하는 서버는 Blocking 코드를 사용하는 서버보다****더 많은 양의 요청을 빠르게 처리 할 수 있게됩니다.**



## [강좌 07편: Event Loop](https://velopert.com/267)

Node.js 에선 Event를 매우 많이 사용하고, 이 때문에 다른 비슷한 기술들보다 훨씬 빠른 속도를 자랑합니다.

Node.js 기반으로 만들어진 서버가 가동되면, 변수들을 initialize 하고, 함수를 선언하고 이벤트가 일어날때까지 기다립니다.

이벤트 위주 (Event-Driven) 어플리케이션에서는, 이벤트를 대기하는 메인 루프가 있습니다.
그리고 이벤트가 감지되었을시 Callback함수를 호출합니다.

![ff](https://velopert.com/wp-content/uploads/2016/02/ff.png)

이벤트가 콜백과 비슷해 보일 수 도 있습니다. 차이점은, 콜백함수는 비동기식 함수에서 결과를 반환할때 호출되지만,
이벤트핸들링은 옵저버 패턴에 의해 작동됩니다.

------

*“옵저버요?”*
![이미지 24](https://velopert.com/wp-content/uploads/2016/02/%EC%9D%B4%EB%AF%B8%EC%A7%80-24.png)

디자인 패턴 중 하나 입니다.  자세한 내용은 [위키피디아](https://ko.wikipedia.org/wiki/%EC%98%B5%EC%84%9C%EB%B2%84_%ED%8C%A8%ED%84%B4)를 참조하세요.

------

이벤트를 대기하는 (EventListeners)  함수들이 옵저버 역할을 합니다. 옵저버들이 이벤트를 기다리다가,
이벤트가 실행되면 이벤트를 처리하는 함수가 실행됩니다.

Node.js 에는 **events** 모듈과 **EventEmitter** 클래스가 내장되어있는데요,
이를 사용하여 이벤트와 이벤트핸들러를 연동(bind) 시킬 수 있습니다:

```
// events 모듈 사용
var events = require('events');

// EventEmitter 객체 생성
var eventEmitter = new events.EventEmitter();
```

이벤트 핸들러와 이벤트를 연동시키는건 다음과 같습니다:

```
// event와 EventHandler 를 연동(bind)
// eventName 은 임의로 설정 가능
eventEmitter.on('eventName', eventHandler);
```

프로그램안에서 이벤트를 발생시킬땐 다음 코드를 사용합니다:

```
eventEmitter.emit('eventName');
```

 

## 이벤트 핸들링 예제

위에서 배운것을 토대로 이벤트를 다루는 예제를 작성해보도록 하겠습니다.

```
// events 모듈 사용
var events = require('events');

// EventEmitter 객체 생성
var eventEmitter = new events.EventEmitter();

// EventHandler 함수 생성
var connectHandler = function connected(){
    console.log("Connection Successful");
    
    // data_recevied 이벤트를 발생시키기
    eventEmitter.emit("data_received");
}

// connection 이벤트와 connectHandler 이벤트 핸들러를 연동
eventEmitter.on('connection', connectHandler);

// data_received 이벤트와 익명 함수와 연동
// 함수를 변수안에 담는 대신에, .on() 메소드의 인자로 직접 함수를 전달
eventEmitter.on('data_received', function(){
    console.log("Data Received");
});

// connection 이벤트 발생시키기
eventEmitter.emit('connection');

console.log("Program has ended");
```

### 출력물

```
$ node main.js
Connection Successful
Data Received
Program has ended
```

 

## EventEmitter 메소드

추후 수정 예정..  EventEmitter 의 상세한 내용은 [NodeJS Documentation](https://nodejs.org/api/events.html#events_class_eventemitter)을 확인해주세요.



## [강좌 08편: HTTP Module](https://velopert.com/287)

Node.JS 강좌 03편에서 맛보기로 Hello World 만을 리턴하는 웹서버를 만들어봤었습니다.
이번 강좌에서는 http 모듈을 이용해  더 기능이 향상된 웹서버과 웹클라이언트를 코딩해보도록 하겠습니다.

## HTTP 서버 예제

우선 index.html 을 생성하세요.

```
<html>
    <head>
        <title>Sample Page</title>
    </head>        
    <body>
        Hello World!
    </body>
</html>
```

다음엔 server.js 를 작성하세요.

```
var http = require('http');
var fs = require('fs');
var url = require('url');


// 서버 생성
http.createServer( function (request, response) {  
   // URL 뒤에 있는 디렉토리/파일이름 파싱
   var pathname = url.parse(request.url).pathname;
   
   
   console.log("Request for " + pathname + " received.");
   
   // 파일 이름이 비어있다면 index.html 로 설정
   if(pathname=="/"){
       pathname = "/index.html";
   }
   
   // 파일을 읽기
   fs.readFile(pathname.substr(1), function (err, data) {
      if (err) {
         console.log(err);
         // 페이지를 찾을 수 없음
         // HTTP Status: 404 : NOT FOUND
         // Content Type: text/plain
         response.writeHead(404, {'Content-Type': 'text/html'});
      }else{	
         // 페이지를 찾음	  
         // HTTP Status: 200 : OK
         // Content Type: text/plain
         response.writeHead(200, {'Content-Type': 'text/html'});	
         
         // 파일을 읽어와서 responseBody 에 작성
         response.write(data.toString());		
      }
      // responseBody 전송
      response.end();
   });   
}).listen(8081);


console.log('Server running at http://127.0.0.1:8081/');
```

클라이언트에서 서버에 접속을하면 URL에서 열고자 하는 파일을 파싱하여 열어줍니다.
파일이 존재하지 않는다면 콘솔에 에러 메시지를 출력합니다.

 

### 출력물

```
$ node server.js
Server running at http://127.0.0.1:8081/
Request for / received.
Request for /showmeerror received.
{ [Error: ENOENT: no such file or directory, open 'showmeerror']
  errno: -2,
  code: 'ENOENT',
  syscall: 'open',
  path: 'showmeerror' }
Request for /index.html received.
```

서버를 실행하고 다음 링크들을 들어갔을때 뜨는 출력물입니다:

1. <http://127.0.0.1:8081/>
2. <http://127.0.0.1:8081/showmeerror>
3. <http://127.0.0.1:8081/index.html>

 

### HTTP 클라이언트 예제

------

```
var http = require('http');

// HTTPRequest의 옵션 설정
var options = {
   host: 'localhost',
   port: '8081',
   path: '/index.html'  
};

// 콜백 함수로 Response를 받아온다
var callback = function(response){
   // response 이벤트가 감지되면 데이터를 body에 받아온다
   var body = '';
   response.on('data', function(data) {
      body += data;
   });
   
   // end 이벤트가 감지되면 데이터 수신을 종료하고 내용을 출력한다
   response.on('end', function() {
      // 데이저 수신 완료
      console.log(body);
   });
}
// 서버에 HTTP Request 를 날린다.
var req = http.request(options, callback);
req.end();
```

14번과 19번 줄을 보면 response.on() 을 사용하죠. .on() 메소드, 익숙하지 않나요?
response 는 [강좌 07편](https://velopert.com/267) Event Loop에서 봤었던 EventEmitter 클래스를 상속한 객체입니다.

 

### 출력물

```
$ node client.js
<html>
    <head>
        <title>Sample Page</title>
    </head>        
    <body>
        Hello World!
    </body>
</html>
```