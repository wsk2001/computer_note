# **Windows 권한 이해하기**

출처: https://blog.naver.com/jjoommnn/130029014466



(이 글은 Windows 운영체제와 MSDN의 공식적인 내용과 다를 수 있다. 이 글은 순전히 내가 이해한 개념과 방식을 서술 한 것이라, 실제와 다를 수 있음을 양지하기 바란다.)

 

Windows(운영체제)를 종료시키는 프로그램을 만들어 본적 있는가? MSDN을 찾아보면

```c++
BOOL ExitWindowsEx( UINT uFlags, DWORD dwReason );
```

가 있어서, 이 함수를 호출하면 쉽게 구현할 수 있을 것 같다. 하지만 단순히 이 함수만 호출해서는 에러를 리턴할 뿐 Windows가 종료하지 않는다.

 MSDN을 좀더 찾아보면 Windows를 종료시키는 예제가 있다.

완전한 코드는 MSDN을 찾아보도록 하고(How to Shut Down the System), 간략한 코드의 흐름을 적어보면 다음과 같다.

```c++
1) OpenProcessToken( ... );
2) LookupPrivilegeValue( ..., SE_SHUTDOWN_NAME, ... );
3) AdjustTokenPrivilege( ... );
4) ExitWindowsEx( ... );
```

