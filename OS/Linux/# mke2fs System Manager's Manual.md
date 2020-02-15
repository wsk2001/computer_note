# mke2fs System Manager's Manual

출처: http://man7.org/linux/man-pages/man8/mke2fs.8.html

## NAME

```
mke2fs - create an ext2/ext3/ext4 filesystem
```



## SYNOPSIS

```
mke2fs [ -c | -l filename ] [ -b block-size ] [ -C cluster-size ] [
-d root-directory ] [ -D ] [ -g blocks-per-group ] [ -G number-of-
groups ] [ -i bytes-per-inode ] [ -I inode-size ] [ -j ] [ -J
journal-options ] [ -N number-of-inodes ] [ -n ] [ -m reserved-
blocks-percentage ] [ -o creator-os ] [ -O [^]feature[,...]  ] [ -q ]
[ -r fs-revision-level ] [ -E extended-options ] [ -v ] [ -F ] [ -L
volume-label ] [ -M last-mounted-directory ] [ -S ] [ -t fs-type ] [
-T usage-type ] [ -U UUID ] [ -V ] [ -e errors-behavior ] [ -z
undo_file ] device [ fs-size ]
```



```
mke2fs -O journal_dev [ -b block-size ] [ -L volume-label ] [ -n ] [
-q ] [ -v ] external-journal [ fs-size ]
```



## DESCRIPTION

Mke2fs는 일반적으로 ext2, ext3 또는 ext4 파일 시스템을 만드는 데 사용됩니다.
장치에 의해 명명 된 디스크 파티션 (또는 파일).

파일 시스템 크기는 fs-size로 지정됩니다.  fs-size에 접미사가없는 경우 -b blocksize 옵션을 지정하지 않으면 fs-size가 블록 크기 블록 수로 해석되지 않는 한 2의 제곱 킬로바이트로 해석됩니다.

fs-size에 'k', 'm', 'g', 't'(대문자 또는 소문자)가 접미사로 사용되면 2KB, 메가 바이트, 기가 바이트, 테라 바이트 등 으로 해석됩니다. 

fs-size를 생략하면 mke2fs는 장치 크기에 따라 파일 시스템을 만듭니다.

mke2fs가 mkfs.XXX (즉, mkfs.ext2, mkfs.ext3 또는 mkfs.ext4)로 실행되면 -t XXX 옵션이 내포됩니다. 따라서 mkfs.ext3은 ext3과 함께 사용할 파일 시스템을 작성하고 mkfs.ext4는 ext4와 함께 사용할 파일 시스템을 작성합니다.

아래에 나열된 옵션으로 재정의되지 않은 경우 새로 만든 파일 시스템에 대한 매개 변수의 기본값은 /etc/mke2fs.conf 구성 파일에 의해 제어됩니다.
자세한 내용은 mke2fs.conf (5) 매뉴얼 페이지를 참조하십시오.



## OPTIONS

`-b block-size`

블록 크기를 바이트 단위로 지정하십시오. 유효한 블록 크기 값은 블록 당 1024, 2048 및 4096 바이트입니다.
생략하면 블록 크기는 파일 시스템 크기와 파일 시스템의 예상 사용량에 따라 발견 적으로 결정됩니다 (-T 옵션 참조).
블록 크기 앞에 음수 부호 ( '-')가 있으면 mke2fs는 추론을 사용하여 적절한 블록 크기를 결정하며 블록 크기는 최소 블록 크기 바이트가되어야합니다.
이는 블록 크기가 2k의 배수 여야하는 특정 하드웨어 장치에 유용합니다.



`-c `

파일 시스템을 만들기 전에 장치에 불량 블록이 있는지 확인하십시오.
이 옵션을 두 번 지정하면 빠른 읽기 전용 테스트 대신 느린 읽기/쓰기 테스트가 사용됩니다.



` -C  cluster-size`

bigalloc 기능을 사용하여 파일 시스템의 클러스터 크기를 바이트 단위로 지정하십시오.
유효한 클러스터 크기 값은 클러스터 당 2048-256M 바이트입니다.
bigalloc 기능이 활성화 된 경우에만 지정할 수 있습니다 (bigalloc에 대한 자세한 내용은 ext4 (5) 매뉴얼 페이지를 참조하십시오).
bigalloc이 사용 가능한 경우 기본 클러스터 크기는 블록 크기의 16 배입니다.



`-d root-directory`

