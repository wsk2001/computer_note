# makeself 사용법

출처: https://automatedprogrammer.sh/2018/09/self-extracting-with-makeself/



혹시 새로운 리눅스 배포판을 설치하고 설정 파일을 홈 디렉토리로 scping하는데 문제가 있습니까? 윈도우/데스크탑 매니저 configs, .vimrc, .bashrc / .zshrc 등.

이 문제는 여러 가상 머신에, 라즈베리파이 등을 설치하는 것과 복합됩니다. 운좋게도이 프로세스를 훨씬 쉽게 만들고 시스템을 가동시킬 수있는 매우 유용한 도구가 있습니다!

필자는 최근에 일하는 동안 패치 파일을 설치하는 쉬운 방법을 찾고있는 동안 makeself라는 도구를 발견했습니다. makeself는 자동 압축 풀기 아카이브를 만드는 쉬운 방법입니다. VirtualBox, Loki Software 및 Google 어스 용 Linux 설치 프로그램과 같은 프로젝트는 모두 makeself를 사용하여 소프트웨어를 설치합니다. 이 튜토리얼에서는 makeself와 약간의 쉘 스크립팅을 사용하여 설정 파일 용 설치 프로그램을 만드는 방법을 설명합니다. 이 방법을 사용하면 설정 파일을 홈 디렉토리에 추출하는 파일 하나만 있으면됩니다. 그리고 무엇보다도, 이것은 매우 쉽게 사용자가 정의 할 수 있습니다. 이 튜토리얼을 시작점으로 사용하고 원하는 설치 스크립트를 빌드하십시오!



## Setup

우리가해야 할 첫 번째 일은 makeself를 설치하는 것입니다. Ubuntu에서 사용할 수있는 패키지와 AUR에서 Arch를 구할 수 있습니다. Makeself 패키지를 찾을 수 없으면 https://github.com/megastep/makeself의 GitHub에서 가져올 수 있습니다. 상황에 따라 다음과 같은 옵션이 있습니다.

```bash
# Ubuntu package
sudo apt-get install makeself

# AUR
pacaur -S makeself

# Pull from GitHub. git required.
git clone https://github.com/megastep/makeself
```



## Collect config files

다음으로 설정 파일을 복사 할 임시 디렉토리를 생성 해 보겠습니다. 이 디렉토리는 파일을 구성하는 방식으로 현재 $HOME 디렉토리를 모방합니다. 이것은 다음 Linux 시스템에서이 파일들을 추출 할 때 중요합니다.

``` bash
# Go to your home directory
cd ~/
 
# Create a directory for the config files. 
mkdir config_files
 
# Copy your config files to the config_files/ directory.
cp .vimrc .bashrc .zshrc .xinitrc .toprc .tmux.conf config_files/
 
# If you have files in .config/, like an i3 config for example,
# make the directories before copying over the files to keep
# the same structure. The -p will create all parent directories
# if needed.
mkdir -p config_files/.config/i3
cp .config/i3/config config_files/.config/i3
 
# ... same for if you have a .vim directory. Use -r to recursively
# copy all files.
mkdir -p config_files/.vim/bundle
cp -r .vim/bundle/Vundle.vim config_files/.vim/bundle/
```

이 시점에서 모든 설정 파일은 config_files/ . 에 있어야합니다.



## Create the “payload”

Config 파일을 'tar'할 것입니다. 툴 타르에 익숙하지 않다면 .zip 파일과 같은 아카이브 유틸리티입니다. 이 아카이브는 다음 시스템으로 추출 할 페이로드가 될 것입니다. 이 페이로드는 홈 디렉토리 맨 위에서 추출되므로 홈 디렉토리와 유사하게 구조화되어야합니다.

```bash
tar -cvf payload.tar -C config_files/ .
```



`-cvf` 이렇게하면 아카이브가 **생성되고** **자세한 출력**과 아카이브의 원하는 **파일 이름**이 인쇄됩니다.

`-C` 이것은 현재 디렉토리를 config_files/ 로 지정 합니다.

`.` 이 마침표는 실수가 아닙니다.  config_files/ 디렉토리로 디렉토리를 변경하면 해당 디렉토리의 모든 것을 아카이브합니다. . 타르에게 '이 디렉토리'라고 알려줍니다.

이후의 단계에서 이 모든 것을 패키징 할 때 makeself가 압축을 처리하므로 압축 플래그를 추가하지 않습니다.



## Create staging directory

