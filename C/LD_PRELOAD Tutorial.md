# LD_PRELOAD Tutorial

출처: https://catonmat.net/simple-ld-preload-tutorial

##### LD_PRELOAD 에 등록하는 함수는 static 함수가 아닌 dynamic 함수만 사용 할 것을 권합니다.
##### 그렇지 않을 경우 오류(core dump) 가 발생 할 수 있습니다.  예: stat() 함수 후킹




모든 프로그램에서 printf 및 fopen과 같은 C 표준 라이브러리 함수를 사용자 정의 함수로 대체 할 수 있다는 것을 알고 있습니까? 이 짧은 기사에서는 LD_PRELOAD 환경 변수를 통해 이를 수행하는 방법을 설명합니다.

간단한 C 프로그램 (prog.c)으로 시작해 봅시다 :

```c
#include <stdio.h>

int main(void) {
    printf("Calling the fopen() function...\n");

    FILE *fd = fopen("test.txt","r");
    if (!fd) {
        printf("fopen() returned NULL\n");
        return 1;
    }

    printf("fopen() succeeded\n");

    return 0;
}
```

위의 코드는 단순히 표준 fopen 함수를 호출 한 다음 반환 값을 확인합니다. 

이제 컴파일하고 실행 해 봅시다 :

```bash
$ ls
prog.c  test.txt

$ gcc prog.c -o prog

$ ls
prog  prog.c  test.txt

$ ./prog
Calling the fopen() function...
fopen() succeeded
```

보시다시피, fopen 호출은 성공적이었습니다.

이제 항상 실패하는 자체 fopen 버전을 작성하고이 파일을 myfopen.c로 호출 해 보겠습니다.

```c
#include <stdio.h>

FILE *fopen(const char *path, const char *mode) {
    printf("Always failing fopen\n");
    return NULL;
}
```

myfopen.so라는 공유 라이브러리로 컴파일 해 봅시다 :

```bash
gcc -Wall -fPIC -shared -o myfopen.so myfopen.c
```

이제 이전에 만든 프로그램을 실행하기 전에 LD_PRELOAD 환경 변수를 myfopen.so 공유 라이브러리로 설정하면 다음과 같은 출력이 나타납니다.

```bash
$ LD_PRELOAD=./myfopen.so ./prog
Calling the fopen() function...
Always failing fopen
fopen() returned NULL
```

보시다시피 fopen은 항상 실패하는 자체 버전으로 바뀌 었습니다.

이것은 자신이 작성하지 않은 프로그램이나 라이브러리의 특정 부분을 디버그하거나 교체해야 할 때 정말 유용합니다.

