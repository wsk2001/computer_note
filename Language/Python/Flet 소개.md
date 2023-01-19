# Flet 소개

출처: https://flet.dev/docs/



## Introduction

### 플렛이란?

Flet은 사전 프런트엔드 개발 경험 없이도 원하는 언어로 대화형 다중 사용자 웹, 데스크톱 및 모바일 애플리케이션을 구축할 수 있는 프레임워크입니다.

Flutter by Google을 기반으로 하는 Flet 컨트롤을 사용하여 프로그램의 UI를 빌드합니다. Flet은 단순히 Flutter 위젯을 '래핑'하는 것이 아니라 더 작은 위젯을 결합하고, 복잡성을 숨기고, UI 모범 사례를 구현하고, 합리적인 기본값을 적용하여 자체적인 '의견'을 추가하여 추가 노력 없이도 앱이 멋지고 전문적으로 보이도록 합니다.



### Flet 앱 예시

현재 Python으로 Flet 앱을 작성할 수 있으며 다른 언어도 곧 추가될 예정입니다.

다음은 샘플 '카운터' 앱입니다.

`counter.py`

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Flet counter example"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER

    txt_number = ft.TextField(value="0", text_align=ft.TextAlign.RIGHT, width=100)

    def minus_click(e):
        txt_number.value = str(int(txt_number.value) - 1)
        page.update()

    def plus_click(e):
        txt_number.value = str(int(txt_number.value) + 1)
        page.update()

    page.add(
        ft.Row(
            [
                ft.IconButton(ft.icons.REMOVE, on_click=minus_click),
                txt_number,
                ft.IconButton(ft.icons.ADD, on_click=plus_click),
            ],
            alignment=ft.MainAxisAlignment.CENTER,
        )
    )

ft.app(target=main)
```

앱 설치 플릿 모듈을 실행하려면:

``` bash
pip install flet
```

프로그램 실행 방법

``` bash
python counter.py
```

앱은 기본 OS 창에서 시작됩니다. Electron의 훌륭한 대안입니다!

###  macOS

![img](.\Images\flet-counter-macos.png)

### Windows

![img](.\Images\flet-counter-windows.png)

이제 앱을 웹 앱으로 실행하려면 마지막 줄을 다음으로 바꾸십시오.

```python
ft.app(target=main, view=ft.WEB_BROWSER)
```



다시 실행하면 이제 즉시 웹 앱을 얻을 수 있습니다.

![img](.\Images\flet-counter-safari.png)

## Tutorials[](https://flet.dev/docs/#tutorials)

실제 앱을 구축하는 방법을 배우고 싶습니까? 귀하의 언어에 대한 시작 안내서로 이동하십시오.

- [Python](https://flet.dev/docs/guides/python/getting-started)



## Python Guide

### Getting Started

#### Python에서 Flet 앱 만들기

Flet 앱을 작성하기 위해 프런트 엔드 전문가가 될 필요는 없지만 Python 및 객체 지향 프로그래밍에 대한 기본 지식이 있는 것이 좋습니다.

이 가이드에서는 Flet 앱의 구조를 연구하고, Flet 컨트롤을 사용하여 데이터를 출력하는 방법을 배우고, 사용자에게 데이터를 요청하고, 기본 페이지 레이아웃을 구축합니다. 또한 준비된 앱을 사용자에게 제공하기 위한 일부 패키징 및 배포 옵션에 대해서도 다룹니다.

##### 플릿 모듈 설치

Flet에는 Python 3.7 이상이 필요합니다. Flet을 시작하려면 먼저 flet 모듈을 설치해야 합니다.

``` bash
pip install flet
```

> 노트
> Flet 모듈을 업그레이드하려면 다음을 실행하십시오.
>
> ```bash
> pip install flet --upgrade
> ```

> 주의
> 시험판 빌드를 가상 환경에 설치하는 것이 좋습니다.

##### Linux

Linux 및 WSL에서 Flet 앱을 실행하려면 GStreamer 라이브러리가 설치되어 있어야 합니다. 대부분의 경우 이미 시스템에 있지만 공유 라이브러리를 로드하는 동안 오류가 발생하는 경우: libgstapp-1.0.so.0: cannot open shared object file: No such file or directory while running Flet app 그런 다음 GStreamer를 설치해야 합니다. .

Ubuntu/Debian에 GStreamer를 설치하려면 다음 명령을 실행하십시오.

``` bash
sudo apt-get update
sudo apt-get install libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev libgstreamer-plugins-bad1.0-dev gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad gstreamer1.0-plugins-ugly gstreamer1.0-libav gstreamer1.0-doc gstreamer1.0-tools gstreamer1.0-x gstreamer1.0-alsa gstreamer1.0-gl gstreamer1.0-gtk3 gstreamer1.0-qt5 gstreamer1.0-pulseaudio
```

다른 Linux 배포판에 설치하려면 [이 가이드](https://gstreamer.freedesktop.org/documentation/installing/on-linux.html?gi-language=c)를 참조하세요.

##### WSL

Flet 앱은 WSL2에서 실행할 수 있습니다. 디스플레이를 열 수 없다는 오류가 발생 하는 경우 문제 해결을 위해 이 [가이드](https://github.com/microsoft/wslg/wiki/Diagnosing-%22cannot-open-display%22-type-issues-with-WSLg)를 따라 하십시요 .



#### 기본 앱 구조

아주 작은 Flet 앱의 구조는 다음과 같습니다.

``` py
import flet as ft

