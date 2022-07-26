# CreateFile, ReadFile, WriteFile, OVERLAPPED io

출처: https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=maenan&logNo=70019680871



Windows 98/NT 에서 직렬 데이타를 전송 또는 수신하기 위해서는 win32 통신 API를 사용한다. 모뎀을 제어하기 위한 프로그램이나 전화선을 제어하는 프로그램을 작성하기 위해서는 직렬통신의 이해가 필수적이라 할 수 있다. 직렬 포트를 제어하기 위한 프로그램을 작성하기 위해서는 먼저 직렬 통신의 기초 지식 특히, 보드 레이트(baud rate), 시작과 정지 비트, 패리티 등과 같은 기초 개념을 이해하고, 또한 RS-232 표준 컴퓨터 포트의 기초 작동과 신호에 대해 익숙해야 한다. *RS-232에 관련된 자료는 자료실에 올려놓았으니 참고 하시기 바랍니다.

win32에서의 직렬통신은 16비트 윈도우에서의 경우와는 근본적으로 다르다. 16비트 직렬 통신에서는 통신을 위한 많은 펑션들이 제공된다. 이들 함수들은 각각 OpenComm, CloseComm, ReadComm, WriteComm이 된다. Windows 98에서 직렬 포트와 다른 통신 장치들은 파일로 취급된다. 직렬 포트는 파일에서 사용되는 동일한 함수를 사용해서 열고, 닫고, 읽고, 쓸 수 있다.

 

이 문서는 윈도우즈 엔티와 윈도우즈 98 에서 상호 호환되는 프로그램 개발자 인터페이스에 촛점을 맞춘다. 윈도우즈 98에서는 전화 API(TAPI)를 지원 하지만 윈도우 NT 3.x는 그렇지 않다. 그래서 이 자료에서는 TAPI에 대한 설명을 포함하지는 않는다. TAPI는 모뎀 인터페이스하는 응용프로그램 개발에 많은 도움을 준다.

이번 장에서는 포트 열기와 닫기, 읽기와 쓰기(논오버렙 과 오버렙)에 대해서만 설명하겠다.



<span style="color:red">**포트 열기**</span>

CreateFile 펑션으로 통신 포트를 연다. 통신 포트를 여는데는 2가지(논오버렙 과 오버렙)방식이 있다. 다음의 소스가 오버렙을 통신 자원을 여는 것이다.

 ``` c++
 HANDLE hComm;
 
 hComm = CreateFile(gszPort,
                     GENERIC_READ | GENERIC_WRITE,
                     0,
                     0,
                     OPEN_EXISTING,
                     FILE_FLAG_OVERLAPPED,
                     0);
 
 if  (hComm == INVALID_HANDLE_VALUE)
                             // error opening port; abort
 ```

<span style="color:red">CreateFile을 호출할때 FILE_FLAG_OVERLAPPED를 없애고 호출하면 논오버렙 작업이 된다.</span> 다음 절에서 오버렙과 논오버렙 작업에 대하여 설명한다.

win32 소프트웨어 개발자 참고서에 보면 통신 포트를 열기 위해 CreateFile을 호출하는데 다음과 같은 요구사항이 있음을 알 수 있다.



<span style="color:red">fdwShareMode 는 반드시 0이어야만 한다.</span>

통신 포트들은 파일을 공유하는 식으로 공유될 수 없다. 응용프로그램이 TAPI의 펑션들을 사용하는 경우에는 응용프로그램 서로간에 공유될 수 있다. TAPI를 사용하지 않는 윈32 응용프로그램에서는 통신 포트를 제어하기 위하여는 핸들을 계승하거나 복제가 필요하다. 핸들의 복제는 이 문서의 핵심 사항이 아니므로 생략한다.

<span style="color:red">fdwCreate 는 반드시 OPEN_EXISTING 플래그여야 한다.</span>

<span style="color:red">fTemplateFile 파라미터는 반드시 NULL이어야 한다.</span>

 

COM1, COM2, COM3, COM4와 같은 이름의 포트 이름들이 있는데, win32의 API는 시스템에 이러한 포트들이 있는지 검색하는 메카니즘을 제공하지 않는다. 어떤 시스템은 4개 이상의 포트를 가지고 있기도 하다. 하드웨어 공급자나 직 렬 장치 드라이버 제작자는 이러한 포트들의 이름을 자유로이 정할 수 있다. 이러한 이유로 사용자가 직접 사용하고자 하는 포트를 지정하는 것이 최선의 방법이다. 만약 포트가 존재하지 않는 경우에는 포트 열기를 시도한 이후에 오류(ERROR_FILE_NOT_FOUND)가 발생할 것이고, 사용자에게 이 사실을 알려 포트가 사용할 수 없음을 알게 한다.



