# 폴더간에 NTFS 권한 복사 (Copying NTFS permissions)

출처: https://confidentialfiles.wordpress.com/2014/03/13/copying-ntfs-permissions-between-folders/



본 예제 에서는 D : 볼륨에 'Programs'라는 폴더를 만들고 NTFS 권한을  'C:\\Program Files'의 폴더 권한과 일치시키는 방법을 설명 합니다.

따라서 D:\Programs 폴더는 셍성 되어 있는것을 기준으로 작성 되었습니다.

방법은 여러 가지 방법이 있습니다. 다음 유틸리티를 사용하여 수행 할 수 있습니다.

1. icACLs and Notepad
2. Windows PowerShell
3. XCopy (not recommended)
4. Robocopy (not recommended)

NTFS 보안의 주제는 Windows에 대한 중간 지식이 필요한 주제이므로 높은 권한으로 특정 프로그램을 실행하는 방법과 같은 기본 세부 정보는 생략합니다.

**역자 주) 방법은 여러가지가 있지만 역자가 테스트 한 방법은 `Windows PowerShell` 을 이용하는 방법과  `Robocopy ` 을 이용한 방법 입니다.**



| Term                              | Acronym | Description                                                  |
| --------------------------------- | ------- | ------------------------------------------------------------ |
| Discretionary access control list | DACL    | 주어진 파일, 폴더, 레지스트리 키, 네트워크 공유 또는 Windows의 다른 유형의 보안 개체에 대한 권한 집합. NTFS 디스크에 저장된 파일 및 폴더의 경우 DACL은 어떤 사용자 계정 또는 그룹에 어떤 권한이 있는지를 나타내는 NTFS 권한 세트입니다. |
| System access control list        | SACL    | 감사를 지원하는 파일, 폴더 또는 기타 유형의 보안 개체에 정의 된 감사 규칙 세트. |
| Access control list               | ACL     | DACL, SACL, 소유자 이름, 무결성 수준 및 내가 놓친 다른 항목을 포함하여 보안 개체 (이 경우 파일 또는 폴더)에 대한 전체 보안 속성 집합을 나타냅니다. |
| Access control entry              | ACE     | DACL 또는 SACL의 개별 규칙을 나타냅니다.                     |
| Integrity level                   | IL      | '필수 레이블'이라고도하는이 레이블은 Windows Vista에 추가되며 DACL의 일부입니다. 그러나 DACL을 복사 할 수있는 것이 IL을 복사 할 수 있다고 가정하지 마십시오. 항상 조사하십시오. |
| Owner                             |         | 모든 보안 개체에는 소유자가있을 수 있습니다. 보안 개체의 소유자로 정의 된 사용자 계정은 해당 DACL에 관계없이 개체에 대한 완전한 액세스 권한을 갖습니다. 그러나 디스크 할당량 기능을 사용하면 NTFS 볼륨의 파일 크기가 소유자의 할당량에서 추론됩니다. 일반적으로 소유권은 DACL에서 소유권 가져 오기 권한이있는 사용자가 대신 할 수 있습니다. [기본적으로 관리자 및 백업 운영자](http://wordpress.redirectingat.com/?id=725X1342&isjs=1&jv=14.1.5-stackpath&sref=https%3A%2F%2Fconfidentialfiles.wordpress.com%2F2014%2F03%2F13%2Fcopying-ntfs-permissions-between-folders%2F&url=http%3A%2F%2Ftechnet.microsoft.com%2Fen-us%2Flibrary%2Fcc976700.aspx&xguid=95306e4c1e311ad8a237c989f06ecb0e&xs=1&xtz=-540&xuuid=9179c3eebf873c928a159685c4abbacc&xcust=8982&xjsf=other_click__auxclick%20%5B2%5D)는 소유권을 다른 사용자 계정으로 이전 할 수도 있습니다. |



## icACLs and Notepad

이 섹션에서는 Windows 7에서 icACL을 사용하여 DACL 및 IL을 전송하는 방법을 보여 주지만이 방법이 Windows Vista, Windows 8, Windows 8.1 및 모든 Windows Server 형제와 호환 될 가능성이 있다고 생각합니다.

IcACL은 IL이없는 Windows Server 2003 서비스 팩 2에서 처음 도입되었습니다. 그럼에도 불구하고 icACL은 IL도 복사합니다.

다음과 같이하십시오 :

- 관리자 권한으로 명령 프롬프트를 엽니 다. 기본 Windows 설정을 사용하는 경우 "C:\\Windows\\System32" 에 도착합니다.

- 쓰기 권한이있는 정리되지 않은 폴더로 이동하십시오. 너무 많은 시간을 보내고 싶지 않은 파일을 만들려고합니다.

- 다음 명령을 실행하십시오.

  ``` cmd
  C:\> icACLs "C:\Program Files" /save Perms.txt
  ```

- 메모장에서 Perms.txt를 엽니 다

  첫 줄을“Program Files”에서“Programs”로 변경하십시오. 다른 변경을하지 않도록주의하십시오.

- 파일을 저장하고 종료하십시오.

- 다음 명령을 실행하십시오.

  ``` cmd
  C:\>  icACLs D:\ /restore Perms.txt
  ```

  IcACL과 함께 /t 스위치를 사용하여 초기 폴더의 모든 파일과 하위 폴더 (이 예에서는“프로그램 파일”)에 대한 권한을 저장할 수 있습니다. 또한 기본적으로 Program Files 폴더에는 IL 규칙이 없습니다. 그러나 시스템 관리자가 다른 폴더에 대한 권한을 옮기기를 원한다고 생각합니다. 일부 폴더에는 IL 규칙이있을 수 있습니다.

역자 주) 위 방법은 테스트 하지는 않았으며 icacls 라는 프로그램은 있는것(OS 설치시 포함) 으로 확인 하였습니다.



