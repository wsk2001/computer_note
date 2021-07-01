# VSCode에서 SSH 원격 접속하는 방법

출처: https://mmozzi.tistory.com/79

리눅스로 SSH 원격 접속을 사용하는 경우, 대부분 **puTTY** 프로그램을 사용합니다. 윈도우 서버에 네트워크 드라이브 연결하여 VSCode를 사용하다가 리눅스 서버에서 개발할 일이 생겨 알아보던 중, **VSCode에서도 SSH 원격 접속을 지원하는 확장 툴인 Remote Development**를 발견해 오늘은 사용 방법에 대해 포스팅하려고 합니다.



## ***VSCode에서 SSH 원격접속 하는 방법***

> 1. 확장 탭에서 **Remote Development** 설치

좌측 메뉴 리스트에 있는 확장 탭에서 **Remote Development**를 검색하여 설치합니다.

![](./images/vscode-001.png)



> 2. SSH 연결 설정 파일 수정

**F1 키를 눌러 ssh를 검색**하여 아래 메뉴를 클릭합니다.

![](./images/vscode-002.png)

내 PC의 config 파일을 선택합니다. (C:\Users\**사용자명**\.ssh\config)

![](./images/vscode-003.png)

config 파일을 열어 아래와 같이 입력하고 저장합니다. 설정 파일에 등록 없이 바로 "**ssh 계정명@IP주소**"로도 연결할 수도 있으나, 포트번호나 계정명에 맞게 접속을 시도하지 못하는 **오류가 발생**하기 때문에 설정 파일부터 등록했습니다. (참고 : https://daewonyoon.tistory.com/317)

``` 
Host [계정명]@[IP 주소]:[포트번호]
    HostName [IP 주소]
    User [계정명]
    Port [포트 번호]
```

![](./images/vscode-004.png)



> 3. SSH 연결

**F1 키를 눌러 ssh를 검색**하여 아래 메뉴를 클릭합니다. (없으면 스크로바를 이용해서 확인)

![](./images/vscode-005.png)

**config 파일에 등록한 Host**를 선택하시면 됩니다.

![](./images/vscode-006.png)

Install이 완료되면 **+ 버튼을 클릭하여 새로운 터미널을 실행**시킬 수 있습니다.

![](./images/vscode-007.png)

![](./images/vscode-008.png)

F1을 눌러 명령창에서 실행하지 않고 원격 탐색기 탭에서 편한 UI로 Remote Development를 사용할 수 있습니다.

![](./images/vscode-009.png)

> ### 4. 탐색기 연결 

**탐색기 탭**에서 원격지 서버의 **파일 목록**을 받아와 사용할 수 있습니다.

![](./images/vscode-010.png)

![](./images/vscode-011.png)

![](./images/vscode-012.png)



> ### 5. 완성된 ssh 작업 환경

![](./images/vscode-013.png)