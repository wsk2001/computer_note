# java ExecutorService 활용 하기 

출처:  https://erim1005.tistory.com/entry/ExecutorService%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-multi-thread-%ED%99%9C%EC%9A%A9%ED%95%98%EA%B8%B0-Java



#### Multi Thread와 ExecutorService

Multi thread는 여러가지 작업을 병행해서 동시에 처리 한다. 즉 순서대로 처리 하는 것이 아닌 닥치는 대로 하는 것이다. 이러한 멀티 쓰레드를 자바는 JDK 1.0부터 지원해 왔다. 

 

Thread 클래스를 선언하여 간편하게 사용이 가능하다. 

```java
public static void main(String[] args) {
	Runnable task = new Runnable() {
		public void run() {
			System.out.println("Thread: " + Thread.currentThread().getName());
		}
	}

	for (int i = 0; i < 10; i++) {
		Thread trhead = new Thread(task);
		thread.start();
	}
}
```

하지만 간편하게 사용하는 만큼 여러 상황에 마주치게 될 때 상단히 곤란해 지기도 한다. 몇개의 쓰레드를 사용할 것인지 정해진 시간마다 반복할 것인지 외에도 많은 상황들을 마주치게 된다.

 

그래서 **ExecutorService**와 **Executors** 가 등장하게 되었다. 쓰레드를 관리해주는 클래스인 것이다.

```
public static void main(String[] args) {
	Runnable task = new Runnable() {
		public void run() {
			System.out.println("Thread: " + Thread.currentThread().getName());
		}
	}
	
	ExecutorService service = Executors.newFixedThreadPool(10);

	for (int i = 0; i < 10; i++) {
		service.submit(task);
	}
}
```

 

**Executors**가 생성해주는 **ExecutorService**는 흔히 **ThreadPool**(쓰레드풀)이라고 불리며 다음과 같은 것들이 있다.

- **newFixedThreadPool(int)** : 인자 갯수만큼 고정된 쓰레드풀을 생성한다.
- **newCachedThreadPool()**: 필요할 때, 필요한 만큼 쓰레드풀을 생성한다. 이미 생성된 쓰레드를 재활용할 수 있기 때문에 성능상의 이점이 있을 수 있다**.**
- **newScheduledThreadPool(int)**: 일정 시간 뒤에 실행되는 작업이나, 주기적으로 수행되는 쓰레드풀을 인자 갯수만큼 생성한다.
- **newSingleThreadExecutor()**: 단일 쓰레드인 풀을 생성한다. 단일 쓰레드에서 동작해야 하는 작업을 처리할 때 사용한다.
- **newWorkStealingPool(int parallelism)**: 시스템에 가용 가능한 만큼 쓰레드를 활용하는 ExecutorService를 생성한다.

 

이중 JDK 1.8 버전에서 새로 나온 newWorkStealingPool은 나중에 나온만큼 신박하다. 

고정된 크기의 풀 사이즈를 사용하는 일반적인 다른 **ExecutorService**와 달리 고정된 호스트 컴퓨터의 CPU에서 활용 가능한 코어의 갯수에 대응하는 크기로 풀 사이즈를 조절한다.

 

더보기

------

#### 실행(Execute)과 콜백(Callback)

쓰레드를 생성하여 실행하는 작업은 크게 두가지로 나눈다.

- 1. 작업 후 종료 (execute)

- 2. 작업 후 결과 보고 (callback)

 

각각의 필요에 따라 원하는 명령을 실행하면 된다.

먼저 execute 메서드를 살펴보면 다음과 같이 되어있다.

```
void execute(Runnable command);
```

작업 시키면 알아서 할테니 신경 끄면 된다.

 

그리고 작업에 대한 보고를 받기를 원할 때는 submit 명령을 사용한다.

```
<T> Future<T> submit(Callable<T> task);
```

**Runnable** 을 넘겨주지 않고 대신 **Callable**<T>를 넘겨준다. 그러면 **Future**<T> 를 돌려준다고 되어있다. 그러면 우리는 **Future**를 통해서 진행중인 작업을 취소하거나 작업이 완료되고 난 후에 <T> 형태의 리턴값을 받을 수 있다.

```java
Callable<Integer> callable = () -> {
	Thread.sleep(1000);
	return 1;
}
Future<Integer> future1 = executorService.submit(callable);
Future<Integer> future2 = executorService.submit(callable);
Future<Integer> future3 = executorService.submit(callable);

try {
	// 작업이 끝날때 까지 기다려 값을 받기
	int value = future1.get();

	// 작업 취소하기. 취소 여부를 돌려받는다.
	boolean canceled = future2.cancel(true);

	// 500 밀리세컨드 동안만 기다려 값을 받기 그안에 안나오면 TimeoutException
	int value = future3.get(500, TimeUnit.MILLISECONDS);

} catch (InterruptedException e) {
	e.printStackTrace();
} catch (ExecutionException e) {
	e.printStackTrace();
} catch (TimeoutException e) {
	e.printStackTrace();
}
```

------

#### 작업 동시 실행과 동시 강제종료

invokeAll

그리고 **ExecutorService**는 모든 작업을 **Collection**<? **extends** **Callable**<T>> 형태로 보내 한꺼번에 수행하는 기능도 가지고 있다. 이때 인자로 아무 것도 없으면 모든 작업이 종료될 때까지 기다리거나, (**long** timeout, **TimeUnit** unit)을 전달해 해당 시간까지 기다린 후에 종료되지 않았다면 **CancellationException**을 발생시킨다.

```java
Callable<Integer> callable = () -> {
	Thread.sleep(1000);
	return 1;
};

List<Callable<Integer>> callableList = new ArrayList<>();

for (int i = 0; i < 10; i++) {
	callableList.add(() -> {
		Thread.sleep(1000);
		return 1;
	});
}

try {
	List<Future<Integer>> futures = executorService.invokeAll(callableList);
} catch (InterruptedException e) {
	e.printStackTrace();
}

try {
	List<Future<Integer>> futures = executorService.invokeAll(
		callableList,
		500,
		TimeUnit.MILLISECONDS
	);
} catch (InterruptedException e) {
	e.printStackTrace();
}
```

invokeAny

첫번째로 작업을 끝낸 결과를 반환하고 나머지는 무시하도록 한다.

 

awaitTermination(long timeout, TimeUnit timeUnit)

지정한 시간만큼 모든 작업이 끝나길 기다리고 시간 후에는 끝나지 않은 모든 작업을 강제로 종료시킨다.

```
executorService.awaitTermination(30, TimeUnit.SECONDS);
```

 