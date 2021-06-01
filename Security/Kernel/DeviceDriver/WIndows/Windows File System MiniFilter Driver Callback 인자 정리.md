# Windows File System MiniFilter Driver Callback 인자 정리

출처: http://jmpcall.blogspot.com/2016/05/windows-file-system-minifilter-driver.html



## PFLT_PRE_OPERATION_CALLBACK function pointer

### Syntax

```
typedef FLT_PREOP_CALLBACK_STATUS ( *PFLT_PRE_OPERATION_CALLBACK)(
  _Inout_ PFLT_CALLBACK_DATA    Data,
  _In_    PCFLT_RELATED_OBJECTS FltObjects,
  _Out_   PVOID                 *CompletionContext
);
```

### Parameters

Data [in, out]

- I/O 동작에 대한 콜백 데이터 구조체(FLT_CALLBACK_DATA) 포인터



FltObjects [in]

- 현재 I/O 요청과 관계된 오브젝트에 대한 opaque 포인터를 포함하는 FLT_RELATED_OBJECT 구조체 포인터



CompletionContext [out]

- 만약 콜백 루틴이 FLT_PREOP_SUCCESS_WITH_CALLBACK 또는 FLT_PREOP_SYNCHRONIZE를 리턴하는 경우, 이 파라미터는 컨텍스트 포인터를 post-operation 콜백 루틴으로 전달 할 수 있다. 아닌 경우는, NULL을 설정한다.

### Return value

콜백 루틴은 다음 FLT_PREOP_CALLBACK_STATUS 값들 중 하나를 리턴할 수 있다.



FLT_PREOP_COMPLETE

- 미니필터 드라이버는 I/O 동작에 대해 완료 중이다. 필터 매니저는 드라이버 스택 아래 또는 파일 시스템에 있는 어떠한 미니필터 드라이버로 I/O 동작을 전달하지 않는다. 이 경우, 필터 매니저는 디바이스 스택에서 미니필터 드라이버 위쪽에 있는 post-operation 콜백 루틴만 호출한다.



FLT_PREOP_DISALLOW_FASTIO

- 이 operation은 fast I/O operation이고, 미니필터 드라이버는 이 동작에서 fast I/O operation을 허용하지 않는다는 것이다. 필터 매니저는 드라이버 스택 아래 또는 파일 시스템에 있는 어떠한 미니필터 드라이버로 fast I/O operation을 전달하지 않는다. 이 경우, 필터 매니저는 디바이스 스택에서 미니필터 드라이버 위쪽에 있는 post-operation 콜백 루틴만 호출한다.



FLT_PREOP_PENDING

- 미니필터 드라이버는 I/O operation에 대해 pend 되었고 여전히 pending 상태이다. 필터 매니저는 미니필터 드라이버가 FltCompletePendedPreOperation을 호출하기 전까지 I/O operation을 진행하지 않는다.



FLT_PREOP_SUCCESS_NO_CALLBACK

- 미니필터 드라이버는 추가적인 처리를 위해 필터 매니저로 I/O operation을 리턴한다. 이 경우, 필터 매니저는 미니필터 드라이버의 post-operation 콜백을 호출하지 않는다.



FLT_PREOP_SUCCESS_WITH_CALLBACK

- 미니필터 드라이버는 추가적인 처리를 위해 필터 매니저로 I/O operation을 리턴한다. 이 경우, 필터 매니저는 I/O가 완료되는 동안 미니필터 드라이버의 post-operation 콜백을 호출한다.



FLT_PREOP_SYNCHRONIZE

- 미니필터 드라이버는 추가적인 처리를 위해 필터 매니저로 I/O operation을 리턴한다. 그러나 동작을 완료하지는 않는다. 이 경우, 필터 매니저는 현재 스레드의 컨텍스트에 있는 미니필터 드라이버의 post-operation 콜백을 호출한다.

### Remarks

미니필터 드라이버의 pre-operation 콜백 루틴은 하나 또는 여러 타입의 I/O operation을 처리한다. 이 콜백 루틴은 legacy filter 모델의 dispatch 루틴과 비슷하다.



미니필터 드라이버는 FLT_REGISTRATION 구조체의 OperationRegistration 배열에 콜백 루틴의 엔트리 포인트를 저장함으로써 특정 타입의 I/O operation 대한 pre-operation 콜백 루틴을 등록한다. 미니필터 드라이버는 DriverEntry 루틴에서 FltRegisterFilter의 파라미터로 이 구조체를 전달한다. 미니필터 드라이버는 post-operation 콜백 없이도 pre-operation 등록이 가능하다. 그 반대의 경우도 마찬가지이다.



