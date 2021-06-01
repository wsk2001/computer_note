# VSCODE c_cpp_properties.json Reference Guide

Visual Studio Code(이하 vscode) 를 이용한 Program 작성시 #include 로 포함한 header 파일을 인식 하지 못하는 경우가 있다. 이때 해당 오류를 해결 하기 위한 방법은 다음과 같다.

vscode 창을 보면 ".vscode" 가 있는데 확장을 시켜 보면 `c_cpp_properties.json` 파일이 있는데 이 파일을 열어  "includePath"  에 header 파일이 있는 경로를 추가 해 주면 된다.



현재 내가 사용하고 있는 `c_cpp_properties.json` 파일을 보고 `github` 에 있는 문서를 번역 하여 아래에 기술 하도록 한다.

`내가 사용하고 있는 json sample`

```json
{
    "env" : {
        "myDefaultIncludePath": [
            "${workspaceFolder}",
            "${workspaceFolder}/../../inc",
            "${workspaceFolder}/../../include"
        ]
    },    
    "configurations": [
        {
            "name": "Win32",
            "includePath": [ "${myDefaultIncludePath}", "." ],
            "defines": [
                "_DEBUG",
                "UNICODE",
                "_UNICODE"
            ],
            "windowsSdkVersion": "10.0.17763.0",
            "compilerPath": "C:/Program Files (x86)/Microsoft Visual Studio/2017/Professional/VC/Tools/MSVC/14.16.27023/bin/Hostx64/x64/cl.exe",
            "cStandard": "c11",
            "cppStandard": "c++17",
            "intelliSenseMode": "msvc-x64"
        }
    ],
    "version": 4
}
```



기본적으로 vscode 에서 파일을 열면 `"env"` 와 `"includePath"` 는 포함되어 있지 않다. 따라서 위의 예제를 참조하여 각 project 에서  `c_cpp_properties.json` 를 수정 하여 사용 하면 된다.

아래는 `github` 에 있는  `c_cpp_properties.json`  사용 가이드 이다. 가이드의 URL 을 https://github.com/Microsoft/vscode-cpptools/blob/master/Documentation/LanguageServer/c_cpp_properties.json.md 이다.



# `c_cpp_properties.json` Reference Guide

