# FileDisk 분석

원본 소스 위치: <http://www.acc.umu.se/~bosse/>

검색어: FileDisk



## 1. Application 분석

이 장 에서는 Application 에서 사용하는 주요 함수에 관하여 분석 결과를 정리 한다.

| 주요 함수       | 설명                                                         |
| --------------- | ------------------------------------------------------------ |
| DeviceIoControl | 지정된 장치 드라이버에 직접 제어 코드를 보내고 해당 장치가 해당 작업을 수행하게합니다. |
| SHChangeNotify | 응용 프로그램이 수행 한 이벤트를 시스템에 알립니다. 응용 프로그램은 셸에 영향을 줄 수있는 작업을 수행하는 경우 이 함수를 사용해야합니다. |
| CreateFile      | 파일 또는 I/O 장치를 만들거나 엽니다. 가장 일반적으로 사용되는 I/O 장치는 파일, 파일 스트림, 디렉터리, 실제 디스크, 볼륨, 콘솔 버퍼, 테이프 드라이브, 통신 리소스, 메일 슬롯 및 파이프입니다. 이 함수는 파일이나 장치, 지정된 플래그 및 속성에 따라 다양한 유형의 I/O에 대해 파일이나 장치에 액세스하는 데 사용할 수있는 핸들을 반환합니다.<br/>이 작업을 트랜잭션 된 작업으로 수행하면 트랜잭션 I/O에 사용할 수있는 핸들이 만들어 지므로 CreateFileTransacted 함수를 사용하십시오. |
| DefineDosDevice | MS-DOS 장치 이름을 정의, 재정의 또는 삭제합니다.             |
| FormatMessage   | 메시지 문자열을 형식화합니다. 이 함수는 메시지 정의를 입력으로 요구합니다. 메시지 정의는 함수에 전달 된 버퍼에서 가져올 수 있습니다. 이미 로드된 모듈의 메시지 테이블 리소스에서 가져올 수 있습니다. 또는 호출자가 함수에 시스템의 메시지 테이블 리소스에서 메시지 정의를 검색하도록 요청할 수 있습니다. 이 함수는 메시지 식별자 및 언어 식별자를 기반으로 메시지 테이블 자원에서 메시지 정의를 찾습니다. 이 함수는 형식화 된 메시지 텍스트를 출력 버퍼로 복사하여 요청 된 경우 모든 삽입 된 삽입 시퀀스를 처리합니다. |



### 1.1 DeviceIoControl

지정된 장치 드라이버에 직접 제어 코드를 보내고 해당 장치가 해당 작업을 수행하게합니다.



#### 1.1.1 Syntax

```c++
BOOL DeviceIoControl(
  HANDLE       hDevice,
  DWORD        dwIoControlCode,
  LPVOID       lpInBuffer,
  DWORD        nInBufferSize,
  LPVOID       lpOutBuffer,
  DWORD        nOutBufferSize,
  LPDWORD      lpBytesReturned,
  LPOVERLAPPED lpOverlapped
);
```



#### 1.1.2 Parameters

##### <span style="color:green">hDevice</span>

작업이 수행 될 Device 의 Handle. Device는 일반적으로 볼륨, 디렉토리, 파일 또는 스트림입니다. Device Handle을 검색하려면 CreateFile 함수를 사용하십시오. 자세한 내용은 주의를 참조하십시오.

##### <span style="color:green">dwIoControlCode</span>

작업의 제어 코드입니다. 이 값은 수행 할 특정 조작과 이를 수행 할 장치의 유형을 식별합니다.

제어 코드 목록을 보려면 비고를 참조하십시오. 각 제어 코드에 대한 문서는 lpInBuffer, nInBufferSize, lpOutBuffer 및 nOutBufferSize 매개 변수에 대한 사용법 세부 정보를 제공합니다.

**CTL_CODE**

이 매크로는 고유 한 시스템 I/O 제어를 만듭니다.

```c++
#define CTL_CODE(      DeviceType, Function, Method, Access) (
  ((DeviceType) << 16) | ((Access) << 14) | ((Function) << 2) | (Method))
```

- *DeviceType*

  - 주어진 IOCTL에 대한 장치 유형을 정의합니다.
  - 이 매개 변수는 WORD 값보다 클 수 없습니다.
  - Microsoft에서 사용하는 값의 범위는 0-32767입니다. 값 32768-65535는 OEM 및 IHV 용으로 예약되어 있습니다.
  - 이 프로그램 에서는 FILE_DEVICE_DISK 사용

- *Function*

  - 장치 범주 내의 작업을 정의합니다.
  - 함수 코드 0-2047은 예약되어 있습니다. 코드 2048-4095는 OEM 및 IHV 용으로 예약되어 있습니다.
  - 함수 코드는 4095보다 클 수 없습니다.

- *Method*

  - I/O 및 파일 시스템 제어를 위해 버퍼를 전달하는 방법에 대한 메소드 코드를 정의합니다.
  - 이 매개 변수에는 다음 값을 사용할 수 있습니다. METHOD_BUFFERED, METHOD_IN_DIRECT, METHOD_OUT_DIRECT, METHOD_NEITHER
  - 이 필드는 Windows Embedded Compact에서 무시됩니다. 다른 Method 값을 사용하여 Windows 기반 데스크톱 플랫폼과의 호환성이 필요한 경우가 아니면 항상 METHOD_BUFFERED 값을 사용해야합니다.

- *Access*

  - 모든 액세스에 대한 액세스 검사 값을 정의합니다.

  - 다음 표에서는이 매개 변수에 사용할 수있는 플래그를 보여줍니다. FILE_ANY_ACCESS는 일반적으로 올바른 값입니다.

    | Flag              | Description                                                  |
    | :---------------- | :----------------------------------------------------------- |
    | FILE_ANY_ACCESS   | 모든 액세스를 요청합니다.                                    |
    | FILE_READ_ACCESS  | 읽기 권한을 요청한다. FILE_WRITE_ACCESS와 함께 사용할 수 있습니다. |
    | FILE_WRITE_ACCESS | 쓰기 권한을 요청한다. FILE_READ_ACCESS와 함께 사용할 수 있습니다. |



##### <span style="color:green">lpInBuffer</span>

작업을 수행하는 데 필요한 데이터가 들어있는 입력 버퍼에 대한 포인터입니다. 이 데이터의 형식은 dwIoControlCode 매개 변수의 값에 따라 다릅니다.

dwIoControlCode가 입력 데이터를 필요로하지 않는 조작을 지정하는 경우,이 파라미터는 NULL가됩니다.

##### <span style="color:green">nInBufferSize</span>

입력 버퍼의 크기 (바이트).

##### <span style="color:green">lpOutBuffer</span>

조작에 의해 리턴 된 데이터를 수신하는 출력 버퍼에 대한 포인터. 이 데이터의 형식은 dwIoControlCode 매개 변수의 값에 따라 다릅니다.

dwIoControlCode가 데이터를 돌려주지 않는 조작을 지정하면 (자),이 파라미터는 NULL가됩니다.

##### <span style="color:green">nOutBufferSize</span>

출력 버퍼의 크기 (바이트).

##### <span style="color:green">lpBytesReturned</span>

출력 버퍼에 저장된 데이터의 크기를 바이트 단위로받는 변수에 대한 포인터입니다.

출력 버퍼가 너무 작아서 데이터를 수신 할 수없는 경우 호출은 실패하고 GetLastError는 ERROR_INSUFFICIENT_BUFFER를 반환하고 lpBytesReturned는 0입니다.

출력 버퍼가 너무 작아서 모든 데이터를 저장할 수 있지만 일부 항목을 저장할 수있는 경우 일부 드라이버는 맞는만큼 많은 데이터를 반환합니다. 이 경우 호출은 실패하고 GetLastError는 ERROR_MORE_DATA를 반환하고 lpBytesReturned는 수신 된 데이터의 양을 나타냅니다. 응용 프로그램은 새로운 시작점을 지정하여 동일한 작업으로 DeviceIoControl을 다시 호출해야합니다.

lpOverlapped가 NULL이면 lpBytesReturned는 NULL 일 수 없습니다. 연산이 출력 데이터를 반환하지 않고 lpOutBuffer가 NULL 일 때도 DeviceIoControl은 lpBytesReturned를 사용합니다. 이러한 작업 후에 lpBytesReturned의 값은 의미가 없습니다.

lpOverlapped가 NULL이 아닌 경우 lpBytesReturned는 NULL 일 수 있습니다. 이 매개 변수가 NULL이 아니고 연산이 데이터를 반환하면 겹침 연산이 완료 될 때까지 lpBytesReturned가 의미가 없습니다. 반환 된 바이트 수를 검색하려면 GetOverlappedResult를 호출합니다. hDevice가 I/O 완료 포트와 연결되어 있으면 GetQueuedCompletionStatus를 호출하여 반환 된 바이트 수를 검색 할 수 있습니다.

##### <span style="color:green">lpOverlapped</span>

OVERLAPPED 구조체에 대한 포인터입니다.

FILE_FLAG_OVERLAPPED를 지정하지 않고 hDevice를 열면 lpOverlapped가 무시됩니다.

FILE_FLAG_OVERLAPPED 플래그로 hDevice를 연 경우 겹친 (비동기) 작업으로 작업이 수행됩니다. 이 경우 lpOverlapped는 이벤트 객체에 대한 핸들을 포함하는 유효한 OVERLAPPED 구조를 가리켜 야합니다. 그렇지 않으면 예기치 않은 방식으로 함수가 실패합니다.

중첩 된 작업의 경우 DeviceIoControl이 즉시 반환하고 작업이 완료되면 이벤트 객체가 신호됩니다. 그렇지 않으면 작업이 완료되거나 오류가 발생할 때까지 함수가 반환되지 않습니다.

#### 1.1.3 Return Value

작업이 성공적으로 완료되면 반환 값은 0이 아닙니다.

작업이 실패하거나 보류중인 경우 반환 값은 0입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### 1.1.4 Remarks

장치에 대한 핸들을 검색하려면 장치의 이름이나 장치와 관련된 드라이버의 이름으로 CreateFile 함수를 호출해야합니다. 장치 이름을 지정하려면 다음 형식을 사용하십시오.

```
\.<i>DeviceName
```

