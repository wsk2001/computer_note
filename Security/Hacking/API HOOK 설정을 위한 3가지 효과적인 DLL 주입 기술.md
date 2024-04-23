# API HOOK 설정을 위한 3가지 효과적인 DLL 주입 기술

출처: https://www.apriorit.com/dev-blog/679-windows-dll-injection-for-api-hooks

시스템 동작과 API 호출을 제어하고 조작할 수 있는 능력은 모든 Windows 개발자에게 유용한 기술입니다. 이를 통해 내부 프로세스를 조사하고 의심스러운 악성 코드를 탐지할 수 있습니다. [이전에는 AppInit_DLLs 레지스트리 키를 조작하여 전역 API 후크를 설정](https://www.apriorit.com/dev-blog/dev-blog/160-apihooks)하고 실행 중인 프로세스 목록에서 calc.exe 프로세스를 보이지 않게 만드는 쉬운 방법을 설명했습니다.

이번에는 DLL(동적 연결 라이브러리) 주입 기술에 대해 더욱 자세히 알아봅니다. 다른 프로세스가 Windows 프로세스를 종료할 수 없도록 Windows 프로세스를 불멸 상태로 만드는 방법을 보여줍니다. 이 DLL 주입 튜토리얼은 Windows 애플리케이션에서 API 호출의 흐름과 동작을 수정하는 다양한 방법에 대해 자세히 알고 싶어하는 Windows 개발자에게 유용합니다.



## API hooking basics

코드 조작의 깊이를 알아보기 전에 API 후킹의 기본 사항 중 일부를 살펴보겠습니다.

API 후킹이란 무엇입니까? API 후킹은 개발자가 시스템이나 애플리케이션의 동작을 조작하는 데 사용하는 기술입니다. API 후킹을 사용하면 Windows 애플리케이션에서 호출을 가로채거나 API 호출과 관련된 정보를 캡처할 수 있습니다. 또한 API 후킹은 바이러스 백신 및 엔드포인트 탐지 및 대응 솔루션이 악성 코드를 식별하는 데 사용하는 기술 중 하나입니다.

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/figure-1.png)

API 후킹을 구현할 수 있는 방법은 여러 가지가 있습니다. 가장 널리 사용되는 세 가지 방법은 다음과 같습니다.

