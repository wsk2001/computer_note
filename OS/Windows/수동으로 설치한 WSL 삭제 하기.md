# 수동으로 설치한 WSL 삭제 하기

출처: https://lahuman.github.io/wsl_uninstall/

[보안프로그램을 피해서 WSL 설치하기!](https://lahuman.github.io/wsl_install_using_command/)를 통해서 설치한 경우 제어판 > 앱에 Ubuntu가 표기 되지 않습니다.

압축을 풀었던 디렉토리를 삭제하고 다시 압축을 풀고 Ubuntu.exe 실행하면 그때부터는 **0x80070003** 메시지를 만나게 됩니다.

메뉴얼 삭제시에 windows의 wsl에 등록된 정보를 삭제하지 않았기 때문에 발생하는 문제입니다.

PowerShell에서 wsl을 삭제해주어야 됩니다.

``` powershell
# 설치된 wsl 목록 확인
PS C:\Users\admin> wslconfig.exe /l
Linux 배포용 Windows 하위 시스템:
Ubuntu-18.04(기본값)

# 설치된 wsl 목록 삭제
PS C:\Users\admin> wslconfig.exe /u Ubuntu-18.04
삭제중...

# 이후 확인하면 설치된 wsl목록이 없음
PS C:\Users\admin> wslconfig.exe /l
Windows Subsystem for Linux has no installed distributions.
Distributions can be installed by visiting the Windows Store:
https://aka.ms/wslstore

# 이후 Ubuntu 디렉토리 삭제 진행
```



##### 메뉴얼 방식으로 설치된 WSL을 삭제를 위해서는 **wslconfig.exe**를 이용한 레지스트리 제거가 필요 합니다.



##### 참고 자료

- [Error 0x80070003 after manually installing/uninstalling when launching bash.exe #3324](https://github.com/microsoft/WSL/issues/3324)

