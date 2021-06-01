# SO_REUSEPORT 소켓 옵션

출처: https://lwn.net/Articles/542629/

<span style="color:blue">Linux Kernel 3.9 개발주기에 병합 된 기능 중 하나는 SO_REUSEPORT 소켓 옵션에 대한 TCP 및 UDP 지원이었습니다.</span> 그 지원은 Tom Herbert에 의해 일련의 패치로 구현되었습니다. 새로운 소켓 옵션을 사용하면 동일한 호스트의 여러 소켓을 동일한 포트에 바인딩 할 수 있으며 멀티 코어 시스템에서 실행되는 멀티 스레드 네트워크 서버 응용 프로그램의 성능을 향상시킬 수 있습니다.

SO_REUSEPORT의 기본 개념은 충분히 간단합니다. 여러 서버 (프로세스 또는 스레드)가 각각 다음과 같이 옵션을 설정하면 동일한 포트에 바인딩 할 수 있습니다.

``` c
    int sfd = socket(domain, socktype, 0);

    int optval = 1;
    setsockopt(sfd, SOL_SOCKET, SO_REUSEPORT, &optval, sizeof(optval));

    bind(sfd, (struct sockaddr *) &addr, addrlen);
```

<span style="color:blue">첫 번째 서버가 소켓을 바인딩하기 전에 이 옵션을 설정한 경우 여러 다른 서버도 동일한 포트에 바인딩 할 수 있습니다. 첫 번째 서버가 이 옵션을 지정해야한다는 요구 사항은 포트 하이재킹 (불량 애플리케이션이 수신 연결 또는 데이터 그램 (일부)을 캡처하기 위해 기존 서버에서 이미 사용하는 포트에 바인드 할 가능성)을 방지합니다.</span> 원치 않는 프로세스가 SO_REUSEPORT를 사용하여 이미 서버에 의해 바인드 된 포트를 가로채는 것을 방지하려면 나중에 해당 포트에 바인드하는 모든 서버에 소켓에서 첫 번째 바인드를 수행하는 데 사용 된 유효 사용자 ID와 일치하는 유효 사용자 ID가 있어야합니다. 

SO_REUSEPORT는 TCP 및 UDP 소켓 모두에서 사용할 수 있습니다. TCP 소켓을 사용하면 일반적으로 서로 다른 스레드에있는 여러 개의 청취 소켓을 동일한 포트에 바인딩 할 수 있습니다. 그러면 각 스레드는 accept ()를 호출하여 포트에서 들어오는 연결을 수락 할 수 있습니다. 이는 단일 소켓에서 들어오는 연결을 허용하는 다중 스레드 서버에서 사용하는 기존 접근 방식에 대한 대안을 제시합니다.

<span style="color:blue">전통적인 접근 방식 중 첫 번째는 모든 수신 연결을 수락 한 다음 처리를 위해 다른 스레드로 전달하는 단일 리스너 스레드를 갖는 것입니다. 이 접근 방식의 문제점은 극단적 인 경우 청취 스레드가 병목 현상이 될 수 있다는 것입니다. </span>SO_REUSEPORT에 대한 초기 토론에서 Tom은 초당 40,000 개의 연결을 허용하는 응용 프로그램을 다루고 있다고 언급했습니다. 이런 종류의 숫자를 감안할 때 Tom이 Google에서 일한다는 사실을 아는 것은 놀라운 일이 아닙니다.

단일 포트에서 작동하는 다중 스레드 서버에서 사용하는 전통적인 접근 방식 중 두 번째는 모든 스레드 (또는 프로세스)가 다음 형식의 간단한 이벤트 루프에서 단일 수신 소켓에서 accept () 호출을 수행하도록하는 것입니다.

``` c
    while (1) {
        new_fd = accept(...);
        process_connection(new_fd);
    }
```

Tom이 지적했듯이 이 기술의 문제점은 여러 스레드가 accept () 호출에서 대기 중일 때 wake-up이 공정하지 않아서 높은로드에서 들어오는 연결이 매우 불균형 한 상태로 스레드에 분산 될 수 있다는 것입니다. 구글에서 그들은 가장 많은 연결을 받아들이는 쓰레드와 가장 적은 연결을 받아들이는 쓰레드 사이에 3 분의 1 차이를 보았습니다. 이러한 불균형은 CPU 코어의 활용도를 떨어 뜨릴 수 있습니다. 반대로 SO_REUSEPORT 구현은 동일한 포트의 accept ()에서 차단 된 모든 스레드 (또는 프로세스)에 연결을 균등하게 분산합니다.

