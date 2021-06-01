# C++ 에서 권한 부여를위한 지원 작업

출처:  [여기-docs.microsoft.com](<https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/supporting-tasks-for-authorization-in-c-->)

다음 태스크는 [C++ 에서 권한 부여](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/using-authorization-in-c--) 사용에 나열된 기본 태스크를 지원합니다.



| Topic                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [Creating an Authorization Policy Store in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-an-authorization-policy-store-in-c--) | 응용 프로그램 설치 전 또는 설치 중에 권한 policy를 작성하기. |
| [Establishing a Client Context with Authorization Manager in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/establishing-a-client-context-with-authorization-manager-in-c--) | 토큰, 도메인 및 사용자 이름에 대한 핸들 또는 클라이언트의 보안 식별자 (SID)에 대한 문자열 표현을 사용하여 클라이언트 컨텍스트를 만들기. |
| [Qualifying Access with Business Logic in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/qualifying-access-with-business-logic-in-c--) | 액세스 확인을 위한 런타임 로직을 제공.                       |
| [Defining Permissions with ACLs in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/defining-permissions-with-acls-in-c--) | 해당 자원과 연관된 ACL을 작성 및 수정하고 클라이언트 권한을 활성화 및 비활성화하여 클라이언트가 어떤 자원에 액세스 할 수 있는지 정의. |
| [Establishing a Client Context from a SID in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/establishing-a-client-context-from-a-sid-in-c--) | 사용자, 그룹 또는 컴퓨터 계정을 식별합니다. SID를 사용하여 리소스에 대한 액세스 권한을 확인. |
| [Verifying Client Access with ACLs in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/verifying-client-access-with-acls-in-c--) | [보안 설명자](https://msdn.microsoft.com/library/windows/desktop/ms721625#-security-security-descriptor-gly)가 클라이언트에 허용하는 액세스 권한을 확인합니디. |
| [Finding the Owner of a File Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/finding-the-owner-of-a-file-object-in-c--) | 파일 소유자의 이름을 찾아서 인쇄합니다.                      |
| [Taking Object Ownership in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/taking-object-ownership-in-c--) | 해당 개체의 소유권을 가져 와서 파일 개체의 DACL을 변경합니다. |



### 1. [Creating an Authorization Policy Store in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-an-authorization-policy-store-in-c--)

**응용 프로그램 설치 전 또는 설치 중에 권한 policy를 작성하기.**

권한 부여 관리자 API를 사용하여 권한 부여 정책을 만들 때 다음 항목에서 제공되는 지침을 따르십시오.

| Topic                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [Creating an Authorization Policy Store Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-an-authorization-policy-store-object-in-c--) | 권한 부여 정책 저장소에는 응용 프로그램 또는 응용 프로그램 그룹의 보안 정책에 대한 정보가 들어 있습니다. 정보에는 응용 프로그램, 조작, 타스크, 사용자와  연관된 사용자 그룹이 포함됩니다. |
| [Creating an Application Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-an-application-object-in-c--) | 권한 policy 저장소에는 하나 이상의 응용 프로그램에 대한 권한 policy 정보가 들어 있습니다. 해당 정책 저장소를 사용하는 각 응용 프로그램에 대해 IAzApplication 개체를 만들어 정책 저장소에 저장해야합니다. |
| [Defining Operations in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/defining-operations-in-c--) | 권한 부여 관리자에서 작업은 응용 프로그램의 저급 기능 또는 메서드입니다. |
| [Grouping Operations into Tasks in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/grouping-operations-into-tasks-in-c--) | 권한 부여 관리자에서 작업은 응용 프로그램 사용자가 완료해야하는 고급 작업입니다. 작업은 하위 수준의 기능과 응용 프로그램의 메소드로 구성된 작업으로 구성됩니다. |
| [Grouping Tasks into Roles in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/grouping-tasks-into-roles-in-c--) | 권한 부여 관리자에서 역할은 사용자 범주 및 해당 사용자가 수행하도록 승인 된 작업을 나타냅니다. |
| [Defining Groups of Users in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/defining-groups-of-users-in-c--) | 권한 부여 관리자에서 IAzApplicationGroup 개체는 사용자 그룹을 나타냅니다. 그런 다음이 그룹의 사용자에게 역할을 할당 할 수 있습니다. |
| [Adding Users to an Application Group in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/adding-users-to-an-application-group-in-c--) | 권한 부여 관리자에서 응용 프로그램 그룹은 사용자 및 사용자 그룹의 그룹입니다. 응용 프로그램 그룹에는 다른 응용 프로그램 그룹이 포함될 수 있으므로 사용자 그룹은 중첩 될 수 있습니다. |



### 2. [Establishing a Client Context with Authorization Manager in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/establishing-a-client-context-with-authorization-manager-in-c--)

**토큰, 도메인 및 사용자 이름에 대한 핸들 또는 클라이언트의 보안 식별자 (SID)에 대한 문자열 표현을 사용하여 클라이언트 컨텍스트를 만들기.**

권한 부여 관리자에서 응용 프로그램은 클라이언트 컨텍스트를 나타내는 IAzClientContext 개체의 AccessCheck 메서드를 호출하여 클라이언트에 작업에 대한 액세스 권한이 부여되는지 여부를 결정합니다.

응용 프로그램은 토큰, 도메인 및 사용자 이름에 대한 핸들 또는 클라이언트의 보안 식별자 (SID)에 대한 문자열 표현을 사용하여 클라이언트 컨텍스트를 만들 수 있습니다.

IAzApplication 인터페이스의 InitializeClientContextFromToken, InitializeClientContextFromName 및 InitializeClientContextFromStringSid 메서드를 사용하여 클라이언트 컨텍스트를 만듭니다.

다음 예제에서는 클라이언트 토큰에서 IAzClientContext 개체를 만드는 방법을 보여줍니다. 이 예에서는 C 드라이브의 루트 디렉터리에 MyStore.xml이라는 기존 XML 정책 저장소가 있고이 저장소에 Expense라는 응용 프로그램이 있고 변수 hToken에 유효한 클라이언트 토큰이 들어 있다고 가정합니다.

```c++
#include <windows.h>

void ExpenseCheck(ULONGLONG hToken)
{
    IAzAuthorizationStore* pStore = NULL;
    IAzApplication* pApp = NULL;
    IAzClientContext* pClientContext = NULL;
    BSTR storeName = NULL;
    BSTR appName = NULL;
    HRESULT hr;
    void MyHandleError(char *s);
 
 //  함수 매개 변수에 널 (NULL) VARIANT를 작성하십시오.
    VARIANT myVar;
    VariantInit(&myVar);

    //  COM 초기화하십시오.
    hr = CoInitializeEx(NULL, COINIT_MULTITHREADED);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not initialize COM.");

    //  AzAuthorizationStore 개체를 만듭니다.
    hr = CoCreateInstance(
   /*"b2bcff59-a757-4b0b-a1bc-ea69981da69e"*/
         __uuidof(AzAuthorizationStore),
         NULL,
         CLSCTX_ALL,
   /*"edbd9ca9-9b82-4f6a-9e8b-98301e450f14"*/
         __uuidof(IAzAuthorizationStore),
         (void**)&pStore);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not create AzAuthorizationStore object.");

    //  정책 저장소의 문자열을 할당합니다..
    if(!(storeName = SysAllocString(L"msxml://c:\\MyStore.xml")))
        MyHandleError("Could not allocate string.");
    
    //  저장소를 초기화하십시오.
    hr = pStore->Initialize(0, storeName, myVar);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not initialize store.");

    //  응용 프로그램 개체를 만듭니다..
    if (!(appName = SysAllocString(L"Expense")))
        MyHandleError("Could not allocate application name string.");
    hr = pStore->OpenApplication(appName, myVar, &pApp);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not open application.");

    //  토큰 핸들에서 클라이언트 컨텍스트를 작성하십시오.
    hr = pApp->InitializeClientContextFromToken(hToken, myVar,
                &pClientContext);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not create client context.");

    //  필요에 따라 클라이언트 컨텍스트를 사용하십시오.

    //  자원을 정리하십시오.
    pStore->Release();
    pApp->Release();
    pClientContext->Release();
    SysFreeString(storeName);
    SysFreeString(appName);
    VariantClear(&myVar);
    CoUninitialize();
}

void MyHandleError(char *s)
{
    printf("An error occurred in running the program.\n");
    printf("%s\n",s);
    printf("Error number %x\n.",GetLastError());
    printf("Program terminating.\n");
    exit(1);
}
```





### 3. [Qualifying Access with Business Logic in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/qualifying-access-with-business-logic-in-c--)

**액세스 확인을 위한 런타임 로직을 제공.**

비즈니스 규칙 스크립트를 사용하여 액세스 점검을위한 런타임 로직을 제공하십시오. 비즈니스 규칙에 대한 자세한 내용은 비즈니스 규칙을 참조하십시오.

작업에 비즈니스 규칙을 할당하려면 먼저 해당 작업을 나타내는 IAzTask 개체의 BizRuleLanguage 속성을 설정하십시오. 스크립트는 Visual Basic Scripting Edition 또는 JScript에 있어야합니다. 스크립트 언어를 지정한 후에는 스크립트의 문자열 표현으로 IAzTask 객체의 BizRule 속성을 설정하십시오.

연관된 비즈니스 규칙이있는 타스크에 포함 된 조작에 대한 액세스를 점검 할 때 응용 프로그램은 IAzClientContext :: AccessCheck 메소드의 varParameterNames W varParameterValues 매개 변수로 전달 될 동일한 크기의 두 개의 h 열을 작성해야합니다. 클라이언트 컨텍스트 생성에 대한 자세한 내용은 [C++에서 권한 부여 관리자를 사용하여 클라이언트 컨텍스트 설정](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/establishing-a-client-context-with-authorization-manager-in-c--)을 참조하십시오.

IAzClientContext :: AccessCheck 메소드는 비즈니스 규칙 스크립트로 전달되는 AzBizRuleContext 오브젝트를 작성합니다. 그런 다음 스크립트는 AzBizRuleContext 개체의 BusinessRuleResult 속성을 설정합니다. TRUE 값은 액세스가 허용됨을 나타내고 FALSE 값은 액세스가 거부됨을 나타냅니다.

비즈니스 규칙 스크립트는 위임 된 IAzScope 오브젝트에 포함 된 IAzTask 오브젝트에 지정할 수 없습니다.

다음 예제에서는 비즈니스 규칙 스크립트를 사용하여 조작에 대한 클라이언트의 액세스를 확인하는 f}을 보여줍니다. 이 예에서는 C 드라이브의 루트 디렉터리에 MyStore.xml이라는 기존 XML 정책 저장소가 있으며 Expense라는 응용 프로그램, Submit Expense라는 작업 및 UseFormControl이라는 작업이 포함되어 있고 변수 hToken에 유효한 클라이언트 토큰

