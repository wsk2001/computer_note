# 파이썬으로 DB 연동, SQLite 사용법 쉽게 이해하기

출처:  https://hleecaster.com/python-sqlite3/


데이터베이스를 관리하는 시스템을 DBMS(DataBase Management System)이라고 하며, 시중에 나와 있는 DBMS의 종류로는 SQLite, Oracle, MS-SQL, MySQL, MariaDB, PostgreSQL 등 다양한 것들이 있다. 그리고 이 DB를 관리하기 위해서는 SQL(Structured Query Language), 즉 구조화된 쿼리(질의) 언어의 기본적인 문법을 알면 된다.

파이썬에서는 sqlite3라는 표준 라이브러리/모듈을 기본적으로 제공하고 있기 때문에 이걸 기준으로 DB를 다뤄보는 기초적인 방법을 소개해본다. 여기서 SQL 문법을 아주 살~짝 사용한다.

목차는 아래와 같다.

- **준비: DB Browser for SQLite 설치**
- **파이썬 SQLite 라이브러리 불러오기 및 버전 확인**
- **DB 연결, 커서 획득**
- **데이터 삽입하기**
- **데이터 불러오기**
- **데이터 조회하기 (필터링)**
- **데이터 수정하기**
- **데이터 삭제하기**
- **DB 연결 해제**
- **DB 백업하기 (dump)**

------



## 준비: DB Browser for SQLite 설치

우선 파이썬으로 DB를 다루기 전에 앞서 데이터베이스를 직접 한 눈에 볼 수 있는(GUI) 브라우저를 하나 설치해보자. 역시 눈으로 봐야 직관적으로 이해가 되기 때문에…

DB Browser for SQLite 라는 프로그램이고, 아래 사이트 들어가면 다운 받아 무료로 사용할 수 있다.

