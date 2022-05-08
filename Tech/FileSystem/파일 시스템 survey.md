# 파일 시스템 survey

## <span style="color:blue">HDFS</span>

HDFS는 Hadoop Distributed File System(하둡 분산 파일 시스템)의 약어 입니다. 특징으로는 일반적인 하드웨어로 구성된 클러스터에서 실행되며 대용량 파일을 다룰 수 있도록 설계된 파일 시스템 입니다. 좀 더 상세한 특징은 아래와 같습니다.

- 아주 큰 용량의 데이터 저장 가능 : 여기서 아주 큰 용량의 데이터는 작게는 수백 메가바이트부터 크게는 수십 페타바이트 크기의 데이터를 이야기 합니다. 단일 장비가 아닌 클러스터에 분산 저장을 하기 때문에 클러스터 규모에 따라 수용 가능한 데이터의 크기가 달라지게 됩니다.

- 스트리밍 방식의 데이터 이용 : <span style="color:red">한번의 쓰기 작업 이후에 여러 번 읽는 것이 가장 효율적이 데이터 이용 방식이라는 컨셉을 가지고 있기 때문에 HDFS로 저장된 데이터는 수정이 불가능한 특징을 가지고 있습니다. 데이터를 수정하고 싶다면 현재의 데이터를 삭제 후 수정된 데이터를 다시 저장해야 합니다.</span>(단, 마지막 데이터에 추가로 데이터를 이어서 붙이는 것은 가능합니다.)

#### <span style="color:green">데이터 갱신이 불가 하므로  TssNAS 적용 대상 에서 제외.</span>





## <span style="color:blue">WinFsp · Windows 파일 시스템 프록시</span>

출처: https://github.com/winfsp/winfsp

**다운로드** WinFsp는 사용자 모드 파일 시스템을 생성할 수 있는 Windows 컴퓨터용 소프트웨어 구성 요소 세트입니다. 이러한 의미에서 UNIX 계열 컴퓨터에서 동일한 기능을 제공하는 FUSE(사용자 공간의 파일 시스템)와 유사합니다.

### 혜택

#### 안정

