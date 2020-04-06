# DLL Injection

참조: AppInit_DLLs.md

출처: https://gwangyi.github.io/posts/dll-injection-by-createremotethread/

## CreateRemoteThread로 DLL Injection하기

## Introduction [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#introduction)

우리가 컴퓨터를 사용하는 이유는, 컴퓨터를 통해 내가 직면한 문제를 해결하는데에 그 근본이 있을 것이다. 그 문제는 암산으로 해결하기엔 너무 복잡한 수학 문제의 답을 구하는 것일 수도 있고, 손으로 한땀 한땀 하기에는 너무나 귀찮은 일을 자동화하는 것일 수도 있고, 심심함을 해소하려는 것일 수도 있다.
컴퓨터를 통해 문제를 해결한다는 것은, 그 문제를 해결할 수 있는 프로그램을 구해서 적절한 사용을 통해 프로그램이 문제를 해결하도록 하는 것이다. 문제를 해결하는데는 하나의 프로그램으로 충분할 수도 있지만, 여러 개의 프로그램을 같이 이용해야 하거나 프로그램을 이용해 또 다른 프로그램을 만들어 해결해야 할 때도 있다.
때론 기존의 프로그램을 거의 그대로 사용하면서 필요한 기능 일부만 추가하면 간단히 해결할 수 있을 때도 많다. 오픈소스 프로그램이라면 그럴 때는 소스코드를 내려받아서 필요한 부분을 패치해 쓰면 되지만, 세상 만사가 어찌 그리 쉬울까. 우리가 만나는 대부분의 프로그램은 클로즈드 소스 형태로 배포되는 경우가 많아 일이 그렇게 쉽게만 풀리지는 않는다.

대상 프로그램에 내가 원하는 코드를 주입해서 그 실행 흐름을 바꾸는 일은 해킹에 해당되는 일이다. 해커들은 온갖 방법을 동원해서 대상이 내가 원하는 일을 *실수로* 실행해주기를 바란다. 그리고 프로그램을 개발하는 개발자들은, 자신이 모르는 방법을 통해 예상하지 못한 일을 하는 프로그램을 매우 싫어한다. 그래서 개발자는 이런 것을 가능하게 하는 부분을 최대한 많이 찾아 막아버리고, 해커는 그걸 어떻게든 피해서 원하는 코드를 우겨넣고야 마는 전쟁이 지금까지도 계속되고 있는 것이다.

