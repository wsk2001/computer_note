#  # Python으로 HTTPS 탐험

출처: https://realpython.com/python-https/



목차

- [HTTP란 무엇입니까?](https://realpython.com/python-https/#what-is-http)
- [HTTPS란 무엇입니까?](https://realpython.com/python-https/#what-is-https)
- HTTPS가 중요한 이유는 무엇입니까?
  - [예제 애플리케이션 만들기](https://realpython.com/python-https/#creating-an-example-application)
  - [Wireshark 설정](https://realpython.com/python-https/#setting-up-wireshark)
  - [데이터가 안전하지 않다는 것을 확인](https://realpython.com/python-https/#seeing-that-your-data-is-not-safe)
- 암호화는 어떻게 도움이 됩니까?
  - [암호화 기본 이해](https://realpython.com/python-https/#understanding-cryptography-basics)
  - [Python HTTPS 애플리케이션에서 암호화 사용](https://realpython.com/python-https/#using-cryptography-in-python-https-applications)
  - [데이터가 안전한지 확인](https://realpython.com/python-https/#seeing-that-your-data-is-safe)
- [키는 어떻게 공유됩니까?](https://realpython.com/python-https/#how-are-keys-shared)
- [현실 세계에서 HTTPS는 어떤가요?](https://realpython.com/python-https/#what-is-https-like-in-the-real-world)
- Python HTTPS 애플리케이션은 어떻게 생겼습니까?
  - [인증 기관 되기](https://realpython.com/python-https/#becoming-a-certificate-authority)
  - [서버 신뢰](https://realpython.com/python-https/#trusting-your-server)
- [결론](https://realpython.com/python-https/#conclusion)



> 이 자습서에는 Real Python 팀에서 만든 관련 비디오 과정이 있습니다. 문서 자습서와 함께 시청하여 이해를 높이십시오: [**Python에서 HTTPS 및 암호화 탐색**](https://realpython.com/courses/exploring-https-cryptography/)



인터넷을 통해 신용 카드 정보를 보내는 것이 왜 괜찮은지 궁금한 적이 있습니까? `https://`브라우저에서 on URL을 알아차렸을 수도 있지만 그게 무엇이며 어떻게 **정보를 안전하게 유지** 합니까 ? 또는 Python HTTPS 애플리케이션을 만들고 싶지만 정확히 무엇을 의미하는지 잘 모르겠습니다. [웹 애플리케이션이](https://realpython.com/python-web-applications/) 안전하다는 것을 어떻게 확신할 수 있습니까 ?

이러한 질문에 답하기 위해 보안 전문가가 될 필요가 없다는 사실에 놀랄 수도 있습니다! 이 자습서에서는 인터넷을 통한 통신을 안전하게 유지하기 위해 결합되는 다양한 요소에 대한 작업 지식을 얻을 수 있습니다. Python HTTPS 애플리케이션이 정보를 안전하게 유지하는 방법에 대한 구체적인 예를 볼 수 있습니다.

**이 자습서에서는 다음 방법을 배웁니다.**

- **네트워크 트래픽** 모니터링 및 분석
- 데이터를 안전하게 유지하기 위해 **암호화** 적용
- **공개 키 인프라(PKI)** 의 핵심 개념 설명
- 나만의 **인증 기관 만들기**
- **Python HTTPS 애플리케이션** 빌드
- 일반적인 Python HTTPS **경고 및 오류 식별**

**무료 보너스:** Flask 웹 앱을 단계별로 빌드하는 방법을 보여주는 [무료 Flask + Python 비디오 자습서에 액세스하려면 여기를 클릭하십시오 .](https://realpython.com/bonus/discover-flask-video-tutorial/)

## HTTP란 무엇입니까?

HTTPS와 Python에서의 사용에 대해 자세히 알아보기 전에 부모인 [HTTP를](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol) 이해하는 것이 중요합니다 . 이 약어 는 즐겨찾는 웹사이트를 서핑할 때 진행되는 대부분의 통신을 뒷받침하는 **HyperText Transfer Protocol** 의 약자입니다 . *보다 구체적으로 HTTP는 웹 브라우저와 같은 사용자 에이전트가 realpython.com* 과 같은 웹 서버와 통신하는 방법입니다 . 다음은 HTTP 통신의 단순화된 다이어그램입니다.

[![HTTP 핸드셰이크 흐름](https://files.realpython.com/media/simple_http_flow.b16a6625419f.png)](https://files.realpython.com/media/simple_http_flow.b16a6625419f.png)

이 다이어그램은 컴퓨터가 서버와 통신하는 방식을 간략하게 보여줍니다. 각 단계의 분석은 다음과 같습니다.

1. 로 이동하도록 브라우저에 지시합니다 `http://someserver.com/link`.
2. 장치와 서버가 [TCP](https://en.wikipedia.org/wiki/Transmission_Control_Protocol) 연결을 설정합니다.
3. 브라우저가 서버에 **HTTP 요청을 보냅니다.**
4. 서버는 HTTP 요청을 수신하고 구문 분석합니다.
5. 서버는 **HTTP 응답** 으로 응답합니다 .
6. 귀하의 컴퓨터는 응답을 수신, 구문 분석 및 표시합니다.

이 분류는 HTTP의 기본 사항을 캡처합니다. 서버에 요청하면 서버가 응답을 반환합니다. HTTP에는 TCP가 필요하지 않지만 안정적인 하위 수준 프로토콜이 필요합니다. 실제로 이것은 거의 항상 IP를 통한 TCP입니다(Google이 대체품을 만들려고 하지만 [)](https://en.wikipedia.org/wiki/QUIC) . 복습이 필요한 경우 [Python의 소켓 프로그래밍(가이드)을](https://realpython.com/python-sockets/) 확인하십시오 .

프로토콜이 진행됨에 따라 HTTP는 더 간단한 프로토콜 중 하나입니다. HTML, 비디오, 이미지 등과 같은 콘텐츠를 인터넷을 통해 전송하도록 설계되었습니다. 이는 HTTP 요청 및 응답으로 수행됩니다. HTTP 요청에는 다음 요소가 포함됩니다.

- **method 는** 클라이언트가 수행하려는 작업을 설명합니다. 정적 컨텐츠에 대한 메소드는 일반적으로 이지만 , 및 와 `GET`같은 다른 메소드도 사용할 수 있습니다 .`POST``HEAD``DELETE`
- **path 는** 요청하려는 웹 페이지를 서버에 나타냅니다. 예를 들어, 이 페이지의 경로는 입니다 `/python-https`.
- **version 은** 1.0, 1.1 또는 2.0과 같은 여러 HTTP 버전 중 하나입니다. 가장 일반적인 것은 아마도 1.1입니다.
- **header 는** 서버에 대한 추가 정보를 설명하는 데 도움이 됩니다.
- **body 는** 클라이언트의 정보를 서버에 제공합니다. 이 필드가 필수는 아니지만 일부 메서드에는 `POST`.

브라우저가 서버와 통신하는 데 사용하는 도구입니다. 서버는 HTTP 응답으로 응답합니다. HTTP 응답에는 다음 요소가 포함됩니다.

- **version 은** 일반적으로 요청 버전과 동일한 HTTP 버전을 식별합니다.
- **status code 는** 요청이 성공적으로 완료되었는지 여부를 나타냅니다. [꽤 많은 상태 코드가](https://www.restapitutorial.com/httpstatuscodes.html) 있습니다 .
- **status message 는** 상태 코드를 설명하는 데 도움이 되는 사람이 읽을 수 있는 메시지를 제공합니다.
- **header 를 통해** 서버는 요청에 대한 추가 메타데이터로 응답할 수 있습니다. 요청 헤더와 동일한 개념입니다.
- **body 는** 내용물을 담고 있습니다. 기술적으로 이것은 선택 사항이지만 일반적으로 유용한 리소스를 포함합니다.

이들은 HTTP의 빌딩 블록입니다. HTTP에 대해 자세히 알아보려면 [개요 페이지를](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview) 확인하여 프로토콜에 대해 자세히 알아볼 수 있습니다.

## HTTPS란 무엇입니까?

이제 HTTP에 대해 조금 더 이해했으므로 HTTPS가 무엇입니까? 좋은 소식은 당신이 이미 이것을 알고 있다는 것입니다! HTTPS는 **HyperText Transfer Protocol Secure** 의 약자입니다 . 기본적으로 HTTPS는 HTTP와 동일한 프로토콜이지만 통신이 안전하다는 추가 의미가 있습니다.

HTTPS는 기본 HTTP를 다시 작성하지 않습니다. 대신 HTTPS는 암호화된 연결을 통해 전송되는 일반 HTTP로 구성됩니다. 일반적으로 이 암호화된 연결 은 네트워크를 통해 정보를 전송하기 전에 정보를 암호화하는 **암호화 프로토콜** 인 TLS 또는 SSL에 의해 제공됩니다 .

**참고:** TLS와 SSL은 매우 유사한 프로토콜이지만 SSL이 사라지고 TLS가 대신할 것입니다. 이러한 프로토콜의 차이점은 이 자습서의 범위를 벗어납니다. TLS가 더 새롭고 더 나은 SSL 버전이라는 것을 아는 것으로 충분합니다.

그렇다면 이러한 분리를 만드는 이유는 무엇입니까? HTTP 프로토콜 자체에 복잡성을 도입하지 않는 이유는 무엇입니까? 답은 **이식성** 입니다 . 통신 보안은 중요하고 어려운 문제이지만 HTTP는 보안이 필요한 많은 프로토콜 중 하나일 뿐입니다. 다양한 응용 프로그램에 걸쳐 셀 수 없이 많은 다른 것들이 있습니다.

- [이메일](https://realpython.com/python-send-email/)
- 인스턴트 메시징
- VoIP(Voice over IP)

다른 것들도 있습니다! 이러한 각 프로토콜이 자체 보안 메커니즘을 만들어야 한다면 세상은 훨씬 덜 안전하고 훨씬 더 혼란스러울 것입니다. 위의 프로토콜에서 자주 사용되는 TLS는 통신 보안을 위한 공통 방법을 제공합니다.

**참고:** 이러한 프로토콜 분리는 네트워킹에서 공통적인 주제이므로 이름이 있습니다. OSI [모델은](https://en.wikipedia.org/wiki/OSI_model) 물리적 매체에서 이 페이지에 렌더링된 HTML까지의 통신을 나타냅니다!

이 자습서에서 배우게 될 거의 모든 정보는 Python HTTPS 응용 프로그램 이상에 적용할 수 있습니다. 특히 HTTPS에 적용되는 방법과 함께 보안 통신의 기본 사항을 배우게 됩니다.

## HTTPS가 중요한 이유는 무엇입니까?

보안 통신은 안전한 온라인 환경을 제공하는 데 중요합니다. 은행과 의료 사이트를 포함하여 더 많은 세상이 온라인으로 이동함에 따라 개발자가 Python HTTPS 애플리케이션을 만드는 것이 점점 더 중요해지고 있습니다. 다시 말하지만 HTTPS는 TLS 또는 SSL을 통한 HTTP일 뿐입니다. TLS는 도청자로부터 프라이버시를 제공하도록 설계되었습니다. 또한 클라이언트와 서버 모두에 대한 인증을 제공할 수도 있습니다.

이 섹션에서는 다음을 수행하여 이러한 개념을 자세히 살펴봅니다.

1. Python HTTPS 서버 **생성**
2. Python HTTPS 서버와 **통신**
3. 이러한 커뮤니케이션 **캡처**
4. 해당 메시지 **분석**

시작하자!

### 예제 애플리케이션 만들기

Secret Squirrels라는 멋진 Python 클럽의 리더라고 가정합니다. 다람쥐는 비밀이기 때문에 회의에 참석하려면 비밀 메시지가 필요합니다. 리더로서 귀하는 회의마다 변경되는 비밀 메시지를 선택합니다. 하지만 때로는 회의 전에 모든 멤버들과 만나 비밀 메시지를 전하는 것이 어려울 때도 있습니다! 구성원이 자신의 비밀 메시지를 볼 수 있는 비밀 서버를 설정하기로 결정합니다.

**참고:** 이 자습서에 사용된 예제 코드는 프로덕션용으로 설계되지 **않았습니다 .** HTTP 및 TLS의 기본 사항을 학습하는 데 도움이 되도록 설계되었습니다. **이 코드를 프로덕션에 사용하지 마십시오.** 아래의 많은 예에는 끔찍한 보안 관행이 있습니다. 이 자습서에서는 TLS와 TLS가 보안을 강화하는 데 도움이 되는 한 가지 방법에 대해 알아봅니다.

Real Python에 대한 몇 가지 자습서를 따랐고 알고 있는 몇 가지 종속성을 사용하기로 결정했습니다.

- 웹 애플리케이션을 빌드하기 위한**[ Flask](https://palletsprojects.com/p/flask)**
- 프로덕션 서버로서의**[ uWSGI](https://uwsgi-docs.readthedocs.io/en/latest/)**
- 서버 실행**[ 요청](https://realpython.com/python-requests/)**

이러한 종속성을 모두 설치하려면 다음을 사용할 수 있습니다 [`pip`](https://realpython.com/what-is-pip/).

```sh
$ pip install flask uwsgi requests
```

종속 항목이 설치되면 애플리케이션 작성을 시작합니다. 라는 파일에서 [Flask](https://realpython.com/tutorials/flask/)`server.py` 애플리케이션을 만듭니다 .

```py
# server.py
from flask import Flask

SECRET_MESSAGE = "fluffy tail"
app = Flask(__name__)

@app.route("/")
def get_secret_message():
    return SECRET_MESSAGE
```

`/`이 Flask 애플리케이션은 누군가 서버 경로를 방문할 때마다 비밀 메시지를 표시합니다 . 그런 식으로 비밀 서버에 애플리케이션을 배포하고 실행합니다.

```bash
$ uwsgi --http-socket 127.0.0.1:5683 --mount /=server:app
```

이 명령은 위의 Flask 애플리케이션을 사용하여 서버를 시작합니다. 당신은 사람들이 그것을 찾을 수 있기를 원하지 않기 때문에 이상한 포트에서 시작하고, 너무 교활하다고 스스로를 자책합니다! `http://localhost:5683`브라우저에서 방문하여 작동하는지 확인할 수 있습니다 .

Secret Squirrels의 모든 사람들이 Python을 알고 있으므로 그들을 돕기로 결정합니다. `client.py`비밀 메시지를 받는 데 도움이 되는 스크립트를 작성합니다 .

```python
# client.py
import os
import requests

def get_secret_message():
    url = os.environ["SECRET_URL"]
    response = requests.get(url)
    print(f"The secret message is: {response.text}")

if __name__ == "__main__":
    get_secret_message()
```

이 코드는 환경 변수가 설정되어 있는 한 비밀 메시지를 출력합니다 `SECRET_URL`. 이 경우 는 `SECRET_URL`입니다 `127.0.0.1:5683`. 따라서 귀하의 계획은 각 클럽 회원에게 비밀 URL을 제공하고 [비밀로 안전하게 유지하도록](https://youtu.be/iThtELZvfPs) 지시하는 것입니다 .

이것이 괜찮아 보일 수 있지만 안심하십시오! 사실, 이 사이트에 사용자 이름과 암호를 입력하더라도 여전히 안전하지 않습니다. 그러나 팀이 어떻게든 URL을 안전하게 유지하더라도 비밀 메시지는 여전히 안전하지 않습니다. 네트워크 트래픽 모니터링에 대해 약간 알아야 하는 이유를 설명하려면. 이를 위해 **Wireshark** 라는 도구를 사용하게 됩니다 .

### Wireshark 설정

[Wireshark](https://www.wireshark.org/) 는 네트워크 및 프로토콜 분석에 널리 사용되는 도구입니다. 이것이 의미하는 바는 네트워크 연결을 통해 발생하는 상황을 확인하는 데 도움이 될 수 있다는 것입니다. Wireshark 설치 및 설정은 이 튜토리얼에서 선택 사항이지만 따라하고 싶다면 자유롭게 하십시오. 다운로드 [페이지에는](https://www.wireshark.org/download.html) 사용 가능한 여러 설치 프로그램이 있습니다.

- macOS 10.12 이상
- 윈도우 인스톨러 64비트
- 윈도우 인스톨러 32비트

Windows 또는 Mac을 사용하는 경우 적절한 설치 프로그램을 다운로드하고 프롬프트를 따를 수 있습니다. 결국 Wireshark를 실행해야 합니다.

Debian 기반 Linux 환경에 있는 경우 설치가 조금 어렵지만 여전히 가능합니다. 다음 명령을 사용하여 Wireshark를 설치할 수 있습니다.

```bash
$ sudo add-apt-repository ppa:wireshark-dev/stable
$ sudo apt-get update
$ sudo apt-get install wireshark
$ sudo wireshark
```

다음과 같은 화면을 만나야 합니다.

[![와이어샤크 메인 화면](https://files.realpython.com/media/wireshark-home.8d87e40d425f.png)](https://files.realpython.com/media/wireshark-home.8d87e40d425f.png)

Wireshark가 실행되면 일부 트래픽을 분석할 시간입니다!

### 데이터가 안전하지 않다는 것을 확인

현재 클라이언트와 서버가 실행되는 방식은 **안전하지 않습니다** . HTTP는 누구나 볼 수 있도록 모든 것을 명확하게 보냅니다. 이것이 의미하는 바는 누군가가 귀하의 를 가지고 있지 않더라도 귀하와 서버 사이의 *모든*`SECRET_URL` 장치 에서 트래픽을 모니터링할 수 있는 한 여전히 귀하가 하는 모든 작업을 볼 수 있다는 것입니다.

이것은 당신에게 상대적으로 무서울 것입니다. 결국, 비밀 다람쥐 회의에 다른 사람들이 나타나는 것을 원하지 않을 것입니다! 당신은 이것이 일어나고 있음을 증명할 수 있습니다. 먼저 아직 실행 중이 아니면 서버를 시작합니다.

```bash
$ uwsgi --http-socket 127.0.0.1:5683 --mount /=server:app
```

이렇게 하면 포트 5683에서 Flask 애플리케이션이 시작됩니다. 다음으로 Wireshark에서 패킷 캡처를 시작합니다. 이 패킷 캡처는 서버를 오가는 모든 트래픽을 보는 데 도움이 됩니다. Wireshark에서 *Loopback:lo* 인터페이스를 선택하여 시작합니다 .

[![Loopback이 선택된 Wireshark](https://files.realpython.com/media/wireshark-loopback-click.8f9a2f451082.png)](https://files.realpython.com/media/wireshark-loopback-click.8f9a2f451082.png)

*Loopback:lo* 부분이 강조 표시되는 것을 볼 수 있습니다 . 이렇게 하면 Wireshark가 이 포트에서 트래픽을 모니터링하도록 지시합니다. 더 잘할 수 있고 캡처하려는 포트와 프로토콜을 지정할 수 있습니다. `port 5683`캡처 필터와 `http`표시 필터에 다음을 입력할 수 있습니다 .

[![포트 5683이 채워진 Wireshark](https://files.realpython.com/media/wireshark-port-5683-filter.3c86d723417d.png)](https://files.realpython.com/media/wireshark-port-5683-filter.3c86d723417d.png)

녹색 상자는 Wireshark가 입력한 필터에 만족함을 나타냅니다. 이제 왼쪽 상단의 핀을 클릭하여 캡처를 시작할 수 있습니다.

[![루프백 및 포트 필터링이 있는 Wireshark를 클릭했습니다.](https://files.realpython.com/media/wirehshark-click.d1a161bccdb9.png)](https://files.realpython.com/media/wirehshark-click.d1a161bccdb9.png)

이 버튼을 클릭하면 Wireshark에 새 창이 생성됩니다.

[![아무것도 캡처되지 않은 Wireshark의 캡처 페이지](https://files.realpython.com/media/wireshark-active-capture.17498bac9dac.png)](https://files.realpython.com/media/wireshark-active-capture.17498bac9dac.png)

이 새 창은 매우 평범하지만 하단에 `<live capture in progress>`작동 중임을 나타내는 메시지가 표시됩니다. 아무 것도 표시되지 않는 것이 정상이므로 걱정하지 마십시오. Wireshark가 무엇이든 보고하려면 서버에서 활동이 있어야 합니다. 일부 데이터를 얻으려면 클라이언트를 실행해 보십시오.

```bash
$ SECRET_URL="http://127.0.0.1:5683" python client.py
The secret message is: fluffy tail
```

위의 코드를 실행하면 `client.py`이제 Wireshark에 일부 항목이 표시됩니다. 모든 것이 잘 진행되면 다음과 같은 두 항목이 표시됩니다.

[![HTTP 요청 및 응답이 캡처된 Wireshark](https://files.realpython.com/media/wireshark-http-two-entries.db53111bad3e.png)](https://files.realpython.com/media/wireshark-http-two-entries.db53111bad3e.png)

이 두 항목은 발생한 통신의 두 부분을 나타냅니다. 첫 번째는 서버에 대한 클라이언트의 요청입니다. 첫 번째 항목을 클릭하면 많은 정보가 표시됩니다.

[![Wireshark의 첫 번째 HTTP 요청](https://files.realpython.com/media/wireshark-http-request-1.ca48adcd8525.png)](https://files.realpython.com/media/wireshark-http-request-1.ca48adcd8525.png)

그것은 많은 정보입니다! 상단에는 여전히 HTTP 요청과 응답이 있습니다. 이러한 항목 중 하나를 선택하면 중간 및 맨 아래 행에 정보가 채워지는 것을 볼 수 있습니다.

중간 행은 Wireshark가 선택한 요청에 대해 식별할 수 있었던 프로토콜의 분석을 제공합니다. 이 분류를 통해 HTTP 요청에서 실제로 발생한 일을 탐색할 수 있습니다. 다음은 Wireshark가 중간 행에서 위에서 아래로 설명하는 정보에 대한 간략한 요약입니다.

1. **Physical Layer:** 이 행은 요청을 보내는 데 사용되는 물리적 인터페이스를 설명합니다. 귀하의 경우 루프백 인터페이스의 인터페이스 ID 0(lo)일 수 있습니다.
2. **Ethernet Information:** 이 행은 소스 및 대상 MAC 주소를 포함하는 Layer-2 프로토콜을 보여줍니다.
3. **IPv4:** 이 행에는 소스 및 대상 IP 주소(127.0.0.1)가 표시됩니다.
4. **TCP:** 이 행에는 신뢰할 수 있는 데이터 파이프를 생성하기 위해 필요한 TCP 핸드셰이크가 포함됩니다.
5. **HTTP:** 이 행은 HTTP 요청 자체에 대한 정보를 표시합니다.

Hypertext Transfer Protocol 계층을 확장하면 HTTP 요청을 구성하는 모든 정보를 볼 수 있습니다.

[![wireshark에서 세부 정보가 확장된 HTTP 요청](https://files.realpython.com/media/wireshark-http-request-expanded.b832a33ff5d0.png)](https://files.realpython.com/media/wireshark-http-request-expanded.b832a33ff5d0.png)

이 이미지는 스크립트의 HTTP 요청을 보여줍니다.

- **Method:** `GET`
- **Path:** `/`
- **Version:** `1.1`
- **Headers:** `Host: 127.0.0.1:5683` , `Connection: keep-alive`및 기타
- **Body:** 본체 없음

마지막 행은 데이터의 16진수 덤프입니다. 이 16진수 덤프에서 HTTP 요청의 일부를 실제로 볼 수 있음을 알 수 있습니다. HTTP 요청이 공개적으로 전송되었기 때문입니다. 그러나 답장은 어떻습니까? HTTP 응답을 클릭하면 비슷한 보기가 표시됩니다.

[![HTTP 응답이 확장된 wireshark](https://files.realpython.com/media/wireshark-http-response-expanded.2111ae46e4b1.png)](https://files.realpython.com/media/wireshark-http-response-expanded.2111ae46e4b1.png)

다시 말하지만, 동일한 세 개의 섹션이 있습니다. 헥스 덤프를 주의 깊게 살펴보면 일반 텍스트로 된 비밀 메시지를 볼 수 있습니다! 이것은 Secret Squirrels에게 큰 문제입니다. 이것이 의미하는 바는 기술적 노하우가 있는 사람이라면 누구나 관심이 있다면 이 트래픽을 매우 쉽게 볼 수 있다는 것입니다. 그래서, 이 문제를 어떻게 해결합니까? 대답은 **암호화** 입니다 .

## 암호화는 어떻게 도움이 됩니까?

**이 섹션에서는 자체 암호화 키를** 만들고 서버와 클라이언트 모두에서 사용하여 데이터를 안전하게 유지하는 한 가지 방법을 배웁니다 . 이것이 최종 단계는 아니지만 Python HTTPS 애플리케이션을 빌드하는 방법에 대한 견고한 기반을 얻는 데 도움이 될 것입니다.

### 암호화 기본 이해

암호화는 도청자 또는 적으로부터 통신을 보호하는 방법입니다. 이를 설명하는 또 다른 방법은 일반 텍스트라고 하는 일반 정보를 가져와 **암호문 이라고** **하는** 뒤섞인 텍스트로 변환하는 것 입니다 .

암호화는 처음에는 위협적일 수 있지만 기본 개념은 쉽게 접근할 수 있습니다. 실제로 이전에 이미 암호화를 연습했을 것입니다. 친구와 비밀 언어를 사용하여 수업 시간에 메모를 전달하는 데 사용한 적이 있다면 암호를 연습한 것입니다. (그렇게 하지 않았다면 걱정하지 마십시오. 곧 그렇게 될 것입니다.)

[어떻게든 문자열을](https://realpython.com/python-strings/) `"fluffy tail"` 가져 와서 이해할 수 없는 것으로 변환 해야 합니다 . 이를 수행하는 한 가지 방법은 특정 문자를 다른 문자에 매핑하는 것입니다. 이를 수행하는 효과적인 방법은 문자를 알파벳의 한 지점 뒤로 이동하는 것입니다. 이렇게 하면 다음과 같이 표시됩니다.

[![1칸 이동한 알파벳 암호](https://files.realpython.com/media/alpha.12689a36982a.png)](https://files.realpython.com/media/alpha.12689a36982a.png)

이 이미지는 원래 알파벳에서 새 알파벳으로 또는 그 반대로 번역하는 방법을 보여줍니다. 따라서 메시지가 있으면 `ABC`실제로 메시지를 보낼 수 있습니다 `ZAB`. 이것을 에 적용하면 `"fluffy tail"`공간이 동일하게 유지된다고 가정하면 `ekteex szhk`. 완벽하지는 않지만 누가 봐도 횡설수설처럼 보일 것입니다.

축하해요! 일반 텍스트를 암호문으로 변환하고 그 반대로 변환하는 방법을 설명하는 암호 로 알려진 **암호를 만들었습니다.** 이 경우 암호는 영어로 설명됩니다. **이 특정 유형의 암호를 대체 암호** 라고 합니다 . 근본적으로 이것은 훨씬 간단한 버전이지만 [Enigma Machine](https://en.wikipedia.org/wiki/Enigma_machine) 에서 사용되는 동일한 유형의 암호입니다 .

이제 Secret Squirrels에게 메시지를 보내려면 먼저 이동해야 할 문자 수를 알려준 다음 인코딩된 메시지를 제공해야 합니다. Python에서는 다음과 같이 표시될 수 있습니다.

```python
CIPHER = {"a": "z", "A": "Z", "b": "a"} # And so on

def encrypt(plaintext: str):
    return "".join(CIPHER.get(letter, letter) for letter in plaintext)
```

여기에서 이라는 함수를 만들었습니다 `encrypt()`. 이 함수는 일반 텍스트를 가져와 암호문으로 변환합니다. 모든 문자가 매핑된 [사전이](https://realpython.com/courses/dictionaries-python/) 있다고 상상해 보십시오 . `CIPHER`마찬가지로 다음을 만들 수 있습니다 `decrypt()`.

```python
DECIPHER = {v: k for k, v in CIPHER.items()}

def decrypt(ciphertext: str):
    return "".join(DECIPHER.get(letter, letter) for letter in ciphertext)
```

이 기능은 의 반대입니다 `encrypt()`. 암호문을 가져와서 일반 텍스트로 변환합니다. 이 형태의 암호에는 사용자가 메시지를 암호화하고 해독하기 위해 알아야 하는 특수 키가 있습니다. 위의 예에서 해당 키는 입니다 `1`. 즉, 암호는 각 문자를 한 문자 뒤로 이동해야 함을 나타냅니다. 키가 있는 사람은 누구나 메시지를 쉽게 해독할 수 있기 때문에 키는 비밀로 유지하는 것이 매우 중요합니다.

**참고:** 암호화에 이것을 사용할 수는 있지만 여전히 매우 안전하지는 않습니다. [이 암호는 주파수 분석을](https://en.wikipedia.org/wiki/Frequency_analysis) 사용하여 빠르게 해독되며 비밀 다람쥐에게는 너무 원시적입니다.

현대 시대에 암호화는 훨씬 더 발전했습니다. 보안을 위해 복잡한 수학적 이론과 컴퓨터 과학에 의존합니다. 이러한 암호 뒤에 있는 수학은 이 자습서의 범위를 벗어나지만 기본 개념은 여전히 동일합니다. 일반 텍스트를 가져와 암호문으로 변환하는 방법을 설명하는 암호가 있습니다.

대체 암호와 현대 암호의 유일한 실제 차이점은 현대 암호는 도청자가 해독하는 데 비실용적이라는 것이 수학적으로 입증되었다는 것입니다. 이제 새 암호를 사용하는 방법을 살펴보겠습니다.

### Python HTTPS 애플리케이션에서 암호화 사용

다행스럽게도 암호를 사용하기 위해 수학이나 컴퓨터 과학의 전문가가 될 필요는 없습니다. Python에는 [암호로 안전한 임의 데이터를](https://realpython.com/python-random/#pythons-best-kept-secrets)`secrets` 생성하는 데 도움이 되는 모듈 도 있습니다 . 이 자습서에서는 적절한 이름의 Python 라이브러리에 대해 알아봅니다 . PyPI에서 사용할 수 있으므로 pip로 설치할 수 있습니다.[`cryptography`](https://cryptography.io/en/latest/)

```bash
$ pip install cryptography
```

[이것은 가상 환경](https://realpython.com/python-virtual-environments-a-primer/)`cryptography` 에 설치됩니다 . 설치 하면 이제 이 방법을 사용하여 수학적으로 안전한 방식으로 사물을 암호화하고 해독할 수 있습니다 .`cryptography`[`Fernet`](https://github.com/fernet/spec/)

암호의 비밀 키는 `1`. 같은 맥락에서 Fernet이 올바르게 작동하려면 키를 만들어야 합니다.

\>>>

```python
>>> from cryptography.fernet import Fernet
>>> key = Fernet.generate_key()
>>> key
b'8jtTR9QcD-k3RO9Pcd5ePgmTu_itJQt9WKQPzqjrcoM='
```

이 코드에서는 `Fernet`키를 가져와서 생성했습니다. 키는 바이트 묶음에 불과하지만 이 키를 비밀로 안전하게 유지하는 것이 매우 중요합니다. 위의 대체 예와 마찬가지로 이 키를 가진 사람은 누구나 메시지를 쉽게 해독할 수 있습니다.

**참고:** 실제로는 이 키를 **매우** 안전하게 보관합니다. 이 예에서는 키를 보는 것이 도움이 되지만 공개 웹 사이트에 키를 게시하는 경우 특히 좋지 않습니다. 즉, 보안을 유지하려는 항목에 **위에 표시된 정확한 키를 사용하지 마십시오 .**

이 키는 이전 키와 매우 유사하게 작동합니다. 암호문으로 전환하고 다시 일반 텍스트로 전환하는 데 필요합니다. 이제 재미있는 부분을 위한 시간입니다! 다음과 같이 메시지를 암호화할 수 있습니다.

\>>>

```python
>>> my_cipher = Fernet(key)
>>> ciphertext = my_cipher.encrypt(b"fluffy tail")
>>> ciphertext
b'gAAAAABdlW033LxsrnmA2P0WzaS-wk1UKXA1IdyDpmHcV6yrE7H_ApmSK8KpCW-6jaODFaeTeDRKJMMsa_526koApx1suJ4_dQ=='
```

`my_cipher`이 코드에서는 메시지를 암호화하는 데 사용할 수 있는 이라는 Fernet 개체를 만들었습니다 . 비밀 메시지를 암호화하려면 개체 `"fluffy tail"`여야 합니다 . `bytes`암호화 후 `ciphertext`긴 바이트 스트림임을 알 수 있습니다.

Fernet 덕분에 이 암호문은 키 없이는 조작하거나 읽을 수 없습니다! 이 유형의 암호화를 사용하려면 서버와 클라이언트 모두 키에 액세스할 수 있어야 합니다. 양쪽에서 동일한 키가 필요한 경우 이를 [대칭 암호화](https://en.wikipedia.org/wiki/Symmetric-key_algorithm) 라고 합니다 . 다음 섹션에서는 이 대칭 암호화를 사용하여 데이터를 안전하게 유지하는 방법을 볼 수 있습니다.

### 데이터가 안전한지 확인

이제 Python의 암호화 기본 사항 중 일부를 이해했으므로 이 지식을 서버에 적용할 수 있습니다. 다음과 같은 새 파일을 만듭니다 `symmetric_server.py`.

```python
# symmetric_server.py
import os
from flask import Flask
from cryptography.fernet import Fernet

SECRET_KEY = os.environb[b"SECRET_KEY"]
SECRET_MESSAGE = b"fluffy tail"
app = Flask(__name__)

my_cipher = Fernet(SECRET_KEY)

@app.route("/")
def get_secret_message():
    return my_cipher.encrypt(SECRET_MESSAGE)
```

이 코드는 원래 서버 코드를 `Fernet`이전 섹션에서 사용한 개체와 결합합니다. 이제 키를 `bytes`사용하여 환경에서 객체 로 읽습니다 [`os.environb`](https://docs.python.org/3/library/os.html#os.environb). 서버를 치우면 이제 클라이언트에 집중할 수 있습니다. 다음을 붙여넣습니다 `symmetric_client.py`.

```python
# symmetric_client.py
import os
import requests
from cryptography.fernet import Fernet

SECRET_KEY = os.environb[b"SECRET_KEY"]
my_cipher = Fernet(SECRET_KEY)

def get_secret_message():
    response = requests.get("http://127.0.0.1:5683")

    decrypted_message = my_cipher.decrypt(response.content)
    print(f"The codeword is: {decrypted_message}")

if __name__ == "__main__":
    get_secret_message()
```

다시 한 번 이것은 이전 클라이언트를 `Fernet`암호화 메커니즘과 결합하도록 수정된 코드입니다. `get_secret_message()`다음을 수행합니다.

1. 서버에 요청 **하십시오 .**
2. 응답에서 원시 바이트를 **가져옵니다 .**
3. 원시 바이트의 암호 해독을 **시도합니다 .**
4. 해독된 메시지를 **인쇄합니다 .**

서버와 클라이언트를 모두 실행하면 비밀 메시지를 성공적으로 암호화하고 해독하는 것을 볼 수 있습니다.

```bash
$ uwsgi --http-socket 127.0.0.1:5683 \
    --env SECRET_KEY="8jtTR9QcD-k3RO9Pcd5ePgmTu_itJQt9WKQPzqjrcoM=" \
    --mount /=symmetric_server:app
```

이 호출에서는 포트 5683에서 서버를 다시 시작합니다. 이번에는 `SECRET_KEY`32 길이 이상의 base64 인코딩 문자열이어야 하는 를 전달합니다. 서버가 다시 시작되면 이제 쿼리할 수 있습니다.

```bash
$ SECRET_KEY="8jtTR9QcD-k3RO9Pcd5ePgmTu_itJQt9WKQPzqjrcoM=" python symmetric_client.py
The secret message is: b'fluffy tail'
```

우후! 메시지를 암호화하고 해독할 수 있었습니다. 잘못된 로 이것을 실행하려고 하면 `SECRET_KEY`오류가 발생합니다.

```bash
$ SECRET_KEY="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=" python symmetric_client.py
Traceback (most recent call last):
  File ".../cryptography/fernet.py", line 104, in _verify_signature
    h.verify(data[-32:])
  File ".../cryptography/hazmat/primitives/hmac.py", line 66, in verify
    ctx.verify(signature)
  File ".../cryptography/hazmat/backends/openssl/hmac.py", line 74, in verify
    raise InvalidSignature("Signature did not match digest.")
cryptography.exceptions.InvalidSignature: Signature did not match digest.

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File "symmetric_client.py", line 16, in <module>
    get_secret_message()
  File "symmetric_client.py", line 11, in get_secret_message
    decrypted_message = my_cipher.decrypt(response.content)
  File ".../cryptography/fernet.py", line 75, in decrypt
    return self._decrypt_data(data, timestamp, ttl)
  File ".../cryptography/fernet.py", line 117, in _decrypt_data
    self._verify_signature(data)
  File ".../cryptography/fernet.py", line 106, in _verify_signature
    raise InvalidToken
cryptography.fernet.InvalidToken
```

따라서 암호화 및 암호 해독이 작동하고 있음을 알 수 있습니다. **하지만 안전한** 가요 ? 네, 그렇습니다. 이를 증명하기 위해 Wireshark로 돌아가서 이전과 동일한 필터로 새 캡처를 시작할 수 있습니다. 캡처 설정이 완료되면 클라이언트 코드를 다시 실행합니다.

```bash
$ SECRET_KEY="8jtTR9QcD-k3RO9Pcd5ePgmTu_itJQt9WKQPzqjrcoM=" python symmetric_client.py
The secret message is: b'fluffy tail'
```

또 다른 성공적인 HTTP 요청 및 응답을 만들었고 다시 한 번 Wireshark에서 이러한 메시지를 볼 수 있습니다. 비밀 메시지는 응답으로만 전송되므로 해당 메시지를 클릭하여 데이터를 볼 수 있습니다.

[![대칭 암호화를 사용하여 암호화된 HTTP 응답에 대한 Wireshark의 보기](https://files.realpython.com/media/wireshark-symmetric-http-response.67ab18c74759.png)](https://files.realpython.com/media/wireshark-symmetric-http-response.67ab18c74759.png)

이 그림의 중간 행에서 실제로 전송된 데이터를 볼 수 있습니다.

```bash
gAAAAABdlXSesekh9LYGDpZE4jkxm4Ai6rZQg2iHaxyDXkPWz1O74AB37V_a4vabF13fEr4kwmCe98Wlr8Zo1XNm-WjAVtSgFQ==
```

엄청난! 즉, 데이터가 암호화되었고 도청자는 메시지 내용이 실제로 무엇인지 알 수 없습니다. 뿐만 아니라, 이 데이터를 무차별 대입하여 크랙하는 데 미친 듯이 오랜 시간을 할애할 수 있으며 거의 성공하지 못할 수도 있습니다.

귀하의 데이터는 안전합니다! 하지만 잠깐만요. 이전에 Python HTTPS 애플리케이션을 사용할 때는 키에 대해 전혀 알 필요가 없었습니다. HTTPS는 대칭 암호화를 배타적으로 사용하지 않기 때문입니다. 결과적으로 비밀을 공유하는 것은 어려운 문제입니다.

이 개념을 증명하기 위해 `http://127.0.0.1:5683`브라우저에서 로 이동하면 암호화된 응답 텍스트가 표시됩니다. 이는 브라우저가 비밀 암호 키에 대해 아무것도 모르기 때문입니다. 그렇다면 Python HTTPS 애플리케이션은 실제로 어떻게 작동할까요? 여기서 **비대칭 암호화** 가 시작됩니다.

## 키는 어떻게 공유됩니까?

이전 섹션에서는 데이터가 인터넷을 통과할 때 데이터를 안전하게 유지하기 위해 대칭 암호화를 사용하는 방법을 살펴보았습니다. 여전히 대칭 암호화가 안전하더라도 데이터를 안전하게 유지하기 위해 Python HTTPS 애플리케이션에서 사용하는 유일한 암호화 기술은 아닙니다. 대칭 암호화는 쉽게 해결되지 않는 몇 가지 근본적인 문제를 야기합니다.

**참고: 대칭 암호화를 사용하려면 클라이언트와 서버 간에** **공유 키가** 있어야 합니다 . 불행히도 보안은 가장 약한 링크만큼만 작동하며 약한 링크는 대칭 암호화에서 특히 치명적입니다. 한 사람이 키를 손상시키면 **모든 키가 손상됩니다** . 모든 보안 시스템이 언젠가는 손상될 것이라고 가정하는 것이 안전합니다.

그렇다면 키는 어떻게 **바꾸 나요?** 하나의 서버와 하나의 클라이언트만 있는 경우 빠른 작업이 될 수 있습니다. 그러나 더 많은 클라이언트와 더 많은 서버가 있으면 키를 변경하고 비밀을 효과적으로 안전하게 유지하기 위해 더 많은 조정이 필요합니다.

게다가 매번 새로운 비밀을 선택해야 합니다. 위의 예에서 무작위로 생성된 키를 보았습니다. 사람들이 그 키를 기억하도록 시도하는 것은 거의 불가능할 수 있습니다. 클라이언트 및 서버 수가 증가함에 따라 기억하고 추측하기 더 쉬운 키를 사용하게 될 것입니다.

키 변경을 처리할 수 있다면 여전히 해결해야 할 문제가 하나 더 있습니다. 초기 키를 어떻게 **공유 합니까?** Secret Squirrels 예제에서는 각 구성원에게 물리적으로 액세스하여 이 문제를 해결했습니다. 각 구성원에게 직접 비밀을 알려주고 비밀로 유지하라고 말할 수 있지만 누군가는 가장 약한 고리가 될 것임을 기억하십시오.

이제 다른 물리적 위치에서 Secret Squirrels에 구성원을 추가한다고 가정합니다. 이 멤버와 비밀을 어떻게 공유합니까? 키가 바뀔 때마다 그들이 당신에게 비행기를 타게 합니까? 비밀키를 서버에 올려놓고 자동으로 공유할 수 있다면 좋을 것 같습니다. 불행히도 이것은 누구나 비밀 키를 얻을 수 있기 때문에 암호화의 전체 목적을 무효화합니다!

물론 비밀 메시지를 얻기 위해 모든 사람에게 초기 마스터 키를 줄 수는 있지만 지금은 이전보다 두 배 많은 문제가 있습니다. 머리가 아프더라도 걱정하지 마세요! 당신은 유일한 사람이 아닙니다.

당신이 필요로 하는 것은 공유된 비밀을 가진 적이 없는 두 당사자를 위한 것입니다. 불가능한 것 같죠? [운 좋게도 Ralph Merkle](https://en.wikipedia.org/wiki/Ralph_Merkle) , [Whitfield Diffie](https://en.wikipedia.org/wiki/Whitfield_Diffie) 및 [Martin Hellman](https://en.wikipedia.org/wiki/Martin_Hellman) 이라는 이름의 세 사람이 등을 맞대고 있습니다. [그들은 비대칭 암호화](https://en.wikipedia.org/wiki/Public-key_cryptography) 라고도 하는 공개 키 암호화가 가능하다는 것을 입증하는 데 도움을 주었습니다.

**참고:** Whitfield Diffie와 Martin Hellman이 이 계획을 최초로 발견한 것으로 널리 알려져 있지만, 1997년에 GCHQ에서 근무하는 [James ](https://en.wikipedia.org/wiki/GCHQ)[H. Ellis](https://en.wikipedia.org/wiki/James_H._Ellis) , [Clifford Cocks](https://en.wikipedia.org/wiki/Clifford_Cocks) 및 [Malcolm J. Williamson](https://en.wikipedia.org/wiki/Malcolm_J._Williamson) 이라는 세 사람이 이전에 이 기능을 시연했습니다. 몇 년 전에!

**비대칭 암호화를** 사용하면 이전에 통신한 적이 없는 두 사용자가 공통 비밀을 공유할 수 있습니다. 기본을 이해하는 가장 쉬운 방법 중 하나는 색상 비유를 사용하는 것입니다. 다음과 같은 시나리오가 있다고 상상해 보십시오.

[![Diffie Hellman Key Exchange의 초기 설정](https://files.realpython.com/media/dh-initial.6b8a9b7877c3.png)](https://files.realpython.com/media/dh-initial.6b8a9b7877c3.png)

이 다이어그램에서 당신은 한 번도 만난 적이 없는 비밀 다람쥐와 통신을 시도하고 있지만 스파이는 당신이 보내는 모든 것을 볼 수 있습니다. 대칭 암호화에 대해 알고 있고 이를 사용하고 싶지만 먼저 비밀을 공유해야 합니다. 운 좋게도 두 사람 모두 개인 키를 가지고 있습니다. 안타깝게도 스파이가 볼 것이기 때문에 개인 키를 보낼 수 없습니다. 그래서 당신은 무엇을합니까?

가장 먼저 해야 할 일은 노란색과 같은 색상에 대해 파트너와 동의하는 것입니다.

[![Diffie Hellman 키 교환의 공유 색상](https://files.realpython.com/media/dh-2.f5ab3fbf2421.png)](https://files.realpython.com/media/dh-2.f5ab3fbf2421.png)

여기서 스파이는 당신과 비밀 다람쥐처럼 공유된 색상을 볼 수 있습니다. 공유 색상은 사실상 **public** 입니다 . 이제 귀하와 Secret Squirrel 모두 **개인** 키를 공유 색상과 결합합니다.

[![결합된 색상 Diffie Hellman 키 교환](https://files.realpython.com/media/dh-3.ad7db1b0f304.png)](https://files.realpython.com/media/dh-3.ad7db1b0f304.png)

당신의 색이 합쳐져 녹색이 되고 비밀 다람쥐의 색이 합쳐져 주황색이 됩니다. 둘 다 공유 색상으로 작업을 완료했으며 이제 결합된 색상을 서로 공유해야 합니다.

[![Diffie Hellman Key Exchange에서 결합된 색상 교환](https://files.realpython.com/media/dh-4.9d2ac2bff7c9.png)](https://files.realpython.com/media/dh-4.9d2ac2bff7c9.png)

이제 개인 키와 Secret Squirrel의 결합된 색상이 있습니다. 마찬가지로 Secret Squirrel은 개인 키와 조합된 색상을 가지고 있습니다. 당신과 Secret Squirrel이 당신의 색상을 결합하는 것은 꽤 빨랐습니다.

그러나 스파이는 이러한 결합된 색상만 가지고 있습니다. 초기 공유 색상이 주어져도 *정확한* 원래 색상을 파악하는 것은 매우 어렵습니다. 스파이는 상점에 가서 여러 종류의 블루스를 사야 했습니다. 그럼에도 불구하고 그들이 조합 후에 올바른 녹색 음영을 보고 있는지 알기 어려울 것입니다! 즉, 개인 키는 여전히 **개인 키** 입니다 .

하지만 당신과 비밀 다람쥐는 어떻습니까? 아직 결합된 비밀이 없습니다! 이것은 개인 키가 반환되는 곳입니다. 비밀 다람쥐에게서 받은 조합 색상과 개인 키를 결합하면 둘 다 같은 색상이 됩니다.

[![Diffie Hellman 키 교환을 사용한 공유 비밀](https://files.realpython.com/media/dh-5.57ffde26feed.png)](https://files.realpython.com/media/dh-5.57ffde26feed.png)

이제 당신과 Secret Squirrel은 동일한 비밀 색상을 공유합니다. 이제 완전히 낯선 사람과 보안 비밀을 성공적으로 공유했습니다. 이것은 공개 키 암호화가 작동하는 방식과 놀라울 정도로 정확합니다. 이 일련의 이벤트에 대한 또 다른 일반적인 이름은 **Diffie-Hellman 키 교환** 입니다 . 키 교환은 다음 부분으로 구성됩니다.

- **개인 키는** 예제의 개인 색상입니다.
- **공개 키는** 공유한 조합 색상입니다.

개인 키는 항상 비공개로 유지하는 반면 공개 키는 누구와도 공유할 수 있습니다. 이러한 개념은 Python HTTPS 애플리케이션의 실제 세계에 직접 매핑됩니다. 이제 서버와 클라이언트에 공유 비밀이 있으므로 이전 친구 대칭 암호화를 사용하여 모든 추가 메시지를 암호화할 수 있습니다!

**참고:** 공개 키 암호화는 또한 색상 혼합을 수행하기 위해 일부 수학에 의존합니다. Diffie-Hellman 키 교환에 대한 Wikipedia [페이지에](https://en.wikipedia.org/wiki/Diffie–Hellman_key_exchange#Cryptographic_explanation) 좋은 설명이 있지만 자세한 설명은 이 자습서의 범위를 벗어납니다.

이와 같은 보안 웹사이트에서 통신할 때 브라우저와 서버는 동일한 원칙을 사용하여 보안 통신을 설정합니다.

1. 귀하의 브라우저는 서버로부터 정보를 **요청합니다 .**
2. 브라우저와 서버는 공개 키를 **교환합니다** .
3. 브라우저와 서버는 공유 개인 키를 **생성합니다 .**
4. 브라우저와 서버는 대칭 암호화를 통해 이 공유 키를 사용하여 메시지를 **암호화하고 해독합니다 .**

다행스럽게도 이러한 세부 정보를 구현할 필요가 없습니다. 클라이언트와 서버 통신을 안전하게 유지하는 데 도움이 되는 내장 및 타사 라이브러리가 많이 있습니다.



## 현실 세계에서 HTTPS는 어떤가요?

암호화에 대한 이 모든 정보가 주어지면 조금 축소하여 실제 세계에서 Python HTTPS 응용 프로그램이 실제로 작동하는 방식에 대해 이야기해 봅시다. 암호화는 이야기의 절반에 불과합니다. 보안 웹 사이트를 방문할 때 필요한 두 가지 주요 구성 요소가 있습니다.

1. **암호화는** 일반 텍스트를 암호문으로 변환하고 그 반대로 변환합니다.
2. **인증은** 사람이나 사물이 자신이 말하는 사람인지 확인합니다.

암호화가 작동하는 방식에 대해 광범위하게 들었지만 인증은 어떻습니까? **실제 세계에서 인증을 이해하려면 공개 키 인프라에** 대해 알아야 합니다 . PKI는 보안 에코시스템에 **인증서** 라는 또 다른 중요한 개념을 도입합니다 .

인증서는 인터넷의 여권과 같습니다. 컴퓨터 세계의 대부분의 것과 마찬가지로 그것들은 파일에 있는 데이터 덩어리일 뿐입니다. 일반적으로 인증서에는 다음 정보가 포함됩니다.

- **발급 대상:** 인증서를 소유한 사람을 식별합니다.
- **발급자:** 인증서를 발급한 사람을 식별합니다.
- **유효 기간:** 인증서가 유효한 기간을 식별합니다.

여권과 마찬가지로 인증서는 특정 기관에서 생성하고 인식하는 경우에만 정말 유용합니다. 브라우저가 인터넷에서 방문하는 모든 사이트의 모든 단일 인증서에 대해 아는 것은 비실용적입니다. **대신 PKI는 인증 기관(CA)** 이라는 개념에 의존합니다 .

인증 기관은 인증서 발급을 담당합니다. PKI에서 신뢰할 수 있는 제3자(TTP)로 간주됩니다. 기본적으로 이러한 엔터티는 인증서에 대한 유효한 기관 역할을 합니다. 당신이 다른 나라를 방문하고 싶고 당신의 모든 정보가 적힌 여권을 가지고 있다고 가정해 봅시다. 외국의 출입국 관리관은 귀하의 여권에 유효한 정보가 포함되어 있는지 어떻게 알 수 있습니까?

모든 정보를 직접 작성하고 서명하는 경우 방문하려는 각 국가의 각 이민국 직원은 귀하를 개인적으로 알고 정보가 실제로 정확함을 증명할 수 있어야 합니다.

이를 처리하는 또 다른 방법은 모든 정보를 **신뢰할 수 있는 제3자(TTP)** 로 보내는 것입니다 . TTP는 귀하가 제공한 정보를 철저히 조사하고 귀하의 주장을 확인한 다음 여권에 서명합니다. 출입국 관리관은 신뢰할 수 있는 제3자만 알면 되므로 훨씬 더 실용적인 것으로 판명되었습니다.

TTP 시나리오는 인증서가 실제로 처리되는 방식입니다. 프로세스는 다음과 같습니다.

1. **인증서 서명 요청(CSR) 생성:** 이는 비자 정보를 작성하는 것과 같습니다.
2. **신뢰할 수 있는 제3자(TTP)에게 CSR 보내기:** 이는 비자 신청 사무소에 정보를 보내는 것과 같습니다.
3. **정보 확인:** 어떻게든 TTP는 사용자가 제공한 정보를 확인해야 합니다. 예를 들어 [Amazon이 소유권을 확인하는 방법을](https://docs.aws.amazon.com/acm/latest/userguide/gs-acm-validate-dns.html) 참조하십시오 .
4. **공개 키 생성:** TTP가 CSR에 서명합니다. 이는 TTP가 비자에 서명하는 것과 같습니다.
5. **검증된 공개 키 발급:** 비자를 우편으로 받는 것과 같습니다.

CSR은 개인 키에 암호화되어 연결되어 있습니다. 이와 같이 공개 키, 개인 키 및 인증 기관의 세 가지 정보는 모두 어떤 식으로든 관련되어 있습니다. [이것은 신뢰 체인](https://en.wikipedia.org/wiki/Chain_of_trust) 으로 알려진 것을 생성하므로 이제 신원을 확인하는 데 사용할 수 있는 유효한 인증서를 갖게 됩니다.

대부분의 경우 이것은 웹사이트 소유자의 책임입니다. 웹사이트 소유자는 이 모든 단계를 따릅니다. 이 프로세스가 끝나면 인증서에 다음과 같이 표시됩니다.

> 때때로 나는 따라 `A`_`B``X``Y`

이 문장은 인증서가 실제로 알려주는 전부입니다. 변수는 다음과 같이 채울 수 있습니다.

- **A** 는 유효한 시작 날짜 및 시간입니다.
- **B** 는 유효한 종료 날짜 및 시간입니다.
- **X** 는 서버 이름입니다.
- **Y** 는 인증 기관의 이름입니다.

기본적으로 이것은 인증서가 설명하는 모든 것입니다. 즉, 인증서를 갖는다는 것은 반드시 당신이 당신이 말하는 당신이라는 것을 의미하는 것이 아니라 당신이 말하는 당신 자신이라는 데 *동의*`Y` 해야 한다는 것입니다. 여기에서 신뢰할 수 있는 제3자의 "신뢰할 수 있는" 부분이 필요합니다.

모든 사람이 HTTPS 핸드셰이크에 만족하려면 클라이언트와 서버 간에 TTP를 공유해야 합니다. 브라우저에는 많은 인증 기관이 자동으로 설치된 상태로 제공됩니다. 확인하려면 다음 단계를 따르세요.

- **Chrome:** *설정 > 고급 > 개인 정보 및 보안 > 인증서 관리 > 기관으로* 이동합니다 .
- **Firefox:** *설정 > 기본 설정 > 개인 정보 및 보안 > 인증서 보기 > 기관* 으로 이동합니다 .

실제 환경에서 Python HTTPS 애플리케이션을 만드는 데 필요한 인프라를 다룹니다. 다음 섹션에서는 이러한 개념을 자신의 코드에 적용합니다. 가장 일반적인 예를 살펴보고 자신만의 비밀 다람쥐 인증 기관이 될 것입니다!



## Python HTTPS 애플리케이션은 어떻게 생겼습니까?

이제 Python HTTPS 애플리케이션을 만드는 데 필요한 기본 부분을 이해했으므로 이전의 애플리케이션에 모든 조각을 하나씩 함께 묶을 차례입니다. 이렇게 하면 서버와 클라이언트 간의 통신이 안전합니다.

자신의 컴퓨터에 전체 PKI 인프라를 설정할 수 있으며 이것이 바로 이 섹션에서 수행할 작업입니다. 생각보다 어렵지 않으니 걱정하지 마세요! 실제 인증 기관이 되는 것은 아래 단계를 수행하는 것보다 훨씬 어렵지만 읽을 내용은 자체 CA를 실행하는 데 필요한 전부입니다.



### 인증 기관 되기

인증 기관은 매우 중요한 공개 및 개인 키 쌍에 지나지 않습니다. CA가 되려면 공개 및 개인 키 쌍을 생성하기만 하면 됩니다.

**참고:** 많은 회사가 이 프로세스를 따랐지만 대중이 사용할 수 있는 CA가 되는 것은 매우 힘든 프로세스입니다. 그러나이 튜토리얼이 끝날 때까지 그러한 회사 중 하나가 아닐 것입니다!

[초기 공개 및 개인 키 쌍은 자체 서명된 인증서](https://cryptography.io/en/latest/x509/tutorial/#creating-a-self-signed-certificate) 입니다 . 초기 비밀을 생성하고 있으므로 실제로 CA가 되려면 이 개인 키가 안전한 것이 매우 중요합니다. 누군가가 CA의 공개 및 개인 키 쌍에 액세스할 수 있으면 완전히 유효한 인증서를 생성할 수 있으며 CA 신뢰를 중지하는 것 외에는 문제를 감지하기 위해 할 수 있는 일이 없습니다.

해당 경고를 제거하면 즉시 인증서를 생성할 수 있습니다. 먼저 개인 키를 생성해야 합니다. 다음을 다음 파일에 붙여넣습니다 `pki_helpers.py`.

```
 1# pki_helpers.py
 2from cryptography.hazmat.backends import default_backend
 3from cryptography.hazmat.primitives import serialization
 4from cryptography.hazmat.primitives.asymmetric import rsa
 5
 6def generate_private_key(filename: str, passphrase: str):
 7    private_key = rsa.generate_private_key(
 8        public_exponent=65537, key_size=2048, backend=default_backend()
 9    )
10
11    utf8_pass = passphrase.encode("utf-8")
12    algorithm = serialization.BestAvailableEncryption(utf8_pass)
13
14    with open(filename, "wb") as keyfile:
15        keyfile.write(
16            private_key.private_bytes(
17                encoding=serialization.Encoding.PEM,
18                format=serialization.PrivateFormat.TraditionalOpenSSL,
19                encryption_algorithm=algorithm,
20            )
21        )
22
23    return private_key
```

`generate_private_key()`[RSA를](https://en.wikipedia.org/wiki/RSA_(cryptosystem)) 사용하여 개인 키를 생성합니다 . 코드 분석은 다음과 같습니다.

- **2~4행은** 함수가 작동하는 데 필요한 라이브러리를 가져옵니다.
- **7~9행은** RSA를 사용하여 개인 키를 생성합니다. 마법의 숫자 `65537`와 `2048`두 가지 가능한 값입니다. [이유에 대해 자세히 알아보거나](https://cryptography.io/en/latest/hazmat/primitives/asymmetric/rsa/#cryptography.hazmat.primitives.asymmetric.rsa.generate_private_key) 이 수치가 유용하다고 믿을 수 있습니다.
- **11~12행은** 개인 키에 사용할 암호화 알고리즘을 설정합니다.
- **14행에서 21행은** 개인 키를 디스크의 지정된 위치에 기록합니다 `filename`. 이 파일은 제공된 암호를 사용하여 암호화됩니다.

자체 CA가 되기 위한 다음 단계는 자체 서명된 공개 키를 생성하는 것입니다. 인증서 서명 요청(CSR)을 우회하고 공개 키를 즉시 구축할 수 있습니다. 다음을 붙여넣습니다 `pki_helpers.py`.

```
 1# pki_helpers.py
 2from datetime import datetime, timedelta
 3from cryptography import x509
 4from cryptography.x509.oid import NameOID
 5from cryptography.hazmat.primitives import hashes
 6
 7def generate_public_key(private_key, filename, **kwargs):
 8    subject = x509.Name(
 9        [
10            x509.NameAttribute(NameOID.COUNTRY_NAME, kwargs["country"]),
11            x509.NameAttribute(
12                NameOID.STATE_OR_PROVINCE_NAME, kwargs["state"]
13            ),
14            x509.NameAttribute(NameOID.LOCALITY_NAME, kwargs["locality"]),
15            x509.NameAttribute(NameOID.ORGANIZATION_NAME, kwargs["org"]),
16            x509.NameAttribute(NameOID.COMMON_NAME, kwargs["hostname"]),
17        ]
18    )
19
20    # Because this is self signed, the issuer is always the subject
21    issuer = subject
22
23    # This certificate is valid from now until 30 days
24    valid_from = datetime.utcnow()
25    valid_to = valid_from + timedelta(days=30)
26
27    # Used to build the certificate
28    builder = (
29        x509.CertificateBuilder()
30        .subject_name(subject)
31        .issuer_name(issuer)
32        .public_key(private_key.public_key())
33        .serial_number(x509.random_serial_number())
34        .not_valid_before(valid_from)
35        .not_valid_after(valid_to)
36        .add_extension(x509.BasicConstraints(ca=True,
37            path_length=None), critical=True)
38    )
39
40    # Sign the certificate with the private key
41    public_key = builder.sign(
42        private_key, hashes.SHA256(), default_backend()
43    )
44
45    with open(filename, "wb") as certfile:
46        certfile.write(public_key.public_bytes(serialization.Encoding.PEM))
47
48    return public_key
```

`generate_public_key()`여기에 자체 서명된 공개 키를 생성하는 새로운 기능이 있습니다 . 이 코드의 작동 방식은 다음과 같습니다.

- **2~5행은** 함수가 작동하는 데 필요한 가져오기입니다.
- **8행에서 18행은** 인증서 주제에 대한 정보를 구성합니다.
- **21행은** 자체 서명된 인증서이므로 동일한 발행자와 주제를 사용합니다.
- **24~25행은** 이 공개 키가 유효한 시간 범위를 나타냅니다. 이 경우 30일입니다.
- **28~38행은** 서명이 필요한 공개 키 빌더 객체에 필요한 모든 정보를 추가합니다.
- **41~43행은** 개인 키로 공개 키에 서명합니다.
- **45행에서 46행은** 공개 키를 `filename`.

이 두 함수를 사용하여 Python에서 매우 빠르게 개인 및 공개 키 쌍을 생성할 수 있습니다.

\>>>

```
>>> from pki_helpers import generate_private_key, generate_public_key
>>> private_key = generate_private_key("ca-private-key.pem", "secret_password")
>>> private_key
<cryptography.hazmat.backends.openssl.rsa._RSAPrivateKey object at 0x7ffbb292bf90>
>>> generate_public_key(
...   private_key,
...   filename="ca-public-key.pem",
...   country="US",
...   state="Maryland",
...   locality="Baltimore",
...   org="My CA Company",
...   hostname="my-ca.com",
... )
<Certificate(subject=<Name(C=US,ST=Maryland,L=Baltimore,O=My CA Company,CN=logan-ca.com)>, ...)>
```

에서 헬퍼 함수를 가져온 후 `pki_helpers`먼저 개인 키를 생성하고 파일에 저장합니다 `ca-private-key.pem`. 그런 다음 해당 개인 키를 에 전달하여 `generate_public_key()`공개 키를 생성합니다. 이제 디렉터리에 두 개의 파일이 있어야 합니다.

```
$ ls ca*
ca-private-key.pem ca-public-key.pem
```

축하해요! 이제 인증 기관이 될 수 있습니다.

### 서버 신뢰

**서버를 신뢰할 수 있는 첫 번째 단계는 인증서 서명 요청(CSR)을** 생성하는 것입니다 . [현실 세계에서 CSR은 Verisign](https://www.verisign.com/) 또는 [Let's Encrypt](https://letsencrypt.org/) 와 같은 실제 인증 기관으로 전송됩니다 . 이 예에서는 방금 만든 CA를 사용합니다.

CSR을 생성하기 위한 [코드를](https://cryptography.io/en/latest/x509/tutorial/#creating-a-certificate-signing-request-csr)`pki_helpers.py` 위의 파일 에 붙여넣습니다 .

```
 1# pki_helpers.py
 2def generate_csr(private_key, filename, **kwargs):
 3    subject = x509.Name(
 4        [
 5            x509.NameAttribute(NameOID.COUNTRY_NAME, kwargs["country"]),
 6            x509.NameAttribute(
 7                NameOID.STATE_OR_PROVINCE_NAME, kwargs["state"]
 8            ),
 9            x509.NameAttribute(NameOID.LOCALITY_NAME, kwargs["locality"]),
10            x509.NameAttribute(NameOID.ORGANIZATION_NAME, kwargs["org"]),
11            x509.NameAttribute(NameOID.COMMON_NAME, kwargs["hostname"]),
12        ]
13    )
14
15    # Generate any alternative dns names
16    alt_names = []
17    for name in kwargs.get("alt_names", []):
18        alt_names.append(x509.DNSName(name))
19    san = x509.SubjectAlternativeName(alt_names)
20
21    builder = (
22        x509.CertificateSigningRequestBuilder()
23        .subject_name(subject)
24        .add_extension(san, critical=False)
25    )
26
27    csr = builder.sign(private_key, hashes.SHA256(), default_backend())
28
29    with open(filename, "wb") as csrfile:
30        csrfile.write(csr.public_bytes(serialization.Encoding.PEM))
31
32    return csr
```

대부분의 경우 이 코드는 원래 공개 키를 생성한 방법과 동일합니다. 주요 차이점은 다음과 같습니다.

- **16-19행은** 인증서에 유효한 대체 DNS 이름을 설정합니다.
- **21~25행은** 다른 빌더 객체를 생성하지만 이전과 동일한 기본 원칙이 적용됩니다. CSR에 필요한 모든 속성을 구축하고 있습니다.
- **27행은** 개인 키로 CSR에 서명합니다.
- **29~30행은** CSR을 PEM 형식으로 디스크에 기록합니다.

CSR을 생성하려면 먼저 개인 키가 필요합니다. `generate_private_key()`운 좋게도 CA의 개인 키를 만들 때부터 동일한 것을 사용할 수 있습니다 . 위의 함수와 정의된 이전 방법을 사용하여 다음을 수행할 수 있습니다.

\>>>

```
>>> from pki_helpers import generate_csr, generate_private_key
>>> server_private_key = generate_private_key(
...   "server-private-key.pem", "serverpassword"
... )
>>> server_private_key
<cryptography.hazmat.backends.openssl.rsa._RSAPrivateKey object at 0x7f6adafa3050>
>>> generate_csr(
...   server_private_key,
...   filename="server-csr.pem",
...   country="US",
...   state="Maryland",
...   locality="Baltimore",
...   org="My Company",
...   alt_names=["localhost"],
...   hostname="my-site.com",
... )
<cryptography.hazmat.backends.openssl.x509._CertificateSigningRequest object at 0x7f6ad5372210>
```

콘솔에서 이 단계를 실행하면 두 개의 새 파일이 생성됩니다.

1. **`server-private-key.pem`:** 서버의 개인 키
2. **`server-csr.pem`:** 서버의 CSR

콘솔에서 새 CSR 및 개인 키를 볼 수 있습니다.

```
$ ls server*.pem
server-csr.pem  server-private-key.pem
```

이 두 문서를 손에 들고 이제 키 서명 프로세스를 시작할 수 있습니다. 일반적으로 이 단계에서 많은 검증이 발생합니다. 실제 세계에서 CA는 귀하가 소유하고 있는지 확인 `my-site.com`하고 다양한 방법으로 이를 증명하도록 요청할 것입니다.

이 경우 귀하가 CA이므로 골치아픈 문제를 해결하고 검증된 공개 키를 직접 생성할 수 있습니다. 이를 위해 파일에 다른 함수를 추가합니다 `pki_helpers.py`.

```
 1# pki_helpers.py
 2def sign_csr(csr, ca_public_key, ca_private_key, new_filename):
 3    valid_from = datetime.utcnow()
 4    valid_until = valid_from + timedelta(days=30)
 5
 6    builder = (
 7        x509.CertificateBuilder()
 8        .subject_name(csr.subject)
 9        .issuer_name(ca_public_key.subject)
10        .public_key(csr.public_key())
11        .serial_number(x509.random_serial_number())
12        .not_valid_before(valid_from)
13        .not_valid_after(valid_until)
14    )
15
16    for extension in csr.extensions:
17        builder = builder.add_extension(extension.value, extension.critical)
18
19    public_key = builder.sign(
20        private_key=ca_private_key,
21        algorithm=hashes.SHA256(),
22        backend=default_backend(),
23    )
24
25    with open(new_filename, "wb") as keyfile:
26        keyfile.write(public_key.public_bytes(serialization.Encoding.PEM))
```

`generate_public_key()`이 코드는 파일 과 매우 유사합니다 `generate_ca.py`. 사실, 그것들은 거의 동일합니다. 주요 차이점은 다음과 같습니다.

- **8~9행은** CSR의 주체 이름을 기반으로 하는 반면 발급자는 인증 기관을 기반으로 합니다.
- **이번에는 10행이** CSR에서 공개 키를 가져옵니다. CA임을 지정하는 줄은 `generate_public_key()`정의 끝에서 `builder`제거되었습니다.
- **16~17행은** CSR에 설정된 모든 확장을 복사합니다.
- **20행은** CA의 개인 키로 공개 키에 서명합니다.

다음 단계는 Python 콘솔을 실행하고 `sign_csr()`. CSR과 CA의 개인 및 공개 키를 로드해야 합니다. CSR을 로드하여 시작합니다.

\>>>

```
>>> from cryptography import x509
>>> from cryptography.hazmat.backends import default_backend
>>> csr_file = open("server-csr.pem", "rb")
>>> csr = x509.load_pem_x509_csr(csr_file.read(), default_backend())
>>> csr
<cryptography.hazmat.backends.openssl.x509._CertificateSigningRequest object at 0x7f68ae289150>
```

이 코드 섹션에서는 파일을 열고 개체를 만드는 데 `server-csr.pem`사용합니다 . 다음으로 CA의 공개 키를 로드해야 합니다.`x509.load_pem_x509_csr()``csr`

\>>>

```
>>> ca_public_key_file = open("ca-public-key.pem", "rb")
>>> ca_public_key = x509.load_pem_x509_certificate(
...   ca_public_key_file.read(), default_backend()
... )
>>> ca_public_key
<Certificate(subject=<Name(C=US,ST=Maryland,L=Baltimore,O=My CA Company,CN=logan-ca.com)>, ...)>
```

`ca_public_key`다시 한 번 에서 사용할 수 있는 개체를 만들었습니다 `sign_csr()`. 모듈 은 도움 `x509`이 되었습니다 `load_pem_x509_certificate()`. 마지막 단계는 CA의 개인 키를 로드하는 것입니다.

\>>>

```
>>> from getpass import getpass
>>> from cryptography.hazmat.primitives import serialization
>>> ca_private_key_file = open("ca-private-key.pem", "rb")
>>> ca_private_key = serialization.load_pem_private_key(
...   ca_private_key_file.read(),
...   getpass().encode("utf-8"),
...   default_backend(),
... )
Password:
>>> private_key
<cryptography.hazmat.backends.openssl.rsa._RSAPrivateKey object at 0x7f68a85ade50>
```

이 코드는 개인 키를 로드합니다. 이전에 개인 키가 지정한 비밀번호를 사용하여 암호화되었음을 상기하십시오. 이 세 가지 구성 요소를 사용하여 이제 CSR에 서명하고 확인된 공개 키를 생성할 수 있습니다.

\>>>

```
>>> from pki_helpers import sign_csr
>>> sign_csr(csr, ca_public_key, ca_private_key, "server-public-key.pem")
```

이를 실행한 후 디렉터리에 세 개의 서버 키 파일이 있어야 합니다.

```
$ ls server*.pem
server-csr.pem  server-private-key.pem  server-public-key.pem
```

아휴! 그것은 꽤 많은 일이었습니다. 좋은 소식은 이제 개인 키와 공개 키 쌍이 있으므로 사용을 시작하기 위해 서버 코드를 변경할 필요가 없다는 것입니다.

원본 `server.py`파일을 사용하여 다음 명령을 실행하여 새로운 Python HTTPS 애플리케이션을 시작합니다.

```
$ uwsgi \
    --master \
    --https localhost:5683,\
            logan-site.com-public-key.pem,\
            logan-site.com-private-key.pem \
    --mount /=server:app
```

축하해요! 이제 자체 인증 기관에서 서명한 개인-공개 키 쌍으로 실행되는 Python HTTPS 지원 서버가 있습니다!

**참고:** Python HTTPS 인증 등식에는 또 다른 측면이 있습니다. 바로 **클라이언트** 입니다 . 클라이언트 인증서에 대한 인증서 확인도 설정할 수 있습니다. 이를 위해서는 약간의 작업이 더 필요하며 실제로 기업 외부에서는 볼 수 없습니다. 그러나 클라이언트 인증은 매우 강력한 도구가 될 수 있습니다.

이제 남은 일은 서버에 쿼리하는 것입니다. 먼저 코드를 일부 변경해야 합니다 `client.py`.

```
# client.py
import os
import requests

def get_secret_message():
    response = requests.get("https://localhost:5683")
    print(f"The secret message is {response.text}")

if __name__ == "__main__":
    get_secret_message()
```

이전 코드에서 유일한 변경 사항은 에서 `http`입니다 `https`. 이 코드를 실행하려고 하면 오류가 발생합니다.

```
$ python client.py
...
requests.exceptions.SSLError: \
    HTTPSConnectionPool(host='localhost', port=5683): \
    Max retries exceeded with url: / (Caused by \
    SSLError(SSLCertVerificationError(1, \
    '[SSL: CERTIFICATE_VERIFY_FAILED] \
    certificate verify failed: unable to get local issuer \
    certificate (_ssl.c:1076)')))
```

그것은 매우 불쾌한 오류 메시지입니다! 여기서 중요한 부분은 메시지입니다 `certificate verify failed: unable to get local issuer`. 이 단어들은 이제 당신에게 더 친숙할 것입니다. 본질적으로 다음과 같이 말합니다.

> localhost:5683이 나에게 인증서를 주었다. 나는 그것이 나에게 준 인증서의 발급자를 확인했고 내가 아는 모든 인증 기관에 따르면 그 발급자는 그들 중 하나가 아닙니다.

브라우저로 웹사이트를 탐색하려고 하면 비슷한 메시지가 표시됩니다.

[![Chrome 인증서 경고](https://files.realpython.com/media/unsafe-chrome.dedcf7161bb6.png)](https://files.realpython.com/media/unsafe-chrome.dedcf7161bb6.png)

이 메시지를 피하려면 `requests`인증 기관에 대해 알려야 합니다! `ca-public-key.pem`이전에 생성한 파일 에서 요청을 지정하기만 하면 됩니다 .

```
# client.py
def get_secret_message():
    response = requests.get("http://localhost:5683", verify="ca-public-key.pem")
    print(f"The secret message is {response.text}")
```

그런 다음 다음을 성공적으로 실행할 수 있어야 합니다.

```
$ python client.py
The secret message is fluffy tail
```

멋진! 완벽하게 작동하는 Python HTTPS 서버를 만들고 성공적으로 쿼리했습니다. 당신과 Secret Squirrels는 이제 당신이 행복하고 안전하게 교환할 수 있는 메시지를 가지고 있습니다!

## 결론

이 자습서에서는 오늘날 인터넷에서 **보안 통신** 의 핵심 토대 중 일부를 배웠습니다 . 이제 이러한 구성 요소를 이해했으므로 더 우수하고 안전한 개발자가 될 것입니다.

이 자습서를 통해 다음과 같은 여러 항목을 이해했습니다.

- 암호화
- HTTPS 및 TLS
- 공개 키 인프라
- 인증서

이 정보에 관심이 있다면 운이 좋은 것입니다! 모든 레이어와 관련된 모든 뉘앙스의 표면을 간신히 긁었습니다. 보안 세계는 끊임없이 진화하고 있으며 새로운 기술과 취약점이 항상 발견되고 있습니다. [여전히 질문이 있는 경우 아래 댓글 섹션이나 Twitter](https://twitter.com/realpython) 에서 언제든지 문의하십시오 .