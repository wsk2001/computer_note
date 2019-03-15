# Dokany Mirror 사용 함수 정리



## <span style="color:blue">WCP2CP</span>

함수 설명: WideChar 문자열을 Char 문자열로 변경 시키는 함수
제약 조건: 1024 Byte 이상 되는 문자열을 사용 할 경우 1024 Byte 이후 Data는 무시 한다.
함수 원형: char *WCP2CP(wchar_t *str)
리턴     값: char * 형으로 변경된 문자열

| param | in/out | 설명                                           |
| ----- | :----: | ---------------------------------------------- |
| str   |   in   | 변경할 원본 문자열 (원본이 변경 되지는 않는다) |







## <span style="color:blue">GetSystemTimeStr</span>

함수 설명: 시스템의 현재 시각을 Milli seconds 까지 문자열로 변환 하여 돌려주는 함수
제약 조건: 없음
함수 원형: static char *GetSystemTimeStr(void)
리턴     값: 변환된 시각의 문자열 ( "YYYY/MM/DD hh:mm:ss.mmm" )

입/출력 Parameter 없음



## <span style="color:blue">DbgPrint</span>

함수 설명: Debug Message 를 파일로 저장 하기 위한 함수
제약 조건: 로그파일이 생성(open) 되어 있어야 한다. 프로그램 시작시 /z 옵션에 의해 파일명을 지정 할 수 있다.
함수 원형: static void DbgPrint(char* format, ...)
리턴     값: 없음

printf() 함수 사용법과 동일 하므로 이하 설명은 생략 한다.





## <span style="color:blue">MirrorCheckFlag</span>

함수 설명: 이 매크로는 함수가 아니다. 특정한 변수의 Flag 값(특정위치의 bit 값)이 1인지 확인 하여 Log 를 남기는 매크로

제약 조건: 없음
함수 원형: MirrorCheckFlag(val, flag)
리턴     값: 없음 (flag & val 값이 참(true) 이면 Log 를 남긴다)

| param | in/out | 설명             |
| ----- | :----: | ---------------- |
| val   |   in   | 비교할 변수의 값 |
| flag  |   in   | 비교할 Flag(bit) |



## <span style="color:blue">GetFilePath</span>

함수 설명: 마운트 포인트의 파일명을 이용해 원본 경로의 파일명을 취득하는 함수.
제약 조건: directory mount 인 경우만 사용 할 수 있다.
함수 원형: static void GetFilePath( PWCHAR  filePath, ULONG   numberOfElements,  LPCWSTR FileName) 
리턴     값: 없음

| param            | in/out | 설명                         |
| ---------------- | :----: | ---------------------------- |
| filePath         |  out   | 취득된 원본 파일의 Full 경로 |
| numberOfElements |   in   | 목적지 버퍼(filePath)의 크기 |
| FileName         |   in   | 마운트 포인트의 파일명       |

RootDirectory (마운트 원본 경로)와 FileName 를 결합 하여 돌려 준다.

이 함수는 Dokany 에서 파일명을 사용하는 거의 모든 함수의 시작점 에서 호출 하여 사용하는 함수로 사용 빈도가 높다.



## <span style="color:blue">PrintUserName</span>

함수 설명:  현재 작업에 대한 Dokan 정보를 이용하여 계정명과 도메인명을 취득 후 Log 로 저장 한다.
제약 조건:  없음(전역변수 g_DebugMode 가 1로 Setting 되어 있어야 하지만 주석 처리 하였음)
함수 원형:  static void PrintUserName(PDOKAN_FILE_INFO DokanFileInfo)
리턴     값: 없음

| param         | in/out | 설명                        |
| ------------- | :----: | --------------------------- |
| DokanFileInfo |   in   | 현재 작업에 대한 Dokan 정보 |




## <span style="color:blue">AddSeSecurityNamePrivilege</span>

함수 설명: 보안 이름 권한을 추가 (이 함수에 의해 Access 권한이 결정 된다)
제약 조건: ?
함수 원형: static BOOL AddSeSecurityNamePrivilege()
리턴     값: 성공시 TRUE, 실패시 FALSE



### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">LookupPrivilegeValue()</span>

함수 설명: LookupPrivilegeValue 함수는 **지정된 시스템에서 사용 된 로컬 고유 식별자 (LUID)를 검색**하여 지정된 권한 이름을 로컬로 나타냅니다.

함수 원형: BOOL LookupPrivilegeValueA( LPCSTR lpSystemName, LPCSTR lpName, PLUID  lpLuid);

리턴     값:  함수가 성공하면 함수는 0이 아닌 값을 반환합니다. 함수가 실패하면 0을 반환합니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.

| param        | in/out | 설명                                                         |
| ------------ | :----: | ------------------------------------------------------------ |
| lpSystemName |   in   | 권한 이름이 검색되는 시스템의 이름을 지정하는 널 (null)로 끝나는 문자열에 대한 포인터. 널 (null). 문자열이 지정되면 함수는 국지 시스템에서 특권 이름을 찾으려고 시도합니다. |
| lpName       |   in   | Winnt.h 헤더 파일에 정의 된대로 권한의 이름을 지정하는 null로 끝나는 문자열에 대한 포인터입니다. 예를 들어,이 매개 변수는 상수 SE_SECURITY_NAME 또는 해당 문자열 'SeSecurityPrivilege'를 지정할 수 있습니다. |
| lpLuid       |  out   | lpSystemName 매개 변수로 지정된 시스템에서 특권이 알려진 LUID를 수신하는 변수에 대한 포인터. |



#### <span style="color:magenta">OpenProcessToken()</span>

OpenProcessToken 함수는 **프로세스와 관련된 액세스 토큰을 엽니다**.

```c++
BOOL OpenProcessToken(
  HANDLE  ProcessHandle,
  DWORD   DesiredAccess,
  PHANDLE TokenHandle
);
```

**ProcessHandle**:  액세스 토큰이 열린 프로세스의 핸들. 프로세스에 PROCESS_QUERY_INFORMATION 액세스 권한이 있어야합니다.

