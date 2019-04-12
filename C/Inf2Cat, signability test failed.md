# Inf2Cat, signability test failed.

"Inf2Cat, signability test failed." Double click to see the tool output. 에러 해결

  1.1 에러 화면 

​    a. 환경 구성

​      \- IDE : Visual Studio 2015

​      \- OS : WIN 8.1 64Bit

​    b. 증상

​      \- Filter Driver: NDIS 프로젝트 생성 후( 다른 종류의 Driver 프로젝트도 동일) 정상 빌드되던 프로젝트가 아래와 같은 에러가 나는 경우가있습니다.

![](.\images\2017-09-15_08;11;42.png)


​    \- 클릭해보시면 서명과정에서의 에러이며 inf파일이 원인이란 것을 알 수 있습니다.

![](.\images\2017-09-15_08;10;21.png)

1.2 에러원인 

​    \- Device Driver 프로젝트를 빌드 후 실제 설치를 하기 위해서는 Driver 서명과정을 거쳐야 하는데 

​      Visual Studio 에서는 서명과정을 빌드 후에 자동으로 진행해 줍니다.

​      Inf2cat이라는 프로그램이 서명과정을 진행해 주는데 Driver를 생성한 날짜와 서명을 하는 

​      날짜가 일치해야 합니다.

​      기본값이 UTC (세계 협정 시각)를 사용하기 때문에 우리나라 시간 (UTC+9:00)과 일치하지 않아 

​      Driver 빌드과정에서 에러가 나게 됩니다.

​     

 ( Driver빌드를 오후 12시부터 오전9시까지 시간사이에 진행하게 되면 UTC 시간이   우리나라 

날짜보다 1일전이 되면서 에러가 나는 것입니다.     

오전 9시 이후에 빌드해보시면 에러가 사라지는 것을 확인 하실 수 있습니다. )
  

![](.\images\2017-09-15_09;14;01.png)

1.3 해결방법

​      \- 프로젝트 속성값 변경

​        a.  프로젝트 선택 후 우클릭에서 [Properties] 메뉴를 클릭합니다.

![](.\images\2017-09-15_08;16;37.png)



b. [Inf2Cat] 메뉴를 클릭하면 Use Local Time 값을 설정 할 수 있습니다.

![](.\images\2017-09-15_08;18;14.png)

c. 콤보박스에서 [Yes (/uselocaltime)] 값을 설정해 줍니다.

![](.\images\2017-09-15_08;19;32.png)

 d. 프로젝트를 재빌드 하시면 정상 빌드가 되는 것을 확인할 수 있습니다.
 ![](.\images\2017-09-15_08;20;39.png) 

1.4 참고

​      \- Inf2Cat 속성 정보

​     [Inf2Cat Properties for Driver Package Projects](https://docs.microsoft.com/en-us/windows-hardware/drivers/develop/inf2cat-properties-for-driver-package-projects)



[**[출처\]** ](http://blog.naver.com/PostView.nhn?blogId=wsi5555&logNo=221102558017&categoryNo=34&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=search#)[[VS2015\] "Inf2Cat, signability test failed." 에러 해결](http://blog.naver.com/wsi5555/221102558017)|**작성자** [해맑은 코끼리](http://blog.naver.com/wsi5555)