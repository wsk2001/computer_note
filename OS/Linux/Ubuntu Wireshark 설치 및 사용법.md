# Ubuntu Wireshark 설치 및 사용법

출처: https://wiki.ubuntu-kr.org/index.php/Wireshark



## WIRESHARK[[편집](https://wiki.ubuntu-kr.org/index.php?title=Wireshark&action=edit&section=1)]

본 문서는 wireshark의 설치 방법 및 간단한 사용설명을 포함하고 있다.

심오한 내용을 학습하고자 하는 사람은 다음 사이트를 참고하시길..

https://www.wireshark.org/



### 개요[[편집](https://wiki.ubuntu-kr.org/index.php?title=Wireshark&action=edit&section=2)]

WIRESHARK는 널리사용되는 Network protocol analyzer로써, 유선/무선으로 송수신 되는 TCP/IP패킷을 분석한다.



### 설치[[편집](https://wiki.ubuntu-kr.org/index.php?title=Wireshark&action=edit&section=3)]

UBUNTU에서는 apt를 사용하여 간단하게 설치가 가능하다.

``` bash
sudo apt install -y wireshark
```

wireshark는 네트워크 시스템 리소스를 사용하는 프로그램이므로 인증된 사용자 혹은 superuser으로만 사용 할 수 있다.

그룹에 사용자 추가를 통해 사용자를 인증 할 수 있다.

``` bash
sudo adduser $USER wireshark
```

그룹 추가 이후 재부팅



사용자 인증 후 wireshark를 none superuser도 사용가능 하도록 설정을 다시한다.

``` bash
sudo dpkg-reconfigure wireshark-common
```

![](./images/Wireshark_config.png)

### 실행 화면

![](./images/750px-Wireshark_run.png)

### interface 선택

프로그램을 초기에 실행하면 자신의 컴퓨터에 설치된 모든 인터페이스 목록이 나타난다.

원하는 인터페이스를 선택하여 클릭하면 해당 인터페이스의 패킷을 캡춰 하는 화면으로 이동한다.

왼쪽 상단의 푸른 상어 지느러미 아이콘(?)은 패킷 캡춰 시작을 지시하는 버튼이다.

해당 버튼을 누르면 인터페이스를 통해 들어오고 나가는 모든 패킷이 캡춰 된다.

![](./images/750px-필터없음.png)

패킷을 하나 더블클릭하면 패킷의 상세 정보를 나타내는 창이 뜬다.

![](./images/750px-패킷상세.png)

필터를 설정하지 않고 캡춰를 하면 자신의 눈보다 빠른 패킷 증가를 보게 될 것이다.



### 필터 형식

Packet을 계층별로 분류하여 필터를 지정 할 수 있다.

ex) packet의 ipaddr이 192.168.0.49 일 경우

ip.addr==192.168.0.49


ex) packet의 목적지가 192.168.0.49일 경우

ip.dst==192.168.0.49


ex) tcp packet중 port 번호가 80일 경우

tcp.port==80


Filter는 논리연산으로 중복 할 수 있다.

ex) 목적지가 192.168.0.49인 80(http) packet

ip.dst==192.168.0.49 && tcp.port==80

![](./images/1200px-다중필터.png)

- 이 문서는 2018년 1월 10일 (수) 05:58에 마지막으로 바뀌었습니다.
- 내용은 별도로 명시하지 않을 경우 [GNU 자유 문서 사용 허가서 1.3 이상](https://www.gnu.org/copyleft/fdl.html)에 따라 사용할 수 있습니다.

- [개인정보 정책](https://wiki.ubuntu-kr.org/index.php/Ubuntu_Korea_Community_Wiki:개인정보_정책)
- [깊이있는 삽질 Ubuntu Korea Community Wiki 소개](https://wiki.ubuntu-kr.org/index.php/Ubuntu_Korea_Community_Wiki:소개)
- [면책 조항](https://wiki.ubuntu-kr.org/index.php/Ubuntu_Korea_Community_Wiki:면책_조항)