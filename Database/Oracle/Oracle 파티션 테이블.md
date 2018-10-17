### 오라클 파티션 정의
------

출처: http://12bme.tistory.com/290

**파티션 개요**

오늘날 기업에서 관리하는 데이터는 수백테라 바이트에 이르는 데이터베이스를 관리합니다. 하지만 이런 데이터들 중 몇몇의 Big Transaction Table이 거의 모든 데이터를 가지고 있고 나머지 테이블들은 이 Big Transaction Table을 경유하여 액세스하는 용도로 사용됩니다. 이렇게 데이터 크기가 크고 중요한 Big Transaction Table을 관리하는 부분에서 Troubleshooting이 발생될 경우 데이터베이스의 성능 및 관리작업에 심각한 영향을 받을 수 있습니다.

이러한 리스크가 있는 Big Transaction Table을 보다 효율적으로 관리하기 위해 Table을 작은 단위로 나눔으로써 데이터 액세스 작업의 성능 향상을 유도하고 데이터 관리를 보다 수월하게 하고자 하는 개념입니다.

**파티션 테이블의 장점**

1) 개선된 가용성

 - 파티션은 독립적으로 관리된다.

 - Backup and Restore을 파티션별로 작업할 수 있다.

 - 같은 테이블에서 Unavailable한 파티션은 다른 파티션에 영향을 주지 않는다.

2) 관리의 용이성

 - 사용자가 지정한 값으로 파티션이 가능하다.

 - 테이블스페이스간에 파티션 이동이 가능하다.

 - 파티션 레벨에서 SELECT, DELETE, UPDATE가 가능하다.

3) 개선된 성능

 - 데이터를 액세스할 때 액세스하는 범위를 줄여 퍼포먼스 향상을 가져올 수 있다.

 - RAC(Real Application Clusters) 환경에서 인스턴스간 Block Connection을 감소시킨다.

![img](C:\WORKSPACE\wonsool\static\images\oracle\rac.png)

**파티션 테이블 사용시 주의할 점**

1) 관리적인 관점

​     - 하나의 테이블을 세분화하여 관리하기 때문에 보다 세심한 관리가 요구된다.

​     - 파티션을 잘못 구성 또는 관리하여 IU(Index Unusable)에 빠지는 것을 주의해야 한다.

2) 사용하는 관점

​     - 파티션 키로 나누어져 있는 테이블에 파티션 키를 조건으로 주지 않아 전체 파티션을 액세스하지 않도록 주의해야 한다.

**파티션 테이블의 특징**

\- 파티션 테이블은 파티션 키 값에 의해 구성되며, 한 테이블 당 가능한 파티션은 이론적으로 65,535개를 지원하나 실질적으로는 10,000개까지만 생성 가능합니다(Oracle Ver 10.2.0.3 테스트)

- 모든 파티션 테이블(또는 인덱스)는 같은 Logical Attribute를 가져야 합니다. 

   ex) Columns, Data Types, Constraints ...

- 모든 파티션 테이블(또는 인덱스)는 다른 Physical Attribute를 가져야 합니다.

   ex) PCTFREE, PCTUSED, INITTRANS, MAXTRANS, TABLESPACE, STORAGE ...

\- 파티션 테이블은 'KEY', 'VALUES LESS THAN Literal', 'Physical Attributes'로 구성된다.

- 'VALUES LESS THAN Literal' 절에서 'Literal' 값에는 SQL Function을 지원한다.

\- Composite Column 구성은 16개까지 가능합니다.

**파티션 종류**

------

**오라클 버전에 따른 파티션**

1) Oracle Ver 7.3

\- Partition View를 처음으로 도입하였다.

\- 당시 Partition View는 큰 테이블을 동일한 템플릿을 가진 여러 개의 다른 테이블로 분할하고 UNION ALL을 사용하여 View로 묶은 형태이다.

\- 그러나 이 방식은 관리의 어려움, 활용성의 부족, 성능 등에 대한 이슈로 인하여 Oracle Ver 9i에서는 더이상 지원하지 않는다.

2) Oracle Ver 8.0

\- 컬럼 값의 Range 기반으로 된 Range Partition이 최초로 도입되었고, 비로소 Partition의 모습을 갖추었다.

\- 각 파티션은 각기 다른 테이블 스페이스, Segment에 저장이 가능한다.

3) Oracle Ver 8i

\- 컬럼 값의 Hash 기반으로 된 hash partition과 Sub Partition을 할 수 있는 Composite Partition이 추가되었다.

\- 이 당시 Composite Partition은 Range-Hash로만 구성 가능함.

