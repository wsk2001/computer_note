### Makefile 에서 Shell 사용하기



Makefile 에서 Shell 명령을 사용 할 수 있다.

다음은 Makefile 을 이용하여 현재 directory 에 있는 *.c 파일을 컴파일 하여 *.o 파일을 생성 하는

예제 이다.

`Makefile`

```
SOURCES = $(shell ls *.c)
OBJECTS = $(SOURCES:.c=.o)

all: $(OBJECTS)

.c.o:
	${CC} -c $<

clean:
	rm -rf $(OBJECTS)
```

 잘 사용하면 편리하게 작업 할 수 있을듯.