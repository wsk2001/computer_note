# Linux 에서 script 를 이용한 daemon 실행 및 종료 방법



### 1. java App 을 background 로 실행 한다.

`run.sh`

``` bash
if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi

file_path="test_daemon.pid"

if [ -e "$file_path" ]; then
    echo "Already running."
    echo "Restart after executing the stop.sh command."
    exit
else
    :
fi

java -version

nohup java -Duser.timezone=GMT+09:00 -Dlogback.configurationFile=../config/logback.xml -jar aaa.jar start &

echo $! > $file_path
```

위 run.sh 를 실행 하면 file_path 로지정된 test_daemon.pid 에 실행 시킨 app 이 저장 된다.



### 2. background 로 실행 App 을 종료 한다.

`stop.sh`

``` bash
if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi

file_path="test_daemon.pid"

if [ -e "$file_path" ]; then
    :
else
    echo "File $file_path does not exist."
    exit
fi

pid=`cat $file_path`
echo "Kill process java(remote-agent = $pid)"

if ps -p $pid > /dev/null; then
    kill -9 $pid
else
    echo "Process with PID $pid is not running."
fi

rm -f $file_path
```

위 stop.sh 를 실행 하면 실행중인 App 을 종료 시킨다.



예제 에서는 root user 인 경우 에만 script 를 실행 하도록 제한 하는 방법을 추가 하였다.

