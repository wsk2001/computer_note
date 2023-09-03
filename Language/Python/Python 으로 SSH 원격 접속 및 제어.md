# Python 으로 SSH 원격 접속 및 제어

출처: https://velog.io/@engineer_km/paramiko



Python 으로 SSH 원격(텔넷 서버)으로 접속하여 제어 하고 싶다면
paramiko 사용 방법을 간략하게 소개해보려고 한다.

### 공식 문서

- https://www.paramiko.org/

### paramiko github

- https://github.com/paramiko/paramiko

### 설치 방법

```null
pip install paramiko
```

### 1) 예제 코드 전체

``` py

>>> import paramiko

>>> HOST = '127.0.0.1'
>>> ID = 'ID'
>>> PASSWD = 'PWD'
>>>


>>> ssh = paramiko.SSHClient()
>>> ssh
<paramiko.client.SSHClient object at 0x7f017ca69b50>
>>>
>>> ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
>>>
>>> ssh.connect(HOST, username=ID, password=PASSWD)
>>>



>>> ssh_stdin, ssh_stdout, ssh_stderr = ssh.exec_command('df -h')


>>> msg = ssh_stdout.read().decode("euc-kr")
>>> print(msg)
Filesystem      Size  Used Avail Use% Mounted on
udev            1.9G     0  1.9G   0% /dev
tmpfs           392M   21M  371M   6% /run
/dev/sda1        39G   32G  4.4G  89% /
tmpfs           2.0G  188K  2.0G   1% /dev/shm
tmpfs           5.0M  4.0K  5.0M   1% /run/lock
tmpfs           2.0G     0  2.0G   0% /sys/fs/cgroup
/dev/loop12     163M  163M     0 100% /snap/gnome-3-28-1804/145
/dev/loop4      253M  253M     0 100% /snap/pynsource/20
/dev/loop1       66M   66M     0 100% /snap/gtk-common-themes/1515
/dev/loop3       56M   56M     0 100% /snap/core18/2246
/dev/loop5       44M   44M     0 100% /snap/snapd/14295
/dev/loop6       43M   43M     0 100% /snap/snapd/14066
/dev/loop8      128K  128K     0 100% /snap/bare/5
/dev/loop9      165M  165M     0 100% /snap/gnome-3-28-1804/161
/dev/loop7      100M  100M     0 100% /snap/core/11798
/dev/loop2       66M   66M     0 100% /snap/gtk-common-themes/1519
/dev/loop10     252M  252M     0 100% /snap/pynsource/19
/dev/loop11      56M   56M     0 100% /snap/core18/2253
/dev/loop0      100M  100M     0 100% /snap/core/11993
tmpfs           392M   24K  392M   1% /run/user/108
tmpfs           392M     0  392M   0% /run/user/0
overlay          39G   32G  4.4G  89% /var/lib/docker/overlay2/a699597c9aa33bc4924c9bc82f8decc538ffe3fe5e3231b512d768bc5ba63b19/merged
shm              64M     0   64M   0% /var/lib/docker/containers/7c001393a1153608c1cae1a0b2f2466a02934d1051faec389a3ce4393e1e3e24/shm

>>> err_msg = ssh_stderr.read().decode("euc-kr")
>>> err_msg
''
>>>



>>> ssh_stdin, ssh_stdout, ssh_stderr = ssh.exec_command('invalid cmd')
>>> msg = ssh_stderr.read().decode('utf-8')
>>> msg
'bash: invalid: 명령어를 찾을 수 없음\n'
>>>



>>> ssh.get_transport()
<paramiko.Transport at 0xa9839c50 (cipher aes128-ctr, 128 bits) (active; 0 open channel(s))>
>>>

>>> ssh.close()
>>>
>>>
>>> ssh.get_transport()
>>>
```



#### 1-1) SSH 세션 접속

- paramiko.SSHClient :
  - SSH 서버(원격)으로 접속하기 위한 클래스
  - 서버로 접속하기 때문에 현재 LOCAL PC는 Client 가 됨.
    그래서 Class 명이 Client 임.
  - 쓰기 쉽도록 상위 레벨로 wraps 한 클래스 (`.Transport`, `.Channel`, `.SFTPClient`)
