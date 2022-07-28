# Playwright for Python
출처:  https://playwright.dev/python/



## 소개

Playwright는 최신 웹 앱에 대한 안정적인 종단 간 테스트를 가능하게 합니다.



### 모든 브라우저 • 모든 플랫폼 • 하나의 API

**Cross-browser.** Playwright는 Chromium, WebKit 및 Firefox를 포함한 모든 최신 렌더링 엔진을 지원합니다.

**Cross-platform.** Windows, Linux 및 macOS, 로컬 또는 CI, 헤드리스 또는 헤드에서 테스트합니다.

**Cross-language.** TypeScript, JavaScript, Python, .NET, Java에서 Playwright API를 사용합니다.

**Test Mobile Web.**  Android 및 모바일 Safari용 Google Chrome의 기본 모바일 에뮬레이션. 동일한 렌더링 엔진이 데스크탑과 클라우드에서 작동합니다.



### 탄력성 • 비정상적 테스트 없음

**Auto-wait.** Playwright는 작업을 수행하기 전에 요소가 실행될 때까지 기다립니다. 또한 다양한 자기 성찰 이벤트가 있습니다. 이 둘을 조합하면 비정상적인 테스트의 주요 원인인 인위적인 시간 초과가 필요하지 않습니다.

**Web-first assertions.**  Playwright는 동적 웹을 위해 특별히 생성됩니다. 검사는 필요한 조건이 충족될 때까지 자동으로 재시도됩니다.

**Tracing.** 테스트 재시도 전략을 구성하고 실행 추적, 비디오, 스크린샷을 캡처하여 플레이크를 제거합니다.



### No trade-offs • No limits

브라우저는 서로 다른 프로세스에서 서로 다른 출처에 속한 웹 콘텐츠를 실행합니다. Playwright는 최신 브라우저 아키텍처와 일치하며 테스트를 외부 프로세스로 실행합니다. 이를 통해 Playwright는 일반적인 진행 중인 테스트 실행자 제한에서 벗어날 수 있습니다.

**Multiple everything.**  여러 탭, 여러 출처 및 여러 사용자에 걸친 테스트 시나리오. 여러 사용자에 대해 다른 컨텍스트로 시나리오를 만들고 하나의 테스트에서 서버에 대해 실행합니다.

**Trusted events.**  요소를 가리키고 동적 컨트롤과 상호 작용하며 신뢰할 수 있는 이벤트를 생성합니다. Playwright는 실제 사용자와 구별할 수 없는 실제 브라우저 입력 파이프라인을 사용합니다.

**Test frames, pierce Shadow DOM.**   Playwright selectors 는 shadow DOM을 관통하고 프레임을 원활하게 입력할 수 있도록 합니다.



### 완전한 격리 • 빠른 실행

**Browser contexts.** Playwright는 각 테스트에 대한 브라우저 컨텍스트를 만듭니다. 브라우저 컨텍스트는 새로운 브라우저 프로필과 동일합니다. 이는 오버헤드 없이 완전한 테스트 격리를 제공합니다. 새 브라우저 컨텍스트를 만드는 데 몇 밀리초 밖에 걸리지 않습니다.

**Log in once.**  컨텍스트의 인증 상태를 저장하고 모든 테스트에서 재사용합니다. 이것은 각 테스트에서 반복적인 로그인 작업을 우회하면서도 독립적인 테스트의 완전한 격리를 제공합니다.



### 강력한 툴링

**Codegen.**  작업을 기록하여 테스트를 생성합니다. 어떤 언어로든 저장하십시오.

**Playwright inspector.** 페이지 검사, 선택기 생성, 테스트 실행 단계, 클릭 지점 확인, 실행 로그 탐색.

**Trace Viewer.**  테스트 실패를 조사하기 위해 모든 정보를 캡처합니다. Playwright 추적에는 테스트 실행 스크린캐스트, 라이브 DOM 스냅샷, 작업 탐색기, 테스트 소스 등이 포함됩니다.





## 시작하기

### Installation

Playwright는 종단 간 테스트의 요구 사항을 수용하기 위해 특별히 만들어졌습니다. Playwright는 Chromium, WebKit 및 Firefox를 포함한 모든 최신 렌더링 엔진을 지원합니다. Windows, Linux 및 macOS, 로컬 또는 CI, 헤드리스 또는 기본 모바일 에뮬레이션으로 테스트합니다.

Playwright는 공식 Playwright Pytest 플러그인을 사용하여 종단 간 테스트를 작성할 것을 권장합니다. 컨텍스트 격리를 제공하여 기본적으로 여러 브라우저 구성에서 실행합니다. 또는 라이브러리를 사용하여 선호하는 테스트 실행기로 테스트 인프라를 수동으로 작성할 수 있습니다. Pytest 플러그인은 Playwright의 동기화 버전을 활용하며 라이브러리를 통해 액세스할 수 있는 비동기 버전도 있습니다.

Playwright를 설치하고 예제 테스트를 실행하여 시작해보세요.

Pytest 플러그인 설치:

``` sh
pip install pytest-playwright
```

필요한 브라우저를 설치.

``` sh
playwright install
```



#### 예제 테스트 추가

아래 코드를 사용하여 현재 작업 디렉터리 또는 하위 디렉터리에 test_my_application.py 파일을 만듭니다.

