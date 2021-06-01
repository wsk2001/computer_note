# py2exe의 옵션들

출처: https://soooprmx.com/archives/1748

오랜만에 파이썬 코드 하나를 exe로 만들면서 사용한 py2exe의 옵션을 정리하는 차원에서 기록.

## py2exe

py2exe는 파이썬(Python)으로 작성된 코드를 파이썬이 설치되지 않은 윈도 환경에서 바로 실행할 수 있는 실행파일로 만들어주는 도구입니다. 이 역시 별도의 어플리케이션으로 존재한다기 보다는 파이썬 모듈로 구성되어 있으며, 실행파일을 만들 때의 옵션들을 포함하는 별도의 빌드 스크립트를 짜야 합니다. 자세한 설명은 py2exe의 프로젝트 홈페이지를 참고하면 되겠습니다. 여기서는 옵션에 대한 설명을 요약합니다. 원문은 py2exe 위키에서 가져왔습니다.

## 옵션항목

- **console** : 콘솔.EXE[1. 도스 프롬프트 창이 뜨는 형태로 실행되는 프로그램]로 변환할 코드의 목록 (리스트 형태)[3. 파이썬의 리스트는 대괄호로 묶고, 각 항목은 컴마로 구분합니다.]
- **windows** : 윈도EXE[2. 윈도창이 뜨는 형태로 실행되는 GUI 프로그램]로 변환활 코드의 목록 (리스트 형태). pyw의 포맷이라면 이 옵션을 선택합니다.
- service : 윈도 서비스 클래스를 포함하는 모듈의 이름 (리스트 형태)
- com_server : 윈도 COM 서버 클래스를 포함하는 모듈의 이름 (리스트 형태)
- zipfile : 실행에 필요한 모듈을 zip 파일의 형태로 묶음. 값을 None으로 주면 실행파일로 묶이고 별도의 zip 파일이 생성되지 않음
- **options** : 컴파일에 필요한 옵션을 지정 (사전 형태)[4. 파이썬의 사전(dictionary) 데이터는 중괄호로 묶고, 각 항목은 컴마로 구분되며, 각 항목은 콜론을 짝 지웁니다.] 사전의 형태는 options={“py2exe”:{ “옵션1″:”값1”, “옵션2″:”값2” …},}의 모양입니다.

## Options

옵션 항목 중 다시 options 에서 사용되는 값들을 살펴봅니다.

- unbuffered : 만약 true로 설정하면 표준입출력에서 버퍼를 사용하지 않는 실행 파일을 생성하게 됩니다.
- **optimize** : 코드를 최적화 합니다. 0=최적화 안함 , 1=일반적 최적화, 2=추가 최적화
- **include** : 포함할 모듈의 이름을 리스트 형태로 지정합니다.
- **packages** : 단일 모듈이 아닌 패키지 이름을 리스트 형태로 지정합니다. 여기에 명시된 패키지는 통상 서브 패키지까지 포함합니다.
- ignore : 패키지나 모듈을 찾다가 없으면 무시할 모듈을 명시합니다.
- **exclude** : 제외할 모듈을 명시합니다.
- dll_exclude : 제외할 DLL 파일을 명시합니다.
- dist_dir : 최종 바이너리를 생성할 경로를 지정합니다. 기본적으로는 setup.py 파일이 실행되는 디렉토리에 dist 디렉토리를 생성하게 됩니다.
- **compressed** : 압축된 zip 파일을 생성할 것인지 여부를 지정합니다. 불리언 값입니다.
- xref : 불리언 값입니다. 교차 참조를 보여줄 것인지 여부를 지정합니다.
- **bundle_files** : 번들링 여부를 결정합니다. 3=번들링하지 않음(디폴트), 2=기본번들링(파이썬 인터프리터를 포함하지 않음), 1=파이썬 인터프리터까지 번들링
- skip_archive : 파이썬 바이트 코드 파일을 번들에 포함하지 않고 파일시스템에 직접 생성합니다.
- ascii : (불리언) encodings, codec 패키지를 포함하지 않습니다.
- custom_boot_script : 실행환경 세팅 후 실행할 파이썬 코드를 명시합니다.

별도의 예제는 표시하지 않습니다. 다만 단일 실행 파일을 생성하는 법을 안내하는 [예전 포스팅을 링크](https://soooprmx.com/wp/archives/976)합니다.

---

## 예제

#### Console 프로그램 생성 예제

```python
# -*-coding: utf-8
# run >>> python setup.py py2exe

from distutils.core import setup
import py2exe

setup(
    options={'py2exe': {'packages': ['encodings'],
                        'bundle_files': 1}},
    zipfile=None,
    console=[{'script': 'TssDiskFormat.py'}],
)

```



### Windows 프로그램 생성 예제

```python
# -*-coding: utf-8
# run >>> python setup.py py2exe

from distutils.core import setup
import py2exe

setup(
    options={'py2exe': {'packages': ['encodings'],
                        'bundle_files': 1}},
    zipfile=None,
    console=[{'script': 'TssDiskExtend.py'}],
)

```



#### 역자 주)

한개의 setup.py 파일에 setup() 함수를 여러개 호출 하도록 하여 한번에 복수의 .exe 파일을 만들 수 있다.