- set_missing_host_key_policy() : ssh 세션에 key 규칙을 paramiko.AutoAddPolicy() 로 함.
  - paramiko.AutoAddPolicy() 는 호스트 이름과 새 호스트 키를 로컬 `.HostKeys` 개체에 자동으로 추가하고 저장하는 정책
- ssh.connect() : SSH 서버(원격)으로 접속

```python
>>> ssh = paramiko.SSHClient()
>>> ssh
<paramiko.client.SSHClient object at 0x7f017ca69b50>
>>>
>>> ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
>>>
>>> ssh.connect(HOST, username=ID, password=PASSWD)
>>>
```

#### 1-2) SSH 서버(원격)으로 입력

- exec_command() : 원격서버에서 명령을 입력
  - ssh_stdin : 입력한 명령에 대한 데이터
    - stdin : write-only
  - ssh_stdout : 입력한 명령에 대한 출력이 잘못되었을 경우의 데이터
    - stdin : read-only
  - ssh_stderr : 입력한 명령에 대한 올바르게 출력되었다고 판단한 데이터
    - stdin : read-only

```python
>>> ssh_stdin, ssh_stdout, ssh_stderr = ssh.exec_command('df -h')
```

#### 1-3) SSH 서버(원격)로 부터 데이터 읽기

```python
>>> msg = ssh_stdout.read().decode("euc-kr")
>>> print(msg)
Filesystem      Size  Used Avail Use% Mounted on
udev            1.9G     0  1.9G   0% /dev
tmpfs           392M   21M  371M   6% /run
/dev/sda1        39G   32G  4.4G  89% /
tmpfs           2.0G  188K  2.0G   1% /dev/shm
tmpfs           5.0M  4.0K  5.0M   1% /run/lock
tmpfs           2.0G     0  2.0G   0% /sys/fs/cgroup
/dev/loop12     163M  163M     0 100% /snap/gnome-3-28-1804/145
/dev/loop4      253M  253M     0 100% /snap/pynsource/20
/dev/loop1       66M   66M     0 100% /snap/gtk-common-themes/1515
/dev/loop3       56M   56M     0 100% /snap/core18/2246
/dev/loop5       44M   44M     0 100% /snap/snapd/14295
/dev/loop6       43M   43M     0 100% /snap/snapd/14066
/dev/loop8      128K  128K     0 100% /snap/bare/5
/dev/loop9      165M  165M     0 100% /snap/gnome-3-28-1804/161
/dev/loop7      100M  100M     0 100% /snap/core/11798
/dev/loop2       66M   66M     0 100% /snap/gtk-common-themes/1519
/dev/loop10     252M  252M     0 100% /snap/pynsource/19
/dev/loop11      56M   56M     0 100% /snap/core18/2253
/dev/loop0      100M  100M     0 100% /snap/core/11993
tmpfs           392M   24K  392M   1% /run/user/108
tmpfs           392M     0  392M   0% /run/user/0
overlay          39G   32G  4.4G  89% /var/lib/docker/overlay2/a699597c9aa33bc4924c9bc82f8decc538ffe3fe5e3231b512d768bc5ba63b19/merged
shm              64M     0   64M   0% /var/lib/docker/containers/7c001393a1153608c1cae1a0b2f2466a02934d1051faec389a3ce4393e1e3e24/shm

>>> err_msg = ssh_stderr.read().decode("euc-kr")
>>> err_msg
''
>>>





>>> ssh_stdin, ssh_stdout, ssh_stderr = ssh.exec_command('invalid cmd')
>>> msg = ssh_stderr.read().decode('utf-8')
>>> msg
'bash: invalid: 명령어를 찾을 수 없음\n'
>>>
```

#### 1-4) SSH 세션 살았는지 확인

- .get_transport() :
  - 세션이 살아있다면 paramiko.Transport 를 반환
  - 세션 연결이 안되었다면(끊겼다면) None을 반환

```python
>>> ssh.get_transport()
<paramiko.Transport at 0xa9839c50 (cipher aes128-ctr, 128 bits) (active; 0 open channel(s))>
>>>

>>> ssh.close()
>>>
>>>
>>> ssh.get_transport()
>>>
```

