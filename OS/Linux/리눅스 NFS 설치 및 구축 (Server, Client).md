# 리눅스 NFS 설치 및 구축 (Server, Client)

- **리눅스 NFS 설치 및 구축**

- **Server**

``` bash
# nfs 설치
yum -y install nfs-utils

# 서비스 시작 / 부팅 시 자동 시작
systemctl start nfs-server
systemctl start rpcbind
systemctl enable nfs-server
systemctl enable rpcbind

# 공유할 디렉토리 생성
mkdir /nfstest
chmod 700 /nfstest

# mount 설정
# 읽고 쓰기(rw) 가능, 클라이언트의 계정을 root로 매핑(no_root_squash), 서버-클라이언트 동기화(rync)
/nfstest 192.168.0.*(rw,no_root_squash,rync)

# 서비스 재시작
systemctl restart nfs-server

# 설정 확인
exportfs -v
```



읽고 쓰기가 가능해야한다면 아래와 같이 rw가 재대로 설정 되었는지 확인해야 합니다.

참고로 /etc/exports 에서 IP후 띄어쓰기 없이 (rw) 설정을 해야하고, 만약 띄어쓰기를 사용하면 설정이 재대로 되지 않아 Read-only 설정으로 되서 에러가 발생할 수 있습니다.



- **Client**

``` bash
# nfs 설치
yum -y install nfs-utils

# 서비스 시작 / 부팅 시 자동 시작
systemctl start nfs-server
systemctl enable nfs-server

# 공유할 디렉토리 생성
/nfstest
chmod 700 /nfstest

# mount 임시 적용
# 서버의 /nfstest 디렉토리를 로컬 /nfstest에 마운트
mount -t nfs IP:/nfstest /nfstest

# mount 영구 적용
vi /etc/fstab
IP:/nfstest		/nfstest	nfs	defaults	0 0
```

