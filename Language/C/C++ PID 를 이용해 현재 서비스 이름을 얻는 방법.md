# C ++ PID 를 이용해 서비스 이름을 얻는 방법

Win32 C++ How to get current application Service Name

출처: https://stackoverflow.com/questions/16654686/win32-c-how-to-get-current-application-service-name?rq=1



내 코드 (DLL)가 이미 서비스로 실행되는 응용 프로그램 내에서 실행되고 있다고 가정합니다. 서비스 이름을 찾는 방법은 무엇입니까? 프로세스 경로를 가져 와서 레지스트리에 서비스를 열거하는 것보다 더 우아한 방법을 원합니다.

GetCurrentProcessId를 사용하여 실행중인 서비스의 프로세스 ID를 얻는 것이 좋습니다. 그런 다음 EnumServiceStatusEx를 사용하여 레지스트리를 직접 사용하지 않고도 서비스를 열거하십시오.

ENUM_SERVICE_STATUS_PROCESS.ServiceStatusProcess.dwProcessId 의 프로세스 ID를 현재 프로세스 ID와 일치시키고 이름을 얻을 수 있습니다. 예를 들면 다음과 같습니다.

```c++
void enumerateServices( DWORD processId )
{
    SC_HANDLE hSCM = OpenSCManager(NULL, NULL, 
        SC_MANAGER_ENUMERATE_SERVICE | SC_MANAGER_CONNECT);


    if (hSCM == NULL)
    {
        return;
    }
    DWORD bufferSize = 0;
    DWORD requiredBufferSize = 0;
    DWORD totalServicesCount = 0;
    EnumServicesStatusEx( hSCM, 
        SC_ENUM_PROCESS_INFO,
        SERVICE_WIN32,
        SERVICE_STATE_ALL,
        nullptr, 
        bufferSize,
        &requiredBufferSize,
        &totalServicesCount,
        nullptr,
        nullptr );

    std::vector<BYTE> buffer( requiredBufferSize );
    EnumServicesStatusEx( hSCM, 
        SC_ENUM_PROCESS_INFO,
        SERVICE_WIN32,
        SERVICE_STATE_ALL,
        buffer.data(), 
        buffer.size(),
        &requiredBufferSize,
        &totalServicesCount,
        nullptr,
        nullptr );

    LPENUM_SERVICE_STATUS_PROCESS services = 
        reinterpret_cast<LPENUM_SERVICE_STATUS_PROCESS>( buffer.data() );
    for ( unsigned int i = 0; i < totalServicesCount; ++i )
    {
        ENUM_SERVICE_STATUS_PROCESS service = services[i];
        if ( service.ServiceStatusProcess.dwProcessId == processId )
        {
            // This is your service.
            std::wcout << service.lpServiceName << L"PID: " << 
                service.ServiceStatusProcess.dwProcessId << std::endl;
        }
    }

    ( void )CloseServiceHandle( hSCM );

}
```

오류 처리에 대한 일반적인 면책 조항이 적용됩니다. 버퍼 크기 등을 제대로 확인하지 않았습니다.

