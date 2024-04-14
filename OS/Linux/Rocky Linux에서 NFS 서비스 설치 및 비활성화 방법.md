## Rocky Linux에서 NFS 서비스 설치 및 비활성화 방법

**0. 사전 준비 **

```bash
 netstat -antp
```

위 명령을 실행 하였을때 `-bash: netstat: command not found` 이 나오면 `net-tools` 를 설치 한다.

```bash
dnf install net-tools -y
```

dnf 명령은 superuser 권한으로 실행 해야 하므로 sudo 를 이용하거나 root user 가 실행 해야 한다.




**1. NFS 서비스 설치**

Rocky Linux에서 NFS 서비스를 설치하려면 다음 명령어를 실행합니다.

```bash
sudo dnf install nfs-utils
```

위 명령 실행 후  netstat -antp 명령을 실행 하면 111 번 포트가 LISTEN 상태인 것을 확인 할 수 있다.



**2. NFS 서비스 비활성화**

**2.1. systemctl 명령어 사용**

NFS 서비스를 비활성화 하려면 다음 명령어를 실행합니다.

```bash
sudo systemctl disable nfs-server.service
sudo systemctl stop rpcbind.socket
```

**2.2. chkconfig 명령어 사용**

`chkconfig` 명령어를 사용하여 NFS 서비스를 비활성화하려면 다음 명령어를 실행합니다.

```bash
sudo chkconfig nfs off
```



**3. NFS 서비스 확인**

NFS 서비스가 비활성화되었는지 확인하려면 다음 명령어를 실행합니다.

```bash
sudo systemctl status nfs-server.service
```

**출력 예시**

```bash
● nfs-server.service - Network File System
     Loaded: disabled (/etc/rc.d/init.d/nfs-server)
     Active: inactive (dead)
```



**4. NFS 서비스 활성화**

NFS 서비스를 다시 활성화하려면 다음 명령어를 실행합니다.

```bash
sudo systemctl enable nfs-server.service
```



**5. 추가 정보**

- NFS 서버 설정: `/etc/exports` 파일을 편집하여 공유할 디렉토리와 클라이언트를 설정합니다.
- NFS 클라이언트 설정: `/etc/fstab` 파일을 편집하여 NFS 서버의 공유 디렉토리를 마운트합니다.
- NFS 보안: `firewalld` 또는 `iptables`를 사용하여 NFS 포트를 열고 클라이언트 IP 주소를 허용합니다.



**참고:**

- 위 명령어는 Rocky Linux 8.5 버전 기준입니다. 다른 버전에서는 명령어가 다를 수 있습니다.
- NFS 서비스를 비활성화해도 기존에 마운트된 NFS 공유 디렉토리는 영향을 받지 않습니다.
- NFS 서비스를 다시 활성화하면 기존에 마운트된 NFS 공유 디렉토리가 자동으로 마운트됩니다.