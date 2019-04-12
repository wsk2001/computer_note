<<<<<<< HEAD
# Windows 를 테스트 모드로 전환 시키기
개발 작업을 하다보면 가끔 Device Driver 를 이용하여 프로그래밍 하는 경우가 있다. 이때 서명이 되지 않은 Device Driver 를 이용하여 개발을 하는 경우가 있는데 그럴 경우는 Windows OS 를 테스트모드로 실행 시켜야 한다. 이 문서는 Windows 를 테스트 모드로 전환 하는 방법과 운영 모드로 전환 하는 방법에 관하여 기술 한다.




## Windows 를 테스트 모드로 전환 시키기
> 명령 프롬프트 열기 (관리자 권한으로 실행) 
> C:\> Bcdedit.exe -set TESTSIGNING ON 
> 컴퓨터 재부팅 

재부팅이 완료 되면 화면 우측 하단에 테스트 모드라고 표시된다.



## Windows 를 운영 모드로 전환 시키기	
> 명령 프롬프트 열기 (관리자 권한으로 실행) 
> C:\> Bcdedit.exe -set TESTSIGNING OFF 
> 컴퓨터 재부팅 



운영 모드로 전환되면 테스트 모드에서 설치한 서명되지 않은 Device Driver 가 Load 되지 않는다.

=======
# OS 를 테스트 모드로 전환 시키기

1. OS 를 테스트 모드로 전환 시키기<br/>
	1.1 명령 프롬프트 열기 (관리자 권한으로 실행)<br/>
	1.2 Bcdedit.exe -set TESTSIGNING ON<br/>
	1.3 컴퓨터 재부팅<br/>
	
2. OS 를 운영 모드로 전환 시키기<br/>	
	2.1 명령 프롬프트 열기 (관리자 권한으로 실행)<br/>
	2.2 Bcdedit.exe -set TESTSIGNING OFF<br/>
	2.3 컴퓨터 재부팅<br/>
>>>>>>> e52eafc4fcb33c93a5200c6a0193d44c0336c78d
