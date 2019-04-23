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



### 17053 관련 오류 추가 테스트

SQL 서버는 Microsoft 측의 관계형 데이터베이스 관리 시스템입니다. 이 도구가 제공하는 주요 기능은 데이터 저장 및 검색입니다. SQL 서버는 데이터 (즉, 1 차, 2 차 및 트랜잭션 로그 파일)를 관리하기위한 3 가지 유형의 파일을 유지 관리합니다. 이러한 파일의 확장자는 각각 **.mdf, .ndf** 및 **.ldf**입니다.

파일에서 많은 양의 데이터를 관리하는 동안 오류 발생 가능성이 높습니다. SQL 오류 17053은 SQL Server에서 발생하는 오류 중 하나입니다. Microsoft는 오류 상황을 어느 정도 처리 할 수있는 무료 유틸리티를 제공합니다. DBCC CHECDB 명령은 데이터베이스의 무결성을 검사하여 특정 수준으로 수정합니다. 그러나 SQL 오류 17053을 처리하기위한 오류 및 사전 예방 방법에 대해 자세히 설명합니다.

#### Description Of SQL Error 17053:

SQL Server 오류 17053은 NTFS 파일 시스템과 내부 스냅 샷의 대체 스트림을 나타냅니다. 오류 메시지는 대개 원인의 진정한 이유를 나타냅니다.

**Case 1** (Operating system error665)

오류 코드는 NTFS 파일 시스템에서 파일 크기 제한을 위반했음을 의미합니다. NTFS 파일 시스템의 크기 제한은 거의 Exabyte입니다. 따라서 DBCC CHECKBD를 사용하는 동안이 오류는 VSS (Volume Shadow Copy Services) 크기 제한으로 인해 발생할 수 있습니다. 기본적으로 VSS 스냅 샷은 사용 가능한 드라이브 크기의 10 % 미만일 수 있습니다. 아래에 표시된 것처럼 몇 단계로 관리 할 수 있습니다.

- 컴퓨터 관리에서 디스크 관리 옵션을 선택하십시오.
- 데이터베이스가있는 모든 드라이브에서 등록 정보를 선택하십시오.
- 창 섀도 복사본을 선택한 다음 설정 선택
- '제한 없음'라디오 버튼을 선택하십시오.
- 이 옵션을 사용하면 시스템에서 VSS 제한 사항을 제거하므로 작업을 원활하게 진행할 수 있습니다.

**Case 2:** (DoDevIoCtlOut() DeviceIoControl() : Operating system error 1)

이 오류는 SMB 파일 공유 또는 네트워크 저장 장치를 사용할 때 발생합니다. 아래에 언급 된 데이터베이스 관련 활동을 수행하는 중에 이 오류를 만날 수 있습니다.

- 새 DB를 만들려고 할때.
- 어떤 활동을 수행하기 위해 SQL 서버를 시작하는 동안.
- 데이터베이스를 온라인으로 마킹 할 때.

이 오류는 장치 IO 컨트롤러와의 비 호환성으로 인해 발생할 수 있습니다. SQL 서버는 장치 제어 코드 FSCTL_FILESYSTEM_GET_STATISTICS 를 사용하여 로그 또는 데이터 파일을 만들고 엽니 다. 따라서 솔루션은 Windows API 장치 IO 컨트롤과 호환되는 타사 네트워크 드라이브 또는 SMB 공유를 사용하는 것입니다.

**NOTE:**  

NTFS 파일 시스템을 SQL 데이터 파일과 함께 사용하는 경우 오류는 무시해도되지만 ReFS 파일 시스템을 사용하면 성능 SQL 서버의 성능이 저하됩니다.



다시 시작시 945 오류 발생

- 액세스 할 수 없는 파일 또는 메모리 또는 디스크 공간 부족으로 인해 데이터베이스 '모델'을 열 수 없습니다. 자세한 내용은 SQL Server 오류 로그를 참조하십시오.



---



## 2. SQL Server가 논리적 일관성 기반 I/O 오류를 발견했습니다. 

