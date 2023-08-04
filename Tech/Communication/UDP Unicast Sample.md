# UDP Unicast Sample

### 유니캐스트, 멀티캐스트, 브로드캐스트 의 정의



**● 유니캐스트(Unicast)**
유니캐스트는 정보를 전송하기 위한 프레임에 자신의 MAC 주소와 목적지의 MAC 주소를 첨부하여 전송하는 방식을 말한다. 어떤 시스템이 유니캐스트 방식으로 데이터를 전송하게 되면 같은 네트워크에 있는 모든 시스템들은 그 MAC 주소를 받아서 자신의 MAC 주소와 비교 후에 자신의 MAC 주소와 같지 않다면 프레임을 버리고 같다면 프레임을 받아서 처리하게 된다. 유니캐스트 방식은 가장 많이 사용하는 방식으로 한 개의 목적지 MAC 주소를 사용하고 CPU 성능에 문제를 주지 않는 방식이다.


**● 브로드캐스트(Broadcast)**
브로드캐스트 방식은 로컬 네트워크에 연결되어 있는 모든 시스템에게 프레임을 보내는 방식을 말한다. 브로드캐스트 방식의 경우 브로드캐스트용 주소가 미리 정해져있고, 수신 받는 시스템은 이 주소가 오면 패킷을 자신의 CPU 로 전송하고 CPU 가 패킷을 처리하는 방식이다. 모든 시스템에게 패킷이 전송되므로 트래픽이 증가하고 CPU 도 패킷을 처리해야 하므로 성능에 저하가 생긴다. 브로드캐스트 방식은 통신하고자 하는 시스템의 MAC 주소를 알지 못하는 경우, 네트워크에 있는 모든 시스템에게 알리는 경우, 라우터끼리 정보를 교환하거나 새로운 라우터를 찾는 경우 등에 이용이 된다.

**● 멀티캐스트(Multicast)**
멀티캐스트는 네트워크에 연결되어 있는 시스템 중 일부에게만 정보를 전송하는 것으로 특정 그룹에 속해 있는 시스템에게만 한 번에 정보를 전송할 수 있는 방법을 말한다. 멀티캐스트는 라우터가 멀티캐스트를 지원해야만 사용 가능하다는 단점이 있다.
그룹 통신을 위하여 다중 수신자들에게 동일한 데이터를 전송하고자 할 경우 유니캐스트 전송 방식을 이용한다면 전송하고자 하는 데이터 패킷을 다수의 수신자에게 각각 여러 번 전송해야 하며, 이러한 동일한 패킷의 중복 전송으로 인해 네트워크 효율이 저하된다. 또한 수신자 수가 증가할 경우 이러한 문제점은 더 커지게 된다. 반면 멀티캐스트 전송이 지원되면 송신자는 여러 수신자에게 한 번에 메시지가 전송되도록 하여 데이터의 중복 전송으로 인한 네트워크 자원 낭비를 최소화할 수 있게 된다.
멀티캐스트 전송이 일반적인 유니캐스트 인터넷 응용 분야와 다른 점은 우선 그 전송 패킷에 있다. 일반적으로 TCP/IP 상의 인터넷 응용 프로그램은 데이터의 송신자가 이를 수신할 수신자의 인터넷 주소를 전송 패킷의 헤더에 표시해 패킷을 전송한다. 그러나 멀티캐스트 전송을 위해서는 헤더에 수신자의 주소 대신 수신자들이 참여하고 있는 그룹 주소를 표시하여 패킷을 전송한다.

▶ 멀티캐스트 특징
\- 멀티캐스트 그룹 단위로 묶어 그 그룹의 Host 들은 동시에 데이터를 받을 수 있다.
\- UDP 를 사용하여 전송함으로 신뢰성을 보장받지는 못한다.
\- Client 에서 멀티캐스트를 사용하는 Application 을 시작하면 멀티캐스트 IP 주소와 멀티캐스트 MAC 주소를 라우터에 등록함으로 멀티캐스트 그룹에 등록된다.
\- 하나의 Client 에서 여러 멀티캐스트 주소를 수용할 수 있다. 즉 여러 가지 멀티캐스트 데이터를 동시에 받을 수 있다.
\- Server가 멀티캐스트 주소로 데이터를 전송 중에 있을 때 중간에 Client 가 끼어들어도 처음부터 데이터를 받을 수 없고 중간부터 데이터를 받게 된다.

