# Log4j의 모든 것 (환경 세팅, 사용법, 기초)

출처: https://androphil.tistory.com/420

우선 jar 파일(log4j-1.2.15.jar)을 받아서 라이브러리에 추가합니다.  

log4j설정파일을 만듭니다. 최상위 폴더에다가 log4j.properties 파일을 생성합니다.

``` properties 
# Log4j Setting file
log4j.rootLogger=INFO, console

# Daily file log
log4j.appender.logfile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.logfile.File=D:/Data/Log/data.log
log4j.appender.logfile.DatePattern='.'yyyy-MM-dd
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
log4j.appender.logfile.layout.ConversionPattern=[%d{HH:mm:ss}][%-5p](%F:%L)-%m%n

# Console log
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-5p%l -%m%n

# log level and appender
log4j.logger.name.of.the.package.one=DEBUG, console
log4j.logger.name.of.the.package.two=INFO, logfile
```

대략 내용을 살펴보면 log4j.rootLogger는 최상위 로거입니다.
모든 INFO레벨이상의 로그는 다 console로 찍겠다는 겁니다.
(레벨에는 DEBUG, INFO, WARN, ERROR, FATAL 순인데, 예를 들어 INFO레벨로 지정해두면 logger.debug로 찍는 로그는 나타나지 않습니다. INFO레벨 이상것만 나타납니다.)

console은 아래 #Console log쪽에 보시면
log4j.appender.**console** <- 요 이름입니다.
요 console은 자세히보면 ConsoleAppender라는 클래스입니다. 이건 말그대로 콘솔에 로그를 찍어준다는 겁니다. layout에는 PatternLayout을 지정할 수 있는데 저 패턴은 뭐 레벨이 뭐고, 클래스가 뭐고, 메시지찍고 뭐 그런 내용입니다.

그리고, 파일에다가 출력 할 수 있는데, DailyRollingFileAppender클래스를 이용합니다. 이눔은 말그대로 매일매일 다른로그를 사용하게 만듭니다. 로그이름이 위와 같이 data.log라면, 해당로그가 어제날짜인데 로그를 찍으려고 하면 기존에 있던 파일은 data.log.2008-04-17 이렇게 바꿔줍니다.

아래부분에 보면 log4j.logger. 다음에 패키지명이나 클래스명을 지정해놓고, 로그레벨과 출력할 로그를 지정할 수 있는데요. 해당 클래스나 패키지의 로그는 저걸로 찍겠다는 겁니다.

name.of.the.package.two 클래스는 logfile로 찍힌다는 겁니다.
그리고, rootLogger가 colsole로 지정되어 있기 때문에 console에도 찍힙니다.

로그를 찍어봅시다.

``` java
package log;
import org.apache.log4j.Logger;

public class Main {
	protected static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		//DEBUG < INFO < WARN < ERROR < FATAL
		logger.fatal("log4j:logger.fatal()");
		logger.error("log4j:logger.error()");
		logger.warn("log4j:logger.warn()");
		logger.info("log4j:logger.info()");
		logger.debug("log4j:logger.debug()");
	}
}
```

자 그럼 콘솔에는

```
FATALlog.Main.main(Main.java:13) -log4j:logger.fatal()
ERRORlog.Main.main(Main.java:15) -log4j:logger.error()
WARN log.Main.main(Main.java:17) -log4j:logger.warn()
INFO log.Main.main(Main.java:19) -log4j:logger.info()
```

이렇게 출력이 될 것이고 로그파일에도 관련하여 출력될 것입니다.

만약 콘솔에 두번 찍힌다면 아마도 이유는 rootLogger도 찍고, 아래 패키지를 지정한 로그도 찍었기 때문이죠.
그리고, 파일에는 한번만 쓰였다면 이유는 파일에 쓰는건

log4j.logger.name.of.the.package.two=INFO, logfile 여기 이 two 클래스 하나이기 때문입니다.



### 페이지 히스토리

