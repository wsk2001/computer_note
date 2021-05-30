# Dockerfile 작성 방법

출처: https://velog.io/@seheon99/Dockerfile-%EC%9E%91%EC%84%B1-%EB%B0%A9%EB%B2%95-12

# Dockerfile reference

Docker는 `Dockerfile` 의 구성 요소를 읽으며 자동으로 이미지를 빌드할 수 있다. `Dockerfile` 은 이미지를 어셈블하기 위해 유저가 커맨드 라인에 입력하는 모든 명령들을 담은 텍스트 문서다. `docker build` 를 사용하므로써 유저들은 여러개의 커맨드라인 명령어들을 성공적으로 자동으로 실행할 수 있는 빌드를 만들 수 있다.

이 문서는 `Dockerfile` 안에서 사용할 수 있는 명령어들을 설명한다. 이 문서를 다 읽고난 후 다양한 팁이 있는 [`Dockerfile` Best Practices](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/) 가이드를 읽어볼 수 있다.

## Usage

Docker의 빌드 명령어는 `Dockerfile` 과 `PATH` 나 `URL` 등의 빌드 정보로 지정된 특정 파일 집합을 통해 이미지를 만든다. `PATH` 는 로컬 파일시스템의 디렉토리에 해당하고 `URL` 은 Git 레포지토리의 주소에 해당한다.

빌드 정보는 재귀적으로 처리된다. 따라서 `PATH` 는 모든 하위 디렉토리를 포함하고 `URL` 은 레포지토리와 모든 하위 모듈을 포함한다. 다음 예제는 현재 디렉토리를 `PATH` 로 빌드하는 커맨드를 나타낸다.

```shell
$ docker build .

Sending build context to Docker daemon	6.51 MB
...
```

빌드는 CLI가 아닌 Docker daemon에 의해 실행된다. 빌드 과정은 모든 빌드 정보를 daemon에게 전달하는 것으로 시작한다. 대부분의 경우, 빈 디렉토리와 비어있는 빌드 정보로 시작한 후 Dockerfile을 만드는 것이 가장 좋다. Dockerfile을 빌드하기 위해 필요한 파일도 추가해야 한다.

> ### Warning
>
> 루트 디렉토리 `/` 를 `PATH` 로 사용하면 모든 파일이 Docker daemon으로 전송해 빌드하므로 사용하면 안된다.

빌드 정보 안에 있는 파일을 사용하기 위해 `Dockerfile` 에서 `COPY` 와 같은 명령어로 파일을 특정시킨다. 빌드의 성능 향상을 위해 `.dockerignore` 파일을 통해 파일과 디렉토리 등을 빌드에서 제외시킬 수 있다. `.dockerignore` 파일을 만드는데에 대한 자세한 정보는 후술한다.

전통적으로, `Dockerfile` 은 `Dockerfile` 로 불리고 빌드 정보의 루트에 위치한다. `docker build` 명령어와 `-f` 플래그를 함께 사용하면 Dockerfile의 위치를 지정해줄 수 있다.

```shell
$ docker build -f /path/to/a/Dockerfile .
```

다음과 같이 레포지토리를 특정한 후 빌드가 성공했을 때 저장할 이미지를 지정할 수 있다.

```shell
$ docker build -t shykes/myapp .
```

빌드 후 여러 레포지토리 이미지를 태그시키고 싶다면, 여러 개의 `-t` 파라미터를 `build` 명령어 다음에 추가시킬 수 있다.

```shell
$ docker build -t shykes/myapp:1.0.2 -t shykes/myapp:latest .
```

`Dockerfile` 의 명령어들을 실행시키기 전 Docker daemon은 `Dockerfile` 의 유효성 검사를 진행하고 문법적으로 문제가 있다면 에러를 발생시킨다.

```shell
$ docker build -t test/myapp .

Sending build context to Docker daemon	2.048 kB
Error response from daemon: Unknown instruction: RUNCMD
```

Docker daemon은 `Dockerfile` 의 명령어들을 하나씩 실행시키고 만약 필요하다면 이미지가 완성되기 전 각각의 명령어들의 결과를 새 이미지에 바로 반영한다. Docker daemon은 받은 빌드 정보를 자동으로 삭제한다.

각각의 명령어들은 독립적으로 작동하고 새로운 이미지를 만든다는 것을 떠올리면 `RUN cd /tmp` 는 다음 명령어에게 아무런 영향을 끼치지 않는다는 것을 알 수 있다.

Docker는 가능할 때 마다 `docker build` 의 실행 속도 향상을 위해 중간 위치에 있는 이미지 (캐시) 를 재사용한다. 재사용하면서 `Using cache` 라는 메시지를 남긴다.

```shell
$ docker build -t svendowideit/ambassador .

Sending build context to Docker daemon 15.36 kB
Step 1/4 : FROM alpine:3.2
 ---> 3lf630c65071
Step 2/4 : MAINTAINER SvenDowideit@home.org.au
 ---> Using cache
 ---> 2alc91448f5f
Step 3/4 : RUN apk update &&	apk add socat &&	rm -r /var/cache
 ---> Using cache
 ---> 21ed6e7fbb73
Step 4/4 : CMD env | grep _TCP= | (sed 's/.*_PORT_\([0-9]*\)_TCP=tcp:\/\/\(.*\):\(.*\)/socat -t 100000000 TCP4-LISTEN:\1,fork,reuseaddr TCP4:\2:\3 \&/' && echo wait) | sh
 ---> Using cache
 ---> 7ea8aef582cc
Successfully built 7ea8aef582cc
```

빌드 캐시는 오직 로컬 부모 체인이 있는 이미지만 사용이 가능하다. 부모 체인이 있는 이미지란 캐시가 만들어진 이미지로부터 만들어진 이미지거나 이미지의 모든 체인이 `docker load` 로 불러온 이미지란 뜻이다. 만약 특정 이미지로부터 만들어진 캐시를 사용해 빌드하고 싶다면 `--cache-from` 옵션을 통해 가능하다. `--cache-from` 옵션을 통해 지정된 이미지는 부모 체인이나 다른 등록 과정이 필요하지 않다.

## BuildKit