4) Oracle Ver 9i

\- 리스트 값으로 파티션을 할 수 있는 List Partition이 추가되었다.

\- Composite Partition에서는 Range-Hash 이외에 Range-List가 추가 지원되었다.

5) Oracle Ver 10g

\- 10T 파티션이 추가되었다.

6) Oracle Ver 11g

\- Composite Partition에서 확장된 Extended Composite Partition이 지원된다.

​    -> Range-Range, List-Range, List-Hash, List-List

\- Reference Partition 추가

\- Interval Partition 추가

\- System Partition 추가

\- Virtual Column Partition 추가

**Partition Table**

**1) Range Partition**

\- Column Value의 범위를 기준으로 하여 행을 분할하는 형태이다.

\- Range Partition에서 Table은 단지 논리적인 구조이며 실제 데이터가 물리적으로 저장되는 곳은 Partition으로 나누어진 Tablespace에 저장이 된다.

\- PARTITION BY RANGE ( column_list ) : 기본 Table에서 어느 Column을 기준으로 분할할지를 정함

\- VALUES LESS THAN ( value_list ) : 각 Partition이 어떤 값의 범위를 포함할지 Upper Bound를 정함.

###### 구문 Sample 
```
CREATE TABLE SALES_DATA_2008
	(
	  COLUMN_1 NUMBER   NOT NULL,
	  COLUMN_2 VARCHAR2(4),
	  COLUMN_3 VARCHAR2(4),
	  COLUMN_4 VARCHAR2(2),
	  COLUMN_5 VARCHAR2(2),
	  COLUMN_6 NUMBER
	)
	TABLESPACE TABLE_SPACE_DATA_1
	PCTFREE 5
	PCTUSED 40
	INITRANS 11
	MAXTRANS 255
	STORAGE
	(
	  INITIAL 2048K
	  NEXT 1024K
	  PCTINCREASE 0
	  MINEXTENTS 1
	  MAXEXTENTS 121
	)
	PARTITION BY RANGE ( COLUMN_3, COLUMN_4, COLUMN_5 )
	(
	  PARTITION P_200801 VALUES LESS THAN ('2008', '04', '01'),
	  PARTITION P_200802 VALUES LESS THAN ('2008', '07', '01'),
	  PARTITION P_200803 VALUES LESS THAN ('2008', '10', '01'),
	  PARTITION P_200804 VALUES LESS THAN ('2009', '01', '01'),
	  PARTITION P_5    VALUES LESS THAN (MAXVALUE)
	  TABLESPACE TABLE_SPACE_DATA_2
	  PCTFREE 5
	  PCTUSED 40
	  INITRANS 11
	  MAXTRANS 255
	  STORAGE
	  (
		INITIAL 1M
		NEXT 1M
		PCTINCREASE 0
		MINEXTENTS 1
		MAXEXTENTS 121
	  )
	);
```


**2) Hash Partition**

\- Partitioning column의 Partitioning Key 값에 Hash 함수를 적용하여 Data를 분할하는 방식

\- 데이터 이력관리의 목적보다 성능 향상의 목적으로 나온 개념이다.

 Hash Partition은 Range Partition에서 범위를 기반으로 나누었을 경우 특정범위의 분포도가 몰려서 각기 Size가 다르게 되는 것을 보완하여, 일정한 분포를 가진 파티션으로 나누고 균등한 데이터 분포도를 이용한 병렬처리로 퍼포먼스를 보다 향상시킬 수 있다.

\- Hash Partition에서 Table은 단지 논리적인 구조이며 실제 데이터가 물리적으로 저장되는 곳은 Partition으로 나누어진 Tablespace에 저장이 된다.

 

###### 구문 Sample

```
CREATE TABLE SALES_DATA_2008
(
  COLUMN_1 NUMBER   NOT NULL,
  COLUMN_2 VARCHAR2(4),
  COLUMN_3 VARCHAR2(4),
  COLUMN_4 VARCHAR2(2),
  COLUMN_5 VARCHAR2(2),
  COLUMN_6 NUMBER
)
TABLESPACE TABLE_SPACE_DATA_1
PCTFREE 5
PCTUSED 40
INITRANS 11
MAXTRANS 255
STORAGE
(
  INITIAL 2048K
  NEXT 1024K
  PCTINCREASE 0
  MINEXTENTS 1
  MAXEXTENTS 121
)
PARTITION BY HASH (COLUMN_3, COLUMN_4, COLUMN_5)
(
  PARTITION P_200801,
  PARTITION P_200802,
  PARTITION P_200803,
  PARTITION P_200804,
  PARTITION P_5 VALUES LESS THAN (MAXVALUE)
)
);
```



