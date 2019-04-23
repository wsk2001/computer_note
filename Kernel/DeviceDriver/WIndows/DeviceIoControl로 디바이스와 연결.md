# DeviceIoControl로 디바이스와 연결

출처: <https://ezbeat.tistory.com/286>



이번에 해볼 내용은 유저어플에서 DeviceIoControl함수를 사용해 로드된 디바이스와 통신을 하는것입니다.

가장 간단하게 유저어플에서 해당 함수를 호출해 디바이스를 작동시켜보는 것만 해보겠습니다.

저도 간단하게 될줄 알고 시작한 내용이었는데 쫌 시간이 걸렸습니다.

======================================================================

일단 두개의 파일을 만들어야 합니다. 첫번째 **유저어플인 exe ** 두번째 **디바이스 드라이버 sys**



가장 먼저 **첫번째 유저어플**부터 보겠습니다. **코드**입니다.

```c
#include <stdio.h>  
#include <Windows.h>  
#include <conio.h>  
  
#define IOCTL_TEST  CTL_CODE(FILE_DEVICE_UNKNOWN,0x4000,METHOD_BUFFERED,FILE_ANY_ACCESS)  
  
int main(void)  
{  
    HANDLE dHandle;  
    WCHAR DeviceLink[] = L"\\\\.\\Ezbeat";  
    DWORD dwRet;  
  
    dHandle = CreateFileW(  
        DeviceLink,  
        GENERIC_READ|GENERIC_WRITE,  
        0,  
        NULL,  
        OPEN_EXISTING,  
        FILE_ATTRIBUTE_NORMAL,  
        NULL  
        );  
  
    if(dHandle == INVALID_HANDLE_VALUE)  
    {  
        printf("Get Device Handle Fail! : 0x%X \n",GetLastError());  
        getch();  
        return 1;  
    }  
  
    if(!DeviceIoControl(dHandle,IOCTL_TEST,0,0,0,0,&dwRet,0))  
    {  
        printf("DeviceIOControl Fail!! \n");  
        getch();  
        CloseHandle(dHandle);  
        return 1;  
    }  
  
    CloseHandle(dHandle);  
  
    return 0;  
}  
```

DeviceIoControl함수는 유저어플과 디바이스와 대화를 할수 있는 함수입니다. 즉, 양방향성을 가지는 함수이죠.

단방향성을 가지는 함수는 ReadFile, WriteFile이 되겠죠.

**함수 정의**입니다.

```c++
BOOL WINAPI DeviceIoControl(
  __in         HANDLE hDevice,
  __in         DWORD dwIoControlCode,
  __in_opt     LPVOID lpInBuffer,
  __in         DWORD nInBufferSize,
  __out_opt    LPVOID lpOutBuffer,
  __in         DWORD nOutBufferSize,
  __out_opt    LPDWORD lpBytesReturned,
  __inout_opt  LPOVERLAPPED lpOverlapped
);
```

첫번째 인자는 CreateFile로부터 얻어온 디바이스의 핸들입니다.

두번째 인자는 해당 디바이스에 전달할 I/O Control Code입니다.

위에서 define으로 정의해뒀죠.



I/O Control Code는 총 32bit로 이루어져있습니다. 그림을 보시겠습니다.

![](.\Images\166F93204CDD7E2804.jpeg)

DeviceType : I/O Control Code가 사용되는 디바이스 장치의 유형을 정의

Access : 해당하는 I/O Control Code와 같이 사용되는 버퍼의 방향을 정의

Function : 구체적인 수행 코드를 구분

Method : 사용되는 버퍼의 사용 전략



그리고 Function에는 0~4095의 수를 사용할수 있지만 MS에서 이미 0~2047까지는 예약해서 사용해버립니다.

그래서 사용자가 컨트롤 코드를 만들 땐 2048부터 사용하시면 됩니다. 전 0x4000으로 했습니다.

Method는 유저어플과 디바이스간 통신을 할 때 버퍼를 어떻게 할것인가를 정하는 부분인데 

METHOD_BUFFERED로 해주면 In/Out전부 System버퍼를 사용하게 됩니다.



더 자세한 내용은 윈도우 디바이스 드라이버 책을 참조바랍니다.



전 CTL_CODE 메크로를 사용해 각각의 값을 하나로 뭉쳐서 IOCTL_TEST라는 값으로 정의했습니다.

제가 디바이스에게 보낼 컨트롤 코드는 **IOCTL_TEST**입니다.



