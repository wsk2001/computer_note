# BUSE - A block device in userspace

출처: https://github.com/acozzette/BUSE

 

이 소프트웨어는 FUSE에서 영감을 얻어 개발 하였으며,  사용자 공간에서 실행되는 Linux 파일 시스템을 개발할 수 있습니다. BUSE의 목표는 가상 블록 장치가 사용자 공간에서도 실행될 수 있도록하는 것입니다. 현재 BUSE는 실험용 이므로 프로덕션 코드에 사용해서는 안됩니다.

 BUSE로 블록 장치를 구현하는 것은 매우 간단합니다. 블록 장치의 동작을 정의하는 함수 포인터로 struct buse_operations (buse.h로 선언)를 채우고 크기 필드를 장치의 원하는 크기 (바이트)로 설정하십시오. 그런 다음 buse_main을 호출하고 이 구조체에 대한 포인터를 전달하십시오. busexmp.c는이 작업을 수행하는 방법을 보여주는 간단한 예제입니다.

 BUSE 자체는 Linux 네트워크 블록 장치 인 NBD를 사용하여 원격 시스템이 로컬 시스템의 가상 블록 장치에 대한 읽기 및 쓰기 요청을 처리 할 수 있도록합니다. BUSE는 서버가 BUSE 사용자가 정의한 코드를 실행하면서 동일한 머신에 NBD 서버와 클라이언트를 설정합니다.



##  예제 코드 실행

BUSE에는 busexmp.c에 메모리 디스크를 구현하는 예제 드라이버가 포함되어 있습니다. 예제 코드를 시험해 보려면 make를 실행 한 후 루트로 다음을 실행하십시오.

```bash
modprobe nbd
./busexmp 128M /dev/nbd0
```

 그런 다음 장치 파일 /dev/nbd0 으로 표시되는 메모리 내 디스크가 실행 중이어야합니다. 가상 디스크에서 파일 시스템을 생성하고 마운트 한 다음 파일 읽기 및 쓰기를 시작할 수 있습니다.

```bash
mkfs.ext4 /dev/nbd0
mount /dev/nbd0 /mnt
```

SIGINT 또는 SIGTERM을 수신하면 BUSE가 블록 장치에서 정상적으로 연결을 끊어야합니다. 그러나 문제가 발생하면 블록 장치가 사용할 수없는 상태로 고정되고 BUSE 프로세스가 종료되거나 중단됩니다.

```bash
nbd-client -d /dev/nbd0
```

 실제로이 명령은 클린 연결 해제를 수행하며 실행중인 BUSE 인스턴스를 종료하는 데 사용될 수도 있습니다.



## 테스트

검사를 수행하려면 test / 디렉토리에서 스크립트를 실행할 수 있습니다. 그들은 다음을 요구합니다 :

-  수퍼 유저 권한
-  nbd 커널 모듈로드
-  PATH의 BUSE 및 nbd (nbd-client) 바이너리

 make test는 BUSE가 PATH에 추가되고 sudo를 사용하여 권한을 부여하는 모든 테스트 스크립트를 실행합니다.



 상세도를 높이려면 BUSE_DEBUG를 정의하십시오. make 명령으로이를 수행 할 수 있습니다.

```bash
make test CFLAGS=-DBUSE_DEBUG
```



### - 끝 -

