# Flet 소개

출처: https://flet.dev/docs/



## Introduction

### 플렛이란?

Flet은 사전 프런트엔드 개발 경험 없이도 원하는 언어로 대화형 다중 사용자 웹, 데스크톱 및 모바일 애플리케이션을 구축할 수 있는 프레임워크입니다.

Flutter by Google을 기반으로 하는 Flet 컨트롤을 사용하여 프로그램의 UI를 빌드합니다. Flet은 단순히 플러터(Flutter) 위젯을 '래핑'하는 것이 아니라 더 작은 위젯을 결합하고, 복잡성을 숨기고, UI 모범 사례를 구현하고, 합리적인 기본값을 적용하여 자체적인 '의견'을 추가하여 추가 노력 없이도 앱이 멋지고 전문적으로 보이도록 합니다.



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

![img](D:\GitHub\computer_note\Language\Python\Images\basic-app-structure.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\controls-text.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\simple-ToDo.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\control-refs.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\control-refs-rewritten.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\getting-user-input-elevated-button.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\getting-user-input-event-handlers.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\getting-user-input-textbox.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\getting-user-input-checkbox.png)

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

![img](D:\GitHub\computer_note\Language\Python\Images\getting-user-input-dropdown.png)



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

![img](D:\GitHub\computer_note\Language\Python\Images\keyboard-shortcuts.png)

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


![img](D:\GitHub\computer_note\Language\Python\Images\scroll-column.gif)

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


![img](D:\GitHub\computer_note\Language\Python\Images\scroll-listview.gif)



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

![img](D:\GitHub\computer_note\Language\Python\Images\row-wrap-as-grid.png)

브라우저 창을 스크롤하고 크기를 조정해 보십시오. 모든 것이 작동하지만 매우 느립니다.

> **NOTE**
>
> 프로그램 시작 시 FLET_WS_MAX_MESSAGE_SIZE 환경 변수 값을 8000000으로 설정합니다. 이는 페이지를 렌더링하는 Flet Server에서 수신할 수 있는 WebSocket 메시지의 최대 크기(바이트)입니다. 기본 크기는 1MB이지만 5,000개의 컨테이너 컨트롤을 설명하는 JSON 메시지의 크기는 1MB를 초과하므로 허용되는 크기를 8MB로 늘립니다.
>
> WebSocket 채널을 통해 큰 메시지를 압축하는 것은 일반적으로 좋은 생각이 아니므로 일괄 업데이트 방식을 사용하여 채널 로드를 제어합니다.

ListView와 유사한 GridView는 많은 자식을 렌더링하는 데 매우 효과적입니다. GridView를 사용하여 위의 예를 구현해 보겠습니다.

``` py
import os
import flet as ft

os.environ["FLET_WS_MAX_MESSAGE_SIZE"] = "8000000"

def main(page: ft.Page):
    gv = ft.GridView(expand=True, max_extent=150, child_aspect_ratio=1)
    page.add(gv)

    for i in range(5000):
        gv.controls.append(
            ft.Container(
                ft.Text(f"Item {i}"),
                alignment=ft.alignment.center,
                bgcolor=ft.colors.AMBER_100,
                border=ft.border.all(1, ft.colors.AMBER_400),
                border_radius=ft.border_radius.all(5),
            )
        )
    page.update()

ft.app(target=main, view=ft.WEB_BROWSER)
```

![img](D:\GitHub\computer_note\Language\Python\Images\grid-view.png)

GridView를 사용하면 스크롤 및 창 크기 조정이 부드럽고 반응이 빠릅니다!

run_count 속성을 사용하여 행 또는 열(실행)의 고정 수를 지정하거나 max_extent 속성을 사용하여 '타일'의 최대 크기를 지정할 수 있으므로 실행 수가 자동으로 달라질 수 있습니다. 이 예에서는 최대 타일 크기를 150픽셀로 설정하고 그 모양을 child_aspect_ratio=1로 '정사각형'으로 설정했습니다. child_aspect_ratio는 각 자식의 주축 범위에 대한 교차축의 비율입니다. 0.5 또는 2로 변경해 보십시오.



#### Batch updates

page.update()가 호출되면 메시지는 마지막 page.update() 이후 페이지 업데이트를 포함하는 WebSocket을 통해 Flet 서버로 전송됩니다. 수천 개의 컨트롤이 추가된 대용량 메시지를 보내면 메시지가 완전히 수신되고 컨트롤이 렌더링될 때까지 사용자가 몇 초 동안 기다려야 할 수 있습니다.

프로그램의 유용성을 높이고 가능한 한 빨리 사용자에게 결과를 제공하기 위해 페이지 업데이트를 일괄적으로 보낼 수 있습니다. 예를 들어 다음 프로그램은 500개 항목의 일괄 처리로 ListView에 5,100개의 자식 컨트롤을 추가합니다.

``` py
import flet as ft

def main(page: ft.Page):

    # add ListView to a page first
    lv = ft.ListView(expand=1, spacing=10, item_extent=50)
    page.add(lv)

    for i in range(5100):
        lv.controls.append(ft.Text(f"Line {i}"))
        # send page to a page
        if i % 500 == 0:
            page.update()
    # send the rest to a page
    page.update()

ft.app(target=main, view=ft.WEB_BROWSER)
```

![img](D:\GitHub\computer_note\Language\Python\Images\sending-page-updates-in-batches.png)





---

### Drag and drop

Flet에서 드래그 앤 드롭의 메커니즘은 매우 간단합니다. 사용자가 Draggable 컨트롤을 드래그하기 시작하고 DragTarget에 '드롭'합니다. 드래그 가능한 대상과 드래그 대상이 모두 동일한 그룹인 경우 드래그 대상은 on_accept 이벤트 핸들러를 호출하고 드래그 가능한 제어 ID를 이벤트 데이터로 전달합니다. 이 경우 draggable은 끌어서 놓기 작업을 위한 소스 '데이터' 역할을 합니다.

다음 예를 살펴보겠습니다. 아래 프로그램에서 '1'을 표시하는 왼쪽 컨트롤을 '0'을 표시하는 오른쪽 컨트롤 위에 드래그할 수 있으며 드래그 작업이 완료되면 왼쪽 컨트롤이 '0'으로 바뀌고 오른쪽 컨트롤이 '1'이 됩니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Drag and Drop example"

    def drag_accept(e):
        # get draggable (source) control by its ID
        src = page.get_control(e.src_id)
        # update text inside draggable control
        src.content.content.value = "0"
        # update text inside drag target control
        e.control.content.content.value = "1"
        page.update()

    page.add(
        ft.Row(
            [
                ft.Draggable(
                    group="number",
                    content=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.CYAN_200,
                        border_radius=5,
                        content=ft.Text("1", size=20),
                        alignment=ft.alignment.center,
                    ),
                ),
                ft.Container(width=100),
                ft.DragTarget(
                    group="number",
                    content=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.PINK_200,
                        border_radius=5,
                        content=ft.Text("0", size=20),
                        alignment=ft.alignment.center,
                    ),
                    on_accept=drag_accept,
                ),
            ]
        )
    )

ft.app(target=main)
```

![img](D:\GitHub\computer_note\Language\Python\Images\drag-and-drop-number.gif)

따라서 on_accept 이벤트가 발생할 때 '소스'(드래그 가능) 및 '대상'(드래그 대상) 컨트롤에 어떤 일이 발생하는지 결정하는 것은 개발자의 책임입니다.

> **TRY SOMETHING**
>
> DragTarget의 그룹 속성을 number1로 변경하고 대상에 '1'을 놓을 때 on_accept가 더 이상 호출되지 않는다는 점에 유의하십시오.

끌어서 놓기 작업을 더욱 대화식으로 만들기 위한 추가 속성 및 이벤트 처리기가 있습니다. 예를 들어 draggable에는 드래그 작업이 진행 중일 때 콘텐츠 대신 다른 컨트롤을 표시하는 content_when_dragging 속성이 있습니다. 포인터 아래에 다른 컨트롤을 표시하는 content_feedback 속성도 있습니다. 기본적으로 동일한 콘텐츠 컨트롤이지만 드래그할 때 커서 아래에 불투명도가 50%로 표시됩니다.

예제에서 Draggable을 수정하여 드래그된 컨트롤 대신 '구멍'을 표시하고 드래그하는 동안 커서 아래에 '1'만 표시해 보겠습니다.

``` py
...
                ft.Draggable(
                    group="number",
                    content=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.CYAN_200,
                        border_radius=5,
                        content=ft.Text("1", size=20),
                        alignment=ft.alignment.center,
                    ),
                    content_when_dragging=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.BLUE_GREY_200,
                        border_radius=5,
                    ),
                    content_feedback=ft.Text("1"),
                ),
...
```

![img](D:\GitHub\computer_note\Language\Python\Images\drag-and-drop-number-2.gif)

끌기 대상 제어에는 on_will_accept 및 on_leave 이벤트 핸들러가 추가로 있어 대상에 무언가를 '드롭'할 적절한 시점을 시각화하는 데 도움이 됩니다. 들어오는 끌기를 허용할 준비가 되면 대상 컨트롤 주위에 테두리를 그리도록 예제에서 DragTarget을 수정해 보겠습니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Drag and Drop example 2"

    def drag_accept(e):
        # get draggable (source) control by its ID
        src = page.get_control(e.src_id)
        # update text inside draggable control
        src.content.content.value = "0"
        # reset source group, so it cannot be dropped to a target anymore
        src.group = ""
        # update text inside drag target control
        e.control.content.content.value = "1"
        # reset border
        e.control.content.border = None
        page.update()

    def drag_will_accept(e):
        # black border when it's allowed to drop and red when it's not
        e.control.content.border = ft.border.all(
            2, ft.colors.BLACK45 if e.data == "true" else ft.colors.RED
        )
        e.control.update()

    def drag_leave(e):
        e.control.content.border = None
        e.control.update()

    page.add(
        ft.Row(
            [
                ft.Draggable(
                    group="number",
                    content=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.CYAN_200,
                        border_radius=5,
                        content=ft.Text("1", size=20),
                        alignment=ft.alignment.center,
                    ),
                    content_when_dragging=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.BLUE_GREY_200,
                        border_radius=5,
                    ),
                    content_feedback=ft.Text("1"),
                ),
                ft.Container(width=100),
                ft.DragTarget(
                    group="number",
                    content=ft.Container(
                        width=50,
                        height=50,
                        bgcolor=ft.colors.PINK_200,
                        border_radius=5,
                        content=ft.Text("0", size=20),
                        alignment=ft.alignment.center,
                    ),
                    on_accept=drag_accept,
                    on_will_accept=drag_will_accept,
                    on_leave=drag_leave,
                ),
            ]
        )
    )

ft.app(target=main)
```

![img](D:\GitHub\computer_note\Language\Python\Images\drag-and-drop-number-3.gif)



---

### Navigation and routing

탐색 및 라우팅은 응용 프로그램 사용자 인터페이스를 가상 페이지(보기)로 구성하고 응용 프로그램 URL이 앱의 현재 상태를 반영하는 동안 '탐색'할 수 있는 단일 페이지 응용 프로그램(SPA)의 필수 기능입니다.

모바일 앱의 경우 탐색 및 라우팅은 특정 애플리케이션 부분에 대한 딥 링크 역할을 합니다.

구현이 Navigator 2.0 Flutter API를 기반으로 하고 Flet의 '페이지' 추상화를 '페이지 및 보기'로 대체해야 하므로 탐색 및 라우팅을 Flet에 추가하는 데 예상보다 많은 노력이 필요했습니다. Flutter의 최신 탐색 및 라우팅 API는 다음과 같이 크게 개선되었습니다.

- 기록 스택에 대한 프로그래밍 방식 제어.
- AppBar에서 '뒤로' 버튼에 대한 호출을 가로채는 쉬운 방법입니다.
- 브라우저 기록과 강력한 동기화.

![img](D:\GitHub\computer_note\Language\Python\Images\routing-app-example.gif)

위 예제의 [소스 코드](https://github.com/flet-dev/examples/blob/main/python/apps/routing-navigation/building-views-on-route-change.py)를 살펴보십시오.

``` py
import flet
from flet import AppBar, ElevatedButton, Page, Text, View, colors


def main(page: Page):
    page.title = "Routes Example"

    print("Initial route:", page.route)

    def route_change(e):
        print("Route change:", e.route)
        page.views.clear()
        page.views.append(
            View(
                "/",
                [
                    AppBar(title=Text("Flet app")),
                    ElevatedButton("Go to settings", on_click=open_settings),
                ],
            )
        )
        if page.route == "/settings" or page.route == "/settings/mail":
            page.views.append(
                View(
                    "/settings",
                    [
                        AppBar(title=Text("Settings"), bgcolor=colors.SURFACE_VARIANT),
                        Text("Settings!", style="bodyMedium"),
                        ElevatedButton(
                            "Go to mail settings", on_click=open_mail_settings
                        ),
                    ],
                )
            )
        if page.route == "/settings/mail":
            page.views.append(
                View(
                    "/settings/mail",
                    [
                        AppBar(
                            title=Text("Mail Settings"), bgcolor=colors.SURFACE_VARIANT
                        ),
                        Text("Mail settings!"),
                    ],
                )
            )
        page.update()

    def view_pop(e):
        print("View pop:", e.view)
        page.views.pop()
        top_view = page.views[-1]
        page.go(top_view.route)

    page.on_route_change = route_change
    page.on_view_pop = view_pop

    def open_mail_settings(e):
        page.go("/settings/mail")

    def open_settings(e):
        page.go("/settings")

    page.go(page.route)


flet.app(target=main, view=flet.WEB_BROWSER)
```



#### Page route

페이지 경로는 # 기호 다음의 애플리케이션 URL 부분입니다.

![img](D:\GitHub\computer_note\Language\Python\Images\page-address-route.png)

사용자가 애플리케이션 URL에 설정하지 않은 경우 기본 애플리케이션 경로는 /입니다. 모든 경로는 /로 시작합니다(예: /store, /authors/1/books/2).

응용 프로그램 경로는 page.route 속성을 읽어 얻을 수 있습니다. 예를 들면 다음과 같습니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.add(ft.Text(f"Initial route: {page.route}"))

ft.app(target=main, view=ft.WEB_BROWSER)
```

응용 프로그램 URL을 잡고 새 브라우저 탭을 열고 URL을 붙여넣고 # 뒤의 부분을 /test로 수정하고 Enter 키를 누릅니다. '초기 경로: /test'가 표시되어야 합니다.

URL의 경로가 변경될 때마다(URL을 편집하거나 뒤로/앞으로 버튼으로 브라우저 기록을 탐색하여) Flet은 page.on_route_change 이벤트 핸들러를 호출합니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.add(ft.Text(f"Initial route: {page.route}"))

    def route_change(route):
        page.add(ft.Text(f"New route: {route}"))

    page.on_route_change = route_change
    page.update()

ft.app(target=main, view=ft.WEB_BROWSER)
```

이제 URL 해시를 몇 번 업데이트한 다음 뒤로/앞으로 버튼을 사용해 보세요! 경로가 변경될 때마다 페이지에 추가된 새 메시지가 표시되어야 합니다.

![img](D:\GitHub\computer_note\Language\Python\Images\page-route-change-event.gif)



page.route 속성을 업데이트하여 프로그래밍 방식으로 경로를 변경할 수 있습니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.add(ft.Text(f"Initial route: {page.route}"))

    def route_change(route):
        page.add(ft.Text(f"New route: {route}"))

    def go_store(e):
        page.route = "/store"
        page.update()

    page.on_route_change = route_change
    page.add(ft.ElevatedButton("Go to Store", on_click=go_store))

ft.app(target=main, view=ft.WEB_BROWSER)
```

'스토어로 이동' 버튼을 클릭하면 애플리케이션 URL이 변경되고 새 항목이 브라우저 기록에 푸시되는 것을 볼 수 있습니다. 브라우저의 '뒤로' 버튼을 사용하여 이전 경로로 이동할 수 있습니다.



#### Page views

Flet의 페이지는 이제 단순한 단일 페이지가 아니라 샌드위치처럼 서로 겹쳐진 View의 컨테이너입니다.

![img](D:\GitHub\computer_note\Language\Python\Images\page-views.svg)

뷰 모음은 네비게이터 기록을 나타냅니다. 페이지에는 뷰 컬렉션에 액세스하기 위한 page.views 속성이 있습니다.

목록의 마지막 보기는 현재 페이지에 표시된 보기입니다. 보기 목록에는 하나 이상의 요소(루트 보기)가 있어야 합니다.

페이지 간 전환을 시뮬레이트하려면 page.route를 변경하고 page.view 목록 끝에 새 보기를 추가합니다.

컬렉션에서 마지막 뷰를 팝하고 page.on_view_pop 이벤트 핸들러에서 경로를 '이전' 경로로 변경하여 되돌아갑니다.



#### 경로 변경에 대한 뷰 구축

신뢰할 수 있는 내비게이션을 구축하려면 프로그램에 현재 경로에 따라 보기 목록을 구축하는 단일 위치가 있어야 합니다. 즉, 탐색 기록 스택(보기 목록으로 표시됨)은 경로의 기능이어야 합니다.

이곳은 page.on_route_change 이벤트 핸들러입니다.

두 페이지 사이를 탐색할 수 있는 완전한 예제에 모든 것을 함께 넣어 보겠습니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Routes Example"

    def route_change(route):
        page.views.clear()
        page.views.append(
            ft.View(
                "/",
                [
                    ft.AppBar(title=ft.Text("Flet app"), bgcolor=ft.colors.SURFACE_VARIANT),
                    ft.ElevatedButton("Visit Store", on_click=lambda _: page.go("/store")),
                ],
            )
        )
        if page.route == "/store":
            page.views.append(
                ft.View(
                    "/store",
                    [
                        ft.AppBar(title=ft.Text("Store"), bgcolor=ft.colors.SURFACE_VARIANT),
                        ft.ElevatedButton("Go Home", on_click=lambda _: page.go("/")),
                    ],
                )
            )
        page.update()

    def view_pop(view):
        page.views.pop()
        top_view = page.views[-1]
        page.go(top_view.route)

    page.on_route_change = route_change
    page.on_view_pop = view_pop
    page.go(page.route)


ft.app(target=main, view=ft.WEB_BROWSER)
```

'스토어 방문' 및 '홈으로 이동' 버튼, 뒤로/앞으로 브라우저 버튼, URL에서 수동으로 경로 변경을 사용하여 페이지 사이를 탐색해 보세요. 어떤 경우에도 작동합니다! :)

