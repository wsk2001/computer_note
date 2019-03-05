# File System Filter Driver Tutorial

이 자습서에서는 간단한 파일 시스템 필터 드라이버를 개발하는 방법을 보여줍니다.

- [Download FsFilter.zip - 16.31 KB](./Download/fsfilter.zip)

**Note**

```
이 기사에서는 파일 시스템 필터 드라이버의 기본 사항에 대해 설명하고 읽을 것을 매우 환영합니다. 그러나 생산을 위해서는 minifilter 프레임 워크를 사용하는 것이 더 오류가 없고 모든 현재 Windows 시스템에서 사용할 수 있으므로 고려해야합니다.
```

## Contents

1. 소개
2. 간단한 파일 시스템 필터 드라이버 만들기
3. 드라이버 설치 방법
4. 샘플 실행하기
5. 개량 하기
6. 결론
7. 유용한 참고 문헌



## 소개

이 자습서에서는 간단한 파일 시스템 필터 드라이버를 개발하는 방법을 보여줍니다. 데모 드라이버는 오프닝 파일의 이름을 디버그 출력에 인쇄합니다.

이 기사에는 기본적인 Windows 드라이버 개발 및 C/C++ 지식이 필요합니다. 그러나 Windows 드라이버 개발 경험이없는 사람들에게도 흥미로울 수 있습니다.



### 파일 시스템 필터 드라이버 란 무엇입니까?

파일 시스템 필터 드라이버는 모든 파일 시스템 I/O 작업 (작성, 읽기, 쓰기, 이름 바꾸기 등)마다 호출되므로 파일 시스템 동작을 수정할 수 있습니다. 파일 시스템 필터 드라이버는 레거시 드라이버와 거의 비슷하지만 몇 가지 특별한 단계가 필요합니다. 이러한 드라이버는 안티 바이러스, 보안, 백업 및 스냅 샷 소프트웨어에서 사용됩니다.



## 간단한 파일 시스템 필터 드라이버 만들기

### 시작하기 전에

드라이버를 빌드하려면 WDK 또는 IFS Kit가 필요합니다. Microsoft 웹 사이트에서 다운로드 할 수 있습니다. 또한 환경 변수 %WINDDK%를 WDK/IFS Kit를 설치 한 경로로 설정해야합니다.

**Be careful:**  드라이버의 작은 오류라도 BSOD(**Blue Screen Of Death**) 또는 시스템이 불안정해질 수 있습니다.



### <span style="color:blue">Main.c</span>

#### <span style="color:green">Driver entry</span>

**이것은 모든 드라이버의 진입점** 입니다. 우선 우리가 할 일은 **DriverObject** 를 전역 변수에 저장하는 것입니다 (나중에 필요합니다).

```c
////////////////////////////////////////////////////////
// DriverObject 의 포인터를 저장 할 전역 변수 선언
PDRIVER_OBJECT   g_fsFilterDriverObject = NULL;

////////////////////////////////////////////////////////
// DriverEntry - 드라이버의 진입 점
NTSTATUS DriverEntry(
    __inout PDRIVER_OBJECT  DriverObject,
    __in    PUNICODE_STRING RegistryPath
    )
{    
    NTSTATUS status = STATUS_SUCCESS;
    ULONG    i      = 0;

    //ASSERT(FALSE); // 디버거를 실행 하지 않습니다.

    // 드라이버 객체를 전역 변수에 저장 합니다.
    g_fsFilterDriverObject = DriverObject;
    ...
}
```



#### <span style="color:green">Set IRP dispatch table</span>

다음 단계는 IRP 처리기에 대한 함수 포인터로 IRP 디스패치 테이블을 채우는 것입니다. 필터 드라이버에는 일반 패스 스루 IRP 처리기 (요청을 추가로 보냄)가 있습니다. 그리고 IRP_MJ_CREATE에 대한 핸들러를 사용하여 시작 파일의 이름을 검색해야합니다. IRP 핸들러의 구현은 나중에 설명 될 것이다.

```c
NTSTATUS DriverEntry(
    __inout PDRIVER_OBJECT  DriverObject,
    __in    PUNICODE_STRING RegistryPath
    )
{    
    ...
    // 드라이버 개체 디스패치 테이블을 초기화합니다.
    // 여기서 기본 기능을 대체할 함수를 등록 합니다.
    for (i = 0; i <= IRP_MJ_MAXIMUM_FUNCTION; ++i) 
    {
        DriverObject->MajorFunction[i] = FsFilterDispatchPassThrough;
    }

    DriverObject->MajorFunction[IRP_MJ_CREATE] = FsFilterDispatchCreate;
    ...
}
```



