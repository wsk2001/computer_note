# FUSE 파일 시스템 작성: 튜토리얼

출처: https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/



### Joseph J. Pfeiffer, Jr., Ph.D. 뉴멕시코주립대학교 컴퓨터과학과 명예교수 [pfeiffer@cs.nmsu.edu](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/pfeiffer@cs.nmsu.edu)   

2018-02-04 버전

Unix의 진정한 공헌 중 하나는 "모든 것이 파일"이라는 관점이었습니다. 데이터 저장에서 파일 형식 변환, 내부 운영 체제 데이터 구조에 이르기까지 엄청나게 다양한 종류의 개체가 파일 추상화에 매핑되었습니다.

이 관점이 취한 가장 최근의 방향 중 하나는 사용자 공간의 파일 시스템 또는 FUSE입니다(아니요, 약어가 실제로 작동하지 않습니다. 오 글쎄). 여기서 아이디어는 디렉터리 구조 및 파일 시스템 작업 측면에서 개체와의 상호 작용을 구상할 수 있다면 FUSE 파일 시스템을 작성하여 해당 상호 작용을 제공할 수 있다는 것입니다. `open()`, `read()`, 와 같은 파일 작업을 구현하는 코드를 작성하기만 하면 됩니다 `write()`. 파일 시스템이 마운트되면 프로그램은 코드를 호출하는 표준 파일 운영 체제 호출을 사용하여 데이터에 액세스할 수 있습니다.

