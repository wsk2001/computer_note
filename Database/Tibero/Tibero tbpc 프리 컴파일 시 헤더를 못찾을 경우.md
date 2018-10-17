### Tibero tbpc 프리 컴파일 시 헤더를 못찾을 경우

tbpc로 프리 컴파일 시 <stddef.h> 표준 라이브러리를 찾을수 없다는 메세지 발생

	$TB_HOME/client/config/tbpc.cfg에 INCLUDE 옵션을 추가 한다.

예시) tbpc.cfg

	INCLUDE=/usr/lib/gcc/x86_64-redhat-linux/4.8.2/include
	INCLUDE=$TB_HOME/client/include
	INCLUDE=/usr/include
	INCLUDE=/usr/include/linux

