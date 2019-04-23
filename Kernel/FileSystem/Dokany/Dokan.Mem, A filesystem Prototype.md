# Dokan.Mem, A filesystem Prototype

Building (almost) a RAM-Disk using the Dokan library and C#

- [Download demo - 519.76 KB](./ZipFiles/Dokan.Mem_demo.zip)
- [Download source - 532.52 KB](./ZipFiles/Dokan.Mem_src.zip)

![](.\images\Dokan.Mem1.png)



## 소개

Hiroki Asakawa는 사용자 모드의 응용 프로그램이 파일 시스템을 시뮬레이션하고 MIT 스타일의 라이센스하에 배포 할 수있게 해주는 장치 드라이버를 만들었습니다. 이 게시물은 C# 에서 RAM-Disk의 기능을 만드는 데 사용되는 방법과 파일 시스템 응용 프로그램 롤링에 대한 몇 가지 팁을 보여줍니다.

코드를 실행하기 전에 설치 프로그램을 실행하여 프록시 드라이버 (dokan.sys)를 설치해야합니다. 이 드라이버는 커널과 사용자 정의 .NET 솔루션 간의 중간 역할을합니다. 프록시 드라이버 설치 프로그램도 여기에 소스 코드에 포함되어 있지만 두 가지 멋진 예제가 포함되어 있으므로 원본 웹 사이트에서 패키지를 다운로드하는 것이 좋습니다. 첫 번째는 C 드라이브의 미러를 구현하고 두 번째는 레지스트리를 읽기 전용 드라이브로 사용할 수있게합니다. 이 기사에서는 Dokan 라이브러리를 사용하여 RAM 디스크와 유사한 기능을 구현하는 방법을 간략하게 설명합니다.

데이터를 파일 시스템으로 공개하는 옵션을 사용하면 몇 가지 이점이 있습니다. 복잡한 UI가 없어도 다른 응용 프로그램에서 즉시 데이터를 사용할 수 있습니다.

Dokan의 기본 설치 프로그램과 .NET 바인딩을 [여기 [^]](http://dokan-dev.net/en/download/)에 다운로드하십시오.



## 코드 사용

관리자로 로그인하고 설치 프로그램을 실행하면 (단지 513KB) 프로젝트를 열고 F5를 누릅니다. 트레이 아이콘이 나타나야 하며, 더블 클릭 하면 시뮬레이션 된 하드 디스크를 가리키는 Windows 탐색기가 열립니다. 첫 번째 사용 가능한 무료 드라이브 문자에 탑재됩니다. 거기에 압축 파일을 붙여 압축을 풉니 다. :)

라이브러리 (버전 0.5.3)를 테스트하기 위해 기본 RAM-Disk를 구현하기로 결정했으며 결과는 여기에 문서화되어 있습니다.

솔루션에는 세 가지 프로젝트가 있습니다.

- `DokanNet `- 이들은 Dokan 라이브러리에 대한 .NET 바인딩입니다.
- `Buffers `-  MemoryStream의 치환에 사용된다
- `Dokan.Mem` -  예 - RAM-Disk를 시뮬레이션 한 Dokan 인터페이스 구현

### DokanNet

이 프로젝트는 DokanOperations 인터페이스에 의해 제공되는 Dokan 라이브러리에 대한 바인딩을 포함합니다. 이는 Word와 같은 응용 프로그램이 파일 시스템에서 수행 할 수있는 작업을 직접적으로 정의한 것입니다.

```c#
1  public interface DokanOperations
 2  {
 3      int CreateFile(string filename, ..., DokanFileInfo info);
 4      int OpenDirectory(string filename, DokanFileInfo info);
 5      int CreateDirectory(string filename, DokanFileInfo info);
 6      int Cleanup(string filename, DokanFileInfo info);
 7      int CloseFile(string filename, DokanFileInfo info);
 8      int ReadFile(string filename, ..., DokanFileInfo info);
 9      int WriteFile(string filename, ..., DokanFileInfo info);
10      int FlushFileBuffers(string filename, DokanFileInfo info);
11      int GetFileInformation(string filename, ..., DokanFileInfo info);
12      int FindFiles(string filename, ArrayList files, DokanFileInfo info);
13      int SetFileAttributes(string filename, ..., DokanFileInfo info);
14      int SetFileTime(string filename, ..., DokanFileInfo info);
15      int DeleteFile(string filename, DokanFileInfo info);
16      int DeleteDirectory(string filename, DokanFileInfo info);
17      int MoveFile(string filename, ..., DokanFileInfo info);
18      int SetEndOfFile(string filename, long length, DokanFileInfo info);
19      int SetAllocationSize(string filename, long length, DokanFileInfo info);
20      int LockFile( string filename, long offset, long length, DokanFileInfo info);
21      int UnlockFile(string filename, long offset, long length, DokanFileInfo info);
22      int GetDiskFreeSpace(ref ulong freeBytesAvailable, ..., DokanFileInfo info);
23      int Unmount(DokanFileInfo info);
24  }
```