주어진 디렉토리의 내용을 파일 시스템의 루트 디렉토리에 복사하십시오.



`-D`

디스크에 쓸 때는 직접 I/O를 사용하십시오.
이렇게하면 mke2fs가 많은 버퍼 캐시 메모리를 더럽 히지 않게되어 사용중인 서버에서 실행중인 다른 응용 프로그램에 영향을 줄 수 있습니다.
이 옵션을 사용하면 mke2fs가 훨씬 느리게 실행되므로 직접 I/O를 사용하는 것과의 균형이 있습니다.



`-e error-behavior`

오류가 감지되면 커널 코드의 동작을 변경하십시오.
모든 경우에 파일 시스템 오류로 인해 다음 부팅시 e2fsck (8)가 파일 시스템을 검사합니다.
오류 동작은 다음 중 하나 일 수 있습니다.

- continue:  정상적인 실행을 계속하십시오.
- remount-ro: 파일 시스템을 읽기 전용으로 다시 마운트하십시오.
- panic: 커널 패닉을 유발합니다.



`-E extended-options`

파일 시스템에 대한 확장 옵션을 설정하십시오. 확장 옵션은 쉼표로 구분되며 등호 ( '=') 기호를 사용하여 인수를 사용할 수 있습니다.
-E 옵션은 이전 버전의 mke2fs에서 -R이었습니다.
-R 옵션은 여전히 이전 버전과의 호환성을 위해 허용되지만 더 이상 사용되지 않습니다.
다음과 같은 확장 옵션이 지원됩니다.

​		`encoding=encoding-name`

​		`encoding_flags=encoding-flags`

​		`mmp_update_interval=interval`

​		`stride=stride-size`

​		`stripe_width=stripe-width`

​		`offset=offset`

​		`resize=max-online-resize`

​		`lazy_itable_init[= <0 to disable, 1 to enable>]`

​		`lazy_journal_init[= <0 to disable, 1 to enable>]`

​		`no_copy_xattrs`

​		`num_backup_sb=<0|1|2>`

​		`packed_meta_blocks[= <0 to disable, 1 to enable>]`

​		`root_owner[=uid:gid]`

​		`test_fs`

​		`discard`

​		`nodiscard`

​		`quotatype`

​		이 부분은 시간 될때 추가 예정



`-F`

지정된 장치가 블록 특수 장치의 파티션이 아니거나 다른 매개 변수가 의미가없는 경우에도 mke2fs가 파일 시스템을 작성하도록합니다.
파일 시스템이 사용 중이거나 마운트되어있는 경우에도 (실제로 위험한 작업) mke2fs가 파일 시스템을 작성하도록하려면이 옵션을 두 번 지정해야합니다.



` -g blocks-per-group`

블록 그룹의 블록 수를 지정하십시오.
기본값은 파일 시스템에 최적이므로 사용자가이 매개 변수를 설정 한 이유는 없습니다.
(RAID 어레이에서 파일 시스템을 작성하는 관리자의 경우 그룹당 블록 수를 조작하는 대신 stride RAID 매개 변수를 -E 옵션의 일부로 사용하는 것이 좋습니다.)
이 옵션은 일반적으로 테스트 사례를 개발중인 개발자가 사용합니다.

bigalloc 기능이 사용 가능한 경우 -g 옵션은 블록 그룹의 클러스터 수를 지정합니다.



`-G number-of-groups`

Ext4 파일 시스템에서 더 큰 가상 블록 그룹 (또는 'flex_bg 그룹')을 작성하기 위해 함께 묶을 블록 그룹 수를 지정하십시오.
메타 데이터가 많은 워크로드에서 메타 데이터 위치 및 성능이 향상됩니다.
그룹 수는 2의 거듭 제곱이어야하며 flex_bg 파일 시스템 기능이 활성화 된 경우에만 지정할 수 있습니다.



`-i bytes-per-inode`

바이트/노드 비율을 지정하십시오.
mke2fs는 디스크의 모든 바이트 당 바이트 당 공간에 대해 inode를 만듭니다.
바이트 당 바이트 비율이 클수록 더 적은 inode가 생성됩니다.
이 값은 일반적으로 파일 시스템의 블록 크기보다 작아서는 안됩니다.이 경우 사용 가능한 것보다 많은 inode가 만들어지기 때문입니다.
파일 시스템이 작성된 후에는이 비율을 변경할 수 없으므로이 매개 변수의 올바른 값을 신중하게 결정하십시오.
파일 시스템의 크기를 조정하면이 비율을 유지하기 위해 inode 수가 변경됩니다.



