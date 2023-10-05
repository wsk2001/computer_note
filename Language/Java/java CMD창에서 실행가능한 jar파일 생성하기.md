# java CMD창에서 실행가능한 jar파일 생성하기



### 1. source 컴파일

``` cmd
javac -d source.java -encoding utf-8 -classpath dependency.jar
```



### 2. Manifest.txt 파일 만들기

`Manifest.txt`

```
Manifest-Version: 1.0
Main-Class: com.hancomwith.xfc.pocagent.PocAgent
Class-Path: .

Name: javassist/
```





### 2. jar 파일 만들기

``` cmd
>jar  -cvmf  Manifest.txt  Server.jar  test\server\*.class  json_simple-1.1.jar
```



