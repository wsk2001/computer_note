# EncFS (Encrypted Filesystem) 소개

link: https://github.com/vgough/encfs

- 개별 파일 암호화
- LGPL
- EncFS는 Cygwin에서 작동하며 일부 Windows 포트도 있습니다.
- windows 용 저장소 : https://github.com/jetwhiz/encfs4win
- 지원 OS: Linux, FreeBSD, Mac OS X, Windows
- 개발 언어 CPP



## 1. 소개

### 1.1 About

EncFS는 사용자 공간에 암호화 된 파일 시스템을 제공합니다. 파일 시스템 인터페이스 용 FUSE 라이브러리를 사용하여 사용자 공간에서 실행됩니다. EncFS는 LGPL에 따라 라이센스가 부여 된 오픈 소스 소프트웨어입니다.

EncFS는 현재 15 세 이상입니다 (2003 년 첫 번째 릴리스). 이것은 오래된 NFS와 CFS와 같은 커널 기반의 암호화 된 파일 시스템이 리눅스 개발에 보조를 맞추지 않았기 때문에 작성되었습니다. FUSE를 사용할 수있게되었을 때, 나는 CFS를 대신 사용하여 2003 년 오픈 소스에 대한 첫 번째 버전을 발표했다.

EncFS는 가상 파일 시스템의 모든 요청을 원시 파일 시스템의 암호화 된 동등한 작업으로 변환하여 개별 파일을 암호화합니다.

자세한 내용은 다음을 참조하십시오.

