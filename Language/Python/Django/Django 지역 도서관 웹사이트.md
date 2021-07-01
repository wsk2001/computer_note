# Django 소개

출처: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Introduction

Django의 첫번째 문서에서는 "Django가 뭐지?"라는 질문에 답해보고,  Django 웹 프레임워크의 특별한 부분에 대해 전반적으로 살펴봅니다. 우리가 이 수업에서 자세히 다루지는 않을 고급 기능들까지  포함하여 간단하게 전반적인 부분을 살펴 볼겁니다. 또한, Django 애플리케이션을 구성하는 중요한 요소도 살펴보겠습니다. (물론 지금 시점에서는 테스트를 할 개발환경을 가지고 있지 않겠지만요.)

| 요구 사항 | 기본적인 컴퓨터 지식. [server-side website 프로그래밍](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps)에 대한 전반적인 이해, 그리고 웹사이트의 [client-server interactions](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps/Client-Server_overview) 의 매커니즘에 대한 특정한 지식. |
| --------- | ------------------------------------------------------------ |
| 목표      | Django란 무엇인지, 어떤 기능이 있는지, Django 어플리케이션의 주요 구성요소는 어떤것들인지에 대해 익숙해지기 |



## Django란?

Djano란 보안이 우수하고 유지보수가 편리한 웹사이트를 신속하게 개발하는 하도록 도움을 주는 파이썬 웹 프레임워크입니다. 훌륭한 개발자에  의해 만들어진 이 프레임워크는, 웹 개발을 하는데 많은 도움을 주기 때문에 새롭게 웹 개발을 시작할 필요없이 그저 프레임워크를  활용하여 앱 개발에만 집중할 수 있게되죠. 무료 오픈소스인데다가, 활발한 커뮤니티들이 있고, 좋은 참고자료와 무료 및 유료 지원을 하는 옵션들이 제공됩니다.

Django는 다음과 같은 소프트웨어를 개발하는데 도움을 줍니다.

- Complete(완결성 있는)

  Django는 "Batteries included" 의 철학을 기반으로 개발자들이 개발하고 싶은 거의 모든것을 개발하는데 도움을 줍니다. 개발자들이 원하는 것은 모두 하나의 "결과물"의 일부일 것이기 때문에 도달하고자 하는 목표지점은 같으며 이  덕분에 일관된 디자인 룰을 적용하여 [광범위한 최신 문서](https://docs.djangoproject.com/en/2.2/)를 제공합니다.

- Versatile(다용도의)

  Django는 문서관리시스템과 Wiki부터 SNS, 뉴스에 이르기까지 다양한 종류의 웹 사이트를 빌드하는데 사용할 수  있고 사용되어 왔습니다. 또한 어떠한 클라이언트측 프레임워크와도 협업할 수 있고, 대부분의 형식(HTML, RSS 피드,  JSON, XML 등)으로 컨텐츠를 전송할 수 있습니다. 당신이 보고 있는 이 사이트도 Django 기반입니다!    내부적으로 Django는 당신이 원하는 대부분의 기능들(몇몇 유명한 데이터베이스들, 템플릿 엔진 등)을 제공하지만, 필요하다면 다른 컴포넌트들을 사용하기 위해 확장될 수 있습니다. 

- Secure(안전한)

  Django 는 개발자들이 웹사이트를 개발할 때 실수하기 쉽지만 고려해야하는 보안 문제에 대해서 많은 도움을  줍니다. 예를 들면, 장고는 유저의 계정과 비밀번호를 관리하는 안전한 방법을 제공합니다. 이 예에서 발생할 수 있는 개발자들의  실수로 세션의 정보를 보안에 취약한 위치에 있는 쿠키(해결책은 쿠키는 그저 key값을 가지도록 하는 반면 실제 데이터는 데이터  베이스에 저장하도록 하는 것입니다)에 넣는 실수를 하는 것입니다. 또 달리 쉽게할 수 있는 실수는 비밀번호를 hash를 통하지  않고 그대로 변형없이 저장하는 것이 있습니다.    *비밀번호에 사용되는 hash 는 [cryptographic hash function](https://en.wikipedia.org/wiki/Cryptographic_hash_function)에 의해 생성된 고정된 길이의 값을 가집니다. Django는 이렇게 변형되어 입력된 비밀번호가 유효한지 hash 함수를 통해 확인할 수 있습니다. 하지만 "단방향" 적인 함수의 특성상, 저장된 hash 값을 웹을 공격하는 사람들이 알아낸다고 하더라도 원본  비밀번호는 알아낼 수 없습니다.*    Django 는 SQL 인젝션, 크로스사이트 스크립팅, 크로스사이트 요청 위조 그리고 클릭 하이젝킹 (이러한 공격 방법에 대한 상세 정보는 [Website security](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps/Website_security)에서 볼 수 있습니다)과 같은 보안 취약점을 보완할 방법 기본적으로 제공합니다.

- Scalable(확장성 있는)

  Django는 컴포넌트 기반의 “[shared-nothing](https://en.wikipedia.org/wiki/Shared_nothing_architecture)” 아키텍쳐(각각의 아키텍쳐가 독립적이어서 필요하다면 교체 및 변경할 수 있는)를 사용합니다. 각 부분이 분명하게 분리되면 어떤  레벨에서든(예를 들면 캐싱 서버, 데이터베이스 서버, 혹은 어플리케이션 서버) 하드웨어를 추가해서 발생하는 늘어난 트래픽에 대응해 크기를 변경할 수 있게 됩니다. 사용자가 가장 많은 몇몇 사이트는 요구사항에 맞춰서 Django의  크기를 성공적으로 변경했습니다. (예를들면 Instagram, Disqus 등)

- Maintainable(유지보수가 쉬운)

  Django 코드는 유지보수가 쉽고 재사용하기 좋게끔 하는 디자인 원칙들과 패턴들을 이용하여 작성됩니다. 특히 Don't Repeat Yourself (DRY) 원칙을 적용해서 불필요한 중복이 없고 많은 양의 코드를 줄였습니다. 또한 Django는  관련된 기능들을 재사용 가능한 "applications"로 그룹화했고, 더 낮은 레벨에서 관련된 코드들을 모듈로 만들었습니다. ([Model View Controller (MVC)](https://developer.mozilla.org/en-US/docs/Web/Apps/Fundamentals/Modern_web_app_architecture/MVC_architecture) 패턴과 유사합니다).

- Portable(포터블한)

  장고는 파이썬으로 작성되어 있으며, 파이썬은 많은 플랫폼에서 작동합니다. 그것은 특정한 서버 플랫폼에 얽매이지 않는다는  것을 의미하며, 리눅스, 윈도우 그리고 맥 OS X 등등 다양한 운영체제에서 작동할 수 있다는 뜻입니다. 나아가, 장고는 많은 웹 호스팅 공급자들에 의해서 지원되고 있습니다. 그들은 장고 사이트의 호스팅과 관련해서 특정한 인프라와 문서를 제공합니다. 



## 탄생 배경은 어떻게 되나요?

장고는 신문 웹사이트를 제작 및 관리하던 어떤 웹 팀에 의해 2003년에서 2005년 사이에 처음으로 개발이 시작되었습니다. 여러  사이트들을 만들면서 웹 팀은 많은 공통 코드와 설계 패턴을 뽑아내어 재사용하였습니다. 이 공통 코드는 일반 웹 개발 프레임워크로  발전했습니다. 그리고 2005년 7월 "장고" 프로젝트로서 오픈소스화 되었죠.. 

장고는 2008년 9월 첫 번째 주요 릴리즈(1.0)에서부터 2017년의 최근 버전(2.0)까지 성장하고 발전했습니다.  장고는 각각의 버전에서 기능을 추가하고 버그를 수정했습니다. 새로운 유형의 데이터베이스, 탬플릿 엔진들 그리고 캐싱에 대한  지원에서부터 일반 보기 함수와 클래스들의 추가까지요(이를 통해 여러 프로그래밍 작업을 위해 개발자들이 작성해야 할 코드를  줄여줍니다). 

**Note**: 장고를 더 좋게 만들기 위해 어떤 작업이 이루어지고 있는지, 최근 버전에서 어떤 변경이 있었는지 확인하려면 장고 웹사이트의 [release notes](https://docs.djangoproject.com/en/1.10/releases/) 를 살펴보세요.

장고는 수많은 사용자와 기여자가 있는 협력적이고 번성하는 프로젝트입니다. 여전히 몇 가지 장고만의 특징이 있지만, 장고는 모든 유형의 웹사이트를 개발할 수 있는 다용도적인 웹 프레임워크로 발전했습니다.

## Django의 인기는 어떤가요?

사실 서버 측 프레임워크의 인기에 대해 쉽고 확정적인 측정값은 없습니다(다만 [Hot Frameworks](http://hotframeworks.com/) 와 같은 사이트는 각 플랫폼에 대해 GitHub 프로젝트와 StackOverflow 질문의 숫자를 세는 방법으로 인기에 대해  접근하려고 합니다). 장고가 인기없는 플랫폼의 문제를 피할 수 있을 만큼 "충분히 인기있는지"가 더 좋은 질문입니다. 장고가 계속 발전하나요? 도움이 필요할 때 받을 수 있나요? 장고를 배우면 돈을 받고 일할 기회가 생기나요?

장고를 사용하는 상위 사이트의 숫자, 장고 코드베이스에 기여하는 사람들의 숫자, 그리고 급여가 지불되거나 지불되지 않거나에 상관없이 지원을 제공하는 사람들의 숫자에 근거해서, 맞습니다. 장고는 인기있는 프레임워크 입니다!

장고를 사용하는 상위 사이트는 다음을 포함합니다 : Disqus, Instagram, Knight Foundation,  MacArthur Foundation, Mozilla, National Geographic, Open Knowledge  Foundation, Pinterest, and Open Stack (출처: [Django home page](https://www.djangoproject.com/)).

## Django는 독선적인가요?

많은 웹 프레임웍들이 흔히 스스로를 "독선적(opinionated)"이라거나 "관용적(unopinionated)"이라고 표현합니다.

독선적인 프레임웍들은 어떤 특정 작업을 다루는 "올바른 방법"에 대한 분명한 의견을 가지고 있습니다. 그것들은 대체로 특정  도메인(특정 타입의 문제를 해결하는)내에서 빠른 개발방법을 제시합니다. 어떤 작업에 대한 올바른 방법이란 보통 잘 알려져있고  문서화가 잘되어있기 때문입니다. 하지만 그것들은 주요 도메인을 벗어난 문제에 대해서는 그리 유연하지 못한 해결책을 제시할 수  있습니다. 또한 이용할수 있는 접근법이나 선택가능한 구성요소가 그리 많지 않을것입니다. 

반면에, 관용적인 프레임웍들은, 구성요소를 한데 붙여서 해결해야 한다거나 심지어 어떤 컴퍼넌트를 써야한다는 '올바른 방법'에 대한 제약이 거의 없다시피 합니다. 그것들은 개발자들이 특정 작업을 완수하는데에 가장 적절한 도구들을 이용하기 쉽게  만들어줍니다. 비록 당신 스스로가 그 컴퍼넌트들을 찾아야 한다는 수고는 해야하긴 하지만 말이죠.

Django는 "다소 독선적" 입니다. 그럼으로써 "양쪽 세계의 최선"의 결과를 전달합니다. Django는 대부분의 웹 개발 작업을 다루는 컴퍼넌트 세트와 그 세트를 이용하는 한, 두가지의 인기있는 방법을 제공합니다. 하지만 Django의 비결합 구조  (decoupled architecture) 덕분에 당신은 꽤 많은 옵션들중에서 다른 방법을 선택하거나 원한다면 완전히 새로운 방법을 만들어 낼 수도 있습니다.

## Django 코드는 어떻게 생겼나요?

전형적인 데이터 기반 웹 사이트에서 웹 어플리케이션은 웹 브라우저(또는 다른 클라이언트)로부터 HTTP 요청(Request)을 기다립니다. 요청을 받으면, 웹 어플리케이션은 URL과 `POST` 데이터 또는 `GET` 데이터의 정보에 기반하여 요구사항을 알아냅니다. 그 다음 무엇이 필요한 지에 따라, 데이터베이스로부터 정보를 읽거나 쓰고,  또는 필요한 다른 작업들을 수행할 것입니다. 그 다음 웹 어플리케이션은 웹 브라우저에 응답(Response)을 반환하는데, 주로  동적인 HTML 페이지를 생성하면서 응답합니다.

Django 웹 어플리케이션은 전형적으로 아래와 같이 분류된 파일들에 대해 일련의 단계를 수행하는 코드로 구성되어 있습니다:

![img](./images/basic-django.png)

- <span style="color:red">**URLs:**</span> 단일 함수를 통해 모든 URL 요청을 처리하는 것이 가능하지만, 분리된 뷰  함수를 작성하는 것이 각각의 리소스를 유지보수하기 훨씬 쉽습니다. URL mapper는 요청 URL을 기준으로 HTTP 요청을  적절한 뷰(view)로 보내주기 위해 사용됩니다. 또한 URL mapper는 URL에 나타나는 특정한 문자열이나 숫자의 패턴을  일치시켜 데이터로서 뷰 함수에 전달할 수 있습니다.
- <span style="color:red">**View:**</span> 뷰는 HTTP 요청을 수신하고 HTTP 응답을 반환하는 요청 처리 함수입니다. 뷰는 Model을 통해 요청을 충족시키는데 필요한 데이터에 접근합니다. 그리고 탬플릿에게 응답의 서식 설정을 맡깁니다.
- <span style="color:red">**Models:**</span> 모델은 응용프로그램의 데이터 구조를 정의하고 데이터베이스의 기록을 관리(추가, 수정, 삭제)하고 쿼리하는 방법을 제공하는 파이썬 객체입니다.
- <span style="color:red">**Templates:**</span> 탬플릿은 파일의 구조나 레이아웃을 정의하고(예: HTML 페이지),  실제 내용을 보여주는 데 사용되는 플레이스홀더를 가진 텍스트 파일입니다. 뷰는 HTML 탬플릿을 이용하여 동적으로 HTML  페이지를 만들고 모델에서 가져온 데이터로 채웁니다. 탬플릿으로 모든 파일의 구조를 정의할 수 있습니다.탬플릿이 꼭 HTML 타입일 필요는 없습니다!

**Note**: <span style="color:blue">장고는 이 구조를 "모델 뷰 템플릿(Model View Template)(MVT)" 아키텍처라고 부릅니다.</span> 이것은 더 익숙한 [Model View Controller](https://developer.mozilla.org/en-US/docs/Web/Apps/Fundamentals/Modern_web_app_architecture/MVC_architecture) 아키텍처와 많은 유사점을 가지고 있습니다.



아래 부문들은 장고 앱의 주요 부분들이 어떻게 보일지에 대한 단서를 보여줄 것입니다     (우리는 개발 환경을 설치한 이후에 세부적인 디테일에 대해 다룰겁니다).

### <span style="color:blue">요청을 알맞은 뷰로 전달 (urls.py)</span>

URL mapper는 보통 **urls.py**라는 이름의 파일에 저장되어 있습니다. 아래 예시에서 `urlpatterns` 맵퍼는 경로들(특정 URL 패턴들)과 해당하는 뷰 함수에 대한 맵핑 목록들을 정의합니다. 만약 지정된 URL 패턴과 일치하는 HTTP 요청이 수신된다면 관련된 view 함수가 요청을 전달합니다.

```
urlpatterns = [
    path('admin/', admin.site.urls),
    path('book/<int:id>/', views.book_detail, name='book_detail'),
    path('catalog/', include('catalog.urls')),
    re_path(r'^([0-9]+)/$', views.best),
]
```

`urlpatterns` 객체는 `path()`함수와 `re_path()` 함수를 항목으로 가지는 리스트입니다 (파이썬 리스트는 대괄호를 사용하여 구분되며, 항목은 쉼표로 분리되고 선택적으로 후행 쉼표가 있을 수 있습니다. 예시: [item1, item2, item3, ]).

두 메소드의 첫 번째 인수는 일치시킬 경로(패턴)입니다. `path()` 메소드는 꺾쇠 괄호(<, >)를 사용해서 인수를 정의합니다. 이 인수는 URL의 한 부분으로, 명명된 인수로 수집되어 뷰 함수로 보내집니다. `re_path()` 함수는 정규식이라는 유연한 패턴 매칭 접근을 사용합니다. 이것에 대해서는 나중에 다루도록 하겠습니다!

두 번째 인수는 패턴이 일치할 때 호출되는 다른 함수입니다. `views.book_detail`은 이 함수가 `book_detail()`이며 `views` 모듈 안에서 찾을 수 있다는 것을 나타냅니다 (즉, `views.py`라는 파일 안에서요).



### <span style="color:blue">요청 처리하기 (views.py)</span>

<span style="color:green">뷰들은 웹 클라이언트로부터 HTTP 요청을 수신하고 HTTP 응답을 되돌려주는 웹 어플리케이션의 심장입니다.</span> 그 사이에 그들은 데이터베이스에 접근하고 템플릿을 렌더링하기 위해 프레임워크의 다른 자원들을 정리합니다.

아래 예시는 이전 예시의 URL mapper가 불러올 수 있는 최소 뷰 함수 `index()`를 보여줍니다. 다른 모든 뷰 함수처럼 이 함수도 `HttpRequest` 객체를 인자(`request`)로 받고 `HttpResponse` 객체를 반환합니다. 이 예시에서는 요청에 관해서는 아무것도 하지 않고, 단순히 하드코딩된 문자열을 반환합니다. 요청에 관련해서는 이후 글에서 더 자세하게 다루겠습니다.

```
## filename: views.py (Django view functions)

from django.http import HttpResponse

def index(request):
    # Get an HttpRequest - the request parameter
    # perform operations using information from the request.
    # Return HttpResponse
    return HttpResponse('Hello from Django!')
```

**Note**: 파이썬에 관하여:

- [Python modules](https://docs.python.org/3/tutorial/modules.html)은 우리가 코드에 쓰고 싶을지도 모르며 분리된 파일로 저장되어 있는 함수의 "라이브러리" 입니다.
- `from django.http import HttpResponse`와 같은 방법으로 `django.http` 모듈에서 `HttpResponse` 객체만 가져와서 뷰에서 사용할 수 있습니다. 모듈에서 여럿, 아니면 전체 모듈을 임포트할 수 있는 몇 가지 방법이 있습니다.
- 함수들은 위에 보여진 것과 같이`def` 키워드로 정의됩니다. 함수의 이름 뒤 괄호 안에 는 명명된 인자들이 나열되어 있습니다. 전체 줄은 콜론으로 끝납니다. 그 아랫 줄이 모두 **들여쓰기 되어있다는 것**에 유의하세요. 들여쓰기는 코드 행이 특정한 블록 안에 있다는 것을 나타내기 때문에 중요합니다(필수적인 들여쓰기는 파이썬의 주요 기능이며, 파이썬 코드가 읽기 쉬운 이유 중 하나이기도 합니다).

뷰들은 보통 **views.py**.라는 파일 안에 저장되어 있습니다.



### <span style="color:blue">데이터 모델 정의하기 (models.py)</span>

<span style="color:green">장고 웹 어플리케이션은 모델(models)이라는 파이썬 객체를 통해 데이터를 관리하고 쿼리합니다. 모델은 필드 타입과 그들의 최대  크기, 기본 값들, 선택 목록 옵션, 문서의 도움말 텍스트, 폼(form)을 위한 labe text등을 포함하여 저장된 데이터의  구조를 정의합니다.</span> 모델의 정의는 기본 데이터베이스와 별개입니다. 본인의 프로젝트 설정의 일부로써 여러 모델 중 하나를 선택할 수 있습니다. 본인이 사용할 데이터베이스를 정했다면, 그것에 직접적으로 접근할 필요가 없습니다. 그저 모델 구조와 다른 코드들을  작성하면, 장고가 당신과 데이터베이스가 소통하는 데 필요한 모든 더러운 작업들을 처리합니다.

아래 코드는 `Team` 객체를 위한 아주 간단한 장고 모델을 보여줍니다. `Team` 객체는 장고 클래스`models.Model`에서 파생되었습니다. 이 객체는 팀 이름과 팀 레벨을 캐릭터 필드로 정의하고 각각의 기록에 저장될 최대 캐릭터 숫자를 정합니다. `team_level`은 랜덤으로 값이 선정되기 때문에, 우리는 이를 choice 필드로 정의하며, choices들 간에 선택된 값이 보여지고 디폴트 값에 따른 데이터가 저장되도록 합니다. 

```
# filename: models.py

from django.db import models

class Team(models.Model):
    team_name = models.CharField(max_length=40)

    TEAM_LEVELS = (
        ('U09', 'Under 09s'),
        ('U10', 'Under 10s'),
        ('U11', 'Under 11s'),
        ...  #list other team levels
    )
    team_level = models.CharField(max_length=3, choices=TEAM_LEVELS, default='U11')
```

**참고 : 파이썬에 대해**

파이썬은 코드를 객체로 구성하는 프로그래밍 스타일인 "객체 지향 프로그래밍"을 지원합니다. 여기에는 관련 데이터 및 해당  데이터를 조작하기위한 함수가 포함됩니다. 객체는 다른 객체로부터 상속, 확장, 파생할 수 있어 관련 객체 간의 공통 동작을 공유 할 수 있습니다. 파이썬에서는 키워드 클래스를 사용하여 객체의 "청사진"을 정의합니다. 클래스의 모델을 기반으로 객체 유형의 여러  특정 인스턴스를 만들 수 있습니다.

예를 들어 여기 Model 클래스에서 파생된 Team 클래스가 있습니다. 이는 모델이며 모델의 모든 방법을 포함할 것이지만  고유한 기능도 제공 할 수 있습니다. 이 모델에서는 데이터베이스가 데이터를 저장하는데 필요한 필드를 정의하여 특정 이름을  지정합니다. 장고는 필드 이름을 포함한 이러한 정의를 사용하여 기본 데이터베이스를 만듭니다.



### <span style="color:blue">데이터 쿼리하기 (views.py)</span>

장고 모델은 데이터베이스를 간단히 탐색하기 위한 쿼리 API를 제공합니다. 이 API는 다양한 조건을 통해 수 많은 필드를 빠르게  매칭시킵니다. (**예를 들어, 정확하게 일치(exact), 대소문자 구분없이(case-insensitive), 해당 숫자보다  큰(greater than) 등이 있습니다.**) 그리고 복잡한 쿼리문을 지원합니다. 예를 들어, 당신은 팀의 이름이 "Fr"로  시작하거나 "al"로 끝나는 U11 레벨의 팀만을 지정할 수 있습니다.

굵게 표시된 줄은 모델 쿼리 API를 사용하여 `team_level` 필드의 텍트스가 정확히 'U09'인 모든 레코드를 필터링하는 방법을 보여줍니다 (이 기준이 필드 이름의 인수로 `filter()` 함수에 전달되는 방법에 유의하십시오. 일치 유형은 **`team_level__exact`**와 같이 이중 밑줄로 구분됩니다).

```
## filename: views.py

from django.shortcuts import render
from .models import Team

def index(request):
    list_teams = Team.objects.filter(team_level__exact="U09")
    context = {'youngest_teams': list_teams}
    return render(request, '/best/index.html', context)
```



이 함수는 `render()` 함수를 사용하여 브라우저로 다시 전송되는 `HttpResponse`를 만듭니다. 지정된 HTML 템플릿과 템플릿에 삽입할 일부 데이터( "context"라는 변수에 제공)를 결합하여 HTML 파일을 생성합니다. 다음 섹션에서는 템플릿을 생성하기 위해 템플릿에 데이터를 삽입하는 방법을 보여줍니다.



### <span style="color:blue">데이터 렌더링 (HTML 템플릿)</span>

템플릿 시스템을 사용하면 페이지가 생성될 때 채워질 데이터에 자리 표시자를 사용하여 출력 문서의 구조를 지정할 수 있습니다. 템플릿은  종종 HTML을 만드는 데 사용되지만 다른 유형의 문서를 만들 수도 있습니다. 장고는 기본 템플릿 시스템과 Jinja2라는  인기있는 파이썬 라이브러리를 모두 지원합니다 (필요한 경우 다른 시스템을 지원하도록 만들 수도 있음).

아래 코드는 이전 섹션의 `render()` 함수가 호출한 HTML 템플릿의 모양을 보여줍니다. 이 템플릿은 렌더링될 때 (위의 `render()` 함수 내의 컨텍스트 변수에 포함 된) "youngest_teams"라는 목록 변수에 액세스할 수 있다는 가정하에 작성되었습니다. HTML 스켈레톤에는 먼저 youngest_teams 변수가 있는지 확인한 후 `for` 루프에서 반복하는 표현식이 있습니다. 각 반복에서 템플리트는 각 팀의 `team_name` 값을 `<li>` 태그의 값으로 표시합니다.

```
## filename: best/templates/best/index.html

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Home page</title>
</head>
<body>
  {% if youngest_teams %}
    <ul>
      {% for team in youngest_teams %}
        <li>{{ team.team_name }}</li>
      {% endfor %}
    </ul>
  {% else %}
    <p>No teams are available.</p>
  {% endif %}
</body>
</html>
```



## <span style="color:blue">또 무엇을 할수 있나요?</span>

이전 섹션에서는 거의 모든 웹 응용 프로그램에서 사용할 주요 기능인 URL 매핑, 뷰, 모델 및 템플릿을 보여줍니다. 추가로 장고가 제공하는 기능들은 다음과 같습니다.

- <span style="color:blue">**양식**</span> : HTML 양식은 서버에서 처리할 사용자 데이터를 수집하는 데 사용됩니다. 장고는 양식 작성, 유효성 검사 및 처리를 단순화합니다.
- <span style="color:blue">**사용자 인증 및 권한**</span> : 장고에는 보안을 염두에 두고 구축된 강력한 사용자 인증 및 권한 시스템이 포함되어 있습니다.
- <span style="color:blue">**캐싱**</span> : 컨텐츠를 동적으로 작성하는 것은 정적 컨텐츠를 제공하는 것 보다 많은 연산을  필요로 하기 때문에 느립니다. 장고는 유연한 캐싱을 제공하여 렌더링된 페이지 전체 또는 일부를 저장하여 필요할 때를 제외하고 다시 렌더링하지 않도록 할 수 있습니다.
- <span style="color:blue">**관리 사이트**</span> : 기본 스켈레톤을 사용하여 앱을 만들 때 장고 관리 사이트가 기본적으로 포함됩니다. 사이트 관리자가 사이트의 모든 데이터 모델을 작성, 편집 및 볼 수있는 관리 페이지를 쉽게 제공할 수 있습니다.
- <span style="color:blue">**데이터 직렬화**</span> : 장고를 사용하면 데이터를 XML 또는 JSON으로 직렬화하고 제공할 수  있습니다. 이 기능은 웹 서비스 (다른 응용 프로그램이나 사이트에서 사용하기 위해 순수하게 데이터를 제공하고 자체를 표시하지 않는 웹 사이트)를 만들거나 클라이언트 쪽 코드가 모든 데이터 렝더링을 처리하는 웹 사이트를 만들 때 유용할 수 있습니다. 



## 요약

축하합니다. 이제 장고 여행의 첫발을 떼셨군요! 이제 우리는 장고의 주요 이점과 역사를 조금 알게됐고 장고 응용프로그램의 주요한 부분을 대략  이해했습니다. 또한 목록, 함수 및 클래스 구문을 포함하여 파이썬 프로그래밍 언어에 대해 몇 가지 사실을 배워야합니다.

위의 실제 장고 코드를 이미 보았지만 클라이언트 측 코드와 달리 실행하기 위해서는 개발 환경을 설정해야합니다. 그것이 우리의 다음 단계입니다.



# Django 개발 환경 세팅하기

link : https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/development_environment



이제 장고가 무엇인지 알았으니, 윈도우, 리눅스(우분투), 맥 OS X에서 어떻게 장고 개발환경을 세팅하는지, 설치 후에는 어떻게 테스트하는지 살펴보겠습니다. 즉 이 문서를 통해서는 사용하고 있는 운영체제가  무엇인지와 상관없이 장고 어플리케이션 개발을 시작하기 위해 필요한 것들을 배우게 됩니다.

| 미리 필요한 것: | 터미널 또는 커맨드 창을 열 수 있어야 합니다. 또, 자신이 사용하는 PC의 운영체제에 따라 PC에 소프트웨어 패키지를 설치할 수 있어야 합니다. |
| --------------- | ------------------------------------------------------------ |
| 목표:           | 장고가 컴퓨터에서 실행될 수 있도록 개발 환경을 세팅합니다.   |

## 장고 개발 환경 개요

장고는 개발 환경을 세팅하고 웹 어플리케이션을 개발하는 것이 매우 쉽습니다. 이 섹션에서는 개발 환경이 제공하는 것들과, 개발 환경  세팅 시 옵션 사항을 알아봅니다. 또 우분투, 맥 OS X, 윈도우에서 장고 개발 환경을 설치하는 방법과 설치 후 테스트하는  방법을 설명합니다.

### 장고 개발 환경이란?

장고 개발 환경이란, 장고를 로컬 컴퓨터에 설치하여 장고 어플리케이션을 개발, 실행, 테스트할 수 있는 환경을 말합니다. 로컬  컴퓨터는 자신이 어플리케이션을 개발하는데 사용하는 컴퓨터입니다. 어플리케이션을 실제 배포하기 전에 로컬 컴퓨터 위에서  어플리케이션을 실행 및 테스트할 수 있습니다.

장고 자체가 제공하는 주요 툴에는 장고 프로젝트를 생성하고 작업하기 위한 파이썬 스크립트들과 심플한 개발용 웹 서버가  있습니다. 이 개발용 웹 서버로 우리는 로컬 컴퓨터에서 개발한 장고 어플리케이션을 같은 로컬 컴퓨터에서 테스트해 볼 수  있습니다. 예를 들면, 자신의 PC에서 개발한 장고 웹 어플리케이션을 크롬 브라우저와 같은 웹 브라우저 상에서 실행하고 테스트해볼 수 있습니다.

앞서 설명한 것 외에도 장고 개발 환경은 여러 툴을 제공합니다. 코드 작성을 돕는 텍스트 에디터와 IDE, 소스코드의 버전을 안전하게 관리하기 위한 Git과 같은 소스 관리 도구와 같은 것들이 있습니다. 그러나 이것들은 여기서는 다루지 않습니다. 또  여기서는 미리 텍스트 에디터를 설치했다고 가정할 것입니다. 그러므로 텍스트 에디터를 아직 설치하지 않았다면 설치해주세요. 자주  사용하는 텍스트 에디터로는 [Sublime Text 3](https://www.sublimetext.com/3), [Gedit](https://wiki.gnome.org/Apps/Gedit#Download), [Atom](https://atom.io/) 등이 있습니다.

### 장고 설치 옵션

장고는 설치 및 구성에서 매우 유연합니다.

장고는 다음 사항이 가능합니다:

- 여러 운영 체제에서 설치가 가능합니다.
- 소스에서, 파이썬 패키지 인덱스(PyPi)에서, 그리고 많은 경우 호스트 컴퓨터의 패키지 매니저 어플리케이션에서 설치가 가능합니다.
- 별도로 설치 및 구성되어있어야 하는 여러가지 데이터베이스 중 하나를 사용하도록 설정할 수 있습니다.
- 메인 시스템의 파이썬 환경 또는 별도의 파이썬 가상 환경에서 실행됩니다.

이러한 각각의 옵션들은 모두 조금씩 다른 구성과 설치가 필요합니다. 이어지는 세부 내용에서 몇 가지 선택 사항을 설명합니다. 이하 글에서는 몇 가지 운영체제에서 장고를 설치 및 설정하는 방법을 보여주고, 나머지 튜토리얼에서는 모두 이 설정을 가정해서  진행됩니다.

**주의**: 공식 장고 문서에서 다른 설치 옵션을 찾을 수 있습니다. 링크 : [appropriate documents below](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/development_environment#furtherreading).

#### 어떤 운영체제가 지원되나요?

장고는 파이썬 3 프로그래밍 언어를 실행할 수 있는 거의 모든 기계에서 실행될 수 있습니다: 윈도우, 맥 OS X, 리눅스/유닉스, 솔라리스 등등. 거의 모든 컴퓨터가 개발 중에 장고를 실행할 수 있는 성능을 갖고 있습니다.

이 글에서는 윈도우, 맥 OS X, 리눅스/유닉스에 관해 설명하도록 하겠습니다.

#### 파이썬은 어느 버전을 사용해야 할까?

가능한 최신 버전을 사용할 것을 권장합니다. 이 글을 작성할 때 가장 최신 버전은 파이썬 3.7입니다.

필요에 따라 Python 3.4 혹은 그 이후의 버전이 사용될 수 있습니다. (파이썬 3.4는 차후에 지원이 안될 수도 있습니다)

**주의**: 파이썬 2.7은 장고 2.0에서 사용할 수 없습니다. (장고 1.11.x 버전에서 마지막으로 파이썬 2.7을 지원했습니다)

#### 장고는 어디서 다운로드할 수 있나요?

장고를 다운로드하는 세가지 방법 :

- pip 도구를 이용한 PyPi(Python Package Repository)에서 설치. 이 방법이 장고의 최신 버전을 받을 수 있는 최적의 방법입니다.
- 본인 컴퓨터의 패키지 매니저에 있는 버전을 사용하세요. 운영체제와 함께 제공되는 장고는 친숙한 설치 방법을 제공합니다.  다만 이것은 상당히 오래된 버전일 것이며, (아마 그렇게 원하지 않을) 시스템 파이썬 환경에만 설치될 수 있다는 것에 유의하세요.
- 소스에서 설치하기. 소스에서 장고의 최신버전을 다운로드하여 설치할 수 있습니다. 초심자에게는 추천하지 않지만, 당신이 장고에게 기여할 수 있는 준비가 됐다면 필요합니다.

아래 글은 최신의 안정된 버전을 얻기 위해 PyPi에서 장고를 설치하는 방법을 보여줍니다.

#### 어떤 Database를 써야 하나요?

장고는 네 가지 메인 데이터베이스(PostgreSQL, MySQL, Oracle 그리고 SQLite)를 지원합니다. 그리고  다른 인기있는 SQL과 NoSQL 데이터베이스들을 다양한 레벨로 지원하는 커뮤니티 라이브러리가 있습니다. 우리는 생산과 개발에  동일한 데이터베이스를 선택하는 것을 추천합니다(장고는 ORM(Object-Relational Mapper)을 사용해  데이터베이스간의 차이 대부분을 추상화하긴 하지만, 아직 피하는게 나은 [잠재적 문제들](https://docs.djangoproject.com/en/2.0/ref/databases/)이 있습니다.

이 글에서(그리고 이 모듈의 거의 모든 부분에서) 우리는 데이터를 파일로 저장하는 SQLite 데이터베이스를 사용할 것  입니다. SQLite는 가벼운 데이터베이스로 사용하기에 적합하며 높은 수준의 동시성을 지원하지 않습니다. 그렇지만 주로 읽기  전용인 응용 프로그램을 위해서는 아주 좋은 선택입니다.

**주의**: 장고는 *django-admin과 같이 웹사이트를 만드는 표준 도구를 사용하면* SQLite가 기본 값으로 설정되어 있습니다. 이는 추가적인 설정이 필요하지 않으므로 시작하기에 좋습니다. 

#### 시스템 전체에 설치할까요, 파이썬 가상환경에 설치할까요?

파이썬 3를 설치하면 모든 파이썬 3 코드가 공유하는 하나의 글로벌 환경이 만들어집니다. 그 환경에 원하는 어떤 파이썬 패키지라도 설치할 수 있지만, 각 패키지의 하나의 버전만 설치할 수 있습니다.

**주의**: 글로벌 환경에 설치된 파이썬 응용 프로그램들은 서로 충돌할 가능성이 있습니다. (예: 같은 패키지의 다른 버전일 경우)

만약 장고를 기본/전역 환경에 설치한다면 컴퓨터에서 하나의 장고 버전만을 대상으로 지정할 수 있습니다. 이것은 당신이 옛날  버전으로 작동하는 웹사이트를 관리하면서 최신 버전의 장고를 이용한 새로운 웹사이트를 만들고 싶을 때 문제가 됩니다.

결과적으로, 경험있는 파이썬/장고 개발자들은 일반적으로 독립적인 파이썬 환경에서 파이썬 앱들을 실행합니다. 이것은 여러 다른 장고 환경이 하나의 컴퓨터에서 작동 가능하게 합니다. 장고 개발팀에서도 당신이 파이썬 가상 환경을 사용하는 것을 추천합니다!

이 모듈은 당신이 장고를 가상 환경에 설치했다고 가정합니다. 아래에서 어떻게 설치하는지 알려드리겠습니다.

## 파이썬 3 설치

장고를 사용하기 위해서 파이썬을 설치해야 합니다. 파이썬 3을 사용하는 경우 장고와 다른 파이썬 앱에서 사용하는 파이썬 패키지 및 라이브러리를 설치, 업데이트, 제거하는 데 사용되는 [pip3 (Python Package Index)](https://pypi.org/) 도구도 필요합니다.

이번 섹션에서는 현재 당신의 파이썬 버전이 무엇인지 확인하고, 필요에 따라 운영체제(Ubuntu Linux 16.04, macOS X, and Windows 10)별로 새로운 버전 설치 방법을 간단하게 설명합니다.

**주의**: 플랫폼에 따라 운영 체제의 자체 패키지 관리자 또는 다른 메커니즘을 통해 Python / pip를 설치할 수도 있습니다. 대부분의 플랫폼의 경우 https://www.python.org/downloads/에서 필요한 설치 파일을 다운로드하여 적절한 플랫폼 별 방법을 사용하여 설치할 수 있습니다.

### 우분투 16.04

우분투 리눅스 18.04 LTS는 파이썬 3.6.6을 기본적으로 포함하고 있습니다. bash 터미널에서 아래 코드를 실행하여 이것을 확인할 수 있습니다.:

```
python3 -V
 Python 3.6.6
```

그러나 파이썬3의 패키지 설치를 위한 Python Package Index tool(장고를 포함해)는 기본적으로 설치되어있지 않습니다. bash 터미널에서 아래 코드를 사용하여 pip3를 설치할 수 있습니다:

```
sudo apt install python3-pip
```

### 맥OS X

맥OS X "엘 캐피탄" 이후의 최신 버전에서는 파이썬3를 포함하고 있지 않습니다. bash 터미널에서 아래 코드를 실행해서 확인할 수 있습니다.:

```
python3 -V
 -bash: python3: command not found
```

당신은[ python.org](https://www.python.org/)에서 파이썬3를(pip3 도구도 함께) 쉽게 설치할 수 있습니다:

1. 필요한 설치 파일을 다운로드하세요:  
   1. https://www.python.org/downloads/ 로 가세요.
   2. **Download Python 3.7.0** 버튼을 선택하세요 (정확한 마이너 버전 숫자는 다를 수도 있습니다).
2. 파인더를 통해 파일을 찾아, 패키지 파일을 더블클릭 하세요. 그리고선 설치 과정을 따릅니다.

이제 아래와 같이 파이썬3의 성공적인 설치를 확인할 수 있습니다:

```
python3 -V
 Python 3.7.0
```

가능한 패키지들의 목록을 불러옴으로써 pip3가 설치된 것을 확인할 수 있습니다:

```
pip3 list
```

### 윈도우 10

윈도우는 파이썬을 기본적으로 포함하고 있지 않지만, [python.org](https://www.python.org/)에서(pip3 도구와 함께) 쉽게 설치할 수 있습니다:

1. 필요한 설치 파일을 다운로드하세요:  
   1. https://www.python.org/downloads/ 로 가세요
   2. **Download Python 3.7.1** 버튼을 선택하세요 (정확한 마이너 버전 숫자는 다를 수도 있습니다).
2. 다운로드된 파일을 더블클릭해서 파이썬을 설치하세요.

명령 프롬프트에서 아래 텍스트를 입력해서 파이썬3가 설치된 것을 확인할 수 있습니다:

```
py -3 -V
 Python 3.7.1
```

윈도우 버전의 설치 파일은 pip3(파이썬 패키지 관리자)가 기본적으로 포함되어 있습니다. 아래 코드로 설치된 패키지 목록을 볼 수 있습니다.

```
pip3 list
```

**주의**: 설치 파일은 위 코드들이 실행되기 위한 모든 것을 설치해줄 것입니다. 만약 파이썬을  찾을 수 없다는 메시지가 나오면, 파이썬을 당신의 시스템 경로에 추가하는 것을 깜빡했을 수가 있습니다. 당신은 설치 파일을 다시  실행해서 'Modify'를 선택 후 두 번째 페이지에 있는 "Add Python to environment variables"  박스에 체크함으로써 시스템 경로에 파이썬을 추가할 수 있습니다.



## 파이썬 가상 환경에서 장고 사용하기

우리가 가상 환경을 만드는 데 사용할 라이브러리들은 [virtualenvwrapper](https://virtualenvwrapper.readthedocs.io/en/latest/index.html) (리눅스와 맥 OS X) 그리고 [virtualenvwrapper-win](https://pypi.python.org/pypi/virtualenvwrapper-win) (윈도우)입니다. 둘 다 [virtualenv](https://developer.mozilla.org/en-US/docs/Python/Virtualenv) 도구를 사용하죠. wrapper 도구는 모든 플랫폼의 인터페이스를 관리하기 위한 일관적인 인터페이스를 생성합니다.

### 가상 환경 소프트웨어 설치하기

#### 우분투 가상 환경 셋업

파이썬과 pip를 설치한 후에 (virtualenv를 포함하는)virtualenvwrapper를 설치할 수 있습니다. 공식 설치 가이드는 [여기](http://virtualenvwrapper.readthedocs.io/en/latest/install.html)엣서 찾을 수 있습니다. 아니면 아래 설명을 따라오세요.

pip3를 사용해서 그 도구를 설치하세요:

```
sudo pip3 install virtualenvwrapper
```

그리고 당신의 shell 스타트업 파일(이것은 당신의 홈 디렉토리에 있는 숨겨진 **.bashrc** 파일 이름입니다)의 끝에 아래 코드를 추가하세요. 이 코드들은 가상 환경이 활동할 위치, 당신의 개발 프로젝트 디렉토리 위치, 그리고 이 패키지와 함께 설치된 스크립트의 위치를 설정합니다:

```
export WORKON_HOME=$HOME/.virtualenvs
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3
export VIRTUALENVWRAPPER_VIRTUALENV_ARGS=' -p /usr/bin/python3 '
export PROJECT_HOME=$HOME/Devel
source /usr/local/bin/virtualenvwrapper.sh
```

**Note**:`VIRTUALENVWRAPPER_PYTHON` 와 `VIRTUALENVWRAPPER_VIRTUALENV_ARGS `변수는 파이썬3의 일반적인 설치 위치를 가리킵니다. 그리고 `source /usr/local/bin/virtualenvwrapper.sh` 는 `virtualenvwrapper.sh` 스크립트의 일반적인 위치를 가리킵니다. 만약 테스트 중에 virtualenv가 작동하지 않는다면, 확인해야 할 일 중 하나는 파이썬과 스크립트가 알맞은 위치에 있는지 입니다(그리고 스타트업 파일을 그에 맞게 바꾸세요).

  `which virtualenvwrapper.sh` 와 `which python3`커맨드를 사용해서 당신의 시스템에 알맞은 위치를 찾을 수 있습니다.

그리고 아래 코드를 터미널에서 실행하여 스타트업 파일을 다시 불러오세요:

```
source ~/.bashrc
```

이 시점에서 아래와 같이 한 다발의 스크립트가 실행되는 걸 볼 수 있습니다 :

```
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/premkproject
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/postmkproject
...
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/preactivate
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/postactivate
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/get_env_details
```

이제 `mkvirtualenv`명령으로 새로운 가상 환경을 생성할 수 있습니다.

#### 맥OS X 가상 환경 설정

맥OS X에서 *virtualenvwrapper*를 설정하는 것은 우분트와 거의 다를바가 없습니다. (다시 말하지만, [공식 설치 가이드](http://virtualenvwrapper.readthedocs.io/en/latest/install.html) 를 따라하거나 아래 내용을 따라해도 됩니다).

아래와 같이 pip를 이용해 *virtualenvwrapper* (와 동봉된 *virtualenv*)를 설치하세요.

```
sudo pip3 install virtualenvwrapper
```

그리고 쉘 시작 파일(shell startup file)의 맨 아랫쪽에 아래 코드를 추가하세요.

```
export WORKON_HOME=$HOME/.virtualenvs
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3
export PROJECT_HOME=$HOME/Devel
source /usr/local/bin/virtualenvwrapper.sh
```

**참고사항**: `VIRTUALENVWRAPPER_PYTHON` 변수는 파이썬3의 일반적인 설치 위치를 가리키며, `source /usr/local/bin/virtualenvwrapper.sh`는  `virtualenvwrapper.sh`스크립트의 일반적인 위치를 가리킵니다. 당신이 테스트할 때 *virtualenv* 가 동작하지 않는다면, 한가지 체크해볼 것은 파이썬과 해당 스크립트가 위에서 가리키는 위치에 있는지 여부입니다( 다르다면 startup 파일을 적절하게 수정해야 합니다).

예를 들어,  맥OS상의 어떤 시스템의 설치 테스트에서는 startup 파일에 아래와 같은 코드를 추가할 필요가 있었습니다 :

```
export WORKON_HOME=$HOME/.virtualenvs
export VIRTUALENVWRAPPER_PYTHON=/Library/Frameworks/Python.framework/Versions/3.7/bin/python3
export PROJECT_HOME=$HOME/Devel
source /Library/Frameworks/Python.framework/Versions/3.7/bin/virtualenvwrapper.sh
```

`which virtualenvwrapper.sh`와 `which python3`.명령을 이용하여 당신 시스템 환경에서의 정확한 위치를 찾을 수 있습니다.

이 코드들은 우분트에서도 같은 코드이지만, startup 파일은 당신의 홈 디렉토리에 위치하며 다른 이름 **.bash_profile**을 가진 숨겨진 파일입니다.

**Note**: 파인더(finder)에서**.bash-profile** 파일을 찾을 수 없다면, 터미널에서 nano를 이용해 이 파일을 열 수 있습니다. 

터미널 명령은 대체로 아래와 같습니다 :

```
cd ~  # Navigate to my home directory
ls -la #List the content of the directory. YOu should see .bash_profile
nano .bash_profile # Open the file in the nano text editor, within the terminal
# Scroll to the end of the file, and copy in the lines above
# Use Ctrl+X to exit nano, Choose Y to save the file.
```

그 다음엔, 터미널에서 아래 명령을 호출하여 startup 파일을 재실행 하세요 :

```
source ~/.bash_profile
```

이 시점에서 한 다발의 스크립트가 실행되는 걸 볼 수 있습니다( Ubuntu 설치때와 같은 스크립트 입니다). 이제 `mkvirtualenv` 명령으로 새로운 가상환경을 생성할 수 있어야 합니다.

#### 윈도우 10 가상 환경 설정

[virtualenvwrapper-win](https://pypi.python.org/pypi/virtualenvwrapper-win) 를 설치하는것이 virtualenvwrapper를 설치하는 것보다 훨씬 쉬운데, 가상 환경 정보를 어디에 저장해야할지 설정할 필요가 없기 때문입니다 (기본값이 있습니다). 아래 명령을 명령 프롬프트에서 실행하는 것이 당신이 해야할 전부입니다:

```
pip3 install virtualenvwrapper-win
```

이제 `mkvirtualenv` 명령으로 새로운 가상환경을 생성할 수 있습니다.

### 가상 환경 생성하기

일단 virtualenvwrapper 나 virtualenvwrapper-win 을 설치했다면 가상 환경으로 작업하는 것은 모든 플랫폼별에서 차이가 거의 없습니다.

이제 mkvirtualenv 명령으로 새로운 가상 환경을 생성할 수 있습니다. 이 명령이 수행될 때 환경이 설정되는 과정을  보게됩니다( 플랫폼에 따라 보이는 것이 다릅니다). 명령이 완료되면 새로운 가상환경이 활성화 됩니다 — 괄호내에 있는 가상환경의  이름으로 프롬프트가 시작하는 것으로 알 수 있습니다 (아래는 우분투의 경우인데, 마지막 라인은 윈도우/맥OS 도 유사합니다).

```
$ mkvirtualenv my_django_environment

Running virtualenv with interpreter /usr/bin/python3
...
virtualenvwrapper.user_scripts creating /home/ubuntu/.virtualenvs/t_env7/bin/get_env_details
(my_django_environment) ubuntu@ubuntu:~$
```

이제 당신은 가상환경내에 있으며 장고를 설치하고 개발을 시작할 수 있습니다.

**주의**: 이 시점부터 이 기사 ( 정확히는 이 모듈)에서 실행되는 모든 명령은 위에서 우리가 설정한 파이썬 가상환경내에서 실행되는 것으로 간주합니다.

### 가상 환경 사용하기

당신이 알아야 하는 명령이 몇 가지 더 있다.(도구 문서에는 더 많이 있긴하지만, 아래 명령이 꾸준히 사용하게될 명령들이다):

- `deactivate` — 활성화된 파이썬 가상 환경을 비활성화한다
- `workon` — 사용가능한 가상 환경 목록을 보여준다
- `workon name_of_environment` — 특정 파이썬 가상 환경을 활성화한다
- `rmvirtualenv name_of_environment` — 특정 환경을 제거한다.

## 장고 설치하기

일단 가상 환경을 하나 생성하고, 진입하기 위해 `workon` 을 호출하면 장고를 설치하기 위해 pip3를 사용할 수 있다. 

```
pip3 install django
```

아래 명령을 실행하여 장고가 설치되었는지 테스트할 수 있다 (이 명령은 단지 파이썬이 django 모듈을 찾을 수 있는지 테스트한다):

```
# Linux/macOS X
python3 -m django --version
 2.0

# Windows
py -3 -m django --version
 2.0
```

**주의**: 위의 윈도우 명령이 django 모듈이 존재하는지 보여주지 않으면, 아래 명령을 시도해보세요:

```
py -m django --version
```

당신의 설치 방법에 따라 변할수도 있긴 하지만, 윈도우에서는 파이썬 3 스트립트는 `py -3`을 명령앞에 붙여야 실행됩니다. 명령 실행에 문제가 있으면 `-3`옵션을 빼 보세요. 리눅스/맥OS X 에서는 `python3`명령입니다.

**중요사항**: 이 **모듈** 의 나머지부분에서는 파이썬 3를 실행하는 명령으로 리눅스 명령 (`python3`) 을 사용합니다. 당신이 윈도우에서 진행중이라면 단지 명령 앞부분을 `py -3`로 변경하면 됩니다.

## 설치한 것 확인하기

위 테스트는 성공해도 그리 재미있는 작업은 아니었습니다. 더 흥미있는 테스트는 기초적인 프로젝트를 생성해서 동작하는것을 보는것입니다. 이것을 해보기 위해, 명령 프롬프트/터미널에서 장고 앱을 저장할 부모폴더로 이동하세요. 테스트 사이트용 폴더를 생성하고 그 폴더안으로 이동하세요.

```
mkdir django_test
cd django_test
```

그 다음 아래와 같이 **django-admin** 도구를 이용해 "*mytestsite*" 라는 사이트의 기본 토대를 생성할 수 있습니다. 사이트를 생성한 이후 그 폴더로 가면 해당 프로젝트를 관리할수 있는 **manage.py** 라는 이름의 메인 스크립트파일을 발견할 것입니다.

```
django-admin startproject mytestsite
cd mytestsite
```

이 폴더내에서 `runserver` 명령과 **manage.py** 를 이용하여 아래와 같이 개발용 웹 서버를 실행할 수 있습니다.

```
$ python3 manage.py runserver
Performing system checks...

System check identified no issues (0 silenced).

You have 15 unapplied migration(s). Your project may not work properly until you apply the migrations for app(s): admin, auth, contenttypes, sessions.
Run 'python manage.py migrate' to apply them.

October 26, 2018 - 07:06:30
Django version 2.1.2, using settings 'mytestsite.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```

**주의**: 위 명령은 Linux/macOS X 명령을 보여준다. 지금 시점에서는 "15 unapplied migration(s)" 의 경고 문구는 무시해도 됩니다 !

일단 서버가 실행중이면 당신 시스템의 웹 브라우저로 아래 URL에 가서 만들어진 사이트를 볼 수 있습니다: `http://127.0.0.1:8000/`. 방문한 사이트에서 아래와 같은 모습이 보여야 합니다:

![Django Skeleton App Homepage](./images/Django_Skeleton_Website_Homepage.png)



## [요약](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/development_environment#요약)

당신은 이제 장고 개발 환경을 구축하고 당신의 컴퓨터에서 실행중입니다.

마지막 확인 섹션에서 `django-admin startproject` 명령을 이용해 어떻게 새로운 장고 웹사이트를 생성할 수 있는지 간단하게 확인했습니다. 그리고 개발용 웹 서버를 이용해 당신의 브라우저로 웹사이트를 실행했습니다(`python3 manage.py runserver`). 다음 튜토리얼에서는 간단하지만 완전한 웹 어플리케이션을 구축하는 이 과정을 좀 더 상세히 설명합니다.

## [더불어 보기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/development_environment#더불어_보기)

- [빠른 설치 가이드](https://docs.djangoproject.com/en/2.0/intro/install/) (Django 문서)
- [Django 설치하는 법 — 완벽 가이드](https://docs.djangoproject.com/en/2.0/topics/install/) (Django 문서) - Django를 제거하는 방법도 포함됨
- [윈도우에 장고 설치하기](https://docs.djangoproject.com/en/2.0/howto/windows/) (Django 문서)





# Django 지역 도서관 웹사이트

출처: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Tutorial_local_library_website



# Django 튜토리얼: 지역 도서관 웹사이트



이번은 실전 튜토리얼 시리즈의 첫번째 파트로서 당신이 배울 내용을 설명하고, 이어지는 튜토리얼 시리즈에서 개발하고 개선시켜볼 "지역 도서관" 예제 웹사이트에 대한 개요를 제공한다.

| 사전학습: | [Django 소개](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Introduction) 파트를 읽으세요. 이어지는 파트를 위해서는 [Django개발 환경 설치하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/development_environment) 파트도 필요합니다. |
| :-------- | ------------------------------------------------------------ |
| 학습목표: | 이 튜토리얼에서 사용될 예제 어플리케이션을 소개하고, 여기서 논의될 토픽들의 범위에 대해 이해하기 |



## 개요

MDN "지역 도서관" 장고 튜토리얼에서 오신 것을 환영합니다. 여기에서는 , "지역 도서관" 카탈로그를 운영하는데 사용될 수 있는 웹사이트를 개발할 것이다. 

이 튜토리얼 시리즈는 아래 내용을 다룬다:

- 웹 애플리케이션의 골격을 만들기 위해 장고 도구 사용하기
- 개발 서버 시작하기와 끝내기
- 애플리케이션 데이터의 틀이 되는 모델 생성하기
- 데이터를 입력하기 위해서 장고 관리자(admin) 사이트 사용하기
- 여러가지 요청에 따른 특정 데이터를 가져오는 뷰(view)와 브라우저상에서 이 데이타를 볼수 있도록 HTML로 렌더링하는 템플릿을 생성하기
- 여러가지 URL 패턴과  특정한 뷰를 연결하는 맵퍼(mappers) 만들기
- 사이트 동작과 접속을 통제하기 위한 유저 인증(authorization) & 세션 추가하기
- 폼으로 작업하기
- 앱을 테스트할 코드 작성하기
- 장고의 보안도구를 효과적으로 사용하기
- 애플리케이션을 운영환경에 배포하기

여러분은 이 토픽들 중 일부는 이미 배웠고, 나머지는 가볍게 경험했다. 이 튜토리얼 시리즈를 완료하면, 여러분은 간단한 장고 앱을 혼자서 충분히 개발할 수 있다. 

## 지역 도서관 웹사이트

*LocalLibrary는 이 튜토리얼 시리즈에서 우리가 만들고 개선시켜나갈 웹사이트의 이름이다.* 이름에서 예상되듯이, 이용자들이 대여 가능한 책을 찾아보고 사용자 계정을 관리할 수 있는, 작은 지역 도서관을 위한 온라인 도서목록을 제공하는 것이 목적이다.

이 예제는, 우리가 필요에 따라 크게 혹은 작게 확장할 수 있고, 대부분은 장고의 특성을 보여줄 수 있도록 아주 신중하게 선택된 예제이다. 더욱 중요한 것은 이 예제는 장고 웹 프레임워크의 가장 중요한 기능들을 경험해 보도록 안내된 경로를 제공한다:

- 처음 몇몇의 튜토리얼에서, 사용자가 어떤 책을 이용할 수 있는지 찾아볼 수 있도록, 간단한 둘러보기 전용 도서관 기능을 정의할 것이다. 이 내용은 거의 모든 웹사이트에서 일반적으로 제공되는 동작(데이타베이스에서 내용을 읽고 보여주는 것)을 탐색해볼 수 있도록 해줄 것이다.
- 튜토리얼을 좀 더 진행해 가면서, 도서관 예제는 좀 더 고급의 장고 기능을 보여줄 수 있도록 자연스럽게 확장된다. 예를 들면, 사용자가 책을 예약하도록 기능을 확장할 수 있고 이것을 이용해 폼을 사용하는 방법과 사용자 인증을 지원하는 방법을 보여줄 수 있다.

이것은 매우 확장성있는 예제이지만, 다음과 같은 이유로 **Local**Library(Local에 강조)로 이름을 지었다. 그 이유는, 당신이 장고 개발을 빠르게 착수할 수 있도록, 필요한 최소한의 정보만 보여주고자 의도한 것이다. 결과적으로 책, 책의 판본, 저자및 다른 Key 정보를 저장할 것이다. 하지만 그외의 일반적인 도서관이 추가로 저장할만한 정보는 저장하지 않을 것이며, 여러개의 도서관 사이트를 지원하거나, "커다란 도서관"을 위한 기능은 제공하지 않을것이다.

## 개발중에 막혔어요, 소스코드는 어딨죠?

튜토리얼을 진행하면서, 각 포인트마다 복사해서 붙여넣기할 수 있는 적절한 토막 코드가 제공될 것이다. 또한 당신이 스스로 (약간의 안내문과 함께) 도전해볼 수 있는 부분도 있을 것이다.

개발중에 진행이 어렵다면, 여기[ Github](https://github.com/mdn/django-locallibrary-tutorial) 에 완전히 개발된 버전의 웹사이트 소스코드를 참고할 수도 있다.

## 요약

LocalLibrary 웹사이트와 당신이 앞으로 배울 내용에 대해 좀 더 알게되었다. 이제 우리 예제를 담을 [뼈대 프로젝트(skeleton project)](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/skeleton_website)를 생성해볼 차례이다.



# 장고 튜토리얼 강좌 2 : 뼈대 사이트 만들기

[장고 튜토리얼](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website)의 두 번째 기사에서는 웹 사이트 프로젝트의 기본 뼈대(skeleton)를 만들고, 사이트의 특성에 맞춰 설정, 경로, 모델, 뷰 및 템플릿을 다루는 방법을 보여줍니다. 

| 사전 준비: | [장고 개발 환경을 설치하세요. (Set up a Django development environment.)](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/development_environment) [장고 튜토리얼](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website)을 복습하세요. |
| :--------- | ------------------------------------------------------------ |
| 목표:      | 당신만의 새로운 웹사이트 프로젝트를 시작하기 위해 장고의 도구들을 사용할 수 있는 능력 기르기. |

## 개요

이 글은 웹사이트의 "뼈대"를 생성하는 법을 보여줍니다. 그리고 이 사이트는 사이트에 특화된 설정, 경로, 모델, 뷰, 템플릿 등을 작성할 수 있습니다. (이후 글에서 이것들에 관해 다루겠습니다)

과정은 직관적입니다:

1. 프로젝트 폴더, 기본적인 파일 템플릿과 프로젝트 관리 스크립트(**manage.py**)를 만들기 위해서 `django-admin`을 사용합니다.

2. 하나 또는 그 이상의 애플리케이션을 만들기 위해서 manage.py 를 사용합니다.

   **Note**: 하나의 웹사이트는 하나 또는 그 이상의 섹션으로 구성될 수 있습니다. (예를 들어 main site, blog, wiki, downloads area 등). 장고는 필요할 때에 다른 프로젝트에서 재사용이 가능할 수 있게 , 이 요소들을 분리된 어플리케이션으로 개발하는 것을 추천합니다.

3. 프로젝트에 포함시키기 위해 새 어플리케이션들을 등록(register)합니다.

4. 각 어플리케이션에 대해 url/mapper를 연결(hook up)합니다.

[Local Library website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 용으로 웹사이트 폴더와 프로젝트 폴더는 locallibrary라고 이름지어질 것입니다. 그리고 catalog라는 단 하나의 어플리케이션을 가질 겁니다. 그러므로 최상위 폴더 구조는 아래와 같습니다:

```cmd
locallibrary/         # Website folder
    manage.py         # Script to run Django tools for this project (created using django-admin)
    locallibrary/     # Website/project folder (created using django-admin)
    catalog/          # Application folder (created using manage.py)
```

다음 섹션에서는 프로세스 단계를 자세히 설명하고 변화를 테스트할 수 있는 방법을 설명합니다. 글의 마지막에서 당신이 이 단계에서 수행해야 하는 몇 가지 다른 사이트 전체 설정에 관해 논의합니다.

## 프로젝트 만들기

먼저 명령 프롬프트 또는 터미널을 열어서, 당신이 [virtual environment](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/development_environment#using_a_virtual_environment) 안에 있는지 확인하고, 어디에 당신의 장고 앱을 (당신의 '문서'와 같이 찾기 쉬운 어딘가로 하세요) 저장하기 원하는지 탐색합니다. 그리고 당신의 새로운 웹사이트 폴더를 만드세요 (이 예제에서는: *locallibrary*). 그리고 cd 명령어를 사용하여 해당 폴더로 들어가세요:

```cmd
# wonsool
> cd  C:\home\rnd\python\django
> django_env\Scripts\activate.bat
# 이후 부터 python3 는 모두 python 으로 대체됨 (가상환경 에서 실행)

# 생략 (실행 하지 말것)
(django_env) > mkdir locallibrary
(django_env) > cd locallibrary

```



다음과 같이 `django-admin startproject` 명령을 사용하여 새로운 프로젝트를 만들고, 그 폴더 안으로 들어가세요. (변역자주: 실제 해보니 위의 문장은 하지 말아야 합니다. 하나의 parent folder 가 더 만들어집니다. 즉, locallibrary-locallibrary-locallibrary )

```cmd
(django_env) > django-admin startproject locallibrary
(django_env) > cd locallibrary
```



`django-admin` 도구가 아래와 같이 폴더/파일 구조를 생성합니다. (번역자주: 윈도우 환경에서는 `tree /f locallibrary` 명령으로 구조를 확인할 수 있습니다. tree 명령은 cd locallibrary 전에 수행한 모습 입니다.)

```cmd
locallibrary/
    manage.py
    locallibrary/
        __init__.py
        settings.py
        urls.py
        wsgi.py
```

Copy to Clipboard

locallibrary 프로젝트의 하위 폴더는 웹사이트의 시작점입니다:

- **`__init__.py`**는 빈 파일입니다. 이 파일은 파이썬에게 이 디렉토리를 하나의 파이썬 패키지로 다루도록 지시합니다.
- **settings.py**는 웹사이트의 모든 설정을 포함하고 있습니다. 이 파일에는 우리가 만드는 모든 어플리케이션, 정적 파일의 위치, 데이터베이스 세부 설정 등을 등록합니다. 
- **urls.py**는 사이트의 URL과 뷰의 연결을 지정해줍니다. 여기에는 모든 URL 매핑 코드가 포함될 수 있지만, 특정한 어플리케이션에 매핑의 일부를 할당해주는 것이 일반적입니다.
- **wsgi.py**는 당신의 장고 어플리케이션이 웹서버와 연결 및 소통하는 것을 돕습니다. 당신은 이것을 표준 형식(boilerplate)으로 다뤄도 무방합니다.

**manage.py**는 어플리케이션을 생성하고, 데이터베이스와 작업하고, 그리고 개발 웹 서버를 시작하기 위해 사용됩니다. 



## catalog application 만들기

다음으로, locallibrary 프로젝트 안에 생성될 catalog 어플리케이션을 만들기 위해 아래 명령어를 실행하세요. 이것은 프로젝트의 **manage.py**와 같은 폴더 안에서 실행되어야 합니다.

```cmd
# wonsool
cd C:\home\rnd\python\django\locallibrary

python manage.py startapp catalog
```



> **주의** : 위 명령어는 리눅스/macOS X를 위한 명령어입니다. 윈도우에서의 명령어는 다음과 같습니다.  `py -3 manage.py startapp catalog`



만약 윈도우에서 작업한다면, 이 튜토리얼 전체에서 `python3`를 `py -3`로 바꾸십시오. ( 가상 환경 이므로 python 으로 실행)

만약 파이썬 3.7.0 이상을 사용한다면 `py manage.py startapp catalog`를 사용하면 됩니다.

이 도구는 새로운 폴더를 생성하고 폴더를 어플리케이션의 파일들로 채웁니다(아래에 굵게 표시). 대부분의 파일들은 그것의 목적에서 따온 이름을 갖고 있습니다. 예를들어, 뷰는 **views.py**에, 모델은 **models.py**에, 테스트는 **tests.py**에, 관리자 사이트 설정은 **admin.py**에, 어플리케이션 등록(registration)은 **apps.py**에 있습니다. 그리고 관련 객체(object)에 대한 작업을 위한 최소한의 표준 코드를 포함합니다.

이제 업데이트된 프로젝트 디렉토리는 다음과 같아야 합니다. 

```cmd
locallibrary/
    manage.py
    locallibrary/
    catalog/
        admin.py
        apps.py
        models.py
        tests.py
        views.py
        __init__.py
        migrations/
```



 추가로 다음을 갖게 됐습니다:

- **migrations 폴더** — 모델을 수정할 때 마다 자동으로 데이터베이스를 업데이트하는 것을 가능하게 해 줄 마이그레이션 파일들을 저장할 폴더 
- **`__init__.py`** — 장고/파이썬이 폴더를 [파이썬 패키지](https://docs.python.org/3/tutorial/modules.html#packages) 로 인식하게 할 빈 파일입니다. 또한 프로젝트의 다른 부분에서 객체(object)를 사용할 수 있게 합니다.

> **주의** : 위의 파일 리스트에서 뭔가 부족한게 있다는 것을 알아챘나요? 뷰와 모델 관련 파일은 있는 반면 URL 맵핑, 템플릿, 정적 파일(static file)과 연관된 파일이 없습니다. 그들을 어떻게 생성하는지에 관해서는 추후에 보여드리겠습니다 (이것들은 모든 웹사이트에서 필요하진 않지만 우리 프로젝트에서는 필요합니다).



## catalog application 등록하기

이제 어플리케이션이 생성되었으니 프로젝트에 등록(register)해야합니다. 도구가 실행될 때 프로젝트에 포함시키기 위해서 말이죠(예를 들어 모델을 데이터베이스에 추가할 때 처럼요). 어플리케이션들은 프로젝트 설정 안의 `INSTALLED_APPS` 리스트에 추가함으로써 등록할 수 있습니다. 

프로젝트의 설정 파일(**locallibrary/locallibrary/settings.py**)을 열고 `INSTALLED_APPS` 리스트의 정의 부분을 찾으세요. 그리고 그 리스트 제일 아래에 다음과 같이 기입해 주세요.

```python
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'catalog.apps.CatalogConfig', 
]
```

`'catalog.apps.CatalogConfig', ` 만 추가 하면 된다.



새로운 행은 어플리케이션 구성 객체(application configuration object)(`CatalogConfig`)를 지정하게 됩니다. 이것은 어플리케이션을 생성할 때 **/locallibrary/catalog/apps.py** 안에 생성됩니다.

> **주의**: 설정 파일에 이미 많은 `INSTALLED_APPS` 항목과 `MIDDLEWARE` 항목이 있음을 알 수 있습니다. 이를 통해 [장고 관리 사이트](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Admin_site)를 지원할 수 있으며 결과적으로 세션, 인증 등을 포함한 많은 기능이 사용됩니다.



## 데이터베이스 설정

이제 보통 프로젝트에 사용할 데이터베이스를 지정하는 시점입니다— 가능한 한 개발과 결과물에 동일한 데이터베이스를 사용하여 사소한 동작 차이를 방지해야 합니다. [Databases에 대한 장고 문서](https://docs.djangoproject.com/en/2.0/ref/settings/#databases) 에서 가능한 다른 옵션을 확인할 수 있습니다. 

이 예제에서는 SQLite 데이터베이스를 사용합니다. 데모 데이터베이스에서 많은 동시 접속을 예상하지 않고, 설정에 추가적인 작업이 필요없기 때문입니다. 이 데이터베이스가 어떻게 설정되어 있는지 **/locallibrary/locallibrary/settings.py**에서 확인할 수 있습니다.

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
    }
}

## 실제 다음과 같이 생성 되어 있음 (2021.06.23, wonsool)
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}
## BASE_DIR 는 위쪽에 정의되어 있음.
```



우리는 SQLite를 사용하기 때문에 여기서 다른 어떤 작업도 할 필요가 없습니다. 다음으로 가죠!



## 프로젝트의 다른 설정

**settings.py** 파일은 다른 많은 설정을 조정하는 데에 사용됩니다. 그러나 지금은 [TIME_ZONE](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-TIME_ZONE) 만 바꿔 봅시다— 이 부분은 표준화된 [List of tz database time zones](https://en.wikipedia.org/wiki/List_of_tz_database_time_zones)과 일치되는 문자열을 사용해야 합니다 (테이블의 TZ 열의 값들을 참고하세요). `TIME_ZONE` 값을 당신의 타임존에 알맞은 문자열로 바꾸세요. (역자주: 한국은 `'Asia/Seoul'`로 설정)

```python
TIME_ZONE = 'Europe/London'
```



지금은 바꾸지 않지만 알아둬야 할 두 가지 설정이 있습니다:

- `SECRET_KEY` : 이것은 장고의 웹사이트 보안 전략의 일부로 사용되는 비밀키입니다. 만약 이 코드를 개발 과정에서 보호하지 않는다면 제품화(production) 과정에서 다른 코드(아마 환경변수나 파일에서 읽어오는)를 사용해야 할 것입니다. 
- `DEBUG` : 이것은 에러가 발생했을 때 HTTP 상태코드 응답 대신 디버깅 로그가 표시되게 합니다. 디버깅 정보는 공격자에게 유용하기 때문에 제품화된(production) 환경에서는 `False` 로 설정해야 합니다. 하지만, 지금은 `True`로 설정합니다.



## URL 맵퍼 연결

웹사이트는 프로젝트 폴더 안의 URL 맵퍼 파일(**urls.py**)과 같이 생성됩니다. **urls.py**를 통해 모든 URL 맵핑을 관리할 수 있지만, 연관된 어플리케이션에 따라 매핑을 다르게 하는 것이 일반적입니다.

 **locallibrary/locallibrary/urls.py** 파일을 열어서 URL 맵퍼를 사용하는 몇 가지 방법을 설명하는 문서를 살펴보세요. 

```python
"""locallibrary URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
예제:
Function views 일 경우
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views 일 경우
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
다른 참조할 URL FILE 들을 포함시켜야 하는경우
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

urlpatterns = [
    path('admin/', admin.site.urls),
]
```



URL 맵핑은 `urlpatterns` 변수를 통해 관리되는데, 이 변수는 `path()` 함수의 파이썬 `list` 타입입니다. 각각의 `path()` 함수는 패턴이 일치할 때 표시될 뷰에 URL 패턴을 연결하거나, 다른 URL 패턴 테스트 코드 목록에 연결합니다(이 두 번째 경우에서 패턴은 대상 모듈에서 정의된 패턴의 "기본 URL"이 됩니다). `urlpatterns` 리스트는 맨 처음에 관리자 어플리케이션의 고유한 URL 맵핑 정의를 갖고 있는 `admin.site.urls` 모듈에 `admin/` 패턴을 가지고 있는 모든 URL을 매핑하는 단일 함수를 정의합니다.

**주의**: `path()` 속의 경로는 일치시킬 URL 패턴을 정의하는 문자열입니다. 이 문자열은 명명된(named) 변수를 꺽쇠 괄호(`< >`) 안에 포함할 수 있습니다. (예시: `'catalog/<id>/'`) 이 패턴은 URL을 **/catalog/***any_chars***/** 처럼 일치시키고 *any_chars*를 뷰에 매개 변수 이름이 `id` 인 문자열로 전달합니다. 경로(path) 함수(method)와 경로(route) 패턴에 대해서는 추후에 더 논의하겠습니다.

`urlpatterns` 리스트에 새로운 리스트 항목을 추가하기 위해서 아래 코드를 파일의 마지막에 추가하세요. 이 새로운 항목은 요청(request)을 모듈 `catalog.urls`(관련 URL **/catalog/urls.py**가 있는 파일)에 `catalog/` 패턴과 함께 전달하는 `path()`를 포함합니다. (번역자주: 만일 `www.xxxx.com/catalog`로 시작되는 요청이 들어 오면 `catalog/urls.py`를 참조해서 맵핑하겠다는 의미)

`locallibrary/locallibrary/urls.py 에 설정`

```python
# Use include() to add paths from the catalog application
from django.conf.urls import include
from django.urls import path

urlpatterns = [
    path('admin/', admin.site.urls),
]

urlpatterns += [
    path('catalog/', include('catalog.urls')),
]
```

`from django.conf.urls import include` 추가 되는 부분 빼먹지 말것 (wonsool)



이제 사이트의 루트 URL(즉, `127.0.0.1:8000`)을 `127.0.0.1:8000/catalog/`로 리다이렉트 하도록 합시다. 이것이 우리가 이 프로젝트에서 사용하는 유일한 어플리케이션입니다. 이것을 하기 위해서는 특별한 뷰 함수(`RedirectView`)를 사용할 겁니다. 이 함수는 `path()`에서 지정된 URL 패턴이 일치할 때(위의 경우에선 루트 URL이죠) 첫 번째 인자를 (`/catalog/`)로 리다이렉트할 새로운 상대 URL로 간주합니다.

파일(`locallibrary/locallibrary/urls.py`)의 하단에 아래 코드를 다시 추가하세요:

```python
#Add URL maps to redirect the base URL to our application
from django.views.generic import RedirectView
urlpatterns += [
    path('', RedirectView.as_view(url='/catalog/', permanent=True)),
]
```



`path()` 함수의 첫 번째 매개변수(parameter)를 비워 놓으면 `'/'`를 의미합니다. 만약 첫 번째 매개변수(parameter)를 `'/'`라고 작성한다면 개발 서버를 시작할 때 장고는 아래의 경고를 보여줄 겁니다.

```python
System check identified some issues:

WARNINGS:
?: (urls.W002) Your URL pattern '/' has a route beginning with a '/'.
Remove this slash as it is unnecessary.
If this pattern is targeted in an include(), ensure the include() pattern has a trailing '/'.
```



장고는 기본적으로 CSS, JavaScript, 그리고 이미지와 같은 정적 파일을 제공하지 않지만, 이들은 사이트에 매우 유용할 수 있습니다. 최종적으로 이 URL 매퍼에 추가할 것은 개발 중에 정적 파일들을 제공하는 것을 가능하게 하는 아래 코드입니다. 

아래 코드를 파일(`locallibrary/locallibrary/urls.py`)의 하단에 추가하세요:

```python
# Use static() to add url mapping to serve static files during development (only)
from django.conf import settings
from django.conf.urls.static import static

urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
```



> **주의**: `urlpatterns` 리스트를 확장하는 여러 가지 방법들이 있습니다(위에서는 코드들을 명백하게 구분하기 위해서 간단히 `+=` 연산자를 사용해서 새로운 리스트 항목을 추가했습니다). 대신 원래의 리스트 정의 안에 새로운 패턴-맵을 추가하는 방법이 있습니다:

```python
urlpatterns = [
    path('admin/', admin.site.urls),
    path('catalog/', include('catalog.urls')),
    path('', RedirectView.as_view(url='/catalog/', permanent=True)),
] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
```



추가적으로 임포트(import) 코드(`from django.urls import include`)를 그것을 사용하는 코드 바로 위에 선언했지만(무엇을 추가했는지 보기 쉽도록) 대개 import 문장은 파이썬 파일의 상단에 포함하는 것이 일반적입니다.

마지막으로 **urls.py**라는 파일을 catalog 폴더 안에 생성하세요. 그리고 임포트된 (텅 빈(emptyt))`urlpatterns`를 정의하기 위해 아래 코드를 추가하세요. 어플리케이션을 만들면서 패턴들을 이곳에 추가할 것입니다. 

```python
from django.urls import path
from catalog import views


urlpatterns = [

]
```



## Website framework 테스트 하기

우리는 이 프로젝트의 뼈대(skeleton)를 만들었습니다. 웹사이트는 아직 아무것도 하지 않지만, 우리들이 여기까지 완성한 프로젝트가 오류 없이 돌아가는지 한번 실행해 볼 필요가 있습니다. 

그 전에 먼저 데이터베이스로의 마이그레이션(migration) 작업을 해야 합니다. 이것은 데이터베이스에 우리의 어플리케이션에 속한 모든 모델을 포함하도록 업데이트합니다(그리고 몇몇 빌드 경고의 원인을 제거합니다).



### 데이터베이스 마이그레이션(migration) 실행하기

장고는 ORM(Object-Relational-Mapper : 객체-관계-매퍼)를 사용하여 장고 코드 안에 있는 모델 정의(객체)를 기본 데이터베이스에서 사용하는 데이터 구조(관계형 DB)에 매핑합니다. 모델의 정의를 바꿀 때 마다, 장고는 변화를 추적해서, 데이터베이스 안의 기본 데이터 구조가 모델과 일치하도록 자동적으로 이전(migrate)하는 스크립트를(**/locallibrary/catalog/migrations/**안에)생성할 수 있습니다.

웹사이트를 생성할 때 장고는 사이트의 관리자 섹션(나중에 살펴볼)에서 사용할 여러 모델들을 자동으로 추가했습니다. 데이터베이스 안의 그 모델들을 위한 테이블들을 정의하기 위해 아래 명령어를 실행하세요(**manage.py**파일이 포함되어 있는 디렉토리에서 실행합니다). 

```cmd
python manage.py makemigrations
python manage.py migrate
```



> **중요**:  저장되어야 할 데이터의 구조에 영향을 미치는 방식으로 모델이 변경될 때마다 위의 명령어를 실행해야 합니다(모든 모델과 개별적인 필드의 추가와 제거를 포함하여).



`makemigrations` 명령어는 프로젝트에 설치된 모든 어플리케이션에 대한 migration을 생성합니다(하지만 적용하진 않습니다)(또한 그저 단일 프로젝트를 위한 migration을 실행하기 위해 어플리케이션 이름을 지정할 수 있습니다). 이것으로 migration이 적용되기 전에 코드를 점검할 기회를 가질 수 있습니다 — 당신이 장고 전문가가 되었을 땐 그것들을 조금 조정할 수도 있습니다!

`migrate` 명령어는 migration을 실제로 데이터베이스에 적용합니다(장고는 현재 데이터베이스에 어떤 것들이 추가되었는지 추적합니다).



> **주의**: 덜 사용되는 migration 명령어에 대한 추가적인 정보는 [Migrations](https://docs.djangoproject.com/en/2.0/topics/migrations/) (장고 문서)를 참고하세요.



### 웹사이트 실행하기

개발 중에 먼저 개발 웹 서버를 사용해서 웹사이트를 서비스한 후 로컬 웹 브라우저에서 볼 수 있습니다.

 

> **주의**: 개발용 웹 서버는 견고하거나 제품에 쓰일 만큼 충분하진 않지만, 개발 중에 편하고 빠른 테스트를 위해 장고 웹사이트를 실행할 수 있는 아주 쉬운 방법입니다. 기본적으로 사이트를 당신의 로컬 컴퓨터에(`http://127.0.0.1:8000/)`서비스 하지만, 네트워크에 있는 다른 컴퓨터를 지정해서 서비스하도록 할 수 있습니다. [django-admin and manage.py: runserver](https://docs.djangoproject.com/en/2.0/ref/django-admin/#runserver) (장고 문서)에서 더 많은 정보를 확인하세요.



**manage.py**와 같은 디렉터리 안에 있는 `runserver` 명령어를 실행해 개발 웹 서버를 실행해 보세요.

```cmd
python manage.py runserver

 Performing system checks...

 System check identified no issues (0 silenced).
 August 15, 2018 - 16:11:26
 Django version 2.1, using settings 'locallibrary.settings'
 Starting development server at http://127.0.0.1:8000/
 Quit the server with CTRL-BREAK.
```



 서버가 실행된다면 로컬 웹 브라우저에서 `http://127.0.0.1:8000/` 으로 이동하여 사이트를 볼 수 있습니다. 아래와 같은 사이트 에러 페이지가 뜰 겁니다:

![Django Debug page for Django 2.0](./images/django_404_debug_page.png)

걱정 마세요! 예상된 에러 페이지입니다. 그 이유는 아직(사이트의 루트에 대한 URL을 가져올 때 리다이렉트되는) `catalogs.urls` 모듈 안에 정의된 page/url들이 없기 때문입니다. 

> **주의**: 위 페이지는 중요한 장고 기능을 보여줍니다— 자동화된 디버그 기록(logging)이죠. 에러 화면은 페이지를 찾을 수 없거나, 코드에서 에러가 발생했을 어떤 때라도 유용한 정보가 표시될 겁니다. 이 경우엔 (목록에 있는 대로) 우리가 제공한 URL과 일치하는 어떤 URL 패턴도 없다는 것을 볼 수 있습니다. 디버그 기록(logging)은 제품화되었을 (웹에 라이브로 사이트를 올려놓으면) 때는 꺼져 있을 겁니다. 정보는 더 적지만, 사용자 친화적인 페이지가 서비스되는 것이죠.

이 지점에서 장고가 작동한다는 것을 알 수 있습니다! 

> **주의**: 어떤 때라도 중요한 변경이 있은 후에는 migration들을 재실행하고 사이트를 다시 테스트해야 합니다. 그렇게 오래 걸리진 않으니까 꼭 하세요!



## 도전 과제

**catalog/** 디렉토리는 뷰, 모델, 그리고 어플리케이션의 다른 부분들을 위한 파일들을 포함하고 있습니다. 이 파일들을 열어 표준 코드(boilerplate)들을 점검해 보세요. 

위에서 본 것처럼, 관리자 사이트를 위한 URL 매핑은 이미 프로젝트의 **urls.py** 안에 추가되어 있습니다. 브라우저에서 관리자 영역으로 이동하여 어떤 일이 일어나는지 살펴보세요(위서 살펴본 매핑에서 올바른 URL을 추론할 수 있습니다).



## 요약

이제 urls, models, views, 그리고 templates으로 채울 수 있는 완벽한 뼈대 웹사이트 프로젝트를 만들었습니다.

[Local Library website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 를 위한 뼈대는 완성되어서 실행되고 있고, 이제는 이 웹사이트가 해야 할 일을 하게 만드는 코드를 작성할 시간입니다. 



# Django Tutorial Part 3: Using models

이 문서에서는 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 웹사이트의 모델을 어떻게 정의할지 보여줄 것입니다. 모델이라는 것이 무엇인지, 어떻게 선언하는지, 그리고 주된 필드 타입들에 대해서 설명합니다. 그리고 모델의 데이터에 접근할 수 있는 몇몇 방법에 대해서 간단히 보여줄 것입니다.

| 사전 준비: | [Django Tutorial Part 2: Creating a skeleton website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website). |
| :--------- | ------------------------------------------------------------ |
| 목표:      | 적절한 필드를 사용하여 모델을 설계 및 생성할 줄 알기.        |

## 개요

장고 웹 어플리케이션들은 모델이라는 파이썬 객체를 통해 데이터에 접속하고 관리합니다. 모델은 저장된 데이터의 구조를 정의합니다. 그것엔 필드 타입, 그리고 데이터의 최대 크기, 기본값, 선택 리스트 옵션, 문서를 위한 도움 텍스트, 폼을 위한 라벨 텍스트 등등이 있습니다. 모델의 정의는 기초 데이터베이스에 대해 독립적입니다 — 프로젝트 설정의 일부로 여러 옵션 중 하나를 선택할 수 있습니다. 사용할 데이터베이스를 정했다면 데이터베이스에 직접적으로 말할 필요가 없습니다 — 그저 모델 구조와 기타 코드를 작성하면, 장고가 데이터베이스와 소통하는 모든 더러운 작업을 대신해줍니다.

이 튜토리얼은 [LocalLibrary website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 예제에서 어떻게 모델을 정의하고 그것에 접근하는지에 대해 보여줍니다.



## LocalLibrary models 디자인하기

모델을 코딩하기 전에, 우리가 어떤 데이터를 저장할 것인지, 그리고 다른 객체(object)들에 대한 관계를 어떻게 지정할 것인지 생각해 봅시다.

우리는 책에 관한 정보들을 저장할 필요가 있고 (**제목, 요약, 저자, 작성된 언어, 분류, ISBN**) 여러 개의 사본을 사용할 수 있어야 합니다(전 세계적으로 고유한 ID, 가용성 상태 등). 저자에 대해서 그들의 이름 뿐만 아니라 더 많은 정보를 저장해야 할 수도 있습니다. 여러 명의 같거나 비슷한 이름의 저자가 있을 수도 있기 때문이죠. 우리는 정보를 **책 제목, 저자, 언어, 그리고 분류에 따라 정렬**할 수 있기를 원합니다.

모델을 디자인할 때는 각각의 "객체(object: 관련된 정보의 모임)" 마다 분리된 모델을 가지는 것이 타당합니다. 이 예시에서 명백히 확인할 수 있는 객체(object)들은 **책, 책 인스턴스, 저자**입니다.

선택을 웹사이트 자체에 하드코딩하는 것 보다는 모델을 사용해서 선택-리스트 옵션을 나타내도록(예시: 드롭 다운 목록)할 수 있습니다 — 이것은 모든 옵션들을 미리 알 수 없거나 옵션들이 변할 수 있을 때에 추천됩니다. 이 경우에 명백한 모델의 후보자로 책의 장르(예시: 공상 과학, 프랑스 시, 등등)와 언어(영어, 프랑스어, 한국어)가 있습니다.

우리가 우리의 모델과 필드를 결정하고 나면, 우리는 그 관계에 대해서 생각해야 합니다. 장고는 당신이 그 관계를 다음과 같이 세 가지로 설정할 수 있게 하는데, 일대일(`OneToOneField`), 일대다(`ForeignKey`), 다대다(`ManyToManyField`) 관계가 그것입니다.

그것들을 염두에 두고 아래의 UML 관계 다이어그램을 살펴봅시다. 이 다이어그램은 우리가 이 예시에서 정의할 모델들을 상자로 보여줍니다. 위에서 살펴본 바와 같이, 우리는 책(Book, 책의 일반적인 세부 사항들), 책 인스턴스(BookInstance, 시스템에서 사용 가능한 책의 특정한 물리적 복사본의 상태), 그리고 저자(Author)를 모델로 생성했습니다. 우리는 또한 장르(Genre)에 대한 모델을 만들어서 값들이 관리자 인터페이스에서 생성/선택이 가능하도록 만들었습니다. 우리는 `BookInstance:status`에 대한 모델을 생성하지 않았습니다 — 값들을(`LOAN_STATUS`) 하드코딩 했죠. 왜냐하면 그것은 변하지 않는 값들이기 때문입니다. 각각의 상자 안에서 모델 이름, 필드 이름과 타입, 그리고 또한 함수(method)와 그들의 반환 타입(return type)을 확인할 수 있습니다.

이 다이어그램은 또한 다중도(multiplicities)을 포함한 모델 간의 관계를 보여줍니다. 다중도(multiplicities)는 관계 안에 존재하는 각각의 모델의 숫자(최대 그리고 최소)를 보여주는 다이어그램 위의 숫자입니다. 예를 들어, 상자 사이를 연결하는 선은 책과 장르가 연관되어 있다는 것을 보여줍니다. 장르(Genre) 모델에 가까이 있는 숫자들은 책이 하나 또는 그 이상의 장르(원하는 만큼 많이)를 가지고 있어야 함을 보여주는 반면, 선의 반대편 끝에 있는 책(Book) 모델 옆의 숫자들은 장르 모델이 0 또는 여러 개의 관련된 책 모델을 가질 수 있음을 보여줍니다 .

![LocalLibrary Model UML](./images/local_library_model_uml.png)

> **주의**: 다음 섹션은 모델이 어떻게 정의되고 사용되는지에 대한 초보적인 설명입니다. 읽으면서 위 다이어그램의 각각의 모델들을 어떻게 구성할 지 생각해 보십시오.



## 모델 입문서

이 부분에서는 어떻게 모델을 정의하는지, 그리고 더 중요할 지도 모르는 필드와 필드의 인자에 대해서 간단한 개요를 제공합니다.

### 모델의 정의

모델들은 보통 어플리케이션의 **models.py** 파일에서 정의됩니다. 이들은 `django.db.models.Model`의 서브 클래스로 구현되며 필드, 메소드 그리고 메타데이터를 포함할 수 있습니다. 아래의 코드 조각은 `MyModelName`라고 이름지어진 "전형적인" 모델을 보여줍니다:

```python
from django.db import models

class MyModelName(models.Model):
    """A typical class defining a model, derived from the Model class."""

    # Fields
    my_field_name = models.CharField(max_length=20, help_text='Enter field documentation')
    ...

    # Metadata
    class Meta:
        ordering = ['-my_field_name']

    # Methods
    def get_absolute_url(self):
        """Returns the url to access a particular instance of MyModelName."""
        return reverse('model-detail-view', args=[str(self.id)])

    def __str__(self):
        """String for representing the MyModelName object (in Admin site etc.)."""
        return self.field_name
```



아래 섹션에서 모델 안에 있는 각각의 요소들을 세부적으로 다뤄봅시다:



#### 필드(Fields)

모델은 모든 타입의, 임의의 숫자의 필드를 가질 수 있습니다 — 각각의 필드는 우리의 데이터베이스 목록(table)에 저장하길 원하는 데이터 열(column)을 나타냅니다. 각각의 데이터베이스 레코드(행, row)는 각 필드 값들 중 하나로 구성되어 있습니다. 위의 예제를 살펴봅시다:

```python
my_field_name = models.CharField(max_length=20, help_text='Enter field documentation')
```



위 예제는 `my_field_name`이라는 하나의 필드를 가지고 있고, `models.CharField` 타입입니다 — 즉, 이 필드가 영숫자(alphanumeric) 문자열을 포함한다는 뜻이죠. 필드 타입들은 특정한 클래스들을 사용하여 등록되며, HTML 양식(form)에서 값을 수신할 때 사용할 유효성 검증 기준과 함께 데이터베이스에 데이터를 저장하는데 사용되는 레코드의 타입을 결정합니다. 또한 필드 타입은 필드가 어떻게 저장되고 사용될지 지정하는 인수를 사용할 수 있습니다. 이 예제에서는 필드에 두 가지 인수를 줍니다:

- `max_length=20` — 이 필드 값의 최대 길이는 20자임을 알립니다.
- `help_text='Enter field documentation'` — 이 값이 HTML 양식(form)에서 사용자들에게 입력될 때 어떤 값을 입력해야 하는지 사용자들에게 알려주기 위해 보여주는 텍스트 라벨을 제공합니다.

필드 이름은 쿼리 및 탬플릿에서 이를 참조하는데 쓰입니다. 필드는 또한 인수로 지정된 라벨(`verbose_name`)을 가지고 있거나, 또는 필드 변수 이름의 첫자를 대문자로 바꾸고 밑줄을 공백으로 바꿔서 기본 라벨을 추정할 수 있습니다(예를 들어 `my_field_name` 은 My field name을 기본 라벨로 가지고 있습니다) .

필드가 선언된 순서는 모델이 폼에서 렌더링 된다면(예시 : 관리자 사이트) 기본 순서에 영향을 미치지만, 이것은 재정렬될 수 있습니다.



##### 일반적(common) 필드 인수

아래의 일반적인 인수들은 많은/거의 대부분의 서로 다른 필드 타입들을 선언할 때 사용할 수 있습니다:

- [help_text](https://docs.djangoproject.com/en/2.0/ref/models/fields/#help-text): 위에서 다뤘던 것 처럼, HTML 양식(form)에 대해 텍스트 라벨을 제공합니다 (예시 : 관리자 사이트).
- [verbose_name](https://docs.djangoproject.com/en/2.0/ref/models/fields/#verbose-name): 필드 라벨 안에서 사용되는 인간이 읽을 수 있는 필드 이름입니다. 지정되지 않았다면, 장고가 기본 verbose_name을 필드 이름으로부터 유추합니다.
- [default](https://docs.djangoproject.com/en/2.0/ref/models/fields/#default): 필드를 위한 기본값입니다. 이것은 값 또는 호출 가능한 객체일 수 있습니다. 이때 객체는 새로운 레코드가 생성될 때 마다 호출됩니다.
- [null](https://docs.djangoproject.com/en/2.0/ref/models/fields/#null): 만약 `True`라면, 장고는 빈 `NULL` 값을 필드를 위한 데이터베이스에 저장할 것입니다(`CharField`는 대신 빈 문자열을 저장할 것입니다). 기본값은 `False`입니다.
- [blank](https://docs.djangoproject.com/en/2.0/ref/models/fields/#blank): 만약 `True`라면, 필드는 양식(form) 안에서 비워두는 것이 허락됩니다. 기본값은 `False`이며, 이것은 장고의 양식(form) 검증이 값을 입력하도록 강제한다는 뜻입니다. 이것은 종종 `null=True`와 함께 사용됩니다. blank 값을 허락할 때, 데이터베이스에서도 공백값을 적절하게 표시할 수 있어야 하기 때문입니다.
- [choices](https://docs.djangoproject.com/en/2.0/ref/models/fields/#choices): 필드를 위한 선택들의 모임입니다. 이 인수가 제공된다면, 대응하는 기본 양식(form) 위젯은 표준 텍스트 필드가 아닌 이 선택 항목을 가진 선택 상자입니다.
- [primary_key](https://docs.djangoproject.com/en/2.0/ref/models/fields/#primary-key): 만약 `True`라면, 현재 필드를 모델의 primary key로 설정합니다(primary key는 모든 다른 테이블 레코드들을 고유하게 확인하도록 지정된 특별한 데이터베이스 열입니다). primary key로 지정된 필드가 없다면 장고가 자동적으로 이 목적의 필드를 추가합니다.

다른 많은 옵션들이 있습니다 — 여기([full list of field options here](https://docs.djangoproject.com/en/2.0/ref/models/fields/#field-options))에서 모든 필드 옵션 목록을 볼 수 있습니다.



##### 일반적인(common) 필드 타입

아래의 목록은 일반적으로 사용되는 필드 타입들을 보여줍니다. 

- [CharField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#django.db.models.CharField)는 작거나 중간 크기의 고정된 길이의 문자열을 정의할 때 사용합니다. 저장되기 위해서는 데이터의 최대 길이(`max_length`)를 정해주어야 합니다.
- [TextField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#django.db.models.TextField)는 임의의 긴 문자열에 사용됩니다. 필드의 최대 길이(`max_length`)를 지정해야 할 수도 있지만, 그것은 필드가 양식(form) 안에 표시될 때만 지정하면 됩니다(데이터베이스 레벨에서 강제되지 않습니다).
- [IntegerField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#django.db.models.IntegerField)은 정수값(모든 숫자)을 저장하는 필드입니다. 그리고 양식(form)에 입력된 값이 정수임을 검증하기도 합니다.
- [DateField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#datefield)와 [DateTimeField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#datetimefield)는 날짜와 날짜시간 정보를 저장, 표현하는데 사용됩니다 (각각 파이썬 `datetime.date`와 `datetime.datetime` 객체로). 이 필드들은 추가적으로 (서로 독점적인) `auto_now=True` (모델이 저장될 때 마다 필드를 현재 날짜로 설정하기 위해), `auto_now_add` (모델이 처음 생성되었을 때만 날짜를 설정하기 위해) , 그리고 `default` (사용자에 의해 변경될 수 있는 기본 날짜를 설정하기 위해) 매개 변수를 선언할 수 있습니다.
- [EmailField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#emailfield)는 이메일 주소를 저장하고 검증하기 위해 사용합니다.
- [FileField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#filefield)와 [ImageField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#imagefield)는 각각 파일과 이미지를 업로드하기 위해 사용됩니다 (`ImageField`는 단지 업로드된 파일이 이미지임을 확인하는 추가 검증을 더할 뿐입니다). 이것들은 업로드된 파일들이 어디에 어떻게 저장되는지 정의하는 매개 변수를 가집니다.
- [AutoField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#autofield)는 자동적으로 증가하는 `IntegerField`의 특별한 타입입니다. 이 타입의 primary key는 명시적으로 지정하지 않는 이상 모델에 자동적으로 추가됩니다.
- [ForeignKey](https://docs.djangoproject.com/en/2.0/ref/models/fields/#foreignkey)는 다른 데이터베이스 모델과 일대다 관계를 지정하기 위해 사용됩니다 (예시: 차는 하나의 제조사를 갖고 있지만 제조사는 많은 차들을 만들 수 있습니다). 일대다에서 "일"쪽이 key를 포함하는 모델입니다.
- [ManyToManyField](https://docs.djangoproject.com/en/2.0/ref/models/fields/#manytomanyfield)는 다대다 관계를 지정하기 위해 사용됩니다 (예시: 책은 여러 장르를 가질 수 있고, 각각의 장르에도 많은 책들이 있습니다). 우리 예제인 도서관 어플리케이션에서는 이 필드를 `ForeignKeys`와 매우 유사하게 사용할 겁니다. 하지만 그룹 사이의 관계를 보여주기 위해서는 더욱 복잡한 방식으로 사용될 수 있습니다. 이것은 레코드가 삭제됐을 때 어떤 일이 일어나는지 정의하기 위해 `on_delete` 매개변수를 가집니다 (예시:  `models.SET_NULL`의 값은 단순히 `NULL`값으로 설정될 겁니다).

다른 많은 타입의 필드들이 많이 있습니다. 서로 다른 타입의 숫자를 위한 필드 (큰 정수, 작은 정수, 부동소수(float)), 불리언(booleans), URL, slug, unique id, 그리고 다른 "시간-관련된" 정보들(duration, time, 등등)들을 포함해서요. 모든 목록을 여기([full list here](https://docs.djangoproject.com/en/2.0/ref/models/fields/#field-types))에서 확인할 수 있습니다.



#### 메타데이터

아래와 같이 `class Meta`를 선언하여 모델에 대한 모델-레벨의 메타데이타를 선언할 수 있습니다.

```python
class Meta:
    ordering = ['-my_field_name']
```



이 메타데이터의 가장 유용한 기능들 중 하나는 모델 타입을 쿼리(query)할 때 반환되는 기본 레코드 순서를 제어하는 것입니다. 이렇게 하려면 위와 같이 필드 이름 목록의 일치 순서를 `ordering` 속성에 지정해야 합니다. 순서는 필드의 타입에 따라 달라질 것입니다(문자 필드는 알파벳 순서에 따라 정렬될 것이고, 반면 날짜 필드는 날짜순으로 정렬될 것입니다). 위와 같이, 반대로 정렬하고 싶다면 마이너스 기호(-)를 필드 이름 앞에 접두사로 붙이면 됩니다.

예를 들어, 우리가 기본적으로 아래와 같이 책들을 정렬하려고 한다면:

```python
ordering = ['title', '-pubdate']
```



책들은 A-Z까지 알파벳 순으로 정렬되고, 그 후에는 제목(title) 안에 있는 발행일 별로 가장 최근 것부터 가장 오래된 것 순으로 정렬될 것입니다.

다른 일반적인(common) 속성은 `verbose_name`이며, 단일 및 복수 형식(form)의 클래스를 위한 자세한(verbose) 이름입니다:

```python
verbose_name = 'BetterName'
```



다른 유용한 속성들은 모델을 위한 새로운 "접근 권한"을 생성 및 적용 가능하게 하며(기본 권한은 자동적으로 적용됨), 다른 필드에 기반한 순서 정렬을 허용하거나, 또는 클래스가 "추상(abstract: 레코드를 생성할 수 없고, 대신 다른 모델들을 만들기 위해 파생되는 기본 클래스)"임을 선언할 수 있습니다.

여러가지 메타데이터 옵션들은 모델에 무슨 데이터베이스를 사용해야만 하는가 그리고 데이터가 어떻게 저장되는가를 제어한다 (이것들은 모델을 기존 데이터베이스에 매핑할 때만 유용하다).

메타데이터 옵션의 모든 목록은 여기에서 볼 수 있습니다: [Model metadata options](https://docs.djangoproject.com/en/2.0/ref/models/options/) (장고 문서).



#### 메소드(Methods)

모델은 또한 메소드를 가질 수 있습니다.

**`최소한, 모든 모델마다 표준 파이썬 클래스의 메소드인 __str__()` 을 정의하여 각각의 object가 사람이 읽을 수 있는 문자열을 반환(return)하도록 합니다.** 이 문자열은 관리자 사이트에 있는 개별적인 레코드들을 보여주는 데 사용됩니다(그리고 모델 인스턴스를 참조해야 하는 다른 모든 곳에도요). 종종 이것은 모델에서 제목(title)이나 이름 필드(name field)를 반환할 것입니다 .

```python
def __str__(self):
    return self.field_name
```



장고 모델에 포함할 다른 일반적인 메소드는 `get_absolute_url()`입니다. 웹사이트의 개별적인 모델 레코드들을 보여주기 위한 URL을 반환하는 메소드입니다(만약 이 메소드를 정의했다면 장고는 관리자 사이트 안의 모델 레코드 수정 화면에 "View on Site" 버튼을 자동적으로 추가할 것입니다). 아래에서 `get_absolute_url()`의 표준적인 사용을 볼 수 있습니다.

```python
def get_absolute_url(self):
    """Returns the url to access a particular instance of the model."""
    return reverse('model-detail-view', args=[str(self.id)])
```



> **주의**: 모델의 개별적인 레코드들을 보여주기 위해서 `/myapplication/mymodelname/2` 와 같은 URL을 사용한다고 가정한다면("2"는 특정한 레코드를 위한 `id` 입니다), 응답과 id를 "모델 디테일 뷰(model detail view)"에 전달하기 위해 (레코드를 표시하기 위한 작업을 할) URL 매퍼를 만들 필요가 있습니다 . 위의 `reverse()` 함수는 알맞은 포맷의 URL을 생성하기 위해서 URL 매퍼를(위 경우에선 'model-detail-view'라고 명명됨) "반전" 시킬 수 있습니다.

 물론 이것이 작동하기 위해선 URL 매핑, 뷰, 그리고 탬플릿을 작성해야 합니다!

또한 원하는 메소드를 정의해서 (그들이 어떤 변수도 가지고 있지 않다면) 코드나 탬플릿에서 불러올 수 있습니다.



### 모델 관리(management)

모델 클래스들을 정의한 이후엔 클래스들을 사용해서 레코드들을 생성, 업데이트, 또는 삭제할 수 있고, 모든 레코드 또는 레코드의 특정 하위 집합을 가져오기 위해 쿼리를 실행할 수 있습니다. 튜토리얼에서 뷰를 정의할 때 그 방법을 보여줄 것이지만, 아래에 간략한 요약이 있습니다.

#### 레코드의 생성과 수정

레코드를 생성하려먼 모델의 인스턴스를 정의하고 `save()`를 호출할 수 있습니다.

```python
# Create a new record using the model's constructor.
record = MyModelName(my_field_name="Instance #1")

# Save the object into the database.
record.save()
```



> **Note**: 만약 당신이 어떤 필드도 `primary_key`를 선언하지 않았다면, 새로운 레코드는 자동적으로 `id`라는 필드 이름을 가진 `primary_key`가 주어지게 됩니다. 위의 레코드를 저장한 후 이 `id` 필드를 쿼리할 수 있는데, 1의 값을 가질 겁니다.

이 새로운 레코드 안의 필드에 점 구문(.)을 사용해서 접근하여 값을 변경할 수 있습니다. 수정된 값들을 데이터베이스에 저장하기 위해 `save()`를 호출해야 합니다.

```python
# Access model field values using Python attributes.
print(record.id) # should return 1 for the first record.
print(record.my_field_name) # should print 'Instance #1'

# Change record by modifying the fields, then calling save().
record.my_field_name = "New Instance Name"
record.save()
```



#### 레코드 검색하기

모델의 객체(`objects`) 속성(기본 클래스에서 제공됨)을 사용하여 특정 기준과 일치하는 레코드를 검색할 수 있습니다.

> **주의:** "추상(abstract)" 모델과 필드 이름을 사용하여 레코드들을 검색하는 방법을 설명하는 것은 조금 혼란스러울 수 있습니다. 아래에서는 `title`과 `genre` 필드가 있는 `Book` 모델을 참조하겠습니다. 이 때 `genre`는 또한 `name`이라는 단일 필드를 가지고 있는 모델입니다.

우리는 `objects.all()`을 사용하여 모델의 모든 레코드들을 `QuerySet`으로 가져올 수 있습니다. `QuerySet`은 반복가능한(iterable) 객체이며, 이것은 반복/루프할 수 있는 많은 객체들을 포함하고 있다는 의미입니다.

```python
all_books = Book.objects.all()
```



 장고의 `filter()`는 반환된 `QuerySet`이 지정된 문자(**text**) 또는 숫자(**numeric**) 필드를 특정한 기준에 맞추어 필터링할 수 있게 합니다. 예를 들어서, "wild"를 제목 안에 포함하는 책들을 필터링하여 그 숫자를 세어 보려면, 아래와 같이 하면 됩니다.

```python
wild_books = Book.objects.filter(title__contains='wild')
number_wild_books = Book.objects.filter(title__contains='wild').count()
```



기준이 될 필드와 타입은 필터 매개 변수 이름에서 정의됩니다. 다음 포맷을 이용해서요: `field_name__match_type` (주의: 위의 `title`과 `contains` 사이 밑줄은 두 개입니다). 위에서 우리는 대소문자를 구분하여 `title`을 필터링합니다. 다른 많은 유형의 일치 방법이 있습니다: `icontains`(대소문자를 구분하지 않음), `iexact`(대소문자를 구분하지 않는 정확히 일치), `exact`(대소문자를 구분하는 정확한 일치) 그리고 `in`, `gt`(보다 더 큰(greater than)), `startswith` 등등이 있습니다. 모든 일치방법 목록은 여기([full list is here](https://docs.djangoproject.com/en/2.0/ref/models/querysets/#field-lookups)) 있습니다.

어떤 경우엔 일대다 관계를 다른 모델에 정의하는 필드를 필터링해야 할 때도 있습니다(예:`ForeignKey`). 이 경우에 추가적인 이중 밑줄을 사용하여 관련 모델 안의 필드에 "색인(index)"할 수 있습니다. 예를 들어 특정한 장르 패턴을 가진 책들을 필터링하려면, 아래와 같이 `genre` 필드를 통해서 `name`에 색인(index)해야 할 겁니다.

```python
# Will match on: Fiction, Science fiction, non-fiction etc.
books_containing_genre = Book.objects.filter(genre__name__icontains='fiction')
```



 **주의**: 밑줄(__)을 사용하여 원하는 만큼 다양한 레벨의 관계(`ForeignKey`/`ManyToManyField`)를 탐색할 수 있습니다. 예를 들어서 추가적인 "cover" 관계를 사용하여 정의된 다른 타입의 `Book`은 다음과 같은 매개 변수 이름을 가질겁니다 :`type__cover__name__exact='hard'.`

관련된 모델의 역방향 검색, 필터 변경, 값의 더 작은 집합 반환하기 등 쿼리로 할 수 있는 일들은 더욱 많이 있습니다. 더 많은 정보를 보려면 [Making queries](https://docs.djangoproject.com/en/2.0/topics/db/queries/) (장고 문서)를 참고하세요.



## LocalLibrary 모델 정의하기

이 섹션에서는 도서관을 위한 모델을 정의하기 시작할 겁니다. models.py ( /locallibrary/catalog/에 있음)파일을 여세요. 페이지 상단의 표준 코드(boilerplate)는 우리들의 모델이 상속받을 모델 기본 클래스 `models.Model`을 포함하는 models 모듈을 가져옵니다.

```python
from django.db import models

# Create your models here.
```



### 장르(Genre) 모델

아래의 장르(`Genre`) 모델 코드를 복사해서 `models.py` 파일에 붙여넣기 하세요. 이 모델은 책 카테고리에 관한 정보를 저장하는데 사용됩니다 — 예를 들어 소설인지, 논픽션인지, 로맨스인지 군사 역사물인지 등등. 위에서 말했던 것 처럼, 우리는 장르를 자유 텍스트나 선택 목록으로 만들지 않고 모델을 이용해 만들었습니다. 가능한 값들이 하드코딩되기 보다는 데이터베이스를 통해 관리되도록 하기 위해서입니다.

```python
class Genre(models.Model):
    """Model representing a book genre."""
    name = models.CharField(max_length=200, help_text='Enter a book genre (e.g. Science Fiction)')

    def __str__(self):
        """String for representing the Model object."""
        return self.name
```



`모델은 하나의 CharField` 필드(`name`)을 가지고 있습니다. 이것은 장르의 이름을 나타냅니다 . 이것은 200자로 제한되어 있고 `help_text`를 갖고 있습니다. 모델의 마지막에서 우리는 `__str__()` 메소드를 선언합니다. 이 메소드는 특정한 레코드에 의해 정의된 장르의 이름을 단지 반환합니다. 아무런 자세한 이름(verbose name)도 정의되지 않았기 때문에, 필드는 폼(form)에서 `Name`으로 호출(call)될 겁니다.



### 책(Book) 모델

아래의 책(Book) 모델을 복사해서 파일의 아래에 붙여넣기 하세요. 책 모델은 일반적으로 사용 가능한 책에 대한 모든 정보들을 보여주지만, 대여 가능한 특정한 물리적 "인스턴스(instance)"나 "복사본(copy)"은 보여주지 않습니다. 모델은 `CharField`를 사용하여 책의 `title`과 `isbn`을 나타냅니다(`isbn`이 이름을 지정하지 않은 첫 번째 매개변수를 사용하여 라벨을 "ISBN"으로 지정한 것에 주목하세요. 만약 그러지 않았다면 기본 라벨은 "Isbn"이었을 것입니다). 텍스트가 상당히 길 것이기 때문에 `summery`에는 `TextField`를 사용합니다.

```python
from django.urls import reverse # Used to generate URLs by reversing the URL patterns

class Book(models.Model):
    """Model representing a book (but not a specific copy of a book)."""
    title = models.CharField(max_length=200)
    author = models.ForeignKey('Author', on_delete=models.SET_NULL, null=True)

    # Foreign Key used because book can only have one author, but authors can have multiple books
    # Author as a string rather than object because it hasn't been declared yet in the file.
    summary = models.TextField(max_length=1000, help_text='Enter a brief description of the book')
    isbn = models.CharField('ISBN', max_length=13, help_text='13 Character <a href="https://www.isbn-international.org/content/what-isbn">ISBN number</a>')

    # ManyToManyField used because genre can contain many books. Books can cover many genres.
    # Genre class has already been defined so we can specify the object above.
    genre = models.ManyToManyField(Genre, help_text='Select a genre for this book')

    def __str__(self):
        """String for representing the Model object."""
        return self.title

    def get_absolute_url(self):
        """Returns the url to access a detail record for this book."""
        return reverse('book-detail', args=[str(self.id)])
```



장르는 책이 여러 개의 장르를 가지고, 장르도 여러 개의 책을 가질 수 있는 다대다 필드(`ManyToManyField`)입니다. 저자는 `ForeignKey` 로 선언됩니다. 따라서 각각의 책은 하나의 저자만 가질 수 있지만, 저자는 여러 개의 책들을 가질 수 있습니다(실제로는 책이 여러 명의 작가를 가질 수 있지만, 이 구현에서는 아닙니다!).

 두 필드 타입들 안에서 관련된 모델 클래스는 모델 클래스나 관련된 모델의 이름을 포함하는 문자열을 사용하여 이름없는 첫 번째 매개 변수로 선언됩니다. 연관된 클래스가 참조되기 전에 파일 안에서 아직 정의되지 않았다면 모델의 이름을 문자열로 사용해야 합니다! 저자 ( `author` )필드에서 관심을 가져야 할 다른 변수는 `null=True`와 `on_delete=models.SET_NULL`입니다. `null=True`는 어떤 저자도 선택되지 않았다면 데이터베이스에 `Null` 값을 저장하도록 하고, `on_delete=models.SET_NULL`은 관련된 저자(author) 레코드가 삭제되었을 때 저자(author)의 값을 `Null`로 설정할 겁니다.

모델은 또한  `Book` 레코드를 나타내는 책의 `title` 필드를 사용하여 `__str__()` 를 정의합니다. 마지막 메소드 `get_absolute_url()` 은 이 모델의 세부 레코드에 접근하는 데에 사용될 수 있는 URL을 반환합니다 (이것이 작동하도록 하기 위해선 `book-detail`이라는 이름의 URL 매핑을 정의하고, 관련 뷰와 탬플릿을 정의해야 합니다).



### 책 인스턴스(BookInstance) 모델

 다음으로, `BookInstance` 모델(아래에 있는)을 다른 모델들 아래에 복사하세요. `BookInstance` 은 누군가 빌릴지도 모를 특정한 책의 복사본을 나타냅니다. 그리고 복사본이 사용 가능한지 여부, 언제 되돌려받을 수 있을지, "출판사(imprint)" 또는 버전 세부 사항, 그리고 도서관 안에 있는 책의 고유 id에 대한 정보를 포함합니다.

 이제 몇몇 필드와 메소드는 친숙할 겁니다. BookInstance 모델은 아래를 사용합니다

- `ForeignKey` : 연관된 `Book` 을 식별하기 위해(각각의 책은 많은 복사본을 가질 수 있지만, 복사본은 하나의 `Book`만을 가질 수 있음).
- `CharField` : 책의 출판사(imprint)(특정한 발간일)을 나타내기 위해.

```python
import uuid # Required for unique book instances

class BookInstance(models.Model):
    """Model representing a specific copy of a book (i.e. that can be borrowed from the library)."""
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, help_text='Unique ID for this particular book across whole library')
    book = models.ForeignKey('Book', on_delete=models.SET_NULL, null=True)
    imprint = models.CharField(max_length=200)
    due_back = models.DateField(null=True, blank=True)

    LOAN_STATUS = (
        ('m', 'Maintenance'),
        ('o', 'On loan'),
        ('a', 'Available'),
        ('r', 'Reserved'),
    )

    status = models.CharField(
        max_length=1,
        choices=LOAN_STATUS,
        blank=True,
        default='m',
        help_text='Book availability',
    )

    class Meta:
        ordering = ['due_back']

    def __str__(self):
        """String for representing the Model object."""
        return f'{self.id} ({self.book.title})'
```



추가적으로 몇 가지 새로운 타입의 필드를 선언합니다:

-  `UUIDField` 는 `id` 필드가 이 모델의 `primary_key` 로 설정되는 데 사용됩니다. 이 타입의 필드는 각 인스턴스에 전역적으로 고유한 값을 할당합니다 (도서관에서 찾을 수 있는 모든 책 마다 하나씩).
- `DateField` 는 `due_back` (만기일) 날짜에 사용됩니다 (책이 빌려지거나 유지 보수된 이후 사용할 수 있을 것으로 예상되는 날짜). 이 값은 `blank` 나 `null` 이 될 수 있습니다(책을 사용 할 수 있는 경우 필요). 메타데이터 모델 (`Class Meta`) 은 레코드들이 쿼리에서 반환되었을 때 레코드들을 정렬하기 위해서 이 필드를 사용합니다 .
- `status` 는 선택/선택 목록(choice/selection list)을 정의하는 `CharField` 입니다. 보시다시피, 우리는 열쇠-값(key-value) 쌍의 튜플(tuple)을 포함하는 튜플을 정의해서 choices 인자에 전달합니다. 열쇠/값(key/value) 쌍에서 값(value)은 사용자가 선택할 수 있는 표시값인 반면, 열쇠(key)는 그 옵션이 선택되었을 때 실제로 저장되는 값입니다. 또한 책이 선반에 저장되기 전에는 사용할 수 없으므로 기본값인 'm'(유지 관리, maintenanace)을 설정했습니다.

 모델 ` __str__()` 은 그것의 고유한 id 그리고 연관된 `Book`의 제목을 조합하여 `BookInstance` 객체를 나타냅니다.

**주의**: 조금의 파이썬:

- 파이썬 3.6에서부터 문자열 보간 구문을 쓸 수 있습니다 (f-strings로 잘 알려진): `f'{self.id} ({self.book.title})'`.
- 이 튜토리얼의 옛 버전에서는 [formatted string](https://www.python.org/dev/peps/pep-3101/) 구문을 사용했고, 이것 또한 파이썬 안에 문자열을 형식화(formatting)하는 유효한 방법입니다 (예 : `'{0} ({1})'.format(self.id,self.book.title)`).



### 저자(Author) 모델

 **models.py** 안에 작성된 코드 아래에 `Author` 모델을 복사 붙여넣기 하세요 (아래에 있습니다).

 이제 모든 필드/메소드들이 익숙할 겁니다. 모델은 저자를 이름(first name), 성(last name), 생일, 그리고 (선택적으로) 사망일을 가진다고 정의합니다. 기본적으로 `__str__()` 는 name을 첫째로 성(last name), 그 다음 이름(first name)이 오는 순서로 반환합니다. `get_absolute_url()` 메소드는 개별 저자를 나타내기 위한 URL을 가져오기 위해 `author-detail` URL 매핑을 반대로 합니다.

```python
class Author(models.Model):
    """Model representing an author."""
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField(null=True, blank=True)
    date_of_death = models.DateField('Died', null=True, blank=True)

    class Meta:
        ordering = ['last_name', 'first_name']

    def get_absolute_url(self):
        """Returns the url to access a particular author instance."""
        return reverse('author-detail', args=[str(self.id)])

    def __str__(self):
        """String for representing the Model object."""
        return f'{self.last_name}, {self.first_name}'
```



## 데이터베이스 마이그레이션 재실행하기

 모든 모델이 생성되었습니다. 이제 데이터베이스 migration을 재실행하여 모델들을 데이터베이스에 추가하세요.

```cmd
python manage.py makemigrations
python manage.py migrate
```



## 도전과제 - 언어(Language) 모델

지역 후원자가 다른 언어로 저술된 새로운 책들을 후원한다고 생각해 보세요(아마도, 프랑스어?). 도전과제는 이것이 도서관 웹사이트에서 이것을 가장 잘 나타낼 수 있는 방법을 찾아내고, 모델에 추가하는 것입니다.

고려해야 할 사항들:

- "언어"(language)는 `Book`, `BookInstance`, 아니면 어떤 다른 객체와 연관되어야 할까요?
-  서로 다른 언어들은 모델로 나타내어야 할까요? 아니면 자유 텍스트 필드?그것도 아니라면 하드-코딩된 선택 리스트로 나타내어야 할까요?

결정을 내린 후에, 필드를 추가하세요. 우리가 선택한 것은 여기([here](https://github.com/mdn/django-locallibrary-tutorial/blob/master/catalog/models.py)) 깃허브에서 볼 수 있습니다.



## 요약

 이 글에서 우리는 모델이 어떻게 정의되는지 배웠고, 이 정보를 지역 도서관 웹사이트를 위한 적절한 모델을 설계하고 구현하기 위해 사용했습니다.





# Django Tutorial Part 4: Django admin site

[LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website의 모델을 만들었으므로, 우리는 Django Admin 을 이용해서 "실제" book data를 추가할 것입니다. 첫째로 우리는 당신에게 관리자 사이트에 모델들을 등록는 방법과, 이후 어떻게 로그인해서 데이터를 만들지를 보여줄 것입니다. 끝으로는 Admin site를 더 개선할 수 있는 방법들에 대해서 알아볼 것입니다.

| 사전 준비: | 첫째로 완료하세요: [Django Tutorial Part 3: Using models](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Models). |
| :--------- | ------------------------------------------------------------ |
| 목적:      | 장고 관리자 사이트의 이득과 한계를 이해하고, 모델을 위한 여러 레코드들을 생성하기. |

## 개요

 장고의 관리자 어플리케이션은 모델을 사용하여 당신이 만들고, 보고, 업데이트하고, 그리고 삭제하는 데에 사용할 수 있는 사이트 영역을 자동적으로 만드는 데에 사용할 수 있습니다. 이는 당신이 시간을 많이 절약할 수 있도록 돕고, 모델을 쉽게 테스트 할 수 있게 하며 당신이 정확한 데이터를 가지고 있다는 느낌을 가질수 있도록 돕습니다. 관리자 어플리케이션은 또한 웹 사이트의 유형에 따라 production의 데이터를 관리하는 데 유용합니다. 모델 중심 접근 방식은 모델에 관한 많은 불필요한 세부 사항을 사용자들에게 노출하는, 모든 사용자들에게 가장 좋은 방식이라고 말할 수 없기 때문에, 장고 프로젝트는 internal 데이터 관리만을(즉, 관리자, 또는 당신의 조직 안에 있는 사람들을 위한 사용만을) 위해서 사용하는 것을 추천합니다.

 웹사이트 안에 관리자 어플리케이션을 포함시키기 위해 요구되는 모든 설정은 [created the skeleton project](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website)를 생성했을 때 자동적으로 완료됩니다(실제 종속성에 대한 정보는, 여기 [Django docs here](https://docs.djangoproject.com/en/2.0/ref/contrib/admin/)를 확인하세요). 결과적으로, 모델을 관리자 어플리케이션에 추가하기 위해서 **꼭 해야만** 하는 것은 그것들을 등록하는(register) 것입니다. 이 글의 마지막에서 모델 데이터를 더 잘 나타내기 위한 관리자 영역의 추가적인 설정을 짧게 보여드리겠습니다.

모델을 등록한 뒤로는 새로운 "superuser"를 만들어서 사이트에 로그인을 하며, 이후에는 books, 저자, book instances 그리고 장르를 만들 것입니다. 이것들은 다음 튜토리얼에서 만들기 시작할 뷰와 템플릿을 테스트할 때 유용할 것입니다.



## Models 등록하기

첫째, catalog 어플리케이션 안의 **admin.py** 파일을 여세요 (**/locallibrary/catalog/admin.py**). 다음과 같을 겁니다 — `django.contrib.admin`을 이미 imoprt하고 있다는 것에 주의하세요:

```python
from django.contrib import admin

# Register your models here.
```



 파일의 하단에 아래 텍스트를 복사 붙여넣기 해서 모델을 등록(register)하세요. 이 코드는 모델들을 import하고 그것들을 등록하기 위해 `admin.site.register` 를 호출합니다.

```python
from catalog.models import Author, Genre, Book, BookInstance

admin.site.register(Book)
admin.site.register(Author)
admin.site.register(Genre)
admin.site.register(BookInstance)
```



 **주의**: 책의 언어를 나타내기 위한 모델을 생성하는 도전과제를 수행했다면 ([see the models tutorial article](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Models)), 그 모델도 import 한 후 등록하세요!

 이것은 모델이나 모델들을 사이트에 등록(register)하는 가장 간단한 방법입니다. 관리자 사이트는 커스터마이즈 범위가 넓고, 아래에서 모델을 등록하는 다른 방법들에 관해 다루겠습니다.



## Superuser 만들기

 관리자 사이트에 로그인하기 위해서는, 직원(*Staff)* 상태가 활성화 된 사용자 계정이 있어야 합니다. 레코드들을 보고 생성하기 위해서는 이 사용자가 모든 객체들을 관리하기 위한 허가가 있어야 합니다. 사이트에 대한 모든 접속 권한과 필요한 허가를 가진 "superuser" 계정을 manage.py를 사용해서만들 수 있습니다. 

superuser를 생성하기 위해 **manage.py**와 같은 경로 안에서 아래 명령어를 호출하세요. username, 이메일 주소, 그리고 강력한 비밀번호를 입력해야 할 것입니다.

```cmd
python manage.py createsuperuser

# wonsool // kang0192(O)P
```



이 명령어가 완료되면 새로운 superuser가 데이터베이스에 추가되었을 것입니다. 이제 로그인을 테스트할 수 있도록 개발 서버를 재시작 하세요:

```
python manage.py runserver
```



## 관리자 계정에 로그인하기

 사이트에 로그인하기 위해서는, /admin URL(예: [http://127.0.0.1:8000/admin](http://127.0.0.1:8000/admin/))을 열어서 새로운 superuser의 userid와 password를 입력하세요(세부 정보를 입력한 후에는 로그인 페이지로 리디렉션되고 /admin URL로 돌아갑니다).

 이곳은 설치된 어플리케이션에 따라 그룹지어진 모든 모델들을 보여줍니다. 모델 이름을 클릭해서 모든 관련 레코드들의 목록을 볼 수 있고, 그 목록들을 클릭하여 수정할 수 있습니다. 또한 모델 오른쪽의 **Add** 링크를 클릭하여 그 타입의 레코드를 곧바로 생성할 수 있습니다.

![Admin Site - Home page](./images/admin_home.png)

 Books 모델 오른쪽의 **Add** 링크를 클릭해서 새로운 책을 만드세요(아래와 같은 화면이 보일 것입니다). 각 필드의 제목, 사용된 위젯의 타입 그리고 `help_text`(있는 경우)가 모델 안에서 지정한 값과 일치하는 방식에 유의하세요. 

 필드에 값들을 입력하세요. 해당 필드 옆에 있는 **+** 버튼을 눌러 새로운 저자나 장르를 생성할 수 있습니다 (이미 생성했다면 목록에서 값을 선택하세요). 모두 완료했다면 레코드를 저장하기 위해 **SAVE**, **Save and add another** 또는 **Save and continue editing**을 클릭할 수 있습니다.  

![Admin Site - Book Add](./images/admin_book_add.png)

> **주의**: 이 지점에서 어플리케이션에 몇 가지 책들, 저자 그리고 장르(예: 판타지)를 추가하면서 시간을 보내보세요. 각각 저자와 장르에 서로 다른 두 가지 책이 포함되어 있는지 확인하세요 (이렇게 하면 나중에 리스트(list)와 디테일 뷰(detail view)들을 구현할 때 그것들을 더욱 흥미롭게 만들어 줍니다).

 책을 추가했다면, 상단 북마크 안의 **Home** 링크를 클릭해서 관리자 페이지로 되돌아가세요. 그리고 **Books** 링크를 클릭해서 책들의 현재 목록을 나타내세요(아니면 다른 링크를 클릭해서 다른 모델의 목록을 보세요). 이제 몇 가지 책을 추가했으니, 목록은 아래 스크린샷과 비슷하게 보일 겁니다. 각 책의 제목이 보입니다; 이것은 지난 글에서 다뤘던 Book 모델의 `__str__()` 메소드가 반환한 값입니다.

![Admin Site - List of book objects](./images/admin_book_list.png)

 이 리스트에서 원하지 않는 책의 왼쪽에 있는 체크박스를 선택하고, Action 드랍-다운 목록에서 delete... 동작을 선택한 후, **Go** 버튼을 클릭해 책을 삭제할 수 있습니다. 또한 **ADD BOOK** 버튼을 클릭해서 새로운 책들을 추가할 수 있습니다. 

 링크 안의 책 이름을 선택해서 책을 편집할 수 있습니다. 아래에 보여지는 책 모델의 편집 페이지는 "Add" 페이지와 거의 동일합니다. 페이지의 제목 (*Change book*)과 **Delete**, **HISTORY** 그리고 **VIEW ON SITE** 버튼들의 추가가 큰 차이점입니다(마지막 버튼은 모델에서 `get_absolute_url()` 메소드를 정의했기 때문에 나타납니다).

![Admin Site - Book Edit](./images/admin_book_modify.png)

이제 **Home** 페이지로 되돌아가서 (홈 링크를 사용해서) **Author** 와 **Genre** 목록들을 보세요 — 새로운 책들을 추가하면서 이미 몇 가지 목록들이 있겠지만, 조금 더 추가해도 됩니다.

 아직 가지고 있지 않은 것은 Book Instance들입니다. 왜냐하면 Books에서 만들어지지 않았기 때문이죠( `BookInstance`에서 `Book`을 만들 수 있긴 하지만 — 이것은 `ForeignKey` 필드의 특성입니다). Home 페이지로 되돌아가서 연관된 **추가(Add)**버튼을 눌러 아래의 Add book Instance 화면을 나타내세요. 크고 전역적으로 고유한 id에 주의하세요. 이것은 도서관에서 한 권의 책을 개별적으로 식별하는 데 사용할 수 있습니다.

![Admin Site - BookInstance Add](./images/admin_bookinstance_add.png)

 각각의 책마다 여러 개의 레코드를 만드세요. 상태(Status)를 최소한 몇 개의 레코드는 대여 가능(Available)로 설정하고, 나머지는 대여 중(On loan)으로 설정하세요. 만약 상태가 대여 불가능(**not** Available)이면, 만기 날짜도 같이 설정하세요.

이게 끝입니다! 관리자 사이트를 어떻게 설정하고 사용하는지 배웠습니다. 또한 `Book`, `BookInstance`, `Genre`, 그리고 `Author` 에 대한 레코드들을 생성했고, 뷰와 탬플릿을 생성할 때 이 레코드들을 사용할 수 있을 것입니다.



## 추가 설정(Advanced configuration)

 장고는 등록된(registered) 모델의 정보를 사용하여 기본적인 관리자 사이트를 만드는 일을 꽤 잘합니다:

- 각각의 모델은 모델의 `__str__()` 메소드로 생성된 문자열로 식별되는, 그리고 편집을 위한 세부 정보(detail views)/양식(forms)에 링크되어 있는 개별적인 레코드들의 목록을 가지고 있습니다. 기본적으로, 이 화면은 레코드에 대한 대량 삭제 작업을 수행할 수 있는 액션 메뉴를 상단에 가지고 있습니다.
- 레코드의 편집과 추가를 위한 모델 디테일 레코드 양식들은 모델 안의 모든 필드를 포함하고 있고, 그것들의 선언 순서에 따라 수직으로 배치되어 있습니다.  

 사용하기 더욱 편하도록 인터페이스를 추가적으로 사용자화 할 수 있습니다. 당신이 할 수 있는 몇 가지 일은:

- 목록 뷰(List views):
  - 각각의 레코드에 표시되는 추가적인 필드/정보를 추가하기. 
  - 날짜나 다른 선택 값(예: 책의 대여 상태)에 기초해서 어떤 레코드들이 목록지어질 지 선택하는 필터들 추가하기.
  - 목록 뷰(list views) 안의 동작 메뉴(actions menu)에 추가적인 옵션을 추가하고 이 메뉴가 양식 위의 어디에 보여져야 할 지 선택하기.
- 세부 뷰(Detail views):
  - 표시할(혹은 제외할)필드, 따를 순서, 그루핑, 편집 가능할지 여부, 사용될 위젯, 방향(orientation) 등등을 선택하기.
  - 인라인 편집(inline editing)기능을 넣기 위해 관련된 필드들을 레코드에 추가하기(예: 책 레코드의 저자 레코드를 생성하는 동안 책 레코드를 추가하고 편집할 수 있는 기능을 추가하기).

 이 글에서는 LocalLibrary의 인터페이스를 향상시키기 위한 몇 가지 변경점을 알아볼 겁니다. 그것들은 `Book`과 `Author` 모델 목록에 더 많은 정보를 추가하고, 그리고 그것들의 편집 보기(edit views)의 레이아웃을 향상시키는 것을 포함합니다. `Language` 와 `Genre` 모델은 변경하지 않을 겁니다. 그들은 각각 하나의 필드만을 갖고 있기 때문에 변경하는 것에 대한 이득이 없습니다!

 모든 관리자 사이트 사용자화(customisation) 선택들(choices)의 완벽한 레퍼런스(reference)를 [The Django Admin site](https://docs.djangoproject.com/en/2.0/ref/contrib/admin/)(장고 문서)에서 찾을 수 있습니다.

### ModelAdmin 클래스 등록하기

관리자 인터페이스에서 모델이 보여지는 방식을 바꾸고 싶다면 [ModelAdmin](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#modeladmin-objects) 클래스(레이아웃을 나타내는)를 정의한 후 모델 안에 등록합니다.

 `Author` 모델부터 시작해 봅시다. catalog 어플리케이션의 **admin.py** 파일을 여세요 (**/locallibrary/catalog/admin.py**). 원래 있던 `Author` 모델에 대한 등록(registration)을 주석처리(#를 앞에 붙여서) 하세요:

```python
# admin.site.register(Author)
```



이제 아래와 같이 새로운 `AuthorAdmin` 과 그것의 등록(registration)을 추가하세요.

```python
# Define the admin class
class AuthorAdmin(admin.ModelAdmin):
    pass

# Register the admin class with the associated model
admin.site.register(Author, AuthorAdmin)
```



 이제 우리는` Book` 그리고 `BookInstance`를 위한 `ModelAdmin` 클래스를 추가할 겁니다. 또다시 원본 등록(registrations)를 주석처리 해야 합니다:

```python
# admin.site.register(Book)
# admin.site.register(BookInstance)
```



이제 새로운 모델들을 생성하고 등록하기 위해서; 이것의 설명을 위해, 우리는 모델들을 등록(register)하기 위해 `@register` 데코레이터(decorator)를 대신 사용합니다(이것은 `admin.site.register()` 구문과 정확히 똑같은 일을 합니다):

```python
# Register the Admin classes for Book using the decorator
@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    pass

# Register the Admin classes for BookInstance using the decorator
@admin.register(BookInstance)
class BookInstanceAdmin(admin.ModelAdmin):
    pass
```



 현재 모든 관리자 클래스들은 비어있기 때문에("`pass"`를 보세요) 관리자 행동은 바뀌지 않았을 것입니다! 이제 우리는 모델 고유의 관리자 행동(behaviour)을 정의하기 위해 이것들을 확장할 수 있습니다.



### 목록 뷰(list view)들을 설정하기

현재 Locallibrary는 모델의 `__str__()` 메소드에서 생성되는 객체 이름을 사용하여 모든 저자들을 목록짓습니다. 이것은 단지 몇 명의 저자만 있을 때는 괜찮지만, 수많은 저자가 있을 때는 겹치는 이름의 서로 다른 저자가 있을 겁니다. 그것들을 구분하거나, 아니면 그저 각각의 저자마다 흥미로운 정보를 보여주고 싶다면, [list_display](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.ModelAdmin.list_display) 를 사용해서 view에 추가적인 필드를 추가할 수 있습니다. 

아래 코드로 `AuthorAdmin` 클래스를 대체하십시오. 목록에 선언될 이름들은 필요한 순서대로 튜플(tuple)로 선언됩니다, 아래에 보시는 것 처럼요(이것들은 원래 모델에서 특정된 것과 같은 이름들입니다).

```python
class AuthorAdmin(admin.ModelAdmin):
    list_display = ('last_name', 'first_name', 'date_of_birth', 'date_of_death')
```



 이제 웹사이트 안의 저자 목록으로 이동하세요. 위의 필드들이 아래와 같이 보여질 것입니다:

![Admin Site - Improved Author List](./images/admin_improved_author_list.png)

우리의 `Book` 모델을 위해서 추가적으로 `author` 그리고 `genre`를 표시해 봅시다. `author`는 `Foreignkey` 필드 관계(일-대-다)이기 때문에, 관련된 레코드의 `__str__()` 값에 의해 나타내어질 것입니다. 아래의 버전으로 `BookAdmin` 클래스를 대체하세요.

```python
class BookAdmin(admin.ModelAdmin):
    list_display = ('title', 'author', 'display_genre')
```



 불행하게도 `genre` 필드는 `ManyToManyField`이기 때문에 `list_display`에 직접적으로 특정할 수 없습니다.(거대한 데이터베이스 접근 "비용"이 발생할 수 있기 때문에 장고는 이것을 방지합니다). 대신 정보를 문자열로 받기 위해서 `display_genre` 함수를 정의할 겁니다(이것이 위에서 호출한 함수입니다; 아래에서 함수를 정의하겠습니다).

> **주의**: `genre` 를 가져오는 것은 좋은 생각이 아닙니다. 왜냐하면 데이터베이스 작동의 "비용"이 너무 높기 때문입니다. 모델 안의 호출 함수들이 다른 이유로도 굉장히 유용하기 때문에 — 예를 들자면 리스트 안의 모든 항목에 delete 링크를 추가하는 것 처럼요.

아래 코드를 `Book` 모델(**catalog/models.py**) 안에 추가하세요. 이것은 `genre` 필드의 첫 세 가지 값들의 문자열을 생성합니다(만약 존재한다면요) 그리고 이 메소드를 위해 관리자 사이트에서 사용될 수 있는 `short_description` 을 만듭니다.

```python
    def display_genre(self):
        """Create a string for the Genre. This is required to display genre in Admin."""
        return ', '.join(genre.name for genre in self.genre.all()[:3])

    display_genre.short_description = 'Genre'
```

`class Book 의 member 로 추가`



 모델을 저장하고 관리자를 업데이트 했다면, 웹사이트를 열어서 Books 목록 페이지로 이동하세요; 아래와 같은 책 리스트가 있을 것입니다:

![Admin Site - Improved Book List](./images/admin_improved_book_list.png)

`Genre` 모델 (그리고 `Language` 모델도, 정의했다면) 은 단일 필드를 갖고 있기 때문에, 추가적인 필드를 보여주기 위해 추가적인 모델을 생성하는 것은 의미가 없습니다.

> **주의**: 최소한 상태 그리고 예상되는 반납일을 보여주는 `BookInstance` 모델 목록을 업데이트 해봅시다 . 이것은 이 글의 마지막에 도전 과제로 추가되어 있습니다!



### 목록 필터 추가하기

 목록 안에 있는 항목들이 아주 많다면, 그 중에 어떤 항목들만이 표시될 지 정할 수 있는 필터를 추가하는 것이 유용합니다. 이것은 `list_filter` 속성 안에서 필드들을 목록지어서 완료됩니다. 현재 `BookInstanceAdmin` 클래스를 아래 코드 조각으로 대체하십시오.

```python
class BookInstanceAdmin(admin.ModelAdmin):
    list_filter = ('status', 'due_back')
```

`catalog/admin.py`



 목록 뷰(list view)는 이제 오른쪽에 필터 상자를 갖고 있을 겁니다. 값을 필터링하기 위해 날짜와 상태를 선택하는 방법에 주의하세요:

![Admin Site - BookInstance List Filters](./images/admin_improved_bookinstance_list_filters.png)

### 세부 보기 레이아웃(detail view layout) 조직하기

 기본적으로, detail view들은 모든 필드들을 모델에 선언된 순서대로, 수직적으로 배치합니다. 당신은 선언 순서, 표시될(혹은 제외될) 필드들, 섹션들이 정보들을 조직하는 데 사용될 지 여부, 필드들이 수직적 또는 수평적으로 표시될 지 여부 그리고 심지어 관리자 양식에서 어떤 편집 위젯이 사용될 지 까지 변경할 수 있습니다.

**주의**: LocalLibrary 모델은 상대적으로 간단하기 때문에 레이아웃을 변경할 큰 필요성이 없습니다; 그러나 방법을 보여드리기 위해, 몇 가지 변경점을 만들 것입니다.

#### 어떤 필드들이 보여지고 배치될 지 제어하기

아래와 같이 `fields` 줄(굵은 글씨)을 추가하기 위해 `AuthorAdmin` 클래스를 업데이트 하세요:

```python
class AuthorAdmin(admin.ModelAdmin):
    list_display = ('last_name', 'first_name', 'date_of_birth', 'date_of_death')
    fields = ['first_name', 'last_name', ('date_of_birth', 'date_of_death')]
```



`fields` 속성은 양식에 보여져야 할 필드들만을 순서대로 목록짓습니다. 필드들은 기본적으로 수직적으로 표시되지만, 추가적으로 그것들을 튜플 안에 그룹짓는다면 수평적으로 표시됩니다(위의 "date" 필드처럼요).

`('date_of_birth', 'date_of_death') 에 의해 수평으로 표시됨 - wonsool`

웹사이트 안에서 저자 세부 사항 뷰로 이동하세요 — 이제 아래와 같이 보일겁니다:

![Admin Site - Improved Author Detail](./images/admin_improved_author_detail.png)

> **주의**: 당신은 또한 양식(form)에서 제외되어야 할 속성들의 목록을 선언하기 위해 `exclude` 속성을 사용할 수 있습니다 (모델 안의 다른 모든 속성들이 표시될 겁니다). 



#### 세부 뷰 구역 나누기(Sectioning the detail view)

[fieldsets](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.ModelAdmin.fieldsets) 속성을 사용하여, 세부 양식 안의 연관된 모델 정보를 그룹화하기 위해 "sections"를 추가할 수 있습니다.

 `BookInstance` 안에서, 우리는 책이 무엇인지 (i.e. `name`, `imprint`, and `id`) 그리고 이것을 언제 빌릴 수 있는지 (`status`, `due_back`)에 대한 정보를 가지고 있습니다. 우리는 `BookInstanceAdmin` 클래스에 굵게 표시된 텍스트를 추가하여 이것들을 각각 다른 구역에 추가할 수 있습니다. 

```python
@admin.register(BookInstance)
class BookInstanceAdmin(admin.ModelAdmin):
    list_filter = ('status', 'due_back')

    fieldsets = (
        (None, {
            'fields': ('book', 'imprint', 'id')
        }),
        ('Availability', {
            'fields': ('status', 'due_back')
        }),
    )
```



각각의 섹션엔 고유한 제목(또는 제목을 원하지 않을 경우, `None`) 그리고 사전의 필드와 관련된 튜플이 있습니다 — 양식(format)은 묘사하기 복잡하지만, 바로 위의 코드 조각을 본다면 이해하기 상당히 쉽습니다.

이제 웹사이트 안의 book instance view로 이동하세요; 이제 아래와 같은 양식이 보일겁니다:

![](./images/admin_improved_bookinstance_detail_sections.png)



### 연관 레코드들의 인라인 편집(Inline editing of associated records)

때때로 연관된 레코드들을 동시에 추가하는 것이 가능할 때도 있습니다. 예를 들어, 세부 사항 페이지에서 책 정보 그리고 특정한 복사본에 대한 정보 둘 다 가질 수도 있습니다.

이것은 [inlines](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.ModelAdmin.inlines)를 선언해서 할 수 있고, [TabularInline](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.TabularInline) (수평적 레이아웃)타입 또는 [StackedInline](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.StackedInline) (기본 모델 레이아웃과 같은 수직적 레이아웃)을 선택할 수 있습니다. 아래 `BookAdmin` 근처의 굵게 표시된 줄을 추가하여 `BookInstance` 정보 인라인을 `Book` 세부 사항(detail)에 추가할 수 있습니다:

```python
class BooksInstanceInline(admin.TabularInline):
    model = BookInstance

@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    list_display = ('title', 'author', 'display_genre')
    inlines = [BooksInstanceInline]
```

`catalog/admin.py`



 이제 웹사이트의 `Book` 을 위한 화면으로 이동하세요 — 화면의 하단에서 이제 이 책과 연관된 책 인스턴스(book instances)를 볼 수 있을 겁니다(책의 장르 필드 바로 아래에):

![Admin Site - Book with Inlines](./images/admin_improved_book_detail_inlines.png)

 이 경우 우리는 인라인된 모델의 모든 필드들을 추가하는, 테이블화된 인라인 클래스를 선언했습니다. 레이아웃을 위해 모든 종류의 추가적인 정보들을 지정할 수 있습니다. 표시할 필드, 그것들의 순서, 그것들이 읽기 전용인지 아닌지, 등등을 포함해서요. (더 많은 정보를 위해 [TabularInline](https://docs.djangoproject.com/en/dev/ref/contrib/admin/#django.contrib.admin.TabularInline) 를 보세요). 



> **주의**: 이 기능에는 몇 가지 고통스러운 한계가 있습니다! 위의 스크린샷에서 우리는 이미 존재하는 세 개의 책 인스턴스(book instance)와 그 아래에 새로운 책 인스턴스를 위한 세 개의 플레이스홀더(placeholder)를 가지고 있습니다(아주 비슷하게 보이지요!). 기본값으로 예비 책 인스턴스를 위한 플레이스를 홀더를 가지지 **않고** 새로운 북 인스턴스마다 **새로운 북 인스턴스 링크 하나씩** 추가하는 것이 좋습니다. 또는 `BookInstance`를 여기서는 읽기 불가(non-readable) 링크로 목록화하는 것도 좋구요. 전자는` BookInstanceInline` 모델 안의 `extra` 속성을 0으로 설정하여 완료할 수 있습니다. 직접 해보세요.



## 도전 과제

이 섹션에서 많은 것을 배웠기 때문에, 이젠 직접 몇 가지를 도전해 볼 차례입니다.

1. `BookInstance` 목록 뷰에 책, 상태, 만기 날짜, 그리고 id(book, status, due back date, id)를 표시하기 위한 코드를 추가해 보세요(기본` __str__()` 텍스트 가 아닌).
2. Book/BookInstance에서 했던 것고 같은 접근법을 사용해서 Author 세부 사항 뷰에 Book 항목들의 인라인 목록(Inline listing)을 추가해 보세요.



## 요약

이게 답니다! 이제 관리자 사이트를 어떻게 설정하는지에 대해 가장 간단한 방법으로 그리고 발전된 방법으로도 배웠습니다. superuser 만드는 방법, 관리자 사이트와 뷰들을 이동하는 방법, 레코드를 삭제 및 업데이트하는 방법 등등을 배웠습니다. 지금까지 여러 Books, BookInstances, Genres 그리고 Authors를 만들었고, 이것들은 우리가 우리만의 view와 탬플릿들을 만들었을 때 그곳에 목록화하여 나타낼 수 있을 것입니다.



# Django Tutorial Part 5: Creating our home page

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Home_page



우리는 이제 첫 전체 페이지를 보여주는 코드를 추가할 때가 되었습니다 — [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website를 위한 홈페이지를요. 이 홈페이지는 각각의 모델 타입마다 갖고 있는 레코드들의 숫자를 보여주고, 우리의 다른 페이지들로 이동할 수 있는 사이드바 내비게이션 링크들을 제공합니다. 이 섹션에서 우리는 기본 URL 맵과 뷰들을 작성하고, 데이터베이스에서 레코드들을 가져오고 그리고 탬플릿을 사용하는 것에 대한 연습 경험을 가질 수 있습니다.

| 사전 준비: | [Django Introduction](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Introduction)을 읽어보세요. 이전 튜토리얼들을 완료하세요 ([Django Tutorial Part 4: Django admin site](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Admin_site) 포함). |
| ---------- | ------------------------------------------------------------ |
| 목표:      | 간단한 url 맵과 뷰를 생성하고(URL 안에 아무런 데이터도 인코드되지 않은), 모델로부터 데이터를 가져오고, 탬플릿을 생성하는 방법을 배우기. |

## <span style="color:blue">개요</span>

지금까지는 우리의 모델을 정의하고 약간의 초기 도서관 레코드들을 만들어 왔고, 이제는 사용자에게 정보를 제공하기 위한 코드를 작성할  때입니다. 첫 번째 우리가 할 일은 우리의 페이지에서 어떤 정보를 보여줄 것인지를 결정하고, 그 요소들을 반환하는 데 사용되는  URL들을 정의하는 것입니다. 다음으로 우리는 페이지를 나타내기 위한 URL 매퍼, views, 그리고 탬플릿들을 생성할  것입니다. 

 아래 다이어그램은 주요 데이터 흐름 그리고 HTTP 요청과 응답을 처리하는 데 필요한 요소들을 보여줍니다. 모델은 이미 구현되었기 떄문에, 우리가 생성할 주요 요소들은 다음과 같습니다:

- URL 매퍼들 : 적절한 view 함수들을 위해 지원되는 URL들(그리고 URL들 안에 인코딩된 어떤 정보라도)을 포워딩하기 위해.
- View 함수들: 요청된 데이터를 모델들에게서 가져오고, 데이터를 표시하는 HTML 페이지를 생성하고 그리고 브라우저 안의 view로 페이지들을 사용자에게 반환하기 위해.
- 탬플릿들: 데이터를 뷰들 안에 렌더링할 때 사용하기 위해.



 우리가 표시해야 할 페이지는 총 다섯 페이지입니다. 하나의 글에 담기에는 너무 많은 정보죠. 따라서, 이 글의 대부분은 홈 페이지를 어떻게 구현하는 지에 대해 집중하고, 다음 글에서 다른 페이지들에 대해 다루겠습니다. 이렇게 하면 URL 매퍼들,  view들, 그리고 모델이 실제로 작동하는 방식에 대해 완벽하고 철저하게 이해할 수 있을 것입니다.



## <span style="color:blue">resource URLs 정의하기</span>

이 버전의 LocalLibrary는 근본적으로 최종 사용자들에게는 읽기 전용이기 때문에, 우리는 사이트의 방문 페이지(홈 페이지) 그리고 책들과 저자들에 대한 목록 및 세부 view들을 보여주는 페이지만 제공하면 됩니다. 

우리의 페이지들을 위해 필요한 URL들은:

- `catalog/` — 홈/색인(index) 페이지.
- `catalog/books/` — 모든 책들의 목록.
- `catalog/authors/` — 모든 저자들의 목록.
- `catalog/book/*<id>*` — `*<id>*` 라는 이름의(기본값) 프라이머리 키(primary key) 필드를 가지는 특정한 책을 위한 세부 사항 뷰(detail view). 예를 들어, 목록에 추가된 세 번째 책은 `/catalog/book/3`이 될 것입니다. 
- `catalog/author/*<id>*` — `*<id>*` 라는 이름의 프라이머리 키(primary key) 필드를 가지는 특정한 저자를 위한 세부 사항 뷰(detail view). 예를 들어, 목록에 추가된 11번째 저자는 `/catalog/author/11`이 될 것입니다.

처음 세 개의 URL들은 인덱스 페이지, 책 목록, 그리고 저자 목록을 반환합니다. 이것들은 아무런 추가적인 정보도  인코드하지 않고, 데이터베이스에서 데이터를 가져오는 쿼리들도 항상 똑같습니다. 그러나, 쿼리들이 반환할 결과들은 데이터베이스의  내용물에 따라 다를 것입니다.

그에 반해서 마지막 두 개의 URL들은 특정한 책 또는 저자에 대한 세부 정보를 나타낼 것입니다. 이 URL들은 표시할 항목의 ID를 인코딩합니다(위에서 `*<id>*` 로 표시). URL 매퍼는 인코딩된 정보를 추출하여 view로 전달합니다. 그리고 view는 데이터베이스에서 무슨 정보를  가져올지 동적으로 결정합니다. URL의 정보를 인코딩하여 우리는 모든 책들(또는 저자들)을 처리하기 위해 단일 모임의 url  매핑, 뷰, 탬플릿을 사용할 것입니다. 

> **주의:** 장고를 이용해서 당신이 필요로 하는 대로 URL들을 만들 수 있습니다 — 위와 같이  URL의 본문(body)에 정보를 인코딩할 수도 있고, 또는 URL 안에 GET 매개 변수들을 포함시킬 수 있습니다(예:  /book/?id=6). 어떤 방식이건 URL들은 깨끗하고, 논리적이고, 읽기 쉬워야 합니다. ([check out the W3C advice here](https://www.w3.org/Provider/Style/URI)).
>  장고 문서는 더 나은 URL 설계(design)를 위해 URL의 본문(body)에 정보를 인코딩하는 것을 권장합니다.

개요에서 다룬 것 처럼, 이 글의 나머지는 색인(index) 페이지를 만드는 방법을 보여줍니다.



## <span style="color:blue">index page 만들기</span>

우리가 만들 첫 번째 페이지는 index page입니다 (`catalog/`). index 페이지는 데이터베이스 안의 서로 다른 레코드들의 생성된 "개수(count)" 와 함께 몇 가지 정적 HTML을 포함합니다. 이것이 작동하도록 하기 위해서 우리는 **URL 매핑, 뷰 그리고 탬플릿**을 생성하겠습니다. 

> **주의**:이 섹션에 조금 더 집중해 봅시다. 대부분의 정보들이 우리가 생성할 다른 페이지들에도 적용되기 때문입니다.



### <span style="color:blue">URL 매핑</span>

[skeleton website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website)를 만들었을 때, 우리는 **locallibrary/urls.py** 파일을 업데이트했습니다. `catalog/`로 시작하는 URL을 받았을 때, URLConf 모듈인 `catalog.urls` 가 나머지 문자열을 처리하도록 하기 위해서죠.

**locallibrary/urls.py** 의 아래 코드 조각은 `catalog.urls` 모듈을 포함합니다:

```python
urlpatterns += [
    path('catalog/', include('catalog.urls')),
]
```

> **주의**: 장고는 import 함수 django.urls.include()를 만날 때 마다 지정된 마지막 문자에서 문자열을 나누고, 나머지 부분 문자열을 추가 작업을 위해 포함된 URLconf 모듈로 보냅니다.

우리는 또한 **/catalog/urls.py**로 이름지어진 URLConf 모듈을 위한 자리 표시자(placeholder) 파일도 생성했습니다. 그 파일에 아래 줄을 추가하세요

```python
urlpatterns = [
    path('', views.index, name='index'),
]
```

이 `path()` 함수는 아래를 정의합니다 : 

- 빈 문자열인 URL 패턴 :`''`. 다른 뷰들을 작업할 때 URL 패턴들에 관해 자세히 다룰겁니다.
- URL 패턴이 감지되었을 때 호출될 view 함수:`views.index`. 이 함수는 **views.py** 파일 안에서 `index()`로 이름지어져 있습니다.

이 `path()` 함수는 또한 `name` 매개 변수를 지정합니다. 그것은 이  특정한 URL 매핑을 위한 고유 ID 입니다. 당신은 이 이름을 매퍼를 "반전" 시킬 수 있습니다. 즉, 매퍼가 처리하도록 설계된 리소스를 향하는 URL을 동적으로 생성하기 위해서죠. 예를 들자면, 우리는 아래 링크를 탬플릿에 추가해서 이름 매개 변수를  사용하여 다른 모든 페이지에서 홈 페이지로 링크를 걸 수 있습니다:

```python
<a href="{% url 'index' %}">Home</a>.
```

> **주의**: 우리는 위 링크를 하드코딩할 수 있지만(예: `<a href="**/catalog/**">Home</a>`), 그렇게 하면 만약에 우리가 홈페이지를 바꿨을 때 (예: `/catalog/index`로 바꿨을 때) 탬플릿들은 더이상 알맞게 링크되지 않습니다. 반전된 url 매핑을 사용하는 것이 훨씬 유연하고 강력합니다.



### <span style="color:blue">View (함수-기반의)</span>

<span style="color:blue">뷰는 HTTP 요청을 처리하고, 데이터베이스에서 요청된 데이터를 가져오고, HTML 탬플릿을 이용해서 데이터를 HTML 페이지에  렌더링하고 그리고 생성된 HTML을 HTTP 응답으로 반환하여 사용자들에게 페이지를 보여주는 함수입니다.</span> 색인(index) 뷰는 이 구조(model)를 따라갑니다 — 이것은 데이터베이스 안에 있는`Book`, `BookInstance`, 사용 가능한 `BookInstance` 그리고 `Author` 레코드들의 개수에 대한 정보를 가져오고, 그 정보를 디스플레이(display)를 위해 탬플릿으로 전달합니다.

**catalog/views.py** 를 열어서, 파일이 이미 탬플릿과 데이터를 이용해 HTML 파일을 생성하는 [render()](https://docs.djangoproject.com/en/2.0/topics/http/shortcuts/#django.shortcuts.render) 바로가기 함수를 포함(import)하고 있음을 확인하세요.

```python
from django.shortcuts import render

# Create your views here.
```

 파일의 하단에 아래 코드를 복사 붙여넣기 하세요.

```python
from catalog.models import Book, Author, BookInstance, Genre

def index(request):
    """View function for home page of site."""

    # Generate counts of some of the main objects
    num_books = Book.objects.all().count()
    num_instances = BookInstance.objects.all().count()

    # Available books (status = 'a')
    num_instances_available = BookInstance.objects.filter(status__exact='a').count()

    # The 'all()' is implied by default.
    num_authors = Author.objects.count()

    context = {
        'num_books': num_books,
        'num_instances': num_instances,
        'num_instances_available': num_instances_available,
        'num_authors': num_authors,
    }

    # Render the HTML template index.html with the data in the context variable
    return render(request, 'index.html', context=context)
```

첫번째 줄은 우리의 모든 뷰들 안에서 데이터에 접근하는 데 사용할 모델 클래스들을 포함(import)합니다.

view 함수의 첫번째 부분은 모델 클래스들에서 `objects.all()` 속성을 사용하는 레코드들의 개수를 가져옵니다. 이 함수는 또한 상태 필드에서 'a'(Available) 값을 가지고 있는 `BookInstance` 객체들의 목록도 가져옵니다. 이전 튜토리얼에서 모델 데이터에 접근하는 방법에 대한 더 많은 정보를 찾을 수 있습니다 : [Django Tutorial Part 3: Using models > Searching for records](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Models#searching_for_records).

view 함수의 마지막에선 HTML 페이지를 생성하고 이 페이지를 응답으로서 반환하기 위해 `render()` 함수를 호출합니다. 이 바로가기(shortcut) 함수는 아주 일반적으로 사용되는 경우들을 간단히 하기 위해 여러 다른 함수들을 포함합니다. `render()` 함수는 아래 매개 변수들을 허용합니다:

- `HttpRequest`인 원본 `request` 객체.
- 데이터에 대한 플레이스홀더(placeholder)들을 갖고 있는 HTML 탬플릿.
- 파이썬 딕셔너리(dictionary)인, 플레이스홀더에 삽입할 데이터를 갖고 있는 `context`변수.

다음 섹션에서 탬플릿과 context 변수에 대해 더 다루도록 하겠습니다. 이제 탬플릿을 생성하여 사용자들에게 실제로 무언가를 표시해 봅시다!



### <span style="color:blue">탬플릿(Template)</span>

<span style="color:green">탬플릿은 파일(HTML 페이지 같은)의 구조(structure)나 배치(layout)을 정의하는 텍스트 파일입니다. 탬플릿은 실제  내용물(content)를 나타내기 위해 플레이스홀더(placeholder)들을 사용합니다.</span> 장고는 당신의 어플리케이션 안에서  'templates' 라고 이름지어진 경로 안에서 자동적으로 탬플릿들을 찾을 것입니다. 예를 들어서, 우리가 방금 추가한  색인(index) 뷰 안에서, `render()` 함수는 **/locallibrary/catalog/templates/** 경로 안에서 ***index.html\*** *파일을 찾으려 할 것이고, 파일이 없다면 에러를 표시할 것입니다. 이것은 이전의 변경점들을 저장하고 브라우저에서 `127.0.0.1:8000`으로 접근해서 확인할 수 있습니다 - 이것은 다른 세부 사항들과 함께 상당히 직관적인 오류 메세지를 표시할 것입니다 : "`TemplateDoesNotExist at /catalog/`".*

> **주의:** 프로젝트의 settings 파일에 기초해서, 장고는 여러 장소에서 탬플릿들을 찾아볼  것입니다. 기본적으로는 설치된 어플리케이션에서 검색합니다. 장고가 어떻게 탬플릿들을 찾는지, 그리고 어떤 탬플릿  양식(format)들을 지원하는지에 관해 여기([Templates](https://docs.djangoproject.com/en/2.0/topics/templates/) (Django docs))에서 찾아볼 수 있습니다.



#### <span style="color:blue">탬플릿 확장(extend)하기</span>

 색인(index) 팀플릿은 head 및 body를 위해 표준 HTML 마크업이 필요할 것입니다. 우리가 아직 생성하지 않은 사이트들의 다른 페이지들을 향한 링크를 걸기 위한 탐색(navigation) 섹션도 필요하고요. 그리고 소개 텍스트 및 책  데이터를 표시하는 섹션 또한 필요합니다. 대부분의 HTML과 탐색(navigation) 구조는 사이트의 모든 페이지에서 동일할 것입니다. 모든  페이지마다 똑같은 코드를 복사하는 대신, 기본 템플릿을 선언하기 위해 장고 탬플릿 언어(Django templating  language)를 사용하고, 탬플릿을 확장하여 각각의 페이지 마다 다른 부분들만을 대체(replace)할 수 있습니다.

아래 코드 조각은 **base_generic.html** 파일의 기본 탬플릿 샘플입니다. 이 샘플은 제목, 사이드바를 위한 섹션과 이름이 지정된 `block` 및 `endblock` 탬플릿 태그(굵게 표시)가 마크된 주요 내용(main contents)들이 포함된 일반적인 HTML을 포함합니다.  블럭(block)들을 비워두거나, 또는 탬플릿에서 파생된 페이지들을 렌더링할 때 사용할 기본 내용을 포함할 수 있습니다.

> **주의:** 탬플릿 태그들은 목록을 반복하거나, 변수 값을 기반으로 조건부 연산을 수행하거나, 여타 다른 일들을 할 수 있는 함수입니다. 탬플릿 태그 외에도 탬플릿 구문(syntax)을 사용하면 view에서 탬플릿으로 전달된  변수들을 참조할 수 있고, 탬플릿 필터(filters)를 사용해서 변수의 형식을 지정할 수 있습니다(예를 들어, 문자열을 소문자로 변환).

```html
<!DOCTYPE html>
<html lang="en">
<head>
  {% block title %}<title>Local Library</title>{% endblock %}
</head>
<body>
  {% block sidebar %}<!-- insert default navigation text for every page -->{% endblock %}
  {% block content %}<!-- default content text (typically empty) -->{% endblock %}
</body>
</html>
```

특정한 view를 위해 탬플릿을 정의할 땐, 먼저 `extends` 탬플릿 태그를 이용하여 기본 탬플릿을 지정합니다 — 아래 코드 샘플을 참조하세요. 그리고 나서 기본 탬플릿에서와 같이 `block`/`endblock` 섹션들을 이용해서 대체할 탬플릿의 섹션들을 선언합니다(있을 경우). 

예를 들어, 아래 코드 조각은 extends 탬플릿 태그의 사용 및 content 블럭(block)을 재정의하는 방법을 보여줍니다. 생성된 HTML은 기본 탬플릿에서 정의된 코드와 구조를 포함할 것입니다(`title` 블럭에서 정의한 기본 내용은 포함하지만, 기본 `contents` 블럭 대신 새로운 `contents` 블럭 포함).

```html
{% extends "base_generic.html" %}

{% block content %}
  <h1>Local Library Home</h1>
  <p>Welcome to LocalLibrary, a website developed by <em>Mozilla Developer Network</em>!</p>
{% endblock %}
```



#### <span style="color:blue">LocalLibrary 기본 탬플릿</span>

우리는 아래 코드 조각을 LocalLibrary 웹사이트의 베이스 탬플릿으로 사용할 것입니다. 보시는 바와 같이, 이것은 HTML 코드를 조금 포함하고 `title`, `sidebar` 그리고 `content` 블럭을 정의합니다. 우리는 기본 제목과 모든 책들 및 저자들에 대한 링크를 갖고 있는 기본 사이드바를 갖고 있습니다. 둘 다 미래에 쉽게 변경하기 위해 블럭들 안에 묶여 있습니다.

> **주의**: 우리는 또한 두 개의 추가적인 탬플릿 태그를 소개합니다: `url` 과 `load static`. 이 태그들은 아래 섹션들에서 설명될 것입니다.

새로운 파일 **base_generic.html** 을 **/locallibrary/catalog/templates/base_generic.html** 경로 안에 생성해서 아래 코드를 파일에 복사 붙여넣기 하세요:

```html
<!DOCTYPE html>
<html lang="en">
<head>
  {% block title %}<title>Local Library</title>{% endblock %}
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <!-- Add additional CSS in static file -->
  {% load static %}
  <link rel="stylesheet" href="{% static 'css/styles.css' %}">
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-2">
      {% block sidebar %}
        <ul class="sidebar-nav">
          <li><a href="{% url 'index' %}">Home</a></li>
          <li><a href="">All books</a></li>
          <li><a href="">All authors</a></li>
        </ul>
     {% endblock %}
      </div>
      <div class="col-sm-10 ">{% block content %}{% endblock %}</div>
    </div>
  </div>
</body>
</html>
```

탬플릿에는 HTML 페이지의 레이아웃과 프리젠테이션을 개선하기 위한 [Bootstrap](http://getbootstrap.com/) 의 CSS가 포함되어 있습니다. 부스트스트랩(또는 다른 클라이언트-사이드 웹 프레임워크)를 사용해서 서로 다른 크기의 화면에서도 잘 표시되는 매력적인 페이지를 빠르게 만들 수 있습니다.

또한 기본 탬플릿은 추가적인 꾸미기(styling)를 제공하는 로컬 css 파일(styles.css)을 참조합니다. **styles.css** 파일을 **/locallibrary/catalog/static/css/** 경로 안에 생성하고 아래 코드를 파일 안에 복사 붙여넣기 하세요:

```css
.sidebar-nav {
    margin-top: 20px;
    padding: 0;
    list-style: none;
}
```



#### <span style="color:blue">색인(index) 탬플릿</span>

 새로운 HTML 파일 **index.html** 을 **/locallibrary/catalog/templates/** 경로 안에 생성해서 아래 코드를 파일 안에 복사 붙여넣기 하세요. 보시는 바와 같이 첫째 행에서 우리의 기본 탬플릿을 확장하고, 탬플릿의 기본 `content` 블럭을 새로운 블럭으로 대체합니다. 

```html
{% extends "base_generic.html" %}

{% block content %}
  <h1>Local Library Home</h1>
  <p>Welcome to LocalLibrary, a website developed by <em>Mozilla Developer Network</em>!</p>
  <h2>Dynamic content</h2>
  <p>The library has the following record counts:</p>
  <ul>
    <li><strong>Books:</strong> {{ num_books }}</li>
    <li><strong>Copies:</strong> {{ num_instances }}</li>
    <li><strong>Copies available:</strong> {{ num_instances_available }}</li>
    <li><strong>Authors:</strong> {{ num_authors }}</li>
  </ul>
{% endblock %}
```

동적 콘텐츠 섹션에서 우리는 우리가 포함하고 싶은 view의 정보를 위한 플레이스홀더(탬플릿 변수)를 선언합니다. 이 변수들은 코드 샘플에서 굵게 표시된 것과 같이 이중 중괄호로(핸들 바)로 묶입니다.

> **주의:** 탬플릿 변수와 탬플릿 태그(함수)들을 쉽게 알 수 있습니다 - 변수들은 이중 중괄호로 감싸여져 있고(`{{ num_books }}`) , 태그들은 퍼센트 기호와 단일 중괄호로 감싸여 있습니다(`{% extends "base_generic.html" %}`).

여기서 주의해야 할 중요한 것은 변수들의 이름은 열쇠(key)들로 정해진다는 것입니다. 이 열쇠(key)들은 우리의 view의 `render()`함수 안의 `context` 사전(dictionary)로 전달하는 열쇠입니다(아래를 확인하세요). 변수들은 탬플릿이 렌더링 될 때 그것들과 연관된 값들로 대체될 것입니다. 

```python
context = {
    'num_books': num_books,
    'num_instances': num_instances,
    'num_instances_available': num_instances_available,
    'num_authors': num_authors,
}

return render(request, 'index.html', context=context)
```



#### <span style="color:blue">Templates 에 정적 파일 참조하기(referencing)</span>

당신의 프로젝트는 자바스크립트, CSS 그리고 이미지를 포함하는 정적 리소스들을 사용할 가능성이 높습니다. 이 파일들의 위치가 알 수 없기 때문에(또는 바뀔 수 있기 때문에), 장고는 `STATIC_URL` 전역 설정을 기준으로 탬플릿에서의 위치를 특정할 수 있도록 합니다. 기본 뼈대 웹사이트(skeleton website)는 `STATIC_URL`의 값을 '`/static/`'으로 설정하지만, 당신은 이것들을 콘텐츠 전달 네트워크(content delivery network)나 다른 곳에서 호스트할 수도 있습니다.

아래 코드 샘플처럼, 탬플릿 안에서 당신은 먼저 탬플릿 라이브러리를 추가하기 위해 "static"을 지정하는 `load` 탬플릿 태그를 호출합니다. 그러고 나서 `static` 탬플릿 태그를 사용할 수 있고 관련 URL을 요구되는 파일에 지정할 수 있습니다.

```html
<!-- Add additional CSS in static file -->
{% load static %}
<link rel="stylesheet" href="{% static 'css/styles.css' %}">
```

비슷한 방법으로 이미지를 페이지에 추가할 수 있습니다. 예를 들어:

```html
{% load static %}
<img src="{% static 'catalog/images/local_library_model_uml.png' %}" alt="UML diagram" style="width:555px;height:540px;">
```

 **주의**: 위의 샘플은 파일들의 위치를 특정하지만, 장고는 기본적으로 파일을 제공하지 않습니다. 우리는 우리가 웹사이트 뼈대를 생성했을 때([created the website skeleton](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website)) 전역 URL 매퍼(/locallibrary/locallibrary/urls.py)를 수정하여 개발 웹 서버가 파일을 제공하도록  설정했습니다만, 제품화되었을(in production)때도 파일을 제공할 수 있어야 합니다. 이것에 관해 차후에 다루겠습니다.

 정적 파일들로 작업하는 것에 대한 더 많은 정보는 장고 문서 안의 [Managing static files](https://docs.djangoproject.com/en/2.0/howto/static-files/) 를 참고하세요.



#### <span style="color:blue">URL 링크하기(Linking to URLs)</span>

위의 기본 탬플릿은 `url` 탬플릿 태그를 소개했습니다.

```html
<li><a href="{% url 'index' %}">Home</a></li>
```

 이 태그는 **urls.py**에서 호출된 `path()` 함수의 이름 및 연관된 view가 그 함수에서 수신받을 모든 인자들을 위한 값들을 허용하고, 리소스에 링크하는 데 사용할 수 있는 URL을 반환합니다 .



#### <span style="color:blue">탬플릿을 찾을 수 있는 곳 설정하기</span>

탬플릿 폴더 안에서 탬플릿을 찾아볼 수 있도록 장고에게 위치를 가르쳐 주어야 합니다. 그것을 하기 위해서, 아래 코드 샘플에 굵게 표시된 것 처럼 **settings.py** 파일을 수정하여 TEMPLATES 객체에 templates 경로(dir)를 추가하세요.

 

```python
TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [
            os.path.join(BASE_DIR, 'templates'),
        ],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]
```

 `wonsool: import os 를 파일의 앞쪽에 추가 하여야 한다.`



## 어떻게 보일까요?

이 시점에서 우리는 색인(index) 페이지를 나타내기 위해 필요한 모든 요소들을 생성했습니다. 서버를 실행하고 (`python3 manage.py runserver`) 브라우저에서 http://127.0.0.1:8000/으로 이동하세요. 모든 것이 알맞게 설정되었다면, 당신의 사이트는 아래 스크린샷과 같이 보여야 합니다.

![Index page for LocalLibrary website](./images/index_page_ok.png)

> **주의:** All books와 All authors 링크들에 대한 경로, 뷰 그리고 탬플릿들이 정의되지 않았기 때문에 그 링크들은 작동하지 않을 것입니다. 우리는 단지 `base_generic.html` 탬플릿 안에 그 링크들을 위한 플레이스홀더(placeholder)들을 삽입했을 뿐입니다.



## 도전 과제

모델 쿼리, 뷰 그리고 탬플릿들과의 친밀함을 시험할 수 있는 두 가지 임무가 있습니다. 

1. LocalLibrary 기본 탬플릿(base template)에는 `title`블록이 정의되어 있습니다. 색인 탬플릿(index template) 안에 이 블록을 덮어쓰기하고 페이지를 위한 새로운 제목을 만들어 보세요.   

   **힌트:** [Extending templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Home_page#extending_templates) 섹션은 블럭(block)을 생성하고 다른 탬플릿에서 블럭을 확장(extend)하는 방법을 설명합니다.

2. 대소문자 구분 없이 특정한 단어를 포함하는 장르와 책들의 개수(count)를 생성하도록 [view](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Home_page#view_(function-based)) 를 수정하고, 결과를 `context`에 전달해 보세요. 이것은 `num_books`와 `num_instances_available`을 생성하고 사용하는 것과 비슷한 방법으로 달성할 수 있습니다. 그리고 나서 이 변수들을 포함시키기 위해 [index template](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Home_page#the_index_template) 를 업데이트 하세요.



## 요약

이제 우리의 사이트를 위한 홈 페이지를 생성했습니다 — 데이터베이스의 여러 레코드들을 표시하고 아직 생성되지 않은 페이지로 링크하는  HTML 페이지 입니다. 그 과정에서 우리는 url 매퍼, view, 모델을 이용한 데이터베이스 쿼리, view에서 탬플릿으로의  정보 전달 그리고 탬플릿의 생성과 확장에 관한 기본적인 정보를 배웠습니다.

다음 글에서는 이 지식들을 토대로 우리 웹사이트의 나머지 네 개의 페이지들을 생성할 것입니다.



# Django Tutorial Part 6: Generic list and detail views

link : https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views

이 튜토리얼은 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website에 책과 저자의 목록과 세부 페이지를 추가 하여 확장할 것입니다. 이 글에서 우리는 제네릭 클래스-기반  뷰(generic class-based views)에 대해 배울 것이며, 그것이 일반적인 사용 사례를 위해 작성해야 하는 코드들을 줄이는 방법을 보여줄 것입니다. 우리는 또한 URL 처리에 대해 더 세부적으로 알아볼 것이며, 기본 패턴 매칭(basic  pattern matching)을 수행하는 방법을 보여줄 것입니다.

| 사전 준비: | [Django Tutorial Part 5: Creating our home page](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Home_page)를 포함한 모든 이전 튜토리얼을 완료하세요. |
| ---------- | ------------------------------------------------------------ |
| 목표:      | 제네릭 클래스-기반 뷰를 어디 그리고 어떻게 사용하는지, 그리고 어떻게 URL들로부터 패턴들을 추출하여 정보를 view들에게 전달하는지에 대해 이해하기. |



## <span style="color:blue">개요</span>

이 튜토리얼에서 우리는 책과 저자에 대한 목록과 세부 페이지(detail page)를 추가하여 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 웹사이트의 첫 번째 버전을 완성할 것입니다(더 정확하게는, 우리는 책 페이지들을 구현하는 방법을 보여주고, 저자 페이지는 스스로 완성하도록 할 것입니다!)

이 과정은 이전 튜토리얼에서 보여준, 색인 페이지(index page)를 만드는 과정과 비슷합니다. 우리는 여전히 URL  맵들, view들, 그리고 탬플릿들을 만들어야 합니다. 주요한 차이점은 세부 페이지(detail pages)에서, 우리는 URL  안의 패턴에서 정보를 추출해서 view로 전달하는 추가적인 도전을 가진다는 점입니다. 

이 튜토리얼의 마지막에서는 제네릭 클래스-기반 뷰를 사용할 때 데이터의 페이지를 매기는 방법을 보여드리겠습니다.



## <span style="color:blue">책 목록 페이지</span>

책 목록 페이지는 모든 사용 가능한 책 레코드들의 목록을 페이지 안에 나타낼 것이며, 다음 URL을 사용하여 접근합니다: `catalog/books`/. 이 페이지는 각 레코드마다 제목과 저자를 나타낼 것이며, 제목은 관련된 책 페이지로 이동하는 하이퍼링크 처리됩니다. 이 페이지는 사이트의 다른 페이지들과 같은 구조와 내비게이션을 가지고 있어서, 우리는 이전 튜토리얼에서 만들었던 템플릿(**base_generic.html**)을 확장해서 사용하면 됩니다.



### <span style="color:blue">URL mapping</span>

**/catalog/urls.py** 파일을 열고 아래 코드박스의 굵은 글씨를 복사해 붙여넣으세요. 인덱스 페이지처럼 `path()` 함수는 URL('**books/'**)과 매치되는 패턴, URL이 매치될 때 호출되는 view 함수`(views.BookListView.as_view())`, 그리고 이 특정 매핑에 대한 이름을 정의합니다.

```python
urlpatterns = [
    path('', views.index, name='index'),
    path('books/', views.BookListView.as_view(), name='books'),
]
```

이전 튜토리얼에서 URL은 이미 `/catalog` 와 매치되었을 것입니다. 그래서 사실상 view는 `/catalog/books/` URL에 접속하면 호출됩니다.

View함수는 이전과 다른 형태를 가집니다. 왜냐면 이 view는 사실 클래스로 구현이 되기 때문입니다. 우리는 이  view를 직접 구현하지 않고, 이미 존재하는 generic view 함수를 상속받아 view 함수를 구현할 것입니다. 이  generic view 함수는 우리가 구현하고 싶은 기능들을 거의 다 가지고 있습니다.

Django의 클래스 기반 view에서는, `as_view()`클래스 메소드를 호출해 적절한 view 함수에 접근할 수 있습니다. 이건 클래스의 인스턴스를 생성하는 작업과 모든 HTTP 요청을 처리하는 핸들러 메소드가 제대로 동작하는지를 처리합니다.



### <span style="color:blue">뷰(클래스 기반)</span>

표준 function으로 우리는 꽤나 쉽게 book list view를 만들 수 있는데 (마치 이전 우리의 index view같이), 모든 책에 대한 데이터 베이스 쿼리를 만들어서 `render()` 함수를 불러 특정 템플릿에 리스트를 보냅니다. 대신 우리는 class-based generic list view (`ListView`) 를 사용하는데— 존재하는 뷰로부터 상속받아온 클래스입니다. generic view가 이미 우리가 필요한 대부분의 기능성을  실행하면서 동시에 Django best-practice이기 때문에, 우리는 코드의 양과 반복을 줄이고 궁극적으로 유지보수에 수고가 덜 드는 견고한 리스트 뷰를 만들 수 있습니다.

**catalog/views.py** 파일을 열고, 아래의 코드를 views.py 파일 가장 아래에 붙여넣기하세요.

```python
from django.views import generic

class BookListView(generic.ListView):
    model = Book
```

이게 전부입니다! Generic view는 명시된 모델(Book)의 모든 레코드를 가져오기 위해 데이터베이스를 쿼리할 것이고, **/locallibrary/catalog/templates/catalog/book_list.html**(아래에서 만들 예정)경로에 있는 템플릿을 렌더링합니다. 템플릿 안에서 우리는 `object_list`나 `book_list`라는 템플릿 변수를 사용해 도서 목록에 접근할 수 있습니다. (일반적으로 "`*the_model_name*_list`").

**Note**: 이 어색한 템플릿 경로는 오타가 아닙니다. Generic views는 `/*application_name*/*the_model_name*_list.html`(지금 상황에서는 `catalog/book_list.html`)에서 템플릿을 찾습니다. 이 경로는 애플리캐이션의 `/*application_name*/templates/` 디렉토리 안에 있습니다(`/catalog/templates/)`.

속성이나 디폴트 동작을 추가할 수도 있습니다. 예를 들어, 같은 모델을 사용하지만 여러 개의 뷰를 사용해야 되는 경우 다른 템플릿 파일을 명시할 수 있습니다. 혹은 `book_list` 템플릿 변수명이 직관적이지 않다고 생각해 다른 템플릿 변수명을 사용하고 싶을지도 모릅니다. 아마 가장 유용한 바리에이션은 리턴 값의  결과를 바꾸거나 필터링하는 것입니다. 따라서 모든 책을 나열하는 대신, 유저가 읽은 순으로 5개의 책을 나열할 수도 있습니다.

```python
class BookListView(generic.ListView):
    model = Book
    context_object_name = 'my_book_list'   # your own name for the list as a template variable
    queryset = Book.objects.filter(title__icontains='war')[:5] # Get 5 books containing the title war
    template_name = 'books/my_arbitrary_template_name_list.html'  # Specify your own template name/location
```

`wonsool: class BookListView 는 generic.ListView class 를 상속 받아 생성 함`



#### <span style="color:blue">클래스 기반 뷰의 메소드 오버라이딩</span>

굳이 여기서 할 필요는 없지만, 클래스 메소드 오버라이딩을 할 수도 있습니다.

예를 들어, 우리는  `get_queryset()`메소드를 오버라이딩해 반환되는 레코드의 리스트들을 바꿀 수 있습니다. 이건 우리가 이전에 했던 `queryset` 속성을 지정하는 방법보다 더 유연한 방법입니다. (비록 지금 상황에서 별 이득이 되지는 않지만요.)

```python
class BookListView(generic.ListView):
    model = Book

    def get_queryset(self):
        return Book.objects.filter(title__icontains='war')[:5] # Get 5 books containing the title war
```

우리는 템플릿에 추가적인 컨텍스트 변수들을 전달하기 위해 `get_context_data()`를 오버라이딩할 수도 있습니다. (도서 목록이 디폴트로 전달됩니다.) 아래의 코드는 `some_data` 라는 이름의 변수를 어떻게 컨텍스트에 추가하는지를 보여줍니다. (이렇게 하면 템플릿 변수로 사용할 수 있습니다.)

```python
class BookListView(generic.ListView):
    model = Book

    def get_context_data(self, **kwargs):
        # Call the base implementation first to get the context
        context = super(BookListView, self).get_context_data(**kwargs)
        # Create any data and add it to the context
        context['some_data'] = 'This is just some data'
        return context
```

이렇게 할 때에는, 아래의 패턴을 따르는 것이 중요합니다:

- 첫째로, 슈퍼클래스에서 기존 컨텍스트를 가져옵니다.
- 그리고 새로운 컨텍스트 정보를 추가합니다. 
- 마지막으로 새롭게 업데이트된 컨텍스트를 리턴합니다.

**Note**: [Built-in class-based generic views](https://docs.djangoproject.com/en/2.0/topics/class-based-views/generic-display/) (Django docs)를 방문해 다양한 예제를 살펴볼 수 있습니다.



### <span style="color:blue">리스트 뷰 템플릿 생성하기</span>

**/locallibrary/catalog/templates/catalog/book_list.html** 경로에 HTML 파일을 만든 다음, 아래의 코드를 복사, 붙여넣기 하세요. 이전에 설명한 것처럼, 이건 제네릭 클래스 기반 리스트 뷰에서 예상되는 기본 템플릿 파일입니다. (`catalog` 애플리케이션 내의 `Book` 모델)

제네릭 뷰의 템플릿은 다른 템플릿과 비슷합니다 (물론 템플릿에 전달되는 컨텍스트나 정보는 다를지도 모르지만요). 다른 index 템플릿처럼, 우리는 첫번째 줄에 base 템플릿을 넣어 확장한 다음, `content`라는 이름의 블록으로 교체합니다.

```html
{% extends "base_generic.html" %}

{% block content %}
  <h1>Book List</h1>
  {% if book_list %}
  <ul>
    {% for book in book_list %}
      <li>
        <a href="{{ book.get_absolute_url }}">{{ book.title }}</a> ({{book.author}})
      </li>
    {% endfor %}
  </ul>
  {% else %}
    <p>There are no books in the library.</p>
  {% endif %} 
{% endblock %}
```

뷰는 `object_list`나 `book_list`라는 디폴트 aliases로 컨텍스트(도서 목록)를 전달합니다. `object_list`나 `book_list` 둘 중 어느 것을 적어도 상관이 없습니다.

#### <span style="color:blue">조건부 실행</span>

`if`, `else` 그리고 `endif` 라는 템플릿 태그들은 `book_list` 가 정의되었는지, 그리고 존재하는지 체크합니다. 만약 `book_list`가 없다면, 책이 존재하지 않는다는 `else` 절의 텍스트 문구가 표시될 것입니다. 만약 `book_list`가 존재한다면, 도서 목록의 갯수만큼  반복해서 실행합니다.

```html
{% if book_list %}
  <!-- code here to list the books -->
{% else %}
  <p>There are no books in the library.</p>
{% endif %}
```

위의 조건문은 단 하나의 상황만 체크합니다. 하지만 `elif`라는 템플릿 태그를 사용해 추가적인 조건을 걸어 테스트할 수 있습니다. (예를 들면 `{% elif var2 %}`) 조건부 연산자에 대한 자세한 내용은 다음 링크에서 확인할 수 있습니다: [if](https://docs.djangoproject.com/en/2.0/ref/templates/builtins/#if), [ifequal/ifnotequal](https://docs.djangoproject.com/en/2.0/ref/templates/builtins/#ifequal-and-ifnotequal), and [ifchanged](https://docs.djangoproject.com/en/2.0/ref/templates/builtins/#ifchanged) in [Built-in template tags and filters](https://docs.djangoproject.com/en/2.0/ref/templates/builtins) (Django Docs).



#### <span style="color:blue">반복 구문</span>

[for](https://docs.djangoproject.com/en/2.0/ref/templates/builtins/#for) 와 `endfor` 라는 템플릿 태그들은 아래와 같이 도서 목록을 살펴보는 루프를 위해 사용합니다. 각각의 반복은 `book` 템플릿 변수에 현재 리스트 아이템에 대한 정보를 채웁니다.

```html
{% for book in book_list %}
  <li> <!-- code here get information from each book item --> </li>
{% endfor %}
```

여기에서는 사용되지 않지만, 반복 구문 내에서 Django는 반복을 추적할 수 있는 다른 변수들을 만들 수 있습니다. 예를 들어, `forloop.last`라는 변수로 루프의 마지막 실행에 대한 조건부 처리을 할 수 있습니다.



#### <span style="color:blue">변수 접근하기</span>

반복 구문 내에서의 코드는 각각의 책에 대한 리스트 아이템을 생성합니다. 이 리스트 아이템은 타이틀(아직 작성되지 않은 상세 뷰의 링크)과 작가의 이름을 나타냅니다.

```html
<a href="{{ book.get_absolute_url }}">{{ book.title }}</a> ({{book.author}})
```

우리는 점 표기법(dot notation)을 사용해서 연관된 책의 레코드(예를 들어 `book.title` 과 `book.author`)에 대한 필드에 접근이 가능합니다. `book` 다음의 텍스트는 모델에 정의되어있는 필드의 이름입니다.

우리는 템플릿 안에 모델에서 정의한 함수를 불러올 수도 있습니다. 이 경우, 우리는 `Book.get_absolute_url()` 함수를 호출해 연관된 세부 레코드를 표시하는 URL을 가져옵니다. 이 작업은 함수가 아무 인자를 가지지 않을 때 제공됩니다 (여기에는 인자를 넘길 방법이 없습니다!).

**Note**: 우리는 템플릿 내에서 함수를 호출할 때 발생하는 부작용을 조금 조심해야 합니다.  여기서 우리는 그저 표시하기 위해 URL을 얻었지만, 함수는 그보다 더한 작업도 할 수 있습니다 — (예를 들면) 우리는 그냥  템플릿을 렌더링한다고 해서 우리의 데이터베이스를 삭제하고 싶지 않을 것입니다.



#### <span style="color:blue">베이스 템플릿 업데이트</span>

베이스 템플릿(**/locallibrary/catalog/templates/\*base_generic.html\***)을 열고 **All books**의 URL 링크 부분에 **{% url 'books' %}** 코드를 삽입하세요. 이건 모든 페이지 링크에 적용될 것입니다 (우리는 "books" url mapper를 만들었으니 이렇게 넣을 수 있습니다.)

```html
<li><a href="{% url 'index' %}">Home</a></li>
<li><a href="{% url 'books' %}">All books</a></li>
<li><a href="">All authors</a></li>
```



### <span style="color:blue">어떻게 보일까요?</span>

우리는 아직 도서 목록을 표시할 수 없습니다. 왜냐면 디펜던시(dependency)가 없기 떄문이죠. 책의 상세 페이지에 대한 URL이 필요한데, 이 URL은 각 도서에 대한 하이퍼 링크입니다. 다음 섹션 이후에는 목록보기와 상세보기가 모두 표시될 것입니다.



## <span style="color:blue">Book 상세 페이지</span>

URL `catalog/book/*<id>*` ( `*<id>*` 는 book의 primary key입니다)에 접근해서, Book의 상세 페이지는 특정 책의 정보를 보여줄 것입니다. `Book` 모델 (author, summary, ISBN, language, 그리고 genre) 같은 필드에 추가로, 우리는 가능한 복사본(`BookInstances`) 의 상세부분, 즉 상태와 기대하는 반납일, 기록 그리고 아이디 등을 리스트화 할 것입니다. 이렇게 하면 독자들이 책의 리스트를 확인할 뿐만 아니라, 언제 책을 대여할 수 있는지에 대한 여부를 확인할 수 있게 해줍니다.



### <span style="color:blue">URL 매핑</span>

**/catalog/urls.py** 파일을 열고 아래 두꺼운 글씨로 된 **book-detail** URL mapper를 추가하세요. `path()` 함수는 연관된 제네릭 클래스 기반의 상세 뷰와 이름에 대한 패턴을 정의합니다.

```python
urlpatterns = [
    path('', views.index, name='index'),
    path('books/', views.BookListView.as_view(), name='books'),
    path('book/<uuid:pk>', views.BookDetailView.as_view(), name='book-detail'),
]
```

*book-detail URL 패턴은 우리가 원하는 책의 id를 캡처하기 위해 특별한 구문을 사용합니다. 구문은 간단합니다: 꺾쇠 괄호는* 캡처하는 URL의 일부를 정의하고 뷰가 캡처 된 데이터에 액세스하는 데 사용할 수있는 변수의 이름을 지정합니다. *예를 들어,* **<something>**은 패턴을 캡처해서 "something"이라는 변수에 데이터를 담아 전달합니다. 우리는 선택적으로 변수 이름 앞에 데이터 형식 (int, str, slug, uuid, path)을 정의하는 [converter specification](https://docs.djangoproject.com/en/2.0/topics/http/urls/#path-converters)을 사용할 수 있습니다.

여기에서 우리는 book id을 캡쳐하기 위해 `'<uuid:pk>'` 라는 특별히 포맷화된 문자열을 활용할 것입니다. 그리고 `pk` (primary key의 단축어)라는 이름의 파라미터로서 뷰로 넘겨줄 것입니다. 도서 모델에 정의 된대로 데이터베이스에 도서를 고유하게 저장하는 데 사용되는 ID입니다.

(번역 봉사자 주: uuid를 읽지 못한다면[NoReverseMatch] <int:pk>로 해보십시오.)

> **Note**: 앞에서 언급했듯이, 관련된 URL 은 실제로는 `catalog/book/<digits>` 입니다.(우리가 **catalog** application 에 있기때문에, `/catalog/` 를 가정합니다).

> **명심**: 통상 class-based detail view 는 **pk** 라는 이름을 가진 파라미터로 전달됩니다. 만일 자체적으로 function view 를 만든다면 어떤 이름이라도 사용 가능합니다. 혹은 이름이 없는 argument 에 정보를 넣어 전달 할 수도 있습니다.



#### <span style="color:blue">Regular expression 을 이용한 고급 path matching</span>

**Note**: 튜터리얼과는 관련 없습니다. 하지만 향후 Django 스타일로 개발하기 위해선 매우 유용한 팁입니다.

`path()` 를 이용한 패턴 검색은 간단하고 일반적인 경우 - 예를 들어 단지 특정 문자열이나 숫자가 있는지 - 매우 유용합니다. 만일 좀더 세밀한 조건 - 예를 들어 특정 문자열 길이를 갖는 문자열 검색 - 으로 검색 하고자 한다면 . [re_path()](https://docs.djangoproject.com/en/2.0/ref/urls/#django.urls.re_path) 를 사용하길 권고 드립니다.

re_path() 는 [Regular expression](https://docs.python.org/3/library/re.html) 을 사용한다는 점만 빼고 path() 와 똑 같습니다. 예를 들어 앞서 서술한 path 는 다음과 같이 re_path 로 대체 사용 가능합니다.

```
re_path(r'^book/(?P<pk>\d+)$', views.BookDetailView.as_view(), name='book-detail'),
```

*Regular expressions* 은 정말로 파워풀한 매핑 툴 입니다. 하지만 솔직히 직관적이지 못하고 초보자에게는 두려운 존재입니다. 아래는 간단한 지침서 입니다!

첫번째로 regular expressions 은 the raw string literal syntax 로 선언되어야 합니다 (즉, 다음처럼 '< >' 로 닫혀 있어야 한다는 겁니다: **r'<your regular expression text goes here>'**).

패턴 매칭을 정의하기 위해 알아야 될 문법의 핵심적인 부분들입니다:

| Symbol          | Meaning                                                      |
| --------------- | ------------------------------------------------------------ |
| ^               | 기술된 text 로 그 문자열이 시작되는지                        |
| $               | 기술된 text 로 그 문자열이 끝나는지                          |
| \d              | 숫자(0, 1, 2, ... 9) 인지                                    |
| \w              | word character 인지. 즉, 대소문자, 숫자, underscore character (_) 로만 구성된 단어인지. |
| +               | 하나 이상의 선행 문자가 있는지. 예를 들어, 하나 이상의 숫자와 매치한다면 `\d+`.를 하나 이상의 'a' 문자와 매치 한다면 `a+` |
| *               | 매치되는 문자열이 없거나 많은 경우, 예를 들어 매칭이 안되거나 한 단어를 찾고자 할 경우 `\w*` |
| ( )             | 괄호안에 있는 패턴의 일부를 선택할때. 선택된 값은 unnamed parameter 로 view 에게 전달된다. (만일 여러 패턴들이 선택 되었다면 선택된 순서대로 연관된 파라미터로써 전달 될것입니다. |
| (?P<*name*>...) | (...에 표기된) 패턴을 명명한 variable로 변환합니다(이 경우에는 "name" 입니다). 변환한 이름을  view 에 지정한 이름으로 넘깁니다. 그러므로 당신의 view 에서는 반드시 argument명을 동일하게 해주어야 합니다! |
| [  ]            | 집합 set 안에 있는 글자중 한개와 매치 될때. 예를 들어 [abc] 는 'a',혹은 'b' 혹은 'c' 와 매치되는지. [-\w] 는 '-' 한 글자 인지 혹은 '-'를 포함한 단어와 매치 하는지를 나타냅니다. |

대부분의 다른 글자들은 문자 그대로 받아 들여 집니다!

몇 가지 실제 패턴 예제를 보도록 합시다: 

| Pattern                        | Description                                                  |
| ------------------------------ | ------------------------------------------------------------ |
| **r'^book/(?P<pk>\d+)$'**      | 이것은 우리가 URL mapper에서 사용한 Regular Expression입니다. 이 표현식은 먼저 문자열이 `book/` 으로 시작하는지 검사하고 (**^book/**), 그 다음에 한 개이상의 숫자가 오는지 (`\d+`), 그리고 문자열이 끝나기 전에 숫자가 아닌 문자가 들어 있지는 않는 지 검사합니다.     또한 이 표현식은 모든 숫자들을 변환하고 **(?P<pk>\d+)** 변환된 값을 view 에 'pk'라는 이름의 parameter로 넘깁니다. **변환된 값은 항상 String type으로 넘어갑니다****!**     예를 들어, 이 표현식은 `book/1234` 을 매칭합니다. 그리고 변수 `pk='1234'` 를 view에 넘깁니다. |
| **r'^book/(\d+)$'**            | 이 표현식은 위의 표현식과 동일한 URL들을 매칭합니다. 변환된 정보는 명명되지 않은 argument로 view에 전달됩니다. |
| **r'^book/(?P<stub>[-\w]+)$'** | 이 표현식은 문자열 처음 부분에 `book/` 으로 시작하는지 검사하고 (**^book/**), 그리고 한 개 또는 그 이상의 '-' 나 word character가 오고 (**[-\w]+**), 그렇게 끝내는지를 매칭합니다. 이 표현식 또한 매칭된 부분을 변환하고 view 에 'stub' 라는 이름의 parameter로 전달합니다.     This is a fairly typical pattern for a "stub". Stubs are  URL-friendly word-based primary keys for data. You might use a stub if  you wanted your book URL to be more informative. For example `/catalog/book/the-secret-garden` rather than `/catalog/book/33`. |

당신은 다양한 패턴들을 한번의 매칭을 통해 변환시킬 수 있습니다. 그러므로 다양한 정보들을 URL안에 인코딩할 수 있습니다.

**Note**: 추가적으로, 특정 날짜에 출간된 책 목록을 URL에 인코딩할 수 있을지 생각해보세요. 그리고 어떤 Regular Expression이 해당 URL을 매칭할 수 있을까요?

#### Passing additional options in your URL maps

우리가 여태까지 사용하지는 않았지만, 쓸모있을만한 한 기능은 당신이 [additional options](https://docs.djangoproject.com/en/2.0/topics/http/urls/#views-extra-options) 을 정의내리고 view에 전달할 수 있다는 것입니다. 이 option들은 dictionary 형태로 정의하고 `path()` 함수의 3번째 명명되지 않은 argument로 전달됩니다. 이 방식은 만약, 당신이 동일한 view 를 다른 곳에서 재활용하려고  하거나, 각 상황에 맞게 조절하려고 할 때 유용합니다. (이 경우, 우리는 각 경우에 따라 다른 template 을 제공합니다).

```python
path('url/', views.my_reused_view, {'my_template_name': 'some_path'}, name='aurl'),
path('anotherurl/', views.my_reused_view, {'my_template_name': 'another_path'}, name='anotherurl'),
```

**Note:** 추가된 options 과 변환된 패턴들 중 명명된 것들은 view 에 명명된 arguments로 전달됩니다. 만약 당신이 **동일한 이름을** 변환된 패턴들과 추가적인 option에 사용한다면, 오직 변환된 패턴들만이 view에 보내지게 됩니다. ( 추가된 option들에 있는 값들은 모두 버려집니다).

 

### 뷰 (클래스 기반)

**catalog/views.py** 을 열고, 다음 코드를 파일 끝에 넣으세요:

```python
class BookDetailView(generic.DetailView):
    model = Book
```

다됬습니다! 이제 해야될 일은 **/locallibrary/catalog/templates/catalog/book_detail.html** template를 만들면, view는 template에 URL mapper에 의해 찾고자 하는 데이터베이스에 있는 특정 `Book ` 레코드의 정보를 전달할 겁니다. template 안에서 template 변수 `object` 또는 `book`(즉, 일반적으로는 "해당_모델_명") 으로 책 목록에 접근할 수 있습니다.

만약 필요하다면, 사용하고 있는 template 또는 template 안에서 book을 참조하는 데 사용되는 context  object의 이름을 바꿀 수 있습니다. 또한, 예를 들어 context에 정보를 추가하는 식으로, 메서드를 오버라이드 할 수도  있습니다.



#### 만약 해당 레코드가 존재하지 않는다면 무슨 일이 일어날까요?

만약 요청된 레코드가존재하지 않는다면, 제네릭 클래스 기반의 detail view는 `Http404` exception 이 저절로 발생할 것입니다. — 만들어질 때, 적절한 "resource not found" 페이지를 알아서 보여줄 것입니다. 만약 원한다면 당신이 해당 페이지를 수정할 수 있겠죠.

이런 일이 어떻게 발생하는지 원리를 조금 알려줄게요. 밑에 있는 코드는 만약 당신이 제네릭 클래스 기반의 detail view를 **사용하지 않는다면**, 클래스 기반의 view를 어떻게 함수 형태로 표현 할 수 있는지 보여줍니다.

```python
def book_detail_view(request, primary_key):
    try:
        book = Book.objects.get(pk=primary_key)
    except Book.DoesNotExist:
        raise Http404('Book does not exist')

    return render(request, 'catalog/book_detail.html', context={'book': book})
```

view는 먼저 model로 부터 특정 book 의 레코드를 얻으려고 할 것입니다. 이 시도가 실패하면, view 는 "해당 책이 존재하지 않음"을 알려주면서 `Http404` exception가 발생할 것입니다. 그리고 마지막 과정은 ,평소처럼, book 정보를 `context` parameter (dictionary 형태로)에 집어 넣고 template 이름과 함께 `render()` 함수를 호출 할 것입니다.

혹, 만약 해당 레코드를 찾지 못한다면, 우리는 손쉬운 방법으로 `Http404` exception을 발생하기 위해`get_object_or_404()` 함수를 사용할 수도 있어요.

```python
from django.shortcuts import get_object_or_404

def book_detail_view(request, primary_key):
    book = get_object_or_404(Book, pk=primary_key)
    return render(request, 'catalog/book_detail.html', context={'book': book})
```



### <span style="color:blue">상세 뷰 템플릿 생성하기</span>

**/locallibrary/catalog/templates/catalog/book_detail.html** 파일을 만들고, 아래 텍스트를 복사 붙여넣기 하세요. 위에서 설명한대로, 이 파일명은 제네릭 클래스 기반 상세 뷰의 디폴트 파일명입니다. (`catalog` 애플리케이션의 `Book` 모델을 위한 상세 뷰)

```html
{% extends "base_generic.html" %}

{% block content %}
  <h1>Title: {{ book.title }}</h1>

  <p><strong>Author:</strong> <a href="">{{ book.author }}</a></p> <!-- author detail link not yet defined -->
  <p><strong>Summary:</strong> {{ book.summary }}</p>
  <p><strong>ISBN:</strong> {{ book.isbn }}</p>
  <p><strong>Language:</strong> {{ book.language }}</p>
  <p><strong>Genre:</strong> {% for genre in book.genre.all %} {{ genre }}{% if not forloop.last %}, {% endif %}{% endfor %}</p>

  <div style="margin-left:20px;margin-top:20px">
    <h4>Copies</h4>

    {% for copy in book.bookinstance_set.all %}
      <hr>
      <p class="{% if copy.status == 'a' %}text-success{% elif copy.status == 'm' %}text-danger{% else %}text-warning{% endif %}">{{ copy.get_status_display }}</p>
      {% if copy.status != 'a' %}
        <p><strong>Due to be returned:</strong> {{copy.due_back}}</p>
      {% endif %}
      <p><strong>Imprint:</strong> {{copy.imprint}}</p>
      <p class="text-muted"><strong>Id:</strong> {{copy.id}}</p>
    {% endfor %}
  </div>
{% endblock %}
```



이 템플릿의 작가 링크는 비어있는 URL입니다. 왜냐면 우리는 아직 작가 상세 페이지를 만들지 않았기 때문이죠. 만약 페이지가 존재한다면, URL을 아래와 같이 업데이트 해야합니다.

```html
<a href="{% url 'author-detail' book.author.pk %}">{{ book.author }}</a>
```

조금 더 커졌지만, 이 템플릿의 대부분의 것들은 이미 언급된 것들입니다:

- content 블럭을 오버라이드 해서 우리의 기본 템플릿을 extend하였습니다.
- 우리는 조건 판단 처리를 해서 특정 컨텐츠가 있을지 없는지 체크하여 표시합니다.
- `for` 루프를 활용하여 objects들의 리스트를 표현합니다.
- 우리는 context fields를 dot notation를 활용합니다 (왜냐하면 우리는 detail generic view를 사용하는데, context의 이름은 `book` 이기에 우리는 "`object`"를 사용할 수 있습니다)

우리가 본 적 없는 한가지 흥미로운 점은 바로 `book.bookinstance_set.all()` 함수입니다. 이 메소드는 Django에 의해 자동으로 만들어진 메소드입니다. 이 메소드는 `Book` 과 관련된 `BookInstance` 레코드 집합을 반환합니다. 

```html
{% for copy in book.bookinstance_set.all %}
  <!-- code to iterate across each copy/instance of a book -->
{% endfor %}
```

이 메소드는 관계의 한쪽("one")에만 `ForeignKey`(one-to many) 필드를 선언했기 때문에 필요합니다. 다른("many") 모델에서 아무것도 선언하지 않았기 때문에 관련 레코드 집합을  가져올 필드가 없습니다. 이 문제를 해결하기 위해, Django는 지금 우리가 사용하고 있는 "reverse lookup"이라는  적당한 이름의 함수를 만들었습니다. 이 함수의 이름은 `ForeignKey`가 선언되어있는 모델 이름을 소문자로 만들고, 그 뒤에 `_set`을 붙이면 됩니다. (따라서 `Book`에서 만든 함수는 `bookinstance_set()`가 되겠죠.)

**Note**: 여기서 우리는 모든 레코드를 가져오기 위해 `all()` 을 사용했습니다(기본값이죠). 반대로 당신은 `filter()` 메서드를 사용해서 레코드의 부분 집합을 가져올 수 있지만, 당신은 template에서 이걸 직접할 수는 없어요. 왜냐하면 함수의 arguments를 정할 수 없으니까요.

만약 순서를 정의하지 않는다면 (클래스 기반 view 또는 model에서), 당신은 개발 서버로 부터 다음과 같은 에러를 받게 될 거라는 것 또한 알아두세요.

```html
[29/May/2017 18:37:53] "GET /catalog/books/?page=1 HTTP/1.1" 200 1637
/foo/local_library/venv/lib/python3.5/site-packages/django/views/generic/list.py:99: UnorderedObjectListWarning: Pagination may yield inconsistent results with an unordered object_list: <QuerySet [<Author: Ortiz, David>, <Author: H. McRaven, William>, <Author: Leigh, Melinda>]>
  allow_empty_first_page=allow_empty_first_page, **kwargs)
```

이것은 [paginator object](https://docs.djangoproject.com/en/2.0/topics/pagination/#paginator-objects) 가 데이터베이스에서 ORDER BY 명령어가 실행되었을 것이라고 예상하기 때문에 발생하는 것입니다. 이러한 것이 없다면, 레코드들이 정확한 순서로 반환되었는지 알 수가 없어요! 

이 튜토리얼은 아직 **Pagination** 에 도달하지는 않았습니다.(곧 하게될 거에요) `sort_by()` 에 parameter를 전달하여 사용하는 것은 (위에서 이야기했던 `filter()` 와 동일한 역할을 합니다.) 사용할 수 없기 때문에, 당신은 3개의 선택권중에 하나를 골라야합니다:

1. 모델의 클래스 메타 선언 안에 순서를 추가합니다.
2. order_by() 를 지정하여 커스텀 클래스 기반 보기에 쿼리 세트 속성을 추가합니다..
3. 맞춤 클래스 기반 보기에 get_queryset 메소드를 추가하고 order_by()도 지정합니다.

만약  `Author` model에 `class Meta` 사용하기를 결정했다면 (커스터마이징 된 클래스 기반 view만큼 유연하진 않겠지만, 쉬운 방법입니다), 아마 밑에 코드와 비슷하게 끝날 거에요:

```python
class Author(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField(null=True, blank=True)
    date_of_death = models.DateField('Died', null=True, blank=True)

    def get_absolute_url(self):
        return reverse('author-detail', args=[str(self.id)])

    def __str__(self):
        return f'{self.last_name}, {self.first_name}'

    class Meta:
        ordering = ['last_name']
```

물론, 해당 field는 `last_name` 이어야 할 필요는 없습니다: 다른 어떤 것도 될 수 있어요.

마지막으로, 그러나 적어도 우리는 정렬(sort)을 attribute/column에 따라 해줘야 합니다. unique 여부와 상관없이 당신의 database의 퍼포먼스 이슈를 위해서 필요합니다. 물론, 이것은 여기서 필요한 것이 아니며 어떻게 보면 약간 진도를 앞서나가는 것 같지만 이런 작은 사용자와 프로젝트에서도 미래의 프로젝트를 위해서 미리 명심해 두는 것이 좋습니다.



## [어떻게 보일까요?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#어떻게_보일까요_2)

이 시점에서 책 목록 페이지와 책 세부 사항 페이지를 보기 위한 모든 것을 만들어 놓았어야 합니다. 명령어 (`python3 manage.py runserver`) 를 입력하여 서버를 실행하고 브라우저를 열어 http://127.0.0.1:8000/ 에 접속해보세요.

**주의!:** 아직 작가 목록 페이지나 작가 세부 사항 페이지를 클릭하시면 안됩니다. — 당신은 challenge에서 이것들을 만들어보게 될 거에요!

책 목록을 보기 위해 **All books** 링크를 클릭하세요. 

![Book List Page](./images/book_list_page_no_pagination.png)

그리고 당신의 책들 중 하나를 클릭해보세요. 만약, 현재까지의 과정을 성공적으로 따라왔다면, 당신은 다음과 같은 스크린샷을 볼 수 있을 겁니다.

![Book Detail Page](./images/book_detail_page_no_pagination.png)

## [Pagination](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#pagination)

만약 레코드가 몇 개 없다면, 현재의 책 목록 페이지도 괜찮습니다. 하지만 수십,수백개의 레코드를 갖고 있다면, 페이지는 가져오는 데 점차 시간이 늘어날 겁니다(양이 너무 많아 탐색하는게 체감적으로 힘들어질 정도로요). 해결 방법은 당신의 list view에  pagination을 추가하는 것입니다, 그리고 pagination은 각 페이지마다 보여주는 항목들을 감소시켜줄 것입니다.

장고는 pagination에 대한 훌륭한 지원을 하고 있습니다. 더욱 좋은 점은,이 지원은 제네릭 클래스 기반 list view에 내장되어 있어서, 당신이 pagination을 하기 위해 해야될 것이 많지 않다는 것입니다!

### [Views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#views)

**catalog/views.py**열고, 밑에 굵은 글씨로 작성되어있는 `paginate_by` 줄을 추가해주세요.

```python
class BookListView(generic.ListView):
    model = Book
    paginate_by = 10
```

이 것이 추가됨에 따라, 당신이 10개 이상의 레코드를 갖게 되면 view는 template에 보내는 데이터에 pagination  하는 것을 시작할 것입니다. 다른 page들을 GET parameter들을 통해 접근할 수 있습니다— 2 페이지에 접속하려면,  다음과 같은 URL을 사용하세요: `/catalog/books/**?page=2**`.

### [Templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#templates)

자료들이 pagination되었습니다, 우리는 template에 결과들을 훑어볼 수 있도록 만들어야합니다. 우리는 이러한 기능이 모든  list view들에 필요할 수 있으므로, 우리는 base template에 추가하는 방향으로 작업을 진행하려고 합니다. 

**/locallibrary/catalog/templates/\*base_generic.html\*** 열고 content block 밑에 다음과 같은 pagination block을 복사하여 추가하세요 (아래에 굵은 글씨로 강조표시를  해놓았습니다). 첫번째로 코드는 현재 페이지가 pagination이 가능한지 체크합니다. 만약 가능하다면, 다음 페이지와 전  페이지를 적절히 추가합니다 (현재 페이지 넘버도요). 

```html
{% block content %}{% endblock %}

{% block pagination %}
  {% if is_paginated %}
    <div class="pagination">
      <span class="page-links">
        {% if page_obj.has_previous %}
          <a href="{{ request.path }}?page={{ page_obj.previous_page_number }}">previous</a>
        {% endif %}
        <span class="page-current">
          <p>Page {{ page_obj.number }} of {{ page_obj.paginator.num_pages }}.</p>
        </span>
        {% if page_obj.has_next %}
          <a href="{{ request.path }}?page={{ page_obj.next_page_number }}">next</a>
        {% endif %}
      </span>
    </div>
  {% endif %}
{% endblock %} 
```

만약 현재 페이지에 pagination이 사용중이라면, `page_obj` 가 [Paginator](https://docs.djangoproject.com/en/2.0/topics/pagination/#paginator-objects) 오브젝트 로서 존재합니다. 해당 오브젝트는 현재 페이지, 전 페이지, 페이지 수는 얼마나 되는 지등의 모든 정보를 제공합니다.

pagination 링크를 만들기 위해 우리는 `{{ request.path }}` 를 이용하여 현재 페이지의 URL을 가져오도록 할 겁니다. 우리가 pagination을 하는 객체와 독립적이기 때문에 유용합니다.

다됬네요!

### [어떻게 보일 까요?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#어떻게_보일_까요)

밑에 있는 스크린샷은 pagination이 어떻게 보이는지를 알려줍니다 — 만약 에디터베이스에 10개가 넘는 제목을 추가하지 않았다면, **catalog/views.py** 파일에 있는 `paginate_by`  줄에 있는 숫자를 낮춤으로써 쉽게 테스트할 수 있습니다. 밑에 있는 결과는 우리가 `paginate_by = 2`로 바꾼 겨로가입니다.

next/previous 링크와 함께 보이는 밑에 pagination 링크는 당신이 어느 페이지에 있느냐에 따라 달리 표시가 됩니다. 

![Book List Page - paginated](./images/book_list_paginated.png)

## [도전과제](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#도전과제)

이번 글의 과제는 프로젝트를 완수하기 위해 필요한 작가 세부 사항과 목록 view들을 만드는 것입니다. 이 과제를 통해 해당 URL들을 사용가능하게 만들어야 해요:

- `catalog/authors/` — 작가 목록.
- `catalog/author/*<id>*` —*`<id>`*라는 이름의 기본키를 이용한 작가의 세부 사항

URL mappers에 필요한 코드들과 view들은 ,사실상, 우리가 위에서 만들었던 `Book` 목록과 세부 사항 view들과 동일해야 합니다. template들은 다르겠지만, 비슷한 동작을 가지고 있을 겁니다.

**Note**:

- 작가 목록 페이지를 위한 URL mapper를 만들고나면, **All authors** base template에 있는 **All authors** 링크 또한 업데이트 해야될 필요를 느끼게 될 겁니다. 우리가 **All books** 링크 업데이트 때 했던, [수행 과정](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#update_the_base_template)을 따라해주세요.

- 작가 세부 사항 페이지에 대한 URL mapper를 만들고나면, 당신은 

  book detail view template

   (

  /locallibrary/catalog/templates/catalog/book_detail.html

  ) 또한 업데이트 해야 합니다. 그래야 작가 링크가 당신이 새로 만든 작가 세부 사항 페이지를 가리키거든요. (비어 있는 URL로 있기 보다는 말이죠). 굵게 되어 있는 부분을 template 내의 태그에 넣어주세요.  

  ```html
  <p><strong>Author:</strong> <a href="{% url 'author-detail' book.author.pk %}">{{ book.author }}</a></p>
  ```

모두 마치면, 당신의 페이즈들은 아마 밑의 스크린샷과 비슷하게 보일 겁니다.

![Author List Page](./images/author_list_page_no_pagination.png)



![Author Detail Page](./images/author_detail_page_no_pagination.png)



## [요약](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Generic_views#요약)

축하합니다, 도서관의 가장 기본적인 기능들은 모두 끝났어요! 

이번 글에서, 우리는 제네릭 클래스 기반의 list view와 detailv view에 대해서 배웠고, 이를 이용하여 책들과 작가들을 보여주기 위한 페이지를 만들었습니다. 이 과정중에 우리는 정규표현식을 이용하여 패턴 매칭을 하는 것도 배웠고, URL로 부터 데이터를 view에 보내는 것도 배웠습니다. 또한, template을 이용하는 몇 가지 트릭도 배웠죠. 마지막에는 list views에 어떻게 paginate를 할 수 있는지도 보았습니다. 이로 인해 우리는 레코드들이 많아져도 리스트를 관리할 수 있게 되었습니다.

다음 글에서 우리는 유저 계정을 지원하도록 도서관을 확장하고, 이를 통해 유저 인증, 권한, 세션, forms을 볼 것입니다.



# Django Tutorial Part 7: Sessions framework

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions



이 튜토리얼에서는 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website을 확장시킬 것입니다. 방문 수를 셀 수 있는 session-based 기능을 더한 홈페이지입니다. 이것은  상대적으로 간단한 예제인데, 이는 당신의 홈페이지에서 익명의 유저들에게 지속적으로 서비스를 제공하는 session  framework의 사용법입니다.

| Prerequisites: | Complete all previous tutorial topics, including [Django Tutorial Part 6: Generic list and detail views](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Generic_views) |
| -------------- | ------------------------------------------------------------ |
| Objective:     | To understand how sessions are used.                         |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#개요)

이전 튜토리얼에서 우리가 만든 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website는 카탈로그에서 유저가 책과 저자를 검색할 수 있도록 합니다. 컨텐츠가 Database로부터 다이나믹하게 생성되기 때문에, 모든 유저는 사용시에 필수적으로 같은 페이지와 정보 타입에 필수적으로 접근할 것입니다.

실제 도서관에서는 각각의 유저들에게 그들의 이전 사이트 사용과 선호 등에 기반한 커스텀된 경험을 제공하고 싶을지도 모릅니다  . 예를 들어, 유저가 이미 알고 있는 경고 메세지들을 숨길 수도 있습니다. 그 유저들이 다음에 사이트를 방문하거나 그들의  선호사항(e.g. 그들이 각 페이지에서 보여지길 원하는 검색의 결과 수)에 대해서 저장하는 것을 말합니다. 

session framework는 당신이 이 행동의 분류하도록 하며, 각 사이트 방문자에 기반한 임의의 데이터를 검색하거나 저장하도록 합니다. 

## [세션이란?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#세션이란)

웹 브라우저와 서버가 HTTP 프로토콜을 통해서 하는 모든 커뮤니케이션은 무상태(stateless)라고 합니다. 프로토콜이  무상태라는 뜻은 클라이언트와 서버 사이의 메시지가 완벽하게 각각 독립적이라는 뜻입니다. — 여기엔 이전 메시지에 근거한 행동이나  순서(sequence)라는 것이 존재하지 않습니다. 결국, 만약 사이트가 클라이언트와 계속적인 관계를 유지하는 것을 당신이  원한다면, 당신이 직접 그 작업을 해야합니다.

세션이라는 것은 Django 그리고 대부분의 인터넷에서 사용되는 메카니즘으로, 사이트와 특정 브라우저 사이의 "state"를 유지시키는 것입니다. 세션은 당신이 매 브라우저마다 임의의 데이터를 저장하게 하고, 이 데이터가 브라우저에 접속할 때 마다  사이트에서 활용될 수 있도록 합니다. 세션에 연결된 각각의 데이터 아이템들은 "key"에 의해 인용되고, 이는 또다시 데이터를  찾거나 저장하는 데에 이용됩니다.

장고는 특정 *session id* 를 포함하는 쿠키를 사용해서 각각의 브라우저와 사이트가 연결된 세션을 알아냅니다. 실질적인 세션의 *data* 사이트의 Database에 기본 설정값으로 저장됩니다 (이는 쿠키안에 데이터를 저장하는 것보다 더 보안에 유리하고, 쿠키는  악의적인 사용자에게 취약합니다). 당신은 Django를 다른 장소 (캐시, 파일, "보안이 된" 쿠키)에 저장하도록 설정할 수  있지만, 그러나 기본 위치가 상대적으로 더 안전합니다.

## [세션 사용설정하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#세션_사용설정하기)

세션설정은 처음에 skeleton website를 생성했을 때 (in tutorial 2) 자동으로 사용할 수 있도록 설정되었습니다. 

세션사용설정은 프로젝트 settings.py에서 아래와 같이 `INSTALLED_APPS` 와 `MIDDLEWARE` 부분에 있습니다.

```python
INSTALLED_APPS = [
    ...
    'django.contrib.sessions',
    ....

MIDDLEWARE = [
    ...
    'django.contrib.sessions.middleware.SessionMiddleware',
    ....
```

## [세션 사용하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#세션_사용하기)

`session` 속성은 `request` parameter 에서 파생된 view 안에 있습니다. ( view 로 전달되는 H`ttpRequest ` 의 첫번째 함수 ). 이 세션의 속성은 현재의 사용자(정확히는 브라우저) 의 site 에 대한 connection 을 의미합니다. 브라우저의 cookie 안에 정의 되어 있습니다.

이 `session` 속성은 사전 같은 객체인데 여러번 읽고 쓰고 심지어 수정까지 가능합니다.여러분은  다양한 dictionary 연산 - 즉, 모든 데이타 삭제, Key 가 존재하는지 데이타를 통한 looping 기타등등. 무엇보다 표준적인 "dictionary" API 를 통해 값을 가져오거나 저장 할 수 있습니다.

아래 코드는 key 값이 "my_car" 인 데이타를 어떻게 읽고 쓰고 삭제하는지 보여줍니다. 물론 그 key 값은 현재의 session (브자우져와 싸이트의 연결정보) 과 연관되어진 key 입니다.

**Note**: 장고가 대단한점 한가지는 여러분이 이런 session 의 매카니즘에 생각하지 않게끔 한다는 점입니다. 만일 view 안에 있는 아래의 code 를 사용하면 **오직 현재의 브라우저만**이 현재의 request 에 관한 `my_car` 정보를 알 수 있다는 겁니다. 

```python
# Get a session value by its key (e.g. 'my_car'), raising a KeyError if the key is not present
my_car = request.session['my_car']

# Get a session value, setting a default if it is not present ('mini')
my_car = request.session.get('my_car', 'mini')

# Set a session value
request.session['my_car'] = 'mini'

# Delete a session value
del request.session['my_car']
```

이 API 는 또한 여러가지 다른 방법을 제공하는데 그들은 대부분  관련된 session cookie 를 다루는데 사용되는 것들 입니다. 예를 들어, 그 cookies 가 사용자의 브라우져에서  지원이 되는지 태스트하거나, 만기일을 알아본다던지, 만기된 session 을 삭제 한다던지. 좀더 알아보고 싶다면 [How to use sessions](https://docs.djangoproject.com/en/2.0/topics/http/sessions/) (Django docs) 에서 그 API 를 배울수 있습니다.

## [세션 데이터 저장하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#세션_데이터_저장하기)

기본적으로 장고는 세션 데이타를 세션 database 에 저장합니다. 그리고 그 session cookie 를 필요할때 client  에게로 내려보내는 거지요. 즉, session 정보가 변경되었거나 삭제 되었을떄. 앞장에서 기술한것처럼, 만일 session  key 값을 이용해서 어떤 정보가 변경이 되었다면 우리는 이에 대해 염려 할 필요가 없습니다.예를 들자면 : 

```python
# This is detected as an update to the session, so session data is saved.
request.session['my_car'] = 'mini'
```

만일 당신이 session data 안에 있는 어떤 정보를 수정 했다면 장고는 여러분이 수정한걸 알아채지 못합니다. (예를 들어,  만일 "my_car" 안의 "wheels" 정보를 수정 했다면 ). 이경우 명시적으로 그 session 이 수정 되었다고 아래의  코드처럼 표현해 주어야 합니다.

```python
# Session object not directly modified, only data within the session. Session changes not saved!
request.session['my_car']['wheels'] = 'alloy'

# Set session as modified to force data updates/cookie to be saved.
request.session.modified = True
```

**Note**: You can change the behavior so the site will update the database/send cookie on every request by adding `SESSION_SAVE_EVERY_REQUEST = True` into your project settings (**locallibrary/locallibrary/settings.py**).

## [간단한 예제 — 방문자수 받아오기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#간단한_예제_—_방문자수_받아오기)

간단한 실제 예제로서, 우리는 현재 유저가 LocalLibrary 홈페이지에 몇 번이나 방문했는지 알려주도록 업데이트할 것입니다.  **/locallibrary/catalog/views.py** 을 열고 밑에 굵게 표시된 부분을 추가해서 바꿔봅시다. 

```python
def index(request):
    ...

    num_authors = Author.objects.count()  # The 'all()' is implied by default.

    # Number of visits to this view, as counted in the session variable.
    num_visits = request.session.get('num_visits', 0)
    request.session['num_visits'] = num_visits + 1

    context = {
        'num_books': num_books,
        'num_instances': num_instances,
        'num_instances_available': num_instances_available,
        'num_authors': num_authors,
        'num_visits': num_visits,
    }

    # Render the HTML template index.html with the data in the context variable.
    return render(request, 'index.html', context=context)
```

먼저 `'num_visits'` 세션 키 값을 가져오도록 합니다, 그리고 만약 전에 방문한적이 없다면 0이 되도록 합니다. 매번 요청받을 때 마다, 값을 증가시키고  세션에 값을 저장합니다 (유저의 다음 방문을 위해서요). context 변수를 통해 template에 `num_visits` 변수가 전달됩니다.

**Note**: 이 지점에서 우리는 브라우저가 쿠키를 지원하는 지 그렇지 않은지 테스트할 수도 있습니다(예제로서 [How to use sessions](https://docs.djangoproject.com/en/2.0/topics/http/sessions/) 를 보도록하십시오). 또한 쿠키를 지원하는 지와는 별개로 UI를 디자인할 것입니다. 

메인 HTML template(**/locallibrary/catalog/templates/index.html**) "Dynamic content" 섹션 밑 부분에 context 변수가 보일 수 있도록 밑에 보이는 굵은 선으로 표시된 코드를 추가해주세요:

```python
<h2>Dynamic content</h2>

<p>The library has the following record counts:</p>
<ul>
  <li><strong>Books:</strong> {{ num_books }}</li>
  <li><strong>Copies:</strong> {{ num_instances }}</li>
  <li><strong>Copies available:</strong> {{ num_instances_available }}</li>
  <li><strong>Authors:</strong> {{ num_authors }}</li>
</ul>

<p>You have visited this page {{ num_visits }}{% if num_visits == 1 %} time{% else %} times{% endif %}.</p>
```

바뀐 것을 저장해주시고 테스트 서버를 재시작해주세요. 페이지를 새로고침할 때 마다, 숫자가 업데이트되는 것을 보실 수 있을겁니다.



## [요약](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Sessions#요약)

익명 유저와의 상호작용을 증대하기 위해 세션을 이용하는 것이 얼마나 쉬운일인지 알 수 있었습니다. 

다음 장에서는, 인증과 권한부여(허가)에 관한 프레임워크를 설명할 것입니다. 그리고 유저 계정을 어떻게 지원할 수 있는지를 보도록 하죠. 



# Django Tutorial Part 8: User authentication and permissions

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication

이 튜토리얼에서는, 당신의 사이트에 그들의 계정으로 로그인을 어떻게 허락할 것인지,  그리고 그들의 로그인 여부와 그들에게 허가한 퍼미션에 따라서 사이트에서 무엇을 할 수 있게 하거나, 볼 수 있게 할 것인지에  대해서 알려줄 것입니다. 또한 예시의 일부분으로, 우리는 이 LocalLibrary website를 확장시켜서, 로그인, 로그아웃 페이지를 덧붙이고, 사용자와 staff들이 그들이 빌려간 책들을 볼 수 있는 특별한 페이지들도 추가할 것입니다.

| 선행학습: | 앞의 모든 튜토리얼을 모두 끝내세요. up to and including [Django Tutorial Part 7: Sessions framework](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Sessions). |
| --------- | ------------------------------------------------------------ |
| 목표:     | 사용자 인증과 허가를 어떻게 셋업하고, 이용하는 지에 대해 이해하기 |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#개요)

Django는 인증과 권위 부여 ("허가") 시스템을 제공합니다. 이것은 [previous tutorial](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Sessions) 에서 논의된 session framework에서 찾아볼 수 있는데, 사용자의 credentials을 검증하고 사용자들이 행동들의 허가 여부를 정의합니다. 이 프레임워크는 `Users` 와 `Groups` (한 번에 한 명 이상의 유저에게 허가를 적용하는 일반적인 방법)을 위한 빌트인 모델을 포함하는데,  permissions/flags는 that designate whether 사용자들이 일이나 , forms, 로그인 한 유저들의  뷰, 그리고 컨텐츠를 제한하는 뷰 툴을 조정합니다.

**Note**: Django의 인증시스템은 매우 일반적인 것을 목표로하기 때문에, 다른 웹사이트  인증시스템에서 제공하는 특정한 기능들을 제공하지 않습니다. 이런 문제에 대한 해결방법은 서드파티 솔루션을 이용하는 것입니다. 예를 들면, 로그인 시도 제한과 제3자에 대한 인증(e.g.OAuth)과 같은 기능들은 제공하지 않습니다.

이 튜토리얼에서 우리는 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) website안에서의 유저 인증을 어떻게 활성화하는지 보여주고, 당신의 로그인, 로그아웃 페이지를 만들며 당신의 모델과 페이지에 대한 권한 및 그 조정에 대해서 살펴볼 것입니다. 우리는 인증과 허가(권한)을 사용해서 사용자나 사서가 빌린 책들을 표시할  것입니다.

이 인증시스템은 매우 유연하므로 당신이 원한다면 단지 제공된 로그인 API를 호출하는 것만으로 scratch에서 온  당신의 URLs, forms, views와 templates를 작성할 수 있습니다. 그렇지만, 이 단계에서는 Django가  "보유한" 인증 views와 forms를 이용하여 우리의 로그인과 로그아웃페이지를 만들 것입니다. 우리는 여전히 어떤 템플릿들을  만들어야 합니다. 그러나, 이것들은 꽤 쉽습니다.

우리는 어떻게 퍼미션을 만드는지, 그리고 로그인 상태와 퍼미션을 views와 templates에서 어떻게 체크하는지에 대해서도 보여줄 것입니다.

## [인증하도록 하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#인증하도록_하기)

Authentication은 우리가 이미 [skeleton website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website) 을 생성했을 때 (in tutorial 2) 자동적으로 생서되었으므로 이 점에서는 할 것이 없습니다..

**Note**: 설정을 위해 필요한 것들은 모두 django-admin startproject  명령을 이용하여 앱을 생성했을 때 끝났습니다. 사용자들과 모델 퍼미션을 위한 데이터베이스 테이블들은 우리가 처음으로 python  manage.py migrate을 실행했을 때 만들어졌습니다.

아래에서 보여주는 것과 같이, 설정은 settings.py파일에서 INSTALLED_APPS과 MIDDLEWARE 부분을 셋업하는 것입니다.

```python
INSTALLED_APPS = [
    ...
    'django.contrib.auth',  #Core authentication framework and its default models.
    'django.contrib.contenttypes',  #Django content type system (allows permissions to be associated with models).
    ....

MIDDLEWARE = [
    ...
    'django.contrib.sessions.middleware.SessionMiddleware',  #Manages sessions across requests
    ...
    'django.contrib.auth.middleware.AuthenticationMiddleware',  #Associates users with requests using sessions.
    ....
```

## [users와 groups 만들기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#users와_groups_만들기)

[Django admin site](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Admin_site)를 tutorial 4에서 봤을 때 이미 당신은 첫번째 유저를 만들었습니다. 이는 슈퍼유저로서 `python manage.py createsuperuser`라는 명령으로 만들었죠. 우리의 슈퍼 유저는 이미 인증이 되어있고 모든 허가 권한을 가지고 있으므로, 우리는 일반적인 사이트 유저를 대표하는 테스트 유저를 만들 것입니다. 우리의 *locallibrary* groups과 website logins을 위해서 우리는 admin 사이트를 이용할 것인데, 이것이 가장 빠른 방법 중에 하나입니다.

Note: 당신은 아래에서 보여주는 것처럼 프로그램적으로 사용자를 추가할 수 있습니다. 만약에 사용자가 로그인하는 사이트를 개발한다면, 당신은 이것을 해야합니다. (사용자들이 admin site를 접근하게 해서는 안됩니다.)

```python
from django.contrib.auth.models import User

# Create user and save to the database
user = User.objects.create_user('myusername', 'myemail@crazymail.com', 'mypassword')

# Update fields and then save again
user.first_name = 'John'
user.last_name = 'Citizen'
user.save()
```

아래에서 우리는 첫번째 그룹과 그 그룹의 사용자를 만들  것입니다. 아직까지는 우리의 도서관 멤버로서 아무런 퍼미션도 부여하지는 않습니다. 그러나 나중에 필요하다면, 개인들에게 각각  하는 것보다는 그룹에 한번에 하는 것이 휠씬 쉬운 일입니다.

개발서버를 시작하고, 웹브라우저를 통해 admin site(http://127.0.0.1:8000/admin/)에 접속하십시오. 그리고 당신의 superuser 계정으로 로그인하십시오. admin site의 최상위 단계에서는 "Django  application"에 의해 소트된 당신의 모델들이 있습니다. Authentication and Authorisation 섹션에 있는 Users or Groups 링크를 클릭하여 현재의 등록된 기록들을 볼 수 있습니다.

![Admin site - add groups or users](./images/admin_authentication_add.png)

첫번째로 우리 도서관 멤버를 위한 새 그룹을 만듭시다.

1. Add버튼(Group 다음에 있는)을 클릭하여 새 그룹을 만듭니다; "Library Members"라는 이름을 넣으세요.

![Admin site - add group](./images/admin_authentication_add_group.png)

1. 우리는 그룹을 위한 어떠한 권한도 필요하지 않습니다. 그러므로 **SAVE** 를 누르세요. (you will be taken to a list of groups).

자 이제 사용자(user)를 만들어봅시다 :

1. admin 사이트의 홈페이지로 돌아가주세요.

2. Users 옆 **Add** 버튼을 클릭하여 user dialog를 열어줍니다.![Admin site - add user pt1](./images/admin_authentication_add_user_prt1.png)

3. 당신의 테스트 사용자(user)를 위해 적절한 사용자이름(**Username)** 과 비밀번호(**Password**/**Password confirmation)** 를 입력해주세요.

4. 사용자(user)를 만들기 위해 **SAVE** 를 눌러줍시다.

     관리자 사이트는 새로운 유저를 만들고, **username**을 바꿀 수 있고 유저모델의 선택 필드에 정보를 추가할 수 있는 *Change user화면으로 즉각 당신에게 보여줄 것입니다. 이 필드들은 이름, 성, 이메일 주소, 유저 상태 및 권한 (오직* **Active** 표시만 가능합니다)를 포함합니다. 더 밑으로 내려가면 당신의 그룹과 권한 유저와 관련된 중요한 날짜들(예를 들어 가입일과 마지막 로그인 날짜)을 기입할 수 있습니다. ![Admin site - add user pt2](./images/admin_authentication_add_user_prt2.png)

5. 그룹 섹션에서, Available groups목록에서 **Library Member** 를 선택하고두 박스 사이에 있는 **오른쪽 화살표**를 누르면 Chosen groups box로 이동이 될 거에요![Admin site - add user to group](./images/admin_authentication_user_add_group.png)

6. 여기서는 아무것도 필요치 않습니다, 그저 **SAVE** 를 선택하고, 유저 목록으로 가십시오.

다됬습니다! 이제 당신은 테스트를 위해 사용할 수 있는 "normal library member" 계정을 갖게 되었습니다(이들이 로그인할 수 있도록 페이지를 만들 때 말이죠).

**Note**: 다른 도서관 유저 만들기를 시도해보아야 합니다. 또한 사서들을 위한 그룹을 만들고 유저를 추가해보세요!

## [authentication views 세팅하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#authentication_views_세팅하기)

Django는 authentication pages에서 login, log out, and password 조정을 위한 거의 모든 것을 제공해 줍니다. "out of the box". 이것은 URL mapper, views와 forms들을 포함하지만 templates은  포함하지 않습니다 — 우리가 만들어야 하죠!

여기서, 우리는 기본 시스템들에 LocalLibrary를 통합하고, template들을 만들 수 있는 지를 볼 거에요.그리고 이것들을 프로젝트 메인 URL들에 넣을 것입니다.

**Note**: 어떤 코드도 사용하지 않으셔도 되지만, 아마 스스로 원할 가능성이 높아요. 더 쉽게 해주거든요. 만약 user model을 바꾸고자 한다면, form을 다루는 코드를 바꾸게 될 가능성이 아주 높아요. (앞으로  나올 주제에요!) 그렇다고 하더라도, stock view 함수들은 사용할 수 있어야 합니다.

**Note:** 이 경우에, catalog application에 URL과 템플릿을 포함해서  인authentication page들을 넣는게 합리적입니다. 그러나 많은 application들을 가지고 있다면, 공통적으로  로그인 해야하는 것을 분리하고 사이트 전체에서 로그인하는 것을 가능하게끔하는 게 좋을겁니다. 이게 우리가 여기서 볼려고하는  것이죠!

### [Project URLs](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#project_urls)

 (**locallibrary/locallibrary/urls.py**) 파일에 다음 코드를 추가해주세요:

```python
#Add Django site authentication urls (for login, logout, password management)
urlpatterns += [
    path('accounts/', include('django.contrib.auth.urls')),
]
```

이 URL( http://127.0.0.1:8000/accounts/)로 접속하세요. URL ('/'의 연결에 주의하세요!) 그러면 장고는 이 URL을 찾을 수 없기 때문에 에러메세지를 보여줄겁니다. 그리고 그 URL들을 어디에서 찾았는지 시도했던 모든 리스트들도 보여줍니다. 이것으로부터 당신은 예를들면, 어떤 URL들이  작동되는지 볼 수 있습니다.

**Note:** Using the above method adds the following URLs with names in square brackets, which can be used to reverse the URL  mappings. You don't have to implement anything else — the above URL  mapping automatically maps the below mentioned URLs.

```python
accounts/ login/ [name='login']
accounts/ logout/ [name='logout']
accounts/ password_change/ [name='password_change']
accounts/ password_change/done/ [name='password_change_done']
accounts/ password_reset/ [name='password_reset']
accounts/ password_reset/done/ [name='password_reset_done']
accounts/ reset/<uidb64>/<token>/ [name='password_reset_confirm']
accounts/ reset/done/ [name='password_reset_complete']
```

Now try to navigate to the login URL (http://127.0.0.1:8000/accounts/login/). This will fail again, but with an error that tells you that we're missing the required template (**registration/login.html**) on the template search path. You'll see the following lines listed in the yellow section up the top:

```python
Exception Type:    TemplateDoesNotExist
Exception Value:    registration/login.html
```

The next step is to create a registration directory on the search path and then add the **login.html** file.

### [Template directory](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#template_directory)

The URLs (and implicitly views) that we just added expect to find their associated templates in a directory **/registration/** somewhere in the templates search path.

For this site, we'll put our HTML pages in the **templates/registration/** directory. This directory should be in your project root directory, i.e the same directory as the **catalog** and **locallibrary** folders). Please create these folders now.

**Note:** Your folder structure should now look like the below:
 locallibrary (Django project folder)
   |_catalog
   |_locallibrary
   |_templates **(new)**
         |_registration

To make these directories visible to the template loader (i.e. to put this directory in the template search path) open the project settings (**/locallibrary/locallibrary/settings.py**), and update the `TEMPLATES` section's `'DIRS'` line as shown.

```python
TEMPLATES = [
    {
        ...
        'DIRS': ['./templates',],
        'APP_DIRS': True,
        ...
```

### [Login template](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#login_template)

**Important**: The authentication templates provided in  this article are a very basic/slightly modified version of the Django  demonstration login templates. You may need to customise them for your  own use!

Create a new HTML file called /**locallibrary/templates/registration/login.html**. give it the following contents:

```python
{% extends "base_generic.html" %}

{% block content %}

{% if form.errors %}
  <p>Your username and password didn't match. Please try again.</p>
{% endif %}

{% if next %}
  {% if user.is_authenticated %}
    <p>Your account doesn't have access to this page. To proceed,
    please login with an account that has access.</p>
  {% else %}
    <p>Please login to see this page.</p>
  {% endif %}
{% endif %}

<form method="post" action="{% url 'login' %}">
{% csrf_token %}

<div>
  <td>{{ form.username.label_tag }}</td>
  <td>{{ form.username }}</td>
</div>
<div>
  <td>{{ form.password.label_tag }}</td>
  <td>{{ form.password }}</td>
</div>

<div>
  <input type="submit" value="login" />
  <input type="hidden" name="next" value="{{ next }}" />
</div>
</form>

{# Assumes you setup the password_reset view in your URLconf #}
<p><a href="{% url 'password_reset' %}">Lost password?</a></p>

{% endblock %}
```

This template shares some similarities with the ones we've seen before — it extends our base template and overrides the `content` block. The rest of the code is fairly standard form handling code,  which we will discuss in a later tutorial. All you need to know for now  is that this will display a form in which you can enter your username  and password and that if you enter invalid values you will be prompted  to enter correct values when the page refreshes.

Navigate back to the login page (http://127.0.0.1:8000/accounts/login/) once you've saved your template, and you should see something like this:

![Library login page v1](./images/library_login.png)

If you try to log in that will succeed and you'll be redirected to another page (by default this will be http://127.0.0.1:8000/accounts/profile/). The problem here is that by default Django expects that after login you will want to be taken to a profile page, which may or may not be the  case. As you haven't defined this page yet, you'll get another error!

Open the project settings (**/locallibrary/locallibrary/settings.py**) and add the text below to the bottom. Now when you log in you should be redirected to the site homepage by default.

```python
# Redirect to home URL after login (Default redirects to /accounts/profile/)
LOGIN_REDIRECT_URL = '/'
```

### [Logout template](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#logout_template)

If you navigate to the logout URL (http://127.0.0.1:8000/accounts/logout/) then you'll see some odd behaviour — your user will be logged out sure enough, but you'll be taken to the **Admin** logout page. That's not what you want, if only because the login link  on that page takes you to the Admin login screen (and that is only  available to users who have the `is_staff` permission).

Create and open /**locallibrary/templates/registration/logged_out.html**. Copy in the text below:

```html
{% extends "base_generic.html" %}

{% block content %}
  <p>Logged out!</p>
  <a href="{% url 'login'%}">Click here to login again.</a>
{% endblock %}
```

This template is very  simple. It just displays a message informing you that you have been  logged out, and provides a link that you can press to go back to the  login screen. If you go to the logout URL again you should see this  page:

![Library logout page v1](./images/library_logout.png)

### [Password reset templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#password_reset_templates)

The default password reset system uses email to send the user a reset link. You need to create forms to get the user's email address, send the  email, allow them to enter a new password, and to note when the whole  process is complete.

The following templates can be used as a starting point.

#### Password reset form

This is the form used to get the user's email address (for sending the password reset email). Create **/locallibrary/templates/registration/password_reset_form.html**, and give it the following contents:

```html
{% extends "base_generic.html" %}

{% block content %}
  <form action="" method="post">
  {% csrf_token %}
  {% if form.email.errors %}
    {{ form.email.errors }}
  {% endif %}
      <p>{{ form.email }}</p>
    <input type="submit" class="btn btn-default btn-lg" value="Reset password">
  </form>
{% endblock %}
```

#### Password reset done

This form is displayed after your email address has been collected. Create **/locallibrary/templates/registration/password_reset_done.html**, and give it the following contents:

```html
{% extends "base_generic.html" %}

{% block content %}
  <p>We've emailed you instructions for setting your password. If they haven't arrived in a few minutes, check your spam folder.</p>
{% endblock %}
```

#### Password reset email

This template provides the text of the HTML email containing the reset link that we will send to users. Create **/locallibrary/templates/registration/password_reset_email.html**, and give it the following contents:

```html
Someone asked for password reset for email {{ email }}. Follow the link below:
{{ protocol}}://{{ domain }}{% url 'password_reset_confirm' uidb64=uid token=token %}
```

#### Password reset confirm

This page is where you enter your new password after clicking the link in the password reset email. Create **/locallibrary/templates/registration/password_reset_confirm.html**, and give it the following contents:

```html
{% extends "base_generic.html" %}

{% block content %}
    {% if validlink %}
        <p>Please enter (and confirm) your new password.</p>
        <form action="" method="post">
        {% csrf_token %}
            <table>
                <tr>
                    <td>{{ form.new_password1.errors }}
                        <label for="id_new_password1">New password:</label></td>
                    <td>{{ form.new_password1 }}</td>
                </tr>
                <tr>
                    <td>{{ form.new_password2.errors }}
                        <label for="id_new_password2">Confirm password:</label></td>
                    <td>{{ form.new_password2 }}</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Change my password" /></td>
                </tr>
            </table>
        </form>
    {% else %}
        <h1>Password reset failed</h1>
        <p>The password reset link was invalid, possibly because it has already been used. Please request a new password reset.</p>
    {% endif %}
{% endblock %}
```

#### Password reset complete

This is the last password-reset template, which is displayed to notify you when the password reset has succeeded. Create **/locallibrary/templates/registration/password_reset_complete.html**, and give it the following contents:

```html
{% extends "base_generic.html" %}

{% block content %}
  <h1>The password has been changed!</h1>
  <p><a href="{% url 'login' %}">log in again?</a></p>
{% endblock %}
```

### [Testing the new authentication pages](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#testing_the_new_authentication_pages)

Now that you've added the URL configuration and created all these templates, the authentication pages should now just work!

You can test the new authentication pages by attempting to log in and then logout your superuser account using these URLs:

- http://127.0.0.1:8000/accounts/login/
- http://127.0.0.1:8000/accounts/logout/

You'll be able to test the password reset functionality from the link in the login page. **Be aware that Django will only send reset emails to addresses (users) that are already stored in its database!**

**Note**: The password reset system requires that your website supports email, which is beyond the scope of this article, so this part **won't work yet**. To allow testing, put the following line at the end of your settings.py file. This logs any emails sent to the console (so you can copy the  password reset link from the console).

```
EMAIL_BACKEND = 'django.core.mail.backends.console.EmailBackend'
```

For more information, see [Sending email](https://docs.djangoproject.com/en/2.0/topics/email/) (Django docs).

## [authenticated users 테스트 하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#authenticated_users_테스트_하기)

This section looks at what we can do to selectively control content the user sees based on whether they are logged in or not.

### [Testing in templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#testing_in_templates)

당신은 템플릿에서 `{{ user }}` 라는 템플릿 변수로 현재 로그인한 사용자에 대한 정보를 얻을 수 있습니다 (이것은 우리의 스켈레톤을 만들때 프로젝트에서 세팅했을 때 템플릿 컨텍스트의 기본 값으로 추가된 것입니다).

보통 처음으로 `{{ user.is_authenticated }}` 라는 템플릿 변수를 통해서 당신은 사용자가 특정 내용을 볼 수 있는 지여부에 대해서 테스트하게 될 것입니다. 이를 시험하기 위해서, 우리는 사이트바에 로그인와 로그아웃 링크를 업데이트 할 것입니다.

Open the base template (**/locallibrary/catalog/templates/base_generic.html**) and copy the following text into the `sidebar` block, immediately before the `endblock` template tag.

```html
  <ul class="sidebar-nav">

    ...

   {% if user.is_authenticated %}
     <li>User: {{ user.get_username }}</li>
     <li><a href="{% url 'logout'%}?next={{request.path}}">Logout</a></li>
   {% else %}
     <li><a href="{% url 'login'%}?next={{request.path}}">Login</a></li>
   {% endif %} 
  </ul>
```

As you can see, we use `if`-`else`-`endif` template tags to conditionally display text based on whether `{{ user.is_authenticated }}` is true. If the user is authenticated then we know that we have a valid user, so we call **{{ user.get_username }}** to display their name.

We create the login and logout link URLs using the `url` template tag and the names of the respective URL configurations. Note also how we have appended `?next={{request.path}}` to the end of the URLs. What this does is add a URL parameter next containing the address (URL) of the *current* page, to the end of the linked URL. After the user has successfully logged in/out, the views will use this "`next`" value to redirect the user back to the page where they first clicked the login/logout link.

**Note**: Try it out! If you're on the home page and you click Login/Logout in the sidebar, then after the operation completes  you should end up back on the same page.

### [Testing in views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#testing_in_views)

If you're using function-based views, the easiest way to restrict access to your functions is to apply the `login_required` decorator to your view function, as shown below. If the user is logged  in then your view code will execute as normal. If the user is not logged in, this will redirect to the login URL defined in the project settings (`settings.LOGIN_URL`), passing the current absolute path as the `next` URL parameter. If the user succeeds in logging in then they will be returned back to this page, but this time authenticated.

```python
from django.contrib.auth.decorators import login_required

@login_required
def my_view(request):
    ...
```

**Note:** You can do the same sort of thing manually by testing on `request.user.is_authenticated`, but the decorator is much more convenient!

Similarly, the easiest way to restrict access to logged-in users in your class-based views is to derive from `LoginRequiredMixin`. You need to declare this mixin first in the superclass list, before the main view class.

```python
from django.contrib.auth.mixins import LoginRequiredMixin

class MyView(LoginRequiredMixin, View):
    ...
```

This has exactly the same redirect behaviour as the `login_required` decorator. You can also specify an alternative location to redirect the user to if they are not authenticated (`login_url`), and a URL parameter name instead of "`next`" to insert the current absolute path (`redirect_field_name`).

```python
class MyView(LoginRequiredMixin, View):
    login_url = '/login/'
    redirect_field_name = 'redirect_to'
```

For additional detail, check out the [Django docs here](https://docs.djangoproject.com/en/2.0/topics/auth/default/#limiting-access-to-logged-in-users).

## [Example — listing the current user's books](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#example_—_listing_the_current_users_books)

Now that we know how to restrict a page to a particular user, let's create a view of the books that the current user has borrowed.

Unfortunately, we don't yet have any way for users to borrow  books! So before we can create the book list we'll first extend the `BookInstance` model to support the concept of borrowing and use the Django Admin application to loan a number of books to our test user.

### [Models](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#models)

First, we're going to have to make it possible for users to have a `BookInstance` on loan (we already have a `status` and a `due_back` date, but we don't yet have any association between this model and a User. We'll create one using a `ForeignKey` (one-to-many) field. We also need an easy mechanism to test whether a loaned book is overdue.

Open **catalog/models.py**, and import the `User` model from `django.contrib.auth.models` (add this just below the previous import line at the top of the file, so `User` is available to subsequent code that makes use of it):

```python
from django.contrib.auth.models import User
```

Next, add the `borrower` field to the `BookInstance` model:

```python
borrower = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, blank=True)
```

While we're here, let's add a property that we can call from our templates to tell if a particular book instance is  overdue. While we could calculate this in the template itself, using a [property](https://docs.python.org/3/library/functions.html#property) as shown below will be much more efficient.

Add this somewhere near the top of the file:

```python
from datetime import date
```

Now add the following property definition to the `BookInstance` class:

```python
@property
def is_overdue(self):
    if self.due_back and date.today() > self.due_back:
        return True
    return False
```

**Note**: We first verify whether `due_back` is empty before making a comparison. An empty `due_back` field would cause Django to throw an error instead of showing the page: empty values are not comparable. This is not something we would want  our users to experience!

Now that we've updated our models, we'll need to make fresh migrations on the project and then apply those migrations:

```cmd
python3 manage.py makemigrations
python3 manage.py migrate
```

### [Admin](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#admin)

Now open **catalog/admin.py**, and add the `borrower` field to the `BookInstanceAdmin` class in both the `list_display` and the `fieldsets` as shown below. This will make the field visible in the Admin section so that we can assign a `User` to a `BookInstance` when needed.

```python
@admin.register(BookInstance)
class BookInstanceAdmin(admin.ModelAdmin):
    list_display = ('book', 'status', 'borrower', 'due_back', 'id')
    list_filter = ('status', 'due_back')

    fieldsets = (
        (None, {
            'fields': ('book','imprint', 'id')
        }),
        ('Availability', {
            'fields': ('status', 'due_back','borrower')
        }),
    )
```

### [Loan a few books](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#loan_a_few_books)

Now that its possible to loan books to a specific user, go and loan out a number of `BookInstance` records. Set their `borrowed` field to your test user, make the `status` "On loan" and set due dates both in the future and the past.

**Note**: We won't spell the process out, as you already know how to use the Admin site!

### [On loan view](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#on_loan_view)

Now we'll add a view for getting the list of all books that have been  loaned to the current user. We'll use the same generic class-based list  view we're familiar with, but this time we'll also import and derive  from `LoginRequiredMixin`, so that only a logged in user can call this view. We will also choose to declare a `template_name`, rather than using the default, because we may end up having a few  different lists of BookInstance records, with different views and  templates.

Add the following to catalog/views.py:

```python
from django.contrib.auth.mixins import LoginRequiredMixin

class LoanedBooksByUserListView(LoginRequiredMixin,generic.ListView):
    """Generic class-based view listing books on loan to current user."""
    model = BookInstance
    template_name ='catalog/bookinstance_list_borrowed_user.html'
    paginate_by = 10

    def get_queryset(self):
        return BookInstance.objects.filter(borrower=self.request.user).filter(status__exact='o').order_by('due_back')
```

In order to restrict our query to just the `BookInstance` objects for the current user, we re-implement `get_queryset()` as shown above. Note that "o" is the stored code for "on loan" and we order by the `due_back` date so that the oldest items are displayed first.

### [URL conf for on loan books](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#url_conf_for_on_loan_books)

Now open **/catalog/urls.py** and add a`path()` pointing to the above view (you can just copy the text below to the end of the file).

```python
urlpatterns += [
    path('mybooks/', views.LoanedBooksByUserListView.as_view(), name='my-borrowed'),
]
```

### [Template for on-loan books](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#template_for_on-loan_books)

Now, all we need to do for this page is add a template. First, create the template file **/catalog/templates/catalog/bookinstance_list_borrowed_user.html** and give it the following contents:

```html
{% extends "base_generic.html" %}

{% block content %}
    <h1>Borrowed books</h1>

    {% if bookinstance_list %}
    <ul>

      {% for bookinst in bookinstance_list %}
      <li class="{% if bookinst.is_overdue %}text-danger{% endif %}">
        <a href="{% url 'book-detail' bookinst.book.pk %}">{{bookinst.book.title}}</a> ({{ bookinst.due_back }})
      </li>
      {% endfor %}
    </ul>

    {% else %}
      <p>There are no books borrowed.</p>
    {% endif %}
{% endblock %}
```

This template is very similar to those we've created previously for the `Book` and `Author` objects. The only thing "new" here is that we check the method we added in the model `(bookinst.is_overdue`) and use it to change the colour of overdue items.

When the development server is running, you should now be able to view the list for a logged in user in your browser at http://127.0.0.1:8000/catalog/mybooks/. Try this out with your user logged in and logged out (in the second case, you should be redirected to the login page).

### [Add the list to the sidebar](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#add_the_list_to_the_sidebar)

The very last step is to add a link for this new page into the sidebar.  We'll put this in the same section where we display other information  for the logged in user.

Open the base template (**/locallibrary/catalog/templates/base_generic.html**) and add the line in bold to the sidebar as shown.

```html
 <ul class="sidebar-nav">
   {% if user.is_authenticated %}
   <li>User: {{ user.get_username }}</li>
   <li><a href="{% url 'my-borrowed' %}">My Borrowed</a></li>
   <li><a href="{% url 'logout'%}?next={{request.path}}">Logout</a></li>
   {% else %}
   <li><a href="{% url 'login'%}?next={{request.path}}">Login</a></li>
   {% endif %}
 </ul>
```

### [What does it look like?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#what_does_it_look_like)

When any user is logged in, they'll see the *My Borrowed* link in the sidebar, and the list of books displayed as below (the  first book has no due date, which is a bug we hope to fix in a later  tutorial!).

![Library - borrowed books by user](./images/library_borrowed_by_user.png)

## [허가](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#허가)

Permissions 는 모델과 연관되어 허가를 가진 유저에 의한 model instance 작업들을 정의하게 됩니다. 기본적으로 Django 자동적으로 *add*, *change*, 그리고 *delete* permissions 을 모든 모델에 제공하는데, 유저가 관리자 사이트를 통해서 (권한)허가를 가지고 연고나도니 작업들을 하게  합니다. 당신은 권한을 모델이나 특정 유저에게 부여하도록 정의할 수 있습니다. You can also change the  permissions associated with different instances of the same model.

Testing on permissions in views and templates is then very similar  for testing on the authentication status (and in fact, testing for a  permission also tests for authentication).

### [Models](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#models_2)

Defining permissions is done on the model "`class Meta`" section, using the `permissions` field. You can specify as many permissions as you need in a tuple, each permission itself being defined in a nested tuple containing the  permission name and permission display value. For example, we might  define a permission to allow a user to mark that a book has been  returned as shown:

```python
class BookInstance(models.Model):
    ...
    class Meta:
        ...
        permissions = (("can_mark_returned", "Set book as returned"),)   
```

We could then assign the permission to a "Librarian" group in the Admin site.

Open the **catalog/models.py**, and add the permission as shown above. You will need to re-run your migrations (call `python3 manage.py makemigrations` and `python3 manage.py migrate`) to update the database appropriately.

### [Templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#templates)

The current user's permissions are stored in a template variable called `{{ perms }}`. You can check whether the current user has a particular permission  using the specific variable name within the associated Django "app" —  e.g. `{{ perms.catalog.can_mark_returned }}` will be `True` if the user has this permission, and `False` otherwise. We typically test for the permission using the template `{% if %}` tag as shown:

```html
{% if perms.catalog.can_mark_returned %}
    <!-- We can mark a BookInstance as returned. -->
    <!-- Perhaps add code to link to a "book return" view here. -->
{% endif %}
```

### [Views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#views)

Permissions can be tested in function view using the `permission_required` decorator or in a class-based view using the `PermissionRequiredMixin`. The pattern and behaviour are the same as for login authentication,  though of course, you might reasonably have to add multiple permissions.

Function view decorator:

```python
from django.contrib.auth.decorators import permission_required

@permission_required('catalog.can_mark_returned')
@permission_required('catalog.can_edit')
def my_view(request):
    ...
```

A permission-required mixin for class-based views.

```python
from django.contrib.auth.mixins import PermissionRequiredMixin

class MyView(PermissionRequiredMixin, View):
    permission_required = 'catalog.can_mark_returned'
    # Or multiple permissions
    permission_required = ('catalog.can_mark_returned', 'catalog.can_edit')
    # Note that 'catalog.can_edit' is just an example
    # the catalog application doesn't have such permission!
```

### [Example](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#example)

We won't update the *LocalLibrary* here; perhaps in the next tutorial!

## [Challenge yourself](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#challenge_yourself)

Earlier in this article, we showed you how to create a page for the current  user listing the books that they have borrowed. The challenge now is to  create a similar page that is only visible for librarians, that displays *all* books that have been borrowed, and which includes the name of each borrower.

You should be able to follow the same pattern as for the other view.  The main difference is that you'll need to restrict the view to only  librarians. You could do this based on whether the user is a staff  member (function decorator: `staff_member_required`, template variable: `user.is_staff`) but we recommend that you instead use the `can_mark_returned` permission and `PermissionRequiredMixin`, as described in the previous section.

**Important**: Remember not to use your superuser for  permissions based testing (permission checks always return true for  superusers, even if a permission has not yet been defined!). Instead,  create a librarian user, and add the required capability.

When you are finished, your page should look something like the screenshot below.

![All borrowed books, restricted to librarian](./images/library_borrowed_all.png)



## [Summary](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Authentication#summary)

Excellent work — you've now created a website that library members can log in  into and view their own content and that librarians (with the correct  permission) can use to view all loaned books and their borrowers. At the moment we're still just viewing content, but the same principles and  techniques are used when you want to start modifying and adding data.

In our next article, we'll look at how you can use Django forms to  collect user input, and then start modifying some of our stored data.



# Django 튜토리얼 파트 9: 폼(form)으로 작업하기

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms

이 튜토리얼에서 우리는 Django에서 HTML Form 작업 방법을 보여주고 특히  model Instance를 생성,수정,제거 하는 Form을 작성하는 가장 쉬운 방법을 보여줄 것이다. 이 예제의 일부분으로  우리는 도서관직원이 (admin 앱을 이용하기 보다) 우리가 만든 form을 이용하여 책 대여기간을 연장하거나 작가 정보를  생성,수정,제거할 수 있도록 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 웹사이트를 확장할 것이다.

| 사전학습: | 아래 파트를 포함하여 앞선 모든 튜토리얼 파트의 학습을 완료할것 [Django 튜토리얼 파트 8: 사용자 인증과 이용권한](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Authentication). |
| --------- | ------------------------------------------------------------ |
| 학습목표: | 사용자로 부터 정보를 얻고 database를 수정하는 form을 작성하는 방법을 이해하기. 일반 클래스 기반 form 편집용 view가 단독 model로 동작하는 form을 작성할 때 얼마나 많이 단순화할 수 있는지 이해하기. |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#개요)

[HTML 폼(Form) (en-US)](https://developer.mozilla.org/en-US/docs/Learn/Forms) 은 웹 페이지상에서 한개 이상의 필드나 위젯들의 묶음을 말하며, 사용자로부터 정보를 수집하여 서버에 제출하는데 사용된다. 다양한  종류의 데이타 입력을 지원하는 위젯들( 텍스트 박스, 체크 박스, 라디오 버튼, 날짜 선택기 등등)이 많이 존재하기 때문에,  폼은 사용자 입력을 수집하는데 유연한 장치라고 할 수 있다. 폼은 또한, 교차 사이트 요청 위조 방지(CSRF  protection, cross-site request forgery protection)와 함께 `POST`요청으로 데이타를 보낼수 있도록 지원하므로, 데이타를 서버와 공유하는데 있어서 비교적 안전한 방법이다.

지금까지 이 튜토리얼에서 우리가 직접 폼을 생성한 적은 없지만, Django 관리 사이트에서 이미 경험해 보았다. 예를 들면, 아래 스크린 샷에서 [Book](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Models) 모델중 하나를 편집하는 폼을 보여주고 있는데, 몇개의 선택 목록과 텍스트 에디터를 볼 수 있다.

![Admin Site - Book Add](./images/admin_book_add.png)

폼을 개발하는 것은 복잡한 작업이 될수도 있다! 개발자는 일단, 폼을 위한 HTML을 작성해야 하며, 서버로 입력된  (아마도 브라우저로도 입력된) 데이타의 유효성을 검증하고 적절하게 수정하도록 하고, 유효하지 않은 입력에 대해서 사용자가 알 수  있도록 폼을 에러 메시지와 함께 다시 표시해야하며,성공적으로 제출된 데이타를 적절히 처리하고, 마지막으로 성공했을 경우 사용자가  알수 있게 응답하도록 개발 해야 한다. *Django 폼은 다음과 같은 기능의 프레임워크를 제공하여 이 모든 단계중 많은  부분을 덜어내 준다. 이 프레임워크는 폼과 그에 연관된 필드를 프로그램적으로 정의하여 객체를 만들고, 폼 HTML 코드를 작성하는 작업과 데이타 유효성 검증과 사용자 상호작용에 이 객체들을 사용한다.*

이번 튜토리얼에서는, 폼을 생성하고 폼으로 작업하는 몇가지 방법을 보여줄 것이다. 특히, 모델을 처리하는 폼을 작성하는데  필요한 작업량을, generic 편집 폼 view를 이용하여 어떻게 획기적으로 줄일 수 있는지 보여줄 것이다. 그  과정에서, 도서관 사서들이 도서관 책 상태를 갱신할 수 있는 폼을 추가하고 책과 저자를 생성, 편집, 삭제할수 있는 페이지를  생성할 것이다. (즉, 위와 같이 책을 편집하는 폼의 기본적인 버전을 다시 개발하는 것이다).

## [HTML 폼(Form) 이란?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#html_폼form_이란)

첫번째로 [HTML 폼(Form](https://developer.mozilla.org/en-US/docs/Learn/Forms))에 대한 간단한 개요이다. 어떤 "team"의 이름을 입력하는 단일 텍스트 필드와 관련 라벨을 가진 간단한 HTML 폼을 생각해보자:

![Simple name field example in HTML form](./images/form_example_name_field.png)

폼은 HTML에서 적어도 한 개 이상의 `type="submit"`인 `input` 요소를 포함하는 `<form>...</form>` 태그 사이의 요소들의 집합으로 정의된다.

```html
<form action="/team_name_url/" method="post">
    <label for="team_name">Enter name: </label>
    <input id="team_name" type="text" name="name_field" value="Default name for team.">
    <input type="submit" value="OK">
</form>
```

위 코드에서는 팀 이름을 입력하기 위한 텍스트 필드를 단지 한개만 가지는데, 폼이 가질수 있는 입력 요소와 관련 라벨의 갯수에는 제한이 없다. 필드의  `type` 속성은 어떤 종류의 위젯이 표시될지 정의한다. 필드의 `name`과 `id` 가 JavaScript/CSS/HTML에 있는 필드를 확인하는데 사용되고 `value`는 필드가 처음 표시될 때의 초기값을 정의한다. 관련 팀 라벨은 `label`태그( 위 코드에서 "Enter name"을 확인)를 이용해 명시된다. 여기서 `for`필드는 관련된 `input`의 `id`값을 포함하고 있다. 

`submit` 타입의 input 태그는 (기본적으로) 사용자가 누를수 있는 버튼으로 표시되는데, 버튼의 동작에 의해 폼의 다른 모든 input 요소의 데이터가 서버로 업로드된다 (위의 경우는 `team_name`만 업로드된다). 폼 속성으로는 데이터를 보내기 위해 사용되는 HTTP `method`와 서버상에서 데이타의 목적지를 ( `action`으로) 정의한다:

- `action`: 폼이 제출(submit)될 때 처리가 필요한 데이타를 전달받는 곳의 자원/URL 주소. 설정이 안되면 (혹은 빈 문자열로 설정되면), 폼은 현재 페이지 URL로 다시 제출된다.

- ```
  method
  ```

  : 데이터를 보내는데 사용되는 HTTP 메소드: 

  post

   이거나 

  get 이다.

  - `POST` 메소드는 사이트간 요청 위조 공격에 좀 더 저항성이 좋게 만들 수 있기 때문에, 관련 데이터에 의해 서버 데이터베이스가 변경될 경우에는 항상 사용 되어야 한다. 
  - `GET` 메소드는 사용자 데이터를 변경하지 않는 폼(예를 들면 , 탐색 폼)에서만 사용되어야 한다. URL을 북마크하길 원하거나 공유하기를 원하는 경우에 추천한다. 

서버의 역할은 첫번째로 - 필드를 비워두거나 초기값으로 채워두도록 - 초기 폼 상태를 표시하는 것이다. 사용자가 제출 버튼을 누른후에, 서버는 웹 브라우저로부터 폼의 데이타를 념겨 받고, 데이타의 유효성 검증을 해야한다. 폼이 유효하지 않은 데이타를  담고 있다면, 서버는 폼을 다시 표기해야 하는데, 이번에는 사용자가 입력한 유효한 데이타는 그대로 표시하며, 유효하지 않은 필드만 경고 메시지와 함께 표기해야 한다. 일단 모든 필드의 데이타가 유효한 폼 데이타의 제출요청을 서버가 받게 되면, 서버는 적절한  동작(예를 들면, 데이타를 저장하거나, 검색결과를 반화하거나, 파일을 업로딩하는 등등의 작업)을 수행하고 사용자에게 알려주게된다.

당신이 상상할 수 있듯이, HTML을 작성하고, 입력된 데이타의 유효성을 검증하고, 필요시에 입력된 데이타를 검증 결과와  함게 다시 표시하며, 유효한 데이타에 대해 요구되는 동작을 수행하는 것은 "올바르게 하기"위해서는 꽤 많은 노력이 필요한  작업이다. Django는 일부 과중한 작업과 반복 코드를 줄여줌으로서, 이 작업을 훨씬 쉽게 만든다!

## [Django 폼 처리 과정](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#django_폼_처리_과정)

Django의 폼 처리 과정은 (모델에 대한 정보를 보여주는데 있어서) 우리가 앞선 튜토리얼에서 배웠던 것과 같은 기술을 사용한다. : 뷰는  요청을 받고, 모델로 부터 데이타를 읽는것을 포함한 요구되는 동작을 수행한다. 그런 다음, (보여줄 데이타를 포함한  context를 전달받은 템플릿으로 부터) HTML page를 생성하고 반환한다. 서버 또한 사용자가 입력한 데이타를 처리가능  해야 하며, 에러가 있으면 그 페이지를 다시 보여줄 필요가 있기 때문에 상황을 더욱 복잡하게 만든다. 

아래에 Django가 어떻게 요청읕 처리하는지 보여주는 플로우 차트가 있다. 폼을 포함하는 페이지에 대한 요청 (초록색으로 표시함) 으로 시작하고 있다. 

![Updated form handling process doc.](./images/Form Handling - Standard.png)

위의 다이어그램에 기반하여, Django 폼이 주요하게 다루는 것은 다음과 같다. :

1. 사용자가 처음으로 폼을 요청할 때 기본 폼을 보여준다.  
   - 폼은 비어있는 필드가 있을 수 있다 (예를 들면, 새로운 책을 등록할 경우) 아니면 초기값으로 채워진 필드가 있을 수도 있다. ( 예를 들면, 기존의 책을 수정하거나, 흔히 사용하는 초기값이 있을경우)
   - 이 시점의 폼은 (초기값이 있긴해도) 유저가 입력한 값에 연관되지 않았기에 unbound 상태라고 불린다.
2. 제출 요청으로 부터 데이타를 수집하고 그것을 폼에 결합한다.  
   - 데이타를 폼에 결합(binding) 한다는 것은 사용자 입력 데이타와 유효성을 위반한 경우의 에러메시지가 폼을 재표시할 필요가 있을 때 준비되었다는 의미이다.
3. 데이타를 다듬어서 유효성을 검증한다.  
   - 데이타를 다듬는다는 것은 사용자 입력을 정화(sanitisation) 하고 (예를 들면, 잠재적으로 악의적인 콘덴츠를 서버로 보낼수도 있는 유효하지 않은 문자를 제거하는 것) python에서 사용하는 타입의 데이타로 변환하는 것이다.
   - 유효성검증은 입력된 값이 해당 필드에 적절한 값인지 검사한다. (예를 들면, 데이타가 허용된 범위에 있는 값인지, 너무 짧거나 길지 않은지 등등) 
4. 입력된 어떤 데이타가 유효하지 않다면, 폼을 다시 표시하는데 이번에는 초기값이 아니라 유저가 입력한 데이타와 문제가 있는 필드의 에러 메시지와 함께 표시한다.
5. 입력된 모든 데이타가 유효하다면, 요청된 동작을 수행한다. (예를 들면, 데이타를 저장하거나, 이메일을 보내거나, 검색결과를 반환하거나, 파일을 업로딩하는 작업 등등)
6. 일단 모든 작업이 완료되었다면, 사용자를 새로운 페이지로 보낸다.

Django는 위에 설명된 작업을 도와줄 수많은 도구와 접근법을 제공한다. 가장 기초적인 것은 `Form` 클래스 인데 form HTML의 생성과 데이터 정화와 유효성검증을 간단하게 만든다. 다음 단계에서는, 도서관 사서가 책의 대여갱신을 할수 있도록 해주는 페이지의 실제적인 예제를 이용해 폼이 어떻게 동작하는지 살펴보도록 한다.

**참고사항:** `Form` 이 어떻게 사용되는지 이해해두면 Django의 "고급 레벨" 폼 프레임워크 클래스를 논의하는데 도움이 된다.

## [책 대여갱신 form과 함수 view](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#책_대여갱신_form과_함수_view)

다음으로 도서관직원이 대여기간을 갱신할수 있는 페이지를 추가할 것이다. 이 작업을 위해 사용자가 날짜 정보를 입력할 수 있는 form을  생성할 것이다. 그 필드는 현재날짜로 부터 3주의 기간 (일반적인 대여기간)으로 초기화될 것이다. 그리고 도서관직원이 과거날짜를 입력하거나 너무 긴 대여기간을 입력하지 않도록 유효성 체크기능을 추가할 것이다. 유효 날짜가 입력되면, 현재 record의 `BookInstance.due_back` 필드에 써넣을 것이다.

아래 예제는 함수기반 view와 `Form` 클래스를 이용할 것이다. 이어지는 내용에서 form 동작 방법과 현재진행중인 LocalLibray 프로젝트에서 변경할 내용을 설명한다.

### [Form 작성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#form_작성하기)

`Form` 클래스는 Django form 관리 시스템의 핵심이다. `Form` 클래스는 form내 field들, field 배치, 디스플레이 widget, 라벨, 초기값, 유효한 값과 (유효성 체크이후에) 비유효 field에 관련된 에러메시지를 결정한다. `Form` 클래스는 또한 미리 정의된 포맷(테이블, 리스트 등등) 의 템플릿으로 그자신을 렌더링하는 method나 (세부 조정된 수동 렌더링을 가능케하는) 어떤 요소의 값이라도 얻는 method를 제공한다.

#### Form 선언하기

`Form` 을 선언하는 문법은 `Model`을 선언하는 것과 많이 닮았으며, 같은  필드타입을 사용한다. ( 또한 일부 매개변수도 유사하다) . 두가지 경우 모두 각 필드가 데이타에 맞는 (유효성 규칙에 맞춘)  타입인지 확인할 필요가 있고, 각 필드가 보여주고 문서화할 description을 가진다는 것에서 Form과 Model이 유사한  문법으로 구성된다는 점을 납득할 수 있다. 

Form 데이타는 어플리케이션 디렉토리 안의 forms.py 파일에 저장되어야 한다. **locallibrary/catalog/forms.py** 파일을 생성하고 열어보자. `Form`을 생성하기 위해, `Form`클래스에서 파생된, `forms`라이브러리를 import 하고 폼 필드를 생성한다. 아래는 도서관 책 갱신 폼에 대한 아주 기본적인 폼 클래스이며 이를 생성한 파일에 추가하자.

```python
from django import forms

class RenewBookForm(forms.Form):
    renewal_date = forms.DateField(help_text="Enter a date between now and 4 weeks (default 3).")
```

#### Form 필드

우리가 구현할 구체적인 내용은 다음과 같다. 대여갱신 날짜를 입력할 한 개의 `DateField` 를 가지는데, 이 필드는 "Renewal date:"라는 라벨로 초기값 없이 빈 칸으로 HTML에 표시되게 된다. 그리고 다음과 같은 도움문구가 추가 된다: "*Enter a date between now and 4 weeks (default 3 weeks).*" 따로 추가지정할 선택사항 없이, 이 필드는 Django의 [input_formats](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#django.forms.DateField.input_formats): YYYY-MM-DD (2016-11-06), MM/DD/YYYY (02/26/2016), MM/DD/YY (10/25/16) 을 이용하여 날짜를 입력받는다. 그리고 Django의 기본 [widget](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#widget): [DateInput](https://docs.djangoproject.com/en/2.0/ref/forms/widgets/#django.forms.DateInput) 를 이용하여 표시될 것이다.

다음과 같이, 대응되는 모델 필드와 유사성 때문에, 여러분이 의미를 대체로 알만한 수많은 종류의 폼필드가 있다 : [`BooleanField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#booleanfield), [`CharField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#charfield), [`ChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#choicefield), [`TypedChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#typedchoicefield), [`DateField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#datefield), [`DateTimeField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#datetimefield), [`DecimalField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#decimalfield), [`DurationField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#durationfield), [`EmailField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#emailfield), [`FileField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#filefield), [`FilePathField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#filepathfield), [`FloatField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#floatfield), [`ImageField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#imagefield), [`IntegerField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#integerfield), [`GenericIPAddressField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#genericipaddressfield), [`MultipleChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#multiplechoicefield), [`TypedMultipleChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#typedmultiplechoicefield), [`NullBooleanField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#nullbooleanfield), [`RegexField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#regexfield), [`SlugField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#slugfield), [`TimeField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#timefield), [`URLField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#urlfield), [`UUIDField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#uuidfield), [`ComboField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#combofield), [`MultiValueField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#multivaluefield), [`SplitDateTimeField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#splitdatetimefield), [`ModelMultipleChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#modelmultiplechoicefield), [`ModelChoiceField`](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#modelchoicefield) .

대부분의 필드에 공통적인 인자들은 아래와 같다. ( 이들은 적절한 기본값을 가지고 있다 ):

- [required](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#required): `True` 로 설정되면, 필드를 빈칸으로 두거나 `None` 값을 줄 수 없게된다. 보통필드는 required는 True로 기본 설정되므로, 폼에서 빈 칸을 허용하기 위해서는`required=False` 로 설정해야 한다. 
- [label](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#label): HTML에서 필드를 렌더링할때 사용하는 레이블이다. [label](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#label) 이 지정되지 않으면, Django는 필드 이름에서 첫번째 문자를 대문자로, 밑줄을 공백으로 변형한 레이블을 새로 생성할 것이다. (예를 들면, renewal_date --> *Renewal date*).
- [label_suffix](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#label-suffix): 기본적으로, 콜론(:)이 레이블 다음에 표시된다. (예를 들면, Renewal date**:**). 이 인자는 다른 문자(들)를 포함한 접미사를 지정할 수 있도록 해준다.
- [initial](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#initial): 폼이 나타날 때 해당 필드의 초기 값.
- [widget](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#widget): 사용할 디스플레이 위젯.
- [help_text](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#help-text) (위의 예에서 봤듯이): 필드 사용법을 보여주는 추가적인 문구.
- [error_messages](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#error-messages): 해당 필드의 에러 메시지 목록. 필요하면 문구를 수정할 수 있다.
- [validators](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#validators): 해당 필드가 유효한 값을 가질 때 호출되는 함수의 목록.
- [localize](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#localize): 폼 데이타 입력의 현지화(localisation)를 허용함 (자세한 정보는 해당 링크 참조).
- [disabled](https://docs.djangoproject.com/en/2.0/ref/forms/fields/#disabled): 이 옵션이 `True` 일때 해당 필드를 볼 수는 있지만 편집이 안됨. 기본 값은 `False`.

#### 유효성 체크

Django는 데이타의 유효성을 체크할 수 있는 수많은 지점을 제공한다. 어떤 필드의 유효성을 체크하는 가장 쉬운 방법은 해당 필드의 `clean_**<fieldname>**()` 메소드를 덮어쓰는 것이다. 예를 들면, 입력된 `renewal_date` 값이 현재로 부터 4 주이후 사이에 있는지는, `clean_**renewal_date**()` 를 아래와 같이 구현하여 유효성 체크를 수행할 수 있다.

forms.py 파일을 업데이트 하면 아래와 같은 모습이 된다:

```python
import datetime

from django import forms
from django.core.exceptions import ValidationError
from django.utils.translation import ugettext_lazy as _

class RenewBookForm(forms.Form):
    renewal_date = forms.DateField(help_text="Enter a date between now and 4 weeks (default 3).")

    def clean_renewal_date(self):
        data = self.cleaned_data['renewal_date']

        # Check if a date is not in the past.
        if data < datetime.date.today():
            raise ValidationError(_('Invalid date - renewal in past'))

        # Check if a date is in the allowed range (+4 weeks from today).
        if data > datetime.date.today() + datetime.timedelta(weeks=4):
            raise ValidationError(_('Invalid date - renewal more than 4 weeks ahead'))

        # Remember to always return the cleaned data.
        return data
```

주목해야할 지점이 두개 있다. 첫 번째 지점은 `self.cleaned_data['renewal_date']` 를 이용하여 데이타를 획득하고 이 데이타의 수정여부에 상관없이 함수가 끝나면 이 데이타를 반환한 다는 것이다. 이 단계는 기본  유효성 검사도구를 이용해 입력값을 "다듬고(cleaned)" 잠재적으로 안전하지 않을 수 있는 입력 값을 정화하며 , 해당  입력값에 맞는 표준 형식으로 변환해준다. ( 이 경우에는 Python `datetime.datetime` 객체 형식이다.).

두 번째 지점은 입력값이 지정한 범위를 벗어날 경우 `ValidationError` 에러를 발생시키고, 유효하지 않은 입력값일 때 폼에 보여주고자 하는 에러 문구를 지정하는 부분이다. 위의 예에서는, [Django의 번역 함수들](https://docs.djangoproject.com/en/2.0/topics/i18n/translation/) 중하나인 `ugettext_lazy()` (`_()` 로 import 됨)로 이 문구를 감싸고 있는데, 당신의 사이트를 나중에 번역하고자 한다면 좋은 예제가 된다.

**참고사항:** [폼과 필드 유효성 체크](https://docs.djangoproject.com/en/2.0/ref/forms/validation/) (장고 문서임) 에 폼의 유효성 체크에 대한 수많은 다른메소드및 예제가 있다. 예를 들면, 서로 의존관계에 있는 여러개의 필드가 있을 경우, [Form.clean()](https://docs.djangoproject.com/en/2.0/ref/forms/api/#django.forms.Form.clean) 함수를 덮어써서,  `ValidationError` 를 다시 발생시킬수도 있다.

여기까지가 본 예제에서 필요한 폼에 대한 모든 내용이다!

### [URL Configuration 작성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#url_configuration_작성하기)

뷰를 생성하기 전에, 책 대여갱신 페이지를 위해 URL 설정을 추가 하자. 아래 설정코드를 **locallibrary/catalog/urls.py** 아랫 부분에 복사하라.

```python
urlpatterns += [
    path('book/<uuid:pk>/renew/', views.renew_book_librarian, name='renew-book-librarian'),
]
```

위 URL 설정코드는 **/catalog/book/\*<bookinstance id>\*/renew/** 형식의 URL을 **views.py** 에 있는 `renew_book_librarian()` 라는 이름의 함수를 호출하고 `BookInstance` id를 `pk`라고 이름지은 매개변수로 전송한다. 위 패턴은 `pk`가 정확히 `uuid`의 형식일때만 일치한다.

**주목할점**: 추출된 URL 데이타 "`pk`" 는 당신 마음대로 이름을 정할 수 있다. 왜냐하면 view 함수에 대해서는 어떤 조작이라도 가능하기 때문이다. ( 특정 이름을 기대하는 매개변수를 가진  Generic detail view 클래스를 사용하지 않고 있다.) 하지만 `pk`는 "primary key"의 약자으로 합리적인 관례상 이름이다 !

### [View 작성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#view_작성하기)

위의 [Django 폼 처리 과정](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#django_form_handling_process) 에서 설명된대로, 위의 폼 뷰는 첫번째로 호출될 때는 기본 폼을 표시해야 한다. 그리고 나서 데이터가 유효하지 않은 경우 에러  메시지를 재 표시하고, 데이터가 유효한 경우에는 데이타를 처리하고 새로운 페이지를 표시해야 한다. 이런 서로 다른 동작을 수행하기 위해, 해당 뷰가 기본 폼을 표시하도록 현재 첫번째로 호출되고 있는지, 데이터 유효성을 체크하기 위해 연속되어  이어지는 호출인지 알 수 있어야 한다.  

서버에 정보를 제출하는 `POST`리퀘스트를 사용하는 폼에 대해서, 가장 흔한 패턴은 뷰에서 `POST` 요청 타입 인지 판단 (`if request.method == 'POST':`) 하여 유효한 요청 여부를 확인하고 `GET` ( `else` 조건으로 ) 요청 타입인 경우 초기 폼 생성을 요청한다. `GET`요청으로 데이터를 제출하려고 한다면 첫 번째 뷰 호출인지 두 번째 이상의 뷰 호출인지 판단하는 전형적인 접근 방법은 폼 데이터를 읽어보는 (즉 폼에서 숨겨진 값을 읽는)것이다.

책 대여갱신 과정은 데이터베이스에 결과를 보내기 때문에, 관례상 `POST`요청 방법을 사용한다. 아래 코드는 이런 종류의 function 뷰에 대해 가장 기본적인 형식을 보여준다.

```python
import datetime

from django.shortcuts import get_object_or_404
from django.http import HttpResponseRedirect
from django.urls import reverse

from catalog.forms import RenewBookForm

def renew_book_librarian(request, pk):
    book_instance = get_object_or_404(BookInstance, pk=pk)

    # POST 요청이면 폼 데이터를 처리한다
    if request.method == 'POST':

        # 폼 인스턴스를 생성하고 요청에 의한 데이타로 채운다 (binding):
        book_renewal_form = RenewBookForm(request.POST)

        # 폼이 유효한지 체크한다:
        if book_renewal_form.is_valid():
            # form.cleaned_data 데이타를 요청받은대로 처리한다(여기선 그냥 모델 due_back 필드에 써넣는다)
            book_instance.due_back = book_renewal_form.cleaned_data['renewal_date']
            book_instance.save()

            # 새로운 URL로 보낸다:
            return HttpResponseRedirect(reverse('all-borrowed') )

    # GET 요청 (혹은 다른 메소드)이면 기본 폼을 생성한다.
    else:
        proposed_renewal_date = datetime.date.today() + datetime.timedelta(weeks=3)
        book_renewal_form = RenewBookForm(initial={'renewal_date': proposed_renewal_date})

    context = {
        'form': book_renewal_form,
        'book_instance': book_instance,
    }

    return render(request, 'catalog/book_renew_librarian.html', context)
```

첫부분에서는 미리 작성된 폼 (`RenewBookForm`)을 import 하고 뷰 함수의 내부에서 쓰일 유용한 객체나 메소드를 import 한다:

- `get_object_or_404()`: 해당 모델의 기본 키(primary key) 값에 연결되는 특정 객체를 반환하거나 해당 record가 없을경우 `Http404`예외를 발생시킨다. 
- `HttpResponseRedirect`: 특정 URL로의 재전송을 생성한다. (HTTP status code 302). 
- `reverse()`: URL 설정(configuration) 의 이름과 전달인자들로 부터 URL을 만들어낸다. 템플릿에서 사용했던 `url`태그에 해당하는 파이썬 형식의 동일 표현이다.
- `datetime`: 날짜와 시간을 다루는 파이썬 라이브러리 이다. 

뷰 코드는 첫번째로 현재 `BookInstance`를 얻기위해 `get_object_or_404()`함수에 `pk` 전달인자를 사용한다( `BookInstance`가 없으면 뷰는 그 즉시 완료되며 페이지에는 "발견 하지 못함" 에러가 뜨게된다). `POST`요청이아니라면 ( `else`절로 처리되어) `renewal_date`필드에 대해 `initial`값을 넘겨주는 기본 폼을 생성한다. ( 기본 값은 아래 코드에서 볼드체로 표시된대로, 현재 날짜로 부터 3주후이다). 

```python
    book_instance = get_object_or_404(BookInstance, pk=pk)

    # GET 요청(혹은 다른 메소드)이면 기본 폼을 생성한다.
    else:
        proposed_renewal_date = datetime.date.today() + datetime.timedelta(weeks=3)
        book_renewal_form = RenewBookForm(initial={'renewal_date': proposed_renewal_date})

    context = {
        'form': book_renewal_form,
        'book_instance': book_instance,
    }

    return render(request, 'catalog/book_renew_librarian.html', context)
```

폼을 생성한이후, HTML 페이지를 생성하기 위해 `render()`를 호출하는데, 이 함수에서 템플릿과 폼을 포함하는 context를 특정한다. 이 경우에 context는 `BookInstance` 또한 포함하는데, `BookInstance`는 갱신하고자 하는 책의 정보를 템플릿에 제공하는데 사용한다.

하지만 `POST`요청이라면, `form`객체를 생성하고 `POST`요청에서의 데이터로 `form`을 채운다. 이 처리과정은 "binding"으로 불리며 폼의 유효성 체크를 할수 있도록 해준다. 여기에서 모든 필드에 관련된 유효성 체크 코드 - 날짜필드가 실제상황에서 유효한 값을 가지는지 체크하는 일반적인 코드와 날짜가 정해진 범위의 값을 가지는지 체크하는 폼의 특별한 함수인 `clean_renewal_date()` 를 포함하는 코드 - 를 실행하며 폼의 데이타가 유효한지 체크한다. 

```python
    book_instance = get_object_or_404(BookInstance, pk=pk)

    # POST 요청이면 폼 데이터를 처리한다
    if request.method == 'POST':

        # 폼 인스턴스를 생성하고 요청에 의한 데이타로 채운다 (binding):
        book_renewal_form = RenewBookForm(request.POST)

        # 폼이 유효한지 체크한다:
        if book_renewal_form.is_valid():
            # form.cleaned_data 데이타를 요청받은대로 처리한다(여기선 그냥 모델 due_back 필드에 써넣는다)
            book_inst.due_back = form.cleaned_data['renewal_date']
            book_inst.save()

            # 새로운 URL로 보낸다:
            return HttpResponseRedirect(reverse('all-borrowed') )

    context = {
        'form': book_renewal_form,
        'book_instance': book_instance,
    }

    return render(request, 'catalog/book_renew_librarian.html', context)
```

폼의 데이터가 유효하지 않다면 `render()`함수가 다시 호출된다. 하지만 이번에 context로 넘겨지는 폼의 값에는 에러메시지가 포함될 것이다.  

폼의 데이터가 유효하다면, `form.cleaned_data`속성을 통해 데이타 사용을 시작할수 있다(즉, 다음과 같다. `data = form.cleaned_data['renewal_date']`). 여기에서는 단지 폼 데이터를 `BookInstance`객체에 관련된 `due_back`변수에 저장했다. 

**중요사항**: 'request'객체를 통해 직접 폼 데이터를 가져올수는 있으나 ( 예를 들면 `request.POST['renewal_date']`나 GET 요청인경우 `request.GET['renewal_date']`처럼), 이 방식은 **절대** 추천하지 않는다. 위 코드에서 깔끔한 데이타(cleaned_data)란 것은 정제되고(sanitised), 유효성체크가되고, 파이썬에서 많이쓰는 타입의 데이타이다.

뷰에서 폼 처리의 마지막 단계는 , 대개는 "Success" 페이지라는 다른 페이지로 주소를 바꾸는 것이다. 여기서는 `'all-borrowed'`라는 뷰( 이 뷰는 [Django 튜토리얼 파트 8: 사용자 인증과 사용권한](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Authentication#challenge_yourself) 파트에서 "도전과제로" 생성했었다) 로 주소를 바꾸기 위해 `HttpResponseRedirect`와 `reverse()`를 사용한다. 당신이 이 페이지를 생성하지 않았다면 URL 주소가 '/'인 홈페이지로 주소를 변경하는 것을 고려해보자.

여기까지가 폼을 다루기 위해 필요한 모든 것이지만, 해당 폼 뷰의 사용권한을 도서관사서로 한정해야 하는 문제가 남아있다. `BookInstance`모델에 "`can_renew`"라는 새로운 사용권한을 추가해야 하겠지만, 작업을 간단하게 하기위해 그냥 기존의 사용권한`can_mark_returned`에 함수 데코레이터`@permission_required`를 사용하도록 하겠다.

그러므로 최종 뷰의 코드는 다음과 같다. 이 코드를 **locallibrary/catalog/views.py** 의 아랫부분에 복사해넣어라.

```python
import datetime

from django.contrib.auth.decorators import permission_required
from django.shortcuts import get_object_or_404
from django.http import HttpResponseRedirect
from django.urls import reverse

from catalog.forms import RenewBookForm

@permission_required('catalog.can_mark_returned')
def renew_book_librarian(request, pk):
    """도서관 사서에 의해 특정 BookInstance를 갱신하는 뷰 함수."""
    book_instance = get_object_or_404(BookInstance, pk=pk)

    # POST 요청이면 폼 데이터를 처리한다
    if request.method == 'POST':

        # 폼 인스턴스를 생성하고 요청에 의한 데이타로 채운다 (binding):
        book_renewal_form = RenewBookForm(request.POST)

        # 폼이 유효한지 체크한다:
        if book_renewal_form.is_valid():
            # book_renewal_form.cleaned_data 데이타를 요청받은대로 처리한다(여기선 그냥 모델 due_back 필드에 써넣는다)
            book_instance.due_back = book_renewal_form.cleaned_data['renewal_date']
            book_instance.save()

            # 새로운 URL로 보낸다:
            return HttpResponseRedirect(reverse('all-borrowed') )

    # GET 요청(혹은 다른 메소드)이면 기본 폼을 생성한다.
    else:
        proposed_renewal_date = datetime.date.today() + datetime.timedelta(weeks=3)
        book_renewal_form = RenewBookForm(initial={'renewal_date': proposed_renewal_date})

    context = {
        'form': book_renewal_form,
        'book_instance': book_instance,
    }

    return render(request, 'catalog/book_renew_librarian.html', context)
```

### [Template 작성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#template_작성하기)

뷰 에서 참조되는 템플릿 (**/catalog/templates/catalog/book_renew_librarian.html**)을 생성하고 아래 코드를 복사해넣어라 :

```html
{% extends "base_generic.html" %}

{% block content %}
    <h1>Renew: {{ book_instance.book.title }}</h1>
    <p>Borrower: {{ book_instance.borrower }}</p>
    <p{% if book_instance.is_overdue %} class="text-danger"{% endif %}>Due date: {{book_instance.due_back}}</p>

    <form action="" method="post">
        {% csrf_token %}
        {{ form.as_table }}
        <input type="submit" value="Submit">
    </form>
{% endblock %}
```

이 작업의 대부분은 앞선 튜토리얼에서 익숙해진 작업이다. 우리는 베이스 템플릿을 확장하고 콘텐츠 블럭을 재설정한다. `{{bookinst}}`(와 그에 따른 변수) 가 `render()` 함수 내의 컨텍스트 객체로 넘겨졌기 때문에 `{{bookinst}}`를 참조할수 있다. 이들을 이용해 책 제목, 대여자 그리고 이전 대여마감일의 목록을 열거한다.

폼 코드는 상대적으로 간단하다. 우선 form이 어디에 제출될 것인지(`action`)(POST인지 PUT인지) 명시하여 `form` 태그를 선언하고, 데이터를 제출하는 `method` 를 명시한다(이 경우에는 "HTTP POST") — 해당 페이지 위 쪽의 [HTML Forms](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#html_forms) overview에서 보았듯이,  `action`을 비워 놓았는데, 이렇게 하면 form 데이터가 현재 URL페이지로 다시 POST 된다(지금 우리가 하고자 하는 것입니다!). `form` 태그 안에는 `submit` input 태그 또한 만들어서 페이지 사용자가 눌러서 데이터를 제출(submit)할 수 있도록 한다. `form` 태그 안에정의된 또 다른 하나인 `{% csrf_token %}`는 Django의 cross-site 위조 방지의 방식 중 하나이다. 

**Note:** Add the `{% csrf_token %}` to every Django template you create that uses `POST` to submit data. This will reduce the chance of forms being hijacked by malicious users.

마지막으로 템플릿에 context라는 dictionary형 데이터로 넘기는 `{{form}}` 변수가 남았다. 별로 놀랍지 않을 수 있지만, 아래처럼 하면 form의 모든 field의 필드, 위젯, 도움말을 함께 렌더링하는 기본 렌더링기능을 사용할 수 있다 — 렌더링된 결과는 다음과 같다.

```html
<tr>
  <t><label for="id_renewal_date">Renewal date:</label></th>
  <td>
    <input id="id_renewal_date" name="renewal_date" type="text" value="2016-11-08" required />
    <br />
    <span class="helptext">Enter date between now and 4 weeks (default 3 weeks).</span>
  </td>
</tr>
```

**Note:** 필드가 하나만 있기 때문에 분명하지는 않지만 기본적으로 모든 필드는 자체 테이블 행에 정의되어 있습니다. 템플릿 변수 `{{ form.as_table }}`을 참조하면이 동일한 렌더링이 제공됩니다. 

유효하지 않은 날짜를 입력하는 경우 페이지에서 렌더링 된 오류 목록 (아래 굵게 표시)을 얻게됩니다.

```html
<tr>
  <th><label for="id_renewal_date">Renewal date:</label></th>
   <td>
      <ul class="errorlist">
        <li>Invalid date - renewal in past</li>
      </ul>
      <input id="id_renewal_date" name="renewal_date" type="text" value="2015-11-08" required />
      <br />
      <span class="helptext">Enter date between now and 4 weeks (default 3 weeks).</span>
    </td>
</tr>
```

#### Form template variable을 사용하는 다른 방법

위와 같이 `{{form.as_table``}}` 을 사용하면 각 필드가 테이블 행으로 렌더링됩니다. 또한 각 필드를 `{{form.as_ul}}` 을 사용하여 목록항목(list item)으로 렌더링하거나 `{{form.as_p}}`를 사용하여 단락(paragraph)으로 렌더링 할 수도 있습니다.

또한 dot notation을 사용하여 form 속성을 인덱싱하여 각 부분 렌더링을 완벽하게 제어 할 수도 있습니다. 예를 들어, `renewal_date` 필드에 대한 여러 개의 개별 항목에 접근 할 수 있습니다.

- `{{form.renewal_date}}:` The whole field.
- `{{form.renewal_date.errors}}`: The list of errors.
- `{{form.renewal_date.id_for_label}}`: The id of the label.
- `{{form.renewal_date.help_text}}`: The field help text.

템플릿의 양식을 수동으로 렌더링하고 템플릿 필드를 동적으로 반복하는 방법에 대한 자세한 예제는, [Working with forms > Rendering fields manually](https://docs.djangoproject.com/en/2.0/topics/forms/#rendering-fields-manually) (Django docs)를 참고.

### [Page를 시험하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#page를_시험하기)

If you accepted the "challenge" in [Django Tutorial Part 8: User authentication and permissions](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Authentication#challenge_yourself) you'll have a list of all books on loan in the library, which is only  visible to library staff. We can add a link to our renew page next to  each item using the template code below.

```html
{% if perms.catalog.can_mark_returned %}- <a href="{% url 'renew-book-librarian' bookinst.id %}">Renew</a>  {% endif %}
```

**Note**: Remember that your test login will need to have the permission "`catalog.can_mark_returned`" in order to access the renew book page (perhaps use your superuser account).

You can alternatively manually construct a test URL like this — [http://127.0.0.1:8000/catalog/book/**/renew/](http://127.0.0.1:8000/catalog/book//renew/) (a valid bookinstance id can be obtained by navigating to a book detail page in your library, and copying the `id` field).

### [What does it look like?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#what_does_it_look_like)

If you are successful, the default form will look like this:

![img](./images/forms_example_renew_default.png)

The form with an invalid value entered, will look like this:

![img](./images/forms_example_renew_invalid.png)

The list of all books with renew links will look like this:

![img](./images/forms_example_renew_allbooks.png)

## [ModelForms](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#modelforms)

Creating a `Form` class using the approach described above is very flexible, allowing you to create whatever sort of form page you like and associate it with any model or models.

However if you just need a form to map the fields of a *single* model then your model will already define most of the information that  you need in your form: fields, labels, help text, etc. Rather than  recreating the model definitions in your form, it is easier to use the [ModelForm](https://docs.djangoproject.com/en/2.0/topics/forms/modelforms/) helper class to create the form from your model. This `ModelForm` can then be used within your views in exactly the same way as an ordinary `Form`.

A basic `ModelForm` containing the same field as our original `RenewBookForm` is shown below. All you need to do to create the form is add `class Meta` with the associated `model` (`BookInstance`) and a list of the model `fields` to include in the form (you can include all fields using `fields = '__all__'`, or you can use `exclude` (instead of `fields`) to specify the fields *not* to include from the model).

```python
from django.forms import ModelForm
from .models import BookInstance

class RenewBookModelForm(ModelForm):
    class Meta:
        model = BookInstance
        fields = ['due_back',]
```

**Note**: This might not look like all that much simpler than just using a `Form` (and it isn't in this case, because we just have one field). However if you have a lot of fields, it can reduce the amount of code quite  significantly!

The rest of the information comes from the model field definitions  (e.g. labels, widgets, help text, error messages). If these aren't quite right, then we can override them in our `class Meta`,  specifying a dictionary containing the field to change and its new  value. For example, in this form we might want a label for our field of "*Renewal date*" (rather than the default based on the field name: *Due date*), and we also want our help text to be specific to this use case. The `Meta` below shows you how to override these fields, and you can similarly set `widgets` and `error_messages` if the defaults aren't sufficient.

```python
class Meta:
    model = BookInstance
    fields = ['due_back',]
    labels = { 'due_back': _('Renewal date'), }
    help_texts = { 'due_back': _('Enter a date between now and 4 weeks (default 3).'), } 
```

To add validation you can use the same approach as for a normal `Form` — you define a function named `clean_*field_name*()` and raise `ValidationError` exceptions for invalid values. The only difference with respect to our original form is that the model field is named `due_back` and not "`renewal_date`".

```python
from django.forms import ModelForm
from .models import BookInstance

class RenewBookModelForm(ModelForm):
    def clean_due_back(self):
       data = self.cleaned_data['due_back']

       #Check date is not in past.
       if data < datetime.date.today():
           raise ValidationError(_('Invalid date - renewal in past'))

       #Check date is in range librarian allowed to change (+4 weeks)
       if data > datetime.date.today() + datetime.timedelta(weeks=4):
           raise ValidationError(_('Invalid date - renewal more than 4 weeks ahead'))

       # Remember to always return the cleaned data.
       return data

    class Meta:
        model = BookInstance
        fields = ['due_back',]
        labels = { 'due_back': _('Renewal date'), }
        help_texts = { 'due_back': _('Enter a date between now and 4 weeks (default 3).'), }
```

The class `RenewBookModelForm` below is now functionally equivalent to our original `RenewBookForm`. You could import and use it wherever you currently use `RenewBookForm`.

## [Generic editing views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#generic_editing_views)

The form handling algorithm we used in our function view example above  represents an extremely common pattern in form editing views. Django  abstracts much of this "boilerplate" for you, by creating [generic editing views](https://docs.djangoproject.com/en/2.0/ref/class-based-views/generic-editing/) for creating, editing, and deleting views based on models. Not only do  these handle the "view" behaviour, but they automatically create the  form class (a `ModelForm`) for you from the model.

**Note:** In addition to the editing views described here, there is also a [FormView](https://docs.djangoproject.com/en/2.0/ref/class-based-views/generic-editing/#formview) class, which lies somewhere between our function view and the other  generic views in terms of "flexibility" vs "coding effort". Using `FormView` you still need to create your `Form`, but you don't have to implement all of the standard form-handling  pattern. Instead you just have to provide an implementation of the  function that will be called once the submitted is known to be be valid.

In this section we're going to use generic editing views to create pages to add functionality to create, edit, and delete `Author` records from our library — effectively providing a basic  reimplementation of parts of the Admin site (this could be useful if you need to offer admin functionality in a more flexible way that can be  provided by the admin site).

### [Views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#views)

Open the views file (**locallibrary/catalog/views.py**) and append the following code block to the bottom of it:

```python
from django.views.generic.edit import CreateView, UpdateView, DeleteView
from django.urls import reverse_lazy
from .models import Author

class AuthorCreate(CreateView):
    model = Author
    fields = '__all__'
    initial={'date_of_death':'05/01/2018',}

class AuthorUpdate(UpdateView):
    model = Author
    fields = ['first_name','last_name','date_of_birth','date_of_death']

class AuthorDelete(DeleteView):
    model = Author
    success_url = reverse_lazy('authors')
```

As you can see, to create the views you need to derive from `CreateView`, `UpdateView`, and `DeleteView` (respectively) and then define the associated model.

For the "create" and "update" cases you also need to specify the fields to display in the form (using in same syntax as for `ModelForm`). In this case we show both the syntax to display "all" fields, and how  you can list them individually. You can also specify initial values for  each of the fields using a dictionary of *field_name*/*value* pairs (here we arbitrarily set the date of death for demonstration  purposes — you might want to remove that!). By default these views will  redirect on success to a page displaying the newly created/edited model  item, which in our case will be the author detail view we created in a  previous tutorial. You can specify an alternative redirect location by  explicitly declaring parameter `success_url` (as done for the `AuthorDelete` class).

The `AuthorDelete` class doesn't need to display any of the fields, so these don't need to be specified. You do however need to specify the `success_url`, because there is no obvious default value for Django to use. In this case we use the `reverse_lazy()` function to redirect to our author list after an author has been deleted — `reverse_lazy()` is a lazily executed version of `reverse()`, used here because we're providing a URL to a class-based view attribute.

### [Templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#templates)

The "create" and "update" views use the same template by default, which will be named after your model: *model_name***_form.html** (you can change the suffix to something other than **_form** using the `template_name_suffix` field in your view, e.g. `template_name_suffix = '_other_suffix'`)

Create the template file **locallibrary/catalog/templates/catalog/author_form.html** and copy in the text below.

```html
{% extends "base_generic.html" %}

{% block content %}

<form action="" method="post">
    {% csrf_token %}
    <table>
    {{ form.as_table }}
    </table>
    <input type="submit" value="Submit" />

</form>
{% endblock %}
```

This is similar to our previous forms, and renders the fields using a table. Note also how again we declare the `{% csrf_token %}` to ensure that our forms are resistant to CSRF attacks.

The "delete" view expects to find a template named with the format *model_name***_confirm_delete.html** (again, you can change the suffix using `template_name_suffix` in your view). Create the template file **locallibrary/catalog/templates/catalog/author_confirm_delete****.html** and copy in the text below.

```html
{% extends "base_generic.html" %}

{% block content %}

<h1>Delete Author</h1>

<p>Are you sure you want to delete the author: {{ author }}?</p>

<form action="" method="POST">
  {% csrf_token %}
  <input type="submit" action="" value="Yes, delete." />
</form>

{% endblock %}
```

### [URL configurations](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#url_configurations)

Open your URL configuration file (**locallibrary/catalog/urls.py**) and add the following configuration to the bottom of the file:

```python
urlpatterns += [
    path('author/create/', views.AuthorCreate.as_view(), name='author_create'),
    path('author/<int:pk>/update/', views.AuthorUpdate.as_view(), name='author_update'),
    path('author/<int:pk>/delete/', views.AuthorDelete.as_view(), name='author_delete'),
]
```

There is nothing particularly new here! You can see that the views are classes, and must hence be called via `.as_view()`, and you should be able to recognise the URL patterns in each case. We must use `pk` as the name for our captured primary key value, as this is the parameter name expected by the view classes.

The author create, update, and delete pages are now ready to test (we won't bother hooking them into the site sidebar in this case, although  you can do so if you wish).

**Note**: Observant users will have noticed that we  didn't do anything to prevent unauthorised users from accessing the  pages! We leave that as an exercise for you (hint: you could use the `PermissionRequiredMixin` and either create a new permission or reuse our `can_mark_returned` permission).

### [Testing the page](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#testing_the_page)

First login to the site with an account that has whatever permissions you decided are needed to access the author editing pages.

Then navigate to the author create page: http://127.0.0.1:8000/catalog/author/create/, which should look like the screenshot below.

![Form Example: Create Author](./images/forms_example_create_author.png)

Enter values for the fields and then press **Submit** to save the author record. You should now be taken to a detail view for your new author, with a URL of something like *http://127.0.0.1:8000/catalog/author/10*.

You can test editing records by appending */update/* to the end of the detail view URL (e.g. *http://127.0.0.1:8000/catalog/author/10/update/*) — we don't show a screenshot, because it looks just like the "create" page!

Last of all we can delete the page, by appending delete to the end of the author detail-view URL (e.g. *http://127.0.0.1:8000/catalog/author/10/delete/*). Django should display the delete page shown below. Press **Yes, delete.** to remove the record and be taken to the list of all authors.

![img](./images/forms_example_delete_author.png)

## [Challenge yourself](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#challenge_yourself)

Create some forms to create, edit and delete `Book` records. You can use exactly the same structure as for `Authors`. If your **book_form.html** template is just a copy-renamed version of the **author_form.html** template, then the new "create book" page will look like the screenshot below:

![img](./images/forms_example_create_book.png)



## [Summary](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Forms#summary)

Creating and handling forms can be a complicated process! Django makes it much  easier by providing programmatic mechanisms to declare, render and  validate forms. Furthermore, Django provides generic form editing views  that can do *almost all* the work to define pages that can create, edit, and delete records associated with a single model instance.

There is a lot more that can be done with forms (check out our See  also list below), but you should now understand how to add basic forms  and form-handling code to your own websites.



# Django 튜토리얼 파트 10: Django 웹 어플리케이션 테스트하기

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing

웹사이트가 성장함에 따라 손으로 일일히 테스트하는 것은 점점 더 어려워진다. 테스트 할 내용이 늘어날 뿐만 아니라, 컴포넌트간의 상호작용도 복잡해지고, 한 쪽의 작은 수정이 다른쪽에 큰 영향을 줄수 있기 때문에,  모든것이 잘 동작할 수 있도록 더 많은 수정이 필요해지며, 그렇게 추가된 수정이 새로운 에러를 유발하지 않도록 확인되어야 한다.  이러한 문제들의 해결책중 하나는, 쉽고 안정적으로 수정사항이 발생할 때마다 실행되는 자동화된 테스트를 작성하는 것이다. 이  튜토리얼은 Django의 테스트 프레임워크를 사용하여 당신의 웹 사이트에 대한 Unit Testing을 자동화하는 방법을  보여줄것이다. 

| 사전학습: | 아래 파트를 포함하여 앞선 모든 튜토리얼 파트의 학습을 완료할 것. [Django 튜토리얼 파트 9: 폼으로 작업하기](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Forms). |
| --------- | ------------------------------------------------------------ |
| 학습목표: | Django 기반 웹사이트의 Unit Test 작성방법 이해하기.          |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#개요)

이제까지 개발된 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 의 현재 상태는 다음과 같다. 모든 book과 author의 목록, `Book` 과 `Author` 항목별 상세 뷰, `BookInstance` 갱신용 페이지, `Author` 항목의 생성,갱신,삭제를 위한 페이지( [forms tutorial](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Forms) 편의 도전과제도 완성 했다면 `Book` 편집 페이지도 포함)가 완성 되었다. 상대적으로 작은 이 사이트조차, 각 페이지가 기대한 대로 동작하는지 손으로 대강 체크하는 것만  해도 몇 분 정도는 걸린다. 사이트가 성장하면서 수정사항이 늘어날수록 적절하게 동작하는지 우리가 수동으로 체크해야 하는 양도  늘어날 수 밖에 없다. 손으로 직접 테스트 하는 방법을 계속 유지한다면, 결국은 대부분의 시간을 테스트에 사용하고 코드를 개선할 수 있는 시간은 거의 없어질 것이다. 

자동화된 테스트는 이러한 문제를 진짜로 해결할 수 있다! 명백한 이점은 수동 테스트보다는 훨신 빠르고, 훨씬 세부적인  내용까지도 테스트 할수 있으며, 매번 정확히 같은 기능을 테스트할 수 있다는 점(사람이 테스트한다면 결코 신뢰성있게 할 수 없는  부분!) 이다. 자동화 테스트는 빠르기 때문에 좀 더 정기적으로 실행할 수 있고, 테스트 실패시 코드가 기대대로 동작하지 않았던 부분을 정확히 지목할 수 있다.

게다가, 자동화된 테스트는 당신의 코드의 첫번째 실전 고객으로 역할을 수행하여, 당신의 웹사이트가 어떻게 동작해야하는지  엄격하게 정의하고 문서화하도록 압력을 준다. 종종 이 내용이 당신이 작성하게될 코드 예제나 관련문서의 기초 자료가 된다. 이러한  이유 때문에, 어떤 소프트웨어 개발 프로세스는 테스트 정의와 구현으로 시작되어, 테스트가 요구하는 동작을 충족하도록 코드가  작성되기도 한다. ( 예를 들면, [test-driven](https://en.wikipedia.org/wiki/Test-driven_development) 과 [behaviour-driven](https://en.wikipedia.org/wiki/Behavior-driven_development) 개발론).

이 튜토리얼은 , LocalLibrary 웹사이트에 몇가지 테스트를 추가하면서, Django에 대한 자동화된 테스트를 작성하는 방법을 보여준다.

### [Testing의 종류](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#testing의_종류)

테스트의 성격, 수준, 테스트의 종류및 테스트에 대한 접근방법은 수없이 많다. 가장 중요한 방법들은 다음과 같다. :

- Unit tests (유닛 테스트)

  독립적인 콤포넌트의 (성능이 아닌) 기능적인 동작을 검증한다. 흔히 class나 function 레벨로 수행한다.

- Regression tests ( 버그수정 확인 테스트 )

  기존에 보고된 버그들이 재발하는지 테스트한다. 각 테스트는, 먼저 이전에 발생했던 버그가 수정되었는지 체크한 이후에, 버그 수정으로 인해 새롭게 발생되는 버그가 없는지 확인차 재수행하게 된다.

- Integration tests ( 통합 테스트 )

  유닛 테스트를 완료한 각각의 독립적인 콤포넌트들이 함께 결합되어 수행하는 동작을 검증한다. 통합 테스트는 콤포넌트간에  요구되는 상호작용을 검사하며, 각 콤포넌트의 내부적인 동작까지 검증할 필요는 없다. 이 테스트는 단지 전체 웹사이트에 걸쳐 각  콤포넌트가 결합하여 수행하는 동작을 대상으로 한다.

**참고사항 :** 이외의 일반적인 테스트의 종류는 다음과 같다. 블랙박스, 화이트 박스, 수동,  자동, 카나리아, 스모크, 적합성 평가(conformance), 인수(acceptance), 기능성, 시스템, 성능, 로드,  스트레스 테스트등. 더 자세한 정보는 위의 키워드로 검색 바람.

### [Django가 testing을 위해 제공하는 것은?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#django가_testing을_위해_제공하는_것은)

웹사이트를 테스트하는 것은 복잡한 작업입니다. 왜냐하면 이것이 HTTP 레벨의 리퀘스트 핸들링, 쿼리모델들, 폼 인증과 처리 그리고 템플릿 렌더링과 같은 여러 로직 레이어로 만들어졌기 때문입니다.

Django는 파이선 표준 라이브러리 unittest로 만들어진 작은 클라스계층의 테스트 프레임워크를 제공합니다. 그 이름과 다르게 이 테스트 프레임워크는 유닛테스트와 통합테스트 모두에게 적당합니다. 이 Django 프레임워크는 웹과 Django의  독특한 특징을 테스트하는 것을 돕기 위한 API메소드와 도구들을 추가합니다. 이것들은 당신이 리퀘스트를 시험하고, 시험 데이터를  삽입하고 그리고 당신의 애플리케이션의 결과물을 검사할 수 있게 합니다. Django는 또한 다른 테스트 프레임워크들을 사용하기  위한 API (LiveServerTestCase)와 도구들을 제공합니다. 예를 들면 당신은 사용자가 생중계 브라우저와 소통하는  것을 시뮬레이션하는 유명한 Selenium 프레임워크와 통합할 수 있습니다.

테스트를 작성하기 위해서는 Django(또는 unittest) 테스트기반 클라스들([SimpleTestCase](https://docs.djangoproject.com/en/2.1/topics/testing/tools/#simpletestcase), [TransactionTestCase](https://docs.djangoproject.com/en/2.1/topics/testing/tools/#transactiontestcase), [TestCase](https://docs.djangoproject.com/en/2.1/topics/testing/tools/#testcase), [LiveServerTestCase](https://docs.djangoproject.com/en/2.1/topics/testing/tools/#liveservertestcase))로부터 어떤 것을 가져오고 그리고 그 다음에 특별한 기능이 기대했던대로 작동하는지 체크하기 위한 분리된 메소드들을 작성합니다.  (테스트들은 그 표현식의 결과가 True 또는 False 값인지 또는 그 두 값들이 동등한지 등을 테스트하기 위해 "assert" 메소드를 사용합니다) 당신이 테스트를 시작하면, 그 프레임워크는 당신의 가져온 클라스들안에서 선택된 테스트 메소드들을  실행합니다. 아래에 보이는 것과 같이, 그 테스트 메소드들은 클라스에서 정의된 보통의 셋업 그리고/또는 tear-down 방식을  가지고 독립적으로 실행됩니다.

```python
class YourTestClass(TestCase):
    def setUp(self):
        # Setup run before every test method.
        pass

    def tearDown(self):
        # Clean up run after every test method.
        pass

    def test_something_that_will_pass(self):
        self.assertFalse(False)

    def test_something_that_will_fail(self):
        self.assertTrue(False)
```

대부분의 테스트들을 위한 최선의 기본 클라스는 [django.test.TestCase](https://docs.djangoproject.com/en/2.0/topics/testing/tools/#testcase) 입니다. 이 테스트 클라스는 이것이 실행되기 전에 하나의 깨끗한 데이터베이스를 생성하고, 그리고 이 자체의 트랜젝션에서 모든 테스트를  실행합니다. 이 클라스는 자체의 테스트 클라이언트를 갖고있는데, 이것은 당신이 view 레벨에서 그 코드가 사용자와 상호작용하는  것을 시뮬레이션할 수 있게 합니다. 아래 섹션에서는 이  [TestCase](https://docs.djangoproject.com/en/2.0/topics/testing/tools/#testcase) 기본 클라스를 이용하여 유닛테스트들에 집중할 것입니다. 

**Note:** 이[django.test.TestCase](https://docs.djangoproject.com/en/2.0/topics/testing/tools/#testcase) 클라스는 매우 편리합니다. 그러나 어떤 테스트들은 그들이 필요로하는 것보다 느려지는 결과가 올 수 있습니다(모든 테스트들이 그들 자체의  데이터베이스나 또는 view 상호작용의 셋업이 필요한 것은 아닙니다) 한번 당신이 이 클라스를 통해서 무엇을 할 수 있는지  익숙해진다면, 당신은 더 심플한 테스트 클라스들을 가지고 당신의 몇몇 테스트들을 대체하게 될 것입니다.

### [무엇을 테스트해야 하는가?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#무엇을_테스트해야_하는가)

Python 또는 Django의 일부분으로서 제공되는 라이브러리들 또는 기능들을 제외한 당신 코드의 모든 면을 테스트해야합니다. 

예를 들면, 아래에 정의된 Author 모델을 가정합니다. 당신은 first_name과 last_name이 데이터베이스  CharField로서 적당하게 저장됐는지에 대해 명시적으로 테스트할 필요가 없습니다. 왜냐하면, 그것은 Django에 의해 정의된 것이기 때문입니다. (물론 사실은 당신이 개발하는 중에 이 기능들을 필연적으로 테스트했음에도 불구하고요) 또한  date_of_birth가 날짜 필드에 적합한 값을 갖었는지 테스트하는 것도 필요없습니다. 왜냐하면 그렇게 하는 것은  Django에서 다시한번 더 실행하는 것이 되니까요. 

그러나 당신은 그 레이블들(*First name, Last name, Date of birth, Died*)로 사용된 텍스트 그리고 그 텍스트(100 chars)을 위해 할당한 그 필드의 크기를 확인해야 합니다. 왜냐하면 이것들은 당신이 디자인한 것이고 추후에 깨지거나 변경될 수 있는 것이기 때문입니다.

```python
class Author(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField(null=True, blank=True)
    date_of_death = models.DateField('Died', null=True, blank=True)

    def get_absolute_url(self):
        return reverse('author-detail', args=[str(self.id)])

    def __str__(self):
        return f'{self.last_name}, {self.first_name}'
```

단순히, 당신은 당신의 요구에 따라 만들어지 메소드들 `get_absolute_url()` 과 `__str__()` 이 원래 요구된 대로 작동하는지 체크해야합니다. 왜냐하면 이것들은 당신이 만들어낸 코드/비즈니스 로직이기 때문입니다. `get_absolute_url()`의 경우에 있어서는 Django의 reverse() 메소드는 적당하게 작동되었다고 신뢰할 수 있습니다, 그래서 당신이 테스트해야 하는 것은 실제로 정의되어온 관련된 view 입니다. 

**Note:** 영특한 독자들은 date of birth와 date of death를 합리적인  값으로 제한해야 한다고 적어야 하고, 그리고 death는 birth보다 뒤에 왔는지를 체크해야 한다고 할 것입니다. Django에 있어서 이 제약은 당신의 폼클라스에 추가될 것입니다(당신이 그 필드들의 이러한 모습을 모델 레벨이 아니라 오직 폼 레벨에서  사용될 수 있도록 인증을 정의할 수 있다고 할지라도 말입니다)

이런 것들을 마음에 두고서 테스트를 정의하고 실행해봅시다.

## [테스트 구조 개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#테스트_구조_개요)

무엇을 테스트 할 지 자세히 보기 전에, 간단히 어디서 그리고 어떻게 테스트가 정의되는지 대략 살펴 봅시다.

장고는 유닛테스트의 모듈인 [built-in test discovery](https://docs.python.org/3/library/unittest.html#unittest-test-discovery)을 사용하는데, 이는 현재 작업중인 디렉토리의 **test\*.py**라는 패턴을 가진 모든 파일들을 체크합니다. 그 파일들의 이름을 적당하게 붙이는 한, 당신은 당신이 원하는 어떤 구조라도 이용할 수  있습니다. 우리는 당신의 테스트코드를 위한 한 모듈을 만들 것을 추천합니다. 그리고 모델들, 뷰들, 폼들 그리고 테스트가 필요한  어떤 다른 타입의 코드라도 각각을 분리하기를 바랍니다. 예를 들면:

```cmd
catalog/
  /tests/
    __init__.py
    test_models.py
    test_forms.py
    test_views.py
```

당신의 LocalLibrary 프로젝트에서 위와 같은 구조의 파일을 만드십시오.  __init__.py 파일은 비어있는  파일입니다.(이것은 Python에게 이 디렉토리가 패키지임을 알려줍니다) skeleton  테스트파일인 /catalog/tests.py를 복사하여 이름을 바꿔서 위의 세개의 테스트파일을 만드십시오.

**Note:** 이 skeleton 테스트파일 **/catalog/tests.py 은 우리가** [Django skeleton website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website) 를 만들었을 때 자동으로 생성됩니다. 당신의 테스트들을 여기에 모두 넣는 것도 괜찮습니다만, 당신이 적절하게 테스트를 해나가면 당신은 금방 매우 크고, 관리할 필요 없는 테스트파일로 끝나게 될 것입니다. 

이 skeleton 파일은 우리에게 필요하지 않으니 지우십시오.

**/catalog/tests/test_models.py** 파일을 오픈하십시오. 이 파일은 아래와 같이 `django.test.TestCase `가 import 되었을 것입니다.

```python
from django.test import TestCase

# Create your tests here.
```

 당신은 종종 특정한 기능을 테스트하기 위한 개별적인 메소드들을 가지고 당신이  테스트하기를 원하는 각각의 모델/뷰/폼을 위한 테스트 클라스를 추가할 것입니다. 또 다른 경우에는 당신은 그 사용사례의 다방면을  테스트하기 위한 개별적인 테스트기능을 가지고, 특별한 사용사례를 테스트하기 위한 별도의 클라스를 갖기를 원할 것입니다. (예를  들면, 실패한 사례들을 테스트하는 기능을 가지고 모델 필드가 적정하게 인증되었는지 테스트하는 한 클라스) 다시한번, 그 구조는  오직 당신 자신에게 달렸습니다. 그러나 당신이 일관되게 하는 것이 최선의 방법입니다. 

아래의 테스트 클라스를 파일 맨아랫부분에 추가하십시오. 이 클라스는 `TestCase`으로부터 이끌어내어서 어떻게 테스트케이스 클라스를 구축하는지 예시를 보여줍니다.

```python
class YourTestClass(TestCase):
    @classmethod
    def setUpTestData(cls):
        print("setUpTestData: Run once to set up non-modified data for all class methods.")
        pass

    def setUp(self):
        print("setUp: Run once for every test method to setup clean data.")
        pass

    def test_false_is_false(self):
        print("Method: test_false_is_false.")
        self.assertFalse(False)

    def test_false_is_true(self):
        print("Method: test_false_is_true.")
        self.assertTrue(False)

    def test_one_plus_one_equals_two(self):
        print("Method: test_one_plus_one_equals_two.")
        self.assertEqual(1 + 1, 2)
```

 이 새로운 클래스는 테스트 전 준비를 위해 사용할 수 있는 메쏘드 두 개를 정의합니다. (예를 들면 테스트에 필요한 모델이나 객체를 생성해 놓을 수 있습니다):

- `setUpTestData()` 는 클래스 전체에서 사용되는 설정을 위해서 테스트 시작 때 딱 한 번만 실행됩니다. 테스트 메쏘드가 실행되면서 수정되거나 변경되지 않을 객체들을 이곳에서 생성할 수 있습니다.
- `setUp()` 은 각각의 테스트 메쏘드가 실행될 때마다 실행됩니다. 테스트 중 내용이 변경될 수 있는 객체를 이곳에서 생성할 수 있습니다 (모든 테스트 메쏘드는 방금 막 생성된 ("fresh") 오브젝트를 입력받게 됩니다).

테스트 클래스는 위 예제에서 사용하지 않은 `tearDown(`h 메소드를 가지고 있습니다. 이 메소드는 특히 데이터베이스 테스트에는 유용하지가 않은데 바로 베이스 클래스인 `TestCase` 가 데이터베이스 삭제(teardown) 을 처리해주기 때문입니다.

아래에는 `Assert` 함수를 사용하여 조건이 참, 거짓 또는 동일한지 테스트하는 여러 가지 테스트 메서드가 있습니다  (`AssertTrue`, `AssertFalse`, `AssertEqual`). 조건이 예상대로 실행되지 않으면 테스트가 실패하고 콘솔에 오류를 보고합니다.

일반적으로 위와 같이 테스트에 **print()** 함수를 포함하면 **안**됩니다. 여기서는 setup 함수들이 콘솔에서 호출되는 순서를 (다음 절에서) 볼 수 있도록 하기 위해 사용되었습니다.

## [어떻게 테스트를 작동시키는가](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#어떻게_테스트를_작동시키는가)

모든 테스트를 실행하는 가장 쉬운 방법은 다음 명령을 사용하는 것입니다.

```cmd
python3 manage.py test
```

이 명령은 현재 경로에서 **test\*.py** 패턴을 만족하는 모든 파일을 찾은 후 이들 테스트를 적합한 베이스 클래스를 이용해서 실행합니다 (우리는 현재 여러 개의 테스트 파일을 가지고 있지만 **/catalog/tests/test_models.py** 만이 실제 테스트를 포함하고 있습니다. 기본적으로 각각의 테스트는 실패 결과만을 보고하며, 마지막으로 테스트 결과 요약을 출력합니다.

만약 `ValueError: Missing staticfiles manifest entry ...` 과 같은 에러를 마주칠 수 있습니다. 이런 에러는 보통 테스팅 도구가 기본적으로 collectstatic을 실행하지 않고, 당신의 앱이 이를 요구하는 storage 클래스를 사용하기 때문일 수 있습니다 ([manifest_strict](https://docs.djangoproject.com/en/2.0/ref/contrib/staticfiles/#django.contrib.staticfiles.storage.ManifestStaticFilesStorage.manifest_strict) 에 더 자세한 정보가 적혀 있습니다). 이 문제를 해결할 수 있는 다양한 방법이 있지만 가장 간단한 방법은 테스트 실행 전에 collectstatic을 실행하는 것입니다.

```cmd
python3 manage.py collectstatic
```

*LocalLibrary*의 루트 디렉토리에서 테스트를 실행하세요. 당신은 아래와 같은 출력output을 볼 것입니다.

```shell
> python3 manage.py test

Creating test database for alias 'default'...
setUpTestData: Run once to set up non-modified data for all class methods.
setUp: Run once for every test method to setup clean data.
Method: test_false_is_false.
setUp: Run once for every test method to setup clean data.
Method: test_false_is_true.
setUp: Run once for every test method to setup clean data.
Method: test_one_plus_one_equals_two.
.
======================================================================
FAIL: test_false_is_true (catalog.tests.tests_models.YourTestClass)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\Github\django_tmp\library_w_t_2\locallibrary\catalog\tests\tests_models.py", line 22, in test_false_is_true
    self.assertTrue(False)
AssertionError: False is not true

----------------------------------------------------------------------
Ran 3 tests in 0.075s

FAILED (failures=1)
Destroying test database for alias 'default'...
```

여기서 우리는 테스트 하나가 실패했음을 알 수 있으며, 실패한 함수와 실패한 이유를 정확히 볼 수 있습니다 (False가 True가 아니기 때문에 실패하는 것이 당연합니다).

**팁**: 우리는 위의 출력 결과를 통해서 객체와 메소드에 설명적인, 정보가 많은 이름을 사용하는 것이 테스트 결과를 이해하는데 도움이 된다는 것을 배울 수 있습니다.

**굵게** 표시된 텍스트는 보통은 테스트 결과에 출력되지 않습니다 (우리가 테스트에서 사용한 `print()` 함수가 출력한 것입니다). 이는 `setUpTestData()` 가 클래스 당 한 번, `setUp()` 이 메소드 당 한 번 실행되는 것을 보여줍니다.

다음 섹션은 특정 테스트만 실행하는 방법과 테스트가 출력하는 정보를 조절하는 방법을 알려줍니다.

### [테스트에 대해 더 많은 정보를 출력하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#테스트에_대해_더_많은_정보를_출력하기)

테스트 실행에 대한 자세한 정보를 얻으려면 verbosity를 조절할 수 있습니다. 예를 들어 테스트 실패와 더불어 성공 (그리고  테스트 데이터베이스 생성 과정에 대한 정보)를 나열하려면 다음과 같이 verbosity를 "2"로 설정할 수 있습니다.

```shell
python3 manage.py test --verbosity 2
```

허용되는 verbosity levels 은 0, 1, 2, and 3, 이며 기본값은 "1" 입니다.

### [테스트의 일부만 실행하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#테스트의_일부만_실행하기)

테스트 중 일부만 실행하려면 패키지, 모듈, `TestCase` 서브클래스, 메서드의 전체 경로를 지정해주면 됩니다.

```shell
# Run the specified module
python3 manage.py test catalog.tests

# Run the specified module
python3 manage.py test catalog.tests.test_models

# Run the specified class
python3 manage.py test catalog.tests.test_models.YourTestClass

# Run the specified method
python3 manage.py test catalog.tests.test_models.YourTestClass.test_one_plus_one_equals_two
```

## [LocalLibrary 테스트](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#locallibrary_테스트)

테스트를 어떻게 돌릴지와 어떤 내용을 테스트할지를 알았으니 이제 실제 예제들을 봅시다.

**Note:** 우리는 가능한 모든 테스트를 작성하지는 않을 것입니다. 하지만 아래의 예제만으로도 테스트가 어떻게 동작하는지와 어떤 테스트를 작성할 수 있을지 아이디어를 얻을 수 있을 것입니다.

### [모델](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#모델)

위에서 논의했듯이 우리는 우리가 디자인 했거나, 우리가 작성한 코드의 동작만을 테스트해야하지 Django 또는 Python 개발팀에서 이미 테스트 한 라이브러리 / 코드는 테스트하지 않아야 합니다.

예를 들어, 아래 `Author` 모델을 봅시다. 여기서 우리는 모든 필드의 라벨을 테스트해야 합니다.  우리가 필드의 라벨을 지정하지는 않았지만 우리의 디자인은 라벨의 값이 어때야하는지를 이미 정해놓고 있기 때문입니다. 우리가 이  값들을 테스트하지 않는다면 필드 라벨에 의도된 값을 가지는지 알 수 없습니다. 마찬가지로 비록 우리는 Django가 필드들을  지정된 길이대로 만들 것이라고 믿지만, 그래도 필드의 길이를 테스트해보는 것이 헛되지는 않습니다.

```python
class Author(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField(null=True, blank=True)
    date_of_death = models.DateField('Died', null=True, blank=True)

    def get_absolute_url(self):
        return reverse('author-detail', args=[str(self.id)])

    def __str__(self):
        return f'{self.last_name}, {self.first_name}'
```

**/catalog/tests/test_models.py** 을 열어서 기존 코드를 다음의 `Author` 모델 테스트 코드로 바꿔주세요.

먼저 우리는 `TestCase` 를 불러온 후 이를 상속해서 우리의 테스트 케이스인 `AuthorModelTest` 를 작성합니다. 테스트가 실패할 경우 출력에서 테스트 명을 쉽게 파악할 수 있도록 이름을 작성한 것에 주목해 주세요. 또 우리는 `setUpTestData()`를 불러서 테스트 시에 이용되지만 수정은 가하지 않는 저자 객체를 생성합니다. 

```python
from django.test import TestCase

from catalog.models import Author

class AuthorModelTest(TestCase):
    @classmethod
    def setUpTestData(cls):
        # Set up non-modified objects used by all test methods
        Author.objects.create(first_name='Big', last_name='Bob')

    def test_first_name_label(self):
        author = Author.objects.get(id=1)
        field_label = author._meta.get_field('first_name').verbose_name
        self.assertEquals(field_label, 'first name')

    def test_date_of_death_label(self):
        author=Author.objects.get(id=1)
        field_label = author._meta.get_field('date_of_death').verbose_name
        self.assertEquals(field_label, 'died')

    def test_first_name_max_length(self):
        author = Author.objects.get(id=1)
        max_length = author._meta.get_field('first_name').max_length
        self.assertEquals(max_length, 100)

    def test_object_name_is_last_name_comma_first_name(self):
        author = Author.objects.get(id=1)
        expected_object_name = f'{author.last_name}, {author.first_name}'
        self.assertEquals(expected_object_name, str(author))

    def test_get_absolute_url(self):
        author = Author.objects.get(id=1)
        # This will also fail if the urlconf is not defined.
        self.assertEquals(author.get_absolute_url(), '/catalog/author/1')
```

필드 테스트들은 필드 라벨의 값(`verbose_name`)을 확인하고 character 필드의 크기가 예상대로인지를 확인합니다. 이들 테스트 메소드의 변수명은 모두 메소드의 기능을 묘사하고 있으며 같은 규칙을 따릅니다.

```python
# Get an author object to test
author = Author.objects.get(id=1)

# Get the metadata for the required field and use it to query the required field data
field_label = author._meta.get_field('first_name').verbose_name

# Compare the value to the expected result
self.assertEquals(field_label, 'first name')
```

몇 가지 흥미로운 점들이 있습니다:

- 우리는 `author.first_name.verbose_name` 을 이용해서 `verbose_name`을 얻지 않습니다. 왜냐면 `author.first_name`은 문자열이지 우리가 속성에 접근할 수 있는 `first_name`객체가 아니기 때문입니다. 대신 우리는 author 객체의 `_meta` 속성을 이용해서 필드에 접근하여 필요한 정보를 얻습니다. 
- 우리는 `assertTrue(field_label == 'first name'` 대신 `assertEquals(field_label,'first name')` 을 이용했습니다. 왜냐면 만약 테스트가 실패할 경우 후자는 실제 라벨의 값을 알려주어 디버깅을 조금 더 용이하게 만들어주기 때문입니다.

**Note:** `last_name` 과 `date_of_birth` 필드의 라벨에 대한 테스트와 `last_name` 필드의 길이에 대한 테스트는 생략되었습니다. 기존 테스트의 이름 작성 규칙 (naming convention)과 테스트 작성법을 이용해서 직접 테스트를 작성해보세요. 

우리는 우리가 작성한 메서드도 테스트를 해야 합니다. 객체의 이름이 우리가 예상한 대로 "Last Name", "First Name" 규칙에 맞게 생성되었는지와 `Author` 객체의 URL이 우리가 예상한 대로 생성되는지를 보면 됩니다. 

```python
def test_object_name_is_last_name_comma_first_name(self):
    author = Author.objects.get(id=1)
    expected_object_name = f'{author.last_name}, {author.first_name}'
    self.assertEquals(expected_object_name, str(author))

def test_get_absolute_url(self):
    author = Author.objects.get(id=1)
    # This will also fail if the urlconf is not defined.
    self.assertEquals(author.get_absolute_url(), '/catalog/author/1')
```

테스트들을 돌려봅시다. Author 모델을 앞선 튜토리얼에 따라 만들었다면 아마 다음과 같이 `date_of_death` 라벨에 대한 에러가 나올 것입니다. 이 테스트는 라벨명이 라벨의 첫글자를 대문자로 처리하지 않는 장고의 컨벤션에 따라 생성된 것을 가정했기 때문에 실패합니다.

```shell
======================================================================
FAIL: test_date_of_death_label (catalog.tests.test_models.AuthorModelTest)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\...\locallibrary\catalog\tests\test_models.py", line 32, in test_date_of_death_label
    self.assertEquals(field_label,'died')
AssertionError: 'Died' != 'died'
- Died
? ^
+ died
? ^
```

이는 아주 사소한 버그이지만, 테스트를 작성하는 것이 코드를 작성할 때 하는 가정들을 얼마나 꼼꼼히 체크하는지를 보여줍니다. 

**Note:**  date_of_death 필드의 라벨을 (/catalog/models.py) "died" 로 바꾸고 테스트를 다시 돌려보세요.

다른 모델을 테스트하는 패턴도 이와 비슷하므로 여기서는 더 다루지 않겠습니다. 다른 모델에 대한 테스트 코드도 직접 작성해 보세요.

### [Forms](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#forms)

Forms를 테스트 하는 것은 모델을 테스트 하는 것과 비슷합니다; 당신이 만들고 디자인한 세세한 모든 것들은 테스트가 필요하며, 프레임워크나 써드 파티 라이브러리 등에 대해서는 테스트를 작성하지 않아도 좋습니다.

따라서 폼에 대한 테스트 코드를 작성할 때는 보통 폼이 우리가 원하는 필드를 가지고 있는지, 그리고 이들 필드들이 적절한  라벨과 도움말과 함께 나타나는지를 테스트하면 됩니다. 직접 별도의 필드와 검증 로직을 작성하지 않은 이상 장고가 필드  타입을 제대로 검증하는지는 테스트 하지 않아도 됩니다 - 예를 들어 이메일 필드가 정말로 이메일 주소 값만을 받아들이는지 직접  테스트 할 필요가 없습니다. 하지만 필드에 대한 다른 추가적인 유효성 검증 로직과 다른 에러 메시지에 대해서는 테스트가  필요합니다.

책 정보를 갱신하기 위한 우리의 Form을 생각해봅시다. 이 Form은 갱신 날짜를 위한 하나의 필드를 가지고 있으며 해당 필드는 우리가 테스트해야 할 라벨과 도움말을 가지고 있습니다.

```python
class RenewBookForm(forms.Form):
    """Form for a librarian to renew books."""
    renewal_date = forms.DateField(help_text="Enter a date between now and 4 weeks (default 3).")

    def clean_renewal_date(self):
        data = self.cleaned_data['renewal_date']

        # Check if a date is not in the past.
        if data < datetime.date.today():
            raise ValidationError(_('Invalid date - renewal in past'))

        # Check if date is in the allowed range (+4 weeks from today).
        if data > datetime.date.today() + datetime.timedelta(weeks=4):
            raise ValidationError(_('Invalid date - renewal more than 4 weeks ahead'))

        # Remember to always return the cleaned data.
        return data
```

**/catalog/tests/test_forms.py** 을 열어서 기존 코드를 아래의 `RenewBookForm` 테스트 코드로 바꿔주세요. 아래 코드는 우선 우리의 Form과 시간에 관련한 로직 테스트를 도와줄 파이썬 및 장고 라이브러리를  불러옵니다. 그리고 우리는 모델을 테스트 했을 때와 마찬가지로 Form 테스트 클래스를 선언하고, 마찬가지로 테스트의 목적과  기능을 알려주는 이름을 지어줬습니다.

```python
import datetime

from django.test import TestCase
from django.utils import timezone

from catalog.forms import RenewBookForm

class RenewBookFormTest(TestCase):
    def test_renew_form_date_field_label(self):
        form = RenewBookForm()
        self.assertTrue(form.fields['renewal_date'].label == None or form.fields['renewal_date'].label == 'renewal date')

    def test_renew_form_date_field_help_text(self):
        form = RenewBookForm()
        self.assertEqual(form.fields['renewal_date'].help_text, 'Enter a date between now and 4 weeks (default 3).')

    def test_renew_form_date_in_past(self):
        date = datetime.date.today() - datetime.timedelta(days=1)
        form = RenewBookForm(data={'renewal_date': date})
        self.assertFalse(form.is_valid())

    def test_renew_form_date_too_far_in_future(self):
        date = datetime.date.today() + datetime.timedelta(weeks=4) + datetime.timedelta(days=1)
        form = RenewBookForm(data={'renewal_date': date})
        self.assertFalse(form.is_valid())

    def test_renew_form_date_today(self):
        date = datetime.date.today()
        form = RenewBookForm(data={'renewal_date': date})
        self.assertTrue(form.is_valid())

    def test_renew_form_date_max(self):
        date = timezone.now() + datetime.timedelta(weeks=4)
        form = RenewBookForm(data={'renewal_date': date})
        self.assertTrue(form.is_valid())
```

앞의 두 함수는 필드의 라벨과 도움말이 예상대로 생성되었는지를 확인합니다. 우리는 필드를 fields 딕셔너리를 통해서 접근했습니다 (e.g. `form.fields['renewal_date']`). 라벨의 값이 `None` 인지도 확인해야하는 것을 기억해 두세요. 장고가 올바른 라벨을 렌더하더라도 만약 라벨의 값이 명시적으로 정해지지 않았다면 `None` 이 반환됩니다.

나머지 함수들은 폼이 적절한 구간 내에 있는 갱신 일자를 수락하는지와 더불어 부적합한 구간에 있는 일자를 거절하는지를 테스트 합니다. 우리가 테스트용 날짜들을 `datetime.timedelta()` (몇 일이나 몇 주를 나타냅니다)을 이용해서 현재 날짜 근처로(`datetime.date.today()` ) 생성하는 것을 기억해두세요. 그리고나서 우리는 폼을 만들고, 데이터를 집어넣고, 데이터가 유효한지를 테스트합니다.

**Note:** 여기서 우리는 데이터베이스나 테스트 클라이언트를 사용하지 않습니다. [SimpleTestCase](https://docs.djangoproject.com/en/2.0/topics/testing/tools/#django.test.SimpleTestCase)를 이용해서 테스트 클래스를 만드는 것을 고려해보세요.

또한 우리는 만약 폼이 유효하지 않을 경우 적절한 에러가 발생하는지 역시 검증해야 합니다. 하지만 보통 이 부분은 view processing의 일부로 처리되기 때문에 다음 섹션에서 다루도록 하겠습니다.

이것이 form에 대한 전부입니다. 비록 다른 폼들이 있지만, 이들은 우리의 클래스 기반 편집 뷰에 의해 생성된 것들이기  때문에 그쪽에서 테스트 되어야 합니다. 테스트를 실행하고 우리의 코드가 여전히 테스트를 통과하는지 확인해 보세요.

### [Views](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#views)

우리의 뷰 동작을 유효하게 하기 위해서 우리는 Django test [Client](https://docs.djangoproject.com/en/2.0/topics/testing/tools/#django.test.Client)를 사용합니다. 이 클래스는 더미 웹 브라우저와 같이 동작하는데, 우리는 URL의 `GET` 과 `POST` 요청을 동시에 하여 그 반응을 살핍니다. 우리는 저수준의 HTTP (응답의 헤더와 상태 코드) 부터 HTML을 렌더하기 위한  템플릿, 그리고 우리가 템플릿에 입력하는 컨텍스트 데이터까지 응답에 대한 거의 모든 것을 확인할 수 있습니다. 또한 우리는 (만약 이뤄진다면) redirect가 진행되는 단계와 각 단계에 대한 URL 및 상태 코드 역시 확인할 수 있습니다. 이를 통해서  우리는 각 뷰가 예상된 대로 동작하는지를 확인할 수 있습니다.

우리의 가장 간단한 뷰 중 하나인 모든 저자들의 목록을 반환하는 뷰부터 시작해봅시다. 이 뷰는 **/catalog/authors/** URL(URL 설정 상에서 'authors'로 명명되었습니다) 에서 출력됩니다.

```python
class AuthorListView(generic.ListView):
    model = Author
    paginate_by = 10
```

이 뷰가 generic list 뷰이다 보니 거의 모든 것이 장고에 의해서 처리됩니다. 만약 장고를 믿는다면 우리는 오로지 뷰가  올바른 URL과 뷰 이름으로 접근 가능한지만 테스트하면 됩니다. 하지만 만약 테스트 주도 방법론 (TDD)를 따른다면 우리는  (코드를 작성하기 전에) 뷰가 모든 저자들을 10명 씩 paginate해서 보여주는지를 확인하는 테스트부터 작성해야 합니다.

**/catalog/tests/test_views.py**를 열어서 기존 코드를 다음의 `AuthorListView` 테스트 코드로 바꿔주세요. 앞서와 마찬가지로 우리 모델과 유용한 클래스들을 불러옵니다.`setUpTestData()` 에서는 pagination을 테스트하기 위해 `Author` 객체 여러 개를 생성합니다.

```python
from django.test import TestCase
from django.urls import reverse

from catalog.models import Author

class AuthorListViewTest(TestCase):
    @classmethod
    def setUpTestData(cls):
        # Create 13 authors for pagination tests
        number_of_authors = 13

        for author_id in range(number_of_authors):
            Author.objects.create(
                first_name=f'Christian {author_id}',
                last_name=f'Surname {author_id}',
            )

    def test_view_url_exists_at_desired_location(self):
        response = self.client.get('/catalog/authors/')
        self.assertEqual(response.status_code, 200)

    def test_view_url_accessible_by_name(self):
        response = self.client.get(reverse('authors'))
        self.assertEqual(response.status_code, 200)

    def test_view_uses_correct_template(self):
        response = self.client.get(reverse('authors'))
        self.assertEqual(response.status_code, 200)
        self.assertTemplateUsed(response, 'catalog/author_list.html')

    def test_pagination_is_ten(self):
        response = self.client.get(reverse('authors'))
        self.assertEqual(response.status_code, 200)
        self.assertTrue('is_paginated' in response.context)
        self.assertTrue(response.context['is_paginated'] == True)
        self.assertTrue(len(response.context['author_list']) == 10)

    def test_lists_all_authors(self):
        # Get second page and confirm it has (exactly) remaining 3 items
        response = self.client.get(reverse('authors')+'?page=2')
        self.assertEqual(response.status_code, 200)
        self.assertTrue('is_paginated' in response.context)
        self.assertTrue(response.context['is_paginated'] == True)
        self.assertTrue(len(response.context['author_list']) == 3)
```

모든 테스트는 `TestCase`를 상속한 우리 테스트 클래스에 있는 클라이언트를 이용해서 `GET` 요청을 하고 그에 따른 응답을 받습니다. 첫번째 테스트는 특정 URL (도메인이 아닌 상대 경로임을 기억하세요) 을 확인하고 두번째 테스트는 URL 설정에서 설정해준 뷰의 이름에서 얻은 URL을 확인합니다.

```python
response = self.client.get('/catalog/authors/')
response = self.client.get(reverse('authors'))
```

응답을 받으면 우리는 응답에서 상태 코드, 사용된 템플릿, pagination이 되었는지 여부, 반환된 객체의 갯수, 그리고 전체 아이템의 갯수를 확인합니다.

우리가 검증하는 변수 중 가장 흥미로운 변수는 바로 `response.context`로 뷰에 의해서  템플릿에 전달되는 context 변수입니다. 이는 템플릿이 필요한 모든 데이터를 받는지를 검증할 수 있게 해주기 때문에 테스팅에  정말 유용합니다. 즉 우리는 어떤 템플릿이 사용되고 또 어떤 데이터가 템플릿에 전달되는지를 확인할 수 있기 때문에 자신있게  렌더링에 관한 나머지 문제들은 오로지 템플릿의 문제라고 생각할 수 있습니다.

#### 로그인한 사용자에게만 보이는 뷰

종종 우리는 로그인 한 사용자에게만 보이는 뷰를 테스트하고 싶습니다. 예를 들어서 `LoanedBooksByUserListView`는 방금 테스트한 뷰와 비슷하지만 로그인한 유저만 접근할 수 있으며, 현재 유저가 대출한, '대출 중' 상태의 `BookInstance` 만 보여주며 가장 오래된 것 부터 먼저 보여줍니다.

```python
from django.contrib.auth.mixins import LoginRequiredMixin

class LoanedBooksByUserListView(LoginRequiredMixin, generic.ListView):
    """Generic class-based view listing books on loan to current user."""
    model = BookInstance
    template_name ='catalog/bookinstance_list_borrowed_user.html'
    paginate_by = 10

    def get_queryset(self):
        return BookInstance.objects.filter(borrower=self.request.user).filter(status__exact='o').order_by('due_back')
```

아래 코드를 **/catalog/tests/test_views.py** 에 추가해주세요. 여기서 우리는 `SetUp()` 을 이용해서 계정을 생성한 다음 테스트에 사용할 `BookInstance` 객체와 관련된 책 및 기타 정보를 생성합니다. 각각의 테스트 계정에 의해서 책이 반반씩 대출되었지만 일단 우리는 모든 책의 상태를 "maintenance"로 설정합니다. 우리는 테스트 하면서 이들 객체들을 수정할 것이기 때문에 `setUpTestData()` 대신 `SetUp()`을 사용했습니다.

**Note:** 아래의 `setUp()` 코드는 특정 `Language`의 책을 생성하지만, `Language` 모델이 이전 튜토리얼에서 도전 과제로 생성되었기 때문에 여러분의 코드에는 존재하지 않을 수 있습니다. 이때는 간단히 `Language` 객체를 불러오거나 생성하는 코드를 주석처리 해주세요. 같은 작업을 곧 나올 `RenewBookInstancesViewTest` 에도 해줘야 합니다.

```python
import datetime

from django.utils import timezone
from django.contrib.auth.models import User # Required to assign User as a borrower

from catalog.models import BookInstance, Book, Genre, Language

class LoanedBookInstancesByUserListViewTest(TestCase):
    def setUp(self):
        # Create two users
        test_user1 = User.objects.create_user(username='testuser1', password='1X<ISRUkw+tuK')
        test_user2 = User.objects.create_user(username='testuser2', password='2HJ1vRV0Z&3iD')

        test_user1.save()
        test_user2.save()

        # Create a book
        test_author = Author.objects.create(first_name='John', last_name='Smith')
        test_genre = Genre.objects.create(name='Fantasy')
        test_language = Language.objects.create(name='English')
        test_book = Book.objects.create(
            title='Book Title',
            summary='My book summary',
            isbn='ABCDEFG',
            author=test_author,
            language=test_language,
        )

        # Create genre as a post-step
        genre_objects_for_book = Genre.objects.all()
        test_book.genre.set(genre_objects_for_book) # Direct assignment of many-to-many types not allowed.
        test_book.save()

        # Create 30 BookInstance objects
        number_of_book_copies = 30
        for book_copy in range(number_of_book_copies):
            return_date = timezone.now() + datetime.timedelta(days=book_copy%5)
            the_borrower = test_user1 if book_copy % 2 else test_user2
            status = 'm'
            BookInstance.objects.create(
                book=test_book,
                imprint='Unlikely Imprint, 2016',
                due_back=return_date,
                borrower=the_borrower,
                status=status,
            )

    def test_redirect_if_not_logged_in(self):
        response = self.client.get(reverse('my-borrowed'))
        self.assertRedirects(response, '/accounts/login/?next=/catalog/mybooks/')

    def test_logged_in_uses_correct_template(self):
        login = self.client.login(username='testuser1', password='1X<ISRUkw+tuK')
        response = self.client.get(reverse('my-borrowed'))

        # Check our user is logged in
        self.assertEqual(str(response.context['user']), 'testuser1')
        # Check that we got a response "success"
        self.assertEqual(response.status_code, 200)

        # Check we used correct template
        self.assertTemplateUsed(response, 'catalog/bookinstance_list_borrowed_user.html')
```

뷰가 로그인하지 않은 사용자를 로그인 화면으로 redirect 하는 것을 확인하기 위해 우리는 `assertRedirects`를 사용함을 `test_redirect_if_not_logged_in()`에서 확인할 수 있습니다. 페이지가 로그인 한 사용자에게 보임을 확인하기 위해서 우리는 테스트 유저로 로그인을 한 후에 페이지에 접근해서 응답의 상태코드가 200번임을 확인합니다 (성공을 의미).

나머지 테스트 코드는 뷰가 현재 로그인 한 사용자가 대출한 책만을 반환하는지를 검증합니다. 아래의 코드를 위의 테스트 클래스 아래에 붙여 넣어 주세요.

```python
    def test_only_borrowed_books_in_list(self):
        login = self.client.login(username='testuser1', password='1X<ISRUkw+tuK')
        response = self.client.get(reverse('my-borrowed'))

        # Check our user is logged in
        self.assertEqual(str(response.context['user']), 'testuser1')
        # Check that we got a response "success"
        self.assertEqual(response.status_code, 200)

        # Check that initially we don't have any books in list (none on loan)
        self.assertTrue('bookinstance_list' in response.context)
        self.assertEqual(len(response.context['bookinstance_list']), 0)

        # Now change all books to be on loan
        books = BookInstance.objects.all()[:10]

        for book in books:
            book.status = 'o'
            book.save()

        # Check that now we have borrowed books in the list
        response = self.client.get(reverse('my-borrowed'))
        # Check our user is logged in
        self.assertEqual(str(response.context['user']), 'testuser1')
        # Check that we got a response "success"
        self.assertEqual(response.status_code, 200)

        self.assertTrue('bookinstance_list' in response.context)

        # Confirm all books belong to testuser1 and are on loan
        for bookitem in response.context['bookinstance_list']:
            self.assertEqual(response.context['user'], bookitem.borrower)
            self.assertEqual('o', bookitem.status)

    def test_pages_ordered_by_due_date(self):
        # Change all books to be on loan
        for book in BookInstance.objects.all():
            book.status='o'
            book.save()

        login = self.client.login(username='testuser1', password='1X<ISRUkw+tuK')
        response = self.client.get(reverse('my-borrowed'))

        # Check our user is logged in
        self.assertEqual(str(response.context['user']), 'testuser1')
        # Check that we got a response "success"
        self.assertEqual(response.status_code, 200)

        # Confirm that of the items, only 10 are displayed due to pagination.
        self.assertEqual(len(response.context['bookinstance_list']), 10)

        last_date = 0
        for book in response.context['bookinstance_list']:
            if last_date == 0:
                last_date = book.due_back
            else:
                self.assertTrue(last_date <= book.due_back)
                last_date = book.due_back
```

당신은 pagination 역시 테스트 할 수 있습니다. 한 번 해보셨으면 좋겠습니다 :)

#### Form을 이용하는 view를 테스트하기

Form을 이용하는 뷰를 테스트하는 것은 앞선 경우에 비해 살짝 더 복잡합니다. 왜냐면 우리는 코드의 더 다양한 부분을 - 첫 화면, 데이터 유효성 검증이 실패한 화면, 데이터 유효성 검증이 성공한 화면 - 모두를 테스트해야 하기 때문입니다. 다행히  우리는 데이터를 보여주기만 하는 뷰를 테스트 할 때 사용했던 클라이언트를 거의 그대로 사용할 수 있습니다.

대출을 갱신하기 위한 뷰를 테스트하기 위한 코드를 짜봅시다 (`renew_book_librarian()`):

```python
from catalog.forms import RenewBookForm

@permission_required('catalog.can_mark_returned')
def renew_book_librarian(request, pk):
    """View function for renewing a specific BookInstance by librarian."""
    book_instance = get_object_or_404(BookInstance, pk=pk)

    # If this is a POST request then process the Form data
    if request.method == 'POST':

        # Create a form instance and populate it with data from the request (binding):
        book_renewal_form = RenewBookForm(request.POST)

        # Check if the form is valid:
        if form.is_valid():
            # process the data in form.cleaned_data as required (here we just write it to the model due_back field)
            book_instance.due_back = form.cleaned_data['renewal_date']
            book_instance.save()

            # redirect to a new URL:
            return HttpResponseRedirect(reverse('all-borrowed'))

    # If this is a GET (or any other method) create the default form
    else:
        proposed_renewal_date = datetime.date.today() + datetime.timedelta(weeks=3)
        book_renewal_form = RenewBookForm(initial={'renewal_date': proposed_renewal_date})

    context = {
        'book_renewal_form': book_renewal_form,
        'book_instance': book_instance,
    }

    return render(request, 'catalog/book_renew_librarian.html', context)
```

이제 우리는 `can_mark_returned` permission을 가진 사용자만이 view를 사용할 수 있는지, 그리고 그 사용자들이 가지고 있지 않은 `BookInstance` 을 수정하려고 시도하면 HTTP 404 에러 페이지로 리다이렉트 되는지 테스트해봐야 합니다. We should check that  the initial value of the form is seeded with a date three weeks in the  future, and that if validation succeeds we're redirected to the  "all-borrowed books" view. As part of checking the validation-fail tests we'll also check that our form is sending the appropriate error  messages.

Add the first part of the test class (shown below) to the bottom of **/catalog/tests/test_views.py**. This creates two users and two book instances, but only gives one user  the permission required to access the view. The code to grant  permissions during tests is shown in bold:

```python
import uuid

from django.contrib.auth.models import Permission # Required to grant the permission needed to set a book as returned.

class RenewBookInstancesViewTest(TestCase):
    def setUp(self):
        # Create a user
        test_user1 = User.objects.create_user(username='testuser1', password='1X<ISRUkw+tuK')
        test_user2 = User.objects.create_user(username='testuser2', password='2HJ1vRV0Z&3iD')

        test_user1.save()
        test_user2.save()

        permission = Permission.objects.get(name='Set book as returned')
        test_user2.user_permissions.add(permission)
        test_user2.save()

        # Create a book
        test_author = Author.objects.create(first_name='John', last_name='Smith')
        test_genre = Genre.objects.create(name='Fantasy')
        test_language = Language.objects.create(name='English')
        test_book = Book.objects.create(
            title='Book Title',
            summary='My book summary',
            isbn='ABCDEFG',
            author=test_author,
            language=test_language,
        )

        # Create genre as a post-step
        genre_objects_for_book = Genre.objects.all()
        test_book.genre.set(genre_objects_for_book) # Direct assignment of many-to-many types not allowed.
        test_book.save()

        # Create a BookInstance object for test_user1
        return_date = datetime.date.today() + datetime.timedelta(days=5)
        self.test_bookinstance1 = BookInstance.objects.create(
            book=test_book,
            imprint='Unlikely Imprint, 2016',
            due_back=return_date,
            borrower=test_user1,
            status='o',
        )

        # Create a BookInstance object for test_user2
        return_date = datetime.date.today() + datetime.timedelta(days=5)
        self.test_bookinstance2 = BookInstance.objects.create(
            book=test_book,
            imprint='Unlikely Imprint, 2016',
            due_back=return_date,
            borrower=test_user2,
            status='o',
        )
```

Add the following tests to the bottom of the test class. These check that only users with the correct permissions (*testuser2*) can access the view. We check all the cases: when the user is not  logged in, when a user is logged in but does not have the correct  permissions, when the user has permissions but is not the borrower  (should succeed), and what happens when they try to access a `BookInstance` that doesn't exist. We also check that the correct template is used.

```python
   def test_redirect_if_not_logged_in(self):
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}))
        # Manually check redirect (Can't use assertRedirect, because the redirect URL is unpredictable)
        self.assertEqual(response.status_code, 302)
        self.assertTrue(response.url.startswith('/accounts/login/'))

    def test_redirect_if_logged_in_but_not_correct_permission(self):
        login = self.client.login(username='testuser1', password='1X<ISRUkw+tuK')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}))

        # Manually check redirect (Can't use assertRedirect, because the redirect URL is unpredictable)
        self.assertEqual(response.status_code, 302)
        self.assertTrue(response.url.startswith('/accounts/login/'))

    def test_logged_in_with_permission_borrowed_book(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance2.pk}))

        # Check that it lets us login - this is our book and we have the right permissions.
        self.assertEqual(response.status_code, 200)

    def test_logged_in_with_permission_another_users_borrowed_book(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}))

        # Check that it lets us login. We're a librarian, so we can view any users book
        self.assertEqual(response.status_code, 200)

    def test_HTTP404_for_invalid_book_if_logged_in(self):
        # unlikely UID to match our bookinstance!
        test_uid = uuid.uuid4()
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk':test_uid}))
        self.assertEqual(response.status_code, 404)

    def test_uses_correct_template(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}))
        self.assertEqual(response.status_code, 200)

        # Check we used correct template
        self.assertTemplateUsed(response, 'catalog/book_renew_librarian.html')
```

아래에 보이는 것처럼 새로운 테스트 방법을 추가해봅시다. 이것은 form의 초기 날짜가  3주 후인지를 확인합니다. 어떻게 우리가 form 필드의 첫 번째 값에 접근하는 것이 가능한지 참고해보세요. (굵게 표시된  부분에서 확인할 수 있습니다.)

```python
    def test_form_renewal_date_initially_has_date_three_weeks_in_future(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        response = self.client.get(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}))
        self.assertEqual(response.status_code, 200)

        date_3_weeks_in_future = datetime.date.today() + datetime.timedelta(weeks=3)
        self.assertEqual(response.context['form'].initial['renewal_date'], date_3_weeks_in_future)
```

만약 당신이 class `RenewBookForm(forms.Form)` 대신에 class `RenewBookModelForm(forms.ModelForm)` 을 사용한다면, form의 필드명은 **'renewal_date'** 대신 **'due_back'**으로 나타날 것입니다.

The next test (add this to the class too) checks that the view  redirects to a list of all borrowed books if renewal succeeds. What  differs here is that for the first time we show how you can `POST` data using the client. The post *data* is the second argument to the post function, and is specified as a dictionary of key/values.

```python
    def test_redirects_to_all_borrowed_book_list_on_success(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        valid_date_in_future = datetime.date.today() + datetime.timedelta(weeks=2)
        response = self.client.post(reverse('renew-book-librarian', kwargs={'pk':self.test_bookinstance1.pk,}), {'renewal_date':valid_date_in_future})
        self.assertRedirects(response, reverse('all-borrowed'))
```

The *all-borrowed* view was added as a *challenge*, and your code may instead redirect to the home page '/'. If so, modify the  last two lines of the test code to be like the code below. The `follow=True` in the request ensures that the request returns the final destination URL (hence checking `/catalog/` rather than `/`).

```python
 response = self.client.post(reverse('renew-book-librarian', kwargs={'pk':self.test_bookinstance1.pk,}), {'renewal_date':valid_date_in_future}, follow=True )
 self.assertRedirects(response, '/catalog/')
```

Copy the last two functions into the class, as seen below. These again test `POST` requests, but in this case with invalid renewal dates. We use `assertFormError() `to verify that the error messages are as expected.

```python
    def test_form_invalid_renewal_date_past(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        date_in_past = datetime.date.today() - datetime.timedelta(weeks=1)
        response = self.client.post(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}), {'renewal_date': date_in_past})
        self.assertEqual(response.status_code, 200)
        self.assertFormError(response, 'form', 'renewal_date', 'Invalid date - renewal in past')

    def test_form_invalid_renewal_date_future(self):
        login = self.client.login(username='testuser2', password='2HJ1vRV0Z&3iD')
        invalid_date_in_future = datetime.date.today() + datetime.timedelta(weeks=5)
        response = self.client.post(reverse('renew-book-librarian', kwargs={'pk': self.test_bookinstance1.pk}), {'renewal_date': invalid_date_in_future})
        self.assertEqual(response.status_code, 200)
        self.assertFormError(response, 'form', 'renewal_date', 'Invalid date - renewal more than 4 weeks ahead')
```

The same sorts of techniques can be used to test the other view.

### [Templates](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#templates)

Django provides test APIs to check that the correct template is being called  by your views, and to allow you to verify that the correct information  is being sent. There is however no specific API support for testing in  Django that your HTML output is rendered as expected.

## [Other recommended test tools](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#other_recommended_test_tools)

Django's test framework can help you write effective unit and integration tests — we've only scratched the surface of what the underlying **unittest** framework can do, let alone Django's additions (for example, check out how you can use [unittest.mock](https://docs.python.org/3.5/library/unittest.mock-examples.html) to patch third party libraries so you can more thoroughly test your own code).

While there are numerous other test tools that you can use, we'll just highlight two:

- [Coverage](http://coverage.readthedocs.io/en/latest/): This Python tool reports on how much of your code is actually executed  by your tests. It is particularly useful when you're getting started,  and you are trying to work out exactly what you should test.
- [Selenium](https://developer.mozilla.org/en-US/docs/Learn/Tools_and_testing/Cross_browser_testing/Your_own_automation_environment) is a framework to automate testing in a real browser. It allows you to  simulate a real user interacting with the site, and provides a great  framework for system testing your site (the next step up from  integration testing).

## [Challenge yourself](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#challenge_yourself)

There are a lot more models and views we can test. As a simple task, try to create a test case for the `AuthorCreate` view.

```python
class AuthorCreate(PermissionRequiredMixin, CreateView):
    model = Author
    fields = '__all__'
    initial = {'date_of_death':'12/10/2016'}
    permission_required = 'catalog.can_mark_returned'
```

Remember that you need to check anything that you specify or that is part of the design. This will include who has access, the initial date, the  template used, and where the view redirects on success.

## [Summary](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Testing#summary)

Writing test code is neither fun nor glamorous, and is consequently often left  to last (or not at all) when creating a website. It is however an  essential part of making sure that your code is safe to release after  making changes, and cost-effective to maintain.

In this tutorial we've shown you how to write and run tests for your  models, forms, and views. Most importantly we've provided a brief  summary of what you should test, which is often the hardest thing to  work out when you're getting started. There is a lot more to know, but  even with what you've learned already you should be able to create  effective unit tests for your websites.

The next and final tutorial shows how you can deploy your wonderful (and fully tested!) Django website.



# Django 튜토리얼 파트 11: Django 웹사이트 공개하기

link: https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment

당신은 이제 놀라운 [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 웹사이트를 (테스트도 마치고) 만들었으니, 도서관 스태프나 회원들이 인터넷을 통해 이용할 수 있도록 공개된 웹 서버에 설치하길 원할  것이다. 이번 장에서는 웹사이트를 배포할수 있는 호스트를 살펴보는 방법에 대한 개요와 사이트를 실제 운운영하기위해 필요한 것들에  대해 설명한다.

| 사전학습: | 아래 파트를 포함하여 앞선 모든 튜토리얼 파트의 학습을 완료할 것. [Django 튜토리얼 파트 10: Django 웹 어플리케이션 테스트하기](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Testing) |
| --------- | ------------------------------------------------------------ |
| 학습목표: | Django 앱을 공개 운영할 수 있는 네트워크상의 장소와 방법을 배우기. |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#개요)

일단 사이트 개발이 완료되면( 혹은 공개 테스트를 시작할 정도로 "충분히" 완료되었다면 ) 개인 컴퓨터 환경보다는 좀더 공개되고 접근성이 있는 네트워크상의 장소가 필요할 것이다.

이제까지는, Django 개발용 웹 서버를 사용하여 사이트를 로컬 브라우저/네트워크 범위 내에서 공개하고, 암호를 포함한  개인정보및 디버깅정보가 노출되는 (보안이 부실한) 개발용 환경 설정으로 웹사이트를 실행하면서, 개발환경에서만 작업을  해왔다. 외부에서 웹사이트를 운영하기 전에 해야할 일은 다음과 같다:

- 프로젝트 설정에서 몇가지를 고쳐야 한다.
- Django 앱을 호스팅할 환경을 선택해야 한다.
- 정적 파일들(static files)을 호스팅할 환경을 선택해야 한다.
- 웹 사이트를 서비스할 공개단계 레벨의 기반환경을 구축한다.

이 튜토리얼은 호스팅사이트를 선택하기위한 몇가지 지침과 Django 앱을 공개하는데 대비하기 위해 필요한 사항에 대한 간단한 개요및 LocalLibrary 웹사이트를 [Heroku](https://www.heroku.com/) 클라우드 호스팅 서비스 위에서 운영되도록 설치하는 방법에 대해 동작하는 예제를 제공한다.

## [운영환경 (Production envrionment) 이란?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#운영환경_(production_envrionment)_이란)

운영환경은 외부공개를 위한 웹사이트를 운영하는 서버 컴퓨터로 부터 제공되는 환경이다. 운영환경은 다음 요소로 구성된다 :

- 웹사이트가 실행되는 컴퓨터 하드웨어.
- Linux, Windows와 같은 운영체제.
- 웹 사이트를 작성한 프로그래밍 언어 구동 환경(runtime)과 프레임웍 라이브러리.
- 콘텐츠및 페이지를 서비스 하기위한 Nginx, Apache와 같은 웹 서버.
- Django 웹사이트와 웹서버간에 "동적인" request 를 전달하기위한 어플리케이션 서버.
- 웹사이트의 정보를 저장하는 데이타베이스.

**주목할점**: 운영환경의 설정에 따라 역방향 프록시(reverse proxy)나 로드 밸런서(load balancer)등이 추가될 수도 있다.

서버 컴퓨터는 빠른 인터넷으로 연결되어 당신의 사무실내에 위치할 수도 있지만, 보통 "클라우드 상에서" 사용하는것이 훨씬 더 흔한 방법이다. 이말은 실제로는 당신의 코드가 호스팅 업체의 데이터센터에 위치한 어떤 원격 컴퓨터에서(혹은 아마도 "가상환경의" 컴퓨터상에서) 실행된다는 것을 의미한다. 원격지 서버는 대개는 어느 정도 검증된 수준의 컴퓨터 자원(예를들면, CPU, 램,  저장 메모리 공간등)과 가격대별 인터넷 연결을 제공한다.

이런 종류의 원격 접속가능한 컴퓨터/네트워크 하드웨어를 IaaS ( *Infrastructure as a Service) 라고 줄여 부른다*. 많은 IaaS 업체들은 미리 설치된 특정 OS 환경에 대한 여러가지 옵션을 제공하는데 당신은 그 위에 운영환경에 필요한 것들을  설치해야 한다. 또 다른 업체는 장고와 웹서버등을 포함하여 완전히 갖추어진 환경을 선택할수 있도록 옵션을 제공하기도 한다.

**주목할점:** 미리 구성된 환경에서는 환경설정에 대한 부담을 덜 수 있어서 웹사이트 구성을 매우  쉽게할 수 있지만, 익숙하지 않은 서버환경이나 다른 콤포넌트 때문에, 혹은 구형 OS 버전 때문에 선택가능한 구성수단이 줄어들  수도 있다. 많은 경우에, 당신이 원하는 결과를 얻기 위해, 당신이 직접 필요한 콤포넌트를 설치하는 것이 낫다. 그러면 시스템  업그레이드가 필요한 경우에, 어떻게 시작해야 하는지 알아챌 수 있을 것이다 !

다른 호스팅 업체들은 Django를 플랫폼의 일부분으로 지원하는데 이런 업체를 PaaS( *Platform as a Service)로 줄여 부른다*. 이런 종류의 호스팅에서는 호스트 플랫폼이 (어플리케이션의 규모의 변화에 따라 조정해야하는 거의 모든 것을 포함하여) 대신  관리해주므로 운영환경(웹 서버, 어플리케이션 서버, 로드 밸런서등)에 대해 거의 고민할 필요가 없다. 이 경우, 다른 서버  기반환경에 신경쓸 필요없이, 웹 어플리케이션에만 집중할수 있기 때문에 웹사이트 배포가 엄청 쉽다.

어떤 개발자들은 PaaS에 비해 좀더 높은 자유도를 가진 IaaS를 선택하는 반면, 다른 개발자들은 관리부담이 덜하고  웹사이트 규모를 쉽게 조정할수 있는 PaaS를 선택할 것이다. 당신은 이제 막 개발을 시작했기에, PaaS에 웹사이트를 설정하는  것이 더 쉬울 것이다. 그리고 그것이 이 튜토리얼에서 우리가 공부할 내용이다.

**한가지 팁:** Python/Django 친화적인 호스팅 업체를 선정했다면, 웹서버,어플리케이션  서버, 역방향 프록시( 혹시 PaaS 업체를 선정했다면 별로 신경쓸 필요없는 내용임)의 여러가지 설정을 이용한 Django  웹사이트를 구성하는 방법에 대한 설명이 제공될 것이다. 예를 들면, 이곳 ( [Digital Ocean Django community docs](https://www.digitalocean.com/community/tutorials?q=django) ) 에는 다양한 설정에 대한 단계적인 가이드가 있다.

## [호스팅 업체 선정하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#호스팅_업체_선정하기)

Django에 대한 지원이 활발하게 이루어지거나 Django가 잘 동작한다고 알려진 호스팅 업체는 약 100여곳이 있다. ( 꽤 광범위한 목록을 [Djangofriendly hosts](http://djangofriendly.com/hosts/) 에서 찾아볼 수 있다.) 이 업체들은 서로 다른 타입(IaaS, PaaS)이거나 서로 다른 가격대에 여러가지 수준의 컴퓨팅/네트워크 자원을 제공한다.

호스팅 업체를 선정할 때 고려해야할 몇가지는 다음과 같다:

- 당신의 사이트가 얼마나 바쁘게 돌아갈 것인지와 이 요구조건을 감당할수 있는 데이터와 컴퓨팅 자원의 비용 수준.
- 수평적인(같은 머신을 더 많이 사용하기) 혹은 수직적인(좀더 고성능의 머신을 사용하기) 사이트 규모조정 수준과 이 작업에 필요한 비용.
- 공급자의 데이터 센터가 위치한 장소와 그에 따른 가장 빠르게 접속할 수 있는 장소.
- 호스트의 역대 가동시간및 가동중지시간 상의 성능.
- 사이트 관리용으로 제공되는 도구 - 가 사용하기 쉬운지와 안전한지 ( 예를들면 SFTP vs FTP )
- 당신의 서버를 모니터링 하기위하 내장 프레임워크.
- 알려진 제한사항들. 어떤 호스트 업체는 의도적 특정 서비스(예를 들면 email)를 제한한다. 또 다른 업체는 특정 가격대에 일정 시간의 "가동시간"이나 일정량의 저장공간만을 제공한다.
- 추가적인 혜택들. 어떤 업체들은 다른곳에서는 유료로 지원되는 도메인 네임과 SSL인증에 대한 지원을 무료로 제공한다. 
- 당신이 사용하던 "무료"계정이 시간초과로 중지되거나 좀더 비싼 계정으로 이전하는데 비용들거나 하게된다는 것은 처음에 선택한 호스팅 서비스와 다른 선택을 했다면 웹사이트가 좀더 성공적일 수도 있었다는 것을 의미한다! 

당신이 개발을 시작했다면 좋은 소식은 "평가용", "개발자용"혹은 "취미개발용" 컴퓨터 환경을 "무료"로 제공하는 업체가 꽤 있다는 것이다. 이들은 항상 자원을 아주 제한적으로만 사용할 수 있고, 처음 신규 가입 기간이 지나 중지되지 않는지 주의를  기울여야 한다. 하지만, 트래픽이 낮은 사이트를 실제 운영환경에서 테스트 하기에 좋으며, 사이트가 좀더 바빠질 경우 더 많은  자원을 제공하는 유료환경으로 쉽게 이전할 수 있다.이런 종류의 환경으로 인기있는 곳은 [Heroku](https://www.heroku.com/), [Python Anywhere](https://www.pythonanywhere.com/), [Amazon Web Services](https://docs.aws.amazon.com/awsaccountbilling/latest/aboutv2/billing-free-tier.html), [Microsoft Azure](https://azure.microsoft.com/en-us/pricing/details/app-service/) 등등이다.

많은 업체가 좀더 쓸만한 수준의 컴퓨팅 파워와 제한사항을 완화한 "기본"계정을 가지고 있다. [Digital Ocean](https://www.digitalocean.com/) 과 [Python Anywhere](https://www.pythonanywhere.com/) 는 상대적으로 비용이 낮은( 1달에 5~10 USD 가격의) 기본 계정을 제공하는 인기있는 호스팅 업체의 대표적인 예이다.

**주목할점:** 사용가격이 유일한 선택기준이 아니라는 것을 명심하라. 당신의 웹사이트가 성공적이라면 사이트의 확장성이 가장 중요한 기준이 될것이다.

## [웹사이트에서 공개(publish)준비 하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#웹사이트에서_공개(publish)준비_하기)

django-admin과 manage.py 도구로 생성된 [Django 뼈대 웹사이트](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/skeleton_website)는 개발환경에 최적화 되어 설정되었다. 개발환경이 아닌 운영환경에서는 ( **settings.py**에 설정되어 있는 ) Django 프로젝트 설정의 많은 부분이, 보안상 혹은 성능상 이유로, 변경되어야 한다.

**한가지 팁:** 운영환경용으로 별도의 **settings.py** 파일을 유지하고 민감한 설정들은 별도의 파일이나 시스템의 환경변수에 저장하는  것이 일반적이다. 소스코드의 다른 부분은 공개된 저장소에 보관하더라도, 이 파일은 격리되어 보호되어야 한다. 

반드시 체크되어야 할 가장 중요한 설정은 다음과 같다 :

- `DEBUG`. 이 설정은 운영환경에서 `False` 로 설정되어야 한다 (`DEBUG = False`). 이 설정은 민감하고 / 보호가 필요한 디버그 정보나 변수정보가 외부로 보여지지 않도록 막는다.

- ```python
  SECRET_KEY
  ```

  . CRSF 보안등을 위해 사용되는 큰 숫자의 랜덤 값이다. 운영환경에서  사용되는 key 값이 형상관리툴에 등록되지 않도록 하거나 운영 서버 밖에서 접근할 수 없도록 관리하는 것이 중요하다. Django 문서에서는 이 값을 환경 변수에서 로딩하거나 serve-only 파일에서 읽어오도록 제안하고 있다.  

  ```python
  # Read SECRET_KEY from an environment variable
  import os
  SECRET_KEY = os.environ['SECRET_KEY']
  
  #OR
  
  #Read secret key from a file
  with open('/etc/secret_key.txt') as f:
      SECRET_KEY = f.read().strip()
  ```

 `SECRET_KEY` *와* `DEBUG` *변수를, 이 변수들이 정의되어 있다면, 시스템의 환경 변수에서 읽어오도록 LocalLibrary* 어플리케이션을 수정하자. 정의되어 있지 않다면 설정 파일의 디폴트 값을 사용하도록 한다.

**/locallibrary/settings.py** 파일을 열고, 기존의 `SECRET_KEY` 설정을 비활성화 하고 아래 코드에서 **bold**체로 보이는 부분을 추가한다. 개발 과정중에는 보통 key와 관련하여 환경변수가 설정되지 않으므로 초기값이 사용되고 있을것이다. (  키가 노출되면 당신이 그 키를 운영환경에 사용하지는 않을것이므로 여기서 어떤키를 사용하는지는 중요하지 않다).

```python
# SECURITY WARNING: keep the secret key used in production secret!
# SECRET_KEY = 'cg#p$g+j9tax!#a3cup@1$8obt2_+&k3q+pmu)5%asj6yjpkag'
import os
SECRET_KEY = os.environ.get('DJANGO_SECRET_KEY', 'cg#p$g+j9tax!#a3cup@1$8obt2_+&k3q+pmu)5%asj6yjpkag')
```

그리고나서 기존의 `DEBUG` 설정을 주석처리 하고 신규 라인을 아래와 같이 추가 하라.

```python
# SECURITY WARNING: don't run with debug turned on in production!
# DEBUG = True
DEBUG = bool( os.environ.get('DJANGO_DEBUG', True) )
```

`DEBUG` 의 초기값은 `True` 이지만, `DJANGO_DEBUG` 환경변수가 비어있는 문자열로 설정 되면 (즉, `DJANGO_DEBUG=''` 와 같이) `False` 로 설정될 것이다.

**주목할 점**: `DJANGO_DEBUG` 환경변수를 "값을 가진 문자열"이나 "빈 문자열" 로 설정하기 보다, 그냥 `True` 나 `False` 로 (각각) 직접 설정할 수 있다면 이런 방식이 좀더 직관적으로 보일것이다. 하지만 불행히도 환경변수는 파이썬 문자열로 저장되며, `False` 로 평가받을수 있는 문자열은 "빈 문자열"이 유일하다.

역자주: os.environ.get의 사용법에 대해서는 이 [링크](https://bugs.python.org/msg277165)를 참조 하라

변경해야할 설정의 전체 체크리스트는 [배포 체크리스트](https://docs.djangoproject.com/en/2.0/howto/deployment/checklist/) (장고 문서) 로 제공된다. 아래 터미널 명령으로도 몇몇 목록을 뽑을 수 있다:

```
python3 manage.py check --deploy
```

## [예제: LocalLibrary를 Heroku에 설치하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#예제_locallibrary를_heroku에_설치하기)

이번 섹션에서는 [Heroku PaaS 클라우드](http://heroku.com) 에 LocalLibrary를 설치하는 방법에 대한 실제적인 예제를 제공한다.

### [왜 Heroku 인가?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#왜_heroku_인가)

Heroku는 현재까지 가장 오래 운영된 서비스들중 하나이며, 인기있는 클라우드기반 PaaS 서비스이다. 원래는 Ruby 앱들만 지원했지만,  현재는 많은 프로그래밍 환경의 앱들을 지원할수 있으며, 여기에는 Django 또한 포함된다!

우리는 다음과 같은 이유로 Heroku 를 사용하기로 결정했다:

- Heroku는 정말 돈 낼 필요가 없는 [무료 단계 (free tier)](https://www.heroku.com/pricing) 를 지원한다. (물론 약간의 제약이 있긴하다).
- Heroku는 PaaS 개념의 서비스로 많은 웹 기반환경의 관리를 제공한다. 서버관리, 로드 밸런싱, 역방향 프록시등  여러가지 웹 기반환경들을 Heroku가 내부적으로 모두 제공하므로 이에 대한 걱정을 덜고 쉽게 개발을 시작할수 있다.
- 제약이 있긴하지만, 이 제약들은 우리가 진행하려는 어플리케이션 개발에는 영향이 없을 것이다. 예를 들면 다음과 같다:  
  - 무료 단계의 Heroku는 활성주기가 짧은 저장공간을 제공하므로 유저가 업로드한 파일을 Heroku 자체에 안전하게 저장할 수는 없다.
  - 무료 단계에서는 30분동안 아무런 요청도 없다면 웹 앱은 비활성화 될 것이다. 이 후에 요청이 오면 응답하는데 몇 초정도 약간의 시간이 더 필요하게 될것이다. 
  - 무료 단계에서는 웹 사이트의 동작 가능 시간이 매월 특정시간 만큼으로 제한된다 ( 사이트가  "비활성(asleep)"상태인 경우의 시간은 제외된다). 이 제약은 사용빈도가 낮고/ 예제 확인용 사이트인 경우는 문제가 되지  않지만, 계속 활성화 상태가 필요한 앱의 경우에는 적합하지 않을것이다.
  - 다른 제약 사항은 [Limits](https://devcenter.heroku.com/articles/limits) (Heroku 문서) 에서 찾아볼 수 있다.
- 대부분의 경우 문제없이 사용할 수 있을것이고, 당신이 결국 Heroku에 만족하게 되었다면, 당신의 앱을 확장하는 것도 매우 쉽다.

Heroku로 이 예제를 호스팅 하는데는 부족함이 없겠지만, 당신이 개발하고자 하는 실제 웹사이트의 요구조건에는 부족할 수도 있다. Heroku는 설치하여 사용하고 확장하기는 쉽지만 대신 모든 요구조건을 충족할 정도로 유연하지는 않으며 일단 무료 단계를 벗어나면 당신의 요구조건을 만족시키기 위해 좀더 비싼 비용을 요구할 가능성이 잠재되어있다.

### [Heroku는 어떻게 동작하는가?](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#heroku는_어떻게_동작하는가)

Heroku는 Django 웹사이트를 한 개이상의 "[다이노(Dyno](https://devcenter.heroku.com/articles/dynos))"에서 실행한다. 다이노란 고립적이고, 가상화된 Unix 콘테이너이며 어플리케이션을 실행하는데 필요한 환경을 제공한다. 다이노는 완전히 고립적이며 *ephemeral* ( *다이노가 재시작될 때마다 깨끗이 비워지는 단기 수명의 )* 파일 시스템을 가지고 있다. 다이노간에 공유하는 유일한 항목은 어플리케이션 [설정 변수](https://devcenter.heroku.com/articles/config-vars) 이다. Heroku 는 웹 트래픽을 모든 "웹" 다이노들로 분배하기 위해 내부적으로 로드 밸런서를 사용한다. 다이노들간에 공유하는것이 없기때문에  Heroku는 단지 다이노를 좀더 추가하는 것으로 앱을 수평적으로 확장할 수있다. ( 사실은 당연히 추가적인 접속자를 받기위해  다이노 뿐만 아니라 데이타베이스도 확장할 필요가 있긴 하다).

파일 시스템이 단기 수명의 특성이 있어서, 어플리케이션에 필요한 서비스(즉, 데이타베이스, 큐, 캐싱 시스템, 저장공간,  이메일 서비스 등등) 를 직접 설치할 수는 없다. 대신에 Heroku 웹 어플리케이션들은 Heroku나 써드파티로 부터 제공되는 독립적인 "애드온"들로 부터의 지원 서비스를 이용한다. 일단 애드온이 웹 어플리케이션에 부착되면, 다이노는 어플리케이션 설정  변수에 포함된 정보를 사용하여 서비스에 접속한다. 

어플리케이션을 실행하기위해서, Heroku는 적절한 환경과 의존성을 셋업하고 어떻게 런칭되는지 이해할 필요가 있다. Django 앱에 대해서는 아래 정보를 몇개의 텍스트 파일로 제공한다.

- **runtime.txt**: 프로그래밍 언어와 사용 버전.
- **requirements.txt**: Django를 포함한 파이썬 관련 라이브러리 의존성.
- **Procfile**: 웹 어플리케이션을 구동하기 위해 실행되어야 하는 프로세스의 목록. 장고의 예를 들자면, Gunicorn 웹 어플리케이션 서버( .wsgi 스크립트와 함께) 가 될것이다. 
- **wsgi.py**: Heroku 환경에서 Django 어플리케이션을 호출 하기 위한 [WSGI](http://wsgi.readthedocs.io/en/latest/what.html) 설정.

개발자들은 Unix bash 스크립트와 매우 유사한, 특별한 클라이언트 앱/터미널로 Heroku와 통신한다. 이 도구는  git 저장소에 보관된 코드를 업로드 할수 있도록 지원하며, 실행중인 프로세스의 모니터링과, 로그를 보고 환경변수를 설정하는등  그외 많은 일을 할수 있도록 지원한다!

Heroku 상에서 어플리케이션을 실행하기 위해서는 Django 웹 어플리케이션을 git 저장소에 보관하고, 위에서 언급한 파일을 추가하며, 데이터베이스 애드온과 통합하고, 스태틱 파일을 다룰수 있도록 수정할 필요가 있다.

일단 Heroku 계정에서 준비할수 있는 모든것을 완료 했으니, Heroku 클라이언트를 다운로드 받아서 웹 사이트를 설치하라.

**주목할 점:** 아래 지시사항은 이 글을 쓸 당시의 Heroku로 작업하는 방법을 반영했다. Heroku 서비스의 절차가 상당히 많이 바뀐다면, 이글 대신 다음링크의 Heroku 문서를 참조하는 것이 좋다: [Django로 Heroku 시작하기](https://devcenter.heroku.com/articles/getting-started-with-python#introduction).

이것으로 시작을 하기위한 준비를 모두 마친다. ( 좀 더 포괄적인 이해를 위해서는 [Heroku의 동작 방식](https://devcenter.heroku.com/articles/how-heroku-works)(Heroku 문서) 가이드를 참고하라).

### [ Github에 애플리케이션 저장소(repository) 생성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#github에_애플리케이션_저장소(repository)_생성하기)

Heroku는 **git** 형상관리 시스템과 밀접하게 통합되어있는데, git을 이용하여 활성화된 시스템에 수정사항의 업로드및 동기화를 수행한다. git은  Heroku 클라우드 상에서 당신의 소스코드 저장소를 가리키도록 이름붙여진 신규 heroku "원격" 저장소를 추가함으로 이  작업을 수행한다. 개발기간동안 "master" 저장소에 변경사항을 저장하기 위에 git을 사용하게된다. 사이트를 배포할 때가  되면, Heroku 저장소에 수정사항을 동기화 한다.

**주목할 점:** 좋은 소프트웨어 개발 예제를 따라서 작성하는데 익숙하다면, 아마도 당신은 이미 git 이나 다른 SCM 도구를 사용하고 있을 것이다. 당신이 이미 git 저장소를 소유하고 있다면, 이 단계를 건너뛰어도 좋다.

git 으로 작업하는 수많은 방법이 있지만, [Github](https://github.com/)에 계정을 생성하여, 저장소를 생성하고, 로컬 컴퓨터와 동기화 하는것이 가장 쉬운 방법중 하나이다 :

1. 이곳( https://github.com/ )을 방문하여 계정을 생성하라.
2. 로그인 해서 꼭대기 툴바의 **+** 링크를 클릭하여 **New repository**를 선택하라.
3. 이 폼의 모든 필드에 기입하라. 그렇지 않으면 진행이 불가한 것은 아니지만, 모두 채울것을 강력 추천한다.  
   - 새로운 저장소 이름 (예시: *django_local_library*)과 설명 (예시: "Local Library website written in Django")을 입력하라.
   - "*Add .gitignore" 선택 목록에서 "***Python***"을 선택하라*.
   - "*Add a license" 선택 목록에서 선호하는 라이센스 유형을 선택하라*.
   - "**Initialize this repository with a README**." 체크박스에 체크하라
4. " **Create repository"** 버튼을 누른다
5. 신규 저장소 페이지에서 초록색의 "**Clone or download**" 버튼을 클릭한다.
6. 아래와 같은 형태의 URL 값을 다이얼로그 박스내의 텍스트 필드에서 복사한다.(예시: **https://github.com/\*<your_git_user_id>\*/django_local_library.git**).

이제 원격 저장소 ("repo")가 생성되었으니 로컬 컴퓨터에 복제(clone)하길 원할 것이다:

1. *git을 로컬 컴퓨터에 설치하라* (플랫폼별 버전은 [이곳](https://git-scm.com/downloads) 에서 찾을 수 있다).

2. 커맨드 프롬프트/터미널 을 열고 위에서 복사한 URL을 이용하여 저장소 내용을 복제(clone) 한다 :  

   ```shell
   git clone https://github.com/<your_git_user_id>/django_local_library.git
   ```

  이 명령은 현재 프롬프트의 위치에 저장소를 생성할 것이다.

새로운 저장소 위치로 이동한다.  

```shell
cd django_local_library.git
```

마지막 단계는 어플리케이션을 복사하여 git을 이용해 저장소에 파일을 추가하는 것이다 :

1. 이 폴더에 Django 어플리케이션을 복사해 넣는다. (locallibrary 폴더를 포함한 위치가 아니라 **manage.py** 와 그 하위 폴더와 같은 위치의 모든 파일에 대해 작업한다).

2. .gitignore

    파일을 열어서, 아래 코드를 맨 밑에 복사하고, 저장하라 ( 이 파일은 기본 설정에 의해 git에 저장되지 말아야할 파일을 구분하는데 사용된다).  

   ```cmd
   # Text backup files
   *.bak
   
   #Database
   *.sqlite3
   ```

3. 커맨드 프로프트/터미널을 열고 

   ```shell
   add
   ```

   명령으로 모든 파일을 git에 등록한다.  

   ```shell
   git add -A
   ```

status 명령을 사용하여 등록하고자 하는 파일이 맞는지 확인한다 ( 당신은 소스파일만 등록 하길 원하고 바이너리나 임시 파일은 원치 않을 것이다). 명령을 실행하면 아래와 유사하게 나온다.  

```shell
> git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   .gitignore
        new file:   catalog/__init__.py
        ...
        new file:   catalog/migrations/0001_initial.py
        ...
        new file:   templates/registration/password_reset_form.html
```

위의 결과에 만족했다면, commit 명령으로 파일의 로컬 저장소 등록을 확정한다:  

```shell
git commit -m "First version of application moved into github"
```

1. 다음 명령을 이용하여 Github 웹사이트와 로컬 저장소를 동기화 한다:  

   ```shell
   git push origin master
   ```

이런 작업이 완료된 후, repo를 생성한 Github 페이지로 다시 가서, 페이지를 새로 로딩하여, 전체 어플리케이션이  모두 업로드된것인지 확인할 수 있어야 한다. 이후로 파일 변경 사항이 발생하면 add/commit/push 순서로 명령을 사용하여 저장소를 업데이트 할 수 있다.

**한가지 팁:** 이쯤에서 당신의 "변형 없이 순수한([vanilla](https://en.wikipedia.org/wiki/Vanilla_software))" 프로젝트를 백업하는것이 좋다. - 이어지는 섹션에서 해볼 작업들은 어떤 플랫폼(혹은 개발작업) 에서는 유용하지만 다른 곳에서는 그렇지 않을 수 있기 때문이다.

백업을 하는 가장 좋은 방법은 git을 사용하여 관리하는 것이다. git을 사용하면, 특정 구 버전으로 갈수 있을 뿐만  아니라, 이것을 운영관련 변경사항으로 부터 분리된 "브랜치(branch)"로 유지보수할 수있고, 운영 브랜치와 개발 브랜치간에  좋은 부분만 선별하여 적용할 수 있다. [Git 공부하기](https://help.github.com/articles/good-resources-for-learning-git-and-github/) 는 수고를 들여 배울가치가 있지만 이 글의 주제를 벗어난다.

백업을 하는 가장 쉬운방법은 단순히 파일을 다른 위치에 복제하는 것이다. 당신의 git 관련 지식에 맞춰, 어떤 방법이든 사용하도록 하자 !

### [Heroku에 맞춰 앱을 수정하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#heroku에_맞춰_앱을_수정하기)

이번 섹션에서는 LocalLibray 어플리케이션이 Heroku상에서 작동할 수 있도록 수정할 필요가 있는 변경사항에 대해 설명한다. Heroku 문서인 [Django로 Heroku 시작해보기](https://devcenter.heroku.com/articles/getting-started-with-python#introduction) 에서 나온 지시사항들은 당신이 로컬 개발 환경의 실행 또한 Heroku 클라이언트를 사용할 것으로 가정하고 있지만, 우리가 여기서 제시할 변경사항은 기존의 Django 개발 서버및 이제껏 배워온 작업방식과 호환될 것이다.

#### Procfile 작성하기

어플리케이션의 프로세스 타입과 엔트리 포인트를 선언하기 위해 GiHub 저장소의 root 폴더에 `Procfile` 파일을 (확장자 없이 ) 생성한다. 아래 문장을 해당 파일에 작성한다:

```shell
web: gunicorn locallibrary.wsgi --log-file -
```

"`web:`" 구문은 Heroku에게 이것이 웹 다이노이며 HTTP 트래픽을 받을수 있다는 것을 알려준다. 이 다이노에서 시작할 프로세스는 *gunicorn 인데 Heroku가 추천하는 인기있는 웹 어플리케이션 서버이다.* `locallibrary.wsgi` *( 어플리케이션 뼈대로 생성된:* **/locallibrary/wsgi.py**) *모듈의 설정 정보를 이용하여* Gunicorn을 구동시킨다.

#### Gunicorn 설치하기

[Gunicorn](http://gunicorn.org/) 은 Django와 함께 사용되는 용도로 Heroku에서 추천되는 HTTP server 이다 (바로 위의 Procfile 에서  미리 본 바와 같다). 하나의 다이노에서 여러개의 Python 동시 프로세스를 실행할 수 있는 WSGI 어플리케이션을 위한 순수  Python으로 작성된 HTTP server 다. ( 추가 정보를 얻으려면 [Gunicorn으로 Python 어플리케이션 배포하기](https://devcenter.heroku.com/articles/python-gunicorn) ( Heroku 문서) 참고하라).

개발기간중에는 LocalLibrary 어플리케이션을 서비스하기 위해 Gunicorn을 필요로 하진 않겠지만, Heroku에서 원격 서버를 셋업하기 위한 [요구조건](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment$edit#requirements) 의 일부이므로 Gunicorn을 설치할 것이다. 

아래와 같이 커맨드라인에서 pip를 이용하여 *Gunicorn* 설치한다 (pip는 [개발 환경 구축하기](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/development_environment) 단계에서 설치했다):

```shell
pip3 install gunicorn
```

#### Database 설정하기

디폴트로 설정되었던 SQLite 데이타베이스는 Heroku에서는 사용할 수 없다. 이유는 SQLite가 파일 베이스로  동작하는 시스템인데, Heroku는 단기-수명(ephemeral) 파일 시스템을 사용하므로 어플리케이션이 재시작되면 파일이  삭제되기 때문이다 ( 보통 하루에 한 번, 어플리케이션이나 그에 딸린 설정 변수가 변경되면 재시작 된다) . 

이런 상황에 대처하는 Heroku의 메커니즘은 [database 애드온](https://elements.heroku.com/addons#data-stores)을 사용하고 애드온에 의해 설정되는 환경 [설정 변수](https://devcenter.heroku.com/articles/config-vars)로 부터의 정보를 이용하여 웹 어플리케이션을 설정하는 것이다. 많은 데이타베이스 옵션이 있지만, Heroku postgres 데이터베이스의 [Hobby tier](https://devcenter.heroku.com/articles/heroku-postgres-plans#plan-tiers)를 사용할것인데, 이것은 무료이고, Django가 지원하며, 무료 hobby dyno plan tier를 사용할 때 신규 Heroku 앱에 자동으로 추가된다.

데이터베이스 연결 정보는 `DATABASE_URL` 라는 설졍변수를 사용해 웹 다이노에 제공된다. Heroku는 , 이 정보를 Django에 하드 코딩 해넣기 보다는, 개발자들이 [dj-database-url](https://warehouse.python.org/project/dj-database-url/) 패키지를 사용하여 `DATABASE_URL` 환경 변수를 분석하여 자동적으로 Django가 원하는 설정 형식으로 변환하는 것을 추천한다. *dj-database-url* 패키지를 설치하는것에 외에도, Django에서 Postgres 데이터베이스로 작업하기 위해서는 [psycopg2](http://initd.org/psycopg/) 도 설치해야 한다.

##### dj-database-url (환경 변수를 통한 Django 데이터베이스 설정 ) 설치하기

Heroku에서 원격 서버에 설치하기 위한  [요구조건](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deploymentt#requirements)의 일부가 되었으니, *dj-database-url* 를 로컬에 설치한다:

```shell
$ pip3 install dj-database-url
```

##### settings.py 수정하기

**/locallibrary/settings.py** 를 열고 아래 설정코드를 파일의 맨 밑에 복사해 넣는다:

```python
# Heroku: Update database configuration from $DATABASE_URL.
import dj_database_url
db_from_env = dj_database_url.config(conn_max_age=500)
DATABASES['default'].update(db_from_env)
```

**주목할 점:**

- `DATABASE_URL` 환경변수가 개발용 컴퓨터에는 없을것이므로 개발단계에서는 계속 SQLite를 사용한다.
-  `conn_max_age=500` 설정을 하면 연결상태가 지속될 수 있는데, 매번 요청 주기에 연결을 새로 하는것보다 이렇게 하는 것이 훨신 효율적이다. 하지만 이것은 옵션이며 불 필요하면 제거해도 된다.

##### psycopg2 (Python Postgres 데이터베이스 지원용) 설치하기

Django에서 Postgres 데이터베이스로 작업하기 위해서는 *psycopg2* 가 필요하므로 Heroku에 원격 서버를 생성하기 위해서는( 아래 요구조건 섹션에 논의된 바와 같이) [requirements.txt](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#requirements) 파일에 이 항목을 추가할 필요가 있다.

환경 변수가 로컬 환경에서 존재하지 않기 때문에, 로컬에서 Django는 디폴트로 SQLite를 사용할 것이다. 당신이  Postgres로 완전히 전환해서 개발과 운영 모두 Heroku 무료 단계 데이터베이스를 사용하길 원한다면 그렇게 할 수 있다.  예를 들면, Linux 기반 시스템에 psycopg2와 관련 모듈을 설치하려면, 아래 bash/터미널 명령을 사용하면 된다:

```shell
sudo apt-get install python-pip python-dev libpq-dev postgresql postgresql-contrib
pip3 install psycopg2
```

다른 플랫폼에 대한 설치방법은 [이곳 psycopg2 웹사이트](http://initd.org/psycopg/docs/install.html) 에서 찾아볼 수 있다.

하지만, 당신이 Heroku에 사이트를 적용하기 위한 ( `requirements.txt` (아래에 나옴)에서) 요구조건 으로서만 맞추려고 한다면, 굳이 이렇게 PostGreSQL을 로컬 컴퓨터에 설치할 필요까지는 없다.

#### 운영환경에서 정적 파일(static file) 지원하기

개발 단계동안 Django와 Django 개발용 웹 서버를 사용하여 정적 파일을 지원해 왔다 (CSS, JavaScript, 등등.). 운영 환경에서는 보통 콘텐츠 전달 네트웍 ( CDN) 이나 웹서버에서 정적 파일을 지원한다.

**주목할 점:** Django/웹 어플리케이션으로 직접 정적 파일을 지원하는 것은 비효율적이다.  웹서버에 의해 직접 조작되거나 CDN으로 완전히 분리하는 것에 비해, 불필요한 추가적인 코드가 요청(requests)에 추가되기 때문이다. 이런 사항은 로컬에서 진행되는 개발단계에서는 영향이 크진 않지만, 운영 단계에서 같은 방식으로 사용하려 한다면 심각한 성능저하 상황이 발생할 수 있다. 

Django 웹 어플리케이션으로부터 분리하여 정적파일을 쉽게 호스팅하기 위해, Django는 개발용 정적 파일을  수집하는 collectstatic 도구를 제공한다 (collectstatic이 실행될 때 어느곳에서 파일이 수집되어야 하는지  정의한 설정 변수들이 있다). Django 템플릿은 정적 파일 종류의 호스팅 위치를 `STATIC_URL` 설정 변수로 알려주며, 정적 파일의 위치가 다른 호스트/서버로 이동되면 여기서 바꿀 수 있도록 한다.

관련 설정 변수는 다음과 같다:

- `STATIC_URL`: 이것은 베이스 URL 위치인데 이곳에서 정적 파일들이 지원된다. 예를 들면 CDN과 같은곳이다. 베이스 템플릿에서 접근하는 정적 템플릿 변수에 사용된다. ( [Django 튜토리얼 파트 5: 홈페이지 작성하기](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Home_page) 를 참고하라).
- `STATIC_ROOT`: 이것은 Django의 "collectstatic" 도구로 템플릿에서 참조하는 모든 정적 파일을 모집하는 디렉토리로 가는 절대 경로이다. 일단 수집되면, 이것들은 파일이 어떤곳에서 호스팅되든지 단체로 업로드 될 수 있다.
- `STATICFILES_DIRS`: 이것은 Django의 colletstatic 도구가 정적 파일을 탐색할 추가적인 디렉토리를 나열한다.

##### settings.py 수정하기

**/locallibrary/settings.py** 파일을 열고 아래 설정을 파일의 맨 밑으로 복사한다. `BASE_DIR` 는 파일에 이미 정의되어 있었어야 한다. ( `STATIC_URL` 도 설정파일이 생성될 때 정의되었을 것이다. 중복되는 이전의 설정은, 지우지 않아도 문제는 없겠지만, 제거하는 것이 좋다).

```python
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/2.0/howto/static-files/

# The absolute path to the directory where collectstatic will collect static files for deployment.
STATIC_ROOT = os.path.join(BASE_DIR, 'staticfiles')

# The URL to use when referring to static files (where they will be served from)
STATIC_URL = '/static/'
```

실제로는, 다음 섹션에서 설치하고 설정할, [WhiteNoise](https://warehouse.python.org/project/whitenoise/) 라는 이름의 라이브러리를 이용하여 파일을 관리할 것이다.

더 많은 정보가 필요하면 [Django and Static Assets](https://devcenter.heroku.com/articles/django-assets) (Heroku docs) 를 참고한다.

#### Whitenoise 적용하기

운영환경에서 정적 파일을 관리하기 위한 수많은 방법이 있다 (바로 앞 섹션에서 관련된 Django 설정을 봤다). Heroku는 [WhiteNoise](https://warehouse.python.org/project/whitenoise/) 프로젝트를 이용하여 운영환경의 Gunicorn상에서 직접 정적 자원을 관리하는 것을 추천한다.

**주목할 점:** Heroku는 자동적으로 collectstatic을 호출하고 사용자 어플리케이션을 업로드한 후 WhiteNoise로 정적파일을 사용할 수 있도록 준비한다. WhiteNoise의 동작 방식과 이 도구를 적용하는  것이 좀더 효율적인 이유에 대한 설명은 [WhiteNoise](https://warehouse.python.org/project/whitenoise/) 문서를 참조한다.

본 프로젝트에 *WhiteNoise를 적용하는 단계적 방법은 다음과 같다* :

##### WhiteNoise 설치하기

아래 명령으로 whitenoise를 로컬에 설치한다 :

```shell
$ pip3 install whitenoise
```

##### settings.py 수정하기

사용자 Django 어플리케이션에 WhiteNoise를 설치하기 위해, **/locallibrary/settings.py** 를 열고, `MIDDLEWARE` 설정을 찾아서 `SecurityMiddleware` 바로 밑의 목록의 윗부분에 `WhiteNoiseMiddleware` 를 추가 한다 :

```python
MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'whitenoise.middleware.WhiteNoiseMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]
```

선택 사항으로, 파일이 서빙될 때 정적 파일의 크기를 줄일 수 있다. (이 방식이 좀더 효율적이다). Just add the following to the bottom of **/locallibrary/settings.py**:

```python
# Simplified static file serving.
# https://warehouse.python.org/project/whitenoise/
STATICFILES_STORAGE = 'whitenoise.storage.CompressedManifestStaticFilesStorage'
```

#### 파이썬 관련 라이브러리 (Requirements) 설치하기

웹 어플리케이션의 Python 관련 라이브러리들은 저장소의 루트에 위치한 **requirements.txt** 라는 파일에 저장되어야 한다. 그러면 Heroku는 환경을 재구성할 때 이 패키지들을 자동적으로 설치할 것이다. 커맨드 라인에서 pip 명령을 이용해 이 파일을 생성할 수 있다. ( 아래 명령을 repo 루트 디렉토리에서 실행한다):

```shell
pip3 freeze > requirements.txt
```

여러가지 관련 라이브러리를 위에서 설치했다면, **requirements.txt** 파일은 최소한 아래 나열된 항목들을 가지고 있어야 한다. ( 버전 숫자는 다를수도 있다). 명확히 이 어플리케이션을 위해 설치한것이 아니라면 , 아래 목록을 제외한 라이브러리는 지우는 것이 좋다.

```text
dj-database-url==0.4.1
Django==2.0
gunicorn==19.6.0
psycopg2==2.6.2
whitenoise==3.2.2
```

위 목록에 **psycopg2** 줄이 있다는 것을 주의해야 한다! 로컬환경에서 이 라이브러리를 설치한 적이 없더라도 이 줄은 **requirements.txt** 파일에 추가해야 한다.

#### Runtime 파일 추가하기

**runtime.txt** 파일이 존재한다면 Heroku에게 웹사이트에서 사용할 프로그래밍 언어를 알려준다. 아래 문구를 추가하여 저장소의 루트에 runtime.txt 파일을 생성한다:

```
python-3.6.4
```

**주목할 점:** Heroku가 지원하는 [Python 실행버전](https://devcenter.heroku.com/articles/python-support#supported-python-runtimes) ( 이 글을 쓰는 당시에는 위 버전을 포함한다)은 그리 많지 않다. Heroku는 이 파일에 지정된 버전에 개의치 않고 지원되는 실행버전을 사용할 것이다.

#### Github에 변경사항을 저장하고 테스트 다시하기

다음으로 모든 변경사항을 Github에 저장하자. 아래 명령을 ( 저장소 범위내 위치에서) 터미널에 입력한다 :

```shell
git add -A
git commit -m "Added files and changes required for deployment to heroku"
git push origin master
```

계속 진행하기전에, 로컬에서 사이트를 다시 테스트 해서 위의 변경사항에 의해 영향받은부분이 없는지 확인한다. 지금까지 해온 것 처럼 개발용 웹 서버를 실행하고 브라우저 상에서 여전히 기대한 대로 동작하는지 체크한다.

```shell
python3 manage.py runserver
```

이제 Heroku에 LocalLibrary의 배포를 시작할 준비가 다 되었다.

### [Heroku 계정 생성하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#heroku_계정_생성하기)

Heroku 이용을 시작 하려면 계정을 한 개 생성해야 한다 :

- [www.heroku.com](https://www.heroku.com/) 사이트로 가서 **SIGN UP FOR FREE** 버튼을 클릭한다.
- 세부 정보를 입력하고 **CREATE FREE ACCOUNT** 버튼을 누른다. 당신은 가입 이메일에서 계정을 확인 하도록 요청받을 것이다.
- 가입 이메일에서 계정 활성화 요청 링크 (account activation link) 를 클릭하면 웹 브라우저 상에서 당신의 계정 위치로 이동될 것이다.
- 당신의 암호를 입력하고 **SET PASSWORD AND LOGIN** 버튼을 클릭하라
- 로그인이 되면 Heroku 대시보드로 이동될 것이다: https://dashboard.heroku.com/apps.

### [client 설치하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#client_설치하기)

여기의 [Heroku 지시사항](https://devcenter.heroku.com/articles/getting-started-with-python#set-up) 에 따라 Heroku 클라이언트를 다운로드하고 설치하라.

클라이언트를 설치한후에 명령어를 실행할 수 있다. 예를 들면, 클라이언트에 대한 도움말을 얻고 싶다면 아래 명령을 실행한다:

```cmd
heroku help
```

### [웹사이트를 생성하고 업로드하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#웹사이트를_생성하고_업로드하기)

앱을 생성하기 위해 "create" 명령을 저장소의 루트 디렉토리에서 실행한다. 이명령은 로컬 컴퓨터의 git 환경에 heroku라는 이름의 git의 원격 저장소(remote) ("원격 저장소의 지정자(pointer)")를 생성한다.

```cmd
heroku create
```

**주목할 점:** 원한다면 원격 저장소(remote)에 이름을 붙일 수 있는데 . "create" 다음에 값을 추가하면 된다. 아무것도 붙이지 않으면 랜덤으로 생성된 이름을 가진다. 이 이름은 기본 URL로 사용된다.

그다음에 아래와 같은 명령으로 앱을 Heroku 저장소에 등록(push)할 수 있다. 이 명령은 앱을 업로드하고, 다이노안에 앱을 포장(pakage)하며, colletstatic을 실행하고 사이트가 시작 되도록 한다.

```cmd
git push heroku master
```

운이 좋다면, 앱은 이제 사이트상에서 "실행(running)" 상태에 있게되겠지만, 어플리케이션을 위한 데이터베이스 테이블을 구성하지 않았기 때문에 제대로 동작하지 않을 것이다. 이 작업을 위해 `heroku run` 명령을 사용해 migrate 작업을 수행할 "[one off 다이노](https://devcenter.heroku.com/articles/deploying-python#one-off-dynos)" 를 시작시켜야 한다. 아래 명령을 터미널에 입력하라 :

```cmd
heroku run python manage.py migrate
```

책과 저자도 추가할 수 있어야 하니까 다시 one-off 다이노를 이용하여 관리자 아이디 또한 생성하자:

```cmd
heroku run python manage.py createsuperuser
```

일단 이 작업이 완료되면, 사이트를 볼 수 있다. 아직 아무 책도 없긴 하지만 사이트가 동작한다. 신규 웹사이트를 브라우저로 보고 싶으면 아래 명령을 사용하라 :

```cmd
heroku open
```

admin 사이트에서 책 몇개를 생성하고 사이트가 기대한대로 동작하는지 확인하라.

### [애드온 관리하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#애드온_관리하기)

`heroku addons` 명령으로 앱의 애드온 적용에 대해 확인할 수 있다. 이 명령은 모든 애드온을 나열하고, 가격과 상태를 보여준다.

```cmd
>heroku addons

Add-on                                     Plan       Price  State
─────────────────────────────────────────  ─────────  ─────  ───────
heroku-postgresql (postgresql-flat-26536)  hobby-dev  free   created
 └─ as DATABASE
```

여기서 단 한개의 애드온만 있는데, 바로  postgres SQL 데이터베이스 이다. 이것은 무료이고, 앱을 생성할 때 자동적으로 생성되었다. 데이터베이스 애드온을 ( 다른 어떤 애드온이라도) 좀 더 자세히 조사하기 위해 아래 명령으로 웹 페이지 열 수 있다 (open) :

```
heroku addons:open heroku-postgresql
```

애드온을 생성하고(create), 제거하고(destroy), 업그레이드하고(upgrade), 다운그레이드하는(downgrade) 다른 명령들도 있다. ( 여는 명령(open)과 유사한 문법을 사용한다). 좀 더 자세한 정보는 [Managing Add-ons](https://devcenter.heroku.com/articles/managing-add-ons) (Heroku 문서) 를 참조하라.

### [구성 변수 ( configuration variables) 설정하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#구성_변수_(_configuration_variables)_설정하기)

`heroku config` 명령을 이용하여 사이트의 구성 변수를 확인할 수 있다. 아래에서 현재 한개의 변수만 가지는 것을 확인할 수있는데, `DATABASE_URL` 변수는 데이터베이스를 설정하는 데 사용된다.

```
>heroku config

=== locallibrary Config Vars
DATABASE_URL: postgres://uzfnbcyxidzgrl:j2jkUFDF6OGGqxkgg7Hk3ilbZI@ec2-54-243-201-144.compute-1.amazonaws.com:5432/dbftm4qgh3kda3
```

위에서 공부한 [웹 사이트 공개(publish) 준비하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#getting_your_website_ready_to_publish) 섹션을 다시 떠올려보면, `DJANGO_SECRET_KEY`와 `DJANGO_DEBUG` 환경 변수를 설정해야 한다. 지금 이 작업을 하자.

**주목할 점:** secret key는 정말로 비밀이 유지되어야 한다! 새로운 키를 만들어 내는 한가지 방법은 새로운 Django 프로젝트를 생성하고 (`django-admin startproject someprojectname`) 신규 프로젝트의 **settings.py**에서 생성된 키를 가져오는 것이다.

`DJANGO_SECRET_KEY` 를 `config:set` 명령을 통해 설정한다(아래 예제와 같이). 당신 자신의 secret key를 사용해야 한다는 것을 기억하자!

```
>heroku config:set DJANGO_SECRET_KEY=eu09(ilk6@4sfdofb=b_2ht@vad*$ehh9-)3u_83+y%(+phh&=

Setting DJANGO_SECRET_KEY and restarting locallibrary... done, v7
DJANGO_SECRET_KEY: eu09(ilk6@4sfdofb=b_2ht@vad*$ehh9-)3u_83+y%(+phh
```

`DJANGO_DEBUG` 도 유사하게 설정한다 :

```
>heroku config:set DJANGO_DEBUG=

Setting DJANGO_DEBUG and restarting locallibrary... done, v8
```

지금 웹사이트를 방문한다면 "Bad request" 에러를 만나게 되는데 이것은 (보안 조치상) `DEBUG=False` 로 설정되어 있여서 [ALLOWED_HOSTS](https://docs.djangoproject.com/en/2.0/ref/settings/#allowed-hosts) 설정이 요구되기 때문이다. **/locallibrary/settings.py** 파일을 열고 아래와 같이 `ALLOWED_HOSTS` 설정을 수정하여 베이스 앱 url과 (예를들면, 'locallibrary1234.herokuapp.com') 로컬 개발 환경 웹서버에서 지금까지 사용해온 URL을 추가하라.

```
ALLOWED_HOSTS = ['<your app URL without the https:// prefix>.herokuapp.com','127.0.0.1']
# For example:
# ALLOWED_HOSTS = ['fathomless-scrubland-30645.herokuapp.com','127.0.0.1']
```

그다음 지금 설정한 것들을 저장하고 Github 저장소와 Herok에 반영한다 :

```
git add -A
git commit -m 'Update ALLOWED_HOSTS with site and development server URL'
git push origin master
git push heroku master
```

Heroku에 사이트 업데이트하는 것을 완료한후, 존재하지 않는 주소URL(예를 들면, **/catalog/doesnotexist/**) 을 입력해 보자. 이전에는 이런 경우 자세한 설명이 담긴 디버그 페이지를 보여주었었지만, 이제는 단순한 "Not Found" 페이지가 보일것이다.

### [디버깅 하기](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#디버깅_하기)

Heroku 클라이언트는 디버깅을 위한 몇가지 도구를 제공한다 :

```
heroku logs  # Show current logs
heroku logs --tail # Show current logs and keep updating with any new results
heroku config:set DEBUG_COLLECTSTATIC=1 # Add additional logging for collectstatic (this tool is run automatically during a build)
heroku ps   #Display dyno status
```

이것들보다 더 자세한 정보가 필요하다면 [Django Logging](https://docs.djangoproject.com/en/2.0/topics/logging/) 을 자세히 살펴보는 것이 좋을것이다.



## [요약](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/Deployment#요약)

이렇게 Django 앱을 운영환경에서 구성해보는 튜토리얼이 끝났고. 지금까지 공부해온 Django 튜토리얼 시리즈도 이것으로 마무리가  되었다. 이 시리즈가 당신에 유용한 자료이길 바란다. 앞에서 설명한 모든 단계를 커버하는 버전의, [Github상의 소스 코드는 이곳에서](https://github.com/mdn/django-locallibrary-tutorial) 확인할 수 있다.

 다음 단계는 마지막으로 몇개의 기사를 읽어보고, 평가작업을 완성하는 것이다.



# Django web application security

link : https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security

사용자의 데이터를 보호하는 것은 모든 웹사이트 개발에서 중요한 부분입니다. 우리는 이전의 강의 [Web security](https://developer.mozilla.org/en-US/docs/Web/Security) 에서 자주 나타나는 보안 위협에 대해 알아봤습니다. — 이번 강의에서는 장고의 내장 보안 기능이 이런 위험에 어떻게 대처하는지 실제 예시들을 통해 살펴보겠습니다. 

| 이 문서를 보기 전에: | 서버사이드 개발에 대한 "[Website security](https://developer.mozilla.org/en-US/docs/Learn/Server-side/First_steps/Website_security)" 부분을 읽고 오세요. MDN 장고 튜토리얼에서 적어도 [Django Tutorial Part 9: Working with forms](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Forms) 까지는 모두 알고있어야 합니다. |
| -------------------- | ------------------------------------------------------------ |
| 목적:                | 장고 웹사이트의 보안을 위해 해야 하는 (하지 말아야 하는) 것들에 대해 이해해 봅시다. |

## [개요](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#개요)

[Website security](https://developer.mozilla.org/en-US/docs/Web/Security) 문서는 서버사이드 설계에서 웹사이트 보안이란 무엇을 이야기하는건지, 또한 개발자가 막아내야 하는 몇가지 대표적인 위협에 대해 소개합니다. 이  문서에서 중요한 내용중의 하나는 바로 웹 애플리케이션이 브라우저에서 전송된 데이터를 신뢰하는 경우에는 거의 모든 종류의 공격이  가능하다는 것입니다. 

**Important:** 웹사이트 보안에 대해 당신이 배울 수 있는 가장 중요한 점 한가지는 **브라우저의 데이터를 절대로 믿지 말라는 것** 입니다. 이건 URL 파라미터의 `GET` request 데이터, `POST `데이터, HTTP 헤더와 쿠키, 사용자가 업로드한 파일들...기타등등을 포함합니다. 언제나 전송받는 모든 데이터를 의심하고 체크하십시오. 언제나 최악을 가정하십시오.

Django 사용자들에게 좋은 소식은 대부분의 일반적인 위협들은 프레임워크에 의해 차단된다는 것입니다! [Security in Django](https://docs.djangoproject.com/en/2.0/topics/security/) 문서는 Django의 보안 개요와 Django 기반의 웹사이트를 어떻게 지킬 수 있는지에 대해 설명하고 있습니다.

## [Common threats/protections](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#common_threatsprotections)

Django 문서를 여기로 복사해오기보다, 이 문서에서 우리는 Django [LocalLibrary](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Tutorial_local_library_website) 튜토리얼에 있는 Django의 몇가지 보안 형태를 묘사해보도록 하겠습니다.

### [Cross site scripting (XSS)](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#cross_site_scripting_xss)

XSS is a term used to describe a class of attacks that allow an attacker to inject client-side scripts *through* the website into the browsers of other users. This is usually achieved  by storing malicious scripts in the database where they can be retrieved and displayed to other users, or by getting users to click a link that  will cause the attacker’s JavaScript to be executed by the user’s  browser.

Django's template system protects you against the majority of XSS attacks by [escaping specific characters](https://docs.djangoproject.com/en/2.0/ref/templates/language/#automatic-html-escaping) that are "dangerous" in HTML. We can demonstrate this by attempting to  inject some JavaScript into our LocalLibrary website using the  Create-author form we set up in [Django Tutorial Part 9: Working with forms](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Forms).

1. Start the website using the development server (`python3 manage.py runserver`).

2. Open the site in your local browser and login to your superuser account.

3. Navigate to the author-creation page (which should be at URL: `http://127.0.0.1:8000/catalog/author/create/`).

4. Enter names and date details for a new user, and then append the following text to the Last Name field:

   ```
   <script>alert('Test alert');</script>
   ```

   .

   

   **Note:** This is a harmless script that, if executed, will display an alert box in your browser. If the alert is displayed  when you submit the record then the site is vulnerable to XSS threats.

5. Press **Submit** to save the record.

6. When you save the author it will be displayed as shown below. Because of the XSS protections the `alert()` should not be run. Instead the script is displayed as plain text.![Author detail view XSS test](./images/author_detail_alert_xss.png)

If you view the page HTML source code, you can see that the dangerous characters for the script tags have been turned into their harmless  escape code equivalents (e.g. `>` is now `>`)

```
<h1>Author: Boon&lt;script&gt;alert(&#39;Test alert&#39;);&lt;/script&gt;, David (Boonie) </h1>
```

Using Django templates protects you  against the majority of XSS attacks. However it is possible to turn off  this protection, and the protection isn't automatically applied to all  tags that wouldn't normally be populated by user input (for example, the `help_text` in a form field is usually not user-supplied, so Django doesn't escape those values).

It is also possible for XSS attacks to originate from other untrusted source of data, such as cookies, Web services or uploaded files  (whenever the data is not sufficiently sanitized before including in a  page). If you're displaying data from these sources, then you may need  to add your own sanitisation code.

### [Cross site request forgery (CSRF) protection](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#cross_site_request_forgery_csrf_protection)

CSRF attacks allow a malicious user to execute actions using the credentials of another user without that user’s knowledge or consent. For example  consider the case where we have a hacker who wants to create additional  authors for our LocalLibrary.

**Note:** Obviously our hacker isn't in this for the  money! A more ambitious hacker could use the same approach on other  sites to perform much more harmful tasks (e.g. transfer money to their  own accounts, etc.)

In order to do this, they might create an HTML file like the one  below, which contains an author-creation form (like the one we used in  the previous section) that is submitted as soon as the file is loaded.  They would then send the file to all the Librarians and suggest that  they open the file (it contains some harmless information, honest!). If  the file is opened by any logged in librarian, then the form would be  submitted with their credentials and a new author would be created.

```html
<html>
<body onload='document.EvilForm.submit()'>

<form action="http://127.0.0.1:8000/catalog/author/create/" method="post" name='EvilForm'>
  <table>
    <tr><th><label for="id_first_name">First name:</label></th><td><input id="id_first_name" maxlength="100" name="first_name" type="text" value="Mad" required /></td></tr>
    <tr><th><label for="id_last_name">Last name:</label></th><td><input id="id_last_name" maxlength="100" name="last_name" type="text" value="Man" required /></td></tr>
    <tr><th><label for="id_date_of_birth">Date of birth:</label></th><td><input id="id_date_of_birth" name="date_of_birth" type="text" /></td></tr>
    <tr><th><label for="id_date_of_death">Died:</label></th><td><input id="id_date_of_death" name="date_of_death" type="text" value="12/10/2016" /></td></tr>
  </table>
  <input type="submit" value="Submit" />
</form>

</body>
</html>
```

Run the development web server, and log in  with your superuser account. Copy the text above into a file and then  open it in the browser. You should get a CSRF error, because Django has  protection against this kind of thing!

The way the protection is enabled is that you include the `{% csrf_token %}` template tag in your form definition. This token is then rendered in  your HTML as shown below, with a value that is specific to the user on  the current browser.

```html
<input type='hidden' name='csrfmiddlewaretoken' value='0QRWHnYVg776y2l66mcvZqp8alrv4lb8S8lZ4ZJUWGZFA5VHrVfL2mpH29YZ39PW' />
```

Django generates a user/browser specific key  and will reject forms that do not contain the field, or that contain an  incorrect field value for the user/browser.

To use this type of attack the hacker now has to discover and include the CSRF key for the specific target user. They also can't use the  "scattergun" approach of sending a malicious file to all librarians and  hoping that one of them will open it, since the CSRF key is browser  specific.

Django's CSRF protection is turned on by default. You should always use the `{% csrf_token %}` template tag in your forms and use `POST` for requests that might change or add data to the database.

### [Other protections](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#other_protections)

Django also provides other forms of protection (most of which would be hard or not particularly useful to demonstrate):

- SQL injection protection

  SQL injection vulnerabilities enable malicious users to execute  arbitrary SQL code on a database, allowing data to be accessed,  modified, or deleted irrespective of the user's permissions. In almost  every case you'll be accessing the database using Django’s  querysets/models, so the resulting SQL will be properly escaped by the  underlying database driver. If you do need to write raw queries or  custom SQL then you'll need to explicitly think about preventing SQL  injection.

- Clickjacking protection

  In this attack a malicious user hijacks clicks meant for a visible  top level site and routes them to a hidden page beneath. This technique  might be used, for example, to display a legitimate bank site but  capture the login credentials in an invisible [``](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe) controlled by the attacker. Django contains [clickjacking protection](https://docs.djangoproject.com/en/2.0/ref/clickjacking/#clickjacking-prevention) in the form of the [`X-Frame-Options middleware`](https://docs.djangoproject.com/en/2.0/ref/middleware/#django.middleware.clickjacking.XFrameOptionsMiddleware) which, in a supporting browser, can prevent a site from being rendered inside a frame.

- Enforcing SSL/HTTPS

  SSL/HTTPS can be enabled on the web server in order to encrypt all  traffic between the site and browser, including authentication  credentials that would otherwise be sent in plain text (enabling HTTPS  is highly recommended). If HTTPS is enabled then Django provides a  number of other protections you can use:

- [`SECURE_PROXY_SSL_HEADER`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-SECURE_PROXY_SSL_HEADER) can be used to check whether content is secure, even if it is incoming from a non-HTTP proxy.
- [`SECURE_SSL_REDIRECT`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-SECURE_SSL_REDIRECT) is used to redirect all HTTP requests to HTTPS.
- Use [HTTP Strict Transport Security](https://docs.djangoproject.com/en/2.0/ref/middleware/#http-strict-transport-security) (HSTS). This is an HTTP header that informs a browser that all future  connections to a particular site should always use HTTPS. Combined with  redirecting HTTP requests to HTTPS, this setting ensures that HTTPS is  always used after a successful connection has occurred. HSTS may either  be configured with [`SECURE_HSTS_SECONDS`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-SECURE_HSTS_SECONDS) and [`SECURE_HSTS_INCLUDE_SUBDOMAINS`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-SECURE_HSTS_INCLUDE_SUBDOMAINS) or on the Web server.
- Use ‘secure’ cookies by setting [`SESSION_COOKIE_SECURE`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-SESSION_COOKIE_SECURE) and [`CSRF_COOKIE_SECURE`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-CSRF_COOKIE_SECURE) to `True`. This will ensure that cookies are only ever sent over HTTPS.

- Host header validation

  Use [`ALLOWED_HOSTS`](https://docs.djangoproject.com/en/2.0/ref/settings/#std:setting-ALLOWED_HOSTS) to only accept requests from trusted hosts.

There are many other protections, and caveats to the usage of the  above mechanisms. While we hope that this has given you an overview of  what Django offers, you should still read the Django security  documentation.



## [Summary](https://developer.mozilla.org/ko/docs/Learn/Server-side/Django/web_application_security#summary)

Django has effective protections against a number of common threats, including XSS and CSRF attacks. In this article we've demonstrated how those  particular threats are handled by Django in our *LocalLibrary* website. We've also provided a brief overview of some of the other protections.

This has been a very brief foray into web security. We strongly recommend that you read [Security in Django](https://docs.djangoproject.com/en/2.0/topics/security/) to gain a deeper understanding.

The next and final step in this module about Django is to complete the [assessment task](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/django_assessment_blog).

