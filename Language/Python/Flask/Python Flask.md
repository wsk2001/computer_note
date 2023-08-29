# Python Flask

출처: https://m.blog.naver.com/PostView.naver?blogId=dsz08082&logNo=221798632183&navType=by



## 01 파이썬 플라스크, Hello Flask

### 1. 플라스크(Flask)란.

파이썬 기반 마이크로 프레임워크. 마이크로 프레임워크란 풀 스택 프레임워크와 반대되는 개념으로 공식 플라스크에서는 다음과 같이 플라스크를 정의한다.

- 마이크로는 여러분의 웹 애플리케이션의 하나의 파이썬 파일로 개발해야 한다는 걸 말하는게 아니며, 기능적으로 부족하다는 걸 의미하지도 않는다.

플라스크에서 이야기하는 마이크로 프레임워크의 "마이크로"는 핵심기능은 간결하게 유지하되 필요한 기능을 손쉽게 확장할 수 있게 하는 것을 목적으로 한다. 기존 파이썬 2버전에서 작동되도록 개발되었지만 파이썬 3버전에서도 작동 가능하도록 저 수준 API가 모두 변경돼 있다. 파이썬만 작동 된다면 어느 운영체제에서도 사용할 수 있다.

#### 플라스크 특징.

플라스크 WSGI  구현체인 벡자이크 (Werkzug)와 템플릿 엔진인 신사2 (Jinja2)로 구성되어 있다.

 · WSGI : Web Server Gateway Interface, 애플리케이션 컨테이너 역할을 정의한 파이썬 표준. (PEP-333)

 · WSGI는 HTTP 웹 요청을 받아 응답하는 애플리케이션에 대한 규격이다.

플라스크는 다음 기능을 지원한다.

\- 개발용 서버와 디버거 내장

\- 단위테스트와 통합 지원

\- RESTful 요청 처리

\- 신사2 템플릿 엔진 내장

\- 안전한 쿠키 지원

\- 100% WSGI 1.0 호환

\- 유니코드 기반

\- 광범위한 문서 제공

\- 구글 앱 엔진 호환

\- 원하는 기능을 추가하여 확장 기능



### 2. 플라스크를 사용해 웹 페이지에 문자열 출력하기

다음 소스코드를 활용해 웹 페이지에 문자열을 출력한다.

``` py
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
	return "<h1>Hello World!</h>"

if __name__ == "__main__":
	app.run()
```

소스코드는 총 7줄로 사용법 자체는 크게 어렵지 않다.

| from flask import Flask                           | Flask 모듈을 가져온다.<br>- flask 모듈에서 Flask 함수를 가져옴. |
| ------------------------------------------------- | ------------------------------------------------------------ |
| app = Flask(__name__)                             | Flask 객체를 app 변수에 할당.<br>- Flask 함수로 Flask 객체를 할당, 객체로 모든 플라스크 기능 사용 가능. |
| @app.route("/")                                   | app 객체를 이용해 라우팅 경로를 설정.<br>- 어플리케이션의 루트 경로(/)를 호출했을 때 함수가 실행되게 설정. |
| def hello():<br>    return "<h1>Hello World!</h>" | 해당 라우팅 경로로 요청이 오면 실행할 함수 정의.<br> (문자열 출력)- 뷰함수(특정 URL을 호출했을 대 호출되는 함수)에 속한다. |
| if __name__ == "__main__":<br>    app.run()       | 메인 모듈로 실행될 때 플라스크 서버 구동.<br>- 파이썬에 인터프리터에 의해 메인 모듈로 실행됐는지 확인 후 앱 실행. |

코드를 실행하면 로컬호스트 루프백 IP인 127.0.0.1의 플라스크 기본 포트 번호인 5000번으로 서버가 실행된다.

 · Serving Flask app "hello" (lazy loading) - 플라스크 앱인 "hello"을 옮겨 로딩. (파일 이름이 hello.py 이니)

  · Environment: production - 환경에 대해 명시. 경고 메시지는 개발 서버이니 배포에 사용하지 말 것 당부.

  · * Debug mode: off - 디버그 모드가 활성화 되어있지 않음.

  · * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit) 

​    \- 해당 주소로 서버가 활성화 되어있음. Ctrl + c 키나 quit를 입력하면 서버가 중단됨.

​    \- 이후로 서버 로그가 나옴. 로그는 [url] - - [시각] "요청" [HTTP 코드] - 의 형태



127.0.0.1:5000 혹은 localhost:5000으로 url을 입력해 브라우저에 접속하면 지정한 함수 안 내용처럼 Hello World! 문자열이 출력된다. 함수에 지정한 문자열은 <h1>태그로 둘려싸여있다. 이는 글씨 크키를 지정하는 HTML 웹 언어의 태그를 가져와 사용한 것이다.

