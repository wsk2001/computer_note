# 서비스와 사용자 프로세스간에 메모리를 공유

출처: https://stackoverflow.com/questions/898683/how-to-share-memory-between-services-and-user-processes

**Question>**

CreateFileMapping () 및 MapViewOfFile ()으로 만든 공유 메모리 세그먼트를 사용하여 정보를 공유하는 Win32 응용 프로그램 집합이 있습니다. 응용 프로그램 중 하나는 시스템 서비스입니다. 나머지는 로그인 한 사용자가 시작합니다. Windows XP에서는 아무런 문제가 없었습니다. 우리는 우리의 세그먼트를“Global\\Something”이라고 지었고 모든 것이 잘되었습니다.

Vista (및 Windows 7)의 추가 보안 기능으로 인해이 아키텍처가 작동하지 않습니다. 일반 사용자는 전역 네임 스페이스에서 (Win32 오류 5) 개체를 만들 수 없습니다. MSDN은 계정에 '전역 만들기'권한이 있으면 모든 것이 잘되어야하지만 실제로는 그렇지 않은 것 같습니다. 또한 Vista의 '무결성'기능은 '낮은 무결성'사용자 프로세스가 '높은 무결성'서비스 생성 공유 메모리 개체에 액세스하지 못하도록합니다. 마법의 SetSecurityDescriptorSacl () 명령을 통해이 문제를 해결할 수있을 것 같지만 sacl을 배우는 데 어려움이 있습니다.

서비스와 일반 사용자 프로세스간에 공유 메모리 세그먼트를 사용하는 올바른 방법은 무엇입니까?

'UAC를 끄십시오'라는 쉬운 대답을 선점하기 위해 우리는 상당히 폐쇄 된 환경에 있으며 가능성이 없습니다.

편집 : 서비스와 사용자 프로세스 모두 세그먼트에 대한 읽기/쓰기 액세스 권한이 필요합니다.



**Answer>**

가장 간단한 방법은 서비스에서 공유 메모리를 만들고 CreateFileMapping에서 DACL을 지정하여 일반 사용자에게 공유 메모리에 대한 읽기 권한을 부여하는 것입니다.

일반 사용자에게는 전역 작성 권한이 없지만 서비스에는이 권한이 있습니다. 사용자가 공유 메모리를 작성하고 서비스 프로브를 확보하도록해야하는 경우, 사용자 코드가 파일 맵핑 핸들을 포함하는 서비스에 메시지를 보내는 IPC 스킴을 가질 수 있으며 서비스는 DuplicateHandle을 호출하여 그것을 참조하십시오. 서비스를 디버그 권한으로 실행해야합니다.

DACL을 만드는 가장 간단한 방법은 ACL을 지정하는 SDDL 형식의 문자열을 사용하는 ConvertStringSecurityDescriptorToSecurityDescriptor를 사용하는 것입니다.

