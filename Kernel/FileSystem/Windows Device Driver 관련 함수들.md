# Windows Device Driver Functions

Windows Device Driver 관련 함수들 정리



## DispatchQueryInformation 루틴

드라이버의 DispatchQueryInformation 루틴은 IRP_MJ_QUERY_INFORMATION I/O 함수 코드에 대한 IRP를 처리합니다. 이 I/O 기능 코드에 대한 드라이버 지원은 선택 사항이며 일반적으로 상위 레벨 또는 파일 시스템 드라이버에 나타납니다. 이 요청은 I/O 관리자 및 기타 운영 체제 구성 요소와 기타 커널 모드 드라이버에 의해 전송됩니다. 예를 들어 사용자 모드 응용 프로그램이 GetFileInformationByHandle을 호출하고 커널 모드 구성 요소가 ZwQueryInformationFile 을 호출 할 때 전송됩니다.





## NtQueryInformationFile function (ntifs.h)

NtQueryInformationFile 루틴은 파일 개체에 대한 다양한 정보를 반환합니다. 몇 가지 파일 정보 클래스에 보다 효율적으로 사용할 수있는 NtQueryInformationByName도 참조하십시오.



### Syntax

``` c++
__kernel_entry NTSYSCALLAPI NTSTATUS NtQueryInformationFile(
  HANDLE                 FileHandle,
  PIO_STATUS_BLOCK       IoStatusBlock,
  PVOID                  FileInformation,
  ULONG                  Length,
  FILE_INFORMATION_CLASS FileInformationClass
);
```

### Parameters

`FileHandle`

[in] 파일 개체에 대한 핸들입니다. 핸들은 NtCreateFile 또는 NtOpenFile에 대한 성공적인 호출 또는 동등한 파일 작성 또는 열기 루틴에 의해 작성됩니다.

`IoStatusBlock`

[out] 최종 완료 상태 및 작업에 대한 정보를 수신하는 IO_STATUS_BLOCK 구조에 대한 포인터. 정보 멤버는이 루틴이 실제로 FileInformation 버퍼에 쓰는 바이트 수를받습니다.

`FileInformation`

[out] 루틴이 파일 객체에 대해 요청 된 정보를 쓰는 호출자 할당 버퍼의 포인터. FileInformationClass 매개 변수는 호출자가 요청하는 정보 유형을 지정합니다.

`Length`

[in] FileInformation이 가리키는 버퍼의 크기 (바이트)입니다.

`FileInformationClass`

[in] FileInformation이 가리키는 버퍼에서 파일에 대해 반환 할 정보 유형을 지정합니다. 장치 및 중간 드라이버는 다음 FILE_INFORMATION_CLASS 값 중 하나를 지정할 수 있습니다 (가장 낮은 열거 형 값부터 가장 높은 열거 형 값까지 나열 됨).

