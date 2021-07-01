# Docker에서 python 실행 환경을 만들기

출처: https://qiita.com/jhorikawa_err/items/fb9c03c0982c29c5b6d5?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=a167b2c3b8-Qiita_newsletter_468_06_30_2021&utm_medium=email&utm_term=0_e44feaa081-a167b2c3b8-34467781

사용하고있는 컴퓨터를 바꿔도 개발 환경을 갖추고 싶을 때는 Docker를 사용하면 편리합니다. Docker에서 Python 환경을 만들어 다양한 곳에 사용 할 수 있도록합니다. 



## Docker 는 ?

Docker는 시스템 개발 및 운영에 최근 자주 사용되는 컨테이너 기술을 제공하는 서비스 중 하나입니다. 컨테이너는 응용 프로그램의 실행에 필요한 환경을 패키지화하여 언제 어디서나 수행 할 수있는 구조입니다. 자신의 컴퓨터 환경을 오염없이 격리 된 환경을 만들어 거기서 프로그램을 운영 할 수 있기 때문에 시행 착오도 간단하고, 그 만든 환경은 공유하여 어디서나 실행할 수 있다는 점이 장점입니다. 개인적으로, M1 Mac에 최근했는데, 그 자체의 사양에 따라 지금까지의 Intel Mac에서 사용했던 Python 모듈을 사용할 수 없게 되거나 하는 것이 있었기 때문에 Docker 컨테이너 에서 라면 과거의 프로그램도 수행 할 수있는 것을 알기에 이것을 사용해보기로했습니다.



## Step 1. Docker 설치

환경에 맞는 Docker를 설치합니다. 여기에서는 Docker Desktop을 사용하는 것으로 가정합니다. M1 용 설치 프로그램도 있습니다. 설치 후 Terminal 등으로 docker-compose 명령을 사용할 수 있게되어 있는지 확인합니다.

설치 후 Docker Desktop을 시작하십시오.



## Step 2. Docker 설정

### 파일 구성

다음과 같은 파일 구성을 먼저 만들어주세요. Docker-python 이라고 써있는 폴더 이름은 원하는 이름으로 괜찮습니다. `Dockerfile` 과 `docker-compose.yml` 과 `sample.py`는 텍스트 파일 입니다.

```
docker-python/
  ├ Dockerfile
  ├ docker-compose.yml
  └ opt/
    └ sample.py
```



### Dockerfile

Dockerfile에 다음과 같이 설명합니다. 여기에 사용하는 개발 환경을 설정하고 컨테이너를 만들 때 먼저 설치하고 싶은 OS 용 라이브러리와 이번처럼 Python을 사용하려면 사용하고자하는 Python 모듈 등을 설치합니다.

덧붙여서 Dockerfile 으로 지정하지 않고 나중에 스스로 추가로 설치할 수 있습니다. 그냥 만들어진 image를 제거하고 다시 생성 하는 경우 Dockerfile 을 다시 사용하여 라이브러리 등을 설치하기 때문에 컨테이너를 만들 때마다 원하는 라이브러리 등은 여기에서 지정해 두는 것이 좋다고 생각합니다.

첫 번째 행에서 FROM python : 3 문구는 Docker가 공식적으로 제공하는 Python의 컨테이너를 기반으로로드하는 것입니다. 자세한 내용은 여기에서. 기본적으로 사용되는 환경은 Linux의 Debian 입니다.

``` dockerfile
FROM python:3
USER root

RUN apt-get update
RUN apt-get -y install locales && \
    localedef -f UTF-8 -i ja_JP ja_JP.UTF-8
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja
ENV LC_ALL ja_JP.UTF-8
ENV TZ JST-9
ENV TERM xterm

RUN apt-get install -y vim less
RUN pip install --upgrade pip
RUN pip install --upgrade setuptools

RUN python -m pip install jupyterlab
```

이 시점에서 사용하고 있는 Python 모듈이 이미 정해져 있으면 `RUN python -m pip install requests` 처럼 모듈의 설치 명령을 덧붙여 갑니다.



###  docker-compose.yml

``` yaml
version: '3'
services:
  python3:
    restart: always
    build: .
    container_name: 'python3'
    working_dir: '/root/'
    tty: true
    volumes:
      - ./opt:/root/opt
```



### sample.py

이 파일은 실행하고자하는 Python 프로그램을 작성합니다.
테스트 하는 것이므로, 간단한 코드를 작성해야합니다. 이 Python 파일을 실행할 때 함께 전달되는 인수 (argument)를 degrees에서 radians로 변경하여 터미널에 인쇄하는 간단한 내용입니다.

