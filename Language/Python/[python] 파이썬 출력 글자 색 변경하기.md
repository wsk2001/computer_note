# [python] 파이썬 출력 글자 색 변경하기

출처: [[python\] 파이썬 출력 글자 색 변경하기 (tistory.com)](https://intotw.tistory.com/128?category=782689)



### ○ python 에서 명령 프롬프트에 출력시 색깔 넣기

- Escape 코드 (색 변경을 위한 시작 코드)와 ANSI Text 색 변경 숫자 코드 (글자색, 배경색)을 사용하여 프린터 명령어 사용시 글자에 색상 및 배경을 넣어봐요.
- '\033[' 이 기호는 Escape sequences 중에서 Control Sequence Introduce (CSI) 를 나타내는 코드이고, Terminal 창에 특수한 역할을 하는 명령을 주는 시작 코드입니다.
- 숫자가 왜 033 이냐 하면, 033 은 oct 로 ESC 코드를 나타내는 것으로, '\033[' 은 '\xb1[' 이나 '\u001b[' 로 대체해도 동일한 효과를 볼 수 있습니다.

 

 참고 URL 

- [위키 백과 - ANSI 이스케이프 코드(Escape Code)](https://en.wikipedia.org/wiki/ANSI_escape_code)
- [위키 백과 - 이스케이프 시퀀스(Escape Sequence)](https://ko.wikipedia.org/wiki/이스케이프_시퀀스)
- [위키 백과 - 이스케이프 문자(Escape Character)](https://en.wikipedia.org/wiki/Escape_character)

### ○ 코드

```python
# -*- coding: utf-8 -*-

BRIGHT_BLACK = '\033[90m'
BRIGHT_RED = '\033[91m'
BRIGHT_GREEN = '\033[92m'
BRIGHT_YELLOW = '\033[93m'
BRIGHT_BLUE = '\033[94m'
BRIGHT_MAGENTA = '\033[95m'
BRIGHT_CYAN = '\033[96m'
BRIGHT_WHITE = '\033[97m'
BRIGHT_END = '\033[0m'

class Colors: 
	BLACK = '\033[30m' 
	RED = '\033[31m' 
	GREEN = '\033[32m' 
	YELLOW = '\033[33m' 
	BLUE = '\033[34m' 
	MAGENTA = '\033[35m' 
	CYAN = '\033[36m' 
	WHITE = '\033[37m' 
	UNDERLINE = '\033[4m' 
	RESET = '\033[0m'

class Background: 
	BLACK = '\033[40m' 
	RED = '\033[41m' 
	GREEN = '\033[42m' 
	YELLOW = '\033[43m' 
	BLUE = '\033[44m' 
	MAGENTA = '\033[45m' 
	CYAN = '\033[46m' 
	WHITE = '\033[47m' 


if __name__ == '__main__':

	print(Colors.RED + 'test 1' + Colors.RESET)
	print(Background.YELLOW + Colors.MAGENTA + 'test 2' + Colors.RESET)
	print(Colors.BLUE + 'test 3' + Colors.RESET)
```

 

### ○ 코드 설명

- '\033[31m'  : Text 색을 빨간색 (Red) 로 변경
- '\033[43m'  : Text 배경색을 노란색(Yellow)로 변경
- 'test 1' : 'test 1' 이라는 문자열 출력
- '\033[0m' : Terminal 속성 초기화 (Reset)

###  

### ○ 실행 결과



![img](.\Images\python_font_color_01.png)실행 화면



 

### ○ 윈도우10에서 작동이 되지 않을때 해결방법

- 윈도우키+R을 눌러서 실행창에 regedit을 입력하여 레지스트리 편집을 실행한다.
- HKEY_CURRENT_USER\Console 에서 DWORD를 생성하여 이름 VirtualTerminalLevel 값을 1을 넣어준다.



![img](.\Images\python_font_color_02.png)



 

 