잘못된 pageid (예상 1 : 363, 실제 1:59). 'C:\\Program Files\\Microsoft SQL Server\\MSSQL14.SQLEXPRESS\\MSSQL\\DATA\\master.mdf' 
파일의 0x000000002d6000 오프셋에서 데이터베이스 ID 1의 페이지 (1 : 363)를 읽는 동안 발생했습니다. 
SQL Server 오류 로그 또는 운영 체제 오류 로그의 추가 메시지가 더 자세한 정보를 제공 할 수 있습니다. 
이는 데이터베이스 무결성을 위협하는 심각한 오류 조건이므로 즉시 수정해야합니다. 
전체 데이터베이스 일관성 검사 (DBCC CHECKDB)를 완료하십시오. 
이 오류는 여러 가지 요인으로 인해 발생할 수 있습니다. 자세한 내용은 SQL Server 온라인 설명서를 참조하십시오.



DBCC CHECKDB  를 이용하여 오류 Check



## 3. MICROSOFT SQL SERVER ERROR CODE 3417

출처: <https://www.sqlrecoverysoftware.net/blog/sql-error-3417.html>

MS SQL Server는 가장 신뢰할 수있는 서버 환경 중 하나로 간주됩니다. 따라서 전 세계의 소규모 및 대규모 조직에서 널리 설치됩니다. 모든 데이터는 MDF 및 NDF 파일 형태로 데이터베이스에 저장됩니다. 하지만 1 차 및 2 차 데이터베이스 파일에서 발생하는 부패의 빈도 또는 불일치 빈도는 낮지 만 필연적 인 것은 아닙니다. 때로는 여러 가지 이유로 인해 사용자의 데이터가 포함 된 기본 및 보조 데이터베이스가 영향을 받으면 MDF 및 NDF 파일에 액세스 할 수 없게되고 오류가 발생합니다. SQL Server DB에서 발생한 오류 중 하나는 SQL 오류 3417입니다.이 SQL 오류에 대해 더 자세히 알아 보겠습니다.

MS SQL Server 오류 코드 3417은 SQL 데이터베이스에 액세스하는 동안 만나는 일반적인 오류 중 하나입니다. 런타임 메시지가 표시됩니다.

```
'Windows could not start the SQL Server (MSSQLSERVER) service on local computer error 3417. For more information review the System Event Log.'
```



### SQL Server 오류 3417이 발생하는 이유는 무엇입니까?

이 오류가 발생하는 이유는 다음과 같습니다.

- 하나의 원인은 프로그램 파일의 데이터 폴더에 대한 네트워크 계정 때문일 수 있습니다.
- 다른 이유는 Windows 설정이 어떻게 든 변경 되었기 때문일 수 있습니다.

**Example:** 어떤 이유로 이 폴더를 이동 한 경우(Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL)다른 위치로 이동 한 다음 동일한 위치로 반환했습니다. 따라서 동일한 위치로 되돌아 갔지만 서버가 작동을 멈추고 다시 시작하려고 할 때 오류 코드 3417이 표시 될 수 있습니다.



### SQL 오류 3417 수정 방법

- Go to "C:\Program Files\Microsoft SQLServer\MSSQL.1MSSqL\Data"
- Security(보안)/Permission(사용 권한) settings
- 네트워크 서비스(Network Service ) 계정 추가
- 그런 다음 다시 확인하십시오.

아래에서 설명하는 방법은 Windows에서 SQL Server 오류 3417을 시작할 수 없다는 문제를 해결할 수 있습니다.

**Note** -  MDF 파일이 압축되어 있지 않은지 확인해야합니다. 파일 등록 정보의 고급 속성에서 '디스크 공간을 절약하기 위해 내용 압축'을 선택 취소하십시오.



### MS SQL Server 오류 코드 3417에 대한 Apt Remedy

위에서 언급 한 수동 솔루션을 사용하여 오류를 수정하지 않으면 데이터베이스가 손상되었을 수 있습니다. 이러한 경우 작업을 방해하지 않으려면 SQL 오류 3417을 제거하는 기능을 포함하여 모든 손상 문제를 억제 할 수있는 제 3 자 솔루션을 사용해야합니다. SQL 복구라는 전문 도구는 이러한 도구 중 하나입니다 SQL Server 데이터베이스를 수정하는 것으로 알려져 있습니다. 소프트웨어를 복구 한 후에도 기본 및 보조 파일을 데이터베이스로 내보낼 수 있습니다.



