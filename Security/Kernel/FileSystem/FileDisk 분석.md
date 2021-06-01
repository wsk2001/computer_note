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



---

## 3. nlited systems 분석 내용

출처: <https://aws.nlited.org/p1031.htm>

### Disk Device

Bo Branten의 filedisk-21을 참조로 사용하여 디스크 장치를 작성하는 코드를 청크에 보냈습니다. 긴 해킹 후 완전한 프로젝트를 구축 할 수 있었고, 대부분의 두 번째 날에는 원격 디버깅 문제를 다루는 등 여러 가지 문제를 해결하는 데 많은 시간을 할애했습니다.

드라이버는 아무런 문제없이 작동하는 것처럼 보였지만 File Explorer에 새로운 디스크가 나타나지 않습니다. 나는 하루 하루를 성공없이 상상할 수있는 모든 것을 시도했다.

오늘 저는 filedisk를 자세히 살펴보고 있습니다. 원래 버전을 사용하는 데 어려움을 겪었던 여러 가지 문제가 있었기 때문에 실제로 실행하지 못했습니다.

- 주문형로드 / 언로드가 없기 때문에 부팅 중에 드라이버를 로드해야합니다. 이것은 디버깅을 매우 어색하게 만들뿐만 아니라 드라이버가 아무 것도하지 않는 것처럼 보입니다.
- 내 드라이버 서명 인증서를 프로젝트에 통합해야합니다. 왜냐하면 filedisk 프로젝트는 표준보다 상당히 다른 Visual Studio 드라이버 템플릿을 사용하여 작성 되었기 때문에 이것은 간단하지 않았습니다. 프로젝트 속성에는 사용자 지정 빌드 단계가 없습니다.
- filedisk 프로젝트는 이상한 (나에게) 레이아웃을 사용하여 조직되었으므로 특별히 그것에 몰두할 동기는 없었습니다.

내가 디스크를 만들 수없는 이유를 알아 내려고 쓰는 데 2 일을 소비 했으므로 filedisk 프로젝트를 파악하는 데 더 많은 동기가 부여되었습니다. 내 참조 프로젝트가 실제로 작동하는지 알아야합니다. 저는 그것이 코드와 웹 사이트의 코멘트가 최근에 2015 년으로 업데이트되어야한다고 생각합니다.

#### Filedisk Layout

filedisk 프로젝트는 'filedisk-21'디렉토리 안에 두 개의 별도 솔루션 (보너스 혼동을 위해 'filedisk'라고 이름 지어 짐)으로 구성됩니다. 'exe'아래의 프로젝트는 가상 디스크를 마운트하고 언마운트하는 사용자 모드 인터페이스를 구축합니다. 'sys'아래의 프로젝트는 장치 드라이버를 빌드합니다. 소스 코드는 'src'디렉토리에 있고 이진 출력은 x64\\Debug\\ 디렉토리에 있습니다.

#### Driver Signing

이것은 쉬운 수정으로 밝혀졌습니다. 사용자 지정 단계에서 직접 signtool을 호출하는 대신 드라이버 프로젝트에는 속성 페이지가 있습니다. 실제 인증서 파일이 아닌 로컬 컴퓨터 저장소에 설치된 인증서를 가리켜 야했습니다.

![](.\Images\I002108_176x120.png)

#### Deploying

프로젝트의 이상한 조직은 특히 드라이버가 부팅 중에로드되기 때문에 앱과 드라이버를 모두 배포하기가 까다 롭습니다. 드라이버 프로젝트에는 Visual Studio 커널 디버거를 사용한다고 가정하는 Visual Studio 드라이버 프로젝트이므로 별도의 '배포'속성이 없습니다. 그런 다음 Visual Studio는 자체 '배포 설치'를 통해 대상을 구성하려고합니다. 나는이 일을 올바르게 할 수 없었습니다. 내 목표는 이미 WinDbg로 작업하고 있지만 Visual Studio와 통신 할 수없는 것 같습니다. 나는 포트 49999를 사용하라고 말하며 포트 50005 사용을 주장합니다.

어쨌든 Visual Studio 커널 디버거를 사용하고 싶지 않아서 한 줄의 .bat 파일을 작성하여 filedisk.sys를 대상에 복사했습니다.

부팅하는 동안 드라이버를로드하는 것은 정말 귀찮은 일입니다. 시스템은 드라이버 파일에 열린 핸들을 남겨 둡니다. 다시 덮어 쓸 수 없습니다. 드라이버를 업데이트하려면 두 번 재부팅해야합니다.

- 레지스트리에서 서비스를 사용하지 않도록 설정하십시오.
- 드라이버를로드하지 않고 다시 부팅하십시오.
- 드라이버 파일을 업데이트하십시오.
- 레지스트리에서 서비스를 사용 가능하게하십시오.
- 재부팅하여 새 드라이버를로드하십시오.

이는 받아 들일 수없는 일이며, 다음 번 작업은 필요에 따라 드라이버를 로드 및 언로드하도록 앱을 업데이트하는 것입니다.

응용 프로그램을 배포하는 것이 더 좋지만 여전히 고유 한 문제가 있습니다. 드라이버를 테스트하려면 여러 명령 행 옵션으로 여러 번 앱을 실행해야합니다.

- filedisk /mount C:\Test\FileDisk\test.dsk 8M F:
- filedisk /umount F:

