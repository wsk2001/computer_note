# Batch Script Tutorial

출처: https://www.tutorialspoint.com/batch_script/index.htm

배치 스크립트는 순차적으로 실행되는 명령이있는 행을 포함하는 간단한 텍스트 파일에 저장됩니다. 스크립팅은 쉘에서의 생활을보다 쉽고 생산적으로 만들기 위해 이러한 명령 시퀀스를 자동화하여 이러한 필요성을 완화 할 수있는 방법입니다. 이 자습서에서는 쉽게 이해할 수 있도록 관련 예제와 함께 Batch Script의 기본 기능에 대해 설명합니다.



### Audience

이 튜토리얼은 초보자가 Batch Script의 기본 개념을 이해할 수 있도록 준비되었습니다.



### Prerequisites

컴퓨터 프로그래밍 및 변수, 명령, 구문 등과 같은 개념에 대한 합리적인 지식이 필요합니다.



## Batch Script - Overview

배치 스크립트는 본질적으로 반복되는 명령 시퀀스를 자동화하기 위해 통합되었습니다. 스크립팅은 쉘에서의 생활을보다 쉽고 생산적으로 만들기 위해 이러한 명령 시퀀스를 자동화하여 이러한 필요성을 완화 할 수있는 방법입니다. 대부분의 조직에서 Batch Script는 어떤 방식 으로든 통합되어 작업을 자동화합니다.

배치 스크립트의 일부 기능은-

- 추가 처리가 가능하도록 사용자의 입력을 읽을 수 있습니다.
- 더 나은 자동화 및 스크립팅을 위해 전환하는 경우와 같은 제어 구조가 있습니다.
- 함수 및 배열과 같은 고급 기능을 지원합니다.
- 정규식을 지원합니다.
- Perl과 같은 다른 프로그래밍 코드를 포함 할 수 있습니다.

배치 스크립트의 일반적인 용도는 다음과 같습니다.

- 다른 목적으로 서버를 설정합니다.
- 원하지 않는 파일 또는 로그 파일 삭제와 같은 하우스 키핑 작업 자동화.
- 한 환경에서 다른 환경으로의 애플리케이션 배포 자동화.
- 한 번에 여러 컴퓨터에 프로그램 설치.

배치 스크립트는 순차적으로 실행되는 명령이있는 행을 포함하는 간단한 텍스트 파일에 저장됩니다. 이러한 파일에는 특수 확장자 BAT 또는 CMD가 있습니다. 이 유형의 파일은 명령 인터프리터라는 시스템 파일에서 제공하는 인터페이스 (셸이라고도 함)를 통해 인식되고 실행됩니다. Windows 시스템에서이 인터프리터는 cmd.exe로 알려져 있습니다.

배치 파일을 실행하는 것은 단순히 클릭하기 만하면됩니다. 명령 프롬프트 또는 시작-실행 줄에서 배치 파일을 실행할 수도 있습니다. 이 경우 파일의 경로가 경로 환경에 있지 않으면 전체 경로 이름을 사용해야합니다. 다음은 배치 스크립트의 간단한 예입니다. 이 배치 스크립트는 실행시 현재 디렉토리의 모든 파일을 삭제합니다.

``` cmd
:: Deletes All files in the Current Directory With Prompts and Warnings
::(Hidden, System, and Read-Only Files are Not Affected)
:: @ECHO OFF
DEL . DR
```



## Batch Script - Environment

이 장에서는 배치 스크립트와 관련된 환경에 대해 설명합니다.

### 작성 및 실행

일반적으로 배치 파일을 만들려면 메모장이 사용됩니다. 이것은 배치 파일 생성을위한 가장 간단한 도구입니다. 다음은 배치 스크립트를위한 실행 환경입니다. Windows 시스템에서는 명령 프롬프트 또는 cmd.exe를 통해 수행됩니다. 모든 배치 파일은이 환경에서 실행됩니다.

다음은 cmd.exe를 시작하는 다른 방법입니다-

#### 방법 1-C:\Windows\System32로 이동하여 cmd 파일을 두 번 클릭합니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\writing_executing_method1.jpg)

