# dlsym man page

출처: linux man page



DLOPEN(3) : Linux Programmer's Manual  



### NAME

dladdr, dlclose, dlerror, dlopen, dlsym, dlvsym - programming interface to dynamic linking loader



### 개요

```c++
#include <dlfcn.h>

void *dlopen(const char *filename, int flag);
char *dlerror(void);
void *dlsym(void *handle, const char *symbol);
int dlclose(void *handle);
```

- Link with -ldl.



### DESCRIPTION

dlopen (), dlsym (), dlclose (), dlerror ()의 네 가지 함수는 동적 연결 로더에 대한 인터페이스를 구현합니다.



#### dlerror()

dlerror () 함수는 dlerror ()에 대한 마지막 호출 이후 dlopen (), dlsym () 또는 dlclose ()에서 발생한 가장 최근의 오류를 설명하는 사람이 읽을 수있는 문자열을 반환합니다. 초기화 이후 또는 마지막으로 호출 된 후 오류가 발생하지 않으면 NULL을 리턴합니다.



#### dlopen()

dlopen () 함수는 null로 끝나는 문자열 파일 이름으로 명명 된 동적 라이브러리 파일을로드하고 동적 라이브러리에 대한 불투명 한 '핸들'을 반환합니다. Filename이 NULL이면 리턴 된 핸들은 기본 프로그램에 대한 것입니다. Filename에 슬래시 ( '/')가 포함 된 경우 (상대 또는 절대) 경로 이름으로 해석됩니다. 그렇지 않으면 동적 링커에서 라이브러리를 다음과 같이 검색합니다 (자세한 내용은 ld.so (8) 참조).

- (ELF 전용) 호출 프로그램의 실행 파일에 DT_RPATH 태그가 포함되어 있고 DT_RUNPATH 태그가 포함되어 있지 않으면 DT_RPATH 태그에 나열된 디렉토리가 검색됩니다.
- 프로그램이 시작될 때 환경 변수 LD_LIBRARY_PATH가 콜론으로 구분 된 디렉토리 목록을 포함하도록 정의 된 경우 검색됩니다. 보안상의 이유로이 변수는 set-user-ID 및 set-group-ID 프로그램에서는 무시됩니다.
- (ELF 전용) 호출 프로그램의 실행 파일에 DT_RUNPATH 태그가 있으면 해당 태그에 나열된 디렉토리가 검색됩니다.
- 캐시 파일 /etc/ld.so.cache(ldconfig(8)에서 유지 관리)는 파일 이름에 대한 항목이 포함되어 있는지 확인합니다.
- /lib 및 /usr/lib 디렉토리가 순서대로 검색됩니다.



라이브러리가 다른 공유 라이브러리에 종속 된 경우 동일한 규칙을 사용하여 동적 링커에 의해 자동으로로드됩니다. (이러한 라이브러리에 종속 관계가있는 경우이 프로세스는 재귀 적으로 발생할 수 있습니다.)

다음 두 값 중 하나가 플래그에 포함되어야합니다.

##### RTLD_LAZY

지연 바인딩을 수행.  심볼을 참조하는 코드 만 실행되므로 심볼 만 확인하십시오. 기호가 참조되지 않으면 절대 해석되지 않습니다. (지연 바인딩은 함수 참조에 대해서만 수행되며 변수에 대한 참조는 라이브러리가로드 될 때 항상 즉시 바인딩됩니다.)



##### RTLD_NOW

이 값이 지정되거나 환경 변수 LD_BIND_NOW가 비어 있지 않은 문자열로 설정된 경우 라이브러리에서 정의되지 않은 모든 기호는 dlopen ()이 리턴되기 전에 분석됩니다. 이 작업을 수행 할 수 없으면 오류가 반환됩니다.



다음 값 중 0 개 이상이 플래그로 OR 될 수도 있습니다.

##### RTLD_GLOBAL

이 라이브러리에서 정의한 기호는 이후에로드 된 라이브러리의 기호 분석에 사용할 수 있습니다.



##### RTLD_LOCAL

이것은 RTLD_GLOBAL의 반대이며, 플래그가 지정되지 않은 경우 기본값입니다. 이 라이브러리에 정의 된 기호는 이후로드 된 라이브러리의 참조를 해석하는 데 사용할 수 없습니다.



##### RTLD_NODELETE

dlclose () 중에 라이브러리를 언로드하지 마십시오. 결과적으로 라이브러리가 나중에 dlopen ()을 사용하여 다시로드되면 라이브러리의 정적 변수가 다시 초기화되지 않습니다. 이 플래그는 POSIX.1-2001에 지정되어 있지 않습니다.



##### RTLD_NOLOAD

라이브러리를로드하지 마십시오. 라이브러리가 이미 상주하는지 여부를 테스트하는 데 사용할 수 있습니다 (dlopen ()이 없으면 NULL을 리턴하거나 상주하는 경우 라이브러리 핸들을 리턴 함). 이 플래그는 또한 이미로드 된 라이브러리에서 플래그를 승격시키는 데 사용될 수 있습니다. 예를 들어, RTLD_LOCAL로 이전에로드 된 라이브러리는 RTLD_NOLOAD | RTLD_GLOBAL. 이 플래그는 POSIX.1-2001에 지정되어 있지 않습니다.



