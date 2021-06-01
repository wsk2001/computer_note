# Java ThreadPoolExecutor

출처: http://wonwoo.ml/index.php/post/2254

오늘은 java의 `ThreadPoolExecutor`에 대해서 알아보도록 하자. 많은 내용은 아니지만 기본적으로 사용하려면 알아두어야 할 것들만 알아보자.

`ThreadPoolExecutor`은 클래스명 그대로 스레드풀을 편하게 관리해주는 클래스이다. `ThreadPoolExecutor`의 최상위 인터페이스는 `Executor` 이며 그에 따른 구현체들은 아주 많으니 문서를 살펴보는 것이 좋을 것 같다. 그 중 가장 많이 사용될 만한 클래스가 `ThreadPoolExecutor`라 오늘은 해당 클래스에 대해 살펴보고 나중에 기회가 된다면.. `ForkJoinPool`에 대해서도 알아보도록 하자.



## ThreadPoolExecutor

`ThreadPoolExecutor`은 4개의 생성자가 있다. 비슷비슷해서 눈이 아프다. 일단 아래의 코드는 ThreadPoolExecutor의 생성자이다.

```
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
```

공통적으로 `corePoolSize`, `maximumPoolSize`, `keepAliveTime`, `unit`, `workQueue` 가 존재하면 실제로 이 다섯가지의 파라미터가 가장 중요한 역할을 한다. 나머지가 중요하지 않다는걸 의미한건 아니다.

일단 하나씩 살펴보자.

- **corePoolSize** : 풀 사이즈를 의미한다. 최초 생성되는 스레드 사이즈이며 해당 사이즈로 스레드가 유지된다. 해당 Job의 맞게 적절히 선택해야 한다. 많다고 성능이 잘나오는 것도 아니고 적다고 안나오는 것도 아니다. 충분히 테스트하면서 적절한 개수를 선택해야 한다.
- **maximumPoolSize** : 해당 풀에 최대로 유지할 수 있는 개수를 의미한다. 이 역시 Job에 맞게 적절히 선택해야 한다.
- **keepAliveTime** : corePoolSize보다 스레드가 많아졌을 경우 maximumPoolSize까지 스레드가 생성이 되는데 keepAliveTime 시간만큼 유지했다가 다시 corePoolSize 로 유지되는 시간을 의미한다. (그렇다고 무조건 maximumPoolSize까지 생성되는 건 아니다.)
- **unit** : keepAliveTime 의 시간 단위를 의미한다.
- **workQueue** : corePoolSize보다 스레드가 많아졌을 경우, 남는 스레드가 없을 경우 해당 큐에 담는다.

해당 파라미터가 어떤 역할을 하는지 알아봤다.



여기서 조금 더 알아볼게 있는데 바로 `maximumPoolSize`와 `workQueue` 파라미터이다. 필자가 위에서 `그렇다고 무조건 maximumPoolSize까지 생성되는 건 아니다.`를 주목하자.

실제로 `corePoolSize`가 스레드 개수보다 많다고 해서 `maximumPoolSize` 개수 까지 바로 생성하지 않는다. 그 전에 큐(workQueue)에 담고 대기한다. (구현체마다 다르지만, 일반적으로) 그리고 나서 `workQueue`에도 담을 공간이 부족하다면 그때 `maximumPoolSize` 까지 스레드를 늘려 작업을 한다. 그 후 `keepAliveTime`에 도달하면 다시 `corePoolSize` 로 유지 된다.

여기서 잠시 코드를 보자.

```java
public static void main(String[] args) throws Exception {
    LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(9);
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 3 ,3, SECONDS, queue);
    for (int i = 0; i < 10; i++) {
        executorService.execute(new Task());
    }
    executorService.awaitTermination(5, SECONDS);
    executorService.shutdown();
}

private static class Task implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}
```

위와 같은 코드가 있다고 가정해보자. corePoolSize는 1, maximumPoolSize는 3, 그리고 9개의 큐가 있다고 가정해보자. 그리고 나서 10개의 스레드를 만들어 돌려보면 어떻게 될까?

다시 해당 옵션에 대해 보면 1개의 core size와 9개의 큐가 있으니 1개의 잡이 실행되며 9개는 큐에 대기 상태가 된다. 그래서 1초에 하나씩 Thread Name이 출력 된다.

이번엔 큐 사이즈를 8개로 줄여보자.

```
LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(8);
```

