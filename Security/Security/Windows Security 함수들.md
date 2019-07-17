# Windows Security 함수들

본 문서는 Windows 에서 사용 할 수 있는 보안 관련 함수들을 기록 한다. 본 문서는 MSDN, DevPia 등의 문서를 기준으로 작성 되었습니다.



## 1. 함수목록

| 함수명                       | 기능                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| GetFileSecurityA             | GetFileSecurity 함수는 파일이나 디렉터리의 보안에 대해 지정된 정보를 가져옵니다. 얻은 정보는 호출자의 액세스 권한과 특권에 의해 제한됩니다.<br/>GetNamedSecurityInfo 함수는 GetFileSecurity와 비슷한 기능을 파일 및 다른 유형의 개체에 제공합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-getfilesecuritya |
| GetSecurityDescriptorDacl    | GetSecurityDescriptorDacl 함수는 지정된 보안 설명자의 DACL (임의 액세스 제어 목록)에 대한 포인터를 검색합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-getsecuritydescriptordacl |
| AllocateAndInitializeSid     | AllocateAndInitializeSid 함수는 최대 8 개의 하위 인증 기관을 사용하여 SID (보안 식별자)를 할당하고 초기화합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-allocateandinitializesid |
| SetEntriesInAcl              | SetEntriesInAcl 함수는 새로운 액세스 제어 또는 감사 제어 정보를 기존 ACL 구조에 병합하여 새로운 ACL (Access Control List)을 만듭니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/aclapi/nf-aclapi-setentriesinacla |
| LocalAlloc                   | 힙에서 지정된 바이트 수를 할당합니다. <br/>참고 로컬 함수는 많은 오버 헤드를 가지며 다른 메모리 관리 함수보다 적은 기능을 제공합니다. 새로운 응용 프로그램은 설명서에 지역 함수를 사용해야한다고 명시하지 않는 한 힙 함수를 사용해야합니다. 자세한 내용은 전역 및 로컬 함수를 참조하십시오.<br/>https://docs.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-localalloc |
| InitializeSecurityDescriptor | InitializeSecurityDescriptor 함수는 새 보안 설명자를 초기화합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-initializesecuritydescriptor <br/>https://crowback.tistory.com/220 <br/>http://www.devpia.com/MAEUL/Contents/Detail.aspx?BoardID=51&MAEULNo=20&no=7537&ref=7537 <br/> 이 링크들 잘 보기 |
| SetSecurityDescriptorDacl    | SetSecurityDescriptorDacl 함수는 DACL (임의 액세스 제어 목록)에 정보를 설정합니다. DACL이 보안 설명자에 이미 있으면 DACL이 바뀝니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-setsecuritydescriptordacl |
| FreeSid                      | FreeSid 함수는 AllocateAndInitializeSid 함수를 사용하여 이전에 할당 된 보안 식별자 (SID)를 해제합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-freesid |
| LocalFree                    | 지정된 로컬 메모리 개체를 해제하고 해당 핸들을 무효화합니다. <br/>https://docs.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-localfree |
| NetUserEnum                  | NetUserEnum 함수는 서버의 모든 사용자 계정에 대한 정보를 검색합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/lmaccess/nf-lmaccess-netuserenum |
| NetGroupEnum                 | NetGroupEnum 함수는 보안 계정 관리자 (SAM) 데이터베이스 또는 도메인 컨트롤러의 경우 Active Directory 인 보안 데이터베이스의 각 글로벌 그룹에 대한 정보를 검색합니다.<br/>NetQueryDisplayInformation 함수는 글로벌 그룹을 열거하기위한 효율적인 메커니즘을 제공합니다. 가능한 경우 NetGroupEnum 함수 대신 NetQueryDisplayInformation을 사용하는 것이 좋습니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/lmaccess/nf-lmaccess-netgroupenum |
| NetQueryDisplayInformation   | NetQueryDisplayInformation 함수는 사용자 계정, 컴퓨터 또는 그룹 계정 정보를 반환합니다. 이 함수를 호출하여 사용자 인터페이스에 표시 할 계정 정보를 빠르게 열거합니다.<br/>https://docs.microsoft.com/ko-kr/windows/win32/api/lmaccess/nf-lmaccess-netquerydisplayinformation |
| GetUserNameW                 | 현재의 thread에 관련 지을 수 있었던 유저의 이름을 취득합니다.<br/>GetUserNameEx 함수를 사용하여 지정된 형식의 사용자 이름을 검색하십시오. 추가 정보는 IADsADSystemInfo 인터페이스에서 제공합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-getusernamew |
| NetApiBufferFree             | NetApiBufferFree 함수는 NetApiBufferAllocate 함수가 할당하는 메모리를 해제합니다. 응용 프로그램은 NetApiBufferFree를 호출하여 다른 네트워크 관리 기능에서 정보를 반환하기 위해 내부적으로 사용하는 메모리를 확보해야합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/lmapibuf/nf-lmapibuf-netapibufferfree |
| GetNamedSecurityInfo         | GetNamedSecurityInfo 함수는 name으로 지정된 개체에 대한 보안 설명 자의 복사본을 검색합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/aclapi/nf-aclapi-getnamedsecurityinfoa |
| AccessCheck                  | AccessCheck 함수는 보안 설명자가 액세스 토큰에 의해 식별 된 클라이언트에 대해 지정된 액세스 권한 집합을 부여하는지 여부를 결정합니다. 일반적으로 서버 응용 프로그램은이 함수를 사용하여 개인 개체에 대한 액세스를 확인합니다.<br/>https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-accesscheck |
| AddAce                       | AddAce 함수는 하나 이상의 ACE (액세스 제어 항목)를 지정된 액세스 제어 목록 (ACL)에 추가합니다.<br/> https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-addace |
|                              |                                                              |
|                              |                                                              |

**이 목록은 문서 작성중 언제든지 추가/변경 될 수 있습니다.**



## 2. 함수 설명 및 예제

이 장 에서는 Security 함수들의 상세 설명 및 사용 예제(가능 한 경우)를 기록 합니다.



### 2.1 GetFileSecurityA

GetFileSecurity 함수는 파일이나 디렉터리의 보안에 대해 지정된 정보를 가져옵니다. 얻은 정보는 호출자의 액세스 권한과 특권에 의해 제한됩니다.

**GetNamedSecurityInfo** 함수는 GetFileSecurity와 비슷한 기능을 파일 및 다른 유형의 개체에 제공합니다.



#### Syntax