**DesiredAccess**: 액세스 토큰에 대한 요청 된 액세스 유형을 지정하는 [액세스 마스크](https://msdn.microsoft.com/0baaa937-f635-4500-8dcd-9dbbd6f4cd02)를 지정합니다. 이러한 요청 된 액세스 유형은 토큰의 [DACL](https://msdn.microsoft.com/d007cbb9-b547-4dc7-bc22-b526f650f7c2) (임의 액세스 제어 목록)과 비교되어 부여되거나 거부되는 액세스를 결정합니다.

액세스 토큰에 대한 액세스 권한 목록은 [액세스 토큰 개체의 액세스 권한](https://msdn.microsoft.com/5f710fd8-33de-47c0-a8b2-baf3008c4ed7)을 참조하십시오.

**TokenHandle**: 함수가 반환 될 때 새로 열어 진 액세스 토큰을 식별하는 핸들에 대한 포인터입니다.

**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.

<span style="color:DarkBlue">**액세스 마스크**</span>

액세스 제어 항목 (ACE)에서 허용 또는 거부되는 권한을 지정하는 32 비트 값입니다. 액세스 마스크는 오브젝트가 열릴 때 액세스 권한을 요청하는 데에도 사용됩니다.

 (ACE) 액세스 제어 목록 (ACL)의 항목. ACE에는 권한이 허용되거나 거부되거나 감사되는 트러스티를 식별하는 액세스 권한 집합과 보안 식별자 (SID)가 포함되어 있습니다.

 (DACL) 개체 소유자가 제어하며 특정 개체 나 사용자가 개체에 대해 가질 수있는 액세스 권한을 지정하는 액세스 제어 목록입니다.

**security descriptor**

보안 개체에 대한 보안 정보가 들어있는 구조 및 관련 데이터입니다. 보안 설명자는 개체의 소유자와 기본 그룹을 식별합니다. 또한 개체에 대한 액세스를 제어하는 DACL과 개체에 액세스하려는 시도의 로깅을 제어하는 SACL을 포함 할 수 있습니다.

<span style="color:DarkBlue">**DACL**</span>

**discretionary access control list** : (DACL) 개체 소유자가 제어하며 특정 개체 나 사용자가 개체에 대해 가질 수있는 액세스 권한을 지정하는 액세스 제어 목록입니다.

See also [*access control list*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/a-gly) and [*system access control list*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/s-gly).

(ACL) 객체에 적용되는 보안 보호 목록입니다. (개체는 파일, 프로세스, 이벤트 또는 보안 설명자가있는 다른 개체가 될 수 있습니다.) 액세스 제어 목록 (ACL)의 항목은 ACE (액세스 제어 항목)입니다. 액세스 제어 목록에는 임의 및 시스템의 두 가지 유형이 있습니다.

See also *access control entry*, [*discretionary access control list*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/d-gly), [*security descriptor*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/s-gly), and [*system access control list*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/s-gly).

**system access control list**: (SACL) 보안 개체 액세스 시도에 대한 감사 메시지 생성을 제어하는 ACL입니다. 개체의 SACL을 가져 오거나 설정하는 기능은 일반적으로 시스템 관리자 만 수행 할 수있는 권한에 의해 제어됩니다.



#### <span style="color:magenta">AdjustTokenPrivileges()</span>

AdjustTokenPrivileges 함수는 **지정된 액세스 토큰의 권한을 사용하거나 사용하지 않도록 설정**합니다. 액세스 토큰에서 권한을 사용하거나 사용하지 않으려면 TOKEN_ADJUST_PRIVILEGES 액세스가 필요합니다.

```c++
BOOL AdjustTokenPrivileges(
  HANDLE            TokenHandle,
  BOOL              DisableAllPrivileges,
  PTOKEN_PRIVILEGES NewState,
  DWORD             BufferLength,
  PTOKEN_PRIVILEGES PreviousState,
  PDWORD            ReturnLength
);
```

**TokenHandle**: 수정할 권한이 포함 된 액세스 토큰에 대한 핸들입니다. 핸들은 토큰에 TOKEN_ADJUST_PRIVILEGES 액세스 권한을 가져야합니다. PreviousState 매개 변수가 NULL이 아닌 경우 핸들은 TOKEN_QUERY 액세스 권한도 가져야합니다.

**DisableAllPrivileges**: <span style="color:navy">함수가 모든 토큰의 권한을 사용 불가능하게할지 여부를 지정합니다. 이 값이 TRUE이면이 함수는 모든 권한을 비활성화하고 NewState 매개 변수를 무시합니다. FALSE이면 함수는 NewState 매개 변수가 가리키는 정보를 기반으로 특권을 수정합니다.</span>

**NewState**: 특권의 배열과 그 속성을 지정하는 TOKEN_PRIVILEGES 구조체의 포인터. DisableAllPrivileges 매개 변수가 FALSE 인 경우 AdjustTokenPrivileges 함수는 토큰에 대한 이러한 권한을 활성화, 비활성화 또는 제거합니다. 다음 표에서는 권한 특성을 기반으로 AdjustTokenPrivileges 함수가 수행하는 작업을 설명합니다.

| Value                    | Meaning                                                      |
| ------------------------ | ------------------------------------------------------------ |
| **SE_PRIVILEGE_ENABLED** | 이 기능은 권한을 활성화합니다.                               |
| **SE_PRIVILEGE_REMOVED** | 권한은 토큰의 권한 목록에서 제거됩니다. 목록의 다른 권한은 연속적으로 유지되도록 재정렬됩니다. <br/><br/>SE_PRIVILEGE_REMOVED가 SE_PRIVILEGE_ENABLED를 대체합니다.<br/><br/>권한이 토큰에서 제거되었으므로 특권이 다시 존재하지 않았던 것처럼 특권을 다시 활성화하면 ERROR_NOT_ALL_ASSIGNED 경고가 나타납니다.<br/><br/>토큰에없는 특권을 제거하려고하면 ERROR_NOT_ALL_ASSIGNED가 리턴됩니다.<br/><br/>제거 된 권한에 대한 권한 검사는 STATUS_PRIVILEGE_NOT_HELD가됩니다. 실패한 권한 검사 감사는 정상적으로 발생합니다.<br/><br/>권한 제거는 되돌릴 수 없기 때문에 제거 된 권한의 이름은 AdjustTokenPrivileges를 호출 한 후 PreviousState 매개 변수에 포함되지 않습니다.<br/><br/>**Windows XP with SP1:**  이 함수는 권한을 제거 할 수 없습니다. 이 값은 지원되지 않습니다. |
| **None**                 | 이 기능은 특권을 사용 불가능하게합니다.                      |

DisableAllPrivileges가 TRUE이면이 함수는이 매개 변수를 무시합니다.

**BufferLength**: PreviousState 매개 변수가 가리키는 버퍼의 크기 (바이트)를 지정합니다. 이 매개 변수는 PreviousState 매개 변수가 NULL이면 0이 될 수 있습니다.

**PreviousState**: 함수가 수정하는 모든 이전 권한 상태를 포함하는 TOKEN_PRIVILEGES 구조로 함수가 채워지는 버퍼에 대한 포인터. 즉,이 함수로 특권이 수정되면 특권과 이전 상태는 PreviousState가 참조하는 TOKEN_PRIVILEGES 구조에 포함됩니다. TOKEN_PRIVILEGES의 PrivilegeCount 구성원이 0이면이 기능으로 어떤 특권도 변경되지 않습니다. 이 매개 변수는 NULL 일 수 있습니다.

수정 된 권한의 전체 목록을 수신하기에는 너무 작은 버퍼를 지정하면 함수가 실패하고 권한을 조정하지 않습니다. 이 경우이 함수는 ReturnLength 매개 변수가 가리키는 변수를 수정 된 권한의 전체 목록을 보유하는 데 필요한 바이트 수로 설정합니다.

**ReturnLength**: PreviousState 매개 변수가 가리키는 버퍼의 필요한 크기 (바이트)를받는 변수에 대한 포인터입니다. PreviousState가 NULL이면이 매개 변수는 NULL 일 수 있습니다.



**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 지정된 권한을 모두 조정했는지 여부를 확인하려면 GetLastError를 호출하여 함수가 성공하면 다음 값 중 하나를 반환합니다.

| Return code                | Description                                                  |
| -------------------------- | ------------------------------------------------------------ |
| **ERROR_SUCCESS**          | 이 함수는 지정된 모든 권한을 조정했습니다.                   |
| **ERROR_NOT_ALL_ASSIGNED** | 토큰에 NewState 매개 변수에 지정된 하나 이상의 특권이 없습니다. 권한을 조정하지 않아도이 오류 값으로 성공할 수 있습니다. PreviousState 매개 변수는 조정 된 권한을 나타냅니다. |

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

AdjustTokenPrivileges 함수는 액세스 토큰에 새 권한을 추가 할 수 없습니다. 토큰의 기존 권한 만 활성화 또는 비활성화 할 수 있습니다. 토큰의 권한을 확인하려면 GetTokenInformation 함수를 호출하십시오.

NewState 매개 변수는 함수가 실패하지 않고 토큰에없는 권한을 지정할 수 있습니다. 이 경우 함수는 토큰에있는 권한을 조정하고 다른 권한을 무시하여 함수가 성공하도록합니다. GetLastError 함수를 호출하여 함수가 지정된 권한을 모두 조정했는지 여부를 확인하십시오. PreviousState 매개 변수는 조정 된 권한을 나타냅니다.

PreviousState 매개 변수는 조정 된 권한의 원래 상태가 포함 된 TOKEN_PRIVILEGES 구조를 검색합니다. 원래 상태를 복원하려면 이후의 AdjustTokenPrivileges 함수 호출에서 NewState 매개 변수로 PreviousState 포인터를 전달하십시오.



**Examples**

이 함수를 사용하는 예제는 [권한 활성화 및 비활성화](https://msdn.microsoft.com/aa2d3fe7-01ee-4243-b72c-3e8b90068e22)를 참조하십시오.

```c++
#include <windows.h>
#include <stdio.h>
#pragma comment(lib, "cmcfg32.lib")

BOOL SetPrivilege(
    HANDLE hToken,          // access token handle
    LPCTSTR lpszPrivilege,  // name of privilege to enable/disable
    BOOL bEnablePrivilege   // to enable or disable privilege
    ) 
{
    TOKEN_PRIVILEGES tp;
    LUID luid;

    if ( !LookupPrivilegeValue( 
            NULL,            // lookup privilege on local system
            lpszPrivilege,   // privilege to lookup 
            &luid ) )        // receives LUID of privilege
    {
        printf("LookupPrivilegeValue error: %u\n", GetLastError() ); 
        return FALSE; 
    }

    tp.PrivilegeCount = 1;
    tp.Privileges[0].Luid = luid;
    if (bEnablePrivilege)
        tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    else
        tp.Privileges[0].Attributes = 0;

    // Enable the privilege or disable all privileges.

    if ( !AdjustTokenPrivileges(
           hToken, 
           FALSE, 
           &tp, 
           sizeof(TOKEN_PRIVILEGES), 
           (PTOKEN_PRIVILEGES) NULL, 
           (PDWORD) NULL) )
    { 
          printf("AdjustTokenPrivileges error: %u\n", GetLastError() ); 
          return FALSE; 
    } 

    if (GetLastError() == ERROR_NOT_ALL_ASSIGNED)

    {
          printf("The token does not have the specified privilege. \n");
          return FALSE;
    } 

    return TRUE;
}
```





#### <span style="color:magenta">CloseHandle()</span>

이 객체와 관련된 핸들을 닫습니다.



### <span style="color:green">사용된 주요 자료형</span>

#### <span style="color:magenta">LUID</span>

LUID는 생성 된 시스템에서만 고유 한 64 비트 값입니다. 로컬 고유 식별자 (LUID)의 고유성은 시스템이 다시 시작될 때까지만 보장됩니다.

응용 프로그램은 함수 및 구조를 사용하여 LUID 값을 조작해야합니다.

```c++
typedef struct _LUID {
  DWORD LowPart;
  LONG  HighPart;
} LUID, *PLUID;
```

LowPart :  하위 비트.

HighPart: 상위 비트.



#### <span style="color:magenta">LUID_AND_ATTRIBUTES</span>

LUID_AND_ATTRIBUTES 구조체는 로컬 고유 식별자 (LUID) 및 해당 특성을 나타냅니다.

```c++
typedef struct _LUID_AND_ATTRIBUTES {
  LUID  Luid;
  DWORD Attributes;
} LUID_AND_ATTRIBUTES, *PLUID_AND_ATTRIBUTES;
```

Luid : LUID 값을 지정합니다.

Attributes: LUID의 속성을 지정합니다. 이 값에는 최대 32 개의 1 비트 플래그가 포함됩니다. 그 의미는 LUID의 정의와 사용에 따라 다릅니다.

##### Remarks

LUID_AND_ATTRIBUTES 구조는 속성이 자주 변경되는 LUID를 나타낼 수 있습니다 (예 : LUID가 PRIVILEGE_SET 구조의 권한을 나타내는 데 사용되는 경우). 특권은 LUID로 표시되며 현재 사용 가능한지 또는 사용 불가능한지를 나타내는 속성을가집니다.



#### <span style="color:magenta">TOKEN_PRIVILEGES</span>

TOKEN_PRIVILEGES 구조체에는 액세스 토큰에 대한 권한 집합에 대한 정보가 들어 있습니다.

```c++
typedef struct _TOKEN_PRIVILEGES {
  DWORD               PrivilegeCount;
  LUID_AND_ATTRIBUTES Privileges[ANYSIZE_ARRAY];
} TOKEN_PRIVILEGES, *PTOKEN_PRIVILEGES;
```

PrivilegeCount: Privileges 배열의 항목 수로 설정해야합니다.

Privileges: LUID_AND_ATTRIBUTES 구조체의 배열을 지정합니다. 각 구조에는 특권의 LUID 속성이 들어 있습니다. LUID와 관련된 권한의 이름을 얻으려면 LookupPrivilegeName 함수를 호출하여 LUID의 주소를 lpLuid 매개 변수의 값으로 전달하십시오.

중요한 상수 ANYSIZE_ARRAY는 공용 헤더 Winnt.h에서 1로 정의됩니다. 둘 이상의 요소가있는이 배열을 만들려면 추가 요소를 고려하여 구조에 충분한 메모리를 할당해야합니다.

권한의 속성은 다음 값의 조합 일 수 있습니다.

| Value                               | Meaning                                                      |
| ----------------------------------- | ------------------------------------------------------------ |
| **SE_PRIVILEGE_ENABLED**            | 권한이 활성화됩니다.                                         |
| **SE_PRIVILEGE_ENABLED_BY_DEFAULT** | 권한은 기본적으로 사용됩니다.                                |
| **SE_PRIVILEGE_REMOVED**            | 권한을 제거하는 데 사용됩니다. 자세한 내용은 AdjustTokenPrivileges를 참조하십시오. |
| **SE_PRIVILEGE_USED_FOR_ACCESS**    | 이 권한은 객체 나 서비스에 액세스하는 데 사용되었습니다. 이 플래그는 불필요한 특권을 포함 할 수있는 클라이언트 응용 프로그램에 의해 전달 된 세트에서 관련 특권을 식별하는 데 사용됩니다. |



## <span style="color:blue">MirrorCreateFile </span>

함수 설명:  CallBack 으로 등록 하여 사용하는 파일 생성시 호출 되는 함수
제약 조건: ?
함수 원형: MirrorCreateFile( LPCWSTR                                            FileName, 
                                                  PDOKAN_IO_SECURITY_CONTEXT  SecurityContext,
                                                  ACCESS_MASK                                   DesiredAccess, 
                                                  ULONG                                                FileAttributes,
                                                  ULONG                                                ShareAccess, 
                                                  ULONG                                                CreateDisposition,
                                                  ULONG                                                CreateOptions, 
                                                  PDOKAN_FILE_INFO                          DokanFileInfo ) 
리턴     값:  생성 성공시 0, 그외에 값은 오류코드

| param             | in/out | 설명                         |
| ----------------- | :----: | ---------------------------- |
| FileName          |        | 파일명 |
| SecurityContext   |        |   |
| DesiredAccess     |        |        |
| FileAttributes    |        |                              |
| ShareAccess       |        |                              |
| CreateDisposition |        |                              |
| CreateOptions     |        |                              |
| DokanFileInfo     |        |                              |



### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">GetFileAttributes()</span>

지정된 파일 또는 디렉토리의 파일 시스템 속성을 검색합니다.

더 많은 특성 정보를 얻으려면 GetFileAttributesEx 함수를 사용하십시오.

이 작업을 트랜잭션 연산으로 수행하려면 GetFileAttributesTransacted 함수를 사용하십시오.

```c++
DWORD GetFileAttributesA(
  LPCSTR lpFileName
);
```

**lpFileName**: 파일 또는 디렉토리의 이름.

이 함수의 ANSI 버전에서 이름은 MAX_PATH 문자로 제한됩니다. 이 제한을 32,767 와이드 문자로 확장하려면 함수의 유니 코드 버전 (GetFileAttributesW)을 호출하고 앞에 '\\?' 경로로. 자세한 내용은 파일 이름, 경로 및 네임 스페이스를 참조하십시오.

```
팁 이 함수의 유니 코드 버전 (GetFileAttributesW)에 대해 Windows 10, 버전 1607부터 "\\?\"를 앞에 붙이지 않고 MAX_PATH 문자 제한을 제거하도록 선택할 수 있습니다. 자세한 내용은 파일, 경로 및 네임 스페이스 이름 지정의 '최대 경로 제한'절을 참조하십시오.
```

**Return Value**

함수가 성공하면 반환 값에는 지정된 파일 또는 디렉토리의 속성이 포함됩니다. 속성 값 및 설명에 대한 목록은 [파일 속성 상수](https://msdn.microsoft.com/ed9a73d2-7fb6-4fb7-97f6-4dbf89e2f156)를 참조하십시오.

함수가 실패하면 반환 값은 INVALID_FILE_ATTRIBUTES입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

GetFileAttributes가 마운트 된 폴더 인 디렉터리에서 호출되면 마운트 된 폴더가 디렉터리와 연결하는 볼륨의 루트 디렉터리가 아닌 해당 디렉터리의 파일 시스템 특성을 반환합니다. 연결된 볼륨의 파일 특성을 가져 오려면 GetVolumeNameForVolumeMountPoint를 호출하여 연결된 볼륨의 이름을 가져옵니다. 그런 다음 GetFileAttributes에 대한 호출에서 결과 이름을 사용하십시오. 결과는 연관된 볼륨에있는 루트 디렉토리의 속성입니다.

네트워크 공유에 대해 GetFileAttributes를 호출하면 함수가 실패하고 GetLastError가 ERROR_BAD_NETPATH를 반환합니다. 해당 공유의 하위 폴더에 대한 경로를 지정해야합니다.

심볼릭 링크 동작 - Path 가 심볼릭 링크를 가리키면이 함수는 심볼릭 링크에 대한 속성을 반환합니다.



#### <span style="color:magenta">SetFileAttributes()</span>

파일 또는 디렉토리의 속성을 설정합니다.

이 작업을 트랜잭션 연산으로 수행하려면 SetFileAttributesTransacted 함수를 사용하십시오.

```c++
BOOL SetFileAttributesA(
  LPCSTR lpFileName,
  DWORD  dwFileAttributes
);
```

**lpFileName**: 속성이 설정 될 파일의 이름.

이 함수의 ANSI 버전에서 이름은 MAX_PATH 문자로 제한됩니다. 이 제한을 32,767 와이드 문자로 확장하려면 유니 코드 버전의 함수 (SetFileAttributesW)를 호출하고 앞에 "'\?" 경로로. 자세한 내용은 파일 이름, 경로 및 네임 스페이스를 참조하십시오.

```
팁이 함수 (SetFileAttributesW)의 유니 코드 버전 인 Windows 10, 버전 1607부터 "\\?\"를 앞에 붙이지 않고 MAX_PATH 문자 제한을 제거하도록 선택할 수 있습니다. 자세한 내용은 파일, 경로 및 네임 스페이스 이름 지정의 '최대 경로 제한'절을 참조하십시오.
```

**dwFileAttributes**: 파일에 대해 설정할 파일 특성입니다.

이 매개 변수는 비트 OR 연산자를 사용하여 결합 된 하나 이상의 값이 될 수 있습니다. 그러나 다른 모든 값은 FILE_ATTRIBUTE_NORMAL보다 우선합니다.

이 기능은 모든 속성을 지원하지는 않습니다. 자세한 내용은주의 섹션을 참조하십시오.

다음은 지원되는 속성 값 목록입니다. (파일 속성 상수)

| Value                                                | Meaning                                                      |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| **FILE_ATTRIBUTE_ARCHIVE** 32 (0x20)                 | 아카이브 파일 또는 디렉토리 인 파일 또는 디렉토리. 응용 프로그램은 일반적으로 이 속성을 사용하여 백업 또는 제거를 위해 파일을 표시합니다. |
| **FILE_ATTRIBUTE_HIDDEN** 2 (0x2)                    | 파일 또는 디렉토리가 숨겨져 있습니다. 일반 디렉토리 목록에는 포함되어 있지 않습니다. |
| **FILE_ATTRIBUTE_NORMAL** 128 (0x80)                 | 다른 속성이 설정되지 않은 파일. 이 속성은 단독으로 사용될 때만 유효합니다. 우선 순위가 가장 낮습니다. |
| **FILE_ATTRIBUTE_NOT_CONTENT_INDEXED** 8192 (0x2000) | 파일이나 디렉터리는 콘텐츠 인덱싱 서비스에 의해 인덱싱되지 않습니다. |
| **FILE_ATTRIBUTE_OFFLINE** 4096 (0x1000)             | 파일의 데이터를 즉시 사용할 수 없습니다. 이 특성은 파일 데이터가 물리적으로 오프라인 저장소로 이동되었음을 나타냅니다. 이 특성은 계층 적 저장소 관리 소프트웨어 인 원격 저장소에서 사용됩니다. 응용 프로그램은이 속성을 임의로 변경하면 안됩니다. |
| **FILE_ATTRIBUTE_READONLY** 1 (0x1)                  | 읽기 전용 파일. 응용 프로그램은 파일을 읽을 수는 있지만 파일에 쓰거나 삭제할 수는 없습니다. 이 속성은 디렉토리에 적용되지 않습니다. 자세한 내용은 'Windows Server 2003, Windows XP 또는 Windows Vista에서 폴더의 읽기 전용 또는 시스템 특성을 보거나 변경할 수 없습니다. |
| **FILE_ATTRIBUTE_SYSTEM** 4 (0x4)                    | 운영 체제가 일부만 사용하거나 독점적으로 사용하는 파일 또는 디렉토리. |
| **FILE_ATTRIBUTE_TEMPORARY** 256 (0x100)             | 임시 저장에 사용중인 파일. 일반적으로 응용 프로그램은 핸들을 닫은 후에 응용 프로그램에서 임시 파일을 삭제하기 때문에 충분한 캐시 메모리가 있으면 대용량 저장소에 데이터를 다시 쓰지 않습니다. 이 시나리오에서 시스템은 데이터 작성을 완전히 피할 수 있습니다. 그렇지 않으면 핸들이 닫힌 후에 데이터가 기록됩니다. |

**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다.  함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

다음 표에서는 SetFileAttributes를 사용하여 설정 할 수 없는 특성을 설정하는 방법을 설명합니다. 모든 파일 속성 값 및 설명에 대한 전체 목록은 [파일 속성 상수](https://msdn.microsoft.com/ed9a73d2-7fb6-4fb7-97f6-4dbf89e2f156)를 참조하십시오.

| Attribute                              | How to Set                                                   |
| -------------------------------------- | ------------------------------------------------------------ |
| **FILE_ATTRIBUTE_COMPRESSED** 0x800    | 파일의 압축 상태를 설정하려면 FSCTL_SET_COMPRESSION 작업과 함께 DeviceIoControl 함수를 사용하십시오. |
| **FILE_ATTRIBUTE_DEVICE** 0x40         | Reserved; do not use.                                        |
| **FILE_ATTRIBUTE_DIRECTORY **0x10      | 파일을 디렉토리로 변환 할 수 없습니다. 디렉토리를 만들려면 [CreateDirectory](https://msdn.microsoft.com/f8ca8b10-c8bd-4285-8a40-dbec4c24729c) 또는 [CreateDirectoryEx](https://msdn.microsoft.com/287996f8-e8ca-4b72-a5f6-016754785c5c) 함수를 사용합니다. |
| **FILE_ATTRIBUTE_ENCRYPTED **0x4000    | 암호화 된 파일을 만들려면 [CreateFile](https://msdn.microsoft.com/80a96083-4de9-4422-9705-b8ad2b6cbd1b) 함수를 FILE_ATTRIBUTE_ENCRYPTED 특성과 함께 사용하십시오. 기존 파일을 암호화 된 파일로 변환하려면 [EncryptFile](https://msdn.microsoft.com/7620e9fa-74d6-4b41-93db-4a562be63202) 기능을 사용하십시오. |
| **FILE_ATTRIBUTE_REPARSE_POINT** 0x400 | 재분석 지점을 파일 또는 디렉터리와 연결하려면 [FSCTL_SET_REPARSE_POINT](https://msdn.microsoft.com/0bed6d69-269b-4921-8984-69c7829fb9ea) 작업과 함께 [DeviceIoControl](https://msdn.microsoft.com/1d35c087-6672-4fc6-baa1-a886dd9d3878) 함수를 사용하십시오. |
| **FILE_ATTRIBUTE_SPARSE_FILE** 0x200   | 파일의 스파 스 속성을 설정하려면 [FSCTL_SET_SPARSE](https://msdn.microsoft.com/aa8f5880-f831-49b6-8359-fe07c78c032f) 작업과 함께 [DeviceIoControl](https://msdn.microsoft.com/1d35c087-6672-4fc6-baa1-a886dd9d3878) 함수를 사용하십시오. |





#### <span style="color:magenta">ImpersonateLoggedOnUser()</span>

이 메서드를 호출하면 호출 한 스레드가 로그온 한 사용자의 보안 컨텍스트를 가장 할 수 있습니다.

```c
bool ImpersonateLoggedOnUser( ) const throw(...);
```

**Return Value**

성공하면 true를 반환하고 실패하면 false를 반환합니다.



**Remarks**

**Security Note**

<span style="color:Darkblue">어떤 이유로 든 가장 (impersonation) 함수에 대한 호출이 실패하면 클라이언트는 가장 하지 않으며 클라이언트 요청은 호출이 이루어진 프로세스의 보안 컨텍스트에서 수행됩니다. 프로세스가 높은 권한을 가진 계정 또는 관리 그룹의 구성원으로 실행중인 경우 사용자는 그렇지 않으면 허용되지 않는 작업을 수행 할 수 있습니다. 따라서 이 함수의 반환 값은 항상 확인되어야합니다.</span>



#### <span style="color:magenta">CreateDirectory()</span>

새 디렉토리를 만듭니다. 기본 파일 시스템이 파일 및 디렉터리의 보안을 지원하는 경우 이 함수는 지정된 보안 설명자를 새 디렉터리에 적용합니다.

템플릿 디렉토리를 지정하려면 [CreateDirectoryEx](https://msdn.microsoft.com/287996f8-e8ca-4b72-a5f6-016754785c5c) 함수를 사용하십시오.

이 작업을 트랜잭션 연산으로 수행하려면 [CreateDirectoryTransacted](https://msdn.microsoft.com/75663b30-5bd9-4de7-8e4f-dc58016c2c40) 함수를 사용하십시오.

```c++
BOOL CreateDirectoryA(
  LPCSTR                lpPathName,
  LPSECURITY_ATTRIBUTES lpSecurityAttributes
);
```

**lpPathName**: 작성할 디렉토리의 경로.

이 함수의 ANSI 버전에는 248 자의 경로에 대한 기본 문자열 크기 제한이 있습니다 (MAX_PATH - 8.3 파일 이름을위한 충분한 공간). 이 제한을 32,767 와이드 문자로 확장하려면 유니 코드 버전의 함수를 호출하고 앞에 "\?" 를 추가하십시오. 경로로. 자세한 내용은 파일 이름 지정을 참조하십시오.

```
팁: 이 함수 (CreateDirectoryW)의 유니 코드 버전 인 Windows 10, 버전 1607부터  "\\?\"를 앞에 붙이지 않고 248 문자 제한을 제거하도록 선택할 수 있습니다. 경로 세그먼트 당 255 자 제한이 여전히 적용됩니다. 자세한 내용은 파일, 경로 및 네임 스페이스 이름 지정의 '최대 경로 길이 제한'절을 참조하십시오.
```

**lpSecurityAttributes**: 

[SECURITY_ATTRIBUTES](https://msdn.microsoft.com/56b5b350-f4b7-47af-b5f8-6a35f32c1009) 구조체에 대한 포인터. 구조체 lpSecurityDescriptor 의 멤버는 새 디렉터리에 대한 보안 설명자를 지정합니다. lpSecurityAttributes가 NULL이면 디렉터리는 기본 보안 설명자를 가져옵니다. 디렉터리의 기본 보안 설명자에있는 ACL은 부모 디렉터리에서 상속됩니다.

**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오. 가능한 오류는 다음과 같습니다.

| Return code              | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **ERROR_ALREADY_EXISTS** | 지정된 디렉토리가 이미 존재합니다.                           |
| **ERROR_PATH_NOT_FOUND** | 하나 이상의 중간 디렉토리가 존재하지 않습니다. 이 함수는 경로에 마지막 디렉토리 만 생성합니다. |



**Remarks**

NTFS 파일 시스템과 같은 일부 파일 시스템은 개별 파일 및 디렉토리에 대한 압축 또는 암호화를 지원합니다. 이러한 파일 시스템 용으로 포맷 된 볼륨에서 새 디렉토리는 상위 디렉토리의 압축 및 암호화 속성을 상속합니다.

응용 프로그램은 FILE_FLAG_BACKUP_SEMANTICS 플래그가 설정된 CreateFile을 호출하여 디렉터리에 대한 핸들을 얻을 수 있습니다. 코드 예제는 CreateFile을 참조하십시오.

이 개체의 보안 설명자를 쿼리하는 상속 함수를 지원하려면 상속이 유효하다는 사실을 경험적으로 결정하고보고 할 수 있습니다. 자세한 내용은 상속 가능한 ACE의 자동 전파를 참조하십시오.



#### <span style="color:magenta">CreateFile()</span>

파일 또는 I/O 장치를 만들거나 엽니다. 가장 일반적으로 사용되는 I/O 장치는 파일,  파일 스트림,  디렉터리,  실제 디스크,  볼륨,  콘솔 버퍼, 테이프 드라이브, 통신 리소스, 메일 슬롯 및 파이프입니다. 이 함수는 파일이나 장치, 지정된 플래그 및 속성에 따라 다양한 유형의 I/O에 대해 파일이나 장치에 액세스하는 데 사용할 수있는 핸들을 반환합니다.

이 작업을 트랜잭션 된 작업으로 수행하면 트랜잭션 I/O에 사용할 수있는 핸들이 만들어 지므로 CreateFileTransacted 함수를 사용하십시오.

```c++
HANDLE CreateFileA(
  LPCSTR                lpFileName,
  DWORD                 dwDesiredAccess,
  DWORD                 dwShareMode,
  LPSECURITY_ATTRIBUTES lpSecurityAttributes,
  DWORD                 dwCreationDisposition,
  DWORD                 dwFlagsAndAttributes,
  HANDLE                hTemplateFile
);
```

**lpFileName**

작성 또는 열 파일 또는 장치의 이름. 이 이름에는 슬래시 (/) 또는 백 슬래시 ()를 사용할 수 있습니다.

이 함수의 ANSI 버전에서 이름의 길이는 MAX_PATH 문자로 제한됩니다. 이 제한을 32,767 와이드 문자로 확장하려면 유니 코드 버전의 함수를 호출하고 앞에 "\?" 를 추가하십시오.  자세한 내용은 파일, 경로 및 네임 스페이스 이름 지정을 참조하십시오.

특수 장치 이름에 대한 자세한 내용은 [MS-DOS 장치 이름 정의](https://msdn.microsoft.com/7d802e9f-dc09-4e3d-b064-e9b57af396e2)를 참조하십시오.

파일 스트림을 만들려면 파일 이름, 콜론 및 스트림 이름을 지정합니다. 자세한 내용은 파일 스트림을 참조하십시오.

**dwDesiredAccess**

파일 또는 장치에 대한 요청 된 액세스. 읽기, 쓰기, 둘 다 또는 모두 0으로 요약 할 수 있습니다.

가장 일반적으로 사용되는 값은 GENERIC_READ, GENERIC_WRITE 또는 둘 다입니다 (GENERIC_READ | GENERIC_WRITE). 자세한 내용은 일반 액세스 권한, 파일 보안 및 액세스 권한, 파일 액세스 권한 상수 및 [ACCESS_MASK](https://msdn.microsoft.com/f115ee54-3333-4109-8004-d71904a7a943)를 참조하십시오.

이 매개 변수가 0이면 응용 프로그램은 GENERIC_READ 액세스가 거부 된 경우에도 해당 파일이나 장치에 액세스하지 않고 파일, 디렉토리 또는 장치 속성과 같은 특정 메타 데이터를 쿼리 할 수 있습니다.

이미 열린 핸들이 있는 열린 요청에서 dwShareMode 매개 변수로 지정된 공유 모드와 충돌하는 액세스 모드를 요청할 수 없습니다.

자세한 내용은 이 항목의 주의 절 및 [파일 만들기 및 열기](https://msdn.microsoft.com/094cac29-c66d-409e-8928-878dc693d393)를 참조하십시오.



**dwShareMode**

파일 또는 장치의 요청 된 공유 모드. 읽기, 쓰기, 둘 다, 삭제, 모두 또는 없음 (다음 표 참조). 속성 또는 확장 속성에 대한 액세스 요청은이 플래그의 영향을받지 않습니다.

이 매개 변수가 0 이고 CreateFile이 성공하면 파일이나 장치를 공유 할 수 없으며 파일이나 장치에 대한 핸들이 닫힐 때까지 다시 열 수 없습니다. 자세한 내용은주의 섹션을 참조하십시오.

열린 핸들이 있는 기존 요청에 지정된 액세스 모드와 충돌하는 공유 모드를 요청할 수 없습니다. CreateFile이 실패하고 GetLastError 함수가 ERROR_SHARING_VIOLATION을 반환합니다.

다른 프로세스가 파일 이나 장치를 연 상태에서 프로세스가 파일이나 장치를 공유 할 수있게하려면 다음 값 중 하나 이상의 호환 가능한 조합을 사용하십시오. 이 매개 변수와 dwDesiredAccess 매개 변수의 유효한 조합에 대한 자세한 내용은  [파일 만들기 및 열기](https://msdn.microsoft.com/094cac29-c66d-409e-8928-878dc693d393)를 참조하십시오.

```
참고: 열려있는 각 핸들의 공유 옵션은 프로세스 컨텍스트에 관계없이 핸들이 닫힐 때까지 유효합니다.
```

| Value                            | Meaning                                                      |
| -------------------------------- | ------------------------------------------------------------ |
| **0**0x00000000                  | 파일, 장치가 삭제, 읽기 또는 쓰기 액세스를 요청하면 다른 프로세스가 파일이나 장치를 열지 못하도록합니다. |
| **FILE_SHARE_DELETE** 0x00000004 | 파일 또는 장치에 대한 후속 열기 작업을 통해 삭제 액세스를 요청할 수 있습니다. <br/>그렇지 않으면 다른 프로세스가 삭제 액세스를 요청하면 파일이나 장치를 열 수 없습니다.<br/>이 플래그가 지정되지 않았지만 파일 또는 장치가 삭제 액세스를 위해 열린 경우, 함수는 실패합니다.<br/>**Note**  삭제 액세스는 삭제 및 이름 변경 작업을 허용합니다. |
| **FILE_SHARE_READ** 0x00000001   | 파일 또는 장치에 대한 후속 열기 작업을 사용하여 읽기 액세스를 요청할 수 있습니다. 그렇지 않으면 다른 프로세스가 읽기 액세스를 요청하면 파일이나 장치를 열 수 없습니다. 이 플래그가 지정되지 않았지만 파일이나 장치가 읽기 액세스를 위해 열린 경우이 함수는 실패합니다. |
| **FILE_SHARE_WRITE** 0x00000002  | 파일 또는 장치에 대한 후속 열기 작업을 통해 쓰기 액세스를 요청할 수 있습니다. 그렇지 않으면 쓰기 액세스를 요청하면 다른 프로세스가 파일이나 장치를 열 수 없습니다. 이 플래그가 지정되지 않았지만 파일이나 장치가 쓰기 액세스를 위해 열렸거나 쓰기 액세스 권한이있는 파일 매핑을 가지고 있으면 함수가 실패합니다. |

**lpSecurityAttributes**

별도의 관련 데이터 멤버 인 선택적 보안 설명자와 반환 된 핸들을 자식 프로세스에서 상속 할 수 있는지 여부를 결정하는 부울 값을 포함하는 SECURITY_ATTRIBUTES 구조체에 대한 포인터입니다.

이 매개 변수는 NULL 일 수 있습니다.

이 매개 변수가 NULL이면 CreateFile에 의해 반환 된 핸들을 응용 프로그램에서 만들 수있는 자식 프로세스가 상속받을 수 없으며 반환 된 핸들과 연결된 파일이나 장치가 기본 보안 설명자를 가져옵니다.

구조체의 lpSecurityDescriptor 멤버는 파일 또는 장치에 대한 [SECURITY_DESCRIPTOR](https://msdn.microsoft.com/653992aa-4e32-4187-b3ac-727e82bfe0b6)를 지정합니다. 이 멤버가 NULL이면 반환 된 핸들과 연결된 파일이나 장치에 기본 보안 설명자가 할당 됩니다.

CreateFile은 기존 파일이나 장치를 열 때 lpSecurityDescriptor 멤버를 무시하지만 bInheritHandle 멤버를 계속 사용합니다.

구조체 bInheritHandlemember는 반환 된 핸들을 상속 할 수 있는지 여부를 지정합니다.

자세한 내용은 **Remarks section** 을 참조하십시오.



**dwCreationDisposition**

존재하거나 존재하지 않는 파일이나 장치를 취하는 작업.

파일이 아닌 장치의 경우이 매개 변수는 일반적으로 OPEN_EXISTING으로 설정됩니다.

자세한 내용은 **Remarks section** 을 참조하십시오.

이 매개 변수는 결합 할 수없는 다음 값 중 하나 여야합니다.

| Value                   | Meaning                                                      |
| ----------------------- | ------------------------------------------------------------ |
| **CREATE_ALWAYS** 2     | 항상 새 파일을 만듭니다. 지정된 파일이 있고 쓰기 가능한 경우 함수는 파일을 덮어 쓰고 함수는 성공하고 마지막 오류 코드는 ERROR_ALREADY_EXISTS (183)로 설정됩니다.<br/><br/>지정된 파일이 존재하지 않고 유효한 경로 인 경우, 새 파일이 작성되고 함수가 성공하며 마지막 오류 코드가 0으로 설정됩니다.<br/><br/>자세한 내용은이 항목의주의 섹션을 참조하십시오. |
| **CREATE_NEW** 1        | 새 파일을 아직 작성하지 않은 경우에만 작성합니다.<br/><br/>지정된 파일이 있으면 함수는 실패하고 마지막 오류 코드는 ERROR_FILE_EXISTS (80)로 설정됩니다.<br/><br/>지정된 파일이 존재하지 않고 쓰기 가능한 위치에 대한 유효한 경로 인 경우 새 파일이 작성됩니다. |
| **OPEN_ALWAYS** 4       | 항상 파일을 엽니 다. 지정된 파일이 있으면 함수는 성공하고 마지막 오류 코드는 ERROR_ALREADY_EXISTS (183)로 설정됩니다.<br/><br/>지정된 파일이 존재하지 않고 쓰기 가능한 위치에 대한 유효한 경로 인 경우이 함수는 파일을 만들고 마지막 오류 코드는 0으로 설정됩니다. |
| **OPEN_EXISTING**3      | 파일 또는 장치가있는 경우에만 엽니 다. 지정된 파일이나 장치가 없으면이 함수는 실패하고 마지막 오류 코드는 ERROR_FILE_NOT_FOUND (2)로 설정됩니다.<br/><br/>장치에 대한 자세한 내용은주의 섹션을 참조하십시오. |
| **TRUNCATE_EXISTING** 5 | 파일을 열고 크기가 0 바이트가되도록 자릅니다. 지정된 파일이 존재하지 않으면 함수는 실패하고 마지막 오류 코드는 ERROR_FILE_NOT_FOUND (2)로 설정됩니다.<br/><br/>호출 프로세스는 dwDesiredAccess 매개 변수의 일부로 설정된 GENERIC_WRITE 비트를 사용하여 파일을 열어야합니다. |

**dwFlagsAndAttributes**

파일 또는 장치 속성 및 플래그 FILE_ATTRIBUTE_NORMAL은 파일의 가장 일반적인 기본값입니다.

이 매개 변수에는 사용 가능한 파일 속성 (FILE_ATTRIBUTE_ *)의 모든 조합을 포함 할 수 있습니다. 다른 모든 파일 특성은 FILE_ATTRIBUTE_NORMAL보다 우선합니다.

이 매개 변수에는 파일 또는 장치 캐싱 동작, 액세스 모드 및 기타 특수 목적 플래그 제어를위한 플래그 조합 (FILE_FLAG_)도 포함될 수 있습니다. 이것은 FILE_ATTRIBUTE_ 값과 결합됩니다.

이 매개 변수에는 SECURITY_SQOS_PRESENT 플래그를 지정하여 SQOS (Security Quality of Service) 정보를 포함 할 수도 있습니다. 추가 SQOS 관련 플래그 정보는 속성 및 플래그 테이블 다음의 테이블에 표시됩니다.

```
CreateFile이 기존 파일을 열 때 그것은 일반적으로 기존 파일의 파일 특성과 파일 플래그를 결합하고, dwFlagsAndAttributes에의 일부로 제공되는 파일 특성을 무시합니다. 특별한 경우는 파일 생성 및 열기에서 자세히 설명합니다.
```

다음 파일 특성 및 플래그 중 일부는 파일에만 적용될 수 있으며 CreateFile에서 열 수있는 다른 모든 유형의 장치에 적용 할 필요는 없습니다. 자세한 내용은이 항목의주의 절 및 파일 만들기 및 열기를 참조하십시오.

파일 특성에 대한 고급 액세스 방법은 [SetFileAttributes](https://msdn.microsoft.com/3d5400c3-555f-44fc-9470-52a36d04d90b)를 참조하십시오. 값과 설명이있는 모든 파일 속성의 전체 목록을 보려면 **파일 속성 상수**를 참조하십시오.

| Attribute                                   | Meaning                                                      |
| ------------------------------------------- | ------------------------------------------------------------ |
| **FILE_ATTRIBUTE_ARCHIVE** 32 (0x20)        | 백업 또는 제거용 으로 적합한 파일. **일반 파일**             |
| **FILE_ATTRIBUTE_ENCRYPTED** 16384 (0x4000) | 파일 또는 디렉토리가 암호화됩니다. 파일의 경우 이는 파일의 모든 데이터가 암호화됨을 의미합니다. 디렉토리의 경우 이는 새로 생성 된 파일 및 하위 디렉토리의 암호화가 기본임을 의미합니다. 자세한 내용은 파일 암호화를 참조하십시오.<br/><br/>FILE_ATTRIBUTE_SYSTEM도 지정되어 있으면이 플래그는 아무 효과가 없습니다.<br/><br/>Home, Home Premium, Starter 또는 ARM 버전의 Windows에서는 이 플래그가 지원되지 않습니다. |
| **FILE_ATTRIBUTE_HIDDEN** 2 (0x2)           | 파일이 숨겨져 있습니다. 일반 디렉토리 목록에 포함시키지 마십시오. |
| **FILE_ATTRIBUTE_NORMAL** 128 (0x80)        | 파일에 다른 속성이 설정되어 있지 않습니다. 이 속성은 단독으로 사용하는 경우에만 유효합니다. |
| **FILE_ATTRIBUTE_OFFLINE** 4096 (0x1000)    | 파일의 데이터는 즉시 사용할 수 없습니다. 이 특성은 파일 데이터가 물리적으로 오프라인 저장소로 이동되었음을 나타냅니다. 이 특성은 계층 적 저장소 관리 소프트웨어 인 원격 저장소에서 사용됩니다. 응용 프로그램은이 속성을 임의로 변경하면 안됩니다. |
| **FILE_ATTRIBUTE_READONLY** 1 (0x1)         | 파일은 읽기 전용입니다. 응용 프로그램은 파일을 읽을 수는 있지만 파일에 쓰거나 지울 수는 없습니다. |
| **FILE_ATTRIBUTE_SYSTEM** 4 (0x4)           | 파일은 운영 체제의 일부이거나 독점적으로 사용됩니다.         |
| **FILE_ATTRIBUTE_TEMPORARY** 256 (0x100)    | 파일이 임시 저장에 사용되고 있습니다. 자세한 내용은이 항목의 [캐싱 동작](https://docs.microsoft.com/) 섹션을 참조하십시오. |

| Flag                                        | Meaning                                                      |
| ------------------------------------------- | ------------------------------------------------------------ |
| **FILE_FLAG_BACKUP_SEMANTICS** 0x02000000   | 백업 또는 복원 작업을 위해 파일을 열거 나 만들었습니다. 시스템은 프로세스에 SE_BACKUP_NAME 및 SE_RESTORE_NAME 권한이 있으면 호출 프로세스가 파일 보안 검사를 무시하도록합니다. 자세한 내용은 [토큰에서 권한 변경](https://msdn.microsoft.com/b8e47d04-07c1-4d57-8209-6b0c397476e5)을 참조하십시오.<br/><br/>디렉토리에 대한 핸들을 얻으려면이 플래그를 설정해야합니다. 디렉터리 핸들은 파일 핸들 대신 일부 기능으로 전달 될 수 있습니다. 자세한 내용은주의 섹션을 참조하십시오. |
| **FILE_FLAG_DELETE_ON_CLOSE** 0x04000000    | 파일은 지정된 핸들과 다른 열려 있거나 중복 된 핸들을 포함하여 모든 핸들이 닫힌 후 즉시 삭제됩니다. 파일에 대한 열린 핸들이있는 경우 FILE_SHARE_DELETE 공유 모드로 모두 열지 않으면 호출이 실패합니다.<br/><br/>FILE_SHARE_DELETE 공유 모드가 지정되어 있지 않으면 파일에 대한 후속 열기 요청이 실패합니다. |
| **FILE_FLAG_NO_BUFFERING** 0x20000000       | 파일 또는 장치가 데이터 읽기 및 쓰기를위한 시스템 캐싱없이 열리고 있습니다. 이 플래그는 하드 디스크 캐싱 또는 메모리 매핑 된 파일에는 영향을주지 않습니다. FILE_FLAG_NO_BUFFERING 플래그를 사용하여 CreateFile로 열린 파일을 성공적으로 사용하기위한 엄격한 요구 사항이 있습니다. 자세한 내용은 [파일 버퍼링](https://msdn.microsoft.com/ae1e5d0f-9b55-4aae-8402-b9c8e33d9363)을 참조하십시오. |
| **FILE_FLAG_OPEN_NO_RECALL** 0x00100000     | 파일 데이터가 요청되었지만 원격 저장소에 계속 있어야합니다. 로컬 저장소로 다시 전송하면 안됩니다. 이 플래그는 원격 스토리지 시스템에서 사용됩니다. |
| **FILE_FLAG_OPEN_REPARSE_POINT** 0x00200000 | 정상적인 재분석 처리는 발생하지 않습니다. CreateFile은 재분석 지점을 열려고 시도합니다. 파일을 열면 재분석 지점을 제어하는 필터가 작동하는지 여부에 관계없이 파일 핸들이 반환됩니다.<br/><br/>이 플래그는 CREATE_ALWAYS 플래그와 함 2 사용할 수 없습니다.<br/><br/>파일이 재분석 지점이 아니면이 플래그는 무시됩니다.<br/><br/>자세한 내용은주의 섹션을 참조하십시오. |
| **FILE_FLAG_OVERLAPPED **0x40000000         | 비동기 I/O를 위해 파일 또는 장치를 열거 나 만들었습니다. 이 핸들에서 후속 I/O 작업이 완료되면 OVERLAPPED 구조에 지정된 이벤트가 신호 상태로 설정됩니다.<br/><br/>이 플래그가 지정되면, 파일은 동시 읽기/쓰기 조작에 사용될 수 있습니다.<br/><br/>이 플래그를 지정하지 않으면 읽기/쓰기 함수에 대한 호출이 OVERLAPPED 구조를 지정하더라도 I/O 조작이 직렬화됩니다..<br/><br/>이 플래그로 작성된 파일 핸들을 사용할 때 고려해야 할 사항에 대해서는이 항목의 동기 및 비동기 I/O 핸들 절을 참조하십시오. |
| **FILE_FLAG_POSIX_SEMANTICS **0x0100000     | POSIX 규칙에 따라 액세스가 발생합니다. 여기에는 해당 이름을 지원하는 파일 시스템에 대해 이름이 다른 여러 파일 (대소 문자 만 다를 수 있음)이 허용됩니다. 이 플래그를 사용하여 만든 파일은 MS-DOS 또는 16 비트 Windows 용으로 작성된 응용 프로그램에서 액세스 할 수 없으므로이 옵션을 사용할 때는주의하십시오. |
| **FILE_FLAG_RANDOM_ACCESS **0x10000000      | 액세스는 무작위로 이루어집니다. 시스템은이를 파일 캐싱을 최적화하기위한 힌트로 사용할 수 있습니다. 파일 시스템이 캐시 된 I/O 및 FILE_FLAG_NO_BUFFERING을 지원하지 않으면이 플래그는 아무 효과가 없습니다.<br/><br/>자세한 내용은이 항목의 캐싱 동작 섹션을 참조하십시오. |
| **FILE_FLAG_SESSION_AWARE** 0x00800000      | 파일 또는 장치가 세션 인식으로 열리고 있습니다. 이 플래그를 지정하지 않으면 세션 0에서 실행중인 프로세스에서 세션 별 장치 (예 : RemoteFX USB 리디렉션을 사용하는 장치)를 열 수 없습니다.이 플래그는 세션 0에 있지 않은 호출자에게는 아무런 영향을 미치지 않습니다. Windows 서버 에디션. Windows Server 2008 R2 및 Windows Server 2008 :이 플래그는 Windows Server 2012 이전에는 지원되지 않습니다. |
| **FILE_FLAG_SEQUENTIAL_SCAN** 0x08000000    | 액세스는 처음부터 끝까지 순차적으로 이루어집니다. 시스템은이를 파일 캐싱을 최적화하기위한 힌트로 사용할 수 있습니다..<br/>읽기 전용 (역방향 스캔)이 사용되는 경우이 플래그를 사용하면 안됩니다.<br/><br/>파일 시스템이 캐시 된 I/O 및 FILE_FLAG_NO_BUFFERING을 지원하지 않으면이 플래그는 아무 효과가 없습니다.<br/><br/>자세한 내용은이 항목의 캐싱 동작 섹션을 참조하십시오. |
| **FILE_FLAG_WRITE_THROUGH** 0x80000000      | 쓰기 작업은 중간 캐시를 통과하지 않으며 직접 디스크로 이동합니다.rn |

dwFlagsAndAttributes 매개 변수는 SQOS 정보를 지정할 수도 있습니다. 자세한 내용은 가장 (Impersonation) 수준을 참조하십시오. 호출 응용 프로그램이 dwFlagsAndAttributes의 일부로 SECURITY_SQOS_PRESENT 플래그를 지정하면 다음 값 중 하나 이상을 포함 할 수도 있습니다.

| Security flag                 | Meaning                                                      |
| ----------------------------- | ------------------------------------------------------------ |
| **SECURITY_ANONYMOUS**        | 익명의 가장 수준에서 클라이언트를 가장합니다.                |
| **SECURITY_CONTEXT_TRACKING** | 보안 추적 모드는 동적입니다. 이 플래그를 지정하지 않으면 보안 추적 모드가 고정됩니다. |
| **SECURITY_DELEGATION**       | 위임 위임 수준에서 클라이언트를 가장합니다.                  |
| **SECURITY_EFFECTIVE_ONLY**   | 클라이언트 보안 컨텍스트의 활성화 된 부분 만 서버에서 사용할 수 있습니다. 이 플래그를 지정하지 않으면 클라이언트 보안 컨텍스트의 모든 측면을 사용할 수 있습니다.rn |
| **SECURITY_IDENTIFICATION**   | 식별 가장 (impersonation) 수준에서 클라이언트를 가장합니다.  |
| **SECURITY_IMPERSONATION**    | 가장 (impersonation) 수준에서 클라이언트를 가장합니다. SECURITY_SQOS_PRESENT 플래그와 함께 다른 플래그가 지정되지 않은 경우 이것이 기본 작동입니다. |

**hTemplateFile**

GENERIC_READ 액세스 권한이있는 템플리트 파일에 대한 유효한 핸들. 템플리트 파일은 작성중인 파일에 대한 파일 속성 및 확장 속성을 제공합니다.

이 매개 변수는 NULL 일 수 있습니다.

기존 파일을 열 때 CreateFile은이 매개 변수를 무시합니다.

새 암호화 된 파일을 열 때 파일은 상위 디렉토리에서 임의 액세스 제어 목록을 상속합니다. 자세한 내용은 [파일 암호화](https://msdn.microsoft.com/5f20109f-727d-44a9-90a1-0adc19b00d28)를 참조하십시오.



**Return Value**

함수가 성공하면 반환 값은 지정된 파일, 장치, 명명 된 파이프 또는 메일 슬롯에 대한 열린 핸들입니다.

함수가 실패하면 반환 값은 INVALID_HANDLE_VALUE입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

CreateFile은 원래 파일 상호 작용을 위해 특별히 개발되었지만 Windows 개발자가 사용할 수있는 대부분의 다른 유형의 I/O 장치와 메커니즘을 포함하도록 확장 및 향상되었습니다. 이 섹션에서는 CreateFile을 다양한 컨텍스트 및 다양한 I/O 유형으로 사용할 때 개발자가 겪을 수있는 다양한 문제를 다루려고합니다. 텍스트는 파일 시스템의 실제 파일에 저장된 데이터를 구체적으로 언급 할 때만 파일이라는 단어를 사용하려고 시도합니다. 그러나 파일의 일부 사용은 파일과 유사한 메커니즘을 지원하는 I/O 객체를보다 일반적으로 참조 할 수 있습니다. 파일이라는 용어의 자유로운 사용은 이전에 언급 된 역사적인 이유로 인해 상수 이름과 매개 변수 이름에서 특히 빈번합니다.

CreateFile에서 반환 된 개체 핸들을 사용하여 응용 프로그램이 끝나면 CloseHandle 함수를 사용하여 핸들을 닫습니다. 이렇게하면 시스템 리소스가 확보 될뿐 아니라 파일이나 장치를 공유하고 데이터를 디스크에 커밋하는 것과 같은 일에 더 많은 영향을 미칠 수 있습니다. 이 주제에는 구체적인 내용이 적혀 있습니다.

기타 더 많은 정보는 https://docs.microsoft.com/ko-kr/windows/desktop/api/fileapi/nf-fileapi-createfilea 를 참조 하도록 한다.



#### <span style="color:magenta">RevertToSelf()</span>

RevertToSelf 함수는 클라이언트 응용 프로그램의 가장을 종료합니다.

```c++
BOOL RevertToSelf(

);
```

**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

프로세스는 [DdeImpersonateClient](https://msdn.microsoft.com/en-us/library/ms648756(v=VS.85).aspx), [ImpersonateDdeClientWindow](https://msdn.microsoft.com/en-us/library/ms649005(v=VS.85).aspx), [ImpersonateLoggedOnUser](https://msdn.microsoft.com/cf5c31ae-6749-45c2-888f-697060cc8c75), [ImpersonateNamedPipeClient](https://msdn.microsoft.com/63fc90ac-536a-4d9b-ba0d-19dc0cc09e6b), [ImpersonateSelf](https://msdn.microsoft.com/f909e3a7-6c7f-4c05-aa2e-e637113804c9), [ImpersonateAnonymousToken](https://msdn.microsoft.com/98d1072e-f569-4c8c-9254-fa558054c7ec) 또는 [SetThreadToken](https://msdn.microsoft.com/ba1a4fce-b3cc-423d-b213-5dfca3dea708) 함수를 사용하여 시작된 가장을 완료 한 후 **RevertToSelf** 함수를 호출해야합니다.

[RpcImpersonateClient](https://msdn.microsoft.com/1b91c4dc-ac49-4002-b293-a25ca2ffcb21) 함수를 사용하여 클라이언트를 가장 한 RPC 서버는 [RpcRevertToSelf](https://msdn.microsoft.com/07bbf6fa-f1df-4d9c-ae67-e79e2ccc12c8) 또는 [RpcRevertToSelfEx](https://msdn.microsoft.com/8860cee2-7e53-4a07-a379-fd00f3d01def)를 호출하여 가장을 종료해야합니다.

**RevertToSelf**가 실패하면 응용 프로그램이 클라이언트 컨텍스트에서 계속 실행됩니다. 이는 적절하지 않습니다. **RevertToSelf**가 실패하면 프로세스를 종료해야합니다.



**Examples**

이 기능을 사용하는 예는 [ACL을 사용하여 클라이언트 액세스 확인](https://msdn.microsoft.com/de21968e-4590-4798-9152-43204d55521f)을 참조하십시오.



### <span style="color:green">사용된 주요 자료형</span>

#### <span style="color:magenta">ACCESS_MASK</span>

ACCESS_MASK 데이터 형식은 표준, 특정 및 일반 권한을 정의하는 DWORD 값입니다. 이러한 권한은 ACE (액세스 제어 항목)에서 사용되며 개체에 대한 요청되거나 허가 된 액세스를 지정하는 주요 수단입니다.

```c++
typedef DWORD ACCESS_MASK;
typedef ACCESS_MASK* PACCESS_MASK;
```

**Remarks**

이 값의 비트는 다음과 같이 할당됩니다.

| Bits  | Meaning                                                      |
| ----- | ------------------------------------------------------------ |
| 0 15  | 특정 권리. 마스크와 연관된 객체 유형에 특정한 접근 마스크를 포함합니다. |
| 16 23 | 표준 권리. 개체의 표준 액세스 권한을 포함합니다.             |
| 24    | 시스템 보안에 액세스하십시오 (ACCESS_SYSTEM_SECURITY). 시스템 액세스 제어 목록 (SACL)에 대한 액세스를 나타내는 데 사용됩니다. 이러한 유형의 액세스에서는 호출 프로세스에 SE_SECURITY_NAME (감사 및 보안 로그 관리) 권한이 있어야합니다. 이 플래그가 감사 액세스 ACE (성공 또는 실패한 액세스)의 액세스 마스크에 설정되면 SACL 액세스가 감사됩니다. |
| 25    | 최대 허용 개수 (**MAXIMUM_ALLOWED**).                        |
| 26 27 | Reserved.                                                    |
| 28    | Generic all (**GENERIC_ALL**).                               |
| 29    | Generic execute (**GENERIC_EXECUTE**).                       |
| 30    | Generic write (**GENERIC_WRITE**).                           |
| 31    | Generic read (**GENERIC_READ**).                             |

표준 권한 비트 (16-23)는 개체의 표준 액세스 권한을 포함하며 다음과 같은 미리 정의 된 플래그의 조합 일 수 있습니다.

| Bit  | Flag             | Meaning                                                      |
| ---- | ---------------- | ------------------------------------------------------------ |
| 16   | **DELETE**       | Delete access.                                               |
| 17   | **READ_CONTROL** | 보안 설명자의 DACL (Discretionary Access Control List) 소유자, 그룹 및 읽기 액세스 권한 |
| 18   | **WRITE_DAC**    | Write access to the DACL.                                    |
| 19   | **WRITE_OWNER**  | Write access to owner.                                       |
| 20   | **SYNCHRONIZE**  | Synchronize access.                                          |



#### <span style="color:red">Access Mask</span>

원문: https://docs.microsoft.com/ko-kr/windows-hardware/drivers/ifs/access-mask

액세스 마스크의 기능은 액세스 권한을 간략한 형태로 설명하는 것입니다. 액세스 관리를 단순화하기 위해 액세스 마스크에는 4 비트 세트, 즉 [RtlMapGenericMask](https://msdn.microsoft.com/library/windows/hardware/ff562027) 함수를 사용하여보다 상세한 권한 세트로 변환되는 일반 권한이 포함됩니다.

다음 그림은 액세스 마스크를 보여줍니다.

![](.\images\fssecurity-03.png)

일반 권한은 다음 중 하나입니다.

- GENERIC_READ        —  객체에 의해 유지되는 정보를 읽을 권리.
- GENERIC_WRITE      —  객체에 의해 유지되는 정보를 쓸 권리.
- GENERIC_EXECUTE —  객체를 실행하거나 양자 택일로 볼 수있는 권리.
- GENERIC_ALL          —  객체를 읽고 쓰고 실행할 수있는 권한.

이러한 권한을 결합 할 수 있습니다 (예 : GENERIC_READ 및 GENERIC_WRITE 모두 요청할 수 있음). 결과 매핑을 통해 각 일반 권한에 필요한 권한이 필요합니다. 이 패러다임은 UNIX 자원에 대한 액세스를 제어하는 데 사용되는 UNIX 'rwx'액세스 비트를 모방합니다. 액세스 마스크의 일반적인 권한은 Windows에서 응용 프로그램 개발을 간소화합니다. 이러한 권한은 다양한 개체 유형에 대한 다양한 보안 권한을가렸기 때문입니다.



다음과 같은 표준 권한 집합이 모든 개체 유형에 적용됩니다.

- DELETE—특정 객체를 삭제할 수있는 권리.
- READ_CONTROL—객체에 대한 제어 (보안) 정보를 읽을 수있는 권한.
- WRITE_DAC—객체에 대한 제어 (보안) 정보를 수정할 수있는 권한.
- WRITE_OWNER—개체의 소유자 SID를 수정할 수있는 권한 소유자는 항상 개체를 수정할 권리가 있음을 상기하십시오.
- SYNCHRONIZE—주어진 객체를 기다릴 수있는 권한 (객체에 대한 유효한 개념이라고 가정).



액세스 마스크의 하위 16 비트는 특정 권한을 나타냅니다. 이러한 특정 권한의 의미는 문제의 대상에 고유합니다. 파일 시스템의 주요 관심사는 파일 객체에 대한 특정 권한입니다. 파일 개체의 경우 특정 권한은 일반적으로 파일 개체가 파일 또는 디렉터리를 나타내는 지 여부에 따라 다르게 해석됩니다. 파일의 경우 일반적인 해석은 다음과 같습니다.

- **FILE_READ_DATA**—지정된 파일로부터 데이터를 읽을 권리.
- **FILE_WRITE_DATA**—주어진 파일에 데이터를 쓸 권리 (파일의 기존 범위 내).
- **FILE_APPEND_DATA**—주어진 파일을 확장 할 권한.
- **FILE_READ_EA**—파일의 확장 된 속성을 읽을 수있는 권리.
- **FILE_WRITE_EA**—파일의 확장 된 속성을 수정할 수있는 권한.
- **FILE_EXECUTE**—지정된 파일을 로컬에서 실행할 수있는 권한. 원격 공유에 저장된 파일을 실행하려면 파일이 서버에서 읽히지 만 클라이언트에서 실행되므로 읽기 권한이 있어야합니다.
- **FILE_READ_ATTRIBUTES**—파일의 속성 정보를 읽을 수있는 권리.
- **FILE_WRITE_ATTRIBUTES**—파일의 속성 정보를 수정할 권리.



디렉토리의 경우 동일한 비트 값이 사용되지만 다음과 같은 경우에는 해석이 다릅니다.

- **FILE_LIST_DIRECTORY**—디렉토리의 내용을 나열 할 수있는 권한.
- **FILE_ADD_FILE**—디렉토리 안에 새로운 파일을 만들 권리.
- **FILE_ADD_SUBDIRECTORY**—디렉토리 내에 새로운 디렉토리 (서브 디렉토리)를 생성 할 수있는 권리.
- **FILE_READ_EA**—지정된 디렉토리의 확장 속성을 읽을 수있는 권한
- **FILE_WRITE_EA**—지정된 디렉토리의 확장 속성을 쓰는 권한.
- **FILE_TRAVERSE**—디렉토리 내의 오브젝트에 액세스 할 수있는 권한 FILE_TRAVERSE 액세스 권한은 FILE_LIST_DIRECTORY 액세스 권한과 다릅니다. FILE_LIST_DIRECTORY 액세스 권한을 보유하면 엔티티는 디렉토리 내용의 목록을 가져올 수 있지만 FILE_TRAVERSE 액세스 권한은 엔티티에 객체에 액세스 할 수있는 권한을 부여합니다. FILE_LIST_DIRECTORY 액세스 권한이없는 호출자는 이미 알고있는 파일을 열 수 있지만 디렉토리의 내용 목록을 가져올 수는 없습니다.
- **FILE_DELETE_CHILD**—현재 디렉토리 내의 파일이나 디렉토리를 삭제할 권한.
- **FILE_READ_ATTRIBUTES**—디렉토리의 속성 정보를 읽을 권리.
- **FILE_WRITE_ATTRIBUTES**—디렉토리의 속성 정보를 수정할 수있는 권리.

파일 객체에 대한 표준 및 특정 권한에 대한 일반 권한의 실제 매핑은 I/O 관리자에 의해 정의됩니다. 이 매핑은 [IoGetFileObjectGenericMapping](https://msdn.microsoft.com/library/windows/hardware/ff549231)을 사용하여 파일 시스템에서 검색 할 수 있습니다. 일반적으로이 매핑은 파일 시스템을 호출하기 전에 I/O 관리자가 IRP_MJ_CREATE를 처리하는 동안 수행됩니다. 그러나 이것은 특정 작업 (예 : 특수화 된 FSCTL 작업)에 대한 보안을 검사하는 파일 시스템에 의해 수행 될 수 있습니다.



#### <span style="color:magenta">SECURITY_ATTRIBUTES</span>

SECURITY_ATTRIBUTES 구조체에는 **개체에 대한 보안 설명자**가 들어 있으며 이 구조체를 지정하여 검색된 핸들을 상속받을 수 있는지 여부를 지정합니다. 이 구조는 [CreateFile](https://msdn.microsoft.com/ko-KR/library/windows/desktop/aa363858), [CreatePipe](https://msdn.microsoft.com/ko-KR/library/windows/desktop/aa365152), [CreateProcess](https://msdn.microsoft.com/ko-KR/library/windows/desktop/ms682425), [RegCreateKeyEx](https://msdn.microsoft.com/ko-KR/library/windows/desktop/ms724844) 또는 [RegSaveKeyEx](https://msdn.microsoft.com/ko-KR/library/windows/desktop/ms724919) 와 같은 다양한 함수로 만든 개체에 대한 보안 설정을 제공합니다.

```c++
typedef struct _SECURITY_ATTRIBUTES {
  DWORD  nLength;
  LPVOID lpSecurityDescriptor;
  BOOL   bInheritHandle;
} SECURITY_ATTRIBUTES, *PSECURITY_ATTRIBUTES, *LPSECURITY_ATTRIBUTES;
```

| parameter                | description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **nLength**              | 이 구조체의 크기 (바이트)입니다. 이 값을 SECURITY_ATTRIBUTES 구조체의 크기로 설정하십시오. |
| **lpSecurityDescriptor** | 객체에 대한 액세스를 제어하는 SECURITY_DESCRIPTOR 구조체에 대한 포인터입니다. 이 멤버의 값이 NULL이면 개체에 호출 프로세스의 액세스 토큰과 연결된 기본 보안 설명자가 할당됩니다. 이는 NULL DACL (임의 액세스 제어 목록)을 할당하여 모든 사용자에게 액세스 권한을 부여하는 것과 동일하지 않습니다. 기본적으로 프로세스의 액세스 토큰에있는 기본 DACL은 액세스 토큰이 나타내는 사용자에게만 액세스를 허용합니다.<br/>보안 설명 자 만들기에 대한 자세한 내용은 [보안 설명 자 만들기](https://msdn.microsoft.com/ko-KR/library/windows/desktop/aa446595)를 참조하십시오. |
| **bInheritHandle**       | 새 프로세스를 만들 때 반환 된 핸들을 상속할지 여부를 지정하는 부울 값입니다. 이 멤버가 TRUE이면 새 프로세스가 핸들을 상속합니다. |



## <span style="color:blue">MirrorCloseFile</span>

함수 설명:  **CallBack**,  PDOKAN_FILE_INFO 를 이용하여 열린 파일 핸들을 닫는다.
제약 조건:  없음
함수 원형: MirrorCloseFile( LPCWSTR                      FileName,
                                                PDOKAN_FILE_INFO   DokanFileInfo)
리턴     값: 없음

| param         | in/out | 설명                                                         |
| ------------- | :----: | ------------------------------------------------------------ |
| FileName      |   in   | Handle 을 닫을 파일명 (사용 하지 않음, Debug 용도로 사용가능) |
| DokanFileInfo |   in   | 닫을 Handle 이 저장 되어 있는 DOKAN_FILE_INFO (Context 에 Handle 이 저장 되어 있음) |

### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">CloseHandle</span>

이 메소드를 구현하여 이 객체와 관련된 핸들을 닫습니다.

```c++
HRESULT CloseHandle(
   HANDLE hHandle 
);
```

**hHandle**

닫을 Handle



**Return Value**

성공하면 S_OK를 반환하고, 실패하면 HRESULT를 반환합니다.



## <span style="color:blue">MirrorCleanup</span>

함수 설명: **CallBack**,  PDOKAN_FILE_INFO 를 이용하여 열린 파일 핸들을 닫고 삭제 Flag 가 설정 되어 있으면 File 또는 Directory 를 삭제 한다.
제약 조건: 없음
함수 원형: MirrorCleanup(  LPCWSTR                    FileName,
                                                PDOKAN_FILE_INFO DokanFileInfo ) 
리턴     값: 없음

| param         | in/out | 설명                                                         |
| ------------- | :----: | ------------------------------------------------------------ |
| FileName      |   in   | 삭제할 파일명 또는 Directory명                               |
| DokanFileInfo |   in   | 닫을 Handle 이 저장 되어 있는 DOKAN_FILE_INFO (Context 에 Handle 이 저장 되어 있음) |

### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">CloseHandle</span>

- MirrorCloseFile 에 설명 되었음



#### <span style="color:magenta">RemoveDirectory</span>

빈 디렉토리를 삭제합니다.

이 작업을 트랜잭션 연산으로 수행하려면 RemoveDirectoryTransacted 함수를 사용하십시오.

```c++
BOOL RemoveDirectoryA(
  LPCSTR lpPathName
);
```

**lpPathName**

제거 할 디렉토리의 경로. 이 경로는 빈 디렉토리를 지정해야하며 호출 프로세스에는 디렉토리에 대한 삭제 액세스 권한이 있어야합니다.



**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### <span style="color:magenta">DeleteFile</span>

기존 파일을 삭제합니다.

이 연산을 트랜잭션 연산으로 수행하려면 DeleteFileTransacted 함수를 사용하십시오.

```c++
BOOL DeleteFileA(
  LPCSTR lpFileName
);
```

**lpFileName**

삭제할 파일의 이름.



**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



## <span style="color:blue">MirrorReadFile</span>

함수 설명: **CallBack**,  파일에서 Data 를 읽는다, 기존에 파일이 열려 있으면 열린 Handle 을 이용하여 data를 읽고 파일이 열려 있지 않으면 파일을 연 다음 data를 읽고 파일을 닫는다.
제약 조건: 없음
함수 원형: MirrorReadFile( LPCWSTR                   FileName, 
                                               LPVOID                       Buffer,
                                               DWORD                      BufferLength,
                                               LPDWORD                  ReadLength,
                                               LONGLONG               Offset,
                                               PDOKAN_FILE_INFO DokanFileInfo) 
리턴     값:  정상적으로 파일을 읽었으면 0, 그렇지 않으면 오류 코드

| param         | in/out | 설명                                                         |
| ------------- | :----: | ------------------------------------------------------------ |
| FileName      |   in   | 읽을 파일명                                                  |
| Buffer        |  out   | 읽은 data 를 저장 할 Buffer                                  |
| BufferLength  |   in   | 읽을 Byte 수                                                 |
| ReadLength    |  out   | 읽은 Byte 수                                                 |
| Offset        |   in   | 읽을 위치                                                    |
| DokanFileInfo |   in   | 이미 파일이 열려 있으면 Handle 을 전달하는 도칸 구조체의 포인터 |

### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">CreateFile</span>

- 위에 설명 되어 있음



#### <span style="color:magenta">SetFilePointerEx</span>

지정한 파일의 파일 포인터를 이동합니다.

```c++
BOOL SetFilePointerEx(
  HANDLE         hFile,
  LARGE_INTEGER  liDistanceToMove,
  PLARGE_INTEGER lpNewFilePointer,
  DWORD          dwMoveMethod
);
```

**hFile**

파일 핸들. 파일 핸들은 GENERIC_READ 또는 GENERIC_WRITE 액세스 권한으로 작성되어야합니다. 자세한 내용은 [파일 보안 및 액세스 권한](https://msdn.microsoft.com/991d7d94-fae7-406f-b2e3-dee811279366)을 참조하십시오.

**liDistanceToMove**

파일 포인터를 이동할 바이트 수입니다. 양수 값을 지정하면 파일에서 포인터가 앞으로 이동하고 음수 값을 지정하면 파일 포인터가 뒤로 이동합니다.

**lpNewFilePointer**

새로운 파일 포인터를 받을 변수에 대한 포인터. 이 매개 변수가 NULL이면 새 파일 포인터가 반환되지 않습니다.

**dwMoveMethod**

파일 포인터의 시작점입니다. 이 매개 변수는 다음 값 중 하나 일 수 있습니다.

| Value              | Meaning                                                      |
| ------------------ | ------------------------------------------------------------ |
| **FILE_BEGIN** 0   | 시작점은 0 또는 파일의 시작입니다. 이 플래그가 지정되면 liDistanceToMove 매개 변수는 부호없는 값으로 해석됩니다. |
| **FILE_CURRENT** 1 | 시작점은 파일 포인터의 현재 값입니다.                        |
| **FILE_END** 2     | 시작점은 현재 파일 끝 위치입니다.                            |



**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다.  함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



**Remarks**

이 함수가 반환하는 파일 포인터는 중복 된 읽기 및 쓰기 작업에는 사용되지 않습니다. 중첩 된 작업에 대한 오프셋을 지정하려면 [OVERLAPPED](https://msdn.microsoft.com/5037f6b9-e316-483b-a8e2-b58d2587ebd9) 구조체의 Offset 및 OffsetHigh 멤버를 사용합니다.

파이프 또는 통신 장치와 같은 비 견해 (nonseeking) 장치에 대한 핸들과 함께 SetFilePointerEx 함수를 사용할 수 없습니다. hFile의 파일 형식을 결정하려면 [GetFileType](https://msdn.microsoft.com/11760e2f-5e8b-4ec7-959b-fb23d5d9a0aa) 함수를 사용하십시오.

다중 스레드 응용 프로그램에서 파일 포인터를 설정할 때주의하십시오. 공유 리소스에 대한 액세스를 동기화해야합니다. 예를 들어 스레드가 파일 핸들을 공유하고, 파일 포인터를 업데이트하고, 파일에서 읽는 응용 프로그램은 중요 섹션 개체 나 뮤텍스 개체를 사용하여이 시퀀스를 보호해야합니다. 이러한 개체에 대한 자세한 내용은 [Critical Section Objects](https://msdn.microsoft.com/2ec11a42-3d12-4d60-9dd7-dc38926d56e1) 및 [Mutex Objects](https://msdn.microsoft.com/eca0795a-1fd0-4034-9d61-9416670919cf)를 참조하십시오.

FILE_FLAG_NO_BUFFERING 플래그가 설정된 상태로 hFile 핸들을 연 경우 응용 프로그램은 파일 포인터를 섹터 정렬 위치로만 이동할 수 있습니다. 섹터 정렬 위치는 볼륨의 섹터 크기의 정수 배가되는 위치입니다. 응용 프로그램은 [GetDiskFreeSpace](https://msdn.microsoft.com/4fe14c49-3fd6-48b7-92de-a0c867b2e042) 함수를 호출하여 볼륨의 섹터 크기를 가져올 수 있습니다. 응용 프로그램에서 섹터 정렬되지 않은 위치와 FILE_FLAG_NO_BUFFERING으로 열린 핸들을 가져 오는 이동 거리 값을 사용하여 SetFilePointerEx를 호출하면 함수가 실패하고 GetLastError가 ERROR_INVALID_PARAMETER를 반환합니다. 자세한 내용은 [파일 버퍼링](https://msdn.microsoft.com/ae1e5d0f-9b55-4aae-8402-b9c8e33d9363)을 참조하십시오.

파일 포인터가 파일의 끝을 넘어서는 위치로 설정하는 것은 오류가 아닙니다. [SetEndOfFile](https://msdn.microsoft.com/2a579609-144a-4b77-8605-87aecf1f0957), [WriteFile](https://msdn.microsoft.com/9d6fa723-fe3e-4052-b0b3-2686eee076a7) 또는 [WriteFileEx](https://msdn.microsoft.com/6995c4ee-ba91-41d5-b72d-19dc2eb95945) 함수를 호출 할 때까지 파일 크기가 증가하지 않습니다. 쓰기 작업을 수행하면 파일 포인터의 크기와 기록 된 버퍼의 크기가 더해져 중간 바이트가 초기화되지 않은 채로 남아있게됩니다.

SetFilePointerEx를 사용하여 파일의 길이를 결정할 수 있습니다. 이렇게하려면 dwMoveMethod에 FILE_END를 사용하고 위치 0을 찾으십시오. 반환 된 파일 오프셋은 파일의 길이입니다. 그러나이 방법은 의도하지 않은 부작용 (예 : 현재 파일 포인터를 저장하지 못해 프로그램이 해당 위치로 돌아갈 수 있음)가 발생할 수 있습니다. 대신 [GetFileSizeEx](https://msdn.microsoft.com/782457bc-8f37-4eec-8ff3-b148fd0a7345) 함수를 사용하는 것이 더 간단하고 안전합니다.

SetFilePointerEx를 사용하여 현재 파일 포인터 위치를 쿼리 할 수도 있습니다. 이렇게하려면 FILE_CURRENT 및 0 거리의 이동 메소드를 지정하십시오.



#### <span style="color:magenta">ReadFile</span>

지정된 파일 또는 입출력 (I/O) 장치에서 데이터를 읽습니다. 장치가 지원할 경우 파일 포인터로 지정된 위치에서 읽기가 발생합니다.

이 함수는 동기 및 비동기 작업을 위해 설계되었습니다. 비동기 작업 전용으로 설계된 유사한 함수에 대해서는 [ReadFileEx](https://msdn.microsoft.com/6c1a4de1-6cae-4c35-bfba-0bc252fadbd9)를 참조하십시오.

```c++
BOOL ReadFile(
  HANDLE       hFile,
  LPVOID       lpBuffer,
  DWORD        nNumberOfBytesToRead,
  LPDWORD      lpNumberOfBytesRead,
  LPOVERLAPPED lpOverlapped
);
```

**hFile**

장치 핸들 (예 : 파일, 파일 스트림, 실제 디스크, 볼륨, 콘솔 버퍼, 테이프 드라이브, 소켓, 통신 자원, 메일 슬롯 또는 파이프).

hFile 매개 변수는 읽기 액세스 권한으로 작성되어야합니다. 자세한 내용은 [일반 액세스 권한](https://msdn.microsoft.com/e18cede9-9bf7-4866-850b-5d7fa43a5b0f) 및 [파일 보안 및 액세스 권한](https://msdn.microsoft.com/991d7d94-fae7-406f-b2e3-dee811279366)을 참조하십시오.

비동기 읽기 작업의 경우 hFile은 [CreateFile](https://msdn.microsoft.com/80a96083-4de9-4422-9705-b8ad2b6cbd1b) 함수에 의해 **FILE_FLAG_OVERLAPPED** 플래그 또는 [소켓](https://msdn.microsoft.com/6bf6e6c4-6268-479c-86a6-52e90cf317db) 또는 [accept](https://msdn.microsoft.com/72246263-4806-4ab2-9b26-89a1782a954b) 함수에 의해 반환 된 소켓 핸들로 열린 모든 핸들이 될 수 있습니다.

**lpBuffer**

파일 또는 장치에서 읽은 데이터를 수신하는 버퍼에 대한 포인터입니다.

이 버퍼는 읽기 작업 동안 유효해야합니다. 호출 원은 읽기 조작이 완료 할 때까지 이 버퍼를 사용하지 않아야합니다.

**nNumberOfBytesToRead**

읽을 최대 바이트 수입니다.

**lpNumberOfBytesRead**

**동기** hFile 매개 변수를 사용할 때 **읽은 바이트 수** 를 수신하는 변수에 대한 포인터. ReadFile은 작업 또는 오류 검사를 수행하기 전에이 값을 0으로 설정합니다. 잠재적으로 잘못된 결과를 피하기 위해 **비동기 작업 인 경우이 매개 변수에 NULL**을 사용하십시오.

이 매개 변수는 lpOverlapped 매개 변수가 NULL이 아닌 경우에만 NULL이 될 수 있습니다.

자세한 내용은 Remarks sectiion을 참조하십시오.

**lpOverlapped**

hFile 매개 변수가 FILE_FLAG_OVERLAPPED로 열리면 [OVERLAPPED](https://msdn.microsoft.com/5037f6b9-e316-483b-a8e2-b58d2587ebd9) 구조체에 대한 포인터가 필요합니다. 그렇지 않으면 NULL이 될 수 있습니다.

hFile이 FILE_FLAG_OVERLAPPED로 열리면 lpOverlapped 매개 변수가 유효하고 고유 한 OVERLAPPED 구조를 가리켜 야합니다. 그렇지 않으면 함수가 읽기 작업이 완료되었다고 잘못보고 할 수 있습니다.

바이트 오프셋을 지원하는 hFile의 경우 이 매개 변수를 사용하는 경우 파일 또는 장치에서 읽기 시작 바이트 오프셋을 지정해야합니다. 이 오프셋은 OVERLAPPED 구조체의 Offset 및 OffsetHigh 멤버를 설정하여 지정됩니다. 바이트 오프셋을 지원하지 않는 hFile의 경우 Offset 및 OffsetHigh는 무시됩니다.

lpOverlapped와 FILE_FLAG_OVERLAPPED의 다른 조합에 대한 자세한 내용은 Remarks section 과 [동기화 및 파일 위치 섹션](https://docs.microsoft.com/)을 참조하십시오.



**Return Value**

함수가 성공하면 반환 값은 0이 아닙니다 (TRUE). 함수가 실패하거나 비동기 적으로 완료되면 리턴 값은 0입니다 (FALSE). 확장 된 오류 정보를 얻으려면 GetLastError 함수를 호출하십시오.

```
Note  GetLastError 코드 ERROR_IO_PENDING은 실패가 아닙니다. 읽기 조작이 비동기 적으로 완료 보류 중으로 지정됩니다. 자세한 내용은 Remarks 를 참조하십시오.
```



**Remarks**

ReadFile 함수는 다음 조건 중 하나가 발생할 때 반환(return) 합니다.

- 요청 된 바이트 수를 읽었을 때.
- 쓰기 작업은 파이프의 쓰기 끝에서 완료 됩니다.
- 비동기 핸들이 사용 중 이며 읽기가 비동기 적으로 발생합니다.
- 오류가 발생합니다.

너무 많은 미해결 비동기 I/O 요청이 있을 때마다 ReadFile 함수가 ERROR_INVALID_USER_BUFFER 또는 ERROR_NOT_ENOUGH_MEMORY와 함께 실패 할 수 있습니다.

보류중인 모든 비동기 I/O 조작을 취소하려면 다음 중 하나를 사용하십시오.

- [CancelIo](https://msdn.microsoft.com/b28162dc-0da8-41c6-9901-29381d2d72c4)— 이 함수는 호출 된 스레드가 지정된 파일 핸들에 대해 실행 한 작업 만 취소합니다.
- [CancelIoEx](https://msdn.microsoft.com/a2ce13b8-7da6-4848-848d-901d9667c2e3)—이 함수는 지정된 파일 핸들에 대해 스레드가 발행 한 모든 작업을 취소합니다.

보류중인 동기 I/O 조작을 취소하려면 [CancelSynchronousIo](https://msdn.microsoft.com/f362c8b2-2193-443e-bb69-78f8b4147117)를 사용하십시오.

취소 된 I/O 작업은 ERROR_OPERATION_ABORTED 오류로 완료됩니다.

ReadFile 함수는 ERROR_NOT_ENOUGH_QUOTA와 함께 실패 할 수 있습니다. 이는 호출 프로세스의 버퍼가 페이지 잠금 될 수 없음을 의미합니다. 자세한 내용은 [SetProcessWorkingSetSize](https://msdn.microsoft.com/8bc0053c-f687-43b5-a435-df1e813a5204)를 참조하십시오.

파일의 일부가 다른 프로세스에 의해 잠겨 있고 읽기 작업이 잠긴 부분과 겹치는 경우이 함수는 실패합니다.

읽기 조작이 버퍼를 사용하는 동안 입력 버퍼에 액세스하면 해당 버퍼로 읽은 데이터가 손상 될 수 있습니다. 응용 프로그램은 읽기 조작이 완료 될 때까지 읽기 조작이 사용중인 입력  버퍼의 읽기, 쓰기, 재 할당 또는 해제를해서는 안됩니다. 비동기 파일 핸들을 사용할 때 특히 문제가 될 수 있습니다. 동기 대 파일 핸들에 관한 추가 정보는 동기화 및 파일 위치 섹션과 CreateFile 참조 항목에서 찾을 수 있습니다.

콘솔 입력에 대한 핸들과 함께 ReadFile을 사용하여 문자를 콘솔 입력 버퍼에서 읽을 수 있습니다. 콘솔 모드는 ReadFile 함수의 정확한 동작을 결정합니다. 기본적으로 콘솔 모드는 ENABLE_LINE_INPUT이며, 이는 ReadFile이 캐리지 리턴에 도달 할 때까지 읽어야 함을 나타냅니다. Ctrl + C를 누르면 호출은 성공하지만 GetLastError는 ERROR_OPERATION_ABORTED를 반환합니다. 자세한 내용은 CreateFile을 참조하십시오.

통신 장치에서 읽을 때 ReadFile의 동작은 SetCommTimeouts 및 GetCommTimeouts 함수를 사용하여 설정되고 검색된 현재 통신 시간 제한에 의해 결정됩니다. 시간 초과 값을 설정하지 않으면 예기치 않은 결과가 발생할 수 있습니다. 통신 시간 초과에 대한 자세한 내용은 COMMTIMEOUT을 참조하십시오.

ReadFile이 버퍼가 너무 작은 메일 슬롯에서 읽으려고하면 함수는 FALSE를 반환하고 GetLastError는 ERROR_INSUFFICIENT_BUFFER를 반환합니다.

FILE_FLAG_NO_BUFFERING 플래그를 사용하여 CreateFile로 열린 파일을 성공적으로 사용하기위한 엄격한 요구 사항이 있습니다. 자세한 내용은 [파일 버퍼링](https://msdn.microsoft.com/ae1e5d0f-9b55-4aae-8402-b9c8e33d9363)을 참조하십시오.

FILE_FLAG_OVERLAPPED로 hFile을 열면 다음 조건이 적용됩니다.

- lpOverlapped 매개 변수는 유효하고 고유 한 OVERLAPPED 구조를 가리켜 야합니다. 그렇지 않으면 함수가 읽기 조작이 완료되었다고 잘못보고 할 수 있습니다.
- lpNumberOfBytesRead 매개 변수는 NULL로 설정해야합니다. GetOverlappedResult 함수를 사용하여 읽은 실제 바이트 수를 가져옵니다. hFile 매개 변수가 I/O 완료 포트와 연결되어 있으면 GetQueuedCompletionStatus 함수를 호출하여 읽은 바이트 수를 가져올 수도 있습니다.

나머지 내용은 [다음](https://docs.microsoft.com/ko-kr/windows/desktop/api/fileapi/nf-fileapi-readfile) 참조 



#### <span style="color:magenta">CloseHandle</span>

- 위에 설명 되어 있음







## <span style="color:blue">MirrorWriteFile</span>

함수 설명: **CallBack**, 파일에 데이터를 기록합니다. 파일이 열려 있지 않으면 열고 기록한 다음 파일을 닫는다.
제약 조건:
함수 원형: MirrorWriteFile( LPCWSTR                    FileName, 
                                                LPCVOID                     Buffer,
                                                DWORD                       NumberOfBytesToWrite,
                                                LPDWORD                  NumberOfBytesWritten,
                                                LONGLONG               Offset,
                                                PDOKAN_FILE_INFO DokanFileInfo) 
리턴     값: 정상 수행시 0, 오류 발생시 오류 코드

| param                | in/out | 설명                                                         |
| -------------------- | :----: | ------------------------------------------------------------ |
| FileName             |   in   | 기록 할 파일명                                               |
| Buffer               |   in   | 기록 할 Data 가 저장되어 있는 Buffer                         |
| NumberOfBytesToWrite |   in   | 기록 할 Bytes 수                                             |
| NumberOfBytesWritten |  out   | 기록 한  Bytes 수                                            |
| Offset               |   in   | 기록 할 위치                                                 |
| DokanFileInfo        |   in   | 이미 파일이 열려 있으면 Handle 을 전달하는 도칸 구조체의 포인터 |



### <span style="color:green">사용된 주요 함수</span>

#### <span style="color:magenta">WriteFile</span>

지정된 파일 또는 입출력 (I/O) 장치에 데이터를 기록합니다.

이 기능은 동기 및 비동기 작동을 위해 설계되었습니다. 비동기 작업만을 위해 설계된 유사한 함수에 대해서는 WriteFileEx를 참조하십시오.

```c++
BOOL WriteFile(
  HANDLE       hFile,
  LPCVOID      lpBuffer,
  DWORD        nNumberOfBytesToWrite,
  LPDWORD      lpNumberOfBytesWritten,
  LPOVERLAPPED lpOverlapped
);
```

**hFile**

파일 또는 I/O 장치 (예 : 파일, 파일 스트림, 물리 디스크, 볼륨, 콘솔 버퍼, 테이프 드라이브, 소켓, 통신 자원, 메일 슬롯 또는 파이프)에 대한 핸들.

hFile 매개 변수는 쓰기 액세스로 작성되어야합니다. 자세한 내용은 일반 액세스 권한 및 파일 보안 및 액세스 권한을 참조하십시오.

비동기 쓰기 작업의 경우 hFile은 FILE_FLAG_OVERLAPPED 플래그를 사용하여 CreateFile 함수로 열리거나 소켓 또는 accept 함수가 반환 한 소켓 핸들이 될 수 있습니다.



**lpBuffer**

파일 또는 장치에 기록 될 데이터가 들어있는 버퍼에 대한 포인터.

이 버퍼는 쓰기 작업이 지속되는 동안 유효해야합니다. 호출 조작자는, 기입 해 조작이 완료 할 때까지,이 버퍼를 사용하지 말아주세요.



**nNumberOfBytesToWrite**

파일 또는 장치에 기록 할 바이트 수.

0 값은 널 쓰기 조작을 지정합니다. Null 쓰기 작업의 동작은 기본 파일 시스템 또는 통신 기술에 따라 다릅니다.

<span style="color:navy">**Windows Server 2003 and Windows XP:**</span>  네트워크를 통한 파이프 쓰기 작업은 쓰기 당 크기가 제한됩니다. 크기는 플랫폼마다 다릅니다. x86 플랫폼의 경우 63.97MB입니다. x64 플랫폼의 경우 31.97MB입니다. Itanium의 경우 63.95MB입니다. 파이프에 대한 자세한 내용은주의 섹션을 참조하십시오.



**lpNumberOfBytesWritten**

**동기식** hFile 매개 변수를 사용할 때 **기록 된 바이트 수**를 수신하는 변수에 대한 포인터. WriteFile은 작업 또는 오류 검사를 수행하기 전에이 값을 0으로 설정합니다. 잠재적으로 잘못된 결과를 피하기 위해 비동기 작업 인 경우이 매개 변수에 NULL을 사용하십시오.

이 매개 변수는 lpOverlapped 매개 변수가 NULL이 아닌 경우에만 NULL이 될 수 있습니다.



**lpOverlapped**

hFile 매개 변수가 FILE_FLAG_OVERLAPPED로 열리면 OVERLAPPED 구조체에 대한 포인터가 필요합니다. 그렇지 않으면이 매개 변수는 NULL이 될 수 있습니다.

바이트 오프셋을 지원하는 hFile의 경우 이 매개 변수를 사용하는 경우 파일이나 장치에 쓰기를 시작할 바이트 오프셋을 지정해야합니다. 이 오프셋은 OVERLAPPED 구조체의 Offset 및 OffsetHigh 멤버를 설정하여 지정됩니다. 바이트 오프셋을 지원하지 않는 hFile의 경우 Offset 및 OffsetHigh는 무시됩니다.

<span style="color:navy">파일의 끝에 쓰려면 OVERLAPPED 구조체의 Offset 및 OffsetHigh 멤버를 모두 0xFFFFFFFF로 지정하십시오. 이는 이전에 CreateFile 함수를 호출하여 FILE_APPEND_DATA 액세스를 사용하여 hFile을 여는 것과 기능적으로 동일합니다.</span>

lpOverlapped와 FILE_FLAG_OVERLAPPED의 다른 조합에 대한 자세한 내용은 Remarks section 과 [동기화 및 파일 위치 섹션](https://docs.microsoft.com/)을 참조하십시오.



**Return Value**

함수가 성공하면 반환 값은 0 이 아닙니다 (TRUE). 함수가 실패하거나 비동기 적으로 완료되면 리턴 값은 0 입니다 (FALSE). 확장 된 오류 정보를 얻으려면 GetLastError 함수를 호출하십시오.

```
Note  GetLastError 코드 ERROR_IO_PENDING은 실패가 아닙니다. 쓰기 조작이 비동기 적으로 완료 보류 중으로 지정됩니다. 자세한 내용은주의를 참조하십시오.
```



**Remarks**

WriteFile 함수는 다음 조건 중 하나가 발생할 때 반환(return)합니다.

- 요청 된 바이트 수가 기록 되었을 때.
- 읽기 작업은 파이프의 읽기 끝에있는 버퍼 공간을 해제합니다 (쓰기가 차단 된 경우). 자세한 내용은 파이프 섹션을 참조하십시오.
- 비동기 핸들이 사용되고 있고 쓰기가 비동기 적으로 발생 할 때.
- 오류가 발생 할 때.

WriteFile 함수는 너무 많은 미해결 비동기 I/O 요청이있을 때마다 ERROR_INVALID_USER_BUFFER 또는 ERROR_NOT_ENOUGH_MEMORY(8) 와 함께 실패 할 수 있습니다.

보류중인 모든 비동기 I/O 작업을 취소하려면 다음 중 하나를 사용하십시오.

- [CancelIo](https://msdn.microsoft.com/b28162dc-0da8-41c6-9901-29381d2d72c4)— 이 함수는 호출 된 스레드가 지정된 파일 핸들에 대해 실행 한 작업 만 취소합니다.
- [CancelIoEx](https://msdn.microsoft.com/a2ce13b8-7da6-4848-848d-901d9667c2e3)— 이 함수는 지정된 파일 핸들에 대해 스레드가 발행 한 모든 작업을 취소합니다.

보류중인 동기 I/O 작업을 취소하려면 [CancelSynchronousIo](https://msdn.microsoft.com/f362c8b2-2193-443e-bb69-78f8b4147117) 함수를 사용하십시오.

취소 된 I/O 작업은 ERROR_OPERATION_ABORTED 오류로 완료됩니다.

WriteFile 함수는 ERROR_NOT_ENOUGH_QUOTA와 함께 실패 할 수 있습니다. 이는 호출 프로세스의 버퍼가 페이지 잠금 될 수 없음을 의미합니다. 자세한 내용은 [SetProcessWorkingSetSize](https://msdn.microsoft.com/8bc0053c-f687-43b5-a435-df1e813a5204)를 참조하십시오.

파일의 일부가 다른 프로세스에 의해 잠겨 있고 쓰기 작업이 잠긴 부분과 겹치면 WriteFile이 실패합니다.

파일에 쓸 때 마지막 쓰기 시간은 쓰기에 사용 된 모든 핸들이 닫힐 때까지 완전히 업데이트되지 않습니다. 따라서 정확한 최종 쓰기 시간을 보장하려면 파일에 기록한 직후에 파일 핸들을 닫으십시오.

쓰기 조작이 버퍼를 사용하는 동안 출력 버퍼에 액세스하면 해당 버퍼에서 기록 된 데이터가 손상 될 수 있습니다. 응용 프로그램은 쓰기 작업이 완료 될 때까지 쓰기 작업이 사용중인 출력 버퍼를 쓰거나, 재 할당하거나, 해제 할 수 없습니다. 비동기 파일 핸들을 사용할 때 특히 문제가 될 수 있습니다. 동기 대 비동기 파일 핸들에 대한 추가 정보는 [동기화 및 파일 위치](https://docs.microsoft.com/) 섹션과 [동기 및 비동기 I/O](https://msdn.microsoft.com/ade51d98-cc9d-4b33-9c52-559a9cb14707)에서 볼 수 있습니다.

타임 스탬프는 원격 파일에 대해 올바르게 업데이트되지 않을 수 있습니다. 일관된 결과를 보장하려면 버퍼되지 않은 I/O를 사용하십시오.

시스템은 null 쓰기 작업을 지정하는 것으로 쓰기 위해 0 바이트를 해석하고 WriteFile은 파일을 자르거나 확장하지 않습니다. 파일을 자르거나 확장하려면 [SetEndOfFile](https://msdn.microsoft.com/2a579609-144a-4b77-8605-87aecf1f0957) 함수를 사용하십시오.

콘솔 출력 핸들을 사용하여 WriteFile을 사용하여 문자를 화면 버퍼에 쓸 수 있습니다. 기능의 정확한 작동은 콘솔 모드에 의해 결정됩니다. 데이터는 현재 커서 위치에 기록됩니다. 커서 위치는 쓰기 작업 후에 업데이트됩니다. 콘솔 핸들에 대한 자세한 내용은 [CreateFile](https://msdn.microsoft.com/80a96083-4de9-4422-9705-b8ad2b6cbd1b)을 참조하십시오.

통신 장치에 쓸 때 WriteFile의 동작은 [SetCommTimeouts](https://msdn.microsoft.com/71aa6ab3-d56c-43bc-9932-5b4e61fc4b30) 및 [GetCommTimeouts](https://msdn.microsoft.com/a5375b2e-0992-4e47-a20f-8a793addeef6) 함수를 사용하여 설정되고 검색된 현재 통신 시간 제한에 의해 결정됩니다. 시간 초과 값을 설정하지 않으면 예기치 않은 결과가 발생할 수 있습니다. 통신 시간 초과에 대한 자세한 내용은 [COMMTIMEOUT](https://msdn.microsoft.com/259aa110-b2c3-4583-a3f9-805a42025a81)을 참조하십시오.

단일 섹터 쓰기가 원자 단위 임에도 불구하고 다중 섹터 쓰기는 트랜잭션을 사용하지 않는 한 원자적인 것으로 보장되지 않습니다. 즉, 생성 된 핸들은 트랜잭션 처리 된 핸들입니다 (예 : [CreateFileTransacted](https://msdn.microsoft.com/0cbc081d-8787-409b-84bc-a6a28d8f83a0)를 사용하여 생성 된 핸들). 캐시 된 다중 섹터 쓰기는 디스크에 즉시 기록되지 않을 수도 있습니다. 따라서 [CreateFile](https://msdn.microsoft.com/80a96083-4de9-4422-9705-b8ad2b6cbd1b)에서 FILE_FLAG_WRITE_THROUGH를 지정하면 잠재적 인 캐싱 지연없이 전체 멀티 섹터 쓰기가 디스크에 기록됩니다.

마운트 된 파일 시스템이있는 볼륨에 직접 작성하는 경우, 먼저 볼륨에 대한 독점적 액세스를 얻어야합니다. 그렇지 않으면 응용 프로그램의 쓰기가 파일 시스템에서 오는 다른 변경과 충돌하고 볼륨의 내용이 일관성없는 상태로 남을 수 있기 때문에 데이터 손상 또는 시스템 불안정을 초래할 위험이 있습니다. 이러한 문제를 방지하기 위해 Windows Vista 이상에서는 다음과 같이 변경되었습니다.

- 볼륨에 마운트 된 파일 시스템이 없거나 다음 조건 중 하나에 해당하는 경우 볼륨 핸들에 대한 쓰기가 성공합니다.
  - 기록 될 섹터는 부트 섹터입니다.
  - 파일 시스템 공간 외부에 존재하도록 작성된 섹터.
  - [FSCTL_LOCK_VOLUME](https://msdn.microsoft.com/b59b5c5e-6719-47a8-8810-14b60204e5ed) 또는 [FSCTL_DISMOUNT_VOLUME](https://msdn.microsoft.com/8828760c-9635-4c69-9867-c2f5314841e6)을 사용하여 볼륨을 명시 적으로 잠 그거나 마운트 해제했습니다.
  - 볼륨에 실제 파일 시스템이 없습니다. 즉, RAW 파일 시스템이 마운트되어 있습니다.
- 다음 조건 중 하나에 해당하면 디스크 핸들에 쓰기가 성공합니다.
  - 기록 될 섹터는 볼륨의 범위 내에 들지 않습니다.
  - 기록 할 섹터가 마운트 된 볼륨에 속하지만 [FSCTL_LOCK_VOLUME](https://msdn.microsoft.com/b59b5c5e-6719-47a8-8810-14b60204e5ed) 또는 [FSCTL_DISMOUNT_VOLUME](https://msdn.microsoft.com/8828760c-9635-4c69-9867-c2f5314841e6)을 사용하여 볼륨을 명시 적으로 잠 그거나 마운트 해제했습니다.
  - 기록 될 섹터가 RAW 이외의 파일 시스템이 마운트되지 않은 볼륨에 속하게됩니다.

FILE_FLAG_NO_BUFFERING을 사용하여 CreateFile로 연 파일을 성공적으로 작업하기위한 엄격한 요구 사항이 있습니다. 자세한 내용은 파일 버퍼링을 참조하십시오.

FILE_FLAG_OVERLAPPED로 hFile을 열면 다음 조건이 적용됩니다.

- lpOverlapped 매개 변수는 유효하고 고유 한 [OVERLAPPED](https://msdn.microsoft.com/5037f6b9-e316-483b-a8e2-b58d2587ebd9) 구조를 가리켜 야합니다. 그렇지 않으면 함수가 쓰기 조작이 완료되었다고 잘못보고 할 수 있습니다.
- lpNumberOfBytesWritten 매개 변수는 NULL로 설정해야합니다. 쓰여진 바이트 수를 얻으려면 [GetOverlappedResult](https://msdn.microsoft.com/7f999959-9b22-4491-ae2b-a2674d821110) 함수를 사용하십시오. hFile 매개 변수가 I/O 완료 포트와 연결되어 있으면 [GetQueuedCompletionStatus](https://msdn.microsoft.com/8121a38b-0fe1-43b8-aed6-4b85af1feba9) 함수를 호출하여 작성된 바이트 수를 얻을 수도 있습니다.



**기타 내용은** [여기](https://docs.microsoft.com/ko-kr/windows/desktop/api/fileapi/nf-fileapi-writefile) 참조





#### <span style="color:magenta">CreateFile</span>

- 기 설명 되었음



#### <span style="color:magenta">GetFileSize</span>

지정된 파일의 크기 (바이트)를 가져옵니다.

[GetFileSizeEx](https://msdn.microsoft.com/782457bc-8f37-4eec-8ff3-b148fd0a7345)를 사용하는 것이 좋습니다.

```c++
DWORD GetFileSize(
  HANDLE  hFile,
  LPDWORD lpFileSizeHigh
);
```

**hFile**

파일 핸들.

**lpFileSizeHigh**

파일 크기의 상위 더블 워드가 반환되는 변수에 대한 포인터입니다. 이 매개 변수는 응용 프로그램에서 상위 더블 워드가 필요하지 않은 경우 NULL 일 수 있습니다.



**Return Value**

함수가 성공하면 반환 값은 파일 크기의 **하위 워드**입니다. **lpFileSizeHigh**가 NULL이 아닌 경우 함수는 파일 크기의 상위 더블 워드를 해당 매개 변수가 가리키는 변수에 넣습니다.

함수가 실패하고 lpFileSizeHigh가 NULL이면 반환 값은 INVALID_FILE_SIZE입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오. lpFileSizeHigh가 NULL이면 대용량 파일에 대해 반환 된 결과가 모호하며 파일의 실제 크기를 결정할 수 없습니다. 대신 [GetFileSizeEx](https://msdn.microsoft.com/782457bc-8f37-4eec-8ff3-b148fd0a7345)를 사용하는 것이 좋습니다.

함수가 실패하고 lpFileSizeHigh가 NULL이 아닌 경우 반환 값은 INVALID_FILE_SIZE이고 GetLastError는 NO_ERROR가 아닌 값을 반환합니다.



#### <span style="color:magenta">CloseHandle</span>

- 기 설명 되었음



#### <span style="color:magenta">SetFilePointerEx</span>

- 기 설명 되었음









## <span style="color:blue">MirrorFlushFileBuffers</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorGetFileInformation</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorFindFiles</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorDeleteFile</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorDeleteDirectory</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorMoveFile</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorLockFile</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorSetEndOfFile</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorSetAllocationSize</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorSetFileAttributes</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorSetFileTime</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorUnlockFile</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack





## <span style="color:blue">MirrorGetFileSecurity</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack





## <span style="color:blue">MirrorSetFileSecurity</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorGetVolumeInformation</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorFindStreams</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorMounted</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">MirrorUnmounted</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:CallBack



## <span style="color:blue">CtrlHandler</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:



## <span style="color:blue">ShowUsage</span>

함수 설명:
제약 조건:
함수 원형:
리턴     값:



## <span style="color:blue">wmain</span>

함수 설명: 프로그램 시작점, 유니코드 프로그래밍모델을 따르는 코드를 작성 하였기 때문에  wmain을 사용 하였다.

제약 조건:
함수 원형:
리턴     값: 



---

---

# Dokan 주요 구조체

```c++
/**
 * \struct DOKAN_OPTIONS
 * \brief Dokan 장치 옵션은 Dokan 장치 동작을 설명 하는데 사용 됩니다.
 * \see DokanMain
 */
typedef struct _DOKAN_OPTIONS {
  /** 점없이 요청 된 Dokan 기능의 버전 (버전 '123'은 Dokan 버전 1.2.3과 같습니다). */
  USHORT Version;
  /** Dokan 라이브러리에서 내부적으로 사용할 스레드 수입니다. 더 많은 스레드가 동시에 많은 이벤트를 처리합니다. */
  USHORT ThreadCount;
  /** 마운트 옵션. See \ref DOKAN_OPTION. */
  ULONG Options;
  /** 파일 시스템은 여기에 무었이라도 저장 할 수 있습니다. (사용자가 활용 가능한지 확인 필요) */
  ULONG64 GlobalContext;
  /** 마운트 포인트. 다음과 같은 드라이버 문자열 "M:\" or a folder path "C:\mount\dokan" on a NTFS partition. */
  LPCWSTR MountPoint;
  /**
  * 네트워크 리디렉터의 UNC 이름
  * \see <a href="https://msdn.microsoft.com/en-us/library/windows/hardware/ff556761(v=vs.85).aspx">Support for UNC Naming</a>
  */
  LPCWSTR UNCName;
  /** Dokan이 완료 대기 이벤트를 포기하기 전에 각 요청의 최대 시간 초과 (밀리 초). */
  ULONG Timeout;
  /** Allocation Unit 볼륨의 크기입니다. 이것은 파일 크기에 영향을 미칩니다. */
  ULONG AllocationUnitSize;
  /** 섹터 볼륨의 크기. 이것은 파일 크기에 영향을 미칩니다. */
  ULONG SectorSize;
} DOKAN_OPTIONS, *PDOKAN_OPTIONS;
```



```c++
/**
 * \struct DOKAN_FILE_INFO
 * \brief Dokan은 현재 작업에 대한 정보를 제공합니다.
 */
typedef struct _DOKAN_FILE_INFO {
  /**
   * 오퍼레이션간에 정보를 전달하는 데 사용할 수있는 컨텍스트입니다.
   * 컨텍스트는 구현이 이벤트의 요청 컨텍스트를 이해하는 데 도움이되는 \c HANDLE, struct, int, 
   * internal reference와 같은 유형을 전달할 수 있습니다.
   */
  ULONG64 Context;
  /** Reserved. Dokan 라이브러리에서 내부적으로 사용됩니다. 수정하지 마십시오. */
  ULONG64 DokanContext;
  /** DokanMain에 전달 된 DOKAN_OPTIONS에 대한 포인터. */
  PDOKAN_OPTIONS DokanOptions;
  /**
   * 원래 지정된 I/O 작업을 요청한 스레드의 프로세스 ID입니다.
   */
  ULONG ProcessId;
  /**
   * 디렉토리 파일 요청
   * 파일이 폴더 인 것으로 보이는 경우 \ref DOKAN_OPERATIONS.ZwCreateFile에 설정해야합니다
   */
  UCHAR IsDirectory;
  /** DOKAN_OPERATIONS 동안 파일을 삭제해야한다면 플래그를 지정하십시오. 정리 이벤트. */
  UCHAR DeleteOnClose;
  /** 읽기 또는 쓰기가 페이징 IO 임 */
  UCHAR PagingIo;
  /** 읽기 또는 쓰기가 동기 IO입니다. */
  UCHAR SynchronousIo;
  /** 캐시없이 데이터 소스에서 직접 읽기 또는 쓰기 */
  UCHAR Nocache;
  /**  \c TRUE이면 Offset 매개 변수를 사용하는 대신 파일의 현재 끝까지 씁니다. */
  UCHAR WriteToEndOfFile;
} DOKAN_FILE_INFO, *PDOKAN_FILE_INFO;
```







---

---

# Dokan 함수 Quick Guide

| 함수명                  | 설명 |
| ----------------------- | ---- |
| [DokanMain](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga099fb73df80893f9da337a2dd7a6634e) | 새로운 Dokan 볼륨을 Mount 한다. |
| [DokanUnmount](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#gae9ed3293f6830c8ace6419082434128a) | 드라이버 문자에 해당하는 Dokan 장치의 마운트를 해제 한다. |
| [DokanRemoveMountPoint](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga5b47eeb4d113e9981b11e4c22890027f) | 마운트 지점에서 Dokan 장치를 마운트 해제 |
| [DokanRemoveMountPointEx](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga53831fe835a36a210b380289f592d51c) | 마운트 지점에서 Dokan 장치를 마운트 해제 |
| [DokanIsNameInExpression](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga2e8585b8730ee74c0cd0cadf3e8cb01a) | Name이 Expression과 일치하는지 확인합니다. |
| [DokanVersion](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#gab90d8e0673436901756384a2ebe8ee18) | Dokan의 버전을 얻으십시오. 반환 된 ULONG은 점이없는 버전 번호입니다. |
| [DokanDriverVersion](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga51cbe6e697d53ecb91be1f3780d2bcca) | Dokan 드라이버의 버전을 확인하십시오. 반환 된 ULONG은 점이없는 버전 번호입니다. |
| [DokanResetTimeout](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga1d5b0bf526fbf143a726fe1816fe7f15) | 드라이버에서 현재 IO 작업의 시간 초과를 연장합니다. |
| [DokanOpenRequestorToken](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga09859474c6ffee4fdc318e47e30c1a03) | 액세스 토큰에 대한 핸들을 가져옵니다. |
| [DokanGetMountPointList](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#gab46c19383ac6905bbde3261c4c55d10f) | 도칸 (Dokan) 마운트 포인트를 취득. |
| [DokanMapKernelToUserCreateFileFlags](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga58cc5a921a84f54042f2ac04b27bbc75) | DOKAN_OPERATIONS :: ZwCreateFile 매개 변수를 CreateFile 매개 변수로 변환합니다. |
| [DokanNtStatusFromWin32](https://dokan-dev.github.io/dokany-doc/html/group___dokan.html#ga1bcba8145ee5205cd41fa5eebdffaa82) | WIN32 오류를 NTSTATUS로 변환하십시오. |



### **DokanMapKernelToUserCreateFileFlags** 

```c++
void DokanMapKernelToUserCreateFileFlags(	ACCESS_MASK 	DesiredAccess,
											ULONG 			FileAttributes,
                                            ULONG 			CreateOptions,
                                            ULONG 			CreateDisposition,
                                            ACCESS_MASK * 	outDesiredAccess,
                                            DWORD * 		outFileAttributesAndFlags,
                                            DWORD * 		outCreationDisposition 
)	
```

DOKAN_OPERATIONS::ZwCreateFile 매개 변수를 CreateFile 매개 변수로 변환합니다.

Dokan 커널은 DesiredAccess를 IRP_MJ_CREATE에 직접 전달합니다. 이 DesiredAccess는 일반 권한 (사용자 CreateFile 요청)에서 표준 권한으로 변환되었으며 여기에서 다시 변환됩니다.  <https://msdn.microsoft.com/windows/hardware/drivers/ifs/access-mask>

| Parameters | Description |
| ------------------------- | ------------------------------------------------------------ |
| DesiredAccess             | DesiredAccess from [DOKAN_OPERATIONS::ZwCreateFile](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183). |
| FileAttributes            | FileAttributes from [DOKAN_OPERATIONS::ZwCreateFile](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183). |
| CreateOptions             | CreateOptions from [DOKAN_OPERATIONS::ZwCreateFile](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183). |
| CreateDisposition         | CreateDisposition from [DOKAN_OPERATIONS::ZwCreateFile](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183). |
| outDesiredAccess          | New [CreateFile](https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx) dwDesiredAccess. |
| outFileAttributesAndFlags | New [CreateFile](https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx) dwFlagsAndAttributes. |
| outCreationDisposition    | New [CreateFile](https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx) dwCreationDisposition. |

See also

[CreateFile function (MSDN)](https://msdn.microsoft.com/en-us/library/windows/desktop/aa363858(v=vs.85).aspx)