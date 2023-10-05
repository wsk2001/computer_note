# python으로 windows service 만들기

출처: https://bitcoder.tistory.com/116



## 1. 소스코드

 

다음은 소스코드입니다.

```python
import win32serviceutil
import servicemanager
import ctypes
import sys
import time

OutputDebugString = ctypes.windll.kernel32.OutputDebugStringW

class MyServiceFramework(win32serviceutil.ServiceFramework):
  _svc_name_ = 'MyPythonService'
  _svc_display_name_ = 'My Python Service'
  is_running = False

  def SvcStop(self):
    OutputDebugString("MyServiceFramework __SvcStop__")
    self.is_running = False

  def SvcDoRun(self):
    self.is_running = True
    while self.is_running:
      OutputDebugString("MyServiceFramework __loop__")
      time.sleep(1)

if '__main__' == __name__:
  win32serviceutil.HandleCommandLine(MyServiceFramework)
```

 

## 2. 윈도우 서비스 설치(등록)하기

작성한 코드를 윈도우 서비스로 설치(등록)하려면 다음의 명령을 실행합니다.

 

\* 명령실행은 관리자 권한의 명령 프롬프트에서 실행해야합니다. 관리자 권한의 명령 프롬프트를 실행하는 방법은 다음 글을 참고하셔도 되겠습니다.

https://bitcoder.tistory.com/48