| FILE_INFORMATION_CLASS value                          | Type of information returned                                 |
| :---------------------------------------------------- | :----------------------------------------------------------- |
| **FileBasicInformation** (4)                          | A [FILE_BASIC_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_basic_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다.<br>FILE_BASIC_INFORMATION 구조에는 파일의 타임 스탬프와 기본 속성이 포함됩니다. 파일 정보를 쿼리하거나 설정하는 루틴에 대한 인수로 사용됩니다. |
| **FileStandardInformation** (5)                       | A [FILE_STANDARD_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_standard_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다. |
| **FileInternalInformation** (6)                       | A [FILE_INTERNAL_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_internal_information) structure. 이 구조는 NTFS에서 파일을 고유하게 식별하는 64 비트 파일 ID를 지정합니다. 다른 파일 시스템에서는이 파일 ID가 고유하다고 보장 할 수 없습니다. |
| **FileEaInformation** (7)                             | A [FILE_EA_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_ea_information) structure. 이 구조는 파일과 연관된 확장 된 속성 블록의 크기를 지정합니다. |
| **FileAccessInformation** (8)                         | A [FILE_ACCESS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_access_information) structure. 이 구조는 액세스 마스크를 포함합니다. 액세스 마스크에 대한 자세한 내용은 ACCESS_MASK를 참조하십시오. |
| **FileNameInformation** (9)                           | A [FILE_NAME_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_name_information) structure. 구조에는 파일의 전체 경로 또는 일부만 포함될 수 있습니다. 호출자는 DesiredAccess에 대한 특별한 요구 사항없이 파일이 열려있는 동안이 정보를 쿼리 할 수 있습니다. 파일 이름 구문에 대한 자세한 내용은이 항목 뒷부분의 설명 섹션을 참조하십시오. |
| **FilePositionInformation** (14)                      | A [FILE_POSITION_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_position_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 DesiredAccess FILE_READ_DATA 또는 FILE_WRITE_DATA 플래그와 CreateOptions 매개 변수에 지정된 FILE_SYNCHRONOUS_IO_ALERT 또는 FILE_SYNCHRONOUS_IO_NONALERT 플래그를 사용하여 파일을 열었어야합니다. |
| **FileModeInformation** (16)                          | A [FILE_MODE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_mode_information) structure. 이 구조는 파일에 액세스 할 수있는 모드를 지정하는 플래그 세트를 포함합니다. 이러한 플래그는 IoCreateFile 루틴의 CreateOptions 매개 변수에 지정할 수있는 옵션의 하위 집합입니다. |
| **FileAlignmentInformation** (17)                     | A [FILE_ALIGNMENT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_alignment_information) structure. 호출자는 DesiredAccess에 대한 특별한 요구 사항없이 파일이 열려있는 동안이 정보를 쿼리 할 수 있습니다. 이 정보는 CreateOptions 매개 변수에 지정된 FILE_NO_INTERMEDIATE_BUFFERING 플래그로 파일을 연 경우 유용합니다. |
| **FileAllInformation** (18)                           | A [FILE_ALL_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_all_information) structure. 여러 파일 정보 구조를 단일 구조로 결합함으로써 FILE_ALL_INFORMATION은 파일에 대한 정보를 얻는 데 필요한 쿼리 수를 줄입니다. |
| **FileAlternateNameInformation** (21)                 | A [FILE_ALL_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_all_information) structure . 파일에 대한 대체 이름 정보 (8.3 형식 이름)를 반환합니다. |
| **FileStreamInformation** (22)                        | A buffer of [FILE_STREAM_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stream_information) structures. 파일 또는 디렉토리의 데이터 스트림을 열거합니다. |
| **FileCompressionInformation** (28)                   | A [FILE_COMPRESSION_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_compression_information) structure .파일에 대한 압축 정보를 반환합니다. |
| **FileNetworkOpenInformation** (34)                   | A [FILE_NETWORK_OPEN_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_network_open_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다. |
| **FileAttributeTagInformation** (35)                  | A [FILE_ATTRIBUTE_TAG_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_attribute_tag_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다. |
| **FileIoPriorityHintInformation** (43)                | A [FILE_IO_PRIORITY_HINT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_io_priority_hint_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_DATA 플래그를 사용하여 파일을 열었어야합니다. |
| **FileSfioReserveInformation** (44)                   | A [FILE_SFIO_RESERVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-file_sfio_reserve_information) structure. |
| **FileHardLinkInformation** (46)                      | A [FILE_LINKS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_links_information) structure. |
| **FileNormalizedNameInformation** (48)                | A [FILE_NAME_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_name_information) structure . 파일의 정규화 된 이름을 반환합니다. |
| **FileIsRemoteDeviceInformation** (51)                | A [FILE_IS_REMOTE_DEVICE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_is_remote_device_information) structure. 호출자는 DesiredAccess에 대한 특별한 요구 사항없이 파일이 열려있는 동안이 정보를 쿼리 할 수 있습니다. |
| **FileStandardLinkInformation** (54)                  | A [FILE_STANDARD_LINK_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-file_standard_link_information) structure. |
| **FileIdInformation** (59)                            | A [FILE_ID_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-file_id_information) structure. |
| **FileDesiredStorageClassInformation** (67)           | A [FILE_DESIRED_STORAGE_CLASS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_desired_storage_class_information) structure. |
| **FileStatInformation** (68)                          | [FILE_STAT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stat_information) |
| **FileStatLxInformation** (70)                        | [FILE_STAT_LX_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stat_lx_information) |
| **FileCaseSensitiveInformation** (71)                 | A [FILE_CASE_SENSITIVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_case_sensitive_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다. 이 값은 Windows 10 버전 1803부터 사용할 수 있습니다. |
| **FileStorageReserveIdInformation** (74)              | A [FILE_STORAGE_RESERVE_ID_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_storage_reserve_id_information) structure. |
| **FileCaseSensitiveInformationForceAccessCheck** (75) | A [FILE_CASE_SENSITIVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_case_sensitive_information) structure. 호출자는 DesiredAccess 매개 변수에 지정된 FILE_READ_ATTRIBUTES 플래그로 파일을 열었어야합니다. 이것은 사용자 모드 호출자에 적용되는 검사와 유사하게 IOManager가 커널 모드 드라이버에 대한 액세스 검사를 수행하도록 강제하는 데 사용되는 FileCaseSensitiveInformation 작업의 특수 버전입니다. 이 작업은 IOManager에서만 인식되며 파일 시스템은이를 수신하지 않아야합니다. 이 값은 Windows 10 버전 1803부터 사용할 수 있습니다. |



### Return value

NtQueryInformationFile은 STATUS_SUCCESS 또는 적절한 NTSTATUS 오류 코드를 반환합니다.



### Remarks

NtQueryInformationFile은 지정된 파일 개체에 대한 정보를 반환합니다. 특정 장치 또는 파일 시스템에서 지원하지 않는 FILE_XXX_INFORMATION 구조의 모든 구성원에서 0을 반환합니다.

FileInformationClass = FileNameInformation이면 파일 이름이 FILE_NAME_INFORMATION 구조로 반환됩니다. 파일 이름의 정확한 구문은 다음과 같은 여러 요인에 따라 달라집니다.

- NtCreateFile에 전체 경로를 제출하여 파일을 연 경우 NtQueryInformationFile은 해당 전체 경로를 반환합니다.
- ObjectAttributes- RootDirectory 핸들이 NtCreateFile에 대한 호출에서 이름으로 열렸고 이후에이 루트 디렉터리 핸들을 기준으로 NtCreateFile에 의해 파일이 열린 경우 NtQueryInformationFile은 전체 경로를 반환합니다.
- ObjectAttributes- RootDirectory 핸들이 NtCreateFile에 대한 호출에서 파일 ID (FILE_OPEN_BY_FILE_ID 플래그 사용)로 열리고 이후에이 루트 디렉터리 핸들에 대한 NtCreateFile에 의해 파일이 열린 경우 NtQueryInformationFile은 상대 경로를 반환합니다.
- 그러나 사용자에게 SeChangeNotifyPrivilege (Microsoft Windows SDK 설명서에 설명 됨)가있는 경우 NtQueryInformationFile은 모든 경우에 전체 경로를 반환합니다.
- 상대 경로 만 반환되는 경우 파일 이름 문자열은 백 슬래시로 시작하지 않습니다.
- 전체 경로와 파일 이름이 반환되면 문자열은 위치에 관계없이 단일 백 슬래시로 시작됩니다. 따라서 C:\dir1\dir2\filename.ext 일은 di\dir1\dir2\filename.ext, 로 표시되고 \server\share\dir1\dir2\filename.ext 파일은 \server\share\dir1\dir2\filename.ext 로 표시됩니다. 

버퍼 오버플로로 인해 NtQueryInformationFile이 실패하면 FileNameInformation을 구현하는 드라이버는 버퍼에 맞는 파일 이름의 WCHAR 문자를 반환하고 FILE_NAME_INFORMATION 구조의 FileNameLength 매개 변수에 필요한 전체 길이를 지정해야합니다. 전체 파일 이름을 검색 할 수 있도록 파일 이름 길이를 사용하여 쿼리를 재발행해야합니다. 이 패턴을 따르지 않는 드라이버는 전체 파일 이름을 검색 할 때까지 점진적으로 길이를 늘려야 할 수 있습니다. 파일 작업에 대한 자세한 내용은 드라이버에서 파일 사용을 참조하십시오.

NtQueryInformationFile의 호출자는 IRQL = PASSIVE_LEVEL에서 실행되고 특수 커널 APC가 활성화되어 있어야합니다.

```
Note
이 함수에 대한 호출이 사용자 모드에서 발생하는 경우 'ZwQueryInformationFile'대신 'NtQueryInformationFile'이름을 사용해야합니다.
```

커널 모드 드라이버에서 호출하는 경우 Windows 기본 시스템 서비스 루틴의 NtXxx 및 ZwXxx 버전은 입력 매개 변수를 처리하고 해석하는 방식에서 다르게 동작 할 수 있습니다. 루틴의 NtXxx 및 ZwXxx 버전 간의 관계에 대한 자세한 내용은 [기본 시스템 서비스 루틴의 Nt 및 Zw 버전 사용을 참조](https://docs.microsoft.com/en-us/windows-hardware/drivers/kernel/using-nt-and-zw-versions-of-the-native-system-services-routines)하십시오.



### 관련 구조체

[FILE_BASIC_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_basic_information)

FILE_BASIC_INFORMATION 구조체 에는 파일의 타임 스탬프와 기본 속성이 포함됩니다. 파일 정보를 쿼리하거나 설정하는 루틴에 대한 인수로 사용됩니다.

#### Syntax

``` c++
typedef struct _FILE_BASIC_INFORMATION {
  LARGE_INTEGER CreationTime;
  LARGE_INTEGER LastAccessTime;
  LARGE_INTEGER LastWriteTime;
  LARGE_INTEGER ChangeTime;
  ULONG         FileAttributes;
} FILE_BASIC_INFORMATION, *PFILE_BASIC_INFORMATION;
```

#### Members

`CreationTime`

파일이 생성 된 시간을 지정합니다.

`LastAccessTime`

파일이 마지막으로 액세스 된 시간을 지정합니다.

`LastWriteTime`

파일이 마지막으로 기록 된 시간을 지정합니다.

`ChangeTime`

파일이 마지막으로 변경된 시간을 지정합니다.

`FileAttributes`

하나 이상의 FILE_ATTRIBUTE_XXX 플래그를 지정합니다. 이러한 플래그에 대한 설명은 Microsoft Windows SDK의 파일 속성 상수를 참조하십시오.

#### Remarks

FILE_ATTRIBUTE_NORMAL 플래그는 다른 속성과 함께 설정하거나 반환 할 수 없습니다. 다른 모든 FileAttributes 값은이 속성을 재정의합니다.

시간 값 CreationTime, LastAccessTime, LastWriteTime 및 ChangeTime은 절대 시스템 시간 형식으로 표현됩니다. 절대 시스템 시간은 그레고리력으로 1601 년이 시작된 이후 100 나노초 간격의 수입니다.

FILE_BASIC_INFORMATION 구조의 XxxTime 멤버에 대해 0 값을 지정하면 ZwSetInformationFile 함수는 해당 시간에 대한 파일의 현재 설정을 유지합니다.

파일 시스템은 파일에서 I/O 작업이 수행 된 후 LastAccessTime, LastWriteTime 및 ChangeTime 멤버의 값을 적절하게 업데이트합니다. 드라이버 또는 응용 프로그램은 적절한 멤버를 -1로 설정하여 호출자의 파일 핸들에서 수행되는 I/O 작업에 대해 이러한 멤버 중 하나 이상을 업데이트하지 않도록 파일 시스템에 요청할 수 있습니다. 호출자는이 세 멤버의 하나, 모두 또는 다른 조합을 -1로 설정할 수 있습니다. -1로 설정된 멤버 만 파일 핸들에 대한 I/O 작업의 영향을받지 않습니다. 다른 구성원은 적절하게 업데이트됩니다. NTFS 및 ReFS 시스템에서는 적절한 구성원을 -2로 설정하여 파일 핸들의 타임 스탬프 업데이트를 복원 할 수 있습니다.

이 구조의 멤버를 설정하려면 호출자에게 파일에 대한 FILE_WRITE_ATTRIBUTES 액세스 권한이 있어야합니다.



[FILE_STANDARD_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_standard_information)

FILE_STANDARD_INFORMATION 구조는 파일 정보를 쿼리하거나 설정하는 루틴에 대한 인수로 사용됩니다.

#### Syntax

``` c++
typedef struct _FILE_STANDARD_INFORMATION {
  LARGE_INTEGER AllocationSize;
  LARGE_INTEGER EndOfFile;
  ULONG         NumberOfLinks;
  BOOLEAN       DeletePending;
  BOOLEAN       Directory;
} FILE_STANDARD_INFORMATION, *PFILE_STANDARD_INFORMATION;
```

#### Members

`AllocationSize`

파일 할당 크기 (바이트)입니다. 일반적으로이 값은 기본 물리적 장치의 섹터 또는 클러스터 크기의 배수입니다.

`EndOfFile`

바이트 오프셋으로 파일 위치의 끝입니다.

`NumberOfLinks`

파일에 대한 하드 링크 수입니다.

`DeletePending`

삭제 보류 상태입니다. TRUE는 파일 삭제가 요청되었음을 나타냅니다.

`Directory`

파일 디렉토리 상태. TRUE는 파일 개체가 디렉터리를 나타냄을 나타냅니다.

#### Remarks

EndOfFile은 파일 끝까지의 바이트 오프셋을 지정합니다. 이 값은 0부터 시작하므로 실제로 파일의 첫 번째 빈 바이트를 참조합니다. 즉, 파일에서 마지막 유효한 바이트 바로 다음 바이트에 대한 오프셋입니다.



[FILE_INTERNAL_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_internal_information)

FILE_INTERNAL_INFORMATION 구조는 파일에 대한 파일 시스템의 8 바이트 파일 참조 번호를 쿼리하는 데 사용됩니다.

#### Syntax

``` c++
typedef struct _FILE_INTERNAL_INFORMATION {
  LARGE_INTEGER IndexNumber;
} FILE_INTERNAL_INFORMATION, *PFILE_INTERNAL_INFORMATION;
```

#### Members

`IndexNumber`

파일의 8 바이트 파일 참조 번호입니다. 이 번호는 파일 시스템에 의해 할당되며 파일 시스템에 따라 다릅니다. (이것은 Microsoft Windows 2000 용 NTFS에 추가 된 16 바이트 '파일 개체 ID'와 동일하지 않습니다.)

#### Remarks

이 정보는 다음 방법 중 하나로 조회 할 수 있습니다.

- FltQueryInformationFile 또는 ZwQueryInformationFile을 호출하고 FileInternalInformation을 FileInformationClass의 값으로 전달하고 호출자 할당 FILE_INTERNAL_INFORMATION 구조 버퍼를 FileInformation의 값으로 전달합니다.
- 주요 기능 코드 IRP_MJ_QUERY_INFORMATION으로 IRP를 생성합니다.

이 정보를 쿼리하는 데 특정 액세스 권한이 필요하지 않습니다.
FILE_INTERNAL_INFORMATION 구조의 IndexNumber 멤버는 FILE_ID_BOTH_DIR_INFORMATION 및 FILE_ID_FULL_DIR_INFORMATION 구조의 FileId 멤버와 동일합니다.

파일 ID라고도하는 파일 참조 번호는 정적 파일 시스템 내에서만 고유합니다. 파일 시스템은 자유롭게 재사용 할 수 있기 때문에 시간이 지남에 따라 고유하다고 보장 할 수 없습니다. 또한 일정하게 유지된다는 보장도 없습니다. 예를 들어, FAT 파일 시스템은 디스크에있는 파일 디렉토리 항목 레코드 (DIRENT)의 바이트 오프셋에서 파일에 대한 파일 참조 번호를 생성합니다. 조각 모음은이 바이트 오프셋을 변경할 수 있습니다. 따라서 FAT 파일 참조 번호는 시간이 지남에 따라 변경 될 수 있습니다.

FileInformation 매개 변수에서 FltQueryInformationFile 또는 ZwQueryInformationFile로 전달되는 버퍼의 크기는 최소한 sizeof (FILE_INTERNAL_INFORMATION) 여야합니다.

이 구조는 LONGLONG (8 바이트) 경계에서 정렬되어야합니다.



[FILE_ACCESS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_access_information) 

FILE_ACCESS_INFORMATION 구조는 파일의 액세스 권한을 쿼리하거나 설정하는 데 사용됩니다.

#### Syntax

``` c++
typedef struct _FILE_ACCESS_INFORMATION {
  ACCESS_MASK AccessFlags;
} FILE_ACCESS_INFORMATION, *PFILE_ACCESS_INFORMATION;
```

#### Members

`AccessFlags`

액세스 제어 항목의 액세스 마스크에서 액세스 권한 세트를 지정하는 플래그입니다. 이 멤버는 [ACCESS_MASK](https://docs.microsoft.com/en-us/windows-hardware/drivers/kernel/access-mask) 유형의 값입니다.

#### Remarks

이 구조는 ZwQueryInformationFile 루틴에서 사용됩니다.

AccessFlags 매개 변수는 파일 개체에 대한 사용자 권한을 인코딩하는 액세스 마스크입니다. 액세스 마스크는 사용자 또는 사용자 그룹에 할당 된 개체에 대한 권한을 인코딩하거나 사용자가 개체를 열 때 요청 된 액세스를 인코딩하는 데 사용할 수 있습니다. 자세한 내용은 ACCESS_MASK를 참조하십시오.



[FILE_NAME_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_name_information)

FILE_NAME_INFORMATION 구조는 [ZwQueryInformationFile](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/nf-ntifs-ntqueryinformationfile) 및 [ZwSetInformationFile](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/nf-ntifs-ntsetinformationfile) 루틴에 대한 인수로 사용됩니다.

#### Syntax

``` c++
typedef struct _FILE_NAME_INFORMATION {
  ULONG FileNameLength;
  WCHAR FileName[1];
} FILE_NAME_INFORMATION, *PFILE_NAME_INFORMATION;
```

#### Members

`FileNameLength`

파일 이름 문자열의 길이 (바이트)를 지정합니다.

`FileName`

파일 이름 문자열의 첫 번째 문자를 지정합니다. 그 다음에는 나머지 문자열이 메모리에 표시됩니다.

#### Remarks

ZwQueryInformationFile 루틴은이 구조를 사용하여 파일 이름 문자열을 호출자에게 반환합니다. 반환 된 이름의 형식에 대한 자세한 내용은 ZwQueryInformationFile을 참조하십시오.

ZwSetInformationFile의 호출자는이 구조를 사용하여 파일에 대한 새로운 짧은 이름을 지정할 수 있습니다.

 

[FILE_MODE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_mode_information)



[FILE_ALIGNMENT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_alignment_information)



[FILE_ALL_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_all_information)



[FILE_STREAM_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stream_information)



[FILE_COMPRESSION_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_compression_information)



[FILE_NETWORK_OPEN_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_network_open_information)



[FILE_ATTRIBUTE_TAG_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntddk/ns-ntddk-_file_attribute_tag_information)



[FILE_IO_PRIORITY_HINT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_io_priority_hint_information)



[FILE_SFIO_RESERVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-file_sfio_reserve_information)



[FILE_LINKS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_links_information) 



[FILE_IS_REMOTE_DEVICE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_file_is_remote_device_information)



[FILE_STANDARD_LINK_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-file_standard_link_information)



[FILE_ID_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-file_id_information)



[FILE_DESIRED_STORAGE_CLASS_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_desired_storage_class_information)



[FILE_STAT_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stat_information)



[FILE_STAT_LX_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_stat_lx_information)



[FILE_CASE_SENSITIVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_case_sensitive_information)



[FILE_STORAGE_RESERVE_ID_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_storage_reserve_id_information)




[FILE_CASE_SENSITIVE_INFORMATION](https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/ntifs/ns-ntifs-_file_case_sensitive_information)

