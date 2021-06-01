# Installing a Minifilter Driver

출처: https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/installing-a-minifilter-driver



Microsoft Windows XP 이상의 운영 체제의 경우 INF 파일과 설치 응용 프로그램을 사용하여 미니 필터 드라이버를 설치해야합니다. (Windows 2000 및 이전 운영 체제에서 미니 필터 드라이버는 일반적으로 Service Control Manager에 의해 설치되었습니다.)

향후 미니 필터 드라이버에 대한 Windows 하드웨어 인증 키트 요구 사항을 충족하려면 INF 기반 설치가 필요합니다. 'INF 기반 설치'는 파일을 복사하고 레지스트리에 정보를 저장하기 위해 INF 파일을 사용해야한다는 것을 의미합니다. INF 파일 만 사용하여 전체 제품을 설치할 필요는 없으며 드라이버에 대해 '오른쪽 클릭 설치'옵션을 제공하지 않아도됩니다.

이 섹션에는 다음이 포함됩니다.

[Creating an INF File for a Minifilter Driver](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/creating-an-inf-file-for-a-minifilter-driver)

[Load Order Groups and Altitudes for Minifilter Drivers](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/load-order-groups-and-altitudes-for-minifilter-drivers)

[Allocated Altitudes](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/allocated-altitudes)

[Minifilter Altitude Request](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/minifilter-altitude-request)

[Reparse Point Tag Request](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/reparse-point-tag-request)



## Creating an INF File for a Minifilter Driver

파일 시스템 미니 필터 드라이버의 INF 파일에는 일반적으로 다음 섹션이 포함되어 있습니다.

- Version (required)

- DestinationDirs (optional but recommended)

- DefaultInstall (required)

- DefaultInstall.Services (required)

- ServiceInstall (required)

- AddRegistry (required)

- DefaultUninstall (optional)

- DefaultUninstall.Services (optional)

- Strings (required)

**참고** x64 기반 Windows Vista 시스템부터는 파일 시스템 드라이버 (파일 시스템, 레거시 필터 및 미니 필터 드라이버)와 같은 비 PnP (플러그 앤 플레이) 드라이버를 포함한 모든 커널 모드 구성 요소에 서명해야합니다.  이 시나리오의 경우 다음 목록에는 파일 시스템 드라이버와 관련된 정보가 포함되어 있습니다.

