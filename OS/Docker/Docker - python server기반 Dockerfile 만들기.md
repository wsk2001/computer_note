# Docker - python server기반 Dockerfile 만들기

 **Container** 이미지를 만들 때 사용하는 **Docker** 에 대해서 알아보도록 하겠습니다.



### DockerFile 작성

먼저 DockerFile을 작성하기 전에,

간단하게 Python Server를 만들어 봅시다~!

 [Flask](https://flask.palletsprojects.com/en/1.1.x/)를 기반으로 하는 간단한 서버를 만들어볼게요

``` python
from flask import Flask

app = Flask(__name__)

@app.route('/')
def main():
    return 'Hello From Flask'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
```

간단하게 8080 port로 서버를 하나 만들어봤어요~!

해당 서버는 로컬에서도 띄울 수 있는데요,

제대로 띄우고 요청하시면~!  응답이 오는 것을 볼 수 있습니다~!

 그러면 위를 기반으로 하는 DockerFile을 작성해 볼게요~!

`DockerFile`

```shell
# python:3.9의 이미지로 부터
FROM python:3.9
# 제작자 및 author 기입
LABEL maintainer="wonsool@outlook.kr"

# 해당 디렉토리에 있는 모든 하위항목들을 '/app/server`로 복사한다
COPY . /app/server

# image의 directory로 이동하고
WORKDIR /app/server

# 필요한 의존성 file들 설치
RUN pip3 install -r requirements.txt

# 환경 설정 세팅
RUN python setup.py install

# container가 구동되면 실행
ENTRYPOINT ["python", "Server.py"]
```

