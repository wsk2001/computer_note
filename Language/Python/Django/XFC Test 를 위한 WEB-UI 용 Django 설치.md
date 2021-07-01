# XFC Test 를 위한 WEB-UI 용 Django 설치

**설치 장치**

- XFC-Notebook

- OS :  

  ``` bash
  # cat /proc/version
  Linux version 3.10.0-1160.25.1.el7.x86_64 (Red Hat 4.8.5-44) (GCC))
  
  # cat /etc/*release
  CentOS Linux release 7.9.2009 (Core)
  ```

- IP : 192.168.0.4

- account : xfc // xfc

  - ssh xfc@192.168.0.4



**설치 절차**

``` bash
# 사전 작업
wget https://kojipkgs.fedoraproject.org//packages/sqlite/3.8.11/1.fc21/x86_64/sqlite-devel-3.8.11-1.fc21.x86_64.rpm
wget https://kojipkgs.fedoraproject.org//packages/sqlite/3.8.11/1.fc21/x86_64/sqlite-3.8.11-1.fc21.x86_64.rpm
yum install -y sqlite-3.8.11-1.fc21.x86_64.rpm sqlite-devel-3.8.11-1.fc21.x86_64.rpm
sqlite3 --version
-----------------------------------------------------------------------------------------------------------

mkdir ~/PYTHON3

cd /home/xfc/PYTHON3

python3 -m venv django_venv

. django_venv/bin/activate

pip install --upgrade pip

pip install django==3.1.4
# Successfully installed asgiref-3.3.4 django-3.1.4 pytz-2021.1 sqlparse-0.4.1 typing-extensions-3.10.0.0

(django_venv) [xfc@localhost PYTHON3]$ mkdir xfcManager
(django_venv) [xfc@localhost PYTHON3]$ cd xfcManager/
(django_venv) [xfc@localhost PYTHON3]$ django-admin startproject xfcManager
(django_venv) [xfc@localhost PYTHON3]$ cd xfcManager
(django_venv) [xfc@localhost PYTHON3]$ python manage.py runserver 0.0.0.0:5000

# 다른 장치에서 확인
http://192.168.0.4:5000

```