#### <span style="color:green">Set Fast-IO dispatch table</span>

**파일 시스템 필터 드라이버에는 고속 I/O 디스패치 테이블이 있어야합니다.** 고속 I/O 디스패치 테이블을 설정하는 것을 잊어 버린 경우 시스템 충돌이 발생할 수 있습니다. Fast-I/O는 I/O 작업을 시작하는 또 다른 방법이며 IRP보다 빠릅니다. Fast-IO 작업은 항상 동기식입니다. Fast-IO 처리기가 FALSE를 반환하면 Fast-IO 방식이 불가능하고 IRP가 만들어 짐을 의미합니다.

```c
// 고속 I/O 디스패치 테이블 설정
FAST_IO_DISPATCH g_fastIoDispatch =
{
    sizeof(FAST_IO_DISPATCH),
    FsFilterFastIoCheckIfPossible,
    ...
};
DriverEntry - Entry point of the driver

NTSTATUS DriverEntry(
    __inout PDRIVER_OBJECT  DriverObject,
    __in    PUNICODE_STRING RegistryPath
    )
{    
    ...
    // 고속 I/O 디스패치 테이블 설정
    DriverObject->FastIoDispatch = &g_fastIoDispatch;
    ...
}
```



#### <span style="color:green">Register a notification for file system changes</span>

파일 시스템 필터 드라이버의 연결/분리를 수행하기 위해 활성화/비활성화되는 파일 시스템을 추적해야합니다. 파일 시스템 변경 사항을 추적하는 방법은 다음과 같습니다.

```c
//////////////////////////////////////////////////////////////////////////
// DriverEntry - Entry point of the driver

NTSTATUS DriverEntry(
    __inout PDRIVER_OBJECT  DriverObject,
    __in    PUNICODE_STRING RegistryPath
    )
{    
    ...
    //  파일 시스템 변경에 대한 등록 된 콜백 루틴.
    status = IoRegisterFsRegistrationChange( DriverObject, 
                       						 FsFilterNotificationCallback ); 
    if (!NT_SUCCESS(status)) 
    {
        return status;
    }
    ...
}
```



#### <span style="color:green">Set driver unload routine</span>

드라이버 초기화의 마지막 부분은 언로드 루틴을 설정합니다. 드라이버 언로드 루틴을 설정하면 드라이버를 언로드 할 수 있으며 시스템 재시작없이 여러번 로드/언로드 할 수 있습니다. 그러나 파일 시스템 필터를 안전하게 언로드 할 수 없으므로 이 드라이버는 디버깅 목적으로 만 언로드 할 수 있습니다.  <span style="color:red">**Never do this in production code.**</span>

```c
//////////////////////////////////////////////////////////////////////////
// DriverEntry - Entry point of the driver

NTSTATUS DriverEntry(
    __inout PDRIVER_OBJECT  DriverObject,
    __in    PUNICODE_STRING RegistryPath
    )
{    
    ...
    // 드라이버 언로드 루틴을 설정합니다 (디버그 목적 만).
    DriverObject->DriverUnload = FsFilterUnload;

    return STATUS_SUCCESS;
}
```



#### <span style="color:green">Driver unload implementation</span>

드라이버 언로드 루틴은 리소스 정리 및 할당 해제를 담당합니다. 우선, 파일 시스템 변경에 대한 알림을 등록 해제하십시오.

```c
//////////////////////////////////////////////////////////////////////////
// Unload routine

VOID FsFilterUnload(
    __in PDRIVER_OBJECT DriverObject
    )
{
    ...

    //  파일 시스템 변경을 위한 언로드 콜백 루틴.
    IoUnregisterFsRegistrationChange(DriverObject, FsFilterNotificationCallback);

    ...
}
```

그런 다음 생성 된 장치를 반복하여 분리하고 삭제하십시오. 해결되지 않은 모든 IRP가 완료 될 때까지 5 초 동안 기다리십시오. <span style="color:red">이전에 언급했듯이, 이것은 디버그 전용 솔루션입니다. 대부분의 경우 작동 하지만 모두를 보장 할 수는 없습니다.</span>

