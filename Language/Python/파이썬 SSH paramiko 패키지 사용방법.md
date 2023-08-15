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

