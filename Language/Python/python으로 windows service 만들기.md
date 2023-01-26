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