> **NOTE**
>
> 페이지 사이를 '탐색'하기 위해 page.go(route) - page.route를 업데이트하고 page.on_route_change 이벤트 핸들러를 호출하여 보기를 업데이트하고 마지막으로 page.update()를 호출하는 도우미 메서드를 사용했습니다.



#### Route templates

Flet은 TemplateRoute를 제공합니다. 재경로 라이브러리를 기반으로 하는 유틸리티 클래스로 ExpressJS와 유사한 경로를 일치시키고 해당 매개변수를 구문 분석할 수 있습니다(예: /account/:account_id/orders/:order_id).

TemplateRoute는 경로 변경 이벤트와 잘 어울립니다.

``` py
troute = TemplateRoute(page.route)

if troute.match("/books/:id"):
    print("Book view ID:", troute.id)
elif troute.match("/account/:account_id/orders/:order_id"):
    print("Account:", troute.account_id, "Order:", troute.order_id)
else:
    print("Unknown route")
```



#### 웹용 URL 전략

Flet 웹 앱은 URL 기반 라우팅을 구성하는 두 가지 방법을 지원합니다.

- **Hash** (default) - 경로를 읽고 해시 조각에 씁니다. 예: fletapp.dev/#/path/to/view.
- **Path** - 경로는 해시 없이 읽고 씁니다. 예: fletapp.dev/path/to/view.

URL 전략을 변경하려면 flet.app() 메서드의 route_url_strategy 매개 변수를 사용하십시오. 예를 들면 다음과 같습니다.

``` py
ft.app(target=main, route_url_strategy="path")
```

Flet 서버에 대한 URL 전략은 경로 또는 해시(기본값)로 설정할 수 있는 FLET_ROUTE_URL_STRATEGY 환경 변수로 구성할 수 있습니다.



---

### File picker and uploads

파일 선택기 컨트롤은 파일 및 디렉터리를 선택하기 위한 기본 OS 대화 상자를 엽니다.

웹, macOS, Window, Linux, iOS 및 Android와 같은 모든 플랫폼에서 작동합니다.

![img](D:\GitHub\computer_note\Language\Python\Images\file-picker-all-modes-demo.png)

위 [데모의 소스 코드](https://github.com/flet-dev/examples/blob/main/python/controls/file-picker/file-picker-all-modes.py)를 확인하십시오.

``` py
import flet
from flet import (
    ElevatedButton,
    FilePicker,
    FilePickerResultEvent,
    Page,
    Row,
    Text,
    icons,
)


def main(page: Page):
    # Pick files dialog
    def pick_files_result(e: FilePickerResultEvent):
        selected_files.value = (
            ", ".join(map(lambda f: f.name, e.files)) if e.files else "Cancelled!"
        )
        selected_files.update()

    pick_files_dialog = FilePicker(on_result=pick_files_result)
    selected_files = Text()

    # Save file dialog
    def save_file_result(e: FilePickerResultEvent):
        save_file_path.value = e.path if e.path else "Cancelled!"
        save_file_path.update()

    save_file_dialog = FilePicker(on_result=save_file_result)
    save_file_path = Text()

    # Open directory dialog
    def get_directory_result(e: FilePickerResultEvent):
        directory_path.value = e.path if e.path else "Cancelled!"
        directory_path.update()

    get_directory_dialog = FilePicker(on_result=get_directory_result)
    directory_path = Text()

    # hide all dialogs in overlay
    page.overlay.extend([pick_files_dialog, save_file_dialog, get_directory_dialog])

    page.add(
        Row(
            [
                ElevatedButton(
                    "Pick files",
                    icon=icons.UPLOAD_FILE,
                    on_click=lambda _: pick_files_dialog.pick_files(
                        allow_multiple=True
                    ),
                ),
                selected_files,
            ]
        ),
        Row(
            [
                ElevatedButton(
                    "Save file",
                    icon=icons.SAVE,
                    on_click=lambda _: save_file_dialog.save_file(),
                    disabled=page.web,
                ),
                save_file_path,
            ]
        ),
        Row(
            [
                ElevatedButton(
                    "Open directory",
                    icon=icons.FOLDER_OPEN,
                    on_click=lambda _: get_directory_dialog.get_directory_path(),
                    disabled=page.web,
                ),
                directory_path,
            ]
        ),
    )


flet.app(target=main)
```

파일 선택기를 사용하면 세 가지 대화 상자를 열 수 있습니다.

- **Pick files** - 하나 또는 여러 개, 임의의 파일 또는 특정 유형만.
- **Save file** - 디렉토리 및 파일 이름을 선택하십시오.
- **Get directory** -  디렉토리를 선택하십시오.

브라우저에서 Flet 앱을 실행할 때 '파일 선택' 옵션만 사용할 수 있으며 선택한 파일의 전체 경로를 반환하지 않기 때문에 업로드에만 사용됩니다.

파일 선택기가 정말 빛나는 곳은 데스크탑입니다! 세 개의 대화 상자 모두 선택한 파일 및 디렉토리의 전체 경로를 반환하므로 사용자에게 큰 도움이 됩니다!



#### 앱에서 파일 선택기 사용

앱의 레이아웃에 영향을 주지 않도록 page.overlay.controls 컬렉션에 파일 선택기를 추가하는 것이 좋습니다. 파일 선택기의 크기가 0x0임에도 불구하고 행 또는 열에 넣을 때 여전히 컨트롤로 간주됩니다.

``` py
import flet as ft

file_picker = ft.FilePicker()
page.overlay.append(file_picker)
page.update()
```

파일 선택기 대화 상자를 열려면 다음 세 가지 방법 중 하나를 호출하십시오.

- `pick_files()`
- `save_file()`
- `get_directory_path()`

Lambda는 다음과 같이 매우 잘 작동합니다.

``` py
ft.ElevatedButton("Choose files...",
    on_click=lambda _: file_picker.pick_files(allow_multiple=True))
```

대화 상자가 닫히면 FilePicker.on_result 이벤트 핸들러가 호출되며 이벤트 객체에는 다음 속성 중 하나가 설정됩니다.

- `files` - "Pick files" 대화 상자만, 선택한 파일 목록 또는 대화 상자가 취소된 경우 없음.
- `path` - "Save file" and "Get directory" dialogs, 파일 또는 디렉토리의 전체 경로 또는 대화 상자가 취소된 경우 없음.

``` py
import flet as ft

def on_dialog_result(e: ft.FilePickerResultEvent):
    print("Selected files:", e.files)
    print("Selected file or directory:", e.path)

file_picker = ft.FilePicker(on_result=on_dialog_result)
```

마지막 결과는 항상 FilePicker.result 속성에서 사용할 수 있습니다.

사용 가능한 모든 대화 방법 및 해당 매개변수에 대해서는 [파일 선택기 제어](https://flet.dev/docs/controls/filepicker) 문서를 확인하십시오.



#### 파일 업로드

파일 선택기에는 모든 플랫폼과 웹에서 작동하는 내장 업로드 기능이 있습니다.

하나 이상의 파일을 업로드하려면 먼저 FilePicker.pick_files()를 호출해야 합니다. 사용자가 파일을 선택하면 어디에도 자동으로 업로드되지 않고 대신 해당 참조가 파일 선택기 상태로 유지됩니다.

실제 업로드를 수행하려면 FilePicker.upload() 메서드를 호출하고 업로드 URL 및 업로드 메서드(PUT 또는 POST)와 함께 업로드해야 하는 파일 목록을 전달해야 합니다.

``` py
import flet as ft

def upload_files(e):
    upload_list = []
    if file_picker.result != None and file_picker.result.files != None:
        for f in file_picker.result.files:
            upload_list.append(
                FilePickerUploadFile(
                    f.name,
                    upload_url=page.get_upload_url(f.name, 600),
                )
            )
        file_picker.upload(upload_list)

ft.ElevatedButton("Upload", on_click=upload_files)
```

> **NOTE**
>
> 각 사용자에 대해 별도의 업로드가 필요한 경우 page.get_upload_url() 호출에서 원하는 수의 디렉토리가 앞에 추가된 파일 이름을 지정할 수 있습니다. 예를 들면 다음과 같습니다.
>
> ``` py
> upload_url = page.get_upload_url(f"/{username}/pictures/{f.name}", 600)
> ```
>
> /{username}/pictures 디렉토리는 존재하지 않는 경우 upload_dir 내부에 자동으로 생성됩니다.



#### 스토리지 업로드

page.get_upload_url() 메서드의 사용법에 주목하세요. Flet의 내부 업로드 저장소에 대해 미리 서명된 업로드 URL을 생성합니다.

> 파일 업로드를 위해 모든 스토리지 사용
>
> boto3 라이브러리를 사용하여 AWS S3 스토리지에 대한 미리 서명된 업로드 URL을 생성할 수 있습니다.
>
> 동일한 기술이 Wasabi, Backblaze, MinIO 및 S3 호환 API를 사용하는 다른 스토리지 공급자에 대해 작동해야 합니다.

업로드된 파일을 디렉토리에 저장하는 Flet을 활성화하려면 flet.app() 호출에서 해당 디렉토리에 대한 전체 또는 상대 경로를 제공하십시오.

``` py 
ft.app(target=main, upload_dir="uploads")
```

업로드를 'assets' 디렉토리에 넣을 수도 있습니다. 사진, 문서 또는 기타 미디어는 Flet 클라이언트에서 바로 액세스할 수 있습니다.

``` py
ft.app(target=main, assets_dir="assets", upload_dir="assets/uploads")
```

그리고 앱의 어딘가에 다음을 사용하여 업로드된 사진을 표시할 수 있습니다.

``` py
page.add(ft.Image(src="/uploads/<some-uploaded-picture.png>"))
```



#### 업로드 진행률

FilePicker.upload() 메서드가 호출되면 Flet 클라이언트는 선택한 파일을 하나씩 업로드하기 시작하고 FilePicker.on_upload 콜백을 통해 진행률을 보고합니다.

on_upload 이벤트의 이벤트 개체는 다음 필드가 있는 FilePickerUploadEvent 클래스의 인스턴스입니다.

- `file_name`
- `progress` - a value from `0.0` to `1.0`.
- `error`

콜백은 업로드된 모든 파일에 대해 적어도 두 번 호출됩니다. 업로드가 시작되기 전에는 진행률이 0이고 업로드가 완료되면 진행률이 1.0입니다. 1MB보다 큰 파일의 경우 업로드된 10%마다 진행률이 추가로 보고됩니다.

여러 파일 업로드를 보여주는 [예제](https://github.com/flet-dev/examples/blob/main/python/controls/file-picker/file-picker-upload-progress.py)를 확인하십시오.

![img](D:\GitHub\computer_note\Language\Python\Images\file-picker-multiple-uploads.png)

모든 속성과 예제는 [파일 선택기](https://flet.dev/docs/controls/filepicker) 컨트롤 문서를 참조하세요.



---

### Animations

출처: https://flet.dev/docs/guides/python/animations



#### 암시적 애니메이션 (Implicit animations)

암시적 애니메이션을 사용하면 대상 값을 설정하여 컨트롤 속성에 애니메이션을 적용할 수 있습니다. 해당 대상 값이 변경될 때마다 컨트롤은 이전 값에서 새 값으로 속성에 애니메이션을 적용합니다. 애니메이션은 지정된 기간 동안 이전 값과 새 값 사이에 보간된 값을 생성합니다. 기본적으로 애니메이션은 애니메이션 값을 선형적으로 증가시키지만 제공된 곡선에 따라 값을 변경하는 곡선을 애니메이션에 적용할 수 있습니다. 예를 들어 easeOutCubic 곡선은 애니메이션 시작 시 애니메이션 값을 빠르게 증가시킨 다음 대상 값에 도달할 때까지 속도를 늦춥니다.

각 컨트롤은 모양의 암시적 애니메이션을 활성화하기 위해 아래에 설명된 여러 animate_{something} 속성을 제공합니다.

- `animate_opacity`
- `animate_rotation`
- `animate_scale`
- `animate_offset`
- `animate_position`
- `animate` (Container)

animate_* 속성은 다음 값 중 하나를 가질 수 있습니다.



- Instance of `animation.Animation` class - al예를 들어 animate_rotation=ft.animation.Animation(duration=300, curve='bounceOut')과 같이 지속 시간(밀리초) 및 애니메이션 곡선을 구성할 수 있습니다. 가능한 값은 Flutter 문서의 곡선을 참조하세요. 기본값은 선형입니다.
- `int` value - 지정된 기간(밀리초)과 선형 곡선으로 애니메이션을 활성화합니다.
- `bool` value -1000밀리초의 지속 시간과 선형 곡선으로 anumation을 활성화합니다.



#### 불투명도 애니메이션 (Opacity animation)

컨트롤의 animate_opacity를 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 Control.opacity 속성의 암시적 애니메이션이 활성화됩니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-opacity.gif)

``` py
import flet as ft

def main(page: ft.Page):

    c = ft.Container(
        width=150,
        height=150,
        bgcolor="blue",
        border_radius=10,
        animate_opacity=300,
    )

    def animate_opacity(e):
        c.opacity = 0 if c.opacity == 1 else 1
        c.update()

    page.add(
        c,
        ft.ElevatedButton(
            "Animate opacity",
            on_click=animate_opacity,
        ),
    )

ft.app(target=main)
```



#### 회전 애니메이션

컨트롤의 animate_rotation을 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 Control.rotate 속성의 암시적 애니메이션이 활성화됩니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-rotation.gif)

``` py
from math import pi
import flet as ft

def main(page: ft.Page):

    c = ft.Container(
        width=100,
        height=70,
        bgcolor="blue",
        border_radius=5,
        rotate=ft.transform.Rotate(0, alignment=ft.alignment.center),
        animate_rotation=ft.animation.Animation(300, ft.AnimationCurve.BOUNCE_OUT),
    )

    def animate(e):
        c.rotate.angle += pi / 2
        page.update()

    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.spacing = 30
    page.add(
        c,
        ft.ElevatedButton("Animate!", on_click=animate),
    )

ft.app(target=main)
```



#### Scale animation

컨트롤의 animate_scale을 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 Control.scale 속성의 암시적 애니메이션이 활성화됩니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-scale.gif)

``` py
import flet as ft

def main(page: ft.Page):

    c = ft.Container(
        width=100,
        height=100,
        bgcolor="blue",
        border_radius=5,
        scale=ft.transform.Scale(scale=1),
        animate_scale=ft.animation.Animation(600, ft.AnimationCurve.BOUNCE_OUT),
    )

    def animate(e):
        c.scale = 2
        page.update()

    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.spacing = 30
    page.add(
        c,
        ft.ElevatedButton("Animate!", on_click=animate),
    )

ft.app(target=main)
```



#### Offset animation

컨트롤의 animate_offset을 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 Control.offset 속성의 암시적 애니메이션이 활성화됩니다.

offset 속성은 컨트롤 크기에 맞게 조정된 컨트롤의 가로 x 및 세로 y 오프셋을 지정하는 transform.Offset 클래스의 인스턴스입니다. 예를 들어 offset transform.Offset(-0.25, 0)은 컨트롤 너비의 1/4에 해당하는 수평 이동을 초래합니다.

오프셋 애니메이션은 다양한 슬라이딩 효과에 사용됩니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-offset.gif)

