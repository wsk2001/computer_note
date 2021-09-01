# wsl 기술 자료



## 0. WSL 2(Windows Subsystem For Linux 2) 정식 버전 사용하기

출처: https://www.lesstif.com/software-architect/wsl-2-windows-subsystem-for-linux-2-89555812.html

내용은 출처 참조.



## 1. 현재 설치되어 있는 wsl 목록 확인 방법

powershell 을 이용하여 작업 한다.

``` powershell
PS C:\Users\wonsool> wslconfig /l
Windows Subsystem for Linux Distributions:
CentOS7 (Default)
Ubuntu-18.04
```



## 2. 등록되어 있는 wsl 종료 하는 방법

powershell 을 이용한 작업

``` powershell
PS C:\Users\wonsool> wsl -t CentOS7
PS C:\Users\wonsool>
PS C:\Users\wonsool> wslconfig /l
Windows Subsystem for Linux Distributions:
CentOS7 (Default)
Ubuntu-18.04
PS C:\Users\wonsool> wslconfig /l
Windows Subsystem for Linux Distributions:
CentOS7 (Default)
Ubuntu-18.04
PS C:\Users\wonsool> wsl -t CentOS7
```



## 3. 서비스 구동

WSL 2 는 [*systemctl*](https://www.lesstif.com/system-admin/systemd-system-daemon-systemctl-24445064.html)을 지원하지 않지만 기존의 *[service ](https://www.lesstif.com/linux-infra-book/service-chkconfig-18219568.html)*명령은 지원하므로 daemon 을 띄울 때에 *[service ](https://www.lesstif.com/linux-infra-book/service-chkconfig-18219568.html)*명령을 사용해야 합니다. service 는 root 만 사용할 수 있는 명령어이므로 다음과 같이 *[/etc/sudoers](https://www.lesstif.com/ws/sudo-46366762.html)* 에 한 줄을 추가해 주면 편리하게 구동할 수 있습니다.



sudoers 파일 편집하기 위해 root 로 visudo 를 구동합니다. 

```bash
sudo visudo
```



모든 사용자가 service 명령을 사용할 수 있도록 추가하고 저장합니다.

```bash
%sudo ALL=NOPASSWD: /usr/sbin/service
```



vi 사용에 익숙 하다면  다음 명령을 이용하여 수정할 수 있습니다.

``` bash
vi /etc/sudoers
```





저는 WSL 에 MySQL 과 redis 를 설치하고 다음 script 를 한 번 실행해 주고 있습니다.

```bash
#!/bin/bash

sudo service mysql start
sudo service redis-server start
```



## [WSL] Windows Subsystem for Linux - SSH 서버 세팅하기

**WSL**은 기능상으로는 실제 리눅스 배포판과 다를 게 거의 없지만, 셸이 구동되는 기본 터미널이 꾸진 Windows 명령 프롬포트(cmd.exe)인 관계로 이로 인해 여러 가지 애로사항이 꽃핀다. 예를 들어,

- 파란색이 하나도 안보인다거나,
- 기껏 정성 들여 설정한 내용이 날아간다거나, (ㅂㄷ)
- 붙여넣기 단축키(Shift+Insert)가 안 먹는다거나..

등등...

이에 대한 해법으로 CMDer과 같은 명령 프롬포트 대체재를 사용하는 방법도 있지만, 가장 좋은 해결책은 WSL Ubuntu에 SSH 서버를 구동해 놓고, TeraTerm이나 Putty와 같은 훨씬 좋고 익숙한 터미널 프로그램으로 원격(?) 접속해서 사용하는 것이다.

WSL 리눅스 배포판에는 기본적으로 SSH 서버가 포함되어 있긴 하지만, 뭔가 문제가 있어서 접속을 시도하면 다음과 같이 "Connection refused" 오류가 발생하면서 접속이 이루어지지 않는다. (sshd의 설정을 변경해도 마찬가지다.)

이 문제를 해결하기 위해서는 WSL에 기본으로 설치되어 있던 SSH 서버를 완전히 삭제(purge)하고 재설치해줘야 한다.

 

### SSH 서버 재설치

WSL에 기본으로 설치되어 있는 SSH 서버는 약간 문제가 있어서 SSH 접속이 제대로 이루어지지 않으므로 완전 제거하고 재설치해야 한다.

작업을 수행하기 전에 APT Repository를 업데이트한다.

```bash
sudo apt-get update
sudo apt-get upgrade
```

SSH 서버를 설정파일까지 완전 삭제(purge)하고 재설치한다.

```bash
sudo apt-get purge openssh-server
sudo apt-get install openssh-server
```

 

### SSH 서버 설정

SSH 서버 설정파일 ''/etc/ssh/sshd_config''을 열고 다음 항목들의 주석을 해제한다.

```bash
Port 22
Protocol 2
PermitRootLogin no
AuthorizedKeysFile  .ssh/authorized_keys
PasswordAuthentication yes
PubkeyAuthentication yes
ChallengeResponseAuthentication no
X11Forwarding yes
UseDNS no
```

이 때, **"PermitRootLogin no"**는 반드시 설정해 줘야 한다. 그 외의 설정은 본인이 사용하는 실사용 서버의 SSH 서버 세팅 정보를 참조해서 변경해 주도록 한다.

참고: 해외 포럼에 굴러다니는 다른 매뉴얼에는 **"UsePrivilegeSeparation no"**도 추가하라고 되어 있는데, 이게 없어도 잘만 동작할 뿐더러 오히려 추가하면 SSH 데몬을 시작할 때 Deprecated 경고가 뜬다. 조금 더 확인해 볼 필요가 있는 사안임.

 

### SSH 서버 재시작

이후 다음 명령으로 SSH 서버를 **완전** 재시작 해 준다.

```bash
sudo service ssh --full-restart
sudo service ssh restart
```

 

### SSH 서버 접속 확인

마지막으로 같은 셸에서 다음과 같이 loopback 접속을 시도해서 SSH 서버의 정상 동작 여부를 확인한다.

```bash
ssh localhost
```

최종적으로 터미널 창을 닫고 Windows에서 SSH 클라이언트로 localhost로 접속을 시도해본다. (sshd가 동작중인 상태에서 Bash.exe나 Ubuntu.exe의 창을 닫더라도 접속이 가능하다.)

 

일단 SSH 서버를 한 번 구동시키면 이를 종료시키지 않는 한 SSH 접속이 가능하다. 하지만, Windows 부팅시 SSH 서버가 자동으로 시작되지는 않으므로 부팅 후 최초 1회는 수동으로 SSH 서버를 시작(sudo service ssh start)해 줘야 한다. 약간의 트릭으로 Windows 부팅시 SSH 서버가 자동으로 시작 되도록 할 수도 있다.

**→ [WSL에서 SSH 서버 자동 시작 설정하기](https://www.tuwlab.com/29342)**

 

### [sshd 데몬 실행시 host key 때문에 실행 안 될 때](https://jiphung.com/72)

``` bash
Could not load host key: /etc/ssh/ssh_host_key
Could not load host key: /etc/ssh/ssh_host_rsa_key
Could not load host key: /etc/ssh/ssh_host_dsa_key
Disabling protocol version 1. Could not load host key
Disabling protocol version 2. Could not load host key
sshd: no hostkeys available -- exiting.
```

이와 같은 메시지가 뜨면서 sshd 데몬이 실행 안 될 때...

 ssh-keygen으로 host key를 생성해 주고 데몬을 실행하면 됩니다

``` bash
# ssh-keygen 실행 방법
sudo ssh-keygen -t rsa1 -f /etc/ssh/ssh_host_key
sudo ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
sudo ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key 

# 각 명령 실행시 오류가 발생 하면 무시
# 월 물어오면 그냥 <enter>
```

