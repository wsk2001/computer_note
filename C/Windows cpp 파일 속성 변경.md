# Windows cpp 파일 속성 변경

출처:

<https://docs.microsoft.com/ko-kr/windows/desktop/api/fileapi/nf-fileapi-getfileattributesa>

<https://docs.microsoft.com/ko-kr/windows/desktop/api/fileapi/nf-fileapi-setfileattributesa>





## 1. GetFileAttributesA function

지정된 파일 또는 디렉토리의 파일 시스템 속성을 검색합니다.

더 많은 특성 정보를 얻으려면 [GetFileAttributesEx](https://msdn.microsoft.com/e5d84000-17c1-4517-97a7-6bd240d73814) 함수를 사용하십시오.

이 작업을 트랜잭션 연산으로 수행하려면 [GetFileAttributesTransacted](https://msdn.microsoft.com/dd1435da-93e5-440a-913a-9e40e39b4a01) 함수를 사용하십시오.



### Syntax

```c++
DWORD GetFileAttributesA(
  LPCSTR lpFileName
);
```



### Parameters

`lpFileName`

파일 또는 디렉토리의 이름.

이 함수의 ANSI 버전에서 이름은 MAX_PATH 문자로 제한됩니다. 이 제한을 32,767 와이드 문자로 확장하려면 함수의 유니 코드 버전 (GetFileAttributesW)을 호출하고 앞에 '\\?' 경로로. 자세한 내용은 [파일 이름, 경로 및 네임 스페이스를](https://msdn.microsoft.com/121cd5b2-e6fd-4eb4-99b4-b652d27b53e8) 참조하십시오.



`Tip`

```
이 함수 (GetFileAttributesW)의 유니 코드 버전 인 Windows 10, 버전 1607부터 '\\?\'를 선행하지 않고 MAX_PATH 문자 제한을 제거하도록 선택할 수 있습니다. 자세한 내용은 파일, 경로 및 네임 스페이스 이름 지정의 '최대 경로 제한'섹션을 참조하십시오.
```



### Return Value

함수가 성공하면 반환 값에는 지정된 파일 또는 디렉토리의 속성이 포함됩니다. 속성 값 및 설명에 대한 목록은 파일 속성 상수를 참조하십시오.

함수가 실패하면 반환 값은 INVALID_FILE_ATTRIBUTES입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



### Remarks

GetFileAttributes가 탑재 된 폴더 인 디렉터리에서 호출되면 탑재 된 폴더가 디렉터리와 연결하는 볼륨의 루트 디렉터리가 아닌 해당 디렉터리의 파일 시스템 특성을 반환합니다. 연결된 볼륨의 파일 특성을 가져 오려면 GetVolumeNameForVolumeMountPoint를 호출하여 연결된 볼륨의 이름을 가져옵니다. 그런 다음 GetFileAttributes에 대한 호출에서 결과 이름을 사용하십시오. 결과는 연관된 볼륨에있는 루트 디렉토리의 속성입니다.

네트워크 공유에 대해 GetFileAttributes를 호출하면 함수가 실패하고 GetLastError가 ERROR_BAD_NETPATH를 반환합니다. 해당 공유의 하위 폴더에 대한 경로를 지정해야합니다.

Windows 8 및 Windows Server 2012에서 이 기능은 다음 기술에서 지원됩니다.

| Technology                                | Supported |
| :---------------------------------------- | :-------- |
| Server Message Block (SMB) 3.0 protocol   | Yes       |
| SMB 3.0 Transparent Failover (TFO)        | Yes       |
| SMB 3.0 with Scale-out File Shares (SO)   | Yes       |
| Cluster Shared Volume File System (CsvFS) | Yes       |
| Resilient File System (ReFS)              | Yes       |

심볼릭 링크 동작 - 경로가 심볼릭 링크를 가리키면이 함수는 심볼릭 링크에 대한 속성을 반환합니다.



### Transacted Operations

파일이 트랜잭션에서 수정을 위해 열려 있으면 다른 스레드는 트랜잭션이 커밋 될 때까지 수정을 위해 파일을 열 수 없습니다. 따라서 트랜잭션 된 스레드가 파일을 먼저 열면 트랜잭션이 커밋되기 전에 파일을 수정하려고하는 모든 후속 스레드가 공유 위반을받습니다. 트랜잭션되지 않은 스레드가 트랜잭션 된 스레드가 수행하기 전에 파일을 수정하고 트랜잭션이 트랜잭션을 열려고 시도 할 때 파일이 열려 있으면 트랜잭션은 ERROR_TRANSACTIONAL_CONFLICT 오류를 수신합니다.



#### Examples

예를 보려면 [파일 속성 검색 및 변경](https://msdn.microsoft.com/f6eaea8a-0cc2-4fb6-bec5-7fb12b20c075)을 참조하십시오.

```c++
#include <windows.h>
#include <tchar.h>
#include <stdio.h>
#include <strsafe.h>

void _tmain(int argc, TCHAR* argv[])
{
   WIN32_FIND_DATA FileData;
   HANDLE          hSearch;
   DWORD           dwAttrs;
   TCHAR           szNewPath[MAX_PATH];   
 
   BOOL            fFinished = FALSE; 

   if(argc != 2)
   {
      _tprintf(TEXT("Usage: %s <dir>\n"), argv[0]);
      return;
   }
 
// Create a new directory. 
 
   if (!CreateDirectory(argv[1], NULL)) 
   { 
      printf("CreateDirectory failed (%d)\n", GetLastError()); 
      return;
   } 
 
// Start searching for text files in the current directory. 
 
   hSearch = FindFirstFile(TEXT("*.txt"), &FileData); 
   if (hSearch == INVALID_HANDLE_VALUE) 
   { 
      printf("No text files found.\n"); 
      return;
   } 
 
// Copy each .TXT file to the new directory 
// and change it to read only, if not already. 
 
   while (!fFinished) 
   { 
      StringCchPrintf(szNewPath, sizeof(szNewPath)/sizeof(szNewPath[0]), TEXT("%s\\%s"), argv[1], FileData.cFileName);

      if (CopyFile(FileData.cFileName, szNewPath, FALSE))
      { 
         dwAttrs = GetFileAttributes(FileData.cFileName); 
         if (dwAttrs==INVALID_FILE_ATTRIBUTES) return; 

         if (!(dwAttrs & FILE_ATTRIBUTE_READONLY)) 
         { 
            SetFileAttributes(szNewPath, 
                dwAttrs | FILE_ATTRIBUTE_READONLY); 
         } 
      } 
      else 
      { 
         printf("Could not copy file.\n"); 
         return;
      } 
 
      if (!FindNextFile(hSearch, &FileData)) 
      {
         if (GetLastError() == ERROR_NO_MORE_FILES) 
         { 
            _tprintf(TEXT("Copied *.txt to %s\n"), argv[1]); 
            fFinished = TRUE; 
         } 
         else 
         { 
            printf("Could not find next file.\n"); 
            return;
         } 
      }
   } 
 
// Close the search handle. 
 
   FindClose(hSearch);
}
```



### Requirements

| **Minimum supported client** | Windows XP [desktop apps \| UWP apps]          |
| ---------------------------- | ---------------------------------------------- |
| **Minimum supported server** | Windows Server 2003 [desktop apps \| UWP apps] |
| **Target Platform**          | Windows                                        |
| **Header**                   | fileapi.h (include Windows.h)                  |
| **Library**                  | Kernel32.lib                                   |
| **DLL**                      | Kernel32.dll                                   |



---



## 2. SetFileAttributesA function

파일 또는 디렉토리의 속성을 설정합니다.

이 작업을 트랜잭션 연산으로 수행하려면 [SetFileAttributesTransacted](https://msdn.microsoft.com/e25e77b2-a6ad-4ce4-8589-d7ff6c4074f6) 함수를 사용하십시오.



### Syntax

```c++
BOOL SetFileAttributesA(
  LPCSTR lpFileName,
  DWORD  dwFileAttributes
);
```



### Parameters

`lpFileName`

속성이 설정 될 파일의 이름.

나머지 부분은 위 GetFileAttributesA 의 lpFileName Parameters 와 동일함.



`dwFileAttributes`

파일에 대해 설정할 파일 특성입니다.

이 매개 변수는 비트 OR 연산자를 사용하여 결합 된 하나 이상의 값이 될 수 있습니다. 그러나 다른 모든 값은 FILE_ATTRIBUTE_NORMAL 보다 우선합니다.

이 기능은 모든 속성을 지원하지는 않습니다. 자세한 내용은 주의 섹션을 참조하십시오.

다음은 지원되는 속성 값 목록입니다.

| Value                                                    | Meaning                                                      |
| :------------------------------------------------------- | :----------------------------------------------------------- |
| **FILE_ATTRIBUTE_ARCHIVE**<br/>32 (0x20)                 | 아카이브 파일 또는 디렉토리 인 파일 또는 디렉토리. 응용 프로그램은 일반적으로 이 속성을 사용하여 백업 또는 제거를 위해 파일을 표시합니다. |
| **FILE_ATTRIBUTE_HIDDEN**<br/>2 (0x2)                    | 파일 또는 디렉토리가 숨겨져 있습니다. 일반 디렉토리 목록에는 포함되어 있지 않습니다. |
| **FILE_ATTRIBUTE_NORMAL**<br/>128 (0x80)                 | 다른 속성이 설정되지 않은 파일. 이 속성은 단독으로 사용될 때만 유효합니다. |
| **FILE_ATTRIBUTE_NOT_CONTENT_INDEXED**<br/>8192 (0x2000) | 파일이나 디렉터리는 콘텐츠 인덱싱 서비스에 의해 인덱싱되지 않습니다. |
| **FILE_ATTRIBUTE_OFFLINE**<br/>4096 (0x1000)             | 파일의 데이터를 즉시 사용할 수 없습니다. 이 특성은 파일 데이터가 물리적으로 오프라인 저장소로 이동되었음을 나타냅니다. 이 특성은 계층 적 저장소 관리 소프트웨어 인 원격 저장소에서 사용됩니다. 응용 프로그램은이 속성을 임의로 변경하면 안됩니다. |
| **FILE_ATTRIBUTE_READONLY**<br/>1 (0x1)                  | 읽기 전용 파일. 응용 프로그램은 파일을 읽을 수는 있지만 파일에 쓰거나 h 제할 수는 없습니다. 이 속성은 디렉토리에 적용되지 않습니다. 자세한 내용은 'Windows Server 2003, Windows XP 또는 Windows Vista에서 폴더의 읽기 전용 또는 시스템 특성을 보거나 변경할 수 없습니다. |
| **FILE_ATTRIBUTE_SYSTEM**<br/>4 (0x4)                    | 운영 체제가 일부만 사용하거나 독점적으로 사용하는 파일 또는 디렉토리. |
| **FILE_ATTRIBUTE_TEMPORARY**<br/>256 (0x100)             | 임시 저장에 사용중인 파일. 일반적으로 응용 프로그램은 핸들을 닫은 후에 응용 프로그램에서 임시 파일을 삭제하기 때문에 충분한 캐시 메모리가 있으면 대용량 저장소에 데이터를 다시 쓰지 않습니다. 이 시나리오에서 시스템은 데이터 작성을 완전히 피할 수 있습니다. 그렇지 않으면 핸들이 닫힌 후에 데이터가 기록됩니다. |



### Return Value

함수가 성공하면 반환 값은 0이 아닙니다.

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



### Remarks

다음 표에서는 SetFileAttributes를 사용하여 설정할 수없는 특성을 설정하는 방법을 설명합니다. 모든 파일 속성 값 및 설명에 대한 전체 목록은 [파일 속성 상수](https://msdn.microsoft.com/ed9a73d2-7fb6-4fb7-97f6-4dbf89e2f156)를 참조하십시오.

| Attribute                                  | How to Set                                                   |
| :----------------------------------------- | :----------------------------------------------------------- |
| **FILE_ATTRIBUTE_COMPRESSED**<br/>0x800    | 파일의 압축 상태를 설정하려면 [FSCTL_SET_COMPRESSION](https://msdn.microsoft.com/e6fb29ed-f4f4-4507-8312-d771ffb00256) 작업과 함께 [DeviceIoControl](https://msdn.microsoft.com/1d35c087-6672-4fc6-baa1-a886dd9d3878) 함수를 사용하십시오. |
| **FILE_ATTRIBUTE_DEVICE**<br/>0x40         | Reserved; do not use.                                        |
| **FILE_ATTRIBUTE_DIRECTORY**<br/>0x10      | 파일을 디렉토리로 변환 할 수 없습니다. 디렉토리를 만들려면 [CreateDirectory](https://msdn.microsoft.com/f8ca8b10-c8bd-4285-8a40-dbec4c24729c) 또는 [CreateDirectoryEx](https://msdn.microsoft.com/287996f8-e8ca-4b72-a5f6-016754785c5c) 함수를 사용합니다. |
| **FILE_ATTRIBUTE_ENCRYPTED**<br/>0x4000    | 암호화 된 파일을 만들려면 [CreateFile](https://msdn.microsoft.com/80a96083-4de9-4422-9705-b8ad2b6cbd1b) 함수를 FILE_ATTRIBUTE_ENCRYPTED 특성과 함께 사용하십시오. 기존 파일을 암호화 된 파일로 변환하려면 [EncryptFile](https://msdn.microsoft.com/7620e9fa-74d6-4b41-93db-4a562be63202) 기능을 사용하십시오. |
| **FILE_ATTRIBUTE_REPARSE_POINT**<br/>0x400 | 재분석 지점을 파일 또는 디렉터리와 연결하려면 [FSCTL_SET_REPARSE_POINT](https://msdn.microsoft.com/0bed6d69-269b-4921-8984-69c7829fb9ea) 작업과 함께 [DeviceIoControl](https://msdn.microsoft.com/1d35c087-6672-4fc6-baa1-a886dd9d3878) 함수를 사용하십시오.. |
| **FILE_ATTRIBUTE_SPARSE_FILE**<br/>0x200   | 파일의 스파스 속성을 설정하려면 [FSCTL_SET_SPARSE](https://msdn.microsoft.com/aa8f5880-f831-49b6-8359-fe07c78c032f) 작업과 함께 [DeviceIoControl](https://msdn.microsoft.com/1d35c087-6672-4fc6-baa1-a886dd9d3878) 함수를 사용하십시오. |



나머지는 GetFileAttributesA 와 동일함.



