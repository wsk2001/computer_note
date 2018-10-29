### C/C++ 에서 Daemon 프로그램 만들기



Daemon 프로그램 이란?  Linux, Unix 에서 백그라운드로 실행 되는 서버 프로그램을 의미 하며 프로그램을 실행한 터미널을 닫아도 종료 되지 않고 계속 실행 되는 프로그램을 의미 한다.

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

