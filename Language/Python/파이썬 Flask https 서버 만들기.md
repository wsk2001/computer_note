# 파이썬 Flask https 서버 만들기

출처: https://louie0724.tistory.com/459



작성일: 8월 7일

 

### **Index**

1. flask 서버 간단하게 만들기
2. 자체 서명된 인증서로 flask 서버 운영

 

 

### **1. flask 서버 간단하게 만들기**

 \- 인증서를 adhoc 이라는 것을 이용하여 간단히 사용할 수 있음



![img](https://blog.kakaocdn.net/dn/bE3TiD/btrbpQfXwq2/3Ga5MAM6kxhYV2EfMIUKU0/img.png)



\- 위 내용의 코드

```
from flask import Flask, render_template, request
import ssl

app = Flask(__name__)

@app.route('/hello')
def index():
    #return "Hello Everon Laboratory"
    return render_template('test01.html')

@app.route('/')
def hello():
    return render_template('hello.html')

if __name__ == "__main__":
    app.debug = True
    app.run(host="0.0.0.0", port="443", ssl_context='adhoc')
```

\- 서버를 실행함



![img](https://blog.kakaocdn.net/dn/bohJ6v/btrbtpaRp9z/VUFiBCUp6lgP0O2CuHcfZK/img.png)



\- 웹브라우저로 실행하여 인증서를 확인함



![img](https://blog.kakaocdn.net/dn/o2iGE/btrbkWnjUHj/AsD3LKrKcbXjrRkfF0kHyK/img.png)



 

 

### **2. 자체 서명된 인증서로 flask 서버 운영**

 \- x509 인증서를 개인키를 이용하여 생성함

 \- <span style="color:blue">**openssl req -x509 -newkey rsa:4096 -nodes -out cert.pem -keyout key.pem -days 365**</span>



![img](https://blog.kakaocdn.net/dn/dAaIdE/btrbrTDfCqx/En00By25nRuJoLKZUjqRA0/img.png)



\- 인증서를 서버에 flask 서버에 적용시킴



![img](https://blog.kakaocdn.net/dn/ZtxBw/btrbmzsf3qy/jbGVlx8KKT682prvCd941k/img.png)



\- 위 내용의 코드

```py
from flask import Flask, render_template, request
import ssl

app = Flask(__name__)

@app.route('/hello')
def index():
    #return "Hello Everon Laboratory"
    return render_template('test01.html')

@app.route('/')
def hello():
    return render_template('hello.html')

if __name__ == "__main__":
    app.debug = True

    ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS)
    ssl_context.load_cert_chain(certfile='cert.pem', keyfile='key.pem', password='louie')
    app.run(host="0.0.0.0", port=443, ssl_context=ssl_context)
```



``` py
from flask import Flask
import ssl

app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World"

if __name__ == "__main__":
    ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS)
    ssl_context.load_cert_chain(certfile='newcert.pem', keyfile='newkey.pem', password='secret')

    app.run(host="0.0.0.0", port=4443, ssl_context=ssl_context)
```



\- 브라우저를 통해 위해서 발행한 인증서 내용을 확인함



![img](https://blog.kakaocdn.net/dn/cyy0WK/btrbuIulFx5/UOAPSsd3YZtYkqxKrDKn31/img.png)



 

 

 

### **Referece:**

https://www.hanbit.co.kr/media/channel/view.html?cms_code=CMS6163871474 