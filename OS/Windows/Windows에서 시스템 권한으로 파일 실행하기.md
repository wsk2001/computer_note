# Windows에서 시스템 권한으로 파일 실행하기

PsExec 다운로드 - <https://technet.microsoft.com/ko-kr/sysinternals/bb897553>

PsExec 파일을 명령 프롬프트 창에서 아래 형식으로 명령어를 입력하면 시스템 권한으로 해당 파일을 실행하게 됩니다.

시스템 권한으로 실행이 되었는지를 확인하는 방법은 '작업관리자'창에서 해당프로그램의 '사용자이름'란에 'SYSTEM'으로 적혀져 있는지 확인하십시오.  

```
PsExec -i -d -s "시스템 권한으로 실행하고자 하는 파일의 전체경로"
예) PsExec -i -d -s c:\windows\regedit.exe
```

`옵션`

```
-i    Run the program so that it interacts with the desktop of the specified session on the remote system. If no session is specified the process runs in the console session.
-d    Don't wait for process to terminate (non-interactive).
-s    Run the remote process in the System account.
```



**탐색기에 Context 메뉴로 등록** (PsExec.exe 파일은 Windows 폴더에 저장) 하는 레지스트리

```
[HKEY_CLASSES_ROOT\exefile\shell\runassystem]
@="시스템 권한으로 실행"

[HKEY_CLASSES_ROOT\exefile\shell\runassystem\command]
@="psexec.exe -i -d -s \"%L\""
```

​		

출처: http://4savit.blogspot.com/2015/07/windows.html



-----

**아래는 MS 에서 제공하는 PsExec Download Page 에 있는 설명 이다**

출처: https://docs.microsoft.com/ko-kr/sysinternals/downloads/psexec



# PsExec v2.2