##### RTLD_DEEPBIND

이 라이브러리에서 기호의 검색 범위를 전역 범위보다 먼저 배치하십시오. 즉, 자체 포함 된 라이브러리는 이미로드 된 라이브러리에 포함 된 동일한 이름을 가진 전역 기호보다 고유 한 기호를 사용합니다. 이 플래그는 POSIX.1-2001에 지정되어 있지 않습니다.



Filename이 NULL 포인터이면 반환 된 핸들은 기본 프로그램을위한 것입니다. dlsym ()이 주어지면 이 핸들은 기본 프로그램에서 기호를 찾은 다음 프로그램 시작시로드 된 모든 공유 라이브러리와 RTDL_GLOBAL 플래그로 dlopen ()에 의해로드 된 모든 공유 라이브러리를 검색합니다.



라이브러리의 외부 참조는 해당 라이브러리의 종속성 목록에있는 라이브러리 및 RTLD_GLOBAL 플래그로 이전에 열린 다른 라이브러리를 사용하여 분석됩니다. 실행 파일이 '-rdynamic'플래그 (또는 동의어 : '--export-dynamic')와 연결되어 있으면 실행 파일의 전역 심볼을 사용하여 동적으로로드 된 라이브러리에서 참조를 확인합니다.



동일한 라이브러리가 dlopen ()으로 다시로드되면 동일한 파일 핸들이 리턴됩니다. dl 라이브러리는 라이브러리 핸들에 대한 참조 횟수를 유지하므로 dlopen ()이 성공한 횟수만큼 dlclose ()가 호출 될 때까지 동적 라이브러리의 할당이 해제되지 않습니다. _init () 루틴이있는 경우 한 번만 호출됩니다. 그러나 RTLD_NOW를 사용한 후속 호출은 RTLD_LAZY로 이전에로드 된 라이브러리에 대한 심볼 분석을 강제 할 수 있습니다.



어떤 이유로 든 dlopen ()이 실패하면 NULL을 반환합니다.



#### dlsym()

dlsym () 함수는 dlopen ()에서 리턴 한 동적 라이브러리와 널로 끝나는 심볼 이름의 '핸들'을 가져 와서 해당 심볼이 메모리에로드 된 주소를 리턴합니다.
지정된 라이브러리 또는 라이브러리를로드 할 때 dlopen ()에 의해 자동으로로드 된 라이브러리에서 기호를 찾을 수없는 경우 dlsym ()은 NULL을 리턴합니다.
(dlsym ()으로 수행 된 검색은 이러한 라이브러리의 종속성 트리를 통해 가장 넓습니다.)
기호 값이 실제로 NULL 일 수 있으므로 (dlsym ()에서 NULL 리턴이 오류를 나타낼 필요가 없음) 오류를 테스트하는 올바른 방법은 dlerror ()를 호출하여 이전 오류 조건을 지우고 호출하는 것입니다. dlsym ()을 반환 한 다음 반환 값을 변수에 저장하고 dlerror ()를 다시 호출하고이 저장된 값이 NULL이 아닌지 확인합니다.

RTLD_DEFAULT 및 RTLD_NEXT라는 두 가지 특수 의사 처리가 있습니다. 전자는 기본 라이브러리 검색 순서를 사용하여 원하는 기호가 처음 나타나는 것을 찾습니다. 후자는 현재 라이브러리 다음에 검색 순서에서 다음에 나타나는 함수를 찾습니다. 이를 통해 다른 공유 라이브러리의 함수 주위에 랩퍼를 제공 할 수 있습니다.

#### dlclose()

dlclose () 함수는 동적 라이브러리 핸들 핸들의 참조 횟수를 감소시킵니다. 참조 카운트가 0으로 떨어지고로드 된 다른 라이브러리에서 기호를 사용하지 않으면 동적 라이브러리가 언로드됩니다.

dlclose () 함수는 성공하면 0을, 에러는 0이 아닌 값을 반환합니다.



#### 더 이상 사용되지 않는 기호 _init () 및 _fini ()

링커는 특수 기호 _init 및 _fini를 인식합니다. 동적 라이브러리가 _init ()라는 루틴을 내 보내면로드 후 dlopen ()이 리턴되기 전에 해당 코드가 실행됩니다. 동적 라이브러리가 _fini ()라는 루틴을 내 보내면 라이브러리가 언로드되기 직전에 해당 루틴이 호출됩니다. 시스템 시작 파일에 대한 링크를 피해야하는 경우 gcc (1) -nostartfiles 명령 행 옵션을 사용하여 수행 할 수 있습니다.

이러한 루틴 또는 gcc -nostartfiles 또는 -nostdlib 옵션을 사용하지 않는 것이 좋습니다. 생성자 / 소멸자 루틴이 실행되지 않으므로 (특별 조치를 취하지 않는 한) 사용하면 원하지 않는 동작이 발생할 수 있습니다.

