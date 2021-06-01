## Windows 10 에 APM 수동 설치 하기



### 1. Apache 설치 하기

#### 1.1 Download 

[https://www.apachelounge.com/download/](https://www.apachelounge.com/download/) 에 접속하여 다운로드합니다.

2018년 11월 24일 현재 Apache Version 은 `Apache 2.4.37 Win64` 로 표시 된다.

`httpd-2.4.37-win64-VC15.zip` 파일을 download 후 압축을 해제 하면 `Apache24` 폴더가 생성 되는데 `Apache24` 폴더를 `C:\`로 이동 시킨다. 최종 경로는 `C:\Apache24` 가 됩니다.



#### 1.2 환경설정

Apache 를 설치 하기 전에 **httpd.conf**  파일을 수정하여 환경을 설정 합니다. `httpd.conf` 파일은 `C:\Apache24\conf` 에 있으며 다음 부분은 수정 합니다.

```ini
Define SRVROOT "c:/Apache24"
ServerRoot "${SRVROOT}"

Listen 80

DocumentRoot "${SRVROOT}/htdocs"

```



#### 1.3 아파치 (Apache) 설치하기

```
1. 관리자 모드로 프롬프트 창을 연다.
2. cd c:\Apache24\bin
3. httpd.exe -k install
```



#### 1.4 아파치 (Apache) 웹서버 실행하기

윈도우 탐색기에서 아래 파일을 실행합니다.

```
C:\Apache24\bin\ApacheMonitor.exe
```



윈도우 오른쪽 하단 작업표시줄에 아파치 아이콘 > 마우스 우측버튼 클릭 후 아파치 모니터를 실행합니다. 아파치 모니터에서 start와 stop버튼으로 서버를 실행시키고 중지시킵니다.



#### 1.5 실행 확인

웹 브라우저를 실행하여 http://localhost/ 로 접속합니다. 아래 페이지가 뜨면 정상적으로 아파치 웹 서버 설치가 완료됩니다.  httpd.conf 에서 Listen 을 80 으로 설정 하지 않고 8080 으로 변경 한 경우는 localhost:8080 처럼 해 주어야 한다.

---
# It works!
---


### 2. PHP 설치 하기

#### 2.1. PHP Download 및 압축 풀기

http://windows.php.net/download/ 에 접속하여 PHP설치파일을 다운로드합니다.

`Binaries and sources Releases` 의 Dropdown 버튼을 눌러 자신이 원하는 Package 를 Download 합니다.

나는 `VC15 x64 Thread Safe (2018-Nov-08 07:50:12)` 를 Download 하였습니다. 최종적으로 Download 된 파일을 `php-7.2.12-Win32-VC15-x64.zip` 이고 size 는 24.6MB (25,856,954 바이트) 입니다.

다운로드된 파일을 적당한 폴더(내 경우는 C:\temp) 에서 압축을 해제 한후  `C:\php7` 으로 이동 시킵니다.

`C:\php7` 경로 아래는 `dev`, `ext`, `extras`, `lib`, `sasl2` 폴더 및 `php.exe`, `php-win.exe` 등의 여러 파일 이 있습니다.



#### 2.2 PHP.ini 파일 수정

`C:\php7` 에 있는   `php.ini-production` 를 사본을 복사후 파일명을 `php.ini` 로 변경 합니다.  그리고 변경된 `php.ini` 를 열어   `; extension_dir = "./"` 에서  `;` 를 제거 한 후 저장 합니다.

```ini
 # 변경 전
 ; extension_dir = "ext"
 
 # 변경 후
 extension_dir = "C:\php7\ext"
```

'C:\php7\ext' 를 환경 변수 path 에 등록

- <span style="color:red">변경된 php.ini 는 Apache 를 재 시작 하지 않으면 반영 되지 않기 때문에 수정이 되었으면 반드시 Apache 를 재 시작 하도록 합니다.</span>


#### 2.3 아파치(Apache) 2.4와 PHP 연동 설정 및 테스트

##### 2.3.1 연동 설정

C:\Apache24\conf\httpd.conf 파일을 열어서 DirectoryIndex 에  index.php 를 추가 합니다.

```ini
# 변경 전
<IfModule dir_module>
    DirectoryIndex index.html
</IfModule>

# 변경 후
<IfModule dir_module>
    DirectoryIndex index.php index.html
</IfModule>

```



그리고 파일의 제일 아래에 다음 을 추가 한 후 Apache  를 재 시작 한다. 

```ini
PHPIniDir "C:/php7"
LoadModule php7_module "C:/php7/php7apache2_4.dll"
AddType application/x-httpd-php .html .php
AddHandler application/x-httpd-php .php
```



##### 2.3.2 테스트

  **C:\Apache24\htdocs** 폴더에 **phpinfo.php**  를 만들고 다음 내용을 넣습니다.

```php
<?php
phpinfo();
?>
```

그리고 웹 브러우져 를 이용하여 **http://localhost/phpinfo.php** 를 호출 합니다. 브러우져 에 `PHP Version` 정보 등이 표시되면 됩니다.



### 3. MySQL 설치

#### 3.1 Download 및 압축 해제

**https://dev.mysql.com/downloads/mysql/**에 접속하여 아래 zip 파일을 다운로드합니다.

문서를 작성 하는 현재(2018.11.25) download 한 파일은 `mysql-5.7.24-winx64.zip` 입니다. **압축을 풀고 원하는 경로로 이동합니다.** 파일은 `C:\mysql` 폴더에 파일을 이동하여 설치하겠습니다. mysql-8 이 출시 되었지만 환경이 많이 달라져서 `mysql-5.7` 을 기준으로 작업 합니다.



> 위의 압축을 푼 폴더에서 **data** 폴더(C:\mysql\data)를 하나 생성합니다. 



#### 3.2 my.ini 파일 생성

압축을 해제한  `C:\mysql\` 로 이동하여 `my.ini` 파일 을 생성 한 후  다음 문장을 추가 합니다.

`my.ini`

```ini
basedir = C:/mysql
datadir = C:/mysql/data
port = 3306
```

- mysql 에서는 my.ini 가 없을 경우는 my.cnf 파일을 찾아 초기화를 수행 합니다.

<bt/>

#### 3.3 환경 변수 설정
제어판 > 시스템 > 고급 시스템 설정 > 환경 변수 클릭 > 시스템 변수 새로만들기 클릭 하여 변수 이름을 <span style="color:blue">**MYSQL_HOME**</span> 으로 지정 하고 변수 값 에는 <span style="color:blue">**C:\mysql**</span> 을 지정 합니다. 그리고 환경 변수 <span style="color:blue">**PATH**</span> 에 <span style="color:blue">**%MYSQL_HOME%\bin**</span> 를 추가 합니다.



#### 3.4. MySQL 설치하기
명령프롬프트를 **관리자 권한** 으로 실행 한 후 아래 명령어를 입력합니다.
```
mysqld --initialize
```
정상적으로 설치가 되면 아무런 표시 없이 명령프롬프트만 뜹니다.


#### 3.5. MySQL 서비스 등록하기
관리자권한의 명령프롬프트에서 C:\mysql\bin 폴더로 이동합니다. 그리고 아래 명령어를 프롬프트에 입력하고 실행합니다.
```
mysqld.exe --install
```
- 위 과정은 mysql 을 service 로 등록 하는 절차로 이후에는 service 를 실행 하여 mysql 을 시작/종료 할 수 있습니다.

##### 3.5.1 명령 프롬프트 에서 mysql 시작 종료 하기
```
# mysql 서버 시작
net start mysql

# mysql 서버 중지
net stop mysql
```

<br/>
#### 3.6. MySQL root 계정 접속 테스트
아래 명령어로 mysql에 접속합니다.
```
mysql -u root - p  (비밀번호는 그냥 Enter키를 입력합니다.)
```

##### 3.6.1 비밀번호가 입력되지 않아 Access denied for ~  ERROR 1045 가 발생한 경우

명령프롬프트에 아래 명령어를 입력하고 실행합니다.

```
net stop mysql
mysqld --skip-grant-tables
```

- 위의 명령어를 실행하면 프롬프트가 계속 실행된 상태로 있습니다.

관리자 권한으로 명령프롬프트창을 새로 하나 더 열어서 mysql경로로 접속합니다.

```cd c:\mysql\bin
cd c:\mysql\bin
mysql -u root - p  (비밀번호는 그냥 Enter키를 입력합니다.)
mysql> use mysql;
mysql> update user set authentication_string = password('root') where user = 'root';  
mysql> SET PASSWORD = PASSWORD('root');
mysql> quit;
```

- mysql 을 재 시작 하면 바뀐 password 를 이용하여 mysql 에 접속 할 수 있습니다.
- mysql 재 시작은 관리자 명령 프롬프트 에서 `services.msc` 를 입력 후 MySQL 을 재시작 하거나, 관리자 명령 프롬프트 에서 `net stop mysql`, `net start mysql` 명령을 차레로 실행 하여 재 시작 할 수 있습니다.

참조사이트 : <http://bombay.tistory.com/3>

---

#### 3.7 MySQL과 PHP를 연동

설치된 MySQL과 PHP를 연동하기 위해서는 php가 설치된 폴더에 **php.ini** 파일을 열어서 아래를 수정합니다.

```ini
# 변경 전
;extension=mysqli
;extension=pdo_mysql

# 변경 후
extension=php_mysqli.dll
extension=php_pdo_mysql.dll
```

- ini 파일이 수정 되었으면 아파치 웹 서버를 재시작합니다.



#### 3.8 연동 테스트

C:\Apache24\htdocs\mysqltest.php 에 아래의 파일을 작성하고 저장 합니다.

```php
<?php
echo "MySql Connecttion Test<br>";

$db = mysqli_connect("localhost", "root", "root", "mysql");
if($db){echo "connect : Success<br>";}
else{echo "disconnect : Failure<br>";}

$result = mysqli_query($db, 'SELECT VERSION() as VERSION');
$data = mysqli_fetch_assoc($result);

echo $data['VERSION'];

?>
```

그리고 웹 브라우져 에서 http://localhost/mysqltest.php 를 호출하여 테스트를 진행 합니다.

테스트 결과는 다음과 같습니다.

```
MySql Connecttion Test
connect : Success
5.7.24
```



이상으로 Apache, PHP, MySQL 수동 설치 및 연동 테스트 를 마칩니다.



 ### - 끝 -





