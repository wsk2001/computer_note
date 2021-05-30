# Docker 명령어 정리



**1. search (image 검색)**

- \# docker search centos
- Docker Hub로부터 사용가능한 image를 찾는 명령어
- Docker는 Dokcer HUB 를 통해 Git Hub 처럼 사용자들간의 이미지 공유를 할 수 있는 환경이 구축되어 있다
- 공식이미지는 galid/centos 처럼 / 앞에 사용자의 이름이 붙지 않는 것

**2. pull (image 다운로드)**

- \# docker pull centos:latest
-  docker hub로 부터 image를 다운받는 명령어

**3. images (image 목록 보기)**

- \# dokcer images

- 현재 Host PC에 다운 받아져있는 image들을 출력하는 명령어

- 방금 받은 이미지가 보인다 해당 이미지의 SIZE를 보면 202MB로 가상 이미지에 비해 상당히 용량이 적은 것을 볼 수 있다

   이는 Docker 이미지에는 커널이 포함되어있지 않고 서비스를 실행하기위한 파일들만이 들어있기 때문이다

**4. run (컨테이너 생성과 동시에 컨테이너로 접속)**

- \# docker run "REPOSITORY"

- docker run <옵션> <이미지이름 or 이미지ID> <실행할 파일>

- 단순히 image안의 파일을 실행할 목적으로 생성된 것 때문에 **메인으로 실행되는 파일이 종료되면** 

    **컨테이너도 같이 종료된다 따라서 계속해서 컨테이너를 유지하고 싶다면 -d 옵션을 이용해야 한다.**

- 다운받은 image를 실행한 형태인 컨테이너로 만드는 명령어이다

- **옵션**

  -  -i (interactive) : 사용자가 입출력을 할 수 있는 상태로 한다
  - -t : 가상 터미널 환경을 에뮬레이션 하겠다는 말
  - -d : 컨테이너를 일반 프로세스가 아닌 데몬프로세스 형태로 실행하여 프로세스가 끝나도 유지되도록 한다

- **결과물이 나타나질 않는다?**

  - 1) docker images를 통해 사용가능한 이미지 목록을 확인한다
  - 2) docker run 'REPOSITORY' 를 입력하여 image를 컨테이너화 한다
  - 3) 아무런 반응이 없다
  - 4) docker ps -a 를 입력하여 도커에서 실행되었던 컨테이너들을 확인한다
  - 5) docker.io/centos가 13초천에 생성되어 13초전에 종료되었다는 것을 볼 수 있다
  - 6) **docker는 image를 실행하고 실행한 결과물만을 반환하기 때문이다**

- **쉘이 실행된 상태로 유지하기**

  - 단순히 image안의 파일을 실행할 목적으로 생성된 것 때문에 **메인으로 실행되는 파일이 종료되면 컨테이너도 같이 종료된다**

     때문에 Docker 컨테이너가 지속적으로 실행할 파일을 지정해주면 된다

  - \# docker run -i -t docker.io/centos /bin/bash

- **컨테이너 종료**

  - **1) Ctrl + d :** 메인 실행파일인 쉘이 종료되기 때문에 컨테이너도 같이 종료된다
  - **2) "Ctrl + p" + "Ctrl + q" :** 컨테이너가 백그라운드에 살아있는 채로 Host OS로 돌아간다.

- **컨테이너를 실행한 상태에서 파일 만들기**

  - 1) docker의 컨테이너를 실행한 상태에서 touch명령어로 "/" 하위에 test.txt란 파일을 만들었다
  - 2) ls / 명령어로 확인한 결과 test.txt파일이 존재한다
  - 3) "Ctrl + D" 로 컨테이너를 종료 한다
  - 4) Host OS의 쉘에서 ls / 를 한다
  - 5) 컨테이너 안에서 생성했던 test.txt 파일이 보이지 않는다

