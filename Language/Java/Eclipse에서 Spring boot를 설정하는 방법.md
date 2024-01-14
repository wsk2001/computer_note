#  Eclipse에서 Spring boot를 설정하는 방법

출처: https://nowonbun.tistory.com/313



이 글은 Eclipse에서 Spring boot를 설정하는 방법에 대한 글입니다.

최근에 제가 여러 가지 이유 때문에 프레임워크를 Spring Web framework에서 Spring boot로 갈아탔습니다. Spring boot가 편하다는 건 이전부터 알고는 있었는데.

개인적으로 무언가 자동으로 설정되는 것을 좋아하지 않습니다.



이유는 여러 가지 있지만, 그 중에서 문제가 발생했을 때.. 어디서 에러가 발생하는지 명확하게 알기가 힘들다는 게 가장 큰 이유이겠네요. 그래서　저는 일부터 열까지 제가 한 땀 한 땀 설정하고 작성하는 것을 좋아합니다.(「프로그램은 자동으로 처리해 주는 것이 절대 없다! 분명 어딘가에서 처리를 했기 때문이다.」라는 주의입니다. 즉, 버그도 어딘가에서 잘못된 처리를 했기 때문에 발생한다라는 생각입니다.)

그런데.. 하나하나 설정해서 나가는 게 좋은 것만은 아닙니다. 생산성이 매우 떨어지고 설정해야 할 것이 너무 많아서 관리가 어렵다는 큰 문제가 있네요.. 그래서 안전성이 100%로 확보되지 않는 라이브러리는 사용도 안 합니다. 특히 오픈 라이브러리...(옛날 사람.. 옛날 사람...ㅎㅎ;;)



저도 생산성도 매우 중요하다고 생각합니다. 그러나 버그가 발생하고.. 그 버그를 파악하기 힘들고 대처하기 힘들다면.. 차라리 안 쓰는 게 낫다는 생각이라 여태 Spring boot를 사용하지 않았네요.

근데 최근 이슈로.. Spring boot framework를 주로 사용하시는 분이 많고 반대로 Web framework로 시작하시는 분이 많이 없네요.. 프로젝트를 혼자하는 것도 아니고..

혼자 Spring boot를 사용 안 할 수도 없고 해서 최근에는 Spring boot를 사용하기 시작했습니다.



그런데.. 아 너무 좋네요... Spring boot.. 너무 편합니다.. 최근에는 진작에 왜 사용하지 않았을까 하는 의구심이 들 정도로 안정성도 좋고 편하네요.

특히, 배포 전략이나 빌드 공정을 설정하는데 너무 편하네요... 다만 한 서버에 여러 웹 어플리케이션을 설치할 경우 톰캣이 많아진다는 문제가 있네요. 이것도 설정할 수 있을지도 모르겠으나.. 아직은 잘 모르겠습니다.



그래서 지금까지 Web framework에서 설정한 것을 Spring boot에서는 어떻게 설정이 되는지 개발 환경을 만드는지 설명하겠습니다.

IntelliJ는 Ultimate 버전을 설치하면 Spring boot 라이브러리도 자동으로 설정되더라고요.. IntelliJ가 너무 편하고 좋은데 유료라서.... 집에서는 Eclipse를 사용해야 합니다.



먼저 Eclipse를 설치해야 하는데... 이건 기존 방법과 같습니다.

