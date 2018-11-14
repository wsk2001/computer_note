### CURL(command line tool and library) 사용법



RESTFul API 를 테스트 하기 위해서 CURL 을 사용하여 GET, POST, PUT, DELETE 등의 기능을 테스트 하던중 `HTTP/1.1 415 Unsupported Media Type` 오류가 발생 하여 원인을 확인 해 보니 Linux, Unix 와 Windows 가 서로 상이한 부분이 있어 CURL 사용법을 정리 하기로 하였다.

`HTTP/1.1 415 Unsupported Media Type` 오류는 Windows 에서 CURL 로 Content-Type, data 등을 보낼때 발생 하는 문제로 Linux 에서는 발생 하지 않아 구글링 후 문제를 해결 하였다.

문제가 되었던 명령은 각각 다음과 같다.

`Lunix 에서 수행한 명령`

```
curl -i -H 'Content-Type: application/json' -d '{"Code":"US","Name":"United States"}' http://192.168.2.1:8080/countries
```

- 정상 처리가 됨.



`Windows 에서 수행한 명령`

```
curl -i -H 'Content-Type: application/json' -d '{"Code":"US","Name":"United States"}' http://127.0.0.1:8080/countries
```

- 오류 발생
```
curl: (6) Could not resolve host: application
HTTP/1.1 415 Unsupported Media Type
Content-Type: application/json; charset=utf-8
X-Powered-By: go-json-rest
Date: Tue, 13 Nov 2018 08:11:57 GMT
Content-Length: 73

{
  "Error": "Bad Content-Type or charset, expected 'application/json'"
}
```



구글링 해 본 결과  you are possibly running this from windows pc. So wrap your json with double quote(`"`) instead of single(`'`)

즉 Windows 에서는 **single(`'`) 대신 double quote(`"`)** 를 사용 하라고 한다.

그리고 `Notice, I have escaped the inner quotes with `\` "" 사이에  double quote(`"`)  가 올 경우는 `\"` 로 바꾸어서 사용 하라고 한다.



Windows 에서 오류가 나던 부분을 수정 한 명령은 다음과 같다.

```
-- 수정 전 명령어
curl -i -H 'Content-Type: application/json' -d '{"Code":"US","Name":"United States"}' http://127.0.0.1:8080/countries

-- 수정 후 명령어
curl -i -H "Content-Type: application/json" -d "{\"Code\":\"US\",\"Name\":\"United States\"}" http://127.0.0.1:8080/countries

```

- 명령을 바꾸어 실행 시키면 오류 없이 잘 수행 된다.
- 오류의 원인은 `Windows 명령 프롬프트는 Unix와 같은 작은 따옴표를 지원하지 않습니다.`
- 결과적으로 작은 따옴표를 큰 따옴표로 바꾸어야합니다. 필요할 때마다 이스케이프 처리를 해야 합니다.



복잡한 json 문장을 보낼 경우 보낼 json 문장을 미리 파일로 작성 한 후 전송 하면 된다.

```
curl -X POST -H "Content-Type: application/json" --data @data.txt http://127.0.0.1:8080/countries

또는

curl -X POST -H "Content-Type: application/json" -d @data.txt http://127.0.0.1:8080/countries


cat data.txt
"{"Code":"US","Name":"United States"}" 

--data, -d 를 이용하는것은 잘 않된다. 추가 확인 예정 
```





아래는 CURL 기술 자료를 정리 한다.  본 문서 에서는 특별한 언급이 없으면 Linux 에서 수행 하는 것을 기준 으로 한다.  그리고 명령어 정리가 끝나는 데로 libcurl 의 예제 및 각 함수의 사용법을 추가로 정리할 예정 임.



#### CURL 에서 지원하는 Protocol

```
DICT, FILE, FTP, FTPS, Gopher, HTTP, HTTPS, IMAP, IMAPS, LDAP, LDAPS, POP3, POP3S, RTMP, RTSP, SCP, SFTP, SMB, SMBS, SMTP, SMTPS, Telnet and TFTP. curl supports SSL certificates, HTTP POST, HTTP PUT, FTP uploading, HTTP form based upload, proxies, HTTP/2, cookies, user+password authentication (Basic, Plain, Digest, CRAM-MD5, NTLM, Negotiate and Kerberos), file transfer resume, proxy tunneling and more.
```

내가 다뤄보지 않은 Protocol 도 일부(POP3S, RTMP, RTSP...) 있음



#### 간단한 사용법