``` py
import flet as ft

def main(page: ft.Page):

    c = ft.Container(
        width=150,
        height=150,
        bgcolor="blue",
        border_radius=10,
        offset=ft.transform.Offset(-2, 0),
        animate_offset=ft.animation.Animation(1000),
    )

    def animate(e):
        c.offset = ft.transform.Offset(0, 0)
        c.update()

    page.add(
        c,
        ft.ElevatedButton("Reveal!", on_click=animate),
    )

ft.app(target=main)
```



#### Position animation

컨트롤의 animate_position을 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 컨트롤의 왼쪽, 위쪽, 오른쪽 및 아래쪽 속성의 암시적 애니메이션이 활성화됩니다.

컨트롤 위치는 스택 컨트롤 내에서만 작동합니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-position.gif)

``` py
import flet as ft

def main(page: ft.Page):

    c1 = ft.Container(width=50, height=50, bgcolor="red", animate_position=1000)

    c2 = ft.Container(
        width=50, height=50, bgcolor="green", top=60, left=0, animate_position=500
    )

    c3 = ft.Container(
        width=50, height=50, bgcolor="blue", top=120, left=0, animate_position=1000
    )

    def animate_container(e):
        c1.top = 20
        c1.left = 200
        c2.top = 100
        c2.left = 40
        c3.top = 180
        c3.left = 100
        page.update()

    page.add(
        ft.Stack([c1, c2, c3], height=250),
        ft.ElevatedButton("Animate!", on_click=animate_container),
    )

ft.app(target=main)
```



#### Animated container

Container.animate를 True, 숫자 또는 animation.Animation 클래스의 인스턴스(위 참조)로 설정하면 크기, 배경색, 테두리 스타일, 그라데이션과 같은 컨테이너 속성의 암시적 애니메이션이 활성화됩니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animate-container.gif)

``` py
import flet as ft

def main(page: ft.Page):

    c = ft.Container(
        width=150,
        height=150,
        bgcolor="red",
        animate=ft.animation.Animation(1000, ft.AnimationCurve.BOUNCE_OUT),
    )

    def animate_container(e):
        c.width = 100 if c.width == 150 else 150
        c.height = 50 if c.height == 150 else 150
        c.bgcolor = "blue" if c.bgcolor == "red" else "red"
        c.update()

    page.add(c, ft.ElevatedButton("Animate container", on_click=animate_container))

ft.app(target=main)
```



#### Animated content switcher

AnimatedSwitcher는 새 컨트롤과 이전에 AnimatedSwitcher에 콘텐츠로 설정된 컨트롤 간의 애니메이션 전환을 허용합니다.

![img](D:\GitHub\computer_note\Language\Python\Images\animated-switcher-images.gif)

``` py
import time

import flet as ft

def main(page: ft.Page):

    i = ft.Image(src="https://picsum.photos/150/150", width=150, height=150)

    def animate(e):
        sw.content = ft.Image(
            src=f"https://picsum.photos/150/150?{time.time()}", width=150, height=150
        )
        page.update()

    sw = ft.AnimatedSwitcher(
        i,
        transition=ft.AnimatedSwitcherTransition.SCALE,
        duration=500,
        reverse_duration=500,
        switch_in_curve=ft.AnimationCurve.BOUNCE_OUT,
        switch_out_curve=ft.AnimationCurve.BOUNCE_IN,
    )

    page.add(
        sw,
        ft.ElevatedButton("Animate!", on_click=animate),
    )

ft.app(target=main)
```



#### Animation end callback

animate_* 속성이 있는 모든 컨트롤에는 애니메이션이 완료될 때 호출되는 on_animation_end 이벤트 핸들러가 있으며 cab은 여러 애니메이션을 연결하는 데 사용됩니다.

이벤트의 개체 데이터 필드에는 애니메이션 이름이 포함됩니다.



- `opacity`
- `rotation`
- `scale`
- `offset`
- `position`
- `container`



예를 들어:

``` py
 c = ft.Container(
        ft.Text("Animate me!"),
        # ...
        animate=ft.animation.Animation(1000, "bounceOut"),
        on_animation_end=lambda e: print("Container animation end:", e.data)
    )
```





---

### 인증

출처: https://flet.dev/docs/guides/python/authentication

GitHub, Google, Azure, Auth0, LinkedIn 등과 같은 타사 ID 공급자를 사용하여 Flet 앱에서 사용자 인증('X로 로그인' 버튼)을 구현할 수 있습니다.

ID 공급자는 API 액세스 토큰을 검색하기 위해 OAuth 2.0 인증 코드 흐름을 지원해야 합니다.

자격 증명 및 사용자 관리를 통한 내장형 Flet 로그인은 향후 릴리스에서 계획되어 있습니다. 앱에서 사용자 계정을 만들고 관리해야 하는 요구 사항이 있는 경우 직접 구현하거나 넉넉한 무료 등급을 제공하는 Auth0 ID 공급자를 사용할 수 있습니다.

Flet authentication features:

- Flet 데스크톱, 웹 및 모바일 앱에서 작동합니다.
- 하나의 앱에서 여러 인증 제공자를 사용합니다.
- 자동 사용자 세부 정보를 가져오는 기본 제공 OAuth 공급자:
  - GitHub
  - Azure
  - Google
  - Auth0
- 선택적 그룹 가져오기.
- 자동 토큰 갱신.
- 저장된 토큰()"Remember me")으로 로그인합니다.
- 사용자 지정 OAuth 공급자.



#### Login process overview

- 클라이언트 ID, 클라이언트 암호, 리디렉션 URL로 OAuth 공급자(내장 또는 일반)를 구성합니다.
- page.login(provider)을 호출하여 OAuth 웹 흐름을 시작합니다.
- 사용자가 OAuth 공급자 웹사이트로 리디렉션됩니다.
- 공급자 웹 사이트에서 사용자는 로그인하고 요청된 범위로 서비스 API에 액세스하는 데 동의합니다.
- 제공자 웹사이트는 인증 코드와 함께 Flet의 OAuth 콜백 URL로 리디렉션됩니다.
- Flet은 인증 코드를 토큰으로 교환하고 page.on_login 이벤트 핸들러를 호출합니다.
- Flet 앱은 page.auth.token 속성에서 API 토큰을 검색하고 page.auth.user에서 사용자 세부 정보를 검색할 수 있습니다.



#### OAuth 공급자 구성

Flet에는 다음과 같은 기본 제공 OAuth 공급자가 있습니다.

- GitHub
- Azure
- Google
- Auth0

또한 일반 OAuth 공급자를 구성하고 인증, 토큰 및 사용자 정보 엔드포인트를 제공할 수 있습니다.

이 가이드에서는 GitHub 계정으로 Flet 로그인 페이지를 구성합니다.

Flet 인증을 GitHub와 통합하려면 먼저 새 GitHub OAuth 앱을 등록해야 합니다(프로필 설정 → 개발자 설정 → OAuth 앱).

승인 콜백 URL은 다음 형식이어야 합니다.

``` py
{application-url}/api/oauth/redirect
```

![img](D:\GitHub\computer_note\Language\Python\Images\github-new-oauth-app.png)

OAuth 앱 세부 정보 페이지에서 '새 클라이언트 암호 생성' 버튼을 클릭합니다. '클라이언트 ID' 및 '클라이언트 암호' 값을 안전한 위치에 복사합니다. Flet 앱에 필요합니다.

![img](D:\GitHub\computer_note\Language\Python\Images\github-oauth-app-details.png)



#### OAuth 공급자로 로그인

``` py
import os

import flet as ft
from flet.auth.providers.github_oauth_provider import GitHubOAuthProvider

def main(page: ft.Page):

    provider = GitHubOAuthProvider(
        client_id=os.getenv("GITHUB_CLIENT_ID"),
        client_secret=os.getenv("GITHUB_CLIENT_SECRET"),
        redirect_url="http://localhost:8550/api/oauth/redirect",
    )

    def login_click(e):
        page.login(provider)

    def on_login(e):
        print("Access token:", page.auth.token.access_token)
        print("User ID:", page.auth.user.id)

    page.on_login = on_login
    page.add(ft.ElevatedButton("Login with GitHub", on_click=login_click))

ft.app(target=main, port=8550, view=ft.WEB_BROWSER)
```

> **CAUTION**
>
> 환경 변수에서 OAuth 앱 클라이언트 ID와 클라이언트 암호를 가져오고 있습니다. 실수로 대중에게 노출되지 않도록 소스 코드에 비밀을 포함하지 마십시오!

앱을 실행하기 전에 명령줄에서 비밀 환경 변수를 설정합니다.

``` bash
$ export GITHUB_CLIENT_ID="<client_id>"
$ export GITHUB_CLIENT_SECRET="<client_secret>"
```

프로그램을 실행하고 'GitHub로 로그인' 버튼을 클릭합니다. GitHub 승인 앱 페이지가 다음 위치에서 열립니다.

- **Desktop** app - 새 브라우저 창 또는 탭.
- **Web** app - 새 팝업 창(팝업 차단기가 꺼져 있는지 확인).
- **Mobile** app - 인앱 웹 브라우저.

![img](D:\GitHub\computer_note\Language\Python\Images\github-oauth-authorize.png)



#### Redirect URL

GitHub OAuth 앱을 등록할 때 리디렉션 URL로 http://localhost:8550/api/oauth/redirect를 사용했습니다. 고정 포트는 8550입니다. 고정 포트에서 Flet 앱을 실행하려면 flet.app 호출에서 포트 인수를 사용하세요.

``` py
ft.app(target=main, port=8550)
```



#### Scope

대부분의 OAuth 공급자는 애플리케이션이 사용자 계정에 대한 애플리케이션의 액세스를 제한하기 위해 하나 이상의 범위를 요청할 수 있도록 허용합니다.

기본 제공 Flet 공급자는 기본적으로 사용자 프로필에 액세스하기 위한 범위를 요청하지만 위의 예에서 public_repo와 같이 로그인 방법에서 추가 범위를 요청할 수 있습니다.

``` py
page.login(
    provider,
    scope=["public_repo"])
```

page.login() 메서드에는 인증 프로세스를 제어하는 여러 인수가 있습니다.

- `fetch_user` (bool) - 사용자 세부 정보를 page.auth.user로 가져올지 여부. 기본값은 참입니다.
- `fetch_groups` (bool) - 사용자 그룹을 page.auth.user.groups로 가져올지 여부. 기본값은 거짓입니다.
- `scope` - 요청할 범위 목록입니다.
- `saved_token` - 권한 부여를 복원할 page.auth.token의 JSON 스냅샷. 토큰은 page.auth.token.to_json()으로 직렬화되고 암호화되어 page.client_storage에 저장될 수 있습니다. 아래를 참조하십시오.
- `on_open_authorization_url` - 인증 URL로 브라우저를 여는 콜백. 아래를 참조하십시오.
- `complete_page_html` - '성공적으로 인증되었습니다. 지금 이 페이지를 닫으십시오' 페이지의 사용자 지정 HTML 콘텐츠.
- `redirect_to_page` (bool) - 인증 페이지가 동일한 브라우저 탭에서 열리는 경우에만 Flet 웹 앱과 함께 사용됩니다.

page.login() 호출의 결과는 다음 필드가 있는 Authorization 클래스의 인스턴스입니다.

- **`token`** - OAuth token used to access provider's API. See below.
- **`user`** - user details with a mandatory `id` field and other fields specific to OAuth provider.
- **`provider`** - an instance of OAuth provider used for authorization.

마지막 인증에 대한 참조는 page.auth 속성에 저장됩니다.

앱에서 여러 OAuth 공급자의 인증을 허용하는 경우 세션에 인증을 저장할 수 있습니다. 예를 들면 다음과 같습니다.

``` py
page.session["github_auth"] = page.login(github_provider)
page.session["google_auth"] = page.login(google_provider)
```



#### 인증 결과 확인

인증 성공 또는 실패 시 page.on_login 이벤트 핸들러가 호출됩니다.

이벤트 처리기 인수 e는 다음 속성을 가진 LoginEvent 클래스의 인스턴스입니다.