DeviceIoControl은 특정 장치에 대한 핸들을 허용 할 수 있습니다. 예를 들어 논리 드라이브 A :에 대한 핸들을 CreateFile과 함께 열려면 `\.\a:.`을 지정하십시오. 또는 `\.\PhysicalDrive0, \.\`PhysicalDrive1 등의 이름을 사용하여 시스템의 실제 드라이브에 대한 핸들을 열 수 있습니다.

장치 드라이버 핸들을 열기 위해 **CreateFile**을 호출 할 때 FILE_SHARE_READ 및 FILE_SHARE_WRITE 액세스 플래그를 지정해야합니다. 그러나 직렬 포트와 같은 통신 자원을 열 때 독점적 액세스를 지정해야합니다. 장치 핸들을 열 때 다음과 같이 다른 **CreateFile** 매개 변수를 사용하십시오.

- fdwCreate 매개 변수는 OPEN_EXISTING을 지정해야합니다.
- hTemplateFile 매개 변수는 NULL이어야합니다.
- fdwAttrsAndFlags 매개 변수는 FILE_FLAG_OVERLAPPED를 지정하여 반환 된 핸들을 겹쳐진 (비동기식) I/O 작업에서 사용할 수 있음을 나타낼 수 있습니다.

지원되는 제어 코드 목록은 다음 항목을 참조하십시오.

- [Communications Control Codes](https://msdn.microsoft.com/a9aed6bb-05aa-4378-837a-ea7ccda39ba4)
- [Device Management Control Codes](https://msdn.microsoft.com/b3a3ffa1-e710-4d96-aff8-5b6876ab032b)
- [Directory Management Control Codes](https://msdn.microsoft.com/e2e671c7-ef65-4401-8016-649e86f84fec)
- [Disk Management Control Codes](https://msdn.microsoft.com/488a7d32-cbb5-4f32-9655-0aca8ac69640)
- [File Management Control Codes](https://msdn.microsoft.com/e27ded4b-d104-4244-b38e-5fed10d32e1e)
- [Power Management Control Codes](https://msdn.microsoft.com/027fffdb-62a1-47d8-b69f-c2fcf7f9ac97)
- [Volume Management Control Codes](https://msdn.microsoft.com/87f39e1c-3ebf-4c6f-a842-699ec3c45e76)



##### 1.1.4.1 [Communications Control Codes](https://msdn.microsoft.com/a9aed6bb-05aa-4378-837a-ea7ccda39ba4)

다음 제어 코드는 장치 입력 및 출력과 함께 사용됩니다.

| Control code                                                 | Operation                                                    |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**IOCTL_SERIAL_LSRMST_INSERT**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_serial_lsrmst_insert) | 라인 및 모뎀 상태 데이터를 데이터 스트림에 배치 할 것인지 여부를 설정합니다. |



##### 1.1.4.2 [Device Management Control Codes](https://msdn.microsoft.com/b3a3ffa1-e710-4d96-aff8-5b6876ab032b)

체인저 장치에는 다음과 같은 제어 코드가 사용됩니다.

| Value                                                        | Meaning                                                      |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**IOCTL_CHANGER_EXCHANGE_MEDIUM**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_exchange_medium) | 원본 요소에서 하나의 대상으로 미디어 조각을 이동하고 원래 대상의 첫 번째 대상에서 두 번째 대상으로 미디어를 이동합니다. |
| [**IOCTL_CHANGER_GET_ELEMENT_STATUS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_get_element_status) | 모든 요소의 상태 또는 특정 유형의 지정된 수의 요소를 검색합니다. |
| [**IOCTL_CHANGER_GET_PARAMETERS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_get_parameters) | 지정된 장치의 매개 변수를 검색합니다.                        |
| [**IOCTL_CHANGER_GET_PRODUCT_DATA**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_get_product_data) | 지정된 장치에 대한 제품 데이터를 검색합니다.                 |
| [**IOCTL_CHANGER_GET_STATUS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_get_status) | 지정된 장치의 현재 상태를 검색합니다.                        |
| [**IOCTL_CHANGER_INITIALIZE_ELEMENT_STATUS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_initialize_element_status) | 모든 요소 또는 특정 유형의 지정된 요소의 상태를 초기화합니다. |
| [**IOCTL_CHANGER_MOVE_MEDIUM**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_move_medium) | 미디어를 대상으로 이동합니다.                                |
| [**IOCTL_CHANGER_QUERY_VOLUME_TAGS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_query_volume_tags) | 지정된 요소에 대한 볼륨 태그 정보를 검색합니다.              |
| [**IOCTL_CHANGER_REINITIALIZE_TRANSPORT**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_reinitialize_transport) | 전송 요소를 물리적으로 다시 조정합니다.                      |
| [**IOCTL_CHANGER_SET_ACCESS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_set_access) | 장치의 삽입 / 배출 포트, 도어 또는 키패드의 상태를 설정합니다. |
| [**IOCTL_CHANGER_SET_POSITION**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_changer_set_position) | 체인저의 로봇 전송 메커니즘을 지정된 요소 주소로 설정합니다. |



다음 제어 코드는 장치 관리와 함께 사용됩니다.

| Control code                                                 | Operation                                                    |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**IOCTL_STORAGE_CHECK_VERIFY**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_check_verify) | 이동식 미디어 장치에서 변경 사항을 확인합니다.               |
| [**IOCTL_STORAGE_EJECT_MEDIA**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_eject_media) | SCSI 장치에서 미디어를 꺼냅니다.                             |
| [**IOCTL_STORAGE_EJECTION_CONTROL**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_ejection_control) | 미디어를 배출하는 메커니즘을 활성화하거나 비활성화합니다.    |
| [**IOCTL_STORAGE_GET_DEVICE_NUMBER**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_get_device_number) | 장치 유형, 장치 번호 및 파티션 할 수있는 장치의 경우 장치의 파티션 번호를 검색합니다. |
| [**IOCTL_STORAGE_GET_HOTPLUG_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_get_hotplug_info) | 장치 유형, 장치 번호 및 파티션 할 수있는 장치의 경우 장치의 파티션 번호를 검색합니다. |
| [**IOCTL_STORAGE_GET_MEDIA_SERIAL_NUMBER**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_get_media_serial_number) | USB 장치의 일련 번호를 검색합니다.                           |
| [**IOCTL_STORAGE_GET_MEDIA_TYPES**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_get_media_types) | 미디어 타입을 검색합니다.                                    |
| [**IOCTL_STORAGE_GET_MEDIA_TYPES_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_get_media_types_ex) | 장치에서 지원하는 미디어 유형에 대한 정보를 검색합니다.      |
| [**IOCTL_STORAGE_LOAD_MEDIA**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_load_media) | 미디어를 장치에로드합니다.                                   |
| [**IOCTL_STORAGE_MANAGE_DATA_SET_ATTRIBUTES**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_manage_data_set_attributes) |                                                              |
| [**IOCTL_STORAGE_MCN_CONTROL**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_mcn_control) | 미디어 변경 알림을 사용하거나 사용하지 않도록 설정합니다.    |
| [**IOCTL_STORAGE_MEDIA_REMOVAL**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_media_removal) | 미디어 꺼내기 메커니즘을 활성화하거나 비활성화합니다.        |
| [**IOCTL_STORAGE_READ_CAPACITY**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_read_capacity) | 장치의 기하학 정보를 검색합니다.                             |
| [**IOCTL_STORAGE_SET_HOTPLUG_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_set_hotplug_info) | 지정된 장치의 핫 플러그 구성을 설정합니다.                   |



##### 1.1.4.3 [Directory Management Control Codes](https://msdn.microsoft.com/e2e671c7-ef65-4401-8016-649e86f84fec)

다음 제어 코드는 파일 압축 및 압축 해제와 함께 사용됩니다.

| Control Code                                                 | Meaning                                                      |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**FSCTL_GET_COMPRESSION**](https://msdn.microsoft.com/en-us/library/Aa364567(v=VS.85).aspx) | 파일 시스템이 스트림 단위 압축을 지원하는 볼륨에서 파일이나 디렉토리의 현재 압축 상태를 검색합니다. |
| [**FSCTL_SET_COMPRESSION**](https://msdn.microsoft.com/en-us/library/Aa364592(v=VS.85).aspx) | 파일 시스템이 파일 단위 및 디렉토리 단위 압축을 지원하는 볼륨에서 파일 또는 디렉토리의 압축 상태를 설정합니다. |

다음 제어 코드는 [재분석 지점](https://docs.microsoft.com/ko-kr/windows/desktop/FileIO/reparse-points) 과 함께 사용됩니다.

**이 섹션의**

| Control Code                                                 | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**FSCTL_DELETE_REPARSE_POINT**](https://msdn.microsoft.com/en-us/library/Aa364560(v=VS.85).aspx) | 지정한 파일 또는 디렉토리에서 재분석 지점을 삭제합니다.      |
| [**FSCTL_GET_REPARSE_POINT**](https://msdn.microsoft.com/en-us/library/Aa364571(v=VS.85).aspx) | 지정된 핸들로 식별되는 파일 또는 디렉토리와 연관된 재분석 지점 데이터를 검색합니다. |
| [**FSCTL_SET_REPARSE_POINT**](https://msdn.microsoft.com/en-us/library/Aa364595(v=VS.85).aspx) | 파일이나 디렉토리에 재분석 지점을 설정합니다.                |





##### 1.1.4.4 [Disk Management Control Codes](https://msdn.microsoft.com/488a7d32-cbb5-4f32-9655-0aca8ac69640)

다음 표에서는 디스크 관리에 사용되는 제어 코드를 보여줍니다.

| Control Code                                                 | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**IOCTL_DISK_ARE_VOLUMES_READY**](https://docs.microsoft.com/ko-kr/windows/desktop/FileIO/ioctl-disk-are-volumes-ready) | 지정된 디스크의 모든 볼륨이 사용될 준비가 될 때까지 대기합니다. |
| [**IOCTL_DISK_CREATE_DISK**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_create_disk) | CREATE_DISK 구조의 정보를 사용하여 지정된 디스크 및 디스크 파티션 테이블을 초기화합니다. |
| [**IOCTL_DISK_DELETE_DRIVE_LAYOUT**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_delete_drive_layout) | 마스터 부트 레코드에서 부트 서명을 제거하여 디스크가 섹터 0 에서 디스크 끝까지 포맷되도록합니다. |
| [**IOCTL_DISK_FORMAT_TRACKS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_format_tracks) | 지정된 연속 된 트랙 세트를 플로피 디스크에 포맷합니다. 추가 매개 변수를 제공하려면 IOCTL_DISK_FORMAT_TRACKS_EX를 대신 사용하십시오. |
| [**IOCTL_DISK_FORMAT_TRACKS_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_format_tracks_ex) | 지정된 연속 된 트랙 세트를 플로피 디스크에 포맷합니다.       |
| [**IOCTL_DISK_GET_CACHE_INFORMATION**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_cache_information) | 디스크 캐시 구성 데이터를 검색합니다.                        |
| [**IOCTL_DISK_GET_CLUSTER_INFO**](https://docs.microsoft.com/ko-kr/windows/desktop/FileIO/ioctl-disk-get-cluster-info) | 지정된 디스크 장치의 특성을 검색합니다.                      |
| [**IOCTL_DISK_GET_DISK_ATTRIBUTES**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_disk_attributes) | 지정된 디스크 장치의 특성을 검색합니다.                      |
| [**IOCTL_DISK_GET_DRIVE_GEOMETRY**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_drive_geometry) | 물리적 디스크의 형상에 대한 정보를 검색합니다. 유형, 실린더 수, 실린더 당 트랙 수, 트랙 당 섹터 수 및 섹터 당 바이트 수를 검색합니다. |
| [**IOCTL_DISK_GET_DRIVE_GEOMETRY_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_drive_geometry_ex) | 물리 디스크의 형상에 대한 확장 정보, 즉 유형, 실린더 수, 실린더 당 트랙, 트랙 당 섹터 및 섹터 당 바이트를 검색합니다. |
| [**IOCTL_DISK_GET_DRIVE_LAYOUT**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_drive_layout) | 디스크의 파티션 테이블에있는 각 항목에 대한 정보를 검색합니다. |
| [**IOCTL_DISK_GET_DRIVE_LAYOUT_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_drive_layout_ex) | 디스크의 파티션 테이블에있는 각 항목에 대한 확장 정보를 검색합니다. |
| [**IOCTL_DISK_GET_LENGTH_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_length_info) | 지정된 디스크, 볼륨 또는 파티션의 길이를 검색합니다.         |
| [**IOCTL_DISK_GET_PARTITION_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_partition_info) | 디스크 파티션의 유형, 크기 및 특성에 대한 정보를 검색합니다. |
| [**IOCTL_DISK_GET_PARTITION_INFO_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_get_partition_info_ex) | 디스크 파티션의 유형, 크기 및 특성에 대한 확장 정보를 검색합니다. |
| [**IOCTL_DISK_GROW_PARTITION**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_grow_partition) | 지정한 파티션을 확대합니다.                                  |
| [**IOCTL_DISK_IS_WRITABLE**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_is_writable) | 지정된 디스크에 쓸 수 있는지 여부를 결정합니다.              |
| [**IOCTL_DISK_PERFORMANCE**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_performance) | 디스크 성능 정보를 제공하는 성능 카운터를 사용합니다.        |
| [**IOCTL_DISK_PERFORMANCE_OFF**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_performance_off) | 디스크 성능 정보를 제공하는 성능 카운터를 사용하지 않도록 설정합니다. |
| [**IOCTL_DISK_REASSIGN_BLOCKS**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_reassign_blocks) | 하나 이상의 블록을 해당 예비 블록 풀에 매핑하도록 디스크 장치에 지시합니다. |
| [**IOCTL_DISK_REASSIGN_BLOCKS_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_reassign_blocks_ex) | 하나 이상의 블록을 해당 예비 블록 풀에 매핑하도록 디스크 장치에 지시합니다. |
| [**IOCTL_DISK_RESET_SNAPSHOT_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_reset_snapshot_info) | 디스크에서 모든 VSS (볼륨 섀도 복사본 서비스) 하드웨어 기반 섀도 복사본 ( '스냅 숏'이라고도 함) 정보를 지 웁니다. <br/>이 작업을 수행하려면 다음 매개 변수를 사용하여 DeviceIoControl 함수를 호출하십시오. |
| [**IOCTL_DISK_SET_CACHE_INFORMATION**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_cache_information) | 디스크 구성 데이터를 설정합니다.                             |
| [**IOCTL_DISK_SET_CLUSTER_INFO**](https://docs.microsoft.com/ko-kr/windows/desktop/FileIO/ioctl-disk-set-cluster-info) | 디스크의 클러스터 정보를 설정합니다.                         |
| [**IOCTL_DISK_SET_DISK_ATTRIBUTES**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_disk_attributes) | <span style="color:green">지정된 디스크 장치의 속성을 설정합니다.</span> |
| [**IOCTL_DISK_SET_DRIVE_LAYOUT**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_drive_layout) | 지정된 드라이브 레이아웃 및 파티션 정보 데이터에 따라 디스크를 분할합니다. |
| [**IOCTL_DISK_SET_DRIVE_LAYOUT_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_drive_layout_ex) | 지정된 드라이브 레이아웃 및 파티션 정보 데이터에 따라 디스크를 분할합니다. |
| [**IOCTL_DISK_SET_PARTITION_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_partition_info) | 지정된 디스크 파티션에 대한 파티션 정보를 설정합니다.        |
| [**IOCTL_DISK_SET_PARTITION_INFO_EX**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_set_partition_info_ex) | AT 및 EFI (Extensible Firmware Interface) 분할 영역에 대한 레이아웃 정보를 포함하여 지정된 디스크 분할 영역에 대한 분할 영역 정보를 설정합니다. |
| [**IOCTL_DISK_UPDATE_PROPERTIES**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_update_properties) | 캐시 된 파티션 테이블을 무효화하고 장치를 다시 열거합니다.   |
| [**IOCTL_DISK_VERIFY**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_disk_verify) | 고정 디스크에서 지정된 범위를 확인합니다.                    |
| [**IOCTL_STORAGE_DEVICE_POWER_CAP**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoctl/ni-winioctl-ioctl_storage_device_power_cap) | Windows 응용 프로그램은 이 제어 코드를 사용하여 저장 장치의 최대 작동 전력 소비 수준을 지정할 수 있습니다. OS는 장치를 주어진 최대 값을 초과하지 않는 전원 상태로 전환하는 것이 가장 좋습니다. 그러나 이것은 장치가 지원하는 것에 달려 있습니다. 실제 최대 값은 원하는 최대 값보다 작거나 클 수 있습니다.이 작업을 수행하려면 다음 매개 변수를 사용하여 DeviceIoControl 함수를 호출하십시오. |
| [**IOCTL_STORAGE_FIRMWARE_ACTIVATE**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoctl/ni-winioctl-ioctl_storage_firmware_activate) | Windows 응용 프로그램은 이 제어 코드를 사용하여 지정된 장치에서 펌웨어 이미지를 활성화 할 수 있습니다. 이 작업을 수행하려면 다음 매개 변수를 사용하여 DeviceIoControl 함수를 호출하십시오. |
| [**IOCTL_STORAGE_FIRMWARE_DOWNLOAD**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoctl/ni-winioctl-ioctl_storage_firmware_download) | Windows 응용 프로그램은 이 제어 코드를 사용하여 펌웨어 이미지를 대상 장치로 다운로드 할 수 있지만 활성화하지는 않습니다. 다운로드 할 이미지가 컨트롤러의 최대 데이터 전송 크기보다 큰 경우이 이미지를 다운로드 할 때까지이 IOCTL을 여러 번 호출해야합니다. 이 작업을 수행하려면 다음 매개 변수를 사용하여 DeviceIoControl 함수를 호출하십시오. |
| [**IOCTL_STORAGE_FIRMWARE_GET_INFO**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoctl/ni-winioctl-ioctl_storage_firmware_get_info) | Windows 응용 프로그램은이 제어 코드를 사용하여 펌웨어 이미지를 대상 장치로 다운로드 할 수 있지만 활성화하지는 않습니다. 다운로드 할 이미지가 컨트롤러의 최대 데이터 전송 크기보다 큰 경우이 이미지를 다운로드 할 때까지이 IOCTL을 여러 번 호출해야합니다. 이 작업을 수행하려면 다음 매개 변수를 사용하여 DeviceIoControl 함수를 호출하십시오. |
| [**IOCTL_STORAGE_PROTOCOL_COMMAND**](https://docs.microsoft.com/en-us/windows/desktop/api/winioctl/ni-winioctl-ioctl_storage_protocol_command) | Windows 응용 프로그램은 이 제어 코드를 사용하여 저장 장치 또는 어댑터의 속성을 반환 할 수 있습니다. 요청은 장치의 조회 데이터 또는 어댑터의 기능 및 제한 사항과 같이 검색 할 정보의 종류를 나타냅니다. |
| [**IOCTL_STORAGE_QUERY_PROPERTY**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoCtl/ni-winioctl-ioctl_storage_query_property) | Windows 응용 프로그램은 이 제어 코드를 사용하여 저장 장치 또는 어댑터의 속성을 반환 할 수 있습니다. 요청은 장치의 조회 데이터 또는 어댑터의 기능 및 제한 사항과 같이 검색 할 정보의 종류를 표시합니다. IOCTL_STORAGE_QUERY_PROPERTY는 포트 드라이버가 특정 속성을 지원하는지 여부 또는 속성 설명자의 어떤 필드가 후속 변경 속성 요청으로 수정할 수 있는지 여부를 결정하는 데에도 사용할 수 있습니다. |
| [**IOCTL_STORAGE_SET_TEMPERATURE_THRESHOLD**](https://docs.microsoft.com/en-us/windows/desktop/api/WinIoctl/ni-winioctl-ioctl_storage_set_temperature_threshold) | Windows 응용 프로그램은 이 제어 코드를 사용하여 장치의 온도 임계 값을 설정할 수 있습니다 (장치에서 지원할 경우). |

##### 1.1.4.5 [File Management Control Codes](https://msdn.microsoft.com/e27ded4b-d104-4244-b38e-5fed10d32e1e)

다음 제어 코드는 파일 관리에 사용됩니다.

| Control Code                                                 | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [**FSCTL_ALLOW_EXTENDED_DASD_IO**](https://msdn.microsoft.com/en-us/library/Aa364556(v=VS.85).aspx) | 파티션 읽기 또는 쓰기 호출에서 I/O 경계 검사를 수행하지 않도록 파일 시스템 드라이버에 신호를 보냅니다. |
| [**FSCTL_CREATE_OR_GET_OBJECT_ID**](https://msdn.microsoft.com/en-us/library/Aa364557(v=VS.85).aspx) | 지정된 파일 또는 디렉토리의 객체 식별자를 가져옵니다. 개체 식별자가 없으면 FSCTL_CREATE_OR_GET_OBJECT_ID를 사용하여 개체 식별자를 만듭니다. |
| [**FSCTL_CSV_CONTROL**](https://msdn.microsoft.com/en-us/library/Dn280516(v=VS.85).aspx) | CSV 컨트롤 조작의 결과를 얻어 온다.                          |
| [**FSCTL_DELETE_OBJECT_ID**](https://msdn.microsoft.com/en-us/library/Aa364559(v=VS.85).aspx) | 지정된 파일 또는 디렉토리에서 객체 식별자를 제거합니다.      |
| [**FSCTL_DUPLICATE_EXTENTS_TO_FILE**](https://msdn.microsoft.com/en-us/library/Mt590823(v=VS.85).aspx) | 응용 프로그램을 대신하여 파일 바이트 범위를 복사하도록 파일 시스템에 지시합니다. |
| [**FSCTL_FILE_LEVEL_TRIM**](https://msdn.microsoft.com/en-us/library/Hh447306(v=VS.85).aspx) | Indicates to the storage system which ranges in the file are not needed to be stored. |
| [**FSCTL_FILESYSTEM_GET_STATISTICS**](https://msdn.microsoft.com/en-us/library/Aa364565(v=VS.85).aspx) | Retrieves the information from various file system performance counters. |
| [**FSCTL_FILESYSTEM_GET_STATISTICS_EX**](https://msdn.microsoft.com/en-us/library/Dn706122(v=VS.85).aspx) | Retrieves the information from various file system performance counters. Support for this control code started with Windows 10. |
| [**FSCTL_FIND_FILES_BY_SID**](https://msdn.microsoft.com/en-us/library/Aa364566(v=VS.85).aspx) | Searches a directory for a file whose creator owner matches the specified SID. |
| [**FSCTL_GET_COMPRESSION**](https://msdn.microsoft.com/en-us/library/Aa364567(v=VS.85).aspx) | Retrieves the current compression state of a file or directory on a volume whose file system supports per-stream compression. |
| [**FSCTL_GET_NTFS_FILE_RECORD**](https://msdn.microsoft.com/en-us/library/Aa364568(v=VS.85).aspx) | Retrieves the first file record that is in use and is of a lesser than or equal ordinal value to the requested file reference number. |
| [**FSCTL_GET_OBJECT_ID**](https://msdn.microsoft.com/en-us/library/Aa364570(v=VS.85).aspx) | Retrieves the object identifier for the specified file or directory. |
| [**FSCTL_GET_REPAIR**](https://msdn.microsoft.com/en-us/library/Aa964898(v=VS.85).aspx) | Retrieves information about the NTFS file system's self-healing mechanism. |
| [**FSCTL_INITIATE_REPAIR**](https://msdn.microsoft.com/en-us/library/Aa964899(v=VS.85).aspx) | Triggers the NTFS file system to start a self-healing cycle on a single file. |
| [**FSCTL_MAKE_MEDIA_COMPATIBLE**](https://msdn.microsoft.com/en-us/library/Aa964900(v=VS.85).aspx) | Closes an open UDF session on write-once media to make the media ROM compatible. |
| [**FSCTL_OPBATCH_ACK_CLOSE_PENDING**](https://msdn.microsoft.com/en-us/library/Aa364578(v=VS.85).aspx) | Notifies a server that a client application is ready to close a file. |
| [**FSCTL_OPLOCK_BREAK_ACK_NO_2**](https://msdn.microsoft.com/en-us/library/Aa364580(v=VS.85).aspx) | Responds to notification that an opportunistic lock on a file is about to be broken. Use this operation to unlock all opportunistic locks on the file but keep the file open. |
| [**FSCTL_OPLOCK_BREAK_ACKNOWLEDGE**](https://msdn.microsoft.com/en-us/library/Aa364579(v=VS.85).aspx) | Responds to notification that an exclusive opportunistic lock on a file is about to be broken. Use this operation to indicate that the file should receive a level 2 opportunistic lock. |
| [**FSCTL_OPLOCK_BREAK_NOTIFY**](https://msdn.microsoft.com/en-us/library/Aa364581(v=VS.85).aspx) | Enables the calling application to wait for completion of an opportunistic lock break. |
| [**FSCTL_QUERY_ALLOCATED_RANGES**](https://msdn.microsoft.com/en-us/library/Aa364582(v=VS.85).aspx) | Scans a file or alternate stream looking for ranges that may contain nonzero data. |
| [**FSCTL_QUERY_ON_DISK_VOLUME_INFO**](https://msdn.microsoft.com/en-us/library/Aa964901(v=VS.85).aspx) | Requests UDF-specific volume information.                    |
| [**FSCTL_QUERY_SPARING_INFO**](https://msdn.microsoft.com/en-us/library/Aa964902(v=VS.85).aspx) | Retrieves the defect management properties of the volume. Used for UDF file systems. |
| [**FSCTL_RECALL_FILE**](https://msdn.microsoft.com/en-us/library/Aa364587(v=VS.85).aspx) | Recalls a file from storage media that Remote Storage manages, which is the hierarchical storage management software. |
| [**FSCTL_REQUEST_BATCH_OPLOCK**](https://msdn.microsoft.com/en-us/library/Aa364588(v=VS.85).aspx) | Requests a batch opportunistic lock on a file.               |
| [**FSCTL_REQUEST_FILTER_OPLOCK**](https://msdn.microsoft.com/en-us/library/Aa364589(v=VS.85).aspx) | Requests a filter opportunistic lock on a file.              |
| [**FSCTL_REQUEST_OPLOCK**](https://msdn.microsoft.com/en-us/library/Ee681828(v=VS.85).aspx) | Requests an opportunistic lock (oplock) on a file and acknowledges that an oplock break has occurred. |
| [**FSCTL_REQUEST_OPLOCK_LEVEL_1**](https://msdn.microsoft.com/en-us/library/Aa364590(v=VS.85).aspx) | Requests a level 1 opportunistic lock on a file.             |
| [**FSCTL_REQUEST_OPLOCK_LEVEL_2**](https://msdn.microsoft.com/en-us/library/Aa364591(v=VS.85).aspx) | Requests a level 2 opportunistic lock on a file.             |
| [**FSCTL_SET_COMPRESSION**](https://msdn.microsoft.com/en-us/library/Aa364592(v=VS.85).aspx) | Sets the compression state of a file or directory on a volume whose file system supports per-file and per-directory compression. |
| [**FSCTL_SET_DEFECT_MANAGEMENT**](https://msdn.microsoft.com/en-us/library/Aa964903(v=VS.85).aspx) | Sets the software defect management state for the specified file. Used for UDF file systems. |
| [**FSCTL_SET_OBJECT_ID**](https://msdn.microsoft.com/en-us/library/Aa364593(v=VS.85).aspx) | Sets the object identifier for the specified file or directory. |
| [**FSCTL_SET_OBJECT_ID_EXTENDED**](https://msdn.microsoft.com/en-us/library/Aa364594(v=VS.85).aspx) | Modifies user data associated with the object identifier for the specified file or directory. |
| [**FSCTL_SET_REPAIR**](https://msdn.microsoft.com/en-us/library/Aa964904(v=VS.85).aspx) | Sets the mode of an NTFS file system's self-healing capability. |
| [**FSCTL_SET_SPARSE**](https://msdn.microsoft.com/en-us/library/Aa364596(v=VS.85).aspx) | Marks the indicated file as sparse or not sparse. In a sparse file, large ranges of zeros may not require disk allocation. |
| [**FSCTL_SET_ZERO_DATA**](https://msdn.microsoft.com/en-us/library/Aa364597(v=VS.85).aspx) | Fills a specified range of a file with zeros (0).            |
| [**FSCTL_SET_ZERO_ON_DEALLOCATION**](https://msdn.microsoft.com/en-us/library/Aa964911(v=VS.85).aspx) | Indicates an NTFS file system file handle should have its clusters filled with zeros when it is deallocated. |
| [**FSCTL_WAIT_FOR_REPAIR**](https://msdn.microsoft.com/en-us/library/Aa964916(v=VS.85).aspx) | Returns when the specified repairs are completed.            |



##### 1.1.4.6 [Power Management Control Codes](https://msdn.microsoft.com/027fffdb-62a1-47d8-b69f-c2fcf7f9ac97)



##### 1.1.4.7[ Volume Management Control Codes](https://msdn.microsoft.com/87f39e1c-3ebf-4c6f-a842-699ec3c45e76)





#### 1.1.5 Examples

DeviceIoControl을 사용하는 예제는 [DeviceIoControl 호출](https://msdn.microsoft.com/b4dbda89-effb-43f7-b3cc-774db57862a9) 을 참조하십시오.



#### 1.1.6  Requirements

| **Minimum supported client** | Windows XP                     |
| ---------------------------- | ------------------------------ |
| **Minimum supported server** | Windows Server 2003            |
| **Target Platform**          | Windows                        |
| **Header**                   | ioapiset.h (include Windows.h) |
| **Library**                  | Kernel32.lib                   |
| **DLL**                      | Kernel32.dll                   |



#### 1.1.7 참고

[CreateEvent](https://msdn.microsoft.com/1f6d946e-c74c-4599-ac3d-b709216a0900)

[CreateFile](https://msdn.microsoft.com/en-us/library/Aa363858(v=VS.85).aspx)

[Device Input and Output Control (IOCTL)](https://msdn.microsoft.com/2dffd86a-162c-4e09-bfa1-73b87522741a)

[GetOverlappedResult](https://msdn.microsoft.com/7f999959-9b22-4491-ae2b-a2674d821110)

[GetQueuedCompletionStatus](https://msdn.microsoft.com/en-us/library/Aa364986(v=VS.85).aspx)

[OVERLAPPED](https://msdn.microsoft.com/5037f6b9-e316-483b-a8e2-b58d2587ebd9)



#### 1.1.8 Device Type 지정

각 디바이스 객체는 DEVICE_OBJECT 구조체의 DeviceType 멤버에 저장되는 디바이스 유형을가집니다. 장치 유형은 드라이버의 기본 하드웨어 유형을 나타냅니다.

장치 개체를 만드는 모든 커널 모드 드라이버는 IoCreateDevice를 호출 할 때 적절한 장치 유형 값을 지정해야합니다. IoCreateDevice 루틴은 제공된 장치 유형을 사용하여 DEVICE_OBJECT 구조체의 DeviceType 멤버를 초기화합니다.

시스템은 알파벳 순서로 나열된 다음 장치 유형 값을 정의합니다.

```c++
#define FILE_DEVICE_8042_PORT           0x00000027
#define FILE_DEVICE_ACPI                0x00000032
#define FILE_DEVICE_BATTERY             0x00000029
#define FILE_DEVICE_BEEP                0x00000001
#define FILE_DEVICE_BUS_EXTENDER        0x0000002a
#define FILE_DEVICE_CD_ROM              0x00000002
#define FILE_DEVICE_CD_ROM_FILE_SYSTEM  0x00000003
#define FILE_DEVICE_CHANGER             0x00000030
#define FILE_DEVICE_CONTROLLER          0x00000004
#define FILE_DEVICE_DATALINK            0x00000005
#define FILE_DEVICE_DFS                 0x00000006
#define FILE_DEVICE_DFS_FILE_SYSTEM     0x00000035
#define FILE_DEVICE_DFS_VOLUME          0x00000036
#define FILE_DEVICE_DISK                0x00000007
#define FILE_DEVICE_DISK_FILE_SYSTEM    0x00000008
#define FILE_DEVICE_DVD                 0x00000033
#define FILE_DEVICE_FILE_SYSTEM         0x00000009
#define FILE_DEVICE_FIPS                0x0000003a
#define FILE_DEVICE_FULLSCREEN_VIDEO    0x00000034
#define FILE_DEVICE_INPORT_PORT         0x0000000a
#define FILE_DEVICE_KEYBOARD            0x0000000b
#define FILE_DEVICE_KS                  0x0000002f
#define FILE_DEVICE_KSEC                0x00000039
#define FILE_DEVICE_MAILSLOT            0x0000000c
#define FILE_DEVICE_MASS_STORAGE        0x0000002d
#define FILE_DEVICE_MIDI_IN             0x0000000d
#define FILE_DEVICE_MIDI_OUT            0x0000000e
#define FILE_DEVICE_MODEM               0x0000002b
#define FILE_DEVICE_MOUSE               0x0000000f
#define FILE_DEVICE_MULTI_UNC_PROVIDER  0x00000010
#define FILE_DEVICE_NAMED_PIPE          0x00000011
#define FILE_DEVICE_NETWORK             0x00000012
#define FILE_DEVICE_NETWORK_BROWSER     0x00000013
#define FILE_DEVICE_NETWORK_FILE_SYSTEM 0x00000014
#define FILE_DEVICE_NETWORK_REDIRECTOR  0x00000028
#define FILE_DEVICE_NULL                0x00000015
#define FILE_DEVICE_PARALLEL_PORT       0x00000016
#define FILE_DEVICE_PHYSICAL_NETCARD    0x00000017
#define FILE_DEVICE_PRINTER             0x00000018
#define FILE_DEVICE_SCANNER             0x00000019
#define FILE_DEVICE_SCREEN              0x0000001c
#define FILE_DEVICE_SERENUM             0x00000037
#define FILE_DEVICE_SERIAL_MOUSE_PORT   0x0000001a
#define FILE_DEVICE_SERIAL_PORT         0x0000001b
#define FILE_DEVICE_SMARTCARD           0x00000031
#define FILE_DEVICE_SMB                 0x0000002e
#define FILE_DEVICE_SOUND               0x0000001d
#define FILE_DEVICE_STREAMS             0x0000001e
#define FILE_DEVICE_TAPE                0x0000001f
#define FILE_DEVICE_TAPE_FILE_SYSTEM    0x00000020
#define FILE_DEVICE_TERMSRV             0x00000038
#define FILE_DEVICE_TRANSPORT           0x00000021
#define FILE_DEVICE_UNKNOWN             0x00000022
#define FILE_DEVICE_VDM                 0x0000002c
#define FILE_DEVICE_VIDEO               0x00000023
#define FILE_DEVICE_VIRTUAL_DISK        0x00000024
#define FILE_DEVICE_WAVE_IN             0x00000025
#define FILE_DEVICE_WAVE_OUT            0x00000026
```

이러한 상수는 Ntddk.h 및 Wdm.h에 정의되어 있습니다. 이러한 파일을 검사하여 추가 장치 유형이 정의되어 있는지 확인하십시오.

FILE_DEVICE_DISK 사양은 디스크 파티션과 디스크로 나타나는 모든 개체를 다룹니다.

중간 드라이버는 일반적으로 기본 장치를 나타내는 장치 유형을 지정합니다. 예를 들어 시스템에서 제공하는 내결함성 디스크 드라이버 인 ftdisk는 FILE_DEVICE_DISK 유형의 장치 개체를 만듭니다. 미러 세트, 스트라이프 세트 및 미러 세트가 관리하는 볼륨 세트에 대한 새로운 디바이스 유형을 정의하지 않습니다.

0에서 32767 사이의 FILE_DEVICE_XXX 값은 Microsoft 용으로 예약되어 있습니다. 모든 드라이버 작성자는 시스템 정의 된 장치 유형에 속한 장치에 대해 이러한 시스템 정의 상수를 사용해야합니다.

하드웨어 유형이 정의 된 유형과 일치하지 않으면 FILE_DEVICE_UNKNOWN 또는 32768 - 65535 범위 내의 값을 지정하십시오.



### 1.2 SHChangeNotify

응용 프로그램이 수행 한 이벤트를 시스템에 알립니다. 응용 프로그램은 셸에 영향을 줄 수있는 작업을 수행하는 경우 이 함수를 사용해야합니다.

#### 1.2.1 Syntax

```c++
void SHChangeNotify(
  LONG    wEventId,
  UINT    uFlags,
  LPCVOID dwItem1,
  LPCVOID dwItem2
);
```

#### 1.2.2 Parameters

##### <span style="color:green">wEventId</span>

Type: **LONG**

발생한 이벤트를 설명합니다. 일반적으로 한 번에 하나의 이벤트만 지정됩니다. 두 개 이상의 이벤트가 지정된 경우 dwItem1 및 dwItem2 매개 변수에 포함 된 값은 지정된 모든 이벤트에 대해 각각 동일해야합니다. 이 매개 변수는 다음 값 중 하나 이상일 수 있습니다.

**SHCNE_ALLEVENTS**

모든 이벤트가 발생했습니다.

**SHCNE_ASSOCCHANGED**

파일 형식 연결이 변경되었습니다. SHCNF_IDLIST는 uFlags 매개 변수에 지정해야합니다. dwItem1과 dwItem2는 사용되지 않으며 NULL이어야합니다. 이 이벤트는 등록 된 프로토콜에 대해서도 전송되어야합니다.

**SHCNE_ATTRIBUTES**

항목 또는 폴더의 속성이 변경되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 변경된 항목 또는 폴더를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_CREATE**

비 폴더 항목이 생성되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 생성 된 항목을 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_DELETE**

비공개 항목이 삭제되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 삭제 된 항목을 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_DRIVEADD**

드라이브가 추가되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 추가 된 드라이브의 루트를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_DRIVEADDGUI**

Windows XP 이상 : 사용되지 않습니다.

**SHCNE_DRIVEREMOVED**

드라이브가 제거되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 제거 된 드라이브의 루트를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_EXTENDED_EVENT**

현재 사용되지 않습니다.

**SHCNE_FREESPACE**

드라이브의 여유 공간이 변경되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1에는 여유 공간이 변경된 드라이브의 루트가 들어 있습니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_MEDIAINSERTED**

저장 매체가 드라이브에 삽입되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1에는 새 미디어가 들어있는 드라이브의 루트가 들어 있습니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_MEDIAREMOVED**

드라이브에서 저장 매체가 제거되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 미디어가 제거 된 드라이브의 루트를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_MKDIR**

폴더가 생성되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 생성 된 폴더를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_NETSHARE**

로컬 컴퓨터의 폴더가 네트워크를 통해 공유됩니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 공유되는 폴더를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_NETUNSHARE**

로컬 컴퓨터의 폴더가 더 이상 네트워크를 통해 공유되지 않습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1에는 더 이상 공유되지 않는 폴더가 들어 있습니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_RENAMEFOLDER**

폴더 이름이 변경되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 이전 PIDL 또는 폴더 이름을 포함합니다. dwItem2는 새 PIDL 또는 폴더 이름을 포함합니다.

**SHCNE_RENAMEITEM**

비공개 항목의 이름이 변경되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 항목의 이전 PIDL 또는 이름을 포함합니다. dwItem2는 항목의 새 PIDL 또는 이름을 포함합니다.

**SHCNE_RMDIR**

폴더가 제거되었습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1에는 제거 된 폴더가 들어 있습니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_SERVERDISCONNECT**

컴퓨터와 서버의 연결이 끊어졌습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 컴퓨터가 연결되지 않은 서버를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다.

**SHCNE_UPDATEDIR**

기존 폴더의 내용이 변경되었지만 폴더가 여전히 존재하며 이름이 변경되지 않았습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 변경된 폴더를 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다. 폴더가 작성, 삭제 또는 이름이 바뀐 경우 SHCNE_MKDIR, SHCNE_RMDIR 또는 SHCNE_RENAMEFOLDER를 각각 사용하십시오.

**SHCNE_UPDATEIMAGE**

시스템 이미지 목록의 이미지가 변경되었습니다. uFlags에 SHCNF_DWORD를 지정해야합니다.

dwItem2에는 변경된 시스템 이미지 목록의 색인이 들어 있습니다. dwItem1은 사용되지 않으므로 NULL이어야합니다.

**SHCNE_UPDATEITEM**

기존 항목 (폴더 또는 비공개)이 변경되었지만 항목이 여전히 존재하며 이름이 변경되지 않았습니다. SHCNF_IDLIST 또는 SHCNF_PATH는 uFlags에 지정해야합니다. dwItem1은 변경된 항목을 포함합니다. dwItem2는 사용되지 않으므로 NULL이어야합니다. 비공개 항목이 생성, 삭제 또는 이름이 변경된 경우에는 대신 SHCNE_CREATE, SHCNE_DELETE 또는 SHCNE_RENAMEITEM을 각각 사용하십시오.

**SHCNE_DISKEVENTS**

모든 디스크 이벤트 식별자의 조합을 지정합니다.

**SHCNE_GLOBALEVENTS**

모든 전역 이벤트 식별자의 조합을 지정합니다.

**SHCNE_INTERRUPT**

지정된 이벤트가 시스템 인터럽트의 결과로 발생했습니다. 이 값은 다른 이벤트 값을 수정하므로 단독으로 사용할 수 없습니다.

##### <span style="color:green"> uFlags </span>

Type: **UINT**

SHCNF_TYPE와 비트 단위로 결합 할 때 dwItem1 및 dwItem2 매개 변수의 의미를 나타내는 플래그. uFlags 매개 변수는 다음 값 중 하나 여야합니다.

**SHCNF_DWORD**

dwItem1 및 dwItem2 매개 변수는 DWORD 값입니다.

**SHCNF_IDLIST**

dwItem1과 dwItem2는 변경의 영향을받는 항목을 나타내는 ITEMIDLIST 구조체의 주소입니다. 각 ITEMIDLIST는 데스크탑 폴더에 상대적이어야합니다.

**SHCNF_PATH**

dwItem1과 dwItem2는 변경의 영향을받는 항목의 전체 경로 이름을 포함하는 최대 길이가 MAX_PATH 인 Null 종료 문자열의 주소입니다.

**SHCNF_PRINTER**

dwItem1과 dwItem2는 변경의 영향을받는 프린터의 이름을 나타내는 Null 종료 문자열의 주소입니다.

**SHCNF_FLUSH**

영향을받는 모든 구성 요소에 알림이 전달 될 때까지 함수가 반환되어서는 안됩니다. 이 플래그는 다른 데이터 유형 플래그를 수정하기 때문에 자체적으로 사용할 수 없습니다.

**SHCNF_FLUSHNOWAIT**

이 함수는 모든 영향을받는 구성 요소에 알림을 제공해야하지만 알림 프로세스가 시작되면 즉시 반환해야합니다. 이 플래그는 다른 데이터 유형 플래그를 수정하기 때문에 자체적으로 사용할 수 없습니다. 이 플래그에는 SHCNF_FLUSH가 포함됩니다.

**SHCNF_NOTIFYRECURSIVE**

모든 children 으로 등록 된 client를 알립니다.



##### <span style="color:green">dwItem1</span>

Type: **LPCVOID**

선택 과목. 첫 번째 이벤트 종속 값입니다.



##### <span style="color:green">dwItem2</span>

Type: **LPCVOID**

선택 과목. 두 번째 이벤트 종속 값.



#### 1.2.3 Return Value

이 함수는 값을 반환하지 않습니다.



#### 1.2.4 Remarks

모든 유형의 새 처리기를 등록하는 응용 프로그램은 SHCNE_ASSOCCHANGED 플래그를 사용하여 SHChangeNotify를 호출하여 셸에 아이콘 및 축소판 캐시를 무효화하도록 지시해야합니다. 등록 된 새 아이콘 및 축소판 핸들러도로드됩니다. 그러나 아이콘 오버레이 핸들러는 다시로드되지 않습니다.

dwItem1과 dwItem2가 가리키는 문자열은 ANSI 또는 유니 코드 일 수 있습니다.



#### 1.2.5 Requirements

| **Minimum supported client** | Windows XP [desktop apps only]          |
| ---------------------------- | --------------------------------------- |
| **Minimum supported server** | Windows 2000 Server [desktop apps only] |
| **Target Platform**          | Windows                                 |
| **Header**                   | shlobj_core.h (include Shlobj.h)        |
| **Library**                  | Shell32.lib                             |
| **DLL**                      | Shell32.dll (version 4.0 or later)      |



### 1.3 CreateFile

파일 또는 I/O 장치를 만들거나 엽니다. 가장 일반적으로 사용되는 I/O 장치는 파일, 파일 스트림, 디렉터리, 실제 디스크, 볼륨, 콘솔 버퍼, 테이프 드라이브, 통신 리소스, 메일 슬롯 및 파이프입니다. 이 함수는 파일이나 장치, 지정된 플래그 및 속성에 따라 다양한 유형의 I/O에 대해 파일이나 장치에 액세스하는 데 사용할 수있는 핸들을 반환합니다.

이 작업을 트랜잭션 된 작업으로 수행하면 트랜잭션 I/O에 사용할 수있는 핸들이 만들어 지므로 CreateFileTransacted 함수를 사용하십시오.

CreateFile 은 별도로 작성된 [CreateFile 함수 정리.md](./CreateFile 함수 정리.md) 를 참조 하십시오.



### 1.4 DefineDosDevice

MS-DOS 장치 이름을 정의, 재정의 또는 삭제합니다.

#### 1.4.1 Syntax

```c++
BOOL DefineDosDeviceW(
  DWORD   dwFlags,
  LPCWSTR lpDeviceName,
  LPCWSTR lpTargetPath
);
```

#### 1.4.2 Parameters

<span style="color:green">**dwFlags**</span>

DefineDosDevice 함수의 제어 가능한 측면, 이 매개 변수는 다음 값 중 하나 이상일 수 있습니다.

| Value                                                        | Meaning                                                      |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| **DDD_EXACT_MATCH_ON_REMOVE**<br/>0x00000004                 | 이 값이 DDD_REMOVE_DEFINITION과 함께 지정되면, 함수는 정확한 일치를 사용하여 제거 할 맵핑을 판별합니다. 이 값을 사용하여 정의하지 않은 것을 삭제하지 않도록하십시오. |
| **DDD_NO_BROADCAST_SYSTEM**<br/>0x00000008                   | WM_SETTINGCHANGE 메시지를 브로드 캐스트하지 마십시오. 기본적으로이 메시지는 변경 내용을 Shell 및 응용 프로그램에 알리기 위해 브로드 캐스트됩니다. |
| <span style="color:green">**DDD_RAW_TARGET_PATH**</span><br/>0x00000001 | lpTargetPath 문자열을있는 그대로 사용합니다. 그렇지 않으면 MS-DOS 경로에서 경로로 변환됩니다. |
| **DDD_REMOVE_DEFINITION**<br/>0x00000002                     | 지정된 장치에 대해 지정된 정의를 제거합니다. 제거 할 정의를 결정하기 위해 함수는이 장치와 관련된 각 매핑의 접두사에 대해 lpTargetPath와 일치하는 항목을 찾아 장치의 매핑 목록을 찾습니다. 일치하는 첫 번째 매핑이 제거 된 다음 함수가 반환됩니다.<br/>lpTargetPath가 NULL 또는 NULL 문자열에 대한 포인터 인 경우이 함수는 장치와 관련된 첫 번째 매핑을 제거하고 밀어 넣은 가장 최근의 매핑을 팝합니다. 아무 것도 남기지 않으면 장치 이름이 제거됩니다.<br/>이 값을 지정하지 않으면 lpTargetPath 매개 변수가 가리키는 문자열이이 장치의 새 매핑이됩니다. |

<span style="color:green">**lpDeviceName**</span>

함수가 정의, 재정의 또는 삭제하는 장치를 지정하는 MS-DOS 장치 이름 문자열에 대한 포인터입니다. 드라이브 이름을 정의, 재정의 또는 삭제하지 않는 한 장치 이름 문자열에는 마지막 문자로 콜론 (:)이 없어야합니다. 예를 들어, C 드라이브는"C:"문자열입니다. 어떠한 경우에도 후행 백 슬래시 ("")는 허용되지 않습니다.

<span style="color:green">**lpTargetPath**</span>

이 장치를 구현할 경로 문자열에 대한 포인터입니다. 문자열은 DDD_RAW_TARGET_PATH 플래그가 지정되지 않은 경우 MS-DOS 경로 문자열입니다.이 경우이 문자열은 경로 문자열입니다.

#### 1.4.3 Return Value

함수가 성공하면 반환 값은 **0** 이 아닙니다.

함수가 실패하면 반환 값은 **0** 입니다. 확장 된 오류 정보를 얻으려면 GetLastError를 호출하십시오.



#### 1.4.4 Remarks

MS-DOS 장치 이름은 오브젝트 이름 공간에 junction으로 저장됩니다. MS-DOS 경로를 해당 경로로 변환하는 코드는 이러한 연결을 사용하여 MS-DOS 장치와 드라이브 문자를 매핑합니다. DefineDosDevice 함수를 사용하면 응용 프로그램이 MS-DOS 장치 네임 스페이스를 구현하는 데 사용되는 junction을 수정할 수 있습니다.

특정 MS-DOS 장치 이름에 대한 현재 매핑을 검색하거나 시스템에 알려진 모든 MS-DOS 장치 목록을 얻으려면 [QueryDosDevice](https://msdn.microsoft.com/ff25bc2b-dde6-40c3-a270-372daab2e5c4) 함수를 사용하십시오.

네트워크 공유가 아닌 부팅을 통해 영구적 인 드라이브 문자 할당을 정의하려면 [SetVolumeMountPoint](https://msdn.microsoft.com/1535fe64-221a-4756-a9ba-81bbe7596598) 함수를 사용하십시오. 마운트 할 볼륨에 이미 할당 된 드라이브 문자가 있으면 [DeleteVolumeMountPoint](https://msdn.microsoft.com/b1a0a273-fa7f-4794-8b50-c74f00b0228d) 기능을 사용하여 할당을 제거하십시오.

시스템 부팅시 정의 된 드라이브 문자 및 장치 이름은 사용자가 관리자가 아닌 경우 재정의 및 삭제로부터 보호됩니다.

Windows XP에서 이 함수는 자체 Local MS-DOS 장치 네임 스페이스의 'LocalSystem'컨텍스트에서 실행되지 않는 호출자의 장치 이름을 만듭니다. 호출자가 'LocalSystem'컨텍스트에서 실행중인 경우 이 함수는 전역 MS-DOS 장치 네임 스페이스에 장치 이름을 만듭니다. 자세한 내용은 MS DOS 장치 이름 및 파일 이름, 경로 및 네임 스페이스 정의를 참조하십시오.

Windows 8 및 Windows Server 2012에서 이 기능은 다음 기술에서 지원됩니다.

| Technology                                | Supported |
| :---------------------------------------- | :-------- |
| Server Message Block (SMB) 3.0 protocol   | No        |
| SMB 3.0 Transparent Failover (TFO)        | No        |
| SMB 3.0 with Scale-out File Shares (SO)   | No        |
| Cluster Shared Volume File System (CsvFS) | No        |
| Resilient File System (ReFS)              | No        |

SMB는 볼륨 관리 기능을 지원하지 않습니다. CsvFs 의 경우 새 이름은 클러스터의 다른 노드에 복제되지 않습니다.

##### Examples

예를 보려면 [Editing Drive Letter Assignments](https://msdn.microsoft.com/17a6df9d-07df-42f7-89c9-72a1d02141f6)을 참조하십시오.

#### 1.4.5 Requirements

| **Minimum supported client** | Windows XP [desktop apps only]          |
| ---------------------------- | --------------------------------------- |
| **Minimum supported server** | Windows Server 2003 [desktop apps only] |
| **Target Platform**          | Windows                                 |
| **Header**                   | fileapi.h (include Windows.h)           |
| **Library**                  | Kernel32.lib                            |
| **DLL**                      | Kernel32.dll                            |



### 1.5 FormatMessage

메시지 문자열을 형식화합니다. 이 함수는 메시지 정의를 입력으로 요구합니다. 메시지 정의는 함수에 전달 된 버퍼에서 가져올 수 있습니다. 이미 로드된 모듈의 메시지 테이블 리소스에서 가져올 수 있습니다. 또는 호출자가 함수에 시스템의 메시지 테이블 리소스에서 메시지 정의를 검색하도록 요청할 수 있습니다. 이 함수는 메시지 식별자 및 언어 식별자를 기반으로 메시지 테이블 자원에서 메시지 정의를 찾습니다. 이 함수는 형식화 된 메시지 텍스트를 출력 버퍼로 복사하여 요청 된 경우 모든 삽입 된 삽입 시퀀스를 처리합니다.

#### 1.5.1 Syntax

```c++
DWORD FormatMessage(
  DWORD   dwFlags,
  LPCVOID lpSource,
  DWORD   dwMessageId,
  DWORD   dwLanguageId,
  LPTSTR  lpBuffer,
  DWORD   nSize,
  va_list *Arguments
);
```



#### 1.5.2 참고

FileDisk 에서는 이 함수를 LastError 를 표시 하는데 사용 하였다. printf 군 함수 처럼 사용 함.



## 2. 사용법

### 2.1 Syntax

```
syntax:
filedisk /mount  <devicenumber> <filename> [size[k|M|G] | /ro | /cd] <drive:>
filedisk /umount <drive:>
filedisk /status <drive:>

