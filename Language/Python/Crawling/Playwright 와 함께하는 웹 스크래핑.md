# Playwright 와 함께하는 웹 스크래핑

출처: https://oxylabs.io/blog/playwright-web-scraping



![img](.\images\ZTE2YWZlNmItNjgzNS00ZDNmLWExNmMtOTQ5ZTA2YTRkNjQz_playwright-tutorial-heading.png)



최근 몇 년 동안 인터넷과 그 영향력은 엄청나게 커졌습니다. 이것은 아마도 보다 사용자 친화적인 응용 프로그램을 만드는 데 도움이 되는 기술의 성장에 기인할 수 있습니다. 또한 개발에서 웹 애플리케이션 테스트에 이르기까지 모든 단계에서 점점 더 많은 자동화가 이루어지고 있습니다.

웹 애플리케이션을 테스트하기 위한 좋은 도구를 갖는 것은 매우 중요합니다. Playwright와 같은 라이브러리는 브라우저에서 웹 애플리케이션을 열고 요소 클릭, 텍스트 입력, 웹에서 공개 데이터 추출과 같은 기타 사용자 상호 작용을 통해 프로세스 속도를 높이는 데 도움이 됩니다.

이 기사에서는 Playwright에 대한 모든 것과 자동화 및 웹 스크래핑에 Playwright를 사용하는 방법을 설명합니다.



## What is Playwright?

Playwright는 웹 브라우저 상호 작용을 자동화할 수 있는 테스트 및 자동화 프레임워크입니다. 간단히 말해서 브라우저를 열 수 있는 코드를 작성할 수 있습니다. 이는 모든 웹 브라우저 기능을 사용할 수 있음을 의미합니다. 자동화 스크립트는 URL로 이동하고, 텍스트를 입력하고, 버튼을 클릭하고, 텍스트를 추출할 수 있습니다. Playwright의 가장 흥미로운 기능은 차단되거나 작업이 완료될 때까지 기다릴 필요 없이 동시에 여러 페이지에서 작업할 수 있다는 것입니다. 그들 중 하나.

Chrome, Chromium을 사용하는 Microsoft Edge, Firefox와 같은 대부분의 브라우저를 지원합니다. WebKit을 사용할 때 Safari가 지원됩니다. 사실, 크로스 브라우저 웹 자동화는 Playwright의 강점입니다. 모든 브라우저에서 동일한 코드를 효율적으로 실행할 수 있습니다. 또한 Playwright는 Node.js, Python, Java 및 .NET과 같은 다양한 프로그래밍 언어를 지원합니다. 이러한 언어를 사용하여 웹 사이트를 열고 상호 작용하는 코드를 작성할 수 있습니다.

Playwright의 문서는 광범위합니다. 시작부터 모든 클래스와 메서드에 대한 자세한 설명까지 모든 것을 다룹니다.



## Playwright에서 프록시 지원

Playwright는 프록시 사용을 지원합니다. 이 주제를 더 자세히 살펴보기 전에 Chromium에서 프록시 사용을 시작하는 방법을 보여주는 빠른 코드 스니펫이 있습니다.



### **Node.js:**

``` js
const { chromium } = require('playwright'); "
const browser = await chromium.launch();
```



### **Python:**

``` py
from playwright.async_api import async_playwright
import asyncio
with async_playwright() as p:
browser = await p.chromium.launch()
```



이 코드는 프록시를 완전히 활용하기 위해 약간만 수정하면 됩니다.

Node.js의 경우 실행 기능은 LauchOptions 유형의 선택적 매개변수를 허용할 수 있습니다. 이 LaunchOption 개체는 차례로 헤드리스와 같은 여러 다른 매개변수를 보낼 수 있습니다. 필요한 다른 매개변수는 프록시입니다. 이 프록시는 서버, 사용자 이름, 암호 등과 같은 속성을 가진 또 다른 개체입니다. 첫 번째 단계는 이러한 매개변수를 지정할 수 있는 개체를 만드는 것입니다.



``` js
// Node.js
const launchOptions = {
    proxy: {
        server: 123.123.123.123:80'
    },
    headless: false
}
```



다음 단계는 이 객체를 시작 함수에 전달하는 것입니다.

``` js
const browser = await chromium.launch(launchOptions);
```