```c++
BOOL GetFileSecurityA(
  LPCSTR               lpFileName,
  SECURITY_INFORMATION RequestedInformation,
  PSECURITY_DESCRIPTOR pSecurityDescriptor,
  DWORD                nLength,
  LPDWORD              lpnLengthNeeded
);
```



#### Parameters

`lpFileName` 보안 정보가 검색되는 파일 또는 디렉토리를 지정하는 널 (null)로 끝나는 문자열에 대한 포인터.

`RequestedInformation` 요청되는 보안 정보를 식별하는 SECURITY_INFORMATION 값.

`pSecurityDescriptor` lpFileName 매개 변수로 지정된 개체의 보안 설명 자의 복사본을 받는 버퍼에 대한 포인터입니다. 호출 프로세스에는 개체 보안 상태의 지정된 부분을 볼 수있는 권한이 있어야합니다. SECURITY_DESCRIPTOR 구조체는 자체 상대적 보안 설명자 형식으로 반환됩니다.

`nLength` pSecurityDescriptor 매개 변수가 가리키는 버퍼의 크기 (바이트)를 지정합니다.

`lpnLengthNeeded` 전체 보안 설명자를 저장하는 데 필요한 바이트 수를 받는 변수에 대한 포인터입니다. 반환 된 바이트 수가 nLength보다 작거나 같으면 전체 보안 설명자가 출력 버퍼에 반환됩니다. 그렇지 않은 경우에는 설명자가 반환되지 않습니다.



#### Return Value

함수가 성공하면 반환 값은 0이 아닙니다.

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

지정된 파일이나 디렉터리의 보안 설명자에서 소유자, 그룹 또는 DACL을 읽으려면 파일이나 디렉터리의 DACL에서 호출자에 대한 READ_CONTROL 액세스를 허용하거나 호출자가 파일이나 디렉터리의 소유자 여야합니다.

파일이나 디렉토리의 SACL을 읽으려면 호출 프로세스에 대해 SE_SECURITY_NAME 권한을 활성화해야합니다.



#### Requirements

| header                        | lib          | dll          |
| ----------------------------- | ------------ | ------------ |
| winbase.h (include Windows.h) | Advapi32.lib | Advapi32.dll |



#### GetFileSecurity 사용 예제 1 

출처: http://cs20001.blogspot.com/2009/11/example-reading-file-permissions.html

파일 사용 권한 읽기:  프로그램 15-4는 프로그램 15-1과 15-2에서 사용하는 ReadFilePermissions 함수입니다. 이 프로그램은 체계적으로 앞의 함수를 사용하여 정보를 추출합니다. 올바른 작동은 ACL이 프로그램 15-3에 의해 작성되었다는 사실에 달려 있습니다. 이 함수는 프로그램 15-3과 동일한 소스 모듈에 있으므로 정의가 반복되지 않습니다.

```c++
/* Return the UNIX-style permissions for a file. */
DWORD ReadFilePermissions (LPCTSTR lpFileName,
                           LPTSTR UsrNm, 
                           LPTSTR GrpNm)
{
   PSECURITY_DESCRIPTOR pSD = NULL;
   DWORD                LenNeeded, PBits, iAce;
   BOOL                 DaclF, AclDefF, OwnerDefF, GroupDefF;
   BYTE                 DAcl [ACL_SIZE];
   PACL                 pAcl = (PACL) &DAcl;
   ACL_SIZE_INFORMATION ASizeInfo;
   PACCESS_ALLOWED_ACE  pAce;
   BYTE                 AType;
   HANDLE               ProcHeap = GetProcessHeap ();
   PSID                 pOwnerSid, pGroupSid;
   TCHAR                RefDomain [2] [DOM_SIZE];
   DWORD                RefDomCnt [] = {DOM_SIZE, DOM_SIZE};
   DWORD                AcctSize [] = {ACCT_NAME_SIZE, ACCT_NAME_SIZE};
   SID_NAME_USE         sNamUse [] = {SidTypeUser, SidTypeGroup};

   /* Get the required size for the security descriptor. */
   GetFileSecurity (lpFileName,
                    OWNER_SECURITY_INFORMATION | GROUP_SECURITY_INFORMATION |
                    DACL_SECURITY_INFORMATION, 
                    pSD, 0, &LenNeeded);
    
   pSD = HeapAlloc (ProcHeap, HEAP_GENERATE_EXCEPTIONS, LenNeeded);
   GetFileSecurity (lpFileName, OWNER_SECURITY_INFORMATION |
                    GROUP_SECURITY_INFORMATION | DACL_SECURITY_INFORMATION,
                    pSD, LenNeeded, &LenNeeded);
    
   GetSecurityDescriptorDacl (pSD, &DaclF, &pAcl, &AclDefF);
   GetAclInformation (pAcl, 
                      &ASizeInfo,
                      sizeof (ACL_SIZE_INFORMATION), 
                      AclSizeInformation);
    
   PBits = 0; /* Compute the permissions from the ACL. */
   for (iAce = 0; iAce < ASizeInfo.AceCount; iAce++) {
      GetAce (pAcl, iAce, &pAce);
      AType = pAce->Header.AceType;
      if (AType == ACCESS_ALLOWED_ACE_TYPE)
         PBits |= (0x1 << (8-iAce));
   }
    
   /* Find the name of the owner and owning group. */
   GetSecurityDescriptorOwner (pSD, &pOwnerSid, &OwnerDefF);
   GetSecurityDescriptorGroup (pSD, &pGroupSid, &GroupDefF);
    
   LookupAccountSid (NULL, pOwnerSid, UsrNm, &AcctSize [0],
         RefDomain [0], &RefDomCnt [0], &sNamUse [0]);
    
   LookupAccountSid (NULL, pGroupSid, GrpNm, &AcctSize [1],
         RefDomain [1], &RefDomCnt [1], &sNamUse [1]);
    
   return PBits;
}
```



#### GetFileSecurity 사용 예제 2