filename formats:
  c:\path\filedisk.img
  \Device\Harddisk0\Partition1\path\filedisk.img
  \\server\share\path\filedisk.img

example:
filedisk /mount  0 c:\tmp\filedisk.img 8M f:
filedisk /mount  1 c:\tmp\cdimage.iso /cd i:
filedisk /umount f:
filedisk /umount i:
```

### 2.2 활용 예

```
C:\> filedisk /mount  0 c:\tmp\filedisk.img 4G g:

C:\>format /fs:ntfs /v:FileDisk /q g:
The type of file system is RAW.
The new file system is NTFS.

WARNING, ALL DATA ON NON-REMOVABLE DISK
DRIVE G: WILL BE LOST!
Processed with Format (Y/N)? Y
QuickFormating 4096M
Creating file system structures.
Format complete.
   4194300 KB total disk space.
   4170940 KB are available.
   
C:\>dir g:
 Volume in drive G is FileDIsk
 Volume Serial Number is C82C-8652
 
 Directory of G:\
 
File Not FOund

C:\>filedisk /status g:
g: \??\c:\tmp\filedisk.img 4294967296 bytes

C:\>filedisk /umount g:

C:\>net stop filedisk

The FileDisk service was stopped successfully.

C:\>
```

##### # 언마운트시 /unmount 가 아니라 /umount 임.



---

## 2. Driver 분석(sys)

이 장에서는 FileDisk 의 Device Driver(sys) 의 주요 함수를 분석 한다.

### 2.1 생성된 함수 분석

| 함수명                  | 설명                                                         |
| ----------------------- | ------------------------------------------------------------ |
| DriverEntry             | 드라이버가 load 되고 첫번째 호출되는 루틴 으로, 드라이버의 초기화 내용을 설정해 준다. |
| FileDiskAdjustPrivilege |                                                              |
| FileDiskCloseFile       |                                                              |
| FileDiskCreateClose     |                                                              |
| FileDiskCreateDevice    |                                                              |
| FileDiskDeleteDevice    |                                                              |
| FileDiskDeviceControl   |                                                              |
| FileDiskOpenFile        |                                                              |
| FileDiskReadWrite       |                                                              |
| FileDiskThread          |                                                              |
| FileDiskUnload          |                                                              |
| ZwAdjustPrivilegesToken |                                                              |
| ZwOpenProcessToken      |                                                              |



### 2.2 주요 사용 함수 분석

| 함수명                     | 설명 |
| -------------------------- | ---- |
| ZwOpenProcessToken         |      |
| ZwAdjustPrivilegesToken    |      |
| IoCreateDeviceSecure       |      |
| PsCreateSystemThread       |      |
| ObReferenceObjectByHandle  |      |
| InitializeObjectAttributes |      |
| ZwCreateDirectoryObject    |      |
| ZwMakeTemporaryObject      |      |
| FileDiskCreateDevice       |      |



#### 2.2.1 IoCreateDeviceSecure routine

자세한 내용은 WdmlibIoCreateDeviceSecure 함수를 참조하십시오.



#### 2.2.2 WdmlibIoCreateDeviceSecure function

WdmlibIoCreateDeviceSecure 함수 (또는 IoCreateDeviceSecure)는 명명 된 장치 개체를 만들고 지정된 보안 설정을 적용합니다.

##### 2.2.2.0 이 프로그램 에서 사용 예

```c++
   status = IoCreateDeviceSecure(  	DriverObject,
        							sizeof(DEVICE_EXTENSION),
                                    &device_name,
                                    DeviceType,
                                    0,
                                    FALSE,
                                    &sddl,
                                    NULL,
                                    &device_object
                                    );
