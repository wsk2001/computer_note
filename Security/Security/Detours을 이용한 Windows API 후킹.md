# Detours을 이용한 Windows API 후킹

출처: https://ch4njun.tistory.com/138

Detours는 Microsoft사에서 공개한 매우 강력한 Windows API 후킹 라이브러리이다.

 기존에는 $9,999 이상의 비용을 내고 사용해야 하던 라이브러리였지만, 현재는 라이센스가 MIT License로 변경되면서 완전 무료 오픈소스로 변경되었습니다. 2002년부터 Microsoft에서 공식적으로 사용해오던 매우 신뢰할 수 있는 라이브러리 입니다. 



##  설치방법

> DownLoad : https://github.com/microsoft/detours

Github를 통해 다운로드를 받은 후 **"Command Prompt"**를 입력해 Visual Studio 터미널창으로 진입한다. 이와 같은 항목이 없을 경우에는 Visual Studio를 설치한 후에 진행하면 된다.

 



![img](.\images\oKrG8nsySXKq8PkbQSehTK.png)

해당 프로젝트를 **다운로드 받은 경로로 이동한 뒤 nmake 명령어**를 통해 MakeFile를 실행해 빌드를 진행하도록 한다.

빌드하는데 3~10분정도가 소요되니 여유있게 기다려주자.

![img](.\images\561pB2KDDMitpacFtndOY1.png)



 

컴파일을 하게되면 bin.X86, lib.X86 파일이 생기는 것을 확인할 수 있다.

디렉토리별 설명은 아래와 같다.

**bin.X86** : samples에 있는 소스코드들이 컴파일 되어서 exe, dll파일로 저장되는 디렉토리이다.

​      앞으로 예제에서 사용하게될 setdll.exe 파일도 해당 경로에 존재한다.

**include** : 내가 직접 dll코드를 작성하게 될 때 사용해야할 **헤더파일이 존재하는 디렉토리**이다.

**lib.X86** : 내가 직접 dll코드를 작성하게 될 때 사용해야할 **라이브러리 파일이 존재하는 디렉토리**이다.

**samples** : Detours Library에 적응하기위해 테스트 해볼수 있는 **예제 코드들이 존재하는 디렉토리**이다.

​       setdll.exe와 같이 실제 사용하기 좋은 소스코드도 많이 존재하는 것 같다. (공부필요....)

 



![img](.\images\vgIeOcQR9sOJ4jeIRfqy0k.png)

setdll.exe 파일을 이용해 샘플을 돌려보며 적응해보자. 잘 모를땐 항상 /? 옵션을 사용해서 도움말을 참고하도록 하자. 대부분의 샘플 명령어에는 기본적으로 해당 옵션이 존재한다.

 ![img](.\images\2Ca06asces5jz5AqUJJcDk.png)

후킹 전, 후킹 명령어, 후킹 후 결과를 차례로 확인할 수 있다.

후킹 명령어는 **setdll.exe /d:[DLL] [Target EXE]** 을 통해 해당 EXE에 원하는 DLL을 주입할 수 있다.

![img](.\images\cWIYGAfk7ZVspLXVEUmXx1.png)

해당 EXE파일에 주입되어있는 DLL을 다시 제거하는 명령어는 아래와 같다.

**setdll.exe /? [Target EXE]** 명령어를 사용해서 해당 EXE파일에 주입된 기타 DLL을 제거할 수 있다.



![img](.\images\PLeowRvFpzvx7F54KBKAd0.png)



 

------

### **[ 실습 ]**

그럼 이제 내가 원하는 EXE파일에 내가 원하는 Windows API를 후킹하기 위해서 **직접 DLL 파일을 생성해서 setdll.exe 를 사용해 Hooking하는 과정을 진행**해보겠다.

 

