# 힙 오버플로우를 통한 fastbin 컨트롤

출처: https://bpsecblog.wordpress.com/2016/08/31/translate_fastbin/

요새 CTF 포너블 문제에서 힙(Heap)에서 일이 터지는 바이너리들을 좀 많이(…) 본 것 같습니다. 덕분에 힙알못인 저는 매우 고난이었는데. 최오리군도 그랬나봅니다ㅋㅋ

![20150401_551badd2bb318.jpeg](https://bpsecblog.files.wordpress.com/2016/08/20150401_551badd2bb318.jpeg?w=740)
어느날 갑자기 자발적으로 ‘이거 번역하겠다’고 fastbin 관련 글을 하나 들고 온 기특한 중국어 능력자 최오리군(눈물)…누나는 너가 번역한다는데 말리지 않는다ㅋㅋ

여하튼, 번역 해두었길래 퍼다 날라봅니다!

> **원문 : [ ](https://www.91ri.org/15788.html)**

------

저번에 RCTF를 참가했을 때 shanxian이라는 이름의 힙 오버플로우 문제를 푼 적이 있었다. 취약점 자체는 명확하게 보였지만 힙 오버플로우에 익숙하지 않아서 이를 활용할 방법을 찾지 못하였다. 대회가 끝난 후에 어느 팀의 라이트업을 보고난 다음에야 힙 오버플로우를 통해 fastbin를 컨트롤해야한다는 것을 알았다. 이와 관련된 자료가 비교적 적어서, 관련된 내용을 정리해 보고자 한다. 이 글을 통해 이 부분에 흥미가 있는 친구들에게 도움이 되었으면 한다.

------

#### 1.배경 지식

###### 1.1 PTMALLOC

다들 알다시피 C언어에서는 malloc을 사용하여 동적 메모리 할당을 진행한다. GNU C(glibc)에서는 dlmalloc 기반의 ptmalloc을 사용한다. ptmalloc의 기본적인 아이디어는 힙에 있는 여러 메모리 구역을 여러 개의 청크(chunk)로 나누어 메모리를 할당(allocation)하고, 해제(free)할 때 청크를 구분하고 회수하는 작업 등을 진행하는 것이다.

구체적으로 얘기하자면, 각각의 청크들은 유저에게 반환되는 mem 외에, 청크의 크기와 관련된 정보를 저장하는 헤더 부분을 가지고 있다. 32bit 시스템에서 청크의 크기는 8byte이며, 각각의 청크의 크기 역시 8 byte의 배수이다. 전형적인 청크의 예시는 다음 그림과 같다.

![fastbin01.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin01.jpg?w=740)

청크의 헤더 부분은 prev_size와 size 파트로 구성되어 있다.

- **prev_size** : 만약 지금의 청크와 인접한 이전의 청크가 아직 사용되지 않았다면, prev_size는 이정 청크의 크기이다.
- **size** : 현재 청크의 크기이다. 청크의 크기가 8의 배수이기 때문에 이 사이즈의 마지막 3bit는 다름 정보를 저장하는 데 사용된다. 우리가 기억해야 할 부분은 제일 낮은 비트이다. 즉, 사진에서의 P 부분이다. 이 부분은 이전 청크가 사용되었는 지 여부(PREV_INUSE)를 저장한다.
  만약 현재의 청크가 사용 상태라면 mem의 앞 8 바이트는 아래와 같이 다른 정보를 저장하는 데 사용된다.
- **fd** : 아직 사용되지 않은 다음 청크의 주소
- **bk** : 아직 사용되지 않은 이전 청크의 주소

위에서도 볼 수 있듯이, 청크 헤더에 포함되어 있는 크기 정보는 주로 메모리에 인접해 있는 청크의 주소를 얻어오는 데 사용된다(현재 청크의 주소에서 청크의 크기를 빼면 이전 청크의 주소가 된다. 현재 청크의 주소에서 현재 청크의 크기를 더하면 다음 청크의 주소가 된다.)는 것을 알 수 있다. 그러나 mem에서의 fd와 bk는 현재 청크가 사용되고 있지 않을 때에만 의미가 있다. 만약 이 구조를 이해하였다면 아직 사용되지 않은 청크들이 fd, bk를 통해 링크드리스트로 구성된다는 것을 알 수 있을 것이다. 사실상, malloc은 확실히 메모리 분배와 회수에 사용되는 이 일련의 링크드 리스트를 관리하고, 이런 링크드 리스트들을 **“bins”**라고 한다.

일반적으로, 각각의 bin 링크드 리스트의 청크들은 모두 같거나 비슷한 크기를 가진다. bin 안에 있는 청크의 크기를 토대로 bin을 fastbin, unsorted bin, small bin, large bin으로 나눌 수 있다. 우리가 오늘 볼 부분이 바로 **fastbin**이다.

------

###### 1.2 FASTBIN

fastbin에 저장된 청크의 크기는 16byte, 24byte, 32byte…., 80byte이다. 비교적 작은 메모리(mem <= 64byte)를 할당할 때에는 먼저 할당하려는 사이즈와 같은 크기의 사용하지 않는 청크가 fastbin에 존재하는 지를 검사한다. 만약 존재한다면 바로 fastbin에서 빼오고 반환한다. 만약 없다면 다른 방식(top chunk 절단)을 통해 크기가 적합한 청크를 얻어와 반환한다.

하나의 청크를 프리(free)할 때에도, 먼저 해당 청크의 크기가 fastbin의 범위 안에 있는 지를 검사한다. 만약 있다면 그에 대응하는 bin에 넣는다. 이름을 보면 뜻을 알 수 있다고, fastbin은 비교적 작은 사이즈의 청크들을 분배하고 회수한다. 그렇기 때문에 위에서 얘기한 bk에 대한 작업은 진행하지 않는다. 그저 fd만 사용하여 더블 링크드 리스트가 아닌 싱글링크드 리스트를 구성하며 LIFO 원칙을 준수한다.

예를 들어, 만약 지금 40byte 크기의 fastbin에 이미 0x0804a000에 위치하는 청크가 포함되어 있다고 가정하자.

![fastbin02.jpg](https://bpsecblog.files.wordpress.com/2016/08/fastbin02.jpg?w=740)

만약 다른 40byte 크기의 0x0804a028에 위치하는 청크가 프리되면, 이는 같은 fastbin으로 돌아가게 된다. 구체적으로 얘기하자면, 0x0804a028이 이 fastbin의 첫 번째 청크가 되고 이전의 0x0804a000에 위치한 첫 번째 청크는 0x08040a28의 fd에 저장된다.

![fastbin03.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin03.png?w=740)

이어서 malloc을 호출하여 32byte의 메모리(실제 크기는 40byte인 청크)를 할당할 때, fastbin의 첫 번째 청크인 0x0804a028은 옮겨지고 반환된다. 이 때 fastbin의 첫 번째 청크는 fd의 위치인 0x0804a028에 저장된 0x0804a000으로 바뀐다. 그런 다음 이전의 상태로 돌아온다.

당연하지만, 실제 할당과 프리를 진행할 때에는 대상 청크의 크기에 대해 검사를 진행한다. 그러나 만약 fd의 내용을 수정할 수 있다면, 그 다음에 malloc을 진행할 때에는 수정된 주소로 반환된다. 이 부분의 문제로 인해서 임의의 주소에 임의의 내용을 쓸 수 있게 될 수도 있다(write-anything-anywhere).

------

#### 2. 실제 예시

만약 이러한 취약점이 존재하는 간단한 프로그램을 만든다면 다음과 같을 것이다.

``` c++
 
int size = 40 | 0x1;
  
  
int main(int argc, char *argv[]) {
    void *buf0, *buf1, *buf2;
    buf0 = malloc(32);
    buf1 = malloc(32);
  
  
    free(buf1);
    free(buf0);
  
  
    buf0 = malloc(32);
    read(0, buf0, 64);
    buf1 = malloc(32);
  
  
    buf2 = malloc(32);
  
  
    printf("buf2 is at %p\n", buf2);
  
  
    return 0;
}
```

위에서 볼 수 있듯이, 이 코드에서 read를 호출할 때, buf0에 쓰는 데이터가 원래의 크기보다 커서 힙 오버플로우가 일어난다. 우리는 이 취약점을 사용하여 인접한 청크의 fd를 조작할 수 있고 buf2를 할당할 때 fastbin으로부터 비정상적인 주소를 반환 받는다.

구체적으로 얘기하면 read를 호출하기 전에, fastbin의 구조는 아래 사진과 같다.


![fastbin04.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin04.jpg?w=740)

그러나 만약 우리가 read를 사용하여 오버플로우를 일으켜서 뒤에 있는 buf1에 있는 fd의 내용을 수정한다면, 그 다음에 buf1=malloc(32)를 실행할 때, 0x0804a028은 fastbin에서 빠지면서 반환된다. 그러나 수정된 fd가 fastbin에 저장되면서 이것이 가리키는 가짜 청크는 그 뒤에 실행되는 buf2=malloc(32)에서 반환된다. 주의해서 볼만한 것은, 할당할 때 fastbin에서 가져온 청크의 크기가 요구에 부합하는 지 여부를 검사한다는 것인데, 따라서 우리가 만들어놓은 위조 청크의 사이즈 역시 0x29여야 한다. 마침 값이 0x29인 전체 변수가 존재하여 우리는 이 변수의 주소에서 4 뺀 0x080497e8을 가까 청크의 주소로 사용할 것이다.

![fastbin05.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin05.jpg?w=740)

출력된 결과를 보면 알 수 있듯이 buf2는 0x080497f0에 할당되고 이 부분이 가짜 청크와 대응되는 메모리 부분이라는 것도 알 수 있다.

![fastbin06.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin06.png?w=740)

일반적으로 우리는 우리가 할당한 메모리에 데이터를 쓴다. 그렇기 때문에 만약 malloc이 반환하는 주소를 제어할 수 있다면, write-anything-anywhere를 실현할 수 있다.

#### 3. House of Spirit

사실상, 리눅스에서의 힙 오버플로우를 이용한 공격들은 이미 10년 전부터 많은 사람들이 연구를 진행해 왔다. 2005년, “The Malloc Maleficarum”이라는 글에서는 다음과 같이 5 가지의 힙 공격 방식을 제기하였다.

- **The House of Prime**
- **The House of Mind**
- **The House of Force**
- **The House of Lore**
- **The House of Spirit**
- **The House of Chaos**

그리고 2009년의 **[Phrack 66기에서는 “Malloc Des-Maleficarum”이라는 글이 실렸는데](http://www.phrack.org/archives/issues/66/10.txt)**, 여기에서는 이 몇 가지 기술들에 대한 좀더 심층적인 내용을 다루었다.

여기에서 House of Spirit은 fastbin과 관련이 있다. 잠깐 House of Spirit의 간단한 설명을 해보겠다.

**House of Spirit의 최종적인 효과는 공격자가 만든 가짜 청크를 fastbin을 통해 반환하게 하는 것이다.** 앞의 예시에서의 힙 오버플로우를 통한 fd overwrite와 다른 것은, House of Spirit은 free의 목표 주소를 수정함으로써 가짜 청크를 fastbin에 넣고, 그 뒤에 malloc을 통해 가짜 청크를 반환하게 한다는 것이다.

예를 들어 만약 아래와 같은 코드가 존재한다고 가정하자.

``` c
int main(int argc, char *argv[]) {
    void *p = malloc(32);
    char buf[8];
    read(0, buf, 0x80);
 
    free(p);
    malloc(32);
}
```

위에서 볼 수 있듯이 데이터를 read하여 buf에 저장할 때, 오버플로우를 통해 포인터인 p의 내용을 조작할 수 있다. 만약 스택의 주소를 안다면 read를 할 때 가짜 청크를 만들고 p를 가짜 청크에 대응하는 메모리 주소로 수정할 수 있다. 테스트 환경에서 buf는 0xffffd704에 위치하고 있었고, 가짜 청크의 크기는 40byte로 0xffffd728(청크의 주소는 8의 배수에 위치한다)에 위치하고 있었다. 또한 이에 대응하는 메모리는 0xffffd730에 위치한다.

이 외에도 glibc에서 free할 때, 뒤에 인접한 청크의 크기를 검사한다. 아래 사진의 3901, 3902번째 줄에서 볼 수 있다.

![fastbin07.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin07.png?w=740)

그렇기 때문에 우리는 가짜 청크 뒤에 또 하나의 다른 가짜 청크가 필요하다. 그저 크기가 너무 크거나 너무 작지만 않으면 된다. 제일 간단한 방식은 아래 사진과 같다.

![fastbin08.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin08.png?w=740)

payload1을 입력으로 하여 gdb를 디버깅해서 보면 free(p) 이후에 fastbin의 내용은 아래와 같다.

![fastbin09.png](https://bpsecblog.files.wordpress.com/2016/08/fastbin09.png?w=740)

보면 알 수 있듯이, 0xfffff728에 위치한 가짜 청크가 그에 대응하는 fastbin에 존재한다는 것을 확실히 알 수 있다. 그리고 그 다음의 malloc(32)에서 반환된 주소는 스택 상의 가짜 메모리인 0xffffd730이다.

------

#### 4. 확장

관련 기술들을 연구한 다음에 필자는 이런 방식이 리얼월드 익스플로잇에서 사용되었는 지를 찾아보았다. 하지만 아쉽게도 이 방식을 직접적으로 사용한 익스플로잇은 찾을 수 없었다.(필자가 검색한 방법이 정확하지 않을 수 있다. 만약 발견하였다면 필자에게 알려주길 바란다.)

그러나 만약 우리가 이 문제를 좀 더 추상적으로 보면 이러한 기술을 활용하는 것의 **본질**은 이렇다.

allocator에서 필요한 청크 정보가 오버플로우와 같은 방식에 의해 조작됨으로써 그 후에 비정상적인 주소에 할당되게 되는 것이다. 만약 이러한 방식으로 다시 본다면, 제일 먼저 세상을 들썩이게한 **[GHOST(CVE-2015-0235)](https://cve.mitre.org/cgi-bin/cvename.cgi?name=cve-2015-0235)** 취약점이 생각나게 된다. 이 취약점에 대한 **[심층적인 분석은 여기](https://www.qualys.com/2015/01/27/cve-2015-0235/GHOST-CVE-2015-0235.txt)**를 보면 되기 때문에 여기에서 더 설명하지는 않고,  메모리 할당에 관련된 부분에 대해서만 돌아보도록 하겠다.

GHOST취약점 활용의 일환으로 “write-anything-anywhere”이 있다. 이 부분에서는 Exim의 메모리 할당 과정에서 트리거되었다. 구체적으로는 Exim은 자신이 구현한 메모리 할당 방법에서 아래와 같은 구조체를 사용하였다.

``` c++
typedef struct storeblock {
  struct storeblock *next;
  size_t length;
} storeblock;
```



이전의 오버플로우를 통해서 이 구조체를 덮을 수 있고 next 포인터를 조작할 수 있다. 결과적으로 조작된 주소는 다음 메모리 블럭이 할당될 때 사용되어 메모리 할당 후 반환된다. 이러한 작업이 진행되고 나면 “wrtie-anything-anywhere”을 할 수 있게 된다.

------

#### 5. 마무리

힙 오버플로우의 활용 방법은 매우 많다. 여기에서 소개한 fastbin말고도 유명한 unlink라는 것도 있다. 비록 이를 활용하는 방법의 세세한 부분은 다르지만, 대부분은 할당 메커니즘 내부에서 사용하는 구조체의 내용이 수정되는 것에서 시작한다. 이 글을 통해서 독자들이 많이 공유하고 토론하였으면 좋겠다.

------

#### 6. 참고

**[Understanding glibc malloc](https://sploitfun.wordpress.com/2015/02/10/understanding-glibc-malloc/)**

**[x86 Exploitation 101 : This is the first witchy house](https://gbmaster.wordpress.com/2014/08/24/x86-exploitation-101-this-is-the-first-witchy-house/)**

**[The Malloc Maleficarum – Glibc Malloc Exploitation Techniques](http://packetstorm.foofus.com/papers/attack/MallocMaleficarum.txt)**

**[Malloc DES-Malificarum](http://phrack.org/issues/66/10.html)**

**[Qualys Security Advisory CVE-2015-0235 – GHOST : glibc gethostbyname buffer overflow](https://www.qualys.com/2015/01/27/cve-2015-0235/GHOST-CVE-2015-0235.txt)**

*-translated by fandu*