▶ 멀티캐스트 IP 주소 체계
224.0.0.0 ~ 239.255.255.255 범위를 갖는 Class D IP 주소를 사용한다.

| 224.0.0.0 ~ 224.0.0.255     | IETF 에서 관리용으로 사용되는 대역(RIP, EIGRP, OSPF 등)      |
| --------------------------- | ------------------------------------------------------------ |
| 224.0.1.0 ~ 238.255.255.255 | 실제 인터넷에서 멀티캐스트를 사용하는 기관이나 기업에게 할당하는 대역 |
| 232.0.0.0 ~ 232.255.255.255 | PIM 기술을 위해 사용하는 대역                                |
| 233.0.0.0 ~ 233.255.255.255 | 하나의 AS 내에 전파를 원할 때 사용하는 대역                  |
| 239.0.0.0 ~ 239.255.255.255 | 기관이나 기업 내부에서 사용할 수 있는 사설 멀티캐스트 주소   |

이 중 주요 IP 두 개는 다음과 같은 역할을 한다.
\- 224.0.0.1 : 현재 서브넷에 존재하는 멀티캐스트가 가능한 모든 호스트를 지칭한다.
\- 224.0.0.2 : 현재 서브넷에 존재하는 멀티캐스트가 가능한 모든 라우터를 지칭한다.

▶ 멀티캐스트 MAC 주소 체계
멀티캐스트 MAC 주소는 앞에 0100.5E 가 붙고 뒤의 주소들은 IP 주소에 일부분을 참조하여 이루어진다. 예를 들어 227.35.189.34 의 멀티캐스트 MAC 주소는 0100.5E23.BD22 가 된다.

227.35.189.34 를 이진수로 표현하면 아래와 같다.
11100011.00100011.10111101.00100010 

이 중 빨간색은 참조하지 않고 파란 부분만 참조하여 멀티캐스트 MAC 기본 주소 앞부분(0100.5E)을 제외하고 나머지는 위 파란색 부분과 매치 시킨다.
00000001-00000000-01011110-00100011-10111101-00100010 으로 표현되며, 16진수로 표현하면 01-00-5E-23-BD-22 가 된다.

▶ 멀티캐스트 Protocol
어떤 장비와 멀티캐스트 정보를 교환하느냐에 따라 세 가지 프로토콜로 나눌 수 있다.
\- IGMP : 호스트와 라우터 간 멀티캐스트 정보를 교환하는 프로토콜
\- CGMP, IGMP Snooping : 라우터와 스위치 간 멀티캐스트 정보를 교환하는 프로토콜
\- Multicasting Routing Protocol : 라우터와 라우터 간 멀티캐스트 정보를 교환하는 프로토콜


**● 유니캐스트, 멀티캐스트, 브로드캐스트 비교**
\- 유니캐스트(Unicast) : 1 대 1(특정 단일)
\- 브로드캐스트(Broadcast) : 1 대 다수(불특정 다수)
\- 멀티캐스트(Multicast) : 1 대 다수(특정 집단)