[보안 코드 작성](https://rads.stackoverflow.com/amzn/click/com/0735617228)에는 SDDL을 사용한 DACL 작성에 대한 장이 포함되어 있습니다.

```c++
// Error handling removed for brevity
SECURITY_ATTRIBUTES attributes;
ZeroMemory(&attributes, sizeof(attributes));
attributes.nLength = sizeof(attributes);
ConvertStringSecurityDescriptorToSecurityDescriptor(
         L"D:P(A;OICI;GA;;;SY)(A;OICI;GA;;;BA)(A;OICI;GR;;;IU)",
         SDDL_REVISION_1,
         &attributes.lpSecurityDescriptor,
         NULL);

CreateFileMapping(INVALID_HANDLE_VALUE, &attributes,
              PAGE_READWRITE, sizeHigh, sizeLow, L"Global\\MyObject");

LocalFree(attributes.lpSecurityDescriptor);
```

'D : P (A; OICI; GA ;;; SY) (A; OICI; GA ;;; BA) (A; OICI; GR ;;; IU)'는 DACL을 지정한다. D : P는 이것이 SACL이 아니라 DACL임을 의미합니다. SACL은 거의 사용하지 않습니다. 그 다음에 누가 액세스 할 수 있는지 제어하는 몇 개의 ACE 문자열이 있습니다. 각각은 A (허용)이며 객체를 허용하고 상속 (OICI)을 포함합니다. 첫 번째는 시스템 (SY) 및 관리자 (BA, 기본 제공 관리자)에게 모든 액세스 권한을 부여합니다 (GA-모두 부여). 마지막은 실제로 세션에 로그온 한 사용자 인 대화식 사용자 (IU)에게 읽기 (GR) 권한을 부여합니다.

이 작업이 완료되면 일반 사용자는 OpenFileMapping을 호출하여 공유 매핑에 대한 핸들을 가져 와서 프로세스에 매핑 할 수 있어야합니다. 일반 사용자는 개체에 대한 권한이 제한되어 있으므로 개체를 열고 읽기 액세스 전용으로 매핑해야합니다.

쓰기 권한이 필요한 사용자는 GR을 GWGR로 교체해야합니다. 이것은 안전하지 않습니다. 제한된 사용자는 서비스가 정보를 읽고 구문 분석하는 동안 공유 메모리를 수정할 수 있으므로 서비스가 중단 될 수 있습니다.



## 명명 된 공유 메모리 생성

출처: https://docs.microsoft.com/en-us/windows/win32/memory/creating-named-shared-memory

데이터를 공유하기 위해 여러 프로세스가 시스템 페이징 파일이 저장하는 메모리 매핑 파일을 사용할 수 있습니다.



### 첫 번째 process

첫 번째 프로세스는 INVALID_HANDLE_VALUE 및 오브젝트 이름으로 CreateFileMapping 함수를 호출하여 파일 맵핑 오브젝트를 작성합니다. PAGE_READWRITE 플래그를 사용하면 프로세스는 작성된 파일 View 를 통해 메모리에 대한 읽기/쓰기 권한을 갖습니다.

그런 다음 프로세스는 CreateFileMapping이 MapViewOfFile을 호출하여 리턴하는 파일 맵핑 오브젝트 핸들을 사용하여 프로세스 주소 공간에 파일 View 를 작성합니다. MapViewOfFile 함수는 파일 View pBuf에 대한 포인터를 리턴합니다. 그런 다음 프로세스는 CopyMemory 함수를 사용하여 다른 프로세스에서 액세스 할 수있는 View 에 문자열을 씁니다.

'Global\\'로 파일 맵핑 오브젝트 이름을 접두어로 사용하면 프로세스가 서로 다른 터미널 서버 세션에있는 경우에도 프로세스가 서로 통신 할 수 있습니다. 이를 위해서는 첫 번째 프로세스에 SeCreateGlobalPrivilege 권한이 있어야합니다.

프로세스가 더 이상 파일 맵핑 오브젝트에 액세스 할 필요가 없으면 CloseHandle 함수를 호출해야합니다. 모든 핸들이 닫히면 시스템은 객체가 사용하는 페이징 파일의 섹션을 해제 할 수 있습니다.

```c++
#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <tchar.h>

#define BUF_SIZE 256
TCHAR szName[]=TEXT("Global\\MyFileMappingObject");
TCHAR szMsg[]=TEXT("Message from first process.");

int _tmain()
{
   HANDLE hMapFile;
   LPCTSTR pBuf;

   hMapFile = CreateFileMapping(
                 INVALID_HANDLE_VALUE,    // use paging file
                 NULL,                    // default security
                 PAGE_READWRITE,          // read/write access
                 0,                       // maximum object size (high-order DWORD)
                 BUF_SIZE,                // maximum object size (low-order DWORD)
                 szName);                 // name of mapping object

   if (hMapFile == NULL)
   {
      _tprintf(TEXT("Could not create file mapping object (%d).\n"),
             GetLastError());
      return 1;
   }
   pBuf = (LPTSTR) MapViewOfFile(hMapFile,   // handle to map object
                        FILE_MAP_ALL_ACCESS, // read/write permission
                        0,
                        0,
                        BUF_SIZE);

   if (pBuf == NULL)
   {
      _tprintf(TEXT("Could not map view of file (%d).\n"),
             GetLastError());

       CloseHandle(hMapFile);

      return 1;
   }


   CopyMemory((PVOID)pBuf, szMsg, (_tcslen(szMsg) * sizeof(TCHAR)));
    _getch();

   UnmapViewOfFile(pBuf);

   CloseHandle(hMapFile);

   return 0;
}
```



### 두 번째 process

두 번째 프로세스는 첫 번째 프로세스와 동일한 이름을 지정하는 OpenFileMapping 함수를 호출하여 첫 번째 프로세스가 공유 메모리에 쓴 문자열에 액세스 할 수 있습니다. 그런 다음 MapViewOfFile 함수를 사용하여 파일보기 pBuf에 대한 포인터를 얻을 수 있습니다. 프로세스는이 문자열을 다른 문자열처럼 표시 할 수 있습니다. 이 예에서 표시된 메시지 상자에는 첫 번째 프로세스에서 작성한 '첫 번째 프로세스의 메시지'메시지가 포함됩니다.

```c++
#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <tchar.h>
#pragma comment(lib, "user32.lib")

#define BUF_SIZE 256
TCHAR szName[]=TEXT("Global\\MyFileMappingObject");

int _tmain()
{
   HANDLE hMapFile;
   LPCTSTR pBuf;

   hMapFile = OpenFileMapping(
                   FILE_MAP_ALL_ACCESS,   // read/write access
                   FALSE,                 // do not inherit the name
                   szName);               // name of mapping object

   if (hMapFile == NULL)
   {
      _tprintf(TEXT("Could not open file mapping object (%d).\n"),
             GetLastError());
      return 1;
   }

   pBuf = (LPTSTR) MapViewOfFile(hMapFile, // handle to map object
               FILE_MAP_ALL_ACCESS,  // read/write permission
               0,
               0,
               BUF_SIZE);

   if (pBuf == NULL)
   {
      _tprintf(TEXT("Could not map view of file (%d).\n"),
             GetLastError());

      CloseHandle(hMapFile);

      return 1;
   }

   MessageBox(NULL, pBuf, TEXT("Process2"), MB_OK);

   UnmapViewOfFile(pBuf);

   CloseHandle(hMapFile);

   return 0;
}
```

