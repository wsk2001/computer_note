# SCSI Miniport Driver

이 섹션에는 다음 정보가 포함되어 있습니다.

[SCSI 미니 포트 드라이버에서 플러그 앤 플레이 지원](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/supporting-plug-and-play-in-a-scsi-miniport-driver)

[플러그 앤 플레이 SCSI 미니 포트 드라이버의 레지스트리 항목](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/registry-entries-for-plug-and-play-scsi-miniport-drivers)

[부팅 드라이브를 관리하는 SCSI 미니 포트 드라이버의 제한 사항](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/restrictions-on-scsi-miniport-drivers-that-manage-the-boot-drive)

[SCSI 미니 포트 드라이버에서 오류 처리](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/error-handling-in-scsi-miniport-drivers)

[필수 및 선택적 SCSI 미니 포트 드라이버 루틴](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/required-and-optional-scsi-miniport-driver-routines)



NT 기반 운영 체제 용 SCSI 미니 포트 드라이버는 HBA에 따라 다르지만 운영 체제에 독립적입니다. 즉, 각 미니 포트 드라이버는 동적 연결 라이브러리 (DLL) 인 시스템 제공 SCSI 포트 드라이버와 연결되며 포트 드라이버의 ScsiPortXxx 루틴 만 호출하여 시스템과 해당 HBA와 통신합니다. 이러한 SCSI 미니 포트 드라이버는 Microsoft Win32 응용 프로그램을 지원하는 다른 Microsoft 운영 체제에서 실행되며 ScsiPortXxx 루틴도 내 보냅니다. ScsiPortXxx 루틴에 대한 자세한 내용은 [SCSI 포트 라이브러리 루틴](https://msdn.microsoft.com/library/windows/hardware/ff565375)을 참조하십시오.

ScsiPortXxx 이외의 루틴을 호출하는 SCSI 미니 포트 드라이버는 두 Microsoft 운영 체제 환경에서 실행할 수 없습니다. NT 기반 운영 체제를 포함하여 Microsoft Windows 시스템에서 이식성을 유지하려면 SCSI 미니 포트 드라이버가 시스템에서 제공하는 ScsiPortXxx 만 호출해야합니다.

SCSI 미니 포트 드라이버는 **플러그 앤 플레이 드라이버**이거나 <span style="color:green">리소스 재배포 또는 전원 관리와 같은 플러그 앤 플레이 작업에 참여하지 않는 **레거시 드라이버**</span>로 실행할 수 있습니다. 플러그 앤 플레이와 레거시 미니 포트 드라이버의 주된 차이점은 초기화 루틴이 호출되는 순서와 Microsoft Windows NT 4.0의 미니 포트 드라이버에 적용된 특정 제한이 적용되거나 적용되지 않는 것입니다.



## SCSI 미니 포트 드라이버에서 플러그 앤 플레이 지원

Microsoft Windows 2000 이상의 운영 체제는 플러그 앤 플레이 운영 체제이지만 기본적으로 SCSI 미니 포트 드라이버는 레거시 드라이버로 실행됩니다. 레거시 미니 포트 드라이버의 HBA는 실행중인 시스템에서 제거 할 수 없으며 실행중인 시스템에 추가 할 때 레거시 미니 포트 드라이버가 자동으로 검색되지 않습니다. 이러한 제한 사항은 특정 HBA에서 허용 될 수 있지만 랩톱의 PC 카드 / CardBus HBA 및 HBA 용 SCSI 미니 포트 드라이버는 플러그 앤 플레이를 지원해야합니다.

플러그 앤 플레이 미니 포트 드라이버는 HBA 전원을 중지하고 관리하기 위해 **HwScsiAdapterControl** 루틴을 구현해야합니다. 드라이버 초기화의 변경 사항을 수용하기 위해 플러그 앤 플레이 미니 포트 드라이버에 추가 루틴이 필요하지 않습니다.

SCSI 포트 드라이버는 대상 장치에 대한 **PDO**와 미니 포트 드라이버에 대한 **FDO**를 만들고 미니 포트 드라이버를 대신하여 장치를 추가, 시작 또는 언로드하라는 요청을 처리합니다. 플러그 앤 플레이 드라이버에 대한 일반적인 내용은 [플러그 앤 플레이](https://msdn.microsoft.com/library/windows/hardware/ff547125)를 참조하십시오.




## 플러그 앤 플레이 SCSI 미니 포트 드라이버의 레지스트리 항목

플러그 앤 플레이를 지원하려면 SCSI 미니 포트 드라이버가 다음을 수행해야합니다.

- HBA 용 서비스로 설치됩니다.
- 미니 포트 드라이버가 플러그 앤 플레이를 지원하는 인터페이스를 나타내는 레지스트리에 PnPInterface 항목이 있어야합니다.

SCSI HBA 용 서비스로 미니 포트 드라이버를 설치하는 것은 일반적으로 주어진 HBA의 플러그 앤 플레이 하드웨어 ID와 일치하는 설치 정보 (INF) 파일을 올바른 드라이버에 제공하여 해당 장치를 제어함으로써 수행됩니다. INF 파일 설정에 대한 자세한 내용은 [플러그 앤 플레이](https://msdn.microsoft.com/library/windows/hardware/ff547125)를 참조하십시오.

미니 포트 드라이버가 HBA 용 서비스로 설치되어 있지 않으면 PnPInterface 레지스트리 항목으로 인해 미니 포트 드라이버가 초기화되지 않습니다. 지정된 인터페이스는 플러그 앤 플레이가 적절한 HBA를 찾을 때만 초기화됩니다. HBA에 서비스가 올바르게 할당되어 있지 않으면 플러그 앤 플레이가 장치를 감지 할 때 알릴 드라이버를 절대로 결정하지 않습니다. 이 동작은 의도적으로 설계된 동작이므로 미니 포트 드라이버는이를 막아서는 안됩니다.

**PnPInterface** 레지스트리 항목은 미니 포트 드라이버의 **Services** 키 아래에 만들어야합니다. 예를 들어 다음 레지스트리 항목은 Twiddle이라는 가상의 미니 포트 드라이버에 대해 플러그 앤 플레이를 사용합니다.

```c++
HKEY_LOCAL_MACHINE\System\CurrentControlSet\Services
    \Twiddle
        \Parameters
            \PnpInterface
                5 : REG_DWORD : 1
                1 : REG_DWORD : 1
                2 : REG_DWORD : 1
                8 : REG_DWORD : 1
```

REG_DWORD 앞에 오는 값은 miniport.h에 선언 된 INTERFACE_TYPE 열거 형에 해당합니다. 이 예제의 값은 Twiddle 미니 포트 드라이버가 **PCIBus** (5), **Isa** (1), **Eisa** (2) 및 **PCMCIABus** (8) 인터페이스에 대해 플러그 앤 플레이를 지원함을 나타냅니다. REG_DWORD 다음 값은 인터페이스에 대한 플러그 앤 플레이 지원을 나타내는 플래그입니다. (현재이 플래그는 값이 될 수 있지만 반드시 있어야합니다. 미래에는 플래그가 선택적 일 수 있습니다.)

**PnPInterface** 값을 레지스트리에 설정하고 시스템을 다시 시작하면 미니 포트 드라이버를 플러그 앤 플레이 드라이버로 초기화 할 수 있습니다. 초기화하는 동안 SCSI 포트 드라이버는 레지스트리를 검사하여 미니 포트 드라이버가 플러그 앤 플레이 또는 레거시 드라이버로 실행되어야하는지 여부를 확인합니다. SCSI 포트 드라이버는 미니 포트 드라이버가 지원하는 각 인터페이스 유형 (예 : PCI 및 ISA)에 대해 레지스트리를 확인합니다. 주로 다중 인터페이스 미니 포트 작성기의 디버깅을 단순화하기위한 것입니다. 미니 포트 드라이버 작성자는 미니 포트 드라이버가 드라이버가 지원하는 모든 인터페이스에 대해 플러그 앤 플레이 드라이버로 실행될 수 있는지 확인해야합니다.



## 부팅 드라이브를 관리하는 SCSI 미니 포트 드라이버의 제한 사항

[부트 드라이브를 관리하는 미니 포트 드라이버의 제한 사항을 참조하십시오.](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/restrictions-on-miniport-drivers-that-manage-the-boot-drive)





## SCSI 미니 포트 드라이버에서 오류 처리

모든 SCSI 미니 포트 드라이버는 다음 종류의 SCSI 오류에 대해 시스템 포트 드라이버에 알려야합니다. 오류가 발생할 때 드라이버가 SRB를 완료하기 전에 SrbStatus 멤버에서 이러한 오류를 설정해야합니다.

- RB_STATUS_ERROR (if the HBA returns a nonspecific bus error)
- SRB_STATUS_PARITY_ERROR
- SRB_STATUS_UNEXPECTED_BUS_FREE
- SRB_STATUS_SELECTION_TIMEOUT
- SRB_STATUS_COMMAND_TIMEOUT
- SRB_STATUS_MESSAGE_REJECTED
- SRB_STATUS_NO_DEVICE
- SRB_STATUS_NO_HBA
- SRB_STATUS_DATA_OVERRUN (also returned for underruns)
- SRB_STATUS_PHASE_SEQUENCE_FAILURE
- SRB_STATUS_BUSY (TID busy)

데이터 언더런의 경우 미니 포트 드라이버는 SRB의 DataTransferLength를 업데이트하여 실제로 전송 된 데이터 양을 나타냅니다.

또한 미니 포트 드라이버는 다음 지침을 사용하여 SRB를 ScsiPortLogError에 전달하여 앞에서 설명한 일부 오류를 기록해야합니다.

SRB_STATUS_ERROR에 대한 드라이버 작성자의 재량에 따라 오류를 기록하십시오.

SRB_STATUS_PARITY_ERROR에 대해 항상 오류를 기록하십시오.

SRB_STATUS_UNEXPECTED_BUS_FREE에 대해 항상 오류를 기록하십시오.

항상 SRB_STATUS_SELECTION_TIMEOUT에 대한 오류를 기록하십시오.

항상 SRB_STATUS_COMMAND_TIMEOUT에 대한 오류를 기록하십시오.

오버런이 발생할 때마다 SRB_STATUS_DATA_OVERRUN에 대한 오류를 기록하지만 언더런이 발생할 때는 기록하지 않습니다.

항상 SRB_STATUS_PHASE_SEQUENCE_FAILURE에 대한 오류를 기록하십시오.

하드웨어 오류에 대해서는 항상 SRB_STATUS_BUSY에 대한 오류를 기록하십시오.

오류를 기록하기 위해 미니 포트 드라이버는 다음과 같은 시스템 정의 오류 또는 경고 코드 중 하나를 사용하여 ScsiPortLogError를 호출합니다.

SP_BUS_PARITY_ERROR이 (가) SRB_STATUS_PARITY_ERROR에 매핑됩니다.

SP_UNEXPECTED_DISCONNECT (대상 논리 단위로)

SP_INVALID_RESELECTION은 SRB_STATUS_PHASE_SEQUENCE_FAILURE 또는 SRB_STATUS_ERROR에 매핑됩니다.

SP_BUS_TIME_OUT은 SRB_STATUS_SELECTION_TIMEOUT에 매핑됩니다.

SP_REQUEST_TIMEOUT은 SRB_STATUS_COMMAND_TIMEOUT에 매핑됩니다.

SP_PROTOCOL_ERROR는 오버런 조건에 대해 SRB_STATUS_PHASE_SELECTION_FAILURE, SRB_STATUS_UNEXPECTED_BUS_FREE 또는 SRB_STATUS_DATA_OVERRUN에 매핑됩니다.

SP_INTERNAL_ADAPTER_ERROR이 (가) SRB_STATUS_ERROR에 매핑됩니다.

SP_IRQ_NOT_RESPONDING (HBA가 더 이상 인터럽트 요청을 생성하지 않음을 미니 포트 드라이버가 감지했음을 경고 함)

SP_BAD_FW_ERROR (FW는 펌웨어 임)

SP_BAD_FW_WARNING

ScsiPortLogError는 오류 로그 패킷을 할당하고,이를 설정하고, 미니 포트를 대신하여 이벤트 로그에 I/O 오류를 기록합니다. 시스템 관리자 또는 사용자는 시스템 이벤트 로그를 검사하고 필요한 경우 HBA를 재구성, 수리 또는 교체하여 HBA 상태를 모니터링 할 수 있습니다.





## 필수 및 선택적 SCSI 미니 포트 드라이버 루틴

모든 SCSI 미니 포트 드라이버에는 적어도 다음 시스템 정의 루틴이 있어야합니다.

- 미니 포트 드라이버를 초기화하는 [**DriverEntry**](https://msdn.microsoft.com/library/windows/hardware/ff552654)
- [*HwScsiFindAdapter*](https://msdn.microsoft.com/library/windows/hardware/ff557300)- 시스템에서 드라이버 지원 HBA를 구성하는 방법 (또는 지원 여부) 결정
- 지원되는 HBA를 초기화하기위한 [*HwScsiInitialize*](https://msdn.microsoft.com/library/windows/hardware/ff557302)
- [**HwScsiStartIo**](https://msdn.microsoft.com/library/windows/hardware/ff557323) 는 들어오는 요청에 대한 HBA (들)에 대한 작업을 시작합니다
- 버스 재설정 요청을 처리하는 [*HwScsiResetBus*](https://msdn.microsoft.com/library/windows/hardware/ff557318)



각 HBA 및 드라이버 디자이너에 따라 SCSI 미니 포트 드라이버에는 다음 시스템 정의 루틴의 일부 또는 전부가 있습니다.

- [HwscsiInterrupt](https://msdn.microsoft.com/library/windows/hardware/ff557312) 는 HBA가 생성 한 인터럽트를 처리합니다. HBA가 인터럽트를 생성하지 않아야 미니 포트 드라이버가 해당 HBA의 모든 I / O 작업을 폴링하여 관리하는 경우에만 선택적입니다. 그러나 폴링을 독점적으로 사용하면 미니 포트 드라이버의 성능과 HBA의 I / O 처리량에 나쁜 영향을 미칩니다. 이러한 미니 포트 드라이버에는 [HwScsiTimer](https://msdn.microsoft.com/library/windows/hardware/ff557327) 루틴이 있어야합니다.
- [HwScsiDisableInterruptsCallback](https://msdn.microsoft.com/library/windows/hardware/ff557288) 및 [HwScsiEnableInterruptsCallback](https://msdn.microsoft.com/library/windows/hardware/ff557295) 을 사용하여 인터럽트 구동 I/O 작업이 오래 걸리는 경우 지연된 I/O 처리를 처리합니다.
- HwScsiTimer는 HBA에서 또는 드라이버 설계자가 결정한 다른 용도로 긴 지연이 필요한 작업 시간을 설정합니다. HwScsiInterrupt 루틴이 없으면 HwScsiTimer 루틴을 사용하여 HBA의 효율적인 폴링을 위해 HwScsiTimer 루틴을 사용할 수 있도록 미니 포트 드라이버에 HwScsiTimer 루틴이 있어야합니다.
- HBA가 시스템 DMA 컨트롤러를 사용하는 경우 [HwScsiDmaStarted](https://msdn.microsoft.com/library/windows/hardware/ff557291)는 시스템 DMA 컨트롤러가 포트 드라이버에 의해 프로그래밍 된 후 HBA 전송을 설정하는 데 필요합니다
- [HwScsiAdapterState](https://msdn.microsoft.com/library/windows/hardware/ff557278) - HBA에 BIOS 또는 x86- 리얼 모드 드라이버가 없거나 x86 전용 Microsoft Windows 시스템에서 실행되지 않는 경우에만 선택 사항입니다.
- [HwScsiAdapterControl](https://msdn.microsoft.com/library/windows/hardware/ff557274) - 미니 포트 드라이버가 플러그 앤 플레이를 지원할 경우 필요합니다.

DriverEntry를 제외한 이전의 각 미니 포트 드라이버 루틴에는 해당 기능을 설명하기 위해 선택한 이름이 있습니다. 모든 미니 포트 드라이버의 초기 진입 점에 대한 필수 이름 인 DriverEntry를 제외하고 미니 포트 드라이버 루틴의 이름은 드라이버 작성자가 선택하는 모든 것이 될 수 있습니다.

다음 섹션에서는 이러한 미니 포트 드라이버 루틴에 대한 요구 사항과 기능에 대해 설명합니다.

SCSI 미니 포트 드라이버의 오류 처리는 [SCSI 미니 포트 드라이버에서 오류 처리](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/storage/error-handling-in-scsi-miniport-drivers)을 설명합니다.





## 부트 드라이브를 관리하는 미니 포트 드라이버에 대한 제한 사항

부트 장치의 어댑터를 관리하는 스토리지 미니 포트 드라이버는 시스템 장애시 특별한 제한을받습니다. 시스템의 메모리 이미지를 디스크에 덤프하는 동안, 미니 포트 드라이버는 다른 환경에서 작동해야합니다. 미니 포트 드라이버, 포트 드라이버 및 디스크 클래스 드라이버 간의 일반적인 통신이 중단됩니다. 커널은 디스크 덤프 포트 드라이버 인 diskdump.sys (ATA 컨트롤러의 경우 dumpata.sys)를 직접 호출하고 파일 시스템을 건너 뛰고 일반 I/O 스택을 통해 디스크 I/O를 수행합니다. 디스크 덤프 드라이버는 부팅 장치의 미니 포트 드라이버를 호출하여 모든 I/O 작업을 처리하고 디스크 덤프 드라이버는 포트 드라이버에 대한 모든 미니 포트 드라이버 호출을 가로 챕니다.

디스크 덤프 드라이버는 포트 드라이버가 제공하는 일련의 지원 루틴을 제공하므로 미니 포트 드라이버는 포트 루틴과 동일한 방식으로 디스크 덤프 드라이버 루틴을 사용할 수 있어야합니다.

그러나 디스크 덤프 경로에서 어댑터를 관리하는 미니 포트 드라이버는 덤프 모드에서 다음과 같은 제한을받습니다.

부트 드라이브를 만들 계획이 없으므로 이하 생략...

