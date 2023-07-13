# FTP-FTPS-SFTP 기술자료

출처: https://cloudedi.tistory.com/9



오늘은 프로토콜에서 FTP와 FTPS, SFTP를 알아보겠습니다.

(프로토콜부터 공부하고 싶다면, 여기서 확인하세요↓)

 

프로토콜(Protocol)

클라우드 EDI에서 다양한 통신 프로토콜을 지원하고, 보안 프로토콜을 통한다고 합니다. 오늘은 여기서 말하는 프로토콜이 무엇인지 알아보도록 하겠습니다. 프로토콜이란? 정보기기 사이 즉 컴

cloudedi.tistory.com

 

 

------

> **FTP**

 

FTP는 **인터넷을 통해 파일 송수신을 지원하기 위해 고안된 프로토콜**입니다.

대용량 서버에 각종 공개용 소프트웨어 및 문서를 저장하여, 사용자가 필요한 자료를 자신의 컴퓨터로 다운로드하여 쓸 수 있습니다.

불특정 다수에게 파일을 배포하기 위해 익명으로 사용할 수 있는 공개 FTP 서버도 존재하지만, 대부분의 경우는 아이디와 비밀번호로 인증받은 사용자만 접속 가능합니다.

이 때 비밀번호가 평문으로 전송되기 때문에, 보안상 취약하다는 단점이 있습니다.



 

> **FTP 동작원리**

 



