# OpenSSL 설치 및 사용

본 문서는 OpenSSL 의 설치 및 사용 방법을 기술 한다.



---

## 1. Windows 윈도우 10 에 OpenSSL 을 설치하는 방법

출처: https://aspdotnet.tistory.com/2653



OpenSSL은 TLS (Transport Layer Security) 및 SSL (Secure Sockets Layer) 프로토콜을 위한 모든 기능을 갖춘 툴킷 입니다. Apache 스타일 라이센스에 따라 라이센스가 부여됩니다. 이 튜토리얼은 Windows 운영 체제에 OpenSSL 을 설치하는 데 도움이 됩니다.



### 1 단계 – OpenSSL 바이너리 다운로드

아래 다운로드 URL 을 통해 최신 OpenSSL Windows 설치 프로그램 파일을 다운로드 받으세요. 

OpenSSL 다운로드 페이지를 방문하려면 아래 링크를 클릭하세요.

 http://slproweb.com/products/Win32OpenSSL.html

 ![img](.\images\5oOE7HFQBcJIl1kof5mpl0_img.png)



 

### 2 단계 – OpenSSL 설치 프로그램 실행

 다운로드 받은 OpenSSL 설치 프로그램을 실행합니다. OpenSSL을 사용하려면 시스템에 Microsoft Visual C++ 를 설치해야 합니다. 시스템에 Microsoft Visual C++가 설치되어 있지 않은 경우 설치 프로그램은 다음과 같은 메시지를 알려줍니다.

 

![img](.\images\j2mqSFKxRQJMDXnNnKn4sk_img.png)

YES 를 클릭 하여 필요한 Microsoft Visual C ++ 패키지를 다운로드 하고 설치합니다.

![img](.\images\uYvHBZ4U7APzac5iG828l1_img.png)

그런 다음, 이제 다시 OpenSSL 설치 프로그램을 실행하고 순서대로 설치합니다.

![img](.\images\EL2EDs2SXk9uCGS5zWjgjK_img.png)



 

### Step 3 – Setup Environment Variables

 이제 시스템에서 OpenSSL 이 제대로 작동하도록 환경 변수를 설정합니다. 위와 같이 별도의 폴더에 설치하였다면, OPENSSL_CONF 및 Path 환경 변수를 설정합니다. 만약에 기본 설정 경로인 C:\Program Files\OpenSSL-Win64 폴더에 설치되었다면 환경 변수 셋팅하지 않아도 됩니다.

 

```
set OPENSSL_CONF=C:\OpenSSL-Win32\bin\openssl.cfg
set Path=......Other Values here......;C:\OpenSSL-Win32\bin
```

 

 

OPENSSL_CONF **Variable** 설정 :

 



![img](.\images\PQ1SjyoIJnK9ajLqFDY3tK_img.png)



Path Variable 변경:



![img](.\images\sjpHtzPTsXK3sHblCkEblK_img.png)



 

### Step 4 – OpenSSL 바이너리 실행

 

명령 프롬프트를 열고 OpenSSL prompt 를 가져오기 위해 **openssl** 을 타이핑합니다. 그런 다음, 설치된 OpenSSL 버전을 보려면 OpenSSL 에서 아래와 같이 **version** 명령을 실행해 봅니다.

 



![img](.\images\q264Eb0WzRuk2AAACel4Jk_img.png)



 

 

참고자료

 

