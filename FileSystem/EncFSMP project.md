# EncFSMP project

출처: <https://github.com/rhiestan/EncFSMP>

이것은 Windows 및 Mac OS에서 encfs 폴더를 마운트하는 도구 인 EncFSMP에 대한 git 프로젝트입니다.



## How to compile

이 가이드는 Visual Studio 2015 Community Edition을 사용하여 EncFSMP를 컴파일하는 방법을 안내합니다. 모든 도구와 라이브러리는 인터넷에서 무료로 사용할 수 있습니다. 이 단계를 완료하려면 C++, CMake 및 Visual Studio에 대한 경험이 필요합니다. 불행히도 제한된 여유 시간으로 인해 EncFSMP를 구축 할 수있는 지원을 제공 할 수 없습니다.

- 내 프로젝트 '빌드 스크립트'([링크](https://github.com/rhiestan/build-scripts))로 이동하여 최신 릴리스를 다운로드하십시오.
  - <https://github.com/rhiestan/build-scripts>
- 이 프로젝트의 README에있는 지침에 따라 zlib, jpeg, png, tiff, bzip2, boost, openssl, wxwidgets, gtest (이 순서대로)를 컴파일하십시오. 다른 라이브러리는 EncFSMP에 필요하지 않습니다.
- [PismoTech 홈페이지](http://pismotec.com/download/)에서 최신 PFM 개발자 키트를 다운로드하고 압축을 풉니다.
  - <https://pismotec.com/download/>
- EncFSMP의 최신 소스 릴리스 다운로드 및 추출
- build_msvc2015_x64_sb_template.bat 파일을 build_msvc2015_x64_sb.bat로 복사하고 SB_SCRIPT_PATH 경로를 빌드 스크립트의 압축을 푼 경로로 조정하고 PFM_ROOT 경로를 PFM SDK를 추출한 경로의 include 폴더로 조정합니다.
- 배치 파일을 두 번 클릭하여 실행하면 여러 파일이 포함 된 새 폴더가 만들어집니다. EncFSMP.sln을 두 번 클릭하십시오.



## Pull requests

다음 요구 사항을 충족하는 한 Pull 요청을 수락합니다.

- 테스트 된 높은 코드 품질
- 멀티 플랫폼 가능 (예 : Windows API 없음)
- 나머지 코드와 비슷한 코드 스타일

develop branch에 대해서만 요청을하십시오.

다음과 같은 영역에서이 순서로 도움이 필요합니다.

- Bug fixes (see tickets at SourceForge, [link](https://sourceforge.net/p/encfsmp/tickets/))
- Test cases (see EncFSMPTestApp.cpp)
- New features



---

<span style="color:blue">**이상은 Project Page 에 있는 내용 입니다. 아래는 실제 테스트 과정과 문제점이 발견 될 경우 해결 과정을 적을 예정 입니다.**</span>

---



## EncFS MP 설치본 Download 및 설치

다음 주소 에서 <https://encfsmp.sourceforge.io/download.html>  다음 (Beta: [EncFSMP 0.99.1 Setup for Windows](http://sourceforge.net/projects/encfsmp/files/EncFSMP/0.99.1/EncFSMP_0.99.1_Setup.exe/download)) 파일을 Download 한다.







