# Python 으로 QR 코드 생성 프로그램 작성

출처: https://qiita.com/kensaka1228/items/f2e4ec7048dba77ccc45



파이썬으로 QR 코드를 만드는 프로그램을 제작했습니다.
기록으로 게시합니다.

다음이 제조한 프로그램의 이미지입니다.
[![2023-06-10 (1).png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F2759758%2F9891706c-4693-8562-9821-ddb3a989b097.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=0f3a62d36bd5effb357500cfed80f347)](https://camo.qiitausercontent.com/e2f89ffa0e53194da0be399c6786aa309116e058/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323735393735382f39383931373036632d343639332d383536322d393832312d6464623361393839623039372e706e67)

## 기능 설명

URL을 입력합니다.
QR 코드의 이름을 입력합니다.
QR 코드 생성 버튼을 클릭하면 QRname으로 입력한 문자로 QR 코드가 이미지로 생성된다.

## 프로그램 코드

```bash
import qrcode
import tkinter

def btn_click():
    S = txt_1.get()
    qr_name = txt_2.get()
    img = qrcode.make(S)
    img.save(f'{qr_name}.png')

# 화면 만들기
tki = tkinter.Tk()
tki.geometry('460x190')
tki.title('QR 코드 작성')
tki.configure(bg='SpringGreen2')

lbl_1 = tkinter.Label(text='URL')
lbl_1.place(x=70, y=50)

lbl_1 = tkinter.Label(text='QRname')
lbl_1.place(x=70, y=100)

txt_1 = tkinter.Entry(width=30)
txt_1.place(x=130, y=50)

txt_2 = tkinter.Entry(width=30)
txt_2.place(x=130, y=100)

# ボタン
btn = tkinter.Button(tki, text='QR 코드 생성', command=btn_click)
btn.place(x=180, y=150)
tki.mainloop() # 앱 시작
```

실제로 작성한 QR 코드는 다음과 같습니다.
[![키타 마이 페이지.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F2759758%2Ff672702c-a838-b96b-deba-8a0e893088f8.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=a5ff64a5fe574ff11f35fa0ddfa6757b)](https://camo.qiitausercontent.com/179b46117225c8f5f662a81ae9bf8c012d9bb9ea/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f323735393735382f66363732373032632d613833382d623936622d646562612d3861306538393330383866382e706e67)

부디 참고해 주세요! !



**코멘트**

덧붙여서, qrcode 모듈을 인스톨 하면 qr 커멘드도 인스톨 되고 있어, 커멘드로 표시·생성할 수도 있습니다.

```bash
$ type qr
qr is /usr/bin/qr
$ qr "Hello, wrold!"
█████████████████████████████
█████████████████████████████
████ ▄▄▄▄▄ █▄▀ ▀▀█ ▄▄▄▄▄ ████
████ █   █ █▄▄▀▀ █ █   █ ████
████ █▄▄▄█ █▀▄   █ █▄▄▄█ ████
████▄▄▄▄▄▄▄█▄▀ █ █▄▄▄▄▄▄▄████
████ █▄▀▀▀▄▄█▄███▀█ ▀▀▄█ ████
████▄▄▄ ▄▄▄▀█▄█ ▄▀██▄▀▄█▄████
██████▄█▄█▄█ █▄▄ ██▄  ▀▄█████
████ ▄▄▄▄▄ █▄▄▄▄▄ █▄▄ ▄▀▄████
████ █   █ ██▀▄█  ▀█▀ ▀██████
████ █▄▄▄█ █▄█▀█▄▀█   ▄▄▄████
████▄▄▄▄▄▄▄█▄▄▄██▄███▄██▄████
█████████████████████████████
▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
$ qr "Hello, wrold!" > hello.png
$ file hello.png
hello.png: PNG image data, 290 x 290, 1-bit grayscale, non-interlaced
```