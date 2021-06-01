# C 에서 popen 함수 사용

출처: https://www.it-note.kr/43

daemon 프로그램과 같이 실행하면 알아서 실행되는 프로그램이 있는가 하면, 어떤 프로그램이나 명령어는 실행시 화면에 keyboard로 데이터를 입력하고, 결과를 화면으로 확인하면서 작업을 하는 경우가 있습니다. 이와 같이 실행 시에 사람의 입력과 결과 확인을 수반하는 프로그램을 입력이나 출력을 제어하기 위하여 사용하는 함수가 popen(3) 함수입니다. popen(3) 함수는 기 개발된 프로그램 또는 명령어를 직접 실행을 시키고, type에 따라 표준 입력으로 명령어에 데이터를 전달하거나 표준 출력으로 출력되는 값을 제어할 수 있게 합니다. 

  이 stream은 단방향으로만 control할 수 있습니다. 즉, read-only, write-only로만 open할 수 있습니다. popen은 내부적으로 fork(2), pipe(2), execve(2) 등을 호출합니다. (system(3)을 호출한 것과 비슷합니다.) stream을 종료할 때에는 fclose(3)가 아닌 pclose(3)로 close해야 합니다.



### Syntax

```c
#include <stdio.h>

FILE *popen(const char *command, const char *type);
```





### 파라미터

```c
command 
    - 실행할 명령어 입니다. 
      command line에서 입력하듯이 명령어와 parameter를 띄어쓰기로 문자열을 설정합니다.

  ex. 
     "ls -al"
```

```c
type
    - 명령어의 Input을 제어할 것인지 Output을 제어할 지를 설정합니다. 
    
 "r" : read-only로 pipe를 open합니다. 즉, 명령어(command)의 결과(표준출력)를 읽겠다는 의미입니다.
        입력없이 output만 있는 프로그램의 출력 결과를 받고 싶을 때에 사용합니다.

 "w" : write-only로 pipe를 open합니다. 
        즉, 명령어(command)의 입력(표준입력)으로 데이터를 전달하겠다는 의미입니다.
        결과는 확인할 수 없으며, 입력순서가 정해져 있을 때에 사용합니다.
        만약 결과도 확인해야 한다면, 명령어를 줄 때에 redirect로 파일에 저장하도록 합니다. 
        예. popen("gdb sample -core core-file > result.txt", "w");
```



### RETURN

```
NULL 아님
    - 정상적으로 pipe stream이 생성되었습니다.

NULL
    - popen(3) 내부에서 fork(2), pipe(2) 등이 실패하였습니다.
      메모리 할당 오류가 아니라면, 상세한 오류내용은 errno에 설정됩니다.
```



###  활용 예제

**Sample.** ls -al 명령어의 결과를 받아서 활용하는 sample (Linux)

```c
#include <stdio.h>
#include <errno.h>

int main(int argc, char *argv[])
{
     char line[4096];
     FILE *fp;

     if((fp = popen("ls -al", "r")) == NULL) {
         fprintf(stderr, "pipe open error: %s\n", strerror(errno));
         return 1;
     }

     while(fgets(line, 4096, fp) != NULL) {
         printf("%s", line);
         /* 또는 ls -al 결과를 parsing하여 활용하는 로직... */
     }

     pclose(fp);
     return 0;
}
```



**입력 결과를 임시파일로 저장하는 예제**

```c
#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <string>

#ifdef WIN32
#define popen _popen 
#define pclose _pclose
#endif

int main(int argc, char* argv[])
{
   const char  *pszCommand = "diskpart > sample.txt";
   FILE        *fp = NULL;
   size_t      readSize = 0;
   char        pszBuff[1024];

   // 명령어 실행
   fp = popen(pszCommand, "w");
   if( !fp)
   {
	  printf( "%s(%d)\n", __FILE__, __LINE__ );
      return -1;
   }

   memset(pszBuff, 0x00, sizeof(pszBuff));
   sprintf( pszBuff, "list disk\n" );
   fputs( pszBuff, fp );


   memset(pszBuff, 0x00, sizeof(pszBuff));
   sprintf( pszBuff, "list volume\n" );
   fputs( pszBuff, fp );

   pclose(fp);
}
```

*필요시 저장된 sample.txt 를 읽어서 분석 할 수 있다*