이제 DeviceIoControl함수가 호출되면 해당 디바이스를 포함하고 있는 드라이버에서는 해당 IRP를 처리할 것입니다.

```
간단히 과정을 말해보면 I/O관리자에서는 IRP를 적절한 디바이스 스택에 넘겨주며 또한 디바이스 스택에서 그 IRP를 처리하기 위한 IO스택을 만듭니다. 그 IO스택 포인터 위치나 크기 등등 이러한 정보는 IRP가 가지고 있습니다.
그리고 IO스택에 쌓이는 값은 IO_STACK_LOCATION이라는 24Byte구조체가 쌓이게 됩니다. 해당 구조체안에는 우리가 처리해야할 명령어도 들어있습니다. 이렇게 IO스택에 해당 구조체가 쌓이게되면 해당 드라이버는 IO_STACK_LOCATION에 들어있는 명령어를 처리하게 됩니다. 이렇게 처리하고 난 후 하위 드라이버가 있다면 IoCallDriver를 호출해서 IRP를 넘길 것입니다. 아니면 IoCompleteRequest를 사용해 I/O관리자에게 IRP모두 처리되었다고 알릴 것입니다.
```

이번에는 **디바이스 드라이버 코드**를 보겠습니다.

```c++
#include <ntddk.h>  
  
#define LINK_NAME   L"\\DosDevices\\Ezbeat"  
#define DEVICE_NAME L"\\Device\\test"  
#define IOCTL_TEST  CTL_CODE(FILE_DEVICE_UNKNOWN,0x4000,METHOD_BUFFERED,FILE_ANY_ACCESS)  
  
PDEVICE_OBJECT MyDevice;  
UNICODE_STRING DeviceLink;  
UNICODE_STRING DeviceName;  
  
NTSTATUS MyIOControl(IN PDEVICE_OBJECT DeviceObject, IN PIRP Irp)  
{  
    PIO_STACK_LOCATION pStack;  
    NTSTATUS returnStatus = STATUS_SUCCESS;  
    ULONG ControlCode;  
  
    pStack = IoGetCurrentIrpStackLocation(Irp);  
    ControlCode = pStack->Parameters.DeviceIoControl.IoControlCode;  
  
    switch(ControlCode)  
    {  
    case IOCTL_TEST:  
        DbgPrint("\n IOCTL_TEST Call~~ \n");  
        break;  
    }  
  
    Irp->IoStatus.Status = STATUS_SUCCESS;  
    IoCompleteRequest(Irp, IO_NO_INCREMENT);  
  
    return returnStatus;  
}  
  
NTSTATUS Create_Handler(IN PDEVICE_OBJECT DeviceObject, IN PIRP Irp)  
{  
    Irp->IoStatus.Status = STATUS_SUCCESS;  
    IoCompleteRequest(Irp, IO_NO_INCREMENT);  
  
    return STATUS_SUCCESS;  
}  
  
VOID OnUnload(IN PDRIVER_OBJECT DriverObject)  
{  
    IoDeleteDevice(MyDevice);  
    IoDeleteSymbolicLink(&DeviceLink);  
  
    //DbgPrint("OnUnload Call! \n");  
}  
  
NTSTATUS DriverEntry(IN PDRIVER_OBJECT DriverObject,IN PUNICODE_STRING RegistryPath)  
{  
    NTSTATUS returnStatus = STATUS_SUCCESS;  
      
    RtlInitUnicodeString(&DeviceLink,LINK_NAME);  
    RtlInitUnicodeString(&DeviceName,DEVICE_NAME);  
  
    returnStatus = IoCreateDevice(  
        DriverObject,  
        0,  
        &DeviceName,  
        FILE_DEVICE_UNKNOWN,  
        FILE_DEVICE_SECURE_OPEN,  
        FALSE,  
        &MyDevice  
        );  
    if(!NT_SUCCESS(returnStatus))  
    {  
        //DbgPrint("IoCreateDevice Fail! \n");  
        return returnStatus;  
    }  
    //DbgPrint("Success IoCreateDevice \n");  
  
    returnStatus = IoCreateSymbolicLink(&DeviceLink,&DeviceName);  
    if(!NT_SUCCESS(returnStatus))  
    {  
        //DbgPrint("IoCreateSymbolicLink Fail! \n");  
        return returnStatus;  
    }  
    //DbgPrint("Success IoCreateSymbolicLink \n");  
  
    DriverObject->DriverUnload = OnUnload;  
    DriverObject->MajorFunction[IRP_MJ_DEVICE_CONTROL] = MyIOControl;  
    DriverObject->MajorFunction[IRP_MJ_CREATE] = Create_Handler;  
  
    return returnStatus;  
}  
```

