# MS Detours를 사용한 API 후킹

출처: https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours

이번 글에서는 API 후킹의 이론과 구현에 대해 이야기하겠습니다. API 후킹은 누군가가 함수를 하이재킹하여 사용자 정의 함수로 리디렉션할 수 있게 해주는 강력한 기술입니다. 제어권을 원래 API로 다시 전달하기 전에 이러한 함수에서 무엇이든 수행할 수 있습니다.

- [Download source code - 44.5 KB](https://www.codeproject.com/KB/Articles/funapihook/apihookingcomplete.zip)



## Contents

1. [소개](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Intro)
2. [시작하기: 기존 API 후킹](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Trad)
3. Detours API Hooking
   - [3.1 예: 프로세스별 패킷 로거](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Packet)
   - [3.2 더 복잡한 예: MSN 메신저](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#MSN)
4. DLL Injection
   - [4.1 프로세스 열거](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#ProcEnum)
   - [4.2 원격 스레드 생성](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Remote)
   - [4.3 DetourCreateProcessWithDll](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#DCP)
   - [4.4 주소로 우회](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Address)
5. [일반적인 오류](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Errors)
6. [결론](https://www.codeproject.com/Articles/30140/API-Hooking-with-MS-Detours#Conclusion)





## 1. 소개

이번 포스팅에서는 API Hooking에 대해 이야기해보겠습니다. API 후킹은 프로그램의 함수 호출을 가로채서 다른 함수로 리디렉션하는 것으로 구성됩니다. 이렇게 하면 매개변수가 수정될 수 있고, 실제로 성공해야 할 때 오류 코드를 반환하도록 선택하면 원본 프로그램을 속일 수 있습니다. 이 모든 작업은 실제 함수가 호출되기 전에 수행되며, 결국 원래 함수/매개변수를 수정/저장/확장한 후 다시 호출될 때까지 원래 함수에 제어권이 다시 넘겨집니다. 이 기사를 완전히 이해하려면 C++에 대한 심층적인 지식이 필요합니다. 저는 무료로 다운로드할 수 있는 Microsoft Detours Library를 사용할 것입니다. 제공된 코드 예제를 성공적으로 컴파일하려면 Detours 라이브러리와 함께 제공되는 Makefile을 실행하고 라이브러리 파일과 기타 모든 항목을 빌드하도록 해야 합니다. 이에 대한 지침은 MSDN 포럼이나 인터넷의 다른 곳에서 찾을 수 있습니다. 공간 확보를 위해 이 기사에 게시된 코드 샘플은 주석 처리가 되어 있지 않지만, 해당 코드로 연결되거나 이어지는 설명이 있습니다.



## 2. 시작하기: 기존 API 후킹

Detours API에 들어가기 전에 함수 주소를 사용자 정의 주소로 덮어쓰는 API 후킹에 대한 전통적인 접근 방식에 대해 설명하겠습니다. 이는 API 후킹의 다양한 방법 중 하나일 뿐입니다. 다른 방법에는 가져오기 주소 테이블 수정(나중에 제공되는 링크), 프록시 DLL 및 매니페스트 파일 사용, 커널 주소 공간에서 드라이버 로드를 통한 후킹 등이 포함됩니다. 내가 사용할 이 기술은 후크된 API가 매번 연결 해제되어야 한다는 점에서 다소 초보적이며, 이로 인해 멀티 스레드 프로그램에서 동시성과 충돌이 발생할 수 있습니다. 원래 기능을 위해 다른 곳에 메모리를 할당하고 우회를 지속적으로 다시 작성하지 않아도 되도록 후크 내에 후크를 설정하여 이 문제를 해결할 수 있는 방법이 있습니다. 코드 및 디버깅 단순성을 위해 후크/후크 해제 방법을 사용하기로 결정했습니다. 앞서 언급한 것처럼 이것이 최선의 방법은 아니지만, 이 글은 API 후킹을 위해 MS Detours를 사용하는 것에 관한 것이므로 그다지 중요하지는 않습니다.

``` c++
#include <windows.h>

#define SIZE 6

typedef int (WINAPI *pMessageBoxW)(HWND, LPCWSTR, LPCWSTR, UINT);
int WINAPI MyMessageBoxW(HWND, LPCWSTR, LPCWSTR, UINT);

void BeginRedirect(LPVOID);

pMessageBoxW pOrigMBAddress = NULL;
BYTE oldBytes[SIZE] = {0};
BYTE JMP[SIZE] = {0};
DWORD oldProtect, myProtect = PAGE_EXECUTE_READWRITE;

INT APIENTRY DllMain(HMODULE hDLL, DWORD Reason, LPVOID Reserved)
{
    switch(Reason)
    {
    case DLL_PROCESS_ATTACH:
        pOrigMBAddress = (pMessageBoxW)
            GetProcAddress(GetModuleHandle("user32.dll"), 
                           "MessageBoxW");
        if(pOrigMBAddress != NULL)
            BeginRedirect(MyMessageBoxW);    
        break;
    case DLL_PROCESS_DETACH:
        memcpy(pOrigMBAddress, oldBytes, SIZE);
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
        break;
    }
    return TRUE;
}

void BeginRedirect(LPVOID newFunction)
{
    BYTE tempJMP[SIZE] = {0xE9, 0x90, 0x90, 0x90, 0x90, 0xC3};
    memcpy(JMP, tempJMP, SIZE);
    DWORD JMPSize = ((DWORD)newFunction - (DWORD)pOrigMBAddress - 5);
    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, 
                    PAGE_EXECUTE_READWRITE, &oldProtect);
    memcpy(oldBytes, pOrigMBAddress, SIZE);
    memcpy(&JMP[1], &JMPSize, 4);
    memcpy(pOrigMBAddress, JMP, SIZE);
    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, oldProtect, NULL);
}

int  WINAPI MyMessageBoxW(HWND hWnd, LPCWSTR lpText, LPCWSTR lpCaption, UINT uiType)
{
    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, myProtect, NULL);
    memcpy(pOrigMBAddress, oldBytes, SIZE);
    int retValue = MessageBoxW(hWnd, lpText, lpCaption, uiType);
    memcpy(pOrigMBAddress, JMP, SIZE);
    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, oldProtect, NULL);
    return retValue;
}
```

이는 표준 API 후크의 프레임워크입니다. 이 모든 것은 프로세스에 주입될 DLL에 있습니다. 이 예에서는 MessageBoxW 함수를 연결하기로 선택했습니다. 이 DLL이 삽입되면 user32.dll에서 MessageBoXW 함수의 주소를 가져오고 후킹이 시작됩니다. BeginRedirect 함수에서 무조건 상대 점프(JMP) opcode(0xE9) 명령에는 점프할 거리가 포함됩니다. 어셈블리 코드로 보면 다음과 같습니다.

``` asm
JMP <distance>
RET
```

BeginRedirect(…) 함수에서 다음을 수행하면 이를 좀 더 명확하게 하는 데 도움이 될 수 있습니다.

``` c++
sprintf_s(debugBuffer, 128, "pOrigMBAddress: %x", pOrigMBAddress);
OutputDebugString(debugBuffer);
..
memcpy(oldBytes, pOrigMBAddress, SIZE);
sprintf_s(debugBuffer, 128, "Old bytes: %x%x%x%x%x", oldBytes[0], oldBytes[1],
    oldBytes[2], oldBytes[3], oldBytes[4], oldBytes[5]);
OutputDebugString(debugBuffer);
..
memcpy(&JMP[1], &JMPSize, 4);
sprintf_s(debugBuffer, 128, "JMP: %x%x%x%x%x", JMP[0], JMP[1],
    JMP[2], JMP[3], JMP[4], JMP[5]);
OutputDebugString(debugBuffer);
```

DLL을 삽입하고 DebugView를 통해 살펴봅니다.

![funapihook/Overwrite.jpg](https://www.codeproject.com/KB/Articles/funapihook/Overwrite.jpg)

API 후크를 설정하기 전에 0x7E466534에서 시작하는 MessageBoxW 코드 흐름에는 8B, FF, 55, 8B, EC의 5바이트가 있었습니다. memcpy(pOrigMBAddress, JMP, SIZE); 이후의 코드 흐름은 함수(E9, A7, AC, B9, 91)로 점프하는 바이트입니다. 따라서 주소 0x7E466534(MessageBoxW)가 호출될 때마다 애플리케이션은 즉시 사용자 정의 함수의 주소로 점프합니다. 이는 또한 API 후킹의 특정 기술에 문제가 발생하는 곳이기도 합니다.

``` c++
int  WINAPI MyMessageBoxW(HWND hWnd, LPCWSTR lpText, LPCWSTR lpCaption, UINT uiType)
{
    OutputDebugString("In MyMessageBoxW");
//    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, myProtect, NULL);
//    memcpy(pOrigMBAddress, oldBytes, SIZE);
    int retValue = MessageBoxW(hWnd, lpText, lpCaption, uiType);
//    memcpy(pOrigMBAddress, JMP, SIZE);
//    VirtualProtect((LPVOID)pOrigMBAddress, SIZE, oldProtect, NULL);
    return retValue;
}
```

바이트를 다시 쓰고 함수를 호출해야 하는 이유는 정확히 무엇입니까? 위에서 했던 것처럼 모든 것을 주석 처리하고 이 DLL을 삽입하고 프로세스가 MessageBoxW 호출을 호출하도록 하면 다음과 같은 일이 발생합니다.

![funapihook/Loop.jpg](https://www.codeproject.com/KB/Articles/funapihook/Loop.jpg)

그것에 대해 생각하고 무슨 일이 일어나고 있는지 보면 사용자 정의 함수에서 MessageBoxW를 호출하고 값을 반환하고 있음을 알 수 있습니다. 그러나 문제는 MessageBoxW가 자신을 MyMessageBoxW로 리디렉션하여 MyMessageBoxW가 기본적으로 무한 루프인 무한 루프 없이 자신을 재귀적으로 호출하게 한다는 것입니다. 이것이 바로 함수가 스스로 풀리는 이유입니다. 실제로 실제 MessageBoxW 함수로 작동하려면 점프에 대해 덮어쓴 바이트를 다시 작성해야 합니다. 그런 다음 원래 함수를 호출하고 반환 값을 저장해야 합니다. 그 후에는 점프를 다시 작성하고 값을 반환하면 작동합니다. 데모로서 다음 줄을 추가했습니다.

``` c++
MessageBoxW(NULL, L"This should pop up", L"Hooked MBW", MB_ICONEXCLAMATION);
```

원래 바이트를 메모리에 다시 복사하는 줄 뒤. 그런 다음 찾을 수 없는 텍스트를 검색할 때 MessageBoxW를 표시하는 메모장으로 이것을 시도해 보았습니다. 결과?

![funapihook/Hooked.jpg](https://www.codeproject.com/KB/Articles/funapihook/Hooked.jpg)

이것이 이 접근 방식의 전부입니다. 이상적으로는 IAT(Import Address Table) 후킹과 같은 고급 방법이 후크가 제자리에 유지되고 원할 때 언제든지 제거될 수 있다는 점에서 더 좋습니다.



## 3. Detours API 후킹

Microsoft Detours 라이브러리는 거의 동일한 방식으로 작동합니다. 

개요에서:

'Detours는 x86 시스템에서 임의의 Win32 바이너리 함수를 가로채기 위한 라이브러리입니다. 가로채기 코드는 런타임에 동적으로 적용됩니다. Detours는 대상 함수의 처음 몇 명령을 사용자가 제공한 우회 함수로 무조건 점프하는 것으로 대체합니다. 대상 함수의 명령 트램펄린의 주소는 대상 포인터에 배치됩니다. 우회 함수는 대상 함수를 대체하거나 대상 포인터를 통해 트램폴린에 대한 서브루틴으로 대상 함수를 호출하여 의미를 확장할 수 있습니다.

따라서 이는 위에서 설명한 방식과 비슷하지만 더 정교하고 우아한 방식입니다. 이 모든 것을 구동하는 기능은 DetourAttach(…) 기능입니다.

``` c++
LONG DetourAttach(
    PVOID * ppPointer,
    PVOID pDetour
    );
```

타겟 API를 후킹하는 역할을 하는 함수입니다. 첫 번째 매개변수는 우회할 함수의 포인터를 가리키는 포인터이다. 두 번째는 우회로 역할을 할 함수에 대한 포인터입니다. 그러나 우회가 시작되기 전에 수행해야 할 몇 가지 작업이 있습니다.

- 우회 트랜잭션을 시작해야 합니다.
- 스레드를 트랜잭션으로 업데이트해야 합니다.

이는 다음 호출을 통해 쉽게 수행됩니다.

- `DetourTransactionBegin()`
- `DetourUpdateThread(GetCurrentThread())`

이 두 가지 작업이 완료되면 우회 경로를 연결할 준비가 된 것입니다. 연결한 후에는 DetourTransactionCommit()을 호출하여 우회를 적용하고 필요한 경우 성공 또는 실패를 확인하는 것이 중요합니다.

### 3.1 예: 프로세스별 패킷 로거

우회를 통한 API 후킹의 예로 Winsock 함수 send(…) 및 recv(…)를 후킹하는 코드 샘플을 제시하겠습니다. 이 함수에서는 원래 함수에 제어권을 넘기기 전에 로그 파일에 보내거나 받은 버퍼를 기록하겠습니다. 주목해야 할 매우 중요한 점은 우회 함수인 함수는 우회할 함수와 정확히 동일한 호출 규칙 및 매개변수를 가져야 한다는 것입니다. 예를 들어 send 함수는 다음과 같습니다.

``` c++
int send(
  __in  SOCKET s,
  __in  const char *buf,
  __in  int len,
  __in  int flags
);
```

따라서 이 함수에 대한 포인터는 다음과 같아야 합니다.

``` c++
int (WINAPI *pSend)(SOCKET, const char*, int, int) = send;
```

포인터를 함수와 동일하게 설정하는 것은 괜찮습니다. 나중에 사용하는 또 다른 방법은 초기 포인터를 NULL로 설정한 다음 DetourFindFunction(…)을 사용하여 주소를 찾는 것입니다. send(...)와 recv(...) 모두에 대해 이 작업을 수행합니다.

``` c++
int (WINAPI *pSend)(SOCKET s, const char* buf, int len, int flags) = send;
int WINAPI MySend(SOCKET s, const char* buf, int len, int flags);
int (WINAPI *pRecv)(SOCKET s, char* buf, int len, int flags) = recv;
int WINAPI MyRecv(SOCKET s, char* buf, int len, int flags);
```

이제 Hooking할 함수와 Redirection할 함수가 모두 정의되었습니다. WINAPI를 사용하는 이유는 이러한 함수가 __stdcall 호출 규칙에 따라 내보내지기 때문입니다. 이제 이 작업을 시작하려면 다음을 수행하십시오.

``` c++
INT APIENTRY DllMain(HMODULE hDLL, DWORD Reason, LPVOID Reserved)
{
    switch(Reason)
    {
        case DLL_PROCESS_ATTACH:
            DisableThreadLibraryCalls(hDLL);
            DetourTransactionBegin();
            DetourUpdateThread(GetCurrentThread());
            DetourAttach(&(PVOID&)pSend, MySend);
            if(DetourTransactionCommit() == NO_ERROR)
                OutputDebugString("send() detoured successfully");
            DetourTransactionBegin();
            DetourUpdateThread(GetCurrentThread());
            DetourAttach(&(PVOID&)pRecv, MyRecv);
            if(DetourTransactionCommit() == NO_ERROR)
                OutputDebugString("recv() detoured successfully");
            break;
            .
            .
            .
```

기본적으로 앞서 언급한 것처럼 우회 트랜잭션을 시작하고 트랜잭션에 대한 스레드를 업데이트한 다음 DetourAttach(…)를 사용하여 실제 우회를 수행함으로써 시작됩니다. 마무리 및 오류 검사는 DetourTransactionCommit() 함수를 사용하여 수행됩니다. 이 함수는 우회 성공 시 NO_ERROR를 반환하고, 실패 시 지정된 오류 코드를 반환합니다. DetourAttach(…) 함수에서 우회할 함수를 포인터에 대한 포인터로 전달해야 하므로 &(PVOID&)로 타입 캐스팅이 작동합니다. 기본적으로는 이것이 전부입니다. 이제 MS Detours를 사용하여 우회할 준비가 되었습니다. 이제 send(...) 및 recv(...)를 가로채는 함수에 대해 설명합니다. 실시간으로 주고받는 패킷을 기록하기 위해 무엇이든 받거나 보낼 때 로그 파일을 열고 쓰고 닫는 방식을 선택했습니다. 이것이 최선의 접근 방식인지는 확실하지 않지만 상관없습니다. catch하고 쓰는 함수는 다음과 같이 정의됩니다.

``` c++
int WINAPI MySend(SOCKET s, const char* buf, int len, int flags)
{
    fopen_s(&pSendLogFile, "C:\\SendLog.txt", "a+");
    fprintf(pSendLogFile, "%s\n", buf);
    fclose(pSendLogFile);
    return pSend(s, buf, len, flags);
}

int WINAPI MyRecv(SOCKET s, char* buf, int len, int flags)
{
    fopen_s(&pRecvLogFile, "C:\\RecvLog.txt", "a+");
    fprintf(pRecvLogFile, "%s\n", buf);
    fclose(pRecvLogFile);
    return pRecv(s, buf, len, flags);
}
```

pRecvLogFile과 pSendLogFile은 모두 FILE*로 선언됩니다. 나는 Windows XP와 함께 제공되는 'Internet Checkers' 게임에서 이것을 테스트하기로 결정했습니다. 두 파일 모두 데이터 캡처에 성공했습니다. 우회 함수에서는 반환 문을 기록해 두는 것이 중요합니다. 메모리 패치와 관련된 다른 접근 방식과 달리 Detours를 사용하면 간단히 주소별로 함수를 호출하여 제어권을 프로그램에 다시 넘길 수 있습니다. 함수가 우회되면 수정이 필요하지 않습니다. 필요한 유일한 것은 제어를 원래 함수로 다시 전달하는 것입니다(또는 API 처리를 차단하는 경우 유효한 것을 반환하는 것입니다).

![funapihook/client.jpg](https://www.codeproject.com/KB/Articles/funapihook/client.jpg)

MSN 메신저가 사용하는 WSARecv(…)를 후킹하면 패킷에서 이메일을 구문 분석하고 대화에서 활성 SOCKET의 번호를 저장할 수도 있습니다. 저는 챗봇(MSN: smarterchild@hotmail.com)과 재미있는 대화를 나누고 일부 패킷을 기록하여 채팅이 어떻게 보이는지 확인하기로 결정했습니다.

```
MSG smarterchild@hotmail.com 
-%20SmarterChild%20-%20*unicef%20contributing%20to%20charity 
137..MIME-Version: 1.0..Content-Type: text/plain; 
charset=UTF-8..X-MMS-IM-Format: FN=Courier%20New; 
EF=; CO=800000; CS=0; PF=22....:-D :-) :-)
```

프로토콜은 일반 텍스트이므로 어떤 종류의 암호화/암호 해독 알고리즘도 리버스 엔지니어링할 필요가 없기 때문에 좋습니다. 패킷에는 보낸 사람의 이메일, 일부 텍스트 플래그 및 실제 메시지 자체가 포함됩니다. MSN 메신저 프로토콜은 여기에 완전히 문서화되어 있습니다. 그러나 일반 텍스트 메시지만 필요할 것입니다. 내가 한 일은 수신된 메시지 패킷에만 있는 특정 속성을 확인하는 것이었습니다. 이 모든 것이 발견되면 이메일을 구문 분석하고 활성 세션을 저장하는 것이 안전합니다.

``` c++
if(strstr(lpBuffers->buf, "MSG ") != 0 && 
  (strstr(lpBuffers->buf, "MIME-Version") != 0 && 
   strstr(lpBuffers->buf, "X-MMS-IM-Format") != 0))
    ParseAndStoreEmail(socket, lpBuffers->buf);
```

lpBuffers는 WSARecv/WSASend의 매개변수인 LPWSABUF 구조입니다. 일단 구문 분석되면 이메일과 활성 SOCKET 세션은 두 개의 병렬 벡터에 저장될 수 있으므로 나중에 쉽게 일치시키고 업데이트할 수 있습니다.

``` c++
vector<string>emailList;
vector<socket>sessionList;
The parsing and storing function works like this
void ParseAndStoreEmail(SOCKET session, const char* buffer)
{
    string email;
    int i = 4; //4 to skip "MSG " part
    while(buffer[i] != ' ')
    {
        email += buffer[i];
        i++;
    }
    if(SearchForDuplicates(session, email.c_str()) != -1)
    {
        emailList.push_back(email);
        sessionList.push_back(session);
        SendDlgItemMessage(::g_hDlg, IDC_CBUSERS, CB_ADDSTRING, NULL,
            (LPARAM)email.c_str());
        SendDlgItemMessage(::g_hDlg, IDC_CBUSERS, CB_SETCURSEL, emailList.size()-1, 0);
    }
}
```

이 함수는 채팅 패킷이 수신될 때만 호출되므로 'MSG' 뒤의 첫 번째 공백 뒤와 패킷의 다음 공백 앞에 있는 텍스트를 가져와 이메일을 구문 분석하기로 결정했습니다. 중복이 아닌 경우 이메일을 벡터에 저장하고 활성 SOCKET을 다른 벡터에 병렬로 저장하는 것이 안전합니다. 그러면 이메일이 콤보 상자에 추가됩니다. 사용자 정의 메시지를 보내는 것도 매우 쉽습니다.

``` c++
case IDOK:
    {
        int index = SendDlgItemMessage(hDlg, IDC_CBUSERS, CB_GETCURSEL, 0, 0);
        int textLength = SendDlgItemMessage(hDlg, IDC_CHAT, WM_GETTEXTLENGTH, 0, 0);
        if(textLength == 0)
            break;
        char* emailSelected = new char[128];
        char* packet = new char[textLength+1];
        GetDlgItemText(hDlg, IDC_CHAT, packet, textLength+1);
        SendDlgItemMessage(hDlg, IDC_CBUSERS, CB_GETLBTEXT, index, (LPARAM)emailSelected);
        SOCKET sessionToSendTo = GetSessionFromEmail(emailSelected);
        BuildPacket(sessionToSendTo, packet);
        delete [] emailSelected;
        delete [] packet;
    }
    break;
```

콤보 상자에서 현재 이메일을 가져와 해당 이메일에 적합한 SOCKET을 가져온 다음 패킷을 작성하고 전송하면 됩니다. BuildPacket(…) 함수는 다음과 같이 작동합니다.

``` c++
void BuildPacket(SOCKET session, const char* message)
{
    char packetSize[8];
    ZeroMemory(packetSize, 8);
    string packetHeader = "MSG 10 N ";
    string packetSettings = "MIME-Version: 1.0\r\nContent-Type: " 
                            "text/plain; charset=UTF-8\r\n";
    packetSettings += "X-MMS-IM-Format: FN=MS%20Shell%20Dlg; " 
                      "EF=; CO=0; CS=0; PF=0\r\n\r\n";
    string packetMessage = message;
    int sizeOfPacket = packetSettings.length() + packetMessage.length();
    _itoa_s(sizeOfPacket, packetSize, 8, 10);
    packetHeader += packetSize;
    packetHeader += "\r\n";
    string fullPacket = packetHeader;
            fullPacket += packetSettings;
            fullPacket += packetMessage;
    psend(session, fullPacket.c_str(), fullPacket.length(), 0);
}
```

나가는 MSN 메신저 패킷의 전체 분석은 다음과 같습니다.

```
MSG [Message #] [Acknowledge Flag] [Size of packet] [Text flags] [Message]
```

적어도 나가는 패킷의 경우 메시지 #은 메시지를 받는 최종 사용자에게 실제로 중요하지 않다는 것을 알았습니다. 01인지 99인지는 중요하지 않아서 패킷의 해당 부분을 하드코딩하게 되었습니다. 패킷 크기가 중요하며 잘못된 패킷 크기가 전송되면 세션이 종료되고 새 SOCKET에서 다시 설정되어야 합니다. 또한 굵은 글씨, 밑줄, 색상 등이 없는 일반 MS Shell 대화 상자 텍스트로 텍스트 속성을 하드 코딩했습니다. 샘플 실행은 다음과 같습니다.

![funapihook/MSNConvo.jpg](https://www.codeproject.com/KB/Articles/funapihook/MSNConvo.jpg)

전송된 메시지가 채팅 창에 표시되지 않는 것을 확인할 수 있습니다. 이는 창에 텍스트를 추가하는 것이 소켓과 아무 관련이 없고 'Enter' 키를 누르거나 '보내기'를 클릭하면 간단히 추가되기 때문입니다. 메시지는 활성 SOCKET을 통해 전송되고 있으며 질문에 대한 챗봇의 응답은 메시지를 성공적으로 수신하고 있음을 확인합니다. 이 섹션을 끝내기 전에 언급하고 싶은 중요한 점은 이 애플리케이션이 이 기사를 위해 특별히 코딩되었다는 것입니다. MSN 메신저 창을 대체하려는 의도는 없으며 채팅 세션에서 시간 초과를 처리하지 못하는 등 몇 가지 단점이 있습니다. 이것에 대한 나의 목표는 MSN 메신저 인터페이스를 대체하거나 완벽하게 작동하는 통합 클라이언트를 작성하는 것이 아니라 단순히 비전통적인 방법으로 패킷을 보낼 수 있도록 하는 것이었습니다.



## 4. DLL Injection

나는 이 글에서 DLL 인젝션에 관해 많은 이야기를 했습니다. API 후크가 포함된 DLL이 작동하려면 프로세스의 주소 공간에 삽입되어야 합니다. DLL 주입에는 일반적으로 프로세스에서 로드되지 않는 DLL을 로드하는 작업이 포함됩니다. 이를 수행하는 방법은 다양합니다. 매우 일반적인 방법은 더미 후크 프로시저와 함께 SetWindowsHookEx(…)를 사용하는 것입니다. 또 다른 일반적인 방법은 CreateRemoteThread(…) API를 사용하는 것입니다. DLL을 로드하기 위해 CodeCave 스텁을 하드코딩하는 것도 가능합니다(저는 Solitaire 기사에서 이 작업을 수행했습니다). 이 기사에서 제가 취하고 설명할 접근 방식은 CreateRemoteThread 접근 방식입니다. 이는 매우 쉽고 상대적으로 효율적입니다. 하지만 시작하기 전에 실제로 주입할 프로세스를 찾는 것이 중요합니다.

### 4.1 프로세스 열거

Windows API는 이를 수행하기 위한 훌륭한 기능인 CreateToolhelp32Snapshot(…)을 제공합니다. 이 함수는 실행 중인 프로세스, 스레드, 힙 등의 스냅샷을 찍습니다. Process32First(…) 및 Process32Next(…) 함수를 사용하면 실행 중인 모든 프로세스를 열거할 수 있습니다. 이것을 알고 코딩하는 것은 문제가 되지 않습니다.

``` c++
#undef UNICODE
#include <vector>
#include <string>
#include <windows.h>
#include <Tlhelp32.h>
using std::vector;
using std::string;

int main(void)
{
    vector<string>processNames;
    PROCESSENTRY32 pe32;
    pe32.dwSize = sizeof(PROCESSENTRY32);
    HANDLE hTool32 = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, NULL);
    BOOL bProcess = Process32First(hTool32, &pe32);
    if(bProcess == TRUE)
    {
        while((Process32Next(hTool32, &pe32)) == TRUE)
            processNames.push_back(pe32.szExeFile);
    }
    CloseHandle(hTool32);
    return 0;
}
```

![funapihook/processes.jpg](https://www.codeproject.com/KB/Articles/funapihook/processes.jpg)

Process32First를 호출한 후에는 항상 '[시스템 프로세스]'이므로 값을 저장하지 않았으므로 실제로는 필요하지 않습니다. Process32Next는 성공 시 TRUE를 반환하므로 간단히 루프에 넣고 수신한 프로세스의 이름을 벡터로 푸시하면 됩니다. 루프가 완료되면 모든 단일 프로세스가 processNames에 저장되어야 합니다. 이것은 훌륭하지만 DLL 주입은 어디에 사용됩니까? PROCESSENTRY32 구조에는 프로세스 ID를 보유하는 멤버도 있습니다. 해당 루프 내에서 벡터의 프로세스 이름을 푸시하는 동안 DLL도 삽입할 것입니다.

### 4.2 CreateRemoteThread

``` c++
while((Process32Next(hTool32, &pe32)) == TRUE)
{
    processNames.push_back(pe32.szExeFile);
    if(strcmp(pe32.szExeFile, "notepad.exe") == 0)
    {
        char* DirPath = new char[MAX_PATH];
        char* FullPath = new char[MAX_PATH];
        GetCurrentDirectory(MAX_PATH, DirPath);
        sprintf_s(FullPath, MAX_PATH, "%s\\testdll.dll", DirPath);
        HANDLE hProcess = OpenProcess(PROCESS_CREATE_THREAD    | PROCESS_VM_OPERATION    |
            PROCESS_VM_WRITE, FALSE, pe32.th32ProcessID);
        LPVOID LoadLibraryAddr = (LPVOID)GetProcAddress(GetModuleHandle("kernel32.dll"),
            "LoadLibraryA");
        LPVOID LLParam = (LPVOID)VirtualAllocEx(hProcess, NULL, strlen(FullPath),
            MEM_RESERVE | MEM_COMMIT, PAGE_READWRITE);
        WriteProcessMemory(hProcess, LLParam, FullPath, strlen(FullPath), NULL);
        CreateRemoteThread(hProcess, NULL, NULL, (LPTHREAD_START_ROUTINE)LoadLibraryAddr,
            LLParam, NULL, NULL);
        CloseHandle(hProcess);
        delete [] DirPath;
        delete [] FullPath;
    }
}
```

여기에서 처음부터 끝까지 진행되는 작업은 먼저 현재 디렉터리를 가져오고 이 경우 DLL 이름 'testdll.dll'을 추가하는 것입니다. 이렇게 하면 실행 파일이 DLL을 찾을 수 있습니다. 그런 다음 DLL 주입을 수행하는 데 필요한 세 가지 플래그를 사용하여 프로세스를 엽니다. 이상적으로는 PROCESS_ALL_ACCESS 플래그를 설정하는 것이 가장 좋지만, 이를 위해서는 프로세스 권한을 SE_DEBUG_NAME으로 변경해야 하는데, 코드를 위해 구현하지 않았습니다. SE_DEBUG_NAME으로 변경하는 코드는 여기에서 찾을 수 있습니다. 계속해서 적절한 플래그를 사용하여 프로세스를 연 후 GetProcAddress(…)를 사용하여 LoadLibraryA 함수의 주소를 찾습니다. 다음으로 주입될 프로세스의 주소 공간에 있는 DLL 경로에 대한 일부 메모리를 할당합니다. 이 작업이 완료되면 WriteProcessMemory(…)를 사용하여 문자열을 메모리에 쓸 수 있습니다. 그런 다음 CreateRemoteThread를 사용하고 시작 주소를 LoadLibraryA로 지정하고 문자열을 매개변수로 전달하기만 하면 됩니다. 결과?

![funapihook/dllloaded.jpg](https://www.codeproject.com/KB/Articles/funapihook/dllloaded.jpg)

### 4.3 DetourCreateProcessWithDll

위 기술은 이미 실행 중인 프로세스에 DLL을 삽입하는 데 유용합니다. 하지만 해당 프로세스가 실행될 때 해당 프로세스에 DLL을 주입하는 것은 어떻습니까? Detours 라이브러리는 이를 수행하는 기능인 DetourCreateProcessWithDll(…)을 제공합니다. 해당 함수를 사용하는 것은 기본적으로 CREATE_SUSPENDED 생성 플래그를 사용하여 CreateProcess를 호출하는 것과 같습니다. 이렇게 하면 메인 스레드가 일시 중지된 상태로 프로세스가 생성되므로 실제 애플리케이션이 표시되기 전에 DLL이 삽입될 수 있습니다. 주목해야 할 중요한 점은 삽입할 DLL이 서수 1의 함수를 내보내야 한다는 것입니다. 그렇지 않으면 DetourCreateProcessWithDll(…) 함수가 실패합니다. testdll.dll을 사용하여 notepad.exe를 시작하는 빠른 구현(다시 서수 1에서 함수를 내보내야 함):

``` c++
#undef UNICODE
#include <cstdio>
#include <windows.h>
#include <detours\detours.h>

int main()
{
    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    ZeroMemory(&pi, sizeof(PROCESS_INFORMATION));
    si.cb = sizeof(STARTUPINFO);
    char* DirPath = new char[MAX_PATH];
    char* DLLPath = new char[MAX_PATH]; //testdll.dll
    char* DetourPath = new char[MAX_PATH]; //detoured.dll
    GetCurrentDirectory(MAX_PATH, DirPath);
    sprintf_s(DLLPath, MAX_PATH, "%s\\testdll.dll", DirPath);
    sprintf_s(DetourPath, MAX_PATH, "%s\\detoured.dll", DirPath);
    DetourCreateProcessWithDll(NULL, "C:\\windows\\notepad.exe", NULL,
        NULL, FALSE, CREATE_DEFAULT_ERROR_MODE, NULL, NULL,
        &si, &pi, DetourPath, DLLPath, NULL);
    delete [] DirPath;
    delete [] DLLPath;
    delete [] DetourPath;
    return 0;
}
```

DetourCreateProcessWithDll 함수는 기본적으로 CreateProcess의 확장 버전입니다. 이는 CreateProcess의 모든 매개변수와 함께 주입될 DLL 경로와 Detours API를 사용하는 모든 DLL에 자동으로 주입되는 detoured.dll의 경로를 포함하는 일부 추가 매개변수를 사용합니다. CreateProcess를 사용하여 이 작업을 수행하려면 CREATE_SUSPENDED를 생성 플래그로 설정하고 DLL을 삽입한 다음 PROCESS_INFORMATION 구조에 저장된 스레드 핸들을 사용하여 ResumeThread(…)를 호출하면 됩니다.

### 4.4 주소로 우회

표준 Win32 API 또는 알려진 내보낸 함수가 아닌 함수를 우회해야 하는 경우가 있을 수 있습니다. 여기서는 함수의 하드코딩된 주소를 사용하여 우회를 수행해야 합니다. 함수가 취하는 주소와 매개변수를 알면(리버스 엔지니어링하거나 문서를 찾은 후) 함수를 연결하는 것이 전적으로 가능합니다. 아래 코드는 방법을 보여줍니다.

``` c++
#undef UNICODE
#include <cstdio>
#include <windows.h>
#include <detours\detours.h>

typedef void (WINAPI *pFunc)(DWORD);
void WINAPI MyFunc(DWORD);

pFunc FuncToDetour = (pFunc)(0x0100347C); //Set it at address to detour in
                    //the process
INT APIENTRY DllMain(HMODULE hDLL, DWORD Reason, LPVOID Reserved)
{
    switch(Reason)
    {
    case DLL_PROCESS_ATTACH:
        {
            DisableThreadLibraryCalls(hDLL);
            DetourTransactionBegin();
            DetourUpdateThread(GetCurrentThread());
            DetourAttach(&(PVOID&)FuncToDetour, MyFunc);
            DetourTransactionCommit();
        }
        break;
    case DLL_PROCESS_DETACH:
        DetourTransactionBegin();
        DetourUpdateThread(GetCurrentThread());
        DetourDetach(&(PVOID&)FuncToDetour, MyFunc);
        DetourTransactionCommit();
        break;
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
        break;
    }
    return TRUE;
}

void WINAPI MyFunc(DWORD someParameter)
{
    //Some magic can happen here
}
```

우회하는 함수는 단순히 함수의 하드코딩된 주소를 가리킨 다음 우회할 준비가 되어 있습니다. 이 특정 예에는 DWORD를 매개변수로 사용하는 void 함수가 있습니다.



## 5. 일반적인 오류

이 기사에 대한 모든 응용 프로그램을 작성하면서 겪은 가장 일반적인 오류는 DLL이 프로세스에 올바르게 로드되는 것이었습니다. 주입될 프로세스가 실행되고 있는 디렉터리에 주의를 기울이는 것이 매우 중요합니다. 주입 방법 및 구현에 따라 주입할 DLL이 실행 중인 프로세스와 동일한 디렉터리에 있어야 할 수도 있습니다. 현재 디렉터리를 찾아 DLL 이름을 추가하므로 구현 시에는 문제가 되지 않습니다. 그러나 로더와 DLL은 동일한 경로에 있어야 합니다. 결국 내 방법은 로더의 현재 디렉터리를 가져와서 DLL 이름을 추가하는 것이므로 DLL이 다른 디렉터리에 있으면 작동하지 않습니다. 아마도 이것이 이 코드를 컴파일하고 스스로 시도한 후에 사람들이 직면할 수 있는 가장 일반적인 오류일 것입니다. detoured.dll 파일은 삽입되는 DLL과 동일한 디렉터리에 있어야 합니다.



## 6. 결론

이것이 이 글의 API 후킹에 대한 전부입니다. API 후크를 구현하는 방법에는 확실히 더 많은 방법이 있습니다. 일부는 더 효율적이고 일부는 그렇지 않습니다. 하지만 이 글을 읽고 나면 우회 관점에서 API 후킹을 이해하는 것이 더 쉬울 것입니다. MS Detours를 사용하면 주소 테이블 항목 수정, 프로그램 흐름 변경 등의 세부 사항이 이미 구현되어 있으므로 API 후킹이 크게 단순화됩니다. 이렇게 하면 디버깅 시간이 많이 절약되고 프로그래머가 메모리 손상, PE 헤더를 통한 구문 분석 등에 대한 걱정 없이 원하는 대로 후크를 설정하고 제거할 수 있습니다(제대로 수행된 경우). 독자로서 여러분이 이 글을 통해 일반적인 API 후킹 기술과 이론, 그리고 이러한 기술을 일상적인 애플리케이션에 구현하는 방법에 대해 배웠기를 바랍니다.

