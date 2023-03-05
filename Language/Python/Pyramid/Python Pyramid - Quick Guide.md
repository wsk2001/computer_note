# Python Pyramid - Quick Guide

출처: https://www.tutorialspoint.com/python_pyramid/python_pyramid_quick_guide.htm



## Python Pyramid - Overview

Pyramid는 Python으로 작성된 오픈 소스, WSGI 호환 웹 프레임워크입니다. 처음에는 Pylons라는 이름의 프로젝트였지만 나중에 Pyramid라는 새 이름으로 출시되었습니다.

Pyramid는 최소한의 웹 프레임워크입니다. 템플릿 라이브러리와 함께 제공되지 않고,  특정 데이터베이스 패키지를 지원하지 않습니다.

그러나 SQLAlchemy를 통해 SQL 데이터베이스와 Zope 개체 데이터베이스는 물론 CouchDB와 같은 다른 NoSQL 데이터베이스와 통합할 수 있습니다.

Pyramid는 Mako, Jinja2 또는 Chameleon과 같은 템플릿 라이브러리와 함께 작동하도록 구성할 수도 있습니다.

피라미드는 Chris McDonough가 개발했습니다. Pyramid의 첫 번째 버전은 2011년 1월에 출시되었습니다. 최신 버전인 Pyramid 2.0은 2021년 3월에 출시되었습니다.



## 다른 파이썬 프레임워크와의 비교

Pyramid 웹 애플리케이션 프레임워크는 Zope 및 Django 프레임워크에서 영감을 받았습니다. 결과적으로 둘 의 장점을 취했습니다.

Pyramid는 주로 repose.bfg 프레임워크를 기반으로 합니다. Pylons 프로젝트와 병합된 후 2010년에 Pyramid로 이름이 변경되었습니다.

Pyramid 애플리케이션을 확장하는 기능은 Zope 라이브러리에서 빌려옵니다. 애플리케이션 코드를 수정하지 않고 애플리케이션을 재사용, 수정 또는 확장할 수 있습니다. 선언적 보안 계층 및 경로 순회와 같은 기능은 Zope에서 상속됩니다.

Pylons 1.0의 경우와 마찬가지로 Pyramid는 정책을 시행하지 않습니다. 또한 사용자가 데이터베이스 또는 템플릿 시스템을 선택할 수 있습니다. URL 디스패치 방식도 Pylons에서 영감을 받았습니다.

뷰의 개념은 Django의 유사한 접근 방식을 기반으로 합니다. 광범위한 문서는 Pyramid에 의해 채택된 Django 기능이기도 합니다.

정의가 정확히 들어맞지는 않지만 Pyramid는 MVC(Model-View-Controller) 접근 방식을 따른다고 할 수 있습니다.



## Python Pyramid - 환경 설정

Pyramid 패키지는 Python 3.6 이상 버전이 설치된 시스템에 설치하는 것을 권장합니다. Pyramid는 Linux, MacOS 및 Windows 플랫폼에 설치할 수 있습니다. 가장 간단한 설치 방법은 PIP 설치 프로그램을 사용하는 것입니다. Python 가상 환경에서 사용하는 것이 좋습니다.

``` bash
pip3 install pyramid
```

Pyramid 웹 애플리케이션은 wsgiref 모듈의 일부인 내장 WSGI 개발 서버를 사용하여 실행할 수 있지만 프로덕션 환경에서는 사용하지 않는 것이 좋습니다. 따라서 프로덕션 품질의 순수 Python WSGI 서버인 Waitress도 설치합니다(Pylons 프로젝트의 일부이기도 함).

``` bash
pip3 install waitress
```

이렇게 하면 WebOb, PasteDeploy 등과 같은 Pylon 프로젝트의 다른 종속성 외에도 Pyramid(버전 2.0), Waitress(버전 2.1.2)가 설치됩니다. 무엇이 설치되는지 확인하려면 pip freeze 명령을 실행하십시오.

``` bash
pip3 freeze
```



## Python Pyramid - Hello World

### Example

종속 항목과 함께 Pyramid가 제대로 설치되었는지 확인하려면 Python 인식 편집기를 사용하여 다음 코드를 입력하고 hello.py로 저장합니다.

``` py
from wsgiref.simple_server import make_server
from pyramid.config import Configurator
from pyramid.response import Response

def hello_world(request):
   return Response('Hello World!')
   
if __name__ == '__main__':
   with Configurator() as config:
      config.add_route('hello', '/')
      config.add_view(hello_world, route_name='hello')
      app = config.make_wsgi_app()
   server = make_server('0.0.0.0', 6543, app)
   server.serve_forever()
```