이 포스팅에서는 DLL Injection이라고 불리는 기법을 소개해보려고 한다. 그 중에서도 특별히 [`CreateRemoteThread()`](https://docs.microsoft.com/en-us/windows/desktop/api/processthreadsapi/nf-processthreadsapi-createremotethread) API를 이용해서 DLL Injection을 수행하는 과정을 소개하도록 하겠다. DLL Injection을 통해 여러 가지 재미있는 일을 해볼 수 있을 것이다.

## Background [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#background)

옛날 옛적 DOS시절에는 프로세스간 메모리 영역이 분리되어있지 않았다. 즉, 서로 다른 프로세스의 메모리는 서로 다른 주소를 가지고 배치되었고, 같은 메모리 주소라면 실제로 같은 물리적인 메모리를 참조할 수 있었다. 이 때는 그냥 '램 상주' 형태로 유틸리티를 메모리에 올려놓고, 특정 인터럽트가 발생하면 유틸리티의 코드가 실행되도록 설정해놓으면 대상 프로그램이 한참 실행되다가 원하는 유틸리티 프로그램의 코드를 실행하도록 할 수 있었다. 그러면 유틸리티는 내가 원하는 동작을 대상 프로그램의 메모리에다 대고 이것 저것을 한 뒤에 빠져나오면 뭐든 할 수 있었던 시절이었다. 그 시절에 게임 좀 해본 사람은 다들 알 Game Wizard 같은 유틸리티가 그런 유틸리티에 속했다.

하지만 윈도우즈가 널리 쓰이게 되면서, 프로세스간 메모리 영역이 분리되기 시작하자 예전과 같은 단순한 방법으로는 재미를 보기 어려워졌다. 단순히 아무 메모리 주소나 잡는다고 해서 다른 프로세스의 메모리 영역이 보이지 않게 된 것이다. 그렇다고 해서 다른 프로세스의 메모리에 아주 접근할 수 없게 해버리면 여러가지 문제가 있기 때문에 몇 가지의 뒷구멍이 존재했고, 이 것을 이용해 예전과 같은 재미있는 일들을 어느 정도 할 수 있었다.

### Debugging API [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#debugging-api)

아무리 윈도우즈가 됐고 프로세스 메모리를 분리한다고 해도, 디버깅이라는 작업은 예전이나 지금이나 가능해야 했다. 만약 프로세스끼리 전혀 접근이 되지 않는다면, 디버깅을 위해선 커널을 붙들던지 CPU에 디버깅용 하드웨어를 직접 갖다붙이던지 해야 간신히 할 수 있을 것이다. 그래서 윈도우즈에서는 [Debugging API](https://docs.microsoft.com/en-us/windows/desktop/debug/debugging-functions)를 제공하여 디버거 프로세스가 디버기 프로세스를 쥐고 흔들 수 있게 해 두었다.

[Debugging API](https://docs.microsoft.com/en-us/windows/desktop/debug/debugging-functions)가 많은 것을 할 수 있지만 몇 가지 확실한 한계가 있다. 일단 디버거가 붙어있는지 여부를 디버기 입장에서 확인하기가 쉽고, 많은 양의 코드를 집어넣는 것이 이것만으로는 복잡한 감이 있다.

그럼에도 불구하고, 기본적인 메모리 읽기/쓰기 동작은 [Debugging API](https://docs.microsoft.com/en-us/windows/desktop/debug/debugging-functions)에서 제공하는 것이 간단하기 때문에 밑에 소개할 다른 방법에서도 가져다 쓸 것이다.

### Registry [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#registry)

윈도우즈에는 어플리케이션이 실행될 때 자동으로 특정 DLL이 로딩되게 하는 기능이 들어 있다. 김 빠질 정도로 간단하게 원하는 실행 코드가 들어 있는 DLL을 삽입시킬 수 있지만, 이 것도 단점이 있다. 시스템 전체에 걸쳐서 어떤 기능을 추가하거나 감시하는 정상적인 프로그램을 위한 기능이지만, 일반적으로 평소엔 사용하지 않는 기능이고, 우리와 같이 *재밌는 일* 을 하기 위해 이 기능을 이용하면 위험하기 때문에, 웬만한 백신 프로그램은 저 항목을 보이는 즉시 지워버릴 것이다. 게다가, 최신 윈도우즈에서는 서명된 DLL만 들어갈 수 있기 때문에, 아무 DLL에 해당하는 우리의 재밌는 DLL은 거부되어 버릴 것이 분명하다.

이에 해당되는 레지스트리 주소는 다음과 같다.

- `HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows\AppInit_DLLs`: user32.dll이 로딩될 때 함께 로딩됨
- `HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\AppCertDLLs`: 새로운 프로세스가 실행될 때 로딩됨

참고로 일반적인 시스템에는 저런 곳에 뭔가가 들어있지 않기 때문에 저런게 있다면 뭔가 문제가 생겼다고 생각해도 크게 틀리지 않다.

### CreateRemoteThread [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#createremotethread)

[`CreateRemoteThread()`](https://docs.microsoft.com/en-us/windows/desktop/api/processthreadsapi/nf-processthreadsapi-createremotethread) 함수는 스레드를 생성하는 함수인데, 저 함수를 호출하는 프로세스가 아닌 다른 프로세스에 스레드를 생성하는 기능을 가지고 있다. 여기서 재미있는 점은 DLL을 읽어서 실행가능한 영역에 적재하는 함수인 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수가 스레드 프로시저와 비슷한 모양을 가지고 있다는 것이다. [`CreateRemoteThread()`](https://docs.microsoft.com/en-us/windows/desktop/api/processthreadsapi/nf-processthreadsapi-createremotethread)를 활용하는 DLL injection은, 바로 이 점을 이용해서 원하는 DLL을 적재하는 스레드를 생성시켜 대상 프로세스에 나의 코드를 주입하는 방법이다.

### Hook [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#hook)

[`SetWindowsHookEx()`](https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-setwindowshookexa) 함수를 이용하면 좀 더 깔끔하게 API를 상대 프로세스에 주입하는 것이 가능하다. 메시지 루프가 돌 때 이 함수를 통해 집어넣은 훅이 동작하게끔 해주는 함수인데, 그 동작을 위해서 OS에서 직접 훅을 박아넣어 주기 때문에 웬만하면 무리없이 동작할 수 있다.
다만 메시지 루프가 돌아야 동작을 한다는 점 때문에, 메시지 루프가 없으면 소용이 없다는 문제가 있다. 그 외에도 주입 시점을 지정하기 어렵다거나, 이것저것 가리지 않고 훅이 박혀버린다거나 하는 단점이 있어 개인적으로 선호하지 않는다.

## Mechanism [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#mechanism)

스레드를 실행하기 위해 준비해야되는 프로시저는 다음과 같은 프로토타입을 가져야 한다.

```c
DWORD WINAPI ThreadProc(  _In_ LPVOID lpParameter);
```

그리고 우리가 호출하기 원하는 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수는 다음과 같이 생겨있다.

```c
HMODULE LoadLibrary(  LPCTSTR lpLibFileName);
```

리턴 값은 일단 무시하고, Win32API이므로 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수도 저기엔 생략되어 있지만 calling convension은 `__stdcall` 형식을 따른다. 그리고 패러미터로는 동일하게 포인터 형식의 패러미터 하나만을 갖는다. 그렇다면 스레드를 생성하면서 그 시작 주소를 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)로 지정하면 원하는 DLL을 로드할 수 있을 것이다.

이 때 패러미터로 원하는 DLL 파일의 위치를 지정해주어야 하는데, 무턱대고 스트링을 패러미터로 주게 되면 반드시 실패하게 된다. 원인은 간단한데, 스레드를 생성할 때 패러미터를 넘겨주기는 하는데 주소값만 넘어가기 때문이다. 같은 프로세스상에서 작업하고 있다면 메모리 주소공간이 동일하니 부모 스레드에서 보고 있는 주소와 생성된 스레드에서 보는 주소가 같은 물리적 위치를 가리키고 있을 것이기 때문에 문제가 되지 않지만, 지금처럼 다른 프로세스에 스레드를 생성하게 된다면 부모 스레드가 보고 있는 주소값을 대상 프로세스에서 생성된 스레드가 접근해 보면 전혀 엉뚱한 값이 써져 있는 사태가 벌어지게 된다.

이를 해결하려면 두 가지 방법이 있는데, 하나는 대상 프로세스의 메모리 어딘가에 써진 문자열을 이용하는 방법이 되겠다. 예를 들어 모든 윈도우즈 프로그램은 `kernel32.dll` 파일을 불러와야 하기 때문에 프로그램의 어딘가에는 `kernel32.dll` 이라는 스트링이 박혀 있을 것이다. 집어넣으려는 dll 파일의 이름이 `ernel32.dll`이라면, `kernel32.dll`이 써진 주소에서 한 바이트만 밀어서 넘겨주게 되면, [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수의 패러미터로 `ernel32.dll`을 전달할 수 있을 것이다.
다른 한 가지는 대상 프로세스 내에 메모리를 [`VirtualAllocEx()`](https://msdn.microsoft.com/ko-kr/library/windows/desktop/aa366890(v=vs.85).aspx)를 이용해 할당받은 뒤, 그 메모리에 [Debugging API](https://docs.microsoft.com/en-us/windows/desktop/debug/debugging-functions)중 하나인 [`WriteProcessMemory()`](https://msdn.microsoft.com/en-us/library/ms681674(v=VS.85).aspx)를 이용해 원하는 DLL의 주소를 써넣어서 쓰는 것이다. 좀 더 유연한 대응을 할 수 있지만, [Debugging API](https://docs.microsoft.com/en-us/windows/desktop/debug/debugging-functions)를 사용해야 하기 때문에 권한의 문제가 있다. 하지만 후자가 일반적인 경우 더 단순하므로 후자를 택하도록 하자.

위의 문제를 해결하는 것을 제대로 이해했다면 여기서 의문점이 하나 생길텐데, **대상 프로세스의 LoadLibrary 주소는 어떻게 알 수 있는가?** 하는 문제가 생긴다. 여기서 윈도 DLL의 특징이 하나 나타나는데, 바로 *선호하는 Base Address*라는 특성이다.
윈도의 DLL은 성능 최적화를 위해서 선호하는 Base Address라는 것이 존재한다. 특별한 일이 없다면 DLL이 로드될 때는 내가 미리 지정한 주소에 로드되도록 하는 것인데, 이는 base address가 변경되면 DLL을 불러올 때 절대주소로 참조한 주소들을 전부 고쳐 써야 정상 동작을 하게 될 것이기 때문에 반드시 고쳐쓰는 작업이 들어가게 되고, 이는 엄청난 오버헤드로 작용한다. 거기에 특별히, 몇몇 핵심 DLL은 커널과도 연관성이 있어 주소가 바뀌면 절대로 안되는 DLL들이 있다. [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)는 바로 그 DLL중 하나인 `kernel32.dll`의 함수이기 때문에 내 프로세스에 있는 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수의 주소와 대상 프로세스에 있는 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수의 주소는 동일하다고 봐도 무방하기 때문에 패러미터를 준비하는 것보다 훨씬 쉽다.

무사히 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)가 호출되었으면 DLL의 `DllMain()` 함수가 호출된다. 이를 이용해 내가 원하는 작업들을 해주면, *재밌는* 일을 하기 위한 준비가 끝난다.

## x86_64/WOW64 Problem [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#x86_64%2Fwow64-problem)

앞에서 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)의 주소가 프로세스마다 모두 동일하다고 주장했는데, 사실 그렇지가 못하다. 물론 32bit OS에서는 저 것이 참이지만, 64bit OS부터는 미묘한 문제가 있어 신경쓸 필요가 있다.
윈도는 특이하게도, 64bit OS상에서도 32bit executable을 실행하는데 문제가 거의 없다. 리눅스의 경우 i386용 라이브러리들을 깔아주지 않으면 x64 어플리케이션만 작동하고 i386은 작동할 수 없는데 비해, 윈도는 i386용 라이브러리들이 이미 OS에 다 포함되어 있어 특별한 조작 없이 i386 바이너리를 실행할 수 있다.
별 문제 없이 i386과 x86_64 바이너리가 잘 동작하기 때문에 놓치기 쉬운데, DLL이 실행가능한 코드를 담고 있기 때문에 작은 문제가 하나 생긴다. i386과 x64가 아무리 호환이 된다고 해도 그냥 단순히 섞어놓을 수 있는 것은 아니어서, x64용 DLL과 i386용 DLL이 서로 교차사용이 불가능하다. 즉, x64용 바이너리에서 실행중인 i386 바이너리의 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)의 주소를 얻는 일이 단순하게 [`GetProcAddress()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-getprocaddress) 만으로 해결되지 않는다.

이를 해결하기 위해서는 [`EnumProcessModulesEx()`](https://docs.microsoft.com/en-us/windows/desktop/api/psapi/nf-psapi-enumprocessmodulesex) 함수를 통해 `kernel32.dll`의 Base Address를 얻어내고, `kernel32.dll`에서 PE 파일을 분석해 Export Address Table로부터 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)의 시작 주소를 얻어와야 할 필요가 있다. 이걸 다 다루면 포스팅이 너무 길어지므로, 다음으로 미루도록 하겠다.

## Result [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#result)

수 년 전에 짰던 코드인데, 요즘도 큰 문제 없이 동작하는 코드이다.

```c
hProc = OpenProcess(PROCESS_ALL_ACCESS, FALSE, dwPID);
if(hProc == NULL)
{
    MessageBox(hWnd, TEXT("Failed to inject DLL!\nCannot open the process"), NULL, MB_ICONERROR);
    break;
}
pRemoteDll = VirtualAllocEx(hProc, NULL, sizeof(char) * MAX_PATH, MEM_COMMIT, PAGE_READWRITE);
if(!pRemoteDll)
{
    CloseHandle(hProc);
    MessageBox(hWnd, TEXT("Failed to inject DLL!\nCannot allocate memory in target process"), NULL, MB_ICONERROR);
    break;
}
if(!WriteProcessMemory(hProc, pRemoteDll, szDllName, sizeof(szDllName), NULL))
{
    VirtualFreeEx(hProc, pRemoteDll, 0, MEM_RELEASE);
    CloseHandle(hProc);
    MessageBox(hWnd, TEXT("Failed to inject DLL!\nCannot write memory in target process"), NULL, MB_ICONERROR);
    break;
}
hKernel32 = LoadLibrary(TEXT("KERNEL32.DLL"));
pfnLoadLibrary = (LPTHREAD_START_ROUTINE)GetProcAddress(hKernel32, TEXT("LoadLibraryA"));
hThread = CreateRemoteThread(hProc, NULL, 0, pfnLoadLibrary, pRemoteDll, 0, NULL);
if(!hThread)
{
    VirtualFreeEx(hProc, pRemoteDll, 0, MEM_RELEASE);
    CloseHandle(hProc);
    FreeLibrary(hKernel32);
    MessageBox(hWnd, TEXT("Failed to inject DLL!\nCannot create remote thread"), NULL, MB_ICONERROR);
    break;
}
WaitForSingleObject(hThread, INFINITE);
VirtualFreeEx(hProc, pRemoteDll, 0, MEM_RELEASE);
CloseHandle(hProc);
FreeLibrary(hKernel32);
if(GetExitCodeThread(hThread, &dwRet))
{
    TCHAR out[1024];
    _stprintf(out, TEXT("Injection finished.\nExit code: 0x%08lX"), dwRet);
    MessageBox(hWnd, out, TEXT("Finished"), MB_ICONINFORMATION);
}
```

순서대로

1. dwPID를 PID로 가진 프로세스를 열고 (`OpenProcess()`)
2. 해당 프로세스에 DLL 주소를 적어넣을 메모리를 할당하고 (`VirtualAllocEx()`)
3. DLL 주소를 써넣은 다음 (`WriteProcessMemory()`)
4. `LoadLibraryA()` 함수의 주소를 얻어오고 (`GetProcAddress()`)
5. `LoadLibraryA()`를 스레드 프로시저로 하는 스레드를 생성한다.

이 코드에서는 32/64bit 문제를 건드리지 않았기 때문에 32/64bit 각각 버전으로 빌드를 따로해야된다.
참고로 스트링을 받는 Win32API 함수는 대부분 A버전과 W버전 두 가지가 있고 UNICODE가 정의됐는지 여부에 따라 A나 W로 define되어있는 A/W가 붙지 않은 함수가 존재한다. A/W가 붙지 않은 버전은 매크로로 구현되어 있기 때문에 실제 symbol이 없어서 이 때는 A가 붙은 것으로 제한해서 사용했다.

### Injecting Dll [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#injecting-dll)

실행시키고 싶은 코드는 Dll의 형태로 만들어서 [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya) 함수를 통해 대상 프로세스에서 로딩시킬 것이다. 앞에서 언급했듯, [`LoadLibrary()`](https://docs.microsoft.com/en-us/windows/desktop/api/libloaderapi/nf-libloaderapi-loadlibrarya)가 호출되면 `DllMain()`이 호출되는데, 이 함수는 다음 형태처럼 생겨야 한다.

```c
BOOL WINAPI DllMain(
  _In_ HINSTANCE hinstDLL,
  _In_ DWORD     fdwReason,
  _In_ LPVOID    lpvReserved
);
```

이 함수는 dll이 적재되는 시점 뿐만 아니라 여러 시점에서 호출되는데, 실행시키고 싶은 코드를 여기다 넣게 되면 injection과 동시에 실행이 돼서 좋기는 하지만 저 모든 시점에서 실행되기 때문에 잘 구분할 필요가 있다.
각 시점은 `fdwReason` 패러미터를 통해 구분할 수 있다. 이 중 처음 적재될 때는 `DLL_PROCESS_ATTACH` 값을 가지므로, 이 때만 실행되게 하면 되겠다. 이 함수가 `FALSE`를 반환하면 적재 도중 오류가 발생한 것으로 취급하여 `LoadLibrary()`는 실패하고 0을 반환하게 된다. `TRUE`를 반환하면 적재가 성공하여 대상 프로세스에 dll이 성공적으로 안착하게 된다.
여기서 두 가지 활용방안이 있는데, 첫째는 교과서적으로 `TRUE`를 반환하여 성공적으로 로드되는 것이다. 이 때 장점은 dll이 성공적으로 프로세스에 로딩되어 지속적인 효과를 줄 수 있다는 것이고, 단점은 dll이 이미 로드된 상태라 다시 injection을 할 수 없다는 것과, dll이 정상적인 방법으로 로딩된 것이 아니다보니, 대상 프로세스가 죽을 때까지 내려오지 않아 dll을 수정하는 것이 불가능하다는 것이다. 이 것은 injection될 dll을 계속 수정하면서 작업해야 할 때 치명적인 문제가 될 수 있는데, 물론 `LoadLibrary()`를 사용했던 것과 비슷한 방법으로 `FreeLibrary()`를 remote thread로 박아넣어 언로드시킬 수도 있다. 그러나 이 때는 언로드될 수 있도록 리소스를 비롯한 많은 것들을 원상복구하는 것이 반드시 필요하며, 적재가 해제될 때 `DLL_PROCESS_DETACH`로 호출되는 `DllMain()`에서 잘 관리해줘야만 한다. 이 때 되돌리는 것을 제대로 하지 않으면 `FreeLibrary()`가 실패하거나 대상 프로세스가 죽어버릴 수도 있으므로 조심해서 자원들을 풀어주도록 하자.
두 번째는 `FALSE`를 의도적으로 리턴하여 dll이 적재되지 않게 할 수도 있다. 코드는 실행되었지만 실제로 dll이 로드된 것은 아니므로, 여러번 다시 inject가 가능하다. 다만 `DllMain()`이 호출된 상태는 `LoadLibrary()`가 실행중인 상태이므로 여러 가지 제약사항이 있는 상태이다. `LoadLibrary()` 중에 또 `LoadLibrary()`를 호출하게 됐을 경우, MSDN에서는 데드락이 발생할 수 있으므로 절대 하지 말라고 경고되어 있다. 그 외에도 정상적인 상태라 할 수 없어 간단한 코드 정도는 그렇게 실행할 수 있겠지만, 좀 복잡해지거나 지속시간을 가져야 하는 경우라면 스레드를 따로 생성해서 거기서 처리하는 것을 추천한다. 물론 이 경우는 `FALSE`를 일부러 리턴해 강제로 로딩을 막는 방법은 사용할 수 없을 것이다.

예시는 다음과 같다.

```c
#include <windows.h>

BOOL WINAPI DllMain(HINSTANCE hInstDll, DWORD fdwReason, LPVOID lpvReserved) {
    if (fdwReason == DLL_PROCESS_ATTACH) {
        MessageBoxA(NULL, "Hello, world!", "I'm Injected", MB_OK);
    }
    return TRUE;
}
```

## Future Work [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#future-work)

32/64bit 문제를 해결하는 길은 또 DLL Injection 포스팅 전체 내용만큼이나 길 것 같아 다음 포스팅으로 미루도록 하겠다.
그리고 추후에 DLL Injection을 통한 쿠키 공유 시스템(Internet Explorer의 쿠키를 뽑아 다른 브라우저에 이식하는)에 대해 정리할 예정이다.

## Conclusion [#](https://gwangyi.github.io/posts/dll-injection-by-createremotethread/#conclusion)

DLL Injection은 상당히 강력한 도구 중 하나이다. `CreateRemoteThread()` API를 이용하면 DLL Injection을 간단하게 수행해볼 수 있다.