```c
//////////////////////////////////////////////////////////////////////////
// Unload routine

VOID FsFilterUnload(
    __in PDRIVER_OBJECT DriverObject
    )
{
    ...

    for (;;)
    {
        IoEnumerateDeviceObjectList(
            DriverObject,
            devList,
            sizeof(devList),
            &numDevices);

        if (0 == numDevices)
        {
            break;
        }

        numDevices = min(numDevices, RTL_NUMBER_OF(devList));

        for (i = 0; i < numDevices; ++i) 
        {
            FsFilterDetachFromDevice(devList[i]);
            ObDereferenceObject(devList[i]);
        }
        
        KeDelayExecutionThread(KernelMode, FALSE, &interval);
    }
}
```



### <span style="color:blue">IrpDispatch.c</span>

#### <span style="color:green">Dispatch pass-through</span>

**이 IRP 처리기**는 **요청을 다음 드라이버로 전달** 하는 것을 제외하고는 아무 것도하지 않습니다. 우리는 장치 확장에 다음 드라이버 개체를 저장했습니다.

```c
/////////////////////////////////////////////////////////////////
// PassThrough IRP Handler

NTSTATUS FsFilterDispatchPassThrough(
    __in PDEVICE_OBJECT DeviceObject, 
    __in PIRP           Irp
    )
{
    PFSFILTER_DEVICE_EXTENSION pDevExt = 
      (PFSFILTER_DEVICE_EXTENSION)DeviceObject->DeviceExtension;

    IoSkipCurrentIrpStackLocation(Irp);
    return IoCallDriver(pDevExt->AttachedToDeviceObject, Irp);
}
```

#### <span style="color:green">Dispatch create</span>

이 IRP 처리기는 모든 파일 생성 작업에서 호출됩니다. PFILE_OBJECT에서 파일 이름을 가져 와서 디버그 출력으로 출력합니다. 그런 다음 위에서 설명한 통과 처리기를 호출합니다. 파일 생성 작업이 수행되는 동안에 만 유효한 파일 이름이 PFILE_OBJECT에 존재한다는 사실에주의하십시오! 또한 상대 개방이 있으며 ID로 열립니다. 이 경우 파일 이름을 가져 오는 것은이 기사의 범위를 벗어납니다.

```c
//////////////////////////////////////////////////////////////////////////////
// IRP_MJ_CREATE IRP Handler

NTSTATUS FsFilterDispatchCreate(
    __in PDEVICE_OBJECT DeviceObject,
    __in PIRP           Irp
    )
{
    PFILE_OBJECT pFileObject = IoGetCurrentIrpStackLocation(Irp)->FileObject;

    DbgPrint("%wZ\n", &pFileObject->FileName);

    return FsFilterDispatchPassThrough(DeviceObject, Irp);
}
```



### <span style="color:blue">FastIo.c</span>

다음 드라이버에 대한 Fast-IO 디스패치 테이블 유효성을 테스트합니다. 다음과 같은 도우미 매크로를 사용합니다 (모든 Fast-IO 루틴이 기본 파일 시스템에 의해 구현되어 있어야하므로이를 반드시 확인해야합니다).

```c
//  Macro to test if FAST_IO_DISPATCH handling routine is valid
#define VALID_FAST_IO_DISPATCH_HANDLER(_FastIoDispatchPtr, _FieldName) \
    (((_FastIoDispatchPtr) != NULL) && \
    (((_FastIoDispatchPtr)->SizeOfFastIoDispatch) >= \
    (FIELD_OFFSET(FAST_IO_DISPATCH, _FieldName) + sizeof(void *))) && \
    ((_FastIoDispatchPtr)->_FieldName != NULL))
```



#### <span style="color:green">Fast-IO pass-through</span>

Fast-IO 요청을 통과하려면 각 Fast-IO 함수에 고유 한 매개 변수 집합이 있으므로 (IRP 요청을 통과하는 것과 달리) 많은 코드를 작성해야합니다. 일반적인 패스 스루 기능은 다음과 같습니다.

