# wcout 유니코드 출력

**이 방법 보다 아래쪽의 방법이 더 간단하고 source code 에서 워닝을 표시하지 않는다.**



출처: <http://x108zero.blogspot.com/2014/09/cc-wcout.html>

유니코드로 프로그램 작성 했으므로 당연히 cout 말고 wcout를 사용했다.

하지만 출력이 안된다;;
알고 보니 한글설정을 해야 한다.

그래서
std::locale::global( std::locale( "kor" ) );
이것을 추가했는데
안되는 경우가 있다.
왜냐하면 Grlobal Locale을 설정하는 것이라서
Stream에 미치지 않기때문이다.

그래서
 std::wcout.imbue( std::locale( "kor" ) ); // 이것을 추가하면 된다.
 std::wcin.imbue( std::locale( "kor" ) ); // cin은 이것을 추가

```c++
#include <iostream>
using namespace std;

int wmain ( int argc, wchar_t *argv[] )
{
 std::wcout.imbue( std::locale( "kor" ) ); // 이것을 추가하면 된다.
 std::wcin.imbue( std::locale( "kor" ) ); // cin은 이것을 추가

 wcout << L"한글테스트" << endl;

 wchar_t* str = L"wchar_t 한글테스트";
 wcout << str << endl;

 return 0;
}  
```



---

# wcout, wprintf 등에서 한글 출력 안될 때 해결법

출처: <https://psychoria.tistory.com/m/132?category=556278>

유니코드 프로젝트에서 wcout이나 wprintf를 사용했을 때 한글이 출력이 안되는 문제가 생길 때가 있습니다.

이럴 때 문제를 해결하기 위해서 setlocale() 함수를 사용하면 됩니다.

main 함수 등에서 다음과 같이 호출하면 됩니다.

```
setlocale(LC_ALL, "");
```

이렇게 하면 시스템의 로케일을 따라가기 때문에 한글 윈도우에서 정상적으로 한글이 출력됩니다.

""를 "korean"으로 변경하면 한글로 특정해서 호출할 수도 있습니다.

wcout에서 한글이 정상적으로 출력되지 않을 때 다음과 같은 방법으로 처리할 수도 있습니다.

```c++
std::wcout.imbue(std::locale(""));
```

이후에 wcout을 호출하게 되면 정상적으로 한글이 출력되는 것을 알 수 있습니다.