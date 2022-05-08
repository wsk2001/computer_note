# Python to LINE Notify

출처: https://kwonkyo.tistory.com/520?category=781723



파이썬으로 모니터링을 돌리고 있을 때 그 결과를 스마트폰으로 간편하게 받을 때 유용한 방법입니다. 라인 메신저에서 제공하는 "LINE Notify"를 이용하면 간단히 해결할 수 있습니다.



![img](.\Images\line_noti_img_01.png)



## LINE 설치, 가입

먼저 LINE Notify를 이용하기 위해서 라인 서비스를 사용하고 있어야 하는데요. 저도 기본 메신저는 카카오톡을 사용하기 때문에 추가로 설치를 했는데, 고맙게도 라인에서는 저처럼 부계정으로 사용하기에 적합한 라이트 버전을 제공하고 있습니다.



![img](.\Images\line_noti_img_02.png)



라인 라이트 버전을 설치하고 나서 간단히 가입절차 진행합니다.



<img src=".\Images\line_noti_img_03.png" alt="img" style="zoom:33%;" /><img src=".\Images\line_noti_img_04.png" alt="img" style="zoom:33%;" /><img src=".\Images\line_noti_img_05.png" alt="img" style="zoom: 33%;" />



라인 앱에서 설정 >> 계정 메뉴로 이동해서 메일, 비밀번호를 입력하고 중간에 있는 로그인 허용 옵션을 활성화해 줍니다. 이렇게 하면 앱에서 할 일은 끝났습니다.



![img](.\Images\line_noti_img_06.png)



## LINE Notify 토큰 발급

LINE Notify 홈페이지로 가서 메시지 전송 API에 필요한 토큰을 발급받아야 합니다.

[ LINE NotifyLINE Notify allows you to send web notifications from GitHub, IFTTT, Mackerel, and more directly to your LINE chats.notify-bot.line.me](https://notify-bot.line.me/en/)

 

위 링크로 가서 앱에서 입력한 계정 정보로 로그인을 합니다. 그리고 "내 정보" 페이지로 이동해서 토큰 만들기를 눌러줍니다.



![img](.\Images\line_noti_img_07.png)



생성하고자 하는 토큰의 이름을 입력하고 "1-on-1 chat with LINE Notify"를 선택하고 "Generate token"을 눌러줍니다.



![img](.\Images\line_noti_img_08.png)



토큰은 즉시 발행됩니다. 발생된 토큰은 후에 다시 확인할 방법은 없기 때문에 바로 복사해서 메모장에 붙여둡니다.



![img](.\Images\line_noti_img_09.png)



## 파이썬에서 LINE으로 메시지 보내기

이제 파이썬에서 라인으로 메시지를 보내는 방법입니다. 메시지를 보내는 방법은 파이썬에서 "requests"라이브러리를 이용해서 라인 서버에 메시지를 보내기만 하면 됩니다. 

[ LINE NotifyLINE Notify allows you to send web notifications from GitHub, IFTTT, Mackerel, and more directly to your LINE chats.notify-bot.line.me](https://notify-bot.line.me/doc/en/)

POST와 GET 방식을 모두 이용하는데 대부분 POST 방식을 쓰시기에 저도 POST 방식으로 테스트해 봤습니다.

``` python
import requests

try:
	TARGET_URL = 'https://notify-api.line.me/api/notify'
	TOKEN = 'WA63BP..................WqnYf8BRUKm'		# 발급받은 토큰
	headers={'Authorization': 'Bearer ' + TOKEN}
	data={'message': '파이썬에서 메시지를 보냅니다. 1 2 3!!'}

	response = requests.post(TARGET_URL, headers=headers, data=data)

except Exception as ex:
	print(ex)
```



이렇게 메시지를 보내면 바로 LINE앱에서 아래와 같이 메시지를 받을 수 있습니다.



<img src=".\Images\line_noti_img_10.png" alt="img" style="zoom: 50%;" />



간단하고 아주 좋습니다. 

