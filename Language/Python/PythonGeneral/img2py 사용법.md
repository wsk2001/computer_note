# img2py 사용법

출처: https://mindgear.tistory.com/110

**mg2py.py**는 이미지 파일을 소스코드 내에 포함시키기 위하여 소스코드로 변환시켜준다.
img2py.py는 **wxPython**을 설치해야 사용할 수 있다.
파일의 위치는 \Lib\site-packages\wx-2.6-msw-unicode\wx\tools 아래에 존재한다. (wxPython 2.6 버전)



img2py.py - 이미지를 PNG 형식으로 변환하고 적절한 코드가있는 Python 모듈에 포함시켜 런타임에 프로그램에로드 할 수 있습니다.
장점은 파이썬 소스 코드이기 때문에 .pyc로 제공되거나 freeze, py2exe 등을 사용하여 프로그램에 '컴파일'될 수 있다는 것입니다.



Usage:
    img2py.py [options] image_file_name python_file



### Options

-  **-m <#rrggbb>** : 원본 이미지에 마스크 나 투명도가 정의되어 있으면 기본적으로 사용됩니다.
  이 옵션을 사용하면 이미지에 투명으로 표시 할 색상을 지정하여 기본값을 무시하거나 새 마스크를 제공 할 수 있습니다.
-  **-n <name>** : 이미지 액세스 기능에는 일반적으로 일반 이름 (getBitmap 등)이 사용됩니다.
  이 옵션을 사용하면 액세스 기능 (getNameBitmap 등)을 사용자 정의하는 데 사용해야하는 이름을 지정할 수 있습니다.
-  **-c**  : 이미지를 참조하는 데 사용할 수있는 이름 카탈로그를 유지하십시오.
  카탈로그는 모듈의 카탈로그 및 색인 속성을 통해 액세스 할 수 있습니다.
  -n <name> 옵션이 지정되면 <name>이 카탈로그 키 및 색인 값에 사용되며, 그렇지 않으면 경로 또는 확장자가없는 파일 이름이 키로 사용됩니다.
- **-a**  : 이 플래그는 python_file을 추가하도록 지정합니다덮어 쓰지 말고 이것은 -n과 결합하여하나의 Python 소스 파일에 여러 이미지를 넣을 수 있습니다.
-  **-u** : 압축을 사용하지 않습니다. 데이터를 압축하지 않은 상태로 둡니다.
-  **-i** : 또한 이미지를 wxIcon으로 반환하는 함수를 출력하십시오.



**<사용 예제> - Icon**
' -i' 옵션은 아이콘 이미지를 소스코드로 변환할 때 사용한다.
img2py.py -i 'icon_file_name' 'result_file_name'

변환이 정상적으로 이루어졌을 경우 다음과 같은 메시지를 출력한다.
Embedded 'icon_file_name' into 'result_file_name'

'result_file_name'에 생성된 결과는 다음과 같다.

```python
######################################################################################
#----------------------------------------------------------------------
# This file was generated by C:\Python24\Lib\site-packages\wx-2.6-msw-unicode\wx\tools\img2py.py
#
from wx import ImageFromStream, BitmapFromImage
from wx import EmptyIcon
import cStringIO, zlib

def getData():
    return zlib.decompress(
'x\xda\x01\x1d\r\xe2\xf2\x89PNG\r\n\x1a\n\x00\x00\x00\rIHDR\x00\x00\x00 \x00\
\x00\x00 \x08\x06\x00\x00\x00szz\xf4\x00\x00\x00\x04sBIT\x08\x08\x08\x08|\
\x08d\x88\x00\x00\x0c\xd4IDATX\x85=\x97yx\xd4\xf5\xd5\xc5?\xbfe\xf6\xccLf\
\x12\xb2/\x10\x12 \x10\x02\x88\x12\xc5\xb0\x15\x04)\x10\x14E\xaa\xa5\xf0\xbc\
.
.
.
.
\x8d|\xb8\xf0.\xe6U\x1c\xe7\xf7G\x8e\xf0\xfd\xc1\x83\x9c\xdf\xb1\x83\xa4\xa2\
"\xc6,_\x893=\x9d\xff\x00\xfdf\xc8e\'\x1c\x07#\x00\x00\x00\x00IEND\xaeB`\x82\
+l6\x8d' )

