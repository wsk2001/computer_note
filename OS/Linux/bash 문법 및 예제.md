# bash 문법 및 예제

본 문서는 bash script 를 작성 할때 참고 할 수 있는 문법 및 확용 예제를 기록 하여 시스템 운영에 도움을 주기 위해 작성 되었습니다.

각 자료는 인터넷 및 서적에서 수집 되었으며 개인이 활용 하기 위한 용도로 작성 되었습니다.

가능한 경우 출처를 기록 할 예정 입니다.



## 1. bash 문법 



### bash while loop syntax

출처: https://www.cyberciti.biz/faq/bash-while-loop/

```bash
#!/bin/bash
while :
do
	echo "infinite loops [ hit CTRL+C to stop]"
	sleep 3
done
```



### 문자열 split

``` bash
str="abc@naver.com;bcd@naver.com;cde@naver.com"

mails=$(echo $str | tr ";" "\n")

for mail in $mails
do
	echo "[$mail]"
done
```











## 2. bash 에 사용되는 tools







## 3. bash 예제

###  3.1 문자열 변수 조작 (자르기, 삭제, 치환)

**3.1.1. 변수 앞에서 몇개만 잘라내기**

python의 slice랑 비슷하지만 조금 다르다. Index는 python과 동일하게 Zero-based index를 사용한다.

${변수명:시작지점}  => 시작지점부터 끝까지

${변수명:시작지점:갯수}  => python과 달리 끝지점이 아닌 갯수임에 유의

``` bash
[root@NETo-c7test01 ~]# AAA="ABCDEFGH"
[root@NETo-c7test01 ~]# echo ${AAA:0}
ABCDEFGH
[root@NETo-c7test01 ~]# echo ${AAA:4}
EFGH
[root@NETo-c7test01 ~]# echo ${AAA:2:3}
CDE
```

끝나는 지점을 지정하고 싶으면 갯수 대신 음수로 넣으면 python의 slice와 동일하게 작동한다. 단 끝지점은 열린 구간으로 포함되지 않는 Index이다.

${변수명:시작지점:음수끝지점}  => 음수로 표현되며, -1이 가장 마지막 문자이고, 끝지점은 포함되지 않는다.



**3.1.2. 변수 뒤에서 몇개만 잘라내기**

사실 위 변수 앞에서 자르기 개념과 동일한데, 시작 지점을 음수로 적용하는 것이다. 단 음수 시작지점은 한칸 띄우거나 괄호로 묶어줘야 한다.

${변수명: 음수시작지점}

${변수명:(음수시작지점)}

${변수명: 음수시작지점:갯수}

${변수명: 음수시작지점:음수끝지점}

``` bash
[root@NETo-c7test01 ~]# AAA="ABCDEFGH"
[root@NETo-c7test01 ~]# echo ${AAA: -4}
EFGH
[root@NETo-c7test01 ~]# echo ${AAA:(-4)}
EFGH
[root@NETo-c7test01 ~]# echo ${AAA: -4:2}
EF
[root@NETo-c7test01 ~]# echo ${AAA: -4:-3}
E
```



**3.1.3. 변수 일부 문자열 삭제하기**

${변수명#삭제문자}  => 시작지점을 포함하여 가장 짧게 매칭되는 패턴 삭제

${변수명##삭제문자}  => 시작지점을 포함하여 가장 길게 매칭되는 패턴 삭제

${변수명%삭제문자}  => 끝지점을 포함하여 가장 짧게 매칭되는 패턴 삭제

${변수명%%삭제문자}  => 끝지점을 포함하여 가장 길게 매칭되는 패턴 삭제



와일드카드 ?, * 활용 가능하다.

``` bash
[root@NETo-c7test01 ~]# AAA="AAABBBCCCBBBDDDAAA"
[root@NETo-c7test01 ~]# echo ${AAA#A*B}
BBCCCBBBDDDAAA
[root@NETo-c7test01 ~]# echo ${AAA##A*B}
DDDAAA
[root@NETo-c7test01 ~]# echo ${AAA%B*A}
AAABBBCCCBB
[root@NETo-c7test01 ~]# echo ${AAA%%B*A}
AAA
```



**3.1.4. 변수 일부 문자열 치환하기**

사실 본인은 치환의 경우 pipe로 보내서 sed, perl을 활용하는 편이기는 하다. 그래서 이건 복잡한거 빼고 기본 예제만 남겨둔다. * 을 사용하면 greedy 하게 매칭한다.

${변수명/찾을패턴/바꿀문자}  => 처음 매칭되는 1개만 변경

${변수명//찾을패턴/바꿀문자}  => 매칭되는 전체 변경

``` bash
[root@NETo-c7test01 ~]# AAA="AAABBBCCCBBBDDDAAA"
[root@NETo-c7test01 ~]# echo ${AAA/BBB/111}
AAA111CCCBBBDDDAAA
[root@NETo-c7test01 ~]# echo ${AAA//BBB/111}
AAA111CCC111DDDAAA
[root@NETo-c7test01 ~]# echo ${AAA/B*D/111}
AAA111AAA
```



**3.1.5. 변수 길이 구하기**

변수명 앞에 # 을 붙여준다.

${#변수명}

``` bash
[root@NETo-c7test01 ~]# AAA="ABCDEFGH"
[root@NETo-c7test01 ~]# echo ${#AAA}
8
```

