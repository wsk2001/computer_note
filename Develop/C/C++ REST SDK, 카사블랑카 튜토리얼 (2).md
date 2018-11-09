## C++ REST SDK, 카사블랑카 튜토리얼 (2)

네이티브 개발자를 위한 클라우드 라이브러리

C++ REST SDK, 카사블랑카 튜토리얼 (2)



코드네임 카사블랑카인 마이크로소프트(이하 MS)의 ‘C++ REST SDK’는 클라우드 환경에서 사용하는 고수준의 라이브러리로, 기존의 C++ 라이브러리와 달리 문법이 모던하고 추상화 수준이 높아 프로그래밍이 용이할 뿐 아니라 C++ 특유의 번거로움이 줄어 기대되고 있다. C++ REST SDK의 전체 코드가 공개된 오픈소스로서 리눅스를 공식 지원하는 점도 이러한 기대에 한몫하고 있다.

지난 시간에는 카사블랑카 SDK의 설치부터 task를 이용한 비동기 프로그래밍, http_listener를 이용한 간단한 웹서버 개발 등을 통해 카사블랑카 SDK의 특징들을 살펴봤다. 윈도우는 물론 리눅스에서 카사블랑카를 컴파일하고 테스트 케이스를 실행하는 과정을 통해 카사블랑카 SDK가 크로스 컴파일을 통해 다양한 OS를 지원할 수 있음을 확인했다. 스레드 생성을 위해서는 beginthreadex를 통해 낮은 수준으로 제어해야만 했지만 std::thread는 OS 관점이 아닌 개발자 관점에서 스레드를 이용할 수 있었으며, 여기서 더 나아가 task는 스레드를 추상화해 코드를 스레드 중심이 아닌 비즈니스 로직 중심으로 작성할 수 있는 이점을 제공한다.

카사블랑카는 .NET의 비동기 연산과 Node.js의 간결함, 비동기 I/O, 얼랭(Erlang)의 액터(Actor) 모델의 영향을 받았는데, 카사블랑카에서 제공하는 대표적인 객체를 이용해 간단한 클라이언트와 서버 애플리케이션을 개발함으로써 이러한 특징을 확인해보자.

지난 연재를 시작한 와중에 카사블랑카의 1.0이 공식 발표됐지만, 지난 글과 마찬가지로 이 글에서도 카사블랑카 0.6 베타가 사용됐음을 밝혀둔다.

![img](../../images/Casablanca/tech_img525.jpg) 
<그림 1> 카사블랑카의 구조


#### http_client를 이용한 웹 클라이언트

http_client는 서버와의 커넥션을 추상화한 객체로, http_client 클래스의 인스턴스를 통해 서버에 요청하고 응답을 받을 수 있다. http_client.h를 선언하고 web::http::client:: http_client 네임스페이스를 통해 http_client에 접근할 수 있는데, 기존의 네트워크 프레임워크처럼 복잡한 절차나 낮은 수준의 네트워크 제어 없이도 http_client 인스턴스의 몇 개의 메소드를 호출하는 것만으로 웹 클라이언트의 기능을 이용할 수 있다.

<리스트 1> 지정된 URI에 웹페이지 요청 및 출력

```cpp
#include &lt;windows.h&gt;
#include &lt;iostream&gt;
#include "http_client.h"
using namespace web::http;
using namespace web::http::client;
#pragma comment(lib, "casablanca110.lib")
int _tmain(int argc, _TCHAR* argv[])
{
	uri_builder builder;
	builder.set_scheme(U("http"));
	builder.set_host(U("se.naver.com"));
	builder.set_port(80);
	uri u(builder.to_uri());
	http_client client(u);
	pplx::task&lt;http_response&gt; responseTask =
		client.request(methods::GET, U("/index.html");
	http_response response = responseTask.get();
	utility::string_t str = response.extract_string().get();
	std::wcout &lt;&lt; str.c_str() &lt;&lt; std::endl;
	return 0;
}
```

http_client 클래스를 사용하는 기본 방법은 먼저 생성자를 통해 URI(Uniform Resource Identifiers)를 설정하고 add_handler로 필요한 추가 요청을 작업해야 한다. 그 다음으로 Request 메소드로 요청한 후 응답을 위해 task로 대기한다. 이를 통해 http_response로 결과값을 얻을 수 있다.

http_client의 생성자는 기본적으로 연결한 서버의 uri 객체를 받는다. 이 객체는 URI를 구성하는 각 항목을 설정하고 관리하는 uri 클래스를 이용해 구현되며, 다음과 같이 생성자에 ‘U() 매크로’를 이용하면 문자열을 직접 설정할 수 있다.

