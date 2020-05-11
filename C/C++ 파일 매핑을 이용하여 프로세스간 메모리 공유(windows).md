# 파일 매핑을 이용하여 프로세스간 메모리 공유

출처: https://wwwi.tistory.com/63

프로세스간 통신이 되면 여러가지로 편리한 점이 있다.
프로세스간 통신을 하는 간단한 방법으로는 메모리 맵 파일이 있다.

어떤 프로세스에서 쓰고 있는 메모리는 가상 메모리로 그 어드레스는 가상 메모리의 어드레스이다.
따라서 다른 프로세스에 있어서는 의미가 없는 어드레스이다.

어떤 프로세스에서 물리 메모리의 어드레스를 가상 메모리로 매핑하여 읽고 쓰면
다른 프로세스에서도 그 메모리에 대하여 읽고 쓰기가 가능하다.


파일 매핑을 이용한 프로스세간 통신 예제

◆ 공유 메모리 생성과 공유 메모리에 쓰기(서버)

```c++
#include <stdio.h>
#include <windows.h>
#include <conio.h>

#define    DATA_LEN    1024

LPSTR    lpMapping;

int main()
{
    BOOL    bEnd = FALSE;
    char    szData[DATA_LEN];
    char    szNo[8];
    HANDLE  hMemMap = NULL;

    while (1)
    {
        printf("1. 공유 메모리 데이터 쓰기\");
        printf("0. 종료\");
        printf("원하는 작업의 번호를 눌러주세요.");

        gets(szNo);

        switch (szNo[0])
        {
            case '1':
                // 매핑 파일 만들기
                hMemMap = CreateFileMapping((HANDLE)0xFFFFFFFF,
                                NULL,
                                PAGE_READWRITE,
                                0,
                                DATA_LEN,
                                "MemoryMapTest");

                if (hMemMap == NULL)
                    return -1;

                if (GetLastError() == ERROR_ALREADY_EXISTS)
                    printf("이미 매핑 오브젝트가 있음.\n");

                // 파일에 매핑하기
                lpMapping = (LPSTR)MapViewOfFile(hMemMap,
                                FILE_MAP_ALL_ACCESS,
                                0,
                                0,
                                0);

                if (lpMapping == NULL)
                    return -2;

                printf(" === 매핑 파일에 데이터 입력 ===\n");

                gets(szData);


                // 매핑 파일에 데이터 입력
                strcpy(lpMapping, szData);
                UnmapViewOfFile(lpMapping);
                break;

            case '0':
                bEnd = TRUE;
                break;

            default:
                printf("번호가 잘못됬어\\n");
                break;
        }

        if (bEnd)
            break;
    }

    if (CloseHandle(hMemMap) == 0)
        printf("메모리맵 핸들 종료 실패\");

    else
        printf("메모리맵 핸들 종료 성공\");


    return 0;
}
```



◆ 공유 메모리 열기와 공유 메모리의 데이터 읽기(클라이언트)

```c++
#include <stdio.h>
#include <windows.h>
#include <conio.h>

LPSTR    lpMapping;

int main()
{
    BOOL    bEnd = FALSE;
    char    szNo[8];
    HANDLE    hMapRead;

    while (1)
    {
        printf("1. 공유 메모리 데이터 읽기\");
        printf("0. 종료\");
        printf("원하는 작업의 번호를 눌러주세요.");

        gets(szNo);

        switch (szNo[0])
        {
            case '1':
                // 매핑 파일 열기
                hMapRead = OpenFileMapping(FILE_MAP_ALL_ACCESS,
                                FALSE,
                                "MemoryMapTest");

                // 파일에 매핑하기
                lpMapping = (LPSTR)MapViewOfFile(hMapRead,
                                FILE_MAP_ALL_ACCESS,
                                0,
                                0,
                                0);

                if (lpMapping == NULL)
                {
                    printf("읽기 실패\");
                    CloseHandle(hMapRead);
                    return -1;
                }

                // 읽어들인 데이터 출력
                printf(lpMapping);

                if (UnmapViewOfFile(lpMapping) == 0)
                {
                    printf("\n읽기용 어드래스 매핑 실패\");
                }

                else
                {
                    printf("\n읽기용 어드래스 매핑 성공\");
                    lpMapping = NULL;
                }

                CloseHandle(hMapRead);

                break;

            case '0':
                bEnd = TRUE;
                break;

            default:
                printf("번호가 잘못됬어\\n");
                break;
        }

        if (bEnd)
            break;
    }

    return 0;
}
```

콘솔로 프로젝트를 만들어 위 소스를 붙여 넣어 서버와 클라이언트 프로그램을 만든다.
서버 프로그램을 실행시켜 1을 선택하여 데이터를 입력한다.

클라이언트 프로그램에서 1을 선택하면 서버 프로그램에서 입력한 데이터를 참조 할 수 있다.



