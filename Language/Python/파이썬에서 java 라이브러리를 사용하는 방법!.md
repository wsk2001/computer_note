#  파이썬에서 java 라이브러리를 사용하는 방법!

출처: https://somjang.tistory.com/entry/Python-%ED%8C%8C%EC%9D%B4%EC%8D%AC%EC%97%90%EC%84%9C-java-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95-feat-Google-json-jar-%ED%8C%8C%EC%9D%BC



최근! 프로젝트를 진행하면서 솔루션에 SSO 로그인 기능을 붙이면서

그룹포탈에서 넘겨주는 쿠키 속에 암호화되어 들어가있는 사번 정보를 복호화 해야하는 작업이 필요했습니다.

 

암호화되어있는 사번을 복호화 하는 작업은

Java 로 만들어져있는 jar 파일로 되어있는 복호화 라이브러리를 활용해야했습니다.

 

하지만 솔루션은 Python 으로 개발 하였기때문에 Java로 만들어진 라이브러리를 바로 활용을 하기는 어려웠습니다.

 

어떻게 하지..? 복호화 알고리즘을 파악해서 우리가 구현을 해야하나..? 싶던 차에

다른 프로젝트에서 Node.js 에서 자바 라이브러리를 활용하여 SSO 로그인 기능을 구현한 것을 보고

오...! Python 도 Java 라이브러리를 불러서 사용하면 되지 않을까...! 라는 생각이 들었습니다.

 

검색해보니 Java 라이브러리를 Python 에서 활용하는 방법이 있었고

고객사에서 제공받은 jar 파일을 Python에서 로드하여 java 에서 로드한 뒤

복호화 하는 부분의 함수를 가져와서 SSO 로그인 기능을 구현할 수 있었습니다.

 

이번 글에서는!

이번 프로젝트에서 알게된 Python 에서 Java 라이브러리를 활용하는 방법에 대해서 공유하고자 합니다.

 

회사에서 업무를 볼 때 사용했던 라이브러리를 블로그 글에 쓸 수는 없으니 

Java 라이브러리 중에서 Json 을 다루는 GSON 라이브러리로 예시를 들어 보겠습니다.



### **👨🏻‍💻 GSON ( Google-Json )**

**https://yookeun.github.io/java/2017/05/27/java-gson/**

 Java에서 JSON(GSON)사용

Java에서 간단하게 json을 생성하고 파싱하는 방법을 알아보자. 여러가지 라이브러리가 있는데 우리는 여기서 구글에서 만든 gson를 이용하겠다. gson은 비교적 가볍고, 메이븐저장소를 지원하고 전

yookeun.github.io

Google에서 만든 json 라이브러리인 GSON 에 대한 자세한 설명과 사용방법은 위의 블로그를 참고해주세요.



### **👨🏻‍💻 JPype 라이브러리 설치**

**https://pypi.org/project/JPype1/**

먼저 pip 명령어를 활용하여 Jpype1 라이브러리를 설치합니다.

``` cmd
$ pip install JPype1
```

여기서 중요한점!

pip install jpype1 처럼 대소문자를 신경쓰지 않고 모든 글자를 소문자로 처리하는 경우가 종종 있는데 

**대소문자를 모두 지켜 입력하여야만 설치가 가능**합니다.



### **👨🏻‍💻 jar 파일을 원하는 위치에 위치시키기**

다운로드 받은 jar 파일을 내 프로젝트 코드 내 원하는 위치에 옮겨둡니다.

원하는 위치가 /app/somjang/solution/jar_files 이라면 

해당 위치에 jar 파일을 옮겨둡니다.



### **👨🏻‍💻 jar 파일을 Python 에서 로드하기**

#### **👨🏻‍💻 JPype import 하기**

먼저 설치한 JPype1 을 import 합니다.

``` py
import jpype.imports
from jpype.types import *
```



#### **👨🏻‍💻 로드 할 jar 파일 목록 만들기**

그 다음 내가 로드할 jar 파일 목록을 만들어야합니다.

여기에는 python 의 glob을 활용하였습니다.

``` py
import glob

def get_jar_file_list(path):
    """
    path 위치의 모든 jar 파일 목록을 Return 하는 함수
    * path example)
      - /app/somjang/solution/jar_files
    :param path: jar 파일이 위치해 있는 디렉토리 경로
    :return: path 위치의 모든 jar 파일 목록
    """
    return glob(path + "*.jar")
```

아까 내가 로드하려고하는 jar 파일을 /app/somjang/solution/jar_files 에 위치해 두었으니

``` py
path = "/app/somjang/solution/jar_files"
jar_file_list = get_jar_file_list(path=path)
```

위와 같이 작성하여 /app/somjang/solution/jar_files 아래의 모든 jar 파일 목록을 만들어줍니다.



#### **👨🏻‍💻 만든 jar 파일 목록을 활용하여 jar 파일 로드하기**

``` py
jpype.startJVM(classpath=jar_file_list)
```

jpype의 startJVM 메소드를 활용하여 아까 만들어둔 jar 파일들을 로드해줍니다.

*** 이 과정에서 최초 실행 시 약간의 시간이 소요됩니다.**

 

여기까지 하고 나면 이제 사용할 준비가 된 것 입니다.



### **👨🏻‍💻 로드한 Java 라이브러리 사용하기**

#### **👨🏻‍💻 GSON 라이브러리 import 하기**

위 과정까지 진행하였다면 이제 사용만 하면 됩니다.

``` py
import com.google.gson.Gson;
import com.google.gson.JsonObject;
```

Java에서 위와 같이 import 를 하여 사용하였다면!



``` py
from com.google.gson import Gson, JsonObject
```

Python 에서는 위와 같이 import 를 진행하면 됩니다.



#### **👨🏻‍💻 GSON 라이브러리 활용하여 json 만들기**

GSON 라이브러리를 활용하여

``` json
{
    "name": "somjang",
    "blog": "somjang.tistory.com",
    "email": "somjang@kakao.com"
}
```

위와 같은 json 을 만들어보겠습니다.

``` py
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Test
public void 단순_JSON_만들기() {
	Gson gson = new Gson();
	JsonObject object = new JsonObject();
	object.addProperty("name", "somjang");
	object.addProperty("blog", "somjang.tistory.com");
	object.addProperty("email", "somjang@kakao.com");
	String json = gson.toJson(object);
	System.out.println(json);
}
```

java 에서 위와 같이 코드를 작성한다고 하면



``` py
from com.google.gson import Gson, JsonObject

gson = Gson()
json_object = JsonObject()
json_object.addProperty("name", "somjang")
json_object.addProperty("blog", "somjang.tistory.com")
json_object.addProperty("email", "somjang@kakao.com")
json_result = gson.toJson(json_object)

print(json_result)
```

Python 에서는 위와 같이 작성하면 됩니다.



### **👨🏻‍💻 아주 유용할 것 같은 느낌적인 느낌!**

Python 에서 Java 라이브러리를 이렇게 쉽게 사용할 수 있다고하니

그동안 Python 으로 구현하면서 아쉬웠던 점이나 Java 에 이미 잘 구현되어있는 라이브러리가 있는 경우

이 방법을 활용해서 작업을 진행해도 좋겠다! 싶었습니다.

 

SSO 로그인 관련해서 고객사 측과 연락을 주고 받느라 많이 힘들기도 했지만

이런 꿀팁을 알게 되어 너무 좋았습니다.

 

읽어주셔서 감사합니다.