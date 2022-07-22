# VMware 가상 머신에 macOS 설치 과정

출처: https://comterman.tistory.com/2387



오늘 포스팅은 VMware를 이용해 윈도우 10운영체제에 맥OS를 설치하는 과정을 살펴볼 텐데, 모든 과정은 [How to Install macOS Catalina on VMware on Windows 10 – PC](https://www.wikigain.com/install-macos-catalina-on-vmware-on-windows/)라는 외국 기사를 그대로 따라 진행한 것임을 밝힙니다.

![img](.\images\995F75375F7DC3FD34.png)

아울러 VMware에 맥OS를 설치해 사용해보니 실사용은 어려운 수준이며, 맥OS의 분위기 파악 용도으로 적합하다는 점을 밝힙니다.



VMware에 맥OS를 설치하는 과정은

1. VMware 다운로드 및 설치
2. VMware에 가상 머신 생성 후 맥OS unlocker 패치
3. 맥OS ISO 이미지 다운로드 및 설치

등으로 나뉘는데 순서대로 과정을 살펴보겠습니다.

## VMware 다운로드 및 설치

VMware는 하나의 운영체제 안에서 다른 운영체제를 설치해 사용할 수 있도록 하는 가상 운영체제 프로그램입니다.



예전에 제 블로그에서 살펴본 hyper-V 역시 같은 목적으로 만들어진 프로그램인데, VMware는 기능이 훨씬 강력하지만 **한 달만 무료로 사용 가능**하며, 이후에는 약 200달러 정도에 구매해야 합니다.

저는 어차피 며칠만 사용할 터라, 한 달 평가판도 충분했고, [VMware 홈페이지에서 Pro 버전을 다운로드](https://www.vmware.com/au/products/workstation-pro/workstation-pro-evaluation.html) 했습니다.

![VMware 다운로드](.\images\99746E505F7DBADB2C.png)



다운로드한 VMware 설치 파일을 실행하면, **컴퓨터를 재부팅하고** 마이크로소프트 VC 패키지 설치 관리자를 다시 실행해야 한다는 경고 메시지가 뜹니다.

![VMware 설치](.\images\9972A9505F7DBADC2C.png)



[예]를 클릭해 컴퓨터를 재부팅한 뒤, 다시 VMware 설치 파일을 실행하면 설치 프로그램이 시작됩니다.

![VMware 설치 마법사](.\images\99B9D7505F7DBADD28.png)



VMware 설치 프로그램은 일반적인 윈도우용 설치 프로그램 처럼 단계별로 진행되는데, 저는 [사용자 경험 개선 프로그램에 참여] 항목의 체크만 해제하고 계속 Next 버튼을 클릭해 진행했습니다.

![VMware 설치 옵션](.\images\99DAA9505F7DBADE2A.png)



VMware 설치 완료 후 실행하면 라이선스 키를 입력하는 화면이 뜨는데, 저는 '**I want to try VMware Workstation 16 for 30 days**' 항목을 선택하고 [Continue] 버튼을 클릭해 30일 시험판으로 사용했습니다.

![VMware Workstation 16 시험판](.\images\99DA3D505F7DBADF2B.png)

## 맥OS 설치 용 VMware 패치

실행한 VMware는 일단 완전히 종료한 뒤(File-Exit), 맥OS용 패치를 적용해야 합니다.

[GitHub의 맥OS Unlocker 링크](https://github.com/paolo-projects/unlocker)에서 [Code]-[Download Zip] 버튼을 클릭해 압축 파일을 다운로드 한 뒤

![VMware 맥OS 패치 다운로드](.\images\997D59505F7DBADF25.png)



파일의 압축을 풀고 win-install.cmd 파일에서 마우스 오른쪽 버튼을 클릭한 뒤 [**관리자 권한으로 실행**]을 선택합니다.

![VMware 맥OS 패치 실행](.\images\997895505F7DBAE02C.png)



명령창이 열리면서 맥OS Unlocker 패치는 자동으로 적용되는데, **패치 적용 전 VMware 프로그램은 완전히 종료**해야 하는 점에 주의합니다.

![VMware 맥OS 패치 설치](.\images\995CA04F5F7DBAE12A.png)

아울러 **맥OS의 ISO 이미지도 다운로드**해 둡니다.

[맥OS 카트리나 10.15.5(19F101) 버전](http://www.mediafire.com/file/2mwxpooe0da6z3n/Catalina_10.15.5.iso/file)

## VMware에 가상 머신 생성

VMware의 패치가 완료된 후 VMware를 다시 실행하고, [**Create a New Virtual Machine**] 버튼을 클릭합니다.

![VMware 새 가상 머신 생성](.\images\9923BB4F5F7DBAE22B.png)



가상머신 마법사가 뜨면 기본 값인 Typical을 그대로 둔 상태로 [Next] 버튼을 클릭합니다.

![VMware 새 가상 머신 마법사](.\images\9949694F5F7DBAE32E.png)



게스트 운영체제 설치 항목에서는 '**I will install the operating system later**'(운영 체제는 나중에 설치하겠음)을 선택한 뒤 [Next] 버튼을 클릭합니다.

![VMware 새 가상 머신 운영체제 설정](.\images\99973F4F5F7DBAE42D.png)



게스트 운영체제 선택 메뉴에서 [**Apple Mac OS X**]를 클릭하고 버전은 macOS 10.15를 선택합니다.

![VMware 새 가상 머신 맥OS X](.\images\99243B4F5F7DBAE52B.png)



Virtual machine name 항목에 적당한 이름을 적고 [Next] 버튼을 클릭합니다.

![VMware 새 가상 머신 이름](.\images\990D3B4F5F7DBAE62F.png)



게스트 운영체제가 사용할 디스크 공간을 설정하는데, 저는 기본 값인 40GB를 그대로 두었고, [**Store virtual disk as a single file**] 항목을 선택한 뒤 [Next] 버튼을 클릭했습니다.

![VMware 새 가상 머신 용량](.\images\99E1C84F5F7DBAE72C.png)



가상 머신 기본 설정이 완료되었는데, 다시 [**Customize Hardware**] 버튼을 클릭합니다.

![VMware 새 가상 머신 사용자 설정](.\images\99D30E395F7DCF0D31.png)

Memory 항목에서 **가상 머신에 할당할 메모리 용량**을 설정합니다.

저는 24GB의 메모리 중 8GB를 설정했습니다.

![VMware 새 가상 머신 메모리 설정](.\images\999D8F485F7DBAE930.png)



Processors 항목은 2개의 프로세서, 프로세서 당 2개의 코어로 설정했습니다.

![VMware 새 가상 머신 CPU 설정](.\images\994647485F7DBAEA28.png)



New CD/DVD (SATA) 항목에서 **Use ISO Image file**을 선택하고 [Browse] 버튼을 클릭한 뒤 맥OS ISO 이미지 경로를 지정하고 [Close] 버튼을 클릭합니다.

![VMware ISO 이미지 경로 설정](.\images\994174485F7DBAEB2D.png)



작업 완료 후 [Finish] 버튼을 클릭해 새 가상머신 마법사를 종료합니다.

![VMware ISO 새 가상머신 설정 완료](.\images\99A690485F7DBAEB30.png)



윈도우 탐색기를 열고 **문서 폴더의 \Virtual Machines\가상머신 폴더** 를 연 뒤 vmx 확장자를 가진 파일을 노트패드 등의 텍스트 편집기로 열어줍니다.

![VMware 맥OS 추가 설정](.\images\999E08485F7DBAEC27.png)



텍스트 에디터로 다음 내용을 추가한 뒤 저장합니다.

```
smbios.reflectHost = "TRUE"
hw.model = "MacBookPro14,3"
board-id = "Mac-551B86E5744E2388"
smc.version = "0"
```



![VMware 맥OS 추가](.\images\999D87485F7DBAED27.png)

## 맥OS 설치

이제 VMware에 맥OS 설치를 위한 모든 준비가 완료되었고, VMware에 새로 만들어진 탭에서 [**Power on this virtual machine**] 버튼을 클릭해 설치를 시작합니다.

![VMware 가상 머신 전원](.\images\99BE38455F7DBAEE28.png)



잠시 후 화면에 애플 로고가 뜨고, 설치가 시작됩니다.

![VMware 맥OS 설치 시작](.\images\991C1A455F7DBAEF2D.png)

단, 처음 작업을 진행했던 **AMD 라이젠 CPU 기반**의 데스크톱 PC는 이 단계에서 VMware의 에러 메시지가 뜨면서 진행이 중단되었습니다.



인텔 CPU 기반의 노트북에서는 아무 문제없이 설치가 진행되는 바, 맥OS는 **VMware 가상 머신으로 설치하더라도 인텔 CPU 기반의 컴퓨터**에서 작업해야 합니다.

가상 머신에서는 인텔/AMD CPU를 구분하지 않을 것이라 생각했는데, 예상이 보기 좋게 빗나갔네요.

정상적인 설치가 진행되면, 언어를 고르는 화면이 뜨고, 이후 과정은 여기서 고르는 언어를 기반으로 진행됩니다.

![맥OS 설치 언어 선택](.\images\996B7A455F7DBAF02C.png)



macOS 유틸리티 항목에서는 제일 먼저 [**디스크 유틸리티**]를 선택합니다.

![맥OS 설치 디스크 유틸리티](.\images\996A52455F7DBAF12C.png)



디스크 유틸리티에서는, 가상머신에 생성된 디스크를 선택하고 포맷하는 과정을 진행합니다.

왼쪽 메뉴에서 **VMware Virtual SATA Hard Drive Media**를 선택한 뒤 상단의 [**지우기**] 버튼을 클릭합니다.

그리고 [이름] 항목은 적당한 이름을 적고 [포맷] 항목은 APFS로 설정한 뒤 [지우기] 버튼을 클릭합니다.

![맥OS 설치 파티션 포맷](.\images\99D4FB455F7DBAF22B.png)



포맷이 진행 된 후 맥 상단 메뉴의 [디스크 유틸리티]-[디스크 유틸리티 종료] 항목을 선택해 **디스크 유틸리티를 종료**합니다.

![맥OS 설치 디스크 유틸리티 종료](.\images\9911F8455F7DBAF32A.png)



다시 macOS 유틸리티로 돌아와 [macOS 설치]를 선택한 뒤 [계속] 버튼을 클릭합니다.

![맥OS 설치 맥OS 유틸리티](.\images\99BA73455F7DBAF41E.png)



이 단계부터는 화면에 표시되는 항목들을 선택한 뒤 계속 진행하기만 하면 됩니다.

먼저 맥OS가 설치될 디스크를 선택한 뒤 [설치] 버튼을 클릭하고

![맥OS 설치 디스크 선택](.\images\9981C44A5F7DBAF52E.png)



국가와 언어 선택 과정을 진행합니다.

![맥OS 설치 국가 선택](.\images\9984EE4A5F7DBAF62E.png)



일반적으로는 이 항목에서 [계속] 버튼을 클릭해 진행하면 되지만, 저는 **세벌식390 자판**을 사용하는터라, 자판 설정을 위해 [설정 사용자화하기] 항목을 클릭했습니다.

![맥OS 설치 언어 설정](.\images\99849C4A5F7DBAF72E.png)



한국어 자판 항목을 열어보니, 세벌식 390도 딱 자리잡고 있네요.

'당연히' 390 자판을 지원할 것이라 생각했지만, 막상 보게 되니 무척 반갑습니다.

![맥OS 설치 세벌식390 선택](.\images\999AB24A5F7DBAF724.png)



Apple ID를 입력해 로그인 설정을 합니다.

저는 아이패드 1세대 시절에 만들어 두었던 ID를 오랫만에 입력했습니다.

![맥OS 설치 애플ID 설정](.\images\99528E4A5F7DBAF829.png)



다시 컴퓨터 계정 생성 화면에서, 이 컴퓨터에서 사용할 계정 이름과 암호를 설정합니다.

![맥OS 설치 컴퓨터 계정 생성](.\images\99E0444A5F7DBAF922.png)



라이트/다크/자동의 화면 모드 중 하나를 선택하면

![맥OS 설치 보기 모드 선택](.\images\99574A4A5F7DBAFA28.png)



하단 작업바가 나름(?) 익숙한 맥OS 화면이 뜨고 사용할 수 있게 됩니다.

![맥OS 부팅 초기화면](.\images\992C98505F7DBAFB2A.png)



그런데 VMware에 처음 설치 후 부팅 완료된 맥OS는 **해상도가 1024\*768로 고정**되어, 노트북 화면 좌우 빈 공간이 까맣게 남아 있습니다.



확인해보니 VMware Tools를 추가 설치하면 해상도를 '좀더 유연하게' 사용할 수 있다고 하는데, 과거 사용했던 Virtual Box 역시 비슷한 추가 프로그램을 설치해 화면 해상도와 마우스 조작에 도움받았던 기억이 납니다.

![VMware 해상도](.\images\99652D505F7DBAFC29.png)

VMware Tools를 설치하는 방법에 대해서는 추가 포스팅을 통해 살펴보도록 하겠습니다.



짧은 시간 VMware에 맥OS를 설치하고 사용해보니, 역시 새로운 환경에 적응 시간이 필요할 듯 싶습니다.

프로그램의 최대/최소 버튼의 위치에서 각종 단축키 설정 등이 완전히 다르고 심지어 한/영 변환도 Ctrl + Space를 사용해야 하는 등(저는 오랫동안 Shift + Space를 이용해 왔습니다) 윈도우PC 환경에서 손에 익은 많은 것들을 바꿔야 할 듯 보입니다.



물론 얼마간 사용하다 보면 문서 작업이나 웹서핑, 혹은 사진 편집 작업 등은  윈도우 환경과 큰 이질감 없이 사용할 수 있을 듯 싶습니다.



다만 문서 작업/웹서핑/사진 편집 작업은 현재 PC로도 충분한 만큼, 비싼 아이맥 구입을 합리화(?) 하려면 파이널 컷 같은 특화된 프로그램을 사용해 봐야할 텐데, 이런 프로그램은 가상 머신 환경에서는 실행할 수 없습니다.



어쨌든 **며칠 남짓 맥OS 경험용**으로는 VMware가 꽤 괜찮은 듯 싶은데, 나름 안정적인 듯 싶은 VMware지만 노트북이 절전모드 진입과 깨어나기를 몇 번 반복하는 와중에 (오랫동안 잊고 지냈던) 블루스크린이 뜨는 것을 보면, 역시 짧은 시간 테스트용으로 적당한 듯 싶습니다.