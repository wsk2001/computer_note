# Centos7 서버에 Tibero 6 설치

출처: https://kamsi76.tistory.com/entry/TIBERO-Centos7-%EC%84%9C%EB%B2%84%EC%97%90-Tibero-6-%EC%84%A4%EC%B9%98



**1.Tmaxsoft 홈페이지에서 파일과 데모라이선스를 신청한다.**

 다운로드 경로 : https://technet.tmaxsoft.com/ko/front/download/findDownloadList.do?cmProductCode=0301

![img](https://t1.daumcdn.net/cfile/tistory/992BDB375E54A17224)





**2. 사전 패키지 설치 확인 및 설치**

\* 아래의 해당 버전 이상의 패키지가 설치되어 있어야 한다.

> [Packages]
> gcc-3.4.6.-11
> gcc-c++-3.4.6.-11
> libgcc-3.4.6.-11
> libstdc++-3.4.6-11
> libstdc++-devel-3.4.6-11
> compat-libstdc++-33-3.2.3-47.3
> libaio-0.3.105-2
> libaio-devel-0.3.105-2



**1.Tmaxsoft 홈페이지에서 파일과 데모라이선스를 신청한다.**

 다운로드 경로 : https://technet.tmaxsoft.com/ko/front/download/findDownloadList.do?cmProductCode=0301

![img](https://t1.daumcdn.net/cfile/tistory/992BDB375E54A17224)





**2. 사전 패키지 설치 확인 및 설치**

\* 아래의 해당 버전 이상의 패키지가 설치되어 있어야 한다.

> [Packages]
> gcc-3.4.6.-11
> gcc-c++-3.4.6.-11
> libgcc-3.4.6.-11
> libstdc++-3.4.6-11
> libstdc++-devel-3.4.6-11
> compat-libstdc++-33-3.2.3-47.3
> libaio-0.3.105-2
> libaio-devel-0.3.105-2



**3. 환경설정 관련 사항**

\- 설정파일 /etc/sysctl.conf 제일 하단에 다음 값을 추가한다.

> kernel.shmmni = 4096
> kernel.shmall = 2097152
> kernel.shmmax = 2147483648
> kernel.sem = 10000 32000 10000 10000
>
> fs.file-max = 6815744
>
> net.ipv4.ip_local_port_range = 1024 65500

\- shell Limit 파라미터 설정을 위해 /etc/security/limits.conf 파일 제일 하단에 다음 값을 추가한다.

> tibero      soft  nproc      2047
> tibero      hard  nproc      16384
> tibero      soft  nofile     1024
> tibero      hard  nofile     65536

\- /etc/sysyemd/logind.conf 파일의 **RemoveIPC=no** 의 주석을 풀어 준다.



**4. Tibero를 설치한다.**

Tmaxsoft에 다운받은 파일과 라이센스 파일을 서버로 옮긴다.

\- 설치하고자 하는 위치에 다운로드 파일 압축을 해제한다.

\# tar -zxvf tibero6-bin-FS07_CS_1902-linux64-166256-opt.tar.gz



\- 환경변수 설정

```
export TB_HOME=/home/tibero/Tibero/tibero6
export TB_SID=tibero
export LD_LIBRARY_PATH=$TB_HOME/lib:$TB_HOME/client/lib
export PATH=$PATH:$TB_HOME/bin:$TB_HOME/client/bin
```



**
\5. $TB_HOME/config/gen_tip.sh 파일 실행**



**6. Tibero 서버를 NOMOUNT 모드로 시작**

\# tbboot nomount

```
Change core dump dir to /home/tibero6/tibero6/bin/prof.
Listener port = 8629

Tibero6

TmaxData Corporation Copyright (c) 2008-. All rights reserved.
Tibero instance started up (NOMOUNT mode).
```



**7. tbsql 을 통해 데이터베이스 접속**

최초 sys 계정의 비밀번호는 **tibero**로 설정되어 있다.

```
# tbsql sys/tibero

tbSQL 6

TmaxData Corporation Copyright (c) 2008-. All rights reserved.

Connected to Tibero.

SQL>
```



**8. 데이터베이스 생성.**

원할 시 다른 명칭으로 생성해도 된다. 다음 예제는 tibero로 생성했다.

```
SQL> create database "tibero" 
  user sys identified by tibero 
  maxinstances 8 
  maxdatafiles 100 
  character set MSWIN949 
  national character set UTF16 
  logfile 
    group 1 'log001.log' size 100M, 
    group 2 'log002.log' size 100M, 
    group 3 'log003.log' size 100M 
  maxloggroups 255 
  maxlogmembers 8 
  noarchivelog 
    datafile 'system001.dtf' size 100M autoextend on next 100M maxsize unlimited 
    default temporary tablespace TEMP 
      tempfile 'temp001.dtf' size 100M autoextend on next 100M maxsize unlimited 
      extent management local autoallocate 
    undo tablespace UNDO 
      datafile 'undo001.dtf' size 100M autoextend on next 100M maxsize unlimited 
      extent management local autoallocate;
Database created.

SQL> quit
Disconnected.
```



**9. 데이터베이스 생성 후 tbboot 명령어로 NORMAL 모드로 재기동한다.**

```
# tbboot
Change core dump dir to /home/tibero6/tibero6/bin/prof.
Listener port = 25010

Tibero 6  

TmaxData Corporation Copyright (c) 2008-. All rights reserved.
Tibero instance started up (NORMAL mode).
```



**10. $TB_HOME/scripts 디렉토리의 system.sh 를 실행한다.** 

해당 파일은 현재 디렉토리에 있는 SQL파일을 수행하여 role, system user, view, package 등을 생성한다. 실행 시 sys 및 syscat 계정에 대한 비밀번호를 물어 보는데 sys는 tibero, syscat는 syscat로 기본 설정되어 있다.

```
# ./system.sh 
Enter SYS password:

Enter SYSCAT password:

Creating the role DBA...
create default system users & roles?(Y/N):

Creating system users & roles...
Creating virtual tables(1)...
Creating virtual tables(2)...
Granting public access to _VT_DUAL...
Creating the system generated sequences...
Creating internal dynamic performance views...
Creating outline table...
Creating system package specifications:
    Running /home/tibero/Tibero/tibero6/scripts/pkg/pkg_standard.sql...
    Running /home/tibero/Tibero/tibero6/scripts/pkg/pkg_dbms_output.sql...
    Running /home/tibero/Tibero/tibero6/scripts/pkg/pkg_dbms_lob.sql...
    Running /home/tibero/Tibero/tibero6/scripts/pkg/pkg_dbms_utility.sql...
    ......중간 생략......

Creating spatial meta tables and views ...
Creating internal system jobs...
Creating internal system notice queue ...
Done.
For details, check /home/tibero6/tibero6/instance/tibero/log/system_init.log.
```



**11. Tibero 정상 동작 여부 확인**

```
# ps -ef | grep tbsvr
tibero   19981     1  0 21:12 pts/2    00:00:00 tbsvr         ... 
tibero   19983 19981  0 21:12 pts/2    00:00:00 tbsvr_TBMP    ...
tibero   19984 19981  0 21:12 pts/2    00:00:00 tbsvr_WP000   ...
tibero   19985 19981  3 21:12 pts/2    00:00:00 tbsvr_WP001   ...
tibero   19986 19981  1 21:12 pts/2    00:00:12 tbsvr_WP002   ...
tibero   19987 19981  2 21:12 pts/2    00:00:12 tbsvr_PEP000   ...
tibero   19988 19981  0 21:12 pts/2    00:00:00 tbsvr_AGNT    ...
tibero   19989 19981  1 21:12 pts/2    00:00:00 tbsvr_DBWR    ...
tibero   19999 19981  0 21:12 pts/2    00:00:00 tbsvr_RECO    ...  
```

