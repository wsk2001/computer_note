### AIX 에서 C 컴파일시 library 를 찾지 못한다고 오류 발생시 대처법

---

AIX 에서는 Share library 파일의 확장자로 .a .so 를 모두 사용하고 있습니다.  

컴파일 옵션에서  -Wl,-brtl 이 포함되면 .so 도 shared lib로 사용하게 됩니다.   

이 옵션이 없는 경우에는 .a 파일을 찾습니다.



**Makefile 예제.**

```
CC    = gcc -maix64 -Wl,-brtl

LDFLAGS  = -L/project/api/c/lib 

CFLAGS  = -I/project/api/c/include -I.

all: TestProg

TestProg: TestProg.c
	$(CC) -o TestProg TestProg.c $(CFLAGS) $(LDFLAGS)

clean:
	rm -f TestProg.o
	rm -f TestProg
```

### - 끝 -

