# ACL 정리

이 문서는 ACL(access control list) 의 개념 및 활용 방법에 관하여 정리한 문서 이다.



## DACL과 SACL

일반적으로 ACL 이라고만 하면 DACL를 뜻한다.

DACL = discretionary access control list (임의 액세스 제어 목록.)

SACL = security access control list (보안 액세스 제어 목록)

 

DACL에는 ACEs = access control entries 가 있음.

 

둘은 비슷해 보이나 **DACL은 권한을 설정하는 역할** **SACL은 파일이나 폴더 혹은 주변기기 오프젝트에 접근하는 이벤트 발생시 기록**(log)을 할수있다. 따라서 **개발자는 DACL**을 **SA는 SACL**쪽을 더 깊게 공부하게 된다. 따라서 MCSD 트랙에서는 SACL은 공부하지 않고 뭔지만 알면 된다.

 

DACL은 명시된것이 누적되는 구조이다. 무조건 allow 하는쪽으로 합해지지도 않고 무조건 deny하는쪽으로 합해지는 것도 아니고 명백한 명제의 교집합이라고 보면 된다.

 

DACL의 ACE에는 통상적인 것과 special 한것이 있는데, 통상적인 것과 special 한것의 관계는 포함관계이다. 통상적인 것은 Full permission, modify permission ...... read permission 등이 있는데

Full 은 모든 special ACE가 포함된 것, modify는 권한변동에 관련된것 두개만 빠진 나머지 모두 read는 read와 execute 두개만 포함된 것이다.

 

DACL을 수정하는 방법에는 3가지가 있다.

1. 오브젝트를 오른쪽 클릭하여, 속성(혹은 등록정보), 보안탭을 클릭하여

   윗쪽의 user를 선택한 다음 아랫쪽에서 그 user에 맞는 권한을 주면 된다.

 

2. cacl.exe 나 xcacl.exe를 사용한다. (xcacl.exe가 없는 OS는 마소 사이트에서 받을수 있음)

    cacl은 통상적인 권한 부여, 수정, Xcacl은 special한 권한의 부여, 수정을 할수있다.

    다양한 옵션을 쓸수 있는데 /T는 하위폴더 전체포함 /G 유저:F 하면 해당 유저에 Full 퍼미션 Grant 허용 /D 하면 deny 등등이 있다.

 

3. win32 API에서 어플리케이션에서 직접 조정(범위를 벗어나므로 방법이 있다는 것만 알아두면됨)

 

 

이상의 내용은 마소 공식 어플리케이션 보안 MOC의 4장 1절 ACL 부분을 공부하고 책을 보지않은채로 머릿속의 내용을 정리한 것이므로 영어와 한국어가 마구마구 섞여 있음(아직 번역판이 안나와있음) 가장 중요한것은 DACL이 뭔지, 어떤 ACE가 있는지, 어떻게 부여, 수정할수 있는지 아는 것.



## ACL을 이용한 적절한 사용 권한 설정

출처 :  <https://kukuta.tistory.com/154>

### 1. ACL이 중요한 이유는?
ACL(Access Control List)은  개개의 사용자들이 디렉토리나 파일과 같은 특정 시스템 개체에 접근할 수 있는 권한을 설정해 놓은 표이다.

- 공격자(해커)가 리소스에 접근 할 수 있다면 게임 끝이다.

- -  “만약 레지스트리키의 ACL이 Everyone에게 모든 권한 허용으로 설정 되어 있으면, 누구나 해당 레지스트리의 키 데이터를 읽고, 쓰고 심지어는 다른 사람의 접근을 차단 할 수 있다”

- ACL을 이용해 리소스에 대한 접근 권한을 설정 해 두지 않으면 예측하지 못한 다양한 방법 공격이 가능하다.
  ex) 64byte 로 지정된 버퍼에 더 많은 데이터를 써버려 오버런 발생
        대량의 데이터를 읽고 쓰게 강제

### 2. ACL의 구성
ACE(Access Control Entry) 란? ACL의 구성 요소. ACL은 0개 혹은 이상의 ACE들을 가질 수 있고, 각 ACE들은 특정 자원에 대한 접근을 제어하거나 모니터링 할 수 있다.
- Windows NT, 2000, XP와 이후 버젼에서 지원
- 95, 98, Me, CE는 ACL을 지원하지 않음
- NT와 이후 버젼 OS에서는 DACL, SACL 두 가지 지원
  - DACL(Discretionary Access Control List) - 접근 권한 결정
  - SACL(System Access Control List) - 감사 정책 결정
  - 둘은 비슷해 보이나 DACL은 권한을 설정하는 역할. SACL은 파일이나 폴더 혹은 주변 기기 오브젝트에 접근하는 이벤트 발생시 기록(log)을 할 수있음
- DACL로 접근 권한을 제어 하거나, SACL로 감사 할 수 있는 보안 리소스의 예
  <http://technet.microsoft.com/ko-kr/library/bb795082.aspx> 참고

- DACL
  - 각 DACL들은 0개 혹은 그 이상의 ACE(Access Control Entry)들로 구성 됨.
  - ACE는 SID(Security ID)와 SID가 리소스에 대해 가능한 작업들의 설명. 
    두 가지 주요 요소로 구성 된다(그 외에도 여러 요소들이 있다).
  - SID - 사용자, 그룹, 컴퓨터를 나타내는 아이디
  - 거부 ACE - 특정 접근 권한을 거부하는 ACE 
    ex) Everyone에 대해 모든 권한을 거부(owner 빼고 심지어 관리자 조차도 접근 불가)
    **ACL은 항상 거부 ACE로 시작한다. 그렇지 않으면 허용 접근 권한이 지정되지 않는다.**

### 3. 적적한 ACE 선택 방법?
ACL 내의 모든 ACE는 반드시 의미가 있다 - 의미를 모르겠다면 제거한다.

1.  사용할 리소스가 무엇인지 결정
2.  비즈니스에서 정의된 접근 요구 사항을 결정
3.  적합한 접근 제어 기술을 선택한다.
4.  접근 요구사항을 접근 제어 기술로 변환한다.

“주체(subject)는 대상 개체(Object)에게 어떤 행동을 수행할 수 있다”


### 4. ACL 생성
-  Window NT 4에서 ACL 생성

- - EXPLICIT_ACCESS
  - AllocateAndInitializeSid()
  - InitailizeSecurityDescripter()
  - SetSecurityDescriptorDacl(),
    SetSecurityDescriptorOwner(),
    SetSecurityDescriptorGroup(),
    SetSecurityDescriptorSacl()

-  Windows 2000에서 ACL 생성

- - SDDL(Security Descriptor Definition Language) 사용

### 5. ACE 권한 지정 순서
거부 ACE가 다른 ACE 보다 먼저 지정되어야 한다(이렇게 하지 않으면 허용 접근 권한이 지정되지 않는다)

1. 명시적 거부
2. 명시적 허용
3. 부모 컨테이너에서 상속된 거부
4. 부모 컨테이너에서 상속된 허용....

### 6. NULL DACL과 그 밖의 위험한 ACE

 **NULL DACL == 방어하지 않는다**
- Everyone(WRITE_DAC)
  오브젝트의 DACL을 변경할 수 있는 권한. 공격자가 SD에 기술된 ACL 변경으로 자신만 해당 오브젝트에 접근하고 다른 사람은 거부하도록 만들 수 있다.
- Everyone(WRITE_ONWER)
  오브젝트의 소유자를 변경할 수 있는 권한. 공격자가 SD에 기술된 오브젝트 소유자를 변경해 버릴 수 있다.
- Everyone(FILE_ADD_FILE)
  공격자가 새로운 실행 파일을 파일 시스템에 추가 할 수 있다.
- Everyone(DELETE)
  누구나 오브젝트를 삭제 가능하다
- Everyone(FILE_DELETE_CHILD)
  공격자가 디렉토리에 FILE_DELETE_CHILD 권한을 가지면, 차일드 오브젝트에 대한 권한관계 없이 삭제 가능하다.
- Everyon(GENERIC_ALL) 
  NULL DACL과 같다. 

※ 

What Are SD(Security Descriptors) and Access Control Lists? 

...

security descriptors to help control access to the objects. Security descriptors include information about who owns an object, who can access it and in what way, and what types of access are audited. Security descriptors, in turn, contain the access control list (ACL) of an object,...



http://technet.microsoft.com/ko-kr/library/cc783702(WS.10).aspx



### 7. 운영체제에 포함되지 않는 기타 접근 권한 메커니즘

- Net 프레임 워크 역할(role), COM+ 역할
  ※ 역할이란 정책을 강제하기 위해 애플리케이션에서 사용. 
      예를 들면 그룹을 지정하고 해당 그룹에 허용되는 트랜젝션들을 정의(애플리케이션 종속적)
- IP제한
- SQL 서버의 트리거와 SQL 서버의 사용 권한 
  업데이트시 그룹을 검사하여 적절한 권한의 그룹이 아닌 경우 롤백을 구동하는 트리거

출처:  https://kukuta.tistory.com/154 [HardCore in Programming]



## 디렉터리 / 파일의 DACL에서 ACE를 제거하는 방법

출처: <https://www.codeproject.com/questions/362730/remove-an-ace-from-a-dacl>

```c++
void RemoveEveryoneDeny()
{
	SECURITY_DESCRIPTOR *psd;
	DWORD               needed;

	// 보안 설명자가 얼마나 큰지 확인 합니다.
	GetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, NULL, 0, &needed); 
	
	// 메모리 할당
	psd = (SECURITY_DESCRIPTOR*)malloc(needed); 
	
	// 보안 설명자를 작성합니다.
	GetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, psd, needed, &needed); 

	PACL            dacl;
	BOOL            present, defaulted;
	ULONG           entries;
	EXPLICIT_ACCESS *ea;

	GetSecurityDescriptorDacl(psd, &present, &dacl, &defaulted);
	GetExplicitEntriesFromAcl(dacl, &entries, &ea);
	// 명시 적 액세스 배열에서 정보를 저장하려면 필요한 항목을 포함하고 항목에 포함 된 항목의 
	// 수는 포함되며 TRUSTEE 구조에는 사용자 계정의 SID가 포함되므로 잘 알려진 
    // 액세스 인터페이스와 비교할 수 있습니다.
	// 모든 사람이 SID를 사용하거나 LookupAccountSid를 사용하여 이름을 가져 와서 
    // Everyone 문자열과 비교합니다.

	PACL            newacl;
	EXPLICIT_ACCESS ea2 = {0};
	
	// 현재의 모든 acl 권한을 무시합니다.
	ea2.grfAccessMode = SET_ACCESS; 
	
	// 이것은 파일 사용 권한에서 채워집니다. CreateFile을 참조하십시오.
	ea2.grfAccessPermissions = STANDARD_RIGHTS_ALL; 
	ea2.grfInheritance = NO_INHERITANCE;
	
	// 이 값으로 설정해야합니다.
	ea2.Trustee.MultipleTrusteeOperation = NO_MULTIPLE_TRUSTEE; 
	
	// null 이어야 합니다
	ea2.Trustee.pMultipleTrustee = nullptr; 
	
	// 이 말은 ptstrName이 문자열을 가리킨다는 것입니다.
	ea2.Trustee.TrusteeForm = TRUSTEE_IS_NAME; 
	
	// Everyone is the World SID, and is a well known group
	ea2.Trustee.TrusteeType = TRUSTEE_IS_WELL_KNOWN_GROUP;
	
	// The world SID name
	ea2.Trustee.ptstrName = _T("Everyone"); 

	SetEntriesInAcl(1, &ea2, dacl, &newacl);

	SECURITY_DESCRIPTOR sd2;
	InitializeSecurityDescriptor(&sd2, SECURITY_DESCRIPTOR_REVISION);
	SetSecurityDescriptorDacl(&sd2, TRUE, newacl, FALSE);

	SetFileSecurity(_T("test.txt"), DACL_SECURITY_INFORMATION, &sd2);

}
```

