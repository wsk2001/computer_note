# **Apache Commons Daemon** 을 이용한 Java프로그램 실행



출처: https://www.javabullets.com/procrun-java-programs-as-windows-services/



# procrun – Java Programs as Windows Services

최근에 Java 프로그램을 Windows 서비스로 실행해야했고 Commons-daemon procrun을 선택했습니다. 이 래퍼는 Tomcat과 JBoss Wildfly 모두에서 서버를 래핑하는 데 사용되지만 내 애플리케이션을 실행하는 방법을 알아내는 데 약간의 시간이 걸렸습니다.

이 게시물은 procrun을 사용하여 Java 프로세스를 래핑하는 예제를 설정합니다.



# Download

[다운로드](http://www.apache.org/dist/commons/daemon/binaries/windows/)에서 procrun을 다운로드했습니다. 다운로드에는 3 가지 버전의 procrun.exe가 포함되어 있습니다.

- 32 bit – 이것이 기본 아키텍처입니다.
- amd64 – 64 비트 AMD 아키텍처
- ia64 – 64 비트 Intel Itanium 아키텍처

JVM 및 칩셋에 적합한 버전을 사용해야합니다.



# Code

코드는 oracle의 [EchoServer](http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java) 및 [EchoClient](http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java) 예제를 기반으로합니다.



## EchoServer

``` java
import java.net.*;
import java.io.*;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);
         
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
```

## EchoClient

클라이언트는 종료 매개 변수를 사용하도록 변경됩니다.

``` java
import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 3) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String shutdown = args[2];
 
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            if (shutdown != null && !"".equals(shutdown)) {
                userInput = shutdown;
            }
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
```

## Prunssrv

또한 서버를 중지하고 시작하는 간단한 클래스를 만들었습니다.

``` java
import java.net.*;
import java.io.*;

public class Prunssrv {
    public static void prunsrvStartServer(String[] args) throws Exception {
        String[] newArgs = new String[1];
        newArgs[0] = System.getProperty("prunsrv.port"); // -Dprunsrv.port=8080

        EchoServer.main(newArgs); 
    }
    
    public static void prunsrvStopServer(String[] args) throws Exception {
        String[] newArgs = new String[2];
        newArgs[0] = System.getProperty("prunsrv.server"); // -Dprunsrv.server=localhost
        newArgs[1] = System.getProperty("prunsrv.port"); // -Dprunsrv.port=8080
        newArgs[1] = "shutdown";
        
        EchoClient.main(newArgs);
    }
}
```

함께 모아서 -

- 위의 클래스와 procrun.exe를 디렉토리 – C:\procrun에 추가하십시오.
- **Compile:** javac *.java
- **Create Archive:** jar -cvf simpleechoserver.jar *.class



# service.bat

service.bat 파일을 만들 필요는 없지만 더 깔끔하고 간단합니다. 이것을 코드 디렉토리에 저장하십시오.

``` bat
@echo off
setlocal

set SERVICE_NAME=SimpleEchoServer

set PR_INSTALL=%~dp0%prunsrv.exe
set PR_DESCRIPTION="Simple Echo Server Service"

REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=%~dp0%\
set PR_STDOUTPUT=%~dp0%\stdout.txt
set PR_STDERROR=%~dp0%\stderr.txt
set PR_LOGLEVEL=Debug
 
REM Path to java installation
set PR_JVM=%JAVA_HOME%\jre\bin\server\jvm.dll
set PR_CLASSPATH=simpleechoserver.jar
 
REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=Prunssrv
set PR_STARTMETHOD=prunsrvStartServer
 
REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=Prunssrv
set PR_STOPMETHOD=prunsrvStopServer
set PR_STOPTIMEOUT=120
 
REM JVM configuration
set PR_JVMMS=256
set PR_JVMMX=1024
set PR_JVMSS=4000

REM JVM options
set prunsrv_port=8080
set prunsrv_server=localhost

set PR_JVMOPTIONS=-Dprunsrv.port=%prunsrv_port%;-Dprunsrv.server=%prunsrv_server%

REM current file
set "SELF=%~dp0%service.bat"
REM current directory
set "CURRENT_DIR=%cd%"
 
REM start - This takes the input from installService and places it between x's
REM       - if there are none then you get xx as a null check
if "x%1x" == "xx" goto displayUsage
set SERVICE_CMD=%1
REM ahift moves to next field
shift
if "x%1x" == "xx" goto checkServiceCmd
:checkServiceCmd
if /i %SERVICE_CMD% == install goto doInstall
if /i %SERVICE_CMD% == remove goto doRemove
if /i %SERVICE_CMD% == uninstall goto doRemove
echo Unknown parameter "%SERVICE_CMD%"
:displayUsage
echo.
echo Usage: service.bat install/remove
goto end
:doRemove
echo Removing the service '%PR_INSTALL%' '%SERVICE_NAME%' ...
%PR_INSTALL% //DS//%SERVICE_NAME%
if not errorlevel 1 goto removed
echo Failed removing '%SERVICE_NAME%' service
goto end
:removed
echo The service '%SERVICE_NAME%' has been removed
goto end
:doInstall
echo Installing the service '%PR_INSTALL%' '%SERVICE_NAME%' ...
%PR_INSTALL% //IS//%SERVICE_NAME% 
goto end
:end
echo Exiting service.bat ...
cd "%CURRENT_DIR%"
```



## Key Points

모든 Procrun 필드는 PR_로 표시되어 있습니다. procrun 노트에서 ++ 또는--표기법을 사용하여 이러한 필드를 procrun.exe에 직접 공급할 수도 있습니다.

- 핵심은 start / stop 필드입니다.

- PR_JVMOPTIONS – 시스템 속성을 Windows 서비스에 전달할 수 있습니다.

- Installing and removing –

  %PR_INSTALL% //IS//%SERVICE_NAME%

  %PR_INSTALL% //DS//%SERVICE_NAME%

- 메모에 정의 된 다른 '//'옵션이 있습니다.



# Running service.bat

관리자 권한으로 실행해야 할 수도 있습니다.

``` cmd
C:\procrun>service.bat

Usage: service.bat install/remove
Exiting service.bat ...
```

To install –

``` cmd
service.bat install
```

And uninstall –

``` cmd
service.bat remove
```

그런 다음 테스트 할 수 있습니다.

``` cmd
java EchoClient localhost 8080
hello
echo: hello
...
```

Windows 서비스로 이동하면 이제 **중지/시작/다시 시작** 옵션이있는 SimpleEchoServer가 표시됩니다.



# prunmgr.exe

마지막 트릭은 prunmgr을 사용하는 것입니다. 이것은 procrun 관리자이며 procrun 작동 매개 변수를 볼 수 있습니다. 시작하려면 prunmgr.exe 사본으로 이동하여 이름을 바꾸거나 배치 파일의 SERVICE_NAME에 복사하십시오.

``` cms
set SERVICE_NAME=SimpleEchoServer
```

So –

``` cmd
C:\procrun>copy prunmgr.exe SimpleEchoServer.exe
```

그런 다음 SimpleEchoServer.exe를 관리자로 실행합니다.

![simpleechoserver](https://i2.wp.com/www.javabullets.com/wp-content/uploads/2016/10/simpleechoserver.png?w=418&ssl=1)

# Reference

https://commons.apache.org/proper/commons-daemon/procrun.html



---

## **Apache Commons Daemon** 을 이용한 Java프로그램 실행



출처: https://commons.apache.org/proper/commons-daemon/procrun.html

## Introduction

Procrun은 Windows 사용자가 (대부분) Java 응용 프로그램 (예 : Tomcat)을 Windows 서비스로 래핑 할 수 있도록하는 응용 프로그램 집합입니다.

이 서비스는 컴퓨터가 부팅 될 때 자동으로 시작되도록 설정할 수 있으며 컴퓨터에 로그온 한 사용자없이 계속 실행됩니다.



## Procrun monitor application

Prunmgr은 procrun 서비스를 모니터링하고 구성하기위한 GUI 응용 프로그램입니다.

각 명령 줄 지시문은 **//XX[//ServiceName]** 형식입니다.

**//ServiceName** 매개 변수를 생략하면 서비스 이름이 파일 이름으로 간주됩니다.
Prunsrv 응용 프로그램은 동일한 방식으로 작동하므로 두 응용 프로그램이 동일한 디렉토리에 상주 할 수 있도록 Prunmgr 응용 프로그램은 이름에서 후행 w (소문자 w)를 제거합니다.
예를 들어 Prunmgr 응용 프로그램의 이름이 TestService.exe 또는 TestServicew.exe로 변경된 경우 기본 서비스 이름은 TestService입니다.

사용 가능한 명령 줄 옵션은 다음과 같습니다.

| 구분 | 요약 | 설명 |
| :--- | -------------------------- | ------------------------------------------------------------ |
| **//ES** | 서비스 구성 수정 | 이것이 기본 작업입니다. 옵션이 제공되지 않으면 호출됩니다. 서비스 구성을 수정, 시작 및 중지 할 수있는 GUI 애플리케이션을 시작합니다. |
| **//MS** | 서비스 모니터링    | GUI 애플리케이션을 시작하고 시스템 트레이로 최소화합니다. |
| **//MR** | 서비스 모니터링 및 실행 | GUI 애플리케이션을 시작하고 시스템 트레이로 최소화합니다. 현재 실행 중이 아니면 서비스를 시작합니다. |
| **//MQ** | 모니터 종료         | 서비스에 대해 실행중인 모니터를 중지합니다. |



## Procrun service application

Prunsrv는 응용 프로그램을 서비스로 실행하기위한 서비스 응용 프로그램입니다. <span style="color:red">Java 애플리케이션뿐만 아니라 모든 애플리케이션을 서비스로 실행되도록 변환 할 수 있습니다.</span>

### Command line arguments

각 명령 줄 지시문은 **//XX[//ServiceName]** 형식입니다.

**//ServiceName** 매개 변수를 생략하면 서비스 이름이 파일 이름으로 간주됩니다.
예를 들어 응용 프로그램의 이름이 TestService.exe로 변경된 경우 기본 서비스 이름은 TestService입니다.

사용 가능한 명령 줄 옵션은 다음과 같습니다.

| 구분                | 요약                              | 설명                                                       |
| :------------------ | --------------------------------- | ---------------------------------------------------------- |
| **//TS**            | 콘솔 애플리케이션으로 서비스 실행 | 이것이 기본 작업입니다. 옵션이 제공되지 않으면 호출됩니다. |
| **//RS**            | 서비스 실행                       | ServiceManager에서만 호출                                  |
| **//ES**            | 서비스 시작 (실행)                |                                                            |
| **//SS**            | 서비스 중지                       |                                                            |
| **//US**            | 서비스 매개 변수 업데이트         |                                                            |
| **//IS**            | 서비스 설치                       |                                                            |
| **//DS**            | 서비스 삭제                       | 현재 실행중인 경우 먼저 서비스를 중지합니다.               |
| **//PS**            | 서비스 설정값 인쇄                | 현재 구성을 (다시) 만들기위한 명령을 인쇄합니다.           |
| **//PP[//seconds]** | 일시 중지                         | 기본값은 60 초입니다.                                      |
| **//VS**            | 버전 인쇄                         | 버전 인쇄 및 종료 (버전 1.0.3 이후)                        |
| **//?**             | 사용법 인쇄                       | 사용법 인쇄 및 종료 (버전 1.0.3부터)                       |

버전 1.0.8부터는 command [ServiceName] 형식으로보다 전통적인 명령 줄을 사용할 수 있습니다.

| 구분            | 요약                              | 설명                                                         |
| :-------------- | --------------------------------- | ------------------------------------------------------------ |
| run             | 콘솔 애플리케이션으로 서비스 실행 | 이것이 기본 작업입니다. 옵션이 제공되지 않으면 호출되며 **//TS** 호출과 동일한 효과를 갖습니다. |
| service         | 서비스 실행                       | ServiceManager에서만 호출                                    |
| start           | 서비스 시작                       | **//ES** 의 동의어                                           |
| stop            | 서비스 중지                       | **//SS** 의 동의어                                           |
| update          | 서비스 매개 변수 업데이트         | **//US** 의 동의어                                           |
| install         | 서비스 설치                       | **//IS** 의 동의어                                           |
| delete          | 서비스 삭제                       | 현재 실행중인 경우 먼저 서비스를 중지합니다.                 |
| print           | 서비스 설정값 인쇄                | 현재 구성을 (다시) 만들기위한 명령을 인쇄합니다.             |
| pause [seconds] | 일시 중지                         | 기본값은 60 초입니다.                                        |
| version         | 버전 인쇄                         | 버전 인쇄 및 종료                                            |
| help            | 사용법 인쇄                       | 사용법 인쇄 및 종료                                          |

### Command line parameters

각 명령 매개 변수는-(또는 ++, 아래 참조)로 시작됩니다.
명령 줄 매개 변수와 이름이 같지만 PR_ 접두사가 붙은 환경 변수가 있으면 동등한 명령 줄 매개 변수를 재정의합니다.
예를 들면 :

``` cmd
set PR_CLASSPATH=xx.jar
```

제공하는 것과 동일합니다

``` cmd
--Classpath=xx.jar
```

명령 줄 매개 변수로.

매개 변수가 반복되면 일반적으로 마지막 값이 우선합니다. 그러나 일부 매개 변수는 여러 값을 가질 수 있습니다 (예 : StartParams 및 JvmOptions). 이 매개 변수가 ++로 시작되면 값이 기존 값에 추가됩니다. 예를 들면 :

``` cmd
--Startup=manual --Startup=auto --JvmOptions=-Done=1 ++JvmOptions=-Dtwo=2
```

다음 값이 사용됩니다.

``` cmd
Startup:
auto

JvmOptions:
-Done=1
-Dtwo=2
```

다중 값 매개 변수만 이를 지원합니다. 아래 표에 ++로 표시되어 있습니다.

다중 값을 지원하지 않는 매개 변수에 ++가 사용되면-와 동일하게 처리됩니다. 오류가보고되지 않습니다.
-사용되는 경우 구성을 덮어 씁니다. 예를 들면 :

``` cmd
++JvmOptions=-Dthree=3 ++JvmOptions=-Dfour=2
```

다음 값이 사용됩니다.

``` cmd
Startup:
auto

JvmOptions:
-Done=1
-Dtwo=2
-Dthree=3
-Dfour=4
```

++ 및--옵션을 혼용하는 경우 마지막--매개 변수로 인해 옵션이 재설정됩니다. 예를 들면 :

``` cmd
--Startup=manual --Startup=auto --JvmOptions=-Done=1 ++JvmOptions=-Dtwo=2 --JvmOptions=-Dthree=3 ++JvmOptions=-Dfour=2
```

다음 값이 사용됩니다.

``` cmd
Startup:
auto

JvmOptions:
-Dthree=3
-Dfour=4
```

서비스 (// US 또는 업데이트 명령)를 업데이트 할 때--를 사용하면 기존 매개 변수가 새 설정으로 바뀝니다.
다중 값 매개 변수의 경우 ++ 옵션 한정자를 사용하면 기존 값에 새 값이 추가됩니다.

| 매개 변수 이름    | 기본값                                | 설명                                                         |
| :---------------- | :------------------------------------ | :----------------------------------------------------------- |
| --Description     |                                       | 서비스 이름 설명 (최대 1024 자)                              |
| --DisplayName     | ServiceName                           | 서비스 표시 이름                                             |
| --Install         | procrun.exe //RS//ServiceName         | 이미지 설치                                                  |
| --Startup         | manual                                | 서비스 시작 모드는 지연(**delayed**), 자동(**auto**) 또는 수 동일 수(**manual**) 있습니다. |
| --Type            |                                       | 서비스 유형은 서비스가 데스크톱과 상호 작용할 수 있도록 상호 작용할 수 있습니다. 이 옵션은 LocalSystem 계정으로 만 사용할 수 있습니다. |
| ++DependsOn       |                                       | 이 서비스가 의존하는 서비스 목록입니다. 종속 서비스는 # 또는; 문자 |
| ++Environment     |                                       | Key=value 형식으로 서비스에 제공 될 환경 변수 목록입니다. # 또는; 문자. # 또는; 값 내의 문자는 작은 따옴표 안에 넣습니다. |
| --User            |                                       | 실행 파일을 실행하는 데 사용되는 사용자 계정입니다. StartMode Java 또는 exe에만 사용되며 LogonAsService 권한이없는 계정으로 응용 프로그램을 서비스로 실행할 수 있습니다. |
| --Password        |                                       | --User 매개 변수로 설정된 사용자 계정의 비밀번호             |
| --ServiceUser     |                                       | 서비스를 실행해야하는 계정의 이름을 지정합니다. DomainName UserName 형식의 계정 이름을 사용합니다. 서비스 프로세스는이 사용자로 로그온됩니다. 계정이 기본 제공 도메인에 속하는 경우. UserName을 지정할 수 있습니다. 서비스 제어 관리자는 표준 이름의 지역화 된 형식을 허용하지 않으므로 이를 사용하려면 NT Authority LocalService, NT Authority NetworkService를 지정해야합니다. 적절한 LocalSystem. |
| --ServicePassword |                                       | --ServiceUser 매개 변수로 설정된 사용자 계정의 비밀번호      |
| --LibraryPath     |                                       | JVM의 DLL을 찾는 데 사용되는 검색 경로에 추가 된 디렉토리입니다. 이 디렉터리는 PATH 환경 변수 앞에 추가되고 SetDLLDirectory 함수에 대한 매개 변수로 추가됩니다. |
| --JavaHome        | JAVA_HOME                             | JAVA_HOME 환경 변수에 정의 된 것과 다른 JAVA_HOME 설정       |
| --Jvm             | auto                                  | Auto (예 : Windows 레지스트리에서 JVM 찾기)를 사용하거나 jvm.dll의 전체 경로를 지정하십시오. 여기에서 환경 변수 확장을 사용할 수 있습니다. Auto가 지정되면 다음 검색 순서가 사용됩니다.<br/>레지스트리에 정의 된 현재 Java 런타임 라이브러리<br/>레지스트리에 정의 된 현재 JRE<br/>서비스에 대해 명시 적으로 구성된 JavaHome<br/>레지스트리에 정의 된 현재 JDK |
| ++JvmOptions      | -Xrs                                  | JVM에 전달 될 -D 또는 -X 형식의 옵션 목록입니다. 옵션은 # 또는; 문자. # 또는; 문자는 작은 따옴표 안에 넣습니다. (exe 모드에서는 사용되지 않습니다.) |
| ++JvmOptions9     |                                       | Java 9 이상에서 실행될 때 JVM에 전달되는 -D 또는 -X 형식의 옵션 목록입니다. 옵션은 # 또는; 문자. # 또는; 문자는 작은 따옴표 안에 넣습니다. (exe 모드에서는 사용되지 않습니다.) |
| --Classpath       |                                       | Java 클래스 경로를 설정하십시오. (exe 모드에서는 사용되지 않습니다.) |
| --JvmMs           |                                       | MB 단위의 초기 메모리 풀 크기. (exe 모드에서는 사용되지 않습니다.) |
| --JvmMx           |                                       | 최대 메모리 풀 크기 (MB). (exe 모드에서는 사용되지 않습니다.) |
| --JvmSs           |                                       | 스레드 스택 크기 (KB)입니다. (exe 모드에서는 사용되지 않습니다.) |
| --StartMode       |                                       | jvm, Java 또는 exe 중 하나입니다. 모드는 다음과 같습니다.<br/>jvm-Java in-process를 시작합니다. jvm.dll에 따라 --Jvm을 참조하십시오.<br/>Java-exe와 동일하지만 자동으로 기본 Java 실행 파일 (예 : % JAVA_HOME % bin java.exe)을 사용합니다. JAVA_HOME이 올바르게 설정되었는지 확인하거나 --JavaHome을 사용하여 올바른 위치를 제공하십시오. 둘 다 설정되지 않은 경우 procrun은 Windows 레지스트리에서 기본 JDK (JRE 아님)를 찾으려고 시도합니다.<br/>exe-이미지를 별도의 프로세스로 실행 |
|                   |                                       |                                                              |
| --StartImage      |                                       | 실행될 실행 파일입니다. Exe 모드에만 적용됩니다.             |
| --StartPath       |                                       | 시작 이미지 실행 파일의 작업 경로입니다.                     |
| --StartClass      | Main                                  | 시작 메서드가 포함 된 클래스입니다. Jvm 및 Java 모드에 적용됩니다. (exe 모드에서는 사용되지 않습니다.) |
| --StartMethod     | main                                  | 서비스가 시작될 때 호출 할 메서드의 이름입니다. 정적 void 여야하며 인수 (String args [])가 있어야합니다. Jvm 모드에만 적용됩니다. Java 모드에서는 항상 기본 방법이 사용됩니다.<br/>참고 : jvm 모드에서 start 메서드는 stop 메서드가 호출 될 때까지 반환되지 않아야합니다. |
| ++StartParams     |                                       | StartImage 또는 StartClass에 전달 될 매개 변수 목록입니다. 매개 변수는 # 또는; 캐릭터. |
| --StopMode        |                                       | Jvm, Java 또는 exe 중 하나입니다. 자세한 내용은 --StartMode를 참조하십시오. |
| --StopImage       |                                       | 서비스 중지 신호에서 실행될 실행 파일입니다. Exe 모드에만 적용됩니다. |
| --StopPath        |                                       | 중지 이미지 실행 파일의 작업 경로입니다. Jvm 모드에는 적용되지 않습니다. |
| --StopClass       | Main                                  | 서비스 중지 신호에 사용될 클래스입니다. Jvm 및 Java 모드에 적용됩니다. |
| --StopMethod      | main                                  | 서비스가 중지 될 때 호출 할 메서드의 이름입니다. 정적 void 여야하며 인수 (String args [])가 있어야합니다. Jvm 모드에만 적용됩니다. Java 모드에서는 항상 기본 방법이 사용됩니다. |
| ++StopParams      |                                       | StopImage 또는 StopClass에 전달 될 매개 변수 목록입니다. 매개 변수는 # 또는; 캐릭터. |
| --StopTimeout     | No Timeout                            | Procrun이 서비스가 정상적으로 종료되기를 기다리는 시간 제한 (초)을 정의합니다. |
| --LogPath         | %SystemRoot%\System32\LogFiles\Apache | 로깅 경로를 정의합니다. 필요한 경우 디렉토리를 만듭니다.     |
| --LogPrefix       | commons-daemon                        | 서비스 로그 파일 이름 접두사를 정의합니다. 로그 파일은 .YEAR-MONTH-DAY.log 접미사를 사용하여 LogPath 디렉터리에 생성됩니다. |
| --LogLevel        | Info                                  | 로깅 수준을 정의하며 오류, 정보, 경고 또는 디버그 일 수 있습니다. (대소 문자 구분 안 함). |
| --LogJniMessages  | 0                                     | Procrun 로그 파일에서 JVM jni 디버그 메시지를 캡처하려면이 0이 아닌 값 (예 : 1)을 설정하십시오. Stdout / stderr 리디렉션이 사용되는 경우에는 필요하지 않습니다. Jvm 모드에만 적용됩니다. |
| --StdOutput       |                                       | 표준 출력 파일 이름을 리디렉션했습니다. 이름이 service-stdout.YEAR-MONTH-DAY.log 인 이름이 지정된 자동 파일이 LogPath 내에 생성 된 경우. |
| --StdError        |                                       | Stderr 파일 이름을 리디렉션했습니다. 이름이 지정된 자동 파일이 service-stderr.YEAR-MONTH-DAY.log라는 이름으로 LogPath 디렉토리에 생성 된 경우. |
| --PidFile         |                                       | 실행중인 프로세스 ID를 저장하기위한 파일 이름을 정의합니다. 실제 파일은 LogPath 디렉토리에 생성됩니다. |

### Installing services

서비스를 설치하려면 **//IS** 매개 변수를 사용해야합니다.

#### Install the service named 'TestService'

``` cmd
prunsrv //IS//TestService --DisplayName="Test Service" \
        --Install=prunsrv.exe --Jvm=auto --StartMode=jvm --StopMode=jvm \
        --StartClass=org.apache.SomeStartClass --StartParams=arg1;arg2;arg3 \
        --StopClass=org.apache.SomeStopClass --StopParams=arg1#arg2
```

### Updating services

서비스 매개 변수를 업데이트하려면 **//US** 매개 변수를 사용해야합니다.

#### Update the service named 'TestService'

``` cmd
prunsrv //US//TestService --Description="Some Dummy Test Service" \
        --Startup=auto --Classpath=%CLASSPATH%;test.jar
```

### Removing services

서비스를 제거하려면 **//DS** 매개 변수를 사용해야합니다. 서비스가 실행 중이면 중지 된 다음 삭제됩니다.

#### Remove the service named 'TestService'

``` cmd
prunsrv //DS//TestService
```

### Debugging services

콘솔 모드에서 서비스를 실행하려면 **//TS** 매개 변수를 사용해야합니다. 서비스 종료는 CTRL + C 또는 CTRL + BREAK를 눌러 시작할 수 있습니다. prunsrv.exe의 이름을 testservice.exe로 바꾸면 testservice.exe를 실행하기 만하면이 명령 모드가 기본적으로 실행됩니다.

#### Run the service named 'TestService' in console mode

``` cmd
prunsrv //TS//TestService [additional arguments]
```

## Using Procrun in jvm mode

jvm 모드를 사용하여 Procrun 서비스 응용 프로그램 (prunsrv)과 인터페이스하려면 적절한 메서드로 클래스를 만들어야합니다. 예를 들면 :

``` java
class MyClass;
// N.B. error handling not shown
static void main(String [] args){
    String mode = args[0];
    if ("start".equals(mode){
        // process service start function
    }
    etc.
}
```

다음과 같이 구성해야합니다.

``` cmd
--Classpath MyClass.jar
--StartMode jvm --StartClass MyClass --StartParams start
--StopMode  jvm --StopClass  MyClass --StopParams  stop
```

위의 예는 단일 'main'메소드를 사용하고 문자열 매개 변수를 사용하여 서비스 기능이 시작인지 중지인지를 지정합니다.

또는 서비스 시작 및 중지 기능에 대해 다른 메소드 이름을 사용할 수 있습니다.

``` java
class MyClass;
// N.B. error handling not shown
static void start(String [] args){
        // process service start function
    }
static void stop(String [] args){
        // process service stop function
    }
}
```

다음과 같이 구성해야합니다.

``` cmd
--Classpath MyClass.jar
--StartMode jvm --StartClass MyClass --StartMethod start
--StopMode  jvm --StopClass  MyClass --StopMethod  stop
```

참고 : jvm 모드에서 start 메서드는 stop 메서드가 호출 될 때까지 반환되지 않아야합니다. Start 및 stop 메서드는 다른 스레드에서 호출됩니다.

## Using Procrun in Java or exe mode

Java 또는 exe 모드를 사용하는 경우 Procrun 서비스 응용 프로그램 (prunsrv)은 별도의 프로세스에서 대상 응용 프로그램을 시작합니다. '중지'애플리케이션은 '시작'애플리케이션과 어떻게 든 통신하여 중지하도록 지시해야합니다. 예를 들어 RPC를 사용합니다.

## Windows Registry Usage

기본 서비스 정의는 레지스트리 키 아래에 유지됩니다.

``` cmd
HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\<ServiceName>
```

추가 매개 변수는 다음 위치의 레지스트리에 저장됩니다.

``` cmd
HKEY_LOCAL_MACHINE\SOFTWARE\Apache Software Foundation\ProcRun 2.0\<ServiceName>\Parameters
```

64 비트 Windows에서 procrun은 구성을 저장하기 위해 항상 32 비트 레지스트리보기를 사용합니다. 이것은 매개 변수가 내부에 저장됨을 의미합니다.

``` cmd
HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Apache Software Foundation\ProcRun 2.0\<ServiceName>
```





# Howto setup a Daemon implementation as windows service

출처: https://stackoverflow.com/questions/31556478/howto-setup-a-daemon-implementation-as-windows-service

``` cmd
prunsrv.exe //IS//MyTestService ^
    --DisplayName="My Test Service" --Description="Doesn't really do anything" ^
    --Install=@@PATH_TO_PRUNSRV@@\prunsrv.exe ^
    --Startup=manual ^
    --Jvm=auto ^
    --Classpath="@@PUT_FULL_CLASSPATH_HERE@@" ^
    --StartMode=jvm ^
    --StartClass==com.stackoverflow.questions.31556478.ServiceLauncher ^
    --StartParams="@@PUT_ANY_START_ARGUMENTS_HERE@@" ^
    --StartMethod=start ^
    --StopMode=jvm ^
    --StopClass=com.stackoverflow.questions.31556478.ServiceLauncher ^
    --StopMethod=stop
```

두 변형을 테스트하고 구현 만 작동했으며 시작 방법에 머물 렀지 만 추가 스레드가 생성되지 않았습니다. 위의 prunsrv 호출로 등록 할 수있는 실행기 구현은 다음과 같습니다 (보증없이).

``` java
package com.stackoverflow.questions.31556478;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLauncher
{
  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLauncher.class);

  private static SomeServer mServer;

  public static void start(final String[] args)
  {
    LOGGER.debug("Start called: {}", Arrays.toString(args));

    try
    {
      mServer = new SomeServer(args);
      mServer.start();
    }
    catch (final Exception e)
    {
      LOGGER.error("Terminating due to Exception: ", e);
    }
  }

  public static void stop(final String[] args) throws Exception
  {
    LOGGER.debug("Stop called: {}", Arrays.toString(args));

    synchronized (ServiceLauncher.class)
    {
      if (mServer != null)
      {
        mServer.stop();
      }
    }
  }
}
```



내일(2021.06.22) 이걸로 테스트 예정

