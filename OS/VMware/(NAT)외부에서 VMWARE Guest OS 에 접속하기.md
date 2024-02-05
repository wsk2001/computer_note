# (NAT)외부에서 VMWARE Guest OS 에 접속하기

**같은 네트워크 망에 있는 다른 Host 컴퓨터가 자신의 VM머신의 OS에 접근 하려고 해도
Bridge로 설정하지 않는 이상 Host 컴퓨터가 다시 뿌려주는 사내 ip를 VM머신이 받아서
쓰기 때문에 포트포워딩을 따로 해줘야합니다.

우선, VM머신의 OS가 리눅스인 상황을 전제로하고 포스팅을 하겠습니다.
(Windows 경우에도 VM머신에 설치된 OS에 할당된 ip만 알면 되므로 커맨드 창(cmd)에서 ipconfig 명령어를 이용해 진행할 수 있습니다.)

[1] VM머신에 설치된 OS의 IP를 알아냅니다.
**



![img](https://blog.kakaocdn.net/dn/esp2oK/btqQu4YYvQG/IPijk6Y3DgAq6Ge9bdOGDk/img.png)



**
제 VM머신에 설치된 리눅스의 IP는 192.168.81.111 이네요.

[2] Host의 IP를 체크합니다.
**



![img](https://blog.kakaocdn.net/dn/lOdKW/btqQzScDicK/YOj6pG0enojDMPHTauMkpk/img.png)



**
Host OS에서 시작 - 실행 - cmd 를 실행시켜 ipconfig를 타이핑합니다.
전 지금 무선 네트워크 망에 접속되어 있는 상황입니다. (로컬 영역도 돌아가고 있네요.)
로컬 영역과 무선 네트워크 망 중 하나를 선택하여 IP Address 를 선택하시면 됩니다.
우선 전 무선 네트워크 망에서 할당해 준 IP를 선택하겠습니다.

**보통 VMware 등을 설치하게 되면 가상 네트워크 어뎁터가 여러개 잡혀있습니다. 그래서 바로 ipconfig 만 치면 화면이 밑으로 많이 내려가게 되는데, 파이프 (Shift + \) + more 명령어를 입력하여 상위 목록을 확인하면서 천천히 내려주시면 됩니다.
..(more에 관한 건 그다지 중요한 설명이 아닙니다.)

**[3] 편집(Edit) - 가상 네트워크 편집기(Virtual Network Editor) 을 열어 VMnet8을 선택합니다.
**



![img](https://blog.kakaocdn.net/dn/TNTR7/btqQjoLFz9h/HhjieBC5pqicSxbCMgHfr1/img.png)



**
**여기서 Subnet IP와 Subnet mask 는 설명하지 않겠습니다. 보통 IP 대역이 192.168.x.y 라면 Subnet IP 는 192.168.x.0 입니다.
이에 관한 정보는 Subnet에 대해서 검색하셔서 공부하시길 바랍니다. -_-;

**[4] NAT Settings 를 클릭합니다.
**



![img](https://blog.kakaocdn.net/dn/LglrZ/btqQn0QOHHD/n7JqnqYiuX3nUO0rsRbBB1/img.png)



**
6.x 버전 VMware에서는 따로 Port Forwading 이라는 메뉴를 통해 접근했었는데, VMware 7.x 부터는 이런식으로 포트 포워딩을 세팅할 수 있습니다. 보이다시피 저는 이미 HostOS의 TCP/9999 포트를 통해서 VM머신의 리눅스의 SSH 서비스를 제공하고 있습니다. 

[5] "Add" 를 클릭하고 정보를 입력합니다.
**



![img](https://blog.kakaocdn.net/dn/YbOdH/btqQlKVn6id/hCjQVlFlDPymvfjub4Rh0K/img.png)



**VMware에 설치된 OS에 외부접속을 허용하려면 우선 Host OS 를 거쳐야합니다.
Host OS에서 열린 특정 Port를 통해서 VMware OS의 특정 Port에 접근하여 연결하는 방식입니다.
**한 번 예로 VM머신의 OS의 웹서버를 제공하는 정보를 써보았습니다.



**Host port : Host OS에서 열어 줄 포트 번호를 선택합니다.
(주의 : Host OS에서 사용하고 있는 포트 번호와 겹치게 되면 충돌이 일어나 서비스가 제대로 실행되지 않을 수 있습니다. 포트 1~1024 대역은 보통 System이 사용하는 대역이니 1024~65535 대역 중 하나의 번호를 골라 선택하시길 바랍니다.) 

Type : TCP/UDP를 결정합니다. 특별한 경우가 아니면 TCP를 선택하시면 됩니다.

Virtual machine IP address : [1] 번 항목에서 알아보았던 VM머신에 설치된 OS의 IP를 적습니다.

Virtual machine port : VM머신에 설치된 OS에서 열어줄 서비스의 포트를 적습니다.
(보통 기본 값으로는, SSH서비스는 22번, Telnet서비스는 23번, Apache 등의 웹 서비스는 80번 포트를 사용합니다. 이것은 VM머신의 OS에서 따로 작업하여 포트 번호를 임의로 바꿀 수 있습니다.)

Description : 설명 란입니다. 그냥 단순히 서비스 명을 적어주심이..^^;
**



**[6] 모든 세팅이 완료되었습니다. VM머신의 설정 창을 모두 OK 해주시고 PuTTY 등의 원격 접속 클라이언트를 사용하여 접근해 보시길 바랍니다.**





![img](https://blog.kakaocdn.net/dn/cgq57v/btqQnZqS5sJ/Q6eVcZ9aKHY485zKgB973K/img.png)



**
PuTTY 말고도 XShell, SecureCRT 등 많은 접속 클라이언트가 있습니다. 저는 제가 이전에 설정해 놓은 SSH 서비스를 Host IP를 통해서 접근해보도록 하겠습니다. 당연히 접속할 곳은 Host IP, Host port 가 되야겠죠?

SSH 서비스를 제공하시는 분들은 SSH 서비스로, Telnet 서비스를 제공하시는 분들은 Telnet으로, 별도의 다른 서비스를 제공하실 생각이신 분들은 해당 서비스에 맞는 특수한 접속 클라이언트를 사용하시거나 Telnet 방식으로 연결하시면 접속할 수 있습니다.
 
웹 서비스를 하시는 분들은 웹 브라우저에** [**http://localhost:Port/**](http://localhost:Port/) **방식이나,** [**http://HostIP:HostPort/**](http://hostip:HostPort/) **방식으로 접근이 가능합니다.

**TIP)
\* localhost 란 자기 자신을 뜻합니다. Host OS에서 작업하기 때문에, Host IP는 자기 자신이 되겠지요. 그래서 localhost를 쓸 수 있습니다. 위의 PuTTY 등의 클라이언트에서도 localhost 문자를 Host 자신의 IP로 인식하는 클라이언트는 마찬가지로 localhost를 입력함으로써 이용할 수 있습니다. *

**
[7] Host OS를 통해서 VM머신의 OS의 서비스에 성공적으로 접근했음을 알 수 있습니다.
**



![img](https://blog.kakaocdn.net/dn/cwYR0R/btqQptFaweB/TOZrY7V4MIKn4GtPxj0Tkk/img.png)







출처: https://anow.tistory.com/12 [Anow's Opportunities - 언제나 진실되게]