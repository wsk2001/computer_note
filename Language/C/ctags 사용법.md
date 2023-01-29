# ctags 사용법

출처: https://blog.naver.com/monovision/140065006594



### ■ 기본 .vimrc 설정

``` bash
vi ~/.vimrc
```

```
set ts=4
set tags=/home/acuser/source/v4.0/.tags
```



### ■ Ctags + vi 연동

**ubuntu 에서 ctags 설치**

``` bash
sudo apt-get install ctags
```



**사용 방법**

``` bash
1. 소스트리 최상단에서 ctags -R 로 태그 파일 생성
2. vi -t [태그명], vi 에서 :ta[g] [태그명]
3. Ctrl + ] 로 이동. Ctrl + t 로 전 위치로 이동
4. :tn - tag next, 
5. :tp - tag previous
6. :ts [태그명]
7. :ta /keyword - keyword 가 포함된 태그로 이동

.vimrc --> st tags+=/절대경로/파일명
```



### ■ ctags 를 이용한 C 코드 자동 완성

``` bash
1. ctags --links=no -f stdlib.tag -R /usr/include
2. .vimrc 설정
set tags+=~/stdlib.tag
set complete
set tagbsearch
3. Ctrl + n 으로 자동 완성
```



### ■ cscope

``` bash
1. find [경로] -name *.[chS] -print > cscope.files
2. cscope 를 실행하여 cscope.files 를 읽어 DB 화(cscope.out)
3. 창이동 : Tab
```



### ■ cscope + VI 연동

``` bash
1. vi 에서
:cs add cscope DB 파일(.out) 를 추가

2. 검색
:cs find [N] [FUNCTION]

3. 이동
:cn, :cp

4. cscope 는 태그 이동이 느리기 때문에 ctags 를 사용하여 tag 이동
.vimrd --> set nocst
```



### ■ vi 플러그인 taglist 사용

``` bash
- 설정
1. taglist 다운(http://vim-taglist.sourceforge.net/)
2. mkdir ~/.vim
3. cp taglist.zip ~/.vim
4. cd ~/.vim
5. unzip taglist.zip

- 사용
1. vi 에서 실행
:Tlist

2. 좌우 이동
Ctrl + w + w

3. 태그창에서 분류 접었다 펴기
+/-

4. .vimrc 설정
- 태그 범위(함수, 매크로, 구조체 등)를 표시
let Tlist_Display_Tag_Scope = 1

- 함수 원형을 표시
let Tlist_Display_Prototype = 1

- 태그 리스트 소팅 (소스 코드 위치 순서가 아닌 이름 순서로 표시)
let Tlist_Sort_Type = "name"

- 태그 리스트 창을 우측에 표시
let Tlist_Use_Right_Window = 1

- 태그 리스트 창의 폭을 35문자로 지정
let Tlist_WinWidth = 35
```



### ■ vi 단축키 맵핑

``` bash
.vimrc 편집

map 단축키
ex)
map <c-k>    <c-w><c-w>
map <c-l>    :Tlist
etc....
```