만약 이 루틴에서 FLT_PREOP_COMPLETE를 리턴하면, 반드시 콜백 데이터 구조체의 IoStatus.Status 필드에 I/O operation에 대한 최종 NTSTATUS 값을 설정해야한다. NTSTATUS 값은 STATUS_PENDING이 될 수 없다. 저 값을 설정하는 경우 cleanup과 close operation은 실패 할 수 없다.



만약 이 루틴에서 FLT_PREOP_DISALLOW_FASTIO를 리턴하면, 콜백 데이터 구조체의 IoStatus.Status 필드는 설정하면 안된다. 왜냐하면 필터 매니저는 자동적으로이 필드를 STATUS_FLT_DISALLOW_FAST_IO로 설정한다.



FLT_PREOP_DISALLOW_FASTIO는 fast I/O operation에서 리턴될 수 있다. 주어진 콜백 데이터 구조체가 fast I/O를 나타내고 있는지 알기 위해선 FLT_IS_FASTIO_OPERATION 매크로를 사용한다.



FLT_PREOP_PENDING은 IRP기반 I/O operation만 pend가 가능하기 때문에 IRP기반 I/O operation에 대해서만 리턴할 수 있다. 주어진 콜백 데이터 구조체가 IRP기반 I/O operation인지 알기 위해선, FLT_IS_IRP_OPERATION 매크로를 사용한다.



만약 미니필터 드라이버의 pre-operation 루틴이 FLT_PREOP_SYNCHRONIZE를 리턴하면, 미니필터 드라이버는 일치되는 post-operation 콜백을 등록해야한다.



FLT_PREOP_SYNCHRONIZE는 IRP기반 I/O operation에 대해 리턴해야한다. 만약 IRP 기반이 아닌 I/O 동작에 대해 리턴을 하게 되면, 미니필터 드라이버는 FLT_PREOP_SUCCESS_WITH_CALLBACK으로 설정된 것으로 처리한다.



미니필터 드라이버는 create operation에 대해 FLT_PREOP_SYNCHRONIZE를 리턴할 수 없다. 왜냐하면 이러한 동작은 이미 필터 매니저에 의해 동기화 되어 있다.



미니필터 드라이버는 비동기 read와 write 동작에 대해 절대로 FLT_PREOP_SYNCHRONIZE를 리턴하면 안된다. 미니필터 드라이버와 시스템 모두에 심각한 성능 저하가 발생할 수 있다. 



미니필터 드라이버의 pre-operation 또는 post-operation 콜백 루틴은 operation에 대한 콜백 데이터 구조체의 내용을 수정할 수 있다. 만약 이러는 경우, 반드시 FltSetCallbackDataDirty 를 호출해야한다.



일반적인 콜백 루틴의 IRQL은 지정된 I/O 경로에 의존적이다.



### 관련 구조체들

### FLT_CALLBACK_DATA Structure Syntax

```
typedef struct _FLT_CALLBACK_DATA {
  FLT_CALLBACK_DATA_FLAGS       Flags;
  const PETHREAD                Thread;
  const PFLT_IO_PARAMETER_BLOCK Iopb;
  IO_STATUS_BLOCK               IoStatus;
  struct _FLT_TAG_DATA_BUFFER  *TagData;
  union {
    struct {
      LIST_ENTRY QueueLinks;
      PVOID      QueueContext[2];
    };
    PVOID  FilterContext[4];
  };
  KPROCESSOR_MODE               RequestorMode;
} FLT_CALLBACK_DATA, *PFLT_CALLBACK_DATA;
```



### Members

Flags

- I/O 동작을 설명하는 bitmask 플래그



Thread

- I/O operation 시작한 스레드 포인터



Iopb

- I/O operation에 대한 파라미터를 포함하는 FLT_IO_PARAMETER_BLOCK 구조체 포인터



IoStatus

- I/O operation에 대한 상태와 정보를 포함하는 IO_STATUS_BLOCK 구조체. 미니필터는 오직 FLT_PREOP_COMPLETE를 리턴하는 preoperation 콜백 루틴과 FLT_POSTOP_FINISHED_PROCESSING을 리턴하는 postoperation 콜백 루틴에서만 이 내용을 수정할 수 있다.



TagData

- I/O operation에 대한 재분석 지점 데이터를 가지고 있는 FLT_TAG_DATA_BUFFER 구조체를 가리킨다. 이 포인터는 post-create path에서만 유효하다. 그러므로 오직 미니필터의 postoperation 콜백 루틴에서만 이 값을 수정할 수 있다.



QueueLinks



QueueContext



FilterContext



RequestorMode



