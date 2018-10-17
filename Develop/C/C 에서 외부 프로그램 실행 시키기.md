### C/CPP 에서 외부 프로그램 실행 시키기.

---

C/C++ 에서 외부 프로그램을 실행 시키는 방법에 관한 글 이다.

---

C/C++ 에서 외부 프로그램을 실행 시키기 위해서 system() 함수를 사용 하면 실행 시킨 외부 프로그램이 종료 될 때 까지 기다려야 한다.

그러면 실행시킨 외부 프로그램의 종료를 대기 하지 않고 실행 시킬 수 있는 방법은 없는것 일까?
Linux, Unix 에서는 fork() 함수, SIGNAL 처리,  execv()  함수를 이용하여 외부 프로그램을 실행 시키고 종료를 대기하지 않도록 할 수 있다.

Linux man page 를 보면 "exec () 함수 군은 현재 프로세스 이미지를 새로운 프로세스 이미지로 대체합니다." 라고 되어 있다.



다음은 Linux-C 에서 외부 프로그램을 실행 시키는 예제 프로그램 이다.

* exec_main.c 

```
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <dirent.h>
#include <signal.h>

/**
 * 외부 프로그램을 실행 시킨다. 
 *
 * @param	program		[in]	program 명
 * @param	arg1		[in]	program 실행시 전달할  첫번째 parameter
 * @param	arg2		[in]	program 실행시 전달할  두번째 parameter 
 * @return	정상 실행시: 0, 오류 발생시 -1
 * @see		이 함수를 호출 하기전에 SIGCHLD 시그널을 무력화 시키지 않으면.
 *          zombie 가 발생 한다.
 */
int execLinuxProcess( char* program, char* arg1, char* arg2 )
{
    char *argv[4];
    int pid = fork () ;

    if (0 == pid)
    {
        argv[0] = program;
        argv[1] = arg1;
        argv[2] = arg2;
        argv[3] = NULL;

        execv (argv[0], (char **)argv) ;
        return 0;
    }
    else if ( pid < 0)
    {
        printf("cannot fork for exec %s\n", argv[0]) ;
        return -1 ;
    }

    return 0;
}

int main(int argc, char** argv)
{
	struct sigaction    act;
	int					state = 0;
	int					i     = 0;
	
    act.sa_handler = SIG_IGN;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;

    // register signal handler
    state = sigaction(SIGCHLD, &act, 0);

	if(state != 0){
	    puts("sigaction() error");
	    exit(1);
	}

	// 이 아래 서 부터 외부 프로그램을 실행 시키면 된다.
	execLinuxProcess("VeryLongExec","0", "100"  );
	
	for( i = 0; i < 50; i++ )
	{
        sleep( 1 );
        print(".");
        fflush( stdout );
	}
	
	return 0;
}
```



다음은 테스트를 위한 VeryLongExec 프로그램 생성 소스 

* VeryLongExec.c

```
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

int main(int argc, char** argv)
{
    int i = 0;
    int count = 0;
    
    if( argc != 3 )
    {
        printf( "Usage: %s start_num end_num\n", argv[0] );
        exit( 0 );
    }
    
    i = atoi( argv[1] );
    count = atoi( argv[2] );
    
    for( ; i <= count; i++ )
    {
        printf( "#");
        fflush( stdout );
        sleep( 1 );
    }
    
    return 0;
}
```



* VeryLongExec.c 는 글을 적는 시점에 즉석에서 작성한 프로그램 이지만 정상 작동 할 것으로 보인다.



프로그램의 동작은 "exec_main" 이 실행 되고 내부적으로 VeryLongExec 를 호출 하여 2개의 프로그램이 동시에 실행 되면서 '.' 과 '#' 가 번갈아 (실행에 따라 순차적이지 않을 수 있음)  출력 되다, exec_main 프로그램이 먼저 종료 되고  "VeryLongExec" 프로그램이 나머지 '#' 을 출력 하고 종료 된다.



execLinuxProces() 함수는 char *argv[4]; 를 수정 하여 argment 의 개 수 를 조정 할 수 있다.

하지만 마지막 인자는 반드시 NULL 로 대입 후 호출 하여야  한다.



##### - 끝 -



 