여기서 우리가 주목해야 할 부분이 1, 2, 3 번 부분임을 눈치챘을 것이다.

 단순히 ExitWindowsEx만 호출한 프로그램이 실패한 이유는, ['실행권한 이해하기'](http://blog.naver.com/jjoommnn/130028918514)에 쓴 방법으로 따져보면, 프로그램(지시자)이 Windows를 종료할 권한이 없기 때문에 Windows(수행주체)가 Windows 종료(작업대상) 요청을 거부한 것이다. 그런데 난 프로그램을 Admin으로 로그인해서 실행시켰다. 그럼 Admin이 Windows를 종료할 권한이 없다는 말인가?

하지만 두번째로 작성한 프로그램으로는 Windows가 종료한다.(이것도 Admin으로 로그인해서 실행시켰다.) 이 프로그램이 작동했다는 말은 프로그램이 Windows를 종료할 권한이 있었다는 말이다. 아무래도 위의 1, 2, 3 과정이 권한을 받는 과정인 모양이다.

그런데 '왜 권한을 받아야 하지?' 라는 생각은 들지 않는가? Admin 권한으로 실행시킨 프로그램이 왜 권한을 부여 받아야 하는가 말이다.

지금 부터 Windows의 권한에 관한 개념을 이해해 보자.

Windows의 권한을 이해하기 위해서는 몇가지 키 포인트가 되는 개념이 있다.

- 1. ACE, ACL(Acess Control Entry, Acess Control List)

- 2. Access Token(엑세스 토큰)

- 3. Privilege(권한)

- 4. Impersonation(가장)

 

앞서 ['실행권한 이해하기'](http://blog.naver.com/jjoommnn/130028918514)에서 '수행주체'의 역할 3가지를(1. 작업대상에 접근 관리, 2. 지시자의 권한 검사, 3. 실제 작업 수행) 적용시켜서 이해해 볼 수 있다.

 

### 1. ACE, ACL은 바로 작업대상 접근을 관리하기 위한 요소이다. 

이것은 작업대상에 부여되는 요소로서, 어떤 사용자는 허가, 어떤 사용자는 불허하는 등의 내용들이 들어 있다. 보통 파일의 접근 권한이나, 커널 객체들을 생성할 때 넣어 주는 Security Attributes 혹은 Security Descriptor 가 그것이다.

 

### 2. Access Token은 한마디로 '지시자'의 신원이라고 할 수 있다. 

결국 Windows에서는 로그온한 계정을 나타낸다. 실제적으로 '지시자'가 되는 것은 Process와 Thread가 될 텐데, 이 Process와 Thread는 생성될 때, 이 계정에 대한 Access Token의 복사본을 가지게 된다.

복사본이라고 한 것은, 처음 생성될 때는 같은 능력(?)을 갖는 Token이지만 각각의 Token에 다른 조작을 가함으로써 다른 능력(?)을 갖는 Token이 될 수 있다는 말이다.

Token의 종류에는 **Primary Token**과 **Impersonation Token**이 있는데, **Primary Token**은 말그대로 생성될 때 부여받은 Token이다. 반대로 **Impersonation Token**은 가장(Impersonate)을 목적으로 다른 사용자 계정을 통해 생성된 Token을 말한다. 예를 들어 원래 Thread가 생성된 계정은 Admin계정이지만 가장을 위해 User 계정의 Access Token을 생성하여, Thread를 가장하는 경우에 이 User 계정의 **Access Token**을 **Impersonation Token**이라고 한다.

 

### 3.Privilege(권한)는 Access Token과 밀접한 관련이 있다. 

쉽게 말해 Access Token은 Privilege의 리스트를 가지고 있다고 할 수 있다. Access Token이 어떤 신원을 나타낸다면 Privilege는 그 신원에 부여된 권한이다.

이 것은 Windows의 관리도구에서 사용자별로 권한을 부여하는 과정으로 정의된다. User 라는 계정을 Administrator 그룹에 넣음으로써 User라는 계정의 권한이 많아지는 경우가 일례가 되겠다.

 

그런데 한가지 덧붙여야 할 내용은 앞서 예를 든 Windows 종료 프로그램과 관련이 있다.

Access Token이 Privilege의 리스트를 가지고 있다고 했지만, 이 Access Token의 모든 Privilege가 활성화 되어 있는 것이 아니라는 사실이다. 즉 Admin의 Access Token을 가지고 있으므로, 당연히 Windows 종료 Privilege도 가지고 있지만, 아직까지 비활성화 되어 있다는 것이다. 결국 필요할 때 이 Privilege를 활성화 시키고 작업을 지시해야 한다는 말이다. (왜 이렇게 초기에 모든 Privilege를 활성화 시켜 두지 않는지는 설명안해도 알 것이다.) <span style="color:blue">**높은 권한은 가능한 짧은 시간에만 유지하고, 더이상 필요 없으면 바로 권한을 다시 비 활성화 하는 것이 좋다.**</span>

이에 따라 위의 Windows 종료 프로그램을 설명해 보면, **1은 Process의 Token을 가져온다**. 그리고 **2는 이 Process Token에 Windows 종료 Privilege(SE_SHUTDOWN_NAME)가 있는지 살펴보고**, **3은 Process의 Token에 Windows 종료 Privilege를 활성화 한다.** 그리고 4는 실제로 Windows를 종료한다. 활성화한 Privilege를 다시 비 활성화 하는 코드는 빠져 있는데, 사실 이건 별 의미가 없다.(Windows가 종료한다. 운영체제가 종료한다고~)

 

2와 3을 합쳐서 생각하면 '지시자' 요소가 된다. 이 2와 3을 합친 개념을 Windows에서는 'Security Context'라고 하며, 'Principal' 이라고도 한다.

 

이쯤되면 Windows의 권한이라는 것도 이해가 되는 듯 하다.

용어가 조금 어렵고, 또 실제 API 사용할 때 넣어주어야 하는 요소가 많아서 그렇지 계정과 권한이라는 큰 개념의 틀에서 맞추어 보면 쉽게 알 수 있을 것 같다.

 

### 4. Impersonation은 뭔가?

Impersonation의 뜻을 살펴보면 '가장, 흉내, ~인 척하기' 등이다.

그럼 Windows의 권한이라는 관점에서 Impersonation은 도대체 뭐가 뭐인척 한다는 말인가?



몇가지 시나리오를 생각해 보자.

 

#### 1.서버 프로그램이 있다. 

이 서버 프로그램은 클라이언트 프로그램과 통신을 하며, 클라이언트 프로그램의 요청을 받아 서비스 한다. 그런데 이 클라이언트의 종류에 따라 서버가 서비스해도 되는 것과 서비스 해서는 안되는 것들이 있다고 하자. 그러면 서버 프로그램은 각각의 클라이언트에 따라 어떤 요청은 서비스하고, 어떤 요청은 거부하도록 코드를 작성해야 한다. 또한 클라이언트가 늘어남에 따라 코드를 다시 작성하고 컴파일해야 한다. 예를들면 A 사용자의 클라이언트는 aaa 파일을 수정할 수 있지만, B 사용자의 클라이언트는 aaa 파일을 수정할 수 없어야 하는 경우가 그렇다.

 

#### 2. IIS 같이 어떤 Application을 호스팅 하는 프로그램이 있다고 생각해 보자. 

즉 내가 만든 프로그램이 다른 프로그램을 실행시켜 준다는 말이다. 대충 생각해 보면 일단 내 프로그램이 구동을 하고 호스팅할 프로그램을 로드해서, 그 호스팅할 프로그램이 실행될 수 있도록 Thread를 만들어서 그 프로그램을 구동시킬 것이다. 그럼 이때 이 Thread의 Access Token은 어떻게 되는가?

앞서 설명했듯이 내가 만든 프로그램이 실행되는 계정의 Access Token을 상속받으므로, 만약 내가 Admin 계정으로 실행된 프로그램이라면, 호스팅되는 Application이 구동되는 Thread도 Admin의 Access Token과 같은 Privilege를 갖게 된다. 호스팅 되는 Application이 어떤 프로그램인지 모르는 상태에서 Admin계정의 AccessToken을 가지게 하는 것은 상당히 불안하다. 이것을 해결하려면 내 프로그램 자체를 Admin이 아닌 일반 User 계정으로 실행시켜야 할 것이다. 그렇지만 만약에, 호스트하는 Application은 User 권한만 있으면 되지만 내 프로그램은 Admin계정의 Access Token을 가져야만 수행할 수 있는 기능이 있다면 어떻게 하겠는가?

(물론 Admin계정의 Access Token을 가졌더라도 모든 Privilege가 활성화 되어 있지는 않지만, 앞선 글의 예에서 봤듯이 마음만 먹으로 Privilege를 활성화 시킬 수 있다.)

우리가 원하는 것은, 아예 Application이 구동되는 Thread의 Access Token이 가진 Privilege 리스트가 아주 최소인 것이다.(Privilege 리스트에 없는 것을 활성화 시킬 수는 없지 않은가)

 

#### 3. 내 프로그램은 원래 낮은 권한의 계정으로 실행된다. 

하지만 특수한 상황에서는 높은 권한이 필요한 경우가 있을 수 있다. 이때는 낮은 권한의 Access Token에는 활성화 할 수 있는 Privilege가 아예 없기 때문에 필요한 Privilege를 활성화 시킬 수도 없다.

또한 앞선 시나리오 처럼 다른 Application을 호스팅 하는 프로그램인 경우에도, 특수한 Application에 관해서는 Admin계정의 Access Token을 주고 싶은 경우가 있을 것이다. 이런 경우는 어떻게 할 것인가?

(당연한 이야기겠지만 Impersonation을 하려면 Impersonation할 계정의 ID와 PWD를 알아야 한다. 다시 말해 User 계정으로 실행되는 프로그램이라도 Admin의 ID와 PWD를 알고 있다면 Admin계정으로 Impersonation할 수 있다. 프로그램의 세팅값으로 Admin의 ID와 PWD를 세팅해 줄 수 있지 않겠는가?)

바로 이런 상황에 때문에 Impersonation이 필요한 것이다.

정리하면, Impersonation이란 어떤 한 Thread의 Token을 다른 계정의 Token인척 하게 만드는 것이다.

위의 시나리오 1과 같은 경우, 클라이언트의 요청을 처리하는 Thread를 각 클라이언트에 따른 사용자로 Impersonate하면 그외의 모든 API 호출들은 이 Impersonation Token을 기반으로 검사되므로, 자연스레 계정에 따른 권한 문제를 해결할 수 있다. 새로운 사용자 클라이언트가 추가되더라도 서버의 Windows 계정에 추가하고 적절한 권한만 주면 서버 프로그램을 고칠 필요도 없다.

시나리오 2, 3의 경우도 마찬가지다. 실제 처리를 담당하는 Thread에 Impersonate를 하면 된다. 그 이후에는 Windows가 알아서 검사하고, 수행하거나 거부하거나 한다.

 

IIS를 공부하다가 보면, web, ftp 등의 익명 요청은 이런 계정으로 처리된다는 식의 말들은, 바로 이 Impersonation이 그 기본 기술이 되는 것이다.

 

항상 하는 이야기지만 자세한 내용은 관련서적과 MSDN을 참조하도록 ^^;



앞선 [Windows 권한 이해하기2](http://blog.naver.com/jjoommnn/130029072191) 에서 Impersonation에 관해서 이야기 했다.

간단히 정리하면, Impersonation이란 Thread가 어떤 계정의 Token으로 실행되는 것처럼 가장(Impersonation)한다는 것이었다.

 

MSDN에서 이 Thread의 Impersonation에 관한 API를 찾아보면 다음 함수가 나온다.

```c++
BOOL WINAPI ImpersonateLoggedOnUser( HANDLE hToken );
```

위의 함수는 현재 Thread를 hToken으로 대표되는 계정으로 Impersonate 시키는 함수다.

즉 아래의

```c++
BOOL LogonUser(
                LPTSTR lpszUsername,
                LPTSTR lpszDomain,
                LPTSTR lpszPassword,
                DWORD dwLogonType,
                DWORD dwLogonProvider,
                PHANDLE phToken
 );
```

함수를 통해서 특정 계정에 대한 Access Token인 hToken을 구해와서 현재 Thread를 그 계정으로 Impersonate 시켜서 현재 Thread가 할 수 있는 작업을 그 계정의 Privilege로 제한 하는 것이다.(물론 Admin 계정으로 Impersonate 시켜면 Privileage가 상승하는 것이다.)

 그런데 위의 함수와 함께 더 알아야 하는 함수가 있는데

```c++
BOOL WINAPI RevertToSelf( void );
```

가 그 함수다. 이 함수는 현재 Thread의 Impersonation을 종료하는 함수라고 한다.

Thread가 Impersonation 할 수 있다면, 그 Impersonation을 끝낼 수도 있어야 한다는 것은 자연스럽다. 그래서 이 함수의 존재도 당연해 보인다.

 

그런데 나는 처음에 이 함수의 정의를 보고 좀 의아했다. 이 함수는 파라미터가 없다. void 다. 내가 기대했던 ReverToSelf 는

 ```c++
BOOL WINAPI RevertToSelf( HANDLE hThread ); 
 ```

였다.

위의 두 함수의 차이점이 뭔지 알겠는가?

위쪽의 함수(MSDN에 나오는 API)는 Impersonate한 Thread가 스스로 Impersonate를 종료하는 것이고 아래쪽의 함수(내가 기대했던 API)는 다른 Thread에서 Impersonate를 끝내는 함수다.

내가 왜 이렇게 기대했는지는 앞선 [Windows 권한 이해하기2](http://blog.naver.com/jjoommnn/130029072191) 에서 예를 든 시나리오 때문이다.

만약 내 프로그램이 어떤 다른 Application을 호스팅하는 프로그램이라고 해보자. 이때 내 프로그램은 Admin 권한으로 실행되지만 호스트되는 Application은 단순한 User 계정으로 실행시키고 싶다. 이때 내 프로그램이 할 수 있는 방법은 호스트 되는 Application을 실행시키는 Thread를 User 계정으로 Impersonate 시키는 것이다.

```
       Application을 실행시킬 Thread 시작
           (Admin 계정으로 실행됨)
                      |
                      |
                      |
      LogOnUser로 User 계정 Token 가져오기
                      |
                      |
      ImpersonateLoggedOnUser로 Thread Impersonate하기
             (User 계정으로 실행됨)
                      |
                      |
                      |    호스트되는 Application 호출
                      +----------------------------------+
                                                         |
                                                         |
                                                 Application 로직 실행 <- Windows 디렉토리를
                                                         |         엑세스 하면 실패할 것이다.
                                                         |
                            호스트되는 Application 반환    |
                      +----------------------------------+
                      |
                      |
     ReverToSelf로 Thread Impersonate 종료
                      |
```



위는 프로그램이 작동되는 상황을 도식으로 표시해 본 것이다.

호스트되는 Application은 Admin 권한이 있어야 호출되는 Windows 디렉토리를 엑세스 하려하면 실패할 것이다. 뭐 그럴 듯 해 보인다.

 

하지만, 다음 도식을 살펴보자.

```
  Application을 실행시킬 Thread 시작
      (Admin 계정으로 실행됨)
                 |
                 |
                 |
 LogOnUser로 User 계정 Token 가져오기
                 |
                 |
 ImpersonateLoggedOnUser로 Thread Impersonate하기
        (User 계정으로 실행됨)
                 |
                 |
                 |   호스트되는 Application 호출
                 +-----------------------------------+
                                                     |
                                                     |
                                           evertToSelf를 호출  <- Impersonate를 종료해버린다.
                                          (Admin 계정으로 실행됨)
                                                     |
                                           Application 로직 실행 <- Windows 디렉토리를
                                                     |             엑세스 하면 성공한다.
                                                     |
                     호스트되는 Application 반환       |
                 +------------------------------------+
                 |
                 |
ReverToSelf로 Thread Impersonate 종료
      (Admin 계정으로 실행됨)
                 |
```

바로 이런 상황이다.

호스트 되는 Application에서 RevertToSelf를 호출해 버리면 Application이 실행되는 Thread는 Impersonate를 종료하게 되고 따라서 Admin 계정으로 실행되게 된다. 그 이후에는 Windows 디렉토리를 엑세스 할 수 있게 된다.

(호스트 되는 Application에서 RevertToSelf를 호출하지 않으면 된다고 하겠지만, 호스트 되는 Application은 내가 만들지 않으므로, 어떤 식으로 작성되는지 알 수가 없다는데 문제가 있다.)

 그래서 나는 이런 상황이 불가능 하도록 RevertToself 가 되어 있기를 기대했던 것이다.

(물론 RevertToSelf 함수가 hThread를 인자로 받게 만들어져 있다고, 즉 다른 Thread에서 Impersonate를 종료하도록 하는 것도 문제가 없는 건 아니다. 만약 Thread가 어떤 작업을 한창 진행중인데 갑자기 다른 Thread에서 그 Thread의 계정을 조정하는 상황이라면 그것도 문제가 될 수 있을 것이다.)

위와 관련하여 인터넷에서 RevertToSelf를 검색하다가 보니 <http://support.microsoft.com/kb/248187/ko> 와 <http://support.microsoft.com/kb/223334> 가 있었다.

이 문서의 핵심은 ASP Application에서 특정 사용자 계정을 Impersonate 하는 방법을 나타내는 것이지만,

내가 이 문서에서 중요하게 봤던 것은 'LogOnUser 함수 호출이 실패할 수 있으니 그 전에 RevertToSelf 를 호출하라' 는 내용이었다.

이 말은 IIS가 Impersonate 시킨 Thread를 호스트되는 ASP Application에서 Impersonate를 종료 시킬 수 있다는 말이다.

사실 이건 좀 맥빠지는 것 같다. 기껏 Impersonate 시켰는데 스스로 종료될 수 있다고 하니 말이다.

하지만 Windows의 권한 API가 그렇다니 따를 수 밖에... 쩝

 

이제까지는 지식의 나열 수준이었는데, 이제 정리를 한번 해 보려한다.

여기 나오는 Windows 운영체제는 Windows NT 계열(Windows NT, Windows 200, Windows XP 등등) 이다. 즉 WIndows 95, 98, ME 는 권한을 지원하는 않는 운영체제이므로 해당사항이 없다.

 

### 정리

#### 1. Windows 운영체제에서 실행되는 모든 Process는 Access Token을 가진다.

이 Accesss Token은 로그인한 계정의 Access Token을 물려 받는다. 또는 CreateProcessAsUser를 통해 Process를 시작한 경우에는 세팅한 계정의 Access Token이 Process의 Access Token이 된다.

이 Process의 Access Token을 Primary Token이라고 하며, Process가 종료할 때까지 변하지 않는다.

관련API : CreateProcess, CreateProcessAsUser

 

#### 2. Access Token은 Privilege 목록을 가지고 있다.

다만 Privilege 목록의 모든 Privilege가 활성화 되어 있지 않을 수 있으며, 사용하고자 하는 Privilege는 활성화 시켜야 한다. 또한 더이상 필요하지 않은 Privilege는 비활성화 시킬 수 있다. 물론 Access Token의 Privilege 목록에 없는 Privilege를 활성화 시킬 수는 없다.

관련API : LookupPrivilegeValue, AdjustTokenPrivilege

 

#### 3. Thread는 2가지 종류의 Access Token을 가질 수 있다.

 Primary Token과 Impersonate Token.

Thread의 Primary Token은 Process의 Access Token과 같은 것이며, Process의 Access Token을 조정하면, 당연히 Thread의 Primary Token도 조절된다. 다른 말로 하면 Thread의 Primary Token이라는 것은 Process의 Access Token과 공유한다고 할 수 있다.

Thread는 생성될 당시의 Primary Token 대신에 다른 계정의 Access Token을 사용하도록 가장(Impersonate)을 할 수 있는데, 이때 Thread를 Impersonate 시킨 Access Token을 Impersonate Token이라고 한다.

만약 Thread가 현재 Impersonate 중이 아니면 OpenThreadToken은 '없는 토큰을 요청했다'는 에러와 함께 실패한다. 즉 Impersonate 하고 있는 않는 Thread는 Thread 고유의 Access Token을 가지지 않는다.

관련API : OpenProcessToken, OpenThreadToken, LogonUser, ImpersonateLoggedOnUser

 

#### 4. 실제 프로그램의 수행은 Thread가 담당한다(Process가 수행하는 것이 아니라). 

따라서Windows 운영체제의 권한(Privilege) 체크는 Thread의 Active Token을 기준으로 수행된다.

이때 Active Token이라는 것은, 만약 Thread가 Impersonate 하지 않은 상태라면 Primary Token이 되며, Thread가 Impersonate 한 상태라면 Impersonate Token이 된다. 따라서 만약 Impersonate 상태인 Thread가 있고, 이 Thread가 수행하려고 하는 작업에 필요한 Prvilege가 Impersonate Token에 없다면 Thread는 작업을 수행할 수 없다.

Impersonate 상태에 있는 Thread는 언제든 스스로 Impersonate를 종료할 수 있다.

관련API : LogonUser, ImpersonateLoggedOnUser, RevertToSelf

 

#### 5. Thread는 자신의 Primary Token을 사용한 Impersonate도 수행할 수 있다. 

이것이 무슨 말이냐 하면, 자신의 Primary Token을 복제해서 자신의 Impersonate Token으로 세팅한다는 말이다.

이것이 필요한 이유는 다음과 같다. 앞서 말했듯이 Process의 모든 Thread는 Process의 Access Token 즉 Primary Token을 공유한다. 따라서 Primary Token의 Privilege를 조정하면 Process의 모든 Thread가 그 영향을 받게 된다. 따라서 특정 Thread만 Privilege를 조정하고 싶을 때는, 그 Thread를 자신의 Primary Token으로 Impersonate 시킨다. 그리고 이 Thread의 Impersonate Token의 Privilege를 조정하면 그 Thread에만 필요한 Privilege를 조절할 수 있다.

관련API : ImpersonateSelf



 [Windows권한 이해하기](http://blog.naver.com/jjoommnn/130029014466) 에서 처음에 보여준 예시 프로그램을 다시 한번 설명하고 마치겠다.



```
너무나 당연한 이야기 겠지만 아래의 코드는 어떤 Thread가 실행하고 있는 중일 것이다.

1) OpenProcessToken( ... );
->Process의 Access Token을 가져온다. 이 Token은 Process에서 실행되는 모든 Thread의 Primary Token과 같다.

2) LookupPrivilegeValue( ..., SE_SHUTDOWN_NAME, ... );
->Access Token의 Privilege 목록에 윈도우 종료 권한이 있는지 살펴본다.

3) AdjustTokenPrivilege( ... );
->Access Token의 Privilege 목록에서 윈도우 종료 권한을 활성화 시킨다. 이후에는 Process의 모든 Thread가 윈도우 종료를 시킬 수 있다. 물론 Impersonate 하고 있는 Thread는 이 권한의 변경에 영향을 받지 않는다.(하지만 Impersonate를 종료(RevertToSelf)를 호출하면 이 변경된 권한을 사용할 수 있다.)

4) ExitWindowsEx( ... );
->Windows 운영체제는 이 API를 호출하는 Thread의 Active Token에서 윈도우 종료 권한이 있는지 검사한 후에, 권한이 있으면 윈도우를 종료한다. (3번에서도 이야기 했지만 만약 현재 Thread가 Impersonate 하고 있는 중이면 이 API 호출이 실패할 수 있다. Impersonate Token에 윈도우 종료 권한이 없다면.)
```





# Windows 권한 이해하기 예제

이전에 Windows 권한에 대해서 설명한 글을 올렸었다.

 [Windows 권한 이해하기1](http://blog.naver.com/jjoommnn/130029014466)

[Windows 권한 이해하기2](http://blog.naver.com/jjoommnn/130029072191)

[Windows 권한 이해하기3](http://blog.naver.com/jjoommnn/130029485318)

[Windows 권한 이해하기4](http://blog.naver.com/jjoommnn/130029660867)

 

그런데 설명만 하고 예제를 만들어 보지 않아서 좀 아쉬운 느낌이 있었다. 그래서 몇가지 테스트를 수행해 본 예제를 만들어 봤다.

 

아래의 예제들은 Windows XP에서 Admin 권한을 가진 계정으로 로그온해서 프로그램을 실행시킨 결과들이다.

그리고 예제들은 개념을 익히기 위해 아주 단순하게 만들것이라 에러 처리가 거의 되어 있지 않다. 열었던 Token에 대해 CloseHandle도 빠져있다. 이점 유의하기 바란다.

그리고 중간중간 결과를 확인하고 다음으로 진행시키기 위해 getch()를 사용했다.



### 1. OpenProcessToken, OpenThreadToken로 Token 가져와 보기

```c++
#include ...
...

DWORD WINAPI ThreadProc( LPVOID lpParameter )
{
    HANDLE hProcessToken;
    HANDLE hThreadToken;
 
    OpenProcessToken( GetCurrentProcess(), TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY, &hProcessToken );
    printf( "OpenProcessToken->" );PrintLastError();

    OpenThreadToken( GetCurrentThread(), TOKEN_ALL_ACCESS, TRUE, &hThreadToken );
    printf( "OpenThreadToken->" );PrintLastError();

    return 0;
}

int _tmain(int argc, _TCHAR* argv[])
{
    DWORD dwThreadID;
    HANDLE hThread = ::CreateThread( NULL, 0, ThreadProc, NULL, NULL, &dwThreadID );
    getch();
    return 0;
}
```

결과는 다음과 같다.

![](.\Images\testoken1_jjoommnn.jpg)

보면 알겠지만, Impersonate 하지 않은 Thread에 대해 OpenThreadToken을 하면 토큰이 없다는 에러와 함께 실패한다.



### **2. ImpersonateSelf로 해당 Thread에서만 윈도우 종료 성공하기**

```c++
#include ...
...

volatile int GO = 0;

DWORD WINAPI ThreadProc( LPVOID lpParameter )
{
    printf( "Thread1 Start " );
    HANDLE hThreadToken;
    ImpersonateSelf( SecurityImpersonation );
    printf( "ImpersonateSelf->" );PrintLastError();
    OpenThreadToken( GetCurrentThread(), TOKEN_ALL_ACCESS, TRUE, &hThreadToken );
    printf( "OpenThreadToken->" );PrintLastError();

    TOKEN_PRIVILEGES tkp; 
    LookupPrivilegeValue( NULL, SE_SHUTDOWN_NAME, &tkp.Privileges[0].Luid );

    tkp.PrivilegeCount = 1;
    tkp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    AdjustTokenPrivileges( hThreadToken, FALSE, &tkp, 0, (PTOKEN_PRIVILEGES)NULL, 0 );
    printf( "AdjustTokenPrivileges->" );PrintLastError();

    //테스트를 위해 실행을 잠시 멈춘다.
    printf( "Thread1 Pause " );
    while( !GO )
        Sleep( 100 );
    
    ExitWindowsEx( EWX_SHUTDOWN | EWX_FORCE, 
                   SHTDN_REASON_MAJOR_OPERATINGSYSTEM |
                   SHTDN_REASON_MINOR_UPGRADE |
                   SHTDN_REASON_FLAG_PLANNED ); 
    printf( "ExitWindowsEx->" );PrintLastError();

    printf( "Thread1 End " );

    return 0;
}

DWORD WINAPI ThreadProc2( LPVOID lpParameter )
{
    printf( "Thread2 Start " );

    ExitWindowsEx( EWX_SHUTDOWN | EWX_FORCE, 
                   SHTDN_REASON_MAJOR_OPERATINGSYSTEM |
                   SHTDN_REASON_MINOR_UPGRADE |
                   SHTDN_REASON_FLAG_PLANNED ); 
    printf( "ExitWindowsEx->" );PrintLastError();

    printf( "Thread2 End " );

    return 0;
}

int _tmain(int argc, _TCHAR* argv[])
{
    DWORD dwThreadID;
    HANDLE hThread;

    hThread = ::CreateThread( NULL, 0, ThreadProc, NULL, NULL, &dwThreadID );
    getch();

    hThread = ::CreateThread( NULL, 0, ThreadProc2, NULL, NULL, &dwThreadID );
    getch();

    //Thread1이 실행을 계속 하도록 만든다.
    GO = 1;

    getch();
    return 0;
}
```

결과는 다음과 같다.

![](.\Images\testoken2_jjoommnn.jpg)

위의 결과화면 후에 바로 윈도우가 종료되는 것을 확인할 수 있을 것이다.

Thread1이 ImpersonateSelf 후에 자신의 Thread Token의 권한을 변경 시켰기 때문에, ProcessToken에는 변화가 없다. 따라서 Thread2는 ProcessToken을 현재 Active Token으로 사용하므로 윈도우를 종료할 권한이 없다.

결국 Thread1만이 윈도우를 종료할 권한을 가지게 된다.



### **3. LogonUser와 ImpersonateLogonUser로 테스트 해 보기**

윈도우 종료예제는 한번 실행시키면 윈도우가 종료되기 때문에 굉장히 귀찮다.(^^;)

그래서 이번에는 시스템의 시간을 바꾸는 예제를 만들어 보자.(SetSystemTime 이라는 API를 이용하게 된다.)

이 테스트를 위해서 테스트용 계정을 하나 만들어 놓자.

Name = "TestUser", Password = "1234" 로 하고, 그냥 Users 그룹에만 넣어둔다. Users 그룹은 SetSystemTime을 할 권한이 없다.

```c++
#include ...
...

DWORD WINAPI ThreadProc( LPVOID lpParameter )
{
    printf( "Thread1 Start " );

    HANDLE hLogonToken;

    LogonUser( TEXT( "TestUser" ), TEXT( "." ), TEXT( "1234" ),
               LOGON32_LOGON_INTERACTIVE, LOGON32_PROVIDER_DEFAULT, &hLogonToken );
    printf( "LogonUser->" );PrintLastError();

    ImpersonateLoggedOnUser( hLogonToken );
    printf( "ImpersonateLoggedOnUser->" );PrintLastError();

    TOKEN_PRIVILEGES tkp; 
    LookupPrivilegeValue( NULL, SE_SYSTEMTIME_NAME, &tkp.Privileges[0].Luid );

    tkp.PrivilegeCount = 1;
    tkp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED; 
    AdjustTokenPrivileges( hLogonToken, FALSE, &tkp, 0, (PTOKEN_PRIVILEGES)NULL, 0 );
    printf( "AdjustTokenPrivileges->" );PrintLastError();

    SYSTEMTIME time;
    GetSystemTime( &time );
    printf( "System Time : %d hour : %d minute : %d second ",
            time.wHour, time.wMinute, time.wSecond );

    time.wMinute += 5;
    SetSystemTime( &time );
    printf( "SetSystemTime->" );PrintLastError();
    
    printf( "Thread1 End " );

    return 0;
}

DWORD WINAPI ThreadProc2( LPVOID lpParameter )
{
    printf( "Thread2 Start " );

    SYSTEMTIME time;
    GetSystemTime( &time );
    printf( "System Time : %d hour : %d minute : %d second ",
            time.wHour, time.wMinute, time.wSecond );

    time.wMinute += 5;
    SetSystemTime( &time );
    printf( "SetSystemTime->" );PrintLastError();

    printf( "Thread2 End " );

    return 0;
}

int _tmain(int argc, _TCHAR* argv[])
{
    DWORD dwThreadID;
    HANDLE hThread;

    hThread = ::CreateThread( NULL, 0, ThreadProc, NULL, NULL, &dwThreadID );
    getch();

    hThread = ::CreateThread( NULL, 0, ThreadProc2, NULL, NULL, &dwThreadID );
    getch();

    return 0;
}
```

결과는 다음과 같다.

![](.\Images\testoken3_jjoommnn.jpg)

hread1을 "TestUser"로 Impersonate 시켰기 때문에, AdjustTokenPrivileges에서 부터 벌써 권한이 없다고 나오고, 실제로 SetSystemTime의 호출도 실패한다. (따라서 GetSystemTime으로 가져온 시간의 변화가 없다.)

Thread2는 Admin 권한의 ProcessToken을 사용하는 중이므로, SetSystemTime이 성공하게 된다.(5분 빨라졌다.)

 

이상으로 간단하게 Windows 운영체제의 권한을 이해해 할 수 있는 예제를 만들어 봤는데,

역시 예제를 봐야 뭔가 느낌이 제대로 오는 것 같다.

