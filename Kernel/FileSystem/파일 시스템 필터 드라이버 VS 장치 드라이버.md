# 파일 시스템 필터 드라이버 VS 장치 드라이버

출처: <https://docs.microsoft.com/ko-kr/windows-hardware/drivers/ifs/file-system-filter-drivers-are-not-device-drivers>



장치 드라이버는 특정 하드웨어 I / O 장치를 제어하는 소프트웨어 구성 요소입니다. 예를 들어 DVD 저장 장치 드라이버는 DVD 드라이브를 제어합니다.

반대로 파일 시스템 필터 드라이버는 하나 이상의 파일 시스템과 함께 작동하여 파일 I/O 작업을 관리합니다. 이러한 작업에는 파일 및 디렉터리 만들기, 열기, 닫기 및 열거, 파일, 디렉토리 및 볼륨 정보 가져 오기 및 설정; 파일 데이터를 읽고 쓰는 것. 또한 파일 시스템 필터 드라이버는 캐싱, 잠금, 스파 스 파일, 디스크 할당량, 압축, 보안, 복구 가능성, 재분석 지점 및 볼륨 탑재 지점과 같은 파일 시스템 관련 기능을 지원해야합니다.



파일 시스템 필터 드라이버와 장치 드라이버 간의 유사점과 차이점에 대한 자세한 내용은 다음을 참조하십시오.

[How File System Filter Drivers Are Similar to Device Drivers](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/ifs/how-file-system-filter-drivers-are-similar-to-device-drivers)