uri u(U(“http://se.naver.com”));

생성자의 인자로 대입된 문자열은 uri 객체에서 URI를 구성하는 요소별로 파싱돼 각각의 요소를 관리하기 쉽다. URI를 구성하는 각 항목을 동적으로 생성하거나 조합할 때에는 uri 객체를 만드는 uri_builder 클래스를 사용한다. uri_builder 클래스의 set_fragment, set_host, set_path, set_port, set_query, set_ scheme, set_user_info 등의 ‘set_*’ 메소드군을 통해 uri의 구성요소를 생성한다. 또한 append, append_path, append_query 등의 ‘append_*’ 메소드군을 이용하면 요청 작업에 대한 관리 및 제어도 가능한 것이 특징이다. 뿐만 아니라 is_valid나 to_string, to_uri 등의 메소드를 이용하면 객체의 상태를 확인하거나 다양한 상황의 URI 값을 활용할 수 있어 유용하다.

<리스트 2> uri_builder로 네이버의 ‘쿼리 URI’ 생성    

```cpp
uri_builder b;
b.set_scheme(U("http"));
b.set_host(U("search.naver.com"));
b.set_port(80);
b.set_path(U("search.naver"));
b.set_query(uri::encode_uri(U("query=카사블랑카"))); 
```

uri_builder를 이용하면 요소들을 정확하게 관리할 수 있다. 각 요소를 결합하는 ‘//, ?’나 ‘&’와 같은 기호로 인한 사소한 실수를 방지해줄 뿐 아니라 URI의 각 요소를 uri_builder 객체에서 정확히 처리할 수 있다. 한글 인코딩의 경우 <리스트 2>처럼 uri::encode_uri나 uri::decode 등의 헬퍼 함수로 쉽게 변환할 수 있으며, <리스트 2>에서 생성된 다음과 같은 URI도 to_string이나 to_uri 등의 메소드로 손쉽게 활용할 수 있다.

http://search.naver.com:80/search.naver?query=%EC%B9%B4%EC%82%AC%EB%B8%94%EB%9E%91%EC%B9%B4

http_client에 오버로딩된 생성자를 보면 uri와 함께 http_ client_config 객체를 인자로 받는 함수가 있다. http_client _config 클래스는 웹 프록시와 패킷 순서 제어(guarantee order), 대기시간을 설정할 수 있는데, http_client의 생성자로 http_client_config 인스턴스를 넘기면 http_client가 가진 추상적인 서버와의 연결 상태를 설정할 수 있다.

http_client(const uri &base_uri, const http_client_config& client _config);

#### 표준 출력 스트림의 지역 설정 변경

이미 알고 있겠지만 C++에서 표준 출력 스트림을 이용하기 위해서는 cout을, 유니코드 환경일 경우 wcout을 사용한다. 윈도우의 표준 문자셋은 유니코드지만 개발자가 간단히 테스트할 경우 표준 출력 스트림으로 wcout보다 cout을 선호하는 경향이 많다. 이런 이유에서인지 유니코드 스트링을 표준 출력 스트림을 통해 출력할 경우 지역 설정에 따라 콘솔에서 한글이 보이지 않는 경우가 더러 있다. 간단한 영문과 한글 혼용 문자열을 표준 출력 스트림으로 출력하는 <리스트 3>이 그 대표적인 예다.

&lt;&lt;리스트 3&gt; 영문과 한글이 혼용된 문자열 출력
```cpp
#include <iostream>
#include <conio.h>
int _tmain(int argc, _TCHAR* argv[])
{
	wchar_t const * str = L"hello 월드";
	std::wcout << str << std::endl;
	_getch();
	return 0;
} 
```

만약 영문과 한글이 혼용된 문자열인 ‘hello 월드’를 출력했는데 ‘hello’만 출력됐다면 지역 설정이 한글을 출력할 수 없기 때문이다. C++ 표준 라이브러리는 특정 언어의 구현, 날짜 및 시간 규약, 통화와 같은 지역에 특화된 정보를 지원하는 std::locale 클래스를 제공한다. std::locale 클래스 덕분에 지역이 달라도 C++ 표준 라이브러리의 컨테이너나 표준 출력 스트림이 정확한 정보를 유지할 수 있는 것이다. 따라서 wcout 인스턴스로 한글을 정상적으로 출력하기 위해서는 지역 설정을 변경해야 한다. 지역 설정 변경에는 함수를 이용한 방법, 글로벌하게 지역 설정을 변경하는 방법, wcout 객체의 지역 설정 값을 변경하는 방법 등이 있다.

&lt;리스트 4&gt; 지역 설정 변경 방법
```cpp
setlocale(LC_ALL, "kor"); // 함수로 지역 설정 변경
std::locale::global( std::locale("kor")); 
// 전역적으로 지역 설정 적용
std::wcout.imbue(std::locale("kor")); 
// wcout 객체의 지역 설정 변경
```

먼저 locale 파일에서 제공하는 함수인 setlocale로 설정 카테고리와 지역 문자열 값을 인자로 받자. 카테고리를 LC_ALL로 설정하고 지역 문자열의 값을 ‘kor’로 설정했다면 C++ 표준 라이브러리의 지역 설정 값이 ‘KOREAN’으로 변경돼 표준 출력 스트림으로 한글 유니코드를 출력할 수 있게 된다.

&lt;리스트 5&gt; 지역 설정과 관련된 함수

```cpp
int _configthreadlocale(int _Flag);
char * setlocale(int _Category, const char * _Locale);
struct lconv * localeconv(void);
_locale_t _get_current_locale(void);
_locale_t _create_locale(int _Category, const char * _Locale);
void _free_locale(_locale_t _Locale);
```



카테고리 값으로는 LC_ALL, LC_COLLATE, LC_CTYPE, LC_MONETARY, LC_NUMERIC, LC_TIME이 있는데 여기서 LC_CTYPE는 대소문자 처리, LC_MONETARY는 화폐 단위 지역 설정 지정, LC_TIME은 strtime 함수 등의 날짜와 시간 양식 지정에 사용되며, LC_ALL로는 모든 지역 설정 값을 지정할 수 있다.

std::locale::global은 C++ 형태로 전역적으로 지역 설정 값을 변경하며, 지역 설정 변경에는 std::wcout.imbue를 이용한다. 그러나 만약 지역 설정 값이 잘못돼 오류가 발생할 경우 인식할 수 없는 유니코드 값으로 인해 더 이상 문자열이 출력되지 않으니 주의하자(<리스트 6> 참조).

&lt;리스트 6&gt; ‘Hello’ 다음의 문자열이 출력되지 않는 경우

```cpp
wchar_t const * str = L"Hello 월드";
wchar_t const * str2 = L"(World)";
std::wcout << str << std::endl;
std::wcout << str2 << std::endl;
```



예컨대 <리스트 6>에서 ‘Hello 월드’란 문자열과 ‘(World)’란 문자열을 표준 출력 스트림을 버퍼로 순차 출력하는데, 지역 설정이 올바르지 않으면 콘솔에 ‘Hello’만 출력되고 ‘월드’나 ‘(World)’는 출력되지 않는 것이다. 이 경우 wcout::fail 함수로 상태 값을 얻어와 wcout::clear 함수로 버퍼를 비울 수 있다.

&lt;리스트 7&gt; ‘Hello (World)’ 출력 유도

```cpp
wchar_t const * str = L"Hello 월드";
wchar_t const * str2 = L"(World)";
std::wcout << str << std::endl;
if(std::wcout.fail()) {
	std::wcout.clear();
}
std::wcout << str2 << std::endl;
```



그러므로 http_client로 웹페이지를 불러왔는데, html 페이지 전체가 출력되지 않는다면 지역 설정 값을 먼저 확인해야 한다.

#### http_client 클래스

uri 클래스의 스킴(scheme), 호스트(host), 포트번호(port), 경로(path), 쿼리(query), 프래그먼트(fragment)를 uri_builder를 통해 생성하고 프록시와 대기시간 등의 연결 정보를 http_ client_config 클래스로 설정한 뒤 http_client의 생성자를 호출하면 서버와 추상화된 커넥션을 이용할 수 있다.

http_client 클래스의 request 메소드는 서버에 요청 방법을 지정할 수 있는데, 일반적으로 HTTP의 GET, PUT, POST, DELETE를 사용할 수 있다. <리스트 1>처럼 request의 두 번째 인자로 추가적인 경로나 쿼리, 플래그먼트 등도 전달할 수 있으니 참고하자.

request 메소드는 task

 타입을 반환하며, 이 타입으로 미뤄볼 때 request가 메인 함수와 비동기적으로 동작함을 유추할 수 있다. 대부분의 네트워킹 작업이 일어나는 카사블랑카 클래스들의 메소드는 대부분 비동기로 동작하기 때문에 task 클래스를 반환한다.

task 클래스의 get 메소드는 비동기로 동작하는 task 인스턴스 작업이 완료되면 task의 템플릿 인자로 넘긴 타입의 결과값을 반환해 메인 스레드의 작업을 동기화시킨다. 이 부분도 비동기로 처리하고 싶다면 <리스트 8>처럼 task의 then 메소드로 메인 스레드와 비동기적인 작업으로 분리할 수 있다.

&lt;리스트 8&gt; task의 then 메소드를 이용한 비동기 작업
```cpp
client.request(methods::GET).then([=](pplx::task<http_response> task) {
	// ...
	http_response response = task.get();
	// ...
});
```

서버와 클라이언트 간의 통신을 살펴보기 위해 이전 시간에 소개한 서버 예제를 수정한 <리스트 9>를 참고하자. 이 코드는 키보드 입력이 있기 전까지 클라이언트는 HTTP GET 요청 내용을 콘솔 화면에 덤프하고, 응답코드로 ‘OK’와 함께 ‘OK!’란 문자열을 반환한다. 관리자 권한 실행이 불편하다면 uri의 주소를 ‘http://localhost:9991/test’로 변경하면 되는데, localhost로 서버 측의 주소를 변경한 경우 클라이언트의 호출 URI도 localhost에 맞춰야 함을 잊지 말자.

&lt;리스트 9&gt; HTTP GET 요청에 응답하는 서버의 코드
```cpp
#include <windows.h>
#include <iostream>
#include <conio.h>
#include "http_listener.h"
using namespace web::http;
using namespace web::http::listener;
#pragma comment(lib, "casablanca110.lib")
#pragma comment(lib, "testlistener110.lib")
int _tmain(int argc, _TCHAR* argv[])
{
	uri u(L"http://*:9991/test");
	http_listener listener = http_listener::create(u);
	listener.support(web::http::methods::GET, [](http_request request) {
		std::wcout << std::endl << L"dump:" << std::endl;
		std::wcout << request.to_string() << std::endl;
		http_response response;
		response.set_status_code(status_codes ::OK);
		response.headers().add(U("Casablanca")
			, U("Microsoft C++ REST SDK"));  // 사용자 정의 헤더 요소
		 response.set_body("OK!");
		request.reply(response);
	});
	listener.open();
	pplx::task<unsigned long> listening = listener.listen([]() {
		std::wcout << "press any key..." << std::endl;
		_getch();
	});
	listening.wait();
	listener.close();
	return 0;
}
```

<리스트 9>가 정상적으로 실행됐다면 이제 <리스트 10>을 실행해 <리스트 9>의 결과값인 ‘OK!’가 정상적으로 출력되는지 확인하자.

&lt;리스트 10&gt; 서버에 HTTP GET을 요청한 후 결과 출력
```cpp
uri u(U("http://localhost:9991/test"));
http_client client(u);
pplx::task<http_response> responseTask = client.request(methods::GET);
http_response response = responseTask.get();
if(status_codes::OK == response.status_code()) {
	if(utility::string_t::npos != response.headers().content_type().find(U("text/plain"))) {
		utility::string_t str = response.extract_string().get();
		std::wcout << str.c_str() << std::endl;
	}
}
```

http_client의 request 메소드는 서버와의 통신이 성공하든 실패하든 정상적인 값을 반환한다. 만일 메소드를 잘못 사용해 설정 값에 오류가 있는 경우는 예외가 발생하지만, 서버 측의 오류 코드를 반환한 경우에도 request 메소드 자체는 성공한 것이다. 예외처리를 줄이기 위해서는 요청 시 서버에서 응답한 코드의 값이 의도된 값인지를 확인하는 과정이 필요한데, 서버의 응답 코드 값은 <리스트 10>처럼 http_response 객체의 status_code 메소드를 통해 확인할 수 있다.

서버로부터 온 HTTP 정보는 HTTP 헤더에 기술돼 있다. 헤더 정보는 http_response 클래스의 headers 메소드를 이용해 접근할 수 있는데, <리스트 11>은 헤더 정보 중 본문 길이와 본문의 타입을 반환한다. 여기서 body_len은 3, content_type은 서버로부터 받은 ‘text/plain; charset=utf-8’이 대입된다.

&lt;리스트 11&gt; content_length와 content_type 메소드의 사용
```cpp
size_t body_len = response.headers().content_length();
utility::string_t content_type = response.headers().content_type();
```

http_headers가 제공하는 특정 헤더의 값을 얻을 때에는 http_headers의 match 메소드를 이용하자(<리스트 12> 참조).



&lt;리스트 12&gt; match를 사용한 헤더 정보 조회
```cpp
bool check = false;
size_t body_len2 = 0;
utility::string_t content_type2;
check = response.headers().match(U("Content-Length"), body_len2);
check = response.headers().match(U("Content-Type"), content_type2);
```

match 메소드는 헤더 요소와 함께 템플릿 특수화로 size_t 타입이나 string_t 타입을 이용해 값을 얻을 수 있다. 헤더에 지정된 요소가 없으면 match는 false를 반환한다.

자주 사용하는 방식은 아니지만 match를 이용하면 필더링하기 위한 헤더 값이나 사용자 정의 헤더 요소들의 값도 얻을 수 있다. <리스트 13>은 헤더 요소 중 Server와 Data 항목을 가져오는데, 헤더 요소 중 Server에는 MS의 HTTP API를 사용했음을 의미하는 ‘Microsoft-HTTPAIP/2.0’ 값이 전송되며, <리스트 9>의 사용자 정의 헤더 요소에 입력된 casablanca의 값이 ‘Microsoft C++ REST SDK’인지 확인한다.

&lt;리스트 13&gt; 사용자 정의 헤더 요소 조회
```cpp
utility::string_t serverType;
utility::string_t dateTime;
utility::string_t sdkName;
response.headers().match(U("Server"), serverType);
response.headers().match(U("Date"), dateTime);
response.headers().match(U("Casablanca"), sdkName);
```

서버가 보내온 응답으로부터 본문 메시지를 분리하기에 앞서, 헤더 정보에서 본문 메시지의 타입을 확인해야 한다. <리스트 10>은 헤더에 포함된 Content-Type 요소가 ‘text/plain’인지를 확인한 후 http_response의 extract_string 메소드를 통해 string_t 타입으로 본문 메시지를 읽어온다. extract_string은 본문 메시지가 MIME 텍스트 타입일 경우 본문을 추출할 수 있는데 분리할 수 없다면 예외가 발생한다.

Content-Type 요소가 ‘application/json’인 경우 JSON (JavaScript Object Notation)을 지원하는 extract_json 메소드로 본문 메시지를 얻을 수 있고, 바이트 형태로 본문 메시지를 분리할 때에는 extract_vector를 이용하면 된다.

JSON 타입의 값 교환
데이터를 송수신할 때 자료를 표현하는 방법 중 하나인 JSON은 자바스크립트의 구문 형식을 따르지만 타 언어나 플랫폼에 적합하도록 표현할 수도 있다.

&lt;리스트 14&gt; JSON으로 표현한 정보의 예
```json
{
	"이름": "테스트",
	"나이": 25,
	"성별": "여",
	"기혼": true,
	"주소": "서울특별시 양천구 목동",
	"특기": ["농구", "도술"],
	"가족관계": {"#": 2, "아버지": "홍판서", "어머니": "춘섬"},
	"회사": "경기 안양시 만안구"
}
```
데이터 표현 방식임은 분명하다. 표현하는 형태가 XML에 비해 간결하고 기호로 구분되는 이름과 값이 쌍을 이루는 간단한 구조 덕분에 이를 지원하는 다양한 C++ 라이브러리가 있으며, 심지어 간단한 JSON 데이터는 개발자가 직렬화 코드를 직접 작성하기도 한다. 카사블랑카는 JSON 데이터의 쉬운 사용을 돕는 web::json::value 클래스를 제공한다(<리스트 15> 참조).
<리스트 15> 카사블랑카에서 지원하는 JSON 타입
```cpp
web::json::value v0 = web::json::value::null();
web::json::value v1 = web::json::value::number(17);
web::json::value v2 = web::json::value::number(3.1415);
web::json::value v3 = web::json::value::boolean(true);
web::json::value v4 = web::json::value::string(U("Hello Again!"));
web::json::value v5 = web::json::value::object();
web::json::value v6 = web::json::value::array();
```


JSON으로 값을 표현하는 방법 중 컨테이너에 포함된 값을 JSON으로 직렬화하는 가장 일반적인 방법은 web::json::value ::field_map을 사용하는 것이다. field_map은 std::pair로 묶인 std::vector라고 생각하면 이해하기 쉽다. field_map을 통해 컨테이너에 JSON의 각 필드값을 채우고 web::json::value ::object로 변환한 후 to_string을 통해 JSON 문자열로 변환한다. field_map을 이용해 JSON 타입으로 포맷팅하는 코드는 <리스트 16>이다.

&lt;리스트 16&gt; field_map을 이용한 JSON 포맷팅
```cpp
web::json::value::field_map f;
f.push_back(
	std::pair&lt;web::json::value, web::json::value&gt;
	(web::json::value::string(U("name1")), web::json::value::number(1))
	);
f.push_back(
	std::pair&lt;web::json::value, web::json::value&gt;
	(web::json::value::string(U("name2")), web::json::value::string(U("hello")))
	);
f.push_back(
	std::pair&lt;web::json::value, web::json::value&gt;
	(web::json::value::string(U("name3")), web::json::value::boolean(U("true")))
	);
f.push_back(
	std::pair&lt;web::json::value, web::json::value&gt;
	(web::json::value::string(U("name4")), web::json::value::null())
	);
web::json::value obj = web::json::value::object(f);
std::wcout &lt;&lt; obj.to_string() &lt;&lt; std::endl;
```
출력값 :
{ "name1" : 1, "name2" : "hello", "name3" : true, "name4" : null }


object를 이용해 JSON을 즉시 생성하는 것도 가능하다. <리스트 17>은 표준 템플릿 라이브러리를 사용하는 것과 유사하게 JSON으로 포맷팅한다.

&lt;리스트 17&gt; object를 이용해 JSON으로 포맷팅
```cpp
web::json::value obj = web::json::value::object();
obj[U("name1")] = web::json::value::string(U("hi there"));
obj[U("name2")] = web::json::value::number(77);
std::wcout &lt;&lt; obj.to_string() &lt;&lt; std::endl;
```
출력값 :
{ "name1" : "hi there", "name2" : 77 }


web::json::value 값을 다시 자료형으로 변환하려면 어떻게 해야 할까? 그 답은 web::json::value의 ‘as_*’ 계열 메소드다. <리스트 18>은 web::json::value 타입으로 변환한 값을 다시 원래 자료형의 변수로 변환한다.

&lt;리스트 18&gt; as_* 계열 메소드를 이용해 원래의 타입으로 변환
```cpp
web::json::value v1 = web::json::value::number(17);
web::json::value v2 = web::json::value::number(3.1415);
web::json::value v3 = web::json::value::boolean(true);
web::json::value v4 = web::json::value::string(U("Hello Again!"));
//...
int32_t av1 = v1.as_integer();
double av2 = v2.as_double();
bool av3 = v3.as_bool();
utility::string_t av4 = v4.as_string();
```

web::json::value 인스턴스의 원래 타입은 json::value:: value_type을 반환하는 web::json::value 클래스의 type 메소드로 확인할 수 있다. web::json::value는 개발자의 편의를 위해 is_null, is_number, is_boolean, is_string, is_array, is_object 함수도 제공해 유용하다. 만약 web::json::value의 원래 타입이 아닌 다른 타입의 as_* 계열 함수를 호출하면 예외가 발생하는데 이때 is_* 계열 함수로 방어 코딩을 할 수 있다.

JSON 문자열을 다시 원래 타입으로 변경하는 가장 편리한 방법은 재정의된 대괄호 연산자를 통해 특정 항목 값의 web:: json::value::object를 얻은 후 as_* 계열 함수를 이용해 원래 타입 값으로 변환하는 것이다. web::json::value는 이터레이터를 제공하는데, <리스트 19>는 JSON 타입의 문자열로 변환한 후 다시 원래 값을 얻는다. 

&lt;리스트 19&gt; JSON 문자열을 파싱하는 코드의 예
```cpp
web::json::value obj = web::json::value::object();
obj[U("name1")] = web::json::value::string(U("hi there"));
obj[U("name2")] = web::json::value::number(77);
utility::string_t tmp = obj[U("name1")].as_string();
for(web::json::value::const_iterator iter = obj.cbegin()
	; iter != obj.cend(); ++iter) {
		std::wcout << iter->first.to_string() << L" : " 
			<< iter->second.to_string() << std::endl;
}
```


출력값 : 


```
"name1" : "hi there"
"name2" : 77
```



출처:  https://www.kdata.or.kr/info/info_04_view.html?field=title&keyword=%C4%AB%BB%E7%BA%ED%B6%FB%C4%AB&type=techreport&page=1&dbnum=173993&mode=detail&type=techreport