TCP와 마찬가지로 SO_REUSEPORT를 사용하면 여러 UDP 소켓을 동일한 포트에 바인딩 할 수 있습니다. 예를 들어 이 기능은 UDP를 통해 작동하는 DNS 서버에 유용 할 수 있습니다. SO_REUSEPORT를 사용하면 각 스레드는 자체 소켓에서 recv ()를 사용하여 포트에 도착하는 데이터 그램을 받아 들일 수 있습니다. 전통적인 접근 방식은 모든 스레드가 단일 공유 소켓에서 recv () 호출을 수행하기 위해 경쟁하는 것입니다. 위에서 설명한 두 번째 전통적인 TCP 시나리오와 마찬가지로 이로 인해 스레드 전체에 불균형 부하가 발생할 수 있습니다. 반대로 SO_REUSEPORT는 모든 수신 스레드에 데이터 그램을 균등하게 배포합니다.

Tom은 기존의 SO_REUSEADDR 소켓 옵션이 이미 여러 UDP 소켓을 동일한 UDP 포트에 바인딩하고 데이터 그램을  허용한다고 언급했습니다. 그러나 SO_REUSEPORT와 달리 SO_REUSEADDR은 포트 하이재킹을 방지하지 않으며 수신 스레드에 데이터 그램을 균등하게 배포하지 않습니다.

Tom의 패치에 대한 두 가지 주목할만한 점이 있습니다. 이들 중 첫 번째는 구현의 유용한 측면입니다. 들어오는 연결과 데이터 그램은 연결의 4- 튜플을 기반으로하는 해시를 사용하여 서버 소켓에 배포됩니다. 즉, 피어 IP 주소 및 포트와 로컬 IP 주소 및 포트가 포함됩니다. 예를 들어, 클라이언트가 동일한 소켓을 사용하여 일련의 데이터 그램을 서버 포트로 전송하는 경우 해당 데이터 그램은 모두 동일한 수신 서버로 전달됩니다 (계속 존재하는 한). 이렇게하면 클라이언트와 서버간에 상태 저장 대화를 쉽게 수행 할 수 있습니다.

<span style="color:blue">또 다른 주목할 점은 현재 TCP SO_REUSEPORT 구현에 결함이 있다는 것입니다. 새 서버가 시작되거나 기존 서버가 종료되어 포트에 바인딩 된 청취 소켓 수가 변경되면 3 방향 핸드 셰이크 중에 들어오는 연결이 끊어 질 수 있습니다. </span> 문제는 연결 요청이 핸드 셰이크 중에 초기 SYN 패킷이 수신 될 때 특정 청취 소켓에 묶여 있다는 것입니다. 포트에 바인딩 된 서버 수가 변경되면 SO_REUSEPORT 논리가 핸드 셰이크의 최종 ACK를 올바른 수신 소켓으로 라우팅하지 않을 수 있습니다. 이 경우 클라이언트 연결이 재설정되고 서버는 고아 요청 구조로 남아 있습니다. 문제에 대한 해결책은 아직 작업 중이며 여러 청취 소켓간에 공유 할 수있는 연결 요청 테이블을 구현하는 것으로 구성 될 수 있습니다.

SO_REUSEPORT 옵션은 표준이 아니지만 여러 다른 UNIX 시스템 (특히 아이디어가 시작된 BSD)에서 유사한 형식으로 사용할 수 있습니다. 멀티 코어 시스템에서 실행되는 네트워크 응용 프로그램에서 최대 성능을 끌어내는 데 유용한 대안을 제공하는 것으로 보이므로 일부 응용 프로그램 개발자에게는 환영 할만한 추가 기능이 될 것입니다.

```
Ubuntu 계열은 거의 3.9 이상 CentOS 는 7.8 의 kernel version 이 3.10 임.
```



`Example`

``` c++
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <stdbool.h>
#include <arpa/inet.h>
#include <pthread.h>

void* do_work(void *arg)
{
    int *port = (int *) arg;

    int listen_socket = socket(AF_INET, SOCK_STREAM, 0);
    int one = 1;
    setsockopt(listen_socket, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &one, sizeof(one));

    struct sockaddr_in serv_addr;
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(*port);

    int ret = bind(listen_socket, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
    if (ret == -1)
    {
        printf("Failed to bind to port\n");
        return NULL;
    }

    listen(listen_socket, 5);

    struct sockaddr_in cli_addr;
    memset(&cli_addr, 0, sizeof(cli_addr));
    int addr_length = sizeof(cli_addr);

    do
    {
        int cli_sock = accept(listen_socket, (struct sockaddr *) &cli_addr, (socklen_t *) &addr_length);
        close(cli_sock);
    } while (true);

    close(listen_socket);

    return 0;
}

int main(int ac, const char *av[])
{
    if (ac != 2)
    {
        printf("Usage: liser <port>\n");
        return 1;
    }

    int port = atoi(av[1]);

    const int MAX_THREADS = 10;
    pthread_t tid[MAX_THREADS];
    for (int i = 0; i < MAX_THREADS; i++) {
        pthread_create(&tid[i], NULL, do_work, &port);
    }

    for (int i = 0; i < MAX_THREADS; i++) {
        pthread_join(tid[i], NULL);
    }
    return 0;
}
```

