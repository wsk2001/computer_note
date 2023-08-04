# SSL Server And Client in cpp

Reference: Google Bard



`ssl_server.cpp`

``` cpp
#include <iostream>
#include <string>
#include <windows.h>
#include <winsock2.h>
#include <openssl/ssl.h>

#pragma comment(lib, "ws2_32.lib")

int main() {
  // Create a new SSL context.
  SSL_CTX* ctx = SSL_CTX_new(TLS_method());

  // Set the ciphers to use.
  SSL_CTX_set_cipher_list(ctx, "DEFAULT");

  // Create a new SSL socket.
  SSL* ssl = SSL_new(ctx);

  // Bind the socket to port 443.
  int sockfd = socket(AF_INET, SOCK_STREAM, 0);
  sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_port = htons(443);
  addr.sin_addr.s_addr = INADDR_ANY;
  bind(sockfd, (sockaddr*)&addr, sizeof(addr));
  listen(sockfd, 10);

  // Accept connections.
  while (true) {
    int clientfd = accept(sockfd, NULL, NULL);

    // Handshake with the client.
    SSL_accept(ssl);

    // Send a message to the client.
    std::string message = "Hello, world!";
    SSL_write(ssl, message.c_str(), message.length());

    // Close the connection.
    SSL_close(ssl);
    close(clientfd);
  }

  return 0;
}

```



`ssl_client.cpp`

``` cpp
#include <iostream>
#include <string>
#include <windows.h>
#include <winsock2.h>
#include <openssl/ssl.h>

#pragma comment(lib, "ws2_32.lib")

int main() {
  // Create a new SSL context.
  SSL_CTX* ctx = SSL_CTX_new(TLS_method());

  // Set the ciphers to use.
  SSL_CTX_set_cipher_list(ctx, "DEFAULT");

  // Create a new SSL socket.
  SSL* ssl = SSL_new(ctx);

  // Connect to the server.
  int sockfd = socket(AF_INET, SOCK_STREAM, 0);
  sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_port = htons(443);
  addr.sin_addr.s_addr = inet_addr("localhost");
  connect(sockfd, (sockaddr*)&addr, sizeof(addr));

  // Handshake with the server.
  SSL_connect(ssl);

  // Send a message to the server.
  std::string message = "Hello, world!";
  SSL_write(ssl, message.c_str(), message.length());

  // Receive a message from the server.
  char buffer[1024];
  int bytes_received = SSL_read(ssl, buffer, sizeof(buffer));
  std::string response(buffer, buffer + bytes_received);

  // Close the connection.
  SSL_close(ssl);
  close(sockfd);

  // Print the response from the server.
  std::cout << response << std::endl;

  return 0;
}

```



---

## SSL Socket 예제

출처: https://www.sysnet.pe.kr/2/0/11410



보안이 취약한 소켓 통신을 HTTPS 웹 사이트처럼 통신하도록 만들어 주는 것이 바로 SSL Socket입니다. 사실 구현이 그리 어렵지도 않습니다. 왜냐하면 OpenSSL 라이브러리에서 대부분의 작업을 맡아서 해주기 때문입니다.

저도 예제 삼아서 SSL Socket을 서버는 C/C++로, 클라이언트는 C#으로 한번 해봤습니다.

순서 상, 서버 먼저 만들어 볼 텐데요, C/C++ 프로젝트에서는 OpenSSL의 도움이 절대적입니다. 따라서 지난 글에 소개한 vcpkg를 이용해 OpenSSL 패키지를 로컬에 구성합니다.

```
오픈 소스 라이브러리를 쉽게 빌드해 주는 "C++ Package Manager for Windows: vcpkg"
; https://www.sysnet.pe.kr/2/0/11409
```



빌드를 완료했으면 SSL 통신을 위한 인증서와 개인키 파일을 만들어야 하는데요. 이에 대해 다음의 글에서 설명한 방법을 따랐습니다.

```
사설 SSL 인증서 만들기
; https://www.joinc.co.kr/w/Site/Tip/SSL_cert
```



따라서 우선 다음의 내용을 가진 server_cert.conf라는 파일을 만든 후,

``` conf
[req]
default_bits = 1024
encrypt_key = yes
distinguished_name = req_dn
x509_extensions = cert_type
prompt = no

[req_dn]
# country (2 letter code)
#C=FI

# State or Province Name (full name)
#ST=

# Locality Name (eg. city)
#L=Seoul

# Organization (eg. company)
#O=Joinc

# Organizational Unit Name (eg. section)
OU=developer

# Common Name (*.example.com is also possible)
CN=testserver.com

# E-mail contact
emailAddress=test@test.com

[cert_type]
nsCertType = server
```



