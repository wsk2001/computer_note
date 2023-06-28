# Bash를 다루는 필수 명령어 71선

출처: https://qiita.com/kjm_nuco/items/6a4faf4d026d130e7db1



# 소개

`mkdir`, `ls`, `cp`, `touch`, `mv`, `rm`또는 그 이상의 명령을 건드리지 않습니다. . . 라는 분, 더 커맨드를 다루고 싶다! 라는 분. . . 필견.
기본적이지만 전부 망라하기에는 힘든 기본 커맨드 및 쇼트 컷을 넘치는 것만 71선 전달합니다. 많이 있으므로, 제목에 어떤 명령을 쓰는 것을 활용하십시오.
일부, mac 유저용으로 되어 있는 것을 양해 바랍니다.



# Ctrl을 사용한 바로 가기

먼저, `Ctrl`을 사용한 바로 가기를 소개합니다.

### `Ctrl-A`　줄 처음 으로 이동

[![스크린샷 2023-05-17 15.11.21.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F3e597bb1-9eb5-e7d9-3e7d-22f4ce336526.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a1e6060446a7c6791681097ba1a83167)](https://camo.qiitausercontent.com/056d48526ab787ca4dac3e141f930fd771299bac/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f33653539376262312d396562352d653764392d336537642d3232663463653333363532362e706e67)
↓
[![스크린샷 2023-05-17 15.37.11.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F5eb08b46-2c7b-ce2f-65f3-ec45930be08f.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=ebb6f0b66ae0ae7c63633e3871627b66)](https://camo.qiitausercontent.com/20f2806a39bb2892ee5eff025fa9870b2e74fb67/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f35656230386234362d326337622d636532662d363566332d6563343539333062653038662e706e67)



### `Ctrl-E`　줄 끝 이동

[![스크린샷 2023-05-17 15.11.21.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F3e597bb1-9eb5-e7d9-3e7d-22f4ce336526.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a1e6060446a7c6791681097ba1a83167)](https://camo.qiitausercontent.com/056d48526ab787ca4dac3e141f930fd771299bac/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f33653539376262312d396562352d653764392d336537642d3232663463653333363532362e706e67)
↓
[![스크린샷 2023-05-17 15.37.41.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F2c405c02-1299-eb13-7121-560b82623b47.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=26372544efd243a19afecac6a772b381)](https://camo.qiitausercontent.com/05c440b8f96043d5a5cdac21c3ed6299270a2bf3/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f32633430356330322d313239392d656231332d373132312d3536306238323632336234372e706e67)



### `Ctrl-W`　직전 한 단어 삭제

[![스크린샷 2023-05-17 16.04.17.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F9736f3bc-fa85-5bdc-a29b-ac61e8a1d250.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=df32c71687ade7a6d68be1140edf80a3)](https://camo.qiitausercontent.com/534ee20818941239fea1c56cab9cb33d60fdda20/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f39373336663362632d666138352d356264632d613239622d6163363165386131643235302e706e67)
↓
[![스크린샷 2023-05-17 16.04.34.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2Fbeb6e403-ec39-fe6b-c816-45ed4a77fd82.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=4419f9b38bafc5c836cd046665fb7533)](https://camo.qiitausercontent.com/fb657b231be5110cd7f990a70d44a0649e39dbf7/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f62656236653430332d656333392d666536622d633831362d3435656434613737666438322e706e67)



### `Ctrl-U`　커서 왼쪽에서 줄 처음까지 삭제

[![스크린샷 2023-05-17 15.11.21.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F3e597bb1-9eb5-e7d9-3e7d-22f4ce336526.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a1e6060446a7c6791681097ba1a83167)](https://camo.qiitausercontent.com/056d48526ab787ca4dac3e141f930fd771299bac/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f33653539376262312d396562352d653764392d336537642d3232663463653333363532362e706e67)
↓
[![스크린샷 2023-05-17 15.33.54.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2Fb67260a1-6f06-60ec-f9c5-fcb5b40e1eeb.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=b0a12bfb8baef0f9842212de25d868f3)](https://camo.qiitausercontent.com/efedb24192ad11cf63d2637f2c559730cbe9f92d/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f62363732363061312d366630362d363065632d663963352d6663623562343065316565622e706e67)



### `Ctrl-K`　커서 위치에서 끝까지 행 삭제

[![스크린샷 2023-05-17 15.11.21.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F3e597bb1-9eb5-e7d9-3e7d-22f4ce336526.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a1e6060446a7c6791681097ba1a83167)](https://camo.qiitausercontent.com/056d48526ab787ca4dac3e141f930fd771299bac/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f33653539376262312d396562352d653764392d336537642d3232663463653333363532362e706e67)
↓
[![스크린샷 2023-05-17 15.42.14.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2Feca6b4e7-18e4-62c6-b17d-b662a26765aa.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=bfd9e031942777e345166640c6f166e1)](https://camo.qiitausercontent.com/c20f14a828de371890479c94e4069e1646255ee3/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f65636136623465372d313865342d363263362d623137642d6236363261323637363561612e706e67)



### `Ctrl-Y`　직전에 삭제한 내용을 붙여넣기

`Ctrl-K`야 `Ctrl-U`에서 삭제한 내용을 붙여넣습니다. 클립보드와는 별도로 취급됩니다.

예) `Ctrl-K`와를 `Ctrl-Y`사용 ↓ ↓개행 ↓
[![스크린샷 2023-05-17 15.11.21.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F3e597bb1-9eb5-e7d9-3e7d-22f4ce336526.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a1e6060446a7c6791681097ba1a83167)](https://camo.qiitausercontent.com/056d48526ab787ca4dac3e141f930fd771299bac/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f33653539376262312d396562352d653764392d336537642d3232663463653333363532362e706e67)
`Ctrl-K`
[![스크린샷 2023-05-17 15.42.14.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2Feca6b4e7-18e4-62c6-b17d-b662a26765aa.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=bfd9e031942777e345166640c6f166e1)](https://camo.qiitausercontent.com/c20f14a828de371890479c94e4069e1646255ee3/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f65636136623465372d313865342d363263362d623137642d6236363261323637363561612e706e67)

[![스크린샷 2023-05-17 15.44.24.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2Fd9cf6762-0872-c9ad-923c-b238557cc323.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=94c023b4c1e1af2e99c0c4a981aa9601)](https://camo.qiitausercontent.com/f1ad38c87dc29e0dae06da870d5dd378bb0ec401/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f64396366363736322d303837322d633961642d393233632d6232333835353763633332332e706e67)
`Ctrl-Y`
[![스크린샷 2023-05-17 15.42.31.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F1b4281c8-896c-7965-4770-6ee02ec83cd0.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=01a483838af108433f8aca01b6402215)](https://camo.qiitausercontent.com/38b20ca63c24234970e21450f2b458e9b4c3c878/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f31623432383163382d383936632d373936352d343737302d3665653032656338336364302e706e67)



### `Ctrl-Q`　출력 일시정지 해제

출력 일시정지( `Ctrl-s`)를 해제한다.
확실히 `Ctrl-s`키를 눌러 버려 콘솔이나 터미널이 키 입력을 받아들이지 않게 되었을 때는 로 원래 `Ctrl-Q`상태로 돌아간다.



# TAB로 보완

Tab 키를 사용하여 파일 폴더 경로를 보완할 수 있으며, 예상되는 명령도 보완할 수 있습니다. 예측 후보가 여러 개인 경우 아래와 같이 표시됩니다.

```
$ d
dappprof                       dirname
dapptrace                      dirs
dash                           disklabel
date                           diskutil
db_archive                     disown
db_checkpoint                  display
db_codegen                     dispqlen.d
db_deadlock                    distnoted
db_dump                        ditto
db_hotbackup                   djpeg
db_load                        djxl
db_printlog                    dmc
db_recover                     dmesg
db_stat                        dnctl
db_upgrade                     dns-sd
db_verify                      do
--More--
```

덧붙여서 `zsh`, tab 키를 2회 누르면 후보가 알파벳순으로 코로코로 바뀌는 사양이 있습니다.

# `& / && / ;`　여러 명령을 계속 실행　

### `$ command1 & command2`

이 쓰기로 `command1`끝나기를 기다리지 않고 `command2`실행할 수 있습니다.

예:

```
$ python3 foo/hoge.py & python3 foo/hogehoge.py
[1] 55312
hogehoge.py is executed
hoge.py is executed
[1]  + done       python3 foo/hoge.py
```

[![스크린샷 2023-04-25 14.27.44.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F7cbaa537-da84-606b-8b71-543084b49024.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=bffd4989ddc4d6e54f20a6210e237120)](https://camo.qiitausercontent.com/4775936059a8555df1ffe8773efb5e377f2f28ea/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f37636261613533372d646138342d363036622d386237312d3534333038346234393032342e706e67)

이 예에서는 `command1`에 해당하는 `python3 foo/hoge.py`것보다 먼저 `command2`에 `python3 foo/hogehoge.py`해당하는 작업이 완료되었습니다. `&`를 사용하면 여러 명령을 병렬로 처리할 수 있습니다. 마지막으로 작성한 명령 만 포그라운드에서 실행되고 나머지 (이번 예제에서는) `command1`는 백그라운드에서 실행됩니다. `command1`실행이 완료되는 순서 `command2`는 보장되지 않습니다.
또한 [1] 55312는 백그라운드에서 실행된 명령의 작업 번호와 프로세스 ID라고 합니다. 작업 번호가 1이고 프로세스 ID가 55312입니다. `+ done`는 작업이 완료되었음을 나타냅니다.
그건 그렇고,

```
$ command1 & command2 & command3
```

처럼 3개 이상 나란히 쓸 수도 있습니다.

또한,

```
python3 bar.py &
```

와 같이 쓰면, 처리를 백그라운드에서 실행시켜, 실행하고 있는 동안 (완료되어 있지 않아도), 프롬프트는 자유롭게 사용할 수 있습니다.

### `$ command1 && command2`

```
$ ls foooo && python3 bar.py
ls: foooo: No such file or directory
```

`&&`하지만 여러 명령을 동시에 실행할 수는 있지만, 이것은 `&`달리 순차적으로 실행됩니다. 즉, 포어라든지 백이든지 하지 않는 방식으로, `command1`의 처리가 끝나지 않으면, `command2`가 실행되지 않습니다.
또한 예제를 보면 알 수 있듯이 `command1`실패하면 `command2`실행되지 않습니다. 이 예제에서는 `foooo`디렉토리가 없으므로 오류가 발생했으며 계속 `python3 bar.py`실행되지 않았습니다.

### `$ command1 ; command2`

`;`는 거의`&&` 동일한 사용법을 사용할 수 있지만 `;`이전 명령의 성공 또는 실패에 관계없이 다음 명령을 실행할 수 있습니다.

# Ctrl + z, `fg`　명령 일시 중지 및 재개　

```
$ python3 bar.py
^Z
[1]+  Stopped                 python3 bar.py
$ fg
python3 bar.py
bar.py is executed
```

`Ctrl + C`에서 커맨드를 강제 정지할 수 있고, `Ctrl + D`로, 특별한 상태가 빠져 나올 수 있는 것을 알고 있는 분은 많을 것입니다.
`Ctrl + Z`는 실행 중인 명령을 일시 중지할 수 있는 바로 가기입니다.
일시 중지된 명령은 `fg`명령으로 다시 시작할 수 있습니다.
또한 `jobs`명령으로 배경의 상태를 확인할 수 있습니다.

```
$ jobs
[1]-  Running                 python3 bar.py &
[2]+  Stopped                 python3 foo/hoge.py
```

# `{}`　여러 디렉토리, 파일을 한 번에 작성

### `$ touch {}`

`{}`을 사용하면 비슷한 이름의 파일 디렉토리를 동시에 만들 수 있습니다.

```
touch sample{1,2,3}.txt
```

[![스크린샷 2023-04-25 16.01.42.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F519f2152-8e20-40b1-e7d7-8f44eca228c9.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=6c1b5a5897839024d331b4f030e82906)](https://camo.qiitausercontent.com/503965e41f8d6ee122e589682a92b924fc1124da/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f35313966323135322d386532302d343062312d653764372d3866343465636132323863392e706e67)
{} 안에서 바꾸고 싶은 문자 뒤를 `,`치십시오. `,`뒤에 공백이 열리지 않도록 주의하십시오.
이 명령은 다음과 같이 작성할 수도 있습니다.

```
touch sample1.txt sample2.txt sample3.txt
```

임의의 이름 파일을 단번에 만들 수 있군요.

### `$ mkdir {}`

```
$ mkdir -p fruits/{apple,orange,banana}_imgs
```

[![스크린샷 2023-04-25 15.54.48.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F4234980c-747a-09ad-61aa-c94e54af2fd0.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=369996d75d058bc1209310d5c339af37)](https://camo.qiitausercontent.com/d506b8ff53cb3079478c77c058b95873f3940c0e/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f34323334393830632d373437612d303961642d363161612d6339346535346166326664302e706e67)

디렉토리의 경우도 마찬가지입니다. 예에서는, `fruits`라는 원래 존재하지 않는 디렉토리를 `-p`옵션으로 단번에 만들고 있습니다. `-p`옵션은 다중 계층 디렉토리를 작성할 때 유용한 옵션입니다.

### `cp foo{,_copy}.py`

`cp foo{,_copy}.py`
는
`cp foo.py foo_copy.py`동일합니다.

`cp foo{.py,.bak}`
는
`cp foo.py foo.bak`동일합니다.
백업을 하고 싶을 때 사용할 수 있네요.

### `echo {1..10}.txt`　숫자 범위 확장

```
$ echo {1..10..2}.txt
1.txt 3.txt 5.txt 7.txt 9.txt
```

이것은 `{1..10..2}`1에서 10까지의 숫자를 2씩 늘리면서 전개한다는 의미입니다. `{1..10}.txt`그러면 1에서 10까지 차례로 전개됩니다.
일부 `bash`에서는 지원되지 않을 수도 있습니다.

# `echo`　문자열 표시

문자열을 표시하는 간단한 명령입니다.

```
$ echo Hello!
Hello!
```

`$`를 사용하여 환경 변수를 지정할 수 있습니다.

```
$ a=everyone
$ echo Hello, $a.
Hello, everyone.
```

### `$ echo 文字列 > ファイル名`　문자열을 파일에 씁니다.

`>`와 함께 사용하면 파일에 쓸 수 있습니다. 덧붙여서 이 쓰는 방법은 신규 파일의 작성도 동시에 해 줍니다.

```
$ echo apple > sample1.txt
$ cat sample1.txt
apple
```

# `ls -l`　파일 속성 목록

파일과 파일의 속성 목록을 가져옵니다.
왼쪽부터 순서대로

- 권한
- 하드 링크 수
- 소유자
- 그룹
- 파일 크기
- 최종 갱신 일시
- 파일(디렉토리) 이름

을 표시합니다.
권한에 대해 자세히 알고 싶은 분은 다음을 참조하십시오.



```
$ ls -l 
total 16
-rw-r--r--  1 ishiishuto  staff   80  4 25 15:32 20seconds.py
-rw-r--r--  1 ishiishuto  staff   69  5 17 15:56 bar.py
drwxr-xr-x  4 ishiishuto  staff  128  4 22 15:31 foo
drwxr-xr-x  5 ishiishuto  staff  160  4 25 15:54 fruits
drwxr-xr-x  5 ishiishuto  staff  160  5  5 16:58 sample
drwxr-xr-x  6 ishiishuto  staff  192  4 25 16:18 venv
```

# `pwd`　현재 디렉토리 얻기

「print working directory」의 약자입니다. 현재 자신의 위치 (현재 디렉토리)를 표시합니다.

```
$ pwd
/Users/ishiishuto/work/sample
```

# `top`　실행 중인 프로세스를 실시간으로 표시

실행 중인 프로세스를 실시간으로 CPU 이용률이 높은 순서로 표시합니다.

```
Processes: 623 total, 2 running, 621 sleeping, 3793 threads                                                                    16:32:08
Load Avg: 1.85, 1.65, 1.60  CPU usage: 6.71% user, 6.13% sys, 87.15% idle  SharedLibs: 483M resident, 86M data, 134M linkedit.
MemRegions: 910308 total, 5452M resident, 234M private, 1782M shared. PhysMem: 15G used (1660M wired, 4480M compressor), 240M unused.
VM: 323T vsize, 4142M framework vsize, 0(0) swapins, 0(0) swapouts. Networks: packets: 2823931/3335M in, 933755/226M out.
Disks: 4904757/66G read, 7729950/66G written.

PID    COMMAND      %CPU  TIME     #TH    #WQ  #PORT MEM    PURG   CMPRS  PGRP  PPID  STATE    BOOSTS            %CPU_ME %CPU_OTHRS UID
159    WindowServer 29.7  33:46.86 21     6    2153  500M   14M    114M-  159   1     sleeping *0[1]             2.44052 1.14758    88
2286   Code Helper  14.2  02:15.11 24     1    239+  191M+  0B     71M-   2278  2278  sleeping *0[1]             0.00000 0.00000    501
462    Google Chrom 13.3  23:01.08 48     2    1624  715M   0B     193M-  462   1     sleeping *2307[212]        0.83111 1.80302    501
0      kernel_task  13.3  37:52.94 477/8  0    0     2448K+ 0B     0B     0     0     running   0[0]             0.00000 0.00000    0
19880  top          7.1   00:03.44 1/1    0    38    6481K  0B     0B     19880 2370  running  *0[1]             0.00000 0.00000    0
2281   Code Helper  5.1   00:56.61 12     3    229+  331M+  768K   48M-   2278  2278  sleeping *1[11]            0.08256 0.00000    501
2278   Electron     3.9   01:23.95 31     2    516   106M   16K    49M    2278  1     sleeping *0[5887]          0.25412 0.56849    501
488    Simulator    1.1   01:10.24 4      2    273   43M    0B     32M    488   1     sleeping *0[11548]         0.00000 0.00000    501
259    TouchBarServ 0.9   02:33.08 4      2    284   19M    2880K  8528K  259   1     sleeping *0[1]             0.00000 0.14946    0
530    Google Chrom 0.9   08:49.14 25     5    948   565M   62M    95M    462   462   sleeping *1[5]             0.00000 0.00000    501
```

# `cat`　파일 내용 확인

`cat`파일의 내용을 확인하는 명령입니다.

```
$ cat sample2.txt
APPLE
BANANA
$ cat sample3.txt 
orange
```

### 파일 병합

여러 파일을 결합하여 하나의 파일로 만들 수도 있습니다.

```
$ cat sample1.txt sample3.txt > marged.txt
$ cat marged.txt                          
apple
orange
```

### 파일 내용 추가

```
cat new_content.txt >> existing_file.txt
```

cat 명령에는 `>>`연산자를 사용하여 기존 파일에 새 내용을 추가할 수도 있습니다. 위의 예에서는 `new_content.txt`내용이 `existing_file.txt`추가됩니다.

# `head`　선두의 n행을 추출한다

```
$ cat sample1.txt 
Apple
Banana
Orange
Strawberry
Grape
Pineapple
Watermelon
Mango
Lemon
Peach
Pear
Cherry
Kiwi
Blueberry
Raspberry
Avocado
Papaya
Plum
Fig
Coconut
```

반대로 기본값은 처음 10행을 추출합니다.

```
$ head sample1.txt 
Apple
Banana
Orange
Strawberry
Grape
Pineapple
Watermelon
Mango
Lemon
Peach
```

`$ head -15 sample1.txt`그렇다면 15 줄을 꺼낼 수 있습니다.
파이프 `|`를 사용하여 `ls . -l | head -20`등을 사용하면 현재 디렉토리 아래의 20 개의 파일에 대한 정보를 얻을 수 있습니다.

# `tail`　말미의 n행을 빼낸다

`head`반대로 `tail`명령은 파일 끝에서 지정된 행 수의 내용을 표시합니다.
`head`와 마찬가지로 파이프 `|`와 병용하여 사용할 수 있습니다.

```
$ tail sample1.txt 
Pear
Cherry
Kiwi
Blueberry
Raspberry
Avocado
Papaya
Plum
Fig
Coconut
```

# `kill`　프로세스 ID를 지정하고 강제 종료

```
$ kill <PID>
```

`<PID>`는 프로세스 ID를 지정합니다. 프로세스 ID는 `ps`명령으로 확인할 수 있습니다. 응용 프로그램이 응답하지 않을 때 등에 사용합니다.

# `killall`　프로세스의 이름을 지정하고 강제 종료

지정된 프로세스 이름과 일치하는 모든 프로세스를 종료합니다. `killall`명령과 `kill`달리 프로세스 이름을 지정하기만 하면 프로세스 ID(PID)를 확인할 필요가 없습니다.

```
$ killall <process_name>
```

`<process_name>`종료할 프로세스의 이름을 지정합니다.

# `w`　사용자 로그인 정보

시스템에 현재 로그인한 사용자 목록과 각 사용자의 활동 정보를 표시하는 데 사용됩니다.

- `USER`: 사용자 이름
- `TTY`: 로그인에 사용되는 터미널 이름
- `FROM`: 로그인 소스 호스트 이름 또는 IP 주소
- `LOGIN@`: 로그인 일시
- `IDLE`: 마지막 작업 이후의 경과 시간
- `JCPU`: 사용자가 실행중인 작업의 CPU 시간
- `PCPU`: 사용자가 실행중인 프로세스의 CPU 시간
- `WHAT`: 사용자의 현재 명령 또는 프로그램

```
$ w
 23:45:10 up 1 day,  3:30,  3 users,  load average: 0.00, 0.01, 0.05
USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
user1    pts/0    192.168.1.101    23:30    5.00s  0.10s  0.00s w
user2    pts/1    192.168.1.102    23:20   15.00s  0.05s  0.00s bash
user3    pts/2    192.168.1.103    23:10    0.00s  0.02s  0.00s sshd: user3 [priv]
```

# `cd $_`,`cd -`

디렉토리를 전환하는 명령 `cd`의 편리한 사용법을 소개합니다.

### `cd $_`

`mkdir`한 명령으로 즉시 이동할 수 있습니다.

```
sample user_name $ mkdir new_dir
sample user_name $ cd $_
new_dir user_name $
```

### `cd -`

이동하기 전에 디렉토리로 이동합니다.

```
sample $ mkdir -p new_dir/bar/foo
sample $ cd $_
foo $ cd -
sample $
```

# `mv`　파일 이동 및 이름 바꾸기

`mv`파일을 이동하는 데 사용되는 명령입니다.

```
$ mv ファイル名 目的のディレクトリ
```

### 파일 이름 바꾸기

`mv`파일의 이름을 바꾸는 데에도 사용할 수 있습니다. `mv 古いファイル名 新しいファイル名`에서 사용합니다.

```
$ ls
sample1.txt
$ mv sample1.txt sample2.txt
$ ls
sample2.txt
```

### 와일드카드를 이용해 단번에 이동

```
$ ls
sample1.txt     sample2.txt     sample3.txt
$ mv sample?.txt ../bar
$ ls bar
$ ls ../bar
sample1.txt     sample2.txt     sample3.txt
```

`?`는 임의의 문자와 일치, `*`0 개 이상의 임의의 문자열과 일치합니다. 이상의 조작은,

```
$ mv sample[123].txt ../bar
```

라든지 할 수 있습니다. `[]`는 지정된 문자 중 하나의 문자와 일치합니다.

# `cal`　캘린더 표시

달력 표시 명령입니다.

```
$ cal
      5月 2023         
日 月 火 水 木 金 土  
    1  2  3  4  5  6  
 7  8  9 10 11 12 13  
14 15 16 17 18 19 20  
21 22 23 24 25 26 27  
28 29 30 31           
```

### `cal -y`　연간 캘린더

`-y`옵션으로 연간 캘린더를 낼 수 있습니다. `-y 2000`그렇다면 2000년 달력을 얻을 수 있습니다.

```
$ cal -y
                            2023
         1月                    2月                    3月           
日 月 火 水 木 金 土  日 月 火 水 木 金 土  日 月 火 水 木 金 土  
 1  2  3  4  5  6  7            1  2  3  4            1  2  3  4  
 8  9 10 11 12 13 14   5  6  7  8  9 10 11   5  6  7  8  9 10 11  
15 16 17 18 19 20 21  12 13 14 15 16 17 18  12 13 14 15 16 17 18  
22 23 24 25 26 27 28  19 20 21 22 23 24 25  19 20 21 22 23 24 25  
29 30 31              26 27 28              26 27 28 29 30 31     
                                                                  

         4月                    5月                    6月           
日 月 火 水 木 金 土  日 月 火 水 木 金 土  日 月 火 水 木 金 土  
                   1      1  2  3  4  5  6               1  2  3  
 2  3  4  5  6  7  8   7  8  9 10 11 12 13   4  5  6  7  8  9 10  
 9 10 11 12 13 14 15  14 15 16 17 18 19 20  11 12 13 14 15 16 17  
16 17 18 19 20 21 22  21 22 23 24 25 26 27  18 19 20 21 22 23 24  
23 24 25 26 27 28 29  28 29 30 31           25 26 27 28 29 30     
30                                                                

         7月                    8月                    9月           
日 月 火 水 木 金 土  日 月 火 水 木 金 土  日 月 火 水 木 金 土  
                   1         1  2  3  4  5                  1  2  
 2  3  4  5  6  7  8   6  7  8  9 10 11 12   3  4  5  6  7  8  9  
 9 10 11 12 13 14 15  13 14 15 16 17 18 19  10 11 12 13 14 15 16  
16 17 18 19 20 21 22  20 21 22 23 24 25 26  17 18 19 20 21 22 23  
23 24 25 26 27 28 29  27 28 29 30 31        24 25 26 27 28 29 30  
30 31                                                             

        10月                   11月                   12月           
日 月 火 水 木 金 土  日 月 火 水 木 金 土  日 月 火 水 木 金 土  
 1  2  3  4  5  6  7            1  2  3  4                  1  2  
 8  9 10 11 12 13 14   5  6  7  8  9 10 11   3  4  5  6  7  8  9  
15 16 17 18 19 20 21  12 13 14 15 16 17 18  10 11 12 13 14 15 16  
22 23 24 25 26 27 28  19 20 21 22 23 24 25  17 18 19 20 21 22 23  
29 30 31              26 27 28 29 30        24 25 26 27 28 29 30  
                                            31              
```

### `cal -m`　지정한 달의 달력

```
$ cal -m 3
      3月 2023         
日 月 火 水 木 金 土  
          1  2  3  4  
 5  6  7  8  9 10 11  
12 13 14 15 16 17 18  
19 20 21 22 23 24 25  
26 27 28 29 30 31
```

# `chmod`　액세스 권한(권한) 변경

파일 및 폴더의 액세스 권한(사용 권한)을 변경합니다. `change mode`의 약칭입니다. 권한은 `ls -l`명령으로 확인할 수 있습니다.

```
$ ls -l
total 8
drwxr-xr-x  2 user  group  4096 Jan 12 10:32 directory1
-rw-r--r--  1 user  group    56 Jan 12 10:32 file1.txt
-rw-r--r--  1 user  group   128 Jan 12 10:32 file2.txt
```

처음 10자 `drwxr-xr-x`등이 권한입니다.
예를 들어, drwxr-xr-x라는 문자열이라면,

- 첫 번째 문자: 파일 형식

  `\-`: 일반 파일을 나타냅니다.
  `d`: 디렉토리를 나타냅니다.
  `l`: 기호 링크를 나타냅니다.

- 2-4번째 문자: 소유자 권한

  `r`: 읽기 권한을 나타냅니다.
  `w`: 쓰기 권한을 나타냅니다.
  `x`: 실행 권한을 나타냅니다.
  `\-`: 해당 권한이 없음을 나타냅니다.

- 5-7 문자 : 소유자 그룹의 사용 권한
  r, w, x 또는 - 중 하나를 가질 수 있습니다.

- 8-10 글자 : 다른 사용자의 권한
  마찬가지로 `r`, `w`, `x`, `-`중 하나를 가질 수 있습니다.

`chmod`명령은 `chmod mode file`같이 사용합니다. `mode`에는 변경할 권한을 나타내는 숫자 또는 기호를 지정합니다.

```
chmod 755 file.txt
```

권한에 대한 정보는

- 읽기 권한 `r`: 4
- 쓰기 권한 `w`: 2
- 실행 권한 `x`: 1
  로 곱하고 `mode`결정합니다. 이 경우 `mode`소유자 (사용자), `1 + 2 + 4 = 7`그리고 소유자 그룹 (그룹), , `1 + 4 = 5`다른 사용자 (others) `1 + 4 = 5`의 상태입니다 `755`.

# `chown`　소유자 및 소유자 그룹 변경

`chown`는 "change owner"의 약칭입니다. 파일 및 폴더의 소유자 및 그룹을 변경합니다.

```
$ chown [options] owner[:group] file
```

예를 들어,

```
$ chown user file.txt
```

에서 `file.txt`소유자를 `user`로 바꿀 수 있습니다.

### `chown user:group`　소유자와 소유자 그룹을 동시에

```
$ chown user:group file.txt
```

위의 예에서는 `file.txt`소유자를 `user`, 소유자 그룹을로 `group`변경합니다.

### `chown -R`　재귀적으로 소유자 변경

```
$ chown -R user directory
```

`directory`아래의 모든 파일과 디렉토리의 소유자를 `user`로 변경합니다. `-R`옵션을 사용하면 디렉토리의 모든 파일과 하위 디렉토리도 재귀 적으로 소유자가 변경합니다.

# `ln`　하드 링크 만들기

심볼릭 링크와 하드 링크를 만들 수 있습니다.
심볼릭 링크와 하드 링크에 대한 설명 기사는 아래 등을 참조하십시오!



### 하드 링크 만들기

```
$ ln /path/to/target link_name
```

에서 하드 링크를 만들 수 있습니다. 이 경우에 `/path/to/target`대한 하드 링크를 `link_name`이름으로 만듭니다.

### `ln -s`　심볼릭 링크 만들기

```
$ ln -s /path/to/target /path/to/directory/link_name
-s`선택적으로 하드 링크를 만들 수 있습니다. 이 경우, 디렉터리에 라는 이름으로 `/path/to/target`심볼릭 링크를 만듭니다.`/path/to/directory``link_name
```

# `man`　수동 표시

명령의 매뉴얼 페이지를 표시하는 것이 `man`명령입니다.

```
$ man [section] keyword
```

같이 사용합니다.

### 명령 매뉴얼 페이지 표시

```
$ man ls

LS(1)                       General Commands Manual                      LS(1)

NAME
     ls – list directory contents

SYNOPSIS
     ls [-@ABCFGHILOPRSTUWabcdefghiklmnopqrstuvwxy1%,] [--color=when]
        [-D format] [file ...]

DESCRIPTION
     For each operand that names a file of a type other than directory, ls
     displays its name as well as any requested, associated information.  For
     each operand that names a file of type directory, ls displays the names
     of files contained within that directory, as well as any requested,
```

이 예에서는 `ls`명령 설명서를 `vim`표시합니다.
매뉴얼 페이지를 탐색한 후 `q`키를 눌러 종료할 수 있습니다.

### 키워드를 검색하여 매뉴얼 페이지 표시:

```
$ man -k network
```

`-k`옵션을 사용하면 키워드 '네트워크'와 관련된 매뉴얼 페이지의 개요를 볼 수 있습니다.

# `less` `more` `vi`　텍스트 파일 표시

표시만 하면 `less`, 편집하고 싶은 경우는 `vi`커멘드를 사용합니다. 마우스나 트랙패드를 건드리지 않아도 좋은 점에서, 에디터로 편집하는 것보다 이러한 커멘드가 좋아하는 편이 일정수 있습니다.

### `less`　텍스트 파일 표시

less 명령은 텍스트 파일의 내용을 페이지 단위로 표시합니다.
스크롤 및 검색이 가능합니다.
파일의 시작 부분에서 보고 페이지 단위로 아래로 스크롤합니다.
`f`에서 한 페이지 아래로, `b`로 한 페이지에 보냅니다.
동영상처럼 여러 파일을 표시하는 경우에는 `:n`다음 파일을 볼 수 있습니다.
종료하고 싶을 때는 를 `:q`입력합니다.

<iframe src="https://asciinema.org/a/yKWX9pG5FKVWG3PUR0bNiQ6WP/iframe?" id="asciicast-iframe-yKWX9pG5FKVWG3PUR0bNiQ6WP" name="asciicast-iframe-yKWX9pG5FKVWG3PUR0bNiQ6WP" scrolling="no" allowfullscreen="true" style="padding: 0px; margin: 0px; box-sizing: inherit; border: 0px; overflow: hidden; display: inline-block; width: 756px; visibility: visible; height: 401px;"></iframe>

편집기를 사용하기 때문에 `less`명령은 필요하지 않습니까? 라고 생각한 당신. 파이프 `|`와 함께 사용하면 출력을 편안하게 읽을 수 있습니다.

```
file imgs/* | less
imgs/1.jpg: JPEG image data, JFIF standard 1.01, aspect ratio, density 1x1, segment length 16, progressive, precision 8, 
1080x1350, components 3
imgs/2.jpg:  JPEG image data, JFIF standard 1.01, resolution (DPI), density 300x300, segment length 16, progressive, preci
sion 8, 735x1102, components 3
imgs/3.jpg: JPEG image data, JFIF standard 1.01, resolution (DPI), density 72x72, segment length 16, progressive, precisi
on 8, 736x919, components 3
```

### `more`　텍스트 파일 표시

`less`에 `more`비해 다기능을 갖추고 있으며, `more`의 완전 상위 호환이므로, 낡은 `more`커멘드는 소개하는 것에 둡니다.
`less`입력 `:q`하지 않으면 종료되지 않지만 `more`파일의 끝에 도달하면 종료됩니다.

### `vi`　텍스트 파일 보기 및 편집

`vi`명령은 파일을 보고 편집할 수 있습니다.
텍스트 파일을 행 단위로 표시합니다.
커서 이동으로 열람의 「NORMAL」모드와, 편집의 「INSERT」모드로, `:`커멘드를 입력하는 「COMMAND」모드가 있습니다. "COMMAND" 모드에서는 "종료" 또는 `:q`"INSERT" 모드로 전환 `:i`, 저장 `:w`등이 있습니다.
`vim`의 해설은 말하면 길기 때문에, 이쪽의 기사를 참고해 주세요.





Vimmer **를** 사용하는 사람 을
`Vim`Vimmer라고합니다. `Vim`를 애용하고 그 독특한 조작 스타일과 사용자 정의 능력에 열중하는 사람들을 말합니다. `Vim`의 조작 방법은 초학자에게는 약간 학습 비용이 듭니다만, 숙련하면, IDE나 특별한 에디터 없이, 보다 빠르고 효율적인 텍스트 편집을 할 수 있게 됩니다.

# `make`자주 사용하는 여러 명령을 한 줄로 실행

자주 사용하는 복수의 커멘드를 정리해 두고, 1행으로 실행하는데 편리한 커멘드입니다.
주로 소스 파일의 컴파일에 사용되지만, 그 이외의 용도에서도 물론 사용할 수 있습니다.
실행하려면 `makefile`파일이 필요하며 언어는 쉘 스크립트로 작성됩니다.
`make`와 `make ターゲット名`같은 형태로 실행합니다.
타겟이란 아래의 예에서 말하는 것, 라는 것`install` 입니다. 당신이 작성한 명령이 같은 대상을 생략하면 맨 위가 실행 됩니다. 는 모든 명령이 단일 쉘 스크립트에서 실행되도록 혼란 스럽습니다.`run``start`
`make run``run``make``install`
`.ONESHELL``makefile`

makefile

```
.ONESHELL:

# Command to install packages using pip
INSTALL_PACKAGES = pip install -r requirements.txt

install: 
    $(INSTALL_PACKAGES)
main: 
    python main.py
start: install main
```

c 언어 프로그램을 빌드하는 데 `make`사용하는 방법은 다음을 참조하십시오.



# `wc`　행수, 단어수, 바이트수, (문자수)를 세는

파일의 행수, 단어수, 바이트수를 세는 명령입니다.

```
$ wc sample?.txt
      20      20     145 sample1.txt
       2       2      13 sample2.txt
       1       1       7 sample3.txt
      23      23     165 total
```

`sample1.txt`에 주목하면 , `20`, `20`가 `145`각각, 행수, 단어수, 바이트수입니다.
옵션을 사용하면 원하는 정보만 표시할 수 있습니다.

| 옵션 |           설명           |
| :--: | :----------------------: |
| `-l` |    파일의 행 수 표시     |
| `-w` |   파일의 단어 수 표시    |
| `-c` |  파일의 바이트 수 표시   |
| `-m` |   파일의 문자 수 표시    |
| `-L` | 파일의 최장 행 길이 표시 |

```
$ wc -m sample1.txt 
     151 sample1.txt
```

파이프 `|`와 함께 사용되는 경우가 많습니다.

```
$ ls -l | wc -l
    3
```

# `pbcopy` `clip`　클립보드에 복사

```
pbcopy < sample1.txt
```

에서 클립보드에 복사할 수 있습니다.
mac는 `pbcopy`Windows에서 `clip`명령을 제공합니다.
파이프 `|`와의 궁합이 좋습니다.

```
ls -l | pbcopy 
```

또한 첫 번째 예는

```
echo sample1.txt | pbcopy
```

하지만 같은 일입니다.

# `alias`　명령에 고유한 별명 정의

길고 매번 치는 것이 번거로운 명령은 별명을 등록합시다.

```
$ alias エイリアス名="登録したいコマンド"
```

에서 별칭(=별칭)을 등록할 수 있습니다.
필자의 애용 별칭은

```
$ alias pyvenv='python3 -m venv venv && . venv/bin/activate'
```

입니다. 파이썬 가상 환경을 만들 때 `activate`까지 단번에합니다.

또한 등록한 별칭은 `alias`명령으로 확인할 수 있습니다.

```
$ alias
alias g='git'
alias ga='git add'
alias gd='git diff'
alias gs='git status'
alias gp='git push'
alias gb='git branch'
alias gst='git status'
alias gco='git checkout'
alias gf='git fetch'
alias gc='git commit'
```

별칭 삭제는 `unalias`입니다.



**.bashrc 및 .bash_profile 정보**
환경 변수 `ailas`설정에 대한 `.bashrc`이해 `.bash_profile`가있는 것이 좋습니다.
`.bash_profile`로그인시에만 실행하면 `.bashrc`대화 모드가 `bash`시작될 때마다 실행됩니다.
자세하게 쓰면 길어지므로, 이하의 기사를 참고해 주세요.



# `curl wget`　URL을 사용하여 파일 다운로드

URl을 사용하여 그물의 파일을 다운로드할 수 있습니다.
다음은 Google 홈페이지의 `HTML`파일을 검색합니다.

```
$ curl google.com
<HTML><HEAD><meta http-equiv="content-type" content="text/html;charset=utf-8">
<TITLE>301 Moved</TITLE></HEAD><BODY>
<H1>301 Moved</H1>
The document has moved
<A HREF="http://www.google.com/">here</A>.
</BODY></HTML>
```

`curl`표준 출력에 결과를 출력하고 `wget`파일을 직접 저장합니다.

# `open`　Finder에서 열기

mac 사용자라면

```
$ open ディレクトリorファイル名
```

에서 Finder를 표시할 수 있습니다.
[![img](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F6d25547b-ca8e-41cd-ae0c-3239fd59181d.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=3a3df8a40061873f6adf429115fad848)](https://camo.qiitausercontent.com/d04050adadeadbbd6aea6e7e94853be9f692c7d6/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f36643235353437622d636138652d343163642d616530632d3332333966643539313831642e706e67)

### Finder에서 파일 경로 가져오기

Finder에서 선택한 파일에 대해 `option + command + C`파일의 전체 경로를 얻을 수 있습니다.

# `history`　명령 내역

`Ctrl + R`에서 입력한 기록을 검색할 수 있습니다.
`-c`: 명령 이력을 지웁니다. 기록을 삭제하면 현재 세션에서 실행된 명령만 표시됩니다.
`-w`: 현재 명령 기록을 파일에 저장합니다. 기본적으로 명령 기록은 세션이 끝날 때 자동으로 저장되지만 이 옵션을 사용하면 수동으로 저장할 수 있습니다.

```
$ history
  1  ls
  2  cd Documents
  3  cat file.txt
  4  cp file.txt backup.txt
  5  rm file.txt
  6  history
```

# `find`　파일 및 디렉토리 검색

파일이나 디렉토리 검색을 위한 명령입니다. 조건에 따라 검색할 수 있습니다.

### `$ find 検索先ディレクトリ -name "ファイル名"`

```
sample/
　├ foo/
    ├ hoge.py
    └ hogehoge.py
　└ bar.py
```

이러한 구조의 디렉토리에 대해서는

```
sample $ find . -name "*.py"
./foo/hogehoge.py
./foo/hoge.py
./bar.py

sample $ find foo -name "*.py"
foo/hogehoge.py
foo/hoge.py
```

같은 사용이 가능합니다. 여기서 `*`는 와일드카드의 의미입니다.

### `$ find 検索先 -size ±ファイル容量`

「검색처」에서 지정한 파일 사이즈 이상(이하)의 파일, 디렉토리를 취득할 수 있습니다.
`-size +400k`그럼 400kB 이상의, `-size -10c`그럼, 10 바이트 이하의 파일, 디렉토리입니다. 숫자 앞 `+`이므로 이상을, `-`으로 다음을, `c, k, M, G`로 각각을 `B, kB, MB, GB`나타냅니다.

```
$ find ./ -size +400k 
./imgs
./imgs/352320.jpg
./imgs/353060.jpg
./imgs/344737.jpg
./imgs/349435.jpg
./imgs/350313.jpg
./imgs/349965.jpg
./imgs/345525.jpg
./imgs/345927.jpg
```

### `$ find 検索先 -exec 実行したいコマンド　{} \;`

검색한 파일이나 디렉토리에 대해 `-exec`선택적으로 `cp`또는 `ls`같은 임의의 명령을 실행할 수 있습니다.

```
$ find ./imgs/* -type f -size -9k -exec file {} +;
./imgs/pic1.jpg: JPEG image data, JFIF standard 1.01, aspect ratio, density 1x1, segment length 16, baseline, precision 8, 225x225, components 3
./imgs/pic3.jpg: JPEG image data, JFIF standard 1.01, aspect ratio, density 1x1, segment length 16, baseline, precision 8, 231x500, components 3
```



**자세히**

`{}`에는 find에서 찾아온 파일이나 디렉토리가 들어가는 이미지.
`;`는 `-exec`실행할 명령의 끝을 세미콜론으로 구분하는 규칙을 기반으로합니다.
`\`로 이스케이프하고 있는 것은, `;`통상, 「복수 커멘드를 계속해 실행」하는 의미가 있기 때문입니다.
위의 예에서는 9kB 이하의 파일을 검색하고, 파일 형식이나 종횡 사이즈를 취득하는, 커멘드를 실행하고 있습니다 `file`.

# `locate`　빠른 파일 및 디렉토리 검색

`locate`는 `find`더 빠르지 만 미리 작성된 데이터베이스에서 파일 이름과 패턴과 일치하는 파일을 검색하므로 최신 정보와 다를 수 있습니다. 대량의 파일을 검색하고 싶을 때만 사용할 수 있습니다. `-exec`같은 옵션은 없습니다.
`$ locate [オプション] [検索パターン]`그리고 사용합니다.
`locate`명령을 처음 사용하면,

```
$ locate -l 5 'bashrc'

WARNING: The locate database (/var/db/locate.database) does not exist.
To create the database, run the following command:

  sudo launchctl load -w /System/Library/LaunchDaemons/com.apple.locate.plist

Please be aware that the database can take some time to generate; once
the database has been created, this message will no longer appear.
```

같은

```
$ locate -l 5 'bashrc'
/System/Library/Templates/Data/private/etc/bashrc
/System/Library/Templates/Data/private/etc/bashrc_Apple_Terminal
/private/etc/bashrc
/private/etc/bashrc_Apple_Terminal
```

# `grep`: 파일에서 문자열 검색

```
$ grep [オプション] '検索文字列' 対象ファイル
```

를 사용하여 파일에서 문자열을 찾습니다.

```
$ grep -n "AP" sample/sample2.txt
1:APPLE
```

유용한 옵션 중 일부를 소개하면,

|  옵션  | 설명                                    |
| :----: | :-------------------------------------- |
| **-n** | 일치하는 행의 행 번호 표시              |
| **-i** | 대소문자를 구별하지 않고 검색           |
| **-r** | 디렉토리의 모든 파일을 재귀 적으로 검색 |
| **-l** | 파일 이름만 표시                        |

등이 있습니다.

아래에서 사용 예를 소개합니다.

```
$ ls sample
sample1.txt     sample2.txt     sample3.txt
$ cat sample/sample1.txt
apple
$ cat sample/sample2.txt
APPLE
BANANA
$ cat sample/sample3.txt
orange
```

이러한 내용의 디렉토리 `sample`에 대해서는

```
$ grep -rl "ap" sample
sample/sample1.txt
$ grep -irl "ap" sample
sample/sample1.txt
sample/sample2.txt
```

와 같이 해당 문자열이 있는 파일명을 취득할 수 있습니다.

기타 옵션은 아래를 참조하십시오.



# `df du ps`　디스크 시스템 용량 사용량

디스크 및 시스템 용량이 얼마나 사용되는지 확인할 수 있습니다.

### `df`　파일 시스템 사용

```
$ df
Filesystem     1K-blocks    Used Available Use% Mounted on
/dev/sda1       10485760 3776328   6169432  38% /
tmpfs             524288       4    524284   1% /tmp
/dev/sdb1        2097152  524288   1572864  25% /home
```

다음은 각 열이 파일 시스템 디스크 파티션에 대해 갖는 의미에 대한 설명입니다.

|            열 이름             | 설명                                                         |
| :----------------------------: | :----------------------------------------------------------- |
| 1K-blocks(1킬로바이트 블록 수) | 총 용량을 킬로바이트 단위로 표시합니다. 이 값은 파일 시스템을 사용할 수 있는 총 용량입니다. |
|          Used(사용됨)          | 사용된 용량을 킬로바이트 단위로 표시합니다. 현재 디스크 사용량입니다. |
|      Available(사용 가능)      | 여전히 사용 가능한 용량을 킬로바이트 단위로 표시합니다. 현재 디스크의 여유 공간입니다. |
|          Use%(사용률)          | 사용률의 백분율입니다.                                       |
|   Mounted on(마운트 포인트)    | 파일 시스템 또는 디스크 파티션이 마운트된 위치를 나타냅니다. 디렉토리의 경로로 표시됩니다. |

또, `-h`옵션으로, `G`이나 `K`표기로 사이즈를 보기 쉽게 성형하고 있습니다.

```
$ df -h
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda1       9.5G  3.6G  5.9G  38% /
tmpfs           512M  4.0K  512M   1% /tmp
/dev/sdb1       2.0G  512M  1.5G  25% /home
```



**파일 시스템**
파일 시스템은 OS가 제공하는 데이터 관리를 위한 데이터 구조와 알고리즘 세트입니다. 일반적인 파일 시스템의 예로는 Windows에서 NTFS 및 FAT, Linux에서 ext4 및 XFS, macOS에서 HFS+ 및 APFS 등이 있습니다. 자세한 내용은 다음 기사 등을 참조하십시오.



### `du`　디스크 사용량

디스크 사용량을 디렉토리별로 집계하여 표시하는 명령입니다.

```
$ du
8       ./.vscode
24      .
```

첫 번째 숫자는 파일 또는 디렉토리 경로의 디스크 사용량입니다.
`du directory_name`의 형태로 디렉토리를 지정할 수 있습니다. 그 경우, 그 디렉토리 및 모든 서브 디렉토리의 사용량을 집계합니다. 디렉토리를 생략하면 현재 디렉토리가 지정됩니다.
파일 이름을 지정할 수도 있으며, 이 경우 해당 파일의 디스크 사용량이 표시됩니다.

### `ps`　실행 중인 프로세스 정보

```
$ ps
  PID TTY          TIME CMD
 1234 pts/0    00:00:01 bash
 5678 pts/0    00:00:00 ps
```

다음은 각 열의 설명입니다.

|     열 이름     | 설명                                          |
| :-------------: | :-------------------------------------------- |
| PID(Process ID) | 프로세스의 고유 식별자.                       |
|   TTY(터미널)   | 프로세스가 실행되는 터미널 장치(터미널) 정보. |
|      TIME       | 프로세스가 실행되는 총 CPU 시간.              |
|  CMD(Command)   | 프로세스를 시작한 명령 또는 프로그램의 이름.  |

다음은 선택적 설명입니다.

| 옵션 | 설명                                               |
| :--: | :------------------------------------------------- |
| `-e` | 모든 프로세스 보기.                                |
| `-l` | 자세한 정보 표시.                                  |
| `-u` | 지정된 사용자와 관련된 프로세스 만 표시합니다.     |
| `-p` | 지정된 프로세스 ID와 연관된 프로세스만 표시합니다. |
| `-r` | 실행 중인 프로세스만 표시.                         |

# `ncdu`

설치가 필요한 `du`상위 명령을 소개합니다. 파일 크기와 파일 수가 보기 쉽고, vim 같은 느낌으로, 크기가 큰 파일을 십자 키 사용하면서 찾을 수 있습니다.

설치 방법

```
$ sudo apt-get install ncdu # Debian系
$ sudo yum install ncdu  # RedHat系
$ brew install ncdu # macOS/Homebrew
$ sudo port install ncdu # macOS/MacPorts
```

[![스크린샷 2023-04-21 11.00.57.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3004778%2F1913538a-e656-96e0-732e-5e6aed366f0b.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=78f3410604682d7e52faafca7a1f039c)](https://camo.qiitausercontent.com/fdeb4d22c8101de8de81775b3fe1a21d1a8f312b/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333030343737382f31393133353338612d653635362d393665302d373332652d3565366165643336366630622e706e67)

# `uniq`　중복 행 삭제

```
$ uniq file.txt
```

에서 `file.txt`인접한 중복 행을 삭제할 수 있습니다. 다만, 저장처를 지정하지 않으면 표준 출력에 결과를 써냅니다.
변경 내용을 저장하려면,

```
$ uniq file.txt > file_modified.txt
```

등으로합시다.
또, 중복한 행수를 표시하거나 `uniq -c file.txt`, 중복한 행만 출력하는 `uniq -d file.txt`것도 존재합니다.
물론 명령의 출력을 인수로 파이프를 `|`사용할 수도 있습니다.

# `sort`　텍스트 파일을 행 단위로 정렬

```
$ sort file.txt
```

알파벳 순서에 따라 행을 정렬하여 표준 출력에 표시합니다. `-r`선택적으로 역순으로, `-f`선택적으로 대소문자 구분이 없는 정렬이 가능합니다.
`du`명령 등과 파이프 `|`로 조합하여 크기순으로 표시할 수도 있습니다.

# `diff`　두 파일의 차이를 표시합니다.

두 파일의 차이를 표시합니다. `-q`옵션을 사용하면 간결하게 두 개의 파일이 완전히 동일한지만 확인할 수 있습니다.

```
$ cat sample1.txt
orange
orange
tomato
apple
$ cat sample2.txt
orange
tamago
tomato
applo
$ diff sample1.txt  sample3.txt 
2c2
< orange
---
> tamago
4c4
< apple
---
> applo
```

# `cmp`　바이트 레벨 차이

```
$ cmp file1.txt file2.txt
file1.txt file2.txt differ: byte 13, line 1
```

`file1.txt`와의 `file2.txt`내용을 바이트 레벨로 비교해, 차이를 출력합니다. 차이가 없으면 출력되지 않습니다.

옵션은 다음과 같습니다.

| 옵션 | 설명                                                         |
| :--: | :----------------------------------------------------------- |
| `-s` | 차이가 있을 경우 1을, 없는 경우 0을 출력, 에러 코드는 2를 출력. |
| `-b` | 행의 끝이나 공백의 취급을 무시.                              |
| `-l` | 차이가 있는 위치와 그 바이트치를 표시.                       |

# `cut`　텍스트 파일을 가로로 분할.

`cut`예를 들어 명령을 수행할 수 있는지 확인해 봅시다.
다음과 같은 파일을 생각합니다.

sample1.txt

```
01,John,200,California
02,Michel,800,Texas
03,Maria,100,New York
04,John,600,New York
05,David,300,Texas
```

### `-b`　바이트 수로 검색할 위치 지정

각 행의 문자를 검색할 위치를 `-b`선택적으로 지정합니다.

두 번째 문자를 꺼내

```
$ cut -b 2 sample1.txt
1
2
3
4
5
```

`-`를 사용하면 연속 복수 문자 꺼낼 수 있습니다.

2~4번째 문자 꺼내기

```
$ cut -b 2-5 sample1.txt
1,Jo
2,Mi
3,Ma
4,Jo
5,Da
```

`,`로 구분하면 배운 범위를 지정할 수 있습니다.

2~4문자째와 7~9문자째를 꺼내

```
$ cut -b 2-5,7-10 sample1.txt
01,Jo,200
1,Jon,20
2,Mihel,
3,Maia,1
4,Jon,60
5,Daid,3
```

종단을 지정하지 않으면, 마지막 꼬리까지 꺼냅니다.

다섯 번째 문자 이후를 꺼내

```
$ cut -b 5- sample1.txt
ohn,200,California
ichel,800,Texas
aria,100,New York
ohn,600,New York
avid,300,Texas
```

### `-d`　구분 기호 지정

구분 기호를 지정할 수 있습니다. 다른 옵션과 함께 사용합니다.

### `-f`　검색할 항목(컬럼) 수 지정

`-b`와 같은 사양으로 항목수를 지정합니다.

```
$ cut -d "," -f 3 sample1.txt 
200
800
100
600
300
$ cut -d "," -f 2-5 sample1.txt 
John,200,California
Michel,800,Texas
Maria,100,New York
John,600,New York
David,300,Texas
```

### 출력 저장

출력은 표준 출력에 표시될 뿐이므로 저장하고 싶을 때는 `>`을 사용합니다.

```
$ cut -d "," -f 2-5 sample1.txt > sample2.txt
```

# `sed`　문자열 대체, 추출, 삭제

### `-e "s/置換前文字列/置換後文字列/"`

아래와 같은 파일을 생각한다.

sample1.txt

```
01,John,2000,America/California
02,Michel,8000,America/Texas
03,Maria,1000,America/New York
04,John,6000,America/New York
05,David,3000,America/Texas
```

`-e`옵션으로 `"s/置換前文字列/置換後文字列/"`지정합니다.

```
$ sed -e "s/000/k/" sample1.txt 
01,John,2k,America/California
02,Michel,8k,America/Texas
03,Maria,1k,America/New York
04,John,6k,America/New York
```

`"s/置換前文字列/置換後文字列/"`무엇이든 상관 `/`없습니다. 대체 대상에 포함되어 있지 않은 특수 문자를 지정하는 것이 이번입니다. `/`를 포함하는 를 대체 `"America/"`하려는 `He is in "`경우를 생각해 봅시다. `"s/America//He is in /"`그렇다면 작동하지 않습니다. `"s/America\//He is in /"`그리고 이스케이프 해도 좋지만,

```
$ sed -e "s@America/@He is in @" sample1.txt 
01,John,200,He is in California
02,Michel,800,He is in Texas
03,Maria,100,He is in New York
04,John,600,He is in New York
05,David,300,He is in Texas
```

같이 `@`구분하여 보기 쉬워집니다.

`g`를 붙이는 것으로, 행에 복수 나오는 문자를 모두 지정할 수 있습니다. `"s/置換前文字列/置換後文字列/g"`그렇지 않으면 각 행의 처음에 나오는 대체 대상 문자열만 대체됩니다.

```
#gを指定する場合
$ sed -e "s@,@\t@g" sample1.txt
01      John    2000    America/California
02      Michel  8000    America/Texas
03      Maria   1000    America/New York
04      John    6000    America/New York
05      David   3000    America/Texas
# gを指定しない場合
$ sed -e "s@,@\t@" sample1.txt
01      John,2000,America/California
02      Michel,8000,America/Texas
03      Maria,1000,America/New York
04      John,6000,America/New York
05      David,3000,America/Texas
```

### `-e "3d"`　세 번째 줄 삭제

`"3d"`에서 세 번째 줄을 삭제할 수 있습니다. `"1,4d"`에서 1~4행을 삭제, `"/$d"`로 마지막 행을 삭제할 수 있습니다.

```
$ sed -e "3d" sample1.txt 
01,John,2000,America/California
02,Michel,8000,America/Texas
04,John,6000,America/New York
05,David,3000,America/Texas
```

### `-i`　직접 편집

`-e`옵션은 표준 출력에 표시하는 반면에 하면 `-i`직접 파일을 편집할 수 있습니다.

### 기타

`sed`명령에는 그 밖에도 할 수 있는 일이 많이 있습니다. 다음은 참고 기사의 인용입니다.

|              명령              | 설명                                                         |
| :----------------------------: | :----------------------------------------------------------- |
|              `=`               | 현재 행 번호를 출력합니다.                                   |
|              `#`               | 코멘트                                                       |
|           `a 文字列`           | 지정된 위치 뒤에 문자열을 추가합니다. (개행하는 경우는 개행전의 캐릭터 라인에 `/`붙인다.) |
|           `i 文字列`           | 지정된 위치 뒤에 텍스트를 삽입합니다. (개행하는 경우는 개행전의 캐릭터 라인에 `/`붙인다.) |
|           `c 文字列`           | 선택한 행을 텍스트로 바꿉니다. (개행하는 경우는 개행전의 캐릭터 라인에 `/`붙인다. |
|              `q`               | 입력을 중단하고 더 이상 처리하지 않고 종료합니다. (※미출력분이 있으면 출력하고 나서 종료합니다.) |
|              `d`               | 지정된 행을 삭제합니다.                                      |
|              `p`               | 처리한 내용을 출력합니다. `-n`옵션과 함께 사용할 수 있습니다. |
|              `P`               | 처리한 내용을 출력합니다.                                    |
|         `r ファイル名`         | 파일을 맨 위에 추가합니다.                                   |
| `s/置換前文字列/置換後文字列/` | (치환 전 캐릭터 라인)로 지정한 캐릭터 라인에 매치 한 부분을 치환 후 캐릭터 라인에 옮겨놓습니다. 또 복수 매치 했을 경우는 선두만 치환을 실시해, 대상 모두 치환을 실시하는 경우는와 같이 옵션을 병용 `"s/置換前/置換後/g"`합니다 `-g`. |
|   `y/元の文字列/対象文字列/`   | (원래 문자열)의 항목을 (대상 문자열)의 동일한 위치에 있는 문자열로 바꿉니다. (※ `tr`커맨드와 같이 사용 가능합니다.) |

아래 기사를 참조하십시오.



# `ssh` `scp`　원격 기계와 상호 작용

### `ssh`　원격 머신에 연결

`$ ssh [オプション] [ログイン名@]接続先 [接続先で実行したいコマンド]`형식으로 원격 컴퓨터에 연결합니다. DeepLearning의 맥락이라면 GPU에 연결할 때 등장합니다.
`~/.ssh`아래에 `config`파일을 만들면 원격 컴퓨터와 쉽게 `ssh`연결할 수 있습니다.

config

```
Host example
    HostName example.com #IPアドレスでも可
    User myusername
    Port 22
    IdentityFile ~/.ssh/id_rsa
```

각 항목의 설명입니다.

|     품목     | 설명                                                 |
| :----------: | :--------------------------------------------------- |
|     Host     | 호스트 별칭을 지정합니다.                            |
|   HostName   | 호스트 이름 또는 IP 주소를 지정합니다.               |
|    사용자    | 접속하는 접속처의 유저명을 지정.                     |
|     포트     | 연결할 포트 번호를 지정합니다.                       |
| IdentityFile | 인증에 사용할 로컬 개인 키 파일의 경로를 지정합니다. |

`IdentityFile`는

```
ls ~/.ssh
```

에서 찾을 수 있습니다.

```
ssh host_name
```

연결에는 로컬 PC의 공개 키가 필요합니다.

```
$ ls ~/.ssh/
```

# `scp`　로컬 원격간에 파일 복사

로컬 파일을 원격으로 복사하거나 그 반대로 할 수 있습니다.

### 로컬에서 원격 서버로 파일 복사

로컬에서 원격으로 복사

```
$ scp /path/to/local/file.txt username@hostname:/path/to/remote/
```

### 원격 서버에서 로컬로 파일 복사

원격에서 로컬로 복사

```
$ scp username@hostname:/path/to/remote/file.txt /path/to/local/
```

### `-r`디렉토리별 복사

```
$ scp -r /path/to/local/directory username@hostname:/path/to/remote/
```

# `rsync`

파일이나 디렉토리를 로컬 또는 로컬과 원격으로 동기화합니다.

### 로컬 디렉토리를 다른 로컬 디렉토리와 동기화

```
$ rsync -av /path/to/source/ /path/to/destination/
```

`-a`, `--archive`옵션은 파일의 속성, 권한, 타임 스탬프 등이 보관되는 아카이브 모드에서 동기화됩니다.
`-v`, `--verbose`옵션은 자세한 출력을 표시합니다.

### 로컬 디렉토리를 원격 서버에 동기화

```
$ rsync -av /path/to/source/ user@remote:/path/to/destination/
```

### 원격 서버에서 로컬 디렉터리로 동기화

```
$ rsync -av user@remote:/path/to/source/ /path/to/destination/
```

# `awk`　조건에 맞는 행 표시

`$ awk 'パターン { アクション }' ファイルパス`형식으로 실행합니다.
패턴에 지정하는 행의 조건을, 액션에는으로 `{}`둘러싸는 표시하는 `{print}`등의 커멘드를, 파일 패스에는 대상 텍스트 파일의 패스를 지정합니다.
다음을 `sample4.txt`사용합니다.

sample4.txt

```
01	John	2000	America/California
02	Michel	8000	America/Texas
03	Maria	1000	America/New York
04	John	6000	America/New York
05	David	3000	America/Texas
$ awk '$2 == "John"' sample4.txt 
01      John    2000    America/California
04      John    6000    America/New York
```

`$2`는 두 번째 줄이라는 의미입니다. 두 번째 줄의 `John`행을 그대로 표시합니다. "액션"은 생략되었으며 far과의 `{print}`호출됩니다.
액션의 종류에는 분할 `split`이나 치환 `sub`등이 있습니다만 전부 소개하면 많기 때문에, 이하의 기사등을 참고해 주세요.



# `ifconfig`　네트워크 인터페이스 설정

네트워크 인터페이스의 구성 정보를 표시하는 데 `$ ifconfig インターフェース名`사용됩니다.

```
$ ifconfig eth0
eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.0.100  netmask 255.255.255.0  broadcast 192.168.0.255
        inet6 fe80::cdef:1234:abcd:ef01  prefixlen 64  scopeid 0x20<link>
        ether 00:11:22:33:44:55  txqueuelen 1000  (Ethernet)
        RX packets 12345  bytes 6789012 (6.4 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 23456  bytes 7890123 (7.5 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
```

| 항목 이름  | 설명         |
| :--------: | :----------- |
|    inet    | IPv4 주소    |
|   inet6    | IPv6 주소    |
|  netmask   | 넷 마스크    |
|    이더    | MAC 주소     |
| RX packets | 수신 패킷 수 |
| TX 패키지  | 송신 패킷 수 |
| RX errors  | 수신 오류 수 |
| TX errors  | 송신 오류 수 |

# 결론

저희 Nuco는 다른 여러 가지 유용한 기사를 공개합니다. 원한다면 [Organization](https://qiita.com/organizations/nuco-inc/profile) 페이지도 들여다보십시오.
또, Nuco에서는 함께 일하는 동료도 모집하고 있습니다! 흥미를 가질 수 있는 분은, [이쪽](https://www.recruit.nuco.co.jp/?qiita_item_id=6a4faf4d026d130e7db1) 까지.