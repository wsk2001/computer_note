### Infer 설치 및 사용 방법

infer 란 Facebook 에서 개발 하여 공개한 Java, C/CPP, Objective-C 의 소스를 분석 하는 정적 분석 도구이다.

소스 까지 공개를 하였는데,  개발 언어가 `OCaml ` 이라고 한다.

아직 `OCaml` 이라는 언어는 별 관심이 없지만 소스 정적 분석 도구는 관심이 있어서 설치 및 간단한 사용 후기를 올린다.  결론 부터 이야기 하면 가끔 필요 할 때 사용 예정 이다.



### 지원 운영체계

현재(2018.11.15) infer 는 Linux 와 MacOSX 만 지원 하는 것 으로 되어 있다.

점점 Unix 를 지원 하지 않는 Software 들이 늘어 나는것 으로 봐서 Unix 의 자리를 빠르게 Linux 가 차지 하는것 으로 보인다.



### 설치

본 문서 에서는 Linux(Ubuntu 16.04.5 x64) 설치 하는것을 기준 으로 작성 한다.

그리고 현재 최종 Version 은 `0.15.0` 이므로 해당 버전을 기준으로 작성 한다.

`설치 명령어`

```
VERSION=0.15.0; \
curl -sSL "https://github.com/facebook/infer/releases/download/v$VERSION/infer-linux64-v$VERSION.tar.xz" \
| sudo tar -C /opt -xJ && \
sudo ln -s "/opt/infer-linux64-v$VERSION/bin/infer" /usr/local/bin/infer
```

- command line 에서 위 명령을 입력 하면 설치가 완료 된다.
- 사전에 `curl` 이 설치 되어 있어야 한다. 
- 잠시(2~3분, 내 VMware 에서 돌아가는 Ubuntu 기준) 기다리면 설치가 완료 되고 infer 를 사용 할 수 있다.



### 사용 방법

내가 사용 하는 주 개발 언어가 C/C++ 이기 때문에 C/C++ 을 기준으로 작성 하고 java 및 Objective-C 관련 해서는 아래 `Contents` 를 참조 한다.

 `Contents`  는 infer 소스에 포함되어 있는 `examples` 아래의 `README.md` 에 있는 내용이다.



### C/C++ 에서 사용 하는 방법 1

```
infer -- g++ -c source.cpp

또는 

infer -- gcc -c source.c
```

위 처럼 사용 하면 된다.

간단한 소스(표준 함수만 사용 한)는 위 명령을 이용하여 분설 할 수 있지만 외부 lib 또는 개발자가 지정한 경로에 별도로 header 파일 이 존재 하는 경우는 `?.h` 파일이 없다고 오류를 발생 시킨다.

이 때는 gcc 컴파일러(대부분의 C 컴파일러) 에서 옵션으로 지원 하는 -I(아이) 옵션 을 사용 하여 header 파일의 경로를 지정 하면 된다.



### C/C++ 에서 사용 하는 방법 2

```
infer -- g++ -c source.cpp -I. -I../include -I../PROJ/include
```

- 여러개의 -I 옵션을 줄 수 있다.



### C/C++ 에서 사용 하는 방법 3

```
infer -- g++ -c a.cpp b.cpp c.cpp -I. -I../include -I../PROJ/include
```

- 여러개의 소스를 분설 할 경우 위 처럼 소스 파일을 여러개 지정 하면 된다.



### 사용시 문제점

infer 의 version 이 말해 주듯이 완벽한 정적 분석은 하지 못한다. C/C++ Developer 들이 실수 하기 쉬운 NULL POINT 문제, memory leak 문제, FILE Close, 사용 하지 않는 변수 등을 잡아 주는데,  함수 내부에서 할당 해제 관련된 부분은 비교적 잘 잡아준다.

아직 infer 의 완벽한 사용법을 알지 못하는 시점에서 사용 중 아쉬운 점은 다음과 같다.

1. 함수의 인수로 point 변수가 있을 경우 사용 전 NULL 검사 오류(경고) 를 하지 않는다.
   내부 변수는 사용 전 NULL Check 가 되어 있지 않으면 error 로 Check 해 준다.

2. JNI 관련 소스 는 제대로 Check 하지 못한다.
   이 문제는 JNI header 파일이 좀 특이하게 되어 있어서 제대로 분석이 되지 않는 것으로 보인다.
```
   XdbJni.c:14:32: error: member reference type 'JNIEnv' (aka 'JNIEnv_') is not a pointer; did you mean to use '.'?
        const  char *pIniFile = (*env)->GetStringUTFChars(env,inifile, NULL);
                                ~~~~~~^~
                                      .
XdbJni.c:14:65: error: too many arguments to function call, expected 2, have 3
        const  char *pIniFile = (*env)->GetStringUTFChars(env,inifile, NULL);
```

위의 두개의 오류는 gcc(g++) 을 이용한 컴파일시 오류 없이 정상 컴파일이 되는 소스를 가지고 테스트 되었다.

infer 가 원하는 문법으로 변경 하면 컴파일이 되지 않는다.



3. Makefile 을 이용한 검사.

   Makefile 을 이용하여 infer 가 동작 한다면 좋을텐데 현재는 Makefile 을 이용하는 예제를 발견 하지 못하였다. 



아래는 배포된 소스의  `examples` 아래의 `README.md` 파일 중 일부 이다.

자세한 사용법은 https://github.com/facebook/infer 를 방문 하여 보기 바란다.



### Contents

-----

- `Hello.java`: try this example by running
```infer -- javac Hello.java ```

- `Hello.m`: try this example by running
  ```infer -- clang -c Hello.m```

- `hello.c`: try this example by running
  ```infer -- gcc -c hello.c```

  In this case, note that Infer captures the gcc command and runs
  clang instead to parse C files. Thus you may get compiler errors and
  warnings that differ from gcc's.

- `android_hello/`: a sample Android app. Try this example by running
  ```infer -- ./gradlew build```

  Make sure that you have the Android SDK 22 installed and up to date, and in
  particular the "Android SDK Build-tools" and "Android Support Repository".

- `c_hello/`: a sample make-based C project. Try this example by running
  ```infer -- make```

- `ios_hello/`: a sample iOS app. Try this example by running
  ```infer -- xcodebuild -target HelloWorldApp -configuration Debug -sdk iphonesimulator```

- `java_hello/`: a sample Java project. Try this example by running
  ```infer -- javac Pointers.java Resources.java Hello.java```

Note
----

The infer toplevel command must be in your PATH for the commands above to
succeed. Otherwise, modify the commands to use the correct path to infer, eg
  ```../infer/bin/infer -- javac Hello.java```

