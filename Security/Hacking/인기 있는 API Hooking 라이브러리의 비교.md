# 인기 있는 API Hooking 라이브러리의 비교

### Microsoft Detours, EasyHook, Nektra Deviare 및 Mhook

출처: https://www.apriorit.com/dev-blog/win-comparison-of-api-hooking-libraries

후킹 API 함수 호출은 운영 체제(OS)의 동작을 변경하거나 강화하는 효율적인 방법입니다. 개발자는 추적 및 디버깅 작업을 수행하고, 샌드박스를 구축하고, 브라우저 보안을 강화하고, 운영 체제 호출을 가로채고, 악성 코드를 분석하는 등의 작업을 수행해야 하는 프로젝트에서 API 후킹을 사용합니다.

API 호출 후킹을 쉽게 구현할 수 있는 강력한 라이브러리가 많이 있습니다. 이 기사에서는 네 가지 주요 API 후킹 라이브러리에 대한 실제 비교를 제공합니다.

- Microsoft Detours
- EasyHook
- Nektra Deviare
- Mhook

각 라이브러리의 주요 장단점을 논의하고 이를 사용하여 API 호출을 가로채는 방법을 보여줍니다. 또한 이 개요의 마지막 부분에 잘 구성된 비교표를 제공하므로 귀하의 프로젝트에 가장 적합한 라이브러리를 정확하게 결정할 수 있습니다.

이 기사는 API 후크 구현이 필요한 프로젝트를 진행하는 개발 팀과 목표 달성에 가장 적합한 API 후크 라이브러리가 무엇인지 알고자 하는 개발 팀에게 유용할 것입니다.

## 샘플 프로젝트 빌드

비교를 공정하게 하기 위해 우리는 다른 라이브러리를 사용하여 API 기능을 가로채서 동일한 기능(이름에 지정된 패턴이 있는 파일을 사용자로부터 숨길 수 있는 프로젝트)을 구현했습니다. 따라서 우리는 가장 널리 사용되는 4개의 API 후킹 라이브러리가 실제로 어떻게 작동하는지 보여줄 수 있습니다.

우리가 작업할 라이브러리는 다음과 같습니다.