그렇다면 어떻게 실행될까? corePoolSize 는 1개와 8개의 큐가 있어 `maximumPoolSize` 동작한다. 1개 잡과 8개가 대기하므로 1개의 스레드가 추가되어 1초에 2개씩 Thread Name이 출력 된다.

이벤엔 6개로 줄여보자.

```
LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(6);
```

이번엔 1개의 잡이 실행되고 큐에 6개가 쌓여 대기하여 `maximumPoolSize` 만큼 스레드가 증가하나 1개의 스레드가 갈곳이 없어 에러가 발생한다.

```
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task Example$Task@50040f0c rejected from java.util.concurrent.ThreadPoolExecutor@2dda6444[Running, pool size = 3, active threads = 3, queued tasks = 6, completed tasks = 0]
    at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
    at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
    at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
```

이왕 한김에 `keepAliveTime`도 테스트를 해보자. 해당 테스트는 대략적인 테스트이므로 정확하지 않을 수 있다.

```
public static void main(String[] args) throws Exception {
    LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(7);
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 3 ,3, SECONDS, queue);
    for (int i = 0; i < 10; i++) {
        executorService.execute(new Task());
    }
    SECONDS.sleep(7);
    for (int i = 0; i < 5; i++) {
        executorService.execute(new Task());
    }
    executorService.awaitTermination(5, SECONDS);
    executorService.shutdown();
}
```

`keepAliveTime` 를 3초로 주고 테스틀을 해보자. 처음에는 10개 스레드를 3개의 코어 돌리니 대략 4초가 걸리고 중간에 7초 정도 대기를 타고 있으니 3초가 조금 넘는 시간이 될 것 같다.
위의 코드를 테스트를 해보면 두번째 `execute` 에서는 다시 1초에 한개의 `Thread Name`이 출력 되는 걸 볼 수 있다.

`SECONDS.sleep(6)`로 주면 3초 이전에 다시 `execute`를 하기에 `maximumPoolSize`로 스레드가 실행되는 걸 알 수 있다.

이정도만 알아도 충분히 `ThreadPoolExecutor`을 사용할 수 있을 것 같다.

## 몇가지 참고

#### Executors

```
Executors.newSingleThreadExecutor()
Executors.newFixedThreadPool()
Executors.newCachedThreadPool()
Executors.newWorkStealingPool()
```

해당 factory 메서드로 Executor등을 만들 수 있다. 해당 건은 다른 블로그를 참고했으면 좋겠다. 필자는 없다……..

#### Spring

Spring을 사용한다면 `ThreadPoolTaskExecutor`를 살펴보는 것도 좋다. 내부 구현은 `ThreadPoolExecutor`로 구현되어 있다. ThreadPoolExecutor 보다 조금 더 간편하며, 추가적인 return 타입도 있다.

```
@Bean
public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setQueueCapacity(100);
    executor.setMaxPoolSize(30);
    executor.set...
    return executor;
}
```

#### LinkedBlockingQueue vs SynchronousQueue

`LinkedBlockingQueue` 와 `SynchronousQueue`는 BlockingQueue의 구현체들이다. 다른점이 있다면 `SynchronousQueue` 버퍼공간이 존재 하지 않는다. 그래서 스레드가 넘칠 경우 에러가 발생한다.
만약 대기 큐를 쓰고 싶다면 `LinkedBlockingQueue` 구현체를 사용해야 하며 동적으로 스레드를 만들고 싶다면 `SynchronousQueue` 를 이용해야 한다.

Spring 의 `ThreadPoolTaskExecutor`도 `queueCapacity` 0 보다 크다면 `LinkedBlockingQueue`로 그렇지 않다면 `SynchronousQueue`으로 BlockingQueue 의 구현체를 설정한다.

```
protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
    if (queueCapacity > 0) {
        return new LinkedBlockingQueue<>(queueCapacity);
    }
    else {
        return new SynchronousQueue<>();
    }
}
```

#### submit, execute

ThreadPoolExecutor에는 많은 메서드가 있지만 submit, execute 메서드의 차이는 return 이 되냐 되지 않는냐의 차이 이다. 실제 구현은 동일하다. (submit 은 execute를 호출한다)
또한 submit 에는 Runnable 파라미터 타입과 Callable 타입이 존재하는데 Callable 타입은 checked exception 을 throws 하고 Runnable는 그렇지 않다.

```
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}

@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

오늘 이렇게 Java ThreadPoolExecutor에 대해서 알아봤다. 많은 내용은 아니였지만 그래도 조금이나마 이해를 하며 사용하는 것이 좋아 작성해봤다.