WinFsp는 매우 안정적입니다. 알려진 커널 모드 충돌은 없으며 리소스 누수 또는 유사한 문제가 발생하지 않습니다. WinFsp의 이러한 안정성은 [설계](https://github.com/winfsp/winfsp/blob/master/doc/WinFsp-Design.asciidoc) 와 엄격한 [테스트 체제](https://github.com/winfsp/winfsp/blob/master/doc/WinFsp-Testing.asciidoc) 덕분 입니다.

#### 성능

WinFsp는 경쟁 제품보다 성능이 뛰어나며 많은 시나리오에서 NTFS만큼 성능이 좋습니다. [성능](https://github.com/winfsp/winfsp/blob/master/doc/WinFsp-Performance-Testing.asciidoc) 에 대해 자세히 알아보십시오 .

#### 호환성

WinFsp는 NTFS 및 파일 시스템 정확성과의 호환성을 위해 노력합니다. 자세한 내용은 [호환성](https://github.com/winfsp/winfsp/blob/master/doc/NTFS-Compatibility.asciidoc) 문서를 참조하십시오.

#### 사용하기 쉬운

WinFsp에는 사용하기 쉽지만 포괄적인 API가 있습니다.

- 이 간단한 [튜토리얼](https://github.com/winfsp/winfsp/blob/master/doc/WinFsp-Tutorial.asciidoc) 은 파일 시스템을 구축하는 방법을 설명합니다.
- 네이티브 개발에 대해서는 [API 참조 를 참조하세요.](https://winfsp.dev/apiref)
- 관리 개발을 위한 .NET 계층을 포함합니다. [src/dotnet](https://github.com/winfsp/winfsp/blob/master/src/dotnet) 을 참조하십시오 .
- FUSE 2.8 호환성 레이어 포함: [fuse/fuse.h](https://github.com/winfsp/winfsp/blob/master/inc/fuse/fuse.h)
- FUSE 3.2 호환성 레이어 포함: [fuse3/fuse.h](https://github.com/winfsp/winfsp/blob/master/inc/fuse3/fuse.h)

#### 다른 이익

- 모든 릴리스에서 제공되는 서명된 드라이버.
- Free/Libre 및 오픈 소스 소프트웨어에 대한 특별한 예외를 제외 하고 [GPLv3](https://github.com/winfsp/winfsp/blob/master/License.txt) 라이선스에 따라 사용할 수 있습니다.

WinFsp에 대해 자세히 알아보려면 해당 웹 사이트를 방문하십시오. [https://winfsp.dev](https://winfsp.dev/)



### NTFS 호환성

<span style="color:red">WinFsp를 사용하면 NTFS 또는 FAT와 유사하게 동작하는 사용자 모드 파일 시스템을 만들 수 있습니다. WinFsp 파일 시스템의 파일에 액세스하는 일반 Windows 응용 프로그램은 WinFsp 파일 시스템이 기본 Windows 파일 시스템이 아닌지 확인할 수 없으며 판단할 수도 없습니다. 그러나 **NTFS 또는 FAT 특정 확장(예: Defrag)에 액세스하는 특수 응용 프로그램은 WinFsp 파일 시스템에서 제대로 작동하지 않습니다.**</span>

#### 지원되는 기능

WinFsp는 다음 NTFS 기능을 지원합니다.

- 볼륨 정보를 조회하고 설정합니다.
- 파일과 디렉토리를 열고, 만들고, 닫고, 삭제합니다.
- 파일 및 디렉토리 정보를 조회하고 설정합니다.
- 보안 정보(ACL)를 쿼리하고 설정합니다.
- 파일을 읽고 씁니다.
- 메모리 매핑된 I/O.
- 디렉토리 변경 알림.
- 파일을 잠그고 잠금을 해제합니다.
- 기회주의적 자물쇠.
- 명명된 스트림을 열고, 만들고, 닫고, 삭제하고, 쿼리합니다.
- 심볼릭 링크에 대한 특별한 지원으로 포인트를 재분석합니다.
- 확장 속성.
- 삭제 및 이름 바꾸기를 위한 기존 Windows 및 POSIX 의미 체계.

#### 지원되지 않는 기능

WinFsp는 다음 NTFS 기능을 지원하지 않습니다.

- 하드 링크. Windows에서는 거의 사용되지 않습니다. 그러나 WinFsp용으로 구현하는 것이 좋습니다.
- 짧은 파일 이름. 짧은 파일 이름은 과거의 유물입니다. WinFsp는 그들을 지원하지 않기로 의식적인 결정을 내렸습니다.
- <span style="color:red">페이징 파일. 사용자 모드 파일 시스템을 통해 페이징 파일 지원을 제공하는 것은 여러 가지 이유로 불가능합니다.</span>
- 개체 ID. ID(FILE_OPEN_BY_FILE_ID)로 파일을 열 수 없습니다.
- 볼륨 액세스. 볼륨 핸들은 열고 닫을 수 있지만 볼륨을 읽거나 쓸 수는 없습니다. 이러한 작업은 디스크에 정보를 저장하지 않는 대부분의 사용자 모드 파일 시스템에서는 거의 의미가 없습니다.
- 희소 파일. 사용자 모드 파일 시스템은 희소 파일을 자유롭게 구현할 수 있습니다. 그러나 WinFsp는 FSCTL 또는 정보 클래스 코드와 관련된 스파스 파일을 지원하지 않습니다.
- 압축 파일. 사용자 모드 파일 시스템은 압축 파일을 자유롭게 구현할 수 있습니다. 그러나 WinFsp는 압축 관련 FSCTL 또는 정보 클래스 코드를 지원하지 않습니다.
- 암호화된 파일. 사용자 모드 파일 시스템은 암호화된 파일을 자유롭게 구현할 수 있습니다. 그러나 WinFsp는 암호화 관련 FSCTL 또는 정보 클래스 코드를 지원하지 않습니다.
- 할당량
- 변화 저널.
- 조각 모음 지원.

#### 비호환성

이 섹션에서는 NTFS가 모두 지원하는 기능에서 NTFS와 호환되지 않는 항목을 나열합니다.

- WinFsp는 대소문자를 구분하고 대소문자를 구분하지 않는 파일 시스템을 지원합니다. 그러나 대소문자를 구분하지 않는 파일 시스템(SL_CASE_SENSITIVE)에서 대소문자 구분 조회를 지원하지 않습니다.
- NTFS는 파일이 정리될 때 디렉터리 항목의 파일 크기 정보를 업데이트합니다. WinFsp 파일 시스템은 이 동작을 복제하지 않습니다. [관련글 : https://blogs.msdn.microsoft.com/oldnewthing/20111226-00/?p=8813 ]
- NTFS는 스트림에 대한 변경 알림을 지원합니다(대부분 문서화되지 않음). WinFsp도 이를 지원하지만 스트림이 있는 파일이 삭제될 때 단일 알림을 발행한다는 점에서 NTFS와 다릅니다. NTFS는 삭제된 스트림당 하나의 알림을 발행하는 것으로 보입니다.
- WinFsp는 스트림 이름 바꾸기를 지원하지 않습니다.
- NTFS는 모든 재분석 지점(\$Extend\$Reparse:$R:$INDEX_ALLOCATION)을 "포함"하는 특별한 NTFS 전용 "디렉토리"를 열어 모든 재분석 지점의 열거 및 변경 알림을 허용합니다. WinFsp는 이 기능을 지원하지 않습니다.



#### <span style="color:green">확장 기능 및 페이징 파일을 지월 하지 않으므로  TssNAS 적용 대상 에서 제외. 테스트는 할 필요 있음</span>

- passthrough 를 이용해 테스트 필요.
- 않되면 않되는 이유 정확히 파악 필요.