파이썬의 경우에는 약간 다릅니다. LaunchOptions의 개체를 생성할 필요가 없습니다. 대신 모든 값을 별도의 매개변수로 보낼 수 있습니다. 프록시 사전이 전송되는 방법은 다음과 같습니다.

``` py
# Python
proxy_to_use = {
    'server': '123.123.123.123:80'
}
browser = await pw.chromium.launch(proxy=proxy_to_use, headless=False)
```

사용할 프록시를 결정할 때 주거용 프록시는 발자국을 남기지 않고 보안 경보를 트리거하지 않으므로 사용하는 것이 가장 좋습니다. Oxylabs의 주거용 프록시는 광범위하고 안정적인 프록시 네트워크를 지원합니다. 특정 국가, 주 또는 도시의 프록시에 액세스할 수 있습니다. 필수적인 것은 Playwright와도 쉽게 통합할 수 있다는 것입니다.



## Playwright를 사용한 기본 스크래핑

Node.js와 Python을 사용하여 Playwright를 시작하는 방법을 다루는 다른 주제로 이동하겠습니다.

Node.js를 사용하는 경우 새 프로젝트를 만들고 Playwright 라이브러리를 설치합니다. 이것은 다음 두 가지 간단한 명령을 사용하여 수행할 수 있습니다.

``` sh
npm init -y
npm install playwright
```

동적 페이지를 여는 기본 스크립트는 다음과 같습니다.

``` js
const playwright = require('playwright');
(async () => {
    const browser = await playwright.chromium.launch({
        headless: false // Show the browser. 
    });
    
    const page = await browser.newPage();
    await page.goto('https://books.toscrape.com/');
    await page.waitForTimeout(1000); // wait for 1 seconds
    await browser.close();
})();
```



제공된 코드를 살펴보겠습니다. 코드의 첫 번째 줄은 Playwright를 가져옵니다. 그런 다음 Chromium 인스턴스가 시작됩니다. 스크립트에서 Chromium을 자동화할 수 있습니다. 또한 이 스크립트는 보이는 UI로 실행되고 있습니다. headless:false를 전달하여 수행했습니다. 그런 다음 새 브라우저 페이지가 열립니다. 그 후 page.goto 함수는 Books to Scrap 웹 페이지로 이동합니다. 그 후, 최종 사용자에게 페이지를 표시하기 위해 1초의 대기 시간이 있습니다. 마지막으로 브라우저가 닫힙니다.

동일한 코드를 Python으로 쉽게 작성할 수 있습니다. 먼저 pip 명령을 사용하여 Playwright를 설치합니다.

``` sh
pip install playwright
```

Playwright는 동기 및 비동기의 두 가지 변형을 지원합니다. 다음 예제에서는 비동기 API를 사용합니다.

``` py
from playwright.async_api import async_playwright
import asyncio
 
async def main():
    async with async_playwright() as pw: 
        browser = await pw.chromium.launch(
            headless=False  # Show the browser
        )
        page = await browser.new_page()
        await page.goto('https://books.toscrape.com/')
        # Data Extraction Code Here
        await page.wait_for_timeout(1000)  # Wait for 1 second
        await browser.close()
        
if __name__ == '__main__':
    asyncio.run(main())
```

이 코드는 Node.js 코드와 유사합니다. 가장 큰 차이점은 asyncio 라이브러리를 사용한다는 것입니다. 또 다른 차이점은 함수 이름이 camelCase에서 snake_case로 변경된다는 것입니다.

둘 이상의 브라우저 컨텍스트를 만들거나 더 세부적으로 제어하려는 경우 컨텍스트 개체를 만들고 해당 컨텍스트에서 여러 페이지를 만들 수 있습니다. 그러면 새 탭에서 페이지가 열립니다.

``` js
const context = await browser.newContext();
const page1 = await context.newPage();
const page2 = await context.newPage();
```

코드에서 페이지 컨텍스트를 처리할 수도 있습니다. page.context() 함수를 사용하여 페이지가 속한 브라우저 컨텍스트를 얻을 수 있습니다.



## 요소 찾기

요소에서 정보를 추출하거나 요소를 클릭하려면 첫 번째 단계는 요소를 찾는 것입니다. Playwright는 CSS와 XPath 선택기를 모두 지원합니다.