---

## 4. C ++에서 ACL을 사용하여 권한 정의

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
| **SE_ASSIGNPRIMARYTOKEN_NAME**<br/>TEXT("SeAssignPrimaryTokenPrivilege") | 프로세스의 기본 토큰을 지정하는 데 필요합니다. <br/>사용자 권한 : 프로세스 수준 토큰을 바꿉니다. |
| **SE_AUDIT_NAME**<br/>TEXT("SeAuditPrivilege")               | 감사 로그 항목을 생성하는 데 필요합니다. 보안 서버에이 권한을 부여하십시오. <br/>사용자 권한 : 보안 감사를 생성합니다. |
| <span style="color:blue">**SE_BACKUP_NAME**</span><br/>TEXT("SeBackupPrivilege") | 백업 작업을 수행하는 데 필요합니다.<br/>이 권한은 파일에 대해 지정된 액세스 제어 목록 (ACL)에 관계없이 시스템이 모든 읽기 액세스 제어를 모든 파일에 부여하도록합니다. 읽기 이외의 액세스 요청은 여전히 ACL로 평가됩니다. 이 권한은 RegSaveKey 및 RegSaveKeyExfunctions에 필요합니다. 이 권한이있는 경우 다음 액세스 권한이 부여됩니다.<br/>READ_CONTROL<br/>ACCESS_SYSTEM_SECURITY<br/>FILE_GENERIC_READ<br/>FILE_TRAVERSE<br/>사용자 권한 : 파일 및 디렉토리를 백업할 수 있습니다. |
| **SE_CHANGE_NOTIFY_NAME**<br/>TEXT("SeChangeNotifyPrivilege") | 파일 또는 디렉토리에 대한 변경 사항에 대한 알림을 수신하려면 필수입니다. 또한이 권한으로 인해 시스템은 모든 순회 액세스 검사를 건너 뜁니다. 기본적으로 모든 사용자가 사용할 수 있습니다. <br/>사용자 권한 : 트래버스 확인을 건너 뜁니다. |
| <span style="color:blue">**SE_CREATE_GLOBAL_NAME**</span><br/>TEXT("SeCreateGlobalPrivilege") | 터미널 서비스 세션 중 에 전역 네임 스페이스 에 명명 된 파일 매핑 개체를 만드는 데 필요합니다. 이 권한은 기본적으로 관리자, 서비스 및 로컬 시스템 계정에 대해 사용 가능합니다. <br/>사용자 권한 : 전역 개체를 만듭니다. |
| **SE_CREATE_PAGEFILE_NAME**<br/>TEXT("SeCreatePagefilePrivilege") | 페이징 파일을 만드는 데 필요합니다. <br/>사용자 권한 : 페이지 파일을 만듭니다. |
| **SE_CREATE_PERMANENT_NAME**<br/>TEXT("SeCreatePermanentPrivilege") | 영구 객체를 만드는 데 필요합니다. <br/>User Right : 영구 공유 객체를 만듭니다. |
| **SE_CREATE_SYMBOLIC_LINK_NAME**<br/>TEXT("SeCreateSymbolicLinkPrivilege") | 기호 링크를 작성하는 데 필요합니다. <br/>사용자 권한 : 심볼릭 링크를 생성합니다. |
| **SE_CREATE_TOKEN_NAME**<br/>TEXT("SeCreateTokenPrivilege")  | 기본 토큰을 만드는 데 필요합니다.<br/>사용자 권한 : 토큰 개체를 만듭니다..<br/>토큰 개체 만들기'정책을 사용하여 이 권한을 사용자 계정에 추가 할 수 없습니다. 또한 Windows API를 사용하여 소유 한 프로세스에 이 권한을 추가 할 수 없습니다 .Windows Server 2003 및 Windows XP SP1 및 이전 버전 : Windows API는 소유 한 프로세스에 이 권한을 추가 할 수 있습니다. |
| **SE_DEBUG_NAME**<br/>TEXT("SeDebugPrivilege")               | 다른 계정이 소유 한 프로세스의 메모리를 디버그하고 조정하는 데 필요합니다.<br/>사용자 권한 : 프로그램을 디버그합니다. |
| **SE_DELEGATE_SESSION_USER<br/>_IMPERSONATE_NAME**<br/>TEXT("SeDelegateSessionUser<br/>ImpersonatePrivilege") | 동일한 세션에서 다른 사용자의 가장 (impersonation) 토큰을 얻으려면 필요합니다.<br/>사용자 권한 : 다른 사용자로 가장합니다. |
| **SE_ENABLE_DELEGATION_NAME**<br/>TEXT("SeEnableDelegationPrivilege") | 위임에 대해 신뢰할 수 있는 사용자 및 컴퓨터 계정을 표시하는 데 필요합니다.<br/>사용자 권한 : 컴퓨터 및 사용자 계정을 위임할 수 있도록 설정합니다. |
| <span style="color:blue">**SE_IMPERSONATE_NAME**</span><br/>TEXT("SeImpersonatePrivilege") | 가장 요청<br/>사용자 권한 : 인증 후 클라이언트를 가장합니다. |
| **SE_INC_BASE_PRIORITY_NAME**<br/>TEXT("SeIncreaseBasePriorityPrivilege") | 프로세스의 기본 우선 순위를 높이려면 필요합니다. <br/>사용자 권한 : 프로세스의 메모리 할당량을 조정합니다. |
| **SE_INCREASE_QUOTA_NAME**<br/>TEXT("SeIncreaseQuotaPrivilege") | 프로세스에 할당 된 할당량을 늘리는 데 필요합니다.<br/>사용자 권한 : 프로세스의 메모리 할당량을 조정합니다. |
| **SE_INC_WORKING_SET_NAME**<br/>TEXT("SeIncreaseWorkingSetPrivilege") | 사용자 컨텍스트에서 실행되는 응용 프로그램에 더 많은 메모리를 할당합니다. <br/>사용자 권한 : 프로세스 작업 집합을 늘립니다. |
| **SE_LOAD_DRIVER_NAME**<br/>TEXT("SeLoadDriverPrivilege")    | 장치 드라이버를로드하거나 언로드하는 데 필요합니다.<br/>사용자 권한 : 장치 드라이버 로드 및 언로드. |
| **SE_LOCK_MEMORY_NAME**<br/>TEXT("SeLockMemoryPrivilege")    | 메모리의 물리적 페이지를 잠그는 데 필요합니다.<br/>사용자 권한 : 메모리의 페이지를 잠급니다. |
| **SE_MACHINE_ACCOUNT_NAME**<br/>TEXT("SeMachineAccountPrivilege") | 컴퓨터 계정을 만드는 데 필요합니다.<br/>사용자 권한 : 워크 스테이션을 도메인 에 추가합니다. |
| **SE_MANAGE_VOLUME_NAME**<br/>TEXT("SeManageVolumePrivilege") | 볼륨 관리 권한을 활성화하는 데 필요합니다.<br/>사용자 권한 : 볼륨의 파일을 관리합니다. |
| **SE_PROF_SINGLE_PROCESS_NAME**<br/>TEXT("SeProfileSingleProcessPrivilege") | 단일 프로세스에 대한 프로파일 링 정보를 수집하는 데 필요합니다.<br/>사용자 권한 : 단일 프로세스를 프로파일링합니다. |
| **SE_RELABEL_NAME**<br/>TEXT("SeRelabelPrivilege")           | 객체의 필수 무결성 레벨을 수정하는 데 필요합니다.<br/>사용자 권한 : 개체 레이블을 수정합니다. |
| **SE_REMOTE_SHUTDOWN_NAME**<br/>TEXT("SeRemoteShutdownPrivilege") | 네트워크 요청을 사용하여 시스템을 종료하는 데 필요합니다.<br/>사용자 권한 : 원격 시스템에서 강제로 종료합니다. |
| <span style="color:blue">**SE_RESTORE_NAME**</span><br/>TEXT("SeRestorePrivilege") | 복원 작업을 수행하는 데 필요합니다. 이 권한은 파일에 대해 지정된 ACL에 관계없이 시스템이 모든 쓰기 액세스 제어를 모든 파일에 부여하도록합니다. 쓰기 이외의 모든 액세스 요청은 여전히 ACL로 평가됩니다. 또한이 권한을 통해 유효한 사용자 또는 그룹 SID를 파일의 소유자로 설정할 수 있습니다. 이 권한은 RegLoadKey 함수에서 필요합니다. 이 권한이있는 경우 다음 액세스 권한이 부여됩니다.<br/>WRITE_DAC<br/>WRITE_OWNER<br/>ACCESS_SYSTEM_SECURITY
FILE_GENERIC_WRITE
FILE_ADD_FILE
FILE_ADD_SUBDIRECTORY
DELETE<br/>사용자 권한 : 파일 및 디렉토리를 복원합니다. |
| **SE_SECURITY_NAME**<br/>TEXT("SeSecurityPrivilege")         | 감사 메시지 제어 및 보기와 같은 다양한 보안 관련 기능을 수행하는 데 필요합니다. 이 권한은 소유자를 보안 운영자로 식별합니다.<br/>사용자 권한 : 감사 및 보안 로그를 관리합니다. |
| **SE_SHUTDOWN_NAME**<br/>TEXT("SeShutdownPrivilege")         | 로컬 시스템을 종료하는 데 필요합니다.<br/>사용자 권한 : 시스템을 종료합니다. |
| **SE_SYNC_AGENT_NAME**<br/>TEXT("SeSyncAgentPrivilege")      | 도메인 컨트롤러가 Lightweight Directory Access Protocol 디렉터리 동기화 서비스를 사용하는 데 필요합니다. 이 권한은 소유자가 오브젝트 및 특성에 대한 보호와 상관없이 디렉토리의 모든 오브젝트 및 특성을 읽을 수있게합니다. 기본적으로 도메인의 Administrator 및 LocalSystem 계정에 할당됩니다.<br/>사용자 권한 : 디렉터리 서비스 데이터를 동기화합니다. |
| **SE_SYSTEM_ENVIRONMENT_NAME**<br/>TEXT("SeSystemEnvironmentPrivilege") | 이 유형의 메모리를 사용하여 구성 정보를 저장하는 시스템의 비 휘발성 RAM을 수정하는 데 필요합니다.<br/>사용자 권한 : 펌웨어 환경 값을 수정합니다. |
| **SE_SYSTEM_PROFILE_NAME**<br/>TEXT("SeSystemProfilePrivilege") | 전체 시스템에 대한 프로파일 링 정보를 수집하는 데 필요합니다.<br/>사용자 권한 : 시스템 성능을 프로파일 링합니다. |
| **SE_SYSTEMTIME_NAME**<br/>TEXT("SeSystemtimePrivilege")     | 시스템 시간을 수정하는 데 필요합니다.<br/>사용자 권한 : 시스템 시간을 변경합니다. |
| <span style="color:blue">**SE_TAKE_OWNERSHIP_NAME**</span><br/>TEXT("SeTakeOwnershipPrivilege") | 임의 접근을 허용하지 않고 객체의 소유권을 획득해야합니다. 이 권한은 소유자가 개체의 소유자로 합법적으로 할당 할 수있는 값으로 만 소유자 값을 설정할 수있게합니다.<br/>사용자 권한 : 파일 또는 다른 개체의 소유권을 가져옵니다. |
| <span style="color:blue">**SE_TCB_NAME**</span><br/>TEXT("SeTcbPrivilege") | 이 권한은 소유자를 신뢰할 수있는 컴퓨터 기반의 일부로 식별합니다. 일부 신뢰할 수있는 보호 된 서브 시스템에는이 권한이 부여됩니다.<br/>사용자 권한 : 운영 체제의 일부로 작동합니다. |
| **SE_TIME_ZONE_NAME**<br/>TEXT("SeTimeZonePrivilege")        | 컴퓨터의 내부 클럭과 관련된 표준 시간대를 조정하는 데 필요합니다.<br/>사용자 권한 : 시간대를 변경합니다. |
| **SE_TRUSTED_CREDMAN_ACCESS_NAME**<br/>TEXT("SeTrustedCredManAccessPrivilege") | Credential Manager에 신뢰할 수있는 호출자로 액세스하는 데 필요합니다.<br/>사용자 권한 : Credential Manager에 신뢰할 수있는 호출자로 액세스합니다. |
| **SE_UNDOCK_NAME**<br/>TEXT("SeUndockPrivilege")             | 노트북을 도킹 해제해야합니다.<br/>사용자 권한 : 도킹 스테이션에서 컴퓨터를 제거하십시오. |
| **SE_UNSOLICITED_INPUT_NAME**<br/>TEXT("SeUnsolicitedInputPrivilege") | 터미널 장치에서 원하지 않는 입력을 읽는 데 필요합니다.<br/>사용자 권한 : 해당 사항 없음. |






