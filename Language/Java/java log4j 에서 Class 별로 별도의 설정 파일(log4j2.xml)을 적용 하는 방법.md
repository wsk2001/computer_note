# log4j 에서 Class 별로 별도의 설정 파일(log4j2.xml)을 적용 하는 방법.



### 1. Classpath 에 지정되지 않은 log4j2.xml 파일을 load 하는 방법

`sample`

``` java
public class  Ex {
    static boolean isSuccess = false;
    
    private static Logger logger = null;

    private static void init_log()
    {
    	if(logger != null)
    		return;
    	
        File file = new File("log_conf/my-log4j2.xml");
        Path resource = file.toPath();
        ConfigurationSource configurationSource = null;
		try {
			configurationSource = new ConfigurationSource(Files.newInputStream(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
        Configurator.initialize(null, configurationSource);

		if(logger == null)
			logger = LogManager.getLogger(Ex.class);
    	
    }
 
	public static void main(String[] args) {
		boolean isSuccess = false;
		
		init_log();
		
		logger.info("Test IQFileApi");
    }
}
```





### java 에서 log4j 를 사용하는 2 개의 package 가 있고 이 두개의 package 를 사용하는 App 을 만들었을때  각각의 package 가 다른 log4j2.xml 을 사용 하는 방법



#### Bing

Java에서 두 개의 패키지가 각각 다른 `log4j2.xml` 설정 파일을 사용하도록 하는 것은 직접적으로 지원되지 않습니다. `log4j2.xml` 설정 파일은 일반적으로 전역 설정을 제공하며, 이는 모든 로깅 동작에 적용됩니다.



### [How to specify Log4J 2.x config location?](https://stackoverflow.com/questions/16716556/how-to-specify-log4j-2-x-config-location)

클래스 경로 및 시스템 속성을 손상시키지 않고 Log4J 2.x log4j2.xml 파일 위치를 수동으로 지정할 수 있는 방법이 있습니까(예: Log4J 1.x의 DOMConfigurator)?



log4j 버전 2.12.1의 경우 여기에서 [log4j2](http://for%20log4j%20version%202.12.1%2C%20you%20can%20find%20how%20to%20reconfigure%20log4j2%20on%20https//logging.apache.org/log4j/2.x/faq.html#reconfig_from_code%20Below%20is%20an%20example)를 재구성하는 방법을 찾을 수 있습니다.

아래는 예시입니다.

``` java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

File file = new File("C:\\Path for Windows OS\\yourLog4j2.xml");
    
LoggerContext context = (LoggerContext) LogManager.getContext(false);
context.setConfigLocation(file.toURI());
    
Logger log  = LogManager.getLogger(YourClass.class);
```



## log4j2 서비스별 log 파일명 지정 방법

Log4j2를 사용하여 서비스별로 로그 파일명을 다르게 지정하는 방법은 다음과 같습니다.

먼저, Log4j2의 설정 파일인 `log4j2.xml` 또는 `log4j2.properties` 파일에서 Appender를 정의하고, 각 서비스에 대한 로거(Logger)를 설정합니다. 이 때, 각 로거에 대해 다른 Appender를 지정하여 서비스별로 다른 로그 파일에 로그를 기록하도록 할 수 있습니다.

아래는 `log4j2.xml` 설정 파일의 예시입니다:

``` xml
<Configuration status="INFO">
    <Appenders>
        <RollingFile name="Service1Appender" fileName="./logs/service1.log" filePattern="./logs/service1-%d{yyyy-MM-dd}-%i.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
        </RollingFile>
        <RollingFile name="Service2Appender" fileName="./logs/service2.log" filePattern="./logs/service2-%d{yyyy-MM-dd}-%i.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <Logger name="com.example.service1" level="info" additivity="false">
            <AppenderRef ref="Service1Appender"/>
        </Logger>
        <Logger name="com.example.service2" level="info" additivity="false">
            <AppenderRef ref="Service2Appender"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>

```

위의 설정에서 `Service1Appender`와 `Service2Appender`는 각각 `service1.log`와 `service2.log`라는 이름의 로그 파일에 로그를 기록합니다. [그리고 `com.example.service1` 패키지와 `com.example.service2` 패키지는 각각 `Service1Appender`, `Service2Appender`를 사용하도록 설정되어 있습니다](https://velog.io/@bread_dd/Log4j-2-제대로-사용하기)[1](https://velog.io/@bread_dd/Log4j-2-제대로-사용하기)[2](https://velog.io/@zzarbttoo/로그log4j2를-이용해-특정-파일에-로그-기록을-모아보았다).

[이렇게 설정하면, `com.example.service1` 패키지와 `com.example.service2` 패키지에서 생성되는 로그는 각각 `service1.log`, `service2.log`라는 이름의 로그 파일에 기록됩니다](https://velog.io/@bread_dd/Log4j-2-제대로-사용하기)[1](https://velog.io/@bread_dd/Log4j-2-제대로-사용하기)[2](https://velog.io/@zzarbttoo/로그log4j2를-이용해-특정-파일에-로그-기록을-모아보았다). 이 방법을 통해 서비스별로 로그 파일명을 다르게 지정할 수 있습니다.



#### 아래는 Log4j2 설정을 테스트하는 간단한 Java 코드

``` java
package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static final Logger service1Logger = LogManager.getLogger("com.example.service1");
    private static final Logger service2Logger = LogManager.getLogger("com.example.service2");

    public static void main(String[] args) {
        service1Logger.info("This is an info message from service1");
        service2Logger.info("This is an info message from service2");
    }
}

```

위의 코드는 `com.example.service1`와 `com.example.service2`라는 이름의 두 개의 로거를 생성하고, 각 로거를 사용하여 로그 메시지를 출력합니다. 이 코드를 실행하면, `service1.log`와 `service2.log`라는 이름의 로그 파일에 각각 로그 메시지가 기록됩니다.

이 코드를 실행하기 전에, 위에서 제공한 Log4j2 설정을 프로젝트의 클래스패스에 추가해야 합니다. 일반적으로 이는 프로젝트의 `src/main/resources` 디렉토리에 `log4j2.xml` 파일을 생성하여 설정을 추가하는 것을 의미합니다.

참고로, 위의 코드는 Log4j2 라이브러리가 프로젝트에 추가되어 있어야 합니다. Maven 프로젝트인 경우, `pom.xml` 파일에 다음과 같은 의존성을 추가해야 합니다:

``` xml
<dependencies>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.14.1</version>
    </dependency>
</dependencies>

```

이제 이 설정을 사용하여 서비스별로 로그 파일명을 다르게 지정할 수 있습니다. 이 코드를 사용하여 테스트를 진행해 보세요.