```
###############################################
# FATAL : 가장 크리티컬한 에러가 일어 났을 때 사용합니다.
# ERROR : 일반 에러가 일어 났을 때 사용합니다.
# WARN : 에러는 아니지만 주의할 필요가 있을 때 사용합니다.
# INFO : 일반 정보를 나타낼 때 사용합니다.
# DEBUG : 일반 정보를 상세히 나타낼 때 사용합니다.
################################################
```

```
######################################################################
#%p debug, info, warn, error, fatal 등의 priority 가 출력된다.
#%m 로그내용이 출력됩니다
#%d 로깅 이벤트가 발생한 시간을 기록합니다.
# 포맷은 %d{HH:mm:ss, SSS}, %d{yyyy MMM dd HH:mm:ss, SSS}같은 형태로 사용하며 SimpleDateFormat에 따른 포맷팅을 하면 된다
#%t 로그이벤트가 발생된 쓰레드의 이름을 출력합니다.
#%% % 표시를 출력하기 위해 사용한다.
#%n 플랫폼 종속적인 개행문자가 출력된다. \r\n 또는 \n 일것이다.
#%c 카테고리를 표시합니다
# 예) 카테고리가 a.b.c 처럼 되어있다면 %c{2}는 b.c가 출력됩니다.
#%C 클래스명을 포시합니다.
# 예)클래스구조가 org.apache.xyz.SomeClass 처럼 되어있다면 %C{2}는 xyz.SomeClass 가 출력됩니다
#%F 로깅이 발생한 프로그램 파일명을 나타냅니다.
#%l 로깅이 발생한 caller의 정보를 나타냅니다
#%L 로깅이 발생한 caller의 라인수를 나타냅니다
#%M 로깅이 발생한 method 이름을 나타냅니다.
#%r 어플리케이션 시작 이후 부터 로깅이 발생한 시점의 시간(milliseconds)
#%x 로깅이 발생한 thread와 관련된 NDC(nested diagnostic context)를 출력합니다.
#%X 로깅이 발생한 thread와 관련된 MDC(mapped diagnostic context)를 출력합니다.
#######################################################################
```



log4j 설정 예제

* 최상위 카테고리에 INFO로 레벨 설정 및 appender로 stdout, rolling을 정의

  ```
  log4j.rootLogger=DEBUG, stdout, rolling
  ```

* stdout 어펜더는 콘솔에 뿌리겠다는 정의

  ```
  log4j.appender.stdout=org.apache.log4j.ConsoleAppender
  ```

* stdout 어펜더는 patternlayout을 사용하겠다는 정의

  ```
  log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
  ```

* 페턴은 다음과 같이 포맷팅 하겠다는 것을 정의

  ```
  log4j.appender.stdout.layout.ConversionPattern=[%d{yyyy\ub144 MM\uc6d4 dd\uc77c HH:mm(ss)}] _%-5p_ [%t] %-17c{2} (%13F:%L) %3x -->\ub0b4\uc6a9 : %m%n
  ```

* 역시나 rolling 어펜더는 파일로 처리한다라고 정의

  ```
  log4j.appender.rolling=org.apache.log4j.DailyRollingFileAppender
  ```

* 로그 파일 이름은 output.log

  ```
  log4j.appender.rolling.File=output.log
  ```

* true면 톰캣을 내렸다 올려도 파일이 리셋되지 않습니다.

  ```
  log4j.appender.rolling.Append=true
  ```

* 역시나 rolling 어펜더는 패턴 레이아웃을 사용하겠다고 정의

  ```
  log4j.appender.rolling.layout=org.apache.log4j.PatternLayout
  ```

* rolling 어펜더는 패턴 레이아웃 포맷

  ```
  log4j.appender.rolling.layout.ConversionPattern=[%d{yyyy\ub144 MM\uc6d4 dd\uc77c HH:mm(ss)}] _%-5p_ [%t] %-17c{2} (%13F:%L) %3x -->\ub0b4\uc6a9 : %m%n
  ```





