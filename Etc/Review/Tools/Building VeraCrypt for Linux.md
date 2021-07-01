# Building VeraCrypt for Linux

본 문서는 Linux( Ubuntu16.04.5 x64 Server) 에 VeraCrypt 를 설치하는 과정을 기술 한다. 필요에 따라 Ubuntu Ubuntu16.04.5 Desktop 에 설치 하는 부분도 같이 기록 하도록 한다. Ubuntu  는 vmware 를 이용하여 기본만 설치되어 있는것을 기준으로 문서를 작성 한다.



## 1. SSH 설치

vmware 에서 제공하는 terminal 을 이용하여 작업을 하다보면 여러가지로 불편한 관계로 Ubuntu 에 ssh server 를 설치 하기로 하였다.

### 1.1 SSH 설치 확인

```
dpkg -l | grep openssh
```

출력 화면에 `openssh-server` , `openssh-sftp-server`  가 표시되지 않으면 server 가 설치 되지 않았으므로 다음 명령을 이용하여 ssh server 를 설치 한다.



```
sudo apt-get update

sudo apt-get install -y openssh-server
```



### 1.2 SSH Server 실행

설치가 완료 되면 다음 명령으로 ssh server 를 실행 한다.

```
sudo service ssh start
```



## 2. GCC(G++) 설치

Linux 의 대부분 Package 가 C/C++ 로 작성 되어 있기 때문에 GCC 를 설치 한다. VeraCrypt 도 C/C++ 로 작성 되어 있다. GCC 설치 절차는 다음과 같다.

### 2.1 설치 명령

```
sudo apt-get upgrade

sudo apt-get install -y build-essential
```

- 위의 명령중 `build-essential` package 를 지정 한 것은 GCC, G++, make Utility 를 한번에 설치 하기 위해서 지정 하였다. 각각 설치 하여도 상관은 없다.



### 2.2 설치 확인

```
gcc --version
출력
gcc (Ubuntu 5.4.0-6ubuntu1~16.04.10) 5.4.0 20160609

g++ --version
출력
g++ (Ubuntu 5.4.0-6ubuntu1~16.04.10) 5.4.0 20160609

```



## 3. pkg-config 설치

**pkg-config**는 [소스 코드](https://ko.wikipedia.org/wiki/%EC%86%8C%EC%8A%A4_%EC%BD%94%EB%93%9C)로부터 소프트웨어를 [컴파일](https://ko.wikipedia.org/wiki/%EC%BB%B4%ED%8C%8C%EC%9D%BC)할 목적으로 설치된 [라이브러리](https://ko.wikipedia.org/wiki/%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC_(%EC%BB%B4%ED%93%A8%ED%8C%85))를 조회하기 위해 통일된 인터페이스를 제공하는 컴퓨터 [소프트웨어](https://ko.wikipedia.org/wiki/%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4)이다.  이 소프트웨어가 설치되어 있지 않으면 Makefile 을 수정 하여 컴파일 하여야 하는 수고를 하여야 한다.

### 3.1 pkg-config 설치 확인

```
dpkg -l | grep pkg-config
```

실행 결과 화면에 아무것도 나타나지 않으면 소프트웨어를 설치 하여야 한다.



### 3.2 pkg-config 설치

```
sudo apt-get install -y pkg-config
```



## 4. yasm 설치

VeraCrypt 의 일부 모듈이 Assembly 언어로 작성 되어 있기 때문에 `yasm` 을 설치 하여야 한다.

### 4.1 yasm 설치 확인

```
yasm --version
```

설치가 되어 있지 않으면 다음 명령을 이용해 설치 한다.



### 4.2 yasm 설치

```
sudo apt install -y yasm
```

설치 후 확인한 version 정보는 다음과 같다.

- yasm --version

  ```
  yasm 1.3.0
  Compiled on May  6 2015.
  Copyright (c) 2001-2014 Peter Johnson and other Yasm developers.
  Run yasm --license for licensing overview and summary.
  ```



## 5. wxWidgets 설치

VeraCrypt 는 기본적으로 GUI 모드로 컴파일 된다. 옵션을 지정 하여 Console 모드로 컴파일이 가능 하지만 컴파일 시점에는 `libwxgtk` 관련 모듈(header 파일-wx.h 포함)이 있어야 컴파일이 가능 하다. 따라서 Console 에서 실행을 하더라도 컴파일을 위해서는 wxWidgets 을 설치 하여야 한다.

### 5.1 wxWidgets 설치

```
sudo apt-get install -y libwxgtk3.0-dev
```



## 6. git 설치

VeraCrypt source 를 github 에서 편하게 내려받기 위해서 git 을 설치 한다.

```
sudo apt install -y git
```



## 7. FUSE 설치

VeraCrypt Linux 와 MAC OS X 에서 사용하는 파일 시스템은 FUSE 를 사용 한다. 따라서 FUSE 를 설치 한다.

```
sudo apt-get install -y libfuse-dev
```



## 8.  VeraCrypt 설치

위 과정까지 모두 설치가 되었다면 드디어 VeraCrypt 를 설치할 준비가 완료된 상태 이다. 

### 8.1 VeraCrypt Source Download

```
git clone https://github.com/veracrypt/VeraCrypt.git
```

위 명령을 수행 하면 ./VeraCrypt 경로에 VeraCrypt 를 컴파일 할 수 있는 source 파일 들이 저장 된다.



### 8.2 VeraCrypt  컴파일 (Console 실행 파일)

```
cd  ~/VeraCrypt/src

make NOGUI=1
```



### 8.3 VeraCrypt  컴파일 (GUI 실행 파일)

```
cd  ~/VeraCrypt/src

make
```

컴파일이 완료 되면 실행 파일은 `~/VeraCrypt/src/Main` 경로에 저장 된다.

- GUI 실행 파일, Console 실행 파일 둘다 `veracrypt` 로 생성 되므로 필요시 파일을 다른 이름으로 변경후 컴파일 하여 사용 하면 된다.

   

### 8.4 실행 파일 확인

```
cd  Main

./veracrypt --version
```

- 출력: `VeraCrypt 1.23-Hotfix-2`



## 9. veracrypt 를 이용하여 new volume 생성

veracrypt 가 설치 되었으면 암호화된 new volume 을 생성 하고 mount 하여 테스트를 진행 한다.

### 9.1 Create a new volume

다음 명령을 이용하여 `new volume` 을 생성 한다.

```
veracrypt -t -c
```

실행 하면 아래 화면이 나타난다.



```
Volume type:
 1) Normal
 2) Hidden
Select [1]:
```

- 위 화면 에서 그냥 <enter>



```
Enter volume path:
```

- volume 파일명을 입력 한다.
- Ex) ./temp/example.hc
- 현재 directory 아래 temp directory 가 생성되어 있어야 한다.