이 인터페이스를 기반으로하는 수업 (예 : 'MyDokanOperations')을 갖게되면 Dokan-main 메서드를 호출하여 새 드라이브를 시작할 수 있습니다. 이 (차단) 작업이 실행중인 동안에는 드라이브를 사용할 수 있습니다.

MyDokanOperations 클래스에서 DokanOperations를 구현하는 스켈레톤 애플리케이션이 아래에 나와 있습니다. DokanOperations는 인터페이스입니다. IDokanOperations라는 이름이 아닐 수도 있지만 읽어야합니다.

```c#
1  class MyDokanOperations : DokanOperations
 2  {
 3     // implementation of DokanOperations interface goes here..
 4  }
 5
 6  DokanOptions options = new DokanOptions
 7  {
 8     DriveLetter = 'Z',
 9     DebugMode = true,
10     UseStdErr = true,
11     NetworkDrive = false,
12     Removable = true,     // provides an "eject"-menu to unmount
13     UseKeepAlive = true,  // auto-unmount
14     ThreadCount = 0,      // 0 for default, 1 for debugging
15     VolumeLabel = "MyDokanDrive"
16  };
17
18  static void Main(string[] args)
19  {
20     DokanNet.DokanMain(
21         options,
22         new MyDokanOperations());
23  }
```

예제는 모두 콘솔에 보여 주며, 이는 새로운 파일 시스템을 개발 할 때 멋지다. 디버깅을 할 때 매우 유용하며 실제로 커널과 시뮬레이션 된 파일 시스템 간의 상호 작용을 따라갈 수 있습니다.

### Dokan.Mem

또한 RAM-Disk는 콘솔 창에 표시하지만 프로젝트의 출력 유형을 'Windows 응용 프로그램'으로 변경하여 쉽게 비활성화 할 수 있습니다. 릴리스 모드에서 응용 프로그램과 상호 작용할 수있는 트레이 아이콘을 제공했습니다.

그 DokanOperations 인터페이스로 돌아 가기; 이러한 호출의 대부분은 실제 API를 가지고 있으며 MSDN에 대한 설명을 찾을 수 있습니다. API 설명은 일반 플로우를 문서화하고 리턴 할 수있는 오류 코드를 나열하므로 매우 유용합니다.

DeleteFile [^] 메소드의 실제 구현을 살펴 보겠습니다.

```c#
1  public int DeleteFile(string filename, DokanFileInfo info)
 2  {
 3     // get parent folder
 4     MemoryFolder parentFolder = _root.GetFolderByPath(
 5         filename.GetPathPart());
 6
 7     // exists?
 8     if (!parentFolder.Exists())
 9         return -DokanNet.ERROR_PATH_NOT_FOUND;
10
11     // fetch file;
12     MemoryFile file = parentFolder.FetchFile(
13         filename.GetFilenamePart());
14
15     // exists?
16     if (!file.Exists())
17         return -DokanNet.ERROR_FILE_NOT_FOUND;
18
19     // delete it;
20     parentFolder.Children.Remove(file);
21     file.Content.Dispose();
22
23     return DokanNet.DOKAN_SUCCESS;
24  }
```

DeleteFile 함수에 대한 MSDN 상태 :

> "응용 프로그램이 존재하지 않는 파일을 삭제하려고하면 DeleteFile 함수가 ERROR_FILE_NOT_FOUND와 함께 실패하고 파일이 읽기 전용 파일이면 ERROR_ACCESS_DENIED와 함께 실패합니다."

RAM 디스크는 파일이 존재하는지 확인하지만 아직 파일 속성 검사를 구현하지 않았습니다. 파일 속성을 설정하는 일은 전혀 구현되지 않았습니다.

```c#
1  public int SetFileAttributes(
2     string filename,
3     FileAttributes attr,
4     DokanFileInfo info)
5  {
6     return -DokanNet.DOKAN_ERROR;
7  }
```

