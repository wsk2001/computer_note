# Linux 파일 이벤트 감시 - Inotify

Linux / System Programming

출처: https://sonseungha.tistory.com/436



리눅스에서는 커널 2.6.13 부터 파일시스템 이벤트를 모니터링할 수 있는 메커니즘을 제공합니다. inotify와 dnotify인데요, 이 글에서는 inotify만 다룰 계획입니다. inotify가 dnotify를 대체하기 위한 녀석이기 때문이죠.

 

Inotify는 파일이나 디렉토리를 개별적으로 모니터링 할 수 있도록 해줍니다. 여기서 한가지 유의할 점은 디렉토리 모니터링 시 재귀적으로 모니터링 되진 않는다는 것입니다. 만약 하위 모든 디렉토리를 모니터링 하기 위해선 각 디렉토리에 대해 모니터링을 하도록 해야합니다. 

 

한가지 Inotify의 사용 예시를 생각해보죠. 여러분이 Daemon과 Daemon의 설정 파일을 제공한다고 가정했을 때, 누군가 설정파일을 변경 시 Daemon에서 이걸 알고 처리할 수 있도록 하는 기능 추가 시 유용할 수 있습니다.

 

그럼, inotify API와 사용법을 알아본 후 예제 코드를 살펴보시죠.



###  1. inotify_init()

**1.1 SYNOPSIS**

``` cpp
#include <sys/inotify.h>

int inotify_init(void);
```

**1.2  return**

​	초기화된 inotify instance를 가리키는 file descriptor



**1.3  description**	

​	inotify instance를 초기화 하기 위한 함수입니다. 성공하면 파일 디스크립터를 리턴하고, 에러 발생 시 -1을 리턴하고, errno가 설정됩니다. 

​	여기서 파일 디스크립터는 inotify instance를 참조할 때 사용되어집니다.



### 2. inotify_add_watch()

**2.1 SYNOPSIS **

``` cpp
#include <sys/inotify.h>

int inotify_add_watch(int fd, const char *pathname, uint32_t mask);
```

**2.2 Parameter**

- fd : inotify_init() 에서 리턴받은 file descriptor
- pathname : 모니터링 리스트에 생성되거나 수정되는 파일
- mask : pathname에 모니터링할 이벤트 지정 마스크

**2.3  return**

​	추가 또는 수정된 inotify instance를 가리키는 file descriptor

**2.4  description**

​	file descriptor가 가리키는 inotify instance의 모니터링 리스트에 새로운 파일 또는 디렉토리를 추가하거나 기존에 추가되어 있던 항목을 수정하기 위해 사용됩니다. 

​	기존에 추가 되어있다면 mask만 수정됩니다. 

​	mask의 종류는 매우 다양하며 몇 가지만 뒤에서 알아보겠습니다.



### 3. inotify_rm_watch()

**3.1 SYNOPSIS **

``` cpp
include <sys/inotify.h>

int inotify_rm_watch(int fd, int wd);
```

**3.2 Parameter**

- fd : inotify_init() 에서 리턴받은 file descriptor
- wd : inotify_add_watch()의 이전 호출이 리턴한 file descriptor.

**3.3  return**

​	수정된 inotify instance를 가리키는 file descriptor

**3.4  description**

​	File descriptor fd가 가리키는 inotify instance로부터 wd로 지정한 모니터링 리스트를 제거합니다.



### 4. **Inotify Event Mask**

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



### 5. **Inotify_event**

그럼 이제 모니터링한 파일 or 디렉토리에서 발생한 이벤트를 어떻게 읽어올 것인지 살펴보겠습니다. inotify_event를 이용해 이벤트를 읽어옵니다. 보통 read()를 사용하나, Blocking 되는 문제가 있어, select나 poll 같은 Multiflexing I/O들을 많이 사용합니다. 여기서는 read()를 이용한 설명을 드리겠습니다. 아래는 inotify_event 구조체를 나타낸 것입니다. 

``` cpp
struct inotify_event {
    int      wd;       /* Watch descriptor */
    uint32_t mask;     /* Mask describing event */
    uint32_t cookie;   /* Unique cookie associating related
                                     events (for rename(2)) */
    uint32_t len;      /* Size of name field */
    char     name[];   /* Optional null-terminated name */
};
```

#### 5.1 **Example Code**

``` cpp
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
    if (event->mask & IN_CLOSE_WRITE)
        printf("IN_CLOSE_WRITE ");
    if (event->mask & IN_CREATE)
        printf("IN_CREATE ");
    if (event->mask & IN_DELETE)
        printf("IN_DELETE ");
    if (event->mask & IN_ISDIR)
        printf("IN_ISDIR ");
    if (event->mask & IN_MODIFY)
        printf("IN_MODIFY ");
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
 
    wd = inotify_add_watch(fd, INOTIFY_PATH, IN_MODIFY | IN_CREATE | IN_DELETE);
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