우선 Hooking에 사용할 EXE파일은 [crackme3.exe](https://securityfonder.tistory.com/attachment/cfile5.uf@2630F3335633966C07B685.exe) 파일이다. KeyFile의 존재여부에 따라 MessageBoxA API를 통해 메세지박스를 띄워주는 실행파일이다.

 

 



![img](.\images\E90bz8quyJtYYzSVwHOPs1.png)

![img](https://blog.kakaocdn.net/dn/b3zRcv/btqCgSePhLP/x37PpKyVd8Lpb3CtcEK2D1/img.png)



위와 같은 MessageBox가 띄워지는 프로그램인 것을 확인할 수 있다.

 



![img](.\images\yxsLhK8nTQw9b7dNlxfcFK.png)



PEView로 확인해본 결과 USER32.dll의 MessageBoxA API가 사용되고 있는 것을 확인할 수 있었다.

 

그러면 간단하게 MessageBoxA API를 후킹해 출력되는 Message를 변경하는 실습을 진행해보자.

 

 



![img](.\images\eDRgoKgL7wdcUSY8cYKkw0.png)



우선 DLL파일을 만드는데 여기서 중요한 부분 몇 가지를 체크해보겠다.

 

1) 3번 라인에서 **detours.h를 반드시 include** 해줘야한다.

  그래야 Detours Libraray의 API들을 사용할 수 있다.

2) *7번 라인에서의 #define은 사실 필수적이진 않지만 export 함수를 만드는 과정에서*

  *해당 접두사가 너무길기 때문에 #define을 통해서 편리하게 사용할 수 있도록 했다.*

3) 9번 라인에서 **detours.lib를 해당 프로젝트에서 사용할 수 있도록 해주는 과정**도 반드시 필요하다.

 

이렇게만 한다면 우선 DLL 파일을 만들 준비는 끝났다.

 

그리고 나서는 내가 **Hooking하고자 하는 API를 Google에 검색해 반환형과 인자를 찾는다.**

 



![img](.\images\tGVK8s2pyMzHPKnsAoVmI0.png)



Microsoft의 API목록에서 쉽게 찾을 수 있다. 그러면 위 코드 11번 라인에서 처럼 **static 변수로 해당 API를 반환형과 인자의 자료형을 맞춰서 함수 포인터에 저장**해준다. 이는 나중에 후킹을 진행할 때 어떤 API를 후킹할지 명시해주기 위함이다.

 

그리고 이제 내가 해당 **API 대신 수행할 Hooking API를 작성**하면 된다.

이때 당연하겠지만 반드시 **반환형과 인자의 자료형은 일치시켜줘야 한다.**

위 코드에서 13~15번 라인에 해당되는 내용이며, 기존에 출력해려던 문자열 대신 "Hooking" 이라는 문자열을 메세지 박스에 출력하게 하는 코드이다. ( 응용은 엄청나게 다양하게 가능할 것으로 예상된다... )

 

이제 마지막으로 DllMain을 작성해준다.

 



![img](.\images\KnclZaHGTU06aOg9790641.png)



아직 Detours Library의 API들의 각각 자세한 역할은 제대로 이해하지 못했다. 나중에 그에 대해 충분히 이해한다면 따로 코멘트를 남기도록 하겠다. 29번, 37번 라인을 통해 Attach, Detach를 진행한다는 것만 확인하고 위 코드는 일단 기억해두자 !!

 

 

이후 해당 DLL 파일을 Build해서 DLL파일로 추출해낸다.



![img](.\images\HKHHI0Ik8kGfkTKrKZt8R1.png)



 

이후 위 실습과 동일하게 setdll.exe를 통해 Hooking을 진행해준다.

 



![img](.\images\YwS161iYM8XcK6fKkY2UW0.png)



하면 해당 EXE가 있는 경로에 Hooking이 진행된 EXE파일이 생성된다.

실행해보면 정상적으로 후킹이 완료된 것을 확인할 수 있다.

 

 



![img](.\images\JHGqL3olpBNQZIgHgGtOC1.png)

