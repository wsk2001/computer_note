# Python으로 Windows API를 Hooking하는방법

출처: https://www.apriorit.com/dev-blog/727-win-guide-to-hooking-windows-apis-with-python

개발자는 시스템 작동 방식을 더 잘 이해하고, 운영 체제 또는 애플리케이션의 동작을 변경하고, 악성 코드를 탐지하고, 강력한 제품을 구축하기 위해 API 후킹 방식을 적용합니다.

Windows API 후킹에 대한 대부분의 가이드와 튜토리얼은 C 또는 C++와 같은 컴파일된 언어 사용을 기반으로 하기 때문에 이러한 언어를 모르는 개발자는 이 방법에 액세스할 수 없습니다. 그러나 컴파일된 언어가 유일한 옵션은 아닙니다. Python과 같은 해석된 언어는 API 후킹에도 사용될 수 있으며 컴파일된 언어에 비해 몇 가지 장점이 있습니다.

이 기사에서는 Windows API를 연결하기 위해 Python을 선택하는 것이 가장 좋은 시기와 이유를 살펴보고 따라하기 쉬운 예제를 기반으로 후크를 설정하기 위해 이 언어를 사용하는 방법을 설명합니다. 또한 두 개의 Python 라이브러리인 Deviare 및 WinAppDbg를 API 후킹에 사용할 수 있는 방법도 보여줍니다.

### API 후킹을 위한 프로그래밍 언어 선택

