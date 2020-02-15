# CentOS-7 mariadb 설치 및 data directory 변경

본 문서는 CentOS-7 에 mariadb 를 설치 하고, mariadb 의 기본 data 저장 directory 인 /var/lib/mysql 을 다른 위치로 변경 하는 작업을 수행 하기 위한 참조 문서 입니다.



## 1. mariadb 설치

### 1.1 mariadb 저장소 설정

/etc/yum.repos.d/mariadb.repo 에 다음 내용을 추가 합니다.

```bash
vi /etc/yum.repos.d/mariadb.repo

[mariadb]
name = MariaDB
baseurl = http://yum.mariadb.org/10.4/centos7-amd64
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
gpgcheck=1
```

위의 숫자중 10.4 로 되어 있는 부분은 다른 버전 으로(예: 10.1) 변경할 수 있습니다.



### 1.1 yum 을 이용한 설치

다음 명령을 수행 하여 mariadb 를 설치 합니다.

```bash
yum install -y MariaDB-server MariaDB-client
```





## 2. mariadb data 저장 위치 변경 및 환경 설정

### 2.1 selinux 비 활성화

/etc/sysconfig/selinux 파일을 편집하여 selinux 를 비활성화 시킵니다.

```bash
vi /etc/sysconfig/selinux

-- 아래 처럼 변경 후 저장
SELINUX=disabled
```

os reboot



### 2.2  mariadb data copy 및 속성 변경

다음 명령을 수행 하여 기 저장된 data 를 복사 합니다.

```bash
-- mysql data 를 저장할 directory 생성
mkdir /home/data

-- 기존 data 복사
cp -r /var/lib/mysql/* /home/data/

-- 퍼미션 설정
chown -R mysql:mysql /home/data
```



### 2.3 mariadb 실행 환경 수정

#### 2.3.1 /etc/my.cnf 파일 수정

/etc/my.cnf  파일을 열어 다음 내용을 추가 합니다.

```bash
#
# This group is read both both by the client and the server
# use it for options that affect everything
#
[client-server]

#
# include all files from the config directory
#
!includedir /etc/my.cnf.d

-- 여기서 부터 추가
[mysqld]
log_error = /var/log/mysql-error.log
user = mysql
datadir = /home/data
socket = /home/data/mysql.sock

[client]
socket = /home/data/mysql.sock

```



#### 2.3.2 mariadb.service 파일 수정

/usr/lib/systemd/system/mariadb.service 파일을 열어 ProtectHome 의 값을 수정 합니다.

```bash
vi /usr/lib/systemd/system/mariadb.service

-- true 로 되어 있는 값을 false 로 변경 합니다.
ProtectHome=false 
```



### 2.4 daemon reload

다음 명령을 수행 하여 daemon 을 reload 합니다.

```bash
systemctl daemon-reload
```





## 3. mariadb 시작 및 확인

### 3.1 mariadb 시작 및 data directory 확인

#### 3.1.1 mariadb server 시작

```bash
-- mariadb 서버 시작
systemctl start mariadb

또는
service mariadb start
```



#### 3.1.2 mariadb 기동 상태 확인

```bash
-- 다음 명령을 실행 하여 mariadb 기동 상태를 점검 합니다.
ps -ef | grep mysqld | grep -v grep
```



```bash
[root@CentOS7 home]# mysql
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 8
Server version: 10.4.12-MariaDB MariaDB Server

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> select @@datadir;
+-------------+
| @@datadir   |
+-------------+
| /home/data/ |
+-------------+
1 row in set (0.000 sec)

```



### 3.2 mariadb 종료

다음 명령을 실행 하여 mariadb 서버를 종료 합니다.

```bash
systemcrl stop mariadb

-- 또는
service mariadb stop
```



### - 끝 -

---



# Ubuntu 16.04 mariadb 설치 및 data dir 변경 

Ubuntu 는 CentOS 와 달리 mariadb 환경 파일이 간단하게 되어 있으며 위치가 상이한 관계로 본 장을 추가 합니다.

### 사전 작업

Ubuntu 는 처음 설치시 ssh 가 설치되지 않아 외부 터미널 프로그램을 이용하여 접속 할 수 없는 경우가 있습니다.

Ubuntu 에 ssh 를 설치 하는 과정 은 다음과 같습니다.

```bash
$ dpkg -l | grep openssh-server
$ sudo apt-get install openssh-server
$ sudo service ssh start
```



## 1. ubuntu 에 mariadb 설치

```bash
sudo apt -y install software-properties-common dirmngr
sudo apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xF1656F24C74CD1D8
sudo add-apt-repository 'deb [arch=amd64] http://mirror.zol.co.zw/mariadb/repo/10.4/ubuntu xenial main'

sudo apt update
sudo apt -y install mariadb-server mariadb-client

```



## 2. datadir 변경

```
vi /etc/mysql/my.cnf
	-- datadir 변경

vi /lib/systemd/system/mariadb.service
    -- ProtectHome=false # true -> false

systemctl daemon-reload
```



ubuntu 는 port 를 datadir 에 같이 위치 시키지 않기 때문에 변경이 비교적 간단 합니다.

my.cnf 를 찾는 방법 입니다.

```bash
sudo grep -R --color datadir /etc/mysql/*
```







### - 끝 -