`-I inode-size`

각 inode의 크기를 바이트 단위로 지정하십시오.
Inode 크기 값은 2보다 크거나 같은 128의 제곱이어야합니다.
Inode 크기가 클수록 inode 테이블이 사용하는 공간이 더 많아지고 파일 시스템에서 사용 가능한 공간이 줄어들고 성능에 부정적인 영향을 줄 수 있습니다.
파일 시스템이 작성된 후에는이 값을 변경할 수 없습니다.

2.6.10 이후의 커널 및 일부 이전 공급 업체 커널에서는 128 바이트보다 큰 inode를 사용하여 성능 향상을 위해 확장 된 속성을 저장할 수 있습니다.
대형 inode에 저장된 확장 속성은 이전 커널에서는 보이지 않으며 이러한 파일 시스템은 2.4 커널로 마운트 할 수 없습니다.

기본 inode 크기는 mke2fs.conf (5) 파일에 의해 제어됩니다.
e2fsprogs와 함께 제공되는 mke2fs.conf 파일에서 inode 크기가 128 바이트 인 작은 파일 시스템을 제외하고 대부분의 파일 시스템의 기본 inode 크기는 256 바이트입니다.



`-j`

ext3 저널을 사용하여 파일 시스템을 작성하십시오.
-J 옵션을 지정하지 않으면 기본 저널 매개 변수를 사용하여 파일 시스템에 저장된 적절한 크기의 저널 (파일 시스템의 크기를 제공함)을 작성합니다.
저널을 실제로 사용하려면 ext3을 지원하는 커널을 사용해야합니다.



`-J journal-options`

명령 행에 지정된 옵션을 사용하여 ext3 저널을 작성하십시오.
저널 옵션은 쉼표로 구분되며 등호 ( '=') 기호를 사용하여 인수를 사용할 수 있습니다.
다음 저널 옵션이 지원됩니다.

​		`size=journal-size`

​		`location=journal-location`

​		`device=external-journal`

​		

`-l filename`

파일 이름에서 불량 차단 목록을 읽습니다.
불량 블록 목록의 블록 번호는 mke2fs에서 사용하는 것과 동일한 블록 크기를 사용하여 생성해야합니다.
결과적으로 mke2fs에 대한 -c 옵션은 포맷하기 전에 디스크를 불량 블록으로 검사하는 훨씬 간단하고 오류가 적은 방법입니다.



`-L new-volume-label`

 파일 시스템의 볼륨 레이블을 new-volume-label로 설정하십시오.
  볼륨 레이블의 최대 길이는 16 바이트입니다.



` -m reserved-blocks-percentage`

수퍼 유저 용으로 예약 된 파일 시스템 블록의 백분율을 지정하십시오.
이렇게하면 조각화가 방지되고 권한이없는 프로세스가 파일 시스템에 쓰지 못하게 된 후 syslogd (8)와 같은 루트 소유 데몬이 계속 올바르게 작동 할 수 있습니다. 기본 백분율은 5 %입니다.



`-M last-mounted-directory`

파일 시스템에 마지막으로 마운트 된 디렉토리를 설정하십시오.
파일 시스템을 마운트 할 위치를 결정하기 위해 마지막으로 마운트 된 디렉토리에서 키 오프하는 유틸리티를 위해 유용 할 수 있습니다.



`-n`

mke2fs가 실제로 파일 시스템을 작성하지 않고 파일 시스템을 작성하는 경우 수행 할 작업을 표시합니다.
파일 시스템을 처음 만들 때 전달 된 mke2fs 매개 변수가 다시 사용되는 한 특정 파일 시스템에 대한 백업 수퍼 블록의 위치를 결정하는 데 사용할 수 있습니다.
(물론 -n 옵션을 추가하면!)



` -N number-of-inodes`

파일 시스템에 예약되어야하는 inode 수 (블록 수 및 아이 노드 당 바이트 수를 기준으로 함)의 기본 계산을 재정의합니다.
이를 통해 사용자는 원하는 inode 수를 직접 지정할 수 있습니다.



`-o creator-os`

파일 시스템의 '작성기 운영 체제'필드의 기본값을 대체합니다.
작성자 필드는 기본적으로 mke2fs 실행 파일이 컴파일 된 OS의 이름으로 설정됩니다.



more ~~~~

