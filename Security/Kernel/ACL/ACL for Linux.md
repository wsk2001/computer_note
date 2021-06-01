# ACL for Linux

이 문서는 Linux 환경에서 ACL을 사용하는 방법에 관하여 기술 합니다.



## 1. How to Configure ACL(Access Control Lists) in Linux FileSystem

출처: https://www.thegeekdiary.com/how-to-configure-aclaccess-control-lists-in-linux-filesystem/

파일 및 디렉토리에 대한 기존 Linux 액세스 권한은 파일 또는 디렉토리의 소유자, 파일 또는 디렉토리가 연관된 그룹의 구성원 및 기타 모든 사람에 대한 읽기, 쓰기 및 실행 권한 조합을 설정하는 것으로 구성됩니다. 액세스 제어 목록 (ACL)은 이러한 전통적인 Linux 액세스 권한보다 세분화 된 액세스 제어 메커니즘을 제공합니다.

역자주) 본 문서는 yum 을 사용하는것 으로 봐서 RedHat 계열의 OS 에서 테스트 한 것으로 보이며, 실제 테스트는 CentOS 및 Ubuntu 에서 수행 할 예정 입니다.



### 1.1 Installing ACL

 파일이나 디렉토리에 대해 ACL을 사용하기 전에 다음 acl 패키지를 설치하십시오.

``` bash
# yum install acl
```



### 1.2 Configuring ACL on a file system

파일 또는 디렉토리가 들어있는 파일 시스템도 ACL 지원으로 마운트해야합니다. 다음은 ACL을 지원하는 로컬 ext3 파일 시스템을 마운트하는 구문입니다.

``` bash
mount -t ext3 -o acl [device-name] [mount-point]
```

For example:

``` bash
# mount -t ext3 -o acl /dev/mapper/VolGroup00-LogVol00 /data
```

파티션이 /etc/fstab 파일에 나열되어 있으면 acl 옵션을 포함시킵니다.



### 1.3 ACL Rules

ACL은 ACL이 연관된 파일 또는 디렉토리에 사용자 또는 그룹이 액세스 할 수있는 방법을 지정하는 일련의 규칙으로 구성됩니다. ACL 규칙에는 두 가지 유형이 있습니다.

- **access ACLs**: 단일 파일 또는 디렉토리에 대한 액세스 정보 지정
- **default ACLs**: 디렉토리에만 관련됩니다. 액세스 ACL이없는 디렉토리 내의 모든 파일에 대한 기본 액세스 정보를 지정합니다.



### 1.4 Display ACLs on files

getfacl 유틸리티를 사용하여 파일의 ACL을 표시하십시오. 파일에 ACL이 없으면 다른 형식이지만 'ls -l'과 동일한 정보를 표시합니다. 예를 들어, 파일 테스트에는 ACL이 없습니다.

```bash
# ls –l test
-rw-rw-r-- 1 oracle oracle 25 Mar 5 10:10 test
```

테스트 파일의 샘플 getfacl 출력 :

```bash
# getfacl test 
# file: test
# owner: oracle 
# group: oracle 
user::rw- 
group::rw- 
other::r--
```



### 1.5 Configuring ACLs on Files

**setfacl** 유틸리티를 사용하여 파일의 ACL에 하나 이상의 규칙을 추가하거나 수정하십시오. 구문은 다음과 같습니다.

```bash
# setfacl -m [rules] [files]
```

규칙은 다음과 같은 형식으로되어 있습니다.

- **u:name:permissions**: 사용자 (사용자 이름 또는 UID)에 대한 액세스 ACL을 설정합니다.)
- **g:name:permissions**: 그룹에 대한 액세스 ACL (그룹 이름 또는 GID)을 설정합니다.
- **m:permissions**: 유효 권한 마스크를 설정합니다. 이것은 소유 그룹의 모든 권한과 모든 사용자 및 그룹 항목의 합집합입니다.
- **o:permissions**: 다른 모든 사용자의 액세스 ACL을 설정합니다 (기타).

사용 권한은 각각 읽기, 쓰기 및 실행에 대한 전통적인 r, w 및 x입니다. 다음 예는 oracle 사용자에게 해당 파일에 대한 읽기 및 쓰기 권한을 부여하는 테스트 파일의 ACL에 규칙을 추가합니다.

```bash
# setfacl -m u:oracle:rwx test
```

getfacl의 출력에는 ACL 규칙이 포함됩니다.

```bash
# getfacl test 
# file: test
# owner: oracle 
# group: oracle 
user::rw- 
user:oracle:rwx 
group::rw- 
mask::rwx 
other::r--
```

파일에 ACL이 있으면 'ls -l'은 사용 권한 다음에 더하기 기호 (+)를 표시합니다.

```bash
# ls –l test
-rw-rwxr--+ 1 oracle oracle 25 Mar 5 10:10 test
```



### 1.5 Removing ACLs of Files

사용자 또는 그룹에 대한 규칙을 제거하는 권한을 지정하지 않고 **-x** 옵션을 사용하십시오.

```bash
# setfacl –x u:oracle test
```

ACL 자체를 제거하려면 -b 옵션을 사용하십시오.

```bash
# setfacl –b test
```



### 1.6 Setting the Default ACLs

기본 ACL을 설정하려면 규칙 앞에 d :를 추가하고 파일 이름 대신 디렉토리를 지정하십시오.

```bash
# setfacl -m d:o:rx /share
```



## 2. UNIX / Linux : 액세스 제어 목록 (ACL) 기본 사항

