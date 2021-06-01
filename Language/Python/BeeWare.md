# BeeWare

출처:  https://docs.beeware.org/en/latest/index.html#

![](C:\gitbook\tech_note\posts\Mobile\images\beeware.png) 

# BeeWare

**Write Python. Run Anywhere.**

BeeWare는 도구 및 라이브러리 모음으로, 각 도구는 함께 작동하여 크로스 플랫폼 네이티브 GUI Python 애플리케이션을 작성하는 데 도움이됩니다. 다음이 포함됩니다.

- 크로스 플랫폼 위젯 툴킷 인 [Toga](https://toga.readthedocs.io/)
- 최종 사용자에게 제공 할 수있는 배포 가능한 아티팩트로 Python 프로젝트를 패키징하는 도구 인 [Briefcase](https://briefcase.readthedocs.io/)
- Python 코드 내에서 iOS 및 macOS의 Objective C 라이브러리 작업을위한 라이브러리 인 [Rubicon ObjC](https://rubicon-objc.readthedocs.io/)
- Python 코드 내에서 Java 라이브러리로 작업하기위한 라이브러리 인 [Rubicon Java](https://rubicon-java.readthedocs.io/)
- 공식 Python 설치 프로그램을 사용할 수 없는 플랫폼에서 사용할 수 있도록 사전 컴파일 된 Python 빌드입니다.

각 도구를 분리하여 사용하거나 모두를 제품군으로 사용할 수 있습니다.

전체 BeeWare 제품군에는 BeeWare의 자체 라이브러리를 사용하여 작성된 [소프트웨어 개발 도구](https://beeware.org/project/projects/tools/) 및 [응용 프로그램도](https://beeware.org/project/projects/applications/) 포함됩니다.

BeeWare 제품군은 macOS, Windows, Linux (GTK 사용)에서 사용할 수 있습니다. Android 및 iOS와 같은 모바일 플랫폼 그리고 웹. 다른 플랫폼 (예 : 셋톱 박스 및 시계)에 대한 지원은 장기 로드맵에 있습니다.

`Platform support`

```
BeeWare 제품군은 진행중인 작업입니다. 일부 도구와 라이브러리는 다른 것보다 성숙하고 일부 플랫폼은 다른 것보다 더 잘 지원됩니다. 특히 웹 지원은 실험적인 것으로 간주되어야합니다.
```

BeeWare를 직접 사용해 볼 준비가 되셨습니까? Python으로 크로스 플랫폼 애플리케이션을 빌드 해 보겠습니다!



## Tutorial 0 - Let’s get set up!

첫 번째 BeeWare 앱을 구축하기 전에 BeeWare 실행을위한 모든 필수 구성 요소가 있는지 확인해야합니다.

역자 주) 앞으로 진행되는 문서는 모두 Linux (Ubuntu) 를 기준으로 설명 합니다. 필요할 경우 [원문](https://docs.beeware.org/en/latest/index.html#) 을 참조 하세요.



### Install Python

가장 먼저 필요한 것은 Python 3.5 이상을 실행하는 작동하는 Python 인터프리터입니다.

- Linux

  ```
  Linux를 사용하는 경우 시스템 패키지 관리자 (Debian / Ubuntu / Mint에 적합, Fedora에 dnf, Arch에 pacman)를 사용하여 Python을 설치합니다.
  
  현재 Raspberry Pi는 지원하지 않습니다.
  ```

  

### Install dependencies

다음으로 운영 체제에 필요한 추가 종속성을 설치하십시오. 로컬 개발을 지원하려면 일부 시스템 패키지를 설치해야합니다. 필요한 패키지 목록은 배포에 따라 다릅니다.

#### **Ubuntu 16.04, Debian 9**

``` bash
$ sudo apt-get update
$ sudo apt-get install git python3-dev python3-venv libgirepository1.0-dev libcairo2-dev libpango1.0-dev libwebkitgtk-3.0-0 gir1.2-webkit-3.0
```

#### **Ubuntu 18.04, Debian 10**

``` bash
$ sudo apt-get install git python3-dev python3-venv libgirepository1.0-dev libcairo2-dev libpango1.0-dev libwebkit2gtk-4.0-37 gir1.2-webkit2-4.0
```

#### **Fedora**

``` bash
$ sudo dnf install git pkg-config python3-devel gobject-introspection-devel cairo-devel cairo-gobject-devel pango-devel webkitgtk4
```

#### **Arch, Manjaro**

``` bash
$ sudo pacman -Syu git pkgconf cairo python-cairo pango gobject-introspection gobject-introspection-runtime python-gobject webkit2gtk
```

Briefcase는 또한 AppImage라는 도구를 사용하여 Linux 배포판에서 사용할 수있는 바이너리를 빌드합니다. 그러나 Linux 용 AppImage 바이너리를 빌드하는 것은 각 배포판에 존재하는 일관성없는 라이브러리 버전으로 인해 복잡합니다. Briefcase는 Docker를 사용하여 AppImage 빌드를 호스팅하기위한 잘 제어 된 바이너리 환경을 제공합니다.

Docker Engine의 공식 설치 프로그램은 다양한 Unix 배포판에서 사용할 수 있습니다. 플랫폼에 대한 지침을 따르십시오. Docker를 설치하면 Ubuntu 16.04 컨테이너를 시작할 수 있습니다.

``` bash
$ docker run -it ubuntu:16.04
```

Docker 컨테이너 내부에 Unix 프롬프트 (root @ 84444e31cff9 : / #와 같은)가 표시됩니다. Ctrl-D를 입력하여 Docker를 종료하고 로컬 셸로 돌아갑니다.



### Set up a virtual environment

이제 기본 Python 설치에서 이 자습서 작업을 분리하는 데 사용할 수있는 "샌드 박스"인 가상 환경을 만들 것입니다. 가상 환경에 패키지를 설치하는 경우 기본 Python 설치 (및 컴퓨터에있는 다른 Python 프로젝트)는 영향을받지 않습니다. 가상 환경을 완전히 엉망으로 만들면 컴퓨터의 다른 Python 프로젝트에 영향을주지 않고 Python을 다시 설치할 필요없이 간단히 삭제하고 다시 시작할 수 있습니다.

``` bash
$ mkdir beeware-tutorial
$ cd beeware-tutorial
$ python3 -m venv beeware-venv
$ source beeware-venv/bin/activate
```

이것이 효과가 있다면 프롬프트가 변경되어야합니다. (beeware-venv) 접두사가 있어야합니다. 이를 통해 현재 BeeWare 가상 환경에 있음을 알 수 있습니다. 이 자습서에서 작업 할 때마다 가상 환경이 활성화되어 있는지 확인해야합니다. 그렇지 않은 경우 마지막 명령 (activate 명령)을 다시 실행하여 환경을 다시 활성화합니다.

**Alternative virtual environments**

``` 
Anaconda 또는 miniconda를 사용하는 경우 conda 환경 사용에 더 익숙 할 수 있습니다. Python에 내장 된 venv 모듈의 전신 인 virtualenv에 대해서도 들어 보셨을 것입니다. Python 설치와 마찬가지로 가상 환경이있는 한 가상 환경을 만드는 방법은 중요하지 않습니다.

그럼에도 불구하고 엄밀히 말하면 가상 환경 사용은 선택 사항입니다. BeeWare의 도구를 기본 Python 환경에 직접 설치할 수 있습니다. 하지만 가상 환경을 사용하는 것이 정말 좋습니다.
```



### Next steps

이제 환경을 설정했습니다. [첫 번째 BeeWare 애플리케이션](https://docs.beeware.org/en/latest/tutorial/tutorial-1.html)을 만들 준비가되었습니다.



## Tutorial 1 - Your first app

첫 번째 애플리케이션을 만들 준비가되었습니다.

### Install the BeeWare tools

먼저 **Briefcase**를 설치해야합니다. **Briefcase** 는 최종 사용자에게 배포하기 위해 응용 프로그램을 패키징하는 데 사용할 수있는 BeeWare 도구이지만 새 프로젝트를 부트 스트랩하는데도 사용할 수 있습니다. Beeware-venv 가상 환경이 활성화 된 상태에서 Tutorial 0에서 만든 beeware-tutorial 디렉토리에 있는지 확인하고 다음을 실행합니다.

``` bash
(beeware-venv) $ python -m pip install briefcase
```



> 설치 중 가능한 오류

> 설치 중에 오류가 표시되는 경우 대부분의 경우 일부 시스템 요구 사항이 설치되지 않았기 때문입니다. 모든 플랫폼 전제 조건을 설치했는지 확인하십시오.

BeeWare 도구 중 하나는 **Briefcase** 입니다. **Briefcase** 는 최종 사용자에게 배포하기 위해 애플리케이션을 패키징하는 데 사용할 수 있지만 새 프로젝트를 bootstrap 하는 데 사용할 수도 있습니다.

### Bootstrap a new project

첫 번째 BeeWare 프로젝트를 시작하겠습니다! Briefcase new 명령을 사용하여 Hello World라는 응용 프로그램을 만들 것입니다. 명령 프롬프트에서 다음을 실행하십시오.

``` bash
(beeware-venv) $ briefcase new
```

Briefcase 는 우리의 새로운 응용 프로그램에 대한 몇 가지 세부 사항을 요청할 것입니다. 이 자습서에서는 다음을 사용합니다.

- **Formal Name** \- Accept the default value: `Hello World`.
- **App Name** - Accept the default value: `helloworld`.
- **Bundle** - 자신의 도메인을 소유하고있는 경우 해당 도메인을 역순으로 입력합니다. (예를 들어 "cupcakes.com"도메인을 소유하고있는 경우 com.cupcakes를 번들로 입력). 자신의 도메인을 소유하지 않은 경우 기본 번들 (com.example)을 수락합니다.
- **Project Name** - Accept the default value: `Hello World`.
- **Description** -기본값을 수락합니다 (또는 정말로 창의적이기를 원한다면 자신의 설명을 생각해보세요!)
- **Author** - Enter your own name here.
- **Author’s email** -  자신의 이메일 주소를 입력하십시오. 이는 구성 파일, 도움말 텍스트 및 앱 스토어에 앱을 제출할 때 이메일이 필요한 모든 곳에서 사용됩니다.
- **URL** - 애플리케이션 랜딩 페이지의 URL입니다. 다시 말하지만, 자신의 도메인을 소유하고 있다면 해당 도메인의 URL (https : // 포함)을 입력하십시오. 그렇지 않으면 기본 URL (https://example.com/helloworld)을 수락하면됩니다. 이 URL은 실제로 존재할 필요는 없습니다 (당분간). 앱 스토어에 애플리케이션을 게시하는 경우에만 사용됩니다.
- **License** - 기본 라이선스 (BSD)를 수락합니다. 그러나 이것은 튜토리얼의 작동에 영향을주지 않습니다. 따라서 라이선스 선택에 대해 특히 강한 느낌이 있다면 다른 라이선스를 자유롭게 선택하십시오.
- **GUI framework** - 기본 옵션 인 Toga (BeeWare의 자체 GUI 툴킷)를 승인하십시오.

그런 다음 Briefcase 은 사용할 프로젝트 스켈레톤을 생성합니다. 지금까지 이 자습서를 따랐고 설명 된대로 기본값을 수락했다면 파일 시스템은 다음과 같아야합니다.

``` bash
beeware-tutorial/
    beeware-venv/
        ...
    helloworld/
        LICENSE
        README.rst
        pyproject.toml
        src/
            helloworld/
                resources/
                    helloworld.icns
                    helloworld.ico
                    helloworld.png
                __init__.py
                __main__.py
                app.py
```

이 스켈레톤은 실제로 다른 것을 추가하지 않고도 완벽하게 작동하는 애플리케이션입니다. src 폴더에는 애플리케이션의 모든 코드가 포함되어 있으며 pyproject.toml 파일은 배포를 위해 애플리케이션을 패키징하는 방법을 설명합니다. 편집기에서 pyproject.toml을 열면 방금 Briefcase 에 제공 한 구성 세부 정보가 표시됩니다.

### Run the app in developer mode

helloworld 프로젝트 디렉토리로 이동하고 Briefcase에 개발자 (또는 개발) 모드에서 프로젝트를 시작하도록 지시합니다.

``` bash
(beeware-venv) $ cd helloworld
(beeware-venv) $ briefcase dev

[hello-world] Installing dependencies...
...
[helloworld] Starting in dev mode...
```

GUI 창이 열립니다.

![](./images/tutorial-11.png)

닫기 버튼을 누르거나 애플리케이션 메뉴에서 종료를 선택하면 완료됩니다! 축하합니다. Python으로 독립형 네이티브 애플리케이션을 작성했습니다.

### Next steps

이제 개발자 모드에서 실행중인 작동중인 애플리케이션이 있습니다. 이제 응용 프로그램이 좀 더 흥미로운 작업을 수행하도록 자체 논리를 추가 할 수 있습니다. [Tutorial 2 ](https://docs.beeware.org/en/latest/tutorial/tutorial-2.html)에서는 애플리케이션에 보다 유용한 사용자 인터페이스를 추가 할 것입니다.



## Tutorial 2 - Making it interesting

Tutorial 1에서는 실행할 수있는 스텁 프로젝트를 생성했지만 직접 코드를 작성하지 않았습니다. 무엇이 생성되었는지 살펴 보겠습니다.

### What was generated

src/helloworld 디렉토리에 `__init__.py`, `__main__.py` 및 `app.py`의 3 개 파일이 표시되어야합니다.

`__init__.py`는 helloworld 디렉토리를 가져 오기 가능한 Python 모듈로 표시합니다. 빈 파일입니다. 그것이 존재한다는 사실은 파이썬 인터프리터에게 helloworld 디렉토리가 모듈을 정의한다는 것을 알려줍니다.

`__main__.py`는 helloworld 모듈을 실행 가능한 모듈이라는 특별한 종류의 모듈로 표시합니다. Python -m helloworld를 사용하여 helloworld 모듈을 실행하려고하면 `__main__.py`파일에서 Python이 실행을 시작합니다. `__main__.py`의 내용은 비교적 간단합니다.

``` python
from helloworld.app import main

if __name__ == '__main__':
    main().main_loop()
```

즉, helloworld 앱에서 main 메소드를 가져옵니다. 진입 점으로 실행중인 경우 main () 메서드를 호출하고 응용 프로그램의 기본 루프를 시작합니다. 메인 루프는 GUI 애플리케이션이 사용자 입력 (마우스 클릭 및 키보드 누르기 등)을 수신하는 방식입니다.

더 흥미로운 파일은 app.py입니다. 여기에는 애플리케이션 창을 만드는 로직이 포함되어 있습니다.

``` python
import toga
from toga.style import Pack
from toga.style.pack import COLUMN, ROW

class HelloWorld(toga.App):
    def startup(self):
        main_box = toga.Box()

        self.main_window = toga.MainWindow(title=self.formal_name)
        self.main_window.content = main_box
        self.main_window.show()

def main():
    return HelloWorld()
```

이 줄을 한 줄씩 살펴 보겠습니다.

``` python
import toga
from toga.style import Pack
from toga.style.pack import COLUMN, ROW
```

먼저 `toga` 위젯 툴킷과 일부 스타일 관련 유틸리티 클래스 및 상수를 가져옵니다. 우리 코드는 아직 이것을 사용하지 않지만 곧 사용할 것입니다.

그런 다음 클래스를 정의합니다.

``` python
class HelloWorld(toga.App):
```

각 Toga 애플리케이션에는 애플리케이션 인 실행중인 엔티티를 나타내는 단일 toga.App 인스턴스가 있습니다. 앱이 여러 창을 관리하게 될 수 있습니다. 그러나 간단한 응용 프로그램의 경우 하나의 기본 창이 있습니다.

다음으로 startup () 메서드를 정의합니다.

``` python
def startup(self):
    main_box = toga.Box()
```

시작 방법이 가장 먼저하는 일은 메인 박스를 정의하는 것입니다. Toga의 레이아웃 체계는 HTML과 유사하게 작동합니다. 각각 다른 상자 또는 실제 위젯을 포함하는 상자 모음을 구성하여 애플리케이션을 빌드합니다. 그런 다음 이러한 상자에 스타일을 적용하여 사용 가능한 창 공간을 사용하는 방법을 정의합니다.

이 응용 프로그램에서는 단일 상자를 정의하지만 여기에 아무것도 넣지 않습니다.

다음으로이 빈 상자를 넣을 수있는 창을 정의합니다.

``` python
self.main_window = toga.MainWindow(title=self.formal_name)
```

그러면 애플리케이션 이름과 일치하는 제목이있는 toga.MainWindow의 인스턴스가 생성됩니다. 메인 창은 Toga의 특별한 종류의 창입니다. 앱의 수명주기와 밀접하게 연결된 창입니다. 메인 창이 닫히면 응용 프로그램이 종료됩니다. 주 창은 응용 프로그램의 메뉴가있는 창이기도합니다 (메뉴 막대가 창의 일부인 Windows와 같은 플랫폼에있는 경우).

그런 다음 빈 상자를 기본 창의 내용으로 추가하고 응용 프로그램에 창을 표시하도록 지시합니다.

``` python
self.main_window.content = main_box
self.main_window.show()
```

마지막으로 main () 메서드를 정의합니다. 이것이 우리 애플리케이션의 인스턴스를 만드는 것입니다.

``` python
def main():
    return HelloWorld()
```

이 main () 메소드는 `__main__.py`에 의해 가져 와서 호출되는 메소드입니다. HelloWorld 애플리케이션의 인스턴스를 생성하고 반환합니다.

이것이 가장 간단한 Toga 애플리케이션입니다. 우리 자신의 콘텐츠 중 일부를 애플리케이션에 넣고 앱이 흥미로운 일을하도록 만들어 보겠습니다.

### Adding some content of our own

`src/helloworld/app.py` 내부의 HelloWorld 클래스를 다음과 같이 수정합니다.

``` python
class HelloWorld(toga.App):
    def startup(self):
        main_box = toga.Box(style=Pack(direction=COLUMN))

        name_label = toga.Label(
            'Your name: ',
            style=Pack(padding=(0, 5))
        )
        self.name_input = toga.TextInput(style=Pack(flex=1))

        name_box = toga.Box(style=Pack(direction=ROW, padding=5))
        name_box.add(name_label)
        name_box.add(self.name_input)

        button = toga.Button(
            'Say Hello!',
            on_press=self.say_hello,
            style=Pack(padding=5)
        )

        main_box.add(name_box)
        main_box.add(button)

        self.main_window = toga.MainWindow(title=self.formal_name)
        self.main_window.content = main_box
        self.main_window.show()

    def say_hello(self, widget):
        print("Hello", self.name_input.value)
```

```
Note
파일 상단의 가져 오기 또는 하단의 main()을 제거하지 마세요. HelloWorld 클래스 만 업데이트하면됩니다.
```

변경된 사항을 자세히 살펴 보겠습니다.

우리는 여전히 메인 박스를 만들고 있습니다. 그러나 이제 우리는 스타일을 적용하고 있습니다.

``` python
main_box = toga.Box(style=Pack(direction=COLUMN))
```

Toga의 내장 레이아웃 시스템을 'Pack'이라고합니다. CSS와 비슷하게 작동합니다. 계층 구조에서 개체를 정의합니다. HTML에서 개체는 div, span 및 기타 DOM 요소입니다. Toga에서는 위젯이자 상자입니다. 그런 다음 개별 요소에 스타일을 지정할 수 있습니다. 이 경우 COLUMN 상자임을 나타냅니다. 즉, 사용 가능한 모든 너비를 사용하고 콘텐츠가 추가됨에 따라 높이가 확장되는 상자이지만 가능한 한 짧게 만들려고합니다. .

다음으로 몇 가지 위젯을 정의합니다.

``` python
name_label = toga.Label(
    'Your name: ',
    style=Pack(padding=(0, 5))
)
self.name_input = toga.TextInput(style=Pack(flex=1))
```

여기서는 Label과 TextInput을 정의합니다. 두 위젯에는 모두 연관된 스타일이 있습니다. 레이블은 왼쪽과 오른쪽에 5px의 패딩이 있고 상단과 하단에는 패딩이 없습니다. TextInput은 유연한 것으로 표시됩니다. 즉, 레이아웃 축에서 사용 가능한 모든 공간을 흡수합니다.

TextInput은 클래스의 인스턴스 변수로 할당됩니다. 이렇게하면 잠시 후에 사용할 위젯 인스턴스에 쉽게 액세스 할 수 있습니다.

다음으로,이 두 위젯을 보관할 상자를 정의합니다.

``` python
name_box = toga.Box(style=Pack(direction=ROW, padding=5))
name_box.add(name_label)
name_box.add(self.name_input)
```

Name_box는 기본 상자와 같은 상자입니다. 그러나 이번에는 ROW 상자입니다. 즉, 콘텐츠가 가로로 추가되고 너비를 가능한 한 좁게 만들려고합니다. 상자에는 또한 모든면에 5px의 패딩이 있습니다.

이제 버튼을 정의합니다.

``` python
button = toga.Button(
    'Say Hello!',
    on_press=self.say_hello,
    style=Pack(padding=5)
)
```

버튼의 모든면에 5px의 패딩이 있습니다. 또한 버튼을 눌렀을 때 호출 할 메서드 인 핸들러도 정의합니다.

그런 다음 이름 상자와 버튼을 기본 상자에 추가합니다.

``` python
main_box.add(name_box)
main_box.add(button)
```

이것으로 레이아웃이 완성됩니다. 나머지 시작 방법은 이전과 같습니다. MainWindow를 정의하고 메인 상자를 창의 내용으로 할당합니다.

``` python
self.main_window = toga.MainWindow(title=self.formal_name)
self.main_window.content = main_box
self.main_window.show()
```

마지막으로해야 할 일은 버튼의 핸들러를 정의하는 것입니다. 핸들러는 모든 메소드, 생성기 또는 비동기 코 루틴 일 수 있습니다. 이벤트를 생성 한 위젯을 인수로 받아들이고 버튼을 누를 때마다 호출됩니다.

``` python
def say_hello(self, widget):
    print("Hello, ", self.name_input.value)
```

메서드의 본문은 간단한 print 문이지만 이름 입력의 현재 값을 조사하고 해당 내용을 인쇄되는 텍스트로 사용합니다.

이제 이러한 변경 사항을 적용 했으므로 응용 프로그램을 다시 시작하여 모양을 확인할 수 있습니다. 이전과 마찬가지로 개발자 모드를 사용합니다.

``` bash
(beeware-venv) $ briefcase dev

[helloworld] Starting in dev mode...
```

이번에는 종속성을 설치하지 않음을 알 수 있습니다. Briefcase 는 응용 프로그램이 이전에 실행되었음을 감지 할 수 있으며 시간을 절약하기 위해 응용 프로그램 만 실행합니다. 앱에 새 종속성을 추가하는 경우 briefcase dev를 실행할 때 -d 옵션을 전달하여 해당 종속성이 설치되었는지 확인할 수 있습니다.

GUI 창이 열립니다.

![](./images/tutorial-21.png)

텍스트 상자에 이름을 입력하고 GUI 버튼을 누르면 애플리케이션을 시작한 콘솔에 출력이 표시됩니다.

### Next steps

이제 좀 더 흥미로운 작업을 수행하는 응용 프로그램이 있습니다. 그러나 그것은 우리 자신의 컴퓨터에서만 실행됩니다. 배포를 위해이 응용 프로그램을 패키징 해 보겠습니다. 튜토리얼 3에서는 친구, 고객에게 보내거나 App Store에 업로드 할 수있는 독립형 설치 프로그램으로 애플리케이션을 래핑합니다.



## Tutorial 3 - Packaging for distribution

출처: https://docs.beeware.org/en/latest/tutorial/tutorial-3.html

지금까지 우리는 "개발자 모드"에서 애플리케이션을 실행했습니다. 이를 통해 로컬에서 애플리케이션을 쉽게 실행할 수 있지만, 우리가 진정 원하는 것은 애플리케이션을 다른 사람에게 제공 할 수있는 것입니다.

하지만 사용자에게 Python을 설치하고, 가상 환경을 만들고, git 저장소를 복제하고, 개발자 모드에서 Briefcase 를 실행하는 방법을 알려줄 필요는 없습니다. 그냥 설치 프로그램을 제공하고 Just Work 애플리케이션을 사용하는 것이 좋습니다.

이러한 방식으로 배포 할 애플리케이션을 패키지화하는 데 Briefcase 를 사용할 수 있습니다.

### Creating your application scaffold

애플리케이션을 패키징하는 것은 이번이 처음이므로 패키징 프로세스를 지원하기 위해 구성 파일과 기타 발판(scaffokd)을 만들어야합니다. Helloworld 디렉토리에서 다음을 실행합니다.

``` bash
(beeware-venv) $ briefcase create

[helloworld] Generating application template...
Using app template: https://github.com/beeware/briefcase-linux-appImage-template.git
...
[helloworld] Installing support package...
...
[helloworld] Installing dependencies...
[helloworld] Entering Docker context...

[helloworld] Building Docker container image...
...
[helloworld] Leaving Docker context.

[helloworld] Installing application code...
...
[helloworld] Installing application resources...
...
[helloworld] Created linux/Hello World
```

```
Note
처음 실행할 때는 Briefcase에서 AppImage 바이너리를 빌드하는 데 사용할 수있는 Ubuntu 16.04 Docker 이미지를 준비해야하므로 시간이 걸릴 수 있습니다. 여기에는 많은 시스템 패키지 다운로드가 포함됩니다. 향후 실행시 이 Docker 이미지가 재사용됩니다.
```

방금 콘텐츠 페이지가 터미널에서 지나가는 것을 보셨을 것입니다. 무슨 일이 일어 났습니까?  Briefcase 는 다음을 수행했습니다.

- **응용 프로그램 템플릿을 생성했습니다.**

  실제 애플리케이션의 코드를 넘어서 기본 설치 프로그램을 빌드하는 데 필요한 많은 파일과 구성이 있습니다. 이 추가 스캐 폴딩은 생성되는 실제 애플리케이션의 이름을 제외하고는 동일한 플랫폼의 모든 애플리케이션에 대해 거의 동일하므로 Briefcase는 지원하는 각 플랫폼에 대한 애플리케이션 템플릿을 제공합니다. 이 단계에서는 구축중인 플랫폼을 지원하는 데 필요한대로 애플리케이션 이름, 번들 ID 및 구성 파일의 기타 속성을 대체하여 템플릿을 롤아웃합니다.

  서류 가방에서 제공하는 템플릿이 마음에 들지 않으면 직접 제공 할 수 있습니다. 그러나 서류 가방의 기본 템플릿을 사용하여 조금 더 경험을 쌓을 때까지는이 작업을 수행하고 싶지 않을 것입니다.

- **지원 패키지를 다운로드하여 설치했습니다.**

  서류 가방에서 취하는 패키징 접근 방식은 '작동 할 수있는 가장 간단한 것'으로 가장 잘 설명됩니다. 빌드하는 모든 애플리케이션의 일부로 완전하고 격리 된 Python 인터프리터를 제공합니다. 이것은 약간의 공간 비효율적입니다. 서류 가방에 5 개의 응용 프로그램이 패키지되어있는 경우 Python 인터프리터 5 개를 갖게됩니다. 그러나이 접근 방식은 애플리케이션과 함께 작동하는 것으로 알려진 특정 버전의 Python을 사용하여 모든 애플리케이션이 완전히 독립적임을 보장합니다.

  다시, Briefcase는 각 플랫폼에 대한 기본 지원 패키지를 제공합니다. 원하는 경우 자체 지원 패키지를 제공하고 해당 패키지를 빌드 프로세스의 일부로 포함 할 수 있습니다. 활성화해야하는 Python 인터프리터에 특정 옵션이 있거나 런타임에 필요하지 않은 모듈을 표준 라이브러리에서 제거하려는 경우이 작업을 수행 할 수 있습니다.

  서류 가방은 지원 패키지의 로컬 캐시를 유지하므로 특정 지원 패키지를 다운로드하면 캐시 된 사본이 향후 빌드에 사용됩니다.

- **응용 프로그램 종속성을 설치했습니다.**

  애플리케이션은 런타임에 필요한 타사 모듈을 지정할 수 있습니다. Pip를 사용하여 애플리케이션의 설치 프로그램에 설치됩니다.

- **응용 프로그램 코드를 설치했습니다.**

  애플리케이션에는 자체 코드와 리소스 (예 : 런타임에 필요한 이미지)가 있습니다. 이러한 파일은 설치 프로그램에 복사됩니다.

- **애플리케이션에 필요한 리소스를 설치했습니다.**

  마지막으로 설치 프로그램 자체에 필요한 추가 리소스를 추가합니다. 여기에는 최종 응용 프로그램 및 스플래시 화면 이미지에 연결해야하는 아이콘과 같은 것들이 포함됩니다.

이 작업이 완료되면 프로젝트 디렉토리를 살펴보면 이제 추가 파일이 포함 된 플랫폼 (macOS, Linux 또는 Windows)에 해당하는 디렉토리가 표시됩니다. 이는 애플리케이션에 대한 플랫폼 별 패키징 구성입니다.

### Building your application

이제 애플리케이션을 컴파일 할 수 있습니다. 이 단계는 대상 플랫폼에서 애플리케이션을 실행하는 데 필요한 바이너리 컴파일을 수행합니다.

``` bash
(beeware-venv) $ briefcase build

[helloworld] Building AppImage...
...
[helloworld] Built linux/Hello World-x86_64-0.0.1.AppImage
```

이 단계가 완료되면 linux 폴더에 Hello World-x86_64-0.0.1.AppImage라는 파일이 포함됩니다. 이 AppImage는 실행 파일입니다. 셸에서 실행하거나 파일 탐색기에서 두 번 클릭 할 수 있습니다. 다른 Linux 사용자에게도 제공 할 수 있으며 2016 년 이후에 출시 된 Linux 버전이있는 한 동일한 방식으로 실행할 수 있어야합니다.

### Running your app

이제 Briefcase 를 사용하여 애플리케이션을 실행할 수 있습니다.

``` bash
(beeware-venv) $ briefcase run

[helloworld] Starting app...

(beeware-venv) $
```

그러면 빌드 명령의 출력을 사용하여 네이티브 애플리케이션 실행이 시작됩니다.

앞에서 본 콘솔 출력이 더 이상 표시되지 않습니다. 이는 이제 출력 할 수있는 (표시되는) 콘솔이없는 독립 실행 형 패키지 앱을 실행하고 있기 때문입니다.

또한 애플리케이션이 실행 중일 때 보이는 방식에 약간의 차이가 있음을 알 수 있습니다. 예를 들어, 운영 체제에 표시되는 아이콘과 이름은 개발자 모드에서 실행할 때 본 것과 약간 다를 수 있습니다. 이는 Python 코드 만 실행하는 것이 아니라 패키지화 된 애플리케이션을 사용하기 때문이기도합니다. 운영 체제의 관점에서 이제는 'Python 프로그램'이 아닌 '앱'을 실행하고 있으며 이는 응용 프로그램이 표시되는 방식에 반영됩니다.

### Building your installer

이제 package 명령을 사용하여 배포를 위해 애플리케이션을 패키징 할 수 있습니다. package 명령은 스캐 폴딩 된 프로젝트를 최종 배포 가능한 제품으로 변환하는 데 필요한 모든 컴파일을 수행합니다. 플랫폼에 따라 설치 프로그램 컴파일, 코드 서명 수행 또는 기타 사전 배포 작업 수행이 포함될 수 있습니다.

``` bash
(beeware-venv) $ briefcase package

[helloworld] Building AppImage...
...
[helloworld] Created linux/Hello World-x86_64-0.0.1.AppImage.
```

Linux에서이 단계는 아무 작업도 수행하지 않습니다. 빌드 명령으로 생성 된 AppImage는 완전한 실행 파일이므로 추가 처리가 필요하지 않습니다.

### Next steps

이제 데스크톱 플랫폼에 배포 할 수 있도록 응용 프로그램이 패키징되었습니다. 하지만 애플리케이션에서 코드를 업데이트해야 할 때 어떻게됩니까? 이러한 업데이트를 패키지 애플리케이션으로 가져 오려면 어떻게해야합니까? [자습서 4](https://docs.beeware.org/en/latest/tutorial/tutorial-4.html)로 이동하여 알아보십시오.



## Tutorial 4 - Updating your application

지난 튜토리얼에서는 애플리케이션을 기본 애플리케이션으로 패키징했습니다. 실제 앱을 다룬다고해서 이야기가 끝나지는 않을 것입니다. 테스트를 수행하고 문제를 발견하고 변경해야 할 가능성이 있습니다. 애플리케이션이 완벽하더라도 결국 개선 된 애플리케이션 버전 2를 게시하고 싶을 것입니다.

그렇다면 코드를 변경할 때 설치된 앱을 어떻게 업데이트합니까?

### Updating application code

우리의 응용 프로그램은 현재 버튼을 누르면 콘솔에 인쇄됩니다. 그러나 GUI 응용 프로그램은 출력을 위해 실제로 콘솔을 사용해서는 안됩니다. 사용자와 통신하려면 대화 상자를 사용해야합니다.

콘솔에 쓰는 대신 인사하는 대화 상자를 추가해 보겠습니다. say_hello 콜백을 다음과 같이 수정합니다.

``` python
def say_hello(self, widget):
    self.main_window.info_dialog(
        'Hi there!',
        "Hello, {}".format(self.name_input.value)
    )
```

그러면 Toga가 버튼을 누를 때 모달 대화 상자를 열도록 지시합니다.

`briefcase dev`를 실행하고 버튼을 누르면 새 대화 상자가 표시됩니다. 그러나 briefcase run 을 실행하면 대화 상자가 나타나지 않습니다.

왜 이런거야?  briefcase  개발자는 코드를 제자리에서 실행하여 작동합니다. 가능한 한 현실적인 코드 런타임 환경을 생성하려고하지만 코드를 애플리케이션으로 래핑하기위한 플랫폼 인프라를 제공하거나 사용하지 않습니다. 앱 패키징 프로세스의 일부에는 코드를 애플리케이션 번들에 복사하는 작업이 포함됩니다. 현재 애플리케이션에는 여전히 이전 코드가 있습니다.

그래서 우리는 briefcase 에 새 버전의 코드를 복사하도록 지시해야합니다. 이전 플랫폼 디렉토리를 삭제하고 처음부터 시작하여 이를 수행 할 수 있습니다. 그러나 Briefcase는 더 쉬운 방법을 제공합니다. 기존 번들 애플리케이션의 코드를 업데이트 할 수 있습니다.

``` bash
(beeware-venv) $ briefcase update

[helloworld] Updating application code...
Installing src/helloworld...

[helloworld] Application updated.
```

Briefcase에서 스캐 폴드 템플릿을 찾을 수없는 경우 자동으로 create를 호출하여 새 스캐 폴드를 생성합니다.

이제 설치 프로그램 코드를 업데이트 했으므로 `briefcase build` 를 실행하여 앱을 다시 컴파일하고, `briefcase run` 하여 업데이트 된 앱을 실행하고, `briefcase package` 를 실행하여 배포를 위해 애플리케이션을 다시 패키징 할 수 있습니다.

(macOS 사용자는 Tutorial 3에서 언급했듯이 튜토리얼의 경우 코드 서명 ID를 설정하는 복잡성을 피하고 튜토리얼을 가능한 한 간단하게 유지하기 위해 `--no-sign` 플래그를 사용하여 `briefcase package`를 실행하는 것이 좋습니다.)

### Updating dependencies and icons

그래도 애플리케이션 코드 만 업데이트됩니다. 종속성이 변경되었거나 새 스플래시 화면 또는 애플리케이션 아이콘과 같은 새 애플리케이션 리소스가있는 경우 어떻게해야합니까? 이 상황에서 update 명령에는 사용할 수있는 몇 가지 옵션이 있습니다.

- `briefcase update -d` (or `briefcase update --update-dependencies`) 응용 프로그램 종속성을 다시 설치합니다.
- `briefcase update -r` (or `briefcase update --update-resources`) 응용 프로그램 리소스를 다시 설치합니다.

### Update and run in one step

코드 변경을 빠르게 반복하는 경우 코드를 변경하고 애플리케이션을 업데이트 한 다음 즉시 애플리케이션을 다시 실행하는 것이 좋습니다. Briefcase에는 실행 명령의 -u (또는 --update) 옵션인 이 사용 패턴을 지원하는 바로 가기가 있습니다.

다른 변화를 시도해 봅시다. 텍스트 입력 상자에 이름을 입력하지 않으면 대화 상자에 'Hello,'가 표시됩니다. 이 엣지 케이스를 처리하기 위해 say_hello 함수를 다시 수정 해 보겠습니다.

``` python
def say_hello(self, widget):
    if self.name_input.value:
        name = self.name_input.value
    else:
        name = 'stranger'

    self.main_window.info_dialog(
        'Hi there!',
        "Hello, {}".format(name)
    )
```

새로운 로직이 작동하는지 확인하기 위해 개발 모드에서 앱을 실행하세요( with `briefcase dev`). 그런 다음 하나의 명령으로 앱을 업데이트, 빌드 및 실행합니다.

``` bash
(beeware-venv) $ briefcase run -u

[helloworld] Updating application code...
Installing src/helloworld...

[helloworld] Application updated.

[helloworld] Building AppImage...
...
[helloworld] Created linux/Hello World-x86_64-0.0.1.AppImage.

[helloworld] Starting app...
```

애플리케이션이 네이티브 바이너리로 실행되는 방식에 대해 테스트하거나 애플리케이션이 패키지 형식 일 때만 나타나는 버그를 사냥하는 경우에만 필요합니다. 대부분의 일상적인 개발의 경우`briefcase dev`이 훨씬 빠릅니다.

package 명령은 `-u` 인수도 허용하므로 애플리케이션 코드를 변경하고 즉시 다시 패키징하려는 경우 `briefcase package -u`를 실행할 수 있습니다.

### Next steps

이제 데스크톱 플랫폼에 배포 할 수 있도록 응용 프로그램이 패키징되었으며 응용 프로그램의 코드를 업데이트 할 수있었습니다.

하지만 모바일은 어떻습니까? [Tutorial 5](https://docs.beeware.org/en/latest/tutorial/tutorial-5/index.html)에서는 애플리케이션을 모바일 애플리케이션으로 변환하여 기기 시뮬레이터와 휴대폰에 배포 할 것입니다.



## Tutorial 5 - Taking it Mobile

지금까지 우리는 데스크톱에서 애플리케이션을 실행하고 테스트했습니다. 그러나 BeeWare는 모바일 플랫폼도 지원합니다. 우리가 작성한 애플리케이션은 모바일 장치에도 배포 할 수 있습니다!

### Tutorial 5 - Taking it mobile: iOS

이제 애플리케이션을 가져 와서 iOS 애플리케이션으로 배포 할 것입니다.

애플리케이션을 iOS에 배포하는 프로세스는 데스크톱 애플리케이션으로 배포하는 프로세스와 매우 유사합니다. 먼저 create 명령을 실행합니다.하지만 이번에는 iOS 애플리케이션을 만들도록 지정합니다.

``` bash
(beeware-venv) $ briefcase create iOS

[helloworld] Generating application template...
Using app template: https://github.com/beeware/briefcase-iOS-Xcode-template.git
...
[helloworld] Installing support package...
...
[helloworld] Installing dependencies...
...
[helloworld] Installing application code...
...
[helloworld] Installing application resources...
...
[helloworld] Created iOS/Hello World
```

이 작업이 완료되면 이제 프로젝트에 iOS 디렉토리가 생깁니다. 이 디렉토리에는 Xcode 프로젝트, 지원 라이브러리 및 애플리케이션에 필요한 애플리케이션 코드가 포함될 Hello World 폴더가 포함됩니다.

그런 다음 Briefcase 을 사용하여 `build`명령을 사용하여 앱을 컴파일 할 수 있습니다. 컴파일 할 장치를 선택하라는 메시지가 표시됩니다. 여러 iOS 버전 용 시뮬레이터가 설치된 경우 타겟팅 할 iOS 버전을 묻는 메시지가 표시 될 수도 있습니다. 표시되는 옵션은이 출력에 표시되는 옵션과 다를 수 있습니다. 우리 목적 상 어떤 시뮬레이터를 선택하든 상관 없습니다.

``` bash
(beeware-venv) $ briefcase build iOS

Select iOS version:

  1) 10.3
  2) 13.3

> 2

Select simulator device:

  1) iPad (7th generation)
  2) iPad Air (3rd generation)
  3) iPad Pro (11-inch)
  4) iPad Pro (12.9-inch) (3rd generation)
  5) iPad Pro (9.7-inch)
  6) iPhone 11
  7) iPhone 11 Pro
  8) iPhone 11 Pro Max
  9) iPhone 8
  10) iPhone 8 Plus

>  6

Targeting an iPhone 11 running iOS 13.3 (device UDID 4768AA69-497B-4B37-BD0C-3961756C38AC)

[hello-world] Building XCode project...
...
Build succeeded.

[hello-world] Built iOS/Hello World/build/Debug-iphonesimulator/Hello World.app
```

이제 애플리케이션을 실행할 준비가되었습니다. `briefcase run iOS`를 실행하여 이를 수행 할 수 있습니다. 그런 식으로 Briefcase 을 실행하면 타겟팅 할 기기를 다시 묻는 메시지가 표시됩니다. 사용 가능한 장치를 이미 알고있는 경우 -d (또는 --device) 옵션을 제공하여 해당 시뮬레이터를 사용하도록 Briefcase 에 알릴 수 있습니다. 애플리케이션을 빌드 할 때 선택한 디바이스 이름을 사용하여 다음을 실행하십시오.

``` bash
$ briefcase run iOS -d "iPhone 11"
```

iPhone 11 시뮬레이터가 여러 개인 경우 서류 가방은 가장 높은 iOS 버전을 선택합니다. 특정 iOS 버전을 선택하려면 특정 버전을 사용하도록 지시합니다.

``` bash
$ briefcase run iOS -d "iPhone 11::13.3"
```

또는 특정 장치의 이름을 UDID로 지정할 수 있습니다.

``` bash
$ briefcase run iOS -d 4768AA69-497B-4B37-BD0C-3961756C38AC
```

이렇게하면 iOS 시뮬레이터가 시작되고 앱이 설치되고 시작됩니다. 시뮬레이터가 시작되고 결국 iOS 애플리케이션을 열어야합니다.

![](./images/tutorial-5.png)

### Next steps

이제 휴대폰에 애플리케이션이 생겼습니다! BeeWare 앱을 배포하는 다른 방법이 있습니까? [자습서 6](https://docs.beeware.org/en/latest/tutorial/tutorial-6.html)으로 이동하여 알아보십시오.



### Tutorial 5 - Taking it mobile: Android

이제 애플리케이션을 Android 애플리케이션으로 배포 할 것입니다.

Android에 애플리케이션을 배포하는 프로세스는 데스크톱 애플리케이션으로 배포하는 프로세스와 매우 유사합니다. Briefcase 은 Android SDK, Android 에뮬레이터 및 Java 컴파일러를 포함하여 Android 용 종속성 설치를 처리합니다.

#### Create an Android app and compile it

먼저 `create` 명령을 실행하십시오. 이렇게하면 Android 앱 템플릿이 다운로드되고 여기에 Python 코드가 추가됩니다.

``` bash
(beeware-venv) $ briefcase create android

[helloworld] Generating application template...
Using app template: https://github.com/beeware/briefcase-android-gradle-template.git
...
[helloworld] Installing support package...
...
[helloworld] Installing dependencies...
...
[helloworld] Installing application code...
...
[helloworld] Installing application resources...
...
[helloworld] Application created.
```

`briefcase create android`를 처음 실행하면 Briefcase에서 Java JDK 및 Android SDK를 다운로드합니다. 파일 크기와 다운로드 시간은 상당 할 수 있습니다. 시간이 다소 걸릴 수 있습니다 (인터넷 연결 속도에 따라 10 분 이상). 다운로드가 완료되면 Google의 Android SDK 라이선스에 동의하라는 메시지가 표시됩니다.

이 작업이 완료되면 이제 프로젝트에 android 디렉토리가 생깁니다. 이 디렉터리에는 Gradle 빌드 구성이있는 Android 프로젝트가 포함될 Hello World 폴더가 포함됩니다. 이 프로젝트에는 애플리케이션 코드와 Python 인터프리터가 포함 된 지원 패키지가 포함됩니다.

그런 다음 Briefcase의 빌드 명령을 사용하여 이를 Android APK 앱 파일로 컴파일 할 수 있습니다.

``` bash
(beeware-venv) $ briefcase build android
[helloworld] Building Android APK...
Starting a Gradle Daemon
...
BUILD SUCCESSFUL in 1m 1s
28 actionable tasks: 17 executed, 11 up-to-date
[helloworld] Built android/Hello World/app/build/outputs/apk/debug/app-debug.apk
```

```
Gradle이 멈춘 것처럼 보일 수 있습니다.
briefcase build android 단계 중에 Gradle (Android 플랫폼 빌드 도구)은 CONFIGURING : 100 %를 인쇄하고 아무것도하지 않는 것처럼 보입니다. 걱정하지 마세요. 더 많은 Android SDK 구성 요소를 다운로드하고 있습니다. 인터넷 연결 속도에 따라 10 분 (또는 그 이상)이 더 걸릴 수 있습니다. 이 지연은 빌드를 처음 실행할 때만 발생합니다. 도구가 캐시되고 다음 빌드에서 캐시 된 버전이 사용됩니다.
```

#### Run the app on a virtual device

이제 애플리케이션을 실행할 준비가되었습니다. Briefcase’s 의 실행 명령을 사용하여 Android 기기에서 앱을 실행할 수 있습니다. Android 에뮬레이터에서 실행 해 보겠습니다.

애플리케이션을 실행하려면 `briefcase run android`를 실행하세요. 이렇게하면 앱을 실행할 수있는 기기 목록이 표시됩니다. 마지막 항목은 항상 새 Android 에뮬레이터를 만드는 옵션입니다.

``` bash
(beeware-venv) $ briefcase run android

Select device:

  1) Create a new Android emulator

>
```

이제 원하는 장치를 선택할 수 있습니다. "Create a new Android emulator"옵션을 선택하고 장치 이름 (beePhone)에 대한 기본 선택을 수락합니다.

Briefcase `run` 은 자동으로 가상 장치를 부팅합니다. 기기가 부팅되면 Android 로고가 표시됩니다.

![](./images/tutorial-5-booting.png)

<*Android virtual device booting*>

기기 부팅이 완료되면 Briefcase가 기기에 앱을 설치합니다. 실행기 화면이 잠시 나타납니다.

![](./images/tutorial-5-running.png)

<*Android virtual device fully started, on the launcher screen*>

그러면 앱이 시작됩니다. 앱이 시작되는 동안 스플래시 화면이 표시됩니다.

![](./images/tutorial-5-splash.png)

<*App splash screen*>

앱이 처음 시작되면 기기에서 unpacking 해야 합니다. 몇 초 정도 걸릴 수 있습니다. unpacking  하면 데스크톱 앱의 Android 버전이 표시됩니다.

![](./images/tutorial-5-launched.png)

<*Demo app fully launched*>

앱 실행이 표시되지 않으면 `briefcase run`을 실행 한 터미널을 확인하고 오류 메시지를 찾아야 할 수 있습니다.

앞으로는 메뉴를 사용하지 않고 이 기기에서 실행하고자한다면, `briefcase run android -d @beePhone`을 사용하여 가상 기기에서 직접 실행하여 에뮬레이터의 이름을 Briefcase에 제공 할 수 있습니다.

#### Run the app on a physical device

실제 Android 휴대폰 또는 태블릿이있는 경우 USB 케이블을 사용하여 컴퓨터에 연결 한 다음 Briefcase 을 사용하여 실제 장치를 대상으로 지정할 수 있습니다.

Briefcase 이 장치를 감지 할 수있는 경우 실행 출력에 나타납니다. 개발을 위해 처음으로 기기를 사용하면 '알 수없는 기기 (개발 용으로 승인되지 않음)'로보고 될 수 있습니다.

``` bash
(beeware-venv) $ briefcase run android

Select device:

  1) Unknown device (not authorized for development) (94ZZY0LNE8)
  2) @beePhone (emulator)
  3) Create a new Android emulator

>
```

Android에서는 기기에 앱을 로드하기 전에 기기를 '개발자'모드로 전환해야합니다. "알 수없는"장치를 선택하면 개발자 모드를 활성화하는 방법을 보여주는 링크가 표시됩니다.

개발자 모드가 활성화되면 `briefcase run android` 를 다시 실행할 수 있습니다.

``` bash
(beeware-venv) $ briefcase run android

Select device:

  1) Pixel 3a (94ZZY0LNE8)
  2) @beePhone (emulator)
  3) Create a new Android emulator

>
```

이번에는 기기 이름과 일련 번호 (이 경우 Pixel 3a)를 가져옵니다.

앞으로 메뉴를 사용하지 않고이 기기에서 실행하려면 `briefcase  run android -d 94ZZY0LNE8`을 사용하여 휴대 전화의 일련 번호를 `briefcase`에 제공 할 수 있습니다. Thi는 메시지없 이 장치에서 직접 실행됩니다.

```
Note
Android 용으로 개발할 때 Android 로그를 볼 수 있으면 유용합니다. 시스템의 나머지 부분에서 배경 소음없이 Android 로그를 보려면 `adb logcat -s MainActivity:* stdio:* Python:*` 을 실행할 수 있습니다. 앱이 stdout에 쓰는 모든 내용 (예 : print () 문의 출력)은 로그에 표시됩니다.
```



### Next steps

이제 휴대폰에 애플리케이션이 생겼습니다! BeeWare 앱을 배포하는 다른 방법이 있습니까? [자습서 6](https://docs.beeware.org/en/latest/tutorial/tutorial-6.html)으로 이동하여 알아보십시오.



## Tutorial 6 - Put it on the web!

모바일 플랫폼을 지원하는 것 외에도 Toga 위젯 툴킷은 웹을 지원합니다! 동일한 API를 사용하여 애플리케이션을 웹 사이트로 배포 할 수 있습니다.

**Coming soon**

### Next steps

이제 웹에 애플리케이션을 배포했습니다! 이제 애플리케이션을 전 세계와 공유 할 준비가되었습니다. [자습서 7](https://docs.beeware.org/en/latest/tutorial/tutorial-7.html)에서는 다른 사용자가 다운로드 할 수 있도록 서류 가방을 사용하여 응용 프로그램을 게시합니다.



## Tutorial 7 - Publishing your app!

애플리케이션 용 설치 프로그램을 만든 후에는 전 세계와 공유하고 싶을 것입니다. Briefcase 은 애플리케이션을 게시하는 방법에 대한 다양한 옵션과 함께 이 프로세스를 관리하기위한 게시 명령을 제공합니다.

**Coming soon**

### Next steps

이것은 BeeWare 프로젝트에서 제공하는 도구로 할 수있는 일에 대한 맛이었습니다. 여기서 당신이하는 일은 당신에게 달려 있습니다!

여기에서 갈 곳 :

- [Toga 위젯 툴킷](https://toga.readthedocs.io/en/latest/tutorial/index.html)의 기능을 보여주는 튜토리얼.
- [Briefcase 프로젝트를 구성 할 때 사용할 수있는 옵션](https://briefcase.readthedocs.io/en/latest/reference/index.html)에 대한 세부 정보입니다.