#### Running FileDisk

필자는 결국 빌드 및 배포 문제를 분류하여 대상에서 FileDisk를 실행할 수있었습니다. 내 프로젝트와 똑같은 방식으로 작동합니다. 모든 것이 잘 작동하고 오류가보고되지 않으며 장치 개체가 만들어 지지만 새로운 디스크가 나타나지 않습니다. 이것은 실망 스럽지만, 저는 그 filedisk가 단순히 작동하지 않는다고 믿는 데 어려움을 겪습니다. 나는 다른 곳에서 이 프로젝트에 대한 언급을 보았으므로 그것이 여전히 효과가 있다고 확신한다. 프로젝트를 시작해야 할 것입니다.

![](.\Images\I002109_262x120.png)

#### Demand Load/Unload

커널 디버깅은 매우 지루하고 부트 로딩은 정말 끔찍합니다. 요청시 드라이버를 로드하는 작업은 간단하고 쉽습니다. FileDisk 프로젝트가 아직 이 작업을 수행하지 않는 이유를 알지 못합니다. 앱을 시작하고 중지 할 필요가 있도록 서비스를 만드는 데 RegEdit을 사용하여 몇 가지 바로 가기를 사용하려고합니다.

##### `FileDisk.reg`

```
Windows Registry Editor Version 5.00

[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\FileDisk]
"DisplayName"="FileDisk virtual disk driver."
"ErrorControl"=dword:00000001
"Start"=dword:00000003
"Type"=dword:00000001
"ImagePath"="\\??\\C:\\Test\\FileDisk\\filedisk.sys"

[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\FileDisk\Parameters]
"NumberOfDevices"=dword:00000004
```



##### Start (load) the driver:

```c
void StartDriver(void) {
  SC_HANDLE hMgr,hService;
  if(!(hMgr= OpenSCManager(0,0,SC_MANAGER_CONNECT))) {
    fprintf(stderr,"OpenSCManager() failed [%d]. Are you running as admin?\n",GetLastError());
  } else {
    if(!(hService= OpenService(hMgr,SERVICE_NAME,SERVICE_ALL_ACCESS))) {
      fprintf(stderr,"OpenService(%s) failed (%d). Are you running as admin? Did you install the service?\n",SERVICE_NAME,GetLastError());
    } else {
      if(!StartService(hService,0,0)) {
        int WinErr= GetLastError();
        if(WinErr==ERROR_SERVICE_ALREADY_RUNNING) {
          printf("Service(%s) is already running.\n",SERVICE_NAME);
        } else {
          fprintf(stderr,"StartService(%s) failed (%d).\n",SERVICE_NAME,WinErr);
        }
      } else {
        printf("Service(%s) has been started.\n",SERVICE_NAME);
      }
      CloseServiceHandle(hService);
    }
    CloseServiceHandle(hMgr);
  }
}

```



##### Stop (unload) the driver:

```c
void StopDriver(void) {
  SC_HANDLE hMgr,hService;
  if(!(hMgr= OpenSCManager(0,0,SC_MANAGER_CONNECT))) {
    fprintf(stderr,"OpenSCManager() failed [%d]. Are you running as admin?\n",GetLastError());
  } else {
    if(!(hService= OpenService(hMgr,SERVICE_NAME,SERVICE_ALL_ACCESS))) {
      int WinErr= GetLastError();
      if(WinErr==ERROR_SERVICE_DOES_NOT_EXIST) {
        printf("Service(%s) does not exist.\n",SERVICE_NAME);
      } else {
        fprintf(stderr,"OpenService(%s) failed (%d). Are you running as admin? Did you install the service?\n",SERVICE_NAME,WinErr);
      }
    } else {
      SERVICE_STATUS Status;
      if(!ControlService(hService,SERVICE_CONTROL_STOP,&Status)) {
        int WinErr= GetLastError();
        if(WinErr==ERROR_SERVICE_NOT_ACTIVE) {
          printf("Service(%s) is not running.\n",SERVICE_NAME);
        } else {
          fprintf(stderr,"ControlService(%s) failed. (%d)",SERVICE_NAME,GetLastError());
        }
      } else {
        printf("Service(%s) has been stopped.\n",SERVICE_NAME);
      }
      CloseServiceHandle(hService);
    }
    CloseServiceHandle(hMgr);
  }
}
```



##### Patch it into main():

```c
    if(!strcmp(Command,"/start")) {
      StartDriver();
    } else if(!strcmp(Command,"/stop")) {
      StopDriver();
    } else if ((argc == 5 || argc == 6) && !strcmp(Command, "/mount"))

```



#### Debugging FileDisk

첫 번째 작업은 FileDisk 드라이버에서 디버그 출력이 표시되지 않는 이유를 파악하는 것입니다. DriverEntry 함수에 DbgPrint () 및 DbgBreakPoint ()를 놓고 실행합니다.

```c
NTSTATUS
DriverEntry (
    IN PDRIVER_OBJECT   DriverObject,
    IN PUNICODE_STRING  RegistryPath
    )
{
    UNICODE_STRING              parameter_path;
    RTL_QUERY_REGISTRY_TABLE    query_table[2];
    ULONG                       n_devices;
    NTSTATUS                    status;
    UNICODE_STRING              device_dir_name;
    OBJECT_ATTRIBUTES           object_attributes;
    ULONG                       n;
    USHORT                      n_created_devices;

    DbgPrint("FileDisk has arrived.\n");
    DbgBreakPoint();

    parameter_path.Length = 0;
    
    ...
}

```



