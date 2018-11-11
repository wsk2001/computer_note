### C++ REST SDK, 카사블랑카 튜토리얼



네이티브 개발자를 위한 클라우드 라이브러리

C++ REST SDK, 카사블랑카 튜토리얼 (1)

가장 대중적이면서도 범용적인 프로그래밍 언어인 C/C++는 소켓 라이브러리 기반으로 제작돼 최근 대용량화되고 서비스화되고 있는 IT 업계의 요구에 대응하기 어려웠다. 이에 마이크로소프트(이하 MS)는 이러한 시대적 변화와 클라우드 환경을 고려한 ‘C++ REST SDK’를 공개했다. 코드네임이 카사블랑카(Casablanca)인 C++ REST SDK는 MFC나 ATL로 상징되는 기존 MS의 라이브러리와 달리 오픈소스로서 윈도우와 리눅스 등의 다양한 OS를 지원한다.

OS 개발자는 OS의 기능을 확장할 수 있도록 C/C++ 컴파일러도 함께 개발한다. 이 덕분에 애플리케이션 개발자는 OS가 제공하는 가장 원초적인 API로 낮은 수준의 애플리케이션이나 서비스도 개발할 수 있다. 또한 여러 모듈을 조합해 화려한 UI를 구현하거나 게임처럼 복잡한 애플리케이션도 개발할 수 있다. C++는 인라인 어셈블러와 포인터로 상징되는 저수준의 코딩, 절차적 처리 방식, 클래스 기반의 객체지향 언어란 점에서 메타 프로그래밍 모델과 혼합해 사용할 수 있다. 그럼에도 C++는 싱글톤 패널이나 뮤텍스, 세마포어 등을 지원하지 않아 아쉬움이 크다. 만약 Sync를 붙여 단일 실행을 보장하는 메소드를 만들 수 있다면 개발이 얼마나 편해질까? 이 같은 물음에 비야네 스타라우스트롭은 문법은 최소로만 제공하고 기능은 모두 라이브러리상에서 구현하는 것이 C++의 철학이라고 밝힌 바 있다. 그렇다면 신기술의 등장과 시대적 변화가 반영된 라이브러리는 어디서 얻을 수 있고 이러한 라이브러리의 안전성은 과연 얼마나 보장되는 것일까?



#### C++ REST SDK, 카사블랑카

MS는 애저 서비스를 출시하면서 닷넷을 클라우드 컴퓨팅까지 확장시켰지만, 네이티브 영역은 아직 최신 IT 기술에 대한 지원이 다소 부족한 편이다. 웹서비스가 이제 대중화됐지만 이를 지원하는 MS가 제공하는 라이브러리는 이를 지원하지 않았고, 윈도우7이 출시되고 나서야 네이티브 웹서비스를 개발할 수 있는 공식 API가 등장했다. 카사블랑카는 네이티브 애저 서비스를 지원하기 위해 등장했지만, 점차 개선된 버전이 나오면서 범용적으로 사용 가능한 순수한 클라우드-서버 통신 기능과 벤더에 특화된 서비스가 별도로 분리됐다. 결국 네이티브 개발자가 C++ 문법으로 쉽게 사용할 수 있도록 클라이언트-서버를 위한 비동기식 C++ API 형태로 바뀌고 있으며 이에 따라 HTTP, TCP, UDP, 웹소켓, URIs와 같은 서비스와 JSON과 같은 REST 서비스를 제공한다. 카사블랑카 0.6 버전은 공식적으로 비주얼 스튜디오 2010 이상을 지원한다. 물론 각 비주얼 스튜디오 버전별 express 또한 지원된다. 카사블랑카가 비주얼 스튜디오 2010 이상을 지원하는 것은 C++ 11 문법에 기반을 둔 병렬 패턴 라이브러리(Parallel Patern Library)와 같은 최신 CRT DLL 파일에 중속되기 때문이다. 리눅스의 경우 부스트(boost) 라이브러리를 사용한다.