**3) Composite(Sub) Partition**

\- 파티션의 칼럼을 Main-Sub 관계로 나누어 분할하는 방식.

\- Composite Partition이 아닌 다른 파티션에서 물리적인 데이터가 저장되는 곳은 Table이 아닌 Partition Table에 저장이 되는 것처럼, Composite Partition에서는 Main Partition이 아닌 Sub Partition에 저장된다.

\- Composite Partition의 조합 구성은 Oracle의 버전이 올라갈수록 조합하는 방식을 다양하게 지원한다.

###### 구문 Sample

```
CREATE TABLE SALES_DATA_2008
(
  COLUMN_1 NUMBER   NOT NULL,
  COLUMN_2 VARCHAR2(4),
  COLUMN_3 VARCHAR2(4),
  COLUMN_4 VARCHAR2(2),
  COLUMN_5 VARCHAR2(2),
  COLUMN_6 NUMBER
)
TABLESPACE TABLE_SPACE_DATA_1
PCTFREE 5
PCTUSED 40
INITRANS 11
MAXTRANS 255
STORAGE
(
  INITIAL 2048K
  NEXT 1024K
  PCTINCREASE 0
  MINEXTENTS 1
  MAXEXTENTS 121
)
PARTITION BY RANGE ( COLUMN_3, COLUMN_4 )
SUBPARTITION BY HASH ( COLUMN_5 )
(
  PARTITION P_200801 VALUES LESS THAN ('2008', '04'),
  PARTITION P_200802 VALUES LESS THAN ('2008', '07'),
  PARTITION P_200803 VALUES LESS THAN ('2008', '10')
    (SUBPARTITIONS P_200803_S1 TABLESPACE TABLE_SPACE_DATA_1_1,
     SUBPARTITIONS P_200803_S2 TABLESPACE TABLE_SPACE_DATA_1_2,
     SUBPARTITIONS P_200803_S3 TABLESPACE TABLE_SPACE_DATA_1_3,
     SUBPARTITIONS P_200803_S4 TABLEPSACE TABLE_SPACE_DATA_1_4,
     SUBPARTITIONS P_200803_S5 TABLEPSACE TABLE_SPACE_DATA_1_5,
     SUBPARTITIONS P_200803_S6 TABLEPSACE TABLE_SPACE_DATA_1_6,
     SUBPARTITIONS P_200803_S7 TABLEPSACE TABLE_SPACE_DATA_1_7,
     SUBPARTITIONS P_200803_S8 TABLEPSACE TABLE_SPACE_DATA_1_8,
    ),
  PARTITION P_200804 VALUES LESS THAN ('2009', '01')
 
);
```



**4) List Partition**

\- Partitioning Column의 특정 값으로 분할하는 방식

\- 데이터 분포도가 낮지 않고, 균등하게 분포되어 있을때 유용하다.

\- Composite Partition에서 'Range-List'일 경우 그 효율이 더욱 높아진다.

\- 다른 파티션 방식처럼 다중 컬럼을 지원하지 않고 단일 컬럼만 가능하다.

\- 구문 Sample

```
CREATE TABLE SALES_DATA_2008
(
  COLUMN_1 NUMBER   NOT NULL,
  COLUMN_2 VARCHAR2(4),
  COLUMN_3 VARCHAR2(4),
  COLUMN_4 VARCHAR2(2),
  COLUMN_5 VARCHAR2(2),
  COLUMN_6 NUMBER
)
TABLESPACE TABLE_SPACE_DATA_1
PCTFREE 5
PCTUSED 40
INITRANS 11
MAXTRANS 255
STORAGE
(
  INITIAL 2048K
  NEXT 1024K
  PCTINCREASE 0
  MINEXTENTS 1
  MAXEXTENTS 121
)
PARTITION BY LIST(COLUMN_2)
(
  PARTITION RS VALUES('A') TABLESPACE TABLE_SPACE_DATA_2,
  PARTITION RM VALUES('B') TABLESPACE TABLE_SPACE_DATA_3,
  PARTITION RN VALUES('C') TABLESPACE TABLE_SPACE_DATA_4,
);
```



**5) Reference Partition**

\- Reference Key로 지정된 경우 부모 테이블의 컬럼이 존재하지 않아도 부모의 Partition Key로 분할하는 방식

\- 구문 Sample