- `error` (str) - OAuth error.
- `error_description` (str) - OAuth error description.

error가 빈 문자열이면 승인이 성공한 것입니다.

이 이벤트 핸들러를 사용하여 로그인/아웃 UI를 전환할 수 있습니다. 예를 들면 다음과 같습니다.

``` py
import os

import flet as ft
from flet.auth.providers.github_oauth_provider import GitHubOAuthProvider

def main(page: ft.Page):

    provider = GitHubOAuthProvider(
        client_id=os.getenv("GITHUB_CLIENT_ID"),
        client_secret=os.getenv("GITHUB_CLIENT_SECRET"),
        redirect_url="http://localhost:8550/api/oauth/redirect",
    )

    def login_button_click(e):
        page.login(provider, scope=["public_repo"])

    def on_login(e: ft.LoginEvent):
        if not e.error:
            toggle_login_buttons()

    def logout_button_click(e):
        page.logout()

    def on_logout(e):
        toggle_login_buttons()

    def toggle_login_buttons():
        login_button.visible = page.auth is None
        logout_button.visible = page.auth is not None
        page.update()

    login_button = ft.ElevatedButton("Login with GitHub", on_click=login_button_click)
    logout_button = ft.ElevatedButton("Logout", on_click=logout_button_click)
    toggle_login_buttons()
    page.on_login = on_login
    page.on_logout = on_logout
    page.add(login_button, logout_button)

ft.app(target=main, port=8550, view=ft.WEB_BROWSER)
```



#### 사용자 세부 정보 액세스

page.login() 메서드가 fetch_user=True(기본값)로 호출되면 사용자 프로필이 page.auth.user에 할당됩니다.

모든 기본 제공 OAuth 제공자는 고유한 사용자 식별자인 user.id 속성을 구현합니다. 이 값은 제공자(숫자, Guid 또는 이메일)에 따라 다르며 앱에서 사용자 키로 사용할 수 있습니다.

나머지 사용자 프로필 속성은 공급자에 따라 다르며 인덱서를 사용하여 액세스할 수 있습니다. 예를 들어 GitHub 사용자의 일부 속성을 인쇄하려면 다음을 수행하십시오.

``` py
print("Name:", page.auth.user["name"])
print("Login:", page.auth.user["login"])
print("Email:", page.auth.user["email"])
```



#### OAuth 토큰 사용

승인에 성공하면 page.auth.token에는 공급자의 API에 액세스하는 데 사용할 수 있는 OAuth 토큰이 포함됩니다. 토큰 개체에는 다음과 같은 속성이 있습니다.

- `access_token` - API 요청 헤더에서 인증 토큰으로 사용되는 액세스 토큰입니다.
- `scope` - 토큰의 범위.
- `token_type` - 액세스 토큰 유형, 예: 교군꾼.
- `expires_in` - 액세스 토큰이 만료되는 선택적 시간(초).
- `expires_at` - 액세스 토큰이 만료되는 선택적 시간(time.time() + expires_in).
- `refresh_token` - 이전 액세스 토큰이 만료되면 새 액세스 토큰을 얻는 데 사용되는 선택적 새로 고침 토큰입니다.

일반적으로 공급자의 API를 호출하는 데(예: 사용자의 GitHub 리포지토리 나열) page.auth.token.access_token만 필요합니다.

``` py
import requests
headers = {"Authorization": "Bearer {}".format(page.auth.token.access_token)}
repos_resp = requests.get("https://api.github.com/user/repos", headers=headers)
user_repos = json.loads(repos_resp.text)
for repo in user_repos:
    print(repo["full_name"])
```



> **NOTE**
>
> 코드 어딘가에 page.auth.token에 대한 참조를 저장하지 말고 액세스 토큰을 가져와야 할 때마다 page.auth.token을 호출하십시오. page.auth.token은 OAuth 토큰이 만료되면 자동으로 갱신하는 속성입니다.
>
> 올바른 코드:
>
> ``` py
> access_token = page.auth.token.access_token
> ```
>
> 잘못된 코드:
>
> ``` py
> token = page.auth.token
> # some other code
> access_token = token.access_token # token could expire by this moment
> ```



#### 인증 토큰 저장 및 복원

영구 로그인(로그인 페이지의 '기억하기' 확인란)을 구현하려면 클라이언트 저장소에 인증 토큰을 저장하고 다음에 사용자가 Flet 앱을 열 때 로그인하는 데 사용할 수 있습니다.

인증 토큰을 JSON으로 직렬화하려면:

``` py
jt = page.auth.token.to_json()
```

> **CAUTION**
>
> 중요한 데이터를 클라이언트 스토리지로 보내기 전에 암호화합니다.

Flet에는 대칭 알고리즘(암호화 및 암호 해독에 동일한 키가 사용됨)을 사용하여 텍스트 데이터를 암호화하는 유틸리티 방법이 포함되어 있습니다. 그들은 약간의 추가 강화가 있는 AES 128인 암호화 패키지의 Fernet 구현과 PBKDF2를 사용하여 사용자 암호에서 암호화 키를 파생합니다.

JSON 토큰을 암호화하려면:

``` py
import os
from flet.security import encrypt, decrypt

secret_key = os.getenv("MY_APP_SECRET_KEY")
# returns base64-encoded string
ejt = encrypt(jt, secret_key)
```

> **CAUTION**
>
> 우리는 환경 변수에서 비밀 키(일명 암호, 암호 등)를 가져오고 있습니다. 실수로 대중에게 노출되지 않도록 소스 코드에 비밀을 포함하지 마십시오!

앱을 실행하기 전에 명령줄에서 암호를 설정합니다.

``` bash
$ export MY_APP_SECRET_KEY="<secret>"
```

이제 암호화된 값을 클라이언트 저장소에 저장할 수 있습니다.

``` py
page.client_storage.set("myapp.auth_token", ejt)
```

다음에 사용자가 앱을 열면 클라이언트 저장소에서 암호화된 토큰을 읽을 수 있고, 존재하는 경우 암호를 해독하고 page.login() 메서드에서 사용할 수 있습니다.

``` py
ejt = page.client_storage.get("myapp.auth_token")
if ejt:
    jt = decrypt(ejt, secret_key)
    page.login(provider, saved_token=jt)
```