> See also: [Customizing Default Settings](https://github.com/Microsoft/vscode-cpptools/blob/master/Documentation/LanguageServer/Customizing%20Default%20Settings.md)

### Example

```json
{
    "env" : {
        "myDefaultIncludePath": [
            "${workspaceFolder}",
            "${workspaceFolder}/include"
        ],
        "myCompilerPath": "/usr/local/bin/gcc-7"
    },
    "configurations": [
        {
            "name": "Mac",
            "intelliSenseMode": "clang-x64",
            "includePath": [ "${myDefaultIncludePath}", "/another/path" ],
            "macFrameworkPath": [ "/System/Library/Frameworks" ],
            "defines": [ "FOO", "BAR=100" ],
            "forcedInclude": [ "${workspaceFolder}/include/config.h" ],
            "compilerPath": "/usr/bin/clang",
            "cStandard": "c11",
            "cppStandard": "c++17",
            "compileCommands": "/path/to/compile_commands.json",
            "browse": {
                "path": [ "${workspaceFolder}" ],
                "limitSymbolsToIncludedHeaders": true,
                "databaseFilename": ""
            }
        }
    ],
    "version": 4
}
```



## Top-level properties

- #### `env`

  표준 환경 변수 구문 인 $ {} 또는 $ {env : }를 통해 구성에서 대체 할 수있는 사용자 정의 변수의 배열입니다. 문자열과 문자열 배열이 허용됩니다.

- #### `configurations`

  IntelliSense 엔진에 프로젝트 및 기본 설정에 대한 정보를 제공하는 구성 개체 배열입니다. 기본적으로이 확장은 운영 체제에 따라 구성을 만듭니다. 추가 구성을 추가 할 수도 있습니다.

- #### `version`

  이 필드는 편집하지 않는 것이 좋습니다. c_cpp_properties.json 파일의 현재 버전을 추적하여 확장자가 현재 존재해야하는 속성 및 설정을 알고이 파일을 최신 버전으로 업그레이드하는 방법을 알 수 있습니다.



## Configuration properties

- #### `name`

  구성의 친숙한 이름입니다. 'Linux', 'Mac'및 'Win32'는 추가 구성이 작성되지 않은 경우 해당 운영 체제에서 기본적으로 해당 구성을로드하도록 확장에 지시하는 특수 이름입니다. VS 코드의 상태 표시 줄은 어떤 구성이 활성화되어 있는지 보여줍니다. 상태 표시 줄의 레이블을 클릭하여 활성 구성을 변경할 수도 있습니다.

- #### `intelliSenseMode`

  설정 파일에서 'C_Cpp.intelliSenseEngine'이 'Default'로 설정된 경우이 속성은 IntelliSense 엔진을 실행할 모드를 결정합니다. 'msvc-x64'는 64 비트 포인터 크기의 Visual Studio 모드로 매핑됩니다. 'clang-x64'는 64 비트 포인터 크기로 CLang 모드에 매핑됩니다. 'gcc-x64'는 64 비트 포인터 크기로 GCC 모드에 매핑됩니다. Windows는 기본적으로 'msvc-x64'를 사용하고, macOS는 기본적으로 'clang-x64'를 사용하고 Linux는 기본적으로 'gcc-x64'를 사용합니다.

- #### `includePath`

  설정 파일에서 'C_Cpp.intelliSenseEngine'이 'Default'로 설정된 경우이 경로 목록은 IntelliSense에서 소스 파일에 포함 된 헤더를 검색하는 데 사용됩니다. 이것은 기본적으로 -I 스위치를 사용하여 컴파일러에 전달하는 경로 목록과 동일합니다. 경로가 / **로 끝나면 IntelliSense 엔진은 해당 디렉터리에서 시작하여 Include를 재귀 적으로 검색합니다. Visual Studio가 설치된 Windows 또는 compilerPath 설정에 컴파일러가 지정된 경우이 목록에 시스템 포함 경로를 나열 할 필요가 없습니다.

- #### `macFrameworkPath`

  설정 파일에서 'C_Cpp.intelliSenseEngine'이 'Default'로 설정된 경우이 경로 목록은 IntelliSense에서 소스 파일에 포함 된 프레임 워크 헤더를 검색하는 데 사용됩니다. 이것은 기본적으로 -F 스위치를 사용하여 컴파일러에 전달하는 경로 목록과 동일합니다. IntelliSense 엔진은 포함을 위해 이러한 경로에서 재귀 검색을 수행하지 않습니다.

- #### `defines`

  설정 파일에서 'C_Cpp.intelliSenseEngine'이 'Default'로 설정된 경우 소스 파일을 컴파일하는 동안 IntelliSense에서이 전 처리기 기호 목록을 사용합니다. 이것은 기본적으로 -D 스위치를 사용하여 컴파일러에 전달한 기호 목록과 동일합니다. Visual Studio가 설치된 Windows 또는 compilerPath 설정에 컴파일러가 지정된 경우이 목록에 시스템 포함 경로를 나열 할 필요가 없습니다.

- #### `forcedInclude` (optional)

  소스 파일의 다른 문자 앞에 포함되어야하는 파일 목록이 처리됩니다. 파일은 나열된 순서대로 포함됩니다.

- #### `compilerPath` (optional)

  프로젝트를 빌드하는 데 사용하는 컴파일러의 절대 경로입니다. 이 확장은 시스템 포함 경로 및 IntelliSense에 사용할 기본 정의를 결정하기 위해 컴파일러를 쿼리합니다.

  args는 사용 된 includes/definitions를 수정하기 위해 추가 될 수 있습니다. -nostdinc ++, -m32, -fno-ms-extensions 등이 있지만, 공백이있는 경로는 args가 사용될 경우 큰 따옴표 ( ')로 둘러 쌓여 야합니다.

  시스템 포함 경로 및 정의에 대한 자동 질의를 사용하지 않으려면이 값을 emptry 문자열 ''로 설정하십시오. 이것은 일반적으로 권장되지 않지만 시스템의 자동 쿼리가 포함되고 정의되는 것이 바람직하지 않은 경우가 있습니다.

- #### `cStandard`

   프로젝트의 IntelliSense에 사용할 C 표준 개정판입니다.

- #### `cppStandard`

  프로젝트에서 IntelliSense에 사용할 C ++ 표준 수정 버전입니다.

- #### `compileCommands` (optional)

  설정 파일에서 'C_Cpp.intelliSenseEngine'을 'Default'로 설정하면이 파일에서 발견 된 포함 및 정의가 includePath에 설정된 값 대신 사용되어 정의됩니다. 컴파일 명령 데이터베이스에 편집기에서 연 파일에 해당하는 번역 단위에 대한 항목이 포함되어 있지 않으면 경고 메시지가 표시되고 확장 프로그램은 includePath를 사용하고 대신 설정을 정의합니다.

  > 파일 형식에 대한 자세한 내용은,  [Clang documentation](https://clang.llvm.org/docs/JSONCompilationDatabase.html) 를 보면 됩니다. CMake와 같은 일부 빌드 시스템은, [simplify generating this file](https://cmake.org/cmake/help/v3.5/variable/CMAKE_EXPORT_COMPILE_COMMANDS.html) 을 참조 하세요.

- #### `browse`

   'C_Cpp.intelliSenseEngine'이 'Tag Parser'( '퍼지'IntelliSense 또는 '찾아보기'엔진이라고도 함)로 설정된 경우 사용되는 속성 집합입니다. 이러한 속성은 Go To Definition/Declaration 기능이나 'Default'IntelliSense 엔진이 원본 파일의 포함을 해결할 수없는 경우에도 사용됩니다.

### Browse properties

- #### `path`

  이 경로 목록은 태그 파서가 소스 파일에 포함 된 헤더를 검색하는 데 사용됩니다. 생략하면 includePath가 경로로 사용됩니다. 태그 파서는 경로가 /* 또는 \\* *로 끝나지 않는 한이 경로의 모든 하위 폴더를 자동으로 검색합니다. 예를 들어 / usr / include는 태그 파서가 include 폴더와 하위 폴더에서 헤더를 검색하도록 지시하지만 /usr/include/*는 /usr/include의 하위 폴더에서 태그 파서가 보이지 않도록 지시합니다.

- #### `limitSymbolsToIncludedHeaders`

  true로 설정하면 태그 파서는 $ {workspaceFolder}의 소스 파일에 직접 또는 간접적으로 포함 된 코드 파일 만 구문 분석합니다. false이면 태그 파서는 경로 목록에 지정된 경로에있는 모든 코드 파일을 구문 분석합니다.

- #### `databaseFilename`

  설정되면, 이것은 확장자에게 작업 공간의 기본 저장 위치가 아닌 다른 곳에 Tag Parser의 기호 데이터베이스를 저장하도록 지시합니다. 상대 경로가 지정되면 작업 영역의 기본 저장소 위치에 상대적으로 만들어지며 작업 영역 폴더 자체에는 상대적으로 만들어지지 않습니다. $ {workspaceFolder} 변수는 작업 공간 폴더와 관련된 경로를 지정하는 데 사용할 수 있습니다 (예 : $ {workspaceFolder} / .vscode / browse.vc.db).



### 끝

