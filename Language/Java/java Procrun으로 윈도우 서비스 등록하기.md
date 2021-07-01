# java Procrun으로 윈도우 서비스 등록하기

출처: http://gnujava.com/board/article_view.jsp?board_no=5&article_no=7116



**1. procrun window용 binary 파일 다운**

[Daemon – Download Apache Commons Daemon](http://commons.apache.org/proper/commons-daemon/download_daemon.cgi)

http://commons.apache.org/daemon/procrun.html

http://commons.apache.org/daemon/binaries.html

http://www.apache.org/dist/commons/daemon/binaries/ 에서 windows 디렉토리의 

최신버전 commons-daemon-X.X.X-bin-windows.zip 를 받아 임의 디렉토리에 압축 해재



**2. C:\procrun 에 압축 해재**

  c:\procrun\prunsrv.exe 파일명을 원하는 이름으로 변경 

​    \- ex) FtpServiceLauncher.exe

​    \- 서비스 실행시 프로세스 목록에  FtpServiceLauncher 로 실행됨(javaw나 java가 아님)



**3. 디렉토리 구성**

  c:\procrun\bin

  c:\procrun\lib

  c:\procrun\log

  \- 디렉토리 추가하고 lib 디렉토리에 의존관계가 있는 모든 jar를 복사

  \- bin 디렉토리에 이름을 바꾼 prunsrv.exe를 이동, bin하위에 각 OS 별로 amd64, ia64를 이름을 바꿔 이동.



**4. 위도우 서비스 등록을 위해 intall.bat 파일 생성하여 아래 내용과 같이 입력**

> @echo off
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem Window Service 설치
>
> rem JDK 1.6 버젼 이상 필요
>
> rem install.bat로 설치된 위치에서 설치 위치 변경시 반듯이 uninstall.bat를 실행하여 서비스 제거뒤 다시 변경된 디렉토리에서 install.bat로 재 설치 해주어야 함
>
> rem 설치 디렉토리 : 현재 디렉토리를 설치 위치로 사용 
>
> rem  ※ 한글 디렉토리명을 사용하면 않됨
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- Window Service 등록 ID
>
> set "SERVICE_ID=ServiceName"
>
> rem ---------- Window Service에 노출되는 이름, "~~" 로 묵인 것에 주의
>
> set "SERVICE_NAME=Service Name"
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 설치할 디렉토리, 설치위치에 c:\procrun를 이동시킨뒤 실행해야 함
>
> set INSTALL_DIR=%cd%
>
> rem ---------- 64bit OS인 경우 prunsrv.exe 를 해당 설치할 OS에 맞는 것으로 사용, 보통 32bit용도 잘 작동함
>
> set INSTALLER=%INSTALL_DIR%\bin\amd64\FtpServiceLauncher.exe
>
> rem ---------- 의존관계가 있는 모든 패키지
>
> set LIB_DIR=%INSTALL_DIR%\lib
>
> rem ---------- 서비스로 구동될 java 프로그램 패키지
>
> set ADAPTER_JAR=%INSTALL_DIR%\service-0.0.1-SNAPSHOT.jar
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> set CLASS_PATH=%ADAPTER_JAR%
>
> set CLASS_PATH=%CLASS_PATH%;%LIB_DIR%\*
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 실행 옵션
>
> rem ---------- OS에 설치된 JDK 지정 (JAVA_HOME, window registry 등에서 조회하는 것으로 보임)
>
> set "PARAMS=--Jvm=auto"
>
> rem ---------- Window Service 자동실행으로 지정
>
> set "PARAMS=%PARAMS% --Startup=auto"
>
> rem ---------- 구동할 java 프로그램 패키지 내의 실행 Class 및 실행 파라미터
>
> rem ---------- 실행 파라미터가 여려개인경우 ; 로 구분자를 주어 지정
>
> set "PARAMS=%PARAMS% --StartMode=jvm --StartClass=com.piccom.service.ServiceLauncher --StartParams=start;properties=%INSTALL_DIR%\ftp.properties"
>
> rem ---------- 구동할 java 프로그램 패키지 내의 중지 Class 및 중지 파라미터
>
> set "PARAMS=%PARAMS% --StopMode=jvm --StopClass=com.piccom.service.ServiceLauncher --StopParams=stop"
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem --------- 로그 옵션
>
> rem --------- 로그 파일 위치를 변경하여 사용 가능함, 상세한 로그를 원할경우 LogLevel을 debug로 변경
>
> rem --------- 옵션을 사용하지 않을 경우 file로 로그 생성하지 않음
>
> set "LOG_PARAMS=--LogPath=%INSTALL_DIR%\log --LogLevel=debug"
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 파일 로그 없는 실행문
>
> rem %INSTALLER% //IS//%SERVICE_ID% --DisplayName="%SERVICE_NAME%" --Install=%INSTALLER% --Classpath=%CLASS_PATH% %PARAMS%
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 파일 로그 남기는 실행문
>
> %INSTALLER% //IS//%SERVICE_ID% --DisplayName="%SERVICE_NAME%" --Install=%INSTALLER% --Classpath=%CLASS_PATH% %PARAMS% %LOG_PARAMS%
>
> rem ---------------------------------------------------------------------------

