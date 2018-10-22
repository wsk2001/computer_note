### Linux 특정 process 의 memory 사용량 check



Linux 에서 특정 process 의 memory 사용량 check 하는 방법<br/>



#### 1.  process 의 pid 를 확인 한다.

```
ps -ef | grep test_process | grep -v grep | awk '{print $2}'
```
<br/>
- 결과 값이 12345 로  나왔다면 다음과 같이 실행 한다.
<br/>


```
ch_mem.sh 12345
```
<br/>


아래는 ch_mem.sh  스크립트 

```
#!/bin/bash 

usage(){
	echo "Usage: $0 pid"
	exit 1
}

if [ $# -ne 1 ] ; then
    usage
fi

while : 
do 
	cat /proc/$1/status | grep VmSize
	date
	
	sleep 5 
done
```
<br/>


- 5 초에 한번씩 test_process 의 memory 사용량을 display 한다.
