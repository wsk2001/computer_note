# SMBv1은 Windows 10 버전 1709 이상 버전에서 기본적으로 설치되지 않습니다.

출처: https://learn.microsoft.com/ko-KR/windows-server/storage/file-server/troubleshoot/smbv1-not-installed-by-default-in-windows



## 요약

Windows 10 Fall Creators Update 및 Windows Server 버전 1709(RS3) 이후 SMBv1(서버 메시지 블록 버전 1) 네트워크 프로토콜은 더 이상 기본적으로 설치되지 않습니다. 2007년부터 SMBv2 이상 프로토콜로 대체되었습니다. Microsoft는 2014년에 SMBv1 프로토콜을 공개적으로 사용 중단했습니다.

SMBv1은 Windows 10 및 Windows Server 2019 이상 버전에서 다음과 같은 동작을 수행합니다.

- 이제 SMBv1에는 별도로 제거할 수 있는 클라이언트 및 서버 하위 기능이 모두 있습니다.
- Windows 10 Enterprise, Windows 10 Education 및 Windows 10 Pro for Workstations는 클린 설치 후 기본적으로 SMBv1 클라이언트 또는 서버를 더 이상 포함하지 않습니다.
- Windows Server 2019 이상에서는 클린 설치 후 기본적으로 SMBv1 클라이언트 또는 서버를 더 이상 포함하지 않습니다.
- Windows 10 Home 및 Windows 10 Pro는 클린 설치 후 기본적으로 SMBv1 서버를 더 이상 포함하지 않습니다.
- Windows 11은 클린 설치 후 기본적으로 SMBv1 서버 또는 클라이언트를 포함하지 않습니다.
- Windows 10 Home 및 Windows 10 Pro는 클린 설치 후에도 기본적으로 SMBv1 클라이언트를 포함합니다. SMBv1 클라이언트가 총 15일 동안 사용되지 않으면(꺼진 컴퓨터 제외) 자동으로 제거됩니다.
- Windows 10 Home 및 Windows 10 Pro의 내부 업그레이드 및 참가자 항공편은 처음에 SMBv1을 자동으로 제거하지 않습니다. <span style="color:red">**Windows는 SMBv1 클라이언트 및 서버의 사용량을 평가하고, 둘 중 하나가 총 15일 동안 사용되지 않는 경우(컴퓨터가 꺼져 있는 시간 제외) Windows에서 자동으로 제거합니다.**</span>
- Windows 10 Enterprise, Windows 10 Education 및 Windows 10 Pro for Workstations 버전의 내부 업그레이드 및 참가자 항공편은 SMBv1을 자동으로 제거하지 않습니다. 관리자는 이러한 관리되는 환경에서 SMBv1을 제거하도록 결정해야 합니다.
- <span style="color:red">**15일 후 SMBv1 자동 제거는 일회성 작업입니다. 관리자가 SMBv1을 다시 설치하는 경우 더 이상 SMBv1을 제거하려고 시도하지 않습니다.**</span>
- SMB 버전 2.02, 2.1, 3.0, 3.02 및 3.1.1 기능은 여전히 완전히 지원되며 기본적으로 SMBv2 이진 파일의 일부로 포함됩니다.
- 컴퓨터 브라우저 서비스는 SMBv1을 사용하므로 SMBv1 클라이언트 또는 서버를 제거하면 서비스가 제거됩니다. 즉, 탐색기 네트워크는 레거시 NetBIOS 데이터그램 검색 방법을 통해 더 이상 Windows 컴퓨터를 표시할 수 없습니다.
- SMBv1은 Windows 10 및 Windows Server 2016의 모든 버전에서 계속 다시 설치할 수 있습니다.
- Microsoft에서 Azure Marketplace용으로 만든 Windows Server 가상 머신에는 SMB1을 사용하도록 설정할 수 없는 SMB1 이진 파일이 & 포함되어 있지 않습니다. 타사 Azure Marketplace VM에 SMB1이 포함될 수 있습니다. 자세한 내용은 공급업체에 문의하세요.

Windows 10 버전 1809(RS5)부터 Windows 10 Pro는 클린 설치 후 기본적으로 SMBv1 클라이언트를 더 이상 포함하지 않습니다. 버전 1709의 다른 모든 동작은 여전히 적용됩니다.

 참고

Windows 10 버전 1803(RS4) Pro는 Windows 10 버전 1703(RS2) 및 Windows 10 버전 1607(RS1)과 동일한 방식으로 SMBv1을 처리합니다. 이 문제는 Windows 10 버전 1809(RS5)에서 해결되었습니다. SMBv1을 수동으로 제거할 수 있습니다. 그러나 Windows는 다음 시나리오에서 15일 후에 SMBv1을 자동으로 제거하지 않습니다.