### FLT_RELATED_OBJECTS Structure Syntax

```
typedef struct _FLT_RELATED_OBJECTS {
  const USHORT        Size;
  const USHORT        TransactionContext;
  const PFLT_FILTER   Filter;
  const PFLT_VOLUME   Volume;
  const PFLT_INSTANCE Instance;
  const PFILE_OBJECT  FileObject;
  const PKTRANSACTION Transaction;
} FLT_RELATED_OBJECTS, *PFLT_RELATED_OBJECTS;
```

### Member

Size

- FLT_RELATED_OBJECTS 구조체 바이트 사이즈



TransactionContext

- Transaction 멤버가 NULL이 아닌 경우 트랜젝션 miniversion ID 값을 포함하는 opaque 멤버. 만약 Transaction이 NULL인 경우, TransactionContext는 정의되지 않는다.



Filter

- Operation에 대해 호출된 콜백 루틴을 가진 미니필터 드라이버의 Opaque 필터 포인터.

이 포인터는 고유의 미니필터 드라이버를 식별하고 로드된 미니필터 드라이버의 수를 유지한다.



Volume

- Operation에 관련된 volume에 대한 opaque volume 포인터. 이 포인터는 volume을 식별하고 volume 디바이스 스택의 lifetime 동안 일정하게 유지된다.



Instance

- Operation에 관련된 미니필터 드라이버 instance에 대한 opaque instance 포인터. 이 포인터는 instance를 식별하고 volume에 attach된 instance 수를 유지한다.



FileObject

- Operation에 관련된 file object 포인터



Transaction

- Vista 이후, operation에 관련된 트랜젝션을 위한 opaque transaction 포인터. 

### Remarks

필터 매니저에 의해 할당된 FLT_RELATED_OBJECTS 구조체는 I/O operation 또는 instance setup 또는 teardown operation 에 관련된 오브젝트들의 opaque 포인터를 포함한다.



FLT_RELATED_OBJECTS 구조체의 내용은 필터 매니저에 의해 설정된다. 미니필터 드라이버는 이 구조체를 직접적으로 수정할 수 없다. 하지만, 만약 operation에 대한 FLT_IO_PARAMETER_BLOCK 구조체에서 I/O operation에 대한 대상 instance 또는 대상 I/O operation을 미니필터 드라이버가 수정한 경우, 필터 매니저는 아래있는 미니필터 드라이버로 전달 된 FLT_RELATED_OBJECTS 구조체의 Instance 또는 FileObject를 수정한다.



## PFLT_POST_OPERATION_CALLBACK function pointer

미니필터 드라이버는 I/O operation에 대해 완료 처리를 수행하기 위해 하나 또는 여러개의 PFLT_POST_OPERATION_CALLBACK 타입의 루틴을 등록할 수 있다.

### Syntax

```
typedef FLT_POSTOP_CALLBACK_STATUS ( *PFLT_POST_OPERATION_CALLBACK)(
  _Inout_  PFLT_CALLBACK_DATA       Data,
  _In_     PCFLT_RELATED_OBJECTS    FltObjects,
  _In_opt_ PVOID                    CompletionContext,
  _In_     FLT_POST_OPERATION_FLAGS Flags
);
```

### Parameters

Data [in,out]

- I/O operation에 대한 콜백 데이터 구조체(FLT_CALLBACK_DATA) 포인터



FltObjects [in]

- 필터 매니저가 유지하는 FLT_RELATED_OBJECTS 구조체 포인터. 해당 포인터는 현재 I/O request과 관계된 오브젝트의 opaque 포인터들을 포함한다.



CompletionContext [in, optional]

- 미니필터 드라이버의 pre-operation 콜백 루틴에 의해 리턴된 컨텍스트 포인터. CompletionContext 포인터는 pre-operaion 콜백 루틴에서 post-operation 콜백 루틴으로 정보를 전달하기 위한 방법을 제공한다.



Flags [in]

- post-operation이 어떻게 수행될지에 대한 플래그 값

  FLTFL_POST_OPERATION_DRAINING
  필터 매니저는 미니필터 드라이버 인스턴스가 detach 되고 있고 post-operation 콜백 루틴이 clean up을 위해 호출되었다는 것을 나타내기 위해 이 플래그를 설정한다. Post-operation 콜백은 FLT_POSTOP_FINISHED_PROCESSING을 리턴한다. 만약 이 플래그가 설정된 경우, Data 파라미터는 operation에 대한 오리지널 콜백 데이터 구조체의 복사본을 가리킨다.  이는 오리지널 콜백 데이터 구조체가 아니다. 추가적으로, 이 플래그 설정 됐을 때, post-operation 콜백 루틴은 IRQL <= APC_LEVEL에서 호출된다.

