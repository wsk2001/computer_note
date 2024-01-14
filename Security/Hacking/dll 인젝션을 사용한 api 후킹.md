# dll 인젝션을 사용한 api 후킹

출처: https://bpsecblog.wordpress.com/2016/07/28/%EC%A0%95%EC%83%81%EC%9D%B8%EC%9D%B4-%EC%93%B4-dll-%EC%9D%B8%EC%A0%9D%EC%85%98%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-api-%ED%9B%84%ED%82%B9/



**주석 wonsool: 완전한 코드는 아님, 개념만 참조**



안녕하세요. 저는 새우잡이 어선에서 **가장 정상**인 **업무요정 IGIs**라고 합니다요.
아무래도 다른 선원들과 다르게 저는 지극히 **정상**이기 때문에 매우 매우 포멀한,
약 따위는 빨지 않은 API 후킹에 대한 글을 **제 위주로 제가 한대로 제가 이해한만큼만** 써보도록 하겠습니다요.
여러분들의 이해따위는 중요하지 않아요…
왜냐구요? 저도 모르는걸요.
왜 모르냐구요? 전 컴공을 졸업한 **컴맹**이니까요.



#### **1. Dll**

이건 제가 절대 귀찮아서 그러는게 아니라 저희 블로그에 매우 잘 설명된 글이 있어요.