[FUSE 파일 시스템은 NFS 또는 CIFS( https://github.com/libfuse/sshfs](https://github.com/libfuse/sshfs) 의 SSHFS 참조)를 사용하지 않고 다른 호스트의 파일에 원격 액세스를 제공하는 것부터 미디어를 사용하여 장치와 통신하는 파일 시스템 구현에 이르기까지 모든 작업을 수행하도록 작성되었습니다. MP3 태그를 기반으로 하는 디렉토리로 음악 컬렉션을 구성하기 위한 전송 프로토콜( [https://github.com/kiorky/jmtpfs 참조 )( ](https://github.com/kiorky/jmtpfs)http://erislabs.net/ianb/projects/id3fs/id3fsd.html 에서 id3fs 참조) ) 정말, 거의 모든 것. 가능성은 당신의 상상력에 의해서만 제한됩니다!

웹에는 FUSE가 작동하는 방식과 FUSE 파일 시스템을 설치 및 사용하는 방법을 설명하는 문서가 많이 있지만 실제로 작성하는 방법을 설명하는 문서는 본 적이 없습니다. 이 튜토리얼의 목표는 그러한 문서가 필요하다고 생각하는 것을 충족시키는 것입니다.

이 튜토리얼은 내가 "Big Brother File System"이라고 부르는 파일 시스템을 사용하여 FUSE를 소개합니다(이름의 이유는 "Big Brother가 보고 있다"이기 때문입니다). 파일 시스템은 단순히 모든 작업을 기본 디렉터리로 전달하지만 작업을 기록합니다.

이 튜토리얼은 관련 예제 파일 시스템과 함께 에서 tarball로 사용할 수 있습니다 . [`http://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial.tgz`](http://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial.tgz)

**대상:** 이 자습서는 Linux(및 일반적으로 Unix 계열 운영 체제)의 일반 프로그래밍에 어느 정도 익숙한 개발자를 대상으로 하므로 tarball의 압축을 푸는 방법, Makefile이 작동하는 방법 등을 알고 있습니다. 이러한 작업을 수행하는 방법에 대한 자세한 내용은 다루지 않겠습니다. FUSE 파일 시스템 사용과 관련하여 알아야 할 사항에 중점을 둘 것입니다.

나는 사용자를 제외하고는 어떤 식으로든 FUSE 프로젝트와 관련이 없습니다. 융합할 인터페이스와 이를 사용하는 기술에 대한 나의 설명은 기존 문서를 읽고 작업한 경험을 정제한 것입니다. 결과적으로 모든 오류는 내 것입니다(수정은 환영합니다!).

## 조직

이 디렉토리 아래에 세 개의 하위 디렉토리가 있습니다.

- `html` html 형식의 자습서 자체를 포함합니다. 튜토리얼 읽기를 시작하려면 [여기를 클릭](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/index.html) 하는 것이 좋습니다 .
- `src` BBFS 파일 시스템 자체에 대한 코드를 포함합니다.
- `example` BBFS 탐색에 사용하기 위한 몇 개의 디렉토리가 포함되어 있습니다.

## 컨설팅

BBFS 또는 FUSE에 관한 일반적인 질문에 답변해 드리겠습니다. 또한 FUSE나 다른 Linux 시스템이나 PIC 마이크로프로세서 개발에 대한 컨설팅도 가능합니다. 관심이 있으시면 다음으로 이메일을 보내주십시오. [`joseph@pfeifferfamily.net`](mailto:joseph@pfeifferfamily.net)

## 특허

[![크리에이티브 커먼즈 라이선스](http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png)](http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_US)
FUSE 파일 시스템 작성: [Joseph J. Pfeiffer, Jr., PhD](http://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/) 의 튜토리얼 은 [Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License에 따라 사용](http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_US) 이 허가되었습니다 .

에 있는 코드 는 LGPLv2에 따라 라이선스가 부여된 에 `src/bbfs.c`있는 함수 프로토타입에서 파생됩니다 . `/usr/include/fuse/fuse.h`내 코드는 GPLv3에 따라 릴리스됩니다. 파일 보기 [`src/COPYING`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/COPYING)



---



# 조직

자습서는 다음 섹션으로 나뉩니다.

- [이 튜토리얼의 파일 및 명명 규칙](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/files.html)

  이 섹션에서는 이 튜토리얼의 일부로 배포되는 파일과 파일 시스템의 기능에 대한 명명 규칙에 대해 설명합니다 `BBFS`. FUSE에 대한 정보를 직접 제공하기 위한 것이 아니라 전체 튜토리얼과 파일 시스템에 대한 개요를 제공하기 위한 것입니다.

- [BBFS 실행](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/running.html)

  `bbfs`를 사용하여 파일 시스템을 마운트하고, 로그를 보고, 마운트 해제하는 방법에 대한 약간의 정보입니다 .

- [콜백 및`struct fuse_operations`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/callbacks.html)

  이것은 FUSE 파일 시스템의 핵심이자 이 튜토리얼의 핵심입니다. 여기에서는 중심 개념에 대해 설명합니다.

- [명령줄 구문 분석 및 FUSE 초기화](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/init.html)

  프로그램 시작하기.

- [개인 데이터 및 관련 주제](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/private.html)

  파일 시스템 상태 유지.

- [불분명한 FUSE 기능에 대한 추가 정보](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/unclear.html)

  이 섹션의 목적은 약간 불분명해 보이는 FUSE 기능에 대한 몇 가지 추가 정보를 제공하는 것입니다. 지금 작성하세요. 이 섹션에서는 `readdir()`FUSE가 파일 생성 플래그를 처리하는 방법을 다룹니다 `open()`.

- [보안 고려 사항 및 경쟁 조건](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/security.html)

  FUSE 파일 시스템을 실행하면 알고 있어야 하는 몇 가지 보안 문제가 발생할 수 있습니다. 또한 FUSE 서버는 다중 스레드이므로 고려해야 할 몇 가지 경쟁 조건이 있을 수 있습니다.

- [감사 및 기타 리소스](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/thanks.html)

  버그를 알아차리거나 다른 방식으로 도움을 준 사람들에게 감사의 말을 전하고 싶습니다. 또한 이것은 웹상의 FUSE에 대한 유일한 리소스가 아닙니다.

[다음: 이 자습서의 파일 및 명명 규칙](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/files.html)



---

# 파일 및 명명 규칙

FUSE 및 Big Brother 파일 시스템 사용에 대해 자세히 알아보기 전에 파일이 구성되는 방식을 살펴보겠습니다.

BBFS 실행 예제의 코드는 하위 디렉토리에 있습니다.`../src`

- [`Makefile`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/Makefile)

  평소와 같이 `Makefile`는 코드 컴파일을 지시하는 데 사용됩니다. 코드가 너무 간단해서 automake를 사용하는 대신 Makefile을 하드 코딩했습니다. 그것은 필요하지만 `pkg-config`(물론 FUSE!), 다른 모든 것은 개발에 사용되는 모든 Linux 시스템에서 사용할 수 있어야 합니다.

- [`bbfs.c`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/bbfs.c)

  Linux 파일 작업을 다시 구현하는 코드는 이 파일에 있습니다. 이 파일에 정의된 모든 함수는 로 시작 `bb_`하므로 시스템 코드에서 내 코드를 쉽게 구분할 수 있습니다.이 파일에는 함수에 대한 포인터가 포함된 `struct fuse_operations `명명 된 도 포함되어 있습니다( 다음 섹션에서 이에 대해 설명합니다). `bb_oper``struct`데이터 구조 의 필드가 가리키는 함수는 모두 표준 접두사 `struct fuse`를 추가하여 필드 이름에서 파생된 이름을 갖습니다 . `bb_`예를 들어 `open()`함수는 `bb_open()`입니다.

- [`log.c`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/log.c)그리고[`log.h`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/log.h)

  수행된 모든 작업을 보고하는 로깅 코드는 에 `log.c`있습니다. `log_`로깅 코드를 식별하는 데 도움이 되도록 이름은 모두 , 다시 시작 합니다. `log.h`다른 곳에서 호출되는 함수의 프로토타입을 포함합니다(물론 이 프로젝트의 유일한 "다른 곳"은 `bbfs.c`).

- [`params.h`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/src/params.h)

  이것은 사용 중인 FUSE API의 버전을 정의하고, `pread()` 시스템 호출에 대한 액세스를 제공하는 기호를 정의하고, `struct bb_state` 파일 시스템 상태를 저장하는 것을 정의합니다.

`#include`또한 FUSE 자체에 대한 거의 모든 문서가 파일 에 있음을 언급해야 합니다 `/usr/include/fuse/fuse.h`. 여기에서 모든 기능에 대한 프로토타입이 제공되고 주석에는 용도가 설명되어 있습니다. 내 `bbfs.c`파일은 내 코드가 삽입된 해당 파일의 복사본으로 시작되었습니다.

[다음: BBFS 컴파일 및 실행](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/running.html)



---

# 환경, 구성, 컴파일 및 실행

튜토리얼의 이 페이지에서는 BBFS 파일 시스템 프로그램을 빌드하고 이를 사용하여 디렉토리를 마운트하는 방법을 설명합니다.

## 환경

이 자습서에서는 FUSE 버전 2.9.x(API 레벨 26)의 사용에 대해 설명합니다. FUSE 자체의 개발은 이 수준을 넘어섰습니다(가장 최근 릴리스는 3.2.1임). 불행히도 개발자들은 버전 3과의 역호환성을 깨뜨렸습니다. 저는 이제 이 튜토리얼을 최신 버전에 맞추기 위해 어떤 변경이 필요한지 살펴보고 있습니다. 그동안 이 튜토리얼의 코드를 사용하려면 이전 버전을 설치하는 것이 좋습니다.

FUSE 라이브러리에 대해 컴파일하려면 라이브러리의 개발 버전을 설치했는지 확인해야 합니다. 데비안과 우분투에서 패키지 이름은 "libfuse-dev"입니다. Fedora에서는 "fuse-devel"입니다. 두 경우 모두 배포판은 fuse 버전 2.9.7만 사용하므로 패키지 관리자를 사용하여 설치하면 이 자습서와 호환되는 버전이 자동으로 사용됩니다.

## 구성 및 컴파일

이 튜토리얼은 구성을 위해 GNU autotools 시스템을 사용합니다. 모든 autotools 기반 프로젝트의 경우와 마찬가지로 최상위 디렉토리로 이동하여 다음을 입력하여 구성하고 컴파일할 수 있습니다.

> ```
> ./구성
> 만들다
> ```

코드를 컴파일하고 사용할 준비가 되어 있어야 합니다.



대부분의 소프트웨어와 달리 이 튜토리얼의 코드는 설치하기 위한 것이 아닙니다. 결과적으로 다양한 설치 대상을 모두 비활성화하려고 했습니다. 하나를 놓친 경우 알려주십시오.

## BBFS 파일 시스템 마운트

명령을 실행하여 BBFS 파일 시스템을 마운트합니다 `bbfs` (일반적으로 FUSE 파일 시스템은 프로그램에 의해 구현되며 해당 프로그램을 실행하여 마운트합니다). `bbfs`에는 루트 디렉토리(실제 디렉토리 데이터 포함)와 마운트 디렉토리의 두 가지 필수 인수가 있습니다. 튜토리얼 tarball에는 및 `example`라는 두 개의 하위 디렉토리가 포함된 디렉토리가 포함되어 있습니다 . 가 비어 있는 동안 이라는 단일 파일 이 포함되어 있는지 확인할 수 있습니다 .`rootdir``mountdir``rootdir``bogus.txt``mountdir`

시도해 보면 다음과 같습니다.

> ```bash
> snowball:655$ pwd
> /home/joseph/fuse-tutorial/example
> snowball:656$ ls -lR
> .:
> total 12
> -rw-r--r-- 1 joseph users  185 Jun  9 15:56 Makefile
> drwxr-xr-x 2 joseph users 4096 Jun 12 17:16 mountdir/
> drwxr-xr-x 2 joseph users 4096 Jun 12 17:16 rootdir/
> 
> ./mountdir:
> total 0
> 
> ./rootdir:
> total 4
> -rw-r--r-- 1 joseph users 11 Jun 12 17:16 bogus.txt
> ```

이제 `example`디렉토리로 이동하여 실행하면

> ```bash
> ../src/bbfs rootdir mountdir
> ```

실제로 있는 모든 파일 `rootdir`은 `mountdir`"

> ```bash
> snowball:657$ ../src/bbfs rootdir/ mountdir/
> about to call fuse_main
> snowball:658$ ls -lR
> .:
> total 40
> -rw-r--r-- 1 joseph users   185 Jun  9 15:56 Makefile
> -rw-r--r-- 1 joseph users 25632 Jun 12 17:51 bbfs.log
> drwxr-xr-x 2 joseph users  4096 Jun 12 17:16 mountdir/
> drwxr-xr-x 2 joseph users  4096 Jun 12 17:16 rootdir/
> 
> ./mountdir:
> total 4
> -rw-r--r-- 1 joseph users 11 Jun 12 17:16 bogus.txt
> 
> ./rootdir:
> total 4
> -rw-r--r-- 1 joseph users 11 Jun 12 17:16 bogus.txt
> ```

그러나 에서 파일 작업을 수행 할 때마다 `mountdir`작업(및 관련 및 관련 없는 항목 전체)이 현재 작업 디렉토리의 새 파일에 기록됩니다.`bbfs.log`

> ```bash
> tail -F bbfslog
> ```

다른 터미널 창에서 작업이 기록되는 것을 볼 수 있습니다.

`mountdir`마지막으로 운영 체제가 파일 시스템으로 인식되는 것을 볼 수 있습니다 .

> ```bash
> snowball:660$ mount | grep mountdir
> bbfs on /home/joseph/fuse-tutorial/example/mountdir type fuse.bbfs (rw,nosuid,nodev,relatime,user_id=1248,group_id=1005)
> ```

## 마운트 해제

마지막으로 다음을 사용하여 파일 시스템을 마운트 해제할 수 있습니다.

> ```bash
> snowball:661$ fusermount -u mountdir
> snowball:662$ ls -lR
> .:
> total 40
> -rw-r--r-- 1 joseph users   185 Jun  9 15:56 Makefile
> -rw-r--r-- 1 joseph users 27520 Jun 12 17:57 bbfs.log
> drwxr-xr-x 2 joseph users  4096 Jun 12 17:16 mountdir/
> drwxr-xr-x 2 joseph users  4096 Jun 12 17:16 rootdir/
> 
> ./mountdir:
> total 0
> 
> ./rootdir:
> total 4
> -rw-r--r-- 1 joseph users 11 Jun 12 17:16 bogus.txt
> ```

(참고 `fusermount`로 이 튜토리얼의 일부가 아닙니다. FUSE와 함께 제공됩니다.)

## `pkg-config`

소프트웨어 구성에 대해 언급할 한 가지는 다음과 같습니다.

```
PKG_CHECK_MODULES(FUSE, fuse)
```

~에`configure.ac`



`pkg-config`이것은 튜토리얼에서 코드를 컴파일하고 링크하는 데 필요한 C 컴파일 플래그와 라이브러리를 얻기 위한 두 번의 호출로 해석됩니다 .

> ```makefile
> `pkg-config fuse --cflags`
> ```

FUSE `pkg-config`를 사용하는 소스 파일을 컴파일하는 데 필요한 C 컴파일러 플래그를 결정하는 데 사용합니다. 명령 주변의 역따옴표는 중요합니다. 명령의 출력을 가져와 명령줄 작업으로 명령줄에 삽입합니다(참고 - 역따옴표라고도 하는 역따옴표는 중요합니다. 앞으로 올 수 없습니다. 따옴표 또는 큰 따옴표). 사용하는 다른 곳,

> ```makefile
> `pkg-config fuse --libs`
> ```

프로그램을 와 연결하기 위한 추가 명령줄 인수를 제공합니다 `libfuse`.

이 튜토리얼의 이전 버전은 다음과 같이 Makefile에서 직접 이러한 호출을 사용했습니다.

> ```makefile
> bbfs : bbfs.o log.o
>         gcc -g -o bbfs bbfs.o log.o `pkg-config fuse --libs`
> 
> bbfs.o : bbfs.c log.h params.h
>         gcc -g -Wall `pkg-config fuse --cflags` -c bbfs.c
> 
> log.o : log.c log.h params.h
>         gcc -g -Wall `pkg-config fuse --cflags` -c log.c
> ```

------

[다음: 콜백 및 `struct fuse_operations`](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/callbacks.html)

------



최종 수정일: 2018년 2월 4일 일요일 09:16:09 MST





---



# 콜백 및`struct fuse_operations`

FUSE 파일 시스템은 소켓에서 수행할 파일 작업을 수신 대기하고 수행하는 프로그램입니다. FUSE 라이브러리( `libfuse`)는 소켓과의 통신을 제공하고 요청을 코드로 전달합니다. "콜백" 메커니즘을 사용하여 이를 수행합니다.

콜백은 파일 작업을 구현하기 위해 작성하는 함수 집합과 이에 `struct fuse_operations` 대한 포인터를 포함합니다.

BBFS의 경우 콜백 구조체의 이름은 `bb_oper`. `bbfs.c`에 포인터로 정의된 총 34개의 파일 작업 이 `bb_oper`있습니다. 초기화는 모든 사람이 익숙하지 않은 구문을 사용합니다. 우리가 보는 초기화의 일부를 `struct`보고

> ```c
> struct fuse_operations bb_oper = {
>   .getattr = bb_getattr,
>   .readlink = bb_readlink,
>   .open = bb_open,
>   .read = bb_read
> };
> ```

(물론 이것은 완전한 구조체가 아닙니다. 무슨 일이 일어나고 있는지 이해하기에 충분합니다)



이것은 를 가리키고 , 를 가리키고 , 를 가리키고 를 `bb_oper.getattr`가리 킵니다 . 이러한 각 함수는 해당 파일 시스템 함수를 다시 구현한 것입니다. 사용자 프로그램이 를 호출 하면 내 함수가 호출됩니다. 일반적으로 내가 다시 구현하는 모든 작업은 호출에 대한 일부 정보를 기록한 다음 기본 파일 시스템에서 작업의 원래 시스템 구현을 호출하는 것입니다. `bb_getattr()``bb_oper.readlink``bb_readlink()``bb_oper.open``bb_open()``bb_oper.read``bb_read()``read()``bb_read()`

`bb_open()`특히 다음 두 가지 `open()`를 살펴 `bb_read()`보겠습니다 `read()`.

여기 `bb_open()`:

> ```c
> int bb_open(const char *path, struct fuse_file_info *fi)
> {
>     int retstat = 0;
>     int fd;
>     char fpath[PATH_MAX];
> 
>     bb_fullpath(fpath, path);
>     
>     log_msg("bb_open(fpath\"%s\", fi=0x%08x)\n",
> 	    fpath,  (int) fi);
>     
>     fd = open(fpath, fi->flags);
>     if (fd < 0)
> 	retstat = bb_error("bb_open open");
>     
>     fi->fh = fd;
>     log_fi(fi);
>     
>     return retstat;
> }
> ```

함수가 호출되면 두 개의 매개변수가 전달됩니다. 하나는 파일 경로(마운트된 파일 시스템의 루트에 상대적)이고, 다른 하나 `struct fuse_file_info`는 파일에 대한 정보를 유지 관리하는 데 사용되는 포인터입니다.

`bb_open()``bb_fullpath()`내 기능 을 사용하여 기본 파일 시스템의 전체 경로에 제공된 상대 경로를 변환하는 것으로 시작합니다 . 그런 다음 전체 경로와 `fi`포인터 주소를 기록합니다. 호출을 기본 파일 시스템으로 전달하고 성공했는지 확인합니다. `open()`그렇다면 (나중에 사용할 수 있도록) 에서 반환한 파일 설명자를 저장 하고 0을 반환합니다. 실패하면 를 반환합니다 `-errno`. 반환 값 정보:

1. 성공하면 0이 반환되어야 합니다. 이것은 라이브러리에서 대부분의 호출에 대한 정상적인 동작입니다. 예외가 문서화되어 있습니다.
2. 음수 반환 값은 실패를 나타냅니다. 값을 `-i`반환하면 호출자에게 -1이 반환되고 `errno`로 설정됩니다 `i`. 내 `bb_error()`함수는 `errno`시스템 `open()`호출에 의해 설정된 대로 조회하고 오류를 기록 `-errno`하고 사용자에게 전달할 수 있도록 이 함수로 돌아갑니다.

FUSE는 일부 번역을 수행합니다. 시스템 호출은 0 이 `open()`아닌 파일 설명자(내가 의존하는 동작)를 반환하는 것으로 문서화되어 있습니다. 따라서 내 반환이 원래 호출자에게 전달될 때 FUSE는 내가 0을 보냈다는 것을 인식하고 적절한 파일 설명자를 반환합니다(반드시 내가 `open()`!)에게 전화를 걸 때 받은 것과 같은 것입니다. 한편 기본 파일이 열려 있고 파일 설명자가 `fi`. 내 코드에 대한 향후 호출에는 이 포인터가 포함될 것이므로 파일 설명자를 가져와 작업할 수 있습니다. 그래서... 사용자 프로그램은 마운트된 파일 시스템에 열린 파일과 추적 중인 파일 설명자가 있습니다. 해당 프로그램이 해당 파일 디스크립터로 무엇이든 하려고 할 때마다 작업은 커널에 의해 가로채어 커널로 전송됩니다.`bbfs`프로그램. 내 프로그램 내에는 기본 디렉토리에 파일이 열려 있고 파일 설명자가 있습니다. 작업이 내 프로그램으로 전송되면 기록하고 내 파일에서 동일한 작업을 수행합니다.

이를 구체화하기 위해 다음을 살펴보겠습니다 `bb_read()`.

> ```c
> int bb_read(const char *path, char *buf, size_t size, off_t offset, struct fuse_file_info *fi)
> {
>     int retstat = 0;
>     
>     log_msg("bb_read(path=\"%s\", buf=0x%08x, size=%d, offset=%lld, fi=0x%08x)\n",
> 	    path,  (int) buf, size,  offset,  (int) fi);
>     
>     retstat = pread(fi->fh, buf, size, offset);
>     if (retstat < 0)
> 	retstat = bb_error("bb_read read");
>     
>     return retstat;
> }
> ```

이 함수를 사용하면 파일 시작 부분에서 지정된 오프셋에서 데이터를 읽을 수 있습니다(따라서 `pread()`보다는 함수 에 더 직접적으로 대응함 `read()`).

이 기능에 대해 지적해야 할 주요 사항은 `fi`파일을 열 때 입력한 파일 설명자를 사용하여 파일을 읽는다는 것입니다. 또한 에서 오류가 아닌 반환을 받으면 `pread()`이 값을 호출자에게 전달합니다. 이 경우 `FUSE` 번역을 수행하지 않고 내가 지정한 값만 반환합니다. 오류를 반환하려면 에서와 동일한 기술을 사용합니다 `bb_open()`.

------

[다음: 명령줄 구문 분석 및 FUSE 초기화](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/init.html)

------



최종 수정 날짜: 2014년 6월 12일 목요일 18:12:31 MDT



---

# 명령줄 구문 분석 및 FUSE 초기화

실제로 아주 적은 명령줄 구문 분석을 수행해야 합니다. FUSE는 많은 수의 명령줄 인수를 이해하고 자체적으로 구문 분석합니다. 가장 중요한 것은 FUSE는 마운트 포인트가 `argv[]`어레이의 구성원 중 하나가 될 것으로 예상한다는 것입니다.

나는 루트 디렉토리와 마운트 포인트가 마운트 포인트 앞에 루트 디렉토리가 있는 명령행의 마지막 두 인수가 될 것이라고 가정함으로써 스스로를 단순화하고 있습니다. 따라서 내 명령줄 구문 분석은 두 개의 짧은 섹션에서 발생합니다. 먼저, 몇 가지 온전한 검사를 수행합니다. 명령줄에 충분한 인수가 있는지 확인하고 마지막 두 인수가 옵션이 아닌지 확인합니다.

> ```c
> if ((argc < 3) || (argv[argc-2][0] == '-') || (argv[argc-1][0] == '-'))
>     bb_usage();
> ```

몇 줄 후에 인수 목록에서 루트 디렉터리를 가져오고 C 라이브러리 `realpath()`함수를 사용하여 정규화된 절대 경로 이름으로 변환한 다음 개인 데이터에 저장합니다(개인 데이터는 다음 섹션에서 설명하겠습니다).

> ```c
> bb_data->rootdir = realpath(argv[argc-2], NULL);
> argv[argc-2] = argv[argc-1];
> argv[argc-1] = NULL;
> argc--;
> ```

FUSE 라이브러리로 제어권을 넘기기 전에 마지막으로 하는 일은 로그 파일을 열고 스트림 포인터를 내 개인 데이터에도 저장하는 것입니다.

파일 시스템을 시작할 준비가 되면 `fuse_main()`. 매개변수는 `argc`및 (내 함수 `argv`에 의해 수정됨 ), POSIX 파일 작업의 재구현에 대한 포인터를 포함하는 구조체 및 개인 데이터를 저장하는 데 사용되는 a입니다. `main()``bb_oper``struct bb_data`

`fuse_main()`나머지 명령줄을 구문 분석하고 명령줄에 지정된 디렉터리를 마운트하고 기타 초기화를 수행합니다. 그런 다음 초기화 함수를 호출하여 내 코드에서 정의한 초기화를 수행합니다. `bb_oper->init` 내 기능을 가리키 `bb_init()`므로 호출됩니다. 내 `bb_init()`기능은 정말 작습니다. 그것이 하는 일은 그것이 호출되었다는 사실을 기록하는 것뿐입니다.

------

[다음: 개인 데이터](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/private.html)

------



최종 수정일: 2014년 6월 12일 목요일 18:13:36 MDT



---

# 개인 데이터

매우 유용한 FUSE 사용에 대한 또 다른 정보는 정의한 데이터를 저장하고 액세스하는 방법입니다.

FUSE에는 `struct fuse_context`파일 시스템에 대한 약간의 추가 정보가 포함되어 있습니다. 여기에서 매우 유용한 필드 중 하나 `struct`는 `void* private_data`파일 시스템에 저장된 임의의 데이터에 대한 포인터입니다.

`fuse_get_context()`FUSE 작업 내부에서 ; 를 호출하여 컨텍스트를 얻을 수 있습니다 . 이것은 우리가 `fuse_get_context()->private_data`개인 데이터를 얻는 데 사용할 수 있음을 의미합니다.

`log_msg()` 내 기능 을 보면 이것을 어떻게 사용하는지 알 수 있습니다 . 여기있어:

> ```c
> void log_msg(const char *format, ...)
> {
>     va_list ap;
>     va_start(ap, format);
> 
>     vfprintf(BB_DATA->logfile, format, ap);
> }
> ```

내가 정의한 매크로를 사용합니다.

> ```c
> #define BB_DATA ((struct bb_state *) fuse_get_context()->private_data)
> ```

개인 데이터 필드를 가져와서 `struct bb_state *`.

내가 정의한 위치 `struct bb_struct`를 보면 내가 어떻게 만들었는지 알 수 있습니다 . `params.h``struct`

> ```c
> struct bb_state {
>     FILE *logfile;
>     char *rootdir;
> };
> ```

`FILE*`여기에는 로그 파일용 필드와 BBFS를 통해 액세스하는 디렉토리 경로의 두 필드가 있습니다.

나는 `malloc()`구조를 하고 내 `main()`함수의 필드 값을 설정합니다(마지막 섹션 참조). 그러면 나중에 사용할 수 있습니다.

------

[다음: 불분명한 함수에 대한 추가 정보](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/unclear.html)

------



최종 수정 날짜: 2014년 6월 12일 목요일 18:14:08 MDT



---



# 불분명한 FUSE 기능에 대한 추가 정보

이 페이지의 목적은 FUSE 문서에서 다소 모호해 보이는 호출에 대한 약간의 추가 정보를 제공하는 것입니다. 추가 설명이 필요한 두 가지 경우가 지금까지 나타났습니다. `readdir()`, 및 FUSE가 `open()`.

## 디렉토리 및`readdir()`

FUSE는 디렉토리 구조에 항목을 배치하는 메커니즘을 제공합니다. 디렉토리 구조 자체는 불투명하므로 기본 메커니즘은 데이터를 생성하고 FUSE 제공 함수를 호출하여 데이터를 구조에 넣는 것입니다.

콜백이 호출 될 때 `readdir()`매개변수 중 하나는 이라는 함수 `filler()`입니다. 이 함수의 목적은 디렉토리 구조에 디렉토리 항목을 삽입하는 것이며, 이는 콜백에도 `buf`.

`filler()`의 프로토타입은 다음과 같습니다.

> ```c
> int fuse_fill_dir_t(void *buf, const char *name,
> 				const struct stat *stbuf, off_t off);
> ```

파일 이름과 선택적으로 파일 유형을 포함 하는 포인터를 호출 하여 항목을 `buf`( 에 전달된 동일한 버퍼에 삽입합니다.`readdir()``filler()``struct stat`

`bb_readdir()``filler()`기본 디렉토리의 파일 이름을 마운트된 디렉토리에 복사하기 위해 가능한 한 간단한 방법으로 사용 합니다. 에 전달된 오프셋 `bb_readdir()`은 무시되고 오프셋 0이 에 전달됩니다 `filler()`. 이것은 `filler()` 자체적으로 디렉토리 구조에 대한 오프셋을 관리하도록 지시합니다. 코드는 다음과 같습니다.

> ```c
> int bb_readdir(const char *path, void *buf, fuse_fill_dir_t filler, off_t offset,
>                struct fuse_file_info *fi)
> {
>     int retstat = 0;
>     DIR *dp;
>     struct dirent *de;
>     
>     log_msg("bb_readdir(path=\"%s\", buf=0x%08x, filler=0x%08x, offset=%lld, fi=0x%08x)\n",
>             path, (int) buf, (int) filler,  offset, (int) fi);
>     
>     dp = (DIR *) (uintptr_t) fi->fh;
> 
>     // Every directory contains at least two entries: . and ..  If my
>     // first call to the system readdir() returns NULL I've got an
>     // error; near as I can tell, that's the only condition under
>     // which I can get an error from readdir()
>     de = readdir(dp);
>     if (de == 0)
>         return -errno;
> 
>     // This will copy the entire directory into the buffer.  The loop exits
>     // when either the system readdir() returns NULL, or filler()
>     // returns something non-zero.  The first case just means I've
>     // read the whole directory; the second means the buffer is full.
>     do {
>         log_msg("calling filler with name %s\n", de->d_name);
>         if (filler(buf, de->d_name, NULL, 0) != 0)
>             return -ENOMEM;
>     } while ((de = readdir(dp)) != NULL);
>     
>     log_fi(fi);
>     
>     return retstat;
> }
> ```

## 파일 생성 플래그

```
open()`시스템 호출은 파일 액세스 모드와 파일 생성 플래그를 모두 사용하는 것으로 문서화되어 있습니다(파일 생성 플래그는 , `O_CREAT`, `O_EXCL`및 `O_NOCTTY`) `O_TRUNC`. 함수에 전달되지 않은 `fuse.h`문서 `O_CREAT`와 기본적으로 전달되지 않는 문서. `O_EXCL``open()``O_TRUNC
```

그 이유는 FUSE가 이러한 플래그를 내부적으로 처리하고 상태와 호출 결과에 따라 호출되는 함수를 수정하기 때문입니다 `getattr()`(your `getattr()`는 항상 파일이 열리기 전에 호출됨). 이러한 플래그가 설정되면 호출은 다음과 같이 처리됩니다.

- `O_CREAT`

  If the file didn't previously exist, your `create()` function is called instead of your `open()` function (if it did exist, then your `open()` is called). After the call to your `create()` function, your `fgetattr()` function is called, though I haven't been able to determine why. One possible use is that you could use this to modify the semantics of creating a file that you yourself don't have access to (note that the standard semantics will only apply the file access mode you specify to *subsequent* `open()`s).If the file did not exist, and the flag is not set, FUSE only calls your `getattr()` function (so neither your `create()` nor your `open()` function is called in this case).

- `O_EXCL`

  The behavior of this flag is only defined when `O_CREAT` is also specified. If the file did not previously exist your `create()` and `fgetatter()`functions are called; if it did, FUSE returns failure after the `getattr()` call (so neither your `open()` nor your `create()` is called in this case).

- `O_NOCTTY`

  So far as I've been able to determine, this flag is simply discarded.

- `O_TRUNC`

  이 플래그의 처리는 파일 시스템이 플래그와 함께 마운트되었는지 여부에 따라 결정됩니다 `-o atomic_o_trunc` . 그렇지 않은 경우 FUSE `truncate()` 는 `open()`. `atomic_o_trunc`옵션이 설정된 경우 플래그가 대신 함수에 전달 됩니다 `open()`(이는 플래그를 명시적으로 처리하는 코드가 없음을 의미합니다. 에 전달되면 에 전달 `bb_open()`합니다 `open()`.

[보안 고려 사항 및 경쟁 조건](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/security.html)

------



최종 수정일: 2011년 1월 1일 토요일 21:45:06 MST



---



# 보안 문제

FUSE 파일 시스템을 작성하고 사용하는 것은 명백할 수도 있고 그렇지 않을 수도 있지만 언급할 가치가 있는 일부 Metrodome 크기의 보안 문제를 가질 수 있습니다. 이 섹션에서는 권한 에스컬레이션, 액세스 권한 확인 및 경쟁 조건 언급에 대한 몇 가지 참고 사항에 대해 설명합니다.

## 권한 상승

요점 ***은 파일 시스템 자체가 파일 시스템을 사용하는 프로세스의 액세스 권한이 아니라 실행 중인 프로세스의 액세스 권한으로 실행된다는 것*** 입니다. 다음은 몇 가지 일반적인 시나리오에서 수행되는 방법입니다.

### `allow_other`일반적인 경우: 사용자가 옵션 없이 파일 시스템을 실행

이것은 정상적인 경우입니다. 파일 시스템은 파일 시스템을 실행한 사용자의 권한으로 실행되며 해당 사용자만 파일 시스템에 액세스할 수 있습니다. 이 경우 FUSE는 특정 보안 문제를 열지 않습니다.

### `allow_other` 사용자가 옵션 을 사용하여 파일 시스템을 실행합니다.

이 경우 파일 시스템은 우연히 파일 시스템을 사용하는 사용자의 권한이 *아니라 파일 시스템을 호출한 사용자의 권한으로 실행됩니다.* 부적절한 액세스 권한이 다른 사용자에게 부여되지 않도록 하는 것은 파일 시스템을 마운트하는 사용자의 책임입니다. 일반적으로 사용자는 자신이 이미 가지고 있는 권한만 부여할 수 있기 때문에 이러한 방식으로만 자신을 해칠 수 있습니다.

이 옵션을 활성화 하려면 옵션 , `user_allow_other`을(를) 설정해야 합니다 .`/etc/fuse.conf`

### 루트가 파일 시스템을 실행합니다.

이것은 (옵션이 설정되었는지 여부에 따라) 앞의 두 경우와 실제로 동일 `allow_other`하지만 root는 언급할 가치가 있는 충분히 특별한 경우입니다. 이 경우 파일 시스템을 사용하는 모든 사용자는 해당 파일 시스템에 대한 루트 권한을 갖습니다! 프로세스가 실제 파일 시스템에 액세스할 수 있는 경우 이를 사용하여 거의 무제한 액세스 권한을 얻을 수 있습니다.

다음 하위 섹션에서는 액세스 권한을 확인하는 방법에 대해 조금 설명하겠지만 여기서 가장 간단한 방법은 루트가 파일 시스템을 마운트하도록 허용하지 않는 것입니다. 단순한 튜토리얼로서의 BBFS의 의도된 역할 때문에 그것이 내가 하는 일입니다. 시작 부분에 약간의 코드가 있습니다 `main()`.``

```c
if ((getuid() == 0) || (geteuid() == 0)) {
    fprintf(stderr, "Running BBFS as root opens unnacceptable security holes\n");
    return 1;
}
```

``



물론 이것은 내가 에 대한 코드를 가지고 있는 동안 `bb_chown()`루트만 기본 파일 시스템에서 파일의 소유권을 변경할 수 있기 때문에 실제로 작동하지 않는다는 것을 의미합니다.

## 접근 권한 확인

일반적으로 플래그 로 실행될 수 있는 파일 시스템은 `allow_other`자체 보안을 보장하기 위한 조치를 취해야 합니다. `fuse.h`요청된 액세스가 허용되는지 확인하기 위해 여러 번의 호출이 필요한 문서 그 외에도 액세스 확인이 필요한 몇 가지 다른 항목이 있습니다(예: `chown()`. 이러한 주의 사항을 준수하는지 확인하는 것은 전적으로 프로그래머의 책임입니다!

액세스 권한을 확인하는 가장 중요한 기능은 `fuse_get_context()`(이름에서 알 수 있듯이) `struct fuse_context`개체에 대한 포인터를 반환하는 것입니다. `uid`작업을 수행하는 프로세스의 UID 및 GID는 및 라는 필드에 있습니다 `gid`.

## 동시 액세스 및 경쟁 조건

기본적으로 FUSE는 다중 스레드를 실행합니다. 이는 (간단히) 이전 요청이 완료되기 전에 파일 시스템에서 두 번째 요청을 처리할 수 있음을 의미합니다. 이것은 차례로 다른 스레드가 단일 데이터 구조를 동시에 수정할 수 있는 가능성을 높여 디버그하기 매우 어려운 버그를 일으킬 수 있습니다.

문제에 대해 수행할 수 있는 몇 가지 작업이 있습니다.

- 파일 시스템이 `-s`옵션으로 실행되면 단일 스레드로 실행됩니다. 이것은 성능을 희생시키면서 문제를 제거합니다. 솔직히 많은 퓨즈 파일 시스템의 특성과 의도를 감안할 때 기본값은 단일 스레드여야 하고 다중 스레드에는 옵션이 필요해야 합니다. 하지만 제가 쓴 것이 아니므로 제 부름이 아닙니다.
- 중요한 섹션에 대한 코드를 분석하고 일반적인 동기화 기본 요소(예: 세마포어)를 삽입하여 위험한 경합이 발생하지 않도록 할 수 있습니다. 물론 FUSE가 단일 호출을 함수에 대한 호출 시퀀스로 변환하는 여러 위치가 있습니다. FUSE가 이러한 호출의 원자성을 보장하기 위해 어떤 조치를 취하는지 조사하지 않았습니다. 그렇지 않은 경우(그리고 나는 그것이 사실이라고 생각합니다. 파일 시스템을 통해 노출하는 데이터에 대한 지식이 없는 상태에서 의미 있는 방식으로 시도하는 것은 나에게 어려운 것과 불가능한 것 사이 어딘가에 있는 것 같습니다), 자신이 정말, 정말 힘든 것 같습니다.

파일 시스템을 단일 스레드로 만들더라도 다른 수단을 통해 기본 데이터 구조에 액세스하는 것을 방지할 수 없습니다. BBFS를 예로 들면:

- bbfs를 두 번 호출하여 두 개의 서로 다른 마운트 지점을 통해 단일 기본 디렉토리를 마운트할 수 있습니다.
- 그 위에 마운트된 BBFS 파일 시스템이 있는 디렉토리는 여전히 정상적인 파일 시스템 작업에 액세스할 수 있습니다.

이러한 사실 중 하나는 원자성을 보호하기 위해 파일 시스템에서 이루어진 모든 노력을 완전히 무효화하기에 충분합니다.

FUSE 코드 자체는 자체 코드와 데이터 구조를 잠그는 데 주의해야 합니다. 내가 아는 한 위험한 경쟁 조건은 코드 외부에서 발생하지 않습니다.

[다음: 감사 및 기타 리소스](https://www.cs.nmsu.edu/~pfeiffer/fuse-tutorial/html/thanks.html)

------



최종 수정일: 2011년 1월 1일 토요일 21:53:06 MST



---

# 감사해요

이 튜토리얼의 날카로운 눈을 가진 몇몇 독자들은 버그를 발견했고 어떤 경우에는 패치를 제공했습니다. 감사해요!

| 니라즈 라왓                        | 이 코드는 FreeBSD로 이식할 수 없었습니다. Neeraj 덕분에 GNU autotools로 전환하고 FreeBSD와의 호환성을 위해 몇 가지를 변경했습니다. |
| ---------------------------------- | ------------------------------------------------------------ |
| 데이비드 테렛:                     | `bb_readlink()`0 대신 읽은 바이트 수를 반환했습니다.         |
| 패트릭 울람:                       | bb_release()가 파일을 닫지 않았습니다.                       |
| 패트릭 울람과 베르나르도 F 코스타: | `bb_readlink()` 문자열을 null로 종료하지 않았습니다. Patrick과 Bernardo도 패치를 제공했습니다! 현재 `bb_readlink()`코드는 Bernardo's |
| 베르나르도 F 코스타:               | 명령줄 인수 구문 분석이 `-o`올바르게 처리되지 않았습니다.    |
| 베르나르도 F 코스타:               | 보안에 미치는 영향에 대해 생각하게 했습니다.                 |
| 디오고 멜로와 시반 탈:             | Makefile 인수 순서가 잘못되어 일부(전부는 아니지만!) 사용자에게 링크 오류가 발생했습니다. |
| 스키피 본드레이크                  | 명령줄 구문 분석에 사용하는 코드가 혼란스럽다고 지적하여 다시 작성했습니다. |
| 이반 미들턴                        | 내 xattr 코드가 컴파일되지 않고 버그가 있음을 발견했습니다.  |

# 기타 리소스

[FUSE 파일 시스템 홈페이지](http://fuse.sourceforge.net/)

[Singh, Sumit, IBM Developerworks에서 FUSE를 사용하여 고유한 파일 시스템 개발](http://www.ibm.com/developerworks/linux/library/l-fuse/)

[OpenSolaris의 FUSE 문서](http://hub.opensolaris.org/bin/view/Project+fuse/Documentation)

------



최종 수정일: 2017년 10월 3일 화요일 14:57:52 MDT

