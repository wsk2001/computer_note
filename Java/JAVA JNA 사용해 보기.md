## JAVA JNA 사용해 보기

Java 에서 Native Library 에 있는 함수를 사용하기 위해서는 2 가지 방법으로 접근 할 수 있다.  JNI(Java Native Interface) 와 JNA(Java Native Access) 가 그것 이다. 그중 JNI 를 가장 많이 사용하고 있는데 본 문서 에서는 JNA 를 사용하는 방법에 관하여 기술 한다. 필자는 C/C++ 을 주 개발 언어로 사용 하기 때문에 주로 JNI 를 사용 하여 JAVA 에서 C Native Code 를 호출 할 수 있도록 지원해 왔었는데 C 개발 환경이 설치되어 있지 않은 장치 에서 Native Code 를 사용 할 경우에는 JNA 가 필요하기 때문에 JNA 를 사용해 보기로 했다. 

JNA 의 공식 배포 SIte 는 [여기](https://github.com/java-native-access/jna) https://github.com/java-native-access/jna 로 해당 Site 에서 library 를 내려 받을 수 있는데 해당 Page 에 접속 후 아래로 내려가면 **DOWNLOAD** 가 있고 그 아래 **JNA**, **JNA Platform** 이  있는데 글을 작성 하는 현재(2018.11.29) 시점에 [jna-5.1.0.jar](http://repo1.maven.org/maven2/net/java/dev/jna/jna/5.1.0/jna-5.1.0.jar)  와 [jna-platform-5.1.0.jar](http://repo1.maven.org/maven2/net/java/dev/jna/jna-platform/5.1.0/jna-platform-5.1.0.jar) 가 올라 와 있다. 이 글에서 테스트는 두개의 파일을 내려받은 것을 기준으로 작성 한다.

JNA 가 pure-java 로 되어 있지 않기 때문에 지원되는 OS 는 한정 되어 있다. 내부적으로 JNI 를 사용하고 Java 개발자 에게는 Java 관련 Interface 만 만들면 사용 할 수 있게 하여 사용의 편의성을 제공 하였다는 점은 높이 평가 할 만 하지만 최적화된 코드와 속도를 위해서는 역시 JNI 가 바람직 해 보인다. 하지만 속도에 민감하지 않은 Site 의 경우 JNA 를 사용 하는것도 나쁘지 않을것 같다. JNA 를 사용하는 Project 를 보면 [Apache Cassandra](http://cassandra.apache.org/), [IntelliJ IDEA](http://jetbrains.com/), [NetBeans IDE](http://netbeans.org/) 등 유명한 Project 에서도 JNA 를 사용 하는것 으로 되어 있다.

우선 JNA 에서 제공하는 Sample 을 돌려보고 내가 직접 C/C++ 로 dll 을 만든 다음 JNA 를 통하여 호출 하는 과정을 거쳐 JNA 사용법을 마칠 예정 이다.



### 1.  공식 SIte 에서 제공 하는 Sample 돌려보기

공식 홈페이지에 있는 `Using the Library` 장을 보면 `Getting Started` 가 있는데 클릭 하여 들어가면 HelloWorld.java Source 가 나온다. 그 소스를 가지고 테스트를 수행 한다.

#### 1.1 HelloWorld.java 

```java
// package com.sun.jna.examples;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/** Simple example of JNA interface mapping and usage. */
public class HelloWorld {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
            Native.load((Platform.isWindows() ? "msvcrt" : "c"),
                                CLibrary.class);

        void printf(String format, Object... args);
    }

    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, World\n");
        for (int i=0;i < args.length;i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }
}
```

- 테스트 환경은 Windows 이며 HelloWorld.java 파일이 있는 경로의 `lib` directory 에 `jna-5.1.0.jar` 와 `jna-platform-5.1.0.jar` 두개의 파일을 저장 하였다.
- <span style="color:red">간편하게 실행 하기 위해서 `package com.sun.jna.examples;` 부분은 주석 처리 하였다.</span>



#### 1.2 소스 컴파일

테스트 환경의 javac version 은 `javac 1.8.0_171` 이며 java version 은 `java version "1.8.0_191"` 로 되어 있다.

컴파일 은 다음 명령으로 할 수 있다.

```
javac -cp .;.\lib\jna-5.1.0.jar HelloWorld.java
```

오류 없이 컴파일이 되었으며 다음 2개의 Class 파일에 생성 되었다.

```
HelloWorld.class
HelloWorld$CLibrary.class
```



#### 1.3 실행

다음 명령으로 컴파일된 Code 를 실행 시켜본다.

```
java -cp .;.\lib\jna-5.1.0.jar HelloWorld
```

`실행 결과`

```
Hello, World
```

가 잘 표시 된다.





### 2. JNA 용 C dll 생성

#### 2.1 Dll 용 Cpp source

`main.cpp`

```cpp
#include "main.h"
#include <stdio.h>

// 두개의 문자열을 겹합 하여 결과를 돌려주는 함수
char* DLL_EXPORT strconcat(char* astr, char* bstr)
{
    static char resStr[256];

    sprintf_s(resStr, sizeof(resStr), "%s%s", astr, bstr );
    return resStr;
}

// 두개의 숫자를 더하고 결과를 돌려주는 함수
int DLL_EXPORT Add( int a, int b)
{
    return a + b;
}

// DLL main
extern "C" DLL_EXPORT BOOL APIENTRY DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    switch (fdwReason)
    {
        case DLL_PROCESS_ATTACH:
            break;

        case DLL_PROCESS_DETACH:
            break;

        case DLL_THREAD_ATTACH:
            break;

        case DLL_THREAD_DETACH:
            break;
    }
    return TRUE;
}

```



#### 2.2 Dll 용 header 파일

`main.h`

```h
#pragma once

#include <windows.h>

#ifdef BUILD_DLL
    #define DLL_EXPORT __declspec(dllexport)
#else
    #define DLL_EXPORT __declspec(dllimport)
#endif


#ifdef __cplusplus
extern "C"
{
#endif

char* DLL_EXPORT strconcat(char* astr, char* bstr);
int DLL_EXPORT Add( int a, int b);

#ifdef __cplusplus
}
#endif

```

- 두개의 파일을 이용한 컴파일 결과 `ConcatDll.dll` 파일이 생성 된다.



### 3. 사용자가 만든 dll 호출 하기

#### 3.1 Dll 호출 용 java 프로그램 Source

`CallDll.java`

```java
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class CallDll {
    public interface clib extends Library {
        clib INSTANCE = (clib) Native.load("ConcatDll.dll", clib.class);
        
        // ConcatDll.dll 에서 제공 하는 문자열 결합 함수
        String strconcat(String astr, String bstr);
        
        // ConcatDll.dll 에서 제공 하는 Add 함수
		int    Add( int a, int b);
    }

    public static void main(String[] args) {
        String concatStr = clib.INSTANCE.strconcat("Hello ", "World!");
		System.out.println( "Concatenate string : "  + concatStr );
		
		int  nRes = clib.INSTANCE.Add(123, 456);
		System.out.println( "Add : " + Integer.toString( nRes ));			
    }
}
```



#### 3.2 컴파일 및 실행

##### 3.2.1 컴파일

```
javac -cp .;.\lib\jna-5.1.0.jar CallDll.java
```



##### 3.2.2 실행

```
java -cp .;.\lib\jna-5.1.0.jar CallDll
```

`실행 결과`

```
Concatenate string : Hello World!
Add : 579
```

잘 실행 된다. 하지만 C 를 모르는 java 개발자 에게는 약간 아쉬운 점이 C Header 파일을 보고 interface 를 작성 하는 부분이 부담이 되거나, 귀찮다. 그래서 github 에 `JNAerator` 를 만든 사람(`Olivier Chafik`)이 있다. `JNAerator` 는 `JNA/BridJ/Node.js 용의 네이티브 바인딩 제네레이터` 로 github 주소는 [여기](https://github.com/nativelibs4java/JNAerator) https://github.com/nativelibs4java/JNAerator 이다. JNAerator 를 이용하여 Interface Source 를 Generation 해 보니 소스 가 엄청 지저분 하고 `org.bridj.??` 등 을 import 하여 source 를 생성 한다. 일단 dll 을 load 하는 부분도 보이지 않고 해서 제대로 동작 하지 않을것으로 보인다, `2013`년 이후에 Update 가 되지 않는것을 봐서 사용하지 않기로 했다. 그 대신 내가 Go 언어를 이용해서 간단한 JNA 용 Class Generation 하는 프로그램을 작성해 보기로 했다.



### 4. Go 언어를 이용한 Interface  Class 생성 프로그램 구현

Class Generator 를 만들기 위해서는 Java 와 C 사이의 Data Type Mapping 관계를 알아야 한다. 다음은 호환 Table 입니다.

| Native Type | Size                | Java Type  | Common Windows Types    |
| ----------- | ------------------- | ---------- | ----------------------- |
| char        | 8-bit integer       | byte       | BYTE, TCHAR             |
| short       | 16-bit integer      | short      | WORD                    |
| wchar_t     | 16/32-bit character | char       | TCHAR                   |
| int         | 32-bit integer      | int        | DWORD                   |
| int         | boolean value       | boolean    | BOOL                    |
| long        | 32/64-bit integer   | NativeLong | LONG                    |
| long long   | 64-bit integer      | long       | __int64                 |
| float       | 32-bit FP           | float      |                         |
| double      | 64-bit FP           | double     |                         |
| char*       | C string            | String     | LPTCSTR                 |
| void*       | pointer             | Pointer    | LPVOID, HANDLE, LP*XXX* |

Unsigned types use the same mappings as signed types. C enums are usually interchangeable with "int".



#### 4.1 Generator Source Code ( go 언어)

`GenJNA.go`

```go
package main

import (
	"bufio"
	"flag"
	"fmt"
	"os"
	"strings"
	_ "strutils"
)

var type_MAP = make(map[string]string)

func usage() {
	fmt.Println("Usage: go run GenJNA.go -sofile=dllname -header=headerfile -class=classname -prefix=[export prefix] -pkg=[java package name]")
	fmt.Println("Ex) go run GenJNA.go -sofile=CallDll.dll -header=main.h -class=TestJNA -prefix=DLL_EXPORT")
	os.Exit(0)
}

func main() {
	soFile := flag.String("sofile", "NULL", "dll filename or so file name")
	headerFile := flag.String("header", "NULL", "C/C++ Header file")
	className := flag.String("class", "NULL", "Output class name")
	exportPrefix := flag.String("prefix", "NULL", "EXPORT prefix")
	packageName := flag.String("pkg", "NULL", "Java Package name")

	flag.Parse()

	if *soFile == "NULL" || *headerFile == "NULL" || *className == "NULL" {
		usage()
	}

	if *exportPrefix == "NULL" {
		*exportPrefix = "DLL_EXPORT"
	}

	funcList, err := loadFile(*headerFile, *exportPrefix)
	if err != nil {
		panic(err)
	}

	makeMappingMap()

	os.Remove(*className + ".java")

	f, err := os.OpenFile(*className+".java", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		panic(err)
	}
	defer f.Close()

	if *packageName != "NULL" {
		f.WriteString("package " + *packageName + ";\n\n")
	}

	f.WriteString("import com.sun.jna.Library;\n")
	f.WriteString("import com.sun.jna.Native;\n")
	f.WriteString("import com.sun.jna.Platform;\n\n")

	f.WriteString("public class " + *className + " {\n")
	f.WriteString("    public interface clib extends Library { \n")
	f.WriteString("        clib INSTANCE = (clib) Native.load(\"" + *soFile + "\", clib.class);\n\n")

	for _, val := range funcList {
		javaFunc, err := cppToJava(val)
		if err != nil {
			panic(err)
		}
		f.WriteString("        " + javaFunc + "\n")
	}

	f.WriteString("    }\n")
	f.WriteString("}\n")
}

func loadFile(header string, prefix string) ([]string, error) {
	var listFunc []string

	fileReader, err := os.Open(header)
	if err != nil {
		fmt.Println(err)
		return listFunc, err
	}
	defer fileReader.Close()

	scanner := bufio.NewScanner(fileReader)

	for scanner.Scan() {
		if len(scanner.Text()) <= 0 {
			continue
		}
		v := strings.TrimLeft(scanner.Text(), " ")
		if err != nil {
			return listFunc, err
		}

		// If it is a preprocessor, skip it.
		if v[0] == '#' || len(v) <= 0 {
			continue
		}

		// If it is not a function, skip it.
		idx := strings.Index(v, "(")
		if idx <= 0 {
			continue
		}

		// If it is not a exported function, skip it.
		idx = strings.Index(v, prefix)
		if idx <= 0 {
			continue
		}

		// prefix remove
		v = strings.Replace(v, prefix, "", -1)
		v, err = trimWhiteSpace(v)
		if err != nil {
			return listFunc, err
		}

		listFunc = append(listFunc, v)
	}

	return listFunc, nil
}

func makeMappingMap() {
	type_MAP["char"] = "byte"
	type_MAP["short"] = "short"
	type_MAP["wchar_t"] = "char"
	type_MAP["int"] = "int"
	type_MAP["long"] = "NativeLong"
	type_MAP["long long"] = "long"
	type_MAP["float"] = "float"
	type_MAP["double"] = "double"
	type_MAP["char*"] = "String"
	type_MAP["void*"] = "Pointer"

	type_MAP["BYTE"] = "byte"
	type_MAP["TCHAR"] = "byte"

	type_MAP["WORD"] = "short"
	type_MAP["DWORD"] = "int"
	type_MAP["BOOL"] = "boolean"
	type_MAP["bool"] = "boolean"

	type_MAP["LONG"] = "NativeLong"
	type_MAP["__int64"] = "long"

	type_MAP["LPTCSTR"] = "String"
	type_MAP["LPVOID"] = "Pointer"
	type_MAP["HANDLE"] = "Pointer"
}

func trimWhiteSpace(in string) (string, error) {
	b := []byte(in)
	strLen := len(b)
	i := 0
	j := 0
	k := 0
	str := make([]byte, strLen)

	for i < strLen {
		if b[i] == 0x20 {
			j++
		} else {
			j = 0
		}

		if 1 < j {
			i++
			continue
		}

		str[k] = b[i]
		k++
		i++
	}

	return string(str), nil
}

func cppToJava(v string) (string, error) {
	rtnValue := ""

	rtnType, err := getReturnType(v)
	if err != nil {
		panic(err)
	}
	rtnValue = rtnValue + rtnType

	funcName, err := getFuncName(v)
	if err != nil {
		panic(err)
	}
	rtnValue = rtnValue + " " + funcName

	params, err := getParams(v)
	if err != nil {
		panic(err)
	}
	rtnValue = rtnValue + params

	return rtnValue, nil
}

func getReturnType(v string) (string, error) {
	idx := strings.Index(v, "(")
	tmp := v[0:idx]

	tmp = strings.Replace(tmp, "(", "", -1)
	words := strings.Split(tmp, " ")
	lenWord := len(words)
	rtnV := ""
	for i, val := range words {
		if i >= lenWord-1 {
			continue
		}
		if i == 0 {
			rtnV = val
		} else {
			rtnV = rtnV + " " + val
		}
	}

	rtnV = type_MAP[rtnV]

	return rtnV, nil
}

func getFuncName(v string) (string, error) {
	idx := strings.Index(v, "(")
	tmp := v[0:idx]

	tmp = strings.Replace(tmp, "(", "", -1)
	words := strings.Split(tmp, " ")
	lenWord := len(words)

	return words[lenWord-1], nil
}

func getParams(v string) (string, error) {
	idxS := strings.Index(v, "(")
	idxE := strings.Index(v, ")")
	allParams := v[idxS+1 : idxE]
	rtnStr := "("

	allParams, err := trimWhiteSpace(allParams)
	if err != nil {
		return rtnStr, nil
	}

	params := strings.Split(allParams, ",")

	for i, val := range params {
		spStr := strings.Split(val, " ")

		for _, strVal := range spStr {
			if len(strVal) <= 0 {
				continue
			}

			lastVal := type_MAP[strVal]

			if 0 < len(lastVal) {
				if i == 0 {
					rtnStr = rtnStr + lastVal
				} else {
					rtnStr = rtnStr + ", " + lastVal
				}

			} else {
				rtnStr = rtnStr + " " + strVal
			}
		}
	}

	rtnStr = rtnStr + ");"

	return rtnStr, nil
}

```



#### 4.2 JNA 용 Source 생성 테스트

JNA 용 Source 생성 테스트에 사용 할 header 파일은 `2.2 Dll 용 header 파일` 에 있는 `main.h` 파일을 이용 하였다.

`소스 생성 테스트 실행`

```
go run GenJNA.go -sofile=CallDll.dll -header=main.h -class=TestJNA -prefix=DLL_EXPORT
```

- 위 실행결과 `TestJNA.java` 파일이 생성 된다.

`TestJNA.java`

```java
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class TestJNA {
    public interface clib extends Library { 
        clib INSTANCE = (clib) Native.load("CallDll.dll", clib.class);

        String strconcat(String astr, String bstr);
        int Add(int a, int b);
    }
}
```

 이제 생성된 .java 코드를 이용해 JNA 를 사용 하면 된다.