<span style="color:red">**포트 닫기**</span>

직렬 포트를 닫는 것은 여는 것보다 훨씬 간단하다. CreateFile이 반환한 핸들을 유일한 파라미터로 사용하는 CloseHandle을 호출하면 된다. 사용을 마치면 항상 직렬 포트를 닫아야 한다. 포트를 닫는 것을 잊으면 열린 채로 있게 되고, 다른 응용프로그램이 이 포트를 열거나 사용할 수 없게 된다.

``` c++
CloseHandle(hComm);
```



<span style="color:blue">**읽기와 쓰기**</span>

통신 포트를 통한 읽기와 쓰기는 win32에서는 매우 간단하다. 사실상 파일 입 출력하는 방법과 같다. win32에서 입출력에는 두가지 방법이 있는데 하나는 오버렙과 논오버렙이다. win32 SDK에서는 동기통신과 비동기통시에 대하여도 설명하고 있다. 그러나 여기서는 오버렙과 논어버렙에 대하여만 설명한다.

논오버렙 입출력은 대다수의 개발자들이 사용하는 방법이다. 작업이 요구되면 작업이 끝나야만 펑션이 종료된다. 오버렙 입출력의 경우는 작업이 종료 되지 않았다고 하더라도 즉시 펑션이 종료된다. 프로그램은 이 시간을 이용 하여 백그라운드 작업을 할 수 있다.

win32에서의 읽기와 쓰기는 16비트 윈도우와는 전혀 다르다. 16비트 윈도우에서는 ReadComm과 WriteComm 펑션만이 있다. win32에서는 많은 읽고 쓰기 펑션들이 있다.



<span style="color:blue">**논오버렙 입출력**</span>

논오버렙 입출력은 제한사항을 갖기 때문에 매우 직선적이다. 하나의 작업이 시작되면 호출한 쓰레드는 붙잡힌다. 하나의 작업이 끝나면 펑션은 종료되고 쓰레드는 작업을 계속할 수 있다. 이 타입의 입출력은 멀티쓰레드 응용프로 그램들에서 많이 사용된다. 왜냐하면 하나의 쓰레드가 입출력을 위해 붙잡히더라도 다른 쓰레드는 계속 작업을 할 수 있다. 만약 하나의 쓰레드가 입출력 작업을 위해 붙잡히면 뒤따라 다른 쓰레드 들도 원래 작업이 종료될 때까 지 붙잡힌다. 예를 들어, 하나의 쓰레드가 ReadFile펑션을 종료하는 동안 대기 중일때 다른 쓰레드가 WriteFile펑션을 호출한다면 붙잡히게 된다.

논오버렙과 오버렙 작업 방식의 호환성을 생각할때 오버렙 방식은 좋은 선택이 아니다. 왜냐면 대부분의 운영체제가 지원하지 않는다. 대부분의 운영체제는 멀티쓰레딩과 같은 류를 제공한다. 그래서 멀티쓰레드 방식의 논오버렙 입출력이 이러한 이유로 인해 가장 좋은 선택이된다.



<span style="color:blue">**오버렙 입출력**</span>

오버렙 입출력은 논오버렙과 달리 직선적이지 않지만 많은 유연성과 효과를 갖는다. 오버렙 방식으로 하나의 포트를 열면 그 작업이 진행중인 동안 같은 시간에 다른 작업을 할 수 있다. 더 나아가 싱글 쓰레드에서 많은 작업을 동 시에 처리할 수 있다.

싱글 쓰레드와 멀티 쓰레드 양쪽 모두는 처리 요구를 하는 것과 그 결과를 처리하는데 있어서 작업의 결과가 유효해 질때까지 하나의 쓰레드는 붙잡혀야만 한다.

오버렙 입출력 작업은 2개의 파트를 갖는다. 작업의 시작과 종료의 확인이다. OVERLAPPED구조를 생성하고, 동기통신에 대한 이벤트를 생성하고, 관련 펑션(ReadFile또는 WriteFile)을 호출. 입출력 작업은 즉시 종료될 수도 그 렇지 않을 수도 있다. 만약 작업이 즉시 종료되면 다른 작업을 즉시할 수 있다. 두번째 파트는 데이타가 어떻게되었는지 처리가 완료되었는지 파악하는것이다. 처리 중에 타임아웃이 걸렸는지 등을 파악한다.



<span style="color:red">**읽기**</span>

ReadFile 펑션은 읽기 작업을 한다. ReadFileEx또한 읽기 작업을 하는데 다른점은 윈도우 95에서는 사용할 수 없는 것이다. 이점에 대하여는 이 자료에서 설명하지 않는다. 다음은 읽기 요구를 어떻게 처리하는지 보여준다. 주의 할 것은 펑션은 ReadFile이 TRUE를 되돌려 줄때에만 그 데이타를 처리한다. 이것은 오버렙에서와 같은 펑션 호출이다. fWaitingOnRead 플래그가 코드에 정의되어 있고 그것은 오버렙된 읽기 작업인지 아닌지를 나타낸다. 그것은 외부에서 새로이 읽기 조작이 일어나는 것을 미리 예방하는데 사용되어진다.

``` c++
DWORD dwRead;

BOOL fWaitingOnRead = FALSE;
OVERLAPPED osReader = {0};

// Create the overlapped event. Must be closed before exiting
// to avoid a handle leak.

osReader.hEvent = CreateEvent(NULL, TRUE, FALSE, NULL);

if (osReader.hEvent == NULL)
   // Error creating overlapped event; abort.

if (!fWaitingOnRead) {
   // Issue read operation.
   if (!ReadFile(hComm, lpBuf, READ_BUF_SIZE, &dwRead, &osReader)) {
      if (GetLastError() != ERROR_IO_PENDING)     // read not delayed?
         // Error in communications; report it.
      else
         fWaitingOnRead = TRUE;
   }
   else {
      // read completed immediately
      HandleASuccessfulRead(lpBuf, dwRead);
    }
}
```

두번째 파트의 오버렙 작업은 작업의 종료여부를 확인하는 것이다. OVERLAPPED 구조체 안의 이벤트 핸들은 WaitForSingleObject펑션에 전달되어서 객체가 신호가 올때까지 기다리게 한다. 이벤트 신호가 오면 작업은 종료 된다. 이것은 성공적으로 종료되었다는 것을 의미하지는 않고, 단지 종료되었다는 것이다. GetOverlappedResult가 작업의 결과를 레포트 해준다. 만약오류가 발생하였다면 GetOverlappedResult는 FALSE를 돌려주고 GetLastError 가 오류 코드를 되돌려 준다. 만약 작업이 성공적으로 끝났다면 GetOverlappedResult는 TRUE를 돌려준다.

> 주의 : GetOverlappedResult는 작업이 종료되었는지 실패된 상태인지를 알수 있다. GetOverlappedResult가 FALSE를 돌려주고 GetLastError가 ERROR_IO_INCOMPLETE이면 그 작업은 종료된 것이 아니다. 또한 GetOverlappedResult는 작업이 종료될 때까지 붙잡을 수 있다. bWait파라미터와 같은 것에 TRUE를 넘겨 줌으로써 오버렙과 논오버렙을 전환할 수 있다.

여기 중첩 읽기 작업에 대하여 종료를 확인하는 소스가 있다. 소스 코드는 호출되었을 때 즉시 종료되었는지 검사하기 위해 똑같은 함수를 호출한다.
물론 fWaitingOnRead 플래그를 사용할 수도 있다.

``` c++
#define READ_TIMEOUT      500      // millisecondsDWORD dwRes;

if (fWaitingOnRead) {
   dwRes    =    WaitForSingleObject(osReader.hEvent,    READ_TIMEOUT);

   switch(dwRes)
   {
        // Read completed.
        case WAIT_OBJECT_0:
            if (!GetOverlappedResult(hComm, &osReader, &dwRead, FALSE))
             // Error in communications; report it.
            else
             // Read completed successfully.
                 HandleASuccessfulRead(lpBuf, dwRead);
            // Reset flag so that another opertion can be issued.
            fWaitingOnRead = FALSE;
            break;

        case WAIT_TIMEOUT:
          // Operation isn't complete yet. fWaitingOnRead flag isn't
          // changed since I'll loop back around, and I don't want
          // to   issue another  read until   the first  one finishes.

           // This is a good time to do some background work.
          break;
        default:
          // Error in the WaitForSingleObject; abort.
          // This indicates a problem with the OVERLAPPED structure's
          // event handle.
        break;
    }
}
```



<span style="color:red">**쓰기**</span>

통신 포트를 통해 데이타를 송신하는 것은 읽기에서와 거의 유사하다. 그것은 많은 똑같은 API를 사용한다. 다음에 그 예이다.

``` c++
BOOL WriteABuffer(char * lpBuf, DWORD dwToWrite)
{
  OVERLAPPED osWrite = {0};
  DWORD dwWritten;
  DWORD dwRes;
  BOOL fRes;

  // Create this write operation's OVERLAPPED structure's hEvent.
  osWrite.hEvent = CreateEvent(NULL, TRUE, FALSE, NULL);
  if (osWrite.hEvent == NULL)
    // error creating overlapped event handle
    return FALSE;

    // Issue write.
    if (!WriteFile(hComm, lpBuf, dwToWrite, &dwWritten, &osWrite)) {
      if (GetLastError() != ERROR_IO_PENDING) {
        // WriteFile failed, but isn't delayed. Report error and abort.
        fRes = FALSE;
      }
      else
        //Write is pending.
        dwRes = WaitForSingleObject(osWrite.hEvent, INFINITE);
        switch(dwRes)
        {
            // OVERLAPPED structure's event has been signaled.
            case WAIT_OBJECT_0:
            if   (!GetOverlappedResult(hComm,   &osWrite,   &dwWritten, FALSE))
                fRes = FALSE;
            else
                // Write operation completed successfully.직렬 포트 닫기

               fRes = TRUE;
            break;
            default:
                // An error has occurred in WaitForSingleObject.
                // This usually indicates a problem with the
                // OVERLAPPED structure's event handle.
            fRes = FALSE;
            break;
        }
      }
    }
    else
      // WriteFile completed immediately.
      fRes = TRUE;

    CloseHandle(osWrite.hEvent);
    return fRes;
}
```

주의할 것은 WaitForSingleObject에서 INFINITE를 지정하면 작업이 종료될때까지 영원히 기다린다.

주의를 요할 것이 있는데 이전의 중첩처리가 완료되지 않았음에도 불구하고 OVERLAPPED 구조체를 재사용한다는 것이다. 만약 새로운 중첩 처리가 필요한데 아직 이전의 중첩처리가 끝나지 않았다면 새로이 구조체를 생성하여 사용 하여야만 한다. 구조체를 생성할 때는 역시 hEvent 멤버도 새로 만들어야 한다. 중첩 작업이 끝나면 그 구조체와 그 이벤트도 해제하여 다시 사용할 수 있도록 한다.

OVERLAPPED 구조체에서 직렬 통신을 위해 수정되어야 하는 것은 오로지 hEvent멤버 뿐다. 다른 멤버들은 모두 0으로 초기화될 것이다. 다른 멤버를 수정하는 것은 직렬 통신에서는 필요하지 않다.



<span style="color:red">**직렬 상태**</span>

직렬 통신 포트의 상태를 알아내는 방법은 두 가지다. 첫 번째 방법은 이벤트 마스크를 사용하여 이벤트가 발생한 것을 알아내는 방법이다. SetCommMask 함수는 이벤트 마스크를 설정하고, WaitCommEvent는 지정한 이벤트가 발생할 때까지 기다린다.
이 함수들은 16비트 윈도우에서의 SetCommEventMast와 EnableCommNotification과 비슷한데 단지 윈32 함수는 WM_COMMNOTIFY메시지를 보내주지 않는다는 점이 다르다. 사실상 WM_COMMNOTIFY메시지는 윈32에서는 없다. 두 번째 방법은 거의 차이점이 없다.



<span style="color:red">**통신 이벤트**</span>

통신 이벤트는 통신 포트를 사용하는 중에 계속 발생한다. 이 이벤트를 알아내는 방법은 2단계가 있다

 

(1) SetCommMask로 알고자 하는 이벤트를 설정한다.

(2) WaitCommEvent 로 상태를 체크한다. 상태 체크는 중첩/비중첩 작업 어디서든 사용할 수 있다.

 

여기서 이벤트라고 하는 것은 통신포트 이벤트만을 말한다. 동기통신을 위한 이벤트 객체에 대한 것이 아니다.
여기에 SetCommMask함수의 예제가 있다

 ``` c++
 DWORD dwStoredFlags;
 
 dwStoredFlags = EV_BREAK | EV_CTS | EV_DSR | EV_ERR | EV_RING | EV_RLSD | EV_RXCHAR | EV_RXFLAG | EV_TXEMPTY;
 
 if (!SetCommMask(hComm, dwStoredFlags))  
   // 통신 마스크 설정 오류
 ```



나머지는 통신과 관련된 작업 이므로 생략... 필요시 원문을 볼것.

