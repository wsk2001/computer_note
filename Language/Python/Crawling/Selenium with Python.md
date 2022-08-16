# Selenium with Python

출처: https://pylessons.com/Selenium-with-python-basic-introduction

Selenium은 웹 사용자 인터페이스 자동화를 위한 가장 인기 있는 도구 중 하나입니다. 이 튜토리얼에서는 이 놀라운 도구와 간단한 예제를 소개합니다.

Selenium with python에 대한 새로운 튜토리얼 시리즈에 오신 것을 환영합니다. 첫 번째 기본 소개 동영상 튜토리얼입니다.

웹 UI 자동화는 웹 사이트 탐색, 텍스트 상자, 버튼 및 드롭다운 처리, 양식 제출, 웹 페이지 탐색, 팝업 처리, 등등. Selenium WebDriver는 이러한 모든 작업을 자동화할 수 있는 도구입니다. Firefox, Internet Explorer, Safari, Chrome, Opera 등과 같이 오늘날 사용 가능한 모든 유형의 웹 브라우저와 상호 작용할 수 있습니다.


Selenium은 오픈 소스이며 웹 UI 자동화 테스트를 수행하기 위해 다양한 프로그래밍 언어로 라이브러리를 사용할 수 있으며 Python이 그 중 하나입니다.

Python용 Selenium WebDriver 클라이언트 라이브러리를 사용하면 Selenium WebDriver에서 사용할 수 있는 모든 기능을 활용하고 Selenium Standalone Server와 상호 작용하여 브라우저 기반 애플리케이션의 자동 테스트(원격 및 분산 테스트 모두)를 수행할 수 있습니다.

Selenium Webdriver 라이브러리는 Python 2.6, 2.7 및 3.2-3.7을 포함하는 일련의 Python 버전과 호환된다는 점을 언급해야 합니다. 거의 모든 Python 버전.

따라서 Python에 Selenium Webdriver를 설치하는 것은 다른 라이브러리에서 설치하는 것만큼 간단하다는 점을 언급해야 합니다. 다음 명령을 사용하기만 하면 됩니다.

``` shell
pip install selenium
```

Selenium을 설치한 후 스크립트에 사용할 브라우저를 결정해야 합니다. Selenium으로 무언가를 만들 때 Firefox를 사용하고 있지만 모든 것이 Chrome에서 동일한 방식으로 작동해야 하므로 무엇을 사용할지는 사용자에게 달려 있습니다. 사용할 브라우저를 결정했으면 브라우저용 셀레늄 드라이버를 다운로드해야 합니다. 이 링크를 열고 Firefox용 GeckoDriver 또는 Google Chrome용 Chrome 드라이버를 찾거나 사용 중인 것을 선택하십시오. 브라우저를 기본 위치에 설치한 경우 이러한 드라이버는 셀레늄 스크립트를 작성하는 동일한 폴더에 배치되어야 합니다. 다른 방법으로 이를 환경 변수에 추가해야 합니다. Linux 또는 iOS에서 어떻게 작동하는지 잘 모르겠지만 찾기가 어렵지 않을 것입니다.

다음은 브라우저를 열고 'www.google.com' 웹사이트로 이동하여 Pylessons.com을 검색하는 기본 예제 스크립트입니다. 다음으로 발견된 요소 중 10개를 가져와서 인쇄합니다. 마지막으로 브라우저를 닫습니다. 자세한 코드 설명은 아래 예입니다.

``` py
from selenium import webdriver

# create a new Firefox session
driver = webdriver.Firefox()
driver.implicitly_wait(30)
driver.maximize_window()

# Navigate to the application home page
driver.get("http://www.google.com")

# get the search textbox
search_field = driver.find_element_by_name('q')

# enter search keyword and submit
search_field.send_keys("Pylessons.com")
search_field.submit()

# get the list of elements which are displayed after the search
# currently on result page using find_elements_by_class_name method
lists = driver.find_elements_by_class_name("LC20lb")

# get the number of elements found
print("Found "+str(len(lists))+" searches:")

# print not more than 10 list elements
i = 0
for listiems in lists:
    print(listiems.text)
    i += 1
    if(i > 10):
        break

# end session and close the browser window
driver.quit()
```



**위 스크립트 디코딩**

