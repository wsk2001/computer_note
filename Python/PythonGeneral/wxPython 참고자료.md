# [wxPython Phoenix Tutorial](https://gadgetlip.tistory.com/entry/wxPython-Phoenix-Tutorial)


출처: 

http://gadgetlip.tistory.com/entry/wxPython-Phoenix-Tutorial?category=528070



## 목차

[1. wxPython Phoenix Tutorial](##1. wxPython-Phoenix-Tutorial)
	[wxPython](###wxPython)
	[wxPython Phoenix](###wxPython Phoenix)
	[wxPython Phoenix의 설치](###wxPython Phoenix의 설치)
	[wxPython API](###wxPython API)
		[기본 위젯](####기본 위젯)
		[상위 위젯](####상위 위젯)
		[컨테이너](####컨테이너)
		[행동 위젯](####행동 위젯)
		[정적 위젯](####정적 위젯)
		[기타 위젯](####기타 위젯)
	[상속](###상속)

[2. wxPython Phoenix 첫단계](##2. wxPython Phoenix 첫단계)
	[wx.Frame](###wx.Frame)
		[사이즈와 위치](####사이즈와 위치)
		[화면 중앙에 배치하기](####화면 중앙에 배치하기)

[3. wxPython Phoenix 메뉴와 툴바 1](##3. wxPython Phoenix 메뉴와 툴바 1)
	[간단한 메뉴](###간단한 메뉴)
	[아이콘과 단축키](###아이콘과 단축키)
	[서브메뉴와 분리표시](###서브메뉴와 분리표시)
	[체크메뉴](###체크메뉴)

[4. wxPython Phoenix 메뉴와 툴바 2](##4. wxPython Phoenix 메뉴와 툴바 2)
	[컨텍스트 메뉴](###컨텍스트 메뉴)
	[툴바](###툴바)
	[활성화/비활성화](###활성화/비활성화)

[5. wxPython에서 레이아웃 짜기](##5. wxPython에서 레이아웃 짜기)
	[절대위치](###절대위치)
	[사이저 이용하기](###사이저 이용하기)
		[wx.BoxSizer](####wx.BoxSizer)
		[wx.GridSizer](####wx.GridSizer)
		[wx.FlexGridSizer](####wx.FlexGridSizer)
		[wx.GridBagSizer](####wx.GridBagSizer)
	[wx.GridBagSizer 예제 1](###wx.GridBagSizer 예제 1)
	[wx.GridBagSizer 예제 2](###wx.GridBagSizer 예제 2)

[6. wxPython의 이벤트](##6. wxPython의 이벤트)
	[정의](###정의)
	[간단한 이벤트 예](###간단한 이벤트 예)
	[이벤트 바인드](###이벤트 바인드)
	[이벤트 거부](###이벤트 거부)
	[이벤트 전달](###이벤트 전달)
	[윈도우 id 1](###윈도우 id 1)
	[윈도우 id 2](###윈도우 id 2)
	[윈도우 id 3](###윈도우 id 3)
	[페인트 이벤트](###페인트 이벤트)
	[포커스 이벤트](###포커스 이벤트)
	[키 이벤트](###키 이벤트)

[7. wxPython 대화창 (dialog)](##7. wxPython 대화창 (dialog))
	[간단한 메시지창](###간단한 메시지창)
	[미리 설정된 대화창](###미리 설정된 대화창)
	[메시지 대화창](###메시지 대화창)
	[about 대화창](###about 대화창)
	[대화창 직접 만들기](###대화창 직접 만들기)

[8. wxPython의 여러가지 위젯](##8. wxPython의 여러가지 위젯)
	[wx.Button](###wx.Button)
	[wx.ToggleButton](###wx.ToggleButton)
	[wx.StaticLine](###wx.StaticLine)
	[wx.StaticBox](###wx.StaticBox)
	[wx.ComboBox](###wx.ComboBox)
	[wx.CheckBox](###wx.CheckBox)
	[wx.StatusBar](###wx.StatusBar)
	[wx.RadioButton](###wx.RadioButton)
	[wx.Gauge](###wx.Gauge)
	[wx.Slider](###wx.Slider)
	[wx.SpinCtrl](###wx.SpinCtrl)



## 1. wxPython Phoenix Tutorial

해당 강좌는 zetcode.com에 있는 wxPython의 번역이며  적절치 않은 레거시 코드나 작동하지 않는 코드를 Phoenix용으로 바뀐 코드만 변경되어 적용했을 뿐임을 밝힙니다.

### wxPython

http://wxpython.org

wxPython은 데스크탑용 그래픽 유저인터페이스 어플을 만들기 위한 크로스플랫폼 툴킷이다. wxPython으로 윈도우, 맥, 유닉스 시스템을 위한 데스크탑 어플을 만들 수 있다. wxPython은 C++ 라이브러리인 wxWidget의 래퍼(wrapper)이다.

wxPython의 장점은 개발하기 쉽다는 것과 무료라는 것, 각 OS에 native한 look으로 만들 수 있다는 것이다.

종종 PyQT와 비교되는데 PyQT도 비교적 OS native한 look을 제공하는 반면 상업적 이용을 위해서는 무료가 아니라는 단점이 있다.

PyQT는 결정적으로 Qt Designer라는 툴을 이용해 쉽게 인터페이스를 만들 수 있다는 강력한 장점이 있고 wxPython도 wxWidget의 명성 덕에 여러가지 툴이 있지만 Qt Designer만큼의 칭찬은 받고있지 않다.



### wxPython Phoenix

http://wiki.wxpython.org/ProjectPhoenix)

wxPython Phoenix는 새로운 wxPython의 코드네임이다. 이 프로젝트의 주된 목표는 wxPython을 Python3.x에 대응토록 하는것이고 아울러 더 빠르고, 사용하기 쉽게 바꾸는 것이다. 따로 Phoenix라는 이름을 붙인것은 Python3가 그러하듯이 하위 호환을 보장하지 않으며 개발에 장해가 되었던 불필요한 점을 제거함으로써 새롭게 태어나고자 함이다. 글 작성 시점으로 아직 개발중이다.



### wxPython Phoenix의 설치

현재 기준 아직 개발이 완료되지 않았기 때문에 단순히 pip로 설치되지 않는다. 명령어는 다음과 같다.

(2020.01.04 pip 를 이용한 설치를 지원 한다. 따라서 실행문 수정함 )

```bash
pip3 install wxpython
```

윈도우라면 python.org에서 받은 exe파일을 설치 한 후 pip를 이용하여 해당과같이 설치하면 되며

맥의 경우에도 python.org에서 받은 dmg파일을 이용하여 설치한 파이썬일 경우에만 설치가 가능하다. Homebrew나 Macport를 통해 설치한 파이썬은 컴파일러가 달라 설치되지 않는다.



### wxPython API

wxPython API는 오브젝트와 메소드의 집합이고 위젯은 그래픽 유저인터페이스 어플을 제작하는데 기본적으로 필요한 구조이다.



#### 기본 위젯

기본 위젯은 하위 위젯에 기본적인 기능을 제공한다. 이들은 조상(ancestor)라 불리고 보통 직접 호출되거나 사용되지 않는다.

```
wx.Window, wx.Control, wx.ControlWithItem
```



#### 상위 위젯

이 위젯은 서로 독립적으로 존재한다.

```
wx.PopupWindow, wx.ScrolledWindow, wx.Frame, wx.MDIParentFrame, wx.MDIChildFrame, wx.Dialog
```



#### 컨테이너

컨테이너는 다른 위젯들을 포함한다.

```
wx.ScrolledWindow, wx.Panel, wx.SplitterWindow, wx.Notebook
```



#### 행동 위젯

이 위젯들은 사용자가 직접 조작한다.

```
wx.ToggleButton, wx.CheckBox, wx.TextCtrl, wx.SpinCtrl, wx.ComboBox, wx.BitmapButton, wx.Slider, wx.Choice, wx.RadioButton, wx.Button, wx.ScrollBar, wx.Grid, wx.RadioBox, wx.SpinButton, wx,ListBox
```



#### 정적 위젯

이 위젯들은 정보를 표시해준다. 사용자가 조작할 순 없다.

```
wx.StaticBitmap, wx.StaticBox, wx.Gauge, wx.StaticText, wx.StaticLine
```



#### 기타 위젯

어플의 기본적인 툴바, 메뉴바, 상황(status)바.

```
wx.ToolBar, wx.MenuBar, wx.StatusBar
```



### 상속

wxPython의 위젯들 간에는 특별한 관계가 있고 이 관계는 상속으로 표현된다. 객체지향 프로그램에서 상속은 중요한 부분이다. 위젯들은 상하구조를 이루고 다른 위젯의 기능을 상속 받을 수 있다.

```
버튼이 가지는 상하구조의 예)

(상) wx.Object - wx.EvtHandler - wx.Window - wx.Control - wx.Button (하)

```



어플에 버튼 위젯을 만든다 가정해보자. 버튼 위젯은 4개의 기본 클래스를 상속받아 생성된다. 가장 가까운 것은 wx.Control 클래스다. 버튼 위젯은 작은 윈도우고 이처럼 화면에 나타나는 모든 위젯은 윈도우라 부를 수 있다. 그래서 wx.Window 클래스로부터 상속받는다.

안보이는 오브젝트도 있다. 사이즈, 장치 정보(device context), 지역 정보(locale) 오브젝트다. 보이지만 윈도우가 아닌 것도 있다. 색이나 커서같은 오브젝트다.

또한 모든 위젯이 컨트롤인것은 아니다. 예를들어 wx.Dialog는 컨트롤이 아니다.

어떤 window건 이벤트에 반응 할 수 있다. 버튼 위젯도 마찬가지다. 버튼을 클릭함으로 wx.EVT_COMMAND_BUTTON_CLICKED 이벤트가 실행된다. 버튼 위젯은 wx.EvtHandler를 wx.Window클래스를 상속받고 어떤 위젯이건 이벤트에 반응하려면 wx.EvtHandler를 상속 받아야 한다. 결국은 모든 오브젝트가 wx.Object에 상속받아 생성된다. 이 클래스는 wxPython의 엄마격이다.

 

## 2. wxPython Phoenix 첫단계

작은 윈도우부터 만들어보자.

(wxPython 네이밍 컨벤션은 CamelCase이다.)

```python
import wx
 
app = wx.App()
 
frame = wx.Frame(None, -1, 'simple.py')
frame.Show()
 
app.MainLoop()
```



```python
import wx
```

이 줄은 wxPython모듈을 임포트한다. 이로써 모든 함수와 오브젝트는 wx. 접두어를 갖는다. 다음 줄은 어플리케이션 오브젝트를 만든다.

```
app = wx.App()
```

어떤 wxPython 프로그램이건 간에 단 한개의 어플리케이션 오브젝트를 가져야한다.  



```
frame = wx.Frame(None, -1, 'simple.py')
frame.Show()
```

wx.Frame 오브젝트를 만들었다. wx.Frame 오브젝트는 중요한 컨테이너 위젯이다. 나중에 이 위젯에 대해 자세히 알아보자. wx.Frame 위젯은 다른 위젯의 부모격인 위젯이다. 첫번째 인자로 None을 설정하면 상속받는 부모가 없다는 뜻이다. wx.Frame 위젯을 만들었으면 반드시 Show() 메소드를 선언해줘야 실제로 화면에 나타난다.  



```
app.MainLoop()
```

마지막 줄은 무한 루프다. 어플리케이션이 종료될때까지 모든 이벤트를 입출한다.

이 간단한 예로도 창 최소화, 최대화, 닫기를 할 수 있는데 wxPython이 기본적으로 제공하는 기능이다.



### wx.Frame

wx.Frame위젯은 wxPython중 가장 중요한 위젯이다. 컨테이너 위젯이니 다른 위젯을 포함 할 수 있다. frame이나 dialog가 아닌 어떤 윈도우건 포함 할 수 있다. wx.Frame은 타이틀바, 경계선, 가운데 표시 구역으로 이뤄지고 타이틀바와 경계선은 선택적(optional)이다. 여러가지 flag으로 없앨 수 있다.

다음과 같이 7개의 파라미터를 가질 수 있다.

 ```python
wx.Frame(wx.Window parent,
		int id=-1,
		string title='',
		wx.Point pos = wx.DefaultPosition,
		wx.Size size = wx.DefaultSize,
		style = wx.DEFAULT_FRAME_STYLE,
		string name = 'frame')
 ```

첫번째 파라미터는 기본 값이 없다. 나머지 6개 파라미터는 기본값이 있다.

처음 3개는 반드시 선언해야 하지만 나머지 뒷부분은 선택적이다.

wx.DEFAULT_FRAME_STYLE은 다음 기본값 flag의 모음이다.

```
wx.MINIMIZE_BOX | wx.MAXIMIZE_BOX | wx.RESIZE_BORDER | wx.SYSTEM_MENU | wx.CAPTION | wx.CLOSE_BOX | wx.CLIP_CHILDREN
```



```
import wx
 
app = wx.App()
window = wx.Frame(None, style=wx.MAXIMIZE_BOX | wx.RESIZE_BORDER |
                              wx.SYSTEM_MENU | wx.CAPTION |
                              wx.CLOSE_BOX)
window.Show(True)
 
app.MainLoop()
```



실행해보면 최소화 버튼 선언을 안했기 때문에 비활성화 된것으로 확인된다.

 

#### 사이즈와 위치

두가지 방법으로 어플의 사이즈를 정할 수 있다. 파라미터에 명시해주거나 SetSize()메소드를 사용하면 된다.

 ```
import wx
 
class Example(wx.Frame):
   
    def __init__(self, parent, title):
        # 파이썬2에서는 super(Example, self)라 명시해야한다.
        super().__init__(parent, title=title, size=(250, 200))
             
        self.Show()
 
if __name__ == '__main__': 
    app = wx.App()
    Example(None, title='Size')
    app.MainLoop()
 ```



이 어플은 가로세로 250x200픽셀이다.

비슷한 방법으로 위치를 설정 할 수 있다. 기본적으로 명시를 안하면 보통은 어플 윈도우가 왼쪽 위에 자리하는데 OS나 윈도우매니저에 따라 달라질 수 있다.

위에서 살펴 봤듯이 wx.Frame위젯의 pos 파라미터를 명시하면 그 위치로 설정 할 수 있다.

비슷한 여러 메소드가 있다.

- Move(wx.Point point) : 설정한 값으로 창을 이동한다
- MoveXY(int x, int y) : 설정한 값으로 창을 이동한다
- SetPosition(wx.Point point) : 창의 위치를 정한다
- SetDimensions(x, y, width, height, sizeFlags) : 창의 위치와 사이즈를 정한다

사용 예

```python
import wx
 
class Example(wx.Frame):
   
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(300, 200))
             
        self.Move((800, 250))
        self.Show()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Move')
    app.MainLoop()
```

특별한 경우가 있는데, 창을 최대화 하는것이다. 이 경우 창의 위치는 (0, 0)이 되고 화면을 모두 차지한다. wxPython은 자체적으로 화면의 좌표를 계산해준다. 최대화 하려면 Maximize() 메소드를 사용하면 된다. 



#### 화면 중앙에 배치하기

```python
import wx
 
class Example(wx.Frame):
   
    def __init__(self, parent, title):
        super().__init__(parent, title=title,
            size=(300, 200))
         
        #self.Center()도 가능
        self.Centre()
        self.Show()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Center')
    app.MainLoop()
```



self.Centre() 혹은 self.Center() 메소드는 창을 화면의 가운데에 위치시켜준다.  



## 3. wxPython Phoenix 메뉴와 툴바 1

그래픽 유저 인터페이스를 갖는 어플의 기본 구성중 하나는 메뉴바일 것이다.

메뉴바는 메뉴라고 불리는 오브젝트로 이루어진다. 상위 메뉴는 메뉴바에 등록될때 각자 이름을 갖고 등록되고 각각 메뉴 아이템을 갖고 있다. 메뉴는 또한 서브메뉴를 가질 수 있다. wx.MenuBar, wx.Menu, wx.MenuItem 세개의 클래스가 메뉴바를 만드는데 이용된다.

### 간단한 메뉴

```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
         
        self.initui()
 
    def initui(self):
        menubar = wx.MenuBar()
        menu_file = wx.Menu()
        menu_file_item = menu_file.Append(wx.ID_EXIT, 'Quit', 'Quit App')
         
        menubar.Append(menu_file, '&File')
        self.SetMenuBar(menubar)
 
        self.Bind(wx.EVT_MENU, self.onquit, menu_file_item)
 
        self.Show(True)
 
    def onquit(self, e):
        self.Close()
 
app = wx.App()
Example(None)
app.MainLoop()
```



```
menubar = wx.MenuBar()
```

먼저 메뉴바 오브젝트를 만든다. 



```
menu_file = wx.Menu()
```

그 다음 메뉴 오브젝트를 만든다. 



```
menu_file_item = menu_file.Append(wx.ID_EXIT, 'Quit', 'Quit App')
```

메뉴 오브젝트에 메뉴를 넣어준다. 첫번째 파라미터는 메뉴 아이템의 ID인데, 기본 제공되는 표준 ID는 자동으로 아이콘과 단축키가 등록된다. (Ctrl+Q)

두번째 파라미터는 메뉴 아이템의 이름이고 세번째는 상태바에 표시되는 도움말 성격의 문자열이다.

여기서 wx.MenuItem을 명시하지 않았는데 Append()메소드를 통해 자동으로 생성된것이다.

이 메소드는 만들어진 메뉴 아이템을 반환한다.



```
self.Bind(wx.EVT_MENU, self.onquit, menu_file_item)
```

wx.EVT_MENU를 직접 만든 onquit()메소드에 바인드한다. 이 메소드는 어플을 종료한다. 



```
menubar.Append(menu_file, '&File')
self.SetMenuBar(menubar)
```

마지막으로 메뉴를 메뉴바에 등록한다. '&' 표시는 액셀레이터 키라해서 그 뒤에 나오는 문자에 밑줄을 긋고 단축키로 만들어준다.

Alt+F를 누르면 해당 메뉴에 접근 할 수 있다. 이후 SetMenuBar()를 호출해주면 되고 이 메소드는 wx.Frame위젯에 종속 되있는것이다. 메뉴바를 세팅해준다.

맥에서 실행해보면 파일메뉴에 아무것도 없는걸 확인 할 수 있는데, 맥은 어플 종료 버튼이 다른 메뉴에 있지 않고 메뉴바 최좌측 어플리케이션 제목 메뉴에 이미 있기 때문에 따로 만들어도 파일메뉴에서 누락된다. 다른 OS에서는 파일메뉴에 등록된것을 확인 할 수 있다. 실제로 보통 맥에서 실행되는 다른 프로그램들도 파일메뉴에 종료 버튼이 없다. 액셀레이터키도 맥에서는 해당하지 않는다.



### 아이콘과 단축키

전 소스와 같지만 이번에는 wx.MenuItem을 수동으로 만들어보자.

```python
import wx
 
APP_EXIT = 1
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        menubar = wx.MenuBar()
        menu_file = wx.Menu()
        menu_file_quit = wx.MenuItem(menu_file, APP_EXIT, '&Quit\tCtrl+Q')
        menu_file_quit.SetBitmap(wx.Bitmap('exit.png'))
         
        #wxPython Classic 에서는 AppendItem을 사용해야 한다.
        menu_file.Append(menu_file_quit)
 
        self.Bind(wx.EVT_MENU, self.onquit, id=APP_EXIT)
 
        menubar.Append(menu_file, '&File')
        self.SetMenuBar(menubar)
 
        self.Show(True)
 
    def onquit(self, e):
        self.Close()
 
app = wx.App()
Example(None)
app.MainLoop()
```



```python
menu_file_quit = wx.MenuItem(menu_file, APP_EXIT, '&Quit\tCtrl+Q')
menu_file_quit.SetBitmap(wx.Bitmap('exit.png'))
 
menu_file.Append(menu_file_quit)
```

wx.MenuItem 오브젝트를 만들었다. '&'키를 액셀레이터 키를 명시해주고 실제 단축키는 컨트롤+Q로 만들었다. 해당 단축키를 만들면 어플이 종료된다. 단축키 지정 앞에 탭 키를 만들었는데 이럼으로 메뉴 제목 다음에 공백을 만들 수 있다. 아이콘을 지정하기 위해 SetBitmap()메소드를 지정했으며 Append()로 메뉴에 등록했다. 



```
self.Bind(wx.EVT_MENU, self.onquit, id=APP_EXIT)
```

어플에서 메뉴를 클릭하면 onquit() 메소드가 호출된다.

이렇게 만들면 맥에서 실행 시 위 예제와 달리 파일메뉴에 종료 메뉴가 생성됨을 확인 할 수 있다. Ctrl+Q 단축키는 자동으로 Cmd+Q로 치환된것을 확인 할 수 있다. 종료 버튼이 2개가 됨으로 일관성 측면에서 바람직하지 않다.



### 서브메뉴와 분리표시

모든 메뉴는 서브메뉴를 가질 수 있기 때문에 비슷한 기능을 묶어서 하나의 서브메뉴로 지정하면 좋다. 또한 메뉴에는 구분을 위해 분리표시를 넣을 수 있다. 예를들어 새 파일, 열기, 저장과 인쇄, 인쇄 미리보기를 구분할 수 있다.

 ```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.initui()
 
    def initui(self):
        menubar = wx.MenuBar()
 
        menu_file = wx.Menu()
        menu_file.Append(wx.ID_NEW, '&New')
        menu_file.Append(wx.ID_OPEN, '&Open')
        menu_file.Append(wx.ID_SAVE, '&Save')
        menu_file.AppendSeparator()
 
        menu_file_import = wx.Menu()
        menu_file_import.Append(wx.ID_ANY, 'Import newsfeed list...')
        menu_file_import.Append(wx.ID_ANY, 'Import bookmarks...')
        menu_file_import.Append(wx.ID_ANY, 'Import mail...')
 
        #wxPython Classic에서는 AppendMenu() 메소드를 사용해야한다.
        menu_file.Append(wx.ID_ANY, 'I&mport', menu_file_import)
 
        menu_file_quit = menu_file.Append(wx.ID_EXIT, '&Quit')
        self.Bind(wx.EVT_MENU, self.onquit, menu_file_quit)
 
        menubar.Append(menu_file, '&File')
        self.SetMenuBar(menubar)
 
        self.Show(True)
 
    def onquit(self, e):
        self.Close()
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
 ```

이 예에서 새 파일, 열기, 저장과 같은 표준 메뉴아이템을 만들었다. 그리고 이 메뉴들과 서브메뉴는 가로선으로 구분된다.

 

### 체크메뉴

메뉴 아이템의 종류는 세가지다. 보통 아이템, 체크 아이템, 라디오 아이템.

체크 아이템 메뉴을 통해 상태바(status bar)와 툴바를 제어하는 예제를 만들어보자.

 ```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.initui()
 
    def initui(self):
        menubar = wx.MenuBar()
        menu_file = wx.Menu()
        menu_view = wx.Menu()
 
        self.showsb = menu_view.Append(wx.ID_ANY, 'Show Statusbar', 'Toggle Statusbar', kind=wx.ITEM_CHECK)
        self.showtb = menu_view.Append(wx.ID_ANY, 'Show Toolbar', 'Toggle Toolbar', kind=wx.ITEM_CHECK)
 
        menu_view.Check(self.showsb.GetId(), True)
        menu_view.Check(self.showtb.GetId(), True)
 
        self.Bind(wx.EVT_MENU, self.toggle_statusbar, self.showsb)
        self.Bind(wx.EVT_MENU, self.toggle_toolbar, self.showtb)
 
        menubar.Append(menu_file, '&File')
        menubar.Append(menu_view, '&View')
        self.SetMenuBar(menubar)
 
        self.toolbar = self.CreateToolBar()
        # wxPython Classic에서는 AddLabelTool()사용
        self.toolbar.AddTool(1, '', wx.Bitmap('exit.png'))
        self.toolbar.Realize()
 
        self.statusbar = self.CreateStatusBar()
        self.statusbar.SetStatusText('Ready')
 
        self.Show(True)
 
    def toggle_statusbar(self, e):
        if self.showsb.IsChecked():
            self.statusbar.Show()
        else:
            self.statusbar.Hide()
 
    def toggle_toolbar(self, e):
        if self.showtb.IsChecked():
            self.toolbar.Show()
        else:
            self.toolbar.Hide()
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
 ```



```python
self.showsb = menu_view.Append(wx.ID_ANY, 'Show Statusbar', 'Toggle Statusbar', kind=wx.ITEM_CHECK)
self.showtb = menu_view.Append(wx.ID_ANY, 'Show Toolbar', 'Toggle Toolbar', kind=wx.ITEM_CHECK)
```

체크메뉴 아이템을 추가하려면 kind파라미터로 wx.ITEM_CHECK를 지정해주면 된다. 지정하지 않을 시 기본 파라미터는 wx.ITEM_NORMAL이다. Append()메소드는 wx.MenuItem을 자동으로 반환한다.  



```
menu_view.Check(self.showsb.GetId(), True)
menu_view.Check(self.showtb.GetId(), True)
```

어플리케이션이 시작되면 상태바와 툴바가 있는 상태로 시작되기 때문에 Check()메서드로 두 체크메뉴 각각 체크해준다.  



```
def toggle_statusbar(self, e):
    if self.showsb.IsChecked():
        self.statusbar.Show()
    else:
        self.statusbar.Hide()
```

체크메뉴 아이템의 체크된 상태에 따라 상태바를 나태내거나 숨긴다. IsChecked()메서드로 상태를 파악할 수 있다.  



## 4. wxPython Phoenix 메뉴와 툴바 2

### 컨텍스트 메뉴

컨텍스트 메뉴는 본문 중간에 나타나는 메뉴다. 예를들어 브라우저에서 웹페이지를 띄워놓고 마우스 오른쪽 버튼을 누르면 나오는 메뉴가 컨텍스트 메뉴다. 여기서 새로고침 하거나 뒤로 가거나 페이지 소스를 볼 수 있다. 툴바에서 오른쪽 버튼을 누른다면 툴바를 변경할 수 있는 다른 컨텍스느 메뉴가 나온다. 컨텍스트 메뉴는 팝업 메뉴라고도 불린다.

 ```python
import wx
 
class MyPopupMenu(wx.Menu):
    def __init__(self, parent):
        super().__init__()
        self.parent = parent
 
        mini = wx.MenuItem(self, wx.NewId(), 'Minimize')
        # wxPython Classic에서는 AppendItem 사용
        self.Append(mini)
        self.Bind(wx.EVT_MENU, self.onminimize, mini)
 
        quit = wx.MenuItem(self, wx.NewId(), 'Close')
        # wxPython Classic에서는 AppendItem 사용
        self.Append(quit)
        self.Bind(wx.EVT_MENU, self.onquit, quit)
 
    def onminimize(self, e):
        self.parent.Iconize()
 
    def onquit(self, e):
        self.parent.Close()
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.initui()
 
    def initui(self):
        self.Bind(wx.EVT_RIGHT_DOWN, self.onrightdown)
 
        self.Show(True)
 
    def onrightdown(self, e):
        self.PopupMenu(MyPopupMenu(self), e.GetPosition())
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
 ```

이 예는 메인 윈도우에서 컨텍스트 메뉴가 생성되도록 만들어졌다.

 

```
class MyPopupMenu(wx.Menu):
    def __init__(self, parent):
        super().__init__()
        self.parent = parent
```

따로 wx.Menu클래스를 만들었다. 



```
mini = wx.MenuItem(self, wx.NewId(), 'Minimize')
self.Append(mini)
self.Bind(wx.EVT_MENU, self.onminimize, mini)
```

메뉴 아이템을 만들었고 컨텍스트 메뉴에 넣었다. 이벤트 핸들러가 이 메뉴 아이템에 바인드 되었다. 



```
self.Bind(wx.EVT_RIGHT_DOWN, self.onrightdown)
```

마우스 우클릭을 하면 onrightdown()메서드를 호출한다. 이를 위해 wx.EVT_RIGHT_DOWN 이벤트를 바인드했다.  



```
def onrightdown(self, e):
    self.PopupMenu(MyPopupMenu(self), e.GetPosition())
```

이 메서드에서 PopupMenu() 메서드를 불렀다. 이 메서드는 컨텍스트 메뉴를 보여준다. 첫번째 파라미터는 보여질 메뉴이고 두번째는 위치이다. 이 예제에서는 마우스 커서 위치에 맞춰서 나오게끔 되어있다. 이벤트 오브젝트에서 지원하는 GetPosition()메서드로 실제 마우스 위치를 알 수 있다.  



### 툴바

어플리케이션을 사용하면서 수행하는 명령 중 같은 종류의 것을 모아 메뉴로 만들고 이중 자주 사용하는 것을 툴바로 만들면 좋다.

툴바를 만들기 위해서는 프레임 위젯의 CreateToolBar()메서드를 사용한다.

 

```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.initui()
 
    def initui(self):
        toolbar = self.CreateToolBar()
        # wxPython Classic에서는 AddLabelTool()사용
        toolbar_item_quit = toolbar.AddTool(wx.ID_ANY, 'Quit', wx.Bitmap('exit.png'))
        toolbar.Realize()
 
        self.Bind(wx.EVT_TOOL, self.onquit, toolbar_item_quit)
 
        self.Show(True)
 
    def onquit(self, e):
        self.Close()
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
```



```
toolbar = self.CreateToolBar()
```

툴바를 만들었다. 기본적으로 툴바는 가로로 만들어지고 아이콘에는 경계선이 없다. 



```
toolbar_item_quit = toolbar.AddTool(wx.ID_ANY, 'Quit', wx.Bitmap('exit.png'))
```

툴바 아이콘을 만들기 위해 AddTool() 메서드를 사용했다. 두번째 파라미터는 툴의 라벨이고 세번째는 툴의 아이콘 이미지다. 실행해보면 툴의 라벨은 안보이는데, 기본값이 아이콘만 보이는 스타일이어서 그렇다.  



```
toolbar.Realize()
```

툴바에 아이템을 넣고 나서 Realize()메서드를 사용한다. 이 메서드는 리눅스에서는 꼭 필요한건 아니지만 윈도우와 맥에서는 꼭 필요하다.  



```
self.Bind(wx.EVT_TOOL, self.onquit, toolbar_item_quit)
```

툴바 이벤트를 실행하기 위해 wx.EVT_TOOL 이벤트 바인더를 사용했다.

툴바를 2개 이상 만들기 위해서는 조금 다르게 해야한다.

 ```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.initui()
 
    def initui(self):
        vbox = wx.BoxSizer(wx.VERTICAL)
 
        toolbar1 = wx.ToolBar(self)
        toolbar1.AddTool(wx.ID_ANY, '', wx.Bitmap('icon/new.png'))
        toolbar1.AddTool(wx.ID_ANY, '', wx.Bitmap('icon/open.png'))
        toolbar1.AddTool(wx.ID_ANY, '', wx.Bitmap('icon/save.png'))
        toolbar1.Realize()
 
        toolbar2 = wx.ToolBar(self)
        toolbar2_item_quit = toolbar2.AddTool(wx.ID_ANY, '', wx.Bitmap('icon/quit.png'))
        toolbar2.Realize()
 
        vbox.Add(toolbar1, 0, wx.EXPAND)
        vbox.Add(toolbar2, 0, wx.EXPAND)
 
        self.Bind(wx.EVT_TOOL, self.onquit, toolbar2_item_quit)
 
        self.SetSizer(vbox)
 
        self.Show(True)
 
    def onquit(self, e):
        self.Close()
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
 ```

툴바 오브젝트를 2개 만들어서 vertical box에 넣는 식이다.

자세한 내용은 나중에 설명 하기로 하자.



### 활성화/비활성화

툴바 버튼을 비활성화 하는 법을 알아보자. 툴바에서 분리 표시도 알아보자.

```python
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        self.count = 5
 
        self.toolbar = self.CreateToolBar()
 
        toolbar_undo = self.toolbar.AddTool(wx.ID_UNDO, '', wx.Bitmap('icon/undo.png'))
        toolbar_redo = self.toolbar.AddTool(wx.ID_REDO, '', wx.Bitmap('icon/redo.png'))
 
        self.toolbar.EnableTool(wx.ID_REDO, False)
 
        self.toolbar.AddSeparator()
 
        toolbar_exit = self.toolbar.AddTool(wx.ID_EXIT, '', wx.Bitmap('icon/quit.png'))
 
        self.toolbar.Realize()
 
        self.Bind(wx.EVT_TOOL, self.onquit, toolbar_exit)
        self.Bind(wx.EVT_TOOL, self.onundo, toolbar_undo)
        self.Bind(wx.EVT_TOOL, self.onredo, toolbar_redo)
 
        self.Show(True)
 
    def onundo(self, e):
        if self.count > 1 and self.count <= 5:
            self.count = self.count - 1
        if self.count == 1:
            self.toolbar.EnableTool(wx.ID_UNDO, False)
        if self.count == 4:
            self.toolbar.EnableTool(wx.ID_REDO, True)
 
    def onredo(self, e):
        if self.count < 5 and self.count >= 1:
            self.count = self.count + 1
        if self.count == 5:
            self.toolbar.EnableTool(wx.ID_REDO, False)
        if self.count == 2:
            self.toolbar.EnableTool(wx.ID_UNDO, True)
 
    def onquit(self, e):
        self.Close()
 
def main():
    app = wx.App()
    Example(None)
    app.MainLoop()
 
if __name__ == '__main__':
    main()
```

3개의 툴바 버튼을 만들었다. 총 4개의 단계를 갖고 언두/리두가 상호 작용한다. 



```
self.toolbar.EnableTool(wx.ID_REDO, False)
self.toolbar.AddSeparator()
```

처음부터 리두 버튼은 EnableTool()메서드로 비활성화 된다.

AddSeparator()메서드로 기능이 다른 종료 버튼은 따로 표시해두었다.



```python
def onundo(self, e):
    if self.count > 1 and self.count <= 5:
        self.count = self.count - 1
    if self.count == 1:
        self.toolbar.EnableTool(wx.ID_UNDO, False)
    if self.count == 4:
        self.toolbar.EnableTool(wx.ID_REDO, True)
```

언두/리두 기능을 시뮬레이션 한 예제이다. 언두 할게 없으면 버튼은 비활성화 된다. 



## 5. wxPython에서 레이아웃 짜기

보통 어플리케이션은 여러가지 위젯으로 구성되어있다. 이 위젯들은 컨테이너 위젯 안에 위치하고 개발자는 이를 조정해야한다.

wxPython에서는 두가지 옵션으로 조정할 수 있다.

- 절대위치 (absolute positioning)
- 사이저 (sizers)



### 절대위치

개발자는 위젯의 위치와 크기를 픽셀 단위로 지정할 수 있다. 절대위치를 사용할 때는 몇가지 알아둬야한다.

- 윈도우 크기를 바꾸어도 위젯의 사이즈와 크기는 변하지 않는다.
- 어플리케이션이 플랫폼에 따라 다르게 보일 수 있다.
- 어플리케이션에 사용된 폰트를 변경하면 레이아웃이 망가질 수 있다.
- 레이아웃을 변경하고싶으면 처음부터 다시 시작해야한다.

다음 얘제에서는 텍스트 편집기의 기본적은 뼈대를 만들었다. wx.TextCtrl의 사이즈는 윈도우 사이즈를 변경해도 변하지 않는다.

 

```python
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(260, 180))
        self.initui()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self, -1)
 
        menubar = wx.MenuBar()
        menu_file = wx.Menu()
        menu_edit = wx.Menu()
        menu_help = wx.Menu()
 
        menubar.Append(menu_file, '&File')
        menubar.Append(menu_edit, '&Edit')
        menubar.Append(menu_help, '&Help')
        self.SetMenuBar(menubar)
 
        wx.TextCtrl(panel, pos=(3, 3), size=(250, 150))
 
 
def main():
    app = wx.App()
    Example(None, title='')
    app.MainLoop()
 
if __name__ == '__main__':
    main()
```

wx.TextCtrl을 x축 3픽셀, y축 3픽셀로 가로, 세로 250, 150픽셀로 만드는 예제이다. 



### 사이저 이용하기

사이저를 이용해 절대위치로 만드는 위젯이 갖는 여러 문제를 해결 할 수 있다. 다음 사이저 중에 한가지를 고를 수 있다.

- wx.BoxSizer
- wx.StaticBoxSizer
- wx.GridSizer
- wx.FlexGridSizer
- wx.GridBagSizer



#### wx.BoxSizer

이 사이저는 행이나 열로 여러가지 위젯을 넣을 수 있게 해준다. 이미 있는 사이저에 다른 사이저를 넣을 수도 있다. 이런식으로 아주 복잡한 레이아웃도 제작 할 수 있다.

 

```
box = wx.BoxSizer(정수 방향)
box.Add(wx.Window window, 정수 비율=0, 정수 flag=0, 정수 경계선=0)
```

"방향"(orientation)은 wx.VERTICAL이나 wx.HORIZONTAL이 될 수 있다. wx.BoxSizer에 위젯을 넣는것은 Add()메서드를 이용해 할 수 있다. 이를 이해하기 위해 이 파라미터를 볼 필요가 있다.

"비율"(proportion) 파라미터는 정해진 방향에서 얼마나 변할지 비율을 정하는 것이다. 비율이 0, 1, 2인 세개의 버튼이 있다 가정하자. 그리고 이들은 가로 wx.BoxSizer에 들어있다. 비율이 0인 버튼은 전혀 변하지 않는다. 비율 2인 버튼은 비율 1인 버튼보다 두배 만큼 변한다.

"flag" 파라미터는 wx.BoxSizer안에서 어떻게 놓여질지 여러가지 정할 수 있다. 위젯의 경계선을 정 할 수 있고, 위젯간의 간격을 픽셀단위로 정할 수 있다. 경계선을 만들기 위해서는 어떤 면으로 만들지 정해줘야 한다. "|" 표시로 여러가지를 혼용해서 쓸 수 있다. (wx.LEFT | wx.BOTTOM) 다음 플랙중 정해서 쓰면된다.

- wx.LEFT
- wx.RIGHT
- wx.BOTTOM
- wx.TOP
- wx.ALL

 

```python
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(260, 180))
        self.initui()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self)
 
        panel.SetBackgroundColour('#4f5049')
        verticalbox = wx.BoxSizer(wx.VERTICAL)
 
        field = wx.Panel(panel)
        field.SetBackgroundColour('#ededed')
 
        verticalbox.Add(field, 1, wx.EXPAND | wx.ALL, 20)
        panel.SetSizer(verticalbox)
 
 
def main():
    app = wx.App()
    Example(None, title='')
    app.MainLoop()
 
if __name__ == '__main__':
    main()
```



```
verticalbox.Add(field, 1, wx.EXPAND | wx.ALL, 20)
```

"field"라는 패널에 20 픽셀 테두리를 둘렀다. wx.ALL은 네 면 모두 테두리를 적용시칸다.

wx.EXPAND 플랙을 쓰면 이 위젯은 할당된 모든 면적을 차지한다. 마지막으로 위젯의 정렬을 지정할 수 있다. 다음 플랙을 지정해주면 된다.

- wx.ALIGN_LEFT
- wx.ALIGN_RIGHT
- wx.ALIGN_TOP
- wx.ALIGN_BOTTOM
- wx.ALIGN_CENTER_VERTICAL
- wx.ALIGN_CENTER_HORIZONTAL
- wx.ALIGN_CENTER



```python
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(390, 350))
 
        self.initui()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self)
 
        font = wx.SystemSettings.GetFont(wx.SYS_SYSTEM_FONT)
        font.SetPointSize(9)
 
        verticalbox = wx.BoxSizer(wx.VERTICAL)
 
        horizontalbox1 = wx.BoxSizer(wx.HORIZONTAL)
 
        statictxt1 = wx.StaticText(panel, label='Class Name')
        statictxt1.SetFont(font)
 
        horizontalbox1.Add(statictxt1, flag=wx.RIGHT, border=8)
        txtctrl1 = wx.TextCtrl(panel)
        horizontalbox1.Add(txtctrl1, proportion=1)
        verticalbox.Add(horizontalbox1, flag=wx.EXPAND | wx.LEFT | wx.RIGHT | wx.TOP, border=10)
 
        verticalbox.Add((-1, 10))
 
        horizontalbox2 = wx.BoxSizer(wx.HORIZONTAL)
        statictxt2 = wx.StaticText(panel, label='Matching Classes')
        statictxt2.SetFont(font)
        horizontalbox2.Add(statictxt2)
        verticalbox.Add(horizontalbox2, flag=wx.LEFT | wx.TOP, border=10)
 
        verticalbox.Add((-1, 10))
 
        horizontalbox3 = wx.BoxSizer(wx.HORIZONTAL)
        txtctrl2 = wx.TextCtrl(panel, style=wx.TE_MULTILINE)
        horizontalbox3.Add(txtctrl2, proportion=1, flag=wx.EXPAND)
        verticalbox.Add(horizontalbox3, proportion=1, flag=wx.LEFT|wx.RIGHT|wx.EXPAND, border=10)
 
        verticalbox.Add((-1, 25))
 
        horizontalbox4 = wx.BoxSizer(wx.HORIZONTAL)
        checkbox1 = wx.CheckBox(panel, label='Case Sensitive')
        checkbox1.SetFont(font)
        horizontalbox4.Add(checkbox1)
        checkbox2 = wx.CheckBox(panel, label='Nested Classes')
        checkbox2.SetFont(font)
        horizontalbox4.Add(checkbox2, flag=wx.LEFT, border=10)
        checkbox3 = wx.CheckBox(panel, label='Non-Project Classes')
        checkbox3.SetFont(font)
        horizontalbox4.Add(checkbox3, flag=wx.LEFT, border=10)
        verticalbox.Add(horizontalbox4, flag=wx.LEFT, border=10)
 
        verticalbox.Add((-1, 15))
 
        horizontalbox5 = wx.BoxSizer(wx.HORIZONTAL)
        btn1 = wx.Button(panel, label='OK', size=(70, 30))
        horizontalbox5.Add(btn1)
        btn2 = wx.Button(panel, label='Close', size=(70,30))
        horizontalbox5.Add(btn2, flag=wx.LEFT | wx.Bottom, border=5)
        verticalbox.Add(horizontalbox5, flag=wx.ALIGN_RIGHT | wx.RIGHT, border=10)
 
        verticalbox.Add((-1, 10))
 
        panel.SetSizer(verticalbox)
 
 
 
def main():
    app = wx.App()
    Example(None, title='Go to Class')
    app.MainLoop()
 
if __name__ == '__main__':
    main()
```

한개의 세로(vertical)사이저를 만들고 그 안에 5개의 가로(horizontal)사이저를 넣은 구조이다 



```
font = wx.SystemSettings.GetFont(wx.SYS_SYSTEM_FONT)
font.SetPointSize(9)
```

기본 시스템 폰트가 10픽셀이상 이어서 너무 크다고 판단되면 위와같이 조절 할 수 있다. 



```
verticalbox.Add(horizontalbox3, proportion=1, flag=wx.LEFT|wx.RIGHT|wx.EXPAND, border=10)
verticalbox.Add((-1, 25))
```

우리는 이미 위젯간 간격을 테두리(border)와 플랙 파라미터를 이용해서 조절 할 수 있다는 걸 안다. 하지만 한가지 제약이 있는데 Add()메서드에서 어떤 면에 테두리를 조정할지 명시 할 순 있지만 각각 얼마나 다르게 테두리를 줄지는 명시 할 수 없다. 이 예에서 왼쪽과 오른쪽에 10픽셀씩 부여했다. 하지만 밑바닥쪽에만 25픽셀로 지정할 순 없는거다. 따라서 밑바닥 테두리 굵기는 0픽셀이거나 10픽셀이 되는것이다. 따라서 따로 Add()메서드로 위젯간 간격을 명시해준다. 



```
verticalbox.Add(horizontalbox5, flag=wx.ALIGN_RIGHT | wx.RIGHT, border=10)
```

버튼 두개를 창 오른쪽에 두려면 세가지를 알아야한다. 비율(proportion), 정렬(align)플랙, wx.EXPAND플랙이다. 비율은 0이어야한다. 창 크기를 리사이즈한다고 버튼 크기가 바뀌어선 안되기 때문이다.

wx.EXPAND플랙을 명시해서는 안된다. 버튼은 정해진 한쪽 구석에만 있어야 하기 때문이다.

그리고 wx.ALIGN_RIGHT플랙을 지정해줘야한다. 창의 크기가 변할때마다 가로(horizontal)사이저의 왼쪽부분이 넓어져아하기 때문이다.



#### wx.GridSizer

2차원 표 형태로 위젯을 배치하는 사이저이다. 표의 각 칸은 같은 크기여야한다.

 ```
wx.GridSizer(정수 행=1, 정수 열=0, 정수 가로간격=0, 정수 세로간격=0)
 ```

행과 열 수를 지정해주고 각 칸의 가로세로 간격도 지정해주면 된다.

좋은 예인 계산기를 만들어보자.

 ```python
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(300,250))
 
        self.initui()
        self.Show()
 
    def initui(self):
        menubar = wx.MenuBar()
        menu_file = wx.Menu()
        menubar.Append(menu_file, '&File')
        self.SetMenuBar(menubar)
 
        verticalbox = wx.BoxSizer(wx.VERTICAL)
        self.display = wx.TextCtrl(self, style=wx.TE_RIGHT)
        verticalbox.Add(self.display, flag=wx.EXPAND | wx.TOP | wx.BOTTOM, border=4)
        gridsizer = wx.GridSizer(5, 4, 5, 5)
 
        gridsizer.AddMany([
            (wx.Button(self, label='Cls'), 0, wx.EXPAND),
            (wx.Button(self, label='Bck'), 0, wx.EXPAND),
            (wx.StaticText(self), wx.EXPAND),
            (wx.Button(self, label='Close'), 0, wx.EXPAND),
            (wx.Button(self, label='7'), 0, wx.EXPAND),
            (wx.Button(self, label='8'), 0, wx.EXPAND),
            (wx.Button(self, label='9'), 0, wx.EXPAND),
            (wx.Button(self, label='/'), 0, wx.EXPAND),
            (wx.Button(self, label='4'), 0, wx.EXPAND),
            (wx.Button(self, label='5'), 0, wx.EXPAND),
            (wx.Button(self, label='6'), 0, wx.EXPAND),
            (wx.Button(self, label='*'), 0, wx.EXPAND),
            (wx.Button(self, label='1'), 0, wx.EXPAND),
            (wx.Button(self, label='2'), 0, wx.EXPAND),
            (wx.Button(self, label='3'), 0, wx.EXPAND),
            (wx.Button(self, label='-'), 0, wx.EXPAND),
            (wx.Button(self, label='0'), 0, wx.EXPAND),
            (wx.Button(self, label='.'), 0, wx.EXPAND),
            (wx.Button(self, label='='), 0, wx.EXPAND),
            (wx.Button(self, label='+'), 0, wx.EXPAND)
        ])
        verticalbox.Add(gridsizer, proportion=1, flag=wx.EXPAND)
        self.SetSizer(verticalbox)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Calc')
    app.MainLoop()
 ```

Bck 버튼과 Close버튼 사이 공간을 처리한 방법에 눈여겨 보자. 빈 wx.StaticText를 삽입했다.

AddMany()메서드가 사용되었는데, 여러 위젯을 한번에 넣는 편한 방법이다.

삽입순서도 첫줄부터 순차적으로 된다.



#### wx.FlexGridSizer

2차원 표로 위젯을 배치하는것은 wx.Grid사이저와 비슷하나 좀 더 유연하게 칸의 크기를 변경 할 수 있다.

하지만 한 행의 높이는 같아야하고 한 열의 폭은 같아야한다. wx.Grid처럼 모든 행열이 같은 크기일 필요는 없다.

 

```
wx.FlexGridSizer(정수 행=1, 정수 열=0, 정수 가로간격=0, 정수 세로간격=0)
```

개발자들은 자주 대화창의 형식으로 정보 입력과 수정하는 창을 만들게 되는데, wx.FlexGridSizer가 꽤 유용하게 사용된다. wx.GridSizer로도 같은 일을 할 수 있지만 더 보기가 좋다. 



```
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(300,250))
 
        self.initui()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self)
 
        horizontalbox = wx.BoxSizer(wx.HORIZONTAL)
 
        flexgridsizer = wx.FlexGridSizer(3, 2, 9, 25)
 
        title = wx.StaticText(panel, label="Title")
        author = wx.StaticText(panel, label='Author')
        review = wx.StaticText(panel, label='Review')
 
        txtctrl1 = wx.TextCtrl(panel)
        txtctrl2 = wx.TextCtrl(panel)
        txtctrl3 = wx.TextCtrl(panel, style=wx.TE_MULTILINE)
 
        flexgridsizer.AddMany([
            (title),
            (txtctrl1, 1, wx.EXPAND),
            (author),
            (txtctrl2, 1, wx.EXPAND),
            (review, 1, wx.EXPAND),
            (txtctrl3, 1, wx.EXPAND)
        ])
        flexgridsizer.AddGrowableRow(2, 1)
        flexgridsizer.AddGrowableCol(1, 1)
 
        horizontalbox.Add(flexgridsizer, proportion=1, flag=wx.ALL | wx.EXPAND, border=15)
        panel.SetSizer(horizontalbox)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Review')
    app.MainLoop()
```



```
flexgridsizer.AddGrowableRow(2, 1)
flexgridsizer.AddGrowableCol(1, 1)
```

3행과 2열을 크기 변경하게 만든것이다. 1행 1열이었다면 (0, 0)이라 표시한다.

크기가 변경되는 위젯에 wx.EXPAND를 명시함을 잊어서는 안된다.



#### wx.GridBagSizer

아마도 wxPython중 가장 복잡한 사이저일 것이다. 많은 개발자들이 사용하기 어려워하는데 이러한 사이저는 wxPython에만 있는것은 아니고 다른 툴킷에도 있다. 어렵다 할지라도 쓰기 불가능한 수준은 아니다.

이 사이저는 직접적으로 아이템의 위치를 정할 수 있다. 아이템들은 부가적으로 여러 행이나 열에 걸쳐서 존재 할 수도 있다.



```
wx.GridBagSizer(정수 가로간격, 정수 세로간격)
```

가로세로 간격은 포함된 아이템들의 간격을 말한다. 그리드 상에 Add()메서드로 배치한다.



```
Add(self, 아이템, 튜플 위치, 튜플 범위=wx.DefaultSpan, 정수 플랙=0, 정수 테두리=0, userData=None)
```

아이템은 그리드상 넣고싶은 위젯이며 위치는 가상의 그리드상 어디에 위치할지 지정해준다. 왼쪽 위 칸 좌표는 (0, 0)이다.

범위는 부가적으로 위젯이 어디까지 확장될지 정한다. (예: 확장(3, 2)는 3행 2열만큼 위젯이 화장된다.)

플랙과 테두리는 wx.BoxSizer에서 얘기했던것과 같다.

그리드상 위치한 아이템은 창 크기를 변경 할때마다 각각 사이즈가 변경되거나 원래 사이즈를 유지할 수 있으며 크기변경을 가능케하는 메서드는 다음과같다.

```
AddGrowableRow(정수 행)
AddGrowableCol(정수 열)
```



### wx.GridBagSizer 예제 1

```
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(340, 170))
 
        self.initui()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self)
        sizer = wx.GridBagSizer(4, 4)
 
        txt_rename = wx.StaticText(panel, label='Rename To')
        sizer.Add(txt_rename, pos=(0, 0), flag=wx.TOP | wx.LEFT | wx.BOTTOM, border=5)
 
        txtctrl = wx.TextCtrl(panel)
        sizer.Add(txtctrl, pos=(1, 0), span=(1, 5), flag=wx.EXPAND | wx.LEFT | wx.RIGHT, border=5)
 
        button_ok = wx.Button(panel, label='OK', size=(90, 28))
        button_close = wx.Button(panel, label='Close', size=(90, 28))
        sizer.Add(button_ok, pos=(3, 3))
        sizer.Add(button_close, pos=(3, 4), flag=wx.RIGHT | wx.BOTTOM, border=5)
 
        sizer.AddGrowableCol(1)
        sizer.AddGrowableRow(2)
 
        panel.SetSizerAndFit(sizer)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Rename')
    app.MainLoop()
```

이제부터 윈도우를 하나의 그리드 표로 봐야한다 



```
txt_rename = wx.StaticText(panel, label='Rename To')
sizer.Add(txt_rename, pos=(0, 0), flag=wx.TOP | wx.LEFT | wx.BOTTOM, border=5)
```

'Rename To'라는 텍스트는 왼쪽 위에 배치해야 하니 (0, 0)이라고 위치를 잡아 주었다. 추가로 위, 왼쪽, 아래 부분에 여백을 주었다. 



```
txtctrl = wx.TextCtrl(panel)
sizer.Add(txtctrl, pos=(1, 0), span=(1, 5), flag=wx.EXPAND | wx.LEFT | wx.RIGHT, border=5)
```

wx.TextCtrl은 두번째 줄의 시작부터 위치한다 (1, 0). (우리는 항상 0부터 계산해야함을 잊지 말자) 그리고 1행 5열에 걸쳐 위치한다 (1, 5). 위젯의 좌우에 여백도 넣어준다. 



```
sizer.Add(button_ok, pos=(3, 3))
sizer.Add(button_close, pos=(3, 4), flag=wx.RIGHT | wx.BOTTOM, border=5)
```

네번째 줄에 두개의 버튼을 넣어준다. 3행은 빈줄이다. OK버튼은 4열에, Close버튼은 5열에 넣어준다. 알아둬야 할것은 하나의 위젯에 공백을 넣어주면 모든 행에 적용 된다는 것이다. 그래서 OK버튼에는 따로 하단 여백을 지정하지 않았다. 눈치 챘을 수도 있지만 두 버튼간의 간격은 지정하지 않았다. 



```
sizer = wx.GridBagSizer(4, 4)
```

다음과같이 이미 간격을 정했기 때문이다 



```
sizer.AddGrowableCol(1)
sizer.AddGrowableRow(2)
```

마지막으로 이 대화창이 크기 변경이 잘 되게끔 만들어준다. 2열과 3행을 유동적으로 만들어준다. 이 코드를 코멘트로 처리하고 실행했을 때 어떤일이 벌어지는지 보는것도 좋다.



### wx.GridBagSizer 예제 2

```
import wx
 
class Example(wx.Frame):
    def __init__(self, parent, title):
        super().__init__(parent, title=title, size=(450, 360))
 
        self.initui()
        self.Center()
        self.Show()
 
    def initui(self):
        panel = wx.Panel(self)
        sizer = wx.GridBagSizer(5, 5)
 
        txt1 = wx.StaticText(panel, label='Java Class')
        sizer.Add(txt1, pos=(0, 0), flag=wx.TOP | wx.LEFT | wx.BOTTOM, border=15)
 
        icon = wx.StaticBitmap(panel, -1, wx.Bitmap('icon/settings.png'))
        sizer.Add(icon, pos=(0, 4), flag=wx.TOP | wx.RIGHT | wx.ALIGN_RIGHT, border=10)
 
        line = wx.StaticLine(panel)
        sizer.Add(line, pos=(1, 0), span=(1, 5), flag=wx.EXPAND | wx.BOTTOM, border=10)
 
        txt2 = wx.StaticText(panel, label='Name')
        sizer.Add(txt2, pos=(2, 0), flag=wx.LEFT, border=10)
 
        txtctrl1 = wx.TextCtrl(panel)
        sizer.Add(txtctrl1, pos=(2, 1), span=(1, 3), flag=wx.TOP | wx.EXPAND)
 
        txt3 = wx.StaticText(panel, label='Package')
        sizer.Add(txt3, pos=(3, 0), flag=wx.LEFT | wx.TOP, border=10)
 
        txtctrl2 = wx.TextCtrl(panel)
        sizer.Add(txtctrl2, pos=(3, 1), span=(1, 3), flag=wx.TOP | wx.EXPAND, border=5)
 
        button1 = wx.Button(panel, label='Browse...')
        sizer.Add(button1, pos=(3, 4), flag=wx.TOP | wx.RIGHT, border=5)
 
        txt4 = wx.StaticText(panel, label='Extends')
        sizer.Add(txt4, pos=(4, 0), flag=wx.TOP | wx.LEFT, border=10)
 
        combo = wx.ComboBox(panel)
        sizer.Add(combo, pos=(4, 1), span=(1, 3), flag=wx.TOP | wx.EXPAND, border=5)
 
        button2 = wx.Button(panel, label='Browse...')
        sizer.Add(button2, pos=(4, 4), flag=wx.TOP | wx.RIGHT, border=5)
 
        staticbox = wx.StaticBox(panel, label='Optional Attributes')
 
        boxsizer = wx.StaticBoxSizer(staticbox, wx.VERTICAL)
        boxsizer.Add(wx.CheckBox(panel, label='Public'), flag=wx.LEFT | wx.TOP, border=5)
        boxsizer.Add(wx.CheckBox(panel, label='Generate Default Constructor'), flag=wx.LEFT, border=5)
        boxsizer.Add(wx.CheckBox(panel, label='Generate Main Method'), flag=wx.LEFT | wx.BOTTOM, border=5)
        sizer.Add(boxsizer, pos=(5, 0), span=(1, 5), flag=wx.EXPAND | wx.TOP | wx.LEFT | wx.RIGHT, border=10)
 
        button3 = wx.Button(panel, label='Help')
        sizer.Add(button3, pos=(7, 0), flag=wx.LEFT, border=10)
 
        button4 = wx.Button(panel, label='OK')
        sizer.Add(button4, pos=(7, 3))
 
        button5 = wx.Button(panel, label='Cancel')
        sizer.Add(button5, pos=(7, 4), span=(1, 1), flag=wx.BOTTOM | wx.RIGHT, border=10)
 
        sizer.AddGrowableCol(2)
        sizer.AddGrowableRow(6)
 
        panel.SetSizer(sizer)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None, title='Create Java Class')
    app.MainLoop()
```

wx.GridBagSizer와 wx.StaticBoxSizer를 동시에 쓴 예제이다. 



```
line = wx.StaticLine(panel)
sizer.Add(line, pos=(1, 0), span=(1, 5), flag=wx.EXPAND | wx.BOTTOM, border=10)
```

종류를 구분하기 위해 라인을 사용 하였다. 



```
icon = wx.StaticBitmap(panel, -1, wx.Bitmap('icon/settings.png'))
sizer.Add(icon, pos=(0, 4), flag=wx.TOP | wx.RIGHT | wx.ALIGN_RIGHT, border=10)
```

wx.StaticBitmap을 이용하여 첫째줄 오른쪽에 아이콘을 삽입 하였다.

 

```
staticbox = wx.StaticBox(panel, label='Optional Attributes')
boxsizer = wx.StaticBoxSizer(staticbox, wx.VERTICAL)
```

wx.StaticBoxSizer는 wx.BoxSizer와 동일하지만, 사이저 주변으로 박스를 추가한다. 여기서는 체크박스를 그 안에 넣는 식으로 만들었다.  



## 6. wxPython의 이벤트

이벤트는 모든 GUI 어플의 필수 요소이다. 모든 GUI 어플은 이벤트가 이끈다. (event-driven)

어플은 실행되는 동안 발생되는 이벤트에 반응하도록 만들어진다. 이벤트는 주로 사용자가 만들지만 다른 수단이 있을 수 있다(인터넷 연결, 윈도우 매니저, 시간). 그래서 MainLoop()호출 하면 어플은 이벤트가 발생 되기를 기다리는 것이다. MainLoop()메서드도 어플이 종료되면 끝난다.



### 정의

이벤트란 프레임워크(보통 GUI툴킷)에서 발생하는 어플리케이션 레벨의 정보이다.

이벤트 루프(event loop)는 기다리고, 이벤트를 발송하는 프로그래밍 구조이다. 이벤트 루프는 반복적으로 프로세스의 이벤트를 계속 관찰한다. 발송자 (dispatcher)는 이벤트를 이벤트 핸들러에 연결해주는 프로세스이다. 이벤트 핸들러는 이벤트에 반응하는 메서드이다.

이벤트 오브젝트는 이벤트와 관련된 오브젝트(객체)이다. 보통은 윈도우(창)이다.

이벤트 타입은 발생된 특별한 이벤트이다.

이벤트 바인더는 이벤트 타입을 이벤트 핸들러에 바인드 해주는 오브젝트이다.



### 간단한 이벤트 예

```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        wx.StaticText(self, label='x:', pos=(10, 10))
        wx.StaticText(self, label='y:', pos=(10, 30))
 
        self.statictxt1 = wx.StaticText(self, label='', pos=(30, 10))
        self.statictxt2 = wx.StaticText(self, label='', pos=(30, 30))
 
        self.Bind(wx.EVT_MOVE, self.onmove)
 
        self.SetSize((250, 180))
        self.SetTitle('Move Event')
        self.Center()
        self.Show(True)
 
    def onmove(self, e):
        x, y = e.GetPosition()
 
        self.statictxt1.SetLabel(str(x))
        self.statictxt2.SetLabel(str(y))
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

이 예는 현재 윈도우의 위치를 나타내준다. 



```
self.Bind(wx.EVT_MOVE, self.onmove)
```

wx.EVT_MOVE 이벤트 바인더를 onmove()메서드로 바인드 해준다.  



```
def onmove(self, e):
    x, y = e.GetPosition()
 
    self.statictxt1.SetLabel(str(x))
    self.statictxt2.SetLabel(str(y))
```

onmove()의 이벤트 파라미터는 특정 이벤트 타입에 고유한 오브젝트이다. 이 예에서는 wx.MoveEvent 클래스의 인스턴스이다. 이 오브젝트는 이벤트에 대한 정보를 갖고 있다. 예를 들어, 이벤트 오브젝트 이거나 윈도우의 위치말이다. 여기서 이벤트 오브젝트는 wx.Frame 위젯이다. 우리는 GetPosition()이벤트 메서드로 윈도우의 위치를 알 수 있다.  



### 이벤트 바인드

wxPython에서 이벤트와 간련된 작업은 직관적이다. 세가지 단계가 있다.

1. 이벤트 바인더 네임 명시: wx.EVT_SIZE, wx.EVT_CLOSE 같은 것들
2. 이벤트 핸들러를 만든다. 이것은 이벤트가 일어날 때 호출될 메서드이다.
3. 이벤트를 이벤트 핸들러에 바인드한다.

바인드는 Bind()메서드로 한다. 이 메서드는 다음과같은 파라미터를 갖는다.

 

```
Bind(이벤트, 핸들러, source=None, id=wx.ID_ANY, id2=wx.ID_ANY)
```

이벤트는 EVT_로 시작되는 오브젝트다. 이벤트의 종류를 명시한다.

핸들러는 호출될 오브젝트다. 개발자가 이벤트에 바인드 할 메서드다.

source파라미터는 다른 위젯에서의 동일한 이벤트 타입을 구분하기 위해 사용된다.

id 파라미터는 여러개 버튼이나 메뉴 아이템이 있을 때 사용 된다.

id2는 핸들러를 id의 범위에 바인드 할 때 사용된다. 예를들어 EVT_MENU_RANGE와 사용된다.

Bind()메서드는 EvtHandler 클래스에서 정의된다. 이 클래스는 wx.Window를 상속한다. wx.Window는 wxPython에서 사용되는 대부분 위젯의 기반이다.

반대로 언바인드 하고싶을때는 Unbind()를 사용하면 되며, 파라미터는 Bind()와 동일하다.



### 이벤트 거부

가끔 이벤트 처리를 멈춰야 할 때가 있다.



```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        self.Bind(wx.EVT_CLOSE, self.onclosewindow)
 
        self.SetTitle('Event veto')
        self.Show(True)
 
    def onclosewindow(self, e):
        dial = wx.MessageDialog(None, 'Are you sure to quit?', 'Question', wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)
 
        returnvalue = dial.ShowModal()
 
        if returnvalue == wx.ID_YES:
            self.Destroy()
        else:
            e.Veto()
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

이 예에서는 wx.CloseEvent를 처리한다. 타이틀바에서 X버튼을 누르거나 (윈도우 기준)alt+f4를 누를 때 호출 된다. 많은 어플에서 실수로 어플을 종료하는걸 막아야 하는데, 이를 위해서는 wx.EVT_CLOSE를 바인드 해야한다.  



```
dial = wx.MessageDialog(None, 'Are you sure to quit?', 'Question', wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)
 
ret = dial.ShowModal()
```

종료 이벤트 중 대화창을 띄운다 



```
if ret == wx.ID_YES:
    self.Destroy()
else:
    e.Veto()
```

반환되는 값에 따라서 윈도우를 닫거나 이벤트를 거부한다. 윈도우를 닫기 위해서 Destroy()를 써야함을 확인하자. Close()메서드를 사용하면 무한 반복될것이다. 



### 이벤트 전달

두가지 종류의 이벤트가 있다. 기본 이벤트와 명령 이벤트다. 이들은 전달되는게 다르다. 이벤트 전달은 자식 위젯에서 부모, 조부모 위젯 등등 까지의 전달 과정을 말한다. 기본 이벤트는 전달되지 않는다. 명령 이벤트는 전달된다. 예를들어 wx.CloseEvent는 기본 이벤트이다. 이 이벤트가 부모 위젯에게 전달이 될필요는 없지 않은가.

기본적으로 이벤트 핸들러에 바인드된 이벤트는 전달을 멈춘다. 전달을 계속 하려면 Skip()메서드를 써야한다.

 ```
import wx
 
class MyPanel(wx.Panel):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.Bind(wx.EVT_BUTTON, self.onbuttonclicked)
 
    def onbuttonclicked(self, e):
        print('event reached panel class')
        e.Skip()
 
 
class MyButton(wx.Button):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.Bind(wx.EVT_BUTTON, self.onbuttonclicked)
 
    def onbuttonclicked(self, e):
        print('event reached button class')
        e.Skip()
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = MyPanel(self)
 
        MyButton(panel, label='OK', pos=(15, 15))
 
        self.Bind(wx.EVT_BUTTON, self.onbuttonclicked)
 
        self.SetTitle('Propagate event')
        self.Show(True)
 
    def onbuttonclicked(self, e):
        print('event reached frame class')
        e.Skip()
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

패널에 버튼을 붙였다. 이 패널은 프레임 위젯에 위치한다. 그리고 모든 위젯에 핸들러를 정의했다.  



```
def onbuttonclicked(self, e):
    print('event reached button class')
    e.Skip()
```

직접 만든 버튼 클래스로 버튼 클릭 이벤트를 처리했다. Skip()메서드가 패널 클래스로 이벤트를 전달한다. 



```
event reached button class

event reached panel class

event reached frame class
```

버튼을 클릭하면 이같이 나올 것이다. 이벤트가 버튼 -> 패널 -> 프레임 위젯까지 전달 되었다.

Skip()메서드 중 하나를 삭제하고 어떻게 되는지 살펴보자.



### 윈도우 id 1

윈도우 구분자는 이벤트 시스템에서 개별적으로 윈도우를 구분할 수 있는 숫자이다. 윈도우 id를 만들기 위해서는 세가지 방법이 있다.

- 시스템이 자동으로 만드는 id
- 표준 id사용
- 사용자가 직접 id제작

각각 위젯은 id 파라미터가 있다. 이벤트 시스템 하의 유니크한 숫자이다. 여러개 위젯을 사용한다면 각각 다르게 명시해야한다.

 

```
wx.Button(parent, -1)
wx.Button(parent, wx.ID_ANY)
```

-1이나 wx.ID_ANY를 지정해주면 wxPython이 자동으로 id를 만들어준다. 자동으로 만들어진 id는 언제나 음수이다. 반대로 사용자가 지정한 id는 항상 양수여야한다. 보통 위젯의 상태를 바꿀 필요가 없을 때 이런 옵션을 쓴다. 예를 들어 단순 글자 (static text)가 어플이 종료될때까지 한번도 바뀔 일이 없을 때 말이다. 또한 GetId()메서드로 id를 알아낼 수도 있다.  



```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        exitbutton = wx.Button(panel, wx.ID_ANY, 'Exit', (10, 10))
 
        self.Bind(wx.EVT_BUTTON, self.onexit, id=exitbutton.GetId())
 
        self.SetTitle('Automatic id')
        self.Show(True)
 
    def onexit(self, event):
        self.Close()
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

실제 id 값이 뭐였는지는 신경 쓰지 않아도 된다. GetId()메서드로 자동으로 생성된 id를 알 수 있다. 



### 윈도우 id 2

표준 id를 사용하는걸 추천한다. 표준 id는 어떤 플랫폼에서는 몇가지 표준 그래픽과 반응을 제공한다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        grid = wx.GridSizer(3, 2, 0, 0)
 
        grid.AddMany([
            (wx.Button(panel, wx.ID_CANCEL), 0, wx.TOP | wx.LEFT, 9),
            (wx.Button(panel, wx.ID_DELETE), 0, wx.TOP, 9),
            (wx.Button(panel, wx.ID_SAVE), 0, wx.LEFT, 9),
            (wx.Button(panel, wx.ID_EXIT)),
            (wx.Button(panel, wx.ID_STOP), 0, wx.LEFT, 9),
            (wx.Button(panel, wx.ID_NEW))
        ])
 
        self.Bind(wx.EVT_BUTTON, self.onquitapp, id=wx.ID_EXIT)
 
        panel.SetSizer(grid)
 
        self.SetSize((220, 180))
        self.SetTitle('Standard ids')
        self.Show(True)
 
    def onquitapp(self, event):
        self.Close()
 
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

위 예제에서 버튼에 표준id를 사용 했다. 리눅스에서 이 버튼은 아이콘을 갖게된다. wx.ID_CANCEL, wx.ID_DELETE, wx.ID_SAVE, wx.ID_EXIT, wx.ID_STOP, wx.ID_NEW는 표준 id이다.



```
self.Bind(wx.EVT_BUTTON, self.onquitapp, id=wx.ID_EXIT)
```

onquitapp() 이벤트 핸들러로 바인드하는 명령이다. id 파라미터는 버튼들끼리 구분하기 위해 이용된다. 



### 윈도우 id 3

마지막으로 사용자가 직접 id를 만드는 방법이 있다. global id를 만들자.

 ```
import wx
 
ID_MENU_NEW = wx.NewId()
ID_MENU_OPEN = wx.NewId()
ID_MENU_SAVE = wx.NewId()
 
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        self.CreateMenuBar()
        self.CreateStatusBar()
 
        self.SetSize((250, 180))
        self.SetTitle('Global IDs')
        self.Center()
        self.Show(True)
 
    def CreateMenuBar(self):
        menubar = wx.MenuBar()
 
        menu_file = wx.Menu()
        menu_file.Append(ID_MENU_NEW, 'New')
        menu_file.Append(ID_MENU_OPEN, 'Open')
        menu_file.Append(ID_MENU_SAVE, 'Save')
 
        menubar.Append(menu_file, '&File')
        self.SetMenuBar(menubar)
 
        self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_NEW)
        self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_OPEN)
        self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_SAVE)
 
    def displaymessage(self, e):
        statusbar = self.GetStatusBar()
 
        eventid = e.GetId()
 
        if eventid == ID_MENU_NEW:
            msg = 'New menu item selected'
        elif eventid == ID_MENU_OPEN:
            msg = 'Open menu item selected'
        elif eventid == ID_MENU_SAVE:
            msg = 'Save menu item selected'
 
        statusbar.SetStatusText(msg)
         
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

위 예제에서 3개의 메뉴 아이템을 만들었다. 이 메뉴의 아이템에 쓰일 id는 global하게 만들어졌다.



```
ID_MENU_NEW = wx.NewId()
ID_MENU_OPEN = wx.NewId()
ID_MENU_SAVE = wx.NewId()
```

wx.NewId() 메서드는 각각 고유한 새 id를 만들어준다. 



```
self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_NEW)
self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_OPEN)
self.Bind(wx.EVT_MENU, self.displaymessage, id=ID_MENU_SAVE)
```

이 세 메뉴 아이템은 각각 고유의 id로 구분된다. 



```
eventid = e.GetId()
 
if eventid == ID_MENU_NEW:
    msg = 'New menu item selected'
elif eventid == ID_MENU_OPEN:
    msg = 'Open menu item selected'
elif eventid == ID_MENU_SAVE:
    msg = 'Save menu item selected'
```

이벤트 오브젝트 id를 가져와서 id에 따라 메시지를 부여한다. 어플의 상태바에 표시된다.



### 페인트 이벤트

페인트 이벤트는 윈도우가 다시 그려질때 생성된다. 우리가 윈도우를 리사이즈하거나 최대화 할때 말이다. 페인트 이벤트는 프로그램적으로 만들수도 있다. 예를들어 wx.StaticText위젯의 글자를 바꾸기 위해 SetLabel()메서드를 부르면 나타난다. 창을 최소화 할때는 페인트 이벤트가 생성되지 않는다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        self.count = 0
        self.Bind(wx.EVT_PAINT, self.onpaint)
 
        self.SetSize((250, 180))
        self.Center()
        self.Show(True)
 
    def onpaint(self, e):
        self.count += 1
        self.SetTitle(str(self.count))
 
         
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

이 예에서 페인트 이벤트가 생성되면 그 수를 계속 추가해서 프레임 윈도우 타이틀로 설정되게 했다.  



```
self.Bind(wx.EVT_PAINT, self.onpaint)
```

페인트 이벤트를 onpaint()메서드로 바인드 한것이다. 



### 포커스 이벤트

포커스는 현재 선택된 위젯을 가리킨다. 키보드로 입력된 문자나 클립보드에서 붙여넣기 하면 그 선택된 위젯으로 입력된다. 포커스에서 두개의 중요한 이벤트 종류가 있는데, 포커스를 받게 되면 생성되는 wx.EVT_SET_FOCUS와, 포커스를 잃으면 발생하는 wx.EVT_KILL_FOCUS이다. 포커스는 위젯을 클릭하거나 tab(shift+tab)을 입력하면 바뀐다.

 ```
import wx
 
class MyWindow(wx.Panel):
    def __init__(self, parent):
        super().__init__(parent)
 
        self.color = '#b3b3b3'
 
        self.Bind(wx.EVT_PAINT, self.onpaint)
        self.Bind(wx.EVT_SIZE, self.onsize)
        self.Bind(wx.EVT_SET_FOCUS, self.onsetfocus)
        self.Bind(wx.EVT_KILL_FOCUS, self.onkillfocus)
 
    def onpaint(self, e):
        dc = wx.PaintDC(self)
        dc.SetPen(wx.Pen(self.color))
        x, y = self.GetSize()
        dc.DrawRectangle(0, 0, x, y)
 
    def onsize(self, e):
        self.Refresh()
 
    def onsetfocus(self, e):
        self.color = '#0099f7'
        self.Refresh()
 
    def onkillfocus(self, e):
        self.color = '#b3b3b3'
        self.Refresh()
 
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        gridsizer = wx.GridSizer(2, 2, 10, 10)
        gridsizer.AddMany([
            (MyWindow(self), 0, wx.EXPAND | wx.TOP | wx.LEFT, 9),
            (MyWindow(self), 0, wx.EXPAND | wx.TOP | wx.RIGHT, 9),
            (MyWindow(self), 0, wx.EXPAND | wx.BOTTOM | wx.LEFT, 9),
            (MyWindow(self), 0, wx.EXPAND | wx.BOTTOM | wx.RIGHT, 9),
        ])
 
        self.SetSizer(gridsizer)
 
        self.SetSize((350, 250))
        self.SetTitle('Focus event')
        self.Center()
        self.Show(True)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

네개의 패널이 있고 패널이 포커스되면 하이라이트되는 예제이다. 



```
self.Bind(wx.EVT_SET_FOCUS, self.onsetfocus)
self.Bind(wx.EVT_KILL_FOCUS, self.onkillfocus)
```

두개의 포커스 이벤트를 이벤트 핸들러에 바인드한것이다.  



```
def onpaint(self, e):
    dc = wx.PaintDC(self)
    dc.SetPen(wx.Pen(self.color))
    x, y = self.GetSize()
    dc.DrawRectangle(0, 0, x, y)
```

이 메서드에서는 윈도우 위에 그림을 그린다. 외각선 색은 포커스 되었냐 아니냐에 따라 바뀌게 된다. 포커스된 창은 파란색의 외각선을 갖게 된다.  



```
def onsetfocus(self, e):
    self.color = '#0099f7'
    self.Refresh()
```

onsetfocus()메서드에서는 self.color의 색을 파란색으로 바꾼다. 위젯이 자식으로 있는 프레임 윈도우를 새로고침 함으로써 색을 바꾼다.



### 키 이벤트

우리가 키보드의 키를 누르면 wx.KeyEvent가 생성된다. 이 이벤트는 현재 포커스 되어있는 위젯으로 전송된다. 세가지 키 핸들러가 있다.

- wx.EVT_KEY_DOWN
- wx.EVT_KEY_UP
- wx.EVT_CHAR

흔하게 사용되는것은 ESC키가 눌렸을 때 프로그램이 종료되는 것이다.



```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
        panel.Bind(wx.EVT_KEY_DOWN, self.onkeydown)
        panel.SetFocus()
 
        self.SetSize((250, 180))
        self.SetTitle('Key event')
        self.Center()
        self.Show(True)
 
    def onkeydown(self, e):
        key = e.GetKeyCode()
        if key == wx.WXK_ESCAPE:
            returnvalue = wx.MessageBox('Are you sure to quit?', 'Question', wx.YES_NO | wx.NO_DEFAULT, self)
 
            if returnvalue == wx.YES:
                self.Close()
 
         
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()

```

ESC를 누르면 컨펌 메시지가 뜬다. 



```
panel.Bind(wx.EVT_KEY_DOWN, self.onkeydown)
```

이벤트 핸들러를 wx.EVT_KEY_DOWN 이벤트에 바인드했다.  



```
key = e.GetKeyCode()
```

이 코드로 눌러진 키의 키코드를 알 수 있다. 



```
if key == wx.WXK_ESCAPE:
```

키코드를 체크한다. ESC는 wx.WXK_ESCAPE 코드를 갖는다.  



## 7. wxPython 대화창 (dialog)

대화창은 현대의 GUI어플에 꼭 필요한 존재이다. 어플에서 대화창이란 어플에 ‘말하는’ 창이며 데이터를 넣거나 수정하는 역할을 할 수 있다. 사용자와 컴퓨터 프로그램 간의 대화로써의 중요한 의미를 갖는다.



### 간단한 메시지창

메시지창은 사용자에게 간단한 정보를 제공한다. cd 굽는 프로그램이 대표적이다. cd가 다 구워지면 메시지가 팝업된다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        # wxPython Classic에서는 FutureCall()
        wx.CallLater(5000, self.showmessage)
 
        self.SetSize((300, 200))
        self.SetTitle('Message Box')
        self.Center()
        self.Show(True)
 
    def showmessage(self):
        wx.MessageBox('Download completed', 'Info', wx.OK | wx.ICON_INFORMATION)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```



```
wx.CallLater(5000, self.showmessage)
```

첫번째 파라미터는 메서드를 호출할 시간이다. 이 파라미터는 밀리세컨드 단위로 명시하면된다. 두번째 파라미터는 불러올 메서드다.  



```
def showmessage(self):
    wx.MessageBox('Download completed', 'Info', wx.OK | wx.ICON_INFORMATION)
```

wx.MessageBox는 작은 대화창을 보여준다. 세가지 파라미터를 명시했는데, 텍스트 메시지, 타이틀, 그리고 플랙이다. 플랙은 버튼이나 아이콘을 표시하기위해 사용된다. 이 예에서는 OK버튼과 정보 아이콘이다. (맥에서는 아이콘이 안나온다.)

 

### 미리 설정된 대화창

wxPython은 미리 설정된 대화창을 갖고 있다. 텍스트를 보여주거나, 인풋을 받거나, 파일을 열거나 저장하는 등 자주 사용되는 것들이다.



### 메시지 대화창

메시지 대화창은 사용자에게 정보를 보여주지만 위 예의 간단한 대화창보다는 더 유연하다. 여러가지 변형해서 사용할 수 있으며 아이콘이나 버튼을 여러가지로 보여줄 수 있다.

- wx.OK
- wx.CANCEL
- wx.YES_NO
- wx.YES_DEFAULT
- wx.NO_DEFAULT
- wx.ICON_EXCLAMATION
- wx.ICON_ERROR
- wx.ICON_HAND
- wx.ICON_INFORMATION
- wx.ICON_QUESTION

이 플랙들이 wx.MessageDialog 클래스와 사용할 수 있는 플랙이다.

 

```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        horizontalbox = wx.BoxSizer()
        gridsizer = wx.GridSizer(2, 2, 2, 2)
 
        button1 = wx.Button(panel, label='Info')
        button2 = wx.Button(panel, label='Error')
        button3 = wx.Button(panel, label='Qeustion')
        button4 = wx.Button(panel, label='Alert')
 
        gridsizer.AddMany([
            button1, button2, button3, button4
        ])
 
        horizontalbox.Add(gridsizer, 0, wx.ALL, 15)
        panel.SetSizer(horizontalbox)
 
        button1.Bind(wx.EVT_BUTTON, self.showmessage1)
        button2.Bind(wx.EVT_BUTTON, self.showmessage2)
        button3.Bind(wx.EVT_BUTTON, self.showmessage3)
        button4.Bind(wx.EVT_BUTTON, self.showmessage4)
 
        self.SetSize((300, 200))
        self.SetTitle('Messages')
        self.Center()
        self.Show(True)
 
    def showmessage1(self, event):
        dial = wx.MessageDialog(None, 'Download completed', 'Info', wx.OK)
        dial.ShowModal()
 
    def showmessage2(self, event):
        dial = wx.MessageDialog(None, 'Error loading file', 'Error', wx.OK | wx.ICON_ERROR)
        dial.ShowModal()
 
    def showmessage3(self, event):
        dial = wx.MessageDialog(None, 'Are you sure to quit?', 'Question', wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)
        dial.ShowModal()
 
    def showmessage4(self, event):
        dial = wx.MessageDialog(None, 'Unallowed operation', 'Exclamation', wx.OK | wx.ICON_EXCLAMATION)
        dial.ShowModal()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

위의 예제에서 네게의 버튼을 그리드 사이저에 넣었다. 이 버튼은 각기 다른 네개의 대화창을 보여준다. 각각 대화창은 여러가지 다른 스타일의 플랙을 써서 만들었다. 



```
def showmessage2(self, event):
    dial = wx.MessageDialog(None, 'Error loading file', 'Error', wx.OK | wx.ICON_ERROR)
    dial.ShowModal()
```

메시지 대화창을 만드는것은 간단하다. 부모를 정하는 인자에서 None을 지정함으로써 대화창을 toplevel(최상위 레벨)로 지정했다. 그 다음 두 인자는 표시될 메시지와 대화창의 제목이다. wx.OK와 wx.ICON_ERROR를 통해 버튼과 아이콘을 표시했다. (맥에서는 아이콘이 적용되지 않는다) ShowModal()메서드로 대화창을 화면에 표시한다.



### about 대화창

거의 모든 어플이 전형적인 about대화창을 갖고있다.(한국식 번역으로는 주로 어플이름뒤에 ‘정보’가 붙는다. 예를들어 ‘Internet Explorer 정보’) 대게 도움말 메뉴바에 들어가며 이 대화창의 목적은 어플의 이름과 버전의 기본적인 정보를 제공하는 것이다. 과거에는 꽤 간단하게만 사용 되었는데 요즘에는 저자에 관한 추가적인 정보도 제공하고 있다. 도움을 줬던 개발자나 문서 저자의 대한 정보말이다. 어플의 라이센스에 대해 명시하기도 하고 어플이나 제작사의 로고를 표시하기도 한다. wxPython 2.8.x부터 이에 대한 특별한 기능도 제공하고 있다.

어플리케이션 정보 대화창을 만들기 위해서는 두개의 오브젝트를 만들어야 한다. wx.AboutDialogInfo와 wx.AboutBox이다.

wxPython은 두 종류의 정보창을 표시할 수 있다. 이는 어떤 플랫폼을 사용하냐와 어떤 메서드를 부를거냐에 따라 달라진다. 플랫폼 native 대화창일 수도 있고, wxPython에서 제공하는 대화창일 수 있다. MS Windows의 기본 정보창은 개발자가 지정한 아이콘이나 라이센스 명시, url을 표시할 수 없다. 이 세가지를 명시하지 않으면 wxPython은 플랫폼 기본 정보창으로 표시한다. 반대의 경우는 wxPython에서 제공하는 대화창을 표시한다. 플랫폼 기본 정보창을 유지하고싶으면 라이센스같은 정보는 다른 메뉴에서 표시하길 권장한다. GTK+(리눅스)에서는 위 세가지 모두 기본 제공한다.



```
import wx
# wxPython Classic에서는 아래 줄이 필요 없음
import wx.adv
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        menubar = wx.MenuBar()
        menu_help = wx.Menu()
        menu_help.Append(100, '&About')
        self.Bind(wx.EVT_MENU, self.onaboutbox, id=100)
        menubar.Append(menu_help, '&Help')
        self.SetMenuBar(menubar)
 
        self.SetSize((300, 200))
        self.SetTitle('About dialog box')
        self.Center()
        self.Show(True)
 
    def onaboutbox(self, e):
        description = """File Hunter is an advanced file manager for
the Unix operating system. Features include powerful built-in editor,
advanced search capabilities, powerful batch renaming, file comparison,
extensive archive handling and more.
"""
        license = """File Hunter is free software; you can redistribute
it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 2 of the License,
or (at your option) any later version.
 
File Hunter is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details. You should have
received a copy of the GNU General Public License along with File Hunter;
if not, write to the Free Software Foundation, Inc., 59 Temple Place,
Suite 330, Boston, MA  02111-1307  USA"""
         
        # wxPython Classic에서는 wx.AboutDialogInfo() 사용
        info = wx.adv.AboutDialogInfo()
 
        info.SetIcon(wx.Icon('icon/settings.png', wx.BITMAP_TYPE_PNG))
        info.SetName('File Hunter')
        info.SetVersion('1.0')
        info.SetDescription(description)
        info.SetCopyright('(C) 2007-2011 Jan Bodnar')
        info.SetWebSite('http://www.zetcode.com')
        info.SetLicense(license)
        info.AddDeveloper('Jan Bodnar')
        info.AddDocWriter('Jan Bodnar')
        info.AddArtist('The Tango Crew')
        info.AddTranslator('Jan Bodnar')
 
        # wxPython Classic에서는 wx.AboutBox() 사용
        wx.adv.AboutBox(info)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

wxPython Phoenix에서는 wx.adv에 해당 기능이 있기 때문에 따로 임포트가 필요하다.

일단 예시를 간단하게 하기 위해 장문의 텍스트를 코드 중간에 삽입했지만 보통 진짜 프로그래밍 제작시에 흔히 하지는 않는다. 이러한 텍스트는 따로 한곳에 모아 두는것이 좋은데 그래야 유지보수하거나 번역시에 편하기 때문이다.

처음 해야 할것은 wx.adv.AboutDialogInfo() 오브젝트를 만드는것이다. 해당 오브젝트는 파라미터가 필요 없다. 다음으로는 마지막으로 wx.adv.AboutDialogInfo()에 필요한 메서드를 호출해주고 wx.adv.AboutInfo() 위젯을 만들여주면 된다.



### 대화창 직접 만들기

```
import wx
 
class ChangeDepthDialog(wx.Dialog):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
        self.SetSize((250, 200))
        self.SetTitle('Change Color Depth')
 
    def initui(self):
        panel = wx.Panel(self)
        verticalbox = wx.BoxSizer(wx.VERTICAL)
 
        staticbox = wx.StaticBox(panel, label='Colors')
        staticboxsizer = wx.StaticBoxSizer(staticbox, orient=wx.VERTICAL)
        staticboxsizer.Add(wx.RadioButton(panel, label='256 Colors', style=wx.RB_GROUP))
        staticboxsizer.Add(wx.RadioButton(panel, label='16 Colors'))
        staticboxsizer.Add(wx.RadioButton(panel, label='2 Colors'))
 
        horizontalbox1 = wx.BoxSizer(wx.HORIZONTAL)
        horizontalbox1.Add(wx.RadioButton(panel, label='Custom'))
        horizontalbox1.Add(wx.TextCtrl(panel), flag=wx.LEFT, border=5)
        staticboxsizer.Add(horizontalbox1)
 
        panel.SetSizer(staticboxsizer)
 
        horizontalbox2 = wx.BoxSizer(wx.HORIZONTAL)
        button_ok = wx.Button(self, label='OK')
        button_close = wx.Button(self, label='Close')
        horizontalbox2.Add(button_ok)
        horizontalbox2.Add(button_close, flag=wx.LEFT, border=5)
 
        verticalbox.Add(panel, proportion=1, flag=wx.ALL | wx.EXPAND, border=5)
        verticalbox.Add(horizontalbox2, flag=wx.ALIGN_CENTER | wx.TOP | wx.BOTTOM, border=10)
 
        self.SetSizer(verticalbox)
 
        button_ok.Bind(wx.EVT_BUTTON, self.onclose)
        button_close.Bind(wx.EVT_BUTTON, self.onclose)
 
    def onclose(self, e):
        self.Close()
 
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        ID_DEPTH = wx.NewId()
 
        toolbar = self.CreateToolBar()
        # wxPython Classic 에서는 AddLabelTool() 사용
        toolbar.AddTool(ID_DEPTH, '', wx.Bitmap('icon/eye.png'))
        toolbar.Realize()
 
        self.Bind(wx.EVT_TOOL, self.onchangedepth, id=ID_DEPTH)
 
        self.SetSize((300, 200))
        self.SetTitle('Custom dialog')
        self.Center()
        self.Show(True)
 
    def onchangedepth(self, e):
        changedepth = ChangeDepthDialog(None, title='Change Color Depth')
        changedepth.ShowModal()
        changedepth.Destroy()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

위 예제에서 wx.Dialog 위젯을 상속받아 ChangeDepthDialog 대화창을 만들었다. 



```
changedepth = ChangeDepthDialog(None, title='Change Color Depth')
changedepth.ShowModal()
changedepth.Destroy()
```

ChangeDepthDialog 클래스를 규정하고 ShowModal로 대화창을 호출했다. Destroy()를 명시해야함을 잊지 말아야한다. 대화창이 Destroy()되지 않으면 최상위 레벨 윈도우에서 작업(조작)을 할 수 없다. 최상위 레벨 윈도우에 닫기 버튼을 눌러도 프로세스상에 어플이 남아 있을 수도 있다.  



## 8. wxPython의 여러가지 위젯

wxPython의 기본적인 위젯에 대해 알아보자. 위젯은 어플의 기본 구성요소이고 wxPython에서는 여러가지 다양한 위젯을 만들 수 있다.

### wx.Button

wx.Button은 간단한 위젯이며 텍스트 문자열을 갖고 있다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
        button_close = wx.Button(panel, label='Close', pos=(20, 30))
        button_close.Bind(wx.EVT_BUTTON, self.onclose)
 
        self.SetSize((250, 200))
        self.SetTitle('wx.Button')
        self.Center()
        self.Show(True)
 
    def onclose(self, e):
        self.Close(True)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

누르면 어플이 종료되는 버튼을 만들었다.  



```
button_close = wx.Button(panel, label='Close', pos=(20, 30))
```

해당 라인으로 버튼의 라벨과 위치를 지정해주었다. 



```
button_close.Bind(wx.EVT_BUTTON, self.onclose)
```

버튼이 눌리면 wx.EVT_BUTTON 이벤트가 트리거된다. 이벤트 핸들러를 명시해 주었다.



### wx.ToggleButton

wx.ToggleButton은 두가지 상태를 갖고 있다. 눌린 상태, 안눌린 상태. 클릭 함으로써 두가지 상태를 토글 할 수 있다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        self.color = wx.Colour(0, 0, 0)
 
        button_red = wx.ToggleButton(panel, label='red', pos=(20, 25))
        button_green = wx.ToggleButton(panel, label='green', pos=(20, 60))
        button_blue = wx.ToggleButton(panel, label='blue', pos=(20, 100))
 
        self.colorpanel = wx.Panel(panel, pos=(150, 20), size=(110, 110))
        self.colorpanel.SetBackgroundColour(self.color)
 
        button_red.Bind(wx.EVT_TOGGLEBUTTON, self.togglered)
        button_green.Bind(wx.EVT_TOGGLEBUTTON, self.togglegreen)
        button_blue.Bind(wx.EVT_TOGGLEBUTTON, self.toggleblue)
 
        self.SetSize((300, 200))
        self.SetTitle('Toggle Buttons')
        self.Center()
        self.Show(True)
 
    def togglered(self, e):
        obj = e.GetEventObject()
        ispressed = obj.GetValue()
 
        green = self.color.Green()
        blue = self.color.Blue()
 
        if ispressed:
            self.color.Set(255, green, blue)
        else:
            self.color.Set(0, green, blue)
 
        self.colorpanel.SetBackgroundColour(self.color)
        self.colorpanel.Refresh()
 
    def togglegreen(self, e):
        obj = e.GetEventObject()
        ispressed = obj.GetValue()
 
        red = self.color.Red()
        blue = self.color.Blue()
 
        if ispressed:
            self.color.Set(red, 255, blue)
        else:
            self.color.Set(red, 0, blue)
 
        self.colorpanel.SetBackgroundColour(self.color)
        self.colorpanel.Refresh()
 
    def toggleblue(self, e):
        obj = e.GetEventObject()
        ispressed = obj.GetValue()
 
        red = self.color.Red()
        green = self.color.Green()
 
        if ispressed:
            self.color.Set(red, green, 255)
        else:
            self.color.Set(red, green, 0)
 
        self.colorpanel.SetBackgroundColour(self.color)
        self.colorpanel.Refresh()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```

세게의 버튼과 패널이 있다. 토글 버튼을 클릭함으로써 패널의 색을 바꾼다. 



```
button_red = wx.ToggleButton(panel, label='red', pos=(20, 25))
wx.ToggleButton이 만들어졌다.
 
self.colorpanel = wx.Panel(panel, pos=(150, 20), size=(110, 110))
self.colorpanel = SetBackgroundColour(self.color)
```

이 패널은 우리가 토글버튼으로 바꾼 색상을 배경색으로 한다.

빨간색 버튼을 누르면 togglered()이벤트 핸들러가 호출된다.

 

```
def togglered(self, e):
    obj = e.GetEventObject()
    ispressed = obj.GetValue()
 
    green = self.color.Green()
    blue = self.color.Blue()
 
    if ispressed:
        self.color.Set(255, green, blue)
    else:
        self.color.Set(0, green, blue)
 
    self.colorpanel.SetBackgroundColour(self.color)
    self.colorpanel.Refresh()
```

togglered() 메서드에서는 red버튼이 눌렸는지 사실을 확인하여 컬러 패널에 색을 업데이트 해준다. 



### wx.StaticLine

```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        font = wx.Font(10, wx.DEFAULT, wx.NORMAL, wx.BOLD)
        heading = wx.StaticText(self, label='The Central Europe', pos=(130, 15))
        heading.SetFont(font)
 
        wx.StaticLine(self, pos=(25, 50), size=(300, 1))
 
        wx.StaticText(self, label='Slovakia', pos=(25, 80))
        wx.StaticText(self, label='Hungary', pos=(25, 100))
        wx.StaticText(self, label='Poland', pos=(25, 120))
        wx.StaticText(self, label='Czech Republic', pos=(25, 140))
        wx.StaticText(self, label='Germany', pos=(25, 160))
        wx.StaticText(self, label='Slovenia', pos=(25, 180))
        wx.StaticText(self, label='Austria', pos=(25, 200))
        wx.StaticText(self, label='Switzerland', pos=(25, 220))
 
        wx.StaticText(self, label='5 445 000', pos=(250, 80))
        wx.StaticText(self, label='10 014 000', pos=(250, 100))
        wx.StaticText(self, label='38 168 000', pos=(250, 120))
        wx.StaticText(self, label='10 562 000', pos=(250, 140))
        wx.StaticText(self, label='81 799 000', pos=(250, 160))
        wx.StaticText(self, label='2 050 000', pos=(250, 180))
        wx.StaticText(self, label='8 414 000', pos=(250, 200))
        wx.StaticText(self, label='7 866 000', pos=(250, 220))
 
        wx.StaticLine(self, pos=(25, 260), size=(300, 1))
 
        sum = wx.StaticText(self, label='164 336 000', pos=(240, 280))
        sum_font = sum.GetFont()
        sum_font.SetWeight(wx.BOLD)
        sum.SetFont(sum_font)
 
        button = wx.Button(self, label='Close', pos=(140, 310))
 
        button.Bind(wx.EVT_BUTTON, self.onclose)
 
        self.SetSize((360, 380))
        self.SetTitle('wx.StaticLine')
        self.Center()
        self.Show(True)
 
    def onclose(self, e):
        self.Close(True)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```



```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        txt1 = '''I'm giving up the ghost of love
in the shadows cast on devotion
She is the one that i adore
creed of my silent suffocation
Break this bittersweet spell on me
lost in the arms of destiny'''
 
        txt2 = '''There is something in the way
You're always somewhere else
Feelings have deserted me
To a point of no return
I don't believe in God
But I pray for you'''
 
        panel = wx.Panel(self)
        verticalbox = wx.BoxSizer(wx.VERTICAL)
 
        # wx.ALIGN_CENTER 도 가능
        statictxt1 = wx.StaticText(panel, label=txt1, style=wx.ALIGN_CENTRE)
        statictxt2 = wx.StaticText(panel, label=txt2, style=wx.ALIGN_CENTRE)
 
        verticalbox.Add(statictxt1, flag=wx.ALL, border=5)
        verticalbox.Add(statictxt2, flag=wx.ALL, border=5)
        panel.SetSizer(verticalbox)
 
        self.SetSize((250, 260))
        self.SetTitle('Bittersweet')
        self.Center()
        self.Show(True)
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```





### wx.StaticBox

꾸며주는 위젯이다. 여러 위젯을 논리적으로 구분하기 위해 자주 쓰인다. 알아두어야 할 것은 이 위젯 안에 들어갈 위젯보다 먼저 만들어져야 한다는 것이다. 그리고 그 안에 들어가는 위젯이라 할지라도 그 자식의 개념은 아니고 같은 급이어서 상속받아 생성하지 않는다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        wx.StaticBox(panel, label='Personal Info', pos=(5, 5), size=(240, 170))
        wx.CheckBox(panel, label='Male', pos=(15, 30))
        wx.CheckBox(panel, label='Married', pos=(15, 55))
        wx.StaticText(panel, label='Age', pos=(15, 95))
        wx.SpinCtrl(panel, value='1', pos=(55, 90), size=(60, -1), min=1, max=120)
 
        button = wx.Button(panel, label='OK', pos=(90, 185), size=(60, -1))
        button.Bind(wx.EVT_BUTTON, self.onclose)
 
        self.SetSize((270, 250))
        self.SetTitle('Static Box')
        self.Center()
        self.Show(True)
 
    def onclose(self, e):
        self.Close(True)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```





### wx.ComboBox

wx.ComboBox는 한줄로 되어있는 텍스트 필드의 집합이다. 밑 화살표가 그려져 있는 버튼와 리스트 박스의 조합으로 이뤄진다. 버튼을 누르면 리스트가 나오며 사용자는 단 한가지 옵션만 선택할 수 있다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        oslist = ['Ubuntu', 'Arch', 'Fedora', 'Debian', 'Mint']
        combobox = wx.ComboBox(panel, pos=(50, 30), choices=oslist, style=wx.CB_READONLY)
 
        self.statictxt = wx.StaticText(panel, label=oslist[0], pos=(50, 140))
        combobox.Bind(wx.EVT_COMBOBOX, self.onselect)
 
        self.SetSize((250, 230))
        self.SetTitle('wx.ComboBox')
        self.Center()
        self.Show(True)
 
    def onselect(self, e):
        i = e.GetString()
        self.statictxt.SetLabel(i)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```



```
combobox = wx.ComboBox(panel, pos=(50, 30), choices=oslist, style=wx.CB_READONLY)
```

wx.ComboBox 위젯의 choices 파라미터는 스트링 리스트를 받아 표시해준다. wx.CB_READONLY 스타일은 스트링을 읽기 전용으로 만든다.



### wx.CheckBox

wx.CheckBox 위젯은 두가지 상태가 있다. 켜짐, 꺼짐. 라벨이 붙어 있는 박스이며, 라벨은 박스의 왼쪽이나 오른쪽에 있을 수 있다.

```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        checkbox = wx.CheckBox(panel, label='Show title', pos=(20, 20))
        checkbox.SetValue(True)
        checkbox.Bind(wx.EVT_CHECKBOX, self.showorhidetitle)
 
        self.SetSize((250, 170))
        self.SetTitle('wx.CheckBox')
        self.Center()
        self.Show(True)
 
    def showorhidetitle(self, e):
        sender = e.GetEventObject()
        ischecked = sender.GetValue()
 
        if ischecked:
            self.SetTitle('wx.CheckBox')
        else:
            self.SetTitle('')
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```



```
checkbox.SetValue(True)
```

기본적으로 윈도우에 타이틀이 나오기 때문에 wx.CheckBox 위젯을 체크한 상태로 만들었다.



### wx.StatusBar

wx.StatusBar 위젯은 어플의 상태를 표시하기 위해 사용된다. 다른 종류의 정보를 표시하기 위해 여러 부분으로 나뉘어서 만들 수도 있다. 상태바에 다른 위젯을 넣을 수도 있다. 대화창이 남발하거나 싫어하는 사용자를 위해 대안으로 만들 수 있다. wx.StatusBar는 두가지 방법으로 만들 수 있다. 수동으로 wx.StatusBar를 만들고 SetStatusBar()메서드로 호출하는 것과, CreateStatusBar() 메서드를 사용하는 것이다. 후자는 자동으로 wx.StatusBar를 만들어준다.



```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        button = wx.Button(panel, label='Button', pos=(20, 20))
        text = wx.CheckBox(panel, label='CheckBox', pos=(20, 90))
        combo = wx.ComboBox(panel, pos=(120, 22), choices=['Python', 'Ruby'])
        slider = wx.Slider(panel, 5, 6, 1, 10, (120, 90), (110, -1))
 
        panel.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
        button.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
        text.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
        combo.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
        slider.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
 
        self.statusbar = self.CreateStatusBar()
 
        self.SetSize((250, 230))
        self.SetTitle('wx.StatusBar')
        self.Center()
        self.Show(True)
 
    def onwidgetenter(self, e):
        name = e.GetEventObject().GetClassName()
        self.statusbar.SetStatusText(name + 'widget')
        e.Skip()
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

마우스를 위젯 위로 올리면 해당 위젯의 이름이 wx.StatusBar에 표시되는 예제이다.



```
panel.Bind(wx.EVT_ENTER_WINDOW, self.onwidgetenter)
```

위젯의 위치에 마우스를 올리면 wx.EVT_ENTER_WINDOW 이벤트가 생성된다.  



```
self.statusbar = self.CreateStatusBar()
```

해당 메서드로 상태바를 만들었다. 



```
def onwidgetenter(self, e):
    name = e.GetEventObject().GetClassName()
    self.statusbar.SetStatusText(name + 'widget')
    e.Skip()
```

onwidgetenter()메서드 안에서 마우스 포인터로 가리키는 위젯을 파악하여 SetStuatusText()메서드로 상태바 값을 변경한다.

(위 예제는 맥에서 정상작동하지 않는다.)



### wx.RadioButton

wx.RadioButton은 여러 옵션 중 단 한가지를 사용자에게 선택하게끔 하는 위젯이다. 라디오 버튼의 그룹은 첫번째 버튼에 wx.RG_GROUP 이라는 스타일을 지정해야하며, 다른 라디오 버튼은 첫번째 라디오 버튼의 해당 스타일 없이 지정되며, 새롭게 다른 그룹을 만드려면 다시 wx.RG_GROUP을 지정해주면 된다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        self.radiobutton1 = wx.RadioButton(panel, label='Value A', pos=(10, 10), style=wx.RB_GROUP)
        self.radiobutton2 = wx.RadioButton(panel, label='Value B', pos=(10, 30))
        self.radiobutton3 = wx.RadioButton(panel, label='Value C', pos=(10, 50))
 
        self.radiobutton1.SetValue(True)
 
        self.radiobutton1.Bind(wx.EVT_RADIOBUTTON, self.setvalue)
        self.radiobutton2.Bind(wx.EVT_RADIOBUTTON, self.setvalue)
        self.radiobutton3.Bind(wx.EVT_RADIOBUTTON, self.setvalue)
 
        self.statusbar = self.CreateStatusBar(3)
 
        self.statusbar.SetStatusText('True', 0)
        self.statusbar.SetStatusText('False', 1)
        self.statusbar.SetStatusText('False', 2)
 
        self.SetSize((210, 210))
        self.SetTitle('wx.RadioButton')
        self.Center()
        self.Show(True)
 
    def setvalue(self, e):
        state1 = str(self.radiobutton1.GetValue())
        state2 = str(self.radiobutton2.GetValue())
        state3 = str(self.radiobutton3.GetValue())
 
        self.statusbar.SetStatusText(state1, 0)
        self.statusbar.SetStatusText(state2, 1)
        self.statusbar.SetStatusText(state3, 2)
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()

 ```

세개의 라디오버튼을 갖고 있는 한 그룹이 있고, 각 버튼은 상태바 표시에 영향을 미친다. 



```
self.radiobutton1 = wx.RadioButton(panel, label='Value A', pos=(10, 10), style=wx.RB_GROUP)
self.radiobutton2 = wx.RadioButton(panel, label='Value B', pos=(10, 30))
self.radiobutton3 = wx.RadioButton(panel, label='Value C', pos=(10, 50))
 
self.radiobutton1.SetValue(True)
```

스타일에 wx.RB_GROUP을 지정함으로 새로운 그룹을 시작한다.

첫번째 버튼의 값을 True로 지정하는 것은 맥을 위한 조치이며, 윈도우는 기본으로 첫번째 버튼에 선택이 되어있다.



### wx.Gauge

wx.Gauge는 오랜 시간이 걸리는 일의 현재 상태를 표시하기 위해 사용된다.

 ```
import wx
 
TASK_RANGE = 50
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        self.timer = wx.Timer(self, 1)
        self.count = 0
 
        self.Bind(wx.EVT_TIMER, self.ontimer, self.timer)
 
        panel = wx.Panel(self)
        verticalbox = wx.BoxSizer(wx.VERTICAL)
        horizontalbox1 = wx.BoxSizer(wx.HORIZONTAL)
        horizontalbox2 = wx.BoxSizer(wx.HORIZONTAL)
        horizontalbox3 = wx.BoxSizer(wx.HORIZONTAL)
 
        self.gauge = wx.Gauge(panel, range=TASK_RANGE, size=(250, 25))
        self.button1 = wx.Button(panel, wx.ID_OK)
        self.button2 = wx.Button(panel, wx.ID_STOP)
        self.text = wx.StaticText(panel, label='Task to be done')
 
        self.Bind(wx.EVT_BUTTON, self.onok, self.button1)
        self.Bind(wx.EVT_BUTTON, self.onstop, self.button2)
 
        horizontalbox1.Add(self.gauge, proportion=1, flag=wx.ALIGN_CENTER)
        horizontalbox2.Add(self.button1, proportion=1, flag=wx.RIGHT, border=10)
        horizontalbox2.Add(self.button2, proportion=1, flag=wx.RIGHT, border=10)
        horizontalbox3.Add(self.text, proportion=1)
        verticalbox.Add((0, 30))
        verticalbox.Add(horizontalbox1, flag=wx.ALIGN_CENTER)
        verticalbox.Add((0, 20))
        verticalbox.Add(horizontalbox2, proportion=1, flag=wx.ALIGN_CENTER)
        verticalbox.Add(horizontalbox3, proportion=1, flag=wx.ALIGN_CENTER)
 
        panel.SetSizer(verticalbox)
 
        self.SetSize((300, 200))
        self.SetTitle('wx.Gauge')
        self.Center()
        self.Show(True)
 
    def onok(self, e):
        if self.count >= TASK_RANGE:
            return
 
        self.timer.Start(100)
        self.text.SetLabel('Task in Progress')
 
    def onstop(self, e):
        if self.count == 0 or self.count >= TASK_RANGE or not self.timer.IsRunning():
            return
 
        self.timer.Stop()
        self.text.SetLabel('Task Interrupted')
 
    def ontimer(self, e):
        self.count = self.count + 1
        self.gauge.SetValue(self.count)
 
        if self.count == TASK_RANGE:
            self.timer.Stop()
            self.text.SetLabel('Task Completed')
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```



```
self.timer = wx.Timer(self, 1)
self.count = 0
```

wx.Timer를 사용하여 정해진 간격동안 코드가 실행되게 하였다. count 변수는 게이지가 얼마나 찼는지 설정하는 역할이다. 



```
self.gauge = wx.Gauge(panel, range=TASK_RANGE, size=(250, 25))
```

게이지 위젯은 이렇게 사용하면 된다. range 파라미터는 게이지 위젯이 가질 최고 값을 정수로 설정해주면 된다.  



```
def onok(self, e):
    if self.count >= TASK_RANGE:
        return
 
    self.timer.Start(100)
    self.text.SetLabel('Task in Progress')
```

OK 버튼을 누르면 onok()메서드가 호출된다. 먼저 count 변수가 TASK_RANGE 영역 안에 있는지 체크하고 아니라면 메서드에서 벗어난다. 영역 안에 있으면 타이머를 시작하고 StaticText를 업데이트 한다.

 

```
def onstop(self, e):
    if self.count == 0 or self.count >= TASK_RANGE or not self.timer.IsRunning():
        return
 
    self.timer.Stop()
    self.text.SetLabel('Task Interrupted')
```

stop 버튼을 누르면 onstop()메서드가 호출되며, 게이지를 멈추기 위해 조건을 검사한다. 조건에 충족되면 타이머를 멈추고 문자열을 업데이트 한다.  



```
def ontimer(self, e):
    self.count = self.count + 1
    self.gauge.SetValue(self.count)
 
    if self.count == TASK_RANGE:
        self.timer.Stop()
        self.text.SetLabel('Task Completed')
```

타이머를 시작하고 나면 정기적으로 ontimer() 메서드가 호출된다. 이 메서드에서는 count 변수와 게이지 위젯을 업데이트 한다. count변수와 TASK_RANGE 가 서로 같으면 타이머를 멈추고 문자열을 업데이트 한다.



### wx.Slider

wx.Slider 위젯은 간단한 핸들이 있는 위젯이다. 이 핸들은 앞뒤로 움직일 수 있다.

 ```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        slider = wx.Slider(panel, value=200, minValue=150, maxValue=500, pos=(20, 20), size=(250, -1), style=wx.SL_HORIZONTAL)
        slider.Bind(wx.EVT_SCROLL, self.onsliderscroll)
 
        self.txt = wx.StaticText(panel, label='200', pos=(20, 90))
 
        self.SetSize((290, 200))
        self.SetTitle('wx.Slider')
        self.Center()
        self.Show(True)
 
    def onsliderscroll(self, e):
        obj = e.GetEventObject()
        value = obj.GetValue()
 
        self.txt.SetLabel(str(value))
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
 ```



### wx.SpinCtrl

wx.SpinCtrl 위젯은 값을 줄이거나 올리거나 하는 UI를 제공한다. 이를 위해 두개의 버튼이 있고 사용자는 이 화살표 표시를 이용하여 값을 변경한다. 

```
import wx
 
class Example(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
 
        self.initui()
 
    def initui(self):
        panel = wx.Panel(self)
 
        wx.StaticText(self, label='Convert Fahrenheit temperature to Celsius', pos=(20, 20))
        wx.StaticText(self, label='Fahrenheit: ', pos=(20, 80))
        wx.StaticText(self, label='Celsius: ', pos=(20, 150))
 
        self.celsius = wx.StaticText(self, label='', pos=(150, 150))
        self.spincontrol = wx.SpinCtrl(self, value='0', pos=(150, 75), size=(60, -1))
        self.spincontrol.SetRange(-459, 1000)
 
        button = wx.Button(self, label='Compute', pos=(70, 230))
        button.SetFocus()
        button_close = wx.Button(self, label='Close', pos=(185, 230))
 
        button.Bind(wx.EVT_BUTTON, self.oncompute)
        button_close.Bind(wx.EVT_BUTTON, self.onclose)
 
        self.SetSize((350, 310))
        self.SetTitle('wx.SpinCtrl')
        self.Center()
        self.Show(True)
 
    def onclose(self, e):
        self.Close(True)
 
    def oncompute(self, e):
        fahr = self.spincontrol.GetValue()
        cels = round((fahr - 32) * 5 / 9.0, 2)
 
        self.celsius.SetLabel(str(cels))
 
 
if __name__ == '__main__':
    app = wx.App()
    Example(None)
    app.MainLoop()
```