비교적 간단한 해결책을 찾았습니다. GetNamedSecurityInfo 또는 GetFileSecurity로 보안 설명자를 가져옵니다. DACL을 추출하고 반복합니다. 이제 플래그와 기타 정보가 일치하는지 확인하고 API DeleteAce를 호출하여 색인 번호를 전달하십시오.

나중에 ACL에서 항목을 추가하기 위해 API 인 AddAce를 사용하기 시작했습니다. 놀랍게도 AddAce를 사용하는 것은 그렇게 간단하지 않습니다. 이유 - ACL에서 ACE의 순서는 구체적입니다. 내가 이해할 수있는 한 사용자 정의 된 ACE가 먼저 배치되어야합니다. 따라서 ACE를 추가 할 때마다 새 ACL을 만들고 새 ACE와 이전 에코를 복사해야했습니다. 나는 여전히 더 나은 방법을 찾고있다. 또는 최소한 ACL에서 ACE를 재 배열 할 수 있습니다. 어떤 제안?



# Security Descriptor

출처: <https://github.com/libyal/libfwnt/wiki/Security-Descriptor>



## Security Descriptor (보안 설명자)

Windows NT 보안 설명자는 다음과 같이 구성됩니다.

- 보안 설명자 헤더
- 소유자 보안 식별자 (SID)
- 그룹 보안 식별자 (SID)
- 임의 액세스 제어 목록 (DACL)
- 시스템 액세스 제어 목록 (SACL)

절대 형식에서는 Windows NT 보안 설명자가 정보 자체가 아닌 정보에 대한 포인터를 포함합니다. 자체 상대적 형식에서 보안 설명자는 보안 설명자와 관련 정보를 인접한 블록에 저장합니다.



### Security descriptor header (보안 설명자 헤더)

보안 설명자 Header는 20 바이트 크기이며 다음으로 구성됩니다.

| Offset | Size | Value | Description                                                  |
| ------ | ---- | ----- | ------------------------------------------------------------ |
| 0      | 1    |       | 개정 번호                                                    |
| 1      | 1    |       | 패딩                                                         |
| 2      | 2    |       | 제어 플래그                                                  |
| 4      | 4    |       | 소유자 SID에 대한 참조.  보안 설명자 머리글의 시작부터 상대적인 오프셋을 포함합니다. |
| 8      | 4    |       | 그룹 SID에 대한 참조. 보안 설명자 머리글의 시작부터 상대적인 오프셋을 포함합니다. |
| 12     | 4    |       | DACL에 대한 참조. 보안 설명자 머리글의 시작부터 상대적인 오프셋을 포함합니다. |
| 16     | 4    |       | SACL에 대한 참조. 보안 설명자 머리글의 시작부터 상대적인 오프셋을 포함합니다. |

```c++
typedef struct _SECURITY_DESCRIPTOR {
  BYTE                        Revision;
  BYTE                        Sbz1;
  SECURITY_DESCRIPTOR_CONTROL Control;
  PSID                        Owner;
  PSID                        Group;
  PACL                        Sacl;
  PACL                        Dacl;
} SECURITY_DESCRIPTOR, *PISECURITY_DESCRIPTOR;
```

제어 플래그는 참조 값의 해석 방법을 결정합니다.



### Security descriptor control flags(보안 설명자 제어 플래그)

| Value  | Identifier               | Description                                                  |
| ------ | ------------------------ | ------------------------------------------------------------ |
| 0x0001 | SE_OWNER_DEFAULTED       | 소유자 기본값<br/>기본 메커니즘에서 보안 설명 자 소유자의 SID를 제공했음을 나타냅니다. 이 플래그는 자원 관리자가 기본 메커니즘으로 소유자가 설정된 오브젝트를 식별하는 데 사용될 수 있습니다. |
| 0x0002 | SE_GROUP_DEFAULTED       | 그룹 기본값<br/> 보안 설명자 그룹의 보안 식별자 (SID)가 기본 메커니즘에서 제공되었음을 나타냅니다. 이 플래그는 리소스 관리자가 기본 메커니즘으로 보안 설명자 그룹을 설정 한 개체를 식별하는 데 사용할 수 있습니다. |
| 0x0004 | SE_DACL_PRESENT          | 현재 DACL<br/>DACL이 있는 보안 설명자를 나타냅니다. 이 플래그가 설정되어 있지 않거나 이 플래그가 설정되어 있고 DACL이 NULL이면 보안 설명자가 모든 사용자에게 전체 액세스 권한을 허용합니다. |
| 0x0008 | SE_DACL_DEFAULTED        | DACL 기본값<br/> 기본 DACL이 있는 보안 설명자를 나타냅니다. 예를 들어 개체 작성자가 DACL을 지정하지 않으면 개체는 작성자의 액세스 토큰에서 기본 DACL을 받습니다. 이 플래그는 시스템에서 ACE 상속과 관련하여 DACL을 처리하는 방법에 영향을 줄 수 있습니다. SE_DACL_PRESENT 플래그가 설정되어 있지 않으면 시스템에서이 플래그를 무시합니다. |
| 0x0010 | SE_SACL_PRESENT          | 현재 SACL<br/> SACL이있는 보안 설명자를 나타냅니다.          |
| 0x0020 | SE_SACL_DEFAULTED        | SACL 기본값<br/> 보안 설명 자의 원래 공급자가 아닌 기본 메커니즘이 SACL을 제공했습니다. 이 플래그는 ACE 상속과 관련하여 시스템이 SACL을 처리하는 방법에 영향을 줄 수 있습니다. SE_SACL_PRESENT 플래그가 설정되어 있지 않으면 시스템은이 플래그를 무시합니다. |
|        |                          |                                                              |
| 0x0100 | SE_DACL_AUTO_INHERIT_REQ | DACL 자동 상속 요청                                          |
| 0x0200 | SE_SACL_AUTO_INHERIT_REQ | SACL 자동 상속 요청                                          |
| 0x0400 | SE_DACL_AUTO_INHERITED   | DACL 자동 상속<br/>상속 가능한 ACE를 기존 자식 개체로 자동 전파하도록 SACL을 설정하는 보안 설명자를 나타냅니다. |
| 0x0800 | SE_SACL_AUTO_INHERITED   | SACL 자동 상속<br/>상속 가능한 ACE를 기존 자식 개체로 자동 전파하도록 SACL을 설정하는 보안 설명자를 나타냅니다. |
| 0x1000 | SE_DACL_PROTECTED        | DACL 보호<br/>상속 가능한 ACE에 의해 보안 설명 자의 DACL이 수정되지 않도록합니다. |
| 0x2000 | SE_SACL_PROTECTED        | SACL 보호<br/>상속 가능한 ACE에 의해 보안 설명 자의 SACL이 수정되지 않도록합니다. |
| 0x4000 | SE_RM_CONTROL_VALID      | RM (Resource Manager) 컨트롤이 유효합니다.                   |
| 0x8000 | SE_SELF_RELATIVE         | Self Relative 자기 상대적 보안 설명자를 나타냅니다.          |



### Security identifier (보안 식별자)

보안 식별자 (SID)는 Windows NT 기반 소프트웨어 전체에서 사용됩니다. SID는 일반적으로 다음과 같이 문자열로 표시됩니다.

```
S-1-5-21-7623811015-3361044348-030300820-1013
```

보안 식별자의 2 진 표현은 크기가 다양합니다.

| Offset | Size       | Value | Description                                                  |
| ------ | ---------- | ----- | ------------------------------------------------------------ |
| 0      | 1          |       | Revision number<br/>이 값은 SID 문자열의 첫 번째 숫자와 일치합니다. |
| 1      | 1          |       | 하위 권한 수                                                 |
| 2      | 6          |       | Authority<br/>48 비트 big-endian 값 포함 이 값은 SID 문자열의 두 번째 숫자와 일치합니다. |
| 8      | 4 x number |       | 32 비트 리틀 엔디안 값의 배열<br/>이 권한은 SID 문자열의 나머지 값과 일치합니다 |

문자열 표현의 'S'는 2 진 표현으로 저장되지 않습니다.





### Access control list (ACL)

DACL과 SACL은 모두 액세스 제어 목록 (ACL)이라고하는 동일한 데이터 구조에 저장됩니다.

액세스 제어 목록 헤더는 8 바이트 크기이며 다음으로 구성됩니다.

| Offset | Size | Value | Description |
| ------ | ---- | ----- | ----------- |
| 0      | 1    |       | Revision    |
| 1      | 1    |       | Padding     |
| 2      | 2    |       | Size        |
| 4      | 2    |       | Count       |
| 6      | 2    |       | Padding     |

액세스 제어 목록 헤더 다음에 ACE (액세스 제어 항목)가옵니다.



### Access control entry (ACE)

액세스 제어 항목 헤더는 4 바이트 크기이며 다음으로 구성됩니다.

| Offset | Size | Value | Description |
| ------ | ---- | ----- | ----------- |
| 0      | 1    |       | Type        |
| 1      | 1    |       | Flags       |
| 2      | 2    |       | Size        |

액세스 제어 항목 (ACE) 헤더 다음에 액세스 항목 데이터가옵니다. ACE 데이터의 크기와 형식은 플래그에 따라 다릅니다.



### Access control entry (ACE) types

| Value | Identifier                              | Description                                                  |
| ----- | --------------------------------------- | ------------------------------------------------------------ |
| 0x00  | ACCESS_ALLOWED_ACE_TYPE                 | 액세스 허용 (Basic ACE data structure)                       |
| 0x01  | ACCESS_DENIED_ACE_TYPE                  | 액세스 불가 (Basic ACE data structure)                       |
| 0x02  | SYSTEM_AUDIT_ACE_TYPE                   | 시스템 감사 (Basic ACE data structure)                       |
| 0x03  | SYSTEM_ALARM_ACE_TYPE                   | 예약 됨 (시스템 경보) (기본 ACE 데이터 구조) ACL 개정 번호 2에서 지원되는 ACE 유형의 최대 수 |
| 0x04  | ACCESS_ALLOWED_COMPOUND_ACE_TYPE        | 예약 됨 (알 수없는 데이터 구조) ACL 개정 번호 3에서 지원되는 ACE 유형의 최대 수 |
| 0x05  | ACCESS_ALLOWED_OBJECT_ACE_TYPE          | 액세스 허용 (Object ACE 데이터 구조)                         |
| 0x06  | ACCESS_DENIED_OBJECT_ACE_TYPE           | 액세스 불가 (Object ACE 데이터 구조)                         |
| 0x07  | SYSTEM_AUDIT_OBJECT_ACE_TYPE            | 시스템 감사 (Object ACE 데이터 구조)                         |
| 0x08  | SYSTEM_ALARM_OBJECT_ACE_TYPE            | 예약 됨 (시스템 경보) (객체 데이터 구조) ACL 개정 번호 4에서 지원되는 ACE 유형의 최대 수 |
| 0x09  | ACCESS_ALLOWED_CALLBACK_ACE_TYPE        | 액세스 허용 (Basic ACE data structure)                       |
| 0x0a  | ACCESS_DENIED_CALLBACK_ACE_TYPE         | 액세스 불가 (Basic ACE data structure)                       |
| 0x0b  | ACCESS_ALLOWED_CALLBACK_OBJECT_ACE_TYPE | 액세스 허용 (Object ACE data structure)                      |
| 0x0c  | ACCESS_DENIED_CALLBACK_OBJECT_ACE_TYPE  | 액세스 불가 (Object ACE data structure)                      |
| 0x0d  | SYSTEM_AUDIT_CALLBACK_ACE_TYPE          | 시스템 감사 (Basic ACE data structure)                       |
| 0x0e  | SYSTEM_ALARM_CALLBACK_ACE_TYPE          | 예약 됨 (시스템 경보) (Basic ACE data structure)             |
| 0x0f  | SYSTEM_AUDIT_CALLBACK_OBJECT_ACE_TYPE   | 시스템 감사 (Object ACE data structure)                      |
| 0x10  | SYSTEM_ALARM_CALLBACK_OBJECT_ACE_TYPE   | 예약 됨 (시스템 경보) (객체 ACE 데이터 구조) ACL 개정 번호 5에서 지원되는 ACE 유형의 최대 수 |
| 0x11  | SYSTEM_MANDATORY_LABEL_ACE_TYPE         | 필수 라벨 (Basic ACE data structure)                         |



