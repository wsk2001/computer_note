# Express 웹 프레임워크



출처: https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs



# Express 웹 프레임워크 (Node.js/JavaScript의 활용)

Express는 JavaScript로 작성되고 Node.js 런타임 환경에서 구동되는 인기 있는 웹 프레임워크입니다. 이 장에서는 Express 프레임워크의 몇 가지 장점과 개발환경 설치 방법, 웹 개발과 배포작업의 방법을 다룹니다.

## [알아야할 것들](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs#알아야할_것들)

이 장의 내용은 Server-side 웹 프로그래밍과 웹 프레임워크에 대한 이해가 필요합니다. 잘 모르겠다면 [Server-side website programming first steps](https://developer.mozilla.org/ko/docs/Learn/Server-side/First_steps) 을 먼저 확인해보세요. 일반적인 프로그래밍 컨셉과 [JavaScript](https://developer.mozilla.org/ko/docs/Web/JavaScript) 의 지식이 요구되지만, 핵심까지 세세하게 알 필요는 없습니다.

**Note**: 여기서는 클라이언트 측에서의 자바스크립에 관한 많은 유용한 자료들을 이용할 수 있다. [JavaScript](https://developer.mozilla.org/ko/docs/Web/JavaScript), [JavaScript Guide](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide), [JavaScript Basics](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/JavaScript_basics), [JavaScript](https://developer.mozilla.org/en-US/docs/Learn/JavaScript) (한 번 배워보자). 자바스크립트의 핵심과 컨셉은 Node.js를 이용한 서버측 개발과 같으므로, 여기의 자료들을 이용하는 게 좋을 것이다. Node.js 는HTTP서버를 구축하고 파일 시스템에 접근하는 등의 브라우저가 필요없는 환경에서에서 유용한 기능을 제공하는 [additional APIs](https://nodejs.org/dist/latest-v6.x/docs/api/documentation.htm)를 제공하지만, 브라우저나 DOM에서 작동되는 자바스크립트 API는 지원하지 않는다.

다음 목차에서는 Node.js와 Express 그리고 인터넷과 책에서의 방대한 좋은 자료들에 있는 정보들을 알려준다. 이들은 [How do I get started with Node.js](https://stackoverflow.com/a/5511507/894359) (StackOverflow) 나 [What are the best resources for learning Node.js?](https://www.quora.com/What-are-the-best-resources-for-learning-Node-js?) (Quora)에 참고해보자



### How do I get started with Node.js 

Q) Node.JS를 시작하는 데 유용한 리소스가 있습니까? 좋은 튜토리얼, 블로그 또는 책이 있습니까?

물론 공식 웹사이트 http://nodejs.org/를 방문했지만 그들이 가지고 있는 문서가 좋은 출발점이라고 생각하지 않았습니다.



A) You can follow these tutorials to get started

**Tutorials**

- [NodeSchool.io interactive lessons](http://nodeschool.io/)
- [The Art of Node (an introduction to Node.js)](https://github.com/maxogden/art-of-node/#the-art-of-node)
- [Hello World](http://www.nodebeginner.org/#hello-world)
- [Hello World Web Server](http://www.nodebeginner.org/#building-the-application-stack) (paid)
- [Node.js guide](http://nodeguide.com/)
- [Build a blog with Node.js, express and MongoDB](http://howtonode.org/express-mongodb)
- [Node.js for Beginners](http://net.tutsplus.com/tutorials/javascript-ajax/node-js-for-beginners/)
- [Learn Node.js Completely and with Confidence](http://javascriptissexy.com/learn-node-js-completely-and-with-confidence/)
- [Node JS Processing Model – Single Threaded Model with Event Loop Architecture](http://www.journaldev.com/7462/node-js-processing-model-single-threaded-model-with-event-loop-architecture)
- [RisingStack's Node Hero Series](https://blog.risingstack.com/node-hero-tutorial-getting-started-with-node-js/)
- [Great Node.js tutorials voted by the programming community](https://hackr.io/tutorials/learn-node-js)
- [Node.js Tutorial](https://www.tutorialkart.com/nodejs/nodejs-tutorial/)
- [30 days of Node](https://www.nodejsera.com/30-days-of-node.html)

**Developer Sites**

- [Joyent's developer site for node](http://www.joyent.com/developers/node)
- [Tutorials Teacher](https://www.tutorialsteacher.com/nodejs/nodejs-tutorials)

**Videos**

- [Node Tuts](http://nodetuts.com/) (Node.js video tutorials)
- [Einführung in Node.js (in German)](https://vimeo.com/thenativeweb)
- [Introduction to Node.js with Ryan Dahl](http://www.youtube.com/watch?v=jo_B4LTHi3I)
- [Node.js: Asynchronous Purity Leads to Faster Development](http://www.infoq.com/presentations/nodejs)
- [Parallel Programming with Node.js](http://www.infoq.com/presentations/Parallel-Programming-with-Nodejs)
- [Server-side JavaScript with Node, Connect & Express](http://vimeo.com/18077379)
- [Node.js First Look](http://www.lynda.com/Nodejs-tutorials/Nodejs-First-Look/101554-2.html)
- [Node.js with MongoDB](http://www.youtube.com/watch?v=0_GNHWZHc-o)
- [Ryan Dahl's Google Tech Talk](http://www.youtube.com/watch?v=F6k8lTrAE2g)
- [Real Time Web with Node.js](http://node.codeschool.com/levels/1)
- [Node.js Tutorials for Beginners](https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBMdkKFn3HasZnnAqVjzHn_)
- [Pluralsight courses (paid)](http://www.pluralsight.com/search/?searchTerm=Node.js)
- [Udemy Learn and understand Nodejs (paid)](https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwj6oMvR35nTAhWFuY8KHfC-BFoQFggZMAA&url=https%3A%2F%2Fwww.udemy.com%2Funderstand-nodejs%2F&usg=AFQjCNF3WkS8cFyYrTgzgd5V_S1ANKkZJw&sig2=rD4t8JYDPvQKjwAMzQnH7Q)
- [The New Boston](https://www.youtube.com/watch?v=-u-j7uqU7sI&list=PL6gx4Cwl9DGBMdkKFn3HasZnnAqVjzHn_)

**Screencasts**

- [Learn All The Nodes](http://learnallthenodes.com/)
- [Node Tuts](http://nodetuts.com/)
- [Einführung in Node.js (in German)](https://vimeo.com/thenativeweb)
- [NodeCasts](http://nodecasts.net/)

**Books**

- [The Node Beginner Book](http://nodebeginner.org/)
- [Mastering Node.js](https://github.com/tj/masteringnode)
- [Up and Running with Node.js](http://chimera.labs.oreilly.com/books/1234000001808/index.html)
- [Node.js in Action](http://www.manning.com/cantelon/)
- [Smashing Node.js: JavaScript Everywhere](https://rads.stackoverflow.com/amzn/click/com/B008Z5OEUY)
- [Node.js & Co. (in German)](http://www.amazon.de/dp/389864829X)
- [Sam's Teach Yourself Node.js in 24 Hours](http://nodejsbook.io/)
- [Most detailed list of free JavaScript Books](http://jsbooks.revolunet.com/)
- [Mixu's Node Book](http://book.mixu.net/node/index.html)
- [Node.js the Right Way: Practical, Server-Side JavaScript That Scale](http://pragprog.com/book/jwnode/node-js-the-right-way)
- [Beginning Web Development with Node.js](https://leanpub.com/webdevelopmentwithnodejs)
- [Node Web Development](http://www.packtpub.com/node-javascript-web-development/book)
- [NodeJS for Righteous Universal Domination!](http://node.codeschool.com/)

**Courses**

- [Real Time Web with Node.js](http://node.codeschool.com/)
- [Essential Node.js from DevelopMentor](http://www.develop.com/training-course/nodejs-featuring-node-npm-express-mocha-mongodb-with-mongoose)
- [Freecodecamp - Learn to code for free](https://www.freecodecamp.org/)
- [Udemy - The Complete Node.js Developer Course (3rd Edition) (paid)](https://www.udemy.com/course/the-complete-nodejs-developer-course-2/)

**Blogs**

- [The Node.js blog](http://blog.nodejs.org/)
- [How To Node](http://howtonode.org/)
- [DailyJS](http://dailyjs.com/)
- [Nodejitsu blog](http://blog.nodejitsu.com/)
- [Ryan Wilcox's Whitepaper](http://www.wilcoxd.com/whitepapers/node_js/)
- [devthought](http://www.devthought.com/)

**Podcasts**

- [NodeUp](http://nodeup.com/)

**JavaScript resources**

- [Crockford's videos](https://www.youtube.com/playlist?list=PL7664379246A246CB) (must see!)
- [Essential JavaScript Design Patterns For Beginners](http://www.addyosmani.com/resources/essentialjsdesignpatterns/book/)
- [JavaScript garden](http://bonsaiden.github.com/JavaScript-Garden/)
- [JavaScript Patterns](http://oreilly.com/catalog/9780596806767) book
- [JavaScript: The Good Parts](http://oreilly.com/catalog/9780596517748/) book
- [Eloquent javascript ](http://eloquentjavascript.net/)book

**Node.js Modules**

- [Search for registered Node.js modules](http://npmjs.org/)
- [A curated list of awesome Node.js libraries](https://github.com/sindresorhus/awesome-nodejs)
- [Wiki List on GitHub/Joyent/Node.js](https://github.com/joyent/node/wiki/modules) (start here last!)

**Other**

- [JSApp.US - like jsfiddle, but for Node.js](http://jsapp.us/)
- [Node with VJET JS (for Eclipse IDE)](https://www.ebayopensource.org/index.php/VJET/NodeJS)
- Production sites with published source:
  - [Node Knockout Hackathon](http://nodeknockout.com/) [(source)](https://github.com/nko3/website)
  - [Freecodecamp - Learn to code for free](https://www.freecodecamp.org/) [(source)](https://github.com/freeCodeCamp/freeCodeCamp)
- [Useful Node.js Tools, Tutorials and Resources](http://coding.smashingmagazine.com/2011/09/16/useful-node-js-tools-tutorials-and-resources/)
- [Runnable.com - like jsfiddle, but for server side as well](http://runnable.com/)
- [Getting Started with Node.js on Heroku](https://devcenter.heroku.com/categories/nodejs)
- [Getting Started with Node.js on Open-Shift](https://blog.openshift.com/run-your-nodejs-projects-on-openshift-in-two-simple-steps/)
- [Authentication using Passport](http://passportjs.org/guide/)



## [목차](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs#목차)

- [Express/Node introduction](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/Introduction)

  처음으로 Express에 배우는 이 곳에서는 "Node가 뭐지?", "Express는 뭐지?"의 물음에 답하고, Express 웹 프레임워크의 전반적인 사항에 대해 알아볼 것이다. 주된 내용의 뼈대를 완성하고, Express 어플리케이션을 하나하나씩 배워볼 것이다. (하지만, 이 곳에서는 아직 어디서 테스팅이 이루어질 지 개발 환경등에서는 다루지 않을 것이다.).

- [Setting up a Node (Express) development environment](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/development_environment)

  이제 Express가 어디에 이용되는지 알아볼 것이다. Windows, Linux(Ubuntu), Mac OS X에서 Node/Express의 개발환경을 구축하기 위한 방법도 살펴볼 것이다. 운영체제에 관계없이, 여기서는 Express 개발을 시작하기위해서 어떤 것이 필요한지도 알려준다.

- [Express Tutorial: The Local Library website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/Tutorial_local_library_website)

  실질적인 튜토리얼에 해당하는 이번 수업에서는 어떤 것을 배우고 차후 수업에 필요한 "지역 도서관"에서의 웹사이트의 전반적인 개요도 알아본다.

- [Express Tutorial Part 2: Creating a skeleton website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/skeleton_website)

  여기서는 웹사이트의 뼈대를 구성해 나갈 것이다. 웹사이트의 뼈대란 사이트의 사이트맵, 템플릿 및 데이터베이스등을 말하므로 이를 만들어볼 것이다.

- [Express Tutorial Part 3: Using a Database (with Mongoose)](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/mongoose)

  여기서는 간단하게나마 Node/Express에 필요한 데이터베이스의 개요에 대해 소개할 것이다. 그리고 로컬의 웹사이트의 DB에 접근하기 위해 [Mongoose](http://mongoosejs.com/)를 사용하는 법도 알아본다. DB에서의 스키마와 모델이 어떻게 정의되는지, 필드의 타입과 기본적인 유효성에 대해서도 알아본다. 또한, 짧게나마 모델 데이터를 접근하는 주된 방법도 알아본다.

- [Express Tutorial Part 4: Routes and controllers](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/routes)

  이 수업에서는 *LocalLibray* 웹사이트에 사용하기 위해 "더미" 핸들러 함수를 통한 라우터(URL 핸들링 코드)에 대해 배운다. 여러분의 라우팅 핸들링 코드를 사용할 수 있는 모듈 구조를 가지고 있으며, 다음 장에서 실제로 핸들러 기능을 확장할 수 있게 된다. 또한, Express에서 사용가능한 모듈 형식의 라우팅에 대해 쉽게 이해할 수 있을 것이다.

- [Express Tutorial Part 5: Displaying library data](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/Displaying_data)

  자, 이제 웹사이트에 책이나 데이터들을 표시할 페이지를 추가할 수 있다. 페이지에는 사이트에 관련된 자세한 부분과 리스트 및 모델 타입들이 얼마나 많이 기록되는지에 관한 홈 페이지가 포함되어 있다. 따라서 우리들은 데이터베이스에서 기록을 얻고 템플릿을 사용하는 데 실질적인 경험을 가질 수 있다.

- [Express Tutorial Part 6: Working with forms](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/forms)

  이 수업에서는 Pug를 사용해서 어떻게 Express에서 [HTML Forms](https://developer.mozilla.org/en-US/docs/Learn/Forms) 이 사용되는지 보여주고, 특히 데이터베이스에서 폼을 작성하고 업데이트하고 지우기 위해 사용하는 방법에 대해 배울 것이다.

- [Express Tutorial Part 7: Deploying to production](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/deployment)

  이제 꽤 훌륭한 로컬라이브러리 웹사이트 만들 수 있으며 , 웹 서버에 업로드 함으로서 여러 사람들이 인터넷을 통해 접근할 수 있게 만들 수 있다. 이 수업은 전반적으로 웹 사이트를 배포하기 위해 호스트와 연결하는 등을 배우고, 실제 서비스를 하기위해 준비해야할 것들을 알려 준다.

  

## [튜토리얼 추가하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs#튜토리얼_추가하기)

**자습서의 끝입니다.** (지금은 말이죠). 만약 이 자습서의 내용을 보충하고 싶으시다면 아래와 같은 주제를 해 주시면 좋을 것 같네요:

- 세션 이용하기
- 사용자 인증
- 사용자 권한 부여 및 허가
- Express 웹 어플리케이션 테스트
- Express 웹 어플리케이션 보안

그리고 평가 작업도 있으면 정말 좋을 것 같아요!





# Express/Node 소개

첫번째 Express 수업에서는 Node, Express를 알아보고, Express 웹 프레임워크 제작의 전반에 대해 배우게 됩니다.
우선 주요 특징들에 대한 틀을 정리한 후 Express 어플리케이션을 구성하는 주요 구성요소들을 살펴볼 것입니다. (테스트할 개발환경은 아직이겠지만요)

| 알아야 할 것들 | 기본적인 컴퓨터의 이해. 서버 사이드 웹사이트 프로그래밍([server-side website programming](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps)), 그리고 특별히 웹사이트에서 클라이언트와 서버간의 상호작용([client-server interactions in websites](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps/Client-Server_overview))의 메커니즘에 대한 이해 |
| :------------- | ------------------------------------------------------------ |
| 목표           | Express에 익숙해지고, Node와 어떻게 함께 사용되는지, 기능은 어떠한지, 그리고 Express 어플리케이션의 주요한 구성요소들에 대해 배운다. |



## [Express와 Node란?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#express와_node란)

[Node](https://nodejs.org/) (또는 더 공식적으로는 Node.js) 는 오픈소스, 크로스 플랫폼이며, 개발자가 모든 종류의 서버 사이드 도구들과 어플리케이션을 [JavaScript](https://developer.mozilla.org/en-US/docs/Glossary/JavaScript)로 만들수 있도록 해주는 런타임 환경이다.런타임은 브라우져 영역 밖에서도 사용할수 있도록 의도했다.(예를들면 서버 OS 또는 컴퓨터에서 직접적으로 실행되는). 이와 같이,  이 환경에서 특정 브라우져에서의 자바스트립트 API들을 제외시키고 ,  HTTP 와 파일 시스템 라이브러리들을 포함하여 더 많은 전형적인 OS API들을 추가했다.

웹 서버 관점에서 노드는 많은 장점들을 가진다:

- 뛰어난 성능! Node는 웹 애플리케이션의 처리량과 확장성을 최적화하도록 설계되었으며 많은 일반적인 웹 개발 문제(예: 실시간 웹 애플리케이션)에 대한 좋은 솔루션입니다.
- 코드는 순수한 자바스크립트로 작성된다, 이는 당신이 각각 브라우져와 웹 서버 코드를 작성할때 언어들 사이에 "context shift" 를 다루는 시간을 적게 할수 있음을 의미한다.
- JavaScript는 비교적 새로운 프로그래밍 언어이며 다른 기존 웹 서버 언어(예: Python, PHP 등)와 비교할 때 언어 설계 개선의 이점이 있습니다. CoffeeScript, ClojureScript, Scala, LiveScript 등
- 노드 패키지 매니저(NPM)는 수천만개의 재사용가능한 패키지에 접근할 수 있도록 한다. 이것은 최고의 의존성 해결과 또한 수많은 빌드 툴체인이 자동화되도록 한다.
- Node.js는 이식 가능합니다. Microsoft Windows, macOS, Linux, Solaris, FreeBSD, OpenBSD, WebOS 및 NonStop OS에서 사용할 수 있습니다. 또한 Node 사이트 호스팅을 위한 특정 인프라와 문서를 제공하는 많은 웹 호스팅 제공업체에서 잘 지원하고 있습니다.
- 도움을 주고자 하는 수많은 사람들이 존재하는 아주 활발한 개발 생태계와 커뮤니티를 지니고 있다.

### [Hello Node.js](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#hello_node.js)

아래 코드처럼 Node에 HTTP 모듈을 사용하여 모든 요청에 응답이 가능한 간단한 웹 서버를 쉽게 생성할 수 있습니다.

이렇게하면 웹 서버가 만들어지고 URL `http://127.0.0.1:8000/` 에 있는 모든 종류의 HTTP 요청에 수신하게 됩니다. 요청이 하나 들어왔을 때, "Hello World" 텍스트 응답을 보내도록 하겠습니다.

1. 터미널을 연다. (윈도우에서는, 커맨드라인 유틸리티)
   ※ 윈도우 키 + R => 'CMD'
2. 프로그램을 저장할 폴더를 생성하고(여기서는 `test-node`), 아래 명령을 입력하여 해당 폴더로 이동한다.

```cmd
cd test-node
```

3. 즐겨쓰는 텍스트에디터를 열어 아래 코드를 입력한 후, 파일명 `hello.js` 로 저장한다.

```javascript
//Load HTTP module
var http = require("http");

//Create HTTP server and listen on port 8000 for requests
http.createServer(function (request, response) {

   // Set the response HTTP header with HTTP status and Content type
   response.writeHead(200, {'Content-Type': 'text/plain'});

   // Send the response body "Hello World"
   response.end('Hello World\n');
}).listen(8000);

// Print URL for accessing server
console.log('Server running at http://127.0.0.1:8000/')
```



4. 터미널로 돌아가 아래 명령을 입력한다.

```cmd
node hello.js
```



이제, 웹브라우저를 열어 다음 주소로 이동한다. `http://localhost:8000` 곧바로 좌상단에 "**Hello World**" 문구가 있고, 나머지 영역은 비어있는 웹페이지를 볼 수 있다.

Node 자체가 다른 일반적인 웹 개발 기능을 지원하지 않습니다. 만약 다른 HTTP 패턴 (예 : `GET`, `POST`, `DELETE` 등)에 대한 특정 처리를 추가하려면 서로 다른 URL 경로("routes")를 사용하여 요청을 개별적으로 처리, 정적 파일을 제공, 템플릿을 사용하여 동적으로 응답을 생성할 수 있으며, 코드를 직접 작성할 필요가가 생기게 됩니다. 또는 기본적인 것들을 직접 구현하는 작업을 피하고 웹 프레임 워크를 사용할 수 있습니다! 

[Express](https://expressjs.com/) 는 가장 인기있는 Node 웹 프레임 워크 이며, 다른 많은 인기있는 [Node web frameworks](https://expressjs.com/en/resources/frameworks.html)의 기본 라이브러리 입니다. Express는 다음과 같은 메커니즘을 제공합니다:

- HTTP 통신 요청(Request; GET, POST, DELETE 등)에 대한 핸들러를 만든다.
- 템플릿에 데이터를 넣어 응답(response)을 만들기 위해 view의 렌더링 엔진과 결합(integrate)한다. 
- 접속을 위한 포트나 응답 렌더링을 위한 템플릿 위치같은 공통 웹 어플리케이션 세팅을 한다. 
- 핸들링 파이프라인(reqest handling pipeline) 중 필요한 곳에 추가적인 미들웨어 처리 요청을 추가한다.

*Express* 자체는 꽤나 최소한의 기능만 탑재하지만 개발자들이 거의 모든 웹 개발의 문제를 다루는 호환성있는 미들웨어 패키지를 만들어왔습니다. 쿠키, 세션, 사용자 로그인, URL 파라미터, `POST` 데이터, 보안 헤더와 그외 많은 것들에 대한 라이브러리들이 있습니다. 여러분은 [Express Middleware](http://expressjs.com/en/resources/middleware.html)에서 Express 팀이 유지보수하는 미들웨어 패키지 리스트를 확인할 수 있습니다. (유명한 서드파티 패키지들을 포함).

**Note:** 

```
이러한 유연성은 양날의 검입니다. 거의 모든 문제나 요구 사항을 해결하는 미들웨어 패키지가 있지만 사용할 올바른 패키지를 찾는 것이 때로는 어려울 수 있습니다. 또한 응용 프로그램을 구성하는 '올바른 방법'은 없으며 인터넷에서 찾을 수 있는 많은 예가 최적이 아니거나 웹 응용 프로그램을 개발하기 위해 수행해야 하는 작업의 일부만 보여줍니다.
```



## [유래](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#유래)

노드(Node)는 2009년 리눅스 한정으로 배포 되었다. NPM은 2010년에 배포되었고, 네이티브 윈도우즈(Windows)는 2012년부터 지원하기 시작하였다. 현재 배포중인 최신 LTS 버전은 Node v8.9.3이 있고, 가장 최신 버전은 Node 9 이다. 이것은 유구한 역사를 짧게 적어본 것으로 더 알고 싶다면 [위키페디아](https://ko.wikipedia.org/wiki/Node.js)를 참조하면 된다.

Express는 2010년 11월 처음 배포되었고 현재는 4.16 버전이 되었다. 현재 배포 버전과의 변경사항을 알고 싶다면 [changelog](https://expressjs.com/en/changelog/4x.html) 를 확인하면 된다. 그리고 더 자세한 역사와 릴리즈 노트는 [GitHub](https://github.com/expressjs/express/blob/master/History.md) 를 참조하면 된다.



## [어떻게 Node/Express가 유명해졌을까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#어떻게_nodeexpress가_유명해졌을까)

유명한 웹 프레임워크 쓴다는 것은 아주 중요합니다. 바로 해당 기술에 대한 지속가능성, 문서화, 추가 라이브러리, 그리고 기술 지원 자원에 대한 지표가 되기 때문입니다. 

서버 측 프레임워크의 인기도에 대한 즉시 사용 가능하고 결정적인 측정은 없습니다(Hot Frameworks와 같은 사이트는 각 플랫폼에 대한 GitHub 프로젝트 및 StackOverflow 질문 수를 계산하는 것과 같은 메커니즘을 사용하여 인기도를 평가하려고 시도하지만). 더 나은 질문은 Node와 Express가 인기가 없는 플랫폼의 문제를 피할 수 있을 만큼 '인기 있는' 것인지입니다. 그들은 계속 진화하고 있습니까? 필요한 경우 도움을 받을 수 있습니까? Express를 배우면 급여를 받을 수 있는 기회가 있나요?

Express를 사용하는 유명 회사의 수, 코드베이스에 기여하는 사람들의 수, 무료 및 유료 지원을 모두 제공하는 사람들의 수를 기준으로 볼 때 Express는 인기 있는 프레임워크입니다!



## [Express는 개발이 자유로울까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#express는_개발이_자유로울까)

웹 프레임워크는 종종 스스로를 'opinionated 또는 'Unopinionated '이라고 합니다.

의견 프레임워크는 특정 작업을 처리하는 '올바른 방법'에 대한 의견이 있는 프레임워크입니다. 그들은 어떤 일을 하는 올바른 방법이 일반적으로 잘 이해되고 잘 문서화되어 있기 때문에 특정 영역에서 빠른 개발(특정 유형의 문제 해결)을 지원하는 경우가 많습니다. 그러나 그들은 주요 영역 외부에서 문제를 해결하는 데 덜 유연할 수 있으며 사용할 수 있는 구성 요소와 접근 방식에 대해 더 적은 선택권을 제공하는 경향이 있습니다.

반대로 Unopinionated  프레임워크는 목표를 달성하기 위해 구성 요소를 결합하는 가장 좋은 방법이나 심지어 어떤 구성 요소를 사용해야 하는지에 대한 제한이 훨씬 적습니다. 개발자가 가장 적합한 도구를 사용하여 특정 작업을 완료하는 것이 더 쉬워지지만 해당 구성 요소를 직접 찾아야 하는 비용이 듭니다.

Express는 의견이 없습니다(unopinionated). 원하는 거의 모든 호환 가능한 미들웨어를 요청 처리 체인에 원하는 거의 모든 순서로 삽입할 수 있습니다. 하나의 파일 또는 여러 파일로 앱을 구성할 수 있으며 임의의 디렉토리 구조를 사용할 수 있습니다. 때로는 선택의 폭이 너무 넓다고 느낄 수도 있습니다!



## [Express의 코드는 어떻게 생겼을까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#express의_코드는_어떻게_생겼을까)

기존의 데이터 기반 웹사이트에서 웹 애플리케이션은 웹 브라우저(또는 다른 클라이언트)의 HTTP 요청을 기다립니다. 요청이 수신되면 애플리케이션은 URL 패턴과 POST 데이터 또는 GET 데이터에 포함된 관련 정보를 기반으로 어떤 작업이 필요한지 알아냅니다. 필요한 사항에 따라 데이터베이스에서 정보를 읽거나 쓰거나 요청을 충족시키는 데 필요한 다른 작업을 수행할 수 있습니다. 그러면 애플리케이션은 웹 브라우저에 응답을 반환하고, 검색된 데이터를 HTML 템플릿의 자리 표시자에 삽입하여 브라우저가 표시할 HTML 페이지를 동적으로 생성하는 경우가 많습니다.

Express는 특정 HTTP 동사(GET, POST, SET 등) 및 URL 패턴('경로')에 대해 호출되는 함수를 지정하는 메서드와 사용되는 템플릿('보기') 엔진을 지정하는 메서드를 제공합니다. 여기서 템플릿 파일의 위치와 응답을 렌더링하는 데 사용할 템플릿. Express 미들웨어를 사용하여 쿠키, 세션 및 사용자에 대한 지원을 추가하고 POST/GET 매개변수 등을 얻을 수 있습니다. Node에서 지원하는 모든 데이터베이스 메커니즘을 사용할 수 있습니다(Express는 데이터베이스 관련 동작을 정의하지 않음).

다음 섹션에서는 Express 및 Node 코드로 작업할 때 보게 될 몇 가지 일반적인 사항에 대해 설명합니다.



### [Helloworld Express](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#helloworld_express)

먼저 표준 Express Hello World 예제를 살펴보겠습니다(아래 및 다음 섹션에서 각 부분에 대해 설명합니다).

**Tip:** 

Node 및 Express가 이미 설치되어 있는 경우(또는 다음 기사에 표시된 대로 설치하는 경우) 이 코드를 app.js라는 파일에 저장하고 `node app.js`를 호출하여 명령 프롬프트에서 실행할 수 있습니다.

```javascript
var express = require('express');
var app = express();

app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
```



처음 두 줄은 express 모듈을 require()(가져오기)하고 Express 애플리케이션을 만듭니다. 전통적으로 이름이 app인 이 객체에는 HTTP 요청 라우팅, 미들웨어 구성, HTML 보기 렌더링, 템플릿 엔진 등록, 애플리케이션 작동 방식(예: 환경 모드, 경로 정의가 대소문자 구분 여부)을 제어하는 애플리케이션 설정 수정을 위한 메소드가 있습니다. 등)

코드의 중간 부분(app.get으로 시작하는 세 줄)은 경로 정의를 보여줍니다. app.get() 메서드는 사이트 루트에 상대적인 경로('/')가 있는 HTTP GET 요청이 있을 때마다 호출될 콜백 함수를 지정합니다. 콜백 함수는 요청과 응답 객체를 인수로 취하고 단순히 'Hello World!' 문자열을 반환하기 위해 응답에 대해 send()를 호출합니다.

마지막 블록은 '3000' 포트에서 서버를 시작하고 콘솔에 로그 주석을 인쇄합니다. 서버가 실행 중인 상태에서 브라우저에서 `localhost:3000` 으로 이동하여 반환된 예제 응답을 볼 수 있습니다.



### [모듈의 생성과 불러옴(Importing and creating modules)](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#모듈의_생성과_불러옴importing_and_creating_modules)

모듈은 Node의 require() 함수를 사용하여 다른 코드로 가져올 수 있는 JavaScript 라이브러리/파일입니다. Express 자체는 우리가 Express 애플리케이션에서 사용하는 미들웨어 및 데이터베이스 라이브러리와 마찬가지로 모듈입니다.

아래 코드는 Express 프레임워크를 예로 사용하여 이름별로 모듈을 가져오는 방법을 보여줍니다. 먼저 require() 함수를 호출하여 모듈의 이름을 문자열('express')로 지정하고 반환된 객체를 호출하여 Express 애플리케이션을 만듭니다. 그런 다음 응용 프로그램 개체의 속성과 기능에 액세스할 수 있습니다.

```javascript
var express = require('express');
var app = express();
```

같은 방식으로 가져올 수 있는 고유한 모듈을 만들 수도 있습니다.

**Tip:** 

```
자신만의 모듈을 만들고 싶을 것입니다. 이렇게 하면 코드를 관리 가능한 부분으로 구성할 수 있기 때문입니다. 모놀리식 단일 파일 응용 프로그램은 이해하고 유지 관리하기 어렵습니다. 모듈을 사용하면 명시적으로 내보내는 변수만 모듈을 사용할 때 가져오기 때문에 네임스페이스를 관리하는 데도 도움이 됩니다.
```

모듈 외부에서 개체를 사용할 수 있도록 하려면 내보내기 개체에 할당하기만 하면 됩니다. 예를 들어 아래의 square.js 모듈은 area() 및 perimeter() 메서드를 내보내는 파일입니다.

```javascript
exports.area = function (width) { return width * width; };
exports.perimeter = function (width) { return 4 * width; };
```



Require()를 사용하여 이 모듈을 가져올 수 있으며 다음과 같이 내보낸 메서드를 호출할 수 있습니다.

```
var square = require('./square'); // Here we require() the name of the file without the (optional) .js file extension
console.log('The area of a square with a width of 4 is ' + square.area(4));
```



**Note:** 

모듈에 대한 절대 경로(또는 처음에 했던 것처럼 이름)를 지정할 수도 있습니다.



한 번에 하나의 속성을 빌드하는 대신 전체 개체를 하나의 할당으로 내보내려면 아래와 같이 module.exports에 할당합니다. 내보내기 개체의 루트를 생성자나 다른 함수로 만들 수도 있습니다. :

```javascript
module.exports = {
  area: function(width) {
    return width * width;
  },

  perimeter: function(width) {
    return 4 * width;
  }
};
```



모듈에 대한 더 많은 정보는 [모듈](https://nodejs.org/api/modules.html#modules_modules)(노드 API 문서)을 참조하십시오.



### [비동기식 API의 사용](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#비동기식_api의_사용)

JavaScript 코드는 완료까지 시간이 다소 소요될 수 있는 작업에 대해 동기보다 비동기 API를 자주 사용합니다. 동기 API는 다음 작업이 시작하기 전에 각 작업이 완료되어야만 합니다. 예를 들어, 다음의 로그함수들은 동기식이며 텍스트를 순서대로 콘솔에 나타낼 것입니다.(First, Second)

```javascript
console.log('First');
console.log('Second');
```



반면 비동기 API는 API가 작업을 시작하고, 즉시 반환(작업이 완료되기 전에)합니다. 작업이 완료되면 API는 추가적인 작업 수행을 위한 일부 매커니즘을 사용합니다. 예를 들어 아래의 코드는 "Second, First"를 출력합니다. 그  이유는 `setTimeout()` 메서드가 먼저 호출되고 즉시 반환되더라도, 작업이 몇 초 동안 완료되지 않기 때문입니다.

```javascript
setTimeout(function() {
   console.log('First');
   }, 3000);
console.log('Second');
```



Node는 싱글 스레드 이벤트 기반 환경이기 때문에 non-blocking 비동기 API는 브라우저보다 Node에서 훨씬 더 중요합니다.

'싱글 스레드'는 서버에 모든 요청이 별도의 프로세스로 생성되지 않고 동일한 스레드에서 실행되는 것을 의미합니다. 이 모델은 속도와 서버 리소스 측면에서 아주 효율적이지만, 만약 특정 함수가 완료되는데에 오랜 시간이 걸리는 동기 메서드를 호출하는 함수가 있다면 현재 요청 뿐만 아니라 웹 어플리케이션에서 다른 요청이 처리되는 것도 차단합니다.

비동기 API가 완료되었음을 어플리케이션에 알리는 방법은 여러가지가 있습니다. 가장 일반적인 방법은 비동기 API를 호출시 작업이 완료되면 다시 호출되는 콜백함수를 이용하는 것이며, 위의 예제에서 사용된 방식입니다.

**Tip:** 순서대로 수행해야하는 종속적인 비동기 작업들이 있을 경우, 콜백을 사용하는 것은 꽤 복잡할 수 있습니다. 중첩된 여러 단계의 콜백이 생성되기 때문입니다. 이 문제는 흔히 'callback hell'이라고 일컬어집니다. 이 문제는 good coding practices(http://callbackhell.com/ 참고), [async](https://www.npmjs.com/package/async)와 같은 모듈의 사용, [Promises](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise)와 같은 ES6 기능을 사용함으로써 개선될 수 있습니다.

**Note:** Node와 Express의 일반적인 규칙은 error-first callbacks을 사용하는 것입니다. 이 규칙에서 콜백 함수의 첫번 째 값은 에러값이고, 다음 인자에는 성공 데이터가 포함됩니다. 이 방법에 대한 좋은 설명은 이 블로그에서 확인할 수 있습니다.:: [The Node.js Way - Understanding Error-First Callbacks](http://fredkschott.com/post/2014/03/understanding-error-first-callbacks-in-node-js/ ) (fredkschott.com).



### [라우트 핸들러의 사용](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#라우트_핸들러의_사용)

Hello World Express 예제에서 위를 참조하십시오. 사이트 루트('/')에 대한 HTTP GET 요청에 대한 (콜백) 경로 처리기 기능을 정의했습니다.

```javascript
app.get('/', function (req, res) {
  res.send('Hello World!');
});
```



콜백 함수는 요청과 응답 객체를 인수로 취합니다. 이 경우 메서드는 응답에서 단순히 send()를 호출하여 'Hello World!' 문자열을 반환합니다. 요청/응답 주기를 종료하는 다른 응답 방법이 많이 있습니다. 예를 들어 res.json()을 호출하여 JSON 응답을 보내거나 res.sendFile()을 호출하여 파일을 보낼 수 있습니다.

**JavaScript tip:** 콜백 함수에서 원하는 인수 이름을 사용할 수 있습니다. 콜백이 호출될 때 첫 번째 인수는 항상 요청이 되고 두 번째 인수는 항상 응답이 됩니다. 콜백 본문에서 작업 중인 개체를 식별할 수 있도록 이름을 지정하는 것이 좋습니다.

Express 애플리케이션 객체는 또한 post(), put(), delete(), options(), trace(), copy()와 같은 방식으로 정확히 같은 방식으로 사용되는 다른 모든 HTTP 동사에 대한 경로 처리기를 정의하는 메서드도 제공합니다. ), lock(), mkcol(), move(), purge(), propfind(), proppatch(), unlock(), report(), mkactivity(), checkout(), merge( ), m-search(), notify(), subscribe(), unsubscribe(), patch(), search() 및 connect().



모든 HTTP 메서드에 대한 응답으로 호출되는 특별한 라우팅 메서드인 app.all()이 있습니다. 이것은 모든 요청 메소드에 대한 특정 경로에서 미들웨어 기능을 로드하는 데 사용됩니다. 다음 예제(Express 설명서에서)는 사용된 HTTP 동사에 관계없이 /secret에 대한 요청에 대해 실행되는 핸들러를 보여줍니다(http 모듈에서 지원하는 경우).

```javascript
app.all('/secret', function (req, res, next) {
  console.log('Accessing the secret section ...')
  next() // pass control to the next handler
})
```

경로를 사용하면 URL의 특정 문자 패턴을 일치시키고 URL에서 일부 값을 추출하여 경로 처리기에 매개변수로 전달할 수 있습니다(매개변수로 전달된 요청 객체의 속성으로).

종종 사이트의 특정 부분에 대한 경로 처리기를 함께 그룹화하고 공통 경로 접두사를 사용하여 액세스하는 것이 유용합니다(예: Wiki가 있는 사이트는 하나의 파일에 모든 Wiki 관련 경로가 있을 수 있으며 경로 접두사로 액세스할 수 있습니다. /wiki/). Express에서는 express.Router 개체를 사용하여 이를 수행합니다. 예를 들어 wiki.js라는 모듈에 wiki 경로를 만든 다음 아래와 같이 Router 개체를 내보낼 수 있습니다.

```javascript
// wiki.js - Wiki route module

var express = require('express')
var router = express.Router()

// Home page route
router.get('/', function (req, res) {
  res.send('Wiki home page')
})

// About page route
router.get('/about', function (req, res) {
  res.send('About this wiki')
})

module.exports = router
```



**Note:** 라우터 개체에 경로를 추가하는 것은 앱 개체에 경로를 추가하는 것과 같습니다(이전에 표시된 대로).

기본 앱 파일에서 라우터를 사용하려면 route 모듈(wiki.js)을 require()한 다음 Express 애플리케이션에서 use()를 호출하여 라우터를 미들웨어 처리 경로에 추가합니다. 그러면 /wiki/ 및 /wiki/about/에서 두 경로에 액세스할 수 있습니다.

```javascript
var wiki = require('./wiki.js')
// ...
app.use('/wiki', wiki)
```

라우트 작업, 특히 라우터 사용에 대한 자세한 내용은 나중에 링크된 [라우트 및 컨트롤러 섹션](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/routes)에서 보여드리겠습니다.



### [미들웨어의 사용](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#미들웨어의_사용)

미들웨어는 정적 파일 제공에서 오류 처리, HTTP 응답 압축에 이르기까지 Express 앱에서 광범위하게 사용됩니다. 라우트 함수는 HTTP 클라이언트에 일부 응답을 반환하여 HTTP 요청-응답주기를 종료하는 반면, 미들웨어 함수는 *일반적으로* 요청 또는 응답에 대해 일부 작업을 수행 한 다음 "스택"(이는 미들웨어 혹은 라우트 핸들러일 수 있습니다.)에서 다음 함수를 호출합니다. 미들웨어가 호출되는 순서는 앱 개발자에게 달려있습니다.

**Note:** 미들웨어는 모든 작업을 수행하고, 코드를 실행하고, 요청 및 응답 객체를 변경할 수 *있으며, 요청-응답주기를 종료* 할 *수도* 있습니다. 만약 주기가 종료되지 않으면, 다음 미들웨어 함수의 제어를 위해 `next()`를 호출해야합니다.( 혹은 요청이 중단된 상태로 유지될 것입니다.)

대부분의 앱은 쿠키, 세션, 사용자 인증,` POST`요청 및 JSON 데이터 접근 , logging 등과 같은 일반적인 웹 개발 작업을 단순화하기 위해*서드파티* 미들웨어를 사용합니다 [. Express 팀에서 관리하는 미들웨어 패키지 목록을](http://expressjs.com/en/resources/middleware.html) 이 곳에서 찾을 수 있습니다 ( 다른 인기있는 서드파티 패키지도 포함). 다른 Express 패키지는 NPM 패키지 관리자에서 사용할 수 있습니다.

서드파티 미들웨어를 사용하려면 먼저 NPM을 사용하여 앱에 설치해야합니다. 예를 들어 [morgan](http://expressjs.com/en/resources/middleware/morgan.html) HTTP 요청 logger 미들웨어 를 설치하려면 다음과 같이 진행합니다.

```cmd
$ npm install morgan
```



그런 다음 *Express application object에* use()를 호출해서 스택에 이 미들웨어를 추가합니다.

```javascript
var express = require('express');
var logger = require('morgan');
var app = express();
app.use(logger('dev'));
...
```



**Note:** 미들웨어 및 라우팅 함수는 선언된 순서대로 호출됩니다. 일부 미들웨어의 경우 순서가 중요합니다 (예를 들어 세션 미들웨어가 쿠키 미들웨어에 의존하는 경우, 쿠키 핸들러를 먼저 추가해야합니다). 거의 항상 라우트를 설정하기 전에 미들웨어가 호출되거나, 미들웨어로 인해 추가된 기능에 라우트 핸들러가 접근할 수 없습니다.

당신은 자신만의 미들웨어 함수를 작성할 수 있으며, 그렇게 해야 할 가능성이 높습니다 (에러 처리 코드를 생성하는 경우에만 해당). 미들웨어 함수와 라우트 핸들러 콜백 의 **유일한** 차이점은 미들웨어 함수에 세 번째 인자로 미들웨어 함수가 요청 주기를 완료하지 않을 때 호출되는 `next`가 있다는 것입니다. (미들웨어 함수가 호출 될 때 여기에는 반드시 호출되는 *next* 함수가 포함됩니다.).

당신이 모든 응답 혹은  특정 HTTP 동사(`GET`, `POST` 등)에 미들웨어를 적용할지 여부에 따라`app.use()`또는 `app.add()`와 함께 프로세싱 체인에 미들웨어 기능을 추가 할 수 있습니다. 두 경우 모두 라우트를 동일하게 지정하지만 **app.use ()** 호출시 라우트는 선택 사항 입니다.

아래의 예제는 두 가지 방법을 사용하고, 경로를 사용하거나 사용하지 않고 미들웨어 기능을 추가하는 방법을 보여줍니다.

```javascript
var express = require('express')
var app = express()

// An example middleware function
var a_middleware_function = function(req, res, next) {
  //... perform some operations
  next(); //Call next() so Express will call the next middleware function in the chain.
}

// Function added with use() for all routes and verbs
app.use(a_middleware_function)

//Function added with use() for a specific route
app.use('/someroute', a_middleware_function)

// A middleware function added for a specific HTTP verb and route
app.get('/', a_middleware_function )

app.listen(3000)
```



**JavaScript Tip:** 위에서 우리는 미들웨어 함수를 별도로 선언 한 다음 그것을 콜백으로 설정합니다. 이전 라우트 핸들러 함수에서는 우리는 콜백 함수가 사용될 때 선언했습니다. JavaScript에서는 두 방법 모두 유효합니다.

Express 공식 문서에는 Express 미들웨어 [사용](https://expressjs.com/en/guide/using-middleware.html) 및 [작성](http://expressjs.com/en/guide/writing-middleware.html) 에 대한 훨씬 더 우수한 자료들이 있습니다.



### [저장된 파일을 서버화하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#저장된_파일을_서버화하기)

Express.static 미들웨어를 사용하여 이미지, CSS 및 JavaScript를 포함한 정적 파일을 제공할 수 있습니다(static()은 실제로 Express의 일부인 유일한 미들웨어 기능입니다). 예를 들어, 아래 줄을 사용하여 node를 호출하는 동일한 수준에서 'public'이라는 디렉토리에서 이미지, CSS 파일 및 JavaScript 파일을 제공합니다.

```javascript
app.use(express.static('public'))
```

공용 디렉토리의 모든 파일은 기본 URL에 파일 이름(기본 '공용' 디렉토리에 상대적)을 추가하여 제공됩니다. 예를 들면 다음과 같습니다.

```javascript
http://localhost:3000/images/dog.jpg
http://localhost:3000/css/style.css
http://localhost:3000/js/app.js
http://localhost:3000/about.html
```

여러 디렉토리를 제공하기 위해 static()을 여러 번 호출할 수 있습니다. 하나의 미들웨어 함수에서 파일을 찾을 수 없으면 다음 미들웨어로 전달됩니다(미들웨어가 호출되는 순서는 선언 순서를 기반으로 함).

```javascript
app.use(express.static('public'))
app.use(express.static('media'))
```



파일을 기본 URL에 추가하는 대신 정적 URL에 대한 가상 접두사를 생성할 수도 있습니다. 예를 들어, 여기에서 파일이 '/media' 접두사로 로드되도록 마운트 경로를 지정합니다.

```
app.use('/media', express.static('public'))
```



이제 /media 경로 접두사에서 공용 디렉터리에 있는 파일을 로드할 수 있습니다.

```
http://localhost:3000/media/images/dog.jpg
http://localhost:3000/media/video/cat.mp4
http://localhost:3000/media/cry.mp3
```

자세한 내용은 Express에서 [정적 파일 제공을 참조](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Serving%20static%20files%20in%20Express)하세요.



### [핸들링 에러](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#핸들링_에러)

오류는 일반적인 세 가지 인수(err, req, res, next) 대신 4개의 인수가 있는 하나 이상의 특수 미들웨어 함수에 의해 처리됩니다. 예:

```javascript
app.use(function (err, req, res, next) {
  console.error(err.stack)
  res.status(500).send('Something broke!')
})
```

필요한 모든 콘텐츠를 반환할 수 있지만 다른 모든 app.use() 후에 호출되어야 하며 요청 처리 프로세스의 마지막 미들웨어가 되도록 호출을 라우팅해야 합니다!

Express에는 앱에서 발생할 수 있는 나머지 오류를 처리하는 오류 처리기가 내장되어 있습니다. 이 기본 오류 처리 미들웨어 기능은 미들웨어 기능 스택의 끝에 추가됩니다. Next()에 오류를 전달하고 오류 처리기에서 처리하지 않으면 내장 오류 처리기에 의해 처리됩니다. 오류는 스택 추적과 함께 클라이언트에 기록됩니다.

**Note:**  스택 추적은 프로덕션 환경에 포함되지 않습니다. 프로덕션 모드에서 실행하려면 NODE_ENV 환경 변수를 '프로덕션'으로 설정해야 합니다.

**Note:** HTTP404 및 기타 '오류' 상태 코드는 오류로 처리되지 않습니다. 이를 처리하고 싶다면 미들웨어 기능을 추가하면 됩니다. 자세한 내용은 FAQ를 참조하세요.

자세한 내용은 오류 처리( [Error handling](http://expressjs.com/en/guide/error-handling.html)  문서)를 참조하세요.



### [데이터베이스의 사용](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#데이터베이스의_사용)

Express 앱은 Node에서 지원하는 모든 데이터베이스 메커니즘을 사용할 수 있습니다(Express 자체는 데이터베이스 관리에 대한 특정 추가 동작/요구 사항을 정의하지 않음). PostgreSQL, MySQL, Redis, SQLite, MongoDB 등 많은 옵션이 있습니다.

이를 사용하려면 먼저 NPM을 사용하여 데이터베이스 드라이버를 설치해야 합니다. 예를 들어 널리 사용되는 NoSQL MongoDB용 드라이버를 설치하려면 다음 명령을 사용합니다.

```cmd
$ npm install mongodb
```



데이터베이스 자체는 로컬로 또는 클라우드 서버에 설치할 수 있습니다. 익스프레스 코드에서 드라이버가 필요하고 데이터베이스에 연결한 다음 CRUD(생성, 읽기, 업데이트 및 삭제) 작업을 수행합니다. 아래의 예(Express 문서)는 MongoDB를 사용하여 '포유류' 레코드를 찾는 방법을 보여줍니다.

```javascript
var MongoClient = require('mongodb').MongoClient

MongoClient.connect('mongodb://localhost:27017/animals', function (err, db) {
  if (err) throw err

  db.collection('mammals').find().toArray(function (err, result) {
    if (err) throw err

    console.log(result)
  })
})
```



또 다른 인기 있는 접근 방식은 'ORM'(Object Relational Mapper)을 통해 데이터베이스에 간접적으로 액세스하는 것입니다. 이 접근 방식에서는 데이터를 '객체' 또는 '모델'로 정의하고 ORM은 이를 기본 데이터베이스 형식에 매핑합니다. 이 접근 방식은 개발자가 계속해서 데이터베이스 의미가 아닌 JavaScript 개체의 관점에서 생각할 수 있고 들어오는 데이터의 유효성 검사 및 검사를 수행할 수 있는 분명한 장소가 있다는 이점이 있습니다. 데이터베이스에 대한 자세한 내용은 이후 기사에서 다루겠습니다.

자세한 내용은 [데이터베이스 통합]((https://expressjs.com/en/guide/database-integration.html))(Express 문서)을 참조하세요.



### [데이터 랜더링(시각화)](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#데이터_랜더링시각화)

템플릿 엔진(Express에서 '보기 엔진'이라고 함)을 사용하면 페이지가 생성될 때 채워질 데이터에 대한 자리 표시자를 사용하여 템플릿의 출력 문서 구조를 지정할 수 있습니다. 템플릿은 HTML을 만드는 데 자주 사용되지만 다른 유형의 문서도 만들 수 있습니다. Express는 여러 템플릿 엔진을 지원하며 여기에 더 인기 있는 엔진에 대한 유용한 비교가 있습니다. JavaScript 템플릿 엔진 비교: Jade, Mustache, Dust 등.

애플리케이션 설정 코드에서 아래와 같이 '보기' 및 '보기 엔진' 설정을 사용하여 Express가 템플릿을 찾아야 하는 위치와 사용할 템플릿 엔진을 설정합니다(템플릿 라이브러리가 포함된 패키지도 설치해야 합니다. !)

```javascript
var express = require('express');
var app = express();

// Set directory to contain the templates ('views')
app.set('views', path.join(__dirname, 'views'));

// Set view engine to use, in this case 'some_template_engine_name'
app.set('view engine', 'some_template_engine_name');
```



템플릿의 모양은 사용하는 엔진에 따라 다릅니다. 'title' 및 'message'라는 데이터 변수에 대한 자리 표시자를 포함하는 'index.template_extension'이라는 템플릿 파일이 있다고 가정하면 경로 처리기 함수에서 Response.render()를 호출하여 HTML 응답을 생성하고 전송합니다.

```
app.get('/', function (req, res) {
  res.render('index', { title: 'About dogs', message: 'Dogs rock!' })
})
```

자세한 내용은 [Express와 함께 템플릿 엔진 사용](http://expressjs.com/en/guide/using-template-engines.html) (Express 문서)을 참조하세요.



### [파일구조](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#파일구조)

Express는 사용하는 구성 요소나 구조 측면에서 어떠한 가정도 하지 않습니다. 경로, 보기, 정적 파일 및 기타 응용 프로그램별 논리는 디렉터리 구조에 관계없이 원하는 수의 파일에 있을 수 있습니다. 전체 Express 응용 프로그램을 하나의 파일에 포함하는 것이 완벽하게 가능하지만 일반적으로 기능(예: 계정 관리, 블로그, 토론 게시판) 및 아키텍처 문제 영역(예: 모델, 보기 또는 컨트롤러)에 따라 응용 프로그램을 파일로 분할하는 것이 합리적입니다. MVC 아키텍처를 사용하고 있습니다).

나중 주제에서는 웹 애플리케이션 생성을 위해 쉽게 확장할 수 있는 모듈식 앱 골격을 생성하는 Express Application Generator를 사용할 것입니다.



## [요약](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#요약)

축하합니다. Express/Node 여정의 첫 번째 단계를 완료했습니다! 이제 Express 및 Node의 주요 이점과 Express 앱의 주요 부분(라우트, 미들웨어, 오류 처리 및 템플릿 코드)이 어떻게 생겼는지 이해해야 합니다. 또한 Express는 의견이 없는 프레임워크이므로 이러한 부분과 사용하는 라이브러리를 함께 가져오는 방법은 전적으로 귀하에게 달려 있다는 점을 이해해야 합니다!

물론 Express는 의도적으로 매우 가벼운 웹 애플리케이션 프레임워크이므로 많은 이점과 잠재력이 타사 라이브러리 및 기능에서 나옵니다. 다음 기사에서 더 자세히 살펴보겠습니다. 다음 기사에서는 작동 중인 Express 코드를 볼 수 있도록 Node 개발 환경을 설정하는 방법을 살펴보겠습니다.

## [더보기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Introduction#더보기)

- [Modules](https://nodejs.org/api/modules.html#modules_modules) (Node API docs)
- [Express](https://expressjs.com/) (home page)
- [Basic routing](http://expressjs.com/en/starter/basic-routing.html) (Express docs)
- [Routing guide](http://expressjs.com/en/guide/routing.html) (Express docs)
- [Using template engines with Express](http://expressjs.com/en/guide/using-template-engines.html) (Express docs)
- [Using middleware](https://expressjs.com/en/guide/using-middleware.html) (Express docs)
- [Writing middleware for use in Express apps](http://expressjs.com/en/guide/writing-middleware.html) (Express docs)
- [Database integration](https://expressjs.com/en/guide/database-integration.html) (Express docs)
- [Serving static files in Express](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/Serving static files in Express) (Express docs)
- [Error handling](http://expressjs.com/en/guide/error-handling.html) (Express docs)





# Node 개발 환경을 설치하기

이제 Express에 관한 내용을 알았으니, Windows, Linux (Ubuntu), 그리고 macOS 에서의 Node/Express 개발 환경을 설정하고 테스트하는 법을 보여드리겠습니다. 사용중인 운영 체제가 무엇이든 간에, 이 글은 당신에게 Express 앱 개발을 시작할 수 있도록 필요한 내용을 제공합니다.

| 전제 조건: | 터미널 혹은 명령어 창을 여는 방법. 당신의 개발 컴퓨터의 운영 체제에 소프트웨어 패키지를 설치하는 방법을 알고 있어야 합니다. |
| :--------- | ------------------------------------------------------------ |
| 목표:      | 당신의 컴퓨터에 Express (X.XX) 을 위한 개발 환경을 설치하는 것. |

## [Express 개발 환경 개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#express_개발_환경_개요)

Node와 Express를 통해 웹앱 개발을 한결 수월하게 할 수 있습니다. 이 섹션에서는 어떤 도구들이 필요한지, Ubuntu, macOS, 그리고 Windows에서 어떻게 Node와 Express를 설치하는지, 마지막으로, 설치 후 어떻게 테스트해볼 수 있는지 살펴볼 것입니다.

### [Express 개발 환경이란 무엇입니까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#express_개발_환경이란_무엇입니까)

노드와 NPM 패키지 관리자는 준비된 바이너리 패키지, 설치 프로그램, 운영체제 패키지 관리자 또는 소스(다음 섹션 참조)에서 함께 설치됩니다. 그런 다음 Express는 개별 Express 웹 애플리케이션의 종속성으로 NPM에 의해 설치됩니다(템플릿 엔진, 데이터베이스 드라이버, 인증 미들웨어, 정적 파일을 제공하는 미들웨어 등과 같은 다른 라이브러리와 함께).

NPM은 MVC 패턴을 따르는 스켈레톤 Express 웹 앱을 만들기 위한 편리한 도구인 Express Application Generator를 (전 세계적으로) 설치하는 데 사용할 수도 있습니다. 이 도구를 사용하여 Express를 사용하는 앱을 만들거나 아키텍처 레이아웃이나 종속성이 동일한 Express 앱을 구성할 필요가 없기 때문에 애플리케이션 생성기는 선택사항입니다. 시작하기가 훨씬 쉽고 모듈식 애플리케이션 구조를 촉진하기 때문에 우리는 그것을 사용할 것입니다.

**Note:** 일부 다른 웹 프레임워크와 달리 개발 환경에는 별도의 개발 웹 서버가 포함되어 있지 않습니다. Node/Express에서 웹 애플리케이션은 자체 웹 서버를 생성하고 실행합니다!

텍스트 편집기 또는 코드 편집용 IDE, 다양한 버전의 코드를 안전하게 관리하기 위한 Git과 같은 소스 제어 관리 도구를 포함하여 일반적인 개발 환경의 일부인 다른 주변 도구가 있습니다. 이러한 종류의 도구(특히 텍스트 편집기)가 이미 설치되어 있다고 가정합니다.

### [지원되는 운영 체제는 무엇입니까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#what_operating_systems_are_supported)

*Node* can be run on Windows, macOS, many "flavours" of Linux, Docker, etc. (there is a full list on the nodejs [Downloads](https://nodejs.org/en/download/) page). Almost any personal computer should have the necessary performance to run Node during development. *Express* is run in a *Node* environment, and hence can run on any platform that runs *Node*.

이 기사에서는 Windows, macOS, 그리고 Ubuntu Linux에서의 설치방법을 안내해드리고 있습니다.

### [어떤 버전의 Node/Express를 사용해야 합니까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#what_version_of_nodeexpress_should_you_use)

There are many [releases of Node](https://nodejs.org/en/blog/release/) — newer releases contain bug fixes, support for more recent versions of ECMAScript (JavaScript) standards, and improvements to the Node APIs. 

Generally you should use the most recent *LTS (long-term supported)* release as this will be more stable than the "current" release while still having relatively recent features (and is still being actively maintained). You should use the *Current* release if you need a feature that is not present in the LTS version.

For *Express* you should always use the latest version.

### [데이터베이스 및 기타 종속성은 어떻습니까?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#what_about_databases_and_other_dependencies)

데이터베이스 드라이버, 템플릿 엔진, 인증 엔진 등과 같은 기타 종속성은 애플리케이션의 일부이며 NPM 패키지 관리자를 사용하여 애플리케이션 환경으로 가져옵니다. 나중에 앱별 도움말에서 이에 대해 논의하겠습니다.

## [Node 설치하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#node_설치하기)

*Express* 를 사용하기 위해서 우선 운영체제에 *Nodejs*와 [Node Package Manager (NPM)](https://docs.npmjs.com/)를 설치해야 합니다. The following sections explain the easiest way to install the Long Term Supported (LTS) version of Nodejs on Ubuntu Linux 16.04, macOS, and Windows 10.

**Tip:** The sections below show the easiest way to install *Node* and *NPM* on our target OS platforms. If you're using another OS or just want to see some of the other approaches for the current platforms then see [Installing Node.js via package manager](https://nodejs.org/en/download/package-manager/) (nodejs.org).

### [Windows and macOS](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#windows_and_macos)

*Node*와 *NPM*을 설치하는 것은 간단합니다:

1. Installer를 다운받읍시다:
   1. https://nodejs.org/en/
   2. "안정적이고 신뢰도가 높은" LTS버튼을 클릭해 다운로드를 시작합니다. .
2. 다운로드된 파일을 더블클릭해 Node를 설치합니다.

The easiest way to install the most recent LTS version of Node 10.x is to use the [package manager](https://nodejs.org/en/download/package-manager/#debian-and-ubuntu-based-linux-distributions) to get it from the Ubuntu *binary distributions* repository. This can be done very simply by running the following two commands on your terminal:

```
curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
sudo apt-get install -y nodejs
```

Copy to Clipboard

**Warning:** Don't install directly from the normal Ubuntu repositories because they contain very old versions of node.



## [Using NPM](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#using_npm)

노드 자체 다음으로 NPM은 노드 애플리케이션 작업을 위한 가장 중요한 도구입니다. NPM은 애플리케이션이 개발, 테스트, 프로덕션에 필요한 모든 패키지(자바스크립트 라이브러리)를 가져오는 데 사용되며 개발 프로세스에 사용되는 테스트 및 도구를 실행하는 데 사용할 수도 있습니다.

**Note:** Node의 관점에서 Express는 NPM을 사용하여 설치한 다음 고유한 코드에 필요한 또 다른 패키지입니다.

NPM을 수동으로 사용하여 필요한 각 패키지를 별도로 가져올 수 있습니다. 일반적으로 package.json이라는 일반 텍스트 정의 파일을 사용하여 종속성을 관리합니다. 이 파일은 패키지의 이름, 버전, 설명, 실행할 초기 파일, 프로덕션 종속성, 개발 종속성, 작업할 수 있는 노드 버전 등을 포함하여 특정 자바스크립트 '패키지'에 대한 모든 종속성을 나열합니다. package.json 파일은 NPM이 애플리케이션을 가져와 실행하는 데 필요한 모든 것을 포함합니다(재사용 가능한 라이브러리를 작성하는 경우 이 정의를 사용하여 패키지를 npm 저장소에 업로드하고 다른 사용자가 사용할 수 있도록 할 수 있음).

### [dependencies 추가](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#dependencies_추가)

다음 단계에서는 NPM을 사용하여 패키지를 다운로드하고 프로젝트 종속 항목에 저장한 다음 노드 애플리케이션에서 요구하는 방법을 보여줍니다.

**Note:** 여기에서는 Express 패키지를 가져오고 설치하는 지침을 보여줍니다. 나중에 이 패키지와 다른 패키지가 Express Application Generator를 사용하여 이미 지정되어 있는 방법을 보여드리겠습니다. 이 섹션은 NPM이 작동하는 방식과 애플리케이션 생성기에 의해 생성되는 내용을 이해하는 데 유용하기 때문에 제공됩니다.

1. 먼저 새 응용 프로그램의 디렉터리를 만들고 탐색합니다.

   ```cmd
   mkdir myapp
   cd myapp
   ```

   

2. Npm init 명령을 사용하여 애플리케이션에 대한 package.json 파일을 만듭니다. 이 명령어는 애플리케이션의 이름과 버전, 초기 진입점 파일의 이름(기본적으로 index.js임)을 비롯한 여러 항목을 묻는 메시지를 표시합니다. 지금은 기본값을 그대로 사용하세요.

   ```
   npm init
   ```

   Package.json 파일(cat package.json)을 표시하면 라이선스로 끝나는 수락한 기본값이 표시됩니다.

   ```js
   {
     "name": "myapp",
     "version": "1.0.0",
     "description": "",
     "main": "index.js",
     "scripts": {
       "test": "echo \"Error: no test specified\" && exit 1"
     },
     "author": "",
     "license": "ISC"
   }
   ```

   

3. 이제 myapp 디렉토리에 Express를 설치하고 package.json 파일의 종속성 목록에 저장합니다.

4. ```cmd
   npm install express
   ```

   이제 package.json의 종속 항목 섹션이 package.json 파일의 끝에 표시되고 Express가 포함됩니다.

   ```
   {
     "name": "myapp",
     "version": "1.0.0",
     "description": "",
     "main": "index.js",
     "scripts": {
       "test": "echo \"Error: no test specified\" && exit 1"
     },
     "author": "",
     "license": "ISC",
     "dependencies": {
       "express": "^4.16.4"
     }
   }
   ```

   

5. Express 라이브러리를 사용하려면 index.js 파일에서 require() 함수를 호출하여 애플리케이션에 포함합니다. 'myapp' 애플리케이션 디렉토리의 루트에서 지금 이 파일을 만들고 다음 내용을 제공하십시오.

   ```js
   const express = require('express')
   const app = express();
   
   app.get('/', (req, res) => {
     res.send('Hello World!')
   });
   
   app.listen(8000, () => {
     console.log('Example app listening on port 8000!')
   });
   ```

   이 코드는 최소한의 'HelloWorld' Express 웹 애플리케이션을 보여줍니다. 이렇게 하면 require()를 사용하여 'express' 모듈을 가져와 포트 8000에서 HTTP 요청을 수신 대기하는 서버(앱)를 만들고 서버를 테스트하는 데 사용할 수 있는 브라우저 URL을 설명하는 메시지를 콘솔에 인쇄합니다. App.get() 함수는 지정된 URL 경로('/')가 있는 HTTP GET 요청에만 응답합니다. 이 경우 Hello World! 메시지.

6. 명령 프롬프트에서 스크립트로 노드를 호출하여 서버를 시작할 수 있습니다.

   ```
   >node index.js
   Example app listening on port 8000
   ```

   

7. URL(http://127.0.0.1:8000/)로 이동합니다. 모든 것이 작동한다면 브라우저는 단순히 'Hello World!' 문자열을 표시해야 합니다.

   

### [개발 종속성](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#development_dependencies)

종속성이 개발 중에만 사용되는 경우 대신 '개발 종속성'으로 저장해야 합니다(패키지 사용자가 프로덕션 환경에 설치할 필요가 없도록). 예를 들어 널리 사용되는 JavaScript Linting 도구 [eslint](http://eslint.org/)를 사용하려면 다음과 같이 NPM을 호출합니다.

```cmd
npm install eslint --save-dev
```

그러면 다음 항목이 애플리케이션의 package.json에 추가됩니다.

```json
  "devDependencies": {
    "eslint": "^4.12.1"
  }
```



**Note:** ['Linters'](https://en.wikipedia.org/wiki/Lint_(software))는 일부 코딩 모범 사례에 대한 준수/비준수를 인식하고 보고하기 위해 소프트웨어에 대한 정적 분석을 수행하는 도구입니다.

### [작업 진행](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#running_tasks)

종속성을 정의하고 가져오는 것 외에도 package.json 파일에 명명된 스크립트를 정의하고 NPM을 호출하여 run-script 명령어로 실행할 수 있습니다. 이 접근 방식은 일반적으로 실행 중인 테스트 및 개발의 일부를 자동화하거나 도구 체인을 빌드하는 데 사용됩니다(예: 자바스크립트 축소, 이미지 축소, 코드 LINT/분석 등을 위한 도구 실행).

**Note:** Gulp 및 Grunt와 같은 작업 실행기는 테스트 및 기타 외부 도구를 실행하는 데 사용할 수도 있습니다.

예를 들어 이전 섹션에서 지정한 eslint 개발 종속성을 실행하는 스크립트를 정의하기 위해 다음 스크립트 블록을 package.json 파일에 추가할 수 있습니다(애플리케이션 소스가 폴더 /src/js에 있다고 가정).

```js
"scripts": {
  ...
  "lint": "eslint src/js"
  ...
}
```



조금 더 설명하자면, eslint src/js는 터미널/명령줄에 입력하여 앱 디렉터리 내의 src/js 디렉터리에 포함된 JavaScript 파일에 대해 eslint를 실행할 수 있는 명령입니다. 위의 내용을 앱의 package.json 파일에 포함하면 이 명령에 대한 바로 가기가 제공됩니다.

그런 다음 다음을 호출하여 NPM을 사용하여 eslint를 실행할 수 있습니다.

```cmd
npm run-script lint
# OR (using the alias)
npm run lint
```



이 예제는 원래 명령보다 짧아 보이지 않을 수 있지만 여러 명령 체인을 포함하여 npm 스크립트 내부에 훨씬 더 큰 명령을 포함할 수 있습니다. 모든 테스트를 한 번에 실행하는 단일 npm 스크립트를 식별할 수 있습니다.



## [Express Application Generator 설치](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#installing_the_express_application_generator)

Express Application Generator 도구는 Express 애플리케이션 '골격'을 생성합니다. 다음과 같이 NPM을 사용하여 생성기를 설치합니다(-g 플래그는 도구를 전역적으로 설치하므로 어디서나 호출할 수 있음).

``` bash
npm install express-generator -g
```

기본 설정으로 'helloworld'라는 Express 앱을 생성하려면 생성하려는 위치로 이동하여 다음과 같이 앱을 실행합니다.

```bash
express helloworld
```



**Note:** 사용할 템플릿 라이브러리와 기타 여러 설정을 지정할 수도 있습니다. 모든 옵션을 보려면 help 명령을 사용하십시오.

```bash
express --help
```

NPM은 현재 위치의 하위 폴더에 새 Express 앱을 생성하여 콘솔에 빌드 진행 상황을 표시합니다. 완료되면 도구는 노드 종속성을 설치하고 앱을 시작하기 위해 입력해야 하는 명령을 표시합니다.

새 앱의 루트 디렉터리에 package.json 파일이 있습니다. 이것을 열면 Express 및 템플릿 라이브러리 Jade를 포함하여 설치된 종속 항목을 확인할 수 있습니다.

```json
{
  "name": "helloworld",
  "version": "0.0.0",
  "private": true,
  "scripts": {
    "start": "node ./bin/www"
  },
  "dependencies": {
    "cookie-parser": "~1.4.3",
    "debug": "~2.6.9",
    "express": "~4.16.0",
    "http-errors": "~1.6.2",
    "jade": "~1.11.0",
    "morgan": "~1.9.0"
  }
}
```

다음과 같이 NPM을 사용하여 helloworld 앱에 대한 모든 종속성을 설치합니다.

```bash
cd helloworld
npm install
```

그런 다음 아래와 같이 앱을 실행합니다(Windows 및 Linux/macOS의 경우 명령이 약간 다름).

```
# Run the helloworld on Windows with Command Prompt
SET DEBUG=helloworld:* & npm start

# Run the helloworld on Windows with PowerShell
SET DEBUG=helloworld:* | npm start

# Run helloworld on Linux/macOS
DEBUG=helloworld:* npm start
```



DEBUG 명령은 유용한 로깅을 생성하여 아래와 같은 출력을 생성합니다.

```
>SET DEBUG=helloworld:* & npm start

> helloworld@0.0.0 start D:\Github\expresstests\helloworld
> node ./bin/www

  helloworld:server Listening on port 3000 +0ms
```



브라우저를 열고 http://127.0.0.1:3000/으로 이동하여 기본 Express 시작 페이지를 확인하세요.

![Express - Generated App Default Screen](https://mdn.mozillademos.org/files/14331/express_default_screen.png)

스켈레톤 애플리케이션 생성에 대한 기사를 볼 때 생성된 앱에 대해 더 자세히 이야기할 것입니다.



## [Summary](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#summary)

이제 Express 웹 응용 프로그램을 만드는 데 사용할 수 있는 컴퓨터에서 실행 중인 노드 개발 환경이 있습니다. 또한 NPM을 사용하여 Express를 응용 프로그램으로 가져오는 방법과 Express Application Generator 도구를 사용하여 응용 프로그램을 만든 다음 실행하는 방법도 보았습니다.

다음 기사에서는 이 환경 및 관련 도구를 사용하여 완전한 웹 애플리케이션을 빌드하는 자습서를 통해 작업을 시작합니다.

## [See also](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/development_environment#see_also)

- [Downloads](https://nodejs.org/en/download/) page (nodejs.org)
- [Installing Node.js via package manager](https://nodejs.org/en/download/package-manager/) (nodejs.org)
- [Installing Express](http://expressjs.com/en/starter/installing.html) (expressjs.com)
- [Express Application Generator](https://expressjs.com/en/starter/generator.html) (expressjs.com)





# Express 튜토리얼: 지역 도서관 웹사이트

실용적인 튜토리얼 시리즈의 첫 번째 문서에서는 학습할 내용을 설명하고 후속 문서에서 작업하고 발전하게 될 '로컬 라이브러리' 예제 웹사이트에 대한 개요를 제공합니다.

| 항목      | 내용                                                         |
| --------- | ------------------------------------------------------------ |
| 전제 조건 | 익스프레스 소개를 읽으십시오. 다음 기사의 경우 노드 개발 환경도 설정해야 합니다. |
| 목표      | 이 자습서에 사용된 예제 응용 프로그램을 소개하고 독자가 다룰 주제를 이해할 수 있도록 합니다. |



## [Overview](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/Tutorial_local_library_website#overview)

MDN '지역 도서관' 익스프레스(노드) 튜토리얼에 오신 것을 환영합니다. 여기에서 지역 도서관의 카탈로그를 관리하는 데 사용할 수 있는 웹사이트를 개발합니다.

이 가이드 문서 시리즈에서는 다음을 수행합니다.

- Express Application Generator 도구를 사용하여  웹사이트 골격과 애플리케이션을 만듭니다.
- 노드 웹 서버를 시작하고 중지합니다.
- 데이터베이스를 사용하여 애플리케이션 데이터를 저장합니다.
- 다양한 정보를 요청하기 위한 경로와 브라우저에 표시할 데이터를 HTML로 렌더링하는 템플릿('view')을 만듭니다.
- 양식으로 작업합니다.
- 애플리케이션을 프로덕션에 배포합니다.

이러한 주제 중 일부에 대해 이미 배웠고 다른 주제에 대해서도 간략하게 설명했습니다. 튜토리얼 시리즈가 끝나면 간단한 Express 앱을 스스로 개발할 수 있을 만큼 충분히 알게 될 것입니다.



## [LocalLibrary 웹사이트](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/Tutorial_local_library_website#the_locallibrary_website)

LocalLibrary는 이 가이드 시리즈를 통해 만들고 발전시킬 웹사이트의 이름입니다. 예상대로 웹사이트의 목적은 사용자가 사용 가능한 책을 검색하고 계정을 관리할 수 있는 소규모 지역 도서관에 온라인 카탈로그를 제공하는 것입니다.

이 예는 필요한 만큼 세부정보를 표시하도록 확장할 수 있고 거의 모든 Express 기능을 표시하는 데 사용할 수 있기 때문에 신중하게 선택되었습니다. 더 중요한 것은 모든 웹사이트에 필요한 기능을 통해 안내 경로를 제공할 수 있다는 점입니다.

- 처음 몇 개의 튜토리얼 기사에서 우리는 도서관 회원들이 어떤 책을 이용할 수 있는지 알아보는 데 사용할 수 있는 간단한 탐색 전용 도서관을 정의할 것입니다. 이를 통해 거의 모든 웹사이트에 공통적인 작업(데이터베이스에서 콘텐츠 읽기 및 표시)을 탐색할 수 있습니다.
- 진행하면서 도서관의 예시는 자연스럽게 확장되어 더 고급 웹사이트 기능을 보여줍니다. 예를 들어 새 책을 만들 수 있도록 도서관을확장하고 이를 사용하여 양식을 사용하고 사용자 인증을 지원하는 방법을 시연할 수 있습니다.

이것은 매우 확장 가능한 예이지만 이유가 있어 LocalLibrary라고 합니다. Express를 빠르게 시작하고 실행하는 데 도움이 되는 최소한의 정보를 보여드리고자 합니다. 결과적으로 우리는 책, 책 사본, 저자 및 기타 주요 정보에 대한 정보를 저장합니다. 그러나 우리는 도서관이 빌려줄 수 있는 다른 항목에 대한 정보를 저장하지 않거나 여러 도서관 사이트 또는 기타 '큰 도서관' 기능을 지원하는 데 필요한 인프라를 제공하지 않습니다.



**막혔는데 소스는 어디서 구하나요?**

가이드를 진행하면서 각 지점에서 복사하여 붙여넣을 수 있는 적절한 코드 스니펫이 제공되며, 일부 지침에 따라 직접 확장할 수 있는 다른 코드도 제공됩니다.

모든 코드 조각을 복사하여 붙여넣는 대신 입력해 보십시오. 다음에 비슷한 것을 작성하려고 할 때 코드에 더 익숙해지기 때문에 장기적으로 도움이 될 것입니다.

문제가 발생하면 여기 [Github 에서](https://github.com/mdn/express-locallibrary-tutorial) 웹사이트의 완전히 개발된 버전을 찾을 수 있습니다.

**Note:**

이 문서가 테스트된 특정 버전의 node, Express 및 기타 모듈은 package.json 프로젝트에 나열되어 있습니다.



### 요약
이제 LocalLibrary 웹사이트와 학습할 내용에 대해 조금 더 알게 되었으므로 이제 예제를 포함할 뼈대 프로젝트 만들기를 시작할 때입니다.



# Express Tutorial Part 2: 스켈레톤 웹사이트 만들기

Express Tutorial의 이 두 번째 기사에서는 사이트별 경로, 템플릿/보기 및 데이터베이스 호출을 채울 수 있는 '골격' 웹 사이트 프로젝트를 만드는 방법을 보여줍니다.

| Prerequisites: | [Set up a Node development environment](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/development_environment). Review the Express Tutorial. |
| :------------- | ------------------------------------------------------------ |
| Objective:     | *Express 앱 제너레이터를 사용하여 자신만의 새로운 웹사이트 프로젝트를 시작할 수 있다.* |



## [Overview](https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/skeleton_website#overview)

이 아티클은 당신이 [Express Application Generator](https://expressjs.com/en/starter/generator.html) 도구를 이용하여 스켈레톤(최소한의 프레임 모형만 갖춘) 웹사이트를 만드는 방법을 보여줍니다. 이는 사이트에 맞춘 라우트, 뷰/템플릿 그리고 데이터 베이스를 사용할 수 있게 합니다. 이 경우 도구를 사용하여 Local Library 웹 사이트의 프레임워크를 만들고 나중에 사이트에 필요한 다른 모든 코드를 추가합니다. 프로세스는 매우 간단하여 명령줄에서 새 프로젝트 이름으로 생성기를 호출하고 선택적으로 사이트의 템플릿 엔진과 CSS 생성기를 지정하기만 하면 됩니다.

다음 섹션에서는 애플리케이션 생성기를 호출하는 방법을 보여주고 다양한 view/CSS 옵션에 대한 약간의 설명을 제공합니다. 스켈레톤 웹사이트가 어떻게 구성되어 있는지도 설명합니다. 마지막으로 웹 사이트를 실행하여 작동하는지 확인하는 방법을 보여드리겠습니다.

```
Note:
```

https://developer.mozilla.org/ko/docs/Learn/Server-side/Express_Nodejs/skeleton_website

