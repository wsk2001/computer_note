# Windows에서의 LD_PRELOAD

Windows에서는 LD_PRELOAD와 유사한 기능을 수행하는데, 이를 가능하게 하는 몇 가지 방법이 있습니다. LD_PRELOAD는 주로 Linux에서 사용되는 환경 변수로, 프로그램이 실행될 때 라이브러리를 강제로 로드하게끔 하는 데 사용됩니다. Windows에서는 다른 방식으로 동일한 효과를 얻을 수 있습니다.

1. **AppInit_DLLs 레지스트리 키:** Windows에서는 `AppInit_DLLs` 레지스트리 키를 통해 DLL을 로드할 수 있습니다. 하지만 이 방법은 보안 이슈로 인해 사용이 제한되어 있습니다.

   - 레지스트리 편집기를 엽니다.
   - `HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows` 키로 이동합니다.
   - `AppInit_DLLs` 값을 설정하고, DLL 경로를 추가합니다.
   - 이 변경 사항을 적용하려면 시스템을 다시 시작해야 합니다.

   이 방법은 쉽게 감지될 수 있고, 보안 소프트웨어에 의해 차단될 수 있습니다.

2. **Detours 라이브러리 사용:** Detours는 Microsoft Research에서 개발한 라이브러리로, 함수 후킹 및 코드 주입에 사용됩니다. Detours를 사용하면 실행 중인 프로세스에 DLL을 주입하여 함수를 후킹할 수 있습니다.

   Detours는 상업적으로 사용할 때는 라이센스가 필요하므로 주의가 필요합니다.

3. **DLL Injection:** DLL 주입은 외부 DLL을 실행 중인 프로세스에 주입하여 특정 동작을 삽입하는 기술입니다. 이를 통해 LD_PRELOAD와 유사한 효과를 얻을 수 있습니다.

   DLL 주입은 여러 방법으로 수행될 수 있으며, 주로 `CreateRemoteThread`, `SetWindowsHookEx` 등의 WinAPI 함수를 사용합니다.

이러한 방법들은 시스템에 영향을 미칠 수 있고, 보안 문제가 발생할 수 있으므로 주의해서 사용해야 합니다. 특히 보안 소프트웨어와 충돌할 수 있으며, 사용자의 동의를 얻지 않고는 시스템에 변화를 가하는 것은 바람직하지 않습니다.