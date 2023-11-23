# 파이썬 SSH paramiko 패키지 사용방법

출처: https://bono915.tistory.com/entry/Python-%ED%8C%8C%EC%9D%B4%EC%8D%AC-SSH-paramiko-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95

오늘은 python의 SSH 사용이 가능한 패키지인 paramiko에 대해 포스팅 하려고 합니다.


먼저 SSH는 Secure Shell의 약자로 다른 Client 또는 Server에 Secure한 방식으로 연결하는
터미널 기능입니다. 일반적으로 기본 포트는 22를 사용하고 있습니다.

이어서 python의 paramiko 패키지를 이용하여 SSH연결을 하는 소스코딩과 테스트를 해보도록 하겠습니다.
python_paramiko.py라는 python 파일을 만들고 아래 소스를 입력하여 저장합니다. 

``` py
#python_paramiko.py

import paramiko

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect('[Host_IP]', port='22', username='[Host_ID]', password='[Host_Password]')

stdin, stdout, stderr = ssh.exec_command('df -h')
print(''.join(stdout.readlines()))

ssh.close()
```

 

저는 Linux Server에 SSH 접속을 하여 'df -h' 명령어를 입력하고

해당 결과값을 출력하도록 구성하였습니다. 

아래 결과를 통해 연결 및 결과에 대한 출력이 잘 된 것을 확인 할 수 있습니다.

``` bash
Filesystem           Size  Used Avail Use% Mounted on
/dev/mapper/cl-root   35G   18G   18G  50% /
devtmpfs             3.9G     0  3.9G   0% /dev
tmpfs                3.9G     0  3.9G   0% /dev/shm
tmpfs                3.9G  385M  3.5G  10% /run
tmpfs                3.9G     0  3.9G   0% /sys/fs/cgroup
/dev/sda1           1014M  277M  738M  28% /boot
tmpfs                783M     0  783M   0% /run/user/993
tmpfs                783M     0  783M   0% /run/user/1000
```

지금까지 Python SSH paramiko 패키지 사용방법에 대한 포스팅이였습니다.





---

# 파이썬 paramiko 라이브러리 사용법 및 예제

출처: https://gr-st-dev.tistory.com/1021

## 소개

Paramiko는 파이썬을 사용하여 SSH (보안 쉘) 클라이언트 및 서버를 작성할 수 있는 모듈입니다. 이 라이브러리를 사용하면 파이썬을 통해 SSH 프로토콜을 사용하여 원격 시스템에 로그인하고 명령을 실행할 수 있습니다.

## 설치

Paramiko를 설치하려면 `pip`를 사용합니다.

``` bash
pip install paramiko
```

## 사용법

1. 모듈 가져오기

   ``` py
   import paramiko
   ```

   

2. SSH 클라이언트 생성하기

   ``` py
   ssh = paramiko.SSHClient()
   ```

   

3. 호스트 키 확인 및 저장하기 (옵션)

   ``` py
   ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
   ```

   

4. SSH 서버에 연결하기

   ``` py
   ssh.connect('호스트', port=22, username='사용자명', password='비밀번호')
   ```

   

5. 명령 실행하기

   ``` py
   stdin, stdout, stderr = ssh.exec_command('ls -al')
   ```

   

6. 결과 확인하기

   ``` sh
   print(stdout.read().decode())
   ```

   

7. 연결 종료하기

   ``` py
   ssh.close()
   ```

   

## 예제

다음은 SSH를 통해 원격 서버에 접속하여 파일 목록을 출력하는 간단한 예제입니다.

``` py
import paramiko

# SSH 클라이언트 생성
ssh = paramiko.SSHClient()

try:
    # 호스트 키 확인 및 저장
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    # SSH 서버에 연결
    ssh.connect('example.com', port=22, username='user', password='password')

    # 명령 실행
    stdin, stdout, stderr = ssh.exec_command('ls -al')

    # 결과 출력
    print(stdout.read().decode())

finally:
    # 연결 종료
    ssh.close()
```



## 장단점

### 장점

- 파이썬에서 SSH를 통해 원격 시스템에 접근할 수 있습니다.
- 간단하고 직관적인 API를 제공하여 사용하기 쉽습니다.
- 다양한 인증 방식을 지원합니다.

### 단점

- 프로토콜이 SSH에 한정되므로 SSH를 지원하지 않는 시스템에는 사용할 수 없습니다.
- Paramiko는 멀티스레딩에 대한 지원이 제한적이므로 별도의 처리가 필요할 수 있습니다.
- 속도 면에서 다른 라이브러리보다 느릴 수 있습니다.

## 추가 정보

Paramiko는 SSH 프로토콜뿐만 아니라 SFTP (SSH 파일 전송 프로토콜)를 지원하기도 합니다. SFTP를 사용하면 파일을 업로드 및 다운로드할 수 있습니다. Paramiko의 자세한 사용법과 기능에 대해서는 공식 문서를 참조하시기 바랍니다.

- Paramiko 공식 문서: https://www.paramiko.org/



