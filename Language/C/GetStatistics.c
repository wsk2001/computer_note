/**
 * Windows 특정 Directory 또는 파일이 NTFS volume 인지 확인 확인 하는 프로그램
 *
 * 컴파일  : cl GetStatistics.c
 * 실행예1 : GetStatistics  c:\Temp
 * 실행예2 : GetStatistics  c:\Temp\aaa.txt
 *  
 */

#include <windows.h>
#include <stdio.h>

void Usage(void)
{
	printf("\n");
	printf("Usage: GetStatics dir_name or file_name\n");
	printf("Ex) GetStatics C:\\Temp\n");
	printf("Ex) GetStatics C:\\Temp\\aaa.txt\n");
	exit(0);
}

int wmain(int argc, wchar_t ** argv)
{
    HANDLE 					h;
    FILESYSTEM_STATISTICS 	*fs;
    BYTE 					buffer[32768];
    DWORD 					dw;

	if( argc < 2 )
	    Usage();
	
    h = CreateFileW(argv[1], 0, 
        FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE, 
        NULL, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, NULL);
		
    if (h == INVALID_HANDLE_VALUE)
    {
        printf("CreateFile Last Error code: %u\n", GetLastError());
        return 1;
    }

	/////////////////////////////////////////////////////////////////////////////
	// 파일 시스템 통계 정보 요청
	// 이 통계에는 사용자 및 메타 데이터 파일에 대한 정보가 들어 있습니다. 
	// 사용자 파일은 사용자가 사용할 수 있습니다. 
	// 메타 데이터 파일은 파일 시스템이 내부 조직에 사용하는 정보가 들어있는 시스템 파일입니다.
	// 통계 구조에는 서버 수명 기간 동안 넘칠 수있는 필드가 있습니다. 
	// 이는 의도적으로 설계된 동작입니다. 
	// 오버플로가 발생하면 값이 그냥 랩됩니다. 예를 들어 0XFFFFF000 + 0x2000은 0x1000이됩니다.
	/////////////////////////////////////////////////////////////////////////////
    if (!DeviceIoControl(h, FSCTL_FILESYSTEM_GET_STATISTICS, NULL, 0, buffer, sizeof(buffer), &dw, NULL))
    {
        dw = GetLastError();
        CloseHandle(h);
        printf("DeviceIoControl: %u\n", dw);
        if (dw == ERROR_INVALID_FUNCTION)
        {
            printf("This probably means the specified file or directory is not on an NTFS volume.\n");
            printf("For example, this happens if you specify a file on a CD-ROM.\n");
        }
        return 1;
    }

    CloseHandle(h);

    fs = (FILESYSTEM_STATISTICS *)buffer;
    printf("Filesystem type: %u\n", fs->FileSystemType);

    if (fs->FileSystemType == FILESYSTEM_STATISTICS_TYPE_NTFS)
    {
        printf("The file or directory is on an NTFS volume.\n");
    }
    else
    {
        printf("The file or directory is not on an NTFS volume.\n");
    }
    return 0;
}