#### 1-5) SSH 세션 끊기

```python
>>> ssh.close()
>>>
```

### 2) 예제 코드 전체 (Raw 한 API 로 사용해보기)

- high level 로 접근하여 사용해도 되지만
  `굳이` 한번 Raw 하게 접근해보고 싶었다.
- 이 방법은 Linux, 여러 네트워크 장비로 접속이 가능했다.
- WINDOWS 환경에서의 SSH 서버로의 접근할 경우 접근이 안되었다.
  - 이유는? 기본적으로 윈도우는 vt100 터미널을 지원하지 않음. 지원하게 하려면 설치가 필요함.
    - https://stackoverflow.com/questions/64474568/how-to-enable-vt100-terminal-emulation-in-windows-10

```python
>>> ssh = paramiko.SSHClient()
>>> ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
>>> ssh.connect(HOST, username=ID, password=PASSWD)
>>>



>>> session = ssh.invoke_shell()
>>> session.settimeout(0.0)



>>> 
>>> session.sendall('df -h'.encode('ascii') + b'\n')
>>> session.recv(50000)
b'df -h\r\nFilesystem      Size  Used Avail Use% Mounted on\r\nudev            1.9G     0  1.9G   0% /dev\r\ntmpfs           392M   21M  371M   6% /run\r\n/dev/sda1        39G   32G  4.4G  89% /\r\ntmpfs           2.0G  188K  2.0G   1% /dev/shm\r\ntmpfs           5.0M  4.0K  5.0M   1% /run/lock\r\ntmpfs           2.0G     0  2.0G   0% /sys/fs/cgroup\r\n/dev/loop12     163M  163M     0 100% /snap/gnome-3-28-1804/145\r\n/dev/loop4      253M  253M     0 100% /snap/pynsource/20\r\n/dev/loop1       66M   66M     0 100% /snap/gtk-common-themes/1515\r\n/dev/loop3       56M   56M     0 100% /snap/core18/2246\r\n/dev/loop5       44M   44M     0 100% /snap/snapd/14295\r\n/dev/loop6       43M   43M     0 100% /snap/snapd/14066\r\n/dev/loop8      128K  128K     0 100% /snap/bare/5\r\n/dev/loop9      165M  165M     0 100% /snap/gnome-3-28-1804/161\r\n/dev/loop7      100M  100M     0 100% /snap/core/11798\r\n/dev/loop2       66M   66M     0 100% /snap/gtk-common-themes/1519\r\n/dev/loop10     252M  252M     0 100% /snap/pynsource/19\r\n/dev/loop11      56M   56M     0 100% /snap/core18/2253\r\n/dev/loop0      100M  100M     0 100% /snap/core/11993\r\ntmpfs           392M   24K  392M   1% /run/user/108\r\ntmpfs           392M     0  392M   0% /run/user/0\r\noverlay          39G   32G  4.4G  89% /var/lib/docker/overlay2/a699597c9aa33bc4924c9bc82f8decc538ffe3fe5e3231b512d768bc5ba63b19/merged\r\nshm              64M     0   64M   0% /var/lib/docker/containers/7c001393a1153608c1cae1a0b2f2466a02934d1051faec389a3ce4393e1e3e24/shm\r\nroot@ytest-VirtualBox:~# '
>>> session.recv(50000)
Traceback (most recent call last):
  File "/usr/local/lib/python3.7/dist-packages/paramiko/channel.py", line 699, in recv
    out = self.in_buffer.read(nbytes, self.timeout)
  File "/usr/local/lib/python3.7/dist-packages/paramiko/buffered_pipe.py", line 155, in read
    raise PipeTimeout()
paramiko.buffered_pipe.PipeTimeout

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/usr/local/lib/python3.7/dist-packages/paramiko/channel.py", line 701, in recv
    raise socket.timeout()
socket.timeout
>>>
```

#### 2-1) invoke_shell

- 터미널을 설정합니다.
  - term : 터미널의 종류 을 설정
    기본 vt100
  - width: 터미널 가로 길이 설정
  - height: 터미널 세로 길이 설정
  - width_pixels : 터미널 가로 픽셀 설정
  - height_pixels : 터미널 세로 픽셀 설정