def main(page: ft.Page):
    # add/update controls on Page
    pass

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/basic-app-structure.png)

> NOTE
>
> 이 섹션은 이 가이드의 뒷부분에서 재사용 가능한 컨트롤을 사용하여 앱 구조에 대한 보다 실제적인 접근 방식을 살펴볼 것이므로 의도적으로 '기본'이라고 합니다.



일반적인 Flet 프로그램은 앱이 새 사용자 세션을 기다리기 시작하는 flet.app() 호출로 끝납니다. main() 함수는 Flet 애플리케이션의 진입점입니다. Page 인스턴스가 전달된 모든 사용자 세션에 대해 새 스레드에서 호출됩니다. 브라우저에서 Flet 앱을 실행할 때 열려 있는 모든 탭 또는 페이지에 대해 새 사용자 세션이 시작됩니다. 데스크탑 앱으로 실행할 때 하나의 세션만 생성됩니다.

페이지는 사용자 세션의 시각적 상태인 사용자 고유의 '캔버스'와 같습니다. 응용 프로그램 UI를 구축하려면 페이지에 컨트롤을 추가 및 제거하고 해당 속성을 업데이트합니다. 위의 코드 샘플은 모든 사용자에게 빈 페이지만 표시합니다.

기본적으로 Flet 앱은 기본 OS 창에서 시작하므로 개발에 매우 편리합니다. 그러나 다음과 같이 flet.app에 대한 호출을 수정하여 새 브라우저 창에서 열 수 있습니다.

``` py
ft.app(target=main, view=ft.WEB_BROWSER)
```

>정보
>내부적으로 모든 Flet 앱은 웹 앱이며 기본 OS 창에서 열리더라도 내장 웹 서버는 여전히 백그라운드에서 시작됩니다. Flet 웹 서버는 'Fletd'라고 하며 기본적으로 임의의 TCP 포트에서 수신 대기합니다. 사용자 지정 TCP 포트를 지정한 다음 데스크톱 보기와 함께 브라우저에서 앱을 열 수 있습니다.
>
>``` py
>flet.app(port=8550, target=main)
>```
>
>Flet 앱의 웹 버전을 보려면 브라우저에서 http://localhost: port를 엽니다.



#### Controls

사용자 인터페이스는 컨트롤(일명 위젯)로 구성됩니다. 컨트롤을 사용자에게 표시하려면 페이지 또는 다른 컨트롤 내부에 컨트롤을 추가해야 합니다. 페이지는 최상위 컨트롤입니다. 컨트롤을 서로 중첩하는 것은 페이지를 루트로 하는 트리로 나타낼 수 있습니다.

컨트롤은 일반 Python 클래스일 뿐입니다. 속성과 일치하는 매개변수가 있는 생성자를 통해 컨트롤 인스턴스를 만듭니다. 예를 들면 다음과 같습니다.

``` py
t = ft.Text(value="Hello, world!", color="green")
```

페이지에 컨트롤을 표시하려면 페이지의 컨트롤 목록에 컨트롤을 추가하고 page.update()를 호출하여 브라우저 또는 데스크톱 클라이언트에 페이지 변경 사항을 보냅니다.

