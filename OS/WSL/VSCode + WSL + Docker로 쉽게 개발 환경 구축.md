# VSCode + WSL + Docker로 쉽게 개발 환경 구축



## 세 종류의 신기

지금 **WSL** , **Docker** , **VSCode** 는 사용할 수 있어 당연한 분위기가 되어 왔기 때문에 초보자를 위해 굉장히~~적용 가능~~간단히 도입 순서를 정리해
**최강의 환경 기구를** 구축하기 위해 이하의 4 단계로 해설하겠습니다

1. [WSL2 도입](https://qiita.com/takoraisutaro/items/da90d5e4b6027e36bcbd?utm_source=Qiitaニュース&utm_campaign=300773ccfb-Qiita_newsletter_569_06_07_2023&utm_medium=email&utm_term=0_e44feaa081-300773ccfb-34467781#1-wsl2の導入)
2. [Docker 소개](https://qiita.com/takoraisutaro/items/da90d5e4b6027e36bcbd?utm_source=Qiitaニュース&utm_campaign=300773ccfb-Qiita_newsletter_569_06_07_2023&utm_medium=email&utm_term=0_e44feaa081-300773ccfb-34467781#2-dockerの導入)
3. [VSCode 설정](https://qiita.com/takoraisutaro/items/da90d5e4b6027e36bcbd?utm_source=Qiitaニュース&utm_campaign=300773ccfb-Qiita_newsletter_569_06_07_2023&utm_medium=email&utm_term=0_e44feaa081-300773ccfb-34467781#3-vscodeの設定)
4. [사용해보기](https://qiita.com/takoraisutaro/items/da90d5e4b6027e36bcbd?utm_source=Qiitaニュース&utm_campaign=300773ccfb-Qiita_newsletter_569_06_07_2023&utm_medium=email&utm_term=0_e44feaa081-300773ccfb-34467781#4-使ってみる)

## 1. WSL2 도입

원래 WSL은 무엇입니까? 라고 하는 분도 계신다고 생각합니다만, **Windows내에서 Linux 환경을 사용할 수 있다고 하는 것만** 기억해 두면 OK입니다
도입에 대해서는 PowerShell로 이하 커멘드를 실행할 뿐!

파워쉘

```
wsl --install
```

설치가 완료되면 PC의 시작 메뉴에서 Ubuntu를 열고 사용자 이름과 암호를 설정합시다
.

WSL을 진지하게 알고 싶은 분은 이쪽↓
[https://learn.microsoft.com/ko-kr/windows/wsl/about](https://learn.microsoft.com/ja-jp/windows/wsl/about)
도입에 대한 공식 문서↓
[https://learn.microsoft.com/ko-kr/windows /wsl/install](https://learn.microsoft.com/ja-jp/windows/wsl/install)

## 2. Docker 도입

Docker에 대해서는 엄청난 잡설 설명을 하면 **어플리케이션의 환경(컨테이너)을 간단하게 구축/공유하기 위한 툴이라고 하는** 느낌입니다

Docker를 사용하면 주로 다음과 같은 이점이 있습니다.

- 독립적인 환경이 쉽게 지어집니다.
- Dockerfile(컨테이너를 세우기 위한 설계도와 같은 것)으로 관리할 수 있기 때문에 환경의 공유가 간단해진다
- 부서져도 재구성이 용이

WSL에서 다음 명령을 실행하면 입력됩니다.

WSL

```bash
sudo apt-get update
sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
sudo usermod -aG docker $USER
```

Docker를 진지하게 알고 싶은 분은 이쪽↓
https://docs.docker.jp/get-started/overview.html
도입에 관한 공식 문서↓
https://docs.docker.com/engine/install/

## 3. VSCode 설정

VSCode 본체의 다운로드는 여기↓
https://code.visualstudio.com/
기동 후에 확장 기능의 탭으로부터 「Remote Development」를 인스톨

## 4. 사용해보기

이번은 엄청 심플한 Python만의 환경을 구축하자☆
우선 WSL의 터미널에서 적당한 작업용 디렉토리로 이동해 이하의 커멘드로 VSCode를 기동하자

WSL

```
code .
```

``ctrl+shift+p''를 누르면 명령 팔레트가 표시되므로 ``Reopen in Container''를 누르십시오
. ! 빌드가 완료되면 컨테이너에 연결된 상태에서 VSCode가 다시 시작됩니다. 왼쪽 하단이 Dev Container가되면 완료 입니다 . 지정 및 환경 구축 후 설치할 확장 기능 등을 지정할 수 있습니다.
[![4-1.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3124426%2Fa43ef4bd-e37f-3873-6967-6a2b518efbf9.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=3c75ab9e6b62464bc70a7b6a2896933b)](https://camo.qiitausercontent.com/2a71f582a74bf3ddacfdab49c2274b3b1c9d94b8/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333132343432362f61343365663462642d653337662d333837332d363936372d3661326235313865666266392e706e67)


[![4-2.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3124426%2Fc519eec3-2991-652d-04d6-45aa866a91d4.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=7a1787e75f583a9905ddf8aa0082f712)](https://camo.qiitausercontent.com/2133444d28d0235bbe91866bfc8f11622825a8db/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333132343432362f63353139656563332d323939312d363532642d303464362d3435616138363661393164342e706e67)