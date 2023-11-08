# IDL 파일의 기본

출처: https://www.codeproject.com/Articles/19605/Basics-of-an-IDL-file



이 문서에서는 IDL 파일의 기본 사항을 설명합니다.



## Introduction

MIDL(Microsoft Interface Definition Language)은 COM 개체를 설명하는 데 사용됩니다. 인터페이스 및 유형 라이브러리 정의가 포함된 파일을 IDL 파일이라고 하며 파일 이름 확장명은 .idl입니다.

IDL(인터페이스 정의 언어)은 프로그래밍 언어가 아니라 객체에 의해 구현되는 인터페이스를 설명하는 설명 언어입니다. IDL 파일은 C++ 헤더 파일과 유사합니다.



## IDL 파일의 내용

모든 IDL 파일에는 모듈의 일부로 정의된 하나 이상의 인터페이스 정의가 있을 수 있습니다. 각 인터페이스 정의는 인터페이스 헤더와 인터페이스 본문으로 구성됩니다. IDL 인터페이스 헤더는 인터페이스 전체에 대한 정보를 지정합니다.

IDL 인터페이스 본문에는 원격 프로시저 호출에 사용되는 데이터 유형과 원격 프로시저에 대한 함수 프로토타입이 포함되어 있습니다. 인터페이스 본문에는 가져오기, pragma, 상수 선언 및 유형 선언도 포함될 수 있습니다. (각 인터페이스 본문에는 메소드, 속성, 변수/상수, 열거형 등이 포함될 수 있습니다. 즉, 인터페이스에서 노출되어야 하는 모든 것이 IDL 파일에 있습니다.)



## IDL 파일의 구조

``` idl
[ // header

  // 인터페이스 속성이 여기에 표시됩니다.

]
interface INTERFACENAME //body

{
// 인터페이스 본문이 여기에 들어갑니다.

}
library TyeLibraryFileName
{
  // 유형 라이브러리 정보

}
```



### 인터페이스 헤더 예

```
[
    object,
    uuid(C0E20128-DB19-4DB3-BCA1-24595E5E24A8),
    dual,
    nonextensible,
    helpstring("IConfig Interface"),
    pointer_default(unique)
]
```

iInterface 헤더에는 플랫폼 독립적인 속성이 포함되어 있습니다. 인터페이스 헤더의 속성은 전체 인터페이스에 전역적으로 적용됩니다. 이러한 속성은 인터페이스 정의 시작 부분에서 대괄호로 묶입니다. 각 속성에는 고유한 의미가 있습니다.

- `Object`: 이는 COM 인터페이스임을 나타냅니다.
- `Uuid`: 인터페이스에 할당되어 다른 인터페이스와 구별되는 UUID(Universally Unique Identifier)를 나타냅니다. 각 인터페이스, 클래스 및 형식 라이브러리는 고유한 식별자로 식별되어야 합니다.
- `Dual`: 이중 특성은 IDispatch 및 VTBL을 통해 속성과 메서드를 노출하는 인터페이스를 식별합니다.
- `Nonextensible`: 이는 IDispatch 구현이 인터페이스 설명에 나열된 속성과 메서드만 포함하고 런타임 시 추가 멤버로 확장될 수 없음을 지정합니다.
- `Helpstring`: 적용되는 요소를 설명하는 데 사용되는 문자열입니다.
- `Pointer_default`: 매개변수 목록에 나타나는 최상위 포인터를 제외한 모든 포인터에 대한 기본 포인터 속성을 지정합니다.



### 인터페이스 본문 예

```
interface ILogManager : IDispatch
{
    [id(1), helpstring("Initializes ILogManager")] HRESULT Initialize([in] IConfig* Config);

    [id(2), helpstring("Logs MemberClaims")] HRESULT LogMemberClaims([in] IMember* Member);

    [id(3), helpstring("Logs ILogData to specified destination")] 
            HRESULT Log([in] ILogData* LogData);

};
```

- `ILogManager` – 인터페이스 이름입니다.
- `IDispatch` – COM 기본 클래스.
- `Id(1)` – 메소드의 고유 ID입니다.
- `Helpstring` - 이 방법을 설명하는 문자열입니다.
- `HRESULT` – 오류 코드를 반환합니다.
- `Initialize` – 메소드 이름.
- `In` – 호출 프로시저에서 호출 프로시저로 전달될 매개변수를 지정합니다.
- `Out` - 호출자에게 다시 전달되는 데이터를 지정하는 매개변수입니다. 하나의 매개변수에 두 방향 특성을 모두 사용하면 해당 매개변수가 메서드에 데이터를 보내고 데이터를 호출자에게 다시 전달하는 데 모두 사용되도록 지정됩니다.



## Type library

형식 라이브러리는 구성 요소에 포함된 클래스, 인터페이스, 열거형 등에 대한 정보를 컴파일러에 제공하는 구성 요소의 필수 부분입니다. 유형 라이브러리 파일의 확장자는 .tlb입니다.



### 형식 라이브러리의 예

```
library MyTypeLibraryFileLib
{
 [
  uuid(B3F6C9C4-26AE-451B-9788-75F6C648DBF4),
  helpstring("LogManager Class")
 ]
 coclass LogManager
 {
  [default] interface ILogManager;
 }; 
}
```

library 문은 자체 uuid, helpstring 및 version 특성이 있는 MyTypeLibraryFileLib 형식 라이브러리를 정의합니다. coclass 문은 이전에 정의된 인터페이스인 ILogManager를 포함하는 완전히 새로운 구성 요소 클래스인 LogManager를 정의합니다.



### MIDL 컴파일러

MIDL 컴파일러는 IDL 파일을 처리하고 형식 라이브러리, 헤더 및 프록시 파일을 만듭니다.

형식 라이브러리는 COM 개체나 COM 인터페이스 또는 둘 다를 설명하는 이진 파일입니다.



### IDL 파일에서 TLB 파일 생성

IDL 파일을 저장한 다음 MIDL 컴파일러를 사용하여 TLB 파일을 생성합니다. 명령줄에서 다음을 입력합니다.

```
midl myfilectl.idl /tlb myfilectl.tlb
```

'/h' 스위치는 C/C++ 헤더 파일도 생성합니다.