``` py
import flet as ft

def main(page: ft.Page):
    t = ft.Text(value="Hello, world!", color="green")
    page.controls.append(t)
    page.update()

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/controls-text.png)

> NOTE
>
> 다음 예제에서는 기본 기능의 내용만 표시합니다.



컨트롤 속성을 수정할 수 있으며 UI는 다음 page.update()에서 업데이트됩니다.

``` py
t = ft.Text()
page.add(t) # it's a shortcut for page.controls.append(t) and then page.update()

for i in range(10):
    t.value = f"Step {i}"
    page.update()
    time.sleep(1)
```

일부 컨트롤은 다른 컨트롤을 포함할 수 있는 '컨테이너' 컨트롤(예: 페이지)입니다. 예를 들어 행 컨트롤을 사용하면 다른 컨트롤을 한 행에 하나씩 정렬할 수 있습니다.

``` py
page.add(
    ft.Row(controls=[
        ft.Text("A"),
        ft.Text("B"),
        ft.Text("C")
    ])
)
```

또는 옆에 있는 TextField 및 ElevatedButton:

``` py
page.add(
    ft.Row(controls=[
        ft.TextField(label="Your name"),
        ft.ElevatedButton(text="Say my name!")
    ])
)
```

page.update()는 마지막 호출 이후 변경된 사항만 보낼 수 있을 만큼 똑똑하므로 페이지에 몇 가지 새로운 컨트롤을 추가하고 일부를 제거하고 다른 컨트롤의 속성을 변경한 다음 page.update()를 호출할 수 있습니다. 예를 들어 일괄 업데이트를 수행하려면 다음과 같이 하십시오.

``` py
for i in range(10):
    page.controls.append(ft.Text(f"Line {i}"))
    if i > 4:
        page.controls.pop(0)
    page.update()
    sleep(0.3)
```

버튼과 같은 일부 컨트롤에는 ElevatedButton.on_click과 같이 사용자 입력에 반응하는 이벤트 핸들러가 있을 수 있습니다.

``` py
def button_clicked(e):
    page.add(ft.Text("Clicked!"))

page.add(ft.ElevatedButton(text="Click me", on_click=button_clicked))
```

간단한 To-Do에 대한 고급 예제:

``` py
import flet as ft

def main(page):
    def add_clicked(e):
        page.add(ft.Checkbox(label=new_task.value))

    new_task = ft.TextField(hint_text="Whats needs to be done?", width=300)
    page.add(ft.Row([new_task, ft.ElevatedButton("Add", on_click=add_clicked)]))

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/simple-ToDo.png)

>  INFO
>
> Flet은 상태 저장 컨트롤을 사용하여 애플리케이션 UI를 '수동으로' 빌드한 다음 컨트롤 속성을 업데이트하여 변경하는 명령형 UI 모델을 구현합니다. Flutter는 애플리케이션 데이터 변경 시 UI가 자동으로 다시 빌드되는 선언적 모델을 구현합니다. 최신 프런트엔드 애플리케이션에서 애플리케이션 상태를 관리하는 것은 본질적으로 복잡한 작업이며 Flet의 '구식' 접근 방식은 프런트엔드 경험이 없는 프로그래머에게 더 매력적일 수 있습니다.



##### `visible` property

모든 컨트롤에는 기본적으로 true인 표시 속성(`visible` property)이 있습니다. 컨트롤은 페이지에 렌더링됩니다. false로 설정하면 컨트롤(및 해당하는 경우 모든 자식)이 페이지 캔버스에서 렌더링되지 않습니다. 숨겨진 컨트롤은 키보드나 마우스로 포커스를 맞추거나 선택할 수 없으며 어떤 이벤트도 발생시키지 않습니다.

##### `disabled` property

모든 컨트롤에는 기본적으로 false인 비활성화 속성(`disabled` property)이 있습니다. 컨트롤과 모든 자식이 활성화됩니다. disabled 속성은 주로 TextField, Dropdown, Checkbox, 버튼과 같은 데이터 입력 컨트롤과 함께 사용됩니다. 그러나 비활성화를 상위 컨트롤로 설정할 수 있으며 해당 값은 재귀적으로 모든 하위에 전파됩니다.