이렇게 openssl.exe를 실행해 주면,

``` bash
c:\temp> openssl.exe req -new -x509 -nodes -config server_cert.conf -out test.pem -keyout key.pem -days 365
Generating a 1024 bit RSA private key
........................++++++
........++++++
writing new private key to 'key.pem'
-----
```

test.pem, key.pem 파일 2개가 생성됩니다. (참고로, openssl.exe는 vcpkg를 통해 빌드한 .\installed\x86-windows\tools\openssl\ 폴더에서도 구할 수 있습니다.)

C/C++ SSL 소켓 서버는 다음의 글에서 이미 친절하게 예제 코드를 실어 놓았으니 이를 참조하겠습니다.

```
Simple TLS Server
; https://wiki.openssl.org/index.php/Simple_TLS_Server
```

예를 들기 위해, 간단한 ECHO 서버를 일반 소켓으로 먼저 만들어 보겠습니다.

`echo server.cpp`

``` cpp
#include <iostream>
#include <string>
#include <openssl/ssl.h>
#include <windows.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

constexpr int PORT = 4490;

void SocketProcess()
{
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd< 0)
    {
        return;
    }

    struct sockaddr_in server_addr;

    int addr_len = sizeof(struct sockaddr_in);

    memset((char *)&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    server_addr.sin_port = htons(PORT);

    bind(sockfd, (struct sockaddr *) &server_addr, addr_len);

    listen(sockfd, 5);

    while (true)
    {
        int newsockfd = accept(sockfd, (struct sockaddr *) &server_addr, &addr_len);

        char buf[4096] = { 0 };
        int nBytesToRead = 4096;
        recv(newsockfd, (char *)buf, nBytesToRead, 0);

        char outputBuf[4096] = { 0 };
        int length = wsprintfA(outputBuf, "Echo: %s\n", buf);
        send(newsockfd, outputBuf, length, 0);

        closesocket(newsockfd);
    }
}

int main()
{
    WORD wVersionRequested = MAKEWORD(2, 2);
    WSADATA wsaData;

    ::WSAStartup(wVersionRequested, &wsaData);

    SocketProcess();

    WSACleanup();

    return 0;
}
```



이 코드에 SSL 기능을 넣어볼 텐데, 의외로 기존 코드의 변경이 거의 없습니다. 단지 recv, send만 다른 함수로 교체될 뿐 다른 코드들은 그냥 적절한 시점에 호출되도록 추가만 해주면 됩니다. 그래서 SSL 여부를 전처리 상수로 지정해 마음대로 on/off 시킬 수 있도록 다음과 같이 코딩할 수 있습니다.

`ssl_echo_server.cpp`

``` cpp
#include <iostream>
#include <string>
#include <openssl/ssl.h>
#include <windows.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

#define USE_SSL

constexpr int PORT = 4490;

#include <openssl/ssl.h>
#include <openssl/err.h>

void init_openssl()
{
    SSL_load_error_strings();
    SSL_library_init();
    OpenSSL_add_all_algorithms();
}

void cleanup_openssl()
{
    ERR_free_strings();
    EVP_cleanup();
}

void SocketProcess()
{
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd< 0)
    {
        return;
    }

    struct sockaddr_in server_addr;

    int addr_len = sizeof(struct sockaddr_in);

    memset((char *)&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    server_addr.sin_port = htons(PORT);

    bind(sockfd, (struct sockaddr *) &server_addr, addr_len);

    listen(sockfd, 5);

#ifdef USE_SSL
    SSL_CTX *sslContext = SSL_CTX_new(SSLv23_server_method());
    SSL_CTX_set_options(sslContext, SSL_OP_SINGLE_DH_USE);

    int use_cert = SSL_CTX_use_certificate_file(sslContext, "..\\test.pem", SSL_FILETYPE_PEM);
    int use_prv = SSL_CTX_use_PrivateKey_file(sslContext, "..\\key.pem", SSL_FILETYPE_PEM);
#endif

    while (true)
    {
        int newsockfd = accept(sockfd, (struct sockaddr *) &server_addr, &addr_len);

#ifdef USE_SSL
        SSL *ssl = SSL_new(sslContext);
        SSL_set_fd(ssl, newsockfd);
        int ssl_err = SSL_accept(ssl);
        if (ssl_err <= 0)
        {
            continue;
        }
#endif

        char buf[4096] = { 0 };
        int nBytesToRead = 4096;

#ifdef USE_SSL
        SSL_read(ssl, (char *)buf, nBytesToRead);
#else
        recv(newsockfd, (char *)buf, nBytesToRead, 0);
#endif

        char outputBuf[4096] = { 0 };
        int length = wsprintfA(outputBuf, "Echo: %s\n", buf);

#ifdef USE_SSL
        SSL_write(ssl, outputBuf, length);
#else
        send(newsockfd, outputBuf, length, 0);
#endif
   
#ifdef USE_SSL
        SSL_free(ssl);
#endif
    
        closesocket(newsockfd);
    }

#ifdef USE_SSL
    SSL_CTX_free(sslContext);
#endif
}

int main()
{
    WORD wVersionRequested = MAKEWORD(2, 2);
    WSADATA wsaData;

    ::WSAStartup(wVersionRequested, &wsaData);

#ifdef USE_SSL
    init_openssl();
#endif
    SocketProcess();
#ifdef USE_SSL
    cleanup_openssl();
#endif

    WSACleanup();

    return 0;
}
```