- Windows 10 버전 1803의 클린 설치를 수행합니다.
- 먼저 Windows 10 버전 1709로 업그레이드하지 않고 Windows 10, 버전 1607 또는 Windows 10 버전 1703을 Windows 10 버전 1803으로 직접 업그레이드합니다.

SMBv1만 지원하는 디바이스에 연결하려고 시도하거나 이러한 디바이스가 사용자에게 연결을 시도하는 경우 다음 오류 메시지 중 하나를 수신할 수 있습니다.

출력

```output
You can't connect to the file share because it's not secure. This share requires the obsolete SMB1 protocol, which is unsafe and could expose your system to attack.
Your system requires SMB2 or higher. For more info on resolving this issue, see: https://go.microsoft.com/fwlink/?linkid=852747
```

출력

```output
The specified network name is no longer available.
```

출력

```output
Unspecified error 0x80004005
```

출력

```output
System Error 64
```

출력

```output
The specified server cannot perform the requested operation.
```

출력

```output
Error 58
```

원격 서버가 이 클라이언트에서 SMBv1 연결을 필요로 하고 SMBv1 클라이언트가 설치되면 다음 이벤트가 기록됩니다. 이 메커니즘은 SMBv1의 사용을 감사하고 사용 부족으로 인해 SMBv1을 제거하는 15일 타이머를 설정하기 위해 자동 제거기에서도 사용됩니다.

출력

```output
Log Name:      Microsoft-Windows-SmbClient/Security
Source:        Microsoft-Windows-SMBClient
Date:          Date/Time
Event ID:      32002
Task Category: None
Level:         Info
Keywords:      (128)
User:          NETWORK SERVICE
Computer:      junkle.contoso.com
Description:
The local computer received an SMB1 negotiate response.

Dialect:
SecurityMode
Server name:

Guidance:
SMB1 is deprecated and should not be installed nor enabled. For more information, see https://go.microsoft.com/fwlink/?linkid=852747.
```

원격 서버가 이 클라이언트에서 SMBv1 연결을 필요로 하고 SMBv1 클라이언트가 설치되지 않은 경우 다음 이벤트가 기록됩니다. 이 이벤트는 연결이 실패하는 이유를 보여주기 위한 것입니다.

출력

```output
Log Name:      Microsoft-Windows-SmbClient/Security
Source:        Microsoft-Windows-SMBClient
Date:          Date/Time
Event ID:      32000
Task Category: None
Level:         Info
Keywords:      (128)
User:          NETWORK SERVICE
Computer:      junkle.contoso.com
Description:
SMB1 negotiate response received from remote device when SMB1 cannot be negotiated by the local computer.
Dialect:
Server name:

Guidance:
The client has SMB1 disabled or uninstalled. For more information: https://go.microsoft.com/fwlink/?linkid=852747.
```

이러한 디바이스는 Windows를 실행하지 않을 가능성이 높습니다. 이전 버전의 Linux, Samba 또는 다른 유형의 타사 소프트웨어를 실행하여 SMB 서비스를 제공할 가능성이 높습니다. 종종 이러한 버전의 Linux 및 Samba는 더 이상 지원되지 않습니다.

 참고

Windows 10 버전 1709는 "Fall Creators Update"라고도 합니다.



## 추가 정보

이 문제를 해결하려면 SMBv1만 지원하는 제품 제조업체에 문의하고 SMBv2.02 이상을 지원하는 소프트웨어 또는 펌웨어 업데이트를 요청합니다. 알려진 공급업체 및 해당 SMBv1 요구 사항의 현재 목록은 다음 Windows 및 Windows Server Storage 엔지니어링 팀 블로그 문서를 참조하세요.

