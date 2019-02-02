Pro* C 원격 접속 옵션

EXEC SQL BEGIN DECLARE SECTION;
 char    username[32];
 char    password[32];
 char    sid[ 256 ];
EXEC SQL END DECLARE SECTION;

int     nRes = 0;

strcpy(username, "userid");
strcpy(password, "userpwd");

//strcpy(sid, "ip:port/sid");
strcpy(sid, "127.0.0.1:1521/ORCL");

EXEC SQL CONNECT :username IDENTIFIED BY :password USING :sid;