![img](https://blog.kakaocdn.net/dn/8pSO7/btqCgRtyfZl/Jstju1MFA85GhisE1k3TQ0/img.png)



---

# Detours 을 이용한 Windows API 후킹 2

저번 포스팅에서는 Detours Library을 이용해서 실행되지 않은 Process에 대해서 DLL을 주입하여 Windows API Hooking하는 실습을 진행했다. 그러나, 실제 이렇게 실행되지 않은 Process에 대해서 DLL을 주입하는 경우는 생각보다 많지 않을 것 같다. 그래서 이번 포스팅에서는 **실행중인 Process에 DLL을 Injection해 Hooking을 진행할 수 있는 방법**에 대해 소개해보고자 한다.



Injection을 진행하는 코드는 아래와 같다.

![img](.\images\ybf6WzPDSRFVLK7ggvPJCK.png)



 

**CreateRemoteThread API를 통해 LoadLibraryA API를 실행**하는 과정이다.

 

**- OpenProcess**

**- VirtualAllocEx**

**- WriteProcessMemory**

**- GetModuleHandle**

**- GetProcAddress**

**- CreateRemoteThread**

**- WaitForSingleObject**

 

순서대로 진행되며 세부적인 동작과정은 아래와 같다.

 

> 
> ***OpenProcess : Argument로 주어진 PID를 통해 해당 Process의 HANDLE을 획득한다.
> ***
> ***VirtualAllocEx : 해당 Process의 메모리에서 읽고 쓸수 있는 DllName 길이 만큼의 공간을 요청하고,***
>           ***그에 해당하는 주소를 반환받아 LPVOID 변수에 저장한다.
> ***
> ***WriteProcessMemory : 해당 Process의 특정 메모리주소에 DllName을 작성한다.***
>                ***특정 메모리 주소는 VirtualAllocEx에서 할당받은 주소로 사용한다.
> ***
> ***GetModuleHandle : 해당 DLL의 Module을 획득한다.
> ***
> ***GetProcAddress : 해당 DLL에서 LoadLibraryA 라는 이름을 가지는 API 주소를 획득한다.
> ***
> ***CreateRemoteThraed : LoadLibraryA를 실행하는데, 인자를 아까 할당받은 메모리 주소로한다.***
>                ***그 메모리 주소에는 내가 Injection하고자하는 DLL의 경로가 저장되어 있다.
> ***
> ***WaitForSingleObject : 이 Process를 CreatRemoteThread로 생성해준 Thread가
>                종료될 때 까지 대기한다.***

 

 

해당 Process에서 DLL Injection을 해제하는 코드는 아래와 같다.

 



![img](.\images\E3ORJ92aVM8TofXkvECZY1.png)



**CreateRemoteThread API를 통해 FreeLibrary API를 호출하는 것**은 DLL Injection에서 LoadLibrary API를 호출하는 것과 매우매우 동일하다. 그러나, FreeLibrary에서 사용하는 첫 번째 인자 즉, 내가 Injection한 DLL의 주소를 찾는 과정이 추가된다.

 

CreateToolhelp32Snapshot API를 통해 찾아나가는 과정은 우선 기억하도록 하자.

 



![img](.\images\8bKvlh9fBgba9YKcL2ZsK1.png)



main 함수에서는 간단하게 사용법에 따른 Injection/UnInjection만 구분해줬다.

 

그럼 한번 사용해보자 !!

우선 이전 예제에서 사용했던 crackme3.exe를 실행하고 ProcessExplorer를 확인해보자.

 



![img](.\images\ojOFpj1vH5AvrxT0B6OA7K.png)



 

현재 Load된 DLL목록을 주욱 한번 살펴본 뒤 위에서 작성한 DLL Injection 코드를 실행해보자. 여기서 crackme3.exe의 PID를 확인한 후 DLL Injection 코드를 실행할 때 인자로 전달해 주어야 한다.

 



![img](.\images\5CRQNbTEgsC4OcioPY7SI1.png)



 

-i 옵션을 주어 15780을 PID로 가지는 Process에 해당 DLL을 Injection했다.

해당 실행파일을 실행하자마자 Process Explorer에 ch4njun_sleep.dll이 정상적으로 Injection 된 것을 확인할 수 있다. 정상적으로 Process에 Load되었기 때문에 자동적으로 해당 DLL의 DllMain이 수행된다.

 

해당 DLL파일은 이전 포스팅에서 사용했던 코드와 완전히 동일하며, Detours Library를 사용해 MessageBoxA API의 API Hooking을 진행하는 코드이다.

 



![img](.\images\edQvGZ0PRwDJz47kOBNDs1.png)



 

그러나 주의해야 할 점은 **실행되지 않은 Process에 Hooking할 때와 달리 26번 라인의 DisableThreadLibraryCalls API를 반드시 호출해 주어야 한다.** 이거 때문에 오지게 삽질함..ㅠ

 



![img](.\images\byNXkJm8kA3UplKeHL66C1.png)



그렇게 DLL Injection이 성공하면 자연스럽게 Hooking에도 성공되며 두 번째 MessageBox부터는 내가 원하는 Hooking 함수가 호출되는 것을 확인할 수 있다.