```
CREATE TABLE CUSTOMERS
(
  CUST_ID   NUMBER    PRIMARY KEY,
  CUST_NAME VARCHAR2(200),
  RATING    VARCHAR2(1)   NOT NULL
)
PARTITION BY LIST(RATING)
(
  PARTITION PA VALUES('A'),
  PARTITION PB VALUES('B')
);
-- Detail Table
CREATE TABLE SALES
(
  SALES_ID   NUMBER   PRIMARY KEY,
  CUST_ID    NUMBER   NOT NULL,
  SALES_AMT  NUMBER,
  CONSTRAINT FK_SALES_01 FOREIGN KEY (CUST_ID) REFERENCES CUSTOMERS
)
PARTITION BY REFERENCE (FK_SALES_01);
```



\- 제약조건

   1) Foreign Key 제약조건이 설정되어 있어야 한다.

   2) 상속받는 테이블의 Key값이 NOT NULL 이어야 한다.

\- 테스트

```
-- Normal
SELECT  *
FROM  SALE_TMP  A,
  CUSTOMERS B
WHERE A.CUST_ID = B.CUST_ID
  AND B.RATING = 'A';
 
Rows     Row Source Operation
-------  ------------------------------------
     0    STATEMENT
    28    HASH JOIN 
    28     PARTITION LIST SINGLE PARTITION: 1
    28      TABLE ACCESS FULL CUSTOMERS PARTITION: 1
    56     TABLE ACCESS FULL SALE_TMP
 
-- Reference Partition
SELECT  *
FROM  SALES     A,
  CUSTOMERS B
WHERE   A.CUST_ID = B.CUST_ID
  AND B.RATING = 'A';
 
Rows     Row Source Operation
-------  -------------------------------------        
     0    STATEMENT
    28    PARTITION LIST SINGLE PARTITION: 1
    28     HASH JOIN
    28      TABLE ACCESS FULL CUSTOMERS PARTITION: 1
    28      TABLE ACCESS FULL SALES PARTITION: 1
```



**6) Interval Partition**

\- Range Partition에서 특정 범위를 지정하고 관리할때는 미리 Range를 만들어주어야 하고 생성 이후 분할 또는 병합을 할 때는 추가적인 작업을 해주어야 한다.

\- 하지만 'Interval Partition'에서는 각 파티션을 미리 정의함으로써 파티션 생성을 오라클이 직접 해주는 방법입니다.

\- 예제 Sample


```
-- 1. Range Partition 생성
CREATE TABLE SALES6
(
  SALES_ID NUMBER,
  SALES_DT DATE
)
PARTITION BY RANGE(SALES_DT)
(
  PARTITION P0701 VALUES LESS THAN (TO_DATE('20070201', 'YYYYMMDD')),
  PARTITION P0701 VALUES LESS THAN (TO_DATE('20070301', 'YYYYMMDD'))
);
 
-- 2. Partition Key 값의 범위에 없는 값으로 Insert
INSERT INTO SALES6 VALUES(1, TO_DATE('20070401', 'YYYYMMDD'));
 
-- Error
ORA-14400: inserted partition key does not map to any PARTITON
 
-- 3. Intrval Partition 생성
CREATE TABLE SALES6
(
  SALES_ID NUMBER,
  SALES_DT DATE
)
PARTITION BY RANGE(SALES_DT) INTERVAL (NUMTOYMINTERVAL(1, 'MONTH'))
(
  PARTITION P0701 VALUES LESS THAN(TO_DATE('20080201', 'YYYYMMDD'))
);
 
-- 4. Partition Key 값의 범위에 없는 값으로 Insert
INSERT INTO SALES6 VALUES(1, TO_DATE('20070601', 'YYYYMMDD'));
 
-- No Error
1 row created.
```


\- 파티션을 특정 테이블 스페이스에 저장하고 싶다면 STORE IN 구문으로 가능하다.

    -> INTERVAL (NUMTOYMINTERVAL(1, 'MONTH')) STORE IN (TS1, TS2, TS3)

\- 애플리케이션 개발자가 특정 파티션에 접하고 싶다면 다음의 구문으로 가능하다.

    -> SELECT * FROM SALES6 PARTITION FOR(TO_DATE('20080501', 'YYYYMMDD'));



**7) System Partition**

\- 테이블 생성시 파티션 구간을 미리 설정하는 것이 아니라 임의로 나눈 파티션에 대해 사용자가 원하는 파티션에 데이터를 저장하는 방식.

- 이 방식은 사용자가 'System Partition'으로 되어 있는 테이블의 데이터를 DML하고자 할 때 직접 파티션을 지정하여 해주어야 한다.

\- 로컬 인덱스 생성 시, 인덱스도 동일한 방법으로 파티셔닝된다.

\- 예제 Sample