``` python
import math
import sys

def main():
  val = float(sys.argv[1])
  print(math.radians(val))

if __name__ == "__main__":
  main()
```



## Step 3. Docker 이미지 만들기, 컨테이너 빌드 및 컨테이너의 시작

터미널 (Mac) 또는 Git bash 나 PowerShell (Win)에서 다음과 같이 명령을 치고 docker-python 폴더를 작업 폴더에 한에 Docker 이미지 (가상 환경의 템플릿) 생성하고 그 이미지를 이용하여 Docker 컨테이너 (템플릿을 이용하여 만들어진 실제로 실행되는 가상 환경이 들어간 넣어 것)을 시작합니다. Dockerfile과 docker-compose.yml을 자동으로 참조 Docker 이미지가 만들어집니다.

이 명령은 이미지 만들기 → 컨테이너 작성 → 컨테이너 시작되지만 현재는 아직 컨테이너의 환경은 백그라운드에서 실행되고 있는 상태입니다.

``` bash
$ cd docker-python/
$ docker compose up -d --build
```



## Step 4. 만들어진 이미지와 컨테이너의 확인

그럼 실제로 만들어진 Docker 이미지와 컨테이너를 확인하려고합니다. 다음 명령을 치고 우선 현재 자신의 환경에서 사용할 수있는 이미지 목록을 가져옵니다.

``` bash
$ docker image ls
```

다음과 같은 메시지가 나와 있으면 성공입니다. 여기에서는 docker-python_python3 라는 것이 지금 만든 Docker 이미지의 이름입니다. 컨테이너를 만들기위한 템플릿의 이름처럼 기억하면 이해 될 수 있습니다.

``` bash
REPOSITORY                                 TAG       IMAGE ID       CREATED       SIZE
docker-python_python3                      latest    fdca699ff626   13 days ago   1.14GB
```

그런 다음 명령을 중 현재 실행되고 있는 컨테이너의 목록을 가져옵니다.

``` bash
$ docker container ls
```

다음과 같은 메시지가 나올 것으로 생각합니다.

``` bash
CONTAINER ID   IMAGE          COMMAND     CREATED          STATUS          PORTS     NAMES
05a6dee0f4d0   fdca699ff626   "python3"   45 minutes ago   Up 45 minutes             python3
```



## Step 5. 컨테이너에 연결

컨테이너가 달려 있는지 확인 했더니 다음 명령 컨테이너에 연결 (컨테이너 속의 환경에 들어가 그 환경에서 명령을 칠 수있게)합니다. python3라고하는 것은 docker-compose.yml에 지정된 컨테이너의 이름입니다.

``` bash
$ docker compose exec python3 bash
```

이제 다음에서 터미널로 치는 명령은 컨테이너의 환경에서 실행됩니다.



## Step 6. Python 용 라이브러리를 설치하기

컨테이너에 연결되면 먼저 Python의 버전을 확인하여보십시오.

``` bash
$ python --version
```

만약 사용하고 Python 모듈이 있으면 이 시점에서 설치해야합니다. 컨테이너 작성시에 설치되어있는 상태로하고 싶으면 Dockerfile에 그 설치를위한 명령을 덧붙여 두십시오. 컨테이너에 연결하여 설치된 모듈은 컨테이너와의 연결을 끄고 컨테이너를 삭제 한 시점에서 사라지기 때문에 임시적인 것이라고 생각합니다.

개인적으로 추천하는 방법은 일단 임시로 모듈을 설치하여 사용해보고 문제가 없으면 Dockerfile에 덧붙여 컨테이너 작성시 모듈이 자동 설치되도록하는 흐름이 좋다고 생각합니다.

``` bash
$ python -m pip install numpy
```



## Step 7. 자신의 컴퓨터에서 Python 파일을 실행

이제 Python의 실행 환경을 만들 수 있기 때문에 실제로 사용해 보자. 그 방법의 하나로서 자신의 컴퓨터에 Python으로 작성된 텍스트 파일 (.py 파일)을 만들고, 그것을 컨테이너의 환경 Python으로 수행 하자는 것입니다.

우선 docker-python 폴더 opt 폴더에 sample.py 이 있는지 확인 한 후, Docker 컨테이너에 연결되어있는 상태에서 다음 명령을 치고 현재 작업 디렉토리에있는 파일 목록을 가져옵니다 .

``` bash
$ ls
```