![API 후킹 라이브러리](https://www.apriorit.com/wp-content/uploads/2022/11/figure-1-9.png)

일반적으로 API 후킹 기술을 사용하는 애플리케이션에는 두 가지 주요 구성 요소가 있습니다. 

- 대상 기능을 가로채는 사용자 모드 DLL(동적 링크 라이브러리) 
- 이 DLL을 대상 프로세스에 주입하는 실행 프로그램 

이 두 구성 요소는 모두 API 후킹 라이브러리를 사용하여 C++로 작성되었습니다. 

유일한 예외는 Mhook 라이브러리로, DLL 주입을 위한 실행 프로그램을 구현하는 것이 불가능합니다. 대신, 사전 정의된 DLL 세트를 모든 사용자 모드 프로세스에 로드하는 AppInit_DLLs 인프라를 사용하여 대상 프로세스에 DLL을 주입합니다. [이전 기사](https://www.apriorit.com/dev-blog/160-apihooks#p2) 중 하나에서 이 인프라 작업의 주요 측면을 살펴볼 수 있습니다 .

이 예에서 대상 프로세스는 파일 탐색기입니다. NtQueryDirectoryFile **NTAPI** 함수는 폴더의 모든 파일을 나열합니다. 우리의 목표는 사용자가 폴더에서 해당 파일을 볼 수 없도록 "+*.txt" 파일 이름 패턴을 가진 모든 파일을 숨기는 것입니다.

이름에 "+*.txt" 패턴이 있거나 없는 여러 파일을 만들어 동일한 폴더에 배치했습니다. 원하는 결과를 얻으려면 DLL을 삽입하여 **NtQueryDirectoryFile NTAPI** 함수를 연결하고 파일 탐색기 프로세스에서 모든 NtQueryDirectoryFile API 함수 호출을 차단해야 합니다.

DLL 주입 전에 폴더의 모든 파일을 볼 수 있습니다.

![API 후킹 라이브러리 테스트](https://www.apriorit.com/wp-content/uploads/2022/11/Screenshot_1.jpg)*스크린샷 1: 대상 파일 이름 패턴이 있는 파일이 폴더에 표시됩니다.*

그런 다음 파일 탐색기 프로세스에서 모든 NtQueryDirectoryFile API 함수 호출을 가로채는 DLL을 삽입합니다(스크린샷 2).

![API 후킹 라이브러리 테스트](https://www.apriorit.com/wp-content/uploads/2022/11/Screenshot_2.jpg)*스크린샷 2: DLL이 explorer.exe 프로세스에 주입됩니다(Dviare 라이브러리 사용).*

DLL 삽입이 성공한 후 "+*.txt" 파일 이름 패턴이 있는 파일은 더 이상 샘플 폴더에 표시되지 않습니다(스크린샷 3).

![API 후킹 라이브러리 테스트](https://www.apriorit.com/wp-content/uploads/2022/11/Screenshot_3.jpg)*스크린샷 3: 대상 파일 이름 패턴이 없는 파일만 폴더에 표시됩니다.*

**이 결과를 얻으려면 NtQueryDirectoryFile NTAPI** 함수 에 후크를 설정 하고 API 함수 호출의 동작을 변경해야 합니다.

아래에서는 소스 함수를 후크하는 방법과 사용자에게 특정 파일을 숨기도록 후크된 함수에 적용할 변경 사항을 보여줍니다. 이 코드는 이 기사에서 조사한 가장 많이 사용되는 4개의 API 후킹 라이브러리 각각에 대해 동일합니다.

소스 함수부터 시작해 보겠습니다.

관련 서비스

[운영 체제 관리 솔루션](https://www.apriorit.com/our-expertise/system-management)

## NtQueryDirectoryFile NTAPI 함수 작업

Winternl.h 헤더를 사용하여 폴더의 파일 나열을 담당하는 **NtQueryDirectoryFile** 함수를 관리하는 데 필요한 정보를 얻을 수 있습니다 . 그러나 데이터 유형은 수동으로 정의해야 합니다.

씨

```
// Special data structures and typedefs for managing NtQueryDirectoryFile.
#define STATUS_SUCCESS   ((NTSTATUS)0x00000000L)
#define FileIdBothDirectoryInformation 37

typedef struct _FILE_ID_BOTH_DIR_INFORMATION
{
    ULONG         NextEntryOffset;
    ULONG         FileIndex;
    LARGE_INTEGER CreationTime;
    LARGE_INTEGER LastAccessTime;
    LARGE_INTEGER LastWriteTime;
    LARGE_INTEGER ChangeTime;
    LARGE_INTEGER EndOfFile;
    LARGE_INTEGER AllocationSize;
    ULONG         FileAttributes;
    ULONG         FileNameLength;
    ULONG         EaSize;
    CCHAR         ShortNameLength;
    WCHAR         ShortName[12];
    LARGE_INTEGER FileId;
    WCHAR         FileName[1];
} FILE_ID_BOTH_DIR_INFORMATION, * PFILE_ID_BOTH_DIR_INFORMATION;

typedef NTSTATUS(WINAPI* _NtQueryDirectoryFile)(
    IN           HANDLE                 FileHandle,
    IN OUT       HANDLE                 Event,
    IN OPTIONAL  PIO_APC_ROUTINE        ApcRoutine,
    IN OPTIONAL  PVOID                  ApcContext,
    OUT          PIO_STATUS_BLOCK       IoStatusBlock,
    OUT          PVOID                  FileInformation,
    IN           ULONG                  Length,
    IN           FILE_INFORMATION_CLASS FileInformationClass,
    IN           BOOLEAN                ReturnSingleEntry,
    IN OPTIONAL  PUNICODE_STRING        FileName,
    IN           BOOLEAN                RestartScan
    );

// Original function
const _NtQueryDirectoryFile TrueNtQueryDirectoryFile = (_NtQueryDirectoryFile)GetProcAddress(GetModuleHandleW(L"ntdll"), "NtQueryDirectoryFile");
```

다음으로 이미 Hooked된 함수를 살펴보겠습니다.

### 후킹 후 NtQueryDirectoryFile NTAPI 함수 작업

**NtQueryDirectoryFile NTAPI** 함수가 연결된 후 원래 함수를 호출합니다. 그런 다음 **FileInformationClass** 클래스를 검사하고 **FileIdBothDirectoryInformation** 클래스 인 경우 지정된 파일 이름 패턴이 있는 모든 파일을 목록에서 찾아서 제거해야 합니다. 이 경우 이름에 "+*.txt" 패턴이 있는 모든 파일입니다. 그리고 이것이다!

**후크된 NtQueryDirectoryFile NTAPI** 함수 의 코드는 다음과 같습니다 .

씨

```
// Hooked function
NTSTATUS WINAPI HookNtQueryDirectoryFile(
    IN           HANDLE                 FileHandle,
    IN OUT       HANDLE                 Event,
    IN OPTIONAL  PIO_APC_ROUTINE        ApcRoutine,
    IN OPTIONAL  PVOID                  ApcContext,
    OUT          PIO_STATUS_BLOCK       IoStatusBlock,
    OUT          PVOID                  FileInformation,
    IN           ULONG                  Length,
    IN           FILE_INFORMATION_CLASS FileInformationClass,
    IN           BOOLEAN                ReturnSingleEntry,
    IN OPTIONAL  PUNICODE_STRING        FileName,
    IN           BOOLEAN                RestartScan)
{
    NTSTATUS status = TrueNtQueryDirectoryFile(FileHandle, Event, ApcRoutine, ApcContext, IoStatusBlock, FileInformation,
        Length, FileInformationClass, ReturnSingleEntry, FileName, RestartScan);

    if (FileIdBothDirectoryInformation == FileInformationClass && STATUS_SUCCESS == status)
    {
        PFILE_ID_BOTH_DIR_INFORMATION pCurrent = nullptr;
        PFILE_ID_BOTH_DIR_INFORMATION pNext = reinterpret_cast<PFILE_ID_BOTH_DIR_INFORMATION>(FileInformation);

        do
        {
            pCurrent = pNext;
            pNext = reinterpret_cast<PFILE_ID_BOTH_DIR_INFORMATION>((PUCHAR)pCurrent + pCurrent->NextEntryOffset);
            const std::wstring fileName = std::wstring(pNext->FileName, pNext->FileNameLength / sizeof(wchar_t));

            // Hiding all files in the directory that match the pattern "+*.txt".
            if (!fileName.empty() && PathMatchSpecW(fileName.c_str(), L"+*.txt"))
            {
                if (0 == pNext->NextEntryOffset)
                {
                    pCurrent->NextEntryOffset = 0;
                }
                else
                {
                    pCurrent->NextEntryOffset += pNext->NextEntryOffset;
                }
                pNext = pCurrent;
            }

        } while (pCurrent->NextEntryOffset != 0);
    }
    return status;
}
```

**이 코드를 사용하여 NtQueryDirectoryFile NTAPI** 함수 를 가로채는 DLL을 만듭니다 . 이 기능은 지정된 패턴으로 파일을 숨기므로 이 코드는 이 기사에서 검토하는 모든 API 후킹 라이브러리에 대해 동일합니다.

그러나 모든 라이브러리는 자체 API를 사용하여 DLL에 대한 기능을 연결 및 해제하고 다른 API를 사용하여 주입된 DLL이 있는 프로세스를 생성하기 때문에 우리가 작업하는 각 라이브러리에 고유한 코드 조각이 있습니다.

Microsoft Detours를 시작으로 각 라이브러리, 장단점, 해당 라이브러리를 사용하여 함수를 연결하고 해제하는 데 필요한 코드를 자세히 살펴보겠습니다.

또한 읽어 보세요:
[API 후크 설정을 위한 3가지 효과적인 DLL 주입 기술](https://www.apriorit.com/dev-blog/679-windows-dll-injection-for-api-hooks)

## 마이크로소프트 우회

[Microsoft Detours](https://github.com/microsoft/Detours) 는 Microsoft Windows에서 임의의 Win32 기능을 가로채고 모니터링하고 계측하기 위한 라이브러리입니다. Microsoft가 2002년에 출시한 이 라이브러리는 Windows 응용 프로그램 내에서 Win32 API 호출을 가로채는 데 널리 사용됩니다.

Detours는 대상 함수에 대한 메모리 내 코드를 다시 작성하여 Win32 함수를 가로챕니다. Detours 패키지에는 디버깅 계측을 추가하고 임의의 DLL 및 데이터 세그먼트를 Win32 바이너리에 연결하는 유틸리티도 포함되어 있습니다.

라이브러리는 기능의 일부만 무료로 제공되는 MIT 라이선스와 상용 라이선스에 따라 사용할 수 있습니다. 이는 많은 독립 소프트웨어 공급업체와 Microsoft의 제품 팀에서 널리 사용됩니다. 이 기사에서는 공개적으로 사용 가능한 라이브러리 버전을 사용하겠습니다.

Microsoft Detours 라이브러리에는 장단점이 있습니다.

**장점:** 

- Microsoft Detours는 Windows 내부에 대한 피상적인 이해만 있는 경우에도 작업할 수 있는 고유한 API를 갖춘 [잘 문서화된 경량 라이브러리입니다.](https://github.com/microsoft/detours/wiki)

- Detours의 계측기는 200MHz 프로세서(라이브러리가 처음 테스트된 프로세서)에서 400ns(나노초) 미만의 차단 오버헤드를 배치하는 벤치마크를 통해 효율적인 코딩을 허용합니다. 라이브러리는 대상 바이너리의 가져오기 섹션에서 최소한의 정적 조작을 수행하여 DLL을 로드합니다.

- Detours는 효율적인 메모리 관리 기능을 갖추고 있어 필요에 따라 메모리 블록을 할당하고 결과 데이터 영역을 사용하여 가능한 한 많은 트램폴린을 저장합니다. 트램폴린 *은* 원래 API의 처음 몇 바이트의 기능을 복제한 다음 변경된 바이트 이후에 API로 점프하는 코드 조각입니다.

- 라이브러리는 트랜잭션 후킹 및 후킹 해제를 지원하므로 전부 아니면 전무 접근 방식으로 여러 후크를 동시에 설정할 수 있습니다. 라이브러리는 모든 후크를 설정할 수 있는 경우에만 후크를 설정하고 하나 이상의 후크를 설치할 수 없는 경우 변경 사항을 롤백합니다. 

**단점:** 

- 이 기사에서 논의하는 다른 라이브러리와 달리 Microsoft Detours는 실행 중인 애플리케이션에서 후크를 설정 및 제거할 수 없으며 그렇게 하려고 하면 무작위 충돌이 발생할 수 있습니다.

- 라이브러리는 상업용으로 사용하기에는 상대적으로 비용이 많이 들고 х64 아키텍처로 작업하려면 상업용 라이센스가 필요합니다.

또한 읽어 보세요:
[Windows API 후킹 튜토리얼(DLL 삽입의 예)](https://www.apriorit.com/dev-blog/160-apihooks)

### API 후킹을 위해 Microsoft Detours 사용

우회는 계측되지 않은 대상 함수(트램펄린을 통해 호출 가능)를 계측에서 사용할 서브루틴으로 유지합니다. 트램펄린 디자인을 통해 기존 바이너리 소프트웨어에 대한 대규모의 혁신적인 확장 기능을 사용할 수 있습니다.

Detours 작업을 시작하려면 라이브러리 자체를 빌드하고, 라이브러리 샘플을 추가하고, 이 라이브러리를 사용하도록 프로젝트를 설정해야 합니다. 

Detours로 작업할 때 새로 생성된 DLL에 추가해야 하는 코드는 다음과 같습니다.

씨

```
// allow calls to Mhook functions
#include <detours.h>

BOOL APIENTRY DllMain(
    HINSTANCE hinstDLL,
    DWORD fdwReason,
    LPVOID lpvReserved)
{
    if (DetourIsHelperProcess())
    {
        return TRUE;
    }
    
    switch (fdwReason)
    {
    case DLL_PROCESS_ATTACH:
…
```

후크를 설정하려면 다음 코드를 사용합니다. 

씨

```
DetourRestoreAfterWith();

    printf("MSDetoursHideFile.dll:Starting.\n");
    fflush(stdout);

    DetourTransactionBegin();
    DetourUpdateThread(GetCurrentThread());
    DetourAttach(&(PVOID&)TrueNtQueryDirectoryFile, HookNtQueryDirectoryFile);
    const LONG error = DetourTransactionCommit();

    if (error == NO_ERROR) 
    {
        printf("MSDetoursHideFile.dll: Detoured NtQueryDirectoryFile().\n");
    }
```

함수를 해제하려면 다음 코드가 필요합니다.

씨

```
    DetourTransactionBegin();
    DetourUpdateThread(GetCurrentThread());
    DetourDetach(&(PVOID&)TrueNtQueryDirectoryFile, HookNtQueryDirectoryFile);
    const LONG error = DetourTransactionCommit();

    printf("MSDetoursHideFile.dll: Removed NtQueryDirectoryFile() (result=%ld)\n", error);

    fflush(stdout);
```

다음으로 목록의 두 번째 라이브러리인 EasyHook로 이동하겠습니다.

읽어보기:
[WinAPI를 사용하여 OS 종료 이벤트 처리](https://www.apriorit.com/dev-blog/413-win-api-shutdown-events)

## EasyHook

[EasyHook](https://github.com/EasyHook/EasyHook) 은 32비트 및 64비트 Windows 버전의 완전 관리형 환경 내에서 순수 관리형 기능을 사용하여 비관리형 코드를 연결할 수 있는 라이브러리입니다. EasyHook은 .NET Framework 3.5 및 4.0용으로 빌드된 어셈블리 주입을 지원하며 기본 DLL도 주입할 수 있습니다. 라이브러리는 MIT 라이센스에 따라 배포됩니다.

EasyHook 라이브러리의 주요 장점과 단점을 살펴보겠습니다.

**장점:** 

- EasyHook에는 오픈 소스 기반이 있으므로 라이브러리를 수정, 추출 및 컴파일할 수 있습니다. 

- 이 라이브러리를 사용하면 관리되지 않는 API에 대한 관리되는 후크 처리기를 작성하고 .NET Remoting, WPF 및 WCF와 같은 관리되는 코드가 제공하는 모든 편의를 사용할 수 있습니다.

- 이 라이브러리는 AnyCPU용으로 컴파일된 주입 라이브러리 및 호스트 프로세스를 작성할 수 있으므로 어셈블리를 64비트 프로세스에서 32비트 프로세스에 주입하거나 그 반대로 주입할 수 있습니다.

- EasyHook은 COM 인터페이스 후킹을 지원합니다. 예를 들어, COM 인터페이스에서 메서드 주소 검색을 단순화하는 EasyHook.COMClassInfo 클래스 가 **있는 후크를 사용하여 이러한 함수를 연결할 수 있습니다.**

- 또한 라이브러리에는 바이러스 백신 소프트웨어의 관심을 끌지 못하는 [실험적인 스텔스 주입 메커니즘](https://github.com/svn2github/Easyhook/blob/master/trunk/EasyHookDll/RemoteHook/stealth.c) 도 있습니다. 이는 기존 스레드를 하이재킹하고 호출 스텁을 실행함으로써 달성할 수 있습니다. **CreateThread()** 함수를 사용하여 이 스텁은 지정된 원격 스레드 프로세스를 다시 실행할 스레드를 생성합니다. 이러한 스텔스 원격 스레드가 생성된 후 하이재킹된 스레드는 일반적인 실행을 계속합니다. 

- 이 라이브러리의 **RhInstallDriver 기능을 사용하여** [시스템에 드라이버를 설치할](https://github.com/EasyHook/EasyHook/blob/master/EasyHookDll/RemoteHook/driver.cpp) 수 있습니다 .

**단점:** 

- EasyHook API는 사용자 친화적이지 않으며 문서가 부족합니다. 

- 이 라이브러리의 성능은 안정적이지 않습니다. 예를 들어, unhook 함수를 호출한 후 애플리케이션이 충돌하는 사건이 여러 번 있었습니다.

- 마지막 커밋이 2019년에 이루어졌기 때문에 라이브러리의 지원이 부족한 것 같습니다. 이 라이브러리 사용에 관한 질문에 대한 답변을 찾는 것이 어려울 수 있습니다. 

### API 후킹에 EasyHook 사용

EasyHook 라이브러리를 사용하여 후크를 설정하려면 EasyHook 바이너리를 빌드하고 [이 라이브러리를 사용하도록 프로젝트를 설정해야](http://easyhook.github.io/tutorials/nativemanuallyaddref.html) 합니다 .

라이브러리 사용을 시작하려면 DLL에 **NativeInjectionEntryPoint** 함수를 만들어야 합니다. 원격 네이티브/관리되지 않는 후크의 경우 EasyHook은 DLL 내에서 **NativeInjectionEntryPoint** 내보내기 메서드를 찾을 것으로 예상합니다 . EasyHook은 DLL이 성공적으로 삽입되면 이 메서드를 호출합니다.

EasyHook을 사용하여 API 후크를 설정하는 코드는 다음과 같습니다.

씨

```
extern "C" void __declspec(dllexport) WINAPI NativeInjectionEntryPoint(REMOTE_ENTRY_INFO * inRemoteInfo);

void WINAPI NativeInjectionEntryPoint(REMOTE_ENTRY_INFO* inRemoteInfo)
{
    OutputDebugStringW(L"EasyHookHideFile.dll:NativeInjectionEntryPoint - Entered to the main function");

    // Install the hook
    HOOK_TRACE_INFO hHook = { NULL };
    NTSTATUS result = LhInstallHook(
        GetProcAddress(GetModuleHandle(TEXT("ntdll")), "NtQueryDirectoryFile"),
        (void*)HookNtQueryDirectoryFile,
        nullptr,
        &hHook);
```

후크 설치 후 스레드에서 후크를 활성화해야 합니다.

씨

```
ULONG ACLEntries[1] = { 0 }; // If the threadId in ACL is set to 0, then internally EasyHook uses GetCurrentThreadId()
LhSetInclusiveACL(ACLEntries, 1, &hHook); // Activates hook for the thread
```

지금은 모든 **NtQueryDirectoryFile** 호출이 EasyHook에 의해 차단됩니다. 그러나 작성된 DLL을 대상 프로세스에 주입해야 하므로 실행 프로그램에서 **RhInjectLibrary 함수를 호출합니다.**

씨

```
RhInjectLibrary(
        dwPid,   // The process to inject the DLL into
        0,       // ThreadId to wake up upon injection
        EASYHOOK_INJECT_DEFAULT,
        NULL,    // 32-bit DLL
        const_cast<WCHAR*>(dllToInjectNameW), // 64-bit DLL
        NULL,    // data to send to injected DLL entry point
        0        // size of data to send
    );
```

DLL 주입 후 후크가 설정되고 필요에 따라 작동합니다.

다음으로 Nektra Deviare 라이브러리를 사용하여 후크 작업을 수행하는 방법을 살펴보겠습니다.

읽어보기:
[Python으로 Windows API를 연결하는 종합 가이드](https://www.apriorit.com/dev-blog/727-win-guide-to-hooking-windows-apis-with-python)

## 넥트라 데비아레

[Nektra Deviare](https://github.com/nektra/deviare2) 는 관리되지 않는 Win32 함수, COM 개체, 32비트 및 64비트 애플리케이션의 함수를 가로챌 수 있는 오픈 소스 라이브러리입니다. 이 라이브러리는 후크된 함수의 사전 호출 및 사후 호출을 제어할 수 있습니다.

Deviare에는 무료 버전이 있지만 상용 제품에 사용하려면 상용 라이센스를 구입해야 합니다.

Deviare 라이브러리의 주요 장단점을 살펴보겠습니다.

**장점:** 

- 이 라이브러리는 포괄적인 API와 함께 제공되며 코드 삽입, [프로세스 간 통신](https://www.apriorit.com/dev-blog/web-python-ipc-methods) 및 매개변수 마샬링을 처리하는 데 도움이 될 수 있습니다.

- Deviare 라이브러리는 COM 구성 요소로 구현되므로 C/C++, Visual Basic, C#, Delphi, Python 등 COM을 지원하는 모든 프로그래밍 언어와 통합될 수 있습니다. 

- 오픈 소스 특성으로 인해 라이브러리는 사용자 정의가 가능합니다.

- Detours와 유사하게 Deviare는 트랜잭션 후킹 및 언후킹을 지원합니다.

**단점:** 

- 이 라이브러리는 문서화 수준이 낮고 효율성이 제한적입니다.

- EasyHook과 달리 Deviare는 터미널 세션을 연결할 수 없습니다. 또한 이 라이브러리는 서비스로 실행될 수 없습니다.

이제 Deviare의 도움으로 API 함수 호출을 연결하는 방법을 살펴보겠습니다.

### API 후킹을 위해 Nektra Deviare 사용

라이브러리를 사용하려면 먼저 라이브러리에 대한 바이너리를 빌드하고 이러한 바이너리를 사용하도록 프로젝트를 설정해야 합니다. 

먼저 DLL에 다음 코드를 추가합니다.

씨

```
// Allow calls to Deviare functions
#include <NktHookLib.h>

static struct {
    SIZE_T nHookId;
    _NtQueryDirectoryFile trueNtQueryDirectoryFile;
} sNtQueryDirectoryFile_Hook = { 0, NULL };

static CNktHookLib cHookMgr;
```

아래 코드를 사용하면 후크를 설치할 수 있습니다.

씨

```
cHookMgr.SetEnableDebugOutput(TRUE);

    HINSTANCE ntdllDLL = NktHookLibHelpers::GetModuleBaseAddress(L"ntdll.dll");
    if (ntdllDLL == NULL)
    {
        OutputDebugStringW(L"DeviarePlugin.dll:HookFunction - Error: Cannot get handle of ntdll.dll");
        return 1;
    }

    LPVOID NtQueryDirectoryFile = NktHookLibHelpers::GetProcedureAddress(ntdllDLL, "NtQueryDirectoryFile");
    if (NtQueryDirectoryFile == NULL)
    {
        OutputDebugStringW(L"DeviarePlugin.dll:HookFunction - Error: Cannot get address of NtQueryDirectoryFile");
        return 1;
    }

    HRESULT dwOsErr = cHookMgr.Hook(&(sNtQueryDirectoryFile_Hook.nHookId), (LPVOID*)&(sNtQueryDirectoryFile_Hook.trueNtQueryDirectoryFile),
        NtQueryDirectoryFile, HookNtQueryDirectoryFile, NKTHOOKLIB_DisallowReentrancy);
```

후크를 제거하려면 다음 기능을 사용할 수 있습니다.

씨

```
cHookMgr.Unhook(sNtQueryDirectoryFile_Hook.nHookId);
```

실행 프로그램이 대상 프로세스에 DLL을 주입하도록 이 함수를 사용할 수 있습니다.

씨

```
DWORD dwOsErr = NktHookLibHelpers::CreateProcessWithDllW(exeNameW, NULL, NULL, NULL, FALSE,
        0, NULL, NULL, &sSiW, &sPi, dllToInjectNameW.c_str(), NULL, NULL);
```

마지막으로, 이 기사에서 개괄적으로 살펴볼 마지막 라이브러리인 Mhook로 이동해 보겠습니다.

## 무크

[Mhook은](https://github.com/apriorit/mhook) 애플리케이션 흐름에 코드를 삽입하는 데 사용할 수 있는 오픈 소스 API 후킹 라이브러리입니다. 원래 Marton Anka가 Microsoft Detours의 무료 대안으로 만든 Mhook은 트램폴린 후킹을 사용하여 API 함수 호출을 연결합니다. 

Apriorit 전문가가 만든 Mhook 라이브러리의 인기 포크가 있습니다. 이 글에서는 포크된 버전으로 작업하는 방법을 설명합니다.

이 라이브러리의 주요 장점과 단점을 살펴보겠습니다.

**장점:** Mhook은 MIT 라이선스에 따라 무료로 배포되며 x86 및 x64 아키텍처를 모두 지원합니다.

높은 수준의 사용자 정의를 제공하고 중요하지 않은 작업에 사용할 수 있는 효율적인 오픈 소스 라이브러리입니다.

Microsoft Detours와 달리 Mhook은 실행 중인 애플리케이션에서 후크를 설정하고 제거할 수 있습니다.

Mhook의 포크 버전은 Apriorit 전문가에 의해 지속적으로 개선되고 유지관리됩니다.

**단점:** Mhook은 트램펄린의 메모리 관리에 능숙하지 않습니다. 라이브러리는 세트 후크당 **VirtualAlloc** 에 대한 한 번의 호출을 사용합니다. 그리고 모든 후크에는 100바이트 미만의 저장 공간이 필요하지만 **VirtualAlloc** 함수는 Mhook이 호출할 때마다 프로세스 가상 주소 공간에서 64KB를 할당하므로 이는 매우 비효율적입니다. 

또한 읽어 보세요:
[Mhook 향상: 10배 속도 개선 및 기타 수정 사항](https://www.apriorit.com/dev-blog/469-mhook-enhancements)

### API 후킹에 Mhook 사용

Mhook 라이브러리를 사용하려면 [Apriorit GitHub 저장소](https://github.com/apriorit/mhook) 에서 프로젝트를 복제하고 빌드한 후 이 라이브러리를 사용하도록 프로젝트를 설정합니다.

Mhook 작업을 시작하려면 DLL에 다음 코드를 추가합니다.

씨

```
#include <mhook.h>

BOOL APIENTRY DllMain(
    HINSTANCE hinstDLL,
    DWORD fdwReason,
    LPVOID lpvReserved)
{
    BOOL result = FALSE;
    
    switch (fdwReason)
    {
    case DLL_PROCESS_ATTACH:
        result = HookFunction();
        if (result)
        {
            OutputDebugStringW(L"MhookHideFile.dll:DllMain - DLL_PROCESS_ATTACH: Successfully set hook");
        }
        else
        {
            OutputDebugStringW(L"MhookHideFile.dll:DllMain - DLL_PROCESS_ATTACH: Error: Failed to set hook.");
        }
        break;
    case DLL_PROCESS_DETACH:
        UnhookFunction();
…
```

후크를 설치하려면 **BOOL HookFunction** 함수를 사용합니다.

씨

```
BOOL HookFunction()
{
    // Set the hook for the NtQueryDirectoryFile function.
    return Mhook_SetHook((PVOID*)&TrueNtQueryDirectoryFile, HookNtQueryDirectoryFile);
}
```

씨

```
BOOL UnhookFunction()
{
    // Unhook the NtQueryDirectoryFile function.
    return Mhook_Unhook((PVOID*)&TrueNtQueryDirectoryFile);
}
```

그리고 그게 다야. [Apriorit GitHub 페이지](https://github.com/apriorit/APIHookingLibraries) 에 설명된 각 라이브러리를 사용하여 프로젝트의 소스 코드를 다운로드하고 검사할 수 있습니다 .

요약하자면, API 후킹이 필요한 프로젝트에 중요할 수 있는 주요 요소에 대해 이 네 가지 라이브러리를 비교해 보겠습니다.

## API 후킹 라이브러리 비교 요약

API 후킹 라이브러리를 비교하는 것은 서로 다른 목적으로 생성되고 서로 다른 기술에 의존하기 때문에 어려운 작업입니다. 그러나 우리는 귀하의 프로젝트에 가장 적합한 라이브러리를 결정하는 데 도움이 될 수 있는 포괄적인 비교표를 구성했습니다. 

예를 들어 속도를 비교하기 위해 프로세스의 스레드 수에 따라 각 라이브러리가 후크를 설정하고 제거하는 데 걸리는 시간을 측정했습니다. 결과적으로 스레드가 10,000개인 무거운 애플리케이션에서는 Detours가 가장 좋은 성능을 발휘했다는 결론을 내릴 수 있습니다.

아래의 전체 API 후킹 라이브러리 비교 요약을 참조하세요.

![Mhook, Detours, Deviare, EasyHook 비교](https://www.apriorit.com/wp-content/uploads/2022/11/figure-2-6.png)

우리의 경험을 바탕으로 소규모 API 후킹 프로젝트에는 Detours 및 EasyHook을 사용하는 것이 좋습니다. 이러한 라이브러리를 사용하면 후크를 빠르게 설치할 수 있으며 이를 사용하는 개발자의 광범위한 경험이 필요하지 않습니다.

높은 안정성, 효율성 및 사용자 정의가 필요한 대규모 프로젝트의 경우 Deviare 또는 Mhook을 사용하는 것이 더 적합합니다.

관련 서비스

[C/C++로 소프트웨어 개발 아웃소싱](https://www.apriorit.com/dev-blog/competences/c-c-sharp-obj-c-programming)

## 결론

API 후크를 설정함으로써 개발자는 시스템 동작을 변경하고 구현하려는 기능을 추가할 수 있습니다. API 후킹 라이브러리를 사용하면 API 함수 호출에서 후크를 더 쉽게 설정하고 제거할 수 있습니다.

이 기사에서는 Microsoft Detours, EasyHook, Nektra Deviare 및 Mhook와 같은 최고의 API 후킹 라이브러리를 사용하여 후크를 설치 및 제거하는 방법을 설명했습니다. 우리는 이를 필수 기준에 따라 비교하고 이러한 각 API 후킹 라이브러리를 사용하여 **NTAPI 가 호출하는** **Ntdll.dll!NtQueryDirectoryFile을** 가로채는 방법을 보여주었습니다 .

Apriorit에는 소프트웨어 솔루션의 보안과 성능을 향상시키는 데 도움을 줄 수 있는 경험이 풍부하고 유능한 [시스템 프로그래밍 전문가가](https://www.apriorit.com/our-expertise/system-management) 있습니다 .