대신 라이브러리는 `__attribute __` ((constructor)) 및 `__attribute __` ((destructor)) 함수 속성을 사용하여 루틴을 내 보내야합니다. 이에 대한 정보는 gcc 정보 페이지를 참조하십시오. 생성자 루틴은 dlopen ()이 리턴되기 전에 실행되고 소멸자 루틴은 dlclose ()가 리턴되기 전에 실행됩니다.



### Glibc 확장 : dladdr () 및 dlvsym ()

Libc는 POSIX에서 설명하지 않은 두 가지 기능을 프로토 타입과 함께 추가합니다.

```c++
#define _GNU_SOURCE         /* See feature_test_macros(7) */
#include <dlfcn.h>

int dladdr(void *addr, Dl_info *info);

void *dlvsym(void *handle, char *symbol, char *version);
```

dladdr () 함수는 함수 포인터를 가져와 이름과 파일이있는 위치를 확인하려고합니다. 정보는 Dl_info 구조에 저장됩니다.

```c++
typedef struct {
    const char *dli_fname;  /* Pathname of shared object that
                               contains address */
    void       *dli_fbase;  /* Address at which shared object
                               is loaded */
    const char *dli_sname;  /* Name of nearest symbol with address
                               lower than addr */
    void       *dli_saddr;  /* Exact address of symbol named
                               in dli_sname */
} Dl_info;
```

기호 일치 addr을 찾을 수 없으면 dli_sname 및 dli_saddr이 NULL로 설정됩니다.

dladdr ()은 오류시 0을, 성공시 0이 아닌 값을 리턴합니다.

버전 2.1 이후 glibc에서 제공하는 dlvsym () 함수는 dlsym ()과 동일하지만 추가 인수로 버전 문자열을 사용합니다.



#### CONFORMING TO

POSIX.1-2001은 dlclose (), dlerror (), dlopen () 및 dlsym ()을 설명합니다.



#### NOTES

RTLD_DEFAULT 및 RTLD_NEXT 기호는 포함하기 전에 _GNU_SOURCE가 정의 된 경우에만 <dlfcn.h>로 정의됩니다.

glibc 2.2.3부터 atexit (3)을 사용하여 라이브러리가 언로드 될 때 자동으로 호출되는 종료 핸들러를 등록 할 수 있습니다.

dlopen 인터페이스 표준은 SunOS에서 제공합니다. 이 시스템에는 dladdr ()이 있지만 dlvsym ()은 없습니다.



#### BUGS

때로는 dladdr ()에 전달하는 함수 포인터가 놀라 울 수도 있습니다. 일부 아키텍처 (특히 i386 및 x86_64)에서는 인수로 사용 된 함수가 동적으로 링크 된 라이브러리에서 가져와야하더라도 dli_fname 및 dli_fbase가 dladdr ()을 호출 한 객체를 다시 가리킬 수 있습니다.

문제는 함수 포인터가 컴파일 타임에 여전히 해결되지만 원래 객체의 plt (Procedure Linkage Table) 섹션을 가리 키는 것입니다 (동적 링커에게 심볼을 확인하도록 요청 한 후 호출을 전달합니다). 이 문제를 해결하기 위해 코드를 위치 독립적으로 컴파일하려고 시도 할 수 있습니다. 그러면 컴파일러는 더 이상 컴파일 타임에 포인터를 준비 할 수 없으며 오늘의 gcc (1)은 최종 심볼 주소를로드 한 코드를 생성합니다 ( Global Offset Table)을 런타임에 dladdr ()에 전달하기 전에



#### EXAMPLE

수학 라이브러리를로드하고 코사인 2.0을 인쇄합니다.

```c++
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

int
main(int argc, char **argv)
{
    void *handle;
    double (*cosine)(double);
    char *error;

    handle = dlopen("libm.so", RTLD_LAZY);
    if (!handle) {
        fprintf(stderr, "%s\n", dlerror());
        exit(EXIT_FAILURE);
    }

    dlerror();    /* Clear any existing error */

    /* Writing: cosine = (double (*)(double)) dlsym(handle, "cos");
       would seem more natural, but the C99 standard leaves
       casting from "void *" to a function pointer undefined.
       The assignment used below is the POSIX.1-2003 (Technical
       Corrigendum 1) workaround; see the Rationale for the
       POSIX specification of dlsym(). */

    *(void **) (&cosine) = dlsym(handle, "cos");

    if ((error = dlerror()) != NULL)  {
        fprintf(stderr, "%s\n", error);
        exit(EXIT_FAILURE);
    }

    printf("%f\n", (*cosine)(2.0));
    dlclose(handle);
    exit(EXIT_SUCCESS);
}

```

이 프로그램이 'foo.c'라는 파일에 있으면 다음 명령을 사용하여 프로그램을 빌드하십시오.

```bash
gcc -rdynamic -o foo foo.c -ldl
```



_init () 및 _fini ()를 내보내는 라이브러리는 예제 이름으로 bar.c를 사용하여 다음과 같이 컴파일하려고합니다.

```c++
gcc -shared -nostartfiles -o bar bar.c
```



#### 끝

