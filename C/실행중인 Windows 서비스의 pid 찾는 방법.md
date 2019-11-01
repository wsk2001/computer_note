# 실행중인 Windows 서비스의 pid 찾는 방법

출처:  https://stackoverflow.com/questions/1774129/how-can-we-find-the-process-id-of-a-running-windows-service 

 [QueryServiceStatusEx](http://msdn.microsoft.com/en-us/library/ms684941%28VS.85%29.aspx)는 서비스가 실행중인 프로세스의 프로세스 ID를 포함하는 [SERVICE_STATUS_PROCESS](http://msdn.microsoft.com/en-us/library/ms685992%28VS.85%29.aspx)를 리턴합니다. 

 [OpenService](http://msdn.microsoft.com/en-us/library/ms684330%28VS.85%29.aspx)를 사용하여 이름에서 서비스에 대한 핸들을 얻을 수 있습니다. 

 다음은 원하는 것을 정확하게 수행하는 미니멀리스트 C++ 함수입니다. 

```c++
DWORD GetServicePid(const char* serviceName)
{
    const auto hScm = OpenSCManager(nullptr, nullptr, NULL);
    const auto hSc  = OpenService(hScm, serviceName, SERVICE_QUERY_STATUS);

    SERVICE_STATUS_PROCESS ssp = {};
    DWORD bytesNeeded = 0;
    QueryServiceStatusEx( hSc, 
                          SC_STATUS_PROCESS_INFO, 
                          reinterpret_cast<LPBYTE>(&ssp), 
                          sizeof(ssp), 
                          &bytesNeeded);

    CloseServiceHandle(hSc);
    CloseServiceHandle(hScm);

    return ssp.dwProcessId;
}
```

