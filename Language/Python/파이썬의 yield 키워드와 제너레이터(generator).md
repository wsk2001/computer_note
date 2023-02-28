# 파이썬의 yield 키워드와 제너레이터(generator)

출처: https://www.daleseo.com/python-yield/

이번 포스팅에서는 파이썬에서 제네레이터(generator)를 만드는데 사용되는 `yield` 키워드에 대해서 알아보겠습니다.

## yield 키워드

대부분의 프로그래밍 언어에서 일반적으로 함수는 어떤 결과 값을 `return` 키워드를 이용해서 반환을 합니다. 하지만 파이썬에서는 함수가 `yield` 키워드를 이용해서 다소 **다른 방식으로** 결과 값을 제공할 수 있습니다.

장황한 설명보다는 간단한 예제 코드부터 살펴보는 것이 좀 더 이해가 쉬울 것 같습니다.

알파벳 `A`, `B`, `C`를 결과 값으로 반환하는 함수를 작성해보겠습니다.

```py
def return_abc():
  return list("ABC")
```

위 함수를 `yield` 키워드를 이용해서 작성해볼까요?

```py
def yield_abc():
  yield "A"
  yield "B"
  yield "C"
```

가장 먼저 눈에 두드러지는 차이는 `return` 키워드를 사용할 때는 결과값을 딱 한 번만 제공하는데, `yield` 키워드는 결과값을 여러 번 나누어서 제공한다는 것입니다.

`for` 루프를 사용해서 위 함수를 호출하여 얻은 결과를 화면에 출력해보겠습니다.

```py
for ch in return_abc():
  print(ch)
A
B
C
for ch in yield_abc():
  print(ch)
A
B
C
```

함수를 사용하는 측면에서 보면 두 함수는 큰 차이가 없어보이는데요.

함수를 호출한 결과 값을 바로 출력하여 도대체 각 함수가 정확히 무엇을 반환하는지 알아보겠습니다.

```py
>>> print(return_abc())
['A', 'B', 'C']
>>> print(yield_abc())
<generator object yield_abc at 0x7f4ed03e6040>
```

`return_abc()` 함수는 리스트(list)를 반환하고, `yield_abc()` 함수는 제너레이터(generator)를 반환하다는 것을 알 수 있습니다.

여기서 우리는 `yield` 키워드를 사용하면 제너레이터를 반환한다는 것을 알 수 있는데요. 과연 generator는 어떤 개념일까요?



## 제너레이터(generator)

파이썬에서 제너레이터는 여러 개의 데이터를 미리 만들어 놓지 않고 필요할 때마다 즉석해서 하나씩 만들어낼 수 있는 객체를 의미합니다.

예를 들어, 위에서 작성한 예제 코드를 알파벳 하나를 만드는데 1초가 걸리도록 수정해보겠습니다.

```py
import time

def return_abc():
  alphabets = []
  for ch in "ABC":
    time.sleep(1)
    alphabets.append(ch)
  return alphabets
```

위 함수를 호출한 결과를 `for` 루프로 돌려보면 3초가 후가 흐른 후에 `A`, `B`, `C`가 한 번에 출력이 되는 것을 볼 수 있습니다.

```py
for ch in return_abc():
  print(ch)
# 3초 경과
A
B
C
```

이 번에는 `yield` 키워드를 이용해서 동일한 결과 값을 제공하는 함수를 작성해보겠습니다.

```py
import time

def yield_abc():
  for ch in "ABC":
    time.sleep(1)
    yield ch
```

위 함수를 호출한 결과를 `for` 루프로 돌려보면 1초 후에 `A`를 출력되고, 또 1초 후에 `B`를 출력되고, 또 1초 후에 `C`가 출력이 될 것입니다.

```py
for ch in return_abc():
  print(ch)
# 1초 경과
A
# 1초 경과
B
# 1초 경과
C
```

만약에 세개의 알파벳이 아닌 백개, 천개, 만개의 알페벳을 제공해야하는 경우에는 어떨까요? 첫번째 방식에서는 첫번째 결과값을 얻는데 백초, 천초, 만초가 걸리는 반면에, 두번째 방식에서는 항상 일초가 걸릴 것입니다. 즉, 제너레이터를 통해서는 결과값을 나누어서 얻을 수 있기 때문에 성능 측면에서 큰 이점이 있습니다.

