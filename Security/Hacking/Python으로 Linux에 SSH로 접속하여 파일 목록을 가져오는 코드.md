### Python으로 Linux에 SSH로 접속하여 파일 목록을 가져오는 코드

``` python
import paramiko

# SSH 접속 정보
host = "서버 주소"
port = 22
username = "사용자 이름"
password = "비밀번호"

# SSH 클라이언트 객체 생성
ssh = paramiko.SSHClient()

# SSH 서버에 접속
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(host, port, username, password)

# SFTP 클라이언트 객체 생성
sftp = ssh.open_sftp()

# 원격 디렉토리 파일 목록 가져오기
remote_path = "/경로/파일"
files = sftp.listdir(remote_path)

# 파일 목록 출력
for file in files:
    print(file)

# SSH 연결 종료
ssh.close()

```



>참고
>
>- 위 코드는 기본적인 예시이며, 필요에 따라 수정해야 할 수도 있습니다.
>- `paramiko` 라이브러리는 Python에서 SSH를 사용하기 위한 라이브러리입니다. 설치가 필요하다면 `pip install paramiko` 명령어를 사용하여 설치하십시오.
>- `sftp.listdir()` 메서드는 원격 디렉토리의 파일 목록을 리스트 형태로 반환합니다.
>- 원격 파일 경로 및 접속 정보는 사용자 환경에 맞게 변경해야 합니다.
>- paramiko 공식 문서: https://paramiko.org/
