# Windows 10의 WSL 에서 LEMP 스택 구성하기

출처: [여기](https://acaroom.net/ko/blog/sean/windows-10%EC%9D%98-wsl-ubuntu-1804%EC%97%90%EC%84%9C-lemp-%EC%8A%A4%ED%83%9D-%EA%B5%AC%EC%84%B1%ED%95%98%EA%B8%B0)

Windows 10의 WSL (Ubuntu 20.04)에서 Nginx, MySQL, PHP 7.4을 설치하고 새로운 Drupal9의 개발 환경을 구성해 보려고 합니다.



## 1. 설치

### 1.1 리눅스 배포판의 갱신

먼저 리눅스 배포판을 갱신하고 업데이트 합니다.

```bash
sudo apt-get update && sudo apt-get upgrade
```



### 1.2 Nginx의 설치와 실행

```bash
sudo apt install nginx
sudo service nginx start
```

이제 리눅스나 윈도우의 인터넷 브라우저를 띄우고 [http://localhost(link is external)](http://localhost) 를 접근하면 Nginx Welcome 페이지를 볼 수 있습니다. 윈도우에서 접근이 가능한 이유는 WSL을 위한 Hyper-V Virtual Ethernet Adapter를 사용하고 있기 때문이죠.



### 1.3 MySQL 서버의 설치와 실행

```bash
sudo apt install mysql-server
sudo usermod -d /var/lib/mysql/ mysql
sudo service mysql start
```

작성 시점(2020-10-04)에서 설치 시 문제가 발생합니다. WSL의 버그로 보여지며 이것을 해결하기 위해서는 아래 [Troubleshooting](https://acaroom.net/ko/blog/sean/windows-10의-wsl-ubuntu-1804에서-lemp-스택-구성하기#Troubleshooting)에서 확인합니다.

mysql의 root에는 기본적인 패스워드가 없습니다. 패스워드 설정을 다음과 같이 진행합니다.

```bash
sudo mysql_secure_installation  ## 패스워드 설정

## root 로 설정 했음
```

이때 VALIDATE PASSWORD PLUGIN을 최초에 물어보는데 no를 하고 패스워드를 입력합니다. 로컬 환경에는 필요 없기 때문이죠. 😏



### 1.4 PHP 7.4의 설치

Drupal 9은 최소한 PHP 7.4버전의 환경을 요구합니다. 다음과 같이 설치합니다.

```bash
sudo add-apt-repository ppa:ondrej/php
sudo apt update
sudo apt install php7.4 php7.4-fpm php7.4-mysql php7.4-curl php7.4-json php7.4-mbstring php7.4-xml php7.4-bcmath
sudo service php7.4-fpm start
```



## 2. 서버의 설정

### 2.1 모든 서버의 시작

이제 필요한 준비가 완료 되었습니다. 필요시 다음과 같은 내용을 ~/.bashrc나 ~/.zshrc에 넣어 터미널 시작 시 서버를 시작시킬 수 있습니다.

```bash
sudo /etc/init.d/mysql start
sudo /etc/init.d/php7.4-fpm start
sudo /etc/init.d/nginx start
```

### 2.2 모든 서버의 종료

```bash
sudo /etc/init.d/nginx stop
sudo /etc/init.d/php7.4-fpm stop
sudo /etc/init.d/mysql stop
```



### 2.2 Nginx 서버의 설정

PHP-FPM서비스를 사용할 수 있도록 다음과 같이 수정합니다.

```bash
sudo vi /etc/nginx/sites-available/default
# port 번호를 8080 으로 바꾼다.

#변경 전
server {
        listen 80 default_server;
        listen [::]:80 default_server;
      
#변경 후
server {
        listen 8080 default_server;
        listen [::]:8080 default_server;        
```

그리고 다음 내용을 찾아서 변경합니다.

```bash
server {
        index index.php index.html index.htm index.nginx-debian.html;

...
        location ~ \.php$ {
                include snippets/fastcgi-php.conf;

                # Make sure unix socket path matches PHP-FPM configured path above
                fastcgi_pass unix:/run/php/php7.4-fpm.sock;

                # Prevent ERR_INCOMPLETE_CHUNKED_ENCODING when browser hangs on response
                fastcgi_buffering off;
        }
...
}
```

서버를 다시 시작합니다.

```bash
sudo service nginx restart
```

다음과 같이 PHP 정보를 띄우도록 테스트 합니다.

```bash
sudo vi /var/www/html/info.php

<?php
phpinfo();
?>
```

이제 info.php를 인터넷 브라우저에서 실행해봅니다.

```
http://127.0.0.1:8080/info.php
```





## 3. LEMP의 디버깅

이제 서버에서 LEMP가 뿌리는 로그를 다음 파일을 통해 확인하면서 디버깅 할 수 있습니다.

```bash
MySQL: /var/log/mysql/error.log
Nginx: /var/log/nginx/error.log
PHP: /var/log/php7.4-fpm.log
```

PHP-FPM은 성능을 이유로 로깅을 위한 설정이 기본적으로 비활성화 상태일 수 있습니다. 다음 파일에서 활성화 합니다.

```bash
sudo vi /etc/php/7.4/fpm/pool.d/www.conf
   catch_workers_output = yes  ## 코멘트 해제


sudo nano /etc/php/7.2/fpm/php-fpm.conf
   log_level = notice  ## 로그레벨 범위를 지정

## 설정을 적용하기 위해 재시작
sudo service php7.4-fpm reload
```

 

## 4. Troubleshooting

### 4.1 캐시 생성 에러

WSL을 사용하는 경우 myql을 시작하려고 할 때 다음과 같은 에러 발생

```bash
╰─❯ sudo service mysql start
 * Starting MySQL database server mysqld
No directory, logging in with HOME=/
mkdir: cannot create directory ‘//.cache’: Permission denied
```

다음과 같이 해결합니다.

```bash
## /etc/profile.d/wsl-integration.sh 파일 편집
# Check if we have HOME folder
if [ "${HOME}" = "/" ]; then
  return
fi


WSL_INTEGRATION_CACHE=$HOME/.cache/wslu/integration`
...
```

### 4.2 포트 에러

가끔 시작하지 못하는 경우 윈도우에서 이미 mysql이 구동중일 수 있습니다. 윈도우 혹은 리눅스의 포트번호를 변경합니다.

### 4.3 유닉스 소켓 에러

Ubuntu-18.04의 WSL에서는 다음과 같은 에러가 발생할 수 있습니다.

```bash
Cannot open /proc/net/unix: No such file or directory
```

`/var/log/mysql/error.log` 를 살펴보면 WSL에서는 AIO 인터페이스가 지원되지 않는 경우를 확인할 수 있습니다.

/etc/mysql/conf.d/no_native_aio.conf을 생성하고 다음과 같이 작성해 둡니다.

```bash
[mysqld]
innodb_use_native_aio = 0
```

이제 mysql을 구동시켜 봅니다.

```bash
sudo service mysql start
```

 

### 4.4 ERROR 1698 (28000): Access denied for user 'root'@'localhost'

참고: [https://stackoverflow.com/questions/39281594/error-1698-28000-access-den...(link is external)](https://stackoverflow.com/questions/39281594/error-1698-28000-access-denied-for-user-rootlocalhost)

WSL에서는 UNIX 소켓 구현에 버그가 있는 듯 합니다. 따라서 mysql의 auth_socket을 기본적으로 인증을 사용하고 있는데 다음과 같이 변경해야 합니다.

```bash
$ sudo mysql -u root # 최초에는 sudo를 사용해 접근해야 함

mysql> USE mysql;
mysql> SELECT User, Host, plugin FROM mysql.user;

+------------------+-----------------------+
| User             | plugin                |
+------------------+-----------------------+
| root             | auth_socket           |
| mysql.sys        | mysql_native_password |
| debian-sys-maint | mysql_native_password |
+------------------+-----------------------+

mysql> UPDATE user SET plugin='mysql_native_password' WHERE User='root';
mysql> FLUSH PRIVILEGES;
mysql> exit;

$ service mysql restart
$ sudo mysql_secure_installation  ## 암호를 다시 설정하기
```



## 5. TIP

### 5.1 WSL 에서 netstat 사용하기

#### 5.1.1 문제 상황

2018년 4월 기준으로 WSL의 문제로 netstat 명령은 올바르게 동작하지 않는다. 그 예는 다음과 같다.

``` bash
$ netstat -an
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address           Foreign Address         State
Active UNIX domain sockets (servers and established)
Proto RefCnt Flags       Type       State         I-Node   Path
```

어떠한 내용도 표시되지 않음이 확인된다.

#### 5.1.2 조치

WSL의 리눅스 배포판 내에서는 윈도우용 응용 프로그램의 실행을 허용하기 때문에 윈도우의 NETSTAT.EXE를 alias로 가져와서 사용할 수 있다.

``` bash
$ echo "alias netstat='/mnt/c/Windows/System32/NETSTAT.EXE'" >>$HOME/.profile
$ . $HOME/.profile
```

실제로 netstat가 올바르게 동작하는지 확인한다.

``` bash
$ netstat -an | grep 5432
  TCP    127.0.0.1:5432         0.0.0.0:0              LISTENING
```

옵션과 일부 사용법의 경우 윈도우의 NETSTAT와 리눅스의 netstat 간에 상이한 부분이 있을 수 있다.



### 끝 

