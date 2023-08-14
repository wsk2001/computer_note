# Socket Programming in Python (Guide)

출처: https://realpython.com/python-sockets/

able of Contents

- [Background](https://realpython.com/python-sockets/#background)
- [Socket API Overview](https://realpython.com/python-sockets/#socket-api-overview)
- [TCP Sockets](https://realpython.com/python-sockets/#tcp-sockets)
- Echo Client and Server
  - [Echo Server](https://realpython.com/python-sockets/#echo-server)
  - [Echo Client](https://realpython.com/python-sockets/#echo-client)
  - [Running the Echo Client and Server](https://realpython.com/python-sockets/#running-the-echo-client-and-server)
  - [Viewing Socket State](https://realpython.com/python-sockets/#viewing-socket-state)
- [Communication Breakdown](https://realpython.com/python-sockets/#communication-breakdown)
- [Handling Multiple Connections](https://realpython.com/python-sockets/#handling-multiple-connections)
- Multi-Connection Client and Server
  - [Multi-Connection Server](https://realpython.com/python-sockets/#multi-connection-server)
  - [Multi-Connection Client](https://realpython.com/python-sockets/#multi-connection-client)
  - [Running the Multi-Connection Client and Server](https://realpython.com/python-sockets/#running-the-multi-connection-client-and-server)
- Application Client and Server
  - [Application Protocol Header](https://realpython.com/python-sockets/#application-protocol-header)
  - [Sending an Application Message](https://realpython.com/python-sockets/#sending-an-application-message)
  - [Application Message Class](https://realpython.com/python-sockets/#application-message-class)
  - [Running the Application Client and Server](https://realpython.com/python-sockets/#running-the-application-client-and-server)
- Troubleshooting
  - [ping](https://realpython.com/python-sockets/#ping)
  - [netstat](https://realpython.com/python-sockets/#netstat)
  - [Windows](https://realpython.com/python-sockets/#windows)
  - [Wireshark](https://realpython.com/python-sockets/#wireshark)
- Reference
  - [Python Documentation](https://realpython.com/python-sockets/#python-documentation)
  - [Errors](https://realpython.com/python-sockets/#errors)
  - [Socket Address Families](https://realpython.com/python-sockets/#socket-address-families)
  - [Using Hostnames](https://realpython.com/python-sockets/#using-hostnames)
  - [Blocking Calls](https://realpython.com/python-sockets/#blocking-calls)
  - [Closing Connections](https://realpython.com/python-sockets/#closing-connections)
  - [Byte Endianness](https://realpython.com/python-sockets/#byte-endianness)
- [Conclusion](https://realpython.com/python-sockets/#conclusion)



지금 보기 이 자습서에는 Real Python 팀에서 만든 관련 비디오 과정이 있습니다. 작성된 튜토리얼과 함께 시청하여 이해를 심화하십시오: [Python에서의 소켓 프로그래밍 1부](https://realpython.com/courses/python-sockets-part-1/)



소켓과 소켓 API는 네트워크를 통해 메시지를 보내는 데 사용됩니다. 프로세스 간 통신(IPC)의 한 형태를 제공합니다. 네트워크는 컴퓨터에 대한 논리적 로컬 네트워크이거나 다른 네트워크에 대한 자체 연결을 통해 외부 네트워크에 물리적으로 연결된 네트워크일 수 있습니다. 확실한 예는 ISP를 통해 연결하는 인터넷입니다.



이 튜토리얼에서는 다음을 생성합니다.

간단한 소켓 서버 및 클라이언트
여러 연결을 동시에 처리하는 향상된 버전
고유한 사용자 지정 헤더 및 콘텐츠가 포함된 본격적인 소켓 응용 프로그램처럼 작동하는 서버-클라이언트 응용 프로그램
이 튜토리얼이 끝나면 파이썬 소켓 모듈의 주요 기능과 메서드를 사용하여 클라이언트-서버 애플리케이션을 작성하는 방법을 이해하게 될 것입니다. 사용자 지정 클래스를 사용하여 엔드포인트 간에 메시지와 데이터를 보내는 방법을 알게 될 것입니다. 이 클래스를 사용하여 자체 애플리케이션을 구축하고 활용할 수 있습니다.

이 자습서의 예제에는 Python 3.6 이상이 필요하며 Python 3.10을 사용하여 테스트되었습니다. 이 튜토리얼을 최대한 활용하려면 소스 코드를 다운로드하고 읽는 동안 참조할 수 있도록 준비하는 것이 가장 좋습니다.

> 소스 코드 가져오기: [여기](https://realpython.com/bonus/python-sockets-source-code/)를 클릭하여 이 자습서의 예제에 사용할 소스 코드를 가져옵니다.

`materials-python-sockets-tutorial.zip`



네트워킹과 소켓은 큰 주제입니다. 그들에 대한 리터럴 볼륨이 작성되었습니다. 소켓이나 네트워킹을 처음 사용하는 경우 모든 용어와 조각에 압도당하는 느낌이 드는 것은 완전히 정상입니다.

하지만 낙담하지 마십시오. 이 튜토리얼은 당신을 위한 것입니다! Python과 관련된 모든 것과 마찬가지로 한 번에 조금씩 배울 수 있습니다. 이 기사를 즐겨찾기에 추가하고 다음 섹션을 볼 준비가 되면 다시 방문하세요.



### Background

소켓은 오랜 역사를 가지고 있습니다. 그것들의 사용은 1971년 ARPANET에서 시작되었고 나중에 Berkeley 소켓이라고 하는 1983년에 출시된 BSD(Berkeley Software Distribution) 운영 체제의 API가 되었습니다.

1990년대에 인터넷이 월드 와이드 웹과 함께 도약했을 때 네트워크 프로그래밍도 마찬가지였습니다. 웹 서버와 브라우저는 새로 연결된 네트워크를 활용하고 소켓을 사용하는 유일한 응용 프로그램이 아닙니다. 모든 유형과 크기의 클라이언트-서버 응용 프로그램이 널리 사용되었습니다.

오늘날 소켓 API에서 사용하는 기본 프로토콜은 수년에 걸쳐 발전하고 새로운 프로토콜이 개발되었지만 하위 수준 API는 동일하게 유지되었습니다.

소켓 응용 프로그램의 가장 일반적인 유형은 한쪽이 서버 역할을 하고 클라이언트의 연결을 기다리는 클라이언트-서버 응용 프로그램입니다. 이것이 이 자습서에서 만들 애플리케이션 유형입니다. 보다 구체적으로 Berkeley 또는 BSD 소켓이라고도 하는 인터넷 소켓용 소켓 API에 중점을 둘 것입니다. 동일한 호스트의 프로세스 간 통신에만 사용할 수 있는 Unix 도메인 소켓도 있습니다.



### Socket API Overview

Python의 소켓 모듈은 Berkeley 소켓 API에 대한 인터페이스를 제공합니다. 이 튜토리얼에서 사용할 모듈입니다.

이 모듈의 기본 소켓 API 함수 및 메서드는 다음과 같습니다.

- socket()
- bind()
- listen()
- accept()
- connect()
- connect_ex()
- send()
- recv()
- close()

Python은 C에 대응하는 시스템 호출에 직접 매핑되는 편리하고 일관된 API를 제공합니다. 다음 섹션에서는 이들을 함께 사용하는 방법을 배웁니다.

표준 라이브러리의 일부로 Python에는 이러한 하위 수준 소켓 함수를 더 쉽게 사용할 수 있는 클래스도 있습니다. 이 튜토리얼에서는 다루지 않지만 네트워크 서버용 프레임워크인 socketserver 모듈을 확인할 수 있습니다. 또한 HTTP 및 SMTP와 같은 더 높은 수준의 인터넷 프로토콜을 구현하는 많은 모듈이 있습니다. 개요는 인터넷 프로토콜 및 지원을 참조하십시오.



### TCP Sockets

socket.socket()을 사용하여 소켓 객체를 만들고 소켓 유형을 socket.SOCK_STREAM으로 지정합니다. 그렇게 할 때 사용되는 기본 프로토콜은 전송 제어 프로토콜(TCP)입니다. 이것은 좋은 기본값이며 아마도 당신이 원하는 것일 것입니다.

왜 TCP를 사용해야 합니까? 전송 제어 프로토콜(TCP):

- **신뢰할 수 있음:** 네트워크에서 삭제된 패킷은 발신자에 의해 감지되고 재전송됩니다.
- **순서대로 데이터 전달:** 송신자가 작성한 순서대로 애플리케이션에서 데이터를 읽습니다.

반대로 socket.SOCK_DGRAM으로 생성된 UDP(User Datagram Protocol) 소켓은 신뢰할 수 없으며 수신자가 읽은 데이터가 발신자의 쓰기와 순서가 다를 수 있습니다.

이것이 왜 중요한가요? 네트워크는 최선의 전달 시스템입니다. 귀하의 데이터가 목적지에 도달하거나 귀하에게 전송된 것을 수신할 것이라는 보장은 없습니다.

라우터 및 스위치와 같은 네트워크 장치는 사용 가능한 대역폭이 한정되어 있으며 고유한 시스템 제한이 있습니다. 클라이언트 및 서버와 마찬가지로 CPU, 메모리, 버스 및 인터페이스 패킷 버퍼가 있습니다. TCP는 네트워크를 통해 통신할 때 항상 발생하는 패킷 손실, 잘못된 데이터 도착 및 기타 함정에 대해 걱정할 필요가 없도록 해줍니다.

이를 더 잘 이해하려면 TCP에 대한 소켓 API 호출 및 데이터 흐름의 순서를 확인하십시오.

[
![TCP socket flow](https://files.realpython.com/media/sockets-tcp-flow.1da426797e37.jpg)](https://files.realpython.com/media/sockets-tcp-flow.1da426797e37.jpg)TCP Socket Flow ([Image source](https://commons.wikimedia.org/wiki/File:InternetSocketBasicDiagram_zhtw.png))

왼쪽 열은 서버를 나타냅니다. 오른쪽에는 클라이언트가 있습니다.

왼쪽 상단 열에서 시작하여 서버가 '수신' 소켓을 설정하기 위해 수행하는 API 호출에 유의하십시오.

- socket()
- bind()
- listen()
- accept()

listen() 소켓은 이름에서 알 수 있는 대로 수행합니다. 클라이언트의 연결을 수신 대기합니다. 클라이언트가 연결되면 서버는 accept()를 호출하여 연결을 수락하거나 완료합니다.

클라이언트는 .connect()를 호출하여 서버에 대한 연결을 설정하고 3방향 핸드셰이크를 시작합니다. 핸드셰이크 단계는 연결의 각 측면이 네트워크에서 도달할 수 있도록 보장하기 때문에 중요합니다. 즉, 클라이언트가 서버에 도달할 수 있고 그 반대도 가능합니다. 하나의 호스트, 클라이언트 또는 서버만 다른 호스트, 클라이언트 또는 서버에 도달할 수 있습니다.

중간에는 .send() 및 .recv()에 대한 호출을 사용하여 클라이언트와 서버 간에 데이터가 교환되는 왕복 섹션이 있습니다.

아래쪽에서 클라이언트와 서버는 각각의 소켓을 닫습니다.



### Echo Client and Server

이제 소켓 API에 대한 개요와 클라이언트와 서버가 통신하는 방법을 살펴보았으므로 첫 번째 클라이언트와 서버를 만들 준비가 되었습니다. 간단한 구현부터 시작합니다. 서버는 수신한 모든 내용을 클라이언트로 되돌려 보낼 것입니다.

#### Echo Server

``` py
# echo-server.py

import socket

HOST = "127.0.0.1"  # Standard loopback interface address (localhost)
PORT = 65432  # Port to listen on (non-privileged ports are > 1023)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        while True:
            data = conn.recv(1024)
            if not data:
                break
            conn.sendall(data)
```



> 참고: 지금 당장 위의 모든 내용을 이해하는 것에 대해 걱정하지 마십시오. 이 몇 줄의 코드에서 많은 일이 일어나고 있습니다. 이는 작동 중인 기본 서버를 볼 수 있는 시작점일 뿐입니다.
>
> 이 튜토리얼의 끝에 추가 리소스에 대한 추가 정보 및 링크가 있는 참조 섹션이 있습니다. 또한 자습서 전체에서 이러한 링크와 기타 유용한 링크를 찾을 수 있습니다.

자, API 호출에서 정확히 무슨 일이 일어나고 있습니까?

socket.socket()은 컨텍스트 관리자 유형을 지원하는 소켓 객체를 생성하므로 with 문에서 사용할 수 있습니다. s.close()를 호출할 필요가 없습니다.

`python`

``` py
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    pass  # Use the socket object without calling s.close().
```

socket()에 전달된 인수는 주소 패밀리 및 소켓 유형을 지정하는 데 사용되는 상수입니다. AF_INET은 IPv4용 인터넷 주소 계열입니다. SOCK_STREAM은 네트워크에서 메시지를 전송하는 데 사용되는 프로토콜인 TCP의 소켓 유형입니다.

.bind() 메서드는 소켓을 특정 네트워크 인터페이스 및 포트 번호와 연결하는 데 사용됩니다.

`python`

``` py
# echo-server.py

# ...

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    # ...
```

.bind()에 전달되는 값은 소켓의 주소 계열에 따라 다릅니다. 이 예에서는 socket.AF_INET(IPv4)을 사용하고 있습니다. 따라서 두 튜플(호스트, 포트)을 기대합니다.

host는 호스트 이름, IP 주소 또는 빈 문자열일 수 있습니다. IP 주소를 사용하는 경우 호스트는 IPv4 형식의 주소 문자열이어야 합니다. IP 주소 127.0.0.1은 루프백 인터페이스의 표준 IPv4 주소이므로 호스트의 프로세스만 서버에 연결할 수 있습니다. 빈 문자열을 전달하면 서버는 사용 가능한 모든 IPv4 인터페이스에서 연결을 수락합니다.

port는 클라이언트에서 연결을 수락할 TCP 포트 번호를 나타냅니다. 0이 예약되어 있으므로 1에서 65535 사이의 정수여야 합니다. 일부 시스템은 포트 번호가 1024 미만인 경우 수퍼유저 권한이 필요할 수 있습니다.

다음은 .bind()와 함께 호스트 이름을 사용하는 방법에 대한 참고 사항입니다.



> “IPv4/v6 소켓 주소의 호스트 부분에 호스트 이름을 사용하면 Python이 DNS 확인에서 반환된 첫 번째 주소를 사용하기 때문에 프로그램이 비결정적 동작을 보일 수 있습니다. 소켓 주소는 DNS 확인 결과 및/또는 호스트 구성에 따라 실제 IPv4/v6 주소로 다르게 확인됩니다. 결정적 동작을 위해 호스트 부분에 숫자 주소를 사용하십시오.” (원천)



이에 대한 자세한 내용은 호스트 이름 사용에서 나중에 배우게 됩니다. 지금은 호스트 이름을 사용할 때 이름 확인 프로세스에서 반환된 내용에 따라 다른 결과를 볼 수 있다는 점만 이해하십시오. 이러한 결과는 무엇이든 될 수 있습니다. 애플리케이션을 처음 실행하면 주소 10.1.2.3을 얻을 수 있습니다. 다음에 다른 주소인 192.168.0.1을 받게 됩니다. 세 번째로 172.16.7.8 등을 얻을 수 있습니다.

서버 예제에서 .listen()은 서버가 연결을 수락할 수 있도록 합니다. 서버를 '수신' 소켓으로 만듭니다.

`python`

``` py
# echo-server.py

# ...

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    conn, addr = s.accept()
    # ...
```

.listen() 메서드에는 백로그 매개변수가 있습니다. 새 연결을 거부하기 전에 시스템이 허용할 허용되지 않은 연결 수를 지정합니다. Python 3.5부터는 선택 사항입니다. 지정하지 않으면 기본 백로그 값이 선택됩니다.

서버가 동시에 많은 연결 요청을 받는 경우 백로그 값을 늘리면 보류 중인 연결에 대한 대기열의 최대 길이를 설정하여 도움이 될 수 있습니다. 최대값은 시스템에 따라 다릅니다. 예를 들어 Linux에서는 /proc/sys/net/core/somaxconn을 참조하십시오.

.accept() 메서드는 실행을 차단하고 들어오는 연결을 기다립니다. 클라이언트가 연결되면 연결을 나타내는 새 소켓 개체와 클라이언트 주소를 포함하는 튜플을 반환합니다. 튜플에는 IPv4 연결의 경우 (host, port) 또는 IPv6의 경우 (host, port, flowinfo, scopeid)가 포함됩니다. 튜플 값에 대한 자세한 내용은 참조 섹션의 소켓 주소 패밀리를 참조하십시오.

반드시 이해해야 할 한 가지는 이제 .accept()에서 새로운 소켓 개체가 생겼다는 것입니다. 이것은 클라이언트와 통신하는 데 사용할 소켓이기 때문에 중요합니다. 서버가 새 연결을 수락하는 데 사용하는 청취 소켓과 다릅니다.

`python`

``` py
# echo-server.py

# ...

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        while True:
            data = conn.recv(1024)
            if not data:
                break
            conn.sendall(data)
```

.accept()가 클라이언트 소켓 개체 conn을 제공한 후 무한 while 루프를 사용하여 conn.recv()에 대한 차단 호출을 반복합니다. 이것은 클라이언트가 보내는 모든 데이터를 읽고 conn.sendall()을 사용하여 다시 에코합니다.

conn.recv()가 빈 바이트 객체 b''를 반환하면 클라이언트가 연결을 종료하고 루프가 종료되었음을 알립니다. with 문은 conn과 함께 사용되어 블록 끝에서 소켓을 자동으로 닫습니다.

