# Linux 서버의 초기 접속 속도가 느릴때

리눅스 서버에서 구동되는 telnet 혹은 ssh ssh2, oracle, mysql 등등에서

접속되고 나서는 속도가 빠르지만 접속하기 전까지 속도가 느린 경우가 있다.



이 경우에는 DNS 역질의 때문으로 /etc/resolv.conf 파일에 DNS 주소가 등록되어 있을 경우 에 발생한다.

위 주소의 DNS 정보가 문제가 있다면 초기 접속 속도가 느리게 된다.



이경우에는 위 파일의 DNS주소를 모두 제거해 준뒤 네트워크 관련 서비스를 재시작 해주면 된다.

/etc/init.d/network restart

service network restart





Reverse DNS lookup은 역으로 IP 주소를 도메인 이름으로 바꾸기 위한 질의이다.

arp, netstat, ntpq, iptables 등 출력결과에 IP 주소가 나오는 대부분의 명령어들은 사용자에게 더 이해하기 쉬운 주소를 보여주기 위해 IP 주소에 대해 도메인 이름에 대한 질의를 보내서 이름이 있을 경우에는 이름으로 표시해 주는 기능을 가지고 있다.

