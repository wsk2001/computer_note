# Google Bard 파이썬(Python) API 활용

출처: https://teddylee777.github.io/python/bard-python-api/



목차[토큰 발급](https://teddylee777.github.io/python/bard-python-api/#토큰-발급)[예제](https://teddylee777.github.io/python/bard-python-api/#예제)[Reference](https://teddylee777.github.io/python/bard-python-api/#reference)

이번 포스팅에서는 **구글 바드(Google Bard)** 의 파이썬(Python) API 사용법에 대해 알아보겠습니다.

Bard는 Google AI에서 만든 대규모 언어 모델입니다. ChatGPT와 유사한 인터페이스를 가지며, 무료로 이용할 수 있고, 한글을 우선 지원한다는 점이 특징입니다. [https://bard.google.com](https://bard.google.com/) 에서 직접 테스트 해 볼 수 있으며, 구글 계정으로 로그인 하면 **무료로 이용** 가능합니다.

## 토큰 발급[Permalink](https://teddylee777.github.io/python/bard-python-api/#토큰-발급)

Google Bard 에서는 아직 **정식 API 서비스를 지원하지 않습니다(2023년 05월 26일 기준).** 향후에는 OpenAI 처럼 API를 개방해줄 것으로 기대합니다. 따라서, 정식으로 API 토큰을 발급하는 방법은 현재로써는 없고, 크롬브라우저에서 **토큰(Token)**을 얻어와야 합니다. 다음의 과정을 통해 토큰을 얻을 수 있습니다.

> **크롬브라우저**를 열고 [https://bard.google.com](https://bard.google.com/) 에 접속하여 **구글 계정에 로그인** 합니다.

![로그인](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-01.png)

> 로그인 한 뒤, **F12**를 누르거나 마우스 우클릭하여 **“검사”** 를 클릭하여 **개발자 도구**를 엽니다.

![검사](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-02.png)

> 개발자 도구 상단 메뉴에서 **“Application”** 을 클릭합니다.

![Application 클릭](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-03.png)

> 왼쪽 탭에서 “Cookies” - “[https://bard.google.com”](https://bard.google.xn--com-9o0a/) 을 **클릭**합니다.

![bard.google.com 클릭](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-04.png)

> “**HSID**” 밑에 “**__Secure-1PSID**” 을 **클릭**합니다.

!["__Secure-1PSID" 클릭](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-05.png)

> 하단의 **“Cookie Value”** 의 값을 **복사**합니다.

![하단의 Cookie Value 클릭](https://teddylee777.github.io/images/2023-05-26-bard-python-api/google-bard-06.png)

## 예제[Permalink](https://teddylee777.github.io/python/bard-python-api/#예제)

Google Bard 파이썬 패키지(`bardapi`)를 설치합니다.

- 프로젝트 GitHub-https://github.com/dsdanielpark/Bard-API

```ba
pip install bardapi
```

> 파이썬(Python) 코드 예시

```
from bardapi import Bard

# 첫 번째 단계에서 복사한 토큰 입력
token = 'XAgJ4E0bx7Rnr3K8xKlZB6PYdr7v1FLtM0lt2wo9AhwS4bj7sjidofsDJSIF-easjidDJF.'

# Bard 객체 생성
bard = Bard(token=token)

# 프롬프트 입력(질의)
print(bard.get_answer("양자역학에 대해 간략하게 알려줘")['content'])
양자역학은 물질과 에너지의 가장 작은 단위인 양자를 연구하는 물리학의 한 분야입니다. 양자역학은 미시 세계의 법칙을 설명하는 데 매우 성공적이었고, 오늘날 우리가 사용하는 거의 모든 기술은 양자역학에 기반을 두고 있습니다.

양자역학은 고전역학과는 매우 다른 특징을 가지고 있습니다. 예를 들어, 양자역학에서는 입자는 위치와 운동량을 동시에 정확하게 알 수 없으며, 입자는 파동과 같은 성질을 가지고 있습니다. 또한, 양자역학에서는 입자가 관측될 때 그 상태가 바뀌는 불확정성의 원리가 적용됩니다.

양자역학은 매우 복잡한 이론이지만, 오늘날 우리 삶에 매우 중요한 역할을 하고 있습니다. 양자역학은 반도체, 레이저, 핵무기, 양자 컴퓨터 등 다양한 기술의 발전에 기여했습니다. 또한, 양자역학은 우주의 기원과 구조를 이해하는 데에도 중요한 역할을 하고 있습니다.

양자역학은 아직까지 완전히 이해되지 않은 부분이 많지만, 물리학의 가장 중요한 분야 중 하나임에는 틀림없습니다. 양자역학의 발전은 우리 삶을 더욱 편리하고 풍요롭게 만들 것입니다.
```

## Reference[Permalink](https://teddylee777.github.io/python/bard-python-api/#reference)

- https://github.com/dsdanielpark/Bard-API



### 오류 발생

```
Traceback (most recent call last):
  File "bard_test.py", line 7, in <module>
    bard = Bard(token=token)
  File "C:\Users\wonso\AppData\Local\Programs\Python\Python38\lib\site-packages\bardapi\core.py", line 55, in __init__
    self.SNlM0e = self._get_snim0e()
  File "C:\Users\wonso\AppData\Local\Programs\Python\Python38\lib\site-packages\bardapi\core.py", line 128, in _get_snim0e
    raise Exception(
Exception: SNlM0e value not found. Double-check __Secure-1PSID value or pass it as token='xxxxx'.
```

**해결 방법**

관리자. __Secure-1PSID와 __Secure-1PSIDTS라는 두 가지 쿠키를 사용해야 합니다. __Secure-1PSIDTS가 없으면 html 페이지 bard.google.com에 승인되지 않은 사용자가 있습니다.

다중 쿠키 값을 설정해야 하는 경우. 바드 쿠키 객체를 사용합니다.

``` py
from bardapi import BardCookies

cookie_dict = {
    "__Secure-1PSID": "xxxxxxxxx",
    "__Secure-1PSIDTS": "xxxxxxxxx",
    # Any cookie values you want to pass session object.
}

bard = BardCookies(cookie_dict=cookie_dict)

print(bard.get_answer("안녕하세요"))
```



내가 테스트 한 source code

``` py
from bardapi import BardCookies

cookie_dict = {
    "__Secure-1PSID": "xxxxx",
    "__Secure-1PSIDTS": "xxxxxx",
}

bard = BardCookies(cookie_dict=cookie_dict)

# 프롬프트 입력(질의)
print(bard.get_answer("양자역학에 대해 간략하게 알려줘")['content'])

```



### 가끔 될때도 있지만 대부분의 경우 쿠키 관련 오류가 발생함

