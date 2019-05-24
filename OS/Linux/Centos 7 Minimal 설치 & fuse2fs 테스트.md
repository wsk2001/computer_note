# Centos 7 Minimal 설치 & fuse2fs 테스트

설치 Version : CentOS-7-x86_64-Minimal-1810.iso

CentOS 에 XecureDisk4Linux binary 설치 하는 과정은 다음과 같다.



### 1.  Net-work 설정 

nmcli d[evice] 명령을 사용하여 이더넷 장치명을 확인할 수 있다. 처음 설치시 ethernet 이 disconnected 되어 있는것을 확인 할 수 있다. 

#### 1.1 IP 설정

**유동 IP 를 사용 하는 경우**

```
nmcli c m eth0 ipv4.method auto

nmcli c m eth0 connection.autoconnect yes
```



**고정 IP 를 사용 하는 경우**

```
nmcli c m eth0 ipv4.method manual

nmcli c m eth0 connection.autoconnect yes

nmcli c m eth0 ipv4.addresses 10.20.30.41/24

nmcli c m eth0 ipv4.gateway 10.20.30.254

nmcli c m eth0 ipv4.dns "168.126.63.1 168.126.63.2"

또는

nmcli c m eth0 ipv4.method manual connection.autoconnect yes ipv4.addresses 10.20.30.41/24 ipv4.gateway 10.20.30.254 ipv4.dns "168.126.63.1 168.126.63.2"
```



**네트워크 재 시작**

```systemctl restart network
systemctl restart network
```



**ip 확인**

```
ip address
```



**이더넷 장치명 확인**

```
nmcli d
```



#### 1.2 net-tools 설치

CentOS 7 기본 설치 시에 net-tools 패키지 설치가 빠져있어서 ifconfig, netstat 등의 명령어가 설치되지 않는다.

```
yum -y install net-tools 
```



#### 1.3 SSH 설치

```
# yum install openssh-server openssh-clients openssh-askpass

# vi /etc/ssh/sshd_config  실행 후 Port 22 로 되어 있는 부분 주석 해제

-- 방화벽 해제
# firewall-cmd --permanent --zone=public --add-port=22/tcp

-- ssh 시작
service sshd restart

```



### 2. 기타 설정

### CentOS 에 sudo 권한 설정

출처: <https://karl27.tistory.com/38>

 Ubuntu의 경우 위험성이 높은 root 계정을 아예 못 쓰게 막아놓고 sudo 라는 권한 명령어를 통해서 root의 권한을 부여받아서 사용이 가능하다. 하지만, centOS의 경우 기본적으로 sudo의 권한은 root만이 갖고 있다.

이 때문에 여러 개발자들이 접속해야 하는 서버의 경우 root 계정을 사용 못하게 하면서 사용하게 하기 위해서는 sudo 명령어를 사용할 수 있는 권한을 설정해 줘야 한다.

**설정파일**

기본적으로 설정파일의 권한 설정은 "- r-- r-- ---"(440)로 돠어 있다. 때문에 수정하기 위해서는 먼저는 쓰기 권한을 설정한 다음에 파일을 수정해야한다.

```ls -al /etc/sudoers 
ls -al /etc/sudoers 

퍼미션 상태확인하고나서.. 

chmod u+w /etc/sudoers vi /etc/sudoers
```



가능하면 몰아서 수정하자.

```
# 생략 
root ALL=(ALL) ALL 
# 여기에 추가를 하면 된다.

# 특정 사용자가 sudo 사용 (예시)lemi 계정
lemi    ALL(-ALL)    ALL

# 특정 그룹 사용자가 sudo 사용 (예시)devel 그룹
%devel    ALL=(ALL)    ALL

# 패스워드 생략하여 sudo 사용
lemi ALL=(ALL) NOPASSWD: ALL 
%devel ALL=(ALL) NOPASSWD: ALL
```

파일을 수정하나 다음에는 다시 위의 모드로 설정을 되돌려 놓아야 sudo 명령어를 사용할 수 있다. 즉, sudo 명령어를 사용할 때마다 위의 파일을 읽어 오는듯 하다.

```
chmod u-w /etc/sudoers
```

이 작업은 CentOS 뿐만 아니라 Ubuntu 에도 적용 된다. ubuntu 설치 시 지정된 기본 계정 이외의 계정 에서 sudo를 사용 할 경우 동일한 작업을 수행 해야만 새로 생성된 계정 에서 sudo 를 사용 할 수 있다.



### 3. XecureDisk4Linux 를 이용한 암호화 테스트

#### 3.1 fuse  설치

```
yum -y install fuse

또는 https://pkgs.org/download/fuse 에서 fuse-2.9.2-11.el7.x86_64.rpm 파일을 download 후 
rpm -ivh fuse-2.9.2-11.el7.x86_64.rpm 를 실행 하여 설치 한다.
```



#### 3.2 fuse lib 설치

fuse-libs-2.9.2-11.el7.x86_64.rpm 를 download 한다.

link: <https://pkgs.org/download/fuse-libs>

download(저장) 된 파일 위치: C:\Temp\fuse-libs-2.9.2-11.el7.x86_64.rpm

```
yum -y install fuse-libs

또는 download 된 피일 이용

rpm -ivh fuse-libs-2.9.2-11.el7.x86_64.rpm
```



#### 3.3 fuse 설치 확인

```
rpm -qa | grep fuse

-- 결과 --
fuse-libs-2.9.2-11.el7.x86_64
fuse-2.9.2-11.el7.x86_64

```



#### 3.4 /etc/fuse.conf 수정

```
- fuse 를 이용한 파일 시스템에 root 이외의 user 가 Access 하기 위해서 다음과 같이 설정 한다.

vi /etc/fuse.conf

-- 원본 --
# mount_max = 1000
# user_allow_other

수정본
# mount_max = 1000
user_allow_other

```



#### 3.5 Package 복사

##### 3.5.1 압축된 파일을 서버에 올리고 압축을 해제 한다.

테스트용은 `C:\Temp` 에 저장 되어 있음.

```
EncDisk.ubuntu.tar.gz
xecuredb.ubuntu.tar.gz
```

##### 3.5.2환경 설정 파일 수정 (.bashrc)

```
# export TZ='Asia/Seoul' # (이 줄은 직접 관련은 없음)
export LD_LIBRARY_PATH=.:/home/xecuredb/xecuredb/api/c/lib:$LD_LIBRARY_PATH
export PATH=.:$PATH
```

##### 3.5.3 테스트

```
dd if=/dev/zero of=fuse_data.img bs=4096 count=500000
mkfs ext4 -F fuse_data.img  
-- chown xecuredb:xecuredb fuse_data.img
run_mount.sh
ps -ef | grep XecureDisk4Linux
sudo chown -R xecuredb:xecuredb mount_tmp
```



이후 `~/EncDisk/mount_tmp`  경로에 파일을 저장 하면 암호화 되어서 저장 되고, 읽거나 꺼내면 자동으로 복호화 된다.



테스트 결과 정상 동작 함.



### -- 끝 --