병렬 패턴 라이브러리의 네임스페이스는 Concurrency이지만, 카사블랑카 0.6 버전에서는 현재 pplx다. 이는 초기 버전의 카사블랑카는 병렬 패턴 라이브러리와 함께 구현됐기 때문에 정식 출시된 병렬 패턴 라이브러리와도 호환되기 위해서다. 네임스페이스가 pplx인 카사블랑카 병렬 패턴 라이브러리와 동시성 런타임의 병렬 패턴 라이브러리는 일부 기능과 몇몇 메소드의 대소문자 정도만 다르며, 추후에는 동시성 런타임으로 통합될 예정이다.



#### 카사블랑카 설치

윈도우 환경에서 카사블랑카 라이브러리를 설치하는 방법은 간단하다. 카사블랑카 소스 코드는 코드플렉스(www.codeplex. com)에서 다운로드할 수 있는데, 먼저 다음 웹사이트에 방문해 git를 설치해야 한다. 참고로 이 글은 가장 최신 버전의 카사블랑카인 0.6을 기준으로 설명한다.

http://git-scm.com/downloads

git를 설치한 후 적절한 폴더를 생성하고, git의 clone을 이용해 카사블랑카 소스 코드를 다운로드하자. clone은 git를 이용해 형상관리에 등록된 소스셋을 로컬 폴더에 복사한다. 다음 명령어를 실행하면 현재 폴더에 casablanca란 폴더가 생성된 후 카사블랑카 소스 코드를 다운로드한다.

git clone https://git01.codeplex.com/casablanca casablanca

이제 다운로드된 폴더의 Releasecasablanca1XX.sln 솔루션 파일을 열자. 비주얼 스튜디오 2012를 사용한다면 casablanca 110.sln 솔루션 파일을 열면 프로젝트를 구성하는 코드 전체를 살펴볼 수 있다. 만약 빌드만 하고 싶다면 솔루션을 열지 말고 비주얼 스튜디오의 IDE로 빌드를 진행하면 된다. 솔루션 전체를 빌드하면 빌드 종속성에 따라 라이브러리가 빌드된다. 카사블랑카 0.6 버전의 경우 종속성에 문제가 있다. 솔루션 전체를 빌드하면 오류가 발생하는데, 이때는 솔루션에 포함된 프로젝트 중 UniTestpp110 프로젝트를 먼저 빌드한 후 솔루션을 빌드해야 한다. 빌드가 완료되면 BinariesWin32 폴더에 디버그와 릴리즈 모드용 라이브러리가 각각 생성된다. 이렇게 생성된 dll 파일 중 _test가 붙은 파일은 테스트 케이스다. TestRunner.exe 과 같은 형식으로 테스트 케이스를 실행할 수 있으며, 카사블랑카 0.6 버전에서 제공하는 테스트 케이스 모듈은 다음과 같다.

```
header_test110.dll
HttpClient110_test.dll
JSON110_test.dll
pplx110_test.dll
streams110_test.dll
testlistener110.dll
TestUnitTestpp110.dll
UnitTestpp110.dll
Uri110_test.dll
```

다음 4개의 dll 파일은 실행 코드에서 참조 가능한 카사블랑카 0.6의 라이브러리다. casablanca1XX.dll이 메인 라이브러리인데, 추가적으로 생성되는 라이브러리는 카사블랑카 버전에 따라 다를 수 있다.

```
casablanca110.dll
CommonUtilities110.dll
testlistener110.dll
HttpTestUtilities110.dll
```


카사블랑카 웹페이지는 빌드 과정을 거치지 않아도 개발자들이 바로 사용할 수 있도록 빌드된 바이너리와 개발 리소스 등을 제공하는데, 다음 웹사이트에서 다운로드할 수 있다.

http://casablanca.codeplex.com/releases

