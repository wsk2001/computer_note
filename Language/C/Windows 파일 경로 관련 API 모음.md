# Windows 파일 경로 관련 API 모음



윈도우의 Visual C++에서 파일 경로를 조작할 때 사용할 수 있는 유용한 API 함수들이다.

사용하기 위해 아래와 같이 선언한다.

\#include <shlwapi.h> #pragma comment(lib, "shlwapi")

**PathAddBackslash -**  경로에 백슬레시를 덧붙여준다. 백슬레시가 이미 붙어 있으면 변경하지 않는다.

"c:\abc" -> "c:\abc\"
"c:\abc\" -> "c:\abc\"

**PathAddExtension -** 파일 경로 뒤에 지정된 확장자를 덧붙여준다. 확장자가 이미 있다면 변경하지 않는다.

"c:\abc", ".bak" -> "c:\abc.bak"
"c:\abc.cpp", ".tmp" -> "c:\abc.cpp"

**PathAppend -** 두 개의 경로를 덧붙인다. 사이에 백슬레시가 없으면 자동으로 추가해 준다.

"c:\abc", "def" -> "c:\abc\def"
"c:\abc\", "def" -> "c:\abc\def"
"c:\abc", "\def" -> "c:\abc\def"
"c:\abc\", "\def" -> "c:\abc\def"

**PathBuildRoot -** 드라이브 식별번호를 드라이브 경로로 변경해 준다.

0 -> "A:\"
1 -> "B:\"
2 -> "C:\"

**PathCanonicalize -** 특별한 경로 문자열을 정리해 준다.

"c:\abc\def\..\ghi" -> "c:\abc\ghi"
"c:\abc\def\.\ghi" -> "c:\abc\def\ghi"

**PathCombine** - 두 개의 경로를 결합한다. 백슬레시도 검사해서 추가하고 ., ..과 같은 특별한 경로 문자열도 정리해 준다.

"c:\abc", "def.txt" -> "c:\abc\def.txt"
"c:\abc\", "..\def\ghi.txt" -> "c:\def\ghi.txt"

**PathCommonPrefix \-**  공통된 경로를 골라낸다.

"c:\abc\def.txt", "c:\abc\ghi\" -> "c:\abc"
"c:\abc\def\jkl.txt", "c:\abc\def\..\jkl.txt" -> "c:\abc\def"

**PathCompactPath  \-**  경로를 dc와 pixel 폭 크기에 알맞게 적당히 줄여준다.

hDC, "c:\abc\def\ghi\jkl.txt", 100 -> "c:\abc\...\jkl.txt"

**PathCompactPathEx \-** 경로를 최대 문자열 길이에 알맞게 적당히 줄여준다.

"c:\\abc\\def\\jkl.txt", 15 -> "c:\...\jkl.txt"

**PathCreateFromUrl  \-** URL로 쓰여진 파일명을 경로로 변환한다.

"file:///c:/abc/def.txt" -> "c:\abc\def.txt"

**PathFileExists \-** 해당 경로나 파일이 실제로 존재하는지 검사한다.

**PathFindExtension \-** 확장자의 위치를 찾아서 반환한다.

"c:\abc\def.txt" -> ".txt"

**PathFindFileName  \-** 파일 이름의 위치를 찾아서 반환한다.

"c:\abc\def.txt -> "def.txt"

**PathFindNextComponent  \-** 전체 경로 중 한 단계씩 하위로 내려간 경로를 반환한다.

"c:\abc\def.txt" -> "abc\def.txt"
"abc\def.txt" -> "def.txt"
"def.txt" -> ""

**PathFindOnPath  \-** 파일을 찾아서 완전한 경로를 반환한다. (목록이 NULL일 경우 실행 PATH에서 찾기)

"cmd.exe", NULL -> "C:\WINDOWS\system32\cmd.exe"
"iexplore.exe", { "c:\Windows", "c:\Program Files", "c:\Program Files\Internet Explorer", NULL } -> "c:\Program Files\Internet Explorer\iexplore.exe"
파일이 여러개라면 첫번째로 발견된 파일의 경로만 반환

