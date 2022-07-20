# syslog 사용

출처: https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1

### 목차 

### [1.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) 소개

syslog는 Unix(:12) 시스템에서 로그메시지를 처리하기 위해서 제공하는 (매우 오래된)표준 인터페이스 중 하나다. 이것을 이용하면, 시스템이나 응용 프로그램에서 발생하는 각종 메시지를 체계적으로 관리할 수 있다. 또한 표준이기 때문에, 운영체제에 관계없이 동일하게 사용할 수 있다는 장점도 제공한다.



응용프로그램에서 굳이 이러한 메시지를 처리하는 루틴을 만들지 않고, syslog를 사용하는데에는 몇가지 이유가 있다. 일단 메시지처리를 위한 루틴에 특별히 신경쓰지 않고 다른 특화된 모듈에 맡김으로써, **작은것이 아름답다** 라는 unix 철학에 부합됨과 동시에, 코딩시 라인수를 대단히 많이 줄일수 있다라는 점 그리고 검증된 안전한 도구라는 점이다.



### [2.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) syslog의 기본

이러한 각 메시지의 처리는 syslogd 라는 데몬프로그램에 의해서 처리되게 되는데, syslogd 는 프로세스의 시작시에 /etc/syslog.conf 를 읽어들인다. 이 파일에는 각각의 메시지를 성격에 맞도록 분류해서 저장하기 위한 규칙이 정의되어 있다. 다음은 syslog.conf 의 파일내용중 일부이다.

``` conf
*.err;kern.debug;auth.notice 	/dev/console
daemon,auth.notice 				/var/log/messages
lpr.info 						/var/log/lpr.log
mail.* 							/var/log/mail.log
ftp.* 							/var/log/ftp.log
auth.* 							@prep.ai.mit.edu
auth.* 							root,amrood
netinfo.err 					/var/log/netinfo.log
install.* 						/var/log/install.log
*.emerg 						*
*.alert 						|program_name
mark.* 							/dev/console
```



syslog.conf 는 2개의 필드로 구성되어 있다. 첫번째 필드는 "selector" 필드 라고 불리우는데, 이 필드는 다시 facilities 와 priorities 의 값으로 구성되어 있다. facilities 는 로그의 분류를 나타내고 priorities 는 각 분류에서의 로그 레벨을 나타낸다.



일반적인 프로그램을 작성할때도 로그정책은 매우 중요하다. 보통 레벨 1은 가장 낮은 수준의 디버그 메시지 레벨 3은 모든 디버그 메시지를 모두 남기는 식으로 정하는데 **priorities**를 이용해서 발생되는 로그에 레벨을 부여해 줄 수 있다.



priority 는 다음과 같이 커널에 정의되어 있다.

``` c
#define KERN_EMERG    "<0>"  /* system is unusable               */
#define KERN_ALERT    "<1>"  /* action must be taken immediately */
#define KERN_CRIT     "<2>"  /* critical conditions              */
#define KERN_ERR      "<3>"  /* error conditions                 */
#define KERN_WARNING  "<4>"  /* warning conditions               */
#define KERN_NOTICE   "<5>"  /* normal but significant condition */
#define KERN_INFO     "<6>"  /* informational                    */
#define KERN_DEBUG    "<7>"  /* debug-level messages             */
```

2번째 필드는 "action" 필드로 첫번째필드의 룰에(facilities 와 priorities) 부합되는 메시지를 어떠한 파일로 처리할것인지에 대한 파일이름을 정의해두고 있다. 유닉스 에서는 일반적으로 /var/log 밑에 메시지를 쌓는게 관례이다. 또한 파일 뿐만 아니라 파이프(:12)를 통해서 다른 응용프로그램에 로그메시지를 보내서 처리하거나, 원격지에 있는 다른 유닉스 시스템으로도 보낼 수 있다.



이번에 우리는 syslog 를 이용 메모리상태를 체크해서 1초 간격으로 원격지의 호스트에 보내는 프로그램을 만들어 보도록 하겠다.



### [3.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) syslog 함수

리눅스는 표준라이브러리 형태로 syslog 함수를 제공한다. 이 함수는 다음과 같이 정의되어 있다.