```c++
#include <stdio.h>
#include <map>
#include <string>
#include <Windows.h>

// 파일이나 폴더의 경로와 다음과 같은 GENERIC 플래그 중 하나 이상을 전달하여 함수를 사용합니다.
// if (CanAccessFolder( TEXT( "C:\\Users\\" ), GENERIC_WRITE )) {}
// if (CanAccessFolder( TEXT( "C:\\" ), GENERIC_READ | GENERIC_WRITE )) {}
bool CanAccessFolder( LPCTSTR folderName, DWORD genericAccessRights )
{
    bool bRet = false;
    DWORD length = 0;
    if (!::GetFileSecurity( folderName, 
                            OWNER_SECURITY_INFORMATION | GROUP_SECURITY_INFORMATION 
                            | DACL_SECURITY_INFORMATION, NULL, NULL, &length ) && 
                            ERROR_INSUFFICIENT_BUFFER == ::GetLastError() ) 
    {
        PSECURITY_DESCRIPTOR security = 
            static_cast< PSECURITY_DESCRIPTOR >( ::malloc( length ) );
        
        if (security && 
            ::GetFileSecurity( folderName, 
                               OWNER_SECURITY_INFORMATION |GROUP_SECURITY_INFORMATION |
                               DACL_SECURITY_INFORMATION, 
                              security, length, &length )) 
        {
            HANDLE hToken = NULL;
            if (::OpenProcessToken( ::GetCurrentProcess(), 
                                    TOKEN_IMPERSONATE | TOKEN_QUERY | TOKEN_DUPLICATE |
                                    STANDARD_RIGHTS_READ, &hToken )) 
            {
                HANDLE hImpersonatedToken = NULL;
                if (::DuplicateToken( hToken, 
                                     SecurityImpersonation, 
                                     &hImpersonatedToken )) 
                {
                    GENERIC_MAPPING mapping = { 0xFFFFFFFF };
                    PRIVILEGE_SET privileges = { 0 };
                    DWORD grantedAccess = 0, privilegesLength = sizeof( privileges );
                    BOOL result = FALSE;
 
                    mapping.GenericRead = FILE_GENERIC_READ;
                    mapping.GenericWrite = FILE_GENERIC_WRITE;
                    mapping.GenericExecute = FILE_GENERIC_EXECUTE;
                    mapping.GenericAll = FILE_ALL_ACCESS;
 
                    ::MapGenericMask( &genericAccessRights, &mapping );
                    if (::AccessCheck( security, 
                                      hImpersonatedToken, 
                                      genericAccessRights, 
                                      &mapping, 
                                      &privileges, 
                                      &privilegesLength, 
                                      &grantedAccess, &result )) 
                    {
                        bRet = (result == TRUE);
                    }
                    ::CloseHandle( hImpersonatedToken );
                }
                ::CloseHandle( hToken );
            }
            ::free( security );
        }
    }
 
    return bRet;
}
```



#### GetFileSecurity 사용 예제 3

```c++
#include <stdio.h>
#include <map>
#include <string>
#include <Windows.h>
int main( int argc, char *argv[] ) {
  if (argc != 3) {
    ::printf( "checkaccess.exe "
              "Read|Write|Execute|ReadWrite|ReadExecute|WriteExecute|"
              "ReadWriteExecute path_to_folder_or_file\n" );
  } else {
    // First arg is the application, second is the access, third
    // is the folder or file
    std::map< std::string, DWORD > m;
    m[ "Read" ] = GENERIC_READ;
    m[ "Write" ] = GENERIC_WRITE;
    // Etc
     
    auto iter = m.find( argv[ 1 ] );
    if (iter != m.end()) {
      if (CheckAccess( argv[ 2 ], iter->second )) {
        ::printf( "Do have access\n" );
      } else {
        ::printf( "Do not have access\n" );
      }
    }
  }
  return 0;
}
```



​	

### 2.2 GetSecurityDescriptorDacl

GetSecurityDescriptorDacl 함수는 지정된 보안 설명자의 DACL (임의 액세스 제어 목록)에 대한 포인터를 검색합니다.

참조: https://www.codeproject.com/Articles/10042/The-Windows-Access-Control-Model-Part-1



#### Syntax

```c++
BOOL GetSecurityDescriptorDacl(
  PSECURITY_DESCRIPTOR pSecurityDescriptor,
  LPBOOL               lpbDaclPresent,
  PACL                 *pDacl,
  LPBOOL               lpbDaclDefaulted
);
```



#### Parameters

`pSecurityDescriptor` DACL이 포함 된 SECURITY_DESCRIPTOR 구조체에 대한 포인터입니다. 이 함수는 포인터를 가져옵니다.



`lpbDaclPresent`

지정된 보안 설명자에 DACL이 있음을 나타내는 값의 포인터. lpbDaclPresent가 TRUE이면 보안 설명자는 DACL을 포함하고 이 함수의 나머지 출력 매개 변수는 유효한 값을받습니다. lpbDaclPresent가 FALSE 인 경우 보안 설명자는 DACL을 포함하지 않으며 나머지 출력 매개 변수는 유효한 값을받지 못합니다.

