# CentOS 에 SSH 설치하기

출처: https://itdev4u.tistory.com/entry/CentOS-SSH-%EC%84%A4%EC%B9%98



**SSH 설치**

**1. ssh 설치 확인**

\# **yum list installed ssh** 명령으로 ssh 설치 확인

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\9901D0505AA157A627.png)

 

**2. ssh 설치**

\# **yum -y install openssh-server openssh-clients openssh-askpass** 명령으로 설치

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\9946F2415AA158F536.png)

[다운로드 중]

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\99BBCE415AA158F507.png)

[설치완료]

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\994224415AA158F50F.png)

[설치 확인]

 

**3. network 재시작**

\# **systemctl restart network** 명령으로 network 재시작

 

**4. ssh 설정파일 수정(/etc/ssh/sshd_config 파일)**

**#Port 22 ==> Port 22** : 이 부분 주석 해제

 

**5. ssh 서비스 구동**

\# **systemctl start sshd.service**

 

**6. 방화벽 해당 Port 열기**

\# **firewall-cmd --zone=public --add-port=22/tcp --permanent**

\# **firewall-cmd --reload**

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\994968365AA15BA323.png)

 

**7. putty 클라이언트로 접속 확인**

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\99CBEA375AA15CBE17.png)

 

![img](C:\GitHub\computer_note\Tech\교육자료\Images\SSH-Install\99367D375AA15CBE21.png)





 

 