```paramiko
        Start an interactive shell session on the SSH server.  A new `.Channel`
        is opened and connected to a pseudo-terminal using the requested
        terminal type and size.

        :param str term:
            the terminal type to emulate (for example, ``"vt100"``)
        :param int width: the width (in characters) of the terminal window
        :param int height: the height (in characters) of the terminal window
        :param int width_pixels: the width (in pixels) of the terminal window
        :param int height_pixels: the height (in pixels) of the terminal window
        :param dict environment: the command's environment
        :return: a new `.Channel` connected to the remote shell
>>> session = ssh.invoke_shell()
```

#### 2-2) 세션 Timeout 설정

- .settimeout(0.0) : 세션 창이 끊길때까지 설정

```python
>>> session.settimeout(0.0)
```

#### 2-3) SSH 서버(원격)으로 입력

- RAW 한 API 인`.sendall` 를 사용

```python
>>> session.sendall('df -h'.encode('ascii') + b'\n')
```

#### 2-4) SSH 서버(원격)으로 데이터 읽기

- RAW 한 API 인`.recv` 를 사용
- .recv 의 인자값은 데이터를 읽어올 사이즈 입니다.
- 서버로 부터 현재 읽어올 데이터가 없었다면 socket.timeout 에러 발생

```python
>>> session.recv(50000)
b'df -h\r\nFilesystem      Size  Used Avail Use% Mounted on\r\nudev            1.9G     0  1.9G   0% /dev\r\ntmpfs           392M   21M  371M   6% /run\r\n/dev/sda1        39G   32G  4.4G  89% /\r\ntmpfs           2.0G  188K  2.0G   1% /dev/shm\r\ntmpfs           5.0M  4.0K  5.0M   1% /run/lock\r\ntmpfs           2.0G     0  2.0G   0% /sys/fs/cgroup\r\n/dev/loop12     163M  163M     0 100% /snap/gnome-3-28-1804/145\r\n/dev/loop4      253M  253M     0 100% /snap/pynsource/20\r\n/dev/loop1       66M   66M     0 100% /snap/gtk-common-themes/1515\r\n/dev/loop3       56M   56M     0 100% /snap/core18/2246\r\n/dev/loop5       44M   44M     0 100% /snap/snapd/14295\r\n/dev/loop6       43M   43M     0 100% /snap/snapd/14066\r\n/dev/loop8      128K  128K     0 100% /snap/bare/5\r\n/dev/loop9      165M  165M     0 100% /snap/gnome-3-28-1804/161\r\n/dev/loop7      100M  100M     0 100% /snap/core/11798\r\n/dev/loop2       66M   66M     0 100% /snap/gtk-common-themes/1519\r\n/dev/loop10     252M  252M     0 100% /snap/pynsource/19\r\n/dev/loop11      56M   56M     0 100% /snap/core18/2253\r\n/dev/loop0      100M  100M     0 100% /snap/core/11993\r\ntmpfs           392M   24K  392M   1% /run/user/108\r\ntmpfs           392M     0  392M   0% /run/user/0\r\noverlay          39G   32G  4.4G  89% /var/lib/docker/overlay2/a699597c9aa33bc4924c9bc82f8decc538ffe3fe5e3231b512d768bc5ba63b19/merged\r\nshm              64M     0   64M   0% /var/lib/docker/containers/7c001393a1153608c1cae1a0b2f2466a02934d1051faec389a3ce4393e1e3e24/shm\r\nroot@ytest-VirtualBox:~# '
>>> session.recv(50000)
Traceback (most recent call last):
  File "/usr/local/lib/python3.7/dist-packages/paramiko/channel.py", line 699, in recv
    out = self.in_buffer.read(nbytes, self.timeout)
  File "/usr/local/lib/python3.7/dist-packages/paramiko/buffered_pipe.py", line 155, in read
    raise PipeTimeout()
paramiko.buffered_pipe.PipeTimeout

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/usr/local/lib/python3.7/dist-packages/paramiko/channel.py", line 701, in recv
    raise socket.timeout()
socket.timeout
>>>
```