``` c
#include <syslog.h>

void syslog(int priority, const char *format, ...);
```

옵션인 **priority**는 syslog.h에 정의되어 있는 priority 와 facility 의 비트연산을 통해서 만들 수 있다.



다음은 정의되어 있는 priority 값들로 kernel의 priority 들과 대응됨을 알 수 있다.

``` c
#define LOG_EMERG   0   /* system is unusable */
#define LOG_ALERT   1   /* action must be taken immediately */
#define LOG_CRIT    2   /* critical conditions */
#define LOG_ERR     3   /* error conditions */
#define LOG_WARNING 4   /* warning conditions */
#define LOG_NOTICE  5   /* normal but significant condition */
#define LOG_INFO    6   /* informational */
#define LOG_DEBUG   7   /* debug-level messages */
```



다음은 정의되어 있는 facility 값들이다. 주요 응용별로 facility 가 나누어져 있음을 알 수 있다.

``` c
#define LOG_KERN    (0<<3)  /* kernel messages */
#define LOG_USER    (1<<3)  /* random user-level messages */
#define LOG_MAIL    (2<<3)  /* mail system */
#define LOG_DAEMON  (3<<3)  /* system daemons */
#define LOG_AUTH    (4<<3)  /* security/authorization messages */
#define LOG_SYSLOG  (5<<3)  /* messages generated internally by syslogd */
#define LOG_LPR     (6<<3)  /* line printer subsystem */
#define LOG_NEWS    (7<<3)  /* network news subsystem */
#define LOG_UUCP    (8<<3)  /* UUCP subsystem */
#define LOG_CRON    (9<<3)  /* clock daemon */
#define LOG_AUTHPRIV    (10<<3) /* security/authorization messages (private) */
#define LOG_FTP     (11<<3) /* ftp daemon */
```

그러나 위의 facility 값들만을 가지고는 log를 분류하는데 충분하지 않을 것이다. 이럴경우 예약된 facility 값들을 사용할 수 있다.

``` c
#define LOG_LOCAL0  (16<<3) /* reserved for local use */
#define LOG_LOCAL1  (17<<3) /* reserved for local use */
#define LOG_LOCAL2  (18<<3) /* reserved for local use */
#define LOG_LOCAL3  (19<<3) /* reserved for local use */
#define LOG_LOCAL4  (20<<3) /* reserved for local use */
#define LOG_LOCAL5  (21<<3) /* reserved for local use */
#define LOG_LOCAL6  (22<<3) /* reserved for local use */
#define LOG_LOCAL7  (23<<3) /* reserved for local use */
```

### [4.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) 예제코드 만들기

가장 먼저 우리는 syslog.conf 를 수정해서 해당 syslog 메시지를 처리하도록 룰을 만들어줘야 한다.



메모리 용량을 위해서 selector 의 이름은 mem으로 정하고, 모든 priorities(*)에 대해서 받아들이고, 받아들인 메시지는 파일로 쌓도록 하겠다.

그러면 메시지 전송프로그램이 파일의 내용을 원격지 호스트로 전송하는 방법을 취하도록 한다. 이 프로그램의 구성을 간단히 그려보면 아래와 같을 것이다.



