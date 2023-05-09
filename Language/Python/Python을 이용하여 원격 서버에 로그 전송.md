# Python을 이용하여 원격 서버에 로그 전송

# 원격지 로그 전송

Python으로 특정 원격지 서버에 로그 전송이 가능하다.

먼저 rsyslog에서 해당 통신이 가능하도록 설정을 변경 해야 한다.

```bash
vim /etc/rsyslog.conf

# UDP 514 포트로 통신 가능하도록 다음 2 LINE 주석 제거
$ModLoad imudp
$UDPServerRun 514

# 로그타입과 저장 위치 설정
local3.*        /var/log/my-logfile
```



이후 rsyslog에 대한 서비스를 재기동 한다.

``` bash
sudo service rsyslog restart
```



마지막으로 Python 소스는 다음과 같다.

``` py
# -*- coding: utf-8 -*-
import logging
import logging.handlers

logger = logging.getLogger('myLogger')
logger.setLevel(logging.INFO)

#add handler to the logger
handler = logging.handlers.SysLogHandler(address = ('SERVER_IP',514), facility=logging.handlers.SysLogHandler.LOG_LOCAL3)

#add formatter to the handler
formatter = logging.Formatter('Python: { "loggerName":"%(name)s", "asciTime":"%(asctime)s", "pathName":"%(pathname)s", "logRecordCreationTime":"%(created)f", "functionName":"%(funcName)s", "levelNo":"%(levelno)s", "lineNo":"%(lineno)d", "time":"%(msecs)d", "levelName":"%(levelname)s", "message":"%(message)s"}')

handler.formatter = formatter
logger.addHandler(handler)

logger.info("Test Message")
```



해당 소스를 실행하면 /var/log/my-logfile 파일에 로그가 쌓이는 것을 확인 할 수 있다.

## 참고 자료

[How to configure logging to syslog in Python?](https://stackoverflow.com/questions/3968669/how-to-configure-logging-to-syslog-in-python)