### Basic ACE data structure(기본 ACE 데이터 구조)

기본 ACE 데이터 구조는 크기가 다양하며 다음과 같이 구성됩니다.

| Offset | Size | Value | Description                       |
| ------ | ---- | ----- | --------------------------------- |
| 0      | 4    |       | Access rights flags (ACCESS_MASK) |
| 4      | …    |       | SID                               |



### Object ACE data structure(개체 ACE 데이터 구조)

| Offset | Size | Value | Description                                        |
| ------ | ---- | ----- | -------------------------------------------------- |
| 0      | 4    |       | Access rights flags (ACCESS_MASK)                  |
| 4      | 4    |       | Flags                                              |
| 8      | 16   |       | 객체 유형 클래스 식별자 GUID를 포함합니다.         |
| 24     | 16   |       | 상속 된 개체 형식 클래스 식별자 GUID를 포함합니다. |
| 40     | …    |       | SID                                                |



### Access control entry (ACE) flags

#### Access flags

| Value | Identifier               | Description                                                  |
| ----- | ------------------------ | ------------------------------------------------------------ |
| 0x01  | OBJECT_INHERIT_ACE       | 비 컨테이너 자식 개체는 ACE를 유효한 ACE로 상속합니다.       |
| 0x02  | CONTAINER_INHERIT_ACE    | 디렉터리와 같은 컨테이너 인 자식 개체는 ACE를 유효한 ACE로 상속합니다. NO_PROPAGATE_INHERIT_ACE 비트 플래그도 설정되어 있지 않으면 상속 된 ACE가 상속됩니다. |
| 0x04  | NO_PROPAGATE_INHERIT_ACE | ACE가 자식 개체에 상속되면 시스템은 상속 된 ACE에서 OBJECT_INHERIT_ACE 및 CONTAINER_INHERIT_ACE 플래그를 지 웁니다. 이렇게하면 ACE가 후속 세대의 개체에 상속되지 않습니다. |
| 0x08  | INHERIT_ONLY_ACE         | ACE가 연결된 개체에 대한 액세스를 제어하지 않는 상속 전용 ACE를 나타냅니다. 이 플래그가 설정되지 않은 경우 ACE는 ACE가 연결된 개체에 대한 액세스를 제어합니다. |

#### Audit flags

| Value | Identifier                 | Description                                                  |
| ----- | -------------------------- | ------------------------------------------------------------ |
| 0x40  | SUCCESSFUL_ACCESS_ACE_FLAG | 성공적인 액세스 시도에 대한 감사 메시지를 생성하기 위해 SACL의 시스템 감사 ACE와 함께 사용됩니다. |
| 0x80  | FAILED_ACCESS_ACE_FLAG     | 시스템 액세스 제어 목록 (SACL)의 시스템 감사 ACE와 함께 사용되어 실패한 액세스 시도에 대한 감사 메시지를 생성합니다. |



### Access rights flags (ACCESS_MASK)

#### Standard access rights flags

| Value      | Identifier          | Description                                        |
| ---------- | ------------------- | -------------------------------------------------- |
| 0x00010000 | fsdrightDelete      | Delete                                             |
| 0x00020000 | fsdrightReadControl | Read control                                       |
| 0x00040000 | fsdrightWriteSD     | Write DAC<br/> DAC ⇒ discretionary access control? |
| 0x00080000 | fsdrightWriteOwner  | Write owner                                        |
| 0x00100000 | fsdrightSynchronize | Synchronize                                        |

#### Non-folder item access rights flags

| Value      | Identifier                                      | Description                                              |
| ---------- | ----------------------------------------------- | -------------------------------------------------------- |
| 0x00000001 | fsdrightReadBody (FILE_READ_DATA)               |                                                          |
| 0x00000002 | fsdrightWriteBody (FILE_WRITE_DATA)             |                                                          |
| 0x00000004 | fsdrightAppendMsg                               | Ignored                                                  |
| 0x00000008 | fsdrightReadProperty (FILE_READ_EA)             |                                                          |
| 0x00000010 | fsdrightWriteProperty (FILE_WRITE_EA)           |                                                          |
| 0x00000020 | fsdrightExecute (FILE_EXECUTE)                  | Ignored                                                  |
|            |                                                 |                                                          |
| 0x00000080 | fsdrightReadAttributes (FILE_READ_ATTRIBUTES)   |                                                          |
| 0x00000100 | fsdrightWriteAttributes (FILE_WRITE_ATTRIBUTES) |                                                          |
| 0x00000200 | fsdrightWriteOwnProperty                        | 수취인은 자신의 항목을 수정할 수 있습니다 Exchange 특정. |
| 0x00000400 | fsdrightDeleteOwnItem                           | 수취인은 자신의 항목을 삭제할 수 있습니다 Exchange 특정  |
| 0x00000800 | fsdrightViewItem                                | 수탁자는 항목을 볼 수 있습니다.<br/> Exchange specific   |

모든 비 폴더 액세스 권한 : 0x001f0fbf

#### Folder item access rights flags

| Value      | Identifier                                      | Description                                                  |
| ---------- | ----------------------------------------------- | ------------------------------------------------------------ |
| 0x00000001 | fsdrightListContents (FILE_LIST_DIRECTORY)      | 수탁자는 파일 내용을 나열 할 수 있습니다.                    |
| 0x00000002 | fsdrightCreateItem (FILE_ADD_FILE)              | 수탁자는 파일을 폴더에 추가 할 수 있습니다.                  |
| 0x00000004 | fsdrightCreateContainer (FILE_ADD_SUBDIRECTORY) | 수탁자는 하위 폴더를 추가 할 수 있습니다.                    |
| 0x00000008 | fsdrightReadProperty (FILE_READ_EA)             |                                                              |
| 0x00000010 | fsdrightWriteProperty (FILE_WRITE_EA)           |                                                              |
|            |                                                 |                                                              |
| 0x00000080 | fsdrightReadAttributes (FILE_READ_ATTRIBUTES)   | Reserved for future use                                      |
| 0x00000100 | fsdrightWriteAttributes (FILE_WRITE_ATTRIBUTES) | Reserved for future use                                      |
| 0x00000200 | fsdrightWriteOwnProperty                        | 수탁자는 자신의 항목을 수정할 수 있습니다. Exchange specific |
| 0x00000400 | fsdrightDeleteOwnItem                           | 수탁자는 자신의 항목을 삭제할 수 있습니다.                   |
| 0x00000800 | fsdrightViewItem                                | 수탁자는 항목을 볼 수 있습니다.  Exchange specific           |
|            |                                                 |                                                              |
| 0x00004000 | fsdrightOwner                                   | 수탁자는 사람이 폴더의 소유자입니다. Exchange 특정 이 권한은 이전 버전의 Exchange에서 frightsOwner 액세스 권한에 해당합니다. |
| 0x00008000 | fsdrightContact                                 | 사용자를 폴더의 연락처로 식별합니다. <br/>특정 Exchange <br/>이 권한은 이전 버전의 Exchange에서 frightsContact 액세스 권한에 해당합니다. |

모든 폴더 액세스 권한 : 0x00000fbf

#### Mandatory label access rights flags

| Value      | Identifier                           | Description                                                  |
| ---------- | ------------------------------------ | ------------------------------------------------------------ |
| 0x00000001 | SYSTEM_MANDATORY_LABEL_NO_WRITE_UP   | 개체보다 낮은 수준의 기본 수준을 가진 주체는 개체에 쓸 수 없습니다. |
| 0x00000002 | SYSTEM_MANDATORY_LABEL_NO_READ_UP    | 개체보다 낮은 수준의 기본 수준을 가진 주체는 개체를 읽을 수 없습니다. |
| 0x00000004 | SYSTEM_MANDATORY_LABEL_NO_EXECUTE_UP | 개체보다 낮은 수준의 기본 수준을 가진 주체는 개체를 실행할 수 없습니다. |

SYSTEM_MANDATORY_LABEL_NO_READ_UP을 제거하기 위해 다음 코드를 사용할 수 있습니다 :

```c++
// hProcess must have READ_CONTROL | WRITE_OWNER 
ULONG RemoveNoReadUp(HANDLE hProcess = NtCurrentProcess())
{
    union {
        PVOID buf;
        PSECURITY_DESCRIPTOR pSD;
    };

    ULONG cb = 0, rcb = 128;

    static volatile UCHAR guz = 0;
    PVOID stack = alloca(guz);

    ULONG err;
    do 
    {
        if (cb < rcb)
        {
            cb = RtlPointerToOffset(buf = alloca(rcb - cb), stack);
        }

        if (GetKernelObjectSecurity(hProcess, LABEL_SECURITY_INFORMATION, pSD, cb, &rcb))
        {
            BOOL bPresent, bDefault;
            PACL Acl;

            if (!GetSecurityDescriptorSacl(pSD, &bPresent, &Acl, &bDefault))
            {
                return GetLastError();
            }

            if (bPresent)
            {
                if (DWORD AceCount = Acl->AceCount)
                {
                    union {
                        PVOID pv;
                        PBYTE pb;
                        PACE_HEADER pAH;
                        PSYSTEM_MANDATORY_LABEL_ACE pMLA;
                    };

                    pv = Acl + 1;
                    do 
                    {
                        if (pAH->AceType == SYSTEM_MANDATORY_LABEL_ACE_TYPE)
                        {
                            if (pMLA->Mask & SYSTEM_MANDATORY_LABEL_NO_READ_UP)
                            {
                                pMLA->Mask &= ~SYSTEM_MANDATORY_LABEL_NO_READ_UP;

                                return SetKernelObjectSecurity(hProcess, LABEL_SECURITY_INFORMATION, pSD) 
                                    ? NOERROR : GetLastError();
                            }
                        }
                    } while (pb += pAH->AceSize, --AceCount);
                }
            }
            return ERROR_NOT_FOUND;
        }

    } while ((err = GetLastError()) == ERROR_INSUFFICIENT_BUFFER);

    return err;
}
```



# Access Control Model

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/access-control-model>

액세스 제어 모델을 사용하면 프로세스가 보안 개체에 액세스하거나 다양한 시스템 관리 작업을 수행하는 기능을 제어 할 수 있습니다.

다음 주제는 액세스 제어 모델의 파트와 이들이 서로 상호 작용을 개괄적으로 설명합니다.

