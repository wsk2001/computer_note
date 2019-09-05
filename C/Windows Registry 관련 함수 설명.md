# Windows Registry 관련 함수 설명

본 문서는 Windows Registry 관련 함수 사용법을 기술 합니다.

출처: https://docs.microsoft.com/en-us/windows/win32/api/winreg/nf-winreg-regqueryvalueexw



## RegOpenKeyExW function

지정된 레지스트리 키를 엽니 다. 키 이름은 대소 문자를 구분하지 않습니다.

키에 대한 트랜잭션 된 레지스트리 작업을 수행하려면 RegOpenKeyTransacted 함수를 호출하십시오.

### Syntax

```c++
LSTATUS RegOpenKeyExW(
  HKEY    hKey,
  LPCWSTR lpSubKey,
  DWORD   ulOptions,
  REGSAM  samDesired,
  PHKEY   phkResult
);
```

### Parameters

`hKey`

열려있는 레지스트리 키에 대한 핸들입니다. 이 핸들은 RegCreateKeyEx 또는 RegOpenKeyEx 함수에서 반환하거나 다음 미리 정의 된 키 중 하나 일 수 있습니다.

**HKEY_CLASSES_ROOT** 

**HKEY_CURRENT_CONFIG** 

**HKEY_CURRENT_USER** 

**HKEY_LOCAL_MACHINE** 

**HKEY_USERS**



`lpSubKey`

열(Open) 레지스트리 하위 키의 이름입니다.

키 이름은 대소 문자를 구분하지 않습니다.

lpSubKey 매개 변수는 빈 문자열에 대한 포인터가 될 수 있습니다. LpSubKey가 빈 문자열에 대한 포인터이고 hKey가 HKEY_CLASSES_ROOT 인 경우 phkResult는 함수에 전달 된 동일한 hKey 핸들을받습니다. 그렇지 않은 경우, phkResult는 hKey에 의해 지정된 키에 대한 새로운 핸들을 수신합니다.

hKey가 미리 정의 된 키 중 하나 인 경우에만 lpSubKey 매개 변수가 NULL 일 수 있습니다. lpSubKey가 NULL이고 hKey가 HKEY_CLASSES_ROOT이면, phkResult는 hKey에 의해 지정된 키에 대한 새로운 핸들을 수신합니다. 그렇지 않으면 phkResult는 함수에 전달 된 동일한 hKey 핸들을받습니다.