DbgPrint ()에서 문서를 읽으려면 다음 레지스트리를 편집해야합니다.

```
HKEY_LOCAL_MACHINE\
  SYSTEM\
    CurrentControlSet\
      Control\
        Session Manager\
          Debug Print Filter\
            DEFAULT = DWORD 0xFF
            IHVDRIVER = DWORD 0xFF
```

DbgFilter () 출력을보기 위해 DEFAULT의 모든 출력 (0xFF)을 활성화해야했습니다. 좋은 소식은 이제 filedisk.sys에서 디버그 텍스트를 볼 수 있다는 것입니다. 나쁜 소식은 시스템의 모든 드라이버에서 디버그 텍스트로 스팸되고 있다는 것입니다. 이로 인해 일부 의심스러운 활동이 노출되었지만 대부분 모든 것이 느려지 게되었습니다. DbgPrint ()에 대한 모든 호출을 DbgPrintEx (IHV_DRIVER_ID, 3, 'format', ...)로 바꿔야합니다. 이것은 더 낫지 만 여전히 최적은 아닙니다. 내 드라이버에서만 디버그 출력을보고 싶습니다. 비디오 드라이버의 디버그 출력에 전혀 관심이 없습니다. 사용자 정의 디버그 필터를 만드는 방법은 없습니다! 그 다음으로 가장 좋은 방법은 모든 디버그 출력을 ERROR로 설정하고 IHVDRIVER = 1로 설정하여 시스템을 파괴하는 것입니다. 그리고 아무도이 작업을하지 않았으며 릴리즈 빌드에서 제거하지 않았습니다. **추가 보너스로 시스템은 부팅 할 때 디버그 필터를 읽고 결코 다시 확인하지 않습니다. 필터를 바꾸고 싶을 때마다 다시 부팅해야합니다.** Ugly.

DEFAULT를 끄면 도움이되었지만 스팸이 너무 많아서 시스템을 거의 사용할 수 없었습니다. IHVDRIVER가 1로 설정되어 있으며 여전히 받아 들일 수없는 양의 스팸이 있습니다. 최악의 범죄자는 onecore, 센서, UsbSleepStudy입니다. onecore 녀석은 특히 나쁜 polluters입니다. 나는 LSEROUS (DPFLTR_LSERMOUS_ID)를 시도하고 그것은 깨끗한 것 같습니다.

스팸 디버그는 실제 문제입니다. 왜냐하면 대상 시스템이 WinDbg에 데이터를 보내려고 할 때 때때로 멈추는 네트워킹 문제가있는 것 같습니다. 이것은 WinDbg에서 BREAK를 치고 다시 시작하여 지울 수 있지만 디버그 텍스트가 너무 많으면 시스템을 사용할 수 없게 만듭니다.

나는 모든 DbgPrintf () 호출을 Debug ()로 대체했다.

`Debug()`

```c
#define DBG_CRITICAL    0x00000000
#define DBG_ERR         0x00000001
#define DBG_WARN        0x00000002
#define DBG_INFO        0x00000004
#define DBG_INIT        0x00000100
#define DBG_IOCTL       0x00000200
#define DBG_READ        0x00000400
#define DBG_MEDIA       0x00000800
#define DBG_WORK        0x00001000

void Debug(DWORD Filter, const char *Fmt, ...) {
  if(!Filter || (Filter & DbgFilter)) {
    va_list ArgList;
    va_start(ArgList,Fmt);
    ULONG Level= (Filter & DBG_ERR) ? DPFLTR_ERROR_LEVEL : (Filter & DBG_WRN) ? DPFLTR_WARNING_LEVEL : 3;
    vDbgPrintfEx(DPFLTR_LSERMOUS,Level,Fmt,ArgList);
    va_end(ArgList);
  }
}
```

디버그 텍스트에 문제가 발생했습니다.

```
FileDisk: OpenMedis(\??\8M) is pending.
FileDiskOpenFile: \??\8M
FileDisk: File \??\8M not found.
```

파일 이름이 명령 줄에서 제대로 구문 분석되지 않고있는 것처럼 보이고 filedisk가 자동으로 실패합니다.

유니 코드를 사용하는 드라이버와 8 비트 ASCII를 사용하는 응용 프로그램간에 혼란이있었습니다.

ASCII / 유니 코드 항목을 정렬하는 데 당황스럽게 오랜 시간이 걸렸습니다. 그 가 UNICODE_STRING 구조체를 만들고 null 종결자를 버렸을 때, 그는 바이트를 저장하고 수백만의 미래의 노동 시간을 낭비했습니다. FileDisk 드라이버가 이제는 미디어 파일을 제대로 열지 만 새로운 디스크는 나타나지 않습니다!

