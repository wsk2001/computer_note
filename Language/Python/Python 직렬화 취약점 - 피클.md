# Python 직렬화 취약점 - 피클

출처: https://www.hackingarticles.in/python-serialization-vulnerabilities-pickle/



### 소개

직렬화는 개체에서 데이터를 수집하여 이를 바이트 문자열로 변환하고 디스크에 씁니다. 데이터를 역직렬화하고 원본 개체를 다시 만들 수 있습니다. PHP, Java, Ruby 및 Python(웹의 일반적인 백엔드 코딩 언어)을 비롯한 많은 프로그래밍 언어가 이를 수행하는 방법을 제공합니다.

Python의 직렬화에 대해 이야기해 보겠습니다. Python에서는 피클 모듈을 사용할 때 직렬화를 '피클링'이라고 합니다.



### Table of content

- Python의 직렬화
- 웹 앱의 직렬화
- 오버 피클링
- **Python YAML vs Python Pickle**
- **Mitigation**
- **Demonstration**
- **Conclusion**



#### Python의 직렬화

Python을 사용하는 동안 pickle.dumps()는 일부 데이터를 직렬화하는 데 사용되고 pickle.loads()는 데이터를 역직렬화하는 데 사용됩니다(피클링 및 피클링 해제). 예를 들어, 여기에 피클된 배열이 있습니다.

``` python
python3
>>> import pickle
>>> variable = pickle.dumps([1,2,3])
>>> print(variable)
b'\x80\x04\x95\x0b\x00\x00\x00\x00\x00\x00\x00]\x94(K\x01K\x02K\x03e.'
>>> pickle.loads(variable)
[1, 2, 3]
>>>
```

위에서 볼 수 있듯이 변수를 인쇄하면 바이트 문자열이 표시됩니다. 이것이 직렬화입니다. 나중에 pickle.loads(variable)을 사용하여 객체를 역직렬화합니다.

이는 나중에 다른 프로그램에서 사용할 수 있는 바이너리로 드라이브에 있는 프로그램의 일부 변수를 저장하려는 경우를 포함하여 많은 경우에 유용합니다. 예를 들어 배열을 만들고 이를 바이너리 파일로 저장해 보겠습니다.

``` python
import pickle
variable = pickle.dumps([1,2,3])
with open("myarray.pkl","wb") as f:
f.write(variable)
```

보시다시피 이제 피클 바이너리가 드라이브에 저장되었습니다. 다시 피클을 사용해 읽어보자.

``` python
import pickle
obj = open("myarray.pkl","rb").read()
pickle.loads(obj)
```

보시다시피 이제 이 역직렬화된 객체(obj)를 다시 배열처럼 작동할 수 있습니다! SDLC 전체에서 개발자가 IDE를 종료하고 현재 모든 데이터와 변수 상태를 저장하고 싶을 때가 올 수 있는데, 이것이 유용한 기능입니다.

#### 웹 앱의 직렬화

자, 우리는 소프트웨어 애플리케이션의 직렬화에 대해 이야기했습니다. 하지만 웹 앱에서 직렬화를 사용하는 것은 무엇입니까? 따라서 HTTP는 상태 비저장 프로토콜입니다. 즉, 한 요청의 상태는 이전 요청에 의존하지 않습니다. 그러나 때로는 상태를 유지해야 하는 경우도 있습니다. 그것이 바로 우리가 쿠키를 가지고 있는 이유입니다. 쿠키는 HTTP 프로토콜에 상태 저장성을 부여합니다.

사용자의 정보와 일부 데이터가 다음에 서버와 상호 작용할 때 유지되기를 원한다면 직렬화는 훌륭한 사용 사례입니다. 일부 데이터를 직렬화하여 쿠키에 넣고(서버의 저장 공간이 아닌 사용자의 저장 공간을 차지합니다! WoW) 다음 요청에는 역직렬화하여 사이트에서 사용하면 됩니다.

Pickle은 이를 위해 Python 웹 앱에서 사용됩니다. 그러나 한 가지 주의할 점은 안전하지 않게 역직렬화되고 해당 콘텐츠가 클라이언트에 의해 제어된다는 것입니다. 추가하기만 하면 json의 직렬화가 훨씬 안전합니다! 다른 직렬화 형식과 달리 JSON은 실행 가능한 코드를 데이터에 포함하는 것을 허용하지 않습니다. 이를 통해 악의적인 행위자가 악용할 수 있는 코드 삽입 취약점의 위험을 제거합니다.