인터페이스를 다시 살펴보면 OpenFile 메소드가 없다는 것을 알 수 있습니다. 시스템이 파일을 열려면 CreateFile 메서드를 호출합니다. CreateFile [^] API에서 사용하는 것과 동일한 플래그가 필요합니다. FileMode에 따라 기존 파일 또는 존재하지 않는 파일을 만들거나 엽니 다.

```c#
1  public int CreateFile(
 2      string filename,
 3      FileAccess access,
 4      FileShare share,
 5      FileMode mode,
 6      FileOptions options,
 7      DokanFileInfo info)
 8  {
 9      [...]
10
11       // attempt to use the file
12      switch (mode)
13      {
14          // Opens the file if it exists and seeks to the end of the file,
15          // or creates a new file
16          case FileMode.Append:
17              if (!thisFile.Exists())
18                  MemoryFile.New(parentFolder, newName);
19              return DokanNet.DOKAN_SUCCESS;
20
21          // Specifies that the operating system should create a new file.
22          // If the file already exists, it will be overwritten.
23          case FileMode.Create:
24              //if (!thisFile.Exists())
25                  MemoryFile.New(parentFolder, newName);
26              //else
27              // thisFile.Content = new Thought.Research.AweBuffer(1024);
                //MemoryStream();
28              return DokanNet.DOKAN_SUCCESS;
29
30          // Specifies that the operating system should create a new file.
31          // If the file already exists, an IOException is thrown.
32          case FileMode.CreateNew:
33              if (thisFile.Exists())
34                  return -DokanNet.ERROR_ALREADY_EXISTS;
35              MemoryFile.New(parentFolder, newName);
36              return DokanNet.DOKAN_SUCCESS;
37
38          // Specifies that the operating system should open an existing file.
39          // A System.IO.FileNotFoundException is thrown if the file does not exist.
40          case FileMode.Open:
41              if (!thisFile.Exists())
42                  return -DokanNet.ERROR_FILE_NOT_FOUND;
43              else
44                  return DokanNet.DOKAN_SUCCESS;
45
46          // Specifies that the operating system should open a file if it exists;
47          // otherwise, a new file should be created.
48          case FileMode.OpenOrCreate:
49              if (!thisFile.Exists())
50                  MemoryFile.New(parentFolder, newName);
51              return DokanNet.DOKAN_SUCCESS;
52
53          // Specifies that the operating system should open an existing file.
54          // Once opened, the file should be truncated so that its size is zero bytes
55          case FileMode.Truncate:
56                  if (!thisFile.Exists())
57                      thisFile = MemoryFile.New(parentFolder, newName);
58                  thisFile.Size = 0;
59                  return DokanNet.DOKAN_SUCCESS;
60      }
61
62      return DokanNet.DOKAN_ERROR;
63  }
```

MemoryFolder 및 MemoryFile 클래스는 메모리의 '파일'을 계층 구조로 매핑하는 데 사용됩니다. 드라이브 루트를 나타내는 루트 노드가 있으며 파일 또는 폴더를 나타내는 개체가 포함될 수 있습니다.

![](.\images\Dokan.Mem2.png)

MemoryFile 클래스는 abstract라는 것을 알 수 있습니다.



### Buffers

RAM-Disk 프로토 타입은 원래 MemoryStream을 기반으로했습니다. 이렇게하면 '가상 메모리 디스크'가 될 것이므로 RAM 디스크라고 부르기 전에 일부 기능이 누락되었습니다.

데이빗 핀치 (David Pinch)의 생각에 기반한 버퍼로 MemoryStream을 대체했습니다. 그는 메모리를 할당하는 데 사용할 수있는 클래스를 작성하여 Dispose 섹션에서 메모리를 확보했습니다. 원래는 할당 된 영역을 보호 할 수있는 기능을 제공하기 때문에 ProtectedBuffer라는 이름을 사용합니다. 이 메모리 블록은 스트림으로 액세스 할 수 있으며 크기를 조정할 수없는 단점이 있습니다.

AweBuffer 클래스는 ProtectedBuffer 클래스를 기반으로 VirtualLock [^] API에 대한 호출을 추가합니다. 이렇게하면 스레드가 실행되는 동안 정보가 물리적 메모리에 고정됩니다. 이 기능이 없으면 RAM 디스크의 최대 크기는 사용 가능한 가상 메모리의 양에 의해서만 제한됩니다. 이론 상으로는 데이터 액세스 속도가 빨라집니다.

