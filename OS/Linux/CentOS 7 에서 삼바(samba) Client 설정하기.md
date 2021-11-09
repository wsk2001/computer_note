# CentOS 7 에서 삼바(samba) Client 설정하기

출처: https://nonaka.tistory.com/182



### 0. 공유서버 계정 정보

``` bash
[windows]
windows 용 test dir: \\10.10.10.110\dev\TechNote\NAS\TSS
user = wonsool
pwd = kang0987

[CentOS]
linux 용 test dir: \\10.10.10.110\dev\TechNote\NAS\Linux
user = wonsool
pwd = kang0987
```





### 1. samba 관련 패키지 설치 및 기동

``` bash
sudo yum -y install samba samba-client cifs-utils
```

- 설치된 package 확인

  ``` bash
  yum list installed
  ```

  



### 2. mount 할 폴더 생성

``` bash
mkdir /share
```



### 3. 아래의 명령어로 mount

``` bash
mount -t cifs -o username=user,password=passwd,vers=3.0 //server_ip/diretory /share

# 주의 vers=3.0 으로 않되면 vers=2.0 으로 할것 (centOS-7)
# centOS-6 은 vers=1.0 으로 하여야 한다.

# 아래는 실제 적용
# mount -t cifs -o username=wonsool,password=kang0987,vers=2.0 //10.10.10.110/dev/TechNote/NAS/Linux /home/tssnas/technet_dir
```

>source: //server_ip/diretory
>
>target: /share

\- credentials 파일로 계정 정보 등록해서 mount

``` bash
# cat /root/.smb.cred
username=user
password=passwd

mount -t cifs -o credentials=/root/.smb.cred,vers=3.0 //server_ip/diretory /share
```



### 4. 시스템 기동 시 자동 mount 등록 방법

\- /etc/fstab 등록

``` bash
//server_ip/diretory /share cifs _netdev,rw,credentials=/root/.smb.cred,vers=3.0  0 0
```

\- systemd에 등록

```bash
# vi share.mount
[Unit]
Description = SMB share
Requires=network-online.target
After=network-online.target

[Mount]
What = //server_ip/diretory
Where = /share
Type = cifs
Options = credentials=/root/.smb.cred,vers=3.0

[Install]
WantedBy = multi-user.target

# systemctl enable share.mount
# systemctl status share.mount
```