def getBitmap():
    return BitmapFromImage(getImage())

def getImage():
    stream = cStringIO.StringIO(getData())
    return ImageFromStream(stream)

def getIcon():
    icon = EmptyIcon()
    icon.CopyFromBitmap(getBitmap())
    return icon
######################################################################################
```



getIcon() 함수를 호출하여 icon 이미지를 사용할 수 있다.

주의점: 'result_file_name'을 지정할 때 현재 작업중인 파일을 지정하면 안됨.
결과를 생성할 때 'result_file_name'의 내용을 날려버리고 새로 작성됨.





**<사용 예제> - jpg or bmp etc..
**-n <name> 옵션은 이미지 파일(jpg, bmp, ...)을 소스코드로 변환 할 때 사용한다.
<name>은 생성된 결과의 함수 이름으로 사용된다. 이미지 파일의 이름을 사용하면 편리함.
(이해가 잘 안되면 생성된 결과의 가장 마지막 함수의 이름을 확인하면 금방 이해할 수 있음.)
실제 입력 할 때 '<>'는 붙이면 안됨. 안됨 -_-;
img2py.py -n 함수이름 'image_file_name' 'result_file_name'

변환이 정상적으로 이루어졌을 경우 다음과 같은 메시지를 출력한다.
Embedded 'image_file_name' using "함수이름" into 'result_file_name'

'result_file_name'에 생성된 결과는 다음과 같다.



```python
######################################################################################
#----------------------------------------------------------------------
# This file was generated by C:\Python24\Lib\site-packages\wx-2.6-msw-unicode\wx\tools\img2py.py
#
from wx import ImageFromStream, BitmapFromImage
import cStringIO, zlib

def get'함수이름'Data():
    return zlib.decompress(
'x\xda4\x9auP\x1b\xdf\xfb\xb6\x13\x8a\x04Op\x97 E\x82K\xb1R$P\xf8\xe0\x85\
\xe0\xee.\xc5\xb58\xc1\x0b\x14w\x87\xe0.\xc5)R\xdc\xb5X)nEZ\x8a\x95\xb7\xdf\
\xdf\xcc\xfb\xd7\xce\xec\xcc\xce\xce\x9c\xd9\xe7\xba\xaf\xfb\xec\x89VW}\x8b\
.
.
.
.
m\x1ec\x19\xfbq\xd1\xfdO8m\x90\x8bL\xeeu]\x97*Q\x03\x1f\xbcy\xfe\x7fz\xa9,\
\x0ex\xcf+\xf7\x00\x00\x00\x00IEND\xaeB`\x82\xa0\xdd}\x0f' )

def get'함수이름'Bitmap():
    return BitmapFromImage(get'함수이름'Image())

def get'함수이름'Image():
    stream = cStringIO.StringIO(get'함수이름'Data())
    return ImageFromStream(stream)
######################################################################################
```



get'함수이름'Image() 함수를 호출하여 이미지를 사용할 수 있다.

주의점: 'result_file_name'을 지정할 때 현재 작업중인 파일을 지정하면 안됨.
결과를 생성할 때 'result_file_name'의 내용을 날려버리고 새로 작성됨.



### 예제

command line 에서 `py img2py.py ~~~` 방법으로 실행 시키는 부분은 쉽게 적용 할 수 있는 예제가 없어서 찾은 예제는 다음과 같다.

```python
from wx.tools import img2py
 
image_file = '110px-Python-logo-notext.svg.png'
python_file = 'python_logo.py'
 
img2py.img2py(image_file=image_file, python_file=python_file,
              imgName='get_python_logo', icon=True)
```

실행 결과 만들어진 파일

**python_logo.py**