인스톨러로 카사블랑카를 설치하면 라이브러리 파일, 헤더 파일, 샘플 등과 몇 가지의 리소스도 함께 설치된다. 물론 카사블랑카 소스 코드를 빌드하는 것과 차이가 없다. 다만 인스톨러를 통해 설치할 경우 빌드된 리소스에 카사블랑카 파일의 설치 경로, 비주얼 스튜디오 환경, 위자드 템플릿 등이 추가되며, 이 또한 실제 예제를 테스트하거나 개발하는 데 큰 차이는 없다.

카사블랑카 0.6 버전은 정적 라이브러리 형태로 프로젝트를 빌드할 수 없음에 주의하자. 만약 정적 라이브러리 형태로 빌드를 시도하면 라이브러리는 빌드되더라도 정적 라이브러리 형태로 사용할 수 없다. 카사블랑카 개발진은 차후에 정적 라이브러리 형태로도 사용할 수 있게 지원할 예정이다.

리눅스에 카사블랑카를 설치하는 것도 크게 어렵지는 않다. 우분투 12.10 버전을 기준으로 설명한다. 윈도우와 마찬가지로 다음 명령어로 git를 설치하자.

`sudo apt-get install git`카사블랑카는 최신 C++ 문법을 사용하므로 기본적으로 g++ 4.7 버전 이상의 컴파일러가 필요하다. 다음 명령어를 통해 컴파일러를 업데이트하자. 저장소 주소(ppa:ubuntu-toolchain-r/test)는 설치 환경에 따라 다를 수 있으므로 만약 설치가 실패한다면 가장 먼저 저장소 주소를 확인하자.

```
sudo add-apt-repository ppa:ubuntu-toolchain-r/test 
sudo apt-get update 
sudo apt-get install g++-4.7 
```



카사블랑카에 종속된 libxml과 libboost는 다음 명령어로 설치할 수 있다. 부스트는 최소 1.48 이상 버전을 설치해야 한다.

```
sudo apt-get install libxml++2.6-dev 
sudo apt-get install libboost-all-dev 
```



이제 git의 clone 명령어를 통해 카사블랑카 소스 코드를 다운로드하자. 다음 명령어를 실행하면 현재 디렉터리에 casablanca 디렉터리가 생성되며, 이후 소스 코드를 다운로드한다.

git clone https://git01.codeplex.com/casablanca casablanca

마지막으로 Release 디렉터리로 이동한 후 make clean을 입력해 빌드를 수행하자. 빌드가 완료되면 Binaries에 라이브러리 파일이 만들어진다. Binaries/Debug32/run_tests.sh를 실행해 테스트 케이스가 정상적으로 동작하는지 확인하자. 카사블랑카는 현재 기능 추가와 안정화가 진행 중이기 때문에 개발 환경(OS)에 따라 기능에 차이가 있을 수 있다(카사블랑카는 윈도우 버전이 먼저 개발된 후 리눅스 버전이 포팅된다).



#### task를 이용한 비동기 프로그래밍

MS는 개발자들이 쉽고 빠르게 안정적인 애플리케이션을 개발할 수 있도록 동시성 런타임(Concurrency Runtime)과 병렬 프로그래밍 모델을 함께 발표했다. 이 중 병렬 패턴 라이브러리로 개발된 템플릿 클래스 ‘task’는 미래 패턴(Future Pattern)을 객체로 구현했다. task 템플릿 클래스의 몇 가지 메소드를 활용하면 특정 작업에 대한 비동기를 쉽게 구현할 수 있어 유용하다. task 템플릿 클래스는 아직 정식 출시된 병렬 패턴 라이브러리에 포함되지 않았다. 병렬 패턴 라이브러리 샘플팩에 포함된 병렬 패턴 라이브러리의 확장을 통해 사용할 수 있으며, 병렬 패턴 라이브러리의 샘플팩은 다음 주소에서 다운로드할 수 있다.

http://archive.msdn.microsoft.com/concrtextras/Release/ProjectReleases.aspx