임의의 코드를 실행하는 악성 피클 데이터를 구성하는 것이 가능합니다!

#### 오버 피클링

우리는 배열과 같이 잘 알려진 데이터 유형을 피클링하는 방법에 대해 이야기했습니다. 하지만 우리가 우리만의 커스텀 클래스를 피클링한다면 어떨까요? Python은 잘 알려진 클래스를 쉽게 이해하고 역직렬화할 수 있지만 서버 연결 및 모든 멋진 네트워킹 스크립트와 같은 사용자 정의 클래스로 무엇을 할 수 있습니까? 이를 직렬화하는 것도 의미가 없지만 Python 개발자는 이를 피클하는 방법도 추가했습니다. Python이 그러한 객체를 역직렬화하려고 시도할 때 불일치가 발생할 가능성이 있습니다.

사용자 정의 피클링 및 역피클링 코드를 사용할 수 있습니다. 클래스를 정의할 때 '누군가가 당신을 피클 해제하라고 요청할 때 해야 할 일은 다음과 같습니다!'라는 메커니즘을 제공할 수 있습니다. 따라서 Python이 이 바이트 문자열을 피클 해제하려고 할 때 어떻게 해야 하는지 알아내기 위해 일부 코드를 실행해야 할 수도 있습니다. 해당 개체를 올바르게 재구성합니다. 이 코드는 이 피클 파일에 포함됩니다.

작은 예를 살펴보겠습니다.

다음은 개념 증명을 위한 코드입니다. 이 코드는 EvilPickle이라는 클래스를 생성합니다. 사용자 정의 개체에 대한 피클링 지원을 구현하려면 함수와 해당 함수를 호출하는 데 사용할 인수 쌍을 반환하는 '__reduce__'라는 메서드를 정의합니다. 여기서는 os.system 함수를 사용하여 간단한 'cat /etc/passwd'를 실행합니다. 마지막으로 이는 backup.data라는 이진 파일에 기록됩니다.

``` python
import pickle
import os
class EvilPickle(object):
  def __reduce__(self):
    return (os.system, ('cat /etc/passwd', ))
pickle_data = pickle.dumps(EvilPickle())
with open("backup.data", "wb") as file:
  file.write(pickle_data)
```

여기서 아이디어는 디시리얼라이저가 시스템에서 cat /etc/passwd를 실행하도록 하는 것입니다. 지금 시험해 봅시다! 위의 코드를 evilpickle.py 파일에 저장하고 실행해보겠습니다. 확인하기 위해 backup.data 파일을 분류하겠습니다. 여기서 우리는 수상한 것을 분명히 볼 수 있습니다!

사용자는 어쨌든 그것을 역직렬화하고 /etc/passwd 파일을 제공하게 됩니다.

``` python
import pickle
pickle.loads(open("backup.data","rb").read())
```

pickletools를 사용하여 분해함으로써 우리는 훨씬 더 괴짜가 되어 내부에서 무슨 일이 일어나고 있는지 확인할 수 있습니다. 여기서 피클링은 SHORT 변수에 저장되고 0으로 저장되는 os(posix)와 같은 Unix에서 수행되며 그 이후의 각 연속 명령은 스택의 다른 숫자 값에 저장됩니다. `REDUCE` opcode는 인수(TUPLE이라고 함. 여기서는 cat /etc/passwd라고 함)를 사용하여 콜러블(일반적으로 Python 함수 또는 메서드, 여기서는 os.system(posix 및 system으로 표시됨))을 호출하는 데 사용됩니다. 그리고 마지막으로 프로그램이 중지됩니다.

튜플과 리스트의 주요 차이점은 튜플은 변경 가능한 리스트와 달리 불변이라는 것입니다. 따라서 목록은 변경할 수 있지만 튜플은 변경할 수 없습니다. 튜플의 불변성으로 인해 Python에서 일단 생성된 튜플의 내용은 변경할 수 없습니다.

``` python
python3 -m pickletools -a backup.data
```

> 참고: -a 옵션은 pickletools를 사용하는 동안 각 단계에 대한 일부 정보를 제공합니다.

따라서 피클 개체는 사용자가 제어하고 서버에서 피클을 해제하므로 이를 사용하여 원격 서버 셸도 가져올 수도 있습니다(소켓을 사용하여 피클하고 최종적으로 서버에 제공).

