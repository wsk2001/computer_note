# 내 컴퓨터에 연결된 ip 주소 확인하고 조회하기

https://rootblog.tistory.com/199



내 컴퓨터에 연결되어 있는 ip 주소들을 확인하고 해당 ip를 조회해서 문제가 없는지 확인하는 방법입니다.

 

인터넷이나 온라인 게임, 윈도우 업데이트 등을 하면 외부와 내 컴퓨터가 연결이 됩니다.

내가 인터넷에 접속하거나 온라인 게임, 윈도우/프로그램의 업데이트를 한 경우에는 정상적인 외부 연결로 통신이 되게 됩니다.

하지만 나 몰래 내 컴퓨터가 좀비 피씨로 이용되고 있거나 해킹을 또는 바이러스, 악성코드에 감염돼서 해커가 내 컴퓨터를 모니터링 중이거나 코인 채굴 프로그램이 몰래 설치돼서 가상 화폐 채굴에 이용되고 있거나 하는 경우에는 내가 모르 연결이 있을 수 있습니다.

netstat 명령어를 사용하면 내 컴퓨터와 연결된 모든 ip 주소를 확인할 수 있어서 위와 연결을 찾는 데 도움이 될 수 있습니다.

 

netstat 명령어를 사용하면 내 컴퓨터와 연결된 곳의 ip 정보를 확인할 수 있지만 단순 인터넷 페이지만 열어도 상당히 많은 수의 연결이 생기기 때문에 가능한 인터넷이나 윈도우/프로그램 업데이트가 모두 된 상태에서 5분 이상 통신을 안한 상태에서 진행하는 것이 좋습니다.

 

전문 악성 프로그램의 경우 연결 흔적을 숨기거나 필요시에만 연결을 하기 때문에 아래 방법으로 탐지가 안될 수 있습니다.

그런 경우가 의심되면 전문 모니터링 프로그램을 사용해서 계속 네트워크를 모니터링하거나 전문 보안 업체에 의뢰하는 것이 좋습니다.

 

 

**■ netstat 명령어 도움말**

 

![img](https://t1.daumcdn.net/cfile/tistory/9974DD4E5D7C701D05) 

netstat /?를 사용해서 확인한 도움말입니다.

 

netstat는 윈도우에서 기본으로 제공하는 명령어로 cui 방식이라서 명령 프롬프트에서 동작합니다.

netstat 명령어를 사용하면 네트워크 프로토콜의 통계와 연결 정보를 확인할 수 있습니다.

연결 정보를 확인하는 것 이외에 -e 옵션을 사용해서 연결 통계를 확인하거나 -r 옵션을 사용해서 라우팅 테이블을 확인할 때도 많이 사용합니다.

 

 

**◎ 내 컴퓨터에 연결된 ip 주소 확인과 조회**

 

![img](https://t1.daumcdn.net/cfile/tistory/9934F74E5D7C701D06)

"**netstat -na**" 명령어를 실행합니다.

-na 옵션은 모든 연결 정보를 ip 주소 형식으로 보여주는 명령어입니다.

명령어를 실행하면 내 컴퓨터에 연결되어 있는 모든 tcp, udp 연결들의 ip 주소를 표시해 줍니다.

여기서 확인해야 하는 것이 **외부 주소**와 **상태**입니다.

외부 주소는 내 컴퓨터와 연결된 곳의 ip 주소이고 상태는 연결 상태입니다.

 

[**https://rootblog.tistory.com/193**](https://rootblog.tistory.com/193)
△ ip주소 조회하기

 

외부 주소의 ip들을 위 링크를 참조해서 조회를 합니다.

조회 결과 내가 접속한 사이트와 관련 있는 곳이거나 윈도우나 프로그램의 자동 업데이트 때문에 연결된 곳이 아닌 ip가 있는지 확인을 해봅니다.

 

ip 주소 조회가 정확하게 나오지 않고 국가나 ip를 대여한 곳의 회사명 정도만 나오기 때문에 아주 확실하게는 알 수 없지만 내가 사용하는 프로그램을 제작한 국가가 아닌 곳에 연결된 것이 있을 경우 해킹이나 악성코드를 의심해 볼 수 있습니다.

 

상태는 통신이 성립하기 위한 과정 단계 중 어디인지 표시해 주는 것으로 LISTEN는 포트가 열려있어서 연결을 기다리는 상태를 의미하고 ESTABLISHED는 연결이 유효하게 되어 있는 상태를 의미합니다.

SVN_SENT, SVN_RECV는 처음 연결을 위해서 필요한 단계이고 TIME_WAIT, CLOSE_WAIT, CLOSED는 종료와 관련된 상태입니다.

ESTABLISHED가 유효한 연결 상태이기 때문에 우선 확인해 보는 것이 좋습니다.

 

 

![img](https://t1.daumcdn.net/cfile/tistory/9978AB4E5D7C701E3A)

위 스샷 중 한 개의 ip 주소를 조회해본 결과입니다.

ip 주소를 사용하는 단체가 마이크로소프트로 나와있습니다.

이런 ip 주소는 대부분 윈도우와 관련된 연결이기 때문에 문제가 없는 연결입니다.

꼭 그런 것은 아니지만 요즘에는 중국이나 일본같은 미국과 한국이 아닌 곳에 연결된 ip 주소가 있는 경우 컴퓨터에 문제가 있을 확률이 있으니 백신 프로그램이나 mzk를 사용해서 컴퓨터를 검사해 보는 것이 좋습니다.

 

[**https://rootblog.tistory.com/198**](https://rootblog.tistory.com/198)

△ MZK 멀웨어 제로 킷

 

 

 

![img](https://t1.daumcdn.net/cfile/tistory/99BDB14E5D7C701E04)

참고용으로 아이들시 아무것도 연결이 되어 있지 않은 경우의 스샷입니다.

아무 통신도 하지 않은 상태로 5~10분 정도 지나서 종료 상태도 모두 사라진 상태로 백그라운드에서 외부 통신이 없는 깨끗한 상태라고 보면됩니다.