```
CREATE TABLE SALES3
(
  SALES_ID  NUMBER,
  PRODUCT_CODE  NUMBER,
  STATE_CODE  NUMBER
)
PARTITION BY SYSTEM
(
  PARTITION P1 TABLESPACE USERS,
  PARTITION P2 TABLESPACE USERS
);
```



- Insert 할 때는 반드시 파티션을 지정해 주어야 한다.  

```
-- Insert할 때 테이블의 파티션을 지정하지 않을 경우
INSERT INTO SALES3 VALUES(1, 101, 1);

-- Error
ERROR at line 1:
ORA-14701: partition-extended name or bind variable must be sued for DMLs on tables partitioned by the System method

-- Insert을 할 때 테이블의 파티션을 지정한 경우
INSERT INTO SALES3 PARTITION(P1) VALUES (1, 101, 1);

-- No Error
1 row created.
```

- Delete, Update 할 때는 필수는 아니지만 파티션을 지정하지 않을 경우 모든 파티션을 찾아다녀야 하므로 이 경우에도 가급적 파티션을 지정해 주어야 한다.

```
DELETE SALES3 PARTITION(P1) WHERE STATUS_CODE = 1;
```



##### 8) Virtual Column Partition

- 파티션으로 나누고자 하는 칼럼이 테이블에서 가공되어 얻을 수 있는 칼럼일 경우 11g 이전에서는 새로운 칼럼을 추가하고 트리거를 이용하여 칼럼 값을 생성하는 방법을 사용하여 많은 오버헤드를 감수하였으나, 11g에서는 'Virtual Column Partition'을 원하여 실제로 저장되지 않는 칼럼을 런타임에 계산하여 생성할 수 있다. 또한 가상 컬럼에 파티션을 적용하는 것도 가능하다.

- 예제 Sample

```
-- Virtual Partition 생성
CREATE TABLE SALES
(
  SALES_ID  NUMBER,
  CUST_ID   NUMBER,
  SALE_CATEGORY   VARCHAR2(6)
  GENERATED ALWAYS AS
  (
    CASE WHEN SALES_AMT <= 10000 THEN 'LOW'
      WHEN SALES_AMT BETWEEN 10000 AND 100000 THEN CASE WHEN CUST_ID < 101 THEN 'LOW'
                  WHEN BETWEEN 101 AND 200 THEN 'MEDIUM'
                  ELSE 'LOW' END
      WHEN SALES_AMT BETWEEN 100000 AND 1000000 THEN CASE WHEN CUST_ID < 1010  THEN 'MEDIUM'
                  WHEN BETWEEN 101 AND 200 THEN 'MEDIUM'
                  ELSE 'ULTRA' END
      ELSE 'ULTRA' END
  ) VIRTUAL
PARTITION BY LIST(SALES_CATEGORY)
(
  PARTITION P_LOW   VALUES ('LOW'),
  PARTITION P_MEDIUM  VALUES ('MEDIUM'),
  PARTITION P_HIGH  VALUES ('HIGH'),
  PARTITION P_ULTRA VALUES ('ULTRA')
);
 
-- Insert 테스트
INSERT INTO SALES(SALES_ID, CUST_ID, SALES_AMT) VALUES (1, 1, 100);
 
-- No Error
1 row created.
```



#### Partition Index



##### 1) Local Index

- 인덱스를 생성한 인덱스와 파티션된 인덱스가 동일하게 파티션된 형태를 말합니다.

- 인덱스와 테이블은 같은 칼럼에 의해 파티션되며, 하나의 인덱스 파티션이 테이블 파티션 하나와 대응되며, 대응되는 인덱스 파티션과 테이블 파티션은 각각 같은 범위를 갖게 됩니다.

- 결국 특정한 하나의 인덱스에 포함된 모든 Key들은 하나의 테이블 파티션 내의 데이타만 가리키게 됩니다.



**1-1) Local Prefixed Index**

- 인덱스의 맨 앞에 있는 컬럼에 의해 파티션되는 방식입니다.

- Local Prefixed Index에서 칼럼은 Unique/Non-Unique를 모두 허용합니다.

- Base Table 의 파티션이 변경되면 Local Index의 관련 파티션만 변경이 됩니다.

```
CREATE TABLE DEPT
(
  DEPT  NUMBER    NOT NULL,
  DNAME   VARCHAR2(10)  NOT NULL,
  LOC VARCHAR2(14)
)
PARTITION BY RANGE (DEPTNO)
(
  PARTITION PART_1 VALUES LESS THAN (30),
  PARTITION PART_2 VALUES LESS THAN (MAXVALUE)
);
 
CREATE INDEX DEPT_N1 ON DEPT(DEPTNO) LOCAL;

1 row created.
```