태그를 제외하고 코드를 재실행하면 기본 글씨가 출력된다.



### 3. 플라스크 어플리케이션 

플라스크 어플리케이션을 만들고 실행하는 법은 간단하다. 다음 과정을 통해 플라스크 어플리케이션이 호출된다.

![img](.\images\ZRkxUggjSbwuxsIpODxiINOIqq4TWqEwyc_MKn_oLlsg.png)

[그림 3-1] 플라스크 어플리케이션 실행 과정

1. 플라스크는 특정 URL이 호출되며 실행된다.
2. 특정 URL이 호출되면 그 URL에 매핑된(대응하는) 함수가 실행된다.
3. 요청한 URL이나(HTTP GET 요청을 통해 가져올 글 요청), 내용(HTTP POST 요청을 통해 내용 요청)을 분석해  

​     비즈니스 로직인 논리를 실행한다. 이때, 상황에 따라 요청의 상태를 유지할 경우 쿠키나 세션을 사용한다. 또한 

​     프로그램의 상태를 기록하기 위해 로깅을 하고 오류가 발생한 경우에 처리할 로직을 별도로 제공할 수도 있다. 

4. 논리가 처리되면 결과를 응답으로 템플릿에 반환한다.
5. 응답으로 전송할 값을 HTML에 표현한다.



## 02 파이썬 플라스크 라우팅

### 1. 플라스크 정적 페이지 라우팅

플라스크는 복잡한 URL를 쉽게 함수로 연결하는 방법을 제공한다. 해당 기능인 route() 함수를 사용해 쉽고 빠르게 원하는 URL를 처리하는 기능을 만들 수 있다. 



이전 글에서 학습한 웹에 문자열 출력하기 예제가 있다. 지난 예제에 출력 문자열과 함수 이름이 변경된 상태이다. 다음 예제는 localhost:5000의 루트(url 뒤 /, /는 생략 가능.)로 접속하면 함수가 실행된다. 

``` py
from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello_flask():
    return 'Hello Blog!'

if __name__ == '__main__':
    app.run()
```

하위 경로를 만들어 함수가 실행되게 해보자. /hello 경로로 들어가야만 함수가 실행되게 route 설정을 변경한다.

``` py
from flask import Flask
app = Flask(__name__)

@app.route('/hello')
def hello_flask():
    return 'Hello Blog!'

if __name__ == '__main__':
    app.run()
```

그러면 기존 루트 경로에서는 연결된 함수가 없어 페이지를 찾을 수 없다.

/hello 경로로 들어가면 연결된 뷰함수가 실행되어 지정한 문자열이 출력된다.



### 2. 플라스크 동적 페이지 라우팅

직접 URL을 지정하는 것이 아닌 상황에 따라 다른 URL 변화하는 동적 적용도 가능하다. 또한 여러 URL을 한 함수에 적용할 수도 있다. URL에 동적인 변수를 사용하려면 원하는 위치에 "<변수>" 형태로 추가한다. 해당 변수는 URL과 일치하는 뷰 함수의 인자로 사용할 수 있다. 



코드에서 동적으로 변경되는 부분은 인자로 전달될 username 변수에 해당한다. URL의 끝점(endpoint)으로 추가된 username은 해당 URL과 일치하는 뷰 함수인 get_profile 함수의 인자로 들어가 함수 내에서 사용된다.

``` py
@app.route('/profile/<username>') #/profile/ 경로에 뒤에 입력한 문자열대로 페이지가 동적 접속.
def get_profile(username):
    return 'profile : ' + username
```

해당 코드를 반영해 #1 과정 코드에 적용한 후 실행하자. url을 테스트하는 출력 구문이 하나 추가되었다.

``` py
from flask import Flask, url_for
app = Flask(__name__)

@app.route("/")
def hello():
	return "Hello Page!"

@app.route('/profile/<username>')
def get_profile(username):
    return 'profile : ' + username

if __name__ == "__main__":
	with app.test_request_context(): 
		print (url_for('get_profile', username='flask'))
		app.run()
```

test_request_context() 함수를 사용해 url_for 함수에서 반환되는 url을 확인한다. 코드 실행 시 username에 flask를 줬을 경우 반환하는 url을 출력하는데 /profile/flask가 출력되었다.

단 입력 형태는 앞서 확인했듯이 끝점에 슬래시('/')가 없는 형태다. 슬래시가 추가될 경우 페이지를 찾지 못한다.

"<<변환타입:변수>>" 형태로 기입해 문자열이 아닌 다른 형태로 변환할 수도 있다. 다음 코드는 /message 경로에 숫자 형태로 입력을 받아 출력하며 출력 형태는 파이썬의 **포맷팅** 구조와 같이 %를 이용해 포맷팅해준다.

