# Python Django CMD wagtail 설치

git 저장소: https://github.com/wagtail/wagtail



## 설치

설치 명령 (# 으로 되어 있는 부분은 설명을 위한 부분 이므로 실제 입력 하지는 않는다.)

``` shell
python3 -m venv wagtail              # 가상 환경  생성
source wagtail/bin/activate          # 가상 환경 활성화
pip install --upgrade pip            # package 관리자 upgrade

pip install wagtail                  # wagtail package 설치
wagtail start cms                    # wagtail 을 이용해 cms site 를 생성. cms 는 다른이름(musite) 으로 변경가능
cd cms                               #
pip install -r requirements.txt      # 종속성 모듈 설치
python manage.py migrate             # django migration
python manage.py createsuperuser     # django cms 관리자 생성
	Username (leave blank to use 'wonsool'): wonsool # 관리자 계정
	Email address: wsk2001@gmail.com                 # 관리자 email
	Password:                                        # 관리자 password : kang0192
	Password (again):
	Superuser created successfully.
python manage.py runserver	         # cms server 실행

```



## 테스트 (내 notebook)

- WSL Terminal 획득

- alias 로 등록되어 있는 cms 실행

  alias 로 등록되어 있는 cms 실행
   /mnt/c/home/docker/django로 이동된다.

- source wagtail/bin/activate  실행

  python 가상 환경 획득

- app 실행

  ```
  $> cd cms
  $> python manage.py runserver
  ```

- 접속

  http://127.0.0.1:8000/admin/

  wonsool // kang0192



## Package 분석

