# OpenSSL 설치 및 Visual Studio 적용하기

출처: https://domdom.tistory.com/225



우선 제가 하려고 하는 것은 OpenSSL 라이브러리를 사용하는 C언어 프로젝트를 빌드하려는 것입니다. 우선 제 개발 환경부터 설명드리겠습니다.

 

\- 운영체제 : Windows 10

\- 개발도구 : Visual Studio 2019 Community

\- 컴파일환경 : x64 (64bit)

\- OpenSSL 버전 : v1.1.1 x64

 

그리고 OpenSSL 설치 및 Visual Studio 환경에 적용하기까지 아래와 같은 순서대로 설명할 예정입니다.

 

1. OpenSSL 설치
2. Visual Studio Project 설정
3. 빌드 및 빌드 결과 확인

 

## 1. OpenSSL 설치

http://slproweb.com/products/Win32OpenSSL.html



위 사이트에 들어갑니다. 그리고 Win64 OpenSSL v1.1.1L 설치파일을 다운받습니다. EXE 든 MSI 든 상관없습니다. 저는 EXE를 다운받아보겠습니다.



![Pn1DdLMVvIlusiWOMy4pf1-img.png](.\images\Pn1DdLMVvIlusiWOMy4pf1-img.png)



다운 받으면 아래와 같이 생겼습니다. Win64OpenSSL-1_1_1L.exe 입니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\lbvqVKEWQZJ3CPJ9Qn2G90-img.png)



실행 시키면 아래와 같이 나옵니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\9yPVknGoV0EkKvDse03kxK-img.png)



동의 버튼에 체크 후 Next 버튼을 눌러서 다음으로 넘어갑니다.

그 다음 또 다음버튼을 누르면 아래와 같은 화면이 나옵니다. 여기서 저는 Windows 를 사용하기 때문에 아래와 같이 체크하고 다음으로 넘어갑니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\QrHnBK7PfqIuSceFNyzKnK-img.png)



그리고 다음에 나오는 Install 버튼을 눌러 설치하면 아래와 같이 나오는데 여기서 그냥 체크 해제 후 Finish 누르면 됩니다. (기부하고 싶으시면 하세요.)



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\O8oMTkrkvbM1SCkj5Sas2k-img.png)



잘 설치되었는 지 아래 경로로 가서 확인해보겠습니다. 본인이 설치하신 경로는 설치할 때 나오니 꼭 한번 확인해보세요.

```
C:\Program Files\OpenSSL-Win64
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\kmrch8LnZkueOkiTlTMYok-img.png)



잘 설치된 것 같습니다. 이제 Visual Studio 에서 Project 설정에 들어가보겠습니다.

 

## 2. Visual Studio Project 설정

우선 이 프로젝트에서 사용한 샘플코드는 아래 글에서 보실 수 있습니다.

https://domdom.tistory.com/222

 

C언어로 만드는 DES 복호화 예제(python 코드도 포함)

이번에 수업 시간에 과제로 했던 것을 정리해봅니다. 과제 내용은 DES 암호화된 파일이 주어지고, 해당 내용을 주어진 key 값으로 복호화하는 코드를 C언어로 작성하라는 것이었습니다. Python으로

domdom.tistory.com

Visual Studio 를 별도의 라이브러리 설정 없이 여시고 소스코드를 작성하면 아래와 같이 라이브러리를 찾을 수 없어서 openssl 부분과 관련 변수명에 빨간줄이 그어지면서 오류가 난 것을 볼 수 있습니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\B64MyolaojEWWQm58ERkNk-img.png)



관련 오류를 없애기 위해서는 우리가 설치한 OpenSSL 라이브러리를 import 해주어야 하는데요. 우선 프로젝트 속성에 들어가보기 전에 저희가 설치한 OpenSSL 버전과 일치한 bit 버전을 맞춰주어야 합니다. x64 로 맞춰져있는 지 확인해주세요.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\NwUGdfLBf8YAlac0UGK1x0-img.png)



이제 프로젝트 속성에 들어가보겠습니다. 소스코드를 띄워주신 다음에 해당 소스코드 내용에 커서를 두고서(그냥 클릭하라는 의미) 프로젝트 메뉴를 봐주세요. 그리고 (프로젝트이름) 속성 버튼을 눌러주세요.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\aHF6PaiMfZB9KStzWHD7kK-img.png)



누르시면 아래와 같이 나옵니다. 여기서 저희는 [구성 속성] -> [VC+ 디렉터리] 탭에서 [포함 디렉터리] 와 [라이브러리 디렉터리]만 보시면 됩니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\KrJBnhba2gtbsGS1i3Onb0-img.png)



우선 [포함 디렉터리]를 설정해주겠습니다. 아래 방향의 화살표를 눌러주시고 누르면 나오는 편집을 클릭해줍니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\7Q4qrgL8Jez973U40YLkEk-img.png)



그럼 위와 같이 나오는데, 여기서 빈 칸에다가 본인이 설치한 OpenSSL-Win64 의 include 폴더를 경로로 지정해줍니다.

```
C:\Program Files\OpenSSL-Win64\include
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\V0AWQSupbk6S9GvMNm1yj1-img.png)



그런 다음에 확인 버튼을 누릅니다. 이제는 [라이브러리 디렉터리]를 설정해줘야 합니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\VzL2ywiDCtrnuhXosKmK2k-img.png)



```
C:\Program Files\OpenSSL-Win64\lib\VC
C:\Program Files\OpenSSL-Win64\lib
```

그런 다음에 [링커] -> [입력] 탭에서 [추가 종속성]을 설정해줘야 합니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\K1swqcHk3GCO9PSu24JYc1-img.png)



여기서도 마찬가지로 화살표를 누르면 편집이 나오고 편집을 누르면 아래와 같이 나옵니다. 그리고 아래 빈칸에다가 OpenSSL 라이브러리를 전부 import 해주면 되는데요. 하나하나 타이핑하기 귀찮으니 명령어를 사용합시다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\NfYvKWXfIW8wziO86cY5Uk-img.png)



일단 아래 두 경로에 존재하는 모든 라이브러리들을 가져올겁니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\2KC19FIJxgIDFHZJtnaKzK-img.png)



```
C:\Program Files\OpenSSL-Win64\lib\VC
C:\Program Files\OpenSSL-Win64\lib
```

본인이 Windows 운영체제를 사용하시는 분이라면 저와 같이 cmd(명령프롬프트)를 여시고 아래 명령어를 입력하셔서 파일 목록을 뽑아옵니다.

```
dir VC /b /a-d | findstr /v /i ".def$" & dir /b /a-d | findstr /v /i ".def$"
```



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\6Ohono14JMRPro0gMZPHO1-img.png)



그리고 이제 저 파일 목록들을 드래그해서 복사한 다음에 추가 종속성 목록에 추가해줍니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\4WQlt3LUdYlvWhz4y57ojK-img.png)



그리고 확인 버튼을 눌러줍니다. 그리고 마지막으로 [적용] 버튼을 눌러주면 됩니다. 그리고 확인 버튼을 누르면 끝입니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\oGkxtxM9BBjjEt5NfxAovK-img.png)



그럼 이제 소스코드를 확인해보시면 빨간 밑줄이 안쳐져있고, 오류도 없는 걸 확인할 수 있습니다. 그리고 빌드 해보시면 성공 1 이라고 나오는 걸 보실 수 있을 겁니다.



![https://tistory1.daumcdn.net/tistory/4631271/skin/images/blank.png](.\images\fKIMgfokHGub1mDaAQVcq1-img.png)