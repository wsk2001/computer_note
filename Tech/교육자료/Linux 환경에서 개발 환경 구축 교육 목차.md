# Linux 환경에서 개발 환경 구축 교육 목차



- Linux  설치 환경 소개

  -  VMWare 를 이용하는 방법 등

    

- 실습 환경 구축 - OS 설치

  - VMWare 를 이용하여 Ubuntu Linux 설치하는 방법




- Linux OS 주요 명령어 소개

  - directory 관련 명령, 퍼미션,  환경변수,  ..., source 명령, 

  - 심볼릭 링크, etc

  - 압축 Tool 기본 사용법 (tar, gzip)

  - sudo 사용법

  - 기타, ...



```
Linux에서 권한은 파일 및 디렉터리에 대한 액세스를 제어하는 방법입니다. 권한에는 읽기, 쓰기 및 실행의 세 가지 유형이 있습니다. 각 파일 및 디렉토리에는 소유자, 그룹 및 기타 사용자에게 할당된 권한이 있습니다. 소유자는 파일이나 디렉토리를 만든 사용자이고, 그룹은 사용자 모음이며 나머지는 시스템의 다른 모든 사용자입니다.

권한은 -rwxrwxrwx와 같은 일련의 문자로 표시될 수 있습니다. 여기서 각 문자는 소유자, 그룹 및 기타에 대한 권한을 나타냅니다. 예를 들어 문자 'r'은 읽기 권한을, 'w'는 쓰기 권한을, 'x'는 실행 권한을 나타냅니다. 글자가 없으면 권한이 부여되지 않았음을 나타냅니다.

chmod 명령을 사용하여 파일 또는 디렉토리에 대한 권한을 변경할 수 있습니다. chown 명령을 사용하여 파일 또는 디렉토리의 소유자 및 그룹을 변경할 수도 있습니다.

시스템을 보호하고 중요한 정보에 대한 무단 액세스를 방지하는 데 도움이 되므로 권한을 올바르게 이해하고 설정하는 것이 중요합니다.
```









- bash Shell 필수 설정
  - alias,  function, LD_LIBRARY_PATH, PATH
  - 특정 패턴의 문자열이 포함된 파일 찾기, 기타, ... 



- VI 에디터 기본 사용법
  - 커서 이동, 삽입, 수정, 삭제
  - 찾기, 일괄 변경, Shell 획득,
  - 기타, ...



- SSH 설치 및 방화벽 설정
  - SSH Server 설치
  - 방화벽 설정
  - SSH Client Terminal Application 설치 및 사용 방법
    - Xterm (MobaXterm)
  - SSH Client 편집기 (notepad++) 를 이용한 Linux 파일 수정



- Linux 주요 Directory 소개
  - /dev, ...



- Linux kernel 및 Device Driver 소개

  - device driver 구분 (블럭 디바이스, 케릭터 디바이스)

  - Kernel Version 확인 방법

  - kernel Module 설치 방법

    

- 개발 Tool 설치 방법 및 테스트

  - sudo apt-get update 

  - sudo apt-get install build-essential 
  - Sample program 작성 및 테스트

  

- Makefile 만드는법

  - 간단한 Makefile 예제 및 설명

  - Static lib 만드는법 및 사용 방법

  - Dynamic lib 만드는법  및 사용 방법

  - Makefile 여러 경로에 있는 소스 일괄 Build

  - Makefile 에서 Shell 사용하기

  

- Linux 에서 C/C++ 을 이용한 개발시 능률을 향상 시키는 Plugin 설치 및 사용법

  - ctags, taglib 사용법
  
  


- C/C++ 개발 시 참조용 명령어

  - ldd, nm
  - 기타

  

- 기타 Tool 및 참조 할만한 기술 소개

  - scp 사용법

  - 네트워크 모니터링 (netstat)

  - LD_PRELOAD
  - 기타



- 실습 및 질의 응답.



위에 소개한 목차는 최종 문서 작성시 순서가 변경 될 수 있으며, 필요에 의해 항목이 추가 될 수 있습니다.



자료 보여주고 피드백 받은내용

- System 함수 사용방법

  - 사용 목적 Lock, 또는 Return 값 Check

