# 초보자를 위한 Python GUI 프로그래밍 - PyQt5

출처: https://wikidocs.net/book/2944

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)

이 책은 Qt Designer와 PyQt5를 이용하여 보다 쉽게 Python GUI 프로그래밍을 배워보는 책입니다. Python의 기본 문법을 익힌 상태에서 이 책을 공부하시는 것을 추천드립니다. 이 책에 있는 모든 코드들은 위의 링크에서 다운로드 받으실 수 있습니다.

본 책은 아래의 환경에서 작성되었습니다.

> Mac OS Mojave(언어 - 영어) - Macbook Pro 13' late 2016(Four Thunderbolt 3 Ports)

본 책의 예제코드는 아래의 환경에서 테스트되었습니다.

> - Windows 10(언어 - 한글) - i7-7700K, RAM 16G, GTX1060
> - Mac OS Mojave(언어 - 영어) - Macbook Pro 13' late 2016(Four Thunderbolt 3 Ports)



이 책은 초보자를 위해 간단한 Qt의 기능만을 소개하고 있습니다.
위젯들의 자세한 메뉴얼과 함수는 아래 링크에 있는 Qt의 공식 홈페이지를 참조해주시기 바랍니다.

> [Qt for Python Documentation](https://doc.qt.io/qtforpython/index.html)
> [Qt Designer Manual](https://doc.qt.io/qt-5/qtdesigner-manual.html)



# 01. PyQt의 시작

이번장에서는 PyQt와 Qt Designer에 대해서 소개하도록 하겠습니다.

[01.01 PyQt란 무엇인가?](https://wikidocs.net/35478)
[01.02 Qt Designer의 설치](https://wikidocs.net/35479)
[01.03 Qt Designer의 화면구성](https://wikidocs.net/35481)
[01.04 Qt Designer를 이용한 UI의 제작과 연결](https://wikidocs.net/35482)



# 01.01 PyQt란 무엇인가?

- [PyQt란?](https://wikidocs.net/35478#pyqt)
- [PyQt의 특징](https://wikidocs.net/35478#pyqt_1)

## PyQt란?

PyQt란, Qt의 레이아웃에 Python의 코드를 연결하여 GUI 프로그램을 만들 수 있게 해주는 프레임워크를 의미합니다. PyQt는 C++의 Cross Platform GUI Framework인 Qt를 영국의 Riverbank Computing에서 Python 모듈로 변환해주는 툴을 만들어주며 시작되었습니다. 현재는 PyQt4버전과 PyQt5버전이 주로 사용되고 있습니다.

## PyQt의 특징

Python에도 PyGTK, PySide, Tkinter등 다양한 GUI Framework가 존재합니다. 하지만 이런 GUI Framework들은 사용하기도 어렵고, 시각적으로 예쁘지 않다는 단점이 있습니다.

PyQt는 이러한 Framework들과 다르게 시각적으로도 괜찮은 디자인을 보여주면서 Qt Designer라는 프로그램을 이용하여 프로그램을 손쉽게 설계할 수 있다는 장점이 있습니다. 이러한 이유로 이 책에서는 PyQt를 이용하여 GUI 프로그램을 만들어보도록 하겠습니다.



# 01.02 Qt Designer의 설치와 실행

## Qt Designer의 설치

이번페이지에서는 Qt Designer를 설치하는 방법에 대해서 알아보도록 하겠습니다.

![01.02.01 QtDeisngerPic - Mac](https://wikidocs.net/images/page/35479/01.02.01_Qt_Designer.png) **그림 01.02.01 Qt Designer화면 - Mac버전**
Qt Designer란, PyQt를 이용하여 GUI프로그래밍을 할 때 손쉽게 프로그램의 레이아웃을 편집할 수 있도록 해주는 편집기입니다. Qt Designer를 설치하는데는 여러방법이 있지만, 이 책에서는 가장 쉬운 방법인 Anaconda를 이용하는 방법에 대해서 알아보도록 하겠습니다.

**그림 01.02.02 Anaconda 홈페이지**
[Anaconda Distribution](https://www.anaconda.com/distribution/)
위의 링크를 클릭하면 위의 사진과 같은 홈페이지가 나옵니다. 이 홈페이지에서 자신의 운영체제에 맞는 Anaconda를 다운로드 받으신 후, 설치를 진행해주세요. Python version은 3.x버전으로 다운받으시기 바랍니다.

Anaconda 설치를 마치셨다면, 각 운영체제별로 Qt Designer를 실행시키는 방법에 대해서 알아보도록 하겠습니다.

## Qt Designer의 실행

### Windows 버전

![01.02.03 QtDesigner - Window](https://wikidocs.net/images/page/35479/01.02.03_QtDesigner_-_Window.PNG) **그림 01.02.03 Anaconda Prompt - Windows**
Windows에서는 시작메뉴에서 Anaconda Prompt를 검색하여 실행시켜줍니다. 그 후, Anaconda Prompt에서 designer라는 명령어를 입력하면 Qt Designer가 실행됩니다.

### Mac 버전

![01.02.04 QtDesigner - Mac](https://wikidocs.net/images/page/35479/01.02.04_QtDesigner-Mac.png) **그림 01.02.04 Qt Designer 위치 - Mac**
Mac에서는 Application 폴더 안에서 Anaconda Navigator를 찾은 후, Anaconda Navigator를 오른쪽 마우스 클릭하고 Show Original을 누릅니다. Anaconda Navigator 내부로 들어갔다면, bin폴더에 있는 Designer라는 파일을 실행하면 Qt Designer가 실행됩니다.

매번 이렇게 Anaconda 폴더 내부로 들어가는 것이 귀찮다면, 해당 파일을 Application 폴더로 복사/붙여넣기 하시면 launchpad에서 Qt Designer를 실행시킬 수 있습니다.



# 01.03 Qt Designer의 화면구성

- [Qt Designer의 시작화면](https://wikidocs.net/35481#qt-designer)
- Qt Designer의 화면구성
  - [Dialog Window](https://wikidocs.net/35481#dialog-window)
  - [Widget Box](https://wikidocs.net/35481#widget-box)
  - [Object Inspector](https://wikidocs.net/35481#object-inspector)
  - [Property Editor](https://wikidocs.net/35481#property-editor)

이번 페이지에서는 Qt Designer의 화면구성에 대해서 알아보도록 하겠습니다.

## Qt Designer의 시작화면

![01.03.01 Qt Designer Launch](https://wikidocs.net/images/page/35481/01.03.01_Qt_Designer_Launch.png)
**그림 01.03.01 Qt Designer의 시작화면**
Qt Designer를 실행시키면 위의 사진과 같은 화면이 나타날 것입니다. 여기서 Dialog without Buttons를 누른 후 Create를 눌러 원하는 아무것도 없는 빈 화면을 만들어주면 됩니다.

## Qt Designer의 화면구성

![01.03.02 Qt Designer Main Window](https://wikidocs.net/images/page/35481/01.03.02_Qt_Designer_Main_Window.png)
**그림 01.03.02 Qt Designer 화면(Mac 버전)**
아무것도 없는 빈 화면을 만들었다면, 이런 여러개의 화면이 여러분을 반겨줄 것입니다. 이 화면들 중, 여러분이 가장 많이 사용할 몇개의 화면에 대해서 알아보도록 하겠습니다.

### Dialog Window

![01.03.03 Dialog Window](https://wikidocs.net/images/page/35481/01.03.03_Dialog.png)
**그림 01.03.03 Dialog Window**
Dialog Window란, 실제로 프로그램의 화면이 될 곳입니다. 이 화면에 위젯들을 배치하여 프로그램 화면을 구성하게 됩니다.

### Widget Box

![01.03.04 Widget Box](https://wikidocs.net/images/page/35481/01.03.04_Widget_Box.png)
**그림 01.03.04 Widget Box**
Widget Box란, 위젯(프로그램에서 기능을 하는 버튼 등의 객체)들을 모아놓은 화면입니다. 이곳에서 원하는 위젯을 Dialog Window로 드래그하여 위젯을 추가할 수 있습니다.

### Object Inspector

![01.03.05 Object Inspector](https://wikidocs.net/images/page/35481/01.03.05_Object_Inspector_.png)
**그림 01.03.05 Object Inspector**
Object Inspector란, 위젯들의 종속관계를 조절하는 곳입니다. 이곳에서 위젯을 Container 안에 넣는 등의 작업을 할 수 있습니다. Container에 관련된 이야기는 추후에 다루도록 하겠습니다.

### Property Editor

![01.03.06 Property Editor](https://wikidocs.net/images/page/35481/01.03.06_Property_Editor.png)
**그림 01.03.06 Property Editor**
Property Editor란, 위젯들의 속성을 조절하는 화면입니다. 이 화면에 대한 자세한 설명은 다음장인 위젯에서 하도록 하겠습니다.



# 01.04 Qt Designer를 이용한 UI의 제작과 연결

- [Qt Designer를 이용한 ui의 제작](https://wikidocs.net/35482#qt-designer-ui)
- UI의 저장과 Python코드와의 연결
  - [UI의 저장](https://wikidocs.net/35482#ui)
  - [UI와 Python 코드의 연결](https://wikidocs.net/35482#ui-python_1)

## Qt Designer를 이용한 ui의 제작

![01.04.01 Layout with Qt Designer](https://wikidocs.net/images/page/35482/01.04.01_Layout_with_Qt_Designer.png)
**그림 01.04.02 Qt Designer를 이용한 UI의 제작**
Qt Designer의 화면에 대해서 알아보았으니, 이제 실제로 프로그램의 ui를 제작하는 방법에 대해서 알아보도록 하겠습니다. Qt Designer를 이용하여 프로그램의 ui를 제작할 때는 Widget Box에 있는 각종 위젯들을 Dialog화면으로 드래그한 후 배치합니다. 이 위젯들의 속성은 Property Editor에서 조절할 수 있는데, 위젯들의 속성은 위젯부분에서 다루도록 하겠습니다.

만든 화면이 실제로 프로그램이 되었을 때, 어떻게 작동될 것인지 미리보기를 보고싶다면 Control(Command) + R을 눌러서 미리보기를 볼 수도 있습니다.

## UI의 저장과 Python코드와의 연결

위젯들을 배치하여 UI들을 만들었다면, 이 UI를 저장하고, Python 코드와 연결시키는 방법에 대해서 알아보도록 하겠습니다.

### UI의 저장

Qt Designer의 UI는 저장버튼(Control + S / Command + S)를 누르면 저장을 할 수 있습니다. 저장된 UI파일은 XML의 형식을 가지며, Python 코드에서 이 XML 파일을 Import한 후 위젯들에 기능을 할당해주면 실제로 기능을 가지고 작동하는 GUI프로그램이 되는 것입니다. 직접 XML형식의 ui파일을 수정하여 레이아웃을 수정할 수도 있습니다.

![01.04.02 Save UI](https://wikidocs.net/images/page/35482/01.04.02_Save_ui.png)
**그림 01.04.02 UI의 저장위치**
UI 파일을 저장할 때 주의할 점은 Python코드에서 import하기 쉽도록 Python 코드와 같은 디렉토리에 위치시키는 것이 좋습니다. UI파일의 이름은 원하는 이름으로 아무렇게나 지정해도 됩니다.

### UI와 Python 코드의 연결

UI파일과 Python코드를 연결시키는데는 UI파일을 통채로 Python코드로 변환시킨 후 그 파일에 코드를 작성하는 방법, UI파일을 Python 코드에서 import하는 방법 이렇게 두가지가 있습니다.

UI파일을 통채로 Python코드로 변환시킨 후 코드를 작성하는 방법은 터미널을 이용해야하는 불편함과, UI를 수정했을 때 코드를 수정하기 어렵고 최악의 경우에는 코드 전체를 다시 작성해야 하는 부작용이 있을 수 있어 이 책에서는 UI파일을 Python에 Import하여 사용하는 방법만을 소개하도록 하겠습니다.

UI파일과 동일한 위치에 Python파일을 하나 만든 후, 아래의 코드를 그대로 복사/붙여넣기 합니다. 이때 중간에 있는 "UI파일이름.ui"는 자신이 가지고 있는 UI의 이름으로 수정해주셔야 합니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("UI파일이름.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv) 

    #WindowClass의 인스턴스 생성
    myWindow = WindowClass() 

    #프로그램 화면을 보여주는 코드
    myWindow.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
```

위의 코드를 실행시켰다면 성공적으로 Qt Designer를 이용하여 제작한 UI가 표시되는 것을 볼 수 있습니다. 이제 다음장에서 이 위젯들에 기능을 만들어주는 방법에 대해서 공부해보도록 하겠습니다.



# 02. 위젯과 레이아웃

이번장에서는 위젯과 레이아웃에 대해서 알아보도록 하겠습니다.

> **02. 위젯과 레이아웃의 하위페이지**
> [02.01 들어가기에 앞서(필독)](https://wikidocs.net/35484)
> [02.02 Button - QPushButton](https://wikidocs.net/35485)
> [02.03 Button - QRadioButton](https://wikidocs.net/35486)
> [02.04 Button - QCheckBox](https://wikidocs.net/35488)
> [02.05 Display - QLabel](https://wikidocs.net/35489)
> [02.06 Display - QTextBrowser](https://wikidocs.net/35490)
> [02.07 Input - QLineEdit](https://wikidocs.net/35491)
> [02.08 Input - QTextEdit, QPlainTextEdit](https://wikidocs.net/35492)
> [02.09 Input - QComboBox](https://wikidocs.net/35493)
> [02.10 Input - QSpinBox, QDoubleSpinBox](https://wikidocs.net/35494)
> [02.11 Input - QSlider, QDial](https://wikidocs.net/35495)
> [02.12 Input - QDateTimeEdit](https://wikidocs.net/36796)
> [02.13 Input - QDateEdit, QTimeEdit](https://wikidocs.net/37787)
> [02.14 Display - QCalendarWidget](https://wikidocs.net/38036)
> [02.15 Display - QPixmap](https://wikidocs.net/38038)
> [02.16 Display - QWebEngineView](https://wikidocs.net/37788)
> [02.17 Display - QProgressBar](https://wikidocs.net/38037)
>
> **아래는 "02. 위젯과 레이아웃"에서 위젯과 관련된 다른 클래스들을 소개하는 부분입니다.**
> [02.08.01 QFont](https://wikidocs.net/37456)
> [02.08.02 QColor](https://wikidocs.net/37457)
> [02.12.01 QDateTime](https://wikidocs.net/37458)
> [02.12.02 QDate](https://wikidocs.net/37459)
> [02.12.03 QTime](https://wikidocs.net/37460)



# 02.01 들어가기에 앞서(필독)

- [위젯의 글자변경](https://wikidocs.net/35484#_1)
- Property Editor의 속성
  - [ObjectName](https://wikidocs.net/35484#objectname)
  - [Gemometry](https://wikidocs.net/35484#gemometry)
  - [Palette](https://wikidocs.net/35484#palette)
  - [Font](https://wikidocs.net/35484#font)
  - [ToolTip](https://wikidocs.net/35484#tooltip)
  - [ToolTipDuration](https://wikidocs.net/35484#tooltipduration)
- 시그널과 함수
  - [시그널](https://wikidocs.net/35484#_3)
  - [함수](https://wikidocs.net/35484#_4)
- [Python코드를 이용한 위젯의 동작](https://wikidocs.net/35484#python)
- [Import 오류시 대처방법](https://wikidocs.net/35484#import)

이번 페이지에서는 위젯을 다루다보면 대부분의 위젯에서 공통적으로 사용하는 기능 몇가지에 대해서 다루도록 하겠습니다.

## 위젯의 글자변경

![02.01.01 Change Text](https://wikidocs.net/images/page/35484/02.01.01_Change_Text.png)
**그림 02.01.01 위젯의 글자 변경**

UI를 만들다보면, 위젯의 글자를 변경해야 하는 경우가 꽤 많이 생깁니다. 위젯에 적힌 글자는 위젯의 종류에 관계없이 위젯을 더블클릭하여 변경할 수 있습니다.

## Property Editor의 속성

![02.01.02 Property Editor](https://wikidocs.net/images/page/35484/02.01.02_Property_Editor.png)
**그림 02.01.02 Property Editor**
Property Editor에 표시되는 요소들은 위젯에 따라 약간씩 차이가 있지만, 공통적으로 표시되는 요소들도 일부 존재합니다. 공통적으로 표시되는 요소들 중 많이, 그리고 자주 사용하는 속성들에 대해서 알아보겠습니다.

### ObjectName

Python코드에서 사용될 위젯의 이름을 결정하는 역할을 합니다. 기억하기 쉬운 이름으로 정하는 것을 권장드리며, 서로 다른 위젯이 같은 ObjectName을 가질 수는 없습니다.

### Gemometry

위젯의 X,Y좌표(위치)와 가로길이(Width), 세로길이(Height)를 지정합니다. 마우스로 위젯을 끌어당겨서 조절할 수도 있지만, Property Editor에서 숫자로 조정해줄 수도 있습니다.

### Palette

위젯의 색상을 지정하는 Palette를 지정합니다. 기본값은 Inherited(시스템의 기본속성)을 사용합니다.

### Font

위젯에 글자가 있을 경우, 그 글자의 폰트와 크기를 지정합니다.

### ToolTip

ToolTip이란, 위젯 위에 마우스를 올려놓았을 때, 이 위젯에 대한 힌트를 제공해주는 글자를 의미합니다. ToolTip을 설정하고 싶지 않은 경우 공백으로 설정하면 되며, ToolTip을 설정하고 싶다면 원하는 글자를 입력하면 됩니다.

### ToolTipDuration

toolTip이 얼마나 오래 보여질지를 정합니다. 단위는 ms(millisecond)로, 1초동안 보이게 하려면 1000을 입력합니다. 기본값은 -1로, toolTip이 마우스가 올려져 있는 동안 항상 보여지게 합니다.

## 시그널과 함수

PyQt에서 위젯과 관련된 코드는 크게 시그널과 함수 이렇게 두가지로 분류됩니다. 시그널과 함수가 무엇인지 알아보도록 하겠습니다.

### 시그널

시그널이란 위젯의 상태가 바뀌었을 때, 특정 행동을 하게 하는 코드를 의미합니다. 버튼을 눌렸을 때 어떤 행동을 하게 하거나, 다이얼을 돌렸을 때 화면에 다른 값이 표시되게 하는 행동들은 모두 시그널을 이용합니다.

시그널은 위젯마다 다르며, 하나의 위젯에 여러개의 시그널이 존재할 수 있습니다. 시그널은 반드시 화면을 표시하는 Class의 생성자부분에 입력되어야 하는데, 생성자부분은 바로 뒤에 나오는 [위젯과 Python 코드의 연결](https://wikidocs.net/35484#python)부분에서 이야기 하도록 하겠습니다.

시그널의 코드는 뒷부분에서 위젯들을 하나씩 소개할 때 구체적으로 소개하도록 하겠습니다.

### 함수

함수는 시그널과 다르게 위젯에 아무런 변화가 없더라도 실행될 수 있는 코드를 의미합니다. 함수는 보통 위젯의 값을 설정하거나, 위젯의 값을 가져오거나, 위젯의 속성을 변화시킬 때 사용됩니다. PyQt에서는 대부분의 위젯들에서 공통적으로 사용되는 함수들이 몇가지 있는데, 이 함수들에 대해서 간단하게 알아보도록 하겠습니다.

> **주의사항
> 모든 함수의 앞에는 self.ObjectName이 들어가야 합니다.
> 이때 ObjectName이란, Property Editor에서 지정한 위젯의 ObjectName을 의미합니다.**

| Method                | 설명                                                         |
| :-------------------- | :----------------------------------------------------------- |
| .move(x,y)            | 위젯의 위치를 지정합니다. Parameter에는 이동할 위치의 x,y좌표가 들어갑니다. |
| .resize(width,height) | 위젯의 크기를 지정합니다. Parameter에는 위젯의 가로,세로 크기가 들어갑니다. |
| .text()               | 위젯에 쓰여있는 글자를 가져옵니다.                           |
| .setText(String)      | 위젯에 새롭게 글자를 작성합니다. Parameter에는 표시할 글자가 들어갑니다. |

## Python코드를 이용한 위젯의 동작

[01.04 Qt Designer를 이용한 UI 제작과 연결](https://wikidocs.net/35482)부분에서 어떻게 Qt Designer를 이용하여 디자인한 UI파일과 Python코드를 연결하는지에 대해서 알아봤었습니다. 그때 언급한 코드만을 이용하면 화면이 보여지기만 하고 위젯들은 아무런 기능도 하지 않을 것입니다.

위젯이 변화했을때 뭔가 기능을 하게 하려면 어떻게 해야할까요?

위젯을 동작시키게 하기 위해서는 시그널을 사용해야 합니다. 이때 시그널은 반드시 클래스의 생성자부분에 사용해야 합니다. 앞으로 2장-위젯 부분에서 "클래스의 생성자부분"이라는 단어는 모두 아래 코드의 "이 부분에 시그널을 입력해야합니다." 부분을 의미합니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("UI파일이름.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        """
        ---------------------------------------------
        이 부분에 시그널을 입력해야 합니다.
        시그널이 작동할 때 실행될 기능은 보통 이 클래스의 멤버함수로 작성합니다.
        ---------------------------------------------
        """

if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()
```

## Import 오류시 대처방법

[Visual Studio Code에서의 Import 오류시 대처방법](https://github.com/Microsoft/vscode-python/issues/2089)
이 책에서 소개하는 방법은 위의 링크를 참조했음을 알려드립니다.

본 책에서는 Python 코드의 에디터로 Visual Studio Code를 이용합니다. Visual Studio Code를 이용하여 PyQt를 이용하다 보면 간혹 PyQt의 모듈이 Import 되지 않는 것을 볼 수 있습니다. 이런 에러가 발생할 때의 해결방법을 안내해드리도록 하겠습니다.

![02.01.03 VSCode Preferences](https://wikidocs.net/images/page/35484/02.01.03_VSCode_Preferences.jpg)
**사진 02.01.03 Visual Studio Code 환경설정 진입**

우선 Visual Studio Code를 열고, 환경설정에 들어갑니다. Window버전의 경우 File > Preferences > Settings로, Mac 버전의 경우 상단바에서 Code > Preferences > Settings를 클릭하면 환경설정에 들어가실 수 있습니다.

![02.01.04 Workspace Setting](https://wikidocs.net/images/page/35484/02.01.04_Workspace_Setting.png)
**사진 02.01.04 Visual Studio Code Workspace Setting화면**

환경설정창에 들어간 후, Workspace Settings를 클릭하고 검색창에 python.linting.pylintArgs를 검색합니다. python.linting.pylintArgs를 검색하면 위와 같은 화면이 나올텐데요, 가장 위에 있는 Python > Linting: Pylint Args라는 항목 아래에 있는 Edit in settings.json버튼을 눌러줍니다.

Edit in settings.json버튼을 누르면 settings.json이라는 {}만 있는 빈 파일이 나올겁니다. 이 중괄호 사이에 다음의 코드를 입력해줍니다.

```
"python.linting.pylintArgs": [
        "--extension-pkg-whitelist=PyQt5"
    ]
```

그리고 settings.json 파일을 저장하면 Visual Studio Code가 성공적으로 PyQt의 모듈들을 인식할 것입니다.



# 02.02 Button - QPushButton

- [QPushButton이란?](https://wikidocs.net/35485#qpushbutton)
- QPushButton의 시그널과 활용
  - [QPushButton의 시그널](https://wikidocs.net/35485#qpushbutton_2)
  - [시그널의 활용](https://wikidocs.net/35485#_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QPushButton Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QPushButton.html?highlight=qpushbutton)

이번 페이지의 예제코드는 위의 링크에 있는 02.02 PushButton 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QPushButton의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 찾으실 수 있습니다.

## QPushButton이란?

![02.02.01 QPushButton](https://wikidocs.net/images/page/35485/02.02.01_QPushButton.png)
**사진 02.02.01 QPushButton**

QPushButton이란 화면에 버튼을 만들어주는 위젯을 의미합니다. Widget Window에서 PushButton을 클릭한 후, Dialog Window에 끌어다 놓으면 왼쪽과 같이 화면에 버튼을 추가할 수 있습니다. 버튼을 추가한 후 Property Editor에서 반드시 ObjectName을 본인이 기억하기 쉬운 이름으로 바꿔주시기 바랍니다.

## QPushButton의 시그널과 활용

### QPushButton의 시그널

QPushButton이 눌렸을 때, 어떤 기능을 하도록 코드를 작성해보도록 하겠습니다. QPushButton의 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> **self.버튼이름.clicked.connect(함수)**

### 시그널의 활용

![02.02.01 QPushButton](https://wikidocs.net/images/page/35485/02.02.01_QPushButton.png)
**사진 02.02.01 QPushButton**
위에서 언급한 시그널에 예시를 들어보도록 하겠습니다. 왼쪽 버튼의 ObjectName은 btn_1로, 오른쪽 버튼의 ObjectName은 btn_2로 정했습니다. 그리고 왼쪽 버튼이 클릭되면 "btn_1 Clicked", 오른쪽 버튼이 클릭되면 "btn_2 Clicked"가 출력되도록 만든다고 가정을 해보겠습니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("pushbuttonTest.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #버튼에 기능을 연결하는 코드
        self.btn_1.clicked.connect(self.button1Function)
        self.btn_2.clicked.connect(self.button2Function)

    #btn_1이 눌리면 작동할 함수
    def button1Function(self) :
        print("btn_1 Clicked")

    #btn_2가 눌리면 작동할 함수
    def button2Function(self) :
        print("btn_2 Clicked")


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()
```

위의 코드처럼 클래스의 생성자 안에서 버튼에 기능을 연결해주고, 기능을 클래스의 메소드로 작성해줍니다.
이 코드를 작동시켜보면 아래 사진과 같이 버튼을 눌렀을 때, "btn_1 Clicked", "btn_2 Clicked"가 성공적으로 보여지게 될 것입니다.

![02.02.02 QPushButton_Run](https://wikidocs.net/images/page/35485/02.02.02_QPushButton_Run.png)
**사진 02.02.02 QPushButton 실행**



# 02.03 Button - QRadioButton

- [QRadioButton이란?](https://wikidocs.net/35486#qradiobutton)
- QRadioButton의 시그널과 활용
  - [QRadioButton의 시그널](https://wikidocs.net/35486#qradiobutton_2)
  - [시그널의 활용](https://wikidocs.net/35486#_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QRadioButton Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QRadioButton.html?highlight=qradiobutton)

이번 페이지의 예제코드는 위의 링크에 있는 02.03 RadioButton 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QRadioButton의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 찾으실 수 있습니다.

## QRadioButton이란?

![02.03.01 RadioButton](https://wikidocs.net/images/page/35486/02.03.01_RadioButton.png)
**사진 02.03.01 QRadioButton**

RadioButton이란, 여러개의 선택지가 있을 때 단 한개만 선택할 수 있게 하는 버튼으로, 사진과 같이 GroupBox와 같은 Container 안에 RadioButton을 넣은 후 사용하는 경우가 많습니다. 서로 다른 Container안에 들어간 Radiobutton들은 서로에게 영향을 끼치지 않으며 독립적으로 작동합니다.

PushButton과 마찬가지로 Widget Window에서 끌어와 사용할 수 있으며, 여러개의 선택지 중 한가지를 선택하는 기능을 가진 만큼 반드시 헷갈리지 않게 Object Name을 잘 지어야 합니다.

## QRadioButton의 시그널과 활용

### QRadioButton의 시그널

QRadioButton이 눌렸을 때, 어떤 기능을 하도록 코드를 작성해보도록 하겠습니다. QRadioButton의 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> **self.Radio버튼이름.clicked.connect(함수)**

### 시그널의 활용

![02.03.01 RadioButton](https://wikidocs.net/images/page/35486/02.03.01_RadioButton.png)
**사진 02.03.01 QRadioButton**
위의 코드에 예시를 들어보도록 하겠습니다. Radio Button들을 클릭하면 GroupBox_rad1,2,3,4 is Checked와 같은 문구가 출력되도록 하겠습니다.

각 RadioButton들의 Object Name은 위에 있는 예제 파일을 다운받아서 확인하시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("radiobuttonTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #GroupBox안에 있는 RadioButton들을 연결합니다.
        #GroupBox의 자세한 설명은 02.14 GroupBox를 참고하세요.
        self.groupBox_rad1.clicked.connect(self.groupboxRadFunction)
        self.groupBox_rad2.clicked.connect(self.groupboxRadFunction)
        self.groupBox_rad3.clicked.connect(self.groupboxRadFunction)
        self.groupBox_rad4.clicked.connect(self.groupboxRadFunction)

    def groupboxRadFunction(self) :
        if self.groupBox_rad1.isChecked() : print("GroupBox_rad1 Chekced")
        elif self.groupBox_rad2.isChecked() : print("GroupBox_rad2 Checked")
        elif self.groupBox_rad3.isChecked() : print("GroupBox_rad3 Checked")
        elif self.groupBox_rad4.isChecked() : print("GroupBox_rad4 Checked")

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

위의 코드처럼 클래스의 생성자 안에서 버튼에 기능을 연결해주고, 기능을 클래스의 메서드로 작성해줍니다.
RadioButton의 경우에는 여러개 중 하나의 선택지를 선택하는 경우에 특정 기능을 수행하게 하는 것이므로, 모든 RadioButton에 같은 함수를 연결합니다. 그 후, 함수 안에서 isChecked()라는 메서드를 이용하여 어떤 RadioButton이 체크되었는지를 확인합니다.

이때 RadioButton은 여러개 중 하나만 선택되는 규칙이 있으므로, if ... elif문을 이용하여 단 하나의 코드만 작동되도록 합니다.

![02.03.02 RadioButton Run](https://wikidocs.net/images/page/35486/02.03.01_RadioButton_Run.png)
**사진 02.03.02 QRadioButton 실행**



# 02.04 Button - QCheckBox

- [QCheckBox란?](https://wikidocs.net/35488#qcheckbox)
- QCheckBox의 시그널과 활용
  - [QCheckBox의 시그널](https://wikidocs.net/35488#qcheckbox_2)
  - [시그널의 활용](https://wikidocs.net/35488#_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QCheckBox Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QCheckBox.html?highlight=qcheckbox)

이번 페이지의 예제코드는 위의 링크에 있는 02.04 CheckBox 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QCheckBox의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QCheckBox란?

![02.04.01 QCheckBox](https://wikidocs.net/images/page/35488/02.04.01_QCheckBox.png)
**사진 02.04.01 QCheckBox**
CheckBox란 RadioButton과 반대되는 개념으로 여러개의 선택지가 있을 때, 여러개를 선택할 수 있게 하는 버튼을 의미합니다. 모양은 왼쪽 사진과 같이 주로 네모모양의 버튼을 갖고 있습니다.

RadioButton과 마찬가지로 그냥 배치하여 사용하는 경우도 있지만, 사진의 오른쪽과 같이 GroupBox와 같은 Container에 CheckBox를 넣은 후 사용하는 경우가 많습니다.

Widget Window에서 PushButton을 클릭한 후, Dialog Window에 끌어다 놓으면 왼쪽과 같이 화면에 버튼을 추가할 수 있습니다. 버튼을 추가한 후 Property Editor에서 반드시 ObjectName을 본인이 기억하기 쉬운 이름으로 바꿔주시기 바랍니다.

## QCheckBox의 시그널과 활용

### QCheckBox의 시그널

QCheckBox이 눌렸을 때, 어떤 기능을 하도록 코드를 작성해보도록 하겠습니다. QCheckBox의 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> **self.체크박스이름.stateChanged.connect(함수)**

### 시그널의 활용

![02.04.01 QCheckBox](https://wikidocs.net/images/page/35488/02.04.01_QCheckBox.png)
**사진 02.04.01 QCheckBox**
위의 코드에 예시를 들어보도록 하겠습니다. CheckBox들의 ObjectName은 위에 올려놓은 예제코드를 참고하시기 바랍니다. 만약 CheckBox가 선택되면, 어떤 체크박스가 선택되었는지 출력하는 기능을 만든다고 가정해봅시다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("checkboxTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #GroupBox밖에 있는 CheckBox에 기능 연결
        self.chk_1.stateChanged.connect(self.chkFunction)
        self.chk_2.stateChanged.connect(self.chkFunction)
        self.chk_3.stateChanged.connect(self.chkFunction)
        self.chk_4.stateChanged.connect(self.chkFunction)

        #GroupBox안에 있는 CheckBox에 기능 연결
        self.groupchk_1.stateChanged.connect(self.groupchkFunction)
        self.groupchk_2.stateChanged.connect(self.groupchkFunction)
        self.groupchk_3.stateChanged.connect(self.groupchkFunction)
        self.groupchk_4.stateChanged.connect(self.groupchkFunction)

    def chkFunction(self) :
        #CheckBox는 여러개가 선택될 수 있기 때문에 elif를 사용하지 않습니다.
        if self.chk_1.isChecked() : print("chk_1 isChecked")
        if self.chk_2.isChecked() : print("chk_2 isChecked")
        if self.chk_3.isChecked() : print("chk_3 isChecked")
        if self.chk_4.isChecked() : print("chk_4 isChecked")

    def groupchkFunction(self) :
        if self.groupchk_1.isChecked() : print("groupchk_1 isChecked")
        if self.groupchk_2.isChecked() : print("groupchk_2 isChecked")
        if self.groupchk_3.isChecked() : print("groupchk_3 isChecked")
        if self.groupchk_4.isChecked() : print("groupchk_4 isChecked")

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

여러개의 버튼에 동일한 함수를 연결한다는 점은 RadioButton과 유사합니다. 하지만, 함수 내부를 살펴보면 if … elif문이 아니라 전부 if문을 사용하는 것을 볼 수 있습니다. CheckBox는 복수선택이 가능하기 때문에 버튼들이 체크되는 경우를 모두 if문을 활용하여 독립적으로 검사합니다. 그리고 RadioButton과 동일하게 .isChecked()메서드를 이용하여 CheckBox가 선택되었는지 체크합니다.

그리고 위의 코드를 실행시켜본다면 아래의 사진과 같은 결과를 얻을 수 있습니다. ![02.04.02 QCheckBox Run](https://wikidocs.net/images/page/35488/02.04.02_QCheckBoxRun.png)
**사진 02.02.02 QCheckBox의 실행**



# 02.05 Display - QLabel

- [QLabel이란?](https://wikidocs.net/35489#qlabel)
- [QLabel의 주요 함수](https://wikidocs.net/35489#qlabel_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QLabel Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QLabel.html?highlight=qlabel)

이번 페이지의 예제코드는 위의 링크에 있는 02.05 Label 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QLabel의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QLabel이란?

![02.05.01 Label](https://wikidocs.net/images/page/35489/02.05.01_Label.png)
**사진 02.05.01 QLabel**

Label이란, 화면에서 수정할 수 없는 한줄짜리 글자를 보여주는 위젯을 의미합니다. 만약 글자가 Label의 영역을 넘어간다면 글자가 잘리는 것이 특징입니다. Widget Window에서 Label을 클릭한 후, Dialog Window에 끌어다놓으면 됩니다. Dialog Window에 추가한 후, Property Editor에서 반드시 ObjectName을 본인이 기억하기 쉬운 이름으로 바꿔주시기 바랍니다.

## QLabel의 주요 함수

QLabel의 값을 바꾸고, QLabel에 값을 설정하는 함수들에 대해서 알아보도록 하겠습니다.

| Method           | 설명                                                         |
| :--------------- | :----------------------------------------------------------- |
| .text()          | Label에 쓰여있는 글자를 가져옵니다.                          |
| .setText(String) | Label에 새롭게 글자를 작성합니다. Parameter에는 Label에 표시할 글자가 입력됩니다. |
| .clear()         | Label에 쓰여있는 글자를 지웁니다.                            |



# 02.06 Display - QTextBrowser

- [QTextBrowser란?](https://wikidocs.net/35490#qtextbrowser)
- [QTextBrowser의 주요 함수](https://wikidocs.net/35490#qtextbrowser_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QTextBrowser Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QTextBrowser.html?highlight=qtextbrowser)

이번 페이지의 예제코드는 위의 링크에 있는 02.06 TextBrowser 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QTextBrowser의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QTextBrowser란?

![02.06.01 TextBrowser](https://wikidocs.net/images/page/35490/02.06.01_TextBrowser.png)
**사진 02.06.01 QTextBrowser**
Label이란, 화면에서 수정할 수 없는 한줄짜리 글자를 보여주는 위젯을 의미합니다. 만약 글자가 Label의 영역을 넘어간다면 글자가 잘리는 것이 특징입니다. Widget Window에서 Label을 클릭한 후, Dialog Window에 끌어다놓으면 됩니다. Dialog Window에 추가한 후, Property Editor에서 반드시 ObjectName을 본인이 기억하기 쉬운 이름으로 바꿔주시기 바랍니다.

## QTextBrowser의 주요 함수

QTextBrowser의 값을 바꾸고, QTextBrowser에 값을 설정하는 함수들에 대해서 알아보도록 하겠습니다.

> **주의사항**
> QTextBrowser에서는 text(), setText()가 작동하지 않습니다. 글자를 가져오고, 설정하는 기능을 사용하시려면 아래에 있는 toPlainText(), setPlainText()함수들을 사용해주시기 바랍니다.

| 함수                  | 설명                                                         |
| :-------------------- | :----------------------------------------------------------- |
| .toPlainText()        | TextBrowser에 쓰여있는 글자를 가져옵니다.                    |
| .setPlainText(String) | TextBrowser에 새롭게 글자를 작성합니다. Parameter에는 TextBrowser에 표시할 글자가 들어갑니다. |
| .append(String)       | TextBrowser에 글자를 추가합니다. 추가된 글자는 다음줄에 표시되며, Parameter에는 추가할 글자가 들어갑니다. |
| .clear()              | TextBrowser에 쓰여있는 글자를 지웁니다.                      |



# 02.07 Input - QLineEdit

- [QLineEdit이란?](https://wikidocs.net/35491#qlineedit)
- QLineEdit의 시그널과 활용예시
  - [QLineEdit의 시그널](https://wikidocs.net/35491#qlineedit_2)
  - [QLineEdit의 활용예시](https://wikidocs.net/35491#qlineedit_3)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QLineEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QLineEdit.html?highlight=qlineedit)

이번 페이지의 예제코드는 위의 링크에 있는 02.07 LineEdit 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QLineEdit의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QLineEdit이란?

![02.07.01 LineEdit](https://wikidocs.net/images/page/35491/02.07.01_LineEdit.png)
**사진 02.07.01 QLineEdit**

QLineEdit이란, 한줄짜리 글자를 입력받을 수 있는 입력위젯입니다. 위젯을 더블클릭하여 기본적으로 입력될 글자를 미리 입력해놓을 수도 있습니다. 다른 위젯들과 마찬가지로 Widget Window에서 끌어와 사용할 수 있습니다. LineEdit을 추가한 후 Property Editor에서 반드시 ObjectName을 본인이 기억하기 쉬운 이름으로 바꿔주시기 바랍니다.

## QLineEdit의 시그널과 활용예시

### QLineEdit의 시그널

QLineEdit의 시그널에 대해서 알아보도록 하겠습니다. QLineEdit에서는 LineEdit의 글자가 바뀔 때 마다 기능을 수행하는 시그널과 Return키(Enter키)가 눌렸을 때 기능을 수행하는 시그널이 있습니다.

> LineEdit의 글자가 바뀔 때 기능 실행
> **self.LineEdit이름.textChanged.connect(함수)**
>
> LineEdit에서 Return키(Enter키)가 눌렸을 때 기능 실행
> **self.LineEdit이름.returnPressed.connect(함수)**

이 시그널들은 타 시그널들과 마찬가지로 생성자 부분 안에 작성하여야 합니다. 또한, 동일한 LineEdit이 textChanged와 returnPressed 시그널을 동시에 사용할 수 있습니다.

### QLineEdit의 활용예시

![02.07.01 LineEdit](https://wikidocs.net/images/page/35491/02.07.01_LineEdit.png)
**사진 02.07.01 QLineEdit**
위의 코드에 예시를 들어보도록 하겠습니다. LineEdit에 글자가 변경되면, 위에 있는 Label에 LineEdit에 있는 글자가 입력되고, 만약 Enter키가 눌리면 LineEdit에 들어있는 글자가 Print 된다고 해봅시다. 그리고 버튼을 누르면 LineEdit안에 "Change Text"라는 글자가 입력되도록 해보겠습니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("lineeditTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #버튼에 기능을 할당하는 코드
        self.lineedit_Test.textChanged.connect(self.lineeditTextFunction)
        self.lineedit_Test.returnPressed.connect(self.printTextFunction)
        self.btn_changeText.clicked.connect(self.changeTextFunction)

    def lineeditTextFunction(self) :
        self.lbl_textHere.setText(self.lineedit_Test.text())

    def printTextFunction(self) :
        #self.lineedit이름.text()
        #Lineedit에 있는 글자를 가져오는 메서드
        print(self.lineedit_Test.text())

    def changeTextFunction(self) :
        #self.lineedit이름.setText("String")
        #Lineedit의 글자를 바꾸는 메서드
        self.lineedit_Test.setText("Change Text")

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

우선 LineEdit안에 있는 글자가 바뀔 때 마다 Label에 글자가 변하기 위해서는 textChanged.connect()를 이용하여 기능을 연결합니다. 또한 Return키(Enter키)가 눌릴 때 마다 LineEdit안에 있는 글자를 Print하기 위해서 returnPressed.connect()를 이용합니다.

그리고 다른 위젯들과 유사하게 LineEdit에 있는 글자를 가져올때는 text()메서드를, 새로운 글자를 입력하기 위해서는 setText()메서드를 사용할 수 있습니다.

그리고 위의 코드를 실행시켜본다면 아래의 사진과 같은 결과를 얻을 수 있습니다.
![02.07.02 LineEdit_Run](https://wikidocs.net/images/page/35491/02.07.02_LineEdit_Run.png)
**사진 02.02.02 QCheckBox의 실행**



# 02.08 Input - QTextEdit, QPlainTextEdit

- [RichText와 PlainText](https://wikidocs.net/35492#richtext-plaintext)
- [QTextEdit이란?](https://wikidocs.net/35492#qtextedit)
- QTextEdit의 주요 함수
  - [글자의 입력, 가져오기에 관한 함수](https://wikidocs.net/35492#_1)
  - [글자의 모양에 관련된 함수](https://wikidocs.net/35492#_2)
  - [함수의 사용예시](https://wikidocs.net/35492#_3)
- [QPlainTextEdit이란?](https://wikidocs.net/35492#qplaintextedit)
- [QTextEdit의 주요 함수](https://wikidocs.net/35492#qtextedit_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)

[QTextEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QTextEdit.html?highlight=qtextedit)
[QPlainTextEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QPlainTextEdit.html?highlight=qtextedit)

이번 페이지의 예제코드는 위의 링크에 있는 02.08 TextEdit 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QTextEdit과 QPlainTextEdit의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## RichText와 PlainText

이번 페이지를 들어가기에 앞서 RichText와 PlainText의 차이점에 대해서 알아보도록 하겠습니다.
Rich Text란 글자의 색상, 크기, 기울임, 굵기 등을 조절할 수 있는 텍스트를 의미하며, Plain Text란 이와 반대되는 개념으로 색상, 크기를 별도로 조절할 수 없고 시스템에서 지정된대로만 글자를 표시하는 것을 의미합니다.

이러한 차이점을 먼저 설명하는 이유는 이번 장에서 배울 TextEdit과 PlainTextEdit의 차이가 RichText와 PlainText의 지원이기 때문입니다. 이제 자세한 내용을 알아보도록 하겠습니다.

## QTextEdit이란?

![02.08.01 QTextEdit](https://wikidocs.net/images/page/35492/02.08.01_QTextEdit.png)
**사진 02.08.01 QTextEdit**

TextEdit은 Rich Text를 지원하는 글자입력 위젯으로 다양한 색상, 굵기의 적용이 가능합니다. LineEdit과 다르게 여러줄의 데이터를 입력할 수 있습니다. LineEdit과 마찬가지로 위젯을 더블클릭해 미리 값을 입력해놓을 수도 있습니다.

## QTextEdit의 주요 함수

QTextEdit의 주요 함수들에 대해서 살펴보도록 하겠습니다.

### 글자의 입력, 가져오기에 관한 함수

| 함수                  | 설명                                                         |
| :-------------------- | :----------------------------------------------------------- |
| .toPlainText()        | TextEdit에 쓰여있는 글자를 가져옵니다.                       |
| .setPlainText(String) | TextEdit에 PlainText 형식의 글을 새로 입력합니다. Parameter에는 TextEdit에 표시할 글자가 들어갑니다. |
| .setText(String)      | TextEdit에 RichText 형식의 글을 입력합니다. Parameter에는 TextEdit에 표시할 글자가 들어갑니다. |
| .append(String)       | TextEdit에 글자를 추가합니다. 추가된 글자는 다음줄에 표시되며, Parameter에는 추가할 글자가 들어갑니다. |
| .clear()              | TextEdit에 쓰여있는 글자를 지웁니다.                         |

### 글자의 모양에 관련된 함수

이 부분에서는 Parameter로 QFont, QColor형식의 객체를 요구하는 함수들이 등장합니다. QFont와 QColor에 대한 설명은 이 페이지의 하위페이지인 [02.08.01 QFont](https://wikidocs.net/37456)와 [02.08.02 QColor](https://wikidocs.net/37457)를 참고해주시기 바랍니다.

| 함수                       | 설명                                                         |
| :------------------------- | :----------------------------------------------------------- |
| .setCurrentFont(QFont)     | TextEdit의 폰트를 바꿉니다. Parameter에는 QFont형식의 객체가 들어갑니다. |
| .setTextColor(QColor)      | TextEdit의 글자색을 바꿉니다. Parameter에는 QColor형식의 객체가 들어갑니다. |
| .setFontItalic(Boolean)    | TextEdit에 쓰여있는 글자를 기울입니다. Parameter에는 True/False값이 들어가며, True일 때 글자가 기울여집니다. |
| .setFontUnderline(Boolean) | TextEdit의 글자에 밑줄을 적용합니다. Parameter에는 True/False값이 들어가며, True일 때 글자에 밑줄이 쳐집니다. |
| .setFontPointSize(size)    | TextEdit의 글자 크기를 변경합니다. Parameter에는 글자의 크기(숫자)가 들어갑니다. |

### 함수의 사용예시

![02.08.01 QTextEdit](https://wikidocs.net/images/page/35492/02.08.01_QTextEdit.png)
**사진 02.08.01 QTextEdit**
위에서 설명한 함수들을 이용하여 위의 사진과 같은 프로그램을 한번 작성해보도록 하겠습니다. 이 프로그램은 이번 챕터의 예제코드에서 다운받으실 수 있으니, 다운받으셔서 ObjectName을 확인하시기 바랍니다.

PrintText라는 버튼을 클릭하면 TextEdit에 있는 내용이 Print되고, ClearText라는 버튼을 클릭하면 TextEdit의 내용이 모두 지워지도록 만들어보겠습니다. 그리고 Set Font를 클릭하면 폰트가 "Apple SD Gothic Neo"체로 변경되고, Set Font Italic버튼을 클릭하면 글자가 기울여지고, Set Font Color Red 버튼을 누르면 글자가 빨간색으로 바뀌게 합니다. 그리고 마지막으로 Font Size Up/Down 버튼을 누르면 글자가 커지고 작아지게 만들어보겠습니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5 import uic

form_class = uic.loadUiType("texteditTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)
        self.fontSize = 10

        #TextEdit과 관련된 버튼에 기능 연결
        self.btn_printTextEdit.clicked.connect(self.printTextEdit)
        self.btn_clearTextEdit.clicked.connect(self.clearTextEdit)
        self.btn_setFont.clicked.connect(self.setFont)
        self.btn_setFontItalic.clicked.connect(self.fontItalic)
        self.btn_setFontColor.clicked.connect(self.fontColorRed)
        self.btn_fontSizeUp.clicked.connect(self.fontSizeUp)
        self.btn_fontSizeDown.clicked.connect(self.fontSizeDown)

    def printTextEdit(self) :
        print(self.textedit_Test.toPlainText())

    def clearTextEdit(self) :
        self.textedit_Test.clear()

    def setFont(self) :
        fontvar = QFont("Apple SD Gothic Neo",10)
        self.textedit_Test.setCurrentFont(fontvar)

    def fontItalic(self) :
        self.textedit_Test.setFontItalic(True)

    def fontColorRed(self) :
        colorvar = QColor(255,0,0)
        self.textedit_Test.setTextColor(colorvar)

    def fontSizeUp(self) :
        self.fontSize = self.fontSize + 1
        self.textedit_Test.setFontPointSize(self.fontSize)

    def fontSizeDown(self) :
        self.fontSize = self.fontSize - 1
        self.textedit_Test.setFontPointSize(self.fontSize)

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

기본적으로 위에서 언급한 버튼들은 모두 QPushButton이므로, 생성자에서 .clicked.connect()를 사용하여 기능을 연결해줍니다. 그리고 각 함수들의 내부를 살펴보면 위에서 언급한 메서드들이 어떻게 사용되는지 알 수 있을겁니다.

글자크기를 조절하는데는 클래스 내에 self.fontSize와 같이 글자 크기를 조절하는 변수를 만들어 사용하는 것이 편리합니다. 또한, 예제코드를 사용할 때, QFont를 선언할 때 입력된 폰트가 시스템에 설치되어있지 않으면 에러가 발생할 수 있으니 반드시 시스템에 설치되어 있는 폰트로 바꿔주시기 바랍니다.

## QPlainTextEdit이란?

![02.08.02 QPlainTextEdit](https://wikidocs.net/images/page/35492/02.08.02_QPlainTextEdit.png)
**사진 02.08.02 QPlainTextEdit**
PlainTextEdit은 Plain Text를 지원하는 글자입력 위젯으로 TextEdit과 달리 글자의 색상, 크기, 기울기, 폰트 등의 변경이 불가능합니다. LineEdit과 다르게 여러줄의 데이터를 입력할 수 있으며 LineEdit, TextEdit과 마찬가지로 위젯을 더블클릭해 미리 값을 입력해놓을 수도 있습니다.

## QTextEdit의 주요 함수

QTextEdit의 주요 함수들에 대해서 살펴보도록 하겠습니다.

| 함수                     | 설명                                                         |
| :----------------------- | :----------------------------------------------------------- |
| .toPlainText()           | PlainTextEdit에 쓰여있는 글자를 가져옵니다.                  |
| .setPlainText(String)    | PlainTextEdit에 글을 새로 입력합니다. Parameter에는 PlainTextEdit에 표시할 글자가 들어갑니다. |
| .appendPlainText(String) | PlainTextEdit에 글자를 추가합니다. 추가된 글자는 다음줄에 표시되며, Parameter에는 추가할 글자가 들어갑니다. |
| .clear()                 | PlainTextEdit에 쓰여있는 글자를 지웁니다.                    |

.toPlainText() .setPlainText() .appendPlainText() .clear()는 사용방법이 TextEdit의 .toPlainText() .setText() .append() .clear()와 동일하므로 이 함수들을 실제로 어떻게 사용하는지는 위에 있는 TextEdit의 사용예시를 참고해주시기 바랍니다.



# 02.08.01 QFont

- [QFont](https://wikidocs.net/37456#qfont)

## QFont

[QFont Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtGui/QFont.html?highlight=qfont)
QFont란, 폰트 정보를 갖고 있는 객체를 의미합니다. QFont 객체는 아래와 같이 선언합니다.

> QFont는 QtGui.QFont에 소속되어 있습니다.
> from PyQt5.QtGui import QFont 혹은 from PyQt5.QtGui import * 을 사용하셔야 합니다.

```
from PyQt5.QtGui import *
fontVar = QFont(FontName)
fontVar = QFont(FontName, Size)
```

| 이름     | 설명                                         |
| :------- | :------------------------------------------- |
| fontVar  | QFont의 객체이름을 의미합니다.               |
| FontName | 폰트의 이름을 문자열의 형태로 입력합니다.    |
| Size     | 글자의 크기를 지정하며, 정수값을 요구합니다. |



# 02.08.02 QColor

- [QColor](https://wikidocs.net/37457#qcolor)

## QColor

[QColor Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtGui/QColor.html?highlight=qcolor)
[Adobe Color - RGB코드 변환](https://color.adobe.com/create)
QColor란, 색상정보를 갖고 있는 객체를 의미합니다. QColor 객체는 아래와 같이 선언합니다. RGB 코드가 익숙하지 않으신 분들은 위에 있는 Adobe Color링크에서 원하시는 색상을 RGB코드로 변환해보시는 것을 추천드립니다.

> QColor는 QtGui.QColor에 소속되어 있습니다.
> from PyQt5.QtGui import QColor 혹은 from PyQt5.QtGui import * 을 사용하셔야 합니다.

```
from PyQt5.QtGui import *
colorVar = QColor(Red, Green, Blue, Alpha)
```

| 이름     | 설명                                                         |
| :------- | :----------------------------------------------------------- |
| colorVar | QColor의 객체이름을 의미합니다.                              |
| Red      | RGB 색상 중 Red값을 의미하며, 0부터 255사이의 정수가 들어갑니다. 값이 클 수록 빨강이 진하게 보여집니다. |
| Green    | RGB색상 중 Green값을 의미하며, 0부터 255사이의 정수가 들어갑니다. 값이 클 수록 초록이 진하게 보여집니다. |
| Blue     | RGB색상 중 Blue값을 의미하며, 0부터 255사이의 정수가 들어갑니다. 값이 클 수록 파랑이 진하게 보여집니다. |
| Alpha    | 투명도를 의미하는 값으로 0부터 255사이의 정수가 들어갑니다. 만약 아무런 값도 입력하지 않을 경우 기본값인 255가 들어갑니다. 값이 255에 가까워 질 수록 불투명하게, 0에 가까워 질 수록 투명하게 보여집니다. |



# 02.09 Input - QComboBox

- [QComboBox란?](https://wikidocs.net/35493#qcombobox)
- QComboBox의 시그널과 주요 함수
  - [QComboBox의 시그널](https://wikidocs.net/35493#qcombobox_2)
  - QComboBox의 주요함수
    - [항목의 반환](https://wikidocs.net/35493#_1)
    - [항목의 추가 및 삭제](https://wikidocs.net/35493#_2)
  - [함수의 사용](https://wikidocs.net/35493#_3)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QComboBox Documentatino - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QComboBox.html?highlight=qcombobox)

이번 페이지의 예제코드는 위의 링크에 있는 02.09 ComboBox 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QComboBox의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QComboBox란?

![02.09.01 QComboBox](https://wikidocs.net/images/page/35493/02.09.01_ComboBox.png)
**사진 02.09.01 QComboBox**

ComboBox란, 여러개의 선택지 중 하나의 선택지를 선택하는 위젯을 의미합니다. RadioButton과 유사한 기능을 하지만, ComboBox는 RadioButton과 다르게 여러개의 선택지가 목록으로 제공된다는 특징이 있으며, 이 항목들에는 리스트처럼 Index가 존재한다는 특징이 있습니다.

ComboBox에 항목을 추가할 때는 Qt Designer에서 ComboBox를 Dialog Window로 끌어온 후, 위젯을 더블클릭하여 항목을 추가할 수도 있고, 아래에 나올 addItem(), InsertItem()함수를 이용하여 항목을 추가할 수도 있습니다.

## QComboBox의 시그널과 주요 함수

### QComboBox의 시그널

QComboBox에서 어떤 항목이 선택되었을 때, 특정 기능을 수행하도록 하는 코드를 작성해보겠습니다. 이 기능은 다음과 같이 작성합니다. 다른 위젯들과 마찬가지로 생성자 내부에서 작성합니다.

> **self.ComboBox이름.currentIndexChanged.connect(함수)**

### QComboBox의 주요함수

QComboBox의 사용을 편리하게 해주는 주요 함수가 몇가지 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

#### 항목의 반환

| 함수             | 설명                                                         |
| :--------------- | :----------------------------------------------------------- |
| .currentIndex()  | 현재 ComboBox에서 선택된 항목의 Index를 반환합니다.          |
| .currentText()   | 현재 ComboBox에서 선택된 항목의 글자를 반환합니다.           |
| .count()         | ComboBox에 몇개의 항목이 있는지를 그 개수를 반환합니다.      |
| .itemText(index) | Index번째에 어떤 항목이 있는지 그 글자를 반환합니다. Parameter로 찾을 항목의 Index를 입력받습니다. |

#### 항목의 추가 및 삭제

| 함수                       | 설명                                                         |
| :------------------------- | :----------------------------------------------------------- |
| .addItem(String)           | ComboBox의 맨 뒤에 항목을 추가합니다. Parameter로 추가할 항목의 글자를 입력받습니다. |
| .insertItem(index, String) | Index번째에 String이라는 항목을 추가합니다. Parameter로 항목을 추가할 위치(index)와 글자를 입력받습니다. |
| .removeItem(index)         | index번째의 항목을 삭제합니다. Parameter로 삭제할 항목의 Index를 입력받습니다. |
| .clear()                   | ComboBox의 모든 항목을 삭제합니다.                           |

### 함수의 사용

![02.09.02 ComboBoxRun](https://wikidocs.net/images/page/35493/02.09.02_ComboBoxRun.png)
**사진 02.09.02 QComboBox의 실행**

위에서 설명한 함수들을 어떻게 쓰는지 한번 알아보도록 하겠습니다.

위의 사진에 있는 프로그램은 위에 있는 ComboBox에서 항목을 선택하면 ComboBox 위에 있는 label에 항목의 이름이 표시되고, PrintItem을 누르면 ComboBox에서 선택된 항목이 Print되며, ClearItem버튼을 누르면 ComboBox가 초기화됩니다. 중간에 있는 LineEdit에 원하는 글자를 쓰고 AddItem 버튼을 누르면 ComboBox에 항목이 추가됩니다. 그리고 맨 밑에 있는 ComboBox에서 삭제하고 싶은 항목을 선택한 후, DeleteItem을 누르면 해당 항목이 ComboBox에서 제거됩니다.

위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("comboboxTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #프로그램 실행 시 두개의 ComboBox를 동기화시키는 코드
        self.syncComboBox()

        #ComboBox에 기능 연결
        self.cmb_Test.currentIndexChanged.connect(self.comboBoxFunction)

        #버튼에 기능 연결
        self.btn_printItem.clicked.connect(self.printComboBoxItem)
        self.btn_clearItem.clicked.connect(self.clearComboBoxItem)
        self.btn_addItem.clicked.connect(self.addComboBoxItem)
        self.btn_deleteItem.clicked.connect(self.deleteComboBoxItem)

    def syncComboBox(self) :
        for i in range(0,self.cmb_Test.count()) :
            self.cmb_second.addItem(self.cmb_Test.itemText(i))

    def comboBoxFunction(self) :
        self.lbl_display.setText(self.cmb_Test.currentText())

    def clearComboBoxItem(self) :
        self.cmb_Test.clear()
        self.cmb_second.clear()

    def printComboBoxItem(self) :
        print(self.cmb_second.currentText())

    def addComboBoxItem(self) :
        self.cmb_Test.addItem(self.lineedit_addItem.text())
        self.cmb_second.addItem(self.lineedit_addItem.text())
        print("Item Added")

    def deleteComboBoxItem(self) :
        self.delidx = self.cmb_second.currentIndex()
        self.cmb_Test.removeItem(self.delidx)
        self.cmb_second.removeItem(self.delidx)
        print("Item Deleted")

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

우선 선택한 항목을 Label에 표시되는 것 부터 살펴보도록 하겠습니다. ComboBox의 항목이 선택되었을 때, Label에 값이 바뀌게 하기 위해서 ComboBox에 .currentIndexChanged.connect()를 이용하여 기능을 연결한 것을 볼 수 있습니다.

그리고 버튼을 누르면 ComboBox의 항목이 print되고, ComboBox가 초기화되는 것을 구현하기 위해서 .currentText()와 .clear()함수를 사용했습니다.

ComboBox에 항목을 추가할 때는 addItem()을 사용했으며, ComboBox에서 항목을 삭제할 때는 삭제할 항목의 index를 .currentIndex()를 이용하여 받아온 후, .removeItem() 함수를 이용하여 항목을 삭제합니다.

이렇게 항목의 글자, index를 가져오는 작업, ComboBox에 항목을 추가, 삭제, 초기화하는 것은 위에서 언급한 함수를 사용하면 편리하게 진행할 수 있습니다. 그런데 위의 프로그램에서 위에 있는 ComboBox와 아래에 있는 ComboBox가 항상 동기화 되는 것을 볼 수 있습니다. 여러개의 ComboBox에 어떻게 동일한 값을 갖게 할 수 있을까요??

여러개의 ComboBox를 동기화하는데는 여러가지 기법이 있지만, 그 기법들의 공통점은 "추가할 때는 같은 것을 추가하고, 제거할 때는 같이 항목을 삭제한다는 것"입니다. 아래의 코드를 한번 보도록 하겠습니다.

```
    def syncComboBox(self) :
        for i in range(0,self.cmb_Test.count()) :
            self.cmb_second.addItem(self.cmb_Test.itemText(i))

    def addComboBoxItem(self) :
        self.cmb_Test.addItem(self.lineedit_addItem.text())
        self.cmb_second.addItem(self.lineedit_addItem.text())
        print("Item Added")

    def deleteComboBoxItem(self) :
        self.delidx = self.cmb_second.currentIndex()
        self.cmb_Test.removeItem(self.delidx)
        self.cmb_second.removeItem(self.delidx)
        print("Item Deleted")
```

이 코드는 처음 보여드린 코드 중, ComboBox를 동기화 하는데 관련이 있는 코드를 가져온 것입니다. 화면이 실행될 때 가장 먼저 실행되는 syncComboBox함수의 경우, 화면이 실행될 때 아래에 있는 ComboBox에 위에 있는 ComboBox의 모든 항목을 가져와 추가합니다. 그리고 ComboBox에 항목을 추가, 삭제하는 addComboBoxItem, deleteComboBoxItem의 코드를 보면 하나의 ComboBox에만 값을 추가하는 것이 아니라, 여러개의 ComboBox에 동시에 같은 항목을 추가하고 삭제하는 것을 볼 수 있습니다.

이런식으로 동일한 항목을 추가, 삭제하여 여러개의 ComboBox를 동기화 시킬 수 있습니다.



# 02.10 Input - QSpinBox, QDoubleSpinBox

- [SpinBox란?](https://wikidocs.net/35494#spinbox)
- SpinBox의 시그널과 함수
  - [SpinBox의 시그널](https://wikidocs.net/35494#spinbox_2)
  - SpinBox의 주요함수
    - [현재 상태의 반환](https://wikidocs.net/35494#_1)
    - [SpinBox의 상태 변경](https://wikidocs.net/35494#spinbox_4)
  - [함수의 사용](https://wikidocs.net/35494#_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QSpinbox Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QSpinBox.html?highlight=qspinbox)
[QDoubleSpinbox Documentatino - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QDoubleSpinBox.html?highlight=qdoublespinbox)

이번 페이지의 예제코드는 위의 링크에 있는 02.10 SpinBox 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QSpinBox와 QDoubleSpinBox의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## SpinBox란?

![02.10.01 SpinBox](https://wikidocs.net/images/page/35494/02.10.01_QSpinBox.png)
**사진 02.10.01 QSpinBox와 QDoubleSpinBox**

SpinBox란, 버튼을 이용하여 상자 안의 숫자의 크기를 조절할 수 있는 위젯을 의미합니다. SpinBox는 QSpinBox와 QDoubleSpinBox 이렇게 두종류가 존재하는데요, QSpinBox는 정수값을, QDoubleSpinBox는 실수값을 가질 수 있습니다. 즉 소수점 아래의 숫자가 필요하다면 QDoubleSpinBox를, 그렇지 않다면 QSpinBox를 사용하시면 됩니다.

![02.10.02 SpinBox Property Editor](https://wikidocs.net/images/page/35494/02.10.02_SpinBox_Property_Editor.png)
**사진 02.10.02 QSpinBox와 QDoubleSpinBox의 Property Editor**

SpinBox는 다른 위젯과 다르게 Property Editor에서 약간 수정할 점이 있는데요, SpinBox를 선택한 후 Property Editor의 밑부분을 보면 위의 사진과 같은 항목이 있을겁니다. 여기서 minumum, maximum, singleStep, value값을 조절해줘야 합니다. 이 값들은 아래와 같은 의미를 가집니다.

> - Minumum : 이 SpinBox에서 가질 수 있는 최소값을 의미합니다.
> - Maximum : 이 SpinBox에서 가질 수 있는 최대값을 의미합니다.
> - SingleStep : 버튼을 눌렀을 때 숫자가 한번에 얼마나 늘어나고 줄어들지를 지정합니다.
> - Value : SpinBox에서 값을 조절하지 않았을 때, 기본적으로 보여줄 값을 지정합니다.

## SpinBox의 시그널과 함수

### SpinBox의 시그널

QSpinBox와 QDoubleSpinBox에서 값이 변경되었을 때, 특정 기능을 수행하도록 하는 코드를 작성해보겠습니다. 이 기능은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> **self.SpinBox/DoubleSpinBox이름.valueChanged.connect(함수)**

### SpinBox의 주요함수

SpinBox들의 사용을 편리하게 해주는 주요 함수가 몇가지 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

#### 현재 상태의 반환

| 함수          | 설명                                           |
| :------------ | :--------------------------------------------- |
| .value()      | 현재 SpinBox에 있는 값을 반환합니다.           |
| .minimum()    | 현재 SpinBox의 최솟값을 반환합니다.            |
| .maximum()    | 현재 SpinBox의 최댓값을 반환합니다.            |
| .singleStep() | 현재 SpinBox의 singleStep의 크기를 반환합니다. |

#### SpinBox의 상태 변경

| 함수               | 설명                                                         |
| :----------------- | :----------------------------------------------------------- |
| .setRange(min,max) | SpinBox의 최대,최솟값을 변경합니다. Parameter로 SpinBox의 새로운 최솟값과 최댓값을 입력받습니다. QSpinBox에서는 정수값을, QDoubleSpinBox에서는 실수값을 입력할 수 있습니다. |
| .setStep(Value)    | SpinBox의 Step크기를 변경합니다. Parameter로 새로운 Step의 값을 입력받습니다. QSpinBox에서는 정수값을, QDoubleSpinBox에서는 실수값을 입력할 수 있습니다. |

### 함수의 사용

![02.10.01 SpinBox](https://wikidocs.net/images/page/35494/02.10.01_QSpinBox.png)
**사진 02.10.01 QSpinBox와 QDoubleSpinBox**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다.

위의 사진에 있는 프로그램은 위에 있는 SpinBox의 값을 바꾸면 SpinBox안에 있는 값이 Print되고, ShowInfo버튼을 누르면 현재 SpinBox의 최댓값, 최솟값, SingleStep값을 print합니다. 그리고 Change버튼을 누르면 SpinBox의 값을 범위를 바꾸고, SingleStep의 값을 변경합니다.

위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("spinboxTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        self.spinbox_Test.valueChanged.connect(self.printValue)
        self.btn_showInfo.clicked.connect(self.printInfo)
        self.btn_changeRangeStep.clicked.connect(self.changeRangeStep)

        self.doublespinbox_Test.valueChanged.connect(self.printDoubleValue)
        self.btn_doubleShowInfo.clicked.connect(self.printDoubleInfo)
        self.btn_doubleChangeRangeStep.clicked.connect(self.doubleChangeRangeStep)

    def printValue(self) :
        print(self.spinbox_Test.value())

    def printInfo(self) :
        print("Maximum value is",self.spinbox_Test.maximum())
        print("Minimum value is",self.spinbox_Test.minimum())
        print("Step Size is",self.spinbox_Test.singleStep())

    def changeRangeStep(self) :
        self.spinbox_Test.setRange(0,1000)
        self.spinbox_Test.setStep(10)

    def printDoubleValue(self) :
        print(self.doublespinbox_Test.value())

    def printDoubleInfo(self) :
        print("Maximum value is",self.doublespinbox_Test.maximum())
        print("Minimum value is",self.doublespinbox_Test.minimum())
        print("Step Size is",self.doublespinbox_Test.singleStep())

    def doubleChangeRangeStep(self) :
        self.doublespinbox_Test.setRange(0,1000)
        self.doublespinbox_Test.setStep(1.5)

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.10.03 SpinBoxRun](https://wikidocs.net/images/page/35494/02.10.03_QSpinBox_Run.png)
**사진 02.10.03 SpinBox의 실행**

# 02.11 Input - QSlider, QDial

- [QSlider란?](https://wikidocs.net/35495#qslider)
- [QDial이란?](https://wikidocs.net/35495#qdial)
- QSlider와 QDial의 기능연결과 함수
  - [QSlider와 QDial의 시그널](https://wikidocs.net/35495#qslider-qdial_1)
  - QSlider와 QDial의 주요함수
    - [현재 상태의 반환](https://wikidocs.net/35495#_1)
    - [Slider와 Dial의 상태 변경](https://wikidocs.net/35495#slider-dial)
  - [함수의 사용](https://wikidocs.net/35495#_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)

[QAbstractSlider Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QAbstractSlider.html#PySide2.QtWidgets.PySide2.QtWidgets.QAbstractSlider.setPageStep)
[QSlider Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QSlider.html?highlight=qslider)
[QDial Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QDial.html?highlight=qdial)

이번 페이지의 예제코드는 위의 링크에 있는 02.11 SliderDial 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QSlider와 QDial의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QSlider란?

![02.11.01 Slider](https://wikidocs.net/images/page/35495/02.11.01_QSlider.png)
**사진 02.11.01 VerticalSlider와 Horizontal Slider**

![02.11.02 QSlider Property](https://wikidocs.net/images/page/35495/02.11.02_QSlider_Property.png) **사진 02.11.02 QSlider의 Property Editor**

QSlider란, 위의 사진과 같이 바를 움직여 값을 조절할 수 있는 위젯을 의미합니다. Slider는 가로로 되어있느냐, 세로로 되어있느냐에 따라서 HorizontalSlider(가로), VerticalSlider(세로)로 구분합니다. QSlider는 정수값을 가질 수 있으며, QSpinBox와 마찬가지로 Property Editor에서 Value, Minimum, Maximum, singleStep, pageStep의 값을 조절해줘야 합니다. 이 값들은 아래와 같은 의미를 갖습니다.

> - Value : QSlider의 값을 의미합니다.
> - Minumum : QSlider가 가질 수 있는 가장 작은 값을 의미합니다.
> - Maximum : Qslider가 가질 수 있는 가장 큰 값을 의미합니다.
> - SingleStep : Slider를 옮기거나, 방향키를 눌러 이동할 수 있는 최소한의 값을 의미합니다.
> - PageStep : SingleStep보다는 조금 큰 개념으로 Page Up/Down등의 키를 눌렀을 때 이동할 수 있는 값을 의미합니다.

## QDial이란?

![02.11.03 QDial](https://wikidocs.net/images/page/35495/02.11.03_QDial.png)
**사진 02.11.03 QDial**

QDial이란, QSlider를 다이얼의 형태로 바꿔놓은 것으로, 위의 사진과 같이 다이얼을 돌려 값을 조절할 수 있는 위젯을 의미합니다. QDial은 정수값을 가질 수 있으며, QSlider와 마찬가지로 Property Editor에서 Value, Minimum, Maximum, singleStep, pageStep의 값을 조절해줘야 합니다.

## QSlider와 QDial의 기능연결과 함수

QSlider와 QDial은 QAbstractSlider라는 동일한 클래스를 상속받습니다.
따라서 기본적인 기능연결과 함수는 QSlider와 QDial이 동일한 코드로 작동됩니다.

### QSlider와 QDial의 시그널

QSpinBox와 QDoubleSpinBox, 그리고 QDial에서 값이 변경되었을 때, 특정 기능을 수행하도록 하는 코드를 작성해보겠습니다. 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> Slider와 Dial의 Slider가 움직였을 때 기능 실행
> **self.Slider/Dial이름.sliderMoved.connect(함수)**
>
> Slider와 Dial의 값이 바뀌었을 때 기능 실행
> **self.Slider/Dial이름.valueChanged.connect(함수)**
>
> Slider와 Dial의 범위가 바뀌었을 때 기능 실행
> **self.Slider/Dial이름.rangeChanged.connect(함수)**

### QSlider와 QDial의 주요함수

QSlider와 QDial들의 사용을 편리하게 해주는 주요 함수가 몇가지 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

#### 현재 상태의 반환

아래에 설명할 함수들은 현재 상태를 알려주는 함수들입니다. 이 함수들은 모두 int형으로 값을 반환하므로, 이 값을 어딘가에 표시하려고 한다면 str()를 이용하여 String형(문자열)로 바꾼 후에 사용하셔야 합니다.

| 함수          | 설명                                               |
| :------------ | :------------------------------------------------- |
| .value()      | Slider 혹은 Dial의 현재값을 반환합니다.            |
| .minimum()    | Slider 혹은 Dial의 최솟값을 반환합니다.            |
| .maximum()    | Slider 혹은 Dial의 최댓값을 반환합니다.            |
| .singleStep() | Slider 혹은 Dial의 singleStep의 크기를 반환합니다. |
| .pageStep()   | Slider 혹은 Dial의 pageStep의 크기를 반환합니다.   |

#### Slider와 Dial의 상태 변경

| 함수                  | 설명                                                         |
| :-------------------- | :----------------------------------------------------------- |
| .setMaximum(Max)      | Slider, Dial의 최댓값을 설정합니다. Parameter로 Slider와 Dial의 새로운 최댓값을 입력받습니다. |
| .setMinimum(Min)      | Slider, Dial의 최솟값을 설정합니다. Parameter로 Slider와 Dial의 새로운 최솟값을 입력받습니다. |
| .setSingleStep(Value) | Slider, Dial의 SingleStep의 크기를 설정합니다. Parameter로 Slider와 Dial의 새로운 SIngleStep의 값을 입력받습니다. |
| .setPageStep(Value)   | Slider, Dial의 PageStep을 설정합니다. Parameter로 Slider와 Dial의 새로운 PageStep을 입력받습니다. |
| .setRange(Max,Min)    | Slider, Dial의 범위를 변경합니다. Parameter로 Slider와 Dial의 새로운 최댓값과 최솟값을 입력받습니다. |
| .setValue(Value)      | Slider, Dial의 값을 설정합니다. Parameter로 Slider와 Dial의 값을 입력받습니다. |

### 함수의 사용

![02.11.04 QSliderDial Example](https://wikidocs.net/images/page/35495/02.11.04_QSliderDial_Example.png)
**사진 02.11.04 QSlider와 QDial의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> - Slider, Dial의 값을 변경하면 Label에 Slider와 Dial의 값이 보여집니다.
> - V_Info, H_Info, D_Info 버튼을 누르면 Horizontal Slider, Vertical Slider, Dial의 최대/최솟값, PageStep, SingleStep값을 출력합니다.
> - V_Range, H_Range, D_Range 버튼을 누르면 Horizontal Slider, Vertical Slider, Dial의 최대/최솟값, PageStep, SingleStep의 값을 변경합니다.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("sliderDialTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #Vertical Slider의 시그널 사용
        self.slider_vertical.valueChanged.connect(self.showVerticalSliderValue)
        self.slider_vertical.rangeChanged.connect(self.printRangeChanged)

        #Horizontal Slider의 시그널 사용
        self.slider_horizontal.valueChanged.connect(self.showHorizontalSliderValue)
        self.slider_horizontal.rangeChanged.connect(self.printRangeChanged)

        #Dial의 시그널 사용
        self.dial_Test.valueChanged.connect(self.showDialValue)
        self.dial_Test.rangeChanged.connect(self.printRangeChanged)

        #버튼에 기능 연결
        self.btn_vInfo.clicked.connect(self.getVerticalInfo)
        self.btn_vValue.clicked.connect(self.setVertical)
        self.btn_hInfo.clicked.connect(self.getHorizontalInfo)
        self.btn_hValue.clicked.connect(self.setHorizontal)
        self.btn_dInfo.clicked.connect(self.getDialInfo)
        self.btn_dValue.clicked.connect(self.setDial)

    def printRangeChanged(self) :
        print("Range Changed")

    #Vertical Slider에 관련된 함수들

    def showVerticalSliderValue(self) :
        #Vertical Slider의 시그널 이용 - Vertical Slider의 값이 변경되면 Label에 값을 표시
        self.lbl_vertical.setText(str(self.slider_vertical.value()))

    def getVerticalInfo(self) :
        #Vertical Slider의 최대/최솟값과 PageStep/SingleStep값을 출력합니다.
        print("Maximum : " + str(self.slider_vertical.maximum()))
        print("Minimum : " + str(self.slider_vertical.minimum()))
        print("PageStep : " + str(self.slider_vertical.pageStep()))
        print("SingleStep : " + str(self.slider_vertical.singleStep()))

    def setVertical(self) :
        #Vertical Slider의 최대/최솟값과 PageStep/SingleStep값을 변경합니다.
        self.slider_vertical.setMaximum(500)
        self.slider_vertical.setMinimum(-500)
        self.slider_vertical.setPageStep(100)
        self.slider_vertical.setSingleStep(20)


    #Horizontal Slider에 관련된 함수들

    def showHorizontalSliderValue(self) :
        #Horizontal Slider의 시그널 이용 - Horizontal Slider의 값이 변경되면 Label에 값을 표시
        self.lbl_horizontal.setText(str(self.slider_horizontal.value()))

    def getHorizontalInfo(self) :
        #Horizontal Slider의 최대/최솟값과 PageStep/SingleStep값을 출력합니다.
        print("Maximum : " + str(self.slider_horizontal.maximum()))
        print("Minimum : " + str(self.slider_horizontal.minimum()))
        print("PageStep : " + str(self.slider_horizontal.pageStep()))
        print("SingleStep : " + str(self.slider_horizontal.singleStep()))

    def setHorizontal(self) :
        #Horizontal Slider의 최대/최솟값과 PageStep/SingleStep값을 변경합니다.
        self.slider_horizontal.setMaximum(500)
        self.slider_horizontal.setMinimum(-500)
        self.slider_horizontal.setPageStep(100)
        self.slider_horizontal.setSingleStep(20)

    #Dial에 관련된 함수들
    def showDialValue(self) :
        #Dial의 시그널 이용 - Dial의 값이 변경되면 Label에 값을 표시
        self.lbl_dial.setText(str(self.dial_Test.value()))

    def getDialInfo(self) :
        #Dial의 최대/최솟값과 PageStep/SingleStep값을 출력합니다.
        print("Maximum : " + str(self.dial_Test.maximum()))
        print("Minimum : " + str(self.dial_Test.minimum()))
        print("PageStep : " + str(self.dial_Test.pageStep()))
        print("SingleStep : " + str(self.dial_Test.singleStep()))

    def setDial(self) :
        #Dial의 최대/최솟값과 PageStep/SingleStep값을 변경합니다.
        self.dial_Test.setMaximum(500)
        self.dial_Test.setMinimum(-500)
        self.dial_Test.setPageStep(100)
        self.dial_Test.setSingleStep(20)


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.11.05 QSliderDial Run](https://wikidocs.net/images/page/35495/02.11.05_QSliderDial_Example_Run.png)
**사진 02.11.05 QSlider, QDial의 실행**



# 02.12 Input - QDateTimeEdit

- QDateTimeEdit란?
  - QDateTimeEdit의 속성
    - [dateTime](https://wikidocs.net/36796#datetime)
    - [maximumDateTime](https://wikidocs.net/36796#maximumdatetime)
    - [minimumDateTime](https://wikidocs.net/36796#minimumdatetime)
    - [calendarPopup](https://wikidocs.net/36796#calendarpopup)
    - [displayFormat](https://wikidocs.net/36796#displayformat)
- QDateTimeEdit의 시그널과 함수
  - [QDateTimeEdit의 시그널](https://wikidocs.net/36796#qdatetimeedit_3)
  - QDateTimeEdit의 함수
    - [현재 상태의 반환](https://wikidocs.net/36796#_1)
    - [QDateTimeEdit의 값 변경](https://wikidocs.net/36796#qdatetimeedit_5)
    - [QDateTimeEdit과 입력/표시 형식과 관련된 함수](https://wikidocs.net/36796#qdatetimeedit_6)
    - [QDateTimeEdit의 날짜/시간의 최대/최소에 관련된 함수들](https://wikidocs.net/36796#qdatetimeedit_7)
  - [함수의 사용](https://wikidocs.net/36796#_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QDateTimeEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QDateTimeEdit.html#PySide2.QtWidgets.QDateTimeEdit)

이번 페이지의 예제코드는 위의 링크에 있는 02.12 DateTimeEdit 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QDateTimeEdit의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QDateTimeEdit란?

![02.12.01 DateTimeEdit](https://wikidocs.net/images/page/36796/02.12.01_DateTimeEdit.png)
**사진 02.12.01 QDateTimeEdit**

QDateTimeEdit이란, 위의 사진과 같이 날짜, 시간을 설정할 수 있는 위젯을 의미합니다. 이 위젯은 정말 다양한 속성을 갖고 있는데요, Property Editor의 사진을 보며 QDateTimeEdit은 어떤 속성을 가지고 있고, 이 속성들이 어떤 의미를 갖는지 하나하나 살펴보도록 하겠습니다.

### QDateTimeEdit의 속성

![02.12.02 Property](https://wikidocs.net/images/page/36796/02.12.02_Property.png)
**사진 02.12.02 QDateTimeEdit의 Property Editor**

#### dateTime

dateTime이란 위젯에 표시할 날짜와 시간, TimeZone을 설정합니다.
날짜와 시간을 개별적으로 조절하는 속성으로는 date와 time이 있습니다.

> - date : 위젯에 표시할 날짜를 설정합니다.
> - time : 위젯에 표시할 시간을 설정합니다.

#### maximumDateTime

maximumDateTime이란 위젯에서 표시할 가장 큰 날짜, 시간(선택할 수 있는 범위 중 가장 미래의 날짜, 시간)을 설정합니다.
가장 큰 날짜, 시간을 개별적으로 조절하는 속성으로는 maximumDate, maximumTime이 있습니다.

> - maximumDate : 위젯에서 표시할 가장 큰(가장 미래의) 날짜를 설정합니다.
> - maximumTime : 위젯에서 표시할 가장 큰(가장 미래의) 시간을 설정합니다.

#### minimumDateTime

minumumDateTime이란 위젯에서 표시할 가장 작은 날짜, 시간(선택할 수 있는 범위 중 가장 과거의 날짜, 시간)을 설정합니다.
가장 작은 날짜, 시간을 개별적으로 조절하는 속성으로는 minimumDate, minimunTime이 있습니다.

> - minimumDate : 위젯에서 표시할 가장 작은(가장 과거의) 날짜를 설정합니다.
> - minimumTime : 위젯에서 표시할 가장 작은(가장 과거의) 시간을 설정합니다.

#### calendarPopup

![02.12.03](https://wikidocs.net/images/page/36796/02.12.03_CalendarPopup.png) **사진 02.12.03 calendarPopup**
calendarPopup을 체크하면 위의 사진과 같이 날짜를 변경할 때 달력을 표시하여 달력에서 날짜를 선택할 수 있게 합니다. calendarPopup을 체크하지 않으면 SpinBox의 형태로 날짜를 변경할 수 있습니다.

#### displayFormat

displayFormat은 위젯에 표시할 날짜, 시간의 표시 형식을 문자열로 지정합니다.
displayFormat의 형식문자는 아래와 같습니다.

> 표시예는 **[2019-01-14 월요일 오후 8시 7분 6초]** 를 표시한다고 가정합니다.
>
> | 구분 | 서식문자 | 설명                                                         | 표시예                         |
> | :--- | :------- | :----------------------------------------------------------- | :----------------------------- |
> | 연도 | yyyy     | 연도를 전체 다 표시합니다                                    | 2019                           |
> | 월   | MM       | 월을 숫자로 표시합니다                                       | 01                             |
> |      | MMM      | 월을 영어단어 약자로 표시합니다                              | Jan                            |
> |      | MMMM     | 월을 영어단어로 표시합니다                                   | January                        |
> | 일   | dd       | 일을 숫자로 표시합니다                                       | 14                             |
> |      | ddd      | 요일을 영어단어 약자로 표시합니다                            | Mon                            |
> |      | dddd     | 요일을 영어단어로 표시합니다                                 | Monday                         |
> | 시간 | AP       | AM/PM(오전/오후)를 표시합니다                                | AP사용 - 8 PM, AP미사용 - 20시 |
> |      | h        | 시간을 표시합니다                                            | 8                              |
> |      | hh       | 시간을 표시합니다. 시간이 한자리수 일 경우, 앞에 0을 붙입니다. | 08                             |
> | 분   | m        | 분을 표시합니다                                              | 7                              |
> |      | mm       | 분을 표시합니다. 분이 한자리수 일 경우, 앞에 0을 붙입니다.   | 07                             |
> | 초   | s        | 초를 표시합니다                                              | 6                              |
> |      | ss       | 초를 표시합니다. 초가 한자리수 일 경우, 앞에 0을 붙입니다.   | 06                             |

## QDateTimeEdit의 시그널과 함수

### QDateTimeEdit의 시그널

QDateTimeEdit의 값이 변경되었을 때, 특정 기능을 수행하도록 하는 코드를 알아보겠습니다. QDateTimeEdit의 시그널은 총 세가지가 있으며, 생성자 내부에 다음과 같이 작성해야 합니다.

> DateTimeEdit의 날짜와 시간이 바뀌었을 때 기능 실행
> **self.DateTimeEdit이름.dateTimeChanged.connect(함수)**
>
> DateTimeEdit의 날짜가 바뀌었을 때 기능 실행
> **self.DateTimeEdit이름.dateChanged.connect(함수)**
>
> DateTimeEdit의 시간이 바뀌었을 때 기능 실행
> **self.DateTimeEdit이름.timeChanged.connect(함수)**

### QDateTimeEdit의 함수

QDateTimeEdit의 사용을 편리하게 해주는 함수들에 대해서 알아보도록 하겠습니다. QDateTimeEdit의 함수들은 Parameter로 [QDateTime](https://wikidocs.net/37458), [QDate](https://wikidocs.net/37459), [QTime](https://wikidocs.net/37460) 객체를 요구하는 경우가 많습니다. 따라서 이 함수를 학습하기 전, 이 페이지의 하위페이지에 있는 QDateTime, QDate, QTime객체의 설명을 먼저 읽고 이 함수를 학습하시는 것을 추천드립니다.

#### 현재 상태의 반환

| 함수               | 설명                                                         |
| :----------------- | :----------------------------------------------------------- |
| .dateTime()        | QDateTimeEdit의 날짜, 시간값을 반환합니다. QDateTime형태의 객체가 반환됩니다. |
| .date()            | QDateTimeEdit의 날짜값을 반환합니다. QDate형태의 객체가 반환됩니다. |
| .time()            | QDateTimeEdit의 시간값을 반환합니다. QTime형태의 객체가 반환됩니다. |
| .maximumDateTime() | QDateTimeEdit의 날짜/시간값의 최댓값을 반환합니다. QDateTime형태의 객체가 반환됩니다. |
| .maximumDate()     | QDateTimeEdit의 날짜값의 최댓값을 반환합니다. QDate형태의 객체가 반환됩니다. |
| .maximumTime()     | QDateTimeEdit의 시간값의 최댓값을 반환합니다. QTime형태의 객체가 반환됩니다. |
| .minimumDateTime() | QDateTimeEdit의 날짜/시간값의 최솟값을 반환합니다. QDateTime형태의 객체가 반환됩니다. |
| .minimumDate()     | QDateTimeEdit의 날짜값의 최솟값을 반환합니다. QDate형태의 객체가 반환됩니다. |
| .minimumTime()     | QDateTimeEdit의 시간값의 최솟값을 반환합니다. QTime형태의 객체가 반환됩니다. |

위의 함수들을 사용할 때는 위의 함수들이 반환하는 값을 받아줄 객체들을 선언한 후 사용해야 합니다. 아래의 예시코드를 보도록 하겠습니다.

```
self.dateTimeVar = self.dateTimeEdit.dateTime()
self.dateVar = self.dateTimeEdit.date()
self.timeVar = self.dateTimeEdit.time()
```

예시코드를 보면 dateTime(), date(), time()함수를 사용할 때, 이 함수들이 반환하는 값을 받아줄 dateTimeVar, dateVar, timeVar과 같은 객체들을 선언한 후, 이 객체들에 값을 넣는 것을 볼 수 있습니다. 이렇듯 현재상태를 반환하는 함수들은 객체들에 값을 저장한 후 그 값을 사용하는 것을 권장합니다.

#### QDateTimeEdit의 값 변경

| 함수                    | 설명                                                         |
| :---------------------- | :----------------------------------------------------------- |
| .setDateTime(QDateTime) | QDateTimeEdit의 날짜/시간값을 설정합니다. Parameter로 새로 설정할 날짜/시간값을 가진 QDateTime 객체가 필요합니다. |
| .setDate(QDate)         | QDateTimeEdit의 날짜값을 반환합니다. Parameter로 새로 설정할 날짜값을 가진 QDate 객체가 필요합니다. |
| .setTime(QTime)         | QDateTimeEdit의 시간값을 반환합니다. Parameter로 새로 설정할 시간값을 가진 QTime 객체가 필요합니다. |

#### QDateTimeEdit과 입력/표시 형식과 관련된 함수

| 함수                           | 설명                                                         |
| :----------------------------- | :----------------------------------------------------------- |
| .calendarPopup()               | QDateTimeEdit의 CalendarPopup이 속성이 참인지, 거짓인지를 반환해줍니다. |
| .setCalendarPopup(bool)        | QDateTimeEdit의 CalendarPopup 속성을 변경할 수 있는 함수입니다. Parameter로 True/False 값이 들어갑니다. |
| .setDisplayFormat(Format Text) | QDateTimeEdit이 어떤 형식으로 날짜와 시간을 보여줄지를 설정합니다. Parameter로 형식문자가 들어갑니다. |

#### QDateTimeEdit의 날짜/시간의 최대/최소에 관련된 함수들

이 부분에서 설명할 함수들은 QDateTimeEdit의 날짜/시간의 최댓값, 최솟값과 관련된 함수들입니다.
Parameter를 요구하는 함수들 중, dateTime이라는 말이 들어가 있는 함수들은 QDateTime형식의 객체를, date가 들어가 있는 함수들은 QDate형식의 객체를, time이 들어가 있는 함수들은 QTime형식의 객체를 Parameter로 입력해주어야 합니다.

| 함수                        | 설명                                                         |
| :-------------------------- | :----------------------------------------------------------- |
| .setDateTimeRange(min, max) | QDateTimeEdit의 날짜/시간 범위를 설정합니다. Parameter로 날짜/시간의 최솟값, 최댓값을 입력받습니다. |
| .setDateRange(min, max)     | QDateTimeEdit의 날짜 범위를 설정합니다. Parameter로 날짜의 최솟값, 최댓값을 입력받습니다. |
| .setTimeRange(min, max)     | QDateTimeEdit의 시간 범위를 설정합니다. Parameter로 시간의 최솟값, 최댓값을 입력받습니다. |
| .setMaximumDateTime(max)    | QDateTimeEdit의 날짜/시간값의 최댓값을 설정합니다. Parameter로 새로운 날짜/시간의 최댓값을 입력받습니다. |
| .setMaximumDate(max)        | QDateTimeEdit의 날짜값의 최댓값을 설정합니다. Parameter로 새로운 날짜의 최댓값을 입력받습니다. |
| .setMaximumTime(max)        | QDateTimeEdit의 시간값의 최댓값을 설정합니다. Parameter로 새로운 시간의 최댓값을 입력받습니다. |
| .setMinimumDateTime(min)    | QDateTimeEdit의 날짜/시간값의 최솟값을 설정합니다. Parameter로 새로운 날짜/시간의 최솟값을 입력받습니다. |
| .setMinimumDate(min)        | QDateTimeEdit의 날짜값의 최솟값을 설정합니다. Parameter로 새로운 날짜의 최솟값을 입력받습니다. |
| .setMinimumTime(min)        | QDateTimeEdit의 시간값의 최솟값을 설정합니다. Parameter로 새로운 시간의 최솟값을 입력받습니다. |
| .clearMaximumDateTime()     | QDateTimeEdit의 날짜/시간값의 최댓값을 기본값으로 변경합니다. 기본값은 7999년 12월 31일 23시 59분 59초입니다. |
| .clearMaximumDate()         | QDateTimeEdit의 날짜값의 최댓값을 기본값으로 변경합니다. 기본값은 7999년 12월 31일 입니다. |
| .clearMaximumTime()         | QDateTimeEdit의 시간값의 최댓값을 기본값으로 변경합니다. 기본값은 23시 59분 59초입니다. |
| .clearMinimumDateTime()     | QDateTimeEdit의 날짜/시간값의 최솟값을 기본값으로 변경합니다. 기본값은 1752년 9월 14일 0시 0분 0초 입니다. |
| .clearMinimumDate()         | QDateTimeEdit의 날짜값의 최솟값을 기본값으로 변경합니다. 기본값은 1752년 9월 14일 입니다. |
| .clearMinimumTime()         | QDateTimeEdit의 시간값의 최솟값을 기본값으로 변경합니다. 기본값은 0시 0분 0초 입니다. |

### 함수의 사용

![02.12.04 QDateTimeEdit Example](https://wikidocs.net/images/page/36796/02.12.04_QDateTimeEdit_Example.png)
**사진 02.12.04 QDateTimeEdit의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> - 프로그램이 시작되면 QDateTimeEdit의 값이 현재 날짜/시간으로 초기화됨
> - DisplayInfo 버튼을 누르면 왼쪽에 있는 Label에 날짜/시간, 날짜, 시간이 표시됨
> - DateTime이라고 쓰여있는 LineEdit에 날짜/시간을 쓰고 DateTime버튼을 누르면 DateTimeEdit의 날짜/시간값을 바꿀 수 있음
> - Date라고 쓰여있는 LineEdit에 날짜를 쓰고 Date버튼을 누르면 DateTimeEdit의 날짜값을 바꿀 수 있음
> - Time이라고 쓰여있는 LineEdit에 시간을 쓰고 Time버튼을 누르면 DateTimeEdit의 시간값을 바꿀 수 있음
> - DisplayFormat이라고 쓰여있는 LineEdit에 원하는 형식을 입력하고 ChangeFormat버튼을 누르면 DateTimeEdit의 형식이 변경됨
> - ShowRange버튼을 누르면 DateTimeEdit의 최대/최소 DateTime이 출력됨
> - Edit Maximum버튼을 누르면 DateTimeEdit의 최댓값이 10일 연장되고, Edit Minimum버튼을 누르면 DateTimeEdit의 최솟값이 10일 연장됨.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5 import uic

form_class = uic.loadUiType("dateTimeEditTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #프로그램이 실행되면 DateTimeEdit의 값이 현재 날짜/시간으로 설정되게 하기
        self.currentDateTime = QDateTime.currentDateTime()
        self.dateTimeEdit_Test.setDateTime(self.currentDateTime)

        #버튼들에 기능 할당
        self.btn_displayDateTime.clicked.connect(self.displayDateTime)
        self.btn_enterDateTime.clicked.connect(self.enterDateTimeFunc)
        self.btn_enterDate.clicked.connect(self.enterDateFunc)
        self.btn_enterTime.clicked.connect(self.enterTimeFunc)
        self.btn_changeFormat.clicked.connect(self.changeDisplayFormat)
        self.btn_showRange.clicked.connect(self.showRangeFunc)
        self.btn_editMaximum.clicked.connect(self.extendMaximum)
        self.btn_editMinimum.clicked.connect(self.extendMinimum)

    def displayDateTime(self) :
        #DateTimeEdit의 값을 사용할 때는 아래와 같이 객체를 만들고, 그 객체에 값을 저장한 후 사용해야 합니다.
        self.displayDateTimeVar = self.dateTimeEdit_Test.dateTime()
        self.displayDateVar = self.dateTimeEdit_Test.date()
        self.displayTimeVar = self.dateTimeEdit_Test.time()

        #QDateTime, QDate, QTime 객체들의 값을 Label에 표시합니다.
        #toString 함수는 02.12QDateTimeEdit의 하위페이지에 있는 QDateTime, QDate, QTime 함수를 참고하시기 바랍니다.
        self.lbl_displayDateTime.setText(self.displayDateTimeVar.toString("yyyy-MM-dd AP hh:mm:ss"))
        self.lbl_displayDate.setText(self.displayDateVar.toString("yyyy-MM-dd"))
        self.lbl_displayTime.setText(self.displayTimeVar.toString("AP hh:mm:ss"))

    def enterDateTimeFunc(self) :
        #LineEdit에서 글자를 가져온 후, fromString 함수를 이용해서 QDateTime객체를 만듭니다.
        #그 후, setDateTime 함수를 이용해 DateTimeEdit에 적용합니다.
        self.enterDateTimeText = self.line_dateTime.text()
        self.enterDateTimeVar = QDateTime.fromString(self.enterDateTimeText, "yyyy-MM-dd AP hh:mm:ss")
        self.dateTimeEdit_Test.setDateTime(self.enterDateTimeVar)

    def enterDateFunc(self) :
        #LineEdit에서 글자를 가져온 후, fromString 함수를 이용해서 QDate객체를 만듭니다.
        #그 후, setDate 함수를 이용해 DateTimeEdit에 적용합니다.
        self.enterDateText = self.line_date.text()
        self.enterDateVar = QDate.fromString(self.enterDateText, "yyyy-MM-dd")
        self.dateTimeEdit_Test.setDate(self.enterDateVar)

    def enterTimeFunc(self) :
        #LineEdit에서 글자를 가져온 후, fromString 함수를 이용해서 QTime객체를 만듭니다.
        #그 후, setTime 함수를 이용해 DateTimeEdit에 적용합니다.
        self.enterTimeText = self.line_time.text()
        self.enterTimeVar = QTime.fromString(self.enterTimeText, "AP hh:mm:ss")
        self.dateTimeEdit_Test.setTime(self.enterTimeVar)

    def changeDisplayFormat(self) :
        #LineEdit에서 글자를 가져온 후, 그 글자를 DateTimeEdit의 형식문자로 지정합니다.
        self.displayFormatText = self.line_displayFormat.text()
        self.dateTimeEdit_Test.setDisplayFormat(self.displayFormatText)

    def showRangeFunc(self) :
        print(self.dateTimeEdit_Test.minimumDateTime())
        print(self.dateTimeEdit_Test.maximumDateTime())

    def extendMaximum(self) :
        #DateTimeEdit의 현재 maximumDateTime을 가져옵니다.
        #그 후 addDays 함수를 이용하여 최댓값을 10일 연장시킨 후, setMaximumDateTime을 이용하여 DateTimeEdit에 적용시킵니다.
        self.currentMaximumDateTime = self.dateTimeEdit_Test.maximumDateTime()
        self.currentMaximumDateTime = self.currentMaximumDateTime.addDays(10)
        self.dateTimeEdit_Test.setMaximumDateTime(self.currentMaximumDateTime)

    def extendMinimum(self) :
        #DateTimeEdit의 현재 minimumDateTime을 가져옵니다.
        #그 후 addDays 함수를 이용하여 최솟값을 10일 뒤로 미룬 후, setMinimumDateTime을 이용하여 DateTimeEdit에 적용시킵니다.
        self.currentMinimumDateTime = self.dateTimeEdit_Test.minimumDateTime()
        self.currentMinimumDateTime = self.currentMinimumDateTime.addDays(-10)
        self.dateTimeEdit_Test.setMinimumDateTime(self.currentMinimumDateTime)


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. QDateTime, QDate, QTime과 같이 날짜, 시간정보를 갖고 있는 객체들을 수정할 때는 QDateTime, QDate, QTime의 자체 함수를 이용합니다. 이 자체함수들의 대한 정보는 이 페이지의 하위페이지인 [QDateTime](https://wikidocs.net/37458), [QDate](https://wikidocs.net/37459), [QTime](https://wikidocs.net/37460) 에서 보실 수 있습니다.
위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.

![02.12.05 QDateTimeEdit Run](https://wikidocs.net/images/page/36796/02.12.05_QDateTimeEdit_Run.png)
**사진 02.12.05 QDateTimeEdit의 실행**



# 02.12.01 QDateTime

- [QDateTime](https://wikidocs.net/37458#qdatetime)
- QDateTime의 함수로 객체생성하기
  - QDateTime의 객체생성 함수
    - [currentDateTime()의 사용](https://wikidocs.net/37458#currentdatetime)
    - [fromString()의 사용](https://wikidocs.net/37458#fromstring)
- QDateTime의 값 수정하기
  - [QDateTime의 값 반환](https://wikidocs.net/37458#qdatetime_4)
  - [QDateTime의 값 수정](https://wikidocs.net/37458#qdatetime_5)
  - [날짜/시간 간격 확인](https://wikidocs.net/37458#_1)

[QDateTime Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtCore/QDateTime.html?highlight=qdatetime)

## QDateTime

QDateTime이란, 날짜와 시간정보를 갖는 객체입니다. QDateTime 객체는 기본적으로 다음과 같이 생성할 수 있습니다.

> QDateTime은 QtCore에 소속되어 있습니다.
> from PyQt5.QtCore import QDateTime 혹은 from PyQt5.QtCore import * 을 사용하셔야 합니다.

```
from PyQt5.QtCore import *
dateTimeVar = QDateTime(dateVar, timeVar)
```

| 이름        | 설명                                                         |
| :---------- | :----------------------------------------------------------- |
| dateTimeVar | QDateTime의 객체이름을 의미합니다.                           |
| dateVar     | 년도, 월, 일과 같은 날짜 정보를 가진 QDate 객체를 의미합니다. |
| timeVar     | 시, 분, 초와 같은 시간 정보를 가진 QTime 객체를 의미합니다.  |

## QDateTime의 함수로 객체생성하기

QDateTime은 위와 같이 QDate객체와 QTime객체를 넣어 생성하는 방법도 있지만, QDateTime의 함수를 이용하여 초기화 하는 방법도 있습니다. 이 방법에 대해서 한번 알아보도록 하겠습니다.

### QDateTime의 객체생성 함수

| 이름                                | 설명                                                         |
| :---------------------------------- | :----------------------------------------------------------- |
| QDateTime.currentDateTime()         | 현재 시스템의 날짜, 시간값을 갖는 객체를 만듭니다.           |
| QDateTime.fromString(String,Format) | 문자열에서 날짜, 시간값을 가져옵니다. Parameter로 날짜, 시간값을 가져올 문자열과 이 문자열이 어떤 형식을 갖고 있는지 알려줄 형식문자를 입력받습니다. |

#### currentDateTime()의 사용

currentDateTime()을 이용하여 QDateTime의 객체를 만드는 것은 다음과 같이 작성합니다.

```
from PyQt5.QtCore import *
dateTimeVar = QDateTime.currentDateTime()
```

위의 코드로 만들어진 QDateTime의 객체는 currentDateTime()함수가 실행되었을 때의 시스템의 날짜, 시간값을 갖게 됩니다.

#### fromString()의 사용

fromString() 함수를 이용하여 객체를 생성해보도록 하겠습니다. 만약 "2019-05-17 00:37:35"라는 문자열과 fromString() 함수를 이용하여 QDateTime의 객체를 만든다고 가정해봅시다. 이 경우 코드는 다음과 같이 작성됩니다.

```
from PyQt5.QtCore import *
dateTimeVar = QDateTime.fromString("2019-05-17 00:37:35","yyyy-MM-dd hh:mm:ss")
```

fromString의 첫번째 Parameter로는 QDateTime의 값이 될 문자열이, 두번째 Parameter로는 앞의 문자열이 어떤 형식을 갖고 있는지 알려주는 형식문자가 들어갑니다. 이 형식문자는 [02.12 Input - QDateTimeEdit의 displayFormat](https://wikidocs.net/36796#displayformat)부분을 참조하시기 바랍니다.

## QDateTime의 값 수정하기

QDateTime에 있는 다양한 함수들을 통해 QDateTime의 값을 수정하고, 가공할 수 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

### QDateTime의 값 반환

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .toString(format) | 현재 DateTime의 날짜,시간값을 문자열의 형태로 출력합니다. Parameter로 날짜,시간의 형식문자가 필요합니다. |
| .date()           | 현재 DateTime의 날짜값을 QDate 형식으로 반환합니다.          |
| .time()           | 현재 DateTime의 시간값을 QTime 형식으로 반환합니다.          |

### QDateTime의 값 수정

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .setDate(QDate)   | 현재 DateTime의 날짜를 바꿉니다. Parameter로 QDate형식의 객체를 필요로 합니다. |
| .setTime(QTime)   | 현재 DateTime의 시간을 바꿉니다. Parameter로 QTime형식의 객체를 필요로 합니다. |
| .addDays(day)     | 현재 DateTime에서 날짜를 변경합니다. Parameter로 몇일 전 / 몇일 후로 변경할지 값을 입력해야 합니다. |
| .addMonths(month) | 현재 DateTime에서 월을 변경합니다. Parameter로 몇달 전 / 몇달 후로 변경할지 값을 입력해야 합니다. |
| .addYears(year)   | 현재 DateTime에서 연도를 변경합니다. Parameter로 몇년 전 / 몇년 후로 변경할지 값을 입력해야 합니다. |

위에서 설명한 함수들 중, addDays, addMonths, addYears의 예시를 한번 들어보도록 하겠습니다.
만약 DateTime의 값을 오늘 날짜에서 10일 후, 10달 후, 10년후로 만든다고 하면 다음과 같이 코드를 작성해야 합니다.

```
qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addDays(10)

qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addMonths(10)

qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addYears(10)
```

만약 10일 전, 10달 전, 10년 전으로 만든다고 하면 다음과 같이 코드를 작성해야 합니다.

```
qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addDays(-10)

qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addMonths(-10)

qDateTimeVar = QDateTime.currentDateTime()
qDateTimeVar.addYears(-10)
```

이처럼 Parameter의 값이 양수면 몇일/몇달/몇년 후로, 음수면 몇일/몇달/몇년 전으로 DateTime의 값이 변하게 됩니다.

### 날짜/시간 간격 확인

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .daysTo(QDate) | 현재 DateTime과 Parameter가 몇일 차이인지 알려줍니다. Parameter로 QDate 객체를 입력합니다. |
| .secsTo(QTime) | 현재 DateTime과 Parameter가 몇초 차이인지 알려줍니다. Parameter로 QTime 객체를 입력합니다. |

위의 함수를 어떻게 사용하는지 예시를 들어보도록 하겠습니다.

```
#오늘이 2019년 5월 21일이라고 가정합니다.
qDateTimeVar = QDateTime.currentDateTime()
qDateVar2 = QDate(2019,5,31)
print(qDateVar.daysTo(qDateVar2))
```

> 결과값 : 10

위와 같이 Parameter로 입력된 QDate객체의 날짜값이 QDateTime의 날짜값보다 미래라면 양수값이 출력되며, QDate객체의 날짜값이 QDateTime의 날짜값보다 과거라면 음수값이 출력됩니다.

# 02.12.02 QDate

- [QDate](https://wikidocs.net/37459#qdate)
- QDate의 함수로 객체생성하기
  - [QDate의 객체생성 함수](https://wikidocs.net/37459#qdate_2)
  - [currentDate()](https://wikidocs.net/37459#currentdate)
  - [fromString()](https://wikidocs.net/37459#fromstring)
- QDate의 값 수정하기
  - [QDate의 값 반환](https://wikidocs.net/37459#qdate_4)
  - [QDate의 값 수정](https://wikidocs.net/37459#qdate_5)
  - [기타 함수](https://wikidocs.net/37459#_1)

[QDate Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtCore/QDate.html?highlight=qdate)

## QDate

QDate란, 날짜정보를 갖는 객체입니다. QDate 객체는 기본적으로 다음과 같이 생성할 수 있습니다.

> QDate은 QtCore에 소속되어 있습니다.
> from PyQt5.QtCore import QDate 혹은 from PyQt5.QtCore import * 을 사용하셔야 합니다.

```
from PyQt5.QtCore import *
dateVar = QDate(Year,Month,Day)
```

| 이름    | 설명                                 |
| :------ | :----------------------------------- |
| dateVar | QDate의 객체이름을 의미합니다.       |
| Year    | QDate 객체가 가질 년도를 의미합니다. |
| Month   | QDate 객체가 가질 월을 의미합니다.   |
| Day     | QDate 객체가 가질 일을 의미합니다.   |

## QDate의 함수로 객체생성하기

QDate은 위와 같이 직접 년,월,일의 값을 넣어서 객체를 생성할 수도 있지만, QDate의 함수를 이용하여 초기화 하는 방법도 있습니다. 이 방법에 대해서 한번 알아보도록 하겠습니다.

### QDate의 객체생성 함수

| 이름                            | 설명                                                         |
| :------------------------------ | :----------------------------------------------------------- |
| QDate.currentDate()             | 현재 시스템의 날짜값을 갖는 객체를 만듭니다.                 |
| QDate.fromString(String,Format) | 문자열에서 날짜값을 가져옵니다. Parameter로 날짜값을 가져올 문자열과 이 문자열이 어떤 형식을 갖고 있는지 알려줄 형식문자를 입력받습니다. |

### currentDate()

currentDate()함수를 이용하여 객체를 생성해보도록 하겠습니다. currentDate()을 이용하여 QDate의 객체를 만드는 것은 다음과 같이 진행합니다.

```
from PyQt5.QtCore import *
dateVar = QDate.currentDate()
```

위의 코드로 만들어진 QDate의 객체는 currentDate()함수가 실행되었을 때의 시스템의 날짜, 시간값을 갖게 됩니다.

### fromString()

fromString() 함수를 이용하여 객체를 생성해보도록 하겠습니다. 만약 "2019-05-17"라는 문자열과 fromString() 함수를 이용하여 QDate의 객체를 만든다고 가정해봅시다. 이 경우 코드는 다음과 같이 작성됩니다.

```
from PyQt5.QtCore import *
dateTimeVar = QDate.fromString("2019-05-17","yyyy-MM-dd")
```

fromString의 첫번째 Parameter로는 QDate의 값이 될 문자열이, 두번째 Parameter로는 앞의 문자열이 어떤 형식을 갖고 있는지 알려주는 형식문자가 들어갑니다. 이 형식문자는 [02.12 Input - QDateTimeEdit의 displayFormat](https://wikidocs.net/36796#displayformat)부분을 참조하시기 바랍니다.

## QDate의 값 수정하기

QDate에 있는 다양한 함수들을 통해 QDate의 값을 수정하고, 가공할 수 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

### QDate의 값 반환

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .toString(format) | 현재 QDate의 날짜,시간값을 문자열의 형태로 출력합니다. Parameter로 날짜,시간의 형식문자가 필요합니다. |
| .year()           | QDate에 저장된 연도의 값을 가져옵니다.                       |
| .month()          | QDate에 저장된 월을 가져옵니다.                              |
| .day()            | QDate에 저장된 일을 가져옵니다.                              |

### QDate의 값 수정

| 함수                     | 설명                                                         |
| :----------------------- | :----------------------------------------------------------- |
| .setDate(year,month,day) | QDate의 날짜값을 변경합니다. Parameter로 년, 월, 일의 값이 필요합니다. |
| .addDays(day)            | 현재 QDate에서 날짜를 변경합니다. Parameter로 몇일 전 / 몇일 후로 변경할지 값을 입력해야 합니다. |
| .addMonths(month)        | 현재 QDate에서 월을 변경합니다. Parameter로 몇달 전 / 몇달 후로 변경할지 값을 입력해야 합니다. |
| .addYears(year)          | 현재 QDate에서 연도를 변경합니다. Parameter로 몇년 전 / 몇년 후로 변경할지 값을 입력해야 합니다. |

위에서 설명한 함수들 중, addDays, addMonths, addYears의 예시를 한번 들어보도록 하겠습니다.
만약 DateTime의 값을 오늘 날짜에서 10일 후, 10달 후, 10년후로 만든다고 하면 다음과 같이 코드를 작성해야 합니다.

```
qDateVar = QDate.currentDate()
qDateVar.addDays(10)

qDateVar = QDate.currentDate()
qDateVar.addMonths(10)

qDateVar = QDate.currentDate()
qDateVar.addYears(10)
```

만약 10일 전, 10달 전, 10년 전으로 만든다고 하면 다음과 같이 코드를 작성해야 합니다.

```
qDateVar = QDate.currentDate()
qDateVar.addDays(-10)

qDateVar = QDate.currentDate()
qDateVar.addMonths(-10)

qDateVar = QDate.currentDate()
qDateVar.addYears(-10)
```

이처럼 Parameter의 값이 양수면 몇일/몇달/몇년 후로, 음수면 몇일/몇달/몇년 전으로 QDate의 값이 변하게 됩니다.

### 기타 함수

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .daysTo(QDate) | 현재 QDate객체와 Parameter가 몇일 차이인지 알려줍니다. Parameter로 QDate 객체를 입력합니다. |
| .dayOfWeek()   | 현재 QDate가 가지고 있는 날짜의 요일을 1부터 7까지의 숫자로 반환합니다. 1이 월요일이고, 7이 일요일입니다. |
| .daysInMonth() | 현재 QDate가 갖고 있는 달이 몇일까지 있는지를 알려줍니다.    |
| .daysInYear()  | 현재 QDate가 갖고 있는 날짜가 1년에서 몇번째 날인지 알려줍니다. |

# 02.12.03 QTime

- [QTime](https://wikidocs.net/37460#qtime)
- QTime의 함수로 객체생성하기
  - [QTime의 객체생성 함수](https://wikidocs.net/37460#qtime_2)
  - [currentTime()](https://wikidocs.net/37460#currenttime)
  - [fromString()](https://wikidocs.net/37460#fromstring)
- QTime의 값 수정하기
  - [QTime의 값 반환](https://wikidocs.net/37460#qtime_4)
  - [기타함수](https://wikidocs.net/37460#_1)

## QTime

[QTime Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtCore/QTime.html?highlight=qdatetime)
QTime이란, 시간정보를 갖는 객체입니다. QTime 객체는 기본적으로 다음과 같이 생성할 수 있습니다.

> QTime은 QtCore에 소속되어 있습니다.
> from PyQt5.QtCore import QTime 혹은 from PyQt5.QtCore import * 을 사용하셔야 합니다.

```
from PyQt5.QtCore import *
timeVar = QTime(Hour, Minute, Second)
```

| 이름    | 설명                                 |
| :------ | :----------------------------------- |
| timeVar | QTime의 객체이름을 의미합니다.       |
| Hour    | QTime 객체가 가질 시간을 의미합니다. |
| Minute  | QTime 객체가 가질 분을 의미합니다.   |
| Second  | QTime 객체가 가질 초을 의미합니다.   |

## QTime의 함수로 객체생성하기

QTime은 위와 같이 직접 시,분,초를 넣어 생성하는 방법도 있지만, QTime의 함수를 이용하여 초기화 하는 방법도 있습니다. 이 방법에 대해서 한번 알아보도록 하겠습니다.

### QTime의 객체생성 함수

| 이름                            | 설명                                                         |
| :------------------------------ | :----------------------------------------------------------- |
| QTime.currentDateTime()         | 현재 시스템의 시간값을 갖는 객체를 만듭니다.                 |
| QTime.fromString(String,Format) | 문자열에서 시간값을 가져옵니다. Parameter로 시간값을 가져올 문자열과 이 문자열이 어떤 형식을 갖고 있는지 알려줄 형식문자를 입력받습니다. |

### currentTime()

currentTime()함수를 이용하여 객체를 생성해보도록 하겠습니다. currentTime()을 이용하여 QTime의 객체를 만드는 것은 다음과 같이 진행합니다.

```
from PyQt5.QtCore import *
timeVar = QTime.currentTime()
```

위의 코드로 만들어진 QTime의 객체는 currentTime()함수가 실행되었을 때의 시스템의 시간값을 갖게 됩니다.

### fromString()

fromString() 함수를 이용하여 객체를 생성해보도록 하겠습니다. 만약 "00:37:35"라는 문자열과 fromString() 함수를 이용하여 QTime의 객체를 만든다고 가정해봅시다. 이 경우 코드는 다음과 같이 작성됩니다.

```
from PyQt5.QtCore import *
timeVar = QTime.fromString("00:37:35","hh:mm:ss")
```

fromString의 첫번째 Parameter로는 QTime의 값이 될 문자열이, 두번째 Parameter로는 앞의 문자열이 어떤 형식을 갖고 있는지 알려주는 형식문자가 들어갑니다. 이 형식문자는 [02.12 Input - QDateTimeEdit의 displayFormat](https://wikidocs.net/36796#displayformat)부분을 참조하시기 바랍니다.

## QTime의 값 수정하기

QTime에 있는 다양한 함수들을 통해 QTime의 값을 수정하고, 가공할 수 있습니다. 이 함수들에 대해서 알아보도록 하겠습니다.

### QTime의 값 반환

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .toString(format) | 현재 QTime의 시간값을 문자열의 형태로 출력합니다. Parameter로 시간의 형식문자가 필요합니다. |
| .hour()           | 현재 QTime의 시간을 반환합니다.                              |
| .minute()         | 현재 QTime의 분을 반환합니다.                                |
| .second()         | 현재 QTime의 초를 반환합니다.                                |

### 기타함수

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .secsTo(QTime) | 현재 QTime과 Parameter가 몇초 차이인지 알려줍니다. Parameter로 QTime 객체를 입력합니다. |

# 02.13 Input - QDateEdit, QTimeEdit

- QDateEdit란?
  - [QDateEdit의 속성](https://wikidocs.net/37787#qdateedit_1)
- QTimeEdit란?
  - [QTimeEdit의 속성](https://wikidocs.net/37787#qtimeedit_1)
- QDate와 QTime의 시그널과 함수
  - [QDate와 QTime의 시그널](https://wikidocs.net/37787#qdate-qtime_1)
  - QDateEdit의 함수
    - [현재 상태의 반환](https://wikidocs.net/37787#_1)
    - [QDateEdit의 값과 입력/표시형식](https://wikidocs.net/37787#qdateedit_3)
    - [QDateEdit의 최대/최소에 관련된 함수들](https://wikidocs.net/37787#qdateedit_4)
  - QTimeEdit의 함수
    - [현재 상태의 반환](https://wikidocs.net/37787#_2)
    - [QDateEdit의 값과 표시형식](https://wikidocs.net/37787#qdateedit_5)
    - [QTimeEdit의 시간의 최대/최소에 관련된 함수들](https://wikidocs.net/37787#qtimeedit_3)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QTimeEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QTimeEdit.html?highlight=qtimeedit)
[QDateEdit Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QDateEdit.html?highlight=qdateedit)

이번 페이지의 예제코드는 위의 링크에 있는 02.13 DateEditTimeEdit 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QTimeEdit, QDateEdit의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QDateEdit란?

![02.13.01 QDateEdit](https://wikidocs.net/images/page/37787/02.13.01_dateEdit.png)
**사진 02.13.01 QDateEdit**

QDateEdit이란, 위의 사진과 같이 날짜를 설정할 수 있는 위젯으로, 앞서 설명했던 QDateTimeEdit에서 시간을 제거하고 날짜만 설정하는 위젯이라고 생각하시면 됩니다. QDateEdit은 QDateTimeEdit과 동일한 속성을 가지지만 수정해야 할 속성은 약간 다른데요, QDateEdit에서 수정해야 하는 속성들에 대해서 알아보도록 하겠습니다.

### QDateEdit의 속성

![02.13.02 QDateEdit Property Editor](https://wikidocs.net/images/page/37787/02.13.02_dateEdit_Property.png)
**사진 02.13.02 QDateEdit의 Property Editor**

> - date : 위젯에 표시할 날짜를 설정합니다.
> - maximumDate : 위젯에서 표시할 가장 큰(가장 미래의) 날짜를 설정합니다.
> - minimumDate : 위젯에서 표시할 가장 작은(가장 과거의) 날짜를 설정합니다.
> - calendarPopup : 날짜를 변경할 때, 달력을 이용할지를 결정합니다.
> - displayFormat : 위젯에 표시할 날짜의 표시형식을 문자열로 지정합니다. displayFormat의 서식문자는 [02.12 QDateTimeEdit](https://wikidocs.net/36796#displayformat) 부분을 참고하시기 바랍니다.

## QTimeEdit란?

![02.13.03 QTimeEdit](https://wikidocs.net/images/page/37787/02.13.03_timeEdit.png)
**사진 02.13.03 QTimeEdit**

QTimeEdit이란, 위의 사진과 같이 시간을 설정할 수 있는 위젯으로, 앞서 설명했던 QDateTimeEdit에서 날짜를 제거하고 시간만 설정하는 위젯이라고 생각하시면 됩니다. QTimeEdit은 QDateTimeEdit과 동일한 속성을 가지지만 수정해야 할 속성은 약간 다른데요, QDateEdit에서 수정해야 하는 속성들에 대해서 알아보도록 하겠습니다.

### QTimeEdit의 속성

![02.13.04 QTimeEdit Property](https://wikidocs.net/images/page/37787/02.13.04_timeEdit_Property.png) **사진 02.13.04 QTimeEdit의 Property Editor**

> - time : 위젯에 표시할 시간을 설정합니다.
> - maximumTime : 위젯에서 표시할 가장 큰(가장 미래의) 시간을 설정합니다.
> - minimumTime : 위젯에서 표시할 가장 작은(가장 과거의) 시간을 설정합니다.
> - displayFormat : 위젯에 표시할 시간의 표시형식을 문자열로 지정합니다. displayFormat의 서식문자는 [02.12 QDateTimeEdit](https://wikidocs.net/36796#displayformat) 부분을 참고하시기 바랍니다.

## QDate와 QTime의 시그널과 함수

### QDate와 QTime의 시그널

QDate와 QTime의 값이 변경되었을 때, 특정 기능을 수행하도록 하는 코드를 알아보겠습니다. QDate와 QTime의 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> DateEdit의 날짜가 바뀌었을 때 기능 실행
> **self.DateEdit이름.dateChanged.connect(함수)**
>
> TimeEdit의 시간이 바뀌었을 때 기능 실행
> **self.TimeEdit이름.timeChanged.connect(함수)**

------

### QDateEdit의 함수

QDateEdit의 사용을 편리하게 해주는 함수들에 대해서 알아보도록 하겠습니다. QDateEdit의 함수들은 Parameter로 [QDate](https://wikidocs.net/37459)객체를 요구하는 경우가 많습니다. 따라서 이 함수를 학습하기 전, 위의 링크에서 QDate객체에 대한 설명을 읽고 이 부분을 학습하시는 것을 추천드립니다.

#### 현재 상태의 반환

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .date()        | QDateEdit의 날짜값을 반환합니다. QDate형태의 객체가 반환됩니다. |
| .maximumDate() | QDateEdit의 날짜값의 최댓값을 반환합니다. QDate형태의 객체가 반환됩니다. |
| .minimumDate() | QDateEdit의 날짜값의 최솟값을 반환합니다. QDate형태의 객체가 반환됩니다. |

위의 함수들을 사용할 때는 위의 함수들이 반환하는 값을 받아줄 객체들을 선언한 후 사용해야 합니다. 아래의 예시코드를 보도록 하겠습니다.

```
self.dateVar = self.dateEdit.date()
```

예시코드를 보면 date()함수를 사용할 때, 함수가 반환하는 값을 받아줄 dateVar과 같은 객체들을 선언한 후, 이 객체들에 값을 넣는 것을 볼 수 있습니다. 이렇듯 현재상태를 반환하는 함수들은 객체들에 값을 저장한 후 그 값을 사용하는 것을 권장합니다.

#### QDateEdit의 값과 입력/표시형식

| 함수                           | 설명                                                         |
| :----------------------------- | :----------------------------------------------------------- |
| .setDate(QDate)                | QDateEdit의 날짜값을 반환합니다. Parameter로 새로 설정할 날짜값을 가진 QDate 객체가 필요합니다. |
| .calendarPopup()               | QDateEdit의 CalendarPopup이 속성이 참인지, 거짓인지를 반환해줍니다. |
| .setCalendarPopup(bool)        | QDateEdit의 CalendarPopup 속성을 변경할 수 있는 함수입니다. Parameter로 True/False 값이 들어갑니다. |
| .setDisplayFormat(Format Text) | QDateEdit이 어떤 형식으로 날짜와 시간을 보여줄지를 설정합니다. Parameter로 형식문자가 들어갑니다. |

#### QDateEdit의 최대/최소에 관련된 함수들

이 부분에서 설명할 함수들은 QDateEdit의 날짜/시간의 최댓값, 최솟값과 관련된 함수들입니다. Parameter를 요구하는 함수들 QDate형식의 객체를 Parameter로 입력해주어야 합니다.

| 함수                    | 설명                                                         |
| :---------------------- | :----------------------------------------------------------- |
| .setDateRange(min, max) | QDateEdit의 날짜 범위를 설정합니다. Parameter로 날짜의 최솟값, 최댓값을 입력받습니다. |
| .setMaximumDate(max)    | QDateEdit의 날짜값의 최댓값을 설정합니다. Parameter로 새로운 날짜의 최댓값을 입력받습니다. |
| .setMinimumDate(min)    | QDateEdit의 날짜값의 최솟값을 설정합니다. Parameter로 새로운 날짜의 최솟값을 입력받습니다. |
| .clearMaximumDate()     | QDateEdit의 날짜값의 최댓값을 기본값으로 변경합니다. 기본값은 7999년 12월 31일 입니다. |
| .clearMinimumDate()     | QDateEdit의 날짜값의 최솟값을 기본값으로 변경합니다. 기본값은 1752년 9월 14일 입니다. |

------

### QTimeEdit의 함수

QTimeEdit의 사용을 편리하게 해주는 함수들에 대해서 알아보도록 하겠습니다. QTimeEdit의 함수들은 Parameter로 [QTime](https://wikidocs.net/37460) 객체를 요구하는 경우가 많습니다. 따라서 이 함수를 학습하기 전, 이 페이지의 위의 링크에 있는 QTime객체의 설명을 먼저 읽고 이 함수를 학습하시는 것을 추천드립니다.

#### 현재 상태의 반환

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .time()        | QTimeEdit의 시간값을 반환합니다. QTime형태의 객체가 반환됩니다. |
| .maximumTime() | QTimeEdit의 시간값의 최댓값을 반환합니다. QTime형태의 객체가 반환됩니다. |
| .minimumTime() | QTimeEdit의 시간값의 최솟값을 반환합니다. QTime형태의 객체가 반환됩니다. |

위의 함수들을 사용할 때는 위의 함수들이 반환하는 값을 받아줄 객체들을 선언한 후 사용해야 합니다. 아래의 예시코드를 보도록 하겠습니다.

```
self.timeVar = self.timeEdit.time()
```

예시코드를 보면 time()함수를 사용할 때, 함수가 반환하는 값을 받아줄 timeVar과 같은 객체들을 선언한 후, 이 객체들에 값을 넣는 것을 볼 수 있습니다. 이렇듯 현재상태를 반환하는 함수들은 객체들에 값을 저장한 후 그 값을 사용하는 것을 권장합니다.

#### QDateEdit의 값과 표시형식

| 함수                           | 설명                                                         |
| :----------------------------- | :----------------------------------------------------------- |
| .setTime(QTime)                | QTimeEdit의 시간값을 반환합니다. Parameter로 새로 설정할 시간값을 가진 QTime 객체가 필요합니다. |
| .setDisplayFormat(Format Text) | QTimeEdit이 어떤 형식으로 날짜와 시간을 보여줄지를 설정합니다. Parameter로 형식문자가 들어갑니다. |

#### QTimeEdit의 시간의 최대/최소에 관련된 함수들

이 부분에서 설명할 함수들은 QTimeEdit의 최댓값, 최솟값과 관련된 함수들입니다. Parameter를 요구하는 함수들은 QTime형식의 객체를 Parameter로 입력해주어야 합니다.

| 함수                    | 설명                                                         |
| :---------------------- | :----------------------------------------------------------- |
| .setTimeRange(min, max) | QTimeEdit의 시간 범위를 설정합니다. Parameter로 시간의 최솟값, 최댓값을 입력받습니다. |
| .setMaximumTime(max)    | QTimeEdit의 시간값의 최댓값을 설정합니다. Parameter로 새로운 시간의 최댓값을 입력받습니다. |
| .setMinimumTime(min)    | QTimeEdit의 시간값의 최솟값을 설정합니다. Parameter로 새로운 시간의 최솟값을 입력받습니다. |
| .clearMaximumTime()     | QTimeEdit의 시간값의 최댓값을 기본값으로 변경합니다. 기본값은 23시 59분 59초입니다. |
| .clearMinimumTime()     | QTimeEdit의 시간값의 최솟값을 기본값으로 변경합니다. 기본값은 0시 0분 0초 입니다. |

# 02.14 Display - QCalendarWidget

- [QCalendarWidget이란?](https://wikidocs.net/38036#qcalendarwidget)
- QCalendarWidget의 시그널과 함수
  - [QCalendarWidget의 시그널](https://wikidocs.net/38036#qcalendarwidget_2)
  - QCalendarWidget의 함수
    - [현재 상태의 반환](https://wikidocs.net/38036#_1)
    - [Calendar의 페이지 이동](https://wikidocs.net/38036#calendar)
    - [Calendar의 상태변경](https://wikidocs.net/38036#calendar_1)
  - [함수의 사용](https://wikidocs.net/38036#_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QCalendarWidget Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QCalendarWidget.html?highlight=qcalendarwidget)

이번 페이지의 예제코드는 위의 링크에 있는 02.14 Calendar 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QCalendarWidget의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QCalendarWidget이란?

![02.14.01 QCalendarWidget](https://wikidocs.net/images/page/38036/02.14.01_Calendar.png)
**사진 02.14.01 QCalendarWidget**

QCalendarWidget이란, 위의 사진과 같이 달력에서 날짜를 선택할 수 있는 위젯입니다. 달력을 보여주는 것 뿐만 아니라, 달력에서 선택한 날짜를 가져오는 기능도 수행할 수 있습니다.

## QCalendarWidget의 시그널과 함수

QCalendarWidget에는 몇가지 시그널과 함수들이 있습니다. 이들에 대해서 알아보도록 하겠습니다.

### QCalendarWidget의 시그널

QCalendarWidget에서 날짜를 클릭하거나, 달력을 다른 페이지로 넘겼을 때, 특정 기능을 수행하는 코드를 작성해보도록 하겠습니다. QCalendarWidget의 시그널은 다음과 같이 작성하며, 생성자 내부에 작성해야 합니다.

> CalendarWidget에서 날짜가 클릭되었을 때 기능 실행
> **self.CalendarWidget이름.clicked.connect(함수)**
>
> CalendarWidget에서 선택된 날짜가 바뀌었을 때 기능 실행
> **self.CalendarWidget이름.selectionChanged.connect(함수)**
>
> CalendarWidget에서 달력을 다른 페이지로 넘겼을 때 기능 실행
> **self.CalendarWidget이름.currentPageChanged.connect(함수)**

### QCalendarWidget의 함수

QCalendarWidget의 값을 가져오고, QCalendarWidget의 값을 수정하는

#### 현재 상태의 반환

QCalendarWidget의 현재 상태를 반환하는 함수들입니다. 함수에 따라 QDate형식의 객체를 반환할 수도, int 형식의 값을 반환할 수도 있으므로, 함수의 설명에서 어떤 형식의 값을 반환하는지를 잘 확인하시기 바랍니다.

| 함수            | 설명                                                         |
| :-------------- | :----------------------------------------------------------- |
| .selectedDate() | QCalendarWidget에서 선택된 날짜를 반환합니다. 반환된 값은 QDate의 형식을 갖습니다. |
| .maximumDate()  | QCalendarWidget에서 선택할 수 있는 날짜의 최댓값을 반환합니다. 반환된 값은 QDate의 형식을 갖습니다. |
| .minimumDate()  | QCalendarWidget에서 선택할 수 있는 날짜의 최솟값을 반환합니다. 반환된 값은 QDate의 형식을 갖습니다. |
| .yearShown()    | QCalendarWidget에서 현재 보여주고 있는 연도를 int 형식으로 반환합니다. |
| .monthShown()   | QCalendarWidget에서 현재 보여주고 있는 월을 int 형식으로 반환합니다. |

#### Calendar의 페이지 이동

QCalendarWidget의 페이지를 이동하는 함수들입니다.

| 함수                        | 설명                                                         |
| :-------------------------- | :----------------------------------------------------------- |
| .setCurrentPage(year,month) | 현재 페이지를 원하는 연도, 월로 이동합니다. Parameter로 이동하고자 하는 년도, 월의 값을 필요로 합니다. |
| .showNextYear()             | 현재 달력에서 내년의 달력을 보여주는 함수입니다.             |
| .showNextMonth()            | 현재 달력에서 다음달의 달력을 보여주는 함수입니다.           |
| .showPreviousYear()         | 현재 달력에서 작년의 달력을 보여주는 함수입니다.             |
| .showPreviousMonth()        | 현재 달력에서 저번달의 달력을 보여주는 함수입니다.           |
| .showToday()                | 오늘 날짜가 있는 달력을 보여주는 함수입니다.                 |

위의 함수 설명에서 "현재 달력에서"의 의미는 만약 함수를 실행할 때 QCalendarWidget이 2019년 5월의 달력을 보여준다면, showNextYear()를 실행하면 2020년 5월, showNextMonth()를 실행하면 2020년 6월의 달력을 보여준다는 의미입니다.

#### Calendar의 상태변경

| 함수                    | 설명                                                         |
| :---------------------- | :----------------------------------------------------------- |
| .setSelectedDate(QDate) | 원하는 날짜가 선택되게 하는 함수입니다. Parameter로 선택할 날짜의 값이 들어있는 QDate 형식의 객체를 필요로 합니다. |
| .setDateRange(min,max)  | QCalendarWidget이 보여줄 날짜의 최대/최솟값을 설정합니다. Parameter로 날짜의 최솟값, 최댓값을 갖고 있는 QDate 형식의 객체가 필요합니다. |
| .setGridVisible(bool)   | 달력에서 그리드를 보여줄지를 결정합니다. Parameter로 True/False가 필요하며, True를 입력하면 달력에서 그리드를 보여주게 됩니다. |

![02.14.02 QCalendarWidget Grid](https://wikidocs.net/images/page/38036/02.14.02_CalendarGrid.png)
**사진 02.14.02 QCalendarWidget의 Grid**

.setGridVisible()함수를 보면 달력에서 그리드를 보여준다는 이야기가 나옵니다. QCalendarWidget에서 그리드란, 위의 사진처럼 달력에 선을 그어주는 것을 의미합니다.

### 함수의 사용

![02.14.03 QCalendarWidget Example](https://wikidocs.net/images/page/38036/02.14.03_CalendarExample.png)
**사진 02.14.03 QCalendarWidget의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> - 날짜를 선택하면 해당 날짜가 print됩니다.
> - 선택된 날짜가 바뀌면 아래에 Selected Date Here라고 적힌 Label에 바뀐 선택된 날짜가 표시됩니다.
> - 달력의 페이지가 바뀌면 아래에 Current Page Here라고 적힌 Label에 지금 보여지고 있는 달력의 연도, 월이 표시됩니다.
> - Prev.Month, Next Month 버튼을 누르면 달력이 전달, 다음달로 이동합니다.
> - Today버튼을 누르면 달력이 오늘 날짜를 보여줍니다.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5 import uic

form_class = uic.loadUiType("calendarTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #QCalendarWidget의 시그널
        self.calendarWidget_Test.clicked.connect(self.calendarClicked)
        self.calendarWidget_Test.currentPageChanged.connect(self.calendarPageChanged)
        self.calendarWidget_Test.selectionChanged.connect(self.calendarSelectionChanged)        

        #QCalendarWidget이 자동으로 오늘 날짜가 있는 달력을 보여주게 설정
        self.todayDate = QDate.currentDate()
        self.calendarWidget_Test.setCurrentPage(self.todayDate.year(), self.todayDate.month())

        #버튼에 기능 연결
        self.btn_prevMonth.clicked.connect(self.prevMonth)
        self.btn_nextMonth.clicked.connect(self.nextMonth)
        self.btn_today.clicked.connect(self.today)

    #CalendarWidget의 시그널에 연결된 함수들
    def calendarClicked(self) :
        print(self.calendarWidget_Test.selectedDate())

    def calendarPageChanged(self) :
        self.year = str(self.calendarWidget_Test.yearShown()) + "년"
        self.month = str(self.calendarWidget_Test.monthShown()) + "월"
        self.lbl_currentPage.setText(self.year + " " + self.month)

    def calendarSelectionChanged(self) :
        self.selectedDateVar = self.calendarWidget_Test.selectedDate()
        self.lbl_selectedDate.setText(self.selectedDateVar.toString())

    #버튼에 연결된 함수들
    def prevMonth(self) :
        self.calendarWidget_Test.showPreviousMonth()

    def nextMonth(self) :
        self.calendarWidget_Test.showNextMonth()

    def today(self) :
        self.calendarWidget_Test.showToday()


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.14.04 QCalendarWidget Run](https://wikidocs.net/images/page/38036/02.14.04_CalendarRun.png)
**사진 02.14.04 QCalendarWidget의 실행**

# 02.15 Display - QPixmap

- [QPixmap이란?](https://wikidocs.net/38038#qpixmap)
- QPixmap의 사용방법
  - [Qt Designer](https://wikidocs.net/38038#qt-designer)
  - Python 코드
    - [QPixmap 객체의 생성](https://wikidocs.net/38038#qpixmap_2)
    - [QPixmap 객체에 이미지 불러오기 - 파일](https://wikidocs.net/38038#qpixmap-)
    - [QPixmap 객체에 이미지 불러오기 - Web](https://wikidocs.net/38038#qpixmap-web)
    - [QPixmap을 Label에 표시하기](https://wikidocs.net/38038#qpixmap-label)
- QPixmap의 함수
  - [이미지의 Load/Save](https://wikidocs.net/38038#loadsave)
  - [이미지의 크기와 관련된 함수](https://wikidocs.net/38038#_1)
  - [Pixmap과 관련된 Label의 함수](https://wikidocs.net/38038#pixmap-label)
  - [함수의 사용](https://wikidocs.net/38038#_2)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QPixmap Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtGui/QPixmap.html?highlight=qpixmap)

이번 페이지의 예제코드는 위의 링크에 있는 02.15 Pixmap 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QPixmap의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QPixmap이란?

![02.15.01 QPixmap](https://wikidocs.net/images/page/38038/02.15.01_QPixmap_Picture.png)
**사진 02.15.01 QPixmap**

![02.15.02 QPixmap Support Format](https://wikidocs.net/images/page/38038/02.15.02_QPixmap_Support_Format.png)
**사진 02.15.02 QPixmap 지원 포맷**

QPixmap이란, PyQt에서 이미지를 보여줄 때 사용하는 객체로 위에 사진에 있는 포맷들을 지원하는 객체입니다. QPixmap은 지금까지 다뤘던 것들과 다르게 자체적인 위젯이 없어서 Label을 이용하여 이미지를 표현합니다. 이제부터 QPixmap을 이용하기 위해서는 Qt Designer에서는 어떻게 위젯을 배치해야 하며, Python 코드는 어떻게 작성해야 하는지에 대해서 알아보도록 하겠습니다.

## QPixmap의 사용방법

### Qt Designer

![02.15.03 Label Placement](https://wikidocs.net/images/page/38038/02.15.03_Lable_Placement.png)
**사진 02.15.03 QPixmap을 사용하기 위한 Label의 배치**

우선, QPixmap을 사용하기 위해서는 Qt Designer에서 위의 사진과 같이 Label을 배치합니다. 이때 위의 사진과 같이 Label의 크기를 크게 만들어야 합니다. 사진이 Label의 크기보다 클 경우 사진이 짤려서 안보여지기 때문에 Label을 사진이 표시될 정도로 적당히 크게 만듭니다.

Qt Desginer를 이용하여 Label을 배치했다면, 이제 Python 코드로 들어가보도록 하겠습니다.

### Python 코드

Qt Designer를 이용하여 배치한 Label에 이미지를 보여주게 하기 위해서는 Python 코드에서 다음의 작업들을 해주어야 합니다.

> 1. QPixmap의 객체를 만듭니다.
> 2. QPixmap 객체에 사진을 불러옵니다.
> 3. Label에 QPixmap 객체를 보여지게 합니다.

이제 이 작업을 순서대로 한번씩 진행해보도록 하겠습니다.

------

#### QPixmap 객체의 생성

> Pixmap은 PyQt5.QtGui에 소속되어 있습니다.
> from PyQt5.QtGui import QPixmap 혹은 from PyQt5.QtGui import * 을 사용해야 합니다.

QPixmap를 만들어주기 위해서는 위의 문구와 같이 QPixmap을 import 해주어야 합니다.
QPixmap을 import 했다면, 다음과 같이 QPixmap 객체를 생성해줍니다.

```
from PyQt5.QtGui import *
qPixmapVar = QPixmap()
```

------

#### QPixmap 객체에 이미지 불러오기 - 파일

> **qPixmapVar.load("사진파일경로")**

QPixmap 객체를 만들어줬다면, 이제 QPixmap 객체에 이미지 파일을 불러오도록 하겠습니다.
QPixmap에 이미지파일을 불러올 때는 위와 같이 .load() 함수를 사용합니다.

![02.15.04 QPixmap Folder Example](https://wikidocs.net/images/page/38038/02.15.04_Folder_Example.png)
**02.15.04 QPixmap 객체에 이미지 불러오기 예시**

이 함수를 어떻게 사용하는지 예시를 들어보도록 하겠습니다. 만약 위의 사진과 같이 Python 코드와 동일한 폴더안에 있는 testImage.jpg라는 이미지 파일을 불러온다고 가정해봅시다. 이때 코드는 다음과 같이 작성할 수 있습니다.

```
#상대경로 이용
qPixmapVar.load("testImage.jpg")

#절대경로 이용
qPixmapVar.load("/Users/sebinlee/Desktop/Github/PyQt5forBeginner/02.15 Pixmap/testImage.jpg")
```

load함수를 사용할 때는 위의 코드와 같이 상대경로를 이용할 수도 있고, 아래의 코드와 같이 절대경로를 이용할 수도 있습니다. 상황에 따라 편리한 방법을 사용하시면 됩니다.

------

#### QPixmap 객체에 이미지 불러오기 - Web

> **qPixmapVar.loadFromData(사진 데이터가 있는 객체)**

QPixmap은 .loadFromData()라는 함수를 이용하여 웹이 있는 사진 파일도 불러올 수 있습니다.

우선 웹에 있는 사진 데이터를 가져오기 위해서 urllib라는 python 모듈을 사용하도록 하겠습니다. urllib에 대한 자세한 설명은 이 책의 뒷부분에서 하도록 하겠습니다.

urllib를 이용하여 웹에 있는 사진 데이터를 가져오고, 이를 QPixmap을 이용하여 보여주기 위해서는 다음의 단계를 거쳐야합니다.

> 1. urllib.request를 import합니다.
> 2. urllib.request의 urlopen().read() 함수를 이용하여 이미지 데이터를 가져온 후, 이를 객체를 만들어 저장합니다.
> 3. 2번에서 만든 객체와 .loadFromData()함수를 사용하여 QPixmap객체를 만듭니다.

우선 urllib.request를 import하고 urlopen().read()함수를 이용하여 이미지 데이터를 가져오는 것 부터 살펴보도록 하겠습니다.

**urllib를 이용하여 웹에서 사진데이터를 가져온 후 저장하는 코드**

```
import urllib.request

urlString = https://avatars1.githubusercontent.com/u/44885477?s=460&v=4
imageFromWeb = urllib.request.urlopen(urlString).read()
```

urllib.request.urlopen(url).read()는 url에 있는 데이터를 가져오는 역할을 하는 함수입니다. 위의 예시코드에서는 https://avatars1.githubusercontent.com/u/44885477?s=460&v=4 에 있는 사진을 가져오도록 하겠습니다. 이 링크는 필자 본인의 Github 프로필 사진입니다.

urllib.request.urlopen(url).read()로 이미지를 가져오기 위해서는 Web에서 가져올 이미지의 url을 문자열 객체로 저장한 후, 이를 urlopen()의 Parameter로 넣습니다. 그 후, 이렇게 읽어온 데이터를 객체를 만들어 저장해줍니다. 위의 코드에서는 imageFromWeb이 읽어온 사진 데이터를 저장하는 객체의 역할을 합니다.

**urllib로 가져온 사진데이터를 이용해 qPixmap객체에 이미지를 load하는 코드**

```
import urllib.request

qPixmapVar = QPixmap()
qPixmapVar.loadFromDate(imageFromWeb)
```

위에서 urllib로 사진데이터를 가져오고 그 데이터를 객체에 저장시켰다면, 이제는 그 객체를 이용하여 qPixmap객체를 초기화시켜보도록 하겠습니다. 우선 qPixmap 객체를 하나 만든 후, .loadFromData()함수로 qPixmap 객체에 이미지를 load합니다. 이때, .loadFromData()함수의 Parameter로는 사진의 데이터를 갖고 있는 imageFromWeb객체를 넣습니다. 그러면 qPixmap은 웹에서 가져온 데이터를 이용하여 이미지를 load하게 됩니다.

------

#### QPixmap을 Label에 표시하기

> **label이름.setPixmap(QPixmap 객체)**

위의 두가지 방법을 이용하여 QPixmap에 이미지를 load했다면, 이제 이 이미지를 화면에 표시해보도록 하겠습니다. qPixmap객체가 가지고 있는 이미지를 Label에 표시할 때는 위와 같이 .setPixmap함수를 이용하면 됩니다.

## QPixmap의 함수

이번에는 QPixmap이 가지고 있는 함수들과, QPixmap과 관련된 Label의 함수에 대해서 다뤄보도록 하겠습니다.

### 이미지의 Load/Save

| 함수                | 설명                                                         |
| :------------------ | :----------------------------------------------------------- |
| .load(fileName)     | 이미지 파일에서 이미지를 가져오는 함수입니다. Parameter로 이미지의 경로를 문자열의 형태로 입력해야 합니다. |
| .loadFromData(Data) | 이미지의 정보값을 갖고 있는 객체에서 이미지를 가져오는 함수입니다. Parameter로 이미지의 정보값을 갖고 있는 객체를 입력해야 합니다. 주로 Web에서 이미지를 가져올 때 많이 사용합니다. |
| .save(fileName)     | QPixmap이 가지고 있는 이미지를 파일로 저장할 때 사용합니다. Parameter로 파일의 이름을 문자열의 형태로 입력해야 합니다. |

### 이미지의 크기와 관련된 함수

이미지의 크기를 조절하고, 반환하는 함수들입니다. 이미지의 크기를 반환하는 함수는 [QSize](https://wikidocs.net/38260)라는 형식의 객체를 반환합니다. QSize에 대한 자세한 설명은 이 페이지의 하위페이지에 있는 [02.15.01 QSize](https://wikidocs.net/38260)의 설명을 읽어보시기 바랍니다.

| 함수                    | 설명                                                         |
| :---------------------- | :----------------------------------------------------------- |
| .scaled(width,height)   | 이미지의 가로, 세로 크기를 조절합니다. Parameter로 가로, 세로 크기값을 입력받습니다. 사진의 원본 비율을 무시하고 크기를 조정해야 할 때 사용하기 좋습니다. |
| .scaledToWidth(width)   | 이미지의 가로크기를 조절합니다. 이 함수를 이용할 경우 세로크기는 새로운 가로값에 따라서 자동으로 조절되며, 이미지의 가로세로 비율은 원본과 동일하게 유지됩니다. Parameter로 가로크기값을 입력합니다. |
| .scaledToHeight(height) | 이미지의 세로크기를 조절합니다. 이 함수를 이용할 경우, 가로크기는 새로운 세로값에 따라서 자동으로 조절되며, 이미지의 가로세로 비율은 원본과 동일하게 유지됩니다. Parameter로 세로크기값을 입력합니다. |
| .size()                 | QPixmap이 가지고 있는 이미지의 크기를 QSize형식으로 반환합니다. |

### Pixmap과 관련된 Label의 함수

Label에는 Pixmap과 관련된 함수들이 몇가지 존재합니다. 이 함수들에 대해서 알아보도록 하겠습니다.
이 부분에서 소개하는 함수들은 위에서 설명한 함수들과 다르게 함수 앞에 QPixmap객체를 붙이는 것이 아니라 label의 이름을 붙여야 합니다.

| 함수                | 설명                                                         |
| :------------------ | :----------------------------------------------------------- |
| .setPixmap(QPixmap) | Label의 영역에 사진을 표시합니다. Parameter로 표시할 이미지값을 가지고 있는 QPixmap객체가 필요합니다. |
| .pixmap()           | Label이 표시하고 있는 사진을 반환합니다. 반환된 값은 QPixmap객체의 형식을 가집니다. |

### 함수의 사용

![02.15.05 QPixmap Example](https://wikidocs.net/images/page/38038/02.15.05_QPixmap_Example.png)
**사진 02.15.05 QPixmap의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> - loadFromFile이라는 버튼을 클릭하면 같은 폴더에 있는 testImage.jpg라는 파일이 화면에 보여줍니다.
> - laodFromWeb이라는 버튼을 누르면 https://avatars1.githubusercontent.com/u/44885477?s=460&v=4 에 있는 사진을 화면에 보여줍니다.
> - SaveFromWeb이라는 버튼을 누르면 현재 화면에 보여지고 있는 사진이 SavedImage.jpg라는 이름으로 저장됩니다.
> - 모든 사진들은 화면에 표시되기 전, 가로크기를 600px로 변경한 후 화면에 표시됩니다.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
import urllib.request
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

form_class = uic.loadUiType("pixmapTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        self.btn_loadFromFile.clicked.connect(self.loadImageFromFile)
        self.btn_loadFromWeb.clicked.connect(self.loadImageFromWeb)
        self.btn_savePicture.clicked.connect(self.saveImageFromWeb)

    def loadImageFromFile(self) :
        #QPixmap 객체 생성 후 이미지 파일을 이용하여 QPixmap에 사진 데이터 Load하고, Label을 이용하여 화면에 표시
        self.qPixmapFileVar = QPixmap()
        self.qPixmapFileVar.load("testImage.jpg")
        self.qPixmapFileVar = self.qPixmapFileVar.scaledToWidth(600)
        self.lbl_picture.setPixmap(self.qPixmapFileVar)

    def loadImageFromWeb(self) :

        #Web에서 Image 정보 로드
        urlString = "https://avatars1.githubusercontent.com/u/44885477?s=460&v=4"
        imageFromWeb = urllib.request.urlopen(urlString).read()

        #웹에서 Load한 Image를 이용하여 QPixmap에 사진데이터를 Load하고, Label을 이용하여 화면에 표시
        self.qPixmapWebVar = QPixmap()
        self.qPixmapWebVar.loadFromData(imageFromWeb)
        self.qPixmapWebVar = self.qPixmapWebVar.scaledToWidth(600)
        self.lbl_picture.setPixmap(self.qPixmapWebVar)

    def saveImageFromWeb(self) :
        #Label에서 표시하고 있는 사진 데이터를 QPixmap객체의 형태로 반환받은 후, save함수를 이용해 사진 저장
        self.qPixmapSaveVar = self.lbl_picture.pixmap()
        self.qPixmapSaveVar.save("SavedImage.jpg")

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_() 
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.15.06 QPixmap Run](https://wikidocs.net/images/page/38038/02.15.06_QPixmap_Run.png)
**사진 02.15.06 QPixmap의 실행**

# 02.15.01 QSize

- [QSize](https://wikidocs.net/38260#qsize)
- [QSize의 함수](https://wikidocs.net/38260#qsize_1)

[QSize Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtCore/QSize.html?highlight=qsize)

## QSize

QSize란, 어떤 객체의 가로세로 크기값을 갖는 객체입니다. QSize 객체는 다음과 같이 생성할 수 있습니다.

```
sizeVar = QSize(width,height)
```

| 이름    | 설명                                  |
| :------ | :------------------------------------ |
| sizeVar | QSize의 객체이름을 의미합니다.        |
| width   | QSize가 가질 가로크기값을 의미합니다. |
| height  | QSize가 가질 세로크기값을 의미합니다. |

## QSize의 함수

QSize에는 QSize의 값을 가져오고, 변경할 수 있는 함수가 몇가지 있습니다. 이 함수들에 대해서 알아보겠습니다.

| 함수         | 설명                                                         |
| :----------- | :----------------------------------------------------------- |
| .width()     | QSize객체의 가로크기값을 반환합니다.                         |
| .height()    | QSize객체의 세로크기값을 반환합니다.                         |
| .toTuple()   | QSize객체가 갖고 있는 가로,세로크기를 튜플(Tuple)의 형식으로 반환합니다. |
| .setWidth()  | QSize객체의 가로크기값을 변경합니다. Parameter로 새로운 가로크기값을 입력받습니다. |
| .setHeight() | QSize객체의 세로크기값을 변경합니다. Parameter로 새로운 세로크기값을 입력받습니다. |



# 02.16 Display - QWebEngineView

- [QWebEngineView란 무엇인가?](https://wikidocs.net/37788#qwebengineview)
- QWebEngineView의 시그널과 함수
  - [QWebEngineView의 시그널](https://wikidocs.net/37788#qwebengineview_2)
  - [QWebEngineView의 함수](https://wikidocs.net/37788#qwebengineview_3)
  - [함수의 사용](https://wikidocs.net/37788#_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QWebEngineView Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWebEngineWidgets/QWebEngineView.html?highlight=qwebengineview)

이번 페이지의 예제코드는 위의 링크에 있는 02.16 WebEngineView 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QWebEngineView의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

본 페이지에서 소개할 QWebEngineView위젯은 PyQt5 5.11 버전부터는 32비트 시스템에서의 사용이 안됩니다. 32비트 시스템을 사용하시는 분들은 Anaconda Prompt, 혹은 Terminal에 아래의 코드를 입력하셔서 자신의 PyQt5 버전을 확인하시기 바랍니다.

```
from PyQt5.Qt import PYQT_VERSION_STR
print(PYQT_VERSION_STR)
```

## QWebEngineView란 무엇인가?

![02.16.01 QWebEngineView](https://wikidocs.net/images/page/37788/02.16.01_QWebEngineView.png)
**사진 02.16.01 QWebEngineView**

![02.16.02 QWebEngnieView Property](https://wikidocs.net/images/page/37788/02.16.02_QWebEngineView_Property.png)
**사진 02.16.02 QWebEngineView Property Editor**

QWebEngineView란, 웹페이지를 보여줄 수 있는 위젯을 의미합니다. QWebEngnieView는 위의 사진과 같이 url이라는 속성을 가지는데, 이 속성을 변경하여 원하는 웹페이지를 화면에 보여줄 수 있습니다.

## QWebEngineView의 시그널과 함수

QWebEngineView의 사용을 도와주는 시그널과 함수들에 대해서 알아보도록 하겠습니다.

### QWebEngineView의 시그널

QWebEngineView에서 새롭게 페이지를 로딩하거나, 웹페이지의 Url이 바뀌었을 때, 특정 기능을 수행하도록 하는 코드를 작성해보겠습니다.
QWebEngineView의 시그널은 다음과 같이 작성하며, 생성자의 내부에 작성해야 합니다.

> WebEngineView에서 새로운 웹페이지의 Load를 시작했을 때
> **self.WebEngineView이름.loadStarted.connect(함수)**
>
> WebEngineView에서 새로운 웹페이지를 Load 하는 중일때
> **self.WebEngineView이름.loadProgress.connect(함수)**
>
> WebEngineView에서 새로운 웹페이지의 Load를 끝냈을 때
> **self.WebEngineView이름.loadFinished.connect(함수)**
>
> WebEngineView의 url이 바뀌었을 때
> **self.WebEngineView이름.urlChanged.connect(함수)**

### QWebEngineView의 함수

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .back()           | 웹브라우저에서 사용할 수 있는 **이전 페이지로 이동** 기능입니다. |
| .forward()        | 웹브라우저에서 사용할 수 있는 **앞 페이지로 이동** 기능입니다. |
| .reload()         | 웹페이지를 **새로고침**합니다.                               |
| .stop()           | 웹페이지의 로딩을 **중단**합니다.                            |
| .load(url)        | QWebEngineView에 새로운 웹페이지를 로드합니다. Parameter로 새로운 웹페이지의 url이 QUrl의 형식으로 입력되어야 합니다. |
| .url().toString() | 현재 QWebEngnieView에서 보여주고 있는 사이트의 url을 문자열의 형태로 반환합니다. |

위의 .load()함수의 경우 Parameter로 QUrl형식의 객체를 입력해야합니다. 따라서 .load함수를 쓸때는 아래와 같이 사용해주셔야 합니다.

```
from PyQt5.QtCore import *
self.QWebEngineView이름.load(QUrl("주소(문자열)"))
```

### 함수의 사용

![02.16.03 QWebEngineVIew Example](https://wikidocs.net/images/page/37788/02.16.03_QWebEngineView_Example.png)
**사진 02.16.03 QWebEngineView의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> - 페이지가 로드되기 시작하면 "Start Loading", 페이지 로딩중에는 "Loading", 페이지 로드가 끝나면 "Load Finished"라는 글자를 출력합니다.
> - 페이지의 url이 바뀌면 바뀐 url을 하단에 있는 LineEdit에 자동으로 보여지게 합니다.
> - LineEdit에 새로운 Url을 입력하고, Go 버튼을 누르면 LineEdit에 입력된 URL로 이동합니다.
> - Back, Forward, Reload, Stop 버튼을 누르면 뒤로가기, 앞으로 가기, 새로고침, 중단 기능을 수행하빈다.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

form_class = uic.loadUiType("webEngineViewTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #WebEngineView의 시그널
        self.webEngineView_Test.loadStarted.connect(self.printLoadStart)
        self.webEngineView_Test.loadProgress.connect(self.printLoading)
        self.webEngineView_Test.loadFinished.connect(self.printLoadFinished)
        self.webEngineView_Test.urlChanged.connect(self.urlChangedFunction)

        #버튼들에 기능을 연결
        self.btn_setUrl.clicked.connect(self.urlGo)
        self.btn_back.clicked.connect(self.btnBackFunc)
        self.btn_forward.clicked.connect(self.btnForwardFunc)
        self.btn_reload.clicked.connect(self.btnRelaodFunc)
        self.btn_stop.clicked.connect(self.btnStopFunc)

    #WebEngineView의 시그널에 연결된 함수들
    def printLoadStart(self) : print("Start Loading")
    def printLoading(self) : print("Loading")
    def printLoadFinished(self) : print("Load Finished")

    def urlChangedFunction(self) :
        self.line_url.setText(self.webEngineView_Test.url().toString())
        print("Url Changed")

    #버튼을 눌렀을 때 실행될 함수들
    def urlGo(self) :
        self.webEngineView_Test.load(QUrl(self.line_url.text()))

    def btnBackFunc(self) :
        self.webEngineView_Test.back()

    def btnForwardFunc(self) :
        self.webEngineView_Test.forward()

    def btnRelaodFunc(self) :
        self.webEngineView_Test.reload()

    def btnStopFunc(self) :
        self.webEngineView_Test.stop()


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.16.04 QWebEngineView Run](https://wikidocs.net/images/page/37788/02.16.04_QWebEngineView_Run.png)
**사진 02.16.04 QWebEngineView의 실행**

# 02.17 Display - QProgressBar

- [QProgressBar란 무엇인가?](https://wikidocs.net/38037#qprogressbar)
- QProgressBar의 시그널과 함수
  - [QProgressBar의 시그널](https://wikidocs.net/38037#qprogressbar_2)
  - QProgressBar의 함수
    - [QProgressBar의 값에 관련된 함수](https://wikidocs.net/38037#qprogressbar_4)
    - [QProgressBar의 범위와 관련된 함수](https://wikidocs.net/38037#qprogressbar_5)
  - [함수의 이용](https://wikidocs.net/38037#_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QProgressBar Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QProgressBar.html?highlight=qprogressbar)

이번 페이지의 예제코드는 위의 링크에 있는 02.17 ProgressBar 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QProgressBar의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QProgressBar란 무엇인가?

![02.17.01 QProgressBar](https://wikidocs.net/images/page/38037/02.17.01_QProgressBar.png)
**사진 02.17.01 QProgressBar**

![02.17.02 QProgressBar Property](https://wikidocs.net/images/page/38037/02.17.02_QProgressBar_Property_Editor.png)
**사진 02.17.02 QProgressBar Property Editor**

QProgressBar란, 어떤 작업이 진행되고 있다는 것을 시각적으로 보여줄 수 있는 위젯을 의미합니다. 주로 프로그램이나 게임등을 설치할 때 많이 보셨을 겁니다. 이 QProgressBar는 minimum, maximum, value, orientation등의 속성을 가지고 있는데요, 이 속성들은 다음의 의미를 가집니다.

> - Minumum : 이 ProgressBar가 가질 수 있는 최소값을 의미합니다.
> - Maximum : 이 ProgressBar가 가질 수 있는 최대값을 의미합니다.
> - Value : 이 ProgressBar가 가지고 있는 값을 의미합니다.
> - Orientation : ProgressBar의 방향을 지정합니다. 가로(Horizontal), 세로(Vertical)을 선택할 수 있습니다.

![02.17.03 QProgressBar SameMinMax](https://github.com/SebinLee/PyQt5forBeginner/blob/master/etc/02.17.03%20QProgressBar%20SameMinMax.gif?raw=true)
**사진 02.17.03 QProgressBar의 Minimum, Maximum이 동일할 때**

QProgressBar는 독특한 특징이 있는데요, 위의 속성들 중 Minimum과 Maximum이 동일할 경우 위의 사진과 같이 독특한 움직임을 하게 됩니다. 이러한 ProgressBar의 움직임은 주로 프로그램을 설치할 때, 설치작업이 끝날 때까지 얼마나 남았는지 잘 모를때 많이 사용합니다. 즉, 작업이 얼마나 남았는지 잘 몰라 위의 사진과 같은 움직임을 보여주고 싶을때는 Minimum과 Maximum을 동일하게 설정하면 됩니다.

## QProgressBar의 시그널과 함수

QProgressBar의 사용을 도와주는 시그널과 함수들에 대해서 알아보도록 하겠습니다.

### QProgressBar의 시그널

QProgressBar의 값이 변경되었을 때, 특정 기능을 수행하도록 하는 코드를 작성해보겠습니다.
QProgressBar의 시그널은 다음과 같이 작성하며, 생성자의 내부에 작성해야 합니다.

> QProgressBar의 값이 변경되었을 때 **self.ProgressBar이름.valueChanged.connect(함수)**

### QProgressBar의 함수

#### QProgressBar의 값에 관련된 함수

| 함수             | 설명                                                         |
| :--------------- | :----------------------------------------------------------- |
| .value()         | QProgressBar가 가지고 있는 현재값을 반환합니다.              |
| .setValue(Value) | QProgressBar에 새로운 Value값을 설정합니다. Parameter로 새로운 Value값이 필요합니다. |
| .reset()         | QProgressBar의 Value값을 초기화합니다. 이때 Value값은 QProgressBar의 최솟값 - 1을 한 값으로 초기화됩니다. |

#### QProgressBar의 범위와 관련된 함수

| 함수               | 설명                                                         |
| :----------------- | :----------------------------------------------------------- |
| .minimum()         | QProgressBar의 최솟값을 반환합니다.                          |
| .maximum()         | QProgressBar의 최댓값을 반환합니다.                          |
| .setMinimum(min)   | QProgressBar의 최솟값을 새로 설정합니다. Parameter로 새로운 최솟값이 필요합니다. |
| .setMaximum(min)   | QProgressBar의 최솟값을 새로 설정합니다. Parameter로 새로운 최댓값이 필요합니다. |
| .setRange(min,max) | QProgressBar의 최솟값과 최댓값을 새로 설정합니다. Parameter로 새로운 최솟값, 최댓값이 필요합니다. |

### 함수의 이용

![02.17.03 QProgressBar Example](https://wikidocs.net/images/page/38037/02.17.03_QProgressBar_Example.png)
**02.17.03 QProgressBar 예시**

위의 사진처럼 1초마다 ProgressBar의 값이 증가하고, ProgressBar의 값이 바뀔 때 마다 ProgressBar의 값을 출력하도록 코드를 작성해보도록 하겠습니다. 시간의 경과를 체크하기 위해서 [QTimer](https://wikidocs.net/38522)라는 객체를 사용하도록 하겠습니다. QTimer에 대한 설명은 이 페이지의 하위페이지에 있는 02.17.01 QTimer를 참고해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

form_class = uic.loadUiType("progressbarTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #ProgressBar의 시그널
        self.progressBar_Test.valueChanged.connect(self.printValue)

        #QTimer를 이용하여 매초마다 ProgressBar의 값이 1씩 늘어나게 설정합니다.
        #QTimer의Interval을 1000으로 설정한 후, ProgrssBar의 값이 늘어나게 하는 함수를 연결하고 QTimer를 시작합니다.
        #QTimer에 대한 설명은 02.17.01 QTimer에서 보실 수 있습니다.
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.progressBarTimer)
        self.timerVar.start()

    def progressBarTimer(self) :
        self.time = self.progressBar_Test.value()
        self.time += 1
        self.progressBar_Test.setValue(self.time)

        #ProgressBar의 값이 최댓값 이상이 되면 Timer를 중단시켜 ProgressBar의 값이 더이상 증가하지 않게 합니다.
        if self.time >= self.progressBar_Test.maximum() :
            self.timerVar.stop()

    def printValue(self) :
        print(self.progressBar_Test.value())

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

QTimer 페이지에서 설명된 대로 QTimer 객체를 만들고, interval을 1000(1초)로 설정하고, timeout.connect()함수로 함수를 연결한 후 QTimer객체를 작동시킵니다. QTimer 페이지에서 설명한것과 유사하게 progressBar의 값을 바꿀 때도 변수를 하나 생성하여 기존의 progressBar의 값을 저장시킨 후, 그 변수값을 1 증가시켜 progressBar의 값으로 설정합니다. 이러한 방법을 이용하여 매 초마다 ProgressBar가 증가하게 만들 수 있습니다.

또한, 시그널을 이용하여 매 초마다 progressBarTimer() 함수에 의해 progressBar의 값이 변경되면 progressBar의 현재 값이 print 되도록 코드를 작성할 수 있습니다.

이 코드를 작동시키면 아래와 같이 실행됩니다.
![02.17.04 QProgressBar Run](https://wikidocs.net/images/page/38037/02.17.04_QProgessBar_Run.png)
**사진 02.17.04 QProgressBar의 실행**



# 02.17.01 QTimer

- [QTimer](https://wikidocs.net/38522#qtimer)
- QTimer의 함수
  - [QTimer의 조작과 관련된 함수](https://wikidocs.net/38522#qtimer_2)
  - [QTimer의 Interval과 관련된 함수](https://wikidocs.net/38522#qtimer-interval)
  - [QTimer의 상태반환](https://wikidocs.net/38522#qtimer_3)
  - [함수의 사용](https://wikidocs.net/38522#_1)

[QTimer Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtCore/QTimer.html?highlight=qtimer)

## QTimer

QTimer란, PyQt에서 시간의 경과를 체크할 수 있는 객체입니다. QTimer는 아래와 같이 생성합니다.

```
from PyQt.QtCore import *
timerVar = QTimer()
```

## QTimer의 함수

QTimer의 함수는 QTimer의 시작/종료와 관련된 함수, Interval에 관련된 함수, QTimer의 상태를 반환하는 함수 등으로 구분할 수 있습니다.

### QTimer의 조작과 관련된 함수

| 함수         | 설명                                                         |
| :----------- | :----------------------------------------------------------- |
| .start()     | QTimer가 시간을 체크하기 시작합니다.                         |
| .start(msec) | QTimer가 Parameter만큼의 시간이 지난 후 시간을 체크하기 시작합니다. Parameter로 시간을 입력해야 하며, 단위는 ms입니다. |
| .stop()      | QTimer를 중지합니다.                                         |

### QTimer의 Interval과 관련된 함수

| 함수                   | 설명                                                         |
| :--------------------- | :----------------------------------------------------------- |
| .setInterval(msec)     | QTimer의 Interval을 설정합니다. Parameter로 Interval을 ms단위로 입력합니다. |
| .timeout.connect(함수) | 매 Interval마다 어떤 함수를 실행할지를 결정합니다. 만약, setInterval 함수로 Interval을 설정하지 않은 경우, 1ms마다 함수가 반복됩니다. |

위의 표에서 Interval이라는 용어가 나옵니다. Interval이란 QTimer에서 일정 간격마다 어떤 함수를 실행하게 할 수 있는데, 그 일정 간격을 Interval이라고 합니다. 만약 setInterval(1000)을 사용한 후 timeout.connect()를 사용하면 1초마다 함수가 실행되게 할 수 있습니다.

### QTimer의 상태반환

| 함수        | 설명                                                         |
| :---------- | :----------------------------------------------------------- |
| .interval() | QTimer의 Interval을 반환합니다.                              |
| .isActive() | QTimer가 작동중인지 체크합니다. QTimer가 작동중이면 True를, 멈춰있으면 False를 반환합니다. |

### 함수의 사용

QTimer가 시작된 후 경과한 시간을 출력하는 코드를 만들어보도록 하겠습니다.

```
from PyQt5.QtCore import *

time = 0

def printTime() :
  time += 1
  print(time)

timerVar = QTimer()
timerVar.setInterval(1000)
timerVar.timeout.connect(printTime)
timerVar.start()
```

위의 코드를 살펴보면 우선 경과한 시간을 저장할 time이라는 변수를 만들고, 매 초마다 실행될 printTime()이라는 함수를 만듭니다. 그리고 QTimer의 Interval을 1000으로 설정하고 .timeout.connect()함수를 이용하여 1초마다 printTime함수를 실행시키도록 합니다. 그 후 QTimer를 시작하면 매초마다 time 변수의 값이 1씩 증가하여 출력될 것입니다. 이렇게 시간의 경과를 확인할 수 있습니다.

# 02.18 Item Widgets - List Widget

- [QListWidget이란 무엇인가?](https://wikidocs.net/35496#qlistwidget)
- QListWidget의 속성 살펴보기
  - [DragDrop / DropAction](https://wikidocs.net/35496#dragdrop-dropaction)
- QListWidget의 함수
  - [QListWidget의 시그널](https://wikidocs.net/35496#qlistwidget_3)
  - QListWidget의 함수
    - [항목의 반환](https://wikidocs.net/35496#_1)
    - [항목의 추가, 삭제](https://wikidocs.net/35496#_2)
    - [함수의 사용](https://wikidocs.net/35496#_3)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QListWidget Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QListWidget.html?highlight=qlistwidget)

이번 페이지의 예제코드는 위의 링크에 있는 02.18 List Widget 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QListWidget의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QListWidget이란 무엇인가?

![02.18.01 QListWidget](https://wikidocs.net/images/page/35496/02.18.01_ListWidget.png)
**사진 02.18.01 QListWidget**

QListWidget이란, 위의 사진과 같이 항목들이 목록의 형태로 나열되는 위젯을 의미합니다. QListWidget의 모든 항목들은 몇번째 줄에 위치해 있는지 값을 갖게 되는데요, 가장 위에서부터 0번째 줄, 밑으로 갈 수록 1번째, 2번째 줄이 됩니다. QComboBox에서의 Index와 유사한 개념이라고 생각하시면 됩니다.

## QListWidget의 속성 살펴보기

![02.18.02 QListWidget Property Editor](https://wikidocs.net/images/page/35496/02.18.02_ListWidget_Property.png)
**사진 02.18.02 QListWidget Property Editor**

QListWidget은 QAbstractItemView를 상속받는 함수로, Property Editor에서 QAbstractItemView의 속성 몇가지를 수정할 수 있습니다. 위의 속성들 중, 자주 사용할만한 일부 속성을 소개해드리도록 하겠습니다.

> - autoScroll : ListWidget에 항목이 많아졌을 때, 자동으로 스크롤을 할 수 있도록 만들어줍니다.
> - dragEnabled : ListWidget의 항목을 드래그 할 수 있게 할지를 결정합니다.
> - dragDropMode : Drag & Drop을 허용할지, Drag만 허용할지 등을 결정합니다다.
> - defaultDropAction : Drop했을 때, 어떤 행동을 할지를 결정합니다. 기본값은 CopyAction입니다.
> - alternatingRowColor : 줄을 구분하기 쉽게 짝수번째 줄의 배경색을 변경합니다.
> - selectionMode : 선택 모드를 결정합니다. 기본값은 SingleSelection이며, MultiSelection등을 선택할 수 있습니다.

위에서 언급한 속성 중, dragDropMode, defaultDropAction은 어떤 속성인지 잘 이해가 되지 않으실 수 있습니다.
이 속성에 대해서 자세히 알아보겠습니다.

### DragDrop / DropAction

![02.18.03 DragDropMode](https://wikidocs.net/images/page/35496/02.18.03_DragDropMode.png)
**사진 02.18.03 DragDropMode**

위의 사진은 DragDropMode가 사용할 수 있는 선택지입니다. 이 중, 유용하게 사용할 수 있는 선택지는 NoDragDrop, DragDrop, InternalMove가 있습니다. 이 선택지들의 의미는 다음과 같습니다.

> - NoDragDrop : Drag & Drop을 허용하지 않습니다.
> - InternalMove : 항목을 끌어다가 다른 위치에 이동시키는 것을 허용합니다. 끌어다 놓을 때의 행위를 Drop으로 간주하지는 않습니다.
> - DragDrop : Drag & Drop을 허용합니다. Drop할 때(항목을 다른곳에 끌어다 놓을 때) 어떤 행동을 할지는 DefaultDropAction에서 지정합니다.

NoDragDrop을 사용하면 항목을 끌어서 이동시키는 행위를 아예 허용하지 않을 수 있으며, InternalMove를 사용하면 항목을 끌어다가 다른 곳에 위치시키는 행동만 허용합니다. 그리고 DragDrop을 사용하면 어떤 항목을 끌어다 놨을 때, 특정한 행위를 하도록 설정할 수 있습니다. 이때 실행할 특정한 행위는 DefaultDropAction에서 지정하는데요, DefaultDropAction은 어떠한 선택지가 있는지 알아보도록 하겠습니다.

![02.18.04 DefaultDropAction](https://wikidocs.net/images/page/35496/02.18.04_DropAction.png)
**사진 02.18.04 DefaultDropAction**

위의 사진은 DefaultDropAction이 사용할 수 있는 선택지입니다. DragDropMode를 DragDrop으로 하고 항목을 다른곳으로 끌어다 놓았을때 어떤 행동을 할 지를 지정합니다. 보통은 항목을 복사하는 CopyAction, 항목을 이동하는 MoveAction을 주로 사용합니다.

## QListWidget의 함수

QListWidget이 가지고 있는 시그널, 그리고 함수들에 대해서 다뤄보도록 하겠습니다.

### QListWidget의 시그널

상단에 있는 QListWidget의 Documentation을 보시면 QListWidget에는 상당히 많은 시그널이 있는 것을 보실 수 있습니다. 그 많은 시그널 중, 유용하게 쓰일 수 있는 시그널 몇개를 소개해드리도록 하겠습니다.

다른 시그널들과 마찬가지로, QListWidget의 시그널 또한 생성자 부분에 위치해야 합니다.

> QListWidget의 항목이 클릭되었을 때 기능 실행
> **self.ListWidget이름.itemClicked.connect(함수)**
>
> QListWidget의 항목이 더블클릭되었을 때 기능 실행
> **self.ListWidget이름.itemDoubleClicked.connect(함수)**
>
> QListWidget에서 다른 항목이 선택되었을 때
> **self.ListWidget이름.currentItemChanged.connect(함수)**

### QListWidget의 함수

이제 QListWidget의 함수들에 대해서 알아보도록 하겠습니다. 아래에서 설명할 함수들 중, QListWidgetItem 형식의 객체를 반환하는 함수들이 몇가지 존재할겁니다. QListWidgetItem 객체는 ListWidget의 항목의 값을 갖고 있는 객체로, **QListWidgetItem 객체가 가지고 있는 ListWidget의 항목을 문자열로** 가져오고 싶다면 다음과 같은 코드를 사용할 수 있습니다.

> **QListWidgetItem객체.text()**

#### 항목의 반환

| 함수           | 설명                                                         |
| :------------- | :----------------------------------------------------------- |
| .currentRow()  | ListWidget에서 선택한 항목이 몇번째 항목인지를 반환합니다.   |
| .currentItem() | ListWidget에서 선택한 항목을 반환합니다. 이때, 반환된 값은 QListWidgetItem의 객체입니다. |
| .item(row)     | ListWidget에서 Row번째 항목을 반환합니다. 이때, 반환된 값은 QListWidgetItem의 객체입니다. |

#### 항목의 추가, 삭제

| 함수                     | 설명                                                         |
| :----------------------- | :----------------------------------------------------------- |
| .addItem(String)         | ListWidget에 새로운 항목을 추가합니다. Parameter로 추가할 항목의 문자열을 입력합니다. |
| .insertItem(row, String) | ListWiget의 row번째에 새로운 항목을 추가합니다. Parameter로 추가할 위치와 추가할 항목의 문자열을 입력합니다. |
| .takeItem(row)           | ListWidget의 row번째 항목을 삭제합니다. 삭제된 항목은 QListWidgetItem의 형태로 반환됩니다. |
| .clear()                 | ListWidget의 모든 항목을 삭제합니다.                         |

#### 함수의 사용

![02.18.05 ListWidget Example](https://wikidocs.net/images/page/35496/02.18.05_ListWidget_Example.png)
**사진 02.18.05 QListWidget의 활용**

위에서 설명한 함수들을 실제로 코드에서 어떻게 사용하는지 한번 알아보도록 하겠습니다. 위의 사진에 있는 프로그램은 아래와 같은 기능을 가지고 있습니다.

> **시그널과 관련된 기능**
>
> - 항목을 클릭하면 항목의 글자를 출력합니다.
> - 항목을 더블클릭하면 항목의 줄 번호와 글자를 출력합니다.
> - 다른 항목을 선택하면 선택한 항목의 줄 번호를 출력합니다.
>
> **함수와 관련된 기능**
>
> - Add 버튼을 누르면 왼쪽에 있는 LineEdit에 입력된 글자가 ListWidget에 추가됩니다.
> - Inset 버튼을 누르면 LineEdit에 입력된 글자가 Spinbox에 표시된 줄번호에 삽입됩니다.
> - Print 버튼을 누르면 현재 선택한 항목을 출력합니다.
> - Print_Multi 버튼을 누르면 선택한 여러개의 항목들을 동시에 출력합니다.
> - Remove 버튼을 누르면 선택한 항목을 삭제합니다.
> - Clear 버튼을 누르면 ListWidget의 항목을 모두 삭제합니다.

이 기능들을 어떻게 구현했는지 코드를 한번 살펴보도록 하겠습니다. 위젯들의 ObjectName은 예제코드를 다운받아 확인해주시기 바랍니다.

```
import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

form_class = uic.loadUiType("listwidgetTest.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        #ListWidget의 시그널
        self.listWidget_Test.itemClicked.connect(self.chkItemClicked)
        self.listWidget_Test.itemDoubleClicked.connect(self.chkItemDoubleClicked)
        self.listWidget_Test.currentItemChanged.connect(self.chkCurrentItemChanged)

        #버튼에 기능 연결
        self.btn_addItem.clicked.connect(self.addListWidget)
        self.btn_insertItem.clicked.connect(self.insertListWidget)

        self.btn_printItem.clicked.connect(self.printCurrentItem)
        self.btn_printMultiItems.clicked.connect(self.printMultiItems)
        self.btn_removeItem.clicked.connect(self.removeCurrentItem)
        self.btn_clearItem.clicked.connect(self.clearItem)

    #ListWidget의 시그널에 연결된 함수들
    def chkItemClicked(self) :
        print(self.listWidget_Test.currentItem().text())

    def chkItemDoubleClicked(self) :
        print(str(self.listWidget_Test.currentRow()) + " : " + self.listWidget_Test.currentItem().text())

    def chkCurrentItemChanged(self) :
        print("Current Row : " + str(self.listWidget_Test.currentRow()))

    #항목을 추가, 삽입하는 함수들
    def addListWidget(self) :
        self.addItemText = self.line_addItem.text()
        self.listWidget_Test.addItem(self.addItemText)

    def insertListWidget(self) :
        self.insertRow = self.spin_insertRow.value()
        self.insertText = self.line_insertItem.text()
        self.listWidget_Test.insertItem(self.insertRow, self.insertText)

    #Button Function
    def printCurrentItem(self) :
        print(self.listWidget_Test.currentItem().text())

    def printMultiItems(self) :
        #여러개를 선택했을 때, selectedItems()를 이용하여 선택한 항목을 List의 형태로 반환받습니다.
        #그 후, for문을 이용하여 선택된 항목을 출력합니다.
        #출력할 때, List안에는 QListWidgetItem객체가 저장되어 있으므로, .text()함수를 이용하여 문자열로 변환해야 합니다.
        self.selectedList = self.listWidget_Test.selectedItems()
        for i in self.selectedList :
            print(i.text())

    def removeCurrentItem(self) :
        #ListWidget에서 현재 선택한 항목을 삭제할 때는 선택한 항목의 줄을 반환한 후, takeItem함수를 이용해 삭제합니다. 
        self.removeItemRow = self.listWidget_Test.currentRow()
        self.listWidget_Test.takeItem(self.removeItemRow)

    def clearItem(self) :
        self.listWidget_Test.clear()


if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
```

다른 위젯들과 마찬가지로 생성자 안에서 기능을 연결하고, 함수의 형태로 기능을 구성하는 것을 볼 수 있습니다. 위의 예제 코드를 실행하면 아래와 같이 실행되는 것을 볼 수 있습니다.
![02.18.06 QListWidget Run](https://wikidocs.net/images/page/35496/02.18.06_ListWidget_Run.png)
**사진 02.18.06 QListWidget의 실행**

# 02.19 Item Widgets - Table Widget

- [QTableWidget이란 무엇인가?](https://wikidocs.net/35498#qtablewidget)
- QTableWidget의 속성 살펴보기
  - [QAbstractItemView의 속성](https://wikidocs.net/35498#qabstractitemview)
  - [QTableView의 속성](https://wikidocs.net/35498#qtableview)
  - [QTableWidget의 속성](https://wikidocs.net/35498#qtablewidget_2)
- QTableWidget의 시그널과 함수
  - [QTableWidget의 시그널](https://wikidocs.net/35498#qtablewidget_4)
  - QTableWidget의 함수
    - [현재상태의 반환](https://wikidocs.net/35498#_1)
    - [항목의 추가, 삭제와 관련된 함수](https://wikidocs.net/35498#_2)
    - [Table의 행과 열에 관련된 함수](https://wikidocs.net/35498#table)
    - [Table의 열의 Header에 관한 함수](https://wikidocs.net/35498#table-header)
    - [Table의 행의 Header에 관한 함수](https://wikidocs.net/35498#table-header_1)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)
[QTableWidget Documentation - Qt for Python](https://doc.qt.io/qtforpython/PySide2/QtWidgets/QTableWidget.html?highlight=qtablewidget)

이번 페이지의 예제코드는 위의 링크에 있는 02.19 Table Widget 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다. QTableWidget의 보다 자세한 정보는 위의 링크에 있는 Documentation에서 확인하실 수 있습니다.

## QTableWidget이란 무엇인가?

![02.19.01 QTableWidget](https://wikidocs.net/images/page/35498/02.19.01_QTableWidget.png)
**사진 02.19.01 QTableWidget**

QTableWIdget이란, 위의 사진과 같이 항목들이 표의 형태로 나열되는 위젯을 의미합니다. QTableWidget에서는 행과 열의 개념을 확실히 알아두어야 하는데요, **행이란 몇번째 줄을, 열은 몇번째 칸을 의미합니다.** 그리고 QListWidget과 유사하게 행은 가장 위에서부터 0번째, 1번째, 2번째 행이 되며, 열은 가장 왼쪽에서부터 0번째, 1번째, 2번째 열이 됩니다.

![02.19.03 TableWidget Edit](https://wikidocs.net/images/page/35498/02.19.03_TableWidget_Edit.png)
**사진 02.19.03 TableWidget Edit**

QTableWidget은 Qt Designer에서 더블클릭하여 항목을 추가할 수 있습니다. Columns, Row는 행과 열의 Header를 설정하며, Item에서는 Cell에 항목을 추가할 수 있습니다. 만약 Columns와 Row에서 Header를 설정하지 않았다면, 행과 열의 Header에는 기본적으로 1,2,3,4와 같은 숫자가 입력됩니다.

여기서 Header란 첫번째 사진에서의 [학번, 학부], [이세빈, 김민수, 홍길동, 이석준]과 같은 행과 열의 제목을, Cell은 첫번째 사진에서의 [20170001, 글로벌미디어..., 20170002, 전자정보공학부]등이 쓰여있는 TableWidget에서 만들어진 칸들을 의미합니다.

## QTableWidget의 속성 살펴보기

![02.19.02 QTableWidget Property Editor](https://wikidocs.net/images/page/35498/02.19.02_QTableWidget_Property.png)
**사진 02.19.02 QListWidget Property Editor**

QTableWidget은 QListWidget과 마찬가지로 QAbstractItemView를 상속받는 위젯으로, QListWidget과 동일하게 Property Editor에서 QAbstractItemView의 속성을 수정할 수 있습니다. 하지만 QTableWidget에서는 QListWidget에 비해서 몇가지 추가로 수정할 속성이 존재하니 속성에 대한 설명을 주의깊게 읽어주시기 바랍니다.

### QAbstractItemView의 속성

> - autoScroll : ListWidget에 항목이 많아졌을 때, 자동으로 스크롤을 할 수 있도록 만들어줍니다.
> - dragEnabled : ListWidget의 항목을 드래그 할 수 있게 할지를 결정합니다.
> - dragDropMode : Drag & Drop을 허용할지, Drag만 허용할지 등을 결정합니다다.
> - defaultDropAction : Drop했을 때, 어떤 행동을 할지를 결정합니다. 기본값은 CopyAction입니다.
> - alternatingRowColor : 줄을 구분하기 쉽게 짝수번째 줄의 배경색을 변경합니다.
> - selectionMode : 선택 모드를 결정합니다. 기본값은 ExtendedSelection이며, MultiSelection등을 선택할 수 있습니다.
> - selectionBehavior : 선택 방법을 결정합니다. 기본값은 SelectItems이며, SelectRow, SelectColumns등을 선택할 수 있습니다.

위의 항목들 중 selectionBehavior를 제외한 속성들은 ListWidget과 겹치는 부분이므로, 해당 속성들의 설명은 [02.18 Item Widgets - List Widget](https://wikidocs.net/35496#qlistwidget_1)을 참고해주시기 바랍니다.

TableWidget에서는 상황에 따라서 selectionBehavior라는 속성을 설정해야 하는데요, selectionBehavior는 어떤 항목을 클릭했을 때 클릭한 항목만 선택할지 혹은 선택한 항목과 같은 행, 같은 열에 있는 모든 항목을 선택할지를 결정하는 속성입니다. 만약 어떤 항목을 클릭했을 때 해당 항목만 선택되게 하고 싶으면 SelectItems를, 같은 행의 모든 항목을 선택하고 싶다면 SelectRow, 같은 열의 모든 항목을 선택하고 싶다면 SelectColumns를 선택하면 됩니다.

### QTableView의 속성

> - showGrid : 표의 선을 보여줄지를 결정합니다.
> - gridStyle : showGrid를 체크했을 때, 표의 선을 어떤 모양으로 할지를 결정합니다. 기본값은 SolidLine(실선) 입니다.

### QTableWidget의 속성

> - rowCount : QTableWidget의 행의 개수를 결정합니다. 기본값은 0입니다.
> - columnCount : QTableWidget의 열의 개수를 결정합니다.

QTableWidget은 반드시 Property Editor에서 rowCount를 변경해주어야 합니다. 변경하지 않을 경우, rowCount(행의 개수)가 0이 되어 TableWidget에 항목을 추가할 수 없습니다.

## QTableWidget의 시그널과 함수

### QTableWidget의 시그널

> TableWidget의 Cell의 내용이 바뀌었을 때 기능 실행
> **self.TableWidget이름.cellChanged.connect(함수)**
>
> TableWidget에서 선택된 Cell이 바뀌었을 때 기능 실행
> **self.TableWidget이름.currentCellChanged.connect(함수)**
>
> TableWidget에서 Cell이 클릭 되었을 때 기능 실행
> **self.TableWidget이름.cellClicked.connect(함수)**
>
> TableWidget에서 Cell이 더블클릭 되었을 때 기능 실행
> **self.TableWidget이름.cellDoubleClicked.connect(함수)**

### QTableWidget의 함수

QTableWidget의 함수들 중에는 QTableWidgetItem 형식의 객체를 반환하고, 입력받는 함수들이 많습니다. QTableWidgetItem은 TableWidget의 항목값을 갖고 있는 객체로, 다음과 같이 생성하고 문자열을 수정 및 반환할 수 있습니다.

```
#QTableWidgetItem객체의 생성
qTableWidgetItemVar = QTableWidgetItem("항목이 가질 문자열 입력")

#QTableWidgetItem객체의 문자열 반환
qTableWidgetItemVar.text()

#QTableWidgetItem객체의 문자열 수정
qTableWidgetItemVar.setText("새로운 문자열")
```

#### 현재상태의 반환

| 함수              | 설명                                                         |
| :---------------- | :----------------------------------------------------------- |
| .item(row, col)   | row번째 줄, col번째 열의 항목을 반환합니다. 이때, 반환된 항목은 QTableWidgetItem의 형식으로 반환됩니다. |
| .currentItem()    | 현재 선택하고 있는 항목을 반환합니다. 이때, 반환된 항목은 QTableWidgetItem의 형식으로 반환됩니다. |
| .currentRow()     | 현재 선택하고 있는 항목의 행을 반환합니다.                   |
| .currentColumn()  | 현재 선택하고 있는 항목의 열을 반환합니다.                   |
| .selectedItems()  | 선택한 항목들을 리스트 형식으로 반환합니다. 리스트 안에는 선택된 항목들이 QTableWidgetItem의 형식으로 포함되어 있습니다. |
| .selectedRanges() | 현재 선택한 범위를 QTableWidgetSelectionRange의 형식으로 반환합니다. |

#### 항목의 추가, 삭제와 관련된 함수

| 함수                   | 설명                                                         |
| :--------------------- | :----------------------------------------------------------- |
| .setItem(row,col,item) | row번째 줄, col번째 칸에 Item이라는 항목을 추가합니다. 이때, Item은 QTableWidgetItem 형식의 객체여야 합니다. |
| .takeItem(row,col)     | row번째 줄, col번째 칸에 있는 항목을 삭제합니다. 이때, 삭제된 항목은 QTableWidgetItem형식의 객체로 반환됩니다. |
| .clear()               | 행과 열의 Header를 포함한 모든 항목을 삭제합니다.            |
| .clearContents()       | 행과 열의 Header를 제외한 모든 항목을 삭제합니다.            |

#### Table의 행과 열에 관련된 함수

| 함수                  | 설명                                                 |
| :-------------------- | :--------------------------------------------------- |
| .currentColumnCount() | 현재 Table Widget에 존재하는 열의 개수를 반환합니다. |
| .currentRowCount()    | 현재 Table Widget에 존재하는 행의 개수를 반환합니다. |
| .setColumnCount(col)  | 현재 Table Widget의 열의 개수를 col개로 설정합니다.  |
| .setRowCount(row)     | 현재 Table Widget의 행의 개수를 row개로 설정합니다.  |

#### Table의 열의 Header에 관한 함수

![02.19.01 QTableWidget](https://wikidocs.net/images/page/35498/02.19.01_QTableWidget.png)
**사진 02.19.01 QTableWidget**

열의 Header란, 위의 사진에서 학번, 학부에 해당하는 것으로, 열이 가지고 있는 제목을 의미합니다.

| 함수                                | 설명                                                         |
| :---------------------------------- | :----------------------------------------------------------- |
| .horizontalHeaderItem(col)          | col번째 열의 Header를 QTableWidgetItem 형식의 객체로 반환합니다. |
| .takeHorizontalHeader(col)          | col번째 열의 Header를 삭제합니다. 이때 삭제된 항목은 QTableWidgetItem형식의 객체로 반환됩니다. |
| .setHorizontalHeaderItem(col, item) | col번째 열의 Header를 Item이 가지고 있는 글자로 바꿉니다. 이때, Item은 QTableWidgetItem 형식의 객체여야 합니다. |
| .setHorizontalHeaderLabels(List)    | 열들의 Header를 일괄적으로 변경합니다. Parameter로 새로운 Header가 될 문자열이 들어있는 List가 필요하며, List의 0번째 항목이 0번째 열의 Header가 됩니다. |

#### Table의 행의 Header에 관한 함수

![02.19.01 QTableWidget](https://wikidocs.net/images/page/35498/02.19.01_QTableWidget.png)
**사진 02.19.01 QTableWidget**

행의 Header란, 위의 사진에서 이세빈, 김민수, 홍길동, 이석준에 해당하는 것으로, 행이 가지고 있는 제목을 의미합니다.

| 함수                              | 설명                                                         |
| :-------------------------------- | :----------------------------------------------------------- |
| .verticalHeaderItem(row)          | row번째 줄의 Header를 QTableWidgetItem 형식의 객체로 반환합니다. |
| .takeVerticalHeader(row)          | row번째 줄의 Header를 삭제합니다. 이때 삭제된 항목은 QTableWidgetItem형식의 객체로 반환됩니다. |
| .setVerticalHeaderItem(row, item) | row번째 줄의 Header를 Item이 가지고 있는 글자로 바꿉니다. 이때, Item은 QTableWidgetItem 형식의 객체여야 합니다. |
| .setVerticalHeaderLabels(List)    | 행들의 Header를 일괄적으로 변경합니다. Parameter로 새로운 Header가 될 문자열이 들어있는 List가 필요하며, List의 0번째 항목이 0번째 행의 Header가 됩니다. |



# 02.20 Container

- [Container란 무엇인가?](https://wikidocs.net/35499#container)
- [GroupBox](https://wikidocs.net/35499#groupbox)
- [ScrollArea](https://wikidocs.net/35499#scrollarea)
- [Tab Widget](https://wikidocs.net/35499#tab-widget)

[초보자를 위한 Python GUI 프로그래밍 예제코드](https://github.com/SebinLee/PyQt5forBeginner)

이번 페이지의 예제코드는 위의 링크에 있는 02.20 Container 폴더에 있습니다. 자유롭게 다운받아서 사용하실 수 있습니다.

## Container란 무엇인가?

![02.20.01 Container](https://wikidocs.net/images/page/35499/02.20.01_Container.png)
**사진 02.20.01 Container**

Container란, 다른 위젯들을 포함하는 틀을 의미합니다. 이 페이지에서는 Qt Designer에서 제공하는 Container들 중 GroupBox, ScrollArea, Tab Widget을 소개하도록 하겠습니다.

## GroupBox

![02.20.02 GroupBox](https://wikidocs.net/images/page/35499/02.20.02_GroupBox.png)
**사진 02.20.02 GroupBox**

GroupBox는 여러 위젯들을 하나의 상자 안에 넣을 수 있는 Container를 의미합니다. GroupBox의 크기는 다른 위젯과 같이 조절할 수 있습니다. GroupBox에 넣은 위젯은 Horizontal Layout, Vertical Layout과 다르게 위치가 자동으로 정렬되지 않으며, 위의 사진과 같이 자유롭게 배치가 가능합니다.

## ScrollArea

![02.20.03 ScrollArea](https://wikidocs.net/images/page/35499/02.20.03_ScrollBox.png)
**사진 02.20.03 ScrollArea**

ScrollArea는 여러 위젯들을 스크롤이 가능한 하나의 상자 안에 넣을 수 있는 Container를 의미합니다. ScrollArea의 크기가 작아 ScrollArea의 내부에 있는 위젯들을 다 표시하지 못할 경우, 자동으로 스크롤바를 생성합니다. ScrollArea의 크기를 크게 키운 후, 원하는 위젯을 ScrollArea의 내부에 위치시키고 ScrollArea의 크기를 원하는 크기로 조절하시면 됩니다.

## Tab Widget

![02.20.04 Tab Widget](https://wikidocs.net/images/page/35499/02.20.04_Tab.png)
![02.20.05 Tab Widget Add](https://wikidocs.net/images/page/35499/02.20.05_Add_Tab.png)
**사진 02.20.04 Tab Widget, Tab Widget 추가**

Tab Widget이란, 여러개의 탭을 가지고 있는 Container를 의미합니다. 각 Tab별로 서로 다른 위젯들을 위치시킬 수 있으며, Tab을 추가시키고 싶을때는 Tab Widget을 오른쪽 마우스 클릭 > Insert Page를 눌러서 페이지를 추가할 수 있습니다. 또한, 현재 페이지를 삭제하고 싶을 때는 Tab Widget을 오른쪽 마우스 클릭 한 후, Insert Page 위에 있는 현재 페이지(위의 사진에서는 Page 1 of 3)을 눌러서 현재 페이지를 삭제할 수 있습니다.