#### Remarks
권한 상수는 <span style="color:blue">Winnt.h</span> 에서 문자열로 정의됩니다. 예를 들어 SE_AUDIT_NAME 상수는 'SeAuditPrivilege'로 정의됩니다.



---

## 4. FSCTL_FILESYSTEM_GET_STATISTICS 관련 오류 확인

dokany 확인 결과 FSCTL_FILESYSTEM_GET_STATISTICS 를 지원 하지 않는것 으로 보임.

그리고 dokany 에 의해 mount 되어있는 directory 는 NTFS 를 지원하지 않는것 으로 조회됨.

windows 의 심볼릭 링크를 이용하여 테스트를 하면 정상적으로 NTFS 로 나타남

```
mklink /d 링크이름 실제위치
```



아래는 확인 프로그램 소스코드

```c++
/**
 * Windows 특정 Directory 또는 파일이 NTFS volume 인지 확인 확인 하는 프로그램
 *
 * 컴파일  : cl GetStatistics.c
 * 실행예1 : GetStatistics  c:\Temp
 * 실행예2 : GetStatistics  c:\Temp\aaa.txt
 *  
 */

#include <windows.h>
#include <stdio.h>

void Usage(void)
{
	printf("\n");
	printf("Usage: GetStatics dir_name or file_name\n");
	printf("Ex) GetStatics C:\\Temp\n");
	printf("Ex) GetStatics C:\\Temp\\aaa.txt\n");
	exit(0);
}

int wmain(int argc, wchar_t ** argv)
{
    HANDLE 					h;
    FILESYSTEM_STATISTICS 	*fs;
    BYTE 					buffer[32768];
    DWORD 					dw;

	if( argc < 2 )
	    Usage();
	
    h = CreateFileW(argv[1], 0, 
        FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE, 
        NULL, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, NULL);
		
    if (h == INVALID_HANDLE_VALUE)
    {
        printf("CreateFile Last Error code: %u\n", GetLastError());
        return 1;
    }

    if (!DeviceIoControl(h, FSCTL_FILESYSTEM_GET_STATISTICS, NULL, 0, buffer, sizeof(buffer), &dw, NULL))
    {
        dw = GetLastError();
        CloseHandle(h);
        printf("DeviceIoControl: %u\n", dw);
        if (dw == ERROR_INVALID_FUNCTION)
        {
            printf("This probably means the specified file or directory is not on an NTFS volume.\n");
            printf("For example, this happens if you specify a file on a CD-ROM.\n");
        }
        return 1;
    }

    CloseHandle(h);

    fs = (FILESYSTEM_STATISTICS *)buffer;
    printf("Filesystem type: %u\n", fs->FileSystemType);

    if (fs->FileSystemType == FILESYSTEM_STATISTICS_TYPE_NTFS)
    {
        printf("The file or directory is on an NTFS volume.\n");
    }
    else
    {
        printf("The file or directory is not on an NTFS volume.\n");
    }
    return 0;
}
```