**1-2) Local Non-Prefixed Index**

\- Index의 첫번째 Column이 Partition Key가 아닌 형태로 Base Table과 동일한 Partition 구조를 가진 Index입니다(equi-partitioned)

\- 빠른 Access가 요구될 때 유용합니다(Base Table의 Partition Key는 제외)

\- Partition 단위로 관리할 수 있으므로 Global Index에 비해 운영상 편리합니다.

- OLAP 측면에서 Global Index보다 조회 속도가 저하됩니다.

 

```
CREATE TABLE DEPT
(
  DEPTNO  NUMBER    NOT NULL,
  DNAME VARCHAR2(10)  NOT NULL,
  LOC   VARCHAR2(14)
)
PARTITION BY RANGE (DEPTNO)
(
  PARTITION PART_1 VALUES LESS THAN (30),
  PARTITION PART_2 VALUES LESS THAN (MAXVALUE)
);
 
CREATE INDEX DEPT_N2 ON DEPT(LOC) LOCAL;
```

**2) Global Index**
- Global Index는 테이블과 다르게 파티션되는 경우입니다.



**2-1) Global Prefixed Index**
- Base Table과 비교하여 not equi-partitioned 상태입니다.
- Oracle은 only Index structure만 관리합니다. (Partition은 관리안함)
- 최종 Partition에는 Maxvalue값이 반드시 기술되어야 합다.
- Local index보다 관리하기 힘듭니다.
- 기준 Table의 Partition이 변경되면 global index의 모든 Partition에 영향을 미칩니다.
  (Global Index 재생성 해야함) 

```
-- 테이블 생성
CREATE TABLE SALES_DATA_2008
(
  COLUMN_1 NUMBER   NOT NULL,
  COLUMN_2 VARCHAR2(4),
  COLUMN_3 VARCHAR2(4),
  COLUMN_4 VARCHAR2(2),
  COLUMN_5 VARCHAR2(2),
  COLUMN_6 NUMBER
)
PARTITION BY RANGE ( COLUMN_3, COLUMN_4 )
(
  PARTITION P_200801 VALUES LESS THAN ('04', '01'),
  PARTITION P_200802 VALUES LESS THAN ('07', '01'),
  PARTITION P_200803 VALUES LESS THAN ('10', '01'),
  PARTITION P_200804 VALUES LESS THAN ('12', MAXVALUE)
);
 
-- Global Prefixed Index
CREATE UNIQUE INDEX RANGE2_GPX8 ON SALES_DATA_2008(COLUMN_2, COLUMN_1)
TABLESPACE TABLE_SPACE_DATA_1
PCTFREE   10
STORAGE( INITIAL 100K NEXT 100K PCTINCREASE 0 )
GLOBAL PARTITION BY RANGE ( CODE )
(
  PARTITION P_2008_P1 VALUES LESS THAN ('2000'),
  PARTITION P_2008_P2 VALUES LESS THAN ('3000'),
  PARTITION P_2008_P3 VALUES LESS THAN (MAXVALUE)
);
```



**2-2) Non-Partitioned Index**

\- 파티션과는 아무런 상관없는 Normal Index를 말함.

 


#### 파티션을 사용할 때 알아야 할 사항들

파티션 테이블 및 인덱스 관리를 위한 명령어들

1) 일반 테이블 파티션 하기
- Export/Import 하는 방법
```
-- 테이블을 Export 한다.
exp user/password tables=number file=exp.dmp
 
-- 백업받은 테이블을 제거한다.
drop table numbers;
 
-- 파티션 테이블을 생성한다.
CREATE TABLE NUMBER(QTY NUMBER(3), NAME VARCHAR2(15))
PARTITION BY RANGE(QTY)
  ( PARTITION P1 VALUES LESS THAN (501),
    PARTITION P2 VALUES LESS THAN (MAXVALUE));
 
--ignore=y를 사용하여 데이터를 Import한다.
imp user/password tables=number file=ex.dmp ignore=y
```

- Subquery를 이용한 방법

```
-- 파티션 테이블을 생성한다.
CREATE TABLE PARTBL(QTY NUMBER(3), NAME VARCHAR2(15))
PARTITION BY RANGE(QTY)
  ( PARTITION P1 VALUES LESS THAN (501),
    PARTITION P2 VALUES LESS THAN (MAXVALUE));
 
-- Subquery를 이용하여 파티션 테이블에 데이터를 입력한다.
INSERT INTO PARTBL(QTY, NAME) SELECT * FROM ORIGTBL;
```