다음에 무엇을해야할지 모르겠습니다.(I'm not sure what to do next.)

나는 이것이 가시성 문제라고 생각한다. 관리자로 앱을 실행해야하며 볼륨 장치 (및 심볼릭 링크)는 관리자 권한으로 만 볼 수 있습니다. 응용 프로그램이 종료되고 일반 사용자가 다시 시작되면 새로 생성 된 디스크가 숨겨집니다. 이것은 단지 이론 일 뿐이지 만 모든 것이 작동하는 것 (오류 없음)을 설명 할 수는 있지만 새로운 디스크는 보이지 않습니다. 내가 그들을 다시 만들 수 없기 때문에 그들이 만들어지고있는 것 같아요, 나는 그들을 삭제할 수 있습니다. 마침내 [Macrium.com](https://blog.macrium.com/virtual-disk-drivers-uac-and-nt-20c1cfc3933e)에서 다음과 같은 내용의 페이지를 발견했습니다.

---

그러나 UAC에서는 작동하지 않습니다. 적어도, 예상대로. 첫째로 - DefineDosDevice는 주어진 드라이브 문자에서 장치로 심볼릭 링크를 생성합니다. 원칙적으로 이것이 바로 당신이 달성하기를 원하는 것입니다. 그러나 링크가 만들어지는 위치에 따라 **당신(실행하는 user)이** 누구인지에 따라 달라집니다.

- NTAUTHORITYSYSTEM: 링크가 \\GLOBAL?? 에 생성됩니다.
- Administrator running as elevated: 권한이 부여 된 사용자의 세션 공간에 링크가 생성됩니다.
- 정상적인 권한으로 실행되는 관리자 : 일반 사용자의 세션 공간에 링크가 생성됩니다.

그래서, 당신이 누구인지에 따라 드라이브 문자가 얼마나 보이는가가 달려 있습니다. 예를 들어 일반 사용자로 실행중인 관리자로 드라이브를 만들면  탐색기를 실행하여 드라이브를 볼 수 없습니다! 반대로 상승 된 모드에서 생성 된 드라이브는 승격되지 않은 사용자에게는 보이지 않습니다.

---

나는 부분적인 해결책을 찾았을지도 모른다. 형식을 사용하여 전역 네임 스페이스에서 심볼릭 링크를 만들도록 강제 할 수 있습니다.

```
\DosDevices\Global\F:
```

이렇게하면 파일 탐색기를 포함하여 모든 사람이 볼 수있는 항목이 만들어집니다.

![](.\Images\I002110_190x120.png)

#### 새로운 디스크가 탐 색기에 나타납니다!

디스크가 완전히 비어 있으므로 포맷을 해야합니다. FileDisk는 하위 수준의 저장소 만 제공하고 파일 시스템 (FAT 또는 NTFS)은 상위 파일 시스템 드라이버에서 제공합니다. NTFS 용으로 디스크를 빠르게 포맷 할 수 있습니다. NTFS는 4.27MB의 8MB 디스크를 사용합니다. 그런 다음 디스크를 다른 드라이브로 취급 할 수 있습니다. 새 파일을 만들고 읽고 쓰십시오.

드라이브에서 실제 입출력을 시작하면 WinDbg로 보내지는 많은 '처리되지 않은 Ioctl'메시지가 발생하여 많은 정지 및 지연이 발생하므로 프로세스가 매우 느립니다.

FileDisk 프로젝트는있는 그대로 Windows 10에서 작동하지 않습니다.

그러나 이제는 기능적 가상 드라이브가 있습니다! 우와!

이것은 나에게 약간의 수수께끼를 남겨 둡니다.

- DeviceIoControl ()에는 관리자 권한(admin privileges)이 필요한가. (?)
- DefineDosDevice ()에는 관리자 권한이 필요하지만 관리자가 작성한 내용은 관리자 컨텍스트에서만 볼 수 있습니다.
- 드라이버와 통신하려면 앱에 글로벌 DOS 장치가 필요합니다.
- 드라이버는 볼륨 문자를 알기 전에 장치를 만들어야합니다. 컨트롤 장치를 만드는 나의 초기 전략은 결국 올바른 접근 방식이었습니다.
- 드라이버가 OPEN_FILE 명령을 보내어 볼륨 문자에 대한 심볼릭 링크를 생성 할 때까지 기다린 다음 **DosDevices\Global\F:** 형식을 사용해야합니다.
- 드라이버에는 데스크탑의 볼륨 열거를 새로 고치는 메커니즘이 없습니다. 앱에서이 작업을 수행해야합니다.

사용자 모드 DefineDosDevice ()를 사용하여 전역 이름을 만들 수 있는지 확인해야합니다.



#### 정리 및 검토

오늘 작업은 FileDisk 프로젝트의 제 버전을 정리하여 원본 버전을 작동시키는 데 필요한 최소한의 변경 사항을 결정하는 것입니다. 그런 다음 FileDisk의 작업 버전을 내 CryptDisk 프로젝트의 참조로 다시 사용할 수 있습니다.

왜 원래의 FileDisk가 작동하지 않았는지 명확히 알 수 없습니다. Bo Branten은 분명히 2015 년 12 월 16 일에 출시 21 일 - 2 년 이내에 효과가 있다고 생각합니다. 그는 심지어 WDK 10을 사용하도록 업데이트되었다고 말합니다. 그러나 여기에는 중요한 참고 사항이 있습니다.

***Note:*** 이후 버전의 Windows  Explorer에서 가상 디스크를 보려면 마운트 프로그램 filedisk.exe를 일반 사용자로 실행해야하며 관리자 권한으로 실행되는 명령 프롬프트에서는 실행하지 마십시오.

**RTFM!**(Read The Fucking Manual, Read The Fucking Man page) 

이 때문에 혼란 스럽지만 관리자가 드라이버를 로드해야하기 때문에. 원래의 FileDisk가 부팅시 로드되도록 구성되어있는 이유는 무엇입니까? Bo가 '숨겨진 개체 경로'문제에 부딪혔다 고 생각하고 부팅시 드라이버를 자동으로 로드하거나 UAC를 사용하지 않도록 설정하거나 관리자로 로그인하여 문제를 회피했습니다.

원본 FileDisk-21 소스부터 시작하여 최소한의 변경 사항 (드라이버 서명 및로드)을 적용하고 다시 테스트해야합니다.

```
흠 ... FileDisk가 의심스러운 기원을 가지고있는 것 같습니다. FileDisk를 연구 자원으로 사용하고 그로부터 아무것도 복사하지 않도록 주의해야합니다. CryptDisk는 처음부터 완전하게 작성되었으며 상당한 구조적 디자인 차이가있는 리터럴 FileDisk 코드를 포함하지 않습니다. 질문은 : 지식이 저작권으로 보호 되는가?
```

[질문 링크](http://www.osronline.com/showThread.cfm?link=114787)

디스크 장치가 생성되는 방식을 재구성했습니다. 디스크는 마운트 명령에 대한 응답으로 필요할 때 만들어지며 미디어가 마운트 해제 될 때 항상 파괴됩니다. 미디어가 없을 때 가상 디스크를 주변에 두는 것은 아무 의미가 없습니다. 또한이 방법을 사용하면 정확히 올바른 수의 장치를 만들 수 있습니다.

제어 장치는 드라이버가로드 된 직후에 생성됩니다. 그러면 로더 응용 프로그램이 마운트 명령을 제어 드라이버에 보내고,이 드라이버가 미디어를 관리 할 새 디스크 장치를 만듭니다. 앱에서 볼륨 문자를 지정하고 기기 번호를 받지만 기기 번호는 사용되지 않습니다. 그런 다음 디스크 장치는 마운트 해제 명령을 포함하여 볼륨 장치 (예 :  \\.\F:)를 통해 볼륨에 대한 모든 명령을 처리합니다.

모든 장치 생성 및 명명은 드라이버에서 처리되며 응용 프로그램에서만 명령을 보냅니다.

#### 볼륨 마운트

나는 어제와 오늘 아침에 NtString에서 버그를 분류하는 일을했습니다. 원격 커널 내부에서 문자열 코드를 디버깅하는 것만큼이나 지루한 작업은 없습니다.

마운트 명령을 수행 중입니다.

- 응용 프로그램은 미디어 파일, 미디어 크기 및 볼륨 문자가 들어있는 MountMedia_s 구조체를 사용하여 MOUNT ioctl을 제어 장치 (\\.\CryptControl)로 보냅니다.
- 제어 장치는 새 디스크 장치 (\Device\CryptDisk01)를 만들고 탑재 IRP를 DeviceDisk :: Create ()에 전달합니다.
- DeviceDisk :: Create ()는 디스크에 대한 새 작업자 스레드를 시작하고 작업 목록에 탑재 IRP를 추가하고 TaskEvent에 신호를 보내고 STATUS_PENDING을 반환합니다.
- 제어 장치가 IRP를 대기 중으로 표시하고 반환합니다.
- 잠시 후 디스크 장치의 작업자 스레드가 깨어나서 작업 목록에서 탑재 IRP를 가져옵니다.
- 디스크 장치는 마운트 정보를 추출하여 미디어 파일을 생성하거나 엽니다.
- 디스크 장치가 볼륨 링크 (\\\\DosDevices\\Global\\F:)를 만듭니다.
- 탑재 IRP가 완료되었습니다 (IoCompleteRequest ()).
- 작업자는 다음 TaskEvent를 기다립니다.

이 모든 것이 효과가있는 것처럼 보입니다: mount 명령이 성공적으로 완료되고 새 볼륨이 파일 탐색기에 나타납니다.

하지만 내 디스크 장치에는 어떤 명령도 수신되지 않습니다. 응용 프로그램이 볼륨 장치 (\\.\F:)를 열려고하면 '파일을 찾을 수 없습니다'와 함께 실패합니다. 디스크 장치 ( Device CryptDisk01)에 대한 심볼릭 링크 인 WinObj (\GLOBAL??\F:)의 볼륨을 볼 수 있습니다.

Working FileDisk는 볼륨 당 2 개의 항목을 생성합니다 :

\DosDevices\Global\F: -> \Devices\FileDisk\FileDisk0

나는 이것이 권한 문제라고 생각한다.  WinObj를 사용하여 제어 장치의 등록 정보를 표시 할 수 있지만 디스크 장치는 표시 할 수 없습니다.

![](.\Images\I002111_352x240.png)

제어 장치는 IoCreateDevice ()를 사용하여 생성됩니다. 디스크 장치는 IoCreateDeviceSecure ()를 사용하여 생성되지만 모든 사용자가 액세스 할 수 있도록 허용해야합니다. 보안 설명자는 `D:P(A;;GA;;;SY)(A;;GA;;;BA)(A;;GA;;;BU)` 입니다.

```
D: DACL definition
 P = SDDL_PROTECTED
 (A;;GA;;;SY) = ALLOWED GENERIC_ALL SDDL_LOCAL_SYSTEM
 (A;;GA;;;BA) = ALLOWED GENERIC_ALL SDDL_BUILTIN_ADMINISTRATORS
 (A;;GA;;;BU) = ALLOWED GENERIC_ALL SDDL_BUILTIN_USERS
```

인증 된 사용자 (AU)도 포함시켜야 할 필요가있는 것 같지만 제어 장치를 볼 수있는 이유가 있습니다. 이것은 아무런 효과가 없었다.

이제 디스크 장치는 응용 프로그램의 ioctl에 대한 응답으로 생성됩니다. 이전에는 디스크 장치가 서비스 관리자에 의해 호출 된 DriverEntry () 중에 작성되었습니다.

어쩌면 DosDevices에서 CryptDisk01에 대한 링크가 필요합니까? 심볼릭 링크에 액세스 할 수 있지만 Device CryptDisk01에 액세스하는 데 여전히 실패하고 \\.\F:에 연결할 수 없습니다.

같은 결과로 하드 코딩 된 이름을 사용하여 시도했습니다.

내가 보는 유일한 차이점은 이제 장치가 나중에 생성된다는 것입니다. 이 이론을 테스트하려면 제어 장치가 작성된 것과 동일한 컨텍스트에서 디스크 장치를 작성해야합니다. 이렇게하려면 하드 코딩 된 MountMedia_s 구조체를 만들어야합니다. 먼저 산책을해야합니다.

DriverEntry ()에서 디스크 장치를 만들면 디스크 장치 (\Device\CryptDisk)에 액세스 할 수 있습니다. 그런 다음 볼륨 장치 (\\.\F:)에 대한 핸들을 열 수 있습니다. 새 볼륨이 파일 탐색기에 나타납니다. 디스크 장치에 ioctls가 갑자기 나타납니다. 따라서로드 시간에 모든 디스크 장치를 만들어야 할 필요가있는 것 같습니다. 불행합니다. 어느 시점에서 필자는 이것이 퍼미션(permissions) (나는 생각하지 않음)인지 또는 가시성(visibility) (나는 그렇게 생각한다) 문제인지를 알아 내기 위해 좀 더 연구해야 할 필요가있다. 필요에 따라 디스크 장치를 만들고 파괴하는 것이 훨씬 더 좋을 것입니다. 사용자가 만들 디스크의 수를 모르기 때문입니다.

볼륨의 모양은"\DosDevices\C:"와 같은 이름을 가진 \DosDevices\ 아래의 모든 항목의 모양 일뿐입니다. 드라이버와의 실제 상호 작용에 의존하지 않는 것처럼 보입니다.

그러나 새 디스크를 포맷 할 수 없습니다. 나는 치아 빗으로 ioctls를 통과해야합니다.

[ioctls.net](http://www.ioctls.net/)은 찾아보기 테이블입니다.

```
IrpTrace:
CryptDisk|DBG DeviceDisk:Worker: Waiting for requests...
CryptDisk|DBG DeviceDisk:IrpDispatch2(14)
CryptDisk|DBG DeviceDisk:IoControl: 2049 (7E004)
CryptDisk|DBG DeviceDisk:Worker:  IRP 14
CryptDisk|DBG DeviceDisk:OpenMedia: \??\C:\Test\CryptDisk\Media.dsk
Opening File Explorer
CryptDisk|DBG DeviceDisk:IrpDispatch2(0)
CryptDisk|DBG DeviceDisk:IrpDispatch2(14)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG DeviceDisk:IrpDispatch2(18)
CryptDisk|DBG DeviceDisk:IrpDispatch2(18)
CryptDisk|DBG DeviceDisk:IrpDispatch2(2)
CryptDisk|DBG DeviceDisk:IrpDispatch2(2)
CryptDisk|DBG DeviceDisk:IrpDispatch2(0)
CryptDisk|DBG DeviceDisk:IrpDispatch2(18)
CryptDisk|DBG DeviceDisk:IrpDispatch2(14)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|DBG DeviceDisk:IrpDispatch2(14)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG DeviceDisk:IoControl: 2 (4D0008)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|DBG Device:Disk:IoctlMountQueryDeviceName: [38]\Device\CryptDisk01
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|ERR DeviceDisk:IoctlMountQueryDeviceName: Buffer overflow (4,44)
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:Worker: \Device\CryptDisk01 IRP 3
CryptDisk|DBG DeviceDisk:IoctlGetHotPlugInfo: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetDeviceNumber: OK.
CryptDisk|DBG DeviceDisk:IoControl: Unhandled command 6/6 (4D0018) IOCTL_MOUNTDEV_QUERY_STABLE_GUID
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
CryptDisk|DBG DeviceDisk:IoctlGetVolumeGpt: 0000000000000000
```

그것은 항상 무언가입니다 ... 나는 FileDisk를 다시 돌렸고 그 볼륨은 나타나기를 거부했습니다. 흠 ... 내 성공은 불가사의 한 사건의 조합이었던 것 같습니다. 다행히 실제로 작동하는 이전 버전으로 되돌릴 수있었습니다.

이 버전?이 작동합니다! (마운트 해제가 제대로 작동하지 않을 수 있습니다.)

내일 나는 FileDisk와 CryptDisk 사이의 ioctl 시퀀스를 비교하여 차이점을 찾을 수 있는지 알아볼 것입니다.

#### Ioctl Tracing

아래는 FileDisk의 ioctl 추적입니다. 하나의 VM에서 동일한 머신의 다른 VM으로 네트워크 전송을 사용하는 커널 디버깅 연결은 속도가 느리고 불안정합니다. 백그라운드에서 WinDbg과 목표물을 공회전시키는 것만으로도 내 네트워크가 무릎을 꿇게됩니다. 모든 ioctl을 인쇄 할 때마다 대상이 매 10 초마다 정지했기 때문에 중복되는 ioctls를 필터링해야했습니다. 드라이버가 QUERY_DEVICE_NAME에 대한 호출로 망치질됩니다.

```
FileDisk has arrived.
FileDisk: IOCTL_?? No media. 17157/4305 [2D0C14] INVALID
FileDisk: IOCTL_?? No media. 53760/D200 [74800] INVALID
FileDisk: IOCTL_?? No media. 49170/C012 [70048] INVALID
FileDisk: IOCTL_?? No media. 49170/C012 [70048] INVALID
FileDisk: IOCTL_?? No media. 49152/C000 [70000] INVALID
FileDisk: IOCTL_?? No media. 16386/4002 [4D0008] INVALID
FileDisk: IOCTL_?? No media. 32782/800E [560038] INVALID
FileDisk: IOCTL_FILE_DISK_OPEN_FILE 63488/F800 [7E000]
FileDisk: File \??\C:\Test\FileDisk\Test.dsk opened.
FileDisk: IOCTL_MOUNTDEV_QUERY_DEVICE_NAME 16386/4002 [4D0008]
FileDisk: IOCTL_STORAGE_GET_HOTPLUG_INFO 17157/4305 [2D0C14]
FileDisk: IOCTL_DISK_GET_PARTITION_INFO_EX 49170/C012 [70048]
FileDisk: IOCTL_DISK_GET_PARTITION_INFO_EX 49170/C012 [70048]
FileDisk: IOCTL_GET_DRIVE_GEOMETRY 49152/C000 [70000]
FileDisk: IOCTL_STORAGE_GET_DEVICE_NUMBER 17440/4420 [2D1080]
FileDisk: IOCTL_DISK_IS_WRITABLE 49161/C009 [70024]
FileDisk: IOCTL_DISK_IS_WRITABLE 49161/C009 [70024]
FileDisk: IOCTL_STORAGE_QUERY_PROPERTY 17664/4500 [2D1400] INVALID
FileDisk: IOCTL_DISK_GET_LENGTH_INFO 53271/D017 [7405C]
FileDisk: IOCTL_VOLUME_GET_GPT_ATTRIBUTES 32782/800E [560038]
FileDisk: IOCTL_UNKNOWN 17508/4464 [2D1190] INVALID
FileDisk: IOCTL_UNKNOWN 16390/4006 [4D0018] INVALID
FileDisk: IOCTL_VOLUME_QUERY_ALLOCATION_UNIT 36884/9014 [564052] INVALID
FileDisk: IOCTL_FT_BALANCED_READ_MODE 32774/8006 [66001B] INVALID
MOUNTDEV_QUERY_DEVICE_NAME: OVERFLOW (4,106)
FileDisk: IOCTL_UNKNOWN 49285/C085 [70214] INVALID
MOUNTDEV_QUERY_DEVICE_NAME: OVERFLOW (4,106)
FileDisk: IOCTL_MOUNTDEV_QUERY_DEVICE_NAME 16386/4002 [4D0008]
FileDisk: IOCTL_STORAGE_GET_DEVICE_NUMBER 17440/4420 [2D1080]
FileDisk: IOCTL_UNKNOWN 17507/4463 [2D118C] INVALID
FileDisk: IOCTL_VOLUME_GET_VOLUME_DISK_EXTENTS 32768/8000 [560000]
FileDisk: IOCTL_MOUNTDEV_QUERY_DEVICE_NAME 16386/4002 [4D0008]
FileDisk: IOCTL_DISK_IS_WRITABLE 49161/C009 [70024]
FileDisk: IOCTL_GET_DRIVE_GEOMETRY 49152/C000 [70000]
FileDisk: IOCTL_DISK_GET_PARTITION_INFO_EX 49170/C012 [70048]
FileDisk: IOCTL_STORAGE_QUERY_PROPERTY 17664/4500 [2D1400] INVALID
FileDisk: IOCTL_STORAGE_GET_HOTPLUG_INFO 17157/4305 [2D0C14]
FileDisk: IOCTL_DISK_GET_MEDIA_TYPES 49920/C300 [70C00] INVALID
MOUNTDEV_QUERY_DEVICE_NAME: OK
FileDisk: IOCTL_DISK_GET_LENGTH_INFO 53271/D017 [7405C]
```

변덕스러운 손가락이 그 저주받은 QUERY_DEVICE_NAME을 (를) 가리키고 있습니다. 정확히 FileDisk를 모방하고 어떤 차이가 있는지 확인해야합니다 ...

나는 시행 착오를 많이 요구하는 규칙을 마침내 알아 냈습니다. MSDN 문서의 MOUNTDEV_NAME에 대한 설명은 번거롭고 혼란스럽고 중요한 세부 사항이 부족한 동시에 혼란 스럽습니다.

- 출력 버퍼 크기 (pStack->Parameters.DeviceIoControl.OutputBufferLength)가 sizeof (MOUNTDEV_NAME)보다 작 으면 IoStatus.Information을 0으로 설정하고 STATUS_INVALID_PARAMETER를 반환합니다.
- MOUNTDEV_NAME.NameLength는 장치 이름의 길이 (\Device\CryptDisk01)를 바이트 단위로 설정하고 종결자를 포함해야합니다.
- 출력 버퍼 크기가 너무 작아서 전체 이름을 유지할 수 없으면 IoStatus.Information을 sizeof (MOUNTDEV_NAME)로 설정하고 STATUS_BUFFER_OVERFLOW를 반환하십시오. MOUNTDEV_NAME.NameLength는 이름의 크기를 유지하므로 호출자는 버퍼를 할당하고 다시 시도 할 수 있습니다. **NOTE :** MOUNTDEV_NAME.Name은 길이가 0 인 배열에 대한 C++ 규칙 때문에 길이가 1 인 배열로 선언됩니다. 즉, 필요한 크기를 'sizeof (MOUNTDEV_NAME) NameLength'로 계산해야합니다. 그렇지 않으면 sizeof (MOUNTDEV.Name)를 빼야합니다. 이 'off by one'은 호출자가 정확히 내가보고 한 크기의 버퍼를 할당했을 때 BUFFER_OVERFLOW를 잘못보고하게 만들었습니다.
- 출력 버퍼가 충분히 크다면 유니 코드 텍스트를 터미네이터를 포함한 MOUNTDEV_NAME.Name []에 복사하고 IoStatus.Information을 sizeof (MOUNTDEV_NAME) NameLength (위 참조)로 설정하고 STATUS_SUCCESS를 반환합니다.

이제 CryptDisk의 ioctl 추적이 더 유망 해 보입니다.

DISK_GET_DRIVE_GEOMETRY에서 BytesPerSector를 설정하는 것을 잊었습니다. 이것은 왜 탐색기가 결국 사라질지를 설명 할 수 있습니다 - 선을 따라 어딘가로 나누었습니다.

성공! 이제 내 디스크에서 포맷 대화 상자를 열 수 있습니다!

![](.\Images\I002112_531x240.png)

format 이 실패했습니다. 내 MediaSize는 8MB 대신 0입니다. 나는 바보 같은 버그, 30 초 수정, 5 분 디버거를 재설정했다.

Format 이 시작된 다음 실패했습니다. DISK_SET_PARTITION에서 입력 대신 출력 버퍼를 확인했습니다. 1 분 수정, 5 분 디버거 재설정. (대상이 부팅되지 않아 Windows 복구가 시작되었습니다.) 많은 지루한 기다림과 Windbg 연결 재설정으로 인해 디스크가 이상하게 보입니다. 다시 부팅하고 미디어 파일을 삭제 한 다음 다시 시도해야합니다. (대상이 부팅에 실패하고, 로그인에 걸려 있고, 복구하고, 마지막으로 네 번째 시도에서 만들었습니다.)

성공! '포맷 완료.' 그런 다음 새 디스크에 텍스트 파일을 만들었습니다. 정상적으로 대상을 다시 시작하려고 시도했지만 종료를 거부했습니다. 이것은 CryptDisk가 마운트 해제에 실패했기 때문일 수 있습니다. 다시 시작하고 기존 미디어 파일을 다시 마운트 할 수있었습니다. 내 파일이 디렉토리에 있었지만 내용이 손실되었습니다. 나는 '두번째 포스트!'를 만들었고 언 마운트를 건너 뛰고 정상적으로 종료 할 수있었습니다. (이번에는 내 개발 VM이 대상을 재부팅하는 동안 호스트 시스템, dev VM 및 대상을 재부팅해야합니다.) 15 분 후 최종적으로 파일을 다시 열어 텍스트를 읽을 수있었습니다.



#### Post Mortem

이제는 CryptDisk 프로젝트에서 일주일을 보냈습니다. 주로 가상 디스크 드라이버에서 작업했습니다.

- 드라이버와 앱 셸을 만들고 프로젝트를 구성하는 날.
- FileDisk 프로젝트를 연구하는 날.
- 제어 장치와 디스크 장치의 첫 번째 초안을 쓰는 날.
- 하루는 왜 내 디스크 장치가 파일 탐색기에 나타나지 않았는지 알아 내려고 보냈습니다.
- FileDisk 참조 프로젝트로 빌드 문제를 수정하는 데 하루가 걸렸습니다.
- Windows가 모든 프로세스에 대한 개체 디렉터리의 개인 복사본을 생성한다는 것을 알게 된 날. 'Global4Realz'개체 경로를 발견합니다.
- NtString 클래스를 작성하는 데 하루를 소비하므로 null 종결 자에 대해 다시 걱정할 필요가 없습니다.
- UNCODE_STRING, C-string, Name [1] 및 sizeof (MOUNTDEV_NAME) 사이에 혼란스러운 '길이'정의를 풀어가는 하루가있었습니다.
- VMware와 KDnic 간의 비정상적인 네트워킹으로 인해 길을 잃었습니다.

이 프로젝트의 원래 견적은 약 3-4 일이 걸렸습니다. 나는 희미한 디버그 환경에서 손실 될 시간을 과소 평가했으며, '개인 객체 디렉토리'전체에 의해 블라인드되었고, NtString을 작성하는 여분의 날을 보내고, GetDeviceName ()을 처리하는 하루를 잃었다.



**끝**

---

