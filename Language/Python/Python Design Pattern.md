# Python Design Pattern

출처: https://www.javatpoint.com/python-design-pattern

이 튜토리얼에서는 디자인 패턴이 무엇인지, Python 프로그래밍 언어를 사용하여 구현하는 방법에 대해 설명합니다. 또한 여러 유형의 디자인 패턴에 대해서도 설명합니다. Python 문제를 해결하기 위한 다양한 접근 방식을 살펴보겠습니다. Python은 고급 동적 유형 언어이며 거의 모든 기술 영역에서 널리 사용됩니다.

## 1. What is Design Pattern?

디자인 패턴은 일반적으로 발생하는 소프트웨어 설계를 해결하기 위해 개발자가 사용하는 기술입니다. 간단히 말해서 코드에서 반복되는 문제를 해결하기 위해 미리 정의된 패턴입니다. 이러한 패턴은 주로 요구사항 분석을 기반으로 설계됩니다.

디자인 패턴은 소프트웨어 개발의 일부입니다. 이는 소프트웨어 개발의 잠재적인 문제에 대한 일반적이고 반복 가능한 솔루션입니다. 패턴 세부 사항을 따르고 코드에 적합한 솔루션을 적용할 수 있습니다.

패턴과 알고리즘을 혼동하는 경우가 많지만 둘 다 반복되는 문제를 해결하기 위한 별도의 접근 방식입니다. 알고리즘은 일반적으로 패턴이 솔루션에 대한 상위 수준 설명인 일부 문제에서 구현할 수 있는 명확한 솔루션 세트를 정의합니다.

예를 들어, 알고리즘은 요리 레시피와 같습니다. 무언가(문제 또는 목표)를 요리하기 위한 명확한 재료 세트(또는 솔루션 세트)가 있습니다. 반면에 패턴은 청사진과 같습니다. 결과와 기능이 무엇인지 확인할 수 있지만 구현 순서를 수정할 수 있습니다.

## 2. Configuration of Design Pattern

아래 다이어그램에서는 디자인 패턴 문서의 기본 구조를 설명합니다. 문제를 해결하기 위해 우리가 어떤 기술을, 어떤 방식으로 사용하고 있는지에 초점이 맞춰져 있습니다.

