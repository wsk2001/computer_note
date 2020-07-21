# CentOS 에 Samba 설치하기

이것 참조: https://suwoni-codelab.com/linux/2017/05/23/Linux-CentOS-Samba/



출처: https://suwoni-codelab.com/linux/2017/05/23/Linux-CentOS-Samba/



이 문서는 위 출처를 참조하여 CentOS Linux release 7.6.1810 (Core) 에 SAMBA 를 설치하는 과정을 설명 합니다. 

CentOS (Redhat 계열의 Linux) 에서 OS Version 을 확인 하는 방법은 다음고 같이 하면 됩니다.

```bash
[root@localhost ~]#
[root@localhost ~]# cat /etc/redhat-release
CentOS Linux release 7.6.1810 (Core)
[root@localhost ~]#

```



---



## 1. Samba 패키지 설치

Samba 를 설치 하기 전에 해당 서버에 Samba 가 설치 되어 있는지 확인 하는 방법 입니다.

``` bash
[root@localhost ~]#
[root@localhost ~]# systemctl status smb
Unit smb.service could not be found.
[root@localhost ~]#

```

위 표를 보면 samba 가 설치되어 있지 않은것을 확인 할 수 있습니다.



yum이라는 리눅스 패키지 매니저를 통해 Samba 를 설치하게 됩니다. (CentOS 8 부터는 Package 관리자가 dnf 로 바뀌었습니다.)

- yum 이란? - https://ko.wikipedia.org/wiki/Yum

아래의 명령어는 samba로 시작하는 패키지들을 모두 설치하며 설치할지 묻는 여부를 자동으로 y 로 응답하는 옵션을 주었습니다.

``` bash
[root@localhost ~]#
[root@localhost ~]# yum install samba* -y
Loaded plugins: fastestmirror, langpacks
Determining fastest mirrors
Could not retrieve mirrorlist http://mirrorlist.centos.org/?release=7&arch=x86_64&repo=os&infra=stock error was
14: curl#6 - "Could not resolve host: mirrorlist.centos.org; Unknown error"
~~~ 오류
[root@localhost ~]#
```

해당 server 에서 yum 저장소 관련 오류가 발생 하여 yum 을 다음과 같이 초기화 하였습니다.

``` bash
[root@localhost ~]#
[root@localhost ~]# yum clean all
Loaded plugins: fastestmirror, langpacks
Cleaning repos: base extras updates
Cleaning up list of fastest mirrors
[root@localhost ~]#

```



yum 재 설치 필요.

```
CentOS 7 기준

[python, yum 삭제]
# rpm -e --nodeps yum

[패키지 다운로드]
http://mirror.centos.org/centos-7/7/os/x86_64/Packages/
에서 아래의 패키지를 다운 받는다

yum-3.4.3-150.el7.centos.noarch.rpm             
yum-fastestmirror-1.1.31-40.el7.noarch.rpm
yum-metadata-parser-1.1.4-10.el7.centos.x86_64.rpm

[yum 재설치]
rpm -Uvh --nodeps yum-3.4.3-150.el7.centos.noarch.rpm             
rpm -Uvh --nodeps yum-fastestmirror-1.1.31-40.el7.noarch.rpm
rpm -Uvh --nodeps yum-metadata-parser-1.1.4-10.el7.centos.x86_64.rpm


출처: https://pubdata.tistory.com/96 [Data, Business, 아빠의 육아 스탬프]
```