```



##### 2.2.2.1 Syntax

```c++
NTSTATUS WdmlibIoCreateDeviceSecure(
  PDRIVER_OBJECT   DriverObject,
  ULONG            DeviceExtensionSize,
  PUNICODE_STRING  DeviceName,
  DEVICE_TYPE      DeviceType,
  ULONG            DeviceCharacteristics,
  BOOLEAN          Exclusive,
  PCUNICODE_STRING DefaultSDDLString,
  LPCGUID          DeviceClassGuid,
  PDEVICE_OBJECT   *DeviceObject
);
```

##### 2.2.2.2 Parameters

<span style="color:green">**DriverObject**</span>

호출 원의 드라이버 객체의 포인터. 각 드라이버는 DriverEntry 루틴에 대한 매개 변수에서 해당 드라이버 개체에 대한 포인터를받습니다. WDM 함수와 필터 드라이버는 AddDevice 루틴에서 드라이버 개체 포인터를받습니다.



<span style="color:green">**DeviceExtensionSize**</span>

장치 개체의 장치 확장에 할당 할 드라이버 결정 바이트 수를 지정합니다. 장치 확장의 내부 구조는 드라이버가 정의합니다.



<span style="color:green">**DeviceName**</span>

선택적으로 장치 개체의 이름을 Null로 끝나는 유니 코드 문자열이 포함 된 버퍼를 가리 킵니다. 문자열은 전체 경로 이름이어야합니다. 이름이 제공되지 않으면 FILE_AUTOGENERATED_DEVICE_NAME 플래그가 DeviceCharacteristics 매개 변수에 있어야합니다. 이름없는 장치 개체를 만들려면 IoCreateDevice 루틴을 사용하십시오.



<span style="color:green">**DeviceType**</span>

장치 유형 (FILE_DEVICE_DISK, FILE_DEVICE_KEYBOARD 등)을 나타내는 시스템 정의 FILE_DEVICE_XXX 상수 중 하나 또는 새 유형의 장치에 대한 공급 업체 정의 값 중 하나를 지정합니다. 자세한 내용은 장치 유형 지정을 참조하십시오. 버스 드라이버는 장치 유형에 대한 정보를 가지고 있지 않기 때문에 PDO에 대한 장치 유형 값을 INF AddReg 지시문에 지정할 수 있습니다.



<span style="color:green">**DeviceCharacteristics**</span>

드라이버의 장치에 대한 추가 정보를 제공하는 하나 이상의 시스템 정의 상수 OREDed 를 지정합니다. 가능한 장치 특성 목록은 DEVICE_OBJECT를 참조하십시오. 장치 특성을 지정하는 방법에 대한 자세한 내용은 장치 특성 지정을 참조하십시오. 대부분의 드라이버는이 매개 변수에 대해 FILE_DEVICE_SECURE_OPEN을 지정합니다.



<span style="color:green">**Exclusive**</span>

장치 개체가 단독 장치를 나타내는 지 여부를 지정합니다. 대부분의 드라이버는이 값을 FALSE로 설정합니다. 자세한 내용은 장치 개체에 대한 독점 액세스 지정을 참조하십시오.



<span style="color:green">**DefaultSDDLString**</span>

// Security Descriptor Definition Language (SDDL)

장치 개체의 기본 보안 설정에 대한 문자열 표현을 지정합니다. 시스템 관리자가 DeviceClassGuid 매개 변수로 식별되는 레지스트리 섹션에 재정의를 설정하지 않는 한 장치 개체에 적용되는 보안은 이 문자열에서 파생됩니다.

보안 설정은 SDDL (Security Descriptor Definition Language)의 하위 집합에서 지정됩니다. 사전 정의 된 상수 집합 (SDDL_DEVOBJ_XXX)도 제공됩니다. 자세한 내용은 [장치 개체 보안](https://msdn.microsoft.com/library/windows/hardware/ff563688)을 참조하십시오.

**RtlInitUnicodeString** 을 이용하여 설정 권한 문자를 설정 하였음



<span style="color:red">**SDDL for Device Objects(장치 개체 용 SDDL)**</span>

이 프로그램 에서 사용한 방법

```c++
UNICODE_STRING      sddl;

