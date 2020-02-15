# MOUNT 명령어 옵션 정리

출처: https://bigsun84.tistory.com/315

**mount** : 보조기억 장치, 다른 파일시스템을 디렉토리처럼 사용할 수 있는 명령어 (사용하는 부분만 쓰다보니 틀린 부분이 많습니다. )



\### 옵션 정리 



-a : fstab에 있는 모든 파일 시스템을 마운트한다.

-c : 

-F : 
-f : 실제로 마운트하지 않고 마운트를 할 수 있는지 확인한다.

-i : 

-l : 

-n : /etc/mtab 파일을 쓰기 작업을 하지 않고 마운트한다.

-O : 

-o : 마운트 옵션을 사용한다.

-r : 읽기만 가능하게 마운트한다.

-t : 파일시스템 타입을 선택한다.

-v : 자세한 작업 메세지를 출력한다.

-w : 읽기/쓰기 모드로 마운트 한다.

-V, --version : 버전 정보를 출력한다.

-h, --help : 도움말을 출력한다.



\## -t 옵션 파일시스템 (일부)

iso9660 : CD-ROM 의 파일 시스템

vfat : window 파일시스템

ext2 : 리눅스에서 사용되는 파일 시스템

ext3 : ext3 + 저널링 파일시스템

nfs : Network File System

cifs : window 공유파일에서 사용되는 파일 시스템



\## -o 옵션

default : 기본적으로 rw, suid, dev, auto, nouser, async 으로 설정한다.

ro : 읽기 전용으로 마운트 한다.

rw : 읽기/쓰기로 마운트 한다.

auto : -a 옵션으로 마운트 한다.

noauto : -a 옵션으로 마운트 하지 않는다.

exec : 실행파일이 실행 가능하도록 마운트한다.

noexec : 실행파일이 실행되지 않게 마운트 한다.

user : 일반 계정의 마운트를 허용한다.

nouser : 일반 계정의 마운트를 허용하지 앟는다.

async : 파일시스템에 대한 입출력이 비동기적으로 이루어지게 한다.

sync : 파일시스템에 대한 입출력이 동기적으로 이루어지게 한다.

remount : 마운트된 부분을 다시 마운트한다.

dev : 파일시스템의 문자, 블록, 특수장치를 확인한다.

suid : set-user-identifer, set-group-identifier을 적용되도록 허용한다.

nosuid : set-user-identifer, set-group-identfier을 적용되도록 허용하지 않는다.

umask : 마운트된 디렉토리에 파일과 디렉토리의 퍼미션을 default값을 지정한다.

fmask : 마운트된 파일의 퍼미션만 설정한다.

dmask : 마운트된 디렉토리만 설정한다.

