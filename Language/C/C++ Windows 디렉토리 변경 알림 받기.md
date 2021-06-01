# C++ Windows 디렉토리 변경 알림 받기

출처: https://docs.microsoft.com/ko-kr/windows/win32/fileio/obtaining-directory-change-notifications

응용 프로그램은 변경 알림을 사용하여 디렉토리 및 해당 하위 디렉토리의 내용을 모니터링 할 수 있습니다. 변경 알림을 기다리는 것은 디렉토리 및 필요한 경우 하위 디렉토리에 대해 읽기 작업을 보류하는 것과 비슷합니다. 감시중인 디렉토리 내에서 무언가가 변경되면 읽기 작업이 완료됩니다. 예를 들어, 응용 프로그램은 이 기능을 사용하여 모니터링되는 디렉토리 내의 파일 이름이 변경 될 때마다 디렉토리 목록을 업데이트 할 수 있습니다.

응용 프로그램은 [FindFirstChangeNotification](https://docs.microsoft.com/en-us/windows/desktop/api/FileAPI/nf-fileapi-findfirstchangenotificationa) 함수를 사용하여 변경 알림을 트리거하는 조건 세트를 지정할 수 있습니다. 조건에는 파일 이름, 디렉토리 이름, 속성, 파일 크기, 마지막 기록 시간 및 보안 변경이 포함됩니다. 이 함수는 대기 함수를 사용하여 대기 할 수있는 핸들도 반환합니다. 대기 조건이 충족되면 [FindNextChangeNotification](https://docs.microsoft.com/en-us/windows/desktop/api/FileAPI/nf-fileapi-findnextchangenotification) 을 사용하여 후속 변경을 기다리는 알림 핸들을 제공 할 수 있습니다. 그러나 이러한 기능은 대기 조건을 만족 한 실제 변경을 나타내지 않습니다.

알림 핸들을 닫으려면 [FindCloseChangeNotification](https://docs.microsoft.com/en-us/windows/desktop/api/FileAPI/nf-fileapi-findclosechangenotification)을 사용하십시오.

알림의 일부로 특정 변경에 대한 정보를 검색하려면 [ReadDirectoryChangesW](https://docs.microsoft.com/en-us/windows/desktop/api/WinBase/nf-winbase-readdirectorychangesw) 함수를 사용하십시오. 이 기능을 사용하면 완료 루틴을 제공 할 수도 있습니다.

볼륨의 변경 사항을 추적하려면 [저널 변경](https://docs.microsoft.com/ko-kr/windows/win32/fileio/change-journals)을 참조하십시오.

다음 예제는 디렉토리 이름 변경을 위해 디렉토리 트리를 모니터합니다. 또한 파일 이름 변경을 위해 디렉토리를 모니터합니다. 이 예에서는 [FindFirstChangeNotification](https://docs.microsoft.com/en-us/windows/desktop/api/FileAPI/nf-fileapi-findfirstchangenotificationa) 함수를 사용하여 두 개의 알림 핸들을 만들고 [WaitForMultipleObjects](https://docs.microsoft.com/windows/desktop/api/synchapi/nf-synchapi-waitformultipleobjects) 함수를 사용하여 핸들을 기다립니다. 트리에서 디렉토리를 만들거나 삭제할 때마다 예제는 전체 디렉토리 트리를 업데이트해야합니다. 디렉토리에서 파일을 작성하거나 삭제할 때마다 예제는 디렉토리를 새로 고쳐야합니다.

 **참고**

```
이 간단한 예제는 종료 및 정리를 위해 ExitProcess 함수를 사용하지만보다 복잡한 응용 프로그램은 항상 적절한 경우 FindCloseChangeNotification과 같은 적절한 리소스 관리를 사용해야합니다.
```

```c++
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <tchar.h>

void RefreshDirectory(LPTSTR);
void RefreshTree(LPTSTR);
void WatchDirectory(LPTSTR);

void _tmain(int argc, TCHAR *argv[])
{
    if(argc != 2)
    {
        _tprintf(TEXT("Usage: %s <dir>\n"), argv[0]);
        return;
    }

    WatchDirectory(argv[1]);
}

void WatchDirectory(LPTSTR lpDir)
{
   DWORD dwWaitStatus; 
   HANDLE dwChangeHandles[2]; 
   TCHAR lpDrive[4];
   TCHAR lpFile[_MAX_FNAME];
   TCHAR lpExt[_MAX_EXT];

   _tsplitpath_s(lpDir, lpDrive, 4, NULL, 0, lpFile, _MAX_FNAME, lpExt, _MAX_EXT);

   lpDrive[2] = (TCHAR)'\\';
   lpDrive[3] = (TCHAR)'\0';
 
// Watch the directory for file creation and deletion. 
 
   dwChangeHandles[0] = FindFirstChangeNotification( 
      lpDir,                         // directory to watch 
      FALSE,                         // do not watch subtree 
      FILE_NOTIFY_CHANGE_FILE_NAME); // watch file name changes 
 
   if (dwChangeHandles[0] == INVALID_HANDLE_VALUE) 
   {
     printf("\n ERROR: FindFirstChangeNotification function failed.\n");
     ExitProcess(GetLastError()); 
   }
 
// Watch the subtree for directory creation and deletion. 
 
   dwChangeHandles[1] = FindFirstChangeNotification( 
      lpDrive,                       // directory to watch 
      TRUE,                          // watch the subtree 
      FILE_NOTIFY_CHANGE_DIR_NAME);  // watch dir name changes 
 
   if (dwChangeHandles[1] == INVALID_HANDLE_VALUE) 
   {
     printf("\n ERROR: FindFirstChangeNotification function failed.\n");
     ExitProcess(GetLastError()); 
   }
 

// Make a final validation check on our handles.

   if ((dwChangeHandles[0] == NULL) || (dwChangeHandles[1] == NULL))
   {
     printf("\n ERROR: Unexpected NULL from FindFirstChangeNotification.\n");
     ExitProcess(GetLastError()); 
   }

// Change notification is set. Now wait on both notification 
// handles and refresh accordingly. 
 
   while (TRUE) 
   { 
   // Wait for notification.
 
      printf("\nWaiting for notification...\n");

      dwWaitStatus = WaitForMultipleObjects(2, dwChangeHandles, 
         FALSE, INFINITE); 
 
      switch (dwWaitStatus) 
      { 
         case WAIT_OBJECT_0: 
 
         // A file was created, renamed, or deleted in the directory.
         // Refresh this directory and restart the notification.
 
             RefreshDirectory(lpDir); 
             if ( FindNextChangeNotification(dwChangeHandles[0]) == FALSE )
             {
               printf("\n ERROR: FindNextChangeNotification function failed.\n");
               ExitProcess(GetLastError()); 
             }
             break; 
 
         case WAIT_OBJECT_0 + 1: 
 
         // A directory was created, renamed, or deleted.
         // Refresh the tree and restart the notification.
 
             RefreshTree(lpDrive); 
             if (FindNextChangeNotification(dwChangeHandles[1]) == FALSE )
             {
               printf("\n ERROR: FindNextChangeNotification function failed.\n");
               ExitProcess(GetLastError()); 
             }
             break; 
 
         case WAIT_TIMEOUT:

         // A timeout occurred, this would happen if some value other 
         // than INFINITE is used in the Wait call and no changes occur.
         // In a single-threaded environment you might not want an
         // INFINITE wait.
 
            printf("\nNo changes in the timeout period.\n");
            break;

         default: 
            printf("\n ERROR: Unhandled dwWaitStatus.\n");
            ExitProcess(GetLastError());
            break;
      }
   }
}

void RefreshDirectory(LPTSTR lpDir)
{
   // This is where you might place code to refresh your
   // directory listing, but not the subtree because it
   // would not be necessary.

   _tprintf(TEXT("Directory (%s) changed.\n"), lpDir);
}

void RefreshTree(LPTSTR lpDrive)
{
   // This is where you might place code to refresh your
   // directory listing, including the subtree.

   _tprintf(TEXT("Directory tree (%s) changed.\n"), lpDrive);
}
```

