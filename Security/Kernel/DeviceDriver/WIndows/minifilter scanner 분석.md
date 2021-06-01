# minifilter scanner 분석

이 문서는 FileSystem minifilter driver 를 분석 하면서 파악된 기능에 대하여 기술 한다.

scanner minifilter driver 는 driver(scanner.sys) 와 user mode(scanuser.exe) 를 생성하는 두 개의 프로젝트로 구성되어 있다.

솔루션은 filter directory 의 scanner 프로젝트와 user directory 의 scanuser 프로젝트로 구성 된다.

파일시스템 미니필터 드라이버는 특정한 기능이 호출되기 전에 먼저 호출되는 사전 콜백 함수와, 특정 기능 완료 후 호출되는 사후 콜백 함수를 같이 등록 할 수 있습니다. 예를들어 파일에 Data 를 읽기 위해 ReadFile() 함수를 호출 한다면, PreReadFile(), PostReadFile() 두개의 함수를 Driver 에 등록 하여 사용 할 수 있습니다.



## 1. Driver (scanner.sys) 소스 분석

### 1.1 파일의 구성

scanner.sys 를 생성 하기 위한 주요 파일의 구성은 다음과 같다.

| 파일명    | 역할                                                         |
| --------- | ------------------------------------------------------------ |
| scanner.c | 스캐너 필터의 기본 모듈, 이 필터는 열기를 진행하기 전에 파일의 데이터를 검색합니다. 이는 바이러스 검사 프로그램의 기능과 유사합니다. |
| scanner.h | 커널 내부에서만 볼 수있는 구조체, 타입 정의, 상수, 전역 변수 및 함수 프로토 타입을 포함하는 헤더 파일. |
| scanuk.h  | 커널과 사용자 모드간에 공유되는 구조체, 타입 정의, 상수, 전역 변수 및 함수 프로토 타입을 포함하는 헤더 파일. |



### 1.2 드라이버 에서 생성한 주요 함수

| 파일명                             | 역할                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| DriverEntry                        | 필터 드라이버의 초기화 루틴입니다. <br/>이 함수는 Filter를 필터 관리자에 등록하고 모든 전역 데이터 구조를 초기화합니다. |
| ScannerAllocateUnicodeString       | 이 루틴은 유니 코드 문자열을 할당합니다.                     |
| ScannerFreeExtensions              | 이 루틴은 teardown 및 초기화 실패시 전역 버퍼를 정리합니다.  |
| ScannerFreeUnicodeString           | 이 루틴은 유니 코드 문자열을 해제합니다.                     |
| ScannerInitializeScannedExtensions | 이 루틴은 레지스트리를 기반으로 검사 할 파일의 확장자를 설정합니다. |
| ScannerInstanceSetup               | 이 루틴은 새로운 인스턴스가 생성 될 때 필터 관리자에 의해 호출됩니다.  우리는 수동 첨부 파일만을 원한다고 레지스트리에 지정 했으므로 여기까지 모두 받아야합니다. |
| ScannerpCheckExtension             | 파일 이름 확장자가 우리가 모니터링 하려고 하는 대상인지 확인합니다. |
| ScannerPortConnect                 | 사용자 모드가 서버 포트에 연결될 때 호출됩니다.              |
| ScannerPortDisconnect              | 사용자 모드와 연결이 끊어 질 때 호출됩니다.                  |
| **ScannerPostCreate**              | 파일 생성 사후 콜백 함수.                                    |
| ScannerPreCleanup                  | 파일이 쓰기 액세스를 위해 열린 경우 재 scan 합니다.          |
| **ScannerPreCreate**               | 파일 생성 사전 콜백 함수.                                    |
| ScannerPreFileSystemControl        | 파일 시스템 컨트롤 사전 콜백 함수                            |
| **ScannerpScanFileInUserMode**     | 이 루틴은 사용자 모드까지 요청을 보내 주어진 파일을 검사하고 호출자에게 이 파일을 여는 것이 안전한지를 알려주도록 호출됩니다. |
| ScannerQueryTeardown               | 이 루틴은 사용자가 수동으로 인스턴스 분리를 시작 할때 필터 관리자 에 의해 호출 됩니다. |
| ScannerUnload                      | 필터 드라이버의 언로드 루틴입니다.                           |