PyTorch ML 모델은 최근까지 ML 모델 직렬화를 위해 피클을 사용했으며 임의 코드 실행에 취약했습니다. Safetensors는 이 문제를 극복했습니다.



#### Python YAML vs Python Pickle

Python YAML은 피클 대신 또 다른 직렬화 형식입니다. 그러나 Python YAML도 기본적으로 임의 코드 실행을 허용합니다. 다음은 또 다른 POC입니다.

``` python
import yaml
document = "!!python/object/apply:os.system ['cat /etc/passwd']"
yaml.load(document)
```

이것은 또한 cat /etc/passwd를 실행합니다. 어쨌든 load() 대신 “safe_load()”를 사용하면 이를 방지할 수 있습니다!

#### Mitigation

Pickle은 Python의 하나의 모듈일 뿐입니다. 이것은 매우 잘 알려진 도구이며 개발자는 여전히 이를 사용하지만 개발자가 조금 더 주의를 기울이면 피클의 문서 페이지에 아래 표시된 경고를 무시하지 않을 것입니다. 

피클의 대안과 이에 대한 간략한 POC는 다음과 같습니다.

`JSON`

``` python
import json
# Serialize
data = {"key": "value"}
json_data = json.dumps(data)


# Deserialize
deserialized_data = json.loads(json_data)
```

`msgpack`

``` python
import msgpack
# Serialize
data = {"key": "value"}
msgpack_data = msgpack.packb(data)


# Deserialize
deserialized_data = msgpack.unpackb(msgpack_data, raw=False)
```

사용할 수 있는 다른 안전한 옵션으로는 Google의 protobuf, CBOR이 있습니다.

### **Demonstration**

Okay, so the given website is a note-taking website which is using serialization. Here is what happens when I submit a note with a PNG image.

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjvby5H9JeXFijmiY4qW7zDDuh482aqH4-h0XCDEv37LJNqFCRxpe_mqLe5ZgWwSHhRTsysvmT7bxxhc1t4nTaRlVORd1xXdJ9qbFtVeZZQHC7fJAIII5jal1ts4cdr0qWYRfvcSBniKHf9k9K3zGGpC3OXt-RwzDqBVHoh1FfFxxZHZgxQifIBQra-1_43/s16000/7.png)

This looks something like this when processed by the server. Observe the URL which is rendering a .pickle file

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjiig9dulOWtu8CuqTZZ0Y7gTAAM86rt7mXih9JjQP2Ht_3RVF2B_yvGnWgAEkKxPRJ_6HujlWGeTZ493gjUGEUgYhYU9n55jHysLK6Wo77X6sNHka5OPc7-8xGyDeC43W0CePkIW-GEnBh3RitkgPCK8ZZ14dRIbEE2Kun9k9NDUJc8YOwb-TvOTPvWfBY/s16000/8.png)

The challenge also provided us with an app.py source code which tells us all about the background logic. I can’t post the entire code but here are some relevant snippets.

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhTzpcCtsOP3tBo_W3MEVKevE_vwyILejQ-IDf741qFx2eSrAfAXnoLj6xd-d-Oz9ZbiYJPMMRRxU36Mb5oexuI2jZcnnP2EEil-AmeWrzDpEToo9isiAJCPzTCWC-yBoKJwufpED_q6v2YtSnMb_GBY1MwrRawRUStfCIyRifNODtjisFrg8N4m2rVbQsI/s16000/9.png)

As we can see, the code is accepting the title, content and image as an object, pickling it and storing it in title.pickle

Here are the key functions of the code:

1. Note() class accepts an object new_note with 3 items: title, content, image_filename.
2. save_note() is calling pickle.dumps() to pickle new_note. save_note() is also called to store an image using image.save which is a flask function. Similarly image.filename extracts image’s filename.
3. secure_filename() function converts insecure names to secure ones. For example: note 1 becomes note_1, ../../../etc/passwd becomes etc_passwd
4. unpickle_file is loading the pickled file provided to it and unpickles it.

Here are some key takeaways about the functionality of the code:

1. The site is accepting 3 key items.
2. It is not checking if PNG is safe or not (as in if it is a valid PNG or not. This is a good attack point)
3. All in all, PNG file upload is a really strong contender to put code in because: a, site isn’t validating safety of PNG and b, it will unpickle any file we provide.

I tried with a simple cat /etc/passwd command on my local machine and the evil.png pickled file was deserializing properly!

