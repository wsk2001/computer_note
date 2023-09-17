# Java에서 MariaDB 테이블의 TIMESTAMP 컬럼에 문자열로 된 날짜 데이터를 삽입하는 방법

먼저, 문자열로 된 날짜를 java.sql.Timestamp 객체로 변환해야 합니다. 이를 위해 java.text.SimpleDateFormat 클래스를 사용할 수 있습니다.

``` java
String strDate = "2023-09-12 07:03:31";
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Date parsedDate = dateFormat.parse(strDate);
Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

```

그런 다음, PreparedStatement를 사용하여 SQL 쿼리를 작성하고, 위에서 생성한 Timestamp 객체를 쿼리에 삽입합니다.

``` java
String query = "INSERT INTO your_table (your_timestamp_column) VALUES (?)";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setTimestamp(1, timestamp);
pstmt.executeUpdate();
```

위의 코드에서 `your_table`은 테이블 이름을, `your_timestamp_column`은 TIMESTAMP 컬럼 이름을 나타냅니다. 또한, `conn`은 유효한 java.sql.Connection 객체입니다.

이렇게 하면, 문자열로 된 날짜 데이터가 MariaDB 테이블의 TIMESTAMP 컬럼에 삽입됩니다. 이 코드는 예외 처리가 포함되어 있지 않으므로 실제 사용 시에는 적절한 예외 처리를 추가해야 합니다.

참고로, MariaDB의 TIMESTAMP 타입은 ‘1970-01-01 00:00:01’ UTC부터 ‘2038-01-19 03:14:07’ UTC까지의 범위만을 저장할 수 있으므로, 이 범위를 벗어나는 날짜는 사용할 수 없습니다. 이 점을 유의하시기 바랍니다.