```
Enter volume size (sizeK/size[M]/sizeG):
```

- 볼륨 size 를 입력 한다.
- Ex) 10M



```
Encryption Algorithm:
 1) AES
 2) Serpent
 3) Twofish
 4) Camellia
 5) Kuznyechik
 6) AES(Twofish)
 7) AES(Twofish(Serpent))
 8) Camellia(Kuznyechik)
 9) Camellia(Serpent)
 10) Kuznyechik(AES)
 11) Kuznyechik(Serpent(Camellia))
 12) Kuznyechik(Twofish)
 13) Serpent(AES)
 14) Serpent(Twofish(AES))
 15) Twofish(Serpent)
Select [1]:
```

- 암호화 알고리즘을 선택 한다. 그냥 <enter>



```
Hash algorithm:
 1) SHA-512
 2) Whirlpool
 3) SHA-256
 4) Streebog
Select [1]:
```

- hash 알고리즘을 선택 한다. 그냥 <enter>



```
Filesystem:
 1) None
 2) FAT
 3) Linux Ext2
 4) Linux Ext3
 5) Linux Ext4
 6) NTFS
 7) exFAT
Select [2]:
```

- 파일 시스템을 선택 한다.  그냥 <enter>
- 4G 이상의 파일을 new volume 에 저장 할 경우는 6) NTFS 를 선택 하여야 한다. 



```
Enter password:
```

- password 를 입력 한다. 
- veracrypt 에서는 20 자 이상을 입력 하길 제안 하지만 테스트를 위해서 `1111` 로 입력 한다.



```
WARNING: Short passwords are easy to crack using brute force techniques!

We recommend choosing a password consisting of 20 or more characters. Are you sure you want to use a short password? (y=Yes/n=No) [No]:
```

- password 의 길이가 짧다고 WARNING message 를 보여 주는데 `y` 를 입력 하여 무시 한다.



```
Re-enter password:
```

- 다시 password 를 입력 한다. `1111`



```
Enter PIM:
```

- 그냥 <enter>

- PIM은 '개인 반복 배수 (Personal Iterations Multiplier)'의 약자입니다. VeraCrypt 1.12에 도입 된 매개 변수이며 값이 헤더 키 유도 함수에서 사용되는 반복 횟수를 제어합니다. 이 값은 암호 대화 상자 나 명령 줄을 통해 지정할 수 있습니다.



```
Enter keyfile path [none]:
```

- 그냥 <enter>



```
Please type at least 320 randomly chosen characters and then press Enter:
```

- 320 자를 입력 하라고 한다.
- 320 자를 입력 하지 않으면 더이상 진행 되지 않으니 입력 한다.



```
Done: 100.000%  Speed:  4.8 MB/s  Left: 0 s

The VeraCrypt volume has been successfully created.
```

위 화면이 나오면 정상적으로 파일이 생성 된것 이다.



아래 명령을 이용하여 파일이 생성 되어 있는것을 확인 한다.

```
ls -al  ./temp/example.hc
```





## 10. 암호화된 Volume 마운트

