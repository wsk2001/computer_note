# OS 를 테스트 모드로 전환 시키기

1. OS 를 테스트 모드로 전환 시키기<br/>
	1.1 명령 프롬프트 열기 (관리자 권한으로 실행)<br/>
	1.2 Bcdedit.exe -set TESTSIGNING ON<br/>
	1.3 컴퓨터 재부팅<br/>
	
2. OS 를 운영 모드로 전환 시키기<br/>	
	2.1 명령 프롬프트 열기 (관리자 권한으로 실행)<br/>
	2.2 Bcdedit.exe -set TESTSIGNING OFF<br/>
	2.3 컴퓨터 재부팅<br/>