- 파일 시스템 드라이버를 포함하여 비 PnP 드라이버 용 INF 파일에는 [제조업체] 또는 [모델] 섹션이 포함되어 있지 않아도됩니다.
- WDK 설치 디렉토리의 \\bin\\SelfSign 디렉토리에있는 SignTool 명령 행 도구를 사용하여 드라이버 SYS 실행 파일을 직접 '삽입'할 수 있습니다. 성능상의 이유로 부팅 시작 드라이버에는 서명이 포함되어 있어야합니다.
- INF 파일이 제공되면 Inf2cat 명령 줄 도구를 사용하여 드라이버 패키지의 카탈로그 (.cat) 파일을 만들 수 있습니다. 카탈로그 파일 만 WHQL 로고 서명을받을 수 있습니다.
- 관리자 권한으로 서명되지 않은 드라이버를 Windows Vista부터 x64 기반 시스템에 설치할 수 있습니다. 그러나 드라이버는 서명되지 않았기 때문에로드 (및 실행)에 실패합니다.
- 드라이버 서명에 대한 일반적인 정보는 [드라이버 서명](https://docs.microsoft.com/windows-hardware/drivers/install/driver-signing)을 참조하십시오.
- Driver 서명 프로세스에 대한 자세한 내용은 [커널 모드 코드 서명 연습](https://go.microsoft.com/fwlink/p/?linkid=79445)을 참조하십시오.
- 사용자 정의 커널 모드 개발 도구를 포함한 모든 커널 모드 구성 요소에 서명해야합니다. 자세한 내용은 [개발 및 테스트 중 드라이버 서명 (Windows Vista 이상)](https://docs.microsoft.com/windows-hardware/drivers/install/signing-drivers-during-development-and-test--windows-vista-and-later-)을 참조하십시오.



### Version Section (required)

버전 섹션은 다음 코드 예제와 같이 미니 필터 드라이버 유형에 따라 결정되는 클래스 및 GUID를 지정합니다.

```c++
[Version]
Signature   = "$WINDOWS NT$"
Class       = "ActivityMonitor"
ClassGuid   = {b86dff51-a31e-4bac-b3cf-e8cfe75c9fc2}
Provider    = %Msft%
DriverVer   = 10/09/2001,1.0.0.0
CatalogFile =
```

다음 표는 파일 시스템 미니 필터 드라이버가 버전 섹션에서 지정해야하는 값을 보여줍니다.

| Entry           | Value                                                        |
| --------------- | ------------------------------------------------------------ |
| **Signature**   | "$WINDOWS NT$"                                               |
| **Class**       | See [File System Filter Driver Classes and Class GUIDs](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/file-system-filter-driver-classes-and-class-guids). |
| **ClassGuid**   | See [File System Filter Driver Classes and Class GUIDs](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/file-system-filter-driver-classes-and-class-guids). |
| **Provider**    | 자신의 INF 파일에서 Microsoft 이외의 공급자를 지정해야합니다. |
| **DriverVer**   | See [**INF DriverVer directive**](https://docs.microsoft.com/windows-hardware/drivers/install/inf-driverver-directive). |
| **CatalogFile** | 서명된 안티 바이러스 미니 필터 드라이버의 경우 이 항목에는 WHQL 제공 카탈로그 파일의 이름이 포함됩니다. 다른 모든 미니 필터 드라이버는 이 항목을 비워 두어야합니다. 자세한 정보는 [INF 버전 섹션](https://docs.microsoft.com/windows-hardware/drivers/install/inf-version-section)의 CatalogFile 항목에 대한 설명을 참조하십시오. |



### DestinationDirs Section (optional but recommended)

DestinationDirs 섹션은 미니 필터 드라이버 및 응용 프로그램 파일이 복사 될 디렉토리를 지정합니다.

이 섹션과 ServiceInstall 섹션에서 시스템 정의 숫자 값으로 잘 알려진 시스템 디렉토리를 지정할 수 있습니다. 이러한 값의 목록은[INF DestinationDirs 섹션](https://docs.microsoft.com/windows-hardware/drivers/install/inf-destinationdirs-section)을 참조하십시오. 다음 코드 예제에서 값 12는 Drivers 디렉토리 (%windir%\\system32\\drivers)를 나타내고 값 10은 Windows 디렉토리 (%windir%)를 나타냅니다.

```c++
[DestinationDirs]
DefaultDestDir = 12
Minispy.DriverFiles = 12
Minispy.UserFiles   = 10,FltMgr
```



### DefaultInstall Section (required)

DefaultInstall 섹션에서 CopyFiles 지시문은 미니 필터 드라이버의 드라이버 파일 및 사용자 응용 프로그램 파일을 DestinationDirs 섹션에 지정된 대상으로 복사합니다.

**참고** : CopyFiles 지시문은 카탈로그 파일 또는 INF 파일 자체를 참조하지 않아야합니다. SetupAPI는 이러한 파일을 자동으로 복사합니다.

단일 INF 파일을 만들어 여러 버전의 Windows 운영 체제에 드라이버를 설치할 수 있습니다. 각 운영 체제 버전에 대해 추가 DefaultInstall, DefaultInstall.Services, DefaultUninstall 및 DefaultUninstall.Services 섹션을 작성하여이 유형의 INF 파일을 작성할 수 있습니다. 각 섹션에는 적용 할 운영 체제 버전을 지정하는 데코레이션 (예 : .ntx86, .ntia64 또는 .nt)이 표시됩니다. 이 유형의 INF 파일 작성에 대한 자세한 정보는 [다중 플랫폼 및 운영 체제에 대한 INF 파일 작성](https://docs.microsoft.com/windows-hardware/drivers/install/creating-inf-files-for-multiple-platforms-and-operating-systems)을 참조하십시오.

다음 코드 예제는 일반적인 DefaultInstall 섹션을 보여줍니다.

```c++
[DefaultInstall]
OptionDesc = %MinispyServiceDesc%
CopyFiles = Minispy.DriverFiles, Minispy.UserFiles
```



### DefaultInstall.Services Section (required)

DefaultInstall.Services 섹션에는 다음 코드 예제와 같이 특정 드라이버의 서비스가 로드되는 방법과시기를 제어하는 AddService 지시문이 포함되어 있습니다.

```c++
[DefaultInstall.Services]
AddService = %MinispyServiceName%,,Minispy.Service
```



### ServiceInstall Section (required)

ServiceInstall 섹션에는 드라이버 서비스를로드하는 데 사용되는 정보가 포함되어 있습니다. MiniSpy 샘플 드라이버에서 이 섹션의 이름은 다음 코드 예제와 같이 'Minispy.Service'입니다. ServiceInstall 섹션의 이름은 DefaultInstall.Services 섹션의 AddService 지시문에 나타나야합니다.

```c++
[Minispy.Service]
DisplayName    = %MinispyServiceName%
Description    = %MinispyServiceDesc%
ServiceBinary  = %12%\minispy.sys
ServiceType    = 2 ;    SERVICE_FILE_SYSTEM_DRIVER
StartType      = 3 ;    SERVICE_DEMAND_START
ErrorControl   = 1 ;    SERVICE_ERROR_NORMAL%
LoadOrderGroup = "FSFilter Activity Monitor"
AddReg         = Minispy.AddRegistry
Dependencies   = FltMgr
```

ServiceType 항목은 서비스 유형을 지정합니다. 미니 필터 드라이버는 값 2 (SERVICE_FILE_SYSTEM_DRIVER)를 지정해야합니다. ServiceType 항목에 대한 자세한 내용은[ INF AddService 지시문](https://docs.microsoft.com/windows-hardware/drivers/install/inf-addservice-directive)을 참조하십시오.

StartType 항목은 서비스 시작시기를 지정합니다. 다음 표는 StartType의 가능한 값과 해당 시작 유형을 나열합니다.

| Value      | Description          |
| ---------- | -------------------- |
| 0x00000000 | SERVICE_BOOT_START   |
| 0x00000001 | SERVICE_SYSTEM_START |
| 0x00000002 | SERVICE_AUTO_START   |
| 0x00000003 | SERVICE_DEMAND_START |
| 0x00000004 | SERVICE_DISABLED     |

이러한 시작 유형에 대한 자세한 내용은 [드라이버로드 시기 결정](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/what-determines-when-a-driver-is-loaded)의 '드라이버 시작 유형'을 참조하십시오.

LoadOrderGroup 항목은 필터 관리자에게 미니 필터 드라이버와 레거시 파일 시스템 필터 드라이버 간의 상호 운용성을 보장하는 데 필요한 정보를 제공합니다. 개발중인 미니 필터 드라이버 유형에 적합한 LoadOrderGroup 값을 지정해야합니다. 로드 순서 그룹을 선택하려면 미니 필터 드라이버의로드 순서 그룹 및 고도를 참조하십시오.

미니 필터 드라이버의 시작 유형이 SERVICE_BOOT_START가 아닌 경우에도 LoadOrderGroup 값을 지정해야합니다. 이런 식으로 미니 필터 드라이버는 레거시 파일 시스템 필터 드라이버와 다릅니다.

참고 필터 관리자의 StartType 값은 SERVICE_BOOT_START이고 LoadOrderGroup 값은 FSFilter Infrastructure입니다. 이 값은 미니 필터 드라이버가로드되기 전에 항상 필터 관리자가로드되도록합니다.

StartType 및 LoadOrderGroup 항목이 드라이버로드시기를 결정하는 방법에 대한 자세한 내용은 [드라이버로드시기 결정](https://docs.microsoft.com/en-us/windows-hardware/drivers/ifs/what-determines-when-a-driver-is-loaded)을 참조하십시오.

**참고** 미니 필터 드라이버의 경우 레거시 파일 시스템 필터 드라이버와 달리 StartType 및 LoadOrderGroup 값은 미니 필터 드라이버가 미니 필터 인스턴스 스택에서 연결되는 위치를 결정하지 않습니다. 이 위치는 미니 필터 인스턴스에 지정된 고도에 따라 결정됩니다.

ErrorControl 항목은 시스템 시작 중에 서비스를 시작하지 못한 경우 수행 할 작업을 지정합니다. 미니 필터 드라이버는 1 (SERVICE_ERROR_NORMAL) 값을 지정해야합니다. ErrorControl 항목에 대한 자세한 내용은[ INF AddService 지시문](https://docs.microsoft.com/windows-hardware/drivers/install/inf-addservice-directive)을 참조하십시오.

AddReg 지시문은 새로 설치된 서비스의 레지스트리에 저장 될 정보를 포함하는 하나 이상의 INF 기록기 정의 AddRegistry 섹션을 나타냅니다. 미니 필터 드라이버는 AddRegistry 섹션을 사용하여 미니 필터 드라이버 인스턴스를 정의하고 기본 인스턴스를 지정합니다.

종속성 항목은 드라이버가 의존하는 서비스 또는로드 순서 그룹의 이름을 지정합니다. 모든 미니 필터 드라이버는 필터 관리자의 서비스 이름 인 FltMgr을 지정해야합니다.



### AddRegistry Section (required)

AddRegistry 섹션은 키와 값을 레지스트리에 추가합니다. 미니 필터 드라이버는 AddRegistry 섹션을 사용하여 미니 필터 인스턴스를 정의하고 기본 인스턴스를 지정합니다. 이 정보는 필터 관리자가 미니 필터 드라이버의 새 인스턴스를 작성할 때마다 사용됩니다.

MiniSpy 샘플 드라이버에서 다음 AddRegistry 섹션과 Strings 섹션의 %strkey% 토큰 정의와 함께 3 개의 인스턴스를 정의합니다. 그 중 하나는 MiniSpy 샘플 드라이버의 기본 인스턴스로 명명됩니다.

```c++
[Minispy.AddRegistry]
HKR,%RegInstancesSubkeyName%,%RegDefaultInstanceValueName%,0x00000000,%DefaultInstance%
HKR,%RegInstancesSubkeyName%"\"%Instance1.Name%,%RegAltitudeValueName%,0x00000000,%Instance1.Altitude%
HKR,%RegInstancesSubkeyName%"\"%Instance1.Name%,%RegFlagsValueName%,0x00010001,%Instance1.Flags%
HKR,%RegInstancesSubkeyName%"\"%Instance2.Name%,%RegAltitudeValueName%,0x00000000,%Instance2.Altitude%
HKR,%RegInstancesSubkeyName%"\"%Instance2.Name%,%RegFlagsValueName%,0x00010001,%Instance2.Flags%
HKR,%RegInstancesSubkeyName%"\"%Instance3.Name%,%RegAltitudeValueName%,0x00000000,%Instance3.Altitude%
HKR,%RegInstancesSubkeyName%"\"%Instance3.Name%,%RegFlagsValueName%,0x00010001,%Instance3.Flags%
```



### DefaultUninstall Section (optional)

DefaultUninstall 섹션은 선택 사항이지만 드라이버를 제거 할 수있는 경우 권장됩니다. 다음 코드 예제와 같이 파일 및 레지스트리 항목을 제거하기위한 DelFiles 및 DelReg 지시문이 포함되어 있습니다.

```c++
[DefaultUninstall]
DelFiles   = Minispy.DriverFiles, Minispy.UserFiles
DelReg     = Minispy.DelRegistry
```



### DefaultUninstall.Services Section (optional)

DefaultUninstall.Services 섹션은 선택 사항이지만 드라이버를 제거 할 수있는 경우 권장됩니다. MiniSpy 샘플 드라이버에서 다음 코드 예제와 같이 미니 필터 드라이버의 서비스를 제거하기위한 DelService 지시문이 포함되어 있습니다.

**참고** DelService 지시문은 항상 서비스를 삭제하기 전에 SPSVCINST_STOPSERVICE 플래그 (0x00000200)를 지정해야합니다.

```c++
[DefaultUninstall.Services]
DelService = Minispy,0x200
```



### Strings Section (required)

문자열 섹션은 INF 파일에서 사용되는 각 %strkey% 토큰을 정의합니다.

INF 파일에 추가 로캘 별 Strings.LanguageID 섹션을 만들어 단일 국제 INF 파일을 만들 수 있습니다. 국제 INF 파일에 대한 자세한 내용은 [국제 INF 파일 만들기](https://docs.microsoft.com/windows-hardware/drivers/install/creating-international-inf-files)를 참조하십시오.

다음 코드 예제는 일반적인 문자열 섹션을 보여줍니다.

```c++
[Strings]
Msft               = "Microsoft Corporation"
MinispyServiceDesc = "Minispy mini-filter driver"
MinispyServiceName = "Minispy"
RegInstancesSubkeyName = "Instances"
RegDefaultInstanceValueName  = "DefaultInstance"
RegAltitudeValueName    = "Altitude"
RegFlagsValueName  = "Flags"

DefaultInstance    = "Minispy - Top Instance"
Instance1.Name     = "Minispy - Middle Instance"
Instance1.Altitude = "370000"
Instance1.Flags    = 0x1 ; Suppress automatic attachments
Instance2.Name     = "Minispy - Bottom Instance"
Instance2.Altitude = "365000"
Instance2.Flags    = 0x1 ; Suppress automatic attachments
Instance3.Name     = "Minispy - Top Instance"
Instance3.Altitude = "385000"
Instance3.Flags    = 0x1 ; Suppress automatic attachments
```





## Load Order Groups and Altitudes for Minifilter Drivers





## Allocated Altitudes





## Minifilter Altitude Request





## Reparse Point Tag Request