출처: https://www.thegeekdiary.com/unix-linux-access-control-lists-acls-basics/



### 2.1 ACL이 필요한 이유는 무엇입니까?

모든 UNIX 파일의 모든 파일에는 소유자/그룹 및 사용 권한 세트가 있습니다. 여러 사용자가 동일한 파일에 액세스해야하고 사용자가 다른 그룹에 속한 경우를 상상해보십시오. 파일 액세스 제어 목록 (FACL) 또는 단순히 ACL은 추가 사용자/그룹 목록 및 파일에 대한 권한입니다.



### 2.2 파일에 ACL이 첨부 된시기를 확인하는 방법

파일에 첨부 된 ACL이 언제 있는지 쉽게 알 수 있습니다. ls -l 명령은 아래와 같이 출력을 생성합니다.

```bash
# ls -l
-rw-r--r-+ 1   root   root  0  Sep  19  14:41  file
```

권한 끝에 **+** 기호가 표시됩니다. 그러면 파일에 첨부 된 ACL이 있음을 확인합니다.



### 2.3 ACL보기

파일의 ACL 정보를 표시하려면 **getfacl** 명령을 사용하십시오.

```
 getfacl /tmp/test
# file: test
# owner: root
# group: root
user::rw-
user:john:rw-
user:sam:rwx
group::r--
mask::rwx
other:---
```

3 개의 다른 user : 행에 주목하십시오. 첫 번째 줄에는 파일 소유자의 표준 파일 사용 권한이 나열되어 있습니다. 다른 2 개의 사용자 권한은 john 및 sam 사용자에 대한 개별 권한입니다. 여기서 마스크 필드는 사용자 및 그룹에 부여 된 추가 사용 권한에만 적용됩니다. 마스크가 rwx로 설정된 경우 추가 사용자 / 그룹에 읽기, 쓰기 및 실행 권한이 부여됩니다. 마스크가 r-x로 설정된 경우 추가 사용자 / 그룹에 쓰기 권한이 부여되지 않습니다. 일반적으로 마스크를 rwx 이외의 다른 것으로 설정하지 마십시오. 마스크 값은 표준 UNIX 사용자 / 그룹 / 다른 사용 권한에 영향을 미치지 않습니다.

**ACL이없는 파일**

ACL이없는 파일에서 getfacl 명령을 실행하면 추가 'user :'행과 'mask'행이 표시되지 않으며 표준 파일 권한이 표시됩니다.



### 2.4 FACL 생성 및 관리

setfacl 명령은 주어진 파일에 대해 ACL을 설정하는 데 사용됩니다. /tmp/test 파일에서 john 사용자에게 rw 액세스 권한을 부여하려면 다음을 입력하십시오.

```bash
# setfacl -m u:john:rw /tmp/test
```

-m 옵션은 **setfacl**에게 명령 행에 언급 된 파일의 ACL을 수정하도록 지시합니다. 사용자 john 대신에 파일에 대한 특정 권한을 가진 그룹을 가질 수 있습니다.

```bash
# setfacl -m g:accounts:rw /tmp/test
```

여러 사용자 및 그룹에 대한 FACL은 단일 명령으로 설정할 수도 있습니다.

```bash
# setfacl -m u:john:rw,g:accounts:rwx /tmp/test
```



### 2.5 디렉토리의 기본 FACL

기본 ACL은 디렉토리에서만 작성됩니다. 디렉토리에 기본 ACL을 설정하면 해당 디렉토리에서 작성된 모든 파일에도 기본 FACL이 자동으로 할당됩니다.

디렉토리에 기본 FACL을 작성하려면 다음을 수행하십시오.

```bash
# setfacl -m default:u:john:rw /accounts
```

```bash
# getfacl accounts/
# file: accounts/
# owner: root
# group: root
user::rwx
group::r-x
other::r-x
default:user::rwx
default:user:john:rw-
default:group::r-x
default:mask::rwx
default:other::r-x
```

이제 accounts 디렉토리에 새 파일을 만들고 파일에 FACL을 나열하십시오.

```bash
# touch /accounts/test
# getfacl test
# file: test
# owner: root
# group: root
user::rw-
user:john:rw-
group::r-x			#effective:r--
mask::rw-
other::r--
```



### 2.6 FACL 제거

```bash
# setfacl -x u:john /tmp/test
```

위의 명령은 /tmp/test 파일에서 사용자 john에 대한 ACL을 제거합니다. 다른 사용자 / 그룹에 대한 ACL은 영향을받지 않습니다.	

파일과 관련된 모든 ACL을 제거하려면 **setfacl**과 함께 **-b** 옵션을 사용하십시오.

```bash
# setfacl -b /tmp/test
```



### 2.7 FACL 백업

많은 경우, 백업 소프트웨어는 FACL과 관련된 메타 데이터를 파일에 복사하지 않을 수 있습니다. 이 경우 파일에 대한 FACL 정보를 백업 할 수 있습니다. 이제 디렉터리의 모든 파일 (모든 하위 디렉터리 포함)에 대한 FACL을 단일 파일로 복사 할 수 있습니다.

```bash
# cd /accounts
# getfacl -R * > accounts_facl        ( -R -> recursive )
```



### 2.8 FACL 복원

/accounts 디렉토리의 파일을 복원 할 때 해당 디렉토리에있는 파일과 연관된 FACL을 복원해야합니다. 이를 수행하려면 -**restore** 옵션과 함께 FACL 백업 파일 **accounts_facl**을 사용하십시오.

```bash
# setfacl --restore=accounts_facl
```



