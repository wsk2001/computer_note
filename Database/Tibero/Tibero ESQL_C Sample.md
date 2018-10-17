### Tibero 에서 ESQL 을 이용하여 DB 작업 하기.

- Source 파일 (write_clob.tbc)
```
  #include <stdio.h>
  #include <esql_lob.h>
  #include <esql_api.h>

  #define MAXBUFLEN 8192+1024

  char *conn_str = "dbuser/dbpassword";

  void Test_clob_write(void)
  {
  	ClobLocator     *clob;
  	unsigned int    clob_len, write_amt, offset;
  	unsigned char   buffer[MAXBUFLEN];
  	int                     i;
  	
  	// Tibero Server 에 접속
  	EXEC SQL CONNECT :conn_str;
  	
  	EXEC SQL WHENEVER SQLERROR
  		do printf("error: %d!!!\n", sqlca.sqlcode);
  	
  	EXEC SQL WHENEVER NOTFOUND
  		do printf("notfound!!!\n");
  	
  	while( 1 )
  	{
  		strcat( buffer, "1234567890AA" );
  		if( 1024 < strlen(buffer))
  			break;
  	}
  	
  	write_amt = strlen(buffer);
  	
  	EXEC SQL INSERT INTO CLOB_TABLE VALUES (11, empty_clob());
  	EXEC SQL ALLOCATE :clob;
  	EXEC SQL SELECT clob_col INTO :clob FROM CLOB_TABLE WHERE num_col = 11;
  	EXEC SQL LOB DESCRIBE :clob GET LENGTH INTO :clob_len;
  	
  	offset = clob_len + 1;
  	
  	EXEC SQL LOB WRITE ONE :write_amt FROM :buffer
  	WITH LENGTH :write_amt INTO :clob AT :offset;
  	EXEC SQL FREE :clob;
  	EXEC SQL COMMIT RELEASE;
  }

  void Test_clob_read(void)
  {
  	ClobLocator *clob;
  	unsigned int clob_len, read_amt;
  	unsigned char buffer[MAXBUFLEN];

  	memset(buffer, 0, sizeof(buffer));
  	
  	EXEC SQL CONNECT :conn_str;
  	EXEC SQL ALLOCATE :clob;
  	EXEC SQL SELECT clob_col INTO :clob FROM CLOB_TABLE WHERE num_col = 2;
  	EXEC SQL LOB DESCRIBE :clob GET LENGTH INTO :clob_len;
  	
  	read_amt = MAXBUFLEN-1024;
  	
  	EXEC SQL LOB READ :read_amt FROM :clob
  	INTO :buffer WITH LENGTH :read_amt;
  	
  	printf("(%d) %s\n", strlen(buffer), buffer );
  	
  	EXEC SQL FREE :clob;
  	
  	EXEC SQL COMMIT RELEASE;
  }

  int main(int argc, char** argv)
  {
  	Test_clob_write();

  	//Test_clob_read();

  }
```

### Makefile 파일
```
	CC              = gcc
	LFLAGS          = -L/tibero/tibero6/client/lib -ltbertl -ltbcli -lpthread -lm
	CFLAGS          = -I. \
					-I/tibero/tibero6/client/include
	
	.tbc.c:
			tbpc $*.tbc
	
	.c.o:
			$(CC) $(CFLAGS) -c $*.c $(CINC)
	
	.cpp.o:
			$(CC) $(CFLAGS) -c $*.cpp $(CINC)
	
	.SUFFIXES: .o .c .cpp .tbc
	
	OBJECTS         =  write_clob.o
	
	all:     $(OBJECTS) write_clob
	
	write_clob:
			$(CC) $(CFLAGS) $(LFLAGS) -o $@ $(OBJECTS)
	
	test:
			write_clob
		
	clean:
			rm -f $(OBJECTS)
			rm -f write_clob    
```


> 컴파일 오류시 $TB_HOME/client/config/tbpc.cfg 에 header 파일 경로를 추가 한다. 