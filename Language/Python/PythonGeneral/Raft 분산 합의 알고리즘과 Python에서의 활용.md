# Raft 분산 합의 알고리즘과 Python에서의 활용

출처: https://d2.naver.com/helloworld/5663184

이 글은 Raft 분산 합의(consensus) 알고리즘을 설명하고, 라이브러리로 내장하는 것을 목적으로 이번에 새로 만든 파이썬 구현체를 소개합니다. 이 구현체는 파이썬으로 분산 프로그래밍을 하거나 높은 가용성이 필요한 경우 참고하면 유익할 것이라고 기대합니다.

분산 환경에서는 컴퓨팅에 참여하는 모든 노드가 현재 상태를 동일하게 유지하는 것이 중요합니다. 분산 환경에서 이런 합의를 위한 프로토콜로 가장 오래 사용되었던 것은 **팩소스**(Paxos)였으나, 스펙이 복잡하고 구현이 힘들다는 단점이 있었습니다(참고: [위키백과](https://ko.wikipedia.org/wiki/팩소스_(컴퓨터_과학))). 이에 대한 대안으로, 스펙상 몇 가지 제약을 걸어 구조를 단순화하여 나온 것이 **래프트**(Raft)입니다(참고: [위키백과](https://ko.wikipedia.org/wiki/래프트_(컴퓨터_과학))).

Raft는 실구현체를 만드는 것이 복잡하지 않아 언어별로 많은 구현체가 있으며 파이썬 구현체도 여러 개 있습니다. 그러나 이들은 대부분 단독으로 사용되는 경우를 상정하고 있어, 코디네이터 내장 프로그램을 만들려는 경우 확장이 여의치 않습니다. 파이썬 구현체 자체의 활용도도 높지는 않은데, 분산 코디네이터로는 ZooKeeper나 Consul 등이 이미 많이 쓰이고 있기 때문입니다.

파이썬 응용에서 분산 코디네이션이 필요한 경우 kazoo를 통해 외부의 ZooKeeper를 사용하는 경우가 일반적인데, 이 방식은 몇 가지 단점이 있습니다.

- ZooKeeper 앙상블을 별도로 운영해야 하는데, 최소 3노드(일반적으로 5노드) 이상의 인스턴스가 별도로 필요합니다.
- ZooKeeper는 메모리 사용량이 많아 보통 하나의 앙상블을 구성한 후 여러 응용이 같이 사용하는 경우가 많습니다. 이 경우 ZooKeeper 장애 및 운영 작업이 단일 장애점(single point of failure, SPOF)이 될 수 있습니다.
- ZooKeeper에 저장되어 있는 데이터를 사용하려면 응용 내의 데이터로 매핑 작업을 해야 합니다.

반면 Raft를 쉽게 내장할 수 있으면 별도의 외부 분산 코디네이터를 상정하지 않아도 되며, 구현에 따라 응용 데이터를 별도의 변환없이 노드 간에 동기화할 수 있습니다.

이 글에서는 Raft 동작 방식을 좀 더 자세히 설명하고, 파이썬에서 라이브러리로 import하여 동작시킬 수 있는 구현체를 설명하고 이를 활용한 간단한 예를 들며 마무리하고자 합니다.

## Raft

Raft는 전술했듯이 분산 환경에서의 합의를 위한 방법은 투표(voting)와 로그 엔트리 복제로 크게 나뉜다. 참여하는 노드들은 투표를 통해 과반의 투표를 얻은 leader를 선출하고, 변경 내역은 leader를 통해서만 follower에게 전달하여 반영된다.

이 글에서는 이를 이해하는 데 도움이 될 수 있게 가장 핵심적인 부분만을 서술한다. Raft에 대한 자세한 설명은 [공식 웹사이트](https://raft.github.io/)를 참고한다.

먼저 노드의 상태 변화를 살펴보자. 노드는 leader, follower, candidate의 세 상태가 될 수 있다. leader는 leader로 선출된 상태를 의미하며, follower는 leader가 선출된 이후 leader로부터 변경 내역이 담긴 AppendEntry 메시지를 받아 처리하는 상태이다.

leader는 변경 내역이 담긴 AppendEntry를 follower에게 전송하되, 변경 내역이 없더라도 일정 시간마다 하트비트로 더미 AppendEntry를 보내야 한다. 일정 시간이 지나도 leader로부터 AppendEntry를 받지 못하면 follower들은 자신의 상태를 candidate로 변경한다. candidate는 leader로 선출될 수 있는 후보군으로, candidate 중 과반의 투표를 얻은 노드가 leader로 선출된다.

이전 leader가 네트워크 단절 등으로 한참 후에 돌아오는 경우 자신을 leader로 알고 있는 노드가 복수 개 존재할 수 있는데 이를 해결하기 위해 투표가 새로 시작될 때마다 term을 하나씩 증가시킨다. 단절된 leader가 전송하는 AppendEntry는 term이 더 낮으므로 다른 노드들에게 무시되며, 단절된 leader는 자신보다 term이 높은 leader의 AppendEntry를 수신하면 자신을 해당 term의 follower로 변경한다.

다음은 투표 과정을 살펴보자. candidate는 해당 term 중 랜덤한 시간만큼 기다리며 투표 요청이 오면 해당 노드를 leader로 선출하는 응답을 한 표만 행사한다. 즉, 다음에 도착하는 다른 노드의 투표 요청은 무시한다.

만일 랜덤한 대기 시간까지 투표 요청이 오지 않으면 다른 노드들에 투표 요청을 보내고 과반의 투표를 얻으면 자신을 leader로 만들고 나머지 노드들에 AppendEntry를 보낸다. candidate가 해당 term의 leader에게 AppendEntry를 받으면 자신을 follower로 변경하고 해당 term의 leader 선출이 마무리된다.

투표 과정은 candidate들이 해당 term에서 한 표씩만을 행사하기 때문에 과반의 투표를 얻은 노드는 하나만 있을 수 있어 안전한 leader 선출이 보장된다. 만일 타이밍상의 문제로 과반의 투표를 얻은 candidate가 존재하지 않으면 term을 올리고 다시 새로운 투표를 수행한다.

이 과정은 위의 설명과 함께 [Raft Scope](https://raft.github.io/raftscope/index.html)의 애니메이션을 보면 쉽게 이해할 수 있다.

마지막으로, AppendEntry의 처리 부분이다. AppendEntry에는 leader가 선출되었을 때의 term과 로그 엔트리의 일련번호(seq)를 포함하여 전송한다. follower는 전송받은 AppendEntry의 term이 자신과 같고(이전 term이면 단절된 leader이므로 무시한다) seq가 마지막으로 받은 seq보다 1 클 때 이를 반영한다. 만일 seq가 예상했던 값이 아니면 보정 작업을 수행한다.

노드들은 동일한 상태에서 시작하며, leader는 변경되는 내역을 직렬화하여 로그 엔트리로 만들고 이를 같은 순서로 follower에게 전달한다. 그리고 follower는 leader와 동일한 순서로 로그 엔트리를 반영하면 노드 간의 동기화가 유지된다. 예를 들어 다음 세 요청이 각각의 클라이언트들에서 왔다고 가정하자.

```
client 1: set A 10

client 2: set A 30

client 3: set B 20  
```

leader는 Mutex를 통해 클라이언트의 변경 요청을 직렬화한다. 만약 그 결과 처리 순서가 다음과 같이 정해졌다고 가정하자.

```
seq 1: set A 30

seq 2: set B 20

seq 3: set A 10  
```

모두 처리된 결과는 A = 10, B = 20이 된다. leader가 이 요청들을 처리하면서 각각의 요청을 로그 엔트리로 만들고 seq 번호를 붙여 follower에게 전달하면 leader, follower들은 모두 같은 상태가 된다.

추가로 생각할 점은, follower가 일시적으로 단절되어 로그 엔트리를 받지 못하다 다시 돌아온 경우 leader는 지난 로그를 전달해 주어야 하기 때문에 로그를 유지하고 있어야 한다는 것이다. 단, 무한정 로그를 계속 쌓아 둘 수 없기 때문에, 일정 시간마다 스냅샷을 생성하여 해당 seq에서의 leader의 모든 상태를 저장하고 로그는 그 이후부터 유지함으로써 스냅샷 생성 이전의 로그는 제거한다. 만일 어느 노드가 추가로 앙상블에 조인하거나 follower가 장기간 단절되었다 돌아오는 경우에는 leader가 스냅샷을 전달하고 그 이후 seq의 로그 엔트리를 반영하여 동기화할 수 있다.

마지막으로, split brain이 되는 경우 과반의 투표를 얻지 못한 구 leader가 변경 작업을 진행하는 것을 막기 위해 leader는 사용자 변경 요청을 최종 커밋하기 전에 follower로부터 과반의 응답을 얻어야 한다. 이를 위해 커밋 인덱스라는 개념이 존재하며, leader가 follower 과반의 응답을 얻고 자기 자신에게도 반영한 로그 엔트리의 인덱스가 커밋 인덱스가 된다. 해당 커밋 인덱스는 AppendEntry에 같이 전달되며 follower는 자신이 가지고 있는 로그 엔트리를 바로 반영하는 게 아니라 인덱스가 커밋 인덱스 이하인 로그 엔트리만을 반영한다.

## pyraft 소개

pyraft는 Raft의 파이썬 구현체로, Raft의 투표와 로그 엔트리를 구현했고 범용성을 위해 로그 엔트리 명령은 Redis RESP 프로토콜을 사용했다. pyraft에 대한 자세한 설명은 [github 저장소](https://github.com/lynix94/pyraft)를 참고한다. [pip](https://pypi.org/project/pyraft)로도 설치할 수 있다.

주요 파일은 raft.py와 resp.py이다. resp.py는 Redis RESP Protocol Encoding/Decoding 구현체로, raft.py에서 사용한다. Raft 구현체가 파일 하나에 다 들어가 있으므로 사용하려면 Raft만을 import하면 된다.

Raft 노드의 핵심 객체는 RaftNode 클래스로, 해당 객체를 생성하고 실행시키면 Raft 선출이 시작된다.

다음은 RaftNode를 생성하고 실행하는 코드이다.

```python
from pyraft import raft

n1 = raft.RaftNode(1, '127.0.0.1:5010', {2:'127.0.0.1:5020', 3:'127.0.0.1:5030'})  
n2 = raft.RaftNode(2, '127.0.0.1:5020', {1:'127.0.0.1:5010', 3:'127.0.0.1:5030'})  
n3 = raft.RaftNode(3, '127.0.0.1:5030', {1:'127.0.0.1:5010', 2:'127.0.0.1:5020'})

n1.start()  
n2.start()  
n3.start()

n1.join()  
n2.join()  
n3.join()  
```

RaftNode 생성자의 첫 번째 인수는 노드 아이디로, 여기서는 편의상 숫자로 했으나 문자열도 사용 가능하다.

두 번째 인수는 사용할 주소로, n1의 경우 5010 포트를 지정했는데 실제로는 5010, 5011을 같이 사용하며, 5010은 사용자 요청을 처리하는 데 쓰고 5011은 내부 투표 프로세스를 처리하는 데 사용한다.

세 번째 인수는 앙상블의 peer map으로, n1의 경우 n2, n3의 nid와 주소를 지정했다. n1, n2, n3의 세 노드가 각각의 peer 정보를 전달받은 상태로 start() 인터페이스가 실행되면 투표 작업이 실행되고, 투표가 완료된 후부터는 사용자의 요청을 처리할 수 있다.

다음은 leader로 선출된 5010 포트로 접속하여 set를 실행하고, follower인 5020에 접속하여 append_entry가 반영되었는지 확인하는 모습이다.

```cmd
lynix@~/lab/pyraft$ telnet localhost 5010  
Trying 127.0.0.1...  
Connected to localhost.  
Escape character is '^]'.  
set key value  
+OK
get key  
+value


lynix@~/lab/pyraft$ telnet localhost 5020  
Trying 127.0.0.1...  
Connected to localhost.  
Escape character is '^]'.  
get key  
+value
```

RaftNode를 쉽게 CLI에서 실행할 수 있도록 make_defaut_node 함수가 있으며 다음과 같은 옵션을 기본값으로 사용한다. 이 함수를 사용한 실행 파일이 example 디렉터리의 run_raft.py로, 이 파일을 사용하여 별도 코드 없이 기본 RaftNode를 구동할 수 있다.

```
python3 run_raft.py -a IP:PORT [-i NODE_ID] [-e ENSEMBLE_LIST]  
  ex) python3 run_raft.py -a 127.0.0.1:5010 -i 1 -e 2/127.0.0.1:5020,3/127.0.0.1:5030
  ex) python3 run_raft.py -a 127.0.0.1:5010 -i 1 -e 127.0.0.1:5020,127.0.0.1:5030
lynix@~/lab/pyraft$  
lynix@~/lab/pyraft$ python3 run_raft.py -i 1 -a 127.0.0.1:5010 2/127.0.0.1:5020,3/127.0.0.1:5030  
[INFO][1-1(c):2020-04-05 17:51:46.806923] get 1. voters: ['1']
[INFO][1-1(c):2020-04-05 17:51:46.807097] 1 is a leader
[INFO][1-1(c):2020-04-05 17:51:46.807145] on_leader called
```

-m 옵션을 사용하면 모듈에서 바로 실행할 수도 있다.

```
lynix@~/lab/pyraft$ python3 -m pyraft.run_raft -i 1 -a 127.0.0.1:5010  
[INFO][1-1(c):2020-04-13 02:14:37.768422] get 1. voters: ['1']
[INFO][1-1(c):2020-04-13 02:14:37.768472] 1 is a leader
[INFO][1-1(c):2020-04-13 02:14:37.768510] on_leader called
```

RaftNode의 leader 선출은 leader_election 스레드가 수행하며, 투표를 수행하고 그 결과에 따라 노드의 상태를 변경하여 상태에 따라 do_leader, do_follower, do_candidate 중 하나를 수행한다. 선출이 진행되면 RaftNode.state의 값이 변경되며, 변경되는 순간 경우에 따라 RaftNode.on_leader, RaftNode.on_follower, RaftNode.on_candidate가 실행된다. on_leader 등의 이벤트 함수는 worker.handler에 같은 이름의 이벤트 핸들러 함수가 등록되어 있는 경우 해당 함수를 실행하는데, 이를 이용해 RaftNode의 상태에 맞는 동작을 실행할 수 있다. 이는 뒤의 run_ex1.py, run_ex2.py에서 볼 수 있다.

사용자 요청은 worker 스레드가 수행하는데, 요청받은 명령이 worker.handler에 있는지 확인한 후 있는 경우 해당 handler 함수를 호출한다. 단, handler.table에 해당 요청이 write, entry 속성이 있는 경우는 follower에도 전파되어야 하기 때문에 invoke_append_entry를 호출하여 follower에 append_entry 로그를 전송한다. append_entry 로그를 생성할 때는 data lock을 통해서 변경 요청이 일련의 순서로 인덱스를 발부받아 생성된다. 이 append_entry 로그는 RaftNode.log에 추가되어 apply_loop 스레드에 의해 순서대로 RaftNode.data에 반영된다.

현재 handler.table에 등록되어 있는 명령은 get, set, del, expire, expireat, pexpire, pexpireat이며, worker.handler에 콜백을 등록하는 것만으로 데이터 처리 기능을 추가할 수 있다.

AppendEntry 처리를 위해 leader는 최근 로그는 list로 유지하되, 영속성을 위해 로그를 파일로도 기록해 놓는다. 그리고 주기적으로 스냅샷을 생성하고 해당 시점 이전의 로그는 제거한다. 스냅샷은 간편하게 node.data의 repr() 결과로 출력되는데, 이는 현재 pyraft가 간편 분산 코디네이터로 사용된다고 산정했기 때문이다. 만일 더 큰 규모의 데이터를 쌓고자 한다면 pickle 등으로 변경할 필요가 있다.

## pyraft와 Raft의 차이

pyraft에서는 사용성을 개선하기 위해 Raft 프로토콜의 일부를 수정했다. Raft 스펙에서는 노드들이 follower 상태에서 시작하며 투표를 하지만 pyraft는 개별 시작, 조인이 가능하게 하기 위해 노드들은 candidate로 시작하고 peer의 목록은 동적으로 구성한다.

실행 프로그램 중 하나를 보면 run_raft.py를 다음과 같이 실행하면 1번 노드는 단독으로(peer 없이) candidate로 시작하고 자신을 leader로 선출한다. 편의상 로컬에서 시작하는 예로 설명하겠다.

```cmd
python3 run_raft.py -i 1 -a 127.0.0.1:5010  
```

다음 노드는 다음과 같이 2번 아이디와 5020 포트로 시작하되, 기존 앙상블로 -e 127.0.0.1:5010 옵션을 설정할 수 있다.

```cmd
python3 run_raft.py -i 2 -a 127.0.0.1:5020 -e 127.0.0.1:5010  
```

2번 노드는 앙상블로 알고 있는 1번 노드에 앙상블 조인을 요청하고 1번 노드는 peer에 2번 노드를 등록한 후, 자신이 leader이기 때문에 ping(더미 append_entry)을 보내 2번 노드를 follower로 만든다. 2번 노드는 candidate 상태였기 때문에 ping을 받는 즉시 follower 상태로 진입한다.

3번 노드도 같은 방식으로 기존 앙상블에 조인하면 1, 2번 노드는 peer에 3번을 추가하고, 역시 1번 노드는 leader로서 3번에 ping을 보내서 follower로 등록한다.

단, ping이 오기 전에 candidate는 자신에게 투표했다 선출에 실패하면 term을 1 올릴 수 있기 때문에, leader가 선출되는 경우 leader는 term을 10 올린다. leader의 첫 번째 ping이 가기 전까지 신규 진입하는 candidate보다 leader의 term이 더 높게 유지되도록 하기 위해서이다.

동일한 설정으로 zoo.cfg를 맞추고 구동하는 ZooKeeper와 비교해보면 앙상블 관리가 훨씬 유연해짐을 알 수 있다.

append_entry가 follower 과반의 응답을 받지 못하는 경우도 약간 다른데, Raft에서는 응답을 받지 못하는 follower에게 계속 재시도하고 과반의 응답을 받으면 클라이언트에게 결과를 전송한다. 그러나 pyraft에서는 무한정 기다리지 않고 타임아웃까지 과반의 응답을 받지 못하면 오류를 반환하고 해당 append_entry는 무효화한다. 이미 해당 append_entry를 받은 follower는 해당 로그를 가지고 있다가(leader의 커밋 인덱스가 올라가지 않았기 때문에 반영되지 않음) 동일한 인덱스 요청이 다시 오면 교체한다.

## pyraft 사용 예

이 라이브러리를 사용하면 분산 코디네이터를 내장한 응용 프로그램을 쉽게 만들 수 있다.

가장 간단한 예로, N개의 파이썬 프로그램이 특정 URL이 유효한지 주기적으로 확인하는 예를 들어보자. 특정 URL의 목록을 set으로 추가하면 N개의 노드 중 leader만이 확인 작업을 하되 만일 leader가 단절되면 새로 선출된 leader가 이 작업을 속개한다. 이를 통해 특정 URL들이 유효한지 확인하는 프로그램을 가용성 있게 실행할 수 있다.

on_leader를 확장하여, on_leader에서 호출되는 url_checker는 node.data를 순회하며, url_로 시작하는 키들의 URL 패스를 얻어와 유효한지 확인하고 접근이 되지 않을 때는 로그를 남긴다.

`python3 run_ex1.py -i 1 127.0.0.1:5010`으로 구동한 뒤 다음과 같이 telnet이나 redis 클라이언트로 5010 노드에 접근하여 url_ 접두사가 붙은 URL을 등록하면 leader는 follower에게 해당 URL을 append_entry로 전달한다. 따라서 모든 노드의 값이 동일해진다.

leader가 선출되면 on_leader 콜백인 url_check_start가 실행되며 이 콜백은 url_checker 스레드를 구동한다. url_checker는 노드를 넘겨받아, RaftNode.state가 'l'인 경우에만(leader인 경우에만) URL 확인 동작을 수행한다. 확인할 URL은 사용자로부터 set으로 설정되는 값들이다.

```python
import os, sys, time  
import threading, urllib.request, urllib.error, urllib.parse  
from pyraft import raft


def url_checker(node):  
    while not node.shutdown_flag:
        time.sleep(5)

        if node.state != 'l':
            continue

        for k, v in node.data.items():
            if not k.startswith('url_'):
                continue

            try:
                url = v 
                if not v.startswith('http'):
                    url = 'https://' + v 

                result = urllib.request.urlopen(url).read()
                print('url %s is ok' % k) 
            except Exception as e:
                print('url %s is bad - %s' % (k, e))


def url_check_start(node):  
    print('url check start...')
    if not hasattr(node, 'checker'):
        node.checker = threading.Thread(target=url_checker, args=(node,))
        node.checker.start()


node = raft.make_default_node()

node.worker.handler['on_leader'] = url_check_start

node.start()  
node.join()  
```

다음은 앙상블 노드 3개를 구동한 후 set을 이용하여 URL 3개를 등록하고 URL이 유효한지 확인하는 모습이다. leader인 노드 1을 강제로 정지시킨 후 노드 3가 새로 leader로 선출되어 재개되는 모습이다.

```
# nid 1,2,3을 각각 5010, 5020, 5030 포트로 구동
lynix@~/lab/pyraft$ python3 run_ex1.py -i 1 -a 127.0.0.1:5010  
[INFO][1-1(c):2020-04-10 02:26:29.373656] get 1. voters: ['1']
[INFO][1-1(c):2020-04-10 02:26:29.373806] 1 is a leader
[INFO][1-1(c):2020-04-10 02:26:29.373832] on_leader called
=====

lynix@~/lab/pyraft$ python3 run_ex1.py -i 2 -a 127.0.0.1:5020 -e 127.0.0.1:5010  
[INFO][2-0(c):2020-04-10 02:26:32.732939] connect to __TEMP_127.0.0.1:5010__
...
[INFO][2-11(c):2020-04-10 02:26:34.377497] on_follower called
=====

lynix@~/lab/pyraft$ python3 run_ex1.py -i 3 -a 127.0.0.1:5030 -e 127.0.0.1:5010  
[INFO][3-0(c):2020-04-10 02:26:36.135556] connect to __TEMP_127.0.0.1:5010__
...
[INFO][3-3(c):2020-04-10 02:26:37.113431] get 1. voters: ['3']
[INFO][3-11(c):2020-04-10 02:26:37.379385] on_follower called
# telnet으로 leader에 접근하여 확인할 URL을 등록
lynix@~/lab/pyraft$ telnet localhost 5010  
Trying 127.0.0.1...  
Connected to localhost.  
Escape character is '^]'.

set url_orca https://github.com/lynix94/orca-lang/blob/master/docs/10min_kr.md  
+OK
set url_krill https://github.com/lynix94/orca-krill/blob/master/README_kr.md  
+OK
set url_bad https://badurl/not_exist.hmtl  
+OK
# telnet으로 URL을 등록한 후의 5010 포트 leader 모습. URL 확인 결과가 계속 출력된다.
[DEBUG][1-11(l):2020-04-10 02:26:48.551103] leader write to 3: "['append_entry', 11, 1, ....
[DEBUG][1-11(l):2020-04-10 02:26:48.823028] apply command [3]: "['set', 'url_bad', 'https://badurl/not_exist.hmtl']"
url url_bad is bad - <urlopen error [Errno -2] Name or service not known>  
url url_krill is ok  
url url_orca is ok  
url url_bad is bad - <urlopen error [Errno -2] Name or service not known>  
url url_krill is ok  
url url_orca is ok  
^Z
[1]+  Stopped                 python3 run_ex1.py -i 1 -a 127.0.0.1:5010
lynix@~/lab/pyraft$ kill % # url_checker가 도는 것을 확인 후 leader를 강제로 정지  
# 5010 leader가 정지된 후, leader 선출이 실행되어 5030 포트 노드 3가 leader로 선출되어 url_checker를 재개
[INFO][3-11(f):2020-04-10 02:27:01.979082] on_candidate called
[INFO][3-12(c):2020-04-10 02:27:02.411162] get 2. voters: ['3', '2']
[INFO][3-12(c):2020-04-10 02:27:02.411214] 3 is a leader
[INFO][3-12(c):2020-04-10 02:27:02.411232] on_leader called
[INFO][3-22(l):2020-04-10 02:27:02.411883] send snapshot to 2(0)
[INFO][3-22(l):2020-04-10 02:27:02.412116] send snapshot to 2(0)
url url_bad is bad - <urlopen error [Errno -2] Name or service not known>  
url url_krill is ok  
url url_orca is ok  
url url_bad is bad - <urlopen error [Errno -2] Name or service not known>  
url url_krill is ok  
url url_orca is ok  
url url_bad is bad - <urlopen error [Errno -2] Name or service not known>  
url url_krill is ok  
```

다음 예는 expire 명령을 사용하여 세션을 관리하는 예이다. ZooKeeper의 임시 노드(ephemeral node)와 유사하며, 외부의 특정 응용 프로그램이 주기적으로 자신의 이름에 타임스탬프를 set하여 최종 업데이트한 시간을 남긴다. 만일 해당 응용이 종료되면 타임스탬프를 갱신하지 못한다.

Raft 노드 중 leader는 node.data를 순회하며, app_로 시작하는 키들의 타임스탬프 값을 가져와 이것이 1분을 넘어가면 dead 로그를 남긴다.

다음은 이의 구현 코드이다. 외부 클라이언트가 주기적으로 expire를 실행하다 멈추었을 경우 응용 프로그램이 'session expired'를 출력한다. 여기서는 단순히 출력만 하지만 실제 상황에서는 이에 대응하는 로직을 넣으면 된다. 구동 및 구조는 url_checker와 유사하다.

```python
import os, sys, time  
import threading  
from pyraft import raft


def session_checker(node):  
    while not node.shutdown_flag:
        time.sleep(5)

        if node.state != 'l':
            continue

        for k, v in list(node.data.items()):
            if not k.startswith('session_'):
                continue

            ts = int(v)
            curr_ts = time.time()

            if curr_ts - ts < 60:
                print('session %s is ok (diff: %d)' % (curr_ts - ts))
            else:
                print('session %s is EXPIRED (diff: %d)' % (curr_ts - ts))


def session_check_start(node):  
    if not hasattr(node, 'cheker'):
        node.checker = threading.Thread(target=session_checker, args=(node,))
        node.checker.start()


node = raft.make_default_node()

node.worker.handler['on_leader'] = session_check_start

node.start()  
node.join()  
```

on_leader 이벤트 외의 기능을 확장하는 것도 간단하다. 다음은 run_ex3.py로 cas(check and set) 명령을 핸들러에 등록하는 예다. cas는 입력되는 값이 cas key expected value와 같은 형태일 때 key의 현재 값이 expected와 같으면 value로 변경하고 expected 값을 반환한다. 현재 값이 expected와 다르면 변경을 하지 않고 현재 값을 반환한다. key가 없는 특이한 경우는 expected 값은 'none'이 된다.

```python
import os, sys, time  
import threading  
from pyraft import raft

def do_cas(node, words):  
    key = words[1]
    expected = words[2]
    value = words[3]

    node.check_ttl(key) # lazy deleting which expired

    if key not in node.data:
        if expected.lower() == 'none':
            node.data[key] = expected
        else:
            return None

    old_value = node.data[key]
    if old_value != expected:
        return old_value

    node.data[key] = value
    node.clear_ttl(key)
    return expected


node = raft.make_default_node()

node.worker.handler['cas'] = [do_cas, 'we', 3, 3] # [function, type(we means write & entry), minimum param, maximum param]

node.start()  
node.join()  
```

구동 방법은 이전과 같으며 다음과 같이 check and set으로 잘 동작한다.

```
lynix@~/lab/pyraft$ telnet localhost 5010  
Trying 127.0.0.1...  
Connected to localhost.  
Escape character is '^]'.  
get key  
$-1
cas key None 1  
+None
get key  
+1
cas key 2 3  
+1
get key  
+1
cas key 1 3  
+1
get key  
+3
```

cas로 변경된 항목은 append_entry 로그로 do_cas가 전파되어 모든 follower에도 적용된다. 이런 식으로 분산 환경의 check and set을 아주 간단히 추가할 수 있고, 비슷한 방식으로 분산 locking도 쉽게 구현할 수 있다.



## 마치며

이상으로 Raft와 그 파이썬 구현체에 대한 설명했다. 3개나 5개의 노드로 앙상블을 구성하여 실행하면 별도의 ZooKeeper나 Consul을 운영할 필요 없이 단독으로 가용성 있는 프로그램을 만들 수 있다. 서론에서 전술했듯이, 본 프로그램은 ZooKeeper처럼 단독으로도 사용할 수 있지만 그보다는 내장하는 것을 염두에 두고 만들었으며, 간단한 기능 추가는 핸들러 테이블에 콜백 핸들러를 등록하는 것만으로 가능하고 좀 더 복잡한 기능이 요구되는 경우에는 아예 RaftNode를 상속해서 만들 수 있다.

프로그램은 현재 Raft를 활용할 수 있는 가장 최소한의 요건만 구현되었는데, 활용성이 높아지려면 앞으로도 많은 개선이 필요하다.

몇 가지 예를 들어보면 pyraft에 참여하는 노드들은 모두 투표에 참여하고 있는데, 투표 비효율을 막기 위해 3~5개의 노드만 투표에 참여하고 나머지 노드들은 로그 엔트리만 반영하는 구조로 개선할 수 있다.

또한 현재는 k/v 자료 구조만 지원하는데 내부 핸들러를 확장하면서 파이썬 collection을 활용하면 쉽게 list, set, hash도 지원 가능해진다. 이렇게 하면 앞의 예에서 전체 데이터를 traverse하지 않게 구현할 수 있다. stream 자료 구조까지 확장한다면, 구독 모델을 사용하여 현재 미비한 데이터 변경에 대한 notify를 보완할 수 있다.

이 부분들은 추후 개선될 예정이며 이 부분 및 기타 부분에 대한 기여는 언제나 환영한다.

