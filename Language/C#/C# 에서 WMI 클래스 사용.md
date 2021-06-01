# C# 에서 WMI 클래스 사용

출처: https://www.dreamincode.net/forums/topic/42934-using-wmi-class-in-c%23/

이 문서는 disk latter(C:, D: 등등) 를 이용하여 disk no( 0, 1, 2) 등을 취득 하기 위한 자료 조사중 발견한 문서 입니다.

> disk latter 는 일반적인 경우 C: 가 0 으로 시작 하지만 C: 이외의 경우는 Disk Tool 등을 이용하여 Drive Letter 를 마음대로 변경 할 수 있기 때문에  ```'C:' - letter``` 의 공식이 성립 하지 않습니다.
>
> 역자의 경우 현재 환경에서는 K: 가 1 로 표기 됩니다. 1 번 disk 를 K: 로 할당 하였음.



C# 에서 WMI 사용에 대한 자습서에 오신 것을 환영합니다. 이 자습서에서는 WMI ([Windows Management Instrumentation](http://msdn2.microsoft.com/en-us/library/aa394582.aspx)) 사용에 대해 설명합니다. WMI (Windows Management Instrumentation)는 Windows 기반 운영 체제의 관리 데이터 및 운영을위한 인프라입니다. WMI를 사용하면 다음을 포함하여 시스템의 모든 하드웨어에 대한 정보를 얻을 수 있습니다.

- HDD Serial Number(s)
- HDD Sizes
- HDD Free Space
- CPU Serial Number(s)
- CPU Clock Speed
- CPU Socket Type
- Network Adapter MAC Address
- Network Adapter Default Gateway

