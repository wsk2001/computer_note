
### Tibero FileToClob 및 ClobToFile  UDF 만들기

----

이 문서는 Tibero CLI API 를 이용하여 UDF 를 만드는 것을 기준으로 작성 되었다.

Tibero 에서 CLOB Column 에 한글을 저장 하고 length() 함수에 의해 길이를 확인 하면 한글의 length  를 1로 계산 한다.

그리고 1024 Bytes 이상의 한글이 포함된 Data 를 CLOB Column 에서 추출 하면 한번에 Data를 다 가져 오지 못하고 여러번에 나누어 Data 를 취득 하여야 한다.

이 문서는 Linux-C 에서 작업 을 수행 하는 것을 기준으로 작성 되었다.

---

 소스 코드 (tbclob.c)

	#include <stdio.h>
	#include <string.h>
	#include <stdlib.h>
	
	#include <pthread.h>
	#include <errno.h>
	#include <dlfcn.h>
	#include <sys/types.h>
	#include <sys/stat.h>
	#include <fcntl.h>
	#include <stdarg.h>
	#include <unistd.h>
	
	#include "extproc.h"
	#include "sqlcli_odbc.h"
	#include "sqlcli_ext.h"
	#include "sqlcli.h"
	
	SQLRETURN 		_rc;
	SQLHENV   		henv;
	SQLHDBC   		hdbc;
	SQLSMALLINT 	errHdlType;
	SQLHANDLE   	errHdl;
	
	pthread_mutex_t _tLock_API;
	int bCheckConn  = 0;
	
	void EnterLock()
	{
		pthread_mutex_lock(&_tLock_API);
	}
	
	void LeaveLock()
	{
		pthread_mutex_unlock(&_tLock_API);
	}
	
	int StartExtProcConn( ExtProcContext*  pContext, 
						SQLHENV*		pHenv, 
						SQLHDBC*		pHdbc, 
						SQLSMALLINT*	pErrHdlType, 
						SQLHANDLE*		pErrhle)
	{
		EnterLock();
		
		_rc    = SQLExtProcGetConnect( pContext, pHenv, pHdbc, pErrHdlType, pErrhle);
		
		if(_rc)
		{
			LeaveLock();
			return _rc;
		}
		
		bCheckConn  =1;
		
		LeaveLock();
			
		return 0;
	}
	
	int FileToClob( ExtProcContext*	Context, 
				   char* 		  fileName, 
				   SQLPOINTER 	   outClob )
	{
		SQLCHAR 		*pData  		= NULL;
		int 			offset      	= 1;
		unsigned int	sz				= 0;
		SQLINTEGER 		out_char_len 	= 0;
		SQLINTEGER 		in_char_len 	= 0;
		
		long 			rs            	= -1L;
		FILE			*fp				= (FILE*) NULL;
		
		if( !bCheckConn )
		{
			rs  = StartExtProcConn( Context, 
									&henv, 
									&hdbc, 
									&errHdlType, 
									&errHdl );
			if( rs ) 
				return rs;
		}
	 
		fp = fopen( fileName, "rt" );
		
		if( fp == (FILE*) NULL )
		{
			printf( "Can not open: %s\n", fileName );
			return -1;
		}
	 
		fseek(fp, 0L, SEEK_END);
		sz = ftell(fp);
		fseek(fp, 0L, SEEK_SET);
	
		pData = (char*)SQLExtProcAllocMemory(Context, sz + 1 );
		memset(pData, 0x00, sz + 1 );
		
		fread( pData, 1, sz, fp );
		fclose( fp );
		
		offset 	= 1;
		in_char_len = sz;
	
		_rc = SQLExtProcLobPutData( Context, 
									outClob, 
									offset, 
									SQL_C_CHAR,
									pData, 
									&in_char_len, 
									&out_char_len,
									&errHdlType, 
									&errHdl);
		
		return 0;
	}
	
	int ClobToFile( ExtProcContext*	Context,
				   SQLPOINTER 	   inClob, 
				   char* 		   fileName)
	{
		SQLCHAR*	pPlain       	= NULL;
		
		SQLLEN     	in_lob_ind    	= 0;
		SQLBIGINT   lob_len     	= 0;
		SQLINTEGER	read_byte_len  	= 1024;    
		SQLINTEGER 	out_byte_len  	= 0;
		
		int 		offset        	= 1; 
		long 		rs            	= -1;
		int			nResult			= 0;
		FILE 		*fp 			= (FILE*) NULL;
	
		if(bCheckConn ==0)
		{
			rs  = StartExtProcConn(Context, &henv, &hdbc, &errHdlType, &errHdl);
			if( rs ) return rs;
		}
		
		_rc = SQLExtProcLobGetLength(Context, inClob, &lob_len, &errHdlType, &errHdl);
		if(_rc ) 
			return _rc;
	
		// 한글인 경우를 고려하여 취득된 Data 의 2 배로 만든다.
		lob_len = lob_len << 1;
		
		out_byte_len = lob_len;
		
		pPlain = (char*)SQLExtProcAllocMemory(Context, lob_len + 1);
		
		memset(pPlain,  0, lob_len + 1);
		
		for( ;; )
		{
			_rc = SQLExtProcLobGetData(	Context, 
										inClob, 
										offset, 
										&read_byte_len, 
										SQL_C_BINARY,
										pPlain + strlen(pPlain),
										out_byte_len,
										&in_lob_ind, 
										&errHdlType, 
										&errHdl );
						
			if( _rc != 0 || in_lob_ind <= 0 || read_byte_len <= 0)
			{
				break;
			}
	
			offset += read_byte_len;
		}	
	
		fp = fopen( fileName, "wt" );
		
		if( fp == (FILE*) NULL )
		{
			printf( "Can not create file: %s\n", fileName );
		}
		else
		{
			fprintf( fp, "%s", pPlain );
			fclose( fp );
		}
		nResult = strlen( pPlain );
		
		return nResult;
	}

