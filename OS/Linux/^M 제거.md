### ^M 제거

---

Dos Format 으로 되어 있는 파일을 Unix Format 으로 바꾸는 방법.



#### 1. 확인 방법

```
cat -v file.log

abcd^M
bcde^M
cdef^M
defg^M
```



#### 2. ^M 입력 방법 

```
ctrl + v + M
```



#### 3. tr 을 이용 하는 방법

```
# 변경 방법 1
tr -d '^M' < file.log

# 변경 방법 2
tr -d '\015' < file.log

# 변경된 내용 저장
tr -d '\015' < file.log > file.tr.log
```



#### 4.  sed 를 이용하는 방법

```
# 변경 방법 1
sed 's/^M//g' file.log

# 변경 방법 2
sed 's/\015//g' file.log

# 변경된 내용 저장(현재 파일 변경)
sed -i 's/^M//g' file.log

```



#### 5.  awk 를 이용하는 방법

```
# 변경 방법 1
awk 'sub(/^M/,"");1' file.log

# 변경 방법 2
awk 'sub(/\015/,"");' file.log

# 변경된 내용 저장
awk 'sub(/\015/,"");' file.log > file.awk.log

```



#### 6. perl 을 이용하는 방법

```
# 변경 방법 1
perl -p -e 's/^M//g' file.log

# 변경 방법 2
perl -p -e 's/\015//g' file.log

# 변경된 내용 저장(현재 파일 변경)
perl -pi -e 's/\015//g' file.log

```



#### 7. vi 를 이용하는 방법

```
# 변경 방법 1
:%s/^M//

# 변경 방법 2
:%s/\015//

# 변경 방법 3 - 파일 포멧 변경 > 저장 > 다시 파일 열기 > 변경됨(ff: File Format)
:set ff=unix

# 변경방법 4
:1,$s/^M//g

:wq

```



#### 8. doc2unix 를 사용하는 방법

```
dos2unix filename.ext

예) dos2unix test_prog.c
```



기타....