[SMBv1 Product Clearinghouse](https://techcommunity.microsoft.com/t5/Storage-at-Microsoft/SMB1-Product-Clearinghouse/ba-p/426008)



### 임대 모드

Oplock을 사용하지 않도록 설정하는 요구 사항과 같은 레거시 소프트웨어 동작에 대한 애플리케이션 호환성을 제공하기 위해 SMBv1이 필요한 경우 Windows는 임대 모드라고 하는 새 SMB 공유 플래그를 제공합니다. 이 플래그는 공유가 임대 및 oplock과 같은 최신 SMB 의미 체계를 사용하지 않도록 설정할지 여부를 지정합니다.

oplock 또는 임대를 사용하지 않고 공유를 지정하여 레거시 애플리케이션이 SMBv2 이상 버전에서 작동하도록 허용할 수 있습니다. 이렇게 하려면 -LeasingMode None **매개 변수와** 함께 New-SmbShare **또는 \**Set-SmbShare\** PowerShell cmdlet을 사용합니다**.

 참고

공급업체에서 필수라고 명시하는 경우 레거시 지원을 위해 타사 애플리케이션에 필요한 공유에만 이 옵션을 사용해야 합니다. 스케일 아웃 파일 서버에서 사용되는 사용자 데이터 공유 또는 CA 공유에 대한 임대 모드를 지정하지 마세요. 이는 oplock 및 임대를 제거하면 대부분의 애플리케이션에서 불안정성 및 데이터 손상이 발생하기 때문입니다. 임대 모드는 공유 모드에서만 작동합니다. 모든 클라이언트 운영 체제에서 사용할 수 있습니다.



### 탐색기 네트워크 검색

컴퓨터 브라우저 서비스는 SMBv1 프로토콜을 사용하여 Windows 탐색기 네트워크 노드("네트워크 환경"라고도 함)를 채웁니다. 이 레거시 프로토콜은 오래 사용되지 않으며 라우팅되지 않으며 보안이 제한됩니다. SMBv1 없이는 서비스가 작동할 수 없으므로 동시에 제거됩니다.

그러나 여전히 가정 및 소규모 비즈니스 작업 그룹 환경에서 탐색기 네트워크를 사용하여 Windows 기반 컴퓨터를 찾아야 하는 경우 더 이상 SMBv1을 사용하지 않는 Windows 기반 컴퓨터에서 다음 단계를 수행할 수 있습니다.

1. "함수 검색 공급자 호스트" 및 "함수 검색 리소스 게시" 서비스를 시작한 다음 자동(지연된 시작)**으로** 설정합니다.
2. 탐색기 네트워크를 열 때 메시지가 표시되면 네트워크 검색을 사용하도록 설정합니다.

이제 이러한 설정이 있는 해당 서브넷 내의 모든 Windows 디바이스가 검색을 위해 네트워크에 표시됩니다. WS-DISCOVERY 프로토콜을 사용합니다. Windows 디바이스가 표시된 후에도 해당 디바이스가 이 찾아보기 목록에 표시되지 않는 경우 다른 공급업체 및 제조업체에 문의하세요. 이 프로토콜을 사용하지 않도록 설정하거나 SMBv1만 지원할 수 있습니다.

 참고

 이 기능을 사용하도록 설정하는 대신 드라이브와 프린터를 매핑하는 것이 좋습니다. 이 기능을 사용하려면 디바이스를 검색하고 검색해야 합니다. 매핑된 리소스는 찾기 쉽고, 학습이 덜 필요하며, 사용하기에 안전합니다. 이러한 리소스가 그룹 정책을 통해 자동으로 제공되는 경우 특히 그렇습니다. 관리자는 IP 주소, AD DS(Active Directory 도메인 Services), Bonjour, mDNS, uPnP 등을 사용하여 레거시 컴퓨터 브라우저 서비스 이외의 방법으로 위치에 프린터를 구성할 수 있습니다.

이러한 해결 방법을 사용할 수 없거나 애플리케이션 제조업체에서 지원되는 버전의 SMB를 제공할 수 없는 경우 Windows[에서 SMBv1, SMBv2 및 SMBv3을 검색, 사용 및 사용하지 않도록 설정하는 방법의 단계에 ](https://learn.microsoft.com/ko-KR/windows-server/storage/file-server/troubleshoot/detect-enable-and-disable-smbv1-v2-v3)따라 수동으로 SMBv1을 다시 사용하도록 설정할 수 있습니다.

 중요

SMBv1을 다시 설치하지 않는 것이 좋습니다. 이 이전 프로토콜은 랜섬웨어 및 기타 맬웨어와 관련된 보안 문제를 알고 있기 때문입니다.



### Windows Server 모범 사례 분석기 메시징

Windows Server 2012 이상 서버 운영 시스템에는 파일 서버에 대한 모범 사례 분석기(BPA)가 포함되어 있습니다. 올바른 온라인 지침에 따라 SMB1을 제거한 경우 이 BPA를 실행하면 모순된 경고 메시지가 반환됩니다.

출력

```output
Title: The SMB 1.0 file sharing protocol should be enabled
Severity: Warning
Date: 3/25/2020 12:38:47 PM
Category: Configuration
Problem: The Server Message Block 1.0 (SMB 1.0) file sharing protocol is disabled on this file server.
Impact: SMB not in a default configuration, which could lead to less than optimal behavior.
Resolution: Use Registry Editor to enable the SMB 1.0 protocol.
```

 중요

이 특정 BPA 규칙의 지침은 무시해야 하며 더 이상 사용되지 않습니다. 거짓 오류는 2022년 4월 누적 업데이트에서 Windows Server 2022 및 Windows Server 2019에서 처음 수정되었습니다. SMB 1.0을 사용하도록 설정하지 마세요.



## 추가 참조

- [SMB1 사용 중지](https://aka.ms/stopusingsmb1)

