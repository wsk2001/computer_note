### Makefile 여러 경로에 있는 소스 일괄 Build

프로젝트를 진행 하다보면 소스들을 여러 경로에 두고 필요시 컴파일을 하게 된다.

lib(*.dll, *.a, *.so) 를 만들거나 필요한 TEST Tool 들을 만들거나 하면 별도의 directory 에 소스를 두고 관리를 하게 된다.

그리고 각각의 directory 에는 Makefile 을 만들어 두고 해당 directory 로 이동하여 compile 을 수행 한다.

그러다 보면 종속성 문제로 일부 binary 에는 수정된 소스의 코드가 반영이 되지 않은 상태로 build 되어 배포가 되는 경우가 발생 할 수 있으므로 일괄 빌드를 할 수 있는 방법을 적용 하여야 한다.

이 방법을 적용 하기 이전에는 compile_all.sh 또는 compile_all.bat 등 스크립트를 이용하여 컴파일 하거나, 별도의 build 프로그램을 만들어서 사용 한 적도 있다.

다음의 Makefile 예제는 여러 경로에 Makefile 이 있고 일괄 빌드를 해야 할 경우 사용 하는 예제 이다.

주의할 점은 각각 의 sub directory 에서 make 에 의한 build 가 정상적으로 수행 되어야 하며, static lib 인 경우는 종속성을 확실히 하기 위해 make clean all 로 하는 방법이 일반적 이다.

아니면 각각의 directory 에 있는 Makefile 에서 lib 종속성을 check 하도록 하면 된다.

```
MAKE = make

SUB_DIRS = Migrator/src \
    Scheduler/src \
    Thread/MigTh \
    Thread/ScheTh

all : subdirs

subdirs :
    @for dir in $(SUB_DIRS); do \
        $(MAKE) -C $$dir all; \
        if [ $$? != 0 ]; then exit 1; fi; \
    done


clean :
    @for dir in $(SUB_DIRS); do \
        $(MAKE) -C $$dir clean; \
        if [ $$? != 0 ]; then exit 1; fi; \
    done

```

위의 예제는 4개의 sub directory 가 있고 해당 directory 에 각각 Makefile 이 있는 경우 일괄 Build 를 하기 위한 Makefile 이다.

빌드 순서는 SUB_DIRS 에 기록한 순서로 진행 된다.

