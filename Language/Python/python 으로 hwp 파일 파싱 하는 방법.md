# python 으로 hwp 파일 파싱 하는 방법

HWP 파일은 한글과컴퓨터에서 개발한 한글 문서 형식입니다. HWP 파일은 HWP 문서 데이터, 문서 속성, 문서 구조, 문서 리소스 등의 정보를 포함합니다.

HWP 파일을 파싱하는 방법은 크게 두 가지로 나눌 수 있습니다.

첫 번째 방법은 HWP 파일을 XML 형식으로 변환한 후, XML 파일을 파싱하는 방법입니다. HWP 파일은 기본적으로 XML 형식으로 저장되기 때문에, HWP 파일을 XML 형식으로 변환하는 것은 비교적 간단합니다. HWP 파일을 XML 형식으로 변환하는 방법은 다음과 같습니다.



``` python
import hwp

hwp_file = hwp.HWPFile()
hwp_file.Open("test.hwp")

# HWP 파일을 XML 형식으로 변환
xml_string = hwp_file.ToXML()

# XML 형식을 파싱

```



두 번째 방법은 HWP 파일의 구조를 직접 파싱하는 방법입니다. HWP 파일의 구조는 다음과 같습니다.

```
HWP 문서 데이터
  문서 속성
    문서 정보
    문서 형식
    문서 크기
    문서 날짜
    문서 저자
    문서 키워드
  문서 구조
    문서 페이지
      문단
        줄
          글자
    표
      행
        열
          글자
    그림
      점
        라인
          픽셀
```

HWP 파일의 구조를 직접 파싱하기 위해서는 HWP 파일의 구조에 대한 이해가 필요합니다. HWP 파일의 구조에 대한 자세한 내용은 한컴퓨터의 HWP 파일 포맷 사양을 참고할 수 있습니다.

HWP 파일의 구조를 직접 파싱하는 방법은 다음과 같습니다.

``` python
import hwp

hwp_file = hwp.HWPFile()
hwp_file.Open("test.hwp")

# HWP 파일의 구조를 파싱
doc_info = hwp_file.GetDocInfo()
doc_format = hwp_file.GetDocFormat()
doc_size = hwp_file.GetDocSize()
doc_date = hwp_file.GetDocDate()
doc_author = hwp_file.GetDocAuthor()
doc_keywords = hwp_file.GetDocKeywords()

# 문단 목록을 가져옴
paragraph_list = hwp_file.GetParagraphList()
for paragraph in paragraph_list:
  # 문단의 내용을 가져옴
  paragraph_text = paragraph.GetText()
  # ...

```

위의 코드는 HWP 파일의 문서 정보, 문서 형식, 문서 크기, 문서 날짜, 문서 저자, 문서 키워드를 출력합니다. 또한, 문단 목록을 가져와서 각 문단의 내용을 출력합니다.

실제로 HWP 파일을 파싱할 때는 필요한 데이터만 추출하는 것이 중요합니다. 예를 들어, 특정 문단의 내용만 추출하거나, 특정 페이지의 표의 내용만 추출하는 등의 작업을 수행할 수 있습니다.

위의 코드는 HWP 5.0 버전 이상의 파일을 읽을 수 있습니다. HWP 5.0 버전부터는 HWP 파일의 구조가 변경되었기 때문에, HWP 3.0 버전 이전의 파일을 읽으려면 별도의 처리가 필요합니다.

HWP 파일의 버전을 확인하는 방법은 다음과 같습니다.

``` py
import hwp

hwp_file = hwp.HWPFile()
hwp_file.Open("test.hwp")

# HWP 파일의 버전을 가져옴
hwp_version = hwp_file.GetHWPVersion()

```

위의 코드는 HWP 파일의 버전을 숫자로 반환합니다. 예를 들어, HWP 5.0 버전의 파일의 경우 hwp_version은 5를 반환합니다.

HWP 3.0 버전 이전의 파일을 읽으려면 HWP 파일의 구조를 직접 파싱해야 합니다. HWP 파일의 구조에 대한 자세한 내용은 한컴퓨터의 HWP 파일 포맷 사양을 참고할 수 있습니다.



### HWP 3.0 버전 이전의 파일을 읽는 방법


HWP 3.0 버전 이전의 파일을 읽으려면 다음과 같은 단계를 수행해야 합니다.

1. HWP 파일을 바이너리 형식으로 읽습니다.
2. HWP 파일의 구조를 파싱합니다.
3. 필요한 데이터를 추출합니다.

