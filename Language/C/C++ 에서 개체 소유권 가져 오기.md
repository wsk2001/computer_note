# C++ 에서 개체 소유권 가져 오기

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/taking-object-ownership-in-c-->

다음 예제에서는 해당 개체의 소유권을 가져 와서 파일 개체의 DACL을 변경하려고 시도합니다. 호출자가 개체에 대한 WRITE_DAC 액세스 권한을 갖고 있거나 개체 소유자 인 경우에만 성공합니다. DACL을 처음 변경하려는 시도가 실패하면 관리자는 개체의 소유권을 가져올 수 있습니다. 이 예에서는 관리자에게 소유권을 부여하기 위해 호출자의 [액세스 토큰](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-token-gly) 에서 SE_TAKE_OWNERSHIP_NAME 권한을 사용하고 로컬 시스템의 Administrators 그룹을 개체의 소유자로 만듭니다. 호출자가 Administrators 그룹의 구성원이면 코드에서 개체의 DACL을 변경할 수 있습니다.

권한을 활성화 및 비활성화하기 위해이 예제에서는 [C++ 에서 권한 활성화 및 비활성화](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/enabling-and-disabling-privileges-in-c--) 에 설명 된 SetPrivilege 샘플 함수를 사용합니다.

```c++
#include <windows.h>
#include <stdio.h>
#include <accctrl.h>
#include <aclapi.h>

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

BOOL TakeOwnership(LPTSTR lpszOwnFile) 
{

    BOOL bRetval = FALSE;

    HANDLE hToken = NULL; 
    PSID pSIDAdmin = NULL;
    PSID pSIDEveryone = NULL;
    PACL pACL = NULL;
    SID_IDENTIFIER_AUTHORITY SIDAuthWorld =
            SECURITY_WORLD_SID_AUTHORITY;
    SID_IDENTIFIER_AUTHORITY SIDAuthNT = SECURITY_NT_AUTHORITY;
    const int NUM_ACES  = 2;
    EXPLICIT_ACCESS ea[NUM_ACES];
    DWORD dwRes;

    // Specify the DACL to use.
    // Create a SID for the Everyone group.
    if (!AllocateAndInitializeSid(&SIDAuthWorld, 1,
                     SECURITY_WORLD_RID,
                     0,
                     0, 0, 0, 0, 0, 0,
                     &pSIDEveryone)) 
    {
        printf("AllocateAndInitializeSid (Everyone) error %u\n",
                GetLastError());
        goto Cleanup;
    }

    // Create a SID for the BUILTIN\Administrators group.
    if (!AllocateAndInitializeSid(&SIDAuthNT, 2,
                     SECURITY_BUILTIN_DOMAIN_RID,
                     DOMAIN_ALIAS_RID_ADMINS,
                     0, 0, 0, 0, 0, 0,
                     &pSIDAdmin)) 
    {
        printf("AllocateAndInitializeSid (Admin) error %u\n",
                GetLastError());
        goto Cleanup;
    }

    ZeroMemory(&ea, NUM_ACES * sizeof(EXPLICIT_ACCESS));

    // Set read access for Everyone.
    ea[0].grfAccessPermissions = GENERIC_READ;
    ea[0].grfAccessMode = SET_ACCESS;
    ea[0].grfInheritance = NO_INHERITANCE;
    ea[0].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[0].Trustee.TrusteeType = TRUSTEE_IS_WELL_KNOWN_GROUP;
    ea[0].Trustee.ptstrName = (LPTSTR) pSIDEveryone;

    // Set full control for Administrators.
    ea[1].grfAccessPermissions = GENERIC_ALL;
    ea[1].grfAccessMode = SET_ACCESS;
    ea[1].grfInheritance = NO_INHERITANCE;
    ea[1].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[1].Trustee.TrusteeType = TRUSTEE_IS_GROUP;
    ea[1].Trustee.ptstrName = (LPTSTR) pSIDAdmin;

    if (ERROR_SUCCESS != SetEntriesInAcl(NUM_ACES,
                                         ea,
                                         NULL,
                                         &pACL))
    {
        printf("Failed SetEntriesInAcl\n");
        goto Cleanup;
    }

    // Try to modify the object's DACL.
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // name of the object
        SE_FILE_OBJECT,              // type of object
        DACL_SECURITY_INFORMATION,   // change only the object's DACL
        NULL, NULL,                  // do not change owner or group
        pACL,                        // DACL specified
        NULL);                       // do not change SACL

    if (ERROR_SUCCESS == dwRes) 
    {
        printf("Successfully changed DACL\n");
        bRetval = TRUE;
        // No more processing needed.
        goto Cleanup;
    }
    if (dwRes != ERROR_ACCESS_DENIED)
    {
        printf("First SetNamedSecurityInfo call failed: %u\n",
                dwRes); 
        goto Cleanup;
    }

    // If the preceding call failed because access was denied, 
    // enable the SE_TAKE_OWNERSHIP_NAME privilege, create a SID for 
    // the Administrators group, take ownership of the object, and 
    // disable the privilege. Then try again to set the object's DACL.

    // Open a handle to the access token for the calling process.
    if (!OpenProcessToken(GetCurrentProcess(), 
                          TOKEN_ADJUST_PRIVILEGES, 
                          &hToken)) 
       {
          printf("OpenProcessToken failed: %u\n", GetLastError()); 
          goto Cleanup; 
       } 

    // Enable the SE_TAKE_OWNERSHIP_NAME privilege.
    if (!SetPrivilege(hToken, SE_TAKE_OWNERSHIP_NAME, TRUE)) 
    {
        printf("You must be logged on as Administrator.\n");
        goto Cleanup; 
    }

    // Set the owner in the object's security descriptor.
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // name of the object
        SE_FILE_OBJECT,              // type of object
        OWNER_SECURITY_INFORMATION,  // change only the object's owner
        pSIDAdmin,                   // SID of Administrator group
        NULL,
        NULL,
        NULL); 

    if (dwRes != ERROR_SUCCESS) 
    {
        printf("Could not set owner. Error: %u\n", dwRes); 
        goto Cleanup;
    }
        
    // Disable the SE_TAKE_OWNERSHIP_NAME privilege.
    if (!SetPrivilege(hToken, SE_TAKE_OWNERSHIP_NAME, FALSE)) 
    {
        printf("Failed SetPrivilege call unexpectedly.\n");
        goto Cleanup;
    }

    // Try again to modify the object's DACL,
    // now that we are the owner.
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // name of the object
        SE_FILE_OBJECT,              // type of object
        DACL_SECURITY_INFORMATION,   // change only the object's DACL
        NULL, NULL,                  // do not change owner or group
        pACL,                        // DACL specified
        NULL);                       // do not change SACL

    if (dwRes == ERROR_SUCCESS)
    {
        printf("Successfully changed DACL\n");
        bRetval = TRUE; 
    }
    else
    {
        printf("Second SetNamedSecurityInfo call failed: %u\n",
                dwRes); 
    }

Cleanup:

    if (pSIDAdmin)
        FreeSid(pSIDAdmin); 

    if (pSIDEveryone)
        FreeSid(pSIDEveryone); 

    if (pACL)
       LocalFree(pACL);

    if (hToken)
       CloseHandle(hToken);

    return bRetval;

}
```