[tecadmin.net/install-openssl-on-windows/](https://tecadmin.net/install-openssl-on-windows/)

 

How To Install OpenSSL on Windows – TecAdmin

OpenSSL is a full-featured toolkit for the Transport Layer Security (TLS) and Secure Sockets Layer (SSL) protocols. It is licensed under an Apache-style license. This tutorial will help you to install OpenSSL on Windows operating systems. Step 1 – Downlo

tecadmin.net

 

 

### OpenSSL 자체 서명 self-signed certificate 된 인증서 생성하기

 

OpenSSL은 TLS (Transport Layer Security) 및 SSL (Secure Sockets Layer) 프로토콜을 위한 모든 기능을 갖춘 툴킷 입니다. Apache 스타일 라이센스에 따라 라이센스가 부여됩니다. 이 튜토리얼은 Windows 운영 체제에 OpenSSL 을 설치하는 데 도움이 됩니다... 자세한 내용은 아래 블로그를 참고 하시기 바랍니다. (**참고로 위의 절차대로 설치 후 아래 블로그를 참고하시기 바랍니다.**)

  

---

## 2. Winsock을 이용하여 OpenSSL echo Server 만들기

출처: https://m.blog.naver.com/beodeulpiri/221461025247

Client / Server TCP 통신을 SSL 인증서로 인증하여 채널 암복화 통신을 하려고 테스트 하던 중에 서버는 OpenSSL을 이용하여 구성되어 있는데, 클라이언트는 MS의 SSPI 의 API를 이용하여 SSL 인증서 처리를 작업하던중 우여곡절끝에 HandShake는 완료되어 EncryptMessage 함수로 암호화해서 데이타를 보내는데 계속 통신이 끊어져서 OpenSSL의 라이브러리를 사용안해서 내부 오류가 발생했나 싶기도 했지만 HandShake는 통과했기 때문에 이상해서 먼저 echo 서버를 만들어 Client 통신처리를 검증하고자 만들게 되었다.



그런데, 찾아보니 OpenSSL을 이용한 echo Server들은 대부분 리눅스 환경에 맞는 코드들이었다. 그래서 윈도우에서 띄워서 테스트해보기 위해 Winsock을 이용하는 방법으로 수정하느라 거의 하루를 삽질하면서 코드를 완성해서 나같은 개발자가 없도록 결과물을 공유하고자 한다.



먼저 서버에서 사용할 셀프 인증서 생성을 해야 한다. 아래사이트에서 OpenSSL([Win32 OpenSSL v1.1.0j](https://slproweb.com/download/Win32OpenSSL-1_1_0j.exe)) 를 받아서 설치하면 한다.

https://slproweb.com/products/Win32OpenSSL.html



v1.1.0 버전을 받아서 설치했다. v1.1.1은 라이브러리 이름이 많이 변경되어서 사용법이 좀 바뀐 것 같다. 설치후 해당 폴더에서 아래 명령어를 실행하여 인증서를 생성한다.

**openssl req -x509 -nodes -days 365 -newkey rsa:1024 -keyout mycert.pem -out mycert.pem**

생성된 mycert.pem 인증서를 서버실행시 같은 폴더에 넣어 놓으면 된다. 만약 인증서 생성시 openssl.cfg 환경설정 파일을 읽을수 없다는 메세지가 나오면 시스템속성 화면에서 환경변수를 아래와 같이 추가해 준다.

변수이름 : OPENSSL_CONF

변수값 : C:\OpenSSL-Win32\bin\openssl.cfg



서버 코드는 다음과 같다. CLR 콘솔 응용프로그램 프로젝트를 선택해서 새로 만들기를 해서 아래 코드를 붙여 넣기 하면 된다.

``` cpp
#include "stdafx.h"

#undef UNICODE

#define WIN32_LEAN_AND_MEAN

#include <winsock2.h>
#include <ws2tcpip.h>
#pragma comment (lib, "Ws2_32.lib")

#include <openssl/ssl.h>
#include <openssl/bio.h>
#include <openssl/err.h>

#pragma comment(lib, "ssleay32.lib")
#pragma comment(lib, "libeay32.lib")

#include <windows.h>
#include <string>
#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <strsafe.h>

#define IP_ADDR localhost
#define DEFAULT_PORT "9002"

#define FAIL    -1

void DebugMsg(const CHAR* pszFormat, ...)
{
#ifdef _DEBUG
	CHAR buf[1024];
	StringCchPrintf(buf, sizeof(buf) / sizeof(CHAR), "(%lu): ", GetCurrentThreadId());
	va_list arglist;
	va_start(arglist, pszFormat);
	StringCchVPrintf(&buf[strlen(buf)], sizeof(buf) / sizeof(CHAR), pszFormat, arglist);
	va_end(arglist);
	StringCchCat(buf, sizeof(buf) / sizeof(CHAR), "\n");
	printf(buf);
#endif
}

void PrintHexDump(DWORD length, const void * const buf, const bool verbose = false)
{
	DWORD i, count, index;
	CHAR rgbDigits[] = "0123456789abcdef";
	CHAR rgbLine[100];
	char cbLine;
	const unsigned char * buffer = static_cast<const unsigned char *>(buf);

	if (!verbose & (length > 16))
		length = 16;

	for (index = 0; length;
		length -= count, buffer += count, index += count)
	{
		count = (length > 16) ? 16 : length;

		sprintf_s(rgbLine, 100, "%4.4x  ", index);
		cbLine = 6;

		for (i = 0; i<count; i++)
		{
			rgbLine[cbLine++] = rgbDigits[buffer[i] >> 4];
			rgbLine[cbLine++] = rgbDigits[buffer[i] & 0x0f];
			if (i == 7)
			{
				rgbLine[cbLine++] = ':';
			}
			else
			{
				rgbLine[cbLine++] = ' ';
			}
		}
		for (; i < 16; i++)
		{
			rgbLine[cbLine++] = ' ';
			rgbLine[cbLine++] = ' ';
			rgbLine[cbLine++] = ' ';
		}

		rgbLine[cbLine++] = ' ';

		for (i = 0; i < count; i++)
		{
			if (buffer[i] < 32 || buffer[i] > 126)
			{
				rgbLine[cbLine++] = '.';
			}
			else
			{
				rgbLine[cbLine++] = buffer[i];
			}
		}

		rgbLine[cbLine++] = 0;
		DebugMsg(rgbLine);
	}
}

SSL_CTX* InitServerCTX(void)
{
	const SSL_METHOD *method;
	SSL_CTX *ctx;

	OpenSSL_add_all_algorithms();  /* load & register all cryptos, etc. */
	SSL_load_error_strings();   /* load all error messages */

	method = TLSv1_2_server_method();
	ctx = SSL_CTX_new(method);   /* create new context from method */
	if (ctx == NULL)
	{
		printf("SSL_CTX_new error\n");
		ERR_print_errors_fp(stderr);
		abort();
	}

	SSL_CTX_set_cipher_list(ctx, "ALL:eNULL");

	return ctx;
}

void LoadCertificates(SSL_CTX* ctx, char* CertFile, char* KeyFile)
{
	//New lines 
	if (SSL_CTX_load_verify_locations(ctx, CertFile, KeyFile) != 1)
	{
		printf("SSL_CTX_load_verify_locations error\n");
		ERR_print_errors_fp(stderr);
	}

	if (SSL_CTX_set_default_verify_paths(ctx) != 1)
	{
		printf("SSL_CTX_set_default_verify_paths error\n");
		ERR_print_errors_fp(stderr);
	}
	//End new lines

	/* set the local certificate from CertFile */
	if (SSL_CTX_use_certificate_file(ctx, CertFile, SSL_FILETYPE_PEM) <= 0)
	{
		printf("SSL_CTX_use_certificate_file error\n");
		ERR_print_errors_fp(stderr);
		abort();
	}
	/* set the private key from KeyFile (may be the same as CertFile) */
	SSL_CTX_set_default_passwd_cb_userdata(ctx, "12345678");
	if (SSL_CTX_use_PrivateKey_file(ctx, KeyFile, SSL_FILETYPE_PEM) <= 0)
	{
		printf("SSL_CTX_use_PrivateKey_file error\n");
		ERR_print_errors_fp(stderr);
		abort();
	}
	/* verify private key */
	if (!SSL_CTX_check_private_key(ctx))
	{
		fprintf(stderr, "Private key does not match the public certificate\n");
		abort();
	}

	//New lines - Force the client-side have a certificate
	//SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER | SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);
	//SSL_CTX_set_verify_depth(ctx, 4);
	//End new lines
}

void ShowCerts(SSL* ssl)
{
	X509 *cert;
	char *line;

	cert = SSL_get_peer_certificate(ssl); /* Get certificates (if available) */
	if (cert != NULL)
	{
		printf("Server certificates:\n");
		line = X509_NAME_oneline(X509_get_subject_name(cert), 0, 0);
		printf("Subject: %s\n", line);
		free(line);
		line = X509_NAME_oneline(X509_get_issuer_name(cert), 0, 0);
		printf("Issuer: %s\n", line);
		free(line);
		X509_free(cert);
	}
	else
		printf("No certificates.\n");
}

/**
* Example SSL server that accepts a client and echos back anything it receives.
* Test using `curl -I https://127.0.0.1:8081 --insecure`
*/
int main(int arc, char **argv)
{
	WSADATA wsaData;
	int iResult;

	SOCKET ListenSocket = INVALID_SOCKET;
	SOCKET ClientSocket = INVALID_SOCKET;

	struct addrinfo *result = NULL;
	struct addrinfo hints;
	
	// Initialize Winsock
	iResult = WSAStartup(MAKEWORD(2, 2), &wsaData);
	if (iResult != 0) {
		printf("WSAStartup failed with error: %d\n", iResult);
		return 1;
	}

	ZeroMemory(&hints, sizeof(hints));
	hints.ai_family = AF_INET;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;
	hints.ai_flags = AI_PASSIVE;

	// Resolve the server address and port
	iResult = getaddrinfo(NULL, DEFAULT_PORT, &hints, &result);
	if (iResult != 0) {
		printf("getaddrinfo failed with error: %d\n", iResult);
		WSACleanup();
		return 1;
	}

	// Create a SOCKET for connecting to server
	ListenSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
	if (ListenSocket == INVALID_SOCKET) {
		printf("socket failed with error: %ld\n", WSAGetLastError());
		freeaddrinfo(result);
		WSACleanup();
		return 1;
	}

	// Setup the TCP listening socket
	iResult = bind(ListenSocket, result->ai_addr, (int)result->ai_addrlen);
	if (iResult == SOCKET_ERROR) {
		printf("bind failed with error: %d\n", WSAGetLastError());
		freeaddrinfo(result);
		closesocket(ListenSocket);
		WSACleanup();
		return 1;
	}
	printf("Setup the TCP listening socket: port[%s]\n", DEFAULT_PORT);
	freeaddrinfo(result);

	iResult = listen(ListenSocket, SOMAXCONN);
	if (iResult == SOCKET_ERROR) {
		printf("listen failed with error: %d\n", WSAGetLastError());
		closesocket(ListenSocket);
		WSACleanup();
		return 1;
	}

	// Accept a client socket
	ClientSocket = accept(ListenSocket, NULL, NULL);
	if (ClientSocket == INVALID_SOCKET) {
		printf("accept failed with error: %d\n", WSAGetLastError());
		closesocket(ListenSocket);
		WSACleanup();
		return 1;
	}
	printf("Accept a client socket.\n");
	// No longer need server socket
	closesocket(ListenSocket);

	char CertFile[] = "mycert.pem";
	char KeyFile[] = "mycert.pem";

	SSL_library_init();
	
	SSL_CTX *ctx = InitServerCTX();        /* initialize SSL */
	LoadCertificates(ctx, CertFile, KeyFile); /* load certs */
	printf("LoadCertificates.\n");

	SSL *ssl = SSL_new(ctx);
	printf("SSL_new.\n");
	BIO *accept_bio = BIO_new_socket(ClientSocket, BIO_CLOSE);
	SSL_set_bio(ssl, accept_bio, accept_bio);

	SSL_accept(ssl);
	printf("SSL_accept.\n");
	
	BIO *bio = BIO_pop(accept_bio);

	char buf[1024];
	memset(buf, 0x00, 1024);
	while (1)
	{
		// first read data
		int r = SSL_read(ssl, buf, 1024);
		printf("SSL_read buf len:%d\n", r);
		PrintHexDump(r,buf);
		switch (SSL_get_error(ssl, r))
		{
		case SSL_ERROR_NONE:
			break;
		case SSL_ERROR_ZERO_RETURN:
			goto end;
		default:
			printf("SSL read problem");
			goto end;
		}

		int len = r;

		// now keep writing until we've written everything
		int offset = 0;
		while (len)
		{
			r = SSL_write(ssl, buf + offset, len);
			switch (SSL_get_error(ssl, r))
			{
			case SSL_ERROR_NONE:
				len -= r;
				offset += r;
				break;
			default:
				printf("SSL write problem");
				goto end;
			}
		}
	}

end:

	SSL_shutdown(ssl);

	BIO_free_all(bio);
	BIO_free_all(accept_bio);

	// cleanup
	closesocket(ClientSocket);
	WSACleanup();

	return 0;
}
```



그리고, 프로젝트 속성에서 설치한 OpenSSL include 폴더와 Lib 폴더를 추가해줘야 한다. 컴파일 하기 귀찮은 사람들을 위해 컴파일된 파일을 첨부하겠다.

**첨부파일**

OpenSSL_Server.zip

[파일 다운로드](https://download.blog.naver.com/open/36a32a9989dbd20e22ccac9da04a324cefb846a410/F1Gmz7jsD8Ncu66xKGPb6xckb7JYxmYXv84eadT2iPc2jpSAHYLVyf2CP50y1ffyK6ygD2vgiG63mSv8d28jNw/OpenSSL_Server.zip)

echo 서버가 잘 작동하는지 먼저 openssl 툴로 접속하여 테스트해 보았다. echo 서버는 localhost로 실행되니 다음과 같이 실행하여 접속해서 테스트가 가능하다.

**openssl s_client -connect 127.0.0.1:9002 -tls1_2**


![img](.\images\openssl_echotest.png)

openssl로 통신테스트 중인 모습

openssl로 접속하여 보낸 문자가 다시 잘  echo되어서 돌아오고, 서버에도 암호화되어서 보내졌는데, 복호화되어서 잘 출력되는 모습을 볼수 있다. 암복호화가 잘 되는지는 Wireshark로 확인이 가능하다.

포트는 현재 9002인데 위에 코드에서 수정해서 컴파일하면 변경해서 사용이 가능하다.



참고사이트

https://docs.microsoft.com/en-us/windows/desktop/winsock/complete-server-code

 

**Complete Winsock Server Code - Windows applications**

Complete Winsock Server Code 2 minutes to read The following is the complete source code for the basic Winsock TCP/IP Server application. Winsock Server Source Code #undef UNICODE #define WIN32_LEAN_AND_MEAN #include <windows.h> #include <winsock2.h> #include <ws2tcpip.h> #include <stdlib.h> #incl...

docs.microsoft.com

https://aticleworld.com/ssl-server-client-using-openssl-in-c/

[![img](.\images\client-server-application-in-c.jp)](https://aticleworld.com/ssl-server-client-using-openssl-in-c/)

**ssl server client programming using openssl in c - AticleWorld**

An SSL is the standard security protocol used to establish an encrypted connection between a server and a client.SSL ensures data will be secured and intact

aticleworld.com



---

## 3. Java 로 간단한 SSL Client/Server 작성 테스트

출처: https://blueyikim.tistory.com/2529

Windows 환경에서 Java 기반의 SSL Client/SSL 동작을 위해 간단한 프로그램과 옵션을 정리합니다. WAS를 사용 시에도 기본적으로 Java 레벨에서 필요한 작업을 사전 인지하고 있을 필요가 있습니다.

 

Java 기반에서는 openssl도 사용할 수 있지만, %JAVA_HOME%\bin 에서 제공되는 keytool 유틸리티를 유용하게 사용합니다.

 

 

### 1. Server 프로그램 작성

1) 소스 작성

``` java
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class EchoSSLServer {

    public static void main(String[] arstring) {

        try {
            SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            // 포트 9999로 서버 소켓 생성
            SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);
            
            // 소켓 accept
            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String rcvdata = null;
 
            // 클라이언트로부터 전송된 데이터를 읽어서 출력함
            while ((rcvdata = bufferedreader.readLine()) != null) {
                System.out.println(rcvdata);
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
```

2) 서버 인증서(테스트용) 작성

 

SSL 프로토콜에서 필요한 서버 인증서를 작성합니다. 보통 운영시스템에서는 외부 인증서업체에서 유료로 발급받습니다. 여기에서는 간단한 테스트용으로 사용하기 위한 자체서명(self-signed) 서버 인증서입니다.

 

사용방법)

``` bash
keytool -genkey -keystore myKeystore -keyalg RSA
```

위에서 myKeyStore는 Key Store 이름이고, RSA는 키 알고리즘

아래와 같은 입력값을 요구합니다. 저는 KeyStore와 Private Key 비밀번호를 모두 weblogic으로 입력하여 생성하였습니다.

![img](.\images\AAAimg.png)



3) 서버 프로그램 실행

``` bash
java -Djavax.net.ssl.keyStore=myKeystore -Djavax.net.ssl.keyStorePassword=weblogic EchoSSLServer
```



### 2. Client 프로그램 작성

1) 소스 작성

``` java
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class EchoSSLClient {

public static void main(String[] arstring) {
    try {

        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        // 클라이언트 소켓 생성 (포트는 서버와 연결하기 위해 9999)
        SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

        OutputStream outputstream = sslsocket.getOutputStream();
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
        BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

        // 서버로 "hello world !!" 데이터 전송
        String senddata = "hello world !!";
        bufferedwriter.write(senddata + '\n');
        bufferedwriter.flush();
        
    } catch (Exception exception) {
        exception.printStackTrace();
    }
  }
}
```

2) 서버 인증서에 대한 CA 업데이트

이 작업은 클라이언트에서 서버 인증서를 신뢰할만한 인증서인지 인증서 발급 인증기관(CA, Certificate Authority)을 Java의 cacerts 파일에 등록합니다. 

 

\* 서버 인증서에서 CA 정보를 추출
``` bash
keytool -export -keystore myKeystore -alias mykey -file server.cer -storepass weblogic
```


\* 추출한 CA 정보를 자바 cacerts 파일에 정보 추가
``` bash
keytool -import -alias myappcert -file server.cer -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
```


\* alias 가 cacerts에 추가되었는지 확인함 (기존 ROOT CA로 알려진 verisign, comodo 등 기 등록되어 있음을 확인할 수 있음)
``` bash
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts"  -storepass changeit
```


``` bash
......
verisignuniversalrootca [jdk], 2016. 8. 26, trustedCertEntry, 
인증서 지문(SHA-256): 23:99:56:11:27:A5:71:25:DE:8C:EF:EA:61:0D:DF:2F:A0:78:B5:C8:06:7F:4E:82:82:90:BF:B8:60:E8:4B:3C
......
comodorsaca [jdk], 2016. 8. 26, trustedCertEntry, 
인증서 지문(SHA-256): 52:F0:E1:C4:E5:8E:C6:29:29:1B:60:31:7F:07:46:71:B8:5D:7E:A8:0D:5B:07:27:34:63:53:4B:32:B4:02:34
......
myappcert, 2022. 12. 21, trustedCertEntry, 
인증서 지문(SHA-256): C7:CC:D4:C7:35:13:7F:4C:FB:BA:0D:75:40:3F:8C:09:99:E7:B8:3A:9D:37:60:41:61:90:D5:BF:E0:4F:8B:D1
......
```



3) 클라이언트 실행

```bash
java  EchoSSLClient
```

 

서버 쪽에 "hello world !!" 메시지가 정상적으로 출력됨을 확인할 수 있습니다.

 

[글참조] [http://://blueyikim.tistory.com/2528](http:////blueyikim.tistory.com/2528)



---

## 4. Java SSL 관련 시스템 프로퍼티 

JVM 기동 시에 시스템 프로퍼티로 SSL 관련 옵션을 설정합니다. 즉, 프로그램을 실행할 때 설정하거나(java -D....) System.setProperty를 수행하여 코드에서 설정할 수 있습니다.

관련 옵션은 다음과 같습니다.

 

**javax.net.ssl.keyStore** - 애플리케이션 프로세스의 자체 인증서 및 개인 키를 포함하는 Java 키 저장소 파일의 위치입니다. Windows에서 지정된 경로 이름은 백슬래시 대신 슬래시(/)를 사용해야 합니다.

 

**javax.net.ssl.keyStorePassword** - javax.net.ssl.keyStore에 지정된 키 저장소 파일에서 개인 키에 액세스하기 위한 비밀번호입니다. 이 암호는 두 번 사용됩니다: 키 저장소 파일 잠금 해제(암호 저장) 및 키 저장소에 저장된 개인 키를 해독(키 암호).

 

**javax.net.ssl.keyStore** - 애플리케이션 프로세스의 자체 인증서 및 개인 키를 포함하는 Java 키 저장소 파일의 위치입니다. Windows에서 지정된 경로 이름은 백슬래시 대신 슬래시(/)를 사용해야 합니다.

 

**javax.net.ssl.trustStore** - 이 애플리케이션 프로세스(신뢰 저장소)에서 신뢰하는 CA 인증서 모음을 포함하는 Java 키 저장소 파일의 위치입니다. /Windows에서 지정된 경로 이름 은 백슬래시 대신 슬래시 를 사용해야 합니다 \.

이 속성을 사용하여 신뢰 저장소 위치를 지정하지 않으면 SunJSSE 구현은 다음 위치에서 키 저장소 파일을 검색하고 사용합니다(순서대로).

1. $JAVA_HOME/lib/security/jssecacerts
2. $JAVA_HOME/lib/security/cacerts

(참조) https://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/JSSERefGuide.html#X509TrustManager

 

**javax.net.ssl.trustStorePassword** - 지정한 키 저장소 파일(저장 암호)의 잠금을 해제하기 위한 암호 javax.net.ssl.trustStore입니다.

 

**javax.net.ssl.trustStoreType** - (선택 사항) Java 키 저장소 파일 형식의 경우 이 속성 값은 jks(또는 JKS)입니다. 기본값이 이미 jks이기 때문에 일반적으로 이 속성을 지정하지 않습니다.

 

**javax.net.debug** - SSL/TLS 계층에 대한 로깅을 켜려면 이 속성을 ssl로 설정합니다.



---

## 5. openssl_examples

출처: https://github.com/darrenjs/openssl_examples

### 5.1 OpenSSL 사용 예-1

ssl_server_nonblock.c는 메모리 BIO(BIO_s_mem)를 사용하여 비차단 소켓 IO로 SSL 읽기 및 쓰기를 수행하는 방법을 설명하는 간단한 OpenSSL 예제 프로그램입니다.

프로그램은 SSL 클라이언트의 연결을 수락합니다. 간단하게 유지하기 위해 단일 라이브 연결만 지원됩니다. 클라이언트가 연결되어 있는 동안 프로그램은 전송하는 모든 바이트를 수신하고 암호화를 해제하고 비차단 소켓 읽기를 사용하여 stdout에 기록합니다. 또한 비 차단 소켓 쓰기를 사용하여 stdin에서 읽고 바이트를 암호화하고 클라이언트로 보냅니다.

이 프로그램은 단일 스레드입니다. 이는 SSL 잠금을 설정할 필요가 없음을 의미합니다. 프로그램이 종료되지 않으므로 SSL 컨텍스트 및 라이브러리와 연결된 리소스를 해제하는 코드가 없습니다.

ssl_client_nonblock.c는 같은 프로그램의 클라이언트 버전입니다.

#### Compilation

프로그램을 컴파일하려면 다음과 같이 사용하십시오.

``` bash
gcc ssl_server_nonblock.c -Wall -O0 -g3 -std=c99 -lcrypto -lssl -o ssl_server_nonblock
```

또는 MacOS에서:

``` bash
 gcc -Wall -O0 -g3 -std=c99 -I/usr/local/opt/openssl/include -L/usr/local/opt/openssl/lib -lssl -lcrypto  -o ssl_server_nonblock ssl_server_nonblock.c
```

또는 Linux 플랫폼의 경우 makefile을 사용해 보십시오.

Ubuntu 시스템에서는 sudo apt install libssl-dev를 실행하여 OpenSSL 헤더를 설치해야 할 수 있습니다.

#### Running

프로그램을 실행하려면 SSL 인증서와 개인 키를 로드할 수 있어야 합니다. 이들은 다음 단계를 사용하여 'openssl' 프로그램을 사용하여 생성할 수 있습니다.

1. 개인 키를 생성합니다. 이것은 우리가 일반적으로 비밀로 유지하는 것입니다.

``` bash
openssl genrsa -des3 -passout pass:ABCD -out server.pass.key 2048
openssl rsa -passin pass:ABCD -in server.pass.key -out server.key
rm -f server.pass.key
```

2. 다음으로 CSR을 생성합니다. 메시지가 표시되면 비밀번호를 비워 둘 수 있습니다(자체 서명이기 때문).

``` bash
openssl req -new -key server.key -out server.csr
```

3. 다음으로 자체 서명된 인증서를 생성합니다.

``` bash
openssl x509 -req -sha256 -days 365 -in server.csr -signkey server.key -out server.crt
rm -f server.csr
```

openssl 프로그램을 사용하여 이 프로그램에 SSL 클라이언트로 연결할 수도 있습니다. 다음은 예제 명령입니다(포트 55555를 사용한다고 가정).

``` bash
openssl s_client -connect 127.0.0.1:55555 -msg -debug -state -showcerts
```

#### Flow of encrypted & unencrypted bytes

이 다이어그램은 읽기 및 쓰기 메모리 BIO(rbio 및 wbio)가 각각 소켓 읽기 및 쓰기와 연결되는 방법을 보여줍니다. 인바운드 흐름(프로그램으로의 데이터)에서 소켓에서 바이트를 읽고 BIO_write를 통해 rbio로 복사합니다. 이는 암호화된 데이터를 SSL 개체로 전송하는 것을 나타냅니다. 그런 다음 SSL_read를 호출하여 암호화되지 않은 데이터를 얻습니다. 암호화되지 않은 사용자 데이터를 암호화된 데이터의 소켓 쓰기로 전달하기 위해 아웃바운드 흐름에서 반대가 발생합니다.

``` bash
  +------+                                    +-----+
  |......|--> read(fd) --> BIO_write(rbio) -->|.....|--> SSL_read(ssl)  --> IN
  |......|                                    |.....|
  |.sock.|                                    |.SSL.|
  |......|                                    |.....|
  |......|<-- write(fd) <-- BIO_read(wbio) <--|.....|<-- SSL_write(ssl) <-- OUT
  +------+                                    +-----+

          |                                  |       |                     |
          |<-------------------------------->|       |<------------------->|
          |         encrypted bytes          |       |  unencrypted bytes  |
```



`common.h`

``` h
/*
  Copyright (c) 2017 Darren Smith

  ssl_examples is free software; you can redistribute it and/or modify
  it under the terms of the MIT license. See LICENSE for details.
*/

#include <openssl/bio.h>
#include <openssl/err.h>
#include <openssl/pem.h>
#include <openssl/ssl.h>

#include <arpa/inet.h>
#include <poll.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

/* Global SSL context */
SSL_CTX *ctx;

#define DEFAULT_BUF_SIZE 64

void handle_error(const char *file, int lineno, const char *msg) {
  fprintf(stderr, "** %s:%i %s\n", file, lineno, msg);
  ERR_print_errors_fp(stderr);
  exit(-1);
}

#define int_error(msg) handle_error(__FILE__, __LINE__, msg)

void die(const char *msg) {
  perror(msg);
  exit(1);
}

void print_unencrypted_data(char *buf, size_t len) {
  printf("%.*s", (int)len, buf);
}

/* An instance of this object is created each time a client connection is
 * accepted. It stores the client file descriptor, the SSL objects, and data
 * which is waiting to be either written to socket or encrypted. */
struct ssl_client
{
  int fd;

  SSL *ssl;

  BIO *rbio; /* SSL reads from, we write to. */
  BIO *wbio; /* SSL writes to, we read from. */

  /* Bytes waiting to be written to socket. This is data that has been generated
   * by the SSL object, either due to encryption of user input, or, writes
   * requires due to peer-requested SSL renegotiation. */
  char* write_buf;
  size_t write_len;

  /* Bytes waiting to be encrypted by the SSL object. */
  char* encrypt_buf;
  size_t encrypt_len;

  /* Store the previous state string */
  const char * last_state;

  /* Method to invoke when unencrypted bytes are available. */
  void (*io_on_read)(char *buf, size_t len);
} client;

/* This enum contols whether the SSL connection needs to initiate the SSL
 * handshake. */
enum ssl_mode { SSLMODE_SERVER, SSLMODE_CLIENT };


void ssl_client_init(struct ssl_client *p,
                     int fd,
                     enum ssl_mode mode)
{
  memset(p, 0, sizeof(struct ssl_client));

  p->fd = fd;

  p->rbio = BIO_new(BIO_s_mem());
  p->wbio = BIO_new(BIO_s_mem());
  p->ssl = SSL_new(ctx);

  if (mode == SSLMODE_SERVER)
    SSL_set_accept_state(p->ssl);  /* ssl server mode */
  else if (mode == SSLMODE_CLIENT)
    SSL_set_connect_state(p->ssl); /* ssl client mode */

  SSL_set_bio(p->ssl, p->rbio, p->wbio);

  p->io_on_read = print_unencrypted_data;
}


void ssl_client_cleanup(struct ssl_client *p)
{
  SSL_free(p->ssl);   /* free the SSL object and its BIO's */
  free(p->write_buf);
  free(p->encrypt_buf);
}


int ssl_client_want_write(struct ssl_client *cp) {
  return (cp->write_len>0);
}


/* Obtain the return value of an SSL operation and convert into a simplified
 * error code, which is easier to examine for failure. */
enum sslstatus { SSLSTATUS_OK, SSLSTATUS_WANT_IO, SSLSTATUS_FAIL};


static enum sslstatus get_sslstatus(SSL* ssl, int n)
{
  switch (SSL_get_error(ssl, n))
  {
    case SSL_ERROR_NONE:
      return SSLSTATUS_OK;
    case SSL_ERROR_WANT_WRITE:
    case SSL_ERROR_WANT_READ:
      return SSLSTATUS_WANT_IO;
    case SSL_ERROR_ZERO_RETURN:
    case SSL_ERROR_SYSCALL:
    default:
      return SSLSTATUS_FAIL;
  }
}


/* Handle request to send unencrypted data to the SSL.  All we do here is just
 * queue the data into the encrypt_buf for later processing by the SSL
 * object. */
void send_unencrypted_bytes(const char *buf, size_t len)
{
  client.encrypt_buf = (char*)realloc(client.encrypt_buf, client.encrypt_len + len);
  memcpy(client.encrypt_buf+client.encrypt_len, buf, len);
  client.encrypt_len += len;
}


/* Queue encrypted bytes. Should only be used when the SSL object has requested a
 * write operation. */
void queue_encrypted_bytes(const char *buf, size_t len)
{
  client.write_buf = (char*)realloc(client.write_buf, client.write_len + len);
  memcpy(client.write_buf+client.write_len, buf, len);
  client.write_len += len;
}


void print_ssl_state()
{
  const char * current_state = SSL_state_string_long(client.ssl);
  if (current_state != client.last_state) {
    if (current_state)
      printf("SSL-STATE: %s\n", current_state);
    client.last_state = current_state;
  }
}


void print_ssl_error()
{
  BIO *bio = BIO_new(BIO_s_mem());
  ERR_print_errors(bio);
  char *buf;
  size_t len = BIO_get_mem_data(bio, &buf);
  if (len > 0)
    printf("SSL-ERROR: %s", buf);
  BIO_free(bio);
}


enum sslstatus do_ssl_handshake()
{
  char buf[DEFAULT_BUF_SIZE];
  enum sslstatus status;

  print_ssl_state();
  int n = SSL_do_handshake(client.ssl);
  print_ssl_state();
  status = get_sslstatus(client.ssl, n);

  /* Did SSL request to write bytes? */
  if (status == SSLSTATUS_WANT_IO)
    do {
      n = BIO_read(client.wbio, buf, sizeof(buf));
      if (n > 0)
        queue_encrypted_bytes(buf, n);
      else if (!BIO_should_retry(client.wbio))
        return SSLSTATUS_FAIL;
    } while (n>0);

  return status;
}

/* Process SSL bytes received from the peer. The data needs to be fed into the
   SSL object to be unencrypted.  On success, returns 0, on SSL error -1. */
int on_read_cb(char* src, size_t len)
{
  char buf[DEFAULT_BUF_SIZE];
  enum sslstatus status;
  int n;

  while (len > 0) {
    n = BIO_write(client.rbio, src, len);

    if (n<=0)
      return -1; /* assume bio write failure is unrecoverable */

    src += n;
    len -= n;

    if (!SSL_is_init_finished(client.ssl)) {
      if (do_ssl_handshake() == SSLSTATUS_FAIL)
        return -1;
      if (!SSL_is_init_finished(client.ssl))
        return 0;
    }

    /* The encrypted data is now in the input bio so now we can perform actual
     * read of unencrypted data. */

    do {
      n = SSL_read(client.ssl, buf, sizeof(buf));
      if (n > 0)
        client.io_on_read(buf, (size_t)n);
    } while (n > 0);

    status = get_sslstatus(client.ssl, n);

    /* Did SSL request to write bytes? This can happen if peer has requested SSL
     * renegotiation. */
    if (status == SSLSTATUS_WANT_IO)
      do {
        n = BIO_read(client.wbio, buf, sizeof(buf));
        if (n > 0)
          queue_encrypted_bytes(buf, n);
        else if (!BIO_should_retry(client.wbio))
          return -1;
      } while (n>0);

    if (status == SSLSTATUS_FAIL)
      return -1;
  }

  return 0;
}

/* Process outbound unencrypted data that is waiting to be encrypted.  The
 * waiting data resides in encrypt_buf.  It needs to be passed into the SSL
 * object for encryption, which in turn generates the encrypted bytes that then
 * will be queued for later socket write. */
int do_encrypt()
{
  char buf[DEFAULT_BUF_SIZE];
  enum sslstatus status;

  if (!SSL_is_init_finished(client.ssl))
    return 0;

  while (client.encrypt_len>0) {
    int n = SSL_write(client.ssl, client.encrypt_buf, client.encrypt_len);
    status = get_sslstatus(client.ssl, n);

    if (n>0) {
      /* consume the waiting bytes that have been used by SSL */
      if ((size_t)n<client.encrypt_len)
        memmove(client.encrypt_buf, client.encrypt_buf+n, client.encrypt_len-n);
      client.encrypt_len -= n;
      client.encrypt_buf = (char*)realloc(client.encrypt_buf, client.encrypt_len);

      /* take the output of the SSL object and queue it for socket write */
      do {
        n = BIO_read(client.wbio, buf, sizeof(buf));
        if (n > 0)
          queue_encrypted_bytes(buf, n);
        else if (!BIO_should_retry(client.wbio))
          return -1;
      } while (n>0);
    }

    if (status == SSLSTATUS_FAIL)
      return -1;

    if (n==0)
      break;
  }
  return 0;
}


/* Read bytes from stdin and queue for later encryption. */
void do_stdin_read()
{
  char buf[DEFAULT_BUF_SIZE];
  ssize_t n = read(STDIN_FILENO, buf, sizeof(buf));
  if (n>0)
    send_unencrypted_bytes(buf, (size_t)n);
}


/* Read encrypted bytes from socket. */
int do_sock_read()
{
  char buf[DEFAULT_BUF_SIZE];
  ssize_t n = read(client.fd, buf, sizeof(buf));

  if (n>0)
    return on_read_cb(buf, (size_t)n);
  else
    return -1;
}


/* Write encrypted bytes to the socket. */
int do_sock_write()
{
  ssize_t n = write(client.fd, client.write_buf, client.write_len);
  if (n>0) {
    if ((size_t)n<client.write_len)
      memmove(client.write_buf, client.write_buf+n, client.write_len-n);
    client.write_len -= n;
    client.write_buf = (char*)realloc(client.write_buf, client.write_len);
    return 0;
  }
  else
    return -1;
}


void ssl_init(const char * certfile, const char* keyfile)
{
  /* SSL library initialisation */

  SSL_library_init();
  OpenSSL_add_all_algorithms();
  SSL_load_error_strings();
#if OPENSSL_VERSION_MAJOR < 3
  ERR_load_BIO_strings(); // deprecated since OpenSSL 3.0
#endif
  ERR_load_crypto_strings();

  /* create the SSL server context */
  ctx = SSL_CTX_new(TLS_method());
  if (!ctx)
    die("SSL_CTX_new()");

  /* Load certificate and private key files, and check consistency */
  if (certfile && keyfile) {
    if (SSL_CTX_use_certificate_file(ctx, certfile,  SSL_FILETYPE_PEM) != 1)
      int_error("SSL_CTX_use_certificate_file failed");

    if (SSL_CTX_use_PrivateKey_file(ctx, keyfile, SSL_FILETYPE_PEM) != 1)
      int_error("SSL_CTX_use_PrivateKey_file failed");

    /* Make sure the key and certificate file match. */
    if (SSL_CTX_check_private_key(ctx) != 1)
      int_error("SSL_CTX_check_private_key failed");
    else
      printf("certificate and private key loaded and verified\n");
  }


  /* Recommended to avoid SSLv2 & SSLv3 */
  SSL_CTX_set_options(ctx, SSL_OP_ALL|SSL_OP_NO_SSLv2|SSL_OP_NO_SSLv3);
}

```



`ssl_server_nonblock.c`

``` c
/*
Copyright (c) 2017 Darren Smith

ssl_examples is free software; you can redistribute it and/or modify
it under the terms of the MIT license. See LICENSE for details.
*/

#include "common.h"


int main(int argc, char **argv)
{
  char str[INET_ADDRSTRLEN];
  int port = (argc>1)? atoi(argv[1]):55555;

  int servfd = socket(AF_INET, SOCK_STREAM, 0);
  if (servfd < 0)
    die("socket()");

  int enable = 1;
  if (setsockopt(servfd, SOL_SOCKET, SO_REUSEADDR, &enable, sizeof(enable)) < 0)
    die("setsockopt(SO_REUSEADDR)");

  /* Specify socket address */
  struct sockaddr_in servaddr;
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port = htons(port);

  if (bind(servfd, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0)
    die("bind()");

  if (listen(servfd, 128) < 0)
    die("listen()");

  int clientfd;
  struct sockaddr_in peeraddr;
  socklen_t peeraddr_len = sizeof(peeraddr);

  struct pollfd fdset[2];
  memset(&fdset, 0, sizeof(fdset));

  fdset[0].fd = STDIN_FILENO;
  fdset[0].events = POLLIN;

  ssl_init("server.crt", "server.key"); // see README to create these files

  while (1) {
    printf("waiting for next connection on port %d\n", port);

    clientfd = accept(servfd, (struct sockaddr *)&peeraddr, &peeraddr_len);
    if (clientfd < 0)
      die("accept()");

    ssl_client_init(&client, clientfd, SSLMODE_SERVER);

    inet_ntop(peeraddr.sin_family, &peeraddr.sin_addr, str, INET_ADDRSTRLEN);
    printf("new connection from %s:%d\n", str, ntohs(peeraddr.sin_port));

    fdset[1].fd = clientfd;

    /* event loop */

    fdset[1].events = POLLERR | POLLHUP | POLLNVAL | POLLIN;
#ifdef POLLRDHUP
    fdset[1].events |= POLLRDHUP;
#endif

    while (1) {
      fdset[1].events &= ~POLLOUT;
      fdset[1].events |= (ssl_client_want_write(&client)? POLLOUT : 0);

      int nready = poll(&fdset[0], 2, -1);

      if (nready == 0)
        continue; /* no fd ready */

      int revents = fdset[1].revents;
      if (revents & POLLIN)
        if (do_sock_read() == -1)
          break;
      if (revents & POLLOUT)
        if (do_sock_write() == -1)
          break;
      if (revents & (POLLERR | POLLHUP | POLLNVAL))
        break;
#ifdef POLLRDHUP
      if (revents & POLLRDHUP)
        break;
#endif
      if (fdset[0].revents & POLLIN)
        do_stdin_read();
      if (client.encrypt_len>0)
        do_encrypt();
    }

    close(fdset[1].fd);
    ssl_client_cleanup(&client);
  }

  return 0;
}
```



`ssl_client_nonblock.c`

``` c
/*
  Copyright (c) 2017 Darren Smith

  ssl_examples is free software; you can redistribute it and/or modify
  it under the terms of the MIT license. See LICENSE for details.
*/

#include "common.h"

int main(int argc, char **argv)
{
  /* --- CONFIGURE PEER SOCKET --- */

  // port name, optionally take from args
  int port = argc>1? atoi(argv[1]):443;

  // host IP address. Attention! This must be a numeric address, not a server
  // host name, because this example code does not perform address lookup.
  char* host_ip = "2600:9000:225d:600:14:c251:2440:93a1";

  // provide the hostname if this SSL client needs to use SNI to tell the server
  // what certificate to use
  const char * host_name = "api.huobi.pro";

  // socket family, AF_INET (ipv4) or AF_INET6 (ipv6), must match host_ip above
  int ip_family = AF_INET6;

  /* Example for localhost connection
     int port = argc>1? atoi(argv[1]):55555;
     const char* host_ip = "127.0.0.1";
     const char * host_name = NULL;
     int ip_family = AF_INET;
  */


  /* --- CONFIGURAITON ENDS --- */

  int sockfd = socket(ip_family, SOCK_STREAM, 0);

  if (sockfd < 0)
    die("socket()");

  /* Specify socket address */

  if (ip_family == AF_INET6) {
    struct sockaddr_in6 addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin6_family = ip_family;
    addr.sin6_port = htons(port);

    if (inet_pton(ip_family, host_ip, &(addr.sin6_addr)) <= 0)
      die("inet_pton()");

    if (connect(sockfd, (struct sockaddr*) &addr, sizeof(addr)) < 0)
      die("connect()");
  }

  if (ip_family == AF_INET) {
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin_family = ip_family;
    addr.sin_port = htons(port);

    if (inet_pton(ip_family, host_ip, &(addr.sin_addr)) <= 0)
      die("inet_pton()");

    if (connect(sockfd, (struct sockaddr*) &addr, sizeof(addr)) < 0)
      die("connect()");
  }

  printf("socket connected\n");

  struct pollfd fdset[2];
  memset(&fdset, 0, sizeof(fdset));

  fdset[0].fd = STDIN_FILENO;
  fdset[0].events = POLLIN;

  ssl_init(0,0);
  ssl_client_init(&client, sockfd, SSLMODE_CLIENT);

  if (host_name)
    SSL_set_tlsext_host_name(client.ssl, host_name); // TLS SNI

  fdset[1].fd = sockfd;
  fdset[1].events = POLLERR | POLLHUP | POLLNVAL | POLLIN;
#ifdef POLLRDHUP
  fdset[1].events |= POLLRDHUP;
#endif

  /* event loop */

  do_ssl_handshake();

  while (1) {
    fdset[1].events &= ~POLLOUT;
    fdset[1].events |= ssl_client_want_write(&client)? POLLOUT:0;

    int nready = poll(&fdset[0], 2, -1);

    if (nready == 0)
      continue; /* no fd ready */

    int revents = fdset[1].revents;
    if (revents & POLLIN)
      if (do_sock_read() == -1)
        break;
    if (revents & POLLOUT)
      if (do_sock_write() == -1)
        break;
    if (revents & (POLLERR | POLLHUP | POLLNVAL))
      break;
#ifdef POLLRDHUP
    if (revents & POLLRDHUP)
      break;
#endif
    if (fdset[0].revents & POLLIN)
      do_stdin_read();
    if (client.encrypt_len>0)
      if (do_encrypt() < 0)
        break;
  }

  close(fdset[1].fd);
  print_ssl_state();
  print_ssl_error();
  ssl_client_cleanup(&client);

  return 0;
}

```



`Makefile`

```Makefile
/*
  Copyright (c) 2017 Darren Smith

  ssl_examples is free software; you can redistribute it and/or modify
  it under the terms of the MIT license. See LICENSE for details.
*/

#include "common.h"

int main(int argc, char **argv)
{
  /* --- CONFIGURE PEER SOCKET --- */

  // port name, optionally take from args
  int port = argc>1? atoi(argv[1]):443;

  // host IP address. Attention! This must be a numeric address, not a server
  // host name, because this example code does not perform address lookup.
  char* host_ip = "2600:9000:225d:600:14:c251:2440:93a1";

  // provide the hostname if this SSL client needs to use SNI to tell the server
  // what certificate to use
  const char * host_name = "api.huobi.pro";

  // socket family, AF_INET (ipv4) or AF_INET6 (ipv6), must match host_ip above
  int ip_family = AF_INET6;

  /* Example for localhost connection
     int port = argc>1? atoi(argv[1]):55555;
     const char* host_ip = "127.0.0.1";
     const char * host_name = NULL;
     int ip_family = AF_INET;
  */


  /* --- CONFIGURAITON ENDS --- */

  int sockfd = socket(ip_family, SOCK_STREAM, 0);

  if (sockfd < 0)
    die("socket()");

  /* Specify socket address */

  if (ip_family == AF_INET6) {
    struct sockaddr_in6 addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin6_family = ip_family;
    addr.sin6_port = htons(port);

    if (inet_pton(ip_family, host_ip, &(addr.sin6_addr)) <= 0)
      die("inet_pton()");

    if (connect(sockfd, (struct sockaddr*) &addr, sizeof(addr)) < 0)
      die("connect()");
  }

  if (ip_family == AF_INET) {
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin_family = ip_family;
    addr.sin_port = htons(port);

    if (inet_pton(ip_family, host_ip, &(addr.sin_addr)) <= 0)
      die("inet_pton()");

    if (connect(sockfd, (struct sockaddr*) &addr, sizeof(addr)) < 0)
      die("connect()");
  }

  printf("socket connected\n");

  struct pollfd fdset[2];
  memset(&fdset, 0, sizeof(fdset));

  fdset[0].fd = STDIN_FILENO;
  fdset[0].events = POLLIN;

  ssl_init(0,0);
  ssl_client_init(&client, sockfd, SSLMODE_CLIENT);

  if (host_name)
    SSL_set_tlsext_host_name(client.ssl, host_name); // TLS SNI

  fdset[1].fd = sockfd;
  fdset[1].events = POLLERR | POLLHUP | POLLNVAL | POLLIN;
#ifdef POLLRDHUP
  fdset[1].events |= POLLRDHUP;
#endif

  /* event loop */

  do_ssl_handshake();

  while (1) {
    fdset[1].events &= ~POLLOUT;
    fdset[1].events |= ssl_client_want_write(&client)? POLLOUT:0;

    int nready = poll(&fdset[0], 2, -1);

    if (nready == 0)
      continue; /* no fd ready */

    int revents = fdset[1].revents;
    if (revents & POLLIN)
      if (do_sock_read() == -1)
        break;
    if (revents & POLLOUT)
      if (do_sock_write() == -1)
        break;
    if (revents & (POLLERR | POLLHUP | POLLNVAL))
      break;
#ifdef POLLRDHUP
    if (revents & POLLRDHUP)
      break;
#endif
    if (fdset[0].revents & POLLIN)
      do_stdin_read();
    if (client.encrypt_len>0)
      if (do_encrypt() < 0)
        break;
  }

  close(fdset[1].fd);
  print_ssl_state();
  print_ssl_error();
  ssl_client_cleanup(&client);

  return 0;
}

```



---

## 6. OpenSSL 설치 및 Visual Studio 적용하기

출처: https://domdom.tistory.com/225



우선 제가 하려고 하는 것은 OpenSSL 라이브러리를 사용하는 C언어 프로젝트를 빌드하려는 것입니다. 우선 제 개발 환경부터 설명드리겠습니다.

 

\- 운영체제 : Windows 10

\- 개발도구 : Visual Studio 2019 Community

\- 컴파일환경 : x64 (64bit)

\- OpenSSL 버전 : v1.1.1 x64

 

그리고 OpenSSL 설치 및 Visual Studio 환경에 적용하기까지 아래와 같은 순서대로 설명할 예정입니다.

 

1. OpenSSL 설치
2. Visual Studio Project 설정
3. 빌드 및 빌드 결과 확인

 

## 1. OpenSSL 설치

http://slproweb.com/products/Win32OpenSSL.html



위 사이트에 들어갑니다. 그리고 Win64 OpenSSL v1.1.1L 설치파일을 다운받습니다. EXE 든 MSI 든 상관없습니다. 저는 EXE를 다운받아보겠습니다.



![Pn1DdLMVvIlusiWOMy4pf1-img.png](.\images\Pn1DdLMVvIlusiWOMy4pf1-img.png)



다운 받으면 아래와 같이 생겼습니다. Win64OpenSSL-1_1_1L.exe 입니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\lbvqVKEWQZJ3CPJ9Qn2G90-img.png)



실행 시키면 아래와 같이 나옵니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\9yPVknGoV0EkKvDse03kxK-img.png)



동의 버튼에 체크 후 Next 버튼을 눌러서 다음으로 넘어갑니다.

그 다음 또 다음버튼을 누르면 아래와 같은 화면이 나옵니다. 여기서 저는 Windows 를 사용하기 때문에 아래와 같이 체크하고 다음으로 넘어갑니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\QrHnBK7PfqIuSceFNyzKnK-img.png)



그리고 다음에 나오는 Install 버튼을 눌러 설치하면 아래와 같이 나오는데 여기서 그냥 체크 해제 후 Finish 누르면 됩니다. (기부하고 싶으시면 하세요.)



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\O8oMTkrkvbM1SCkj5Sas2k-img.png)



잘 설치되었는 지 아래 경로로 가서 확인해보겠습니다. 본인이 설치하신 경로는 설치할 때 나오니 꼭 한번 확인해보세요.

```
C:\Program Files\OpenSSL-Win64
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\kmrch8LnZkueOkiTlTMYok-img.png)



잘 설치된 것 같습니다. 이제 Visual Studio 에서 Project 설정에 들어가보겠습니다.

 

## 2. Visual Studio Project 설정

우선 이 프로젝트에서 사용한 샘플코드는 아래 글에서 보실 수 있습니다.

https://domdom.tistory.com/222

 

C언어로 만드는 DES 복호화 예제(python 코드도 포함)

이번에 수업 시간에 과제로 했던 것을 정리해봅니다. 과제 내용은 DES 암호화된 파일이 주어지고, 해당 내용을 주어진 key 값으로 복호화하는 코드를 C언어로 작성하라는 것이었습니다. Python으로

domdom.tistory.com

Visual Studio 를 별도의 라이브러리 설정 없이 여시고 소스코드를 작성하면 아래와 같이 라이브러리를 찾을 수 없어서 openssl 부분과 관련 변수명에 빨간줄이 그어지면서 오류가 난 것을 볼 수 있습니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\B64MyolaojEWWQm58ERkNk-img.png)



관련 오류를 없애기 위해서는 우리가 설치한 OpenSSL 라이브러리를 import 해주어야 하는데요. 우선 프로젝트 속성에 들어가보기 전에 저희가 설치한 OpenSSL 버전과 일치한 bit 버전을 맞춰주어야 합니다. x64 로 맞춰져있는 지 확인해주세요.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\NwUGdfLBf8YAlac0UGK1x0-img.png)



이제 프로젝트 속성에 들어가보겠습니다. 소스코드를 띄워주신 다음에 해당 소스코드 내용에 커서를 두고서(그냥 클릭하라는 의미) 프로젝트 메뉴를 봐주세요. 그리고 (프로젝트이름) 속성 버튼을 눌러주세요.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\aHF6PaiMfZB9KStzWHD7kK-img.png)



누르시면 아래와 같이 나옵니다. 여기서 저희는 [구성 속성] -> [VC+ 디렉터리] 탭에서 [포함 디렉터리] 와 [라이브러리 디렉터리]만 보시면 됩니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\KrJBnhba2gtbsGS1i3Onb0-img.png)



우선 [포함 디렉터리]를 설정해주겠습니다. 아래 방향의 화살표를 눌러주시고 누르면 나오는 편집을 클릭해줍니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\7Q4qrgL8Jez973U40YLkEk-img.png)



그럼 위와 같이 나오는데, 여기서 빈 칸에다가 본인이 설치한 OpenSSL-Win64 의 include 폴더를 경로로 지정해줍니다.

```
C:\Program Files\OpenSSL-Win64\include
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\V0AWQSupbk6S9GvMNm1yj1-img.png)



그런 다음에 확인 버튼을 누릅니다. 이제는 [라이브러리 디렉터리]를 설정해줘야 합니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\VzL2ywiDCtrnuhXosKmK2k-img.png)



```
C:\Program Files\OpenSSL-Win64\lib\VC
C:\Program Files\OpenSSL-Win64\lib
```

그런 다음에 [링커] -> [입력] 탭에서 [추가 종속성]을 설정해줘야 합니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\K1swqcHk3GCO9PSu24JYc1-img.png)



여기서도 마찬가지로 화살표를 누르면 편집이 나오고 편집을 누르면 아래와 같이 나옵니다. 그리고 아래 빈칸에다가 OpenSSL 라이브러리를 전부 import 해주면 되는데요. 하나하나 타이핑하기 귀찮으니 명령어를 사용합시다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\NfYvKWXfIW8wziO86cY5Uk-img.png)



일단 아래 두 경로에 존재하는 모든 라이브러리들을 가져올겁니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\2KC19FIJxgIDFHZJtnaKzK-img.png)



```
C:\Program Files\OpenSSL-Win64\lib\VC
C:\Program Files\OpenSSL-Win64\lib
```

본인이 Windows 운영체제를 사용하시는 분이라면 저와 같이 cmd(명령프롬프트)를 여시고 아래 명령어를 입력하셔서 파일 목록을 뽑아옵니다.

```
dir VC /b /a-d | findstr /v /i ".def$" & dir /b /a-d | findstr /v /i ".def$"
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\6Ohono14JMRPro0gMZPHO1-img.png)