- Partition Exchange 명령어를 하는 방법

```
-- ALTER TABLE EXCHANGE PARTITION은 파티션 테이블을 일반 테이블로,
-- 또는 파티션 되어있지 않은 테이블을 파티션 테이블로 변경시킬 때 사용한다.
 
-- 파티션 테이블 생성
CREATE TABLE P_EMP (SAL NUMBER(7,2))
PARTITION BY RANGE(SAL)
  (PARTITION EMP_P1 VALUES LESS THAN (2000),
   PARTITION EMP_P2 VALUES LESS THAN (4000));
 
-- 첫번째 파티션에 들어갈 데이터
CREATE TABLE DUMMY_Y
SELECT  SAL
FROM  EMP
WHERE   SAL < 2000;
 
-- 두번째 파티션에 들어갈 데이터
CREATE TABLE DUMMY_Z
SELECT  SAL
FROM  EMP
WHERE SAL BETWEEN 2000 AND 3999;
 
ALTER TABLE P_EMP EXCHANGE PARTITION EMP_P1
WITH TABLE DUMMY_Y;
 
ALTER TABLE P_EMP EXCHANGE PARTITION EMP_P2
WITH TABLE DUMMY_Z;
```

- 여러 개의 파티션으로 분리된 테이블 중 일부의 파티션만 가진 테이블 생성하기

```
-- 데이타를 Export한 후 필요한 파티션으로 이루어진 테이블을 생성한다.
-- 데이터 생성
CREATE TABLE YEAR(COL1 DATE) PARTITION BY RANGE (COL1)
  ( PARTITION OCTOBER   VALUES LESS THAN ('01-NOV-1999') TABLESPACE OCTOBER,
    PARTITION NOVEMBER  VALUES LESS THAN ('01-DEC-1999') TABLESPACE NOVEMBER,
    PARTITION DECEMBER  VALUES LESS THAN (MAXVALUE) TABLESPACE DECEMBER);
-- 데이터를 Import
IMP USERNAME/PASSWORD FILE=EXPDAT.DMP FROMUSER=<owner> TOUSER=<owner>
TABLES=(YEAR:OCTOBER, YEAR:NOVEMBER, YEAR:DECEMBER)</owner></owner>
```

- 파티션을 추가하는 방법

```
-- 파티션 테이블 생성
CREATE TABLE PART_TBL
(
  IN_DATE  CHAR(8)  PRIMARY KEY,
  EMPNO    NUMBER,
  ENAME    VARCHAR2(20),
  JOB      VARCHAR2(20)
)
PARTITION BY RANGE (IN_DATE)
(
  PARTITION PART_TBL_03 VALUE LESS THAN ('20000331') TABLESPACE PTS_03,
  PARTITION PART_TBL_04 VALUE LESS THAN ('20000430') TABLESPACE PTS_04,
  PARTITION PART_TBL_05 VALUE LESS THAN ('20000531') TABLESPACE PTS_05,
  PARTITION PART_TBL_06 VALUE LESS THAN ('20000630') TABLESPACE PTS_06,
  PARTITION PART_TBL_07 VALUE LESS THAN ('20000731') TABLESPACE PTS_07,
  PARTITION PART_TBL_08 VALUE LESS THAN ('20000831') TABLESPACE PTS_08,
  PARTITION PART_TBL_09 VALUE LESS THAN ('20000930') TABLESPACE PTS_09,
  PARTITION PART_TBL_10 VALUE LESS THAN ('20001031') TABLESPACE PTS_10
);
 
-- 파티션 추가
ALTER TABLE PART_TBL ADD PARTITION PART_TBL_11 VALUES LESS THAN ('20001130') TABLESPACE PTS_11;
ALTER TABLE PART_TBL ADD PARTITION PART_TBL_12 VALUES LESS THAN ('20001231') TABLESPACE PTS_12;
```

- 특정 파티션을 삭제하는 방법
특정 파티션 삭제 이후 삭제한 파티션의 값이 들어올 경우 그 다음 VALUES LESS THAN으로 편입됩니다.

```
ALTER TABLE PART_TBL DROP PARTITION PART_TBL_08;
```

- 파티션을 나누는 방법
만약 3월만 들어가있는 파티션이 있을 경우, 여기서 1, 2월을 추가하려면 파티션에 ADD가 아닌 SPLIT을 해주어야 합니다.