---

### 용어 정의

**access token**

액세스 토큰에는 로그온 세션에 대한 보안 정보가 들어 있습니다. 시스템은 사용자가 로그온 할 때 액세스 토큰을 작성하고 사용자를 대신하여 실행되는 모든 프로세스에 토큰 사본이 있습니다. 토큰은 사용자, 사용자 그룹 및 사용자의 권한을 식별합니다. 시스템은 토큰을 사용하여 보안 개체에 대한 액세스를 제어하고 사용자가 로컬 컴퓨터에서 다양한 시스템 관련 작업을 수행하는 기능을 제어합니다. 기본 토큰과 가장이라는 두 가지 종류의 액세스 토큰이 있습니다.

See also [*impersonation token*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/i-gly), [*primary token*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/p-gly), [*privilege*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/p-gly), [*process*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/p-gly), and [*security identifier*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/s-gly).



**impersonation token**

서버가 보안 작업에서 클라이언트 프로세스를 '가장 할 수 있도록'클라이언트 프로세스의 보안 정보를 캡처하기 위해 생성 된 액세스 토큰입니다.

See also [*access token*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/a-gly) and [*primary token*](https://docs.microsoft.com/ko-kr/windows/desktop/SecGloss/p-gly).



**primary token**

일반적으로 Windows 커널에서만 생성되는 액세스 토큰입니다. 해당 프로세스의 기본 보안 정보를 나타내는 프로세스에 할당 될 수 있습니다.



**privilege**

사용자가 시스템 종료, 장치 드라이버로드 또는 시스템 시간 변경과 같은 다양한 시스템 관련 작업을 수행 할 수있는 권한. 사용자의 액세스 토큰에는 사용자 또는 사용자 그룹이 보유한 권한 목록이 포함됩니다.



**process**

응용 프로그램이 실행되는 보안 컨텍스트입니다. 일반적으로 보안 컨텍스트는 사용자와 연결되므로 주어진 프로세스에서 실행되는 모든 응용 프로그램은 소유하는 사용자의 권한을 갖습니다.



**security identifier**

(SID) 사용자, 그룹 및 컴퓨터 계정을 식별하는 가변 길이의 데이터 구조입니다. 계정이 처음 생성되면 네트워크의 모든 계정에 고유 한 SID가 발급됩니다. Windows의 내부 프로세스는 계정의 사용자 또는 그룹 이름 대신 계정의 SID를 참조합니다.



**Dynamic Access Control**

(DAC) 사용자, 장치 및 자원 클레임을 기반으로 액세스 제어 정책을 지정할 수있는 기능입니다. 따라서 보안 및 컴플라이언스 요구 사항을 유지하면서 애플리케이션에 대해보다 유연한 인증을 수행 할 수 있습니다.



