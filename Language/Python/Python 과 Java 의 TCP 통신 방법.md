# Python 과 Java 의 TCP 통신 방법



## How to communicate tcp between java and python.



두 프로세스 간의 소켓 연결을 열어 TCP를 통해 Java와 Python 간에 통신할 수 있습니다.

Java에서는 java.net 패키지를 사용하여 소켓 연결을 만들 수 있습니다. 다음은 Java TCP 서버에 대한 예제 코드입니다.



``` java
import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 9000;
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
```



다음은 Python TCP 클라이언트에 대한 예제 코드입니다.

``` py
import socket

def main():
    host = 'localhost'
    port = 9000
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))

    message = "Hello from Python"
    s.sendall(message.encode())

    data = s.recv(1024)
    s.close()
    print("Received", repr(data.decode()))

if __name__ == '__main__':
    main()

```

이것은 아이디어를 보여주기 위한 기본 예일 뿐이며 필요에 따라 사용자 정의할 수 있습니다.





## How to communicate tcp between python and java.

두 프로세스 간의 소켓 연결을 열어 TCP를 통해 Python과 Java 간에 통신할 수 있습니다.

Python에서는 소켓 모듈을 사용하여 소켓 연결을 만들 수 있습니다. 다음은 Python TCP 서버에 대한 예제 코드입니다.

``` py
import socket

def main():
    host = 'localhost'
    port = 9000
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host, port))
    s.listen(1)
    conn, addr = s.accept()
    with conn:
        print('Connected by', addr)
        while True:
            data = conn.recv(1024)
            if not data:
                break
            conn.sendall(data)

if __name__ == '__main__':
    main()

```



다음은 Java TCP 클라이언트에 대한 예제 코드입니다.

``` java
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 9000;
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}

```

이것은 아이디어를 보여주기 위한 기본 예일 뿐이며 필요에 따라 사용자 정의할 수 있습니다.