RtlInitUnicodeString(&sddl, _T("D:P(A;;GA;;;SY)(A;;GA;;;BA)(A;;GA;;;BU)"));

// 이후에 IoCreateDeviceSecure 의 매개변수로 사용함.

참고로 Dokany 에서는 다음과 같이 사용하고 있다.
static UNICODE_STRING sddl = RTL_CONSTANT_STRING(
    L"D:P(A;;GA;;;SY)(A;;GRGWGX;;;BA)(A;;GRGWGX;;;WD)(A;;GRGX;;;RC)");
```

<https://docs.microsoft.com/ko-kr/windows-hardware/drivers/kernel/sddl-for-device-objects>

보안 설명자 정의 언어 (SDDL)는 보안 설명자를 나타내는 데 사용됩니다. 장치 개체의 보안은 INF 파일에 저장되거나 IoCreateDeviceSecure로 전달되는 SDDL 문자열로 지정 될 수 있습니다. 보안 설명자 정의 언어는 Microsoft Windows SDK 설명서에 완전히 설명되어 있습니다.

INF 파일은 SDDL의 전체 범위를 지원하지만 IoCreateDeviceSecure 루틴은 언어의 하위 집합 만 지원합니다. 이 부분 집합은 여기에 정의됩니다.

장치 개체에 대한 SDDL 문자열은 'D : P'다음에 '(A ;;;;;; SID)'형식의 하나 이상의 표현식이옵니다. SID 값은 Access 값이 적용되는 대상 (예 : 사용자 또는 그룹)을 결정하는 보안 식별자를 지정합니다. Access 값은 SID에 허용 된 액세스 권한을 지정합니다. 액세스 및 SID 값은 다음과 같습니다.

**Note** 장치 개체에 SDDL을 사용하는 경우 드라이버는 Wdmsec.lib에 연결해야합니다.

*Access*

허용 된 액세스를 결정하는 [ACCESS_MASK](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/kernel/access-mask) 값을 지정합니다. 이 값은 '0xhex'형식의 16 진수 값이나 액세스 권한을 나타내는 두 자의 기호 코드 시퀀스로 작성할 수 있습니다.



다음 코드는 일반 액세스 권한을 지정하는 데 사용할 수 있습니다.

| ode  | Generic access right |
| :--- | :------------------- |
| GA   | GENERIC_ALL          |
| GR   | GENERIC_READ         |
| GW   | GENERIC_WRITE        |
| GX   | GENERIC_EXECUTE      |



다음 코드는 특정 액세스 권한을 지정하는 데 사용될 수 있습니다.

| Code | Specific access right |
| :--- | :-------------------- |
| RC   | READ_CONTROL          |
| SD   | DELETE                |
| WD   | WRITE_DAC             |
| WO   | WRITE_OWNER           |



GENERIC_ALL은 ACL을 변경하는 기능을 포함하여 위의 두 테이블에 나열된 모든 권한을 부여합니다.



*SID*

지정된 액세스 권한이 부여 된 SID를 지정합니다. SID는 계정, 별칭, 그룹, 사용자 또는 컴퓨터를 나타냅니다.

다음 SID는 시스템의 계정을 나타냅니다.

| SID  | Description                                                  |
| :--- | :----------------------------------------------------------- |
| SY   | System<br/><br/>사용자 모드 구성 요소를 포함하여 운영 체제 자체를 나타냅니다. |
| LS   | Local Service<br/><br/>로컬 서비스 (Authenticated 및 World에도 속한)에 대한 미리 정의 된 계정입니다. 이 SID는 Windows XP부터 사용할 수 있습니다. |
| NS   | Network Service<br/><br/>네트워크 서비스에 대한 미리 정의 된 계정 (Authenticated 및 World에도 속함). 이 SID는 Windows XP부터 사용할 수 있습니다. |



다음 SID는 시스템의 그룹을 나타냅니다.

| SID  | Description                                                  |
| :--- | :----------------------------------------------------------- |
| BA   | Administrators<br/><br/>컴퓨터의 기본 제공 Administrators 그룹입니다. |
| BU   | Built-in User Group<br/><br/>모든 로컬 사용자 계정과 도메인의 사용자를 대상으로하는 그룹 |
| BG   | Built-in Guest Group<br/><br/>로컬 또는 도메인 게스트 계정을 사용하여 로그인하는 사용자를 다루는 그룹. |



다음 SID는 사용자가 인증 된 범위를 나타냅니다.

| SID  | Description                                                  |
| :--- | :----------------------------------------------------------- |
| AU   | Authenticated Users<br/><br/>로컬 시스템이나 도메인이 인식하는 모든 사용자. Builtin Guest 계정을 사용하여 로그인 한 사용자는 인증되지 않습니다. 그러나 시스템 또는 도메인의 개별 계정을 가진 Guests 그룹의 구성원은 인증됩니다. |
| AN   | Anonymous Logged-on User<br/><br/>익명 네트워크 세션과 같이 신원없이 로그온 한 모든 사용자. Builtin Guest 계정을 사용하여 로그인하는 사용자는 인증도 익명도 아닙니다. 이 SID는 Windows XP부터 사용할 수 있습니다. |



다음 SID는 사용자가 컴퓨터에 로그인 한 방법을 설명합니다.

| SID  | Description                                                  |
| :--- | :----------------------------------------------------------- |
| IU   | Interactive Users<br/><br/>로컬 로그온 및 원격 데스크톱 로그온과 같이 처음에 컴퓨터에 '대화 형으로'로그온 한 사용자. |
| NU   | Network Logon User<br/><br/>대화 형 데스크톱 액세스 (예 : 파일 공유 또는 RPC 호출)없이 컴퓨터에 원격으로 액세스하는 사용자. |
| WD   | World<br/><br/>Windows XP 이전에는 인증 된 사용자, 익명 사용자 또는 Builtin Guest 계정 등 모든 세션에이 SID가 적용되었습니다.<br/>Windows XP부터이 SID는 익명 로그온 세션을 포함하지 않습니다. 인증 된 사용자와 Builtin Guest 계정 만 포함합니다.<br/>신뢰할 수 없거나 '제한된'코드는 World SID의 적용을받지 않습니다. 자세한 내용은 다음 표의 제한된 코드 (RC) SID에 대한 설명을 참조하십시오. |



다음 SID는 특별한 언급이 필요합니다.

| SID  | Description                                                  |
| :--- | :----------------------------------------------------------- |
| RC   | Restricted Code<br/><br/>이 SID는 신뢰할 수없는 코드로 액세스를 제어하는 데 사용됩니다. RC가있는 토큰에 대한 ACL 유효성 검사는 토큰의 일반 목록 인 SID (예 : WD 포함) 및 두 번째 목록 (일반적으로 RC 및 원래 토큰 SID의 하위 집합 포함)에 대한 두 가지 검사로 구성됩니다. 토큰이 두 테스트를 모두 통과 한 경우에만 액세스가 허용됩니다. 따라서 RC는 실제로 다른 SID와 함께 작동합니다.<br/><br/>RC를 지정하는 ACL은 WD를 지정해야합니다. RC에서 ACL에서 WD와 쌍을 이루면 신뢰할 수없는 코드를 포함한 Everyone의 상위 집합이 설명됩니다.<br/><br/>신뢰할 수없는 코드는 Explorer에서 Run As 옵션을 사용하여 시작될 수 있습니다. 기본적으로 World는 신뢰할 수없는 코드를 포함하지 않습니다. |
| UD   | User-Mode Drivers<br/><br/>이 SID는 사용자 모드 드라이버에 대한 액세스 권한을 부여합니다. 현재이 SID는 사용자 모드 드라이버 프레임 워크 (UMDF) 용으로 작성된 드라이버 만 포함합니다. 이 SID는 Windows 8부터 사용할 수 있습니다.<br/><br/>'UD'약어를 인식하지 못하는 이전 버전의 Windows에서는이 SID (S-1-5-84-0-0-0-0)의 정규화 된 형식을 지정하여 UMDF에 대한 액세스 권한을 부여해야합니다 운전자. 자세한 내용은 사용자 모드 드라이버 프레임 워크 설명서에서 장치 액세스 제어를 참조하십시오. |



**장치 개체의 SDDL 예제**

이 섹션에서는 Wdmsec.h에있는 미리 정의 된 SDDL 문자열에 대해 설명합니다. 템플릿으로 사용하여 장치 개체에 대한 새 SDDL 문자열을 정의 할 수도 있습니다.

SDDL_DEVOBJ_KERNEL_ONLY

**"D:P"**

SDDL_DEVOBJ_KERNEL_ONLY는 '비어있는'ACL입니다. 사용자 모드 코드 (시스템으로 실행중인 프로세스 포함)는 장치를 열 수 없습니다.

PnP 버스 드라이버는 PDO를 생성 할 때이 설명자를 사용할 수 있습니다. 그런 다음 INF 파일은 장치에 대한 더 안전한 보안 설정을 지정할 수 있습니다. 이 설명자를 지정함으로써, 버스 드라이버는 INF가 처리되기 전에 장치를 여는 시도가 성공하지 못하도록합니다.

마찬가지로 비 -WDM 드라이버는 적절한 사용자 모드 프로그램 (예 : 설치 관리자)이 레지스트리에 최종 보안 설명자를 설정할 때까지이 설명자를 사용하여 장치 개체를 액세스 할 수 없게 만들 수 있습니다.

이 모든 경우 기본값은 엄격한 보안이며 필요에 따라 완화됩니다.



<span style="color:green">**DeviceClassGuid**</span>

DefaultSDDLString, DeviceType, DeviceCharacteristics 및 Exclusive 매개 변수에 대한 무시를 포함하는 레지스트리 섹션을 식별하는 GUID의 포인터입니다.

**Note**

```
항상 사용자 정의 클래스 GUID를 지정해야합니다. 기존 클래스 GUID를 지정하면 안됩니다. 기존 클래스 GUID를 지정하면 기존 클래스 GUID를 지정하려고 시도하는 다른 드라이버가 설치에 실패하거나 잘못된 보안 설정으로 설치 될 수 있습니다.
```



<span style="color:green">***DeviceObject**</span>

새로 생성 된 DEVICE_OBJECT 구조체에 대한 포인터를받는 변수의 포인터. DEVICE_OBJECT 구조는 비 페이징 된 풀에서 할당됩니다.



##### 2.2.2.3 Return Value

WdmlibIoCreateDeviceSecure는 성공시 STATUS_SUCCESS를 반환하고 실패하면 적절한 NTSTATUS 오류 코드를 반환합니다. 이 기능으로 반환 할 수있는 오류 코드의 일부 목록은 다음과 같습니다.



##### 2.2.2.4 Remarks

WdmlibIoCreateDeviceSecure는 명명 된 장치 개체를 만들고 지정된 보안 설정을 적용한 다음 개체에 대한 포인터를 반환합니다. 호출자는 IoDeleteDevice를 호출하여 객체가 더 이상 필요하지 않을 때 객체를 삭제해야합니다.

이 루틴은 운영 체제의 일부가 아닙니다. 드라이버는 Microsoft Windows 2000 이상 버전의 Windows에서 Wdmsec.lib에 연결하여 루틴을 사용할 수 있습니다. (Wdmsec.lib 라이브러리는 Microsoft Windows XP 서비스 팩 1 (SP1) 및 Windows Server 2003 Edition의 Driver Development Kit (DDK)와 함께 제공되며 Windows Driver Kit [WDK]와 함께 제공됩니다.)

INF 파일에 의해 설정된 보안 설명자를 가질 수없는 명명 된 장치 개체를 만드는 드라이버는 WdmlibIoCreateDeviceSecure를 사용해야합니다. 자세한 내용은 장치 개체 만들기를 참조하십시오. 호출 한 사람은 반환 된 장치 개체의 특정 멤버를 설정해야합니다. 자세한 내용은 장치 개체 초기화 및 장치에 대한 장치 유형 관련 설명서를 참조하십시오.

올바른 매개 변수에 DeviceType 및 DeviceCharacteristics 값을 지정하는 데주의하십시오. 두 매개 변수 모두 시스템 정의 FILE_XXX 상수를 사용하고 일부 드라이버 작성자는 실수로 잘못된 매개 변수의 값을 지정합니다.

호출자는 Flags 필드와 같이 반환 된 장치 개체의 특정 필드를 설정하고 드라이버 정의 정보로 장치 확장을 초기화합니다. 새 장치 개체에 필요한 다른 작업은 장치 개체 초기화 및 장치에 대한 장치 유형 관련 설명서를 참조하십시오.

DeviceClassGuid 매개 변수로 지정된 GUID는 장치 개체의 장치 설치 클래스를 결정합니다. 장치 설정 클래스에 대한 자세한 내용은 장치 설정 클래스를 참조하십시오. 호출자는 시스템 관리자가 장치의 보안 설정을 변경할 수 있도록 값을 제공해야합니다 (예 : 특정 사용자의 액세스를 거부하는 경우). 자세한 내용은 설치 후 장치 개체 레지스트리 속성 설정을 참조하십시오.

비 WDM 드라이버는 기존 장치 설치 클래스에서 아직 사용하지 않는 GUID를 지정합니다. Microsoft Windows SDK에 포함 된 GuidGen.exe 도구를 사용하여 새 GUID를 생성하십시오.

원시 모드 가능 장치를 처리하는 WDM 버스 드라이버는 장치의 장치 설정 클래스를 지정할 수 있지만 해당 클래스가 이미 생성 된 것으로 보장되는 경우에만 가능합니다. 그렇지 않으면 새 GUID를 만듭니다.

디스크, 테이프, CD-ROM 및 RAM 디스크의 장치 개체에는 볼륨이 장치에 탑재 된 적이 없음을 나타 내기 위해 초기화되는 볼륨 매개 변수 블록 (VPB)이 제공됩니다.

WdmlibIoCreateDeviceSecure에 대한 드라이버 호출이 오류를 반환하면 드라이버는 해당 장치에 할당 된 모든 리소스를 해제해야합니다.



### 2.3 서비스에 대한 권한 재설정

출처: <https://securityfactory.tistory.com/255>

서비스는 시스템 권한으로 동작하기 때문에 서비스에 등록되는 프로세스와 데이터를 주고 받거나 통신을 하기 위해서는 해당 서비스에 대한 권한을 재설정 해주어야 합니다. 이때 사용하는 함수가 **ConvertStringSecurityDescriptorToSecurityDescriptor** API 입니다.

**Syntax**

```c++
BOOL WINAPI ConvertStringSecurityDescriptorToSecurityDescriptor (
                    _In_  LPCTSTR              StringSecurityDescriptor,
                    _In_  DWORD 			   StringSDRevision,   
                    _Out_ PSECURITY_DESCRIPTOR *SecurityDescriptor,
                    _Out_ PULONG 			   SecurityDescriptorSize
);
```

첫번째 파라미터로 들어가는 값

D:P(A;;GA;;;SY)(A;;GA;;;BA)(A;;GA;;;WD)(A;;GA;;;RC)S:(ML;;NW;;;LW)



D:			// DACL

P			// Security descriptor control flags

​			   //P의 의미: The SE_DACL_PROTECTED flag is set.

(A;;GA;;;SY)	//Allow Read/Write/Excute right to System

(A;;GA;;;BA)      //Allow Read/Write/Excute right to Built-in administrators.

(A;;GA;;;WD) 	//Allow Read/Write/Excute right to Everyone

(A;;GA;;;RC) 		//Allow Read/Write/Excute right to Restricted code.

S:			//SACL

(ML;;NW;;;LW)	//보안권한 낮게 만들기



<<참고 사이트>>

 \- 첫 번째 파라미터 값의 의미 및 설명

   <https://msdn.microsoft.com/ko-kr/library/windows/desktop/aa379570(v=vs.85).aspx>

#### 보안 설명자 문자열 형식

보안 설명자 문자열 형식은 보안 설명자에 정보를 저장하거나 전송하기위한 텍스트 형식입니다. ConvertSecurityDescriptorToStringSecurityDescriptor 및 ConvertStringSecurityDescriptorToSecurityDescriptor 함수는이 형식을 사용합니다.



 \- 괄호 안의 값들의 의미

   <https://msdn.microsoft.com/ko-kr/library/windows/desktop/aa374928(v=vs.85).aspx>



 이상으로 '[Tip] 서비스에 대한 권한 재설정'에 대한 포스팅을 마치겠습니다.



### 2.4 ACE Strings

보안 설명자 정의 언어 (SDDL)는 보안 설명자 문자열의 DACL 및 SACL 구성 요소에 ACE 문자열을 사용합니다.

보안 설명자 문자열 형식 예에서 볼 수 있듯이 보안 설명자 문자열의 각 ACE는 괄호로 묶습니다. ACE 필드는 다음 순서로되어 있으며 세미콜론 (;)으로 구분됩니다.

`syntax`

```
ace_type;ace_flags;rights;object_guid;inherit_object_guid;account_sid;(resource_attribute)
```

#### Fields

##### <span style="color:green">**ace_type**</span>

ACE_HEADER 구조체의 AceFlags 멤버 값을 나타내는 문자열입니다. ACE 플래그 문자열은 Sddl.h에 정의 된 다음 문자열의 연결 일 수 있습니다.



##### <span style="color:green">**ace_flags**</span>

##### <span style="color:green">**rights**</span>

##### <span style="color:green">**object_guid**</span>

##### <span style="color:green">**inherit_object_guid**</span>

##### <span style="color:green">**account_sid**</span>

##### <span style="color:green">**resource_attribute**</span>





### 2.5 [현재 사용자가 관리자 그룹에 포함되는지 확인하기](https://securityfactory.tistory.com/245)

현재 프로세스가 로컬 관리자 계정으로 실행 중인지 확인하려면 프로세스와 관련된 액세스 토큰을 검사해야 합니다. 오늘은 이 작업을 수행하는 방법에 대해 알아보고자 합니다.

```c++
#include <windows.h>
#include <vector>
  
