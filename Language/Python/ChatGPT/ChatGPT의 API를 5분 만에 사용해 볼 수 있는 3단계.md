# ChatGPT의 API를 5분 만에 사용해 볼 수 있는 3단계

출처: https://qiita.com/JinA293/items/ab953c40ce73d161f4fc?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=dc32590542-Qiita_newsletter_557_03_15_2023&utm_medium=email&utm_term=0_e44feaa081-dc32590542-34467781

1. **OpenAI 계정 만들기 🍀**
2. **API 키 가져오기🔑**
3. **프로그램을 작성하고 실행합니다 💻**



## 1. OpenAI 계정 만들기 🍀

OpenAI 계정을 만드는 단계는 다음과 같습니다.

1. **[OpenAI 홈페이지](https://openai.com/product) 로 이동하여 중간에 있는 "Get Started"를 클릭합니다.**

   [![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2F1b05b2d7-b916-0953-8e11-2ab20e6959f4.png)](https://camo.qiitausercontent.com/9e9b4b06b852c49c4fd372b5639ff93e350168f4/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f31623035623264372d623931362d303935332d386531312d3261623230653639353966342e706e67)

2. **계정 등록 양식이 표시되므로 필요한 정보 (이메일 주소, 비밀번호)를 입력합니다.**
   ※Google이나 Microsoft의 인증도 가능.
   [![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2F05a4a9f7-a53f-7707-2fac-3c6a1678cfcb.png)](https://camo.qiitausercontent.com/43f314f23b43a81685ac075aaba695f3bcf4e0c3/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f30356134613966372d613533662d373730372d326661632d3363366131363738636663622e706e67)

3. **OpenAI에서 보낸 확인 이메일에 따라 유효한 이메일 주소인지 확인합니다.**
   ※Google이나 Microsoft의 인증을 하고 있는 사람 이외.

4. **이름, 전화 번호를 입력합니다.**

5. **SMS로 온 인증 코드에서 유효한 전화 번호인지 확인합니다.**

6. **자신에게 맞는 OpenAI의 사용 용도를 선택. 이번처럼 놀면 "I'm exploring personal use"가 적절하다.**

**세세한 설명은 이쪽의 기사를 추천! 👇**



## 2. API 키 가져오기🔑

계정을 만들면 API를 사용하기 위해 API 키를 가져옵니다.
API 키를 얻는 단계는 다음과 같습니다.

1. **방금전과 같이 [OpenAI의 홈페이지](https://openai.com/product) 에 액세스해, 중간의 「Get Started」를 클릭.**
2. **방금 만든 계정으로 로그인.**
3. **이 화면에 오면 오른쪽 상단의 Personal → View API keys를 클릭하십시오.**
   [![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2Fabc3d4af-f0b0-a0e7-b347-4098ad01a991.png)](https://camo.qiitausercontent.com/5a2c9b58b6fd3a0933cb18c89276d7c7af76dac8/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f61626333643461662d663062302d613065372d623334372d3430393861643031613939312e706e67)
4. **이 화면에 오면 중간에 "Create new secret key"를 클릭하십시오.**
   [![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2F1edaf66b-45ac-596f-71b9-2ed9b71c210f.png)](https://camo.qiitausercontent.com/4409f5b14663e389bc5816c8f523eeb7eb67d1da/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f31656461663636622d343561632d353936662d373162392d3265643962373163323130662e706e67)
5. **그러면 이렇게 API 키가 발행되므로 복사하여 안전한 장소에 저장합시다. (누설하면 위험)**
   [![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2F0bf27f93-193c-a569-2a5c-77396562e1ee.png)](https://camo.qiitausercontent.com/e2d73839a1cb0ffc031fbfc81462716353a1585c/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f30626632376639332d313933632d613536392d326135632d3737333936353632653165652e706e67)

## 3. 프로그램을 써서 실행한다💻

조속히 처리를 써 갑시다-!
프로그램을 작성하는 순서는 다음과 같다.

1. **에디터와 파일(이번에는 main.py이라고 함) 준비**
2. **터미널에서 아래 명령을 실행하고 openAI 라이브러리 설치**

```
% pip install openai
```

3. **프로그램을 작성한다(후술 0이 붙어 있는 것은 [아래에서 설명하고 있습니다](https://qiita.com/JinA293/items/ab953c40ce73d161f4fc#くわしく説明) )**

main.py

```py
import openai # openai api를 활용하는 데 필요

openai.api_key = "XXXXXXXXXXXXXXXXXXXXXXXXX" # 발행된 apikey를 xxx로 바꾸기

res = openai.ChatCompletion.create( # res에 api 응답이 저장됨
   model="gpt-3.5-turbo", # ChatGPT 모델 선택하기 후술 ➀
   messages=[
       {
           "role": "user", # role를 system, user, assistant의 3종류 중에서 선택 하기
           "content": "ChatGPT란 무엇입니까?" # 듣고 싶은 질문이나 하고 싶은 지시를 입력
       },
   ],
)

print(res) # 응답(res) 출력
print(res["choices"][0]["message"]["content"]) # 응답(res) 중에서 content만 지정하여 출력
```

4. **실행 결과의 확인(알기 쉽도록 일부 실제로는 출력되지 않는 주석을 붙이고 있습니다)**

```bash
% python main.py
---------------------------------------↓print(res)の出力結果-------------------------------------------
{
  "choices": [
    {
      "finish_reason": "stop",
      "index": 0,
      "message": {
        "content": "\n\nChatGPT\u3068\u306f\u3001\u30a4\u30f3\u30bf\u30fc\u30cd\u30c3\u30c8\u4e0a\u306e\u30c1\u30e3\u30c3\u30c8\u30dc\u30c3\u30c8\u306e\u3072\u3068\u3064\u3067\u3042\u308a\u3001\u4eba\u5de5\u77e5\u80fd\u306b\u57fa\u3065\u3044\u305f\u5bfe\u8a71\u30b7\u30b9\u30c6\u30e0\u3067\u3001\u8a00\u8a9e\u306e\u7406\u89e3\u3068\u751f\u6210\u3092\u884c\u3046\u3053\u3068\u304c\u3067\u304d\u307e\u3059\u3002ChatGPT\u306f\u3001OpenAI\u306e\u8a00\u8a9e\u30e2\u30c7\u30ebGPT-2\u3092\u57fa\u306b\u3057\u305f\u3082\u306e\u3067\u3001\u591a\u69d8\u306a\u30c8\u30d4\u30c3\u30af\u306b\u95a2\u3059\u308b\u8cea\u554f\u3084\u4f1a\u8a71\u3092\u884c\u3046\u3053\u3068\u304c\u3067\u304d\u308b\u70b9\u304c\u7279\u5fb4\u3067\u3059\u3002\u307e\u305f\u3001\u4eba\u9593\u3089\u3057\u3044\u81ea\u7136\u306a\u4f1a\u8a71\u3092\u884c\u3048\u308b\u3053\u3068\u304c\u7279\u5fb4\u3067\u3001\u81ea\u5206\u81ea\u8eab\u3092\u6539\u5584\u3057\u7d9a\u3051\u308b\u3053\u3068\u3067\u3001\u3088\u308a\u9ad8\u5ea6\u306a\u81ea\u7136\u306a\u5bfe\u8a71\u3092\u884c\u3046\u3053\u3068\u304c\u3067\u304d\u307e\u3059\u3002",
        "role": "assistant"
      }
    }
  ],
  "created": 1677967405,
  "id": "chatcmpl-6qUGL2bmlM8okZNprfmkbpm3cf5y1",
  "model": "gpt-3.5-turbo-0301",
  "object": "chat.completion",
  "usage": {
    "completion_tokens": 188, ← chatgpt가 반환한 텍스트의 토큰 수
    "prompt_tokens": 17, ← user(자신)가 요청한 텍스트의 토큰 수
    "total_tokens": 205 ← 이번 교환의 총 토큰수 후술
  }
}

---------------------↓print(res["choices"][0]["message"]["content"])출력 결과--------------------------

ChatGPT는 인터넷상의 채팅 봇 중 하나이며 인공 지능을 기반으로 한 대화 시스템에서 언어를 이해하고 생성합니다.
수 있습니다. ChatGPT는 OpenAI의 언어 모델 GPT-2를 기반으로하며 다양한 주제에 대한 질문과 대화를 할 수 있다는 점
특징입니다. 또, 인간다운 자연스러운 대화를 할 수 있는 것이 특징으로, 스스로를 계속 개선하는 것으로, 보다 고도의 자연스러운 대화를 실시하는 것이
수 있습니다.
```

**자, 여기에서 처음부터 ChatGPT의 API를 실행해 볼 수 있었기 때문에, 한 번 끝입니다, 수고하셨습니다! :: ![:그 다음에:](https://cdn.qiita.com/emoji/twemoji/unicode/1f389.png)_**

## 자세히 설명🔍

### ➀model이란?

ChatGPT의 API는 "gpt-3.5-turbo"라는 모델을 채용하고 있으며, 그 중에서는 "gpt-3.5-turbo"와 "gpt-3.5-turbo-0301"(3월 1일 시점까지의 성능을 갖춘 두 가지 모델이 제공됩니다. (GPT-3.5 자체는 다른 모델도 있습니다.)

※3월 15일에 공개된 GPT-4를 사용하고 싶은 경우는, 웨이트 리스트에 등록할 필요가 있는 것 같습니다.
**자세하게 알기 쉽게 정리되어 있는 기사는 이쪽! 👇**



### ➁ role이란?

요청시 role를 나누어 사용하여 ChatGPT에 지시를 줍니다.

- **system** : ChatGPT 설정 및 상태 지정
- **assistant** : 사전에 ChatGPT가 말한 텍스트를 입력하십시오.
- **user** : 사용자의 질문 및 지시를 나타내는 텍스트 입력

이와 같이 구분할 수 있고, 실제로 전부 사용해 보면 다음과 같이 됩니다.

main.py

```py
import openai

openai.api_key = "XXXXXXXXXXXXXXXXXXXXXXXXX"

res = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {
            "role": "system", # chatgpt 설정 및 상태 지정
            "content": "일본어로 대답해 주세요." # 일본어로 응답하도록 설정·상황화
        },
        {
            "role": "user", # 사용자의 질문 및 지시를 나타내는 텍스트 입력
            "content": "ChatGPT란 무엇입니까?" # 질문・지시
        },
        {
            "role": "assistant", # 이전에 chatgpt가 말한 텍스트를 입력하십시오.
            "content": "chatgpt는 최근 일본에서 유행하는 초밥의 이름입니다." # 사전에 ChatGPT가 발화 한 발화 한 내용
        },
                {
            "role": "user", # 사용자의 질문 및 지시를 나타내는 텍스트 입력
            "content": "그 이야기 진짜? 진짜 말해! 그 다음에 일본에서 유행하고 있는 초밥도 가르쳐.。" # 질문・지시
        },
    ],
)

print(res["choices"][0]["message"]["content"])
```

실행 결과

```
미안해, 내가 말한 것은 농담이었다. ChatGPT는 인공 지능의 채팅봇으로 자연어를 다룰 수 있습니다. 일반적으로 전문적
질문이나 말하기를 원할 때 사용됩니다.

일본에서 유행하는 초밥의 예는 다음과 같습니다.
- 스시 장인이 손으로 감는 '손으로 감는 스시'
- 외국인에게 인기있는 '회전 초밥'
- 해외에서 유행하는 '스시버거'
- 인스타 빛나는 '말차 스시'
- 츠키지에서 먹는 '본격적인 에도마에 스시'
등이 있습니다.
```

이 방법으로 각 역할을 구분할 수 있습니다.

assistant의 이해가 조금 어려울지도 모릅니다만, 「GPT군, 아까는 이렇게 말했어?」라고 하는 것을 미리 설정할 수 있는 것이며, ChatGPT와 대화의 랠리를 계속하기 위해서는 필수입니다. print(res)에서 content 아래에 role로 assistant가 붙어 있는 이유도, content가 ChatGPT의 회신이기 때문이군요.

### ➂ 토큰이란?

ChatGPT에서 송수신한 텍스트를 단어나 문자로 분할하여 포인트로 변환한 것과 같습니다. 영어는 1단어 1토큰이거나 합니다만, 일본어는 1문자 1~3토큰이거나 합니다. 그리고 그 토큰을 바탕으로 요금이 발생합니다.

**토큰이나 요금의 계산 방법에 대해 자세하게 알기 쉽게 정리하고 있는 기사는 이쪽! 👇**



### ④무료 프레임에 대해서

청구 관련 확인은 [OpenAI의 Biilling overview](https://platform.openai.com/account/billing/overview) 페이지에서 확인할 수 있으며, 그 페이지에서 [Organization의 Usage](https://platform.openai.com/account/usage) 페이지로 날아가면 무료 테두리를 확인할 수 있습니다.

중단의 **Free trial usage** 를 확인하면 $18(약 66,700토큰, 즉 최대 약 66,700영어의 송수신이 가능)의 무료 테두리가 존재하는 것을 알 수 있습니다. 부담없이 만져도 요금의 걱정이 없는 것은 고맙다.
[![이미지.png](D:\GitHub\computer_note\Language\Python\ChatGPT\images\2Fb14c3b97-0e44-45af-efce-229a14ef9c88.png)](https://camo.qiitausercontent.com/53dfbab22f80c1d9e4c0d2529ca72f77a4252523/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323831343936392f62313463336239372d306534342d343561662d656663652d3232396131346566396338382e706e67)

## 요약

이상으로 **5분에 0부터 ChatGPT의 API를 실행해 볼** 때까지의 흐름과 보충 설명은 끝이 됩니다!
만약 이 기사가 참고가 되면 ChatGPT를 만진 것 같은 사람에게 꼭 보내 주세요. 이 기사를 읽은 것이 계기로 ChatGPT의 API에 접해 보았다는 것을 말해 주는 사람이 있으면, 뛰어 넘어 기뻐합니다. 그렇게 이 기사가 만약 누군가의 도움이 된다면, 누군가의 미래의 선택을 펼칠 수 있다면, 최고입니다!
여기까지 보아 주셔서 감사합니다.

진