자세한 내용은 [레지스트리 요소 크기 제한](https://docs.microsoft.com/windows/desktop/SysInfo/registry-element-size-limits)을 참조하십시오.



`ulOptions`

키를 열 때 적용 할 옵션을 지정합니다. 이 매개 변수를 0 또는 다음 값으로 설정하십시오.

| Value                    | Meaning                                                      |
| :----------------------- | :----------------------------------------------------------- |
| **REG_OPTION_OPEN_LINK** | 키는 기호 링크입니다. 레지스트리 기호 링크는 절대적으로 필요한 경우에만 사용해야합니다. |



`samDesired`

열 수있는 키에 대해 원하는 액세스 권한을 지정하는 마스크입니다. 키의 보안 설명자가 호출 프로세스에 대해 요청 된 액세스를 허용하지 않으면 함수가 실패합니다. 자세한 내용은 레지스트리 키 보안 및 액세스 권한을 참조하십시오.



`phkResult`

열려있는 키에 대한 핸들을받는 변수에 대한 포인터입니다. 키가 미리 정의 된 레지스트리 키 중 하나가 아닌 경우 핸들 사용을 마친 후 RegCloseKey 함수를 호출하십시오.



### Return Value

함수가 성공하면 반환 값은 ERROR_SUCCESS입니다.

함수가 실패하면 반환 값은 Winerror.h에 정의 된 0이 아닌 오류 코드입니다. FormMessage 함수를 FORMAT_MESSAGE_FROM_SYSTEM 플래그와 함께 사용하여 오류에 대한 일반적인 설명을 얻을 수 있습니다.





## RegQueryValueExW function

열려있는 레지스트리 키와 연결된 지정된 값 이름의 형식과 데이터를 검색합니다.

반환 된 모든 문자열 값 (REG_SZ, REG_MULTI_SZ 및 REG_EXPAND_SZ)이 null로 종료되도록하려면 [RegGetValue](https://docs.microsoft.com/windows/desktop/api/winreg/nf-winreg-reggetvaluea) 함수를 사용하십시오.



### Syntax

```c++
LSTATUS RegQueryValueExW(
  HKEY    hKey,
  LPCWSTR lpValueName,
  LPDWORD lpReserved,
  LPDWORD lpType,
  LPBYTE  lpData,
  LPDWORD lpcbData
);
```



### Parameters

`hKey`

열려있는 레지스트리 키에 대한 핸들입니다. 키는 KEY_QUERY_VALUE 액세스 권한으로 열어야합니다. 자세한 내용은 [레지스트리 키 보안 및 액세스 권한](https://docs.microsoft.com/windows/desktop/SysInfo/registry-key-security-and-access-rights)을 참조하십시오.

이 핸들은 [RegCreateKeyEx](https://docs.microsoft.com/windows/desktop/api/winreg/nf-winreg-regcreatekeyexa), [RegCreateKeyTransacted](https://docs.microsoft.com/windows/desktop/api/winreg/nf-winreg-regcreatekeytransacteda), [RegOpenKeyEx](https://docs.microsoft.com/windows/desktop/api/winreg/nf-winreg-regopenkeyexa) 또는 [RegOpenKeyTransacted](https://docs.microsoft.com/windows/desktop/api/winreg/nf-winreg-regopenkeytransacteda) 함수에 의해 반환됩니다. 다음 [미리 정의 된 키](https://docs.microsoft.com/windows/desktop/SysInfo/predefined-keys) 중 하나 일 수도 있습니다.

**HKEY_CLASSES_ROOT**

**HKEY_CURRENT_CONFIG**

**HKEY_CURRENT_USER**

**HKEY_LOCAL_MACHINE**

**HKEY_PERFORMANCE_DATA**

**HKEY_PERFORMANCE_NLSTEXT**

**HKEY_PERFORMANCE_TEXT**

**HKEY_USERS**



`lpValueName`

레지스트리 값의 이름.

LpValueName이 NULL이거나 빈 문자열 인 ''인 경우 함수는 키의 이름이 없거나 기본값 (있는 경우)에 대한 유형 및 데이터를 검색합니다.

LpValueName이 레지스트리에 없는 값을 지정하면 함수는 ERROR_FILE_NOT_FOUND를 반환합니다.

키에는 자동으로 이름이 지정되지 않거나 기본값이 없습니다. 이름없는 값은 모든 유형이 될 수 있습니다. 자세한 내용은 [레지스트리 요소 크기 제한](https://docs.microsoft.com/windows/desktop/SysInfo/registry-element-size-limits)을 참조하십시오.



`lpReserved`

이 매개 변수는 예약되어 있으므로 NULL이어야합니다.



`lpType`

지정된 값에 저장된 데이터 유형을 나타내는 코드를 수신하는 변수에 대한 포인터. 가능한 유형 코드 목록은 [레지스트리 값 유형](https://docs.microsoft.com/windows/desktop/SysInfo/registry-value-types)을 참조하십시오. 형식 코드가 필요하지 않으면 lpType 매개 변수는 NULL 일 수 있습니다.



`lpData`

값의 데이터를받는 버퍼에 대한 포인터. 이 매개 변수는 데이터가 필요하지 않은 경우 NULL 일 수 있습니다.



`lpcbData`

LpData 매개 변수가 가리키는 버퍼의 크기를 바이트 단위로 지정하는 변수에 대한 포인터. 함수가 반환되면이 변수는 lpData에 복사 된 데이터의 크기를 포함합니다.

LpcbData 매개 변수는 lpData가 NULL 인 경우에만 NULL이 될 수 있습니다.

데이터에 REG_SZ, REG_MULTI_SZ 또는 REG_EXPAND_SZ 유형이있는 경우이 크기에는 데이터가 저장되지 않은 경우 종료되는 모든 null 문자가 포함됩니다. 자세한 내용은주의를 참조하십시오.

LpData 매개 변수로 지정된 버퍼가 데이터를 저장할만큼 크지 않으면이 함수는 ERROR_MORE_DATA를 반환하고 lpcbData가 가리키는 변수에 필요한 버퍼 크기를 저장합니다. 이 경우 lpData 버퍼의 내용은 정의되지 않습니다.

LpData가 NULL이고 lpcbData가 NULL이 아닌 경우이 함수는 ERROR_SUCCESS를 반환하고 lpcbData가 가리키는 변수에 데이터 크기를 바이트 단위로 저장합니다. 이를 통해 응용 프로그램은 값 데이터에 대해 버퍼를 할당하는 가장 좋은 방법을 결정할 수 있습니다.

HKey가 HKEY_PERFORMANCE_DATA를 지정하고 lpData 버퍼가 반환 된 모든 데이터를 포함 할 정도로 크지 않으면 RegQueryValueEx는 ERROR_MORE_DATA를 반환하고 lpcbData 매개 변수를 통해 반환 된 값은 정의되지 않습니다. 이것은 성능 데이터의 크기가 한 통화에서 다른 통화로 변경 될 수 있기 때문입니다. 이 경우 버퍼 크기를 늘리고 RegQueryValueEx를 다시 호출하여 lpcbData 매개 변수에서 업데이트 된 버퍼 크기를 전달해야합니다. 함수가 성공할 때까지이 작업을 반복하십시오. LpcbData에 의해 리턴 된 값은 예측할 수 없기 때문에 버퍼 크기를 추적하기 위해 별도의 변수를 유지 보수해야합니다.

LpValueName 레지스트리 값이 없으면 RegQueryValueEx는 ERROR_FILE_NOT_FOUND를 반환하고 lpcbData 매개 변수를 통해 반환 된 값은 정의되지 않습니다.



### Return Value

함수가 성공하면 반환 값은 ERROR_SUCCESS입니다.

함수가 실패하면 반환 값은 시스템 오류 코드입니다.

lpData 버퍼가 너무 작아서 데이터를받을 수 없으면 ERROR_MORE_DATA를 반환합니다.

lpValueName 레지스트리 값이 없으면이 함수는 ERROR_FILE_NOT_FOUND를 반환합니다.



### Remarks

응용 프로그램은 일반적으로 RegEnumValue를 호출하여 값 이름을 확인한 다음 RegQueryValueEx를 호출하여 이름의 데이터를 검색합니다.

데이터에 REG_SZ, REG_MULTI_SZ 또는 REG_EXPAND_SZ 유형이 있으면 해당 문자열이 올바른 종료 널 문자와 함께 저장되지 않았을 수 있습니다. 따라서 함수가 ERROR_SUCCESS를 반환하더라도 응용 프로그램은 문자열을 사용하기 전에 제대로 종료되었는지 확인해야합니다. 그렇지 않으면 버퍼를 덮어 쓸 수 있습니다. REG_MULTI_SZ 문자열에는 두 개의 종료 null 문자가 있어야합니다. 응용 프로그램에서 문자열이 제대로 종료되도록하는 방법 중 하나는 필요한 경우 종료 null 문자를 추가하는 RegGetValue를 사용하는 것입니다.

데이터에 REG_SZ, REG_MULTI_SZ 또는 REG_EXPAND_SZ 형식이 있고 이 함수의 ANSI 버전이 사용되는 경우 (명시 적으로 RegQueryValueExA를 호출하거나 Windows.h 파일을 포함하기 전에 UNICODE를 정의하지 않음)이 함수는 저장된 유니 코드 문자열을 LpData가 가리키는 버퍼로 복사하기 전에 ANSI 문자열.

특정 레지스트리 키에 액세스하는 작업은 리디렉션됩니다. 자세한 내용은 레지스트리의 레지스트리 가상화 및 32 비트 및 64 비트 응용 프로그램 데이터를 참조하십시오.



### Examples

이 함수를 호출 할 때마다 lpcbData 매개 변수가 가리키는 값을 다시 초기화하십시오. 다음 코드 예제와 같이이 함수를 루프에서 호출 할 때 매우 중요합니다.

```c++
#include <windows.h>
#include <malloc.h>
#include <stdio.h>

#define TOTALBYTES    8192
#define BYTEINCREMENT 4096

void main()
{
    DWORD BufferSize = TOTALBYTES;
    DWORD cbData;
    DWORD dwRet;

    PPERF_DATA_BLOCK PerfData = (PPERF_DATA_BLOCK) malloc( BufferSize );
    cbData = BufferSize;

    printf("\nRetrieving the data...");

    dwRet = RegQueryValueEx( HKEY_PERFORMANCE_DATA,
                             TEXT("Global"),
                             NULL,
                             NULL,
                             (LPBYTE) PerfData,
                             &cbData );
    while( dwRet == ERROR_MORE_DATA )
    {
        // Get a buffer that is big enough.

        BufferSize += BYTEINCREMENT;
        PerfData = (PPERF_DATA_BLOCK) realloc( PerfData, BufferSize );
        cbData = BufferSize;

        printf(".");
        dwRet = RegQueryValueEx( HKEY_PERFORMANCE_DATA,
                         TEXT("Global"),
                         NULL,
                         NULL,
                         (LPBYTE) PerfData,
                         &cbData );
    }
    if( dwRet == ERROR_SUCCESS )
        printf("\n\nFinal buffer size is %d\n", BufferSize);
    else printf("\nRegQueryValueEx failed (%d)\n", dwRet);
}
```