샘플팩의 ConcRTExtras 폴더의 헤더 파일을 포함시키면 병렬 패턴 라이브러리 확장을 사용할 수 있다. <리스트 1>은 task로 미래 패턴을 사용한 예다. 예컨대 slow_operation_A() 함수가 비동기로 동작해야 한다면, 해당 함수의 반환값 타입을 인자로 받는 task 객체 ‘taskObject_A’를 생성해야 한다. 생성된 taskObejct_A는 slow_operatino_A() 비동기로 작업을 수행하고 대기한다.

`<리스트 1> task를 이용한 미래 패턴 사용`

```cpp
task taskObject_A = slow_operation_A();
taskObject_A.then([](T i) {
	return slow_operation_B(i);
});
bool isDone = taskObject_A.is_done();
T A_return_value = taskObject_A.get();
task taskObject_B = slow_operation_B();
when_all(taskObject_A, taskObject_B).then([](tuple result) {
	// do something
});
```

비동기로 수행된 작업 결과는 task 객체가 가지고 있는 then() 메소드나 get() 메소드로 얻을 수 있다. get() 메소드는 결과값을 즉시 반환하는데, 반환값은 task의 템플릿 인자인 T다. 이 T는 slow_operation_A의 반환값과 같다. 만약 slow_operation_A()가 수행되는 중에 task 객체의 get() 메소드가 호출되면 get() 메소드는 비동기적으로 실행되고 있는 slow_operation_A()가 반환될 때까지 블록된다. is_done() 메소드를 이용하면 slow_ operation_A() 함수의 수행 완료 여부를 알 수 있어 이러한 문제를 예방할 수 있다. task는 해당 함수의 동작이 완료되면 다음 실행할 then() 메소드를 제공한다. then() 메소드는 또다시 task나 함수자(Functor), 함수 포인터, 람다식 등의 실행식을 인자로 받는다. 이러한 실행식은 task 객체를 생성할 때 템플릿 인자로 대입했던 T 타입의 변수를 인자로 받는다. 개발자는 비동기로 실행되는 slow_operation_A() 함수의 실행이 완료된 후에만 실행해야 할 코드를 then() 메소드의 인자인 실행식 핸들러로 등록할 수 있다. 여기서 주목해야 할 것은 then() 메소드의 반환값이 task 객체라는 점이다. 따라서 slow_operation_A() 함수가 비동기로 실행되고 종료됐을 때, 실행식을 then() 메소드가 실행되는 순간 비로소 또 다른 함수인 slow_operation_B()가 비동기로 실행된다. 결국 then() 메소드의 결과값이 then()을 호출하고 다시 그 결과값의 then()을 호출하는 식으로 정확한 종료 시점과 실행 시점을 보장할 수 있다.

종료 시점의 판단을 위해 when_all 도움 함수가 제공된다. 이를 활용하면 2개 이상의 task가 대기돼도 모든 task의 종료 시점을 알 수 있다. 이밖에도 task는 몇 가지의 유용한 메소드와 도움 함수들을 제공하는데, 보다 자세한 내용은 헤더 파일에서 확인할 수 있다.



#### 카사블랑카의 스트링 처리

문자열은 OS, 플랫폼, 개발 언어, 프레임워크나 프로토콜마다 각각 표현 방식이 다를 수 있다. 예컨대 윈도우 API는 char 배열 형태의 문자열과 wchar_t 형태의 UTP-16 타입의 문자열을 인자로 받을 수 있다. 또한 일반적으로 사용하는 std::wstring은 플랫폼에 따라 2바이트 문자가 아닐 수도 있다. 리눅스나 다른 OS의 경우 UTF-8 타입의 문자열을 사용하는 것이 일반적이며, 네트워크를 통해 문자열을 송수신할 때에는 base64 등을 이용해 인코딩한 후 UTF-8로 보내기도 한다. 카사블랑카는 기준이 되는 문자 타입을 환경과 플랫폼에 따라 casblanca::char_t로 정의했는데, 문자열 타입은 casablanca::string_t이다.