위 에서 생성된 ./temp/example.hc 파일을 이용하여 /var/vctemp 에 마운트를 수행 하는 방법을 기술 한다.

### 10.1 directory 생성

```
mkdir /var/vctemp

chmod 777 /var/vctemp
```

- directory permission 은 해당 볼륨을 사용할 user 에게만 주어도 된다.



### 10.2 volume mount

본 문서 에서는 편의상 `testuser` 로 로그인 한 것을 기준으로 설명 한다.

```
veracrypt /home/testuser/temp/example.hc /var/vctemp
```

- 위 명령을 입력 하면 password 를 입력 하라고 한다.



```
Enter password for /home/testuser/temp/example.hc:
```

- 위 에서 입력 했던 password `1111` 을 입력 한다.



```
Enter PIM for /home/testuser/temp/example.hc:
```

- PIM 을 입력 하라고 한다. 그냥 <enter>



```
Enter keyfile [none]:
```

- key 파일을 입력 하라고 한다. 그냥 <enter>
- 일반적인 경우 사용 하지 말라고 한다. 그렇지만 강력한 보호를 원하거나 무차별 대입 공격 으로 부터 방어 하기 위해서는 key 파일을 사용 할 수 있고 Key 파일을 최대 크기는 1M 까지만 유효 하다고 한다.
- 사용자가 임의의 파일을 Key 파일로 사용 해도 되고 내장 키 파일 생성기를 사용하여 키 파일을 생성 할 수 있다고 한다.



```
Protect hidden volume (if any)? (y=Yes/n=No) [No]:
```

- hidden volume 으로 보호할 것인지 선택 한다. 그냥 [No] 로 되어 있으니 <enter>
- 이때 다음과 같은 오류가 발생 하면 dmsetup 를 설치해 준다.

```
Error: No such file or directory:
dmsetup

VeraCrypt::Process::Execute:108
```

설치 명령: `sudo apt-get install dmsetup` 그리고 `volume mount` 다시 시작



```
Enter your user password or administrator password:
```

- root 계정의 password 를 입력 한다.



위 과정을 거처 암호화된 Volume 이 정상 적으로 mount 되었다. 확인은 `ls -al /var/vctemp`

```
drwx------  2 testuser testuser 16384 Dec 31  1969 .
drwxr-xr-x 12 root     root      4096 Dec 11 17:09 ..
```

아무것도 없는 빈 directory 가 나타난다.



해당 경로로 이동해 할당된 용량을 확인 해 본다.

```
cd /var/vctemp

df -k .

[결과]
Filesystem             1K-blocks  Used Available Use% Mounted on
/dev/mapper/veracrypt1      9928     0      9928   0% /var/vctemp
```



## 11. 암호화된 Volume 테스트 및 unmount

암호화된 Volume 에 파일을 생성(복사) 해 보고 해제, 다시 마운트 후 확인 하는 과정을 거쳐 veracrypt 를 테스트 해 본다.



### 11.1 임의의 파일 복사

```
cp ~/VeraCrypt/src/Main/CommandLineInterface.cpp /var/vctemp

ls -al /var/vctemp

[결과]
total 45
drwx------  2 testuser testuser 16384 Dec 11 18:31 .
drwxr-xr-x 12 root     root      4096 Dec 11 17:09 ..
-rwx------  1 testuser testuser 24825 Dec 11 18:31 CommandLineInterface.cpp
```



### 11.2 Unmount 및 확인


```
veracrypt -d /home/testuser/temp/example.hc
```

위 명령을 실행 하면 다음과 같이 root password 입력을 요청 한다.



```
Enter your user password or administrator password:
```

- root password 를 입력 한다.

- 이때 해당 directory 에 user 가 접근해 있으면 다음 오류가 발생 한다.

```
Error: umount: /var/vctemp: target is busy
        (In some cases useful info about processes that
         use the device is found by lsof(8) or fuser(1).)
```

- 접근해 있는 user 가 다른곳 으로 이동 하고 동일한 명령을 내리면 정상 unmount 된다.

확인

`ls -al /var/vctemp`

```
[결과]
total 8
drwxrwxrwx  2 root root 4096 Dec 11 17:09 .
drwxr-xr-x 12 root root 4096 Dec 11 17:09 ..
```



### 11.3 다시 마운트 해 보고 파일이 있는지 확인 한다.

```
veracrypt /home/testuser/temp/example.hc /var/vctemp
...
나머지는 10.2 volume mount 와 동일 하게 진행
```



### 11.4 전에 복사했던 파일이 남아 있는지 확인 한다.

```
ls -al /var/vctemp

[결과]
total 45
drwx------  2 testuser testuser 16384 Dec 31  1969 .
drwxr-xr-x 12 root     root      4096 Dec 11 17:09 ..
-rwx------  1 testuser testuser 24825 Dec 11 18:31 CommandLineInterface.cpp
```

정상적으로 남아 있다. 



### - 끝 -