LD_PRELOAD로 할 수있는 몇 가지 트릭이 더 있으며 다음에 그에 대해 쓸 것입니다. 계속 지켜봐주세요! (업데이트 : https://catonmat.net/simple-ld-preload-tutorial-part-two)



---

출처: https://catonmat.net/simple-ld-preload-tutorial-part-two

처음 예제는 공유 라이브러리를 만들고 LD_PRELOAD 환경 변수를 통해 preload 하여 공유 라이브러리의 함수를 재정의하는 방법을 보여주었습니다. 

오늘은 재정의 된 함수에서 원래 함수를 호출하는 방법을 보여 드리겠습니다.

먼저 이전 기사에서 사용한 코드 예제를 살펴 보겠습니다. 우리는 fopen을 사용한 prog.c라는 프로그램을 가지고있었습니다 :

```c
#include <stdio.h>

int main(void) {
    printf("Calling the fopen() function...\n");

    FILE *fd = fopen("test.txt", "r");
    if (!fd) {
        printf("fopen() returned NULL\n");
        return 1;
    }

    printf("fopen() succeeded\n");

    return 0;
}
```

이제 prog.c에서 fopen을 무시하고 C 표준 라이브러리에 있는 원래 fopen을 호출하는 myfopen.c라는 공유 라이브러리를 작성해 보겠습니다.

```c
#define _GNU_SOURCE

#include <stdio.h>
#include <dlfcn.h>

FILE *fopen(const char *path, const char *mode) {
    printf("In our own fopen, opening %s\n", path);

    FILE *(*original_fopen)(const char*, const char*);
    original_fopen = dlsym(RTLD_NEXT, "fopen");
    return (*original_fopen)(path, mode);
}
```

이 공유 라이브러리는 printf 함수를 이용하여 경로(path) 를 출력 한 다음 RTLD_NEXT 의사 핸들과 함께 dlsym을 사용하여 원래 fopen 함수를 찾습니다.

원래 함수를 사용하기 위해 

```c
FILE *(*original_fopen)(const char*, const char*);
```

를 이용해 함수 포인트를 정의 한 부분과, dlsym() 함수를 이용하여 원래의 함수를 찾아오는 부분을 주의깊게 보시기 바랍니다.

dlfcn.h에서 RTLD_NEXT 정의를 가져 오려면 _GNU_SOURCE 기능 매크로를 정의해야합니다. RTLD_NEXT는 현재 라이브러리 다음에 검색 순서에서 다음에 나타나는 함수를 찾습니다.

이 공유 라이브러리를 다음과 같이 컴파일 할 수 있습니다 :

```bash
gcc -Wall -fPIC -shared -o myfopen.so myfopen.c -ldl
또는
gcc -Wall -fPIC -DMACHINE64 -D_FILE_OFFSET_BITS=64 -shared -o myfopen.so myfopen.c -ldl

```

PRE LOAD 하고 prog를 실행하면 다음과 같은 결과가 나타납니다.

```bash
$ LD_PRELOAD=./myfopen.so ./prog
Calling the fopen() function...
In our own fopen, opening test.txt
fopen() succeeded
```

우리가 여는 파일의 파일 이름을 인쇄하고 성공적으로 파일을 엽니다.

프로그램의 일부가 작동하는 방식을 변경하거나 고급 디버깅을 수행해야하는 경우에 유용합니다.

다음에는 LD_PRELOAD가 어떻게 구현되는지 살펴 보겠습니다. 계속 지켜봐주세요!



### 테스트 소스

```c
#define _GNU_SOURCE

#include <stdio.h>
#include <dlfcn.h>

// gcc -Wall -fPIC -shared -o tss_acl.so tss_acl.c -ldl

FILE *fopen(const char *path, const char *mode) 
{
    printf("In our own fopen, opening %s\n", path);

    FILE *(*org_fopen)(const char*, const char*);
    org_fopen = dlsym(RTLD_NEXT, "fopen");
    return (*org_fopen)(path, mode);
}

FILE *popen(const char *path, const char *mode) 
{
    printf("In our own popen, opening %s\n", path);

    FILE *(*org_popen)(const char*, const char*);
    org_popen = dlsym(RTLD_NEXT, "popen");
    return (*org_popen)(path, mode);
}

int open(const char *path, int flags)
{
    printf("In our own open, opening %s\n", path);

    int (*org_open)(const char*, int);
    org_open = dlsym(RTLD_NEXT, "open");
    return (org_open)(path, flags);
}

```



## <span style="color:red">How to reset LD_PRELOAD</span>

```bash
unset LD_PRELOAD
```





## LD_PRELOAD를 이용한 so injection과 hooking. + wrapping function

출처: https://umbum.dev/128

`LD_PRELOAD`는 prefix로 `LD_`가 붙은, [ld.so](http://man7.org/linux/man-pages/man8/ld.so.8.html#ENVIRONMENT)에 속하는 환경변수로, windows의 `AppInit_Dlls` 레지스트리와 비슷한 역할을 한다.

`LD_PRELOAD`**에 설정된 shared object는 libc를 비롯한 다른 모든 shared object보다 먼저 로딩된다.**

`LD_PRELOAD`에 설정된 shared library의 함수 중에 이후 로딩된 libc의 함수 이름과 동일한 함수가 있다면 먼저 로딩된( = `LD_PRELOAD`에 설정된) shared library의 함수를 호출하게 된다. 그래서 결과적으로 보면 자동으로 후킹을 수행하는 것과 같다.



- **타인 소유 파일에도 동작한다.**

- [secure-execution mode](http://umbum.tistory.com/entry/secure-execution-mode)로 실행되면 제약이 걸린다.

- overloading을 따지지 않는다. 즉 파라미터, 리턴 타입 상관 없이 이름만 일치하면 후킹한다.

- main은 후킹이 안된다

- `LD_PRELOAD`에 등록할 library에 대해 읽기 권한이 있어야 한다.

- `LD_PRELOAD`에 등록한 library가 위치한 path가 `LD_LIBRARY_PATH`나 `/etc/ld.so.conf`에 등록되어 있을 필요는 없다.

- <span style="color:red">``LD_LIBRARY_PATH``나 ``/etc/ld.so.conf``에 등록된 디렉토리에 위치한 library일 경우 library 파일 이름만 적어도 인식한다.</span>

- `/etc/ld.so.conf`에는 include ld.so.conf.d/*.conf 로 등록되어 있고 conf 파일 하나를 작성 하고 경로를 지정 하면 된다.

  



<library path에 위치한 library일 경우 그냥 filename만 적어도 인식한다.>

<실행하려는 파일이 타인 소유 파일이고, setUID가 걸려있는 경우(secure-execution mode) library를 찾을 수 없을 때의 오류메시지와 동일한 오류가 나온다.>



### Usage

#### 특정 바이너리 실행시만 환경변수로 지정해 바로 사용할 경우 :

```
LD_PRELOAD=pathname ./binary
```



#### 쉘에 등록한 다음 사용할 경우 ( 어떤 바이너리든 실행시 마다 LD_PRELOAD 동작 ) :

```
export LD_PRELOAD=pathname
```



#### /etc/ld.so.preload에 삽입하는 경우

ld.so.preload 파일이 없으면 만들어주면 된다. 계정 상관 없이 시스템 전역적으로 후킹된다.

예: /etc/ld.so.preload )

```ini
/home/tssuser/workspace/acl/tss_acl.so
```





#### 명령어에도 적용된다.

이를 이용해 실행할 바이너리가 `LD_PRELOAD`로 넘긴 library를 로딩하는지 확인할 수 있다.

```
LD_PRELOAD=pathname ldd ./binary
```



\* 명령어도 `/bin`같은 command path에 위치한 파일이기 때문에, `ldd`에도 `LD_PRELOAD`가 적용되어 library를 로딩하게 된다. `constructor` 등이 있는 경우 `ldd`에서 `constructor`가 실행된다. binary에 setUID가 걸려있어도 명령어에 적용된 `constructor`는 실행되므로 이를 보고 setUID 걸려있어도 `LD_PRELOAD`가 적용된다고 생각해서는 안된다.

\* binary를 `fork` 등으로 실행하게 되는 명령어라면 명령어 자체와 `fork`로 실행된 자식프로세스 모두가 library를 로딩한다.



### hooking

`LD_PRELOAD`로 프로세스 실행 시 내가 원하는 library를 로딩하도록 할 수는 있는데, library를 로딩하는 것과 library 내의 함수를 호출하는 것은 별개다. 

프로세스가 내가 원하는 library 내의 함수를 호출하도록 하려면, GCC attribute로 library를 로딩하면서 자동으로 호출하는 함수를 만들거나, 프로세스가 원래 호출하는 함수를 hooking해야한다.



#### GCC attribute

[2017/01/13 - [System/LINUX & UNIX\] - 생성자, 소멸자 / GCC __attribute__((constructor / destructor))](http://umbum.tistory.com/146)



hooking은 다음과 같이 진행한다.

1. `nm -D`로 심볼을 출력해 해당 프로그램이 사용하는 함수 이름을 얻는다.
2. user_library에 hooking 타겟 함수 이름과 동일한 함수를 만든다.
3. `LD_PRELOAD`로 대상 프로세스가 library를 로딩하도록 하면, 대상 프로세스가 hooking 타겟 함수를 호출할 때 마다 user_library의 함수가 대신 호출된다.
4. 추가) user_library의 함수(wrapper function)가 실행된 다음 원래 함수(hooking 타겟 함수)가 호출되어야 하는 경우, `dlsym(RTLD_NEXT, ...)`를 사용한다.

\* 프로그램 실행 시 기본적으로 사용되는 libc를 hooking 할 경우 4번 과정을 수행하지 않으면 제대로 동작하지 않는 경우가 대부분이다.

\* hooking 함수에서 `dlsym(RTLD_NEXT,... )`을 이용해 원래 함수 포인터를 얻은 뒤 `return orig_func` 하면 된다.

  ( `RTLD_NEXT`를 주면 symbol과 일치하는 다음 함수의 포인터를 반환한다. )

\* `dlsym()`을 이용하기 위해 `#include `해야 하며 library를 컴파일할 때 `-ldl` 옵션을 줘야 한다.

\* `RTLD_NEXT`는 `dlfcn.h`에 있는게 아니라 `#define _GNU_SOURCE` 해야 정의된다. 



#### e.g.

`getchar()`를 hooking해서 wrapping function이 동작하도록 했다.

wrapping function은 `"hooked getchar!"` 문자열을 출력한 다음 original function을 호출한다.



#### libhook.so :

```c
#include <dlfcn.h>
#define _GNU_SOURCE
 
typedef int (*orig_getchar_ftype)(void);
 
int getchar(){
    orig_getchar_ftype orig_getchar;
    orig_getchar = (orig_getchar_ftype)dlsym(RTLD_NEXT, "getchar");
    puts("hooked getchar!");
    return orig_getchar();
}
```



#### test :

```c
#include <stdio.h>
 
int main(){
    getchar();
    return 0;
}
```



#### LD_PRELOAD를 이용해 자식 프로세스를 생성하는 생성자, 소멸자를 포함하는 .so를 로딩하는 경우?

일반적으로 사용할 경우 `constructor` 안에 `system`이나 `fork`가 있어도 전혀 문제가 되지 않는다.

자식 프로세스를 만들면서 현재 프로세스의 상태를 복사하기 때문에 `constructor` 내부의 `system`이나 `fork`가 다시 실행되지 않기 때문.

그러나 환경변수는 parent의 환경변수를 child가 물려 받으면서 재적용 되는 것 같다. `LD_PRELOAD`가 자식 프로세스에서도 동작한다.



그래서 `LD_PRELOAD`를 사용해서 library를 로딩할 때, **constructor 또는 destructor로 지정한 함수 내에 system 혹은 fork 등이 있을 경우** 자식으로 생성된 프로세스도 `LD_PRELOAD`가 적용되어 이 library를 로딩하게 되므로 연쇄적으로 `system` 혹은 `fork`를 호출하게 된다.

`exec` 계열의 경우는 동작하지 않는 것 같다.



위 경우는 로딩할 library 내의 `constructor`가 `system, fork` 하게 되는 경우로, **실행할 프로세스가 system, fork하는 경우는 상관없다.** 이 경우 당연히 연쇄적인 child 생성은 일어나지 않지만, 자식 프로세스도 `LD_PRELOAD`가 적용되기 때문에, 이 library를 로딩하게 되어 `constructor` 또는 `destructor`를 호출하게 된다는 점은 유의.

#### 참고

http://optumsoft.com/dangers-of-using-dlsym-with-rtld_next/

``` c
* malloc(), free()를 wapping하면서 dlsym(RTLD_NEXT, ...)를 사용할 때 주의해야 할 점. 

RTLD_NEXT는 순서대로 NEXT 함수를 반환하므로 wapper function이 있는 .so보다 libc.so가 뒤에 있어야 RTLD_NEXT로 libc.so의 함수를 반환받을 수 있다.(ldd로 확인 가능) 만약 wapper function의 .so보다 libc.so가 앞에 있다면, libc.so의 원래 malloc()과 func()를 건너 뛰고 ld-linux...so의 malloc()과 free()를 반환하게 된다. 이 경우 ld-linux...so 자신이 또 다른 malloc(), free()를 가지고 있기 때문에 memory leak이 발생할 수 있다.
```





## linux 전역 환경 변수 설정

환경 변수가 전체 시스템에서 전역 적으로 유지되어야하는 경우 시스템의 모든 사용자가 로드 한 기본 프로필로 환경 변수를 설정할 수 있습니다.

모든 전역 프로필 설정은 /etc/profile 에 저장됩니다. 이 파일은 디렉토리를 편집 할 수 있지만 실제로는 /etc/profile.d 디렉토리에 전역 환경 변수를 저장하는 것이 좋습니다. 여기서 전체 시스템에 대한 환경 변수를 설정하는 데 사용되는 파일 목록을 찾을 수 있습니다.

- /etc/profile.d 아래에 새 파일을 작성하여 전역 환경 변수를 저장하십시오. 다른 사람들이 그 목적을 이해할 수 있도록 상황에 맞는 이름을 사용해야합니다.
  sudo touch /etc/profile.d/http_proxy.sh
- 텍스트 편집기에서 기본 프로파일을여십시오.
  sudo vi /etc/profile.d/http_proxy.sh
- 환경 변수를 내보낼 세 줄 추가
  export HTTP_PROXY=http : //my.proxy : 8080 내보내기
  export HTTPS_PROXY=https : //my.proxy : 8080 내보내기
  export NO_PROXY= localhost, :: 1, .example.com
- 변경 사항을 저장하고 텍스트 편집기를 종료하십시오.



`예) /etc/profile.d/tss_acl.sh`

```bash
export MOUNT_POINT=/home/tssuser/xdbtss/mnt
```