``` py
@app.route('/message/<int:message_id>')
def get_message(message_id):
    return 'message_id : %d' % message_id
```

기존 코드에 위 코드를 적용해 결과를 확인한다.

``` py
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
	return "Hello Page!"

@app.route('/profile/<username>')
def get_profile(username):
    return 'profile : ' + username

@app.route('/message/<int:message_id>')
def get_message(message_id):
    return 'message_id : %d' % message_id

if __name__ == "__main__":
		app.run()
```



## 03 파이썬 플라스크 HTML 렌더링

플라스크 웹 페이지 정적 / 동적 라우팅.

웹은 크게 사용자에게 보여지는 프론트엔드와 프론트에서 요청한 내용을 처리하는 백엔드단이 있다.

플라스크는 장고와 달리 마이크로 프레임워크기 떼문에 프론트엔드와 백엔드를 동시에 처리하지 못한다.

플라스크는 요청을 처리하는 등 서버의 백엔드만 담당하며 사용자에게 보여지는 부분은 프론트엔드 언어를 사용해야 한다. 웹 페이지에서 주로 보여지는 부분인 HTML 언어를 알아보고 플라스크에서 활용면을 살펴본다.



### 1. HTML

HTML : 하이퍼텍스트 마크업 언어(HyperText Markup Language), 구조적 웹 문서를 만들 수 있는 언어로 태그 단위로 제어한다. 너무 간단하고 태그로만 이뤄진 스크립트라는 등의 이유로 프로그래밍 언어의 범주는 속하지 않지만 웹을 구축하기 위해서는 꼭 알아야 할 언어다.



HTML 언어로 구조화하여 문자열을 출력하는 법은 다음 코드와 같다.

``` html
<!doctype html>
<html>
  <head>
    <title>Hello HTML</title>
  </head>
  <body>
    <p>Hello World!</p>
  </body>
</html>
```

확장자 : html

특징과 문법 : 

\- 태그 구조 : 시작 태그와 종료 태그가 한 묶음이며 종료 태그에는 "/"가 붙는다.

\- 문서의 시작 : 문서의 시작은 <html>이다. 이는 문서가 html 문서임을 표시하는 것이다.

\- 주석 : 일종의 개발자들의 설명줄. 코드에 영향을 주지 않는다. html에서는 <! 내용>으로 표기한다.

\- 코드 구조 : html은 <head>와 <body> 구조로 나뉜다. 

:: <head>는 문제 제목과 같은 보충 정보

:: <body>는 실제 브라우저 표시 내용 (웹 문서의 본문)

:: <title> 태그는 브라우저의 윗부분인 제목을 설정하는 것.

:: <h1~6> 태그는 표제로 <h1>~<h6>까지 숫자가 커질수록 글씨가 작아진다.

:: <p> 태그는 단락(문장) 변경을 의미한다.

:: <br> 태그는 줄 바꿈을 의미한다.

:: <center> 태그는 가운데 정렬을 의미한다.



이 외 다양한 태그가 존재한다.

간단히 플라스크를 사용해 만든 웹 페이지에 접속해보자.

``` py
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
	return "Hello Page!"

if __name__ == "__main__":
		app.run()
```

웹 페이지에 접속해 F12 개발자키를 누르면 페이지 소스를 볼 수 있다. 파이썬에서 입력한 문자열이 HTML 코드로 자동 반영되어 들어가 있다.



### 2. 플라스크에서 HTML 코드 사용하기

HTML 태그를 사용해 웹 페이지를 꾸며보자. 

