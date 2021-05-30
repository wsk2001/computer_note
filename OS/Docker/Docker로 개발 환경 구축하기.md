# Docker로 개발 환경 구축하기

출처: https://changhoi.github.io/posts/docker/docker-development-env-(1)/

## Docker로 개발 환경 구축하기 (1)

현재 개발 동아리 팀에 다양한 OS로 인해 생기는 여러 문제들을 Docker로 힙하게 해결 하고자 Docker를 이용해 개발 환경을 구축 했다. 이후 동아리에서 Docker hands on 세션을 진행 하기 위해, 내용을 정리해 보려고 한다. 목표는 docker compose를 활용한 일반적인 백엔드 개발 환경을 구성해보는 것이고, 이번 편은 docker image까지 사용하는 방법을 확인해볼 예정이다.



## Docker는 무엇일까?

도커는 2013년 솔로몬 하익스라는 사람이 최초로 오픈 소스로 공개했다고 한다. 도커의 역사나 기타 배경적 이야기는 제쳐두고, 도커의 기본 개념을 살펴보자.

- 도커는 **컨테이너 기반** 오픈 소스 **가상화 플랫폼**이다.

  컨테이너 기반이라는 것은 컨테이너형 가상화 기술을 사용 한다는 뜻이다. 기존의 호스트 안에 게스트 OS를 가상화 하는 것은 운영 체제 가상화라고 하는데, 운영 체제 가상화를 위해서는 가상화 소프트웨어를 사용 해야만 하고 OS 전체를 가상화한다. 컨테이너 가상화 기술은 이와 비교했을 때 오버헤드가 작아 운영 환경에서도 사용 가능하고, 개발 환경에서도 사용 가능하다. 가벼움 뿐 아니라 도커는 실행 환경을 컨테이너로 추상화하고 동일한 인터페이스를 제공해 프로그램의 배포 및 관리를 단순하게 한다.

- 도커는 **컨테이너형 가상화를 위한 상주 앱**(도커 엔진), 이를 관리하기 위한 **명령형 도구**로 이루어져 있다.

  아래 이미지에 나타난 것처럼 컨테이너를 올릴 도커 엔진과, 이들을 관리하기 위한 명령어들로 구성되어 있다는 것이다.

