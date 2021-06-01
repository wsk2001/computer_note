# inotify를 이용한 리눅스 파일 시스템 감시

출처: http://imakeworld.egloos.com/4710553

notify를 이용한 리눅스 파일 시스템 감시

dnotify를 대체할 2.6 커널의 파일 시스템 이벤트 모니터링 메커니즘

Eli M. Dow│IBM

Inotify는 차기 리눅스 커널에 포함될 파일 시스템 모니터링 메커니즘으로서, 구 커널에서 지원되던 파일 모니터링 메커니즘이였던 dnotify의 강력한 대체재이다. Inotify는 강력하고 세련된 비동기식 메커니즘으로서 보안과 퍼포먼스 등 다양한 파일 모니터링의 필요를 이상적으로 채운다. Inotify를 설치하는 방법과 파일 시스템 이벤트에 응답하는 사용자 공간의 애플리케이션 샘플을 구현하는 방법을 설명한다.

파일 시스템 이벤트 모니터링은 파일 매니저에서 보안 툴에 이르기까지 다양한 유형의 프로그램들에 필수적인 것이지만, 이전 커널의 표준인 dnotify는 아쉽게도 제한이 많았다. 이제, 보다 현대적인 파일 시스템 이벤트-모니터링 대안인 Inotify를 만나보자.

### 1. 왜 Inotify인가?

dnotify 대신 Inotify를 사용하는데는 많은 이유가 있다. 우선, dnotify는 변경사항을 보고자 할 때 각 디렉토리에 대한 한 개의 파일 디스크립터를 열어야 한다. 따라서, 여러 디렉토리들을 한번에 모니터링 할 때는 작업량이 많을 수 밖에 없다. 프로세스 당 파일 디스크립터 제한에 걸리기 때문이다.

게다가 파일 디스크립터는 디렉토리를 고정하기 때문에 지원 장치의 언마운트가 허용되지 않는다. 따라서 제거 가능한 미디어가 개입된 시나리오에서 문제가 발생한다. Inotify를 사용할 때, 언마운트 된 파일시스템 상의 파일을 보고자 한다면, 보기(watch)는 자동으로 제거되고 언마운트 이벤트를 받는다.

dnotify 보다 Inotify가 더 나은 두 번째 이유는 약간 복잡하다. dnotify 인프라를 사용하는 단순한 파일시스템-모니터링 세분성은 디렉토리 레벨에서만 존재한다. dnotify를 이용하여 보다 정밀한 모니터링을 하려면 애플리케이션 프로그래머들은 관찰되고 있는 각 디렉토리와 관련된 `stat` 구조의 캐시를 유지해야한다. `stat` 구조의 사용자 공간 캐시는 공지 신호를 받았을 때 디렉토리에 정확히 어떤 변화가 발생했는지를 결정하는데 필요하다. 공지를 받으면 `stat` 구조의 리스트가 생성되고 마지막 상태와 비교된다. 확실히 이는 최상의 방법은 아니다.

Inotify의 또 다른 장점은 기본 인터페이스로서 파일 디스크립터를 사용하여 애플리케이션 개발자들이 `select`와 `poll` 을 사용하여 장치 볼 수 있다는 점이다. 이로서 효율적인 다중화 I/O와 Glib의 `mainloop`와의 통합이 가능하다. 이와 반대로, dnotify는 프로그래머들이 어려워하는 신호를 사용하고 있다.

Inotify는 최소한의 파일 디스크립터를 사용하고 보다 세분화된 모니터링을 보장하는 등의 고급 기능을 제공하여 이러한 문제들을 해결한다. Inotify와의 통신은 디바이스 노트를 통해 이루어진다. 따라서, Linux 2.6에서 파일 모니터링을 할 때 선택을 분명히 해야 한다.

### 2. Inotify 설치하기

