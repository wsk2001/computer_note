# Dokan 을 이용한 MSSQL 암호화 Memo

이 문서는 Dokany 을 이용한 MSSQL Database 파일 암호화 적용시 발생한 문제점을 기록 하여 차후 Dokany 을 이용한 암호화 적용시 참고 하기 위해 작성 되었다.



## 1. Error 17053 타사 네트워크 장치의 SMB 파일 공유 오류

출처: https://sqlmvp.tistory.com/1086

SQL Server 2012부터는 추적플래그를 사용하지 않고 원격 SMB 파일 공유에 데이터베이스를 생성하여 운용할 수 있다. SQL Server는 데이터베이스에 대한 SMB 파일 공유를 사용 하도록 클러스터를 구성할 수 있다.

- SMB 파일 공유와 함께 저장소로 SQL Server 설치 옵션

  <https://msdn.microsoft.com/ko-kr/library/hh759341(v=sql.120).aspx>

 

데이터 로그 파일을 생성하거나 열 때 SQL Server는 다양한 파일 조작 API를 호출한다. Win32 API는 DeviceIoControl을 호출하여 장치 드라이버에 대한 다양한 작업명령을 보낼 수 있다.

최근 SMB 공유에 원격으로 모든 데이터베이스를 저장하도록 구성하였는데 수행 할 때마다 다음과 같은 오류를 수신하였다.

1. SQL Server 다시 시작
2. 새 데이터베이스 생성
3. 온라인 데이터베이스 마킹

`오류`

```
2015-06-04 13:14:19.97 spid9s      Error: 17053, Severity: 16, State: 1. 
2015-06-04 13:14:19.97 spid9s      DoDevIoCtlOut() GetOverlappedResult() : Operating system error 1(Incorrect function.) encountered.
```

디버깅을 통해 확인한 결과 SQL Server는 DeviceIoControl이 장치 제어코드 **FSCTL_FILESYSTEM_GET_STATISTICS** 를 사용하여 데이터를 열거나 로그파일에 대해서 호출한다. 그러나 서드파티의 특정 디바이스 드라이버는 제어코드 **FSCTL_FILESYSTEM_GET_STATISTICS** 를 지원하지 않기 때문에 오류가 발생하였다. 윈도우 파일 공유가 아닌 서드파티 공급업체의 SMB 공유를 사용하기 위해서는 스토리지 공급업체에 확인 해야 한다.



[참고자료]

- DeviceIoControl API

  <https://msdn.microsoft.com/en-us/library/windows/desktop/aa363216(v=vs.85).aspx>

- Device code FSCTL_FILESYSTEM_GET_STATISTICS

  <https://msdn.microsoft.com/en-us/library/windows/desktop/aa364565(v=vs.85).aspx>

- SQL Server support of SMB file share

  <https://msdn.microsoft.com/en-us/library/hh759341(v=sql.120).aspx>

- Error 17053 when using third party network storage device / SMB file share

  <http://blogs.msdn.com/b/psssql/archive/2015/07/21/error-17053-when-using-third-party-network-storage-device-smb-file-share.aspx>



이 오류는 SMB 파일 공유 또는 네트워크 저장 장치를 사용할 때 발생합니다. 아래에 언급 된 데이터베이스 관련 활동을 수행하는 중에이 오류를 만날 수 있습니다.

- 새 DB를 만들려고합니다.
- 어떤 활동을 수행하기 위해 SQL 서버를 시작하는 동안.
- 데이터베이스를 온라인으로 표시.

이 오류는 장치 IO 컨트롤러와의 비 호환성으로 인해 발생할 수 있습니다. SQL 서버는 장치 제어 코드 **FSCTL_FILESYSTEM_GET_STATISTICS** 를 사용하여 로그 또는 데이터 파일을 만들고 엽니 다. 따라서 솔루션은 Windows API 장치 IO 컨트롤과 호환되는 타사 네트워크 드라이브 또는 SMB 공유를 사용하는 것입니다.