Selenium WebDriver 문을 간략하게 더 잘 이해하기 위해 스크립트를 한 줄씩 논의해 보겠습니다. 다음 튜토리얼에서 다양한 Selenium WebDriver 기능에 대해 더 많이 알게 될 것입니다.

**Step 1.**

Selenium webdriver 모듈은 Firefox, Chrome, Internet Explorer, Safari, 기타 및 RemoteWebDriver를 비롯한 다양한 브라우저를 지원하는 클래스를 구현하여 원격 컴퓨터에서 사용 가능한 브라우저에서 테스트합니다. 따라서 Selenium WebDriver 메서드를 다음과 같이 사용하려면 Selenium 패키지에서 webdriver를 가져와야 합니다.

``` py
from selenium import webdriver
```

**Step 2.**

다음으로 웹 페이지를 로드하는 데 사용할 브라우저의 개체가 필요합니다. 브라우저 개체는 Selenium 명령을 사용하여 브라우저와 통신하기 위한 프로그래밍 가능한 인터페이스를 제공합니다. 테스트 스크립트에서는 Firefox를 사용하고 있습니다. 다음 코드와 같이 Firefox 인스턴스를 만들 수 있습니다.

``` py
driver = webdriver.Firefox()
```

위의 명령문을 실행하면 새로운 Firefox 창이 실행됩니다. 드라이버 인스턴스에 대해 다음과 같은 설정을 하고 있습니다.

``` py
driver.implicitly_wait(30)
driver.maximize_window()
```

Selenium이 30초 안에 브라우저를 시작하거나 새 탭을 열도록 시간 초과를 구성하고 있습니다. 다음 명령문은 브라우저를 전체 창으로 최대화합니다.

**Step 3.**

다음으로 'http://www.google.com'으로 이동하여 주어진 URL을 driver.get() 메서드에 전달합니다. get() 메서드를 호출한 후 Webdriver는 페이지가 브라우저 창에서 렌더링될 때까지 기다렸다가 컨트롤을 스크립트로 다시 보냅니다.

페이지가 로드된 후 Selenium은 페이지의 다양한 요소와 상호 작용합니다. 다음으로 테스트 스크립트에서 HTML 개체를 검색하고, 웹 구성 요소에 텍스트를 보내고, 키 누르기 이벤트를 시뮬레이션하고, 버튼을 클릭하고, 드롭다운에서 선택하는 등 다양한 Selenium WebDriver 기능을 살펴보겠습니다. 이 모든 기능이 사용되는 것을 봅시다. 다른 단계에서.

**Step 4.**

우선, 검색을 위한 텍스트 입력을 제공하기 위해 Google 검색 텍스트 상자를 찾습니다. 검색 텍스트 상자에는 'q'라는 이름 속성이 있으며 아래 코드에서 식별할 수 있습니다.

``` py
search_field.send_keys("Pylessons.com")
search_field.submit()
```

검색 요청을 제출한 후 Firefox 드라이버는 Google에서 반환한 결과 페이지를 표시합니다. 결과 페이지에는 검색된 텍스트와 일치하는 항목 목록이 표시됩니다. 각 항목 div 요소에는 'LC20lb' 클래스가 있으며 'find_elements_by_class_name' 메서드를 사용하여 액세스할 수 있습니다. 이런 식으로 요소 목록을 얻습니다.

``` py
lists = driver.find_elements_by_class_name("LC20lb")
```

항목 목록이 너무 많은 페이지로 확장될 수 있으므로 캡처된 처음 10개 항목을 인쇄하도록 코드를 제한하고 있습니다.

``` py
i = 0
for listiems in lists:
    print(listiems.text)
    i += 1
    if(i > 10):
        break
```



**Summary**

위의 예는 Selenium WebDriver와 Python을 함께 사용하여 간단한 테스트 자동화 스크립트를 만드는 방법에 대한 진정한 통찰력을 제공했습니다. 기본적인 예제 스크립트입니다. 다음 튜토리얼에서 Python과 함께 Selenium Library의 흥미롭고 복잡한 다른 기능을 사용할 것입니다.

그러나 다음 수준으로 이동하고 Selenium Python에서 테스트 자동화 제품군을 만들고 싶다면 향후 자습서를 따르십시오. 자동화를 통해 Selenium을 배우는 방법은 단 한 가지뿐입니다!