다음으로, 우리는 페이로드와 설치 스크립트를 저장할 디렉토리를 생성 할 것입니다. 이것은 makeself가 압축하여 실행 파일로 전달할 디렉토리입니다.

```bash
# Be sure to be in your home directory
mkdir config_stage
 
# Copy the payload to this directory
cp payload.tar config_stage/
 
# Create an empty file that will be our install script
touch config_stage/install.sh
 
# And modify the file to be executable
chmod +x config_stage/install.sh
```



## Write install script

이 설치 스크립트는 대상의 홈 디렉토리에 payload.tar를 추출합니다. 이것은 매우 간단한 한 줄짜리 스크립트 일 것입니다. 유스 케이스에 따라이 스크립트는 필요에 맞게 수정할 수 있습니다. 또한 bash 스크립트 일 필요는 없습니다. 파이썬 스크립트 또는 rust  바이너리 일 수도 있습니다. makeself는 실행 파일만큼 관심이없고 대상 시스템에서 실행될 수 있습니다. 하지만 간단히하기 위해 필자는 bash로 작성하려고합니다.

**config_stage/install.sh**

```bash
#!/bin/bash
tar -xvf ./payload.tar -C "$HOME" &&
echo "Config files extracted successfully!"
```

이 스테이징 디렉토리 안에있는 것처럼 스크립트가 작성됩니다.



## Run makeself

이제 config_stage/ 디렉토리에 대해 makeelf를 실행합니다. GitHub 버전을 가져온 경우, git 디렉토리에서 makeself.sh에 대한 경로로 makeself를 변경하십시오 (예 : makeself/makeself.sh)

```bash
makeself --sha256 ./config_stage  my-configs-installer.run "My config files installer" ./install.sh
```

다음은 명령의 분석입니다.

`--sha256` 이것은 무결성 검사에 sha256 해시를 추가하도록 makeself에게 알려줍니다. 무결성 검사는 파일을 손상시키지 않기 때문에 매우 중요합니다. 다른 시스템으로 전송하는 데 문제가 있고 파일의 일부가 전송되지 않으면 makeself가 확인하고 알려주도록합니다.

`./config_stage` / config_stage는 시스템의 임시 디렉토리에 추출 될 아카이브 용 디렉토리입니다.

`my-configs-installer.run` 은 makeself가 작성할 실행 파일의 이름입니다. 원하는 이름이 될 수 있습니다.

`My config files installer` 이것은 실행 파일의 레이블입니다. 그것은 또한 당신이 원하는 무엇이든 될 수 있습니다.

`./install.sh` 이것은 config_stage/가 임시 디렉토리로 추출 된 후에 실행되도록 작성한 설치 스크립트입니다. 마치 우리가 config_stage/ 디렉토리에있는 것처럼 ./으로 썼다.

출력은 이와 비슷해야하며 새 파일 my-configs-installer.run이 홈 디렉토리에 있습니다!

```bash
Header is 588 lines long

About to compress 4460 KB of data...
Adding files to archive named "my-configs-installer.run"...
./
./payload.tar
./install.sh
CRC: 2272251193
SHA256: 710b5c0e2b9c6b3b6bcd42274427ac47487f98afab088626eb472c72bd37e778
MD5: 03c9bde4560f41d7de3cd0237fd0d38c

Self-extractable archive "my-configs-installer.run" successfully created.
```



## Executing on target system

대상 시스템에서 (./my-configs-installer.run을 입력하여) 실행하면 config_stage/를 /tmp/의 임시 디렉토리로 추출합니다. 그런 다음 payload.tar를 홈 디렉토리로 추출 할 ./install.sh를 실행합니다. 스크립트가 완료되면 임시 디렉토리의 모든 항목이 삭제됩니다. 

이 실행 파일은 설정 파일을 설치하려는 시스템으로 보낼 수 있습니다. 그러나 위대한 일은 그 잠재력은 끝이 없다는 것입니다. 새 시스템을 설정할 때 반복적으로 수행하는 다른 작업이 있으면 install.sh 스크립트에 추가 할 수 있습니다! 더 이상 손으로 모든 것을 할 필요가 없습니다!

makeself에는 더 재미있는 기능이 있습니다. 자세한 내용을 보려면 makeself --help를 실행하면됩니다.



## Other helpful commands

실행 파일에는 몇 가지 유용한 명령이 자동으로 내장되어 있습니다. 다음은 몇 가지 명령 예제입니다.

### –help

