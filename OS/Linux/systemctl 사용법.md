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



### OS부팅 후 서비스 활성화/비활성화/확인

```bash
 활성화) systemctl enable 서비스이름.service
비확성화) systemctl disable 서비스이름.service
   확인) systemctl is-enabled 서비스이름.service
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
Wants=network-online.target
After=network-online.target


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

##### * After=network-online.target, Wants=network-online.target 를 추가 하지 않으면 등록된 서비스 자동 시작시(OS 재 부팅) socket 을 이용하여 타 서버에 접속 하는경우 오류가 발생 합니다. 



간단한 설명 끝.



- 참조1 : https://chanchan-father.tistory.com/196
- 







## Linux Systemctl(systemd) 상세 옵션 및 설정 방법

출처 : https://sysops.tistory.com/67

systemctl은 systemd의 LGPL 라이센스를 따르는 시스템 자원 통합 관리 도구이다

설정 파일은 **<span style="color:blue">/etc/systemd</span>** 아래에 위치하며, 각 파일 별로 **[Unit], [Service], [Install]**로 구성됨



\- systemd 는 리눅스 커널 API 로 설계된 시스템 관리 데몬이다.

\- Lennart Poettering 와 Kay Sievers 가 처음 개발 하였다. (GNU 약소 GPL 라이선스)

\- 시스템이 부팅하는 동안 데몬 스크립트를 병렬로 수행 할수 있도록 설계하였다. **<span style="color:blue">병렬로 서비스를 수행하기 때문에 서비스간의 종속성 및 실행 순서 관리가 매우 중요</span>**하다. 

\- 프로세스간의 통신은 D-bus 에서 담당한다. (소켓, D-bus 지원)

\- 전통적인 Sysvinit 의 경우 서비스 감시 기능이 부족하다는 단점이 있다.

\- 전통적인 Sysvinit 의 경우 서비스간 종속성을 관리하지 못한다.

\- 전통적인 Sysvinit 의 경우 볶잡한 스크립트를 필요로 한다. (start, status, stop 등 각각에 대한 스크립팅이 필요하다.) -> 이건 확실히 그렇습니다. 데몬 관리가 어려움...

\- 전통적인 Sysvinit 의 경우 udev 관리 면에서 부적당하다.



###  systemd 서비스 등록 샘플

``` ini
[Unit]
Description=Sample Service
Requires=local-fs.target
After=local-fs.target

[Service]
Type=simple
PIDFile=/var/run/sample.pid
ExecStart=/usr/sbin/sampled -d
ExecStop=/usr/sbin/sampled -k

