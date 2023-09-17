# java ConcurrentLinkedQueue

java.util 에서 제공하는 Queue class는 멀티 스레드 환경에서 critical section에 대한 동기화가 적용되어 있지 않다. 즉 멀티 스레드 환경에서 Queue 객체를 사용하게 된다면 아래와 같은 코드에 문제가 발생한다. 

``` java
queue.poll();
```

poll() 함수는 Queue 객체 맨 앞에 들어있는 data를 꺼내오는 동작을 수행한다.  개발자가 원하는 동작은 꺼내오는 동시에 data가 삭제되는 것이다. 즉, 다음번 poll() 함수에서는 다른 data를 꺼내오게 될 것을 기대한다. **하지만 멀티 스레드 환경에서는 이런 기대는 만족되지 않는다.** 여러 스레드 중 2개나 3개의 스레드가 poll() 함수를 동일하게 수행하는 결과가 나타날 수 있기 때문이다. 만약 queue에 [“1”, “2”, “3”] 과 같이 데이터가 들어있다고 가정하자. 스레드 3개가 critical section에서 poll() 함수를 수행해 모두 “1”이라는 데이터를 가져오면 queue는 [“2”, “3”] 데이터를 가지게 된다. 이로써 개발자가 원한 동작은 수행되지 않고 예측하지 않은 동작이 수행돼 동작상에 에러가 발생할 수 있다. **멀티 스레드 환경**에서 이러한 문제를 **해결**하기 위해 제공하는 Class가 바로 **ConcurrentLinkedQueue**이다. 큐가 비어있을 경우 null을 리턴한다.

`사용예시`

``` java
mport java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
Queue<YourObject> queue = new ConcurrentLinkedQueue<YourObject>();

//data를 offer 할 때
put: queue.offer(Data);


//data를 꺼낼 때
get: queue.poll();
```



## ConcurrentLinkedQueue

`예제`

``` java
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {

    public static void main(String[] args) {
        // ConcurrentLinkedQueue를 생성합니다.
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        // 스레드 1
        Thread thread1 = new Thread(() -> {
            // 큐에 요소를 추가합니다.
            queue.add("Hello, world!");
        });

        // 스레드 2
        Thread thread2 = new Thread(() -> {
            // 큐에서 요소를 제거합니다.
            String element = queue.poll();
            System.out.println(element); // Hello, world!
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
```



출처: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentLinkedQueue.html

linked node를 기반으로 하는 **무제한 스레드 안전 큐**입니다. 이 큐는 요소 FIFO(선입선출) 순서를 지정합니다. 대기열의 헤드는 대기열에 가장 오랫동안 있었던 요소입니다. 대기열의 꼬리는 대기열에 가장 짧은 시간 동안 있었던 요소입니다. 새 요소는 대기열의 꼬리에 삽입되고 대기열 검색 작업은 대기열의 헤드에 있는 요소를 가져옵니다. ConcurrentLinkedQueue는 많은 스레드가 공통 컬렉션에 대한 액세스를 공유할 때 적절한 선택입니다. 대부분의 다른 동시 컬렉션 구현과 마찬가지로 이 클래스는 null 요소의 사용을 허용하지 않습니다.
이 구현은 Maged M. Michael과 Michael L. Scott이 쓴 Simple, Fast, Practical Non-Blocking and Blocking Concurrent Queue Algorithms에 설명된 내용을 기반으로 하는 효율적인 비차단 알고리즘을 사용합니다.

반복자는 약한 일관성을 가지며 반복자 생성 당시 또는 생성 이후의 대기열 상태를 반영하는 요소를 반환합니다. ConcurrentModificationException을 발생시키지 않으며 다른 작업과 동시에 진행할 수 있습니다. 반복자가 생성된 이후 대기열에 포함된 요소는 정확히 한 번 반환됩니다.

대부분의 컬렉션과 달리 크기 방법은 상수 시간 작업이 아니라는 점에 유의하세요. 이러한 대기열의 비동기적 특성으로 인해 현재 요소 수를 결정하려면 요소 순회가 필요하므로 순회 중에 이 컬렉션이 수정되면 부정확한 결과가 보고될 수 있습니다. 또한 대량 작업 addAll, RemoveAll, keepAll, containAll, equals 및 toArray는 원자적으로 수행된다는 보장이 없습니다. 예를 들어, addAll 작업과 동시에 작동하는 반복자는 추가된 요소 중 일부만 볼 수 있습니다.

이 클래스와 해당 반복자는 Queue 및 Iterator 인터페이스의 모든 선택적 메서드를 구현합니다.

메모리 일관성 효과: 다른 동시 컬렉션과 마찬가지로 개체를 ConcurrentLinkedQueue에 배치하기 전 스레드의 작업은 다른 스레드의 ConcurrentLinkedQueue에서 해당 요소에 액세스하거나 제거한 이후의 작업보다 먼저 발생합니다.

이 클래스는 Java 컬렉션 프레임워크의 멤버입니다.



### Method Summary

| Modifier and Type | Method and Description                                       |
| :---------------- | :----------------------------------------------------------- |
| `boolean`         | `add(E e)` <br>이 큐의 끝에 지정된 요소를 삽입합니다.        |
| `boolean`         | `addAll(Collection<? extends E> c)` <br>지정된 컬렉션의 반복자가 반환한 순서대로 지정된 컬렉션의 모든 요소를 이 큐의 끝에 추가합니다. |
| `boolean`         | `contains(Object o)`<br>이 큐에 지정된 요소가 포함되어 있으면 true를 반환합니다. |
| `boolean`         | `isEmpty()`<br/>이 큐에 요소가 없으면 true를 반환합니다.     |
| `Iterator<E>`     | `iterator()` <br/>이 큐의 요소에 대한 반복자를 적절한 순서로 반환합니다. |
| `boolean`         | `offer(E e)`<br/>이 큐의 끝에 지정된 요소를 삽입합니다.      |
| `E`               | `peek()`<br/>이 큐의 헤드를 검색하지만 제거하지는 않습니다. 또는 이 큐가 비어 있으면 null을 반환합니다. |
| `E`               | `poll()`<br/>이 큐의 헤드를 검색하고 제거하거나, 이 큐가 비어 있으면 null을 반환합니다. |
| `boolean`         | `remove(Object o)`<br/>지정된 요소가 있는 경우 이 큐에서 해당 요소의 단일 인스턴스를 제거합니다. |
| `int`             | `size()`<br/>이 큐에 있는 요소의 수를 반환합니다.            |
| `Spliterator<E>`  | `spliterator()`<br/>이 큐의 요소에 대한 Spliterator를 반환합니다. |
| `Object[]`        | `toArray()`<br/>이 큐의 모든 요소를 적절한 순서로 포함하는 배열을 반환합니다. |
| `<T> T[]`         | `toArray(T[] a)`<br/>이 큐에 있는 모든 요소를 적절한 순서로 포함하는 배열을 반환합니다. 반환된 배열의 런타임 유형은 지정된 배열의 런타임 유형입니다. |



 

 