예를 들어 다중 항목 컨트롤이 있는 양식이 있는 경우 각 컨트롤에 대해 비활성화된 속성을 개별적으로 설정할 수 있습니다.

``` py
first_name = ft.TextField()
last_name = ft.TextField()
first_name.disabled = True
last_name.disabled = True
page.add(first_name, last_name)
```

또는 양식 컨트롤을 컨테이너에 넣을 수 있습니다. 열을 선택한 다음 열에 대해 비활성화를 설정합니다.

``` py
first_name = ft.TextField()
last_name = ft.TextField()
c = ft.Column(controls=[
    first_name,
    last_name
])
c.disabled = True
page.add(c)
```



#### 컨트롤 참조

Flet 컨트롤은 개체이며 해당 속성에 액세스하려면 해당 개체에 대한 참조(변수)를 유지해야 합니다.

다음 예를 참고하십시오.

``` py
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

![img](https://flet.dev/img/docs/getting-started/control-refs.png)

main() 메서드의 맨 처음에 우리는 버튼의 on_click 핸들러에서 사용할 세 개의 컨트롤을 생성합니다. 두 개의 TextField는 이름과 성을 위한 것이고 Column은 인사말 메시지를 위한 컨테이너입니다. 모든 속성이 설정된 컨트롤을 만들고 main() 메서드의 끝에서 page.add() 호출에서 해당 참조(변수)를 사용합니다.

점점 더 많은 컨트롤과 이벤트 핸들러가 추가되면 모든 컨트롤 정의를 한 곳에 유지하는 것이 어려워져 main() 본문 전체에 분산됩니다. page.add() 매개변수를 보면(IDE에서 변수 정의로 계속 점프하지 않고) 최종 양식이 어떻게 생겼는지 상상하기 어렵습니다.

``` py
    page.add(
        first_name,
        last_name,
        ft.ElevatedButton("Say hello!", on_click=btn_click),
        greetings,
    )
```

first_name은 TextField이고 자동 초점이 설정되어 있습니까? 인사말은 행입니까 아니면 열입니까?

Flet은 컨트롤에 대한 참조를 정의하고, 이벤트 핸들러에서 해당 참조를 사용하고, 트리를 구축하는 동안 나중에 실제 컨트롤에 대한 참조를 설정할 수 있는 Ref 유틸리티 클래스를 제공합니다. 아이디어는 React에서 나옵니다.

새로운 유형의 제어 참조를 정의하려면 다음을 수행하십시오.

``` py
first_name = ft.Ref[ft.TextField]()
```

참조된 컨트롤(컨트롤 역참조)에 액세스하려면 Ref.current 속성을 사용합니다.

``` py
# empty first name
first_name.current.value = ""
```

컨트롤을 참조에 할당하려면 Control.ref 속성을 참조로 설정합니다.

``` py
page.add(
    ft.TextField(ref=first_name, label="First name", autofocus=True)
)
```

>노트
>모든 Flet 컨트롤에는 ref 속성이 있습니다.

참조를 사용하도록 프로그램을 다시 작성할 수 있습니다.

``` py
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

![img](https://flet.dev/img/docs/getting-started/control-refs-rewritten.png)

이제 page.add()에서 페이지의 구조와 모든 컨트롤을 명확하게 볼 수 있습니다.

예, .current를 추가해야 하므로 논리가 좀 더 장황해집니다. ref의 컨트롤에 액세스하려면 개인 취향의 문제입니다 :)



---

### Getting user input

https://flet.dev/docs/guides/python/getting-user-input

Flet으로 대화형 웹 앱을 만드는 것은 매우 쉽습니다! 데이터 표시에만 국한되지 않고 사용자에게 입력을 요청하고 페이지 컨트롤에서 생성되는 다양한 이벤트에 응답할 수 있습니다.

#### Buttons

버튼은 누를 때 클릭 이벤트를 생성하는 가장 필수적인 입력 컨트롤입니다.

``` py
btn = ft.ElevatedButton("Click me!")
page.add(btn)
```