HWP 파일을 바이너리 형식으로 읽는 방법은 다음과 같습니다.

``` python
import io

with io.open("test.hwp", "rb") as f:
  data = f.read()

```

위의 코드는 HWP 파일의 내용을 바이너리 형식으로 읽어서 data 변수에 저장합니다.

HWP 파일의 구조를 파싱하는 방법은 다음과 같습니다.

``` python
import struct

# HWP 파일의 구조를 정의
struct_def = {
  "header": {
    "size": 4,
    "type": struct.Struct("<I")
  },
  "body": {
    "size": 4,
    "type": struct.Struct("<I")
  }
}

# HWP 파일의 구조를 파싱
header = struct.unpack(struct_def["header"]["type"], data[:struct_def["header"]["size"]])
body = struct.unpack(struct_def["body"]["type"], data[struct_def["header"]["size"]:])

```

위의 코드는 HWP 파일의 구조를 정의하고, struct 모듈을 사용하여 HWP 파일의 구조를 파싱합니다.

필요한 데이터를 추출하는 방법은 HWP 파일의 구조에 따라 다릅니다. 예를 들어, 문단의 내용을 추출하려면 다음과 같은 코드를 사용할 수 있습니다.

``` python
# 문단의 시작 위치를 찾습니다.
paragraph_start = body[0]

# 문단의 끝 위치를 찾습니다.
paragraph_end = body[paragraph_start + 1]

# 문단의 내용을 추출합니다.
paragraph_text = data[paragraph_start + 2:paragraph_end - 1]

```

위의 코드는 문단의 시작 위치와 끝 위치를 찾은 후, 그 사이의 데이터를 문단의 내용으로 추출합니다.

실제로 HWP 3.0 버전 이전의 파일을 읽을 때는 다음과 같은 주의 사항이 있습니다.

- HWP 파일의 구조가 변경되었기 때문에, HWP 5.0 버전 이후의 코드를 사용하여 HWP 3.0 버전 이전의 파일을 읽으려고 하면 오류가 발생할 수 있습니다.
- HWP 3.0 버전 이전의 파일은 한글 코드와 영문 코드가 혼합되어 있는 경우가 많습니다. 따라서, HWP 파일을 읽을 때는 한글 코드와 영문 코드를 구분하여 처리해야 합니다.

다음은 HWP 3.0 버전 이전의 파일을 읽는 예제 코드입니다.

``` python
import io
import struct

def read_hwp(filename):
  # HWP 파일을 바이너리 형식으로 읽습니다.
  with io.open(filename, "rb") as f:
    data = f.read()

  # HWP 파일의 구조를 파싱합니다.
  header = struct.unpack("<I", data[:4])
  body = struct.unpack("<I", data[4:])

  # 문단의 목록을 가져옵니다.
  paragraph_list = []
  for i in range(header[0]):
    paragraph_start = body[i]
    paragraph_end = body[i + 1]
    paragraph_text = data[paragraph_start + 2:paragraph_end - 1]
    paragraph_list.append(paragraph_text)

  return paragraph_list

paragraph_list = read_hwp("test.hwp")
for paragraph in paragraph_list:
  print(paragraph)

```



### hwp 3.0 ~ 5.0 사이의 파일은 어떻게 읽는가?

HWP 3.0 ~ 5.0 사이의 파일은 HWP 5.0 버전의 구조를 기반으로 하기 때문에, HWP 5.0 버전의 코드를 사용하여 읽을 수 있습니다. 다만, 다음과 같은 주의 사항이 있습니다.

- HWP 3.0 ~ 5.0 사이의 파일은 HWP 5.0 버전의 파일과 일부 구조가 다를 수 있습니다. 따라서, 코드를 작성할 때는 HWP 파일의 구조를 확인해야 합니다.
- HWP 3.0 ~ 5.0 사이의 파일은 한글 코드와 영문 코드가 혼합되어 있는 경우가 많습니다. 따라서, HWP 파일을 읽을 때는 한글 코드와 영문 코드를 구분하여 처리해야 합니다.

다음은 HWP 3.0 ~ 5.0 사이의 파일을 읽는 예제 코드입니다.

``` python
import hwp

hwp_file = hwp.HWPFile()
hwp_file.Open("test.hwp")

# 문단의 목록을 가져옴
paragraph_list = hwp_file.GetParagraphList()
for paragraph in paragraph_list:
  # 문단의 내용을 출력
  print(paragraph.GetText())

```

