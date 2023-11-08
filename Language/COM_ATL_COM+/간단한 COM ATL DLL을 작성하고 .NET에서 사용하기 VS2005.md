# 간단한 COM ATL DLL을 작성하고 .NET에서 사용하기 VS2005

출처: https://www.codeproject.com/Articles/38254/Writing-Simple-COM-ATL-DLL-Using-it-with-dotnet



속성, 메서드, 이벤트와 같은 간단한 문제를 다루고 이를 .NET 애플리케이션에서 사용하는 기사

- [Download source - 37.58 KB](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/simpleatlcom.zip) 



## Introduction

포럼에서 토론하는 동안 COM/ATL DLL을 작성하고 싶지만 속성, 메서드를 생성하는 방법, 구성 요소에서 이벤트를 발생시키는 방법을 모르거나 이를 생성하기 위한 기본 지식을 얻으려고 노력하는 사람들을 많이 만났습니다. 그들을 위해 나는 이 글을 VC++ 6.0으로 작성했습니다. Visual Studio 6은 이제 지원이 중단되었기 때문에 많은 초보자들이 Visual Studio 2005/2008을 사용하여 동일한 기사를 요청했습니다. 둘 사이에는 GUI 차이가 많이 있기 때문입니다.

그래서 저는 Visual Studio 2005를 사용하여 동일한 기사를 발표하고 있으며 새로운 기사이기 때문에 독자들에게 새로운 것을 제공해야 합니다. 그렇지 않으면 독자들이 이 기사를 거부할 것입니다. .NET 애플리케이션이 애플리케이션의 Visual C++ 점유율을 서서히 잠식하고 있습니다. 따라서 대중적인 요구를 따르기 위해 관리되지 않는 응용 프로그램 대신 .NET 응용 프로그램에서 COM 구성 요소를 사용하는 방법을 보여 드리겠습니다. 초보 기사이므로 기사에 몇 가지 추가 스크린샷이 포함되어 있습니다. 같은 것을 사용하기 위해 불을 지르지 마십시오.



## Table of Contents

- Creating ATL Component
- Using it in Visual C# Application (.NET 2005)



## Creating ATL Component

Visual Studio 2005에서 ATL 구성 요소를 만드는 방법을 단계별로 살펴보겠습니다.



1. Visual Studio 2005를 열고 파일| 신규 | 새 프로젝트의 이름을 제공할 수 있는 다음 파일-저장 대화 상자를 표시하는 프로젝트 메뉴. 여기에서는 이름을 SimpleATLCom으로 지정하고 프로젝트를 생성할 대상 폴더를 선택하는 것이 좋습니다. 그렇지 않으면 문서 폴더가 복잡해집니다. 확인을 눌러 변경 사항을 확인하세요. 이렇게 하면 빈 ATL 구성 요소가 생성됩니다.

![Image 1](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg1.jpg)

Figure 1: File Open Dialog



이제 다음 대화 상자(ATL 프로젝트 마법사)에서 기본 설정을 선택하고 마침을 누릅니다. MFC 지원이 필요한 경우 MFC 지원 확인란을 선택하세요.


![Image 2](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg2.jpg)

Figure 2: ATL Project Wizard

이제 다음 단계에서 표시할 ATL 개체를 프로젝트에 삽입하세요.

2. 프로젝트 | Class 을 추가합니다. 다음 대화 상자가 나타납니다.

![Image 3](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg3.jpg)

Figure 3: Add Simple ATL Object

ATL|ATL Simple Object를 선택하고 ADD 버튼을 누릅니다.



3. ATL 단순 개체 마법사에 새 인터페이스 이름을 입력합니다. 인터페이스 이름을 입력하기 시작하면 Visual Studio 개발 환경에서 나머지 세부 정보가 자동으로 채워지는 것을 볼 수 있습니다. 또한 이벤트를 실행하는 구성요소 기능을 제공하는 옵션 탭의 연결 포인트 확인란을 선택하세요.


![Image 4](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg4.jpg)

Figure 4: ATL Simple Object

![Image 5](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg5.jpg)

Figure 5: Select Connection Point Check Box

4. 이제 다음 IDL(인터페이스 정의 언어)을 확인하면 생성되어 프로젝트에 포함됩니다.