![img](https://flet.dev/img/docs/getting-started/getting-user-input-elevated-button.png)

웹 페이지의 컨트롤에 의해 생성된 모든 이벤트는 계속해서 스크립트로 다시 전송되므로 버튼 클릭에 어떻게 응답합니까?

#### Event handlers

'카운터' 앱의 이벤트가 있는 버튼:

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Flet counter example"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER

    txt_number = ft.TextField(value="0", text_align="right", width=100)

    def minus_click(e):
        txt_number.value = str(int(txt_number.value) - 1)
        page.update()

    def plus_click(e):
        txt_number.value = str(int(txt_number.value) + 1)
        page.update()

    page.add(
        ft.Row(
            [
                ft.IconButton(ft.icons.REMOVE, on_click=minus_click),
                txt_number,
                ft.IconButton(ft.icons.ADD, on_click=plus_click),
            ],
            alignment=ft.MainAxisAlignment.CENTER,
        )
    )

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/getting-user-input-event-handlers.png)

#### Textbox

Flet은 TextField, Checkbox, Dropdown, ElevatedButton과 같은 양식 작성을 위한 여러 컨트롤을 제공합니다.

사용자에게 이름을 물어봅시다:

``` py
import flet as ft

def main(page):
    def btn_click(e):
        if not txt_name.value:
            txt_name.error_text = "Please enter your name"
            page.update()
        else:
            name = txt_name.value
            page.clean()
            page.add(ft.Text(f"Hello, {name}!"))

    txt_name = ft.TextField(label="Your name")

    page.add(txt_name, ft.ElevatedButton("Say hello!", on_click=btn_click))

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/getting-user-input-textbox.png)

#### Checkbox

체크 컨트롤은 사용하기 쉽도록 다양한 속성 및 이벤트 방출기를 제공합니다.

하나의 확인란 ToDo를 만들어 봅시다.

```py
import flet as ft


def main(page):
    def checkbox_changed(e):
        output_text.value = (
            f"You have learned how to ski :  {todo_check.value}."
        )
        page.update()

    output_text　= ft.Text()
    todo_check = ft.Checkbox(label="ToDo: Learn how to use ski", value=False, on_change=checkbox_changed)
    page.add(todo_check, output_text)

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/getting-user-input-checkbox.png)

#### Dropdown

``` py
import flet as ft


def main(page: ft.Page):
    def button_clicked(e):
        output_text.value = f"Dropdown value is:  {color_dropdown.value}"
        page.update()

    output_text = ft.Text()
    submit_btn = ft.ElevatedButton(text="Submit", on_click=button_clicked)
    color_dropdown = ft.Dropdown(
        width=100,
        options=[
            ft.dropdown.Option("Red"),
            ft.dropdown.Option("Green"),
            ft.dropdown.Option("Blue"),
        ],
    )
    page.add(color_dropdown, submit_btn, output_text)

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/getting-user-input-dropdown.png)



---

### 키보드 단축키

견고한 키보드 지원은 웹, 특히 데스크톱 앱을 사용하는 동안 사용자 생산성의 핵심입니다. 실제로 마우스와 키보드 사이를 계속 전환하는 것은 정말 성가신 일입니다.

양식 컨트롤의 .autofocus 속성 및 TextField.focus() 메서드 외에도 Flet은 '전역' 키보드 이벤트를 처리할 수 있습니다.

모든 키 입력을 캡처하려면 page.on_keyboard_event 핸들러를 구현하십시오. 이벤트 처리기 매개 변수 e는 다음 속성이 있는 KeyboardEvent 클래스의 인스턴스입니다.

- `key` - 눌린 키의 텍스트 표현, 예:. `A`, `Enter` or `F5`.
- `shift` - `True` 'Shift' 키를 누른 경우.
- `ctrl` - `True` '컨트롤' 키를 누른 경우.
- `alt` - `True` 'Alt'('Option') 키를 누른 경우.
- `meta` - `True` 'Command' 키를 누른 경우.

다음은 간단한 사용 예입니다.

``` py
import flet as ft

def main(page: ft.Page):
    def on_keyboard(e: ft.KeyboardEvent):
        page.add(
            ft.Text(
                f"Key: {e.key}, Shift: {e.shift}, Control: {e.ctrl}, Alt: {e.alt}, Meta: {e.meta}"
            )
        )

    page.on_keyboard_event = on_keyboard
    page.add(
        ft.Text("Press any key with a combination of CTRL, ALT, SHIFT and META keys...")
    )

