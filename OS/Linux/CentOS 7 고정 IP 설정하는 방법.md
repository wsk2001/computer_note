# CentOS 7 고정 IP 설정하는 방법

주의: VMware 에서 실행 할 때는 주의가 필요함



CentOS 7을 설치할 때 네트워크를 설정할 수 있습니다. GUI 환경이므로 네트워크 설정을 알고 있다면 쉽게 고정 IP로 설정할 수 있습니다. 만약 DHCP로 설치했다면 다음과 같은 방법으로 고정 IP 설정을 할 수 있습니다.

### 네트워크 설정 파일 열기

네트워크 설정 파일을 확인 하기 위해 다음 명령을 실행 합니다.

```bash
[root@CentOS-7 ~]# ifconfig
ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.194.157  netmask 255.255.255.0  broadcast 192.168.194.255
        inet6 fe80::ab28:fd92:9fa3:4c89  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:3a:11:55  txqueuelen 1000  (Ethernet)
        RX packets 155  bytes 16652 (16.2 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 130  bytes 16716 (16.3 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1  (Local Loopback)
        RX packets 68  bytes 5588 (5.4 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 68  bytes 5588 (5.4 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

virbr0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet 192.168.122.1  netmask 255.255.255.0  broadcast 192.168.122.255
        ether 52:54:00:70:c3:68  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

```

네트워크 설정 파일은 /etc/sysconfig/network-scripts/ifcfg-ens33입니다.(파일 이름은 다를 수도 있습니다.) 설정 파일을 텍스트 에디터로 엽니다.

```
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
BOOTPROTO="dhcp"
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="enp0s3"
UUID="91af51db-7cf0-4069-9433-77d356b31bca"
DEVICE="enp0s3"
ONBOOT="yes"
```



### 네트워크 설정 파일 수정하기

BOOTPROTO="dhcp"를 주석처리하고 네트워크 정보를 추가합니다.

```
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
#BOOTPROTO="dhcp"
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="enp0s3"
UUID="91af51db-7cf0-4069-9433-77d356b31bca"
DEVICE="enp0s3"
ONBOOT="yes"

BOOTPROTO="static"
IPADDR="192.168.194.141"
NETMASK="255.255.255.0"
GATEWAY="192.168.194.1"
DNS1="168.126.63.1"
DNS2="8.8.8.8"
```



### 네트워크 재시작하기

네트워크를 재시작하면 변경 사항이 적용됩니다.(변경되지 않으면 서버를 재부팅 하세요.)

```
systemctl restart network
```

외부에서 접속하여 네트워크 설정을 변경했다면, 네트워크를 재시작했을 때 연결이 끊긴다는 것에 주의합니다.

