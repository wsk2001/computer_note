# WSL 에 centos 7 설치/삭제

WSL 에서 SystemD 는 지원하지 않는 모양이다. 윈도우10 20H1 (2020년 1분기 예정 업데이트) 버전에 포함되는 WSL2 에서 지원되는 것인지는 확인해 보지 못했다.



윈도우10 에서는 기본적으로 Ubuntu, Kali Linux, Debian, AlpinLinux, OpenSUSE, SUSE Enterprise server 만 스토어에서 다운로드 받을 수 있는 모양이다. 따라서 CentOS 는 다음과 같이 수동설치해야 한다.





*** 설치하는 법**

- 1. 제어판에서 윈도우 기능 켜기/끄기에서 윈도우 리눅스 하위 시스템을 체크하여 설치한다.

- 2. 아래 다운로드 링크에서 파일(약 350메가 정도)되는 파일을 받아 압축 해제한다.

- 3. CentOS.exe 파일을 관리자모드로 실행한다. (첫번째로 실행하면 설치가 된다)

- 4. CentOS.exe 파일을 관리자모드로 실행한다. (두번째부터는 프로그램이 실행된다)

- 5. yum 업데이트를 통해 업데이트를 받는다. (yum -y update)





*** 삭제하는 법**

- 1. 커맨드창이나 파워셀(Powershell)로 압축된 폴더로 이동한 다음 명령을 입력한다.

     (두번 반복해야 정상적으로 설치된 파일들이 모두 삭제되는 것으로 보인다)

     CentOS.exe /clean



\* WSL CentOS 7 다운로드 링크 : https://github.com/yuk7/CentWSL/releases/tag/7.0.1905.1

\* 참고 : https://www.how2shout.com/how-to/install-centos-on-windows-10-subsystem-for-linux.html

\* 리눅스용 윈도우즈 하위 시스템 설명서 : https://docs.microsoft.com/ko-kr/windows/wsl/about