---

Makefile

	TARGET		= libtbclob.so
	BITS 		= -m64
	CC 			= gcc
	CFLAGS 		= -fPIC -shared -nostdlib
	OBJS 		= tbcplug.o
	INCLUDES 	= -I. -I/tibero/tibero6/client/include
	LFLAGS		= -L. -L/tibero/tibero6/client/lib -ltbcli -lpthread -lm
	
	.c.o:
		${CC} ${INCLUDES} ${BITS} ${CFLAGS} -c $<
	
	all: ${OBJS} ${TARGET}
	
	${TARGET}: ${OBJS}
		${CC} $(LFLAGS) $(CFLAGS) ${OBJS} ${BITS}  -o $@
	
	dist:
		cp ${TARGET} /tibero/plugin
		
	clean:
		rm -f *.o
		rm -f libxdbtbplug.a libxdbtbplug.so $(OBJS)
---

생성된 .so 파일(plugin)을 tibero 에 설치하는  sql (install.sql) 

	DROP FUNCTION iFileToClob;
	DROP FUNCTION iClobToFile;
	
	CREATE or REPLACE LIBRARY tbclob
	IS '/tibero/plugin/libtbclob.so';
	/
	
	CREATE OR REPLACE FUNCTION iFileToClob(fileName string, pData IN OUT CLOB) RETURN BINARY_INTEGER
	AS
	LANGUAGE C
	LIBRARY tbclob
	NAME "FileToClob"
	WITH CONTEXT
	PARAMETERS(CONTEXT, fileName string , pData);
	/
	
	CREATE OR REPLACE FUNCTION iClobToFile(pData IN CLOB, fileName string) RETURN BINARY_INTEGER
	AS
	LANGUAGE C
	LIBRARY tbclob
	NAME "ClobToFile"
	WITH CONTEXT
	PARAMETERS(CONTEXT, pData, fileName string);
	/
	
	GRANT EXECUTE ON iFileToClob TO PUBLIC;
	GRANT EXECUTE ON iClobToFile TO PUBLIC;
	
	CREATE OR REPLACE PACKAGE HANCOMPKG
	IS
		FUNCTION FileToClob(sFileName IN VARCHAR2) RETURN CLOB DETERMINISTIC PARALLEL_ENABLE;
		FUNCTION ClobToFile(sData IN CLOB, sFileName IN VARCHAR2) RETURN NUMBER DETERMINISTIC PARALLEL_ENABLE;
		END HANCOMPKG;
	/
	
	CREATE OR REPLACE PACKAGE BODY HANCOMPKG
	IS
		FUNCTION FileToClob(sFileName IN VARCHAR2) RETURN CLOB DETERMINISTIC PARALLEL_ENABLE
		IS
			pOutput CLOB ;
		BEGIN
			DBMS_LOB.CREATETEMPORARY(pOutput, FALSE);
			IF iFileToClob(sFileName, pOutput) <> 0 THEN RETURN NULL; END IF;
		  
			IF pOutput IS NULL OR DBMS_LOB.GETLENGTH(pOutput) <= 0 THEN RETURN NULL; END IF;
		  
			RETURN pOutput;
		  
			EXCEPTION WHEN OTHERS THEN RETURN NULL;
		END FileToClob;
	
		FUNCTION ClobToFile(sData IN CLOB, sFileName IN VARCHAR2) RETURN NUMBER DETERMINISTIC PARALLEL_ENABLE
		IS
			nRes NUMBER;
		BEGIN
			nRes := iClobToFile(sData, sFileName );
					  
			RETURN nRes;
		  
			EXCEPTION WHEN OTHERS THEN RETURN nRes;
		END ClobToFile;
	
	END HANCOMPKG;
	/
	
	CREATE OR REPLACE PUBLIC SYNONYM HANCOMPKG FOR HANCOMPKG;
	GRANT EXECUTE ON HANCOMPKG TO PUBLIC;
	
	quit;
---

install.sql 을 실행 하는 shell (install.sh)

	tbsql sys/tibero @'install.sql'
	* windows format 으로 저장 하면 \r\f 가 추가 되어
	* sql 실행시 오류가 발생 할 수 있음. 오류 발생시 unix format 으로 변경 필요.
---

UDF Test 용 SQL

	CREATE TABLE CLOB_T (
		NO NUMBER,
		FileCLOB CLOB
	);
	
	INSERT INTO CLOB_T VALUES(1, empty_clob());
	
	commit;
	
	UPDATE CLOB_T
	SET    FileCLOB = empty_clob()
	WHERE  NO = 1;
	
	commit;
	
	UPDATE CLOB_T
	SET    FileCLOB = HANCOMPKG.FileToClob('/tibero/tibero_clob/read_file.txt')
	WHERE  NO = 1;
	
	commit;
	
	SELECT 	HANCOMPKG.ClobToFile(FileCLOB, '/tibero/tibero_clob/save_file.txt')
	FROM	CLOB_T
	WHERE    NO = 1;
---

기타

Tibero 는 Oracle 처럼 내부적으로 관리하는 rowid 가 있어 Data migration 시 PK 대신 rowid 를 사용 하면 된다.



---



## End