메모리 효율 측면에서도 이 두가지 방식은 큰 차이를 보이는데요. `return` 키워드를 사용할 때는 모든 결과 값을 메모리에 올려놓아야 하는 반면에, `yield` 키워드를 사용할 때는 결과 값을 하나씩 메모리에 올려놓습니다.

제너레이터(generator)는 이러한 특성 때문에 흔히 게으른 반복자(lazy iterator)라고도 불리는데요. 이 제너레이터의 게으른 특성을 잘 활용하면 좀 더 효율적인 프로그램을 작성할 수 있는 경우가 많습니다.

특히 메모리에 한 번에 올리기에는 부담스럽게 대용량의 파일을 읽거나, 스트림 데이터를 처리할 때 상당히 유용하게 사용될 수 있습니다.

> 예제 코드에서 실행 지연을 위해서 사용한 `time` 모듈에 대한 자세한 내용은 [관련 포스팅](https://www.daleseo.com/python-time)을 참고 바랍니다.

## 무한 데이터 생산

제너레이터를 사용하면 이론적으로 무한한 데이터를 계속해서 만들어낼 수도 있습니다.

예를 들어, 알파벳 `A`, `B`, `C`를 계속해서 무한하게 제공하는 함수를 작성해보겠습니다.

```py
def yield_infinite_abc():
  while True:
    yield "A"
    yield "B"
    yield "C"
```

이 함수를 호출한 결과를 `for` 루프로 돌리면 `A`, `B`, `C`가 화면에 끊임없이 출력이 될 것입니다. (`Ctrl + C`를 눌러서 빠져나올 수 있습니다.)

```py
for ch in yield_infinite_abc():
  print(ch)
A
B
C
A
B
C
A
B
C
A
^C
Traceback (most recent call last):
  File "main.py", line 8, in <module>
    print(ch)
KeyboardInterrupt
```

이렇게 데이터를 무한하게 제공하는 함수는 사실 `yield` 키워드가 없이는 작성하는 것이 거의 불가능에 가깝습니다. 컴퓨터의 물리적인 메모리에는 언제나 한계가 있으며 제 아무리 큰 리스트를 만들더라도 이 한계를 초과할 수는 없기 때문입니다.



## yield from

제너레이터를 반환하는 함수를 작성하다 보면 아래와 같이 리스트를 제너레이터로 변환해야 할 일이 자주 생깁니다.

```py
def yield_abc():
  for ch in ["A", "B", "C"]:
    yield ch
```

`yield from`를 사용하면 리스트를 바로 제너레이터로 변환할 수 있어서 매우 편리하니 한 번 꼭 사용해보세요.

```py
def yield_abc():
  yield from ["A", "B", "C"]
```



## Generator Comprehension

지금까지는 함수 안에서는 `yield` 키워드를 사용해서 제너레이터를 만들어내는 방법에 대해서 살펴보았는데요. 제너레이터를 만드는 또 다른 방법으로 제너레이터 표현식(generator comprehension/expression)이라는 것도 있습니다.

리스트 표현식(list comprehension)과 사용 방법이 매우 유사한데요. 단지 차이점이라고 하면 리스트 표현식은 대괄호를 사용하고 제너레이터 표현식은 소괄호를 사용한다는 것입니다.

```py
abc = (ch for ch in "ABC")

print(abc)

for ch in abc:
  print(ch)
<generator object <genexpr> at 0x7f2dab21ff90>
A
B
C
```

> list comprehension에 대한 좀 더 자세한 내용은 아래 관련 포스팅을 참고 바랍니다.
>
> - [파이썬 map 내장 함수 사용법 (feat. List Comprehension)](https://www.daleseo.com/python-map)
> - [파이썬 filter 내장 함수 사용법 (feat. List Comprehension)](https://www.daleseo.com/python-filter)



## 마치면서

디른 프로그래밍 언어에서 넘어오신 많은 개발자 분들께서 파이썬의 `yield` 키워드 때문에 당혹스러워하시는 것을 자주 봅니다. 사실 알고보면 별로 어려운 개념이 아니오니 이번 기회에 제너레이터에 대한 개념을 잘 잡으셔서 효과적으로 파이썬 코딩을 할 수 있으셨으면 좋겠습니다.