- **DLL injection** — Windows 프로세스 내에서 코드를 실행하여 다양한 작업을 수행할 수 있습니다.
- **Code injection** —사용자 정의 코드를 다른 프로세스에 붙여넣는 데 사용되는 WriteProcessMemory API를 통해 구현됩니다.
- **[Win32 Debug API](https://www.drdobbs.com/tools/the-win32-debug-api/184410719)** toolset — 디버깅된 애플리케이션에 대한 완전한 제어권을 제공하여 디버깅된 프로세스의 메모리를 쉽게 조작할 수 있도록 합니다.

이 기사에서는 API 호출을 통해 시스템 동작을 조작하는 가장 유연하고, 가장 잘 알려져 있으며, 가장 많이 연구된 접근 방식인 DLL 주입 방법에 중점을 둡니다. 그렇다면 DLL 주입이란 무엇입니까? 간단히 말해서, 다른 프로세스의 주소 공간 내에서 사용자 정의 코드를 실행하는 프로세스입니다. DLL 주입은 또한 가장 보편적인 API 후킹 방법이며 다른 API 후킹 기술보다 제한이 적습니다.

다음을 기반으로 널리 사용되는 세 가지 DLL 주입 방법이 있습니다.

- **SetWindowsHookEx 함수**. 이 방법은 그래픽 사용자 인터페이스(GUI)를 사용하는 애플리케이션에만 적용 가능합니다.
- **CreateRemoteThread 함수**. 이 방법은 모든 프로세스를 연결하는 데 사용할 수 있지만 많은 코딩이 필요합니다.
- **원격 스레드 컨텍스트 패치**. 이 방법은 효율적이지만 다소 복잡하므로 어떤 이유로든 다른 두 가지 방법이 효과가 없을 경우에만 사용하는 것이 좋습니다.

이 기사에서는 이러한 각 메서드를 구현하는 방법을 설명하고 그 중 하나로 API 후크를 설정하는 실제 예를 제공합니다. 우리의 여정은 SetWindowsHookEx 함수를 사용하여 이 목록의 첫 번째 기술을 개요하는 것으로 시작됩니다.



## SetWindowsHookEx 함수를 사용한 DLL 주입

이 게시물에서 소개하는 첫 번째 DLL 주입 기술은 SetWindowsHookEx 함수를 기반으로 합니다. WH_GETMESSAGE 후크를 사용하여 시스템 창에서 처리되는 메시지를 감시하는 프로세스를 설정합니다. 후크를 설정하려면 SetWindowsHookEx 함수를 호출합니다.

``` c++
SetWindowsHookExW(WH_GETMESSAGE, functionAddress, dllToBeInjected, 0);
```

WH_GETMESSAGE 인수는 후크 유형을 결정하고, functionAddress 매개변수는 창이 메시지를 처리하려고 할 때마다 시스템이 호출해야 하는 함수의 주소(프로세스의 주소 공간에서)를 결정합니다.

dllToBeInjected 매개변수는 functionAddress 함수가 포함된 DLL을 식별합니다. 마지막 인수인 0은 후크가 의도된 스레드를 나타냅니다. 0을 전달하면 시스템에 존재하는 모든 GUI 스레드에 대한 후크를 설정한다고 시스템에 알립니다. 따라서 이 방법을 적용하면 시스템의 특정 프로세스 또는 모든 프로세스를 후킹할 수 있습니다.

이 모든 것이 어떻게 작동하는지 살펴보겠습니다.

![image 1](https://www.apriorit.com/wp-content/uploads/2020/05/figure-21.png)

1. Some_application.exe 스레드가 일부 창에 메시지를 보내려고 합니다.
2. 시스템은 이 스레드에 대해 WH_GETMESSAGE 후크가 설정되어 있는지 확인합니다.
3. 그런 다음 시스템은 메시지에 대한 콜백을 포함하는 DLL인 Inject.dll이 Some_application.exe 프로세스의 주소 공간에 매핑되는지 여부를 확인합니다.
4. Inject.dll이 아직 매핑되지 않은 경우 시스템은 이를 Some_application.exe 프로세스의 주소 공간에 매핑하고 해당 프로세스에서 DLL의 잠금 횟수를 증가시킵니다.
5. Inject.dll의 DllMain 함수는 DLL_PROCESS_ATTACH 매개변수를 사용하여 호출됩니다.
6. 그런 다음 Some_application.exe 프로세스의 주소 공간에서 콜백이 호출됩니다.
7. 콜백에서 반환된 후 프로세스 주소 공간의 DLL 잠금 카운터가 1만큼 감소합니다.





## CreateRemoteThread 함수를 사용하여 DLL 주입

이제 CreateRemoteThread 함수를 사용하여 DLL을 주입하는 가장 유연한 방법을 살펴보겠습니다. 전체적인 흐름은 다음과 같습니다.

![figure 3 n](https://www.apriorit.com/wp-content/uploads/2020/05/figure-3-n.png)

DLL을 주입하려면 대상 프로세스의 스레드 내에서 LoadLibrary 함수를 호출하여 원하는 DLL을 로드해야 합니다. 다른 프로세스의 스레드를 관리하는 것은 매우 복잡하므로 그 안에 자신만의 스레드를 생성하는 것이 좋습니다. 다행히 CreateRemoteThread 함수를 사용하면 이 작업이 쉬워집니다.

``` c++
HANDLE CreateRemoteThread(
  HANDLE                 hProcess,
  LPSECURITY_ATTRIBUTES  lpThreadAttributes,
  SIZE_T                 dwStackSize,
  LPTHREAD_START_ROUTINE lpStartAddress,
  LPVOID                 lpParameter,
  DWORD                  dwCreationFlags,
  LPDWORD                lpThreadId
);
```

이 함수는 CreateThread 함수와 매우 유사하지만 새 스레드가 속할 프로세스를 식별하는 추가 hProcess 매개 변수가 있습니다.

우리가 연결하려는 프로세스의 핸들을 얻는 것부터 시작합니다.

``` c++
HANDLE processHandle = OpenProcess(
               PROCESS_CREATE_THREAD | // For CreateRemoteThread
               PROCESS_VM_OPERATION  | // For VirtualAllocEx/VirtualFreeEx
               PROCESS_VM_WRITE,       // For WriteProcessMemory
               FALSE,                  // Don't inherit handles
               processPid);            // PID of our target process
```

그런 다음 대상 프로세스는 개인 메모리에만 액세스할 수 있으므로 DLL 경로를 전달하기 위해 대상 프로세스에 일부 메모리를 할당해야 합니다.

``` c++
// 전체 DLL 경로를 유지하는 데 필요한 바이트 수
int bytesToAlloc = (1 + lstrlenW(injectLibraryPath)) * sizeof(WCHAR);
  
// DLL 경로에 대해 원격 프로세스에 메모리를 할당합니다.
LPWSTR remoteBufferForLibraryPath = LPWSTR(VirtualAllocEx(
        processHandle, NULL, bytesToAlloc, MEM_COMMIT, PAGE_READWRITE));
```

WriteProcessMemory 함수를 사용하여 DLL 경로를 대상 프로세스의 주소 공간에 배치할 수 있습니다.

``` c++
// DLL 경로를 대상 프로세스의 주소 공간에 넣습니다.
WriteProcessMemory(processHandle, remoteBufferForLibraryPath,
            injectLibraryPath, bytesToAlloc, NULL);
```

그런 다음 새 스레드를 시작할 수 있습니다. 이 스레드의 도움으로 DLL이 대상 프로세스에 로드됩니다.

``` c++
// Kernel32.dll에서 LoadLibraryW의 실제 주소를 가져옵니다.
PTHREAD_START_ROUTINE loadLibraryFunction = PTHREAD_START_ROUTINE>(
        GetProcAddress(GetModuleHandleW(L"Kernel32"), "LoadLibraryW"));
  
  
// Kernel32.dll에서 LoadLibraryW의 실제 주소를 가져옵니다.
HANDLE remoteThreadHandle = CreateRemoteThread(processHandle,
        NULL, 0, loadLibraryFunction, remoteBufferForLibraryPath, 0, NULL);
```

마지막으로 스레드 컨텍스트 패치를 기반으로 하는 세 번째 DLL 주입 방법으로 이동할 수 있습니다.



## 원격 스레드 컨텍스트 패치를 사용하여 DLL 주입

이 DLL 주입 방법은 대부분 일반 스레드 활동처럼 보이기 때문에 감지하기가 쉽지 않습니다. 성공하려면 기존 원격 스레드의 컨텍스트를 조작하고 스레드가 이러한 조작에 대해 알지 못하도록 해야 합니다. 대상 스레드의 명령 포인터는 먼저 사용자 정의 코드 조각으로 설정됩니다. 코드가 실행되면 포인터가 원래 위치로 리디렉션됩니다.

전체 프로세스는 다음과 같습니다.

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/figure-4.png)

x64 시스템에서 이 DLL 주입 방법을 어떻게 구현할 수 있는지 살펴보겠습니다.

먼저 대상 프로세스를 찾고 그 안에서 스레드를 선택해야 합니다. DLL이 가능한 한 빨리 로드될 수 있도록 이미 실행 중이거나 실행될 가능성이 있는 스레드를 선택하는 것이 좋습니다. 대기 중인 스레드를 선택하는 것은 최선의 아이디어가 아닙니다. 그러한 스레드는 실행 준비가 되어 있지 않으면 코드를 실행하지 않기 때문입니다.

먼저 OpenThread 함수를 사용하여 원격 스레드의 핸들을 엽니다.

``` c++
HANDLE remoteThreadHandle = OpenThread(
        THREAD_SET_CONTEXT    | // For SetThreadContext
        THREAD_SUSPEND_RESUME | // For SuspendThread and ResumeThread
        THREAD_GET_CONTEXT,     // For GetThreadContext
        FALSE,                  // Don't inherit handles
        remoteThreadId);        // TID of our target thread
```

그런 다음 주입된 코드와 DLL 경로를 저장하기 위해 원격 프로세스에 메모리를 할당해야 합니다.

``` c++
SYSTEM_INFO systemInformation;
GetSystemInfo(&systemInformation);
  
// Allocate systemInformation.dwPageSize bytes in the remote process
LPBYTE buffer = LPBYTE(VirtualAllocEx(remoteProcessHandle, NULL, systemInformation.dwPageSize,
        MEM_COMMIT | MEM_RESERVE, PAGE_EXECUTE_READWRITE));
```

다음으로 원격 할당 버퍼 중간에 DLL 경로를 작성합니다.

``` c++
// Calculate how many bytes to write into the remote buffer
int libraryPathSizeBytes = (wcslen(injectLibraryPath) + 1) * sizeof(WCHAR);
  
WriteProcessMemory(remoteProcessHandle,
        buffer + systemInformation.dwPageSize / 2, injectLibraryPath, libraryPathSizeBytes, NULL);
```

그런 다음 원격 스레드를 일시 중단하고 해당 컨텍스트를 검색합니다.

``` c++
SuspendThread(remoteThreadHandle);
  
CONTEXT context;
context.ContextFlags = CONTEXT_FULL;
  
GetThreadContext(remoteThreadHandle, &context);
```

이제 어셈블리 코드를 컴파일하고 이를 버퍼에 저장합니다.

``` c++
BYTE codeToBeInjected[] = {
        // sub rsp, 28h
        0x48, 0x83, 0xec, 0x28,                           
        // mov [rsp + 18h], rax
        0x48, 0x89, 0x44, 0x24, 0x18,                     
        // mov [rsp + 10h], rcx
        0x48, 0x89, 0x4c, 0x24, 0x10,
        // mov rcx, 11111111111111111h; placeholder for DLL path
        0x48, 0xb9, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11,     
        // mov rax, 22222222222222222h; placeholder for “LoadLibraryW” address
        0x48, 0xb8, 0x22, 0x22, 0x22, 0x22, 0x22, 0x22, 0x22, 0x22,
        // call rax
        0xff, 0xd0,
        // mov rcx, [rsp + 10h]
        0x48, 0x8b, 0x4c, 0x24, 0x10,
        // mov rax, [rsp + 18h]
        0x48, 0x8b, 0x44, 0x24, 0x18,
        // add rsp, 28h
        0x48, 0x83, 0xc4, 0x28,
        // mov r11, 333333333333333333h; placeholder for the original RIP
        0x49, 0xbb, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33,
        // jmp r11
        0x41, 0xff, 0xe3
};
  
// Set the DLL path
*reinterpret_cast<PVOID*>(codeToBeInjected + 0x10) = static_cast<void*>(buffer + systemInformation.dwPageSize / 2);
// Set LoadLibraryW address
*reinterpret_cast<PVOID*>(codeToBeInjected + 0x1a) = static_cast<void*>(GetProcAddress(GetModuleHandle(L"kernel32.dll"), "LoadLibraryW"));
// Jump address (back to the original code)
*reinterpret_cast<unsigned long="">(codeToBeInjected + 0x34) = context.Rip;</unsigned>
```

원격 스레드의 원격 IP(RIP) 레지스터를 버퍼로 설정합니다.

``` c++
context.Rip = reinterpret_cast(buffer);
```

마지막으로 새 컨텍스트를 설정하고 스레드를 재개합니다.

``` c++
SetThreadContext(remoteThreadHandle, &context);
  
ResumeThread(remoteThreadHandle);
```

다양한 DLL 주입 기술에 대해 더 잘 이해했으므로 이제 이러한 기술이 실제로 어떻게 작동하는지 살펴보겠습니다.



## 실제로 DLL 주입으로 API 후크 설정

**CreateRemoteThread** 함수를 사용하는 것은 DLL 주입으로 API 후크를 설정하는 가장 보편적인 방법이지만 이 방법에는 광범위한 예비 코딩이 필요합니다. 이것이 바로 시간이 덜 소요되는 **SetWindowsHookEx** 함수를 사용하여 DLL 주입으로 API 후크를 설정하는 방법을 설명하는 이유입니다 .

이 예제는 C++로 작성된 기본 사용자 모드 DLL을 기반으로 합니다. 추적을 따라갈 수 있으려면 최신 버전의 [Mhook](https://github.com/apriorit/mhook) 소스를 프로젝트에 추가해야 합니다.

여기서 우리의 주요 목표는 시스템의 다른 프로세스가 종료할 수 없는 불멸의 프로세스를 만드는 것입니다. 전역 API 후크를 설정하는 것부터 시작합니다.

1. **SetWindowsHookEx** 함수 를 사용하여 DLL을 삽입합니다 .

C++

```c++
int main(int argc, char* argv[])
{       
    HMODULE dllToBeInjected = LoadLibraryExW(L"dllWithMhook.dll", NULL, DONT_RESOLVE_DLL_REFERENCES)
  
    // Get the address of the function to be called in a message
    HOOKPROC functionAddress = HOOKPROC(GetProcAddress(dllToBeInjected, "MessageHookFunction"));
  
    // Set the hook in the hook chain
    HHOOK hookHandle = SetWindowsHookExW(WH_GETMESSAGE, functionAddress, dllToBeInjected, 0);
    // Trigger the hook (our DLL is being loaded to the target process)
    PostThreadMessage(threadId, WM_NULL, NULL, NULL);
      
    system("pause");  
         
    UnhookWindowsHookEx(hookHandle);
  
    return 0;
}
```

1. 후크를 제거한 후 원래 기능을 복원할 수 있는지 확인하려면 해당 주소를 저장해야 합니다.

**[프로세스를 종료하려면 kernel32.dll에서 TerminateProcess](https://docs.microsoft.com/en-us/windows/win32/api/processthreadsapi/nf-processthreadsapi-terminateprocess)** 함수를 호출해야 합니다 . 전역 변수의 생성 및 초기화 덕분에 이제 원래 함수의 주소를 저장할 수 있습니다.

C++

```c++
typedef BOOL (*TerminateProcessType)(HANDLE hProcess, UINT uExitCode);
  
// The original function
TerminateProcessType TrueTerminateProcess = TerminateProcessType(
        GetProcAddress(GetModuleHandleW(L"kernel32"), "TerminateProcess"));
```

1. **원래 TerminateProcess** 함수 대신 **HookedTerminateProcess** 함수 를 연결했습니다 . 후크된 함수는 먼저 kernel32.dll에서 [QueryFullProcessImageNameW](https://docs.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-queryfullprocessimagenamew) 함수를 호출하고 프로세스에 대한 실행 가능 이미지의 전체 이름을 가져옵니다.

이제 프로세스 이름을 확인해야 합니다. "_immortal" 접미사가 있는 경우 종료를 허용해서는 안 되는 프로세스입니다.

**참고** : 원본 기능과 후크 기능 모두 동일한 서명을 가져야 합니다.

C++

```c++
BOOL HookedTerminateProcess(HANDLE hProcess, UINT uExitCode)
{
        WCHAR processExecutablePath[MAX_PATH + 1] = { 0 };
        DWORD processExecutablePathSize = MAX_PATH;
  
        if (!QueryFullProcessImageNameW(hProcess, PROCESS_NAME_NATIVE,
                      processExecutablePath, &processExecutablePathSize))
        {
               return TrueTerminateProcess(hProcess, uExitCode);
        }
  
        // It's not a process of interest; just call the original function
        if (!wcsstr(processExecutablePath, L"_immortal.exe"))
        {
               return TrueTerminateProcess(hProcess, uExitCode);
        }
  
        MessageBoxW(0, L"The process can't be terminated!",
                L"Injected Dll", MB_OK | MB_ICONERROR);
  
        // Return error as if the original 'TerminateProcess' failed
        SetLastError(ERROR_ACCESS_DENIED);
  
        return 0;
}
```

1. 여기서 마침내 대상 프로세스의 코드에 DLL을 삽입하여 후크를 설정할 수 있습니다.

대상 프로세스에 로드되면 **DllMain 함수는** **DLL_PROCESS_ATTACH** 매개변수 를 수신합니다 . 이제 우리는 Mhook 라이브러리의 도움으로 이 프로세스를 조작하고 선택한 기능을 연결할 수 있습니다.

C++

```c++
BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpReserved) 
{
        WCHAR libraryPath[MAX_PATH + 1] = { 0 };
        DWORD libraryPathSize = MAX_PATH;
  
        switch (fdwReason)
        {
               case DLL_PROCESS_ATTACH:
                       if (!GetModuleFileNameW(hinstDLL, libraryPath, libraryPathSize))
                       {
                               return TRUE;
                       }
  
                       // Increment load library link count
                       LoadLibraryW(libraryPath);
  
                       Mhook_SetHook((PVOID*)&TrueTerminateProcess, HookedTerminateProcess);
                       break;
```

1. DLL이 대상 프로세스의 주소 공간에서 언로드되면 **DllMain 함수는** **DLL_PROCESS_DETACH** 매개변수 를 받습니다 . 그 후 후크를 제거하고 원래 기능을 복원합니다.

C++

```c++
BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpReserved) 
{
        WCHAR libraryPath[MAX_PATH + 1] = { 0 };
        DWORD libraryPathSize = MAX_PATH;
  
        switch (fdwReason)
        {
        ..................
               case DLL_PROCESS_DETACH:
                       Mhook_Unhook((PVOID*)&TrueTerminateProcess);
                       break;    
        }    
  
        return TRUE; 
}
```

이제 Windows DLL 삽입으로 API 후크를 설정하는 데 필요한 모든 코드가 있습니다. 이제 이 코드가 실제로 작동하는지 확인할 차례입니다.

또한 읽어보세요

#### 사용자 모드 및 드라이버 모드 기술을 사용하여 네트워크 제어 및 모니터링: 개요, 장단점, WFP 구현

후킹과 기타 사용자 및 커널 모드 기술을 사용하여 애플리케이션의 네트워크 트래픽을 관리합니다. 트래픽을 제어하여 사용자 액세스를 관리하고, 보안 위협을 탐지하고, 데이터 유출을 방지하는 방법을 알아보세요.

[더 알아보기](https://www.apriorit.com/dev-blog/688-driver-controlling-and-monitoring-networks-with-user-mode-and-driver-mode-techniques)

![img](https://www.apriorit.com/articles/sd-articles/688-driver-controlling-and-monitoring-networks-with-user-mode-and-driver-mode-techniques/attachment/controlling-and-monitoring-a-network-jpg)

## API 후킹 샘플 코드 실행

실제적인 설명을 위해 [구조적 저장소 뷰어 유틸리티를 사용하고 ](https://www.mitec.cz/ssv.html)**SetWindowsHookEx** 함수 와 함께 DLL을 주입하여 이를 불멸의 프로세스로 전환했습니다 . 이 프로세스의 결과로 우리는 SSView_immortal.exe라는 이름의 실행 파일을 얻었습니다. 이 실행 파일을 실행하고 작업 관리자에서 살펴보겠습니다. 또한 DLL이 실제로 Taskmgr.exe 프로세스에 삽입되었는지 확인하려면 [Process Explorer](https://docs.microsoft.com/en-us/sysinternals/downloads/process-explorer) 유틸리티가 설치되어 있어야 합니다 .

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/WIN-API-HOOKS-3.jpg)

작업 관리자에서 SSView_immortal.exe 프로세스를 볼 수 있습니다. 종료해 보겠습니다.

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/WIN-API-HOOKS-4.png)

End task 를 클릭하면 오류가 있는 메시지 상자가 나타납니다(연결된 함수에 표시되는 것과 동일한 오류).

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/WIN-API-HOOKS-5.jpg)

그런 다음 "액세스가 거부되었습니다"라는 메시지도 수신됩니다. 이것은 후크된 함수를 구현할 때 [SetLastError](https://docs.microsoft.com/en-us/windows/win32/api/errhandlingapi/nf-errhandlingapi-setlasterror) 함수 를 사용하여 이전에 설정한 **ERROR_ACCESS_DENIED 응답입니다.**

![ ](https://www.apriorit.com/wp-content/uploads/2020/05/WIN-API-HOOKS-6.jpg)

보시다시피, 우리는 시스템 프로세스를 성공적으로 연결하여 다른 Windows 프로세스가 이를 종료할 수 없도록 만들었습니다. 이것이 바로 우리가 의도한 것입니다.

## 결론

API 호출을 연결하는 방법에는 여러 가지가 있습니다. DLL 주입은 사용자 정의 코드를 시스템 프로세스에 주입하기 위한 가장 유연하고 효과적이며 잘 연구된 방법 중 하나입니다. DLL 주입을 수행할 때 DLL은 런타임 시 필요에 따라 로드되므로 실행 중인 프로세스에 코드를 삽입하는 것이 중요합니다.

특정 함수에 후크를 설정하거나 원격 스레드의 컨텍스트를 조작하여 DLL 주입으로 함수를 후크할 수 있는 방법은 여러 가지가 있습니다. 경험상 **CreateRemoteThread** 함수를 사용하여 후크를 설정하는 것이 가장 효과적인 접근 방식이라고 말할 수 있습니다. 이 기능은 Windows 운영 체제에서 지원되므로 작업 시 추가 트릭, 복잡한 실행 파일 구조 또는 운영 체제 내부를 사용할 필요가 없습니다. 그러나 GUI 응용 프로그램으로 작업하는 경우 가장 간편한 옵션인 **SetWindowsHookEx** 기능을 사용할 수 있습니다.

Apriorit에서는 이미 수천 개의 후크를 설정했으며 다양한 운영 체제와 프로세스를 탐색하는 방법을 알고 있습니다. 귀하의 꿈의 프로젝트 실현에 한 걸음 더 가까이 다가가십시오. 당사에 연락하여 이에 대한 모든 정보를 알려주십시오!