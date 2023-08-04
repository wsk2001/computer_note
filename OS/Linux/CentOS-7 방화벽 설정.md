
### CentOS-7 방화벽 설정

---

CentOS-7 에서는 방화벽 설정 하는 방법이 바뀌었다.



##### 방화벽 설정

	firewall-cmd --zone=public --add-port=1521/tcp --permanent



##### 방화벽을 리로드

	firewall-cmd --reload



---

CentOS 7 부터 iptables -> firewalld 로 변경

 

1. 설정파일 위치

 

/etc/firewalld/zones/public.xml 

vi로 열어 보면

<zone>

 <short>Public</short>

 <description>For use in public areas. You do not trust the other computers on networks to not harm your computer. Only selected incoming connections are accepted.</description>

 <service name="ssh"/>

 <service name="dhcpv6-client"/>

 <port protocol="tcp" port="8080"/>

 <port protocol="tcp" port="80"/>

</zone>

이런식으로 추가 되어 있다

 

2. 설정 추가&오픈 (설정에 추가하여 서버재부팅 후에도 계속적으로 적용되도록 함)

 

예) 8080 포트 오픈

firewall-cmd --permanent --zone=public --add-port=8080/tcp

 

3. 임시 오픈 (서버 재부팅 시 사라짐)

firewall-cmd --permanent --zone=public --add-port=8080/tcp

 

4. reload

firewall-cmd --reload

 

5. on/off

(on) systemctl start firewalld

​    systemctl enable firewalld

 

(off) systemctl stop firewalld

​    systemctl disable firewalld

 

6. 방화벽 상태 확인

firewall-cmd --state

 

7. 활성화 상태의 zone 리스트

firewall-cmd --get-active-zones

 

8. 서비스 리스트

firewall-cmd --get-service

 

9. 특정 존에 있는 서비스 리스트

예)public 존에 있는 서비스 리스트 확인

firewall-cmd --zone=public --list-services