**5. 설치된 서비스 삭제 처리용 uninstall.bat 작성**

> @echo off
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem Window Service 설치
>
> rem JDK 1.6 버젼 이상 필요
>
> rem install.bat로 설치된 위치에서 설치 위치 변경시 반듯이 uninstall.bat를 실행하여 서비스 제거뒤 다시 변경된 디렉토리에서 install.bat로 재 설치 해주어야 함
>
> rem 설치 디렉토리 : 현재 디렉토리를 설치 위치로 사용 
>
> rem  ※ 한글 디렉토리명을 사용하면 않됨
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- Window Service 등록 ID
>
> set "SERVICE_ID=ServiceName"
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 설치할 디렉토리
>
> set INSTALL_DIR=%cd%
>
> rem ---------- 64bit OS인 경우 prunsrv.exe 를 해당 설치할 OS에 맞는 것으로 사용, 보통 32bit용도 잘 작동함
>
> set INSTALLER=%INSTALL_DIR%\bin\amd64\FtpServiceLauncher.exe
>
> rem ---------------------------------------------------------------------------
>
> 
>
> rem ---------------------------------------------------------------------------
>
> rem ---------- 실행문
>
> %INSTALLER% //DS//%SERVICE_ID%
>
> rem ---------------------------------------------------------------------------



**6. java source**

  \- main() 내용

  \- 실재 실행되는 FtpService는 Thread의 구현채이고 serviceStart() 호출시 Thread.start()를 실행

>   public static void main(String[] args) {
>
> ​    Properties properties = new Properties();
>
> ​    java.io.FileInputStream fis = null;
>
> ​    String mode = null;
>
> ​    for(int i=0;args != null && i < args.length; i++){
>
> ​      if(args[i].startsWith("properties=")){
>
> ​        java.io.File file = new java.io.File(args[i].replace("properties=", ""));
>
> ​        fis = new java.io.FileInputStream(file);
>
> ​        properties.load(fis);
>
> ​      }else if(args[i].startsWith("start")){
>
> ​        mode = args[i];
>
> ​      }else if(args[i].startsWith("stop")){
>
> ​        mode = args[i];
>
> ​      }
>
> ​    }
>
> ​    if ("start".equals(mode)){
>
> ​      
>
> ​      FtpService.instance = new FtpService();
>
> ​      FtpService.instance.setProperties(properties );
>
> ​      FtpService.instance.serviceStart();
>
> ​      
>
> ​    }else if("stop".equals(mode)){
>
> ​      
>
> ​      FtpService.instance.serviceEnd();
>
> ​    }
>
>   }

**7. install.bat를 실행 한뒤 아래 문장으로 서비스 실행을 체크**

c:\procrun\bin\amd64\FtpServiceLauncher.exe //TS//ServiceName