[How File System Filter Drivers Are Different from Device Drivers](https://docs.microsoft.com/ko-kr/windows-hardware/drivers/ifs/how-file-system-filter-drivers-are-different-from-device-drivers)



---

## 파일 시스템 필터 드라이버가 장치 드라이버와 유사점

다음 하위 섹션에서는 Microsoft Windows 운영 체제의 파일 시스템 필터 드라이버와 장치 드라이버 간의 유사점에 대해 설명합니다.



### 유사한 구조

장치 드라이버와 마찬가지로 파일 시스템 필터 드라이버에는 [DriverEntry](https://msdn.microsoft.com/library/windows/hardware/ff544113), [디스패치](https://msdn.microsoft.com/library/windows/hardware/ff566407) 및 [I/O completion](https://msdn.microsoft.com/library/windows/hardware/ff565398) 루틴이 있습니다. 이들은 디바이스 드라이버가 호출하는 것과 동일한 커널 모드 루틴을 많이 호출하고, 연관된 디바이스 (즉, 파일 시스템 볼륨)에 대한 I/O 요청을 필터링합니다.



### 유사한 기능

파일 시스템 필터 드라이버 및 장치 드라이버는 I / O 시스템의 일부이므로 [I/O 요청 패킷](https://msdn.microsoft.com/library/windows/hardware/ff558771) (IRP)을 수신하고 이에 대한 조치를 취합니다.

장치 드라이버와 마찬가지로 파일 시스템 필터 드라이버는 자체 IRP를 만들어 하위 수준의 드라이버로 보낼 수 있습니다.

두 종류의 드라이버는 다양한 시스템 이벤트의 (콜백 함수를 사용하여) 알림을 등록 할 수 있습니다.



### 다른 유사점

장치 드라이버와 마찬가지로 파일 시스템 필터 드라이버는 IOCTL ([Introduction to I / O Control Codes](https://msdn.microsoft.com/library/windows/hardware/ff548059))을 수신 할 수 있습니다. 그러나 파일 시스템 필터 드라이버는 파일 시스템 제어 코드 (FSCTL)를 수신 및 정의 할 수도 있습니다.

장치 드라이버와 마찬가지로 파일 시스템 필터 드라이버는 시스템 시작시 로드되거나 나중에 시스템 시작 프로세스가 완료된 후에 로드되도록 구성 할 수 있습니다.



---

## 파일 시스템 필터 드라이버가 장치 드라이버와 차이점

다음 하위 절에서는 파일 시스템 필터 드라이버와 장치 드라이버 간의 차이점에 대해 설명합니다.



### 전원 관리 없음

파일 시스템 필터 드라이버는 장치 드라이버가 아니므로 하드웨어 장치를 직접 제어하지 않으므로 [IRP_MJ_POWER](https://msdn.microsoft.com/library/windows/hardware/ff550784) 요청을받지 못합니다. 대신, 전원 IRP는 저장 장치 스택으로 직접 전송됩니다. 그러나 드물 긴하지만 파일 시스템 필터 드라이버가 전원 관리를 방해 할 수 있습니다. 이러한 이유로 파일 시스템 필터 드라이버는 DriverEntry 루틴에서 IRP_MJ_POWER에 대한 디스패치 루틴을 등록하면 안되며 [PoXxx](https://msdn.microsoft.com/library/windows/hardware/ff559835) 루틴을 호출하면 안됩니다.



### WDM 없음

파일 시스템 필터 드라이버는 Windows 드라이버 모델 (WDM) 드라이버 일 수 없습니다. Microsoft [Windows 드라이버 모델](https://msdn.microsoft.com/library/windows/hardware/ff565698) 은 장치 드라이버 용입니다. Windows Me, Windows 98 및 Windows 95의 파일 시스템 드라이버 개발에 대한 자세한 내용은 Windows Me 드라이버 개발 키트 (DDK)를 참조하십시오.



### AddDevice 또는 StartIo 없음

파일 시스템 필터 드라이버는 장치 드라이버가 아니므로 하드웨어 장치를 직접 제어하지 않으므로 [AddDevice](https://msdn.microsoft.com/library/windows/hardware/ff540521) 또는 [StartIo](https://msdn.microsoft.com/library/windows/hardware/ff563858) 루틴을 가져서는 안됩니다.



### 생성 된 다른 장치 개체

파일 시스템 필터 드라이버 및 장치 드라이버는 모두 장치 개체를 만들지 만 장치 개체의 수와 종류가 다릅니다.

장치 드라이버는 장치를 나타내는 실제 및 기능 장치 개체를 만듭니다. 플러그 앤 플레이 (PnP) 관리자는 장치 드라이버가 만든 모든 장치 개체를 포함하는 전역 장치 트리를 작성하고 유지 관리합니다. 파일 시스템 필터 드라이버가 만드는 장치 개체는이 장치 트리에 포함되어 있지 않습니다.

파일 시스템 필터 드라이버는 실제 또는 기능 장치 개체를 만들지 않습니다. 대신 제어 장치 개체를 만들고 장치 개체를 필터링합니다. 제어 장치 개체는 시스템 및 사용자 모드 응용 프로그램에 대한 필터 드라이버를 나타냅니다. 필터 장치 개체는 특정 파일 시스템이나 볼륨을 필터링하는 실제 작업을 수행합니다. 파일 시스템 필터 드라이버는 일반적으로 하나의 제어 장치 개체와 하나 이상의 필터 장치 개체를 만듭니다.



### 기타 차이점

파일 시스템 필터 드라이버는 장치 드라이버가 아니기 때문에  [direct memory access (DMA)](https://msdn.microsoft.com/library/windows/hardware/ff565374)를 수행하지 않습니다.

대상 장치의 기능 드라이버의 위 또는 아래에 부착 할 수있는 장치 필터 드라이버와 달리 파일 시스템 필터 드라이버는 대상 파일 시스템 드라이버 위에 만 부착 할 수 있습니다. 따라서 장치 드라이버 용어 에서 파일 시스템 필터 드라이버는 상위 필터 일 수 있으며 하위 필터는 될 수 없습니다.



#### <span style="color:blue">필자(주) 파일시스템 필터 드라이버는 직접 File 을 읽고 쓰는 기능은 없는것으로 보임, 즉 ReadFile, WriteFile 등을 호출 할 수 없는것으로 판단됨. 해당 함수들이 호출 될때 중간에 이벤트를 받거나 또는 일부 DATA 를 모니터링 하는 정도로 사용 하기는 좋을듯. 특히 WDM 을 사용하지 않으므로 인해 Windows Device Driver Kit 을 설치하지 않아도 되므로 상대적으로 쉽게 접근할 수 있음.</span>







