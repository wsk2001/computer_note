# MSSQL Service 시작 계정 변경

클러스터 환경에서 SQL Server, SQL Server 에이전트 서비스의 시작 계정에 대해 변경하는 방법을 포스팅 합니다.

**[환경]**

도메인 : JKMOON.COM

Windows Server 2008R2 EE

MSSQL Server 2008R2 / MSCS구성



1. 도메인, sqladmin 계정을 생성 > administrators 그룹에 포함시킵니다.

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\127C12374FD89E4637.png)

2. SSMS접속하여, 추가한 계정에 대해, 신규 로그인 계정을 생성합니다. "sysadmin 권한"

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\11560D354FD89E4735.png)

3. Node1,2번 로컬 administrators 그룹에 새로 생성한 sqladmin 계정을 추가.

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\130828334FD89E4804.png)

4. SQL Server(MSSQLSERVER), SQL Server 에이전트 서비스 로그온 계정을 변경합니다.

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\207F55364FD89E4924.png)

5. node2로 정상적으로 SQL 서비스가 실행 되는지 take-over 후 확인 합니다.

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\203C03394FD89E4A09.png)

6. fail-over 테스트 정상 "확인"

sqladmin계정으로 로그인 > SSMS 실행하여 DB 접속도 확인 합니다.

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\193F6A3F4FD89E4B1D.png)

![](C:\home\Documents\computer_note\Database\MS-SQL\Images\1572B23D4FD89E4C16.png)

7. 정상적으로 온라인 되었다면, 서비스가 정상적인지 확인 후 node1 동일하게 서비스 계정을 변경합니다.

   

[참조사이트]

SQL Server의 서비스 시작 계정 변경(SQL Server 구성 관리자)

<http://msdn.microsoft.com/ko-kr/library/ms345578.aspx> 