# Windows API Hook

참고: https://learn.microsoft.com/ko-kr/windows/win32/winmsg/hooks



출처: https://wendys.tistory.com/96

### 1. C/C++ 윈도우 메시지 가로채기 기법(windows message hooking)

윈도우 개발 기법 중 하나가 후킹이라는 기법이 있습니다. 생각보다 간단하게 제어를 할 수 있다는 장점이 있습니다.  거기다가 해당 기법은 MSDN에서 제공되는 공식적인 기술이기 때문에 MSDN 문서도 존재합니다.

https://docs.microsoft.com/en-us/windows/win32/winmsg/hooks

### Windows Message Hooking?

후킹이 뭐냐 하면 내가 만든 프로그램을 시스템 또는 다른 프로그램에 SPY로 심어두는 것을 말합니다.

예를 들어 마우스 메시지를 후킹 한다고 했을 때 마우스의 클릭이벤트 메시지가 발생한 경우 SPY를 통해 먼저 알아채고 시스템으로 전달하는 것입니다.

이때 만약 Left Click Message를 Right Click Message로 다른 정보를 전달한다던지 아예 클릭을 안 했다고 시스템에 전달을 하는 등의 행위를 할 수 있도록 하는 기법 이 바로 후킹입니다.

마우스 메시지뿐만 아니라 키보드 메시지 후킹을 하여 키로그를 작성한다던지, 기타 메시지를 모두 후킹 하여 처리를 할 수도 있습니다.

 