``` py
import re
from playwright.sync_api import Page, expect


def test_homepage_has_Playwright_in_title_and_get_started_link_linking_to_the_intro_page(
    page: Page, foo
):
    page.goto("https://playwright.dev/")

    # Expect a title "to contain" a substring.
    expect(page).to_have_title(re.compile("Playwright"))

    # create a locator
    get_started = page.locator("text=Get Started")

    # Expect an attribute "to be strictly equal" to the value.
    expect(get_started).to_have_attribute("href", "/docs/intro")

    # Click the get started link.
    get_started.click()

    # Expects the URL to contain intro.
    expect(page).to_have_url(re.compile(".*intro"))
```



#### 예제 테스트 실행

기본적으로 테스트는 크롬에서 실행됩니다. 이는 CLI 옵션을 통해 구성할 수 있습니다. 테스트는 헤드리스 모드에서 실행되므로 테스트를 실행할 때 브라우저 UI가 열리지 않습니다. 테스트 결과 및 테스트 로그가 터미널에 표시됩니다.

``` sh
pytest
```

헤드 모드에서 테스트 실행, 여러 테스트 실행, 특정 테스트 실행 등에 대해 자세히 알아보려면 [테스트 실행](https://playwright.dev/python/docs/running-tests) 문서를 참조하세요.



### 쓰기 테스트

Playwright assertions은 동적 웹을 위해 특별히 생성됩니다. 검사는 필요한 조건이 충족될 때까지 자동으로 재시도됩니다. Playwright는 자동 대기 기능이 내장되어 있어 작업을 수행하기 전에 요소가 실행 가능해질 때까지 기다립니다. Playwright는 주장을 작성하는 기대 기능을 제공합니다.

웹 퍼스트 어설션, 로케이터 및 선택기를 사용하여 테스트를 작성하는 방법을 보려면 아래의 예제 테스트를 살펴보십시오.

``` py
import re
from playwright.sync_api import Page, expect


def test_homepage_has_Playwright_in_title_and_get_started_link_linking_to_the_intro_page(
    page: Page, foo
):
    page.goto("https://playwright.dev/")

    # Expect a title "to contain" a substring.
    expect(page).to_have_title(re.compile("Playwright"))

    # create a locator
    get_started = page.locator("text=Get Started")

    # Expect an attribute "to be strictly equal" to the value.
    expect(get_started).to_have_attribute("href", "/docs/intro")

    # Click the get started link.
    get_started.click()

    # Expects the URL to contain intro.
    expect(page).to_have_url(re.compile(".*intro"))
```



#### Assertions

Playwright는 예상 조건이 충족될 때까지 기다리는 예상 기능을 제공합니다.

``` py
import re
from playwright.sync_api import expect

expect(page).to_have_title(re.compile("Playwright"))
```



#### Locators

로케이터는 Playwright의 자동 대기 및 재시도 기능의 중심 부분입니다. 로케이터는 언제든지 페이지에서 요소를 찾는 방법을 나타내며 .click .fill 등과 같은 요소에 대한 작업을 수행하는 데 사용됩니다. 사용자 지정 로케이터는 page.locator(selector, **kwargs) 메소드로 생성할 수 있습니다 .

``` py
from playwright.sync_api import expect

get_started = page.locator("text=Get Started")

expect(get_started).to_have_attribute("href", "/docs/installation")
get_started.click()
```



[Selectors](https://playwright.dev/python/docs/selectors) 는 로케이터를 생성하는 데 사용되는 문자열입니다. Playwright는 Text, CSS, XPath 등과 같은 다양한 선택기를 지원합니다. 이 [심층 가이드](https://playwright.dev/python/docs/selectors)에서 사용 가능한 selectors와 선택 방법에 대해 자세히 알아보세요.

``` py
from playwright.sync_api import expect

expect(page.locator("text=Installation")).to_be_visible()
```



#### 테스트 격리

Playwright Pytest 플러그인은 테스트에 전달되는 내장 페이지 고정 장치와 같은 테스트 고정 장치의 개념을 기반으로 합니다. 단일 브라우저에서 여러 테스트가 실행되는 경우에도 모든 테스트가 새로운 환경을 가져오는 새로운 브라우저 프로필과 동일한 브라우저 컨텍스트로 인해 테스트 간에 페이지가 격리됩니다.

``` py
from playwright.sync_api import Page

def test_basic_test(page: Page):
  # ...
```



#### 테스트 후크 사용

다양한 픽스처를 사용하여 테스트 전후에 코드를 실행하고 테스트 간에 개체를 공유할 수 있습니다. 기능 범위 고정 장치 예. autouse를 사용하면 beforeEach/afterEach처럼 작동합니다. 그리고 autouse가 있는 모듈 범위 고정 장치는 모든 테스트 전과 후에 실행되는 beforeAll/afterAll처럼 동작합니다.

``` py
import pytest
from playwright.sync_api import Page


@pytest.fixture(scope="function", autouse=True)
def before_each_after_each(page: Page):
    print("beforeEach")
    # Go to the starting url before each test.
    page.goto("https://playwright.dev/")
    yield
    print("afterEach")

def test_main_navigation(page: Page):
    # Assertions use the expect API.
    expect(page).to_have_url("https://playwright.dev/")
```