> - The excellent [encfs manpage](https://github.com/vgough/encfs/blob/master/encfs/encfs.pod)
> - The technical overview in [DESIGN.md](https://github.com/vgough/encfs/blob/master/DESIGN.md)



### 1.2 Status

지난 15 년 동안 많은 훌륭한 대안들이 생겨났습니다. 컴퓨팅 성능은 개인용 컴퓨터의 파일 시스템 전체 (심지어 휴대 전화까지도)를 암호화하는 것이 합리적 일 때까지 증가했습니다. 리눅스에서는 ecryptfs가 동적으로 마운트 가능한 암호화 된 홈 디렉토리를 제공하며 우분투와 같이 제가 사용하는 배포판에 잘 통합되어 있습니다.

EncFS는 잠시 동안 휴면 상태였습니다. 버전 2에 대한 더 나은 기반을 제공하기 위해 정리를 시작했지만 EncFS 꽃이 다시 커뮤니티의 관심사에 달려 있는지 여부를 확인합니다. 누구나 쉽게 기여할 수 있도록하기 위해 GitHub (2014)에 새 집을 옮기고 있습니다. 그 이후로 프로젝트는 여러 참여자 덕분에 몇 번 업데이트되었으므로 EncFS에 관심이 있으시면 잠깐 들러주세요!



### 1.3 Unique Features

EncFS에는 2014 년 12 월 현재 귀하에게 흥미로운 몇 가지 기능이 있습니다.

#### 1.3.1 Reverse mode

`encfs --reverse` 암호화되지 않은 폴더에 대한 암호화 된보기를 제공합니다. 이를 통해 rsync와 같은 표준 도구를 사용하여 암호화 된 원격 백업을 수행 할 수 있습니다.



#### 1.3.2 Fast on classical HDDs

EncFS는 일반적으로 stat ()의 ecryptfs보다 훨씬 빠릅니다. 즉, 백업 장치가 고전적인 하드 디스크 일 때 많은 작업 부하가 발생합니다. 이것은 ecryptfs가 파일 크기를 결정하기 위해 각 파일 헤더를 읽어야하기 때문입니다. EncFS는 그렇지 않습니다. 이것은 각 stat에 대한 추가 탐색입니다. HDD, SSD 및 램 디스크에 대한 자세한 벤치 마크는  [PERFORMANCE.md](https://github.com/vgough/encfs/blob/master/PERFORMANCE.md) 를 참조하십시오.



#### 1.3.3 Works on top of network filesystems

EncFS는 네트워크 파일 시스템 (NFS, CIFS ...)에서 작동하지만 ecryptfs는 여전히 문제( [problems](https://bugs.launchpad.net/ecryptfs/+bug/277578))가있는 것으로 알려져 있습니다.



### 1.4 Development

마스터 브랜치는 최신의 안정적인 코드베이스를 포함합니다. 버그 수정 및 개선이 필요한 부분입니다.

dev 브랜치는 실험적인 작업을 포함하는데, 그 중 일부는 안정적 일 때 master 브랜치로 역 이식 될 수 있습니다. dev 브랜치는 안정적이지 않으며, 변경 사항 간의 역 호환성을 보장하지 않습니다.



### 1.5 Windows

EncFS는 Cygwin에서 작동하며 일부 Windows 포트도 있습니다.

추가 정보는 위키([the wiki](https://github.com/vgough/encfs/wiki))를 참조하십시오.



## 2. 설치

이 문서는 EncFS 컴파일을위한 일반적인 정보를 제공합니다. 

운영 체제 또는 배포판에 대한 구체적인 지침을 찾으려면 [wiki](https://github.com/vgough/encfs/wiki) 를 살펴보십시오.



### 2.1 Compiling EncFS

EncFS는 CMake 툴 체인을 사용하여 메이크 파일을 만듭니다.

EncFS를 구축하고 테스트하는 가장 빠른 방법 :

```bash
git clone https://github.com/vgough/encfs.git

cd encfs

./build.sh
```



 또는 EncFS를 구축하기위한 자세한 단계는 다음과 같습니다.

```bash
mkdir build
cd build
cmake ..
make
```



선택 사항이지만 강력하게 권장되는 것은 단위 및 통합 테스트를 실행하여 생성 된 바이너리가 예상대로 작동하는지 확인하는 것입니다. 단위 테스트는 거의 즉시 실행됩니다.

```bash
make unittests
make test
```



통합 테스트는 20 초 정도 걸리고 암호화 된 파일 시스템을 마운트하고 테스트를 실행합니다 : 
루트 (또는 sudo)에서 통합 테스트를 실행하면 추가 테스트가 실행됩니다

```bash
make integration
```



컴파일 프로세스에서는 encfs 디렉토리에 encfs 및 encfsctl이라는 두 개의 실행 파일을 만듭니다. 다음을 통해 시스템 디렉토리에 설치할 수 있습니다.

```bash
make install
```



기본 경로 (/usr/local)가  설치할 위치가 아닌 경우 cmake를 실행할 때 CMAKE_INSTALL_PREFIX 옵션을 설정하십시오. 예 :

```bash
cmake .. -DCMAKE_INSTALL_PREFIX=/opt/local
```



Encfs와 encfsctl은 수동으로 설치할 수도 있습니다. 특별한 권한이 필요 없습니다. 맨 페이지 encfs.1과 encfsctl.1을 원할 수도 있습니다.



## 3. Dependencies

EncFS는 여러 라이브러리에 의존합니다.

```
* fuse                   : the userspace filesystem layer
* openssl / libressl     : used for cryptographic primitives
* tinyxml2 (embeded)     : for reading and writing XML configuration files
* gettext                : internationalization support
* libintl                : internationalization support
* cmake                  : version 3.0.2 (Debian jessie version) or newer
* GNU make / ninja-build : to run the build for cmake
```



## 4. Ubuntu 에 EncFS Source 를 이용한 설치

### 4.1. SSH 설치

vmware 에서 제공하는 terminal 을 이용하여 작업을 하다보면 여러가지로 불편한 관계로 Ubuntu 에 ssh server 를 설치 하기로 하였다.

#### 4.1.1 SSH 설치 확인

```bash
dpkg -l | grep openssh
```

출력 화면에 `openssh-server` , `openssh-sftp-server`  가 표시되지 않으면 server 가 설치 되지 않았으므로 다음 명령을 이용하여 ssh server 를 설치 한다.



```bash
sudo apt-get update

sudo apt-get install -y openssh-server
```



#### 4.1.2 SSH Server 실행

설치가 완료 되면 다음 명령으로 ssh server 를 실행 한다.

```bash
sudo service ssh start
```



### 4.2. GCC(G++) 설치

Linux 의 대부분 Package 가 C/C++ 로 작성 되어 있기 때문에 GCC 를 설치 한다. VeraCrypt 도 C/C++ 로 작성 되어 있다. GCC 설치 절차는 다음과 같다.

#### 4.2.1 설치 명령

```bash
sudo apt-get upgrade

sudo apt-get install -y build-essential
```

- 위의 명령중 `build-essential` package 를 지정 한 것은 GCC, G++, make Utility 를 한번에 설치 하기 위해서 지정 하였다. 각각 설치 하여도 상관은 없다.



#### 4.2.2 설치 확인

```bash
gcc --version
[출력]
gcc (Ubuntu 5.4.0-6ubuntu1~16.04.10) 5.4.0 20160609

g++ --version
[출력]
g++ (Ubuntu 5.4.0-6ubuntu1~16.04.10) 5.4.0 20160609

```



### 4.3 cmake 설치

```bash
sudo apt install -y cmake
```



### 4.4. FUSE 설치

#### 4.4.1 간단 설치

```bash
sudo apt install -y libfuse-dev
```



#### 4.4.2 Source 를 이용한 설치

```bash
sudo apt install -y libtool
sudo apt install -y m4
sudo apt-get install -y gettext
sudo apt install -y autoconf

wget https://github.com/libfuse/libfuse/archive/fuse-2.9.7.tar.gz
tar -xvf fuse-2.9.7.tar.gz
cd libfuse-fuse-2.9.7/
makeconf.sh
./configure
 make -j8
sudo make install
cd example
mkdir ~/fuse_dir
hello ~/fuse_dir
ls -al ~/fuse_dir
cat ~/fuse_dir/hello
mount
sudo fusermount -u ~/fuse_dir
mount
```

* FUSE source 는 작업시점(2018-12-20) 현재 `fuse-3.3.0.tar.gz` 이 최신 version 이지만 EncFS 와 호환이 되지 않는 관계로(함수가 조금 많이 변경 되었음) `fuse-2.9.7.tar.gz` 를 이용하여 설치 함. 그리고 간단 설치와 동일한 Version 임.



### 4.5 openssl 설치
```bash
sudo apt install -y openssl libssl-dev
```



### 4.6 tinyxml2 설치

```bash
sudo apt install -y libtinyxml2-dev
```

- tinyxml2 





### 4.7 gettext 설치

```bash
sudo apt-get install -y gettext
```

- gettext-dev



### 4.8 git  설치

```bash
sudo apt nstall  -y git
```



### 4.9 EncFS Download

```bash
git clone https://github.com/vgough/encfs.git
```



### 4.10 EncFS 컴파일

```bash
cd encfs

./build.sh
```

- 컴파일 완료 후 make integration 에서 일부 오류가 나지만 일단 `skip` 하자



## 5. EncFS로 데이터 암호화

참조: https://www.howtoforge.com/tutorial/encrypt-your-data-with-encfs-on-ubuntu/

위의 4 장 에서 EncFS 를 설치 하였으면 `~/encfs/build` 에 `encfs` 실행 파일이 생성 된다.  테스트를 쉽게 하기 위해서 `.profile` 의 `PATH` 에 `.:~/encfs/build`  를 추가 하고 테스트를 진행 한다.

수정 후 `.profile` 의 `PATH` 는 다음과 같다.

```
PATH=".:~/encfs/build:$HOME/bin:$HOME/.local/bin:$PATH"
```

 이 튜토리얼에서는 Ubuntu 16.04 (Xenial Xerus)에서 EncFS를 사용하여 데이터를 암호화하는 방법을 보여줍니다.

### 5.1 사전주의 사항

이 튜토리얼에서는 Ubuntu 16.04 시스템에 사용자 이름 `falko`를 사용하고 있습니다. 보안 감사에서 현재 encfs를 구현할 때 몇 가지 문제점을 발견했습니다. 아래 보안 경고에서 encfs를 사용하려는 방식이 이러한 문제의 영향을 받는지 결정하십시오. encfs에 대한 대안은 ecryptfs (https://launchpad.net/ecryptfs)입니다. 다른 튜토리얼에서 다루겠습니다.

#### 5.1.1 Encfs 보안 정보

Taylor Hornby의 보안 감사 (Defay Security)에 따르면 Encfs의 현재 구현은 여러 유형의 공격에 취약하거나 잠재적으로 취약합니다. 예를 들어, 암호화 된 데이터에 대한 읽기 / 쓰기 액세스 권한을 가진 공격자는 이후의 암호화 복잡성을 낮출 수 있습니다 암호화 된 데이터를 합법적 인 사용자가 알아 채지 못하게하거나 타이밍 분석을 사용하여 정보를 추론 할 수 있습니다. 이러한 문제가 해결 될 때까지는 이러한 공격이 가능한 시나리오에서 중요한 데이터의 안전한 집으로 간주해서는 안됩니다. '



### 5.2 EncFS 사용

#### 5.2.1 home directory 에 `encrypted`,  `decrypted ` directory 생성

```
mkdir -p ~/encrypted
mkdir -p ~/decrypted
```

`decrypted` 디렉토리는 `encrypted` 디렉토리의 마운트 지점(mount point)으로 사용됩니다.



#### 5.2.2 마운트

```
encfs ~/encrypted ~/decrypted
```

이 명령을 처음 실행하면 EncFS 설정이 시작되고 암호화 된 볼륨에 대한 암호를 정의해야합니다

```bash
Creating new encrypted volume.
Please choose from one of the following options:
 enter "x" for expert configuration mode,
 enter "p" for pre-configured paranoia mode,
 anything else, or an empty line will select standard mode.
?>
```

- 여기서 `p` 를 입력 합니다.



그 이후 다음 화면이 나타납니다.

```bash
Paranoia configuration selected.

Configuration finished.  The filesystem to be created has
the following properties:
Filesystem cipher: "ssl/aes", version 3:0:2
Filename encoding: "nameio/block", version 4:0:2
Key Size: 256 bits
Block Size: 1024 bytes, including 8 byte MAC header
Each file contains 8 byte header with unique IV data.
Filenames encoded using IV chaining mode.
File data IV is chained to filename IV.
File holes passed through to ciphertext.

-------------------------- WARNING --------------------------
The external initialization-vector chaining option has been
enabled.  This option disables the use of hard links on the
filesystem. Without hard links, some programs may not work.
The programs 'mutt' and 'procmail' are known to fail.  For
more information, please see the encfs mailing list.
If you would like to choose another configuration setting,
please press CTRL-C now to abort and start over.

Now you will need to enter a password for your filesystem.
You will need to remember this password, as there is absolutely
no recovery mechanism.  However, the password can be changed
later using encfsctl.

New Encfs Password:

```

- 여기서 테스트를 위해서 password 를 `1111` 로 입력 합니다.

- `Verify Encfs Password:` 이 나오면 다시한번 `1111` 입력

그러면 command prompt 가 나옵니다.



#### 5.2.3  Mount 확인

확인을 위해 `mount` 명령을 실행 하면 다음고 같은 화면 이 나타납니다.

```
...
/home/falko/encfs on /home/falko/decrypted
```



그리고 df -h 명령으로 다시 확인 해 보면 다음 과 같은 화면이 나타압니다.

```bash
$> df -h

Filesystem      Size  Used Avail Use% Mounted on
udev            2.0G     0  2.0G   0% /dev
tmpfs           394M  5.8M  388M   2% /run
/dev/sda1        39G  2.0G   35G   6% /
tmpfs           2.0G     0  2.0G   0% /dev/shm
tmpfs           5.0M     0  5.0M   0% /run/lock
tmpfs           2.0G     0  2.0G   0% /sys/fs/cgroup
tmpfs           394M     0  394M   0% /run/user/1000
encfs            39G  2.0G   35G   6% /home/falko/decrypted

```



 #### 5.2.4 Mount 된 Valume 테스트

##### 5.2.4.1 테스트 전 directory 확인

```bash
cd ~/encrypted

ls -al
-출력-
total 12
drwxrwxr-x 2 falko    falko    4096 Dec 12 20:16 .
drwxr-xr-x 7 falko    falko    4096 Dec 12 20:12 ..
-rw-rw-r-- 1 falko    falko    1311 Dec 12 20:16 .encfs6.xml
```

```bash
cd ~/decrypted

ls -al
-출력-
total 8
drwxrwxr-x 2 falko    falko    4096 Dec 12 20:16 .
drwxr-xr-x 7 falko    falko    4096 Dec 12 20:12 ..
```



##### 5.2.4.2 테스트 Data 생성

```
cd ~/decrypted

echo "hello foo" > foo
echo "hello bar" > bar
ln -s foo foo2
```



##### 5.2.4.3 복호화 directory 확인

```bash
ls -l

total 8
-rw-rw-r-- 1 falko falko 10 Apr 26 18:54 bar
-rw-rw-r-- 1 falko falko 10 Apr 26 18:54 foo
lrwxrwxrwx 1 falko falko 3 Apr 26 18:54 foo2 -> foo
```



##### 5.2.4.4 암호화 directory 확인

```bash
cd ~/encrypted

ls -l

total 8
lrwxrwxrwx 1 falko falko 24 Dec 12 20:38 9vLgu-ZqrAK4Z5s1ZWSi0-QW -> OFrbSSsEUHEfLIzDm8fJUM,L
-rw-rw-r-- 1 falko falko 26 Dec 12 20:37 OFrbSSsEUHEfLIzDm8fJUM,L
-rw-rw-r-- 1 falko falko 26 Dec 12 20:37 PUWZRELDbAJmXfnSg55PNpXR

```



##### 5.2.4.5 암호화 된 파일 확인

vi PUWZRELDbAJmXfnSg55PNpXR

```
³< ëÂ;^G^Y¶W/¨¤?t^[¸9éF<Ì-`×M
```

- 파일이 잘 암호화 되어 있다.



#### 5.2.5 Unmount Mount Test

```bash
# unmount
encfs -u ~/decrypted
또는
fusermount -u ~/decrypted
ls -al ~/decrypted

#mount
encfs ~/encrypted ~/decrypted
EncFS Password: 1111

ls -al ~/decrypted
cat  ~/decrypted/foo
[출력]
hello foo
```

