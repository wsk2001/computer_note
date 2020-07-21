# 로그 분석에 사용할 Linux 명령 6 선

로그를 주로 보안 시선으로 분석 할 때 유용한 6 가지 명령과 그 사용법입니다.

출처 : [여기]([https://qiita.com/moneymog/items/16d2f843c344a5ace51a?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=e6f4bbf1f1-Qiita_newsletter_410_05_13_2020&utm_medium=email&utm_term=0_e44feaa081-e6f4bbf1f1-34467781](https://qiita.com/moneymog/items/16d2f843c344a5ace51a?utm_source=Qiitaニュース&utm_campaign=e6f4bbf1f1-Qiita_newsletter_410_05_13_2020&utm_medium=email&utm_term=0_e44feaa081-e6f4bbf1f1-34467781))



## 용처

운용하고있는 서버에 취약점 발견 Exploit의 흔적의 유무를 로그에서 분석 한다.



## 명령 6 선

| 명령어 | 용도                                 |
| ------ | ------------------------------------ |
| grep   | 지정한 문자열을 포함하는 행을 검색   |
| awk    | 문자열을 분해하거나 조건부 행의 검색 |
| sort   | 행 정렬                              |
| uniq   | 중복 행 제거 또는 숫자의 계산        |
| wc     | 문자 행 개수 계산                    |
| sed    | 지정한 조건의 대체 (문자열 수정)     |



## 분석 대상 (예)

### Access_log (Apache HTTP Server 액세스 로그)

```bash
203.0.113.1 - - [03/May/2020:12:00:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
203.0.113.1 - - [03/May/2020:12:10:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
203.0.113.2 - - [03/May/2020:12:20:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
203.0.113.2 - - [03/May/2020:12:30:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
203.0.113.2 - - [03/May/2020:12:40:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
198.51.100.3 - - [03/May/2020:12:50:00 +0900] "GET /index.cgi?page=<Script>alert('Evil')</Script> HTTP/1.1" 200 3000 "-" "Evil User Agent"
198.51.100.3 - - [03/May/2020:13:00:00 +0900] "GET /../../../../../etc/shadow HTTP/1.1" 200 3000 "-" "Evil User Agent"
```

### 보충

- 203.0.113.0/24에서 정상적으로 액세스 198.51.100.0 / 24 의심스러운 액세스 가정
- 로그 포맷 대한 자세한 내용은 다음을 참조하십시오. 로그 파일 - Apache HTTP Server 버전 2.4



## 활용 방법 (예)

### 특징 검색 (grep)

- 디렉토리 탐색 공격 조사

``` bash
$ grep -n "\.\." access_log
7:198.51.100.3 - - [03/May/2020:13:00:00 +0900] "GET /../../../../../etc/shadow HTTP/1.1" 200 3000 "-" "Evil User Agent"
```

- 의심스러운 스크립트 삽입 조사

``` bash
$ grep -i "<script>" --color access_log
198.51.100.3 - - [03/May/2020:12:50:00 +0900] "GET /index.cgi?page=<Script>alert('Evil')</Script> HTTP/1.1" 200 3000 "-" "Evil User Agent"
※ 실제 환경에서는 Script가 채색되어 나타납니다
```

- 의심 HTML 태그 삽입 조사

```bash
$ grep -E "<[^>]+>[^<]+<[^>]+>" -o access_log
<Script>alert('Evil')</Script>
```

- 옵션 설명

| 옵션    | 설명                            |
| ------- | ------------------------------- |
| -n      | 일치하는 행의 행 번호를 표시    |
| -i      | 대소 문자를 구별하지 않는다     |
| --color | 검색과 일치하는 부분을 색칠하기 |
| -o      | 검색과 일치하는 부분을 표시     |
| -E      | 확장 정규식을 사용              |

### 불필요한 로그 제거 (grep)

- 정상적인 접근 소스에서 로그 제거

```bash
$ grep -v "203.0.113." access_log
198.51.100.3 - - [03/May/2020:12:50:00 +0900] "GET /index.cgi?page=<Script>alert('Evil')</Script> HTTP/1.1" 200 3000 "-" "Evil User Agent"
198.51.100.3 - - [03/May/2020:13:00:00 +0900] "GET /../../../../../etc/shadow HTTP/1.1" 200 3000 "-" "Evil User Agent"
```

- 옵션 설명

| 옵션 | 설명                                   |
| ---- | -------------------------------------- |
| -v   | 검색 조건에 맞는 행을 표시하지 않는다. |



### 요소의 추출 (awk)

- 소스 IP 주소의 추출

``` bash
$ awk '{print $1}' access_log 
203.0.113.1
203.0.113.1
203.0.113.2
203.0.113.2
203.0.113.2
198.51.100.3
198.51.100.3
```

- User Agent의 추출

``` bash
$ awk -F[\"] '{print $6}' access_log 
Mozilla/5.0 (Windows NT 10.0; Win64; x64)
Mozilla/5.0 (Windows NT 10.0; Win64; x64)
Mozilla/5.0 (Windows NT 6.3; Win64; x64)
Mozilla/5.0 (Windows NT 6.3; Win64; x64)
Mozilla/5.0 (Windows NT 6.3; Win64; x64)
Evil User Agent
Evil User Agent
```

- 옵션 등 설명

| 옵션       | 설명                                  |
| ---------- | ------------------------------------- |
| {print $1} | 구분 기호로 구분 된 첫 번째 열을 표시 |
| -F         | 구분자를 지정한다 (디폴트는 스페이스) |



### 집계 정렬 (sort / uniq / wc)

- 원본 IP 주소 당 조회수 (내림차순)

``` bash
$ awk '{print $1}' access_log | sort | uniq -c | sort -rn
      3 203.0.113.2
      2 203.0.113.1
      2 198.51.100.3
```

- 원본 IP 주소 당 갯수

```bash
$ awk '{print $1}' access_log | sort | uniq | wc -l
3
```

- 옵션 설명

| 옵션    | 설명                                        |
| ------- | ------------------------------------------- |
| uniq -c | 중복 숫자 표시 (전후 만 비교하는 sort 필요) |
| sort -n | 숫자를 문자열이 아닌 숫자로 처리            |
| sort -r | 내림차순으로 정렬 (기본값은 오름차순)       |
| wc -l   | 행 수를 표시                                |



### 대체 (sed)

- 정상적인 접근의 소스 IP 주소의 익명화

``` bash
$ sed "s/203\.0\.113\.[0-9]\{1\,3\}/xxx.xxx.xxx.xxx/g" access_log
xxx.xxx.xxx.xxx - - [03/May/2020:12:00:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
xxx.xxx.xxx.xxx - - [03/May/2020:12:10:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
xxx.xxx.xxx.xxx - - [03/May/2020:12:20:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
xxx.xxx.xxx.xxx - - [03/May/2020:12:30:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
xxx.xxx.xxx.xxx - - [03/May/2020:12:40:00 +0900] "GET /index.html HTTP/1.1" 200 1000 "http://example.com/" "Mozilla/5.0 (Windows NT 6.3; Win64; x64)"
198.51.100.3 - - [03/May/2020:12:50:00 +0900] "GET /index.cgi?page=<Script>alert('Evil')</Script> HTTP/1.1" 200 3000 "-" "Evil User Agent"
198.51.100.3 - - [03/May/2020:13:00:00 +0900] "GET /../../../../../etc/shadow HTTP/1.1" 200 3000 "-" "Evil User Agent"
```

- 옵션 설명

| 옵션                  | 설명                                                         |
| --------------------- | ------------------------------------------------------------ |
| s/문자열 A/문자열 B/g | 문자열 A를 문자열 B로 대체합니다. g을 붙여 모두 대체한다. 정규식을 사용할 수 있습니다. |



### 참고

IPA에서 로그 분석을 지원하는 툴도 공개되어있어 이쪽의 활용에도 유효합니다.