![Python Design Pattern](https://static.javatpoint.com/tutorial/python-design-pattern/images/python-design-pattern1.png)

위의 다이어그램에서 -

- **Pattern Name -** 패턴을 짧고 효과적으로 정의하는데 사용됩니다.
- **Intent/ Motive -** 이는 목표 또는 패턴의 기능을 정의합니다.
- **Applicability -** 패턴을 적용할 수 있는 모든 가능한 영역을 정의합니다.
- **Participants and Consequences -** 디자인 패턴에 사용되는 클래스와 객체, 패턴과 함께 존재하는 결과 목록으로 구성됩니다.



## 3. History of Patterns

디자인 패턴은 객체 지향 디자인의 일반적인 문제에 대한 솔루션 집합입니다. 다양한 프로젝트에서 문제의 해결 방법이 계속해서 반복되면 결국 누군가 이름을 붙이고 해결 방법을 자세히 정의하게 됩니다. 이것이 패턴이 인식되는 방식입니다.

크리스토퍼 알렉산더(Christopher Alexander)는 A Pattern Language: Towns, Building, and Construction이라는 책에서 처음으로 패턴의 개념을 설명했습니다.

이 책은 도시환경을 디자인하기 위한 '언어'를 정의한다. 언어는 패턴일 뿐이다.

네 명의 저자인 Erich Gamma, John, Vlissiders, Ralph Johnson, Richard Helm이 패턴 언어의 아이디어를 선택했습니다. 나중에 그들은 Design Patterns: Elements of Reusable Object-Oriented Software라는 책을 출판했습니다. 이 책에는 프로그래밍 언어를 활용한 디자인 패턴의 개념이 담겨 있습니다.

이 책은 객체 지향 설계의 23가지 유용한 다양한 문제를 다루었습니다. 이 책은 프로그래머들 사이에서 큰 인기를 얻었고 매우 빠르게 베스트셀러 책이 되었습니다.

흥미로운 사실 - 이 책은 이름이 너무 길어서 사람들이 'The book of Gang of Four'라고 부르기 시작했고 곧 간단히 'The GoF book'으로 요약되었습니다.

이 책 이후에 다른 많은 객체 지향 패턴이 발견되었습니다. 곧 패턴 접근 방식은 프로그래밍 분야에서 매우 유명해졌습니다. 객체 지향 디자인 외에도 사용할 수 있는 다른 패턴도 많이 있습니다.



## 4. 디자인 패턴 활용의 장점

디자인 패턴을 사용하면 다음과 같은 이점이 있습니다.

- 모든 디자인 패턴은 언어 중립적입니다.
- 패턴은 프로그래머에게 특정 문제에 대해 검증된 솔루션을 선택할 수 있는 기회를 제공합니다.
- 프로젝트에 대한 기술적 위험을 줄이기 위해 실행 기록으로 구성됩니다.
- 패턴은 사용하기 쉽고 매우 유연합니다.



## 5. Python의 디자인 패턴

우리는 모두 Python의 기능에 익숙합니다. 그렇지 않다면 간단히 소개하겠습니다. Python은 고급 오픈 소스 동적 유형 언어입니다. 영어와 유사한 구문을 갖고 있어 배우기 쉽습니다. 다양한 디자인을 지원하는 수많은 라이브러리를 제공합니다.

Python에서 지원하는 디자인 패턴은 아래에 나열되어 있습니다. 튜토리얼에서는 이러한 디자인 패턴을 사용할 것입니다.

- Model View Controller Pattern
- Flyweight Pattern
- Factory pattern
- Singleton pattern
- Object Oriented Pattern
- Strategy Pattern
- Command Pattern
- Chain of Responsibility Pattern
- Abstract Factory Pattern
- Proxy Pattern
- Facade Pattern
- Observer Pattern
- Prototype Pattern
- Template Pattern
- Adapter Pattern
- Builder Pattern
- Prototype Pattern
- Decorator Pattern
- State Pattern



## 6. 디자인 패턴 학습 의 중요성

많은 소프트웨어 개발자는 Singleton 패턴을 모르고 수년 동안 작업할 수 있습니다. 우리가 모르는 사이에 패턴을 구현하는 경우도 발생할 수 있습니다. 그렇다면 여기서 질문이 생깁니다. 왜 디자인 패턴을 배워야 할까요? 개발에서 디자인 패턴의 중요성을 조명하는 다음 사항을 살펴보겠습니다.

- 디자인 패턴에는 소프트웨어를 개발하는 동안 직면하는 일반적인 문제에 대해 사전 정의된 시도 및 테스트된 솔루션 세트가 있습니다. 디자인 패턴을 알고 있다면 시간 낭비 없이 솔루션을 적용할 수 있습니다. 또한 객체 지향 설계의 원리를 사용하여 문제를 해결하는 방법을 알려줍니다.
- 디자인 패턴은 또한 개발자와 팀원 간의 공통 이해를 향상시킵니다. 코드에 문제가 있다고 가정해 보겠습니다. '그건 싱글톤을 사용하세요'라고 말하면 디자인 패턴과 이름을 알면 누구나 이해할 수 있습니다.
- 디자인 패턴은 우리가 무시했을 수도 있는 일반적인 문제를 소개하므로 학습 목적에도 유용합니다. 또한 실습 경험이 없었을 수도 있는 영역에 대해 생각해 볼 수도 있습니다.

우리는 다가오는 튜토리얼에서 디자인 패턴에 대해 더 자세히 알아볼 것입니다.



## 7. Singleton Design Pattern in Python

싱글톤 디자인 패턴은 Credential Design Pattern 중 하나로 구현이 가장 쉽습니다. 이름 자체가 설명하듯이 이는 특정 유형의 하나의 객체를 제공하는 방법입니다. 객체에 대한 단일 전역 액세스 지점을 제공하면서 클래스의 단일 인스턴스 형성을 설명하는 데 사용됩니다.

![Singleton Design Pattern in Python](https://static.javatpoint.com/tutorial/python-design-pattern/images/singleton-design-pattern-in-python.png)

단일 클래스의 여러 객체 생성을 방지합니다. 새로 생성된 개체는 응용 프로그램에서 전역적으로 공유됩니다.

데이터 연결의 간단한 예를 통해 이를 이해할 수 있습니다. 데이터베이스 연결을 설정하는 동안 데이터베이스 작업을 위한 독점 데이터베이스 연결 개체를 생성합니다. 해당 단일 연결 개체를 사용하여 데이터베이스에 관한 모든 작업을 수행할 수 있습니다. 이 프로세스를 단일 디자인 패턴이라고 합니다.



### Motivation

싱글톤 디자인 패턴은 특히 상호 리소스에 대한 액세스에 대한 메커니즘이 필요한 애플리케이션 유형에 사용됩니다. 앞서 논의한 것처럼 단일 클래스를 사용하여 단일 인스턴스를 정의할 수 있습니다.

싱글톤 패턴 사용의 가장 큰 이점 중 하나는 공유 리소스를 제한하고 무결성을 유지할 수 있다는 것입니다. 또한 위험하거나 무능한 코드를 초래하는 다른 클래스가 현재 코드를 덮어쓰는 것을 방지합니다. 동일한 지점에서 덮어쓸 수 있다는 걱정 없이 프로그램의 여러 지점에서 동일한 개체를 호출할 수 있습니다.



### Implementation

싱글톤 패턴을 구현하기 위해 정적 메소드를 사용합니다. 공유 리소스를 반환할 수 있는 getInstance() 메서드를 만듭니다. 정적 메서드를 호출하면 고유한 싱글톤 개체가 제공되거나 인스턴스화된 개체의 존재를 선택하는 오류가 발생합니다.

정의된 클래스의 여러 객체를 생성하고 무결성을 유지하는 것으로 제한됩니다.

간단한 비유의 예를 들 수 있습니다. 즉, 카운티에는 국가 운영을 통제하고 액세스하는 단일 중앙 정부가 있습니다. 특정 기간에는 누구도 다른 정부를 만들 수 없습니다.

싱글톤 패턴을 사용하여 이 비유를 구현할 수 있습니다.

**Example -**

``` py
class GovtSingleton:  
   __instance__ = None  
  
   def __init__(self):  
       # This is a Constructor  
      
       if GovtSingleton.__instance__ is None:  
           GovtSingleton.__instance__ = self  
       else:  
           raise Exception("We can not creat another class")  
  
   @staticmethod  
   def get_instance():  
       # We define the static method to fetch instance  
       if not GovtSingleton.__instance__:  
           GovtSingleton()  
       return GovtSingleton.__instance__  
  
# Creating an object of above defined class.  
gover = GovtSingleton()  
print(gover)  
  
same_gover = GovtSingleton.get_instance()  
print(same_gover)  
  
another_gover = GovtSingleton.get_instance()  
print(another_gover)  
  
new_gover = GovtSingleton()  
print(new_gover)  
```

**Output -**

```
<__main__.GovtSingleton object at 0x0000026FDDAC8160>
<__main__.GovtSingleton object at 0x0000026FDDAC8160>
<__main__.GovtSingleton object at 0x0000026FDDAC8160>
File "C:/Users/DEVANSH SHARMA/PycharmProjects/MyPythonProject/pillow_image.py", line 119, in __init__
    raise Exception("We cannot creat another class")
Exception: We cannot create another class
```

**Explanation -**

위 코드에서는 객체를 인스턴스화하여 변수에 저장했습니다. 우리는 또한 다른 기존 클래스가 있는지 확인하는 구성을 정의했습니다. 그렇지 않으면 예외가 발생합니다. 그런 다음 기존 인스턴스를 반환하는 get_instance()라는 정적 메서드를 정의했습니다. 사용할 수 없는 경우 만들고 반환합니다.

스크립트를 실행하면 하나의 GovInstance 개체가 메모리의 단일 지점에 저장됩니다. 다른 객체를 생성하면 예외가 발생합니다.

![Singleton Design Pattern in Python](https://static.javatpoint.com/tutorial/python-design-pattern/images/singleton-design-pattern-in-python2.png)

방법 - 2: 이중 검사 잠금 싱글톤 디자인 패턴

개체가 None과 동일하지 않기 때문에 스레딩 동기화는 더 이상 사용되지 않습니다. 다음 예를 이해해 봅시다.

**Example -**

``` py
# Double Checked Locking singleton pattern   
import threading   
class Single_Checked(object):   
  
   # resources shared by each and every   
   # instance   
  
   __single_acq_lock = threading.Lock()   
   __singleton_instance = None  
  
   # define the classmethod   
   @classmethod  
   def instance(cls):   
  
      # check for the singleton instance   
      if not cls.__singleton_instance:   
         with cls.__single_acq_lock:   
            if not cls.__singleton_instance:   
               cls.__singleton_instance = cls()   
  
      # return the singleton instance   
      return cls.__singleton_instance   
  
# main method   
if __name__ == '__main__':   
  
   # create class A   
   class A(Single_Checked):   
      pass  
  
   # create class B  
   class B(Single_Checked):   
      pass  
  
   X1, X2 = A.instance(), A.instance()   
   Y1, Y2 = B.instance(), B.instance()   
  
   assert X1 is not Y1   
   assert X1 is X2   
   assert Y1 is Y2   
  
   print('X1 : ', X1)   
   print('X2 : ', X2)   
   print('Y1 : ', Y1)   
   print('Y2 : ', Y2)  
```

**Output -**

```
X1 :  <__main__.A object at 0x00000262466480D0>
X2 :  <__main__.A object at 0x00000262466480D0>
Y1 :  <__main__.B object at 0x00000262465C2430>
Y2 :  <__main__.B object at 0x00000262465C2430>
```



### 디자인 패턴의 적용 가능성

싱글톤 디자인 패턴의 적용 가능성을 다음과 같이 정의합니다.

- 전역 변수에 대한 확실한 제어가 필요한 프로젝트에서는 싱글턴 방법을 사용해야 합니다.
- 싱글톤 패턴은 로깅, 캐싱, 스레드 풀, 구성 설정 등 가장 많이 발생하는 문제를 해결하며 종종 Factory 디자인 패턴과 함께 사용됩니다.





## 8. Factory Design Pattern

팩토리 디자인 패턴은 인스턴스화할 객체를 생성하기 위해 인터페이스나 클래스를 사용할 수 있는 생성 디자인 패턴 유형 중 하나입니다. 팩토리 메소드는 객체를 생성하는 가장 좋은 방법을 제공합니다. 이 방법에서는 클라이언트에 논리를 공개하지 않고 개체가 생성됩니다. 새로운 유형의 객체를 생성하기 위해 클라이언트는 동일한 공통 인터페이스를 사용합니다.



### Problem

우리가 나라의 다른 지역에 있는 책을 제공하는 웹사이트를 만들 계획을 가지고 있다고 가정해 봅시다. 웹사이트의 초기 버전은 책 주문만 받았지만, 시간이 지날수록 우리 웹사이트가 인기를 얻게 되면서 의류, 신발 등 판매 품목을 더 이상 추가하지 않을수 없게 되었습니다. 매우 좋은 생각이지만 소프트웨어 개발자는 어떻습니까? 이제 코드의 대부분이 책의 클래스와 관련되어 있기 때문에 전체 코드를 변경해야 하고, 개발자는 전체 코드베이스를 변경해야 합니다. 지저분한 코드로 이어질 수 있습니다.

다른 예를 통해 이 개념을 이해해 보겠습니다.

**Example -**

``` py
# Python Code for Object   
# Oriented Concepts without   
# using Factory method   
  
class FrenchLocalizer:   
  
    """ it simply returns the french version """  
  
    def __init__(self):   
  
        self.translations = {"car": "voiture", "bike": "bicyclette",   
                            "cycle":"cyclette"}   
  
    def localize(self, message):   
  
        """change the message using translations"""  
        return self.translations.get(msg, msg)   
  
class SpanishLocalizer:   
    """it simply returns the spanish version"""  
  
    def __init__(self):   
  
        self.translations = {"car": "coche", "bike": "bicicleta",   
                            "cycle":"ciclo"}   
  
    def localize(self, msg):   
  
        """change the message using translations"""  
        return self.translations.get(msg, msg)   
  
class EnglishLocalizer:   
    """Simply return the same message"""  
  
    def localize(self, msg):   
        return msg   
  
if __name__ == "__main__":   
  
    # main method to call others   
    f = FrenchLocalizer()   
    e = EnglishLocalizer()   
    s = SpanishLocalizer()   
  
    # list of strings   
    message = ["car", "bike", "cycle"]   
  
    for msg in message:   
        print(f.localize(msg))   
        print(e.localize(msg))   
        print(s.localize(msg))  
```

이러한 유형의 문제를 어떻게 해결할 수 있는지 이해해 봅시다.



### Solution

솔루션에서는 간단한 객체 구성을 사용하는 대신 특수 팩토리 메서드를 사용하여 구성 객체를 호출합니다. 객체를 생성하는 두 가지 방법은 매우 유사하지만 팩토리 메서드 내에서 호출됩니다.

예를 들어 책, 모바일, 옷, 액세서리 클래스와 같은 판매 품목에는 구매 메소드를 선언하는 구매 인터페이스가 포함되어야 합니다. 각 클래스는 이러한 메서드를 고유하게 구현합니다.

좀 더 구체적으로 이해하기 위해 다음 다이어그램을 살펴보겠습니다.

**Example -**

``` py
# Solution using the   
# factory design pattern  
  
class French_Language:   
  
   #it will return the french version  
  
   def __init__(self):   
  
      self.translations = {"book": "voiture", "phone": "biclothtte",   
                     "cloth":"clothtte"}   
  
   def localize(self, message):   
  
      """change the message using translations"""  
      return self.translations.get(msg, msg)   
  
class Spanish_Language:   
   #it will return the spanish version  
  
   def __init__(self):   
      self.translations = {"book": "libro", "phone": "teléfono",  
                     "cloth":"paño"}  
  
   def localize(self, msg):   
  
      #change the message using translations  
      return self.translations.get(msg, msg)   
  
class English_Language:   
   """Simply return the same message"""  
  
   def localize(self, msg):   
      return msg   
  
def Factory(language ="English"):   
  
   """Factory Method"""  
   localizers = {   
      "French": French_Language,   
      "English": English_Language,   
      "Spanish": Spanish_Language,   
   }   
  
   return localizers[language]()   
  
if __name__ == "__main__":   
  
   fr = Factory("French")   
   en = Factory("English")   
   sp = Factory("Spanish")   
  
   message = ["book", "phone", "cloth"]   
  
   for m in message:   
      print(fr.localize(m))   
      print(en.localize(m))   
      print(sp.localize(m))   
```

**Output -**

```
voiture
book
libro
biclothtte
phone
teléfono
clothtte
cloth
paño
```



### 팩토리 메소드 사용의 장점

팩토리 방식의 장점은 다음과 같습니다.

- 팩토리 메소드는 기존 클라이언트 코드를 배포하지 않고 새로운 유형의 제품을 추가하는 데 매우 유용합니다.
- 이는 제품과 생성자 클래스 및 개체 간의 긴밀한 결합을 방지합니다.



### 팩토리 메소드 사용의 단점

팩토리 메소드 사용의 단점은 다음과 같습니다.

- 많은 수의 작은 파일이 생성되거나 파일이 복잡해집니다.
- 클라이언트에는 특정 실제 제품 객체를 생성하기 위한 하위 클래스가 있을 수 있습니다.



### 적용 가능성

그 개념은 클라이언트 코드를 변경할 필요가 없는 다형성과 동일합니다. 예를 들어 직사각형, 정사각형, 원 등과 같은 다양한 모양을 그리고 싶다고 가정합니다. 사용자의 입력에 따라 팩토리 메소드를 사용하여 인스턴스를 생성할 수 있습니다.

택시 신청에서는 1륜차, 2륜차, 3륜차, 4륜차를 예약할 수 있습니다. 여기에서 사용자는 자신이 예약하고 싶은 모든 놀이기구를 예약할 수 있습니다. 팩토리 메소드의 도움으로 사용자 입력을 받는 인스턴스를 생성하는 데 도움이 되는 Booking이라는 클래스를 생성할 수 있습니다. 따라서 여기서 개발자는 새 기능을 추가하기 위해 전체 코드를 변경할 필요가 없습니다.

팩토리 메소드는 보존하기 어려운 복잡한 논리 코드를 제거합니다. 또한 기존 코드를 수정하면 미묘한 버그가 발생하고 동작이 변경될 수 있으므로 코드베이스를 변경하는 것도 방지됩니다.



### 팩토리 메소드를 사용하는 곳

먼저 팩토리 메소드를 구현할 수 있는 상황을 식별해야 합니다. 작업을 수행하기 위해 애플리케이션이 인터페이스(제품)에 의존하고 해당 정의된 인터페이스의 구체적인 구현이 여러 개 있는 경우에 사용할 수 있습니다.

팩토리 메서드를 사용하면 해결할 수 있는 문제가 많이 있습니다. 우리는 이 설명에 맞는 몇 가지 예를 정의합니다.



#### 복잡한 논리 코드 대체

일반적으로 코드는 일부 요구 사항 변경으로 인해 새 경로를 추가하기 때문에 유지 관리가 어려운 if/else/elif와 같은 논리로 구성됩니다.

팩토리 메소드를 사용하면 각 논리 경로의 본문을 공통 인터페이스를 통해 정의된 다양한 함수나 클래스에 넣을 수 있습니다. 개발자는 수정을 위한 콘크리트 이식을 제공할 수 있습니다.



#### 공통 인터페이스에서 유사한 기능 결합

이미지에 특정 필터를 적용해야 한다고 가정해 보겠습니다. 팩토리 메소드는 사용자 입력에 따라 특정 필터를 찾습니다. 팩토리 메소드는 실제 구현을 적용할 수 있습니다.



#### 동일한 기능의 여러 구현 지원

과학자 그룹은 위성 이미지를 한 좌표계에서 다른 좌표계로 변환해야 합니다. 그러나 시스템에는 다양한 수준의 변환을 수행하는 여러 알고리즘이 있습니다. 애플리케이션을 통해 사용자는 이상적인 알고리즘을 선택할 수 있습니다. 팩토리 메소드는 이 옵션을 기반으로 알고리즘을 확실하게 구현할 수 있습니다.



#### 관련 외부 시리즈 통합

비디오 스트리밍 애플리케이션은 여러 외부 서비스를 통합하려고 합니다. 이 응용 프로그램을 사용하면 사용자는 비디오의 출처를 알 수 있습니다. Factory 메서드는 사용자 기본 설정에 따라 올바른 통합을 생성합니다.



### 결론

팩토리 메소드(Factory Method)는 대중적이고 널리 사용되는 창조적인 디자인 패턴입니다. 이는 존재하는 인터페이스의 다양한 구체적인 구현에 사용될 수 있습니다. 팩토리 메소드는 유지 관리가 어려운 복잡한 논리 코드를 제거합니다. 또한 새로운 요구 사항을 지원하기 위해 기존 코드를 수정하는 것도 방지됩니다.



---

팩토리 메서드 패턴은 객체지향 디자인 패턴 중 하나로, 어떤 객체를 생성할지 결정하는 책임을 하위 클래스로 위임하는 패턴입니다.

팩토리 메서드 패턴은 다음과 같은 상황에서 사용될 수 있습니다.

- 객체를 생성하는 방법이 여러 가지일 때
- 객체의 구체적인 종류를 미리 알 수 없을 때
- 객체의 생성 방법을 확장해야 할 때

팩토리 메서드 패턴을 구현하려면 다음과 같은 단계를 거칩니다.

1. 객체를 생성하는 책임을 가지는 공장 클래스를 정의합니다.
2. 공장 클래스에는 객체를 생성하는 메서드를 정의합니다.
3. 객체를 생성하는 메서드는 하위 클래스에서 구현합니다.

다음은 팩토리 메서드 패턴을 구현한 예입니다.

``` java
// 객체를 생성하는 공장 클래스
class Factory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        } else {
            throw new IllegalArgumentException("Invalid product type: " + type);
        }
    }
}

// 객체를 생성하는 하위 클래스
class ProductA extends Product {

}

class ProductB extends Product {

}
```

이 예에서는 `Factory` 클래스가 객체를 생성하는 책임을 가집니다. `Factory` 클래스의 `createProduct()` 메서드는 객체를 생성하는 구체적인 방법을 `ProductA` 또는 `ProductB` 클래스에 위임합니다.

팩토리 메서드 패턴은 다음과 같은 장점이 있습니다.

- 객체를 생성하는 방법을 확장하기 쉽습니다.
- 객체의 구체적인 종류를 미리 알 필요가 없습니다.
- 객체의 생성 방법을 코드에서 분리하여 유지보수하기 쉽습니다.

그러나 팩토리 메서드 패턴은 다음과 같은 단점도 있습니다.

- 코드가 복잡해질 수 있습니다.
- 객체를 생성하는 메서드가 너무 많아질 수 있습니다.



## 9. 디자인 패턴의 분류

디자인 패턴은 크게 생성 디자인 패턴, 구조 디자인 패턴, 행동 디자인 패턴의 세 가지로 분류됩니다. 이는 세부 수준, 복잡성 및 설계 중인 전체 시스템에 대한 적용 범위에 따라 서로 다릅니다.

또한 패턴에는 관용어 패턴과 아키텍처 패턴이라는 두 가지 유형이 있습니다. 하지만 이 튜토리얼에서는 이에 대해 탐색하지 않을 것입니다.



### 생성 디자인 패턴

이름에서 알 수 있듯이 기존 코드의 유연성과 재사용성을 향상시키는 개체 또는 클래스 생성 메커니즘을 제공합니다. 의존성을 줄이고 클래스와의 상호 작용 방법을 제어하므로 복잡한 구성을 처리하지 않아도 됩니다. 아래는 다양한 생성 디자인 패턴입니다.

- **Abstract Factory-** 구체적인 유형을 지정하지 않고도 객체를 생성할 수 있습니다.
- **Builder -** 복잡한 객체를 생성하는 데 사용됩니다.
- **Factory Method -** 생성할 정확한 클래스를 지정하지 않고도 객체를 생성할 수 있습니다.
- **Prototype -**기존 개체에서 새 개체를 만드는 데 사용됩니다.
- **Singleton -**싱글톤 디자인 패턴은 객체의 인스턴스가 하나만 생성되도록 합니다.



### 구조 디자인 패턴

구조적 디자인 패턴은 주로 개체와 클래스를 더 큰 구조로 조립하여 이러한 구조가 유연하고 효율적이어야 함을 보장합니다. 코드의 가독성과 유지 관리성을 향상시키는 데 매우 중요합니다. 또한 기능이 적절하게 분리되고 캡슐화되었는지 확인합니다. 상호 의존적인 사물 간의 최소한의 인터페이스를 줄입니다.

- **Adapter -** 기존 클래스 중 하나를 인터페이스로 래핑하여 두 개의 호환되지 않는 클래스가 함께 작동하도록 제공합니다.
- **Composite -** 개체 그룹을 단일 개체로 래핑합니다.
- **Bridge -** 두 클래스가 독립적으로 달라질 수 있도록 추상화를 분리합니다.
- **Decorator -** 런타임에 개체 동작을 동적으로 확장합니다.
- **Facade -** 더 복잡한 기본 개체에 대한 간단한 인터페이스를 제공합니다.
- **Flyweight -** 복잡한 객체 모델의 비용을 줄입니다.
- **Proxy -**이는 비용을 절감하고 복잡성을 줄이며 액세스를 제어하기 위해 기본 개체에 대한 자리 표시자 인터페이스를 제공합니다.



### 동작 디자인 패턴

동작 디자인 패턴은 한 클래스가 다른 클래스와 통신하는 방식을 담당합니다.

- **Chain of Responsibility -** 이는 처리 개체 체인에 대한 명령을 나타냅니다.
- **Command -** 매개변수의 동작을 캡슐화하는 개체를 생성합니다.
- **Interpreter -** 특화된 언어를 구현합니다.
- **Iterator -** 기본 표현을 위반하지 않고 객체의 모든 요소에 순차적으로 액세스합니다.
- **Mediator -** 기존 메서드에 대한 자세한 지식을 가진 유일한 클래스가 됨으로써 클래스 간의 느슨한 결합을 제공합니다.
- **Memento -** 객체를 이전 상태로 복원합니다.
- **Observer -** 이를 통해 여러 관찰자 개체가 이벤트를 볼 수 있습니다.
- **State -** 내부 상태가 변경되면 객체의 동작을 수정할 수 있습니다.
- **Strategy -** 런타임 시 선택될 알고리즘 계열 중 하나를 제공합니다.
- **Template Method -** 이를 통해 하위 클래스가 구체적인 동작을 제공할 수 있습니다. 또한 알고리즘의 골격을 추상 클래스로 정의합니다.
- **Visitor -** 메소드의 계층 구조를 하나의 객체로 이동하여 객체 구조에서 알고리즘을 분리합니다.

다음 튜토리얼에서는 몇 가지 중요한 디자인 패턴을 배울 것입니다.



## 10. Abstract Factory pattern

추상 팩토리 패턴은 관련된 객체들의 모음을 생성하는 인터페이스를 정의하는 패턴입니다. 이 패턴을 사용하면 객체를 생성하는 책임을 팩토리 클래스에 위임하여 클라이언트 코드에서 객체의 구체적인 클래스를 알 필요가 없도록 할 수 있습니다.

추상 팩토리 패턴은 다음과 같은 장점이 있습니다.

- **클라이언트 코드의 유연성을 향상합니다.** 클라이언트 코드에서 객체의 구체적인 클래스를 알 필요가 없기 때문에, 객체의 구체적인 클래스가 변경되더라도 클라이언트 코드를 수정할 필요가 없습니다.
- **객체의 의존성을 낮춥니다.** 클라이언트 코드에서 객체의 구체적인 클래스를 알 필요가 없기 때문에, 객체 간의 의존성이 낮아집니다.

추상 팩토리 패턴의 예제로, 다음과 같은 프로그램을 생각해 볼 수 있습니다.

``` py
from abc import ABC, abstractmethod

class Product(ABC):
    @abstractmethod
    def do_something(self):
        pass

class ConcreteProduct1(Product):
    def do_something(self):
        print("I'm ConcreteProduct1")

class ConcreteProduct2(Product):
    def do_something(self):
        print("I'm ConcreteProduct2")

class AbstractFactory(ABC):
    @abstractmethod
    def create_product1(self):
        pass

    @abstractmethod
    def create_product2(self):
        pass

class ConcreteFactory1(AbstractFactory):
    def create_product1(self):
        return ConcreteProduct1()

    def create_product2(self):
        return ConcreteProduct2()

class ConcreteFactory2(AbstractFactory):
    def create_product1(self):
        return ConcreteProduct1()

    def create_product2(self):
        return ConcreteProduct2()

def main():
    factory = ConcreteFactory1()

    product1 = factory.create_product1()
    product2 = factory.create_product2()

    product1.do_something()
    product2.do_something()

if __name__ == "__main__":
    main()

```

위 프로그램에서는 `Product` 클래스를 추상 클래스로 정의하고, `ConcreteProduct1`, `ConcreteProduct2` 클래스를 구상 클래스로 정의하였습니다. `AbstractFactory` 클래스를 추상 팩토리 클래스로 정의하고, `ConcreteFactory1`, `ConcreteFactory2` 클래스를 구상 팩토리 클래스로 정의하였습니다.

`main()` 함수에서는 `ConcreteFactory1` 팩토리 클래스를 사용하여 `Product1`과 `Product2` 객체를 생성하였습니다. `Product1` 객체는 `ConcreteProduct1` 클래스의 인스턴스이고, `Product2` 객체는 `ConcreteProduct2` 클래스의 인스턴스입니다.

`Product1` 객체와 `Product2` 객체의 `do_something()` 메서드를 호출하면 다음과 같이 출력됩니다.