의외로 쉽게 C++ 코드에 SSL을 적용할 수 있었습니다. ^^



C#은 BCL의 도움으로 별다른 외부 라이브러리 없이 간단하게 해결됩니다. 우선, SSL이 없는 일반 Echo Client 예제를 보겠습니다.

`echo_client.cs`

``` csharp
// EchoClient.cs

using System;
using System.Net.Sockets;
using System.Text;

class EchoClient
{
    internal void Process(int port, string msg)
    {
        using (TcpClient client = new TcpClient("127.0.0.1", port))
        {
            NetworkStream ns = client.GetStream();

            byte[] buf = Encoding.ASCII.GetBytes(msg);
            ns.Write(buf, 0, buf.Length);

            buf = new byte[4096];
            int readLen = ns.Read(buf, 0, 4096);
            Console.WriteLine(Encoding.ASCII.GetString(buf, 0, readLen));
        }
    }
}
```

``` csharp
// Program.cs

using System;

class Program
{
    static void Main(string[] args)
    {
        string msg = string.Empty;

        while (true)
        {
            msg = Console.ReadLine();

            EchoClient echo = new EchoClient();
            echo.Process(4490, msg);
        }
    }
}
```

위의 코드에 SSL을 입혀보면 다음과 같이 변경됩니다.



``` csharp
// EchoSslClient.cs

using System;
using System.Net.Security;
using System.Net.Sockets;
using System.Security.Authentication;
using System.Security.Cryptography.X509Certificates;
using System.Text;

class EchoSslClient
{
    internal void Process(int port, string certName, string msg)
    {
        using (TcpClient client = new TcpClient("127.0.0.1", port))
        using (SslStream sslStream = new SslStream(client.GetStream(), false, validateCertificate, null))
        {
            try
            {
                sslStream.AuthenticateAsClient(certName);
            }
            catch (AuthenticationException e)
            {
                Console.WriteLine(e.ToString());
                return;
            }

            byte[] buf = Encoding.ASCII.GetBytes(msg);
            sslStream.Write(buf, 0, buf.Length);
            sslStream.Flush();

            buf = new byte[4096];
            int readLen = sslStream.Read(buf, 0, 4096);
            Console.WriteLine(Encoding.ASCII.GetString(buf, 0, readLen));
        }
    }

    private bool validateCertificate(object sender, X509Certificate certificate, X509Chain chain,
        SslPolicyErrors sslPolicyErrors)
    {
        // ...[생략: 잠시 후에 설명]...
    }
}
```

``` csharp
// Program.cs

using System;

class Program
{
    static void Main(string[] args)
    {
        string msg = string.Empty;

        while (true)
        {
            msg = Console.ReadLine();

            EchoSslClient echo = new EchoSslClient();
            echo.Process(4490, "testserver.com", msg);
        }
    }
}
```



위의 코드에서 주의할 것은, SslStream.AuthenticateAsClient의 인자로 전달된 "testserver.com" 문자열이 임의의 값이 아니라는 점입니다. 즉, 서버 인증서를 생성할 때 server_cert.conf 파일 안에 지정한 "CN=testserver.com" 항목의 CN 값이라는 것에 유의해야 합니다. HTTPS 통신을 위한 웹 서버의 인증서에서도 CN 값이 반드시 일치해야 한다는 것을 상기해 보면 됩니다.

어쨌든, 전체적인 코드가 그리 어렵지 않기 때문에 가능하면 보안을 위해 여러분의 소켓 서버에 SSL을 적용하는 것을 긍정적으로 검토하는 것도 좋겠습니다. ^^

([첨부 파일은 이 글의 예제 코드를 포함](./images/ssl_socket_server_client_sample.zip)합니다.)

마지막으로 validateCertificate 콜백 메서드에 대해 설명해 보겠습니다. 간단하게 이 메서드는 다음과 같이 true를 반환하게 할 수 있습니다.