import pickle

import os

**class** EvilPickle(object):

  **def** __reduce__(self):

​    **return** (os.system, ('cat /etc/passwd', ))

pickle_data = pickle.dumps(EvilPickle())

with open("evil.png", "wb") as file:

  file.write(pickle_data)

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg42MZSl2Xr69mkO04WbbtjPVvcCslPkY1xZnCuZfZ7tiHP_ND9be_-UHjvr2pem5gZanubBnbLH63TyP8LoXCI0TTfzm1GqtPkJCC4q8y3uOOFSk1a1B5fN-4pZKkqT3wPKmdHzvNfJ59y93MaowbMFZJ3jRrDVmbDhQEEwfN65QqBKH-aEF-4ETh4H08d/s16000/10.png)

Let’s take it a step further and use a netcat listener to receive data from the deserialized local execution of evil.png and have it give us a shell!

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgZCA5AVtVk2kSqmOJPXP2s1dCKXkxTsjKQeBswJ5vNSI9Mi3LnHZQx6PRpAx5EnXD1gMzZsWbdJuIHUzBUvjADS5-1oMLTar1sSu1C1USjeIS_1uGz2eLZ9UA1BX8cWJ6h0UVKSWxJusTTWPkmzxBxywfEbSsyNSnJZpIzNVRV4OFCt2HHYXXFOqc0bqLT/s16000/11.png)

By following the same logic, we could exploit the server. First I create a PNG file and upload it on the server.

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjj0rQ1j0-zIN9phD833H7oSjzBpDmdfx5TgvZ15iE5k9ApSIZj54NqCJ3Y6Yrk1mw-FF34qyRgFlIdOUqWW7inn8szRMl-wk32xxQYl1SsrrIS9zleT6sDWrhRzocNao5OtqvBDCoMx-zDDgo3uief0Ir-Q1Yd_2G3ADN9eFwzmlNLxuzxQjd_KmBTqL6Y/s16000/12.png)

The uploaded data becomes a pickle file which gets stored on the server and when it is called, data is visible on the screen (it is unpickled).

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhWrVe8qcMhJm_dfSVipRAzG1MPNlKFHvAMOpbgtUIKByZ7BeHZdoqTZIHhu9ZA79YzrTru7HKSKiEW7tG54fpiJA8Z5yrIfmM4Y4Dq7hkOm6Ohhnc3MLhYKNCEqnCkZUcEQhmzOpxe41IK2HWQ3vRDuVVW5kB13as9hHkm7sfBcNywbjipjUoKrLIJuUPp/s16000/13.png)

Finally, we access the uploaded PNG file on the server.

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgALVlkX1rTApK10Khic_OCaefFltd3M3JkXe8SxefOD-0-SsreXEgL3hgCcWh2LuoQn9NpBJLIAKu_-o25R9YrCP1z6_t4-5CLWivFGfjgPJ6N6FhaXqU6ULvXXqUC6TdrBk9pwp7sS-1KfTr1CnpH0zSmyI89mumFSks2f1vVb5Msr7EnXUDR-nDkGWJR/s16000/14.png)

We get a reverse shell on the netcat listener we set up this way!

![img](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhGSrcV1iyNnJ_yKI7PK2NTeABfvuXsF118p-abhpRSGa0agrJ7qLG-TPN83XX_5iHSROLkpQpokeypIW0jKVGSG42ezkli7MJ5mp06qifM1oo7_MqIJX0-ZXwGB-1eFBAHzr45YgTlgGU4yBgxV5SK-gPaoXuCkE-7yXJ48YCm5WBkPEfYSreUasVymbr4/s16000/15.png)

This is how we root the box! Please note that I hid and altered a few details throughout the CTF section of the article because the CTF is still an ongoing challenge and I couldn’t obtain permission to post a complete solution.

### **Conclusion**

Serialization vulnerabilities are easy to exploit and easy to overlook by developers. One can even achieve arbitrary code execution on machines. As we saw, when deserialization insecurely or by using insecure functions, we put our infrastructure at risk for compromise. Developers should carefully read the documentation page and not ignore warnings. Finally, use languages like json to serialize/deserialize data which can’t be used to contain executable code since it is a data-only language. Thanks for reading.

**Author: Harshit Rajpal** is an InfoSec researcher and left and right-brain thinker. Contact **[here](https://in.linkedin.com/in/harshit-rajpal-79bb43103)**