#### 방법 2-실행 명령을 통해-다음 스냅 샷은 Windows 서버 2012에서 명령 프롬프트 (cmd.exe)를 찾는 방법을 보여줍니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\writing_executing_method2.jpg)

cmd.exe가 실행되면 다음 화면이 표시됩니다. 이것은 배치 스크립트를 실행하기위한 환경입니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\batch_script_environment.jpg)

### 환경 변수

명령 프롬프트에서 배치 파일을 실행하려면 배치 파일이 저장된 위치로 이동하거나 경로 환경 변수에 파일 위치를 입력 할 수 있습니다. 따라서 배치 파일이 C:\Application\bin 위치에 저장되어 있다고 가정하면 PATH 변수 포함을 위해 다음 지침을 따라야합니다.

|   OS    |                            Output                            |
| :-----: | :----------------------------------------------------------: |
| Windows | 문자열을 추가하십시오. C:\Application\bin 을 시스템 변수 PATH의 끝에 추가합니다. |



## Batch Script - Commands

이 장에서는 자주 사용되는 배치 명령 중 일부를 살펴 보겠습니다.

| S.No | Commands & Description                                       |
| ---- | :----------------------------------------------------------- |
| 1    | [VER](https://www.tutorialspoint.com/batch_script/batch_script_ver.htm)<br> This batch command shows the version of MS-DOS you are using. |
| 2    | [ASSOC](https://www.tutorialspoint.com/batch_script/batch_script_assoc.htm)<br/>This is a batch command that associates an extension with a file type (FTYPE), displays existing associations, or deletes an association. |
| 3    | [CD](https://www.tutorialspoint.com/batch_script/batch_script_cd.htm)<br/>This batch command helps in making changes to a different directory, or displays the current directory. |
| 4    | [CLS](https://www.tutorialspoint.com/batch_script/batch_script_cls.htm)<br/>This batch command clears the screen. |
| 5    | [COPY](https://www.tutorialspoint.com/batch_script/batch_script_copy.htm)<br/>This batch command is used for copying files from one location to the other. |
| 6    | [DEL](https://www.tutorialspoint.com/batch_script/batch_script_del.htm)<br/>This batch command deletes files and not directories. |
| 7    | [DIR](https://www.tutorialspoint.com/batch_script/batch_script_dir.htm)<br/>This batch command lists the contents of a directory. |
| 8    | [DATE](https://www.tutorialspoint.com/batch_script/batch_script_date.htm)<br/>This batch command help to find the system date. |
| 9    | [ECHO](https://www.tutorialspoint.com/batch_script/batch_script_echo.htm)<br/>This batch command displays messages, or turns command echoing on or off. |
| 10   | [EXIT](https://www.tutorialspoint.com/batch_script/batch_script_exit.htm)<br/>This batch command exits the DOS console. |
| 11   | [MD](https://www.tutorialspoint.com/batch_script/batch_script_md.htm)<br/>This batch command creates a new directory in the current location. |
| 12   | [MOVE](https://www.tutorialspoint.com/batch_script/batch_script_move.htm)<br/>This batch command moves files or directories between directories. |
| 13   | [PATH](https://www.tutorialspoint.com/batch_script/batch_script_path.htm)<br/>This batch command displays or sets the path variable. |
| 14   | [PAUSE](https://www.tutorialspoint.com/batch_script/batch_script_pause.htm)<br/>This batch command prompts the user and waits for a line of input to be entered. |
| 15   | [PROMPT](https://www.tutorialspoint.com/batch_script/batch_script_prompt.htm)<br/>This batch command can be used to change or reset the cmd.exe prompt. |
| 16   | [RD](https://www.tutorialspoint.com/batch_script/batch_script_rd.htm)<br/>This batch command removes directories, but the directories need to be empty before they can be removed. |
| 17   | [REN](https://www.tutorialspoint.com/batch_script/batch_script_ren.htm)<br/>Renames files and directories |
| 18   | [REM](https://www.tutorialspoint.com/batch_script/batch_script_rem.htm)<br/>This batch command is used for remarks in batch files, preventing the content of the remark from being executed. |
| 19   | [START](https://www.tutorialspoint.com/batch_script/batch_script_start.htm)<br/>This batch command starts a program in new window, or opens a document. |
| 20   | [TIME](https://www.tutorialspoint.com/batch_script/batch_script_time.htm)<br/>This batch command sets or displays the time. |
| 21   | [TYPE](https://www.tutorialspoint.com/batch_script/batch_script_type.htm)<br/>This batch command prints the content of a file or files to the output. |
| 22   | [VOL](https://www.tutorialspoint.com/batch_script/batch_script_vol.htm)<br/>This batch command displays the volume labels. |
| 23   | [ATTRIB](https://www.tutorialspoint.com/batch_script/batch_script_attrib.htm)<br/>Displays or sets the attributes of the files in the curret directory |
|      |                                                              |
| 24   | [CHKDSK](https://www.tutorialspoint.com/batch_script/batch_script_chkdsk.htm)<br/>This batch command checks the disk for any problems. |
| 25   | [CHOICE](https://www.tutorialspoint.com/batch_script/batch_script_choice.htm)<br/>This batch command provides a list of options to the user. |
| 26   | [CMD](https://www.tutorialspoint.com/batch_script/batch_script_cmd.htm)<br/>This batch command invokes another instance of command prompt. |
| 27   | [COMP](https://www.tutorialspoint.com/batch_script/batch_script_comp.htm)<br/>This batch command compares 2 files based on the file size. |
| 28   | [CONVERT](https://www.tutorialspoint.com/batch_script/batch_script_convert.htm)<br/>This batch command converts a volume from FAT16 or FAT32 file system to NTFS file system. |
| 29   | [DRIVERQUERY](https://www.tutorialspoint.com/batch_script/batch_script_driverquery.htm)<br/>This batch command shows all installed device drivers and their properties. |
| 30   | [EXPAND](https://www.tutorialspoint.com/batch_script/batch_script_expand.htm)<br/>This batch command extracts files from compressed .cab cabinet files. |
| 31   | [FIND](https://www.tutorialspoint.com/batch_script/batch_script_find.htm)<br/>This batch command searches for a string in files or input, outputting matching lines. |
| 32   | [FORMAT](https://www.tutorialspoint.com/batch_script/batch_script_format.htm)<br/>This batch command formats a disk to use Windows-supported file system such as FAT, FAT32 or NTFS, thereby overwriting the previous content of the disk. |
| 33   | [HELP](https://www.tutorialspoint.com/batch_script/batch_script_help.htm)<br/>This batch command shows the list of Windows-supplied commands. |
| 34   | [IPCONFIG](https://www.tutorialspoint.com/batch_script/batch_script_ipconfig.htm)<br/>This batch command displays Windows IP Configuration. Shows configuration by connection and the name of that connection. |
| 35   | [LABEL](https://www.tutorialspoint.com/batch_script/batch_script_label.htm)<br/>This batch command adds, sets or removes a disk label. |
| 36   | [MORE](https://www.tutorialspoint.com/batch_script/batch_script_more.htm)<br/>This batch command displays the contents of a file or files, one screen at a time. |
| 37   | [NET](https://www.tutorialspoint.com/batch_script/batch_script_net.htm)<br/>Provides various network services, depending on the command used. |
| 38   | [PING](https://www.tutorialspoint.com/batch_script/batch_script_ping.htm)<br/>This batch command sends ICMP/IP "echo" packets over the network to the designated address. |
| 39   | [SHUTDOWN](https://www.tutorialspoint.com/batch_script/batch_script_shutdown.htm)<br/>This batch command shuts down a computer, or logs off the current user. |
| 40   | [SORT](https://www.tutorialspoint.com/batch_script/batch_script_sort.htm)<br/>This batch command takes the input from a source file and sorts its contents alphabetically, from A to Z or Z to A. It prints the output on the console. |
| 41   | [SUBST](https://www.tutorialspoint.com/batch_script/batch_script_subst.htm)<br/>This batch command assigns a drive letter to a local folder, displays current assignments, or removes an assignment. |
| 42   | [SYSTEMINFO](https://www.tutorialspoint.com/batch_script/batch_script_systeminfo.htm)<br/>This batch command shows configuration of a computer and its operating system. |
| 43   | [TASKKILL](https://www.tutorialspoint.com/batch_script/batch_script_taskkill.htm)<br/>This batch command ends one or more tasks. |
| 44   | [TASKLIST](https://www.tutorialspoint.com/batch_script/batch_script_tasklist.htm)<br/>This batch command lists tasks, including task name and process id (PID). |
| 45   | [XCOPY](https://www.tutorialspoint.com/batch_script/batch_script_xcopy.htm)<br/>This batch command copies files and directories in a more advanced way. |
| 46   | [TREE](https://www.tutorialspoint.com/batch_script/batch_script_tree.htm)<br/>This batch command displays a tree of all subdirectories of the current directory to any level of recursion or depth. |
| 47   | [FC](https://www.tutorialspoint.com/batch_script/batch_script_fc.htm)<br/>This batch command lists the actual differences between two files. |
| 48   | [DISKPART](https://www.tutorialspoint.com/batch_script/batch_script_diskpart.htm)<br/>This batch command shows and configures the properties of disk partitions. |
| 49   | [TITLE](https://www.tutorialspoint.com/batch_script/batch_script_title.htm)<br/>This batch command sets the title displayed in the console window. |
| 50   | [SET](https://www.tutorialspoint.com/batch_script/batch_script_set.htm)<br/>Displays the list of environment variables on the current system. |



## Batch Script - Files

이 장에서는 배치 파일을 생성, 저장, 실행 및 수정하는 방법을 배웁니다.

### Creating Batch Files

배치 파일은 일반적으로 메모장에서 생성됩니다. 따라서 가장 간단한 방법은 메모장을 열고 스크립트에 필요한 명령을 입력하는 것입니다. 이 연습에서는 메모장을 열고 다음 문장을 입력합니다.

``` cmd
:: Deletes All files in the Current Directory With Prompts and Warnings 
::(Hidden, System, and Read-Only Files are Not Affected) 
:: 
@ECHO OFF 
DEL . 
DR
```

### Saving Batch Files

배치 파일이 생성 된 후 다음 단계는 배치 파일을 저장하는 것입니다. 배치 파일의 확장자는 .bat 또는 .cmd입니다. 배치 파일의 이름을 지정할 때 명심해야 할 몇 가지 일반적인 규칙-

- 배치 파일의 이름을 지정할 때 공백을 피하십시오. 다른 스크립트에서 호출 될 때 때때로 문제가 발생합니다.
- ping.cmd와 같이 시스템에서 사용할 수있는 공통 배치 파일을 따라 이름을 지정하지 마십시오.

![](C:\gitbook\tech_note\posts\OS\Windows\images\saving_batch_files.jpg)

위의 스크린 샷은 배치 파일을 저장하는 방법을 보여줍니다. 배치 파일을 저장할 때 유의해야 할 몇 가지 사항이 있습니다.

- .bat 또는 .cmd를 파일 이름 끝에 넣어야합니다.
- "파일 형식"옵션을 "모든 파일"로 선택합니다.
- 전체 파일 이름을 따옴표 ''로 묶습니다.

### Executing Batch Files

다음은 배치 파일을 실행하는 단계입니다-

- **Step 1** − 명령 프롬프트 (cmd.exe)를 엽니 다.
- **Step 2** − .bat 또는 .cmd 파일이 저장된 위치로 이동하십시오.
- **Step 3** − 다음 이미지와 같이 파일 이름을 쓰고 Enter 버튼을 눌러 배치 파일을 실행합니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\executing_batch_files.jpg)

### Modifying Batch Files

다음은 기존 배치 파일을 수정하는 단계입니다.

- **Step 1** − Windows 탐색기를 엽니 다.
- **Step 2** − .bat 또는 .cmd 파일이 저장된 위치로 이동하십시오.
- **Step 3** − 파일을 마우스 오른쪽 버튼으로 클릭하고 상황에 맞는 메뉴에서 "편집"옵션을 선택합니다. 추가 편집을 위해 파일이 메모장에서 열립니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\modifying_batch_files.jpg)

<Batch Script - Syntax>

일반적으로 배치 파일의 첫 번째 줄은 종종 다음 명령으로 구성됩니다.

#### ECHO Command

``` bat
@echo off
```

기본적으로 배치 파일은 실행될 때 명령을 표시합니다. 이 첫 번째 명령의 목적은이 디스플레이를 끄는 것입니다. 'echo off'명령은 'echo off'명령 자체를 제외하고 전체 스크립트의 디스플레이를 끕니다. 앞에있는 'at'기호 '@'는 명령이 자신에게도 적용되도록합니다.

#### Documentation

종종 배치 파일에는 'Rem'명령으로 시작하는 줄이 포함되어 있습니다. 이것은 주석과 문서를 입력하는 방법입니다. 컴퓨터는 Rem을 따르는 줄에있는 모든 것을 무시합니다. 복잡성이 증가하는 배치 파일의 경우 주석을 추가하는 것이 좋습니다.

#### First Batch Script Program

간단한 첫 번째 배치 스크립트 프로그램을 구성 해 보겠습니다. 메모장을 열고 다음 코드 줄을 입력합니다. 파일을“List.cmd”로 저장합니다.

코드는 다음을 수행합니다.

- echo off 명령을 사용하여 코드가 실행될 때 명령이 표시되지 않도록합니다.
- Rem 명령은이 배치 파일이 정확히 무엇을하는지 설명하는 주석을 추가하는 데 사용됩니다.
- dir 명령은 C : Program Files 위치의 내용을 가져 오는 데 사용됩니다.
- ">"명령은 출력을 C:\lists.txt 파일로 리디렉션하는 데 사용됩니다.
- 마지막으로 echo 명령은 사용자에게 작업이 완료되었음을 알리는 데 사용됩니다.

``` bat
@echo off 
Rem This is for listing down all the files in the directory Program files 
dir "C:\Program Files" > C:\lists.txt 
echo "The program has completed"
```

위의 명령이 실행되면 C : Program Files에있는 파일 이름이 C : Lists.txt 파일로 전송되고 명령 프롬프트에 '프로그램이 완료되었습니다'라는 메시지가 표시됩니다.



### Batch Script - Variables

배치 파일에는 두 가지 유형의 변수가 있습니다. 하나는 배치 파일이 호출 될 때 전달 될 수있는 매개 변수 용이고 다른 하나는 set 명령을 통해 수행됩니다.

#### Command Line Arguments

배치 스크립트는 명령 줄 인수 개념을 지원합니다. 여기서 인수는 호출 될 때 배치 파일로 전달 될 수 있습니다. 인수는 % 1, % 2, % 3 등의 변수를 통해 배치 파일에서 호출 할 수 있습니다.

다음 예제는 3 개의 명령 줄 인수를 받아들이고이를 명령 줄 화면에 반영하는 배치 파일을 보여줍니다.

``` bat
@echo off 
echo %1 
echo %2 
echo %3
```

위의 배치 스크립트가 test.bat라는 파일에 저장되고 배치를 다음과 같이 실행하면

``` bat
Test.bat 1 2 3
```

다음은 배치 파일이 실행될 때 명령 프롬프트에서 어떻게 보이는지에 대한 스크린 샷입니다.

![](C:\gitbook\tech_note\posts\OS\Windows\images\command_line_arguments.jpg)

위의 명령은 다음 출력을 생성합니다.

``` bat
1 
2 
3
```

배치를 다음과 같이 실행하면

``` bat
Example 1 2 3 4
```

출력은 여전히 위와 동일하게 유지됩니다. 그러나 네 번째 매개 변수는 무시됩니다.

#### Set Command

변수를 초기화 할 수있는 다른 방법은 'set'명령을 사용하는 것입니다. 다음은 set 명령의 구문입니다.

**Syntax**

``` bat
set /A variable-name=value
```

where,

- **variable-name**: 설정하려는 변수의 이름입니다.
- **value** : 변수에 대해 설정해야하는 값입니다.
- **/A –**  이 스위치는 값이 본질적으로 숫자 여야하는 경우에 사용됩니다.

다음 예제는 set 명령을 사용할 수있는 간단한 방법을 보여줍니다.

**Example**

``` bat
@echo off 
set message=Hello World 
echo %message%
```

- 위의 코드 스 니펫에서 message라는 변수가 정의되고 'Hello World'값으로 설정됩니다.
- 변수 값을 표시하려면 변수를 % 기호로 묶어야합니다.

**Output**

위의 명령은 다음 출력을 생성합니다.

``` bat
Hello World
```

#### Working with Numeric Values

배치 스크립트에서 숫자 값을 보유하는 변수를 정의 할 수도 있습니다. / A 스위치를 사용하면됩니다.

다음 코드는 / A 스위치를 사용하여 숫자 값을 설정할 수있는 간단한 방법을 보여줍니다.

``` bat
@echo off 
SET /A a = 5 
SET /A b = 10 
SET /A c = %a% + %b% 
echo %c%
```

- 먼저 두 변수 a와 b의 값을 각각 5와 10으로 설정합니다.
- 이러한 값을 추가하고 변수 c에 저장합니다.
- 마지막으로 변수 c의 값을 표시합니다.

위 프로그램의 출력은 15입니다.

모든 산술 연산자는 배치 파일에서 작동합니다. 다음 예제는 배치 파일에서 사용할 수있는 산술 연산자를 보여줍니다.

``` bat
@echo off 
SET /A a = 5 
SET /A b = 10 
SET /A c = %a% + %b% 
echo %c% 
SET /A c = %a% - %b% 
echo %c% 
SET /A c = %b% / %a% 
echo %c% 
SET /A c = %b% * %a% 
echo %c%
```

위의 명령은 다음 출력을 생성합니다.

``` bat
15 
-5 
2 
50
```

#### Local vs Global Variables

모든 프로그래밍 언어에는 변수를 일종의 범위, 즉 액세스 할 수있는 코드 섹션으로 표시하는 옵션이 있습니다. 일반적으로 전역 범위를 가진 변수는 프로그램에서 어디서나 액세스 할 수있는 반면 로컬 범위 변수에는 액세스 할 수있는 정의 된 경계가 있습니다.

DOS 스크립팅에는 로컬 및 전역 범위 변수에 대한 정의도 있습니다. 기본적으로 변수는 전체 명령 프롬프트 세션에 전역 적입니다. SETLOCAL 명령을 호출하여 스크립트 범위에 대한 로컬 변수를 만듭니다. SETLOCAL을 호출 한 후 ENDLOCAL을 호출하거나 EXIT를 호출하거나 실행이 스크립트의 EOF (파일 끝)에 도달하면 모든 변수 할당이 되돌아갑니다. 다음 예제는 스크립트에 로컬 및 글로벌 변수가 설정된 경우의 차이점을 보여줍니다.

**Example**

``` bat
@echo off 
set globalvar = 5
SETLOCAL
set var = 13145
set /A var = %var% + 5
echo %var%
echo %globalvar%
ENDLOCAL
```

위의 프로그램에 대해 주목해야 할 몇 가지 중요한 사항입니다.

- 'globalvar'는 전역 범위로 정의되며 전체 스크립트에서 사용할 수 있습니다.
- ‘var‘변수는‘SETLOCAL’과‘ENDLOCAL’블록으로 묶여 있기 때문에 로컬 범위에서 정의됩니다. 따라서이 변수는‘ENDLOCAL’문이 실행되는 즉시 삭제됩니다.

**Output**

위의 명령은 다음 출력을 생성합니다.

``` bat
13150
5
```

echo % var % 명령은 ENDLOCAL 문 뒤에‘var’변수가 더 이상 존재하지 않기 때문에 아무 것도 생성하지 않습니다.

#### 환경 변수 작업

배치 파일에서 사용할 변수가있는 경우 항상 환경 변수를 사용하는 것이 좋습니다. 환경 변수가 정의되면 % 기호를 통해 액세스 할 수 있습니다. 다음 예제는 시스템에 정의 된 JAVA_HOME을 보는 방법을 보여줍니다. JAVA_HOME 변수는 일반적으로 다양한 응용 프로그램에서 사용되는 주요 구성 요소입니다.

``` bat
@echo off 
echo %JAVA_HOME%
```

출력에는 시스템마다 종속되는 JAVA_HOME 디렉토리가 표시됩니다. 다음은 출력의 예입니다.

``` bat
C:\Atlassian\Bitbucket\4.0.1\jre
```



### Batch Script - Comments

생성 된 스크립트에 대한 설명이나 문서를 추가하는 것은 항상 좋은 방법입니다. 이는 스크립트가 실제로 수행하는 작업을 이해하기 위해 스크립트 유지 관리에 필요합니다.

예를 들어, 주석 형식이없는 다음 코드를 생각해보십시오. 다음 대본을 개발하지 않은 평범한 사람이 대본을 이해하려고하면 그 사람이 대본이 실제로 무엇을하는지 이해하는 데 많은 시간이 걸립니다.

``` bat
ECHO OFF 
IF NOT "%OS%"=="Windows_NT" GOTO Syntax 
ECHO.%* | FIND "?" >NUL 
IF NOT ERRORLEVEL 1 GOTO Syntax 
IF NOT [%2]==[] GOTO Syntax 
SETLOCAL 
SET WSS= 
IF NOT [%1]==[] FOR /F "tokens = 1 delims = \ " %%A IN ('ECHO.%~1') DO SET WSS = %%A 
FOR /F "tokens = 1 delims = \ " %%a IN ('NET VIEW ^| FIND /I "\\%WSS%"') DO FOR /F 
"tokens = 1 delims = " %%A IN ('NBTSTAT -a %%a ^| FIND /I /V "%%a" ^| FIND "<03>"') 
DO ECHO.%%a %%A 
ENDLOCAL 
GOTO:EOF 
ECHO Display logged on users and their workstations. 
ECHO Usage: ACTUSR [ filter ] 
IF "%OS%"=="Windows_NT" ECHO Where: filter is the first part 
of the computer name^(s^) to be displayed
```

#### Comments Using the Rem Statement

배치 스크립트에서 주석을 작성하는 방법에는 두 가지가 있습니다. 하나는 Rem 명령을 사용하는 것입니다. Rem 문 다음에 오는 모든 텍스트는 주석으로 처리되며 실행되지 않습니다. 다음은이 문의 일반 구문입니다.

**Syntax**

``` bat
Rem Remarks
```

여기서‘비고’는 추가해야 할 주석입니다.

다음 예제는 Rem 명령을 사용할 수있는 간단한 방법을 보여줍니다.

**Example**

``` bat
@echo off 
Rem This program just displays Hello World 
set message=Hello World 
echo %message%
```

**Output**

위의 명령은 다음 출력을 생성합니다. Rem 문이있는 줄이 실행되지 않음을 알 수 있습니다.

``` bat
Hello World
```



#### Comments Using the :: Statement

배치 스크립트에서 주석을 작성하는 다른 방법은 :: 명령을 사용하는 것입니다. :: 문 뒤에 오는 모든 텍스트는 주석으로 처리되며 실행되지 않습니다. 다음은이 문의 일반 구문입니다.

**Syntax**

``` bat
:: Remarks
```

여기서‘Remarks’는 추가해야하는 주석입니다.

다음 예제는 '::'명령의 사용법을 보여줍니다.

**Example**

``` bat
@echo off 
:: This program just displays Hello World 
set message = Hello World 
echo %message%
```

**Output**

위의 명령은 다음 출력을 생성합니다. :: 문이있는 줄은 실행되지 않습니다.

``` bat
Hello World
```

**Note** − Rem 줄이 너무 많으면 결국 배치 파일의 각 코드 줄을 실행해야하기 때문에 코드 속도가 느려질 수 있습니다.

이 항목의 시작 부분에서 본 큰 스크립트의 예를보고 문서가 추가 될 때 어떻게 보이는지 살펴 보겠습니다.

``` bat
::===============================================================
:: The below example is used to find computer and logged on users
::
::===============================================================
ECHO OFF 
:: Windows version check 
IF NOT "%OS%"=="Windows_NT" GOTO Syntax 
ECHO.%* | FIND "?" >NUL 
:: Command line parameter check 
IF NOT ERRORLEVEL 1 GOTO Syntax
IF NOT [%2]==[] GOTO Syntax 
:: Keep variable local 
SETLOCAL 
:: Initialize variable 
SET WSS= 
:: Parse command line parameter 
IF NOT [%1]==[] FOR /F "tokens = 1 delims = \ " %%A IN ('ECHO.%~1') DO SET WSS = %%A 
:: Use NET VIEW and NBTSTAT to find computers and logged on users 
FOR /F "tokens = 1 delims = \ " %%a IN ('NET VIEW ^| FIND /I "\\%WSS%"') DO FOR /F 
"tokens = 1 delims = " %%A IN ('NBTSTAT -a %%a ^| FIND /I /V "%%a" ^| FIND 
"<03>"') DO ECHO.%%a %%A 
:: Done 
ENDLOCAL
GOTO:EOF 
:Syntax 
ECHO Display logged on users and their workstations. 
ECHO Usage: ACTUSR [ filter ] 
IF "%OS%"=="Windows_NT" ECHO Where: filter is the first part of the 
computer name^(s^) to be displayed
```

이제 코드를 개발하지 않은 사용자가 코드를 더 잘 이해할 수있게되었으며 따라서 유지 관리가 더 용이하다는 것을 알 수 있습니다.



### Batch Script - Strings

DOS에서 문자열은 'Hello, World!'와 같이 정렬 된 문자 모음입니다.

| S.No | Strings & Description                                        |
| ---- | :----------------------------------------------------------- |
| 1    | [Create String](https://www.tutorialspoint.com/batch_script/batch_script_create_string.htm)<br>A string can be created in DOS in the following way. |
| 2    | [Empty String](https://www.tutorialspoint.com/batch_script/batch_script_empty_string.htm)<br/>Empty String |
| 3    | [String Interpolation](https://www.tutorialspoint.com/batch_script/batch_script_string_interpolation.htm)<br/>String interpolation is a way to construct a new String value from a mix of constants, variables, literals, and expressions by including their values inside a string literal. |
| 4    | [String Concatenation](https://www.tutorialspoint.com/batch_script/batch_script_string_concatenation.htm)<br/>You can use the set operator to concatenate two strings or a string and a character, or two characters. Following is a simple example which shows how to use string concatenation. |
| 5    | [String length](https://www.tutorialspoint.com/batch_script/batch_script_string_length.htm)<br/>In DOS scripting, there is no length function defined for finding the length of a string. There are custom-defined functions which can be used for the same. Following is an example of a custom-defined function for seeing the length of a string. |
| 6    | [toInt](https://www.tutorialspoint.com/batch_script/batch_script_toint.htm)<br/>A variable which has been set as string using the set variable can be converted to an integer using the /A switch which is using the set variable. The following example shows how this can be accomplished. |
| 7    | [Align Right](https://www.tutorialspoint.com/batch_script/batch_script_align_right.htm)<br/>This used to align text to the right, which is normally used to improve readability of number columns. |
| 8    | [Left String](https://www.tutorialspoint.com/batch_script/batch_script_left_string.htm)<br/>This is used to extract characters from the beginning of a string. |
| 9    | [Mid String](https://www.tutorialspoint.com/batch_script/batch_script_mid_string.htm)<br/>This is used to extract a substring via the position of the characters in the string. |
| 10   | [Remove](https://www.tutorialspoint.com/batch_script/batch_script_remove.htm)<br/>The string substitution feature can also be used to remove a substring from another string. |
| 11   | [Remove Both Ends](https://www.tutorialspoint.com/batch_script/batch_script_remove_both_ends.htm)<br/>This is used to remove the first and the last character of a string. |
| 12   | [Remove All Spaces](https://www.tutorialspoint.com/batch_script/batch_script_remove_all_spaces.htm)<br/>This is used to remove all spaces in a string via substitution. |
| 13   | [Replace a String](https://www.tutorialspoint.com/batch_script/batch_script_replace_string.htm)<br/>To replace a substring with another string use the string substitution feature. |
| 14   | [Right String](https://www.tutorialspoint.com/batch_script/batch_script_right_string.htm)<br/>This is used to extract characters from the end of a string. |