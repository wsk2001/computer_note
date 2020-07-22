# TEST용 Data file 생성

가끔 필요에 의해 Test 용 Data 파일을 생성 할 필요가 있습니다. 이때 사용하면 좋을 파일생성 방법 입니다.



### Linux

``` bash
time for num in `seq 0 100`; do dd if=/dev/urandom  of=sample-${num} bs=1024k count=5; done
```

* 종료 후 Duration Time 이 표시됩니다.



### Windows

`create_files.bat`

``` bat
@ECHO OFF
REM ###############################################
REM Create Test Files
REM ###############################################

REM SETlocal EnableDelayedExpansion
SETLOCAL EnableDelayedExpansion

SET "startTime=%time: =0%"

SET kilo=1024
SET mega=1048576
SET giga=107374182400
SET tera=1099511627776

REM ###############################################
SET /A file_size=%mega% * 5
SET loop_cnt=100
SET file_path=C:\tmp
REM ###############################################

REM ###############################################
REM CREATE FILES
REM ###############################################
FOR /L %%x IN (1, 1, %loop_cnt%) DO (
  fsutil file createnew %file_path%\data_%%x.dat %file_size%
)

SET "endTime=%time: =0%"

REM get elapsed time
SET "end=!endTime:%time:~8,1%=%%100)*100+1!"  &  SET "start=!startTime:%time:~8,1%=%%100)*100+1!"
SET /A "elap=((((10!end:%time:~2,1%=%%100)*60+1!%%100)-((((10!start:%time:~2,1%=%%100)*60+1!%%100), elap-=(elap>>31)*24*60*60*100"


REM convert elapsed time to hh:mm:ss:cc format
SET /A "cc=elap%%100+100,elap/=100,ss=elap%%60+100,elap/=60,mm=elap%%60+100,hh=elap/60+100"

ECHO. 
ECHO Start:    %startTime%
ECHO End:      %endTime%
ECHO Elapsed:  %hh:~1%%time:~2,1%%mm:~1%%time:~2,1%%ss:~1%%time:~8,1%%cc:~1%
ECHO. 

```



#### 끝

