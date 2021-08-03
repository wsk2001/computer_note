# Django-navutils

출처: https://github.com/agateblue/django-navutils

**Note**:  이 패키지는 아직 베타 버전입니다. 내 자신의 몇 가지 프로젝트에서 성공적으로 사용되었습니다. 그러나 API는 첫 번째 주요 버전이 출시 될 때까지 이전 버전과 호환되지 않는 변경 사항이있을 수 있습니다.

Django-navutils는 django 프로젝트 내에서 메뉴와 이동 경로를 처리하기위한 경량 패키지입니다.

- [Features](https://github.com/agateblue/django-navutils#features)
- [Requirements](https://github.com/agateblue/django-navutils#requirements)
- [Install](https://github.com/agateblue/django-navutils#install)
- Usage
  - [Menus](https://github.com/agateblue/django-navutils#menus)
  - [Breadcrumbs](https://github.com/agateblue/django-navutils#breadcrumbs)
- [Changelog](https://github.com/agateblue/django-navutils#changelog)
- [License](https://github.com/agateblue/django-navutils#license)

## [Features](https://github.com/agateblue/django-navutils#id1)

- 관련된 데이터베이스 없음 (원하지 않는 한) : 메뉴와 이동 경로는 평범한 오래된 파이썬 코드입니다.
- 고도로 사용자 정의 가능
- 조건부 메뉴 항목 표시 : 인증 된 사용자,  익명,  직원들 ,  맞춤 기준!
- I18n 친화적 : 일반적인 django 번역 메커니즘에 의존 할 수 있습니다.
- 무제한 메뉴
- 반자동 현재 메뉴 노드 감지

## [Requirements](https://github.com/agateblue/django-navutils#id2)

- Python >= 2.7 or >= 3.3
- Django >= 1.7

메뉴 시스템은 모든 프로젝트에 통합 될 수 있지만 이동 경로 부분에서는 클래스 기반보기를 사용해야합니다.

## [Install](https://github.com/agateblue/django-navutils#id3)

패키지는 pip에서 사용할 수 있으며 `pip install django-navutils` 를 통해 설치할 수 있습니다.

또한 설정(`settings.INSTALLED_APPS`)에 `navutils`를 추가해야합니다.

또한 settings.CONTEXT_PROCESSORS에 다음을 추가하십시오.

``` python
CONTEXT_PROCESSORS = (
    # ...
    'navutils.context_processors.menus',
)
```

## [Usage](https://github.com/agateblue/django-navutils#id4)

### [Menus](https://github.com/agateblue/django-navutils#id5)

navutils는 메뉴 및 노드 인스턴스를 사용하는 메뉴를 나타내며 각 메뉴는 메뉴 링크를 나타내는 노드 인스턴스 모음입니다. 노드에는 노드 인스턴스 인 자식이있을 수 있습니다.

최소한의 예를 보겠습니다.

`yourapp/menu.py`:

``` python
from navutils import menu

main_menu = menu.Menu('main')
menu.register(main_menu)

# 모두에게 보여 질 것입니다
blog = menu.Node(id='blog', label='Blog', pattern_name='blog:index')
main_menu.register(blog)

# pattern_name 인수로 생성 된 노드는 django reverse를 사용합니다.
assert blog.get_url() == '/blog'

# 복귀를 비활성화하려면 url 인수를 사용하십시오.
django = menu.Node(id='django',
                   label='Django project',
                   url='http://djangoproject.com',
                   link_attrs={'target': '_blank'})

# 각 노드 인스턴스는 임의의 수의 자식을 받아 들일 수 있습니다
blog.add(
    menu.Node(id='last_entries',
              label='Last entries',
              pattern_name='blog:last_entries')
)
blog.add(
    menu.Node(id='archives', label='Archives', pattern_name='blog:archives')
)

# 익명 사용자에게만 표시됩니다.
login = menu.AnonymousNode(id='login',
                           label='Login',
                           pattern_name='accounts_login',
                           link_attrs={'class': 'big-button'})
main_menu.register(login)

# 인증 된 사용자에게만 표시됩니다.
logout = menu.AuthenticatedNode(id='logout',
                                label='Logout',
                                pattern_name='accounts_logout')
main_menu.register(logout)

```

`yourapp/templates/index.html`

``` html
{% load navutils_tags %}
{% render_menu menu=menus.main user=request.user %}
```

익명 사용자의 경우 다음과 같이 출력됩니다.

``` html
<nav class="main-menu">
    <ul>
        <li class="has-children menu-item">
            <a href="/blog">Blog<a>
            <ul class="sub-menu">
                <li class="menu-item">
                    <a href="/blog/latest">Latest entries</a>
                </li>
                <li class="menu-item">
                    <a href="/blog/archives">Archives</a>
                </li>
            </ul>
        </li>
        <li class="menu-item">
            <a href="http://djangoproject.com" target="_blank">Django project</a>
        </li>
        <li class="menu-item">
            <a href="/login" class="big-button">Login</a>
        </li>
    </ul>
</nav>

```

children 인수를 사용하여 상위 인스턴스에 직접 하위 노드를 설정할 수도 있습니다.

``` python
user = menu.Node(
    id='user',
    label='Greetings',
    pattern_name='user:dashboard',
    children=[
        menu.Node(id='logout', label='Logout', pattern_name='user:logout'),

        # you can nest children indefinitely
        menu.Node(
            id='settings',
            label='Settings',
            pattern_name='user:settings',
            children = [
                menu.Node(id='newsletter',
                          label='Newsletter',
                          pattern_name='user:settings:newsletter')
            ],
        ),
    ]
)
```

노드는 다양한 방법으로 사용자 정의 할 수 있습니다.

``` python
heavily_customized_node = menu.Node(
    'customized',
    'My custom menu',  # 임의의 템플릿 값도 지원합니다.
                       # like {{ request.user }}
    url='#',  # 임의의 템플릿 값도 지원합니다.
              # like {{ request.user }}

    # 렌더링시 노드에 적용될 사용자 정의 CSS 클래스
    css_class='custom-class',

    # the <a> title attribute
    title='click me!',

    # a path to a custom template for rendering the node
    # it's also possible to globally specify a custom template by naming
    # your template '<yourapp>/templates/navutils/node.html'
    template='myapp/menu/mynode.html',

    # extra context you can use in your node template
    context={'foo': 'bar'},

    # a dict of attributes that will be applied as HTML attributes on the <li>
    attrs = {'style': 'background-color: white;'}

    # a dict of attributes that will be applied as HTML attributes on the <a>
    link_attrs = {'target': '_blank', 'data-something': 'fancy-stuff'}
)

```

#### Current node

어떤 방식 으로든 현재 노드를 강조하고 싶을 것입니다. Navutils는이를 달성하기 위해 상속 된 뷰 믹스를 제공합니다.

다음 메뉴를 가정합니다.

``` python
from navutils import menu

main_menu = menu.Menu(id='main')
menu.register(main_menu)

login = menu.Node(id='login', label='Login', pattern_name='account_login')
main_menu.register(login)

```

다음 코드를 사용하여 보기를 메뉴 노드에 바인딩 할 수 있습니다.

``` python
from navutils import MenuMixin

class Login(MenuMixin, TemplateView):
    current_menu_item = 'login'
```

내부적으로 믹스 인은 컨텍스트에 값을 전달하고 현재 클래스가 추가됩니다.
보기가 표시되면 로그인 노드에 추가하십시오. 컨텍스트에서 current_menu_item 키 아래의 노드 식별자를 수동으로 전달하는 한 django 함수 기반 뷰로 동일한 결과를 얻을 수 있습니다.

**노드 참조**

Navutils는 일반적인 사용 사례를 해결하는 몇 가지 노드 하위 클래스를 제공합니다.

##### Node

기본 노드 유형은 누구(anonymous ) 에게나 표시됩니다.

##### AnonymousNode

익명 사용자에게만 표시됩니다.

##### AuthenticatedNode

인증 된 사용자에게만 표시됩니다.

##### StaffNode

스태프 사용자/수퍼 유저에게만 표시됩니다.

##### PermissionNode

주어진 권한이있는 사용자에게 표시됩니다. 용법:

``` python
vip_node = menu.PermissionNode('vip',
                               label='VIP Area',
                               pattern_name='vip:index',
                               permission='access_vip_area')
```

##### AllPermissionsNode

권한 목록과 일치하는 사용자에게 표시됩니다. 용법:

``` python
permissions = ['myapp.access_vip_area', 'myapp.drink_champagne']
champagne_node = menu.AllPermissionsNode('champagne',
                                         label='Champagne!',
                                         pattern_name='vip:champagne',
                                         permissions=permissions)

```

##### AnyPermissionsNode

주어진 권한과 일치하는 사용자에게 표시됩니다. 용법:

``` python
permissions = ['myapp.can_party', 'myapp.can_have_fun']
have_a_good_time = menu.AnyPermissionsNode('good-time',
                                           label='Have a good time',
                                           pattern_name='good_time',
                                           permissions=permissions)

```

##### PassTestNode

사용자 지정 테스트와 일치하는 사용자에게 표시됩니다. 용법:

``` python
def can_drink_alcohol(user, context):
    return user.age >= 21 or user.looks_mature_for_his_age

drink_alcohol = menu.PassTestNode('drink',
                                  label='Have a beer',
                                  pattern_name='beer',
                                  test=can_drink_alcohol)

```

충분하지 않은 경우 기본 템플릿을 재정의 할 수도 있습니다.

- `navutils/menu.html` : 노드를 순환하는 메뉴 래퍼
- `navutils/node.html` : 각 노드 인스턴스를 표시하기 위해 호출

물론 자신 만의 하위 클래스를 자유롭게 만들 수 있습니다.

### [Breadcrumbs](https://github.com/agateblue/django-navutils#id6)

이동 경로는보기로 설정되므로 클래스 기반보기 에서만 사용할 수 있습니다.

우선, 모든 뷰에 대한 기본 Mixin 을 정의하고 싶을 것입니다.

``` python
from navutils import BreadcrumbsMixin, Breadcrumb

class BaseMixin(BreadcrumbsMixin):
    def get_breadcrumbs(self):
        breadcrumbs = super(BaseMixin, self).get_breadcrumbs()
        breadcrumbs.append(Breadcrumb('Home', url='/'))
        return breadcrumbs
```

그런 다음 이 뷰를 모든 곳에서 상속 할 수 있습니다.

``` python
# breadcrumbs = Home > Blog
class BlogView(BaseMixin):
    title = 'Blog'


# breadcrumbs = Home > Logout
class LogoutView(BaseMixin):
    title = 'Logout'

```

기본적으로 이동 경로의 마지막 요소는 view의 제목 속성에서 추론됩니다. 그러나 복잡한 계층의 경우 get_breadcrumbs 메소드를 자유롭게 재정의 할 수 있습니다.

``` python
# you can trigger url reversing via pattern_name, as for menu nodes
class BlogMixin(BaseMixin)
    def get_breadcrumbs(self):
        breadcrumbs = super(BlogMixin, self).get_breadcrumbs()
        breadcrumbs.append(Breadcrumb('Blog', pattern_name='blog:index'))
        return breadcrumbs


# breadcrumbs = Home > Blog > Last entries
class BlogIndex(BlogMixin):
    title = 'Last entries'


# for dynamic titles, just override the get_title method
# breadcrumbs = Home > Blog > My category name
class CategoryDetail(BlogMixin, DetailView):

    model = Category

    def get_title(self):
        # assuming your Category model has a title field
        return self.object.title

```

마지막 단계는 템플릿에서 이동 경로를 렌더링하는 것입니다. 제공된 믹스 인은 컨텍스트에서 데이터 전달을 처리하므로 필요한 것은 다음과 같습니다.

``` html
{% load navutils_tags %}

{% render_breadcrumbs breadcrumbs %}
```

Navutils의 탐색 경로 부분은 두 개의 템플릿과 함께 번들로 제공됩니다. 자유롭게 재정의 할 수 있습니다.

- `navutils/breadcrumbs.html`: 이동 경로 래퍼
- `navutils/crumb.html`: 각 이동 경로 를 렌더링하는 데 사용

