# Windows 10용 OpenSSH 설치

출처: https://docs.microsoft.com/ko-kr/windows-server/administration/openssh/openssh_install_firstuse

Windows 에 OpenSSH  를 설치 하면 Windows 와 Linux 또는 Unix 간 ssh (scp) 를 통하여 파일 전송을 쉽게 할 수 있어 개발 및 유지보수 작업에 도움이 됩니다.



OpenSSH 클라이언트 및 OpenSSH 서버는 Windows Server 2019 및 Windows 10 1809에서 개별적으로 설치할 수 있는 구성 요소입니다. 이러한 Windows 버전 사용자는 지침에 따라 OpenSSH를 설치하고 구성해야 합니다.

```
PowerShell GitHub 리포지토리(https://github.com/PowerShell/OpenSSH-Portable) 에서 OpenSSH를 가져온 사용자는 이 지침을 따르는 대신 해당 지침을 따라야 합니다.
```



## Windows Server 2019 또는 Windows 10 1809의 설정 UI에서 OpenSSH 설치

OpenSSH 클라이언트 및 서버는 Windows 10 1809의 설치 가능한 기능입니다.

OpenSSH를 설치하려면 설정을 시작한 다음, 앱 > 앱 및 기능 > 선택적 기능 관리로 이동합니다.

이 목록에서 OpenSSH 클라이언트가 이미 설치되어 있는지 확인합니다. 그렇지 않으면 페이지 상단에서 "기능 추가"를 선택한 후 다음을 수행합니다.

- OpenSSH 클라이언트를 설치하려면 "OpenSSH 클라이언트"를 찾은 다음, "설치"를 클릭합니다.
- OpenSSH 서버를 설치하려면 "OpenSSH 서버"를 찾은 다음, "설치"를 클릭합니다.

설치가 완료되면 앱 > 앱 및 기능 > 선택적 기능 관리로 돌아와서 OpenSSH 구성 요소가 목록에 표시되는지 확인합니다.

```
OpenSSH 서버를 설치하면 "OpenSSH-Server-In-TCP"라는 방화벽 규칙이 생성되고 활성화됩니다. 이 규칙은 포트 22에서의 인바운드 SSH 트래픽을 허용합니다.
```



## PowerShell을 사용하여 OpenSSH 설치

PowerShell을 사용하여 OpenSSH를 설치하려면 먼저 관리자 권한으로 PowerShell을 시작합니다. OpenSSH 기능을 설치할 수 있는지 확인하기 위해 다음을 수행합니다.

```powershell
Get-WindowsCapability -Online | ? Name -like 'OpenSSH*'

# This should return the following output:

Name  : OpenSSH.Client~~~~0.0.1.0
State : NotPresent
Name  : OpenSSH.Server~~~~0.0.1.0
State : NotPresent
```

그런 다음, 서버 및/또는 클라이언트 기능을 설치합니다.

```powershell
# Install the OpenSSH Client
Add-WindowsCapability -Online -Name OpenSSH.Client~~~~0.0.1.0

# Install the OpenSSH Server
Add-WindowsCapability -Online -Name OpenSSH.Server~~~~0.0.1.0

# Both of these should return the following output:

Path          :
Online        : True
RestartNeeded : False
```



## OpenSSH 제거

Windows 설정을 사용하여 OpenSSH를 제거하려면 설정을 시작한 다음, 앱 > 앱 및 기능 > 선택적 기능 관리로 이동합니다. 설치된 기능 목록에서 OpenSSH 클라이언트 또는 OpenSSH 서버 구성 요소를 선택한 다음, 제거를 선택합니다.

PowerShell을 사용하여 OpenSSH를 제거하려면 다음 명령 중 하나를 사용합니다.

```powershell
# Uninstall the OpenSSH Client
Remove-WindowsCapability -Online -Name OpenSSH.Client~~~~0.0.1.0

# Uninstall the OpenSSH Server
Remove-WindowsCapability -Online -Name OpenSSH.Server~~~~0.0.1.0
```

제거할 때 서비스가 사용 중이었으면 OpenSSH를 제거한 후 Windows를 다시 시작해야 합니다.



## SSH 서버의 초기 구성

Windows에서 초기 사용을 위해 OpenSSH를 구성하려면 관리자 권한으로 PowerShell을 시작한 후 다음 명령을 실행하여 SSHD 서비스를 시작합니다.

```powershell
Start-Service sshd
# OPTIONAL but recommended:
Set-Service -Name sshd -StartupType 'Automatic'
# Confirm the Firewall rule is configured. It should be created automatically by setup. 
Get-NetFirewallRule -Name *ssh*
# There should be a firewall rule named "OpenSSH-Server-In-TCP", which should be enabled
# If the firewall does not exist, create one
New-NetFirewallRule -Name sshd -DisplayName 'OpenSSH Server (sshd)' -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 22
```



## SSH의 초기 사용

Windows에 OpenSSH 서버를 설치한 후에는 SSH 클라이언트가 설치된 모든 Windows 디바이스에서 PowerShell을 사용하여 빠르게 테스트할 수 있습니다. PowerShell에서 다음 명령을 입력합니다.

```powershell
ssh username@servername
```

서버에 처음 연결하면 다음과 비슷한 메시지가 표시됩니다.

```
The authenticity of host 'servername (10.00.00.001)' can't be established.
ECDSA key fingerprint is SHA256:(<a large string>).
Are you sure you want to continue connecting (yes/no)?
```

답변은 "예" 또는 "아니요"여야 합니다. 예로 답변하면 로컬 시스템의 알려진 ssh 호스트 목록에 해당 서버가 추가됩니다.

그리고 암호를 입력하라는 메시지가 표시됩니다. 보안을 위해 암호는 입력 중에 표시되지 않습니다.

연결된 후에는 다음과 비슷한 명령 셸 프롬프트가 표시됩니다.

```
domain\username@SERVERNAME C:\Users\username>
```

Windows OpenSSH 서버에서 사용되는 기본 셸은 Windows 명령 셸입니다.



### - 끝 -