![img](https://blog.kakaocdn.net/dn/vTDif/btqAxhmeEty/cenXL8qQ2bUmHTvfxYY1K0/img.jpg)

### WIndows Message Hooking 활용 범위

개발자라면 많이들 사용하는 SPY++ 프로그램 등이 해당 기법을 이용하여 개발되어있습니다.

그리고 제가 만들어 사용중인 마우스 제스처 프로그램이 있습니다. 이부분은 추후 차근차근 설명 드리며 함께 만들어보겠습니다.

 

### 주의사항

위의 그림에서 보면 알 수 있듯이 OS의 Message Queue와 Application Message Queue 사이에서 행위를 하기 때문에 `EXE에서 구현하는 형태가 아닌 DLL에서 후킹이 구현되어야 합니다.`

즉, DLL을 일반적으로 DLL을 로드해야 하기에 EXE, DLL 모두 필요하게 됩니다.

 

### SetWindowsHookExA

``` c++
HHOOK SetWindowsHookExA( // Hooking 설치
  __in int idHook, // HOOK TYPE
  __in HOOKPROC lpfn, // HOOK PROCEDURE
  __in HINSTANCE hmod, // DLL INSTANCE HANDLE
  __in DWORD dwThreadId
  );

BOOL UnhookWindowsHookEx( // Hooking 해제
  __in HHOOK hhk
  );
```

EXE에서는 DLL을 LOAD하기만 하고 실제 DLL에서 SetWindowsHookExA API를 호출하여 후킹을 설치하게 됩니다.

만약 작업이 끝났다면 UnhookWindowsHookEx를 이용하여 반드시 언훅을 하도록 합니다.

 

### 2. 윈도우 후킹을 하기 전에 알아야 할 DLL Main에서 하면 안되는 5가지 작업

### WIndows Message Hooking 하기에 앞서 DLL Main에서 하면 안 되는 작업

최근 주력 업무 및 시스템 분석을 하게 되면서 DLL Main 에서 몇 가지 동작들에 의해서 문제가 발생한 적이 많이 있어서 정확하게 정리를 했습니다.

후킹을 하기위해 `DLL을 로드했을 때 보장되어있는 상황은 Kernel32.dll 이 로드되어있다는 보장` 외에는 보장된 상황이 없습니다.

그렇다면 어떠한 상황들에서 어떠한 문제가 생길까요??

가장 큰 문제는 해당 DLL에 `데드락(Dead Lock)`이 걸리게 되어 더 이상 흐림이 진행되지 못하여 프로그램이 실행되지 못하는 상황이 발생할 수 있습니다.

### 주의사항

- LoadLibrary, LoadLibraryEx 등을 호출하게되면 데드락 또는 크래시를 유발할 수 있습니다.
- CoCreateInstanceEx를 사용하여 COM Thread를 초기화하는 경우 특정 상황에서 LoadLibraryEx가 호출될 수 있습니다.
- 다른 스레드와의 동기화 처리는 데드락을 유발할 수 있습니다.
- 로더 락을 획득하려는 코드가 가지고있는 동기화 오브젝트를 획득하게 되면 데드락을 유발할 수 있습니다.
- CreateProcess 프로세스를 새로 생성하는 경우 다른 DLL을 로드할 수 있습니다.

```
가장 좋은건 DLL Main에선 깔끔하게 비워두는 것이 좋습니다.
```

하지만 메시지 후킹을 넘어 API 후킹을 하게되면 DLL Main 타이밍에 처리해야 할 일들이 생기기 때문에 여러 가지 상황에 주의하여 개발을 해야 합니다.



### 3. 윈도우 메시지 후킹 마우스를 지배하는자 (windows mouse message hooking)

### 실전 윈도우 메시지 후킹 - 마우스를 지배하는 자

이제부터 실전입니다. 메시지 후킹에는 마우스 메시지, 키보드 메시지, 그 외 모든 윈도우 메시지를 가로챌 수 있습니다.

예를 들면 키보드 메시지 후킹을 이용한 기술은 키로거를 만들기도 하고, 매크로 키보드를 만들기도 하며, 특정 키보드 키를 바꾸기도 합니다.

그럼 마우스 후킹으로는 무엇을 할 수 있을까요?? 특별히 많은걸 할 수 있지는 않습니다. 원하는 경우 오토 클릭을 할 수 있을 테고, 아니면 마우스 움직임에 따라 동작하는 매크로 등이 있을 수 있습니다.

이번 포스팅에서는 마우스 움직임에 따라 스크롤을 내리거나 올리고 창을 닫는 등 마우스 제스처 매크로 프로그램을 만들어 볼 계획입니다. **(사실은 개인적으로 만들어서 사용하고 있는 툴 중 가장 만족도가 높으며, 기술도 다양하게 들어가기 때문에 많은 사람들에게 알려드리고 싶어요)**

 

### 마우스 메시지 후킹 시작

메시지 후킹은 이전에서도 말씀드린 바와 같이 `exe와 dll의 구조로 이루어져야 합니다.` 메시지 후킹뿐만 아니라 API 후킹 등을 할 때도 마찬가지죠.

그렇다면 프로젝트를 생성하는 것부터 간단하게 작성할 수 있는 코드와 동작들을 한번 보겠습니다.

 

프로젝트는 아예 따로 생성되어도 되고, 하나의 빈 프로젝트에 여러 개의 프로젝트를 추가 하여 개발하는 방법들이 존재합니다. 저는 하나의 빈 프로젝트에 관련있는 여러개의 프로젝트를 추가하는 방법을 선호하기 때문에 각자의 취향에 맞게 개발을 진행하면 되겠습니다.

 

![img](https://blog.kakaocdn.net/dn/loGbm/btqwWgkpBuu/PRPrbYD4F0uawGwpviKvTK/img.png)

![img](https://blog.kakaocdn.net/dn/qp1gg/btqwWZWyCoZ/l0d9Gbrow0KDaEXXOzV9P1/img.png)

 

저는 블랭크 프로젝트에 dll과 exe 프로젝트를 각각 추가했습니다.

샘플 프로젝트라서 이름을 저렇게 지정하였지만, 저렇게 되면 analog_note_dll.dll, analog_note_exe.exe라는 파일명이 생기기 때문에 프로젝트 성격에 맞는 이름으로 설정하시길 권장드립니다.

 

예전에는 기본 프로젝트를 생성하면 stdafx.h 가 있었는데 visual studio 2019가 되면서 pch.h로 변경된 듯합니다. 그전에 변경됐을 수도 있고요.. 이는 pre compile header의 약자로 프로젝트 설정에서 변경 가능합니다.

`project -> properties -> C/C++ -> Precompile Headers`에서 사용 여부와 파일명을 변경하면 됩니다.

 

![img](https://blog.kakaocdn.net/dn/SsIBG/btqwXGI0bos/8tTzYgz4KxvEEr3vGpkZLK/img.png)

 

프로젝트의 생성이 끝났으면 이제 개발을 시작하겠습니다.

기본 코드는 exe에서는 dll을 로드해주는 역할만 하면 되며, 모든 작업은 dll에서 하게 됩니다.

 

### analog_note_dll - dllmain.cpp

```cpp
BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                     )
{
    switch (ul_reason_for_call)
    {
    case DLL_PROCESS_ATTACH:
        if (hook_mouse_callback::instance()->attach(hModule)) {
            MessageBox(nullptr, L"hook success", L"analog_note", MB_OK);
        }
        else {
            MessageBox(nullptr, L"hook success", L"analog_note", MB_OK);
        }
 
        break;
 
    case DLL_THREAD_ATTACH:
        break;
 
    case DLL_THREAD_DETACH:
        if (hook_mouse_callback::instance()->attached()) {
            hook_mouse_callback::instance()->detach();
        }
        
        break;
 
    case DLL_PROCESS_DETACH:
        break;
    }
    return TRUE;
}
```

dll main의 코드입니다.

DLL_PROCESS_ATTACH 시점에 있는 hook_mouse_callback::instance() ? 이게 무엇일까요??

후킹에서는 콜백 함수가 사용되어야 하기 때문에 static function이 필요합니다.

그런데 static function을 사용하게 되면 전역 변수에는 접근이 쉽게 되지만 마약 이를 class로 구현하는 경우 member 변수에 접근이 힘들어지게 되어 모든 값을 전역 변수로 관리해야 하는 불편함에 놓이게 됩니다.

이런 부분을 좋아하지 않기 때문에 싱글톤 패턴을 이용하여 class로 구현을 하게 되었으며, 해당 방법을 선호하지 않는 경우 전역 변수를 이용하여 개발을 하여도 됩니다.

 

### analog_note_dll - hook_mouse_callback.hpp

```cpp
#pragma once
#include "pch.h"
 
class hook_mouse_callback {
private:
    static hook_mouse_callback* volatile _instance;
    static std::mutex _mutex;
 
    hook_mouse_callback();
    hook_mouse_callback(const hook_mouse_callback& other) {};
    ~hook_mouse_callback() {};
public:
    static hook_mouse_callback* volatile instance() {
        if (_instance == nullptr) {
 
            std::lock_guard<std::mutex> lock(_mutex);
 
            if (_instance == nullptr) {
                _instance = new hook_mouse_callback();
            }
 
            atexit(_finalize);
        }
        return _instance;
    }
 
    static void _finalize() {
        hook_mouse_callback::instance()->finalize();
 
        if (_instance) {
            delete _instance;
        }
    }
 
public:
 
    void finalize() {
 
    }
 
    bool attached() const {
        return _hook != nullptr;
    }
 
    bool attach(
        __in HINSTANCE module
        ) {
        _hook = SetWindowsHookExW(WH_MOUSE_LL, proxy_function, module, 0);
        return _hook != nullptr;
    }
 
    void detach() {
        if (attached()) {
            UnhookWindowsHookEx(_hook);
        }
    }
 
    static LRESULT __stdcall proxy_function(
        __in int code,
        __in WPARAM wparam,
        __in LPARAM lparam
        ) {
        return hook_mouse_callback::instance()->new_function(
            code,
            wparam,
            lparam);
    }
 
    LRESULT new_function(
        __in int code,
        __in WPARAM wparam,
        __in LPARAM lparam
        );
 
private:
    HHOOK _hook;
 
};
 
```

 

차근차근 봐보겠습니다. 클래스의 구조가 익숙하지 않으신 분들도 있을 것 같네요. 해당 클래스는 싱글톤으로 구현된 클래스입니다. 싱글톤이 뭔지 잘 모르시는 분들은 아래 링크를 통해서 간단하게라도 보시면 도움이 되실 거예요

https://wendys.tistory.com/12

위의 코드에서 attach 부분은 main에서 호출된 부분입니다.

정말 간단한 SetWIndowsHookExW만을 호출하고 있는데, 이때 parameter가 중요합니다.

 

### 첫 번째 중요한 paramter WH_XXX

첫 번째로 WH_XXX를 통해서 마우스 이벤트를 전달받을지, 키보드 이벤트를 받을지, 윈도 메시지를 받을지 결정되기 때문입니다.

현재 프로젝트에서는 마우스 메시지를 전달받기 위해서 WH_MOUSE_LL을 설정하고 있습니다.

근데 MSDN에 보면 WH_MOUSE와 WH_MOUSE_LL이 구분되어있는 것을 보실 수 있습니다.

 

https://docs.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-setwindowshookexa

WH_MOUSE는 특정 프로세스에서 마우스 이벤트를 전달받는 방법이고, WH_MOUSE_LL은 Low Level에서 전달받는 방법으로 마우스의 전역 이벤트를 전달받기 위해선 WH_MOUSE_LL을 설정하여야 합니다.

 

### 두 번째 중요한 parameter proxy_function

proxy_function이라고 명시한 parameter는 사실 함수 포인터를 삽입해야 하는 변수이기에 static function이어야 합니다.

하지만 class 내에 static function을 사용한 경우 member 변수, 함수 등을 사용할 수 없기 때문에 위에서 설명한 것처럼 singleton class를 이용하여 처리하는 방법 중 하나입니다.

즉, 호출된 static member function 내부에서 singleton을 이용하여 member function을 호출하는 기법입니다.

 

그렇다면 이제 마지막으로 구현만 하면 됩니다. proxy_function이 호출하고 있는 member function인 new_function에서 실제 구현을 하게 되면 손쉽고 간단하게 마우스를 제어할 수 있습니다.

 

### analog_note_dll - hook_mouse_callback.cpp

```
#include "pch.h"
 
hook_mouse_callback* volatile hook_mouse_callback::_instance = nullptr;
std::mutex hook_mouse_callback::_mutex;
 
hook_mouse_callback::hook_mouse_callback() :
    _hook(nullptr) {
 
}
 
LRESULT hook_mouse_callback::new_function(
    __in int code,
    __in WPARAM wparam,
    __in LPARAM lparam
    ) {
 
    do {
        if (code < HC_ACTION) {
            break;
        }
 
        MOUSEHOOKSTRUCT* mouse_param = (MOUSEHOOKSTRUCT*)lparam;
 
        if (wparam == WM_LBUTTONDOWN) {
            OutputDebugString(L"mouse left button down");
        }
        else if (wparam == WM_LBUTTONUP) {
            OutputDebugString(L"mouse left button up");
        }
        if (wparam == WM_RBUTTONDOWN) {
            OutputDebugString(L"mouse right button down");
        }
        else if (wparam == WM_RBUTTONUP) {
            OutputDebugString(L"mouse right button up");
        }
        else if (wparam == WM_MOUSEMOVE) {
            //OutputDebugString(L"mouse move");
        }
        else {
            break;
        }
 
    } while (false);
 
    return ::CallNextHookEx(_hook, code, wparam, lparam);
}
```

cpp 부분인데 너무 별게 없어서 실망하셨나요? 그래도 있을 건 다 있는 부분입니다.

제일 윗부분에 instance와 mutex는 static member이기 때문에 cpp에서 재선언이 필요한 singleton 변수입니다.

실제로 new_function에서는 몇 가지 값이 들어오는데,

wparam을 통해 마우스의 행위를 알 수 있으며, lparam은 MOUSEHOOKSTRUCT로 타입 캐스팅을 하여 마우스 위치 등을 확인할 수 있습니다.

 

![img](https://blog.kakaocdn.net/dn/8P5Bo/btqwXfdOzAt/psV1SYUkVveK7vnp61UBK1/img.png)

 

![img](https://blog.kakaocdn.net/dn/ejleXo/btqwWoWOFnY/2QpPHaLVGnSySlraHzJUc0/img.png)

 

샘플을 한번 돌려봤습니다.

잘 동작하네요 :)

OutputDebugString 등은 DebugView.exe 프로세스를 통해서 볼 수 있습니다. DebugView는 아래에 sysinternals에서 받을 수 있으며, MS에서 제공하는 윈도우 개발 시 사용할 수 있는 다양한 툴들을 공식적으로 다운로드할 수 있는 곳입니다.

 

https://docs.microsoft.com/en-us/sysinternals/downloads/debugview

음번에는 마우스의 움직임을 이용하여 특정 동작들을 할 수 있는 프로젝트로 업그레이드를 해보겠습니다.

 

### Sample Download

처음부터 직접 개발을 하시면 좋겠지만 그렇지 않은분들은 아래 샘플 프로젝트를 통하여 이것저것 시도해보시길 바랍니다.

analog_note_mouse_hook.zip

 