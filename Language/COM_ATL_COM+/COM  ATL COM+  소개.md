# COM  ATL COM+  소개



## COM

COM은 Component Object Model의 약자로, Windows 운영 체제에서 사용되는 소프트웨어 구성 요소 모델입니다. COM은 컴퓨터 프로그램 간에 서로 통신하고 상호 작용할 수 있도록 하는 표준화된 방법을 제공합니다.

COM은 다음과 같은 특징을 가지고 있습니다.

- **객체 지향:** COM은 객체 지향 프로그래밍을 기반으로 합니다. 객체는 서로 상호 작용할 수 있는 데이터와 코드의 집합입니다.
- **다중 스레드:** COM은 다중 스레드에서 작동할 수 있습니다. 이는 여러 프로그램이 동시에 COM 객체를 사용할 수 있음을 의미합니다.
- **재사용:** COM 객체는 다른 프로그램에서 재사용할 수 있습니다. 이는 개발자가 COM 객체를 사용하여 새로운 응용 프로그램을 쉽게 만들 수 있음을 의미합니다.

COM은 다음과 같은 용도로 사용될 수 있습니다.

- **응용 프로그램 간의 통신:** COM은 응용 프로그램 간에 데이터를 교환하고 작업을 수행하는 데 사용할 수 있습니다.
- **확장성:** COM은 응용 프로그램을 확장하고 새로운 기능을 추가하는 데 사용할 수 있습니다.
- **재사용:** COM은 응용 프로그램에서 재사용할 수 있는 컴포넌트로 구성됩니다.

COM은 Windows 운영 체제의 중요한 구성 요소입니다. Windows에서 작동하는 대부분의 응용 프로그램은 COM을 사용합니다.

**COM의 구성 요소**

COM은 다음과 같은 구성 요소로 구성됩니다.

- **클래스:** 클래스는 객체의 템플릿입니다. 클래스를 사용하여 객체를 만들 수 있습니다.
- **객체:** 객체는 클래스의 인스턴스입니다. 객체는 데이터와 코드의 집합입니다.
- **인터페이스:** 인터페이스는 객체가 서로 통신하는 데 사용하는 메서드와 속성의 집합입니다.
- **파생:** 파생은 기존 클래스를 확장하는 프로세스입니다. 파생을 사용하여 기존 클래스에 새로운 기능을 추가할 수 있습니다

**COM의 사용 방법**

COM을 사용하려면 다음 단계를 따르세요.

1. COM 클래스를 가져옵니다.
2. 클래스의 인스턴스를 만듭니다.
3. 인스턴스의 메서드와 속성을 사용하여 작업을 수행합니다.

다음은 COM을 사용하는 간단한 예입니다.

``` c++
#include <iostream>
#include <comdef.h>

class MyClass {
public:
  MyClass() {
    std::cout << "MyClass()가 호출되었습니다." << std::endl;
  }

  ~MyClass() {
    std::cout << "~MyClass()가 호출되었습니다." << std::endl;
  }

  void print() {
    std::cout << "Hello, world!" << std::endl;
  }
};

int main() {
  // COM 클래스를 가져옵니다.
  IClass* pClass = nullptr;
  CoCreateInstance(CLSID_MyClass, nullptr, CLSCTX_INPROC_SERVER, IID_IClass, (void**)&pClass);

  // 클래스의 인스턴스를 만듭니다.
  MyClass* pMyClass = static_cast<MyClass*>(pClass);

  // 인스턴스의 메서드를 호출합니다.
  pMyClass->print();

  // 인스턴스를 해제합니다.
  pClass->Release();

  return 0;
}

```

이 예제는 MyClass라는 COM 클래스를 사용합니다. MyClass 클래스는 print() 메서드를 제공합니다. main() 함수는 다음 작업을 수행합니다.

- CoCreateInstance() 함수를 사용하여 MyClass 클래스의 인스턴스를 만듭니다.
- pMyClass 포인터를 사용하여 인스턴스에 액세스합니다.
- pMyClass->print()를 호출하여 print() 메서드를 호출합니다.
- pClass->Release()를 호출하여 인스턴스를 해제합니다.

이 예제는 COM을 사용하여 간단한 메시지를 출력하는 방법을 보여줍니다. COM을 사용하여 더 복잡한 작업을 수행할 수도 있습니다.

**COM의 장점**

COM은 다음과 같은 장점이 있습니다.

- **객체 지향:** COM은 객체 지향 프로그래밍을 기반으로





## ATL