**PathFindSuffixArray  \-** 지정된 접미사 목록에서 입력된 경로에 일치하는 것을 찾는다. (대소문자 구분)

"c:\abc\DEF.txt", { "def.txt", "DEF.txt", ".txt" }, 3 -> "DEF.txt"
"c:\abc\def\ghi.txt", { "def.txt", "DEF.txt", ".txt" }, 3 -> ".txt"

**PathGetArgs  \-** 전체 문자열에서 앞 단어를 무시한 입력 인자 위치를 찾아서 반환한다.

"test.exe temp.txt sample.doc" -> "temp.txt sample.doc"
"test.exe sample All 15" -> "sample All 15"
"test.exe" -> ""
"abc def ghi" -> "def ghi"

[PathGetCharType](http://msdn.microsoft.com/en-us/library/bb773608%28v=VS.85%29.aspx)  \- 문자가 경로에서 어떤 목적으로 쓰일 수 있는지 확인한다.

':' -> GCT_SEPARATOR
'.' -> GCT_LFNCHAR | GCT_SHORTCHAR
'\\' -> GCT_SEPARATOR
'/' -> GCT_INVALID
'\"' -> GCT_INVALID
'\'' -> GCT_LFNCHAR | GCT_SHORTCHAR
'\t' -> GCT_INVALID
'\n' -> GCT_INVALID
',' -> GCT_LFNCHAR
'*' -> GCT_WILD
'?' -> GCT_WILD
입력된 문자열을 검색하면서 리턴값을 조사하면 올바른 경로인지 미리 검사할 수 있다.

**PathGetDriveNumber  \-** 경로가 어느 드라이브에 있는지 번호를 확인한다.

"c:\abc\def.txt" -> 2
"d:" -> 3
"s:\test" -> 18

**PathIsContentType  \-** 파일 확장자가 콘텐츠 형식과 일치하는지 확인한다.

"c:\abc\def.txt", "text/plain" -> TRUE
".txt", "text/plain" -> TRUE
"txt", "text/plain" -> FALSE

**PathIsDirectory  \-** 실제로 존재하는 폴더인지 확인한다.

"c:\windows" -> TRUE
"c:\abc" -> FALSE

[PathIsDirectoryEmpty](http://msdn.microsoft.com/en-us/library/bb773623%28v=VS.85%29.aspx)  \- 폴더 내부가 비어있는지 확인한다.

"c:\windows" -> FALSE

**PathIsFileSpec  \-** 주어진 경로에 경로 문자들이 없는지 확인한다. 결과가 참이라도 올바른 파일명이 아닐 수 있다.

"c:\abc\def.txt" -> FALSE
"test.txt" -> TRUE
"*/wow." -> TRUE

**PathIsHTMLFile  \-** 콘텐츠 형식이 "text/html"인 확장자인지 확인한다.

"test.html" -> TRUE
"test.htm" -> TRUE
"test.xml" -> FALSE
"test.txt" -> FALSE

**PathIsLFNFileSpec  \-** 주어진 경로가 긴파일이름에 적합한지 확인한다.

**PathIsNetworkPath  \-** 주어진 경로가 네트워크 경로 형식인지 확인한다. (실제로 존재하는지 확인하지는 않음)

"c:\abc\def.txt" -> FALSE
"\\abc\def.txt" -> TRUE
"http://abc/def.txt" -> FALSE

**PathIsPrefix  \-** 경로가 주어진 위치에서 시작하는지 확인한다.

"c:\", "c:\abc" -> TRUE
"C:\", "c:\abc" -> TRUE
"c:", "c:\abc" -> FALSE
"..\abc", "..\abc\def" -> TRUE
"d:\abc", "d:\def" -> FALSE

**PathIsRelative  \-** 주어진 경로가 상대 경로인지 확인한다. (파일명만 있어도 상대 경로로 인정)

".\abc.txt" -> TRUE
"..\abc\def.txt" -> TRUE
"c:\abc\def.txt" -> FALSE
"c:\abc\..\def.txt" -> FALSE
"test.txt" -> TRUE

**PathIsRoot  \-** 주어진 경로가 드라이브 루트인지 검사한다.

"c:\\" -> TRUE
"c:" -> FALSE
"c:\test.txt" -> FALSE

**PathIsSameRoot \-** 주어진 경로가 같은 드라이브에 있는지 검사한다.

"c:\abc\def", "c:\test.txt" -> TRUE

**PathIsSystemFolder  \-** 시스템 속성을 가진 폴더인지 확인한다.

"c:\windows" -> FALSE
"c:\program files" -> TRUE
"C:\Documents and Settings" -> FALSE
"C:\Documents and Settings\All Users\Application Data" -> TRUE


**PathIsUNC** - 네트워크 공유 경로인지 확인한다. (실제로 존재하는지 확인하지는 않음)

"c:\abc\def" -> FALSE
"\\abc\def" -> TRUE
"\\192.168.0.1" -> TRUE
"\\abc\def.txt" -> TRUE
"\\" -> TRUE
"\\test.txt" -> TRUE


**PathIsUNCServer** - 네트워크 공유 서버인지 확인한다. (실제로 존재하는지 확인하지는 않음)

"c:\abc\def" -> FALSE
"\\abc\def" -> FALSE
"\\192.168.0.1" -> TRUE
"\\abc\def.txt" -> FALSE
"\\" -> TRUE
"\\test.txt" -> TRUE


**PathIsUNCServerShare** -  네트워크 공유 폴더인지 확인한다. (실제로 존재하는지 확인하지는 않음)

"c:\abc\def" -> FALSE
"\\abc\def" -> TRUE
"\\192.168.0.1" -> FALSE
"\\abc\def.txt" -> TRUE
"\\" -> FALSE
"\\test.txt" -> FALSE

**PathIsURL \-** 주어진 경로가 URL 형식이 맞는지 확인한다.

**PathMakePretty  \-** 대문자로 만들어진 경로 문자열을 소문자로 변환한다. (소문자가 하나라도 있으면 실패)

"C:\ABC\DEF" -> TRUE, "C:\abc\def"
"c:\ABC\DEF" -> FALSE, "c:\ABC\DEF"
"C:\abc\DEF" -> FALSE, "C:\abc\DEF"

**PathMakeSystemFolder  \-** 지정된 폴더를 시스템 폴더로 만든다.

**PathMatchSpec  \-** 와일드카드 형식으로 파일명과 일치되는지 확인한다.

"test.txt", "*.txt" -> TRUE
"abc.txt", "ab?.*" -> TRUE
"c:\abc\def.txt", "*\???.txt" -> TRUE

**PathParseIconLocation  \-** 파일 경로와 아이콘 인덱스 번호를 분리한다.

"iexplore.exe, 1" -> 1, "iexplore.exe"

**PathQuoteSpaces  \-** 경로에 공백이 포함되어 있으면 큰따옴표로 묶어준다. 공백이 없으면 무시

c:\1. abc -> "c:\\1. abc"
c:\abc -> c:\abc

**PathRelativePathTo  \-** 한 경로에서 다른 경로로 가는 상대 경로를 추출한다. (같은 드라이브에서만 가능)

"c:\abc\def\ghi\jkl.txt", 0, "c:\abc\mno\pqr.txt", 0 -> TRUE, "..\..\mno\pqr.txt"
"c:\abc\def\ghi\jkl", FILE_ATTRIBUTE_DIRECTORY, "c:\abc\mno\pqr.txt", 0

-> TRUE, "..\..\..\mno\pqr.txt"

"c:\abc\def\ghi\jkl.txt", 0, "c:\abc\mno\pqr", FILE_ATTRIBUTE_DIRECTORY

-> TRUE, "..\..\mno\pqr"

**PathRemoveArgs  \-** 경로에 포함된 인자를 지운다.

"test.exe temp.txt sample.doc" -> "test.exe"
"test.exe sample All 15" -> "test.exe"
"test.exe" -> "test.exe"
"abc def ghi" -> "abc"

**PathRemoveBackslash  \-** 경로 끝에 백슬레시가 있으면 삭제한다.

"c:\abc\" -> "c:\abc"
"c:\abc" -> "c:\abc"

**PathRemoveBlanks  \-** 경로 앞뒤에 공백문자가 있으면 삭제한다.

"  c:\abc   " -> "c:\abc"

**PathRemoveExtension  \-** 확장자를 삭제한다.

"c:\abc\def.txt" -> "c:\abc\def"

**PathRemoveFileSpec  \-** 파일 이름을 삭제한다.

"c:\abc\def.txt" -> "c:\abc"
"c:\abc\def\" -> "c:\abc\def"

**PathRenameExtension  \-** 확장자를 교체한다.

"c:\abc\def.txt", ".bak" -> "c:\abc\def.bak"
"c:\abc\def", ".bak" -> "c:\abc\def.bak"
"c:\abc\def\", ".bak" -> "c:\abc\def\.bak"

**PathSearchAndQualify  \-** 주어진 경로의 오류를 바로잡고, 상대 경로도 정리하고, 환경 변수도 적용한다.

"C:\foo\." -> "C:\foo"
"C:\foo\baz\.." -> "C:\foo"
"C:\foo\\\baz" -> "C:\foo\baz"
"\\server\aa\..\bb" -> "\\server\aa\bb"
"notepad.exe" -> "C:\Windows\System32\notepad.exe"
"%SystemRoot%\System32\notepad.exe" -> "C:\Windows\System32\notepad.exe"
(XP에서는 환경변수 확장이 제대로 작동하지 않음)

**PathSetDlgItemPath  \-** 주어진 다이얼로그 아이템 윈도우의 크기에 알맞게 긴 경로를 적당히 줄여준다.

**PathSkipRoot  \-** 루트 경로를 제외한 첫번째 위치를 찾아서 반환한다.

"c:\abc\def" -> "abc\def"
"\\\\abc\\def\\ghi.txt" -> "ghi.txt"

**PathStripPath** - 가장 마지막 경로만 남기고 삭제한다.

"c:\abc\def.txt" -> "def.txt"
"c:\abc\def" -> "def"
"c:\abc\def\" -> "def\"

**PathStripToRoot  \-** 루트 경로만 남기고 삭제한다.

"c:\abc\def.txt" -> "c:\"
"c:\abc\def" -> "c:\"

**PathUndecorate \-** 경로에서 임시 파일에 붙는 숫자를 삭제한다.

"C:\Path\File[5].txt" -> "C:\Path\File.txt"
"C:\Path\File[12]" -> "C:\Path\File"
"C:\Path\File.txt" -> "C:\Path\File.txt"
"C:\Path\[3].txt" -> "C:\Path\[3].txt"
인터넷으로 접근한 임시파일들은 [#]과 같은 식으로 번호가 뒤에 붙는데 그러한 임시 번호를 제거한다.

**PathUnExpandEnvStrings  \-** 경로에서 환경 변수에 해당하는 문자열을 환경 변수 이름으로 교체한다.

"c:\Windows\test.txt" -> "%SystemRoot%\test.txt"
"c:\program files\test.txt" -> "%ProgramFiles%\test.txt"
"c:\abc\def" -> "%SystemDrive%\abc\def"

**PathUnmakeSystemFolder  \-** 지정된 폴더의 시스템 속성을 해제한다.

**PathUnquoteSpaces** - 큰 따옴표로 감싸진 경로에서 따옴표를 제거한다.

"c:\abc\1 def" -> c:\abc\1 def
c:\abc\def -> c:\abc\def



출처: https://mindgear.tistory.com/242

