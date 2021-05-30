# CentOS7 에 Django 설치 및 테스트

본 문서는 CentOS7 에 Django 를 설치하고 테스트 하는 과정을 기록한 문서이다.

WIndows10 에 WSL 로 CentOS7 을 설치 하고 WSL 에서 작업한 과정을 기록 한다.

WSL 에는 기본적으로 root 로 login 이 되며 필요시 계정을 생성 할 수 있다.

WSL 용 CentOS7 은 https://github.com/yuk7/CentWSL#centwsl 에서 download 하여 설치 한다.



yum groupinstall "Development Tools"
yum install -y epel-release
yum install -y python3

mkdir -p /root/workspace/DJANGO
cd /root/workspace/DJANGO

python3 -m venv venv
source venv/bin/activate
-- 반드시 Django==2.1 로 지정 하여 설치 한다.
-- 2.2 버전부터는 SQLite 3.8.3 이상 버전을 사용해야하며, 사용하지 않을 경우 오류가 발생

pip install Django==2.1
pip install --upgrade pip
django-admin startproject potal
cd potal
python3 manage.py migrate
python3 manage.py runserver

http://127.0.0.1:8000/

