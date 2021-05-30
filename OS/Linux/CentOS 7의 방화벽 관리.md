# CentOS 7의 방화벽 관리 

출처: https://www.manualfactory.net/10153

CentOS 7의 방화벽 관리 데몬은 firewalld입니다. 설정은 firewall-cmd 명령어로 합니다.



> ### 방화벽 실행 여부 확인

``` bash
firewall-cmd --state
```

실행 중이면 running, 실행 중이 아니면 not running을 출력합니다.



> ### 방화벽 다시 로드

``` bash
firewall-cmd --reload
```

방화벽 설정 후 다시 로드해야 적용됩니다.



> ### 존(Zone) 출력하기

``` bash
firewall-cmd --get-zones
```

존(zone) 목록을 출력합니다.



``` bash
firewall-cmd --get-default-zone
```

기본 존을 출력합니다.



``` bash
firewall-cmd --get-active-zones
```

활성화된 존을 출력합니다.



> ### 사용 가능한 서비스/포트 출력하기

``` bash
firewall-cmd --list-all
```

사용 가능한 모든 서비스/포트 목록을 출력합니다.



``` bash
firewall-cmd --zone=public --list-all
```

public 존에 속한 사용 가능한 모든 서비스/포트 목록을 출력합니다.



> ### 서비스/포트 추가/제거

``` bash
firewall-cmd --add-service=ftp
```

ftp 서비스 를 추가합니다.



``` bash
firewall-cmd --remove-service=ftp
```

ftp 서비스를 제거합니다.



``` bash
firewall-cmd --add-port=21/tcp
```

21번 tcp 포트를 추가합니다.



``` bash
firewall-cmd --remove-port=21/tcp
```

21번 tcp 포트를 제거합니다.



> ### --zone=<zone>

zone 옵션이 없으면 기본 존(default zone)에 추가 또는 삭제를 합니다. 다른 존에 작업하고 싶으면 zone 옵션을 추가합니다. 예를 들어

``` bash
firewall-cmd --zone=trusted --add-service=ftp
```

는 trusted 존에 ftp 서비스를 추가합니다.



> ### --permanent

시스템 재부팅 또는 방화벽 재시작 후에도 적용되도록 하려면 --permanent 옵션을 붙입니다.

``` bash
firewall-cmd --permanent --add-service=ftp
```