``` IDL
1.    // SimpleATLcom.idl : IDL source for SimpleATLcom
2.    //
3.    // This file will be processed by the MIDL tool to
4.    // produce the type library (SimpleATLcom.tlb) and marshalling code.
5.
6.    import "oaidl.idl";
7.    import "ocidl.idl";
8.
9.    [
10.     object,
11.     uuid(8F4FEC76-F1F6-4D69-A2E8-FFF4F316C101),
12.     dual,
13.     nonextensible,
14.     helpstring("ISimpleCom Interface"),
15.     pointer_default(unique)
16.   ]
17.   interface ISimpleCom : IDispatch{
18.    };
19.    [
20.    uuid(2CA18A17-D157-4D1E-A2C7-3D69EBD87250),
21.    version(1.0),
22.    helpstring("SimpleATLcom 1.0 Type Library")
23.    ]
24.    Library SimpleATLcomLib
25.    {
26.      importlib("stdole2.tlb");
27.      [
28.        uuid(85D35B5B-8DBF-4562-96B0-66A4C4718DD5),
29.         helpstring("_ISimpleComEvents Interface")
30.      ]
31       dispinterface _ISimpleComEvents
32        {
33.         properties:
34.         methods:
35.      };
36.      [
37.        uuid(A7657FC5-D63C-49C3-AEE5-D8799F412C74),
38.        helpstring("SimpleCom Class")
39.      ]
40.       coclass SimpleCom
41.       {
42          [default] interface ISimpleCom;
43.         [default, source] dispinterface _ISimpleComEvents;
44.       };
45.    };
```

- At Line 11: 개발 환경에서 생성된 기본 UUID(고유 ID)이며, 이를 통해 구성요소를 고유하게 식별할 수 있습니다.
- At line 17: 이것이 구성 요소 이름과 UUID입니다. 해당 내용은 구성 요소를 고유하게 식별하는 데 사용될 수 있으며, 이 ID를 사용하여 구성 요소를 생성할 수 있습니다.
- At Line 31: 우리의 dis-interface, 즉 이벤트 기능.



5. 이제 속성 추가 및 메서드 추가 마법사를 사용하여 속성 및 메서드를 추가합니다. 이는 클래스 뷰에서 찾을 수 있습니다. ISimpleCom 인터페이스를 마우스 오른쪽 버튼으로 클릭한 다음 ADD- 메서드 추가 또는 속성 추가를 클릭합니다.


![Image 6](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg6.jpg)

Figure 6: Add Method/Add Property



다음과 같이 Long을 OUT, RETVAL 매개변수로 계산하는 메소드를 추가합니다.


![Image 7](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg7.jpg)

![image-20231106122824459](C:\Users\wonso\AppData\Roaming\Typora\typora-user-images\image-20231106122824459.png)

Figure 7: Add Method

예에 표시된 대로 Long 유형의 ComMark 속성을 추가합니다.

![Image 8](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg8.jpg)

Figure 8: Add Property

마찬가지로 속성 AtlMarks를 LONG을 추가하고 StudentName을 BSTR로 직접 추가하세요. 죄송합니다. 매개변수의 IN, OUT 및 RETVAL 유형에 대해 언급하는 것을 잊어버렸습니다.

- `Propget `– Component에서 값을 가져오는 속성을 나타냅니다.
- `PropPut `– Component에 속성을 부여하는 속성을 나타냅니다. 이는 선택 사항일 수 있으며 제거하면 속성이 읽기 전용이 될 수 있습니다.
- `Method `— 일부 계산을 수행하는 간단한 함수
- `[in]` - 데이터가 들어가고 있거나 구성 요소에 어떤 가치를 부여하고 있음을 의미합니다.
- `[out,retval]` - 표기법에 따르면 이를 사용하는 인수는 데이터와 함께 반환됩니다.
- `HRESULT` - 표준 오류 보고 변수



6. 이제 거의 동일한 방식으로 Event를 추가하고 인터페이스에 메소드를 추가합니다. 이제 _ISimpleComEvents를 마우스 오른쪽 버튼으로 클릭하고 추가 | 메소드를 추가합니다.


![Image 9](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg9.jpg)

Figure 9: Add Event



7. 모든 작업이 완료되면 수정된 IDL 파일은 다음과 같습니다.

