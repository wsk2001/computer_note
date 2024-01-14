# RTC 와  세마포어 sample

먼저, RTC와 세마포어를 사용하여 100ms 간격으로 이벤트를 발생시키는 프로그램을 작성합니다. 그 후, 이 이벤트를 수신하여 동작하는 복수의 프로그램을 작성하겠습니다.

### 이벤트 발생 프로그램 (rtc_event_generator.c)

``` c
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <linux/rtc.h>
#include <unistd.h>
#include <signal.h>
#include <sys/time.h>
#include <semaphore.h>

int rtc_fd;
sem_t *rtc_semaphore;

// RTC 인터럽트 핸들러 함수
void rtc_handler(int signum) {
    sem_post(rtc_semaphore);
}

// RTC 초기화 함수
void init_rtc() {
    rtc_fd = open("/dev/rtc0", O_RDONLY);
    if (rtc_fd == -1) {
        perror("Failed to open RTC device");
        exit(EXIT_FAILURE);
    }

    // RTC 인터럽트 핸들러 등록
    signal(SIGALRM, rtc_handler);

    // RTC 주기 설정 (여기서는 100ms로 설정)
    struct itimerval timer;
    timer.it_value.tv_sec = 0;
    timer.it_value.tv_usec = 100000; // 100ms
    timer.it_interval = timer.it_value;
    setitimer(ITIMER_REAL, &timer, NULL);

    // RTC 주기적으로 인터럽트가 발생하도록 설정
    struct rtc_time rtc_tm;
    if (ioctl(rtc_fd, RTC_RD_TIME, &rtc_tm) == -1) {
        perror("Failed to read RTC time");
        exit(EXIT_FAILURE);
    }
    rtc_tm.tm_year = 70; // RTC 시간 초기화 (1970년 이후의 시간)
    if (ioctl(rtc_fd, RTC_SET_TIME, &rtc_tm) == -1) {
        perror("Failed to set RTC time");
        exit(EXIT_FAILURE);
    }

    // 세마포어 초기화
    rtc_semaphore = sem_open("/rtc_semaphore", O_CREAT, 0644, 0);
    if (rtc_semaphore == SEM_FAILED) {
        perror("Failed to create semaphore");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // RTC 초기화
    init_rtc();

    // 메인 루프
    while (1) {
        // RTC 인터럽트를 기다림
        sem_wait(rtc_semaphore);

        // 여기에 100ms마다 실행할 코드를 작성
        printf("RTC Event!\n");
    }

    // RTC 파일 디스크립터 닫기
    close(rtc_fd);

    // 세마포어 제거
    sem_close(rtc_semaphore);
    sem_unlink("/rtc_semaphore");

    return 0;
}

```

컴파일 방법

``` bash
gcc -o rtc_event_generator rtc_event_generator.c -lrt -lpthread
```





### 이벤트 수신 프로그램 (rtc_event_receiver.c -)

``` c
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h> 
#include <semaphore.h>

int main() {
    sem_t *rtc_semaphore;

    // 세마포어 열기
    rtc_semaphore = sem_open("/rtc_semaphore", O_CREAT, 0644, 0);
    if (rtc_semaphore == SEM_FAILED) {
        perror("Failed to open semaphore");
        return 1;
    }

    // 메인 루프
    while (1) {
        // RTC 이벤트를 기다림
        sem_wait(rtc_semaphore);

        // 여기에 RTC 이벤트를 수신하여 실행할 코드를 작성
        printf("Received RTC Event! Do something...\n");
    }

    // 세마포어 닫기
    sem_close(rtc_semaphore);

    return 0;
}
```

컴파일 방법

``` bash
gcc -o rtc_event_receiver rtc_event_receiver.c -lrt -lpthread
```



두 프로그램 간에는 세마포어를 통해 통신이 이루어집니다. 첫 번째 프로그램은 RTC 이벤트가 발생할 때마다 세마포어를 통해 두 번째 프로그램에 이벤트를 알리며, 두 번째 프로그램은 세마포어를 통해 RTC 이벤트를 기다리고 수신합니다.

주의: 프로그램을 실행하기 전에 `/dev/rtc0`에 접근 가능한 권한이 필요하며, 두 프로그램이 같은 세마포어를 사용하고 있어야 합니다. 또한, 이 예제는 단순한 데모용이며, 실제 환경에서는 더 많은 예외 처리 및 안정성을 고려해야 합니다.





