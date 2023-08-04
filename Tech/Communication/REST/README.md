### Programming Language별 RESRful API 호출 방법



REST API 를 지원하는 암호화 Server 및 각 언어 에서 REST API 를 이용해 서비스를 호출 하는 과정을 기록 한다.

본 문서에서는 각 언어의 컴파일을 위한 컴파일러 설치는 설명 하지 않는다, 이미 개발 환경이 구축 되어 있는 상태 에서 REST API 를 사용하는 방법만 간단히 설명 한다.

<br/>

#### REST Server 개발

REST Server 는 Go 언어를 이용하여 개발 하였다. 내가 주로 사용하는 언어는 C/C++ 이지만 여러가지 언어를 다뤄야 하는 상황 이고 간단한 프로그램(백엔드)은 Go 언어를 사용 할 때가 있다.

REST Server 는 Go 언어에서 쉽게 지원하는 Package 가 있어 Go 언어로 개발 하기로 했다.

사용된 Package 는 `github.com/ant0ine/go-json-rest/rest` 로 설치를 하려면

```
go get github.com/ant0ine/go-json-rest/rest
```

명령을 이용 하여야 한다.



본 서버에서는 3 개의 url  을 지원 하는데 `encrypt` 와 `decrypt` 그리고 `hash` 이다.

`encrypt` Interface 규격

```json
요청
{
    "Alias" : "alias name",
    "Plain" : "암호화 할 문자열"
}

응답
{
    "Cipher" : "암호화된 문자열"
}
```

<br/>

`decrypt` Interface 규격

```json
요청
{
    "Alias" : "alias name",
    "Cipher" : "복호화 할 문자열"
}

응답
{
    "Plain" : "복호화된 문자열"
}
```

<br/>

 `hash` Interface 규격

```json
요청
{
    "Algo" : algorithm 번호, //1 ~ 8
    "Plain" : "Hash 값을 계산 할 문자열"
}

응답
{
    "Hash" : "계산된 Hash 문자열(base64)"
}
```



그리고 REST  API Method 는 POST 를 사용 하였는데 이유는 GET 을 사용 할 경우 Parameter 전달을 지원하지 않는 경우가 있어 (url 로 지원 가능, URL 인코딩 필요) POST 를 사용 하기로 하였다.



