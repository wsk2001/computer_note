# Windows 관리자 권한 으로 프로그램 실행 시키기

Windows 에서 일부 프로그램은 관리자 권한으로 실행 시키지 않으면 오류가 발생 하는 경우가 있다.

무의적으로 일반 User 의 권한을 획득 한후 프로그램이 오동작 하는 오류를 막기 위해서 프로그램 실행 전 관리자 권한을 Check 하는 방법이 필요하다.

다음은 관리자 권한으로 프로그램을 실행 하는지 Check 후 관리자 권한이 아니면 실행 하지 않도록 하는 batch script 이다.

`admin_run.bat`

```
@echo off

net session > NUL 2>&1
IF %ERRORLEVEL% EQU 0 (
    adminprog.exe -p C:\data_mirror -m C:\data
) ELSE (
    ECHO you are NOT Administrator. Exiting...
    EXIT /B 1
)
```

위의 batch 프로그램은 관리자 권한으로 실행 시키면 `adminprog.exe` 가 실행 되지만 아닌경우  `you are NOT Administrator. Exiting...` message 를 보여주고 종료 한다.



##### 주의: 시스템(SYSTEM) 권한과 관리자(Administrator)  권한은 다름



#### - 끝 -