이 자습서에서는 WMI를 사용하여 [Windows 쿼리 언어](https://en.wikipedia.org/wiki/WQL) 또는 WQL과 함께 검색 할 수있는 일부 정보를 살펴 봅니다. 이 자습서는 이 주제에 대한 포괄적인 자습서는 아니지만 최소한 응용 프로그램에서 WMI를 사용하기위한 훌륭한 기초를 제공하기에 충분할 것입니다.

이 자습서에서는 HDD 정보, CPU 정보 및 네트워크 어댑터 정보를 검색하는 방법을 살펴 봅니다. 이 학습서에는 애플리케이션에 대한 이 정보를 검색하는 데 사용할 3 개의 클래스가 있습니다.

[Win32_LogicalDisk Class](http://msdn2.microsoft.com/en-us/library/aa394173(VS.85).aspx)

[Win32_NetworkAdapterConfiguration Class](http://msdn2.microsoft.com/en-us/library/aa394217(VS.85).aspx)

[Win32_Processor Class](http://msdn2.microsoft.com/en-us/library/aa394373.aspx)

물론 더 많은 [WMI 클래스](http://msdn2.microsoft.com/en-us/library/aa394554(VS.85).aspx)를 사용할 수 있으며, 모든 WMI 클래스를 시도하고 다루는 경우 20 페이지 자습서가 될 것입니다. 우리가 사용할 모든 항목에는 .Net 프레임 워크에 제공된 System.Management Namsepace에 대한 참조가 필요합니다.



### **Win32_LogicalDiskClass**

Win32_LogicalDisk WMI 클래스는 Windows를 실행하는 컴퓨터 시스템의 실제 로컬 저장 장치로 확인되는 데이터 소스를 나타냅니다. 이 섹션에서는 다음과 같은 몇 가지 작업을 수행합니다.

- 선택한 HDD 일련 번호 검색
- 선택한 HDD 여유 공간을 검색합니다.
- HDD 초기 크기 검색

첫 번째 작업은 선택된 HDD의 일련 번호를 얻는 것입니다 (이 섹션의 모든 작업과 마찬가지로) 드라이브 문자를 메소드에 전달할 것입니다.이 드라이브 문자와 함께 Win32_LogicalDisk 클래스의 DeviceID 속성을 사용하여 시스템을 쿼리합니다 . 이 작업을 마치면이 섹션에서 사용할 많은 속성에 액세스 할 수 있습니다. 먼저 일련 번호를 받으십시오.

```c#
/// <summary>
/// method to retrieve the selected HDD's serial number
/// </summary>
/// <param name="strDriveLetter">Drive letter to retrieve serial number for</param>
/// <returns>the HDD's serial number</returns>
public string GetHDDSerialNumber(string drive)
{
	//check to see if the user provided a drive letter
	//if not default it to "C"
	if (drive == "" || drive == null)
	{
		drive = "C";
	}
	//create our ManagementObject, passing it the drive letter to the
	//DevideID using WQL
	ManagementObject disk = new ManagementObject("Win32_LogicalDisk.DeviceID=\"" + drive + ":\"");
	//bind our management object
	disk.Get();
	//return the serial number
	return disk["VolumeSerialNumber"].ToString();
}

```

보시다시피, 우리는 적절한 HDD를보고 있는지 확인하기 위해 DevideID를 확인한 다음 ManagementObject 클래스의 Get 메소드를 사용하여 관리 객체를 바인딩하여 찾고있는 속성을 검색 할 수 있습니다.

Win32_LogicalDisk 클래스의 장점은 한 속성이나 속성을 검색하는 방법을 배우면 HDD의 여유 공간을 검색하는 등 나머지 속성을 쉽게 검색 할 수 있다는 것입니다.

```c#
/// <summary>
/// method to retrieve the HDD's freespace
/// </summary>
/// <param name="drive">Drive letter to get free space from (optional)</param>
/// <returns>The free space of the selected HDD</returns>
public double GetHDDFreeSpace(string drive)
{
	//check to see if the user provided a drive letter
	//if not default it to "C"
	if (drive == "" || drive == null)
	{
		drive = "C";
	}
	//create our ManagementObject, passing it the drive letter to the
	//DevideID using WQL
	ManagementObject disk = new ManagementObject("Win32_LogicalDisk.DeviceID=\"" + drive + ":\"");
	//bind our management object
	disk.Get();
	//return the free space amount
	return Convert.ToDouble(disk["FreeSpace"]);
}

```



그리고 다음과 같이 선택한 HDD의 초기 크기를 검색합니다.

```c#
/// <summary>
/// method to retrieve the HDD's size
/// </summary>
/// <param name="drive">Drive letter to get free space from (optional)</param>
/// <returns>The free space of the selected HDD</returns>
public double getHDDSize(string drive)
{
	//check to see if the user provided a drive letter
	//if not default it to "C"
	if (drive == "" || drive == null)
	{
		drive = "C";
	}
	//create our ManagementObject, passing it the drive letter to the
	//DevideID using WQL
	ManagementObject disk = new ManagementObject("Win32_LogicalDisk.DeviceID=\"" + drive + ":\"");
	//bind our management object
	disk.Get();
	//return the HDD's initial size
	return Convert.ToDouble(disk["Size"]);
}

```



앞의 3 가지 방법에서 드라이브 문자는 실제로 선택 사항입니다. 우리는 드라이브가 제공되었는지 확인하고 그렇지 않은 경우 일반적으로 대부분의 컴퓨터에서 OS 드라이브 인 사용자 'C'드라이브로 기본 설정합니다.



### **Win32_NetworkAdapterConfiguration Class**

Win32_NetworkAdapterConfiguration WMI 클래스는 네트워크 어댑터의 특성과 동작을 나타냅니다. 이 클래스에는 TCP / IP 및 IPX (Internetwork Packet Exchange) 프로토콜 관리를 지원하는 추가 속성 및 메서드가 포함됩니다.

먼저 시스템 MAC 주소를 검색하는 방법을 살펴 보겠습니다. 이 예에서 우리는 찾은 첫 번째 네트워크 어댑터에서 정보를 검색 할 것입니다. 네트워크 어댑터 정보 모음을 만들기 위해 쉽게 수정할 수 있습니다.

```c#
/// <summary>
/// Returns MAC Address from first Network Card in Computer
/// </summary>
/// <returns>MAC Address in string format</returns>
public string FindMACAddress()
{
	//create out management class object using the
	//Win32_NetworkAdapterConfiguration class to get the attributes
	//of the network adapter
	ManagementClass mgmt = new ManagementClass("Win32_NetworkAdapterConfiguration");
	//create our ManagementObjectCollection to get the attributes with
	ManagementObjectCollection objCol = mgmt.GetInstances();
	string address = String.Empty;
	//loop through all the objects we find
	foreach (ManagementObject obj in objCol)
	{
		if (address == String.Empty)  // only return MAC Address from first card
		{
			//grab the value from the first network adapter we find
			//you can change the string to an array and get all
			//network adapters found as well
			//check to see if the adapter's IPEnabled
			//equals true
			if ((bool)obj["IPEnabled"] == true)
			{
				address = obj["MacAddress"].ToString();
			}
		}
		//dispose of our object
		obj.Dispose();
	}
	//replace the ":" with an empty space, this could also
	//be removed if you wish
	address = address.Replace(":", "");
	//return the mac address
	return address;
}

```

첫 번째 예에서는이 섹션의 다른 예제와 함께 ManagementObjectCollection 클래스의 GetInstances 메서드를 사용하여 지정된 클래스의 모든 인스턴스 (이 경우 Win32_NetworkAdapterConfiguration 클래스)를 검색합니다.

이제 네트워크 어댑터의 기본 IP 게이트웨이 검색을 살펴 보겠습니다. 첫 번째 예제와 마찬가지로 IPEnabled 속성이 true 인 경우 찾은 첫 번째 어댑터를 사용합니다.

```c#
/// <summary>
/// method to retrieve the network adapters
/// default IP gateway using WMI
/// </summary>
/// <returns>adapters default IP gateway</returns>
public string GetDefaultIPGateway()
{
	//create out management class object using the
	//Win32_NetworkAdapterConfiguration class to get the attributes
	//of the network adapter
	ManagementClass mgmt = new ManagementClass("Win32_NetworkAdapterConfiguration");
	//create our ManagementObjectCollection to get the attributes with
	ManagementObjectCollection objCol = mgmt.GetInstances();
	string gateway = String.Empty;
	//loop through all the objects we find
	foreach (ManagementObject obj in objCol)
	{
		if (gateway == String.Empty)  // only return MAC Address from first card
		{
			//grab the value from the first network adapter we find
			//you can change the string to an array and get all
			//network adapters found as well
			//check to see if the adapter's IPEnabled
			//equals true
			if ((bool)obj["IPEnabled"] == true)
			{
				gateway = obj["DefaultIPGateway"].ToString();
			}
		}
		//dispose of our object
		obj.Dispose();
	}
	//replace the ":" with an empty space, this could also
	//be removed if you wish
	gateway = gateway.Replace(":", "");
	//return the mac address
	return gateway;
}

```



### **Win32_Processor**

이 클래스를 사용하면 Windows 기반 컴퓨터에서 실행중인 프로세서의 속성을 검색 할 수 있습니다. 프로세서가 여러 개인 시스템에서는 각 프로세서마다 Win32_Processor 클래스의 인스턴스 하나가 존재합니다. 이 클래스를 사용하면 다음과 같은 프로세서에 대한 정보를 검색 할 수 있습니다.

- CPU ID
- CPU Manufacturer
- CPU Status
- CPU Current Clock Speed

다른 클래스 예제와 마찬가지로, 우리는 찾은 첫 번째 프로세서에서만 정보를 얻습니다. 또한 시스템의 모든 프로세서에 대한 정보를 반환하도록 수정할 수도 있습니다. 이 예에서는 각 프로세서마다 고유 한 인스턴스가 있으므로 GetInstances 메서드도 사용합니다. 먼저 CPU ID를 얻는다 :

```c#
/// <summary>
/// Return processorId from first CPU in machine
/// </summary>
/// <returns>[string] ProcessorId</returns>
public string GetCPUId()
{
	string cpuInfo =  String.Empty;			
	//create an instance of the Managemnet class with the
	//Win32_Processor class
	ManagementClass mgmt = new ManagementClass("Win32_Processor");
	//create a ManagementObjectCollection to loop through
	ManagementObjectCollection objCol = mgmt.GetInstances();
	//start our loop for all processors found
	foreach(ManagementObject obj in objCol)
	{
		if(cpuInfo == String.Empty) 
		{
			// only return cpuInfo from first CPU
			cpuInfo = obj.Properties["ProcessorId"].Value.ToString();
		}			 
	}
	return cpuInfo;		
}

```



제조업체를 얻기 위해 찾은 첫 번째 인스턴스의 Manufacturer 속성을 반환합니다.

```c#
/// <summary>
/// method for retrieving the CPU Manufacturer
/// using the WMI class
/// </summary>
/// <returns>CPU Manufacturer</returns>
public string GetCPUManufacturer()
{
	string cpuMan = String.Empty;
	//create an instance of the Managemnet class with the
	//Win32_Processor class
	ManagementClass mgmt = new ManagementClass("Win32_Processor");
	//create a ManagementObjectCollection to loop through
	ManagementObjectCollection objCol = mgmt.GetInstances();
	//start our loop for all processors found
	foreach (ManagementObject obj in objCol)
	{
		if (cpuMan == String.Empty)
		{
			// only return manufacturer from first CPU
			cpuMan = obj.Properties["Manufacturer"].Value.ToString();
		}
	}
	return cpuMan;		
}

```



오늘날 프로세서는 20 년 전과 크게 다르지만 오늘날 사람들은 프로세서를 더 많이 사용하기 위해 프로세서를 클럭킹하고 있습니다. 이 경우 일부 시스템은 시스템 정보에 표시된 것보다 높은 클럭 속도로 실행 중일 수 있으므로 CurrentClockSpeed 속성을 사용하여 찾은 첫 번째 인스턴스의 현재 클럭 속도를 검색합니다.

```c#
/// <summary>
/// method to retrieve the CPU's current
/// clock speed using the WMI class
/// </summary>
/// <returns>Clock speed</returns>
public int GetCPUCurrentClockSpeed()
{
	int cpuClockSpeed = 0;
	//create an instance of the Managemnet class with the
	//Win32_Processor class
	ManagementClass mgmt = new ManagementClass("Win32_Processor");
	//create a ManagementObjectCollection to loop through
	ManagementObjectCollection objCol = mgmt.GetInstances();
	//start our loop for all processors found
	foreach (ManagementObject obj in objCol)
	{
		if (cpuClockSpeed == 0)
		{
			// only return cpuStatus from first CPU
			cpuClockSpeed = Convert.ToInt32(obj.Properties["CurrentClockSpeed"].Value.ToString());
		}
	}
	//return the status
	return cpuClockSpeed;	
}
```

앞에서 언급했듯이 WMI 클래스를 사용하는 방법에 대한 전체 목록이 이 클래스를 사용하여 시스템에 대해 검색 할 수있는 수백 가지 항목이있을 수 있습니다. 이 자습서의 항목과 첨부 된 클래스 파일은 응용 프로그램에서 WMI 클래스를 사용하는 데 도움이됩니다.

이 튜토리얼을 위해 내가 만든 클래스 파일을 포함시킬 것입니다. 클래스 자체는 GNU General Public License하에 있습니다. 즉, 원하는대로 수정하고 배포 할 수 있지만 라이센스 정보는 그대로 유지해야합니다. 이 튜토리얼이 유용하고 유익한 정보가 되셨기를 바랍니다. 읽어 주셔서 감사합니다.

Happy Coding!
[![Attached File](https://www.dreamincode.net/forums/public/style_extra/mime_types/zip.gif)](https://www.dreamincode.net/forums/index.php?app=core&module=attach&section=attach&attach_id=5986) [PC_WMI.zip](https://www.dreamincode.net/forums/index.php?app=core&module=attach&section=attach&attach_id=5986) **(13K)**



### 다른 site 참고 자료

```c#
using System.Management;

//CPU 이름 얻기
ManagementObjectSearcher win32Proc = new ManagementObjectSearcher("select * from Win32_Processor");

foreach (ManagementObject obj in win32Proc.Get())
{
    string procName = obj["Name"].ToString();
}

//GRAPHIC 카드 이름 얻기
ManagementObjectSearcher win32Display = new ManagementObjectSearcher("select * from Win32_DisplayConfiguration")

foreach (ManagementObject obj in win32Display.Get())
{
    string grpName = obj["Description"].ToString();
}

//메모리 용량 얻기
ManagementObjectSearcher win32CompSys = new ManagementObjectSearcher("select * from Win32_ComputerSystem")

foreach (ManagementObject obj in win32CompSys.Get())
{
    string memName = obj["totalphysicalmemory"].ToString();
}
```