## 5. CreateFile 의 dwFlagsAndAttributes



CreateFile 의 인자중 6번째 인자인 DWORD dwFlagsAndAttributes 는 다음과 값들을 조합으로 가질 수 있다.



- ATTRIBUTES

FILE_ATTRIBUTE_ARCHIVE
FILE_ATTRIBUTE_ENCRYPTED
FILE_ATTRIBUTE_HIDDEN
FILE_ATTRIBUTE_NORMAL
FILE_ATTRIBUTE_OFFLINE
FILE_ATTRIBUTE_READONLY
FILE_ATTRIBUTE_SYSTEM
**FILE_ATTRIBUTE_TEMPORARY**

- FLAG

  FILE_FLAG_BACKUP_SEMANTICS
  **FILE_FLAG_DELETE_ON_CLOSE**
  **FILE_FLAG_NO_BUFFERING**
  FILE_FLAG_OPEN_NO_RECALL
  FILE_FLAG_OPEN_REPARSE_POINT
  FILE_FLAG_OVERLAPPED
  FILE_FLAG_POSIX_SEMANTICS
  FILE_FLAG_RANDOM_ACCESS
  FILE_FLAG_SEQUENTIAL_SCAN
  **FILE_FLAG_WRITE_THROUGH**



이중에서 진하게 해놓은 플래그와 속성들에 대해서 몇가지 실험을 해 보았다.