### Return value

이 콜백 루틴은 다음 값들 중 하나를 리턴한다.



FLT_POSTOP_FINISHED_PROCESSING

- 미니필터 드라이버는 I/O operation에 대한 완료 처리가 끝났고 필터 매니저로 operation의 제어를 리턴 중이다.

  post-operation 콜백이 값을 리턴하고 난 후, 필터 매니저는 I/O operation의 완료 처리를 계속 진행한다.



FLT_POSTOP_MORE_PROCESSING_REQUIRED

- 미니필터 드라이버는 I/O operation에 대한 완료 처리를 중단했다. 그러나 필터 매니저로 operation의 제어를 넘기지 않는다.

  미니필터 드라이버의 post-operation 콜백은 만약 미니필터 드라이버의 post-operation 콜백이 I/O 동작이 작업 큐에 올라갔을 때 이 상태 값을 리턴한다. 미니필터 드라이버는 결국 I/O operation의 완료 처리를 다시 시작해야한다.

  post-operation 콜백이 FLT_POSTOP_PROCESSING_REQUIRED를 리턴하고 난 후, 필터 매니저는 다음 두 조건을 만족하지 않는 한 I/O operation의 완료 처리를 수행하지 않는다.

  \1. Post-operation 콜백은 I/O operation을 작업 큐에 올렸다.
  \2. 작업 루틴이 operation에 대한 완료 처리를 수행하고 난 후, FltCompletePendedPostOperation을 호출해 필터 매니저로 operation의 제어를 리턴한다.

  이 상태 값은 오직 IRP기반 I/O operation에서 리턴할 수 있다. 

### Remarks

미니필터 드라이버의 post-operation 콜백 루틴은 I/O operation의 하나 또는 여러 타입에 대한 완료 처리를 수행한다.

Post-operation 콜백 루틴은 legacy file system filter drivers에서 사용하는 완료 루틴과 비슷하다.

Post-operation 콜백 루틴은 임의의 스레드 컨텍스트에서 호출된다. IRQL <= DISPATCH_LEVEL

IRQL_DISPATCH_LEVEL에서 호출될 수도 있기 때문에, 다음과 같은 제한사항이 적용된다.

- 낮은 IRQL에서 실행되어야하는 어떠한 커널 모드 루틴 호출도 안전할 수 없다.
- 해당 루틴에서 사용되는 임의의 데이터 구조체는 반드시 nonpaged pool로 할당되어야한다.
- pageable한 메모리를 만들 수 없다.
- 리소스, 뮤텍스, fast 뮤텍스를 요청할 수 없다. 하지만 spin lock은 요청할 수 있다.
- get, set, delete 컨텍스트를 할 수 없다. 그러나 컨텍스트 해제는 가능하다.

IRQL < DISPATCH_LEVEL에서 수행이 필요한 임의의 I/O 완료 처리는 postoperation 콜백 루틴에서 직접적으로 수행될 수 없다. 대신에, FltDoCompletionProcessingWhenSafe 또는 FltQueueDeferredIoWorkItem과 같은 함수를 호출함으로써 작업 큐에 올려서 사용해야한다.



만약 post-operation 콜백의 Flags 파라미터가 FLTFL_POST_OPERAITON_DRAINING이 설정되어 있는 경우, FltDoCompletionProcessingWhenSafe는 절대로 호출되면 안된다. 다음은 예외 이다.

- 미니필터 드라이버의 pre-operation 콜백 루틴이 IRP기반 I/O operation에 대해 FLT_PREOP_SYNCHRONIZE를 호출한 경우, 일치되는 post-operation 콜백 루틴은 pre-operation 콜백과 같은 스레드 컨텍스트로서 IRQL <= APC_LEVEL에서 호출이 보장된다. 
- IRP_MJ_CREATE operation에서 시작된 스레드 컨텍스트에서 post-create 콜백 루틴은 IRQL_PASSIVE_LEVEL에서 호출을 보장한다. 



jmpcall님이 19th May 2016에 게시

라벨: [FLT_CALLBACK_DATA](http://jmpcall.blogspot.com/search/label/FLT_CALLBACK_DATA) [FLT_POST_OPERATION_CALLBACK](http://jmpcall.blogspot.com/search/label/FLT_POST_OPERATION_CALLBACK) [FLT_PRE_OPERATION_CALLBACK](http://jmpcall.blogspot.com/search/label/FLT_PRE_OPERATION_CALLBACK)[FLT_RELATED_OBJECTS](http://jmpcall.blogspot.com/search/label/FLT_RELATED_OBJECTS)