##### Netscape의 웹 서버에서 기본 페이지 가져 오기

```
curl http://www.netscape.com/
```



##### funet의 ftp-server에서 README 파일을 사용자의 홈 디렉토리로 가져 오기.

```
curl ftp://ftp.funet.fi/README
```



##### FTP 사이트의 디렉토리 목록 가져 오기(예)

```
curl ftp://ftp.kaist.ac.kr/
```



#### HTTP GET 사용 예

```
curl -v http://localhost:8082/spring-rest/foos/9
```



#### HTTP POST 사용 예

```
curl -d 'id=9&name=baeldung' http://localhost:8082/spring-rest/foos/new

또는 data 파일을 지정 하여 전송 -d @request.json

curl -d @request.json -H "Content-Type: application/json" http://localhost:8082/spring-rest/foos/new
```



#### HTTP PUT 사용 예

기존 정보를 수정 하려고 할때 보내는 옵션 입니다. POST 와 유사 하지만 -X 옵션을 추가 합니다.

요청 메소드 유형에 대한 언급이 없으면 `GET`을 사용하여 컬이 기본값으로 설정됩니다. 따라서 `PUT`의 경우 메소드 유형을 명시 적으로 언급합니다.

```
curl -d @request.json -H 'Content-Type: application/json' -X PUT http://localhost:8082/spring-rest/foos/9
```



#### HTTP DELETE 사용 예

-X 옵션을 사용하여 DELETE를 사용하도록 지정합니다.

```
curl -X DELETE http://localhost:8082/spring-rest/foos/9
```



나머지는 https://curl.haxx.se/docs/manual.html 를 참조 한다.



CURL 관련 전체 자료는 https://curl.haxx.se/ 를 참조 하기 바란다, 자주 사용 하고 유용한 옵션만 정리 하도록 한다.



#### 주요 옵션

출처: https://www.lesstif.com/pages/viewpage.action?pageId=147 

| short  | long                 | 설명                                                         | 비고                                                         |
| ------ | -------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **-k** | --insecure           | https 사이트를 SSL certificate 검증없이 연결한다.            | wget 의 --no-check-certificate 과 비슷한 역할 수행           |
| -l     | --head               | HTTP header 만 보여주고 content 는 표시하지 않는다           |                                                              |
| -D     | --dump-header <file> | <file> 에 HTTP header 를 기록한다.                           |                                                              |
| -L     | --location           | 서버에서 [HTTP 301 ](http://en.wikipedia.org/wiki/HTTP_301)이나 [HTTP 302](http://en.wikipedia.org/wiki/HTTP_302) 응답이 왔을 경우 redirection URL 로 따라간다.--max-redirs 뒤에 숫자로 redirection 을 몇 번 따라갈지 지정할 수 있다. 기본 값은 50이다 | curl -v daum.net 을 실행하면 결과값으로 다음과 같이 HTTP 302 가 리턴된다.< HTTP/1.1 302 Object Moved < Location: <http://www.daum.net/>-L 옵션을 추가하면 [www.daum.net](http://www.daum.net/) 으로 재접속하여 결과를 받아오게 된다. |
| **-d** | --data               | HTTP Post data                                               | FORM 을 POST 하는 HTTP나 JSON 으로 데이타를 주고받는 REST 기반의 웹서비스 디버깅시 유용한 옵션이다 |
| **-v** | --verbose            | 동작하면서 자세한 옵션을 출력한다.                           |                                                              |
| -J     | --remote-header-name | 어떤 웹서비스는 파일 다운로드시 [Content-Disposition Header](http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html) 를 파싱해야 정확한 파일이름을 알 수 있을 경우가 있다. -J 옵션을 주면 헤더에 있는 파일 이름으로 저장한다. | curl 7.20 이상부터 추가된 옵션                               |
| **-o** | --output FILE        | curl 은 remote 에서 받아온 데이타를 기본적으로는 콘솔에 출력한다. -o 옵션 뒤에 FILE 을 적어주면 해당 FILE 로 저장한다. (download 시 유용) |                                                              |
| -O     | --remote-name        | file 저장시 remote 의 file 이름으로 저장한다. -o 옵션보다 편리하다. |                                                              |
| -s     | --silent             | 정숙 모드. 진행 내역이나 메시지등을 출력하지 않는다. -o 옵션으로 remote data 도 /dev/null 로 보내면 결과물도 출력되지 않는다 | HTTP response code 만 가져오거나 할 경우 유리                |