```bash
Makeself version 2.4.0
 1) Getting help or info about ./my-configs-installer.run :
  ./my-configs-installer.run --help   Print this message
  ./my-configs-installer.run --info   Print embedded info : title, default target directory, embedded script ...
  ./my-configs-installer.run --lsm    Print embedded lsm entry (or no LSM)
  ./my-configs-installer.run --list   Print the list of files in the archive
  ./my-configs-installer.run --check  Checks integrity of the archive

 2) Running ./my-configs-installer.run :
  ./my-configs-installer.run [options] [--] [additional arguments to embedded script]
  with following options (in that order)
  --confirm             Ask before running embedded script
  --quiet       Do not print anything except error messages
  --accept              Accept the license
  --noexec              Do not run embedded script
  --keep                Do not erase target directory after running
            the embedded script
  --noprogress          Do not show the progress during the decompression
  --nox11               Do not spawn an xterm
  --nochown             Do not give the extracted files to the current user
  --nodiskspace         Do not check for available disk space
  --target dir          Extract directly to a target directory (absolute or relative)
                        This directory may undergo recursive chown (see --nochown).
  --tar arg1 [arg2 ...] Access the contents of the archive through the tar command
  --                    Following arguments will be passed to the embedded script
```



### –check

아카이브 무결성 확인 중 ... 100 % SHA256 체크섬은 정상입니다. 100 % MD5 체크섬은 정상입니다. 문제 없다.



### –info

```bash
Identification: My config files installer
Target directory: config_stage
Uncompressed size: 4460 KB
Compression: gzip
Date of packaging: Sun Sep 2 22:25:03 PDT 2018
Built with Makeself version 2.4.0 on linux-gnu
Build command was: /usr/bin/makeself 
    "--sha256" 
    "./config_stage" 
    "my-configs-installer.run" 
    "My configs installer" 
    "./install.sh"
Script run after extraction:
     ./install.sh
config_stage will be removed after extraction
```





# makeself 사용법

Make self-extractable archives on Unix

출처: https://github.com/megastep/makeself

Makeself.sh는 디렉토리에서 자체 추출 압축 tar 아카이브를 생성하는 작은 쉘 스크립트입니다. 결과 파일은 셸 스크립트 (많은 파일에 .run 접미사가 있음)로 나타나며 현재 실행될 수 있습니다. 그런 다음 아카이브가 임시 디렉토리에 압축이 풀리고 임의의 임의의 명령 (예 : 설치 스크립트)이 실행됩니다. 이것은 Windows 환경에서 WinZip Self-Extractor로 생성 된 아카이브와 매우 유사합니다. 또한 자체 보관에는 무결성 자체 유효성 검사 (CRC 및 / 또는 MD5 / SHA256 체크섬)에 대한 체크섬이 포함됩니다.

Makeself.sh 스크립트 자체는 파일 디렉토리에서 아카이브를 작성하는 데에만 사용됩니다. 생성 된 아카이브는 압축 된 (gzip, bzip2 또는 compress 사용) TAR 아카이브이며 시작 부분에 작은 쉘 스크립트 스텁이 있습니다. 이 작은 스텁은 파일 추출, 임베디드 된 명령 실행 및 완료시 임시 파일 제거의 모든 단계를 수행합니다. 그러한 아카이브에 포함 된 소프트웨어를 설치하기 위해 사용자가해야하는 것은 아카이브 (예 : sh nice-software.run)를 '실행'하는 것입니다. 저는 '.run'(Loki Software가 출시 한 일부 Makeself 아카이브에서 소개 한)이나 '.sh'접미사를 사용하여 사용자를 혼동하지 않도록하여 해당 쉘 스크립트가 실제로 쉘 스크립트임을 알 수 있도록하는 것이 좋습니다. 많은 바이너리 데이터가 첨부되어 있습니다!).

이 스크립트의 코드를 가능한 한 이식성있게 유지하려고합니다. 즉, bash 특정 기능에 의존하지 않고 작동하는 UNIX 호환 시스템에 설치된 명령 만 호출합니다. 이 스크립트와 생성 된 아카이브는 호환 가능한 모든 Bourne 셸과 함께 유닉스 환경에서 실행되어야하며 물론 압축 프로그램을 사용할 수 있어야합니다.

버전 2.1부터 Makeself는 다음 플랫폼에서 다시 작성되고 테스트되었습니다.

- Linux (all distributions)
- Sun Solaris (8 and above)
- HP-UX (tested on 11.0 and 11i on HPPA RISC)
- SCO OpenUnix and OpenServer
- IBM AIX 5.1L
- macOS (Darwin)
- SGI IRIX 6.5
- FreeBSD
- UnicOS / Cray
- Cygwin (Windows)