```
-- 3월 파티션에서 2월과 3월을 SPLIT함.
ALTER TABLE PART_TBL SPLIT PARTITION PART_TBL_03 AT ('20000229')
  INTO (PARTITION PART_TBL_02    TABLESPACE PTS_02,
        PARTITION PART_TBL_03_1  TABLESPACE PTS_03);
 
-- 2월 파티션에서 1월과 2월을 SPLIT함.
ALTER TABLE PART_TBL SPLIT PARTITION PART_TBL_02 AT ('20000131')
  INTO (PARTITION PART_TBL_01    TABLESPACE PTS_01,
        PARTITION PART_TBL_02_1  TABLESPACE PTS_02);
```

- 파티션 이름을 변경하는 방법

```
ALTER TABLE PART_TBL RENAME PARTITION PART_TBL_02_1 TO PART_TBL_02;
```

- 파티션의 테이블스페이스를 옮기는 방법

```
ALTER TABLE PART_TBL MOVE PARTITION PART_TBL_10 TABLESPACE PTS_10_1 NOLOGGING;
```

- 특정 파티션의 데이터를 Truncate하는 방법

Partition의 Data를 모두 삭제하려면 Truncate하는 방법을 사용할 수가 있는데,
Truncate는 Rollback이 불가능하며 특정 Partition 전체를 삭제하므로 주의하여 사용하여야 합니다.

```
ALTER TABLE PART_TBL TRUNCATE PARTITION PART_TBL_02;
```

- 파티션 테이블의 물리적인 속성 변경하는 방법

Partition Table은 특정 Partition의 속성만 변경할 수 있고, Table의 속성을 변경하여 전체 Partition에 대해 동일한 변경을 할 수 있습니다.

```
-- part_tbl의 모든 Partition의 Next 값이 변경
ALTER TABLE PART_TBL STORAGE (NEXT 10M);
-- part_tbl_05 Partition의 Maxextents 값만 변경
ALTER TABLE APRT_TBL MODIFY PARTITION PART_TBL_05 STORAGE (MAXEXTENTS 1000);
```

- 인덱스 관리

파티션 테이블 관련 변경작업을 한 후에는 테이블에 걸려있는 Local, Global Index에 대해 반드시 Rebuild를 해주어야 합니다.

```
-- 특정 파티션의 인덱스 Rebuild
ALTER INDEX IND_PART_TBL REBUILD PARTITION I_PART_TBL_02;
-- 글로벌 인덱스 Rebuild
ALTER INDEX PART_TBL_PK REBUILD;
```



##### Backup & Recovery

###### 1) Export

 - Table-Level Export
    기존의 Table Export처럼 Table 전체를 Export하는 경우입니다. 이는 Emp Table(Partitioned 또는 Non-Partitioned) 전체를 Export하는 경우입니다.

    $ exp scott/tiger tables=emp file=emp.dmp

 - Partition-Level Export

    이는 Partition Table의 일부 Partition만을 Export하는 것으로, Full Mode의 Export시에는 사용하지 못하고, Table단위의 Export시에만 가능합니다. ':'을 이용하여 Partition 이름을 지정하며 이 경우 Emp Table의 px Partition만을 Export합니다.

    $ exp scott/tiger tables=emp:px file=emp_par.dmp


- 다음과 같이 두가지 경우를 Level을 혼용하여 사용하는 것도 가능합니다.
    Sales Table은 전부를, Emp Table에서는 px Partition만을 Export.
     $ exp scott/tiger tables=(emp:px, sales) file=both.dmp

###### 2) Import
 - Table-Leve Import

    Partitioned 또는 Non-Partitioned Table 전체를 Import 합니다. 모든 Import Mode (full, user, table)에서 사용됩니다. emp table(Partitioned 또는 non-Partitioned) 전체를 Import 합니다.

    $ imp scott/tiger file=wookpark.dmp tables=emp

 - Partition-Level Import

    Export Dump File에서 (full, user, table 중 어떠한 Mode를 이용하여 Export했건간에)

    Partitioned Tabled의 일부 Partition만을 Import합니다.

    Table Import Mode에서만 사용 가능합니다.

    -- emp table의 px Partition만을 Import
    
    -- ':'을 이용하여 Partition을 지정
    
     $ imp scott/tiger file=wookpark.dmp tables=emp:px

테이블 단위의 Import시 우선 Table Creation 문장을 행하고 Row Insert문을 수행하는 것과 마찬가지로, Partition-level Import도 우선 Partitioned Table의 생성 문장을 수행하고 Row Insert문을 수행하게 됩니다.

따라서 ignore=y option등을 적절히 사용하면, Non-Partitioned Table과 Partitioned Table간의 Partitioned Table의 구조 변경등을 수행할 수 있게 됩니다.
