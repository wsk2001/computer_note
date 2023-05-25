# Linux의 LD_PRELOAD와 유사한 Windows 기능은

<span style="color:red">Windows 보안부팅 (**UEFI**) 이 활성화 되어 있으면 사용 할 수 없다.</span>



Linux의 LD_PRELOAD와 유사한 Windows 기능은 AppInit_DLLs 레지스트리 키입니다. 이 키는 프로세스 시작 시 로드되는 DLL을 지정합니다. AppInit_DLLs 키를 사용하여 프로세스의 동작을 수정하거나 추가 기능을 제공하는 DLL을 로드할 수 있습니다.


Linux의 LD_PRELOAD와 유사한 Windows 기능은 AppInit_DLLs 레지스트리 키입니다. 이 키는 프로세스 시작 시 로드되는 DLL을 지정합니다. AppInit_DLLs 키를 사용하여 프로세스의 동작을 수정하거나 추가 기능을 제공하는 DLL을 로드할 수 있습니다.

AppInit_DLLs 키를 설정하려면 다음 단계를 따르세요.

1. 레지스트리 편집기를 엽니다.
2. 다음 위치로 이동합니다.
   - HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows
3. 오른쪽 창에서 AppInit_DLLs를 두 번 클릭합니다.
4. AppInit_DLLs 값 데이터 상자에 DLL의 경로를 입력합니다. 여러 DLL을 지정하려면 각 DLL 경로를 세미콜론(;)으로 구분합니다.
5. 확인을 클릭합니다.

AppInit_DLLs 키를 사용하여 로드된 DLL은 프로세스의 모든 스레드에서 로드됩니다. DLL은 프로세스가 시작될 때 로드되므로 프로세스의 초기 동작을 수정하는 데 사용할 수 있습니다.

AppInit_DLLs 키는 악성 코드를 로드하는 데 사용될 수 있으므로 신중하게 사용해야 합니다.



---

### AppInit_DLLs 키를 사용하여 파일 열기를 후킹하는 방법은 다음과 같습니다.

- AppInit_DLLs 키를 사용하여 로드할 DLL을 만듭니다.
- DLL에서 NtOpenFile 함수를 후킹합니다.
- NtOpenFile 함수에서 파일 열기 요청을 검사합니다.
- 파일 열기 요청이 허용되지 않으면 함수를 실패합니다.
- 파일 열기 요청이 허용되면 함수를 성공합니다.

다음은 AppInit_DLLs 키를 사용하여 파일 열기를 후킹하는 데 사용할 수 있는 예제 DLL입니다.

``` cpp
// 이 DLL은 NtOpenFile 함수를 후킹하고 파일이 특정 디렉토리로 열리는 것을 차단합니다.

#include <windows.h>

PVOID NtOpenFile_Original = NULL;

NTSTATUS WINAPI NtOpenFile_Hook( PHANDLE FileHandle,
								 ACCESS_MASK DesiredAccess,
								 POBJECT_ATTRIBUTES ObjectAttributes,
								 PIO_STATUS_BLOCK IoStatusBlock,
								 ULONG ShareAccess,
								 ULONG OpenOptions)
{
	// 파일이 특정 디렉토리에서 열려 있는지 확인하십시오.
	if (ObjectAttributes->ObjectName->Name.Buffer == "C:\Windows")
	{
		// 파일 열기를 차단합니다.
		return STATUS_ACCESS_DENIED;
	}

	// 원래 NtOpenFile 함수를 호출합니다.
	return NtOpenFile_Original(FileHandle, DesiredAccess, ObjectAttributes, IoStatusBlock, ShareAccess, OpenOptions);
}

BOOL APIENTRY DllMain(HMODULE hModule, DWORD ul_reason_for_call, LPVOID lpReserved)
{
	switch (ul_reason_for_call)
	{
		case DLL_PROCESS_ATTACH:
			// 원래 NtOpenFile 함수의 주소를 가져옵니다.
			NtOpenFile_Original = GetProcAddress(GetModuleHandle("ntdll.dll"), "NtOpenFile");
			// NtOpenFile 함수를 후킹합니다.
			if (NtOpenFile_Original)
			{
				Mhook_SetHook((PVOID*)&NtOpenFile, NtOpenFile_Hook);
			}
			break;
		case DLL_PROCESS_DETACH:
			// NtOpenFile 함수를 해제합니다.
			if (NtOpenFile_Original)
			{
				Mhook_Unhook((PVOID*)&NtOpenFile);
			}
			break;
	}
	return TRUE;
}

```



이 DLL을 AppInit_DLLs 키에 추가하려면 다음 단계를 따르세요.