``` csharp
private bool validateCertificate(object sender, X509Certificate certificate, X509Chain chain,
    SslPolicyErrors sslPolicyErrors)
{
    return true;
}
```

그럼 인증서가 SSL 통신이 가능하다는 기준만 충족하면 무조건 SSL 통신에 사용할 수 있다는 것을 클라이언트 측에서 허락하는 것과 같습니다. 가령, SslStream.AuthenticateAsClient의 인자로 전달되는 CN 값이 달라도 되고 해당 인증서가 로컬 PC의 신뢰할 수 있는 인증서가 아니어도 SSL 통신을 할 수 있습니다.

제 개인적인 의견으로는, 정식 인증서를 발급받기 전 또는 로컬 PC의 개발 환경에서는 다음과 같은 식으로 UntrustedRoot 옵션에 대해서만큼은 true를 반환하도록 하는 것이 개발 편의를 높일 수 있습니다.

``` csharp
private bool validateCertificate(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
{
    if (sslPolicyErrors == SslPolicyErrors.None)
    {
        return true;
    }

#if DEBUG
    if (sslPolicyErrors == SslPolicyErrors.RemoteCertificateChainErrors)
    {
        if (chain.ChainStatus.Length == 1)
        {
            if (chain.ChainStatus[0].Status == X509ChainStatusFlags.UntrustedRoot)
            {
                return true;
            }
        }
    }
#endif

    Console.WriteLine("validateCertificate: {0}", sslPolicyErrors);
    return false;
}
```

참고로, 클라이언트에서 제공한 CN 값이 다르면 sslPolicyErrors에는 RemoteCertificateNameMismatch 오류 값이 담깁니다.

이 글을 실습하면서 발생할 수 있는 오류에 대해 정리합니다.

우선, OpenSSL 라이브러리를 추가한 Visual C++ 프로젝트를 컴파일 시 applink.c 포함 구문에서 다음과 같은 오류가 발생할 수 있습니다.



```c
#include <openssl/applink.c>

Error C4996 'fopen': This function or variable may be unsafe. Consider using fopen_s instead. To disable deprecation, use _CRT_SECURE_NO_WARNINGS. See online help for details.
```

오류 메시지에 따르면 위의 #include 전에 "#define _CRT_SECURE_NO_WARNINGS" 전처리 상수를 정의하면 될 듯한데, 그래도 오류가 발생합니다. 아마도 이게 C 파일이라서 그런 듯싶은데 Visual C++ 프로젝트의 속성 창에서 "Configuration Properties" / "C/C++" / "Preprocessor" 범주의 "Preprocessor Definitions"에 "_CRT_SECURE_NO_WARNINGS" 상수를 추가해 전역적으로 적용해야 컴파일 오류가 없어집니다.



------

그다음, vcpkg로 빌드한 openssl의 경우 다음과 같은 경로에 있는 openssl.exe를 실행하면 0xc000007b 오류가 발생할 수 있습니다.



```bash
E:\git_clone\vcpkg\installed\x86-windows\tools\openssl> openssl

The application was unable to start correctly (0xc000007b). Click OK to close the application. 
```

이 오류는 openssl.exe의 실행에 필요한 libeay32.dll, ssleay32.dll 파일이 없어서 그런 것입니다. 따라서, ".\installed\x86-windows\bin" 폴더의 libeay32.dll, ssleay32.dll 파일을 openssl.exe 파일이 있는 폴더에 복사해 실행하면 됩니다.

그래도 실행하면 다음과 같은 경고가 발생합니다.



```bash
E:\git_clone\vcpkg\installed\x86-windows\tools\openssl> openssl
WARNING: can't open config file: E:/git_clone/vcpkg/packages/openssl_x86-windows/openssl.cnf
OpenSSL>
```

일단 실행은 되어 OpenSSL 프롬프트가 떴지만 그래도 왠지 깔끔하지 않습니다. ^^ openssl.cnf 파일은 비주얼 스튜디오 2017이 있는 경우 쉽게 구할 수 있습니다. 가령, 제 컴퓨터에서는 다음의 경로에서 구할 수 있었습니다.



```bash
C:\Program Files (x86)\Microsoft Visual Studio\2017\Enterprise\Common7\IDE\CommonExtensions\Microsoft\TeamFoundation\Team Explorer\Git\usr\ssl
```

따라서 그 파일을 오류 메시지에 따라 "E:\git_clone\vcpkg\packages\openssl_x86-windows" 폴더에 복사하면 경고가 사라집니다.



How does HTTPS Work?

![](.\images\how_https_work_1.jpg)
