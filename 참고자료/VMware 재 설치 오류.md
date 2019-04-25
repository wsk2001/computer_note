# VMware 재 설치 오류

출처: <https://communities.vmware.com/thread/580312>

win7에서 Workstation 12 및 14에서 나에게 일어난 일. 이 문제를 해결하기 위해 내가 할 수있는 다른 방법이 있습니까?

로그에서 vmusb/netbridge/netadapter/vmci 드라이버를 설치할 때 오류가 발생합니다.

setupapi.dev.log 의 오류 내용.

```
===================================

flq: {_commit_copyfile}

flq: CopyFile: 'C:\Users\acs\AppData\Local\Temp\{5f5042b8-f0c3-00c7-ea0b-e401025fd614}\vmusb.sys'

flq: to: 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\SET5977.tmp'

flq: MoveFile: 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\SET5977.tmp'

flq: to: 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\vmusb.sys'

!!! flq: MoveFile: FAILED!

!!! flq: Error 32: The process cannot access the file because it is being used by another process.

!!! flq: Windows could not copy a boot file 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\vmusb.sys' due to the presence of an in-use file.

!!! flq: Error installing file (0x00000005)

!!! flq: Error 5: Access is denied.

! flq: SourceFile   - 'C:\Users\acs\AppData\Local\Temp\{5f5042b8-f0c3-00c7-ea0b-e401025fd614}\vmusb.sys'

flq: TempFile     - 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\SET5977.tmp'

! flq: TargetFile   - 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\vmusb.sys'

flq: {SPFQNOTIFY_COPYERROR}

!!! sto: Failed to copy file 'C:\Users\acs\AppData\Local\Temp\{5f5042b8-f0c3-00c7-ea0b-e401025fd614}\vmusb.sys' to 'C:\windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\vmusb.sys'. Error = 0x00000005

sto: File 'C:\Users\acs\AppData\Local\Temp\{5f5042b8-f0c3-00c7-ea0b-e401025fd614}\vmusb.sys' is not in use by any processes.

flq: {SPFQNOTIFY_COPYERROR - returned 0x00000000}

!!! flq: SPFQNOTIFY_COPYERROR: returned SPFQOPERATION_ABORT.

!!! flq: Error 2: The system cannot find the file specified.

flq: {_commit_copyfile exit(0x00000002)}

flq: {_commit_copy_subqueue exit(0x00000002)}

====================================
```

이 문제를 일으키는 몇 가지 가능성이 있습니다. 아래에서 시도해 볼 수 있습니까?

`1` 안티 바이러스 소프트웨어 로그가 있는지 확인하십시오.이 복사 작업 실패는 안티 바이러스가이 작업을 거부하여 발생할 수 있습니다.

`2` 워크 스테이션을 제거한 후 하나의 GUID로 명명 된 모든 하위 디렉토리에 대해 'C:\\windows\\System32\\ DriverStore\\Temp'폴더로 이동하여 다음 드라이버 파일이있는 하위 폴더를 찾아 삭제하십시오. vmusb/netbridge/netadapter/vmci

예를 들어 'C:windows\System32\DriverStore\Temp\{431616f7-d661-75d8-1d2d-c8796036d55a}\vmusb.sys'가있는 경우 하위 폴더 {431616f7-d661-75d8-1d2d- c8796036d55a}

`3` 'C:\\windows\\System32\\DriverStore\\'보안 속성을 확인하고 '시스템'계정이이 폴더에 대한 모든 권한을 가지고 있는지 확인하십시오.

`4` NTFS의 '대체 데이터 스트림'을 웹 사이트 https://docs.microsoft.com/en-us/sysinternals/downloads/streams로 이동하여 stream이라는 도구를 다운로드하고 압축을 풀어 한 폴더에 저장합니다. c:\temp, 한 관리자 콘솔에서 다음을 실행하십시오.

```
C:\> cd /d C:\windows\System32\DriverStore
C:\Windows\System32\DriverStore> c:\temp\streams64 -s *
c:\temp\Streams64 -s -d *
```

