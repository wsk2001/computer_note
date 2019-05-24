# Linux 명령어 정리



## dd

**dd**는 파일을 변환하고 복사하는 것이 주 목적인 [유닉스](https://ko.wikipedia.org/wiki/유닉스) 및 [유닉스 계열](https://ko.wikipedia.org/wiki/유닉스_계열) [운영 체제](https://ko.wikipedia.org/wiki/운영_체제)용 명령 줄 유틸리티이다.

유닉스에서 하드웨어와 특수 [장치 파일](https://ko.wikipedia.org/wiki/장치_파일)용 장치 드라이버는 파일 시스템에서 마치 일반 파일처럼 나타난다. dd는 기능이 개별 드라이버에서 구현되어 있는 경우 이러한 파일들을 읽거나 기록하는 것이 가능하다. 그러므로 dd는 하드 드라이브의 [부팅 섹터](https://ko.wikipedia.org/wiki/부팅_섹터)를 백업하는 등의 일과 고정된 크기의 랜덤 데이터를 취득하기 위해 사용할 수 있다. dd 프로그램은 복사 시 데이터에 변환을 수행할 수도 있는데, 여기에는 [바이트 순서](https://ko.wikipedia.org/wiki/엔디언) 스와핑, [ASCII](https://ko.wikipedia.org/wiki/ASCII)↔[EBCDIC](https://ko.wikipedia.org/wiki/EBCDIC) 텍스트 인코딩 변환을 포함할 수 있다.[[2\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-tfl-dd-2)

dd라는 이름은 [IBM](https://ko.wikipedia.org/wiki/IBM)의 [작업 제어 언어](https://ko.wikipedia.org/wiki/작업_제어_언어)(JCL)에서 발견되는 [DD 문](https://ko.wikipedia.org/wiki/작업_제어_언어#인스트림_입력)과 관련되며[[3\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-jargon-dd-3)[[4\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-4) 여기에서 DD는 "Data Description"을 가리킨다.[[5\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-5) 이 명령의 문법은 다른 유닉스 명령보다 JCL 문과 유사하다.[[3\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-jargon-dd-3)

원래 [ASCII](https://ko.wikipedia.org/wiki/ASCII)와 [EBCDIC](https://ko.wikipedia.org/wiki/EBCDIC) 간의 변환을 위해 고안된 dd는 [버전 5 유닉스](https://ko.wikipedia.org/w/index.php?title=버전_5_유닉스&action=edit&redlink=1)에 처음 등장하였다.[[6\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-6) dd 명령어는 [SUS](https://ko.wikipedia.org/wiki/단일_유닉스_규격)의 일부인 [IEEE](https://ko.wikipedia.org/wiki/IEEE) 표준 1003.1-2008에 규정되어 있다.



### 용도

dd 명령은 다양한 목적을 위해 쓰일 수 있다.



#### 데이터 전송

| dd if=/dev/sr0 of=myCD.iso bs=2048 conv=noerror,sync | CD-ROM으로부터 [ISO](https://ko.wikipedia.org/wiki/ISO_이미지) [디스크 이미지](https://ko.wikipedia.org/wiki/디스크_이미지)를 생성한다. 일부의 경우 작성된 ISO 이미지는 CD-ROM 기록에 사용되는 것과 동일하지 않을 수도 있다. |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| dd if=system.img of=/dev/sdc bs=4096 conv=noerror    | 이전에 만든 이미지로부터 하드 디스크 드라이브(또는 SD 카드)를 복원한다. |
| dd if=/dev/sda2 of=/dev/sdb2 bs=4096 conv=noerror    | 하나의 [파티션](https://ko.wikipedia.org/wiki/하드_디스크_파티션)을 다른 파티션으로 [복제](https://ko.wikipedia.org/wiki/디스크_복제)한다. |
| dd if=/dev/ad0 of=/dev/ad1 bs=1M conv=noerror        | 하드 디스크 드라이브 "ad0"을 "ad1"으로 복제한다.             |



#### 마스터 부트 레코드 백업 및 복원

마스터 부트 레코드를 복구할 수 있다. 복구 파일로부터 전송받거나 복구 파일로 전송할 수 있다.

플로피 드라이브의 처음 두 개의 섹터를 복제:

```
dd if=/dev/fd0 of=MBRboot.img bs=512 count=2
```

완전한 x86 [마스터 부트 레코드](https://ko.wikipedia.org/wiki/마스터_부트_레코드)의 이미지 만들기 (MS-DOS [파티션](https://ko.wikipedia.org/wiki/파티션), MBR 매직 바이트 포함):

```
dd if=/dev/sda of=MBR.img bs=512 count=1
```

[마스터 부트 레코드](https://ko.wikipedia.org/wiki/마스터_부트_레코드)의 부트 코드만의 이미지 만들기 ([파티션 테이블](https://ko.wikipedia.org/wiki/파티션_테이블) 없이, 또 부팅 시 필요한 매직 바이트 없이):

```
dd if=/dev/sda of=MBR_boot.img bs=446 count=1
```



#### 데이터 수정

dd는 특정 자리의 데이터를 수정할 수 있다. 이를테면 아래의 명령을 통해 파일의 최초 512바이트를 널(null) 바이트로 채울 수 있다:

```
dd if=/dev/zero of=path/to/file bs=512 count=1 conv=notrunc
```

notrunc 변환 옵션은 출력 파일을 잘라내지 않는다는 것을 뜻한다. 즉, 출력 파일이 이미 존재하면 지정된 바이트를 대체하되 출력 파일의 나머지 부분만은 남겨둔다. 이 옵션을 사용하지 않으면 dd는 512바이트 길이의 출력 파일을 생성한다.

디스크 이미지 파일로서 특정 디스크 파티션을 다른 파티션에 복제하려면 다음과 같이 실행한다:

```
dd if=/dev/sdb2 of=partition.image bs=4096 conv=noerror
```



#### 디스크 완전 소거

보안 목적으로 이따금은 버림 받은 장치의 [디스크 완전 소거](https://ko.wikipedia.org/wiki/데이터_완전_소거)를 할 필요가 있다.

dd를 이용하여 디스크를 0으로 채워 소거하는 방법은 아래와 같다:

```
dd if=/dev/zero of=/dev/sda bs=4k
```

다른 방법으로 랜덤 데이터를 채워 소거할 수도 있다:

```
dd if=/dev/urandom of=/dev/sda bs=4k
```



#### 데이터 복구

파일, 드라이브, 파티션의 [데이터 복구](https://ko.wikipedia.org/wiki/데이터_복구) 및 복원에 대한 [오픈 소스 소프트웨어](https://ko.wikipedia.org/wiki/오픈_소스_소프트웨어)의 초기 역사에는 GNU dd가 포함되어 있으며, 저작권 고지는 1985년에 시작된다.[[8\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-8) dd 프로세스 당 하나의 블록 크기를 가지며 특정한 형태의 dd를 실행 중인 사용자의 상호작용 세션까지만 한정하여 복구 알고리즘을 제공하였다. 그 뒤 dd_rescue[[9\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-9)라는 C 프로그램이 1999년 10월에 작성되어 해당 알고리즘 안에 두 개의 블록 크기를 가질 수 있었다. 그러나 2003년 dd_rescue의 데이터 복구 알고리즘을 강화한 셸 스크립트 dd_rhelp의 저자는 2004년에 처음 출시된 dd와는 무관한 데이터 복구 프로그램인 GNU [ddrescue](https://ko.wikipedia.org/w/index.php?title=Ddrescue&action=edit&redlink=1)[[10\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-10)[[11\]](https://ko.wikipedia.org/wiki/Dd_(유닉스)#cite_note-11)의 사용을 권장하였다.

더 새로운 GNU 프로그램을 더 오래된 스크립트와 구별하기 위해 GNU의 ddrescue와는 다른 이름이 쓰이기도 하는데 여기에는 addrescue (freecode.com, freshmeat.net에서의 이름), gddrescue ([데비안](https://ko.wikipedia.org/wiki/데비안) 패키지 이름), gnu_ddrescue([오픈수세](https://ko.wikipedia.org/wiki/오픈수세) 패키지 이름)가 포함된다. savehd7이라는 다른 오픈 소스 프로그램은 복잡한 알고리즘을 사용하지만 사용을 위해 자체 프로그래밍 언어 인터프리터의 설치가 필요하다.



#### 드라이브 성능 벤치마크

드라이브 벤치마크를 테스트하고, 1024바이트 블록에 대한 순차 시스템 읽기/쓰기 성능을 분석하려면 다음과 같이 진행한다:

```
dd if=/dev/zero bs=1024 count=1000000 of=file_1GB
dd if=file_1GB of=/dev/null bs=1024
```



#### 랜덤 데이터로 파일 생성

커널 랜덤 드라이버를 이용하여 랜덤 데이터로 이루어진 100바이트의 파일을 채우려면 다음과 같이 진행한다:

```
dd if=/dev/urandom of=myrandom bs=100 count=1
```



#### 파일을 대문자로 변환

파일을 대문자로 변경하려면 다음과 같이 진행한다:

```
dd if=filename of=filename1 conv=ucase
```



#### 옵션

| 옵션         | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| bs           | Bytes, 한번에 읽고 쓸 최대 바이트 크기를 지정합니다. (block size) |
| cbs          | Bytes, 한번에 변환 작업 가능한 바이트 크기를 지정합니다.     |
| ibs          | Bytes, 한번에 읽어드리는 입력단위 바이트를 지정합니다.       |
| count        | Blocks, 지정한 블록(bs) 수 만큼 복사합니다.                  |
| conv         | 기호목록에 따라 파일을 변환합니다.                           |
| conv=ascii   | EBCDIC코드를 ASCII코드로 변환합니다.                         |
| conv=ebcdic  | ASCII코드를 EBCDIC코드를 변환합니다.                         |
| conv=ibm     | ASCII코드를 EBCDIC코드로 EBCDIC코드를 ASCII코드로 변환합니다. |
| conv=block   | cbs 단위로 변환 할 때 줄바꿈 문자를 공백으로 변환합니다.     |
| conv=unblock | cbs단위로 변환 할 때 공백문자를 줄바꿈 문자로 변환합니다.    |
| conv=lcase   | 영어 대문자를 소문자로 변환 합니다.                          |
| conv=ucase   | 영어 소문자를 대문자로 변환 합니다.                          |
| conv=swab    | 입력되는 두 바이트의 순서를 변경합니다.                      |
| conv=sync    | ibs크기와 obs크기가 차이가 있을 경우 NULL 문자로 대체합니다  |
| conv=excl    | 출력파일이 이미 존재 하면 수행하지 않습니다.                 |
| conv=nocreat | 출력파일이 존재해야 수행합니다.                              |
| conv=notrunc | 출력파일을 자르지 않고 출력합니다.                           |
| conv=noerror | 입력데이터를 읽을 때 에러가 발생하여도 계속 진행합니다.      |
| if           | File, 지정한 파일을 입력대상으로 설정합니다. (예: if=/dev/zero) |
| obs          | Bytes, 한번에 작성하는 출력단위 바이트를 지정합니다.         |
| of           | File, 지정한 파일을 출력대상으로 설정합니다.                 |
| seek         | N, 지정한 단위만큼 obs 크기를 건너뛰고 출력을 시작합니다.    |
| skip         | N, 지정한 단위 만큼 ibs크기를 건너뛰고 입력을 시작합니다.    |




#### fuse2fs 를 위한 image 파일 생성

```
dd if=/dev/zero of=fuse_data.img bs=4096 count=500000
```

설명:  fuse_data.img 파일을 2G Bytes 용량으로 만들고, 내부는 0 으로 채운다.

파일의 용량은 bs x count 로 결정 된다.



## e2fsck

출처: <https://realforce111.tistory.com/38>

e2fsck는 리눅스 파일 시스템 점검 및 복구를 할 수 있는 명령어 입니다. fsck의 확장 명령어라고 할 수 있으며 리눅스에서 사용가능한 거의 모든 종류의 파일시스템의 점검과 복구를 할 수 있는 명령어 입니다.

 리눅스는 부팅단계에서 /etc/rc.d/rc.sysinit 스크립트에 내용에 따라 fsck 가 /etc/fstab파일을 참조하여 각 마운트된 (디스크)장치의 파일 시스템을 점검합니다. 그런데 점검도중 파일시스템이 에러 난 파티션이 있다면 부팅이 되지 않습니다.

이럴경우 Root 비밀번호 입력 후 (Repair filesystem):e2fsck /dev/sdb1 입력 및 리부팅 해주면 정상부팅이 됩니다.

**fsck (e2fsck ) 명령어를 사용하여 파일시스템을 점검할 때에는 파티션을 언마운트 후 실행해야 합니다.** 
**언마운트 하지않고 실행하면 파일시스템에 손상 갈 수 있습니다.**



*** fsck 명령어 사용 방법**

```
# e2fsck -f /dev/sdb1 
-f : 파일시스템이 이상이 없더라도 강제적으로 파일 시스템을 점검 할 때 사용 

# e2fsck -j ext3 /dev/sda1 
-j : 저널링 파일 시스템을 대상으로 작업할때 사용 하는 옵션. 저널링 파일 시스템(보통 ext3)를 대상으로 작업할때 옵션이 아니라 필수 해줘야 하는 옵션 

# e2fsck -j ext3 -fv /dev/sda2 
-v : 점검내역 상세 보기(ext3의 파일 시스템으로 구성된 sda1파티션을 강제로 점검하면서 상세내역도 보는 명령) 

# e2fsck -b 98304 -p /dev/sda1 
-p : 오류 있는 파일 시스템의 자동 복구 

# e2fsck -b 98304 -y /dev/sdb1 
-y : 수행 과정의 질문에 모두 yes라 응답하는 옵션 

# e2fsck -b 98304 -n /dev/sdb1 
-n : 수행 과정의 질몬에 모두 no 로 응답하는 옵션 

# e2fsck -j ext3 -cv /dev/sda2 (/dev/sda2 파일 시스템내의 배드블록이 있다면 배드블록 아이노드에 마킹하는 작업)
-c : 점검 과정에서 찾게된 베드블록을 마킹하여 사용치 못하게 하는 옵션
```



위와 같은 많은 방법을 동원하여 복구 하려 해도 안될 때 백업 슈퍼 블록을 이용하여 파일 시스템을 복구할 수 있습니다.

```
# dumpe2fs /dev/sda1 | grep superblock     // 슈퍼 블록 위치 파악
```



```
# e2fsck -b 57345 -f -j ext3 -y /dev/sda1
```

sda1 파티션의 파일시스템을 4번째 슈퍼블록 복사본을 이용하여 강제로 복구 실행합니다.
모든 질문에 yes로 응답해 주시면 됩니다.



#### 옵션

| -p   | 파일시스템을 검사하면서 자동으로 복구해준다. |
| ---- | -------------------------------------------- |
| -n   | 파일 시스템을 바꾼다.                        |
| -y   | 모든 질문에대한 응답을 yes 로 취급한다.      |
| -c   | BAD BLOCK 을 CHECK 한다.                     |
| -f   | 깨끗한 파일시스템까지 강제적으로 check 한다. |



#### fuse2fs 에서 생성한 파일을 사용한 예

```
e2fsck -f fuse_data.img
```





## mkfs

리눅스 파일 시스템 만들기

dd 명령으로 disk image 를 생성 한 경우 mount 하기 전에 format 을 수행 한다.

사용 예) mkfs -t ext4 /dev/sdb1

따로 옵션을 주지 않으면 ext2 파일 시스템 형식으로 작성 한다.



#### 사용법

 mkfs [ -V ] [ -t 형태 ] [ fs-options ] 장치이름 [ 블럭 ]



#### 설명 

mkfs 명령은 한 장치(보통 한 하드디스크 파티션)를 리눅스 파일 시스템으로 만드는데 사용된다. 장치이름은 /dev/hda1, /dev/sdb2 이런 식의 디스크 장치 이름이거나, /, /usr/, /home 같은 파일시스템을 위한 마운트 경로가 사용된다. 블럭인자는 그 파일 시스템을 위해 사용되는 블럭의 개수이다. 성공적으로 끝나면, 0, 실패하면, 1을 리턴한다.
실질적으로, mkfs 명령은파일 시스템을 만드는데 각각 그 특정 파일 시스템을 만드는명령을 사용한다. 그 프로그램들의 이름은 대게 mkfs . 파일 시스템 이름 형식 으로 되어 있다. 그 프로그램들은 먼저 /etc/fs 경로안에 있거나, 아니면, /sbin 경로안에 있게 된다. 특정 파일 시스템을 생성하는 프로그램들에 대한 보다 자세한 사항은 각 파일 시스템을 만드는 각 프로그램들 의 매뉴얼 페이지를 참조한다.



#### 사용 예

fdisk -l 로 파티션 확인 

hda9를 ext3로 포멧하는 예제

mkfs -V -t ext3 /dev/hda9  



#### fuse2fs 에서 생성한 파일을 사용한 예

```
mkfs ext4 -F fuse_data.img 
```





## resize2fs

#### 용도

디스크 볼륨 사이즈 증가시키기



#### 사용법

resize2fs 파일명 용량



#### 사용 예

```
resize2fs /dev/vg_data/lv_data 200M
```



#### fuse2fs 에서 생성한 파일을 사용한 예

```
resize2fs fuse_data.img 4000
```