ft.app(target=main)
```

![img](https://flet.dev/img/docs/getting-started/keyboard-shortcuts.png)

---

### Large lists

출처: https://flet.dev/docs/guides/python/large-lists

대부분의 경우 열 및 행 컨트롤을 사용하여 목록을 표시할 수 있지만 목록에 수백 또는 수천 개의 항목이 포함된 경우 열 및 행은 현재 스크롤링에서 보이지 않더라도 모든 항목을 한 번에 렌더링하므로 지연 UI로 인해 효과가 없습니다. 위치.

다음 예에서는 페이지에 5,000개의 텍스트 컨트롤을 추가합니다. 페이지는 열을 기본 레이아웃 컨테이너로 사용합니다.

``` py
import flet as ft

def main(page: ft.Page):
    for i in range(5000):
        page.controls.append(ft.Text(f"Line {i}"))
    page.scroll = "always"
    page.update()

ft.app(target=main, view=ft.WEB_BROWSER)
```

프로그램을 실행하고 페이지의 모든 텍스트 줄을 처음 로드하고 렌더링하는 데 몇 초가 걸릴 뿐만 아니라 스크롤도 느리고 느리다는 것을 확인하십시오.


![img](https://flet.dev/img/docs/getting-started/scroll-column.gif)

항목이 많은 목록을 표시하려면 현재 스크롤 위치에서만 볼 수 있는 요청 시 항목을 렌더링하는 [ListView](https://flet.dev/docs/controls/listview) 및 [GridView](https://flet.dev/docs/controls/gridview) 컨트롤을 사용합니다.



#### ListView

ListView는 세로(기본값) 또는 가로일 수 있습니다. ListView 항목은 스크롤 방향으로 차례로 표시됩니다.

ListView는 이미 해당 자식의 효과적인 온디맨드 렌더링을 구현하지만 모든 항목('범위')에 대해 동일한 고정 높이 또는 너비(가로 ListView의 경우)를 설정할 수 있는 경우 스크롤 성능을 더욱 향상시킬 수 있습니다. 이것은 item_extent 속성으로 절대 범위를 설정하거나 first_item_prototype을 True로 설정하여 모든 자식의 범위를 첫 번째 자식의 범위와 같게 만들어 수행할 수 있습니다.

ListView 컨트롤을 사용하여 5,000개 항목의 목록을 출력해 보겠습니다.

``` py
import flet as ft

def main(page: ft.Page):
    lv = ft.ListView(expand=True, spacing=10)
    for i in range(5000):
        lv.controls.append(ft.Text(f"Line {i}"))
    page.add(lv)

ft.app(target=main, view=ft.WEB_BROWSER)
```

이제 스크롤이 부드럽고 빨라 마우스 움직임을 따라갈 수 있습니다.


![img](https://flet.dev/img/docs/getting-started/scroll-listview.gif)



>  NOTE
>
> 우리는 ListView 생성자에서 expand=True를 사용했습니다. 제대로 작동하려면 ListView에 지정된 높이(또는 가로인 경우 너비)가 있어야 합니다. 예를 들어 절대 크기를 설정할 수 있습니다. ListView(높이=300, 간격=10)이지만 위의 예에서는 페이지에서 사용 가능한 모든 공간을 차지하도록 ListView를 만듭니다. Control.expand 속성에 대해 자세히 읽어보십시오.



#### GridView

GridView를 사용하면 스크롤 가능한 그리드에 컨트롤을 정렬할 수 있습니다.

예를 들어 ft.Column(wrap=True) 또는 ft.Row(wrap=True)를 사용하여 '그리드'를 만들 수 있습니다.

``` py
import os
import flet as ft

os.environ["FLET_WS_MAX_MESSAGE_SIZE"] = "8000000"

def main(page: ft.Page):
    r = ft.Row(wrap=True, scroll="always", expand=True)
    page.add(r)

    for i in range(5000):
        r.controls.append(
            ft.Container(
                ft.Text(f"Item {i}"),
                width=100,
                height=100,
                alignment=ft.alignment.center,
                bgcolor=ft.colors.AMBER_100,
                border=ft.border.all(1, ft.colors.AMBER_400),
                border_radius=ft.border_radius.all(5),
            )
        )
    page.update()

ft.app(target=main, view=ft.WEB_BROWSER)
```

