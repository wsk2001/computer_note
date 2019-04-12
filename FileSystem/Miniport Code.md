# Miniport Code

출처: <http://perisoft.net/index.htm>



## **Virtual Device Source Code**

가상 장치를 만들어야합니까?

우리는 정밀한 소스 코드를 제공하여 개발을 시작하고 처음부터 결승선에 가까이 다가 가게합니다.

각 샘플은 다음과 같은 기능을 제공합니다.

- 드라이버 문자가 할당되고 실제 디스크 드라이브와 똑같이 작동하는 가상 디스크 드라이브를 만듭니다.
- 2TB보다 큰 디스크 드라이브 지원 (long lba 주소 지정)
- [SPC-5 및 SBC-4](http://www.t10.org/) 명령 세트 준수
- 32 비트 및 64 비트 Windows 7 이상, Server 2008 이상을 지원합니다. 요청시 XP, Vista 및 Itanium 지원 가능.
- 프로그래밍 방식으로 설치하거나 하드웨어 추가 마법사를 사용하여 설치할 수 있습니다.
- 각 샘플에는 [가상 stortport 미니 포트](https://docs.microsoft.com/en-us/windows-hardware/drivers/storage/overview-of-storage-virtual-miniport-drivers) 가 포함되어 있습니다.
- Verifier clean, / W4 및 / Wall Clean은 Windows HLK에서 ScsiComplianceTest를 전달합니다.
- 절대 정밀도로 튜닝



### **UVStor**

**적용 대상**:  virtual devices

**예제 장치 :**  디스크 드라이브로 마운트 된 파일

**주요 특징들**

- 가상 디스크, DVD 드라이브, 네트워크 백엔드가있는 장치 및 기타 가상 장치에 적합합니다.

- Windows 응용 프로그램에서 장치를 만듭니다.

- 장치 드라이버 개발이 필요 없습니다. 동반자가 될 준비가되어있는 번들 (소스 코드가 제공되지 않았거나 필요함).

- 백 엔드에 따라 초당 1 기가 바이트 이상의 성능을 달성하십시오.

- C ++을 이용한다.
- 가격: $6000
- Sample Download: [Download UVStor](http://perisoft.net/bin/uvstor.zip)



### **StorFile**

**적용 대상**:  virtual devices

**예제 장치 :**  디스크 드라이브로 마운트 된 파일

**주요 특징들**

- 가상 디스크, DVD 드라이브, 네트워크 백엔드가있는 장치 및 기타 가상 장치에 적합합니다.
- 모든 코드는 독립 실행 형 미니 포트 드라이버에 있습니다.
- 모든 코드는 PASSIVE_LEVEL에서 실행되므로 모든 WDK 함수를 호출하는 것이 이상적입니다.
- 우수한 장치 수명 제어를 위해 동적으로 장치를 쉽게 추가 및 제거 할 수 있습니다.
- 오버랩 된 요청 대기열과 빠른 비동기식 백엔드를 이용합니다.
- 비동기 파일 I/O를 사용하여 스파스 디스크 파일을 읽고 쓰는 커널 수준 코드.
- 각 장치는 최적의 멀티 코어 성능을위한 전용 스레드를 확보합니다.
- HwStorProcessServiceRequest, StorPortCompleteServiceIrp 및 IOCTL_MINIPORT_PROCESS_SERVICE_IRP를 활용합니다.
- pseudo unrecovered 오류를 생성하기 위해 WRITE LONG을 지원합니다.
- inf 파일에서 미니 포트 드라이버에 정보를 전달하십시오.
- IOCTL을 앱에서 미니 포트로 전달하기위한 전체 코드 및 테스트 유틸리티.
- 요청, 장치 및 어댑터별로 컨텍스트 데이터를 제공합니다.
- 가격: $6000
- Sample Download: [Download StorFile](http://perisoft.net/bin/storfile.zip)



### **StorRaid**

**적용 대상**:  software RAID

**예제 장치 :**  RAID0, RAID1

**주요 특징들**

- 로컬 디스크 드라이브에 스트라이프 및 미러를 생성합니다.
- 미러링은 디스크 재구성을 수행합니다.
- 디스크가 실행되는 동안 미러를 동적으로 추가 할 수 있습니다.
- 고성능 I / O 알고리즘.
- RAID 관리 명령 줄 도구가 포함되어 있습니다.
- 고성능을위한 선택 가능한 스트라이프 섹터 인터리빙
- 512 및 4K를 포함한 모든 섹터 크기를 지원합니다.
- RAID에 할당 된 디스크 드라이브에는 Windows에서 드라이브 문자가 할당되지 않습니다.
- RAID에 할당 된 디스크 드라이브는 RAID 데이터를 캡슐화하는 GPT 파티션으로 포맷됩니다.
- 단일 장치 드라이버 솔루션.
- 엔터프라이즈 급, 소프트웨어 RAID 솔루션을 출하 할 준비가되었습니다.
- 가격: $24,000
- Sample Download:  [Download StorRaid](http://perisoft.net/bin/storraid.zip)



### FAQ:

**도착하는데 얼마나 걸리나요?**

이 소프트웨어는 전자 방식으로 제공됩니다. 주문을 입력 한 후 즉시 소스 코드를 다운로드하여 시작할 수 있습니다. 온라인 주문은 신용 카드로 안전하고 안전합니다. 우리는 구매 주문서를받습니다.

**각 샘플에는 무엇이 포함되어 있습니까?**

Visual Studio 2017 또는 이후 버전을 사용하여 소스 코드, 설치 프로그램 / 제거 프로그램 및 사전 빌드 된 드라이버를 실행할 준비가 된 모든 정보를 제공하는 문서를 신중하고 체계적으로 문서화 한 소스 코드를 얻을 수 있습니다.

**장치 관리자에서 어떻게 보이나요?**

![](.\Images\storfile_devmgmt.png)

**기기에 제한이 있습니까?**

SCSI 유형의 여러 장치 및 장치 (예 : 디스크 드라이브, 광학, 프린터)를 정의 할 수 있습니다.

**API는 무엇입니까?**

<span style="color:green">**UVStor**</span> : 간단하고 사용하기 쉬운 C++ 클래스를 장치 요구에 맞게 사용자 정의하십시오.

<span style="color:green">**StorFile**</span>: 6 가지 간단한 함수를 채우고 콜백을 사용하면 코드가 완료됩니다.  WDK 기능이나 다른 드라이버는 무료로 호출 할 수 있습니다.

```
// 아래 함수는 PASSIVE_LEVEL에서 호출됩니다.
// 어댑터, 장치 및 요청에 대한 컨텍스트 데이터를 정의 할 수 있습니다
StartAdapter()
StopAdapter()
StartDevice()
StopDevice()
StartSrb()
StartIoctl()
// 아래의 콜백은 DISPATCH_LEVEL 이하에서 호출 할 수 있으며 백엔드에서 게시 할 때 편리합니다. 
CompleteSrb()
CompleteIoctl()
AddDevice()
RemoveDevice()

```

<span style="color:green">**StorRaid**</span>: 장치 드라이버는 소프트웨어 RAID 솔루션으로 즉시 사용할 수 있습니다. 여기에는 RAID를 관리하기위한 IOCTL 인터페이스가 포함됩니다.

**어떤 기술 지원이 제공됩니까?**

90 일 무료 지원 및 전액 환불 정책.

**로열티 또는 기타 비용이 있습니까?**

아니요. 소스를 수정하여 판매량에 관계없이 원하는만큼 많은 제품에 사용할 수 있습니다. 소스를 사용할 수있는 시간에 대한 제한도 없습니다.

**라이센스 계약이란 무엇입니까?**

[License Agreement](javascript:displayPopup("license.htm"))

아래는 라이센스 전문

소스 코드를 구입하여 실행하고 소스 코드의 파생 제품을 준비하는 사람 또는 조직에게 사용 권한이 무료로 부여됩니다. 직접 경쟁이 가능한 소스 코드 또는 파생 제품은 다른 잠재 고객이 사용할 수 없도록해야합니다 (예 : 공개 포럼에 소스 코드를 게시하지 마십시오).

Perisoft는 구입 후 90 일 동안 무료 전자 메일 지원을 제공합니다. Perisoft가 지원 기간 중에 지원 문제를 해결하기 위해 상업적으로 합당한 노력을 기울이지 않는 경우, 제품의 전체 구매 가격에 대한 환불이 승인됩니다.

소스 코드에는 제 3 자의 권리가 없습니다. Perisoft는 소스 코드의 사용 및 / 또는 소지가 제 3 자의 지적 재산권을 침해한다는 주장에 대해 구매자에게 보상합니다.

소스 코드는 상품성, 특정 목적에의 적합성, 소유권 및 비 침해에 대한 보증을 포함하여 (단, 이에 한하지 않음) 어떠한 종류의 명시 적 또는 묵시적 보증도없이 '있는 그대로'제공됩니다. 어떠한 경우에도 Perisoft는 소스 코드 또는 소스 코드의 사용 또는 기타 거래로 인해 또는 계약과 관련하여 발생하는 계약상의 불법 행위 또는 기타 다른 손해에 대해서도 책임을지지 않습니다.



### 주요 고객 :

facilis, samsung, appAssure,PeoWess,Siemens,...