DriverEntry에서는 먼저 디바이스를 생성합니다.  - **IoCreateDevice**

생성한 디바이스에게 유저어플에서 해당 디바이스에게 접근할수 있도록 이름을 지정해줍니다. - **IoCreateSymbolicLink**

또 첫번째 인자는 user-visivle 이름이며, 두번째 인자는 실제 드라이버 생성한 디바이스의 이름입니다.



이름을 지정할 때 그냥 Ezbeat 이런식으로 하면 안되고 **"\DosDevices\Ezbeat"** 이렇게 해줘야

유저어플에서 접근할 때 **"\\.\Ezbeat"** 이렇게 접근할 수 있습니다.



그리고 이제 IRPDispatch루틴들을 등록합니다. 

먼저 Unload루틴 등록하고 

그 다음 DeviceIoControl함수가 IRP를 보냈을 때 그 IRP를 처리하는 MyIOControl 핸들러등록

마지막으로 IRP_MJ_CREATE는 왜 등록하는지 궁금하실 것입니다.



IRP_MJ_XXX는 0x1b개가 존재합니다. 그러면 우리는 코드를 작성할 때 모든 IRP_MJ_XXX를 처리해주어야할까요??

DriverObject->MajorFunction[IRP_MJ_DEVICE_CONTROL] = MyIOControl;

위와 같이 등록을 안한 IRP_MJ_XXX는 디폴트로 처리되는 핸들러가 있습니다.

![](.\Images\162F66224CDE139640.jpeg)



운영체제는 모든 드라이버의 PDRIVER_OBJECT->MajorFunction[] 배열이 위와 같은 함수를 가지도록 초기화합니다.

그렇기 때문에 PDRIVER_OBJECT->MajorFunction[] 배열 값을 변경하지 않은 부분에 대해서는 

I/O관리자에게 STATUS_INVALID_DEVICE_REQUEST를 전달하게 됩니다.

즉, 성공적으로 처리했다는 것은 아니란 말이죠.



**만약에 제가 위 코드에서 IRP_MJ_CREATE를 처리해주지 않았다면 어떠한 일이 생길까요?**

유저어플에서는 제 디바이스의 핸들을 얻기위해 CreateFile을 호출했습니다. 그러면 IO스택에는 IRP_MJ_CREATE가 존재할 것 입니다. 드라이버에서는 위 명령어를 처리하고 IRP를 I/O관리자에게 리턴할 때 STATUS_INVALID_DEVICE_REQUEST를 전달할 것입니다. 고로 CreateFile함수 호출은 성공적으로 이루어지질 못하게되죠.

**유저어플 CreateFile에서는 INVALID_HANDLE_VALUE이라는 값을 반환할 것**입니다.





그렇기 때문에 IRP_MJ_CREATE가 들어왔을 때도 성공적으로 처리했다는 값을 I/O관리자에게 반환을 해주어야 합니다. 이 문제 때문에 몇 시간은 소비한것 같군요. ( Sone형 감사해요 ^^ )



마지막으로 MyIOControl함수를 보겠습니다. 해당 함수에서는 IoGetCurrentIrpStackLocation를 사용해 먼저 IRP내부에 존재하는 IO스택 포인터 위치를 리턴 받습니다. IRP내부에 존재하는 CurrentStackLocation이 가리키는 값을 가져오게되죠.



그리고 반환된 IO_STACK_LOCATION구조체는 24Byte로 그 안에 유저어플에서 전달했던 컨트롤 코드가 존재하게 됩니다. pStack->Parameters.DeviceIoControl.IoControlCode 이제 switch문을 사용해 적절히 처리해주면 됩니다.



이것도 역시나 마지막에 I/O관리자에게 성공적으로 IRP를 처리했다고 반환을 해주어야겠지요.



**Unload루틴에서는 생성했던 디바이스와 심볼릭링크를 제거**해줍니다.

제거하지 않을 경우 다시 드라이버를 로드시킬 때 DriverEntry에 있는 IoCreateDevice,IoCreateSymbolicLink함수들이 성공을 못합니다. 꼭 제거를 해주어야합니다.



이제 어느정도 틀은 잡았으니 유저어플과 디바이스간 제대로 통신하게끔해서 멋진 툴을 하나 만들어보고싶네요.