18.09 버전부터 Docker는 [moby/buildkit](https://velog.io/@seheon99/github.com/moby/buildkit) 프로젝트에서 제공하는 빌드를 실행하기 위한 새로운 백엔드를 지원한다. BuildKit 백엔드는 기존 기능에 비해 많은 해택을 제공한다. BuildKit의 기능은 다음과 같다.

- 사용되지 않는 빌드 단계를 찾아내고 비활성화 한다.
- 빌드 단계에 따라 빌드들을 병렬화 한다.
- 빌드 중 빌드 정보에 따라 변경된 파일만 전송한다.
- 사용되지 않는 빌드 정보 안의 사용되지 않은 파일들을 찾아내고 비활성화 한다.
- 다양한 새 기능이 있는 외부 Dockerfile을 사용한다.
- API의 사이드 이펙트를 예방한다.
- 자동 빌드 시 빌드 캐시의 우선순의를 결정한다.

BuildKit 백엔드를 사용하려면 `docker build` 하기 전 CLI에 `DOCKER_BUILDKIT=1` 의 환경 변수 설정을 해야 한다.

## Format

다음은 `Dockerfile` 의 형식이다.

```dockerfile
# Comment
INSTRUCTION arguments
```

명령어는 대소문자를 구분하지 않는다. 하지만 인자들과 더 쉽게 구분하기 위해 대문자로 작성하는 암묵적인 규칙이 있다.

Docker는 `Dockerfile` 의 순서에 따라 명령어를 수행한다. `Dockerfile` 은 **반드시 `FROM` 명령어로 시작** 해야 한다. `Dockerfile` 은 명령문과 주석, 글로벌 변수로 나뉜다. `FROM` 명령어는 빌드의 *원형 이미지* 를 정하고 `Docerfile` 의 `FROM` 명령어에서 사용할 인자를 정하는 하나 이상의 `ARG` 명령어와 함깨 사용된다.

Docker는 `#` 으로 *시작하는* 올바르지 않은 구문을 주석으로 처리한다. `#` 은 인자로 처리되는 줄은 주석으로 처리하지 않는다. 다음은 올바른 구문이다.

```dockerfile
# Comment
RUN echo 'we are running some # of cool things'
```

주석은 Dockerfile 명령어들이 실행되기 전에 제거된다. 따라서 다음 코드와 같이 사용된 주석은 쉘에서 `echo` 명령어를 실행할 떄 처리되지 않는다.

```dockerfile
RUN echo hello \
# comment
world
```

줄을 연장시켜주는 `\` 문자는 주석에 적용되지 않는다.

> ### Note on whitespace
>
> 호완성을 위해 주석이나 명령어 앞에 오는 빈칸은 무시하고 회피한다. 앞에 오는 빈칸은 적용되지 않고 다음과 같이 변환된다.
>
> ```dockerfile
> 		# this is a comment-line
> 	RUN echo hello
> RUN echo world
> # this is a comment-line
> RUN echo hello
> RUN echo world
> ```
>
> 그러나 다음 `RUN` 명령어 뒤에 오는 인자와 같이 명령어의 *인자* 로 들어오는 빈칸은 무시되지 않는다. 따라서 다음은 앞에 빈칸을 포함한 채 `' hello world'` 라고 출력될 것이다.
>
> ```dockerfile
> RUN echo "\
> 	 hello\
> 	 world"
> ```

## Parser directives

구문 지시자는 선택적이고 `Dockerfile` 의 다음 라인부터 어떻게 명령들이 적용될지에 영향을 준다. 구문 지시자는 빌드에 레이어를 추가하지 않고, 빌드하는 단계에서 나타나지도 않는다. 구문 지시자는 `# directive=value` 와 같이 특별한 주석의 형태로 작성된다. 하나의 지시자는 한 번만 사용된다.

주석을 확인할 때, 빈 줄이나 빌드 명령어가 실행된 후에는 Docker는 더 이상 구문 지시자를 찾지 않는다. 대신 Docker는 모든 구문 지시자 형태를 주석으로 처리하고 더 이상 올바른 구문 지시자인지 확인하지 않는다. 그러므로 모든 구문 지시자는 `Dockerfile` 의 최상단에 위치한다.

구문 지시자는 대소문자를 구분하지 않는다. 그러나, 소문자로 작성하는 것이 암묵적인 규칙이다. 암묵적인 규칙에는 모든 구문 지시문 다음에 빈 줄 하나를 넣는 것도 포함된다. 구문 지시자에는 다음 줄을 연속해서 사용할 수 있는 문자 `\` 의 사용이 제한된다.

이런 규칙들 때문에 다음 예시들은 모두 문법적으로 적절하지 않다.

- `\` 의 잘못된 사용

  ~~~null
  	```dockerfile
  	# direc \
  	tive=value
  	```
  ~~~

- 중복 정의

  ~~~null
  	```dockerfile
  	# directive=value1
  	# directive=value2
  
  	FROM ImageName
  	```
  ~~~

- 빌드 명령어 다음에 작성해 주석으로 처리하는 경우

  ~~~null
  	```dockerfile
  	FROM ImageName
  	# directive=value
  	```
  ~~~

- 구문 지시자가 아닌 주석 다음에 작성해 주석으로 처리하는 경우

  ~~~null
  	```dockerfile
  	# About my dockerfile
  	# directive=value
  	FROM ImageName
  	```
  ~~~

- 적절하지 않은 구문 지시자로 구문 지시문이 아닌 주석으로 처리되고 따라서 다음 구문 지시자 또한 주석으로 처리되는 경우

  ~~~null
  	```dockerfile
  	# unknowndirective=value
  	# knowndirective=value
  	```
  ~~~

- 구문 지시문에서 라인을 넘기는 문자가 아닌 빈 문자는 허용된다. 따라서 다음 지시문들은 모두 같은 것으로 처리된다.

  ~~~null
  	```dockerfile
  	#directive=value
  	# directive =value
  	#		directive= value
  	# directive = value
  	#		  dIrEcTiVe=value
  	```
  ~~~

다음은 지원되는 구문 지시어이다.

- `syntax`
- `escape`

## syntax

```dockerfile
# syntax=[remote image reference]
```

For example:

```dockerfile
# syntax=docker/dockerfile
# syntax=docker/dockerfile:1.0
# syntax=docker.io/docker/dockerfile:1
# syntax=docker/dockerfile:1.0.0-experimental
# syntax=example.com/user/repo:tag@sha256:abcdef...
```

이 기능은 BuildKit을 사용 중일때만 활성화 가능하다.

`syntax` 지시자는 현재 Dockerfile을 빌드할 때 사용될 Dockerfile 빌더의 위치를 지정해준다. BuildKit은 Docker 이미지에 분산되어 있는 외부 빌드의 구성 요소를 하나인 것 처럼 연속적으로 사용할 수 있게 해주고 컨테이너 샌드박스 환경 안에서 실행시킨다.

사용자 지정 Dockerfile 구성요소로 다음 기능을 사용할 수 있다.

- 데몬의 업데이트 없이 자동으로 버그를 잡는다.
- 모든 사용자가 같은 Dockerfile을 같은 환경에서 빌드한다는 것을 보장해준다.
- 데몬 업데이트 없이 가장 최신 기능을 사용할 수 있다.
- 실험적인 기능이나 서드파티 기능을 사용할 수 있다.

## Official releases

Docker는 Docker Hub의 `docker/dockerfile` 레포지토리를 빌드하는 Dockerfile이 사용할 수 있는 여러 공식 버전을 제공한다. 배포된 이미지에 접근하는 방법은 두 가지가 있다.

Stable channel은 다음과 같이 의미상 버전을 따른다.

- `docker/dockerfile:1.0.0` - `1.0.0` 버전만을 허용한다.
- `docker/dockerfile:1.0` - `1.0.*` 의 버전을 허용한다.
- `docker/dockerfile:1` - `1.*.*` 의 버전을 허용한다.
- `docker/dockerfile:latest` - stable channel 의 가장 최근 배포된 버전을 허용한다.

Experimental channel 은 stable channel 에서 배포된 버전의 구성요소가 포함된 상위 버전을 사용한다.

- `docker/dockerfile:1.0.1-experimental` - `1.0.1-experimental` 버전만 허용한다.
- `docker/dockerfile:1.0-experimental` - `1.0` 버전 이후의 가장 최신 experimental 버전을 허용한다.
- `docker/dockerfile:experimental` - experimental channel 에 배포된 가장 최근 버전을 허용한다.

두 채널 중 필요에 맞는 채널을 사용해야한다. 만약 버그 수정을 원한다면, `docker/dockerfile:1.0` 을 사용해야 할 것이다. 만약 실험적 기능의 효과를 사용하고자 한다면, experimental channel을 사용해야 할 것이다. 만약 experimental channel을 사용 중이라면 새 배포 버전이 나올 때 호완되지 않는 상황이 발생할 수 있다. 따라서 전체 버전을 명시하는 것을 권장한다.

## escape

```dockerfile
# escape=\ (backslash)
```

또는

```dockerfile
# escape=` (backtick)
```

`escape` 지시자는 `Dockerfile` 에서 문자를 escape 문자로 설정한다. 만약 특정되지 않았다면, 기본 escape 문자는 `\` 이다.

Escape 문자는 escape 문자를 위해 사용하기도 하고 개행을 위해 사용하기도 한다. Escape 문자는 `Dockerfile` 명령어에 여러 줄을 작성할 수 있게 해준다. 하지만 명심해야 할 것이 `Dockerfile` 에 `escape` parser directive가 있든 없든 *`RUN` 명령에서는 개행을 제외한 escaping은 작동하지 않는다는 점이다.*

Escape 문자를 억음 부호 (**`**) 로 설정하는 것은 `\` 가 디랙토리 경로를 분리하는 문자로 사용되는 `Windows` 환경에서 유용할 수 있다. 억음 부호는 Windows PowerShell 에서 같은 의미로 사용되기도 한다.

다음 `Windows` 에서 작동하지 않는 예제를 살펴보자. 두 번째 줄의 두 번째 `\` 은 첫 번째 `\` 에 의해 escape 되지 않고 다음 줄로의 연장의 의미로 해석된다. 또한 세 번째 줄의 마지막 `\` 도 명령어의 인자로 사용되지 않고 다음 줄로의 연장을 뜻하는 문자가 되었다. 그 결과 두 번째 줄과 세 번째 줄은 하나의 명령문으로 처리된다.

```dockerfile
FROM microsoft/nanoserver
COPY testfile.txt c:\\
RUN dir c:\
```

결과는 다음과 같다.

```powershell
PS C:\John> docker build -t cmd .
Sending build context to Docker daemon 3.072 kB
Step 1/2 : FROM microsoft/nanoserver
 ---> 22738ff49c6d
Step 2/2 : COPY testfile.txt c:\RUN dir c:
GetFileAttributesEx c:RUN: The system cannot find the file specified.
PS C:\John
```

위 상황에서 하나의 해결책은 `COPY` 명령어와 `dir` 명령어의 대상을 `/` 로 지정하는 것이다. 그러나 이 방법은 좋게 말해서 `Windows` 경로와 자연스럽지 않고 나쁘게 말해서 `Windows` 의 모든 명령이 `/` 를 경로 구분자로 인식하지 않으니 에러가 발생하기 쉬운 조건이다.

`escape` 구문 지시자를 추가함으로써 다음의 `Dockerfile` 은 자연스러운 구문을 갖고 성공적으로 실행될 수 있다.

```dockerfile
# escape=`

FROM microsoft/nanoserver
COPY testfile.txt c:\
RUN dir c:\
```

결과는 다음과 같다.

```powershell
PS C:\John> docker build -t succeeds --no-cache=true .
Sending build context to Docker daemon 3.072 kB
Step 1/3 : FROM microsoft/nanoserver
 ---> 22738ff49c6d
Step 2/3 : COPY testfile.txt c:\
 ---> 96655de338de
Removing intermediate container 4db9acbb1682
Step 3/3 : RUN dir c:\
 ---> Running in a2c157f842f5
 Volume in drive C has no label.
 Volume Serial Number is 7E6D-E0F7

 Directory of c:\

10/05/2016  05:04 PM             1,894 License.txt
10/05/2016  02:22 PM    <DIR>          Program Files
10/05/2016  02:14 PM    <DIR>          Program Files (x86)
10/28/2016  11:18 AM                62 testfile.txt
10/28/2016  11:20 AM    <DIR>          Users
10/28/2016  11:20 AM    <DIR>          Windows
           2 File(s)          1,956 bytes
           4 Dir(s)  21,259,096,064 bytes free
 ---> 01c7f3bef04f
Removing intermediate container a2c157f842f5
Successfully built 01c7f3bef04f
PS C:\John>
```

## Environment replacement

`Dockerfile` 에서 실행되는 일부 명령들은 `ENV` 문장을 통해 정의된 환경 변수를 매개변수로 사용할 수 있다. 탈출 문자는 문자로 표현할 수 없는 내용을 구문에 포함시키기 위해 사용된다.

환경 변수는 `Dockerfile` 에 `$variable_name` 또는 `${variable_name}` 의 형식으로 정의될 수 있다. 두 형식은 같은 표현으로 처리되고 중괄호 표현은 `${foo}_bar` 과 같은 빈 문자가 없는 변수의 이름에서 생기는 모호함을 해결하기 위해 사용된다.

`${variable_name}` 의 표현은 또한 다음과 같은 몇 개의 표준 bash 연산자를 지원한다.

- `${variable:-word}` 는 `variable` 이 정의되어 있다면 그 값으로 대체되고 정의되어있지 않다면 `word` 로 대체된다.
- `${variable:+word}` 는 `variable` 이 정의되어 있다면 `word` 가 그 값으로 대체되고 정의되어있지 않다면 빈 문자열로 대체된다.

두 표현에서 `word` 는 환경 변수를 포함한 아무 문자열이나 될 수 있다.

Escaping 을 통해 변수를 작성하기 전에 `\$foo` 나 `\${foo}` 처럼 `\` 를 포함해 작성할 수 있다. 앞의 두 예시는 `$foo` 와 `${foo}` 로 대체된다.

예시 (`#` 다음의 문자로 대체되어 적용된다)

```dockerfile
FROM busybox
ENV foo /bar
WORKDIR ${foo}		# WORKDIR /bar
ADD . $foo			# ADD . /bar
COPY \$foo /quux	# COPY $foo /quux
```

환경 변수는 `Dockerfile` 에서 다음의 명령어에 적용할 수 있다.

- `ADD`
- `COPY`
- `ENV`
- `EXPOSE`
- `FROM`
- `LABEL`
- `STOPSIGNAL`
- `USER`
- `VOLUME`
- `WORKDIR`
- `ONBUILD` (위의 명령어들과 함께 사용되었을 때)

하나의 `ENV` 명령어로 여러 변수를 설정할 때 환경 변수를 사용하면 우선순위는 모두 같다. 예를 들어 다음과 같이 환경변수를 정의한다고 가정해보자.

```dockerfile
ENV abc=hello
ENV abc=bye def=$abc
ENV ghi=$abc
```

이 때 `def` 의 값은 `bye` 가 아닌 `hello` 가 된다. 하지만 `ghi` 의 값은 `abc` 를 `bye` 로 설정한 명령어와 같이 실행된게 아니므로 `bye` 가 된다.

## .dockerignore file

Docker CLI가 docker daemon에게 내용물을 전달하기 전에 `.dockerignore` 은 내용물의 루트 디렉토리에 있는지 찾아본다. 만약 `.dockerignore` 이 루트 디렉토리에 있다면 CLI는 내용물 중 `.dockerignore` 의 내용과 일치하는 파일이나 디렉토리를 제외시킨다. 이 작업은 민감하거나 쓸모없는 파일이나 디렉토리가 daemon에게 전달되거나 `ADD`, `COPY` 의 명령으로 이미지에 추가되는걸 막아준다.

CLI는 `.dockerignore` 파일을 유닉스 쉘 에서 사용하는 것과 비슷하게 개행으로 구분된 패턴 리스트로 인식한다. 매칭의 목적은 내용물의 루트를 실제 루트 디렉토리처럼 사용할 수 있게 하는 것이다. 예를 들어, `/foo/bar` 과 `foo/bar` 은 둘 다 `PATH` 나 `URL` 의 git 레포지토리의 루트에서 `foo` 의 서브 디렉토리에 포함된 `bar` 의 파일이나 디렉토리를 제외한다. 둘은 다른 것을 제외하지 않는다.

