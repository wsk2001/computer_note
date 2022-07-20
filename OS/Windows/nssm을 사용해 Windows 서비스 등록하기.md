# nssm을 사용해 Windows 서비스 등록하기

출처: https://woony-sik.tistory.com/9

서비스를 배포 후에 명령어로 하나하나 실행하기 귀찮아 만들게 된 런처 프로그램이 있다. 거기에서 Nginx도 실행 종료할 수 있도록 등록해놨는데 회사 팀장님이 Nginx는 그냥 별도로 빼자고 한다.

그래서 전 회사에서 한번 사용해본 nssm을 사용해 Nginx를 윈도우 서비스로 등록한 내용이다. 한번 해봤지만 사용법을 잊어버려서 정리할 겸 써본다. 그때도 생각했지만 매우 간단하다.

우선 nssm 을 설치하자. 다운로드 경로는 아래와 같다.

[nssm.cc/download](https://nssm.cc/download)

[ NSSM - the Non-Sucking Service ManagerNSSM - the Non-Sucking Service Manager Windows 10 Creators Update 2017-04-26: Users of Windows 10 Creators Update should use prelease build 2.2.4-101 to avoid an issue with services failing to start. If for some reason you cannot use that build you can alsnssm.cc](https://nssm.cc/download)

위 사이트를 들어가 Latest release 버전을 다운받고 원하는 곳에 압축을 푼다.

 

이제 따라하기만 하면 윈도우 서비스에 등록할 수 있다.

 

\1. CMD 열기

\2. nssm.exe가 존재하는 폴더까지 이동 (cd /nssm-2.24/win64)

\3. nssm.exe install <service name>

 

여기까지 했으면 아래와 같은 GUI가 나타난다.

 



![img](https://blog.kakaocdn.net/dn/qHjIj/btq255K3J7x/7jOsGaZnzvckKbTZAtH7Z1/img.png)서비스에 등록할 프로그램 선택 전



Application 탭에서 Application 영역 안의 Path 줄 오른쪽 끝에 보이는 ... 버튼을 클릭하여 등록할 파일을 선택해주면 아래와 같이 Path, Startup directory가 잡힌다.

 



![img](https://blog.kakaocdn.net/dn/bLxPyk/btq2SLBgeJi/oo2K7ysyFgiKdCMw4bCRkK/img.png)서비스에 등록할 프로그램 선택 후



Install service 버튼을 눌러주면 서비스에 등록된다.

 

지금까지의 작업은 말 그대로 등록된 상태라 실행을 시켜줘야 한다. 

 

CMD창에서 다시 nssm 이 깔려있는 폴더로 이동후 nssm start nginx 를 입력하면 끝!



![img](https://blog.kakaocdn.net/dn/DrUOT/btq2YQPJK73/hs0MyNU5fIRk1jpBII5hGk/img.png)등록한 서비스 실행시키기





 

추가로 아래는 명령어 리스튼데 많이 사용할 것 같을 것만 정리했다.

| 명령어                   | 설명                             |
| ------------------------ | -------------------------------- |
| nssm start servicename   | 등록된 서비스를 실행한다.        |
| nssm stop servicename    | 등록된 서비스를 종료한다.        |
| nssm restart servicename | 등록된 서비스를 재실행한다.      |
| nssm status servicename  | 등록된 서비스의 상태를 확인한다. |
| nssm edit servicename    | 등록된 서비스를 수정한다.        |
| nssm remove servicename  | 등록된 서비스를 삭제한다.        |

 

여기까지