실제로 RAM 디스크는 실행중인 다른 모든 응용 프로그램을 스왑 파일로 밀어 넣기 때문에 더 큰 지연이 발생합니다. 괜찮은 표시를 얻으려면 여러 시스템에서 일부 테스트가 필요하지만이 버퍼는 단순한 MemoryStream보다 성능이 떨어집니다.

*) The Old New Thing [^]의 VirtualLock 항목을 참조하십시오.

```c#
1  [STAThread()]
2  static void Main(string[] args)
3  {
4      // Set us up a tray-icon to interact with the user;
5      SetupNotifyIcon();
6
7      // Set to false if you want to test with the AweBuffers
8      // instead of a MemoryStream
9      MemoryFile.UseMemStream = true;
```



## Points of Interest

### In General

- 파일 시스템을 실행하려면 관리자로 로그인해야합니다.
- 제시된 코드는 프로토 타입 품질의 것입니다. 잠금이없고 오류 처리도없고 초보 사용자 인터페이스가 있습니다.
- 경로와 파일 이름은 Windows에서 대소 문자를 구분하지 않습니다. 다른 앱은 파일을 요청할 때 다른 케이스를 사용합니다.
- 파일이나 폴더의 이름을 바꾸는 특별한 방법은 MoveFile 메서드로 수행됩니다.
- 폴더는 일반적으로 계층 구조로 정렬되지만 필수는 아닙니다. 하나는 폴더 구조를 완전히 생략하고 파일 만 표시 할 수 있습니다 (FindFiles 메소드 참조).
- 각 파일을 검사하는 경향이 있으므로 바이러스 검사기를 비활성화 할 수 있습니다. 마운트 후 요청 된 첫 번째 파일은 'AutoRun.Inf'입니다.
- MemoryFile 클래스는 Windows에서 파일의 콘텐츠 인덱싱을하지 못하도록 FILE_ATTRIBUTE_NOT_CONTENT_INDEXED [^] 특성을 갖습니다.
- 온라인 readme [^]에는 Dokan-Library의 내부 구조와 작동 방식에 대한 심층적 인 설명이 포함되어 있습니다.



## SQL Server와 상호 작용하는 경우

SQL Server에 저장된 그림의 탐색기에서 Paint.NET을 사용할 수있어서 멋지 기 때문입니다.

```c#
1  public int ReadFile(string sourcePath, byte[] buffer, ref uint readBytes,
 2      long offset, DokanFileInfo info)
 3  {
 4      using (var con = new SqlConnection())
 5      {
 6          int fileId = GetFileIdByPath(sourcePath);
 7          using (var myVarBinarySource = new VarBinarySource(
 8              con,
 9              "[TableName]", // source table
10              "[Contents]",  // source column
11              "[Id]",        // key column
12              resourceId))
13          {
14              var dbStream = new VarBinaryStream(myVarBinarySource);
15              dbStream.Seek(offset, SeekOrigin.Begin);
16              readBytes = (uint) dbStream.Read(buffer, 0, buffer.Length);
17          }
18      }
19      return DokanNet.DOKAN_SUCCESS;
20  }
```

그것은 미친 듯이 데이터베이스 연결을 열고 닫는 것입니다.하지만 결국 그것은 아주 잘 수행됩니다. 또는 CreateFile 메서드에서 연결을 열고 목록에 추가 한 다음 CloseFile 메서드가 호출 될 때 다시 닫을 수 있습니다. testdata가 필요한 경우; AdventureWorks 데이터베이스에는 [제작]이라는 테이블이 포함되어 있습니다. [문서]에는 바이너리 모양의 Word 문서가 몇 개 들어 있습니다.



## 결론

Hiroki는 훌륭한 일을 해냈습니다. Dokan-libraries는 훌륭하게 수행합니다. 장소에 중단 점을 둘 수 있으며 특수 설정없이 코드를 단계별로 실행할 수 있습니다. cool :

RAM 디스크에 관해서는, 그것은 많은 속도를 추가하지 않습니다. AweBuffer 버전을 사용하려고 시도했을 때 벤치 마크가 중단되었으며 파일을 만들 때 7Mb/s라고보고되었습니다. 읽기 동작 중 최대 속도는 약 40 Mb/s입니다. 비교해 보면 내 하드 디스크는 평균 읽기 속도가 165 Mb/s 인 파일을 만들 때 약 64 Mb/s의 속도를냅니다.



출처:  https://www.codeproject.com/Articles/95430/Dokan-Mem-A-filesystem-Prototype



