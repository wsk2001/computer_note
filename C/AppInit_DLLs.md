# AppInit_DLLs

출처: http://egloos.zum.com/byung/v/5126114

AppInit_DLLs의 구조는 일반적으로 API hooking의 mechanism으로 사용되었던 구조이다. 해당 레지스트리 키(AppInit_DLLs:REG_SZ)는 HKEY_LOCAL_MACHINE\Software\Microsoft\Windows NT\CurrentVersion\Windows 에서 확인할 수가 있는 데, 이는 User32.dll의 DLL_PROCESS_ATTACH과정에서 LoadLibrary() 함수를 사용하여 load 된다. 그러므로, 해당 Registry에 설정된 DLL path는 user32.dll을 사용하는 모든 Application에 의해서 load되므로, DLL injection의 기능으로 사용되어 왔다. 이에 따라서 많은 malware가 이러한 mechanism을 사용하는 바, Dead Lock이나 Crash의 문제가 되었었고, performance에도 영향을 미치게 됨에 따라서 Win7에서는 일부 수정이 있게 되었다.

 

사실 Windows Vista 에서 부터 AppInit_DLLs 구조는 default 가 disable 이다. 이러한 Default 설정은 Win7에서도 마찮가지이며, 이외에도 다음의 표와 같이 일부 수정된 기능이 존재한다.

 

| **Value**                             | **Description**                                              | **Sample values**                                            |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| LoadAppInit_DLLs (REG_DWORD)          | Value that globally enables or disables AppInit_DLLs.        | 0x0 – AppInit_DLLs are disabled. 0x1 – AppInit_DLLs are enabled. |
| AppInit_DLLs (REG_SZ)                 | Space -or comma-delimited list of DLLs to load. The complete path to the DLL should be specified by using short file names. | C:\PROGRA~1\Test\Test.dll                                    |
| RequireSignedAppInit_DLLs (REG_DWORD) | Require code-signed DLLs.                                    | 0x0 – Load any DLLs. 0x1 – Load only code-signed DLLs.       |

상위의 표를 보면 알겠지만, AppInit_DLLs 를 원천적으로 Disable 할 수 있다. 그리고, enable이 된다 하더라도 Code-signed DLL만을 Load하게끔 구성할 수 있다. 이미 짐작하겠지만, AppInit_DLLs를 Application Compatibility의 이유로 인하여 사용해야만 한다면, Recommendation으로는 code-signed DLLs만을 Load하도록 하는 것이 될 것이다.

 

DLL을 Code-sign하기 위해서는 Digital Signature를 Binary에 embedding 해야 할 것이며, 이는 아래와 같은 절차를 따르게 된다.

ü /integritycheck linker option을 가지고 DLL을 Link 한다. 이는 Visual Studio 2005/2008에서 지원한다. 이는 dumpbin /headers <dllname.dll> 을 통해 dumping된 data에서 Check integrity 가 포함되었는지를 확인함으로써 verify할 수 있다.

ü DLL은 **/ph** (page-hash) option을 가지고 signed되어야 한다. 또한, **/t** (timestamp) option을 가지고 sign함으로써 code signing certificate의 expiring에 대해서 보호될 수 있다. 그리고, release시에는 반드시 **/ac** option을 사용하여 cross-certificate에 대한 참조가 가능하도록 한다. 이는 signing code path가 windows kernel에 존재할 때 필요하다.

 

다음은 SignTool을 이용하여 DLL을 sign 하는 예이다.

 

·     Test-signing example:

Signtool sign /v /ph /s My /n Contoso.com(Test) /t http://timestamp.verisign.com/scripts/timestamp.dll DLLname.dll

 

·     Release-signing example:

Signtool sign /v **/ph /ac** MSCV-VSClass3.cer /s my /n contoso.com **/t** http://timestamp.verisign.com/scripts/timestamp.dll *DLLname*.dll

 

참조 및 발췌: http://www.microsoft.com/whdc/driver/install/AppInit-Win7.mspx