`<리스트 2> 문자열을 매크로로 변환`

```cpp
#include 
auto uri = http::uri(U("http://127.0.0.1"));
```

플랫폼에 따라 빈번하게 사용되는 스트링을 쉽게 변환할 수 있도록 U(str) 매크로가 제공된다. TEXT(str) 형태로 문자열을 사용하는 것에 익숙하다면 편리할 것이다. 카사블랑카 유틸리티 함수 중 Utility::conversions 네임스페이스를 통해 이러한 도움 함수를 제공하는 데 그중 일부는 <리스트 3>에서 확인할 수 있다.



#### 카사블랑카로 simpleServer 개발

지금부터는 카사블랑카로 간단한 서버 프로그램을 개발해보자. 이를 위해서는 먼저 빌드한 환경을 테스트 프로젝트에 적용해야 한다.
`<리스트 3> 카사블랑카가 제공하는 문자열 변환 유틸리티 함수들`
```cpp
namespace Utility {
	namespace conversions
	{
		std::string utf16_to_utf8(const utf16string &w); 
		utf16string utf8_to_utf16(const std::string &s);
		utf16string usascii_to_utf16(const std::string &s);
		utility::string_t to_string_t(const std::string &s);
		utility::string_t to_string_t(const utf16string &s);
		utf16string to_utf16string(const std::string &value);
		utf16string to_utf16string(const utf16string &value);
		std::string to_utf8string(const std::string &value);
		std::string to_utf8string(const utf16string &value);
		utility::string_t to_base64(const std::vector& data);
		utility::string_t to_base64(uint64_t data);
		std::vector from_base64(const utility::string_t& str);
		std::string from_base64_str(const std::string& str);
	}
}
```

테스트 프로젝트를 만들고 헤더파일의 경로를 추가하기 위해서는 프로젝트 속성의 [Configuration] → [Properties] → [VC++ Directories]의 헤더파일 디렉터리와 라이브러리 디렉터리에 각각 카사블랑카 SDK가 설치된 Releaseinclude와 BinariesWin32Debug 또는 BinariesWin32Release를 추가해야 한다. 참고로 이번 개발에는 casablanca110.lib를 사용한다.

이제 빌드한 라이브러리를 이용해 간단한 웹서버를 만들어보자. 이를 위해 카사블랑카에서 제공하는 http_listener를 사용하자. 카사블랑카 0.6 버전은 프로젝트의 기능을 빠르게 업데이트하기 위해 HTTP listener, IIS Integration, Azure와 관련된 코드가 메인 라이브러리에서 제외됐다. 오는 4분기 메인 라이브러리에 통합될 예정이다. 또한 actor 기능의 경우 별도의 프로젝트로 독립돼 발표된다. 본론으로 돌아와 http_listener를 사용하기 위해서는 다음과 같은 http_listener의 헤더 파일 경로는 추가해야 한다.

Release	estsFunctionalhttputilities	estlistenerinclude

통합이 예정돼 있으므로 http_listener의 헤더파일 경로는 차후 바뀔 수 있다. http_listener가 메인 라이브러리에 정식으로 포함되면 다른 헤더 파일처럼 기본 헤더 파일 경로(Release include)로 사용할 수 있다. http_listener는 서버와 관련된 래핑 객체이며, 이를 이용하면 간단한 코딩만으로도 웹서버는 물론 http 핸들러도 쉽게 만들 수 있다. 이제 기본 카사블랑카 라이브러리인 casablanca110.dll과 여기서 분리된 http_listener 객체의 구현체인 testlistener110.dll을 테스트 파일에서 찾을 수 있게 복사하자. 이는 카사블랑카 0.6 버전에서는 http_listener가 메인 라이브러리에서 분리돼 있기 때문이다. <리스트 4>는 simpleServer 코드다.

`<리스트 4> simpleServer`

