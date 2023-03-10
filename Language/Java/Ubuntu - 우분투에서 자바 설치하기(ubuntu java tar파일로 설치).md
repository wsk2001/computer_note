# Ubuntu - 우분투에서 자바 설치하기(ubuntu java tar파일로 설치)

출처: https://myhappyman.tistory.com/87



 

------

### JDK 1.8 tar 다운로드

우분투에 자바 1.8 jdk를 압축파일을 다운받아서 설치해보겠습니다.

 

SSH접속하여 설치한다고 생각하고 먼저 1.8 jdk부터 다운받도록 합니다.

tar 압축파일로 설치를 할 예정이므로 tar로 다운로드합니다.

 

 

https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html



![img](.\images\EXg6W7V9hVfB9CopqVwtrK.png)자신의 OS에 알맞는 버전으로 다운로드합니다.



 

64비트 리눅스버전으로 다운로드했습니다.

 

이제 Ubuntu서버에 붙어서 확인하도록 하겠습니다. 저는 putty를 활용했습니다.

 

------

### 우분투 접근하여 설정하기

서버에 접근하시면 기본적으로 자바가 설치되어 있을겁니다. openjdk~~

 

1. 자바가 설치되어있다면 설정된 버전이 나올 겁니다.

**$ java -version**

 

2. 이미 설치되어 있던 자바(openJdk)를 삭제하도록 합니다. (1.8로 설치예정)

**$ sudo apt-get purge openjdk-\***

 

3. 삭제가 완료되면 이제 압축을 풀 디렉토리를 생성합니다.(mkdir - 디렉토리 생성)

**$ sudo mkdir -p /usr/local/java**

 

4. 생성한 디렉토리로 먼저 오라클에서 다운받은 자바 tar압축 파일을 옮겨줍니다.

\> 저는 파일질라를 이용해서 옮겼습니다. 다른 디렉토리에 있다면 mv명령어를 통해 옮겨주도록 합니다.



![img](.\images\FTkrkeXbznCWnstL1zuxkK.png)파일질라로 압축파일 이동



 

5. 압축을 풀어줍니다. (tar xvf)

**$ sudo tar xvf /usr/local/java/jdk-8u241-linux-x64.tar.gz**



![img](.\images\k8dx4xRJgd27eewTvsrk2k.png)압축이 풀린 모습



 

6. 환경설정을 진행합니다. (/etc/profile)

**$ sudo vi /etc/profile**

 

7. 열린 vi창 맨 밑에 자바 환경변수를 등록하고 저장합니다.

**export JAVA_HOME=$(readlink -f /usr/local/java | sed "s:bin/java::")**

 

*vi가 처음이신분들은 처음에 i를 입력하면 편집모드로 변경됩니다.*

*화살표 아래를 눌러서 맨 밑으로 오시고 위 export ~를 입력하신다음 **esc** 키를 입력 후*

*:**wq!** 를 입력하여 저장 종료를 합니다.*

 

8. 자바 명령어를 등록합니다.

``` sh
$ sudo update-alternatives --install "/usr/bin/java" "java" "/usr/local/java/jdk1.8.0_241/bin/java" 1;
$ sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/local/java/jdk1.8.0_241/bin/javac" 1;
$ sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/local/java/jdk1.8.0_241/bin/javaws" 1;

$ sudo update-alternatives --set java /usr/local/java/jdk1.8.0_241/bin/java;
$ sudo update-alternatives --set javac /usr/local/java/jdk1.8.0_241/bin/javac;
$ sudo update-alternatives --set javaws /usr/local/java/jdk1.8.0_241/bin/javaws;
```

 

9. 이제 자바 버전을 확인해봅니다.

**$ java -version**



![img](https://blog.kakaocdn.net/dn/og7Hr/btqB6X0N1US/kR9pE4p9BBSqFc9khlhyH0/img.png)1.8.0_241로 변경 설정된 모습



 

여기서 정상적으로 안보이는 경우 SSH접속을 다시 해보시고 명령어를 입력해보시고 그래도 동일하다면 위에서부터 중간에 경로가 틀린게 없는지 확인해주세요.



**끝**

