# AIX 서비스 시작 정지.



aix에서 현재 실행중인 서비스를 보려면,

```
# lssrc -a
Subsystem Group PID Status
syslogd ras 17294 active
sendmail mail 14738 active
portmap portmap 14210 active
......
i4llmd iforls inoperative
dtsrc inoperative
wsmrefserver inoperative
```



sendmail이 현재 active상태이다. 필요에 의해서 sendmail서비스를 정지하려면,

```
# stopsrc -s sendmail
0513-044 The sendmail Subsystem was requested to stop.
# lssrc -a | grep -i sendmail
sendmail mail inoperative>
```



다시 실행하려면,

```
# startsrc -s sendmail
0513-059 The sendmail Subsystem has been started. Subsystem PID is 16026.

# lssrc -a | grep -i sendmail
sendmail mail 16026 active
```



자, 문제는 ftp나 telnet 같은 서비스는 lssrc에서 보이지 않는다는 것이다. ftp나 telnet같은 inetd의 제어를 받는 서비스는 -ls 옵션을 주어서 볼 수 있다.

```
# lssrc -a | grep -i inet
inetd tcpip 15484 active

# lssrc -ls inetd
Subsystem Group PID Status
inetd tcpip 15484 active

Debug Not active

Signal Purpose
SIGALRM Establishes socket connections for failed services.
SIGHUP Rereads the configuration database and reconfigures services.

SIGCHLD Restarts the service in case the service ends abnormally.

Service Command Description Status
wsmserver /usr/websm/bin/wsmserver wsmserver -start active
ttdbserver /usr/dt/bin/rpc.ttdbserver rpc.ttdbserver 100083 1 active
cmsd /usr/dt/bin/rpc.cmsd cmsd 100068 2-5 active
dtspc /usr/dt/bin/dtspcd /usr/dt/bin/dtspcd active
time internal active
daytime internal active
daytime internal active
ntalk /usr/sbin/talkd talkd active
exec /usr/sbin/rexecd rexecd active
klogin /usr/sbin/krlogind krlogind active
login /usr/sbin/rlogind rlogind active
kshell /usr/sbin/krshd krshd active
telnet /usr/sbin/telnetd telnetd -a active
ftp /usr/sbin/ftpd ftpd active
```



ftp 서비스를 정지시키려면, /etc/inetd.conf 에서 ftp관련 설정을 찾아 주석처리(#)한다.

```
# grep -i ftp /etc/inetd.conf
#ftp stream tcp6 nowait root /usr/sbin/ftpd ftpd
```



그리고, inetd를 재 실행해주거나, 간단하게 refresh해주면 된다.

```
# refresh -s inetd
0513-095 The request for subsystem refresh was completed successfully.

# lssrc -ls inetd
Subsystem Group PID Status
inetd tcpip 15484 active

Debug Not active

Signal Purpose
SIGALRM Establishes socket connections for failed services.
SIGHUP Rereads the configuration database and reconfigures services.

SIGCHLD Restarts the service in case the service ends abnormally.

Service Command Description Status
wsmserver /usr/websm/bin/wsmserver wsmserver -start active
ttdbserver /usr/dt/bin/rpc.ttdbserver rpc.ttdbserver 100083 1 active
cmsd /usr/dt/bin/rpc.cmsd cmsd 100068 2-5 active
dtspc /usr/dt/bin/dtspcd /usr/dt/bin/dtspcd active
time internal active
daytime internal active
daytime internal active
ntalk /usr/sbin/talkd talkd active
exec /usr/sbin/rexecd rexecd active
klogin /usr/sbin/krlogind krlogind active
login /usr/sbin/rlogind rlogind active
kshell /usr/sbin/krshd krshd active
telnet /usr/sbin/telnetd telnetd -a active
```

lssrc로 확인해보면, ftp가 없어진 것을 볼 수 있다.