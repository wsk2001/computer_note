# CentOS 7 에 Nginx 설치 하는 방법

출처: https://cofs.tistory.com/412

CentOS7 에 Nginx 설치 방법에 대해서 설명한다. yum을 활용하여 쉽게 설치해 보자.

### 요약

1. yum 외부 저장소 추가
2. yum install
3. 방화벽 포트 개방
4. nginx 포트 설정
5. nginx 데몬 실행
6. 데몬 실행시 오류 발생 (오류 없으면 생략)
7. 실행



### 1. yum 외부 저장소 추가

yum 저장소에는 nginx가 없기 때문에 외부저장소를 추가해야 한다.

``` shell
vi /etc/yum.repos.d/nginx.repo
```

/etc/yum.repos.d 경로에 nginx.repo 파일을 추가하고 내용은 다음과 같이 작성한다.

``` shell
[nginx]
name=nginx repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=0
enabled=1
```

위 내용은 공식사이트에 있으며, OS가 다르다면 해당 OS에 맞게 수정해주면 됨.  CentOS7은 위와 같이 작성하면 됨.



### 2. yum install

``` shell
yum install -y nginx
```

yum install 명령어를 사용하여 nginx를 설치한다.



### 3. 방화벽 포트 개방

``` shell
firewall-cmd --permanent --zone=public --add-port=8089/tcp
firewall-cmd --reload
firewall-cmd --list-ports
```

차례대로 포트 개방, 방화벽 재시작, 개방된 포트 목록 확인이다. 필자는 8089번 포트를 개방하고 nginx에서 사용할 계획이다.



### 4. nginx 포트 설정

nginx 설정파일의 Listen의 포트 80을 8089로 변경한다.

``` shell
vi /etc/nginx/conf.d/default.conf
...
listen 80;  ->  listen 8089;
```



### 5. nginx 데몬 실행

설치와 포트설정이 모두 끝났으므로 nginx 데몬을 실행한다.

``` shell
systemctl start nginx
systemctl enable nginx
```

서버 부팅시 자동으로 nginx 서비스를 구동하기 위해서 systemctl enable 명령어를 실행한다. 필요없다면 생략 가능함.



### 6. 데몬 실행시 오류 발생 (오류 없으면 생략)

데몬 실행 시 다음과 같은 오류가 발생할 수 있다.

``` shell
Job for nginx.service failed because the control process exited with error code. See "systemctl status nginx.service" and "journalctl -xe" for details.
```

이럴땐 다음 명령어로 오류의 상세 정보를 확인한다.

``` shell
journalctl -xe
```

**오류 상세 정보**

``` shell
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has finished shutting down.
10월 21 11:20:00 192.168.nate.com polkitd[687]: Unregistered Authentication Agent for unix-process:11231:22076 (system bus name :1.33, object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8) (disconnected from bus)
10월 21 11:23:43 192.168.nate.com polkitd[687]: Registered Authentication Agent for unix-process:11256:44452 (system bus name :1.35 [/usr/bin/pkttyagent --notify-fd 5 --fallback], object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8)
10월 21 11:23:43 192.168.nate.com systemd[1]: Starting nginx - high performance web server...
-- Subject: Unit nginx.service has begun start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has begun starting up.
10월 21 11:23:43 192.168.nate.com systemd[1]: PID file /var/run/nginx.pid not readable (yet?) after start.
10월 21 11:23:43 192.168.nate.com systemd[1]: Started nginx - high performance web server.
-- Subject: Unit nginx.service has finished start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has finished starting up.
-- 
-- The start-up result is done.
10월 21 11:23:43 192.168.nate.com polkitd[687]: Unregistered Authentication Agent for unix-process:11256:44452 (system bus name :1.35, object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8) (disconnected from bus)
10월 21 11:23:49 192.168.nate.com polkitd[687]: Registered Authentication Agent for unix-process:11265:44983 (system bus name :1.36 [/usr/bin/pkttyagent --notify-fd 5 --fallback], object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8)
10월 21 11:23:49 192.168.nate.com systemd[1]: Stopping nginx - high performance web server...
-- Subject: Unit nginx.service has begun shutting down
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has begun shutting down.
10월 21 11:23:49 192.168.nate.com systemd[1]: Stopped nginx - high performance web server.
-- Subject: Unit nginx.service has finished shutting down
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has finished shutting down.
10월 21 11:23:49 192.168.nate.com polkitd[687]: Unregistered Authentication Agent for unix-process:11265:44983 (system bus name :1.36, object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8) (disconnected from bus)
10월 21 11:24:01 192.168.nate.com polkitd[687]: Registered Authentication Agent for unix-process:11276:46211 (system bus name :1.37 [/usr/bin/pkttyagent --notify-fd 5 --fallback], object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8)
10월 21 11:24:01 192.168.nate.com systemd[1]: Starting nginx - high performance web server...
-- Subject: Unit nginx.service has begun start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has begun starting up.
10월 21 11:24:01 192.168.nate.com nginx[11282]: nginx: [emerg] bind() to 0.0.0.0:8089 failed (13: Permission denied)
10월 21 11:24:01 192.168.nate.com systemd[1]: nginx.service: control process exited, code=exited status=1
10월 21 11:24:01 192.168.nate.com systemd[1]: Failed to start nginx - high performance web server.
-- Subject: Unit nginx.service has failed
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit nginx.service has failed.
-- 
-- The result is failed.
10월 21 11:24:01 192.168.nate.com systemd[1]: Unit nginx.service entered failed state.
10월 21 11:24:01 192.168.nate.com systemd[1]: nginx.service failed.
10월 21 11:24:01 192.168.nate.com polkitd[687]: Unregistered Authentication Agent for unix-process:11276:46211 (system bus name :1.37, object path /org/freedesktop/PolicyKit1/AuthenticationAgent, locale ko_KR.UTF-8) (disconnected from bus)
```

8089포트가 권한이 없다고 하는 내용인데 SElinux 사용 시 포트 등록이 안되어 있어서 발생하는 오류이다. semanage로 포트를 등록해 주자.

``` shell
semanage port -a -t http_port_t -p tcp 8089
```

포트를 등록했으면 다시 nginx 데몬을 실행한다.

``` shell
systemctl start nginx
systemctl enable nginx
```



### 7. 실행

```
http://127.0.0.1:8089
```

정상적으로 페이지가 출력되면 끝 ~