API 후킹은 소프트웨어 구성 요소 간에 전달되는 API 함수 호출, 메시지 또는 이벤트를 가로채서 운영 체제(OS), 애플리케이션 또는 기타 소프트웨어 구성 요소의 동작을 변경하거나 강화하는 다양한 기술을 다룹니다. 이러한 가로채기를 처리하는 코드를 후크라고 합니다. [API 후크 설정을 위한 효과적인 DLL 주입 기술 3편](https://www.apriorit.com/dev-blog/679-windows-dll-injection-for-api-hooks)에서 언급했듯이 **Windows용 API 후킹은 DLL(동적 링크 라이브러리) 주입, 코드 주입, Win32 디버그 API 도구 세트 사용** 등 다양한 방법을 사용하여 수행할 수 있습니다.

그러나 인터넷에서 접하게 되는 대부분의 API 후킹 예제는 C 또는 C++를 사용합니다. 운이 좋으면 C# 또는 Visual Basic으로 예제를 찾을 수 있습니다.

API 후킹은 낮은 수준의 기술이므로 C 및 C++와 같은 컴파일된 언어가 유일한 선택인 것처럼 보일 수 있습니다. 이것이 사실이 아닌 이유와 API 후킹에 interpreted 언어를 사용할 수 있는 경우를 설명하기 전에 두 프로그래밍 언어 그룹 간의 주요 차이점에 대해 간단히 기억해 보겠습니다.

컴파일 언어는 컴파일러를 사용하여 구현되는 프로그래밍 언어입니다. 컴파일러는 특정 프로그래밍 언어로 작성된 명령문을 다른 언어, 일반적으로 기계어 코드로 번역하는 프로그램입니다. C, C++ 및 Go는 컴파일된 언어의 일반적인 예입니다.

해석된(interpreted ) 언어는 인터프리터를 사용하여 구현되며 실행 전에 소스 코드를 기계어 코드로 직접 컴파일하지 않는 프로그래밍 언어입니다. 인터프리터는 프로그램을 실행하여 각 명령문을 하나 이상의 서브루틴 시퀀스로 해석한 다음 기계어 코드로 해석합니다. Python과 JavaScript는 해석된 언어의 일반적인 예입니다.

![1 compiled languages vs interpreted languages](.\Images\1-compiled-languages-vs-interpreted-languages.jpg)

그림 1. 컴파일된 프로그래밍 언어와 해석된 프로그래밍 언어의 비교

С/С++ 작업이 Python 프로그래밍과 어떻게 다른지 비교해 보겠습니다. 일부 응용 프로그램에 대한 작은 유틸리티나 패치를 작성하고 싶다고 가정해 보겠습니다. С 또는 С++를 사용하는 경우 환경을 준비하는 데 추가 시간을 소비해야 하며 이를 위해서는 다음이 필요합니다.

- IDE 설치
- API 후킹을 위한 프레임워크 다운로드 및 구축
- 표준 라이브러리가 필요한 모든 유틸리티를 제공하지 않을 수 있으므로 타사 라이브러리를 설치하고 빌드하세요.

모든 것을 설치한 후에도 첫 번째 시도에서 프로젝트를 빌드할 수 있는 가능성은 여전히 낮습니다. 그 이유는 C/C++ 개발자가 DLL 파일 누락이나 부적절한 라이브러리 버전과 같은 오류를 찾는 것이 매우 일반적이기 때문입니다.

반면 Python에는 종속성을 처리하기 위한 pip 및 가상 환경과 같은 편리한 도구가 있습니다. 또한 빠른 개발이 가능하고, 유용한 타사 라이브러리가 다양하며, 편리한 환경 구성을 제공합니다. 따라서 C나 C++를 모른다면 Windows API 후킹의 대안으로 Python을 확실히 사용할 수 있습니다.

Python으로 API 함수를 연결하는 방법을 자세히 알아보기 전에 이 언어가 어떻게 작동하는지 살펴보겠습니다.

### 파이썬 작동 방식

Python은 가장 널리 사용되는 대화형 프로그래밍 언어 중 하나입니다. 가장 큰 장점은 다른 프로그래밍 언어에 필요한 것보다 적은 코드로 프로그램을 작성할 수 있는 간단한 구문입니다. 그리고 구문이 읽기 쉽고 영어와 유사하기 때문에 모든 팀원이 쉽게 이해할 수 있습니다.

Python은 다음 플랫폼에서 작동합니다.

- Windows
- Linux
- macOS

Python 코드를 작성한 후 바로 실행할 수 있습니다. 그러나 Python은 해석된 언어이기 때문에 Python 가상 머신이라는 해석기인 실행기가 필요합니다.

> 참고: Python 가상 머신은 Python 코드를 실행하는 데 필수적인 구성 요소입니다.

Python으로 작성된 프로그램은 다음과 같이 작동합니다.

- Python 소스 코드는 컴파일러로 전송되어 Python 바이트코드라는 OS 중립적 중간 파일 형식을 생성합니다.
- Python 가상 머신은 Python 바이트코드 파일을 해석하여 프로세서에 대한 머신별 명령을 생성합니다.



![2 Python program execution flow](.\Images\2-Python_program_execution_flow.jpg)

그림 2. Python 프로그램 실행 흐름

이제 Python 코드를 애플리케이션 프로세스에 삽입하는 방법을 살펴보겠습니다.



### Python 코드를 프로세스에 삽입하는 방법

[이전 글](https://www.apriorit.com/dev-blog/679-windows-dll-injection-for-api-hooks)에서 썼던 것처럼, API 함수를 후킹하려면 대상 프로세스의 메모리 주소 공간 내부에 후킹 코드를 삽입해야 합니다. 후킹 코드가 Python으로 작성된 경우 대상 프로세스가 이를 실행할 수 있어야 합니다.

그러나 대상 애플리케이션은 Python, 해당 가상 머신 또는 해석된 언어에 대해 전혀 알지 못할 수 있습니다. Python 코드를 대상 애플리케이션 내에서 실행하려면 Python 가상 머신을 여기에 삽입해야 할 수도 있습니다.

Python 가상 머신을 대상 프로세스에서 실행하려면 다음 몇 단계만 수행하면 됩니다.

- Python 코드를 실행할 수 있도록 애플리케이션이 python.dll 파일을 로드하도록 합니다. 
- 애플리케이션이 Py_Initialize 함수를 호출하여 Python 인터프리터를 초기화하도록 합니다.

> 참고: 다음 예제('API 후크용 Python 라이브러리' 섹션 이전)는 모두 Python 3.8.5가 설치된 Windows 7 x32에서 수행됩니다.

ctypes 및 mayhem 라이브러리 모듈을 사용하여 Python에서 이 두 단계를 수행하는 방법을 살펴보겠습니다.

``` python
import ctypes.util
  
# Resolving python.dll path in order to inject it in the target process
python_library = 'python{}{}.dll'.format(sys.version_info.major, sys.version_info.minor)
python_library = ctypes.util.find_library(python_library)
```

이제 대상 프로세스에 phyton.dll 라이브러리를 로드하고 해당 프로세스가 Py_InitializeEx 함수를 호출하도록 합니다.

``` python
from mayhem import utilities
from mayhem.proc.windows import WindowsProcess
from mayhem.windll import kernel32
  
# Resolving Py_InitializeEx address in the remote process
python_library_remote = process.load_library(python_library)
python_library_local  = kernel32.GetModuleHandleW(python_library)
  
initializer = python_library_remote + (
        kernel32.GetProcAddress(python_library_local, b'Py_InitializeEx') - python_library_local)
  
# Calling Py_InitializeEx(0) in the remote process
process.join_thread(process.start_thread(initializer, 0))
```

이제 대상 프로세스는 모든 Python 코드를 실행할 수 있습니다. 코드를 실행하기 위해 python.dll 파일의 [PyRun_SimpleString](https://docs.python.org/3/c-api/veryhigh.html) 함수를 사용하겠습니다. Python 코드를 사용하여 별도의 스레드에서 이 함수를 호출해 보겠습니다.

``` python
# PyRun_SimpleString uses utf-8 encoded strings
injected_code = source_code.encode('utf-8') + b'\x00'
  
# Allocate some memory in the remote process to place our Python code there
alloced_address = process.allocate(
        size=utilities.align_up(len(injected_code)), permissions='PAGE_READWRITE')
          
# Put utf-8 encoded Python source code into the allocated memory
process.write_memory(alloced_address, injected_code)
  
# Run PyRun_SimpleString in a new thread in the remote process
process.start_thread(code_runner, alloced_address)
```

이 코드를 재사용 가능하게 만들기 위해 Injector 클래스에 넣어 대상 프로세스에서 임의의 Python 코드를 실행해 보겠습니다.

``` python
class Injector:
    '''Class that allows running arbitrary Python code in any process'''
  
    def initialize(self):
        '''Calls Py_InitializeEx(0) in the remote process'''
        ...
      
    def finalize(self):
        '''Calls Py_FinalizeEx(0) in the remote process'''
        ...
  
    def run_code(self, source_code, should_wait=False):
        '''Runs the Python source code in the remote process in a separate thread'''
        ...
```

이제 기본 사항을 알았으니 Python 코드 삽입의 실제 예를 살펴보겠습니다.



### Python 코드 삽입의 예

이 예에서는 notepad.exe 프로세스에 일부 Python 코드를 삽입하여 일부 텍스트가 포함된 메시지 상자를 표시합니다. python.dll 주입을 담당하는 코드는 다음과 같습니다.

``` python
injector = Injector(pid=5815) # notepad.exe PID
injector.initialize()
```

Python.dll이 원격 프로세스에 삽입됩니다. [Sysinternals 도구의 Process Explorer 유틸리티](https://docs.microsoft.com/en-us/sysinternals/downloads/process-explorer)를 사용하여 notepad.exe 프로세스 목록에 python.dll이 있는지 확인해 보겠습니다.

![3 presence of python dll file in list of notepad exe processes](.\Images\3-presence-of-python-dll-file-in-list-of-notepad-exe-processes.png)

그림 3. notepad.exe 프로세스 목록에 python.dll 파일이 있음

notepad.exe 프로세스에 python.dll이 로드된 것을 확인할 수 있습니다. 이제 메모장에서 'Hello from Python'이라는 텍스트가 포함된 메시지 상자를 표시하도록 해 보겠습니다.

``` python
code = r'''
import ctypes
ctypes.windll.user32.MessageBoxA(0, b"Hello from Python", b"Hello from Python", 0)
'''
  
injector.run_code(code, should_wait=True)
```

Python 코드를 notepad.exe에 삽입하면 메모장에 다음 메시지 상자가 표시됩니다.

![4 message box that opens in notepad after python code injection](.\Images\4-message-box-that-opens-in-notepad-after-python-code-injection.png)

그림 4. Python 코드 삽입 후 메모장에서 열리는 메시지 상자

Process Explorer는 Python 코드를 실행하는 notepad.exe 프로세스에 스레드가 있음을 보여줍니다.

![5 the stack for the python dll thread that is running python code](.\Images\5-the-stack-for-the-python-dll-thread-that-is-running-python-code.png)

그림 5. Python 코드를 실행하는 python.dll 스레드의 스택

그러나 위에 설명된 예제는 Python 코드 삽입에 사용할 수 있는 유일한 옵션은 아닙니다. Import Address Table 구조를 이용하여 API Hooking을 수행할 수 있는 방법을 살펴보겠습니다.



### Import Address Table을 사용한 Windows API Hooking

Windows [Portable Executable](https://en.wikipedia.org/wiki/Portable_Executable) 형식에는 IAT(가져오기 주소 테이블)라는 구조가 포함되어 있습니다. 이 구조에는 특정 응용 프로그램에서 사용하는 라이브러리 이름이 포함되어 있습니다. 각 라이브러리에 대해 IAT에는 이 라이브러리에서 가져온 기능 목록도 포함되어 있습니다. 따라서 애플리케이션을 시작할 때 로더는 로드할 라이브러리와 이러한 라이브러리의 함수 주소를 연결하는 방법을 알 수 있습니다.

IAT에는 실행 파일이 작업을 수행하는 데 중요한 정보에 대한 포인터가 포함되어 있습니다.

- 예상되는 기능을 제공하기 위해 실행 파일이 의존하는 DLL 목록
- 특정 시점에 바이너리에 의해 호출될 수 있는 DLL의 함수 이름 및 해당 주소 목록

![6 how to get access to the import address table](.\Images\6-how-to-get-access-to-the-import-address-table.jpg)

그림 6. Import Address Table에 액세스하는 방법

대상 함수의 주소를 다른 함수 주소로 덮어써서 IAT에 지정된 함수 포인터를 후크할 수 있습니다. 현재 모듈의 IAT에 액세스하기 위해 [ImageDirectoryEntryToData](https://docs.microsoft.com/en-us/windows/win32/api/dbghelp/nf-dbghelp-imagedirectoryentrytodata)함수를 사용합니다.

``` python
import ctypes
import ctypes.wintypes
  
# Define helper functions to manipulate the IAT of the module
  
# https://docs.microsoft.com/en-us/windows/win32/api/dbghelp/nf-dbghelp-imagedirectoryentrytodata
_dbghelp                                    = ctypes.windll.dbghelp
_dbghelp.ImageDirectoryEntryToData.restype  = ctypes.wintypes.LPVOID
_dbghelp.ImageDirectoryEntryToData.argtypes = [
    ctypes.wintypes.LPVOID,
    ctypes.wintypes.BOOL  ,
    ctypes.wintypes.USHORT,
    ctypes.wintypes.PULONG
]
  
ImageDirectoryEntryToData = _dbghelp.ImageDirectoryEntryToData
  
# https://docs.microsoft.com/en-us/windows/win32/api/libloaderapi/nf-libloaderapi-getmodulehandlea
_kernel32                           = ctypes.windll.kernel32
_kernel32.GetModuleHandleA.restype  = ctypes.wintypes.HMODULE
_kernel32.GetModuleHandleA.argtypes = [
    ctypes.wintypes.LPCSTR
]
  
GetModuleHandleA = _kernel32.GetModuleHandleA
  
# https://docs.microsoft.com/en-us/windows/win32/api/libloaderapi/nf-libloaderapi-getprocaddress
_kernel32                         = ctypes.windll.kernel32
_kernel32.GetProcAddress.restype  = ctypes.wintypes.HMODULE
_kernel32.GetProcAddress.argtypes = [
    ctypes.wintypes.HMODULE,
    ctypes.wintypes.LPCSTR
]
  
GetProcAddress = _kernel32.GetProcAddress
  
# https://docs.microsoft.com/en-us/windows/win32/api/processthreadsapi/nf-processthreadsapi-getcurrentprocess
_kernel32                            = ctypes.windll.kernel32
_kernel32.GetCurrentProcess.restype  = ctypes.wintypes.HANDLE
_kernel32.GetCurrentProcess.argtypes = []
  
GetCurrentProcess = _kernel32.GetCurrentProcess
  
# https://docs.microsoft.com/en-us/windows/win32/api/memoryapi/nf-memoryapi-writeprocessmemory
_kernel32                             = ctypes.windll.kernel32
_kernel32.WriteProcessMemory.restype  = ctypes.wintypes.BOOL
_kernel32.WriteProcessMemory.argtypes = [
    ctypes.wintypes.HANDLE       ,
    ctypes.wintypes.LPVOID       ,
    ctypes.wintypes.LPCVOID      ,
    ctypes.c_size_t              ,
    ctypes.POINTER(ctypes.c_size_t)
]
  
# Define helper structures to manipulate the IAT of the module
class IMAGE_IMPORT_DESCRIPTOR_START(ctypes.Union):
    fields = [
        ('Characteristics'   , ctypes.wintypes.DWORD),
        ('OriginalFirstThunk', ctypes.wintypes.DWORD),
    ]
  
class IMAGE_IMPORT_DESCRIPTOR(ctypes.Structure):
    anonymous = ['DUMMY']
      
    fields = [
        ('DUMMY'         , IMAGE_IMPORT_DESCRIPTOR_START),
        ('TimeDateStamp' , ctypes.wintypes.DWORD)      ,
        ('ForwarderChain', ctypes.wintypes.DWORD)      ,
        ('Name'          , ctypes.wintypes.DWORD)      ,
        ('FirstThunk'    , ctypes.wintypes.DWORD)      ,
    ]
  
PIMAGE_IMPORT_DESCRIPTOR = ctypes.POINTER(IMAGE_IMPORT_DESCRIPTOR)
  
# Get Image import descriptor for the current module
current_module = GetModuleHandleA(None)
  
size              = ctypes.wintypes.ULONG()
import_descriptor = ctypes.cast(
    ImageDirectoryEntryToData(current_module, True, IMAGE_DIRECTORY_ENTRY_IMPORT, ctypes.byref(size)), PIMAGE_IMPORT_DESCRIPTOR)
```

IMAGE_IMPORT_DESCRIPTOR 구조에는 작업에 필요한 모든 가져온 모듈(현재 모듈에서 사용됨)에 대한 정보가 포함되어 있습니다. 가져온 모든 모듈에는 현재 프로세스에서 가져온 함수 목록이 있습니다. 가져온 각 함수는 x32 및 x64 시스템에 대해 다른 IMAGE_THUNK_DATA 구조로 정의됩니다. 두 가지 옵션을 모두 살펴보겠습니다.

``` python
class IMAGE_THUNK_DATA32(ctypes.Union):
    fields = [
        ('ForwarderString', ctypes.wintypes.DWORD),
        ('Function'       , ctypes.wintypes.DWORD),     # Contains address of the imported function - that is to be replaced by our handler
        ('Ordinal'        , ctypes.wintypes.DWORD),
        ('AddressOfData'  , ctypes.wintypes.DWORD),
]
  
class IMAGE_THUNK_DATA64(ctypes.Union):
    fields = [
        ('ForwarderString', ctypes.c_ulonglong),
        ('Function'       , ctypes.c_ulonglong),        # Contains address of the imported function - that is to be replaced by our handler
        ('Ordinal'        , ctypes.c_ulonglong),
        ('AddressOfData'  , ctypes.c_ulonglong),
]
  
IMAGE_THUNK_DATA = IMAGE_THUNK_DATA32 \
    if ctypes.sizeof(ctypes.c_voidp) == 4 else IMAGE_THUNK_DATA64
  
PIMAGE_THUNK_DATA = ctypes.POINTER(IMAGE_THUNK_DATA)
```

함수를 연결하려면 다음 단계를 수행해야 합니다.
- 대상 모듈의 가져오기 설명자를 찾습니다.
- 대상 함수를 내보내는 모듈의 가져오기 설명자를 찾습니다.
- IAT의 대상 함수 주소를 대신 호출하려는 함수의 주소로 바꿉니다(후크 설정).

예를 들어 [CreateFileW](https://docs.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-createfilew)함수를 연결하는 방법을 살펴보겠습니다.

``` python
...
  
  
# Real address of CreateFileW - address to be searched for in the IAT
real_CreateFileW = GetProcAddress(GetModuleHandleA(b'kernel32'), b'CreateFileW')
  
  
# Our handler of CreateFileW
@ctypes.WINFUNCTYPE(ctypes.c_void_p, ctypes.c_void_p, ctypes.wintypes.DWORD, ctypes.wintypes.DWORD, ctypes.c_void_p, ctypes.wintypes.DWORD, ctypes.wintypes.DWORD, ctypes.c_void_p)
def CreateFileW_handler(*args):
    ctypes.windll.user32.MessageBoxW(0, "Hook!!!!", "Hook!!!!", 0)
    real_CreateFileW(*args)
  
  
while import_descriptor.contents.Name:
    module_name_offset = current_module + import_descriptor.contents.Name
  
    # CreateFileW is in kernel32.dll
    if ctypes.cast(module_name_offset, ctypes.wintypes.LPSTR).value.lower() == b'kernel32.dll':
            replace_function_address(import_descriptor, real_CreateFileW, ctypes.addressof(CreateFileW_handler))
  
    # Move to the next imported module
    import_descriptor = ctypes.cast(ctypes.cast(
        import_descriptor, ctypes.wintypes.LPVOID).value + ctypes.sizeof(IMAGE_IMPORT_DESCRIPTOR), PIMAGE_IMPORT_DESCRIPTOR)
```

보시다시피, replacement_function_address 함수에서 주소를 전환합니다. 매우 간단합니다. kernel32.dll 파일에서 가져온 모든 함수를 살펴보고 필요한 함수의 주소를 찾으면 이를 WriteProcessMemory 함수로 바꿉니다.

``` python
def replace_function_address(import_descriptor, function_address_to_replace, function_address_new_handler):
    import_thunk = ctypes.cast(
        current_module + import_descriptor.contents.FirstThunk, PIMAGE_THUNK_DATA)
  
    while import_thunk.contents.Function:
        # If we found the function to be hooked
        if ctypes.cast(import_thunk.contents.Function, ctypes.wintypes.LPVOID).value == ctypes.cast(function_address_to_replace, ctypes.wintypes.LPVOID).value:
            # Where to write new function address
            imported_function_location = ctypes.pointer(ctypes.wintypes.DWORD.from_buffer(import_thunk.contents, IMAGE_THUNK_DATA.Function.offset))
  
            # Replace the entry with the new address
            bytes_written = ctypes.wintypes.DWORD()
            WriteProcessMemory(GetCurrentProcess(),
                    imported_function_location, function_address_new_handler, ctypes.sizeof(ctypes.c_void_p), ctypes.byref(bytes_written))
  
            return       
  
        # Move to the next imported function
        import_thunk = ctypes.cast(
            ctypes.cast(import_thunk, ctypes.wintypes.LPVOID).value + ctypes.sizeof(IMAGE_THUNK_DATA), PIMAGE_THUNK_DATA)
```

이것이 IAT 구조의 도움으로 API 기능을 연결하는 방법입니다. 그러나 Python에서 API 함수를 연결하려면 다양한 라이브러리를 사용해야 합니다. 다음 섹션에서는 API 후크 설정에 도움이 될 수 있는 두 가지 Python 라이브러리인 Deviare 및 WinAppDbg에 대해 설명합니다.

Linux 드라이버 개발에도 관심이 있다면 [Linux WiFi 드라이버 프로토타입](https://www.apriorit.com/dev-blog/645-lin-linux-wi-fi-driver-tutorial-how-to-write-simple-linux-wireless-driver-prototype) 설계 방법에 대한 기사를 확인하세요.



### API 후킹을 위한 Python 라이브러리

이전 섹션에서는 모든 내부 구조가 거의 처음부터 정의되어야 하기 때문에 동적 언어가 코드 삽입 및 가져오기 테이블 수정과 같은 저수준 개발 작업에 최선의 선택이 아닐 수 있다는 점을 논의했습니다. 그러나 Python에는 다양한 개발 작업을 크게 단순화할 수 있는 유용한 타사 라이브러리가 많이 있습니다.

이 섹션에서는 WinAPI 함수 후킹 프로세스를 쉽게 할 수 있는 두 가지 Python 라이브러리를 살펴봅니다.

참고: WinAppDbg 라이브러리는 Python 2에서만 작동하므로 다음 예제는 모두 Python 2.7.16이 설치된 Windows 7 x32에서 수행됩니다.

![two libraries to perform api hooking](.\Images\two-libraries-to-perform-api-hooking.jpg)

#### Deviare

Deviare는 임의의 Win32 함수, COM 개체 및 기호가 프로그램 데이터베이스(PDB)에 있는 함수를 계측하기 위한 전문 오픈 소스 후킹 라이브러리입니다.

이 라이브러리의 가장 큰 장점은 COM(구성 요소 개체 모델) 구성 요소로 구현되므로 C/C++, VB, C#, Delphi, Python 등 COM을 지원하는 모든 프로그래밍 언어와 통합될 수 있다는 것입니다.

모든 후킹은 Deviare COM DLL 파일에 포함된 특수 Spy Manager 클래스를 통해 수행됩니다. 이 클래스를 사용하여 프로세스 생성 및 종료 또는 동적 라이브러리 로드와 같은 다양한 시스템 이벤트에 대한 후크를 설정하고 콜백을 설정할 수 있습니다.

``` python
import win32com.client
  
class SpyManagerEvents:
    '''
    This is the event handler class
    '''
  
    def OnLoadLibraryCall(self, process, library_name, module):
        '''
        This function is called when a new DLL is loaded
        '''
  
    def OnProcessStarted(self, process):
        '''
        This function is called when starting a new process
        '''
  
    def OnThreadStarted(self):
        '''
        This function is called when the process creates a new thread
        '''
    ...
  
# Inside main
spyManager = win32com.client.DispatchWithEvents('DeviareCOM.NktSpyMgr', SpyManagerEvents)
spyManager.Initialize()
  
hook = spyManager.CreateHook(SOME_FUNCTION_NAME, 0)
hook.Attach(PID, True)
hook.Hook(True)
```

함수를 연결하려면 다음 세 단계만 수행하면 됩니다.

1. 후크할 함수의 이름과 선택적 후크 플래그를 제공하여 후크 인스턴스를 생성합니다. 모든 함수 이름이 “[dll]![함수 이름]” 형식인지 확인하세요. 예: “kernel32.dll!CreateFileW”.

   ``` python
   # HRESULT INktSpyMgr::CreateHook(VARIANT func, LONG hookFlags, INktHook** ppHook);
     
   spyManager.CreateHook(SOME_FUNCTION_NAME, HOOK_FLAG)
   ```

   

2. 대상 프로세스에 후크를 연결합니다.

   ``` python
   # HRESULT INktHook::Attach(VARIANT procOrId, VARIANT_BOOL sync);
     
   hook.Attach(PID, True)
   ```

   

3. 연결된 모든 프로세스에서 후크를 활성화합니다.

   ``` python
   # HRESULT INktHook::Hook(VARIANT_BOOL sync);
     
   hook.Hook(True)
   ```

   

#### Deviare 라이브러리를 사용한 API 후킹의 예

Internet Explorer용 간단한 후크를 작성하고 www.google.com으로 향하는 모든 요청을 www.bing.com으로 리디렉션해 보겠습니다.

Internet Explorer는 InternetConnectW 함수를 사용하여 HTTP 세션을 엽니다.

``` python
HINTERNET InternetConnectW(
    __in HINTERNET hInternet,
    __in LPCWSTR lpszServerName,
    __in INTERNET_PORT nServerPort,
    __in_opt LPCWSTR lpszUserName,
    __in_opt LPCWSTR lpszPassword,
    __in DWORD dwService,
    __in DWORD dwFlags,
    __in_opt DWORD_PTR dwContext
);
```

Deviare 라이브러리를 사용하여 InternetConnectW 함수를 연결하고 두 번째 매개변수를 www.bing.com으로 바꾸겠습니다.

``` python
import ctypes
import psutil
import win32com.client
  
class SpyManagerEvents:
  
    def OnFunctionCalled(self, nktHookAsPyIDispatch, nktProcessAsPyIDispatch, nktHookCallInfoAsPyIDispatch):
          
        nktHookCallInfo = win32com.client.Dispatch(nktHookCallInfoAsPyIDispatch)
        nktParamsEnum   = nktHookCallInfo.Params()
          
        if nktHookCallInfo.IsPreCall:
            # Parameter indexes start with 0
            url = nktParamsEnum.GetAt(1)
  
            if url.Value.lower() == 'www.google.com':
                url.Value = 'www.bing.com'
  
def main():
      
    spyManager = win32com.client.DispatchWithEvents('DeviareCOM.NktSpyMgr', SpyManagerEvents)
    spyManager.Initialize()
  
    for process in psutil.process_iter(['pid', 'name']):
        if process.info['name'].lower() == 'iexplore.exe':
            # To auto hook child processes
            hook = spyManager.CreateHook('Wininet.dll!InternetConnectW',
                            flgAutoHookChildProcess | flgRestrictAutoHookToSameExecutable | flgAutoHookActive)
            hook.Attach(process.info['pid'], True)
            hook.Hook(True)
  
    MessageBox = ctypes.windll.user32.MessageBoxA
    MessageBox(None, 'Press OK to end the demo.', 'Deviare Python Demo', 0)
```

이제 스크립트를 실행하고 Google에 액세스해 보겠습니다. 주소 필드에 Google 검색 엔진의 주소가 포함되어 있어도 브라우저가 Bing으로 리디렉션되는 것을 볼 수 있습니다.

![7 redirection from google to bing](.\Images\7-redirection-from-google-to-bing.png)

그림 7. Google에서 Bing으로 리디렉션

Google에 대한 액세스가 요청될 때 일종의 경고를 활성화할 수도 있습니다. google.com에 대한 액세스가 금지되어 있다는 메시지 상자를 표시하도록 스크립트를 약간 변경해 보겠습니다.

``` python
...
  
class SpyManagerEvents:
      
    def OnFunctionCalled(self, nktHookAsPyIDispatch, nktProcessAsPyIDispatch, nktHookCallInfoAsPyIDispatch):
          
        nktHookCallInfo = win32com.client.Dispatch(nktHookCallInfoAsPyIDispatch)
        nktProcess      = win32com.client.Dispatch(nktProcessAsPyIDispatch)
        nktParamsEnum   = nktHookCallInfo.Params()
          
        if nktHookCallInfo.IsPreCall:
            # Parameter indexes start with 0
            url = nktParamsEnum.GetAt(1)
  
            if 'www.google.com' in url.Value.lower():
                # Skip the call to the original InternetConnectW
                nktHookCallInfo.SkipCall()
  
                # Return an error to the caller
                if nktProcess.PlatformBits == 64:
                    nktHookCallInfo.Result().LongLongVal = 0
                else:
                    nktHookCallInfo.Result().LongVal = 0
                      
                nktHookCallInfo.LastError = 5
  
                # Show a message box saying that the operation is not allowed
                MessageBox = ctypes.windll.user32.MessageBoxA
                MessageBox(None, 'Access to google.com is forbidden', 'Warning', 0)
  
...
```

이제 google.com에 액세스하려고 하면 다음 메시지가 표시됩니다.

![8 a message saying that access to google is forbidden](.\Images\8-a-message-saying-that-access-to-google-is-forbidden.png)

그림 8. google.com에 대한 접근이 금지되어 있다는 메시지

API 후킹과 관련된 다양한 시나리오에서 Deviare 라이브러리를 사용할 수 있습니다. C++, C#, Python의 인터페이스가 거의 동일하므로 다양한 프로그래밍 언어로 사용하기 쉽습니다.



#### WinAppDbg

Python을 사용한 Windows API 연결에 사용할 수 있는 또 다른 라이브러리인 WinAppDbg를 살펴보겠습니다.

사용을 시작하려면 공식 웹사이트에서 직접 WinAppDbg 라이브러리를 다운로드하고 .msi 파일을 실행하여 설치하세요.

> 참고: 현재 WinAppDbg 라이브러리는 Python 2.7에서만 사용할 수 있습니다.

WinAppDbg는 전체 운영 체제는 물론 특정 프로세스(시스템, 프로세스, 스레드, 모듈 및 창)를 모니터링하는 데 사용할 수 있는 몇 가지 유형을 노출합니다. 각각은 시스템의 프로세스, 스레드 및 DLL 모듈의 스냅샷 역할을 합니다.

모든 후킹은 Debug 클래스를 통해 수행됩니다. 사용자 정의 이벤트 핸들러를 Debug 객체에 전달하여 디버깅 이벤트에 대한 알림을 받을 수 있습니다. 실행 중 특정 이벤트가 발생하면 EventHandler 클래스의 해당 메소드가 자동으로 호출됩니다.

해당 메서드 내에서 해당 이벤트를 처리하는 코드를 작성할 수 있습니다.

``` python
import winappdbg
  
class DebugEvents(winappdbg.EventHandler):
    '''
    This is the event handler class
    '''
  
    def load_dll(self, event):
        '''
        This function is called when a new DLL is loaded
        '''
  
    def create_process(self, event):
        '''
        This function is called when starting a new process
        '''
  
    def create_thread(self, event):
        '''
        This function is called when the process creates a new thread
        '''
    ...
  
# Inside main
debug = winappdbg.Debug(DebugEvents())
debug.loop()
```

Debug 개체의 Hook_function 메서드는 대상 함수의 시작 부분에 코드 중단점을 설정하고 두 개의 콜백(함수에 들어갈 때 하나, 함수에서 반환할 때 하나)을 설정할 수 있습니다.

CreateFile 함수에 대해 Hook_function 메서드가 어떻게 작동하는지 살펴보겠습니다.

``` python
from winappdbg.win32 import PVOID, DWORD, HANDLE
  
class DebugEvents(winappdbg.EventHandler):
    '''
    This is the event handler class
    '''
  
    def load_dll(self, event):
          
        module = event.get_module()
  
        if module.match_name('kernel32.dll'):
  
            # Resolve function addresses
            address_CreateFileA = module.resolve('CreateFileA')
            address_CreateFileW = module.resolve('CreateFileW')
  
            
            signature_CreateFileA = (PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE)
            signature_CreateFileW = (PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE)
  
            # Hook function(pid, address, preCB, postCB, paramCount, signature)
            event.debug.hook_function(event.get_pid(), address_CreateFileA, preCB=pre_CreateFileA, signature=signature_CreateFileA)
            event.debug.hook_function(event.get_pid(), address_CreateFileW, preCB=pre_CreateFileW, signature=signature_CreateFileW)
  
            # Another way of setting up hooks without a signature
  
            # event.debug.hook_function(event.get_pid(), address_CreateFileA, preCB=pre_CreateFileA, paramCount=7)
            # event.debug.hook_function(event.get_pid(), address_CreateFileW, preCB=pre_CreateFileW, paramCount=7)
              
...
```

위의 코드에서 어떤 일이 일어나는지 자세히 설명하겠습니다.

- load_dll 함수 내에서 kernel32.dll 파일을 로드하고 있는지 확인합니다. 그렇다면 대상 프로세스에서 내보낸 함수의 주소를 반환하는 CreateFileA 및 CreateFileW 함수에 대해 module.resolve() 메서드를 호출합니다.
- 그런 다음 후크된 각 함수에 대한 서명을 만듭니다. 두 CreateFile 함수에 대한 서명은 PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE입니다. 모든 포인터에 PVOID 서명을 사용하는 것이 중요합니다. 그렇지 않으면 WinAppDbg 문서에 따르면 ctypes 함수 라이브러리가 프로세스를 중단시킬 수 있습니다.
- 다음으로, event.debug.hook_function을 사용하여 각 함수를 연결합니다. 가장 중요한 매개변수는 preCB와 postCB입니다. 이는 실제 WinAPI 호출 전과 호출 직후에 각각 호출될 콜백 함수를 정의합니다. 이러한 함수에는 다음과 같은 프로토타입이 있습니다.

``` python
def pre_CreateFileA(event, return_address, lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile):
    ...
  
def pre_CreateFileW(event, return_address, lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile):
    ...
  
def post_CreateFileA(event, return_value):
    ...
  
def post_CreateFileW(event, return_value):
    ...
```

이 간단한 후크를 만드는 데는 많은 코드가 필요했습니다. 그러나 다행스럽게도 WinAppDbg 라이브러리는 apiHooks 클래스를 통해 후크를 설정하는 더 쉬운 방법을 제공합니다.

``` python
from winappdbg.win32 import PVOID, DWORD, HANDLE
  
class DebugEvents(winappdbg.EventHandler):
    '''
    This is the event handler class
    '''
  
    apiHooks = {
  
        # Hooks for the kernel32 library
        'kernel32.dll': [
  
            # Function     Signature
            ('CreateFileA', (PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE)),
            ('CreateFileW', (PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE)),
  
            # Can also pass parameter count
            # ('CreateFileA', 7),
            # ('CreateFileW', 7),
        ],
    }
  
    # Now we can simply define a method for each hooked API
    # 'pre_'  methods are called when entering the hooked function
    # 'post_' methods are called when returning from the hooked function
  
    ...
```

#### WinAppDbg 라이브러리를 사용한 API 후킹의 예

이것이 실제로 어떻게 작동하는지 확인하기 위해 WinAppDbg 라이브러리를 사용하여 간단한 후크의 예를 작성해 보겠습니다.

이 예제는 상당히 인위적이지만 라이브러리 사용 방법을 보여주기에는 충분합니다. 첨부파일을 다운로드하기 위한 Skype의 대상 폴더를 변경해 보겠습니다. 먼저 Sysinternals 도구의 프로세스 모니터를 사용하여 Skype가 파일을 다운로드하고 저장하는 방법을 살펴보겠습니다.

![9 how skype saves files](.\Images\9-how-skype-saves-files.png)

그림 9. Skype가 파일을 저장하는 방법

Skype가 CREATE_ALWAYS 속성과 함께 CreateFileW 함수를 사용하여 다운로드한 파일을 저장하는 것을 볼 수 있습니다. 따라서 대상 폴더를 변경하려면 skype.exe 프로세스에서 CreateFileW 함수를 연결하면 됩니다.

``` python
import winappdbg
import os
  
SKYPE_RECEIVED_FILES_FOLDER                = r'C:\SkypeDownload'
SKYPE_REDIRECTED_FOLDER_FOR_RECEIVED_FILES = r'C:\SomeNewFolder'
SKYPE_PROCESS_NAME                         = 'skype.exe'
  
class DebugEvents(winappdbg.EventHandler):
    ...
  
def main():
      
    # Create a Debug object. We don't want the process to be terminated when the debugger has stopped.
    debug = winappdbg.Debug(DebugEvents(), bKillOnExit=False)
  
    try:
  
        # Look up currently running processes
        debug.system.scan_processes()
  
        # For all processes that match the requested filename...
        for process, name in debug.system.find_processes_by_filename(SKYPE_PROCESS_NAME):
            print 'Attaching to PID: {} Name: {}'.format(process.get_pid(), name)
  
            # Attach to the process
            debug.attach(process.get_pid())
  
        # Wait for all debuggees to finish
        debug.loop()
          
    finally:
        debug.stop()
  
if __name__ == '__main__':
    main()
```

위 코드에서는 실행 중인 모든 skype.exe 인스턴스를 찾기 위해 모든 프로세스를 열거했습니다. 이제 CreateFileW 함수를 연결해 보겠습니다.

``` python
class DebugEvents(winappdbg.EventHandler):
  
    apiHooks = {
          
        # Hooks for the kernel32 library
        'kernel32.dll': [
  
            #  Function    Parameters
            ('CreateFileA', [PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE]),
            ('CreateFileW', [PVOID, DWORD, DWORD, PVOID, DWORD, DWORD, HANDLE]),
        ]
    }
  
    # Methods beginning with 'pre_' are called when entering the API and methods beginning with 'post_' are called when returning from the API
  
    def pre_CreateFileW(self, event, return_address, lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile):
  
        filepath = event.get_process().peek_string(lpFileName, fUnicode=True)
          
        if self._is_skype_save_file(filepath, dwCreationDisposition == CREATE_ALWAYS):
            _, filename = winappdbg.PathOperations.split_filename(filepath)
                          
            new_filepath       = winappdbg.PathOperations.join_path(SKYPE_REDIRECTED_FOLDER_FOR_RECEIVED_FILES, filename)
            new_filepath_bytes = new_filepath.encode('utf-16le')
  
            # Allocate memory in the target process and get a pointer
            new_filepath_address = event.get_process().malloc(len(new_filepath_bytes))
  
            # Write the new payload to that pointer
            event.get_process().write(new_filepath_address, new_filepath_bytes)
  
            top_of_stack = event.get_thread().get_sp()
  
            # Windows 32-bit uses stdcall convention for WinAPI calls: Parameters are pushed to the stack from right to left
            if event.get_thread().get_bits() == 32 or event.get_thread().is_wow64():
                event.get_process().write_dword(top_of_stack + 4, new_filepath_address)
                  
            # Windows 64-bit uses fast call: First four parameters are stored in rcx, rdx, r8 and r9
            elif event.get_thread().get_bits() == 64:
                event.get_thread().set_register('Rdx', new_filepath_address)
  
            print '\n\n'
            print 'Redirecting saving file...'
            print 'Original file path: {}; New file path: {}; Thread: {};'.format(filepath, new_filepath, event.get_tid())
  
    def _is_skype_save_file(self, filepath, is_created):
          
        directory, _ = winappdbg.PathOperations.split_filename(filepath)
          
        return is_created and \
            SKYPE_RECEIVED_FILES_FOLDER.lower() in winappdbg.PathOperations.native_to_win32_pathname(directory).lower()
```

이제 애플리케이션을 실행하고 저장 작업이 다른 폴더로 리디렉션되는지 확인할 수 있습니다.

``` python
Attaching to PID: 3592 Name: Skype.exe
Attaching to PID: 3504 Name: Skype.exe
Attaching to PID: 3760 Name: Skype.exe
Attaching to PID: 2600 Name: Skype.exe
Attaching to PID: 3784 Name: Skype.exe
Attaching to PID: 3360 Name: Skype.exe
  
Redirecting saving file...
Original file path: \\?\C:\SkypeDownload\Samples.zip; New file path: C:\SomeNewFolder\Samples.zip; Thread: 3408;
```

결과에서 볼 수 있듯이 이제 파일은 원래 대상 폴더 대신 SomeNewFolder 폴더에 저장되도록 성공적으로 리디렉션되었습니다.

WinAppDbg 라이브러리가 API 후킹을 위해 특별히 설계되지 않았다는 점은 주목할 만합니다. 프로세스, 스레드 및 창을 조작하는 다른 유틸리티를 제공하며 애플리케이션에 대한 명령줄 디버거를 시작할 수도 있습니다. 따라서 이 라이브러리는 후킹이 애플리케이션의 주요 목적이 아닐 때 더 유용합니다.

다양한 유형의 API에 대해 자세히 알아보려면 [Places API 구현 옵션](https://www.apriorit.com/dev-blog/717-web-implementing-places-api)을 살펴보는 기사를 확인하세요.



### 결론

Windows API 후킹을 수행하려면 C 및 C++와 같은 컴파일 언어를 알 필요가 없습니다. Python과 같은 동적 언어는 훌륭한 대안이 될 수 있으며 고유한 장점도 있습니다. Python은 우수한 개발 속도를 보여주고, 편리한 환경 구성을 제공하며, 유용한 타사 라이브러리가 많이 있습니다.

이 기사에서는 Python과 Deviare 및 WinAppDbg 라이브러리를 사용하여 다양한 프로세스에서 후크를 설정하는 방법을 보여주었습니다. 이러한 지식을 통해 운영 체제나 특정 응용 프로그램의 작동 방식을 더 잘 이해하고, 다양한 프로세스를 조작하고, 제품을 개선할 수 있습니다.

Apriorit에는 고품질 IT 제품을 만드는 데 도움을 줄 준비가 되어 있는 전문 Python 개발자로 구성된 전문 팀이 있습니다.