![구성도](https://www.joinc.co.kr/images/articles/19/syslog_2.jpg) memchecker 은 /proc/meminfo 파일을 주기적으로(1초 단위)로 읽어들여서 이 값을 syslog 를 통해서 /var/log/mem 이라는 파일로 보낸다. memsender 은 /var/log/mem 파일을 감시하고 있다가 메시지가 추가되면, 해당 메시지를 소켓을 통해서 memrecver 로 보내도록 한다. memrecver 은 printf 를 이용해서 해당 메시지를 화면상에 출력한다.



가장 먼저 우리는 syslog.conf 를 편집해야 하는데, 기존의 로그들과 겹치지 않도록 facilities 와 priorities 를 지정해야 한다.



facilities 는 미리 정의되어 있는데, local0 에서부터 local7까지는 사용자정의를 위한 facilities 로 남겨져 있다. 우리는 그중 local0 을 사용하도록 하고 모든 priorites 에 대해서(즉 priorites 구분없이) 메시지를 받아들이도록 할것이다. 다음과 같은 내용을 저장하자

``` c
local0.*            /var/log/meminfo
```

앞으로 local0 의 facilities 를 가지는 모든 메시지는 /var/log/meminfo 에 쌓이게 될것이다. (linux의 syslog.conf 는 일반적인 BSD와는 조금 다른듯 합니다. 현재 사용중인 Tru64에서는 local0.* 와 같이 사용해도 특별한 에러등은 없지만 아무런 출력이 되지 않습니다. 구체적으로 local0.debug, local0.info 와 같이 적어주어야 하더군요. 다만 facility 자체나 message destination 에는 * 가 유효한듯 합니다. - freesoft)



syslog.conf 의 내용을 syslogd 가 인식하도록 하기 위해서 syslogd 를 다시 시작하도록 하자.

``` sh
syslog restart
```

이제 syslog 를 사용할때 facilities 를 local0 으로 지정해주면, /var/log/meminfo 로 로그메시지가 쌓이게 된다.

``` c
#include <syslog.h>

int main(int argc, char **argv)
{
    syslog(LOG_INFO|LOG_LOCAL0, "hello world\n");
}

[root@localhost test]# cat /var/log/meminfo
```

이제 본격적으로 프로그램을 작성해 보도록 하자. 첫번째 프로그래은 "memcheker" 로 /proc/meminfo 를 읽어들여서 매초마다 syslog 를 이용해서 메시지를 파일로 보내는 프로그램이다.

**예제 : memcheker.c**

``` c
#include <syslog.h>
#include <stdio.h> 
#include <unistd.h>

struct py_mem
{
    char total[15];
    char used[15];
};

struct sw_mem
{
    char total[15];
    char used[15];
};

char field_list[9][20];
void get_meminfo(char *buf, int number);
    
int main()
{   
    FILE *fp;
    char buf_in[80];
    int  line_num = 0; 

    struct py_mem real_mem;
    struct sw_mem vir_mem;
    
    char mem_info[255];

    for(;;)
    {
        line_num = 0;
        fp = fopen("/proc/meminfo", "r");
        if (fp == NULL)
        {
            perror("file open error : ");
            exit(0);
        }
        memset(buf_in, '\0', 80);
        while(fgets(buf_in, 80, fp) != NULL)
        {
            if (line_num == 0)
            {
                line_num++;
                continue;
            }
            else if (line_num == 1)
            {
                buf_in[strlen(buf_in) -1] = '\0';
                get_meminfo(buf_in,7);
                sprintf(mem_info, "%s,%s,%s,%s,%s,%s",
                                field_list[1], field_list[2],
                                field_list[3], field_list[4],
                                field_list[5], field_list[6]);
            }
            else if (line_num == 2)
            {
                get_meminfo(buf_in,2);
                sprintf(mem_info, "%s,%s,%s\n", mem_info, field_list[1], field_list[2]);
            }
            if (line_num == 3)
            {
                break;
            }

            memset(buf_in, '\0', 80);
            line_num++;
        }
        fclose(fp);
        syslog(LOG_INFO|LOG_LOCAL0, mem_info);
        sleep(1);
    }
}

void get_meminfo(char *buf, int number)
{
    char field_buf[20];
    int i;
    int j;
    int field = 0;
    struct py_mem buf_mem;

    for (i = 0; i < 7; i++)
        memset(field_list[i], '\0', 20);

    for (i = 0, j=0; i < strlen(buf); i++)
    {
        if (buf[i] == ' ')
        {
            if (buf[i+1] == ' ')
            {
                continue;
            }
            else
            {
                if (field == number)
                    return;
                field++;
                j = 0;
            }
        }
        else
        {
            field_list[field][j] = buf[i];
            j++;
        }
    }
}
```

위의 프로그램은 /proc/meminfo 을 열어서 2번째 와 3번째 라인의 정보를 읽어들인다. ![meminfo 내용](https://www.joinc.co.kr/images/articles/19/syslog_4.jpg)



그다음 get_meminfo() 함수를 이용해서, 우리가 필요로하는 정보를 가져온다. 가져온 정보는 csv 스타일로 변경시킨다음, syslog 를 이용해서 파일에 쓰도록 한다.

컴파일후 실제로 /var/log/meminfo 에 적재되는지 tail(1) 을 이용해서 확인해 보도록 하자.

``` sh
tail -f ?
```

아마 1초마다 라인이 갱신되는걸 볼수 있을것이다. 글이 상당히 길어진 관계로 오늘은 memchecker.c 까지만 하고, 나머지 프로그램인 memrecver 과 memsender 은 다음 문서에서 다루도록 하겠다.



### [5.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) syslog를 다른 호스트로 보내기

위에서 언급되었다 시피, 로그를 파일 뿐만 아니라 다른 호스트로 보낼 수도 있다. @뒤에 메시지를 보낼 호스트명을 적어주기만 하면 된다. 만약 **local0**의 모든 레벨의 syslog를 syslog.joinc.co.kr 로 메시지를 보내고 싶다면 다음과 같이 설정하면 된다.

``` sh
local0.*            @syslog.joinc.co.kr
```

### [6.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) 원격지의 syslog 받아들이기

syslog 정보를 다른 호스트로 보내는 기능은 특히 분산환경에서 로그정보를 하나의 서버에서 관리하기 위한 목적으로 유용하게 사용할 수 있다. 이때 원격의 syslog 메시지를 받아들이는 서버는 **원격의 syslog 메시지를 받아들이겠다**는 설정을 해줘야 한다.



syslogd 를 실행할때 **-m 0 -r**옵션을 주면된다. /etc/init.d/sysklogd 를 수정하도록 하자. 배포판마다 약간씩 다르다. 아래는 ubuntu 9.04를 기준으로 설명한다. 다른 배포판이라고 해도 적용시키는데 큰 문제는 없을 것이다.

``` sh
...
else
        # -m 0 -r 을 추가했다. 
        SYSLOGD="${SYSLOGD} -u ${USER} -m 0 -r"
fi
...
```

이제 syslogd를 재 실항하면 원격지의 syslog 메시지를 받아들이는걸 확인할 수 있을 것이다.



### [7.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) syslog 메시지를 다른 프로그램으로 보내기

로그 메시지를 분석해서 다른 프로세스를 타게 하고 싶을때가 있을 것이다. 로그메시지를 SMS - 단문메시지 - 로 보내는 것과 같은게 대표적인 경우가 될 것이다. 혹은 메일로 보내고 싶은 경우도 생길 것이다. 이경우를 위해서 syslog 메시지를 다른 프로그램으로 파이프를 통해서 넘기면 된다. **|**뒤에 프로그램명을 적어주기만 하면 된다. 만약 로그 메시지를 mymail 이라는 프로그램을 통해서, 메일로 보내길 원한다면 다음과 같이 syslog.conf 를 수정하면 된다.

``` sh
local0.*       |/usr/local/mymail
```

### [8.](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1#toc) 참고문헌

- [syslog 사용하기 2](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_2)



- [syslog 소개 문서에서 이어짐](https://www.joinc.co.kr/w/Site/system_programing/Unix_Env/syslog_1)
- wiki 스타일에 맞게 수정해야함.
- 내용도 너무 오래 되었음 수정해야함. 이제 memrecver 에 소켓연결후 syslog 메시지를 전송하는 memsender 를 제작해 보도록하자, 보통의 파일을 열어서 읽은 다음에 소켓으로 전송하는 일반적인 루틴을 가지고 있지만, 파일의 마지막라인을 만나더라도, 파일 연결지시자를 계속 유지하고 있다가 새로운 내용이 추가되면 다시 read 해야 하므로(이를테면 tail 에 -f 옵션을 준것과 비슷한), select(2) 를 통해서 이를 구현하도록 하겠다.
  select 는 입출력다중화를 위한 목적으로 주로 쓰이며, 네트웍 프로그래밍에서 다중 클라이언트를 받아들이는 서버제작 기법으로 많이 활용된다.



**예제: memsender.c**

``` c
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>

#define PORT 12345
#define PACKET_SIZE 255

int main()
{
    int fd;
    int n;
    int state;
    int client_len;
    int client_sockfd;
    int i;
    FILE *fp;
    char *buf;

    char l_buf[1];

    struct sockaddr_in clientaddr;
    struct timeval tv;

    fd_set readfds;

    if((fd = open("/var/log/meminfo", O_RDONLY)) == -1)
    {
        perror("file open error : ");
        exit(0);
    }


    // memrecver 서버에 연결을 위한 소켓생성및 연결

    client_sockfd = socket(AF_INET, SOCK_STREAM, 0);
    clientaddr.sin_family = AF_INET;
    clientaddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    clientaddr.sin_port = htons(PORT);

    client_len = sizeof(clientaddr);

    if (connect(client_sockfd, (struct sockaddr *)&clientaddr, client_len) < 0)
    {
        perror("connect error : ");
        exit(0);
    }

    fp = fdopen(fd, "w");
    buf = (char *)malloc(PACKET_SIZE);
    i = 0;
    while(1)
    {
        tv.tv_sec = 0;
        tv.tv_usec = 10000;

        state = select(2, &readfds, (fd_set *)0, (fd_set *)0, &tv);

        switch(state)
        {
            case -1:
                perror("select error : ");
                exit(0);

            default :
                memset(buf, 0x00, PACKET_SIZE);
                while ((n = read(fd, l_buf, 1)) > 0)
                memset(buf, 0x00, PACKET_SIZE);
                while ((n = read(fd, l_buf, 1)) > 0)
                {
                    buf[i] = l_buf[0];
                    if (l_buf[0] == '\n')
                    {
                        write(client_sockfd, buf, i+1);
                        printf("-->%s", buf);
                        memset(buf, 0x00, PACKET_SIZE);
                        i = 0;
                        continue;
                    }
                    i++;
                }
                break;
        }
    }
    close(fd);
}
```

select 가 들어간것만 빼고는 매우 일반적인 프로그램이므로 설명은 생략하도록 하겠다.
소켓관련 프로그래밍은 [셈플로 알아보는 소켓프로그래밍(1)](http://www.joinc.co.kr/article.php?sid=11)을 >참고하기 바란다.



**예제: memrecever.c**

``` c
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h> 
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/stat.h> 
#include <netinet/in.h>
#include <arpa/inet.h> 
#include <fcntl.h> 

#define SA struct sockaddr
#define PACKET_SIZE 255 

const char *needle = ": ";

int main(int argc, char **argv)
{
    int server_sockfd, client_sockfd;
    int state, clilen;
    int n;

    FILE *fp;

    struct sockaddr_in clientaddr, serveraddr;
    char buf_in[PACKET_SIZE];
    char buf_out[PACKET_SIZE];
    char *par_buf;

    // 소켓연결을 만든다.
    server_sockfd = socket(AF_INET, SOCK_STREAM, 0);
    bzero(&serveraddr, sizeof(serveraddr));

    serveraddr.sin_family = AF_INET;
    serveraddr.sin_addr.s_addr = htonl(INADDR_ANY);
    serveraddr.sin_port = htons(12345);

    if(bind (server_sockfd, (SA *)&serveraddr, sizeof(serveraddr)) == -1)
    {
        perror("bind error : ");
        exit(0);
    }

    if ((state = listen(server_sockfd, 5)) == -1)
    {
        perror("listen error : ");
        exit(0);
    }

    while(1)
    {
        clilen = sizeof(clientaddr);
        memset(buf_in, 0x00, PACKET_SIZE);

        if ((client_sockfd = accept(server_sockfd, (SA *)&clientaddr, &clilen)) == -1)
        {
            perror("Accept error : ");
            close(client_sockfd);
            continue;
        }
        while(1)
        {
            if ( (n = read(client_sockfd, buf_in, PACKET_SIZE)) <= 0)
            {
                perror("read error : ");
                close(client_sockfd);
                break;
            }
            if ((par_buf = strstr(buf_in, needle)) != NULL)
            {
                printf("%s", par_buf+2);
            }

            memset(buf_in, 0x00, PACKET_SIZE);
        }
    }
}
```

이 프로그램은 소켓을 통해서 메모리 상황을 받아서 출력만 해준다.
받은 문자열을 파싱해서 그럴듯하게 보여주는건 여러분들에게 맡기도록 하겠다.
프로그램의 실행은 가장먼저 memchecker 을 실행하고 다음 memrecevier 을 실행하고, memsender을 실행하면 된다.