- **CentOS systemctl permit 오류 해결**

  - **1) 우선 /sbin/init을 쉘로 하는 컨테이너를 백그라운드로 실행시킨다**

    ``` bash
  # docker run -d --name centos docker.io/centos /sbin/init
    ```

    **2) 방금 백그라운드로 생성한 컨테이너에 docker exec를 통해 /bin/bash 프로세스를 생성한다**
  
    ``` bash
  # docker exec -i -t centos /bin/bash
    ```
  
  - **3) 확인**
  
    - **docker centos 컨테이너 내부에서 systemctl 사용이 가능하다**
  
      ``` bash
      # systemctl start httpd
      # systemctl stop httpd
      ```

**5. ps (컨테이너 확인(실행중인 image 확인))**

- \# docker ps

  실행중인 컨테이너의 목록을 확인한다

- \# docker ps -a 

  이전에 종료되었던 컨테이너들을 포함한 컨테이너의 목록을 확인한다



**6. start (종료된 컨테이너 시작)**

\# docker start "container ID"

**1) 컨테이너를 실행한다**

**![img](https://t1.daumcdn.net/cfile/tistory/997ABD3A5C1F0D4132)**

**2) 컨테이너에서 touch명령어로 test.txt파일을 만든다**

**![img](https://t1.daumcdn.net/cfile/tistory/9953EE495C1F0D412E)**

**3) exit를 통해 컨테이너를 종료한다**

**4) ps -a 명령어를 이용하여 종료된 컨테이너를 출력하여 컨테이너 ID를 알아낸다**

**![img](https://t1.daumcdn.net/cfile/tistory/99426C3F5C1F0D4131)**

**4) docker start "컨테이너ID" 명령어를 통해 컨테이너를 실행한다**

  **성공하면 컨테이너 ID가 한번더 출력된다**

**![img](https://t1.daumcdn.net/cfile/tistory/990E9E3A5C1F0D4116)**

**5) 하지만 아직 Host OS의 쉘이 보이는것을 알 수 있다**

**docker ps에 a옵션을 주지 않고 현재 실행중인 컨테이너 목록만을 확인하면 방금실행한 컨테이너가 보인다**

![img](https://t1.daumcdn.net/cfile/tistory/99092B465C1F0E2E2A)



**7. attach (컨테이너에 접속하기)**

\# docker attach "container ID"

**1) docker ps 를 통해 현재 실행중인 컨테이너 목록을 확인 한다**

**![img](https://t1.daumcdn.net/cfile/tistory/99ADD0335C1F0EF034)**



**2) docker attach "Conatiner ID" 를 통해 컨테이너에 접속한다**

**![img](https://t1.daumcdn.net/cfile/tistory/9943323F5C1F0EF02D)**

**3) ls명령어로 확인하면 아까 생성했던 test.txt파일이 보인다**

**![img](https://t1.daumcdn.net/cfile/tistory/9975C2365C1F0F4F36)**

**8. stop (컨테이너 종료하기)**

\# docker stop "Container ID"





**9. rm (컨테이너 삭제)**

\# docker rm "Container ID"

\- 운영체제의 프로세스와 달리 **컨테이너가 종료되더라도 다시 실행하면 이전 상태가 유지된다 따라서 사용하지 않는 컨테이너는**

 **rm 명령어를 통해** **완전히 제****거를 해야한다**

**10. port forwarding**

\# docker run -it -p "HOST PORT":"Docker PORT" "실행할 도커 이미지" /bin/bash





**11. docker image 만들기**

**1. 반드시 "Dockerfile" 이라는 이름으로 docker file 생성**

FROM "기본이미지"

ADD "이미지에 추가할 파일"

RUN "실행할 명령어"



**2. 만들어진 dockerfile이 존재하는 위치에서 아래 명령어 입력**

\# docker build --tag "태그" . 



**Ubuntu**

**1.** **실행
**

**- docker run -it --name ubuntu docker.io/ubuntu /bin/bash**

**2. 기본 설정**

**1) service 명령어 이용**

**- apt-get update, apt-get upgrade**

**2) 네트워크 명령어 이용(ip add)**

**- apt-get install -y net-tools**