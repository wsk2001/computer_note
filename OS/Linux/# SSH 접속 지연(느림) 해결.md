# SSH 접속 지연(느림) 해결

CentOS 6 에서 테스트 하였습니다.

어느날 빠르게 접속되던 `ssh` 가 어느날 부터 느리게 연결 될 때가 있습니다.

계정을 입력하는 부분 부터, 패스워드 입력 후 인증이 되기 까지 말이죠.

저 같은 경우에 다음과 같은 방법으로 해결했습니다.

- GSSAPI 옵션 해제
- DNS 사용 해제
- localhost DNS 로 설정

### GSSAPI 옵션 해제 & DNS 사용 해제

```
[]$ vim /etc/ssh/sshd_config

GSSAPIAuthentication no # yes를 no로 바꿔도 되고, yes를 주석처리하고 no를 주석해제하시면됩니다.
UseDNS no # 주석을 해제하고 no로 설정
```

### localhost DNS 설정

```
[]$ vim /etc/resolve.conf

nameserver 127.0.0.1  # 맨위에 적용
```

인터넷에 검색해 보시면 보다 많은 내용을 확인 하실 수 있으며, 보다 자세한 검색을 원하시면 다음과 같은 명령으로 `ssh` 접속 흐름을 파악하시면 좋습니다.

```
[]$ ssh -v [계정]@[접속 주소]
```



출처: https://gafani.tistory.com/entry/SSH-접속-지연느림-해결 [Sanctuary]