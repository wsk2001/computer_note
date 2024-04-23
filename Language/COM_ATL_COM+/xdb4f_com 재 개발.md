# xdb4f_com 재 개발

### 개발 절차

- 파일 -> 새로만들기 -> 프로젝트 -> Visual C++ -> ATL -> ATL 프로젝트 선택

- 이름에 프로젝트명 입력: hello_com 후 확인

- ATL 프로젝트 마법사 시작 -> 다음 -> 마침 선택

- 솔루션창 에서 마우스 우클릭 -> 추가 -> 클래스 선택

- Visual C++ -> ATL -> ATL 단순 개체 선택 후 추가 버튼 클릭

- ATL 단순 개체 마법사 시작 

- 약식 이름에 hello_simple 입력 후 마침 버튼 click

- 클래스 뷰 탭 선택

- hello_com tree 확장

- Ihello_simple 선택 후 우클 -> 추가 -> 매서드 추가

- 매서드 이름 , 매개 변수 형식, 매개 변수 이름 입력

- 매개변수 형식에서 * 가 있는것 예: LONG* 를 선택 하면 out, retval check box 가 활성화됨

- return value 가 필요한 경우 LONG* 등을 입력 후 변수명을 설정 하면됨

- 나머지는 생성된 함수 내부에 구현 하면 됨.

- COM 을 이용하여 Data 를 받을 때 BSTR 을 이용하여 받으면 C 에서 바로 사용하기 번거럽기때문에 char arr 로 변경 하여 사용 한다.

  ```c++
  int bstr_to_str(BSTR bstrValue, char* str, int size)
  {
  	USES_CONVERSION;
  	strcpy_s(str, size, OLE2A(bstrValue));
  	return 0;
  }
  ```

  

- COM 등록
	- regsvr32 hello_com
	- regsvr32 /i hello_com 로 하면 오류 발생 하니 regsvr32 hello_com 로 할것
- COM 해제
	- regsvr32 /u hello_com

COM ID(ProgID 찾는 방법) 

- xfile_simple.rgs 에 등록되어 있음.
- ProgID = s 'xdb4f_com.xfile_simple.1'

