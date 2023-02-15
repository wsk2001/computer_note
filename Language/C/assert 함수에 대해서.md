# assert 함수에 대해서

출처: [[C언어/C++\] assert 함수에 대해서 : 디버깅을위한, 더 안전한 코드를 위한 오류 검출 방법 (tistory.com)](https://blockdmask.tistory.com/286)



**1. C/C++ assert함수란?**

\- 간단하게 말하자면 **assert 함수는** **디버깅 모드**에서 **개발자가** 오류가 생기면 치명적일 것이라는 곳에 **심어 놓는** **에러 검출용** 코드입니다.

\- 예를들어서 int score 가 음수가 나오면 절대 안되는 상황이라고 가정을 하면, 보통 우리는 아래와 같이 코드를 작 성 할 수 있습니다.

``` cpp
int GetScore()
{
    //어딘가에 정의 되어있는 score라고 하면

    if(score < 0)
    {
        //log를 심을 수 도 있다.
        return 0;
    }
    return score;    
}
```

\- 위와 같은 방법으로 score를 0으로 뱉어낸다고 가정하면, 제대로 동작하는지 알 수 있을까요? 로그를 심는 방법도 있겠지만, 그것은 제가 수많은 로그를 뒤지고 뒤져서 찾아내야하는 단점도 존재합니다.

\- 그렇기 때문에 **assert라는 함수를 이용해서 오류가 발생할것 같은 곳에 넣어**둔다면 **프로그램이 저절로 개발자에게 알려**줍니다.

\- 이 **assert함수**에 걸리게 되면 **버그 발생위치, call stack등 여러 정보**를 알 수 있게 됩니다. 작은 규모의 프로젝트가 아니라 큰 규모의 프로젝트에서는 이런 **call stack, dump가 남는게 정말정말 소중**하기 때문에 assert 함수도 잘 사용하게 되면, 상대적으로 **적은 시간 안에 버그를 찾을 수** 있습니다.

\- **중요한것은** 이 assert 함수는 **디버그 모드에서만 컴파일**이 되기 때문에, 다른 코드에 영향을 주지 않는 코드만 넣어야합니다.



**2. assert 함수 원형 및 사용법**

\- C언어 헤더파일 : <assert.h>

\- C++ 헤더파일 : <cassert>

\- 함수 원형 : **void assert (int expression)**

\- expression이 **"0"**이 되면 **assert error**가 나타나게 됩니다.  (즉 거짓 이면 오류 발생)

\- <span style="color:red"> **C++스타일로 말씀드리면 expression이 false가 나면! assert error 입니다.** </span>



**ex)** assert(A != NULL); 

//A가 null이 아니면 **true(1)**가 나오게 되므로 **pass** 이지만,

//A가 null이 맞다면 **false(0)**가 나오게 되므로 **assert error**가 나오게 됩니다.



**3. C++을 이용한 assert 함수 사용예시**

``` c++
#include<iostream>
#include<cassert>
using namespace std;
int main(void)
{
    int score;
    while (true)
    {
        
        cout << "Input score : ";
        cin >> score;
 
        //0보다 작은 score가 들어오면 assert error!!
        assert(score >= 0);
        cout << "=> score : " << score << endl;
    }
    return 0;
}
```



 **4. C언어를 이용한assert 함수 사용예시**

``` c
#include<stdio.h>
#include<assert.h> //위에 헤더파일 잊으면 아니되옵니다.
int main(void)
{
    int score;
    while (1)
    {
        printf("Input score: ");
        scanf("%d", &score);
 
        //0보다 작은 score가 들어오게되면 assert error가 발생해요! 띠용띠용
        assert(score >= 0);
        printf("=> score : %d\n", score);
    }
    return 0;
}
```




![img](.\images\99E480425BD0842820)

**▲코드 수행 결과**

위 결과해서 **Assertion Failed : score >= 0** 이라고 친절하게 알려주면서 에러가 발생하게 됩니다.



**5. 추가 지식**

\- 혹시 모르시는 분들을 위해 제가 위에서 

"**assert 함수는** **디버깅 모드**에서 **개발자가** 오류가 생기면 치명적일 것이라는 곳에 **심어 놓는** **에러 검출용** 코드입니다."

라고 말씀드렸잖아요. 여기서 "디버깅 모드"가 뭐지? 라고 하시는 분들이 있을 것 같은데요. 간단하게 말씀드리면

\- 디버깅 모드는 **컴파일 할때** **디버깅 정보를 삽입**해서 디버깅을 할 수 있도록 하는 **컴파일 모드중에 하나**입니다.

\- **디버깅 모드인지 릴리즈 모드인지 보는 방법**은 **"비주얼 스튜디오 왼쪽 위를 보면 나와있습니다."**

\- ![img](.\images\9976C7485BD0828212)

\- 잘 보이시죠? 이쪽이 Debug라고 나와있으면 Debug모드이고, Release모드라고 나와있으면 Release 모드입니다.

\- 보면 알 수 있듯이 눌러서 변경할 수 있어요!