### 1.3 Driver 에서 사용한 주요 커널 함수

| 함수명                            | 역할                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| ExInitializeDriverRuntime         | Device Driver Runtime Flag 설정.  wdm.h 에 정의되어 있으나 문서화된 자료는 찾을 수 없음.  현재 DRIVER_RUNTIME_INIT_FLAGS 에는 DrvRtPoolNxOptIn 와 LastDrvRtFlag 두개의 항목만 enum 으로 잡혀 있음. <br/>드라이버는 POOL_NX_OPTIN으로 빌드되어 NonPagedPoolNx 지원을 자동으로 검색하려고합니다.<br/> DrvRtPoolNxOptIn의 이점을 자동으로 얻으려면 드라이버가 POOL_NX_OPTIN도 정의해야합니다. scanner 에서는 **DrvRtPoolNxOptIn** 을 사용 하고 있음. |
| FltRegisterFilter                 | 필터 드라이버를 등록                                         |
| RtlInitUnicodeString              | 유니 코드 문자의 카운트 된 문자열을 초기화합니다.            |
| FltBuildDefaultSecurityDescriptor | FltBuildDefaultSecurityDescriptor는 FltCreateCommunicationPort와 함께 사용할 기본 보안 설명자를 작성합니다. |
| FltCreateCommunicationPort        | 미니 필터 드라이버가 사용자 모드 응용 프로그램에서 연결 요청을받을 수있는 통신 서버 포트를 만듭니다. |
| FltFreeSecurityDescriptor         | FltBuildDefaultSecurityDescriptor 루틴에 의해 할당 된 보안 설명자를 해제합니다. |
| FltStartFiltering                 | 등록 된 미니 필터 드라이버에 대한 필터링을 시작합니다.       |
| FltCloseCommunicationPort         | 미니 필터 드라이버의 통신 서버 포트를 닫습니다.              |
| FltUnregisterFilter               | 등록 된 미니 필터 드라이버는 FltUnregisterFilter를 호출하여 등록을 취소하여 필터 관리자가 더 이상 I/O 작업을 처리하지 못하도록합니다. |
| ZwOpenKey                         | InitializeObjectAttributes 에 의해서 조회된 OBJECT_ATTRIBUTES 를 이용하여 기존 레지스트리 키를 엽니다.  InitializeObjectAttributes 는RegistryPath 를 이용하여 수행 됩니다. |
| ZwQueryValueKey                   | 레지스트리 키의 값 항목을 조회합니다.                        |
| ExAllocatePoolWithTag             | 지정된 형식의 풀 메모리를 할당하고 할당 된 블록에 대한 포인터를 반환합니다. |
| ExFreePoolWithTag                 | 지정된 태그로 할당 된 풀 메모리 블록을 할당 해제합니다.      |
| ZwClose                           | 오브젝트 핸들을 닫습니다.                                    |
| FltCloseClientPort                | 통신 클라이언트 포트를 닫습니다.                             |
| IoThreadToProcess                 | 지정된 스레드에 대한 프로세스에 대한 포인터를 리턴합니다. 이 생성이 사용자 프로세스에 의해 수행되는지 확인합니다. |
| RtlCompareUnicodeString           | 두 유니 코드 문자열을 비교합니다.                            |
| **FltGetFileNameInformation**     | 파일이나 디렉토리의 이름 정보를 반환합니다.                  |
| **FltParseFileNameInformation**   | FLT_FILE_NAME_INFORMATION 구조체의 내용을 구문 분석합니다. FltParseFileNameInformation은 FLT_FILE_NAME_INFORMATION 구조체의 Name 멤버를 구문 분석하고 결과를 사용하여 이 구조체의 Volume, Share, Extension, Stream, FinalComponent, ParentDir 및 NamesParsed 멤버의 값을 설정합니다. 자세한 내용은 FLT_FILE_NAME_INFORMATION을 참조하십시오.<br/>typedef struct _FLT_FILE_NAME_INFORMATION {<br/>USHORT                     Size;<br/>FLT_FILE_NAME_PARSED_FLAGS NamesParsed;<br/>FLT_FILE_NAME_OPTIONS      Format;<br/>UNICODE_STRING             Name;<br/>UNICODE_STRING             Volume;<br/>UNICODE_STRING             Share;<br/>UNICODE_STRING             Extension;<br/>UNICODE_STRING             Stream;<br/>UNICODE_STRING             FinalComponent;<br/>UNICODE_STRING             ParentDir;<br/>} FLT_FILE_NAME_INFORMATION, *PFLT_FILE_NAME_INFORMATION; |
| FltReleaseFileNameInformation     | 파일 이름 정보 구조체를 해제합니다. 내부적으로 할당받은 메모리를 해제 하는것 으로 보임. |
| FltCancelFileOpen                 | 미니 필터 드라이버는 FltCancelFileOpen 루틴을 사용하여 새로 열거 나 생성 된 파일을 닫을 수 있습니다. |
| FltAllocateContext                | 지정된 컨텍스트 유형에 대한 컨텍스트 구조를 할당합니다.      |
| FltSetStreamHandleContext         | 스트림 핸들에 대한 컨텍스트를 설정합니다.                    |
| FltReleaseContext                 | 컨텍스트에 대한 참조를 해제합니다                            |
| FltGetStreamHandleContext         | 주어진 미니 필터 드라이버 인스턴스에 의해 스트림 핸들에 설정된 컨텍스트를 검색합니다. |
| MmGetSystemAddressForMdlSafe      | 이 루틴은 MDL의 매핑 된 주소를 반환합니다. Mdl이 아직 맵핑되지 않았거나 시스템 주소가 맵핑되지 않은 경우 맵핑됩니다. |
| RtlCopyMemory                     | RtlCopyMemory 루틴은 원본 메모리 블록의 내용을 대상 메모리 블록에 복사합니다. memcpy() 함수를 재정의한 매크로임. |
| **FltSendMessage**                | minifilter 드라이버 또는 minifilter 드라이버 인스턴스 대신 대기중인 사용자 모드 응용 프로그램에 메시지를 보냅니다. |
| FltGetVolumeFromInstance          | 주어진 미니 필터 드라이버 인스턴스가 첨부 된 볼륨에 대한 불투명 포인터를 반환합니다.<br/>FltGetVolumeFromInstance는 RetVolume 매개 변수에서 리턴 된 불투명 볼륨 포인터에 런 다운 참조를 추가합니다. 이 포인터가 더 이상 필요하지 않으면 호출자는 FltObjectDereference를 호출하여 포인터를 해제해야합니다. 따라서 FltGetVolumeFromInstance에 대한 모든 성공적인 호출은 FltObjectDereference에 대한 후속 호출과 일치해야합니다.<br/>주어진 인스턴스를 생성 한 미니 필터 드라이버에 대한 불투명 필터 포인터를 얻으려면 FltGetFilterFromInstance를 호출하십시오.<br/>주어진 볼륨에 대한 장치 개체에 대한 포인터를 가져 오려면 [FltGetDeviceObject](https://msdn.microsoft.com/library/windows/hardware/ff543007)를 호출하십시오. <br/>주어진 인스턴스가 첨부 된 볼륨에 대한 자세한 정보를 얻으려면 [FltQueryVolumeInformation](https://msdn.microsoft.com/library/windows/hardware/ff543443)을 호출하십시오. |
| FltGetVolumeProperties            | 주어진 볼륨에 대한 볼륨 특성 정보를 리턴합니다. <br/>FltGetVolumeProperties는 시스템 교착 상태 위험없이 마운트 프로세스 중에 안전하게 쿼리 할 수있는 정보 만 반환합니다. 따라서 미니 필터 드라이버는 일반적으로이 루틴을 포스트 마운트 콜백 함수 또는 InstanceSetupCallback (PFLT_INSTANCE_SETUP_CALLBACK) 루틴에서 호출하여 주어진 볼륨에 연결할 것인지 결정합니다. |
| FltAllocatePoolAlignedWithTag     | 캐시되지 않은 I/O 작업에 사용할 장치 정렬 버퍼를 할당합니다.<br/>FltAllocatePoolAlignedWithTag는 주어진 볼륨에 대한 기본 장치에 따라 정렬 된 버퍼를 할당합니다. 이러한 장치 정렬 버퍼는 비 캐시 I/O에 필요합니다. (캐시 된 I/O에도 사용할 수 있습니다.) 따라서 FltReadFile 및 FltWriteFile과 같이 캐시되지 않은 I/O를 수행하는 루틴을 호출 할 때 Minifilter 드라이버는 **ExAllocatePoolWithTag** 대신 **FltAllocatePoolAlignedWithTag**를 호출해야합니다. |
| FltReadFile                       | FltReadFile은 열린 파일, 스트림 또는 장치에서 데이터를 읽습니다. |
| FltFreePoolAlignedWithTag         | FltAllocatePoolAlignedWithTag에 대한 이전 호출에 의해 할당 된 캐시 정렬 버퍼를 해제합니다. |
| FltObjectDereference              | 불투명 필터, 인스턴스 또는 볼륨 포인터에서 런 다운 참조를 제거합니다. |



### 1.4 분석 내용 종합

<span style="color:green">**ScannerPostCreate** 와 **ScannerPreCleanup** 에서 **ScannerpScanFileInUserMode** 를 호출 하고 **ScannerpScanFileInUserMode**  내부에서 **FltSendMessage** 를 호출 하여 User Mode(app)로 Data 를 전달 한다. (내부적 으로는 Buffer 에 저장 되는듯) 그리고 **ScannerPreWrite** 에서 **FltSendMessage** 를 호출 하여 User Mode(app)로 Data 를 전달 한다. </span>

<span style="color:green">전달된 Data 는 User Mode 에서 전송을 요청하여 받아간다.</span>



## 2. User Mode(scanuser.exe) 소스 분석

### 2.1 파일의 구성

scanuser.exe 를 생성 하기 위한 주요 파일의 구성은 다음과 같다.

| 파일명     | 역할                                                         |
| ---------- | ------------------------------------------------------------ |
| scanUser.c | 이 파일에는 사용자 응용 프로그램 스캐너의 주요 기능에 대한 구현이 포함되어 있습니다. 이 Application은 실제로 파일 내용을 검사합니다. 등록된 파일 확장자(.doc) 의 생성을 검사 하여 특정 문자열이 포함되어 있는지 검사한다. |
| scanuser.h | 스캐너의 사용자 모드 부분에 대한 구조, 유형 정의, 상수, 전역 변수 및 함수 프로토 타입을 포함하는 헤더 파일입니다. |
| scanuk.h   | 커널과 사용자 모드간에 공유되는 구조체, 타입 정의, 상수, 전역 변수 및 함수 프로토 타입을 포함하는 헤더 파일. |



### 2.2 Application 에서 생성한 주요 함수

| 파일명        | 역할                                                         |
| ------------- | ------------------------------------------------------------ |
| main          | Application 의 시작 함수로,  명령행 옵션을 파싱 하여 요청 수, 스레드 수 를 설정 하고 필터와 통신 채널을 열고, 스레드를 생성 한다. 옵션 없이 실행 하면 요청 수는 5 이며 스레드 수는 2 이다. |
| ScanBuffer    | 입력으로 전달된 문자열 에 **FoulString** 이 포함되어 있는지 확인 한다. |
| ScannerWorker | 작업 스레드, 인자로 주어진 context 는 메시지를 보내거나 받는데 사용하는 포트 핸들에 대한 포인터와 이미 comm과 관련된 완료 포트 핸들을 가지고있다. |
| Usage         | Application 실행 방법은 표시 한다.  옵션 으로는 스레드당 요청 수(숫자),  스레드 숫자 (1~64) 로 지정 할 수 있다. |



### 2.3 Application 에서 사용한 주요 함수

| 파일명                         | 역할                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| FilterConnectCommunicationPort | 파일 시스템 미니 필터로 만든 통신 서버 포트에 대한 새 연결을 엽니 다. |
| CreateIoCompletionPort         | 입출력 (I/O) 완료 포트를 작성하여 지정된 파일 핸들과 연관 시키거나 파일 핸들과 아직 연관되지 않은 I/O 완료 포트를 작성하여 나중에 연관을 허용합니다. 열린 파일 핸들의 인스턴스를 I/O 완료 포트와 연관 시키면 프로세스가 해당 파일 핸들과 관련된 비동기 I/O 조작 완료 통지를 수신 할 수 있습니다. |
| CreateThread                   | 호출 프로세스의 가상 주소 공간 내에서 실행할 스레드를 생성합니다.  다른 프로세스의 가상 주소 공간에서 실행되는 스레드를 만들려면 CreateRemoteThread 함수를 사용하십시오. |
| **FilterGetMessage**           | 커널 모드 미니 필터에서 메시지를 가져옵니다. <br/>미니 필터에서 메시지를받은 후 호출자는 FilterReplyMessage를 호출하여 응답을 보낼 수 있습니다.<br/>**미니 필터 또는 인스턴스는 FltSendMessage를 호출하여 사용자 모드 응용 프로그램에 메시지를 보냅니다.** |
| WaitForMultipleObjectsEx       | 지정된 개체 중 하나 또는 모두가 신호를받은 상태가 될 때까지 기다리고 I/O 완료 루틴 또는 비동기 프로 시저 호출 (APC)이 스레드에 대기하거나 시간 제한 간격이 경과 할 때까지 대기합니다. |
| RtlEqualMemory                 | memcmp() 를 재 정의한 매크로임.                              |
| **GetQueuedCompletionStatus**  | Driver 에서 전송한 Data 를 Queue 에서 꺼냅니다.  데이터가 없으면 대기 합니다. 한 번에 여러 I/O 완료 패킷을 dequeue하려면 GetQueuedCompletionStatusEx 함수를 사용하십시오. |
| CONTAINING_RECORD              | 유형이 주어진 구조체의 기본 주소와 구조체 내의 필드 주소를 계산하여 주소를 전달 하는 매크로. |
| FilterReplyMessage             | 커널 모드 미니 필터에서 메시지에 응답합니다.  사용자 모드 응용 프로그램은 FilterReplyMessage 함수를 호출하여 커널 모드 미니 필터에서받은 메시지에 응답합니다.  미니 필터에서 메시지를 받으려면 FilterGetMessage를 호출하십시오.  미니 필터로 메시지를 보내려면 FilterSendMessage를 호출하십시오. 미니 필터는 FltSendMessage를 호출하여 사용자 모드 응용 프로그램에 메시지를 보냅니다. |



### 2.4 분석 내용 종합

<span style="color:green">**scanner, scanuser 는 파일 확장자 ".doc" 를 열 경우 파일의 header(파일의 앞쪽) 부분에 "foul" 문자열이 있는지 감사하는 파일시스템 미니필터 드라이버 예제 이다.  바이러스 scan program 은 확장자를 추가 하여 바이러스에 감염되어 있는지 확인 하는 program 을 작성 할 수 있다.**</span>

<span style="color:green">**FilterGetMessage 에서 요청된 데이터는 Queue 에 저장 되었다가 Thread(ScannerWorker) 의 GetQueuedCompletionStatus() 함수에 의해 분석 및 처리가 완료된다.**</span>



### 2.5 FilterGetMessage 함수

FilterGetMessage 함수는 커널 모드 미니 필터에서 메시지를 가져옵니다.

#### 2.5.1 Syntax

```c++
HRESULT FilterGetMessage(
  HANDLE                 hPort,
  PFILTER_MESSAGE_HEADER lpMessageBuffer,
  DWORD                  dwMessageBufferSize,
  LPOVERLAPPED           lpOverlapped
);
```

#### 2.5.2 Parameters

`hPort`

사전에 [FilterConnectCommunicationPort](https://msdn.microsoft.com/294783f2-2cbf-4eea-82ae-a396c62f911a) 를 호출하여 반환 된 **통신 포트 핸들**, 이 매개 변수는 필수이며 NULL 일 수 없습니다.



`lpMessageBuffer`

미니 필터에서 메시지를 수신하는 **발신자 할당 버퍼의 포인터**. 메시지에는 [FILTER_MESSAGE_HEADER](https://msdn.microsoft.com/294e5475-3aca-4758-87ed-07892a910b4f)구조체가 있어야하지만 그렇지 않으면 형식이 호출자 정의입니다. 이 매개 변수는 필수이며 NULL 일 수 없습니다.



`dwMessageBufferSize`

lpMessageBuffer 매개 변수가 가리키는 버퍼의 크기 (바이트)입니다.



`lpOverlapped`

OVERLAPPED 구조체의 포인터. 이 매개 변수는 선택적이며 NULL 일 수 있습니다. NULL이 아닌 경우 호출자는 OVERLAPPED 구조체의 hEvent 멤버를 유효한 이벤트 핸들 또는 NULL로 초기화해야합니다.



#### 2.5.3 Return Value

FilterGetMessage는 성공하면 S_OK를 반환합니다. 그렇지 않으면, 오류 값을 리턴합니다.



#### 2.5.4 Remarks

FilterGetMessage 함수는 동기 및 비동기 (겹쳐진) 작업을 위해 설계되었습니다.

lpOverlapped가 NULL이고 메시지를 사용할 수 있으면 FilterGetMessage는 즉시 반환합니다. 그렇지 않으면 발신자는 메시지를 수신 할 때까지 대기 상태가됩니다.

**lpOverlapped가 NULL이 아닌 경우 FilterGetMessage는 ERROR_IO_PENDING을 반환합니다.  이 상황에서 lpOverlapped 구조의 이벤트 개체(hEvent ?)는 FilterGetMessage가 반환되기 전에 비 신호 상태로 설정됩니다.  메시지가 전달되면 이 이벤트는 신호가있는 상태로 설정됩니다.**

미니 필터에서 메시지를받은 후 호출자(User Mode)는 FilterReplyMessage를 호출하여 응답을 보낼 수 있습니다.

미니 필터 또는 인스턴스(driver)는 FltSendMessage를 호출하여 사용자 모드 응용 프로그램에 메시지를 보냅니다.



#### 2.5.5 Requirements

| **Target Platform** | Universal                     |
| ------------------- | ----------------------------- |
| **Header**          | fltuser.h (include Fltuser.h) |
| **Library**         | FltLib.lib                    |
| **DLL**             | FltLib.dll                    |





### 2.6 소스 분석중 추가로 알게된 내용

#### 2.6.1 CmRegisterCallback routine

출처: <https://kskang.tistory.com/536?category=46158>

  기존 WINDOW 32비트 플랫폼에서의 regmon은 **KeServiceDescriptorTable**에 등록되어있는 NativeAPI 중 레지스트리 관련 API들을 Hooking하는 드라이버를 제작하여 regmon 을 구현 하였다. 

하지만 **64비트 플랫폼에서는 더이상 커널 후킹이 불가능**(Kernel Patch Protection때문)하고
기존의 KeServiceDescriptorTable과 같이 후킹하기 편한 형태로 SystemCall을 구현하고 있지 않기 때문에
더이상 기존의 방식을 사용할수가 없게 되었다. 

regmon for x64 버전을 살펴보았더니 MS에서 이미 이런 문제를 위해 XP이상 부터 동작 가능한 
Registry Callback 기능을 추가하였다. 

참고 URL :  <http://www.codeproject.com/Articles/17263/Moving-to-Windows-Vista-x64> 

```c++
NTSTATUS 
  RegistryCallback(
    IN PVOID  CallbackContext,
    IN PVOID  Argument1,
    IN PVOID  Argument2 
    );
```

위와 같은 함수형태를 가지고 있고 레지스트리 API 가 호출되기전에  callback을 받을수도있고
호출된후에 callback을 받을수도 있도록 되어있다. 

```c++
NTSTATUS
  CmRegisterCallback(
    IN PEX_CALLBACK_FUNCTION  Function,
    IN PVOID  Context,
    OUT PLARGE_INTEGER  Cookie 
    ); 
```



Callback 함수는 위와 같은 프로토타입을 가지고 있고 적절한 STATUS를 리턴할수있도록
되어 있어 레지스트리 억세스 컨트롤 기능도 그대로 구현할 수 있다.   



#### - 끝 -

