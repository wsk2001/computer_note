# DLL Injection 공부

출처: https://nameng.tistory.com/117

https://github.com/gud425/DLL_Injection 백업용 github

# DLL_Injection

> made by nam

메모장이 켜질때마다 DLL을 삽입하여 내가 원하는 작동을 하도록 실행하는 프로그램

## 소스해석

### DLL 소스

```cpp
DWORD WINAPI ThreadProc(LPVOID lparm) {
    int a=0, b=0, c=0;
    int flag = 0,return_val;
    while (1) {
        if (flag)
            break;
        for (int i = 0; i < (a * b * c) + 1; i++) {
            return_val=MessageBox(NULL, L"hacked by nam", L"LOL", MB_YESNOCANCEL|MB_ICONWARNING);
            if (return_val == IDYES)
                a++;
            else if (return_val == IDNO)
                b++;
            else if (return_val == IDCANCEL)
                c++;

            if (a == 4&&b == 2&&c == 5) {
                flag = 1;
                break;
            }
        }

    }
    return 0;
}
```

messagebox에서 나오는 버튼 3개를 각각 4 2 5번 누르면 꺼지게 동작

### 메인소스

```cpp
void register_program() {
	HKEY  m_hRegsKey;
	const WCHAR* regItemName = L"dll_injection_by_nam";
	const WCHAR* val = L"C:\\Users\\nam\\source\\repos\\dll_injection\\x64\\Debug\\dll_injection.exe";
	RegCreateKey(HKEY_LOCAL_MACHINE, L"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", &m_hRegsKey);
	RegSetValueEx(m_hRegsKey, regItemName, NULL, REG_SZ,(const byte*)val, 134);//lstrlen(val)*2
	RegCloseKey(m_hRegsKey);
}
```

SOFTWARE\Microsoft\Windows\CurrentVersion\Run 에 추가하여 컴퓨터 시작될때마다 자동으로 실행되게 만드는 함수



```cpp
DWORD32 get_pid(wstring proc_name) {

	PROCESSENTRY32 pe32;
	HANDLE hSnapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS,0);
	pe32.dwSize = sizeof(PROCESSENTRY32);
	Process32First(hSnapshot, &pe32);
	do {
		wstring num = pe32.szExeFile;
		if (!wcscmp(num.c_str(), proc_name.c_str())) {
			return pe32.th32ProcessID;
		}

	} while (Process32Next(hSnapshot, &pe32));
	return 0;
}
```

구할 프로세스의 이름을 인자로 주면 PID를 리턴하는 함수



```cpp
BOOL dll_inject(DWORD hwPID, LPCTSTR DllPath) {
	HANDLE hProcess = NULL;
	HANDLE hThread = NULL; 
	HMODULE hMod = NULL; 
	LPVOID pRemoteBuf = NULL; 
	LPTHREAD_START_ROUTINE pThreadProc; 
	hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, hwPID);
	
	DWORD dwBufSize = (DWORD)(_tcslen(DllPath) + 1) * sizeof(TCHAR);
	
	pRemoteBuf = VirtualAllocEx(hProcess, NULL, dwBufSize, MEM_COMMIT, PAGE_READWRITE);
	WriteProcessMemory(hProcess, pRemoteBuf, (LPVOID)DllPath, dwBufSize, NULL); 
	hMod = GetModuleHandle(TEXT("kernel32.dll"));
	pThreadProc = (LPTHREAD_START_ROUTINE)GetProcAddress(hMod, "LoadLibraryW");
	hThread = CreateRemoteThread(hProcess, NULL, 0, pThreadProc, pRemoteBuf, 0, NULL);
	WaitForSingleObject(hThread, INFINITE); 
	CloseHandle(hThread); 
	CloseHandle(hProcess); 
	return 1; 
}
```

DLL 인젝션의 핵심 부분이다.

1. **OpenProcess**(**dwDesiredAccess**:접근권한, **bInheritHandle**:TRUE면 핸들을 상속, FALSE면 상속X, **dwProcessId**:프로세스 식별자(PID)) 리턴값으로 대상 프로세스의 핸들을 구한다.
2. **VirtualAllocEx**(**hProcess**:인젝션할 프로세스의 핸들, **lpAddress**:할당할 주소로 NULL을 넣으면 자동, **dwSize**:할당할 메모리의 크기(byte), **flAllocationType**:할당유형-전체메모리 내에 할당하려면 MEM_COMMIT(0x1000), **flProtect**:할당된페이지에 대한 권한)
   리턴값으로 대상 프로세스에 LPVOID형의 메모리 공간을 확보해 DLL의 경로를 넣을 공간을 만든다.
3. **WriteProcessMemory**(**hProcess**:프로세스의 핸들, **lpBaseAddress**:DLL절대경로를 작성할 주소, **lpBaseAddress**:DLL의 절대경로가 작성된주소, **nSize**:Write할 바이트 크기(DLL의 절대경로의 길이보다 크게 지정), **lpNumberOfBytesWritten**:Write가 잘 되었는지 작성한 바이트를 저장하는 주소)
4. **GetModuleHandle**(TEXT("kernel32.dll"));
5. **GetProcAddress**(hMod, "LoadLibraryW");
   LoadLibrary는 kernel32.dll에 존재하므로 kernel32.dll의 핸들을 얻고 LoadLibrary의 주소를 구한다.
6. **CreateRemoteThread**(**hProcess**:프로세스의 핸들, **lpThreadAttributes**:보안속성-특별한 속성이 없다면 NULL, **dwStackSize**:스택의 초기사이즈(byte)-0을 넣으면 실행파일의 default size를 사용, **lpStartAddress**:스레드의 시작주소, **lpParamete**r:스레드가 실행할 함수의 파라미터, **dwCreationFlags** : 0을 넣으면 스레드 생성직후에 실행, **lpThreadId** : 스레드식별자(TID)를 저장하기위한 주소)





## 탐지방법

CreateRemoteThread의 인자중 lpStartAddress의 인자로 GetProcAddress로 얻어온 LoadLibrary주소가 들어가게 되는데 이부분을 탐지하면 된다.