그러자 sample.py라는 파일이 목록에 나온다고 생각합니다. 이제 자신의 컴퓨터에서 opt이라는 폴더와 컨테이너의 환경에 폴더가 동기화되는 것이 확인 된 것입니다. 이 폴더를 통해 컨테이너의 환경에서 자신의 컴퓨터의 파일에 액세스 할 수있게된다는 치수입니다.

파일의 존재를 확인할 수 있었다 곳에 컨테이너의 환경에 설치된 Python으로 opt 폴더에서이 Python 파일을 실행 해 봅시다.

``` bash
$ python sample.py 180.0
```

결과적으로 3.141592653589793 같이 원주율의 값이 나오면 성공입니다.



## Step 8. 컨테이너 삭제

컨테이너를 사용한 후에 컨테이너와의 연결을 끄고 필요 없게 된 컨테이너를 제거합니다. 먼저 다음 명령으로 컨테이너와의 연결을 끊습니다.

``` bash
$ exit
```

다음 명령은 Docker 컨테이너를 종료하고 삭제합니다.

``` bash
$ docker compose down
```

다음과 같은 메시지가 나올 것입니다.

``` bash
[+] Running 2/2
 ⠿ Container python3              Removed                                                                           10.4s
 ⠿ Network docker-python_default  Removed    
```

이 후 다음과 같이 명령을 치고 컨테이너 목록에서 만든 컨테이너가 사라져 있으면 무사히 삭제 된 것입니다.

``` bash
$ docker container ls
```



## Step 9. 다시 컨테이너를 시작

컨테이너 템플릿 인 이미지의 내용을 복사하여 만들어지는 인스턴스와 같은 것이므로 다시 컨테이너를 시작하려면 Step 3에서 그랬던 것처럼 다시 이미지를 다시 만들고 그 이미지에서 컨테이너를 만들고 시작합니다 있습니다. 또는 이미 Dockerfile과 docker-compose.yml에서 이미지를 만들고있다 경우 - build 옵션을 제거하고 다음과 같은 명령을 입력하고 이미 만들어져있는 Docker 이미지에서 컨테이너를 만들고 시작하는 수 있습니다.

``` bash
$ docker compose up -d
```



## Step 10. 필요없는 이미지 삭제

만약 빌드 한 이미지도 필요 같은 경우는 제거합니다. 이를 위해 먼저 이미지의 목록을 가져옵니다.

``` bash
$ docker image ls
```

다음과 같은 명령 ID를 지정하여 필요없는 이미지를 삭제합니다. Imageid는 자신의 이미지 ID에 해당하는 것을 넣어주세요.

``` bash
$ docker image rm imageid
```



## Step 11. Jupyter Notebook에서 웹 브라우저를 통해 Python을 사용해 본다

Python의 코드를 테스트하고 싶을 때 특히 과학 계산 등에서는시 Jupyter Notebook과 같은 브라우저를 통해 대화식으로 Python 코드를 실행시킬 수있는 도구를 사용하고 싶은 일이 있을지도 모릅니다. 따라서 단계도 실어 둡니다. 덧붙여서 먼저 쓴 Dockerfile에 이미 jupyternotebook가 설치되는 같은 명령이 적혀 있습니다.

먼저, Docker 이미지를 삭제 한 경우 이미지를 다시 만듭니다. 단지 이번에는 컨테이너에 들어 가지 않고 자신의 컴퓨터의 브라우저에서 컨테이너의 환경에 액세스하게되므로 다음과 같은 명령을 사용하여 이미지 만 만듭니다. 그 때 터미널에서 명령 입력 대상이 자신의 컴퓨터에서 작업 폴더가 있는지 확인해야합니다.

``` bash
$ docker compose build
```

이제 다음 명령을 입력하여 이미지가 나열되어 있으면 OK입니다.

``` bash
$ docker image ls
```

이미지가있는 것이 확인되면 다음 명령으로 이미지에서 임시로 컨테이너를 시작하고 부팅 직후 Jupyter Notebook을 사용하는 눈의 서버를 컨테이너의 환경에서 시작합니다. 덧붙여서 $ PWD라는 명령을 입력하려고하는 터미널에서 현재 작업 폴더의 위치를 보여줍니다.

``` bash
$ docker run -v $PWD/opt:/root/opt -w /root/opt -it --rm -p 7777:8888 docker-python_python3 jupyter-lab --ip 0.0.0.0 --allow-root -b localhost
```

성공하면 다음과 같은 메시지가 나올 것입니다.