그리고 이제 저 파일 목록들을 드래그해서 복사한 다음에 추가 종속성 목록에 추가해줍니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\4WQlt3LUdYlvWhz4y57ojK-img.png)



그리고 확인 버튼을 눌러줍니다. 그리고 마지막으로 [적용] 버튼을 눌러주면 됩니다. 그리고 확인 버튼을 누르면 끝입니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\oGkxtxM9BBjjEt5NfxAovK-img.png)



그럼 이제 소스코드를 확인해보시면 빨간 밑줄이 안쳐져있고, 오류도 없는 걸 확인할 수 있습니다. 그리고 빌드 해보시면 성공 1 이라고 나오는 걸 보실 수 있을 겁니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\fKIMgfokHGub1mDaAQVcq1-img.png)



---

## 7. Visual Studio 2019 를 이용한 예제.

출처: https://stackoverflow.com/questions/70755464/tls-1-2-implementation-in-c-windows-application-using-openssl

`Client`

``` cpp
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#include <errno.h>
#include <string.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <WS2tcpip.h>
#include <string>
#include <iostream>

#pragma comment (lib, "ws2_32.lib")

#define FAIL    -1

//Added the LoadCertificates how in the server-side makes.    
void LoadCertificates(SSL_CTX* ctx, char* CertFile, char* KeyFile)
{
    /* set the local certificate from CertFile */
    if (SSL_CTX_use_certificate_file(ctx, CertFile, SSL_FILETYPE_PEM) <= 0)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    /* set the private key from KeyFile (may be the same as CertFile) */
    if (SSL_CTX_use_PrivateKey_file(ctx, KeyFile, SSL_FILETYPE_PEM) <= 0)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    /* verify private key */
    if (!SSL_CTX_check_private_key(ctx))
    {
        fprintf(stderr, "Private key does not match the public certificate\n");
        abort();
    }
}

int OpenConnection(const char* hostname, int port)
{
    int sd;
    struct hostent* host;
    struct sockaddr_in addr;
    WSAData data;
    WORD ver = MAKEWORD(2, 2);
    int wsResult = WSAStartup(ver, &data);
    if (wsResult != 0)
    {
        printf("winsock error");
        return 0;
    }

    if ((host = gethostbyname(hostname)) == NULL)
    {
        perror(hostname);
        abort();
    }
    sd = socket(PF_INET, SOCK_STREAM, 0);
    ZeroMemory(&addr, sizeof(addr));
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = *(long*)(host->h_addr);
    if (connect(sd, (struct sockaddr*)&addr, sizeof(addr)) != 0)
    {
        closesocket(sd);
        perror(hostname);
        abort();
    }
    return sd;
}

SSL_CTX* InitCTX(void)
{
    const SSL_METHOD* method = TLS_client_method(); /* Create new client-method instance */
    SSL_CTX* ctx;

    OpenSSL_add_all_algorithms();  /* Load cryptos, et.al. */
    SSL_load_error_strings();   /* Bring in and register error messages */
   // method = SSLv3_client_method();  /* Create new client-method instance */
    ctx = SSL_CTX_new(method);   /* Create new context */
    if (ctx == NULL)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    return ctx;
}

void ShowCerts(SSL* ssl)
{
    X509* cert;
    char* line;

    cert = SSL_get_peer_certificate(ssl); /* get the server's certificate */
    if (cert != NULL)
    {
        printf("Server certificates:\n");
        line = X509_NAME_oneline(X509_get_subject_name(cert), 0, 0);
        printf("Subject: %s\n", line);
        free(line);       /* free the malloc'ed string */
        line = X509_NAME_oneline(X509_get_issuer_name(cert), 0, 0);
        printf("Issuer: %s\n", line);
        free(line);       /* free the malloc'ed string */
        X509_free(cert);     /* free the malloc'ed certificate copy */
    }
    else
        printf("No certificates.\n");
}

int main()
{
    SSL_CTX* ctx;
    int server;
    SSL* ssl;
    char buf[1024];
    int bytes;
    char hostname[] = "127.0.0.1";
    char portnum[] = "54000";
    char CertFile[] = "C:/Users/cert/Documents/testing/ec_crt.pem";
    char KeyFile[] = "C:/Users/cert/Documents/testing/private-key.pem";

    SSL_library_init();

    ctx = InitCTX();
    LoadCertificates(ctx, CertFile, KeyFile);
    
    printf("clinet certificate loaded");
    server = OpenConnection(hostname, atoi(portnum));
    ssl = SSL_new(ctx);      /* create new SSL connection state */
    SSL_set_fd(ssl, server);    /* attach the socket descriptor */
    if (SSL_connect(ssl) == FAIL)   /* perform the connection */ {
        printf("Connection failed");
        ERR_print_errors_fp(stderr);
    }
    else
    {
        const char* msg = "Hello???";

        printf("Connected with %s encryption\n", SSL_get_cipher(ssl));
        ShowCerts(ssl);        /* get any certs */
        SSL_write(ssl, msg, strlen(msg));   /* encrypt & send message */
        bytes = SSL_read(ssl, buf, sizeof(buf)); /* get reply & decrypt */
        buf[bytes] = 0;
        printf("Received: \"%s\"\n", buf);
        SSL_free(ssl);        /* release connection state */
    }
    closesocket(server);         /* close socket */
    SSL_CTX_free(ctx);        /* release context */
    return 0;
}
```





