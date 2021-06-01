# Visual CPP Shared memory Sample

```c++
// sample_shm.h

#ifndef SHM_PATH_MAX
#define SHM_PATH_MAX 256
#endif

#define SHM_DIR_MAP _T("Local\\WATCHDIR")
#define SHM_ACL_MAP _T("Local\\SHMACL")

#define DIR_MAX_COUNT 10
#define ACL_MAX_COUNT 1024

typedef struct _SHM_WATCH_DIR {
	char path[SHM_PATH_MAX];
	int  flag;                 // 0: not use, 1: append wait,  2: append complete, 3:remove wait
} SHM_WATCH_DIR;

typedef struct SHM_acl_t {
	char ad;                   // allow list, deny list 구분
	char owner [          64]; // owner (linux 계정 명), * 이면 전체 계정
	char pname [SHM_PATH_MAX]; // program 명 * 이면 전체 program
	char path  [SHM_PATH_MAX];
} SHM_ACL_T;


```



```c++
// CreateShm.cpp

#include <stdio.h>
#include <Windows.h>
#include <tchar.h>
#include <conio.h>
#include "sample_shm.h"

#pragma warning (disable:4996)

HANDLE         hDirMap;
HANDLE         hAclMap;
SHM_WATCH_DIR* pDir = NULL;
SHM_ACL_T*     pAcl = NULL;

int create_dir_map(void)
{
	SHM_WATCH_DIR* p = NULL;
	int            i = 0;

	hDirMap = CreateFileMapping(INVALID_HANDLE_VALUE,
		NULL,
		PAGE_READWRITE,
		0,
		sizeof(SHM_WATCH_DIR) * DIR_MAX_COUNT,
		SHM_DIR_MAP);

	pDir = (SHM_WATCH_DIR*)MapViewOfFile(hDirMap, PAGE_READONLY, 0, 0, 0);

	for (i = 0, p = pDir; i < DIR_MAX_COUNT; i++, p++)
	{
		sprintf_s(p->path, "C:\\WorkSpace\\HancomSolution\\SharedMemory-%d", i);
		p->flag = 1;
	}

	return 0;
}

int create_acl_map(void)
{
	SHM_ACL_T* p = NULL;

	int            i = 0;

	hAclMap = CreateFileMapping(INVALID_HANDLE_VALUE,
		NULL,
		PAGE_READWRITE,
		0,
		sizeof(SHM_ACL_T) * ACL_MAX_COUNT,
		SHM_ACL_MAP);

	pAcl = (SHM_ACL_T*) MapViewOfFile(hAclMap, PAGE_READONLY, 0, 0, 0);

	for (i = 0, p = pAcl; i < ACL_MAX_COUNT-1000; i++, p++)
	{
		p->ad = i % 2 ? '0' : '1';
		sprintf_s(p->owner, "owner-%d", i);
		sprintf_s(p->pname, "pname-%d", i);
		sprintf_s(p->path, "C:\\WorkSpace\\HancomSolution\\SharedMemory-%d", i);
	}

	return 0;
}


int main(void)
{

	create_dir_map();
	create_acl_map();

	printf("\nPress any key to exit! ");
	getch();
	printf("\n");

	UnmapViewOfFile(pDir);

	CloseHandle(hDirMap);

	return 0;

}
```



