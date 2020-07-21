# cmake 최신버전으로 설치하기

출처: [https://blanche-star.tistory.com/entry/cmake-%EC%B5%9C%EC%8B%A0%EB%B2%84%EC%A0%84%EC%9C%BC%EB%A1%9C-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0](https://blanche-star.tistory.com/entry/cmake-최신버전으로-설치하기)



이글을 찾는사람이라면 딱 2가지 경우일겁니다.



minimal설치라서 cmake가 없거나

기존에 설치된 cmake로 설치진행시 낮은버전이니 최신버전으로 업그레이드 하라는 문구를 본사람일테죠.



### 1. 기존에 설치된 cmake를 삭제

전자일경우 딱히 기존 cmake를 삭제하는 작업같은건 없겠지만

후자는 기존 cmake를 삭제하셔야합니다.  root user 로 다음 명령을 실행 합니다.

``` bash
 yum remove cmake
```

rpm이면 

``` bash
rpm -e 패키지명
```



### 2. 다운로드

해당 게시글은 cmake컴파일 설치방법입니다.

https://cmake.org/download/ 에 가셔서 최신버전의 cmake파일을 다운받습니다.

리눅스 이실테니 tar.gz로 된녀석을 다운받으시고

(바이너리 설치는해본적이 없지만, 아마 매우쉬울것 같긴하네요. 하지만 여기는 컴파일 설치이니 바이너리 파일말고 소스파일로 다운받도록 합시다.)



저같은경우 링크주소 복사해서 wget 링크주소 으로 받는 편입니다. 그게 아무래도 ftp프로그램 실행할 필요도 없으니 편합니다.

cd /usr/local/src

wget https://cmake.org/files/v3.11/cmake-3.11.2.tar.gz

이런식으로 말아지요.



\2. 압축풀기 및 설치

다 받으셨다면 압축을 푸시고

tar -xvzf 해당파일.tar.gz

cd 압축풀린폴더

설치를 진행합시다.

./bootstrap --prefix=/usr/local



make



make install





\3. 설치확인

cmake --version

cmake version 3.11.2

만약 해당 명령어를 찾을수 없다고 뜰경우

vi ~./bash_profile

PATH=**/usr/local/bin**:$PATH:$HOME/bin





굵은 글씨를 참고하여 수정하면 된다.

베시를 수정한거니 ssh를 재시작하면 적용된다.



출처: https://blanche-star.tistory.com/entry/cmake-최신버전으로-설치하기 [순백염소]