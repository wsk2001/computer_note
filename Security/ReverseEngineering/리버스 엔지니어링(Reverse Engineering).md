# 리버스 엔지니어링(Reverse Engineering)

출처: https://maple19out.tistory.com/category/Reverse%20Engineering



## 1. Debugging Tools

### 1. Ollydbg

http://www.ollydbg.de/

www.ollydbg.de

32-bit debugger이다. 위 첨부된 링크에서 다운 받을 수 있으며, 가벼워서 처음 리버스 엔지니어링을 접할 때 이용하기에 좋다. 앞으로 작성할 리버싱 핵심원리 공부 관련 포스팅에서는 Ollydbg를 이용하여 리버싱을 할 것 같다. 64-bit program에서 debugging 할 수 없는 제약사항이 있다.

 

 

### 2. x64dbg

https://x64dbg.com/#start

32-bit 및 64-bit debugging을 할 수 있는 open-source debugger다. 첨부된 링크의 features 항목을 보면 보다 자세한 특징을 알 수 있다. Ollydbg 사용에 익숙해질 때 쯤 x64dbg로 갈아타 볼 생각이다.

 

 

### 3. Ghidra

https://ghidra-sre.org/

NSA(National Security Agency)에서 개발된 reverse engineering tool로써, 2019년 3월 RSA conference에서 대중들에게 공개되었다. 많은 보안 전문가들이 IDA pro의 대항마로 보고 있으며 Java로 작성되어 있다. 비교적 최근에 공개된 오픈소스 소프트웨어이기도 하고, NSA에서 개발한 tool인 만큼 아직 사용해보지는 않았지만 성능이 기대가 된다. 리버싱이 익숙해질 때 쯤 익혀나갈 예정.

 

 

### 4. IDA pro

https://www.hex-rays.com/products/ida/

www.hex-rays.com

Debugging tool의 끝판왕. 다양한 기능을 제공하는 것으로 알고 있지만 가격이 비싸 리버싱을 공부해나가면서 적성에 맞고 정말 필요하다 싶으면 구매해서 사용해 볼 것 같다.



## 2장 Hello World! 리버싱

처음 프로그래밍 언어를 배웠을 때, 아마도 가장 먼저 Hello World!를 출력해보는 프로그램을 작성할 것이다. 마찬가지로 리버싱에서도 Hello World! 프로그램을 분석해보는 방식으로 시작한다. (코드 작성은 Microsoft Visual C++ 2010 Express을 기준으로 하였다.)

### 1. 프로그램 작성

먼저 다음과 같은 코드를 작성하여 Hello World! 문구를 출력하는 프로그램을 만들어보자.

 

```
#include "windows.h"
#include "tchar.h"

int _tmain(int argc, TCHAR *argv[]) {
	MessageBox(NULL, L"Hello World!", L"www.reversecore.com", MB_OK);
    
    return 0;
}
```

(HelloWorld.cpp)

 

Release 모드에서 빌드(Debug 모드로 빌드할 시 프로그램에 Debugging 정보가 포함되어 프로그램 내용이 길어지고 복잡해진다.)를 하게 되면 .exe 파일이 생성될 것이다. 클릭하여서 실행해보자.

 



![img](https://blog.kakaocdn.net/dn/sdMM6/btqNYwp6TbZ/aMcCg04efTvx9qenG3wxYK/img.png)HelloWorld.exe



위와 같이 Hello World!라는 문구를 출력하는 메시지 박스가 출력되는 것을 확인할 수 있다. 이제 위 exe 파일을 디버거를 이용해 그 구조를 살펴보겠다.

 

### 2. Ollydbg를 이용한 분석(1)

Ollydbg를 처음 실행하면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/5lsd9/btqNWEvEn1s/CvbIVV2ym5pvVy3YrqwBT1/img.png)Ollydbg 실행화면



여기서 File->Open(F3)를 이용해 HelloWorld.exe파일을 열어보자.