링크 - [[Java\] 01. Java 설치와 Eclipse 설치](https://nowonbun.tistory.com/259)

Eclipse 다운로드 링크 - https://www.eclipse.org/downloads/packages/

![img](.\images\8wWRDLIIEvpqmSBDn9yFek_img.png)

적당한 곳에 압축을 풉니다. 저는 d:\eclipse 폴더에 설치했습니다.

![img](.\images\Qp6H9ZZE9ZtUKcWGram7g1_img.png)

이클립스를 실행하여 프로젝트 생성 위자드를 실행하게 되면 아직 Spring boot를 만들 수 있는 영역이 없습니다.

![img](.\images\8XbpASt2ff8aRh4yjVdSyk_img.png)

Help -> Eclipse MarketPlace를 선택합니다.

![img](.\images\YfrySnuA1qX8ypEPUyPJt1_img.png)

그리고 검색창에 sts로 검색을 합니다.



그러면 Spring tools 4가 보이는데 설치합니다.

![img](D:\GitHub\computer_note\Language\Java\images\jjTKxIVS5y1FRtObfZqF90_img.png)

![img](D:\GitHub\computer_note\Language\Java\images\whGcrUI6DLJcyIiSeDNkZ0_img.png)

오른쪽 아래의 툴바를 보면 설치 상황이 보이는데 설치가 완료될 때까지 기다립니다.

![img](.\images\tPRs4OQQgVkCXKt1JBAUF0_img.png)

설치가 완료되면 Eclipse가 재시작이 됩니다.



그리고　다시 프로젝트 생성 위자드를 열면 Spring boot 카테고리가 생성된 것을 확인할 수 있습니다.

![img](.\images\azIpKiYTSrlrFtqLm5WiQ0_img.png)

Spring Starter Project를 선택하고 프로젝트를 생성합니다.

![img](.\images\RNqF87obD40fE9oILUmAsK_img.png)

프로젝트 이름과 기본 패키지, Maven에서 사용될 Group과 버전 설정을 하고 Next를 누릅니다.



Spring boot는 처음 생성할 때 기본 Dependencies를 설정할 수 있는데.. 웹 환경을 사용하려면 Spring Boot DevTools, Thymeleaf, Spring Web을 설정합니다.

![img](.\images\OkhiVI9B29YpT30aFeAOo1_img.png)

Spring Boot DevTools는 개발할 때 도움을 주는 라이브러리이고, Spring Web은 Web Framework 라이브러리입니다.

DevTools를 체크하지 않아도 개발하는 데는 문제없으나, 없으면 디비깅 중 소스 수정할 때 템플릿 캐싱 설정이나 톰캣 재기동등 불편한 점이 있기 때문에 있는 편이 좋습니다. 나중에 릴리스(Production deploy)할 때는 pom.xml에서 제거하면 됩니다.

Spring Web은 기존의 Web Framework입니다. 다른 점은 예전에는 xml로 Web framework를 설정해야 할 것이 많이 있습니다. 그런데 Spring boot에서는 application.properties에서 모든 설정이 통합이 되어서 설정하기가 더 편해졌습니다.

Thymeleaf는 템플릿 프레임워크입니다. 사실 저는 Spring boot를 사용하기 전까지 JSTL(JSP)를 자주 사용했는데.. Spring boot에서는 Thymeleaf 템플릿 프레임워크를 추천하네요..

성능에 대해서 개인적으로 여러 테스트를 해봤는데 크게 차이를 못 느끼겠고.. 가독성 면에서는 JSP보다 Thymeleaf가 더 깔끔한 느낌이더라구요.. 근데 Thymeleaf는 좀 구조가 독특해서.. Thymeleaf의 문법에 대해서는 따로 설명하도록 하겠습니다.



마지막으로 Finish를 눌러서 프로젝트를 생성합니다.



프로젝트를 생성 후에 몇 분이 지나면 프로젝트가 생성이 되는데 아래와 같은 구조가 생성이 됩니다.

![img](D:\GitHub\computer_note\Language\Java\images\jJkVJg5RT5dvItTjbhBMX1_img.png)

먼저 설정할 부분이 application.properties입니다.

`application.properties`

```properties
# 서버 포트 설정
server.port=8081
# thymeleaf 파일 설정
spring.thymeleaf.prefix=classpath:templates/
spring.thymeleaf.check-template-location=true
# thymeleaf 파일 확장자
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML5
# thymeleaf 캐쉬 모드
spring.thymeleaf.cache=false
```

그리고 기본적으로 Controller 클래스를 생성할 때는 @SpringBootApplication 어노테이션과 main 함수가 있는 클래스와 같은 패키지 혹은 하위 패키지에 Controller를 설정할 수 있습니다.

저는 하위 패키지를 생성해서 HomeController를 생성했습니다.

`HomeController.java`

``` java
package com.example.demo.Controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
// 컨트럴러 어노테이션
@Controller
public class HomeController {
  // 매핑 주소
  @RequestMapping(value = {"/", "/index.html"})
  public String index(Model model) {
    // 템플릿에 전달할 데이터
    model.addAttribute("data", "hello world");
    // 템플릿 파일명
    return "Home/index";
  }
}
```



![img](.\Java\images\0pZ36lm0y10jTmgle4h1lK_img.png)

같은 패키지가 아닌 곳에 설정하려면 @SpringBootApplication 어노테이션에 scanBasePackages 값을 설정하면 됩니다.


![img](https://blog.kakaocdn.net/dn/bcn2yS/btrt3e3xVQu/J56V8XxKB5QEVJcY2kiu21/img.png)

이번에는 컨트럴러에서 호출한 Thymeleaf 템플릿을 설정합니다.

![img](.\images\yTrKywHNN2yQuUPwKzSrU1_img.png)

index 함수에서 Home/index파일을 호출하였기 때문에 Home 폴더 밑의 index.html 파일을 생성합니다.

``` html
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
  <!-- 컨트럴러에서 받은 데이터를 출력 -->
  <span th:text="${data}">message</span>
</body>
</html>
```

템플릿 파일까지 생성이 되었으면 이제 실행합니다.



실행하는 방법은 Project Explorer의 프로젝트에서 마우스 오른쪽 클릭을 하거나 main 함수가 있는 곳에서 마우스 오른쪽 클릭을 하면 Run as 또는 Debug as 에서 Spring boot App 메뉴가 있는데 클릭을 하면 실행을 할 수 있습니다.

![img](.\images\eZr2jpcLMVMqDwIrEWJbN0_img.png)

![img](D:\GitHub\computer_note\Language\Java\images\OqDB0W8xWYpk79GlRyrWY0_img.png)

실행을 하면 위와 같은 로그가 발생하는데 application.properties에서 설정한 포트로 접속을 하면 정상적으로 실행하는 것을 확인할 수 있습니다.

![img](.\images\I0siAFC9dI5TFfshxdAY8K_img.png)

여기까지가 Spring boot의 기본적인 설정입니다.

앞으로 JPA도 Web Framework와 설정이 다르기 때문에 다시 설정을 해야 할 것 같고, Apache 연결 등도 다시 확인해야 하겠네요.

Thymeleaf 문법도 정리가 필요할 것 같고, 처음 템플릿 위자드을 보니 기본적으로 지원하는 라이브러리가 많이 있는데 하나하나 확인해 봐야겠네요.



여기까지 Eclipse에서 Spring boot를 설정하는 방법에 대한 글이었습니다.