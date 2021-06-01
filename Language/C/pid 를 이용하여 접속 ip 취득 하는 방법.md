# pid 를 이용하여 접속 ip 취득 하는 방법

### Linux 용 Source

```c++
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h> 
#include <unistd.h>

#ifndef __out
#define __out
#endif

#ifndef __in
#define __in
#endif

#ifndef __inout
#define __inout
#endif

typedef struct _st_conn_ips 
{
    int  pid;
    char ip[ 32 ];
} st_conn_ips, *pst_conn_ips;

// get connected ip and pid list
int get_connected_ips(__out st_conn_ips* ip_pid)
{
    FILE*           fp = NULL;
    int             state;
    char            buf[ 256 ];
    st_conn_ips*    p = ip_pid;
    int             n_cnt = 0;

    fp = popen("netstat -tapen | grep ssh | awk 'length($5) > 10 {print $5,$9}'", "r");
    if (fp == NULL)
    {
        perror("erro : ");
        exit(0);
    }

    // 192.168.xxx.xxx:9354 31291/sshd:
    while(fgets(buf, 256, fp) != NULL)
    {
        char *p_start = buf;
        char *p_pos   = buf;
        int  len      = strlen(buf);
        int  i        = 0;
        
        // find first ':'
        for(i = 0; i < len; i++, p_pos++)
        {
            if(*p_pos == ':')
            {
                memcpy(p->ip, p_start, i);
                break;
            }
        }
        
        // find space
        for( ;i < len; i++, p_pos++)
        {
            if(*p_pos == 0x20)
            {
                i++;
                p_pos++;
                p_start = p_pos;
                break;
            }               
        }
        
        // find first '/'
        for(i = 0; i < len - (p_start-buf); i++, p_pos++ )
        {
            if(*p_pos == '/')
            {
                char tmp[ 32 ];
                
                memset( tmp, 0x00, sizeof(tmp) );
                memcpy( tmp, p_start, i);
                p->pid = atoi(tmp);
                
                break;
            }
        }
        
        p++;
        n_cnt++;
    }

    pclose(fp);

    return n_cnt;
}

// get ppid list
int get_pedigree(__in int pid, __out int* pids)
{
    FILE* fp = NULL;
    int   state;
    int*  p = pids;
    int   n, count = 0;
    char  cmd[ 256 ];
    char  buf[ 256 ];
    
    n = pid;
    *p++ = n;
    count++;
    while(1)
    {
        sprintf(cmd, "ps -o ppid= %d", n );

        fp = popen(cmd, "r");
        if (fp == NULL)
        {
            perror("erro : ");
            break;
        }

        memset(buf, 0x00, sizeof(buf));
        if(fgets(buf, 256, fp) == NULL)
        {
            pclose(fp);
            break;
        }

        pclose(fp);
        
        n = atoi(buf);
        if(n <= 1)
            break;
        
        *p++ = n;
        count++;        
    }
    
    return count;
}

// return no error:0, other err value
int get_ip_from_pid(__in int pid, __out char* ip)
{
    int          arr_pids[ 32 ];
    int          pid_count = 0;
    int          conn_count = 0;
    int          i = 0, j = 0;
    int          res = 0 ;
    int          flag = 0;
    st_conn_ips  ips[ 32 ];
    pst_conn_ips pips;
    
        
    memset(ips, 0x00, sizeof(ips));
    memset(arr_pids, 0x00, sizeof(arr_pids));
    
    conn_count = get_connected_ips(ips);
    pid_count  = get_pedigree(pid, arr_pids);
    
    for( i = 0 ; i < pid_count; i++)
    {
        pips = ips;
        for(j = 0; j < conn_count; j++, pips++ )
        {
            if(arr_pids[i] == pips->pid)
            {
                memcpy(ip, pips->ip, strlen(pips->ip));
                flag = 1;
                break;
            }
        }
        
        if( flag )
            break;
    }

    if(!flag)
    {
        sprintf(ip, "%s", "127.0.0.1");
        res = -1;
    }
    
    return res;
}

int main(int argc, char** argv)
{
    int  pid = getpid();
    char ip_addr[ 32 ]; 
    
    memset(ip_addr, 0x00, sizeof(ip_addr));
    
    if( !get_ip_from_pid(pid, ip_addr) )
        printf( "pid=%d, ip=%s\n", pid, ip_addr );
    else
        printf( "Error: Not found connected ip address\n" );
}

```

### Windowd 에서 ppid 구하는 소스 (getppid.c)

```c++
#include    <windows.h>
#include    <tlhelp32.h>
#include    <stdio.h>

DWORD getppid(DWORD pid)
{
    HANDLE hSnapshot;
    PROCESSENTRY32 pe32;
    DWORD ppid = 0;

    hSnapshot = CreateToolhelp32Snapshot( TH32CS_SNAPPROCESS, 0 );
    __try{
        if( hSnapshot == INVALID_HANDLE_VALUE ) __leave;

        ZeroMemory( &pe32, sizeof( pe32 ) );
        pe32.dwSize = sizeof( pe32 );
        if( !Process32First( hSnapshot, &pe32 ) ) __leave;

        do{
            if( pe32.th32ProcessID == pid ){
                ppid = pe32.th32ParentProcessID;
                break;
            }
        }while( Process32Next( hSnapshot, &pe32 ) );

    }
    __finally{
        if( hSnapshot != INVALID_HANDLE_VALUE ) CloseHandle( hSnapshot );
    }
    return ppid;
}

int main(){
	DWORD pid = GetCurrentProcessId();

    printf( "%lx\n", getppid(pid) );

    return 0;

}
```

