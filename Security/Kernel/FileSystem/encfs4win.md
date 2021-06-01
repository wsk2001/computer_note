# encfs4win

개요: 본 문서는 파일시스템 암호화 솔루션인 encfs4win 을 분석 하여 dokany 에서 filesize 조회시 암호화 header size 를 반영하여 file size 를 조회 하기 위한 용도로 작성 되었다.

source 저장소는 https://github.com/wsk2001/encfs4win 으로 https://github.com/jetwhiz/encfs4win 를 포크 하였다.



## encfs4win - Windows port of EncFS



### About

EncFS4win은 원래 encfs4win 프로젝트의 부활이지만 EncFS 프로젝트에 대한 최근 변경 사항으로 업데이트되었습니다. 원래 encfs4win은 EncFS v1.7.4를 기반으로하여 보안 문제가있는 것으로 나타났습니다. EncFS에 대한 업데이트는 최근 이러한 많은 문제를 해결하기 위해 수행되었으며 이 프로젝트의 목표는 이러한 현대화를 Windows로 이식하는 것입니다.

EncFS는 가상 EncFS 파일 시스템에 대한 모든 요청을 원시 파일 시스템의 동등한 암호화 작업으로 변환하여 개별 파일을 암호화합니다.

Encfs4win 용 GUI는 encfsw를 통해 사용할 수 있으며, 암호화 된 파티션을 더 쉽게 마운트 및 마운트 해제 할 수있는 트레이 아이콘을 제공합니다!

EncFS는 파일 시스템 인터페이스 용 Dokan 라이브러리를 사용하여 사용자 공간에 암호화 된 파일 시스템을 제공합니다. EncFS는 LGPL에 따라 라이센스가 부여 된 오픈 소스 소프트웨어입니다.

자세한 내용은 다음을 참조하십시오.

- 우수한 [encfs 맨 페이지](https://github.com/wsk2001/encfs4win/blob/master/encfs/encfs.pod)
- [DESIGN.md](https://github.com/wsk2001/encfs4win/blob/master/DESIGN.md)의 기술 개요

**NOTE:** 파일/폴더를 찾을 수없는 문제가 발생하는 대소 문자 구분 문제를 방지하려면 드라이브 (예 : 'X :')를 plain_dir로 사용해야합니다!



### Installing

설치는 몇 번의 클릭만으로 간단합니다! [encfs4win/releases](https://github.com/jetwhiz/encfs4win/releases) (예 : encfs4win-installer.exe)에서 최신 encfs4win 설치 프로그램을 다운로드하고 실행하기 만하면됩니다.

설치 프로그램에는 encfs 실행 파일, OpenSSL 라이브러리 및 필요한 모든 MS Visual C ++ 라이브러리를 포함하여 Windows에서 encfs를 실행하는 데 필요한 모든 것이 포함되어 있습니다. Dokan 라이브러리도 자동으로 설치됩니다 (아직 설치되지 않은 경우).

Encfs4win은 이제 [Chocolatey](https://github.com/chocolatey/choco) 패키지 관리자를 통해 사용할 수 있습니다. choco를 통한 설치 지침은 https://chocolatey.org/packages/encfs4win을 참조하십시오.



### Building

Encfs4win에는 몇 가지 종속성이 있습니다.

- [Visual Studio 2015](https://www.visualstudio.com/en-us/downloads/download-visual-studio-vs.aspx) - 프로젝트 빌드 용 (Visual C ++ 및 Windows SDK 10 포함).

- [Dokan library](https://github.com/dokan-dev/dokany) - 소프트웨어의 FUSE 부분을 처리합니다. 레거시 (예 : v0.7.4) 또는 최신 (v1.0)을 사용할 수 있습니다.

- [TinyXML2 library](https://github.com/leethomason/tinyxml2) - encfs에 대한 구성 파일을 읽고 씁니다.

- [Easylogging++](https://github.com/easylogging/easyloggingpp) - 로깅 기능을 제공합니다.

- [OpenSSL library](https://github.com/openssl/openssl) -  항상 최신 버전을 사용하십시오. OpenSSL을 빌드하려면 Perl이 설치되어 있어야합니다!

- <span style="color:red">Windows 의 경우 **Strawberry Perl** 이 설치되어 있오야 OpenSSL 이 제대로 Build 됨. lilk: http://strawberryperl.com/ </span>
- ActivePerl 이 설치 되어 있는 경우 **Strawberry Perl** 로 교체 해야함
  - ActivePerl 의 version 이 낮아서 오류가 발생 함.
  - OpenSSL 을 컴파일 하려면 perl version 이 5.9.5 이상 이어야 함.



### 자동화 된 버전

Encfs4win은 이제 루트 디렉토리에있는 'build.bat'라는 완전 자동화 된 빌드 도구와 함께 제공됩니다. 이 도구는 최종적으로 encfs를 빌드하기 전에 Dokan, TinyXML2, Easylogging ++ 및 OpenSSL을 자동으로 다운로드, 빌드 및 설치합니다. 빌드가 완료된 후 encfs\Release 폴더에서 'encfs.exe'및 'encfsctl.exe'를 찾습니다.

자동화 도구는 또한 이러한 필수 구성 요소가 이미 설치되어 있는지 확인합니다 (DOKAN_ROOT 및 OPENSSL_ROOT 환경 변수를 검색하여). 발견되면 설치된 버전을 사용하고 해당 전제 조건을 건너 뜁니다.

이 도구에는 '--beta'인수 (MSYS 필요)를 제공하는 경우 libgcrypt (및 libgpg-error)를 빌드하는 기능도 있습니다. 그러나 Encfs는 여전히 libgcrypt 라이브러리에 대한 지원을 제공하는 중입니다.



### 수동 버전

전제 조건의 일부 또는 전부를 직접 처리하도록 선택할 수도 있습니다. 위의 종속성을 설치하고 빌드 한 후 encfs4win 솔루션 (encfs/encfs.sln)을 열고 솔루션을 빌드하면됩니다. Dokan 및 OpenSSL 설치를 각각 가리키는 환경 변수 DOKAN_ROOT 및 OPENSSL_ROOT가 있어야합니다 (그렇지 않으면 설치를 가리 키도록 솔루션을 수정해야합니다).

그러면 encfs.exe 및 encfsctl.exe 바이너리가 빌드되고 encfs\Release 디렉토리에 배치됩니다.



### Environment

Encfs4win은 다음에서 작동합니다.

- Windows 10
- Windows 8 and 8.1
- Windows Server 2012
- Windows 7



### Status

변경 사항이 업스트림에 올 때 EncFS 프로젝트로 이 업데이트를 유지하려고 노력할 것입니다.

EncFS4win은 Dokan의 원래 릴리스 (v0.7.x)와 최신 Dokan (v1.x.x)에 대해 성공적으로 빌드되었습니다.



### Credits

이 프로젝트를 가능하게 한 토대를 마련해 주신 [vgough/encfs](https://github.com/vgough/encfs) 및 [freddy77/encfs4win](https://github.com/freddy77/encfs4win) 에게 특별히 감사드립니다!