```c
BOOLEAN FsFilterFastIoQueryBasicInfo(
    __in PFILE_OBJECT       FileObject,
    __in BOOLEAN            Wait,
    __out PFILE_BASIC_INFORMATION Buffer,
    __out PIO_STATUS_BLOCK  IoStatus,
    __in PDEVICE_OBJECT     DeviceObject
    )
{
    //
    //  Pass through logic for this type of Fast I/O
    //

    PDEVICE_OBJECT    nextDeviceObject = 
      ((PFSFILTER_DEVICE_EXTENSION)DeviceObject->DeviceExtension)->AttachedToDeviceObject;
    PFAST_IO_DISPATCH fastIoDispatch   = nextDeviceObject->DriverObject->FastIoDispatch;

    if (VALID_FAST_IO_DISPATCH_HANDLER(fastIoDispatch, FastIoQueryBasicInfo)) 
    {

        return (fastIoDispatch->FastIoQueryBasicInfo)(
            FileObject,
            Wait,
            Buffer,
            IoStatus,
            nextDeviceObject);
    }
    
    return FALSE;
}
```

#### <span style="color:green">Fast-IO detach device</span>

이것은 우리가 스스로 처리해야하고 다음 드라이버를 호출하지 않아야하는 특별한 Fast-IO 요청입니다. 파일 시스템 장치 스택에서 필터 장치를 분리하고 장치를 삭제해야합니다. 이는 다음 코드를 통해 쉽게 수행 할 수 있습니다.

```c
VOID FsFilterFastIoDetachDevice(
    __in PDEVICE_OBJECT     SourceDevice,
    __in PDEVICE_OBJECT     TargetDevice
    )
{
    //  파일 시스템의 볼륨 장치 오브젝트에서 분리합니다.
    IoDetachDevice(TargetDevice);
    IoDeleteDevice(SourceDevice);
}
```



### <span style="color:blue">Notification.c</span>

<span style="color:red">**일반적인 파일 시스템은 제어 장치와 볼륨 장치로 구성**</span>됩니다. 볼륨 장치가 저장 장치 스택에 연결됩니다. 제어 장치는 파일 시스템으로 등록됩니다.

![](.\Images\01-fsDevices.png)

**그림 1 - 일반적인 파일 시스템의 장치**

우리는 모든 활성 파일 시스템에 대해 호출되는 콜백과 파일 시스템이 활성 상태로 등록되거나 등록 해제 될 때마다 호출합니다. 필터 장치를 부착하거나 분리 할 수있는 좋은 장소입니다. 파일 시스템이 활성화 될 때 우리는 제어 장치에 연결하고 (아직 연결되어 있지 않은 경우에만 해당), 볼륨 장치를 열거하고 연결합니다. 파일 시스템 비활성화시 파일 시스템 제어 장치 스택을 검사하고 장치를 찾고 분리합니다. 파일 시스템 볼륨 장치에서 분리는 앞에서 설명한 FsFilterFastIoDetachDevice 루틴에서 수행됩니다.

```c
////////////////////////////////////////////////////////////////////////////
// 이 루틴은 파일 시스템이 활성 파일 시스템으로 등록/해제 될 때마다 호출됩니다.

VOID FsFilterNotificationCallback(
    __in PDEVICE_OBJECT DeviceObject,
    __in BOOLEAN        FsActive
    )
{
    //
    //  주어진 파일 시스템에 착탈하기.
    //

    if (FsActive)
    {
        FsFilterAttachToFileSystemDevice(DeviceObject);
    }
    else
    {
        FsFilterDetachFromFileSystemDevice(DeviceObject);
    }
}
```



### <span style="color:blue">AttachDetach.c</span>

이 파일에는 필터 드라이버를 분리 및 부착 하기 위한 도우미 루틴이 들어 있습니다, 그리고 이미 필터가 부착 되어 있는지 확인 하는 도우미 루틴이 있습니다.

#### <span style="color:green">Attaching</span>

연결을 수행하기 위해 장치 확장 (call IoCreateDevice)과 첨부 할 장치 개체 (DO_BUFFERED_IO, DO_DIRECT_IO, FILE_DEVICE_SECURE_OPEN)에서 장치 개체 전파 플래그를 사용하여 새 장치 개체를 만듭니다. 그런 다음 실패시 지연이있는 루프에서 IoAttachDeviceToDeviceStackSafe를 호출합니다. 장치 개체가 초기화를 완료하지 않았기 때문에이 첨부 요청이 실패 할 수 있습니다. 이 상황은 볼륨으로만로드 된 필터를 탑재하려고 시도 할 때 발생할 수 있습니다. 부착이 끝나면 '첨부 된'장치 개체를 장치 확장에 저장하고 DO_DEVICE_INITIALIZING 플래그를 지 웁니다. 장치 확장명은 다음과 같습니다.

