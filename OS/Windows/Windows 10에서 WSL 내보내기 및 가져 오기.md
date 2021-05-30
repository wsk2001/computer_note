# Windows 10에서 WSL 내보내기 및 가져 오기

출처: https://winaero.com/export-import-wsl-linux-distro-windows-10/

권장 : Windows 문제를 해결하고 시스템 성능을 최적화하려면 [여기](https://winaero.com/idx.php?dest=a8)를 클릭하십시오.

Windows 10 버전 1903 '2019 년 4 월 업데이트'에는 WSL 기능에 대한 여러 가지 흥미로운 변경 및 개선 사항이 포함되어 있습니다. 여기에는 저장소의 추가 배포판, 가상 네트워크 공유를 통해 File Exporer에서 WSL 파일을 찾아 보는 기능, TAR 파일로  WSL 배포판을 내보내고 가져 오는 기능이 포함됩니다.

Windows 10에서 기본적으로 Linux를 실행하는 기능은 WSL 기능에서 제공합니다. WSL은 Linux 용 Windows 하위 시스템을 나타내며 처음에는 Ubuntu로만 제한되었습니다. 최신 버전의 WSL을 사용하면 Microsoft Store에서 여러 Linux 배포판을 설치하고 실행할 수 있습니다.

[WSL을 사용 설정](https://winaero.com/blog/enable-wsl-windows-10-fall-creators-update/) 한 후 스토어에서 다양한 Linux 버전을 설치할 수 있습니다. 다음 링크를 사용할 수 있습니다.

1. [Ubuntu](https://www.microsoft.com/en-us/store/p/ubuntu/9nblggh4msv6)
2. [openSUSE Leap](https://www.microsoft.com/en-us/store/p/opensuse-leap-42/9njvjts82tjx)
3. [SUSE Linux Enterprise Server](https://www.microsoft.com/en-us/store/p/suse-linux-enterprise-server-12/9p32mwbh6cns)
4. [Kali Linux for WSL](https://www.microsoft.com/en-us/store/p/kali-linux/9pkr34tncv07)
5. [Debian GNU/Linux](https://www.microsoft.com/en-us/store/p/debian-gnu-linux/9msvkqc78pk6)



### WSL 배포판 내보내기 및 가져 오기

Windows 10 버전 1903 '2019 년 4 월 업데이트'를 사용하면 Linux 배포판을 TAR 파일로 가져오고 내보낼 수 있습니다. 이를 통해 Linux 환경을 사용자 정의하고 원하는 앱을 설치 한 다음 파일로 내보낼 수 있습니다. 나중에 다른 PC에서 설정을 복원하거나 친구와 공유 할 수 있습니다.

이 작업은 WSL 관리를 허용하는 명령 줄 도구 인 wsl.exe를 사용하여 수행 할 수 있습니다. 이 글을 쓰는 시점에서이 기능은 Windows 10 빌드 18836에서 구현되었습니다. 19h1 브랜치로가는 중이므로 다음 빌드에서 확인할 수 있습니다.

#### WSL 배포판을 파일로 내보내려면 다음을 수행하십시오.

- 내보낼 배포판을 시작하십시오.

- 업데이트하고, 앱을 설치 및 구성하고, 원하는대로 변경하십시오.

- WSL 환경을 종료합니다.

- 새 명령 프롬프트 또는 PowerShell 을 엽니다.

  - 관리자 권한으로 열어야 합니다.

- 다음 명령을 실행하십시오.

  ``` cmd
  wsl.exe --export <DistributionName> <FileName>
  
  # 실제 예
  wsl --export Ubuntu-20.04 C:\Tmp\WSL_Ubuntu_2004_Backup.tar
  ```

  DistributionName을 WSL 배포판의 실제 이름 (예 : Ubuntu)으로 대체합니다. FileName을 TAR 파일의 전체 경로로 바꾸어 배포판을 저장합니다.

- Tip:  `wsl --list --all` 명령을 사용하여 설치된 WSL 배포판 및 해당 이름 목록을 볼 수 있습니다.



#### Windows 10의 파일에서 WSL 배포판가져 오기

Linux 배포판의 루트 파일 시스템이 포함 된 tar 파일을 가져올 수 있으므로 원하는 구성과 함께 원하는 배포판을 가져올 수 있습니다. 사용자 지정된 배포를 저장할 이름과 사용자 지정 폴더 위치를 지정할 수 있습니다.

파일에서 WSL 배포판을 가져 오려면 다음을 수행하십시오.

- 새 명령 프롬프트를 엽니다.

  - 관리자 모드로 엽니다.

- 다음 명령을 실행하십시오.

  ``` cmd
  wsl.exe --import <DistributionName> <InstallLocation> <FileName>
  
  # 예 (위의 export 해 놓았던 파일 이용)
  wsl --import Ubuntu-20.04 C:\data\Ubuntu20.04 C:\Tmp\WSL_Ubuntu_2004_Backup.tar
  ```

- DistributionName  가져 오는 배포판에 할당하려는 이름으로 대체합니다.

- FileName을 TAR 파일의 전체 경로로 바꿉니다.

- 가져온 배포판을 실행하려면 명령 프롬프트 또는 PowerShell에서 다음 명령을 실행합니다.

  ``` cmd
  wsl --distribution <DistributionName>
  ```

  DistributionName 부분을 가져온 배포판에 할당 한 이름으로 바꿉니다.

- 마지막으로 가져온 Linux 배포를 제거하려면 다음 명령을 실행하십시오.

  ``` cmd
  wsl.exe --unregister <DistributionName>
  
  # 예를 들면
  wsl.exe --unregister UbuntuCustom
  ```





# 수동으로 설치한 WSL 삭제 하기

출처: https://lahuman.github.io/wsl_uninstall/

# 수동으로 설치한 WSL 삭제 하기

[보안프로그램을 피해서 WSL 설치하기!](https://lahuman.github.io/wsl_install_using_command/)를 통해서 설치한 경우 제어판 > 앱에 Ubuntu가 표기 되지 않습니다.

압축을 풀었던 디렉토리를 삭제하고 다시 압축을 풀고 Ubuntu.exe 실행하면 그때부터는 **0x80070003** 메시지를 만나게 됩니다.

메뉴얼 삭제시에 windows의 wsl에 등록된 정보를 삭제하지 않았기 때문에 발생하는 문제입니다.

PowerShell에서 wsl을 삭제해주어야 됩니다.

``` bash
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

#### 메뉴얼 방식으로 설치된 WSL을 삭제를 위해서는 **wslconfig.exe**를 이용한 레지스트리 제거가 필요 합니다.

#### 참고 자료

- [Error 0x80070003 after manually installing/uninstalling when launching bash.exe #3324](https://github.com/microsoft/WSL/issues/3324)

