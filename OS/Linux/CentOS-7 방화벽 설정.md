
### CentOS-7 방화벽 설정

---

CentOS-7 에서는 방화벽 설정 하는 방법이 바뀌었다.



##### 방화벽 설정

	firewall-cmd --zone=public --add-port=1521/tcp --permanent



##### 방화벽을 리로드

	firewall-cmd --reload

