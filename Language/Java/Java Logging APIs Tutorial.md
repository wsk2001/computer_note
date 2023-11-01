# Java Logging APIs Tutorial

출처: https://mkyong.com/logging/java-logging-apis-tutorial/

이 튜토리얼에서는 java.util.logging 패키지의 Java 내장 로깅 API를 사용한 로깅을 보여줍니다.



### 1. Java Logging APIs Hello World

1.1 Java Logging API 또는 java.util.logging hello world 예제는 일부 메시지를 기록하고 콘솔에 표시합니다.

`HelloWorld.java`

``` java

package com.mkyong;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorld {

    private static Logger logger = Logger.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {

        // set log level to SEVERE, default level info
        //logger.setLevel(Level.SEVERE);

        // Log a info level msg
        logger.info("This is level info logging");

        logger.log(Level.WARNING, "This is level warning logging");

        logger.log(Level.SEVERE, "This is level severe logging");

        System.out.println("Hello Java Logging APIs.");
    }

}


```



Output

`Terminal`

```
Jun 05, 2021 1:35:08 PM com.mkyong.HelloWorld main
INFO: This is level info logging
Jun 05, 2021 1:35:08 PM com.mkyong.HelloWorld main
WARNING: This is level warning logging
Jun 05, 2021 1:35:08 PM com.mkyong.HelloWorld main
SEVERE: This is level severe logging
Hello Java Logging APIs.
```



### 2. Logging Levels

2.1 java.util.logging 패키지에는 7가지 로깅 수준이 있습니다.

- SEVERE (highest value)
- WARNING
- INFO (default)
- CONFIG
- FINE
- FINER
- FINEST (lowest value)

> **Note**
> Read the [Java logging Level](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/Level.html).

2.2 로깅 수준을 SEVERE로 설정하면 SEVERE 수준과 같거나 그 이상의 메시지가 기록됩니다. 그리고 SEVERE는 가장 높은 로깅 수준이므로 SEVERE 수준의 메시지만 기록합니다.

`HelloWorld2.java`

``` java

package com.mkyong;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorld2 {

    private static Logger logger = Logger.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {

        // set log level to SEVERE, default level info
        logger.setLevel(Level.SEVERE);

        // Log a info level msg
        logger.info("This is level info logging");

        logger.log(Level.WARNING, "This is level warning logging");

        logger.log(Level.SEVERE, "This is level severe logging");

        System.out.println("Hello Java Logging APIs.");
    }

}

```

Output

`Terminal`

```
Jun 05, 2021 1:41:07 PM com.mkyong.HelloWorld main
SEVERE: This is level severe logging
Hello Java Logging APIs.
```

2.3 로깅 수준을 WARNING으로 설정하면 WARNING 수준인 Level.SEVERE 및 Level.WARNING과 같거나 그 이상의 메시지가 기록됩니다.

``` java
  // set logging level to WARNING
  logger.setLevel(Level.WARNING);
```

Output

`Terminal`

```
Jun 05, 2021 5:08:11 PM com.mkyong.HelloWorld main
WARNING: This is level warning logging
Jun 05, 2021 5:08:11 PM com.mkyong.HelloWorld main
SEVERE: This is level severe logging
Hello Java Logging APIs.
```



### 3. logging.properties

Java 로깅 API(java.util.logging)는 기본적으로 $JAVA_HOME/jre/lib/(Java 8 이하)에 login.properties를 로드합니다. Java 9 이상의 경우에는 login.properties 파일이 $JAVA_HOME/conf로 이동되었습니다.

3.1 다음은 콘솔과 파일 모두에 메시지를 기록하는 login.properties 샘플입니다.

`logging.properties`

``` properties

# Logs to file and console
handlers= java.util.logging.FileHandler, java.util.logging.ConsoleHandler
# Global logging levels, 7 levels
.level= SEVERE

# Log file output in user's home directory, %h
java.util.logging.FileHandler.pattern = %h/java%u.log
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 1
java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter
# java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter

java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

java.util.logging.SimpleFormatter.format=[%1$tc] %4$s: %5$s %n

# log level for package
com.mkyong.level = SEVERE

```