`.dockerignore` 파일의 첫 번째 줄에 `#` 로 시작하는 라인이 있다면, 그 라인은 주석으로 처리되고 CLI에 의해 처리되기 전 무시된다.

다음은 `.dockerignore` 파일의 예제이다.

```dockerfile
# comment
*/temp*
*/*/temp*
temp?
```

이 파일은 빌드할 때 다음 행동을 한다.

| Rule        | Behavior                                                     |
| ----------- | ------------------------------------------------------------ |
| `# comment` | 주석으로 인식하고 무시함                                     |
| `*/temp*`   | 루트의 서브 디렉토리 안에서 이름이 `temp` 로 시작하는 모든 파일과 디렉토리를 제외시킨다. 예를 들어 이름이 `/somedir/temporary.txt` 인 파일은 제외되고 `/somedir/temp` 의 디렉토리도 제외된다. |
| `*/*/temp*` | 루트의 두 레벨 아래 있는 서브 디렉토리 안에서 이름이 `temp`로 시작하는 파일과 디렉토리를 제외시킨다. 예를 들어 `/somedir/subdir/temporary.txt` 는 제외된다. |
| `temp?`     | 루트 디렉토리에서 이름이 `temp` 로 시작하고 뒤에 한 글자가 추가된 파일과 디렉토리를 제외시킨다. 예를 들어 `/tempa` 와 `/tempb` 는 제외된다. |