BOOL IsCurrentUserLocalAdministrator(void);
  
void main(int argc, char **argv)
{
   if (IsCurrentUserLocalAdministrator())
      printf("You are an administrator\n");
   else
      printf("You are not an administrator\n");
}
 
/*-------------------------------------------------------------------------
IsCurrentUserLocalAdministrator ()
현재 사용자가 관리자 그룹에 포함되는지 확인한다.
--------------------------------------------------------------------------*/
BOOL IsCurrentUserLocalAdministrator(void)
{
    HANDLE access_token; 
    DWORD buffer_size = 0; 
    PSID admin_SID; 
    TOKEN_GROUPS *group_token = NULL; 
    SID_IDENTIFIER_AUTHORITY NT_authority = SECURITY_NT_AUTHORITY; 
 
    if (!OpenProcessToken(GetCurrentProcess(),TOKEN_READ,&access_token)) 
        return false; 
 
    GetTokenInformation(access_token, 
                        TokenGroups, 
                        group_token, 
                        0, 
                        &buffer_size); 
 
    std::vector<char> buffer(buffer_size);
 
    group_token = reinterpret_cast<TOKEN_GROUPS*>(&buffer[0]); 
 
    bool succeeded = GetTokenInformation( access_token,
                                          TokenGroups, 
                                          group_token, 
                                          buffer_size, 
                                          &buffer_size); 
 
    CloseHandle(access_token);
 
    if (!succeeded) 
        return false;
 
    if (!AllocateAndInitializeSid( &NT_authority, 
                                    2, 
                                    SECURITY_BUILTIN_DOMAIN_RID, 
                                    DOMAIN_ALIAS_RID_ADMINS, 
                                    0,0,0,0,0,0, 
                                    &admin_SID )) 
    {
        return false; 
    }
 
    bool found=false;
     
    for(int i=0; !found && i < group_token->GroupCount; i++) 
        found = EqualSid(admin_SID,group_token->Groups[i].Sid);
         
    FreeSid(admin_SID);
     
    return found;
}

```

