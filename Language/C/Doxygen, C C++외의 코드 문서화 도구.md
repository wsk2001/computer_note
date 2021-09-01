#  Doxygen, C/C++외의 코드 문서화 도구

 Doxygen은 주어진 소스 코드를 분석하고 소스 코드에 있는 특정한 형식의 주석을 이용해서 소스 코드를 자동으로 문서화해주는 프로그램이다. 결과는 일련의 HTML 페이지들이구, LATEX나 PDF 문서들을 만들어낼 수도 있다.

 일단 Doxygen을 설치 하기 전에 아래와 같이 해당 문서에그래프 다이어그램도 만들기 위해서는 Graphviz의 Graph Visualization Software를 설치할 필요가 있다. 

![img](./images/127CDD0E4CA06EBC2D)

[graphviz-2.26.3 다운 받으러 가기](http://www.graphviz.org/Download_windows.php)

이 사이트에 접을 하면 위와 같이 첫 화면에서 **current stable release** 부분에서 다운을 받을 수 있다.

![img](./images/1721540B4CA06ECA5D)

 graphviz-2.26.3.msi 이라는 파일 이름으로 다운이 받아졌을 것이다. 다운 받고 아무 폴더에 깔아 주기만 하면 된다.

| **Doxygen 설치** |      |
| ---------------- | ---- |
|                  |      |

이제 본격적으로 Doxygen을 설치해보자. 

[Doxygen 다운 받으러 가기](http://www.stack.nl/~dimitri/doxygen/)

![img](./images/1854961F4CA06F845A)

위에서 보이듯이 Download 메뉴를 클릭하고 중간쯤에 다운 받을 수 있는 화면이 나온다 윈도우에서는 Doxygen Wizard로 문서화를 간편하게 할 수 있다. Wizard가 나오기 전에는 조금 사용하기 불편했던 모양이지만, 요즘에는 편의성이 많이 제공되고 있다. 

![img](./images/112651034CA06FE8BD)

다운을 받았으면 원하는 폴더에 설치를 해주자. 본인은 \Tools\Doxygen 1.7.1 이라는 폴더에 설치를 했다. 

| **Doxygen Wizard** |      |
| ------------------ | ---- |
|                    |      |

 설치를 끝마쳤다면, bin 폴더에 doxywizard.exe 파일을 실행 시켜 보자. 

![img](./images/14101F0B4CA070C584)

아래는 실행 시킨 모습이다. 실행을 시켰으니 옵션들에 대해서 알아 보자. 우선 Wizard - Project 부분이다.

![img](./images/176012164CA071715E)

 \- 1번 : 프로젝트 이름을 설정한다. 

 \- 2번 : 소스 파일이 있는 디렉토리를 설정한다. 

 \- 3번 : 문서화한 결과 html들을 넣을 Destination Directory를 설정한다. 

![img](./images/1115CD284CA0720E5F)

\- 1번 : **Include cross-refeenced source code in the output** 옵션은 각 함수마다 사용한 함수 코드로 바로 Jump할 수 있는 링크를 생성해 준다. 즉, html 문서에서 링크를 타고 넘나 드는 것과 같은 효과를 나타낸다.

 \- 2번 : Doxygen에서는 각 코드만의 고유 주석 스타일을 가지고 있는데, 이런 주석 스타일에 맞춰서 해당 언어에 맞는 것에 체크를 해주면 된다. 

![img](./images/112BDB1D4CA072C735)

 \- 이곳은 자신이 출력하고자 하는 형식을 고르는 곳이다. 

![img](./images/1562F10E4CA0730E3D)

 \- Wizard의 마지막 부분인 Diagrams 부분은 다이어그램을 사용할 것인지 안할 것인지 설정하는 부분이다. GraphViz를 설치 했다면 이 다이어그램을 사용할 수 있다.

![img](./images/11515D264CA073A682)

 \- 이런 설정을 다 마쳤다면 Run Doxygen을 통해 문서화하고 결과물들을 뽑아 낼 수 있다.

| **Doxygen 에서 한글은?** |      |
| ------------------------ | ---- |
|                          |      |

 간혹 가다가 문서화 했을 시, 주석에 한글사용을 했다면 깨지는 경우가 있다. 아까 expert부분을 넘어 갔는데, 이 부분은 세세한 설정부분이 있어 까다롭지만, 다른 것은 몰라도 이 한글이 안깨지게 하는 법만 알면 된다. 아래 빨간색 네모친 부분만 바꿔주자.

![img](./images/125D07264CA075B959)

![img](./images/205D07264CA075B95A)



| **Doxygen, chm 파일 만들기** |      |
| ---------------------------- | ---- |
|                              |      |

 PDF와 같이 manual이나 그런곳에 자주 보이는 chm 형식으로 파일을 만들 수 있다. Wizard - Output 부분에 chm 부분을 체크 하면 된다. 허나 이 설정을 체크만 한다고 해서 다 끝나는 것은 아니다. MS HTML Help가 필요하기 때문이다. 만약 깔려 있다면 상관없지만, 안깔려 있다면 꼭 설치를 해주고 설정을 해줘야 한다. 

[MS HTML Help 받으러 가기](http://msdn.microsoft.com/en-us/library/ms669985.aspx)

![img](./images/191E48174CA076B07D)

위의 다운로드 링크를 클릭해 다운받고, 어디에다가 설치 했는지 알아 두자 Doxygen Wizard의 설정도 바꿔줘야 하기 때문이다.

![img](./images/16619C194CA0770B9E)

Expert - HTML 부분에서 중간쯤에 **GENERATE_HTMLHELP** 부분을 체크 한 후, **HHC_LOCATION** 부분에서 아까 설치한 HTML Help의 프로그램 경로를 지정해 준다.