``` idl
1.  interface ISimpleCom : IDispatch
2.  {
3.   [id(1), helpstring("method Calculate")] HRESULT Calculate([out,retval]
4.           LONG* a_lTotalMarks);
5.   [propget, id(2), helpstring("property ComMarks")] HRESULT ComMarks([out,
6.           retval] LONG* pVal);
7.   [propput, id(2), helpstring("property ComMarks")] HRESULT ComMarks([in]
8.            LONG newVal);
9.   [propget, id(3), helpstring("property AtlMarks")] HRESULT AtlMarks([out,
10.            retval] LONG* pVal);
11.  [propput, id(3), helpstring("property AtlMarks")] HRESULT AtlMarks([in]
12.            LONG newVal);
13.  [propget, id(4), helpstring("property StudentName")]
                HRESULT StudentName([out,
14.            retval] BSTR* pVal);
15.  [propput, id(4), helpstring("property StudentName")]
                HRESULT StudentName([in]
16.            BSTR newVal);
17.  };
18.
19.  dispinterface _ISimpleComEvents
20.  {
21.    properties:
22.    methods:
23.    [id(1), helpstring("method TotalMarks")] HRESULT TotalMarks([in] LONG
24.            a_lTotalMark);
25.  };
```

8. 이제 기본 인터페이스에 연결 지점을 추가합니다. 추가하려면 CSimpleCom을 마우스 오른쪽 버튼으로 클릭하고 ADD | 연결 포인트를 추가합니다. 다음 스크린샷이 나타납니다::

![Image 10](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg10.jpg)

Figure 10: Chose Add Connection Point

연결 지점을 구현하기 위해 _ISimpleComEvents 소스 인터페이스를 이동하는 버튼을 선택하고 완료를 클릭합니다. 이벤트를 발생시키는 데 사용할 수 있는 새로운 함수 Fire_TotalMarks가 CSimpleCom 클래스에 추가되었습니다.

![Image 11](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg11.jpg)

Figure 11: Add Connection Wizard

9. 이제 CSimpleCom 클래스에 있는 모든 속성과 함수를 코딩합니다. 즉, 외부 세계에서 들어오는 정보를 저장하기 위해 ComMarks, ATLMarks 및 StudentName에 대한 클래스 변수를 추가합니다. 코딩 후 Class는 다음과 같습니다.

C++

Shrink ▲  

```c++
1.  //// Declare following private variable in Header File
2.  long m_lComMarks,m_lAtlMarks;
3.      CComBSTR m_bstName;
4.
5.  ////  Source File
6.  STDMETHODIMP CSimpleCom::Calculate(LONG* a_lTotalMarks)
7.  {
8.    long lTotalMarks = m_lAtlMarks + m_lComMarks;
9.    * a_lTotalMarks = lTotalMarks;
10.       Fire_TotalMarks(lTotalMarks); //Fire Event
11.       return S_OK;
12. }
13. /// Return Com marks
14. STDMETHODIMP CSimpleCom::get_ComMarks(LONG* pVal)
15. {
16.     * pVal = m_lComMarks;
17.     return S_OK;
18.     }
19. // Store Com Marks in Local Storage
20. STDMETHODIMP CSimpleCom::put_ComMarks(LONG newVal)
21. {
22.     m_lComMarks = newVal;
23.     return S_OK;
24. }
25. // return ATL Marks
26. STDMETHODIMP CSimpleCom::get_AtlMarks(LONG* pVal)
27. {
28.  * pVal = m_lAtlMarks;
29.  return S_OK;
30.  }
31. //Store ATL marks in local storage
32. STDMETHODIMP CSimpleCom::put_AtlMarks(LONG newVal)
33. {
34.  m_lAtlMarks = newVal;
35.  return S_OK;
36. }
37.  //return Student Name
38. STDMETHODIMP CSimpleCom::get_StudentName(BSTR* pVal)
39. {
40.  *pVal = m_bstName.Copy();
41.  return S_OK;
42. }
43. //Store Student name in local Storage.
44. STDMETHODIMP CSimpleCom::put_StudentName(BSTR newVal)
45. {
46.  m_bstName = newVal;
47.  return S_OK;
48. }
```

이제 솔루션을 빌드하면 빌드 프로세스가 끝나면 DLL이 자동으로 등록됩니다.

## Using it in Visual C# Application

이제 다시 단계별로 진행하여 C#.NET에서 프로젝트를 만들고 프로젝트에 COM 구성 요소 지원을 포함합니다.

1. 동일한 솔루션에 새 C# Window 애플리케이션 프로젝트(SimpleATLcomTest)를 추가하고 기본 구성 수락을 클릭하여 테스트 프로젝트를 만듭니다.

    ![Image 12](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg12.jpg)

   Figure 12: Add a New C# Project

