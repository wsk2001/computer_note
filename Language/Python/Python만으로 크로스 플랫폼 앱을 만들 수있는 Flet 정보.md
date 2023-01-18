# Python만으로 크로스 플랫폼 앱을 만들 수있는 Flet 정보

출처: https://qiita.com/NasuPanda/items/48849d7f925784d6b6a0?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=d556b6c2cd-Qiita_newsletter_549_01_18_2023&utm_medium=email&utm_term=0_e44feaa081-d556b6c2cd-34467781



# 소개

Python만으로 크로스 플랫폼 앱을 만들 수 있는 [Flet](https://flet.dev/) 이라는 프레임워크에 대한 기사입니다.

Python만으로 다음과 같은 웹 데스크톱에 양대응한 앱을 만들 수 있습니다.

[![trolli-app.gif](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2Fc1d7acb3-6c17-4523-155d-f402fe2dd9f9.gif)](https://camo.qiitausercontent.com/1286524dbf4c6afed4c8c758bbec722ba4aa45ee/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f63316437616362332d366331372d343532332d313535642d6634303266653264643966392e676966)
[![todo-complete-demo-web.gif](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2Fe4b2fd54-ece1-372c-d21a-899082557564.gif)](https://camo.qiitausercontent.com/11010964869cbb9be3f70358e559dd7ae94a28f6/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f65346232666435342d656365312d333732632d643231612d3839393038323535373536342e676966)



[GitHub - flet-dev/flet: Flet enables developers to easily build realti...https://github.com![img](https://opengraph.githubassets.com/420077098bf88f49d49741df9262d9eea0456829c44b1a0a9701aba44d1d89ac/flet-dev/flet)](https://github.com/flet-dev/flet)



## Flet 개요

[![image.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2Ff35ea378-8e4f-1952-729a-295b8c453f1a.png)](https://camo.qiitausercontent.com/1d8d6f440d04e83677774db4b53819f2dc0967c8/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f66333565613337382d386534662d313935322d373239612d3239356238633435336631612e706e67)

Flet은 Flutter를 기반으로 합니다. 주로 다음과 같은 특징이 있습니다.

- From idea to app in minutes
  - "빠르게 GUI 앱을 만들 수 있다"는 것이 Flet의 주요 판매 포인트와 같습니다.
- Simple Architecture
  - JS 프런트 엔드 나 RestAPI를 작성하지 않고 Python만으로 SPA를 만들 수 있습니다.
- Batteries included
  - [Batteries Included](https://en.wikipedia.org/wiki/Batteries_Included) 는 Python의 디자인 사상과 같습니다. 건전지가 포함되어 있는, 즉 그대로라도 곧바로 움직일 수 있는 것을 가리킵니다.
- Powered by Flutter
  - Flet의 UI는 [Flutter](https://flutter.dev/) 로 구성되어 있어 깨끗한 UI를 제공할 수 있어 멀티 플랫폼에 대응하고 있습니다.
- Speaks your language
  - Flet 은 언어에 의존하지 않는 것을 목표로 하고 있습니다. 현재 지원되고 있는 것은 Python만입니다만, 향후 Go나 TypeScript, C#에도 대응해 나갈 예정인 것 같습니다.
- Deliver to any device
  - 여러 플랫폼(iOS, Android, Web, Windows, Mac, Linux)을 지원합니다.

모바일 지원은 현재 개발중인 것으로 보이며 [문서](https://flet.dev/docs/guides/python/mobile-support) 에는
로드맵과 비전이 게재되어 있습니다.

따라서 이 기사에서는 데스크톱 앱에 빌드하고 웹 앱으로 배포만을 다룹니다.

### Flet 사용 사례

정리하면 "익숙한 단일 언어로" "크로스 플랫폼" 앱을 개발할 수 있으므로 속도를 중시 할 때 특히 위력을 발휘한다고 생각됩니다.

사내용의 툴 개발 등에 적합하다고 말할 수 있을 것 같습니다.



https://flet.dev/docs/tutorials/trello-clone#why-flet 요약

많은 개발자들은 종종 다음과 같은 상황을 만날 수 있습니다.

- 사내 비개발자를 위해 비교적 소규모 앱을 개발해야 함
- CUI 앱을 개발하면 처음 예상보다 많은 사람들이 사용할 수 있다는 것을 알았기 때문에 GUI화하고 싶습니다.

이러한 상황에서 요구되는 것은 주로 속도이므로, electron과 같이 거대한 툴이나 flutter와 같은 기능 풍부한 프레임워크, .NET MAUI등의 크로스 플랫폼인 프레임워크는 적합하지 않은 경우가 많습니다.

Flet은 위와 같은 사용 사례에서 보기 좋고, 어느 정도 성능이 좋고, 신속하게 GUI 앱을 제공할 수 있습니다.



Flutter 보충

https://flutter.dev/

Flutter는 Google에서 개발한 오픈 소스 프레임워크입니다.
주요 특징에는 다음과 같은 것이 있습니다.

- 크로스 플랫폼에 대응하고 있어 원 소스로 복수의 플랫폼(iOS, Android, Web, Windows, Mac, Linux)에 대응한 앱을 개발할 수 있다
- Dart 언어로 개발
- 빠른 동작
- 핫 리로드 지원

## Flet의 기본

거의 [공식 튜토리얼](https://flet.dev/docs/guides/python/getting-started) 의 일본어 번역입니다.
절찬 개발중인 도서관이므로 최대한 공식 문서를 참조하십시오.

### 설치

```
pip install flet
```

Linux, WSL의 경우 추가 패키지 설치 및 설정이 필요합니다.
https://flet.dev/docs/guides/python/getting-started#linux 를 참조하십시오.

### Flet 앱의 기본형

```
import flet as ft

def main(page: ft.Page):
    # add/update controls on Page
    pass

ft.app(target=main)
```

- `main`함수는 Flet 애플리케이션의 엔드포인트입니다. 이 함수에는 `flet.Page`인스턴스가 전달됩니다.

- ```
  flet.app
  ```

  호출하면 응용 프로그램으로 실행됩니다.

  - 기본적으로 데스크톱 앱으로 시작됩니다.
  - `flet.app(view=ft.WEB_BROWSER)`와 같이 변경하는 것으로, 브라우저로 기동할 수가 있습니다.

### 내부적으로 웹 앱

모든 Flet 앱은 내부적으로 웹 앱으로, 네이티브 OS의 윈도우에서 열려 있어도, 빌트인의 웹 서버가 백그라운드에서 기동하고 있습니다.

Flet 웹 서버를 **Fletd** 라고 하며 기본적으로 임의의 TCP 포트에서 수신 대기합니다.

`flet.app(port=8550, target=main)`와 같이 포트를 지정하여 시작, 브라우저에서 `http://localhost:8550`열면 Flet 앱의 웹 버전이 표시됩니다.

[![Pasted image 20230103235053.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F422e09bc-7eb6-c530-d416-ee4cc9f100aa.png)](https://camo.qiitausercontent.com/e2ae3fca05729ba4e3b338eb0122e54abab358a5/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f34323265303962632d376562362d633533302d643431362d6565346363396631303061612e706e67)

## 제어

Flet UI는 Flutter 모델을 단순화한 [Control](https://flet.dev/docs/controls/) 로 구성됩니다.

※ 이하 「Control」은 「컨트롤」로 합니다

컨트롤은 다음 중 하나로 표시됩니다.

- 페이지에 추가
- 다른 컨트롤에 추가

**페이지** 는 최상위 컨트롤입니다.
컨트롤의 중첩은 **페이지를 루트로하는 트리** 로 표현됩니다.

### 컨트롤의 인스턴스 생성

컨트롤은 클래스로 구현됩니다.
예를 들어, `ft.Text`인스턴스를 생성하려면 다음을 수행합니다.

```
t = ft.Text(value="Hello, world!", color="green")
```

### 페이지에 컨트롤 추가

페이지에 컨트롤을 추가하려면 `page.controls`컨트롤의 인스턴스를 추가하고 `page.update`호출합니다.

```py
import flet as ft


def main(page: ft.Page):
    # Text Control 생성
    t = ft.Text(value="Hello, world!", color="green")
    # 페이지의 컨트롤 목록에 컨트롤 추가
    page.controls.append(t)
    # 페이지 업데이트
    page.update()


ft.app(target=main)
```

위의 코드는 다음과 같은 UI를 생성합니다.

[![Pasted image 20230104000342.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F168f359b-08a8-6f7c-4f8e-651b517234c6.png)](https://camo.qiitausercontent.com/945e5baf50cbeb6ba5198970b1d408b9739259c6/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f31363866333539622d303861382d366637632d346638652d3635316235313732333463362e706e67)

`page.add(control)`라는 `page.controls.append(control)`정리 `page.update()`한 함수도 준비되어 있습니다.

### 속성 업데이트

컨트롤의 속성을 업데이트한 후 `page.update`호출하면 페이지에 반영됩니다.

다음 코드는 0 ~ 9까지 초당 텍스트가 업데이트되는 텍스트를 생성합니다.

```
import time
import flet as ft

def main(page: ft.Page):
    t = ft.Text()
    page.add(t)

    for i in range(10):
        t.value = f"Step {i}"
        page.update()
        time.sleep(1)

ft.app(target=main)
```

### 컨테이너

`Page`그리고 `Row`같은 `Column`다른 컨트롤을 중첩 할 수있는 컨트롤도 있습니다.

```
import flet as ft

def main(page):
    row = ft.Row(controls=[ft.Text("A"), ft.Text("B"), ft.Text("C")])
    column = ft.Column(controls=[row, ft.Text("D")])
    page.add(column)

ft.app(target=main)
```

[![Pasted image 20230104154411.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F985a9a56-4119-e519-e24d-5189ea2129a1.png)](https://camo.qiitausercontent.com/03c64c7a742ddc55d9f83df6a9adbcdee89fdd7c/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f39383561396135362d343131392d653531392d653234642d3531383965613231323961312e706e67)

### 이벤트 핸들러

버튼 등의 컨트롤은, `ElevatedButton.on_click`같이, 사용자의 입력으로 발화하는 이벤트 핸들러를 가질 수가 있습니다.

```
def button_clicked(e):
    page.add(ft.Text("Clicked!"))

page.add(ft.ElevatedButton(text="Click me", on_click=button_clicked))
```

### `visible`속성

표시/숨기기를 전환하는 속성입니다. 모든 컨트롤에 디폴트로 `True`가 설정되어 있습니다.

`visible`를 `False`하면(자), 표시되지 않게 되는 것은 물론, 키보드나 마우스에 의해 선택할 수 없고, 이벤트도 발생하지 않게 됩니다.

### `disabled`속성

`TextField`, `Dropdown`, 버튼 등 사용자로부터 입력을 받는 컨트롤에서 주로 사용됩니다.

`disabled`는 부모 컨트롤로 설정할 수 있으며, 그 값은 재귀적으로 모든 자식 컨트롤에 전파됩니다.

### 참조

컨트롤은 객체이므로 속성에 액세스하려면 해당 객체에 대한 참조(변수)를 유지해야 합니다.

다음과 같은 예를 생각해 봅시다.

```
import flet as ft

def main(page):

    first_name = ft.TextField(label="First name", autofocus=True)
    last_name = ft.TextField(label="Last name")
    greetings = ft.Column()

    def btn_click(e):
        greetings.controls.append(ft.Text(f"Hello, {first_name.value} {last_name.value}!"))
        first_name.value = ""
        last_name.value = ""
        page.update()
        first_name.focus()

    page.add(
        first_name,
        last_name,
        ft.ElevatedButton("Say hello!", on_click=btn_click),
        greetings,
    )

ft.app(target=main)
```

이 코드는 다음과 유사한 페이지를 생성합니다.
[![RefExample](.\Images\https%3A%2F%2Fuser-images.githubusercontent.com%2F85564407%2F210390595-5a82d8fb-b5d0-48ab-9303-31d7eae8f0b2.gif)](https://camo.qiitausercontent.com/7c8ead9e3b5828e9577babe399c180f201e727b2/68747470733a2f2f757365722d696d616765732e67697468756275736572636f6e74656e742e636f6d2f38353536343430372f3231303339303539352d35613832643866622d623564302d343861622d393330332d3331643765616538663062322e676966)

`main`함수의 시작 부분에서 `on_click`사용하는 세 가지 컨트롤을 만듭니다.

이 상태로 컨트롤이나 이벤트 핸들러를 점점 추가해 나가면 컨트롤의 정의를 한 곳에 정리하는 것이 어려워 버릴 것 같습니다.
그 결과, `pages.add()`의 파라미터를 본 것만으로는, 어떠한 페이지가 가능한지 상상하는 것이 어려워져 버릴지도 모릅니다.

이러한 문제를 해결하기 위해 Flet은 컨트롤에 대한 참조를 정의하고 이벤트 핸들러에서 해당 참조를 사용하고 나중에 페이지를 만들 때 실제 컨트롤에 대한 참조를 설정할 수 있습니다 `Ref`. 유틸리티 클래스가 제공됩니다. (아시다시피, React로부터 착상을 얻고있는 것 같습니다.)

`Ref`를 정의하려면 다음을 수행합니다.

```
first_name = ft.Ref[ft.TextField]()
```

참조를 컨트롤에 할당하려면 다음을 수행합니다.

```
page.add(  
	ft.TextField(ref=first_name, label="First name", autofocus=True)  
)
```

`Ref`을 사용하여 이전 예를 다시 작성하면 다음과 같이 보입니다.

```
import flet as ft


def main(page):

    first_name = ft.Ref[ft.TextField]()
    last_name = ft.Ref[ft.TextField]()
    greetings = ft.Ref[ft.Column]()

    def btn_click(e):
        greetings.current.controls.append(
            ft.Text(f"Hello, {first_name.current.value} {last_name.current.value}!")
        )
        first_name.current.value = ""
        last_name.current.value = ""
        page.update()
        first_name.current.focus()

    page.add(
        ft.TextField(ref=first_name, label="First name", autofocus=True),
        ft.TextField(ref=last_name, label="Last name"),
        ft.ElevatedButton("Say hello!", on_click=btn_click),
        ft.Column(ref=greetings),
    )

ft.app(target=main)
```

이제 `page.add`의 호출을 보는 것만으로 페이지의 구조를 알게 되었습니다.

### 명령형 UI

여기까지 샘플을 보고 프런트 엔드 개발 경험이 있는 분은 「Flutter 베이스인데 선언형이 아닌가?」라고 하는 의문을 가진 사람도 있는 것은 아닐까요.

Flutter를 시작으로, 현재의 프런트 엔드 개발에 사용되는 프레임워크에서는, 데이터가 변경되면 자동적으로 UI가 재구축되는 선언적인 모델이 채용되는 경우가 많습니다.

반면에 Flet은 수동으로 속성을 업데이트하여 UI를 제어하는 명령형 모델을 구현합니다.

굳이 이 "오래된" 접근법을 채택하여 프런트 엔드 개발 경험이없는 프로그래머의 개발 장애물을 낮추는 목표가있는 것 같습니다.

> Flet implements imperative UI model where you "manually" build application UI with stateful controls and then mutate it by updating control properties. Flutter implements declarative model where UI is automatically re-built on application data changes. Managing application sta. complex task and Flet's "old-school" approach could be more attractive to programmers without frontend experience.
>
> https://flet.dev/docs/guides/python/getting-started

## 데스크톱 앱에 빌드

[공식 문서](https://flet.dev/docs/guides/python/packaging-desktop-app) 에서는 빌드 방법으로 Pyinstaller에 의한 빌드를 소개하고 있습니다.

여기서는 pyinstaller의 GUI 래퍼인 [auto-py-to-exe](https://github.com/brentvollebregt/auto-py-to-exe) 를 사용하여 빌드해 보겠습니다.

### auto-py-to-exe로 빌드

설치합니다.

```
pip install auto-py-to-exe
```

명령을 두드리고 시작합니다.

```
auto-py-to-exe
```

시작하면 아래와 같은 창이 표시된다고 생각합니다.

[![Pasted image 20230104092247.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F3cceb432-f908-33d5-1e81-7d813046d0ba.png)](https://camo.qiitausercontent.com/f09ced192c643542a541b9101dd72fab0c7195ed/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f33636365623433322d663930382d333364352d316538312d3764383133303436643062612e706e67)

필요한 사항을 입력합니다.

- Script Location : 배포할 Python 파일을 지정합니다. (여기에서는 `main.py`)
- Onefile : One file을 선택하면 문자 그대로 하나의 파일로 출력됩니다.
- Console Window : 콘솔을 표시할지 여부를 선택할 수 있습니다. GUI 앱의 경우 표시하지 않는 것이 좋습니다.

[![Pasted image 20230104092336.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F1759b609-b8d8-2ec3-1e44-1ea5c808d77a.png)](https://camo.qiitausercontent.com/d355a1944bd19d5438c3dacfe9311d3aa6bcea88/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f31373539623630392d623864382d326563332d316534342d3165613563383038643737612e706e67)

입력이 끝나면 "CONVERT ..."를 클릭하십시오.

잠시 후 아래와 같은 표시가 나오면 OK입니다.
"OPEN OUTPUT FOLDER"를 클릭하면 출력 대상 폴더로 이동합니다.
[![Pasted image 20230104093133.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F9e6cd6a1-5f8c-350f-4862-0f3e1da4d795.png)](https://camo.qiitausercontent.com/66b61fc0219931ad31015911723d4b3eff6254e9/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f39653663643661312d356638632d333530662d343836322d3066336531646134643739352e706e67)

### 빌드 결과

무사히 파일 단독으로 실행할 수 있게 되었습니다.

[![auto-py-to-exe-result](.\Images\https%3A%2F%2Fuser-images.githubusercontent.com%2F85564407%2F210463174-34634593-d914-4bee-ab80-af7be86c2ca3.gif)](https://camo.qiitausercontent.com/4a13d49915a6542c68a8bfa77c382ca2a4c99b7a/68747470733a2f2f757365722d696d616765732e67697468756275736572636f6e74656e742e636f6d2f38353536343430372f3231303436333137342d33343633343539332d643931342d346265652d616238302d6166376265383663326361332e676966)

수중의 Windows 환경에서도 시험해 보았습니다만, 똑같이 동작했습니다.

## 웹 앱으로 배포

웹 앱으로 배포할 수도 있습니다.

### 웹 소켓

Flet은 UI의 실시간 부분 업데이트와 프로그램에 이벤트를 보내는 데 WebSocket을 사용합니다.
따라서 호스팅 공급자를 선택할 때 WebSocket을 지원하는지 확인해야 합니다.

배포 대상으로 [공식 문서](https://flet.dev/docs/guides/python/deploying-web-app) 에서 소개된 것은 [fly.io](https://flet.dev/docs/guides/python/deploying-web-app/fly-io) 와 [Replit](https://flet.dev/docs/guides/python/deploying-web-app/replit) 입니다.
이 기사에서는 fly.io에 대해 다룹니다.

### fly.io

fly.io 에는 다음과 같은 특징이 있습니다.

> - `Dockerfile` 사용할 수 있으므로 특정 언어와 런타임에 의존하지 않습니다.
> - 배포된 앱은 각 지역의 Edge Server에서 실행되며 빠른 응답을 기대할 수 있습니다.
> - 도쿄 Region이 있다
> - 특별한 설정없이 자동으로 HTTPS URL을 제공합니다.
> - 매니지드인 PostgreSQL이 있다( [무료 테두리 첨부!](https://fly.io/blog/free-postgres/) )
> - 동일한 조직에서 배포한 앱은 WireGuard VPN을 공유하고 `<app-name>.internal` 주소로 서로 액세스할 수 있습니다 .
>
> [fly.io에 Pleroma 설치 - castaneai](https://castaneai.hatenablog.com/entry/pleroma-on-fly-io) 블로그

### fly에 로그인

[Install flyctl · Fly Docs](https://fly.io/docs/hands-on/install-flyctl/) 를 참조하여 `flyctl` 설치하십시오.

```
brew install flyctl
```

다음 명령을 사용하여 fly에 로그인하고 (Github 인증을 사용할 수 있음) 지불 정보를 등록합니다.

```
flyctl auth login
```

### 배포 준비

여기에서는 다음 파일을 준비합니다.

- `requiments.txt`
- `fly.toml`
- `Dockerfile`

requiments.txt

```
flet>=0.1.33
```

`flet-getting-started`곳을 원하는 이름으로 변경하십시오.

fly.toml

```
app = "flet-getting-started"

kill_signal = "SIGINT"
kill_timeout = 5
processes = []

[env]
  FLET_SERVER_PORT = "8080"

[experimental]
  allowed_public_ports = []
  auto_rollback = true

[[services]]
  http_checks = []
  internal_port = 8080
  processes = ["app"]
  protocol = "tcp"
  script_checks = []

  [services.concurrency]
    hard_limit = 25
    soft_limit = 20
    type = "connections"

  [[services.ports]]
    force_https = true
    handlers = ["http"]
    port = 80

  [[services.ports]]
    handlers = ["tls", "http"]
    port = 443

  [[services.tcp_checks]]
    grace_period = "1s"
    interval = "15s"
    restart_limit = 0
    timeout = "2s"
FROM python:3-alpine

WORKDIR /app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 8080

CMD ["python", "./main.py"]
```

### 배포

다음 명령으로 Fly 앱을 빌드하고 배포할 수 있습니다.

```
flyctl apps create --name flet-getting-started

flyctl deploy
```

확인해 보겠습니다.

```
flyctl apps open
```

[![Pasted image 20230104151822.png](.\Images\https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F1224564%2F704b03a0-3817-aefd-f516-58f92af4cd77.png)](https://camo.qiitausercontent.com/98ed6d484a1953b768942978db316babefb8d950/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f313232343536342f37303462303361302d333831372d616566642d663531362d3538663932616634636437372e706e67)

안전하게 배포 할 수있었습니다.

### 삭제

더 이상 필요하지 않은 앱은 삭제해 둡시다.

```
flyctl apps destroy flet-getting-started
```

## 요약

이상, Flet의 소개였습니다.

저는 비개발자를 위한 간단한 툴을 만드는 경우가 많기 때문에 Python으로 조금 코드를 작성하면 웹 데스크톱에 대응하는 앱을 만들 수 있다는 점은 매우 매력적입니다. 모바일 대응도 기대됩니다.

여담입니다만, 이 기사를 쓸 때 지금 화제의 [ChatGPT](https://chat.openai.com/chat) 를 사용해 보았습니다.
「글머리 기호에 요약해」 「첨삭해」등의 말의 뒤에 [DeepL](https://www.deepl.com/translator) 로 번역한 문서를 밀어넣는 것으로, 거친 개요를 파악할 수 있습니다.

원문을 읽지 않으면 의미를 모르거나, 「요약해」때에 중요한데 버려져 버리는 부분이 다소 있거나 합니다만, 처음으로 취급하는 라이브러리의 문서를 읽을 때에는 꽤 편리할 것 같습니다.

## 참고

- 공식 문서 : 

  https://flet.dev/docs/

  - 컨트롤 참조 : https://flet.dev/docs/controls
  - Todo 앱, Trello 클론 등의 간단한 앱 만들기 : https://flet.dev/docs/tutorials

- 샘플 앱 리포지토리 : https://github.com/flet-dev/examples/tree/main/python



---

예제: https://choiseokwon.tistory.com/359

---





## 튜토리얼

https://flet.dev/docs/tutorials



## 공식 홈페이지

https://flet.dev/