우선, **FILE_FLAG_DELETE_ON_CLOSE**(이하 DELETE_ON_CLOSE) 는 해당 파일에 대해서 참조하고 있는 핸들이 모두 닫혔을때 파일을 디스크상에서 삭제한다. 이 플래그는 CreateFile 에 사용되므로 파일을 생성하고, 생성한 파일을 쓰거나, 읽거나 하다가 마지막으로 CloseHandle() 을 호출하면 해당 파일은 자동적으로 디스크에서 삭제된다.

이것을 이용해서 실행이 종료 되었을때 자동적으로 디스크에서 삭제되는 프로그램을 만들수는 없을까? 

라는 의문을 해결해 보기로 했다. 

1. FILE_FLAG_DELETE_ON_CLOSE 옵션을 주고 CreateFile 로 파일을 생성
2. 실행파일을 Binary 리소스 형태로 프로젝트 내에 포함한 후 위에서 생성한 파일에 WriteFile 로 씀
3. CreateProcess 로 위 1~2 과정에서 만든 Executable 파일을 실행

하였으나, **ERROR_SHARING_VIOLATION** 이라는 오류로 인해 **CreateProcess** 가 성공적으로 호출되지 못했다. 공유 속성을 여러가지로 변경해보고 다시 컴파일 해 보았으나 실패. 아마도 현재 CreateFile 로 핸들을 얻은 파일은 CreateProces 로 새로운 프로세스를 생성하지 못하나 보다.


