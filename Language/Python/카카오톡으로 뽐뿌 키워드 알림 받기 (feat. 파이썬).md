# **카카오톡으로 뽐뿌 키워드 알림 받기 (feat. 파이썬)**

출처: https://liwonfather.tistory.com/5

## 카카오톡으로 뽐뿌 키워드 알림 받기

 

------

------



![img](.\Images\img_2023-01-25_05-001.png)



이번에 이전 포스팅의 연장선으로 커뮤니티 중 하나인 뽐뿌를 가지고 프로그램을 만들었다.

뽐뿌는 핫딜 정보가 가득찬 커뮤니티로 저렴한 쇼핑을 위해 필자도 자주 애용하고 있다.

본론으로 돌아가 프로그램의 목표는 자신이 설정한 키워드가 새로운 게시글로 등록이 되면

자동으로 해당 쇼핑 정보를 카카오톡으로 보내주는것이다.

 

먼저 해당 프로그램을 이해도롤 높이고자 한다면 필자의 이전 포스팅들을 참조하길 바란다.

 

[2022.03.02 - [Python\] - [리원아빠] 파이썬으로 카카오톡 메시지 나에게 보내기](https://liwonfather.tistory.com/2)

[ [리원아빠\] 파이썬으로 카카오톡 메시지 나에게 보내기카카오톡 메시지 나에게 보내기 파이썬을 통해 키워드 챗봇을 만들었다. 텔레그램이 아닌 카카오톡으로 만들다보니 카카오 인증이 필요했다. 인증은 json으로 처리했는데 처음해봐서 구글링이liwonfather.tistory.com](https://liwonfather.tistory.com/2)

[2022.03.03 - [Python\] - [리원아빠] 카카오톡 자동 토큰 발행 후 나에게 메시지 보내기](https://liwonfather.tistory.com/3)

[ [리원아빠\] 카카오톡 자동 토큰 발행 후 나에게 메시지 보내기카카오톡 자동 토큰 발행 및 메시지 보내기 이전 포스팅에 카카오톡을 통해 자신에게 메시지를 보내는 방법에 대해 알아봤다. 2022.03.02 - [IT/Python] - [리원아빠] 파이썬으로 카카오톡 메시지 나에liwonfather.tistory.com](https://liwonfather.tistory.com/3)

[2022.03.04 - [Python\] - [리원아빠] 파이썬으로 새로운 게시글 등록시 카카오톡 메시지 보내기](https://liwonfather.tistory.com/4)

[ [리원아빠\] 파이썬으로 새로운 게시글 등록시 카카오톡 메시지 보내기새로운 게시글 카카오톡 메시지 보내기 이 포스팅에 대한 이해도를 높이기 위해서 아래의 포스팅을 먼저 보고 오는것을 추천한다. 2022.03.02 - [IT/Python] - [리원아빠] 파이썬으로 카카오톡 메시지liwonfather.tistory.com](https://liwonfather.tistory.com/4)

 

가장 최신 포스팅인 fm korea 핫딜 정보를 보내주는 프로그램과 많이 유사하며

프로그램 구조를 간략하게 설명하면 아래와 같다.

 

- 자신이 설정한 키워드를 포함한 게시글들의 url 추출
- 해당 게시글들을 크롤링
- 크롤링 결과를 이전 크롤링 결과와 비교
- 비교 후 신규 게시글 리스트만 카카오톡으로 전송

가장 먼저 뽐뿌 사이트에 키워드를 포함한 조건을 추가하여 검색을 한다.

그 후, 검색 결과의 작성시간, 제목, 링크를 크롤링한다.

 

이 후는 fm korea 핫딜 정보 프로그램과 동일하다.

카카오 인증을 하고 새로운 게시글들만 카카오톡으로 전송해준다.

마찬가지로 최초 실행시에는 비교 대상이 없기에

최대 20개까지의 이전 게시글이 카카오톡으로 전송된다.

 

카카오톡 인증을 하는 소스부터 살펴보자.

```py
#kakao.py

import time
import requests
import json

# code url  https://kauth.kakao.com/oauth/authorize?client_id=자신의 rest api key 값&redirect_uri=https://example.com/oauth&response_type=code
url = 'https://kauth.kakao.com/oauth/token'  # 카톡 인증 url
rest_api_key = '자신의 rest api key 값'  # 카톡 rest 키
redirect_uri = 'https://example.com/oauth'  # 카톡 인증용 redirect url
authorize_code = 'code url을 통해 얻은 code 값'  # 카톡 인증용 코드 수행시마다 재발급 필요


# 최초 token들 발급하여 refresh token 저장
def f_get_refresh_token():
    data = {
        'grant_type': 'authorization_code',
        'client_id': rest_api_key,
        'redirect_uri': redirect_uri,
        'code': authorize_code,
    }

    response = requests.post(url, data=data)
    tokens = response.json()

    with open('refresh_token.json', 'w') as fd:
        json.dump(tokens, fd)


# refresh token을 이용하여 새로운 access token 발급
def f_reissue_token():
    with open('refresh_token.json', 'r') as fd:
        token = json.load(fd)
    refresh_token = token['refresh_token']
    data = {
        'grant_type': 'refresh_token',
        'client_id': rest_api_key,
        'refresh_token': refresh_token
    }
    response = requests.post(url, data=data)
    tokens = response.json()

    with open('access_token.json', 'w') as fd:
        json.dump(tokens, fd)
    with open('access_token.json', 'r') as fd:
        ts = json.load(fd)
    access_token = ts['access_token']
    return access_token


# 메시지 전송
def f_send_msg(access_token, msg):
    header = {'Authorization': 'Bearer ' + access_token}
    url = 'https://kapi.kakao.com/v2/api/talk/memo/default/send'  # 나에게 보내기 주소
    post = {
        'object_type': 'text',
        'text': msg,
        'link': {
            'web_url': 'https://developers.kakao.com',
            'mobile_web_url': 'https://developers.kakao.com'
        },
        'button_title': '키워드'
    }
    data = {'template_object': json.dumps(post)}
    return requests.post(url, headers=header, data=data)
```

 

카카오 인증하는 코드는 이미 모듈화를 해놨기에 이전 포스팅 소스와 동일하다.

뽐뿌 사이트를 크롤링하는 코드는 ppom.py로 kakao.py를 import 해서 사용해야 한다.

```py
#ppom.py

import requests
import os
import json
import time
import copy
from bs4 import BeautifulSoup
from datetime import datetime

import kakao

# 변수 선언
site_url = 'https://m.ppomppu.co.kr/new/'  # 사이트 URL
board_list = []
p_board_list = []
keyword ='막걸리'


# 사이트에 파라미터로 넘길 조건들, 키워드 추가시 +뒤에 키워드 추가
params = {
    'search_type': 'subject',
    'keyword': keyword
}

# 조건 추가하여 사이트 오픈
result_search = requests.get('https://m.ppomppu.co.kr/new/bbs_list.php?id=ppomppu&category=', params=params)
# print(result_search.url)

# 사이트 리스트 가져오는 함수
def f_get_list():
    if result_search.status_code == 200:
        html = result_search.text
        soup = BeautifulSoup(html, 'html.parser')
        times = soup.select('#wrap > div.ct > div.bbs > ul > li > a > div.thmb_N2 > ul > li.exp > time')
        titles = soup.select('#wrap > div.ct > div.bbs > ul > li > a > div.thmb_N2 > ul > li.title > span.cont')
        links = soup.select('a.list_b_01n')

        for i in range(0, len(titles), 1):
            board_list.append('작성시간: ' +times[i].text +'\n제목: ' +titles[i].text.strip() +'\n링크: ' +site_url+links[i]['href'])
    else:
        print(result_search.status_code)

while True:
    f_get_list()  # 게시글 크롤링
    access_token = kakao.f_reissue_token()  # 새로운 액세스 토큰을 발급 받음
    sms_list = list(set(board_list) - set(p_board_list))  # 이전 리스트와 비교하여 다른 값만 문자 보낼 리스트로 저장
    p_board_list = copy.deepcopy(board_list)  # 현재 게시글을 이전 게시글로 저장

     authorization_kakao.f_send_msg(access_token, '뽐뿌 결과\n'  +'키워드 : '+keyword + '\n현재 시간 {} 최신글은 총 {}개입니다.'.format(datetime.now().strftime('%H:%M:%S'),len(sms_list)))

    for i in range(0, len(sms_list), 1):
        kakao.f_send_msg(access_token, sms_list[i])

    board_list.clear()
    sms_list.clear()
    time.sleep(1800)  # 반복 주기
```

필자는 막걸리를 좋아하기에 알림 받을 키워드로 막걸리를 지정했다.

만약 여러가지 키워드를 설정하고자 한다면 아래처럼 하나의 키워드 다음에 +를 붙여주면 된다.

ex)keyword ='막걸리 +탄산수' 

 

그럼 끝.