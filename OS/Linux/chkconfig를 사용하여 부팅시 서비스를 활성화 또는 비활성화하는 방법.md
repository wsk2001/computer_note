# chkconfig를 사용하여 부팅시 서비스를 활성화 또는 비활성화하는 방법

출처: https://www.thegeekdiary.com/how-to-enable-or-disable-service-on-boot-with-chkconfig/

CentOS / RHEL은 시스템의 다양한 실행 수준에서 시작되는 서비스를 관리하기위한 간단한 명령 줄 도구 (chkconfig)를 제공합니다. chkconfig는 서비스가 시작되어야하는 실행 레벨과 상대적으로 실행 레벨 초기화 중에 서비스가 시작되어야하는시기를 알리기 위해 실제 init 스크립트에 추가 주석 행이 필요합니다. (init 스크립트는 다른 서비스에 종속 된 서비스가 종속 된 서비스 이후에 시작되도록 특정 순서로 처리됩니다.) httpd init 스크립트에서 가져온 이러한 행은 다음과 같습니다.

``` bash
# chkconfig: 345 85 15
# description: Apache is a World Wide Web server.  It is used to serve
# HTML files and CGI.
```

여기,

**345** – 서비스가 기본적으로 활성화 될 런레벨.

**85** – 시작 우선 순위. 숫자가 낮을수록 우선 순위가 높아지고 주어진 런레벨 내에서 서비스가 더 빨리 시작됩니다.

**15** – 중지 우선. 숫자가 낮을수록 우선 순위가 높아지고 주어진 런레벨 내에서 서비스가 더 빨리 중지됩니다.



## chkconfig를 사용하여 서비스 나열

어떤 서비스가 어떤 실행 레벨에서 시작되는지 목록을 얻으려면 "chkconfig –list"명령을 사용하십시오.

``` bash
# chkconfig --list
acpid          	0:off	1:off	2:on	3:on	4:on	5:on	6:off
auditd         	0:off	1:off	2:on	3:on	4:on	5:on	6:off
blk-availability	0:off	1:on	2:on	3:on	4:on	5:on	6:off
cgconfig       	0:off	1:off	2:off	3:off	4:off	5:off	6:off
...
```

선택적으로 이름을 추가 인수로 추가 할 수 있으며 chkconfig는 해당 서비스에 대한 정보 만 나열합니다. 다음은 내 시스템에서 chkconfig –list iptables의 출력입니다.

``` bash
# chkconfig --list iptables
iptables       	0:off	1:off	2:off	3:on	4:on	5:on	6:off
```

이 경우 chkconfig는 iptables 서비스가 실행 수준 3, 4 및 5에 대해 시작될 것이라고보고합니다.



## 부팅시 서비스 활성화 또는 비활성화

이 예에서는 iptables 서비스를 사용합니다. 원하는 경우 서비스가 시작될 전류 규칙을 나열하십시오.

``` bash
# chkconfig --list iptables
httpd           0:off   1:off   2:off    3:off    4:off    5:off    6:off
```



런레벨을 지정하지 않고“chkconfig on”은 런레벨 2,3,4 및 5에서 서비스를 활성화합니다. 

예 :

``` bash
# chkconfig iptables on
```

``` bash
# chkconfig --list iptables
iptables       	0:off	1:off	2:on	3:on	4:on	5:on	6:off
```



마찬가지로 모든 실행 수준에서 서비스를 비활성화하려면 "chkconfig off"명령을 사용합니다. 예를 들면 :

``` bash
# chkconfig iptables off
```

``` bash
# chkconfig --list iptables
iptables       	0:off	1:off	2:off	3:off	4:off	5:off	6:off
```



### chkconfig 미세 제어

–level 옵션을 chkconfig에 제공하여 변경할 런레벨 (켜짐 또는 꺼짐)을 지정할 수 있습니다. 다른 런레벨은 변경되지 않습니다. 이것은 런레벨 3과 5에서 iptables를 시작하도록 시스템을 구성합니다.

``` bash
# chkconfig --level 35 iptables on
```

``` bash
# chkconfig --list iptables
iptables       	0:off	1:off	2:off	3:on	4:off	5:on	6:off
```



## chkconfig를 사용하여 서비스 추가

chkconfig에 제공된 권장 사항에 따라 모든 실행 레벨에 새 서비스를 추가하려면 다음 명령을 사용하십시오.

``` bash
# chkconfig --add [servicename]
```

chkconfig는 한 번에 올바른 디렉토리의 서비스에 대한 모든 링크를 설정합니다.



> 참고 : 응용 프로그램 또는 서비스가 설치되면 초기화 스크립트가 생성되고 /etc/init.d에 자동으로 추가됩니다. 따라서 서비스 이름을 식별하는 데 어려움이있는 경우 /etc/init.d를 방문하여 적절한 스크립트를 찾은 다음 해당 내용에서 서비스 이름을 얻으십시오.



## 서비스 정보 재설정

/etc/rc.d 디렉토리 트리의 백업과 시스템으로 돌아가 복원하는 방법이있는 한 서비스를 가지고 노는 것은 교육적입니다. 그러나 이러한 유형의 과감한 조치는 일반적으로 필요하지 않습니다. 대신 다음 명령을 실행하여 서비스의 시작 우선 순위 및 기타 정보를 권장 설정으로 복원 할 수 있습니다.

``` bash
# chkconfig [servicename] reset
```

이 명령은 모든 것을 정상적인 기본값으로 되돌립니다.



## chkconfig를 사용하여 서비스 제거

더 이상 서비스 사용이 필요하지 않은 경우 "chkconfig off"스위치를 사용하여 부팅시 비활성화 할 수 있습니다.

``` bash
# chkconfig [servicename] off
```



그런 다음 다음 명령을 사용하여 서비스 실행을 중지해야합니다.

``` bash
# service [servicename] stop
```

앞의 명령이 즉시 적용됩니다. 그러나이 절차를 완료하려면 다음을 입력하여 chkconfig 관리 도구에서 제거 할 수 있습니다.

``` bash
# chkconfig --del [servicename]
```



### -- 끝 --