- [1.액세스 제어 모델의 일부](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/access-control-components)
- [2.스레드와 보안 개체 간의 상호 작용](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/interaction-between-threads-and-securable-objects)
- [3.DACL 및 ACE](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/dacls-and-aces)
- [4.Null DACL 및 Empty DACL](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/null-dacls-and-empty-dacls)
- [5.익명 액세스 허용](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/allowing-anonymous-access)





## 3. DACL 및 ACE

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/dacls-and-aces>

Windows 개체에 DACL ([임의 액세스 제어 목록](https://msdn.microsoft.com/library/windows/desktop/ms721573#-security-discretionary-access-control-list-gly))이 없으면 시스템의 모든 사람이 액세스 할 수 있습니다. 개체에 DACL이있는 경우 시스템은 DACL의 ACE [(액세스 제어 항목](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-control-entry-gly))에서 명시적으로 허용 한 액세스 만 허용합니다. DACL에 ACE가 없으면 시스템에서 다른 사용자의 액세스를 허용하지 않습니다. 마찬가지로 DACL에 제한된 사용자 또는 그룹 집합에 대한 액세스를 허용하는 ACE가있는 경우 시스템은 ACE에 포함되지 않은 모든 트러스트를받는 사람에 대한 액세스를 암시 적으로 거부합니다.

대부분의 경우 액세스 허용 ACE를 사용하여 개체에 대한 액세스를 제어 할 수 있습니다. 객체에 대한 액세스를 명시 적으로 거부 할 필요가 없습니다. ACE가 그룹에 대한 액세스를 허용하고 그룹 구성원에 대한 액세스를 거부하려는 경우는 예외입니다. 이렇게하려면 그룹에 대한 액세스 허용 ACE보다 먼저 DACL에 액세스 거부 ACE를 배치하십시오. 액세스가 허용되거나 거부 될 때까지 시스템에서 ACE를 순서대로 읽으므로 ACE 순서가 중요합니다. 사용자의 액세스 거부 ACE가 먼저 나타나야합니다. 그렇지 않으면 시스템에서 그룹의 액세스 허용 ACE를 읽으면 제한된 사용자에게 액세스 권한을 부여합니다.

다음 그림에서는 한 사용자에 대한 액세스를 거부하고 두 그룹에 대한 액세스 권한을 부여하는 DACL을 보여줍니다. A 그룹의 구성원은 A 그룹에 허용 된 권한과 Everyone에게 허용 된 권한을 누적하여 읽기, 쓰기 및 실행 권한을 얻습니다. 예외는 Andrew입니다. Andrew는 Everyone 그룹의 구성원 임에도 불구하고 액세스가 거부 된 ACE의 액세스를 거부합니다.

![](.\images\accctrl1.png)





---

### LookupAccountSidA 함수

LookupAccountSid 함수는 보안 식별자 (SID)를 입력으로 받아들입니다. 이 SID에 대한 계정 이름과 이 SID가있는 첫 번째 도메인의 이름을 검색합니다.



#### Syntax

```c++
BOOL LookupAccountSidA(
  LPCSTR        lpSystemName,
  PSID          Sid,
  LPSTR         Name,
  LPDWORD       cchName,
  LPSTR         ReferencedDomainName,
  LPDWORD       cchReferencedDomainName,
  PSID_NAME_USE peUse
);
```



#### Parameters

`lpSystemName`

대상 컴퓨터를 지정하는 널 (NULL) 종료. 문자열에 대한 포인터. 이 문자열은 원격 컴퓨터의 이름이 될 수 있습니다. 이 매개 변수가 **NULL이면** 계정 이름 변환이 **로컬 시스템**에서 시작됩니다. 로컬 시스템에서 이름을 확인할 수없는 경우 이 함수는 로컬 시스템에서 신뢰하는 도메인 컨트롤러를 사용하여 이름을 확인하려고합니다. 일반적으로 계정이 트러스트되지 않은 도메인에 있고 해당 도메인의 컴퓨터 이름을 알고있는 경우에만 lpSystemName 값을 지정하십시오.



`Sid`

검색 할 SID에 대한 포인터입니다.



`Name`

lpSid 매개 변수에 해당하는 **계정 이름**이 들어있는 널 (NULL) 종료 **문자열**을 수신하는 버퍼에 대한 포인터.



`cchName`

입력시, lpName 버퍼의 크기 (TCHAR 단위)를 지정합니다. 버퍼가 너무 작거나 cchName이 0이기 때문에 함수가 실패하면 cchName은 종료 널 문자를 포함하여 필요한 버퍼 크기를받습니다.



`ReferencedDomainName`

계정 이름이있는 **도메인의 이름**을 포함하는 null로 끝나는 **문자열**을받는 버퍼에 대한 포인터.

서버에서 로컬 컴퓨터의 보안 데이터베이스에있는 대부분의 계정에 대해 반환 된 도메인 이름은 해당 서버가 도메인 컨트롤러 인 도메인의 이름입니다.

워크 스테이션에서 로컬 컴퓨터의 보안 데이터베이스에있는 대부분의 계정에 대해 반환 된 도메인 이름은 시스템의 마지막 시작 시점에서 백 슬래시가 제외 된 컴퓨터 이름입니다. 컴퓨터 이름이 변경되면 시스템이 다시 시작될 때까지 이전 이름이 도메인 이름으로 계속 반환됩니다.

일부 계정은 시스템에 의해 사전 정의됩니다. 이 계정에 대해 반환 된 도메인 이름은 BUILTIN입니다.



`cchReferencedDomainName`

입력시 lpReferencedDomainName 버퍼의 크기 (TCHAR)를 지정합니다. 버퍼가 너무 작거나 cchReferencedDomainName이 0이기 때문에 함수가 실패하면 cchReferencedDomainName은 종료 널 문자를 포함하여 필요한 버퍼 크기를받습니다.



`peUse`

계정의 유형을 나타내는 SID_NAME_USE 값을받는 변수에 대한 포인터입니다.



#### Return Value

함수가 성공하면 함수는 0이 아닌 값을 반환합니다.

함수가 실패하면 0을 반환합니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

LookupAccountSid 함수는 먼저 잘 알려진 SID 목록을 검사하여 지정된 SID의 이름을 찾습니다. 제공된 SID가 잘 알려진 SID와 일치하지 않으면이 함수는 기본 제공 및 관리 방식으로 정의 된 로컬 계정을 확인합니다. 그런 다음이 함수는 기본 도메인을 확인합니다. 기본 도메인에서 인식하지 못하는 보안 식별자는 SID 접두어에 해당하는 트러스트 된 도메인과 비교하여 검사됩니다.

함수가 SID에 대한 계정 이름을 찾을 수 없으면 GetLastError는 ERROR_NONE_MAPPED를 반환합니다. 네트워크 시간 초과로 인해 함수가 이름을 찾지 못하는 경우 이 문제가 발생할 수 있습니다. 또한 로그온 세션을 식별하는 로그온 SID와 같이 해당 계정 이름이없는 SID에 대해서도 발생합니다.

LookupAccountSid는 로컬 계정, 로컬 도메인 계정 및 명시 적으로 트러스트 된 도메인 계정에 대한 SID를 찾는 것 외에도 포리스트의 계정의 SIDhistory 필드에만 나타나는 SID를 비롯하여 포리스트의 모든 도메인에있는 모든 계정에 대한 SID를 조회 할 수 있습니다 . SIDhistory 필드에는 다른 도메인에서 이전 된 계정의 이전 SID가 저장됩니다. LookupAccountSid는 SID를 조회하기 위해 포리스트의 글로벌 카탈로그를 쿼리합니다.



##### Examples

이 함수를 사용하는 예제는 [액세스 토큰에서 SID 검색](https://msdn.microsoft.com/f895dfef-75ad-419c-95d0-6480bdf9c769)을 참조하십시오.



---

### GetAce 함수

GetAce 함수는 액세스 제어 목록 (ACL)에서 ACE (Access Control Entry)에 대한 포인터를 가져옵니다.

#### Syntax

```c++
BOOL GetAce(
  PACL   pAcl,
  DWORD  dwAceIndex,
  LPVOID *pAce
);
```

#### Parameters

`pAcl`

검색 할 ACE가 들어있는 ACL에 대한 포인터입니다.

`dwAceIndex`

검색 할 ACE의 인덱스입니다. 값 0은 ACL의 첫 번째 ACE에 해당하고, 두 번째 ACE에 대한 값은 1 등입니다.

`pAce`

함수가 ACE의 주소로 설정하는 포인터에 대한 포인터입니다.

#### Return Value

함수가 성공하면 함수는 0이 아닌 값을 반환합니다.

함수가 실패하면 0을 반환합니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



---

### GetFileSecurity 함수

GetFileSecurity 함수는 파일이나 디렉터리의 보안에 대해 지정된 정보를 가져옵니다. 얻은 정보는 호출자의 액세스 권한과 [특권](https://msdn.microsoft.com/2fe6cfd3-8a2e-4dbe-9fb8-332633daa97a)에 의해 제한됩니다.

[GetNamedSecurityInfo](https://msdn.microsoft.com/11f2119b-5314-4fa1-8016-9c01f79d037d) 함수는 GetFileSecurity와 비슷한 기능을 파일 및 다른 유형의 개체에 제공합니다.

<span style="color:green">**GetFileSecurity 함수는 PSECURITY_DESCRIPTOR 를 받아서 사용하기 위해 호출 하는데, 실제 Data 를 받기 전에 먼져 size 를 확인 한 후 Data 를 받아야 하므로  GetFileSecurity 함수를 두번 호출 하여야 한다.**</span>

`예`

```c++
DWORD dwSize =0;
DWORD dwBytesNeeded =0;

// size 확인
GetFileSecurity (strFile,DACL_SECURITY_INFORMATION,NULL,NULL,&dwBytesNeeded);

dwSize = dwBytesNeeded;
PSECURITY_DESCRIPTOR* secDesc = (PSECURITY_DESCRIPTOR*)LocalAlloc(LMEM_FIXED,dwBytesNeeded);

// Data 추출.
if(GetFileSecurity (strFile,DACL_SECURITY_INFORMATION,secDesc,dwSize,&dwBytesNeeded) == false)
{
	printf("파일 시스템 개체 보안을 쿼리하지 못했습니다. - %d\n",GetLastError());
	LocalFree(secDesc);
	return false;
}

// LocalAlloc 으로 할당 받은 메모리는 LocalFree 를 이용하여 free 할것.
```

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

`lpFileName`

보안 정보가 검색되는 파일 또는 디렉토리를 지정하는 널 (null)로 끝나는 문자열에 대한 포인터.



`RequestedInformation`

요청되는 보안 정보를 식별하는 SECURITY_INFORMATION 값.



`pSecurityDescriptor`

lpFileName 매개 변수로 지정된 개체의 보안 설명 자의 복사본을받는 버퍼에 대한 포인터입니다. 호출 프로세스에는 개체 보안 상태의 지정된 부분을 볼 수있는 권한이 있어야합니다. SECURITY_DESCRIPTOR 구조체는 자체 상대적 보안 설명자 형식으로 반환됩니다.



`nLength`

pSecurityDescriptor 매개 변수가 가리키는 버퍼의 크기 (바이트)를 지정합니다.



`lpnLengthNeeded`

전체 보안 설명자를 저장하는 데 필요한 바이트 수를받는 변수에 대한 포인터입니다. 반환 된 바이트 수가 nLength보다 작거나 같으면 전체 보안 설명자가 출력 버퍼에 반환됩니다. 그렇지 않은 경우에는 설명자가 반환되지 않습니다.



#### Return Value

함수가 성공하면 반환 값은 0이 아닙니다. 함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### Remarks

지정된 파일이나 디렉터리의 보안 설명자에서 소유자, 그룹 또는 DACL을 읽으려면 파일이나 디렉터리의 DACL에서 호출자에 대한 READ_CONTROL 액세스를 허용하거나 호출자가 파일이나 디렉터리의 소유자 여야합니다.

파일이나 디렉토리의 SACL을 읽으려면 호출 프로세스에 대해 SE_SECURITY_NAME 권한을 활성화해야합니다.



---

### GetSecurityDescriptorDacl 함수

**GetSecurityDescriptorDacl** 함수는 지정된 보안 설명자의 DACL (임의 액세스 제어 목록-[discretionary access control list](https://msdn.microsoft.com/d007cbb9-b547-4dc7-bc22-b526f650f7c2))에 대한 포인터를 검색합니다.



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

`pSecurityDescriptor`

DACL이 포함 된 SECURITY_DESCRIPTOR 구조체에 대한 포인터입니다. 이 함수는 포인터를 가져옵니다.



`lpbDaclPresent`

지정된 보안 설명자에 DACL이 있음을 나타내는 값의 포인터. lpbDaclPresent가 TRUE이면 보안 설명자는 DACL을 포함하고이 함수의 나머지 출력 매개 변수는 유효한 값을받습니다. lpbDaclPresent가 FALSE 인 경우 보안 설명자는 DACL을 포함하지 않으며 나머지 출력 매개 변수는 유효한 값을받지 못합니다.

lpbDaclPresent에 대한 TRUE 값은 pDacl이 NULL이 아님을 의미하지 않습니다. 즉, pDacl이 NULL 인 동안 lpbDaclPresent는 TRUE 일 수 있으며, 이는 NULL DACL이 적용됨을 의미합니다. NULL DACL은 암시 적으로 개체에 대한 모든 액세스를 허용하며 빈 DACL과 다릅니다. 빈 DACL은 개체에 대한 액세스를 허용하지 않습니다. 적절한 DACL 만들기에 대한 자세한 내용은 [DACL 만들기](https://msdn.microsoft.com/f8ec202f-4f34-4123-8f3c-cfc5960b4dc2)를 참조하십시오.



`pDacl`

액세스 제어 목록 (ACL)에 대한 포인터의 포인터. DACL이 있으면 이 함수는 pDacl이 가리키는 포인터를 보안 설명자의 DACL 주소로 설정합니다. DACL이 없으면 값이 저장되지 않습니다.

함수가 pDacl이 가리키는 포인터에 NULL 값을 저장하면 보안 설명자가 NULL DACL을가집니다. NULL DACL은 암시 적으로 개체에 대한 모든 액세스를 허용합니다.

응용 프로그램에서 NULL이 아닌 DACL을 예상했지만 NULL DACL이 발생하면 응용 프로그램이 안전하게 실패하고 액세스를 허용하지 않아야합니다.



`lpbDaclDefaulted`

보안 설명자에 대한 DACL이있는 경우 SECURITY_DESCRIPTOR_CONTROL 구조의 SE_DACL_DEFAULTED 플래그 값으로 설정된 플래그에 대한 포인터입니다. 이 플래그가 TRUE이면 DACL이 기본 메커니즘에 의해 검색되었습니다. FALSE이면 사용자가 DACL을 명시 적으로 지정했습니다.



#### Return Value

함수가 성공하면 함수는 0이 아닌 값을 반환합니다. 

함수가 실패하면 0을 반환합니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



### DACL 만들기

적절한 임의 액세스 제어 목록 (DACL)을 만드는 것은 응용 프로그램 개발에 필수적이고 중요한 부분입니다. NULL DACL은 모든 사용자에게 모든 유형의 액세스를 허용하므로 NULL DACL은 사용하지 마십시오.

다음 예에서는 DACL을 올바르게 만드는 방법을 보여줍니다. 이 예에는 보안 설명자 정의 언어 (SDDL)를 사용하여 DACL에서 액세스 및 거부 된 액세스 제어를 정의하는 CreateMyDACL 함수가 포함되어 있습니다. 응용 프로그램의 개체에 다른 액세스 권한을 제공하려면 필요에 따라 CreateMyDACL 함수를 수정하십시오.

`사용 구조체`

```c++
typedef struct _SECURITY_ATTRIBUTES {
  DWORD  nLength;
  LPVOID lpSecurityDescriptor; // SECURITY_DESCRIPTOR
  BOOL   bInheritHandle;
} SECURITY_ATTRIBUTES, *PSECURITY_ATTRIBUTES, *LPSECURITY_ATTRIBUTES;

typedef struct _SECURITY_DESCRIPTOR {
  BYTE                        Revision;
  BYTE                        Sbz1;
  SECURITY_DESCRIPTOR_CONTROL Control;
  PSID                        Owner;
  PSID                        Group;
  PACL                        Sacl;
  PACL                        Dacl;
} SECURITY_DESCRIPTOR, *PISECURITY_DESCRIPTOR;
```



#### 예제에서 :

1. main 함수는 SECURITY_ATTRIBUTES 구조체의 주소를 CreateMyDACL 함수에 전달합니다.

2. CreateMyDACL 함수는 SDDL 문자열을 사용하여 다음을 수행합니다.

   - 게스트 및 익명 로그온 사용자에 대한 액세스를 거부합니다.
   - 인증 된 사용자에 대한 읽기 / 쓰기 / 실행 액세스 허용
   - 관리자에게 모든 권한을 허용합니다.

   SDDL 문자열 형식에 대한 자세한 내용은 [보안 설명자 문자열 형식](https://msdn.microsoft.com/library/windows/desktop/aa379570)을 참조하십시오.

3. CreateMyDACL 함수는 [ConvertStringSecurityDescriptorToSecurityDescriptor](https://msdn.microsoft.com/library/windows/desktop/aa376401) 함수를 호출하여 SDDL 문자열을 보안 설명자로 변환합니다. 보안 설명자는 SECURITY_ATTRIBUTES 구조체의 lpSecurityDescriptor 멤버에 의해 지정됩니다. CreateMyDACL은 ConvertStringSecurityDescriptorToSecurityDescriptor의 반환 값을 main 함수에 보냅니다.

4. main 함수는 업데이트 된 [SECURITY_ATTRIBUTES](https://msdn.microsoft.com/library/windows/desktop/aa379560) 구조를 사용하여 [CreateDirectory](https://msdn.microsoft.com/library/windows/desktop/aa363855) 함수로 만든 새 폴더에 대한 DACL을 지정합니다.

5. main 함수가 SECURITY_ATTRIBUTES 구조체를 사용하여 끝나면 main 함수는 [LocalFree](https://msdn.microsoft.com/library/windows/desktop/aa366730) 함수를 호출하여 lpSecurityDescriptor 멤버에 할당 된 메모리를 해제합니다.

#### 참고:

```
ConvertStringSecurityDescriptorToSecurityDescriptor와 같은 SDDL 함수를 성공적으로 컴파일하려면 _WIN32_WINNT 상수를 0x0500 이상으로 정의해야합니다.
```

```c++
#define _WIN32_WINNT 0x0500

#include <windows.h>
#include <sddl.h>
#include <stdio.h>

#pragma comment(lib, "advapi32.lib")

BOOL CreateMyDACL(SECURITY_ATTRIBUTES *);

void main()
{
     SECURITY_ATTRIBUTES  sa;
      
     sa.nLength = sizeof(SECURITY_ATTRIBUTES);
     sa.bInheritHandle = FALSE;  

     // 함수를 호출하여 DACL을 설정합니다. 
     // DACL은 SECURITY_ATTRIBUTES lpSecurityDescriptor 구성원에서 설정됩니다.
     if (!CreateMyDACL(&sa))
     {
         // 오류가 발생했습니다. 메시지를 생성하고 종료하십시오.
         printf("Failed CreateMyDACL\n");
         exit(1);
     }

     // 업데이트 된 SECURITY_ATTRIBUTES를 사용하여 보안 개체에 대한 보안 특성을 지정하십시오.
     // 이 예에서는 새 디렉토리를 만드는 동안 보안 속성을 사용합니다.
     if (0 == CreateDirectory(TEXT("C:\\MyFolder"), &sa))
     {
         // 오류가 발생했습니다. 메시지를 생성하고 종료하십시오.
         printf("Failed CreateDirectory\n");
         exit(1);
     }

     // SECURITY_DESCRIPTOR에 할당 된 메모리를 해제하십시오.
     if (NULL != LocalFree(sa.lpSecurityDescriptor))
     {
         // 오류가 발생했습니다. 메시지를 생성하고 종료하십시오.
         printf("Failed LocalFree\n");
         exit(1);
     }
}


// CreateMyDACL.
//    원하는 DACL이 포함 된 보안 설명자를 만듭니다.
//    이 함수는 SDDL을 사용하여 거부 및 허용 ACE를 만듭니다.
//
// Parameter:
//    SECURITY_ATTRIBUTES * pSA
//    SECURITY_ATTRIBUTES 구조체의 포인터. 
//    구조체를 올바르게 초기화하고 구조체의 lpSecurityDescriptor 
//    멤버 사용을 끝내면 구조체를 해제하는 것은 사용자의 책임입니다.
//    구조체의 lpSecurityDescriptor 멤버를 해제하려면 LocalFree 함수를 호출하십시오.
// 
// Return value:
//    구조체의 주소가 NULL이면 거짓입니다. 
//    그렇지 않으면이 함수는 ConvertStringSecurityDescriptorToSecurityDescriptor 
//    함수의 값을 반환합니다.
BOOL CreateMyDACL(SECURITY_ATTRIBUTES * pSA)
{
     // DACL에 대한 SDDL을 정의합니다. 
     // 이 예에서는 다음 액세스를 설정합니다.
     //     기본 제공 guest 는 모든 액세스가 거부됩니다.
     //     익명 로그온은 모든 액세스가 거부됩니다.
     //     인증 된 사용자는 읽기 / 쓰기 / 실행 액세스가 허용됩니다.
     //     관리자는 모든 권한을 가질 수 있습니다.
     // 응용 프로그램에 적절한 DACL을 생성하려면 필요에 따라이 값을 수정하십시오.
     TCHAR * szSD = TEXT("D:")       // 임의 ACL
        TEXT("(D;OICI;GA;;;BG)")     // built-in guest에 대한 액세스 거부
        TEXT("(D;OICI;GA;;;AN)")     // anonymous logon user에 대한 액세스 거부
        TEXT("(A;OICI;GRGWGX;;;AU)") // 인증 된 사용자에게 읽기/쓰기/실행 허용
        TEXT("(A;OICI;GA;;;BA)");    // 관리자에게 모든 권한 부여

    if (NULL == pSA)
        return FALSE;

    // 반대의 함수는 ConvertSecurityDescriptorToStringSecurityDescriptor. 
    return ConvertStringSecurityDescriptorToSecurityDescriptor(
                szSD,
                SDDL_REVISION_1,
                &(pSA->lpSecurityDescriptor),
                NULL);
}
```



---

### ConvertStringSecurityDescriptorToSecurityDescriptorA 함수

ConvertStringSecurityDescriptorToSecurityDescriptor 함수는 문자열 형식 보안 설명자를 유효한 기능적 보안 설명자로 변환합니다. 이 함수는 ConvertSecurityDescriptorToStringSecurityDescriptor 함수가 문자열 형식으로 변환 된 보안 설명자를 검색합니다.

#### Syntax

```c++
BOOL ConvertStringSecurityDescriptorToSecurityDescriptorA(
  LPCSTR               StringSecurityDescriptor,
  DWORD                StringSDRevision,
  PSECURITY_DESCRIPTOR *SecurityDescriptor,
  PULONG               SecurityDescriptorSize
);
```

#### Parameters

`StringSecurityDescriptor`

변환 할 문자열 형식 보안 설명자가 포함 된 Null 종료 문자열에 대한 포인터입니다.

`StringSDRevision`

StringSecurityDescriptor 문자열의 개정 레벨을 지정합니다. 현재이 값은 SDDL_REVISION_1이어야합니다.

`SecurityDescriptor`

변환 된 보안 설명자에 대한 포인터를받는 변수에 대한 포인터입니다. 반환 된 보안 설명자는 자기 상대적입니다. 반환 된 버퍼를 해제하려면 LocalFree 함수를 호출하십시오. 보안 설명자를 절대 보안 설명자로 변환하려면 MakeAbsoluteSD 함수를 사용하십시오.

`SecurityDescriptorSize`

변환 된 보안 설명 자의 크기 (바이트)를받는 변수에 대한 포인터입니다. 이 매개 변수는 NULL 일 수 있습니다.

#### Return Value

함수가 성공하면 반환 값은 0이 아닙니다. 

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오. GetLastError는 다음 오류 코드 중 하나를 반환 할 수 있습니다.

| Return code                 | Description                                                  |
| :-------------------------- | :----------------------------------------------------------- |
| **ERROR_INVALID_PARAMETER** | 매개 변수가 유효하지 않습니다.                               |
| **ERROR_UNKNOWN_REVISION**  | SDDL 개정 레벨이 유효하지 않습니다.                          |
| **ERROR_NONE_MAPPED**       | 입력 보안 설명자 문자열의 SID (보안 식별자)를 계정 조회 작업에서 찾을 수 없습니다. |

#### Remarks

ace_type이 ACCESS_ALLOWED_OBJECT_ACE_TYPE이고 object_guid 또는 inherit_object_guid에 GUID가 지정되어 있지 않으면 ConvertStringSecurityDescriptorToSecurityDescriptor가 ace_type을 ACCESS_ALLOWED_ACE_TYPE으로 변환합니다. ace_type, object_guid 및 inherit_object_guid 필드에 대한 자세한 내용은 [Ace 문자열](https://msdn.microsoft.com/82c99170-784b-4724-a25b-2f2e8a2e0225)을 참조하십시오.



---

### ConvertSecurityDescriptorToStringSecurityDescriptor 함수

ConvertSecurityDescriptorToStringSecurityDescriptor 함수는 보안 설명자를 문자열 형식으로 변환합니다. 문자열 형식을 사용하여 보안 설명자를 저장하거나 전송할 수 있습니다.

문자열 형식 보안 설명자를 유효한 기능적 보안 설명 자로 다시 변환하려면 ConvertStringSecurityDescriptorToSecurityDescriptor 함수를 호출하십시오.

#### Syntax

```c++
BOOL ConvertSecurityDescriptorToStringSecurityDescriptorA(
  PSECURITY_DESCRIPTOR SecurityDescriptor,
  DWORD                RequestedStringSDRevision,
  SECURITY_INFORMATION SecurityInformation,
  LPSTR                *StringSecurityDescriptor,
  PULONG               StringSecurityDescriptorLen
);
```

#### Parameters

`SecurityDescriptor`

변환 할 보안 설명자에 대한 포인터입니다. 보안 설명자는 절대 또는 자기 상대적 형식 일 수 있습니다.



`RequestedStringSDRevision`

출력 StringSecurityDescriptor 문자열의 개정 레벨을 지정합니다. 현재이 값은 SDDL_REVISION_1이어야합니다.



`SecurityInformation`

SECURITY_INFORMATION 비트 플래그의 조합을 지정하여 출력 문자열에 포함 할 보안 설명 자의 구성 요소를 나타냅니다.



`StringSecurityDescriptor`

null로 끝나는 보안 설명자 문자열에 대한 포인터를받는 변수에 대한 포인터입니다. 문자열 형식에 대한 설명은 보안 설명자 문자열 형식을 참조하십시오. 반환 된 버퍼를 해제하려면 LocalFree 함수를 호출하십시오.



`StringSecurityDescriptorLen`

StringSecurityDescriptor 버퍼에 반환 된 보안 설명자 문자열의 크기 (TCHAR)를받는 변수에 대한 포인터입니다. 이 매개 변수는 크기를 검색 할 필요가 없으면 NULL 일 수 있습니다. 크기는 문자열의 WCHAR 수가 아닌 WCHAR의 버퍼 크기를 나타냅니다.



#### Return Value

함수가 성공하면 반환 값은 0이 아닙니다. 

함수가 실패하면 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오. GetLastError 함수는 다음 오류 코드 중 하나를 반환 할 수 있습니다.

| Return code                 | Description                                                  |
| :-------------------------- | :----------------------------------------------------------- |
| **ERROR_INVALID_PARAMETER** | 매개 변수가 유효하지 않습니다.                               |
| **ERROR_UNKNOWN_REVISION**  | 개정 레벨이 유효하지 않습니다.                               |
| **ERROR_NONE_MAPPED**       | 계정 검색 작업에서 입력 보안 설명 자의 [보안 식별자](https://msdn.microsoft.com/3e9d7672-2314-45c8-8178-5a0afcfd0c50) (SID)를 찾을 수 없습니다. |
| **ERROR_INVALID_ACL**       | [액세스 제어 목록](https://msdn.microsoft.com/0baaa937-f635-4500-8dcd-9dbbd6f4cd02) (ACL)이 유효하지 않습니다. SE_DACL_PRESENT 플래그가 입력 보안 설명자에 설정되어 있고 DACL이 NULL이면이 오류가 반환됩니다. |

#### Remarks

DACL이 NULL이고 SE_DACL_PRESENT 제어 비트가 입력 보안 설명자에 설정되어 있으면 함수가 실패합니다.

DACL이 NULL이고 SE_DACL_PRESENT 컨트롤 비트가 입력 보안 설명자에 설정되어 있지 않으면 결과 보안 설명자 문자열에 D : 구성 요소가 없습니다. 자세한 내용은 [보안 설명자 문자열 형식](https://msdn.microsoft.com/0a226629-084c-40c5-bdd4-ad7355c807cf)을 참조하십시오.







---

### LookupAccountSidA 함수



---

### GetSidIdentifierAuthority 함수



---

### GetSidSubAuthorityCount 함수





## 보안 설명자 문자열 형식

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/security-descriptor-string-format>

보안 설명자 문자열 형식은 보안 설명자에 정보를 저장하거나 전송하기위한 텍스트 형식입니다. ConvertSecurityDescriptorToStringSecurityDescriptor 및 ConvertStringSecurityDescriptorToSecurityDescriptor 함수는 이 형식을 사용합니다.

형식은 owner (O :), 기본 그룹 (G :), DACL (D :) 및 SACL (S :)의 네 가지 주요 구성 요소를 나타내는 토큰이있는 Null 종료 문자열입니다.

참고:

[액세스 제어 항목](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-control-entry-gly) (ACE)과 조건부 ACE는 서로 다른 형식을 갖습니다. ACE의 경우 ACE 문자열을 참조하십시오. 조건부 ACE의 경우 [조건부 ACE의 보안 설명자 정의 언어](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/security-descriptor-definition-language-for-conditional-aces-)를 참조하십시오.

```c++
O:owner_sid
G:group_sid
D:dacl_flags(string_ace1)(string_ace2)... (string_acen)
S:sacl_flags(string_ace1)(string_ace2)... (string_acen)
```

owner_sid

​	개체의 소유자를 식별하는 SID 문자열입니다.

group_sid

​	개체의 주 그룹을 식별하는 SID 문자열입니다.

dacl_flags

​	DACL에 적용되는 보안 설명자 제어 플래그 이러한 컨트롤 플래그에 대한 설명은 SetSecurityDescriptorControl 함수를 참조하십시오. dacl_flags 문자열은 0 개 이상의 다음 문자열을 연결할 수 있습니다.

| Control             | Constant in Sddl.h    | Meaning                                   |
| :------------------ | :-------------------- | :---------------------------------------- |
| "P"                 | SDDL_PROTECTED        | The SE_DACL_PROTECTED flag is set.        |
| "AR"                | SDDL_AUTO_INHERIT_REQ | The SE_DACL_AUTO_INHERIT_REQ flag is set. |
| "AI"                | SDDL_AUTO_INHERITED   | The SE_DACL_AUTO_INHERITED flag is set.   |
| "NO_ACCESS_CONTROL" | SSDL_NULL_ACL         | The ACL is null.                          |



sacl_flags

​	SACL에 적용되는 보안 설명자 제어 플래그 sacl_flags 문자열은 dacl_flags 문자열과 동일한 제어 비트 문자열을 사용합니다.

string_ace

​	보안 설명자의 DACL 또는 SACL에서 ACE를 설명하는 문자열입니다. ACE 문자열 형식에 대한 설명은 ACE 문자열을 참조하십시오. 각 ACE 문자열은 괄호 (())로 묶입니다.

불필요한 구성 요소는 보안 설명자 문자열에서 생략 될 수 있습니다. 예를 들어 입력 보안 설명자에 SE_DACL_PRESENT 플래그가 설정되어 있지 않으면 ConvertSecurityDescriptorToStringSecurityDescriptor에 출력 문자열에 D : 구성 요소가 포함되지 않습니다. SECURITY_INFORMATION 비트 플래그를 사용하여 보안 설명자 문자열에 포함 할 구성 요소를 나타낼 수도 있습니다.

보안 설명자 문자열 형식은 NULL ACL을 지원하지 않습니다.

빈 ACL을 나타내려면 보안 설명자 문자열에 추가 문자열 정보없이 D : 또는 S : 토큰이 포함됩니다.

보안 설명자 문자열은 SECURITY DESCRIPTOR CONTROL 비트를 다른 방식으로 저장합니다. SE_DACL_PRESENT 또는 SE_SACL_PRESENT 비트는 문자열에 D : 또는 S : 토큰이 있음을 나타냅니다. DACL 또는 SACL에 적용되는 다른 비트는 dacl_flags 및 sacl_flags에 저장됩니다. SE_OWNER_DEFAULTED, SE_GROUP_DEFAULTED, SE_DACL_DEFAULTED 및 SE_SACL_DEFAULTED 비트는 보안 설명자 문자열에 저장되지 않습니다. SE_SELF_RELATIVE 비트는 문자열에 저장되지 않지만 ConvertStringSecurityDescriptorToSecurityDescriptor는 항상이 비트를 출력 보안 설명 자로 설정합니다.

다음 예에서는 보안 설명자 문자열과 관련된 보안 설명자의 정보를 보여줍니다.

```c++
"O:AOG:DAD:(A;;RPWPCCDCLCSWRCWDWOGA;;;S-1-0-0)"
```

```c++
"O:DAG:DAD:(A;;RPWPCCDCLCRCWOWDSDSW;;;SY)
(A;;RPWPCCDCLCRCWOWDSDSW;;;DA)
(OA;;CCDC;bf967aba-0de6-11d0-a285-00aa003049e2;;AO)
(OA;;CCDC;bf967a9c-0de6-11d0-a285-00aa003049e2;;AO)
(OA;;CCDC;6da8a4ff-0e52-11d0-a286-00aa003049e2;;AO)
(OA;;CCDC;bf967aa8-0de6-11d0-a285-00aa003049e2;;PO)
(A;;RPLCRC;;;AU)S:(AU;SAFA;WDWOSDWPCCDCSW;;;WD)"
```





## SID 문자열

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/sid-strings>

보안 설명자 정의 언어 (SDDL)에서 보안 설명자 문자열은 보안 설명자의 다음 구성 요소에 대해 SID 문자열을 사용합니다.

- Owner
- Primary group
- The [trustee](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/trustees) in an ACE

보안 설명자 문자열의 SID 문자열은 SID (S-R-I-S-S)의 표준 문자열 표현이나 Sddl.h에 정의 된 문자열 상수 중 하나를 사용할 수 있습니다. 표준 SID 문자열 표기법에 대한 자세한 내용은 SID 구성 요소를 참조하십시오.

잘 알려진 SID에 대한 다음 SID 문자열 상수는 Sddl.h에 정의되어 있습니다. 해당 상대 ID (RID)에 대한 자세한 내용은 잘 알려진 SID를 참조하십시오.

| SDDL SID string | Constant in Sddl.h                 | Account alias and corresponding RID                          |
| :-------------- | :--------------------------------- | :----------------------------------------------------------- |
| "AN"            | SDDL_ANONYMOUS                     | Anonymous logon. The corresponding RID is SECURITY_ANONYMOUS_LOGON_RID. |
| "AO"            | SDDL_ACCOUNT_OPERATORS             | Account operators. The corresponding RID is DOMAIN_ALIAS_RID_ACCOUNT_OPS. |
| "AU"            | SDDL_AUTHENTICATED_USERS           | Authenticated users. The corresponding RID is SECURITY_AUTHENTICATED_USER_RID. |
| "BA"            | SDDL_BUILTIN_ADMINISTRATORS        | Built-in administrators. The corresponding RID is DOMAIN_ALIAS_RID_ADMINS. |
| "BG"            | SDDL_BUILTIN_GUESTS                | Built-in guests. The corresponding RID is DOMAIN_ALIAS_RID_GUESTS. |
| "BO"            | SDDL_BACKUP_OPERATORS              | Backup operators. The corresponding RID is DOMAIN_ALIAS_RID_BACKUP_OPS. |
| "BU"            | SDDL_BUILTIN_USERS                 | Built-in users. The corresponding RID is DOMAIN_ALIAS_RID_USERS. |
| "CA"            | SDDL_CERT_SERV_ADMINISTRATORS      | Certificate publishers. The corresponding RID is DOMAIN_GROUP_RID_CERT_ADMINS. |
| "CD"            | SDDL_CERTSVC_DCOM_ACCESS           | Users who can connect to certification authorities using Distributed Component Object Model (DCOM). The corresponding RID is DOMAIN_ALIAS_RID_CERTSVC_DCOM_ACCESS_GROUP. |
| "CG"            | SDDL_CREATOR_GROUP                 | Creator group. The corresponding RID is SECURITY_CREATOR_GROUP_RID. |
| "CO"            | SDDL_CREATOR_OWNER                 | Creator owner. The corresponding RID is SECURITY_CREATOR_OWNER_RID. |
| "DA"            | SDDL_DOMAIN_ADMINISTRATORS         | Domain administrators. The corresponding RID is DOMAIN_GROUP_RID_ADMINS. |
| "DC"            | SDDL_DOMAIN_COMPUTERS              | Domain computers. The corresponding RID is DOMAIN_GROUP_RID_COMPUTERS. |
| "DD"            | SDDL_DOMAIN_DOMAIN_CONTROLLERS     | Domain controllers. The corresponding RID is DOMAIN_GROUP_RID_CONTROLLERS. |
| "DG"            | SDDL_DOMAIN_GUESTS                 | Domain guests. The corresponding RID is DOMAIN_GROUP_RID_GUESTS. |
| "DU"            | SDDL_DOMAIN_USERS                  | Domain users. The corresponding RID is DOMAIN_GROUP_RID_USERS. |
| "EA"            | SDDL_ENTERPRISE_ADMINS             | Enterprise administrators. The corresponding RID is DOMAIN_GROUP_RID_ENTERPRISE_ADMINS. |
| "ED"            | SDDL_ENTERPRISE_DOMAIN_CONTROLLERS | Enterprise domain controllers. The corresponding RID is SECURITY_SERVER_LOGON_RID. |
| "HI"            | SDDL_ML_HIGH                       | High integrity level. The corresponding RID is SECURITY_MANDATORY_HIGH_RID. |
| "IU"            | SDDL_INTERACTIVE                   | Interactively logged-on user. This is a group identifier added to the token of a process when it was logged on interactively. The corresponding logon type is LOGON32_LOGON_INTERACTIVE. The corresponding RID is SECURITY_INTERACTIVE_RID. |
| "LA"            | SDDL_LOCAL_ADMIN                   | Local administrator. The corresponding RID is DOMAIN_USER_RID_ADMIN. |
| "LG"            | SDDL_LOCAL_GUEST                   | Local guest. The corresponding RID is DOMAIN_USER_RID_GUEST. |
| "LS"            | SDDL_LOCAL_SERVICE                 | Local service account. The corresponding RID is SECURITY_LOCAL_SERVICE_RID. |
| "LW"            | SDDL_ML_LOW                        | Low integrity level. The corresponding RID is SECURITY_MANDATORY_LOW_RID. |
| "ME"            | SDDL_MLMEDIUM                      | Medium integrity level. The corresponding RID is SECURITY_MANDATORY_MEDIUM_RID. |
| "MU"            | SDDL_PERFMON_USERS                 | Performance Monitor users.                                   |
| "NO"            | SDDL_NETWORK_CONFIGURATION_OPS     | Network configuration operators. The corresponding RID is DOMAIN_ALIAS_RID_NETWORK_CONFIGURATION_OPS. |
| "NS"            | SDDL_NETWORK_SERVICE               | Network service account. The corresponding RID is SECURITY_NETWORK_SERVICE_RID. |
| "NU"            | SDDL_NETWORK                       | Network logon user. This is a group identifier added to the token of a process when it was logged on across a network. The corresponding logon type is LOGON32_LOGON_NETWORK. The corresponding RID is SECURITY_NETWORK_RID. |
| "PA"            | SDDL_GROUP_POLICY_ADMINS           | Group Policy administrators. The corresponding RID is DOMAIN_GROUP_RID_POLICY_ADMINS. |
| "PO"            | SDDL_PRINTER_OPERATORS             | Printer operators. The corresponding RID is DOMAIN_ALIAS_RID_PRINT_OPS. |
| "PS"            | SDDL_PERSONAL_SELF                 | Principal self. The corresponding RID is SECURITY_PRINCIPAL_SELF_RID. |
| "PU"            | SDDL_POWER_USERS                   | Power users. The corresponding RID is DOMAIN_ALIAS_RID_POWER_USERS. |
| "RC"            | SDDL_RESTRICTED_CODE               | Restricted code. This is a restricted token created using the [**CreateRestrictedToken**](https://msdn.microsoft.com/en-us/library/Aa446583(v=VS.85).aspx) function. The corresponding RID is SECURITY_RESTRICTED_CODE_RID. |
| "RD"            | SDDL_REMOTE_DESKTOP                | Terminal server users. The corresponding RID is DOMAIN_ALIAS_RID_REMOTE_DESKTOP_USERS. |
| "RE"            | SDDL_REPLICATOR                    | Replicator. The corresponding RID is DOMAIN_ALIAS_RID_REPLICATOR. |
| "RO"            | SDDL_ENTERPRISE_RO_DCs             | Enterprise Read-only domain controllers. The corresponding RID is DOMAIN_GROUP_RID_ENTERPRISE_READONLY_DOMAIN_CONTROLLERS. |
| "RS"            | SDDL_RAS_SERVERS                   | RAS servers group. The corresponding RID is DOMAIN_ALIAS_RID_RAS_SERVERS. |
| "RU"            | SDDL_ALIAS_PREW2KCOMPACC           | Alias to grant permissions to accounts that use applications compatible with operating systems previous to Windows 2000. The corresponding RID is DOMAIN_ALIAS_RID_PREW2KCOMPACCESS. |
| "SA"            | SDDL_SCHEMA_ADMINISTRATORS         | Schema administrators. The corresponding RID is DOMAIN_GROUP_RID_SCHEMA_ADMINS. |
| "SI"            | SDDL_ML_SYSTEM                     | System integrity level. The corresponding RID is SECURITY_MANDATORY_SYSTEM_RID. |
| "SO"            | SDDL_SERVER_OPERATORS              | Server operators. The corresponding RID is DOMAIN_ALIAS_RID_SYSTEM_OPS. |
| "SU"            | SDDL_SERVICE                       | Service logon user. This is a group identifier added to the token of a process when it was logged as a service. The corresponding logon type is LOGON32_LOGON_SERVICE. The corresponding RID is SECURITY_SERVICE_RID. |
| "SY"            | SDDL_LOCAL_SYSTEM                  | Local system. The corresponding RID is SECURITY_LOCAL_SYSTEM_RID. |
| "WD"            | SDDL_EVERYONE                      | Everyone. The corresponding RID is SECURITY_WORLD_RID.       |

ConvertSidToStringSid 및 ConvertStringSidToSid 함수는 항상 표준 SID 문자열 표기법을 사용하며 SDDL SID 문자열 상수를 지원하지 않습니다.

잘 알려진 SID에 대한 자세한 내용은 잘 [알려진 SID](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/well-known-sids)를 참조하십시오.





## ACE 문자열

출처: <https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/ace-strings>

보안 설명자 정의 언어 (SDDL)는 보안 설명자 문자열의 DACL 및 SACL 구성 요소에 ACE 문자열을 사용합니다.

보안 설명자 문자열 형식 예에서 볼 수 있듯이 보안 설명자 문자열의 각 ACE는 괄호로 묶습니다. ACE 필드는 다음 순서로되어 있으며 세미콜론 (;)으로 구분됩니다.

**참고:**

```
조건부 액세스 제어 항목 (ACE)에는 다른 ACE 유형과 다른 형식이 있습니다. 조건부 ACE의 경우 조건부 ACE의 보안 설명자 정의 언어를 참조하십시오.
```

### Syntax

```
ace_type;ace_flags;rights;object_guid;inherit_object_guid;account_sid;(resource_attribute)
```

### Fields

#### ace_type

ACE_HEADER 구조체의 AceType 멤버 값을 나타내는 문자열입니다. ACE 유형 문자열은 Sddl.h에 정의 된 다음 문자열 중 하나 일 수 있습니다.

| CE type string | Constant in Sddl.h                  | AceType value                                                |
| :------------- | :---------------------------------- | :----------------------------------------------------------- |
| "A"            | SDDL_ACCESS_ALLOWED                 | ACCESS_ALLOWED_ACE_TYPE                                      |
| "D"            | SDDL_ACCESS_DENIED                  | ACCESS_DENIED_ACE_TYPE                                       |
| "OA"           | SDDL_OBJECT_ACCESS_ALLOWED          | ACCESS_ALLOWED_OBJECT_ACE_TYPE                               |
| "OD"           | SDDL_OBJECT_ACCESS_DENIED           | ACCESS_DENIED_OBJECT_ACE_TYPE                                |
| "AU"           | SDDL_AUDIT                          | SYSTEM_AUDIT_ACE_TYPE                                        |
| "AL"           | SDDL_ALARM                          | SYSTEM_ALARM_ACE_TYPE                                        |
| "OU"           | SDDL_OBJECT_AUDIT                   | SYSTEM_AUDIT_OBJECT_ACE_TYPE                                 |
| "OL"           | SDDL_OBJECT_ALARM                   | SYSTEM_ALARM_OBJECT_ACE_TYPE                                 |
| "ML"           | SDDL_MANDATORY_LABEL                | SYSTEM_MANDATORY_LABEL_ACE_TYPE                              |
| "XA"           | SDDL_CALLBACK_ACCESS_ALLOWED        | ACCESS_ALLOWED_CALLBACK_ACE_TYPE**Windows Vista and Windows Server 2003:** Not available. |
| "XD"           | SDDL_CALLBACK_ACCESS_DENIED         | ACCESS_DENIED_CALLBACK_ACE_TYPE**Windows Vista and Windows Server 2003:** Not available. |
| "RA"           | SDDL_RESOURCE_ATTRIBUTE             | SYSTEM_RESOURCE_ATTRIBUTE_ACE_TYPE**Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista and Windows Server 2003:**Not available. |
| "SP"           | SDDL_SCOPED_POLICY_ID               | SYSTEM_SCOPED_POLICY_ID_ACE_TYPE**Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista and Windows Server 2003:**Not available. |
| "XU"           | SDDL_CALLBACK_AUDIT                 | SYSTEM_AUDIT_CALLBACK_ACE_TYPE**Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista and Windows Server 2003:**Not available. |
| "ZA"           | SDDL_CALLBACK_OBJECT_ACCESS_ALLOWED | ACCESS_ALLOWED_CALLBACK_ACE_TYPE**Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista and Windows Server 2003:**Not available. |

참고:

ace_type이 ACCESS_ALLOWED_OBJECT_ACE_TYPE이고 object_guid 또는 inherit_object_guid에 GUID가 지정되어 있지 않으면 ConvertStringSecurityDescriptorToSecurityDescriptor가 ace_type을 ACCESS_ALLOWED_ACE_TYPE으로 변환합니다.



#### ace_flags

ACE_HEADER 구조체의 AceFlags 멤버 값을 나타내는 문자열입니다. ACE 플래그 문자열은 Sddl.h에 정의 된 다음 문자열의 연결 일 수 있습니다.

| ACE flags string | Constant in Sddl.h     | AceFlag value              |
| :--------------- | :--------------------- | :------------------------- |
| "CI"             | SDDL_CONTAINER_INHERIT | CONTAINER_INHERIT_ACE      |
| "OI"             | SDDL_OBJECT_INHERIT    | OBJECT_INHERIT_ACE         |
| "NP"             | SDDL_NO_PROPAGATE      | NO_PROPAGATE_INHERIT_ACE   |
| "IO"             | SDDL_INHERIT_ONLY      | INHERIT_ONLY_ACE           |
| "ID"             | SDDL_INHERITED         | INHERITED_ACE              |
| "SA"             | SDDL_AUDIT_SUCCESS     | SUCCESSFUL_ACCESS_ACE_FLAG |
| "FA"             | SDDL_AUDIT_FAILURE     | FAILED_ACCESS_ACE_FLAG     |



#### rights

ACE가 제어하는 액세스 권한을 나타내는 문자열입니다. 이 문자열은 '0x7800003F'와 같은 액세스 권한의 16 진수 문자열 표현이거나 다음 문자열의 연결 일 수 있습니다.

**일반 액세스 권한**

| 액세스 권한 문자열 | Constant in Sddl.h   | Access right value |
| ------------------ | -------------------- | ------------------ |
| "GA"               | SDDL_GENERIC_ALL     | GENERIC_ALL        |
| "GR"               | SDDL_GENERIC_READ    | GENERIC_READ       |
| "GW"               | SDDL_GENERIC_WRITE   | GENERIC_WRITE      |
| "GX"               | SDDL_GENERIC_EXECUTE | GENERIC_EXECUTE    |

**표준 액세스 권한**

| 액세스 권한 문자열 | Constant in Sddl.h   | Access right value |
| ------------------ | -------------------- | ------------------ |
| "RC"               | SDDL_READ_CONTROL    | READ_CONTROL       |
| "SD"               | SDDL_STANDARD_DELETE | DELETE             |
| "WD"               | SDDL_WRITE_DAC       | WRITE_DAC          |
| "WO"               | SDDL_WRITE_OWNER     | WRITE_OWNER        |

**디렉터리 서비스 개체 액세스 권한**

| 액세스 권한 문자열 | Constant in Sddl.h  | Access right value          |
| ------------------ | ------------------- | --------------------------- |
| "RP"               | SDDL_READ_PROPERTY  | ADS_RIGHT_DS_READ_PROP      |
| "WP"               | SDDL_WRITE_PROPERTY | ADS_RIGHT_DS_WRITE_PROP     |
| "CC"               | SDDL_CREATE_CHILD   | ADS_RIGHT_DS_CREATE_CHILD   |
| "DC"               | SDDL_DELETE_CHILD   | ADS_RIGHT_DS_DELETE_CHILD   |
| "LC"               | SDDL_LIST_CHILDREN  | ADS_RIGHT_ACTRL_DS_LIST     |
| "SW"               | SDDL_SELF_WRITE     | ADS_RIGHT_DS_SELF           |
| "LO"               | SDDL_LIST_OBJECT    | ADS_RIGHT_DS_LIST_OBJECT    |
| "DT"               | SDDL_DELETE_TREE    | ADS_RIGHT_DS_DELETE_TREE    |
| "CR"               | SDDL_CONTROL_ACCESS | ADS_RIGHT_DS_CONTROL_ACCESS |

**파일 액세스 권한**

| 액세스 권한 문자열 | Constant in Sddl.h | Access right value   |
| ------------------ | ------------------ | -------------------- |
| "FA"               | SDDL_FILE_ALL      | FILE_ALL_ACCESS      |
| "FR"               | SDDL_FILE_READ     | FILE_GENERIC_READ    |
| "FW"               | SDDL_FILE_WRITE    | FILE_GENERIC_WRITE   |
| "FX"               | SDDL_FILE_EXECUTE  | FILE_GENERIC_EXECUTE |

**레지스트리 키 액세스 권한**

| 액세스 권한 문자열 | Constant in Sddl.h | Access right value |
| ------------------ | ------------------ | ------------------ |
| "KA"               | SDDL_KEY_ALL       | KEY_ALL_ACCESS     |
| "KR"               | SDDL_KEY_READ      | KEY_READ           |
| "KW"               | SDDL_KEY_WRITE     | KEY_WRITE          |
| "KX"               | SDDL_KEY_EXECUTE   | KEY_EXECUTE        |

**필수 라벨 권리**

| 액세스 권한 문자열 | Constant in Sddl.h | Access right value                   |
| ------------------ | ------------------ | ------------------------------------ |
| "NR"               | SDDL_NO_READ_UP    | SYSTEM_MANDATORY_LABEL_NO_READ_UP    |
| "NW"               | SDDL_NO_WRITE_UP   | SYSTEM_MANDATORY_LABEL_NO_WRITE_UP   |
| "NX"               | SDDL_NO_EXECUTE_UP | SYSTEM_MANDATORY_LABEL_NO_EXECUTE_UP |





#### object_guid

ACCESS_ALLOWED_OBJECT_ACE와 같이 개체 별 ACE 구조체의 ObjectType 멤버 값을 나타내는 GUID의 문자열 표현입니다. GUID 문자열은 UuidToString 함수에서 반환 한 형식을 사용합니다.

다음 표는 일반적으로 사용되는 개체 GUID 목록입니다.

| Rights and GUID                         | Permission      |
| :-------------------------------------- | :-------------- |
| CR;ab721a53-1e2f-11d0-9819-00aa0040529b | Change password |
| CR;00299570-246d-11d0-a768-00aa006e0529 | Reset password  |



#### inherit_object_guid

개체 별 ACE 구조체의 InheritedObjectType 멤버 값을 나타내는 GUID의 문자열 표현입니다. GUID 문자열은 [UuidToString](https://msdn.microsoft.com/library/windows/desktop/aa379352) 형식을 사용합니다.



#### account_sid

ACE의 트러스티를 식별하는 [SID string](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/sid-strings) 입니다.



#### resource_attribute

[<span style="color:blue">선택 사항</span>] resource_attribute는 리소스 ACE에만 적용되며 선택 사항입니다. 데이터 유형을 나타내는 문자열. 리소스 특성 에이스 데이터 형식은 Sddl.h에 정의 된 다음 데이터 형식 중 하나 일 수 있습니다.

"#"기호는 자원 속성에서 '0'과 동의어입니다. 예를 들어 D:AI(XA;OICI;FA;;;WD;(OctetStringType==#1#2#3##)) 는 다음과 같이 해석된다 D:AI(XA;OICI;FA;;;WD;(OctetStringType==#01020300)).

Windows Server 2008 R2, Windows 7, Windows Server 2008, Windows Vista 및 Windows Server 2003 : 리소스 특성을 사용할 수 없습니다.

| Resource attribute ace data type string | Constant in Sddl.h | Data type        |
| :-------------------------------------- | :----------------- | :--------------- |
| "TI"                                    | SDDL_INT           | Signed integer   |
| "TU"                                    | SDDL_UINT          | Unsigned integer |
| "TS"                                    | SDDL_WSTRING       | Wide string      |
| "TD"                                    | SDDL_SID           | SID              |
| "TX"                                    | SDDL_BLOB          | Octet string     |
| "TB"                                    | SDDL_BOOLEAN       | Boolean          |



### ACE 문자열 예제

다음 예에서는 액세스 허용 ACE에 대한 ACE 문자열을 보여줍니다. 객체 별 ACE가 아니므로 object_guid 및 inherit_object_guid 필드에 정보가 없습니다. ace_flags 필드도 비어있어 ACE 플래그가 설정되지 않았 음을 나타냅니다.

```c++
(A;;RPWPCCDCLCSWRCWDWOGA;;;S-1-1-0)
```

위에 표시된 ACE 문자열은 다음 ACE 정보를 설명합니다.

```c++
AceType:       0x00 (ACCESS_ALLOWED_ACE_TYPE)
AceFlags:      0x00
Access Mask:   0x100e003f
                    READ_CONTROL
                    WRITE_DAC
                    WRITE_OWNER
                    GENERIC_ALL
                    Other access rights(0x0000003f)
Ace Sid      : (S-1-1-0)
```



다음 예제에서는 Windows 및 자원에 대한 클레임으로 분류 된 파일을 보안 설정이 높은 비즈니스 영향으로 설정 한 상태로 보여줍니다.

```c++
(RA;CI;;;;S-1-1-0; ("Project",TS,0,"Windows","SQL")) 
(RA;CI;;;;S-1-1-0; ("Secrecy",TU,0,3))
```

위에 표시된 ACE 문자열은 다음 ACE 정보를 설명합니다.

```c++
AceType:       0x12 (SYSTEM_RESOURCE_ATTRIBUTE_ACE_TYPE)
AceFlags:      0x1  (SDDL_CONTAINER_INHERIT)
Access Mask:   0x0
Ace Sid      : (S-1-1-0)
Resource Attributes: Project has the strings Windows and SQL, Secrecy has the unsigned int value of 3
```

자세한 내용은 [보안 설명자 문자열 형식](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/security-descriptor-string-format) 및  [SID Strings](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/sid-strings)을 참조하십시오. 조건부 ACE의 경우 [조건부 ACE의 보안 설명자 정의 언어](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/security-descriptor-definition-language-for-conditional-aces-)를 참조하십시오.