1. 레지스트리 편집기를 엽니다.

2. 다음 위치로 이동합니다.

   HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows

3. 오른쪽 창에서 AppInit_DLLs를 두 번 클릭합니다.

4. AppInit_DLLs 값 데이터 상자에 DLL의 경로를 입력합니다. 이 경우 DLL의 경로는 다음과 같습니다.

   C:\Windows\System32\appinit_hook.dll

5. 확인을 클릭합니다.

이제 DLL이 프로세스 시작 시 로드됩니다.



---

### 위 과정 에서 레지스트리에 dll 을 등록  및 삭제 하는 방법은 cmd 창 에서 수행 하는 방법

레지스트리에 DLL을 등록 및 삭제하는 방법은 다음과 같다.

- DLL 등록
  - 명령 프롬프트를 관리자 권한으로 실행합니다.
  - DLL 파일이 있는 위치로 이동합니다.
  - 다음 명령을 실행합니다. regsvr32 <DLL 파일 이름>
- DLL 삭제
  - 명령 프롬프트를 관리자 권한으로 실행합니다.
  - DLL 파일이 있는 위치로 이동합니다.
  - 다음 명령을 실행합니다. regsvr32 /u <DLL 파일 이름>

예를 들어, "C:\Windows\System32"에 있는 "mydll.dll" 파일을 등록하려면 다음 명령을 실행합니다.

``` bat
regsvr32 mydll.dll
```

"C:\Windows\System32"에 있는 "mydll.dll" 파일을 삭제하려면 다음 명령을 실행합니다.

``` bat
regsvr32 /u mydll.dll
```



위 처럼 하면 dll 이 HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows 에 등록 되고,  DLL을 삭제하려면 regsvr32 명령에 /u 옵션을 사용합니다. 이렇게 하면 해당 DLL에 대한 정보가 레지스트리에서 삭제됨.



---

### windows 의 file open 함수의 종류 

Windows의 파일 열기 함수의 종류는 다음과 같습니다.

- CreateFile()
- CreateNamedPipe()
- CreateFileMapping()
- CreateFileTransacted()
- CreateFileW()
- CreateNamedPipeW()
- CreateFileMappingW()
- CreateFileTransactedW()

이러한 함수는 모두 파일을 열고 파일 핸들을 얻는 데 사용됩니다. 파일 핸들은 파일과 상호 작용하는 데 사용되는 숫자입니다. 파일 핸들을 얻은 후에는 파일을 읽고, 쓰고, 닫을 수 있습니다.

CreateFile() 함수는 가장 기본적인 파일 열기 함수입니다. 이 함수는 파일 이름, 파일 열기 모드, 파일 공유 모드, 파일 보안 속성 등을 지정하여 파일을 열 수 있습니다.

CreateNamedPipe() 함수는 파이프 파일을 열는 데 사용됩니다. 파이프 파일은 두 개의 프로세스 간에 통신하는 데 사용되는 파일입니다.

CreateFileMapping() 함수는 파일 매핑을 만드는 데 사용됩니다. 파일 매핑은 파일의 내용을 메모리에 매핑하는 것입니다. 파일 매핑을 사용하면 파일의 내용을 메모리에 직접 읽고 쓸 수 있습니다.

CreateFileTransacted() 함수는 트랜잭션 파일을 열는 데 사용됩니다. 트랜잭션 파일은 데이터를 안전하게 저장하는 데 사용되는 파일입니다.

CreateFileW() 함수는 파일을 열는 데 사용되는 Unicode 버전의 CreateFile() 함수입니다.

CreateNamedPipeW() 함수는 파이프 파일을 열는 데 사용되는 Unicode 버전의 CreateNamedPipe() 함수입니다.

CreateFileMappingW() 함수는 파일 매핑을 만드는 데 사용되는 Unicode 버전의 CreateFileMapping() 함수입니다.

CreateFileTransactedW() 함수는 트랜잭션 파일을 열는 데 사용되는 Unicode 버전의 CreateFileTransacted() 함수입니다.





---

## 보안 부팅 상태 확인

PC에서 보안 부팅의 상태를 확인하려면 다음을 수행합니다.

1. 시작으로 이동합니다.
2. 검색 창에 **msinfo32**를 입력하고 Enter를 누릅니다.
3. **시스템 정보**가 열립니다. **시스템 요약**을 선택합니다.
4. 화면 오른쪽에 있는 **BIOS 모드** 및 **보안 부팅 상태**를 확인합니다. Bios 모드에 **UEFI**가 표시되고 보안 부팅 상태에 **꺼짐**이 표시되면 보안 부팅이 비활성화된 것입니다.