![img](https://blog.kakaocdn.net/dn/WOlOX/btru3MLGqP1/4Jzw4cW31LAUMPhmmx4YJK/img.jpg)출처 : https://www.donga.com/news/It/article/all/20120330/45174705/3 



 

FTP는 파일 송수신만을 위해 고안되어서 동작 방식이 단순합니다.

바로 빠른 파일 전송이 주 목적입니다.



FTP 서비스를 제공하는 서버와 여기에 접속하는 클라이언트 사이, 두 개의 연결이 생성됩니다.

**제어 채널과 전송 채널로 분리**되어 있습니다.

데이터 전송을 제어하기 위한 네트워크 21번 포트와 실제 데이터 전송에 사용되는 네트워크 20번 포트로 이루어집니다.

먼저 제어 포트인 21번 포트로 사용자 인증·명령을 위한 연결이 만들어지고, 여기를 통해 클라이언트에서 지시하는 명령어가 전달됩니다.

그리고 실제로 데이터를 주고 받는 것은 20번 포트 데이터 채널에서 이뤄지는 것입니다.

여기서 포트는 데이터가 이동하는 통로입니다.

 

 

> **데이터 전송 방식**

 

**1. 액티브(Active) 모드**

액티브 모드는 능동 모드라고도 합니다.

클라이언트가 서버에게 포트를 알려줘서 통신하는 방식으로, 앞서 동작원리에서 본 네트워크 포트 20번, 21번을 사용하는 방식을 액티브 모드라고 합니다.

일부 네트워크 보안 장비(방화벽 등)에서 위 포트를 차단하는 경우, 사용자가 FTP 서버에 접속하더라도 제대로 출력되지 않는 문제가 발생할 수도 있습니다.





**2. 패시브(Passive) 모드**

패시브 모드는 수동 모드라고도 합니다.

클라이언트가 서버가 지정한 임의의 서버 포트로 연결할 수 있게 하는 것입니다.

패시브 모드는 앞서 액티브 모드에서 동작하지 않을 때, 전송용 포트를 다른 임의 번호로 할당하여 전송을 진행할 수 있게 합니다.

패시브 모드의 접속은 FTP 클라이언트 프로그램에서 설정하여 접속할 수 있습니다.



 

> **FTP 사용 방법**

 

FTP는 **웹브라우저**나 **윈도우탐색기** 등으로도 사용할 수 있지만, **FTP 클라이언트 프로그램**을 별도로 설치해 접속하는 것이 좀 더 쉽습니다.

FTP 서버에 접속하기 위해서는 해당 서버의 IP 주소나 인터넷 주소가 필요합니다.

FTP 서버의 인터넷 주소는 WWW의 http://가 아닌 **ftp://의 형태**를 가집니다.



FTP 클라이언트 프로그램을 사용하는 경우, 접속할 서버 사이트를 입력하고 승인된 사용자 계정과 암호를 넣어 접속하면 됩니다.

여기서 자신의 PC내 원하는 폴더에 내려 받을 수 있고, 반대로 자신의 PC에 있는 파일/폴더를 FTP 서버에 올릴 수도 있습니다.



이외에도 **윈도우 명령 프롬프트**를 통해서도 FTP에 접속이 가능합니다.

FTP 클라이언트 프로그램보다는 사용이 어렵고 불편하지만, 어떤 운영체제에서도 사용할 수 있다는 장점이 있습니다.

 



![img](https://blog.kakaocdn.net/dn/cuGiJL/btruVZepDRj/sKLbr3giDeN69N3HeQq5Gk/img.png)출처 : https://terms.naver.com/entry.naver?docId=3574148&amp;cid=59088&amp;categoryId=59096 



 

 

> **FTPS**

 

FTP의 비밀번호가 평문으로 가서 보안의 취약했던 점을 보안하기 위해 등장한 것이 FTPS입니다.

기존 FTP에서 SSL/TLS를 사용한 통신으로 보안성을 추가한 것입니다.

HTTP 통신에 보안을 위해 만들어졌습니다.

개인정보를 안전하게 통신하는데 사용됩니다.

속도는 느리지만 데이터를 안전하게 주고 받을 수 있는 비대칭키 방식입니다.



뛰어난 호환성을 가지고 있으나, FTP 기반의 프로토콜이기 때문에 FTPS도 두 가지 채널이 필요합니다.

따라서 방화벽 설정 문제로 FTP처럼 작동이 안되는 경우가 생길 수 있습니다.

 

 

> **SFTP**

 

SFTP는 SSH가 추가된 것으로 보안성(secure)을 강화했습니다.

서버와 클라이언트 간의 데이터 전송 시 계정 정보 등을 암호화하여 해킹이나 보안 상의 문제를 사전 방지 가능합니다.

접속하는 방법은 일반 FTP와 동일하지만, 신호 제어용 네트워크 포트가 21번이 아닌 **22번 포트를 사용**합니다.



SFTP는 보안 강화용 공개키 또는 개인 인증키 등을 사용할 수 있어 보다 안전한 데이터 송수신이 가능합니다.

다만 속도가 보안을 위해서 암호화 복호화 과정을 거치기 때문에 느립니다.



---

## FTPS와 SFTP 비교하기

지난번에 FTP, SFTP, FTPS를 간단하게 설명했었습니다.

 

FTP, FTPS, SFTP


오늘은 지난번에 이어, 조금 더 FTPS와 SFTP를 비교해보도록 하겠습니다.

 

------

> **FTPS와 SFTP 비교**

 

한 눈에 보기 쉽게 먼저 FTPS와 SFTP 비교표를 보여드리겠습니다.



|            | **FTPS**                                                     | **SFTP**                                                     |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **보안**   | 인증 옵션을 통해 강력한 보호 기능 – 파일 전송을 더 안전하게 만들어줌 |                                                              |
| **기반**   | FTP에 계층 추가 – SSL/TLS로 보호                             | SSH 기반                                                     |
| **포트**   | 여러 포트 번호: 명령 채널&데이터 채널                        | 단일 포트 번호(고유한 형식의 패킷): 초기 인증, 실행된 명령, 데이터 등 모든 통신에 사용 |
| **암호화** | 명령&데이터 채널 or 데이터 채널만 암호화                     | 하나의 연결 사용 : 인증 정보 & 데이터 모두 암호화            |
| **인증**   | 신뢰할 수 있는 인증서                                        | SSH 키 : SSH 개인키 & 공개키                                 |
| **구현**   | 방화벽에서 다양한 포트를 열어야 함→ 네트워크 위험, 보안 약화 | 구현이 비교적 쉬움                                           |
| **속도**   | 명령, 데이터 채널이 비동기적으로 실행 -> 약간 빠름           |                                                              |

 

FTPS와 SFTP는 공통적으로 FTP가 제공하지 않는 인증 옵션을 통해 강력한 보호 기능을 제공합니다.

파일 전송을 더 안전하게 만든다는 점은 동일하지만, 기반 기술이 전혀 다르기 때문에 둘을 같은 프로토콜로 혼돈해서는 안됩니다!



 

**1. 기반 차이**

FTPS는 FTP에 계층을 추가하여 SSL/TLS로 보호하는 것이고, SFTP는 SSH 기반 프로토콜입니다.



FTPS는 FTP를 기반으로 하기 때문에 명령 채널과 데이터 채널이 존재하여, 포트 번호가 여러 개입니다.

반면 SFTP는 단일 포트 번호를 사용하여 초기 인증, 명령 실행, 데이터 등 모든 통신에 사용합니다.





**2. 암호화 차이**

FTPS는 명령과 데이터 채널 또는 데이터 채널만 암호화합니다.

반면 SFTP는 하나의 연결만 암호화 하여 인증정보와 데이터 모두 암호화할 수 있습니다



FTPS 는 인증에 신뢰할 수 있는 인증서를 사용하지만, SFTP는 SSH 키를 사용합니다.

FTPS는 다양한 포트를 사용하기 때문에, 방화벽에서 여러 개를 열어 놓아 네트워크 위험이 있고 보안이 약화되는 점이 있습니다.

이에 비해 SFTP는 비교적 구현이 쉽습니다.





FTPS는 FTP에 보안을 추가한 것으로 보면 간단합니다.

SFTP는 SSH 방식을 이용한 SSH의 확장 프로토콜로 암호화된 구간에서 FTP를 이용하는 것입니다.

(SSH 내용 확인하기↓)

 

SSH(Secure Shell)

지난번 SFTP 포스팅에서 FTP에 SSH가 추가된 것이라고 했었는데요. (SFTP가 기억이 안나거나 모르겠다면, 여기서 확인하세요↓) FTP, FTPS, SFTP 오늘은 프로토콜에서 FTP와 FTPS, SFTP를 알아보겠습니다. (

cloudedi.tistory.com

 

 

> **SSL/TLS 짚고가기**

 

**SSL (Secure Socket Layer) 보안 소켓 계층**

**TLS (Transport Layer Security) 전송 계층 보안**



안전한 보안 통신을 형성해주는 역할의 보안용 프로토콜입니다.

SSL/TLS는 다른 프로토콜 포함이 가능하며, 정보의 암호화에 사용되는 알고리즘에서 인증, 암호키 교환 등을 선택하여 사용합니다.





**1. 정의**

정보의 암호화에 사용되는 각 알고리즘 중에 선택하여 사용합니다. (인증, 암호키 교환 등)

응용계층(HTTP)과 전송계층(TCP) 사이에 위치합니다.

(HTTP 내용 확인하기↓)

 

HTTP와 HTTPS

프로토콜에서 HTTP와 HTTPS에 대해 알아보도록 하겠습니다. (프로토콜이 궁금하시다면, 여기서 확인하세요↓) 프로토콜(Protocol) 클라우드 EDI에서 다양한 통신 프로토콜을 지원하고, 보안 프로

cloudedi.tistory.com

 

**2. 주요 역할**

암호화 : 내용을 알 수 없도록 암호화

인증 : 올바른 대상인지 확인

조작 검사 : 내용이 조작되었는지 검출





TLS는 SSL보다 보안성이 강화된 것인데, 기능적 차이는 거의 없다고 봐도 됩니다.



-- -

## FTPS 의 key 파일 만드는 방법



### 1. FTPS 의 keyStore 파일 만드는 방법

FTPS의 keyStore 파일을 만드는 방법은 다음과 같습니다.

1. keytool 유틸리티를 사용합니다.
2. keytool 유틸리티를 실행합니다.
3. keytool 유틸리티에 다음과 같은 매개변수를 입력합니다.
   - -genkeypair: 새 키 쌍을 생성합니다.
   - -alias: 키 쌍의 별칭을 지정합니다.
   - -keyalg: 키 생성 알고리즘을 지정합니다.
   - -keysize: 키의 크기를 지정합니다.
   - -storetype: 키 저장소의 유형을 지정합니다.
   - -storepass: 키 저장소의 비밀번호를 지정합니다.
   - -keypass: 키의 비밀번호를 지정합니다.
4. keytool 유틸리티가 키 쌍을 생성합니다.
5. 생성된 키 쌍을 keyStore 파일에 저장합니다.

다음은 keytool 유틸리티를 사용하여 keyStore 파일을 만드는 예입니다.

```cmd
> keytool -genkeypair -alias [별칭] -keyalg RSA -keysize 2048 -keystore [keyStore 파일 경로] -validity [유효 기간]
# 예
> keytool -genkeypair -alias mykeypair -keyalg RSA -keysize 2048 -storetype JKS -storepass mystorepass -keypass mykeypass
```





이 명령은 mykeypair라는 키 쌍을 생성하고, RSA 알고리즘을 사용하여 키를 생성하고, 키의 크기를 2048비트로 지정하고, JKS 형식의 keyStore 파일에 저장하고, mystorepass라는 비밀번호를 사용하여 keyStore 파일을 보호하고, mykeypass라는 비밀번호를 사용하여 키를 보호합니다.



### 2. FTPS 의 trustStore 파일 만드는 방법

FTPS의 trustStore 파일을 만드는 방법은 다음과 같습니다.

1. keytool 유틸리티를 사용합니다.
2. keytool 유틸리티를 실행합니다.
3. keytool 유틸리티에 다음과 같은 매개변수를 입력합니다.
   - -importcert: 신뢰할 수 있는 인증서를 가져옵니다.
   - -file: 가져올 인증서 파일의 경로를 지정합니다.
   - -storetype: 신뢰 저장소의 유형을 지정합니다.
   - -storepass: 신뢰 저장소의 비밀번호를 지정합니다.
4. keytool 유틸리티가 신뢰할 수 있는 인증서를 가져옵니다.
5. 가져온 신뢰할 수 있는 인증서를 trustStore 파일에 저장합니다.

다음은 keytool 유틸리티를 사용하여 trustStore 파일을 만드는 예입니다.

keytool -importcert -file C:\temp\server.crt -storetype JKS -storepass mystorepass

이 명령은 C:\temp\server.crt 파일의 인증서를 가져와 JKS 형식의 trustStore 파일에 저장하고, mystorepass라는 비밀번호를 사용하여 trustStore 파일을 보호합니다.

trustStore 파일을 생성한 후에는 FTPS 서버에 신뢰할 수 있는 인증서를 추가해야 합니다. FTPS 서버에 신뢰할 수 있는 인증서를 추가하는 방법은 FTPS 서버의 설명서를 참조하십시오.



---



## FTPS 용 keyStore, trustStore 파일 만드는 절차

참조: Bard

### 1. 사전 조건

아래 명령은 openssl 및 jdk 가 설치되어 있어야 한다.



### 2. 실행 명령 예제

``` bash
openssl req -new -keyout server.key -out server.csr
openssl x509 -req -in server.csr -signkey server.key -out server.crt
keytool -importcert -alias myserver -file server.crt -keystore mykeystore.jks -storepass mypassword
keytool -importcert -alias myserver -file server.crt -keystore mytruststore.jks -storepass mypassword -keypass mypassword
```



#### 2.1 csr 파일 생성

```
openssl req -new -keyout server.key -out server.csr
```

openssl req -new -keyout server.key -out server.csr 명령은 인증서 요청(CSR)을 생성하는 명령입니다. CSR은 서버의 인증서를 발급받기 위해 필요한 정보가 포함된 파일입니다.



이 명령은 다음과 같은 옵션을 사용합니다.

- -new: 새 CSR을 생성합니다.
- -keyout server.key: CSR에 사용할 개인 키 파일을 지정합니다.
- -out server.csr: CSR 파일의 이름을 지정합니다.



이 명령을 실행하면 CSR에 대한 정보를 입력하라는 프롬프트가 표시됩니다. CSR에 대한 정보는 다음과 같습니다.

- Common Name: 서버의 도메인 이름 또는 IP 주소
- Organization: 회사 이름
- Organizational Unit: 부서 이름
- City: 도시 이름
- State: 주 또는 지역 이름
- Country: 국가
- Email Address: 이메일 주소



CSR에 대한 정보를 입력하면 CSR 파일이 생성됩니다. CSR 파일은 FTPS 서버의 인증서를 발급받기 위해 사용됩니다.



#### 2.2 crt 파일 생성

```
openssl x509 -req -in server.csr -signkey server.key -out server.crt
```

openssl x509 -req -in server.csr -signkey server.key -out server.crt 명령은 CSR 파일에서 인증서를 생성하는 명령입니다. 인증서는 서버의 신원을 확인하고 안전한 연결을 제공하는 데 사용됩니다.



이 명령은 다음과 같은 옵션을 사용합니다.

- -req: CSR 파일을 인증서 생성에 사용합니다.
- -in server.csr: CSR 파일의 이름을 지정합니다.
- -signkey server.key: CSR 파일에 사용되는 개인 키 파일을 지정합니다.
- -out server.crt: 인증서 파일의 이름을 지정합니다.



이 명령을 실행하면 인증서 파일이 생성됩니다. 인증서 파일은 FTPS 서버에 설치되어 안전한 연결을 제공합니다.



다음은 인증서 파일의 구성 요소입니다.

- Subject: 인증서의 주체입니다. 인증서를 발급받은 서버의 이름입니다.
- Issuer: 인증서를 발급한 기관입니다. 인증 기관의 이름입니다.
- Serial Number: 인증서의 일련 번호입니다. 인증서를 식별하는 데 사용됩니다.
- Valid From: 인증서가 유효한 시작 날짜입니다.
- Valid To: 인증서가 유효한 종료 날짜입니다.
- Public Key: 인증서에 포함된 공개 키입니다. 서버와 클라이언트 간의 암호화 통신에 사용됩니다.
- Signature: 인증서에 서명한 디지털 서명입니다. 인증서의 무결성을 보장합니다.



#### 2.3 keystore 파일 생성

```
keytool -importcert -alias myserver -file server.crt -keystore mykeystore.jks -storepass mypassword
```

keytool -importcert -alias myserver -file server.crt -keystore mykeystore.jks -storepass mypassword 명령은 인증서 파일을 keystore에 가져오는 명령입니다.



이 명령은 다음과 같은 옵션을 사용합니다.

- -importcert: 인증서 파일을 keystore에 가져옵니다.
- -alias: keystore에서 인증서를 식별하는 별칭입니다.
- -file: 인증서 파일의 이름입니다.
- -keystore: 인증서를 가져올 keystore의 이름입니다.
- -storepass: keystore의 비밀번호입니다.



이 명령을 실행하면 인증서 파일이 keystore에 가져와집니다. 인증서 파일은 FTPS 서버의 인증서로 사용됩니다.



다음은 인증서 파일의 구성 요소입니다.

- Subject: 인증서의 주체입니다. 인증서를 발급받은 서버의 이름입니다.
- Issuer: 인증서를 발급한 기관입니다. 인증 기관의 이름입니다.
- Serial Number: 인증서의 일련 번호입니다. 인증서를 식별하는 데 사용됩니다.
- Valid From: 인증서가 유효한 시작 날짜입니다.
- Valid To: 인증서가 유효한 종료 날짜입니다.
- Public Key: 인증서에 포함된 공개 키입니다. 서버와 클라이언트 간의 암호화 통신에 사용됩니다.
- Signature: 인증서에 서명한 디지털 서명입니다. 인증서의 무결성을 보장합니다.



이 명령은 다음과 같이 사용됩니다.

```
keytool -importcert -alias myserver -file server.crt -keystore mykeystore.jks -storepass mypassword
```



이 명령을 실행하면 인증서 파일 `server.crt`가 `mykeystore.jks`라는 이름의 keystore에 가져와집니다. 인증서 파일의 별칭은 `myserver`이고, keystore의 비밀번호는 `mypassword`입니다.



#### 2.4 truststore 파일 생성

```
keytool -importcert -alias myserver -file server.crt -keystore mytruststore.jks -storepass mypassword -keypass mypassword
```

keytool -importcert -alias myserver -file server.crt -keystore mytruststore.jks -storepass mypassword -keypass mypassword 명령은 인증서 파일을 신뢰 저장소에 가져오는 명령입니다.



이 명령은 다음과 같은 옵션을 사용합니다.

- -importcert: 인증서 파일을 신뢰 저장소에 가져옵니다.
- -alias: 신뢰 저장소에서 인증서를 식별하는 별칭입니다.
- -file: 인증서 파일의 이름입니다.
- -keystore: 인증서를 가져올 신뢰 저장소의 이름입니다.
- -storepass: 신뢰 저장소의 비밀번호입니다.
- -keypass: 인증서 파일의 비밀번호입니다.



이 명령을 실행하면 인증서 파일이 신뢰 저장소에 가져와집니다. 인증서 파일은 FTPS 클라이언트가 서버와 안전하게 연결하는 데 사용됩니다.



다음은 인증서 파일의 구성 요소입니다.

- Subject: 인증서의 주체입니다. 인증서를 발급받은 서버의 이름입니다.
- Issuer: 인증서를 발급한 기관입니다. 인증 기관의 이름입니다.
- Serial Number: 인증서의 일련 번호입니다. 인증서를 식별하는 데 사용됩니다.
- Valid From: 인증서가 유효한 시작 날짜입니다.
- Valid To: 인증서가 유효한 종료 날짜입니다.
- Public Key: 인증서에 포함된 공개 키입니다. 서버와 클라이언트 간의 암호화 통신에 사용됩니다.
- Signature: 인증서에 서명한 디지털 서명입니다. 인증서의 무결성을 보장합니다.



이 명령은 다음과 같이 사용됩니다.

keytool -importcert -alias myserver -file server.crt -keystore mytruststore.jks -storepass mypassword -keypass mypassword



이 명령을 실행하면 인증서 파일 `server.crt`가 `mytruststore.jks`라는 이름의 신뢰 저장소에 가져와집니다. 인증서 파일의 별칭은 `myserver`이고, 신뢰 저장소의 비밀번호는 `mypassword`이고, 인증서 파일의 비밀번호는 `mypassword`입니다.



