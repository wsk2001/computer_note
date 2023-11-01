# **COM 소스 Sample**

`IqfileCom.h`

``` c++
// MyCom.h
#pragma once

#include <comdef.h>

// MyCom 클래스의 클래스 ID
const CLSID CLSID_MyCom = { 0x90741403, 0x7150, 0x4825, { 0xa1, 0x2d, 0x5d, 0x34, 0x61, 0x5a, 0x11, 0x70 } };

class MyCom : public IUnknown
{
public:
    virtual HRESULT STDMETHODCALLTYPE QueryInterface(REFIID riid, void **ppvObject) override;
    virtual ULONG STDMETHODCALLTYPE AddRef(void) override;
    virtual ULONG STDMETHODCALLTYPE Release(void) override;

    STDMETHOD(HelloWorld)(char *pszMessage);

private:
    LONG m_refCount;
};
```





`IqfileCom.cpp`

``` c++
// IqfileCom.cpp
#include "IqfileCom.h"

// IUnknown 함수 구현
HRESULT IqfileCom::QueryInterface(REFIID riid, void **ppvObject)
{
    if (riid == IID_IUnknown)
    {
        *ppvObject = static_cast<IUnknown*>(this);
        AddRef();
        return S_OK;
    }

    return E_NOINTERFACE;
}

ULONG IqfileCom::AddRef(void)
{
    return InterlockedIncrement(&m_refCount);
}

ULONG IqfileCom::Release(void)
{
    LONG refCount = InterlockedDecrement(&m_refCount);

    if (refCount == 0)
    {
        delete this;
        return 0;
    }

    return refCount;
}

// IqfileCom 함수 구현
STDMETHODIMP IqfileCom::HelloWorld(char *pszMessage)
{
    OutputDebugString(L"Hello, world! (pszMessage = %s)\n", pszMessage);
    return S_OK;
}
```



`Makefile`

``` Makefile
# Makefile for IqfileCom COM object

CC = cl
LD = link

TARGET = IqfileCom.dll
OBJS = IqfileCom.obj

all: $(TARGET)

$(TARGET): $(OBJS)
    $(LD) $(OBJS) /OUT:$@ /DLL /SUBSYSTEM:WINDOWS

IqfileCom.obj: IqfileCom.cpp
    $(CC) /c /EHsc IqfileCom.cpp

clean:
    rm -f $(OBJS) $(TARGET)

```



이 Makefile을 사용하면 다음과 같이 COM 소스를 컴파일할 수 있습니다.

이렇게 컴파일하면 `IqfileCom.dll`이라는 COM DLL이 생성됩니다. 이 DLL을 다른 COM 응용 프로그램에서 사용할 수 있습니다.



다음은 `IqfileCom.dll`을 사용하는 간단한 예제입니다.

`IqfileComClient.cpp`

``` c++
// IqfileComClient.cpp
#include <iostream>
#include <comdef.h>

int main()
{
    // COM 라이브러리를 초기화합니다.
    CoInitialize(NULL);

    // IqfileCom 인터페이스를 가리키는 포인터를 생성합니다.
    IUnknown *pCom = NULL;
    HRESULT hr = CoCreateInstance(CLSID_IqfileCom, NULL, CLSCTX_INPROC_SERVER, IID_IUnknown, (void**)&pCom);

    if (SUCCEEDED(hr))
    {
        // IqfileCom 인터페이스를 가리키는 포인터를 IqfileCom 클래스로 캐스트합니다.
        IqfileCom *pIqfileCom = static_cast<IqfileCom*>(pCom);

        // IqfileCom::HelloWorld() 함수를 호출합니다.
        int nResult = pIqfileCom->HelloWorld("안녕하세요!");

        // COM 리소스를 해제합니다.
        pIqfileCom->Release();
        pCom->Release();
    }

    // COM 라이브러리를 종료합니다.
    CoUninitialize();

    return 0;
}
```



이 코드를 컴파일하고 실행하면 다음과 같은 출력이 표시됩니다.

```
Hello, world!
```

이 예제에서 `CoCreateInstance()` 함수를 사용하여 `IqfileCom` 클래스의 인스턴스를 생성합니다. 그런 다음 `IqfileCom` 인터페이스를 가리키는 포인터를 `IqfileCom` 클래스로 캐스트합니다. 마지막으로 `IqfileCom::HelloWorld()` 함수를 호출하여 "Hello, world!"라는 메시지를 출력합니다.



`Makefile`

```Makefile
# Makefile for IqfileComClient application

CC = cl

TARGET = IqfileComClient.exe
OBJS = IqfileComClient.obj

all: $(TARGET)

$(TARGET): $(OBJS)
    $(CC) $(OBJS) /OUT:$@ /SUBSYSTEM:WINDOWS

IqfileComClient.obj: IqfileComClient.cpp
    $(CC) /c /EHsc IqfileComClient.cpp

clean:
    rm -f $(OBJS) $(TARGET)

```



C# 에서 호출

``` cs
using System;
using System.Runtime.InteropServices;

namespace IqfileComClient
{
    class Program
    {
        static void Main(string[] args)
        {
            // COM 라이브러리를 초기화합니다.
            System.Runtime.InteropServices.ComTypes.CoInitializeEx(IntPtr.Zero, System.Runtime.InteropServices.ComTypes.COINIT_MULTITHREADED);

            // IqfileCom 인터페이스를 가리키는 포인터를 생성합니다.
            [DllImport("IqfileCom.dll")]
            static extern IntPtr CoCreateInstance(Guid clsid, IntPtr pUnkOuter, uint dwClsContext, Guid iid, out IntPtr ppvObject);

            IntPtr pCom = CoCreateInstance(CLSID_IqfileCom, IntPtr.Zero, System.Runtime.InteropServices.ComTypes.CLSCTX_INPROC_SERVER, IID_IUnknown, out IntPtr ppvObject);

            if (pCom != IntPtr.Zero)
            {
                // IqfileCom 인터페이스를 가리키는 포인터를 IqfileCom 클래스로 캐스트합니다.
                IqfileCom pIqfileCom = (IqfileCom)Marshal.PtrToStructure(ppvObject, typeof(IqfileCom));

                // IqfileCom::HelloWorld() 함수를 호출합니다.
                int nResult = pIqfileCom.HelloWorld("안녕하세요!");

                // COM 리소스를 해제합니다.
                pIqfileCom.Release();
                Marshal.Release(ppvObject);
            }

            // COM 라이브러리를 종료합니다.
            System.Runtime.InteropServices.ComTypes.CoUninitialize();

            Console.WriteLine("Hello, world! (pszMessage = {0})", nResult);
        }
    }

    [ComImport]
    [Guid("90741403-7150-4825-A12D-5D34615A1170")]
    [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
    public interface IqfileCom
    {
        [DllImport("IqfileCom.dll")]
        [return: MarshalAs(UnmanagedType.I4)]
        int HelloWorld([MarshalAs(UnmanagedType.LPStr)] string pszMessage);
    }
}

```

