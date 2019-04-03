# OS 를 테스트 모드로 전환 시키기

1. OS 를 테스트 모드로 전환 시키기
	1.1 명령 프롬프트 열기 (관리자 권한으로 실행) 
	1.2 Bcdedit.exe -set TESTSIGNING ON 
	1.3 컴퓨터 재부팅 
	
2. OS 를 운영 모드로 전환 시키기	
	2.1 명령 프롬프트 열기 (관리자 권한으로 실행) 
	2.2 Bcdedit.exe -set TESTSIGNING OFF 
	2.3 컴퓨터 재부팅 