```c
//////////////////////////////////////////////////////////////////////////
// Structures

typedef struct _FSFILTER_DEVICE_EXTENSION
{
    PDEVICE_OBJECT AttachedToDeviceObject;
} FSFILTER_DEVICE_EXTENSION, *PFSFILTER_DEVICE_EXTENSION;
```

#### <span style="color:green">Detaching</span>

분리는 아주 간단합니다. 장치 확장에서 'attach to'장치 개체를 가져 와서 IoDetachDevice 및 IoDeleteDevice를 호출합니다.

```c
void FsFilterDetachFromDevice(
    __in PDEVICE_OBJECT DeviceObject
    )
{    
    PFSFILTER_DEVICE_EXTENSION pDevExt = 
      (PFSFILTER_DEVICE_EXTENSION)DeviceObject->DeviceExtension;
    
    IoDetachDevice(pDevExt->AttachedToDeviceObject);
    IoDeleteDevice(DeviceObject);
}
```

#### <span style="color:green">기기가 연결되어 있는지 확인 하기.</span>

우리가 장치에 연결되어 있는지 확인하려면 장치 스택을 반복 (IoGetAttachedDeviceReference 및 IoGetLowerDeviceObject 사용)하고 장치를 검색해야합니다. 장치 드라이버 개체와 드라이버 개체 (g_fsFilterDriverObject)를 비교하여 장치를 구별합니다.

```c
//////////////////////////////////////////////////////////////////////////
// Misc

BOOLEAN FsFilterIsMyDeviceObject(
    __in PDEVICE_OBJECT DeviceObject
    )
{
    return DeviceObject->DriverObject == g_fsFilterDriverObject;
}
```



### <span style="color:blue">Sources and makefile</span>

소스 및 메이크 파일 파일은 빌드 유틸리티가 드라이버를 빌드하는 데 사용됩니다. 여기에는 프로젝트 설정과 소스 파일 이름이 포함됩니다.

Sources file contents:

```
TARGETNAME  = FsFilter
TARGETPATH  = obj
TARGETTYPE  = DRIVER
DRIVERTYPE  = FS

SOURCES     = \
    Main.c \
    IrpDispatch.c \
    AttachDetach.c \
    Notification.c \
    FastIo.c
```

makefile은 표준입니다.

```
!include $(NTMAKEENV)\makefile.def
```

MSVC makefile 프로젝트 빌드 명령 줄은 다음과 같습니다.

```
call $(WINDDK)\bin\setenv.bat $(WINDDK) chk wxp
cd /d $(ProjectDir)
build.exe –I
```





## 드라이버 설치 방법

### <span style="color:green">SC.EXE overview</span>

sc.exe (sc - service control)를 사용하여 드라이버를 관리합니다. 설치된 서비스의 데이터베이스를 쿼리하거나 수정하는 데 사용할 수있는 명령 줄 유틸리티입니다. Windows XP 이상 버전과 함께 제공되거나 Windows SDK/DDK에서 찾을 수 있습니다.



### <span style="color:green">Start</span>

드라이버를 시작하려면 다음을 호출하십시오.

```
sc start FsFilter
```

그러면 FsFilter라는 서비스가 시작됩니다.



### <span style="color:green">Stop</span>

드라이버를 중지하려면 다음을 호출 하십시요.

```
sc stop FsFilter
```

그러면 FsFilter라는 서비스가 중지됩니다.



### <span style="color:green">Uninstall</span>

제거하려면 다음을 호출하십시오.

```
sc delete FsFilter
```

서비스 관리자가 FsFilter라는 이름의 서비스 항목을 삭제하도록 지시합니다.



### <span style="color:green">Resulting script</span>

이러한 모든 명령은 드라이버 테스트를 쉽게하기 위해 단일 배치 파일에 저장됩니다. 다음은 Install.cmd 명령 파일의 목록입니다.

```
sc create FsFilter type= filesys binPath= c:\FsFilter.sys
sc start FsFilter
pause
sc stop FsFilter
sc delete FsFilter
pause
```



## 샘플 실행하기