```cpp
#include "stdafx.h"
#include "http_listener.h"
using namespace web::http;
using namespace web::http::listener;
#pragma comment(lib, "casablanca110.lib")
#pragma comment(lib, "testlistener110.lib")
int _tmain(int argc, _TCHAR* argv[])
{
	http_listener::create(U("http://localhost:9991/test"), [](http_request message) {
		message.reply(status_codes::OK, U("Hello World!"));
	}).listen([]() { fgetc(stdin); }).wait();
	return 0;
}
```

<리스트 4>를 실행하려면 웹브라우저에서 서버에 정의된 주소로 접속하면 된다.
<리스트 4>에서 http_listener::create 함수의 인자에 대입한 localhost 대신 특정 주소나 80번 포트로 설정하고 싶다면, 비주얼 스튜디오를 관리자 권한으로 실행하고 디버깅하거나 테스트 서버 바이너리를 관리자 권한으로 실행해야 한다.
`<리스트 5> Node.js의 간단한 서버 코드`
```cpp
var http = require('http');
http.createServer(function (request, response) {
  response.writeHead(200, {'Content-Type': 'text/plain'});
  response.end('Hello World!');
}).listen(8080, '127.0.0.1');
```

<리스트 5>는 Node.js 코드인데 <리스트 4>와 비교하면 상당히 유사하다. 실제로 카사블랑카 개발팀은 직관적이면서도 간결한 Node.js에 영향을 많이 받았다고 한다.



#### http_listener 객체를 이용한 http 서버의 동작

http_listener 객체를 이용하면 복잡한 http 서버 구조나 동작도 쉽게 이해할 수 있을 뿐 아니라 소켓을 잘 몰라도 간단한 http 서버를 만들 수 있다. http_listener 객체로 만든 http 서버는 <리스트 6>인데, <리스트 4>보다 코드가 더 직관적이고 이해하기 쉽다. 카사블랑카의 헤더 파일, lib, dll 파일의 환경을 맞춘 후 <리스트 6>을 컴파일하고 웹브라우저에서 http://localhost: 9991/test에 접속해 보자.

`<리스트 6> http_listener 객체를 이용한 http 서버`
```cpp
#include 
#include 
#include "http_listener.h"
using namespace web::http;
using namespace web::http::listener;
#pragma comment(lib, "casablanca110.lib")
#pragma comment(lib, "testlistener110.lib")
int _tmain(int argc, _TCHAR* argv[])
{
	uri u(L"http://localhost:9991/test");
	http_listener listener = http_listener::create(u);
	listener.support(web::http::methods::GET, [](http_request request) {
		std::wcout << "method:" << std::endl;
		std::wcout << request.method().c_str() << std::endl << std::endl;

		std::wcout << "request uri:" << std::endl;
		std::wcout << request.request_uri().to_string().c_str() << std::endl << std::endl;
        
		std::wcout << "headers:" << std::endl;
		http_headers headers = request.headers();
		for(http_headers::const_iterator iter = headers.begin();
			iter != headers.end(); ++iter) {
				std::wcout << iter->first.c_str() << “ - “
				<< iter->second.c_str() << std::endl;
		}

		std::wcout << std::endl << L"body:" << std::endl;
		std::vector body = request.extract_vector().get();

		std::wcout << std::endl << L"dump:" << std::endl;
		std::wcout << request.to_string() << std::endl;

		request.reply(status_codes::OK, U("Hello World!"));
	});
	listener.open();
	pplx::task listening = listener.listen([]() {
		for(int i = 0;i < 30;++i) {
			std::cout << ".";
			Sleep(1000);
		}
	});
	listening.wait();
	listener.close();
	return 0;
}
```