```python
from wx.lib.embeddedimage import PyEmbeddedImage
 
get_python_logo = PyEmbeddedImage(
    b'iVBORw0KGgoAAAANSUhEUgAAAG4AAABuCAYAAADGWyb7AAAABGdBTUEAALGPC/xhBQAAACBj'
    b'SFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QA/wD/AP+g'
    b'vaeTAAAAB3RJTUUH4QgKBhw1+kgs7gAADjFJREFUeNrtnX2YVNV5wH/vmVmWRb6CloiIShoB'
    b'g1tBFncBNSBLDM0HSOqagGiMpqkxqSbpYx7ENtPWGNNWik+e+FATtTUfRpeighJtgl3tVmZB'
    b'VIRVqjEGQclGgYfwtR9zz9s/Znd2Z+bOzJ3Z+dqZOX/M7tyPc+59f/e8533f894zwlApgYCZ'
    b'3Vl7jhj/eaJ6HuhEgfFqdQLCeKAGGANqgGqUEaAW5Ei4AnsYBaAD9ADIfsG+p1b3imHnpLfN'
    b'G83NTc5QEYcU64Ut/vrm6oNjQvN8VucrLEB1Vi8cQMMM+j/o206i7RpzDIBGHXMCeFWQrSib'
    b'j46U59sDTd0VcB5Lw+2b5qtlJdhlAmNdBJwLaC7n6lHgKUH+LfgvX2ipgEtQ6ldvXGKU2xXq'
    b'kgk4P9Ditr+myB3b7v7CL0C0Ag6Yu/qpsy3OOpRPphJwgaAN3B60jty4fe2KV8oa3NzVT1xq'
    b'kcdQxg0BaH1fOxG9ue3ulfcVUna+AkN7BmXUEIIGqB/4zMQ5y3h362PPlRW42YGnTherz6GM'
    b'HmLQ+lWV6vwzG6744N3gY9sLIUNTkKelx96FcmoSAStoC+idIP8AbAacYoHWV6cKd9ff/B9/'
    b'VggZ+vOuIgMbxttulkcLqF8YAgestU1t31/WOnB33W0bzvdZ1gNTsw7N7VwPdQpajeFuYFHJ'
    b'9zjt8X0cbJUbNKDbsXZxLDSAF+9cttv6zCKUw8UAbUCdjbNveXBG6YOzTE4ADdCfbPv+sp2J'
    b'zt3+3aX7VLmniKABik+5svTBhWOJrsKwqs+kvGCfPl1M0MIXJfUlD07EaGLr0fzRg+N5pKig'
    b'ha9+QulblaqayOQ3OFNSnW6tTi0maKBgZVgZuAM2oZ+myJcIBJJekyg3FBW08LZQ+ahKN+da'
    b'mFHfWXtHonMvunX9Xyl8urigxW4vUT/Oqqokj4isqv/2+qli5bvBf/7cSwB1tz461SB/I+j1'
    b'FWgFAhcth0RCYpkaXVb/7fUnQS3KKcWnHgsHrWDg0gg51RSdIZKozrKwKvMRMM4ntAJ0vAIY'
    b'JwOlVIE2hNyB0oJWqFEu7+CsDrjdkoBWJlalEfaq8ut4IdlITCuWtLuArWsHjgpFib1ArPxJ'
    b'qUFzE1NJlfpv/uwx0KW5hiaqe9p+8OXzymSMy/NYmquepmXkgOdxRC0INP3Nkkn0hMYnvq6e'
    b'3q5qenA6fyvTW47lBdycbzxaI8P9M6zIVIVTjTXDFLWuAosqTgK52uSCt0mAJNxlUZiSH2gx'
    b'AJ3QrRj9mqvLoNqv6FRBqtH2y/cgbET5kUx/5jfZBRcImIaeGZ9C5QZFF6lSg4YHSRXrMRtL'
    b'EghJEghDEghYCms9qteoiRs0l33CNNBpwLd09+UPUm1uk3N/+f6gx7iG2x9vbOieuQuVjaCf'
    b'RftevghfQF5T6Apt8iesM/nwmtxxj3z3IfYGukM7dfflCzLucYu/vrn68Kief8VyY6IGK9AS'
    b'kNMk7SjJr0GZAPYZbV+4XKZvWZ9Wj6sPbB59eFTPs1CBlja0mMniNKH1fa9C5WFtX/hJz+Bm'
    b'BTaNoLtnEzC3Ai1daHhoJyW0vj9+rPxc2+d/1BO4qi59QODSCrRsQXOJ7qSG1vuhH8Kaf1eN'
    b'DpbEgatf9fh1iF7l3kCJQtPBQFN3fy6ZSvQMLRK7m8fuy65OCG5WYNNpIrI2UQMlC43BQEsx'
    b'xKWafE0JLVLH97T9ymGu4Kq67CpgdAXaIKB5GsfShBYuE+k5eFUcuHm3PnEGwo0VaJlA0zSg'
    b'JdCpyaH1Rln4Rhw4x6/XueV4VKClhqbC8Thdmc445gVa+J+ZunP++bGq8uoKtMx6mlgOu49x'
    b'2YQW2b80Aq5+9cYGlGkVaBmoRwUV/UOM/hvpeRxLC5oCekUEnFH98wq0zKCF3x1gR5SART/k'
    b'zfhIFxqAzNT2OeNMeJteUoGWITTA+qQtpsdN8GYxpgst7NTRU1VnpgceHQZ6UQVaZtBA9k/u'
    b'ODUYOeLFWVUotWlZjN6h9cVBZptRXcNmAiMq0DKBBmB/HLV4W8246UBNRsaHF2jhh6XOiDC1'
    b'Ai1TaPoHJ+RfGy1tWZJbaACc63esThYq0DKAhsJf77jvK0diJL48IzPfMzQFy1l+g05WT1MR'
    b'5QYt+ZgkcNe2e296JEpy7Y0XA1PS983UO7RwRscoo/CRCrS0of1j2703rYrvKuY7OYfW+78f'
    b'BiyAVoGWCtrvFf5y2703bYrTbu2NTag2ZgwN79AA/KiOCKdqVaAlFrrsB/vjqs7ha/73geuP'
    b'xkHb84nJhHTdoKClmd7gRxhRgRYHzVF4DWErlubtH+54lkDANZlT9yw6gxC/gt5oyaCg4Tkn'
    b'xR9edLpooO0DtqO8gmiHWj0sRo4INiy02DcdQuGN/U6UDNwRXyS2Eun9Gj7eOL5OR5331fHt'
    b'23HfV06Qomh740xCbAA9JyvQUskrZowbUWBoR1HuN0Yf2rrm2pcZAkXfahxDl6zCcgtQ7SkW'
    b'mabxkWpayA/qEFm3Mr/QBO5X61/dds/yjqKHpQFDe3AWYlfSqSviVrXNI7TwGAcngWF5hnZc'
    b'RK8Prrn2kZQCe3PZmfhC56GMB04DGe767oCNfLgXx/UEl68DXxSRsWDHAdN47YWZiI5KO+qf'
    b'LrQ4Abi35QftBMbkEVo32GXBNV/8L/cn+0ofb/csBL0G5DJwJvS/K9BXryS4JkksSEkhSHF5'
    b'wsVmkJHF4KCl7HkRVcmJfKpHVfnitrXx0PTNxdX4h3+VvT3fBM6kz0fxdKPZMMPxKLhM284e'
    b'tD5wJ/NoiPxk29qVD8cP9ktqMeZnoLVR0/6eb5QcqqwCQCN1WwbL7/ME7SQhc1sctN8u+QzG'
    b'tIHWDjJdO7vQtIDQNPXD6Rdhn+bHT/tR2w+u3h8N7YpFGDag6k9fbQwFaGQJWtw+axTeyYdz'
    b'Lcq6OGtR+EVxQktg2mVtPPWaSJRw33tGxO7LQ0Tk1eDaa16Puh6//hPouPR1fZ4sOtenP4/Q'
    b'kratbxknxP/lPvYoLVH3tHfJx0A/n9SiK7pxpmiggbLHnDJKXgq7pzkMGFuN/jUMNdcR9dpQ'
    b'ERsHFBAaCTSKSKtpCTQdA92Tyyi/MTZ6SXqrS9PyzQoJTXPkI6Z7L1Grrpvn+1LQt+dyasbp'
    b'Nu/1x/+vHAf8aUYOdd6h5dBHzBQa/E7qWt8x4ckNfTqX82nbfnjNof6YYdcF9K2DkTNoZAna'
    b'IHIfsxntGbjP6kboTUEfftz3FNCZC2gRZyDSsO+0jKIghRxntIDQ4trWhyLgWu5tOgZsydnM'
    b'dXSYfmxhxpligTYI9QuvS0Pbjgi4cJ+wD+Q23SBSqockNHLoI3q+F4n8rlAE3KR3qp5QeCvH'
    b'0AZ3M4Wy6JL2vHxB421G/vHBOHDNzU0OomuzD02TsssoClLwcSbv0EBYJdPbu+PAAYwYecY6'
    b'YBdaDNA8LnqWT2jk0N1Idi/KBrkoGJUtEAWuJbAghHKz62iaLWg2iw51vqFlkEaXhft8Bytf'
    b'jhVj3AI1bWtW/DdwV7bzHrMeBcm3Reep7axDex/VxTJ366GU4ADO2jfsbwV9MjfQyI5DXYhx'
    b'xovFnT1o+3BkoTS0veYmPldwzc1NztHRwz+H6obBQtOsQBtcunZWoeUnJ2ULVVWzZO7WXYme'
    b'+4TLHrYHmrprxky6SkT/vjf3cvBp4dmOgpQMNPpNfmQ5FwUXyYX/k/kKsS2BBaHgmmsDYmUe'
    b'Smt2oNnsOdSFgpZVK5lDWH0E1aXsnXSu1G99WCT1YlOe1mQO3nNNG3DJ7JsfnG2EFcB8lKnA'
    b'8PSgkT1oXp5+5UaMHAwnwzrJr0f6pgedFIfKgD/Jjh0w3SgDDnIAUQfkID7e5cKtb/WDasNr'
    b'SWsV9O33XLcdCE+KBgLm4o5zx/TvPeK9onzlPtrQk1Lbsp8SLJn/7kAgYFuJWQrJS3GiJwty'
    b'mvtYwqWwv/SR8zS60i3+0oWmYHy7dVejjfsRpWzOKvRag1LXOqv0weUv93FM7+RjbqD1n3eo'
    b'vFTloKAVWRpd6YNzShGalE+PK6XcR7VlAq5o0+jIbnpDSRknNkaxZHNeK3thrTdADqF6Dujp'
    b'KaGJSOmDy6lxMGhov8TYW2RG6xu9pwgvXXw58EOUjyRJYiojcMWXRtfMzOc/L9Lv8IVjiK1P'
    b'a7B+Ln5/G3C2a/02/+rSVKABcAxrvjoQ2sAiDW0dqH4rYf1SDlalalXOoGVqBKn+WupaPkh6'
    b'3dK1EdVj7vVLGYAzejJnFl2maXTCvlSXLXU7ehAOuNdvKYcet78Icx/He9DuJnJcfP2mDHpc'
    b'dRCNCp8UPvdRWKTt80cmve4XGy4jEvuMrU+6Sh6cTN30AcKvcgYtk9xH1XF0Ot9L2Nt2fuIU'
    b'MGuSGFVvlIdVabmz+HIf9Wu6Y946fXn+2Kgmgpd8jO5jz6Jam6StJ/PeAQrmxrUveghkZRFm'
    b'ZJ3oTYw6SPjN2TpUTZK2OjhR81FZ0HKsPMDt+ewonJMvAOcP0dzH8E4rfyFzghvKQ1UCMm3j'
    b'UaxtRHl9aEBzGTOt/F0hoBUUHIDUbumgp2cuqo9nHRo59BHREMpNMid4R8FkRxEUVYTdC78E'
    b'3AGcnoHKGtQSgmlCewUr18uc4EuFlJkpBnAiqNRuuZ+qziko30E5UHzQpB3sCvaeVVdoaEXT'
    b'4+J64IuzqqgefQUqK1BdCJySEbRYMOlDO4TynxjzU2a/0JooCF0B5wbxzcXVHO/6ODgLEbkQ'
    b'ZQZwWhaXEOyDpMDvQHYBz6Oh59h79svS1OwUo1yKHpwrzF2XTqLHNwWciYhORJiAyoexjEXU'
    b'hENTGNDRoMdALMoJRLtQ6QJ9H+wBLB2IdOA4b1Jzco9c8OrxoSKD/weQ8i7cArqaQAAAACV0'
    b'RVh0ZGF0ZTpjcmVhdGUAMjAxNy0wOC0xMFQwNjoyODo1MyswMDowMAPxhVgAAAAldEVYdGRh'
    b'dGU6bW9kaWZ5ADIwMTctMDgtMTBUMDY6Mjg6NTMrMDA6MDByrD3kAAAAAElFTkSuQmCC')
```