``` bash
    To access the server, open this file in a browser:
        file:///root/.local/share/jupyter/runtime/jpserver-1-open.html
    Or copy and paste one of these URLs:
        http://46102976db71:8888/lab?token=xxxxxxxxxx
        http://127.0.0.1:8888/lab?token=xxxxxxxxxx

```

Jupyter Notebook을 사용하기위한 서버가 컨테이너의 환경에서 무사히 상승했습니다. 다만 메시지에는 http://127.0.0.1:8888에 접속하라고 써 있습니다 만, 이것은 컨테이너의 환경에서 사용할 수있는 주소에서 컨테이너 외부 환경 인 자신의 컴퓨터 환경에서이 주소로 액세스 할 수 없습니다. 액세스하기 위해서는 8888 포트 대신 명령에서 지정한 7777라는 포트를 사용합니다. 명령하고있는 것은 즉 컨테이너의 환경에서 사용할 수있는 8888라는 포트를 자신의 컴퓨터에서 사용할 수 있도록 7777이라는 포트에 매핑 한 것입니다. 명령의 7777 부분은 원하는 수치로 바꾸어주고 괜찮습니다.

는 웹 브라우저를 열고 URL 란에 http://127.0.0.1:7777을 입력하여 Jupyter Notebook 서버에 액세스하여 봅시다.

그러자 Token authentication is enabled라는 페이지가 나온다고 생각하기 때문에 거기에 위의 메시지 token =에 계속 코드를 복사하여 Password or token의 입력란에 입력하고 Log in 버튼을 누릅니다.

잘하면 Jupyter Notebook에 로그인되어 무사히 사용할 수있게됩니다.

만약 브라우저를 통하지 않고, docker run 명령에서 시작한 컨테이너에 연결하려면 다른 터미널 창에서 다음 명령을 연결합니다. 이때 docker-container-id는 docker container ls에서 확인 된 현재 실행중인 컨테이너 ID를 입력합니다.

``` bash
$ docker exec -it *docker-container-id* bash
```

서버를 중지하려면 서버를 시작한 터미널에서 Control 키 + C를 입력합니다. 그러면 다음과 같은 메시지가 나옵니다.

``` bash
Shutdown this Jupyter server (y/[n])? 
```

터미널에 y를 입력하고 Enter 키를 눌러보세요. 서버가 멈추고 동시에 일시적으로 만들어져 컨테이너도 삭제됩니다. 연결 해제와 함께 컨테이너가 삭제 된 것은 docker run 명령을 사용할 때 -rm라는 옵션을 사용했기 때문입니다. 이 옵션이 없으면 연결을 해제 할 때 컨테이너가 정지 한 상태에서 제거되지 않고 남아 있습니다. 이때 정지 된 컨테이너는 docker container ls에서는 표시되지 않게되므로, 확인하고 싶은 경우는 docker container ls -a 중지 한 컨테이너도 모두 표시하도록합니다.



## Step 12. 정지 한 컨테이너 삭제

정지 한 컨테이너 가 계속 남아있을 수 있습니다. Docker start 명령으로 중지 한 컨테이너를 기동시켜 재사용 할 수 있습니다 만, 삭제하려면 다음과 같이 할 수 있습니다.

우선 다음과 같은 명령을 입력하여 정지하고있는 컨테이너 포함 해 모든 컨테이너를 표시합니다.

``` bash
$ docker container ls -a
```

다음과 같이 나타나면 그 중에서 지우고 싶은 컨테이너의 ID를 확인합니다. 이 경우 5e00e61e8717가 이에 해당합니다. 이 값은 컨테이너를 다시 만들 때마다 바뀝니다.

``` bash
CONTAINER ID   IMAGE                                      COMMAND                  CREATED          STATUS                     PORTS     NAMES
5e00e61e8717   docker-python_python3                      "jupyter-lab --ip 0.…"   11 seconds ago   Exited (0) 6 seconds ago             relaxed_wiles
```

삭제할 컨테이너의 ID가 확인되면 다음 명령으로 지정된 컨테이너를 삭제합니다. Container-id는 삭제할 컨테이너의 ID와 교체하십시오.

``` bash
$ docker container rm container-id
```



## 결론

이번에는 Docker가 무엇인지 이해하기 위해 Python 환경을 사용해 만들어 사용해 보았습니다.  프로그램의 실행 환경을 이렇게 쉽게 만들 수있는 것은 매우 편리하다고 생각합니다. 적극적으로 사용해 보겠습니다.

이상입니다. 수고하셨습니다.



