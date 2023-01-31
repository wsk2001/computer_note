### C/C++ 에서 Daemon 프로그램 만들기



Daemon 프로그램 이란?  Linux, Unix 에서 백그라운드로 실행 되는 서버 프로그램을 의미 하며 프로그램을 실행한 터미널을 닫아도 종료 되지 않고 계속 실행 되는 프로그램을 의미 한다.

Linux 데몬은 일반적으로 직접적인 사용자 상호 작용 없이 Linux 시스템에서 실행되는 백그라운드 프로세스입니다. 백그라운드에서 지속적으로 실행되어 시스템 모니터링, 네트워크 연결 관리 또는 들어오는 요청에 대한 서버 역할과 같은 특정 작업을 수행합니다. 데몬은 일반적으로 서비스로 실행되며 Systemd 또는 Upstart와 같은 운영 체제의 초기 시스템에 의해 시작 및 관리됩니다.

---

아래는 Daemon 프로그램 예제 이다.



```C
#include <sys/type.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

int MakeDaemon( void )
{
    int status = -1;

    /* 첫번째 fork. */
    status = fork();
    switch( status )
    {
        case -1 : perror( fork() );
                  exit(-1);
        case  0 : break;    /* first child process  */
        default : exit(0);  /* parent process       */
    }

    status = setsid();
    if( status == -1 )
    {
        perror( setsid() );
        exit(0);
    }

    /*  두번째 fork, */
    status = fork();
    switch( status )
    {
        case -1 : perror( fork() );
                  exit(-1);
        case  0 : break;    /* second child process  */
        default : exit(0);  /* first child process   */
    }
}

int main()
{
    MakeDaemon();
    /* 여기서 부터 daemon process 가 동작 하게 프로그래밍 하면 된다 */
}
```