lpbDaclPresent에 대한 TRUE 값은 pDacl이 NULL이 아님을 의미하지 않습니다. 즉, pDacl이 NULL 인 동안 lpbDaclPresent는 TRUE 일 수 있으며, 이는 NULL DACL이 적용됨을 의미합니다. **NULL DACL은 암시 적으로 개체에 대한 모든 액세스를 허용하며 빈 DACL과 다릅니다**. 빈 **DACL은 개체에 대한 액세스를 허용하지 않습니다**. 적절한 DACL 만들기에 대한 자세한 내용은[ DACL 만들기](https://docs.microsoft.com/windows/desktop/SecBP/creating-a-dacl)를 참조하십시오.



`pDacl`

액세스 제어 목록 (ACL)에 대한 포인터의 포인터. DACL이 있으면이 함수는 pDacl이 가리키는 포인터를 보안 설명자의 DACL 주소로 설정합니다. DACL이 없으면 값이 저장되지 않습니다.

함수가 pDacl이 가리키는 포인터에 NULL 값을 저장하면 보안 설명자가 NULL DACL을가집니다. NULL DACL은 암시 적으로 개체에 대한 모든 액세스를 허용합니다.

응용 프로그램에서 NULL이 아닌 DACL을 예상했지만 NULL DACL이 발생하면 응용 프로그램이 안전하게 실패하고 액세스를 허용하지 않아야합니다.



`lpbDaclDefaulted`

보안 설명자에 대한 DACL이있는 경우 SECURITY_DESCRIPTOR_CONTROL 구조의 SE_DACL_DEFAULTED 플래그 값으로 설정된 플래그에 대한 포인터입니다. 이 플래그가 TRUE이면 DACL이 기본 메커니즘에 의해 검색되었습니다. FALSE이면 사용자가 DACL을 명시 적으로 지정했습니다.



#### Return Value

함수가 성공하면 함수는 0이 아닌 값을 반환합니다.

함수가 실패하면 0을 반환합니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Requirements

| header                                | lib          | dll          |
| ------------------------------------- | ------------ | ------------ |
| securitybaseapi.h (include Windows.h) | Advapi32.lib | Advapi32.dll |



#### GetSecurityDescriptorDacl 예제 1 - 서비스에 대한 DACL 수정

출처: https://docs.microsoft.com/en-us/windows/win32/services/modifying-the-dacl-for-a-service

서비스 제어 프로그램은 액세스를 제어하기 위해 서비스와 관련된 DACL을 만들거나 수정할 수 있습니다. 서비스 개체와 연결된 DACL을 검색하려면 QueryServiceObjectSecurity 함수를 사용합니다. DACL을 설정하려면 SetServiceObjectSecurity 함수를 사용합니다. 서비스 오브젝트와 연관된 SECURITY_DESCRIPTOR에 대한 모든 변경 사항은 서비스가 시스템에서 제거 될 때까지 지속됩니다.

다음 예제에서는 서비스에 대한 새 DACL을 만들고 설정합니다. 이 코드는 하나의 ACE (Access Control Entry)를 서비스의 기존 DACL에 병합합니다. 새 ACE는 Guest 계정에 지정된 서비스에 대한 액세스를 시작, 중지, 삭제 및 READ_CONTROL 할 수있는 권한을 부여합니다. 서비스에 대한 액세스는 BuildExplicitAccessWithName 함수에 전달 된 AccessPermissions 매개 변수에 의해 수정 될 수 있습니다.

szSvcName 변수는 서비스 이름을 포함하는 전역 변수입니다. 이 변수를 설정하는 전체 예제는 [SvcControl.cpp](https://docs.microsoft.com/en-us/windows/win32/services/svccontrol-cpp)를 참조하십시오. 

```c++
//
// Purpose: 
//   Updates the service DACL to grant start, stop, delete, and read
//   control access to the Guest account.
//
// Parameters:
//   None
// 
// Return value:
//   None
//
VOID __stdcall DoUpdateSvcDacl()
{
    EXPLICIT_ACCESS      ea;
    SECURITY_DESCRIPTOR  sd;
    PSECURITY_DESCRIPTOR psd            = NULL;
    PACL                 pacl           = NULL;
    PACL                 pNewAcl        = NULL;
    BOOL                 bDaclPresent   = FALSE;
    BOOL                 bDaclDefaulted = FALSE;
    DWORD                dwError        = 0;
    DWORD                dwSize         = 0;
    DWORD                dwBytesNeeded  = 0;

    // Get a handle to the SCM database. 
 
    schSCManager = OpenSCManager( 
        NULL,                    // local computer
        NULL,                    // ServicesActive database 
        SC_MANAGER_ALL_ACCESS);  // full access rights 
 
    if (NULL == schSCManager) 
    {
        printf("OpenSCManager failed (%d)\n", GetLastError());
        return;
    }

    // Get a handle to the service

    schService = OpenService( 
        schSCManager,              // SCManager database 
        szSvcName,                 // name of service 
        READ_CONTROL | WRITE_DAC); // access
 
    if (schService == NULL)
    { 
        printf("OpenService failed (%d)\n", GetLastError()); 
        CloseServiceHandle(schSCManager);
        return;
    }    

    // Get the current security descriptor.

    if (!QueryServiceObjectSecurity(schService,
        DACL_SECURITY_INFORMATION, 
        &psd,           // using NULL does not work on all versions
        0, 
        &dwBytesNeeded))
    {
        if (GetLastError() == ERROR_INSUFFICIENT_BUFFER)
        {
            dwSize = dwBytesNeeded;
            psd = (PSECURITY_DESCRIPTOR)HeapAlloc(GetProcessHeap(),
                    HEAP_ZERO_MEMORY, dwSize);
            if (psd == NULL)
            {
                // Note: HeapAlloc does not support GetLastError.
                printf("HeapAlloc failed\n");
                goto dacl_cleanup;
            }
  
            if (!QueryServiceObjectSecurity(schService,
                DACL_SECURITY_INFORMATION, psd, dwSize, &dwBytesNeeded))
            {
                printf("QueryServiceObjectSecurity failed (%d)\n", GetLastError());
                goto dacl_cleanup;
            }
        }
        else 
        {
            printf("QueryServiceObjectSecurity failed (%d)\n", GetLastError());
            goto dacl_cleanup;
        }
    }

    // Get the DACL.

    if (!GetSecurityDescriptorDacl(psd, &bDaclPresent, &pacl,
                                   &bDaclDefaulted))
    {
        printf("GetSecurityDescriptorDacl failed(%d)\n", GetLastError());
        goto dacl_cleanup;
    }

    // Build the ACE.

    BuildExplicitAccessWithName(&ea, TEXT("GUEST"),
        SERVICE_START | SERVICE_STOP | READ_CONTROL | DELETE,
        SET_ACCESS, NO_INHERITANCE);

    dwError = SetEntriesInAcl(1, &ea, pacl, &pNewAcl);
    if (dwError != ERROR_SUCCESS)
    {
        printf("SetEntriesInAcl failed(%d)\n", dwError);
        goto dacl_cleanup;
    }

    // Initialize a new security descriptor.

    if (!InitializeSecurityDescriptor(&sd, 
        SECURITY_DESCRIPTOR_REVISION))
    {
        printf("InitializeSecurityDescriptor failed(%d)\n", GetLastError());
        goto dacl_cleanup;
    }

    // Set the new DACL in the security descriptor.

    if (!SetSecurityDescriptorDacl(&sd, TRUE, pNewAcl, FALSE))
    {
        printf("SetSecurityDescriptorDacl failed(%d)\n", GetLastError());
        goto dacl_cleanup;
    }

    // Set the new DACL for the service object.

    if (!SetServiceObjectSecurity(schService, 
        DACL_SECURITY_INFORMATION, &sd))
    {
        printf("SetServiceObjectSecurity failed(%d)\n", GetLastError());
        goto dacl_cleanup;
    }
    else printf("Service DACL updated successfully\n");

dacl_cleanup:
    CloseServiceHandle(schSCManager);
    CloseServiceHandle(schService);

    if(NULL != pNewAcl)
        LocalFree((HLOCAL)pNewAcl);
    if(NULL != psd)
        HeapFree(GetProcessHeap(), 0, (LPVOID)psd);
}
```



#### GetSecurityDescriptorDacl 예제 2 - DACL에서 ACE 제거

출처: https://www.codeproject.com/Questions/362730/remove-an-ACE-from-a-DACL

디렉터리/파일의 DACL에서 ACE를 제거하는 방법 샘플이 도움이 될 것입니다. 나는 'Everyone'그룹의 파일/디렉토리의 DACL로부터 모든 Deny ACE를 제거하고 어딘가에 저장하는 C ++ 프로그램을 작성하고 있습니다. 또한 GetFileSecurity 동안 OWNER_SECURITY_INFORMATION / DACL_SECURITY_INFORMATION 또는 두 가지 모두에 대해 어떤 SecurityInformation을 쿼리해야합니까?

##### Solution 1

```c++
void RemoveEveryoneDeny()
{
	SECURITY_DESCRIPTOR *psd;
	DWORD needed;

    //find out how big the security descriptor is
	GetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, NULL, 0, &needed); 
	psd = (SECURITY_DESCRIPTOR*)malloc(needed); //allocate that memory
	GetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, psd, needed, &needed); 		//fill out the security descriptor

	PACL dacl;
	BOOL present, defaulted;
	ULONG entries;
	EXPLICIT_ACCESS *ea;

	GetSecurityDescriptorDacl(psd, &present, &dacl, &defaulted);
	GetExplicitEntriesFromAcl(dacl, &entries, &ea);
	//do what you need to to save information from the explicit access
	//array, the amount of entries that it contains is in entries, and
	//the TRUSTEE structure that it contains will contain the SID of
	//the user account, so you can compare it to the well known Everyone
	//SID or use LookupAccountSid to get the name from it and compare that
	//with the Everyone string

	PACL newacl;
	EXPLICIT_ACCESS ea2 = {0};
	ea2.grfAccessMode = SET_ACCESS; //overrides any current acl permissions
	
    //this is filled in from the file 	permissions, see CreateFile
    ea2.grfAccessPermissions = STANDARD_RIGHTS_ALL; 
	ea2.grfInheritance = NO_INHERITANCE;
    
    //must be set to this
	ea2.Trustee.MultipleTrusteeOperation = NO_MULTIPLE_TRUSTEE; 
	
    //must be null
    ea2.Trustee.pMultipleTrustee = nullptr; 
    //this says that ptstrName points at a string
	ea2.Trustee.TrusteeForm = TRUSTEE_IS_NAME; 
    //Everyone is the World SID, and is a well known group
	ea2.Trustee.TrusteeType = TRUSTEE_IS_WELL_KNOWN_GROUP; 
    //The world SID name
	ea2.Trustee.ptstrName = _T("Everyone"); 

	SetEntriesInAcl(1, &ea2, dacl, &newacl);

	SECURITY_DESCRIPTOR sd2;
	InitializeSecurityDescriptor(&sd2, SECURITY_DESCRIPTOR_REVISION);
	SetSecurityDescriptorDacl(&sd2, TRUE, newacl, FALSE);

	SetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, &sd2);
}
```



##### Solution 2

비교적 간단한 해결책을 찾았습니다. GetNamedSecurityInfo 또는 GetFileSecurity로 보안 설명자를 가져옵니다. DACL을 추출하고 반복합니다. 이제 플래그와 기타 정보가 일치하는지 확인하고 API DeleteAce를 호출하여 색인 번호를 전달하십시오.

나중에 ACL에서 항목을 추가하기 위해 API 인 AddAce를 사용하기 시작했습니다. 놀랍게도 AddAce를 사용하는 것은 그렇게 간단하지 않습니다. 이유 - ACL에서 ACE의 순서는 구체적입니다. 내가 이해할 수있는 한 사용자 정의 된 ACE가 먼저 배치되어야합니다. 따라서 ACE를 추가 할 때마다 새 ACL을 만들고 새 ACE와 이전 에코를 복사해야했습니다. 나는 여전히 더 나은 방법을 찾고있다. 또는 최소한 ACL에서 ACE를 재 배열 할 수 있습니다. 어떤 제안?





### 2.3 AllocateAndInitializeSid

AllocateAndInitializeSid 함수는 최대 8 개의 하위 인증 기관을 사용하여 SID (보안 식별자)를 할당하고 초기화합니다.

#### Syntax

```c++
BOOL AllocateAndInitializeSid(
  PSID_IDENTIFIER_AUTHORITY pIdentifierAuthority,
  BYTE                      nSubAuthorityCount,
  DWORD                     nSubAuthority0,
  DWORD                     nSubAuthority1,
  DWORD                     nSubAuthority2,
  DWORD                     nSubAuthority3,
  DWORD                     nSubAuthority4,
  DWORD                     nSubAuthority5,
  DWORD                     nSubAuthority6,
  DWORD                     nSubAuthority7,
  PSID                      *pSid
);
```







### 2.4 SetEntriesInAcl



### 2.5 LocalAlloc



### 2.6 InitializeSecurityDescriptor



### 2.7 SetSecurityDescriptorDacl



### 2.8 FreeSid



### 2.9 LocalFree



### 2.10 NetUserEnum



### 2.11 NetGroupEnum



### 2.12 NetQueryDisplayInformation



### 2.13 GetUserNameW



### 2.14 NetApiBufferFree



### 2.15 GetNamedSecurityInfo





## 3. Windows 파일 및 폴더 소유권 가져오기 Utility

본 장 에서는 Windows 에서 파일 및 폴더의 소유권을 가져 오거나 변경하는 Utility 에 대하여 기록 합니다.

참조: https://bbon.kr/windows-manage-ownership-and-permission-for-file-folder/



| 파일명  | 기능                                                         |
| ------- | ------------------------------------------------------------ |
| TAKEOWN | 이 도구는 관리자가 이전에 액세스가 거부되었던 파일의 소유권을 다시 할당하여 액세스를 복구하도록 합니다. |
| ICACLS  | 권한을 부여 합니다. (ACL 을 조회 하거나 변경 합니다.)        |



### 3.1 TAKEOWN

해당 파일의 소유권을 가져옵니다



#### Usage

```bat
TAKEOWN [/S system [/U username [/P [password]]]]
        /F filename [/A] [/R [/D prompt]]
```



#### 설명:

```
이 도구는 관리자가 이전에 액세스가 거부되었던 파일의 소유권을 다시 할당하여 액세스를 복구하도록 합니다.
```



#### 매개 변수 목록

```bat
    /S           system          연결할 원격 시스템을 지정합니다.

    /U           [domain\]user   명령을 실행할 사용자 컨텍스트를
                                 지정합니다.

    /P           [password]      제공된 사용자 컨텍스트에 대한
                                 암호를 지정합니다.
                                 생략된 경우 입력하도록 묻습니다.

    /F           filename        파일 이름 또는 디렉터리 이름 패턴을
                                 지정합니다. 패턴은 "*" 와일드카드를
                                 사용하여 지정할 수 있습니다.
                                 sharename\filename을 허용합니다.

    /A                           현재 사용자의 대신에 관리자 그룹에게
                                 소유권을 줍니다.

    /R                           재귀: 지정한 디렉터리 및 모든 하위
                                 디렉터리에 도구가 작동하도록
                                 지정합니다.

    /D           prompt          현재 사용자가 디렉터리에 대한
                                 "폴더 목록" 권한이 없는 경우 사용하는
                                 기본 응답입니다. 하위 디렉터리에서
                                 재귀(/R) 작업을 수행할 경우 생깁니다.
                                 소유권을 가지려면 "Y", 건너뛰려면 "N".

    /SKIPSL                      심볼 링크를 따르지 않습니다.
                                 /R에만 해당됩니다.

    /?                           이 도움말 메시지를 표시합니다.
    
    참고: 1) /A가 지정되지 않으면 현재 로그온된 사용자에게 파일
             소유권을 줍니다.

          2) "?" 및 "*"로 지정한 혼합 패턴은 지원되지 않습니다.

          3) /D를 사용하면 확인 메시지를 표시하지 않습니다.
```



#### 예:

```bat
    TAKEOWN /?
    TAKEOWN /F lostfile
    TAKEOWN /F \\system\share\lostfile /A
    TAKEOWN /F directory /R /D N
    TAKEOWN /F directory /R /A
    TAKEOWN /F *
    TAKEOWN /F C:\Windows\System32\acme.exe
    TAKEOWN /F %windir%\*.txt
    TAKEOWN /S system /F MyShare\Acme*.doc
    TAKEOWN /S system /U user /F MyShare\MyBinary.dll
    TAKEOWN /S system /U domain\user /P password /F share\filename
    TAKEOWN /S system /U user /P password /F Doc\Report.doc /A
    TAKEOWN /S system /U user /P password /F Myshare\*
    TAKEOWN /S system /U user /P password /F Home\Logon /R
    TAKEOWN /S system /U user /P password /F Myshare\directory /R /A
```



### 3.2 ICACLS 

파일이나 폴더의 접근권한 (ACL) 을 조회 하거나 변경 합니다.



#### Syntax

```bat
Add or remove permissions:
   ICACLS Name
      [/grant[:r] User:Permission[...]]
         [/deny User:Permission[...]]
            [/remove[:g|:d]] User[...]]
               [/inheritance:e|d|r ]
                  [/setintegritylevel Level[...]]
                     [/T] [/C] [/L] [/Q]
```



| **옵션**     | **설명**                                                    |
| ------------ | ----------------------------------------------------------- |
| /save        | 현재 설정된 권한을 ACL(Access Control List)파일로 저장한다. |
| /restore     | 저장된 ACL 파일로부터 권한을 복원한다.                      |
| /setowner    | TAKEOWN을 이용하여 소유자를 변경한다.                       |
| /findsid     | 설정한 SID와 일치하는 파일을 찾는다.                        |
| **/grant**   | **권한 설정(허용)**                                         |
| /deny        | 권한 해제(거부)                                             |
| /remove      | 해당 SID(계정)에 대한 권한 삭제                             |
| /inheritance | 상속 추가/제거                                              |



#### Usage

```bat
ICACLS 이름 /save aclfile [/T] [/C] [/L] [/Q]
```

나중에 /restore와 함께 사용할 수 있도록 이름이 일치하는 파일 및 폴더에 대한 DACL을 aclfile에 저장합니다. SACL, 소유자 또는 무결성 레이블은 저장되지 않습니다.



```bat
ICACLS 디렉터리 [/substitute SidOld SidNew [...]] /restore aclfile [/C] [/L] [/Q]
```

저장된 DACL을 디렉터리의 파일에 적용합니다.



```bat
ICACLS 이름 /setowner user [/T] [/C] [/L] [/Q]
```

일치하는 모든 이름의 소유자를 변경합니다. 이 옵션은 소유권을 강제 변경하지 않고 takeown.exe 유틸리티를 사용하여 변경합니다.



```bat
ICACLS 이름 /findsid Sid [/T] [/C] [/L] [/Q]
```

SID를 명시적으로 지정하는 ACL이 포함된 일치하는 모든 이름을 찾습니다.



```bat
ICACLS 이름 /verify [/T] [/C] [/L] [/Q]
```

ACL이 정규형이 아니거나 길이가 ACE 개수와 일치하지 않는 모든 파일을 찾습니다.



```bat
ICACLS 이름 /reset [/T] [/C] [/L] [/Q]
```

ACL을 일치하는 모든 파일에 대해 상속된 기본 ACL로 바꿉니다



```bat
ICACLS 이름 [/grant[:r] Sid:perm[...]]
       [/deny Sid:perm [...]]
       [/remove[:g|:d]] Sid[...]] [/T] [/C] [/L] [/Q]
       [/setintegritylevel Level:policy[...]]
```



/grant[:r] Sid:perm은 지정된 사용자 액세스 권한을 허용합니다. :r을 사용하면 사용 권한이 이전에 허용된 모든 명시적 사용 권한을 대체합니다. :r을 사용하지 않으면 사용 권한이 이전에 허용된 모든 명시적 사용 권한에 추가됩니다.

/deny Sid:perm은 지정된 사용자 액세스 권한을 명시적으로 거부합니다. 지정된 사용 권한에 대해 명시적인 deny ACE가 추가되고 모든 명시적 grant의 동일한 사용 권한이 제거됩니다.

/remove[:[g|d]] Sid는 ACL에 있는 모든 SID 항목을 제거합니다.  :g를 사용하면 해당 SID에 허용된 모든 권한 항목을 제거하고 :d를 사용하면 해당 SID에 거부된 모든 권한 항목을 제거합니다.
    

/setintegritylevel [(CI)(OI)]수준에 따라 명시적으로 무결성 ACE가 일치하는 모든 파일에 추가됩니다. 이 수준은 다음 중 하나로 지정됩니다.
            L[ow]
            M[edium]
            H[igh]
무결성 ACE에 대한 상속 옵션은 수준보다 우선할 수 있고  디렉터리에만 적용됩니다.
    

/inheritance:e|d|r
        e - 상속할 수 있도록 설정합니다.
        d - ACE를 상속하거나 복사하지 못하도록 설정합니다.
        r - 상속된 모든 ACE를 제거합니다.



#### 참고:

    SID는 숫자 또는 이름 형식이 될 수 있습니다. 숫자 형식을 지정하면
    SID의 시작 부분에 * 기호를 붙여야 합니다.
    
    /T 이름에 지정된 디렉터리 아래의 일치하는 모든 파일/디렉터리에서 이 작업을 수행하도록 지정합니다.
    
    /C 어떠한 파일 오류의 경우에도 이 작업을 계속하도록 지정합니다. 오류 메시지는 그대로 표시됩니다.
    
    /L 해당 대상에 대한 바로 가기 링크 자체에서 이 작업을 수행하도록 지정합니다.
    
    /Q ICACLS가 성공 메시지를 표시하지 않도록 지정합니다.
    
    ICACLS은 ACE 항목의 정식 순서를 유지합니다.
            명시적 거부
            명시적 허용
            상속된 거부
            상속된 허용
            
        perm은 다음 두 가지 형식 중 하나로 지정할 수 있는 사용 권한 마스크입니다.
            단순 권한 시퀀스:
                    N - 권한 없음
                    F - 모든 권한
                    M - 수정 권한
                    RX - 읽기 및 실행 권한
                    R - 읽기 전용 권한
                    W - 쓰기 전용 권한
                    D - 삭제 권한
             괄호 안 쉼표로 구분된 특정 권한 목록:
                    DE - 삭제
                    RC - 읽기 제어
                    WDAC - DAC 쓰기
                    WO - 쓰기 소유자
                    S - 동기화
                    AS - 액세스 시스템 보안
                    MA - 최대한 허용
                    GR - 일반 읽기
                    GW - 일반 쓰기
                    GE - 일반 실행
                    GA - 일반 전체
                    RD - 데이터 읽기/디렉터리 나열
                    WD - 데이터 쓰기/파일 추가
                    AD - 데이터 추가/하위 디렉터리 추가
                    REA - 확장 특성 읽기
                    WEA - 확장 특성 쓰기
                    X - 실행/트래버스
                    DC - 자식 삭제
                    RA - 특성 읽기
                    WA - 특성 쓰기
            상속 권한은 모든 형식 앞에 올 수 있으며 디렉터리에만
            적용됩니다.
                    (OI) - 개체 상속
                    (CI) - 컨테이너 상속
                    (IO) - 상속 전용
                    (NP) - 상속 전파 안 함
                    (I) - 부모 컨테이너에서 상속된 권한


#### 예:

```bat
icacls c:\windows\* /save AclFile /T
```

c:\windows와 해당 하위 디렉터리 아래의 모든 파일에 대한 ACL을 AclFile에 저장합니다.



```bat
icacls c:\windows\ /restore AclFile
```

c:\windows와 해당 하위 디렉터리에 있는 AclFile 내의 모든 파일에 대한 ACL을 복원합니다.



```bat
icacls file /grant Administrator:(D,WDAC)
```

사용자에게 파일에 대한 관리자 삭제 및 DAC 쓰기 권한을 허용합니다.



```bat
icacls file /grant *S-1-1-0:(D,WDAC)
```

SID S-1-1-0으로 정의한 사용자에게 파일에 대한 삭제 및 DAC 쓰기 권한을 허용합니다.



**/grant,  /deny, remove 등의 명령을 적절히 조합하여 Access  권한을 제어 할 수 있다.**







## Process Security and Access Rights

출처: https://docs.microsoft.com/en-us/windows/win32/procthread/process-security-and-access-rights

Microsoft Windows 보안 모델을 사용하면 프로세스 개체에 대한 액세스를 제어 할 수 있습니다. 보안에 대한 자세한 내용은 액세스 제어 모델을 참조하십시오.

사용자가 로그인하면 시스템은 인증 과정에서 사용자를 고유하게 식별하는 데이터 집합을 수집하여 액세스 토큰에 저장합니다. 이 액세스 토큰은 사용자와 관련된 모든 프로세스의 보안 컨텍스트를 설명합니다. 프로세스의 보안 컨텍스트는 프로세스 또는 프로세스를 만든 사용자 계정에 부여 된 자격 증명 집합입니다.

토큰을 사용하여 CreateProcessWithTokenW 함수를 사용하여 프로세스의 현재 보안 컨텍스트를 지정할 수 있습니다. CreateProcess, CreateProcessAsUser 또는 CreateProcessWithLogonW 함수를 호출 할 때 프로세스에 대한 보안 설명자를 지정할 수 있습니다. NULL을 지정하면 프로세스가 기본 보안 설명자를 가져옵니다. 프로세스의 기본 보안 설명자에있는 ACL은 작성자의 기본 토큰 또는 가장 토큰에서 가져옵니다.

프로세스의 보안 설명자를 검색하려면 GetSecurityInfo 함수를 호출하십시오. 프로세스의 보안 설명자를 변경하려면 SetSecurityInfo 함수를 호출하십시오.

프로세스 개체에 대한 유효한 액세스 권한에는 표준 액세스 권한과 일부 프로세스 별 액세스 권한이 포함됩니다. 



다음 표에는 모든 개체에서 사용하는 표준 액세스 권한이 나와 있습니다.

| Value                              | Meaning                                                      |
| :--------------------------------- | :----------------------------------------------------------- |
| **DELETE**<br/>(0x00010000L)       | 객체를 삭제하는 데 필요합니다.                               |
| **READ_CONTROL**<br/>(0x00020000L) | SACL의 정보는 제외하고 개체에 대한 보안 설명자의 정보를 읽는 데 필요합니다. SACL을 읽거나 쓰려면 ACCESS_SYSTEM_SECURITY 액세스 권한을 요청해야합니다. 자세한 내용은 SACL 액세스 권한을 참조하십시오. |
| **SYNCHRONIZE**<br/>(0x00100000L)  | 동기화를 위해 개체를 사용할 수있는 권한. 이렇게하면 객체가 신호가있는 상태가 될 때까지 스레드가 대기 할 수 있습니다. |
| **WRITE_DAC**<br/>(0x00040000L)    | 개체의 보안 설명자에서 DACL을 수정하려면 필요합니다.         |
| **WRITE_OWNER**<br/>(0x00080000L)  | 개체의 보안 설명자에서 소유자를 변경하려면 필요합니다.       |



다음 표에는 프로세스 별 액세스 권한이 나와 있습니다.

| Value                                 | Meaning                                                      |
| :------------------------------------ | :----------------------------------------------------------- |
| **PROCESS_ALL_ACCESS**                | 프로세스 개체에 대한 모든 가능한 액세스 권한. Windows Server 2003 및 Windows XP : Windows Server 2008 및 Windows Vista에서 PROCESS_ALL_ACCESS 플래그의 크기가 증가했습니다. Windows Server 2008 및 Windows Vista 용으로 컴파일 된 응용 프로그램이 Windows Server 2003 또는 Windows XP에서 실행되는 경우 PROCESS_ALL_ACCESS 플래그가 너무 커서이 플래그를 지정하는 함수가 ERROR_ACCESS_DENIED와 함께 실패합니다. 이. 제점을 피하려면 조작에 필요한 최소 액세스 권한 세트를 지정하십시오. PROCESS_ALL_ACCESS를 사용해야하는 경우 _WIN32_WINNT를 응용 프로그램이 목표로하는 최소 운영 체제로 설정하십시오 (예 : _WIN32_WINNT _WIN32_WINNT_WINXP 정의). 자세한 내용은 Windows 헤더 사용을 참조하십시오. |
| **PROCESS_CREATE_PROCESS**            | 프로세스를 만드는 데 필요합니다.                             |
| **PROCESS_CREATE_THREAD**             | 스레드를 만드는 데 필요합니다.                               |
| **PROCESS_DUP_HANDLE**                | DuplicateHandle을 사용하여 핸들을 복제하는 데 필요합니다.    |
| **PROCESS_QUERY_INFORMATION**         | 토큰, 종료 코드 및 우선 순위 클래스와 같은 프로세스에 대한 특정 정보를 검색하는 데 필요합니다 (OpenProcessToken 참조). |
| **PROCESS_QUERY_LIMITED_INFORMATION** | 프로세스에 대한 특정 정보를 검색하는 데 필요합니다 (GetExitCodeProcess, GetPriorityClass, IsProcessInJob, QueryFullProcessImageName 참조). PROCESS_QUERY_INFORMATION 액세스 권한이있는 핸들에는 PROCESS_QUERY_LIMITED_INFORMATION이 자동으로 부여됩니다. Windows Server 2003 및 Windows XP :이 액세스 권한은 지원되지 않습니다. |
| **PROCESS_SET_INFORMATION**           | 우선 순위 클래스와 같은 프로세스에 대한 특정 정보를 설정하는 데 필요합니다 (SetPriorityClass 참조). |
| **PROCESS_SET_QUOTA**                 | SetProcessWorkingSetSize를 사용하여 메모리 제한을 설정하는 데 필요합니다. |
| **PROCESS_SUSPEND_RESUME**            | 프로세스를 일시 중단하거나 다시 시작하는 데 필요합니다.      |
| **PROCESS_TERMINATE**                 | TerminateProcess를 사용하여 프로세스를 종료하는 데 필요합니다. |
| **PROCESS_VM_OPERATION**              | 프로세스의 주소 공간에서 작업을 수행하는 데 필요합니다 (VirtualProtectEx 및 WriteProcessMemory 참조). |
| **PROCESS_VM_READ**                   | ReadProcessMemory를 사용하는 프로세스에서 메모리를 읽는 데 필요합니다. |
| **PROCESS_VM_WRITE**                  | WriteProcessMemory를 사용하는 프로세스에서 메모리에 쓰는 데 필요합니다. |
| **SYNCHRONIZE**                       | 대기 함수를 사용하여 프로세스가 종료 될 때까지 대기해야합니다. |

다른 프로세스에 대한 핸들을 열고 전체 액세스 권한을 얻으려면 SeDebugPrivilege 권한을 활성화해야합니다. 자세한 내용은 토큰에서 권한 변경을 참조하십시오.

CreateProcess 함수에 의해 반환 된 핸들은 프로세스 개체에 대한 PROCESS_ALL_ACCESS 액세스 권한을가집니다. OpenProcess 함수를 호출하면 시스템은 프로세스의 보안 설명자에있는 DACL에 대해 요청 된 액세스 권한을 확인합니다. GetCurrentProcess 함수를 호출하면 DACL이 호출자에게 허용하는 최대 액세스 권한이있는 가상 엽서가 시스템에서 반환됩니다.

개체의 SACL을 읽거나 쓰려면 프로세스 개체에 대한 ACCESS_SYSTEM_SECURITY 액세스 권한을 요청할 수 있습니다. 자세한 내용은 액세스 제어 목록 (ACL) 및 SACL 액세스 권한을 참조하십시오.

##### Warning

```
여기에 언급 된 액세스 권한 중 일부가 있는 프로세스는 이를 사용하여 다른 액세스 권한을 얻을 수 있습니다. 예를 들어 프로세스 A에 PROCESS_DUP_HANDLE 액세스로 B를 처리하는 핸들이 있으면 프로세스 B에 대한 의사 핸들을 복제 할 수 있습니다. 프로세스 B에 최대한 액세스 할 수있는 핸들을 만듭니다. 의사 핸들에 대한 자세한 내용은 GetCurrentProcess를 참조하십시오.
```



### Protected Processes

Windows Vista는 보호 된 프로세스를 도입하여 디지털 권한 관리에 대한 지원을 강화합니다. 시스템은 보호 된 프로세스 및 보호 된 프로세스의 스레드에 대한 액세스를 제한합니다.

다음 표준 액세스 권한은 프로세스에서 보호 된 프로세스로 허용되지 않습니다.

**DELETE** **READ\_CONTROL** **WRITE\_DAC** **WRITE\_OWNER**



다음 특정 액세스 권한은 프로세스에서 보호 된 프로세스로 허용되지 않습니다.

**PROCESS\_ALL\_ACCESS** **PROCESS\_CREATE\_PROCESS** **PROCESS\_CREATE\_THREAD** **PROCESS\_DUP\_HANDLE** **PROCESS\_QUERY\_INFORMATION** **PROCESS\_SET\_INFORMATION** **PROCESS\_SET\_QUOTA** **PROCESS\_VM\_OPERATION** **PROCESS\_VM\_READ** **PROCESS\_VM\_WRITE**



**PROCESS_QUERY_LIMITED_INFORMATION** 권한은 **PROCESS_QUERY_INFORMATION**을 통해 사용 가능한 정보의 하위 집합에 대한 액세스를 제공하기 위해 도입되었습니다.

