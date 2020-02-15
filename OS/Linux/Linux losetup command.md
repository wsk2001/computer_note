# Linux losetup command

출처: https://www.computerhope.com/unix/losetup.htm

 Linux 운영 체제에서 losetup 명령은 루프 장치를 설정하고 제어합니다.



## Syntax

```
losetup loopdev
losetup -a
losetup -j file [-o offset]
losetup -d loopdev...
losetup -f
losetup [{-e|-E} encryption] [-o offset] [--sizelimit size] [-p pfd] 
        [-r] {-f[--show]|loopdev} file
losetup -c loopdev
```

losetup은 루프 장치를 일반 파일 또는 블록 장치와 연결하고 루프 장치를 분리하고 루프 장치의 상태를 쿼리하는 데 사용됩니다.

 vnd (vnode 디스크) 또는 lofi (루프백 파일 인터페이스)라고도하는 루프 장치는 파일을 블록 장치로 액세스 할 수 있도록하는 의사 장치입니다.

 loopdev 인수 만 제공되면 해당 루프 장치의 상태가 표시됩니다.



## Options

크기 및 오프셋 인수 뒤에 이진 (2 ^ N) 접미사 KiB, MiB, GiB, TiB, PiB 및 EiB가 올 수 있습니다 ( 'iB'는 선택 사항입니다. 예를 들어 'K'는 'KiB'와 동일한 의미를 가짐) 또는 10 진수 (10 ^ N) 접미사 KB, MB, GB, PB 및 EB.

| **-a**, **--all**                                  | 모든 루프 장치의 상태 표시                                   |
| -------------------------------------------------- | ------------------------------------------------------------ |
| **-c**, **--set-capacity** *loopdev*               | 지정된 루프 장치와 연관된 파일의 크기를 강제로 루프 드라이버가 다시 읽도록합니다. |
| **-d**, **--detach** *loopdev...*                  | 지정된 루프 장치와 관련된 파일 또는 장치를 분리합니다        |
| **-e**, **-E**, **--encryption** *encryption_type* | 지정된 이름 또는 번호로 데이터 암호화 사용                   |
| **-f**, **--find**                                 | 사용하지 않은 첫 번째 루프 장치를 찾습니다.  파일 인수가 존재하면 이 장치를 사용하십시오. 그렇지 않으면 이름을 사용하십시오. |
| **-h**, **--help**                                 | 도움말 출력                                                  |
| **-j**, **--associated** *file*                    | 주어진 파일과 관련된 모든 루프 장치의 상태 표시              |
| **-k**, **--keybits** *num*                        | 키에서 사용할 비트 수를 num으로 설정하십시오.                |
| **-N**, **--nohashpass**                           | 비밀번호를 해시하지 마십시오. 기본적으로 데비안 시스템은 해시 기능을 통해 비밀번호를 실행하지만, 데비안 이외의 시스템에서는 그렇지 않을 수 있습니다. |
| **-o**, **--offset** *offset*                      | 데이터 시작 오프셋 지정                                      |
| **--sizelimit** *size*                             | 데이터 끝은 데이터 시작 후 크기 바이트 이하로 설정됩니다.    |
| **-r**, **--read-only**                            | 읽기 전용 루프 장치 설정                                     |
| **--show**                                         | -f 옵션과 파일 인수가 있으면 장치 이름을 인쇄하십시오. 이 옵션의 짧은 형식 (-s)은 더 이상 사용되지 않습니다. 이 짧은 형식은 --sizelimit에 동일한 옵션이 사용되는 Loop-AES 구현과 충돌 할 수 있습니다. |



## Encryption

 -E 및 -e 옵션 중 하나를 사용하여 전송 기능 (암호화 / 암호 해독 또는 기타 목적으로)을 지정할 수 있습니다. 원하는 암호화를 지정하는 두 가지 메커니즘이 있습니다 : 번호 및 이름. 암호화가 숫자로 지정되면, 리눅스 커널이 아마도 커널을 패치함으로써 그 숫자로 암호화에 대해 알고 있는지 확인해야합니다. 항상 존재하는 표준 번호는 0 (암호화 없음) 및 1 (XOR 암호화)입니다. Cryptoloop 모듈이로드되거나 컴파일되면 번호 18을 사용합니다.이 cryptoloop 모듈은 임의 암호화 유형의 이름을 사용하여 해당 암호화 수행 방법을 알고있는 모듈을 찾습니다.



## 종료 상태

 losetup은 성공하면 종료 상태 0을, 실패하면 0이 아닌 값을 반환합니다. losetup은 루프 장치의 상태를 표시 할 때 장치가 구성되지 않은 경우 1을, 오류가 발생하여 losetup이 장치의 상태를 판별하지 못하게하는 경우 2를 리턴합니다.



## 제한 사항

 DES 암호화는 매우 느립니다. 반면에 간단한 XOR 방법은 매우 약합니다. 둘 다 새로운 알고리즘에 비해 안전하지 않은 것으로 간주됩니다. 또한 일부 암호는 사용이 가능하도록 라이센스가 필요할 수 있습니다.

 Cryptoloop는 dm-crypt를 위해 사용되지 않습니다. 자세한 내용은 cryptsetup을 참조하십시오.



## Examples

로드 가능한 루프 장치 모듈을 사용하는 경우 먼저 다음 명령으로 모듈을로드해야합니다.

```sh
modprobe loop
```

 

암호화 모듈이 필요할 수도 있습니다.

```sh
modprobe des
```

```sh
modprobe cryptoloop
```



### 다음 명령은 루프 장치를 사용하는 예입니다.

```sh
dd if=/dev/zero of=/file bs=1k count=100
```

```sh
losetup -e des /dev/loop0 /file
```

Password:
Init (up to 16 hex digits):

```sh
mkfs -t ext2 /dev/loop0 100
```

```
mount -t ext2 /dev/loop0 /mnt
```

...

```
umount /dev/loop0
```

```
losetup -d /dev/loop0
```

로드 가능한 모듈을 사용하는 경우 다음 명령으로 모듈을 제거 할 수 있습니다.

```
rmmod loop
```



## 관련 명령

[**dd**](https://www.computerhope.com/unix/dd.htm) —  파일 인코딩을 복사하고 변환합니다.
[**mkfs**](https://www.computerhope.com/unix/mkfs.htm) —  일반적으로 하드 디스크 파티션 인 Linux 파일 시스템 구축.
[**mount**](https://www.computerhope.com/unix/umount.htm) —  데이터에 액세스 할 수 있도록 파일 시스템을 마운트합니다.