- Rtc 설정 및 read 방법

  - Linux 시스템의 실시간 시계(RTC)는 hwclock 명령을 사용하여 설정할 수 있습니다. hwclock 명령은 명령줄에서 하드웨어 시계(BIOS 또는 CMOS 시계라고도 함)를 읽고 설정하는 데 사용됩니다. Linux에서 RTC를 설정하는 구문은 다음과 같습니다.

  - ``` bash
    sudo hwclock --set --date "MM/DD/YYYY HH:MM:SS"
    ```

    ```
    이 명령은 하드웨어 시계를 지정된 날짜 및 시간으로 설정합니다. 날짜 및 시간은 'MM/DD/YYYY HH:MM:SS'(월/일/년 시:분:초) 형식이어야 합니다.
    
    --systohc 옵션을 사용하여 하드웨어 시계를 현재 시스템 시간으로 설정할 수도 있습니다.
    
    sudo hwclock --systohc
    
    하드웨어 시계가 잘못된 시간으로 설정된 경우, 특히 시스템의 시간대가 올바르게 설정되지 않은 경우 문제가 발생할 수 있으므로 hwclock 명령을 주의해서 사용해야 한다는 점도 중요합니다.
    ```

  - 읽는 방법

    Linux에서 RTC(Real-Time Clock)는 ioctl 시스템 호출 및 RTC_RD_TIME 요청을 사용하여 C에서 읽을 수 있습니다.

    다음은 C에서 RTC 시간을 읽는 방법의 예입니다.

    ``` c
    #include <stdio.h>
    #include <stdlib.h>
    #include <fcntl.h>
    #include <unistd.h>
    #include <linux/rtc.h>
    #include <sys/ioctl.h>
    
    int main() {
        int rtc_fd;
        struct rtc_time rtc_tm;
    
        rtc_fd = open("/dev/rtc0", O_RDONLY);
        if (rtc_fd == -1) {
            perror("open");
            exit(1);
        }
    
        if (ioctl(rtc_fd, RTC_RD_TIME, &rtc_tm) == -1) {
            perror("ioctl");
            close(rtc_fd);
            exit(1);
        }
    
        printf("Current RTC date/time is %d-%d-%d, %02d:%02d:%02d.\n",
            rtc_tm.tm_mday, rtc_tm.tm_mon + 1, rtc_tm.tm_year + 1900,
            rtc_tm.tm_hour, rtc_tm.tm_min, rtc_tm.tm_sec);
    
        close(rtc_fd);
        return 0;
    }
    ```

    이 코드는 open() 함수를 사용하여 '/dev/rtc0'에 있는 RTC 장치 파일을 연 다음 RTC_RD_TIME 요청과 함께 ioctl() 함수를 사용하여 현재 RTC 시간을 rtc_time 구조체로 읽습니다. 구조체에는 일, 월, 연도, 시, 분 및 초에 대한 필드가 포함되어 있습니다. 그런 다음 날짜와 시간이 콘솔에 인쇄됩니다.

    RTC 장치 파일은 '/dev/rtc' 또는 '/dev/misc/rtc'와 같이 시스템의 다른 경로에 있을 수 있습니다.
    또한 이 예제는 RTC 장치 드라이버용으로 작성되었으며 다른 장치의 경우 접근 방식이 다를 수 있습니다.

    

- Task 스케쥴링 방법

  - C에서 Linux 작업 스케줄링

  - Linux에서 C의 태스크 스케줄링은 sched.h 라이브러리의 sched_setscheduler() 함수를 사용하여 달성할 수 있습니다. 이 기능은 주어진 프로세스에 대한 스케줄링 정책과 우선 순위를 설정합니다.

    다음은 C에서 프로세스에 대한 스케줄링 정책 및 우선 순위를 설정하는 방법의 예입니다.

    ``` C
    #include <sched.h>
    #include <unistd.h>
    
    int main() {
        int policy = SCHED_FIFO;  // or SCHED_RR, SCHED_OTHER
        struct sched_param param;
        param.sched_priority = 1; // set priority to 1
    
        int ret = sched_setscheduler(0, policy, &param);
        if (ret == -1) {
            perror("sched_setscheduler");
            exit(1);
        }
        // 나머지 코드
        return 0;
    }
    ```

    이 코드는 우선 순위가 1인 First-In First-Out 실시간 스케줄링을 나타내는 SCHED_FIFO로 스케줄링 정책을 설정합니다. 다른 가능한 정책으로는 라운드 로빈 스케줄링을 위한 SCHED_RR과 표준 Linux 스케줄러를 위한 SCHED_OTHER가 있습니다. sched_setscheduler()의 첫 번째 매개변수는 프로세스 ID이며, 이 경우 0은 현재 프로세스를 의미합니다.

    sched_param 구조체의 sched_priority 필드는 프로세스의 우선 순위를 설정합니다. 유효한 우선 순위 범위는 일정 정책에 따라 다르며 SCHED_FIFO 및 SCHED_RR의 경우 0
    99, SCHED_OTHER의 경우 0
    sched_get_priority_max(SCHED_OTHER)일 수 있습니다.

    높은 우선 순위를 설정한다고 해서 프로세스가 항상 실행된다는 보장은 없으며 시스템 부하 및 기타 요인에 따라 달라집니다. 또한 실시간 스케줄링 정책은 적절하게 사용되지 않으면 시스템 전체 잠금을 유발할 수 있으므로 위험할 수 있습니다.

    프로세스에 대한 스케줄링 정책 및 우선순위를 설정하려면 수퍼유저 권한이 필요할 수 있으며 스케줄링 정책이 성공적으로 설정되었는지 확인하려면 sched_setscheduler() 함수의 반환 값을 확인해야 합니다.

    

- 에대해서 교육내용에 추가해 달라고 하네요

- 환경은 centos 8.4 면 좋겠다고 합니다. 가능할까요?
