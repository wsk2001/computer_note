### STL 정리 및 예제

STL(Standard Template Library) - 표준 템플릿 라이브러리

1.  list
    - list 컨테이너는 vector와 deque 처럼 시퀀스 컨테이너로 원소가 상대적인 순서를 유지한다.
    - 그러나 list는 노드 기반 컨테이너로 원소가 노드 단위로 저장되며 list는 이중 연결 리스트로 구현된다.

2.  map
    - map 컨테이너는 연관 컨테이너 중 자주 사용하는 컨테이너로 원소를 key 와 value의 쌍으로 저장한다.
    - set은 원소로 key 하나만을 저장하지만, map 은 원소로 key와 value의 쌍(pair 객체)를 저장한다.
    - set처럼 원소의 key는 컨테이너에 중복 저장될 수 없으며 중복 key를 저장해야 한다면 mulitmap을 사용해야 한다.

3.  vector
    - vector 컨테이너는 대표적인 시퀀스 컨테이너로 배열과 비슷하여 사용이 쉬우며 자주 사용된다.
    - vector는 임의 접근 반복자(Random Access Iterator)를 지원하는 배열 기반 컨테이너이다.

4.  deque
    - deque는 시퀀스 컨테이너이며 배열 기반 컨테이너이다.
      
5.  set
    - set 컨테이너는 연관 컨테이너 중 단순한 컨테이너로 key라 불리는 원소(value).의 집합으로 이루어진 컨테이너이다.
    - 모든 연관 컨테이너는 노드 기반 컨테이너이며 균현 이진 트리로 구현되므로 균형 이진 트리의 모든 특징을 갖는다.

6.  multimap
    - map 컨테이너에 중복 원소(key)를 허용해야 한다면 multimap 컨테이너를 사용한다.
    - map은 key를 중복해서 컨테이너에 저장할 수 없지만 multimap은 여러 key를 중복해서 저장할 수 있다.

7.  multiset
    - multiset 컨테이너는 중복 원소를 컨테이너에 저장할 수 있다는 것 외에는 set과 다른 것이 없다.
    - set 처럼 원소가 자동 정렬되는 연관 컨테이너에 노드 기반 컨테이너이다.

8.  iterator
    - 반복자는 컨테이너에 저장된 원소를 순회하고 접근하는 일반화된 방법을 제공한다.
    - 반복자는 컨테이너와 알고리즘이 하나로 동작하게 묶어주는 인터페이스 역할을 한다.

9.  find  
    - find 함수는 iterator 순차열 범위에서 원하는 값을 가진 iterator 반복자 위치를 찾아서 반환한다.
    - 만약 해당 값을 찾지 못하면 반환하는 iter 는 end() 를 가리킬 것이다.

10. reverse_iterator
    - 기본 iterator에 반복자 어댑터 reverse_iterator 를 이용해 정방향 반복자를 역방향 반복자로 변환할 수 있다.