3.2 자원 폴더(Maven 표준 디렉토리)에 log.properties를 넣고 시스템 속성인 java.util.logging.config.file을 사용하여 login.properties의 위치를 정의할 수 있습니다.

> **Note**
> [How to load logging.properties for java.util.logging](https://mkyong.com/logging/how-to-load-logging-properties-for-java-util-logging/)

`LoadLogPropertiesFile.java`

``` java

package com.mkyong;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadLogPropertiesFile {

  static {
      // must set before the Logger
      String path = LoadLogPropertiesFile.class.getClassLoader()
                        .getResource("logging.properties").getFile();
      System.setProperty("java.util.logging.config.file", path);
  }

  private static Logger logger = Logger.getLogger(LoadLogPropertiesFile.class.getName());

  public static void main(String[] args) {

      logger.fine("This is level fine logging");

      // Log a fine level msg
      logger.info("This is level info logging");

      logger.log(Level.SEVERE, "This is level severe logging");

      logger.log(Level.WARNING, "This is level warning logging");

      System.out.println("Hello Java Logging APIs.");
  }

}

```

SimpleFormatter를 사용하여 콘솔로 출력합니다.

`Terminal`

``` 
[Sat Jun 05 14:04:18 MYT 2021] SEVERE: This is level severe logging
Hello Java Logging APIs.
```

XMLFormatter를 사용하여 파일로 출력합니다.

`$user_home/java0.log`

``` xml

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE log SYSTEM "logger.dtd">
<log>
<record>
<date>2021-06-05T06:04:18.276214600Z</date>
<millis>1622873058276</millis>
<nanos>214600</nanos>
<sequence>0</sequence>
<logger>com.mkyong.LoadLogPropertiesFile</logger>
<level>SEVERE</level>
<class>com.mkyong.LoadLogPropertiesFile</class>
<method>main</method>
<thread>1</thread>
<message>This is level severe logging</message>
</record>
</log>

```



### 4. java.util.logging.config.file

시스템 속성 java.util.logging.config.file은 login.properties의 위치를 정의했습니다.

명령줄에서 시스템 속성 java.util.logging.config.file을 사용하여 런타임 시 login.properties 파일의 위치를 정의할 수 있습니다.

| Terminal                                                     |
| ------------------------------------------------------------ |
| $ java -jar -Djava.util.logging.config.file=/path/logging.properties server.jar |



### 5. java.util.logging을 선택하는 이유

이 Java 로깅 API 또는 java.util.logging은 Java JDK의 일부로, 콘솔이나 파일에 대한 로그와 같은 기본 로깅 기능만 필요한 간단한 프로젝트에 적합합니다.

로그 파일 회전, 오류 로그에 대한 이메일 보내기, 데이터베이스에 로그 보내기 등과 같은 고급 로깅 기능의 경우 로깅 Facade SLF4j와 같은 타사 로깅 프레임워크나 Logback 및 Apache Log4j 2와 같은 로깅 프레임워크가 많이 있습니다.



---

# Java의 기본 log 기능 정리

출처: http://www.gisdeveloper.co.kr/?p=5174

log4j 등과 같은 로그를 위한 좋은 라이브러리가 있으나 Java에서 제공하는 기본 Log 기능에 대해 정리합니다. 먼저 가장 간단한 예는 아래와 같습니다. log에 대한 수준(Level)을 지정해 메세지를 콘솔에 표시하는 경우입니다.

``` java
package tst_Log_console;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainEntry {
    private final static Logger LOG = Logger.getGlobal();
    
    public static void main(String[] args) {
        LOG.setLevel(Level.INFO);
        
        LOG.severe("severe Log");
        LOG.warning("warning Log");
        LOG.info("info Log");
    }
}
```

Log의 수준은 높은 순서로 SEVERE, WARNING, INFO입니다. 각각이 의미하는 바는 심각함, 경고, 정보입니다. setLevel 함수를 통해 표시할 레벨의 수준을 조정할 수 있습니다. 위 코드에 대한 실행 결과는 다음과 같습니다.

```
4월 09, 2018 5:58:41 오후 tst_Log_console.MainEntry main
심각: severe Log
4월 09, 2018 5:58:41 오후 tst_Log_console.MainEntry main
경고: warning Log
4월 09, 2018 5:58:41 오후 tst_Log_console.MainEntry main
정보: info Log
```

위처럼 로그의 내용은 이미 정해져 있는데요. 만약 자신만의 로그 형식을 원한다면 먼저 Formatter 클래스를 상속받아 다음과 같은 클래스를 정의해야 합니다.

``` java
package tst_Log_console;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {
    public String getHead(Handler h) {
        return "START LOG\n";
    }
    
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append(calcDate(rec.getMillis()));
        
        buf.append(" [");
        buf.append(rec.getLevel());
        buf.append("] ");
        
        buf.append("[");
        buf.append(rec.getSourceMethodName());
        buf.append("] ");
        
        buf.append(rec.getMessage());
        buf.append("\n");
        
        return buf.toString();
    }
    public String getTail(Handler h) {
        return "END LOG\n";
    }
    
    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }
}
```

그리고 이 클래스를 다음의 예처럼 활용할 수 있습니다.

``` java
package tst_Log_console;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MainEntry2 {
    private final static Logger LOG = Logger.getGlobal();
    
    public static void main(String[] args) {
        //=============================================
        // 기본 로그 제거
        //------------
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
        //=============================================
        
        LOG.setLevel(Level.INFO);
        
        Handler handler = new ConsoleHandler();
        CustomLogFormatter formatter = new CustomLogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);
        
        LOG.severe("severe Log");
        LOG.warning("warning Log");
        LOG.info("info Log");
    }
}
```

위의 클래스에서 getHead 함수는 로그 기록을 시작할때 한번만 호출되어 로그로써 표시되는 문자열을 반환합니다. 그리고 format은 개발자가 로그를 표시하기 원할때마다 메세지의 형식을 정의하기 위해 호출되는 함수입니다. 그리고 getTail은 프로그램이 종료될때 호출되는 로그 메세지로 표시할 문자열을 반환합니다. (그러나 현재 시점에서 이 getTail은 로그를 콘솔로 표시할 때 호출되지 않는 문제가 있음) 코드를 좀더 살펴보면, 12-20번 코드는 기본적으로 지정된 로그(콘솔창에 이미 정의된 형식으로 내요을 출력함)를 제거합니다. 그리고 24-27번 코드처럼 앞서 정의한 Formatter에 대한 상속 클래스를 생성해 지정합니다. 실행해 보면 다음과 같습니다.

```
START LOG
2018-04-09 18:06 [SEVERE] [main] severe Log
2018-04-09 18:06 [WARNING] [main] warning Log
2018-04-09 18:06 [INFO] [main] info Log
```

위의 경우처럼 로그를 화면이 아닌 파일로 저장하고 싶을 때가 있습니다. 이 경우 아래의 코드와 같이 하면 됩니다.

``` java
package tst_Log_console;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainEntry3 {
    private final static Logger LOG = Logger.getGlobal();
    
    public static void main(String[] args) throws SecurityException, IOException {
        //=============================================
        // 기본 로그 제거
        //------------
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
        //=============================================
        
        LOG.setLevel(Level.INFO);
        
        Handler handler = new FileHandler("message.log", true);
        
        CustomLogFormatter formatter = new CustomLogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);
        
        LOG.severe("severe Log");
        LOG.warning("warning Log");
        LOG.info("info Log");
    }
}
```

달라지는 내용은 26번 코드에서 ConsoleHandler 대신 FileHandler로 변경되었다는 것입니다. 이 FileHandler 생성자는 첫번째 인자에 사용할 파일명을, 그리고 두번째 인자에 파일 기록시 APPEND 모드인지의 여부를 지정합니다.