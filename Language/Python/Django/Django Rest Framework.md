# Django Rest Framework



## 훑어보기

![Django Rest Framework 3 훑어보기](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)



Django Rest Framework 3을 이해해 보고자 튜토리얼 문서를 번역하고 있습니다. 의역이 난무하지만 기술적으로 잘못된 내용을 전달하지 않게 최선을 다해보겠습니다.



원문 - [QuickStart Guide with Django Rest Framework3](http://www.django-rest-framework.org/tutorial/quickstart/)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------



# 훑어보기

이제부터 관리자 계정으로 사용자를 관리하는 간단한 API를 만들어 보겠습니다.



## 프로젝트 설정

`tutorial`이라는 이름으로 새로운 Django 프로젝트를 만든 다음 `quickstart` 앱을 생성합니다.

```language-bash
# 프로젝트용 디렉터리를 만듭니다
mkdir tutorial
cd tutorial

# 패키지 환경을 독립적으로 관리하기 위해 가상 환경을 생성합니다
virtualenv env
source env/bin/activate  # On Windows use `env\Scripts\activate`

# Django와 Django REST framework를 가상 환경에 설치합니다
pip install django
pip install djangorestframework

# 프로젝트와 앱을 생성합니다
django-admin.py startproject tutorial .  # 마지막에 있는 .을 꼭 입력하세요
cd tutorial
django-admin.py startapp quickstart
cd ..
```

이제 처음으로 데이터베이스를 생성합니다.

```language-bash
python manage.py migrate
```

관리자 계정으로 사용할 아이디와 비밀번호도 만들죠. 나중에 이 계정으로 인증을 할 겁니다.

```language-bash
python manage.py createsuperuser
```

자, 이제 준비를 마쳤으니 코딩을 해봅시다.

## 시리얼라이저

시리얼라이저부터 만들어보죠. `tutorial/quickstart/serializers.py` 파일을 만듭시다. 데이터를 표현하는 데 사용할 겁니다.

```language-python
from django.contrib.auth.models import User, Group
from rest_framework import serializers


class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'username', 'email', 'groups')


class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ('url', 'name')
```

하이퍼링크 관계를 위해 `HyperlinkedModelSerializer`를 사용한 것을 눈여겨 보세요. 물론 주 키(primary key)나 다른 방법으로도 관계를 연결할 수 있겠지만, 하이퍼링크야말로 RESTful 디자인에 잘 어울립니다.

## 뷰

좋습니다. 이제 뷰를 만들죠. `tutorial/quickstart/views.py` 파일을 다음과 같이 작성합니다.

```language-python
from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from tutorial.quickstart.serializers import UserSerializer, GroupSerializer


class UserViewSet(viewsets.ModelViewSet):
    """
    사용자(user)를 보거나 편집하는 API
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer


class GroupViewSet(viewsets.ModelViewSet):
    """
    그룹(group)을 보거나 편집하는 API
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer
```

뷰를 여러 번 작성하기보다는 자주 사용되는 기능들을 묶은 ViewSet 클래스를 사용했습니다.

물론 필요에 따라 뷰를 잘게 나눌 수도 있습니다. 하지만 뷰셋(viewset)을 사용하면 뷰 로직을 아주 간결하고 체계적으로 유지할 수 있습니다.

[문서의 제일 처음](http://www.django-rest-framework.org/#example)에 보여드렸던 클래스에서는 `model` 속성 대신 `queryset`과 `serializer_class` 속성을 사용했는데요.

일반적인 경우 ViewSet 클래스에 `model` 속성만 설정하면 `serializer`와 `queryset`은 자동으로 설정됩니다. `queryset`이나 `serializer_class` 속성을 설정하면 API의 행동을 좀더 명시적으로 표현할 수 있는데 대다수 애플리케이션에서는 이렇게 하기를 권장합니다.

## URL

자 이제 API URL을 연결해봅시다. `tutorial/urls.py` 파일입니다.

```language-python
from django.conf.urls import url, include
from rest_framework import routers
from tutorial.quickstart import views

router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet)
router.register(r'groups', views.GroupViewSet)

# 우리가 만든 API를 자동으로 라우팅합니다.
# 그리고 API 탐색을 위해 로그인 URL을 추가했습니다.
urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]
```

뷰 대신 뷰셋을 사용한 덕에, 뷰셋의 라우터(router) 클래스를 통해 API에 대한 URL이 자동으로 생성됩니다.

여기서도 역시, API URL을 여러분 마음대로 설정하고 싶다면 여러분이 직접 만든 클래스 기반 뷰(class based view)에 URL을 연결하면 되겠습니다.

마지막으로 탐색친화적(browseable)인 API를 만들기 위해 기본 로그인/로그아웃 뷰를 추가했습니다. 반드시 추가해야 하는 건 아니자만 여러분의 API가 인증 후 API 탐색을 허용하는 경우에는 유용하게 사용할 수 있겠습니다.

## 설정

전역 변수를 몇 개 설정해 봅시다. 페이지 기능을 켜고, API에는 관리자 계정만 접근하도록 합시다. `tutorial/settings.py` 파일에 다음 내용을 추가합니다.

```language-python
INSTALLED_APPS = (
    ...
    'rest_framework',
)

REST_FRAMEWORK = {
    'DEFAULT_PERMISSION_CLASSES': ('rest_framework.permissions.IsAdminUser',),
    'PAGINATE_BY': 10
}
```

네, 이제 끝났습니다.

------

## API 테스트하기

지금까지 만든 API를 테스트해 봅시다. 명령창에서 서버를 실행합니다.

```language-bash
python ./manage.py runserver
```

이제 명령창에서 (curl 명령 등으로) API에 접근할 수 있습니다.

```language-bash
bash: curl -H 'Accept: application/json; indent=4' -u admin:password http://127.0.0.1:8000/users/
{
    "count": 2,
    "next": null,
    "previous": null,
    "results": [
        {
            "email": "admin@example.com",
            "groups": [],
            "url": "http://127.0.0.1:8000/users/1/",
            "username": "admin"
        },
        {
            "email": "tom@example.com",
            "groups": [                ],
            "url": "http://127.0.0.1:8000/users/2/",
            "username": "tom"
        }
    ]
}
```

httpie 같은 도구를 사용해도 좋습니다.

```language-bash
bash: http -a username:password http://127.0.0.1:8000/users/
HTTP/1.1 200 OK
...
{
    "count": 2,
    "next": null,
    "previous": null,
    "results": [
        {
            "email": "admin@example.com",
            "groups": [],
            "url": "http://localhost:8000/users/1/",
            "username": "paul"
        },
        {
            "email": "tom@example.com",
            "groups": [                ],
            "url": "http://127.0.0.1:8000/users/2/",
            "username": "tom"
        }
    ]
}
```

아니면 웹 브라우저에서 봐도 되죠.

![Quick start image](http://www.django-rest-framework.org/img/quickstart.png)

웹 브라우저를 사용한다면 오른쪽 상단의 버튼을 눌러 로그인을 해야 할 겁니다.

좋습니다. 쉽죠?

REST 프레임워크가 어떻게 작동하는지 더 알고 싶다면, [다음 튜토리얼](https://raccoonyy.github.io/drf3-tutorial-1)을 계속 읽어가든지 [API 가이드](http://www.django-rest-framework.org/#api-guide)를 참고하세요.



# DRF3 튜토리얼 1 - 직렬화

원문 - [Tutorial 1: Serialization](http://www.django-rest-framework.org/tutorial/1-serialization/)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 1: Serialization

## 개요

이 튜토리얼에서는 pastebin 같이 간단한 코드 하이라이팅 웹 API를 만들어 보겠습니다. 이와 함께 REST 프레임워크의 다양한 기능을 살펴보고, 이런 기능들이 어떻게 서로 어울려 작동하는지도 설명하겠습니다.

이 튜토리얼은 꽤 길기 때문에 쿠키와 음료를 옆에 준비해두기를 권합니다. 그저 슬쩍 훑어보고만 싶다면 [훑어보기](https://raccoonyy.github.io/django-rest-framework-3-quickstart/) 문서를 보세요.

------

**안내:** 이 튜토리얼에서 사용하는 코드는 GitHub에 있는 저장소([tomchristie/rest-framework-tutorial](https://github.com/tomchristie/rest-framework-tutorial))에서 확인할 수 있습니다. 전체 코드를 구현한 데모 버전은 [여기서](http://restframework.herokuapp.com/) 확인할 수 있습니다.

------

## 새 가상 환경 만들기

뭔가를 하기 전에 virtualenv를 사용하여 가상 환경을 만듭시다. 이를 통해 패키지 설정을 독립적으로 관리할 수 있습니다.

```bash
virtualenv env
source env/bin/activate
```

가상 환경 안에서 다음과 같은 패키지를 설치합니다.

```bash
pip install django
pip install djangorestframework
pip install pygments  # 코드 하일라이팅에 사용할 패키지입니다
```

**안내:** 가상 환경에서 벗어나고 싶다면 `deactivate`를 입력하면 됩니다. 더 자세한 내용은 [virtualenv 문서](http://www.virtualenv.org/en/latest/index.html)에서 확인하세요.

## 시작하기

자, 이제 코드를 작성해 봅시다. 우선 새 프로젝트를 만듭니다.

```bash
cd ~
django-admin.py startproject tutorial
cd tutorial
```

그리고 웹 API를 위한 앱을 하나 생성합니다.

```bash
python manage.py startapp snippets
```

방금 만든 `snippets` 앱 외에 `rest_framework` 앱도 `INSTALLED_APPS`에 추가해야 합니다. `tutorial/settings.py` 파일을 수정하세요.

```python
INSTALLED_APPS = (
    ...
    'rest_framework',
    'snippets',
)
```

URL도 설정해야 합니다. `tutorial/urls.py` 파일에 snippet 앱의 URL을 추가합시다.

```python
urlpatterns = [
    url(r'^', include('snippets.urls')),
]
```

이제 준비 과정은 끝났습니다.

## 모델 만들기

이 튜토리얼에서 사용할 간단한 `Snippet` 모델을 만듭시다. 이 모델에는 코드 조각들이 저장됩니다. `snippets/models.py` 파일을 다음과 같이 수정합니다.

**안내:** 코드 주석을 작성하는 것은 좋은 프로그래밍 습관입니다. 우리가 제공하는 저장소의 코드에서도 코드 주석을 볼 수 있을 겁니다. 하지만 여기서는 코드에 집중하기 위해 주석을 제거했습니다.

```python
from django.db import models
from pygments.lexers import get_all_lexers
from pygments.styles import get_all_styles

LEXERS = [item for item in get_all_lexers() if item[1]]
LANGUAGE_CHOICES = sorted([(item[1][0], item[0]) for item in LEXERS])
STYLE_CHOICES = sorted((item, item) for item in get_all_styles())


class Snippet(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    title = models.CharField(max_length=100, blank=True, default='')
    code = models.TextField()
    linenos = models.BooleanField(default=False)
    language = models.CharField(choices=LANGUAGE_CHOICES, default='python', max_length=100)
    style = models.CharField(choices=STYLE_CHOICES, default='friendly', max_length=100)

    class Meta:
        ordering = ('created',)
```

snippet 모델을 초기화할 마이그레이션을 만들어야 하고, 데이터베이스에 처음으로 싱크도 해야 합니다.

```bash
python manage.py makemigrations snippets
python manage.py migrate
```

## 시리얼라이저 클래스 만들기

웹 API를 만들려면 우선 Snippet 클래스의 인스턴스를 `json` 같은 형태로 직렬화(serializing)하거나 반직렬화(deserializing)할 수 있어야 합니다. Django REST 프레임워크에서는 Django 폼과 비슷한 방식으로 시리얼라이저를 작성합니다. `snippets/serializers.py` 파일을 만들고 다음과 같은 내용을 작성합시다.

```python
from django.forms import widgets
from rest_framework import serializers
from snippets.models import Snippet, LANGUAGE_CHOICES, STYLE_CHOICES


class SnippetSerializer(serializers.Serializer):
    pk = serializers.IntegerField(read_only=True)
    title = serializers.CharField(required=False, allow_blank=True, max_length=100)
    code = serializers.CharField(style={'base_template': 'textarea.html'})
    linenos = serializers.BooleanField(required=False)
    language = serializers.ChoiceField(choices=LANGUAGE_CHOICES, default='python')
    style = serializers.ChoiceField(choices=STYLE_CHOICES, default='friendly')

    def create(self, validated_data):
        """
        검증한 데이터로 새 `Snippet` 인스턴스를 생성하여 리턴합니다.
        """
        return Snippet.objects.create(**validated_data)

    def update(self, instance, validated_data):
        """
        검증한 데이터로 기존 `Snippet` 인스턴스를 업데이트한 후 리턴합니다.
        """
        instance.title = validated_data.get('title', instance.title)
        instance.code = validated_data.get('code', instance.code)
        instance.linenos = validated_data.get('linenos', instance.linenos)
        instance.language = validated_data.get('language', instance.language)
        instance.style = validated_data.get('style', instance.style)
        instance.save()
        return instance
```

시리얼라이저 클래스의 윗부분에서는 직렬화/반직렬화될 필드를 선언했습니다. `create()` 메서드와 `update()` 메서드에서는 `serializer.save()`가 호출되었을 때 인스턴스가 생성 혹은 수정되는 과정을 전부 명시하고 있습니다.

시리얼라이저 클래스는 Django의 `Form` 클래스와 매우 비슷하며, `required`나 `max_length`, `default` 같이 `Form` 클래스에서 사용하던 값 검증을 위한 옵션도 필드에 지정할 수 있습니다.

이러한 옵션들을 통해 특정 상황 - 예를 들면 HTML로 렌더링한다든지 - 에 시리얼라이저가 어떻게 작동해야 하는지를 명시할 수 있습니다. 앞선 코드의 `{'base_template': 'textarea.html'}` 부분은 Django `Form`의 `widget=widgets.Textarea`과 같습니다. 탐색가능한 API를 만들 때 이런 기능이 얼마나 유용한지는 차차 알아보겠습니다.

사실, 나중에 살펴 볼 `ModelSerializer` 클래스를 사용하면 이러한 기능을 일일이 구현하지 않아도 되지만 일단은 명시적인 시리얼라이저를 만들어 봅시다.

## 시리얼라이저 사용하기

진도를 나아가기에 앞서, 우리가 만든 시리얼라이저 클래스에 조금 익숙해져 봅시다. Django 셸을 띄우세요.

```bash
python manage.py shell
```

필요한 패키지들을 import하고 나서, 코드 조각(Snippet 클래스의 인스턴스)을 만들어 봅시다.

```python
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser

snippet = Snippet(code='foo = "bar"\n')
snippet.save()

snippet = Snippet(code='print "hello, world"\n')
snippet.save()
```

snippet 인스턴스가 만들어졌으니, 이 인스턴스 중 하나를 직렬화해보죠.

```python
serializer = SnippetSerializer(snippet)
serializer.data
# {'pk': 2, 'title': u'', 'code': u'print "hello, world"\n', 'linenos': False, 'language': u'python', 'style': u'friendly'}
```

여기서는 모델 인스턴스를 파이썬의 데이터 타입으로 변환했는데요. 직렬화 과정을 마무리하려면 이 데이터를 `json`으로 변환해야 합니다.

```python
content = JSONRenderer().render(serializer.data)
content
# '{"pk": 2, "title": "", "code": "print \\"hello, world\\"\\n", "linenos": false, "language": "python", "style": "friendly"}'
```

반직렬화도 이와 비슷합니다. 먼저, 파이썬 데이터 타입을 파싱합니다.

```python
from django.utils.six import BytesIO

stream = BytesIO(content)
data = JSONParser().parse(stream)
```

다음으로 이 데이터를 인스턴스화합니다.

```python
serializer = SnippetSerializer(data=data)
serializer.is_valid()
# True
serializer.validated_data
# OrderedDict([('title', ''), ('code', 'print "hello, world"\n'), ('linenos', False), ('language', 'python'), ('style', 'friendly')])
serializer.save()
# <Snippet: Snippet object>
```

폼을 다루는 방식과 많이 비슷하죠? 뷰를 작성할 때뿐만 아니라 시리얼라이저를 사용하는 방식도 폼을 다루는 방식과 유사합니다.

모델의 인스턴스뿐만 아니라 쿼리셋도 직렬화할 수 있습니다. 시리얼라이저의 인자에 `many=True`만 추가하면 됩니다.

```python
serializer = SnippetSerializer(Snippet.objects.all(), many=True)
serializer.data
# [{'pk': 1, 'title': u'', 'code': u'foo = "bar"\n', 'linenos': False, 'language': u'python', 'style': u'friendly'}, {'pk': 2, 'title': u'', 'code': u'print "hello, world"\n', 'linenos': False, 'language': u'python', 'style': u'friendly'}]
```

## ModelSerializers 사용하기

`SnippetSerializer` 클래스는 `Snippet` 모델의 정보들을 그대로 베낍니다. 이런 특징을 살려 코드를 좀더 간단하게 줄여 보겠습니다.

Django에서 `Form` 클래스와 `ModelForm` 클래스를 제공하듯, REST 프레임워크에서도 `Serializer` 클래스와 `ModelSerializer` 클래스를 제공합니다.

우리가 만든 시리얼라이저가 `ModelSerializer` 클래스를 사용하도록 리팩터링해 봅시다. `snippets/serializers.py` 파일을 열고 `SnippetSerializer` 클래스 부분을 다음과 같이 수정합니다.

```python
class SnippetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Snippet
        fields = ('id', 'title', 'code', 'linenos', 'language', 'style')
```

이렇게 시리얼라이저에 프로퍼티 하나만 정의한 후 시리얼라이저 인스턴스를 출력해보면 모든 필드를 확인할 수 있습니다. `python manage.py shell` 명령으로 Django 셸을 열어 다음과 같이 해보세요.

```python
>>> from snippets.serializers import SnippetSerializer
>>> serializer = SnippetSerializer()
>>> print(repr(serializer))
SnippetSerializer():
    id = IntegerField(label='ID', read_only=True)
    title = CharField(allow_blank=True, max_length=100, required=False)
    code = CharField(style={'base_template': 'textarea.html'})
    linenos = BooleanField(required=False)
    language = ChoiceField(choices=[('Clipper', 'FoxPro'), ('Cucumber', 'Gherkin'), ('RobotFramework', 'RobotFramework'), ('abap', 'ABAP'), ('ada', 'Ada')...
    style = ChoiceField(choices=[('autumn', 'autumn'), ('borland', 'borland'), ('bw', 'bw'), ('colorful', 'colorful')...
```

`ModelSerializer` 클래스가 마법을 부리는 도구는 아닙니다. 그저 시리얼라이저 클래스의 단축 버전일 뿐이죠.

- 필드를 자동으로 인식한다
- `create()` 메서드와 `update()` 메서드가 이미 구현되어 있다.

## 시리얼라이저를 사용하는 Django 뷰 만들기

이제, 앞에서 새로 만든 시리얼라이저 클래스를 뷰에서 어떻게 사용할 수 있는지 보여드리겠습니다.
지금 당장은 REST 프레임워크의 기능을 사용하지 않고, 일반적인 Django 뷰의 형태로 만들겠습니다.

HttpResponse의 하위 클래스를 만들고, 받은 데이터를 모두 `json` 형태로 반환합시다.

`snippets/views.py` 파일을 열고 다음 내용을 입력합니다.

```python
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer

class JSONResponse(HttpResponse):
    """
    콘텐츠를 JSON으로 변환한 후 HttpResponse 형태로 반환합니다.
    """
    def __init__(self, data, **kwargs):
        content = JSONRenderer().render(data)
        kwargs['content_type'] = 'application/json'
        super(JSONResponse, self).__init__(content, **kwargs)
```

우리가 만들 API의 최상단에서는 저장된 코드 조각을 모두 보여주며, 새 코드 조각을 만들 수도 있습니다.

```python
@csrf_exempt
def snippet_list(request):
    """
    코드 조각을 모두 보여주거나 새 코드 조각을 만듭니다.
    """
    if request.method == 'GET':
        snippets = Snippet.objects.all()
        serializer = SnippetSerializer(snippets, many=True)
        return JSONResponse(serializer.data)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = SnippetSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JSONResponse(serializer.data, status=201)
        return JSONResponse(serializer.errors, status=400)
```

인증되지 않은 사용자도 이 뷰에 POST를 할 수 있도록 `csrf_exempt` 데코레이터를 적어둔 점을 눈여겨 보시기 바랍니다. 이는 보통의 경우 필요 없을 수도 있고 REST 프레임워크의 뷰가 이보다 더 정밀한 속성들을 제공하기도 하지만, 일단 여기서는 우리가 구현하고 싶은 기능을 `csrf_exempt`가 잘 담당하고 있습니다.

이제 코드 조각 하나를 보여줄 뷰도 필요합니다. 또 이 코드 조각을 업데이트하거나 삭제할 수도 있어야 합니다.

```python
@csrf_exempt
def snippet_detail(request, pk):
    """
    코드 조각 조회, 업데이트, 삭제
    """
    try:
        snippet = Snippet.objects.get(pk=pk)
    except Snippet.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = SnippetSerializer(snippet)
        return JSONResponse(serializer.data)

    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = SnippetSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JSONResponse(serializer.data)
        return JSONResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        snippet.delete()
        return HttpResponse(status=204)
```

마지막으로 이 뷰들과 URL을 연결합시다. `snippets/urls.py` 파일을 만들고 다음 내용을 입력합니다.

```python
from django.conf.urls import url
from snippets import views

urlpatterns = [
    url(r'^snippets/$', views.snippet_list),
    url(r'^snippets/(?P<pk>[0-9]+)/$', views.snippet_detail),
]
```

여기서 다루지 않은 특별한 경우들은 그냥 무시하고 지나갑시다. `json`의 내용이 깨졌거나 뷰가 처리할 수 없는 메서드 요청인 경우, '500 서버 오류'를 보게 될 겁니다. 그렇지만 일단은 그냥 둡시다.

## 첫 번째 웹 API 테스트하기

이제 코드 조각을 보여주는 서버를 구동해 봅시다.

셸에서 빠져나가,

```bash
quit()
```

Django의 개발 서버를 띄웁니다.

```bash
python manage.py runserver

Validating models...

0 errors found
Django version 1.4.3, using settings 'tutorial.settings'
Development server is running at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```

다른 터미널 창에서 서버를 테스트합시다.
테스트에는 [curl](http://curl.haxx.se/)이나 [httpie](https://github.com/jakubroztocil/httpie#installation)를 사용할 수 있습니다. Httpie는 파이썬으로 작성된 사용자 친화적인 http 클라이언트입니다. 설치해 보죠.

httpie는 pip로 설치하면 됩니다.

```bash
pip install httpie
```

마지막으로 코드 조각 전체를 가져와 봅시다.

```bash
http http://127.0.0.1:8000/snippets/

HTTP/1.1 200 OK
...
[
  {
    "id": 1,
    "title": "",
    "code": "foo = \"bar\"\n",
    "linenos": false,
    "language": "python",
    "style": "friendly"
  },
  {
    "id": 2,
    "title": "",
    "code": "print \"hello, world\"\n",
    "linenos": false,
    "language": "python",
    "style": "friendly"
  }
]
```

id를 지정하여 특정 코드 조각만 가져올 수도 있습니다.

```bash
http http://127.0.0.1:8000/snippets/2/

HTTP/1.1 200 OK
...
{
  "id": 2,
  "title": "",
  "code": "print \"hello, world\"\n",
  "linenos": false,
  "language": "python",
  "style": "friendly"
}
```

웹 브라우저에서도 똑같은 json 데이터를 확인하실 수 있을 겁니다.

## 지금 어디까지 왔을까요?

지금까지 잘 해 왔습니다. 시리얼라이저 API를 만들 땐 Django 폼 API나 일반적인 Django 뷰와 비슷하다고 느꼈을 겁니다.

우리가 만든 API 뷰는 아직까진 `json`을 응답하는 일 외에는 별다른 일을 하지 않으며, 몇몇 경우에는 (웹 API의 기능임에도) 에러도 발생하고 있습니다.

[튜토리얼 2부](https://raccoonyy.github.io/drf3-tutorial-2)에서는 API를 개선해보겠습니다.



# DRF3 튜토리얼 2 - 요청과 응답

![DRF3 튜토리얼 2 - 요청과 응답](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)

원문 - [Requests and Responses](http://www.django-rest-framework.org/tutorial/2-requests-and-responses/)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 2: 요청과 응답

이번 튜토리얼부터 REST 프레임워크의 진정한 핵심부를 다루고자 합니다. 먼저, 두 가지 핵심 요소를 소개하죠.

## 요청(Request) 객체

REST 프레임워크의 `Request` 객체는 평범한 `HttpRequest` 객체를 확장하여 좀더 유연하게 요청을 파싱합니다. `Request` 객체의 핵심부는 `request.data` 속성입니다. 이 속성은 `request.POST`와 비슷하지만 웹 API에 좀더 적합합니다.

```python
request.POST  # 폼 데이터만 다루며, 'POST' 메서드에서만 사용 가능
request.data  # 아무 데이터나 다룰 수 있고, 'POST'뿐만 아니라 'PUT'과 'PATCH' 메서드에서도 사용 가능
```

## 응답(Response) 객체

REST 프레임워크에는 `Response` 객체도 존재합니다. 이 객체는 `TemplateResponse` 타입이며, 렌더링되지 않은 콘텐츠를 불러와 클라이언트에게 리턴할 콘텐츠 형태로 변환합니다.

```python
return Response(data)  # 클라이언트가 요청한 형태로 콘텐트를 렌더링함
```

## 상태 코드

여러분이 만든 뷰에서 숫자 형태의 HTTP 상태 코드를 사용하는 경우, 읽기에도 어려울 뿐더러 코드에 오류가 있더라도 발견하기가 어렵습니다. REST 프레임워크에서는 각 상태 코드에 대해 좀더 명확한 식별자를 제공합니다. 예를 들어 `status` 모듈의 `HTTP_400_BAD_REQUEST` 같은 식별자 같이요. 숫자로 된 식별자를 사용하기보다는 문자 형태의 식별자를 사용하세요.

## API 뷰 감싸기

REST 프레임워크는 API 뷰를 작성하는 데 사용할 수 있는 두 가지 래퍼를 제공합니다.

1. `@api_view` 데코레이터를 함수 기반 뷰에서 사용할 수 있습니다.
2. `APIView` 클래스는 클래스 기반 뷰에서 사용할 수 있습니다.

이 래퍼들은 뷰에서 받은 `Request`에 몇몇 기능을 더하거나, 콘텐츠가 잘 변환되도록 `Response`에 특정 context를 추가합니다.

또한 때에 따라 `405 Method Not Allowed`를 반환하거나, `request.data`가 깨진 경우 `ParseError` 예외를 던지는 등의 일도 수행합니다.

## 이 모든 것을 한 군데 모으기

그럼 이제 이 두 요소를 사용하여 뷰를 작성해봅시다.

`views.py`에 `JSONResponse` 클래스는 더이상 필요하지 않으니 지웁시다. 그리고 뷰 코드를 조금 리팩터링합니다.

```python
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer


@api_view(['GET', 'POST'])
def snippet_list(request):
    """
    코드 조각을 모두 보여주거나 새 코드 조각을 만듭니다.
    """
    if request.method == 'GET':
        snippets = Snippet.objects.all()
        serializer = SnippetSerializer(snippets, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = SnippetSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
```

예제에 비해 목록 뷰가 조금 개선되었습니다. 조금 간단해지면서 폼 API와 유사하다는 느낌을 줍니다. 또한 이름 형태의 상태 코드를 사용하여 의미를 명확히 했습니다.

이제 `views.py`를 열어, 코드 조각 하나를 담당하는 뷰를 수정합시다.

```python
@api_view(['GET', 'PUT', 'DELETE'])
def snippet_detail(request, pk):
    """
    코드 조각 조회, 업데이트, 삭제
    """
    try:
        snippet = Snippet.objects.get(pk=pk)
    except Snippet.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = SnippetSerializer(snippet)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = SnippetSerializer(snippet, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        snippet.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
```

이 또한 어디선가 본 듯 합니다. 일반적인 Django 뷰와 크게 다르지 않죠?

여기서 특정 콘텐츠 형태에 대한 요청이나 응답을 명시적으로 연결하지 않았음을 주목하세요. `request.data`는 `json` 요청 뿐만 아니라 `yaml`과 같은 다른 포맷도 다룰 수 있습니다. 응답 객체에 데이터를 담아 리턴하는 것과 비슷하면서도, REST 프레임워크에서는 우리가 원하는 형태로 응답 객체를 렌더링해 줍니다.

## URL의 접미어를 통해 다른 포맷 제공하기

앞에서 보았듯이 하나의 콘텐츠 형태에 묶여 있지 않다는 응답 객체의 장점을 활용하기 위해, 우리 API에서도 여러 형태의 포맷을 제공해 봅시다. 포맷의 접미어를 URL 형태로 전달받으려면, 다음과 같은 URL을 다룰 수 있어야 합니다.

http://example.com/api/items/4.json

일단 `format` 키워드를 두 가지 뷰에 추가해 봅시다.

```python
def snippet_list(request, format=None):
def snippet_detail(request, pk, format=None):
```

그리고 이제 `urls.py`를 조금 수정하겠습니다. 기존의 URL에 `format_suffix_patterns`라는 패턴을 추가합니다.

```python
from django.conf.urls import patterns, url
from rest_framework.urlpatterns import format_suffix_patterns
from snippets import views

urlpatterns = [
    url(r'^snippets/$', views.snippet_list),
    url(r'^snippets/(?P<pk>[0-9]+)/$', views.snippet_detail),
]

urlpatterns = format_suffix_patterns(urlpatterns)
```

이 외에 더 수정한 부분은 없는데도, 코드는 간명해졌고, 사용자는 자신이 원하는 형태의 포맷을 전달 받을 수 있습니다.

## 어떻게 되었을까?

이제 [튜토리얼 1](https://raccoonyy.github.io/drf3-tutorial-1)에서 했던 것처럼 명령행에서 API를 테스트해봅시다. 앞에서 했던 것과 비슷하게 작동하는 것처럼 보이지만, 이번에는 잘못된 요청에도 잘 대응합니다.

전체 코드 조각 목록은 다음과 같이 받아올 수 있습니다.

```bash
http http://127.0.0.1:8000/snippets/

HTTP/1.1 200 OK
...
[
  {
    "id": 1,
    "title": "",
    "code": "foo = \"bar\"\n",
    "linenos": false,
    "language": "python",
    "style": "friendly"
  },
  {
    "id": 2,
    "title": "",
    "code": "print \"hello, world\"\n",
    "linenos": false,
    "language": "python",
    "style": "friendly"
  }
]
```

그리고 이제는 `Accept` 헤더를 사용하여 응답 받을 데이터의 포맷도 지정할 수 있습니다.

```bash
http http://127.0.0.1:8000/snippets/ Accept:application/json  # Request JSON
http http://127.0.0.1:8000/snippets/ Accept:text/html         # Request HTML
```

아니면 포맷 접미어를 붙여도 되고요.

```bash
http http://127.0.0.1:8000/snippets.json  # JSON suffix
http http://127.0.0.1:8000/snippets.api   # Browsable API suffix
```

비슷하게, `Content-Type` 헤더를 사용해도 응답 받을 데이터의 포맷을 지정할 수 있습니다.

```bash
# 데이터를 넘기면서 POST 요청
http --form POST http://127.0.0.1:8000/snippets/ code="print 123"

{
  "id": 3,
  "title": "",
  "code": "print 123",
  "linenos": false,
  "language": "python",
  "style": "friendly"
}

# JSON으로 POST 요청
http --json POST http://127.0.0.1:8000/snippets/ code="print 456"

{
    "id": 4,
    "title": "",
    "code": "print 456",
    "linenos": false,
    "language": "python",
    "style": "friendly"
}
```

자, 이제 브라우저에서도 확인해보세요. http://127.0.0.1:8000/snippets/

### 탐색 가능한 API

API는 클라이언트의 요청에 따라 데이터의 포맷을 결정하여 응답합니다. 따라서 웹 브라우저의 요청에 대해서는 기본적으로 HTML 형태로 응답해주게 됩니다. 이 덕분에 API를 웹 브라우저에서 탐색할 수 있습니다.

브라우저에서 탐색 가능함은 사용성 면에서 굉장히 유용하여, API를 더 쉽게 개발하고 사용하도록 도와줍니다. 또한 다른 개발자들이 API를 파악하고 사용할 때의 진입장벽을 획기적으로 낮춰 줍니다.

[브라우저에서 탐색 가능한 API](http://www.django-rest-framework.org/topics/browsable-api)에서 브라우저 탐색 기능에 대해 더 자세히 알아볼 수 있습니다.

## What's next?

[튜토리얼 3부](https://raccoonyy.github.io/drf3-tutorial-3)에서는 클래스 기반 뷰를 사용하면서, 일반적인 뷰에 비해 코드량이 얼마나 줄어드는지 확인하겠습니다.



# DRF3 튜토리얼 3 - 클래스 기반 뷰

![DRF3 튜토리얼 3 - 클래스 기반 뷰](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)

원문 - [Class Based Views](http://www.django-rest-framework.org/tutorial/3-class-based-views/)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 3: 클래스 기반 뷰

앞서 함수 기반으로 만들었던 API 뷰를 클래스 기반 뷰로도 만들 수 있습니다. 이는 일반적인 기능을 재사용하게 해주며 코드 중복([DRY](http://en.wikipedia.org/wiki/Don't_repeat_yourself))도 막아주기 때문에 굉장히 쓸모 있는 패턴입니다.

## 클래스 기반 뷰로 API 재작성하기

먼저 최상단 뷰를 클래스 기반 뷰로 재작성해봅시다. 이를 위해 `views.py`를 약간 리팩터링해야 합니다.

```python
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status


class SnippetList(APIView):
    """
    코드 조각을 모두 보여주거나 새 코드 조각을 만듭니다.
    """
    def get(self, request, format=None):
        snippets = Snippet.objects.all()
        serializer = SnippetSerializer(snippets, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = SnippetSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
```

좋습니다. 이전 코드와 거의 똑같이 보이지만 HTTP 메서드를 분리했기 때문에 좀더 좋습니다. 마찬가지로 `views.py` 파일을 열어, 코드 조각 하나를 담당하는 뷰도 수정해봅시다.

```python
class SnippetDetail(APIView):
    """
    코드 조각 조회, 업데이트, 삭제
    """
    def get_object(self, pk):
        try:
            return Snippet.objects.get(pk=pk)
        except Snippet.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        snippet = self.get_object(pk)
        serializer = SnippetSerializer(snippet)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        snippet = self.get_object(pk)
        serializer = SnippetSerializer(snippet, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        snippet = self.get_object(pk)
        snippet.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
```

훌륭합니다. 그렇지만 역시나 아직까진 함수 기반 뷰와 크게 다르지 않습니다.

이제 `urls.py`도 수정해야 합니다. 클래스 기반 뷰를 사용해야 하니까요.

```python
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from snippets import views

urlpatterns = [
    url(r'^snippets/$', views.SnippetList.as_view()),
    url(r'^snippets/(?P<pk>[0-9]+)/$', views.SnippetDetail.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)
```

자, 끝났습니다. 개발 서버를 실행해 보면 이전과 동일하게 작동하는 모습을 볼 수 있을 겁니다.

## 믹스인 사용하기

클래스 기반 뷰를 사용하여 얻는 가장 큰 이점은 기능들을 손쉽게 조합할 수 있다는 점입니다.

지금까지 사용한 생성/조회/업데이트/삭제 등의 명령은 일반적으로 모델을 사용할 때의 뷰와 비슷합니다. 이러한 보편적인 기능을 REST 프레임워크에서는 믹스인 클래스로 구현해두었습니다.

이제 뷰에 믹스인 클래스를 추가하는 모습을 살펴봅시다. `views.py` 파일입니다.

```python
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from rest_framework import mixins
from rest_framework import generics

class SnippetList(mixins.ListModelMixin,
                  mixins.CreateModelMixin,
                  generics.GenericAPIView):
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, **kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)
```

여기서 무슨 일이 벌어지는지 꼼꼼히 따져봅시다. `GenericAPIView`와 `ListModelMixin`, `CreateModelMixin`을 사용하여 뷰를 만들었습니다.

기본 뷰(`GenericAPIView`)는 핵심 기능을 제공하며, 믹스인 클래스들은 `.list()`나 `.create()` 기능을 제공합니다. 여기서는 이 기능들을 `get`과 `post` 메서드에 적절히 연결하였습니다. 이정도면 간단하죠?

```python
class SnippetDetail(mixins.RetrieveModelMixin,
                    mixins.UpdateModelMixin,
                    mixins.DestroyModelMixin,
                    generics.GenericAPIView):
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer

    def get(self, request, *args, **kwargs):
        return self.retrieve(request, *args, **kwargs)

    def put(self, request, *args, **kwargs):
        return self.update(request, *args, **kwargs)

    def delete(self, request, *args, **kwargs):
        return self.destroy(request, *args, **kwargs)
```

비슷하게, 여기서도 `GenericAPIView`는 핵심 기능을 제공하며, 나머지 믹스인들이 `.retrieve()`, `.update()`, `.destroy()` 기능을 제공합니다.

## 제네릭 클래스 기반 뷰 사용하기

믹스인 클래스를 사용하여 뷰의 코드를 꽤 많이 줄였지만, 더 줄일 수 있습니다. REST 프레임워크에서는 믹스인과 연결된 제네릭 뷰를 제공합니다. 이를 사용하면 `views.py` 파일이 굉장히 짧아집니다.

```python
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from rest_framework import generics


class SnippetList(generics.ListCreateAPIView):
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer


class SnippetDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer
```

보세요, 정말 간결하죠? 별 노력 없이 아주 많은 기능을 구현했는데도 코드는 더 깔끔하고 훨씬 Django다워졌습니다.

[튜토리얼 4부](https://raccoonyy.github.io/drf3-tutorial-4)에서는 인증과 권한을 API에서 어떻게 다룰지를 살펴보겠습니다.



# DRF3 튜토리얼 4 - 인증과 권한

![DRF3 튜토리얼 4 - 인증과 권한](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)

원문 - [Authentication & Permissions](http://www.django-rest-framework.org/tutorial/4-authentication-and-permissions)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 4: 인증과 권한

지금까지 우리가 만든 API에서는 누구라도 코드 조각을 편집하거나 삭제할 수 있습니다. 아무 제한이 없죠. 여기에 다음과 같은 고급 기능을 추가하고 싶습니다.

- 코드 조각은 만든 사람과 연관이 있다.
- 인증받은 사용자만 코드 조각을 만들 수 있다.
- 해당 코드 조각을 만든 사람만, 이를 편집하거나 삭제할 수 있다.
- 인증받지 않은 사용자는 '읽기 전용'으로만 사용 가능하다.

## 모델에 속성 추가하기

먼저 `Snippet` 모델을 조금 수정해보겠습니다.

필드를 두 개 추가합니다. 하나(owner)는 코드 조각을 만든 사람을 가리킵니다. 다른 하나는 하이라이트된 코드를 HTML 형태로 저장하는 데 사용됩니다.

`models.py`에 있는 `Snippet` 모델에 다음 내용을 추가하세요.

```python
owner = models.ForeignKey('auth.User', related_name='snippets')
highlighted = models.TextField()
```

그리고 모델이 저장될 때 하이라이트된 코드를 highlighed 필드에 저장해야 합니다. 코드 하이라이팅에는 `pygments` 라이브러리를 사용합니다.

필요한 라이브러리들을 임포트하고,

```python
from pygments.lexers import get_lexer_by_name
from pygments.formatters.html import HtmlFormatter
from pygments import highlight
```

모델에 `.save()` 메서드를 작성합니다.

```python
def save(self, *args, **kwargs):
    """
    `pygments` 라이브러리를 사용하여 하이라이트된 코드를 만든다.
    """
    lexer = get_lexer_by_name(self.language)
    linenos = self.linenos and 'table' or False
    options = self.title and {'title': self.title} or {}
    formatter = HtmlFormatter(style=self.style, linenos=linenos,
                              full=True, **options)
    self.highlighted = highlight(self.code, lexer, formatter)
    super(Snippet, self).save(*args, **kwargs)
```

코드를 모두 작성했다면 데이터베이스를 업데이트해야 합니다.

보통은 데이터베이스 마이그레이션을 작성하겠지만, 지금은 튜토리얼일 뿐이니 데이터베이스를 지우고 새로 만들겠습니다.

```bash
rm -f tmp.db db.sqlite3
rm -r snippets/migrations
python manage.py makemigrations snippets
python manage.py migrate
```

API를 테스트하는 데 사용할 사용자 계정을 만들고 싶다면, 다음과
같이 `createsuperuser` 명령을 사용하시면 됩니다.

```bash
python manage.py createsuperuser
```

## 사용자 모델에 엔드포인트 추가하기

사용자를 추가했으니 사용자를 보여주는 API도 추가합시다. `serializers.py` 파일에 새 시리얼라이저를 작성합니다.

```python
from django.contrib.auth.models import User

class UserSerializer(serializers.ModelSerializer):
    snippets = serializers.PrimaryKeyRelatedField(many=True, queryset=Snippet.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'snippets')
```

`'snippets'`는 사용자 모델과 **반대 방향**으로 이어져 있기 때문에 `ModelSerializer`에 기본적으로 추가되지 않습니다. 따라서 명시적으로 필드를 지정해주었습니다.

사용자와 관련된 뷰도 추가해야겠죠. 읽기 전용 뷰만 있으면 되니까, 제네릭 클래스 기반 뷰 중에서 `ListAPIView`와 `RetrieveAPIView`를 사용합시다.

```python
from django.contrib.auth.models import User


class UserList(generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer


class UserDetail(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
```

`UserSerializer` 클래스도 임포트하고요.

```python
from snippets.serializers import UserSerializer
```

마지막으로 뷰에 URL을 연결합시다. `urls.py` 파일에 다음 내용을 추가합니다.

```python
url(r'^users/$', views.UserList.as_view()),
url(r'^users/(?P<pk>[0-9]+)/$', views.UserDetail.as_view()),
```

## 사용자가 만든 코드 조각 연결하기

지금까지는 코드 조각을 만들었더라도, 해당 코드 조각을 만든 사용자와 아무 관계도 맺지 않았습니다. 사용자는 직렬화된 표현에 나타나지 않았고, 요청하는 측에서 지정하는 속성이었을 뿐입니다.

이를 해결하기 위해 코드 조각 뷰에서 `.perform_create()` 메서드를 오버라이딩합시다. 이 메서드는 인스턴스를 저장하는 과정을 조정하며, 따라서 요청이나 요청 URL에서 정보를 가져와 원하는 대로 다룰 수 있습니다.

`SnippetList` 뷰 클래스에 다음 내용을 추가합니다.

```python
def perform_create(self, serializer):
    serializer.save(owner=self.request.user)
```

우리가 만든 시리얼라이저의 `create()` 메서드는 검증한 요청 데이터에 더하여 `'owner'` 필드도 전달합니다.

## 시리얼라이저 업데이트하기

이제 코드 조각이, 해당 코드 조각을 작성한 사용자와 연결되었습니다. `SnippetSerializer`에도 이를 반영합시다. 다음 내용을 `serializers.py`의 `SnippetSerializer`에 추가합시다.

```python
owner = serializers.ReadOnlyField(source='owner.username')
```

**안내:** `Meta` 클래스의 필드 목록에도 `'owner'`를 추가해야 합니다.

이 필드에는 조금 재미있는 면이 있는데요. `source` 인자로는 특정 필드를 지정할 수 있습니다. 여기에는 직렬화된 인스턴스의 속성 뿐만 아니라 위의 코드에서처럼 마침표 표기 방식을 통해 특정 속성을 탐색할 수도 있습니다. 마치 Django의 템플릿 언어와 비슷하죠.

이 필드는 `CharField`나 `BooleanField`와는 달리 타입이 없는 `ReadOnlyField` 클래스로 지정했습니다. 타입이 없는 `ReadOnlyField`는 직렬화에 사용되었을 땐 언제나 읽기 전용이므로, 모델의 인스턴스를 업데이트할 때는 사용할 수 없습니다. `CharField(read_only=True)`도 이와 같은 기능을 수행합니다.

## 뷰에 요청 권한 추가하기

이렇게 해서 코드 조각이 사용자와 연결되었습니다. 이제 인증 받은 사용자만 코드 조각을 생성/업데이트/삭제해봅시다.

REST 프레임워크는 특정 뷰에 제한을 걸 수 있는 권한 클래스를 제공하고 있습니다. 그 중 한 가지인 `IsAuthenticatedOrReadOnly`는 인증 받은 요청에 읽기와 쓰기 권한을 부여하고, 인증 받지 않은 요청에 대해서는 읽기 권한만 부여합니다.

뷰 파일에 다음 내용을 추가합니다.

```python
from rest_framework import permissions
```

그리고 `SnippetList` 클래스와 `SnippetDetail` 클래스에 **모두** 다음 속성을 추가합니다.

```python
permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
```

## 탐색 가능한 API에 로그인 추가하기

여러분이 지금 시점에 브라우저에서 API에 접속해본다면 더이상 새 코드 조각을 만들 수 없다는 사실을 알 수 있을 겁니다. 이를 해결하려면 사용자 로그인 기능이 필요합니다.

URL 설정 파일인 `urls.py`를 수정하면 탐색 가능한 API에 사용할 로그인 뷰를 추가할 수 있습니다.

다음 내용을 `urls.py` 파일의 첫 부분에 추가합니다.

```python
from django.conf.urls import include
```

그리고 파일의 끝 부분에 다음 내용을 추가합니다. 탐색 가능한 API의 로그인 뷰와 로그아웃 뷰에 사용되는 url 패턴입니다.

```python
urlpatterns += [
    url(r'^api-auth/', include('rest_framework.urls',
                               namespace='rest_framework')),
]
```

url 패턴에서 `r'^api-auth/'` 부분은 여러분이 사용하고 싶은 URL을 나타냅니다. 여기에는 한 가지 제약만 따르는데, 바로 namespace에 `'rest_framework'`를 지정해야 한다는 점입니다.

다시 브라우저로 돌아가 API에 접근해 보면 오른쪽 상단에 'Login' 링크가 보일 겁니다. 이제 앞에서 만들었던 사용자로 로그인하면 코드 조각을 만들 수 있습니다.

코드 조각을 몇 개 만든 후에는 '/users/'에도 가보세요. 해당 사용자가 만든 코드 조각 목록이 'snippets' 필드에 포함되어 있을 겁니다.

## 객체 수준에서 권한 설정하기

코드 조각은 아무나 볼 수 있어야 하지만, 업데이트와 삭제는 해당 코드를 만든 사용자만 할 수 있어야 합니다.

이를 위해 커스텀 권한을 만들어 봅시다.

snippets 앱 안에 `permissions.py` 파일을 만들고 다음 내용을 입력합니다.

```python
from rest_framework import permissions


class IsOwnerOrReadOnly(permissions.BasePermission):
    """
    객체의 소유자에게만 쓰기를 허용하는 커스텀 권한
    """

    def has_object_permission(self, request, view, obj):
        # 읽기 권한은 모두에게 허용하므로,
        # GET, HEAD, OPTIONS 요청은 항상 허용함
        if request.method in permissions.SAFE_METHODS:
            return True

        # 쓰기 권한은 코드 조각의 소유자에게만 부여함
        return obj.owner == request.user
```

이렇게 만든 커스텀 권한을 코드 조각 인스턴스에 추가합시다. `SnippetDetail` 클래스에 `permission_classes` 속성을 추가합니다.

```python
permission_classes = (permissions.IsAuthenticatedOrReadOnly,
                      IsOwnerOrReadOnly,)
```

당연히 `IsOwnerOrReadOnly` 클래스를 임포트해야겠고요.

```python
from snippets.permissions import IsOwnerOrReadOnly
```

다시 브라우저로 돌아가 보면, 코드 조각에 대한 'DELETE'와 'PUT' 기능은 해당 사용자에게만 나타날 겁니다.

## API에 인증 붙이기

API에 권한을 설정했으므로, 이제는 코드 조각을 수정할 수 있는 인증 절차가 필요합니다. 지금까지는 [인증 클래스](http://www.django-rest-framework.org/api-guide/authentication)를 만들지 않고 기본으로 제공되는 `SessionAuthentication`과 `BasicAuthentication`을 사용했습니다.

웹 브라우저로 API를 사용하는 경우, 로그인을 하면 브라우저의 세션에 인증 정보가 저장됩니다.

프로그램 상에서 API를 사용하는 경우, 인증에 필요한 내용을 명시적으로 전달해야만 합니다.

인증 없이 코드 조각을 생성하려는 경우, 다음과 같이 에러를 보여줍니다.

```bash
http POST http://127.0.0.1:8000/snippets/ code="print 123"

{
    "detail": "Authentication credentials were not provided."
}
```

사용자 계정과 비밀번호를 포함하여 요청한다면, 이 요청은 성공합니다.

```bash
http -a tom:password POST http://127.0.0.1:8000/snippets/ code="print 789"

{
    "id": 5,
    "owner": "tom",
    "title": "foo",
    "code": "print 789",
    "linenos": false,
    "language": "python",
    "style": "friendly"
}
```

## 요약

이렇게 해서 웹 API 위에 권한들이 잘 설정되었고, 사용자의 코드 조각에 대한 엔드 포인트도 완성되었습니다.

[튜토리얼 5부](https://raccoonyy.github.io/drf3-tutorial-5)에서는 이 모든 것을 한 데 엮어 하이라이트된 코드 조각을 보여주는 HTML 엔드 포인트를 만들고, API에서는 관련 모델에 대한 링크도 제공해 보겠습니다.



# DRF3 튜토리얼 5 - 관계 & 하이퍼링크 API

![DRF3 튜토리얼 5 - 관계 & 하이퍼링크 API](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)

원문 - [Relationships and Hyperlinked APIs](http://www.django-rest-framework.org/tutorial/5-relationships-and-hyperlinked-apis)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 5: 관계 & 하이퍼링크 API

지금까지 우리가 만든 API에서 '관계'는 주 키(primary key)로 나타나고 있었습니다. 이번 튜토리얼에서는 API의 발견성(discoverability)과 응집력(cohesion)을 향상시키고자 관계를 하이퍼링크로 나타내 보겠습니다.

## API의 최상단에 대한 엔드 포인트 만들기

지금까지 '코드 조각'과 '사용자'에 대한 엔드 포인트를 만들었지만, 아직까지 이렇다 할 API의 시작점은 없었습니다. 이를 만들기 위해 평범한 함수 기반 뷰와 `@api_view` 데코레이터([2부 참고](https://raccoonyy.github.io/drf3-tutorial-2/))를 사용해 보겠습니다. `snippets/views.py` 파일을 여세요.

```python
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.reverse import reverse


@api_view(('GET',))
def api_root(request, format=None):
    return Response({
        'users': reverse('user-list', request=request, format=format),
        'snippets': reverse('snippet-list', request=request, format=format)
    })
```

여기서 URL을 만드는 데 `reverse` 함수를 사용한 점을 주목하세요.

## 코드 조각의 하이라이트 버전에 대한 엔드 포인트 만들기

우리 API에서 아직까지 만들지 않은 부분은 바로, 코드 조각의 하이라이트 버전을 볼 수 있는 방법입니다.

API의 다른 부분과는 달리 이번에는 JSON 대신 HTML 형태로 나타내겠습니다. REST 프레임워크에서 HTML로 렌더링하는 방식은 두 가지 정도가 있는데, 하나는 템플릿을 사용하는 것이고, 다른 하나는 미리 렌더링된 HTML을 사용하는 것입니다. 이번에는 후자가 더 어울려 보입니다.

하이라이트된 코드 조각을 보여주려고 할 때 주의해야 할 점 하나는, 우리가 사용할 만한 제네릭 뷰가 없다는 것입니다. 오브젝트 자체가 아니라, 오브젝트의 속성 하나를 반환할 것이기 때문입니다.

제네릭 뷰 대신 평범한 클래스를 사용하고, `.get()` 메서드를 구현하겠습니다. `snippets/views.py` 파일에 다음 내용을 추가하세요.

```python
from rest_framework import renderers
from rest_framework.response import Response

class SnippetHighlight(generics.GenericAPIView):
    queryset = Snippet.objects.all()
    renderer_classes = (renderers.StaticHTMLRenderer,)

    def get(self, request, *args, **kwargs):
        snippet = self.get_object()
        return Response(snippet.highlighted)
```

지금까지 그래왔듯이, 새 뷰는 URL 설정에서도 연결해야 합니다.

`snippets/urls.py` 파일에서 최상단 url을 방금 만든 뷰로 연결합시다.

```python
url(r'^$', views.api_root),
```

하이라이트된 코드 조각을 볼 수 있는 url에 대한 패턴도 추가합니다.

```python
url(r'^snippets/(?P<pk>[0-9]+)/highlight/$', views.SnippetHighlight.as_view()),
```

## 하이퍼링크로 API 연결하기

요소들 사이의 관계를 다루는 일은 웹 API 설계에서 또 하나의 도전 과제입니다. 관계를 표현하는 방법은 꽤 다양합니다.

- 주 키(primary key)
- 하이퍼링크
- 관계 요소의 식별 가능한 슬러그(slug) 필드
- 관계 요소의 기본 문자열 표현
- 포함된 관계 요소에 대한 표현
- 이 외에도 사용자화된 표현

REST 프레임워크에서는 모든 방법을 지원합니다. 관계 혹은 역관계에 적용하거나, 제네릭 외부 키(foreign key)처럼 사용자화된 manager에 적용할 수도 있습니다.

여기서는 하이퍼링크 방식을 채택하겠습니다. 이러게 하려면 기존에 사용했던 `ModelSerializer`를 `HyperlinkedModelSerializer`로 변경해야 합니다.

`HyperlinkedModelSerializer`는 다음과 같은 점들이 다릅니다.

- `pk` 필드는 기본 요소가 아닙니다.
- `HyperlinkedIdentityField`를 사용하는 `url` 필드가 포함되어 있습니다.
- 관계는 `PrimaryKeyRelatedField` 대신 `HyperlinkedRelatedField`를 사용하여 나타냅니다.

기존 시리얼라이저에 하이퍼링크를 추가하기는 쉽습니다. `snippets/serializers.py` 파일에 다음 내용을 추가합시다.

```python
class SnippetSerializer(serializers.HyperlinkedModelSerializer):
    owner = serializers.ReadOnlyField(source='owner.username')
    highlight = serializers.HyperlinkedIdentityField(view_name='snippet-highlight', format='html')

    class Meta:
        model = Snippet
        fields = ('url', 'highlight', 'owner',
                  'title', 'code', 'linenos', 'language', 'style')


class UserSerializer(serializers.HyperlinkedModelSerializer):
    snippets = serializers.HyperlinkedRelatedField(many=True, view_name='snippet-detail', read_only=True)

    class Meta:
        model = User
        fields = ('url', 'username', 'snippets')
```

이 코드에서는 새롭게 `'highlight'` 필드가 추가되었습니다. 이 필드는 `url` 필드와 같은 타입이며, `'snippet-detail'` url 패턴 대신 `'snippet-highlight'` url 패턴을 가리킵니다.

앞에서 URL의 format 접미어로 `'.json'`을 붙였듯이, `highlight` 필드에는 format 접미어로 `'.html'`을 붙였습니다.

## URL 패턴에 이름 붙이기

하이퍼링크 API를 만들고 싶다면, URL 패턴에 이름을 붙여야 합니다. 어떤 패턴들인지 살펴봅시다.

- API의 최상단은 `'user-list'`와 `'snippet-list'`를 가리킵니다.
- 코드 조각 시리얼라이저에는 `'snippet-highlight'`를 가리키는 필드가 존재합니다.
- 사용자 시리얼라이저에는 `'snippet-detail'`을 가리키는 필드가 존재합니다.
- 코드 조각 시리얼라이저와 사용자 시리얼라이저에는 `'url'` 필드가 존재합니다. 이 필드는 기본적으로 `'{모델_이름}-detail'`을 가리키며 따라서 `'snippet-detail'`과 `'user-detail'`을 가리킵니다.

이 이름들을 URL 설정에 넣었다면 `snippets/urls.py` 파일은 다음과 같을 겁니다.

```python
from django.conf.urls import url, include

# API endpoints
urlpatterns = format_suffix_patterns([
    url(r'^$', views.api_root),
    url(r'^snippets/$',
        views.SnippetList.as_view(),
        name='snippet-list'),
    url(r'^snippets/(?P<pk>[0-9]+)/$',
        views.SnippetDetail.as_view(),
        name='snippet-detail'),
    url(r'^snippets/(?P<pk>[0-9]+)/highlight/$',
        views.SnippetHighlight.as_view(),
        name='snippet-highlight'),
    url(r'^users/$',
        views.UserList.as_view(),
        name='user-list'),
    url(r'^users/(?P<pk>[0-9]+)/$',
        views.UserDetail.as_view(),
        name='user-detail')
])

# 탐색 가능한 API를 위한 로그인/로그아웃 뷰
urlpatterns += [
    url(r'^api-auth/', include('rest_framework.urls',
                               namespace='rest_framework')),
]
```

## 페이징 기능 추가하기

사용자나 코드 조각의 목록이 꽤 긴 경우가 있을 겁니다. 따라서 결과물을 여러 페이지로 나누어, API 클라이언트 측에서 각 페이지를 하나씩 차례대로 읽어가도록 만들면 좋겠습니다.

`tutorial/settings.py` 파일을 열어 페이징 설정의 기본 값을 바꿔 봅시다. 다음을 추가합니다.

```python
REST_FRAMEWORK = {
    'PAGE_SIZE': 10
}
```

REST 프레임워크의 모든 설정은 'REST_FRAMEWORK'라는 딕셔너리에 넣어야 합니다. 이렇게 해야 프로젝트의 다른 설정들과 분리할 수 있습니다.

필요에 따라 페이징 스타일을 바꿀 수도 있지만 여기서는 기본 스타일을 따르겠습니다.

## 탐색 가능한 API

탐색 가능한 API를 브라우저에서 열어서 링크들을 이리 저리 눌러보면, API의 구석구석을 둘러볼 수 있을 겁니다.

또한 코드 조각 인스턴스의 '하이라이트 버전'을 살펴볼 수도 있겠습니다. (HTML 형태입니다.)

[튜토리얼 6부](https://raccoonyy.github.io/drf3-tutorial-6)에서는 뷰셋과 라우터를 통해 코드 양을 줄이는 방법을 배우겠습니다.

> 번역에 도움을 주신 파이님께 감사합니다.



# DRF3 튜토리얼 6 - 뷰셋과 라우터

![DRF3 튜토리얼 6 - 뷰셋과 라우터](https://raccoonyy.github.io/content/images/size/w2000/2015/12/drf-logo.png)

원문 - [Viewsets and Routers](http://www.django-rest-framework.org/tutorial/6-viewsets-and-routers)

번역을 허락해 준 [Tom Christie](https://twitter.com/_tomchristie)에게 고마움을 전합니다.

------

# 튜토리얼 6: 뷰셋 & 라우터

REST 프레임워크는 `ViewSets`이라는 추상 클래스를 제공합니다. 이를 통해 개발자는 API의 상호작용이나 상태별 모델링에 집중할 수 있고, URL 구조는 기본 관례에 따라 자동으로 설정됩니다.

`ViewSet` 클래스는 `View` 클래스와 거의 비슷하지만, `get`과 `put` 메서드는 지원하지 않고 `read`와 `update` 메서드를 지원합니다.

`ViewSet` 클래스는 따지고 보면, 앞 장에서 만든 핸들러 메서드가 실제 뷰로 구체화될 때 이를 연결해주기만 합니다. 이때 보통은 `Router` 클래스를 사용하여 복잡한 URL 설정을 처리합니다.

## 뷰셋을 사용하여 리팩터링하기

지금까지 만든 뷰들을 살펴보면서 뷰셋을 사용해서 리팩터링을 해봅시다.

가장 먼저 리팩터링할 뷰는 `UserList`와 `UserDetail` 뷰입니다. `UserViewSet` 하나로 모아보죠. 두 뷰의 코드를 삭제한 다음 아래의 클래스 하나를 입력합니다.

```python
from rest_framework import viewsets

class UserViewSet(viewsets.ReadOnlyModelViewSet):
    """
    이 뷰셋은 `list`와 `detail` 기능을 자동으로 지원합니다
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer
```

여기서 사용한 `ReadOnlyModelViewSet` 클래스는 '읽기 전용' 기능을 자동으로 지원합니다. `queryset`과 `serializer_class` 속성은 여전히 설정을 해야 하지만, 두 개의 클래스에 중복으로 설정할 필요는 없어졌습니다.

다음으로는 `SnippetList`와 `SnippetDetail`, `SnippetHighlight` 뷰를 리팩터링해보죠. 이 뷰들을 삭제하고, 아래의 클래스를 입력합니다.

```python
from rest_framework.decorators import detail_route

class SnippetViewSet(viewsets.ModelViewSet):
    """
    이 뷰셋은 `list`와 `create`, `retrieve`, `update`, 'destroy` 기능을 자동으로 지원합니다

	여기에 `highlight` 기능의 코드만 추가로 작성했습니다
    """
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,
                          IsOwnerOrReadOnly,)

    @detail_route(renderer_classes=[renderers.StaticHTMLRenderer])
    def highlight(self, request, *args, **kwargs):
        snippet = self.get_object()
        return Response(snippet.highlighted)

    def perform_create(self, serializer):
            serializer.save(owner=self.request.user)
```

이번에는 읽기 기능과 쓰기 기능을 모두 지원하기 위해 `ModelViewSet` 클래스를 사용했습니다.

추가한 `highlight` 기능에는 여전히 `@detail_route` 데코레이터를 사용했습니다. 이 데코레이터는 `create`나 `update`, `delete`에 해당하지 않는 기능에 대해 사용하면 됩니다.

`@detail_route` 데코레이터를 사용한 기능은 기본적으로 `GET` 요청에 응답합니다. `methods` 인자를 설정하면 `POST` 요청에도 응답할 수 있습니다.

추가 기능의 URL은 기본적으로 메서드 이름과 같습니다. 이를 변경하고 싶다면 데코레이터에 `url_path` 인자를 설정하면 됩니다.

## 뷰셋과 주소를 명시적으로 연결하기

핸들러 메서드는 단지 URL 설정과 연결하는 기능만 담당합니다. 물 밑에서 어떤 일들이 벌어지는지 알아보고자, 여기서는 먼저 뷰셋의 뷰들을 명시적으로 적어보겠습니다.

`urls.py` 파일에서 `ViewSet` 클래스를 실제 뷰(concrete view)와 연결합니다.

```python
from snippets.views import SnippetViewSet, UserViewSet, api_root
from rest_framework import renderers

snippet_list = SnippetViewSet.as_view({
    'get': 'list',
    'post': 'create'
})
snippet_detail = SnippetViewSet.as_view({
    'get': 'retrieve',
    'put': 'update',
    'patch': 'partial_update',
    'delete': 'destroy'
})
snippet_highlight = SnippetViewSet.as_view({
    'get': 'highlight'
}, renderer_classes=[renderers.StaticHTMLRenderer])
user_list = UserViewSet.as_view({
    'get': 'list'
})
user_detail = UserViewSet.as_view({
    'get': 'retrieve'
})
```

`ViewSet` 클래스의 뷰들을 HTTP 메서드에 따라 어떻게 실제 뷰와 연결했는지 살펴보세요.

이제 실제 뷰와 URL을 연결해보겠습니다.

```python
urlpatterns = format_suffix_patterns([
    url(r'^$', api_root),
    url(r'^snippets/$', snippet_list, name='snippet-list'),
    url(r'^snippets/(?P<pk>[0-9]+)/$', snippet_detail, name='snippet-detail'),
    url(r'^snippets/(?P<pk>[0-9]+)/highlight/$', snippet_highlight, name='snippet-highlight'),
    url(r'^users/$', user_list, name='user-list'),
    url(r'^users/(?P<pk>[0-9]+)/$', user_detail, name='user-detail')
])
```

## 라우터 사용하기

`View` 클래스 대신 `ViewSet` 클래스를 사용했기 때문에, 이제는 URL도 설정할 필요가 없습니다. `Router` 클래스를 사용하면 뷰 코드와 뷰, URL이 관례적으로 자동 연결됩니다. 단지 뷰를 라우터에 적절히 등록해주기만 하면 됩니다. 그러면 REST 프레임워크가 알아서 다 합니다.

`urls.py` 파일을 다음과 같이 고쳐보죠.

```python
from django.conf.urls import url, include
from snippets import views
from rest_framework.routers import DefaultRouter

# 라우터를 생성하고 뷰셋을 등록합니다
router = DefaultRouter()
router.register(r'snippets', views.SnippetViewSet)
router.register(r'users', views.UserViewSet)

# 이제 API URL을 라우터가 자동으로 인식합니다
# 추가로 탐색 가능한 API를 구현하기 위해 로그인에 사용할 URL은 직접 설정을 했습니다
urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]
```

라우터에 뷰셋을 등록하는 일은 url 패턴 설정하기와 비슷합니다. 여기서는 두 개를 등록했는데요. 뷰들에 사용할 URL의 접두어와 뷰셋입니다.

`DefaultRouter` 클래스는 API의 최상단 뷰를 자동으로 생성해주므로, `views` 모듈에 있는 `api_root` 메서드와 연결했던 URL도 삭제하였습니다.

## 뷰? 뷰셋? 장단점 비교하기

뷰셋은 유용한 추상화입니다. API 전반에 걸쳐 일관적인 URL 관례를 구현할 수 있고 작성할 코드 양은 최소한으로 유지할 수 있어서, URL 설정에 낭비될 정성을 API의 상호작용과 표현 자체에 쏟을 수 있습니다.

하지만 이것이 항상 옳다는 뜻은 아닙니다. 클래스 기반 뷰와 함수 기반 뷰에 각각 장단점이 있듯이 말이죠. 뷰셋을 사용하면 명확함이 좀 약해집니다.

## 회고

정말 적은 양의 코드만으로 pastebin과 같은 웹 API를 구현했습니다. 이 API는 웹 브라우저를 완벽히 지원하고, 인증 기능도 있고, 오브젝트별로 권한도 설정되며 다양한 형태로 렌더링됩니다.

지금까지 기본 Django 뷰에서 시작하여 기능들을 점진적으로 만드는 설계 과정을 차근차근 살펴보았습니다.

최종 코드는 [GitHub](https://github.com/tomchristie/rest-framework-tutorial)에서 볼 수 있고, [라이브 데모](http://restframework.herokuapp.com/)에서 작동 모습을 확인할 수도 있습니다.

## 더 많은 것들

이제 튜토리얼이 끝났습니다. REST 프레임워크에 대해 더 알고 싶다면 다음 링크들로 시작하면 좋을 겁니다.

- [GitHub](https://github.com/tomchristie/django-rest-framework)에서 이슈를 등록하거나 리뷰하고, pull requests를 작성해 보세요.
- [REST 프레임워크 사용자 모임](https://groups.google.com/forum/?fromgroups#!forum/django-rest-framework)에 가입해서 활동해 보세요.
- 창시자의 [트위터](https://twitter.com/_tomchristie)를 팔로우하고 인사를 나누세요.

**이제 멋진 무언가를 만들어 보세요.**

> 번역에 도움을 주신 목돌님과 조청님께 감사합니다.



# Django에서 리턴한 json 파일의 한글이 깨져 보일 때

Django의 HttpResponse를 사용하여 json 형태를 보내주면 한글이 깨지는 경우가 있습니다. 이럴 땐 다음처럼 content_type을 설정해주면 잘 나오네요.

```language-python
HttpResponse(
    json_context,
    content_type=u"application/json; charset=utf-8",
    status=status)
```