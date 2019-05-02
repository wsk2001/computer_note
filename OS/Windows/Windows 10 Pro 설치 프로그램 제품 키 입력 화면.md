# Windows 10 Pro 설치 프로그램 제품 키 입력 화면

**Q)** 
Windows 10 Pro를 다시 설치하고자 https://www.microsoft.com/ko-kr/software-download/windows10ISO 에서 
ISO file을 받아 다시 미디어를 만들어 설치하고 있습니다.

확인한 결과 이번 ISO 파일에는 Home과 Pro가 같이 들어 있습니다.
설치시 제품키를 보고 Home이나 Pro를 구별하여 설치한다고 합니다.
하지만 문제는 제 컴퓨터의 경우 설치시 제품 키 입력화면이 보이지 않고 넘어가 버리는것입니다.
아마도 메인보드에 자동인증이 Home으로 되어 있는것 같습니다.
Pro를 사용하고자 Pro를 구매했습니다.
하지만 계속 Home으로 설치가 됩니다.
해결 방법이 있을까요?

**A)**
답변 정말 감사합니다.
정품이 좋은 점이 있네요^^
고객지원센터에 문의하여 해결했습니다.
혹시 저와 같은 문제가 발생하시는 분은 참고하세요.
해결 방법은 ei.cfg를 이용하면 되더군요.

아래 내용은 기술지원팀에서 지원해주신 내용입니다.
1.     제작한 USB에 들어갑니다.
2.     \source 경로안에 ei.cfg 라는 파일을 만듭니다.
3.     Ei.cfg의 내용은 아래와 같습니다.

[EditionID]
Professional


[Channel]
Retail

[VL]
0

4.     해당 \source 경로안에 pid.txt 라는 파일을 만듭니다.
[PID]
Value=<제품키>

 
5. 해당 USB를 가지고 설치를 진행합니다.

도움을 주신 한국 마이크로소프트 기술지원팀께 감사드립니다.^^
