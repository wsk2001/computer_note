# 자바 JAR 파일 만드는 법 및 이클립스에서 사용방법 정리

출처: https://wakestand.tistory.com/347



 ![img](.\images\jar-img-001.png)



자바에서 JAR는 Java Archive의 약자인데 이게 뭔 말이냐면 클래스 파일들과 해당 클래스에 사용되는 이미지 등을 JAR 안에 넣어놓고 타 프로젝트에서 해당 JAR를 import 하면 JAR에 작성한 클래스들을 뽑아다 쓸 수 있게 된다

 자주 보게 되는 예제로는 Oracle과 Java를 연결하는 데 사용하는 OJDBC JAR를 들 수 있는데 기존 자바에는 오라클과 자바를 연결하는 클래스가 없지만 OJDBC라는 JAR를 만들어 자바에서 OJDBC JAR를 import 하면 자바에서 오라클을 컨트롤 할 수 있도록 해 놓았다

 즉 오라클과 자바를 연결하려는 사람들은  해당 JAR를 import 하기만 하면 필요한 클래스나 메소드를 OJDBC Jar에서 뽑아다 사용할 수 있게 된다

 쉽게 이해할 수 있도록 이클립스에서 JAR 파일을 만들어 보고 타 프로젝트에서 해당 JAR 파일을 import 해서 활용해 보자

 처음은 자바 JAR를 만드는 과정인데

 ![img](.\images\jar-img-002.png)



먼저 프로젝트를 하나 만들고 pack1 패키지를 만들고 그 안에 Test1 클래스를 만들고 Test1 클래스 안에 calc 메소드를 생성한다

 내용은 int 타입의 파라미터를 넘기면 파라미터에 10을 더해서 반환하는 간단한 내용이다

 ![img](.\images\jar-img-003.png)



이걸로 클래스 작성은 끝이다 다음으로 해당 프로젝트 우클릭 > Export를 누른 뒤

 ![img](.\images\jar-img-004.png)



Java > JAR file을 눌러주고

 ![img](.\images\jar-img-005.png)

JAR를 만드려는 프로젝트가 맞는지 확인하고 하단의 JAR file:의 경로를 설정해 주는데 이게 JAR 파일 을 어디에 저장할건지 물어보는거다
이후 Finish를 누르면 Jar 파일이 아래와 같이 설정한 경로에 생성되는 것이 보인다

 ![img](https://blog.kakaocdn.net/dn/uQsuz/btqJrx8n89D/emWfsnv6rvt8i4lTln4hHK/img.png)

 ![img](.\images\jar-img-006.png)
다음에는 JAR 파일을 만들었으니 JAR 파일을 Import 해서 활용해 보자 
새 프로젝트를 만든 뒤 우클릭 후 Build Path > Configure Build Path를 누른다

 ![img](.\images\jar-img-007.png)

Libraries 탭에서 Add External JARs를 누른 뒤 아까 내가 생성했던 JAR를 선택해주면

 ![img](.\images\jar-img-001-1.png)

 ![img](.\images\jar-img-008.png)

프로젝트 내 Referenced Libraries에 내가 작성한 jar 파일이 들어온 것이 보이는데 왼쪽에서 화살표 버튼을 눌러 보면 내가 작성했던 Test1.class가 보인다

근데 소스는 없고 왜 클래스 파일만 있냐면 자바에서 클래스 파일은 소스를 보여주지 않으면서 실제 작동에는 차이가 없는 파일을 말하는데 이런 식으로 클래스만 넘겨주면 소스는 나만 소유하면서도 실제 사용에는 아무 지장이 없게 된다

 ![img](.\images\jar-img-009.png)

마지막으로 실 사용 시에는 import 패키지명.* 으로 해당 패키지의 전체 Class를 import하거나 혹은 import 패키지명.클래스명으로 원하는 Class만 import 가능한데 main 메소드 부분에서 Test1 t1 = new Test1(); 으로 JAR 파일의 Test1.class를 가져와 t1에 넣고  t1.calc(10) 메소드를 수행하니 메소드가 잘 수행되어 10이 더해진 20이 나오는 것이 보인다

 

마지막으로 정리해보자면

**JAR란?**

Java Archive의 약자로 자바 클래스, 클래스에 사용되는 이미지 등을 모아놓은 파일!

 

**JAR를 왜 쓰는거지?**

각 프로젝트별로 필요한 클래스와 기능들을 따로 구현하기에는 손이 너무 많이 가고 JAR 파일을 만들어 두면 JAR 파일을 import 할 시 해당 기능을 모두 동일하게 사용할 수 있다

 

예를 들자면 오라클과 자바를 연결하는 OJDBC JAR가 있는데 이걸 JAR 없이 프로젝트별로 개별로 작성한다 생각하면 답이 안나온다

 

**JAR export란?**

프로젝트를 JAR 파일 형태로 빼내는 것

 

**JAR import란?**

JAR 파일을 프로젝트 내로 가져오는 것 이후 개별 클래스에서 import를 통해 JAR 파일의 내용을 사용할 수 있다



