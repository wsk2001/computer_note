# CentOS 6.9 서비스 종료 후 yum 사용 방법

CentOS 6은 2020 년 11 월 30 일에 수명이 종료되었습니다. YUM이 더 이상 사용되지 않는 저장소에 연결을 시도합니다. 따라서 더 이상 사용되지 않는 저장소 URL을 업데이트하고 저장소를 가리켜 야합니다.

CentOS 6 reached end of life on the 30th November 2020. YUM attempting connect to deprecated repositories. So, we need update deprecated repositories URL’s and point to the vault.



### 조치

1. cd /etc/yum.repos.d/
2. cp CentOS-Base.repo CentOS-Base.repo.old
3. vi CentOS-Base.repo
4. [base] [updates] [extras]  ----아래 내용 복붙 ----
5. yum clean all

---아래 내용 복붙----
```ini
[base]
name=CentOS-$releasever - Base
# mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=os&infra=$infra
# baseurl=http://mirror.centos.org/centos/$releasever/os/$basearch/
baseurl=https://vault.centos.org/6.10/os/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6

# released updates
[updates]
name=CentOS-$releasever - Updates
# mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=updates&infra=$infra
# baseurl=http://mirror.centos.org/centos/$releasever/updates/$basearch/
baseurl=https://vault.centos.org/6.10/updates/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6

# additional packages that may be useful
[extras]
name=CentOS-$releasever - Extras
# mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=extras&infra=$infra
# baseurl=http://mirror.centos.org/centos/$releasever/extras/$basearch/
baseurl=https://vault.centos.org/6.10/extras/$basearch/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6
```



## CentOS 6.x 에 GCC 4.9 설치 방법

출처: https://superuser.com/questions/834862/how-to-install-g-4-9-2-in-cent-os-6-5#

- 위의 방법대로 CentOS-Base.repo 를 수정 한다.

- 다음 방법 으로 g++ 4.9 를 설치 한다.

  ```bash
  cd /etc/yum.repos.d
  wget http://linuxsoft.cern.ch/cern/scl/slc6-scl.repo
  yum -y --nogpgcheck install devtoolset-3-gcc devtoolset-3-gcc-c++
  ```

  실제로 새로 설치된 g++ 4.9 를 사용 하려면 다음 명령을 실행 후 컴파일 한다.

  다음 명령을 실행 하면 새로운 shell 이 획득 된다.

  ``` bash
  scl enable devtoolset-3 bash
  ```

- 기존에 g++ version 4.4.7 이 설치되어 있는 장치에 g++ 4.9.1 을 추가로 설치 하였다.

- 한개의 장치에 각각 다른 버전의 g++ 을 설치 할 수 있다.