Inotify를 설치하는 첫 번째 단계는 현재 사용하고 있는 리눅스 커널이 이를 지원하는지의 여부를 결정하는 것이다. 배포판을 확인하는 가장 간단한 방법은 /dev/inotify 장치의 존재 유무를 확인하는 것이다. 이 장치가 있으면 [애플리케이션에서 Inotify 사용하기](http://www.dbguide.net/comb/common/edit/editor_cho.htm#simpapp)섹션으로 이동하기 바란다.

이 글이 쓰여질 당시, Inotify는 Andrew Morton의 리눅스 Linux 2.6-mm 트리에 포함되었고 여러 리눅스 배포판들이 Inotify를 실행하는 커널(Gentoo와 Ubuntu)을 제공하고 있거나 또는 이를 지원하는 보조 커널 패키지를 갖고 있었다. (Fedora와 SuSE). Andrew가 트리에서 Inotify 지원을 제거할 수도 있고 Inotify를 제거하는 일은 개발 단계에서는 종종 있는 일이였기 때문에 처음부터 다시 붙이는 것을 권장한다.

이 장치가 없다면 커널을 패치하고 장치를 만들어야 한다.



### 3. Inotify용 커널 패치

Linux Kernel Archives ([참고자료](http://www.dbguide.net/comb/common/edit/editor_cho.htm#resources))에서 Inotify 패치를 얻을 수 있다.

자신의 커널에 해당하는 버전 숫자 중 가장 높은 숫자를 가진 패치를 적용해야 한다. 배포판마다 커널 설치를 약간 다르게 핸들하긴 하지만 여기에서는 일반적인 가이드라인을 설명하겠다: *Note*: 배포판의 2.6 리눅스 커널 소스나, Linux Kernel Archives에서 안정적인 최신 릴리스를 사용해야 한다.

우선 커널 소스 디렉토리로 간다:

```
bash:~$ cd /usr/src
```

앞서 커널 소스를 설치했기 때문에 이를 패킹을 푼다(unpack):

```
bash:~$ sudo tar jxvf linux-source-2.6.8.1.tar.bz2
```

symlink를 새로운 소스 트리로 만든다:

```
bash:~$ sudo ln -sf linux-source-2.6.8.1 linux
```

현재 디렉토리를 방금 만든 커널 소스 디렉토리로 변경한다:

```
bash:~$ cd linux
```

Inotify 패치를 복사한다:

```
bash:~$ sudo cp ~/inotify* /usr/src
```

커널을 붙인다(patch):

```
bash:~$ sudo patch -p1 < ../inotify*.patch
```

커널을 구현한다:

```
bash:~$ sudo make menuconfig
```

inotify 기능을 확인하면서 일반적인 방식으로 커널을 설정한다. 필요하다면 이 새로운 커널을 bootloader에 추가한다. 단 구 커널 이미지와 bootloader 옵션을 기억해야 한다. 이 단계는 bootloader 마다 다양하다. (bootloader 관련 추가정보는 [참고자료](http://www.dbguide.net/comb/common/edit/editor_cho.htm#resources) 참조). 머신을 재부팅하고 inotify 기능이 추가된 커널을 선택한다. 새로운 커널을 테스트하여 올바르게 작동하는지 확인한다.



### 4. inotify 장치 만들기

다음에는, /dev/inotify 장치가 만들어졌는지를 확인해야 한다. 다음은 그 단계이다. *Important note:* 마이너 넘버(minor number)가 변경될 수 있기 때문에 최신 것인지를 부지런히 검토해야 한다. 자신의 리눅스가 udev 기능을 지원한다면 자동으로 업데이트 된다.

새로운 커널로 재부팅 한 후에, 마이너 넘버를 획득해야 한다:

```
bash:~$ dmesg | grep ^inotify
```

다음은 리턴된 예제이다:

```
inotify device minor=63
```

inotify는 `misc` 장치이기 때문에 메이저는 10이다. 다음 명령행을 실행하여 장치 노드를 root 사용자로 만든다:

```
bash:~$ mknod /dev/inotify c 10 63
```

*Note:* 필요할 경우 "63"을 적당한 마이너 넘버로 대체한다.

선택적으로 허가(permission)를 설정해야 한다. 허가 설정 샘플은 다음과 같다:

```
bash:~$ chown root:root /dev/inotify
bash:~$ chmod 666 /dev/inotify
```

이제 파일 시스템 모니터링을 위한 inotify 장치를 사용할 준비를 갖추게 되었다.



### 5. 애플리케이션에서 Inotify 사용하기

파일 시스템 이벤트에 임의의 디렉토리를 감시하는 샘플 프로그램일 만드는 방법을 설명하겠다. inotify가 파일 시스템 모니터링을 얼마나 쉽게 만드는지를 보여줄 예정이다.

주 메소드

이 간단한 예제를 통해 임의의 디렉토리에 대한 watch를 설정하는 것이 얼마나 쉬운지를 보게 될 것이다. 주요 헬퍼 루틴은 나중에 설명하도록 하겠다. 여기에 사용되는 샘플 코드는   . **Listing 1. 디렉토리에 대한 watch 설정하기**

```c++
/* This program will take as argument a directory name and monitor it,
   printing event notifications to the console.
*/
int main (int argc, char **argv)
{
   /* This is the file descriptor for the inotify device */
   int inotify_fd;

   /* First we open the inotify dev entry */
   inotify_fd = open_inotify_dev();
   if (inotify_fd < 0)
   {
      return 0;
   }


   /* We will need a place to enqueue inotify events,
      this is needed because if you do not read events
      fast enough, you will miss them.
   */
   queue_t q;
   q = queue_create (128);


   /* Watch the directory passed in as argument
      Read on for why you might want to alter this for
      more efficient inotify use in your app.
   */
   watch_dir (inotify_fd, argv[1], ALL_MASK);
   process_inotify_events (q, inotify_fd);


   /* Finish up by destroying the queue, closing the fd,
      and returning a proper code
   */
   queue_destroy (q);
   close_inotify_dev (inotify_fd);
   return 0;
}
```



중요한 헬퍼 메소드

다음은 모든 inotify 기반 애플리케이션에 공통적으로 적용되는 가장 중요한 헬퍼 루틴들이다:

- 읽기를 위한 inotify 장치 열기
- 이 장치에서 읽혀지는 이벤트 큐잉(queuing)
- 애플리케이션들이 이벤트 공지를 사용하여 유용하게 일을 수행하도록 하는 이벤트 핸들러

여러 전략들이 사용될 수 있기 때문에 이벤트 큐잉에 대한 자세한 설명은 하지 않겠다. 멀티 쓰레디드 방식이 진화될수록 어디에서나 구현될 수 있다. 그와 같은 구현은 읽기 쓰레드가 inotify 장치에 대해 select()를 수행하고 그런 다음 이벤트를 몇몇 쓰레드가 공유된 스토리지(또는 Glib의 비동기식 메시지 큐)에 복사한다. 이곳은 핸들러 쓰레드가 실행되는 장소이다. **Listing 2. inotify 장치 열기**

```c++
/* This simply opens the inotify node in dev (read only) */
int open_inotify_dev ()
{
   int fd;

   fd = open("/dev/inotify", O_RDONLY);

   if (fd < 0)
   {
      perror ("open(\"/dev/inotify\", O_RDONLY) = ");
   }

   return fd;
}
```





이것은 리눅스 시스템 상에서 파일들을 이용한 프로그래밍을 했다면 매우 익숙할 것이다.**Listing 3. 실제 이벤트-핸들링 루틴**

```
/* This method does the dirty work of determining what happened,
   then allows us to act appropriately
*/
void handle_event (struct inotify_event *event)
{
   /* If the event was associated with a filename, we will store it here */
   char * cur_event_filename = NULL;


   /* This is the watch descriptor the event occurred on */
   int cur_event_wd = event->wd;

   if (event->len)
   {
      cur_event_filename = event->filename;
   }
   printf("FILENAME=%s\n", cur_event_filename);
        printf("\n");


   /* Perform event dependent handler routines */
   /* The mask is the magic that tells us what file operation occurred */
   switch (event->mask)
   {
      /* File was accessed */
      case IN_ACCESS:
         printf("ACCESS EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was modified */
      case IN_MODIFY:
         printf("MODIFY EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File changed attributes */
      case IN_ATTRIB:
         printf("ATTRIB EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was closed */
      case IN_CLOSE:
         printf("CLOSE EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was opened */
      case IN_OPEN:
         printf("OPEN EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was moved from X */
      case IN_MOVED_FROM:
         printf("MOVE_FROM EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was moved to X */
      case IN_MOVED_TO:
         printf("MOVE_TO EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* Subdir was deleted */
      case IN_DELETE_SUBDIR:
         printf("DELETE_SUBDIR EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was deleted */
      case IN_DELETE_FILE:
         printf("DELETE_FILE EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* Subdir was created */
      case IN_CREATE_SUBDIR:
         printf("CREATE_SUBDIR EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* File was created */
      case IN_CREATE_FILE:
         printf("CREATE_FILE EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* Watched entry was deleted */
      case IN_DELETE_SELF:
         printf("DELETE_SELF EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* Backing FS was unmounted */
      case IN_UNMOUNT:
         printf("UNMOUNT EVENT OCCURRED: File \"%s\" on WD #%i\n",
                 cur_event_filename, cur_event_wd);
      break;


      /* Too many FS events were received without reading them
         some event notifications were potentially lost.  */
      case IN_Q_OVERFLOW:
         printf("Warning: AN OVERFLOW EVENT OCCURRED: \n");
      break;


      case IN_IGNORED:
         printf("IGNORED EVENT OCCURRED: \n");
      break;


      /* Some unknown message received */
      default:
         printf ("UNKNOWN EVENT OCCURRED for file \"%s\" on WD #%i\n",
              cur_event_filename, cur_event_wd);
      break;
   }
}
```

각  

퍼포먼스 모니터링과 관련하여 어떤 파일들이 가장 자주 읽히는지, 그리고 파일이 열려있던 기간을 파악할 수 있다. 이러한 종류의 모니터링은 쉽다. 어떤 상황에서, 짧은 기간 동안 애플리케이션에 의해 파일이 반복적으로 읽힌다면, 디스크로 돌아가기 보다는 메모리의 파일을 캐시하여 퍼포먼스를 향상시키기 때문이다.

특정 액션을 수행하는 이벤트 스팩의 핸들러들에 대한 다른 예제들도 이해하기 쉽다. 예를 들어, 기반 파일 시스템에 대해 메타데이터 스토리지 인덱스를 구현한다면 파일 생성 이벤트를 찾아 그 파일에 대한 데이터 마이닝 작동을 실행할 수 있다. 보안을 위해서는, 어떤 누구도 작성해서는 안되는 디렉토리에 파일이 쓰여졌다면 시스템 경고를 실행할 수 있다.

inotify가 많은 정밀한 이벤트들 `CLOSE` versus `CLOSE_WRITE`을 지원한다는 것도 기억하라.

이 글에서 사용된 코드의 많은 이벤트들이 코드가 실행될 때마다 보고자 하는 것이 아닐 수도 있다. 사실, 가능하다면 본인의 애플리케이션에 필요한 이벤트의 하위세트만 요청할 수 있으면 좋겠다. 이 글에서 제시한 코드에서는 전체 마스크를 사용하여 많은 이벤트들을 보여주고자 하였다. (샘플 코드에 있는 51줄의 `main` 메소드 ([참고자료](http://www.dbguide.net/comb/common/edit/editor_cho.htm#resources)) 또는 [Listing 1](http://www.dbguide.net/comb/common/edit/editor_cho.htm#code1)의 29줄) 애플리케이션 프로그래머들은 일반적으로 많은 선택권이 주워지길 바란다. 또한 여러분도 필요에 맞는 특정 마스크가 필요하다. 이는 앞서 보여준 `handle_event()` 메소드에서 catch 문장에서 관심 없는 아이템들을 제거할 수 있다.

결론

inotify가 퍼포먼스 모니터링, 디버깅, 자동화 같은 분야에 적용된다면 강력하고 정밀한 리눅스 파일 시스템의 모니터링 메커니즘이 된다. 이 글에 제시된 코드를 사용하여 최소한의 퍼포먼스 오버헤드로 실시간 파일 시스템 이벤트에 응답하고 이를 기록할 수 있는 애플리케이션을 작성할 수 있다.

**참고자료**



**[출처]** [[리눅스\] inotify 를 이용한 폴더/파일 변화 감시 소스 코드](http://blog.naver.com/websearch/221075783079)|**작성자** [까미유](http://blog.naver.com/websearch)

리눅스에서 inotify 를 이용하면 폴더의 변화 및 파일 추가/수정/삭제/이동 등의 이벤트를 감시할 수 있습니다.  아래의 소스 코드는 inotify 를 이용한 /tmp 폴더의 폴더/파일 변화를 감시하는 소스 코드입니다.

[주의] inotify 는 하위 폴더의 파일에 대한 감시가 되지 않는다. 

```c++
#include <stdio.h>
#include <unistd.h>
#include <sys/inotify.h>

int main( int argc, char * argv[] )
{
 int iFd = inotify_init();
 if( iFd == -1 )
 {
  printf( "inotify_init error\n" );
  return 0;
 }
 
 // 파일 변경을 감시할 디렉토리를 설정한다.
 int n = inotify_add_watch( iFd, "/tmp", IN_ACCESS | IN_ATTRIB |
      IN_CLOSE_WRITE | IN_CLOSE_NOWRITE | IN_CREATE |
      IN_DELETE | IN_DELETE_SELF |
      IN_MODIFY | IN_MOVE_SELF |
      IN_MOVED_FROM | IN_MOVED_TO | IN_OPEN );
 if( n == -1 )
 {
  printf( "inotify_add_watch error\n" );
 }
 else
 {
  char szBuf[8192];
  
  while( 1 )
  {
   n = read( iFd, szBuf, sizeof(szBuf) );
   if( n < 0 )
   {
    printf( "read error\n" );
    break;
   }
   
   int iPos = 0;
   
   while( iPos < n )
   {
    struct inotify_event * psttEvent = (struct inotify_event *)&szBuf[iPos];
    
    // .a.txt.swp 와 같이 . 으로 시작하는 파일 이름은 무시한다.
    if( psttEvent->name[0] != '.' )
    {
     printf( "name[%s] mask[%x] cookie[%x]", psttEvent->name, psttEvent->mask, psttEvent->cookie );
     
     if( psttEvent->mask & IN_ACCESS ) printf( " IN_ACCESS" );
     if( psttEvent->mask & IN_ATTRIB ) printf( " IN_ATTRIB" );
     if( psttEvent->mask & IN_CLOSE_WRITE ) printf( " IN_CLOSE_WRITE" );
     if( psttEvent->mask & IN_CLOSE_NOWRITE ) printf( " IN_CLOSE_NOWRITE" );
     if( psttEvent->mask & IN_CREATE ) printf( " IN_CREATE" );
     if( psttEvent->mask & IN_DELETE ) printf( " IN_DELETE" );
     if( psttEvent->mask & IN_DELETE_SELF ) printf( " IN_DELETE_SELF" );
     if( psttEvent->mask & IN_MODIFY ) printf( " IN_MODIFY" );
     if( psttEvent->mask & IN_MOVE_SELF ) printf( " IN_MOVE_SELF" );
     if( psttEvent->mask & IN_MOVED_FROM ) printf( " IN_MOVED_FROM" );
     if( psttEvent->mask & IN_MOVED_TO ) printf( " IN_MOVED_TO" );
     if( psttEvent->mask & IN_OPEN ) printf( " IN_OPEN" );
     
     printf( "\n" );
    }
    
    iPos += sizeof(inotify_event) + psttEvent->len;
   }
  }
 }
 
 close( iFd );

 return 0;
} 

```



##  [Inotify] 파일 이벤트 감시

출처: https://sonseungha.tistory.com/436

리눅스에서는 커널 2.6.13 부터 파일시스템 이벤트를 모니터링할 수 있는 메커니즘을 제공합니다. inotify와 dnotify인데요, 이 글에서는 inotify만 다룰 계획입니다. inotify가 dnotify를 대체하기 위한 녀석이기 때문이죠.



Inotify는 파일이나 디렉토리를 개별적으로 모니터링 할 수 있도록 해줍니다. <span style="color:blue"> 여기서 한가지 유의할 점은 디렉토리 모니터링 시 재귀적으로 모니터링 되진 않는다는 것입니다.</span> 만약 하위 모든 디렉토리를 모니터링 하기 위해선 각 디렉토리에 대해 모니터링을 하도록 해야합니다. 



한가지 Inotify의 사용 예시를 생각해보죠. 여러분이 Daemon과 Daemon의 설정 파일을 제공한다고 가정했을 때, 누군가 설정파일을 변경 시 Daemon에서 이걸 알고 처리할 수 있도록 하는 기능 추가 시 유용할 수 있습니다.



그럼, inotify API와 사용법을 알아본 후 예제 코드를 살펴보시죠.



-  **Inotify API**

**[SYNOPSIS]**

```c++
#include <sys/inotify.h>

int inotify_init(void);

```

**return**

초기화된 inotify instance를 가리키는 file descriptor



inotify instance를 초기화 하기 위한 함수입니다. 성공하면 파일 디스크립터를 리턴하고, 에러 발생 시 -1을 리턴하고, errno가 설정됩니다. 여기서 파일 디스크립터는 inotify instance를 참조할 때 사용되어집니다.





**[SYNOPSIS]**

```c++
#include <sys/inotify.h>

int inotify_add_watch(int fd, const char *pathname, uint32_t mask);

```

**Parameter**

fd : inotify_init() 에서 리턴받은 file descriptor

pathname : 모니터링 리스트에 생성되거나 수정되는 파일

mask : pathname에 모니터링할 이벤트 지정 마스크



**return**

추가 또는 수정된 inotify instance를 가리키는 file descriptor



file descriptor가 가리키는 inotify instance의 모니터링 리스트에 새로운 파일 또는 디렉토리를 추가하거나 기존에 추가되어 있던 항목을 수정하기 위해 사용됩니다. 기존에 추가 되어있다면 mask만 수정됩니다. mask의 종류는 매우 다양하며 몇 가지만 뒤에서 알아보겠습니다.





**[SYNOPSIS]**

```c++
#include <sys/inotify.h>

int inotify_rm_watch(int fd, int wd);
```

**Parameter**

fd : inotify_init() 에서 리턴받은 file descriptor

wd : inotify_add_watch()의 이전 호출이 리턴한 file descriptor.



**return**

수정된 inotify instance를 가리키는 file descriptor



File descriptor fd가 가리키는 inotify instance로부터 wd로 지정한 모니터링 리스트를 제거합니다.



-  **Inotify Event Mask**

위에서 inotify_add_watch() API를 설명할 때 parameter로 이벤트 관련된 mask를 설명드렸었습니다. 이 parameter는 pathname에 대해 모니터링할 이벤트를 전달할 수 있는데요. 그 중 몇 가지를 알아보도록 하겠습니다.



아래 표 중 입력은 실제 mask로 지정할 수 있는 이벤트이며, 출력은 지정하지 않아도 모니터링 가능한 이벤트입니다.



| Mask             | 입력 | 출력 | 설명                                           |
| ---------------- | ---- | ---- | ---------------------------------------------- |
| IN_ACCESS        | √    | √    | 파일 접근 가능 ( read() )                      |
| IN_ATTRIB        | √    | √    | 파일 메타데이터 변경됨                         |
| IN_CLOSE_WRITE   | √    | √    | Write 위해 열린 파일이 닫힘                    |
| IN_CLOSE_NOWRITE | √    | √    | ReadOnly로 열린 파일이 닫힘                    |
| IN_CLOSE         | √    |      | IN_CLOSE_WRITE \| IN_CLOSE_NOWRITE             |
| IN_CREATE        | √    | √    | 모니터링 디렉토리 내의 파일 or 디렉토리 생성됨 |
| IN_DELETE        | √    | √    | 모니터링 디렉토리 내의 파일 or 디렉토리 삭제됨 |
| IN_DELETE_SELF   | √    | √    | 모니터링 중인 디렉토리 or 파일이 삭제됨        |
| IN_MODIFY        | √    | √    | 파일이 수점됨                                  |
| IN_MOVE_SELF     | √    | √    | 모니터링 중인 디렉토리 or 파일이 이동됨        |
| IN_MOVE_FROM     | √    | √    | 파일이 모니터링중인 디렉토리 밖으로 이동됨     |
| IN_MOVE_TO       | √    | √    | 파일이 모니터링중인 디렉토리로 이동됨          |
| IN_OPEN          | √    | √    | 파일이 열림                                    |
| IN_ALL_EVENTS    | √    |      | 위의 모든 이벤트를 포함함.                     |
| IN_DONT_FOLLOW   | √    |      | Symbolic Link를 역참조하지 않음                |



위의 mask외에도 여러 mask가 있습니다. 상세한 내용은 man-page를 참고하시면 좋을 것 같습니다.



-  **Inotify_event**

그럼 이제 모니터링한 파일 or 디렉토리에서 발생한 이벤트를 어떻게 읽어올 것인지 살펴보겠습니다. inotify_event를 이용해 이벤트를 읽어옵니다. 보통 read()를 사용하나, Blocking 되는 문제가 있어, select나 poll 같은 Multiflexing I/O들을 많이 사용합니다. 여기서는 read()를 이용한 설명을 드리겠습니다. 아래는 inotify_event 구조체를 나타낸 것입니다. 

```c++
struct inotify_event {
    int      wd;       /* Watch descriptor */
    uint32_t mask;     /* Mask describing event */
    uint32_t cookie;   /* Unique cookie associating related
                                     events (for rename(2)) */
    uint32_t len;      /* Size of name field */
    char     name[];   /* Optional null-terminated name */
};

```



```c++
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <poll.h>
#include <errno.h>
#include <unistd.h>
#include <sys/inotify.h>
 
#define INOTIFY_PATH "/tmp/"
 
static void __handle_inotify_event(const struct inotify_event *event)
{
    if (event->mask & IN_ACCESS)
        printf("IN_ACCESS ");
    if (event->mask & IN_ATTRIB)
        printf("IN_ATTRIB ");
    if (event->mask & IN_CLOSE_NOWRITE)
        printf("IN_CLOSE_NOWRITE ");
    
    // 파일 생성 및 수정시
    if (event->mask & IN_CLOSE_WRITE)
        printf("IN_CLOSE_WRITE ");
    
    if (event->mask & IN_CREATE)
        printf("IN_CREATE ");

    if (event->mask & IN_MODIFY)
        printf("IN_MODIFY ");
    
    if (event->mask & IN_DELETE)
        printf("IN_DELETE ");
    
    if (event->mask & IN_ISDIR)
        printf("IN_ISDIR ");
    
    if (event->mask & IN_MOVE_SELF)
        printf("IN_MOVE_SELF ");
    if (event->mask & IN_MOVED_FROM)
        printf("IN_MOVED_FROM ");
    if (event->mask & IN_MOVED_TO)
        printf("IN_MOVED_TO ");
    if (event->mask & IN_OPEN)
        printf("IN_OPEN ");
    if (event->len > 0)
        printf(": name = %s\n", event->name);
}
 
int main(int argc, char *argv[])
{
    int ret;
    int fd;
    int wd;
    char buf[4096] __attribute__ ((aligned(__alignof__(struct inotify_event))));
    char *ptr;
    ssize_t size;
    const struct inotify_event *event;
 
    fd = inotify_init();
    if (fd < 0) {
        perror("inotify_init");
        exit(EXIT_FAILURE);
    }
 
    wd = inotify_add_watch(fd, INOTIFY_PATH, IN_OPEN | IN_CLOSE | IN_MODIFY | IN_CREATE | IN_DELETE);
    if (wd < 0) {
        fprintf(stderr, "Failed to add watch [%s] [%s]", INOTIFY_PATH, strerror(errno));
        perror("inotify_add_watch");
        exit(EXIT_FAILURE);
    }
 
    while (1) {
        size = read(fd, buf, sizeof(buf));
        if (size == -1 && errno != EAGAIN) {
            perror("read");
            fprintf(stderr, "read : %s", strerror(errno));
            exit(EXIT_FAILURE);
        }
 
        if (size <= 0)
            break;
 
        for (ptr = buf; ptr < buf + size; ptr += sizeof(struct inotify_event) + event->len) {
            event = (struct inotify_event *)ptr;
            __handle_inotify_event(event);
        }
    }
 
    ret = inotify_rm_watch(fd, wd);
    if (ret < 0) {
        fprintf(stderr, "Failed to rm watch [fd : %d] [wd : %d] [%s]", fd, wd, strerror(errno));
        perror("inotify_rm_watch");
        exit(EXIT_FAILURE);
    }
 
    return 0;
}

```



---

Solaris Watch Dir

https://solarisrants.wordpress.com/2013/07/24/solaris-file-event-notification/