[https://sqlitebrowser.org](https://sqlitebrowser.org/)

가벼운 PortableApp이 있으니 라이트한 사용자들은 이 버전으로 받는 게 좋다.

아무튼 이 데이터베이스 브라우저를 통해 직접 코딩을 통해 DB를 다루면서 그 내용들이 잘 반영이 되었는지 계속 확인하면서 작업을 하면 편하니 반드시 미리 설치를 해놓자.

실행해보면 이런 화면이 뜬다. (심지어 언어 설정이 한국어로도 가능한 프로그램이다.)

![img](https://wikidocs.net/images/page/12453/DB_Browser_for_SQLite.png)

이제 파이썬으로 DB를 다뤄보면서 이 브라우저를 통해 그 결과를 확인하자.



## 파이썬 SQLite 라이브러리 불러오기 및 버전 확인

우선 라이브러리를 불러와서 “sqlite3 라이브러리”의 버전과 “SQLite(DB 엔진)” 버전을 각각 출력해보자.

``` python
import sqlite3

print(sqlite3.version)
print(sqlite3.sqlite_version)
```

그냥 해본 거다. (…)



## DB 연결, 커서 획득

이제 진짜 DB를 생성하는 쿼리를 실행해보자.

``` python
# DB 생성 (오토 커밋)
conn = sqlite3.connect("test.db", isolation_level=None)

# 커서 획득
c = conn.cursor()

# 테이블 생성 (데이터 타입은 TEST, NUMERIC, INTEGER, REAL, BLOB 등)
c.execute("CREATE TABLE IF NOT EXISTS table1 \
    (id integer PRIMARY KEY, name text, birthday text)")
```

일단 `"test.db"`라는 데이터베이스를 생성해봤다. 브라우저로 열어서 보면 잘 보일 거다. 엑셀에서 첫 행을 적어두었다 생각하면 된다.

`c.execute("CREATE TABLE IF NOT EXISTS 테이블이름()")`안에 문자열로 필드(열) 이름과 데이터 타입을 작성해주면 된다.

`CREATE TABLE IF NOT EXITSTS 테이블_이름()` 이라는 쿼리는 말 그대로 “`테이블_이름`이라는 테이블이 없으면 테이블을 생성해라”라는 의미다. 있으면 있는 걸 그대로 쓰는 거고. 그 안에 필드를 나열해주면 되는데 **필드명 > 테이터 타입** 순으로 입력한다. `PRIMARY KEY`는 테이블 내에 있는 레코드를 식별하는 고유 키를 말하며, 유일한 것이어야 한다.



### 개념 1. commit & rollback

위에서 `sqlite3.connect()` 괄호 안에 `isolation_level=None`이라고 명시했는데 이는 (실습을 위해) 쿼리문을 실행하여 DB에 즉시 반영, 즉시 자동 커밋을 하기 위함이다.

commit(커밋)은 “변경사항을 DB에 반영한다”는 뜻이라 commit을 하지 않으면 수정(추가/갱신/삭제 등) 작업에 대한 기록을 컴퓨터 메모리 같은 데 임시로 가지고 있을 뿐 실제로 DB에는 반영하지 않는다. 최종적으로 DB를 수정을 하려면 마지막에 반드시 `conn.commit()`이라는 명령을 실행해주어야 한다.

참고로 이것과 commit과 반대되는 개념으로 rollback(롤백)이 있다. 이전 이력으로 되돌린다는 뜻. `conn.rollback()`으로 명령한다.



### 개념 2. cursor

파이썬에서 파일을 읽고 쓰려면 커서를 가져와야 한다. 그래서 `conn.cursor()`로 일단 커서를 생성한다.



## 데이터 삽입하기

데이터 한 건을 넣으려면 c.execute(“INSERT INTO table1 VALUES()”)라고 해서 값을 직접 넣자.

``` python
# 데이터 삽입 방법 1
c.execute("INSERT INTO table1 \
    VALUES(1, 'LEE', '1987-00-00')")
```

테이블 안에 데이터가 들어간 걸 확인할 수 있다. 그런데 이렇게 하는 건 필드명과 순서를 정확히 알고 있다는 전제 하에 가능한 거다.

만약 정석으로 데이터를 삽입하려면 아래와 같이 튜플로 넣어주는 게 좋다.

``` python
# 데이터 삽입 방법 2
c.execute("INSERT INTO table1(id, name, birthday) \
    VALUES(?,?,?)", \
    (2, 'KIM', '1990-00-00'))
```

이번엔 튜플이나 리스트 형태의 데이터 세트를 한 번에 삽입하는 방법도 알아야겠지. `c.executemany()`를 사용하면 된다.

``` python
test_tuple = (
    (3, 'PARK', '1991-00-00'),
    (4, 'CHOI', '1999-00-00'),
    (5, 'JUNG', '1989-00-00')
)

c.executemany("INSERT INTO table1(id, name, birthday) VALUES(?,?,?)", test_tuple)
```

끝이다. 리스트로도 가능하다.



## 데이터 불러오기

이제 데이터들이 잘 있나 확인해야겠지. 우선 데이터를 모두 선택한 다음에 `c.fetchone()`으로 하나씩 출력해보자.

``` python
c.execute("SELECT * FROM table1")
print(c.fetchone())
print(c.fetchone())
print(c.fetchall())
```

`c.fetchone()`을 사용하면 한 줄씩 출력하는 걸 알 수 있다. 커서가 이동하기 때문이다. 그리고 이후에 `c.fetchall()`이라는 걸 사용해서 전체를 가져와서 출력하더라도 이미 읽은 지점 이후에 있는 것들만 출력된다. (참고로 fetch는 “가져오다”라는 뜻.)

그래서 만약 전체 데이터를 출력하고 싶다면 이렇게 전체를 다시 읽어 놓고 해야 한다.

``` python
c.execute("SELECT * FROM table1")
print(c.fetchall())
```

그리고 이렇게 가져온 데이터는 리스트 형태로 출력되는 걸 알 수 있는데, 결국 반복문을 돌 수 있다는 뜻이다.

``` python
# 방법 1
c.execute("SELECT * FROM table1")
for row in c.fetchall():
    print(row)

# 방법 2
for row in c.execute("SELECT * FROM table1 ORDER BY id ASC"):
    print(row)
```

방법 2가 한 줄 덜 쓰긴 하지만 가독성이 떨어져서 차라리 방법 1이 나은 것 같다.



## 데이터 조회하기 (필터링)

원하는 데이터만 찾아서 가져올 수도 있어야겠지. SQL에서 WHERE 문을 써야 한다.

그런데 이때 방법이 이것저것 있다. 포매팅을 할 수도 있고, 딕셔너리 형식으로 설정해서 가져올 수도 있다. 좀 복잡하니 해보면서 익숙한 방법을 사용하면 된다.

여기서는 id라는 변수가 어떤 녀석인 것들만 선택해서 가져올지 짜봤다.

``` python
# 방법 1
param1 = (1,)
c.execute('SELECT * FROM table1 WHERE id=?', param1)
print('param1', c.fetchone())
print('param1', c.fetchall())

# 방법 2
param2 = 1
c.execute("SELECT * FROM table1 WHERE id='%s'" % param2)  # %s %d %f
print('param2', c.fetchone())
print('param2', c.fetchall())

# 방법 3
c.execute("SELECT * FROM table1 WHERE id=:Id", {"Id": 1})
print('param3', c.fetchone())
print('param3', c.fetchall())

# 방법 4
param4 = (1, 4)
c.execute('SELECT * FROM table1 WHERE id IN(?,?)', param4)
print('param4', c.fetchall())

# 방법 5
c.execute("SELECT * FROM table1 WHERE id In('%d','%d')" % (1, 4))
print('param5', c.fetchall())

# 방법 6
c.execute("SELECT * FROM table1 WHERE id=:id1 OR id=:id2", {"id1": 1, "id2": 4})
print('param6', c.fetchall())
```

처음엔 좀 어렵지만, 익숙해지면 어렵지 않게 쓸 수 있겠지.



## 데이터 수정하기

위에서 조회할 때 사용한 WHERE 문과 함께 UPDATE를 통해 갱신하는 거다.

id가 무엇인 녀석을 찾아서 name을 새로운 걸로 고쳐보자. 이렇게.

``` python
# 방법 1
c.execute("UPDATE table1 SET name=? WHERE id=?", ('NEW1', 1))

# 방법 2
c.execute("UPDATE table1 SET name=:name WHERE id=:id", {"name": 'NEW2', 'id': 3})

# 방법 3
c.execute("UPDATE table1 SET name='%s' WHERE id='%s'" % ('NEW3', 5))

# 확인
for row in c.execute('SELECT * FROM table1'):
    print(row)
```

편한 방법으로 사용하자.



## 데이터 삭제하기

테이블에 있는 특정 데이터를 지우려면 WHERE과 DELETE를 조합하면 된다.

``` python
# 방법 1
c.execute("DELETE FROM table1 WHERE id=?", (1,))

# 방법 2
c.execute("DELETE FROM table1 WHERE id=:id", {'id': 3})

# 방법 3
c.execute("DELETE FROM table1 WHERE id='%s'" % 5)

# 확인
for row in c.execute('SELECT * FROM table1'):
    print(row)
```

만약 테이블에 있는 데이터 전체를 지우려면 conn.execute() 안에 쿼리문을 써주면 된다.

``` python
# 방법 1
# conn.execute("DELETE FROM table1")

# 방법 2
print(conn.execute("DELETE FROM table1").rowcount)
```

뒤에 rowcount를 붙여주면 지운 행 개수를 돌려준다.



## DB 연결 해제

데이터베이스를 연결해서 이런저런 수정을 했으면 마지막엔 그 연결을 해제해야 한다. 그래서 항상 `conn.close()` 명령으로 마무리한다.



## DB 백업하기 (dump)

데이터베이스는 항상 dump를 통해 백업을 해놓는 게 중요하다. 그래야 나중에 다른 PC에서도 이걸 그대로 재구성할 수 있다.

``` python
with conn:
    with open('dump.sql', 'w') as f:
        for line in conn.iterdump():
            f.write('%s\n' % line)
        print('Completed.')
```

이래놓고 sql 파일을 열어보면 신기하게 아래와 같이 생겼다.

``` python
BEGIN TRANSACTION;
CREATE TABLE table1(id integer PRIMARY KEY, name text, birthday text);
INSERT INTO "table1" VALUES(1,'LEE','1987-00-00');
INSERT INTO "table1" VALUES(2,'KIM','1990-00-00');
INSERT INTO "table1" VALUES(3,'PARK','1991-00-00');
INSERT INTO "table1" VALUES(4,'CHOI','1999-00-00');
INSERT INTO "table1" VALUES(5,'JUNG','1989-00-00');
COMMIT;
```

실제로 DB를 아예 재구성하는 SQL 쿼리들이 작성되어 있다.



### 끝

