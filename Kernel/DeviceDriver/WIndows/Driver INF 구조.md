# Driver INF 구조

출처: <https://oulth.tistory.com/25>



## 드라이버 INF 구조 

드라이버를 작성하면 같이 작성해야하는 것이 바로 INF 파일이다. 사용자의 컴퓨터에 장치가 연결되면 운영체제는 INF를 보고 어떤 드라이버를 연결할지 판단한다. 



INF는 섹션으로 구분해서 드라이버 정보를 기술한다. 섹션은 [ ] 로 감싸서 구분한다. 



> #### [ Version ] 섹션 

어떤 포멧의 내용을 서술하게 되는지 설명한다.



**Signature** 

`$WINDOWS NT` : NT 계열의 운영체제만 지원한다. 즉 요즘 운영체제를 지원한다는 의미이다. 

`$WINDOWS 95` : 9X와 ME 계열 지원 

`$Chicago` : 모든 계열의 운영체제를 지원한다.

ex) Signature   = "$Windows NT$"



**Class=class-name** 

드라이버가 어떤 클래스를 지원하는 드라이버인지 나타낸다. 여기서 클래스는 장치 유형을 말한다. 

USB, 마우스 키보드 등등 에는 모두 시스템에서 정의한 클래스가 존재한다.  Class 는 대응되는 ClassGuid가 있는데 ClassGuid에 대응되야한다.  **운영체제에서 정의한 클래스가 아니라면  Install32 섹션을 반드시 기술해야한다.** 

ex) Class       = SCSIAdapter



**ClassGuid** 

Class와 대응되는 유일한 클래스를 나타내는 GUID이다. 클래스의 이름이 Printer라고 하면 사람들은 당연히 프린터 드라이버라고 생각하지겠지만 사실 새로 나온 하드웨어 이름이 마침 프린터였을 수 있는 것이다. 이런 경우가 있을 수 있기 때문에 classguid를 이용해서 정확하게 어떤 의미로 사용되는지 부연설명하는 기능을 한다. 랜덤한 값을 넣을 수도 있지만 **고유한 값을 넣는다면 ClassInstall32 그리고 AddHwClass를 정의해줘야한다.** 

ex) ClassGUID   = {4D36E97B-E325-11CE-BFC1-08002BE10318} 



**Provider** 

드라이버의 공급자이다. 

ex) Provider    = %PROVIDER%



**DriverVer** 

드라이버의 생성 날짜와 버전이다. 

ex) DriverVer = 03/26/2019,3.0.1.0



**CatalogFile = filename.cat** 

WHQL 인증 테스트를 통과한 드라이버는 카탈로그 파일을 가지게 되는데 인증을 받는 드라이버라면 반드시 기술되어야하는 지시자이다.  **64비트 운영체제에서는 인증 받지 못한 드라이버는 사용할 수가 없다.** 

ex) CatalogFile = xecuredisk.cat



> #### [ ClassInstall32 ] 섹션

새로운 클래스 유형을 추가할 때 필요한 정보를 담는다.



> #### [ SourceDisksNames ] 섹션  

드라이버 설치에 필요한 파일들의 볼륨명이나 저장되어 있는 위치를 나타낼 때 사용하는 섹션이다.

ex) 1 = %DISKNAME%,,,



> #### [ SourceDiskFiles ] 섹션 

SourceDisksNames 섹션이 정의한 내용을 어떤 파일에 적용할지를 기술한다. 



> #### [ DestinationDirs ] 섹션 

드라이버 설치 중 드라이버를 어디에 설치할 지를 기술하는 섹션이다. 정해져 있는 번호도 있다. 

10  : c드라이브의 윈도우

11 : 윈도우의 system32

12   윈도우의 system32\Drivers

1 : 절대 경로

file.sys=12 이런 식으로 쓴다.

ex) AdapterFiles = 12



> #### [ Manufacturer ] 섹션 

하나 또는 여러 장치를 지원하는 경우 제조사 아키텍처 운영체제 별로 어떤 장치를 지원할 것인지 정의할 수 있다. 

ex) %PROVIDER% = Models, NTx86, NTamd64



> [Strings] 섹션

설치 파일 내에서 사용할 문자열 정보를 정의한다. %문자열% 형식으로 되어있는 녀석들의 정보이다. 

ex)

PROVIDER    = "Perisoft"
DISKNAME    = "Install Disk"
ADAPTERNAME = "UVStor Adapter"
LOCATION    = "Virtual"                               ; displayed in device manager
HARDWARE_ID = "root\uvstor"



> [Models] 섹션

구체적으로 어떤 장치를 지원할 것인지를 기술하는 부분이다. Manufacturer 에서 기수란 내용이 Models 섹션이 된다. 

ex)

[Models.NTx86]
%ADAPTERNAME% = Driver, %HARDWARE_ID%
[Models.NTamd64]
%ADAPTERNAME% = Driver, %HARDWARE_ID%



> [DDInstall] 섹션 

설치할 내용을 구체적으로 기술한다. 



> [DDInstall] 섹션 지시자 

CopyFiles=@filename|file-list-section[,file-list-section]



DestinationDirs 섹션에서 정의한 파일 또는 섹션을 이곳에 기술한다. 어떤 드라이버가 설치될 것이지 결정한다. 



CopyINF=filename1.inf



드라이버 설치 과정 중에 복사해야할 INF 파일이 있는 경우 사용된다. 



Delfiles=file-list-section[,file-list-section]



드라이버 설치 중에 삭제할 파일이 있다면 사용된다. 



Renfiles=file-list-section[,file-list-seciton]

드라이버 설치 과정에서 파일 이름이 변경되어야하는 파일이다. 



DelReg=del-registry-section[,del-registry-section]

드라이버 설치 과정 중 삭제해야하는 레지스트리 키가 있다면 사용한다. 



HKCR - HKEY_CLASSES_ROOT

HKCU - HKEY_CURRENT_USER

HKLM - HKEY_LOCAL_MACHINE 

HKU - HKEY_USERS

HKR - 루트가 아닌 특정 경로를 나타낸다. 



LogConfig=log-config-section[,log-config-section]

드라이버 설치 과정에서 드라이버가 사용할 입출력 자원 및 속성에 대해 정의할 때 사용한다. 

구형에서 사용한다. 



AddReg=add-registry-section

드라이버가 설치될 때 생성해야할 레지스트리가 있다면 기술한다. 장치가 연결되었을 때 어떻게 동작할 것인지 초기화하는데 사용한다. 



DDInstall.Service 섹션 

이 것은 드라이버에서 사용하는 서비스에 대한 내용을 정의한다. 



---

위에 언급되지 않은 내용.

>  [Driver]

CopyFiles = AdapterFiles
AddReg    = AdapterRegistry

> [AdapterFiles]

uvstor.sys

> [AdapterRegistry]

HKR,,LocationInformationOverride,,%LOCATION%

>  [Driver.Services]

AddService = uvstor_adapter, 2, AdapterService        ; SPSVCINST_ASSOCSERVICE

>  [AdapterService]

ServiceType   = 1                                     ; SERVICE_KERNEL_DRIVER
StartType     = 3                                     ; SERVICE_DEMAND_START
ErrorControl  = 0                                     ; SERVICE_ERROR_IGNORE
ServiceBinary = %12%\uvstor.sys
AddReg        = AdapterServiceRegistry

>  [AdapterServiceRegistry]

HKR, "Parameters", "BusType", 0x00010001, 0x0e        ; BusTypeVirtual