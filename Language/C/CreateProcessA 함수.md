# CreateProcessA 함수

새 프로세스와 기본 스레드를 작성합니다. 새 프로세스는 호출 프로세스의 보안 컨텍스트에서 실행됩니다.

호출하는 프로세스가 다른 사용자로 가장하는 경우 새 프로세스는 호출 프로세스에 토큰을 사용하고 가장 토큰은 사용하지 않습니다. 가장 프로세스 토큰이 나타내는 사용자의 보안 컨텍스트에서 새 프로세스를 실행하려면 [CreateProcessAsUser](https://docs.microsoft.com/windows/desktop/api/processthreadsapi/nf-processthreadsapi-createprocessasusera) 또는 [CreateProcessWithLogonW](https://docs.microsoft.com/windows/desktop/api/winbase/nf-winbase-createprocesswithlogonw) 함수를 사용하십시오.



## Syntax

```c++
BOOL CreateProcessA(
  LPCSTR                lpApplicationName,
  LPSTR                 lpCommandLine,
  LPSECURITY_ATTRIBUTES lpProcessAttributes,
  LPSECURITY_ATTRIBUTES lpThreadAttributes,
  BOOL                  bInheritHandles,
  DWORD                 dwCreationFlags,
  LPVOID                lpEnvironment,
  LPCSTR                lpCurrentDirectory,
  LPSTARTUPINFOA        lpStartupInfo,
  LPPROCESS_INFORMATION lpProcessInformation
);
```



## Parameters

`lpApplicationName`

실행될 모듈의 이름. 이 모듈은 Windows 기반 응용 프로그램이 될 수 있습니다. 로컬 컴퓨터에서 적절한 하위 시스템을 사용할 수 있으면 다른 유형의 모듈 (예 : MS-DOS 또는 OS / 2) 일 수 있습니다.

문자열은 실행할 모듈의 전체 경로와 파일 이름을 지정하거나 부분 이름을 지정할 수 있습니다. 부분 이름의 경우, 함수는 현재 드라이브와 현재 디렉토리를 사용하여 스펙을 완료합니다. 이 함수는 검색 경로를 사용하지 않습니다. 이 매개 변수에는 파일 이름 확장명이 포함되어야합니다. 기본 확장은 사용되지 않습니다.

lpApplicationName 매개 변수는 NULL 일 수 있습니다. 이 경우 모듈 이름은 lpCommandLine 문자열의 첫 번째 공백으로 구분 된 토큰이어야합니다. 공백이 포함 된 긴 파일 이름을 사용하는 경우 인용 된 문자열을 사용하여 파일 이름이 끝나는 위치와 인수가 시작되는 위치를 나타냅니다. 그렇지 않으면 파일 이름이 모호합니다. 예를 들어, 'c:\\program files\\sub dir\\program name'문자열을 고려하십시오. 이 문자열은 여러 가지 방법으로 해석 될 수 있습니다. 시스템은 다음 순서로 가능성을 해석합니다.

**c:\program.exe** **c:\program files\sub.exe** **c:\program files\sub dir\program.exe** **c:\program files\sub dir\program name.exe**  실행 모듈이 16 비트 응용 프로그램 인 경우 lpApplicationName은 NULL이어야하고 lpCommandLine이 가리키는 문자열은 실행 가능 모듈과 인수를 지정해야합니다.

배치 파일을 실행하려면 명령 인터프리터를 시작해야합니다. lpApplicationName을 cmd.exe로 설정하고 lpCommandLine을 다음 인수로 설정하십시오. /c와 배치 파일의 이름.



`lpCommandLine`

실행될 명령 행.

이 문자열의 최대 길이는 32,768 자이며 유니 코드 종료 널 문자를 포함합니다. lpApplicationName이 NULL이면 lpCommandLine의 모듈 이름 부분은 MAX_PATH 문자로 제한됩니다.

이 함수의 유니 코드 버전 인 CreateProcessW는이 문자열의 내용을 수정할 수 있습니다. 따라서이 매개 변수는 읽기 전용 메모리 (예 : const 변수 또는 리터럴 문자열)에 대한 포인터가 될 수 없습니다. 이 매개 변수가 상수 문자열 인 경우 함수가 액세스 위반을 일으킬 수 있습니다.

lpCommandLine 매개 변수는 NULL 일 수 있습니다. 이 경우 함수는 lpApplicationName이 가리키는 문자열을 명령 줄로 사용합니다.

lpApplicationName 및 lpCommandLine이 모두 NULL이 아닌 경우 lpApplicationName이 가리키는 널 종료 문자열은 실행할 모듈을 지정하고 lpCommandLine이 가리키는 널 종료 문자열은 명령 행을 지정합니다. 새로운 프로세스는 GetCommandLine을 사용하여 전체 명령 줄을 검색 할 수 있습니다. C로 작성된 콘솔 프로세스는 argc 및 argv 인수를 사용하여 명령 행을 구문 분석 할 수 있습니다. argv [0]은 모듈 이름이므로 C 프로그래머는 일반적으로 모듈 이름을 명령 줄의 첫 번째 토큰으로 반복합니다.

lpApplicationName이 NULL이면 명령 줄의 첫 번째 공백으로 구분 된 토큰이 모듈 이름을 지정합니다. 공백이 포함 된 긴 파일 이름을 사용하는 경우 인용 된 문자열을 사용하여 파일 이름이 끝나는 위치와 인수가 시작되는 위치를 나타냅니다 (lpApplicationName 매개 변수에 대한 설명 참조). 파일 이름에 확장명이 없으면 .exe가 추가됩니다. 따라서 파일 이름 확장명이 .com 인 경우이 매개 변수에는 .com 확장명이 포함되어야합니다. 파일 이름이 확장자없이 마침표 (.)로 끝나거나 파일 이름에 경로가 포함되어 있으면 .exe가 추가되지 않습니다. 파일 이름에 디렉토리 경로가 없으면 시스템은 다음 순서로 실행 파일을 검색합니다.

- 응용 프로그램이로드 된 디렉토리.
- 상위 프로세스의 현재 디렉토리
- 32 비트 Windows 시스템 디렉토리. 이 디렉터리의 경로를 가져 오려면 GetSystemDirectory 함수를 사용하십시오.
- 16 비트 Windows 시스템 디렉토리. 이 디렉토리의 경로를 가져 오는 함수는 없지만 탐색됩니다. 이 디렉토리의 이름은 System입니다.
- Windows 디렉토리. GetWindowsDirectory 함수를 사용하여이 디렉터리의 경로를 가져옵니다.
- PATH 환경 변수에 나열된 디렉토리. 이 함수는 App Paths 레지스트리 키로 지정된 응용 프로그램 별 경로를 검색하지 않습니다. 검색 순서에 이 응용 프로그램 별 경로를 포함 시키려면 ShellExecute 함수를 사용하십시오.

시스템은 인수와 파일 이름을 구분하기 위해 종료 문자 Null을 명령 행 문자열에 추가합니다. 이렇게하면 내부 처리를 위해 원래 문자열을 두 개의 문자열로 나눕니다.



`lpProcessAttributes`

새 프로세스 개체에 대해 반환 된 핸들을 자식 프로세스가 상속 할 수 있는지 여부를 결정하는 SECURITY_ATTRIBUTES 구조체에 대한 포인터입니다. lpProcessAttributes가 NULL이면 핸들을 상속 할 수 없습니다.

구조의 lpSecurityDescriptor 멤버는 새 프로세스에 대한 보안 설명자를 지정합니다. lpProcessAttributes가 NULL이거나 lpSecurityDescriptor가 NULL이면 프로세스는 기본 보안 설명자를 가져옵니다. 프로세스의 기본 보안 설명자에있는 ACL은 작성자의 기본 토큰에서 가져옵니다. Windows XP : 프로세스의 기본 보안 설명자에있는 ACL은 작성자의 기본 토큰 또는 가장 (impersonation) 토큰에서 가져옵니다. 이 동작은 Windows XP SP2 및 Windows Server 2003에서 변경되었습니다.



`lpThreadAttributes`

새 스레드 객체에 대해 반환 된 핸들을 자식 프로세스가 상속 할 수 있는지 여부를 결정하는 SECURITY_ATTRIBUTES 구조체에 대한 포인터입니다. lpThreadAttributes가 NULL이면 핸들을 상속 할 수 없습니다.

구조의 lpSecurityDescriptor 멤버는 주 스레드에 대한 보안 설명자를 지정합니다. lpThreadAttributes가 NULL이거나 lpSecurityDescriptor가 NULL이면 스레드가 기본 보안 설명자를 가져옵니다. 스레드에 대한 기본 보안 설명자의 ACL은 프로세스 토큰에서 가져옵니다. Windows XP : 스레드의 기본 보안 설명자에있는 ACL은 작성자의 기본 토큰 또는 가장 (impersonation) 토큰에서 가져옵니다. 이 동작은 Windows XP SP2 및 Windows Server 2003에서 변경되었습니다.



`bInheritHandles`

이 매개 변수가 TRUE이면 호출 프로세스의 상속 가능한 각 핸들이 새 프로세스에 상속됩니다. 매개 변수가 FALSE 인 경우 핸들은 상속되지 않습니다. 상속 된 핸들은 원래 핸들과 동일한 값과 액세스 권한을가집니다.

**Terminal Services:**  세션간에 핸들을 상속 할 수 없습니다. 또한이 매개 변수가 TRUE이면 호출자와 동일한 세션에서 프로세스를 작성해야합니다.

**Protected Process Light (PPL) processes:**  

PROCESS_DUP_HANDLE이 PPL이 아닌 프로세스에서 PPL 프로세스까지 허용되지 않기 때문에 PPL 프로세스가 비 PPL 프로세스를 작성할 때 일반 핸들 상속이 차단됩니다. 프로세스 보안 및 액세스 권한 참조



`dwCreationFlags`

우선 순위 클래스와 프로세스 생성을 제어하는 플래그. 값 목록은 프로세스 작성 플래그를 참조하십시오.

또한 이 매개 변수는 프로세스의 스레드 우선 순위를 결정하는 데 사용되는 새 프로세스의 우선 순위 클래스를 제어합니다. 값 목록을 보려면 GetPriorityClass를 참조하십시오. 우선 순위 클래스 플래그가 하나도 지정되지 않은 경우, 생성 프로세스의 우선 순위 클래스가 IDLE_PRIORITY_CLASS 또는 BELOW_NORMAL_PRIORITY_CLASS가 아닌 경우 우선 순위 클래스의 기본값은 NORMAL_PRIORITY_CLASS입니다. 이 경우 하위 프로세스는 호출 프로세스의 기본 우선 순위 클래스를받습니다.



`lpEnvironment`

새 프로세스의 환경 블록에 대한 포인터. 이 매개 변수가 NULL이면 새 프로세스는 호출 프로세스의 환경을 사용합니다.

환경 블록은 Null로 끝나는 Null 종료 문자열 블록으로 구성됩니다. 각 문자열의 형식은 다음과 같습니다.

*name*=*value*\0

등호는 구분 기호로 사용되기 때문에 환경 변수 이름에 사용하면 안됩니다.

환경 블록은 유니 코드 또는 ANSI 문자를 포함 할 수 있습니다. lpEnvironment가 가리키는 환경 블록에 유니 코드 문자가 포함되어 있으면 dwCreationFlags에 CREATE_UNICODE_ENVIRONMENT가 포함되어 있는지 확인하십시오. 이 매개 변수가 NULL이고 상위 프로세스의 환경 블록에 유니 코드 문자가 포함되어 있으면 dwCreationFlags에 CREATE_UNICODE_ENVIRONMENT가 포함되어 있는지도 확인해야합니다.

이 환경 함수의 ANSI 버전 인 CreateProcessA는 프로세스 환경 블록의 총 크기가 32,767자를 초과하면 실패합니다.

ANSI 환경 블록은 두 개의 0 바이트로 종료됩니다. 하나는 마지막 문자열이고 다른 하나는 블록을 종료하는 것입니다. 유니 코드 환경 블록은 4 개의 0 바이트로 종료됩니다. 마지막 두 개는 두 번째 문자열이고 두 개는 블록을 종료합니다.



`lpCurrentDirectory`

프로세스의 현재 디렉토리에 대한 전체 경로. 이 문자열은 UNC 경로를 지정할 수도 있습니다.

이 매개 변수가 NULL이면 새 프로세스는 호출 프로세스와 동일한 현재 드라이브 및 디렉토리를 갖습니다. (이 기능은 주로 응용 프로그램을 시작하고 초기 드라이브 및 작업 디렉토리를 지정해야하는 쉘에 대해 제공됩니다.)



`lpStartupInfo`

STARTUPINFO 또는 STARTUPINFOEX 구조체에 대한 포인터.

확장 속성을 설정하려면 STARTUPINFOEX 구조를 사용하고 dwCreationFlags 매개 변수에 EXTENDED_STARTUPINFO_PRESENT를 지정하십시오.

STARTUPINFO 또는 STARTUPINFOEX 핸들은 더 이상 필요하지 않으면 CloseHandle로 닫아야합니다.

```
중요 호출자는 STARTUPINFO의 표준 핸들 필드에 유효한 핸들 값이 포함되도록해야합니다. dwFlags 구성원이 STARTF_USESTDHANDLES를 지정하는 경우에도 이러한 필드는 유효성 검사없이 자식 프로세스로 변경되지 않고 복사됩니다. 올바르지 않은 값은 하위 프로세스가 오작동하거나 충돌 할 수 있습니다. Application Verifier 런타임 확인 도구를 사용하여 잘못된 핸들을 검색하십시오.
```



`lpProcessInformation`

새로운 프로세스에 대한 식별 정보를 수신하는 PROCESS_INFORMATION 구조체의 포인터.

PROCESS_INFORMATION의 핸들은 더 이상 필요하지 않으면 CloseHandle로 닫아야합니다.



## Return Value

함수가 성공하면 반환 값은 0이 아닙니다.

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.

프로세스가 초기화를 완료하기 전에 이 함수가 반환됩니다. 필요한 DLL을 찾지 못하거나 초기화하지 못하면 프로세스가 종료됩니다. 프로세스의 종료 상태를 가져 오려면 GetExitCodeProcess를 호출하십시오.