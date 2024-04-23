# Detours Samples 설명

출처: https://github.com/microsoft/detours/wiki/Samples



# Building The Samples



샘플 애플리케이션을 빌드하려면 샘플 디렉터리에 nmake를 입력하세요. 다른 많은 샘플 프로그램을 사용하려면 setdll 및 syslog 샘플을 빌드해야 합니다.

각 샘플 디렉토리에는 샘플 사용법을 보여주기 위해 nmake test를 입력하여 호출할 수 있는 테스트가 있습니다. 거의 예외를 제외하고 모든 .exe 프로그램은 /? 사용법 메시지를 표시하는 명령입니다.

trace 샘플은 syelogd.exe 데몬을 통해 출력을 기록하고 CreateProcessW를 후크하여 하위 프로세스에 로드합니다. 예를 들어, `withdll -d:traceapi.dll cmd.exe`를 입력하면 모든 프로세스가 traceapi.dll을 통해 API 호출을 기록하는 명령 셸이 생성됩니다.

### Detours 에는 다음 샘플이 포함됩니다.:



| Name                                                         | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Commem](https://github.com/microsoft/detours/wiki/SampleCommem) | COM 인터페이스의 멤버 함수를 우회하는 방법을 보여줍니다.     |
| [Cping](https://github.com/microsoft/detours/wiki/SampleCping) | DCOM/RPC 스택의 여러 기능을 우회하여 DCOM 메시지 전송에 따른 오버헤드를 측정합니다. |
| [Disas](https://github.com/microsoft/detours/wiki/SampleDisas) | Detours 디스어셈블러 테이블을 테스트합니다.                  |
| [Dtest](https://github.com/microsoft/detours/wiki/SampleDtest) | Win32 Sleep 기능과 Private 기능을 우회합니다.                |
| [Dumpe](https://github.com/microsoft/detours/wiki/SampleDumpe) | 바이너리에서 내보낸 모든 함수 목록을 덤프합니다.             |
| [Dumpi](https://github.com/microsoft/detours/wiki/SampleDumpi) | 바이너리로 가져온 모든 함수 목록을 덤프합니다.               |
| [dynamic_alloc](https://github.com/microsoft/detours/wiki/SampleDynamicAlloc) | 동적으로 할당된 영역에서 작성된 코드로 우회                  |
| [Einst](https://github.com/microsoft/detours/wiki/SampleEinst) | 바이너리 파일로 컴파일된 페이로드를 찾습니다.                |
| [Excep](https://github.com/microsoft/detours/wiki/SampleExcep) | 첫 번째 예외 필터를 사용하여 페이지의 VM 권한을 전환합니다.  |
| [FindFunc](https://github.com/microsoft/detours/wiki/SampleFindFunc) | 디버그 기호를 사용하여 함수를 찾기 위해 DetourFindFunction을 사용하여 함수를 우회합니다. |
| [Impmunge](https://github.com/microsoft/detours/wiki/SampleImpmunge) | 바이너리 가져오기 테이블의 모든 항목을 수정합니다.           |
| [Member](https://github.com/microsoft/detours/wiki/SampleMember) | 클래스 멤버 함수를 우회하는 방법을 보여줍니다.               |
| [Payload](https://github.com/microsoft/detours/wiki/SamplePayload) | 페이로드를 사용하여 런타임에 정보를 전달하는 다양한 방법을 보여줍니다. |
| [Region](https://github.com/microsoft/detours/wiki/SampleRegion) | 트램폴린에 대한 제한이 없는 메모리 영역을 변경하는 방법을 보여줍니다. |
| [Setdll](https://github.com/microsoft/detours/wiki/SampleSetdll) | <span style="color:red">모든 바이너리(예: .DLL 또는 .EXE)의 가져오기 테이블에 DLL을 추가합니다.</span> |
| [Simple](https://github.com/microsoft/detours/wiki/SampleSimple) | sleep 에 소요된 틱 수를 기록하도록 Sleep API를 수정합니다.   |
| [Slept](https://github.com/microsoft/detours/wiki/SampleSlept) | Simple 샘플의 보다 정교한 버전입니다.                        |
| [Syelog](https://github.com/microsoft/detours/wiki/SampleSyelog) | 시스템 이벤트 로깅 라이브러리 및 서비스.                     |
| [Traceapi](https://github.com/microsoft/detours/wiki/SampleTraceapi) | Win32 API 추적 샘플. 1401 Win32 API 함수에 대한 추적 문을 우회하고 인쇄합니다. |
| [Tracebld](https://github.com/microsoft/detours/wiki/SampleTracebld) | 프로세스와 모든 하위 프로세스의 파일 액세스 패턴을 추적합니다. |
| [Tracelnk](https://github.com/microsoft/detours/wiki/SampleTracelnk) | Windows 동적 연결 API에 대한 모든 호출을 추적합니다.         |
| [Tracemem](https://github.com/microsoft/detours/wiki/SampleTracemem) | Windows HeapAlloc API에 대한 모든 호출을 추적합니다.         |
| [Tracereg](https://github.com/microsoft/detours/wiki/SampleTracereg) | 레지스트리 API를 통해 활동을 추적합니다.                     |
| [Traceser](https://github.com/microsoft/detours/wiki/SampleTraceser) | 직렬 포트(com1 또는 com2)를 통해 활동을 추적합니다.          |
| [Tracetcp](https://github.com/microsoft/detours/wiki/SampleTracetcp) | WinSock TCP API를 통해 활동을 추적합니다.                    |
| [Tryman](https://github.com/microsoft/detours/wiki/SampleTryman) | 32비트 및 64비트 대상 프로세스를 모두 연결하기 위해 도우미 프로세스를 사용하는 방법을 보여줍니다. |
| [Withdll](https://github.com/microsoft/detours/wiki/SampleWithdll) | <span style="color:red">대상 애플리케이션을 수정하지 않고 우회 DLL을 새 프로세스에 로드하는 방법을 보여줍니다.</span> |