Configurator 개체는 URL 경로를 정의하고 여기에 보기 기능을 바인딩하는 데 필요합니다. WSGI 응용 프로그램 개체는 이 구성 개체에서 가져오며 localhost의 IP 주소 및 포트와 함께 make_server() 함수에 대한 인수입니다. 서버 개체는 serve_forever() 메서드가 호출될 때 수신 루프에 들어갑니다.

명령 터미널에서 이 프로그램을 실행하십시오.

``` bash
Python hello.py
```

### Output

WSGI 서버가 실행되기 시작합니다. 브라우저를 열고 주소 표시줄에 http://localhost:6543/을 입력합니다. 요청이 수락되면 hello_world() 뷰 함수가 실행됩니다. Hello world 메시지를 반환합니다. 브라우저 창에 Hello world 메시지가 표시됩니다.

![Hello World](https://www.tutorialspoint.com/python_pyramid/images/hello_world.jpg)

앞서 언급했듯이 wsgiref 모듈에서 make_server() 함수로 생성된 개발 서버는 프로덕션 환경에 적합하지 않습니다. 대신 Waitress 서버를 사용합니다. 다음 코드에 따라 hello.py를 수정합니다.

``` py
from pyramid.config import Configurator
from pyramid.response import Response
from waitress import serve

def hello_world(request):
   return Response('Hello World!')
   
if __name__ == '__main__':
   with Configurator() as config:
      config.add_route('hello', '/')
      config.add_view(hello_world, route_name='hello')
      app = config.make_wsgi_app()
      serve(app, host='0.0.0.0', port=6543)
```

웨이트리스 모듈의 serve() 함수를 사용하여 WSGI 서버를 시작한다는 점을 제외하면 다른 모든 기능은 동일합니다. 프로그램 실행 후 브라우저의 '/' 경로를 방문하면 이전과 같이 Hello world 메시지가 표시됩니다.

함수 대신 콜러블 클래스를 뷰로 사용할 수도 있습니다. 콜러블 클래스는 __call__() 메서드를 재정의하는 클래스입니다.

``` py
from pyramid.response import Response
class MyView(object):
   def __init__(self, request):
      self.request = request
   def __call__(self):
      return Response('hello world')
```



## Python Pyramid - 애플리케이션 구성

Pyramid 응용 프로그램 개체에는 경로에 대한 보기 기능의 매핑 및 기타 응용 프로그램별 구성 요소 등록을 저장하는 응용 프로그램 레지스트리가 있습니다. Configurator 클래스는 애플리케이션 레지스트리를 빌드하는 데 사용됩니다.

Configurator 수명 주기는 애플리케이션 개체를 반환하는 컨텍스트 관리자가 관리합니다.

``` py
with Configurator(settings=settings) as config:
   #configuration methods
   app = config.make_wsgi_app()
```

Configurator 클래스는 응용 프로그램을 사용자 지정하기 위해 다음과 같은 중요한 메서드를 정의합니다.



### add_route()

이 메서드는 URL 발송을 위한 경로를 등록합니다. 다음 인수가 사용됩니다.

- **name** −  첫 번째 필수 위치 인수는 경로의 고유한 이름이어야 합니다. 이름은 보기를 등록하거나 URL을 생성할 때 경로를 식별하는 데 사용됩니다.
- **pattern** − 두 번째 필수 위치 인수는 URL에서 변수 데이터를 구문 분석하기 위한 변수 자리 표시자를 선택적으로 포함하는 URL 경로를 나타내는 문자열입니다. 자리 표시자는 중괄호로 둘러싸여 있습니다. 예: '/students/{id}'.
- **request_method** − 값은 'GET', 'POST', 'HEAD', 'DELETE', 'PUT' 중 하나일 수 있습니다. 이 유형의 요청만 경로와 일치합니다.



### add_view()

이 메서드는 애플리케이션 레지스트리에,  보기 구성을 추가합니다. 보기 기능을,  configuration 에 있는 route_name에 바인딩합니다. 필요한 인수는 −

- **view** −  뷰 함수의 이름입니다.
- **route_name** − 경로 구성 선언의 이름과 일치해야 하는 문자열입니다.
- **request_method** − HTTP REQUEST_METHOD를 나타내는 문자열(예: 'GET', 'POST', 'PUT', 'DELETE', 'HEAD' 또는 'OPTIONS') 또는 이러한 문자열 중 하나 이상을 포함하는 튜플입니다.



### add_static_view()

이 메서드는 이미지 및 CSS 파일과 같은 정적 자산을 렌더링하는 데 사용되는 보기를 추가하고 다음 인수를 사용합니다.

- **name** − 이 인수는 애플리케이션 관련 로컬 URL 접두사 또는 전체 URL을 나타내는 문자열입니다.
- **Path** − 이 인수는 정적 파일이 있는 디스크의 경로를 나타냅니다. 해당 값은 절대 또는 패키지 상대 경로일 수 있습니다.

이 메소드는 차례로 Configurator 객체의 **add_route()** 메소드를 호출합니다.



### add_notfound_view()

이 메서드는 현재 요청에 대해 일치하는 보기를 찾을 수 없을 때 실행할 보기를 추가합니다. 다음 코드는 예를 보여줍니다.

``` py
from pyramid.config import Configurator
from pyramid.response import Response

def notfound(request):
   return Response('Not Found', status='404 Not Found')
   
config.add_notfound_view(notfound)
```



### add_forbidden_view()

HTTPForbidden 예외가 발생할 때 실행할 뷰를 정의하도록 애플리케이션 레지스트리를 구성합니다. 인수 목록에는 403 상태 응답을 반환하는 함수에 대한 참조가 포함되어 있습니다. 인수가 제공되지 않으면 레지스트리는 default_exceptionresponse_view()를 추가합니다.



### add_exception_view()

이 메서드는 지정된 예외에 대해 구성에 예외 보기 기능을 추가합니다.



### make_wsgi_app()

이 메서드는 Pyramid WSGI 애플리케이션 개체를 반환합니다.

- [WSGI(Web Server Gateway Interface)](https://m.blog.naver.com/ikarte666/222131525435)



### scan()

뷰 등록을 위한 래퍼입니다. @view_config 데코레이터를 찾는 모든 애플리케이션 모듈을 가져옵니다.

각각에 대해 동일한 키워드 인수로 config.add_view(view)를 호출합니다. scan() 함수에 대한 호출은 모든 데코레이션에 대한 패키지 및 모든 하위 패키지의 스캔을 수행합니다.

애플리케이션 레지스트리 구성을 수행하는 명령문의 일반적인 시퀀스는 다음 코드 스니펫과 같습니다.

``` py
from pyramid.config import Configurator

with Configurator() as config:
   config.add_route('hello', '/')
   config.add_view(hello_world, route_name='hello')
   app = config.make_wsgi_app()
```

애플리케이션 구성에 대한 이러한 접근 방식을 명령형 구성이라고 합니다. 피라미드는 장식 구성이라고 하는 구성에 대한 또 다른 접근 방식을 제공합니다.



### 선언적 구성

특히 응용 프로그램 코드가 여러 파일에 분산되어 있는 경우 명령형 코드로 구성을 수행하는 것이 어려운 경우가 있습니다. 선언적 구성은 편리한 접근 방식입니다. pyramid.view 모델은 뷰 함수 자체의 정의에 매우 근접한 뷰 등록을 허용하는 함수, 클래스 또는 메서드 데코레이터인 view_config를 정의합니다.

@view_config() 데코레이터에 두 가지 중요한 인수가 제공됩니다. 그들은 route_name과 request_method입니다. Configurator 클래스의 add_route() 메소드와 동일한 설명을 합니다. 바로 아래 함수는 응용 프로그램 개체의 레지스트리에 추가된 경로에 바인딩되도록 데코레이션됩니다.

아래는 hello_world() 뷰 함수의 선언적 구성 예입니다.

``` py
from pyramid.response import Response
from pyramid.view import view_config

@view_config(route_name='hello', request_method='GET')
def hello_world(request):
   return Response('Hello World!')
```

view_config 데코레이터는 hello_world() 함수에 속성을 추가하여 스캔에서 나중에 찾을 수 있도록 합니다.

#### Example

구성 데코레이션과 스캔 호출의 조합을 집합적으로 선언적 구성이라고 합니다. 다음 코드는 선언적 접근 방식으로 애플리케이션 레지스트리를 구성합니다.

scan() 함수는 경로와 해당 매핑된 보기를 검색하므로 필수 구성 문을 추가해야 합니다.

``` py
from wsgiref.simple_server import make_server
from pyramid.config import Configurator
from pyramid.response import Response
from pyramid.view import view_config

@view_config(route_name='hello', request_method='GET')
def hello_world(request):
   return Response('Hello World!')
   
if __name__ == '__main__':
   with Configurator() as config:
      config.add_route('hello', '/')
      config.scan()
      app = config.make_wsgi_app()
   server = make_server('0.0.0.0', 6543, app)
   server.serve_forever()
```

스캐너는 view_config에 대한 인수를 pyramid.config.Configurator.add_view() 메서드에 대한 호출로 변환하므로 작업은 다음 명령문과 동일합니다.

``` py
config.add_view(hello_world, route_name='hello', request_method='GET')
```

#### Output

위의 프로그램이 실행되면 WSGI 서버가 시작됩니다. 브라우저가 http://localhost:6543/ 링크를 방문하면 'Hello World' 메시지가 이전과 같이 렌더링됩니다.

![Config](https://www.tutorialspoint.com/python_pyramid/images/config.jpg)



## Python Pyramid - Url Routing

MVC 아키텍처가 등장하기 전에 웹 애플리케이션은 사용자가 브라우저에 입력한 URL을 브라우저에 대한 응답으로 HTML로 렌더링되는 프로그램 파일에 매핑하는 메커니즘을 사용했습니다. Pyramid 프레임워크는 URL의 엔드포인트가 애플리케이션의 레지스트리에 등록된 다른 URL 패턴과 일치하는 라우팅 메커니즘을 사용하고 매핑된 보기를 호출하고 응답을 렌더링합니다.

일반적인 URL은 프로토콜(예: http:// 또는 https://)과 IP 주소 또는 호스트 이름의 세 부분으로 구성됩니다. URL의 첫 번째/호스트 이름 뒤의 나머지 부분을 경로 또는 끝점이라고 합니다.

![Mysite](https://www.tutorialspoint.com/python_pyramid/images/mysite.jpg)

하나 이상의 변수 부분이 뒤따르는 끝점이 경로를 형성합니다. 변수 부분 식별자는 중괄호로 묶습니다. 예를 들어 위 URL의 경우 경로는 /blog/{id}입니다.

WSGI 애플리케이션은 라우터 역할을 합니다. 경로 맵에 있는 URL 패턴에 대해 들어오는 요청을 확인합니다. 일치하는 항목이 발견되면 관련 뷰 콜러블이 실행되고 응답이 반환됩니다.

### Route Configuration

Configurator 객체의 add_route() 메서드를 호출하여 애플리케이션에 새 경로를 추가합니다. 경로에는 URL 생성에 사용되는 식별자 역할을 하는 이름과 URL의 PATH_INFO 부분(예: URL의 /blog/1과 같이 스키마와 포트 뒤에 오는 부분)과 일치하는 패턴이 있습니다. http://example.com/blog/1).

앞서 언급했듯이 add_route() 메서드의 패턴 매개변수는 중괄호로 묶고 /로 구분된 하나 이상의 자리 표시자 식별자를 가질 수 있습니다. 다음 문은 '/{name}/{age}' 패턴에 주어진 경로 이름으로 'index'를 할당합니다.

``` py
config.add_route('index', '/{name}/{age}')
```

호출 가능한 보기를 이 경로에 연결하기 위해 다음과 같이 add_view() 함수를 사용합니다.

``` py
config.add_view(index, route_name='index')
```

일치시킬 경로에 대해 index() 함수를 사용할 수 있어야 합니다.

``` py
def index(request):
   return Response('Root Configuration Example')
```

#### Example

우리는 아래 프로그램에 이러한 문장을 넣습니다.

``` py
from wsgiref.simple_server import make_server
from pyramid.config import Configurator
from pyramid.response import Response

def index(request):
   return Response('Root Configuration Example')
   
if __name__ == '__main__':
   with Configurator() as config:
      config.add_route('index', '/{name}/{age}')
      config.add_view(index, route_name='index')
      app = config.make_wsgi_app()
server = make_server('0.0.0.0', 6543, app)
server.serve_forever()
```

#### Output

위의 코드를 실행하고 브라우저에서 http://localhost:6543/Ravi/21을 방문하십시오. URL의 PATH_INFO가 인덱스 경로와 일치하면 다음 출력이 표시됩니다.

![Root Configuration](https://www.tutorialspoint.com/python_pyramid/images/root_configuration.jpg)

경로 구성에 사용되는 패턴은 일반적으로 슬래시(/) 문자로 시작합니다. 패턴 세그먼트(패턴에서 / 문자 사이의 개별 항목)는 리터럴 문자열이거나 자리 표시자 마커(예: {name})이거나 둘의 특정 조합일 수 있습니다. 교체 마커 앞에 / 문자가 올 필요는 없습니다.

다음은 경로 패턴의 몇 가지 예입니다.

``` py
/student/{name}/{marks}
/{id}/student/{name}/{marks}
/customer/{id}/item/{itemno}
/{name}/{age}
```

자리 표시자 식별자는 유효한 Python 식별자여야 합니다. 따라서 대문자 또는 소문자 ASCII 문자 또는 밑줄로 시작해야 하며 대문자 또는 소문자 ASCII 문자, 밑줄 및 숫자만 사용할 수 있습니다.



### Route Matching

들어오는 요청이 특정 경로 구성과 연결된 URL 패턴과 일치하면 matchdict라는 사전 개체가 요청 개체의 특성으로 추가됩니다.

request.matchdict에는 패턴 요소의 대체 패턴과 일치하는 값이 포함되어 있습니다. matchdict의 키는 문자열이고 값은 유니코드 객체입니다.

이전 예제에서 index() 보기 함수를 다음과 같이 변경하십시오.

``` py
def index(request):
   return Response(str(request.matchdict))
```