이것은 가장 흥미로운 부분입니다. 파일 시스템 필터 작업을 설명하기 위해 Windows 용 Sysinternals DebugView를 사용하여 디버그 출력을 모니터링하고 OSR Device Tree를 사용하여 장치 및 드라이버를 확인합니다.

그래서 드라이버를 만드십시오. 그런 다음 빌드 출력 파일 FsFilter.sys를 복사하고 Install.cmd 스크립트를 디스크 C의 루트에 설치합니다.

![](.\Images\02-diskC.PNG)

그림 2 - 디스크의 드라이버 및 설치 스크립트.

Install.cmd를 실행하십시오. 드라이버를 설치하고 시작한 다음 사용자 입력을 기다립니다.

![](.\Images\03-install.PNG)

그림 3 - 드라이버가 성공적으로 설치 및 시작되었습니다.



이제 DebugView 유틸리티를 시작하십시오.

![](.\Images\04-debugView.PNG)

그림 4 - 디버그 출력 모니터링.

어떤 파일이 열리고 있는지 확인할 수 있습니다! 필터가 작동합니다. 이제 장치 트리 유틸리티를 실행하고 드라이버를 찾으십시오.

![](.\Images\05-deviceTree01_small.png)

그림 5 - 장치 트리에있는 필터 드라이버

드라이버가 만든 수많은 장치를 볼 수 있습니다. 이제 NTFS 드라이버를 열고 장치 트리를 살펴 보겠습니다.

![](.\Images\06-deviceTree02.PNG)

그림 6 - 필터가 NTFS에 연결되었습니다.

우리는 첨부되어 있습니다. 다른 파일 시스템을 살펴 보겠습니다.

![](.\Images\07-deviceTree03_small.png)

그림 7 - 우리 필터는 다른 파일 시스템에도 연결됩니다.

마지막으로, 설치 스크립트를 앞으로 이동하려면 아무 키나 누르십시오. 드라이버를 중지하고 제거합니다.

![](.\Images\08-uninstall.PNG)

그림 8 - 드라이버가 중지되고 제거됩니다.

F5 키를 눌러 장치 트리 목록을 새로 고칩니다.

![](.\Images\09-deviceTree04_small.png)

그림 9 - 필터 장치가 장치 트리에 없습니다.

필터가 없어졌습니다. 이전과 같이 시스템이 실행 중입니다.



## 개량 하기

샘플 드라이버에는 새로 도착한 볼륨에 연결하기 위해 일반적으로 요구되는 기능이 없습니다. 가능한 한 운전자가 쉽게 이해할 수 있도록하기 위해 수행됩니다. 새로 도착한 볼륨을 추적하기 위해 자신의 IRP_MJ_FILE_SYSTEM_CONTROL 처리기를 작성할 수 있습니다.



## 결론

이 튜토리얼에서는 간단한 파일 시스템 필터 드라이버를 작성하는 방법과 명령 행에서 설치, 시작, 중지 및 설치 제거하는 방법을 보여줍니다. 또한 일부 파일 시스템 필터 드라이버 측면에 대해서도 설명했습니다. 첨부 된 필터를 사용하여 파일 시스템 장치 스택을보고 드라이버에서 디버그 출력을 모니터링하는 방법을 배웠습니다. 제공된 소스를 자신의 파일 시스템 필터 드라이버의 골격으로 사용하거나 동작을 수정할 수 있습니다.



## 유용한 참고 문헌

1. [파일 시스템 필터 드라이버](http://msdn.microsoft.com/en-us/library/ms793575.aspx)
2. [파일 시스템 또는 파일 시스템 필터 개발자 용 컨텐츠](http://www.osronline.com/section.cfm?section=20)
3. [Windows NT 파일 시스템 내부 (OSR Classic Reprints) (단행본)](http://www.amazon.com/Windows-System-Internals-Classic-Reprints/dp/0976717514)
4. sfilter DDK sample

[Apriorit Development Blog](https://www.apriorit.com/apriorit-technology-blogs)에서 드라이버 개발 팁을 더 읽으십시오.

Apriorit 웹 사이트에서 [Windows 필터 드라이버 예제](https://www.apriorit.com/dev-blog/167-file-system-filter-driver)를 살펴보십시오.



출처: https://www.codeproject.com/Articles/43586/File-System-Filter-Driver-Tutorial