[Install]
WantedBy=multi-user.target
```



####  **[Unit] 섹션** 

**<span style="color:blue">Description=</span>**

해당 유닛에 대한 상세한 설명을 포함한다.



**<span style="color:blue">Requires=</span>**

상위 의존성을 구성한다. 목록의 유닛이 정상적일 경우 유닛이 시작된다. (필요 조건)



**<span style="color:blue">RequiresOverridable=</span>**

"Requires=" 옵션과 유사하다. 하지만 이 경우 "사용자에 의해서" 서비스가 시작하는데 상위 의존성이 있는 유닛 구동에 실패 하더라도 이를 무시하고 유닛을 시작한다. (즉 상위 의존성을 무시한다.) 자동 시작의 경우 적용 되지 않는다.



**<span style="color:blue">Requisite=, RequisiteOverridable=</span>**

"Requires=" 와 "RequiresOverridable=" 와 유사하다. 상위 의존성 유닛이 시작되지 않은 경우 즉시 실패를 반환한다.



**<span style="color:blue">Wants=</span>**

"Requires=" 보다 다소 완화된 옵션이다. 상위 의존성의 유닛이 시작되지 않더라도 전체 수행과정에 영향을 끼치지 않는다. 이 옵션은 하나의 유닛을 다른 유닛과 연계할 경우 사용하게 된다. (충분 조건)



**<span style="color:blue">BindsTo=</span>**

"Requires=" 와 매우 유사하다. systemd 개입 없이 갑작기 서비스가 사라진 경우 (가령 NIC 가 물리적 장애가 난 경우) 해당 유닛도 같이 중지 하도록 설정하도록 한다.



**<span style="color:blue">PartOf=</span>**

"Requires=" 와 매우 유사하다. 상위 의존성의 유닛을 중지하거나 재시작하는 경우 해당 유닛 또한 중지나 재시작을 수행한다. 만일 오라클과 오라클 리스너의 경우에서 처럼 하나의 서비스 다른 하나의 종속성을 가지게 되는 경우 필요한 설정이다.



**<span style="color:blue">Conflicts=</span>**

역의 관계를 설정한다. 만일 유닛1' 의 "Conflicts=" 설정이 '유닛2' 로 되어 있다면 '유닛1'이 시작된 경우 '유닛2'가 중지되고, '유닛1'이 중지된 경우 '유닛2'가 시작한다. 이 옵션은 "After=" 와 "Before=" 옵션과는 독립적으로 작동한다. 각 서비스가 반대의 역활을 하거나 혹은 보조적인 서비스 역활을 수행한다면 서비스 실행 관리에 편리할 것 같다. 



**<span style="color:blue">Before=, After=</span>**

유닛 시작의 전후 관계를 설정한다. 해당 설정은 "Requires=" 설정과는 독립적이다. "Before=" 에 나열된 유닛이 시작되기 전에 실행하고 "After=" 은 해당 유닛이 시작된 이후 나열된 유닛이 실행한다.

이 설정은 시스템이 종료(shutdown) 될때는 역으로 작동하게 된다.



**<span style="color:blue">OnFailure=</span>**

해당 유닛이 "실패" 상태가 되면 수행할 유닛 목록 (예 "/" 파일 시스템 마운트 실패시 복구 모드 수행)



**<span style="color:blue">PropagatesReloadTo=, ReloadPropagatedFrom=</span>**

리로드(reload) 명령을 다른 유닛에게 전달하거나 혹은 전달받아 해당 유닛도 리로드(reload)하게 된다.



**<span style="color:blue">RequiresMountsFor=</span>**

절대 경로로 지정하여 유닛을 구동하는데 필요한 마운트 목록을 자동으로 구성하여 "Requires=", "After=" 을 수행한다. 즉 필요한 마운트 경로가 준비되어 있는지 점검하고 마운트를 미리 진행한다.



**<span style="color:blue">OnFailureIsolate=[yes|no]</span>**

"yes” 인 경우 "OnFailure=" 에 선언된 리스트와는 격리모드(isolation mode)로 작동한다. 즉 해당 유닛에 종속성이 없는 모든 유닛은 중지 되게 된다. "OnFailureIsolate=yes" 인 경우 "OnFailure=" 옵션에 오직 하나의 유닛만 설정 할 수 있다.  





#### **[Service] 섹션**

**<span style="color:blue">Type</span>**=[**simple**|forking|oneshot|notify|dbus]

유닛 타입을 선언한다.

"simple" (기본값) 유닛이 시작된 경우 즉시 systemd 는 유닛의 시작이 완료되었다고 판단한다. 다른 유닛과 통신하기 위해 소켓을 사용하는 경우 이러한 설정을 사용하면 안된다.

"forking" 자식 프로세스를 생성이 완료되는 단계까지를 systemd 가 시작이 완료되었다고 판단하게 된다. 부모 프로세스를 추적할 수 있도록 PIDFile= 필드에 PID 파일을 선언해 주어야 한다.

"oneshot" 은 "simple" 과 다소 유사하지만 단일 작업을 수행하는데 적합한 타입니다. 또한 실행 이후 해당 실행이 종료되더라도 RemainAfterExit=yes 옵션을 통해 유닛이 활성화 상태로 간주할 수 있다.

"notify" 은 "simple" 과 동일하다. 다만 유닛이 구동되면 systemd 에 시그널을 보낸다. 이때 시그널에 대한 내용은  libsystemd-daemon.so 에 선언 되어 있다.

"dbus" DBUS 에 지정된 BusName 이 준비될때까지 대기한다. 즉 DBUS 준비가 완료된 이후 유닛이 시작되었다고 간주한다. 



**<span style="color:blue">RemainAfterExit</span>**=[yes|**no**]

유닛이 종료 이후에도 유닛이 활성화 상태로 판단한다.



**<span style="color:blue">GuessMainPID</span>**=[**yes**|no]

이 옵션는 Type=forking 가 설정되어 있고 PIDFile= 설정이 되어 있지 않은 경우에 작동한다. 

systemd 가 유닛이 정상적으로 시작되었는지 판단이 명확하지 않는 경우 사용된다. 만일 여러개의 데몬으로 구성된 유닛의 경우 잘못된 PID 추측이 발생할 수 있다. 그로인해 오류 검출이나 자동 재시작 등의 작업이 불가능 할 수 있다. 이 옵션은 이러한 문제를 방지하는 기능을 한다. 



**<span style="color:blue">PIDFile</span>**=

PID 파일을 지정한다. 만일 유닛 타입이 forking 이라면 해당 설정을 추가해 주어야 한다. (절대 경로 사용)



**<span style="color:blue">BusName</span>**=

D-Bus 의 버스 이름을 지정한다. Type=dbus 인 경우 필수 사항이다. 다른 Type 의 경우라도 D-Bus 버스 이름을 다르게 사용하는 경우 별도로 설정을 해주는 것이 좋다.



**<span style="color:blue">Environment</span>**=

해당 유닛 에서 사용할 환경 변수를 선언한다. 또한 반드시 “Exec*=” 옵션보다 상단에 위치해야 한다. 예제는 아래와 같다.

Environment="ONE=one" 'TWO=two two'



**<span style="color:blue">EnvironmentFile</span>**=

해당 유닛에서 사용할 환경 변수 파일을 선언한다. 환경 변수 파일에서 "#' 와 ";" 로 시작되는 라인은 주석으로 처리된다. "Environment=" 와 같이 사용하는 경우 "Environment=" 옵션값이 먹게 된다. 또한 반드시 “Exec*=” 옵션보다 상단에 위치해야 한다.



**<span style="color:blue">ExecStart</span>**=

구동 명령어(스크립트)을 선언한다. 실행 명령어는 반드시 절대 경로 또는 변수(${STRINGS} 따위) 로 시작해야 한다. 다중 명령어를 지원한다. 예제는 아래와 같다.

ExecStart="commnad 1; command 2; command 3” 또는

ExecStart="commnad 1”

ExecStart="command 2”  



**<span style="color:blue">ExecStop</span>**=

중지 명령어(스크립트)를 선언한다. "ExecStart=" 동일하게 사용하면 된다. 중지 방식은 "KillMode=" 로 지정된다.



**<span style="color:blue">KillMode</span>**=[**control-group**|process|none]

중지 방법에 대해서 선언한다.

"control-group" 은 해당 유닛의 그룹까지 모두 중지 시킨다. 기본값이다.

"process" 은 해당 유닛 즉 메인 프로세스만 중지 시킨다.

"none" 은 아무런 액션을 하지 않는다.

\* 그룹이란 유닛과 그 유닛에 종속성을 가지는 유닛의 묶음을 뜻한다.



**<span style="color:blue">ExecReload</span>**=

리로그(reload) 를 수행할 명령어를 선언한다.



**<span style="color:blue">ExecStartPre</span>**=, ExecStartPost=, ExecStopPre=, ExecStopPost=

유닛 시작, 중지 등의 엑션과 관련하여 수행할 추가 명령어를 선언한다. 사용법은 "ExecStart=" 동일하게 사용하면 된다.



**<span style="color:blue">RestartSec</span>**=

재시작 명령을 수행할때 중지 이후 다시 시작하는데 대기(sleep)하는 시간을 설정한다. 기본값은 "100ms" 이다. 각각 “min”, “s”, “ms” 단위로 설정한다. 해당 설정은 Restart= 옵션이 있는 경우에만 적용된다.



**<span style="color:blue">TimeoutStartSec</span>**=

유닛이 시작하는데 대기하는 시간을 설정한다. 기본값은 90초(90s)이다. 만일 Type=oneshot 인 경우 해당 설정이 해당 설정이 적용되지 않는다. 만일 시작 시간을 대기하지 않고 무한정 리턴값을 기다리게 설정할려면 "TimeoutStartSec=0" 으로 설정해 주면 된다.



**<span style="color:blue">TimeoutStopSec</span>**=

옵션을 중지하는데 대기하는 시간을 설정한다. 기본값은 90초(90s)로 위의 "TimeoutStartSec=" 옵션과 동일하게 "TimeoutStopSec=0" 으로 설정하면 무한정 리턴값을 기다리게 된다. "TimeoutStopSec" 옵션에 설정된 값 안에 종료되지 않으면 SIGKILL 시그널을 보내서 강제로 종료하게 된다.



**<span style="color:blue">TimeoutSec</span>**=

"TimeoutStartSec=" 와 "TimeoutStopSec=" 을 동시에 설정한다.



**<span style="color:blue">WatchdogSec</span>**=

유닛이 시작된 이후 유닛 상태 감시(keep-alive ping)할때의 상태 값을 리턴하는데 대기하는 시간을 설정한다.  "Restart=" 옵션이 "on-failure", "always" 인 경우 유닛을 자동으로 재시작하게되고 이때 "WatchdogSec=" 설정을 해주어야 한다. 기본값은 "0" 으로 유닛 상태 감시를 사용하지 않는다.



**<span style="color:blue">Restart</span>**=[**no**|on-success|on-failure|on-watchdog|on-abort|always]

유닛이 죽었을때나 혹은 "WatchdogSec=" 만큼의 시간 동안 응답이 없는 경우 재시작한다. "ExecStartPre=", "ExecStartPost=", "ExecStopPre=", "ExecStopPost=", "ExecReload=" 에 설정된 유닛의 경우에는 포함되지 않는다. 즉 해당 유닛에만 해당된다.

"no" (기본값), 유닛을 다시 시작하지 않는다.

"on-success" 는 유닛이 정상적으로 종료되었을 때만 재시작한다. 종료시에 "0" 값을 리턴하여 종료되었거나 SIGHUP, SIGINT, SIGTERM, SIGPIPE 등과 같은 시그널 또는 "SuccessExitStatus=" 설정에서 지정된 리턴 코드 목록에 따른 시그널에 대해서 모두 성공으로 인식해 재시작을 하게 된다.

"on-failure" 유닛이 비정상적으로 종료되었을때 재시작한다. 리턴값이 "0" 이 아닌 경우, core dump 와 같이 비정상적인 시그널을 받고 종료된 경우, 타임 아웃값내 응답이 없는 경우 등일때 재시작 하게 된다.

"on-watchdog" "WatchdogSec=" 에 설정된 시간내 응답이 없는 경우에만 재시작 한다.

"on-abort" 지정되지 않은 리턴값을 받은 경우 재시작을 한다.

"always" 종료 상태 등과 무관하게 무조건 재시작한다. (사용자가 중지해도 시스템이 다시 띄우게 된다. 설정된 유닛 중지 시 주의가 필요하다.)



**<span style="color:blue">SuccessExitStatus</span>**=

성공으로 판단할 시그널을 설정해 준다. 문법은 아래와 같다.

"SuccessExitStatus=1 2 8 SIGKILL"



**<span style="color:blue">RestartPreventExitStatus</span>**=

재시작을 방지할 리턴 코드를 설정한다. 재시작을 하지 않을 리턴 코드를 설정하는데 유용하다. 문법은 아래와 같다.

"RestartPreventExitStatus=1 6 SIGABRT"



**<span style="color:blue">PermissionsStartOnly</span>**=[yes|**no**]

"User=", "Group=" 옵션 등과 같이 권한 설정 옵션을 적용 하여 시작한다. 해당 설정은 "ExecStart=" 옵션에서만 적용 되며 "ExecStartPre=", "ExecStartPost=", "ExecReload=", "ExecStop=", "ExecStopPost=" 옵션에서는 적용되지 않는다.



**<span style="color:blue">User=, Group=</span>**

유닛의 프로세스를 수행할 사용자명, 그룹명 등을 지정한다.



**<span style="color:blue">RootDirectoryStartOnly</span>**=[yes|**no**]

"/" 디렉토리를 지정한다. chroot() 함수를 사용하여 구동한다. jail 구성의 일반적인 형태이다. 해당 설정은 "ExecStart=" 옵션에서만 적용 되며 "ExecStartPre=", "ExecStartPost=", "ExecReload=", "ExecStop=", "ExecStopPost=" 옵션에서는 적용되지 않는다.



**<span style="color:blue">RootDirectory</span>**=

chroot() 함수로 변경할 "/" 디렉토리를 지정한다.



**<span style="color:blue">WorkingDirectory</span>**=

프로세스의 작업 디렉토리를 지정한다. 별도의 지정이 없으면 유닛은 "/" 디렉토리를 작업 디렉토리로 사용한다. 특정 디렉토리에서 실행해야하는 프로세스에서 필요하다. 



**<span style="color:blue">NonBlocking</span>**=[yes|**no**]

소켓 파일 디스크립션 (FD) 에 O_NONBLOCK 플래그를 선언한다. yes 일 경우 STDIN/STDOUT/STDERR 을 제외하고 모든 소켓에 O_NONBLOCK 플래그가 지정된다. 즉 non-blocking mode 로 작동하게 된다.



**<span style="color:blue">NotifyAccess</span>**=[none|main|all]

유닛 상태에 대해서 sd_notify() 함수를 사용하여 알림(notification) 소켓에 접근할 수 있도록 한다.

“none” 은 유닛 상태에 대한 모든 정보를 무시한다.

"main" 은 메인 프로세스에 대해서만 상태 정보 알림을 허용한다.

"all” 은 모든 유닛 즉 컨트롤 그룹의 유닛 상태 정보 알림을 허용한다.

"Type=notify" 또는 "WatchdogSec=" 가 설정된 경우 "NotifyAccess=" 을 접근 가능하게 (즉 main 이상) 설정해야 한다. "NotifyAccess=" 만 설정하고 값이 없는 경우 기본값은 "main” 이다.



**<span style="color:blue">Sockets</span>**=

유닛에서 사용하는 소켓의 이름을 지정한다. 기본으로 <유닛명>.socket 으로 생성되지만 지정된 이름으로 소켓을 사용하는 경우 별도의 설정이 가능하다. 또한 하나의 유닛에서 여러 소켓 목록을 일괄적으로 관리하는 경우 "Sockets=" 옵션이 여러번 사용 될 수도 있다. 만일 "Sockets=" 옵션이 아무런 설정값 없이 단독으로 사용되는 경우 소켓 목록이 리셋되게 된다.



**<span style="color:blue">StartLimitInterval=, StartLimitBurst=</span>**

위의 두 설정값을 이용하여 제한된 시간에 너무 많은 재시작 ("Restart=") 이 발생되는 것을 방지해 준다. 기본값에 따르면 10초 간격으로 5번까지 서비스 시작을 허용하고 그 이상 더 재시작 이벤트가 발생하면 자동으로 재시작 하지 않도록 설정해 준다. (즉 1분내 5번 재시작 시도이후 복구 불가시 Fail 발생) 나중에 관리자가 수동으로 구동하여 복구 할 수 있도록 하여 무한대의 유닛 재시작 이벤트 발생을 방지한다.
"StartLimitInterval=" 옵션의 경우 기본값 10초(10s), "0" 으로 설정할 경우 비활성화한다.

"StartLimitBurst=" 옵션의 경우 기본값 5회



**<span style="color:blue">StartLimitAction</span>**=[**none**|reboot|reboot-force|reboot-immediate]

만일 복구 재시도가 제한된 설정 ( Service Recovery Limit > StartLimitInterval * StartLimitBurst ) 내에 마치지 못하면 다음 조치로 어떠한 방식의 작동을 할지 선언한다.

"none" (기본값), 아무런 액션도 하지 않습니다.

"reboot" 시스템을 재부팅 한다. (systemctl reboot 와 동일)

"reboot-force" 시스템을 강제 제부팅 한다. 단 데이타 유실을 없다. (systemctl reboot -f 와 동일)

"reboot-immediate" 시스템을 강제 재부팅 한다. 데이타의 유실이 있다. reboot() 함수를 사용하여 즉각적인 재부팅을 수행한다. sync 과정없이 진행



**<span style="color:blue">Nice</span>**=

해당 유닛의 프로세스의 nice 값을 지정한다. "-20" 부터 "19" 까지 정수형으로 등록한다.



**<span style="color:blue">OOMScoreAdjust</span>**=

OOM(Out Of Memory) killer 작동시 프로세스 조정값를 미리 지정할 수 있다. "-1000" 에서 "1000" 까지 정수형으로 등록한다.



**<span style="color:blue">UMask</span>**=

umask 값을 선언한다. 별도의 설정이 없으면 기본값은 "0022" 이다.



**<span style="color:blue">SyslogFacility</span>**=

로그 사설을 설정할 수 있다. "kern, user, mail, daemon, auth, syslog, lpr, news, uucp, cron, authpriv, ftp, local0, local1, local2, local3, local4, local5, local6, local7" 등의 값으로 설정 가능하다.



**<span style="color:blue">SyslogLevel</span>**=

로그 레벨을 설정할 수 있다. "emerg, alert, crit, err, warning, notice, info, debug" 등 설정이 가능하다.



**<span style="color:blue">TCPWrapName</span>**=

TCP 래퍼를 사용하기위한 설정이다.



**<span style="color:blue">PAMName</span>**=

PAM 보안 사설을 사용하기 위한 설정이다.



####  **[Install] 섹션**

**<span style="color:blue">Alias</span>**=

유닛의 알리아스 이름을 지정한다. "systemctl enable" 명령어를 통해서 알리아스 이름으로 생성할 수 있다. 알리아스 이름은 유닛 파일 확장자(유닛 타입)를 가지고 있어야 한다.

(service, socket, mount, swap 등이 있다.  예: httpd.service 의 Alias=apache.service)



**<span style="color:blue">WantedBy=, RequiredBy=</span>**

"systemctl enable" 명령어로 유닛을 등록할때 등록에 필요한 유닛을 지정한다. 해당 유닛을 등록하기위한 종속성 검사 단계로 보면 된다. 따라서 해당 설정은 [Unit] 섹션의 "Wants=" 와 :Requires=" 옵션과 관계 있다.



**<span style="color:blue">Also</span>**=

"systemctl enable" 와 "systemctl disable" 로 유닛을 등록하거나 해제할때 다른 유닛 또한 같이 등록, 해제를 하도록 구성할 수 있다.



> Targets table

| SysV Runlevel | systemd Target                                        | Notes                                                        |
| ------------- | ----------------------------------------------------- | ------------------------------------------------------------ |
| 0             | runlevel0.target, poweroff.target                     | Halt the system.                                             |
| 1, s, single  | runlevel1.target, rescue.target                       | Single user mode.                                            |
| 2, 4          | runlevel2.target, runlevel4.target, multi-user.target | User-defined/Site-specific runlevels. By default, identical to 3. |
| 3             | runlevel3.target, multi-user.target                   | Multi-user, non-graphical. Users can usually login via multiple consoles or via the network. |
| 5             | runlevel5.target, graphical.target                    | Multi-user, graphical. Usually has all the services of runlevel 3 plus a graphical login. |
| 6             | runlevel6.target, reboot.target                       | Reboot                                                       |
|    emergency           | emergency.target                             | Emergency shell                              |





## systemd.unit — Unit configuration

출처: https://www.freedesktop.org/software/systemd/man/systemd.unit.html



### Name

systemd.unit — Unit configuration



### Synopsis

```
service.service, socket.socket, device.device, mount.mount, automount.automount, swap.swap, target.target, path.path, timer.timer, slice.slice, scope.scope
```



### system unit 검색 경로

```
/etc/systemd/system.control/*
/run/systemd/system.control/*
/run/systemd/transient/*
/run/systemd/generator.early/*
/etc/systemd/system/*
/etc/systemd/systemd.attached/*
/run/systemd/system/*
/run/systemd/systemd.attached/*
/run/systemd/generator/*
…
/usr/lib/systemd/system/*
/run/systemd/generator.late/*
```



### 사용자 unit 검색 경로

```
~/.config/systemd/user.control/*
$XDG_RUNTIME_DIR/systemd/user.control/*
$XDG_RUNTIME_DIR/systemd/transient/*
$XDG_RUNTIME_DIR/systemd/generator.early/*
~/.config/systemd/user/*
/etc/systemd/user/*
$XDG_RUNTIME_DIR/systemd/user/*
/run/systemd/user/*
$XDG_RUNTIME_DIR/systemd/generator/*
~/.local/share/systemd/user/*
…
/usr/lib/systemd/user/*
$XDG_RUNTIME_DIR/systemd/generator.late/*
```



### Description

unit 파일은 서비스, 소켓, 장치, 마운트 지점, 자동 마운트 지점, 스왑 파일 또는 파티션, 시작 대상, 감시 된 파일 시스템 경로에 대한 정보를 인코딩하는 일반 텍스트 ini 스타일 파일입니다. [systemd (1)](https://www.freedesktop.org/software/systemd/man/systemd.html#), 자원 관리 슬라이스 또는 외부에서 생성 된 프로세스 그룹에 의해 제어되고 감독되는 타이머. 구문에 대한 일반적인 설명은 [systemd.syntax (5)](https://www.freedesktop.org/software/systemd/man/systemd.syntax.html#)를 참조하십시오.



이 매뉴얼 페이지에는 모든 장치 유형의 공통 구성 옵션이 나와 있습니다. 이러한 옵션은 장치 파일의 [Unit] 또는 [Install] 섹션에서 구성해야합니다.

여기에 설명 된 일반 [Unit] 및 [Install] 섹션 외에도 각 유닛에는 유형별 섹션이있을 수 있습니다. 서비스 단위의 [service]. 자세한 내용은 해당 매뉴얼 페이지를 참조하십시오 : [systemd.service (5)](https://www.freedesktop.org/software/systemd/man/systemd.service.html#), [systemd.socket (5)](https://www.freedesktop.org/software/systemd/man/systemd.socket.html#), [systemd.device (5)](https://www.freedesktop.org/software/systemd/man/systemd.device.html#), [systemd.mount (5)](https://www.freedesktop.org/software/systemd/man/systemd.mount.html#), [systemd.automount (5)](https://www.freedesktop.org/software/systemd/man/systemd.automount.html#), [systemd.swap (5)](https://www.freedesktop.org/software/systemd/man/systemd.swap.html#) , [systemd.target (5)](https://www.freedesktop.org/software/systemd/man/systemd.target.html#), [systemd.path (5)](https://www.freedesktop.org/software/systemd/man/systemd.path.html#), [systemd.timer (5)](https://www.freedesktop.org/software/systemd/man/systemd.timer.html#), [systemd.slice (5)](https://www.freedesktop.org/software/systemd/man/systemd.slice.html#), [systemd.scope (5)](https://www.freedesktop.org/software/systemd/man/systemd.scope.html#)입니다.

다음 섹션에서 설명하는 것처럼 컴파일 중에 결정된 일련의 경로에서 단위 파일이로드됩니다.

유효한 단위 이름은 '이름 접두사'와 단위 유형을 지정하는 점(.) 및 접미사로 구성됩니다. '단위 접두사'는 하나 이상의 유효한 문자 (ASCII 문자, 숫자, ':', '-', '_', '.'및 '\\')로 구성되어야합니다. 접미사를 포함한 장치 이름의 총 길이는 256자를 초과하지 않아야합니다. 형식 접미사는 '.service', '.socket', '.device', '.mount', '.automount', '.swap', '.target', '.path', '.timer','.slice '또는'.scope ' 중 하나 여야합니다.

'인스턴스 이름'이라는 단일 인수로 단위 이름을 매개 변수화 할 수 있습니다. 그런 다음 장치는 여러 서비스 또는 다른 장치의 정의 역할을하는 '템플릿 파일'을 기반으로 구성됩니다. 템플릿 단위는 이름 끝에 하나의 '@'가 있어야합니다 (형식 접미사 바로 앞). 전체 장치의 이름은 '@'와 장치 유형 접미사 사이에 인스턴스 이름을 삽입하여 구성됩니다. 단위 파일 자체에서 인스턴스 매개 변수는 '% i'및 기타 지정자를 사용하여 참조 할 수 있습니다 (아래 참조).

단위 파일에는 여기에 나열된 것 외에 추가 옵션이 포함될 수 있습니다. systemd가 알 수없는 옵션을 발견하면 경고 로그 메시지를 작성하지만 장치를 계속로드합니다. 옵션 또는 섹션 이름 앞에 X-가 붙으면 systemd에서 완전히 무시합니다. 무시 된 섹션 내의 옵션에는 접두사가 필요하지 않습니다. 응용 프로그램은이를 사용하여 단위 파일에 추가 정보를 포함 할 수 있습니다.

장치 검색 경로 중 하나에서  심볼릭 링크를 만들어 장치의 별칭을 지정할 수 있습니다 (대체 이름이 있음). 예를 들어 systemd-networkd.service에는 설치 중에 심볼릭 링크로 생성 된 dbus-org.freedesktop.network1.service라는 별칭이 있으므로 D-Bus를 통해 dbus-org.freedesktop.network1.service를로드하도록 systemd에 요청하면 Systemd-networkd.service를로드합니다. 다른 예로, 부팅시 시작된 기본 시스템 대상인 default.target은 일반적으로 multi-user.target 또는 graphic.target으로 심볼릭 연결되어 기본적으로 시작되는 항목을 선택합니다. 별칭 이름은 disable, start, stop, status 등과 같은 명령과 Wants=, Requires=, Before=, After= 를 포함한 모든 장치 종속성 지시문에서 사용될 수 있습니다. **preset**  설정 명령과 함께 Aliases 를 사용할 수 없습니다.



alias 는 다음 제한 사항을 준수합니다.

- 특정 유형의 unit ( '.service', '.socket',…)은 동일한 유형 접미사가있는 이름으로 만 별명을 지정할 수 있습니다.
- 일반 unit (템플릿 또는 인스턴스가 아님)은 일반 이름으로 만 별칭을 지정할 수 있습니다. 
- 템플릿 인스턴스는 다른 템플릿 인스턴스에서만 별칭을 지정할 수 있으며 인스턴스 부분은 동일해야합니다.
- 템플릿은 다른 템플릿으로 별칭을 지정할 수 있습니다 (이 경우 별칭은 템플릿의 모든 인스턴스에 적용됨).
- 특별한 경우, 템플릿 인스턴스 (예 : 'alias@inst.service')는 다른 템플릿 (예 : 'template@inst.service')에 대한 심볼릭 링크 일 수 있습니다.
- 이 경우이 특정 인스턴스 만 별칭이 지정되고 템플릿의 다른 인스턴스 (예 : 'alias@foo.service', 'alias@bar.service')는 별칭이 지정되지 않습니다.
- 이러한 규칙은 인스턴스 (있는 경우)가 항상 주어진 단위 및 모든 해당 별칭에 대해 고유하게 정의되어야한다는 요구 사항을 유지합니다.

유닛 파일은 [Install] 섹션의 Alias= 지시문을 통해 별칭을 지정할 수 있습니다. 장치가 활성화되면 해당 이름에 대한 심볼릭 링크가 생성되고 장치가 비활성화되면 제거됩니다. 예를 들어, reboot.target은 Alias=ctrl-alt-del.target을 지정하므로 사용 가능한 경우 reboot.target 파일을 가리키는 symlink /etc/systemd/systemd/ctrl-alt-del.service가 작성됩니다. Ctrl + Alt + Del이 호출되면 systemd는 ctrl-alt-del.service를 찾고 reboot.service를 실행합니다. Systemd는 정상 작동 중에 [Install] 섹션을 전혀 보지 않으므로 해당 섹션의 지시문은 활성화 중에 생성 된 심볼릭 링크를 통해서만 영향을 미칩니다.

유닛 파일 foo.service와 함께 foo.service.wants/ 디렉토리가 존재할 수 있습니다. 이러한 디렉토리에서 심볼릭 링크 된 모든 단위 파일은 Wants= 유형의 종속성으로 암시 적으로 장치에 추가됩니다. Requires= 유형 의존성에 대해서도 유사한 기능이 존재하며,이 경우 디렉토리 접미사는 .requires/입니다. 이 기능은 장치 파일을 수정하지 않고도 장치를 다른 장치의 시작에 연결하는 데 유용합니다. Wants=의 의미에 대한 자세한 내용은 아래를 참조하십시오. 유닛 파일의 .wants/ 또는 .requires/ 디렉토리에 심볼릭 링크를 생성하는 기본 방법은 대상 유닛의 [Install] 섹션에 종속성을 포함시키고 파일 시스템에 enable 또는 preset 명령을 사용하여 심볼릭 링크를 만드는 것입니다. Systemctl (1).

유닛 파일 foo.service와 함께 'drop-in'디렉토리 foo.service.d/가 존재할 수 있습니다. 이 디렉토리에서 접미사가 '.conf'인 모든 파일은 단위 파일 자체를 구문 분석 한 후에 구문 분석됩니다. 장치 파일을 수정하지 않고 장치의 구성 설정을 변경하거나 추가 할 때 유용합니다. 드롭 인 파일에는 적절한 섹션 헤더가 포함되어야합니다. 인스턴스화 된 유닛의 경우,이 로직은 먼저 인스턴스 '.d/'서브 디렉토리 (예 : 'foo@bar.service.d/')를 찾아 '.conf'파일을 읽은 다음 템플리트 '.d/'서브 디렉토리를 읽습니다. (예 : 'foo @ .service.d /') 및 '.conf'파일이 있습니다. 또한 대시 ( '-')를 포함하는 장치 이름의 경우 모든 대시를 검색 한 후 장치 이름을 잘라서 생성 된 디렉토리 세트도 있습니다. 특히, 장치 이름 foo-bar-baz.service의 경우 일반 드롭 인 디렉토리 foo-bar-baz.service.d /뿐만 아니라 foo-bar-.service.d / 및 foo-.service도 검색됩니다.  .d/. 이것은 이름이 공통 접두어로 시작하는 관련 단위 세트에 대한 공통 드롭 인을 정의하는 데 유용합니다. 이 체계는 특히 체계적인 명명 구조가 구성 요소 구분 기호로 대시 주위에 구축되는 마운트, 자동 마운트 및 슬라이스 장치에 유용합니다. 접두사 계층 구조보다 아래에있는 동일한 이름의 드롭 인 파일은 접두사 계층 구조보다 상위 계층 구조보다 우선합니다. 즉, foo-bar-.service.d/10-override.conf는 foo-.service.d/10-override.conf보다 우선합니다.

위에서 설명한 유닛 별칭의 경우 별칭과 모든 별칭에 대한 드롭 인이로드됩니다. Default.target 별명 지정 예 graphic.target, default.target.d/, default.target.wants/, default.target.requires/, graphic.target.d/, graphic.target.wants/, graphic.target .requires/를 모두 읽습니다. 템플릿의 경우 템플릿의 드롭 인, 템플릿 별칭, 템플릿 인스턴스 및 모든 별칭 인스턴스를 읽습니다. 특정 템플릿 인스턴스 만 별칭으로 지정하면 대상 템플릿, 대상 템플릿 인스턴스 및 별칭 템플릿 인스턴스에 대한 드롭 인을 읽습니다.

/etc/systemd/system 외에도 시스템 서비스의 드롭 인 '.d/'디렉토리는 /usr/lib/systemd/system 또는 /run/systemd/system 디렉토리에있을 수 있습니다. /etc의 드롭 인 파일은 /run의 파일보다 우선하며, /usr/ lib의 파일보다 우선합니다. 이 디렉토리 중 하나에있는 드롭 인 파일은 위치에 관계없이 장치 파일보다 우선합니다. 이름이 다른 여러 개의 드롭 인 파일은 파일이있는 디렉토리에 상관없이 사전 순서대로 적용됩니다.

또한 unit 은 type.d/의 최상위 드롭 인을 지원합니다. 여기서 형식은 예를 들어 'service'또는 'socket-시스템의 모든 해당 단위 파일 설정을 변경하거나 추가 할 수 있습니다. 드롭 인 구성 적용의 형식과 우선 순위는 위에서 정의한 내용을 따릅니다. Type.d/의 구성은 이름 별 대체 디렉토리의 설정과 비교하여 우선 순위가 가장 낮습니다. 따라서 foo-.service.d/10-override.conf의 내용은 service.d/10-override.conf를 대체합니다.

systemd는 장치간에 유연한 종속성 시스템을 제공하지만,이 기능을 조금만 사용하는 대신 종속성을 암시 적으로 만드는 버스 기반 또는 소켓 기반 활성화와 같은 기술을 사용하여 시스템을 더 단순하고 유연하게 만드는 것이 좋습니다.

위에서 언급 한 바와 같이, 유닛은 템플릿 파일로부터 인스턴스화 될 수있다. 이를 통해 단일 구성 파일에서 여러 장치를 작성할 수 있습니다. Systemd가 장치 구성 파일을 찾으면 먼저 파일 시스템에서 리터럴 장치 이름을 검색합니다. 그것이 성공하지 않고 장치 이름에 '@'문자가 포함되어 있으면 systemd는 동일한 이름을 공유하지만 인스턴스 문자열 (예 : '@'문자와 접미사 사이의 부분)이 제거 된 장치 템플릿을 찾습니다. 예 : 서비스 getty@tty3.service가 요청되고 해당 이름의 파일이 없으면 systemd는 getty@.service를 찾고 해당 구성 파일에서 서비스를 찾으면 인스턴스화합니다.

구성 파일 내에서 인스턴스 문자열을 참조하기 위해 많은 구성 옵션에서 특수 '%i'지정자를 사용할 수 있습니다. 자세한 내용은 아래를 참조하십시오.

unit 파일이 비어 있거나 (예 : 파일 크기가 0) /dev/null로 심볼릭 링크되어 있으면 해당 구성이로드되지 않고로드 상태가 '마스킹 됨'으로 표시되어 활성화 할 수 없습니다. 이 기능을 사용하면 장치를 완전히 비활성화하여 수동으로도 시작할 수 없습니다.

unit 파일 형식은 [Interface Stability Promise](https://www.freedesktop.org/wiki/Software/systemd/InterfaceStabilityPromise)에서 다룹니다.



### 유닛 이름에  이스케이프 문자열 포함시키기








### - 끝 -





