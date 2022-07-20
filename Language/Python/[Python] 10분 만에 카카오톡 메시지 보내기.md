# [Python\] 10분 만에 카카오톡 메시지 보내기

출처: https://novice-engineers.tistory.com/9?category=908185



### 1. 사용자 토큰 발급

## **1. Kakao Developers 사이트 이동**

[www.developers.kakao.com/](https://developers.kakao.com/)

[ Kakao Developers카카오 API를 활용하여 다양한 어플리케이션을 개발해보세요. 카카오 로그인, 메시지 보내기, 친구 API, 인공지능 API 등을 제공합니다.developers.kakao.com](https://developers.kakao.com/)

## **2. 로그인**



![img](.\Images\kakao_img_01.png)로그인



처음 Kakao Developers를 이용하시는 분은 로그인 과정에서 이용 약관을 수락해 주세요!

 

## **3. 애플리케이션 생성**



![img](.\Images\kakao_img_02.png)이전이전

012

애플리케이션 생성 슬라이드쇼



**위 슬라이드 쇼를 참고해 주세요.**

\1. 로그인 후 상단에서 내 애플리케이션으로 이동해 주세요.

\2. 애플리케이션 추가하기 클릭.

\3. 앱 이름, 사업자명을 입력 후 저장을 눌러주세요.

\4. 생성된 애플리케이션을 클릭해 주세요.

 

## **4. 앱 키 저장! - ★**



![img](.\Images\kakao_img_03.png)앱 키



**REST API 키**를 메모장에 복사해 주세요. 중요합니다!!!

 

## **5. 활성화 설정**



![img](.\Images\kakao_img_04.png)이전이전

012

활성화 설정 슬라이드쇼



**위 슬라이드 쇼를 참고해 주세요.**

\1. 좌측 메뉴에서 카카오 로그인 클릭

\2. 활성화 설정에서 OFF를 클릭하여 ON으로 설정

\3. 스크롤 내린 후 Redirect URI를 클릭

\4. 위 슬라이드 쇼 3번째 URl를 그대로 입력 **( https://example.com/oauth ->그대로 복사 붙여 넣기!)**

**[나중에 바꿔도 상관없으나 처음 테스트는 반드시 일치시켜주세요!]**

 

## ***\*6. REST API 이동\****

***\*# 간단하게 REST API란?\****

***\*[www.novice-engineers.tistory.com/10?category=908185](https://novice-engineers.tistory.com/10?category=908185)\****

[ ※ 간단하게 REST API란?※ API(Application Programming Interface)란 응용프로그램에 사용할 수 있도록, OS(운영체제)나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스를 의미합니다. 문장으로 들으니까 매novice-engineers.tistory.com](https://novice-engineers.tistory.com/10?category=908185)



![img](.\Images\kakao_img_05.png)이전이전

012

REST API 이동



**위 슬라이드 쇼를 참고해 주세요.**

\1. 상단에서 문서 클릭

\2. 스크롤을 조금 내린 후 [카카오 로그인 박스 안에 있는 REST API 클릭]

\3. [Ctrl + F]를 누른 후 Request를 입력

\4. URL이란 박스 안에 아래와 같은 코드를 확인하실 수 있습니다.

**GET /oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code HTTP/1.1
Host: kauth.kakao.com**

 

**=> https://****kauth.kakao.com\**/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code 이러한 주소가 됩니다.\****

 

## ***\*7. 인증 코드 받기 - ★\****



![img](.\Images\kakao_img_06.png)이전이전

012

인증코드 URI



**위 슬라이드 쇼를 참고해 주세요.**

https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code

\1. 위 주소를 그대로 복사하여 메모장에 붙여 넣기

\2. {REST_API_KEY}에 4번에서 저장한 REST API 키를 붙여 넣기

\3. {REDIRECT_URI}에 5번의 Redirect URI를 넣어주기 (**https://example.com/oauth)**

**[반드시 자신의 REST_API_KEY를 입력해 주세요!]**

\4. 완성된 주소를 인터넷 주소창에 입력 (완성된 주소는 슬라이드 쇼 1번 이미지 형식입니다.)

\5. 동의하고 계속하기 버튼 클릭

**6. 3번째 슬라이드와 같이 뜨시면 성공입니다! 당황하지 마시고 전체 주소를 복사 후 메모장에 붙여 넣기 해주세요.**

\7. example.com/oauth?code=~~~~에서 '=' 이후의 코드만 따로 저장해 주세요. (빨간 네모 박스만 저장)

**ex) 제 3번째 이미지에서는 -7sSp7Xxqw2QAew7-rctW.... 만 저장하면 되는 겁니다!**

 

## ***\*8. 사용자 토큰 받기 - ★\****

**1. Visual Studio Code 실행**

**설치가 안 되신 분은 설치를 부탁드립니다.**

[www.novice-engineers.tistory.com/3](https://novice-engineers.tistory.com/3)

[ #파이썬 - 2 (설치)1. 파이썬 설치하기 아래 이미지를 클릭하여 다운로드 경로로 이동해주세요. Download Python The official home of the Python Programming Language www.python.org 1-1. 위 빨간색 박스를 클릭하여 설치를 진..novice-engineers.tistory.com](https://novice-engineers.tistory.com/3)

**2. 코드 그대로 붙여 넣기**

```
import requests

url = 'https://kauth.kakao.com/oauth/token'
rest_api_key = '자신의 REST API 키'
redirect_uri = 'https://example.com/oauth'
authorize_code = '-7sSp7Xxqw2QAew7-rctWJtZT4ycERTzQM_nylg8bX0OxFcgGegkA9jaVvPLEVOX_FrkxQo9dRoAAAF2qFCIsA'

data = {
    'grant_type':'authorization_code',
    'client_id':rest_api_key,
    'redirect_uri':redirect_uri,
    'code': authorize_code,
    }

response = requests.post(url, data=data)
tokens = response.json()
print(tokens)

# json 저장
import json
#1.
with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","w") as fp:
    json.dump(tokens, fp)

#2.
with open("kakao_code.json","w") as fp:
    json.dump(tokens, fp)
```

인증받은 키를 json형태로 저장하여 읽어오는 방식을 사용할 것입니다.

 

**★ 위 코드 마지막 with 부분에서 디렉토리 경로에 익숙하신 분은 #1. 에서 자신의 디렉토리 경로를 입력해주시고 디렉토리 경로가 익숙하지 않으신 분은 #2. 를 입력해 주세요.**

**반드시 #1. 과 #2. 둘 중 하나만 입력해 주세요!**

 

**3. rest_api_key 변수에 4번에서 저장한 자신의 REST API 키를 넣어주기 (하단 이미지 참고) & 실행**



![img](.\Images\kakao_img_07.png)사용자 토큰 받기



위 이미지와 같이 터미널 창에 에러 없이 access_token, token_type, refresh_token 등이 입력되면 성공입니다.

 

**4.** **저장한 json파일 읽어 오기**

```
# import requests

# url = 'https://kauth.kakao.com/oauth/token'
# rest_api_key = '자신의 REST API 키'
# redirect_uri = 'https://example.com/oauth'
# authorize_code = '-7sSp7Xxqw2QAew7-rctWJtZT4ycERTzQM_nylg8bX0OxFcgGegkA9jaVvPLEVOX_FrkxQo9dRoAAAF2qFCIsA'

# data = {
#     'grant_type':'authorization_code',
#     'client_id':rest_api_key,
#     'redirect_uri':redirect_uri,
#     'code': authorize_code,
#     }

# response = requests.post(url, data=data)
# tokens = response.json()
# print(tokens)

# # json 저장
# import json
# #1.
# with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","w") as fp:
#     json.dump(tokens, fp)

# #2.
# with open("kakao_code.json","w") as fp:
#     json.dump(tokens, fp)

# 위는 전부 주석 처리

# json 읽어오기
import json
#1.
with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","r") as fp:
    ts = json.load(fp)
print(ts)
print(ts["access_token"])

#2.
with open("kakao_code.json","r") as fp:
    ts = json.load(fp)
print(ts)
print(ts["access_token"])
```

w로 저장(write)했으니 r로 읽어(read) 보겠습니다.

2번에서 사용한 코드는 [Ctrl + A] + [Ctrl + /]로 전부 주석처리 후 import json 과 #1 또는 #2 를 입력 후 실행해 주세요.

 

**★ 2번에서 #1. 을 선택하신 분은 with open 코드에서 자신의 디렉토리 경로를 입력해 주시고**

**#2. 을 선택하신 분은 #2. 을 그대로 입력해 주세요.**

**여기도 반드시 #1. 과 #2. 중 하나만 입력해 주세요!**

 

**여기까지 정상적으로 수행되신다면 성공입니다!**

 

사용자 토큰 발급은 완료하였고 다음 장에 카카오톡 메시지를 보내보도록 하겠습니다.



### 2. 메시지 보내기

**사용자 토큰을 발급받지 않으신 분은 반드시 1편을 수행해주세요!**

[www.novice-engineers.tistory.com/9?category=908185](https://novice-engineers.tistory.com/9?category=908185)

[ [Python\] 10분 만에 카카오 오픈 API로 메시지 보내기 - (1. 사용자 토큰 발급)1. Kakao Developers 사이트 이동 www.developers.kakao.com/ Kakao Developers 카카오 API를 활용하여 다양한 어플리케이션을 개발해보세요. 카카오 로그인, 메시지 보내기, 친구 API, 인공지능 API 등을 제공합..novice-engineers.tistory.com](https://novice-engineers.tistory.com/9?category=908185)

## **1. 메시지 이동**



![img](.\Images\kakao_img_08.png)이전이전

012



**위 슬라이드 쇼를 참고해주세요.**

\1. 문서 클릭

\2. 스크롤을 조금 내린 후 [메시지 박스 안에 있는 REST API 클릭]

\3. [Ctrl + F]를 누른 후 Request를 입력

\4. URL이란 박스 안에 아래와 같은 코드를 확인하실 수 있습니다.

**POST/v2/api/talk/memo/default/send HTTP/1.1
Host: kapi.kakao.com
Authorization: Bearer {ACCESS_TOKEN}**

**=> https://kapi.kakao.com/v2/api/talk/memo/default/send 이러한 주소가 됩니다.**

 

## **2. Visual Studio Code에 코드 입력 & 실행**

```
import requests
import json

#1.
with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","r") as fp:
    tokens = json.load(fp)

#2.
with open("kakao_code.json","r") as fp:
    tokens = json.load(fp)

url="https://kapi.kakao.com/v2/api/talk/memo/default/send"

# kapi.kakao.com/v2/api/talk/memo/default/send 

headers={
    "Authorization" : "Bearer " + tokens["access_token"]
}

data={
    "template_object": json.dumps({
        "object_type":"text",
        "text":"Hello, world!",
        "link":{
            "web_url":"www.naver.com"
        }
    })
}

response = requests.post(url, headers=headers, data=data)
response.status_code
```

**이전 1강에서 사용한 with open에 따라 #1. 또는 #2. 중 하나만 선택하여 사용하시면 됩니다.**

 

**실행 후 자신의 카카오톡을 확인해주세요!**



![img](.\Images\kakao_img_09.png)



이렇게 메시지가 온 것을 확인하실 수 있습니다.

 

 

 

 

 

 

## **※ 간략한 코드 설명**

headers={
"Authorization" : "Bearer " + tokens["access_token"]
}
**# "Bearer"이라는 문자열과 json에서 불러온 access_token의 value를 조합하여 인증키를 만든다.**

 

data={
"template_object": json.dumps({
"object_type":"text",
"text":"Hello, world!",
"link":{
"web_url":"www.naver.com"
}
})
}

**# object_type은 text 고정값**
**# text에는 내용**
**# link는 필수사항이라서 그냥 네이버를 넣어줬습니다.**

**아래 링크를 참조하시면 이해하시는데 도움될 것 같습니다!**

**[www.developers.kakao.com/docs/latest/ko/message/message-template#text](https://developers.kakao.com/docs/latest/ko/message/message-template#text)**

[ Kakao Developers카카오 API를 활용하여 다양한 어플리케이션을 개발해보세요. 카카오 로그인, 메시지 보내기, 친구 API, 인공지능 API 등을 제공합니다.developers.kakao.com](https://developers.kakao.com/docs/latest/ko/message/message-template#text)

 

response = requests.post(url, headers=headers, data=data)
response.status_code

**# 위 2줄의 코드가 메시지를 보내는 과정입니다.**

 

## **※ 추가 (scop error)**

```
print(response.status_code)
if response.json().get('result_code') == 0:
	print('메시지를 성공적으로 보냈습니다.')
else:
	print('메시지를 성공적으로 보내지 못했습니다. 오류메시지 : ' + str(response.json()))
```

 

**위에서 scop error 나시는 분은 어플리케이션 생성 하실때 동의 항목에서 설정하시고 토큰 받으시면 됩니다.**

**공유해 주신 김대성님 정말 감사합니다! :)**

 

## **※ 추가 (403 error)**

**메시지를 성공적으로 보내지 못했습니다. 오류메시지 : {'msg': 'insufficient scopes.', 'code': -402, 'api_type': 'TALK_MEMO_DEFAULT_SEND', 'required_scopes': ['talk_message'], 'allowed_scopes': ['profile_nickname']}**

'3. 동의항목'을 참고로해도 동일 메시지를 받아서 하단 코드를

https://kauth.kakao.com/oauth/authorize?client_id={client_id}&redirect_uri=https://example.com/oauth&response_type=code

아래와 같이 수정하니 추가 동의하고 성공했습니다.

https://kauth.kakao.com/oauth/authorize?client_id={client_id}&redirect_uri=https://example.com/oauth&response_type=code&scope=profile_nickname,friends,talk_message

**공유해 주신 dokeun님 정말 감사합니다! :)**



---

### 3. 동의 항목

이전 2장에서 혹시라도 안되시는 분들을 위해 추가적으로 셋팅을 올리겠습니다.

## 1. 본인인증 확인



![img](.\Images\kakao_img_10.png)이전이전

01

본인 인증 확인



\1. 우측 상단에 자신의 아이디를 클릭

\2. 계정 설정 클릭

\3. 스크롤을 내린 후 본인인증이 되어 있는지 확인

 

## 2. 동의 항목



![img](.\Images\kakao_img_11.png)이전이전

01234



\1. 메인에서 내 애플리케이션 클릭

\2. 자신이 만든 app 클릭

\3. 좌측의 동의항목 클릭

\4. 프로필 정보 동의

\5. 스크롤 내린 후 카카오톡 메시지 전송 동의

 

**감사합니다.**



### [[Python\] 10분 만에 카카오톡 메시지 보내기 친구편 - (1. 설정 & 사용자 토큰 발급)](https://novice-engineers.tistory.com/13)

지금부터는 파이썬을 활용한 친구에게 카카오톡 메시지 보내기를 해보겠습니다.

 

**이해를 위해 메시지 보내기 1편을 꼭 해보시고 진행해 주세요!**

[www.novice-engineers.tistory.com/9?category=908185](https://novice-engineers.tistory.com/9?category=908185)

[ [Python\] 10분 만에 카카오톡 메시지 보내기 - (1. 사용자 토큰 발급)1. Kakao Developers 사이트 이동 www.developers.kakao.com/ Kakao Developers 카카오 API를 활용하여 다양한 어플리케이션을 개발해보세요. 카카오 로그인, 메시지 보내기, 친구 API, 인공지능 API 등을 제공합..novice-engineers.tistory.com](https://novice-engineers.tistory.com/9?category=908185)

**친구에게 카카오톡을 보내기 위해서는 조금 불편한 절차가 필요합니다.**

**우선 kakao developers에 가입된 친구(계정)가 있어야 합니다.**

**저 같은 경우는 부계정을 생성하여 테스트를 진행했습니다.**

**집에 공기계(안 쓰시는) 핸드폰이 있으시다면 카카오톡 부계정을 생성하실 수 있습니다.**

**=> 궁금하신 분은 댓글에 남겨주세요! 알려드리겠습니다.**

**부계정이나 kakao developers에 가입된 친구(계정)가 있다고 생각하고 진행하겠습니다.**

## **1. 애플리케이션 생성**



![img](.\Images\kakao_img_12.png)이전이전

012



**위 슬라이드 쇼를 참고해 주세요.**

\1. 로그인 후 상단에서 내 애플리케이션으로 이동해 주세요.

\2. 애플리케이션 추가하기 클릭.

\3. 앱 이름, 사업자명을 입력 후 저장을 눌러주세요.

\4. 생성된 애플리케이션을 클릭해 주세요.

 

## **2. 앱 키 저장! - ★**



![img](.\Images\kakao_img_13.png)앱 키



**REST API 키**를 메모장에 복사해 주세요. 중요합니다!!!

 

## **3. 활성화 설정**



![img](.\Images\kakao_img_14.png)이전이전

012



**위 슬라이드 쇼를 참고해 주세요.**

\1. 좌측 메뉴에서 카카오 로그인 클릭

\2. 활성화 설정에서 OFF를 클릭하여 ON으로 설정

\3. 스크롤 내린 후 Redirect URI를 클릭

\4. 위 슬라이드 쇼 3번째 URl를 그대로 입력 **( https://example.com/oauth ->그대로 복사 붙여 넣기!)**

**[나중에 바꿔도 상관없으나 처음 테스트는 반드시 일치시켜주세요!]**

 

## **4. 팀 관리**



![img](.\Images\kakao_img_15.png)이전이전

01



**위 슬라이드 쇼를 참고해 주세요.**

\1. 좌측 메뉴에서 팀 관리 클릭

\2. 팀원 초대 클릭

\3. 부계정 또는 kakao developers에 가입된 친구(계정)의 카카오 계정(이메일) 입력

\4. EDITOR 클릭

\5. 초대 클릭

 

## **5.동의항목**



![img](.\Images\kakao_img_16.png)이전이전

01

동의항목



**위 슬라이드 쇼를 참고해 주세요.**

\1. 카카오 로그인 -> 동의항목 클릭

\2. 프로필 정보, 카카오 서비스내 친구 목록 2개를 설정 클릭 후 동의 하기

\3. 스크롤 내린 후 카카오톡 메시지 전송 설정 클릭 후 동의 하기

**[ 친구 목록을 받아오기 위해서 중요한 설정입니다.]**

 

## **6. 인증 코드 받기**

https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code&scope=talk_message,friends

**이전 1편에서는 다르게 &scope=talk_message,friends를 code뒤에 붙여 주셔야 됩니다!**

**이 부분이 없으면 친구 목록을 받아오지 못합니다. 이 코드 때문에 2시간을 검색했습니다 ㅠㅠ...**

이후에는 1편과 동일한 방식으로 진행하시고 코드를 저장해 주세요.

 

## **7. 사용자 토큰 받아오기**

```
import requests

url = 'https://kauth.kakao.com/oauth/token'
rest_api_key = '자신의 API 키'
redirect_uri = 'https://example.com/oauth'
authorize_code = '받아온 authorize_code'

data = {
    'grant_type':'authorization_code',
    'client_id':rest_api_key,
    'redirect_uri':redirect_uri,
    'code': authorize_code,
    }

response = requests.post(url, data=data)
tokens = response.json()
print(tokens)

# json 저장
import json
with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","w") as fp:
    json.dump(tokens, fp)
```

코드는 1편과 동일하고 authorize_code 변수만 6번에서 받아온 코드로 바꿔주세요!

 

## **8. scope 정보**



![img](.\Images\kakao_img_17.png)scope 정보



**실행 후 콘솔에서 scope에 friends가 있는지 반드시 확인해 주세요!!!**

**여기까지 실행하셔서 토큰을 받아 오시면 성공입니다!**



### [[Python\] 10분 만에 카카오톡 메시지 보내기 친구편 - (2. 메시지 보내기)](https://novice-engineers.tistory.com/14)

**사용자 토큰을 발급받지 않으신 분은 반드시 친구 1편을 수행해주세요!**

[www.novice-engineers.tistory.com/13](https://novice-engineers.tistory.com/13)

[ [Python\] 10분 만에 카카오톡 메시지 보내기 친구편 - (1. 설정 & 사용자 토큰 발급)지금부터는 파이썬을 활용한 친구에게 카카오톡 메시지 보내기를 해보겠습니다. 이해를 위해 메시지 보내기 1편을 꼭 해보시고 진행해 주세요! www.novice-engineers.tistory.com/9?category=908185 [Python] 10분novice-engineers.tistory.com](https://novice-engineers.tistory.com/13)

## **1. 카카오톡 소셜 REST API 확인**



![img](.\Images\kakao_img_18.png)이전이전

0123



**위 슬라이드 쇼를 참고해 주세요.**

\1. 상단 메뉴에서 문서 클릭

\2. [Ctrl + F] 누른 후 '카카오톡 소셜' 입력

\3. [카카오톡 소셜 박스 안에 있는 REST API 클릭]

\4. 왼쪽 메뉴바에서 '친구 목록 가져오기' 클릭

\5. 스크롤 밑으로 내린 후 URL 확인

**GET /v1/api/talk/friends HTTP/1.1
Host: kapi.kakao.com
Authorization: Bearer {ACCESS_TOKEN}**

**=> https://kapi.kakao.com/v1/api/talk/friends 이러한 주소가 됩니다.** 

 

## **2. 친구 목록 가져오기**

```
import requests
import json

#1.
with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","r") as fp:
    tokens = json.load(fp)
#2.
with open("kakao_code.json","r") as fp:
    tokens = json.load(fp)

# print(tokens)
# print(tokens["access_token"])

friend_url = "https://kapi.kakao.com/v1/api/talk/friends"

# GET /v1/api/talk/friends HTTP/1.1
# Host: kapi.kakao.com
# Authorization: Bearer {ACCESS_TOKEN}

headers={"Authorization" : "Bearer " + tokens["access_token"]}

result = json.loads(requests.get(friend_url, headers=headers).text)

print(type(result))
print("=============================================")
print(result)
print("=============================================")
friends_list = result.get("elements")
print(friends_list)
# print(type(friends_list))
print("=============================================")
print(friends_list[0].get("uuid"))
friend_id = friends_list[0].get("uuid")
print(friend_id)
```

**위 코드를 그대로 Visual Studio Code에 입력해 주세요.**

**디렉토리 여부에 따라 #1. 또는 #2. 중 하나만 선택하여 사용하시면 됩니다.**

**# 간단한 코드 설명**

1번에서 얻은 url을 friend_url에 저장합니다.

headers에 인증 키를 Bearer {ACCESS_TOKEN} 형태로 저장합니다.

응답은 JSON 객체로 받기 때문에 json.loads로 result에 저장합니다. (result는 dictionary 타입입니다.)

print는 값들이 정상적으로 출력되는지 확인하기 위한 코드입니다.

result는 dictionary 형태이기 때문에 필요한 정보가 담긴 elements의 value들을 friends_list에 저장합니다.

이후 메시지에 보낼 때 필요한 값은 uuid라는 값이기 때문에 friend_id에 friends_list[0] 번째에 있는 uuid를 저장합니다.

**제가 테스트하는 과정에서는 친구를 1명만 넣기 때문에 friends_list[0]으로 표기했는데 여러 명의 친구에게 테스트하실 때는 for문으로 실행하시면 됩니다.**

**# 실행 결과**



![img](.\Images\kakao_img_19.png)출력결과



## **3. 친구에게 메시지 보내기 REST API 확인**



![img](.\Images\kakao_img_20.png)이전이전

0123

이동 경로



**위 슬라이드 쇼를 참고해 주세요.**

\1. 상단 메뉴에서 문서 클릭

\2. [Ctrl + F] 누른 후 '메시지' 입력

\3. [메시지 박스 안에 있는 REST API 클릭]

\4. 왼쪽 메뉴바에서 '친구에게 기본 메시지 보내기' 클릭

\5. URL 확인

**POST /v1/api/talk/friends/message/default/send HTTP/1.1
Host: kapi.kakao.com
Authorization: Bearer {ACCESS_TOKEN}**

**=>https://kapi.kakao.com/v1/api/talk/friends/message/default/send 이러한 주소가 됩니다.**

 

## **4. 친구에게 메시지 보내기**

```
import requests
import json

with open(r"C:\Users\user\Desktop\PythonWorkspace\kakao_test\kakao_code.json","r") as fp:
    tokens = json.load(fp)
# print(tokens)
# print(tokens["access_token"])

friend_url = "https://kapi.kakao.com/v1/api/talk/friends"

# GET /v1/api/talk/friends HTTP/1.1
# Host: kapi.kakao.com
# Authorization: Bearer {ACCESS_TOKEN}

headers={"Authorization" : "Bearer " + tokens["access_token"]}

result = json.loads(requests.get(friend_url, headers=headers).text)

print(type(result))
print("=============================================")
print(result)
print("=============================================")
friends_list = result.get("elements")
print(friends_list)
# print(type(friends_list))
print("=============================================")
print(friends_list[0].get("uuid"))
friend_id = friends_list[0].get("uuid")
print(friend_id)

send_url= "https://kapi.kakao.com/v1/api/talk/friends/message/default/send"

data={
    'receiver_uuids': '["{}"]'.format(friend_id),
    "template_object": json.dumps({
        "object_type":"text",
        "text":"성공입니다!",
        "link":{
            "web_url":"www.daum.net",
            "web_url":"www.naver.com"
        },
        "button_title": "바로 확인"
    })
}

response = requests.post(send_url, headers=headers, data=data)
response.status_code
```

위 코드를 전체 복사 후 붙여 넣기 해주세요.

**# 간단한 코드 설명**

3번에서 얻은 url을 send_url에 저장합니다.

data에 REST API에서 제공하는 텍스트 형태로 저장합니다.

이후 메시지를 보내는 과정입니다.

**# 결과**



![img](.\Images\kakao_img_21.png)



***\*메시지를 확인해 보시면 카카오톡 메시지 보내기 친구편에 성공하셨습니다!\****

***\*3번에서 스크롤을 밑으로 조금더 내려보시면 Sample이라고 나오는데 피드, 리스트, 위치, 커머스, 텍스트의 형태를 확인 가능합니다.\****

***\*위 코드에서 data형태를 바꾼다면 텍스트 뿐만 아니라 다양한 메시지를 보내실 수 있습니다!\****

 

***\*감사합니다.\****



### 얼굴 인식편 도 있음.