```c++
#include <windows.h>
#include <stdio.h>
#include <azroles.h>

void CheckAccess(ULONGLONG hToken)
//  Void CheckAccess().
{
    IAzAuthorizationStore* pStore = NULL;
    IAzApplication* pApp = NULL;
    IAzClientContext* pClientContext = NULL;
    IAzOperation* pOperation = NULL;
    IAzTask* pTask = NULL;
    BSTR storeName = NULL;
    BSTR appName = NULL;
    BSTR operationName = NULL;
    BSTR objectName = NULL;
    BSTR taskName = NULL;
    BSTR bizRule = NULL;
    BSTR bizRuleLanguage = NULL;
    LONG operationID;
    HRESULT hr;
    VARIANT varOperationIdArray;
    VARIANT varOperationId;
    VARIANT varResultsArray;
    VARIANT varResult;
    VARIANT varParamName;
    VARIANT varParamValue;
    VARIANT nameString;
    VARIANT expenseAmount;
    void MyHandleError(char *s);

    VARIANT myVar;
    VariantInit(&myVar);

    //  Initialize COM.
    hr = CoInitializeEx(NULL, COINIT_MULTITHREADED);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not initialize COM.");

    //  Create the AzAuthorizationStore object.
    hr = CoCreateInstance(
   /*"b2bcff59-a757-4b0b-a1bc-ea69981da69e"*/
         __uuidof(AzAuthorizationStore),
         NULL,
         CLSCTX_ALL,
   /*"edbd9ca9-9b82-4f6a-9e8b-98301e450f14"*/
         __uuidof(IAzAuthorizationStore),
         (void**)&pStore);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not create AzAuthorizationStore object.");

    //  Allocate a string for the policy store.
    if(!(storeName = SysAllocString(L"msxml://c:\\MyStore.xml")))
        MyHandleError("Could not allocate string.");
    
    //  Initialize the store.
    hr = pStore->Initialize(0, storeName, myVar);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not initialize store.");

    //  Create an application object.
    if (!(appName = SysAllocString(L"Expense")))
        MyHandleError("Could not allocate application name string.");
    hr = pStore->OpenApplication(appName, myVar, &pApp);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not open application.");

    //  Create a client context from a token handle.
    hr = pApp->InitializeClientContextFromToken(hToken, myVar,
          &pClientContext);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not create client context.");

    //  Create a business rule for the Submit Expense task.

    //  Open the Submit Expense task.
    if(!(taskName = SysAllocString(L"Submit Expense")))
        MyHandleError("Could not allocate task name string.");
    hr = pApp->OpenTask(taskName, myVar, &pTask);

    //  Assign a business rule to the task.

    //  Set the business rule language to VBScript.
    if(!(bizRuleLanguage = SysAllocString(L"VBScript")))
        MyHandleError("Could not allocate business rule language string.");
    hr = pTask->put_BizRuleLanguage(bizRuleLanguage);
    if(!(SUCCEEDED(hr)))
        MyHandleError("Could not allocate business rule language string.");

    //  Create a BSTR with the business rule code.
    if(!(bizRule = SysAllocString(
        L"Dim Amount \n"
        L"AzBizRuleContext.BusinessRuleResult = FALSE \n"
        L"Amount = AzBizRuleContext.GetParameter(\"ExpAmount\") \n"
        L"if Amount < 500 then AzBizRuleContext.BusinessRuleResult = TRUE"
        )))
         MyHandleError("Could not allocate business rule string.");

    
    hr = pTask->put_BizRule(bizRule);
    if(!(SUCCEEDED(hr)))
        MyHandleError("Could not assign business rule.");

    //  Save the new task data to the store.
    hr = pTask->Submit(0, myVar);
    if(!(SUCCEEDED(hr)))
        MyHandleError("Could not save task data.");

    //  Set up parameters for access check.

    //  Set up the object name.
    if (!(operationName = SysAllocString(L"UseFormControl")))
        MyHandleError("Could not allocate operation name string.");

    //  Get the ID of the operation to check.
    hr = pApp->OpenOperation(operationName, myVar, &pOperation);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not open operation.");

    hr = pOperation->get_OperationID(&operationID);
    if(!(SUCCEEDED(hr)))
        MyHandleError("Could not get operation ID.");

    //  Create a SAFEARRAY for the operation ID.
    varOperationIdArray.parray = SafeArrayCreateVector(VT_VARIANT, 0, 1);

    //  Create an array of indexes.
    LONG* index = new LONG[1];
    index[0] = 0;

    //  Populate a SAFEARRAY with the operation ID.
    varOperationId.vt = VT_I4;
    varOperationId.lVal = operationID;

    hr = SafeArrayPutElement(varOperationIdArray.parray, index,
          &varOperationId);
    if(!(SUCCEEDED(hr)))
        MyHandleError("Could not put operation ID in array.");
    
    //  Set SAFEARRAY type.
    varOperationIdArray.vt = VT_ARRAY | VT_VARIANT;

    //  Create business rule parameters.

    //  Create array of business rule parameter names.
    varParamName.parray = SafeArrayCreateVector(VT_VARIANT, 0, 1);
    varParamName.vt = VT_ARRAY | VT_VARIANT;
    nameString.vt = VT_BSTR;
    nameString.bstrVal = SysAllocString(L"ExpAmount");
    SafeArrayPutElement(varParamName.parray, index, &nameString);

    //  Create array of business rule parameter values.
    varParamValue.parray = SafeArrayCreateVector(VT_VARIANT, 0, 1);
    varParamValue.vt = VT_ARRAY | VT_VARIANT;
    expenseAmount.vt = VT_I4;
    expenseAmount.lVal = 100;  // access denied if 500 or more
    SafeArrayPutElement(varParamValue.parray, index, &expenseAmount);

    if(!(objectName = SysAllocString(L"UseFormControl")))//used for audit
        MyHandleError("Could not allocate object name string.");

    //  Check access.
    hr = pClientContext->AccessCheck(
        objectName,
        myVar,                  // use default application scope
        varOperationIdArray,
        varParamName,
        varParamValue,
        myVar,
        myVar,
        myVar,
        &varResultsArray);

    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not complete access check.");

    hr = SafeArrayGetElement(varResultsArray.parray, index, &varResult);
    if (!(SUCCEEDED(hr)))
        MyHandleError("Could not get result from array.");

    if (varResult.lVal == 0)
        printf("Access granted.\n");
    else
        printf("Access denied.\n");
    

    //  Clean up resources.
    pStore->Release();
    pApp->Release();
    pOperation->Release();
    pClientContext->Release();
    pTask->Release();
    SysFreeString(storeName);
    SysFreeString(appName);
    SysFreeString(operationName);
    SysFreeString(objectName);
    SysFreeString(taskName);
    SysFreeString(bizRule);
    SysFreeString(bizRuleLanguage);
    VariantClear(&myVar);
    VariantClear(&varOperationIdArray);
    VariantClear(&varOperationId);
    VariantClear(&varResultsArray);
    VariantClear(&varResult);
    VariantClear(&varParamName);
    VariantClear(&varParamValue);
    VariantClear(&nameString);
    VariantClear(&expenseAmount);
    CoUninitialize();
}

void MyHandleError(char *s)
{
    printf("An error occurred in running the program.\n");
    printf("%s\n",s);
    printf("Error number %x\n.",GetLastError());
    printf("Program terminating.\n");
    exit(1);
}
```