[![img](https://t1.daumcdn.net/tistory_admin/assets/blog/3a7f996138f6cde9823c94a88ad69999312529e0/blogs/image/extension/zip.gif?_version_=3a7f996138f6cde9823c94a88ad69999312529e0) invalid-file](https://wwwi.tistory.com/attachment/dk38.zip)

간단 메모리 맵핑 예제 소스

```c++
#include <stdio.h>
#include <windows.h>
#include <conio.h>

int nMapRead(LPSTR);
int nMapWrite(LPSTR);

HANDLE hfMemMap;

int main()
{
	static LPSTR lpAddress1, lpAddress2;

	char szNo[8];
	BOOL bEnd = FALSE;

	while (1) 
	{
		printf("***************\n");
		printf("1. Writre\n");
		printf("2. Read\n");
		printf("0. End\n");
		printf("***************\n");
		printf("No. = ");

		gets(szNo);

		switch (szNo[0]) 
		{
			case '1':
				nMapWrite(lpAddress1);
				break;
			case '2':
				nMapRead(lpAddress2);
				break;
			case '0':
				bEnd = TRUE;
				break;
			default:
				printf("번호가 잘못됬어\n\n");
				break;
		}
		if (bEnd)
			break;
    }

    if (hfMemMap) 
	{
		if (CloseHandle(hfMemMap) == 0)
			printf("메모리맵 핸들 종료 실패\n");
		else
			printf("메모리맵 핸들 종료 성공\n");
    }

    return 0;
}

int nMapWrite(LPSTR lpStr)
{
	char szData[1024];

	if (!hfMemMap)
		CloseHandle(hfMemMap);

	hfMemMap = CreateFileMapping((HANDLE) - 1, 
					NULL,
					PAGE_READWRITE,
					0, 
					1024,
					"MemoryMapTest");
	if (hfMemMap == NULL)
		return -1;
	if (GetLastError() == ERROR_ALREADY_EXISTS)
		printf("이미 매핑 오브젝트가 있어요.\n");

	lpStr = (LPSTR)MapViewOfFile(hfMemMap,
					FILE_MAP_ALL_ACCESS, 
					0, 
					0, 
					0);
	if (lpStr == NULL)
		return -2;

	printf("쓰기 ==");

	gets(szData);
	strcpy(lpStr, szData);
	strcat(lpStr, "\n");

	UnmapViewOfFile(lpStr);

	printf("쓰기 끝\n");
	return 0;
}

int nMapRead(LPSTR lpStr)
{
    HANDLE hfMapRead;

    hfMapRead = OpenFileMapping(FILE_MAP_ALL_ACCESS, 
					FALSE, 
					"MemoryMapTest");
        
    lpStr = (LPSTR)MapViewOfFile(hfMapRead,
					FILE_MAP_ALL_ACCESS, 
					0, 
					0, 
					0);

    if (lpStr == NULL) 
	{
        printf("일기 실패\n");
        CloseHandle(hfMapRead);
        return -1;
    }

    printf(lpStr);
    
	if (UnmapViewOfFile(lpStr) == 0) 
	{
        printf("읽기용 어드래스 매핑 실패\n");
    } 
	else 
	{
        printf("읽기용 어드래스 매핑 성공\n");
        lpStr = NULL;
    }

    CloseHandle(hfMapRead);

    return 0;
}

```





---

`writw_shm.c`

```c++
#include <windows.h>
#include <stdio.h>

#pragma pack(1)

typedef struct tag_SMTEST
{
    char  c;
    int   i;
    short s;
} SMTEST;

int main(int argc, char *argv[])
{
    HANDLE hMapF;
    SMTEST *psm;

 

    // JJBSMTEST라는 이름으로 공유메모리를 만든다.
    hMapF=CreateFileMapping( INVALID_HANDLE_VALUE,
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
    psm = (SMTEST *) MapViewOfFile(hMapF,FILE_MAP_ALL_ACCESS,0,0,sizeof(SMTEST));

    psm->c = 'a';
    psm->i = 100;
    psm->s = 10; 

    // 아래 프로그램을 실행할수있게 시간을 주자
    Sleep(10000);
 
    UnmapViewOfFile(psm);

    CloseHandle(hMapF);

    return 0;
}

 
```



`read_shm.c`

```c++
#include <windows.h>
#include <stdio.h>

#pragma pack(1)

typedef struct tag_SMTEST
{
    char  c;
    int   i;
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
    psm = (SMTEST *) MapViewOfFile(hMapF,FILE_MAP_ALL_ACCESS,0,0,sizeof(SMTEST));


    // 공유메모리에 설정된 내용을 출력한다.
    printf("c:%c i:%d, s:%d\n", psm->c, psm->i, psm->s);
 
    UnmapViewOfFile(psm);

    CloseHandle(hMapF);

    return 0;
}
```



## 다른 Sample

---
### First Process

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



### Second Process

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



## 관련 함수

### CreateFileMappingA function

지정된 파일에 대한 명명 된 또는 명명되지 않은 파일 매핑 개체를 만들거나 엽니 다.



#### Syntax

```c++
HANDLE CreateFileMappingA(
  HANDLE                hFile,
  LPSECURITY_ATTRIBUTES lpFileMappingAttributes,
  DWORD                 flProtect,
  DWORD                 dwMaximumSizeHigh,
  DWORD                 dwMaximumSizeLow,
  LPCSTR                lpName
);
```



#### Parameters

`Parameters`

파일 맵핑 오브젝트를 작성할 파일에 대한 핸들입니다.

FlProtect 매개 변수가 지정하는 보호 플래그와 호환되는 액세스 권한으로 파일을 열어야합니다. 반드시 필요한 것은 아니지만 매핑하려는 파일을 단독 액세스를 위해 열 것을 권장합니다. 자세한 내용은 파일 보안 및 액세스 권한을 참조하십시오.

hFile이 INVALID_HANDLE_VALUE 인 경우 호출 프로세스는 또한 dwMaximumSizeHigh 및 dwMaximumSizeLow 매개 변수에서 파일 맵핑 오브젝트의 크기를 지정해야합니다. 이 시나리오에서 CreateFileMapping은 파일 시스템의 파일이 아니라 시스템 페이징 파일이 지원하는 지정된 크기의 파일 맵핑 오브젝트를 작성합니다.

`lpFileMappingAttributes`

자식 프로세스가 반환 된 핸들을 상속 할 수 있는지 여부를 결정하는 SECURITY_ATTRIBUTES 구조에 대한 포인터입니다. SECURITY_ATTRIBUTES 구조의 lpSecurityDescriptor 멤버는 새 파일 맵핑 오브젝트의 보안 설명자를 지정합니다.

LpFileMappingAttributes가 NULL이면 핸들을 상속 할 수 없으며 파일 맵핑 오브젝트가 기본 보안 설명자를 가져옵니다. 파일 매핑 개체에 대한 기본 보안 설명 자의 액세스 제어 목록 (ACL)은 작성자의 기본 또는 가장 토큰에서 가져옵니다. 자세한 내용은 파일 매핑 보안 및 액세스 권한을 참조하십시오.

`flProtect`

파일 맵핑 오브젝트의 페이지 보호를 지정합니다. 객체의 모든 매핑 된 뷰는이 보호 기능과 호환되어야합니다.



이 매개 변수는 다음 값 중 하나 일 수 있습니다.

| Value                           | Meaning                                                      |
| :------------------------------ | :----------------------------------------------------------- |
| **PAGE_EXECUTE_READ** 0x20      | 보기를 읽기 전용, 기록 중 복사 또는 실행 액세스로 매핑 할 수 있습니다.<br/>HFile 매개 변수로 지정된 파일 핸들은 GENERIC_READ 및 GENERIC_EXECUTE 액세스 권한으로 작성해야합니다. <br/>Windows Server 2003 및 Windows XP :이 값은 Windows XP SP2 및 Windows Server 2003 SP1까지 사용할 수 없습니다. |
| **PAGE_EXECUTE_READWRITE** 0x40 | 보기를 읽기 전용, 쓰기시 복사, 읽기 / 쓰기 또는 실행 액세스로 맵핑 할 수 있습니다.  HFile 매개 변수가 지정하는 파일 핸들은 GENERIC_READ, GENERIC_WRITE 및 GENERIC_EXECUTE 액세스 권한으로 작성해야합니다. |
| **PAGE_EXECUTE_WRITECOPY** 0x80 | 보기를 읽기 전용, 기록 중 복사 또는 실행 액세스로 매핑 할 수 있습니다. 이 값은 PAGE_EXECUTE_READ와 같습니다. HFile 매개 변수가 지정하는 파일 핸들은 GENERIC_READ 및 GENERIC_EXECUTE 액세스 권한으로 작성해야합니다. <br/>Windows Vista :이 값은 Windows Vista SP1까지 사용할 수 없습니다.<br/>Windows Server 2003 및 Windows XP :이 값은 지원되지 않습니다. |
| **PAGE_READONLY** 0x02          | 읽기 전용 또는 쓰기시 복사 액세스를 위해 뷰를 매핑 할 수 있습니다. 특정 지역에 쓰려고하면 액세스 위반이 발생합니다.<br/>HFile 매개 변수가 지정하는 파일 핸들은 GENERIC_READ 액세스 권한으로 작성해야합니다. |
| **PAGE_READWRITE** 0x04         | 보기를 읽기 전용, 쓰기시 복사 또는 읽기 / 쓰기 액세스로 매핑 할 수 있습니다.<br/>HFile 매개 변수가 지정하는 파일 핸들은 GENERIC_READ 및 GENERIC_WRITE 액세스 권한으로 작성해야합니다. |
| **PAGE_WRITECOPY** 0x08         | 읽기 전용 또는 쓰기시 복사 액세스를 위해 뷰를 매핑 할 수 있습니다. 이 값은 PAGE_READONLY와 같습니다.<br/>HFile 매개 변수가 지정하는 파일 핸들은 GENERIC_READ 액세스 권한으로 작성해야합니다. |

응용 프로그램은 파일 맵핑 오브젝트에 대해 다음 페이지 속성 값 중 하나와 결합하여 하나 이상의 다음 속성을 지정할 수 있습니다.

| Value                               | Meaning                                                      |
| :---------------------------------- | :----------------------------------------------------------- |
| **SEC_COMMIT** 0x8000000            | 파일 맵핑 오브젝트가 운영 체제 페이징 파일에 의해 백업되는 경우 (hfile 매개 변수는 INVALID_HANDLE_VALUE 임) 파일의보기가 프로세스 주소 공간에 맵핑 될 때 전체 페이지 범위가 예약되지 않고 커미트되도록 지정합니다. 시스템은 전체 맵핑을 보유 할 수있는 확약 가능 페이지가 충분해야합니다. <br/>그렇지 않으면 CreateFileMapping이 실패합니다.<br/>이 속성은 실행 가능 이미지 파일 또는 데이터 파일로 백업되는 파일 맵핑 오브젝트에는 영향을 미치지 않습니다 (hfile 매개 변수는 파일에 대한 핸들 임).<br/>SEC_COMMIT은 SEC_RESERVE와 결합 할 수 없습니다.<br/>속성을 지정하지 않으면 SEC_COMMIT로 가정됩니다. |
| **SEC_IMAGE** 0x1000000             | HFile 매개 변수가 지정하는 파일이 실행 가능 이미지 파일임을 지정합니다.<br/>SEC_IMAGE 속성은 PAGE_READONLY와 같은 페이지 보호 값과 결합되어야합니다. <br/>그러나 이 페이지 보호 값은 실행 가능 이미지 파일의보기에는 영향을 미치지 않습니다. <br/>실행 가능 이미지 파일의보기에 대한 페이지 보호는 실행 파일 자체에 의해 결정됩니다.<br/>SEC_IMAGE에 유효한 다른 속성은 없습니다. |
| **SEC_IMAGE_NO_EXECUTE** 0x11000000 | HFile 매개 변수가 지정하는 파일이 실행되지 않는 실행 가능 이미지 파일이고로드 된 이미지 파일에 강제 무결성 검사가 실행되지 않도록 지정합니다. <br/>또한 SEC_IMAGE_NO_EXECUTE 속성으로 생성 된 파일 매핑 개체의 뷰를 매핑하면 PsSetLoadImageNotifyRoutine 커널 API를 사용하여 등록 된 드라이버 콜백이 호출되지 않습니다.<br/>SEC_IMAGE_NO_EXECUTE 속성은 PAGE_READONLY 페이지 보호 값과 결합되어야합니다. SEC_IMAGE_NO_EXECUTE에 유효한 다른 속성은 없습니다.<br/>Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista, Windows Server 2003 및 Windows XP :이 값은 Windows Server 2012 및 Windows 8 이전에는 지원되지 않습니다. |
| **SEC_LARGE_PAGES** 0x80000000      | 운영 체제 페이징 파일 (hfile 매개 변수는 INVALID_HANDLE_VALUE 임)이 지원하는 파일 맵핑 오브젝트에 큰 페이지를 사용할 수 있습니다. <br/>실행 가능 이미지 파일 또는 데이터 파일이 지원하는 파일 맵핑 오브젝트에는이 속성이 지원되지 않습니다 (hFile 매개 변수는 실행 가능 이미지 또는 데이터 파일에 대한 핸들입니다).<br/>파일 매핑 개체의 최대 크기는 GetLargePageMinimum 함수에서 반환 한 큰 페이지의 최소 크기의 배수 여야합니다. <br/>그렇지 않으면 CreateFileMapping이 실패합니다. <br/>SEC_LARGE_PAGES로 작성된 파일 맵핑 오브젝트의보기를 맵핑 할 때 기본 주소 및보기 크기도 최소 큰 페이지 크기의 배수 여야합니다.<br/>SEC_LARGE_PAGES를 사용하려면 발신자의 토큰에서 SeLockMemoryPrivilege 권한을 활성화해야합니다.<br/>SEC_LARGE_PAGES가 지정되면 SEC_COMMIT도 지정해야합니다.<br/>Windows Server 2003 :이 값은 Windows Server 2003 SP1까지 지원되지 않습니다.<br/>Windows XP :이 값은 지원되지 않습니다. |
| **SEC_NOCACHE** 0x10000000          | 모든 페이지를 캐시 할 수 없도록 설정합니다.<br/>장치에 명시 적으로 필요한 경우를 제외하고 응용 프로그램은이 특성을 사용하지 않아야합니다. <br/>SEC_NOCACHE로 매핑 된 메모리와 연동 된 함수를 사용하면 EXCEPTION_ILLEGAL_INSTRUCTION 예외가 발생할 수 있습니다.<br/>SEC_NOCACHE는 SEC_RESERVE 또는 SEC_COMMIT 속성을 설정해야합니다. |
| **SEC_RESERVE** 0x4000000           | 파일 맵핑 오브젝트가 운영 체제 페이징 파일에 의해 백업되는 경우 (hfile 매개 변수는 INVALID_HANDLE_VALUE 임) 파일의보기가 <br/>프로세스 주소 공간에 맵핑 될 때 프로세스가 나중에 사용하기 위해 전체 페이지 범위를 예약하도록 지정합니다.<br/>예약 된 페이지는 VirtualAlloc 함수에 대한 후속 호출에서 커밋 될 수 있습니다. <br/>페이지가 커밋 된 후에는 VirtualFree 기능을 사용하여 해제하거나 해제 할 수 없습니다.<br/>이 속성은 실행 가능 이미지 파일 또는 데이터 파일로 백업되는 파일 맵핑 오브젝트에는 영향을 미치지 않습니다 (hfile 매개 변수는 파일에 대한 핸들 임).<br/>SEC_RESERVE는 SEC_COMMIT과 결합 할 수 없습니다. |
| **SEC_WRITECOMBINE** 0x40000000     | 모든 페이지가 쓰기 결합되도록 설정합니다.<br/>장치에 명시 적으로 필요한 경우를 제외하고 응용 프로그램은 이 특성을 사용하지 않아야합니다. <br/>SEC_WRITECOMBINE으로 매핑 된 메모리와 연동 된 함수를 사용하면 EXCEPTION_ILLEGAL_INSTRUCTION 예외가 발생할 수 있습니다.<br/>SEC_WRITECOMBINE에는 SEC_RESERVE 또는 SEC_COMMIT 속성이 설정되어 있어야합니다.<br/>Windows Server 2003 및 Windows XP :이 플래그는 Windows Vista까지 지원되지 않습니다. |



`dwMaximumSizeHigh`

파일 맵핑 오브젝트의 최대 크기의 상위 DWORD입니다.



`dwMaximumSizeLow`

파일 맵핑 오브젝트의 최대 크기의 하위 DWORD입니다.

이 매개 변수와 dwMaximumSizeHigh가 0이면, 파일 맵핑 오브젝트의 최대 크기는 hFile이 식별하는 파일의 현재 크기와 같습니다.

길이가 0 인 파일을 매핑하려는 시도는 오류 코드 ERROR_FILE_INVALID와 함께 실패합니다. 애플리케이션은 길이가 0 인 파일을 테스트하고 해당 파일을 거부해야합니다.

`lpName`

파일 매핑 개체의 이름입니다.

이 매개 변수가 기존 매핑 객체의 이름과 일치하면이 함수는 flProtect가 지정하는 보호 기능으로 객체에 대한 액세스를 요청합니다.

이 매개 변수가 NULL이면 파일 맵핑 오브젝트가 이름없이 작성됩니다.

LpName이 기존 이벤트, 세마포어, 뮤텍스, 대기 가능 타이머 또는 작업 객체의 이름과 일치하면 함수가 실패하고 GetLastError 함수가 ERROR_INVALID_HANDLE을 반환합니다. 이러한 개체는 동일한 네임 스페이스를 공유하기 때문에 발생합니다.

이름은 'Global'또는 'Local'접두어를 사용하여 전역 또는 세션 네임 스페이스에서 개체를 명시 적으로 만들 수 있습니다. 이름의 나머지 부분에는 백 슬래시 문자 ()를 제외한 모든 문자가 포함될 수 있습니다. 세션 0 이외의 세션에서 글로벌 네임 스페이스에 파일 매핑 개체를 만들려면 SeCreateGlobalPrivilege 권한이 필요합니다. 자세한 내용은 커널 객체 네임 스페이스를 참조하십시오.

터미널 서비스 세션을 사용하여 빠른 사용자 전환이 구현됩니다. 첫 번째 로그온 사용자는 세션 0 (제로)을 사용하고 다음 로그온 사용자는 세션 1 (1) 등을 사용합니다. 응용 프로그램이 여러 사용자를 지원할 수 있도록 커널 개체 이름은 터미널 서비스에 대해 설명 된 지침을 따라야합니다.



#### Return value

함수가 성공하면 리턴 값은 새로 작성된 파일 맵핑 오브젝트에 대한 핸들입니다.

함수 호출 전에 개체가 존재하면 함수는 기존 개체에 대한 핸들 (현재 크기가 아닌 지정된 크기)을 반환하고 GetLastError는 ERROR_ALREADY_EXISTS를 반환합니다.

함수가 실패하면 리턴 값은 NULL입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

파일 맵핑 오브젝트가 작성된 후 파일 크기는 파일 맵핑 오브젝트의 크기를 초과하지 않아야합니다. 그렇다면 모든 파일 내용을 공유 할 수있는 것은 아닙니다.

응용 프로그램에서 디스크의 실제 명명 된 파일 크기보다 큰 파일 매핑 개체의 크기를 지정하고 페이지 보호가 쓰기 액세스를 허용하는 경우 (즉, flProtect 매개 변수는 PAGE_READWRITE 또는 PAGE_EXECUTE_READWRITE를 지정) 디스크의 파일 파일 맵핑 오브젝트의 지정된 크기와 일치하도록 증가합니다. 파일이 확장되면 파일의 이전 끝과 파일의 새 끝 사이의 파일 내용이 0이되는 것은 아닙니다. 동작은 파일 시스템에 의해 정의됩니다. 디스크의 파일을 늘릴 수 없으면 CreateFileMapping이 실패하고 GetLastError가 ERROR_DISK_FULL을 반환합니다.

운영 체제 페이징 파일이 지원하는 파일 맵핑 오브젝트에서 페이지의 초기 내용은 0입니다.

CreateFileMapping이 리턴하는 핸들은 새 파일 맵핑 오브젝트에 대한 전체 액세스 권한을 가지며 파일 맵핑 오브젝트에 대한 핸들이 필요한 모든 함수와 함께 사용할 수 있습니다.

여러 프로세스가 단일 공유 파일 맵핑 오브젝트를 사용하거나 동일한 파일이 지원하는 별도의 파일 맵핑 오브젝트를 작성하여 동일한 파일의보기를 공유 할 수 있습니다. 단일 파일 맵핑 오브젝트는 프로세스 작성시 핸들 상속, 핸들 복제 또는 이름으로 파일 맵핑 오브젝트 열기를 통해 여러 프로세스가 공유 할 수 있습니다. 자세한 정보는 CreateProcess, DuplicateHandle 및 OpenFileMapping 함수를 참조하십시오.

파일 맵핑 오브젝트를 작성하면 실제로 뷰를 프로세스 주소 공간에 맵핑하지 않습니다. MapViewOfFile 및 MapViewOfFileEx 함수는 파일보기를 프로세스 주소 공간에 맵핑합니다.

한 가지 중요한 예외를 제외하면, 동일한 파일이 지원하는 모든 파일 맵핑 오브젝트에서 파생 된 파일보기는 특정 시간에 일관성이 있거나 동일합니다. 일관성은 프로세스 내의 뷰와 다른 프로세스에 의해 매핑 된 뷰에 대해 보장됩니다.

원격 파일과 관련된 예외입니다. CreateFileMapping은 원격 파일에서 작동하지만 일관성을 유지하지는 않습니다. 예를 들어, 두 대의 컴퓨터가 모두 파일을 쓰기 가능으로 매핑하고 두 컴퓨터가 모두 같은 페이지를 변경하면 각 컴퓨터는 페이지에 대한 자체 쓰기 만 볼 수 있습니다. 디스크에서 데이터가 업데이트되면 병합되지 않습니다.

입력 및 출력 (I / O) 기능 (ReadFile 및 WriteFile)을 사용하여 액세스되는 맵핑 된 파일 및 파일은 반드시 일관성이있는 것은 아닙니다.

파일 맵핑 오브젝트의 맵핑 된보기는 오브젝트에 대한 내부 참조를 유지하며 파일 맵핑 오브젝트는 오브젝트에 대한 모든 참조가 해제 될 때까지 닫히지 않습니다. 따라서 파일 매핑 개체를 완전히 닫으려면 응용 프로그램에서 UnmapViewOfFile을 호출하여 파일 매핑 개체의 모든 매핑 된 뷰를 매핑 해제하고 CloseHandle을 호출하여 파일 매핑 개체 핸들을 닫아야합니다. 이 함수들은 어떤 순서로든 호출 될 수 있습니다.

맵핑 된보기를 통해 파일을 수정하면 마지막 수정 시간 소인이 자동으로 업데이트되지 않을 수 있습니다. 필요한 경우 호출자는 SetFileTime을 사용하여 타임 스탬프를 설정해야합니다.

세션 0 이외의 세션에서 글로벌 네임 스페이스에 파일 매핑 개체를 만들려면 SeCreateGlobalPrivilege 권한이 필요합니다. 이 권한 점검은 파일 맵핑 오브젝트 작성으로 제한되며 기존 오브젝트 열기에는 적용되지 않습니다. 예를 들어 서비스 나 시스템이 전역 네임 스페이스에 파일 매핑 개체를 만드는 경우 호출자가 필요한 액세스 권한을 가지고 있으면 세션에서 실행중인 모든 프로세스가 해당 파일 매핑 개체에 액세스 할 수 있습니다.

Windows XP : 이전 단락에서 설명한 요구 사항은 Windows Server Windows2003 및 Windows XP SP2에서 도입되었습니다.

구조적 예외 처리를 사용하여 파일보기에서 쓰거나 읽는 코드를 보호하십시오. 자세한 내용은 파일보기에서 읽고 쓰기를 참조하십시오.

실행 권한으로 매핑하려면 응용 프로그램에서 PAGE_EXECUTE_READWRITE 또는 PAGE_EXECUTE_READ를 사용하여 CreateFileMapping을 호출 한 다음 FILE_MAP_EXECUTE | FILE_MAP_WRITE 또는 FILE_MAP_EXECUTE | FILE_MAP_READ.



#### Examples

예를들어, [Creating Named Shared Memory](https://docs.microsoft.com/windows/desktop/Memory/creating-named-shared-memory) 또는 [Creating a File Mapping Using Large Pages](https://docs.microsoft.com/windows/desktop/Memory/creating-a-file-mapping-using-large-pages) 를 참조 하십시요.



### OpenFileMappingA function

명명 된 파일 매핑 개체를 엽니 다.

#### Syntax

```c++
HANDLE OpenFileMappingA(
  DWORD  dwDesiredAccess,
  BOOL   bInheritHandle,
  LPCSTR lpName
);
```

#### Parameters

`dwDesiredAccess`

파일 매핑 개체에 대한 액세스 이 액세스는 대상 파일 맵핑 오브젝트의 보안 설명자에 대해 점검됩니다. 값 목록은 파일 매핑 보안 및 액세스 권한을 참조하십시오.

`bInheritHandle`

이 매개 변수가 TRUE이면 CreateProcess 함수로 작성된 프로세스가 핸들을 상속 할 수 있습니다. 그렇지 않으면 핸들을 상속 할 수 없습니다.

`lpName`

열 파일 매핑 개체의 이름입니다. 

이 이름으로 파일 맵핑 오브젝트에 대한 열린 핸들이 있고 맵핑 오브젝트의 보안 설명자가 dwDesiredAccess 매개 변수와 충돌하지 않으면 열기 조작이 성공합니다. 이름은 'Global\\'또는 'Local\\'접두어를 사용하여 전역 또는 세션 네임 스페이스에서 개체를 명시 적으로 열 수 있습니다. 이름의 나머지 부분에는 백 슬래시 문자 (\\)를 제외한 모든 문자가 포함될 수 있습니다. 자세한 내용은 커널 객체 네임 스페이스를 참조하십시오. 빠른 사용자 전환은 터미널 서비스 세션을 사용하여 구현됩니다. 첫 번째 로그온 사용자는 세션 0을 사용하고 다음 로그온 사용자는 세션 1을 사용합니다. 응용 프로그램이 여러 사용자를 지원할 수 있도록 커널 개체 이름은 터미널 서비스에 대해 설명 된 지침을 따라야합니다.



#### Return value

함수가 성공하면 리턴 값은 지정된 파일 맵핑 오브젝트에 대한 열린 핸들입니다.

함수가 실패하면 리턴 값은 NULL입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

OpenFileMapping이 반환하는 핸들은 파일 매핑 개체에 대한 핸들이 필요한 모든 함수와 함께 사용할 수 있습니다.

맵핑 된보기를 통해 파일을 수정하면 마지막 수정 시간 소인이 자동으로 업데이트되지 않을 수 있습니다. 필요한 경우 호출자는 SetFileTime을 사용하여 타임 스탬프를 설정해야합니다.

더 이상 필요하지 않으면 호출자는 OpenHandMapping에 의해 반환 된 핸들을 CloseHandle에 대한 호출로 호출 해제해야합니다.



### MapViewOfFile function

파일 맵핑보기를 호출 프로세스의 주소 공간에 맵핑합니다.

보기에 제안 된 기본 주소를 지정하려면 MapViewOfFileEx 함수를 사용하십시오. 그러나이 방법은 권장되지 않습니다.

#### Syntax

```c++
LPVOID MapViewOfFile(
  HANDLE hFileMappingObject,
  DWORD  dwDesiredAccess,
  DWORD  dwFileOffsetHigh,
  DWORD  dwFileOffsetLow,
  SIZE_T dwNumberOfBytesToMap
);
```

#### Parameters

`hFileMappingObject`

파일 매핑 객체에 대한 핸들입니다. CreateFileMapping 및 OpenFileMapping 함수는 에서 리턴된 핸들 입니다.

`dwDesiredAccess`

페이지의 페이지 보호를 결정하는 파일 맵핑 오브젝트에 대한 액세스 유형. 이 매개 변수는 다음 값 중 하나이거나 적절한 경우 여러 값의 비트 단위 OR 조합 일 수 있습니다.

| Value                   | Meaning                                                      |
| :---------------------- | :----------------------------------------------------------- |
| FILE_MAP_ALL_ACCESS | 파일의 읽기/쓰기 view가 매핑됩니다. <br/>파일 맵핑 오브젝트는 PAGE_READWRITE 또는 PAGE_EXECUTE_READWRITE 보호로 작성되어야합니다.<br/>MapViewOfFile 함수와 함께 사용하면 FILE_MAP_ALL_ACCESS는 FILE_MAP_WRITE와 같습니다. |
| FILE_MAP_READ       | 파일의 읽기 전용 view 가 맵핑됩니다. <br/>파일 view 에 쓰려고하면 액세스 위반이 발생합니다.<br/>파일 맵핑 오브젝트는 PAGE_READONLY, PAGE_READWRITE, PAGE_EXECUTE_READ 또는 PAGE_EXECUTE_READWRITE 보호로 작성되어야합니다. |
| FILE_MAP_WRITE      | 파일의 읽기/쓰기 view 가 매핑됩니다. <br/>파일 맵핑 오브젝트는 PAGE_READWRITE 또는 PAGE_EXECUTE_READWRITE 보호로 작성되어야합니다. <br/>MapViewOfFile과 함께 사용하면(FILE_MAP_WRITE `|` FILE_MAP_READ) 및 FILE_MAP_ALL_ACCESS는 FILE_MAP_WRITE와 같습니다. |

비트 단위 OR을 사용하면 위의 값을 이 값과 결합 할 수 있습니다.

| Value                        | Meaning                                                      |
| :--------------------------- | :----------------------------------------------------------- |
| **FILE_MAP_COPY**            | 파일의 기록 중 복사 view 가 맵핑됩니다. <br/>파일 맵핑 오브젝트는 PAGE_READONLY, PAGE_READ_EXECUTE, PAGE_WRITECOPY, PAGE_EXECUTE_WRITECOPY, PAGE_READWRITE 또는 PAGE_EXECUTE_READWRITE 보호로 작성되어야합니다.<br/>프로세스가 COW (Copy-On-Write) 페이지에 쓰면 시스템은 원본 페이지를 프로세스 전용의 새 페이지로 복사합니다. <br/>새 페이지는 페이징 파일에 의해 지원됩니다. <br/>새 페이지의 보호는 기록 중 복사에서 읽기/쓰기로 변경됩니다.<br/>COW (Copy-On-Write) 액세스가 지정되면 호출 프로세스가 view 의 모든 페이지에 쓸 수 있으므로 모든 페이지를 개인용으로 만들 수 있으므로 시스템 및 프로세스 커미트 비용은 전체 view에 부과됩니다. <br/>새 페이지의 내용은 원본 파일에 다시 쓰여지지 않으며 뷰를 매핑 해제 할 때 손실됩니다. |
| **FILE_MAP_EXECUTE**         | 파일의 실행 가능 뷰가 매핑됩니다 (매핑 된 메모리는 코드로 실행될 수 있음). <br/>파일 맵핑 오브젝트는 PAGE_EXECUTE_READ, PAGE_EXECUTE_WRITECOPY 또는 PAGE_EXECUTE_READWRITE 보호로 작성되어야합니다.<br/>Windows Server 2003 및 Windows XP :이 값은 Windows XP SP2 및 Windows Server 2003 SP1부터 사용할 수 있습니다. |
| **FILE_MAP_LARGE_PAGES**     | Windows 10 버전 1703부터이 플래그는 큰 페이지 지원을 사용하여 뷰를 맵핑하도록 지정합니다. <br/>뷰의 크기는 GetLargePageMinimum 함수에 의해보고 된 큰 페이지 크기의 배수 여야하며 SEC_LARGE_PAGES 옵션을 사용하여 파일 맵핑 오브젝트를 작성해야합니다. <br/>LpBaseAddress에 널이 아닌 값을 제공하면 값은 GetLargePageMinimum의 배수 여야합니다.<br/>참고 : Windows 10, 버전 1703 이전의 OS 버전에서는 FILE_MAP_LARGE_PAGES 플래그가 적용되지 않습니다. <br/>이 릴리스에서는 섹션이 SEC_LARGE_PAGES 플래그 세트로 작성된 경우 큰 페이지를 사용하여 뷰가 자동으로 맵핑됩니다. |
| **FILE_MAP_TARGETS_INVALID** | 맵핑 된 파일의 모든 위치를 CFG (Control Flow Guard)에 대한 유효하지 않은 대상으로 설정합니다. <br/>이 플래그는 PAGE_TARGETS_INVALID와 유사합니다. <br/>실행 액세스 권한 FILE_MAP_EXECUTE와 함께 이 플래그를 사용하십시오. <br/>해당 페이지의 위치에 대한 간접 호출은 CFG 확인에 실패하고 프로세스가 종료됩니다. <br/>할당 된 실행 페이지의 기본 동작은 CFG의 유효한 호출 대상으로 표시됩니다. |

SEC_IMAGE 속성으로 작성된 파일 맵핑 오브젝트의 경우 dwDesiredAccess 매개 변수는 적용되지 않으며 FILE_MAP_READ와 같은 유효한 값으로 설정해야합니다.

파일 매핑 개체에 대한 액세스에 대한 자세한 내용은 파일 매핑 보안 및 액세스 권한을 참조하십시오.

`dwFileOffsetHigh`

view 가 시작되는 파일 오프셋의 상위 DWORD입니다.

`dwFileOffsetLow`

view 를 시작할 파일 오프셋의 하위 DWORD입니다. 상한 및 하한 오프셋의 조합은 파일 매핑 내에서 오프셋을 지정해야합니다. 또한 시스템의 메모리 할당 단위와 일치해야합니다. 즉, 오프셋은 할당 구조의 배수 여야합니다. 시스템의 메모리 할당 단위를 얻으려면 SYSTEM_INFO 구조의 멤버를 채우는 GetSystemInfo 함수를 사용하십시오.

`dwNumberOfBytesToMap`

view 에 맵핑 할 파일 맵핑의 바이트 수입니다. 모든 바이트는 CreateFileMapping으로 지정된 최대 크기 내에 있어야합니다. 이 매개 변수가 0이면, 맵핑은 지정된 오프셋에서 파일 맵핑의 끝까지 확장됩니다.



#### Return value

함수가 성공하면 리턴 값은 맵핑 된보기의 시작 주소입니다.

함수가 실패하면 리턴 값은 NULL입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

파일을 매핑하면 파일의 지정된 부분이 호출 프로세스의 주소 공간에 표시됩니다.

주소 공간보다 큰 파일의 경우 한 번에 파일 데이터의 일부만 매핑 할 수 있습니다. 첫 번째보기가 완료되면이를 맵핑 해제하고 새보기를 맵핑 할 수 있습니다.

뷰의 크기를 얻으려면 VirtualQuery 함수를 사용하십시오.

지정된 시간에 동일한 데이터를 포함하는 파일 (또는 파일 맵핑 오브젝트 및 해당 맵핑 된 파일)의 다중보기는 일관됩니다. 파일보기가 동일한 파일이 지원하는 파일 맵핑 오브젝트에서 파생 된 경우 발생합니다. 프로세스는 DuplicateHandle 함수를 사용하여 파일 맵핑 오브젝트 핸들을 다른 프로세스에 복제하거나 다른 프로세스가 OpenFileMapping 함수를 사용하여 이름으로 파일 맵핑 오브젝트를 열 수 있습니다.

한 가지 중요한 예외를 제외하면, 동일한 파일이 지원하는 모든 파일 맵핑 오브젝트에서 파생 된 파일보기는 특정 시간에 일관성이 있거나 동일합니다. 일관성은 프로세스 내의 뷰와 다른 프로세스에 의해 매핑 된 뷰에 대해 보장됩니다.

원격 파일과 관련된 예외입니다. MapViewOfFile은 원격 파일과 작동하지만 일관성을 유지하지는 않습니다. 예를 들어, 두 대의 컴퓨터가 모두 파일을 쓰기 가능으로 매핑하고 두 컴퓨터가 모두 같은 페이지를 변경하면 각 컴퓨터는 페이지에 대한 자체 쓰기 만 볼 수 있습니다. 디스크에서 데이터가 업데이트되면 병합되지 않습니다.

파일의 맵핑 된 뷰는 ReadFile 또는 WriteFile 함수에 의해 액세스되는 파일과 일관성이 보장되지 않습니다.

메모리 맵핑 파일에 포인터를 저장하지 마십시오. 매핑을 모든 주소에서 사용할 수 있도록 파일 매핑의 기초에서 오프셋을 저장합니다.

EXCEPTION_IN_PAGE_ERROR 예외를 방지하려면 구조적 예외 처리를 사용하여 페이지 파일 이외의 파일의 메모리 맵핑보기에서 쓰거나 읽는 코드를 보호하십시오. 자세한 내용은 파일보기에서 읽고 쓰기를 참조하십시오.

맵핑 된보기를 통해 파일을 수정하면 마지막 수정 시간 소인이 자동으로 업데이트되지 않을 수 있습니다. 필요한 경우 호출자는 SetFileTime을 사용하여 타임 스탬프를 설정해야합니다.

파일 맵핑 오브젝트가 페이징 파일에 의해 백업되는 경우 (hFile 매개 변수가 INVALID_HANDLE_VALUE로 설정된 상태에서 CreateFileMapping이 호출 됨) 페이징 파일은 전체 맵핑을 보유 할 수있을 정도로 커야합니다. 그렇지 않으면 MapViewOfFile이 실패합니다. 페이징 파일이 지원하는 파일 맵핑 오브젝트에서 페이지의 초기 내용은 0입니다.

페이징 파일이 지원하는 파일 맵핑 오브젝트가 작성되면 호출자는 MapViewOfFile이 동시에 페이지를 예약하고 커미트해야하는지 (SEC_COMMIT) 또는 단순히 페이지를 예약해야하는지 (SEC_RESERVE)를 지정할 수 있습니다. 파일을 매핑하면 전체 매핑 된 가상 주소 범위를 프로세스의 다른 할당에서 사용할 수 없습니다. 예약 된 범위의 페이지가 커밋 된 후 VirtualFree를 호출하여 해제하거나 해제 할 수 없습니다. 보기가 맵핑 해제되고 파일 맵핑 오브젝트가 닫히면 예약 및 커미트 된 페이지가 해제됩니다. 자세한 내용은 UnmapViewOfFile 및 CloseHandle 함수를 참조하십시오.

실행 권한이있는 파일을 가지려면 응용 프로그램에서 PAGE_EXECUTE_READWRITE 또는 PAGE_EXECUTE_READ를 사용하여 CreateFileMapping을 호출 한 다음 FILE_MAP_EXECUTE | FILE_MAP_WRITE 또는 FILE_MAP_EXECUTE | FILE_MAP_READ.





### 끝