**NOTE:**  NTFS 파일 시스템을 SQL 데이터 파일과 함께 사용하는 경우 오류는 무시해도되지만 ReFS 파일 시스템을 사용하면 성능 SQL 서버의 성능이 저하됩니다.



---



## SQL Server가 논리적 일관성 기반 I/O 오류를 발견했습니다. 

잘못된 pageid (예상 1 : 363, 실제 1:59). 'C:\\Program Files\\Microsoft SQL Server\\MSSQL14.SQLEXPRESS\\MSSQL\\DATA\\master.mdf' 
파일의 0x000000002d6000 오프셋에서 데이터베이스 ID 1의 페이지 (1 : 363)를 읽는 동안 발생했습니다. 
SQL Server 오류 로그 또는 운영 체제 오류 로그의 추가 메시지가 더 자세한 정보를 제공 할 수 있습니다. 
이는 데이터베이스 무결성을 위협하는 심각한 오류 조건이므로 즉시 수정해야합니다. 
전체 데이터베이스 일관성 검사 (DBCC CHECKDB)를 완료하십시오. 
이 오류는 여러 가지 요인으로 인해 발생할 수 있습니다. 자세한 내용은 SQL Server 온라인 설명서를 참조하십시오.



DBCC CHECKDB  를 이용하여 오류 Check



---

## C ++에서 ACL을 사용하여 권한 정의

출처:  https://docs.microsoft.com/en-us/windows/desktop/secauthz/defining-permissions-with-acls-in-c--

ACL을 사용하여 보호 된 자원에 대한 액세스를 제어 할 수 있습니다. 해당 자원과 연관된 ACL을 작성 및 수정하고 클라이언트 권한을 활성화 및 비활성화하여 클라이언트가 어떤 자원에 액세스 할 수 있는지 정의하십시오.