다음은 **FILE_ATTRIBUTE_TEMPORARY** 속성이다. 이 MSDN의 설명에 따르면 이 속성을 지정하면 파일의 변경 내용을 디스크에 쓰지 않고 시스템 캐시 용량이 허락하는 만큼 메모리상에서 내용을 변경하고 있다가, 핸들이 닫길때 디스크에 저장한다. **FILE_FLAG_DELETE_ON_CLOSE** 와 같이 쓰이면 유용할것 같다는 의견이 있다.


세번째로는 **FILE_FLAG_WRITE_THROUGH** 는 **FILE_FLAG_NO_BUFFERING** 플래그와 같이 쓰여서 파일을 캐싱할 것인지 아닌지를 결정한다. **FILE_FLAG_WRITE_THROUGH** 만 지정되었을 경우에는 시스템 캐시에 내용이 적용 된 후 바로 지연 없이 디스크에 변경 내용을 저장한다. 하지만, **FILE_FLAG_WRITE_THROUGH** 과 **FILE_FLAG_NO_BUFFERING** 모두 지정 되었을 경우에는 시스템 캐시에 내용을 변경하지 않고 바로 디스크에 쓴다. 

읽기의 경우에는 **FILE_FLAG_WRTIE_THROUGH** 만 지정되면 파일의 원본이 아니라 디스크 캐시 등 캐싱 된 곳에서 읽어오나, **FILE_FLAG_NO_BUFFERING** 까지 같이 지정되면 무조건 원본 파일에서 읽어온다.

자세한 내용은 [INFO: FILE_FLAG_WRITE_THROUGH and FILE_FLAG_NO_BUFFERING](http://support.microsoft.com/kb/99794/en-us?fr=1) 를 참조하라.


실행중인 파일의 원본을 삭제하거나, 프로그램 종료 후 자신의 파일을 제거하는 프로그램을 만드는 방법은 더 연구해 봐야 할 것 같다.

