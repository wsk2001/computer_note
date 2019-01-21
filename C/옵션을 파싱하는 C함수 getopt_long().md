# 옵션을 파싱하는 C함수  getopt_long()

출처: https://soooprmx.com/archives/4991

## 긴 이름 옵션 파싱함수

`getopt()` 함수는 비교적 사용이 쉬운데, 보통 명령행 유틸리티들은 옵션을 보다 명확하게 인지할 수 있는 긴 이름의 옵션들을 지원한다. 예를 들어 출력파일을 결정할 때 `-o outfile`로 쓰는 대신 `--output outfile`로 쓰게끔 허용하는 것이다. 이 기능은 `getopt_long()`이라는 함수를 통해 지원할 수 있다. 긴 이름 옵션은 하나의 문자열에 망라하기 어려우므로 별도의 구조체를 사용하여 옵션 정보를 마련해두고 이 구조체의 배열을 사용하는 방식으로 처리한다. 그러면서 인터페이스는 좀 달라지지만, 사용하는 방식은 거의 유사하다고 보면 된다.



## option 구조체

각각의 옵션 정보를 담고 있는 구조체가 필요하다. 이 구조체는 다음의 정보를 포함한다.

- `const char * name` : 옵션의 이름
- `int has_arg` : 옵션이 추가적인 파라미터를 갖는지 여부에 대한 플래그. 이는 따로 정의된 상수 `no_arguement`, `required_arguement`, `optional_argument`를 쓴다.
- `int *flag` : 옵션 값이 저장될 위치
- `int val` : 옵션값

## getopt_long()

이 함수의 원형은 다음과 같다.

```
int getopt_long(
    int argc, 
    char * const *argv, 
    const char *shortopts, 
    const struct option* longopts,
    int *indexptr
    );
```

먼저 이 함수는 짧은 옵션을 만난 경우, `getopt()`와 동일한 동작을 하면서 파싱된 옵션 값을 리턴한다. 그리고 긴 옵션을 만나는 경우에는 `option` 구조체의 정의에 따라서 다음과 같은 동작을 한다. 이 때 가장 중요한 구조체의 멤버는 `flag`와 `val`이다.

`flag`가 널포인터이면, `getopt_long()` 함수는 `val` 값을 리턴한다. 플래그가 널포인터가 아니면 플래그값이 `val`로 세팅된다. 그리고 이 경우 함수는 0을 리턴하게 된다. `*indexptr`에는 구조체 배열 중 몇 번째 option의 값이 검출되었는지를 알려주기 위해서 이 값이 함수 내에서 업데이트된다.

## 구현 전략

구현 전략은 다음과 같다.

1. 짧은 옵션들을 먼저 정의한다.
2. 긴 옵션명을 추가로 정의한다.
3. 긴 옵션명 중에서 짧은 옵션명과 동일한 옵션인 것과 그렇지 않은 것을 구분한다.
4. 짧은 옵션과 다른 긴 옵션의 경우 결과를 저장할 flag가 될 변수를 미리 준비한다.
5. 1~4의 내용으로 옵션 구조체 배열을 정의한다. 이 배열은 `{0, 0, 0, 0}`의 센티넬로 마무리한다.
6. `getopt_long()` 함수를 루프 속에서 처리한다.
7. -1이 리턴될 때까지 반복한다.

## 샘플코드

```
#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>

/* Flag set by ‘--verbose’. */
static int verbose_flag;

int main (int argc, char **argv)
{
    int c;

    while (1)
    {
        static struct option long_options[] =
        {
            // --verbose 옵션을 만나면 "verbose_flag = 1"이 세팅된다.
            {"verbose", no_argument,       &verbose_flag, 1},
            {"brief",   no_argument,       &verbose_flag, 0},
            // 이 아래로는 짧은 옵션과 동일한 옵션들
            // 예를 들어 --add 는 -a와 동일한 동작을 한다.
            {"add",     no_argument,       0, 'a'},
            // 널포인터의 val이 'a'이면 'a'를 리턴할 것이다.
            {"append",  no_argument,       0, 'b'},
            {"delete",  required_argument, 0, 'd'},
            {"create",  required_argument, 0, 'c'},
            {"file",    required_argument, 0, 'f'},
            {0, 0, 0, 0} // 옵션 배열은 {0,0,0,0} 센티넬에 의해 만료된다.
        };

        // !!! 옵션 인덱스를 저장하는 변수
        int option_index = 0;

        // 옵션 파싱
        c = getopt_long (argc, argv, "abc:d:f:",
                long_options, &option_index);

        // getopt()와 동일하게 모든 옵션 파싱을 마치면 -1을 리턴합니다.
        if (c == -1)
            break;

        switch (c)
        {
            case 0:
                // 긴 이름 옵션을 만났을 때
                if (long_options[option_index].flag != 0)
                    break;
                printf ("option %s", long_options[option_index].name);
                if (optarg)
                    printf (" with arg %s", optarg);
                printf ("\n");
                break;

            // 이하는 짧은 옵션과 동일한 긴 이름 옵션을 만났을 때
            case 'a':
                // `-a`를 만났거나 `--add`를 만났다.
                puts ("option -a\n");
                break;

            case 'b':
                puts ("option -b\n");
                break;

            case 'c':
                printf ("option -c with value `%s'\n", optarg);
                break;

            case 'd':
                // `--delete`, `-d`를 만난 경우, 추가 옵션이 필요하며 이는 `optarg`에 저장되어 있다.
                printf ("using short option.\n");
                printf ("option -d with value `%s'\n", optarg);
                break;

            case 'f':
                printf ("option -f with value `%s'\n", optarg);
                break;

            case '?':
                /* getopt_long already printed an error message. */
                break;

            default:
                abort ();
        }
    }

    // verbose_flag가 1로 설정되었을 때(--verbose)
    if (verbose_flag)
        puts ("verbose flag is set");

    /* Print any remaining command line arguments (not options). */
    if (optind < argc)
    {
        printf ("non-option ARGV-elements: ");
        while (optind < argc)
            printf ("%s ", argv[optind++]);
        putchar ('\n');
    }

    exit (0);
}
```


  