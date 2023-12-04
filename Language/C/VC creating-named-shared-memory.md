# VC creating-named-shared-memory



## 명명된 공유 메모리 만들기

데이터를 공유하기 위해 여러 프로세스는 시스템 페이징 파일이 저장하는 메모리 매핑 파일을 사용할 수 있습니다.



### 첫 번째 프로세스

첫 번째 프로세스는 **INVALID_HANDLE_VALUE** 및 개체의 이름을 사용하여 [**CreateFileMapping**](https://learn.microsoft.com/ko-kr/windows/desktop/api/WinBase/nf-winbase-createfilemappinga) 함수를 호출하여 파일 매핑 개체를 만듭니다. **PAGE_READWRITE** 플래그를 사용하여 프로세스는 생성된 모든 파일 뷰를 통해 메모리에 대한 읽기/쓰기 권한을 가집니다.

그런 다음, [**프로세스는 CreateFileMapping**](https://learn.microsoft.com/ko-kr/windows/desktop/api/WinBase/nf-winbase-createfilemappinga) 이 [**MapViewOfFile**](https://learn.microsoft.com/ko-kr/windows/win32/api/memoryapi/nf-memoryapi-mapviewoffile) 호출에서 반환하는 파일 매핑 개체 핸들을 사용하여 프로세스 주소 공간에서 파일 보기를 만듭니다. **MapViewOfFile** 함수는 파일 뷰 `pBuf`에 대한 포인터를 반환합니다. 그런 다음, 프로세스는 [**CopyMemory**](https://learn.microsoft.com/ko-kr/previous-versions/windows/desktop/legacy/aa366535(v=vs.85)) 함수를 사용하여 다른 프로세스에서 액세스할 수 있는 문자열을 뷰에 씁니다.

파일 매핑 개체 이름을 "Global\"으로 접두사로 지정하면 프로세스가 서로 다른 터미널 서버 세션에 있더라도 서로 통신할 수 있습니다. 이렇게 하려면 첫 번째 프로세스에 [**SeCreateGlobalPrivilege**](https://learn.microsoft.com/ko-kr/windows/win32/secauthz/privilege-constants) 권한이 있어야 합니다.

프로세스에서 파일 매핑 개체에 더 이상 액세스할 필요가 없는 경우 [**CloseHandle**](https://learn.microsoft.com/ko-kr/windows/win32/api/handleapi/nf-handleapi-closehandle) 함수를 호출해야 합니다. 모든 핸들이 닫혀 있으면 시스템에서 개체가 사용하는 페이징 파일의 섹션을 해제할 수 있습니다.

``` C++
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



### 두 번째 프로세스

두 번째 프로세스는 첫 번째 프로세스와 동일한 매핑 개체 이름을 지정하는 [**OpenFileMapping**](https://learn.microsoft.com/ko-kr/windows/desktop/api/WinBase/nf-winbase-openfilemappinga) 함수를 호출하여 첫 번째 프로세스에서 공유 메모리에 기록된 문자열에 액세스할 수 있습니다. 그런 다음 [**MapViewOfFile**](https://learn.microsoft.com/ko-kr/windows/win32/api/memoryapi/nf-memoryapi-mapviewoffile) 함수를 사용하여 파일 뷰 `pBuf`에 대한 포인터를 가져올 수 있습니다. 프로세스는 다른 문자열과 마찬가지로 이 문자열을 표시할 수 있습니다. 이 예제에서 표시되는 메시지 상자에는 첫 번째 프로세스에서 작성한 "첫 번째 프로세스의 메시지"라는 메시지가 포함됩니다.

``` C++
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



### 관련 항목

- [파일 및 메모리 공유](https://learn.microsoft.com/ko-kr/windows/win32/memory/sharing-files-and-memory)





---

## 페이징 파일을 사용하여 공유 메모리를 구현하는 방법

`Sender.cpp`

``` C++
#include <windows.h>
#include <iostream>

int main() {
    // Create a file mapping object.
    HANDLE hMapFile = CreateFileMapping(
        INVALID_HANDLE_VALUE,    // Use paging file
        NULL,                    // Default security
        PAGE_READWRITE,          // Read/write access
        0,                       // Maximum object size (high-order DWORD)
        256,                     // Maximum object size (low-order DWORD)
        L"Global\\MyFileMappingObject"  // Name of mapping object
    );

    if (hMapFile == NULL) {
        std::cout << "Could not create file mapping object." << std::endl;
        return 1;
    }

    // Map the view of the file mapping into the address space of the current process.
    LPVOID pBuf = MapViewOfFile(
        hMapFile,               // Handle of the map object
        FILE_MAP_ALL_ACCESS,    // Read/write permission
        0,
        0,
        256
    );

    if (pBuf == NULL) {
        std::cout << "Could not map view of file." << std::endl;
        CloseHandle(hMapFile);
        return 1;
    }

    // Write to the shared memory.
    CopyMemory((PVOID)pBuf, "Hello from first process!", 26);

    std::cout << "Data written to shared memory: " << (char*)pBuf << std::endl;

    // Wait before closing.
    Sleep(10000);

    // Unmap the file view and close the file mapping object.
    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}

```



`Listener.cpp`

``` C++
#include <windows.h>
#include <iostream>

int main() {
    // Open the named file mapping object.
    HANDLE hMapFile = OpenFileMapping(
        FILE_MAP_ALL_ACCESS,   // Read/write access
        FALSE,                 // Do not inherit the name
        L"Global\\MyFileMappingObject"  // Name of mapping object
    );

    if (hMapFile == NULL) {
        std::cout << "Could not open file mapping object." << std::endl;
        return 1;
    }

    // Map the view of the file mapping into the address space of the current process.
    LPVOID pBuf = MapViewOfFile(
        hMapFile,               // Handle of the map object
        FILE_MAP_ALL_ACCESS,    // Read/write permission
        0,
        0,
        256
    );

    if (pBuf == NULL) {
        std::cout << "Could not map view of file." << std::endl;
        CloseHandle(hMapFile);
        return 1;
    }

    // Read from the shared memory.
    std::cout << "Data read from shared memory: " << (char*)pBuf << std::endl;

    // Unmap the file view and close the file mapping object.
    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}

```



---

## 파일매핑 주요 함수

hF=CreateFile("test.txt")  - 파일 오픈

hMapF=CreateFileMapping(hF)  - 파일 내용을 메모리에 올린다                    		

\- 메모리에 올려진 첫번째 주소를 얻는다.             pF=MapViewOfFile(hMapF)

\- 첫번째 주소로 메모리 내용을 조작한다.

\- 중간중간에 변경된 내용을 강제로 디스크에 쓰게만든다.  	FlushViewOfFile(pF)

\- 해제.                                    							UnmapViewOfFile(pF);

\- 해제.                                    CloseHandle(hMapF);

\- 파일 닫기.                                 CloseHandle(hF);



``` c++
#include <windows.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
 HANDLE hF, hMapF;
 DWORD fSize, i;
 char *pF, *pFtmp;

 hF=CreateFile("test.txt",GENERIC_READ|GENERIC_WRITE, 0,
                                  NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);

 fSize=GetFileSize(hF,NULL);

 hMapF=CreateFileMapping(
                                         hF,                          //file handle
                                         NULL,
                                         PAGE_READWRITE,
                                         0,                           //file size
                                         0,                           //file size
                                         NULL);                    //map name
 if(hMapF==NULL)
 {
  printf("CreateFileMapping() fail");
  CloseHandle(hF);
  return 1;
 }

 pF=MapViewOfFile(hMapF,FILE_MAP_ALL_ACCESS,0,0,0);
 
 pFtmp=pF;

 for(i=0;i<fSize;i++)
 {
  *pFtmp=(*pFtmp+1);
  pFtmp++;
 }

 //메모리 내용을 강제로 파일에 쓴다.
 FlushViewOfFile(pF, 0);

 UnmapViewOfFile(pF);

 CloseHandle(hMapF);

 CloseHandle(hF);

 return 0;
}

```



Share Memory를 만드는 프로세스:

CreateFileMapping->MapViewOfFile->UnmapViewOfFile->CloseHandle



Share Memory를 Open하는 프로세스:

OpenFileMapping->MapViewOfFile->UnmapViewOfFile->CloseHandle

<예제>

<공유메모리를 만들고 공유메모리에 데이터를 써넣는 프로그램>



``` c++
#include <windows.h>
#include <stdio.h>

#pragma pack(1)

typedef struct tag_SMTEST
{
 char c;
 int  i;
 short s;
} SMTEST;


int main(int argc, char *argv[])
{
 HANDLE hMapF;
 SMTEST *psm;

 

// JJBSMTEST라는 이름으로 공유메모리를 만든다.

 hMapF=CreateFileMapping(
                                         INVALID_HANDLE_VALUE,
                                         NULL,
                                         PAGE_READWRITE,
                                         0,    
                                         sizeof(SMTEST),    
                                         "JJBSMTEST");   
 if(hMapF==NULL)
 {
  printf("CreateFileMapping() fail");
  return 1;
 }
 psm=(SMTEST *)MapViewOfFile(hMapF,FILE_MAP_ALL_ACCESS,0,0,sizeof(SMTEST));
//이후부터 psm 포인터만 읽으면 파일의 내용을 자유롭게 액세스할 수 있다. 
// 파일이 프로세스의 주소 공간에 맵되어 있으므로 포인터를사용하면 연결된 파일의 원하는 부분을 읽을 수 있다.

 psm->c='a';
 psm->i=100;
 psm->s=10;

 

//아래 프로그램을 실행할수있게 시간을 주자

 Sleep(10000);
 
 UnmapViewOfFile(psm);

 CloseHandle(hMapF);

 return 0;
}
```



<예제>

<공유메모리에 접근하여 데이터를 출력하는 프로그램.>

``` c++
#include <windows.h>
#include <stdio.h>

#pragma pack(1)

typedef struct tag_SMTEST
{
 char c;
 int  i;
 short s;
} SMTEST;


int main(int argc, char *argv[])
{
 HANDLE hMapF;
 SMTEST *psm;

// JJBSMTEST라는 이름으로 공유메모리에 접근한다.

 hMapF=OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, "JJBSMTEST");
 
 if(hMapF==NULL)
 {
  printf("OpenFileMapping() fail");
  return 1;
 }
 psm=(SMTEST *)MapViewOfFile(hMapF,FILE_MAP_ALL_ACCESS,0,0,sizeof(SMTEST));

 

 //공유메모리에 설정된 내용을 출력한다.
 printf("c:%c i:%d, s:%d\n",psm->c,psm->i,psm->s);
 
 UnmapViewOfFile(psm);

 CloseHandle(hMapF);

 return 0;
}
```





<예제> 파일을 메모리에 올려놓고 마음대로 불러다 쓴다.

``` c++
typedef struct Member
{
 char Id[256];
 char name[256]; 
}Member;


Member * member;
Member mem[1000];

void Init()
{

 int i=0;
  
 for(i=0; i<1000; i++)
 {
  strcpy(mem[i].Id,   "22");
  strcpy(mem[i].name,   "22");
 }
}


void FileSet()
{
 DWORD dwWritten;
 
 
 hFile = CreateFile("c:\\jinyong.dat", GENERIC_WRITE, 0, NULL, CREATE_NEW, FILE_ATTRIBUTE_NORMAL, NULL);
 
 if(hFile!=INVALID_HANDLE_VALUE)
 {
  WriteFile(hFile, mem, sizeof(Member)*1000, &dwWritten, 0);
 }
 
 CloseHandle(hFile);
}


void Mapping()
{
 HANDLE hFile2;

 int i=0;
 hFile2 = CreateFile("c:\\jinyong.dat", GENERIC_READ | GENERIC_WRITE, 0, NULL, OPEN_EXISTING, 0, 0);
 if(hFile2 != INVALID_HANDLE_VALUE)
 {
  hMap = CreateFileMapping(hFile2, NULL, PAGE_READWRITE, 0, 0, NULL);
  member = (Member *)MapViewOfFile(hMap, FILE_MAP_ALL_ACCESS, 0, 0, 0);
 }
 printf("%s\n",member[2].Id);
 printf("%s\n",member[3].name);

}
```



// https://yonghello.tistory.com/entry/%ED%8C%8C%EC%9D%BC%EB%A7%A4%ED%95%91%EC%98%88%EC%A0%9C