Server 의 source 는   [GoRestServer.go](https://github.com/wsk2001/computer_note/tree/master/REST/GoRestServer.go)로 구현 되어 있다.

암호화 lib 는 C/C++ 로 개발되어 Go 에서 호출 및 link 하여 사용 한다.

<br/>

#### REST Client 개발

REST Client 는 각 언어별로 개발이 되어 있는데 `C/C++`, `C#`,  `Java`, `Python`, `Go`, `Node.js` 용 Client 를 작성 하였다.

<br/>

#### C/C++ 용 REST Client

C/C++ 용 REST Client 는 libcurl, openSSL 을 이용하여 개발 하였다.

libcurl 은 CURL 의 기능을 모두 지원하는 C/C++ 용 lib 로 http 관련 client 를 개발 하는데 자주 사용되는 lib 이고, **OpenSSL**은 [네트워크](https://ko.wikipedia.org/wiki/%EC%BB%B4%ED%93%A8%ED%84%B0_%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)를 통한 데이터 통신에 쓰이는 [프로토콜](https://ko.wikipedia.org/wiki/%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C)인 [TLS](https://ko.wikipedia.org/wiki/%ED%8A%B8%EB%9E%9C%EC%8A%A4%ED%8F%AC%ED%8A%B8_%EB%A0%88%EC%9D%B4%EC%96%B4_%EB%B3%B4%EC%95%88)와 [SSL](https://ko.wikipedia.org/wiki/SSL)의 [오픈 소스](https://ko.wikipedia.org/wiki/%EC%98%A4%ED%94%88_%EC%86%8C%EC%8A%A4) 구현판이다. 라고 위키백과에 쓰여져 있다.

구현 소스는 [CurlRestPost.cpp](https://github.com/wsk2001/computer_note/tree/master/REST/CurlRestPost.cpp)로  컴파일은 

```
g++ -o CurlRestPost CurlRestPost.cpp -IC:\Tools\CURL\include -LC:\Tools\CURL\lib -lcurl
```

이렇게 하였다.

지금 테스트 하는 libcurl 이 `libcrypto-1_1-x64.dll` 및 `libssl-1_1-x64.dll` 을 필요로 하는데 처음 실행시 `SSL_CTX_set_ciphersuites` 이 없다는 오류가 발생 하여 OpenSSL 을 최신 version 으로 재 설치 하였다.

`주요 코드`

```cpp
#include <iostream>
#include <string>
#include <curl/curl.h>
#include <string.h>

using namespace std;

bool http_post(char * url, const char * jsonData, string & str)
{
    CURL	*	curl;
    CURLcode	res;
    struct curl_slist * headers = NULL;

    curl = curl_easy_init();

    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonData);
        headers = curl_slist_append(headers, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        res = curl_easy_perform(curl);

        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_string);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &str);

        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
        return true;
    }
    else
    {
        return false;
    }
}
```

<br/>

#### C# 용 REST Client

C# 용 REST Client 는 별도의 추가 모듈을 설치 하지 않고, Visual Studio 2010, Visual Studio 2017 에서 컴파일 및 Test 되었으며 소스 파일을 [CsRestPost.cs](https://github.com/wsk2001/computer_note/tree/master/REST/CsRestPost.cs) 이다.

사용된 주요 Class 는 HttpWebRequest, WebRequest, Stream, WebResponse, StreamReader 등 이다.

주요 소스코드는 다음과 같다.

```C#
byte[] byteArray = Encoding.UTF8.GetBytes (data); 

HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
request.ContentType = "application/json";
request.Method = "POST";
request.KeepAlive = true;
request.ContentLength = byteArray.Length; 

Stream st = request.GetRequestStream();
st.Write(byteArray, 0, byteArray.Length);
st.Close();

WebResponse response = request.GetResponse ();  
Console.WriteLine (((HttpWebResponse)response).StatusDescription);  
st = response.GetResponseStream ();  

```

<br/>

#### Java 용 REST Client

java 용 REST Client 는 JSON Data 처리를 위해 `json-simple-1.1.1.jar` 를 사용 하였다.

구현된 source 는 [JavaRestPost.java](https://github.com/wsk2001/computer_note/tree/master/REST/JavaRestPost.java)  이고 사용된 주요 Class 는 `URL`, `HttpURLConnection`, `BufferedReader`, `InputStreamReader` 등 이다.

소스의 주요 부분은 다음과 같다.

```java
try {
	URL obj = new URL(strUrl);
	HttpURLConnection con = (HttpURLConnection)obj.openConnection(); 
	
	con.setDoInput(true);
	con.setDoOutput(true);
	con.setUseCaches(false);

	con.setRequestMethod("POST"); 
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("accept", "application/json");
	
	DataOutputStream osw = null;
	osw = new DataOutputStream(con.getOutputStream());
	osw.writeBytes(strData);
	osw.flush();
	
	in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
	
	StringBuilder sb = new StringBuilder();
	String line; 
	
	while((line = in.readLine()) != null) { // response를 차례대로 출력  
		sb.append(line);
	} 
	strRes = sb.toString();
} catch(Exception e) { 
	e.printStackTrace(); 
} finally { 
	if(in != null) 
	{
		try { 
			in.close(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 
	}
} 
```

`주의`

con.setDoInput(true);
con.setDoOutput(true); 을 설정 하지 않으면 Permission 오류가 발생 하니 주의 할 것.

 <br/>

#### Python 용 REST Client

python 으로 REST Client 를 개발 하기 위해서 별도의 모듈을 설치 할 필요는 없다.

주요하게 보아야 할 부분은 header  지정 하는 부분과, data 를 json.dumps() 함수를 사용하여 변환 하는 부분이다

소스 코드는 [PyRestSample.py](https://github.com/wsk2001/computer_note/tree/master/REST/PyRestSample.py)  로 소스의 길이가 길지 않으므로 전체 소스를 본 문서에 추가 하였다.

```python
import requests
import json

def encrypt( url, enc_data):
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post(url, data=json.dumps(enc_data), headers=headers)
    res_data = json.loads( r.text )
    return res_data['Cipher']
    
def decrypt( url, dec_data):
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post(url, data=json.dumps(dec_data), headers=headers)
    res_data = json.loads( r.text )
    return res_data['Plain']

def main():
    enc_url = "http://127.0.0.1:8080/encrypt"
    dec_url = "http://127.0.0.1:8080/decrypt"

    enc_data = {'alias': 'normal', 'plain': '1234567890123'}
    cipher_txt = encrypt( enc_url, enc_data)
    print( 'cipher_text : ' + cipher_txt)

    dec_data = {'alias': 'normal', 'cipher': cipher_txt }
    plain_text = decrypt( dec_url, dec_data)

    print( 'plain_text : ' + plain_text)

if __name__ == "__main__":
    main()
```

<br/>

#### Go 용 REST Client

go 언어 용 REST Client 를 개발 하기 위해서는 별도의 package 를 추가로 설치 하지 않아도 된다.

사용한 주요 Package 는 `encoding/json`, `io/ioutil`, `net/http` 로 기본 설치에 포함 되어 있는 Package 이다.

소스를 보면 알겠지만 주요 함수는 `http.Post` 로 별 특별한 주의 사항 없이 사용 할 수 있다.

소스 코드는 [GoRestPost.go](https://github.com/wsk2001/computer_note/tree/master/REST/GoRestPost.go) 에 구현 되어 있다.
```go
package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

type responseEnc struct {
	Cipher string
}

type responseDec struct {
	Plain string
}

func main() {
	res := responseEnc{}
	jsonData := map[string]string{"alias": "normal", "plain": "1234567890123"}
	jsonValue, _ := json.Marshal(jsonData)
	response, err := http.Post("http://127.0.0.1:8080/encrypt", "application/json", bytes.NewBuffer(jsonValue))
	if err != nil {
		fmt.Printf("The HTTP request failed with error %s\n", err)
	} else {
		data, _ := ioutil.ReadAll(response.Body)
		json.Unmarshal(data, &res)
	}
	fmt.Println("Cipher string : " + res.Cipher)

	resDec := responseDec{}
	jsonData = map[string]string{"alias": "normal", "cipher": res.Cipher}
	jsonValue, _ = json.Marshal(jsonData)
	response, err = http.Post("http://127.0.0.1:8080/decrypt", "application/json", bytes.NewBuffer(jsonValue))
	if err != nil {
		fmt.Printf("The HTTP request failed with error %s\n", err)
	} else {
		data, _ := ioutil.ReadAll(response.Body)
		json.Unmarshal(data, &resDec)
	}
	fmt.Println("Plain string : " + resDec.Plain)

	fmt.Println("Terminating the application...")
}

/**
 * run : go run GoRestPost.go
 */

```

<br/>

#### node.js 용 REST Client

node.js 에서 REST Client 를 개발 하기 위해서 WebFramework 인 `request` 를 추가로 설치 하였다.

`설치 방법`

```
npm install --save request
```

주의 사항은 node.js 는 SIngle Thred, CallBack 방식을 사용 하기 때문에 코드가 순차적으로 실행 되지 않는다는 점을 주의 하여야 한다.

즉 첫번째 `request` 요청(암호화)에 의한 response 를 받고 그결과를 이용하여 두번째 요청(복호화)을 할 경우 순차적으로 코드를 작성 하면 원하는 결과를 얻을 수 없다. 첫번째 결과를 받기 전에 두번째 요청이 실행 되기 때문이다. 이 문제를 피하기 위해서는 첫번째 요청의 결과를 받는 callback 함수 내부에 두번째 요청을 수행 하는 코드를 집어 넣으면 된다.
구현 소스는 [NodeRestPost.js](https://github.com/wsk2001/computer_note/tree/master/REST/NodeRestPost.js) 

아래는 node.js 에서 rest api 를 사용하는 예제 이다.

```javascript
var request = require('request');

var srcData = '1234567890123';
var encData = '';
var decData = '';

var reqEncData = {
    'Alias' : 'normal',
    'Plain' : srcData
} 

request({
            method: 'POST',
            url: "http://127.0.0.1:8080/encrypt",
            json: true,
            headers: {
                "content-type": "application/json",
            },
            body: reqEncData
        }, function(error, response, body) {
            encData = response.body.Cipher;

            var reqDecData = {
                'Alias' : 'normal',
                'Cipher' : encData   
            }
                       
            request({
                method: 'POST',
                url: "http://127.0.0.1:8080/decrypt",
                json: true,
                headers: {
                    "content-type": "application/json",
                },
                body: reqDecData
            }, function(error, response, body) {
                decData = response.body.Plain;

                console.log( 'src : ' + srcData);
                console.log( 'enc : ' + encData);
                console.log( 'dec : ' + decData);                
            });
        });
```

실행은 다음과 같이 한다.

```
node NodeRestPost.js
```

<br/>

##### # 추가로 더 필요하게 되면 여기서 언급되지 않은 언어 를 이용하여 Client 를 작성 할 예정 이고 해당 언어 에서 REST API 를 지원 하지 않는다면 C/C++ 로 라이브러리를 만든 후 타 언어에 제공 하는 방법으로 구현 예정 이다.

 





