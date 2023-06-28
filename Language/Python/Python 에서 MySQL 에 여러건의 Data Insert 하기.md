# Python 에서 MySQL 에 여러건의 Data Insert 하기

``` py
import csv
import MySQLdb

db = MySQLdb.connect(   host = "dbname.description.host.com",
                        user = "user",
                        passwd = "key",
                        db = "dbname")
cursor = db.cursor()

query = 'INSERT INTO table_name(column,column_1,column_2,column_3)
VALUES(%s, %s, %s, %s)'                                                         

csv_data = csv.reader(file('file_name'))

my_data = []
for row in csv_data:
     my_data.append(tuple(row))

cursor.executemany(query, my_data)
cursor.close()
```

출처: https://stackoverflow.com/questions/46543436/speeding-bulk-insert-into-mysql-with-python