매칭은 Go 언어의 [filepath.Match](http://golang.org/pkg/path/filepath#Match) 규칙에 따라 진행된다. 전처리 단계에서는 앞이나 뒤에 오는 빈 문자를 제거하고 `.` 와 `..` 의 요소를 Go 의 [filepath.Clean](http://golang.org/pkg/path/filepath/#Clean) 을 통해 처리한다. 빈 라인은 전처리 단계에서 제거된다.

Go 의 filepath.Match 규칙에 의해 Docker는 존재하지 않거나 존재하는 모든 디렉토리들을 특정하는 특별한 와일드카드 문자열인 `**` 을 지원한다. 예를 들어, `**/*.go` 는 루트 디렉토리와 그 하위 디렉토리에서 `.go` 로 끝나는 모든 파일을 제외한다.

`!` 로 시작하는 라인은 제외되는 파일의 예외 파일이 된다. 다음 예시의 `.dockerignore` 파일은 이 메커니즘을 따른다.

```dockerfile
*.md
!README.md
```

`README.md` 를 제외한 모든 마크다운 파일은 내용물에서 제외된다.

`!` 의 제외 규칙의 존재는 `.dockerignore` 의 마지막 줄에 포함시키는 파일과 제외시키는 파일이 겹칠 때 포함시킬지 제외시킬지에 영향을 끼친다. 다음 예제를 살펴보자.

```dockerfile
*.md
!README*.md
README-secret.md
```

모든 마크다운 파일이 포함되지 않지만 README-secret.md를 제외한 README 마크다운 파일은 포함된다.

이제 다음 예제를 살펴보자.

```dockerfile
*.md
README-secret.md
!README*.md
```

모든 README 파일이 포함된다. 또한 `!README*.md` 에 두 번째 줄인 `README-secret.md` 가 포함되므로 두 번째 줄은 아무런 영향도 끼치지 않는다.

`.dockerignore` 파일은 `Dockerfile` 와 `.dockerignore` 도 제외시킬 수 있다. 이 파일들은 제외되어도 daemon이 동작하는데 필요하기 때문에 전달은 되지만 이미지를 위한 명령어인 `ADD`, `COPY` 명령어에 사용할 수는 없다.

마지막으로 특정 파일을 내용물에 제외시키는 것이 아닌 포함시켜야 할 때가 있다. 이 때는 첫 번째 패턴으로 `*` 를 사용하고 그 뒤에 `!` 를 붙여 제외시킬 패턴을 추가한다.

> ### Note
>
> 관례적으로 `.` 패턴은 무시된다.

## FROM

```dockerfile
FROM [--platform=<platform>] <image> [AS <name>]
```

또는

```dockerfile
FROM [--platform=<platform>] <image>[:<tag>] [AS <name>]
```

또는

```dockerfile
FROM [--platform=<platform>] <image>[@<digest>] [AS <name>]
```

`FROM` 명령어는 새로운 빌드 단계를 준비하고 다음 명령어들의 *기본 이미지* 를 지정한다. 따라서 유효한 `Dockerfile` 은 `FROM` 명령어로 시작해야 한다. `FROM` 의 이미지는 유효한 이미지면 어떤 이미지든 상관 없다. [*Public Repositories*](https://docs.docker.com/engine/tutorials/dockerrepos/) 에서 **이미지를 pull 해서** 사용한다면 아주 간단하다.

- `ARG` 는 `Dockerfile` 에서 `FROM` 앞에 올 수 있는 유일한 명령이다.
- `FROM` 은 `Dockerfile` 에서 여러개의 이미지를 만들기 위해 또는 하나의 빌드 단계를 다른 단계들과 독립시키기 위해 여러번 사용될 수 있다. 다른 `FROM` 명령이 실행되기 전 마지막으로 출력된 이미지 ID 커밋을 기록해두면 된다. 각각의 `FROM` 명령은 이전 명령어로 만들어진 모든 상황을 지워버린다.
- 선택적으로 `AS` 를 `FROM` 뒤에 붙여 빌드 단계에 이름을 줄 수 있다. 이름은 `FROM` 의 이어지는 다음 `FROM` 에서 `COPY --from=<name|index>` 명령과 같이 해당 단계의 이미지를 부를 때 사용할 수 있다.
- `tag` 나 `digest` 값은 선택적이다. 만약 둘 모두 생략했다면, 빌더는 `latest` 태그를 기본적으로 적용할 것이다. 만약 빌더가 `tag` 값을 찾지 못한다면 에러를 반환한다.

선택적으로 `FROM` 명령이 멀티 플랫폼 이미지를 참조할 때 `--platform` 플래그가 이미지의 플랫폼을 특정하기 위해 사용될 수 있다. 예를 들어 `linux/amd64` , `linux/arm64` , 또는 `windows/amd64` 가 있을 수 있다. 빌드에서 요구한 플랫폼을 기본으로 사용한다. 전역 빌드 인자는 이 플래그의 값으로도 사용될 수 있다. 예를 들어 자동 플랫폼 인자는 빌드 단계를 네이티브 빌드 플랫폼으로 강제할 수 있게 허용하기도 하고 크로스 컴파일을 하게 할 수도 있다.

### Understand how ARG and FROM interact

`FROM` 명령은 `FROM` 전에 `ARG` 명령어로 선언된 변수를 사용할 수 있다.

```dockerfile
ARG		CODE_VERSION=latest
FROM	base:${CODE_VERSION}
CMD		/code/run-app

FROM	extras:${CODE_VERSION}
CMD		/code/run-extras
```

`FROM` 전에 선언된 `ARG` 명령어는 빌드 단계 밖에서 실행된다. 따라서 `FROM` 전에 선언된 변수는 `FROM` 명령어 다음의 빌드 단계에서는 사용할 수 없다. 빌드 단계에서 `FROM` 전에 선언된 변수를 값을 설정하지 않은 상태에서 이름만 재정의하면 사용할 수 있다.

```dockerfile
ARG		VERSION=latest
FROM	busybox:$VERSION
ARG		VERSION
RUN		echo $VERSION > image_version
```

## RUN

`RUN` 은 두 가지 형태가 있다.

- `RUN <command>` (기본적으로 리눅스에서는 `/bin/sh` 로 실행되고 윈도우에서는 `cmd /S /C` 로 실행된다. 따라서 커맨드는 그 형식에 맞게 작성되어야 한다)
- `RUN ["executable", "param1", "param2"]` (*exec* 형식)

`RUN` 명령어는 현재 이미지의 위 새 래이어에서 실행되고 결과를 커밋한다. 커밋된 이미지의 결과물은 `Dockerfile` 의 다음 스탭에서 사용된다.

`RUN` 명령어를 레이어링하고 커밋을 생성하는 것은 소스 컨트롤처럼 자주 커밋하고 이미지의 어느 부분에서든지 새로운 파생 이미지를 만들 수 있다는 Docker의 핵심 컨셉과 일치한다.

*exec* 형식은 쉘 명령어가 멍잉(munging, wrangling) 되는 것을 방지해주고 쉘을 사용할 수 없는 기본 이미지를 사용하는 `RUN` 명령어가 동작할 수 있게 해준다.

*shell* 의 형식을 갖고 있는 기본 쉘은 `SHELL` 명령을 통해 바꿀 수 있다.

*shell* 형식에서 `\` 을 사용하면 한 줄인 `RUN` 명령어를 다음 줄로 이을 수 있다. 예를 들어 다음 두 줄을 살펴보자.

```dockerfile
RUN /bin/bash -c 'source $HOME/.bashrc; \
echo $HOME'
```

위 명령은 다음 한 줄의 명령으로 바꿀 수 있다.

```dockerfile
RUN /bin/bash -c 'source $HOME/.bashrc;	echo $HOME'
```

*'bin/sh'* 가 아닌 다른 쉘을 사용하고 싶을 때 *exec* 형식을 사용할 수 있다.

```dockerfile
RUN	["/bin/bash", "-c", "echo hello"]
```

> ### Note
>
> *exec* 형식은 JSON array에서 파생된 것이다. 따라서 단어들을 묶을 때는 작은 따옴표 ( `'` ) 가 아닌 큰 따옴표 ( `"` ) 로 묶어야 한다.

*shell* 형식과는 다르게 *exec* 형식은 명령 쉘을 통해 실행되지 않는다. 따라서 보통의 쉘의 작동이 실행되지 않는다. 예를 들어 `RUN ["echo", "$HOME"]` 은 `$HOME` 의 변수를 참조하지 못한다. 만약 쉘의 작동을 원한다면 *shell* 형식이나 쉘의 디렉토리를 `RUN ["sh", "-c", "echo $HOME"]` 와 같이 사용해야 한다. *exec* 형식을 shell 형식과 같은 방법으로 쉘 디렉토리와 사용한다면, Docker가 아닌 쉘의 환경 변수를 사용한다.

> ### Note
>
> *JSON* 형식에서 escape 문자를 사용하기 위해서는 `\` 가 필요하다. 이 내용은 백슬래시를 경로 구분자로 사용하는 윈도우 환경에서 관련이 많다. 다음 명령줄은 JSON을 사용하지 않는 *shell* 형식과는 다르게 처리되고 예상치 못한 방법으로 실행에 실패할 것이다.
>
> ```dockerfile
> RUN ["c:\windows\system32\tasklist.exe"]
> ```
>
> 위 예제에 대한 올바른 표현은 다음과 같다.
>
> ```dockerfile
> RUN ["c:\\windows\\system32\\tasklist.exe"]
> ```

`RUN` 명령의 캐시는 다음 빌드에서 자동으로 삭제되지 않는다. `RUN apt-get dist-upgrade -y` 와 같은 명령의 캐시는 다음 빌드에서 재사용하게된다. `RUN` 명령어에 의한 캐시는 `docker build --no-cache` 와 같이 `--no-cache` 플래그를 통해 삭제가 가능하다.

`RUN` 명령에 의한 캐시는 또한 `ADD` 와 `COPY` 명령어에 의해 초기화 가능하다.

[`Dockerfile` Best Practices guide](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/) 에서 자세한 정보를 확인 가능하다.

### Known issues (RUN)

- 783 이슈는 AUFS 파일 시스템에서 파일 권한과 관련해 발생하는 문제다. 파일을 제거하기 위해 `rm` 명령을 사용할 때 발생한다.

  ```null
  	최신 `dirperm1` 옵션이 추가 가능한 AUFS 버전의 시스템에서는 docker가 이 이슈를 자동으로 해결하기 위해 레이어를 `dirperm1` 옵션을 추가하며 마운팅한다. `dirperm1` 과 관련된 자세한 정보는 `aufs` man 페이지에서 확인할 수 있다.
  
  	만약 시스템이 `dirperm1` 옵션을 지원하지 않는다면, 그 이슈는 해결 방법을 제시할 것이다.
  ```

## CMD

`CMD` 명령어는 다음 세 가지 형식이 있다.

- `CMD ["executable", "param1", "param2"]` (*exec* 형식, 이 형식을 자주 사용한다)
- `CMD ["param1", "param2"]` (*기본 파라미터를 `ENTRYPOINT` 로 갖는다.*)
- `CMD command param1 param2` (*shell* 형식)

`Dockerfile` 에는 단 하나의 `CMD` 명령어가 존재할 수 있다. 만약 여러 개의 `CMD` 명령어를 사용한다면 마지막의 `CMD` 명령어만 실행될 것이다.

**`CMD` 명령의 주 목적은 실행중인 컨테이너에 기본 환경을 제공하기 위함이다.** 기본 환경에는 실행 가능한 파일을 포함하거나 올바른 `ENTRYPOINT` 명령어가 실행됐다면 포함하지 않을 수 있다.

만약 `CMD` 가 `ENTRYPOINT` 명령어로의 기본 인자를 전달하는데 사용했다면, `CMD` 와 `ENTRYPOINT` 명령어들은 `JSON` 배열 형식으로 특정되어야 한다.

> ### Note
>
> Exec 형식은 JSON 배열 형식으로 해석되므로 문자열을 작은 따옴표 (`'`) 가 아닌 큰 따옴표 (`"`) 로 감싸야 한다.

*shell* 형식과는 다르게 *exec* 형식은 커맨드 쉘을 통해 실행되지 않는다. 따라서 보통의 쉘 동작이 실행되지 않는다. 예를 들어 `CMD ["echo", "$HOME"]` 은 `$HOME` 변수를 참조하지 못한다. 만약 쉘 동작을 작동시켜야 한다면 *shell* 형식을 사용하거나 `CMD [ "sh", "-c", "echo $HOME"]` 과 같이 쉘의 디렉토리를 통해 실행해야 한다. *exec* 형식을 쉘 디렉토리를 통해 사용한다면 Docker의 환경 변수를 사용하는 것이 아닌 쉘의 환경 변수를 사용할 것이다.

*shell* 이나 *exec* 형식을 사용한다면, `CMD` 명령어는 이미지를 실행할 때 실행될 명령어를 작성할 수 있다.

만약 *shell* 형식을 `CMD` 에 사용한다면, `<command>` 는 `/bin/sh -c` 를 통해 실행 될 것이다.

```dockerfile
FROM ubuntu
CMD echo "This is a test." | wc -
```

만약 **`<command>` 를 쉘 없이 실행** 하고 싶다면 실행 파일의 전체 경로와 명령어를 JSON 배열 형식으로 표현해야 한다. **이 배열 형식은 `CMD` 의 바람직한 형식이다.** 다른 추가적인 인자들은 반드시 배열에 독립적으로 표현되어야 한다.

```dockerfile
FROM ubuntu
CMD ["/usr/bin/wc", "--help"]
```

만약 컨테이너를 매번 같은 실행파일로 실행시키고 싶다면, `ENTRYPOINT` 를 `CMD` 와 같이 사용하는 것을 고려해 볼 수 있다. 자세한 내용은 `ENTRYPOINT` 에서 확인할 수 있다.

만약 유저가 `docker run` 명령어의 매개 인자를 특정했다면 `CMD` 로 정의된 내용을 대체한다.

> ### Note
>
> `RUN` 과 `CMD` 를 혼동하지 말자. `RUN` 은 명령을 실행하고 그 결과를 커밋하는 반면 `CMD` 는 빌드 타임에서 아무 것도 실행하지 않고 이미지의 초기 명령을 특정할 뿐이다.

## LABEL

```
LABEL <key>=<value> <key>=<value> <key>=<value> ...
```

`LABEL` 명령어는 이미지에 메타데이터를 추가한다. `LABEL` 은 키-벨류 쌍이다. `LABEL` 로 세부사항을 추가하기 위해서는 따옴표와 백슬래쉬를 커맨드라인에 사용할 수 있다. 다음은 몇 가지 예시들이다.

```dockerfile
LABEL	"com.example.vendor"="ACME Incorporated"
LABEL	com.example.label-with-value="foo"
LABEL	version="1.0"
LABEL	description="This text illustrates \
that label-values can span multiple lines."
```

이미지는 하나 이상의 라벨을 가질 수 있다. 여러 개의 라벨을 하나의 라인에서 정할 수도 있다. Docker 1.10 이전 버전에서는 최종 이미지의 사이즈를 감소시켰지만 더이상 적용되는 이야기는 아니다. 여전히 여러 라벨을 한 명령구에서 정할 수 있고 다음은 여러 라벨을 정의하기 위한 두 가지 방법이다.

```dockerfile
LABEL	multi.label1="value1" multi.label2="value2" other="value3"

LABEL	multi.label1="value1" \
		multi.label2="value2" \
		other="value3"
```

라벨은 기본 혹은 상위 이미지의 라벨을 현재 이미지에 상속받는다. 만약 라벨이 다른 값으로 존재한다면 가장 최근 적용된 값이 그 전의 값을 대신하게 된다.

이미지의 라벨을 확인하기 위해 `docker image inspect` 명령어를 사용할 수 있다. 라벨을 확인하기 위해 `--format` 옵션을 사용할수도 있다.

```shell
docker image inspect --format='' myimage
{
	"com.example.vendor": "ACME Incorporated",
	"com.example.label-with-value": "foo",
	"version": "1.0",
	"description": "This text illustrates that label-values can span multiple lines.",
	"multi.label1": "value1",
	"multi.label2": "value2",
	"other": "value3"
}
```

### MAINTAINER (deprecated)

`MAINTAINER` 명령은 만들어진 이미지의 *작성자* 필드 값을 설정한다. `LABEL` 명령어는 상황에 아주 유연하고 `MAINTAINER` 대신 `LABEL` 을 사용할 수 있다. 또한 `LABEL` 명령어로 여러 필요한 메타데이터를 설정할 수 있고 `docker inspect` 같은 명령으로 쉽게 확인할 수 있다. `MAINTAINER` 필드에 맞는 라벨을 다음과 같이 설정할 수 있다.

```dockerfile
LABEL maintainer="SvenDowideit@home.org.au"
```

이 명령은 다른 라벨들과 함께 `docker inspect` 로 확인할 수 있다.

## EXPOSE

`EXPOSE` 명령은 Docker에게 컨테이너가 런타임에서 어떤 네트워크 포트에 대기중인지 알려줄 수 있다. 대기 중인 포트가 TCP 이든 UDP 이든 설정할 수 있고, 프로토콜을 따로 설정하지 않는다면 TCP가 기본으로 설정되어있다.

`EXPOSE` 명령어는 실제로 포트를 개방하지는 않는다. `EXPOSE` 는 단지 이미지를 빌드한 사람과 컨테이너를 실행하는 사람 간의 소통을 위한 문서로 활용되며 컨테이너를 실행하는 사람은 포트를 개방해야 할 것이다. 컨테이너가 실행될 때 포트를 실제로 개방하기 위해서는 `docker run` 과 `-p` 플래그를 사용하고 하나 이상의 포트와 연결시킨다. 또는 `-P` 플래그로 expose 된 모든 포트를 내림차순으로 개방할 수 있다.

기본적으로, `EXPOSE` 는 TCP를 기반으로 동작하지만 UDP를 다음과 같이 특정할 수 있다.

```dockerfile
EXPOSE 80/udp
```

TCP와 UDP로 둘 다 개방하기 위해서는 다음 두 줄이 포함되어 있어야 한다.

```dockerfile
EXPOSE 80/tcp
EXPOSE 80/udp
```

이 경우 `docker run` 을 `-P` 플래그와 사용한다면, TCP로 한 번, UDP로 한 번 개방한다. `-P` 는 호스트의 포트 중 삭제된 포트를 내림차순으로 사용한다는 것을 명심하자. 따라서 TCP와 UDP 포트는 한 번에 개방되지 않는다.

`EXPOSE` 세팅과 별개로, 그것 모두를 `-p` 플래그를 통해 런타임에서 개방할 수 있다.

```dockerfile
docker run -p 80:80/tcp -p 80:80/udp ...
```

호스트 시스템에서 포트 리디렉션을 설정하기 위해 `-P` 플래그를 사용할 수 있다. `docker network` 명령은 컨테이너간 커뮤니케이션을 위한 특정 포트 개방이 필요 없는 네트워크를 만들 수 있게 한다. 해당 네트워크는 컨테이너들끼리 서로 네트워크에 연결되어 서로의 어느 포트로든 소통할 수 있다. 자세한 정보는 [이 기능의 프리뷰](https://docs.docker.com/engine/userguide/networking/) 를 통해 확인할 수 있다.

## ENV

```dockerfile
ENV <key> <value>
ENV <key>=<value> ...
```

`ENV` 명령은 환경 변수 `<key>` 를 값 `<value>` 로 설정하는 명령어이다. 그 값은 빌드 단계에 있는 다른 하위 명령어들과 사용될 수 있고 많은 부분에서 대체될 수 있다.

`ENV` 명령은 두 가지 형식이 있다. 첫 번째 형식인 `ENV <key> <value>` 는 하나의 변수에 값을 설정한다. 첫 번째 빈칸 이후 빈칸을 포함한 모든 문자열은 `<value>` 로 처리된다. `<value>` 는 다른 환경 변수를 대체하기 때문에 따옴표 문자는 escape 된 문자가 아니라면 제거될 것이다.

두 번째 형식인 `ENV <key>=<value> ...` 는 여러 개의 변수를 한 번에 설정할 수 있다. 두 번째 형식은 첫 번째 형식과는 다르게 등호 기호를 문법에서 사용한다. 명령줄 파싱처럼, 따옴표와 백슬래시는 값에 빈 칸을 사용하기 위해 사용할 수 있다.

다음은 그 예시들이다.

```dockerfile
ENV myName="John Doe" myDog=Rex\ The\ Dog \
	myCat=fluffy
ENV	myName John Doe
ENV mydog Rex The Dog
ENV myCat fluffy
```

두 방식 모두 최종 이미지에서는 같은 결과를 갖게된다.

환경 변수는 `ENV` 명령을 통해 설정되고 컨테이너가 결과 이미지를 만들 때 까지 유지된다. `docker inspect` 를 통해 변수의 값을 확인할 수 있고 `docker run --env <key>=<value>` 를 통해 값을 바꿀 수 있다.

> ### Note
>
> 시스템 환경 유지는 예상치 못한 사이드 이펙트를 발생시킬 수 있다. 예를 들어, `ENV DEBIAN_FRONTEND noninteractive` 가 설정되어있는 상황에서 데비안 파생 이미지를 사용하는 사용자는 `apt-get` 이 불확실해질 수 있다. 하나의 명령어에 대한 변수 값을 설정하기 위해서는 `RUN <key>=<value> <command>` 를 사용할 수 있다.

## ADD

`ADD` 는 두 가지 형식을 갖고있다.

```dockerfile
ADD [--chown=<user>;<group>] <src>... <dest>
ADD [--chown=<user>;<group>] ["<src>",... "<dest>"]
```

빈 칸을 포함하는 경로는 첫 번째 형식을 사용할 수 없으므로 두 번째 형식을 사용한다.

> ### Note
>
> `--chown` 기능은 리눅스 컨테이너를 사용하는 Dockerfile 에서만 사용 가능하고 윈도우 컨테이너에서는 사용할 수 없다. 유저와 그룹 권환에 대한 개념이 윈도우와 리눅스 간에 변환될 수 없기 때문이다. 따라서 `/etc/passwd` 와 `/etc/group` 에서 추출한 유저와 그룹 이름을 ID로 사용하는 이 기능은 오직 리눅스 OS 파생 컨테이너에서만 확인할 수 있다.

`ADD` 명령어는 새 파일, 디렉토리, 리모트 파일의 URL 주소 등을 `<src>` 로 받고 이미지의 파일 시스템의 `<dest>` 에 그것들을 추가한다.

여러 개의 `<src>` 리소스가 작성될 수 있고 만약 리소스가 파일이나 디렉토리라면, 그 주소는 빌드 내용물의 소스와 관련된 것으로 인식된다.

각각의 `<src>` 는 와일드카드를 포함하고 있을 수 있고 파일 매칭은 Go 의 [filepath.Match](http://golang.org/pkg/path/filepath#Match) 규칙에 의해 진행된다. 예를 들면 다음과 같다.

"hom" 으로 시작하는 모든 파일을 추가하려면 다음과 같다.

```dockerfile
ADD hom* /mydir/
```

아래 예제에서, `?` 는 단일 문자를 대체한다. 예를 들면 "home.txt" 가 해당한다.

```dockerfile
ADD hom?.txt /mydir/
```

`<dest>` 는 절대 경로이거나 `WORKDIR` 와 연관된 상대경로이고 소스 파일들이 모두 대상 컨테이너 안으로 복사된다.

다음은 상대 경로를 사용한 예시이다. "test.txt" 를 `<WORKDIR>/relativeDir/` 에 추가한다.

```dockerfile
ADD test.txt relativeDir/
```

다음 예제는 절대 경로를 사용하고 "test.txt" 를 `/absoluteDir/` 에 추가한다.

```dockerfile
ADD test.txt /absoluteDir/
```

특수한 문자 (`[`, `]` 등) 가 포함된 파일이나 디렉토리를 추가할 때에는 매칭 패턴으로 처리되지 않게 그 경로를 Go 언어의 규칙에 따라 escape 시켜줘야 한다. 다음은 `arr[0].txt` 를 추가하는 예시이다.

```dockerfile
ADD arr[[]0].txt /mydir/
```

모든 새로운 파일과 디렉토리는 `--chown` 플래그를 통해 유저 이름과 그룹 이름을 제공하지 않았다면, UID와 GID가 0인 상태로 만들어지고 제공했다면 UID/GID가 해당 파일을 추가하기 위해 요구된다. `--chown` 플래그의 형식은 각각의 유저 이름, 그룹 이름 문자열이나 UID, GID의 번호의 조합이 필요하다. 유저 이름을 그룹 이름 없이 또는 UID를 GID 없이 플래그 값에 적용하면 GID를 UID와 같은 숫자로 적용할 것이다. 만약 유저 이름과 그룹 이름이 주어진다면, 컨테이너의 루트 파일시스템 `/etc/passwd` 와 `/etc/group` 파일은 이름과 숫자 형식의 UID, GID 를 번역하는데 사용된다. 다음 예제는 올바른 `--chown` 플래그의 사용법이다.

```dockerfile
ADD --chown=55:mygroup files* /somedir/
ADD --chown=bin files* /somedir/
ADD --chown=1 files* /somedir/
ADD --chown=10:11 files* /somedir/
```

만약 컨테이너 루트 파일시스템이 `/etc/passwd` 나 `/etc/group` 파일에 `--chwon` 플래그에 사용된 유저나 그룹의 이름이 포함되어 있지 않다면, 빌드는 `ADD` 연산자에서 에러가 발생한다. 숫자로 된 ID는 루트 파일시스템 파일을 찾아볼 필요도 없고 의존적이지도 않다.



# 최고의 Dockerfile 작성을 위한 몇 가지 습관들

출처: https://velog.io/@seheon99/%EC%B5%9C%EA%B3%A0%EC%9D%98-Dockerfile-%EC%9E%91%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%AA%87-%EA%B0%80%EC%A7%80-%EC%8A%B5%EA%B4%80%EB%93%A4

이 글에서는 효율적인 `Dockerfile` 을 만드는 방법에 대해 다룬다.

Docker 는 이미지를 `Dockerfile` 의 명령어들을 읽고 그에 맞게 자동으로 빌드한다. `Dockerfile` 은 주어진 이미지를 빌드하는데 필요한 모든 커맨드가 텍스트 형식으로 담긴 파일이다. `Dockerfile` 의 자세한 형식이나 어떤 명령어를 사용할 수 있는지는 [`Dockerfile` reference](https://velog.io/@seheon99/docs.docker.com/engine/reference/builder/) 에서 자세히 설명하고 있다.

Docker 이미지는 `Dockerfile` 명령어의 결과로 만들어진 읽기 전용의 레이어들로 구성되어 있다. 읽기 전용의 레이어이기 때문에 이전 레이어에서 변경된 내용이 있다면 다음 레이어가 되어 그 위에 쌓이게 된다. 다음 `Dockerfile` 을 살펴보자.

```dockerfile
FROM	ubuntu:18.04
COPY	. /app
RUN		make /app
CMD		python /app/app.py
```

각각의 명령어들은 각각의 레이어를 만든다.

- `FROM` 은 Docker 이미지 `ubuntu:18.04` 에서 레이어를 만든다.
- `COPY` 는 Docker 밖의 파일을 Docker 안에 추가한다.
- `RUN` 은 `make` 를 사용해 실제 응용프로그램을 빌드한다.
- `CMD` 는 컨테이너 안에서 실행할 커맨드를 지정한다.

이미지를 실행시키고 컨테이너를 만들면, 쓰기가 가능한 새로운 레이어 (컨테이너 레이어) 를 아래 레이어 위에 추가한다. 컨테이너를 실행할 때 파일을 만들거나 수정하거나 삭제하는 등의 모든 변경점들은 이 쓰기 가능한 레이어에 기록된다.

## 기본적인 가이드라인과 몇 가지 권고 사항들

### *일회용* 한 컨테이너 만들기

`Dockerfile` 에서 정의된 이미지는 가능한 가장 *일회용 (ephemeral)* 한 컨테이너를 생성해야 한다. 컨테이너가 *일회용* 하다는 것은 컨테이너를 멈추고 삭제한 후 다시 빌드하고 최소한의 몇 가지 설정을 바꾸는 것 만으로 컨테이너를 대체할 수 있다는 걸 의미한다.

### 빌드 컨텍스트에 대해 이해하기

`docker build` 커맨드를 입력하면 현재 작업중인 디렉토리를 *빌드 컨텍스트* 라고 부르게 된다. 기본적으로 `Dockerfile` 은 여기에 위치해 있다고 가정하지만 `-f` 플래그로 다른 위치를 지정해줄 수도 있다. `Dockerfile` 이 어디에 있던지 상관 없이 현재 디렉토리에 있는 모든 파일과 디렉토리는 Docker 데몬에게 빌드 컨텍스트로 전달되게 된다.

> #### 빌드 컨텍스트 예제
>
> 빌드 컨텍스트의 디렉토리를 만들고 `cd` 커맨드로 이동한다. `hello` 라는 이름의 텍스트 파일에 "hello" 라고 적고 그 파일을 `cat` 하는 `Dockerfile` 하나를 만든다. 빌드 컨텍스트 (`.`) 에서 이미지를 빌드한다.
>
> ```shell
> mkdir myproject && cd myproject
> echo "hello" > hello
> echo -e "FROM busybox\nCOPY /hello /\nRUN cat /hello" > Dockerfile
> docker build -t helloapp:v1 .
> ```
>
> `Dockerfile` 과 `hello` 를 다른 분리된 디렉토리로 옮기고 마지막 빌드 캐시에 의존하지 않는 방식으로 두 번째 버전의 이미지를 빌드한다. `-f` 플래그로 `Dockerfile` 의 위치를 설정했고 빌드 컨텍스트의 디렉토리도 설정했다.
>
> ```shell
> mkdir -p dockerfiles context
> mv Dockerfile dockerfiles && mv hello context
> docker build --no-cache -t helloapp:v2 -f dockerfiles/Dockerfile context
> ```

이미지를 빌드하는데 필요하지 않은 파일들을 추가하는 것은 빌드 컨텍스트와 이미지 크기를 키우게 된다. 또한 이미지를 빌드하는 시간, pull & push 하는 시간, 컨테이너의 런타임 크기가 모두 증가하게 된다. 빌드 컨텍스트의 크기를 확인하려면 `Dockerfile` 의 빌드할 때 표시되는 메시지에서 다음 문구를 찾으면 된다.

```shell
Sending build context to Docker daemon	187.8MB
```

### `stdin` 으로 여러 `Dockerfile` 입력하기

Docker는 `stdin` 의 파이프를 통해 여러 `Dockerfile` 을 처리할 수 있다. `Dockerfile` 을 `stdin` 으로 처리하면 디스크나 어느 곳에든지 `Dockerfile` 을 따로 작성하지 않아도 된다는 점에서 유용할 때도 있다.

> 예를 들어 다음의 두 커맨드의 결과는 같다.
>
> ```shell
> echo -e 'FROM busybox\nRUN echo "hello world"' | docker build -
> docker build -<<EOF
> FROM busybox
> RUN echo "hello world"
> EOF
> ```
>
> 상황에 따라 최적의 방법으로 빌드를 할 수 있다.

#### 빌드 컨텍스트를 보내지 않고 `stdin` 을 통한 `Dockerfile` 로 이미지 빌드하기

다음 문법을 사용하면 `stdin` 의 `Dockerfile` 로 빌드 컨텍스트 없이 이미지를 빌드할 수 있다. `-` 기호는 `PATH` 의 위치를 대신하고 Docker가 빌드 컨텍스트를 디렉토리가 아닌 `stdin` 을 통해 입력받도록 설정할 수 있다.

```shell
docker build [OPTIONS] -
```

다음 예제는 `stdin` 을 통한 `Dockerfile` 로 이미지를 빌드하고 데몬에게 어떤 파일도 빌드 콘텍스트로 보내지 않는다.

```shell
docker build -t myimage:latest -<<EOF
FROM busybox
RUN echo "hello world"
EOF
```

빌드 컨텍스트를 보내지 않는 것은 `Dockerfile` 이 어떤 파일도 필요하지 않을 때 데몬에게 아무 파일도 보내지 않으므로 빌드 속도를 향상시킨다는 측면에서 아주 유용할 수 있다.

만약 특정 파일을 빌드 컨텍스트에서 제외시키는 방법으로 빌드 속도를 향상시키고 싶다면 `.dockerignore` 에 대한 문서를 참조하면 도움이 된다.

> `Dockerfile` 로 빌드할 때, 이 문법에서는 `COPY` 또는 `ADD` 를 사용하면 오류가 발생할 것이다. 다음 예제는 이 에러가 발생한다.
>
> ```shell
> # create a directory to work in
> mkdir example
> cd example
> 
> # create an example file
> touch somefile.txt
> 
> docker build -t myimage:latest -<<EOF
> FROM busybox
> COPY samefile.txt .
> RUN cat /somefile.txt
> EOF
> 
> # observe that the build fails
> ...
> Step 2/3 : COPY somefile.txt .
> COPY failed: stat /var/lib/docker/tmp/docker-builder249218248/somefile/txt:no such file or directory
> ```

#### `stdin` 을 통한 `Dockerfile` 로 로컬 빌드 컨텍스트를 빌드하기

다음 문법을 로컬 파일 시스템의 파일을 사용해 `stdin` 을 통한 `Dockerfile` 로 이미지를 빌드할 수 있다. `-f` (또는 `--file` ) 옵션으로 사용할 `Dockerfile` 을 설정할 수 있고 `-` 을 파일 이름으로 사용해 `stdin` 을 통해 `Dockerfile` 을 전달할 수 있다.

아래 예제는 현재 디렉토리 ( . ) 를 빌드 컨텍스트로 stdin 을 통해 입력받은 Dockerfile 을 사용해 이미지를 빌드한다.

```shell
docker build [OPTIONS] -f- PATH
```

아래 예제는 현재 디렉토리 ( `.` ) 를 빌드 컨텍스트로 `stdin` 을 통해 입력받은 `Dockerfile` 을 사용해 이미지를 빌드한다.

```shell
# create a directory to work in
mkdir example
cd example

# create an example file
touch somefile.txt

# build an image using the current directory as context, and a Dockerfile passed through stdin
docker build -t myimage:latest -f- . <<EOF
FROM busybox
COPY somefile.txt .
RUN cat /somefile.txt
EOF
```

#### `stdin` 을 통한 `Dockerfile` 로 원격 빌드 컨텍스트 빌드하기

다음 문법으로 원격 `git` 레포지토리를 `stdin` 을 통한 `Dockerfile` 로 빌드할 수 있다. `-f` (또는 `--file` ) 옵션을 사용하므로써 사용할 `Dockerfile` 을 지정할 수 있고 `-` 을 파일 이름으로 사용해 `stdin` 을 통해 `Dockerfile` 을 전달할 수 있다.

```shell
docker build [OPTIONS] -f- PATH
```

이 문법은 `Dockerfile` 이 없거나 따로 설정한 `Dockerfile` 을 레포지토리에 따로 fork 할 필요 없이 레포지토리의 파일로 이미지를 빌드할 수 있다. 다음 예제는 [Github의 "hello-world" 레포지토리](https://github.com/docker-library/hello-world) 에서 `hello.c` 를 `stdin` 을 통한 `Dockerfile` 로 빌드한다.

```shell
docker build -t myimage:latest -f- https://github.com/docker-library/hello-world.git <<EOF
FROM busybox
COPY hello.c .
EOF
```

> ##### 프로그램 안에서...
>
> Git 레포지토리를 빌드 컨텍스트로 빌드할 때, Docker 는 로컬에 `git clone` 을 통해 레포지토리를 복사하고 데몬에게 빌드 컨텍스트로 넘긴다. 이 기능은 `docker build` 하는 호스트에서 `git` 의 설치가 필요하다.

### `.dockerignore` 로 파일 제외하기

빌드와 상관 없는 파일을 `.dockerignore` 을 통해 원본 레포지토리의 변경 없이 제외할 수 있다. 이 파일은 `.gitignore` 과 비슷한 방식으로 파일을 제외한다. 자세한 내용은 [.dockerignore file](https://docs.docker.com/engine/reference/builder/#dockerignore-file) 을 참고하면 도움이 될 것이다.

### 멀티-스테이지 빌드 사용하기

멀티-스테이지(multi-stage)는 중간 레이어나 파일을 줄이려고 아둥바둥거리지 않아도 최종 이미지의 크기를 효과적으로 줄일 수 있게 해준다.

이미지는 마지막 빌드 처리 과정에서 만들어지기 때문에, 빌드 캐시의 이점을 활용해 이미지 레이어를 최소화할 수 있다.

예를 들어 만약 빌드가 여러개의 레이어를 갖고 있을 때, 수정 빈도가 낮은 레이어부터 높은 순서로 빌드 캐시를 재사용할 수 있게 정렬할 수 있다.

- 응용프로그램을 빌드하는데 필요한 툴을 설치한다.
- 필요한 관련 라이브러리를 설치하거나 업데이트한다.
- 응용프로그램을 생성한다.

Go 응용프로그램을 위한 `Dockerfile` 은 다음과 같다.

```dockerfile
FROM	golang:1.11-alpine AS build

# Install tools required for project
# RUN `docker build --no-cache .` to update dependencies
RUN		apk add --no-cache git
RUN		go get github.com/golang/dep/cmd/dep

# List project dependencies with Gopkg.toml and Gopkg.lock
# These layers are only re-built when Gopkg files are updated
COPY	Gopkg.lock Gopkg.toml /go/src/project/
WORKDIR	/go/src/project/
# Install library dependencies
RUN		dep ensure -vendor-only

# Copy the entire project and build it
# This layer is rebuilt when a file changes in the project directory
COPY	. /go/src/project/
RUN		go build -o /bin/project

# This result in a single layer image
FROM	scratch
COPY	--from=build /bin/project /bin/project
ENTRYPOINT	["/bin/project"]
CMD		["--help"]
```

### 필요없는 패키지를 설치하지 말기

복잡도, 의존도, 파일 크기를 줄이고 빌드 시간을 줄이기 위해 추가적인, 필요 없는 패키지를 "있으면 좋은!" 이라는 의미로 설치하지 말아야 한다. 예를 들어 데이터베이스 이미지에 텍스트 에디터를 설치하면 안된다.

### 응용프로그램 나누기

각각의 컨테이너들은 하나의 주제가 있어야 한다. 응용프로그램을 여러 개의 컨테이너로 나누는 것은 수평적인 구조를 만들기 쉽고 컨테이너를 재활용하기 쉽게 한다. 실제로 웹 응용프로그램 스택은 각각의 고유한 이미지를 가지고 웹, 데이터베이스, 그리고 캐시를 관리하는 세 가지 컨테이너로 나뉠 수 있다.

각각의 컨테이너를 하나의 프로세스로 제한하는 것은 좋은 규칙이지만 견고하고 빠른 규칙은 아니다. 예를 들어 컨테이너가 프로세서에 의해 생성될 때, 몇 개의 프로그램도 각자의 규칙에 의해 추가적인 프로세스를 생성할 수 있다. 실제로 Celery는 여러 워커 프로세스를 생성할 수 있고, Apache도 요청에 따라 프로세스를 하나씩 생성할 수 있다.

컨테이너를 최대한 깨끗하고 모듈화 된 구조를 유지할 수 있는 최고의 판단을 해라. 만약 컨테이너가 다른 컨테이너에게 의존한다면, 컨테이너들 간 통신을 보장하기 위해 Docker 컨테이너 네트워크를 사용할 수도 있다.

### 레이어의 수 최소화 하기

Docker 의 과거버전에 빌드의 성능을 보장하기 위해 이미지의 레이어 수를 최소화 하는 것이 중요했었다. 다음 기능은 이 제한을 줄이기 위해 추가되었다.

- `RUN`, `COPY`, `ADD` 의 세 가지 명령어는 레이어를 만든다. 다른 명령어는 임시 중간 이미지를 만들고 빌드의 크기를 증가시키지 않는다.
- 가능하면 멀티-스테이지 빌드를 사용하고 최종 이미지에서 하나의 복사만 하게 되면 여러 툴과 여러 중간 빌드 스테이지에 대한 디버그 정보를 최종 이미지 크기의 증가 없이 포함할 수 있다.

### 여러 줄의 인자들 정렬하기

가능하다면 여러 줄의 인자들을 문자숫자로 정렬하는 것이 나중에 보기 편하다. 정렬을 하게 되면 중복 패키지를 피할 수 있고 리스트를 업데이트하기 쉽게 만들 수 있다. 또한 PR을 더 읽기 쉽고 리뷰하기 편하게 만들 수도 있다. 빈 칸을 `\` 전에 넣으면 더 좋다.

다음은 `buildpack-deps` 이미지에서 가져온 예시이다.

```dockerfile
RUN	apt-get update && apt-get install -y \
  bzr \
  cvs \
  git \
  mercurial \
  subversion
```

### 빌드 캐시의 영향력

이미지를 빌드할 때, Docker는 `Dockerfile` 에 있는 명령어들을 하나하나 순서대로 실행시킨다. 각각의 명령어들이 검사되기 때문에, Docker는 새로운 이미지를 만드는 대신 재사용할 수 있는 캐시 이미지가 존재하는지 확인할 수 있다.

만약 모든 캐시의 사용을 원하지 않는다면, `docker build` 커맨드에서 `--no-cache=true` 옵션을 사용할 수 있다. 그러나 만약 Docker가 캐시를 사용하게 한다면 맞는 이미지에 대한 캐시를 찾을 수 있는지 없는지 이해하는 것은 아주 중요하다. Docker의 기본적인 규칙은 다음과 같다.

- 이미 캐시에 있는 부모 이미지에서 시작했다면, 다음 명령어는 해당 이미지의 모든 자식 이미지들과 모두 같은 명령어인지 비교한다. 만약 다르다면, 그 캐시는 제외된다.
- 대부분의 경우, `Dockerfile` 의 명령어들을 자식 이미지 중 하나와 비교하는 것으로 충분할 때도 있다. 그러나 특정 명령어는 더 자세한 분석이 필요하다.
- `ADD`, `COPY` 명령어에서, 이미지의 파일들은 검사되고 각각의 파일마다 체크섬을 계산한다. 파일의 마지막 수정 시간과 마지막 접근 시간은 체크섬에 고려되지 않는다. 캐시를 찾아볼 때, 체크섬은 기존 이미지들의 체크섬과 비교된다. 만약 파일의 각각의 파일이나 메타데이터 등 어떤 내용이 변경되었다면, 그 캐시는 제외된다.
- `ADD`, `COPY` 커맨드에서, 캐시 체크는 컨테이너 안에 있는 파일은 비교해보지 않는다. 예를 들어, `RUN apt-get -y update` 커맨드를 실행할 때, 컨테이너로 업데이트된 파일은 캐시가 존재하는지 결정할 때 고려할 대상이 아니다. 이러한 경우, 커맨드 문자열만 비교하게 된다.

캐시가 제외되고나면, `Dockerfile` 의 모든 하위 명령어들은 새로운 이미지를 만들고 기존 캐시는 사용되지 않는다.

## Dockerfile 명령어

다음 권고 사항들은 `Dockerfile` 을 효과적이고 유지보수에 용이하게 만들기 위해 디자인 된 것이다.

### FROM

가능하다면 기초 이미지는 현재 공식 이미지를 사용해라. Alpine 이미지가 여전히 리눅스에 포함되는 강력하게 통제되고 5 MB 이하의 작은 크기를 갖고 있기 때문에 추천한다.

### LABEL

이미지에 라벨을 추가하여 프로젝트에 도움이 될 수 있고 라이센스 정보를 기록하여 자동화에 도움이 될 수 있고 다른 여러가지 이유로 사용할 수 있다. 각각의 라벨에는 `LABEL` 로 시작하고 하나 이상의 키-값 쌍이 뒤에 붙는다. 다음의 예제는 서로 다른 허용되는 여러가지 형식을 보여준다. 설명하는 주석이 인라인으로 포함되어 있다.

> 빈 칸을 갖고있는 문자열은 반드시 따옴표로 묶거나 빈 칸을 탈문자 시켜야 한다. 큰 따옴표 또한 탈문자 시켜야 한다.

```dockerfile
# Set one or more individual labels
LABEL	com.example.version="0.0.1-beta"
LABEL	vendor1="ACME Incorporated"
LABEL	vendor2=ZENITH\ Incorporated
LABEL	com.example.release-data="2015-02-12"
LABEL	com.example.version.is-production=""
```

하나의 이미지는 하나 이상의 라벨을 가질 수 있다. Docker 1.10 이전에는 모든 라벨이 하나의 `LABEL` 명령어 안에 들어가있는 형식을 추가적인 레이어가 발생하지 않기 때문에 권장했다. 이제 더이상 하나로 묶을 필요는 없어졌지만, 여러 라벨을 한 번에 묶는 것은 여전히 가능하다.

```dockerfile
# Set multiple labels on one line
LABEL	com.example.version="0.0.1-beta" com.example.release-data="2015-02-12"
```

위 코드는 다음과 같이 작성될 수 있다.

```dockerfile
# Set multiple labels at once, using line-continuation characters to break long lines
LABEL	vendor=ACME\ Incorporated \
		com.example.is-beta= \
		com.example.is-production="" \
		com.example.version="0.0.1-beta" \
		com.example.release-date="2015-02-12"
```

### RUN

길고 복잡한 `RUN` 문장을 `\` 를 이용해 여러 줄로 나누는 것은 `Dockerfile` 을 더 읽기 쉽고 이해하기 쉽고 수정하기 쉽게 만든다.

#### APT-GET

아마도 `RUN` 을 가장 많이 사용하는 응용프로그램은 `apt-get` 일 것이다. `apt-get` 를 통해 패키지를 설치하기 때문에 `RUN apt-get` 는 자주 보이는 커맨드이다.

업그래이드가 제한된 컨테이너에서 부모 이미지의 많은 필수 패키지를 업그래이드할 수 없기 때문에, `RUN apt-get upgrade` 와 `dist-upgrade` 를 피해야 한다. 만약 부모 이미지의 패키지가 구 버전이라면, 관리자에게 문의해야 한다. 만약 패키지 중 `foo` 라는 업데이트가 필요한 패키지가 있다면, `apt-get install -y foo` 를 사용해 자동으로 업데이트 시킬 수 있다.

`RUN apt-get update` 는 `apt-get install` 과 항상 같은 `RUN` 구문에서 같이 사용해야 한다. 예를 들어보자.

```dockerfile
RUN	apt-get update && apt-get install -y \
	package-bar \
	package-baz \
	package-foo
```

`apt-get update` 하나만 `RUN` 으로 실행하면 캐시 관련 문제를 만들고 하위 `apt-get install` 명령어가 실패한다. 예를 들어 다음과 같은 `Dockerfile` 을 살펴보자.

```dockerfile
FROM	ubuntu:18.04
RUN		apt-get update
RUN		apt-get install -y curl
```

이미지를 빌드한 후, 모든 레이어는 Docker 캐시에 있다. 나중에 `apt-get install` 에 패키지를 추가했다고 가정해보자.

```dockerfile
FROM	ubuntu:18.04
RUN		apt-get update
RUN		apt-get install -y curl nginx
```

Docker 는 초기와 그 후 수정된 명렁어를 동일하게 인식하고 이전 단계에서 발생한 캐시를 재사용한다. `apt-get update` 의 결과는 빌드가 캐시된 버전을 사용하기 때문에 실행되지 않는다. `apt-get update` 가 실행되지 않기 때문에, 해당 빌드는 구 버전의 `curl` 과 `nginx` 를 받을 수 있다.

`RUN apt-get update && apt-get install -y` 를 사용하면 `Dockerfile` 이 추가적인 코딩이나 매뉴얼 없이도 최신 버전의 패키지를 확실히 설치한다. 이 기술은 "cache busting" (부서지는 캐시) 으로 알려져있다. Cache-busting 은 패키지의 버전을 특정하므로써 처리할 수도 있다. 이 방법은 "version pinning" (버전 안전핀) 으로 알려져있다.

```dockerfile
RUN	apt-get update && apt-get install -y \
	package-bar \
	package-baz \
	package-foo=1.3.*
```

Version pinning 은 캐시와 상관 없이 특정 버전을 빌드에게 강제한다. 이 기술은 필요 패키지의 예상치 못한 변화에 의한 실패를 줄일 수 있다.

다음은 권고 사항이 잘 지켜진 `RUN` 명령어다.

```dockerfile
RUN	apt-get update && apt-get install -y \
	aufs-tools \
	automake \
	build-essential \
	curl \
	dpkg-sig \
	libcap-dev \
	libsqlite3-dev
	mercurial \
	reprepro \
	ruby1.9.1 \
	ruby1.9.1-dev \
	s3cmd=1.1.* \
 && rm -rf /var/lib/apt/lists/*
```

`s3cmd` 는 버전이 `1.1.*` 로 특정되었다. 만약 이미지가 이전에 하위 버전을 사용했다면, 새로운 버전을 사용하는 것은 `apt-get update` 의 cache bust 를 발생시키고 새 버전을 확실하게 설치한다. 패키지를 각 줄에 나열하는 것은 패키지를 중복 작성하는 것을 방지해준다.

추가적으로, `apt` 의 캐시를 삭제하고싶다면 `/var/lib/apt/lists` 를 삭제하므로써 이미지의 크기를 줄일 수 있다. `RUN` 명령어를 `apt-get update` 로 시작하므로써 패키지 캐시는 항상 `apt-get install` 전에 재설정된다.

> 공식 Debian, Ubuntu 이미지는 자동으로 `apt-get clean` 을 실행한다. 따라서 추가적인 명령이 필요 없다.

#### 파이프 사용하기

몇몇의 `RUN` 커맨드는 한 커맨드의 결과를 다른 커맨드의 입력으로 사용하는 파이프 기능에 의존하기도 한다. 파이프는 `|` 를 통해 사용할 수 있다. 다음 예제를 살펴보자.

```dockerfile
RUN	wget -O - https://some.site | wc -l > /number
```

Docker 는 위 커맨드를 파이프의 마지막 커맨드의 반환 코드로 커맨드의 실행 결과를 판단하는 `/bin/sh -c` 인터프리터로 실행시킨다. 예를 들어 위의 빌드는 `wget` 커맨드가 실패해도 `wc -l` 커맨드가 성공했다면 성공적으로 마무리되고 새로운 이미지를 만들어낸다.

만약 파이프의 어느 단계에서든지 에러를 반환할 때 커맨드의 결과를 실패로 처리하고 싶다면, `set -o pipefail &&` 를 앞에 추가하므로써 예상치 못한 오류가 발생해도 빌드를 성공했다고 처리하는 상황을 방지할 수 있다.

```dockerfile
RUN	set -o pipefail && wget -O - https://some.site | wc -l > /number
```

> ##### 모든 쉘이 `-o pipefail` 옵션을 제공하는건 아니다
>
> Debian 파생 이미지의 `dash` 쉘의 경우, `pipeshell` 옵션을 제공하는 쉘을 선택하기 위해 다음과 같은 *exec* 형식을 `RUN` 에 사용하는걸 고려해볼 수 있다.
>
> ```dockerfile
> RUN	["/bin/bash", "-c", "set -o pipefail && wget -O - https://some.site | wc -l > /number"]
> ```

### CMD

`CMD` 명령어는 이미지에 포함된 소프트웨어를 여러 매개변수와 실행하는데 사용된다. `CMD` 는 `CMD ["executable", "param1", "param2" ... ]` 과 같은 형식으로 주로 사용된다. 그러므로 만약 이미지가 Apache 나 Rails 와 같은 서비스를 위한것이라면, `CMD ["apache2", "-DFOREGROUND"]` 와 같이 실행할 수 있다. 추가적으로, 이 명령어의 형식은 모든 서비스 형식의 이미지에 추천된다.

대부분의 다른 경우에, `CMD` 는 `bash`, `python`, `perl` 과 같은 상호작용되는 쉘을 제공한다. 예를 들어, `CMD ["perl", "-de0"]`. `CMD ["python"]`, 또는 `CMD ["php", "-a"]` 과 같이 작성할 수 있다. 이 형식을 `docker run -it python` 과 같이 사용한다는 것은, 사용할 수 있는 쉘로 환경을 변경한다는 뜻이다. `CMD` 를 사용할 때, `ENTRYPOINT` 에 익숙한 사람들은 드물게 `ENTRYPOINT` 와 `CMD ["param", "param"]` 이 결합된 형식으로 사용되기도 한다.

### EXPOSE

`EXPOSE` 명령어는 컨테이너가 연결에 대기할 포드 번호를 명시한다. 따라서 보통, 응용 프로그램의 전통적인 포트 번호를 사용해야 한다. 예를 들어 Apache 웹 서버를 작동시키는 이미지는 `EXPOSE 80` 을 사용해야 하고, MongoDB 를 사용하는 이미지는 `EXPOSE 27017` 을 사용해야 한다.

외부에서 접근할 때, 유저는 `docker run` 에서 플래그로 어떻게 포트와 포트를 연결할 지 명시할 수 있다. 컨테이너 링크를 할 때, Docker 는 환경 변수를 소스에 작성해 전달할 수 있다 (`MYSQL_PORT_3306_TCP` 와 같이).

### ENV

새로운 소프트웨어를 실행하기 쉽게 만들려면, `ENV` 명령어를 해당 소프트웨어가 사용하는 `PATH` 환경 변수를 업데이트 하는데 사용할 수 있다. 예를 들어, `ENV PATH /usr/local/nginx/bin:$PATH` 는 `CMD ["nginx"]` 가 잘 작동하는걸 보장해준다.

`ENV` 명령어는 또한 Postgres 의 `PGDATA` 와 같이 컨테이너에 필수적인 환경 변수를 제공하는데 아주 유용하다.

마지막으로, `ENV` 명령어는 보통 버전 번호를 설정해 버전 충돌 관리에 자주 사용된다.

```dockerfile
ENV	PG_MAJOR 9.3
ENV	PG_VERSION 9.3.4
RUN	curl -SL http://example.com/postgres-$PG_VERSION.tar.xz | tar -xJC /usr/src/postgress && …
ENV PATH /usr/local/postgres-$PG_MAJOR/bin:$PATH
```

프로그램에 상수 변수를 갖고있는 것과 비슷하게 (값을 하드코딩하는 것을 방지하기 위해), `ENV` 명령어는 컨테이너의 소프트웨어 버전을 마법과 같이 자동으로 바꿔준다.

각각의 `ENV` 명령줄은 `RUN` 커맨드와 같이 새 중간 레이어를 만든다. 이것은 나중에 환경 변수를 지정해제하지 않는다면, 그 변수는 끝까지 레이어에 남아있고 그 값은 버려지지 않는다는 것을 뜻한다. 다음과 같은 `Dockerfile` 에서 이 내용을 테스트해볼 수 있다.

```dockerfile
FROM	alpine
ENV		ADMIN_USER="mark"
RUN		echo $ADMIN_USER > ./mark
RUN		unset ADMIN_USER
$ docker run --rm test sh -c 'echo $ADMIN_USER'

mark
```

이 것을 예방하기 위해, 그리고 환경 변수를 실제로 지정해제 하기 위해서는 `RUN` 명령어를 사용해 환경 변수를 설정하고 사용하고 지정 해제를 한 번에 할 수 있다. 커맨드를 `;` 또는 `&&` 로 나눌 수 있다. 만약 여러 커맨드를 사용하고 그 중 하나의 커맨드가 실패한다면, `docker build` 역시 실패 코드를 반환한다. 이건 보통 좋은 개념이다. `\` 를 리눅스 `Dockerfile` 에서 사용하면 가독성을 높일 수 있다. 또한 모든 커맨드를 쉘 스크립트에 넣을 수 있고 실행하기 위해 `RUN `커맨드를 사용할 수 있다.

```dockerfile
FROM	alpine
RUN		export ADMIN_USER="mark" \
		&& echo $ADMIN_USER > ./mark \
		&& unset ADMIN_USER
CMD		sh
docker run --rm test sh -c 'echo $ADMIN_USER'
```

### ADD or COPY

`ADD` 와 `COPY` 는 기능적으로 비슷하지만 보통 다들 `COPY` 를 사용해야 한다고 말한다. `ADD` 보다 더 투명하기 때문이다. `COPY` 는 로컬 파일을 컨테이너로 복사해오는 기본적인 기능만 지원하지만, `ADD` 는 즉각적으로 보이지 않는 몇 가지 기능을 더 갖고있다 (tar 파일의 압축 해제나 원격 URL의 지원과 같은). 결과적으로, `ADD` 는 `ADD rootfs.tar.xz /` 과 같이 tar 파일을 자동으로 압축 해제해 이미지에 추가하는데 사용하는 것이 가장 좋다.

만약 컨텍스트의 다른 파일을 사용하는 여러 개의 `Dockerfile` 을 갖고있다면, 한번에 `COPY` 하는 것 보다 개별적으로 `COPY` 해야 한다. 이것은 각각의 단계의 필요한 파일이 바뀌었을 때, 빌드 캐시가 무효화 (그 단계가 다시 실행되게 강제하는 것) 되는 것을 보장해준다.

```dockerfile
COPY	requirements.txt /tmp/
RUN		pip install --requirement /tmp/requirements.txt
COPY	. /tmp/
```

`COPY . /tmp/` 를 `RUN` 앞에 두었을 때 보다 결과적으로 캐시 무효화가 더 적다.

이미지 사이즈 측면에서, `ADD` 를 원격 URL 로부터 패키지를 받아오는 것은 아주 추천하지 않는 방법이다. 원격 파일 저장소로부터 패키지를 받기 위해서는 `curl` 과 `wget` 을 사용해야 한다. 그렇게 하면 이미지에 다른 레이어를 추가하지 않아서 더 이상 사용하지 않는 파일을 삭제할 수 있다. 예를 들어 다음과 같은 방법은 피해야 한다.

```dockerfile
ADD	http://example.com/big.tar.xz /usr/src/things/
RUN	tar -xJf /usr/src/things/big.tar.xz -C /usr/src/things
RUM make -C /usr/src/things all
```

대신 다음과 같은 방법을 사용할 수 있다.

```dockerfile
RUN	mkdir -p /usr/src/things \
	&& curl -SL http://example.com/big.tar.xz \
	| tar -xJC /usr/src/things \
	&& make -C /usr/src/things all
```

다른 `ADD` 의 tar 압축 해제 기능이 필요 없는 디렉토리나 파일들은 `COPY` 로 복사해야 한다.

### ENTRYPOINT

`ENTRYPOINT` 의 최고의 사용 방법은 이미지의 메인 커맨드를 설정하고 그 이미지가 그 커맨드인 것 처럼 사용하는 것이다 (`CMD` 로 기본 플래그를 설정할 수 있다).

커맨드 라인 툴인 `s3cmd` 의 이미지 예제를 살펴보자.

```dockerfile
ENTRYPOINT	["s3cmd"]
CMD	["--help"]
```

이제 이 이미지는 다음과 같은 커맨드로 `s3cmd` 의 도움 내용을 볼 수 있다.

```shell
$ docker run s3cmd
```

또는 커맨드를 실행하기 위해 파라미터를 사용할 수 있다.

```shell
$ docker run s3cmd ls s3://mybucket
```

이것은 파일 이름이 실제 프로그램으로 가는 레퍼런스의 이름으로도 사용되고 커맨드로도 사용되므로 아주 유용하다.

`ENTRYPOINT` 명령어는 또한 위 커맨드와 비슷한 방법으로 함수처럼 사용되는 것 처럼 헬프 스크립으로도 사용할 수 있다.

예를 들어 Postgres 공식 이미지는 다음과 같은 스크립을 `ENTRYPOINT` 로 사용하고 ㅇㅣㅆ다.

```sh
#!/bin/bash
set -e

if [ "$1" = 'postgres' ]; then
	chown -R postgres "$PGDATA"
	
	if [ -z "$(ls -A "$PGDATA")" ]; then
		gosu postgres initdb
	fi
	
	exec gosu postgres "$@"
fi

exec "$@"
```

> #### PID 1 로 응용프로그램 구성하기
>
> 스크립트는 `exec` Bash 커맨드를 사용하기 때문에 마지막으로 실행되는 응용 프로그램은 컨테이너의 PID 1이 된다. 이것은 응용 프로그램이 컨테이너에게 보낸 Unix 신호를 받을 수 있다는 뜻이다. 자세한 내용은 [`ENTRYPOINT` 레퍼런스](https://docs.docker.com/engine/reference/builder/#entrypoint) 를 참고해라.

헬프 스크립은 컨테이너로 복사되고 컨테이너의 시작에서 `ENTRYPOINT` 로 실행된다.

```dockerfile
COPY	./docker-entrypoint.sh/
ENTRYPOINT	["/docker-entrypoint.sh"]
CMD	["postgres"]
```

이 스크립트는 유저가 Postgres 를 여러 방법으로 사용할 수 있게 해준다.

```shell
$ docker run postgres
```

Postgres 를 실행하고 파라미터를 서버에게 보낼 수 있다.

```shell
$ docker run postgres postgres --help
```

마지막으로, Bash 와 같은 완전히 다른 툴을 실행하는데 사용될 수도 있다.

```shell
$ docker run --rm -it postgres bash
```

### VOLUME

`VOLUME` 명령어는 여러 데이터베이스 저장소 구역을 나타내고 저장소를 설정하고 파일이나 폴더를 Docker 컨테이너에 생성하기 위해 사용된다. `VOLUME` 을 이미지에서 변하기 쉽거나 유저에게 서비스 할 수 있는 부분에 사용하는걸 권장한다.

### USER

만약 서비스가 권한없이 실행될 수 있다면, `USER` 을 통해 root 가 아닌 유저로 변경해야 한다. `Dockerfile` 에서 유저를 만들고 그룹에 넣는 것은 다음과 같다.

```dockerfile
RUN groupadd -r postgres && useradd --no-log-init -r -g postgres postgres
```

> #### 고정된 UID/GID 를 고려하자
>
> 이미지의 유저와 그룹은 결정되지 않은 UID/GID 로 할당되었고 "다음" UID/GID 는 이미지의 빌드와 관련이 없다. 따라서 만약 그것이 민감한 문제라면, 고정된 UID/GID 를 고려해볼 수 있다.

> 몇몇개의 극소수의 파일에서 발생하는 Go 의 패키지 압축 버그 때문에, 엄청나게 큰 UID 를 Docker 컨테이너에 만드는 것은 컨테이너 레이어의 `/var/log/faillog` 가 널 (`\0`) 문자로 가득 차기 때문에 디스크 과부하가 올 수 있다. 이 문제를 회피하는 방법은 유저를 추가할 때 `--no-log-init` 플래그를 추가하는 것이다. Debian/Ubuntu 의 `adduser` 명령어는 이 플래그를 지원하지 않는다.

`sudo` 를 설치하거나 사용하는 등 예상하지 못하는 TTY 나 문제가 발생할 수 있는 신호가 발생하는 행동은 피해야 한다. 만약 `sudo` 와 같은 데몬을 `root` 로 초기화하지만 `root` 로 동작하지 않는 기능이 절대적으로 필요하다면, ["gosu"](https://github.com/tianon/gosu) 를 사용하는 것을 고려해보자.

마지막으로, 레이어와 복잡도를 감소시키기 위해, `USER` 를 자주 바꾸는 것 또한 피해야 한다.

### WORKDIR

투명성과 신뢰성을 위해, `WORKDIR` 로는 항상 절대경로를 줘야 한다. 또한 읽기도 힘들고 문제도 많이 생기고 유지보수도 어려운 `RUN cd .. && do-something` 같은 불어난 명령어 대신 `WORKDIR` 을 사용할 수도 있다.

### WORKDIR

### ONBUILD

`ONBUILD` 커맨드는 현제 `Dockerfile` 이 끝난 후 실행하는 명령이다. `ONBUILD` 는 `FROM` 으로 가져온 어떤 이미지에서든지 실행된다. `ONBUILD` 커맨드를 부모 `Dockerfile` 이 자식 `Dockerfile` 에게 준 명령어라고 생각하자.

Docker 빌드는 `ONBUILD` 커맨드를 모든 자식 `Dockerfile` 의 어떤 커맨드를 시작하기도 전에 실행한다.

`ONBUILD` 로 빌드된 이미지는 별도의 태그가 필요하다. 예를 들어 `ruby:1.9-onbuild` 나 `ruby:2.0-onbuild` 와 같은 것들이다.

`ONBUILD` 에서 `ADD` 나 `COPY` 를 할 떄는 주의를 해야한다. "Onbuild" 된 이미지는 만약 새 빌드 컨텍스트에 추가할 리소스가 없다면 비극적으로 실패한다. 위에서 추천한 방법으로 별도의 태그를 추가하는 것은 이 것을 누그러뜨리고 `Dockerfile` 의 작성자로 하여금 선택을 할 수 있게 해준다.

## 공식 이미지의 예제들

다음은 공식 이미지의 `Dockerfile` 예시들이다.

- [Go](https://hub.docker.com/_/golang/)
- [Perl](https://hub.docker.com/_/perl/)
- [Hy](https://hub.docker.com/_/hylang/)
- [Ruby](https://hub.docker.com/_/ruby/)

## 참고자료

- [Dockerfile Reference](https://docs.docker.com/engine/reference/builder/)
- [More about Base Images](https://docs.docker.com/develop/develop-images/baseimages/)
- [More about Automated Builds](https://docs.docker.com/docker-hub/builds/)
- [Guidelines for Creating Official Images](https://docs.docker.com/docker-hub/official_images/)