[![dockerVsVM](https://www.nakivo.com/blog/wp-content/uploads/2019/05/Docker-containers-are-not-lightweight-virtual-machines.png)](https://www.nakivo.com/blog/wp-content/uploads/2019/05/Docker-containers-are-not-lightweight-virtual-machines.png)

[dockerVsVM](https://www.nakivo.com/blog/wp-content/uploads/2019/05/Docker-containers-are-not-lightweight-virtual-machines.png)



## Docker는 왜 사용할까?

- 실행 환경이 변하지 않는다.
- 코드를 통해 실행 환경을 만들 수 있다.
- 실행 환경이 곧 앱이 되기 때문에 배포가 간단해진다. (컨테이너 자체를 바꿔서 버전을 업데이트 할 수 있다.)

도커를 왜 사용해야 하는가에 대한 글은 이 [링크](https://www.44bits.io/ko/post/why-should-i-use-docker-container)에서 확인하면 더 좋을 것 같다.

## Docker는 어떻게 쓰는 걸까?

나는 이미 도커를 쓰겠다고 마음을 먹었으니 앞선 주제는 이 정도 선에서 마무리 짓고, 도커를 어떻게 쓸지를 정리하도록 하겠다. 도커는 기본적으로 이미지를 다루는 섹션과 컨테이너를 다루는 섹션으로 구분 지을 수 있다. 쉽게 말하자면 이미지는 컨테이너를 정의하는 것이라고 볼 수 있고, 이에 따라 컨테이너가 생성된다.

이제 본격적으로 이미지와 컨테이너를 다룰 건데, 이번 글에서는 먼저 이미지에 대한 내용을 공부하고, 다음 컨테이너를 다뤄 보도록 하겠다.

## Docker Help와 Search

먼저 docker의 help 명령어와 search 명령어를 보고 넘어가자.

### `docker help`

도커 명령어들은 하위 명령을 가지고 있는 박스처럼 구성되어 있어서, help 명령어로 내려가면서 명령들을 찾아볼 수 있다. 하위 명령을 찾는 방법은 다음처럼 어떤 명령어 뒤에 `--help`를 붙여서 확인할 수 있다.

- `docker image --help`

### `docker search`

도커 허브에 등록된 이미지들을 찾아볼 수 있다. `--limit` 명령어로 검색 건수를 제한할 수도 있다.

- `docker search --limit 5 ubunut` (`--limit`을 뒤에 붙여도 됨)

## Docker 이미지

Docker 이미지는 컨테이너 실행에 필요한 파일과 설정 값 등을 포함하고 있는 것이다. 이미지는 Dockerfile을 통해서 빌드 할 수 있고, 빌드 된 이미지는 기본적으로 불변이다. 이미지는 Dockerfile이 변경되면 새롭게 빌드할 수 있고, 개인적으로는 이때 이미지는 변경된다기 보다는 대체된다는 느낌이다.

```
Dockerfile -> Docker Image -> Docker Container
```

Dockerfile은 우리가 필요한 이미지가 어떤 형태, 상태인지를 정의한 파일이다. 이 파일을 기반으로 이미지를 빌드할 수 있다. 여러 Instruction이 있는데 이후 정리해보도록 하고, 우선 가장 기본적인 Instruction을 정리해보자

| Instructuon | 설명                                                         |
| :---------- | :----------------------------------------------------------- |
| FROM        | 도커 이미지의 바탕이 될 베이스 이미지를 지정한다. 이 이미지는 Docker hub라는 레지스트리에 공개된 것이어야 한다. |
| RUN         | 이미지를 실행할 때 컨테이너 안에서 실행할 명령어를 정의한다. |
| COPY        | 호스트의 파일 또는 디렉토리를 도커 컨테이너 안으로 복사한다. |
| CMD         | 도커를 실행할 때 컨테이너 안에서 실행할 명령을 정의한다. RUN과 다르게 RUN은 이미지를 빌드할 때 실행되고, CMD는 컨테이너를 실행하면 한 번만 실행된다. |

이제 지금 배운 걸로 docker image를 만들어보자. 아래와 같은 코드가 있고, Dependencies도 모두 설치 된 상태라고 가정하자.

```javascript
// app.js
const app = require("express")();

app.use(logger("dev"));

app.get("", (req, res) => {
  res.send("<h1>Hello world docker!</h1>");
});

app.listen(3000, () => {
  console.log("Server is on 3000");
});
```

이런 자바스크립트 서버를 도커 위에서 동작하게 하려면 다음과 같은 Dockerfile이 필요하다.

```bash
# Dockerfile

FROM node:latest

COPY . .

RUN npm i

CMD ["npm", "start"]
```

위 Instruction을 확인해보면 위 Dockerfile은 node 최신 버전의 이미지를 베이스로, 현재 폴더를 도커 컨테이너의 현재 폴더에 복사 해 넣고, npm i를 수행한 상태의 컨테이너 이미지를 빌드하게 해준다. 이제 그럼 정말로 이미지를 만들어보자.

### `docker image build`

`docker image build`를 사용하면 도커 이미지가 빌드된다. 정확한 사용 방법은 아래와 같다.

- `docker image build <Dockerfile 위치>`

`docker image build .` 명령어로 이미지를 빌드 한 다음 `docker image ls`를 사용해서 빌드된 이미지를 확인할 수 있다. 이 명령어는 빌드된 이미지의 목록과 Dockerfile의 `FROM` Instruction에 의해 pull 받아온 이미지들까지 보여주는 명령어이다.

```bash
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
<none>              <none>              14c7411cfdf3        4 seconds ago        935MB
node                 latest              1a77bcb355eb        5 days ago          933MB
```

node의 이미지와 다르게 방금 우리가 만든 이미지의 REPOSITORY와 TAG는 `<none>`이다. 이미지의 이름과 태그라고 하는데 이를 지정해 주어야 나타난다. 빌드할 때 이미지에 이름과 태그를 붙이려면 `-t` 옵션을 붙여서 사용해야 한다.

- `docker image build -t 이름[:태그] <Dockerfile 위치>`

태그는 없어도 되는데, 없는 경우 latest로 자동 설정 된다. 아래 명령어를 다시 실행한 다음 목록을 확인해 보면, none 이미지의 태그와 이름이 붙었다.

- `docker image build -t changhoi/hands-on:latest .`

```bash
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
changhoi/hands-on   latest              14c7411cfdf3        45 seconds ago       935MB
node                 latest              1a77bcb355eb        5 days ago          933MB
```

IMAGE ID를 통해서 `<none>`과 `changhoi/hands-on`이 같은 이미지라는 것을 알 수 있는데, Dockerfile의 변경점이 없어서 대체된 것으로 보인다. Dockerfile을 기반으로 빌드를 할 때 도커는 캐시를 사용한다. 예를 들어서 아무 변경 사항 없이 다시 `docker image build .` 명령어를 사용하면 아래처럼 콘솔에 찍히는 것을 볼 수 있다.

```bash
Sending build context to Docker daemon  2.168MB
Step 1/4 : FROM node:latest
 ---> 1a77bcb355eb
Step 2/4 : COPY . .
 ---> Using cache
 ---> 8fd65e34f927
Step 3/4 : RUN npm i
 ---> Using cache
 ---> 1001c43e9d1c
Step 4/4 : CMD ["npm", "start"]
 ---> Using cache
 ---> 071dd8f024c6
Successfully built 071dd8f024c6
```

캐시를 사용하는 것으로 인해서 원래는 `<none>`이 생성되어야 하는데, 기존에 이름과 태그가 그대로 붙어 있는 것을 확인할 수 있다. 첫 번째 step은 `FROM` Instruction을 실행하는데, 캐시를 사용한다는 말은 없지만, 지정한 이미지가 호스트에 남아있다면 그 이미지를 그대로 가져다 쓴다.

위 두 가지 상황(`FROM`을 별도 pull없이 가져옴, 명령어를 캐시해서 사용함)을 캐시 없이 빌드 하도록 강제할 수 있는데 아래 옵션을 추가하면 된다.

- `docker image build --pull=true`: `FROM`에 있는 베이스 이미지를 새로 pull 받도록 한다.
- `docker image build --no-cache`: 다른 Instruction을 캐시 없이 진행한다.

아래 명령어를 다시 실행해보고 `docker image ls`를 해 봤다.

- `docker image build -t changhoi/hands-on --no-cache --pull=true .`

```bash
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
changhoi/hands-on   latest              d320649c3efe        10 seconds ago      935MB
<none>              <none>              14c7411cfdf3        16 minutes ago      935MB
node                latest              1a77bcb355eb        5 days ago          933MB
```

아까 이미지는 `<none>`으로 바뀌고 새로운 `latest`가 생긴 것을 확인할 수 있다. 이것은 이름:태그가 고유하기 때문이다. 이름은 중복 가능하지만, 같은 이름의 같은 태그는 존재하지 않는다.

### `docker image tag`

`<none>`이라고 붙은 이미지에 새롭게 태그를 붙여주자. 태그 명령어는 아래와 같이 사용할 수 있다.

- `docker image tag 원래이름:태그 새로운이름:태그`
- `docker image tag 구분가능한IMAGEID 새로운이름:태그`

구분 가능한 IMAGEID라는 것은 ID를 모두 치지 않더라도 유일해질 정도만 쳐도 괜찮다는 뜻이다. 아래 명령어로 태그를 붙인 다음, 목록을 확인해 보면 구분 가능한 ID에 새롭게 이름과 태그가 붙는 것을 확인할 수 있다.

- `docker image tag 14c changhoi/hands-on:old`

```bash
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
changhoi/hands-on   latest              d320649c3efe        3 minutes ago       935MB
changhoi/hands-on    old                14c7411cfdf3        5 minutes ago       935MB
node                latest              1a77bcb355eb        5 days ago          933MB
```

### `docker image rm`

만든 이미지는 당연히 지울 수도 있는데, 아래 명령어로 이미지를 삭제할 수 있다. 다만, 지우려는 이미지가 다른 이미지의 베이스 이미지면 안된다.

- `docker image rm changhoi/hands-on:old`

위 명령어를 실행한 뒤 목록을 확인해 보면 삭제된 것을 알 수 있다.

```bash
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
changhoi/hands-on   latest              d320649c3efe        45 minutes ago      935MB
node                latest              1a77bcb355eb        5 days ago          933MB
```

### ETC

- `docker image pull`: Docker hub에 있는 특정한 이미지를 Pull 받을 수 있다.

## Appendix

### Dockerfile Instruction

| Instruction | 설명                                                         |
| :---------- | :----------------------------------------------------------- |
| FROM        | 도커 이미지의 바탕이 될 베이스 이미지를 지정한다. 이 이미지는 Docker hub라는 레지스트리에 공개된 것이어야 한다. |
| RUN         | 이미지를 실행할 때 컨테이너 안에서 실행할 명령어를 정의한다. |
| COPY        | 호스트의 파일 또는 디렉토리를 도커 컨테이너 안으로 복사한다. |
| CMD         | 도커를 실행할 때 컨테이너 안에서 실행할 명령을 정의한다. RUN과 다르게 RUN은 이미지를 빌드할 때 실행되고, CMD는 컨테이너를 실행하면 한 번만 실행된다. |
| ADD         | COPY + 압축 파일 해제, URL로부터 컨테이너 파일 및 디렉토리 추가 |
| ARG         | `docker image build` 실행할 때 사용하는 변수                 |
| ENTRYPOINT  | 컨테이너를 실행 가능 파일로 사용할 때 정의하는 명령          |
| ENV         | 컨테이너 안에서 사용하는 환경 변수                           |
| EXPOSE      | 컨테이너가 노출하는 포르 설정                                |
| HEALTHCHECK | 명령을 실행한 다음 결과를 헬스 체크에 사용한다               |
| LABEL       | 이미지에 추가되는 메타 데이터                                |
| ONBUILD     | 컨테이너 안에서 실행되는 명령을 정의함. 이미지에서는 실행되지 않음 |
| STOPSIGNAL  | 컨테이너에 전달되면 컨테이너를 종료                          |
| USER        | 컨테이너 사용자. USER Instruction 이후에 나오는 RUN Instruction도 해당 사용자 권한으로 실행된다. |
| VOLUME      | 호스트나 다른 컨테이너에서 볼륨을 마운트                     |
| WORKDIR     | 컨테이너의 작업 디렉토리                                     |

## Reference

- Book: 도커/쿠버네티스를 활용한 컨테이너 개발 실전 입문
- https://github.com/voyagerwoo/docker-hands-on
- https://www.44bits.io/ko/post/why-should-i-use-docker-container
- https://www.44bits.io/ko/post/almost-perfect-development-environment-with-docker-and-docker-compose?fbclid=IwAR3GPrNSySCj4k-4qu3pL-fRq29uBQ8RR6MSfviibHZnmYj-BQiV_G9e34U

\#[DOCKER](https://changhoi.github.io/tags/docker/),[DOCKER_CONTAINER](https://changhoi.github.io/tags/docker-container/),[DOCKERFILE](https://changhoi.github.io/tags/dockerfile/),[도커로_개발_환경_구축하기](https://changhoi.github.io/tags/도커로-개발-환경-구축하기/)

#### 관련 글

- #### [Docker로 개발 환경 구축하기 (2)](https://changhoi.github.io/posts/docker/docker-development-env-(2)/)

- #### [도커 베스트 프랙티스 (번역)](https://changhoi.github.io/posts/docker/Docker-best-practices/)

- #### [Monolithic 서버사이드 타입스크립트 세팅 03](https://changhoi.github.io/posts/backend/serverside-typescript-setting-03/)

- #### [복잡한 애플리케이션 설정을 위한 복수의 도커 파일 사용하기(번역)](https://changhoi.github.io/posts/docker/Use-multiple-Dockerfiles-for-complex-application-configuration/)

- #### [컨테이너 서버리스 Fargate 배포하기](https://changhoi.github.io/posts/docker/fargate-demo/)





## Docker로 개발 환경 구축하기 (2)

그 전 글은 [여기](https://changhoi.github.io/posts/docker/docker-development-env-(1))에서 볼 수 있다.

이제 우리는 Docker 이미지를 다룰 수 있다. 이미지를 다루는 방법을 잘 모르는 상태라면 이전 글을 읽고 오는 편이 좋지만, 알고 있다면 더 준비할 내용 없이 바로 이 글을 읽어도 좋을 것 같다. 컨테이너를 다루는 방법은 이미지를 다루는 방법에 비해서 짧고 간단하다.



## Docker 컨테이너

짧게 컨테이너 명령어를 위한 기본 지식을 살펴본 다음 명령어들을 확인해보자.

- 컨테이너를 사용해서 실행되는 앱을 객체화 한 것처럼 실행할 수 있다.
- 컨테이너는 다음과 같은 상태로 나뉜다.
  1. 실행 중
  2. 정지
  3. 파기

위 주기는 용어로서 사용 된 건 아니고 그냥 말 그대로 이해하면 된다. `실행 중`은 도커 컨테이너 안에서 프로세스가 동작하고 있는 상태이고, `정지`는 컨테이너는 남아있지만 프로세스가 종료된 상태, `파기`는 컨테이너가 Terminate 되는 것을 의미한다.

### `docker container ls`

기본적으로는 현재 실행 중인 컨테이너의 목록을 보여준다. `-a`옵션을 붙이는 것을 통해서 현재 정지 상태에 있는 컨테이너까지 모두 볼 수 있다.

### `docker container run`

`docker container run`으로 이미지에서 컨테이너를 만들고 CMD 명령어를 실행시킬 수 있다. 명령어는 다음과 같이 쓴다.

```
docker container run [옵션] 이미지명:태그 [명령] [명령 파라미터] or
docker container run [옵션] 이미지ID [명령] [명령 파라미터]
```

옵션과 명령, 명령 파라미터 부분은 모두 생략이 가능하다. 명령, 명령 파라미터는 Dockerfile에서 정의했던 `CMD` 인스트럭션 부분을 오버라이드 할 수 있다.

```
$ docker container run changhoi/hello-world-docker

> hello-world-docker@1.0.0 start /
> node index

Hello world Docker!
```

주로 사용하는 옵션들은 아래와 같다.

- `-p`: 포트포워딩을 정의한다. 컨테이너의 포트와 호스트의 포트를 연결하는 옵션이다. 이에 대한 설명은 아래 잠깐 더 나와있다.
- `-d`: 백그라운드에서 실행하도록 하는 옵션이다.
- `--name 컨테이너명`: 실행되는 컨테이너에 원하는 이름을 붙일 수 있다. 이후 도커 명령을 사용할 일이 있을 때 컨테이너를 특정하기 쉬워진다.
- `-i`: 컨테이너의 표준 입력을 호스트와 연결한다.
- `-t`: 터미널 기능을 활성화 한다.
- `--rm`: 컨테이너가 종료될 때 컨테이너가 삭제되도록 한다.
- `-v`: 호스트와 컨테이너간 디렉토리, 파일을 공유하기 위해 사용하는 옵션이다. (갓도우는 무슨 설정을 해줘야 사용 가능하다.)

------

포트포워딩을 설명하자면, 일단 도커 컨테이너는 마치 하나의 게스트 OS처럼 자체 포트를 가지고 있다. 따라서 도커 안에 올라간 앱이 만약 3000번 포트를 listen하는 서버라고 치면 `localhost:3000`에서는 접근할 수 없다는 뜻이다. 그래서 도커 컨테이너의 3000번 포트를 로컬 호스트의 3000번 포트와 연결하겠다고 옵션을 만들어줘야 위 주소로 접근이 가능한 것이다.

```
-p`옵션은 다음과 같이 사용할 수 있다. `-p 로컬의포트:도커포트
$ docker container run -p 8000:3000 changhoi/hands-on

> hello-world-docker@1.0.0 start /
> node index

Server is on 3000
```

위에 작동 중인 Docker는 3000번 포트를 사용하지만, 로컬 호스트의 8000번 포트와 붙어있는 것이기 때문에 `localhost:8000`에서 확인할 수 있다.

```
curl localhost:8000
<h1>Hello world docker!!!</h1>%
```

------

여러 글을 확인 해본 결과 `-i` 옵션과 `-t` 옵션은 보통 같이 쓰이는 경우가 많다. 다음 파이썬 프로그램을 도커에 올렸다고 치자. 그 아래는 Dockerfile을 정의한 모습이다.

```
# cal.py
def add_ten(num):
    return num + 10

val = int(input("enter number: "))
output = add_ten(val);

print(output);
FROM python:latest

COPY . .

CMD ["python3", "cal.py"]
```

이제 `-it` 옵션인 경우, `-i`옵션, `-t`옵션을 각각 쓴 경우, 안 쓴 경우를 비교 해보자. 순서는 안 쓴 경우, `-i`, `-t`, `-it` 순서이다.

```
$ docker container run changhoi/py-cal

enter number: Traceback (most recent call last):
  File "cal.py", line 4, in <module>
    val = int(input("enter number: "))
EOFError: EOF when reading a line

# EOFError 에러가 난 모습
$ docker container run -i changhoi/py-cal

enter number: 10
20

# 정상 작동
$ docker container run -t changhoi/py-cal

enter number: 10

# 컨테이너쪽 터미널에 입력이 전달되지 않는다.
$ docker container run -it changhoi/py-cal

enter number: 10
20

# 정상 작동
```

실험을 통해서, `-t` 옵션을 써야 터미널을 활용 할 수 있는 상태가 되는 것 같고, 터미널에서 사용자 I/O가 필요한 경우에 `-i` 옵션을 줘야 한다는 것을 알았다. 터미널에서 사용자 입력을 하고 싶으면 `-it`를 사용하면 된다.

------

위 실험을 해보는 동안 정지 상태의 컨테이너가 많이 생겼다. 이런 일회성 컨테이너는 일일히 삭제를 해줘야 하고, 만약 `--name` 옵션을 준 명령어를 사용한 다음, 컨테이너를 삭제하지 않고 또 다시 실행하는 경우에는 도커는 에러를 발생시킨다. (이미 존재하는 컨테이너라고) 이런 경우 `--rm` 명령어를 추가해서 간단하게 바로 삭제가 가능하다. 아래 예시에서도 `--rm`을 붙이면 `docker container ls -a`에서 나타나지 않는 것을 알 수 있다.

```
$ docker container run --name unuse-rm node
$ docker container ls -a

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                     PORTS               NAMES
b2ed686f960e        node                "docker-entrypoint.s…"   9 seconds ago       Exited (0) 8 seconds ago                       unuse-rm

$ docker container run --name use-rm --rm node
$ docker container ls -a

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                     PORTS               NAMES
b2ed686f960e        node                "docker-entrypoint.s…"   2 minutes ago       Exited (0) 2 minutes ago                       unuse-rm
```

------

`-v` 옵션은 로컬의 파일시스템을 컨테이너와 공유할 수 있게 해주는 옵션이다. `-v 로컬디렉토리:컨테이너디렉토리` 형태로 작성한다. 아래 명령어로 현재 위치의 파일들을 컨테이너와 공유할 수 있다.

```
$ docker container run -v `{PWD}`:/app
```

### `docker container stop`

실행 중인 상태의 도커를 정지 상태로 만든다. `docker container stop 컨테이너명 or 컨테이너ID`를 사용해서 정지할 수 있다.

### `docker container restart`

실행 중인 상태의 도커를 재시작한다. `docker container restart 컨테이너명 or 컨테이너ID`를 사용해서 재시작할 수 있다.

### `docker container rm`

정지 상태의 컨테이너를 파기할 수 있다. `docker container rm 컨테이너명 or 컨테이너ID`를 사용해 파기할 수 있다. 일반적으로는 컨테이너가 정지 상태여야 파기가 가능하지만, `-f`옵션을 주면 실행 중인 컨테이너를 정지하고 파기할 수 있다.

### `docker container exec`

실행 중인 컨테이너에 명령을 실행할 수 있다. `docker container exec [옵션] [컨테이너명 or 컨테이너ID] [실행할명령]` 형태로 사용한다.

### 기타 알아보면 좋을 것들

- `docker container cp`: 실행 중인 컨테이너에 파일을 복사해 넣을 때 사용한다.
- `docker container log`: 표준 입출력으로 나타나는 로그들을 볼 수 있다. 파일에 저장되는 것은 볼 수 없다.
- `docker container prune`: 정지 상태인 도커 컨테이너를 모두 삭제해준다.

## 마치며

컨테이너 명령어는 이미지보다 간단하게 정리했다. 이제 컨테이너들을 사용해서 개발 환경을 구축하다가 삽질 한 내용을 올려볼까 했는데 사실 그 삽질기는 여기 [REF](https://www.44bits.io/ko/post/almost-perfect-development-environment-with-docker-and-docker-compose?fbclid=IwAR3GPrNSySCj4k-4qu3pL-fRq29uBQ8RR6MSfviibHZnmYj-BQiV_G9e34U)에서 더 자세하게 확인할 수 있다. 결과적으로 팀원들과 컨테이너 개발 환경을 구축했는데 개발 환경을 구축하는 것은 Docker compose를 사용해 여러 컨테이너를 활용한 로컬 개발 환경을 구축한 것이었다. 해당 결과는 이 [링크](https://weehan-dev.github.io/posts/devops/docker-compose로-개발-환경-구성하기)에서 볼 수 있다. 해당 글은 이 시리즈처럼 자세한 이론은 설명하지 않았다. 결과적으로 어떻게 만들어내는 건지 궁금하다면 바로 링크를 확인하면 될 것 같다. 컴포즈에 대한 글은 다음 글에서 다뤄볼 생각인데, 꼼꼼한 설명보다는 필요한 내용과 어디서 어떻게 찾아볼 수 있는지에 대한 내용이 될 것 같다.

## Reference

- Book: 도커/쿠버네티스를 활용한 컨테이너 개발 실전 입문
- https://github.com/voyagerwoo/docker-hands-on
- https://docs.docker.com/v17.12/edge/engine/reference/commandline/container/
- https://www.44bits.io/ko/post/almost-perfect-development-environment-with-docker-and-docker-compose?fbclid=IwAR3GPrNSySCj4k-4qu3pL-fRq29uBQ8RR6MSfviibHZnmYj-BQiV_G9e34U