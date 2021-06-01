# 콘솔 창 핸들 (HWND) 구하는 방법

출처:<https://support.microsoft.com/en-us/help/124103/how-to-obtain-a-console-window-handle-hwnd>

## 요약

콘솔 응용 프로그램과 연관된 창을 조작하는 것이 유용 할 수 있습니다. Win32 API는 콘솔 응용 프로그램과 연결된 창 핸들을 가져 오는 직접적인 방법을 제공하지 않습니다. 그러나 FindWindow ()를 호출하여 창 핸들을 얻을 수 있습니다. 이 함수는 클래스 이름이나 창 이름을 기반으로 창 핸들을 검색합니다.

현재 콘솔 제목을 확인하려면 GetConsoleTitle ()을 호출하십시오. 그런 다음 FindWindow ()에 현재 콘솔 제목을 제공하십시오.



## More Information

여러 창이 같은 제목을 가질 수 있으므로 현재 콘솔 창 제목을 고유 한 제목으로 변경해야합니다. 이렇게하면 잘못된 창 핸들이 반환되지 않습니다. SetConsoleTitle ()을 사용하여 현재 콘솔 창 제목을 변경하십시오. 다음은 프로세스입니다.

- GetConsoleTitle ()을 호출하여 현재 콘솔 창 제목을 저장합니다.
- 콘솔 제목을 고유 한 제목으로 변경하려면 SetConsoleTitle ()을 호출하십시오.
- Sleep(40)을 호출하여 창 제목이 업데이트될 수 있도록 기다립니다.
- FindWindow (NULL, uniquetitle)를 호출하여 HWND를 가져 오면이 호출은 HWND를 반환하거나 작업이 실패하면 NULL을 반환합니다.
- 원래 창 제목을 복원하려면 SetConsoleTitle ()을 1 단계에서 검색 한 값으로 호출하십시오.

결과 HWND를 테스트해야합니다. 예를 들어, HWND에서 GetWindowText ()를 호출하고 결과를 GetConsoleTitle ()과 비교하여 반환 된 HWND가 현재 프로세스와 일치하는지 여부를 테스트 할 수 있습니다.

결과로 나오는 HWND가 모든 창 핸들 작업에 적합하지는 않습니다.



## Sample Code

다음 함수는 현재 콘솔 응용 프로그램 창 핸들 (HWND)을 검색합니다. 함수가 성공하면 반환 값은 콘솔 창의 핸들입니다. 함수가 실패하면 반환 값은 NULL입니다. 일부 오류 검사는 간결성을 위해 생략되었습니다.

```c++
HWND GetConsoleHwnd(void)
   {
       #define MY_BUFSIZE 1024 // Buffer size for console window titles.
       HWND hwndFound;         // This is what is returned to the caller.
       char pszNewWindowTitle[MY_BUFSIZE]; // Contains fabricated
                                           // WindowTitle.
       char pszOldWindowTitle[MY_BUFSIZE]; // Contains original
                                           // WindowTitle.

       // Fetch current window title.

       GetConsoleTitle(pszOldWindowTitle, MY_BUFSIZE);

       // Format a "unique" NewWindowTitle.

       wsprintf(pszNewWindowTitle,"%d/%d",
                   GetTickCount(),
                   GetCurrentProcessId());

       // Change current window title.

       SetConsoleTitle(pszNewWindowTitle);

       // Ensure window title has been updated.

       Sleep(40);

       // Look for NewWindowTitle.

       hwndFound=FindWindow(NULL, pszNewWindowTitle);

       // Restore original window title.

       SetConsoleTitle(pszOldWindowTitle);

       return(hwndFound);
   }
```

