# eclipse 에서 프로젝트 복사붙혀넣기

해당 프로젝트가 있는 파일경로로 가기

1) 프로젝트 폴더를 통째로 Copy & Paste 한다.

 

2) 복사한 프로젝트 폴더에서 .project 파일을 열어, <name>프로젝트명</name> 을 변경한다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>project_test</name>  //  여기
	<comment></comment>
	<projects>
	</projects>
```

  POM.xml 파일의 <name>프로젝트명</name> 을 변경한다.

```xml
<artifactId>project_1</artifactId>
	<name>project_test</name>  // 여기
	<packaging>war</packaging>
	<version>1.0.0</version>
	<properties>
		<java-version>1.7</java-version>
		<org.springframework-version>4.0.5.RELEASE</org.springframework-version>
		<org.aspectj-version>1.8.0</org.aspectj-version>
		<org.slf4j-version>1.7.5</org.slf4j-version>
```

 

3) 혹시 cvs 나 svn 폴더가 있다면 삭제한다.

 

4) 이클립스에서 Import > Existing Projects into WorkSpace 으로 복사한 프로젝트를 불러 들인다.