## Windows PowerShell 5.x

이 섹션에서는 PowerShell v5.x를 사용하여 DACL, SACL 및 개체 소유자를 전송하는 방법에 대해 설명합니다.

PowerShell에는 다음과 같이 파이프 할 수있는 Get-Acl 및 Set-Acl 명령이 있습니다.

``` powershell
Get-Acl -Path | Set-Acl -Path
```

소스 파일 또는 폴더에서 대상 파일 또는 폴더로 모든 액세스 제어 등록 정보를 전송하려고 시도합니다. 

다음 명령을 실행 하세요:

``` powershell
Get-Acl -Path 'C:\Program Files' | Set-Acl -Path 'D:\Programs'
```

… 관리 권한으로 실행하면 트릭이 필요합니다. 이것은 Windows 10에서 테스트되었지만 PowerShell 5.x가있는 다른 버전의 Windows에서 작동합니다.



## Robocopy

Windows 2000부터 RoboCopy는 훌륭한 XCopy의 후속 제품인 Windows 구성 요소가되었습니다. TechNet의 Microsoft 지원 기사 323275 및 Robocopy 설명서에 따르면 Robocopy는 DACL, SACL 및 소유권 데이터를 전송할 수 있습니다. 다음 명령은 DACL 만 전송합니다.

``` cmd
C:\> Robocopy "C:\Program Files" "D:\Programs" /COPY:S /SECFIX
```

이 방법은 테스트되지 않았습니다. 따라서 위험은 전적으로 귀하의 책임입니다.

**역자 주) 테스트 결과 잘 수행 되는것을 확인 하였음.**



## XCopy

다음 명령이 작동한다고 들었지만 ACL의 어느 부분이 전송되는지에 대한 실마리는 없습니다.

``` cmd
C:\> xcopy "C:\Program Files" "D:\Programs" /T /E /I /H /K /X /Y
```

이 방법은 테스트되지 않았습니다. 따라서 위험은 전적으로 귀하의 책임입니다. 그러나 각 스위치의 의미를 알고 싶다면 XCopy 도움말 페이지에서 관련 부분을 재현하고 있습니다.

| Switch | Description                                                  |
| ------ | ------------------------------------------------------------ |
| /E     | 빈 디렉토리를 포함하여 디렉토리 및 서브 디렉토리를 복사합니다. /S /E와 동일 /T를 수정하는 데 사용될 수 있습니다. |
| /H     | 숨겨진 파일과 시스템 파일도 복사합니다.                      |
| /I     | 대상이 존재하지 않고 둘 이상의 파일을 복사하는 경우 대상은 디렉토리 여야한다고 가정합니다. |
| /K     | 속성을 복사합니다. 일반 Xcopy는 읽기 전용 속성을 재설정합니다. |
| /T     | 디렉토리 구조를 작성하지만 파일을 복사하지는 않습니다. 빈 디렉토리 나 하위 디렉토리는 포함하지 않습니다. / T / E는 빈 디렉토리와 하위 디렉토리를 포함합니다. |
| /X     | 파일 감사 설정을 복사합니다 (/ O 포함).                      |
| /Y     | 기존 대상 파일을 덮어 쓸 것인지 묻는 메시지를 표시하지 않습니다. |
| /O     | 파일 소유권 및 ACL 정보를 복사합니다.                        |
| /S     | 빈 디렉토리를 제외한 디렉토리 및 서브 디렉토리를 복사합니다. |