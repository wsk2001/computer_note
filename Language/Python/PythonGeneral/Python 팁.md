# Python 팁



## 15 가지 Python 팁과 요령

출처: https://medium.com/@george.seif94/15-python-tips-and-tricks-so-you-dont-have-to-look-them-up-on-stack-overflow-90cec02705ae

Python에서 작업하는 방법을 잊을 때마다 Stack Overflow에서 검색하는 데 지치 셨나요? 나도! 
더 빠르게 코딩 할 수있는 15 가지 파이썬 팁과 트릭이 있습니다!

### (1) Swapping values

``` python
x, y = 1, 2
print(x, y)
x, y = y, x
print(x, y)
```



### (2) 문자열 목록을 하나의 문자열로 결합

```python
sentence_list = ["my", "name", "is", "George"]
sentence_string = " ".join(sentence_list)
print(sentence_string)
```



### (3) 문자열을 하위 문자열 목록으로 분할

```python
sentence_string = "my name is George"
sentence_string.split()
print(sentence_string)
```



### (4) 숫자로 채워진 목록 초기화

```python
[0]*1000 # List of 1000 zeros 
[8.2]*1000 # List of 1000 8.2's
```



### (5) 사전 병합

``` python
x = {'a': 1, 'b': 2}
y = {'b': 3, 'c': 4}
z = {**x, **y}
```



### (6) Reversing a string

``` python
name = "George"
name[::-1]
```



### (7) Returning multiple values from a function

``` python
def get_a_string():
    a = "George"
    b = "is"
    c = "cool"
    return a, b, c
sentence = get_a_string()
(a, b, c) = sentence
```



### (8) List 이해

``` python
a = [1, 2, 3]

# Create a new list by multiplying each element in a by 2
b = [num*2 for num in a] 
```



### (9) Iterating over a dictionary

``` python
m = {'a': 1, 'b': 2, 'c': 3, 'd': 4} 
for key, value in m.items():
    print('{0}: {1}'.format(key, value))
```



### (10) List 의 값과 index 를 반복적으로 가져 오기

```python
m = ['a', 'b', 'c', 'd']
for index, value in enumerate(m):
    print('{0}: {1}'.format(index, value))
```



### (11) 빈 컨테이너 초기화

``` python
a_list = list()
a_dict = dict()
a_map = map()
a_set = set()
```



### (12) 문자열 끝에서 쓸모없는 문자 제거

``` python
name = "  George "
name_2 = "George///"
name.strip() # prints "George"
name_2.strip("/") # prints "George"
```



### (13) 목록에서 가장 빈번한 요소 찾기

``` python
test = [1, 2, 3, 4, 2, 2, 3, 1, 4, 4, 4]
print(max(set(test), key = test.count))
```



### (14) 개체의 메모리 사용량 확인

``` python
import sys
x = 1
print(sys.getsizeof(x))
```



### (15) Convert a dict to XML

``` python
from xml.etree.ElementTree import Element
def dict_to_xml(tag, d):
    '''
    Turn a simple dict of key/value pairs into XML
    '''
    elem = Element(tag)
    for key, val in d.items():
        child = Element(key)
        child.text = str(val)
        elem.append(child)
    return elem
```



## Python 디버깅

출처: https://medium.com/analytics-vidhya/are-you-writing-print-statements-to-debug-your-python-code-690e6ba098e9

Python이 제공하는 강력한 무기 인 'pdb 모듈'을 사용하는 것 외에는 아무것도 할 필요가 없습니다. 이 모듈은 효과적으로 디버깅하는 데 도움이됩니다.

`debug_add.py`

```python
def add_num(listA,num):
    sum=[]
    for i in listA:
        sum.append(i*num)
    return sum

listA = [2, 4, 6, 8]
num=10
result=add_num(listA,num)
print(result)
```



### Pdb를 호출하는 몇 가지 방법 :

여기에서는 pdb를 호출하는 3 가지 방법을 살펴 봅니다.

**사후 분석** : 프로그램 수준에서 디버그하려면 이것을 사용합니다.

**인라인 pdb** : 3.7 이전 버전에서 작업하는 경우 사용합니다.

**breakpoint ()** : 버전 3.7 이상에서 사용



#### 사후 분석

아래와 같이 python 파일을 실행하면 pdb가 호출됩니다.

``` cmd
python -m pdb debug_add.py
```



이것은 pdb 모드로 들어가고 코드의 첫 번째 줄에서 중지됩니다.

``` cmd
(venv) C:\Users\PycharmProjects\>python -m pdb debug_add.py
> c:\users\pycharmprojects\debug_add.py(2)<module>()
-> def add_num(listA,num):
(Pdb)
```



디버거에 대한 도움이 필요하면 언제든지 모든 옵션을 나열하는‘h’(help)를 사용하십시오.

``` cmd
Pdb) h
Documented commands (type help <topic>):
========================================
EOF    c          d        h         list      q        rv       undisplay
a      cl         debug    help      ll        quit     s        unt
alias  clear      disable  ignore    longlist  r        source   until
args   commands   display  interact  n         restart  step     up
b      condition  down     j         next      return   tbreak   w
break  cont       enable   jump      p         retval   u        whatis
bt     continue   exit     l         pp        run      unalias  where
Miscellaneous help topics:
==========================
exec  pdb
```



#### 인라인 pdb

이전 버전의 Python 3.7에서는 pdb 모듈을 명시 적으로 가져오고 pdb.set_trace ()를 호출하여 프로그램을 중지하고 디버깅을 수행해야했습니다.

``` python
def add_num(listA,num):
    sum=[]
    for i in listA:
        sum.append(i*num)
    return sum

listA = [2, 4, 6, 8]
num=10
import pdb; pdb.set_trace()
result=add_num(listA,num)
print(result)
```

이것을 실행할 때 콘솔을 살펴 보겠습니다.

```cmd
> c:\users\pycharmprojects\debug_add.py(11)<module>()
-> result=add_num(listA,num)
(Pdb)
```

pdb.set_trace ()가 발견되어 디버그 모드로 들어가기 때문에 실행이 중지되었습니다. 이제 위의 사후 분석 방법에 표시된대로 여기에서 모든 디버깅 작업을 수행 할 수 있습니다.



#### breakpoint()

breakpoint () 위치는 버그가 의심되는 위치에  배치합니다. 여기 에서는 add_num 이전에 배치

``` python
def add_num(listA,num):
    sum=[]
    for i in listA:
        sum.append(i*num)
    return sum

listA = [2, 4, 6, 8]
num=10
breakpoint()
result=add_num(listA,num)
print(result)
```

Output:

```cmd
> c:\users\pycharmprojects\debug_add.py(11)<module>()
-> result=add_num(listA,num)
(Pdb) n
> c:\users\pycharmprojects\debug_add.py(12)<module>()
-> print(result)
(Pdb) n
[20, 40, 60, 80]
 — Return — 
> c:\users\prade\pycharmprojects\jobportal\debug_add.py(12)<module>()->None
-> print(result)
(Pdb)
```



### 여기에 사용 된 pdb의 옵션에 대한 요약 :

*n — move to next line/step over definitions*
*s — step into definitions (built-in / user defined)*
*u — to skip remaining iterations in a loop*
*c — continue execution or till the next breakpoint() is encountered*
*l — Shows the current line of code to be executed with arrow “->”*
*q — to quit the debugger*
*variable name — to examine the current state of the variable*
*h option name — displays help on the option provided*
*h — to view options menu and explore more options as needed.*