[**Download PsTools**](https://download.sysinternals.com/files/PSTools.zip) **(2.7 MB)**



## Introduction

Telnet과 같은 유틸리티와 Symantec의 PC Anywhere와 같은 원격 제어 프로그램을 사용하면 원격 시스템에서 프로그램을 실행할 수는 있지만 액세스하려는 원격 시스템에 클라이언트 소프트웨어를 설치하고 설정해야하는 어려움이 있습니다. PsExec은 수동으로 클라이언트 소프트웨어를 설치하지 않고도 콘솔 응용 프로그램에 대한 완벽한 상호 작용이 가능한 다른 시스템에서 프로세스를 실행할 수있는 경량 Telnet 대체 장치입니다. PsExec의 가장 강력한 용도는 원격 시스템에 대한 대화 형 명령 프롬프트 및 IpConfig와 같은 원격 가능 도구 (원격 시스템에 대한 정보를 표시 할 수없는 기능)를 실행하는 것입니다.

```
참고 : 일부 안티 바이러스 스캐너는 하나 이상의 도구가 '원격 관리'바이러스에 감염되었다고보고합니다. PsTools에는 바이러스가 포함되어 있지 않지만 바이러스에 의해 사용되어 바이러스 알림을 유발합니다.
```



## Installation

실행 경로에 PsExec를 복사하기 만하면됩니다. 'psexec'를 입력하면 사용 구문이 표시됩니다.



## Using PsExec

PsExec의 고급 사용법을 다루는 Mark의 기사에서 Windows IT Pro Magazine 2004 년 7 월호를 참조하십시오.

```
Usage: psexec [\\computer[,computer2[,...] | @file\]][-u user [-p psswd][-n s][-r servicename][-h][-l][-s|-e][-x][-i [session]][-c executable [-f|-v]][-w directory][-d][-<priority>][-a n,n,...] cmd [arguments]
```



| **Parameter**   | **Description**                                              |
| --------------- | ------------------------------------------------------------ |
| **-a**          | 응용 프로그램을 실행할 수있는 프로세서를 쉼표로 구분하십시오. 여기서 1은 가장 낮은 번호의 CPU입니다. 예를 들어 CPU 2와 CPU 4에서 응용 프로그램을 실행하려면 '-a 2,4' |
| **-c**          | 지정된 실행 파일을 원격 시스템에 복사하여 실행하십시오. 이 옵션을 생략하면 응용 프로그램이 원격 시스템의 시스템 경로에 있어야합니다. |
| **-d**          | 프로세스가 종료 될 때까지 기다리지 마십시오 (비 대화식).     |
| **-e**          | 지정된 계정의 프로필을로드하지 않습니다.                     |
| **-f**          | 원격 시스템에 파일이 이미있는 경우에도 지정된 프로그램을 복사하십시오. |
| **-i**          | 원격 시스템에서 지정된 세션의 데스크탑과 상호 작용하도록 프로그램을 실행하십시오. 세션을 지정하지 않으면 프로세스가 콘솔 세션에서 실행됩니다. |
| **-h**          | 대상 시스템이 Vista 이상인 경우 계정의 상승 된 토큰 (사용 가능한 경우)을 사용하여 프로세스를 실행하십시오. |
| **-l**          | 제한된 사용자로 프로세스를 실행합니다 (Administrators 그룹을 제거하고 Users 그룹에 할당 된 권한 만 허용). Windows Vista에서는 프로세스가 낮은 무결성으로 실행됩니다. |
| **-n**          | 원격 컴퓨터에 연결하는 시간 초과 (초)를 지정합니다.          |
| **-p**          | 사용자 이름에 대한 선택적 암호를 지정합니다. 이것을 생략하면 숨겨진 비밀번호를 입력하라는 메시지가 표시됩니다. |
| **-r**          | 만들거나 상호 작용할 원격 서비스의 이름을 지정합니다.        |
| **-s**          | 시스템 계정에서 원격 프로세스를 실행하십시오.                |
| **-u**          | 원격 컴퓨터 로그인에 대한 선택적 사용자 이름을 지정합니다.   |
| **-v**          | 지정된 파일이 버전 번호가 높거나 원격 시스템의 버전 번호보다 최신 버전 인 경우에만 지정된 파일을 복사하십시오. |
| **-w**          | 프로세스의 작업 디렉토리를 설정합니다 (원격 컴퓨터 기준).    |
| **-x**          | Winlogon 보안 데스크탑에 UI를 표시합니다 (로컬 시스템 만 해당). |
| **-priority**   | 다른 우선 순위에서 프로세스를 실행하려면 -low, -belownormal, -abovenormal, -high 또는 -realtime을 지정합니다. 낮은 메모리에서 실행하려면 -background를 사용하고 Vista에서는 I / O 우선 순위를 사용하십시오. |
| **computer**    | 원격 컴퓨터 나 지정된 컴퓨터에서 응용 프로그램을 실행하기 위해 PsExec을 직접 호출하십시오. 컴퓨터 이름을 생략하면 PsExec은 로컬 시스템에서 응용 프로그램을 실행하고 와일드 카드 (\\\\ *)를 지정하면 PsExec은 현재 도메인의 모든 컴퓨터에서 명령을 실행합니다. |
| **@file**       | PsExec는 파일에 나열된 각 컴퓨터에서 명령을 실행합니다.      |
| **cmd**         | 실행할 응용 프로그램의 이름입니다.                           |
| **arguments**   | 통과 할 인수 (파일 경로는 대상 시스템에서 절대 경로 여야 함). |
| **-accepteula** | This flag suppresses the display of the license dialog.      |



 이름에 공백이있는 응용 프로그램은 따옴표로 묶을 수 있습니다.

**psexec \\marklap"c:\long name app.exe"**

Enter 키를 누를 때만 입력이 원격 시스템으로 전달됩니다. Ctrl-C를 입력하면 원격 프로세스가 종료됩니다.

사용자 이름을 생략하면 프로세스가 원격 시스템의 계정 컨텍스트에서 실행되지만 네트워크 리소스에 액세스 할 수 없습니다 (가장하기 때문에). 원격 프로세스가 네트워크 리소스에 액세스해야하거나 다른 계정에서 실행해야하는 경우 Domain \\ User 구문에 유효한 사용자 이름을 지정하십시오. 암호 및 명령은 원격 시스템으로 전송되는 동안 암호화됩니다.

PsExec에서 반환하는 오류 코드는 PsExec가 아니라 실행하는 응용 프로그램에 따라 다릅니다.



## Examples

이 기사에서는 PsExec의 작동 방법과 사용 방법에 대한 팁을 제공합니다. 



다음 명령은 \\\\ marklap에서 대화 형 명령 프롬프트를 시작합니다.  

**psexec \\marklap cmd**



이 명령은 /all 스위치를 사용하여 원격 시스템에서 IpConfig를 실행하고 결과 출력을 로컬로 표시합니다.

**psexec \\marklap ipconfig /all**



이 명령은 test.exe 프로그램을 원격 시스템에 복사하고 대화식으로 실행합니다.

**psexec \\marklap -c test.exe**



원격 시스템에 이미 설치되어있는 프로그램의 전체 경로를 지정하십시오 (시스템의 경로에없는 경우).

**psexec \\marklap c:\bin\test.exe**



Regedit를 시스템 계정에서 대화 형으로 실행하여 SAM 및 SECURITY 키의 내용보기 ::

**psexec -i -d -s c:\windows\regedit.exe**



제한된 사용자 권한으로 Internet Explorer를 실행하려면 다음 명령을 사용하십시오.

**psexec -l -d "c:\program files\internet explorer\iexplore.exe"**



[**Download PsTools**](https://download.sysinternals.com/files/PSTools.zip) **(2.7 MB)**



**PSTools**

PsExec은 Psutools라는 로컬 및 원격 시스템의 관리를 돕는 Sysinternals 명령 줄 도구 키트의 일부입니다.