### 4. [Defining Permissions with ACLs in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/defining-permissions-with-acls-in-c--)

**해당 자원과 연관된 ACL을 작성 및 수정하고 클라이언트 권한을 활성화 및 비활성화하여 클라이언트가 어떤 자원에 액세스 할 수 있는지 정의.**

ACL을 사용하여 보호 된 자원에 대한 액세스를 제어 할 수 있습니다. 해당 자원과 연관된 ACL을 작성 및 수정하고 클라이언트 권한을 활성화 및 비활성화하여 클라이언트가 어떤 자원에 액세스 할 수 있는지 정의하십시오.

| Topic                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [Modifying the ACLs of an Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/modifying-the-acls-of-an-object-in-c--) | [액세스 제어 항목](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-control-entry-gly) (ACE)을 개체의 DACL ([임의 액세스 제어 목록](https://msdn.microsoft.com/library/windows/desktop/ms721573#-security-discretionary-access-control-list-gly))에 추가하거나 제거합니다. |
| [Creating a Security Descriptor for a New Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-a-security-descriptor-for-a-new-object-in-c--) | 새 개체에 대한 보안 설명자를 만듭니다.                       |
| [Controlling Child Object Creation in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/controlling-child-object-creation-in-c--) | 컨테이너 개체의 DACL을 사용하여 컨테이너 내에 자식 개체를 만들 수있는 사용자를 제어합니다. |
| [Enabling and Disabling Privileges in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/enabling-and-disabling-privileges-in-c--) | 프로세스가 시스템 수준의 작업을 수행하도록 허용 또는 금지합니다. |



#### 4.1 Modifying the ACLs of an Object in C++

다음 예에서는 ACE (액세스 제어 항목)를 개체의 DACL (임의 액세스 제어 목록)에 추가합니다.

AccessMode 매개 변수는 새 ACE 유형과 새 ACE가 지정된 트러스티에 대한 기존 ACE와 결합되는 방식을 결정합니다. AccessMode 매개 변수에 GRANT_ACCESS, SET_ACCESS, DENY_ACCESS 또는 REVOKE_ACCESS 플래그를 사용하십시오. 이러한 플래그에 대한 자세한 내용은 [ACCESS_MODE](https://msdn.microsoft.com/en-us/library/Aa374899(v=VS.85).aspx)를 참조하십시오.

비슷한 코드를 사용하여 시스템 액세스 제어 목록 (SACL)을 처리 할 수 있습니다. GetNamedSecurityInfo 및 SetNamedSecurityInfo 함수에서 SACL_SECURITY_INFORMATION을 지정하여 개체에 대한 SACL을 가져오고 설정합니다. AccessMode 매개 변수에 SET_AUDIT_SUCCESS, SET_AUDIT_FAILURE 및 REVOKE_ACCESS 플래그를 사용하십시오. 이러한 플래그에 대한 자세한 내용은 ACCESS_MODE를 참조하십시오.

이 코드를 사용하여 개체 별 ACE를 디렉터리 서비스 개체의 DACL에 추가합니다. 개체 별 ACE에서 GUID를 지정하려면 TrusteeForm 매개 변수를 TRUSTEE_IS_OBJECTS_AND_NAME 또는 TRUSTEE_IS_OBJECTS_AND_SID로 설정하고 pszTrustee 매개 변수를 OBJECTS_AND_NAME 또는 OBJECTS_AND_SID 구조체에 대한 포인터로 설정하십시오.

이 예제에서는 GetNamedSecurityInfo 함수를 사용하여 기존 DACL을 가져옵니다. 그런 다음 ACE에 대한 정보로 EXPLICIT_ACCESS 구조체를 채우고 SetEntriesInAcl 함수를 사용하여 새 ACE를 DACL의 기존 ACE와 병합합니다. 마지막으로이 예제에서는 SetNamedSecurityInfo 함수를 호출하여 새 DACL을 개체의 보안 설명자에 연결합니다.

```c++
#include <windows.h>
#include <stdio.h>

DWORD AddAceToObjectsSecurityDescriptor (
    LPTSTR pszObjName,          // name of object
    SE_OBJECT_TYPE ObjectType,  // type of object
    LPTSTR pszTrustee,          // trustee for new ACE
    TRUSTEE_FORM TrusteeForm,   // format of trustee structure
    DWORD dwAccessRights,       // access mask for new ACE
    ACCESS_MODE AccessMode,     // type of ACE
    DWORD dwInheritance         // inheritance flags for new ACE
) 
{
    DWORD dwRes = 0;
    PACL pOldDACL = NULL, pNewDACL = NULL;
    PSECURITY_DESCRIPTOR pSD = NULL;
    EXPLICIT_ACCESS ea;

    if (NULL == pszObjName) 
        return ERROR_INVALID_PARAMETER;

    // Get a pointer to the existing DACL.

    dwRes = GetNamedSecurityInfo(pszObjName, ObjectType, 
          DACL_SECURITY_INFORMATION,
          NULL, NULL, &pOldDACL, NULL, &pSD);
    if (ERROR_SUCCESS != dwRes) {
        printf( "GetNamedSecurityInfo Error %u\n", dwRes );
        goto Cleanup; 
    }  

    // Initialize an EXPLICIT_ACCESS structure for the new ACE. 

    ZeroMemory(&ea, sizeof(EXPLICIT_ACCESS));
    ea.grfAccessPermissions = dwAccessRights;
    ea.grfAccessMode = AccessMode;
    ea.grfInheritance= dwInheritance;
    ea.Trustee.TrusteeForm = TrusteeForm;
    ea.Trustee.ptstrName = pszTrustee;

    // Create a new ACL that merges the new ACE
    // into the existing DACL.

    dwRes = SetEntriesInAcl(1, &ea, pOldDACL, &pNewDACL);
    if (ERROR_SUCCESS != dwRes)  {
        printf( "SetEntriesInAcl Error %u\n", dwRes );
        goto Cleanup; 
    }  

    // Attach the new ACL as the object's DACL.

    dwRes = SetNamedSecurityInfo(pszObjName, ObjectType, 
          DACL_SECURITY_INFORMATION,
          NULL, NULL, pNewDACL, NULL);
    if (ERROR_SUCCESS != dwRes)  {
        printf( "SetNamedSecurityInfo Error %u\n", dwRes );
        goto Cleanup; 
    }  

    Cleanup:

        if(pSD != NULL) 
            LocalFree((HLOCAL) pSD); 
        if(pNewDACL != NULL) 
            LocalFree((HLOCAL) pNewDACL); 

        return dwRes;
}
```



#### 4.2 Creating a Security Descriptor for a New Object in C++

다음 예제에서는 다음 프로세스를 사용하여 새 레지스트리 키에 대한 보안 설명자를 만듭니다. 비슷한 코드를 사용하여 다른 개체 유형에 대한 보안 설명자를 만들 수 있습니다.

- 다음 예제에서는 다음 프로세스를 사용하여 새 레지스트리 키에 대한 보안 설명자를 만듭니다. 비슷한 코드를 사용하여 다른 개체 유형에 대한 보안 설명자를 만들 수 있습니다.
- EXPLICIT_ACCESS 배열은 SetEntriesInAcl 함수에 전달되어 보안 설명 자의 DACL을 만듭니다.
- 보안 설명자를위한 메모리를 할당 한 후 예제에서는 InitializeSecurityDescriptor 및 SetSecurityDescriptorDacl 함수를 호출하여 보안 설명자를 초기화하고 DACL을 연결합니다.
- 그런 다음 보안 설명자는 SECURITY_ATTRIBUTES 구조에 저장되고 새로 작성된 키에 보안 설명자를 연결하는 RegCreateKeyEx 함수에 전달됩니다.

```c++
#pragma comment(lib, "advapi32.lib")

#include <windows.h>
#include <stdio.h>
#include <aclapi.h>
#include <tchar.h>

void main()
{

    DWORD dwRes, dwDisposition;
    PSID pEveryoneSID = NULL, pAdminSID = NULL;
    PACL pACL = NULL;
    PSECURITY_DESCRIPTOR pSD = NULL;
    EXPLICIT_ACCESS ea[2];
    SID_IDENTIFIER_AUTHORITY SIDAuthWorld =
            SECURITY_WORLD_SID_AUTHORITY;
    SID_IDENTIFIER_AUTHORITY SIDAuthNT = SECURITY_NT_AUTHORITY;
    SECURITY_ATTRIBUTES sa;
    LONG lRes;
    HKEY hkSub = NULL;

    // Create a well-known SID for the Everyone group.
    if(!AllocateAndInitializeSid(&SIDAuthWorld, 1,
                     SECURITY_WORLD_RID,
                     0, 0, 0, 0, 0, 0, 0,
                     &pEveryoneSID))
    {
        _tprintf(_T("AllocateAndInitializeSid Error %u\n"), GetLastError());
        goto Cleanup;
    }

    // Initialize an EXPLICIT_ACCESS structure for an ACE.
    // The ACE will allow Everyone read access to the key.
    ZeroMemory(&ea, 2 * sizeof(EXPLICIT_ACCESS));
    ea[0].grfAccessPermissions = KEY_READ;
    ea[0].grfAccessMode = SET_ACCESS;
    ea[0].grfInheritance= NO_INHERITANCE;
    ea[0].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[0].Trustee.TrusteeType = TRUSTEE_IS_WELL_KNOWN_GROUP;
    ea[0].Trustee.ptstrName  = (LPTSTR) pEveryoneSID;

    // Create a SID for the BUILTIN\Administrators group.
    if(! AllocateAndInitializeSid(&SIDAuthNT, 2,
                     SECURITY_BUILTIN_DOMAIN_RID,
                     DOMAIN_ALIAS_RID_ADMINS,
                     0, 0, 0, 0, 0, 0,
                     &pAdminSID)) 
    {
        _tprintf(_T("AllocateAndInitializeSid Error %u\n"), GetLastError());
        goto Cleanup; 
    }

    // Initialize an EXPLICIT_ACCESS structure for an ACE.
    // The ACE will allow the Administrators group full access to
    // the key.
    ea[1].grfAccessPermissions = KEY_ALL_ACCESS;
    ea[1].grfAccessMode = SET_ACCESS;
    ea[1].grfInheritance= NO_INHERITANCE;
    ea[1].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[1].Trustee.TrusteeType = TRUSTEE_IS_GROUP;
    ea[1].Trustee.ptstrName  = (LPTSTR) pAdminSID;

    // Create a new ACL that contains the new ACEs.
    dwRes = SetEntriesInAcl(2, ea, NULL, &pACL);
    if (ERROR_SUCCESS != dwRes) 
    {
        _tprintf(_T("SetEntriesInAcl Error %u\n"), GetLastError());
        goto Cleanup;
    }

    // Initialize a security descriptor.  
    pSD = (PSECURITY_DESCRIPTOR) LocalAlloc(LPTR, 
                             SECURITY_DESCRIPTOR_MIN_LENGTH); 
    if (NULL == pSD) 
    { 
        _tprintf(_T("LocalAlloc Error %u\n"), GetLastError());
        goto Cleanup; 
    } 
 
    if (!InitializeSecurityDescriptor(pSD,
            SECURITY_DESCRIPTOR_REVISION)) 
    {  
        _tprintf(_T("InitializeSecurityDescriptor Error %u\n"),
                                GetLastError());
        goto Cleanup; 
    } 
 
    // Add the ACL to the security descriptor. 
    if (!SetSecurityDescriptorDacl(pSD, 
            TRUE,     // bDaclPresent flag   
            pACL, 
            FALSE))   // not a default DACL 
    {  
        _tprintf(_T("SetSecurityDescriptorDacl Error %u\n"),
                GetLastError());
        goto Cleanup; 
    } 

    // Initialize a security attributes structure.
    sa.nLength = sizeof (SECURITY_ATTRIBUTES);
    sa.lpSecurityDescriptor = pSD;
    sa.bInheritHandle = FALSE;

    // Use the security attributes to set the security descriptor 
    // when you create a key.
    lRes = RegCreateKeyEx(HKEY_CURRENT_USER, _T("mykey"), 0, _T(""), 0, 
            KEY_READ | KEY_WRITE, &sa, &hkSub, &dwDisposition); 
    _tprintf(_T("RegCreateKeyEx result %u\n"), lRes );

Cleanup:

    if (pEveryoneSID) 
        FreeSid(pEveryoneSID);
    if (pAdminSID) 
        FreeSid(pAdminSID);
    if (pACL) 
        LocalFree(pACL);
    if (pSD) 
        LocalFree(pSD);
    if (hkSub) 
        RegCloseKey(hkSub);

    return;

}
```





#### 4.3 Controlling Child Object Creation in C++

컨테이너 개체의 DACL을 사용하여 컨테이너 내에서 자식 개체를 만들 수있는 사용자를 제어 할 수 있습니다. 일반적으로 개체 작성자가 개체의 소유자로 할당되고 개체의 소유자가 개체에 대한 액세스를 제어 할 수 있으므로이 작업이 중요 할 수 있습니다.

다양한 유형의 컨테이너 객체에는 하위 객체를 만드는 기능을 제어하는 특정 액세스 권한이 있습니다. 예를 들어 키 아래에 하위 키를 만들려면 스레드에 레지스트리 키에 대한 KEY_CREATE_SUB_KEY 액세스 권한이 있어야합니다. 레지스트리 키의 DACL에는이 액세스 권한을 허용하거나 거부하는 ACE가 포함될 수 있습니다. 마찬가지로 NTFS는 디렉터리에 파일이나 디렉터리를 만드는 기능을 제어하기 위해 FILE_ADD_FILE 및 FILE_ADD_SUBDIRECTORY 액세스 권한을 지원합니다.

ADS_RIGHT_DS_CREATE_CHILD 액세스 권한은 DS (디렉터리 서비스) 개체의 자식 개체 생성을 제어합니다. 그러나 DS 개체에는 다양한 유형의 개체가 포함될 수 있으므로 시스템은보다 세밀한 제어를 지원합니다. 개체 별 ACE를 사용하여 지정된 형식의 자식 개체를 만들 수있는 권한을 허용하거나 거부 할 수 있습니다. 사용자가 다른 유형의 하위 객체를 만들지 못하게하면서 한 유형의 하위 객체를 만들도록 허용 할 수 있습니다.

다음 예제에서는 SetEntriesInAcl 함수를 사용하여 개체 별 ACE를 ACL에 추가합니다. ACE는 지정된 유형의 하위 개체를 만들 수있는 권한을 부여합니다. EXPLICIT_ACCESS 구조체의 grfAccessPermissions 멤버는 ADS_RIGHT_DS_CREATE_CHILD로 설정되어 ACE가 자식 개체 생성을 제어 함을 나타냅니다. OBJECTS_AND_SID 구조체의 ObjectsPresent 멤버는 ACE_OBJECT_TYPE_PRESENT로 설정되어 ObjectTypeGuid 멤버에 유효한 GUID가 있음을 나타냅니다. GUID는 생성이 제어되는 자식 개체 유형을 식별합니다.

다음 예제에서 pOldDACL은 기존 ACL 구조에 대한 유효한 포인터 여야합니다. 개체의 ACL 구조를 만드는 방법에 대한 자세한 내용은 [C++의 새 개체에 대한 보안 설명자 만들기](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/creating-a-security-descriptor-for-a-new-object-in-c--)를 참조하십시오.

```c++
DWORD dwRes;
PACL pOldDACL = NULL;
PACL pNewDACL = NULL;
GUID guidChildObjectType = GUID_NULL;   // GUID of object to control creation of
PSID pTrusteeSID = NULL;           // trustee for new ACE
EXPLICIT_ACCESS ea;
OBJECTS_AND_SID ObjectsAndSID;

// pOldDACL must be a valid pointer to an existing ACL structure.

// guidChildObjectType must be the GUID of an object type 
// that is a possible child of the object associated with pOldDACL.
 
// Initialize an OBJECTS_AND_SID structure with object type GUIDs and 
// the SID of the trustee for the new ACE. 

ZeroMemory(&ObjectsAndSID, sizeof(OBJECTS_AND_SID));
ObjectsAndSID.ObjectsPresent = ACE_OBJECT_TYPE_PRESENT;
ObjectsAndSID.ObjectTypeGuid = guidChildObjectType;
ObjectsAndSID.InheritedObjectTypeGuid  = GUID_NULL;
ObjectsAndSID.pSid = (SID *)pTrusteeSID;

// Initialize an EXPLICIT_ACCESS structure for the new ACE. 

ZeroMemory(&ea, sizeof(EXPLICIT_ACCESS));
ea.grfAccessPermissions = ADS_RIGHT_DS_CREATE_CHILD;
ea.grfAccessMode = GRANT_ACCESS;
ea.grfInheritance= NO_INHERITANCE;
ea.Trustee.TrusteeForm = TRUSTEE_IS_OBJECTS_AND_SID;
ea.Trustee.ptstrName = (LPTSTR) &ObjectsAndSID;

// Create a new ACL that merges the new ACE
// into the existing DACL.

dwRes = SetEntriesInAcl(1, &ea, pOldDACL, &pNewDACL);
```





#### 4.4 Enabling and Disabling Privileges in C++

C ++에서 권한 활성화 및 비활성화 참조







### 5. [Establishing a Client Context from a SID in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/establishing-a-client-context-from-a-sid-in-c--)

**사용자, 그룹 또는 컴퓨터 계정을 식별합니다. SID를 사용하여 리소스에 대한 액세스 권한을 확인.**





### 6. [Verifying Client Access with ACLs in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/verifying-client-access-with-acls-in-c--)

**[보안 설명자](https://msdn.microsoft.com/library/windows/desktop/ms721625#-security-security-descriptor-gly)가 클라이언트에 허용하는 액세스 권한을 확인합니디.**

다음 예는 보안 설명자가 클라이언트에 허용하는 액세스 권한을 서버가 확인할 수있는 방법을 보여줍니다. 이 예제에서는 ImpersonateNamedPipeClient 함수를 사용합니다. 그러나 다른 가장 (impersonation) 함수를 사용하여 동일하게 작동합니다. 클라이언트를 가장 한 후 이 예에서는 OpenThreadToken 함수를 호출하여 가장 토큰을 가져옵니다. 그런 다음 MapGenericMask 함수를 호출하여 GENERIC_MAPPING 구조에 지정된 매핑에 따라 모든 일반 액세스 권한을 해당 특정 및 표준 권한으로 변환합니다.

AccessCheck 함수는 보안 설명자의 DACL에서 클라이언트에 대해 허용 된 권한에 대해 요청 된 액세스 권한을 확인합니다. 액세스를 확인하고 보안 이벤트 로그에 항목을 생성하려면 [AccessCheckAndAuditAlarm](https://docs.microsoft.com/en-us/windows/desktop/api/Winbase/nf-winbase-accesscheckandauditalarma) 기능을 사용하십시오.

```c++
#include <windows.h>
#pragma comment(lib, "advapi32.lib")

BOOL ImpersonateAndCheckAccess(
  HANDLE hNamedPipe,               // 가장 파이프 핸들
  PSECURITY_DESCRIPTOR pSD,        // 검사 할 보안 설명자
  DWORD dwAccessDesired,           // 확인 권한
  PGENERIC_MAPPING pGeneric,       // 객체에 대한 일반 매핑
  PDWORD pdwAccessAllowed          // 허용 된 액세스 권한을 반환합니다.
) 
{
   HANDLE hToken;
   PRIVILEGE_SET PrivilegeSet;
   DWORD dwPrivSetSize = sizeof( PRIVILEGE_SET );
   BOOL fAccessGranted=FALSE;

// 클라이언트로 가장하십시오.

   if (! ImpersonateNamedPipeClient(hNamedPipe) ) 
      return FALSE;

// 클라이언트 보안 컨텍스트로 가장 (impersonation) 토큰을 가져옵니다.

   if (! OpenThreadToken( GetCurrentThread(), TOKEN_ALL_ACCESS,
         TRUE, &hToken ))
   {
      goto Cleanup;
   }

// 일반 액세스 권한을 개체 별 액세스 권한으로 변환하려면 
// GENERIC_MAPPING 구조체를 사용하십시오.

   MapGenericMask( &dwAccessDesired, pGeneric );

// 클라이언트의 액세스 권한을 확인하십시오.

   if( !AccessCheck( 
      pSD,                 // 검사 할 보안 설명 자
      hToken,              // 가장 토큰
      dwAccessDesired,     // 요청 된 액세스 권한
      pGeneric,            // GENERIC_MAPPING에 대한 포인터
      &PrivilegeSet,       // 체크에 사용 된 특권을 받는다.
      &dwPrivSetSize,      // PrivilegeSet 버퍼의 크기
      pdwAccessAllowed,    // 허용 된 접근 권한의 마스크를 받는다.
      &fAccessGranted ))   // 액세스 검사 결과를 수신한다.
   {
      goto Cleanup;
   }

Cleanup:

   RevertToSelf();

   if (hToken != INVALID_HANDLE_VALUE)
      CloseHandle(hToken);  

   return fAccessGranted;
}
```





### 7. [Finding the Owner of a File Object in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/finding-the-owner-of-a-file-object-in-c--)

**파일 소유자의 이름을 찾아서 인쇄합니다.**

다음 예제에서는 GetSecurityInfo 및 LookupAccountSid 함수를 사용하여 파일 소유자의 이름을 찾아서 인쇄합니다. 파일은 로컬 서버의 현재 작업 디렉토리에 있습니다.

```c++
#include <stdio.h>
#include <windows.h>
#include <tchar.h>
#include "accctrl.h"
#include "aclapi.h"
#pragma comment(lib, "advapi32.lib")

int main(void)
{
DWORD dwRtnCode = 0;
PSID pSidOwner = NULL;
BOOL bRtnBool = TRUE;
LPTSTR AcctName = NULL;
LPTSTR DomainName = NULL;
DWORD dwAcctName = 1, dwDomainName = 1;
SID_NAME_USE eUse = SidTypeUnknown;
HANDLE hFile;
PSECURITY_DESCRIPTOR pSD = NULL;


// 파일 객체의 핸들을 가져옵니다.
hFile = CreateFile(
                  TEXT("myfile.txt"),
                  GENERIC_READ,
                  FILE_SHARE_READ,
                  NULL,
                  OPEN_EXISTING,
                  FILE_ATTRIBUTE_NORMAL,
                  NULL);

// CreateFile 오류 코드에 대해 GetLastError를 확인하십시오.
if (hFile == INVALID_HANDLE_VALUE) {
          DWORD dwErrorCode = 0;

          dwErrorCode = GetLastError();
          _tprintf(TEXT("CreateFile error = %d\n"), dwErrorCode);
          return -1;
}



// 파일의 소유자 SID를 가져옵니다.
dwRtnCode = GetSecurityInfo(
                  hFile,
                  SE_FILE_OBJECT,
                  OWNER_SECURITY_INFORMATION,
                  &pSidOwner,
                  NULL,
                  NULL,
                  NULL,
                  &pSD);

// GetSecurityInfo 오류 조건에 대해 GetLastError를 확인하십시오.
if (dwRtnCode != ERROR_SUCCESS) {
          DWORD dwErrorCode = 0;

          dwErrorCode = GetLastError();
          _tprintf(TEXT("GetSecurityInfo error = %d\n"), dwErrorCode);
          return -1;
}

// 먼저 LookupAccountSid를 호출하여 버퍼 크기를 가져옵니다.
bRtnBool = LookupAccountSid(
                  NULL,           // local computer
                  pSidOwner,
                  AcctName,
                  (LPDWORD)&dwAcctName,
                  DomainName,
                  (LPDWORD)&dwDomainName,
                  &eUse);

// 버퍼의 메모리를 재 할당하십시오.
AcctName = (LPTSTR)GlobalAlloc(
          GMEM_FIXED,
          dwAcctName);

// GlobalAlloc 오류 조건에 대한 GetLastError를 확인하십시오.
if (AcctName == NULL) {
          DWORD dwErrorCode = 0;

          dwErrorCode = GetLastError();
          _tprintf(TEXT("GlobalAlloc error = %d\n"), dwErrorCode);
          return -1;
}

    DomainName = (LPTSTR)GlobalAlloc(
           GMEM_FIXED,
           dwDomainName);

    // GlobalAlloc 오류 조건에 대한 GetLastError를 확인하십시오.
    if (DomainName == NULL) {
          DWORD dwErrorCode = 0;

          dwErrorCode = GetLastError();
          _tprintf(TEXT("GlobalAlloc error = %d\n"), dwErrorCode);
          return -1;

    }

    // Second call to LookupAccountSid to get the account name.
    bRtnBool = LookupAccountSid(
          NULL,                   // 로컬 또는 원격 컴퓨터의 이름
          pSidOwner,              // 보안 식별자
          AcctName,               // 계정 이름 버퍼
          (LPDWORD)&dwAcctName,   // 계정 이름 버퍼 크기 
          DomainName,             // 도메인 이름
          (LPDWORD)&dwDomainName, // 도메인 이름 버퍼 크기
          &eUse);                 // SID 유형

    // LookupAccountSid를 두 번째 호출하여 계정 이름을 가져옵니다.
    if (bRtnBool == FALSE) {
          DWORD dwErrorCode = 0;

          dwErrorCode = GetLastError();

          if (dwErrorCode == ERROR_NONE_MAPPED)
              _tprintf(TEXT
                  ("Account owner not found for specified SID.\n"));
          else 
              _tprintf(TEXT("Error in LookupAccountSid.\n"));
          return -1;

    } else if (bRtnBool == TRUE) 

        // Print the account name.
        _tprintf(TEXT("Account owner = %s\n"), AcctName);

    return 0;
}
```





### 8. [Taking Object Ownership in C++](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/taking-object-ownership-in-c--)

**해당 개체의 소유권을 가져 와서 파일 개체의 DACL을 변경합니다.**

다음 예제에서는 해당 개체의 소유권을 가져 와서 파일 개체의 DACL을 변경하려고 시도합니다. 호출자가 개체에 대한 WRITE_DAC 액세스 권한을 갖고 있거나 개체 소유자 인 경우에만 성공합니다. DACL을 처음 변경하려는 시도가 실패하면 관리자는 개체의 소유권을 가져올 수 있습니다. 이 예에서는 관리자에게 소유권을 부여하기 위해 호출자의 [액세스 토큰](https://msdn.microsoft.com/library/windows/desktop/ms721532#-security-access-token-gly)에서 SE_TAKE_OWNERSHIP_NAME 권한을 사용하고 로컬 시스템의 Administrators 그룹을 개체의 소유자로 만듭니다. 호출자가 Administrators 그룹의 구성원이면 코드에서 개체의 DACL을 변경할 수 있습니다.

권한을 활성화 및 비활성화하기 위해이 예제에서는[ C ++에서 권한 활성화 및 비활성화](https://docs.microsoft.com/ko-kr/windows/desktop/SecAuthZ/enabling-and-disabling-privileges-in-c--)에 설명 된 SetPrivilege 샘플 함수를 사용합니다.

```c++
#include <windows.h>
#include <stdio.h>
#include <accctrl.h>
#include <aclapi.h>

//Forward declaration of SetPrivilege
BOOL SetPrivilege(
    HANDLE hToken,          // 액세스 토큰 핸들
    LPCTSTR lpszPrivilege,  // 활성화/비활성화 할 권한 이름
    BOOL bEnablePrivilege   // 권한을 활성화 또는 비활성화하는 방법
    ) ;


BOOL TakeOwnership(LPTSTR lpszOwnFile) 
{

    BOOL bRetval = FALSE;

    HANDLE hToken = NULL; 
    PSID pSIDAdmin = NULL;
    PSID pSIDEveryone = NULL;
    PACL pACL = NULL;
    SID_IDENTIFIER_AUTHORITY SIDAuthWorld =
            SECURITY_WORLD_SID_AUTHORITY;
    SID_IDENTIFIER_AUTHORITY SIDAuthNT = SECURITY_NT_AUTHORITY;
    const int NUM_ACES  = 2;
    EXPLICIT_ACCESS ea[NUM_ACES];
    DWORD dwRes;

    // 사용할 DACL을 지정하십시오.
    // Everyone 그룹에 대한 SID를 만듭니다..
    if (!AllocateAndInitializeSid(&SIDAuthWorld, 1,
                     SECURITY_WORLD_RID,
                     0,
                     0, 0, 0, 0, 0, 0,
                     &pSIDEveryone)) 
    {
        printf("AllocateAndInitializeSid (Everyone) error %u\n",
                GetLastError());
        goto Cleanup;
    }

    // BUILTIN\Administrators 그룹에 대한 SID를 만듭니다.
    if (!AllocateAndInitializeSid(&SIDAuthNT, 2,
                     SECURITY_BUILTIN_DOMAIN_RID,
                     DOMAIN_ALIAS_RID_ADMINS,
                     0, 0, 0, 0, 0, 0,
                     &pSIDAdmin)) 
    {
        printf("AllocateAndInitializeSid (Admin) error %u\n",
                GetLastError());
        goto Cleanup;
    }

    ZeroMemory(&ea, NUM_ACES * sizeof(EXPLICIT_ACCESS));

    // Everyone에 대한 읽기 권한을 설정하십시오.
    ea[0].grfAccessPermissions = GENERIC_READ;
    ea[0].grfAccessMode = SET_ACCESS;
    ea[0].grfInheritance = NO_INHERITANCE;
    ea[0].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[0].Trustee.TrusteeType = TRUSTEE_IS_WELL_KNOWN_GROUP;
    ea[0].Trustee.ptstrName = (LPTSTR) pSIDEveryone;

    // 관리자를 위해 모든 권한을 설정하십시오.
    ea[1].grfAccessPermissions = GENERIC_ALL;
    ea[1].grfAccessMode = SET_ACCESS;
    ea[1].grfInheritance = NO_INHERITANCE;
    ea[1].Trustee.TrusteeForm = TRUSTEE_IS_SID;
    ea[1].Trustee.TrusteeType = TRUSTEE_IS_GROUP;
    ea[1].Trustee.ptstrName = (LPTSTR) pSIDAdmin;

    if (ERROR_SUCCESS != SetEntriesInAcl(NUM_ACES,
                                         ea,
                                         NULL,
                                         &pACL))
    {
        printf("Failed SetEntriesInAcl\n");
        goto Cleanup;
    }

    // 개체의 DACL을 수정하십시오.
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // 객체의 이름
        SE_FILE_OBJECT,              // 객체 유형
        DACL_SECURITY_INFORMATION,   // 개체의 DACL 만 변경
        NULL, NULL,                  // 소유자 또는 그룹을 변경하지 마십시오.
        pACL,                        // 지정된 DACL
        NULL);                       // SACL을 변경하지 마십시오.

    if (ERROR_SUCCESS == dwRes) 
    {
        printf("Successfully changed DACL\n");
        bRetval = TRUE;
        // No more processing needed.
        goto Cleanup;
    }
    if (dwRes != ERROR_ACCESS_DENIED)
    {
        printf("First SetNamedSecurityInfo call failed: %u\n",
                dwRes); 
        goto Cleanup;
    }

    // 액세스가 거부되어 이전 호출이 실패한 경우 SE_TAKE_OWNERSHIP_NAME 
    // 권한을 활성화하고 Administrators 그룹에 대한 SID를 만들고 해당 개체의 
    // 소유권을 가져오고 권한을 사용하지 않도록 설정하십시오. 
    // 그런 다음 개체의 DACL을 다시 설정하십시오.

    // 호출 프로세스의 액세스 토큰에 대한 핸들을 엽니 다.
    if (!OpenProcessToken(GetCurrentProcess(), 
                          TOKEN_ADJUST_PRIVILEGES, 
                          &hToken)) 
       {
          printf("OpenProcessToken failed: %u\n", GetLastError()); 
          goto Cleanup; 
       } 

    // SE_TAKE_OWNERSHIP_NAME 권한을 활성화하십시오.
    if (!SetPrivilege(hToken, SE_TAKE_OWNERSHIP_NAME, TRUE)) 
    {
        printf("You must be logged on as Administrator.\n");
        goto Cleanup; 
    }

    // 개체의 보안 설명자에 소유자를 설정합니다..
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // 객체의 이름
        SE_FILE_OBJECT,              // 객체 유형
        OWNER_SECURITY_INFORMATION,  // 객체 소유자 만 변경
        pSIDAdmin,                   // 관리자 그룹의 SID
        NULL,
        NULL,
        NULL); 

    if (dwRes != ERROR_SUCCESS) 
    {
        printf("Could not set owner. Error: %u\n", dwRes); 
        goto Cleanup;
    }
        
    // SE_TAKE_OWNERSHIP_NAME 권한을 사용 중지합니다..
    if (!SetPrivilege(hToken, SE_TAKE_OWNERSHIP_NAME, FALSE)) 
    {
        printf("Failed SetPrivilege call unexpectedly.\n");
        goto Cleanup;
    }

    // 개체의 DACL을 다시 수정하여 소유자가되었으므로 이제 다시 시도하십시오.
    dwRes = SetNamedSecurityInfo(
        lpszOwnFile,                 // 객체의 이름
        SE_FILE_OBJECT,              // 객체 유형
        DACL_SECURITY_INFORMATION,   // 개체의 DACL 만 변경
        NULL, NULL,                  // 소유자 또는 그룹을 변경하지 마십시오.
        pACL,                        // 지정된 DACL
        NULL);                       // SACL을 변경하지 마십시오.

    if (dwRes == ERROR_SUCCESS)
    {
        printf("Successfully changed DACL\n");
        bRetval = TRUE; 
    }
    else
    {
        printf("Second SetNamedSecurityInfo call failed: %u\n",
                dwRes); 
    }

Cleanup:

    if (pSIDAdmin)
        FreeSid(pSIDAdmin); 

    if (pSIDEveryone)
        FreeSid(pSIDEveryone); 

    if (pACL)
       LocalFree(pACL);

    if (hToken)
       CloseHandle(hToken);

    return bRetval;

}
```



####  C ++에서 권한 활성화 및 비활성화

액세스 토큰에서 권한을 사용하면 프로세스가 이전에 수행 할 수 없었던 시스템 수준의 작업을 수행 할 수 있습니다. 응용 프로그램은 특권이 계정 유형, 특히 다음과 같은 강력한 특권에 적합한 지 철저하게 검증해야합니다.

| Privilege constant/string                                | Display name                  |
| :------------------------------------------------------- | :---------------------------- |
| SE_ASSIGNPRIMARYTOKEN_NAME SeAssignPrimaryTokenPrivilege | 프로세스 수준 토큰 바꾸기     |
| SE_BACKUP_NAME SeBackupPrivilege                         | 파일 및 디렉토리 백업         |
| SE_DEBUG_NAME SeDebugPrivilege                           | 프로그램 디버그               |
| SE_INCREASE_QUOTA_NAME SeIncreaseQuotaPrivilege          | 프로세스의 메모리 할당량 조정 |
| SE_TCB_NAME SeTcbPrivilege                               | 운영 체제의 일부로 작동       |

이러한 위험 가능성이있는 권한을 활성화하기 전에 코드의 기능이나 작업에 실제로 권한이 필요한지 확인하십시오. 예를 들어 운영 체제의 기능 중 SeTcbPrivilege가 필요한 기능은 거의 없습니다. 사용 가능한 모든 권한 목록을 보려면 권한 상수를 참조하십시오.

다음 예에서는 액세스 토큰에서 권한을 사용하거나 사용하지 않도록 설정하는 방법을 보여줍니다. 이 예에서는 LookupPrivilegeValue 함수를 호출하여 로컬 시스템에서 권한을 식별하는 데 사용하는 로컬 고유 식별자 (LUID)를 가져옵니다. 그런 다음 예제에서는 AdjustTokenPrivileges 함수를 호출합니다.이 함수는 bEnablePrivilege 매개 변수의 값에 따라 권한을 사용하거나 사용하지 않도록 설정합니다.

```c++
#include <windows.h>
#include <stdio.h>
#pragma comment(lib, "cmcfg32.lib")

BOOL SetPrivilege(
    HANDLE hToken,          // access token handle
    LPCTSTR lpszPrivilege,  // 활성화/비활성화 할 권한 이름
    BOOL bEnablePrivilege   // 권한을 활성화 또는 비활성화하는 방법
    ) 
{
    TOKEN_PRIVILEGES tp;
    LUID luid;

    if ( !LookupPrivilegeValue( 
            NULL,            // 로컬 시스템에 대한 조회 권한
            lpszPrivilege,   // 조회 권한
            &luid ) )        // LUID 또는 특권을 받음
    {
        printf("LookupPrivilegeValue error: %u\n", GetLastError() ); 
        return FALSE; 
    }

    tp.PrivilegeCount = 1;
    tp.Privileges[0].Luid = luid;
    if (bEnablePrivilege)
        tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    else
        tp.Privileges[0].Attributes = 0;

    // 권한을 활성화하거나 모든 권한을 비활성화합니다..

    if ( !AdjustTokenPrivileges(
           hToken, 
           FALSE, 
           &tp, 
           sizeof(TOKEN_PRIVILEGES), 
           (PTOKEN_PRIVILEGES) NULL, 
           (PDWORD) NULL) )
    { 
          printf("AdjustTokenPrivileges error: %u\n", GetLastError() ); 
          return FALSE; 
    } 

    if (GetLastError() == ERROR_NOT_ALL_ASSIGNED)

    {
          printf("The token does not have the specified privilege. \n");
          return FALSE;
    } 

    return TRUE;
}
```

