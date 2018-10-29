### C# 에서 Lambda 사용 하기

<br/>

C# 에서 Lambda(무명 Method) 를 사용하는 방법을 기술 한다.

<br/>

##### 1. 람다 Synyax  
(입력 파라미터) => { 문장블럭 };

예)
``` C# 
str => { MessageBox.Show(str); }

() => Write("No");

(p) => Write(p);

(s, e) => Write(e);

(string s, int i) => Write(s, i);
```

<br/>

##### 2. 이벤트 핸들러에 Lambda 사용 예

<br/>

###### 2.1 일반 이벤트 핸들러 예제

```C#
this.button1.Click += new System.EventHandler(button1_Click);

private void button1_Click(object sender, EventArgs e)
{
    ((Button)sender).BackColor = Color.Red;
}
```

<br/>

###### 2.2 delegate 를 이용한 이벤트 핸들러

``` C#
this.button1.Click += delegate(object sender, EventArgs e)
{
    ((Button)sender).BackColor = Color.Red;
};
```

<br/>

###### 2.3 Lambda 를 이용한 이벤트 핸들러

```C#
this.button1.Click += (sender, e) => ((Button)sender).BackColor = Color.Red;
```



##### - Lambda 를 이용하면 코드가 한결 간단해 진다 -



