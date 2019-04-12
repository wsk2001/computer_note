# MS-DOS 장치 이름 소개

비 WDM 드라이버에서 만든 명명 된 장치 개체에는 일반적으로 MS-DOS 장치 이름이 있습니다. MS-DOS 장치 이름은 \\DosDevices\\DosDeviceName 형식의 이름을 가진 개체 관리자의 심볼릭 링크입니다.

MS-DOS 장치 이름이있는 장치의 예는 직렬 포트, COM1입니다. MS-DOS 장치 이름은 \\DosDevices\\ COM1입니다. 마찬가지로 C 드라이브의 이름은 \\DosDevices\\C:입니다.

WDM 드라이버는 일반적으로 장치에 MS-DOS 장치 이름을 제공하지 않습니다. 대신 WDM 드라이버는 IoRegisterDeviceInterface 루틴을 사용하여 장치 인터페이스를 등록합니다. 장치 인터페이스는 특정 명명 규칙이 아닌 장치의 기능에 따라 장치를 지정합니다. 자세한 내용은 장치 인터페이스 클래스를 참조하십시오.

드라이버는 사용자 모드 프로그램에서 작동하도록 잘 알려진 MS-DOS 장치 이름이 장치에 필요한 경우에만 MS-DOS 장치 이름을 제공해야합니다.

드라이버는 IoCreateSymbolicLink 루틴을 **사용하여** 장치에 대한 심볼릭 링크를 생성하여 장치 개체의 MS-DOS 장치 이름을 제공합니다. 예를 들어, 다음 코드 예제에서는 **\\DosDevices**\\DosDeviceName 에서 **\\Device**\DeviceName으로 심볼릭 링크를 만듭니다.

```c++
UNICODE_STRING DeviceName;
UNICODE_STRING DosDeviceName;
NTSTATUS status;

RtlInitUnicodeString(&DeviceName, L"\\Device\\DeviceName");
RtlInitUnicodeString(&DosDeviceName, L"\\DosDevices\\DosDeviceName");
status = IoCreateSymbolicLink(&DosDeviceName, &DeviceName);
if (!NT_SUCCESS(status)) {
  /* Symbolic link creation failed.  Handle error appropriately. */
}
```

시스템은 **\\DosDevices** 디렉토리의 여러 버전을 지원합니다. 드라이버가 의도 한 버전의 심볼릭 링크를 생성했는지 확인하십시오. 자세한 내용은 [로컬 및 글로벌 MS-DOS 장치](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/kernel/local-and-global-ms-dos-device-names) 이름을 참조하십시오.

사용자 모드에서 DosDevices 네임 스페이스에 액세스하려면 파일 이름을 열 때 \\\\.\\를 지정하십시오. CreateFile ()을 호출하여 사용자 모드에서 해당 장치를 열 수 있습니다.

예를 들어, 다음 코드 예제는 \\\\DosDevices\\\\DosDeviceName 장치를 사용자 모드로 엽니다.

```c++
file = CreateFileW(L"\\\\.\\DosDeviceName",
  GENERIC READ | GENERIC WRITE,
    0,
    NULL,
    OPEN_EXISTING,
    0,
    NULL);
```

심볼릭 링크는 사용자 모드 DefineDosDevice 루틴을 사용하여 사용자 모드 응용 프로그램에서 만들 수도 있습니다. 자세한 내용은 Microsoft Windows SDK를 참조하십시오.