`Server`

``` cpp
#include <errno.h>

#include <malloc.h>
#include <string.h>

#include <sys/types.h>


#include "openssl/ssl.h"
#include "openssl/err.h"
#include <WS2tcpip.h>
#include <string>

#pragma comment (lib, "ws2_32.lib")


#define FAIL    -1

int OpenListener(int port)
{
    int sd;
    struct sockaddr_in addr;
    WSAData data;
    WORD ver = MAKEWORD(2, 2);
    int wsResult = WSAStartup(ver, &data);
    if (wsResult != 0)
    {
        printf("winsock error");
        return 0;
    }

    sd = socket(PF_INET, SOCK_STREAM, 0);
    ZeroMemory(&addr, sizeof(addr));
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = INADDR_ANY;
    if (bind(sd, (struct sockaddr*)&addr, sizeof(addr)) != 0)
    {
        perror("can't bind port");
        abort();
    }
    if (listen(sd, 10) != 0)
    {
        perror("Can't configure listening port");
        abort();
    }
    listen(sd, SOMAXCONN);
    return sd;
}

SSL_CTX* InitServerCTX(void)
{
    const SSL_METHOD* method = TLS_client_method(); /* Create new client-method instance */
    SSL_CTX* ctx;

    OpenSSL_add_all_algorithms();  /* load & register all cryptos, etc. */
    SSL_load_error_strings();   /* load all error messages */
    
    ctx = SSL_CTX_new(method);   /* create new context from method */
    if (ctx == NULL)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    return ctx;
}

void LoadCertificates(SSL_CTX* ctx, char* CertFile, char* KeyFile)
{
    //New lines 
    if (SSL_CTX_load_verify_locations(ctx, CertFile, KeyFile) != 1)
        ERR_print_errors_fp(stderr);

    if (SSL_CTX_set_default_verify_paths(ctx) != 1)
        ERR_print_errors_fp(stderr);
    //End new lines

    /* set the local certificate from CertFile */
    if (SSL_CTX_use_certificate_file(ctx, CertFile, SSL_FILETYPE_PEM) <= 0)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    /* set the private key from KeyFile (may be the same as CertFile) */
    if (SSL_CTX_use_PrivateKey_file(ctx, KeyFile, SSL_FILETYPE_PEM) <= 0)
    {
        ERR_print_errors_fp(stderr);
        abort();
    }
    /* verify private key */
    if (!SSL_CTX_check_private_key(ctx))
    {
        fprintf(stderr, "Private key does not match the public certificate\n");
        abort();
    }

    //New lines - Force the client-side have a certificate
    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER | SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);
    SSL_CTX_set_verify_depth(ctx, 4);
    //End new lines
}

void ShowCerts(SSL* ssl)
{
    X509* cert;
    char* line;

    cert = SSL_get_peer_certificate(ssl); /* Get certificates (if available) */
    if (cert != NULL)
    {
        printf("Server certificates:\n");
        line = X509_NAME_oneline(X509_get_subject_name(cert), 0, 0);
        printf("Subject: %s\n", line);
        free(line);
        line = X509_NAME_oneline(X509_get_issuer_name(cert), 0, 0);
        printf("Issuer: %s\n", line);
        free(line);
        X509_free(cert);
    }
    else
        printf("No certificates.\n");
}

void Servlet(SSL* ssl) /* Serve the connection -- threadable */
{
    char buf[1024];
    char reply[1024];
    int sd, bytes;
    const char* HTMLecho = "<html><body><pre>%s</pre></body></html>\n\n";

    if (SSL_accept(ssl) == FAIL)     /* do SSL-protocol accept */
        ERR_print_errors_fp(stderr);
    else
    {
        ShowCerts(ssl);        /* get any certificates */
        bytes = SSL_read(ssl, buf, sizeof(buf)); /* get request */
        if (bytes > 0)
        {
            buf[bytes] = 0;
            printf("Client msg: \"%s\"\n", buf);
           // sprintf(reply, HTMLecho, buf);   /* construct reply */
            SSL_write(ssl, reply, strlen(reply)); /* send reply */
        }
        else
            ERR_print_errors_fp(stderr);
    }
    sd = SSL_get_fd(ssl);       /* get socket connection */
    SSL_free(ssl);         /* release SSL state */
    closesocket(sd);          /* close connection */
}

int main()
{
    SSL_CTX* ctx;
    int server;
    char portnum[] = "54000";

    char CertFile[] = "C:/Users/cert/Documents/testing/ec_crt.pem";
    char KeyFile[] = "C:/Users/cert/Documents/testing/private-key.pem";

    SSL_library_init();

    ctx = InitServerCTX();        /* initialize SSL */
    LoadCertificates(ctx, CertFile, KeyFile); /* load certs */
    printf("Certificate loaded");
    server = OpenListener(atoi(portnum));    /* create server socket */
    while (1)
    {
        struct sockaddr_in addr;
        socklen_t len = sizeof(addr);
        SSL* ssl;

        int client = accept(server, (struct sockaddr*)&addr, &len);  /* accept connection as usual */
        //printf("Connection: %s:%d\n", inet_ntop(addr.sin_addr), ntohs(addr.sin_port));
        printf("Connected");
        ssl = SSL_new(ctx);              /* get new SSL state with context */
        SSL_set_fd(ssl, client);      /* set connection socket to SSL state */
        Servlet(ssl);         /* service connection */
    }
    closesocket(server);          /* close server socket */
    SSL_CTX_free(ctx);         /* release context */
}
```

'TLS 1.2 implementation in C++ Windows Application using OpenSSL'

Winsocket을 사용하여 클라이언트와 서버를 만들고 Visual Studio 2019에서 OpenSSL을 사용하여 TLS 통신을 활성화하려고 합니다. 문제는 서버에 연결되고 서버가 클라이언트에 연결된 메시지를 인쇄하고 연결된 클라이언트 코드도 표시하지만 SSL_Connect 메서드에서는 실패하고 클라이언트 코드에 오류가 표시되지 않는다는 것입니다! 나는 또한 일반 TCP 소켓 서버를 만들고 연결 후 실패하는 것보다 이것에 연결하려고 시도합니다.



<span style="color: red">**다음을 사용해야 할 수도 있습니다. **</span>

``` cpp
const SSL_METHOD* method = TLS_server_method();
```

<span style="color: red">**InitServerCtx() 에서 ?**</span>