이것은 실제 예를 통해 더 잘 이해할 수 있습니다. Chrome에서 https://books.tocrape.com/을 엽니다. 첫 번째 책을 마우스 오른쪽 버튼으로 클릭하고 검사를 선택합니다.

![img](.\images\ZDhlODZmZGQtNDk1NC00ZDExLTljMDUtNTAyOTZjZTkzNDhm_books_to_scrape_image.png)



모든 책이 product_prod 클래스가 있는 article 요소 아래에 있음을 알 수 있습니다.

모든 책을 선택하려면 이러한 모든 기사 요소에 대해 루프를 실행해야 합니다. 다음 기사 요소는 CSS 선택기를 사용하여 선택할 수 있습니다.

``` js
.product_pod
```

마찬가지로 XPath 선택기는 다음과 같습니다.

``` py
//*[@class="product_pod"]
```

이러한 선택기를 사용하기 위한 가장 일반적인 기능은 다음과 같습니다.

- $eval(selector, function) – 첫 번째 요소를 선택하고 요소를 함수로 보내고 함수의 결과가 반환됩니다.
- $$eval(selector, function) –  모든 요소를 선택한다는 점을 제외하고 위와 동일합니다.
- querySelector(selector) – 첫 번째 요소를 반환합니다.
- querySelectorAll(selector)– 모든 요소를 반환합니다.

이러한 방법은 CSS 및 XPath 선택기 모두에서 올바르게 작동합니다.



## 텍스트 스크래핑

계속해서 책을 긁어내는 예제를 계속하면 페이지가 로드된 후 선택기를 사용하여 $$eval 함수를 사용하여 모든 책 컨테이너를 추출할 수 있습니다.

``` js
const books = await page.$$eval('.product_pod', all_items => {
// run a loop here
})
```

이제 책 데이터를 포함하는 모든 요소를 루프에서 추출할 수 있습니다.

```js
all_items.forEach(book => {
    const name = book.querySelector('h3').innerText;
})
```

마지막으로 innerText 속성을 사용하여 각 데이터 포인트에서 데이터를 추출할 수 있습니다. Node.js의 전체 코드는 다음과 같습니다.

``` js
const playwright = require('playwright');
 
(async () => {
    const browser = await playwright.chromium.launch();
    const page = await browser.newPage();
    await page.goto('https://books.toscrape.com/');
    const books = await page.$$eval('.product_pod', all_items => {
        const data = [];
        all_items.forEach(book => {
            const name = book.querySelector('h3').innerText;
            const price = book.querySelector('.price_color').innerText;
            const stock = book.querySelector('.availability').innerText;
            data.push({ name, price, stock});
        });
        return data;
    });
    console.log(books);
    await browser.close();
})();
```



Python의 코드는 약간 다릅니다. Python에는 Node.js의 $eval과 유사한 eval_on_selector 함수가 있지만 이 시나리오에는 적합하지 않습니다. 그 이유는 두 번째 매개변수가 여전히 JavaScript여야 하기 때문입니다. 이것은 특정 시나리오에서 좋을 수 있지만 이 경우 전체 코드를 Python으로 작성하는 것이 훨씬 더 좋습니다.

요소와 요소 목록을 각각 반환하는 query_selector 및 query_selector_all을 사용하는 것이 좋습니다.

``` py
from playwright.async_api import async_playwright
import asyncio
 
 
async def main():
    async with async_playwright() as pw:
        browser = await pw.chromium.launch()
        page = await browser.new_page()
        await page.goto('https://books.toscrape.com')
 
        all_items = await page.query_selector_all('.product_pod')
        books = []
        for item in all_items:
            book = {}
            name_el = await item.query_selector('h3')
            book['name'] = await name_el.inner_text()
            price_el = await item.query_selector('.price_color')
            book['price'] = await price_el.inner_text()
            stock_el = await item.query_selector('.availability')
            book['stock'] = await stock_el.inner_text()
            books.append(book)
        print(books)
        await browser.close()
 
if __name__ == '__main__':
    asyncio.run(main())
```

