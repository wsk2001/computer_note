# STL 정리

**표준 템플릿 라이브러리**(STL: Standard Template Library)는 [C++](https://ko.wikipedia.org/wiki/C%2B%2B)을 위한 [라이브러리](https://ko.wikipedia.org/wiki/라이브러리_(컴퓨팅))로서 [C++ 표준 라이브러리](https://ko.wikipedia.org/wiki/C%2B%2B_표준_라이브러리)의 많은 부분에 영향을 끼쳤다. 이것은 [알고리즘](https://ko.wikipedia.org/w/index.php?title=알고리즘_(C%2B%2B)&action=edit&redlink=1), [컨테이너](https://ko.wikipedia.org/w/index.php?title=컨테이너_(C%2B%2B)&action=edit&redlink=1), [함수자](https://ko.wikipedia.org/w/index.php?title=함수자_(C%2B%2B)&action=edit&redlink=1) 그리고 [반복자](https://ko.wikipedia.org/wiki/반복자)라고 불리는 네가지의 구성 요소를 제공한다.[[1\]](https://ko.wikipedia.org/wiki/표준_템플릿_라이브러리#cite_note-1)

STL은 컨테이너와 [연관 배열](https://ko.wikipedia.org/wiki/연관_배열) 같은 C++을 위한 일반 클래스들의 미리 만들어진 집합을 제공하는데, 이것들은 어떤 빌트인 타입과도 그리고 어떤 사용자 정의 타입과도 같이 사용될 수 있다. STL 알고리즘들은 컨테이너들에 독립적인데, 이것은 라이브러리의 복잡성을 눈에 띄게 줄여주었다.

STL은 결과를 [템플릿](https://ko.wikipedia.org/wiki/템플릿_(C%2B%2B))의 사용을 통해 달성한다. 이 접근법은 전통적인 [런타임 다형성](https://ko.wikipedia.org/wiki/다형성)에 비해 훨씬 효과적인 컴파일 타임 다형성을 제공한다. 현대의 C++ 컴파일러들은 STL의 많은 사용에 의해 야기되는 어떤 추상화 패널티도 최소화하도록 튜닝되었다.

STL은 제네릭 알고리즘과 C++을 위한 데이터 구조체들의 첫 번째 라이브러리로서 만들어졌다. 이것은 다음의 네가지를 기초로 한다. [제네릭 프로그래밍](https://ko.wikipedia.org/wiki/제네릭_프로그래밍), 효율성을 잃지 않은 [추상화](https://ko.wikipedia.org/wiki/추상화_(컴퓨터_과학)), [폰 노이만 구조](https://ko.wikipedia.org/wiki/폰_노이만_구조)[[2\]](https://ko.wikipedia.org/wiki/표준_템플릿_라이브러리#cite_note-stltutorial-2) 그리고 밸류 시멘틱스(value semantics)가 그것이다.



## 구성

### 컨테이너

STL은 연속 컨테이너들과 연관 컨테이너들을 포함한다. 컨테이너들은 데이터를 저장하는 객체들이다. 표준 연속 컨테이너들은 `vector`, `deque`, 그리고 `list`를 포함한다. 표준 연관 컨테이너들은 `set`, `multiset`, `map`, `multimap`, `hash_set`, `hash_map`, `hash_multiset` 그리고 `hash_multimap`이다. 또한 *container adaptors* `queue`, `priority_queue`, 그리고 `stack`이 있는데 이것들은 다른 컨테이너들을 구현으로서 사용하면서 특정한 인터페이스와 함께하는 컨테이너들이다.

| 컨테이너                                                     | 서술                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **pair**                                                     | pair 컨테이너는 간단한 연관 컨테이너로서 데이터 요소의 2-[튜플](https://ko.wikipedia.org/wiki/튜플) 또는 객체들로(고정된 순서에 따라 'first' 그리고 'second'로 불리는) 이루어진다. STL 'pair'는 배치, 복사 그리고 비교될 수 있다. 모든 'first' 요소들이 유니크 키로서 행동하고 각각이 자신의 'second' 값 객체들과 연관되는 곳에서, map 또는 hash_map에 할당된 객체들의 배열은 기본 값으로 'pair' 타입이다. |
| [vector](https://ko.wikipedia.org/wiki/벡터_(STL))           | C 배열과 같은 동적 배열 로서 객체를 삽입하거나 제거할 때 자동으로 자신의 크기를 조정하는 능력을 갖는다. vector의 end에 요소를 삽입하는 것은 상환 상수 시간(amortized constant time)을 필요로 한다. 마지막 요소를 제거하는 것은 단지 상수 시간을 필요로 하는데, 크기 조정이 일어나지 않기 때문이다. 처음 또는 중간에 삽입하거나 삭제하는 것은 선형 시간이 든다. 불린 타입을 위한 특수화가 존재하는데 이것은 불린 값을 비트에 저장하기 위해 공간을 최적화한다. |
| **list**                                                     | 이중 연결 리스트; 요소들이 근접한 메모리에 저장되지 않는다. 느린 검색과 접근(선형 시간)을 갖지만, 한 번 위치가 찾아지면 빠른 삽입과 제거 시간을 갖는다(상수 시간). |
| **slist**                                                    | 단일 연결 리스트; 요소들이 근접한 메모리에 저장되지 않는다. 느린 검색과 접근(선형 시간)을 갖지만, 한 번 위치가 찾아지면 빠른 삽입과 제거 시간을 갖는다(상수 시간). 이것은 이중 연결 리스트보다 삽입과 제거에서 약간 효율적이며 적은 메모리를 사용한다. 하지만 단지 전방향으로만 반복될 수 있다. C++ 표준 라이브러리에서 다음으로 구현된다. `forward_list` |
| [deque](https://ko.wikipedia.org/wiki/덱_(자료_구조))**(double-ended queue)** | 시작과 끝에서 삽입과 삭제를 상환 상수 시간에 수행하는 벡터이지만 덱을 교체한 이후에 반복자 유효성을 보장하지 않는다. |
| [queue](https://ko.wikipedia.org/wiki/큐_(자료_구조))        | FIFO 큐 인터페이스를 push/pop/front/back 연산들로 제공한다.  front(), back(), push_back(), 그리고 pop_front() 연산을 지원하는 어느 시퀀스들도 큐를 인스턴스화하는데 사용될 수 있다(예를 들면 list 그리고 deque). |
| [priority queue](https://ko.wikipedia.org/wiki/우선순위_큐)  | 우선순위 큐 인터페이스를 push/pop/top 연산들로 제공한다(높은 우선순위 요소가 위에 존재한다).  <br/>연산 front(), push_back(), 그리고 pop_back() 를 지원하는 어느 임의 접근 시퀀스도 priority_queue를 인스턴스화하는데 사용될 수 있다(예를 들면 vector 그리고 deque). 이것은 힙을 사용하여 구현된다. <br/>요소들은 추가적으로 비교를 지원해야 한다(어느 요소가 높은 우선순위를 갖는지와 먼저 pop되어야 하는지를 결정하기 위한). |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |

