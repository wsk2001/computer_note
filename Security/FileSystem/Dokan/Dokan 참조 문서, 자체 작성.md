# Dokan 참조 문서, 자체 작성



``` c++
typedef struct _DOKAN_FILE_INFO {
  /**
   * 작업 간에 정보를 전달하는 데 사용할 수 있는 컨텍스트입니다.
   * 컨텍스트는 구현이 이벤트의 요청 컨텍스트를 이해하는 데 도움이 되는 c HANDLE, struct, int, 내부 참조와 같은 모든 유형을 전달할 수 있습니다.
   */
  ULONG64 Context;
    
  /** 예약된. Dokan 라이브러리에서 내부적으로 사용합니다. 절대 수정하지 마세요. */
  ULONG64 DokanContext;
    
  /** DokanMain 또는 DokanCreateFileSystem에 전달된 DOKAN_OPTIONS에 대한 포인터. */
  PDOKAN_OPTIONS DokanOptions;
    
  /**
   * 예약된. Dokan 라이브러리에서 내부적으로 사용합니다. 절대 수정하지 마세요.
   * 이벤트 처리에 추가 데이터가 연결되어야 하는 경우 해당 데이터에 대한 포인터를 여기에 배치할 수 있습니다.
   */
  PVOID ProcessingContext;
    
  /**
   * 주어진 I/O 작업을 원래 요청한 스레드의 프로세스 ID입니다.
   */
  ULONG ProcessId;
    
  /**
   * 디렉토리 파일을 요청합니다.
   * 파일이 폴더인 경우 DOKAN_OPERATIONS.ZwCreateFile에 설정해야 합니다.
   */
  UCHAR IsDirectory;
    
  /** DOKAN_OPERATIONS 동안 파일을 삭제해야 하는 경우 플래그를 지정합니다. 정리 이벤트. */
  UCHAR DeleteOnClose;
    
  /** 읽기 또는 쓰기가 페이징 IO입니다. */
  UCHAR PagingIo;
    
  /** 읽기 또는 쓰기는 동기식 IO입니다. */
  UCHAR SynchronousIo;
    
  /** 캐시 없이 데이터 소스에서 직접 읽기 또는 쓰기 */
  UCHAR Nocache;
    
  /**  TRUE이면 Offset 매개변수를 사용하는 대신 파일의 현재 끝에 씁니다. */
  UCHAR WriteToEndOfFile;
  
} DOKAN_FILE_INFO, *PDOKAN_FILE_INFO;
```