HTML 언어로 작성하여 플라스크를 이용해 나타내는 법은 어렵지 않으며 이미 [지난 글](https://dsz08082.blog.me/221798632183)에서 해본적이 있다.

``` py
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
	return "<h1>Hello World!</h>"

if __name__ == "__main__":
	app.run()
```

이번에는 HTML이 무엇이고 어떻게 사용하는지 알았으니 간단히 기본 태그와 입력, 버튼 태그만 가지고 입력창을 만들어보자.

``` py
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
	html = """\
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8"> 
		<title>HTML for python flask</title>
	</head>
	<body>
	<form>
		​<p>이름 : <input type="text" id="input"></p>
		<p>이름을 입력하고 제출버튼을 누르세요. <input type="button" value="제출" onclick="alert('제출 완료!')" /></p>
	</form>
	</body>
	"""
	return html

if __name__ == "__main__":
		app.run()
```

태그는 대부분 직관적이라 알아보기 쉽다. html 언어로 작성한 내용을 변수에 담아 반환하게 했다.



실행 결과 작성된 내용이 반환된다.

### 3. 플라스크 HTML 파일 렌더링

파이썬 코드 내부에서 html 코드작업을 하다보면 다소 불편하고 작성이 어렵다.

플라스크에서 제공하는 렌더링 함수를 이용해 외부에서 작성한 html 파일을 가져와 사용할 수 있어 간편하게 웹 페이지를 조성할 수 있다.

html 변수에 담았던 html 작성 내용을 파이썬 코드가 위치한 경로에 templates 폴더를 만든 뒤 .html 확장자로 앞내용을 넣어 저장한다.

``` html
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8"> 
		<title>HTML for python flask</title>
	</head>
	<body>
	<form>
		​<p>이름 : <input type="text" id="input"></p>
		<p>이름을 입력하고 제출버튼을 누르세요. <input type="button" value="제출" onclick="alert('제출 완료!')" /></p>
	</form>
	</body>
```

작성한 코드의 내용을 render_temlate 함수를 이용해 가져올 것이다. 모듈을 불러와 사용한다. 다음 코드 한 줄이면 html 내용을 쉽게 가져올 수 있다. 각 html 웹 페이지를 별도의 파일로 작성하면 유지보수가 쉬워 용이하다.

``` py
return render_template('파일명.html')
```

적용된 코드는 다음과 같다.

``` py
from flask import Flask, render_template
app = Flask(__name__)

@app.route("/")
def hello():
	return render_template('input.html')

if __name__ == "__main__":
		app.run()
```

마찬가지로 작성한 html의 내용을 사용할 수 있다.



## 04 파이썬 플라스크 GET POST

HTML, 플라스크 웹 페이지 렌더링.

플라스크가 제공하는 기능 중 하나인 요청과 응답을 알아본다.

GET 요청과 POST 요청을 살펴본다. 아울러 HTTP 메소드 개념을 학습한다.



### 1. HTTP 메소드

어떤 방식으로 웹 어플리케이션 페이지를 개발하더라도 가장 기본적이고 필수적인 처리 과정은 요청을 받아 적절히 처리하고 그 결과로 응답을 주는 것이다.

가장 먼저 처리해야 하는 것은 HTTP 요청으로 넘어온 데이터를 어떻게 받아 처리하는지다. 이완 관련해 HTTP 메서드를 먼저 알아보자.

모든 컴퓨터 서비스는 인터넷 통신 규약인 프로토콜의 하나를 사용해 통신한다. 웹 사이트의 URL은 그 중 http와 https 프로토콜을 사용한다. 

기존 http 프로토콜이 사용되었으며 점차 보안을 위해 보안 기능이 탑재된 https 프로토콜을 사용하기 시작한다. 이는 WWW(world wide web)에서 데이터 통신을 하는데 근간이다. 명시된 URL에 데이터를 탐색하는 메서드가 프로토콜에 정의되어있다.

HTTP 메서드는 다음과 같이 존재한다.

| GET  | 리소스 취득      | DELETE  | 파일 삭제                |
| ---- | ---------------- | ------- | ------------------------ |
| POST | 엔티티 바디 전송 | TRACE   | 경로 조사                |
| PUT  | 파일 전송        | CONNECT | 프록시에서의 터널링 요구 |
| HEAD | 메시지 헤더 취득 | OPTIONS | 응답 가능 메소드 요청    |

HTTP 웹 통신 관련 자세한 내용은 다음 링크에서 확인할 수 있다.

https://blog.naver.com/dsz08082/221591491162

여기서 알아볼 것은 요청이다. 서버에 요청을 보내 응답을 받기 위해서 GET 혹은 POST 방식으로 요청한다.

### 2. 플라스크 GET 요청

플라스크에서 요청에 대한 정보는 request에 담겨있고 객체는 안전을 보장한다. 파이썬에 존재하는 requests 모듈이 아니라 플라스크 프레임워크에 존재하는 request를 불러와 사용해야 하겠다.



이 request 모듈에는 HTTP 메서드에 대한 정보를 얻을 수 있는 method와 GET 방식으로 URL에 인자를 'key=value' 형태로 전달했을 때 그 인자를 참조할 수 있는 args, POST나 PUT 방식의 HTML 폼 데이터를 얻을 수 있는 form 속성이 있다.



GET 방식의 경우 모든 파라미터를 url로 보내 요청하는 방식이다. url에 파라미터로 값을 입력하기 위해 사용한다. url에 파라미터로 값을 넣는 방법은 ?를 붙이고 키=값의 쌍 형태로 넣으면 된다. 파라미터를 추가하고자 할 때는 &를 붙인 뒤 동일하게 추가한다.

```
http://localhost:5000/?name=user01&juso=평택시
```

코드로 request의 args를 활용한 GET 방식 요청을 살펴보자.

``` py
from flask import Flask
from flask import request
 
app = Flask(__name__)
 
@app.route('/')
def user_juso():
 
    temp = request.args.get('name', "user01")
    temp1 = request.args.get('juso', "평택시")
 
    return temp + "-" + temp1

if __name__ == "__main__":
		app.run()
```

