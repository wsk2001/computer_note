# Python으로 만든 Windows 서비스 앱 샘플?



다음은 Python 및 pywin32 라이브러리로 만든 Windows 서비스 애플리케이션의 간단한 예입니다.

`sample_service.py`

``` py
import win32serviceutil
import win32service
import win32event
import time

class SampleService(win32serviceutil.ServiceFramework):
    _svc_name_ = "SampleService"
    _svc_display_name_ = "Sample Service"

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.hWaitStop = win32event.CreateEvent(None, 0, 0, None)

    def SvcStop(self):
        self.ReportServiceStatus(win32service.SERVICE_STOP_PENDING)
        win32event.SetEvent(self.hWaitStop)

    def SvcDoRun(self):
        self.ReportServiceStatus(win32service.SERVICE_RUNNING)
        while True:
            time.sleep(30)
            rc = win32event.WaitForSingleObject(self.hWaitStop, 0)
            if rc == win32event.WAIT_OBJECT_0:
                break

if __name__ == '__main__':
    win32serviceutil.HandleCommandLine(SampleService)

```

이 예제는 백그라운드에서 실행되고 일부 작업을 주기적으로 수행하는 기본 Windows 서비스를 구현합니다. 이 서비스는 다른 Windows 서비스와 마찬가지로 시작, 중지, 설치 및 제거할 수 있습니다. 서비스의 정확한 동작은 SvcDoRun 메서드의 논리를 수정하여 사용자 지정할 수 있습니다.



### Python으로 만든 Windows 서비스 앱 샘플을 작성하고 실행하는 방법은 무엇입니까?

Python으로 만든 Windows 서비스 애플리케이션을 작성하고 실행하려면 다음 단계를 따르십시오.

1. pywin32 라이브러리 설치: 터미널에서 다음 명령을 실행하여 pip를 사용하여 라이브러리를 설치할 수 있습니다.

   ``` cmd
   pip install pywin32
   ```

   

2. Python 스크립트 만들기: 텍스트 편집기를 열고 sample_service.py라는 새 파일을 만듭니다. 이전 답변에서 제공한 샘플 서비스에 대한 코드를 이 파일에 복사하여 붙여넣습니다.

   

3. 파일을 저장하고 닫습니다.

   

4. 서비스 설치: 터미널을 열고 sample_service.py 파일이 포함된 디렉터리로 이동한 후 다음 명령을 실행합니다.

   ``` cmd
   python sample_service.py install
   ```

   

5. 서비스 시작: 서비스를 시작하려면 다음 명령을 실행합니다.

   ``` cmd
   python sample_service.py start
   ```

   

6. 서비스가 실행 중인지 확인: Windows 검색 표시줄에 'Services'를 입력하고 'service' 결과를 클릭하여 액세스할 수 있는 Windows에서 서비스 관리자를 열어 서비스가 실행 중인지 확인할 수 있습니다. 서비스 관리자에서 'Sample Service'를 찾아 상태가 '실행 중'인지 확인합니다.

   

7. 서비스 중지: 서비스를 중지하려면 다음 명령을 실행합니다.

   ``` cmd
   python sample_service.py stop
   ```

   

8. 서비스 제거: 서비스를 제거하려면 다음 명령을 실행합니다.

   ``` cmd
   python sample_service.py remove
   ```



참고: 서비스를 설치하고 관리하려면 터미널을 관리자로 실행해야 할 수 있습니다.



---

# python 으로 windows service 프로그램 만들기



Windows 서비스를 만들기 위해서는 `win32serviceutil` 모듈과 `pywin32` 패키지가 필요합니다. `pywin32` 패키지는 `pip` 명령어로 설치할 수 있습니다.

``` cmd
pip install pywin32
```

아래 예제는 "HelloWorldService" 라는 이름의 간단한 Windows 서비스를 만드는 예제입니다. 이 서비스는 매 초마다 현재 시간을 로그로 남기는 간단한 기능을 수행합니다.

``` py
import logging
import os
import time

import win32service
import win32serviceutil
import win32event

class HelloWorldService(win32serviceutil.ServiceFramework):
    _svc_name_ = "HelloWorldService"
    _svc_display_name_ = "Hello World Service"
    _svc_description_ = "This service logs the current time every second."

    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self.hWaitStop = win32event.CreateEvent(None, 0, 0, None)

    def SvcStop(self):
        self.ReportServiceStatus(win32service.SERVICE_STOP_PENDING)
        win32event.SetEvent(self.hWaitStop)

    def SvcDoRun(self):
        logging.info("Hello World Service started.")
        while True:
            logging.info(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
            time.sleep(1)

if __name__ == '__main__':
    logging.basicConfig(
        filename=os.path.join(os.getcwd(), 'helloworld.log'),
        format='%(asctime)s %(message)s',
        level=logging.INFO
    )
    win32serviceutil.HandleCommandLine(HelloWorldService)

```

위 코드를 실행하면 `HelloWorldService` 라는 이름의 Windows 서비스가 등록됩니다. 이제 아래 명령어를 실행하여 서비스를 시작하거나 중지할 수 있습니다.

``` cmd
# 서비스 등록
python <파일이름>.py install
# 서비스 시작
python <파일이름>.py start
# 서비스 중지
python <파일이름>.py stop
# 서비스 삭제
python <파일이름>.py remove
```



서비스가 실행되면 `helloworld.log` 파일에 로그가 기록됩니다. `logging` 모듈을 사용하여 로그를 작성하고, `time` 모듈을 사용하여 현재 시간을 기록합니다. `win32serviceutil.ServiceFramework` 클래스를 상속받아서 `HelloWorldService` 클래스를 정의하였고, `SvcDoRun` 메서드에서 실제로 서비스가 실행되는 코드를 작성하였습니다. `SvcStop` 메서드에서는 서비스가 중지될 때 실행될 코드를 작성합니다.

