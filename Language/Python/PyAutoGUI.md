# PyAutoGUI

## PyAutoGUI 소개

PyAutoGUI는 **마우스/키보드 자동 제어를 위한 크로스 플랫폼 (cross-platform) 파이썬 모듈**입니다. ([PyAutoGUI 공식 문서](https://pyautogui.readthedocs.io/en/latest/index.html))

[PyWin32](https://codetorial.net/pywin32/index.html)는 Windows 전용이기 때문에 다양한 운영체제에서 자유롭게 마우스와 키보드의 동작을 컨트롤하기 위해서는 PyAutoGUI 모듈을 사용해야 합니다.

Windows와 macOS, Linux와 같은 주요 운영체제는 프로그래밍을 이용해서 마우스와 키보드를 제어하는 각자의 방식이 있습니다.

이는 복잡하고 이해하기 어렵기 때문에 여러 플랫폼에서 단순하게 동작하는 PyAutoGUI API가 만들어졌습니다.

◦ Windows에서 PyAutoGUI는 `Win32 API` 를 사용합니다.

◦ macOS에서 PyAutoGUI는 Cocoa API에 접근하기 위해 `pyobjc` 모듈을 사용합니다.

◦ Linux에서 PyAutoGUI는 X11 또는 X Window System에 접근하기 위해 `Xlib` 모듈을 사용합니다.

Python과 PyAutoGUI를 이용해서 마우스, 키보드를 자동 제어하고 업무 자동화 등에 활용할 수 있습니다.

## PyAutoGUI 설치

우선 명령 프롬프트에서 pip 또는 conda를 통해서 설치를 진행합니다.

```
pip install pyautogui
conda install -c conda-forge pyautogui
```

이제 PyAutoGUI의 다양한 기능에 대해 알아봅니다.

순서는 아래와 같습니다.

Contents

- [PyAutoGUI - 파이썬 마우스/키보드 자동 조작 모듈](https://codetorial.net/pyautogui/index.html#)
- PyAutoGUI 마우스 사용하기
  - [마우스 커서의 현재 위치와 모니터 크기 얻기](https://codetorial.net/pyautogui/mouse_control.html#id1)
  - [마우스 이동과 클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id2)
  - [마우스 상대적 이동과 더블클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id3)
  - [마우스 드래그하기](https://codetorial.net/pyautogui/mouse_control.html#id4)
- PyAutoGUI 키보드 입력하기
  - [문자 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id1)
  - [단축키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id3)
  - [개별 키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id4)
- PyAutoGUI 스크린샷 찍기
  - [pyautogui.screenshot()](https://codetorial.net/pyautogui/screenshot.html#pyautogui-screenshot)
- PyAutoGUI 이미지로 마우스 이동
  - [1. 이미지 영역 찾기](https://codetorial.net/pyautogui/locate_by_image.html#id1)
  - [2. 이미지 영역의 가운데 위치 얻기](https://codetorial.net/pyautogui/locate_by_image.html#id2)
  - [3. 클릭하기](https://codetorial.net/pyautogui/locate_by_image.html#id3)
- PyAutoGUI 메세지 박스 표시하기
  - [pyautogui.alert()](https://codetorial.net/pyautogui/message_box.html#pyautogui-alert)
  - [pyautogui.confirm()](https://codetorial.net/pyautogui/message_box.html#pyautogui-confirm)
  - [pyautogui.prompt()](https://codetorial.net/pyautogui/message_box.html#pyautogui-prompt)
  - [pyautogui.password()](https://codetorial.net/pyautogui/message_box.html#pyautogui-password)
- PyAutoGUI Fail-Safes(안전장치)
  - [pyautogui.FAILSAFE](https://codetorial.net/pyautogui/fail_safes.html#pyautogui-failsafe)
  - [pyautogui.PAUSE](https://codetorial.net/pyautogui/fail_safes.html#pyautogui-pause)
- PyAutoGUI Reference
  - [일반적 함수](https://codetorial.net/pyautogui/reference.html#id1)
  - [마우스 제어](https://codetorial.net/pyautogui/reference.html#id2)
  - [키보드 입력](https://codetorial.net/pyautogui/reference.html#id3)
  - [스크린샷](https://codetorial.net/pyautogui/reference.html#id4)
  - [이미지 영역](https://codetorial.net/pyautogui/reference.html#id5)



### 다음글

다음글 : [PyAutoGUI 마우스 사용하기](https://codetorial.net/pyautogui/mouse_control.html)



# PyAutoGUI 마우스 사용하기

PyAutoGUI를 이용해서 마우스를 컨트롤하는 방법을 소개합니다.

- [마우스 커서의 현재 위치와 모니터 크기 얻기](https://codetorial.net/pyautogui/mouse_control.html#id1)
- [마우스 이동과 클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id2)
- [마우스 상대적 이동과 더블클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id3)
- [마우스 드래그하기](https://codetorial.net/pyautogui/mouse_control.html#id4)

## [마우스 커서의 현재 위치와 모니터 크기 얻기](https://codetorial.net/pyautogui/mouse_control.html#id6)

### position(), size()

```
import pyautogui

print(pyautogui.position())
print(pyautogui.size())
```

`position()` 함수는 마우스 커서의 현재 위치를 (x, y) 튜플의 형태로 반환합니다.

`size()` 함수는 현재 메인 모니터의 크기를 (width, height) 튜플의 형태로 반환합니다.

## [마우스 이동과 클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id7)

### moveTo(), click()

```
import pyautogui

pyautogui.moveTo(200, 200)
pyautogui.click()
```

`moveTo()` 를 이용해서 마우스 커서를 x=200, y=200의 위치로 이동하고, `click()` 을 이용해서 마우스를 한 번 클릭합니다.

아래 그림과 같이 (x, y) 위치는 화면 왼쪽 위가 (0, 0)이고 오른쪽으로 아래로 갈수록 x와 y가 증가합니다.

[![_images/location.png](.\Images\location.png)](https://codetorial.net/pyautogui/_images/location.png)

스크린 상의 x, y 위치

### click(x, y)

```
import pyautogui

pyautogui.click(200, 200)
```

`moveTo()` 와 `click()` 을 한 번에 실행하려면 `click()` 에 x, y 위치를 넣어주면 됩니다.

### button 설정하기

```
import pyautogui

pyautogui.click(200, 200, button='right')
```

클릭할 마우스 버튼을 선택하려면 `button` 키워드를 사용합니다. ‘left’, ‘right’, ‘middle’을 설정할 수 있습니다.

### interval 설정하기

```
import pyautogui

pyautogui.click(200, 200, clicks=2, interval=0.5)
```

`clicks` 키워드는 클릭 횟수를, `interval` 키워드는 클릭 사이의 시간 간격을 설정해줍니다.



## [마우스 상대적 이동과 더블클릭하기](https://codetorial.net/pyautogui/mouse_control.html#id8)

### moveRel(), doubleClick()

```
import pyautogui

pyautogui.moveRel(0, 10)
pyautogui.doubleClick()
```

`moveRel()` 을 이용해서 커서를 현재 위치에서 x=0, y=10 만큼 이동하고, `doubleClick()` 을 이용해서 마우스를 더블클릭합니다.

## [마우스 드래그하기](https://codetorial.net/pyautogui/mouse_control.html#id9)

### dragTo(), dragRel()

```
import pyautogui

pyautogui.dragTo(300, 300, button='left')
pyautogui.dragTo(400, 400, 2, button='left')
pyautogui.dragRel(30, 0, 2, button='right')
```

`dragTo()` 를 사용해서, x=300, y=300의 위치로 마우스 왼쪽 버튼을 클릭한 채로 드래그합니다.

세 번째 인수의 값을 넣어주면 드래그 시간을 설정하도록 합니다. 현재 커서 위치에서 x=400, y=400의 위치로 2초 동안 왼쪽 버튼을 누른 채로 드래그합니다.

`dragRel()` 을 이용하면 상대적 이동이 가능합니다. 현재 위치에서 x=30, y=0 만큼, 2초 동안 마우스 오른쪽 버튼을 누른 채로 드래그합니다.

`button` 키워드는 ‘left’, ‘right’, ‘middle’로 설정할 수 있습니다.



### 이전글/다음글

이전글 : [PyAutoGUI - 파이썬 마우스/키보드 자동 조작 모듈](https://codetorial.net/pyautogui/index.html)

다음글 : [PyAutoGUI 키보드 입력하기](https://codetorial.net/pyautogui/keyboard_control.html)



#  PyAutoGUI 키보드 입력하기

PyAutoGUI 이용해서 키보드를 컨트롤하는 방법을 소개합니다.

- [문자 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id1)
- [단축키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id3)
- [개별 키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id4)

## [문자 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id5)

### typewrite()

```
import pyautogui

pyautogui.click(500, 500)
pyautogui.typewrite('Hello world!', interval=0.1)
```

커서를 x=500, y=500 위치로 이동해서, 마우스를 한 번 클릭하고, `typewrite()` 함수를 이용해 ‘Hello world!’를 타이핑합니다.

이 때 `interval` 은 키보드 입력 사이의 시간 간격을 설정합니다.

### 리스트 입력하기

```
import pyautogui

pyautogui.click(500, 500)
pyautogui.typewrite(['a', 'b', 'c'], interval=0.1)
```

키의 이름을 리스트의 형태로 입력해줘도 입력이 가능합니다.

## [단축키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id6)

### hotkey()

```
import pyautogui

pyautogui.hotkey('ctrl', 'c')  # ctrl-c to copy
pyautogui.hotkey('ctrl', 'v')  # ctrl-v to paste
```

‘ctrl+c’ 또는 ‘ctrl+v’와 같은 단축키(hotkey)도 `hotkey()` 를 이용해서 입력할 수 있습니다.

## [개별 키 입력하기](https://codetorial.net/pyautogui/keyboard_control.html#id7)

### keyDown(), keyUp()

```
import pyautogui

pyautogui.keyDown('c')
pyautogui.keyUp('c')
```

개별 키를 누르고 떼는 동작은 `keyDown()` 과 `keyUp()` 을 이용해서 수행할 수 있습니다.

### press()

```
import pyautogui

pyautogui.press('c')
```

`press()` 는 `keyDown()` 과 `keyUp()` 동작을 함께 수행합니다.

### KEYBOARD_KEYS

```
import pyautogui

print(pyautogui.KEYBOARD_KEYS)
['\t', '\n', '\r', ' ', '!', '"', '#', '$', '%', '&', "'", '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', 'accept', 'add', 'alt', 'altleft', 'altright', 'apps', 'backspace', 'browserback', 'browserfavorites', 'browserforward', 'browserhome', 'browserrefresh', 'browsersearch', 'browserstop', 'capslock', 'clear', 'convert', 'ctrl', 'ctrlleft', 'ctrlright', 'decimal', 'del', 'delete', 'divide', 'down', 'end', 'enter', 'esc', 'escape', 'execute', 'f1', 'f10', 'f11', 'f12', 'f13', 'f14', 'f15', 'f16', 'f17', 'f18', 'f19', 'f2', 'f20', 'f21', 'f22', 'f23', 'f24', 'f3', 'f4', 'f5', 'f6', 'f7', 'f8', 'f9', 'final', 'fn', 'hanguel', 'hangul', 'hanja', 'help', 'home', 'insert', 'junja', 'kana', 'kanji', 'launchapp1', 'launchapp2', 'launchmail', 'launchmediaselect', 'left', 'modechange', 'multiply', 'nexttrack', 'nonconvert', 'num0', 'num1', 'num2', 'num3', 'num4', 'num5', 'num6', 'num7', 'num8', 'num9', 'numlock', 'pagedown', 'pageup', 'pause', 'pgdn', 'pgup', 'playpause', 'prevtrack', 'print', 'printscreen', 'prntscrn', 'prtsc', 'prtscr', 'return', 'right', 'scrolllock', 'select', 'separator', 'shift', 'shiftleft', 'shiftright', 'sleep', 'space', 'stop', 'subtract', 'tab', 'up', 'volumedown', 'volumemute', 'volumeup', 'win', 'winleft', 'winright', 'yen', 'command', 'option', 'optionleft', 'optionright']
```

PyAutuGUI 모듈의 모든 키 리스트는 `pyautogui.KEYBOARD_KEYS` 를 출력하면 확인할 수 있습니다.



### 이전글/다음글

이전글 : [PyAutoGUI 마우스 사용하기](https://codetorial.net/pyautogui/mouse_control.html)

다음글 : [PyAutoGUI 스크린샷 찍기](https://codetorial.net/pyautogui/screenshot.html)



# PyAutoGUI 스크린샷 찍기

PyAutoGUI를 이용해서 간단하게 스크린샷을 찍고, 이미지로 저장할 수 있습니다.

## pyautogui.screenshot()

```
import pyautogui

im1 = pyautogui.screenshot()
im2 = pyautogui.screenshot('my_screenshot.png')
im3 = pyautogui.screenshot('my_region.png', region=(0, 0, 300, 300))
```

`screenshot()` 을 호출하면 화면 스크린샷 이미지 객체 (im1)를 반환합니다.

파일 이름 (‘my_screenshot.png’)을 입력해주면 스크린샷 이미지를 파일로 저장합니다.

region=(0, 0, 300, 300)과 같이 영역을 설정해주면 x=0, y=0에서 x=300, y=300 위치까지의 사각형 영역을 이미지 파일로 저장합니다.



### 이전글/다음글

이전글 : [PyAutoGUI 키보드 입력하기](https://codetorial.net/pyautogui/keyboard_control.html)

다음글 : [PyAutoGUI 이미지로 마우스 이동](https://codetorial.net/pyautogui/locate_by_image.html)



# PyAutoGUI 이미지로 마우스 이동

프로그램이 항상 조금씩 다른 위치에서 실행되기 때문에 화면 상의 특정한 위치에 마우스를 위치시키기가 까다로울 수 있습니다.

이런 상황에서 화면 상의 특정 영역의 이미지 파일을 갖고 있으면 그 영역을 찾아서 클릭할 수 있습니다.

아래의 예제를 통해 계산기가 어디에 있든 항상 똑같은 버튼을 클릭하도록 해보겠습니다.

순서는 아래와 같습니다.

- [1. 이미지 영역 찾기](https://codetorial.net/pyautogui/locate_by_image.html#id1)
- [2. 이미지 영역의 가운데 위치 얻기](https://codetorial.net/pyautogui/locate_by_image.html#id2)
- [3. 클릭하기](https://codetorial.net/pyautogui/locate_by_image.html#id3)

![_images/9_2_calculator.png](https://codetorial.net/pyautogui/_images/9_2_calculator.png)

우선 계산기의 특정 영역을 캡처해서 해당 폴더에 아래와 같이 ‘five.PNG’ 파일로 저장합니다.

![_images/five.PNG](https://codetorial.net/pyautogui/_images/five.PNG)

five.PNG

## [1. 이미지 영역 찾기](https://codetorial.net/pyautogui/locate_by_image.html#id5)

### locateOnScreen()

```
import pyautogui

five_btn = pyautogui.locateOnScreen('five.PNG')
print(five_btn)
(1443, 652, 38, 47)
```

`locateOnScreen()` 에 그림(‘five.PNG’)를 설정해주면, 아래와 같이 화면 상에서 일치하는 영역을 찾아서 왼쪽 위의 위치와 영역의 가로, 세로 크기를 튜플의 형태((left, top, width, height))로 출력합니다.

해당 영역을 찾지 못하면 `None` 을 반환합니다.

## [2. 이미지 영역의 가운데 위치 얻기](https://codetorial.net/pyautogui/locate_by_image.html#id6)

### center()

```
import pyautogui

five_btn = pyautogui.locateOnScreen('five.PNG')
center = pyautogui.center(five_btn)
print(center)
```

`center()` 함수를 사용하면, 해당 영역의 가운데 위치(클릭할 지점)를 튜플의 형태로 얻을 수 있습니다.

center를 출력하면 (1462, 675)가 출력됩니다.

## [3. 클릭하기](https://codetorial.net/pyautogui/locate_by_image.html#id7)

### click()

```
import pyautogui

center = pyautogui.locateCenterOnScreen('five.PNG')
pyautogui.click(center)
```

이미지 영역을 찾고 가운데 지점을 찾는 더 쉬운 방법은 `locateCenterOnScreen()` 함수를 사용하는 것입니다.

아래와 같이 `click(center)` 를 통해 계산기의 5 버튼의 가운데 위치가 클릭됩니다.

![_images/9_2_calculator_2.png](https://codetorial.net/pyautogui/_images/9_2_calculator_2.png)

화면 상에서 계산기의 위치가 바뀌어도 항상 똑같은 위치를 클릭할 수 있습니다.

1920×1080 화면 기준으로, 클릭에 1~2초 정도가 걸리기 때문에 게임에 적용하기는 어려울 수 있지만 대부분의 어플리케이션에서 사용될 수 있습니다.



### 이전글/다음글

이전글 : [PyAutoGUI 스크린샷 찍기](https://codetorial.net/pyautogui/screenshot.html)

다음글 : [PyAutoGUI 메세지 박스 표시하기](https://codetorial.net/pyautogui/message_box.html)



# PyAutoGUI 메세지 박스 표시하기

PyAutoGUI를 사용해서 메세지 박스를 표시하는 여러가지 함수를 소개합니다.

순서는 아래와 같습니다.

- [pyautogui.alert()](https://codetorial.net/pyautogui/message_box.html#pyautogui-alert)
- [pyautogui.confirm()](https://codetorial.net/pyautogui/message_box.html#pyautogui-confirm)
- [pyautogui.prompt()](https://codetorial.net/pyautogui/message_box.html#pyautogui-prompt)
- [pyautogui.password()](https://codetorial.net/pyautogui/message_box.html#pyautogui-password)

## [pyautogui.alert()](https://codetorial.net/pyautogui/message_box.html#id13)

### 예제1

```
import pyautogui

pyautogui.alert('Alert!')
```

pyautogui.alert() 함수는 알림을 위한 메세지 박스를 하나 띄웁니다.

아래와 같은 창이 뜹니다.

[![_images/pyautogui_alert_01.png](https://codetorial.net/pyautogui/_images/pyautogui_alert_01.png)](https://codetorial.net/pyautogui/_images/pyautogui_alert_01.png)

그림. pyautogui.alert() 사용하기.

### 예제2

```
import pyautogui

alert = pyautogui.alert('Alert!')
print(alert)
OK
```

pyautogui.alert()는 OK 문자열을 반환합니다.

## [pyautogui.confirm()](https://codetorial.net/pyautogui/message_box.html#id14)

### 예제1

```
import pyautogui

pyautogui.confirm('Shall I proceed?')
```

pyautogui.confirm()은 확인을 위한 메세지 박스를 표시합니다.

아래와 같은 창이 표시됩니다.

[![_images/pyautogui_confirm_01.png](https://codetorial.net/pyautogui/_images/pyautogui_confirm_01.png)](https://codetorial.net/pyautogui/_images/pyautogui_confirm_01.png)

그림. pyautogui.confirm() 사용하기.

### 예제2

```
import pyautogui

confirm = pyautogui.confirm('Shall I proceed?')
print(confirm)
Cancel
```

pyautogui.confirm()은 클릭한 버튼에 따라 OK 또는 Cancel 문자열을 반환합니다.

### 예제3

```
import pyautogui

a = pyautogui.confirm('Enter option', buttons=['A', 'B', 'C'])

if a == 'A':
    print('You selected A')
elif a == 'B':
    print('You selected B')
else:
    print('You selected C')
You selected C
```

buttons를 이용해서 버튼의 개수와 표시될 문자열을 지정할 수 있습니다.

선택한 버튼의 문자열을 반환하기 때문에, 조건문을 이용해서 코드를 실행할 수 있습니다.

아래와 같은 창이 표시됩니다.

[![_images/pyautogui_confirm_02.png](https://codetorial.net/pyautogui/_images/pyautogui_confirm_02.png)](https://codetorial.net/pyautogui/_images/pyautogui_confirm_02.png)

그림. pyautogui.confirm() 사용자 정의 버튼 사용하기.



## [pyautogui.prompt()](https://codetorial.net/pyautogui/message_box.html#id15)

### 예제1

```
import pyautogui

name = pyautogui.prompt('What is your name?')
print(name)
John
```

pyautogui.prompt()는 사용자의 입력을 받을 수 있는 프롬프트 창을 표시합니다.

입력한 값을 반환하고, 입력값이 없으면 None을 반환합니다.

아래와 같은 창이 표시됩니다.

[![_images/pyautogui_prompt_01.png](https://codetorial.net/pyautogui/_images/pyautogui_prompt_01.png)](https://codetorial.net/pyautogui/_images/pyautogui_prompt_01.png)

그림. pyautogui.prompt() 사용하기.

## [pyautogui.password()](https://codetorial.net/pyautogui/message_box.html#id16)

### 예제1

```
import pyautogui

pw = pyautogui.password('Enter password (hidden mode)')
print(pw)
1234
```

비밀번호를 입력 받는 경우와 같이 문자열이 표시되지 않게 하려면 pyautogui.password()를 사용할 수 있습니다.

아래 그림과 같이 문자열이 asterisk (*) 문자로 표시되고, 입력한 값을 반환합니다.

[![_images/pyautogui_password_01.png](https://codetorial.net/pyautogui/_images/pyautogui_password_01.png)](https://codetorial.net/pyautogui/_images/pyautogui_password_01.png)

그림. pyautogui.password() 사용하기.



### 이전글/다음글

이전글 : [PyAutoGUI 이미지로 마우스 이동](https://codetorial.net/pyautogui/locate_by_image.html)

다음글 : [PyAutoGUI Fail-Safes(안전장치)](https://codetorial.net/pyautogui/fail_safes.html)



# PyAutoGUI Fail-Safes(안전장치)

## pyautogui.FAILSAFE

코드가 잘못 프로그램된 경우, 마우스와 키보드를 제어하기 힘든 상황이 생길 수 있습니다. 이런 상황을 대비해서 안전장치가 있습니다.

만약 마우스 커서가 화면의 왼쪽 위 모서리(x=0, y=0)로 이동하면, 안전장치가 가동되어 프로그램을 종료합니다.

이러한 안전장치를 사용하고 싶지 않다면, 아래와 같이 설정해줍니다.

### 예제

```
import pyautogui

pyautogui.FAILSAFE = False
```

## pyautogui.PAUSE

모든 PyAutoGUI의 함수들은 `pyautogui.PAUSE` 의 값을 설정함으로써 딜레이를 가질 수 있습니다.

### 예제

```
import pyautogui

pyautogui.PAUSE = 2.5
pyautogui.moveTo(200, 200)
pyautogui.click()
```

`moveTo()` 함수 뒤에 2.5초, `click()` 함수 뒤에 2.5초의 딜레이를 갖습니다.

`pyautogui.PAUSE` 의 디폴트 값은 0.1초입니다.



### 이전글/다음글

이전글 : [PyAutoGUI 메세지 박스 표시하기](https://codetorial.net/pyautogui/message_box.html)

다음글 : [PyAutoGUI Reference](https://codetorial.net/pyautogui/reference.html)



# PyAutoGUI Reference

## 일반적 함수

| 함수                 | 설명                                                |
| :------------------- | :-------------------------------------------------- |
| pyautogui.position() | 마우스 커서의 현재 위치를 튜플의 형태로 반환합니다. |
| pyautogui.size()     | 현재 메인 모니터의 크기를 튜플의 형태로 반환합니다. |

## 마우스 제어

| 함수                                   | 설명                                                         |
| :------------------------------------- | :----------------------------------------------------------- |
| pyautogui.moveTo(x, y)                 | 마우스 커서를 입력한 위치로 이동합니다.                      |
| pyautogui.moveRel(x, y)                | 마우스 커서를 현재 위치에서 입력한 위치만큼 이동합니다.      |
| pyautogui.dragTo(x, y)                 | 마우스를 클릭한 채로 입력한 위치로 이동합니다.               |
| pyautogui.dragTo(x, y, button=’right’) | 마우스 오른쪽 버튼을 클릭한 채로 커서를 입력한 위치로 이동합니다. (‘left’, ‘right’, ‘middle’) |
| pyautogui.dragRel(x, y)                | 마우스를 클릭한 채로 현재 위치에서 입력한 위치로 이동합니다. |
| pyautogui.click()                      | 마우스를 한 번 클릭합니다.                                   |
| pyautogui.click(x, y)                  | 마우스 커서를 입력한 위치로 이동해서 한 번클릭합니다.        |
| pyautogui.doubleClick()                | 마우스를 더블클릭합니다.                                     |

## 키보드 입력

| 함수                  | 설명                                             |
| :-------------------- | :----------------------------------------------- |
| pyautogui.typewrite() | 입력한 문자열을 타이핑합니다.                    |
| pyautogui.hotkey()    | 입력한 단축키를 타이핑합니다.                    |
| pyautogui.keyDown()   | 입력한 키를 누릅니다.                            |
| pyautogui.keyUp()     | 입력한 키를 뗍니다.                              |
| pyautogui.press()     | 입력한 키를 눌렀다 뗍니다. (keyDown() + keyUp()) |

## 스크린샷

| 함수                                 | 설명                                               |
| :----------------------------------- | :------------------------------------------------- |
| pyautogui.screenshot()               | 스크린샷 이미지를 반환합니다.                      |
| pyautogui.screenshot(‘filename.png’) | 스크린샷 이미지를 입력한 파일 이름으로 저장합니다. |

## 이미지 영역

| 함수                             | 설명                                                         |
| :------------------------------- | :----------------------------------------------------------- |
| pyautogui.locateOnScreen()       | 입력한 이미지에 해당하는 영역의 (left, top, width, height) 값을 반환합니다. |
| pyautogui.locateAllOnScreen()    | 입력한 이미지에 해당하는 모든 영역의 (left, top, width, height) 값을 반환합니다. |
| pyautogui.locateCenterOnScreen() | 입력한 이미지에 해당하는 영역의 가운데 위치를 반환합니다.    |



### - 끝 -

