# systemctl 사용법

이 문서는 CentOS-7 이후 부터 지원되는 systemctl 명령어 사용 방법을 기술 합니다.

CentOS-7 에서는 기존의 [service](https://zetawiki.com/wiki/리눅스_service), [chkconfig](https://zetawiki.com/wiki/Chkconfig) 명령어가 [systemctl](https://zetawiki.com/wiki/Systemctl) 명령어로 대체됨 (service 명령을 실행 하면 systemctl 로 redirection 됩니다.)

Debian 계열에도 동일 하게 사용되며, suse 계열 에도 사용할 수 있습니다.

아래 명령중 '**서비스이름.service**' 는 '**서비스이름**' 만 사용 하여도 됩니다.



### 서비스 등록, 삭제

```bash
systemctl mask 서비스이름.service
systemctl unmask 서비스이름.service
```

##### * 서비스이름.service 를 /etc/systemd/system/ 에 복사 후 systemctl daemon-reload 를 실행 해도 등록됨



### OS부팅 후 서비스 활성화 비활성화

```bash
systemctl enable 서비스이름.service
systemctl disable 서비스이름.service
```



### 서비스 시작,중지,재시작

```bash
systemctl start 서비스이름.service
systemctl stop 서비스이름.service 
systemctl restart 서비스이름.service
```



### 서비스 상태 확인

```bash
systemctl status 서비스이름.service
```



### 실행중인 서비스 확인

```bash
 systemctl list-units --type=service
 systemctl list-unit-files | grep enabled 
```



### 서비스 예제 (test_svc.service)

```bash
#!/bin/bash

[Unit]
Description=blah blah test_svc service

[Service]
Type=simple
RemainAfterExit=yes
WorkingDirectory=/home/username/service_script/
ExecStart=/bin/bash start_test_svc.sh
ExecStop=/bin/bash stop_test_svc.sh

[Install]
WantedBy=default.target

```

##### * .service 를 만드는 방법은 다양 하지만 가장 간단한 예제 입니다. 위의 start_test_svc.sh 에서 daemon 을 기동 하는 스크립트를 작성 하고 stop_test_svc.sh 에서 daemon 을 kill 하는  스크립트를 작성 하면 됩니다.

##### * 여기서 주의해서 보아야 할 부분은 "RemainAfterExit=yes" 부분으로 이 문장이 생략 되면 ExecStart 실행 후 바로 ExecStop 을 실행 하여 서비스 daemon 이 기동 후 즉시 kill 됩니다.



### - 끝 -





