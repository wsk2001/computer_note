# Eclipse Tip



## 1. Eclipse 에 외부 jar 포함하는 방법

```
프로젝트 선택 후 우측 마우스
	-> Build Path
		-> Add External Archives
```



## 2. Eclipse 에서 실행 가능한 jar 파일 만들기

**2.1 실행 환경 설정**

```
Package Explorer
  -> 프로젝트 선택 후 우측 버튼 Click
    -> Run As
	   -> Run configurations
	     -> Java Application
		   -> New_configuration 선택

Main Tab 선택 후 작업
  Project : 원하는 Project 선택 (예: LocalAgent
  Main class : 실행할 main 함수가 포함된 class 지정
               Project 에 포함된 class 들 에는 여러군데 main 함수가 있을 수 있음
			   (예: com.hancomwith.LocalAgent)

Apply 버튼으로 적용 및 Close 버튼으로 닫기
```

**2.2 실행 가능 jar 만들기**

```
Package Explorer
  -> 프로젝트 선택 후 우측 버튼 Click
    -> Export
	  -> Java
	    -> Runnable JAR file 선택 후 Next 버튼 Click

Runnable JAR File Export 창 에서 작업
  Launch configuration : 위 에서 만든 New_configuration - LocalAgent 선택
  Export destination   : Browse... 버튼을 눌러 출력을 원하는 경로와 파일명 입력
                         (예: LocalAgent\run\LocalAgent.jar)
  Library handling: 에서 적당한 옵션 선택 (예: Extract required libraries into generated JAR)

Finish 버튼을 눌러 파일 생성
```

