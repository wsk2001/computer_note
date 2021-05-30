# CentOS 6 Python 2.7 설치

출처: https://chhanz.github.io/linux/2019/01/09/rhel6-python27/

CentOS 6 의 기본 Python 버전은 v2.6 입니다.
CentOS 의 응용프로그램들은 *Python 2.6* 과 호환되도록 설정되어있고, 이를 *Update* 를 할 경우 다수의 응용프로그램들이 문제가 발생 할 수 있습니다.
하지만 일부 시스템은 몇몇 소프트웨어를 사용하기위해, 최신 버전의 Python 을 사용하기 원합니다.

위와 같은 일부 시스템에 최신 버전의 Python 을 설치하기 위해서는 다음과 같은 절차로 설치를 진행하면 됩니다.



## SCL Repository 연결

------

*yum* 명령을 통해 SCL Repository 를 연결합니다.

```bash
# yum install -y centos-release-scl
```



## SCL Repository 연결 확인

Repository 가 정살적으로 연결 되었는지 확인합니다.

```bash
# yum repolist
Loading mirror speeds from cached hostfile
 * base: centos.mirror.moack.net
 * epel: mirror.premi.st
 * extras: centos.mirror.moack.net
 * updates: centos.mirror.moack.net
repo id                                        repo name                                                                    status
base                                           CentOS-6 - Base                                                               6,713
centos-sclo-rh                                 CentOS-6 - SCLo rh                                                            2,880
centos-sclo-sclo                               CentOS-6 - SCLo sclo                                                            406
epel                                           Extra Packages for Enterprise Linux 6 - x86_64                               12,517
extras                                         CentOS-6 - Extras                                                                35
updates                                        CentOS-6 - Updates                                                              257
repolist: 22,808
```



## Python 2.7 설치

------

등록한 Repository 를 통해 설치가 가능한 Python 을 확인합니다.

```bash
# yum list python27-python.x86_64 --showd
Loaded plugins: fastestmirror, refresh-packagekit, security
Loading mirror speeds from cached hostfile
 * base: centos.mirror.moack.net
 * epel: mirror.premi.st
 * extras: centos.mirror.moack.net
 * updates: centos.mirror.moack.net
Available Packages
python27-python.x86_64                                         2.7.8-3.el6                                          centos-sclo-rh
python27-python.x86_64                                         2.7.8-16.el6                                         centos-sclo-rh
python27-python.x86_64                                         2.7.8-18.el6                                         centos-sclo-rh
python27-python.x86_64                                         2.7.13-3.el6                                         centos-sclo-rh
```

원하는 특정 버전을 확인하고 설치 진행합니다.
테스트를 위해 v2.7.8-3 로 설치하도록 하겠습니다.

```bash
# yum install python27-python-2.7.8-3.el6
Loaded plugins: fastestmirror, refresh-packagekit, security
Setting up Install Process
Loading mirror speeds from cached hostfile
 * base: centos.mirror.moack.net
 * epel: mirror.premi.st
 * extras: centos.mirror.moack.net
 * updates: centos.mirror.moack.net
Resolving Dependencies
--> Running transaction check
---> Package python27-python.x86_64 0:2.7.8-3.el6 will be installed
--> Processing Dependency: python27-python-libs(x86-64) = 2.7.8-3.el6 for package: python27-python-2.7.8-3.el6.x86_64
--> Processing Dependency: python27-runtime for package: python27-python-2.7.8-3.el6.x86_64
--> Processing Dependency: libpython2.7.so.1.0()(64bit) for package: python27-python-2.7.8-3.el6.x86_64
--> Running transaction check
---> Package python27-python-libs.x86_64 0:2.7.8-3.el6 will be installed
---> Package python27-runtime.x86_64 0:1.1-25.el6 will be installed
--> Processing Dependency: scl-utils for package: python27-runtime-1.1-25.el6.x86_64
--> Running transaction check
---> Package scl-utils.x86_64 0:20120927-29.el6_9 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

==================================================================================================================================
 Package                             Arch                  Version                            Repository                     Size
==================================================================================================================================
Installing:
 python27-python                     x86_64                2.7.8-3.el6                        centos-sclo-rh                 80 k
Installing for dependencies:
 python27-python-libs                x86_64                2.7.8-3.el6                        centos-sclo-rh                5.6 M
 python27-runtime                    x86_64                1.1-25.el6                         centos-sclo-rh                1.0 M
 scl-utils                           x86_64                20120927-29.el6_9                  base                           23 k

Transaction Summary
==================================================================================================================================
Install       4 Package(s)

Total download size: 6.8 M
Installed size: 24 M
Is this ok [y/N]:

... 중략 ...

Installed:
  python27-python.x86_64 0:2.7.8-3.el6

Dependency Installed:
  python27-python-libs.x86_64 0:2.7.8-3.el6     python27-runtime.x86_64 0:1.1-25.el6     scl-utils.x86_64 0:20120927-29.el6_9

Complete!
```

위와 같이 설치가 완료가 되면, *scl Command* 를 이용하여 Python 을 활성화 해야됩니다.

```bash
// scl 설정 전
# python -V
Python 2.6.6

// scl 을 이용하여 PATH 등록
# scl --list
python27
# scl enable python27 bash
# echo $PATH
/opt/rh/python27/root/usr/bin:/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin

# python -V
Python 2.7.8
```

PATH 가 등록이 되고 Python 이 최신 버전으로 *Update* 된 것을 볼 수 있습니다.



## .bash_profile 에 등록

------

Python 2.7 설치 경로에 있는 enable 파일을 *.bash_profile* 에 추가하여 shell 이 재시작하더라도 사용이 가능하게 설정을 할 수 있습니다.

```bash
# vi .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
        . ~/.bashrc
fi

# User specific environment and startup programs

PATH=$PATH:$HOME/bin

export PATH

source /opt/rh/python27/enable
```



### 시스템 재접속 테스트

```bash
# exit
logout
Connection to 192.168.XX.XX closed.

~ $ ssh root@192.168.XX.XX
root@192.168.XX.XX's password:
Last login: Wed Jan  9 00:00:00 2019 from 192.168.XX.XX

# python -V
Python 2.7.8
```

위와 같이 설치가 완료되었습니다.