다른 시스템에서 Makeself 및 / 또는 아카이브를 사용하여 성공적으로 실행하면 알려주십시오!

Makeself를 사용하여 공개적으로 사용 가능한 아카이브의 예는 다음과 같습니다.

- 게임 패치 및 설치자를위한 [ID 소프트웨어](http://www.idsoftware.com/) 게임은 Linux 용 Quake 3 또는 Return To Castle Wolfenstein과 같습니다.
- [Loki Software](http://www.lokigames.com/products/myth2/updates.php3) 에서 인기있는 게임의 Linux 버전 용으로 출시 한 모든 게임 패치.
- Linux 용 [nVidia 드라이버](http://www.nvidia.com/)
- [Google 어스](http://earth.google.com/) Linux 버전 용 설치 프로그램
- Linux 용 [VirtualBox](http://www.virtualbox.org/) 설치 프로그램
- The [Makeself](http://makeself.io/) distribution itself.



#### 아파치 사용자를위한 중요한 노트

기본적으로 대부분의 웹 서버는 Makeself 아카이브가 일반 텍스트 파일이므로 웹 브라우저에 텍스트로 표시 될 수 있다고 생각합니다. 이것을 막는 올바른 방법은 다음과 같이 (httpd.conf에서)이 파일 형식에 대한 MIME 유형을 추가하는 것입니다.

```bash
AddType application/x-makeself .run
```



#### 특정 GNU / Linux 배포판에 대한 중요 사항

V2.1.2 이전에 Makeself로 만든 아카이브는 GNU 형식에서 점진적으로 쓸모없는 헤드 및 테일 유닉스 명령에 대한 이전 구문을 사용하고있었습니다. 따라서 이러한 압축 파일의 압축을 풀 때 문제가 발생할 수 있습니다. 이 문제를 해결하려면 환경 변수 $_POSIX2_VERSION을 설정하시기 바랍니다. 예전 구문 (예 : `export _POSIX2_VERSION=199209`)



## Usage

Makeself의 구문은 다음과 같습니다.

```bash
makeself.sh [args] archive_dir file_name label startup_script [script_args]
```



#### Parameters

##### args

- **--version**: stdout에 버전 번호를 인쇄 한 다음 즉시 종료합니다.

- **--gzip**: gzip을 압축 용으로 사용하십시오 (Linux와 같이 gzip이 일반적으로 사용 가능한 플랫폼의 기본값)

- **--bzip2**: 더 나은 압축을 위해서는 gzip 대신 bzip2를 사용하십시오. bzip2 명령은 명령 경로에서 사용 가능해야합니다. 아카이브 접두어를 '.bz2.run'과 같이 설정하여 잠재적 사용자가 압축을 풀 때 bzip2가 필요하다는 것을 알 수 있도록하는 것이 좋습니다.

- **--pbzip2**: 여러 CPU가있는 머신에서 더 빠르고 더 압축하려면 gzip 대신 pbzip2를 사용하십시오. pbzip2 명령은 명령 경로에서 사용할 수 있어야합니다. 아카이브 접두어를 '.bz2.run'과 같이 설정하여 잠재적 사용자가 압축을 풀 때 bzip2가 필요하다는 것을 알 수 있도록하는 것이 좋습니다.

- **--xz**: 더 나은 압축을 위해서는 gzip 대신 xz를 사용하십시오. xz 명령은 명령 경로에서 사용 가능해야합니다. 아카이브 접두어를 아카이브에 대해 '.xz.run'과 같이 설정하여 잠재적 사용자가 압축을 풀 때 xz가 필요하다는 것을 알 수 있도록하는 것이 좋습니다.

- **--lzo**: 더 나은 압축을 위해서는 gzip 대신 lzop을 사용하십시오. lzop 명령은 명령 경로에서 사용 가능해야합니다. 아카이브 접두어를 아카이브에 대해 .lzo.run과 같은 것으로 설정하여 잠재적 인 사용자가 추출하기 위해 lzop을 필요로한다는 것을 알 수 있도록하는 것이 좋습니다.

- **--lz4**: 더 나은 압축을 위해서는 gzip 대신 lz4를 사용하십시오. lz4 명령은 명령 경로에서 사용 가능해야합니다. 아카이브 접두어를 아카이브 용으로 '.lz4.run'과 같이 설정하여 잠재 사용자가 압축을 풀려면 lz4가 필요하다는 것을 알 수 있도록하는 것이 좋습니다.

- **--pigz**: 압축 용 pigz를 사용하십시오.

- **--base64**: 압축하지 않고 Base64 형식으로 압축 파일을 ASCII로 인코딩하십시오 (base64 명령 필요).

- **--gpg-encrypt**: gpg -ac -z $COMPRESS_LEVEL을 사용하여 아카이브를 암호화하십시오. 암호화 할 것인지 묻는 메시지가 나타납니다. 잠재적 인 사용자가 gpg를 설치했다고 가정합니다.

- **--ssl-encrypt**: openssl aes-256-cbc -a -alt를 사용하여 아카이브를 암호화하십시오. 암호화 할 것인지 묻는 메시지가 나타납니다. 잠재적 인 사용자가 OpenSSL 도구를 설치했다고 가정합니다.

- **--compress**: UNIX 압축 명령을 사용하여 데이터를 압축합니다. gzip을 사용할 수없는 모든 플랫폼에서는 이것이 기본값이어야합니다.

- **--nocomp**: 압축 파일을 압축하지 마십시오. 그러면 압축되지 않은 TAR 파일이됩니다.

- **--complevel**: gzip, bzip2, pbzip2, xz, lzo 또는 lz4의 압축 수준을 지정하십시오. (기본값은 9)

- **--notemp**: 생성 된 아카이브는 임시 디렉토리에 파일을 추출하지 않지만 현재 디렉토리에 작성된 새 디렉토리에 파일을 추출합니다. 자체적으로 추출 및 컴파일 할 수있는 소프트웨어 패키지를 배포하는 것이 더 좋습니다 (즉 임베디드 스크립트를 통해 컴파일을 시작합니다.)

- **--current**: 파일은 하위 디렉토리가 아닌 현재 디렉토리로 추출됩니다. 이 옵션은 위의 --notemp를 의미합니다.

- **--follow**:  아카이브 디렉토리 내부의 심볼릭 링크를 따르십시오. 즉 링크 자체 대신 가리키는 파일을 저장하십시오.

- **--append** *(new in 2.1.x)*: 새 아카이브를 만드는 대신 기존 아카이브에 데이터를 추가하십시오. 이 모드에서는 원본 아카이브의 설정이 다시 사용되므로 (압축 유형, 레이블, 포함 된 스크립트) 명령 줄에서 다시 지정할 필요가 없습니다

- **--header**: Makeself 2.0은 별도의 파일을 사용하여 makeself-header.sh라는 헤더 스텁을 저장합니다. 기본적으로, 그것이 makeself.sh와 동일한 위치에 저장되어 있다고 가정합니다. 이 옵션은 다른 곳에 저장되어있는 경우 실제 위치를 지정하는 데 사용할 수 있습니다.

- **--copy** : 추출시 아카이브는 먼저 임시 디렉토리로 자신을 추출합니다. 주 응용 프로그램은 설치 프로그램이 나중에 CD를 마운트 해제하고 새 CD를 삽입해야 할 때 CD의 Makeself 아카이브에 저장된 독립형 설치 프로그램을 허용하는 것입니다. 이렇게하면 여러 CD에 걸쳐있는 설치 프로그램의 '파일 시스템 사용 중'오류를 방지 할 수 있습니다.

- **--nox11**: X11에서 새로운 터미널의 자동 스폰을 비활성화하십시오.

- **--nowait**: 새 X11 터미널에서 실행될 때 스크립트 실행이 끝나면 사용자 프롬프트를 비활성화합니다.

- **--nomd5** and **--nocrc** : 아카이브에 대한 MD5 / CRC 체크섬 생성을 비활성화합니다. 무결성 검사가 필요하지 않으면 추출 프로세스 속도가 빨라집니다.

- **--sha256**  : 아카이브에 대한 SHA256 체크섬을 추가합니다. --nomd5도 사용되지 않는 한 MD5 / CRC 체크섬에 추가됩니다.

- **--lsm file** : 생성 된 아카이브에 임베드 될 makeself에 LSM 파일을 제공하십시오. LSM 파일은 쉽게 파싱 할 수있는 방식으로 소프트웨어 패키지를 설명합니다. LSM 항목은 나중에 아카이브에 --lsm 인수를 사용하여 검색 할 수 있습니다. LSM 파일의 예가 Makeself와 함께 제공됩니다.

- **--tar-extra opt** : tar 명령 행에 더 많은 옵션을 추가하십시오.

  예를 들어, GNU tar를 사용하여 패키지 아카이브 디렉토리에서 .git 디렉토리를 제외하려면 `makeself.sh --tar-extra '--exclude = .git'`을 사용할 수 있습니다.

- **--keep-umask** : 자동 압축 해제 아카이브를 실행할 때 겹쳐 쓰기보다는 umask를 쉘 기본값으로 설정하십시오.

- **--packaging-date date** : 제공된 문자열을 현재 날짜 대신 포장 날짜로 사용하십시오.

- **--license** : 라이센스 파일을 추가하십시오.

- **--nooverwrite** :  지정된 대상 디렉토리가 이미있는 경우 아카이브를 추출하지 마십시오.

- **--header file** :  헤더 스크립트 파일의 위치를 지정하십시오 (기본 makeself-header.sh).

- **--help-header file** : 아카이브의 --help 출력에 헤더를 추가하십시오.



#### archive_dir

archive_dir은 아카이브 할 파일이 들어있는 디렉토리의 이름입니다.



##### file_name

file_name은 만들 아카이브의 이름입니다.



##### label

label은 패키지를 설명하는 임의의 텍스트 문자열입니다. 파일을 추출하는 동안 표시됩니다.



##### startup_script

startup_script는 추출 된 파일의 디렉토리에서 실행될 명령입니다. 따라서이 디렉토리에 들어있는 프로그램을 실행하려면 명령 앞에 ./를 붙여야합니다. 예를 들어 ./program은 문제가되지 않습니다. script_args는이 명령에 대한 추가 인수입니다.



다음은 사용자가 /home/joe/mysoft에 패키지 이미지가 저장되어 있다고 가정하고 mysoft.sh라는 자동 압축 풀기 패키지를 생성하려고합니다.이 패키지는 처음에 /home에 저장된 'setup'스크립트를 실행합니다 /joe/ mysoft :

```bash
makeself.sh /home/joe/mysoft mysoft.sh "Joe's Nice Software Package" ./setup
```



다음은 Makeself 배포가 포함 된 makeself.run 아카이브를 작성한 방법입니다.

```bash
makeself.sh --notemp makeself makeself.run "Makeself by Stephane Peter" echo "Makeself has extracted itself"
```

makeself로 생성 된 아카이브는 다음 인수를 전달할 수 있습니다.

- **--keep** : 임베디드 스크립트가 실행 된 후 제거 될 임시 디렉토리에서 파일을 추출하지 못하게하십시오. 파일은 현재 작업 디렉토리에 압축이 풀리고 제거 할 때까지 여기에 남아 있습니다.
- **--verbose** : 삽입 된 명령을 실행하기 전에 사용자에게 메시지를 표시합니다.
- **--target dir** : Allows to extract the archive in an arbitrary place.
- **--nox11** : Do not spawn a X11 terminal.
- **--confirm** : Prompt the user for confirmation before running the embedded command.
- **--info** : Print out general information about the archive (does not extract).
- **--lsm** : Print out the LSM entry, if it is present.
- **--list** : List the files in the archive.
- **--check** : Check the archive for integrity using the embedded checksums. Does not extract the archive.
- **--nochown** : By default, a `chown -R` command is run on the target directory after extraction, so that all files belong to the current user. This is mostly needed if you are running as root, as tar will then try to recreate the initial user ownerships. You may disable this behavior with this flag.
- **--tar** : Run the tar command on the contents of the archive, using the following arguments as parameter for the command.
- **--noexec** : Do not run the embedded script after extraction.
- **--nodiskspace** : Do not check for available disk space before attempting to extract.





**배포 설치용 만들기** 

$>makeself.sh --help 해보면 

Usage: ./makeself.sh [params] archive_dir file_name label [startup_script] [args]

 <옵션>  <묶을디렉토리> <설치파일명> <설치파일 수행시노출되는 문장> <설치파일 수행후 실행할 명령어등> 이렇게됨



### 실제 사용 예

```bash
#!/bin/sh

INSTALL_FILE="install.sh"
SOURCE_DIR="/home/admin"
STARTUP_CMD="rm -rf $SOURCE_DIR;mkdir -p $SOURCE_DIR;mv * $SOURCE_DIR/" 
rm -f $INSTALL_FILE
makeself/makeself.sh --notemp $SOURCE_DIR $INSTALL_FILE "admin tool home:[$SOURCE_DIR]"  $STARTUP_CMD
```

