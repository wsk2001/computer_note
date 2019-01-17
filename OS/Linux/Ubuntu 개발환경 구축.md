## Ubuntu 개발 환경구축

본 문서는 자주 설치하는 LinuxServer 인 Ubuntu 에 필요한 개발 환경을 구축 하기 위한 기본 설치서 이다. 필요에 따라 선택적 으로 Package 를 설치 하면 된다.  이 문서는 필요에 의해 계속 추가 및 수정이 이루어질 것이다.  (node.js, go, ruby, php, etc...)

 

### 1. update & upgrade

```bash
sudo apt update

sudo apt upgrade
```

<br/>

### 2. SSH Server 설치

```bash
dpkg -l | grep openssh
sudo apt install -y openssh-server
dpkg -l | grep openssh
sudo service ssh start
```
- 이제 ssh 로 Linux 에 접속 할 수 있다.

<br/>

### 3. C/C++ 개발 환경 구축

```bash
sudo apt install -y build-essential

sudo apt install cmake
```

<br/>

### 4. Assembler 개발 환경 구축

```bash
sudo apt install -y yasm
```

<br/>

### 5. git tool 설치

```bash
sudo apt install -y git
```

<br/>

### 6. pkg-config 설치

```bash
sudo apt install -y pkg-config
```

<br/>

### 7. python3-pip 및 python-setuptool 설치

```bash
sudo apt install -y python-minimal

sudo apt install -y python-pip

sudo apt install -y python3-pip

pip install setuptools
```

<br/>

### 8. ninja 설치

```bash
git clone https://github.com/ninja-build/ninja.git

cd ninja

# 오류시 vi ./configure.py 하고 첫줄에 있는 python 을 python3 로 변경 한다.

./configure.py --bootstrap

chmod 755 ninja

sudo cp ninja /usr/bin/

sudo chown root:root /usr/bin/ninja
```

<br/>

### 9. meson 설치

```bash
git clone https://github.com/mesonbuild/meson.git

cd  meson

sudo python3 setup.py install
```

<br/>

### 10. java 설치

```bash
sudo apt install -y openjdk-8-jdk-headless

[확인]

java -version
javac -version
```



### 11. CURL 설치

```bash
sudo apt install -y curl
```



### 12. 한글 설정

우분투 서버를 사용하고 있을 경우 X 시스템과 폰트관련 패키지가 설치되어 있지 않다. 



#### 12.1 Font 관련 Package 설치

```bash
$ sudo apt-get install libfontenc1 libxfont1 xfonts-encodings xfonts-utils xfonts-base xfonts-75dpi
```



#### 12.2 한글 언어팩 설치

```bash
$ sudo apt install language-pack-ko
$ sudo locale-gen ko_KR.UTF-8
```



#### 12.3 locale 설정

```bash
sudo vi /etc/default/locale
```

```
LANG="en_US.UTF-8"
LANG="ko_KR.UTF-8"
```



#### 12.4 한글 환경 설정

```bash
vi /etc/environment 
```

아래 2 줄 추가

```
LANG="ko_KR.UTF8"
LANGUAGE="ko_KR:ko:en_GB:en"
```



#### 12.5 폰트 추가

은폰트 - unfonts 추가

```bash
apt install -y fonts-unfonts-core fonts-unfonts-extra 
```



백묵폰트 - baekmuk 추가

```bash
apt install -y fonts-baekmuk
```



나눔폰트 - nanum 추가

```bash
apt install -y fonts-nanum fonts-nanum-coding fonts-nanum-extra
```



#### 12.6 OS 재 기동

```bash
sync
sync
sync

reboot
```



...ing