<리스트 6>의 http_listener는 create, support, open, listen, close 순으로 멤버 함수를 호출한다. 소켓 프로그래밍에 익숙하다면 메소드만으로도 동작 과정을 유추할 수 있을 것이다. http_listener가 노출하고 있는 메소드들은 개발자의 편의를 위해 다양한 상황에 대한 오버링 메소드를 제공한다. add_handler를 포함시키면 나열된 메소드를 쉽게 사용할 수 있다. http_listener는 create를 통해 객체를 생성한다. create를 통해 생성할 때에는 uri 객체를 인자로 대입할 수 있다. uri는 URL (Uniform Resource Locator)이 포함된 URI(Uniform Resource Identifier)를 관리하는 객체다. 이 객체는 특정 요소를 파싱하거나 빠르게 접근할 때 유용하니 기억해 두자. uri 객체는 다음과 같이 일반적인 URI 형태를 생성자의 인자로 받을 수 있다. protocol : // server [: port] / path ? query # fragment create로 생성한 http_listener는 support 메소드로 핸들러를 지정할 수 있다. <리스트 6>은 요청 방식과 핸들러를 매칭해 support 메소드의 인자로 대입했다. 카사블랑카 0.6 버전의 경우 GET, POST, PUT, DEL, HEAD, OPTIONS, TRACE, CONNECT, MERGE, PATCH 방식의 요청을 인식할 수 있다. support의 이런 특징을 이용하면 GET 방식과 POST 방식을 구분해 각각 핸들러로 등록할 수 있으며, 헤더 파일에서 support가 지원하는 다양한 오버로딩 함수를 확인할 수 있다. <리스트 6>은 GET 방식인 경우 핸들러가 람다식으로 정의됐다. support 메소드를 이용해 핸들러를 정의했다면 open 메소드로 서버 포트를 연다. 그리고 listen 메소드를 통해 서버가 대기 상태일 때의 동작도 정의할 수 있다. listen 메소드는 listen 동작의 미래 패턴인 task를 반환한다. 이 task의 wait 메소드를 호출하면 listen 메소드는 반환을 대기한다. 마지막으로 listen 함수가 반환돼 서버의 open 동작이 종료되면 close 메소드를 호출해 내부 서버 자원을 반환한다.

`<리스트 7> 웹브라우저를 통해 접속한 결과`
```
....method:
GET
request uri:
http://localhost:9991/test
headers:
Accept - text/html, application/xhtml+xml, */*
Accept-Encoding - gzip, deflate
Accept-Language - ko-KR
Connection - Keep-Alive
DNT - 1
Host - localhost:9991
User-Agent - Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/
6.0)
body:
dump:
GET http://localhost:9991/test HTTP/1.1
Accept: text/html, application/xhtml+xml, */*
Accept-Encoding: gzip, deflate
Accept-Language: ko-KR
Connection: Keep-Alive
DNT: 1
Host: localhost:9991
User-Agent: Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6
.0)
........................
```

<리스트 6>을 실행하면 서버가 open되고, listen 메소드가 수행된다. listen 메소드는 30초 동안 마침표를 찍으며 대기한다. 이때 웹브라우저를 통해 create 메소드의 인자로 대입한 URI에 접근하면 HTTP GET 방식의 웹페이지 요청이 들어오고 support에 정의된 람다식의 핸들러가 수행된다. 람다식은 http_request를 인자로 받으며, 이 http_request에는 클라이언트로부터 요청받은 정보가 기술돼 있다. http_request 객체에 포함된 method, request_uri, headers, extract_string 등의 메소드로 요청받은 정보를 얻을 수 있으며, <리스트 7>은 이러한 정보를 출력한 결과다. 요청 정보를 받은 서버는 적절한 값을 클라이언트에 반환한다. 이때 http_response 객체가 사용되며, <리스트 6>은 set_status _code 메소드와 set_body를 이용해 반환된 결과값과 클라이언트에 표기할 문자열을 정의한다. 이후 http_request 객체의 reply 메소드를 통해 요청 결과를 반환한다.



출처: https://www.kdata.or.kr/info/info_04_view.html?field=&keyword=&type=techreport&page=46&dbnum=173229&mode=detail&type=techreport