Node.js와 Python 코드의 출력은 동일합니다. 편의를 위해 [여기](https://github.com/oxylabs/playwright-web-scraping)를 클릭하여 이 문서에 사용된 전체 코드를 찾을 수 있습니다.



## Playwright vs Puppeteer and Selenium

Selenium 및 Puppeteer와 같은 다른 도구도 Playwright와 동일한 작업을 수행할 수 있습니다.

그러나 Puppeteer는 브라우저 및 프로그래밍 언어와 관련하여 제한적입니다. 사용할 수 있는 유일한 언어는 JavaScript이며 이 언어와 작동하는 유일한 브라우저는 Chromium입니다.

반면 Selenium은 모든 주요 브라우저와 많은 프로그래밍 언어를 지원합니다. 그러나 느리고 개발자 친화적이지 않습니다.

또한 Playwright는 네트워크 요청을 가로챌 수 있습니다. 네트워크 요청에 대한 자세한 내용은 [이 페이지](https://playwright.dev/docs/network/)를 참조하십시오.

다음 표는 차이점과 유사점을 간략하게 요약한 것입니다.

| 구분                      | PLAYWRIGHT                    | **PUPPETEER**    | **SELENIUM**                                       |
| ------------------------- | ----------------------------- | ---------------- | -------------------------------------------------- |
| **SPEED**                 | Fast                          | Fast             | Slower                                             |
| **DOCUMENTATION**         | Excellent                     | Excellent        | Fair                                               |
| **DEVELOPER EXPERIENCE**  | Best                          | Good             | Fair                                               |
| **PROGRAMMING LANGUAGES** | JavaScript, Python, C#, Java  | JavaScript       | Java, Python, C#, Ruby JavaScript, Kotlin          |
| **BACKED BY**             | Microsoft                     | Google           | Community and Sponsors                             |
| **COMMUNITY**             | Small but active              | Large and active | Large and active                                   |
| **BROWSER SUPPORT**       | Chromium, Firefox, and WebKit | Chromium         | Chrome, Firefox, IE, Edge, Opera, Safari, and more |



## 성능 비교

이전 섹션에서 논의한 것처럼 프로그래밍 언어와 지원되는 브라우저의 엄청난 차이로 인해 모든 시나리오를 비교하기가 쉽지 않습니다.

비교할 수 있는 유일한 조합은 스크립트가 Chromium을 자동화하기 위해 JavaScript로 작성된 경우입니다. 이것은 세 가지 도구가 모두 지원하는 유일한 조합입니다.

자세한 비교는 이 기사의 범위를 벗어납니다. 이 기사에서 Puppeteer, Selenium 및 Playwright의 성능에 대해 자세히 읽을 수 있습니다. 핵심은 Puppeteer가 가장 빠르며 Playwright가 그 뒤를 잇는다는 것입니다. 일부 시나리오에서는 Playwright가 더 빠릅니다. 셀레늄은 세 가지 중 가장 느립니다.

다시 말하지만, Playwright에는 여러 프로그래밍 언어를 지원하는 다중 브라우저 지원과 같은 다른 이점이 있습니다.

빠른 브라우저 간 웹 자동화를 찾고 있거나 JavaScript를 모르는 경우 Playwright가 유일한 선택이 될 것입니다.





## 결론

이 기사에서는 웹 스크래핑 동적 사이트에 사용할 수 있는 웹 테스트 도구로서 Playwright의 기능을 살펴보았습니다. 비동기적 특성과 브라우저 간 지원으로 인해 다른 도구에 대한 인기 있는 대안입니다. 이 기사에서는 Node.js와 Python의 코드 예제도 다뤘습니다.

Playwright는 URL 탐색, 텍스트 입력, 버튼 클릭, 텍스트 추출 등을 도울 수 있습니다. 가장 중요한 것은 동적으로 렌더링되는 텍스트를 추출할 수 있다는 것입니다. 이러한 작업은 Puppeteer 및 Selenium과 같은 다른 도구로도 수행할 수 있지만 여러 브라우저에서 작업해야 하거나 JavaScript/Node.js 이외의 언어로 작업해야 하는 경우 Playwright가 훌륭한 선택이 될 것입니다.

다른 유사한 주제에 관심이 있는 경우 Selenium 또는 Puppeteer 자습서를 사용한 웹 스크래핑에 대한 블로그 게시물을 확인하십시오. 마지막으로, 당사의 범용 웹 스크레이퍼의 기능을 무료로 사용해 보는 것을 주저하지 마십시오.