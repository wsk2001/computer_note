### CURL 사용법



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



아래는 CURL 사용법을 각 명령어 별로 정리할 예정 이다.

그리고 명령어 정리가 끝나는 데로 libcurl 의 예제 및 각 함수의 사용법을 추가로 정리할 예정 임.