![img](https://mblogthumb-phinf.pstatic.net/MjAxODA0MTFfMjEy/MDAxNTIzNDU0Njk5OTg1.-ODTs3RC7n3VHfAOuLrst8sTkk8dDgRBHazV_rr_x_og.d-UdLCTY6TA3cp6-Tsfy_zi_Ojg7d4JXouYQkXjQ2YIg.PNG.wnrjsxo/image.png?type=w800)

유니캐스트, 멀티캐스트, 브로드캐스트 비교



---

### Python 예제

제공한 정보를 기반으로, 파이썬을 사용하여 UDP 유니캐스트 서버와 클라이언트 앱을 만드는 방법을 설명해 드리겠습니다. 아래에 코드와 함께 제공된 정보를 사용하여 작성한 내용을 확인해 주세요.

**UDP 서버 및 클라이언트 앱 만들기:**

UDP(User Datagram Protocol)는 TCP/IP 슈트의 인터넷 프로토콜을 사용하는 프로토콜로, 연결 없이 데이터를 빠르게 전송할 수 있는 특징이 있습니다. UDP는 데이터의 신뢰성을 보장하지 않습니다. 따라서 UDP를 사용하여 간단한 채팅 앱을 만들어 보겠습니다.



#### Server

``` py
import socket

localIP = "127.0.0.1"
localPort = 20001
bufferSize = 1024
msgFromServer = "Hello UDP Client"
bytesToSend = str.encode(msgFromServer)

# Create a datagram socket
UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# Bind to address and ip
UDPServerSocket.bind((localIP, localPort))
print("UDP server up and listening")

# Listen for incoming datagrams
while True:
    bytesAddressPair = UDPServerSocket.recvfrom(bufferSize)
    message = bytesAddressPair[0]
    address = bytesAddressPair[1]
    clientMsg = "Message from Client:{}".format(message)
    clientIP = "Client IP Address:{}".format(address)
    print(clientMsg)
    print(clientIP)
    # Sending a reply to client
    UDPServerSocket.sendto(bytesToSend, address)

```



#### Client

``` py
import socket

UDP_IP_ADDRESS = "127.0.0.1"
UDP_PORT_NO = 20001
Message = "Hello, Server"

# Create a socket for sending data to the server
clientSock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Send the message to the server
clientSock.sendto(Message.encode(), (UDP_IP_ADDRESS, UDP_PORT_NO))

# Receive the server's response
data, addr = clientSock.recvfrom(1024)
print("Response from server:", data.decode())

# Close the socket
clientSock.close()

```

위의 코드는 간단한 UDP 서버와 클라이언트 앱을 만드는 방법을 보여줍니다. 서버와 클라이언트 모두 간단한 메시지를 주고 받습니다. 서버는 클라이언트로부터 메시지를 수신하면 해당 메시지를 출력하고, 클라이언트에게 "Hello UDP Client"라는 메시지를 보냅니다. 클라이언트는 서버에게 메시지를 전송한 후 서버의 응답을 받아 출력합니다.

위의 코드를 파일로 저장한 후, 터미널에서 서버와 클라이언트 각각을 실행하여 테스트할 수 있습니다. 이를 통해 UDP 프로토콜을 사용한 간단한 통신 앱을 만들 수 있습니다.



### Java 예제

아래에 Java를 사용하여 UDP 유니캐스트 서버와 클라이언트 앱을 만드는 방법을 설명하겠습니다. 제공된 정보를 기반으로 코드와 함께 제시된 내용을 확인하세요.

**UDP 서버 및 클라이언트 앱 만들기:**

UDP(User Datagram Protocol)는 TCP와는 다른 프로토콜로, 데이터의 도착 순서나 전송 보증을 제공하지 않고 빠른 데이터 전송을 지원합니다.



#### Server

``` java
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(20001);
            byte[] receiveData = new byte[1024];

            System.out.println("UDP server up and listening");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Message from Client: " + message);
                System.out.println("Client IP Address: " + clientAddress.getHostAddress());

                byte[] sendData = "Hello UDP Client".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}


```



#### Client

``` java
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 20001;

            String message = "Hello, Server";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}


```

위의 코드는 Java를 사용하여 UDP 서버와 클라이언트 앱을 만드는 방법을 보여줍니다. 서버는 클라이언트로부터 메시지를 수신하면 해당 메시지를 출력하고, 클라이언트에게 "Hello UDP Client"라는 메시지를 보냅니다. 클라이언트는 서버에게 메시지를 전송한 후 서버의 응답을 받아 출력합니다.

위의 코드를 파일로 저장한 후, 터미널에서 서버와 클라이언트 각각을 실행하여 테스트할 수 있습니다. 이를 통해 Java를 사용하여 간단한 UDP 통신 앱을 만들 수 있습니다.



---

### 디버깅

1. 오류: java.io.IOException: Operation not permitted (sendto failed)

   https://stackoverflow.com/questions/28098386/plaindatagramsocketimpl-ioexception-operation-not-permitted

   PlainDatagramSocketImpl(IOException: 작업이 허용되지 않음)

   

2. 