```c++
// ReadShm.cpp

#include <stdio.h>
#include <Windows.h>
#include <tchar.h>
#include <conio.h>
#include "sample_shm.h"

#pragma warning (disable:4996)

HANDLE         hDirMap;
HANDLE         hAclMap;
SHM_WATCH_DIR* pDir = NULL;
SHM_ACL_T*     pAcl = NULL;

int
str_right_cmp(const char* str1, const char* str2) {
	int  len1 = strlen(str1);
	int  len2 = strlen(str2);
	char* p1 = NULL;
	char* p2 = (char*)str2;

	if (len1 < len2)
		return -1;

	p1 = (char*)str1 + (len1 - len2);

	return strncmp(p1, p2, len2);
}


bool is_dir(char* filename)
{
	return (GetFileAttributesA(filename) & FILE_ATTRIBUTE_DIRECTORY) == FILE_ATTRIBUTE_DIRECTORY;
}


int view_dirs(void)
{
	SHM_WATCH_DIR* p = NULL;
	int            i = 0;

	if (hDirMap == NULL)
	{
		hDirMap = CreateFileMapping(INVALID_HANDLE_VALUE,
			NULL,
			PAGE_READWRITE,
			0,
			sizeof(SHM_WATCH_DIR) * DIR_MAX_COUNT,
			SHM_DIR_MAP);
		if (hDirMap == NULL)
		{
			DWORD dw = GetLastError();
			printf("Error: %d\n");
			return 0;
		}
	}

	pDir = (SHM_WATCH_DIR*)MapViewOfFile(hDirMap, PAGE_READONLY, 0, 0, 0);
	if (pDir != NULL)
	{
		printf("\n");
		for (i = 0, p = pDir; i < DIR_MAX_COUNT; i++, p++)
		{
			printf("path : %s\n", p->path);
			printf("flag : %d\n\n", p->flag);
		}
	}

	return 0;
}


int view_acl(void)
{
	SHM_ACL_T* p = NULL;
	int        i = 0;

	if (hAclMap == NULL)
	{
		hAclMap = CreateFileMapping(INVALID_HANDLE_VALUE,
			NULL,
			PAGE_READWRITE,
			0,
			sizeof(SHM_ACL_T) * ACL_MAX_COUNT,
			SHM_ACL_MAP);
		if (hAclMap == NULL)
		{
			DWORD dw = GetLastError();
			printf("Error: %d\n");
			return 0;
		}
	}

	pAcl = (SHM_ACL_T*)MapViewOfFile(hAclMap, PAGE_READONLY, 0, 0, 0);
	if (pAcl != NULL)
	{
		printf("\n");
		for (i = 0, p = pAcl; i < ACL_MAX_COUNT; i++, p++)
		{
			if(p->ad != 0x00)
			printf("ad=%c, owner=%s, pname=%s, path=%s\n"
				, p->ad, p->owner, p->pname, p->path );
		}
	}

	return 0;
}


int is_include_acl(char ad, char* owner, char* pname, char* path)
{
	SHM_ACL_T* p = NULL;
	int        i = 0;
	int        flag_o, flag_p;
	int        nres = 0;

	if (is_dir(pname) == true)
		return 0;

	if (hAclMap == NULL)
	{
		hAclMap = CreateFileMapping(
			INVALID_HANDLE_VALUE,
			NULL,
			PAGE_READWRITE,
			0,
			sizeof(SHM_ACL_T) * ACL_MAX_COUNT,
			SHM_ACL_MAP
		);
		if (hAclMap == NULL)
		{
			DWORD dw = GetLastError();
			printf("Error: %d\n");
			return 0;
		}
	}

	pAcl = (SHM_ACL_T*)MapViewOfFile(hAclMap, PAGE_READONLY, 0, 0, 0);
	if (pAcl != NULL)
	{
		for (i = 0, p = pAcl; i < ACL_MAX_COUNT; i++, p++)
		{
			flag_o = flag_p = 0;
			if (p->ad == ad && !memcmp(p->path, path, strlen(path)))
			{
				if (*p->owner == '*')
					flag_o = 1;
				else {
					if (!strcmp(owner, p->owner))
						flag_o = 1;
				}

				if (*p->pname == '*')
					flag_p = 1;
				else {
					if (*(p->pname + 1) == ':') { // full path check
						if (!strcmp(pname, p->pname))
							flag_p = 1;
					}
					else { //short path check
						if (!str_right_cmp((const char*)pname, (const char*)p->pname))
							flag_p = 1;
					}
				}

			}
			if (flag_o == 1 && flag_p == 1) {
				nres = 1;
				break;
			}
		}
	}

	return nres;
}


int main(void)
{
	view_dirs();
	view_acl();

	getch();

	UnmapViewOfFile(pDir);
	CloseHandle(hDirMap);

	UnmapViewOfFile(pAcl);
	CloseHandle(hAclMap);

	return 0;
}
```