![img](https://blog.kakaocdn.net/dn/dhwRsZ/btqNU3iRoIv/JB3IPYdRo52iGIuqv6fMDK/img.png)HelloWorld.exe를 연 직후



무언가 굉장히 복잡해 보이지만 크게 4가지 영역으로 나뉘는 것을 확인할 수 있다. 파란 펜으로 표기한 것과 같이 1번 영역은 프로그램의 어셈블리 코드를 나타낸다. 2번 영역은 코드의 진행 시점에서의 register value 및 flag value를 나타낸다. 3번 영역은 Dump 영역으로, data의 ASCII값을 확인할 수 있다. 4번 영역은 stack 영역으로 현재 esp, ebp가 어디에 위치하고 있는지 알 수 있다.

 

 

EP(entry point)에서 하나하나 코드를 실행시키면서 프로그램 내부 구조를 파악할 수 있는데, 생각했던 것 만큼 내부 구조가 단순하지는 않다. 각 컴파일러마다 추가적으로 붙게 되는 Stub Code 등이 있으며, 언뜻 보기에는 무의미해 보이는 assembly 연산도 존재한다. 우선 코드를 하나씩 실행시키기 위해 사용되는 기본적인 기능들로는 다음과 같은 것들이 있다.

| **기능(단축키)**         | **설명**                                           |
| ------------------------ | -------------------------------------------------- |
| Debug->Restart (Ctrl+F2) | 프로그램을 처음부터 restart 한다.                  |
| Debug->Step into (F7)    | 코드를 하나 실행시킨다.                            |
| Debug->Step over (F8)    | 코드를 하나 실행시킨다.(함수 내부로 들어가지 않음) |
| Debug->Run (F9)          | 프로그램을 bp까지 실행한다.                        |

 

 

앞서 말했듯이, 프로그램에 앞에 Stub Code가 붙어있으므로, 우리가 원하는 main 함수를 찾아낼 때 까지 위와 같은 기능을 이용한다. 물론 다른 방법들도 존재하지만, 처음에는 위와 같은 방법이 익숙해지도록 하는 것이 좋다. 코드를 실행시키다보면 다음과 같은 코드를 볼 수 있을 것이다.



![img](https://blog.kakaocdn.net/dn/C6884/btqNYvLvHJM/T2lU5Tn21kJfjr2P14bef0/img.png)HelloWorld.exe의 main내부



이 부분이 우리가 원하는 main 함수의 내부임을 알 수 있다.

 

### 3. Ollydbg를 이용한 분석(2)

언제나 항상 main 함수가 나올 때까지 F7, F8을 연타할 수는 없는 노릇이다. 사실 여러 다른 방법이 있는데 HelloWorld 프로그램의 경우 다음과 같은 방법으로 main 함수를 찾아낼 수 있다.

 

(1) 문자열 검색

HelloWorld 프로그램에서는 HelloWorld! 라는 문자열을 출력한다. 이에 착안하여 프로그램 내부에서 사용되는 문자열을 검색하면 보다 쉽게 main 함수를 찾을 수 있을 것이다. code창에서 마우스 우클릭->Search for->All referenced text strings를 선택하면 다음과 같은 화면이 나온다.



![img](https://blog.kakaocdn.net/dn/PjoSC/btqNZMlVKEg/vGvYGQaIV3A545NOIzBVKk/img.png)문자열 기반의 main 함수 찾기



 

(2) API 검색

MessageBox API를 이용한 다는 점에 착안하여 어디서 호출되는지를 찾아볼 수도 있다. 마우스 우클릭->Search for->Name in all modules를 통해 프로그램에 사용된 모든 API 호출 목록을 찾아볼 수 있다. 아래 첨부사진과 같이 00FE100E 주소에서 MessageBoxW가 호출됨을 알 수 있다.



![img](https://blog.kakaocdn.net/dn/notks/btqNXGfr0Qq/uo95PP6gE5OONzFMgDuuak/img.png)API 기반의 main 함수 찾기



위에서 제시한 방법이외에도 다양한 방법으로 원하는 곳의 주소를 찾을 수 있다. 상황에 따라 적절한 방법을 이용하여 원하는 위치로 이동하는 것에 익숙해질 수 있도록 해야한다.

 

### 4. "Hello World!" 문자열 패치

이제 본격적으로 Hello World 프로그램의 내용물을 바꿔보도록 하겠다. 책에서 제시한 대로 아래와 같은 두 가지 방법을 생각해 볼 수 있다.

 

(1) 문자열 변경



![img](https://blog.kakaocdn.net/dn/nIvaY/btqNXG7yReU/R6znw0GUKSbIxjng1rKI4k/img.png)HelloWorld.exe main



위 코드를 보면 알 수 있듯이, "Hello Word!"라는 문자열이 0FE211C라는 주소에 있음을 알 수 있다. Dump창에서 Ctrl+G를 통해 0FE211C로 이동해보면 다음과 같은 창을 확인할 수 있다.



![img](https://blog.kakaocdn.net/dn/nhQ1E/btqNWg9E2VA/iKLvWH1DgiyZ0v8rjSdZnk/img.png)00FE211C의 dump



원하는 영역만큼 드래그 하여 Ctrl+E(Edit)을 이용하여 내용을 다음과 같이 수정해보았다.



![img](https://blog.kakaocdn.net/dn/VdCmS/btqNYxbuILw/nutEXNmbOrWZJKaVWAZb41/img.png)00FE211C 수정



F9를 통해 프로그램을 실행시켜보자.



![img](https://blog.kakaocdn.net/dn/dwEa8h/btqNVBTXkge/aAeMSVP53fKom1RmNAvbMK/img.png)maple 19 out! 으로 패치



 

(2) 다른 메모리에 문자열을 추가하고 해당 주소를 push하도록 변경

위 첨부 사진에서 00FE213C영역에는 빈 공간이 있음을 알 수 있다. 여기에 변경하고 싶은 문자열을 추가하고 해당 문자열의 주소를 main 함수에서 push 하는 방식을 이용할 수도 있다.



![img](https://blog.kakaocdn.net/dn/cjMY1F/btqNWFOOZ8g/fAemFk92j4J1f9Hn59KWc1/img.png)00FE213C에 문자열 추가

![img](https://blog.kakaocdn.net/dn/do9tzB/btqNU1yAi1P/TBDebk64U9rBfIqRMiUR50/img.png)PUSH 0FE213C로 변경



F9를 통해 프로그램을 실행시켜보자.



![img](https://blog.kakaocdn.net/dn/EziVf/btqNWFOO1h5/jcMbdcL1Y3kg5rhw2ng03K/img.png)maple 20 in!으로 패치



위와 같이 정상적으로 패치가 이뤄졌음을 확인할 수 있다.



## 6장 abex' crackme #1 분석

이번 포스팅에서는 crackme라는 프로그램을 실행하고 분석하여 최종적으로 crack 하는 것이 목적이다.

 

### 1. 프로그램 실행

우선 프로그램을 실행하여 어떠한 프로그램인지 알아보자.

 



![img](https://blog.kakaocdn.net/dn/Lv7aX/btqObYmS20z/LTc5Q2eAFvkMlKg2euJkL1/img.png)프로그램 실행1



이후 확인 버튼을 눌러보자.



![img](https://blog.kakaocdn.net/dn/G6wzk/btqObXBwGv2/3AXsJMdkEN6KmxIg0ZMeG0/img.png)프로그램 실행2



확인 버튼을 다시 누르면 프로그램은 종료가 된다. 첫 번째 캡처 화면에서 보이듯이 HD를 CD-Rom으로 인식하게끔 크랙을 진행해야 함을 유추해 볼 수 있다.

 

 

### 2. Debugging (with Ollydbg)

Ollydbg로 이제 본격적으로 디버깅을 해보자. 프로그램을 Ollydbg로 열면 다음과 같은 코드를 볼 수 있다.



![img](https://blog.kakaocdn.net/dn/Axj7K/btqOcQa6dmk/5gBZkbnKfvPEdAlQPv1bi0/img.png)프로그램의 어셈블리 코드



어떠한 '조건'을 만족시켜야 원하는 대로 크랙을 할 수 있고, 그 '조건'을 만족시키지 못하기 때문에 프로그램이 그대로 종료되는 것 같다. 즉, '조건'과 관련된 명령인 분기문 위주로 코드를 살펴보되, message box에서 나왔던 문자열의 위치 주변으로 코드를 살펴보면 된다. 00401024에서 EAX와 ESI의 비교가 이뤄지는 것을 볼 수 있고, 이에 따라 다음 명령어인 JE가 실행되어 0040103D가 실행(your hd is a cd-rom!), 00401028이 실행(this is not a cd-rom drive)된다. 크랙을 위해서는 0040103D로 분기할 수 있도록 코드를 수정하면 될 것 같다.

 

(1) JE SHORT 0040103D를 수정

가장 간단한 방법은 그냥 조건 없이 JMP SHORT 0040103D로 코드를 수정하여 원하는 코드를 실행하도록 만들 수 있다.

 



![img](https://blog.kakaocdn.net/dn/Ii7aC/btqObX2E85I/Yd1HtrUnzAYmMpf9fIh5Xk/img.png)JE를 JMP로 수정(조건 없애기)



이후 F9를 통해 프로그램을 실행시킨 후, 확인 버튼을 눌러보자.



![img](https://blog.kakaocdn.net/dn/nZg1v/btqObXuLM3s/FVtxMzlrT2SjecJNw79Df0/img.png)YEAH!



정상적으로 크랙이 이뤄졌음을 확인할 수 있다.

 

 

(2) CMP EAX, ESI를 수정

분기하는 조건을 수정하는 방법도 있다. 이후 나오는 명령어인 JE SHORT 0040103D는 ZF(zero flag)가 1인 경우 분기하게 된다. 따라서 ZF가 1이 되도록 CMP EAX, ESI를 CMP EAX, EAX로 변경해주는 방법도 고려해볼 수 있다.

 



![img](https://blog.kakaocdn.net/dn/bOBfUX/btqOcQ92fll/imD0VOlbtWa7kwblblb6Jk/img.png)CMP EAX, ESI를 CMP EAX, EAX로 수정



이후 F9를 통해 프로그램을 실행시킨 후, 확인 버튼을 눌러보자.



![img](https://blog.kakaocdn.net/dn/bUD4Je/btqN9NTtpMx/NbkICOMSwDx9Bx5K0kgWtk/img.png)YEAH!



정상적으로 크랙이 이뤄졌음을 확인할 수 있다.



## x64dbg로 hello world 디버깅해보기

이번 포스팅에서는 x64dbg를 이용하여 hello world를 디버깅해보겠다. dreamhack.io의 x64dbg사용법 강좌를 참고하였다.

 

### 1. x64dbg 다운로드

우선 x64dbg를 이전 Debugging Tools 작성글에 나와있는 링크를 통해 다운로드한다. 다운로드한 후 압축을 해제한 후, release폴더에 있는 x96dbg.exe 파일을 실행해보자.



![img](https://blog.kakaocdn.net/dn/bpkhGN/btqOdOFaM3c/SyJvL00o5dTy0BgLU04Wdk/img.png)귀여운 무당벌레 녀석



이전 포스팅에서도 설명하였듯이, x64dbg는 32-bit 뿐만 아니라 64-bit도 지원한다. 원하는 모드를 선택하면 다음과 같은 창이 나온다.



![img](https://blog.kakaocdn.net/dn/b0Nj8A/btqOjnF2PV2/4SKxMwRyuZjltDGdilwQ00/img.png)x64dbg 실행화면



위 사진은 x64dbg를 실행한 후, 임의로 실행파일은 열었을 때 나오는 화면이다. Ollydbg와 매우 유사한 것을 확인할 수 있으며, 단축키도 거의 비슷하다. 몇몇 주요 단축키들을 정리하면 다음과 같다.

 

| **기능(단축키)** | **설명**                                    |
| ---------------- | ------------------------------------------- |
| F2               | BP 설정                                     |
| F7               | 코드 한 줄 실행(call의 경우 함수 내부 진입) |
| F8               | 코드 한 줄 실행(call의 경우 내부 진입x)     |
| F9               | 프로그램 실행                               |
| Ctrl + g         | 주소 이동                                   |
| -, +             | 이전 또는 다음 주소로 이동                  |
| <enter>          | 분기문에서 target 주소로 이동               |
| <space>          | 어셈블리 코드 수정                          |

 

### 2. x64dbg로 hello world 디버깅해보기

이제 본격적으로 간단한 프로그램을 디버깅해보자. 먼저 다음과 같은 코드를 작성하여 프로그램을 생성한다. (visual studio 2019 / release 모드 / x64 에서 빌드되었다.)

 

```
#include <stdio.h>

void main() {
	puts("hello world!\n");
}
```

(hello-world.cpp)

 

이후 생성된 .exe파일을 x64dbg(64bit mode)로 open하면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/Tkxv1/btqOcQXKeAh/N4b8YYD6hN1nNmEJwOC9U1/img.png)초기 실행화면



이때 초기 설정으로 시스템 중단점 설정이 켜져 있으므로 설정-> 환경설정에서 시스템 중단점 항목을 체크 해제한 후 다시 실행해준다. 그러면 다음과 같이 프로그램의 시작 지점에서 멈춘 상태로 디버깅이 되는 것을 확인할 수 있다.

 



![img](https://blog.kakaocdn.net/dn/WKsAg/btqOiATalLK/Q1RLx0k65vdQYazNQOtjzk/img.png)설정 후 초기 실행화면



### 3. main 함수 찾기

이전에 Ollydbg로 hello world를 디버깅할 때와 마찬가지로 main 함수를 찾아보자. dreamhack 강좌에서는 다음과 같은 세 가지 방법을 제시한다.

 

(1) 정해진 pattern

visual studio 2019 64bit release 모드에서 컴파일된 코드에 한하여 위 첨부 사진의 네 번째 줄에 있는 jmp 명령어에서 <enter> 키를 눌러 target 주소로 이동한 후 스크롤을 조금만 내리면 다음과 같은 코드를 볼 수 있다.



![img](https://blog.kakaocdn.net/dn/dQfLQQ/btqOgulPXN7/1Tk2gA64gwKRivhwDKxaTK/img.png)main 함수 찾기



파란 펜으로 표시한 부분은 바로 main 함수의 argc, argv, envp argument를 Windows API로 설정하는 부분이다. 모든 argument 값을 설정한 후, 동그라미 표시한 call <hello_x64.main>이 바로 main 함수임을 알 수 있다.

 

 

(2) 문자열 검색

작성한 프로그램은 hello world라는 문자열을 출력한다. 이를 이용하여 x64dbg 상단 메뉴 아이콘 중 Az라고 표기되어 있는 아이콘을 누르면 현재 보고 있는 모듈에 있는 문자열들을 참조하는 어셈블리어를 검색해준다.



![img](https://blog.kakaocdn.net/dn/EnSF4/btqOhvEOvfa/X4H0Ecl2KWHOCk6wv6Q3rK/img.png)문자열을 통한 main함수 찾기



위 화면에서 더블클릭하면 해당 문자열이 있는 곳, 즉 main 함수 내부로 이동할 수 있다.

 

 

(3) import한 함수로 찾기

작성한 프로그램에서는 puts라는 함수를 호출한다는 점을 이용하여 main함수를 찾을 수도 있다.

x64dbg 상단 메뉴 아이콘 중 옛날 핸드폰 모양의 아이콘을 클릭하면 현재 모듈에서 import한 모든 함수 목록이 나온다. 상단에 puts함수가 있음을 확인할 수 있으며 이를 통해 main 함수 내부로 이동할 수 있다.



![img](https://blog.kakaocdn.net/dn/bRlKyu/btqOdOrxDvI/BELEiljiDptAW0queRIfJK/img.png)import한 함수로 main함수 찾기



 

Ollydbg와 비교했을 때 더 가독성도 좋아보이고 64-bit도 지원하므로 유용하게 사용할 수 있을 것 같다. 이후 리버싱 핵심원리 포스팅에서도 가끔씩 x64dbg를 이용해 디버깅을 해봐야겠다.



## 7장 Stack Frame

### 1. Stack Frame 개념 설명

 

Stack Frame이란 ESP(스택 포인터)가 아닌 EBP(베이스 포인터) 레지스터를 이용하여 스택 내의 로컬 변수, 파라미터, 복귀 주소 등에 접근하는 기법을 말한다. ESP 값은 프로그램 안에서 수시로 변하기 때문에 EBP를 함수 시작 전에 저장하고 유지하면 안전하게 변수, 파라미터, 복귀 주소 등에 접근할 수 있다.

 

Stack Frame의 어셈블리 코드 부분은 다음과 같다.

```
PUSH EBP
MOV EBP, ESP
...
...
...
MOV ESP, EBP
POP EBP
RETN
```

함수가 호출되고 나면 PUSH EBP를 통해 기존 EBP값(함수 호출 이전의 EBP 주소)을 저장한다. 이후에 MOV EBP, ESP를 통해 ESP 값을 EBP에 저장하게 되면, 함수 내부의 로컬 변수는 EBP-4 등으로, 파라미터는 EBP+4등으로 접근이 가능해진다. 함수의 기능을 다 수행하고 나면 MOV ESP, EBP를 통해 스택을 정리해주고 마지막으로 POP EBP를 통해 함수 호출 이전의 EBP 값을 복원한다. 마지막으로 RETN을 통해 스택에 저장되었던 복귀 주소로 돌아간다. 

 

그림으로 EBP, ESP를 도식화하면 다음과 같다. (단, 스택은 거꾸로 자라므로 ESP가 위로 올라가면 주소는 줄어든다.)

 



![img](https://blog.kakaocdn.net/dn/vS7qi/btqOMqJtBiQ/HxmOXM6q5SAfkdezT6qnm0/img.png)Stack 내부



 

### 2. 프로그램 작성

 

본격적으로 stack frame을 어셈블리 레벨에서 확인해보기 위해 간단한 프로그램을 작성해보자. 다음과 같은 코드를 작성하고 빌드하여 프로그램을 생성한다. (visual c++ express 2010 release 모드 기준으로 빌드하였으며, 최적화 옵션을 끈 상태로 컴파일하였다. 최적화 옵션을 켰을 경우 함수 생성이 안 될 수도 있다.)

 

```
#include "stdio.h"

long add(long a, long b)
{
	long x=a, y=b;
    
    return (x+y);
}

int main(int argc, char* argv[])
{
	long a=1, b=2;
    printf("%d\n", add(a, b));
    
    return 0;
}
```

(StackFrame.cpp)

 

코드를 보면 알겠지만, 간단한 두 정수의 덧셈 결과를 반환하는 함수를 만들어 호출하고 그 결과를 출력하는 함수다.

 

 

### 3. Debugging(with x32dbg)

x32dbg를 이용하여 해당 프로그램을 열어보자.



![img](https://blog.kakaocdn.net/dn/Cw8K9/btqOxsvlxHX/6kIWqcktsR6kOThPEUSoK1/img.png)StackFrame.exe의 초기 디버깅 화면



cpp 파일은 몇 줄 안되는데 언제나처럼 굉장히 많은 코드 정보가 눈에 들어온다. F7, F8을 연타하여 main 함수를 찾아낼 수도 있겠지만, 보다 수월하게 찾는 방법을 생각해보자. 다양한 방법이 있겠지만, printf 함수를 main에서 호출하므로 마우스 우클릭-> 모듈 간 호출을 통해 한번 찾아보자.

 



![img](https://blog.kakaocdn.net/dn/b3tGn3/btqOCdLfyFw/639QkNldwtZbFcrf9Vs97k/img.png)호출된 모듈들에 대한 정보창



고맙게도 첫 번째 줄에 printf가 나와주었다. 더블클릭하여 해당 주소로 이동해보자.



![img](https://blog.kakaocdn.net/dn/bEU1g1/btqOIamSBiv/jl8K43326gbWIUEB8NamG1/img.png)main 함수



위와 같은 형태의 코드가 보인다. 과연 이것이 main 함수일까? 위 코드를 한 줄씩 분석해보면 다음과 같은 특징을 보인다.

 

(1) 우선 첫 두 줄은 push ebp / mov ebp, esp 명령을 수행한다. 이는 앞서 설명한 Stack Frame을 형성하는 과정과 동일하다. main 함수 역시 함수이므로 같은 방식으로 함수가 시작하는 것을 확인할 수 있다.

 

(2) 00B81026 와 00B8102D 주소의 mov 명령어는 1, 2를 각각 ebp로부터 4, 8 떨어진 곳에 저장한다. 이는 StackFrame.cpp 파일의 long a=1, b=2; 부분과 관련지을 수 있다.

 

(3) 00B81034 ~ 00B8103B의 코드는 eax, ecx 레지스터에 아까 1, 2를 저장했던 변수에 저장된 값을 mov 연산을 통해 옮겨주고, 이후에 call 연산을 통해 add함수를 호출하는 것을 확인할 수 있다.

 

즉, 우리가 찾는 main함수임을 알 수 있다. 이제 main함수를 찾았으니, main의 시작 부분에 bp를 설치하고 실행을 한 후, EBP, ESP 값이 어떻게 변화하는지 관찰해보자. 우선 main()의 함수 시작 시 스택은 다음과 같은 상태이다.



![img](https://blog.kakaocdn.net/dn/l9y35/btqOH8WUP0V/8pheikVodI21grLV5v5uqk/img.png)main()의 시작 초기 스택 상태

![img](https://blog.kakaocdn.net/dn/bPVbpW/btqON9Hfs9y/ol7QauEKKWRXUCcIHWGPy0/img.png)초기 ESP, EBP 값



이때 ESP에 저장된 값은 main()의 실행이 끝난 후 돌아갈 리턴 주소임을 알고 있자. 현재 상태에서 PUSH EBP를 실행하면 스택에 EBP값이 저장된다. 이렇게 저장하는 이유는 함수가 실행되기 이전의 EBP값을 백업하기 위함이다. 이후에 MOV EBP, ESP를 통해 ESP의 값을 EBP에 옮긴다. 이후에는 main 함수가 끝날 때까지 EBP 값은 고정된다. 스택 창을 마우스 우클릭에서 EBP 따라가기, ESP 따라가기 등을 선택하여 EBP, ESP 관점에서 스택을 볼 수도 있다.

 



![img](https://blog.kakaocdn.net/dn/dthHoj/btqOH8P87yH/h1agTqqEcz22zBdUm50qG0/img.png)EBP 따라가기 수행후 스택창



 

이제 push ebp / mov ebp, esp를 수행한 후의 스택창을 살펴보자.



![img](https://blog.kakaocdn.net/dn/bhTOFw/btqON8uObaO/ehkhxchliEbid7FB9cPMZk/img.png)

![img](https://blog.kakaocdn.net/dn/xeIva/btqOMpqjzYw/Uuz3jOW5mCrc2moeX1hbGk/img.png)EBP, ESP 변화



위와 같이 Stack Frame이 정상적으로 생성되었음을 확인할 수 있다.

 

이제 add 함수를 호출하기 이전 과정을 살펴보자.



![img](https://blog.kakaocdn.net/dn/KTr4A/btqOKZrUBR0/rtuecDAswuUYHdVlPJiuQk/img.png)add 함수 호출 직전까지의 코드



우선 main 함수에서는 2개의 long 변수를 선언한다. 하나의 long 변수는 4바이트를 차지하므로, sub esp, 8을 통해 2개의 변수를 할당할 수 있게 스택에 공간을 할당해준다. 이후에 mov 연산을 통해 1, 2를 새로 생성된 공간에 할당해준다. 이때, ebp를 통해 로컬 변수에 접근하는 것을 확인할 수 있다. 해당 변수가 할당된 직후 StackFrame.cpp 파일에서는 add 함수를 호출한다. 이를 위해 argument를 넘겨줘야 하는데, eax와 ecx 레지스터에 각각 값을 이동시킨 후, push 연산을 통해 스택에 채워넣는다. 이 때 눈여겨볼 점은 바로 변수가 역순으로 스택에 쌓인다는 것이다. 스택은 LIFO(Last In First Out) 구조이기 때문에, 역순으로 집어넣게 되면 함수 쪽에서는 올바른 순서로 파라미터를 꺼낼 수 있기 때문이다. push ecx까지 수행한 후 ESP, EBP 값은 다음과 같다.



![img](https://blog.kakaocdn.net/dn/4z5S5/btqOCcS7cFR/2FrK73IdfikjfGKqT1sVEk/img.png)스택 창. local variable과 argument가 들어간 것을 확인할 수 있다.

![img](https://blog.kakaocdn.net/dn/bHYxyX/btqOzBlpKRZ/02alOBGftLIlNoAINBl0dK/img.png)EBP, ESP 값



이제 call 부분을 실행시켜 add 함수 내부로 들어가 보겠다. call 부분이 실행되면 해당 함수로 들어가기 전에 CPU는 해당 함수가 종료될 때 복귀 주소(다음으로 실행되어야 할 명령어 주소)를 스택에 저장한다.



![img](https://blog.kakaocdn.net/dn/bo1ohC/btqOMoZgXl0/65WXcgk5fKsdGFGektCxS1/img.png)call 실행 후, 스택 창

![img](https://blog.kakaocdn.net/dn/pbvQk/btqOH8WXJcp/XHsUoE3prsuUdCWxNYPFG1/img.png)스택의 끝 부분에 저장된 값과 함수가 끝난 후 다음으로 수행되어야 할 명령어 주소가 일치한다.

![img](https://blog.kakaocdn.net/dn/cEtsIL/btqOM8PpmDB/LJ8g1gjrCFifpvpnfArZcK/img.png)add 함수 내부



위와 같이 add 함수 내부 역시 push ebp / mov ebp, esp로 시작하고, ret(return) 이전에 mov esp, ebp / pop ebp를 이용해 Stack Frame이 생성/삭제되는 것을 확인할 수 있다. add 함수는 return 값이 있는 함수인데, return 값이 있는 함수의 경우 해당 값을 eax 레지스터에 저장한다. 이는 00B81015 주소의 명령어에서 확인할 수 있다. 이제 ret까지 수행하고 난 후의 스택 창을 확인해보자.



![img](https://blog.kakaocdn.net/dn/kEzI8/btqOFiS7sKF/UjeZXY6Ki5UbbYkas31yok/img.png)add 함수 return 한 후 스택창



위와 같이 함수 호출 직전의 스택과 동일한 것을 확인할 수 있다. 이제 마지막으로 main 함수의 add 함수 호출 이후 부분의 코드를 잠시 살펴보자.



![img](https://blog.kakaocdn.net/dn/c2SH3e/btqOFjYKfMG/Q43rJms8fJCZX4G1CQYDMk/img.png)add 함수 호출 이후 main 함수



가장 상단 부분의 add esp, 8은 함수의 호출이 끝났으니, 사용했던 argument는 더 이상 필요가 없으므로 스택에서 제거해주는 과정이다. StackFrame.cpp에서는 이후에 printf 함수를 호출하므로 add 함수를 호출하는 과정과 마찬가지로 eax(add 함수의 반환 값)과 B820F4 주소(서식 문자 %d가 있다.)를 push 해주고 printf함수를 호출한다. 마찬가지로 add esp, 8을 통해 스택에 쌓아두었던 argument를 해제해준다.

 

 

위와 같이 함수가 호출되면 stack frame이 먼저 생성되고 ebp 레지스터를 이용해 각종 변수/파라미터에 접근한 후, 함수가 종료되기 직전에 stack frame을 해제하는 것을 확인할 수 있다.



## 8장 abex' crackme#2 분석(1)

이번 포스팅에서는 abex' crackme#2라는 프로그램을 분석해본다. 해당 프로그램은 Visual Basic으로 제작되어 visual C++로 작성된 프로그램과는 다른 형태의 디스어셈 코드를 보이는데, 이런 부분에 대한 설명은 생략하고 크랙 하는데 초점을 맞춰 글을 작성해보겠다.

 

### 1. abex' crackme#2 실행

우선 해당 프로그램을 실행시켜보자.



![img](https://blog.kakaocdn.net/dn/JyqAE/btqOP47TFGo/XXpCfKKMafTaJDXYl96JHK/img.png)실행화면



Name과 Serial 입력란과 세 가지 버튼이 보인다. 왠지 name과 serial을 입력하고 Check를 통해 만족하는 serial key인 경우 통과하는 형태의 프로그램으로 보인다. maple19out / maple19out을 입력하고 Check 버튼을 눌러보았다.

 



![img](https://blog.kakaocdn.net/dn/bfZbfi/btqOVKgd7fM/3HjmWTeSUTjy3vslKeXF2K/img.png)성질내는 메시지박스



예상대로 뭔가 조건을 만족하지 않아 Wrong이라는 메시지박스가 창에 나타난다.

 

 

### 2. debugging (with x32dbg)

이제 x64dbg를 이용하여 프로그램을 열어보자.



![img](https://blog.kakaocdn.net/dn/uGUaf/btqON9BzdC1/kNK2EvkEQeWapS8VKLVH0k/img.png)초기 디버깅 화면



본격적으로 코드를 들여다보기 전에, 무작정 코드를 하나하나 실행시키기보다 전략적으로 리버싱을 해보자. 해당 프로그램은 메시지 박스에서 '문자열'을 출력한다. 따라서 마우스 우클릭을 이용해 문자열을 참조하는 곳이 있는지 확인해보자. 오류가 날 때 출력하는 'Nope, this serial is wrong!' 부분을 찾아보면 좋을 것 같다.

 



![img](https://blog.kakaocdn.net/dn/zCTpu/btqOUk9WjLc/6STNFHiQDm5Uxr5RPZuYr0/img.png)문자열 출력 부분



여기서 이제 이러한 문자열을 출력하게 되는 배경을 생각해보자. 처음에 이름과 시리얼 키를 입력하였고, 어떠한 조건에 의해 wrong이라는 문자열을 출력하게 되는 것이다. 따라서 이에 대응되는 조건 분기문이 있을 것임을 예측해 볼 수 있다. 스크롤을 조금 올려보자.

 



![img](https://blog.kakaocdn.net/dn/kFG8U/btqOS9t5dxF/5Z7wTwzlmpjapjpYlOkGx1/img.png)조건문 발견



test 연산자는 logical compare(AND)를 통해 값을 세팅하고 바로 밑의 je 부분에서 jump가 일어난다. 만약 점프가 일어나지 않는다면 밑의 코드를 보아 Congratulations! 와 같은 문자열이 나와있는 것으로 보아 크랙에 성공할 수 있음을 예측해볼 수 있다. 조금만 더 스크롤을 올려 상단에 있는 어셈블리 코드를 살펴보자.



![img](https://blog.kakaocdn.net/dn/53dLK/btqOKY8E6QU/MFPf5UfifrE1Hx6phKW9i0/img.png)argument passing...?



test 코드가 실행되기 이전에 어떤 함수가 호출되고, 그 이전에 push edx, push eax가 실행되는 것으로 보아 함수가 호출되기 이전에 argument passing이 이뤄지는 것 같다. 왜 인지 입력 serial key와 실제 serial key 간에 string compare를 하고, 그 결과가 eax 레지스터에 반환되는 것 같다. edx와 eax 레지스터에 무엇이 담겨있는지 확인하기 위해 00403327 부분에 bp를 설정하고 프로그램을 실행시켜보자.



![img](https://blog.kakaocdn.net/dn/KPwe8/btqOKY8Fbfv/KdUCbJnxptsOKQH2wQJxg1/img.png)eax, edx 레지스터 변화



위 사진과 같이 lea 연산을 통해 eax 레지스터에는 0019F2A0가, edx 레지스터에는 0019F290이 담겨있음을 확인할 수 있다. 이제 덤프 창에서 해당 주소로 이동해보자. 



![img](https://blog.kakaocdn.net/dn/LFgz8/btqOP5yZj8W/XAdogopNYS9IhrJldyoGQk/img.png)hex dump 창



이렇게만 봐서는 무슨 내용이 들어가 있는지 잘 보이지가 않는다. 마우스 우클릭을 통하여 스택에서 따라가기 기능을 이용하면 해당 주소 값에 무엇이 들어가 있는지 한눈에 보인다.

 



![img](https://blog.kakaocdn.net/dn/Oj7n1/btqOKXvdtPD/Ne8yJOzBD88pEuA94XkkdK/img.png)스택 창



위와 같이 edx, eax 주소 값으로부터 8byte 떨어진 곳에 처음에 입력했던 maple19out 문자열과 D1C5D4D0라는 시리얼 냄새가 나는 문자열이 보인다. 실제로 Visual Basic에서 사용하는 문자열 객체는 바로 문자열이 나타나지 않는다고 한다. 이제 저 D1C5D4D0이라는 문자열을 한 번 serial key에 입력해보자.

 



![img](https://blog.kakaocdn.net/dn/StyXa/btqOMqX8U7k/2NlSX5Tv41vvCzwkUOAS41/img.png)크랙 성공!



위와 같이 크랙에 성공하였음을 확인하였다. 그러나 Name에 다른 문자열을 집어넣으면 D1C5D4D0이라는 시리얼 키로는 통과하지 못하는 것을 확인할 수 있다. 즉, Name에 따라 Serial Key가 변한다는 것인데 이에 대해서 Serial 생성 알고리즘을 다음 포스팅에서 다뤄보겠다.



## 8장 abex' crackme#2 분석(2)

지난 포스팅에 이어 이번에는 serial 생성 알고리즘을 살펴보겠다. abex' crackme#2 프로그램에서는 이름/시리얼을 입력하고 [Check] 버튼을 클릭한 후, 조건에 대한 검사를 통해 올바른 시리얼 키인지 아닌지를 판별한다. 즉, 이전에 찾았던 조건 분기 코드는 check button을 클릭했을 때 호출되는 함수의 내부에서 작동되었을 것이라고 예측할 수 있다.

 

### 1. 함수 시작 찾기

이전의 stack frame 관련 포스팅 내용을 바탕으로, 함수의 시작을 찾아보자. 스크롤을 올리다 보면 다음과 같은 명령어를 발견할 수 있다.



![img](https://blog.kakaocdn.net/dn/YQ0PT/btqO3zTkFEZ/QBvqjYqimTOetGGas2qjc0/img.png)함수의 시작



push ebp / mov ebp, esp 어디서 많이 보던 코드다. 바로 stack frame을 구성하기 위해 함수의 시작 부분에서 ebp를 새롭게 설정하는 부분이다. 즉, 해당 부분이 함수의 시작 부분임을 알 수 있다. 해당 부분을 기준으로 아래쪽으로 코드를 살펴보면서 name 문자열을 읽는 부분을 살펴보면 될 것 같다.

 

 

### 2. Name 문자열을 읽는 코드

해당 부분부터 디버깅을 진행하다 보면 다음과 같은 코드를 살펴볼 수 있다.



![img](https://blog.kakaocdn.net/dn/OETkL/btqO0S6UxXB/cDuImmHez2GBnBZxKrYhHK/img.png)함수 내부



여기서 [ebp-88]주소에 어떤 것이 들어가 있는지 확인하기 위하여 해당 명령어에 위치한 상태에서 enter키를 눌러보자.

 



![img](https://blog.kakaocdn.net/dn/bmk8eK/btqOYvqNUYy/X8jiYydnxD9KkKnKolkag1/img.png)ebp-88



아직까지는 특별히 들어간 것이 없다. 이제 call까지 실행된 후의 값을 확인해보자.



![img](https://blog.kakaocdn.net/dn/dlmQug/btqOVJpodkw/XrbjFGGJl2tnpizBZ1PbR0/img.png)Name 문자열



위와 같이 Name 문자열이 ebp-88 부분에 들어간 것을 확인할 수 있다.

 

 

### 3. 암호화 루프

계속해서 디버깅을 하다보면 다음과 같은 Loop를 만나게 된다.



![img](https://blog.kakaocdn.net/dn/Am9zw/btqO6gFX26c/kAKCG9ruQJqiRvidGgQMok/img.png)

![img](https://blog.kakaocdn.net/dn/bdmoSp/btqOS7YEZDe/lJqC339KQno0xU0SkTWH51/img.png)

![img](https://blog.kakaocdn.net/dn/oq20k/btqOVK2Wnke/VYdK3xIaAkLykQO6kfMcCk/img.png)



간단히 설명하자면 00403197의 test ax, ax를 통해 조건을 검사하고 0040319F부터 loop가 시작된다. 이후에 004032A0를 통해 다시 loop의 조건 검사 부분으로 jump 하게 된다. vbaVarForInit(), vbaVarForNext는 문자열 객체에서 한 글자씩 참조할 수 있도록 도와주는 역할을 하며, mov ebx, 4 부분은 loop count를 세팅해주는 역할을 한다.

 

 

### 4. 암호화 방법



![img](https://blog.kakaocdn.net/dn/b2XDYd/btqOSxpIrON/vPQb2chCsXd1c9fYJlLnh0/img.png)



디버깅을 진행하다 보면 위와 같은 부분을 발견할 수 있다. 우선 004031F6의 연산을 통해 Name 문자열에서 하나의 문자(UNICODE)를 가져온다. 'm'이 될 것이다. 004031F7에 의해서 'm'(UNICODE)를 'm'(ASCII CODE)로 변환한다. m은 아스키 값으로 109다. 이제 맨 아래의 call vbaVarAdd를 통해 어떻게 값이 변하는지 살펴보자.

 



![img](https://blog.kakaocdn.net/dn/qB6IG/btqO6hdPH1g/48zINiTdtX6dthzYGy5Pr1/img.png)vbaVarAdd의 내부



또 내부적으로 VarAdd라는 것을 호출하는데 그에 앞서 [esp+4], [esp+C], [esp+14]의 주소 값을 argument로 넘기고 함수를 호출하고 있다. 이때의 스택에는 다음과 같은 값들이 저장되어 있다.



![img](https://blog.kakaocdn.net/dn/ddD5Gl/btqO3yfSoq9/29KtZi0DJ7nc3ikNRkCsCK/img.png)스택



이제 VarAdd내부로 들어가 보자.



![img](https://blog.kakaocdn.net/dn/blQNVf/btqO6gTxO9l/eKeP9wYXmyY4fgemXePOyk/img.png)하...



굉장히 복잡해 보이는데 핵심은 바로 766E9DAB의 add edx, eax부분이다. 이 부분을 통해 'm'(109)에 64h(100)라는 값이 더해지고 결과적으로 D1(109 + 100) 이 된다. 함수가 종료되면 edx 레지스터에는 D1이라는 값이 남게 된다. 결과적으로 암호화 알고리즘은 각 문자열에 64 hex 값을 더한 것이 serial key가 됨을 알 수 있다.



## 10장 함수 호출 규약

함수 호출 규약(Calling Convention)은 함수를 호출할 때 어떤 방식으로 argument를 전달할지, 함수가 끝난 후 어떤 방식으로 ESP(스택 포인터)를 정리하는지에 대한 약속이다. 대표적으로 cdecl, stdcall, fastcall 등이 있다. Ollydbg로 예제 프로그램의 어셈블리 코드를 살펴보면서 각 방식의 특징을 살펴보겠다.

 

### 1. cdecl

주로 C언어에서 사용되는 방식이며, **'Caller'**에서 스택을 정리하는 특징을 보인다.

```
#include "stdio.h"

int add(int a, int b)
{
	return a+b;
}

int main(int argc, char* argv[])
{
	return add(1, 2);
}
```

위와 같은 코드를 VC++에서 최적화 옵션을 끄고 빌드한 후, 디버깅해보자.

 



![img](https://blog.kakaocdn.net/dn/VxUrE/btqPemzWYpc/EUdl6TMRB7lofDyvKUBSdk/img.png)main함수와 add함수



00401010은 main함수, 00401000은 add 함수를 나타낸다. 여기서 CALL 00401000이 수행된 직후에 ADD ESP, 8 연산을 통해 스택을 정리하는 것을 확인할 수 있다. 이와 같이 Caller에서 자신이 스택에 넘긴 argument를 직접 정리하는 방식이 cdecl이다.

 

 

### 2. stdcall

stdcall 방식은 Win32 API에서 사용되며, **'Callee'**에서 스택을 정리하는 것이 특징이다. C언어는 기본적으로 cdecl 방식을 사용하며, stdcall 방식을 이용하기 위해서는 _stdcall 키워드를 함수 이름 앞에 붙여주면 된다.

 

```
#include "stdio.h"

int _stdcall add(int a, int b)
{
	return a+b;
}

int main(int argc, char* argv[])
{
	return add(1, 2);
}
```

똑같이 빌드해보고 디버깅해보자.

 



![img](https://blog.kakaocdn.net/dn/cWueab/btqO6h71lFl/B4p7MioDfHd3FbcI2ElK0k/img.png)main 함수와 add함수



cdecl과 비교했을 때 다른점은 함수 호출 직후 따로 ADD ESP, 8이 없으며, add 함수 내부에서 RETN 대신 RETN 8로 보트가 바뀌었다. RETN 8이란 RETN + POP 8과 같은 의미로, 리턴 후 지정된 크기만큼 ESP를 증가시킨다. stdcall 방식의 장점은 함수를 호출할 때마다 ADD ESP, XXX와 같은 명령어가 필요 없어 코드 size가 줄어든다. 

 

 

### 3. fastcall

fastcall은 기본적으로 stdcall과 같지만 함수에 argument를 전달할 때 일부(2개까지)를 레지스터를 이용하여 전달한다. 예를 들어 argument가 4개인 경우, ecx, edx 레지스터를 이용하여 앞의 두 argument를 전달한다. 따라서 fastcall은 좀 더 빠른 함수의 호출이 가능하다. 그러나 레지스터에 중요한 값이 저장되어있는 경우, 백업해야 하는 오버헤드가 발생할 수도 있다.



## 11장 Lena's Reversing for Newbies

Lena라는 사람이 [www.tuts4you.com/](https://www.tuts4you.com/)게시판에 40여 개의 crackme라는 강좌를 올려놓았다고 한다. 나중에 한 번 들어가서 보는 것도 괜찮을 것 같다. [forum.tuts4you.com/files/categories/](https://forum.tuts4you.com/files/categories/) 에서 관련된 파일을 다운로드할 수 있다. 리버싱 핵심원리 책에서는 Tut.ReverseMe1.exe 파일을 다뤄보고 이에 대한 crack을 진행한다.

 

### 1. 프로그램 실행



![img](https://blog.kakaocdn.net/dn/cz4RS4/btqQ8NcevOW/3lUb5Xbxzq5E5dQPJqI89K/img.png)실행화면 1



위와 같은 문구가 출력된다. 확인 버튼을 눌러보자.



![img](https://blog.kakaocdn.net/dn/FAZbp/btqQ8MEwuvI/YjWcwa5zMMaOp4eIHFZav0/img.png)실행화면 2



무언가 적합한 Regcode를 발견하여 Register me! 버튼을 눌렀을 때 통과할 수 있는 구조 같다. SmartCheck는 Numega 사에서 만든 크래커들이 자주 이용하는 툴이라고 한다. Nag? 버튼을 클릭해보자.



![img](https://blog.kakaocdn.net/dn/McSYu/btqQ8MYNOtw/1mKfIKIzVxdnESLlMz8TSk/img.png)실행화면 3



위와 같이 프로그램을 초기에 실행시켰을 때와 같은 메시지 창이 출력된다. 확인 버튼을 누르면 다시 실행화면 2와 같은 상태로 돌아간다. 이제 아무 키나 입력하고 Register me!를 눌러보자.



![img](https://blog.kakaocdn.net/dn/qVD0l/btqRhe0ImfN/i8Vrg2FN0rekqeN8UeDpr1/img.png)실행화면 4



역시 Wrong registration code !라는 문구를 출력한다. 이제 본격적으로 크랙을 하기 위해 디버깅을 해보자.

 

### 2. Debugging(with x32dbg)



![img](https://blog.kakaocdn.net/dn/dyK8yE/btqReRkExoA/kAiY2L5FHrh5fTmaUAjQCK/img.png)디버깅 1



우선 프로그램이 메시지 박스를 출력한다는 점에 착안하여, 메시지 박스 호출 부분을 조작해보자. 마우스 우클릭을 통하여 MsgBox가 호출되는 부분을 검사해보자.



![img](https://blog.kakaocdn.net/dn/puPqA/btqRigKKTzu/B2PFxN0sJUl8LE5QAvjRR0/img.png)디버깅 2



위와 같이 총 4 곳에서 MsgBox가 호출되는 것을 확인할 수 있다. 마우스 우클릭을 통하여 모든 부분에 BP를 설정하고, F9를 눌러 프로그램을 진행시켜보자.



![img](https://blog.kakaocdn.net/dn/dm4yqk/btqRjgjrqOk/38NawTepgLXbIIKIfdXL31/img.png)디버깅 3



위와 같이 00402CFE 지점에서 멈췄는데 윗 부분에 "Get rid of all Nags and find the right registration code !"라는 문자열이 있는 것을 확인할 수 있다. 계속 F9를 눌러 실행시켜보면 이전 실행화면과 같은 창이 나타나는 것을 확인할 수 있다. 따라서 00402CFE 지점을 적절히 수정한다면 Get rid of all Nags 이 부분을 달성할 수 있을 것 같다. 먼저 해당 부분을 Call ... 대신 add esp, 14로 스택을 정리하는 형태로 어셈블리 명령어를 수정해보는 접근을 생각해 볼 수 있다. 그러나 이러한 방법은 MsgBox의 return 값이 담기는 eax 레지스터에 대한 처리를 할 수 없으므로 다른 방법을 생각해봐야 한다. 스크롤을 조금 더 올려보자.



![img](https://blog.kakaocdn.net/dn/cl7i6g/btqQ58gTlQf/xRez33fjLWGHJEVTGW7im0/img.png)디버깅 4



위와 같이 stack frame을 생성하는 코드를 발견할 수 있다. 따라서 다음과 같이 생각해 볼 수 있다. 어떠한 함수 A가 호출되고, A안에서 MsgBox가 호출된다. 따라서 함수 A가 호출되는 부분에 바로 return을 하게 되면 MsgBox가 호출되지 않을 것이다. stack frame을 생성하는 코드를 다음과 같이 바꿔보자.



![img](https://blog.kakaocdn.net/dn/bD5BCM/btqRePUHMqk/88WeV8Jtwk3YpmgfRmWPP1/img.png)디버깅 5



위와 같이 수정하고 다시 프로그램을 실행시켜보면 Nag? 버튼을 클릭하여도 아무런 반응이 일어나지 않는 것을 확인할 수 있다.

 

이제 Regcode를 살펴보자. 적절한 Regcode가 입력으로 들어온다면 그에 대한 처리가 있을 것이고, 적절하지 않은 Regcode가 입력으로 들어오면 실행화면 4와 같은 창이 나오게 된다. 이번에는 출력되는 문자열에 기반하여 디버깅을 해보자. 우선 마우스 우클릭을 하여 문자열이 출력되는 부분들을 확인해보자.



![img](https://blog.kakaocdn.net/dn/d4LfNI/btqRavWMG0w/4SkNFyhtkuOrbSgvBtQKLk/img.png)디버깅 6



아마도 Yep ! You succded registering ! 문자열을 출력하는 조건이 적합한 Regcode가 들어왔을 때임을 직감할 수 있다. 해당 부분으로 이동해보자.



![img](https://blog.kakaocdn.net/dn/nbpcB/btqRgiI41G3/NpiZ31QVcEO3rYb68nYwj0/img.png)디버깅 7



해당 문자열의 윗부분에 "I'mlena151" 이라는 문자열을 stack에 쌓고 varStrCmp라는 함수를 호출하는 것을 확인할 수 있는데 vbaStrCmp()는 Visual Basic에서 사용자가 입력한 문자열과 대상 문자열을 비교하는 함수이다. I'mlena151을 Regcode로 입력하고 Register me!버튼을 눌러보자.



![img](https://blog.kakaocdn.net/dn/P8EHR/btqRjeMJn0L/TDHaY3pzrLzcDavpC7crc1/img.png)디버깅 8



위와 같이 크랙에 성공하였음을 알 수 있다.



## 13장 PE File Format (1)

PE(Portable Executable) 파일은 Windows 운영체제에서 사용되는 실행 파일 형식이다. 기존 UNIX의 COEF(Common Object File Format)을 기반으로 Microsoft에서 만들었으며, 문자 그대로 이식성을 좋게 하려는 의도가 있었으나 Windows 운영체제에서만 사용된다.

 

notepad.exe(32bit)을 헥스 에디터 HxD를 이용하여 열어보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/dN0pce/btqRrQZeL7z/0wfdh6W5Fvwf4x6ZURedl1/img.png)notepad.exe



위 그림은 notepad.exe 파일의 시작 부분이며, PE 파일의 header 부분이다. 헤더 부분에는 파일이 실행되기 위해 필요한 모든 정보들이 있으며 구조체 형식으로 저장되어 있다. PE File Format을 공부한다는 것은 PE 헤더 구조체를 공부한다는 것과 같은 말이다.

 

### 1. 기본 구조

아래 그림은 notepad.exe 파일이 메모리에 loading 될 때의 모습을 나타낸다.

 



![img](https://blog.kakaocdn.net/dn/ICFsz/btqRoP1lghJ/feuJqKGxsn6vWo4qb8lT81/img.png)PE 파일이 메모리에 loading 되었을 때의 모습



DOS header부터 Section header까지를 PE 헤더, 밑의 Section들을 합쳐 PE 바디라고 한다. 파일에서는 offset으로, 메모리에서는 VA(Virtual Address)로 위치가 표현되며 파일이 메모리에 로딩되면 모양이 달라진다. Section header에는 각 Section에 대한 파일/메모리에서의 크기, 위치, 속성 등이 정의되며 각 section의 끝에는 NULL padding이라고 불리는 영역이 존재한다.

 

VA와 RVA에 대해 구분할 줄 알아야 하는데, VA(Virtual Address)는 프로세스 가상 메모리의 **'절대 주소'**를 말하며, RVA(Relative Virtual Address)는 어느 기준 위치(ImageBase)에서부터의 **'상대 주소'**를 가리킨다. 즉 다음과 같은 수식으로 나타난다.

**RVA + ImageBase = VA**

이는 프로세스가 메모리에 로딩되는 순간 이미 다른 파일이 로딩되어있는 경우, relocation을 통해 다른 위치에 로딩하기 위한 것이다.

 

### 2. PE 헤더

#### 1) DOS Header

PE File Format을 만들 당시, 널리 사용되던 DOS 파일에 대한 하위 호환성을 고려하여 만들어진 부분이다. IMAGE_DOS_HEADER 구조체로 구성되어 있으며 해당 구조체는 같은 구조를 갖는다.

```
typedef struct _IMAGE_DOS_HEADER {
	WORD e_magic;	//DOS signature : 4D5A("MZ")
    WORD e_cblp;
    WORD e_cp;
    WORD e_crlc;
    WORD e_cparhdr;
    WORD e_minalloc;
    WORD e_ss;
    WORD e_sp;
    WORD e_csum;
    WORD e_ip;
    WORD e_cs;
    WORD e_lfarlc;
    WORD e_ovno;
    WORD e_res[4];
    WORD e_oemid;
    WORD e_oeminfo;
    WORD e_res2[10];
    LONG e_lfanew;	//offset to NT header
 } IMAGE_DOS_HEADER, *PIMAGE_DOS_HEADER
```

구조체의 크기는 40이며, 중요한 멤버로 e_magic과 e_lfanew가 있다.

e_magic : DOS의 signature를 나타내는 부분으로, MZ값이 와야한다.

e_lfanew : NT Header 구조체의 offset을 나타낸다. HxD에서 해당 부분을 살펴보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/byYagz/btqRrPF4Fyt/3XTnI5HcAcyS7Ut0J4koz0/img.png)IMAGE_DOS_HEADER



MZ의 아스키 값인 4D5A가 e_magic 부분에 나타나고, 000000E0 (little endian임에 주의)가 e_lfanew 값으로 나타나는 것을 확인할 수 있다.

 

#### 2) DOS Stub

DOS Header 밑에는 DOS Stub이 존재한다. Optional 한 영역으로 없어도 되며 NT Header가 시작되기 이전까지의 영역이다. (없어도 된다고 해서 없애버리면 프로그램이 실행이 안된다...e_lfanew offset을 올바르게 잡아줘도 file 크기를 나타내는 값 등 전체적인 틀이 깨져 다른 멤버의 수정도 필요한 듯하다.) 해당 부분은 DOS용 실행 부분으로, 16bit 어셈블리 명령어로 구성되어 있다. HxD에서 해당 부분을 살펴보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/mOFD3/btqRrQSwXMn/G5WHwwnX0NyA7Rxmcmf7pk/img.png)DOS Stub



 

#### 3) NT Header

NT header 구조체는 다음과 같다.

```
typedef struct _IMAGE_NT_HEADERS {
  DWORD                   Signature;
  IMAGE_FILE_HEADER       FileHeader;
  IMAGE_OPTIONAL_HEADER32 OptionalHeader;
} IMAGE_NT_HEADERS32, *PIMAGE_NT_HEADERS32;
```

위와 같이 3개의 멤버로 되어 있는데 첫 멤버는 Signature로 5045000h("PE"00) 값을 갖는다. 그리고 FileHeader와 Optional Header 구조체 멤버가 있다. 우선 Signature에 해당되는 부분을 HxD에서 살펴보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/cJRHjc/btqRpuJrVF4/dgU8oUXVtAXWAJdj067uRK/img.png)IMAGE_NT_HEADERS



 

#### 4) NT Header - File Header

파일의 개략적인 속성을 나타내는 IMAGE_FILE_HEADER 구조체이다.

```
typedef struct _IMAGE_FILE_HEADER {
  WORD  Machine;
  WORD  NumberOfSections;
  DWORD TimeDateStamp;
  DWORD PointerToSymbolTable;
  DWORD NumberOfSymbols;
  WORD  SizeOfOptionalHeader;
  WORD  Characteristics;
} IMAGE_FILE_HEADER, *PIMAGE_FILE_HEADER;
```

IMAGE_FILE_HEADER 구조체에서는 다음과 같은 4가지 멤버가 중요하다.

 

\#1 Machine : CPU별로 고유한 값이 부여된다.

\#2 NumberOfSections : PE 파일에 존재하는 Section의 개수를 나타낸다.

\#3 SizeOfOptionalHeader : IMAGE_NT_HEADERS의 마지막 멤버인 IMAGE_OPTIONAL_HEADERS32 구조체의 크기를 나타낸다. C언어 구조체이기 때문에 크기가 이미 정해져 있지만, Windows PE로드는 이 값을 보고 구조체 크기를 인식한다.

\#4 Characteristics : 파일의 속성을 나타내는 값으로, 실행이 가능한 형태인지, DLL 파일인지 등의 정보가 OR 형식으로 조합되어 표기된다.

 

이제 HxD에서 IMAGE_FILE_HEADER 구조체를 확인해보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/KAicQ/btqRrQkIfjC/Y3lteuZNrrEPUZGbKMbKLK/img.png)IMAGE_FILE_HEADER



중요 멤버만 살펴보면 다음과 같다.

Machine : 014C (x86)

NumberOfSections : 00003 (3개)

SizeOfOptionalHeader : 00E0

Characteristics : 010F

 

#### 5) NT Header - Optional Header

PE헤더 구조체 중에서 가장 크기가 큰 IMAGE_OPTIONAL_HEADER32이다.

```
typedef struct _IMAGE_OPTIONAL_HEADER {
  WORD                 Magic;
  BYTE                 MajorLinkerVersion;
  BYTE                 MinorLinkerVersion;
  DWORD                SizeOfCode;
  DWORD                SizeOfInitializedData;
  DWORD                SizeOfUninitializedData;
  DWORD                AddressOfEntryPoint;
  DWORD                BaseOfCode;
  DWORD                BaseOfData;
  DWORD                ImageBase;
  DWORD                SectionAlignment;
  DWORD                FileAlignment;
  WORD                 MajorOperatingSystemVersion;
  WORD                 MinorOperatingSystemVersion;
  WORD                 MajorImageVersion;
  WORD                 MinorImageVersion;
  WORD                 MajorSubsystemVersion;
  WORD                 MinorSubsystemVersion;
  DWORD                Win32VersionValue;
  DWORD                SizeOfImage;
  DWORD                SizeOfHeaders;
  DWORD                CheckSum;
  WORD                 Subsystem;
  WORD                 DllCharacteristics;
  DWORD                SizeOfStackReserve;
  DWORD                SizeOfStackCommit;
  DWORD                SizeOfHeapReserve;
  DWORD                SizeOfHeapCommit;
  DWORD                LoaderFlags;
  DWORD                NumberOfRvaAndSizes;
  IMAGE_DATA_DIRECTORY DataDirectory[IMAGE_NUMBEROF_DIRECTORY_ENTRIES];
} IMAGE_OPTIONAL_HEADER32, *PIMAGE_OPTIONAL_HEADER32;
```

이 중 중요한 멤버들은 다음과 같다.

 

\#1 Magic : 32bit용 구조체인 경우 10B, 64bit용 구조체인 경우 20B를 갖는다.

\#2 AddressOfEntryPoint : EP의 RVA 값을 나타낸다.

\#3 ImageBase : PE 파일이 로딩되는 시작 주소를 나타낸다.

\#4 SectionAlignment, FileAlignment : 각각 메모리에서의 최소 단위, 파일에서의 최소 단위를 나타낸다.

\#5 SizeOfImage : 가상 메모리에서 PE Image가 차지하는 크기를 나타낸다.

\#6 SizeOfHeader : PE 헤더의 전체 크기를 나타낸다.

\#7 Subsystem : 시스템 드라이버(*.sys)인지, 일반 실행 파일인지(*.exe, *.dll)를 구분한다.

\#8 NumberOfRvaAndSizes : IMAGE_OPTIONAL_HEADER32 구조체의 마지막 멤버인 DataDirectory 배열의 개수를 나타낸다.

\#9 DataDirectory : IMAGE_DATA_DIRECTORY 구조체의 배열로, 배열의 각 항목마다 정의된 값을 지닌다. 각 항목은 다음과 같다.

```
DataDirectory[0] = EXPORT Directory //중요
DataDirecotry[1] = IMPORT Directory //중요
DataDirectory[2] = RESOURCE Directory //중요
DataDirectory[3] = EXCEPTION Directory
DataDirectory[4]=  SECURITY Directory
DataDirectory[5] = BASERELOC Directory
DataDirectory[6] = DEBUG Directory
DataDirectory[7] = COPYRIGHT Directory
DataDirectory[8] = GLOBALPTR Directory
DataDirectory[9] = TLS Directory //중요
DataDirectory[A] = LOAD_CONFIG Directory
DataDirectory[B] = BOUND_IMPORT Directory
DataDirectory[C] = IAT Directory
DataDirectory[D] = DELAY_IMPORT Direectory
DataDirectory[E] = COM_DESCRIPTOR Directory
DataDirectory[F] = Reserved Directory
```

HxD에서 IMAGE_OPTIONAL_HEADER 구조체를 살펴보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/bWfbWb/btqRlJt4PAj/ZYlwSBn59FWplrktR7PMH1/img.png)IMAGE_OPTIONAL_HEADER



위에서 언급한 중요 멤버 값만 정리해보면 다음과 같다.

Magic : 010B (32-bit임을 나타낸다.)

AddressOfEntryPoint : 0000739D (EP의 RVA)

ImageBase : 01000000 (PE 파일이 로딩되는 시작 주소)

SectionAlignment : 00001000

FileAlignment : 00000200

SizeOfImage : 00014000

SizeOfHeader : 00000400

Subsystem : 0002 (창 기반 애플리케이션을 나타냄)

NumberOfRvaAndSizes : 00000010 (16개의 DataDirectory가 있음을 나타냄)

 

#### 6) Section Header

각 섹션의 속성(property)을 정의한 것이 섹션 헤더이다. 섹션 헤더는 각 섹션별로 IMAGE_SECTION_HEADER 구조체의 배열로 되어 있다.

```
typedef struct _IMAGE_SECTION_HEADER {
  BYTE  Name[IMAGE_SIZEOF_SHORT_NAME];
  union {
    DWORD PhysicalAddress;
    DWORD VirtualSize;
  } Misc;
  DWORD VirtualAddress;
  DWORD SizeOfRawData;
  DWORD PointerToRawData;
  DWORD PointerToRelocations;
  DWORD PointerToLinenumbers;
  WORD  NumberOfRelocations;
  WORD  NumberOfLinenumbers;
  DWORD Characteristics;
} IMAGE_SECTION_HEADER, *PIMAGE_SECTION_HEADER;
```

알아야 할 중요 멤버는 다음과 같다.

 

\#1 VirtualSize : 메모리에서 섹션이 차지하는 크기

\#2 VirtualAddress : 메모리에서 섹션의 시작 주소(RVA)
\#3 SizeOfRawData : 파일에서 섹션이 차지하는 크기

\#4 PointerToRawData : 파일에서 섹션의 시작 위치

\#5 Characteristics : 섹션의 속성(bit OR)

이 중 VirtualAddress와 PointerToRawData는 IMAGE_OPTIONAL_HEADER32에 정의된 Alignment를 따라야 한다. 또한 파일 상태에서의 섹션의 크기와, 메모리에 로딩되었을 때의 섹션의 크기가 서로 다른 경우가 일반적이므로, VirtualSize와 SizeOfRawData는 서로 다른 값을 갖는다. 멤버 중 Name에는 따로 특별한 규칙이 없기 때문에 어떠한 값이 와도 상관없다. (메모장의 경우 .text, .data, .rsrc가 온다.)

 

HxD에서 IMAGE_SECTION_HEADER를 살펴보면 다음과 같다. (3개의 섹션이 존재하므로 3 부분이 있다.)



![img](https://blog.kakaocdn.net/dn/bTwBXY/btqRpsZcYzc/zUdsEKhOQVLYzyVZtChoK0/img.png)IMAGE_SECTION_HEADER



Name 멤버를 기준으로 3개(.text / .data / .rsrc)로 나뉜다.



## 13장 PE File Format (2)

### - RVA to RAW

Section Header를 잘 이해했다면, PE 파일이 메모리에 로딩되었을 때 각 섹션에서 메모리의 주소와 파일 offset을 잘 매핑할 수 있어야 한다. 이러한 매핑 과정을 'RVA to RAW'라고 부른다. 기본적인 방법은 다음과 같다.

1. RVA가 속해 있는 section을 찾는다.
2. 간단한 비례식을 사용해서 파일 offset(RAW)를 계산한다.

 

비례식은 다음과 같다.

**RAW - PointerToRawData = RVA - VirtualAddress**

**RAW = RVA - VirtualAddress + PointerToRawData**

 

 



![img](https://blog.kakaocdn.net/dn/nDLuQ/btqRqQlzyR8/AVnB4t85z1bK1kyhcYOuUk/img.png)File 상태와 Memory 상태



간단한 예제 몇 가지를 통해 계산을 해보자.

 

Q1. RVA = 5000일 때, File offset? 

5000은 text Section에 해당하며 해당 섹션의 Virtual Address는 1000이므로

RAW = 5000(RVA) - 1000(VirtualAddress) + 400(PointerToRawData) = 4400

 

Q2. RVA = 13314일 때, File offset?

13314는 rsrc Section에 해당하며 해당 섹션의 Virtual Address는 9000이므로

RAW = 13314(RVA) - B000(VA) + 8400(PointerToRawData) = 10714

 

Q3. RVA = ABA8일 때, File offset?

data Section이지만 비례식을 통해 계산하면 RAW가 rsrc 섹션에 해당하는 값으로 결과가 나온다. 이 경우에 RAW 값은 정의될 수 없다.



## 13장 PE File Format (3)

### - IAT

IAT(Import Address Table)에는 Windows 운영체제의 핵심 개념인 process, memory, DLL 구조 등에 대한 내용이 함축되어 있다. IAT란 프로그램이 어떤 라이브러리에서 어떤 함수를 사용하고 있는지를 기술하는 테이블이다.

 

\- DLL

DLL(Dynamic Linked Library)는 프로그램에 라이브러리를 포함시키지 않고 별도의 파일(DLL)로 구성하여 필요할 때마다 불러 쓰는 방식으로, 한 번 로딩된 DLL은 다른 process들이 공유해서 사용함으로써 memory를 효율적으로 사용할 수 있는 방법이다. DLL에는 두 가지 방식이 있는데 프로그램 내부에서 사용되는 순간에 로딩하고 끝나면 해제되는 Explicit Linking(혹은 dynamic loading)방법, 프로그램이 시작될 때 로딩되고 종료될 때 해제되는 Implicit Linking(혹은 dynamic linking)방법이 있다. IAT에서는 후자인 Implicit Linking에 대한 메커니즘을 제공하는 역할을 한다. OllyDbg에서 notepad.exe를 통해 IAT가 사용되는 부분을 알아보겠다.

 



![img](https://blog.kakaocdn.net/dn/2KmJI/btqRQSvxgoW/Tv1TwoR8tzKMlA2PieZyZ0/img.png)CreateFileW() 호출



위와 같이 01001104에 들어있는 값을 호출하는 방식으로 CreateFileW를 호출한다. (모든 API호출은 이와 같은 방식을 취한다.) 01001104의 주소 값은 75833F50이며 해당 주소는 notepad.exe 프로세스 메모리에 로딩된 kernel32.dll의 CreateFileW 함수다. 그냥 CALL 75833F50이라 하면 될 듯한데, 왜 이렇게 indirect 하게 호출하는 것일까? 두 가지 이유로 이와 같은 방식을 취하는데 다음과 같다.

 

(1) 프로그램을 컴파일 하는 순간에는 notepad.exe 프로그램이 어떤 환경에서 실행될지 알 수 없다. (xp, vista, windows 10 등등...) 즉 위의 환경에 따라 kernel32.dll의 버전이 달라지고 CreateFileW 함수의 위치가 달라지기 때문에, 해당 함수의 호출을 보장하기 위해 함수가 저장될 위치만을 준비하고 CALL DWORD PTR DS:[1001104] 형식의 명령어를 적어두기만 한다.

 

(2) 기존에 DLL이 이미 로딩된 상태에서 다른 DLL이 로딩될 경우, DLL Relocation이 발생하므로 실제 함수의 주소를 하드 코딩할 수 없다.

 

\- IMAGE_IMPORT_DESCRIPTOR

PE 파일은 자신이 어떤 라이브러리를 import하는지 IMAGE_IMPORT_DESCRIPTOR 구조체에 명시한다.

```
typedef struct _IMAGE_IMPORT_DESCRIPTOR {
	union {
    	DWORD Characteristics;
        DWORD OriginalFirstThunk;	//INT(Import Name Table) address (RVA)
	};
    DWORD TimeDateStamp;
    DWORD ForwarderChain;
    DWORD Name;	//library name string address (RVA)
    DWORD FirstThunk;	//IAT(Import Address Table) address (RVA)
    
} IMAGE_IMPORT_DESCRIPTOR;

typedef struct _IMAGE_IMPORT_BY_NAME {
	WORD Hint;	//ordinal
    BYTE Name[1];	//function name string
} IMAGE_IMPORT_BY_NAME, *PIMAGE_IMPORT_BY_NAME;
```

일반적인 프로그램에서는 여러 개의 라이브러리를 import하기 때문에, 라이브러리의 개수만큼 위 구조체의 배열 형식으로 존재하며, 구조체 배열의 마지막은 NULL 구조체로 끝나게 된다. IMAGE_IMPORT_DESCRIPTOR 구조체에서 중요한 멤버는 다음과 같다. 

 

\#1 OriginalFirstThunk : INT(Import Name Table)의 주소 (RVA)

\#2 Name : Library 이름 문자열의 주소(RVA)
\#3 FirstThunk : IAT(Import Address Table)의 주소 (RVA)

 

PE로더가 임포트 함수 주소를 IAT에 입력하는 순서는 다음과 같다.

(1) IID(IMAGE_IMPORT_DESCRIPTOR)의 Name 멤버를 읽어 "kernel32.dll" 문자열을 얻는다.

(2) 해당 라이브러리를 LoadLibrary("kernel32.dll")을 통하여 로딩한다.

(3) IID의 OriginalFirstThunk 멤버를 읽어서 INT 주소를 얻는다.

(4) INT에서 배열의 값을 하나씩 읽어 해당 IMAGE_IMPORT_BY_NAME(RVA)를 얻는다.

(5) IMAGE_IMPORT_BY_NAME의 Hint(ordinal) 또는 Name 항목을 이용하여 해당 함수의 시작 주소를 얻는다. (GetProcAddresss 이용)

(6) IID의 FirstThunk멤버를 읽어서 IAT 주소를 얻는다.

(7) 해당 IAT 배열 값에 위에서 구한 함수의 주소를 입력한다.

(8) INT가 끝날 때까지(NULL) (4) ~ (7)을 반복한다.

 

\- notepad.exe를 이용한 실습

우선 IMAGE_IMPORT_DESCRIPTOR가 위치한 곳을 찾아야 하는데, 이전 포스팅에서 언급산 IMAGE_OPTIONAL_HEADER32.DataDirectory[1].VirtualAddress 값이 IID 구조체 배열의 시작 주소이다.



![img](https://blog.kakaocdn.net/dn/ph9Qz/btqRQR4tifE/wYBElOfRyfMc8TUqNMugu1/img.png)DataDirectory[1]



위 정보에서 앞 4byte는 RVA를, 뒤 4byte는 size를 나타낸다. RVA가 7604이니까 File Offset은 6A04가 된다. (6A04 = 7604 - 1000 + 400) 해당 부분을 HxD로 찾아가 보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/t3GXK/btqRGputlHM/jBIZ2tUbKRC0EsRSthW4t1/img.png)IMAGE_IMPORT_DESCRIPTOR 배열



size가 C8이였으므로 위와 같이 드래그 한 부분이 IID 배열이 된다. (마지막은 NULL 구조체로 되어 있다.) 앞에서 부터 20byte(첫 IID)에 대해 각 구조체 멤버를 나타내면 다음과 같다.

| **Member**              | **RVA**  | **RAW**  |
| ----------------------- | -------- | -------- |
| OriginalFirstThunk(INT) | 00007990 | 00006D90 |
| TimeDateStamp           | FFFFFFFF | -        |
| ForwarderChain          | FFFFFFFF | -        |
| Name                    | 00007AAC | 00006EAC |
| FirstThunk(IAT)         | 000012C4 | 000006C4 |

1. 라이브러리 이름(Name)

Name 항목은 import 함수가 소속된 라이브러리 파일 이름 문자열 포인터이다. Name의 RAW인 6EAC를 찾아가 보자.



![img](https://blog.kakaocdn.net/dn/rE4cH/btqRI8MLoLU/rHvYdBth8635K0gCKcwTtK/img.png)comdlg32.dll



위와 같이 "comdlg32.dll"이라는 라이브러리에서 import 하는 것임을 확인할 수 있다.

 

2. OriginalFirstThunk - INT(Import Name Table)

INT는 import하는 함수의 정보가 담긴 구조체 포인터 배열로, 해당 정보를 얻어야 프로세스 메모리에 로딩된 라이브러리에서 해당 함수의 시작 주소를 정확히 구할 수 있다. OriginalFirstThunk의 멤버를 따라가면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/bwMz3l/btqRI7mKvPp/ZkrKa1LYk9pX7ndyWH2Rg1/img.png)INT



3. IMAGE_IMPORT_BY_NAME

위의 7A7A(RVA) -> 6E7A(RAW)를 따라가 보면 IMAGE_IMPORT_BY_NAME 구조체가 나온다.



![img](https://blog.kakaocdn.net/dn/dmi7lR/btqRx0oPOeN/jB1DK0XIkhWtcbKtT119x0/img.png)IMAGE_IMPORT_BY_NAME



최초 2 byte 값(000F)은 Ordinal로 라이브러리에서 함수의 고유번호이며 그 뒤로 PageSetupDlgW라는 함수가 나오는 것을 확인할 수 있다.

 

4. FirstThunk - IAT(Import Address Table)

IAT의 RAW인 000006C4를 따라가 보자.



![img](https://blog.kakaocdn.net/dn/brOpcK/btqRI71mqJc/RIVLOYsEkOx0XH41ON7Xg1/img.png)IAT



위에서 드래그한 영역이 comdlg32.dll 라이브러리에 해당되는 IAT 배열 영역으로 마지막은 NULL로 끝나는 것을 확인할 수 있다. 

 

 

### - EAT

EAT(Export Address Table)은 라이브러리 파일에서 제공하는 함수를 다른 프로그램에서 가져다 사용할 수 있도록 하는 메커니즘이다. EAT를 통해서만 해당 라이브러리에서 export 하는 함수의 시작 주소를 정확히 구할 수 있다. IMAGE_EXPORT_DIRECTORY 구조체는 IAT와는 다르게 PE 파일에 하나만 존재한다. (export는 하나만 할 수 있기 때문) PE 파일에서 IMAGE_EXPORT_DIRECTORY는 IMAGE_OPTIONAL_HEADER32.DataDirectory[0].VirtualAddress 값의 시작 주소가 된다. notepad.exe에서 사용하는 라이브러리인 kernel32.dll의 IMAGE_OPTIONAL_HEADER32.DataDirectory[0]을 확인해보겠다.

 



![img](https://blog.kakaocdn.net/dn/cCyCdN/btqRL2k9CE9/pYmMXmlLi0aqYmue9HWFR0/img.png)kenel32.dll의 IMAGE_OPTIONAL_HEADER32.DataDirectory[0]



RVA = 262C이므로 File offset은 1A2C이다.

 

\- IMAGE_EXPORT_DIRECTORY

IMAGE_EXPORT_DIRECTORY의 구조체는 다음과 같다.

```
typedef struct _IMAGE_EXPORT_DIRECTORY {
	DWORD Characteristics;
    DWORD TimeDateStamp;	//creation time date stamp
    WORD MajorVersion;
    WORD MinorVersion;
    DWORD Name;	//address of library file name
    DWORD Base;	//ordinal base
    DWORD NumberOfFunctions;	//number of functions
    DWORD NumberOfNames;	//number of names
    DWORD AddressOfFunctions;	//address of function start address array
    DWORD AddressOfNames;	//address of function name string array
    DWORD AddressOfNameOrdinals;	//address of ordinal array
} IMAGE_EXPORT_DIRECTORY, *PIMAGE_EXPORT_DIRECTORY;
```

중요 멤버들은 다음과 같다.

\#1 NumberOfFunctions : 실제 Export 함수 개수

\#2 NumberOfNames : Export 함수 중에서 이름을 가지는 함수 개수

\#3 AddressOfFunctions : Export 함수 주소 배열

\#4 AddressOfNames : 함수 이름 주소 배열

\#5 AddressOfNameOrdinals Ordinal 배열

 

라이브러리에서 함수 주소를 얻기 위해 GetProcAddress()라는 API를 이용하는데, 그 과정은 다음과 같다.

(1) AddressOfNames 멤버를 이용해 함수 이름 배열로 간다.

(2) 함수 이름 배열에는 문자열 주소가 저장되어 있다. strcmp를 통해 원하는 함수 이름을 찾는다. (해당되는 index를 name_index라고 가정

(3) AddressOfNameOrdinals 멤버를 이용해 Ordinal 배열 접근

(4) ordinal 배열에서 name_index로 ordinal 값 발견

(5) AddressOfFunctions 멤버를 이용해 EAT 접근

(6) EAT에 아까 구한 ordinal 값을 배열 인덱스로 하여 원하는 함수의 시작 주소를 얻는다.

 

\- kernel32.dll을 이용한 실습

실제로 kernel32.dll 파일의 EAT에서 AddConsoleAliasW 함수 주소를 찾는 실습을 진행해보자.

앞에서 구한 IMAGE_EXPORT_DIRECTORY의 RAW인 1A2C로 이동해보자.



![img](https://blog.kakaocdn.net/dn/ECrMd/btqRwaE8euf/xcdAg5Ak4QP32KduUm4AHK/img.png)kernel32.dll의 IMAGE_EXPORT_DIRECTORY 구조체



위에서 드래그된 부분이 구조체의 각 멤버를 나타낸다. 멤버별로 해당 값을 살펴보면 다음과 같다.

| **Member**            | **Value** | **RAW** |
| --------------------- | --------- | ------- |
| Characteristics       | 00000000  |         |
| TimeDateStamp         | 48025BE1  |         |
| MajorVersion          | 0000      |         |
| MinorVersion          | 0000      |         |
| Name                  | 00004B8E  | 3F8E    |
| Base                  | 00000001  |         |
| NumberOfFunctions     | 000003B9  |         |
| NumberOfNames         | 000003B9  |         |
| AddressOfFunctions    | 00002654  | 1A54    |
| AddressOfNames        | 00003538  | 2938    |
| AddressOfNameOrdinals | 0000441C  | 3824    |

앞에서 설명한 GetProcAddress() 동작 원리 순서대로 진행해보자.

 

1. 함수 이름 배열

AddressOfNames의 RAW = 2938이다. 해당 부분을 HxD에서 찾아가 보자.



![img](https://blog.kakaocdn.net/dn/bbJheW/btqROfEAGTR/c5zo7FMf3mFDG7oQruMHok/img.png)AddressOfNames



2. 원하는 함수 이름 찾기

RVA(4BCD) -> RAW(3FCD)를 따라가면 다음과 같은 문자열을 만날 수 있다.



![img](https://blog.kakaocdn.net/dn/tRNxl/btqRQSbglzD/UyjvHp6SjkpBkfemH6INV0/img.png)



이때 'AddConsoleAliasW'의 배열 index는 3이다.

 

3. Ordinal 배열

AddressOfNameOrdinals의 멤버 값은 RAW 3824(위 표 참고)이다.



![img](https://blog.kakaocdn.net/dn/cio80r/btqRAjO7QF9/MT60Flgngk4UHwQ8yZT291/img.png)AddressOfNameOrdinals



 

4. Ordinal

AddressOfNameOrdinals[index] = ordinal로부터 index = 3, ordinal = 7 값을 얻었다.

 

5. 함수 주소 배열 - EAT

마지막으로 AddConsoleAliasW의 실제 함수 주소를 찾아갈 수 있다. AddressOfFunctions의 RAW는 1A54이다.



![img](https://blog.kakaocdn.net/dn/bjeBhl/btqRCVtF1J7/sNkjESMHdDeJJ9YJkLCPK0/img.png)AddressOfFunctions



 

6. AddConsoleAliasW 함수 주소

4에서 계산한 ordinal을 index로 하여 AddressOfFunctions에 적용하면, RVA = 0002BEF9라는 값을 얻을 수 있다. kernel32.dll의 ImageBase는 7C7D0000으로 'AddConsoleAliasW'의 함수의 VA는 7C7D0000 + 0002BEF9 = 7C7FBEF9가 된다.



## 14장 실행 압축

실행 압축(run-time packer)은 리버싱의 단골 주제로, 이를 이해하기 위해서는 PE file format과 운영체제의 기본적인 사항들에 대해 잘 알고 있어야 하며, 압축/해제 알고리즘에 대한 기본적인 내용에 대해서 알아야 한다.

 

실행 압축이란, 말 그대로 실행(PE: Portable Executable) 파일을 대상으로 파일 내부에 압축해제 코드를 포함하고, 실행되는 순간에 메모리에서 압축을 해제시킨 후 실행하는 기술이다. 일반적인 압축과는 다르게 실행이 가능하다는 특징이 있으며, 실행 압축을 통해 리버싱을 방해할 수도 있으며 코드 양이 더 늘어나는 경우도 있다.

 

### 1. 패커(Packer)

PE 패커(Packer)란 실행 파일 압축기를 말한다. 

 

\#1 사용 목적

PE 파일의 크기 감소를 통해 네트워크 전송에도 용이하고 보관하기도 좋다. 또한 파일 내부의 코드와 리소스 등을 감출 수 있어 압축된 데이터는 알아보기 힘든 형태의 binary로 저장되어 내부 내용을 감춰준다.

 

\#2 사용 현황

실행 압축은 DOS 시절부터 존재했다. 당시에는 PC 성능이 좋지 못해 파일이 실행될 때마다 수행되는 decoding routine 오버헤드가 컸지만, 요즈음은 PC 성능이 좋아 크게 영향을 받지 않아 많이 사용된다.

 

\#3 패커 종류

패커에는 크게 두 종류가 있다. 평범한 PE 파일을 만들어 내는 순수한 의도의 패커와 PE 헤더를 심각하게 훼손시키는 불순한 의도의 패커로 나눌 수 있다.

 

### 2. 프로텍터

PE 프로텍터란 PE 파일을 리버싱으로 부터 보호하기 위한 유틸리티로, 일반적인 패커의 기능 이외에도 리버싱을 막기 위한 다양한 기법(Anti-Debugging, Anti-Emulating, Code obfuscating, Polymorphic Code, Garbage Code, Debugger Detection 등)이 추가된다. 이러한 프로텍터들로 압축된 PE 파일의 크기는 원본 PE보다 커지는 경향이 있으며 디버깅하기 매우 어렵다.

 

\#1 사용 목적

크래킹 방지 & 코드 및 리소스 보호의 목적으로 이용된다.

 

\#2 사용 현황

크래킹에 민감한 보안 프로그램들이 프로텍터를 많이 사용한다. 또한 백신에 감지되는 것을 회피하기 위해 악성 코드에도 많이 사용한다.

 

\#3 프로텍터 종류

상용 프로그램(ASProtect, Themida, SVKP)과 공개용 프로그램(UltraProtect, Morphine)등이 있다.

 

### 3. 실행 압축 테스트

notepad.exe를 실행압축 해보자. 리버싱 핵심원리 서적에서는 UPX 패커를 이용하여 실습을 진행한다.



![img](https://blog.kakaocdn.net/dn/ceWwiX/btqRwbKP6ih/6MA5uv8Zf9hPQkukC2qu11/img.png)UPX 패커



위와 같이 UPX 폴더 내부에 디렉토리를 위치시키고 upx명령어를 입력하면 명령어와 옵션에 대한 정보가 나타난다. 다음과 같이 명령어를 입력하여 실행 압축을 해본다.

 



![img](https://blog.kakaocdn.net/dn/LkoZX/btqRCVf8kSB/o5kzHVo78J73jOLmhDaYs0/img.png)upx 압축 실행

![img](https://blog.kakaocdn.net/dn/Zxew1/btqRQSoOlg3/6F4u6X0ajl9KwZ9AIlKjfK/img.png)upx 압축 결과



위와 같이 실행압축한 notepad_upx.exe 파일은 크기가 줄어든 것을 확인할 수 있다.

 

PEVIEW를 통해 notepad_upx.exe 파일의 구조가 어떻게 변화했는지 확인해보자.



![img](https://blog.kakaocdn.net/dn/cj03Xs/btqRxZQUXDi/PKF7x6E1FBeBlcqpGtUYc0/img.png)PEView



위에서 주목할 부분은 UPX0 헤더의 Size Of Raw Data의 값은 0인데 Virtual Size는 10000이 된다. 압축된 코드를 첫 번째 섹션에 풀게 되면서 해당 Virtual Size 만큼의 코드를 메모리에서 차지하게 되는 것이고, 압축해제 코드와 압축된 내용은 두 번째 섹션에 존재한다. 파일이 실행되면 decoding routine을 거쳐 원본 코드를 첫 번째 섹션에 해제한 후, 원본 EP 코드를 실행한다.



## 15장 UPX 실행 압축된 notepad 디버깅

출처: https://maple19out.tistory.com/24

이전 실습에서 생성한 notepad_upx.exe를 디버깅하여 실행 압축에 대한 개념을 이해하고 원시적인 방법으로 tracing 하면서 OEP를 찾아보자.

 

### 1. notepad.exe의 EP code

우선 원본 notepad.exe의 EP 코드를 살펴보자.



![img](https://blog.kakaocdn.net/dn/ejmCQK/btqROdGK2Ha/xX9I2nukTngbe05ciF06ak/img.png)notepad.exe의 EP 코드



010073BE에서 GetModuleHandleA API를 이용하여 notepad.exe 프로세스의 ImageBase를 구한 후, 010073B4와 010073C0 주소에서 각각 MZ와 PE Signature를 비교한다.

 

### 2. notepad_upx.exe의 EP Code



![img](https://blog.kakaocdn.net/dn/cT9JTX/btqRCWMUkvS/OKZYNiCLmD62rfQcAklKOk/img.png)notepad_upx.exe의 EP 코드



EP 주소는 010154F0으로, 이곳은 두 번째 섹션의 끝부분이다. 우선 PUSHAD 명령어를 통해 EAX ~ EDI 레지스터 값을 스택에 저장하고, ESI와 EDI 레지스터를 각각 두 번째 섹션의 시작 주소와 첫 번째 섹션 시작 주소로 세팅한다. UPX 파일에서 첫 번째 섹션은 메모리에서만 존재하는 곳으로, 압축 해제된 원본 파일의 코드가 저장되는 장소다. 이와 같이 디버깅할 때 ESI와 EDI가 동시에 세팅되면 ESI(source)에서 EDI(destination)로 메모리 복사가 일어날 거라고 예측할 수 있다.

 

### 3. UPX 파일 트레이싱

압축된 코드의 양이 상당히 많다. 이와 같은 코드를 트레싱할 때는 루프를 만나면 그 역할을 살펴본 후 탈출하는 방식으로 진행하면 좋다. OllyDbg에서는 Ctrl+F7, Ctrl+F8로 Step Into / Step Over 명령을 반복할 수 있는데, 해당 명령어를 수행하면 트레이싱 과정이 화면에 표시된다. 트레이싱을 진행하면서 루프를 만나면 기능을 파악하고 탈출 직후의 명령어에 BP를 설정하고 F9를 눌러 진행시키는 방향으로 트레이싱을 진행하면 보다 수월하게 진행할 수 있다. (F7을 다시 입력하면 트레이싱이 멈춘다.)

 



![img](https://blog.kakaocdn.net/dn/bfRgZe/btqRCVgaHvW/PwtZtx4QC44U7QjwuDEAK1/img.png)JUMP to OEP



계속해서 트레이싱을 진행하다보면 위와 같은 명령어를 발견할 수 있다. JUMP 명령어 윗부분에 POPAD 명령어도 확인할 수 있는데, 해당 명령을 통해 UPX 코드 중 가장 첫 번째 명령인 PUSHAD에 대응되는 것으로 레지스터를 원래대로 복원시키는 명령어이다. 최종적으로 JMP 하게 되는 0100739D는 UPX 압축전의 EP와 같은 것을 확인할 수 있다.

 

### 4. UPX의 OEP를 빨리 찾는 방법

위와 같이 트레이싱을 하는 방법은 시간이 많이 걸린다는 단점이 있다. UPX 패커를 기준으로 보다 빨리 OEP를 찾는 방법이 있는데 다음과 같은 것이 있다.

 

1) POPAD 명령어 이후의 JUMP 명령어에 BP 설정

UPX 패커는 EP 코드가 PUSHAD / POPAD 명령어로 둘러싸여 있으므로, POPAD 명령어가 호출되는 곳을 찾아 이후에 JMP 명령어가 나타나는 곳을 확인하는 방법이 있다.

 

2) 스택에 하드웨어 브레이크 포인트(Hardware Break Point) 설치

이 방법 역시 UPX의 특징인 PUSHAD/POPAD 명령어의 특성을 이용하는 방법으로, PUSHAD 명령을 실행한 후 스택을 보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/bPF5G8/btqRGpg1JpQ/av3LwKBjgLyfcH2L0tx1EK/img.png)PUSHAD 명령어 이후 스택의 모습



마우스 커서를 스택의 top 부분인 000DFF54에 대고 우측 메뉴를 이용하여 하드웨어 BP를 설정한다.



![img](https://blog.kakaocdn.net/dn/oHyqu/btqRL2ZPrC4/Yk9BkMWwhfLeFfRN9aDYR1/img.png)하드웨어 BP



하드웨어 BP란 CPU에서 지원하는 BP로, 일반적인 BP와 다르게 BP가 설치된 명령어가 실행된 이후에 제어가 멈추게 된다는 특징이 있다. 이 상태에서 실행하게 되면 POPAD가 호출되는 순간 하드웨어 BP가 설정된 000DFF54 주소를 액세스하고 제어가 멈춘다. 밑에 있는 JMP 명령을 통해 OEP를 찾을 수 있다.

 



![img](https://blog.kakaocdn.net/dn/I1FAR/btqRt6Dan7t/IPtObr6fk8EhJ9UZoH2Kr0/img.png)하드웨어 BP 설정 후 실행 직후



## 16장 Base Relocation Table

### 1. PE 재배치

PE 파일이 메모리에 로딩될 때 ImageBase 주소에 로딩이 된다. DLL의 경우 이미 다른 DLL이 로딩되어 있다면 다른 비어 있는 주소 공간에 로딩된다. 이와 같은 것을 PE 재배치라고 한다. DLL과 달리 EXE 파일은 가장 먼저 메모리에 로딩되기 때문에 재배치를 고려할 수가 없었으나, Windows Vista 이후부터는 보안 강화를 위해 ASLR(Address Space Layout Randomization) 기능이 추가되어 실행될 때마다 랜덤 한 주소에 로딩이 된다.

 

=> 이 글을 작성하는 시점에 Windows 10 환경에서 OllyDbg로 notepad.exe를 여러번 재실행해보았지만, 로딩되는 주소에는 변화가 없었다. 해당 부분에 대한 궁금증을 해소하기 위해서 구글링을 해보았고 다음 링크의 글들이 궁금증을 해결하는데 도움을 주었다.

 

프로그램을 단시간내에 실행했을 때에 ASLR이 작동하기보다는 재부팅 등을 수행한 후에 ASLR이 작동한다는 등의 내용을 포함하고 있는 글이다. 참고해보면 좋을 것 같다.

### 2. PE 재배치 발생시 수행되는 작업

PEView를 통해 Windows7의 notepad.exe의 ImageBase를 확인해보면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/bbs93L/btqRI8mpnR5/hIPvkxSat2c95ngL80nowk/img.png)notepad.exe의 ImageBase



OllyDbg로 notepad.exe를 실행하면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/I8948/btqRI7HHmxo/k9QOftgmRlYEVu9Pr4cywK/img.png)notepad.exe의 EP



PEView에 나타난 ImageBase와는 다른 001D0000에 프로그램이 로딩된 것을 확인할 수 있으며, 위 첨부 사진에서 빨간색 동그라미는 각 프로세스 메모리 주소가 하드 코딩되어 있다. notepad.exe 프로그램이 재 실행되었을 때, 메모리의 다른 위치에 로딩된다면 위의 빨간 영역들은 로딩된 주소에 맞게 relocation을 해주어야 한다.

 

### 3. PE 재배치 동작 원리

Windows의 PE 로더가 수행하는 PE 재배치 작업의 동작 원리는 다음과 같다.

 

1) 프로그램에서 하드코딩된 주소 위치를 찾는다.
2) 값을 읽은 후 ImageBase를 뺀다. (VA -> RVA)
3) 실제로 로딩된 주소를 더한다. (RVA -> VA)

 

여기서 핵심은 하드코딩된 주소의 위치를 찾는 것으로, PE 파일 내부에 Relocation Table이라고 하는 하드코딩 주소들의 offset을 모아놓은 목록이 존재한다. Relocation Table을 찾아가기 위해서는 PE 헤더의 Base Relocation Table을 따라가면 된다.

 

(1) Base Relocation Table

PE 헤더의 DataDirectory의 5번 Index (6번째)에 존재한다. PEView를 통해 확인하면 다음과 같다.



![img](https://blog.kakaocdn.net/dn/bjzPUh/btqRXH8uqWl/pdVKkDGCIqUasQ2HnuwL1K/img.png)Base Relocation Table 주소



RVA는 0002F000이다. PEView에서 해당 위치를 찾아보자.



![img](https://blog.kakaocdn.net/dn/ELOpP/btqRI80XJ9H/0r9695xku0LzyF9vTmvet0/img.png)Base Relocation Table



 

(2) IMAGE_BASE_RELOCATION 구조체

위 그림에는 하드코딩 주소들의 offset이 나열되어 있다. 이 테이블을 이용하여 하드코딩 주소의 offset을 정확히 알아낼 수 있다. Base Relocation Table은 IMAGE_BASE_RELOCATION 구조체 배열인데, 그 정의는 다음과 같다.

 

```
typedef struct _IMAGE_BASE_RELOCATION {
	DWORD VirtualAddress;
    DWORD SizeOfBlock;
//  WORD TypeOffset[1];
} IMAGE_BASE_RELOCATION;
typedef IMAGE_BASE_RELOCATION UNALIGNED * PIMAGE_BASE_RELOCATION;

#define IMAGE_REL_BASED_ABSOLUTE		0
#define IMAGE_REL_BASED_HIGH			1
#define IMAGE_REL_BASED_LOW			2
#define IMAGE_REL_BASED_HIGHLOW			3
#define IMAGE_REL_BASED_HIGHADJ			4
#define IMAGE_REL_BASED_MIPS_JMPADDR		5
#define IMAGE_REL_BASED_MIPS_JMPADDR16		9
#define IMAGE_REL_BASED_IA64_IMM64		9
#define IMAGE_REL_BASED_DIR64			10
```

첫 번째 멤버 Virtual Address는 기준 주소를 나타내며(RVA), 두 번째 멤버 SizeOfBlock은 각 단위 블록의 크기를 가리킨다. 마지막으로 주석 처리된 TypeOffset[1]의 의미는 구조체 밑으로 WORD 타입의 배열이 따라온다는 의미를 나타낸다. 

 

위의 구조체 정의를 기반으로 Base Relocation Table을 분석해보자. VirtualAddress 멤버의 값은 1000이고, 블록 전체 크기는 150(hex)이다. 이후로는 2byte짜리 TypeOffset이 나오는데 앞의 4비트는 매크로로 정의된 TYPE, 뒤의 12비트는 offset이 된다. TypeOffset의 개수는 블록 사이즈가 150이므로 (150-8)/2 = A4(hex) 이다. 이제 실제로 1420 RVA에 PE 재배치가 필요한지 OllyDbg에서 확인해보자.

 



![img](https://blog.kakaocdn.net/dn/mWmvJ/btqRCVHVOf7/glcG7oMgkrtvYbS3tXUxQ1/img.png)하드코딩된 주소 예제



(3) 실습

간단한 실습을 통해 PE 재배치 작업의 동작 원리를 이해해보자. 앞에서 설명한 3.PE 동작 원리를 바탕으로 진행한다.

 

\#1 프로그램에서 하드코딩된 주소 위치를 찾기



![img](https://blog.kakaocdn.net/dn/bHUosW/btqRL3rtffW/hmj6lZ7UXkemHxwaTST471/img.png)RVA 1420 주소의 내용



RVA 1420 주소에는 010010C4가 들어있다.

 

\#2 ImageBase 만큼 빼기

010010C4 - 01000000 = 000010C4

 

\#3 실제 로딩 주소 더하기

000010C4 + 001D0000 = 001D10C4