[ 관리자 권한으로 cmd창 실행하기관리자 권한으로 cmd창을 실행하는 다음의 4가지 방법을 소개합니다. - 방법1. 실행창으로 실행하기 - 방법2. 작업표시줄아이콘을 클릭하여 관리자권한으로 실행하기 - 방법3. 아이콘에서 우클릭bitcoder.tistory.com](https://bitcoder.tistory.com/48)

 

> python main.py install

 

다음 그림은 윈도우 서비스 설치(등록)를 실행한 화면입니다.



![img](.\Images\urH70Kcyyg67R4cseUFQgK.png)



 

등록된 윈도우 서비스는 services.msc를 실행하여 관리콘솔에서 확인할 수 있습니다.



![img](.\Images\aKuJ2up9b5eFtMAAMondY0.png)



 

## 2. 실행하기

2.1. 명령을 이용하여 실행하기

윈도우 서비스 설치(등록) 후 서비스를 시작하려면 다음의 명령을 실행합니다.

> python main.py start

 

다음 그림은 윈도우 서비스 시작명령을 실행한 화면입니다.



![img](.\Images\WmrypcjJgyfx41gktcMOc1.png)



 

2.2. services.msc를 이용하여 실행하기

관리콘솔에서 "My Python Service"를 더블클릭하면 다음과 같은 윈도우가 나타납니다.



![img](.\Images\igHrgGm61RYQ025GAILTKk.png)



 

여기서 "시작" 버튼을 클릭하면 서비스가 실행됩니다.

 

## 3. 실행확인

윈도우 서비스는 다른 응용프로그램과 달리 출력콘솔이나 화면 인터페이스 윈도우를 가지고 있지 않기 때문에,

 

작성한 소스코드에서 OutputDebugString을 이용하여 디버그 메시지를 출력하도록 작성하였습니다.

 

실행에 따른 디버그 메시지를 확인하려면 DebugView를 관리자권한으로 실행시킨 후,

 

다음 그림과 같이 "Capture Global Win32" 옵션을 켜줍니다.

 



![img](.\Images\72Vs5M8kU4sC0RNLOXimC0.png)



 

다음 그림과 같이 디버그 메시지를 확인할 수 있습니다.



![img](.\Images\NZZNrJGQ64vSl1cz0GQIQK.png)



 

소개해드린 윈도우 서비스 소스코드는 서비스 등록과 실행을 확인하기 위해 최소한의 코드작성으로 구현하였습니다.

 

여기에 필요에 따라 여러가지 기능을 추가하여 윈도우 서비스를 만들어볼 수 있습니다.



---

## Bard 답변 (windows service 만들기)

파이썬으로 윈도우 서비스로 동작하는 App을 만드는 방법을 설명하겠습니다.

**1. 필요한 패키지 설치**

먼저, 파이썬 윈도우 서비스로 동작하기 위해서는 다음과 같은 패키지가 필요합니다.

- pywin32
- pywintypes

이 패키지를 설치하려면 다음과 같이 명령을 실행합니다.

```
pip install pywin32
pip install pywintypes
```

**2. 서비스 클래스 작성**

서비스 클래스는 다음과 같은 구조로 작성합니다.

``` py
import win32serviceutil
import win32service
import win32event

class MyService(win32serviceutil.ServiceFramework):

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.service_name = "MyService"
        self.service_description = "A simple Python service"

    def SvcDoRun(self):
        while True:
            # 서비스가 수행할 작업을 여기에 구현합니다.
            time.sleep(1)

    def SvcStop(self):
        # 서비스가 중지될 때 수행할 작업을 여기에 구현합니다.
        pass

if __name__ == "__main__":
    win32serviceutil.HandleCommandLine(MyService)

```




알겠습니다. 파이썬으로 윈도우 서비스로 동작하는 App을 만드는 방법을 설명하겠습니다.

**1. 필요한 패키지 설치**

먼저, 파이썬 윈도우 서비스로 동작하기 위해서는 다음과 같은 패키지가 필요합니다.

- pywin32
- pywintypes

이 패키지를 설치하려면 다음과 같이 명령을 실행합니다.

```
pip install pywin32
pip install pywintypes
```

**2. 서비스 클래스 작성**

서비스 클래스는 다음과 같은 구조로 작성합니다.

Python

```
import win32serviceutil
import win32service
import win32event

class MyService(win32serviceutil.ServiceFramework):

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.service_name = "MyService"
        self.service_description = "A simple Python service"

    def SvcDoRun(self):
        while True:
            # 서비스가 수행할 작업을 여기에 구현합니다.
            time.sleep(1)

    def SvcStop(self):
        # 서비스가 중지될 때 수행할 작업을 여기에 구현합니다.
        pass

if __name__ == "__main__":
    win32serviceutil.HandleCommandLine(MyService)
```

코드를 사용할 때는 주의하시기 바랍니다. [자세히 알아보기](https://bard.google.com/faq#coding)

content_copy

이 클래스는 `win32serviceutil.ServiceFramework` 클래스를 상속합니다. `__init__()` 메서드에서 서비스 이름과 설명을 지정합니다. `SvcDoRun()` 메서드에서 서비스가 수행할 작업을 구현합니다. `SvcStop()` 메서드에서 서비스가 중지될 때 수행할 작업을 구현합니다.

**3. 서비스 등록**

서비스 클래스를 작성했으면 다음과 같이 서비스 등록을 수행합니다.

```
python myservice.py install
```

이 명령을 실행하면 다음과 같은 서비스가 등록됩니다.

```
MyService
A simple Python service
```

**4. 서비스 시작**

서비스 등록을 완료했으면 다음과 같이 서비스를 시작합니다.

```
sc start MyService
```

이 명령을 실행하면 서비스가 시작됩니다.

**5. 서비스 중지**

서비스가 실행 중일 때 다음과 같이 서비스를 중지합니다.

```
sc stop MyService
```

이 명령을 실행하면 서비스가 중지됩니다.

**예제**

다음은 간단한 예제입니다.

``` py
import win32serviceutil
import win32service
import win32event
import time

class MyService(win32serviceutil.ServiceFramework):

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.service_name = "MyService"
        self.service_description = "A simple Python service"

    def SvcDoRun(self):
        while True:
            print("The time is now:", time.ctime())
            time.sleep(1)

    def SvcStop(self):
        print("The service is stopping...")

if __name__ == "__main__":
    win32serviceutil.HandleCommandLine(MyService)

```

이 예제에서는 서비스가 1초마다 현재 시간을 출력합니다.

**참고**

- 서비스 클래스에서 `SvcDoRun()` 메서드가 중단되면 서비스가 중지됩니다.
- 서비스 클래스에서 `SvcStop()` 메서드를 호출하면 서비스가 중지됩니다.
- 서비스 클래스에서 `win32event.SetEvent()` 메서드를 호출하여 서비스가 중지되도록 할 수 있습니다.

이외에도 Windows 서비스로 동작하는 Python App을 만들기 위한 다양한 방법이 있습니다. 예를 들어, `pywin32` 라이브러리 대신 `cx_Freeze` 또는 `pyinstaller`를 사용하여 Windows 바이너리를 생성할 수 있습니다.



---

## CharGPT 답변 (windows service 만들기)

Python을 사용하여 Windows 서비스로 동작하는 앱을 만들려면 다음 단계를 따를 수 있습니다:

1. 필요한 도구 설치:

   - NSSM(Non-Sucking Service Manager)을 다운로드 및 설치하십시오. 이 도구를 사용하여 Python 프로그램을 Windows 서비스로 등록하고 실행할 수 있습니다[[2](https://devse.tistory.com/91)].

2. Python 프로그램 작성:

   - Python으로 원하는 앱을 작성하십시오. 이 프로그램은 Windows 서비스로 실행될 것입니다.

3. NSSM을 사용하여 Windows 서비스 등록:

   - 관리자 권한의 명령 프롬프트에서 다음 명령을 실행하여 NSSM을 사용하여 Python 프로그램을 Windows 서비스로 등록하십시오

     ```
     phpCopy code
     nssm install <서비스 이름>
     ```

4. 서비스 구성:

   - NSSM 설정 대화 상자를 열고 다음을 구성하십시오
     - Application 탭: Python 스크립트 경로 설정
     - Details 탭: 서비스 이름, 설명 등 설정
     - Startup 탭: 서비스를 시작 유형으로 설정

5. 서비스 시작:

   - NSSM 설정을 저장하고 서비스를 시작하십시오.

이제 Python 프로그램이 Windows 서비스로 등록되어 백그라운드에서 실행됩니다. 필요한 경우 서비스 관리자를 사용하여 서비스를 시작, 중지 또는 다시 시작할 수 있습니다.

위 단계를 따르면 Python을 사용하여 Windows 서비스로 동작하는 앱을 만들 수 있습니다.



---

## Bing 답변 (windows service 만들기)

Python을 사용하여 Windows 서비스로 동작하는 애플리케이션을 만드는 것은 복잡한 과정일 수 있습니다. 아래에 간단한 예제를 제공하겠습니다. 이 코드는 Python의 `win32serviceutil` 모듈을 사용하여 작성되었습니다. 이 모듈은 pywin32 패키지의 일부로서, Windows 서비스를 생성하고 제어하는 데 필요한 기능을 제공합니다.

``` py
import win32serviceutil
import win32service
import win32event
import servicemanager
import socket

class MyService(win32serviceutil.ServiceFramework):
    _svc_name_ = 'MyService'
    _svc_display_name_ = 'My Service'

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.hWaitStop = win32event.CreateEvent(None, 0, 0, None)
        socket.setdefaulttimeout(60)

    def SvcStop(self):
        self.ReportServiceStatus(win32service.SERVICE_STOP_PENDING)
        win32event.SetEvent(self.hWaitStop)

    def SvcDoRun(self):
        servicemanager.LogMsg(servicemanager.EVENTLOG_INFORMATION_TYPE,
                              servicemanager.PYS_SERVICE_STARTED,
                              (self._svc_name_, ''))
        self.main()

    def main(self):
        # Your logic here
        pass

if __name__ == '__main__':
    if len(sys.argv) == 1:
        servicemanager.Initialize()
        servicemanager.PrepareToHostSingle(MyService)
        servicemanager.StartServiceCtrlDispatcher()
    else:
        win32serviceutil.HandleCommandLine(MyService)

```

이 코드는 기본적인 Windows 서비스를 설정합니다. `main` 함수에 원하는 로직을 추가하면 됩니다. 이 서비스를 설치하려면, 커맨드 라인에서 `python myservice.py install`을 실행하고, 서비스를 시작하려면 `python myservice.py start`를 실행하면 됩니다.

이 코드는 단순한 예제이며, 실제 사용을 위해서는 추가적인 에러 처리, 로깅, 그리고 서비스가 중단되었을 때의 처리 등이 필요할 수 있습니다. 또한, 이 코드를 실행하려면 관리자 권한이 필요합니다. 이 코드를 사용하기 전에, Python과 관련 패키지가 시스템에 설치되어 있는지 확인하십시오.

참고로, 이 코드는 Python 2와 Python 3 모두에서 동작합니다. 하지만 Python 2는 2020년부터 공식적으로 지원이 종료되었으므로, 가능하다면 Python 3를 사용하는 것이 좋습니다.

