# Windows Device Driver

출처: https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554690(v=vs.85).aspx



# Windows 드라이버 시작



여기서 드라이버에 대한 기본 개념에 대해 알아봅니다.

[C 프로그래밍 언어](http://go.microsoft.com/fwlink/p?LinkID=317477)에 익숙해야 하고 함수 포인터, 콜백 함수 및 이벤트 처리기의 개념을 이해하고 있어야 합니다. [사용자 모드 드라이버 프레임워크 1.x](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn265594(v=vs.85).aspx)를 기반으로 드라이버를 작성하려면 [C++ 및 COM](http://go.microsoft.com/fwlink/p?LinkID=317460)에 익숙해야 합니다.



## 이 조항의 내용

- [드라이버란?](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554678(v=vs.85).aspx)
- [드라이버를 작성해야 하나요?](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn449736(v=vs.85).aspx)
- [드라이버 모델 선택](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554652(v=vs.85).aspx)
- [첫 번째 드라이버 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554811(v=vs.85).aspx)
- [Windows 호환 하드웨어 개발 보드](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn914597(v=vs.85).aspx)
- [Sharks Cove 하드웨어 개발 보드](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn745910(v=vs.85).aspx)
- [드라이버 배포 및 테스트를 위한 컴퓨터 프로비전(WDK 10)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn745909(v=vs.85).aspx)
- [모든 드라이버 개발자를 위한 개념](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554731(v=vs.85).aspx)

## 관련 항목

- [WDK(Windows 드라이버 키트)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff557573(v=vs.85).aspx)



# 드라이버란?



*드라이버*를 하나의 정확한 용어로 정의하는 것은 매우 어려운 일입니다. 가장 기본적인 의미에서, **드라이버란 운영 체제와 장치의 상호 통신을 허용하는 소프트웨어 구성 요소**입니다. 예를 들어, 응용 프로그램이 장치에서 데이터를 읽어야 할 수 있습니다. 응용 프로그램은 운영 체제에서 구현한 함수를 호출하고, 운영 체제는 드라이버에서 구현한 함수를 호출합니다. 장치를 설계 및 제조한 회사에서 작성한 드라이버는 데이터를 가져오기 위해 장치 하드웨어와 통신하는 방법을 알고 있습니다. 드라이버는 장치에서 데이터를 가져온 후 운영 체제로 반환하고, 운영 체제는 응용 프로그램으로 반환합니다.

![](.\Images\IC535114.png)

## 정의 확장

위의 설명은 여러 가지 면에서 지나치게 단순화된 것입니다.

- 모든 드라이버가 장치를 설계한 회사에서 작성해야 하는 것은 아닙니다. 장치는 공시된 하드웨어 표준에 따라 설계되는 경우가 많습니다. 즉, Microsoft에서 드라이버를 작성할 수 있으며 드라이버 설계자는 드라이버를 제공할 필요가 없습니다.
- 모든 드라이버가 장치와 직접 통신하는 것은 아닙니다. 특정 I/O 요청(예: 장치에서 데이터 읽기)의 경우 종종 스택의 계층에 있는 여러 드라이버가 요청에 참여합니다. 스택을 시각화하는 일반적인 방법은 다음 다이어그램과 같이 첫 번째 참가자를 맨 위에 놓고 마지막 참가자를 맨 아래에 두는 것입니다. 스택에 있는 몇몇 드라이버는 요청에 참가할 때 요청의 형식을 다른 것으로 변형합니다. 이러한 드라이버는 장치와 직접 통신하지 않으며, 단지 요청을 조작한 후 스택의 아래쪽에 있는 드라이버로 전달하기만 합니다.

![](.\Images\IC535115.png)

- 장치와 직접 통신하는 스택의 한 드라이버를 <span style="color:red">***기능 드라이버*** </span>라고 하고, 보조 프로세싱을 수행하는 드라이버를 <span style="color:red">***필터 드라이버***</span>라고 합니다.
- 일부 필터 드라이버는 I/O 요청에 대한 정보를 관찰 및 기록할 뿐 요청에 적극적으로 참여하지는 않습니다. 예를 들어 특정 필터 드라이버는, 스택의 다른 드라이버들이 I/O 요청을 정확하게 처리하는지 확인하는 검증 도구의 역할을 합니다.

드라이버란 운영 체제와 장치 간 통신을 관찰하거나 이에 참여하는 소프트웨어 구성 요소라고 말함으로써 *드라이버*의 정의를 확장할 수 있습니다.

## 소프트웨어 드라이버

이러한 확장된 정의는 상당히 정확하지만 여전히 불완전한데, 그 이유는 일부 드라이버의 경우 하드웨어 장치와 전혀 연결되지 않기 때문입니다. 예를 들어, 핵심 운영 체제 데이터 구조에 액세스할 수 있는 도구에 데이터를 써야 하는데, 커널 모드에서 실행되는 코드에 의해서만 이 도구에 액세스할 수 있는 경우를 가정해봅니다. 이렇게 하려면 도구를 두 개의 구성 요소로 나눌 수 있습니다. <span style="color:red">***첫 번째 구성 요소는 사용자 모드에서 실행되며 사용자 인터페이스를 보여 줍니다. 두 번째 구성 요소는 커널 모드에서 실행되며 핵심 운영 체제 데이터에 액세스할 수 있습니다.***</span> 사용자 모드에서 실행되는 구성 요소를 응용 프로그램이라고 하며, 커널 모드에서 실행되는 구성 요소를 *소프트웨어 드라이버*라고 합니다. 소프트웨어 드라이버는 하드웨어 장치와 연결되지 않습니다. 프로세서 모드에 대한 자세한 내용은 [사용자 모드 및 커널 모드](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554836(v=vs.85).aspx)를 참조하세요.

다음 다이어그램은 커널 모드 소프트웨어 드라이버와 통신하는 사용자 모드 응용 프로그램을 보여 줍니다.

![](.\Images\IC535116.png)

## 추가 참고 사항

소프트웨어 드라이버는 항상 커널 모드에서 실행됩니다. 소프트웨어 드라이버를 작성하는 주요 이유는 커널 모드에서만 사용할 수 있는 보호된 데이터에 액세스하기 위해서입니다. 그러나 장치 드라이버가 항상 커널 모드 데이터 및 리소스에 액세스해야 하는 것은 아닙니다. 따라서 일부 장치 드라이버는 사용자 모드에서 실행됩니다.

아직 언급하지 않은 드라이버 범주인 <span style="color:red">*버스 드라이버*</span>가 있습니다. 버스 드라이버를 이해하려면 장치 노드 및 장치 트리를 이해해야 합니다. 장치 트리, 장치 노드 및 버스 드라이버에 대한 자세한 내용은 [장치 노드 및 장치 스택](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554721(v=vs.85).aspx)을 참조하세요.

지금까지의 설명은 *기능 드라이버*를 지나치게 단순화했습니다. 장치에 대한 기능 드라이버는 장치와 직접 통신하는 스택의 드라이버입니다. 이는 PCI(Peripheral Component Interconnect) 버스에 직접 연결된 장치에 해당하는 내용입니다. PCI 장치에 대한 기능 드라이버는 장치의 포트와 메모리 리소스에 매핑되는 주소를 가져옵니다. 기능 드라이버는 그러한 주소에 데이터를 기록함으로써 장치와 직접 통신합니다. 그러나 장치가 PCI 버스에 직접 연결되지 않는 경우도 많습니다. 대신 장치는 PCI 버스에 연결된 호스트 버스 어댑터에 연결됩니다. 예를 들면 USB Toaster는 호스트 버스 어댑터(USB 호스트 컨트롤러라고 함)에 연결되며, 이 어댑터는 PCI 버스에 연결됩니다. USB Toaster에는 기능 드라이버가 있고, USB 호스트 컨트롤러에도 기능 드라이버가 있습니다. Toaster에 대한 기능 드라이버는 USB 호스트 컨트롤러에 대한 기능 드라이버에 요청을 보냄으로써 Toaster와 간접적으로 통신합니다. 그러면 USB 호스트 컨트롤러에 대한 기능 드라이버는 USB 호스트 컨트롤러 하드웨어와 직접 통신하고, 이 하드웨어는 Toaster와 통신합니다.

![](.\Images\IC535117.png)



# 드라이버를 작성해야 하나요?



Microsoft Windows에는 많은 장치 유형에 대한 기본 제공 드라이버가 포함되어 있습니다. 장치 유형에 대한 기본 제공 드라이버가 있는 경우 드라이버를 작성할 필요가 없습니다. 장치는 기본 제공 드라이버를 사용할 수 있습니다.

## USB 장치에 대한 기본 제공 드라이버

장치가 USB DWG(장치 작업 그룹)에서 정의한 장치 클래스에 속하는 경우 이미 기존 Windows USB 클래스 드라이버가 있을 수 있습니다. 자세한 내용은 [지원되는 USB 장치 클래스에 대한 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff538820(v=vs.85).aspx)를 참조하세요.

## 기타 장치에 대한 기본 제공 드라이버

현재 Microsoft는 다음과 같은 기타 장치 유형에 대해 기본 제공 드라이버를 제공합니다.

| 장치 기술 및 드라이버                                        | 기본 제공 드라이버                                           | Windows 지원                               | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------ | ------------------------------------------------------------ |
| ACPI: ACPI 드라이버                                          | Acpi.sys                                                     | Windows XP 이상                            | Microsoft는 Acpi.sys 드라이버 및 ACPI BIOS를 통해 기본 ACPI 장치 기능을 지원합니다. ACPI 장치의 기능을 향상하기 위해 공급업체는 WDM 기능 드라이버를 제공할 수 있습니다. Windows ACPI 지원에 대한 자세한 내용은 ACPI 디자인 지침에서 [ACPI 장치 지원](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff536161(v=vs.85).aspx)을 참조하세요. |
| 오디오: Microsoft 오디오 클래스 드라이버                     | PortCls.sys                                                  | Windows XP 이상                            | Microsoft는 PortCls(포트 클래스 드라이버)를 통해 기본 오디오 렌더링 및 오디오 캡처를 지원합니다. 오디오 장치의 하드웨어 공급업체는 PortCls와 작동하는 어댑터 드라이버를 제공해야 합니다. 어댑터 드라이버에는 초기화 코드, 드라이버 관리 코드(DriverEntry 함수 포함) 및 오디오 미니포트 드라이버 모음이 포함됩니다. 자세한 내용은 [포트 클래스 소개](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff536829(v=vs.85).aspx)를 참조하세요. |
| 버스: 기본 SD 버스 드라이버, 기본 SD 저장소 클래스 드라이버 및 저장소 미니포트 드라이버 | sdbus.sys, sffdisk.sys, sffp_sd.sys                          | Windows Vista 이상                         | Microsoft는 SD 카드 판독기에 대해 다음과 같이 지원합니다. 운영 체제에서 PCI 버스에 직접 연결하는 SD 호스트 컨트롤러를 지원합니다. 시스템이 SD 호스트 컨트롤러를 열거할 때 기본 SD 버스 드라이버(sdbus.sys)를 로드합니다. 사용자가 SD 메모리 카드를 삽입하면 Windows는 기본 SD 저장소 클래스 드라이버(sffdisk.sys) 및 저장소 미니포트 드라이버(sffp_sd.sys)를 버스 드라이버 맨 위에 로드합니다. 사용자가 GPS나 무선 LAN처럼 다른 종류의 기능을 하는 SD 카드를 삽입하면 Windows는 해당 장치에 대해 공급업체에서 제공한 드라이버를 로드합니다. |
| HID: HID I2C 드라이버                                        | HIDI2C.sys                                                   | Windows 8 이상                             | Microsoft는 SPB(Simple Peripheral Bus) 및 GPIO(범용 I/O)를 지원하는 SoC 시스템에서 HID over I2C 장치를 지원합니다. HIDI2C.sys 드라이버를 통해 지원합니다. 자세한 내용은 [HID over I2C](https://msdn.microsoft.com/ko-kr/library/windows/hardware/jj127208(v=vs.85).aspx)를 참조하세요. |
| HID: 레거시 게임 포트 드라이버                               | HidGame.sys, Gameenum.sys                                    | Windows VistaWindows Server 2003Windows XP | Windows Vista 이하 버전에서 Microsoft는 HidGame.sys 및 Gameenum.sys 드라이버를 통해 레거시(USB 아님, Bluetooth 아님, I2C 아님) 게임 포트를 지원합니다. 자세한 내용은 [Windows에서 지원하는 HID 전송](https://msdn.microsoft.com/ko-kr/library/windows/hardware/jj126201(v=vs.85).aspx)을 참조하세요. |
| HID: 레거시 키보드 클래스 드라이버                           | Kbdclass.sys                                                 | Windows XP 이상                            | Microsoft는 Kbdclass.sys 드라이버를 통해 레거시(USB 아님, Bluetooth 아님, I2C 아님) 키보드를 지원합니다. 자세한 내용은 키보드 및 마우스 HID 클라이언트 드라이버를 참조하세요. 레거시 키보드의 기능을 향상하기 위해 공급업체는 키보드 필터 드라이버를 제공할 수 있습니다. 자세한 내용은 [MSDN 코드 갤러리에서 Kbfiltr 샘플](http://go.microsoft.com/fwlink/p/?LinkId=618052)을 참조하세요. |
| HID: 레거시 마우스 클래스 드라이버                           | Mouclass.sys                                                 | Windows XP 이상                            | Microsoft는 Mouclass.sys 드라이버를 통해 레거시(USB 아님, Bluetooth 아님, I2C 아님) 마우스를 지원합니다. 키보드 및 마우스 HID 클라이언트 드라이버입니다. 레거시 마우스의 기능을 향상하기 위해 공급업체는 마우스 필터 드라이버를 제공할 수 있습니다. 자세한 내용은 [MSDN 코드 갤러리에서 Moufiltr 샘플](http://go.microsoft.com/fwlink/p/?LinkId=618052)을 참조하세요. |
| HID: PS/2(i8042prt) 드라이버                                 | I8042prt.sys                                                 | Windows XP 이상                            | Microsoft는 I8042.sys 드라이버를 통해 레거시 PS/2 키보드 및 마우스를 지원합니다. PS/2 마우스 또는 키보드의 기능을 향상하기 위해 공급업체는 키보드 또는 마우스 필터 드라이버를 제공할 수 있습니다. 자세한 내용은 MSDN 코드 갤러리에서 [Kbfiltr 샘플](http://go.microsoft.com/fwlink/p/?LinkId=618052) 및 [Moufiltr 샘플](http://go.microsoft.com/fwlink/p/?LinkId=618052)을 참조하세요. |
| 이미징: WSD(Web Services for Devices) 스캔 클래스 드라이버   | WSDScan.sys                                                  | Windows Vista 이상                         | Microsoft는 WSD 스캔 드라이버(wsdscan.sys)를 통해 웹 서비스 스캐너(즉 웹에서 사용하는 스캐너)를 지원합니다. 그러나 WSD 분산된 스캔 관리를 지원하는 웹 서비스 스캐너 장치는 두 웹 서비스 프로토콜을 구현해야 합니다. 자세한 내용은 [WSD(Web Services for Devices)가 포함되는 WIA](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff553313(v=vs.85).aspx)를 참조하세요. |
| 인쇄: Microsoft 플로터 드라이버                              | Msplot                                                       | Windows XP 이상                            | Microsoft는 Microsoft 플로터 드라이버(Msplot)를 통해 Hewlett-Packard 그래픽 언어를 지원하는 플로터를 지원합니다. 플로터의 기능을 향상하기 위해 하나 이상의 PCD(플로터 특성 데이터) 파일로 구성된 미니 드라이버를 만들 수 있습니다. 자세한 내용은 [플로터 드라이버 미니 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff559833(v=vs.85).aspx)를 참조하세요. |
| 인쇄: Microsoft 포스트스크립트 프린터 드라이버               | Pscript                                                      | Windows XP 이상                            | Microsoft는 포스트스크립트 프린터 드라이버(Pscript)를 통해 포스트스크립트 프린터를 지원합니다. 포스트스크립트 프린터의 기능을 향상하기 위해 하나 이상의 PPD(PostScript Printer Description) 파일 및 폰트(NTF) 파일로 구성된 미니 드라이버를 만들 수 있습니다. 자세한 내용은 [Pscript 미니 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff561810(v=vs.85).aspx)를 참조하세요. |
| 인쇄: Microsoft 범용 프린터 드라이버                         | Unidrv                                                       | Windows XP 이상                            | Microsoft는 범용 프린터 드라이버(Unidrv)를 통해 포스트스크립트가 아닌 프린터를 지원합니다. 포스트스크립트가 아닌 프린터의 기능을 향상하기 위해 하나 이상의 GPD(Generic Printer Description) 파일로 구성된 미니 드라이버를 만들 수 있습니다. 자세한 내용은 [Microsoft 범용 프린터 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff556567(v=vs.85).aspx)를 참조하세요. |
| 인쇄: Microsoft v4 프린터 드라이버                           |                                                              | Windows 8 이상                             | Windows 8부터 Microsoft는 플로터뿐만 아니라 포스트스크립트 및 포스트스크립트가 아닌 프린터를 지원하는 단일 제공 클래스 드라이버를 제공합니다. 이 드라이버는 Microsoft 플로터 드라이버, Microsoft 범용 프린터 드라이버 및 Microsoft 포스트스크립트 프린터 드라이버를 대체합니다. 수정 없이 자체적으로 사용하는 이 프린터 드라이버는 기본 인쇄를 지원합니다. 자세한 내용은 [V4 프린터 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/hh706306(v=vs.85).aspx)를 참조하세요. |
| 인쇄: Microsoft XPS 프린터 드라이버                          | XPSDrv                                                       | Windows Vista 이상                         | Microsoft는 XPS 프린터 드라이버(XPSDrv)를 통해 XPS 문서 형식 인쇄를 지원합니다. 이 드라이버는 Microsoft의 GDI 기반 버전 3 프린터 드라이버 아키텍처를 확장하여 사용하는 XPS(XML Paper Specification) 문서를 지원합니다. XPSDrv 프린터 드라이버를 통해 XPS 문서 형식이 스풀 파일 형식 및 문서 파일 형식으로 사용됩니다. 수정 없이 자체적으로 사용하는 XPSDrv 프린터 드라이버는 기본 XPS 인쇄를 지원합니다. 자세한 내용은 [XPSDrv 프린터 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff564289(v=vs.85).aspx)를 참조하세요. |
| 센서: 센서 HID 클래스 드라이버                               | SensorsHIDClassDriver.dll                                    | Windows 8 이상                             | Microsoft는 HID 클래스 드라이버를 통해 동작, 작업 및 기타 센서 유형에 대한 지원을 제공합니다. Windows 8에는 이 HID 클래스 드라이버와 해당 HID I2C 및 HID USB 미니포트 드라이버가 포함되기 때문에 직접 드라이버를 구현할 필요가 없습니다. 센서에 대한 펌웨어에서 이 백서에 설명된 사용만 보고하면 됩니다. Windows는 펌웨어와 고유의 HID 드라이버를 사용하여 센서를 활성화 및 초기화한 후 센서로 관련 Windows API를 제공합니다. |
| 터치: Windows 포인터 장치 드라이버                           |                                                              | Windows 8 이상                             | Microsoft는 HID 클래스 드라이버를 통해 펜 및 터치 장치를 지원합니다. Windows 8에는 이 HID 클래스 드라이버와 해당 HID I2C 및 HID USB 미니포트 드라이버가 포함되기 때문에 사용자의 드라이버를 구현할 필요가 없습니다. 포인터 장치용 펌웨어의 이 백서에 설명된 사용만 보고하면 됩니다. Windows는 펌웨어와 고유의 HID 드라이버를 사용하여 장치에 대해 터치 및 포인터 기능을 사용 가능하도록 만들며 장치에 대한 액세스와 함께 Windows 터치 및 포인터 API를 제공합니다. |
| WPD: 미디어 전송 프로토콜 클래스 드라이버                    | WpdMtpDr.dll, WpdMtp.dll, WpdMtpUs.dll, WpdConns.dll 및 WpdUsb.sys | Windows Vista 이상                         | Microsoft는 미디어 전송 프로토콜 클래스 드라이버를 통해 음악 플레이어, 디지털 카메라, 휴대폰, 상태 모니터링 장치 등 Windows와 연결이 필요한 휴대용 장치를 지원합니다. 이 클래스 드라이버를 사용하는 공급업체는 장치에 MTP 클래스 프로토콜을 구현해야 합니다. (디지털 스틸 카메라의 경우 MTP 구현은 PTP를 사용하여 이전 버전과 호환되어야 합니다.) 자세한 내용은 [하드웨어 공급업체에 대한 지침](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff597573(v=vs.85).aspx)을 참조하세요. |



# 드라이버 모델 선택



Microsoft Windows는 드라이버 작성에 사용할 수 있는 다양한 드라이버 모델을 제공합니다. 최상의 드라이버 모델 선택을 위한 전략은 작성하려는 드라이버의 유형에 따라 달라집니다. 다음과 같은 옵션이 있습니다.

- 장치 기능 드라이버
- 장치 필터 드라이버
- 소프트웨어 드라이버
- 파일 시스템 필터 드라이버
- 파일 시스템 드라이버

다양한 드라이버 간 차이점에 대한 자세한 내용은 [드라이버란 무엇입니까?](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554678(v=vs.85).aspx) 및 [장치 노드 및 장치 스택](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554721(v=vs.85).aspx)을 참조하세요. 다음 섹션에서는 각 드라이버 유형에 맞는 모델의 선택 방법에 대해 설명합니다.

## 장치 기능 드라이버를 위한 드라이버 모델 선택

하드웨어 장치를 설계할 때 가장 먼저 고려해야 할 것 중 하나는 기능 드라이버의 작성의 필요 여부입니다. 다음과 같은 질문을 해보세요.



이러한 질문에 답하려면 자신의 장치가 [장치 및 드라이버 기술](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff557557(v=vs.85).aspx)에 설명되어 있는 기술 목록에서 어디에 해당되는지 확인하세요. 기능 드라이버 작성의 필요 여부를 결정하고 장치에 어떤 드라이버 모델을 사용할 수 있는지 알아보려면 해당 특정 기술에 대한 설명서를 참조하세요.

몇몇 개별 기술에는 미니 드라이버 모델이 있습니다. 미니 드라이버 모델에서는 장치 드라이버가 일반적인 작업을 처리하는 부분과 장치 전용 작업을 처리하는 부분, 이렇게 두 부분으로 구성됩니다. 대개 Microsoft는 일반적인 부분을 작성하고 장치 제조업체는 장치 전용 부분을 작성합니다. 장치 전용 부분에는 다양한 이름이 있는데, 이들 중 대부분은 *미니*라는 접두사를 공유합니다. 미니 드라이버 모델에 사용되는 몇 가지 이름은 다음과 같습니다.

- 디스플레이 미니포트 드라이버
- 오디오 미니포트 드라이버
- 배터리 미니클래스 드라이버
- Bluetooth 프로토콜 드라이버
- HID 미니 드라이버
- WIA 미니 드라이버
- NDIS 미니포트 드라이버
- 저장소 미니포트 드라이버
- 스트리밍 미니 드라이버

미니 드라이버 모델의 개요를 보려면 [미니 드라이버 및 드라이버 쌍](https://msdn.microsoft.com/ko-kr/library/windows/hardware/hh439643(v=vs.85).aspx)을 참조하세요.

[장치 및 드라이버 기술](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff557557(v=vs.85).aspx)에 나열된 모든 기술에 전용 미니 드라이버 모델이 있는 것은 아닙니다. 특정 기술에 대한 설명서에서는 [KMDF(커널 모드 드라이버 프레임워크)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff544396(v=vs.85).aspx)를 사용하도록 권장하고, 다른 기술에 대한 설명서에서는 [UMDF(사용자 모드 드라이버 프레임워크)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff560456(v=vs.85).aspx)를 사용하도록 권장할 수 있습니다. 요점은, 자신의 특정 장치 기술에 대한 설명서를 공부하기 시작해야 한다는 것입니다. 해당 장치 기술에 미니 드라이버 모델이 있으면 미니 드라이버 모델을 사용해야 합니다. 아니면 <span style="color:red">UMDF, KMDF, WDM(Windows Driver Model)</span> 중 무엇을 사용할지에 대해 기술 관련 설명서의 권장 사항을 따르면 됩니다.

## 장치 필터 드라이버를 위한 드라이버 모델 선택

대개 여러 드라이버가 단일 I/O 요청(예: 장치에서 데이터 읽기)에 참여하는 경우가 자주 있습니다. 드라이버는 스택에 놓이는데, 스택을 시각화하는 일반적인 방법은 첫 번째 드라이버를 맨 위에 두고 마지막 드라이버를 맨 아래에 두는 것입니다. 스택에는 하나의 기능 드라이버가 있으며 필터 드라이버도 있을 수 있습니다. 기능 드라이버와 필터 드라이버에 대한 자세한 내용은 [드라이버란 무엇입니까?](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554678(v=vs.85).aspx) 및 [장치 노드 및 장치 스택](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554721(v=vs.85).aspx)을 참조하세요.

장치에 대해 필터 드라이버를 작성하려고 준비하는 경우, 자신의 장치가 [장치 및 드라이버 기술](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff557557(v=vs.85).aspx)에 설명된 기술 목록에서 어디에 해당하는지 확인하세요. 특정 장치 기술에 대한 설명서에 필터 드라이버 모델 선택에 대한 지침이 있는지 알아보세요. 해당 장치 기술에 대한 설명서에 이러한 지침이 없으면 우선 UMDF를 드라이버 모델로 사용하는 것이 좋습니다. 필터 드라이버가 UMDF를 통해 사용할 수 없는 데이터 구조에 액세스해야 한다면 KMDF를 드라이버 모델로 사용하는 것이 좋습니다. 매우 드문 경우지만, 해당 드라이버가 KMDF를 통해 사용할 수 없는 데이터 구조에 액세스해야 한다면 WDM을 드라이버 모델로 사용하세요.

## 소프트웨어 드라이버를 위한 드라이버 모델 선택

장치와 연결되지 않은 드라이버를 *소프트웨어 드라이버*라고 합니다. 소프트웨어 드라이버에 대한 자세한 내용은 [드라이버란 무엇입니까?](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554678(v=vs.85).aspx) 항목을 참조하세요. 소프트웨어 드라이버는 커널 모드에서 실행할 수 있으므로 유용합니다. 이 경우 보호된 운영 체제 데이터에 액세스할 수 있습니다. 프로세서 모드에 대한 자세한 내용은 [사용자 모드 및 커널 모드](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554836(v=vs.85).aspx)를 참조하세요.

소프트웨어 드라이버의 경우 두 가지 옵션은 KMDF 및 커널 모드 Windows NT 드라이버 모델입니다. KMDF 및 커널 모드 Windows NT 모델 모두 PnP(플러그 앤 플레이) 및 전원 관리에 대해 염려할 필요 없이 드라이버를 작성할 수 있습니다. 대신 드라이버의 주요 작업에 집중할 수 있습니다. KMDF의 경우, PnP와 전원을 프레임워크에서 처리하므로 PnP와 전원에 대해 염려할 필요가 없습니다. 커널 모드 Windows NT 모델의 경우, 커널 모드 서비스가 PnP 및 전원 관리와 완전히 분리된 환경에서 작동하므로 PnP와 전원에 대해 염려할 필요가 없습니다.

KMDF에 이미 익숙한 경우 이 모델을 사용하는 것이 좋습니다. 드라이버를 PnP 및 전원 관리와 완전히 분리하려면 커널 모드 Windows NT 모델을 사용하세요. 전원 전환 또는 PnP 이벤트를 인식하는 소프트웨어 드라이버를 작성해야 하는 경우 커널 모드 Windows NT 모델을 사용할 수 없으며, KMDF를 사용해야 합니다.

**참고**  매우 드문 경우지만, PnP 또는 전원 이벤트를 인식하는 소프트웨어 드라이버를 작성해야 하며 드라이버가 KMDF를 통해 사용할 수 없는 데이터에 액세스해야 한다면 WDM을 사용해야 합니다.

 

## 파일 시스템 필터 드라이버를 위한 드라이버 모델 선택

파일 시스템 필터 드라이버를 위한 모델 선택에 대한 자세한 내용은 파일 시스템 미니필터 드라이버 및 [파일 시스템 필터 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff540382(v=vs.85).aspx)를 참조하세요.

## 파일 시스템 드라이버를 위한 드라이버 모델 선택

파일 시스템 드라이버를 위한 모델 선택에 대한 자세한 내용은 [파일 시스템 미니필터 드라이버](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff540402(v=vs.85).aspx)를 참조하세요.

## 관련 항목

- [커널 모드 드라이버 프레임워크](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff544396(v=vs.85).aspx)
- [사용자 모드 드라이버 프레임워크](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff560456(v=vs.85).aspx)

 

# 첫 번째 드라이버 작성 (오류 발생 확인 필요)

첫 번째 드라이버를 작성하는 경우 다음 연습을 사용하여 작업을 시작합니다. 각 연습 과정은 서로 별개이므로 원하는 순서로 수행할 수 있습니다.

## 이 조항의 내용

| 항목                                                         | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [템플릿을 기반으로 한 범용 Windows 드라이버(UMDF 2) 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/hh439659(v=vs.85).aspx) | 이 항목은 사용자 모드 드라이버 프레임 워크(UMDF) 2를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다. |
| [범용 Hello World 드라이버(KMDF) 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/hh439665(v=vs.85).aspx) | 이 항목은 커널 모드 드라이버 프레임 워크(KMDF)를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다. |
| [템플릿을 기반으로 한 범용 Windows 드라이버(KMDF) 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/hh439654(v=vs.85).aspx) | 이 항목은 커널 모드 드라이버 프레임 워크(KMDF)를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다. |

 

UMDF(사용자 모드 드라이버 프레임워크) 및 KMDF(커널 모드 드라이버 프레임워크)는 [WDF(Windows 드라이버 프레임워크)](http://go.microsoft.com/fwlink/p?linkid=399235)의 일부입니다. 



# 템플릿을 기반으로 한 유니버설 Windows 드라이버(UMDF 2) 작성

이 항목은 사용자 모드 드라이버 프레임 워크(UMDF) 2를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다.

시작하려면 Visual Studio 2015 및 WDK(Windows 드라이버 키트) 10이 설치되어 있는지 확인합니다. [Windows 하드웨어 개발자 센터](http://go.microsoft.com/fwlink/p/?LinkId=524487)에서 다운로드할 수 있습니다.

[Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)는 WDK를 설치하면 함께 제공됩니다.



## 드라이버 패키지 만들기 및 빌드

1. Visual Studio를 엽니다. **파일** 메뉴에서 **새로 만들기 > 프로젝트**를 선택합니다.

2. 새 프로젝트 대화 상자의 왼쪽 창에서<span style="color:red"> **템플릿 > Visual C++ > Windows 드라이버(VS 2017 에서는 테스트 로 변경됨) > WDF**로 이동합니다. **사용자 모드 드라이버(UMDF V2)**</span>를 선택합니다.

3. **이름** 필드에 프로젝트 이름으로 "UmdfDriver"를 입력합니다.

   **참고**  새 KMDF 또는 UMDF 드라이버를 만들 경우 32자 이하의 드라이버 이름을 선택해야 합니다. 길이 제한은 wdfglobals.h에서 정의됩니다. 

4. **위치** 필드에, 새 프로젝트를 만들 디렉터리를 입력합니다.

5. **솔루션용 디렉터리 만들기**를 선택합니다. **확인**을 클릭합니다.

![](.\Images\IC779068.png)

Visual Studio가 하나의 프로젝트와 하나의 솔루션을 만듭니다. **솔루션 탐색기** 창에서 볼 수 있습니다. **솔루션 탐색기** 창이 보이지 않는 경우 **보기** 메뉴에서 **솔루션 탐색기**를 선택하세요. 솔루션에는 UmdfDriver라는 드라이버 프로젝트가 있습니다. 드라이버 소스 코드를 보려면 **소스 파일**에서 아무 파일이나 엽니다. Driver.c 및 Device.c에서 시작하는 것이 좋습니다.

```
VS 편집 창 에서 #include "driver.tmh" 에 빨간 줄이 표시되며 포함 할 수 없다는 표시가 나타나지만 무시한다. 한번 빌드 하면 "driver.tmh" 등 thm 이 생성 되어 이후로는 오류로 표시되지 않는다.
```



![](.\Images\IC779069.png)

6. **솔루션 탐색기** 창에서 **'UmdfDriver'(1개 프로젝트) 솔루션**을 마우스 오른쪽 단추로 클릭하고 **구성 관리자**를 선택합니다. 드라이버 프로젝트 및 패키지 프로젝트에 대한 구성과 플랫폼을 선택합니다. 예를 들어 Debug 및 x64를 선택합니다.

7. **솔루션 탐색기** 창에서 **UmdfDriver**를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다. **구성 속성 > 드라이버 설정 > 일반**으로 이동하고 **대상 플랫폼**이 **범용**으로 기본 설정되는 것을 확인합니다.

8. 드라이버를 빌드하고 드라이버 패키지를 만들려면 **빌드** 메뉴에서 **솔루션 빌드**를 선택합니다. Microsoft Visual Studio의 **출력**창에 빌드 진행 상황이 표시됩니다. **출력** 창이 보이지 않는 경우 **보기** 메뉴에서 **출력**을 선택하세요.

빌드 출력이 다음을 포함하는지 확인합니다.

```
>  Driver is a Universal Driver.
```

솔루션이 성공적으로 빌드되었는지 확인했으면 Visual Studio를 닫아도 됩니다.

9. 만들어진 드라이버를 보려면 파일 탐색기에서 **UmdfDriver** 폴더로 이동한 다음 **x64\Debug\UmdfDriver**로 이동합니다. 이 디렉터리에는 다음 파일이 들어 있습니다.

    - UmdfDriver.dll-- 사용자 모드 드라이버 파일
    - UmdfDriver.inf -- 드라이버 설치 시 Windows에서 사용하는 정보 파일

    

## 유니버설 Windows 드라이버 배포 및 설치

일반적으로 드라이버를 테스트 및 디버그할 때 디버거와 드라이버는 별도의 컴퓨터에서 실행됩니다. 디버거를 실행하는 컴퓨터를 ***호스트 컴퓨터*** 라고 하고 드라이버를 실행하는 컴퓨터를 ***대상 컴퓨터*** 라고 합니다. 대상 컴퓨터를 ***테스트 컴퓨터*** 라고도 합니다.

지금까지는 호스트 컴퓨터에서 Visual Studio를 사용하여 드라이버를 빌드했습니다. 이제 대상 컴퓨터를 구성해야 합니다. [드라이버 배포 및 테스트를 위한 컴퓨터 프로비전(WDK 10)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn745909(v=vs.85).aspx)의 지침을 따르세요. 그런 다음 드라이버를 배포, 설치, 로드 및 디버깅할 수 있습니다.

1. 호스트 컴퓨터의 Visual Studio에서 솔루션을 엽니다. UmdfDriver 폴더에서 UmdfDriver.sln 솔루션 파일을 두 번 클릭할 수 있습니다.
2. **솔루션 탐색기** 창에서 **UmdfDriver**를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다.
3. 여기 표시된 것처럼 **UmdfDriver 속성 페이지** 창에서 **구성 속성 > 드라이버 설치(Driver Install) > 배포(Deployment)**로 이동합니다.
4. **배포 전 이전 드라이버 버전 제거**를 선택합니다.
5. **대상 장치 이름**에 대해 테스트 및 디버깅을 위해 구성한 컴퓨터의 이름을 선택합니다.
6. **하드웨어 ID 드라이버 업데이트**를 선택하고 드라이버에 대한 하드웨어 ID를 입력합니다. 이 연습에서 하드웨어 ID는 Root\UmdfDriver입니다. **확인**을 클릭합니다.

![](.\Images\IC779070.png)

```
참고  이 연습에서 하드웨어 ID는 실제 하드웨어 장치를 나타내는 것이 아니라, 장치 트리에서 루트 노드의 하위로서 배치될 예상 장치를 나타냅니다. 실제 하드웨어에 대해서는 하드웨어 ID 드라이버 업데이트를 선택하는 대신 설치 및 확인을 선택하세요.
드라이버의 정보(INF) 파일에서 하드웨어 ID를 볼 수 있습니다. 솔루션 탐색기 창에서 UmdfDriver > 드라이버 파일로 이동하여 UmdfDriver.inf를 두 번 클릭합니다. 하드웨어 ID는 [Standard.NT$ARCH$] 아래에 있습니다.
```



c++

```c++
[Standard.NT$ARCH$]
%DeviceName%=MyDevice_Install,Root\UmdfDriver
```

7. **디버그** 메뉴에서 **디버깅 시작**을 선택하고 키보드에서 **F5**를 누릅니다.

8. 드라이버가 대상 컴퓨터에 배포, 설치 및 로드될 때까지 기다립니다. 이 작업은 몇 분 정도 걸릴 수 있습니다.

## 관련 항목

- [드라이버 개발, 테스트 및 배포](http://go.microsoft.com/fwlink/p?linkid=399234)
- [Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)
- [첫 번째 드라이버 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554811(v=vs.85).aspx)



# 드라이버 배포 및 테스트를 위한 컴퓨터 프로비전(WDK 10)

*대상 또는 테스트 컴퓨터의 프로비전*은 자동 드라이버 배포, 테스트 및 디버깅을 위해 컴퓨터를 구성하는 프로세스입니다. 컴퓨터를 프로비전하려면 Microsoft Visual Studio를 사용합니다.

테스트 및 디버그 환경에는 *호스트 컴퓨터*와 *대상 컴퓨터*라는 두 대의 컴퓨터가 있습니다. 대상 컴퓨터를 *테스트 컴퓨터*라고도 합니다. 개발자는 호스트 컴퓨터에서 Visual Studio로 드라이버를 개발하고 빌드합니다. 디버거는 호스트 컴퓨터에서 실행되며 Visual Studio 사용자 인터페이스에서 사용할 수 있습니다. 드라이버를 테스트하고 디버그할 때 드라이버는 대상 컴퓨터에서 실행됩니다.

호스트와 대상 컴퓨터는 이름으로 서로 ping할 수 있어야 합니다. 이 두 컴퓨터가 동일한 작업 그룹 또는 동일한 네트워크 도메인에 연결되어 있는 경우 더 쉬울 수 있습니다. 컴퓨터가 작업 그룹에 있는 경우 허브나 스위치가 아닌 라우터를 사용하여 컴퓨터를 연결하는 것이 좋습니다.<span style="color:red"> **가상 컴퓨터에서는 프로비전이 지원되지 않습니다.**</span>

## 프로비전을 위해 대상 컴퓨터 준비

1. 대상 컴퓨터에서 드라이버를 실행하고 테스트하는 데 사용할 운영 체제를 설치합니다.

2. 보안 부팅이 x86 또는 x64 대상 컴퓨터에서 사용되는 경우 사용하지 않도록 설정합니다. UEFI(Unified Extensible Firmware Interface) 및 보안 부팅에 대한 자세한 내용은 [UEFI 펌웨어](http://go.microsoft.com/fwlink/p/?LinkID=309386)를 참조하세요.

   대상 컴퓨터에서 ARM 프로세서를 사용하는 경우 Windows 디버그 정책을 설치합니다. 이 작업은 Microsoft 또는 대상 컴퓨터 제조업체에서만 수행할 수 있습니다. 보안 부팅을 사용하지 않도록 설정할 필요가 없습니다.

3. 대상 컴퓨터에서 대상 컴퓨터의 플랫폼과 일치하는 WDK 테스트 대상 설치 MSI를 실행합니다. Remote 아래 WDK(Windows 드라이버 키트) 설치 디렉터리에서 MSI를 찾을 수 있습니다.

   예: C:\Program Files (x86)\Windows Kits\10\Remote\x64\WDK Test Target Setup x64-x64_en-us.msi

4. 대상 컴퓨터에서 N 또는 KN 버전의 Windows를 실행하는 경우 Windows N 및 KN 버전용 Media Feature Pack을 설치합니다.

   - [Windows 8.1 N 및 KN 버전용 Media Feature Pack](http://go.microsoft.com/fwlink/p?linkid=329737)
   - [Windows 8 N 및 KN 버전용 Media Feature Pack](http://go.microsoft.com/fwlink/p?linkid=329738)
   - [Windows 7 N 및 KN 버전용 Media Feature Pack](http://go.microsoft.com/fwlink/p?linkid=329739)

5. 대상 컴퓨터에서 Windows Server를 실행하는 경우 방금 WDK 테스트 대상 설치 MSI에서 만든 DriverTest 폴더를 찾습니다. (예: c:\DriverTest). **DriverTest** 폴더를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다. **보안** 탭에서 **Authenticated Users** 그룹에 **수정** 권한을 제공합니다.

호스트와 대상 컴퓨터가 서로 ping할 수 있는지 확인합니다. 명령 프롬프트 창을 열고 **ping** *ComputerName*을 입력합니다.

호스트 및 대상 컴퓨터가 작업 그룹에 연결되어 있고 서로 다른 서브넷에 있는 경우 호스트 및 대상 컴퓨터가 통신할 수 있도록 일부 방화벽 설정을 조정해야 할 수도 있습니다. 다음 단계를 따르세요.

1. 대상 컴퓨터의 제어판에서 **네트워크 및 인터넷 > 네트워크 공유 센터**로 이동합니다. 활성 네트워크를 기록해 둡니다. 이 네트워크는 **공용 네트워크**, **개인 네트워크** 또는 **도메인**입니다.
2. 대상 컴퓨터의 제어판에서 **시스템 및 보안 > Windows 방화벽 > 고급 설정 > 인바운드 규칙**으로 이동합니다.
3. 인바운드 규칙 목록에서 활성 네트워크에 대한 모든 네트워크 검색 규칙을 찾습니다. (예: **프로필**이 **개인**인 모든 네트워크 검색 규칙을 찾습니다.) 각 규칙을 두 번 클릭하고 **범위** 탭을 엽니다. **원격 IP 주소**에서 **모든 IP 주소**를 선택합니다.
4. 인바운드 규칙 목록에서 활성 네트워크에 대한 모든 파일 및 프린터 공유 규칙을 찾습니다. 각 규칙에 대해 규칙을 두 번 클릭하여 **범위** 탭을 엽니다. **원격 IP 주소**에서 **모든 IP 주소**를 선택합니다.

## 대상 컴퓨터 프로비전

이제 Visual Studio에서 호스트 컴퓨터로부터 대상 컴퓨터를 프로비전할 수 있습니다.

1. 호스트 컴퓨터에서 Visual Studio를 열고 드라이버 메뉴에서 테스트 > 컴퓨터 구성 을 선택합니다.

   **새 장치 추가**를 클릭합니다.

2. **네트워크 호스트 이름**으로 대상 컴퓨터의 이름을 입력합니다. **장치 프로비전 및 디버거 설정 선택**을 선택합니다.

![](.\Images\IC798034.png)

**다음**을 클릭합니다.

3. 디버깅 연결 형식을 선택하고 필요한 매개 변수를 입력합니다.

다양한 유형의 연결을 통한 디버깅 설정 방법에 대한 자세한 내용은 [Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p/?linkid=223405)에 대한 CHM 또는 온라인 설명서의 [Visual Studio에서 커널 모드 디버깅 설정](http://go.microsoft.com/fwlink/p?linkid=389193)을 참조하세요.

4. 프로비저닝 프로세스는 몇 분 정도 걸리며 대상 컴퓨터가 자동으로 한두 번 다시 부팅될 수 있습니다







# 범용 Hello World 드라이버(KMDF) 작성

이 항목은 커널 모드 드라이버 프레임 워크(KMDF)를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다.

시작하려면 Visual Studio 2015 및 WDK(Windows 드라이버 키트) 10이 설치되어 있는지 확인합니다. [Windows 하드웨어 개발자 센터](http://go.microsoft.com/fwlink/p/?LinkId=524487)에서 다운로드할 수 있습니다.

[Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)는 WDK를 설치하면 함께 제공됩니다.



## 드라이버 패키지 만들기 및 빌드

1. Microsoft Visual Studio를 엽니다. **파일** 메뉴에서 **새로 만들기 > 프로젝트**를 선택합니다.

2. **새 프로젝트** 대화 상자에서 **WDF**를 선택합니다.

3. 가운데 창에서 **커널 모드 드라이버, 비어 있음(KMDF)**을 선택합니다.

4. **이름** 필드에 프로젝트 이름으로 "KmdfHelloWorld"를 입력합니다.

   ```
   참고   *새 KMDF 또는 UMDF 드라이버를 만들 경우 32자 이하의 드라이버 이름을 선택해야 합니다. 길이 제한은 wdfglobals.h에 정의되어 있습니다.
   ```

5. **위치** 필드에, 새 프로젝트를 만들 디렉터리를 입력합니다.

6. **솔루션용 디렉터리 만들기**를 선택합니다. **확인**을 클릭합니다.

![](.\Images\IC779071.png)

Visual Studio가 하나의 프로젝트와 하나의 솔루션을 만듭니다. 여기서 볼 수 있는 것처럼 **솔루션 탐색기** 창에 표시됩니다. 솔루션 탐색기 창이 보이지 않는 경우 **보기** 메뉴에서 **솔루션 탐색기**를 선택하세요. 솔루션에는 KmdfHelloWorld라는 드라이버 프로젝트가 있습니다.

![](.\Images\IC779072.png)

7. **솔루션 탐색기** 창에서 **KmdfHelloWorld**를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다. **구성 속성 > 드라이버 설정 > 일반**으로 이동하고 **대상 플랫폼**이 **범용**으로 기본 설정되는 것을 확인합니다.

8. **솔루션 탐색기** 창에서 **KmdfHelloWorld**를 마우스 오른쪽 단추로 클릭하고 **추가 > 새 항목**을 선택합니다.

9. **새 항목 추가** 대화 상자에서 **C++ 파일**을 선택합니다. **이름**에 "Driver.c"를 입력합니다.

   **참고**  파일 이름 확장명은 **.cpp**가 아니고 **.c**입니다.

    

   **추가**를 클릭합니다. Driver.c 파일이 아래와 같이 소스 파일 아래에 추가됩니다.

![](.\Images\IC561063.png)

10. Driver.c를 열고 다음 코드를 입력합니다.

```c

#include <ntddk.h>
#include <wdf.h>
DRIVER_INITIALIZE DriverEntry;
EVT_WDF_DRIVER_DEVICE_ADD KmdfHelloWorldEvtDeviceAdd;

NTSTATUS DriverEntry(_In_ PDRIVER_OBJECT  DriverObject, _In_ PUNICODE_STRING RegistryPath)
{
    NTSTATUS status;
    WDF_DRIVER_CONFIG config;
 
    KdPrintEx(( DPFLTR_IHVDRIVER_ID, DPFLTR_INFO_LEVEL, "KmdfHelloWorld: DriverEntry\n" ));
    WDF_DRIVER_CONFIG_INIT(&config, KmdfHelloWorldEvtDeviceAdd);
    status = WdfDriverCreate(DriverObject, RegistryPath, WDF_NO_OBJECT_ATTRIBUTES, &config, WDF_NO_HANDLE);
    return status;
}

NTSTATUS KmdfHelloWorldEvtDeviceAdd(_In_ WDFDRIVER Driver, _Inout_ PWDFDEVICE_INIT DeviceInit)
{
    NTSTATUS status;
    WDFDEVICE hDevice;
    UNREFERENCED_PARAMETER(Driver);

    KdPrintEx(( DPFLTR_IHVDRIVER_ID, DPFLTR_INFO_LEVEL, "KmdfHelloWorld: KmdfHelloWorldEvtDeviceAdd\n" ));
    status = WdfDeviceCreate(&DeviceInit, WDF_NO_OBJECT_ATTRIBUTES, &hDevice);
    return status;
}
```

11. Driver.c를 저장합니다.

12. **솔루션 탐색기** 창에서 **솔루션 'KmdfHelloWorld'(1개 프로젝트)**를 마우스 오른쪽 단추로 클릭하고 **구성 관리자**를 선택합니다. 드라이버 프로젝트 및 패키지 프로젝트에 대한 구성과 플랫폼을 선택합니다. 이 연습에서는 Debug 및 x64를 선택합니다.

13. **솔루션 탐색기** 창에서 **KmdfHelloWorld**를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다. **Wpp 추적 > 모든 옵션**에서 **Wpp 추적 실행**을 **아니요**로 설정합니다. **적용**를 클릭한 다음 **확인**을 클릭합니다.

14. 드라이버를 빌드하고 드라이버 패키지를 만들려면 **빌드** 메뉴에서 **솔루션 빌드**를 선택합니다. Visual Studio의 **출력** 창에 빌드 진행 상황이 표시됩니다. **출력** 창이 보이지 않는 경우 **보기** 메뉴에서 **출력**을 선택하세요.

    솔루션이 성공적으로 빌드되었는지 확인했으면 Visual Studio를 닫을 수 있습니다.

15. 만들어진 드라이버를 보려면 파일 탐색기에서 **KmdfHelloWorld** 폴더로 이동한 다음 **C:\KmdfHelloWorld\x64\Debug**로 이동합니다. 이 폴더에는 다음 항목이 들어 있습니다.

    - KmdfHelloWorld.sys - 커널 모드 드라이버 파일
    - KmdfHelloWorld.inf - 드라이버 설치 시 Windows에서 사용하는 정보 파일
    - KmdfHelloWorld.cat - 설치 관리자가 드라이버 패키지에 대한 테스트 서명을 확인하는 데 사용하는 카탈로그 파일

## 드라이버 배포 및 설치

일반적으로 드라이버를 테스트 및 디버그할 때 디버거와 드라이버는 별도의 컴퓨터에서 실행됩니다. 디버거를 실행하는 컴퓨터를 *호스트 컴퓨터*라고 하고 드라이버를 실행하는 컴퓨터를 *대상 컴퓨터*라고 합니다. 대상 컴퓨터를 *테스트 컴퓨터*라고도 합니다.

지금까지는 호스트 컴퓨터에서 Visual Studio를 사용하여 드라이버를 빌드했습니다. 이제 대상 컴퓨터를 구성해야 합니다. [드라이버 배포 및 테스트를 위한 컴퓨터 프로비전(WDK 10)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn745909(v=vs.85).aspx)의 지침을 따르세요. 그런 다음 드라이버를 배포, 설치, 로드 및 디버깅할 수 있습니다.

1. 호스트 컴퓨터의 Visual Studio에서 솔루션을 엽니다. KmdfHelloWorld 폴더에서 KmdfHelloWorld.sln 솔루션 파일을 두 번 클릭할 수 있습니다.
2. **솔루션 탐색기** 창에서 **KmdfHelloWorld** 프로젝트를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다.
3. 여기 표시된 것처럼 **KmdfHelloWorld 속성 페이지** 창에서 **구성 속성 > 드라이버 설치 > 배포**로 이동합니다.
4. **배포 전 이전 드라이버 버전 제거**를 선택합니다.
5. **대상 장치 이름**에 대해 테스트 및 디버깅을 위해 구성한 컴퓨터의 이름을 선택합니다. 이 연습에서는 MyTestComputer라는 이름의 컴퓨터를 사용합니다.
6. **하드웨어 ID 드라이버 업데이트**를 선택하고 드라이버에 대한 하드웨어 ID를 입력합니다. 이 연습에서 하드웨어 ID는 Root\KmdfHelloWorld입니다. **확인**을 클릭합니다.

![](.\Images\IC779073.png)

```
참고  이 연습에서 하드웨어 ID는 실제 하드웨어 장치를 나타내는 것이 아니라, 장치 트리에서 루트 노드의 하위로서 배치될 예상 장치를 나타냅니다. 실제 하드웨어에 대해서는 하드웨어 ID 드라이버 업데이트를 선택하는 대신 설치 및 확인을 선택하세요.
드라이버의 정보(INF) 파일에서 하드웨어 ID를 볼 수 있습니다. 솔루션 탐색기 창에서 KmdfHelloWorld > 드라이버 파일로 이동하여 KmdfHelloWorld.inf를 두 번 클릭합니다. 하드웨어 ID는 [Standard.NT$ARCH$] 아래에 있습니다.
```



<span style="color:blue">c++</span>

```c++
[Standard.NT$ARCH$]
%KmdfHelloWorld.DeviceDesc%=KmdfHelloWorld_Device, Root\KmdfHelloWorld
```

7. **디버그** 메뉴에서 **디버깅 시작**을 선택하거나 키보드에서 **F5** 키를 누릅니다.

8. Visual Studio의 **출력** 창에 먼저 진행 상황이 표시됩니다. **디버거 직접 실행** 창이 열리고 계속해서 진행률을 표시합니다.

   드라이버가 대상 컴퓨터에 배포, 설치 및 로드될 때까지 기다립니다. 1-2분 정도 걸릴 수 있습니다.

9. **디버그** 메뉴에서 **모두 중단**을 선택합니다. 호스트 컴퓨터의 디버거가 대상 컴퓨터를 중단합니다. **디버거 직접 실행 창**에서 커널 디버깅 명령 프롬프트인 **kd>**를 볼 수 있습니다.

![](.\Images\IC562474.png)

10. 이제 **kd>** 프롬프트에 명령을 입력하여 디버거로 실험할 수 있습니다. 예를 들면 다음 명령을 시도해볼 수 있습니다.
    - [lm](http://go.microsoft.com/fwlink/p?linkid=399236)
    - [.sympath](http://go.microsoft.com/fwlink/p?linkid=399238)
    - [.reload](http://go.microsoft.com/fwlink/p?linkid=399239)
    - [x KmdfHelloWorld!*](http://go.microsoft.com/fwlink/p?linkid=399240)
11. 대상 컴퓨터가 다시 실행되도록 하려면 **디버그** 메뉴에서 **계속**을 선택합니다.
12. 디버깅 세션을 중지하려면 **디버그** 메뉴에서 **디버깅 중지**를 선택합니다.

## 관련 항목

- [드라이버 개발, 테스트 및 배포](http://go.microsoft.com/fwlink/p?linkid=399234)
- [Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)
- [첫 번째 드라이버 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554811(v=vs.85).aspx)



# 템플릿을 기반으로 한 유니버설 Windows 드라이버(KMDF) 작성

이 항목은 커널 모드 드라이버 프레임 워크(KMDF)를 사용하여 [범용 Windows 드라이버](https://msdn.microsoft.com/windows-drivers/develop/getting_started_with_universal_drivers)를 작성하는 방법을 설명합니다. Microsoft Visual Studio 템플릿으로 시작해서 별도의 컴퓨터에 드라이버를 배포하고 설치할 것입니다.

시작하려면 Visual Studio 2015 및 WDK(Windows 드라이버 키트) 10이 설치되어 있는지 확인합니다. [Windows 하드웨어 개발자 센터](http://go.microsoft.com/fwlink/p/?LinkId=524487)에서 다운로드할 수 있습니다.

[Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)는 WDK를 설치하면 함께 제공됩니다.



## 드라이버 패키지 만들기 및 빌드

1. Microsoft Visual Studio를 엽니다. **파일** 메뉴에서 **새로 만들기 > 프로젝트**를 선택합니다. 아래와 같이 **새 프로젝트** 대화 상자가 열립니다.

2. **새 프로젝트** 대화 상자에서 **WDF**를 선택합니다.

3. 가운데 창에서 **커널 모드 드라이버(KMDF)**를 선택합니다.

4. **이름** 필드에 프로젝트 이름으로 "KmdfDriver"를 입력합니다.

   **참고**   *새 KMDF 또는 UMDF 드라이버를 만들 경우 32자 이하의 드라이버 이름을 선택해야 합니다. 길이 제한은 wdfglobals.h에 정의되어 있습니다.

5. **위치** 필드에, 새 프로젝트를 만들 디렉터리를 입력합니다.

6. **솔루션용 디렉터리 만들기**를 선택합니다. **확인**을 클릭합니다.

   ![](.\Images\IC779065.png)

   Visual Studio가 하나의 프로젝트와 하나의 솔루션을 만듭니다. 여기서 볼 수 있는 것처럼 **솔루션 탐색기** 창에 표시됩니다. **솔루션 탐색기** 창이 보이지 않는 경우 **보기** 메뉴에서 **솔루션 탐색기**를 선택하세요. 솔루션에는 KmdfDriver라는 드라이버 프로젝트가 있습니다. 드라이버 소스 코드를 보려면 **소스 파일**에서 아무 파일이나 엽니다. Driver.c 및 Device.c에서 시작하는 것이 좋습니다.

   ![](.\Images\IC779066.png)

7. **솔루션 탐색기** 창에서 **'KmdfDriver'(1개 프로젝트) 솔루션**을 마우스 오른쪽 단추로 클릭하고 **구성 관리자**를 선택합니다. 드라이버 프로젝트 및 패키지 프로젝트에 대한 구성과 플랫폼을 선택합니다. 이 연습에서는 Debug 및 x64를 선택합니다.

8. 드라이버를 빌드하고 드라이버 패키지를 만들려면 **빌드** 메뉴에서 **솔루션 빌드**를 선택합니다. Visual Studio의 **출력** 창에 빌드 진행 상황이 표시됩니다. **출력** 창이 보이지 않는 경우 **보기** 메뉴에서 **출력**을 선택하세요.

   솔루션이 성공적으로 빌드되었는지 확인했으면 Visual Studio를 닫아도 됩니다.

9. 빌드된 드라이버를 보려면 파일 탐색기에서 **KmdfDriver** 폴더로 이동한 다음 **x64\Debug\KmdfDriver**로 이동합니다. 이 폴더에는 다음 항목이 들어 있습니다.

   - KmdfDriver.sys - 커널 모드 드라이버 파일
   - KmdfDriver.inf -- 드라이버 설치 시 Windows에서 사용하는 정보 파일

## 드라이버 배포 및 설치

일반적으로 드라이버를 테스트 및 디버그할 때 디버거와 드라이버는 별도의 컴퓨터에서 실행됩니다. 디버거를 실행하는 컴퓨터를 *호스트 컴퓨터*라고 하고 드라이버를 실행하는 컴퓨터를 *대상 컴퓨터*라고 합니다. 대상 컴퓨터를 *테스트 컴퓨터*라고도 합니다. 디버깅 드라이버에 대한 자세한 내용은 [Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)를 참조하세요.

지금까지는 호스트 컴퓨터에서 Visual Studio를 사용하여 드라이버를 빌드했습니다. 이제 대상 컴퓨터를 구성해야 합니다. [드라이버 배포 및 테스트를 위한 컴퓨터 프로비전(WDK 10)](https://msdn.microsoft.com/ko-kr/library/windows/hardware/dn745909(v=vs.85).aspx)의 지침을 따르세요. 그런 다음 드라이버를 배포, 설치, 로드 및 디버깅할 수 있습니다.

1. 호스트 컴퓨터의 Visual Studio에서 솔루션을 엽니다. KmdfDriver 폴더에서 KmdfDriver.sln 솔루션 파일을 두 번 클릭할 수 있습니다.

2. **솔루션 탐색기** 창에서 **KmdfDriver** 프로젝트를 마우스 오른쪽 단추로 클릭하고 **속성**을 선택합니다.

3. **KmdfDriver Package 속성 페이지** 창의 왼쪽 창에서 **구성 속성 > 드라이버 설치 > 배포**로 이동합니다.

4. **배포 전 이전 드라이버 버전 제거**를 선택합니다.

5. **원격 컴퓨터 이름**에 대해 테스트 및 디버깅을 위해 구성한 컴퓨터의 이름을 선택합니다. 이 연습에서는 MyTestComputer라는 이름의 컴퓨터를 사용합니다.

6. **하드웨어 ID 드라이버 업데이트**를 선택하고 드라이버에 대한 하드웨어 ID를 입력합니다. 이 연습에서 하드웨어 ID는 Root\KmdfDriver입니다. **확인**을 클릭합니다.

   ![](.\Images\IC779067.png)

   ```
   참고  이 연습에서 하드웨어 ID는 실제 하드웨어 장치를 나타내는 것이 아니라, 장치 트리에서 루트 노드의 하위로서 배치될 예상 장치를 나타냅니다. 실제 하드웨어에 대해서는 하드웨어 ID 드라이버 업데이트를 선택하는 대신 설치 및 확인을 선택하세요.
   드라이버의 정보(INF) 파일에서 하드웨어 ID를 볼 수 있습니다. 솔루션 탐색기 창에서 KmdfDriver > 드라이버 파일로 이동하여 KmdfDriver.inf를 두 번 클릭합니다. 하드웨어 ID는 [Standard.NT$ARCH$] 아래에 있습니다.
   ```

   c++

   ```c++
   [Standard.NT$ARCH$]
   %KmdfDriver.DeviceDesc%=KmdfDriver_Device, Root\KmdfDriver
   ```

   

7. **디버그** 메뉴에서 **디버깅 시작**을 선택하거나 키보드에서 **F5** 키를 누릅니다.

8. Visual Studio의 **출력** 창에 먼저 진행 상황이 표시됩니다. **디버거 직접 실행 창**이 열리고 계속해서 진행률을 표시합니다.

   드라이버가 대상 컴퓨터에 배포, 설치 및 로드될 때까지 기다립니다. 1-2분 정도 걸릴 수 있습니다.

9. **디버그** 메뉴에서 **모두 중단**을 선택합니다. 호스트 컴퓨터의 디버거가 대상 컴퓨터를 중단합니다. **디버거 직접 실행 창**에서 커널 디버깅 명령 프롬프트인 **kd>**를 볼 수 있습니다.

   ![](.\Images\IC561058.png)

10. 이제 **kd>** 프롬프트에 명령을 입력하여 디버거로 실험할 수 있습니다. 예를 들면 다음 명령을 시도해볼 수 있습니다.

    - [lm](http://go.microsoft.com/fwlink/p?linkid=399236)
    - [.sympath](http://go.microsoft.com/fwlink/p?linkid=399238)
    - [.reload](http://go.microsoft.com/fwlink/p?linkid=399239)
    - [x KmdfDriver!*](http://go.microsoft.com/fwlink/p?linkid=399240)

11. 대상 컴퓨터가 다시 실행되도록 하려면 **디버그** 메뉴에서 **계속**을 선택합니다.

12. 디버깅 세션을 중지하려면 **디버그** 메뉴에서 **디버깅 중지**를 선택합니다.

## 관련 항목

- [드라이버 개발, 테스트 및 배포](http://go.microsoft.com/fwlink/p?linkid=399234)
- [Windows용 디버깅 도구](http://go.microsoft.com/fwlink/p?linkid=223405)
- [첫 번째 드라이버 작성](https://msdn.microsoft.com/ko-kr/library/windows/hardware/ff554811(v=vs.85).aspx)

