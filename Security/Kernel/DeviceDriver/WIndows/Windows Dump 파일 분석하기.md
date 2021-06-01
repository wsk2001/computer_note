# [Windows Dump 파일 분석하기](https://kikook.tistory.com/539)

출처:  https://kikook.tistory.com/539



몇 가지 방법이 있는데, 그 중 Windbg.exe 를 이용한 방법이다.

아래 사이트에서 Winsdk_web.exe 를 다운받아 설치한다. 아래는 32비트용임.
http://www.microsoft.com/download/en/details.aspx?displaylang=en&id=8279

[![img](https://t1.daumcdn.net/tistory_admin/assets/blog/5d3d5ea1293599b62522fb700f1bc924555cd420/blogs/image/extension/exe.gif?_version_=5d3d5ea1293599b62522fb700f1bc924555cd420) winsdk_web.exe](https://kikook.tistory.com/attachment/cfile27.uf@201584384F3C8E051CEDE9.exe)



다음은 설치 화면...


\1. .NET 프레임웍 4 가 설치되지 않아 몇 가지 SDK component가 설치되지 않을 수 있다는 메시지.

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\137081384ED5E15316.jpg)*



\2. 설치마법사 화면. Next!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\18013A334ED5E1D316.jpg)



\3. I Agree 선택하고, Next!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\1306C0394ED5E2310D.jpg)



\4. 설치 위치. 특별한 사항 없음 기본 그대로, Next!!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\150370394ED5E25411.jpg)



\5. 설치하고자 하는 옵션을 선택하는 화면. windbg를 사용하려면 별다른 선택없이 기본으로 Next!!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\145D9F3B4ED5E2771D.jpg)



\6. 이제 설치하겠다는 협박(?).ㅋㅋㅋ Next!!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\2027F63C4ED5E28F09.jpg)



\7. 설치 시작됨! 여기서 멈추려면 Cancel을.. 선택한 옵션을 다운로드 받으면서 설치가 진행되기 때문에 시간이 꽤 걸림.

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\154B18364ED5E2A420.jpg)



\8. 설치 완료!!!

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\175BEA364ED5E3B71B.jpg)



\1. 설치가 끝나고 WinDbg를 실행시킨다.

시작 - 프로그램 - Debugging Tools for Windows - WinDbg

\2. Symbol File Path를 설정한다.
  File - Symbol File Path...
  창이 하나 열리는데 거기에 아래와 같이 입력한다.
  SRV* c:\websymbols*http://msdl.microsoft.com/download/symbols

- **WinDBG 심볼 패스 설정하기**

 \- [File] - [Symbol File Path...] - 아래 주소 입력

 **SRV\*c:\symbols\*http://msdl.microsoft.com/download/symbols**

  \- 0:000> .reload

- **심볼 패스 쉽게 설정하기**

 \- symfix 명령어 이용하기 (폴더만 지정해주면 알아서 다운로드함)

 0:000>  .symfix  c:\symbols
 0:000>  .reload  

- **심볼 패스 추가하기** 

 \- sympath 명령 뒤에 + 를 붙여서 자신의 프로그램 심볼이 있는 폴더를 추가 해준다.

***\*
\** 0:000>  .sympath+ c:\TssShield
 0:000>  .reload**  




\3. 아래와 같이 덤프파일을 불러온다.  (C:\Windows\Minidump)

File - Open Crash Dump...

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\151031354ED5E4461A.jpg)

\4. Dump 파일이 있는 폴더에 들어가서 해당 Dump (*.dmp) 파일을 선택하고 확인! 그러면 아래와 같이 Symbol을 읽어 오면서 분석 준비를 마치게 된다.

분석준비가 끝났으면, 아래와 같이 맨 아래 kd> 명령창에 다음 명령어를 입력하고 엔터!
1: kd> !analyze

위 명령어 결과는 문제 원인으로 추정된 것에 대한 간단한 결과만 알려준다.
자세한 내용을 보기 위해서는 아래와 같은 명령어를 입력한다.
1:kd> !analyze -v

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\140E84394F3C8FDD2D.jpg)

\5. 위와같이 명령어를 실행하면 1:kd> 가 BUSY 로 바뀌면서 덤프 분석을 실행한다.
 아래 그림 중 첫번째는 analyze 만 실행한 것이고, 두 번째는 -v 옵션을 준 결과이다.

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\1730B4354F3C902E23.jpg)

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\1825C4354F3C902F2B.jpg)

![](C:\home\Documents\computer_note\Kernel\DeviceDriver\WIndows\Images\17310C354F3C902F24.jpg)



위 분석 결과로 보면, 문제 발생 원인은 scsk5.sys 라는 파일에 있으며, 상세 결과를 보면 Driver Fault 라는 내용도 나온다.

전문가 까지는 아니더라도, 대강의 장애 원인 파악은 가능하다.

아래는 참고 자료
출처 : http://fl0ckfl0ck.tistory.com/203



[![img](https://t1.daumcdn.net/tistory_admin/assets/blog/5d3d5ea1293599b62522fb700f1bc924555cd420/blogs/image/extension/pdf.gif?_version_=5d3d5ea1293599b62522fb700f1bc924555cd420) 커널디버깅.pdf](https://kikook.tistory.com/attachment/cfile7.uf@14021C394ED5E7472DE765.pdf)


기타 참고 자료 : http://www.numz.net/a/2162598
http://deguls.tistory.com/52
http://blog.naver.com/ojini21c?Redirect=Log&logNo=120090334194
http://www.devpia.com/MAEUL/Contents/Detail.aspx?BoardID=50&MAEULNO=20&no=870274&ref=870267
http://debugbeyond.blogspot.com/2011/01/how-to-set-microsoft-symbol-server.html





참조 : https://jangpd007.tistory.com/203