이 파일은 GUI 코드로 가져 와서 프레임 아이콘 또는 단추 등으로 표시하기 위해 이미지로 다시 변환 할 수 있습니다.

```python
import wx
 
from python_logo import get_python_logo
 
 
class ImageFrame(wx.Frame):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.SetIcon(get_python_logo.GetIcon())
        self.main_logo_image = wx.Image(get_python_logo.getImage())
        self.panel = wx.Panel(self)
        self.create_main_logo_static_bitmap()
        self.create_spin_button()
        self.add_sizers()
        self.SetInitialSize((300, 250))
 
    def create_main_logo_static_bitmap(self):
        self.static_bitmap = wx.StaticBitmap(
            self.panel, bitmap=self.main_logo_image.ConvertToBitmap())
 
    def create_spin_button(self):
        scaled_image = self.main_logo_image.Scale(
            32, 32, wx.IMAGE_QUALITY_HIGH)
        button_bitmap = scaled_image.ConvertToBitmap()
        self.spin_button = wx.Button(self.panel, label='Spin Logo')
        self.spin_button.SetBitmap(button_bitmap)
        self.spin_button.SetBitmapMargins(2, 2)
        self.spin_button.SetInitialSize()
        self.spin_button.Bind(wx.EVT_BUTTON, self.on_spin_button)
 
    def add_sizers(self):
        frame_sizer = wx.BoxSizer(wx.VERTICAL)
        frame_sizer.Add(self.panel, 1, wx.EXPAND)
        self.SetSizer(frame_sizer)
 
        main_sizer = wx.BoxSizer(wx.VERTICAL)
        self.panel.SetSizer(main_sizer)
        main_sizer.AddSpacer(20)
 
        image_sizer = wx.BoxSizer(wx.HORIZONTAL)
        image_sizer.AddStretchSpacer(1)
        image_sizer.Add(self.static_bitmap)
        image_sizer.AddStretchSpacer(1)
        main_sizer.Add(image_sizer, flag=wx.EXPAND)
        main_sizer.AddSpacer(20)
 
        button_sizer = wx.BoxSizer(wx.HORIZONTAL)
        button_sizer.AddStretchSpacer(1)
        button_sizer.Add(self.spin_button)
        button_sizer.AddStretchSpacer(1)
        main_sizer.Add(button_sizer, flag=wx.EXPAND)
 
        main_sizer.AddSpacer(20)
 
    def on_spin_button(self, event):
        self.main_logo_image = self.main_logo_image.Rotate90()
        self.static_bitmap.SetBitmap(self.main_logo_image.ConvertToBitmap())
 
 
if __name__ == '__main__':
    wx_app = wx.App(False)
    frame = ImageFrame(None, title='Image Frame')
    frame.Show()
    wx_app.MainLoop()
```



`주의`

```
위 코드중 get_python_logo.getImage() 부분은 get_python_logo.GetImage() 로 수정 하여야 한다.
그렇지 않으면 실행시 wxPyDeprecationWarning 가 나온다.
```





### 끝