| Topic                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [Modifying the ACLs of an Object in C++](https://docs.microsoft.com/en-us/windows/desktop/secauthz/modifying-the-acls-of-an-object-in-c--) | **[액세스 제어 항목](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-control-entry-gly) (ACE)을** 개체의 **DACL ([임의 액세스 제어 목록](https://msdn.microsoft.com/library/windows/desktop/ms721573#-security-discretionary-access-control-list-gly))에 추가** 하거나 제거합니다. |
| [Creating a Security Descriptor for a New Object in C++](https://docs.microsoft.com/en-us/windows/desktop/secauthz/creating-a-security-descriptor-for-a-new-object-in-c--) | 새 개체에 대한 보안 설명자를 만듭니다.                       |
| [Controlling Child Object Creation in C++](https://docs.microsoft.com/en-us/windows/desktop/secauthz/controlling-child-object-creation-in-c--) | 컨테이너 개체의 DACL을 사용하여 컨테이너 내에 자식 개체를 만들 수있는 사용자를 제어합니다. |
| [Enabling and Disabling Privileges in C++](https://docs.microsoft.com/en-us/windows/desktop/secauthz/enabling-and-disabling-privileges-in-c--) | 프로세스가 시스템 수준의 작업을 수행하도록 허용 또는 금지합니다. |



### Enabling and Disabling Privileges in C++

액세스 토큰에서 권한을 사용하면 프로세스가 이전에 할 수 없었던 시스템 수준의 작업을 수행 할 수 있습니다. 응용 프로그램은 특권이 계정 유형, 특히 다음과 같은 강력한 특권에 적합한 지 철저하게 검증해야합니다.

| 특권 상수/string                                             | Display name                  |
| :----------------------------------------------------------- | :---------------------------- |
| SE_ASSIGNPRIMARYTOKEN_NAME<br/>SeAssignPrimaryTokenPrivilege | 프로세스 수준 토큰 바꾸기     |
| SE_BACKUP_NAME<br/>SeBackupPrivilege                         | 파일 및 디렉토리 백업         |
| SE_DEBUG_NAME<br/>SeDebugPrivilege                           | 프로그램 디버그               |
| SE_INCREASE_QUOTA_NAME<br/>SeIncreaseQuotaPrivilege          | 프로세스의 메모리 할당량 조정 |
| SE_TCB_NAME<br/>SeTcbPrivilege                               | 운영 체제의 일부로 작동       |

이러한 위험 가능성이있는 권한을 활성화하기 전에 코드의 기능이나 작업에 실제로 권한이 필요한지 확인하십시오. 예를 들어 운영 체제의 기능 중 SeTcbPrivilege가 필요한 기능은 거의 없습니다. 사용 가능한 모든 권한 목록을 보려면 권한 상수( [Privilege Constants](https://docs.microsoft.com/en-us/windows/desktop/secauthz/authorization-constants))를 참조하십시오.

다음 예에서는 액세스 토큰( [*access token*](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-token-gly))에서 권한을 사용하거나 사용하지 않도록 설정하는 방법을 보여줍니다. 이 예에서는 [LookupPrivilegeValue](https://docs.microsoft.com/en-us/windows/desktop/api/Winbase/nf-winbase-lookupprivilegevaluea) 함수를 호출하여 로컬 시스템에서 권한을 식별하는 데 사용하는 로컬 고유 식별자([*locally unique identifier*](https://msdn.microsoft.com/library/windows/desktop/ms721592#-security-locally-unique-identifier-gly)) (LUID)를 가져옵니다. 그런 다음 예제에서는 [AdjustTokenPrivileges](https://msdn.microsoft.com/en-us/library/Aa375202(v=VS.85).aspx) 함수를 호출합니다.이 함수는 bEnablePrivilege 매개 변수의 값에 따라 권한을 사용하거나 사용하지 않도록 설정합니다.

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



## 권한 상수

권한 부여 상수는 다음과 같이 사용법에 따라 분류됩니다.

## In this section

| Topic                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**Account Rights Constants**](https://docs.microsoft.com/en-us/windows/desktop/secauthz/account-rights-constants) | 계정 권한은 사용자 계정이 수행 할 수있는 로그온 유형을 결정합니다. 관리자는 사용자 및 그룹 계정에 계정 권한을 할당합니다. 각 사용자의 계정 권한에는 사용자 및 사용자가 속한 그룹에 부여 된 권한이 포함됩니다. |
| [**App Container SID Constants**](https://docs.microsoft.com/en-us/windows/desktop/secauthz/app-container-sid-constants) | 응용 프로그램 패키지 권한을 지시하십시오.                    |
| [**Auditing Constants**](https://docs.microsoft.com/en-us/windows/desktop/secauthz/auditing-constants) | 감사 정책 이벤트의 범주와 하위 범주를 나타냅니다.            |
| [**Capability SID Constants**](https://docs.microsoft.com/en-us/windows/desktop/secauthz/capability-sid-constants) | AllocateAndInitializeSid 함수를 사용하여 응용 프로그램에 대해 잘 알려진 기능을 정의하십시오. |
| [**Privilege Constants**](https://docs.microsoft.com/en-us/windows/desktop/secauthz/privilege-constants) | 특권은 사용자 계정이 수행 할 수있는 시스템 조작의 유형을 결정합니다. 관리자는 사용자 및 그룹 계정에 권한을 할당합니다. 각 사용자의 권한에는 사용자 및 사용자가 속한 그룹에 부여 된 권한이 포함됩니다. |





# 권한 상수(Privilege Constants)

출처: https://docs.microsoft.com/en-us/windows/desktop/secauthz/privilege-constants



특권은 사용자 계정이 수행 할 수있는 시스템 조작의 유형을 결정합니다. 관리자는 사용자 및 그룹 계정에 권한을 할당합니다. 각 사용자의 권한에는 사용자 및 사용자가 속한 그룹에 부여 된 권한이 포함됩니다.

[액세스 토큰](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-token-gly)의 권한을 가져 와서 조정하는 함수는 [로컬 고유 식별자](https://msdn.microsoft.com/library/windows/desktop/ms721592#-security-locally-unique-identifier-gly) (LUID) 유형을 사용하여 권한을 식별합니다. [LookupPrivilegeValue](https://docs.microsoft.com/en-us/windows/desktop/api/Winbase/nf-winbase-lookupprivilegevaluea) 함수를 사용하여 로컬 시스템에서 권한 상수에 해당하는 LUID를 확인합니다. [LookupPrivilegeName](https://docs.microsoft.com/en-us/windows/desktop/api/Winbase/nf-winbase-lookupprivilegenamea) 함수를 사용하여 LUID를 해당 문자열 상수로 변환하십시오.

운영 체제는 다음 표의 설명 열에서 '사용자 권한'다음에 나오는 문자열을 사용하여 권한을 나타냅니다. 운영 체제는 로컬 보안 설정 Microsoft 관리 콘솔 (MMC) 스냅인의 사용자 권한 할당 노드의 정책 열에 사용자 권한 문자열을 표시합니다.

| 상수/값                                                      | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| **SE_ASSIGNPRIMARYTOKEN_NAME**<br/>TEXT("SeAssignPrimaryTokenPrivilege") | 프로세스의 기본 토큰을 지정하는 데 필요합니다. 사용자 권한 : 프로세스 수준 토큰을 바꿉니다. |
| **SE_AUDIT_NAME**<br/>TEXT("SeAuditPrivilege")               | 감사 로그 항목을 생성하는 데 필요합니다. 보안 서버에이 권한을 부여하십시오. <br/>사용자 권한 : 보안 감사를 생성합니다. |
| **SE_BACKUP_NAME**<br/>TEXT("SeBackupPrivilege")             | 백업 작업을 수행하는 데 필요합니다. 이 권한은 파일에 대해 지정된 액세스 제어 목록 (ACL)에 관계없이 시스템이 모든 읽기 액세스 제어를 모든 파일에 부여하도록합니다. 읽기 이외의 액세스 요청은 여전히 ACL로 평가됩니다. 이 권한은 RegSaveKey 및 RegSaveKeyExfunctions에 필요합니다. 이 권한이있는 경우 다음 액세스 권한이 부여됩니다.<br/> READ_CONTROL<br/>ACCESS_SYSTEM_SECURITY<br/>FILE_GENERIC_READ<br/>FILE_TRAVERSE<br/>사용자 권한 : 파일 및 디렉토리를 백업할 수 있습니다. |
| **SE_CHANGE_NOTIFY_NAME**<br/>TEXT("SeChangeNotifyPrivilege") | 파일 또는 디렉토리에 대한 변경 사항에 대한 알림을 수신하려면 필수입니다. 또한이 권한으로 인해 시스템은 모든 순회 액세스 검사를 건너 뜁니다. 기본적으로 모든 사용자가 사용할 수 있습니다. <br/>사용자 권한 : 트래버스 확인을 건너 뜁니다. |
| <span style="color:blue">**SE_CREATE_GLOBAL_NAME**</span><br/>TEXT("SeCreateGlobalPrivilege") | 터미널 서비스 세션 중 에 전역 네임 스페이스 에 명명 된 파일 매핑 개체를 만드는 데 필요합니다. 이 권한은 기본적으로 관리자, 서비스 및 로컬 시스템 계정에 대해 사용 가능합니다. <br/>사용자 권한 : 전역 개체를 만듭니다. |
| **SE_CREATE_PAGEFILE_NAME**<br/>TEXT("SeCreatePagefilePrivilege") | 페이징 파일을 만드는 데 필요합니다. <br/>사용자 권한 : 페이지 파일을 만듭니다. |
| **SE_CREATE_PERMANENT_NAME**<br/>TEXT("SeCreatePermanentPrivilege") | 영구 객체를 만드는 데 필요합니다. <br/>User Right : 영구 공유 객체를 만듭니다. |
| **SE_CREATE_SYMBOLIC_LINK_NAME**<br/>TEXT("SeCreateSymbolicLinkPrivilege") | 기호 링크를 작성하는 데 필요합니다. 사용자 권한 : 심볼릭 링크를 생성합니다. |
| **SE_CREATE_TOKEN_NAME**<br/>TEXT("SeCreateTokenPrivilege")  |                                                              |
| **SE_DEBUG_NAME**<br/>TEXT("SeDebugPrivilege")               |                                                              |
| **SE_DELEGATE_SESSION_USER<br/>_IMPERSONATE_NAME**<br/>TEXT("SeDelegateSessionUser<br/>ImpersonatePrivilege") |                                                              |
| **SE_ENABLE_DELEGATION_NAME**<br/>TEXT("SeEnableDelegationPrivilege") |                                                              |
| **SE_IMPERSONATE_NAME**<br/>TEXT("SeImpersonatePrivilege")   |                                                              |
| **SE_INC_BASE_PRIORITY_NAME**<br/>TEXT("SeIncreaseBasePriorityPrivilege") |                                                              |
| **SE_INCREASE_QUOTA_NAME**<br/>TEXT("SeIncreaseQuotaPrivilege") |                                                              |
| **SE_INC_WORKING_SET_NAME**<br/>TEXT("SeIncreaseWorkingSetPrivilege") |                                                              |
| **SE_LOAD_DRIVER_NAME**<br/>TEXT("SeLoadDriverPrivilege")    |                                                              |
| **SE_LOCK_MEMORY_NAME**<br/>TEXT("SeLockMemoryPrivilege")    |                                                              |
| **SE_MACHINE_ACCOUNT_NAME**<br/>TEXT("SeMachineAccountPrivilege") |                                                              |
| **SE_MANAGE_VOLUME_NAME**<br/>TEXT("SeManageVolumePrivilege") |                                                              |
| **SE_PROF_SINGLE_PROCESS_NAME**<br/>TEXT("SeProfileSingleProcessPrivilege") |                                                              |
| **SE_RELABEL_NAME**<br/>TEXT("SeRelabelPrivilege")           |                                                              |
| **SE_REMOTE_SHUTDOWN_NAME**<br/>TEXT("SeRemoteShutdownPrivilege") |                                                              |
| **SE_RESTORE_NAME**<br/>TEXT("SeRestorePrivilege")           |                                                              |
| **SE_SECURITY_NAME**<br/>TEXT("SeSecurityPrivilege")         |                                                              |
| **SE_SHUTDOWN_NAME**<br/>TEXT("SeShutdownPrivilege")         |                                                              |
| **SE_SYNC_AGENT_NAME**<br/>TEXT("SeSyncAgentPrivilege")      |                                                              |
| **SE_SYSTEM_ENVIRONMENT_NAME**<br/>TEXT("SeSystemEnvironmentPrivilege") |                                                              |
| **SE_SYSTEM_PROFILE_NAME**<br/>TEXT("SeSystemProfilePrivilege") |                                                              |
| **SE_SYSTEMTIME_NAME**<br/>TEXT("SeSystemtimePrivilege")     |                                                              |
| **SE_TAKE_OWNERSHIP_NAME**<br/>TEXT("SeTakeOwnershipPrivilege") |                                                              |
| **SE_TCB_NAME**<br/>TEXT("SeTcbPrivilege")                   |                                                              |
| **SE_TIME_ZONE_NAME**<br/>TEXT("SeTimeZonePrivilege")        |                                                              |
| **SE_TRUSTED_CREDMAN_ACCESS_NAME**<br/>TEXT("SeTrustedCredManAccessPrivilege") |                                                              |
| **SE_UNDOCK_NAME**<br/>TEXT("SeUndockPrivilege")             |                                                              |
| **SE_UNSOLICITED_INPUT_NAME**<br/>TEXT("SeUnsolicitedInputPrivilege") |                                                              |






#### Remarks
권한 상수는 <span style="color:blue">Winnt.h</span> 에서 문자열로 정의됩니다. 예를 들어 SE_AUDIT_NAME 상수는 'SeAuditPrivilege'로 정의됩니다.

