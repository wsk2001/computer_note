# system 계정으로 net drive 연결

출처: https://stackoverflow.com/questions/182750/map-a-network-drive-to-be-used-by-a-service



### 서비스에서 사용할 네트워크 드라이브 매핑

일부 Windows 서비스가 매핑 된 네트워크 드라이브를 원하는 코드를 사용하고 UNC 경로가 없다고 가정합니다. 서비스가 시작될 때 서비스 세션에서 드라이브 매핑을 사용할 수있게하려면 어떻게해야합니까? 서비스 사용자로 로그인하고 영구 매핑을 생성하면 실제 서비스 컨텍스트에서 매핑이 설정되지 않습니다.



이 해킹을 위해서는 [Mark Russinovich의 SysinternalsSuite](http://technet.microsoft.com/en-us/sysinternals/bb842062.aspx)가 필요합니다.

**Step one:** 상승 된 cmd.exe 프롬프트를 엽니 다 (관리자 권한으로 실행).

**Step two:** PSExec.exe를 사용하여 루트로 다시 승격 : SysinternalsSuite가 포함 된 폴더로 이동하고 다음 명령을 실행합니다. psexec -i -s cmd.exe 이제 nt Authority system 프롬프트 내에 있으며 whoami를 입력하여 이를 증명할 수 있습니다. -i는 드라이브 매핑이 사용자와 상호 작용해야하기 때문에 필요합니다.

**Step Three:**  `net use z: \\servername\sharedfolder /persistent:yes` 명령을 사용하여 영구 매핑 된 드라이브를 SYSTEM 계정으로 만듭니다. 

**WARNING**: 이 매핑은 SYSTEM 계정에서 생성 한 것과 동일한 방식으로 만 제거 할 수 있습니다. 제거해야하는 경우 1 단계와 2 단계를 따르되 3 단계의 명령을 `net use z : / delete` 로 변경합니다.

**NOTE**:  새로 생성 된 매핑 된 드라이브가 이제 이 시스템의 모든 사용자에게 표시되지만 '연결 해제 된 네트워크 드라이브 (Z :)'로 표시됩니다. 이름에 속지 마십시오. 연결이 끊어 졌다고 주장 할 수 있지만 모든 사람에게 적용됩니다. 이것이 이 해킹이 M$에서 지원되지 않는다는 것을 알 수있는 방법입니다.



Example

``` cmd
ServiceCreate

system 계정으로 net drive 연결
https://stackoverflow.com/questions/182750/map-a-network-drive-to-be-used-by-a-service

net use z: \\10.10.10.110\dev /persistent:yes


C:\opt\Tools\PSTools>PsExec.exe -i -s cmd.exe

PsExec v2.2 - Execute processes remotely
Copyright (C) 2001-2016 Mark Russinovich
Sysinternals - www.sysinternals.com


cmd.exe exited on DESKTOP-VOD18KQ with error code 0.

C:\opt\Tools\PSTools>

```