**[요기보면 라이브러리 설명이 있고, 다이나믹 링크 얘기가 있거든요.](https://bpsecblog.wordpress.com/2016/03/07/about_got_plt_1/)**

이걸 이해할 수 있을 때까지 읽고 이 두가지를 잘 조합한 다음에 Google 신에게 dll이란? 이라는 키워드로 검색해보세요.

이해되셨죠?



#### 2. Dll 인젝션(Injection)

자 이제 Dll을 알았으니까 Dll 인젝션을 알려드릴게요.

> injection 미국·영국 [ɪn|dƷekʃn]
>
> 1. 주사 2. (상황・사업 등의 개선을 위한 거액의) 자금 투입 3. (액체의) 주입

네x버 사전에서 가져왔어요.

이거 보시면 아시겠죠? Dll을 삽입하는 겁니다. 간단하죠?
자 그럼 제 **의식의 흐름에 따라 설명**을 해볼게요. 전 **설명충**이니까.

일단 **프로세스가 실행되면 Dll들을 막 불러와요. 프로그램을 실행하려면 Dll이 필요하니까요.**

![dll_추가된_그림](.\Images\dll_e1848ee185aee18480e185a1e18483e185ace186ab_e18480e185b3e18485e185b5e186b7.png)

<p style="text-align: center;">프로세스에 필요한 dll 이 올라간 그림 (feat.Process Explorer)</p>

이렇게 불러오는데, 얘네들은 이 **프로세스에게 할당된 메모리에 올라가는거죠.**

그러니까 이 **프로세스에게 할당된 메모리**라는게 뭐냐…
뭐 당연한거지만 **프로세스들끼리 메모리가 겹치면 문제가 생기겠죠?**

예를 들면 스타크래프트의 SCV 정보가 들어가야 할 메모리에 워크래프트의 피전트 정보가 들어가서
스타를 하는데 SCV대신 오크가 뛰어다니는 (졸라 재밌겠다.) 일이 생기진 않겠지만, 말이 그렇다는 겁니다. (설마 스타크래프트 몰라요? 워크래프트 몰라요? 아니죠? 내가 나이가 많은건가…)

여기까진 웃자고 하는 소리고…(보통 제가 이런 개그를 치면 우리 선원들은 대화의 장을 떠나 업무에 몰두 하더군요. 나라는 업무요정.)

어쨌든… 제대로 설명드리면

> ##### **프로세스?**
>
> 프로그램과 프로세스
> **프로그램은 일반적으로 하드 디스크 등에 저장되어 있는 실행코드**를 뜻하고, **프로세스는 프로그램을 구동하여 프로그램 자체와 프로그램의 상태가 메모리 상에서 실행되는 작업 단위**를 지칭한다. 예를 들어, 하나의 프로그램을 여러 번 구동하면 여러 개의 프로세스가 메모리 상에서 실행된다.

위키에서 가져왔어요.

보통 **프로그램에 메모리가 붙으면 프로세스**라고 합니다.
그러니까 메모리가 붙어서 **실행 중인 프로그램**이 프로세스다. 이렇게 이해하시면 쉽네요.

> ##### 메모리 할당?
>
> 프로그램이 돌아가려면 그 안에 변수라던가 코드들이라던가 온갖 **정보들이 필요**하니까요.
> **그것들에 대한 메모리**가 필요한거죠.

그리고 그 **정보가 겹치면 당연히 문제**가 생기겠죠. 여기서 필요한 정보가 저기서 쓰이고 그러면 안되니까요. 특히 운영 체제에서 사용하는 메모리 정보에 다른 놈(?)들이 접근해서 정보를 수정하거나 분탕질을 친다면 아주 큰일이 납니다요.

그래서 **프로세스 별로 메모리를 주고 접근 권한을 주는 거죠.**

뭐 이제 그 접근 권한에 대한건 뒤에서 다시 설명드리도록 하고

> ##### DLL인젝션?
>
> 드디어 Dll인젝션입니다. 선행지식을…장황하게 설명했지만 간단히 말해서
>
> **내가 만든 Dll**이 **타겟 프로세스 메모리에 삽입(Injection)**되어서
> **내가 원하는 행위**를 하도록 만드는 것입니다.

![내dll_추가된_그림.png](.\Images\e18482e185a2dll_e1848ee185aee18480e185a1e18483e185ace186ab_e18480e185b3e18485e185b5e186b7.png)

<p style="text-align: center;">내가 만든 dll을 삽입한 그림 (feat.Process Explorer)</p>

이렇게 들어가서 막 원래 프로세스에는 없던 행위(내가 만든 Dll에 들어있는)를 하고 그러는 겁니다.
이 행위를 통해서  후킹(Hooking)을 할 수 있죠.

------

#### 3. API 후킹

> ##### **후킹(HOOKING)???**
>
> 후킹(영어: hooking)은 소프트웨어 공학 용어로, **운영 체제나 응용 소프트웨어 등의 각종 컴퓨터 프로그램에서 소프트웨어 구성 요소 간에 발생하는 함수 호출, 메시지, 이벤트 등을 중간에서 바꾸거나 가로채는 명령, 방법, 기술이나 행위**를 말한다. 이때 이러한 간섭된 함수 호출, 이벤트 또는 메시지를 처리하는 코드를 후크(영어: hook)라고 한다.
>
> 크래킹(불법적인 해킹)을 할 때 크래킹 대상 컴퓨터의 메모리 정보, 키보드 입력 정보 등을 빼돌리기 위해서 사용되기도 한다.
>
> 예를 들어 특정한 API를 후킹하게 되면 해당 API의 리턴값을 조작하는 등의 동작을 수행할 수 있다.

위키에서 가져왔어요.

**바꾸기, 가로채기** 라고 생각하시면 되요!

> ##### API???
>
> API(Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스)는 **응용 프로그램에서 사용할 수 있도록, 운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스**를 뜻한다. 주로 파일 제어, 창 제어, 화상 처리, 문자 제어 등을 위한 인터페이스를 제공한다.

흠…이거는 쉽게 말해서 일반적으로 사용자가 코드를 모르기 때문에 또는 권한이 없기 때문에 접근할 수 없는 시스템 자원(운영 체제에서 관리하는 자원들)을 사용할 수 있게 만들어주는 함수들이라고 생각하면 됩니다.

우리는 **API라는 이름으로 주어진 함수를 정해진 방법으로 시스템 자원을 사용**할 수 있습니다.

대표적으로 이런게 있어요.

> ##### OPENPROCESS(PROCESS_ALL_ACCESS, FALSE, DWPID)
>
> 이렇게 사용하면 이 프로세스에 의해 생성 된 프로세스가 핸들을 상속하지 않도록 설정된, 대상 프로세스에 대한 모든 권한을 가진, 대상 프로세스의 핸들을 획득할 수 있습죠.

자… 이 API를 후킹하면 위에서 얘기한대로 소프트웨어 구성 요소 간에 발생하는 함수 호출, 메시지, 이벤트 등을 중간에서 가로채는게 가능합니다. 바꿔줄 수도 있고요.

그러면 **원래는 접근하지 못하던 시스템 자원에 마음껏 영향을 줄 수 있는거**죠.

간단하게 주요 정보를 얻어오는 것부터 시작해서

심각하게 시스템에 **분탕질**을 칠 수 있습니다.

신나는 일이죠!!! 분탕질이라니!!!

> ##### 분탕질
>
>  1 . 집안의 재물을 다 없애 버리는 짓.
> 2 . 아주 야단스럽고 부산하게 소동을 일으키는 짓.
> 3 . 남의 물건 따위를 약탈하거나 노략질하는 짓을 비유적으로 이르는 말.

전 2번이 좋네요. 2번으로 가겠…..

> ##### API 후킹 준비물
>
> SetPrivilege()
> OpenProcessToken()
> GetCurrentProcess()
> LookupPrivilegeValue()
> AdjustTokenPrivileges()
> GetProcAddress()
> GetModuleHandle()
> VirtualProtect()
> memcpy()
> VirtualAllocEx()
> WriteProcessMemory()
> CreateRemoteThread()
> VirtualFreeEx()

------

#### 4. 가볼까?

아 이거 코드는 제가 공부할 때 사용했던 **리버싱 핵심 원리**라는 책에 있던 코드를 상당 부분 사용 했습니다요. (이 책 쩔어요. 꼭 보세요. 두 번 보세요. 세번 보세요.)

##### COREDLL.CPP

- Coredll.dll을 생성
- dll injection을 통해 대상 프로그램에서 로드되는 CreateProcess에 후킹하기 위한 코드 탑재

``` cpp
#include "windows.h"
#include "stdio.h"
#include "tchar.h   // TCHAR 사용을 위함
#include "psapi.h"  // API 사용을 위함
 
#pragma warning(disable : 4996) // 비쥬얼 스튜디오 C4996 에러 해결하기 위한 옵션
 
#define _CRT_SECURE_NO_DEPRECATE
#define STR_MODULE_NAME     (L"Coredll.dll")    // 생성될 dll 이름
#define STR_TARGET_PROCESS_NAME (L"iexplore.exe")   // 타겟 프로그램 이름(추후 사용)
 
#define STATUS_SUCCESS  (0x00000000L)
 
typedef LONG NTSTATUS;
 
// 타입 캐스팅을 위한 선언
typedef BOOL(WINAPI *PFCREATEPROCESSA)(
    LPCTSTR lpApplicationName,
    LPTSTR lpCommandLine,
    LPSECURITY_ATTRIBUTES lpProcessAttributes,
    LPSECURITY_ATTRIBUTES lpThreadAttributes,
    BOOL bInheritHandles,
    DWORD dwCreationFlags,
    LPVOID lpEnvironment,
    LPCTSTR lpCurrentDirectory,
    LPSTARTUPINFO lpStartupInfo,
    LPPROCESS_INFORMATION lpProcessInformation
    );
 
typedef BOOL(WINAPI *PFCREATEPROCESSW)(
    LPCTSTR lpApplicationName,
    LPTSTR lpCommandLine,
    LPSECURITY_ATTRIBUTES lpProcessAttributes,
    LPSECURITY_ATTRIBUTES lpThreadAttributes,
    BOOL bInheritHandles,
    DWORD dwCreationFlags,
    LPVOID lpEnvironment,
    LPCTSTR lpCurrentDirectory,
    LPSTARTUPINFO lpStartupInfo,
    LPPROCESS_INFORMATION lpProcessInformation
    );
 
BYTE g_pOrgCPA[5] = { 0, };     // CreateProcessA 원래 시작 주소
BYTE g_pOrgCPW[5] = { 0, };     // CreateProcessW 원래 시작 주소
 
// CoreHook.exe에서 사용할 후킹 코드
BOOL hook_by_code(LPCSTR szDllName, LPCSTR szFuncName, PROC pfnNew, PBYTE pOrgBytes)
{
    FARPROC pFunc;  // 프로세스 주소
    DWORD dwOldProtect, dwAddress;  // 상대 거리 주소
    BYTE pBuf[5] = { 0xE9, 0, };
    PBYTE pByte;
 
    // GetProcAddress를 호출, szDllName의 szFuncName 주소를 가져옴
    // 후킹 대상 API 주소를 구함
    pFunc = (FARPROC)GetProcAddress(GetModuleHandleA(szDllName), szFuncName);
    pByte = (PBYTE)pFunc;
 
    // 이미 후킹되어 있다면 FALSE 반환
    if (pByte[0] == 0xE9)
        return FALSE;
 
    // 변경할 시작 주소, 변경할 크기, 변경할 설정 값, 변경 전 상태를 저장할 변수 포인터
    // 보호 특성을 변경할 가상 메모리의 기준 주소에 대한 포인터, 변경할 메모리 페이지 영역의 크기, 적용할 메모리 보호 형식, 이전 메모리 보호 값에 대한 포인터
    // 5바이트 패치를 위해 메모리에 WRITE 속성 추가
    VirtualProtect((LPVOID)pFunc, 5, PAGE_EXECUTE_READWRITE, &dwOldProtect);
 
    // 기존코드(5바이트) 백업
    memcpy(pOrgBytes, pFunc, 5);
 
    // 원본 API 코드 시작 부분의 5바이트를 ‘JMP XXXXXXXX’ 명령어로 변환하기 위한 JMP 주소 계산(E9 XXXXXXXX)
    // 점프할 주소 – 현재 명령어 주소 – 현재 명령어 길이(JMP = 5)
    dwAddress = (DWORD)pfnNew - (DWORD)pFunc - 5;
    memcpy(&pBuf[1], &dwAddress, 4);
 
    // Hook: 5바이트 패치(JMP XXXXXXXX)
    memcpy(pFunc, pBuf, 5);
 
    // 메모리 속성 복원
    VirtualProtect((LPVOID)pFunc, 5, dwOldProtect, &dwOldProtect);
 
    return TRUE;
}
 
// CoreHook.exe에서 사용할 언후킹 코드
// 훅할 때의 역순
BOOL unhook_by_code(LPCSTR szDllName, LPCSTR szFuncName, PBYTE pOrgBytes)
{
    FARPROC pFunc;
    DWORD dwOldProtect;
    PBYTE pByte;
 
    pFunc = (FARPROC)GetProcAddress(GetModuleHandleA(szDllName), szFuncName);
    pByte = (PBYTE)pFunc;
 
    if (pByte[0] != 0xE9)
        return FALSE;
 
    VirtualProtect((LPVOID)pFunc, 5, PAGE_EXECUTE_READWRITE, &dwOldProtect);
 
    memcpy(pFunc, pOrgBytes, 5);
 
    VirtualProtect((LPVOID)pFunc, 5, dwOldProtect, &dwOldProtect);
 
    return TRUE;
}
 
// 권한 획득
BOOL SetPrivilege(LPCTSTR lpszPrivilege, BOOL bEnablePrivilege)
{
    TOKEN_PRIVILEGES tp;
    HANDLE hToken;
    LUID luid;
 
    if (!OpenProcessToken(GetCurrentProcess(),
        TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY,
        &hToken))
    {
        printf("OpenProcessToken error: %u\n", GetLastError());
        return FALSE;
    }
 
    if (!LookupPrivilegeValue(NULL,     // lookup privilege on local system
        lpszPrivilege,          // privilege to lookup
        &luid))                 // receives LUID of privilege
    {
        printf("LookupPrivilegeValue error: %u\n", GetLastError());
        return FALSE;
    }
 
    tp.PrivilegeCount = 1;
    tp.Privileges[0].Luid = luid;
    if (bEnablePrivilege)
        tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    else
        tp.Privileges[0].Attributes = 0;
 
    // Enable the privilege or disable all privileges.
    if (!AdjustTokenPrivileges(hToken,
        FALSE,
        &tp,
        sizeof(TOKEN_PRIVILEGES),
        (PTOKEN_PRIVILEGES)NULL,
        (PDWORD)NULL))
    {
        printf("AdjustTokenPrivileges error: %u\n", GetLastError());
        return FALSE;
    }
 
    if (GetLastError() == ERROR_NOT_ALL_ASSIGNED)
    {
        printf("The token does not have the specified privilege. \n");
        return FALSE;
    }
 
    return TRUE;
}
 
// dll을 삽입
BOOL InjectDLL2(HANDLE hProcess, LPCTSTR szDllName)
{
    HANDLE hThread;
    LPVOID pRemoteBuf;
    DWORD dwBufSize = (DWORD)(_tcslen(szDllName) + 1) * sizeof(TCHAR);
    FARPROC pThreadProc;
 
    pRemoteBuf = VirtualAllocEx(hProcess, NULL, dwBufSize, MEM_COMMIT, PAGE_READWRITE);
 
    if (pRemoteBuf == NULL)
        return FALSE;
 
    WriteProcessMemory(hProcess, pRemoteBuf, (LPVOID)szDllName, dwBufSize, NULL);
 
    pThreadProc = GetProcAddress(GetModuleHandleA("kernel32.dll"), "LoadLibraryW");
    hThread = CreateRemoteThread(hProcess, NULL, 0, (LPTHREAD_START_ROUTINE)pThreadProc, pRemoteBuf, 0, NULL);
    WaitForSingleObject(hThread, INFINITE);
 
    VirtualFreeEx(hProcess, pRemoteBuf, 0, MEM_RELEASE);
 
    CloseHandle(hThread);
 
    return TRUE;
}
 
// 기존의 CreateProcessA 대신 불리는 함수
//
BOOL WINAPI NewCreateProcessA(
    LPCTSTR lpApplicationName,
    LPTSTR lpCommandLine,
    LPSECURITY_ATTRIBUTES lpProcessAttributes,
    LPSECURITY_ATTRIBUTES lpThreadAttributes,
    BOOL bInheritHandles,
    DWORD dwCreationFlags,
    LPVOID lpEnvironment,
    LPCTSTR lpCurrentDirectory,
    LPSTARTUPINFO lpStartupInfo,
    LPPROCESS_INFORMATION lpProcessInformation
    )
{
    BOOL bRet;
    FARPROC pFunc;
    DWORD addr;
    FILE *fp;
 
    // unhook
    unhook_by_code("kernel32.dll", "CreateProcessA", g_pOrgCPA);
 
    // original API 호출
    pFunc = GetProcAddress(GetModuleHandleA("kernel32.dll"), "CreateProcessA");
    bRet = ((PFCREATEPROCESSA)pFunc)(lpApplicationName,
        lpCommandLine,
        lpProcessAttributes,
        lpThreadAttributes,
        bInheritHandles,
        dwCreationFlags,
        lpEnvironment,
        lpCurrentDirectory,
        lpStartupInfo,
        lpProcessInformation);
 
    ###요기다가 하고 싶은거 넣으시면 되요!!!!
 
    // 생성된 자식 프로세스에 Coredll.dll을 인젝션 시킴
    if (bRet)
        InjectDLL2(lpProcessInformation->hProcess, STR_MODULE_NAME);
 
    // hook
    hook_by_code("kernel32.dll", "CreateProcessA", (PROC)NewCreateProcessA, g_pOrgCPA);
 
    return bRet;
}
 
// 기존의 CreateProcessW 대신에 불리는 함수
//
 
BOOL WINAPI NewCreateProcessW(
    LPCTSTR lpApplicationName,
    LPTSTR lpCommandLine,
    LPSECURITY_ATTRIBUTES lpProcessAttributes,
    LPSECURITY_ATTRIBUTES lpThreadAttributes,
    BOOL bInheritHandles,
    DWORD dwCreationFlags,
    LPVOID lpEnvironment,
    LPCTSTR lpCurrentDirectory,
    LPSTARTUPINFO lpStartupInfo,
    LPPROCESS_INFORMATION lpProcessInformation
    )
{
    BOOL bRet;
    FARPROC pFunc;
    DWORD addr;
    FILE *fp;
 
    // unhook
    unhook_by_code("kernel32.dll", "CreateProcessW", g_pOrgCPW);
 
    // original API 호출
    pFunc = GetProcAddress(GetModuleHandleA("kernel32.dll"), "CreateProcessW");
    bRet = ((PFCREATEPROCESSW)pFunc)(lpApplicationName,
        lpCommandLine,
        lpProcessAttributes,
        lpThreadAttributes,
        bInheritHandles,
        dwCreationFlags,
        lpEnvironment,
        lpCurrentDirectory,
        lpStartupInfo,
        lpProcessInformation);
 
    ###요기다가 하고 싶은거 넣으시면 되요!!!!
 
    // 생성된 자식 프로세스에 Coredll.dll을 인젝션 시킴
    if (bRet)
        InjectDLL2(lpProcessInformation->hProcess, STR_MODULE_NAME);
 
    // hook
    hook_by_code("kernel32.dll", "CreateProcessW",
        (PROC)NewCreateProcessW, g_pOrgCPW);
    return bRet;
}
 
BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    char szCurProc[MAX_PATH] = { 0, };
    char *p = NULL;
 
    GetModuleFileNameA(NULL, szCurProc, MAX_PATH);
    p = strrchr(szCurProc, '\\');
 
    if ((p != NULL) && !_stricmp(p + 1, "CoreHook.exe"))
        return TRUE;
 
    // change privilege
    SetPrivilege(SE_DEBUG_NAME, TRUE);
 
    switch (fdwReason)
    {
    case DLL_PROCESS_ATTACH:
        // hook
        hook_by_code("kernel32.dll", "CreateProcessA",
            (PROC)NewCreateProcessA, g_pOrgCPA);
        hook_by_code("kernel32.dll", "CreateProcessW",
            (PROC)NewCreateProcessW, g_pOrgCPW);
        break;
 
    case DLL_PROCESS_DETACH:
        // unhook
        unhook_by_code("kernel32.dll", "CreateProcessA",
            g_pOrgCPA);
        unhook_by_code("kernel32.dll", "CreateProcessW",
            g_pOrgCPW);
        break;
    }
 
    return TRUE;
}
```

##### COREHOOK.CPP

``` c++
#include "windows.h"
#include "stdio.h"
#include "tlhelp32.h"
#include "tchar.h"
 
#define STR_TARGET_PROCESS_NAME (L"iexplore.exe")
 
enum { INJECTION_MODE = 0, EJECTION_MODE };
 
// 권한 설정
BOOL SetPrivilege(LPCTSTR lpszPrivilege, BOOL bEnablePrivilege)
{
    TOKEN_PRIVILEGES tp;
    HANDLE hToken;
    LUID luid;
 
    // 현재 프로세스의 핸들을 가져와 관련된 액세스토큰을 가져옴.
    if (!OpenProcessToken(GetCurrentProcess(),
        TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY,
        &hToken))
    {
        printf("OpenProcessToken error: %u\n", GetLastError());
        return FALSE;
    }
 
    // 로컬 시스템에 대한 LUID를 가져옴.
    if (!LookupPrivilegeValue(NULL,          // lookup privilege on local system
        lpszPrivilege,   // privilege to lookup
        &luid))         // receives LUID of privilege
    {
        printf("LookupPrivilegeValue error: %u\n", GetLastError());
        return FALSE;
    }
 
    tp.PrivilegeCount = 1;
    tp.Privileges[0].Luid = luid;
 
    if (bEnablePrivilege)
        tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    else
        tp.Privileges[0].Attributes = 0;
 
    // Enable the privilege or disable all privileges.
    if (!AdjustTokenPrivileges(hToken,  // 액세스 토큰 핸들
        FALSE,  // TURE일 경우 모든 권한 비활성화
        &tp,        // TOKEN_PRIBILEGES 구조체 포인터
        sizeof(TOKEN_PRIVILEGES),   // 다음에 오는 버퍼의 사이즈
        (PTOKEN_PRIVILEGES)NULL,    // 이전 상태 없어도 됨
        (PDWORD)NULL))
    {
        printf("AdjustTokenPrivileges error: %u\n", GetLastError());
        return FALSE;
    }
 
    if (GetLastError() == ERROR_NOT_ALL_ASSIGNED)
    {
        printf("The token does not have the specified privilege. \n");
        return FALSE;
    }
 
    return TRUE;
}
 
BOOL InjectDll(DWORD dwPID, LPCTSTR szDllPath)
{
    HANDLE                  hProcess, hThread;
    LPVOID                  pRemoteBuf;
    DWORD                   dwBufSize = (DWORD)(_tcslen(szDllPath) + 1) * sizeof(TCHAR);
    LPTHREAD_START_ROUTINE  pThreadProc;
 
    // 파라미터로 받은 프로세스의 핸들을 받아옴.
    if (!(hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, dwPID)))
    {
        printf("OpenProcess(%d) failed!!!\n", dwPID);
        return FALSE;
    }
 
    // 해당 프로세스의 가상메모리 공간을 할당 받음.
    // 대상 핸들, 할당할 메모리 번지 지정(NULL이면 시스템이 자동 지정), 할당할 메모리 양,
    // 할당 방법 지정, 할당한 페이지의 액세스 타입 지정
    // 할당한 메모리 번지 반환 / NULL 반환
    pRemoteBuf = VirtualAllocEx(hProcess, NULL, dwBufSize,
        MEM_COMMIT, PAGE_READWRITE);
 
    // 해당 프로세스 메모리를 조작.
    // 조작할 대상 프로세스 핸들, 조작할 가상메모리 주소, 메모리에 적을 값(인젝션 시킬 DLL 경로),
    // 메모리에 쓸 크기, 특정 프로세스의 바뀔 바이트를 받는 변수(NULL 사용 안함)
    WriteProcessMemory(hProcess, pRemoteBuf,
        (LPVOID)szDllPath, dwBufSize, NULL);
 
    pThreadProc = (LPTHREAD_START_ROUTINE)
        GetProcAddress(GetModuleHandle(L"kernel32.dll"),
            "LoadLibraryW");
 
    hThread = CreateRemoteThread(hProcess, NULL, 0,
        pThreadProc, pRemoteBuf, 0, NULL);
    WaitForSingleObject(hThread, INFINITE);
 
    VirtualFreeEx(hProcess, pRemoteBuf, 0, MEM_RELEASE);
 
    CloseHandle(hThread);
    CloseHandle(hProcess);
 
    return TRUE;
}
 
// 삽입했던 Dll 제거
// 삽입과 역순
BOOL EjectDll(DWORD dwPID, LPCTSTR szDllPath)
{
    BOOL                    bMore = FALSE, bFound = FALSE;
    HANDLE                  hSnapshot, hProcess, hThread;
    MODULEENTRY32           me = { sizeof(me) };
    LPTHREAD_START_ROUTINE  pThreadProc;
 
    if (INVALID_HANDLE_VALUE ==
        (hSnapshot = CreateToolhelp32Snapshot(TH32CS_SNAPMODULE, dwPID)))
        return FALSE;
 
    bMore = Module32First(hSnapshot, &me);
    for (; bMore; bMore = Module32Next(hSnapshot, &me))
    {
        if (!_tcsicmp(me.szModule, szDllPath) ||
            !_tcsicmp(me.szExePath, szDllPath))
        {
            bFound = TRUE;
            break;
        }
    }
 
    if (!bFound)
    {
        CloseHandle(hSnapshot);
        return FALSE;
    }
 
    if (!(hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, dwPID)))
    {
        printf("OpenProcess(%d) failed!!!\n", dwPID);
        CloseHandle(hSnapshot);
        return FALSE;
    }
 
    pThreadProc = (LPTHREAD_START_ROUTINE)
        GetProcAddress(GetModuleHandle(L"kernel32.dll"),
            "FreeLibrary");
    hThread = CreateRemoteThread(hProcess, NULL, 0,
        pThreadProc, me.modBaseAddr, 0, NULL);
    WaitForSingleObject(hThread, INFINITE);
 
    CloseHandle(hThread);
    CloseHandle(hProcess);
    CloseHandle(hSnapshot);
 
    return TRUE;
}
 
int _tmain(int argc, TCHAR* argv[])
{
    int nMode = INJECTION_MODE;
 
    if (argc != 3)
    {
        printf("\n Usage  : CoreHook.exe <-hook|-unhook> <dll path>\n\n");
        return 1;
    }
 
    // change privilege
    SetPrivilege(SE_DEBUG_NAME, TRUE);
 
    // Inject(Eject) Dll to all process
    if (!_tcsicmp(argv[1], L"-unhook"))
        nMode = EJECTION_MODE;
 
    InjectTargetProcess(nMode, argv[2]);
 
    return 0;
}
```



Win 7 울트 K 32비트 기준으로 짜여져 있습니다.

------

#### 맺으며

감사합니다.
역시 제가 제일 노멀하고 포멀한 것 같아요.

