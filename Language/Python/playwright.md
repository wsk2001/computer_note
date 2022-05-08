# playwright

출처: https://seokdev.site/222



**소개 :** playwright는 원래 [JS 버전](https://github.com/microsoft/playwright)만 있었는데, python버전도 출시가 되었다.

playwright는 chromium, firefox, webkit 오토메이션 툴이다. (macro)

(주로 cross-browser 테스트로 많이 쓰이는 듯)

 

**특징 기능 :**

- action 실행 전 element auto-wait (ex. 지정한 HTML 요소가 나오면 클릭)
- 네트워크 요청받기 / 보내기
- 모바일 디바이스, 권한, 위치 에뮬레이션
- shadow-piercing selector (::shadow, /deep/)
- 파일 업로드 및 다운로드

 

### 1. 설치

``` bash
pip install playwright
python -m playwright install
```



### 2. 사용법

우선 sync(동기), async(비동기) 두 가지 방식 중 선택해서 코딩하면 된다.

동기(synchronous) 처리 : 특정 작업이 끝나면 다음 작업을 처리하는 순차처리 방식

비동기(asynchronous) 처리 : 여러 작업을 처리하도록 예약한 뒤 작업이 끝나면 결과를 받는 방식

 

#### 1. 비동기로 홈페이지를 열어 스크린샷 캡처

[whatsmyuseragent.org](http://whatsmyuseragent.org/) 홈페이지를 열어서 스크린샷으로 저장한다.

``` python
import asyncio
from playwright.async_api import async_playwright

# 비동기
async def main():
    async with async_playwright() as p:
        for browser_type in [p.chromium]:
            browser = await browser_type.launch()
            page = await browser.newPage()
            await page.goto('http://whatsmyuseragent.org/')
            await page.screenshot(path=f'example-{browser_type.name}.png')
            await browser.close()
```



#### 2. 네이버 및 구글 검색기

[waitForSelector](https://playwright.dev/#version=v1.3.0&path=docs%2Fapi.md&q=pagewaitforselectorselector-options) 를 사용하면 원하는 element가 나올 때까지 기다렸다가 스크린샷을 캡처한다.

아래 코드는, naver에 '코로나 19 현황'을 검색하고, google에 'naver'를 검색해서, 결과를 스크린샷으로 저장한다.

``` python
import asyncio
from playwright.async_api import async_playwright

def searchGoogle(page, keyword):
    page.goto("https://www.google.com")
    page.type("input[name=q]", keyword)
    page.click("input[type=submit]")
    page.waitForSelector("text=" + keyword)  # 검색 결과 keyword가 나오면 continue
    page.screenshot(path=f'{browser_type.name}-google.png')


def searchNaver(page, keyword):
    page.goto("https://www.naver.com/")
    page.type("input[name=query]", keyword)
    page.click("button[type=submit]")
    page.screenshot(path=f'{browser_type.name}-naver.png')

# 동기
with sync_playwright() as p:
    for browser_type in [p.chromium]:
        browser = browser_type.launch()
        page = browser.newPage()

        searchGoogle(page, "네이버")
        searchNaver(page, "코로나 19 현황")

        browser.close()
```



#### 3. 네트워크 요청 인터셉트

Route 공식 문서 : https://playwright.dev/#version=v1.3.0&path=docs%2Fapi.md&q=pagerouteurl-handler

page.route(url, handler) : url[Regex, string, function, boolean 타입 가능], handler[function Route, Request]

아래 코드는 티스토리 블로그를 열어서 css와 관련 네트워크 요청을 전부 print한다.

``` python
import asyncio
from playwright.async_api import async_playwright

with sync_playwright() as p:
    browser = p.chromium.launch()
    page = browser.newPage()

    def log_and_continue_request(route, request):
        print(request.url)
        route.continue_()

    # Network 요청 전부 로깅
    page.route('.*?[.css]', lambda route,
               request: log_and_continue_request(route, request))

    page.goto('https://choiseokwon.tistory.com/')
    browser.close()
```



#### 4. 모바일 환경 구글 맵 에뮬

지원 기기 목록 : https://github.com/Microsoft/playwright/blob/master/src/deviceDescriptors.ts

[waitForRequest 문서 열기](https://playwright.dev/#version=v1.3.0&path=docs%2Fapi.md&q=pagewaitforrequesturlorpredicate-options)

아래 코드는, 동기, iPhone 11 Pro로 구글 맵에 콜로세움 위치를 입력해서 스크린샷으로 저장

``` python
import asyncio
from playwright.async_api import async_playwright

with sync_playwright() as p:
    iphone_11 = p.devices['iPhone 11 Pro']
    browser = p.webkit.launch(headless=False)
    context = browser.newContext(
        **iphone_11,
        locale='en-US',
        geolocation={'longitude': 12.492507, 'latitude': 41.889938},
        permissions=['geolocation']
    )
    page = context.newPage()
    page.goto('https://www.google.com/maps/')
    page.click('text="Your location"')
    page.waitForRequest(".*[Colosseo]")
    page.screenshot(path='colosseum-iphone.png')
    browser.close()
```



 

React Playwright : https://github.com/KyleADay/react-app-playwright#why-playwright

Playwright 공식 문서 : https://playwright.dev/#



# Playwright 를 이 사용한 웹 크롤링

출처: https://github.com/microsoft/playwright-python

Playwright는 단일 API로 Chromium, Firefox 및 WebKit 브라우저를 자동화하는 Python 라이브러리입니다. Playwright는 항상 친환경적이고 기능이 뛰어나고 안정적이며 빠른 자동화를 제공합니다.

