# DOKAN_OPTION

출처: https://dokan-dev.github.io/dokany-doc/html/group___d_o_k_a_n___o_p_t_i_o_n.html#ga60a028554f3d30088c13204a70f700aa



DOKAN_OPTIONS.Options에서 사용되는 모든 DOKAN_OPTION 플래그. 더...



## Macros

```
#define DOKAN_OPTION_DEBUG   1
#define DOKAN_OPTION_STDERR   (1 << 1)
#define DOKAN_OPTION_ALT_STREAM   (1 << 2)
#define DOKAN_OPTION_WRITE_PROTECT   (1 << 3)
#define DOKAN_OPTION_NETWORK   (1 << 4)
#define DOKAN_OPTION_REMOVABLE   (1 << 5)
#define DOKAN_OPTION_MOUNT_MANAGER   (1 << 6)
#define DOKAN_OPTION_CURRENT_SESSION   (1 << 7)
#define DOKAN_OPTION_FILELOCK_USER_MODE   (1 << 8)
#define DOKAN_OPTION_CASE_SENSITIVE   (1 << 9)
#define DOKAN_OPTION_ENABLE_UNMOUNT_NETWORK_DRIVE   (1 << 10)
#define DOKAN_OPTION_DISPATCH_DRIVER_LOGS   (1 << 11)
#define DOKAN_OPTION_ALLOW_IPC_BATCHING   (1 << 12)
```



## 상세 설명

DOKAN_OPTIONS.Options에서 사용되는 모든 DOKAN_OPTION 플래그.

See also [DOKAN_FILE_INFO](https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___f_i_l_e___i_n_f_o.html)



### 매크로 정의 문서

***#define DOKAN_OPTION_ALLOW_IPC_BATCHING  (1 << 12)***

```
단일 이벤트 대신 드라이버에서 이벤트 배치를 가져와 병렬로 실행합니다. 이 옵션은 CPU 수가 적은 컴퓨터와 요청을 처리하는 데 시간이 걸리는 사용자 영역 파일 시스템(예: 원격 저장소)에서만 사용해야 합니다.
```



***#define DOKAN_OPTION_ALT_STREAM  (1 << 2)***

```
file-name : stream-name 형식으로 대체 스트림 경로를 사용할 수 있습니다. 이것이 지정되지 않으면 드라이버는 콜론이 있는 경로에 액세스하려는 모든 시도에 실패합니다.
```



***#define DOKAN_OPTION_CURRENT_SESSION  (1 << 7)***

```
현재 세션에서만 드라이브 마운트
```



***#define DOKAN_OPTION_DEBUG  1***

```
출력 디버그 메시지 활성화
```



***\#define DOKAN_OPTION_DISPATCH_DRIVER_LOGS  (1 << 11)***

```
커널 드라이버 전역 및 볼륨 로그를 사용자 영역에 전달합니다. 단일 스레드가 활성화된 경우 매우 느릴 수 있습니다.
```



***#define DOKAN_OPTION_ENABLE_UNMOUNT_NETWORK_DRIVE  (1 << 10)***

```\
탐색기를 통해 네트워크 드라이브 마운트 해제 허용
```



<span style="color:red">***#define DOKAN_OPTION_FILELOCK_USER_MODE  (1 << 8)***</span>

```
Lockfile/Unlockfile 작업을 활성화합니다. 그렇지 않으면 Dokan이 처리합니다.
```



***#define DOKAN_OPTION_MOUNT_MANAGER  (1 << 6)***

```
Windows 마운트 관리자를 사용하십시오. 이 옵션은 더 나은 시스템 통합을 위해 사용하는 것이 좋습니다.
드라이브 문자가 사용 중이지만 사용 중인 경우 마운트 관리자가 할당하고 DOKAN_OPERATIONS::Mounted 매개변수에 새 마운트 지점이 포함됩니다.
```



***#define DOKAN_OPTION_NETWORK  (1 << 4)***

```
네트워크 드라이브 사용 - Dokan 네트워크 공급자를 설치하고 DOKAN_OPTIONS::UNCName을 제공해야 합니다.
```



***#define DOKAN_OPTION_REMOVABLE  (1 << 5)***

```
이동식 드라이브 사용 일부 환경에서는 사용자 영역 응용 프로그램이 드라이브와의 통신이 거부되어 원치 않는 마운트 해제가 발생한다는 점에 유의하십시오.
```

See also [Issue #843](https://github.com/dokan-dev/dokany/issues/843)



***#define DOKAN_OPTION_STDERR  (1 << 1)***

```
stderr에 디버그 메시지 출력 활성화
```



***#define DOKAN_OPTION_WRITE_PROTECT  (1 << 3)***

```
마운트 드라이브를 쓰기 방지로 활성화
```



### - 끝 -