2. 아래 스크린샷과 같이 인터페이스를 디자인하십시오. 구성 요소에 값을 입력하기 위한 3개의 텍스트 상자와 계산된 값을 포함한 모든 값을 검색하기 위한 4개의 상자를 추가합니다.

   
   ![Image 13](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg13.jpg)

   Figure 13: Test Application Design

3. 이제 프로젝트 이름을 마우스 오른쪽 버튼으로 클릭하고 참조 추가 메뉴 항목을 클릭하여 SimpleATLCom.dll에 대한 참조를 프로젝트에 추가하세요.


   ![Image 14](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg14.jpg)

   Figure 14: Add Reference Menu

   COM 탭 항목을 클릭하고 SimpleATLCom.dll을 선택하여 구성 요소 참조를 프로젝트에 추가합니다.


   ![Image 15](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg15.jpg)

   Figure 15: Add Reference

   

4. 이제 프로젝트에 구성요소를 사용하려면 다음 코드를 작성하세요.

   ``` c#
   1.  // Include SimpleAtlComLib
   2.  using SimpleATLcomLib;
   3.  namespace SimpleAtlComTest
   4.  {
   5.  public partial class SimpleATLCom : Form
   6.  {
   7.  // Create Object of Main Component Interface
   8.  ISimpleCom objSimpleCom = new SimpleComClass();
   9.  // Also declare Event variable
   10. _ISimpleComEvents_Event event1 = null;
   11. public SimpleATLCom()
   12. {
   13. InitializeComponent();
   14. // Initialize event1 variable with object of simplecom
   15. event1 = (_ISimpleComEvents_Event)objSimpleCom;
   16.
   17. // Add Event to listen event raised by Component
   18. event1.TotalMarks += new _ISimpleComEvents_TotalMarksEventHandler
       (ISimpleCom_TotalMarksEvent);
   19.
   20. }
   21. // this function will put the values back to Component
   22. private void btnPutValues_Click(object sender, EventArgs e)
   23. {
   24. objSimpleCom.AtlMarks = int.Parse(txtPATLMarks.Text);
   25. objSimpleCom.ComMarks = int.Parse(txtPComMarks.Text);
   26. objSimpleCom.StudentName = txtPName.Text;
   27. }
   28. // this function will bring values stored into Component
   29. // and populate into TextBox present on Form
   30. private void btnGetDetails_Click(object sender, EventArgs e)
   31. {
   32. txtGAtlMarks.Text = objSimpleCom.AtlMarks.ToString();
   33. txtGComMarks.Text = objSimpleCom.ComMarks.ToString();
   34. txtGName.Text = objSimpleCom.StudentName;
   35. objSimpleCom.Calculate();
   36. }
   37. // this Event will called, when invoke method Calculate
   // of simpleATLCom.
   38. void ISimpleCom_TotalMarksEvent(int lTotalMarks)
   39. {
   40. txtTotalMarks.Text = lTotalMarks.ToString();
   41. MessageBox.Show("Total Marks is " + txtTotalMarks.Text);
   42. }
   43. }
   44. }
   ```

   

5. 테스트 애플리케이션을 실행하면 결과가 표시됩니다.

   

   ![Image 16](https://www.codeproject.com/KB/atl/SimpleATLComWithDotNet/comimg17.jpg)

   Figure 16: Running Application



## About the Download Code

#### Source Code Includes

- *SimpleAtlCom.dll* (with source code)
- Test Project in Visual C#

## Using of Demo Application

원한다면 Com DLL과 테스트 애플리케이션을 먼저 사용하세요. Com DLL, 즉 SimpleAtlCom.dll을 컴퓨터에 등록하는 것을 잊지 마십시오. 이 명령줄을 사용하여 구성 요소를 등록할 수 있습니다.

``` cmd
Drive:> %sys%regsvr32 path_to_dll\SimpleAtlCom.dll
```



## Author Comment

I tried my level best to tell each and every simple aspect of com DLL. If it is missing something, feel free to contact me or leave your precious comments in the discussion forum below. 

## Special Thanks

- To my mother (Late) and father and off course my wife
- To *CodeProject.com, for* providing a platform for Programmer Interaction.
- To [Leeland Clay](http://www.codeproject.com/KB/Membership/View.aspx?mid=3308415) for correcting editing mistake(s) and providing suggestion(s) for improvement.

## History

- 18th July, 2009: Initial post

## License

This article, along with any associated source code and files, is licensed under [The Code Project Open License (CPOL)](http://www.codeproject.com/info/cpol10.aspx)