[전체 앱 예시를 참조하세요.](https://github.com/flet-dev/examples/blob/main/python/apps/authentication/github-oauth-with-listing-repos.py)

``` py
import json
import os

import flet
import requests
from flet import (
    AppBar,
    ElevatedButton,
    Icon,
    ListTile,
    ListView,
    LoginEvent,
    Page,
    Row,
    Text,
    icons,
)
from flet.auth.providers.github_oauth_provider import GitHubOAuthProvider
from flet.security import decrypt, encrypt


def main(page: Page):
    # encryption passphrase
    secret_key = os.getenv("MY_APP_SECRET_KEY")

    # configure provider
    provider = GitHubOAuthProvider(
        client_id=os.getenv("GITHUB_CLIENT_ID"),
        client_secret=os.getenv("GITHUB_CLIENT_SECRET"),
        redirect_url="http://localhost:8550/api/oauth/redirect",
    )

    # client storage keys
    AUTH_TOKEN_KEY = "myapp.auth_token"

    def perform_login(e):
        # perform login
        saved_token = None
        ejt = page.client_storage.get(AUTH_TOKEN_KEY)
        if ejt:
            saved_token = decrypt(ejt, secret_key)
        if e is not None or saved_token is not None:
            page.login(provider, saved_token=saved_token, scope=["public_repo"])

    def on_login(e: LoginEvent):
        if e.error:
            raise Exception(e.error)

        # save token in a client storage
        jt = page.auth.token.to_json()
        ejt = encrypt(jt, secret_key)
        page.client_storage.set(AUTH_TOKEN_KEY, ejt)

        logged_user.value = f"Hello, {page.auth.user['name']}!"
        toggle_login_buttons()
        list_github_repositories()
        page.update()

    def list_github_repositories():
        repos_view.controls.clear()
        if page.auth:
            headers = {
                "Authorization": "Bearer {}".format(page.auth.token.access_token)
            }
            repos_resp = requests.get(
                "https://api.github.com/user/repos", headers=headers
            )
            user_repos = json.loads(repos_resp.text)
            for repo in user_repos:
                repos_view.controls.append(
                    ListTile(
                        leading=Icon(icons.FOLDER_ROUNDED),
                        title=Text(repo["full_name"]),
                    )
                )

    def logout_button_click(e):
        page.client_storage.remove(AUTH_TOKEN_KEY)
        page.logout()

    def on_logout(e):
        toggle_login_buttons()
        list_github_repositories()
        page.update()

    def toggle_login_buttons():
        login_button.visible = page.auth is None
        logged_user.visible = logout_button.visible = page.auth is not None

    logged_user = Text()
    login_button = ElevatedButton("Login with GitHub", on_click=perform_login)
    logout_button = ElevatedButton("Logout", on_click=logout_button_click)
    repos_view = ListView(expand=True)
    page.on_login = on_login
    page.on_logout = on_logout
    toggle_login_buttons()
    perform_login(None)
    page.add(Row([logged_user, login_button, logout_button]), repos_view)


flet.app(target=main, port=8550, view=flet.WEB_BROWSER)
```



#### Signing out

page.logout()을 호출하면 page.auth 참조가 재설정되고 page.on_logout 이벤트 핸들러가 트리거됩니다.

로그아웃 방법에서 저장된 토큰을 제거할 수 있습니다. 예를 들면 다음과 같습니다.

``` py
def logout_button_click(e):
    page.client_storage.remove(AUTH_TOKEN_KEY)
    page.logout()
```

[전체 앱 예시를 참조하세요.](https://github.com/flet-dev/examples/blob/main/python/apps/authentication/github-oauth-with-listing-repos.py)

``` py
import json
import os

import flet
import requests
from flet import (
    AppBar,
    ElevatedButton,
    Icon,
    ListTile,
    ListView,
    LoginEvent,
    Page,
    Row,
    Text,
    icons,
)
from flet.auth.providers.github_oauth_provider import GitHubOAuthProvider
from flet.security import decrypt, encrypt


def main(page: Page):
    # encryption passphrase
    secret_key = os.getenv("MY_APP_SECRET_KEY")

    # configure provider
    provider = GitHubOAuthProvider(
        client_id=os.getenv("GITHUB_CLIENT_ID"),
        client_secret=os.getenv("GITHUB_CLIENT_SECRET"),
        redirect_url="http://localhost:8550/api/oauth/redirect",
    )

    # client storage keys
    AUTH_TOKEN_KEY = "myapp.auth_token"

    def perform_login(e):
        # perform login
        saved_token = None
        ejt = page.client_storage.get(AUTH_TOKEN_KEY)
        if ejt:
            saved_token = decrypt(ejt, secret_key)
        if e is not None or saved_token is not None:
            page.login(provider, saved_token=saved_token, scope=["public_repo"])

    def on_login(e: LoginEvent):
        if e.error:
            raise Exception(e.error)

        # save token in a client storage
        jt = page.auth.token.to_json()
        ejt = encrypt(jt, secret_key)
        page.client_storage.set(AUTH_TOKEN_KEY, ejt)

        logged_user.value = f"Hello, {page.auth.user['name']}!"
        toggle_login_buttons()
        list_github_repositories()
        page.update()

    def list_github_repositories():
        repos_view.controls.clear()
        if page.auth:
            headers = {
                "Authorization": "Bearer {}".format(page.auth.token.access_token)
            }
            repos_resp = requests.get(
                "https://api.github.com/user/repos", headers=headers
            )
            user_repos = json.loads(repos_resp.text)
            for repo in user_repos:
                repos_view.controls.append(
                    ListTile(
                        leading=Icon(icons.FOLDER_ROUNDED),
                        title=Text(repo["full_name"]),
                    )
                )

    def logout_button_click(e):
        page.client_storage.remove(AUTH_TOKEN_KEY)
        page.logout()

    def on_logout(e):
        toggle_login_buttons()
        list_github_repositories()
        page.update()

    def toggle_login_buttons():
        login_button.visible = page.auth is None
        logged_user.visible = logout_button.visible = page.auth is not None

    logged_user = Text()
    login_button = ElevatedButton("Login with GitHub", on_click=perform_login)
    logout_button = ElevatedButton("Logout", on_click=logout_button_click)
    repos_view = ListView(expand=True)
    page.on_login = on_login
    page.on_logout = on_logout
    toggle_login_buttons()
    perform_login(None)
    page.add(Row([logged_user, login_button, logout_button]), repos_view)


flet.app(target=main, port=8550, view=flet.WEB_BROWSER)
```



#### 승인 흐름 맞춤설정

기본적으로 OAuth 인증 흐름은 새 브라우저 창/탭(데스크톱 앱), 브라우저 팝업 창(웹) 또는 인앱 웹 보기(모바일)에서 발생합니다.

권한 부여 흐름이 완료되면 사용자는 Flet의 OAuth 콜백 페이지(/api/oauth/redirect)로 리디렉션되어 JavaScript로 브라우저 창/탭을 닫으려고 시도하고 JavaScript 닫기가 작동하지 않는 경우 창을 수동으로 닫는 지침을 사용자에게 제공합니다. .

모바일 앱의 인앱 웹 보기는 JavaScript에 의존하지 않고 Flet에 의해 닫힐 수 있으므로 이 섹션은 Flet 데스크톱 및 웹 앱에만 적용됩니다.

page.login() 메서드에서 '인증 완료' 페이지의 내용을 사용자 지정할 수 있습니다. 예를 들면 다음과 같습니다.

``` html
complete_page_html = """
<!DOCTYPE html>
<html>
  <head>
    <title>Signed in to MyApp</title>
  </head>
<body>
  <script type="text/javascript">
      window.close();
  </script>
  <p>You've been successfully signed in! You can close this tab or window now.</p>
</body>
</html>
"""

page.login(
    provider,
    complete_page_html=complete_page_html,
)
```

또한 사용자에게 더 친숙할 수 있는 동일한 탭에서 공급자의 인증 페이지를 열도록 웹 앱을 변경하고 팝업 차단기를 처리하지 않도록 할 수 있습니다.

``` py
page.login(
    provider,
    on_open_authorization_url=lambda url: page.launch_url(url, web_window_name="_self"),
    redirect_to_page=True
)
```

새 탭에서 흐름을 열려면(_self가 _blank로 대체됨):

``` py
page.login(
    provider,
    on_open_authorization_url=lambda url: page.launch_url(url, web_window_name="_blank")
)
```



#### 사용자 지정 OAuth 공급자 구성

flet.auth.oauth_provider.OAuthProvider 클래스를 사용하여 앱에서 모든 OAuth 호환 인증 공급자를 구성할 수 있습니다.

LinkedIn 인증 코드 흐름 가이드의 지침에 따라 Flet 앱 사용자가 자신의 LinkedIn 계정으로 로그인할 수 있도록 LinkedIn OAuth 공급자를 구성하는 데 필요한 모든 매개 변수를 얻을 수 있습니다.

``` py
import os

import flet as ft
from flet.auth.oauth_provider import OAuthProvider

def main(page: ft.Page):

    provider = OAuthProvider(
        client_id=os.getenv("LINKEDIN_CLIENT_ID"),
        client_secret=os.getenv("LINKEDIN_CLIENT_SECRET"),
        authorization_endpoint="https://www.linkedin.com/oauth/v2/authorization",
        token_endpoint="https://www.linkedin.com/oauth/v2/accessToken",
        redirect_url="http://localhost:8550/api/oauth/redirect",
        user_endpoint="https://api.linkedin.com/v2/me",
        user_scopes=["r_liteprofile", "r_emailaddress"],
        user_id_fn=lambda u: u["id"]
    )

    def login_click(e):
        page.login(provider)

    def on_login(e):
        if e.error:
            raise Exception(e.error)
        print("User ID:", page.auth.user.id)
        print("Access token:", page.auth.token.access_token)

    page.on_login = on_login
    page.add(ft.ElevatedButton("Login with LinkedIn", on_click=login_click))

ft.app(target=main, port=8550, view=ft.WEB_BROWSER)
```

필수 공급자 설정:

- `client_id`
- `client_secret`
- `authorization_endpoint`
- `token_endpoint`
- `redirect_url`

다른 예제와 마찬가지로 클라이언트 ID 및 클라이언트 암호는 환경 변수에서 가져옵니다.



---

### 클라이언트 스토리지

출처: https://flet.dev/docs/guides/python/client-storage



Flet의 클라이언트 저장소 API를 사용하면 클라이언트 측의 키-값 데이터를 영구 저장소에 저장할 수 있습니다. Flet 구현은 shared_preferences Flutter 패키지를 사용합니다.

실제 저장 메커니즘은 Flet 앱이 실행되는 플랫폼에 따라 다릅니다.

- Web - [Local storage](https://developer.mozilla.org/en-US/docs/Web/API/Storage).
- Desktop - JSON file.
- iOS - [NSUserDefaults](https://developer.apple.com/documentation/foundation/nsuserdefaults).
- Android - [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences).

스토리지에 데이터 쓰기:

```python
# strings
page.client_storage.set("key", "value")

# numbers, booleans
page.client_storage.set("number.setting", 12345)
page.client_storage.set("bool_setting", True)

# lists
page.client_storage.set("favorite_colors", ["read", "green", "blue"])
```



NOTE

shared_preferences 플러그인을 사용하는 각 Flutter 애플리케이션에는 고유한 환경 설정 세트가 있습니다. 동일한 Flet 클라이언트(Flutter 앱)가 여러 Flet 앱의 UI를 실행하는 데 사용되므로 하나의 Flet 애플리케이션에 저장된 모든 값은 동일한 사용자가 실행하는 다른 Flet 앱에서 보거나 사용할 수 있습니다.

하나의 애플리케이션 설정을 다른 것과 구별하려면 {company}.{product}.와 같이 모든 스토리지 키에 고유한 접두어를 사용하는 것이 좋습니다. 예를 들어 하나의 앱에 인증 토큰을 저장하려면 acme.one_app.auth_token 키를 사용하고 다른 앱에서는 acme.second_app.auth_token을 사용합니다.

CAUTION

민감한 데이터를 클라이언트 저장소로 보내기 전에 암호화하는 것은 Flet 앱 개발자의 책임이므로 다른 앱이나 앱 사용자가 읽거나 변조하지 않습니다.

데이터 읽기:

```python
# The value is automatically converted back to the original type
value = page.client_storage.get("key")

colors = page.client_storage.get("favorite_colors")
# colors = ["read", "green", "blue"]
```



키가 있는지 확인.

```python
page.client_storage.contains_key("key") # True if the key exists
```



모든 키 가져오기:

```python
page.client_storage.get_keys("key-prefix.")
```



값을 제거합니다.

```python
page.client_storage.remove("key")
```



저장소를 비웁니다.

```python
page.client_storage.clear()
```



CAUTION

clear()는 동일한 사용자가 실행한 모든 Flet 앱의 모든 기본 설정을 제거하고 영구적인 애플리케이션 데이터가 클라이언트 저장소에 저장되어서는 안 된다는 경고 역할을 하는 위험한 함수입니다.



---

### Session storage

Flet은 서버 측의 사용자 세션에 키-값 데이터를 저장하기 위한 API를 제공합니다.

세션에 데이터 쓰기:

```python
# strings
page.session.set("key", "value")

# numbers, booleans
page.session.set("number.setting", 12345)
page.session.set("bool_setting", True)

# lists
page.session.set("favorite_colors", ["read", "green", "blue"])
```



CAUTION

현재 Flet 구현에서 세션 저장소에 저장된 데이터는 일시적이며 앱을 다시 시작할 때 보존되지 않습니다.



Reading data:

```python
# The value is automatically converted back to the original type
value = page.session.get("key")

colors = page.session.get("favorite_colors")
# colors = ["read", "green", "blue"]
```



Check if a key exists:

```python
page.session.contains_key("key") # True if the key exists
```



Get all keys:

```python
page.session.get_keys()
```



Remove a value:

```python
page.session.remove("key")
```



Clear the session storage:

```python
page.session.clear()
```



---

### 민감한 데이터 암호화

출처: https://flet.dev/docs/guides/python/encrypting-sensitive-data\

토큰, 키, 신용 카드 번호 및 기타 '비밀'과 같은 민감한 데이터는 데이터 위반을 방지하기 위해 암호화된 형태로 유휴 상태(데이터베이스, 파일, 클라이언트 스토리지)에 저장되어야 합니다.

Flet에는 대칭 알고리즘(암호화 및 암호 해독에 동일한 키가 사용됨)을 사용하여 중요한 텍스트 데이터를 암호화 및 해독하는 유틸리티 방법이 포함되어 있습니다. 그들은 약간의 추가 강화가 있는 AES 128인 암호화 패키지의 Fernet 구현과 PBKDF2를 사용하여 사용자 암호에서 암호화 키를 파생합니다.



#### Secret key

암호화 비밀 키(암호 또는 암호 문구라고도 함)는 사용자가 구성하고 데이터를 암호화 및 해독하는 데 사용되는 임의의 암호와 유사한 문자열입니다. 암호화 알고리즘은 비밀 키를 사용하여 암호화 키(32바이트)를 '파생'합니다.

> **DANGER**
>
> 실수로 대중에게 노출되지 않도록 소스 코드에 비밀을 포함하지 마십시오!

환경 변수를 통해 앱에 비밀을 제공할 수 있습니다.

``` py
import os
secret_key = os.getenv("MY_APP_SECRET_KEY")
```

앱을 실행하기 전에 명령줄에서 암호를 설정합니다.

```bash
$ export MY_APP_SECRET_KEY="<secret>"
```

> **NOTE**
>
> 환경 변수를 통해 비밀을 전달하는 것은 개발자와 서비스 공급자 사이에서 일반적인 관행이지만 일부 환경에서 비밀이 유출되는 것을 완전히 방지하지는 못합니다. 비밀 파일 또는 볼트 서비스 마운트와 같은 다른 메커니즘을 사용하여 응용 프로그램에 비밀을 주입할 수 있습니다.



#### 데이터 암호화

encrypt() 메서드를 사용하여 문자열을 암호화합니다.

``` py
import os
from flet.security import encrypt, decrypt

secret_key = os.getenv("MY_APP_SECRET_KEY")
plain_text = "This is a secret message!"
encrypted_data = encrypt(plain_text, secret_key)
```

encryption_data는 URL 안전 base64 인코딩 문자열입니다.

encrypt는 문자열만 허용하므로 모든 개체는 암호화 전에 JSON, XML 또는 기타 텍스트 기반 형식으로 직렬화되어야 합니다.

``` py
import os
from flet.security import encrypt, decrypt

secret_key = os.getenv("MY_APP_SECRET_KEY")
encrypted_data = "601llp2zpPp4QjBWe2cOwGdBQUFBQUJqTTFJbmgyWU5jblVp..."
plain_text = decrypt(encrypted_data, secret_key)
print(plain_text)
```



---

### PubSub

Flet을 사용하여 채팅 앱을 빌드하는 경우 세션 간에 사용자 메시지를 전달해야 합니다. 사용자가 메시지를 보내면 다른 모든 앱 세션에 브로드캐스트되고 해당 페이지에 표시되어야 합니다.

Flet은 페이지 세션 간의 비동기 통신을 위한 간단한 내장 PubSub 메커니즘을 제공합니다.

Flet PubSub를 사용하면 모든 앱 세션에 메시지를 브로드캐스팅하거나 특정 '주제'(또는 '채널') 가입자에게만 보낼 수 있습니다.

일반적인 PubSub 사용법은 다음과 같습니다.

- 브로드캐스트 메시지를 구독하거나 앱 세션 시작 시 주제를 구독합니다.
- 브로드캐스트 메시지를 보내거나 '보내기' 버튼 클릭과 같은 일부 이벤트에 대한 주제로 보냅니다.
- 브로드캐스트 메시지 구독을 취소하거나 '탈퇴' 버튼 클릭과 같은 특정 이벤트의 주제 구독을 취소합니다.
- page.on_close의 모든 항목에서 구독을 취소합니다.

다음은 간단한 채팅 애플리케이션의 예입니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Flet Chat"

    # subscribe to broadcast messages
    def on_message(msg):
        messages.controls.append(ft.Text(msg))
        page.update()

    page.pubsub.subscribe(on_message)

    def send_click(e):
        page.pubsub.send_all(f"{user.value}: {message.value}")
        # clean up the form
        message.value = ""
        page.update()

    messages = ft.Column()
    user = ft.TextField(hint_text="Your name", width=150)
    message = ft.TextField(hint_text="Your message...", expand=True)  # fill all the space
    send = ft.ElevatedButton("Send", on_click=send_click)
    page.add(messages, ft.Row(controls=[user, message, send]))

ft.app(target=main, view=ft.WEB_BROWSER)
```

![img](.\Images\chat-app-example.gif)



---

### User controls

사용자 컨트롤(UserControl)을 사용하면 기존 Flet 컨트롤을 결합하여 격리된 재사용 가능 구성 요소를 구축할 수 있습니다. 사용자 컨트롤은 컨트롤처럼 동작하며 메서드와 속성을 가질 수 있습니다.

다음은 최소한의 사용자 컨트롤(UserControl) 예입니다.

``` py
import flet as ft

class GreeterControl(ft.UserControl):
    def build(self):
        return ft.Text("Hello!")

def main(page):
    page.add(GreeterControl())

ft.app(target=main)
```

UserControl은 컨트롤의 UI를 빌드하기 위해 호출되는 build() 메서드를 구현해야 하며 단일 컨트롤 인스턴스 또는 컨트롤 목록을 반환해야 합니다. UserControl은 스택에서 상속되므로 여러 자식이 서로 위에 정렬됩니다. 컨트롤의 UI를 다르게 정렬해야 하는 경우 행, 열 또는 기타 레이아웃 컨트롤을 사용하세요. 예를 들면 다음과 같습니다.

``` py
class GreeterControl(ft.UserControl):
    def build(self):
        return ft.Column([
            ft.TextField(label="Your name"),
            ft.ElevatedButton("Login")
        ])
```

UserControl은 외부 레이아웃과 격리됩니다. 즉, 상위 컨트롤에 대해 update() 메서드가 호출될 때 UserControl 내부의 모든 변경 사항은 업데이트 다이제스트에 포함되지 않습니다. UserControl은 self.update()를 호출하여 변경 사항을 Flet 페이지에 푸시해야 합니다. 예를 들면 다음과 같습니다.

``` py
import flet as ft

class Counter(ft.UserControl):
    def add_click(self, e):
        self.counter += 1
        self.text.value = str(self.counter)
        self.update()

    def build(self):
        self.counter = 0
        self.text = ft.Text(str(self.counter))
        return ft.Row([self.text, ft.ElevatedButton("Add", on_click=self.add_click)])

def main(page):
    page.add(Counter(), Counter())

ft.app(target=main)
```

![img](.\Images\user-control-counter.gif)

이벤트 핸들러(예: def add_click(self, e)) 및 컨트롤 참조(예: self.text)를 클래스 멤버로 선언하거나 로컬 변수 및 내부 함수를 사용하여 build() 메서드 내에서 모든 UserControl의 논리를 구현할 수 있습니다. 예를 들어 위의 코드는 다음과 같이 다시 작성할 수 있습니다.

``` py
class Counter(ft.UserControl):
    def build(self):

        self.counter = 0
        text = ft.Text(str(self.counter))

        def add_click(e):
            self.counter += 1
            text.value = str(self.counter)
            self.update()

        return ft.Row([text, ft.ElevatedButton("Add", on_click=add_click)])
```

> **NOTE**
>
> counter는 add_click 메서드 내에서 볼 수 없으므로 로컬 변수로 선언할 수 없으므로 클래스 필드 self.counter로 선언해야 합니다.

사용자 컨트롤에는 사용자 지정 데이터를 전달하는 생성자가 있을 수 있습니다. 예를 들면 다음과 같습니다.

``` py
import flet as ft

class Counter(ft.UserControl):
    def __init__(self, initial_count):
        super().__init__()
        self.counter = initial_count

    def build(self):
        text = ft.Text(str(self.counter))
        def add_click(e):
            self.counter += 1
            text.value = str(self.counter)
            self.update()

        return ft.Row([text, ft.ElevatedButton("Add", on_click=add_click)])

# then use the control
def main(page):
    page.add(
        Counter(100),
        Counter(200))

ft.app(target=main)
```

> **NOTE**
>
> super().`__init__`()는 항상 자신의 생성자에서 호출해야 합니다.

사용자 컨트롤은 수명 주기 '후크' 메서드를 제공합니다.

- `did_mount()` - UserControl이 페이지에 추가되고 임시 ID가 할당된 후에 호출됩니다.
- `will_unmount()` - UserControl이 페이지에서 제거되기 전에 호출됩니다.

이러한 방법을 사용하여 간단한 '카운트다운' 컨트롤을 구현할 수 있습니다.

``` py
import flet as ft
import time, threading

class Countdown(ft.UserControl):
    def __init__(self, seconds):
        super().__init__()
        self.seconds = seconds

    def did_mount(self):
        self.running = True
        self.th = threading.Thread(target=self.update_timer, args=(), daemon=True)
        self.th.start()

    def will_unmount(self):
        self.running = False

    def update_timer(self):
        while self.seconds and self.running:
            mins, secs = divmod(self.seconds, 60)
            self.countdown.value = "{:02d}:{:02d}".format(mins, secs)
            self.update()
            time.sleep(1)
            self.seconds -= 1

    def build(self):
        self.countdown = ft.Text()
        return self.countdown

def main(page: Page):
    page.add(Countdown(120), Countdown(60))

ft.app(target=main)
```

![img](.\Images\user-control-countdown.gif)



---

### Control 참조

Flet 컨트롤은 개체이며 해당 속성에 액세스하려면 해당 개체에 대한 참조(변수)를 유지해야 합니다.

다음 예를 고려하십시오.

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

main() 메서드의 맨 처음에 우리는 버튼의 on_click 핸들러에서 사용할 세 개의 컨트롤을 생성합니다. 두 개의 TextField는 이름과 성을 위한 것이고 Column은 인사말 메시지를 위한 컨테이너입니다. 모든 속성이 설정된 컨트롤을 만들고 main() 메서드의 끝에서 page.add() 호출에서 해당 참조(변수)를 사용합니다.

더 많은 모드 컨트롤과 이벤트 핸들러가 추가되면 모든 컨트롤 정의를 한 곳에 유지하는 것이 어려워져 main() 본문 전체에 분산됩니다. page.add() 매개변수를 보면(IDE에서 변수 정의로 계속 점프하지 않고) 최종 양식이 어떻게 생겼는지 상상하기 어렵습니다.

``` py
    page.add(
        first_name,
        last_name,
        ft.ElevatedButton("Say hello!", on_click=btn_click),
        greetings,
    )
```

first_name은 TextField이고 자동 초점이 설정되어 있습니까? 인사말은 행입니까 아니면 열입니까?



#### Ref 클래스

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

참조 세트 Control.ref 특성에 제어를 지정하려면 다음을 수행하십시오.

``` py
page.add(
    ft.TextField(ref=first_name, label="First name", autofocus=True)
)
```

> **NOTE**
>
> 모든 Flet 컨트롤에는 ref 속성이 있습니다.

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

이제 page.add()에서 페이지의 구조와 모든 컨트롤을 명확하게 볼 수 있습니다.

예, .current를 추가해야 하므로 논리가 좀 더 장황해집니다. ref의 컨트롤에 액세스하려면 개인 취향의 문제입니다 :)



---

### Accessibility

Flet은 기본 운영 체제에서 제공하는 것 외에도 접근성을 위한 일류 프레임워크 지원을 포함하는 Flutter를 기반으로 합니다.



#### 스크린 리더

모바일의 경우 화면 판독기(TalkBack, VoiceOver)를 사용하면 시각 장애가 있는 사용자가 화면 콘텐츠에 대한 음성 피드백을 받고 모바일의 제스처 및 데스크톱의 키보드 단축키를 통해 UI와 상호 작용할 수 있습니다. 모바일 장치에서 VoiceOver 또는 TalkBack을 켜고 앱을 탐색하십시오.

웹의 경우 현재 다음 스크린 리더가 지원됩니다.

모바일 브라우저:

- iOS - VoiceOver
- Android - TalkBack

데스크톱 브라우저:

- MacOS - VoiceOver
- Windows - JAWs & NVDA

웹의 스크린 리더 사용자는 시맨틱 트리를 구축하려면 '접근성 활성화' 버튼을 토글해야 합니다.



#### Text

Text.semantics_label 속성을 사용하여 기본 텍스트 컨트롤 의미 체계를 재정의합니다.



#### Buttons

텍스트가 있는 모든 버튼은 적절한 의미 체계를 생성합니다.

Tooltip 속성을 사용하여 IconButton, FloatingActionButton 및 PopupMenuButton 버튼에 대한 스크린 리더 시맨틱을 추가합니다.



#### 맞춤 시맨틱

특정 요구 사항의 경우 시맨틱 제어를 사용하십시오.



#### 디버깅 시맨틱

page.show_semantics_debugger를 True로 설정하여 프레임워크에서 보고하는 접근성 정보를 표시하는 오버레이를 표시합니다.

앱 개발 중에 시맨틱 디버거를 편리하게 전환하기 위해 특정 키보드 단축키를 구현할 수 있습니다.

![img](.\Images\debug-accessibility-toggle.gif)

``` py
import flet as ft

def main(page: ft.Page):
    page.title = "Flet counter example"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    def on_keyboard(e: ft.KeyboardEvent):
        print(e)
        if e.key == "S" and e.ctrl:
            page.show_semantics_debugger = not page.show_semantics_debugger
            page.update()

    page.on_keyboard_event = on_keyboard

    txt_number = ft.Text("0", size=40)

    def button_click(e):
        txt_number.value = str(int(txt_number.value) + 1)
        page.update()

    page.add(
        txt_number,
        ft.Text("Press CTRL+S to toggle semantics debugger"),
        ft.FloatingActionButton(
            icon=ft.icons.ADD, tooltip="Increment number", on_click=button_click
        ),
    )

ft.app(target=main, view=ft.WEB_BROWSER)
```



---

### Mobile support

이 문서에서는 모바일로 전환하기 위한 Flet의 비전을 다루고 로드맵을 제공합니다.



#### Server-Driven UI

Flet은 서버 기반 UI(SDUI) 프레임워크입니다. SDUI는 Technology Radar 게시물에서 가장 잘 설명된 새로운 기술입니다.

> 서버 기반 UI는 렌더링을 모바일 앱의 일반 컨테이너로 분리하고 각 뷰의 구조와 데이터는 서버에서 제공합니다. 이는 한때 앱 스토어를 왕복해야 했던 변경 사항을 이제 서버가 보내는 응답에 대한 간단한 변경을 통해 수행할 수 있음을 의미합니다.

DoorDash, Airbnb, Lyft 등과 같은 회사는 출시 기간을 단축하기 위해 모바일 앱에서 서버 기반 UI를 성공적으로 구현해 왔습니다.



##### Flet approach

Flet은 Python 또는 다른 언어로 작성된 프로그램이 서버에서 실행되고 앱 스토어의 독립형 Flutter 앱(.apk 또는 .ipa 패키지) 또는 Flutter 위젯을 다른 앱의 일부 - 모바일로 전달됨:

![img](.\Images\flet-highlevel-diagram.svg)

SDUI 경험이 준비되면 독립형 모바일 패키지 작업을 시작할 것입니다.



#### Roadmap

모바일 플랫폼에서 Flet 앱에 최상의 경험을 제공하기 위해 올해 말까지 다음 항목을 출시할 계획입니다.

##### Flutter용 Flet 위젯

우리가 할 첫 번째 단계는 Flet 클라이언트를 Flutter 위젯으로 분리하고 패키지를 https://pub.dev에 게시하는 것입니다. 그런 다음 Flet 위젯을 모바일 개발자가 기존 또는 새로운 Flutter 앱에 통합하여 핵심 앱 기능에 동적 서버 기반 UI 경험을 추가할 수 있습니다. 완전한 Flet 앱을 호스팅할 목적으로 단일 Flet 위젯으로 새로운 Flutter 앱을 만들 수도 있습니다.

개발자는 Android, iOS, Linux, macOS 또는 Windows 플랫폼에 앱을 패키징, 서명 및 배포하기 위해 Flutter 가이드를 따릅니다.

Flet 팀은 Flutter 앱의 패키징, 서명 및 게시를 자동화하는 샘플 CI 파이프라인을 제공합니다.

##### iOS 및 Android용 Flet Studio

다음 단계는 'Flet 프레임워크로 개발된 모바일 경험 테스트'를 위한 App Store 및 Google Play의 독립 실행형 'Flet Studio' 앱(이름이 최종이 아님)입니다. 개발자 또는 베타 테스터는 Flet Studio 내에서 호스팅된 Flet 앱의 URL을 '등록'하고 모바일 장치에서 어떻게 작동하는지 즉시 확인할 수 있습니다.

##### 화이트 라벨 Flet 모바일 앱

사용자 App Store 또는 Google Play 계정에 화이트 레이블이 지정된 Flet 앱을 자동 게시하기 위한 가이드 및 CI 파이프라인을 제공할 예정입니다. 이 앱은 특정 앱 URL에 '고정'되며 네트워크 사용량을 최소화하기 위해 앱 자산(미디어, 글꼴)을 추가로 묶을 수 있습니다.

##### Flet 앱용 독립형 모바일 패키지

우리는 Flet 프레임워크, 사용자 프로그램, 언어 런타임 및 모든 종속성을 독립 실행형 모바일 패키지(.apk 또는 .ipa 패키지)로 함께 번들링하기 위한 방법을 조사하고 프로토타입을 개발할 예정이므로 Flet 프로그램에는 웹 서버가 필요하지 않습니다.

##### 기본 앱에 Flet 포함

Flutter Add-to-App 기능을 사용하여 Flet 위젯을 기존 기본 Android 및 iOS 앱(Flutter로 개발되지 않음)에 통합하기 위한 가이드, 샘플 앱 및 CI 파이프라인을 제공할 예정입니다. Put Flutter to work 문서는 Flutter를 기존 모바일 앱에 통합하는 방법에 대한 실제 예를 제공합니다.



---

### 핫 리로드

flet Python 모듈을 설치하면 핫 리로드로 웹 및 데스크탑 앱을 실행할 수 있는 flet 명령 도구(Flet CLI)도 설치됩니다.

``` bash
usage: flet run [-h] [--port PORT] [--directory] [--recursive] [--hidden] [--web]
                script

Runs Flet app in Python with hot reload.

positional arguments:
  script                path to a Python script

options:
  -h, --help            show this help message and exit
  -v, --verbose         -v for detailed output and -vv for more detailed
  -p PORT, --port PORT  custom TCP port to run Flet app on
  -d, --directory       watch script directory
  -r, --recursive       watch script directory and all sub-directories recursively
  -n, --hidden          application window is hidden on startup
  -w, --web             open app in a web browser
```

기본적으로 flet은 단일 스크립트 파일만 감시합니다. 스크립트 디렉토리의 모든 파일을 보려면 --directory 플래그를 사용하십시오. --recursive 플래그를 사용하여 스크립트 디렉토리와 모든 하위 디렉토리를 재귀적으로 감시합니다.

예를 들어:

``` bash
flet run main.py -d
```



---

### 데스크톱 앱 패키징

Flet Python 앱과 모든 종속 항목을 실행 파일로 패키징할 수 있으며 사용자는 Python 인터프리터나 모듈을 설치하지 않고도 컴퓨터에서 실행할 수 있습니다.

Flet은 PyInstaller API를 래핑하여 Flet Python 앱과 모든 종속성을 Windows, macOS 및 Linux용 단일 패키지로 패키징합니다. Windows 패키지를 생성하려면 Windows에서 PyInstaller를 실행해야 합니다. Linux 앱을 빌드하려면 Linux에서 실행해야 합니다. macOS에서 macOS 앱을 빌드합니다.

PyInstaller 설치부터 시작하십시오.

``` py
pip install pyinstaller
```

.py 파일이 있는 디렉터리로 이동하고 다음 명령을 사용하여 앱을 빌드합니다.

``` py
flet pack your_program.py
```

이제 번들 Flet 앱을 dist 폴더에서 사용할 수 있습니다. 프로그램을 실행하여 작동하는지 확인하십시오.

맥OS에서:

``` bash
open dist/your_program.app
```

Windows에서:

``` cmd
dist\your_program.exe
```

리눅스에서:

``` bash
dist/your_program
```

이제 dist 폴더의 내용을 압축하여 사용자에게 배포할 수 있습니다! 패키지 프로그램을 실행하기 위해 Python이나 Flet을 설치할 필요가 없습니다. Electron의 훌륭한 대안입니다!

기본적으로 실행 파일/번들은 Python 스크립트와 동일한 이름을 갖습니다. --name 인수로 변경할 수 있습니다.

``` bash
flet pack your_program.py --name bundle_name
```



#### 패키지 아이콘 사용자 정의

기본 번들 앱 아이콘은 플로피 디스크가 컴퓨터 데이터를 저장하는 데 사용되었던 고대 시대를 그리워하는 젊은 개발자에게 혼란을 줄 수 있는 디스켓입니다.

--icon 인수를 추가하여 아이콘을 자신의 아이콘으로 바꿀 수 있습니다.

``` bash
flet pack your_program.py --icon <your-image.png>
```

PyInstaller는 제공된 PNG를 플랫폼별 형식(Windows의 경우 .ico 및 macOS의 경우 .icns)으로 변환하지만 이를 위해서는 Pillow 모듈을 설치해야 합니다.





#### 패키징 자산

Flet 앱에는 자산이 포함될 수 있습니다. 제공된 앱 자산은 your_program.py 옆의 assets 폴더에 있으며 macOS/Linux에서 --add-data 인수를 사용하여 애플리케이션 패키지에 추가할 수 있습니다.

``` py
flet pack your_program.py --add-data "assets:assets"
```

Windows  에서는 (`assets;assets`) ;로 구분되어야 합니다.

``` bash
flet pack your_program.py --add-data "assets;assets"
```



#### macOS 번들 사용자 지정

macOS 번들 세부 정보는 다음 플렛 팩 macOS 관련 인수를 사용하여 사용자 지정할 수 있습니다.

- `--product-name` - Dock, 활동 모니터, 정보 대화 상자에 표시되는 macOS 번들의 표시 이름입니다.
- `--product-version` - '정보' 대화 상자에 표시된 번들 버전.
- `--copyright` - '정보' 대화 상자에 표시된 저작권 표시.
- `--bundle-id` 고유 번들 ID.



#### Windows 실행 가능 메타데이터 사용자 정의

Windows 실행 가능 '세부 정보' 속성 대화 상자는 다음 플렛 팩 인수를 사용하여 사용자 지정할 수 있습니다.

- --product-name - '제품 이름' 필드.
- --product-version - '제품 버전' 필드.
- --file-version - '파일 버전' 필드.
- --file-description - '파일 설명' 필드, 작업 관리자의 프로그램 표시 이름.
- --copyright - '저작권' 필드.



#### 다중 플랫폼 패키징에 CI 사용

특정 OS용 PyInstaller로 앱 패키지를 만들려면 해당 OS에서 실행해야 합니다.

Mac 또는 PC에 대한 액세스 권한이 없는 경우 AppVeyor - Windows, Linux 및 macOS용 지속적 통합 서비스를 사용하여 세 가지 플랫폼 모두에 대한 앱을 번들로 묶을 수 있습니다. 즉, 지속적 통합(CI)은 리포지토리에 대한 모든 푸시에서 애플리케이션을 빌드, 테스트 및 배포(지속적인 전달 - CD)하는 자동화된 프로세스입니다.

AppVeyor는 GitHub, GitLab 및 Bitbucket에서 호스팅되는 오픈 소스 프로젝트에 대해 무료입니다. AppVeyor를 사용하려면 해당 소스 제어 공급자 중 하나에 있는 리포지토리에 앱을 푸시합니다.

AppVeyor를 시작하려면 무료 계정에 가입하세요.

'새 프로젝트' 버튼을 클릭하고 AppVeyor가 GitHub, GitLab 또는 Bitbucket 계정에 액세스할 수 있도록 승인하고 프로그램이 있는 저장소를 선택하고 새 프로젝트를 만듭니다.

이제 Windows, Linux 및 macOS용 앱 패키징을 구성하려면 리포지토리 appveyor.yml의 루트에 다음 콘텐츠가 포함된 파일을 추가합니다. appveyor.yml은 모든 커밋에서 실행해야 하는 빌드, 테스트, 패키징 및 배포 명령을 설명하는 빌드 구성 파일 또는 CI 워크플로입니다.

> **NOTE**
>
> flet-dev/python-ci-example 리포지토리를 포크하고 필요에 따라 사용자 지정할 수 있습니다.

GitHub 리포지토리에 변경 사항을 푸시하면 AppVeyor가 자동으로 새 빌드를 시작합니다.

![img](.\Images\appveyor-ci-flet-python-project.png)

리포지토리에 푸시할 때마다 CI 워크플로가 수행하는 작업:

- 깨끗한 가상 머신에 리포지토리를 복제합니다.
- pip를 사용하여 앱 종속성을 설치합니다.
- Flet pack을 실행하여 Python 앱을 Windows, macOS 및 Ubuntu용 번들로 패키징합니다.
- Zip/Tar 앱 번들을 만들고 'Artifacts'에 업로드합니다.
- 새 태그가 푸시되면 App Bundle을 GitHub 릴리스에 업로드합니다. 새 태그를 눌러 출시하세요!

> 깃허브_토큰
>
> appveyor.yml의 GITHUB_TOKEN은 생성된 패키지를 저장소 '릴리스'에 게시하기 위해 AppVeyor에서 사용하는 GitHub 개인 액세스 토큰(PAT)입니다. 자체 토큰을 생성하고 appveyor.yml에서 교체해야 합니다. GitHub 계정에 로그인하고 개인 액세스 토큰 페이지로 이동합니다. '새 토큰 생성'을 클릭하고 공용 또는 개인 저장소에 대해 각각 'public_repo' 또는 'repo' 범위를 선택하십시오. 생성된 토큰을 클립보드에 복사하고 AppVeyor Portal로 돌아갑니다. 구성 데이터 암호화 페이지로 이동하여 토큰을 '암호화할 값' 필드에 붙여넣고 '암호화' 버튼을 클릭합니다. appveyor.yml의 GITHUB_TOKEN 아래에 암호화된 값을 넣습니다.

Python 프로젝트에 대해 AppVeyor를 구성하고 새 태그를 리포지토리에 푸시하고 GitHub 릴리스의 세 가지 플랫폼 모두에 대한 데스크톱 번들을 '자동으로' 가져옵니다! 

![img](.\Images\appveyor-ci-flet-github-releases.png)

GitHub 릴리스 외에도 Amazon S3 버킷 또는 Azure Blob 스토리지에 대한 아티팩트 릴리스를 구성할 수도 있습니다.



## Deploying web app

Flet 앱은 Python 앱과 Flet 웹 서버가 모두 번들로 함께 배포됨을 의미하는 '독립 실행형' 웹 앱으로 배포될 수 있습니다.



### 호스팅 제공업체 선택

Flet 앱은 WebSocket을 사용하여 UI의 실시간 부분 업데이트 및 이벤트를 프로그램으로 다시 보냅니다. Flet 앱의 호스팅 공급자를 선택할 때 WebSockets 지원에 주의를 기울여야 합니다. 때로는 WebSocket이 허용되지 않거나 더 비싼 제품의 일부로 제공되며 때로는 시간 초과로 WebSocket 연결을 주기적으로 끊는 프록시가 있습니다(Flet은 재연결 논리를 구현하지만 어쨌든 앱 사용자에게는 불쾌한 동작일 수 있습니다).

Flet 앱용 호스팅 공급자를 선택할 때 또 다른 중요한 요소는 대기 시간입니다. UI에 대한 모든 사용자 작업은 Flet 앱에 메시지를 보내고 앱은 업데이트된 UI를 사용자에게 다시 보냅니다. 대부분의 사용자에게 더 가까운 곳에서 앱을 실행할 수 있도록 호스팅 제공업체에 여러 데이터 센터가 있는지 확인하세요.

> **NOTE**
>
> 우리는 이 섹션에서 호스팅 공급자와 제휴하지 않습니다. 우리는 단지 그들의 서비스를 사용하고 그것을 좋아합니다.



---

### Fly.io

[Fly.io는 강력한 WebSocket 지원을 제공하며 사용자와 가까운 데이터 센터에 앱을 배포할 수 있습니다. 최대 3개의 애플리케이션을 무료로 호스팅할 수 있는 넉넉한 프리 티어로 매우 매력적인 가격이 책정되어 있습니다.

Fly를 시작하려면 flyctl을 설치한 다음 인증합니다.

```text
flyctl auth login
```



flyctl로 앱을 배포하려면 Python 앱이 있는 폴더에 다음 3개 파일을 추가해야 합니다.

애플리케이션 종속성 목록이 포함된 requirements.txt를 생성합니다. 최소한 flet 모듈을 포함해야 합니다.

requirements.txt

```txt
flet>=0.1.33
```



Fly 애플리케이션을 설명하는 fly.toml을 만듭니다.

fly.toml

```toml
app = "<your-app-name>"

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
```



https:// your-app-name .fly.dev와 같이 애플리케이션 URL에서도 사용되는 원하는 애플리케이션 이름으로 your-app-name을 바꿉니다.

FLET_SERVER_PORT 환경 변수의 값을 Flet 웹 앱이 실행될 내부 TCP 포트인 8080으로 설정하고 있습니다.

애플리케이션 컨테이너를 빌드하기 위한 명령이 포함된 Dockerfile을 생성합니다.

```Dockerfile
FROM python:3-alpine

WORKDIR /app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 8080

CMD ["python", "./main.py"]
```



`main.py` is a file with your Python program.

NOTE

Fly.io는 모든 앱을 Docker 컨테이너로 배포하지만 Fly의 좋은 점은 무료 원격 Docker 빌더를 제공하므로 컴퓨터에 Docker를 설치할 필요가 없다는 것입니다.

다음으로 명령줄을 앱이 있는 폴더로 전환하고 다음 명령을 실행하여 새 Fly 앱을 만들고 초기화합니다.

```text
flyctl apps create --name <your-app-name>
```



다음을 실행하여 앱을 배포합니다.

```text
flyctl deploy
```



그게 다야! 다음을 실행하여 브라우저에서 앱을 엽니다.

```text
flyctl apps open
```



---

### Replit

Replit은 모든 언어로 작성된 웹 앱을 위한 온라인 IDE 및 호스팅 플랫폼입니다. 프리 티어를 사용하면 일부 성능 제한이 있는 앱을 얼마든지 실행할 수 있습니다.

Replit에서 앱을 실행하려면:

- [Sign up](https://replit.com/signup?from=landing) on Replit.
- Click "New repl" button.
- Select "Python" language from a list and provide repl name, e.g. `my-app`.
- Click "Packages" tab and search for `flet` package; select its latest version.
- Click "Secrets" tab and add `FLET_SERVER_PORT` variable with value `5000`.
- Switch back to "Files" tab and copy-paste your app into `main.py`.
- Run the app. Enjoy.



- 리플릿에 가입하세요.
- '새 교체' 버튼을 클릭합니다.
- 목록에서 'Python' 언어를 선택하고 repl 이름을 제공합니다. 내 앱.
- '패키지' 탭을 클릭하고 플렛 패키지를 검색합니다. 최신 버전을 선택하십시오.
- '비밀' 탭을 클릭하고 값이 5000인 FLET_SERVER_PORT 변수를 추가합니다.
- '파일' 탭으로 다시 전환하고 앱을 복사하여 main.py에 붙여넣습니다.
- 앱을 실행합니다. 즐기다.



---

### Self Hosting

NGINX를 사용하여 자체 서버에서 Flet 앱을 호스팅합니다.

AWS, Oracle, Linode 등에서 사용할 수 있는 무료 및 저렴한 클라우드 서버 계층이 있습니다.



#### Flet 환경 설정

##### `requirements.txt` and virtualenv

애플리케이션 종속성 목록이 포함된 requirements.txt를 생성합니다. 최소한 flet 모듈을 포함해야 합니다.

`requirements.txt`

``` txt
flet>=0.2.4
```

virtualenv를 생성하고 요구 사항을 설치합니다.

``` cmd
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

##### Sample `main.py` Flet app

`main.py`

``` py
import flet as ft
import os


# set Flet path to an empty string to serve at the root URL (e.g., https://lizards.ai/)
# or a folder/path to serve beneath the root (e.g., https://lizards.ai/ui/path
DEFAULT_FLET_PATH = ''  # or 'ui/path'
DEFAULT_FLET_PORT = 8502


def main(page: ft.Page):
    page.title = "You Enjoy Mychatbot"
    page.add(ft.Text("Reba put a stopper in the bottom of the tub"))


if __name__ == "__main__":
    flet_path = os.getenv("FLET_PATH", DEFAULT_FLET_PATH)
    flet_port = int(os.getenv("FLET_PORT", DEFAULT_FLET_PORT))
    ft.app(name=flet_path, target=main, view=None, port=flet_port)
```



#### Flet 서버 자동 시작

##### 시스템 단위 파일 생성

시스템 서비스 단위 파일 flet.service를 사용하여 Flet 서버를 자동으로 시작합니다.

아래 설정에서는 flet 앱 스크립트가 $HOME/flet-app/main.py에 정의되어 있다고 가정합니다. 설정에 따라 User, Group, WorkingDirectory 등을 교체합니다.

`flet.service`

```
[Unit]
Description=Flet Server
After=network.target

[Service]
User=ubuntu
Group=ubuntu
WorkingDirectory=/home/ubuntu/flet-app
Environment="PATH=/home/ubuntu/flet-app/.venv/bin"
ExecStart=/home/ubuntu/flet-app/.venv/bin/python /home/ubuntu/flet-app/main.py

[Install]
WantedBy=multi-user.target
```

##### Flet 서버 활성화

``` bash
cd /etc/systemd/system
sudo ln -s /home/ubuntu/flet-app/flet.service
sudo systemctl start flet
sudo systemctl enable flet
sudo systemctl status flet
```



#### NGINX Proxy Setup

NGINX는 Flet 앱과 websocket을 프록시합니다.

/etc/nginx/sites-available/* 구성 파일에서 필요에 따라 경로와 포트를 업데이트합니다.

``` bash
    location / {
        proxy_pass         http://127.0.0.1:8502/;
        proxy_http_version 1.1;
        proxy_set_header   Upgrade $http_upgrade;
        proxy_set_header   Connection keep-alive;
        proxy_set_header   Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header   X-Forwarded-Proto $scheme;
    }
    
    location /ws {
        proxy_pass         http://127.0.0.1:8502/ws;
        proxy_http_version 1.1;
        proxy_set_header   Upgrade $http_upgrade;
        proxy_set_header   Connection keep-alive;
        proxy_set_header   Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header   X-Forwarded-Proto $scheme;
    }
```

그게 다야! NGINX를 다시 시작하고 브라우저에서 앱을 엽니다.



## Tutorials

출처: https://flet.dev/docs/tutorials



### Flet을 사용하여 Python에서 To-Do 앱 만들기

이 자습서에서는 Flet 프레임워크를 사용하여 Python에서 ToDo 웹 앱을 만든 다음 인터넷에서 공유하는 방법을 단계별로 보여줍니다. 이 앱은 Python 코드의 180줄(포맷!)로 구성된 단일 파일 콘솔 프로그램이지만 풍부하고 응답성이 뛰어난 UI를 갖춘 다중 세션, 최신 단일 페이지 애플리케이션입니다.

![img](.\Images\todo-complete-demo-web.gif)

[여기](https://flet-todo.fly.dev/)에서 라이브 데모를 볼 수 있습니다.

튜토리얼을 위해 ToDo 앱을 선택했습니다. 페이지 레이아웃 구축, 컨트롤 추가, 이벤트 처리, 목록 표시 및 편집, 재사용 가능한 UI 구성 요소 만들기, 배포 옵션 등 웹 앱을 만드는 데 필요한 모든 기본 개념을 다루기 때문입니다. .

자습서는 다음 단계로 구성됩니다.

- Flet 시작하기
- 페이지 컨트롤 추가 및 이벤트 처리
- 목록 항목 보기, 편집 및 삭제
- 목록 항목 필터링
- 마무리
- 앱 배포



#### Flet 시작하기

Flet 웹 앱을 작성하려면 HTML, CSS 또는 JavaScript를 알 필요는 없지만 Python 및 객체 지향 프로그래밍에 대한 기본 지식은 필요합니다.

Flet에는 Python 3.7 이상이 필요합니다. Flet을 사용하여 Python에서 웹 앱을 만들려면 먼저 flet 모듈을 설치해야 합니다.

``` bash
pip install flet
```

시작하려면 간단한 hello-world 앱을 만들어 보겠습니다.

다음 내용으로 hello.py를 만듭니다.

``` py
import flet as ft

def main(page: ft.Page):
    page.add(ft.Text(value="Hello, world!"))

ft.app(target=main)
```

이 앱을 실행하면 인사말이 포함된 새 창이 표시됩니다.

![img](.\Images\todo-app-hello-world.png)



#### 페이지 컨트롤 추가 및 이벤트 처리

이제 다중 사용자 ToDo 앱을 만들 준비가 되었습니다.

시작하려면 작업 이름을 입력하기 위한 TextField와 새 작업이 있는 확인란을 표시할 이벤트 처리기가 있는 '+' FloatingActionButton이 필요합니다.

다음 내용으로 todo.py를 생성합니다.

``` py
import flet as ft

def main(page: ft.Page):
    def add_clicked(e):
        page.add(ft.Checkbox(label=new_task.value))
        new_task.value = ""
        page.update()

    new_task = ft.TextField(hint_text="Whats needs to be done?")

    page.add(new_task, ft.FloatingActionButton(icon=ft.icons.ADD, on_click=add_clicked))

ft.app(target=main)
```

앱을 실행하면 다음과 같은 페이지가 표시됩니다.

![img](.\Images\todo-app-1.png)



#### Page layout

이제 앱을 보기 좋게 만들어 봅시다! 우리는 전체 앱이 600px 너비의 페이지 상단 중앙에 있기를 원합니다. TextField 및 '+' 버튼은 가로로 정렬되어야 하며 전체 앱 너비를 차지해야 합니다.

![img](.\Images\todo-diagram-1.svg)

[`Row`](https://flet.dev/docs/controls/row)  는 자식 컨트롤을 페이지에 가로로 배치하는 데 사용되는 컨트롤입니다.  [`Column`](https://flet.dev/docs/controls/column)은 자식 컨트롤을 페이지에 세로로 배치하는 데 사용되는 컨트롤입니다.

todo.py 내용을 다음으로 바꿉니다.

``` py
import flet as ft


def main(page: ft.Page):
    def add_clicked(e):
        tasks_view.controls.append(ft.Checkbox(label=new_task.value))
        new_task.value = ""
        view.update()

    new_task = ft.TextField(hint_text="Whats needs to be done?", expand=True)
    tasks_view = ft.Column()
    view=ft.Column(
        width=600,
        controls=[
            ft.Row(
                controls=[
                    new_task,
                    ft.FloatingActionButton(icon=ft.icons.ADD, on_click=add_clicked),
                ],
            ),
            tasks_view,
        ],
    )

    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.add(view)

ft.app(target=main)
```

앱을 실행하면 다음과 같은 페이지가 표시됩니다.

![img](.\Images\todo-app-2.png)

**Check Box 는 Whats needs to be done? 에 문자열 입력 후 + 버튼을 눌렀을 때 생성 된다.**



#### 재사용 가능한 UI 구성 요소

기본 기능에서 앱을 계속 작성할 수 있지만 가장 좋은 방법은 재사용 가능한 UI 구성 요소를 만드는 것입니다. 더 큰 프로젝트의 일부가 될 앱 헤더, 사이드 메뉴 또는 UI에서 작업하고 있다고 상상해 보십시오. 지금은 이러한 용도를 생각할 수 없더라도 구성 가능성과 재사용 가능성을 염두에 두고 모든 웹 앱을 만드는 것이 좋습니다.

재사용 가능한 ToDo 앱 구성 요소를 만들기 위해 상태 및 표시 논리를 별도의 클래스에 캡슐화합니다.

``` py
import flet as ft

class TodoApp(ft.UserControl):
    def build(self):
        self.new_task = ft.TextField(hint_text="Whats needs to be done?", expand=True)
        self.tasks = ft.Column()

        # application's root control (i.e. "view") containing all other controls
        return ft.Column(
            width=600,
            controls=[
                ft.Row(
                    controls=[
                        self.new_task,
                        ft.FloatingActionButton(icon=ft.icons.ADD, on_click=self.add_clicked),
                    ],
                ),
                self.tasks,
            ],
        )

    def add_clicked(self, e):
        self.tasks.controls.append(ft.Checkbox(label=self.new_task.value))
        self.new_task.value = ""
        self.update()


def main(page: ft.Page):
    page.title = "ToDo App"
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.update()

    # create application instance
    todo = TodoApp()

    # add application's root control to the page
    page.add(todo)

ft.app(target=main)
```

> **NOTE**
>
> 페이지에 두 개의 TodoApp 구성 요소를 추가해 보세요.
>
> ``` py
> # create application instance
> app1 = TodoApp()
> app2 = TodoApp()
> 
> # add application's root control to the page
> page.add(app1, app2)
> ```



#### 목록 항목 보기, 편집 및 삭제

이전 단계에서는 확인란으로 표시된 작업 항목이 있는 기본 ToDo 앱을 만들었습니다. 작업 이름 옆에 '편집' 및 '삭제' 버튼을 추가하여 앱을 개선해 보겠습니다. '편집' 버튼은 작업 항목을 편집 모드로 전환합니다.



![img](.\Images\todo-diagram-2.svg)



이 단계 전에는 코드가 튜토리얼에 완전히 포함될 만큼 짧았습니다. 앞으로는 단계에서 도입된 변경 사항만 강조 표시할 것입니다.

[여기](https://github.com/flet-dev/examples/blob/main/python/tutorials/todo/to-do-4.py)에서 이 단계의 전체 코드를 복사합니다. 아래에서 보기, 편집 및 삭제 작업을 구현하기 위해 수행한 변경 사항에 대해 설명합니다.

작업 항목 보기 및 작업을 캡슐화하기 위해 새 작업 클래스를 도입했습니다.

``` py
import flet
from flet import (
    Checkbox,
    Column,
    FloatingActionButton,
    IconButton,
    Page,
    Row,
    TextField,
    UserControl,
    colors,
    icons,
)


class Task(UserControl):
    def __init__(self, task_name, task_delete):
        super().__init__()
        self.task_name = task_name
        self.task_delete = task_delete

    def build(self):
        self.display_task = Checkbox(value=False, label=self.task_name)
        self.edit_name = TextField(expand=1)

        self.display_view = Row(
            alignment="spaceBetween",
            vertical_alignment="center",
            controls=[
                self.display_task,
                Row(
                    spacing=0,
                    controls=[
                        IconButton(
                            icon=icons.CREATE_OUTLINED,
                            tooltip="Edit To-Do",
                            on_click=self.edit_clicked,
                        ),
                        IconButton(
                            icons.DELETE_OUTLINE,
                            tooltip="Delete To-Do",
                            on_click=self.delete_clicked,
                        ),
                    ],
                ),
            ],
        )

        self.edit_view = Row(
            visible=False,
            alignment="spaceBetween",
            vertical_alignment="center",
            controls=[
                self.edit_name,
                IconButton(
                    icon=icons.DONE_OUTLINE_OUTLINED,
                    icon_color=colors.GREEN,
                    tooltip="Update To-Do",
                    on_click=self.save_clicked,
                ),
            ],
        )
        return Column(controls=[self.display_view, self.edit_view])

    def edit_clicked(self, e):
        self.edit_name.value = self.display_task.label
        self.display_view.visible = False
        self.edit_view.visible = True
        self.update()

    def save_clicked(self, e):
        self.display_task.label = self.edit_name.value
        self.display_view.visible = True
        self.edit_view.visible = False
        self.update()

    def delete_clicked(self, e):
        self.task_delete(self)


class TodoApp(UserControl):
    def build(self):
        self.new_task = TextField(hint_text="Whats needs to be done?", expand=True)
        self.tasks = Column()

        # application's root control (i.e. "view") containing all other controls
        return Column(
            width=600,
            controls=[
                Row(
                    controls=[
                        self.new_task,
                        FloatingActionButton(icon=icons.ADD, on_click=self.add_clicked),
                    ],
                ),
                self.tasks,
            ],
        )

    def add_clicked(self, e):
        task = Task(self.new_task.value, self.task_delete)
        self.tasks.controls.append(task)
        self.new_task.value = ""
        self.update()

    def task_delete(self, task):
        self.tasks.controls.remove(task)
        self.update()


def main(page: Page):
    page.title = "ToDo App"
    page.horizontal_alignment = "center"
    page.update()

    # create application instance
    app = TodoApp()

    # add application's root control to the page
    page.add(app)


flet.app(target=main)
```

작업 항목 보기 및 작업을 캡슐화하기 위해 새 작업 클래스를 도입했습니다.

``` py
class Task(ft.UserControl):
    def __init__(self, task_name):
        super().__init__()
        self.task_name = task_name

    def build(self):
        self.display_task = ft.Checkbox(value=False, label=self.task_name)
        self.edit_name = ft.TextField(expand=1)

        self.display_view = ft.Row(
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
            vertical_alignment=ft.CrossAxisAlignment.CENTER,
            controls=[
                self.display_task,
                ft.Row(
                    spacing=0,
                    controls=[
                        ft.IconButton(
                            icon=ft.icons.CREATE_OUTLINED,
                            tooltip="Edit To-Do",
                            on_click=self.edit_clicked,
                        ),
                        ft.IconButton(
                            ft.icons.DELETE_OUTLINE,
                            tooltip="Delete To-Do",
                            on_click=self.delete_clicked,
                        ),
                    ],
                ),
            ],
        )

        self.edit_view = ft.Row(
            visible=False,
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
            vertical_alignment=ft.CrossAxisAlignment.CENTER,
            controls=[
                self.edit_name,
                ft.IconButton(
                    icon=ft.icons.DONE_OUTLINE_OUTLINED,
                    icon_color=ft.colors.GREEN,
                    tooltip="Update To-Do",
                    on_click=self.save_clicked,
                ),
            ],
        )
        return ft.Column(controls=[self.display_view, self.edit_view])

    def edit_clicked(self, e):
        self.edit_name.value = self.display_task.label
        self.display_view.visible = False
        self.edit_view.visible = True
        self.update()

    def save_clicked(self, e):
        self.display_task.label = self.edit_name.value
        self.display_view.visible = True
        self.edit_view.visible = False
        self.update()
```

또한 '추가' 버튼을 클릭할 때 작업 인스턴스를 만들고 유지하도록 TodoApp 클래스를 변경했습니다.

``` py
class TodoApp(ft.UserControl):
    def build(self):
        self.new_task = ft.TextField(hint_text="Whats needs to be done?", expand=True)
        self.tasks = ft.Column()
        # ...

    def add_clicked(self, e):
        task = Task(self.new_task.value, self.task_delete)
        self.tasks.controls.append(task)
        self.new_task.value = ""
        self.update()
```

'삭제' 작업 작업을 위해 작업 제어 인스턴스를 매개 변수로 허용하는 TodoApp 클래스의 task_delete() 메서드를 구현했습니다.

``` py
class TodoApp(ft.UserControl):
    # ...
    def task_delete(self, task):
        self.tasks.controls.remove(task)
        self.update()
```

그런 다음 task_delete 메서드에 대한 참조를 Task 생성자에 전달하고 'Delete' 버튼 이벤트 핸들러에서 호출했습니다.

``` py
class Task(ft.UserControl):
    def __init__(self, task_name, task_delete):
        super().__init__()
        self.task_name = task_name
        self.task_delete = task_delete

        # ...        

    def delete_clicked(self, e):
        self.task_delete(self)
```

앱을 실행하고 작업 편집 및 삭제를 시도합니다.

![img](.\Images\view-edit-delete.gif)



#### 목록 항목 필터링

작업을 생성, 편집 및 삭제할 수 있는 기능적인 ToDo 앱이 이미 있습니다. 생산성을 더욱 높이기 위해 상태별로 작업을 필터링할 수 있기를 원합니다.

여기에서 이 단계의 전체 코드를 복사합니다. 아래에서 필터링을 구현하기 위해 수행한 변경 사항에 대해 설명합니다.

탭 컨트롤은 필터를 표시하는 데 사용됩니다.

``` py

# ...

class TodoApp(ft.UserControl):
    def __init__(self):
        self.tasks = []
        self.new_task = ft.TextField(hint_text="Whats needs to be done?", expand=True)
        self.tasks = ft.Column()

        self.filter = ft.Tabs(
            selected_index=0,
            on_change=self.tabs_changed,
            tabs=[ft.Tab(text="all"), ft.Tab(text="active"), ft.Tab(text="completed")],
        )

        self.view = ft.Column(
            width=600,
            controls=[
                ft.Row(
                    controls=[
                        self.new_task,
                        ft.FloatingActionButton(icon=ft.icons.ADD, on_click=self.add_clicked),
                    ],
                ),
                ft.Column(
                    spacing=25,
                    controls=[
                        self.filter,
                        self.tasks,
                    ],
                ),
            ],
        )
```

상태에 따라 다른 작업 목록을 표시하기 위해 '모두', '활성' 및 '완료' 작업이 포함된 세 개의 목록을 유지할 수 있습니다. 그러나 우리는 동일한 목록을 유지하고 상태에 따라 작업의 가시성만 변경하는 더 쉬운 접근 방식을 선택했습니다.

TodoApp 클래스에서 모든 작업을 반복하고 작업 상태에 따라 표시 속성을 업데이트하는 update() 메서드를 재정의했습니다.

``` py
class TodoApp(ft.UserControl):

    # ...

    def update(self):
        status = self.filter.tabs[self.filter.selected_index].text
        for task in self.tasks.controls:
            task.visible = (
                status == "all"
                or (status == "active" and task.completed == False)
                or (status == "completed" and task.completed)
            )
        super().update()
```

필터링은 탭을 클릭하거나 작업 상태를 변경할 때 발생해야 합니다. TodoApp.update() 메서드는 탭에서 선택한 값이 변경되거나 작업 항목 확인란을 클릭할 때 호출됩니다.

``` py
class TodoApp(ft.UserControl):

    # ...

    def tabs_changed(self, e):
        self.update()

class Task(ft.UserControl):
    def __init__(self, task_name, task_status_change, task_delete):
        super().__init__()
        self.completed = False
        self.task_name = task_name
        self.task_status_change = task_status_change
        self.task_delete = task_delete

    def build(self):
        self.display_task = ft.Checkbox(
            value=False, label=self.task_name, on_change=self.status_changed
        )
        # ...

    def status_changed(self, e):
        self.completed = self.display_task.value
        self.task_status_change(self)
```

앱을 실행하고 탭을 클릭하여 작업 필터링을 시도하십시오.

![img](.\Images\todo-app-filtering.gif)



#### 마무리 (Final touches)

이제 Todo 앱이 거의 완성되었습니다. 마지막 터치로 완료되지 않은 작업 수를 표시하는 바닥글(열 컨트롤)(텍스트 컨트롤)과 'Clear completed' 버튼을 추가합니다.

[여기](https://github.com/flet-dev/examples/blob/main/python/apps/todo/todo.py)에서 이 단계의 전체 코드를 복사합니다. 아래에서 바닥글을 구현하기 위해 수행한 변경 사항을 강조 표시했습니다.

``` py
class TodoApp():
    def __init__(self):
        # ...

        self.items_left = ft.Text("0 items left")

        self.view = ft.Column(
            width=600,
            controls=[
                ft.Row([ ft.Text(value="Todos", style="headlineMedium")], alignment=ft.MainAxisAlignment.CENTER),
                ft.Row(
                    controls=[
                        self.new_task,
                        ft.FloatingActionButton(icon=ft.icons.ADD, on_click=self.add_clicked),
                    ],
                ),
                ft.Column(
                    spacing=25,
                    controls=[
                        self.filter,
                        self.tasks,
                        ft.Row(
                            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
                            vertical_alignment=ft.CrossAxisAlignment.CENTER,
                            controls=[
                                self.items_left,
                                ft.OutlinedButton(
                                    text="Clear completed", on_click=self.clear_clicked
                                ),
                            ],
                        ),
                    ],
                ),
            ],
        )

    # ...

    def clear_clicked(self, e):
        for task in self.tasks.controls[:]:
            if task.completed:
                self.task_delete(task)

    def update(self):
        status = self.filter.tabs[self.filter.selected_index].text
        count = 0
        for task in self.tasks.controls:
            task.visible = (
                status == "all"
                or (status == "active" and task.completed == False)
                or (status == "completed" and task.completed)
            )
            if not task.completed:
                count += 1
        self.items_left.value = f"{count} active item(s) left"
        super().update()
```

앱 실행:

![img](.\Images\todo-app-4.png)



#### 앱 배포

축하합니다! Flet을 사용하여 첫 번째 Python 앱을 만들었습니다. 멋져 보입니다!

이제 여러분의 앱을 세상과 공유할 시간입니다!

Flet 앱을 웹 앱으로 Fly.io 또는 Replit에 배포하려면 [다음 지침](https://flet.dev/docs/guides/python/deploying-web-app)을 따르십시오.



#### Summary

이 자습서에서는 다음 방법을 배웠습니다.

- 간단한 Flet 앱을 만듭니다.
- 재사용 가능한 UI 구성 요소로 작업합니다.
- 열 및 행 컨트롤을 사용하여 UI 레이아웃을 디자인합니다.
- 목록 작업: 항목 보기, 편집 및 삭제, 필터링
- Flet 앱을 웹에 배포합니다.



추가 정보를 보려면 컨트롤 및 예제 리포지토리를 탐색할 수 있습니다.

여러분의 피드백을 듣고 싶습니다! [이메일](hello@flet.dev)을 보내거나 [Discord](https://discord.gg/dzWXP8SHG8)에서 토론에 참여하거나 [Twitter](https://twitter.com/fletdev)에서 팔로우하세요.



---

### Flet을 사용하여 Python에서 계산기 앱 만들기

출처: https://flet.dev/docs/tutorials/python-calculator

이 자습서에서는 Flet 프레임워크를 사용하여 Python에서 계산기 앱을 만들고 웹 앱으로 배포하는 방법을 단계별로 보여줍니다. 이 앱은 간단한 콘솔 프로그램이지만 iPhone 계산기 앱 UI와 유사한 다중 플랫폼 애플리케이션입니다.

![img](.\Images\calc-app.gif)

[여기](https://flet-calc.fly.dev/)에서 라이브 데모를 찾을 수 있습니다.

이 자습서에서는 페이지 레이아웃 빌드, 컨트롤 추가, 재사용 가능한 UI 구성 요소 만들기, 이벤트 처리 및 배포 옵션과 같은 웹 앱 만들기의 모든 기본 개념을 다룹니다.

자습서는 다음 단계로 구성됩니다.

- Flet 시작하기
- 페이지 컨트롤 추가
- 건물 페이지 레이아웃
- 재사용 가능한 UI 구성 요소
- 이벤트 처리
- 데스크톱 앱으로 패키징
- 웹 앱 배포
- 요약



#### Flet 시작하기

