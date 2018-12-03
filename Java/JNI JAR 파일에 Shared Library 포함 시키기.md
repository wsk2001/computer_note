## JNI JAR 파일에 Shared Library 포함 시키기

JNI 를 이용하여 Native Library 를 호출 하려면 Run-Time 에 동적으로 Shared Library(.dll, .so, .a 등)를 Load 해야 한다. 그런데 Shared Library 를 찾지 못하는 경우가 가끔 발생 한다. 특히 WAS 환경 등 에서 사용 할 경우 발견하지 못하는 경우가 있는데 이때를 대비해서 Shares Library 를 JAR 파일 내부에 포함 시켜놓고 참조 할 수 있도록 하는 방법이 있어 포스팅 하기로 하였다.

이 방법은 JNA 를 Survey 하던 도중 생각이 나서 자료를 좀 찾아보고 테스트를 해 보기로 하였다.

우선 stackoverflow 의 [여기](https://stackoverflow.com/questions/2937406/how-to-bundle-a-native-library-and-a-jni-library-inside-a-jar) 에 보면 사용 하는 방법이 나와있다.  참고 예제는 다음과 같다.

```java
static {
    try {
        // used for tests. This library in classpath only
        System.loadLibrary("crypt"); 
    } catch (UnsatisfiedLinkError e) {
        try {
            // during runtime. .DLL within .JAR
            NativeUtils.loadLibraryFromJar("/natives/crypt.dll"); 
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }
}
```

- 여기서 crypt.dll 파일은 jar 파일의 `/native/crypt.dll` 에 위치 하여야 System.loadLibrary("crypt") 에 의해 호출 실패시 다시 load 할 수 있다.



### 1. NativeUtils.loadLibraryFromJar 소스코드

NativeUtils.loadLibraryFromJar 의 소스 코드는 [github](https://github.com/adamheinrich/native-utils) 에 공개되어 있다.  공개된 소스 코드는 다음과 같다.

```java
/*
 * Class NativeUtils is published under the The MIT License:
 *
 * Copyright (c) 2012 Adam Heinrich <adam@adamh.cz>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cz.adamh.utils;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.StandardCopyOption;

/**
 * A simple library class which helps with loading dynamic libraries stored in the
 * JAR archive. These libraries usually contain implementation of some methods in
 * native code (using JNI - Java Native Interface).
 * 
 * @see <a href="http://adamheinrich.com/blog/2012/how-to-load-native-jni-library-from-jar">http://adamheinrich.com/blog/2012/how-to-load-native-jni-library-from-jar</a>
 * @see <a href="https://github.com/adamheinrich/native-utils">https://github.com/adamheinrich/native-utils</a>
 *
 */
public class NativeUtils {
 
    /**
     * The minimum length a prefix for a file has to have according to {@link File#createTempFile(String, String)}}.
     */
    private static final int MIN_PREFIX_LENGTH = 3;
    public static final String NATIVE_FOLDER_PATH_PREFIX = "nativeutils";

    /**
     * Temporary directory which will contain the DLLs.
     */
    private static File temporaryDir;

    /**
     * Private constructor - this class will never be instanced
     */
    private NativeUtils() {
    }

    /**
     * Loads library from current JAR archive
     * 
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file is deleted after
     * exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     * 
     * @param path The path of file inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @throws IOException If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters
     * (restriction of {@link File#createTempFile(java.lang.String, java.lang.String)}).
     * @throws FileNotFoundException If the file could not be found inside the JAR.
     */
    public static void loadLibraryFromJar(String path) throws IOException {
 
        if (null == path || !path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }
 
        // Obtain filename from path
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;
 
        // Check if the filename is okay
        if (filename == null || filename.length() < MIN_PREFIX_LENGTH) {
            throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
        }
 
        // Prepare temporary file
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory(NATIVE_FOLDER_PATH_PREFIX);
            temporaryDir.deleteOnExit();
        }

        File temp = new File(temporaryDir, filename);

        try (InputStream is = NativeUtils.class.getResourceAsStream(path)) {
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            temp.delete();
            throw e;
        } catch (NullPointerException e) {
            temp.delete();
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        try {
            // 여기에서 복사된 Shared Object 를 load 한다.
            System.load(temp.getAbsolutePath());
        } finally {
            if (isPosixCompliant()) {
                // Assume POSIX compliant file system, can be deleted after loading
                temp.delete();
            } else {
                // Assume non-POSIX, and don't delete until last file descriptor closed
                temp.deleteOnExit();
            }
        }
    }

    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault()
                    .supportedFileAttributeViews()
                    .contains("posix");
        } catch (FileSystemNotFoundException
                | ProviderNotFoundException
                | SecurityException e) {
            return false;
        }
    }

    private static File createTempDirectory(String prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix + System.nanoTime());
        
        if (!generatedDir.mkdir())
            throw new IOException("Failed to create temp directory " + generatedDir.getName());
        
        return generatedDir;
    }
}
```

- 한가지 더 봐야 할 부분은 역시 JNA 를 보면서 확인 한 부분 인데 각 OS 별로 Shared Object 가 다른 binary 로 배포가 되어야 하는데 동작하는 OS 를 확인 하여 필요한 Shared Object 를 Load 할 수 구현 해 보자.
- 이때 JNI 에서 호출 하는 Interface 함수는 동일 하게 작성 되어 있어야 각 OS 별 오류 없이 동작 하게 된다.

- OS 및 Architecture 확인은 System.getProperty("os.name"),  System.getProperty("os.arch") 를 이용하면 된다.

### 2. 간단한 OS 및 Arch 확인 소스

`GetOsArch.java`

```java
import java.io.File;
import java.io.IOException;

public class GetOsArch {
	public static void main(String[] args) {
		System.out.println( "os.name " + System.getProperty("os.name") );
		System.out.println( "os.arch " + System.getProperty("os.arch") );
		if( is64Bit( System.getProperty("os.arch")) == true ) {
			System.out.println( "os bit => 64");
		} else {
			System.out.println( "os bit => 32");
		}
	}

	public static final boolean is64Bit( String ARCH) {
        String model = System.getProperty("sun.arch.data.model",
                                          System.getProperty("com.ibm.vm.bitmode"));
        if (model != null) {
            return "64".equals(model);
        }
        if ("x86-64".equals(ARCH)
            || "ia64".equals(ARCH)
            || "ppc64".equals(ARCH) || "ppc64le".equals(ARCH)
            || "sparcv9".equals(ARCH)
            || "mips64".equals(ARCH) || "mips64el".equals(ARCH)
            || "amd64".equals(ARCH)) {
            return true;
        }
		return false;
    }
}
```

- full source 는 https://github.com/java-native-access/jna/blob/master/src/com/sun/jna/Platform.java 참조



### 3.   Jar 에 Shared Library 를 넣는 예제

`directory 구조`

```
.
├── class
|   └── nativeso
|       ├──  SO
|       |     ├── Linux64
|       |     |   └── libNativeSO.so
|       |     └── Windows64
|       |         └── NativeSO.dll
|       ├── NativeSO.class
|       └── NativeUtils.class
├── src
|   └── nativeso  
|       ├── NativeSO.java
|       └── NativeUtils.java
├── HelloJni.c
├── HelloJniTest.java
├── Makefile.linux64
├── Makefile.win64
└── NativeSO.jar

```



`파일 설명`

| 파일명         | 파일 설명 |
| -------------- | --------- |
| libNativeSO.so | Linux x64, Java 에서 창조 할 Native Lib     |
| NativeSO.dll                        | Windows x64, Java 에서 창조 할 Native Lib |
| NativeSO.java, NativeSO.class | Native Interface 및 Load 용 Java library |
| NativeUtils.java, NativeUtils.class | Native library 를 수동 load 하기 위한 Class |
| HelloJni.c | Native library source |
| HelloJniTest.java | JNI Test용 소스 파일 |
| Makefile.linux64 | JNI 를 만드는 Linux 64bit 용 Makefile |
| Makefile.win64 | JNI 를 만드는 Windows 64bit 용 Makefile |
| NativeSO.jar | 컴파일 결과 최종 Jar 파일 |

아래는 각 소스 파일이다.



#### 3.1  NativeSO.java

```java
package  nativeso;

import java.io.*;
import java.util.*;

public class NativeSO {

    private static String _sLoadLibruryError = "";

    private NativeSO()
    {
		 
    }

    public static native String javaHello( String name );

    public static String sayHello( String name )
    {        
        String str = javaHello( name ); 
        return str;
    }

    static {
        try {
          System.loadLibrary("NativeSO");
        }catch (UnsatisfiedLinkError e) {
        	try {
				String osArch = getOsArch();
				
				if( "Windows64".startsWith(osArch) )
				{
					NativeUtils.loadLibraryFromJar("/nativeso/SO/Windows64/NativeSO.dll");
				}
				else if("Linux64".startsWith(osArch))
				{
					NativeUtils.loadLibraryFromJar("/nativeso/SO/Linux64/libNativeSO.so");
				}
				else
				{
					System.out.println("Can not Support OS " + osArch );
					System.exit(-1);
				}
            	 
        	} catch (IOException e1) {
            	throw new RuntimeException(e1);
        	}
    	}
    }
	
	public static final String getOsArch() {
		String osName = System.getProperty("os.name");
		String osAndArch = "";
		
		if (osName.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                osAndArch = "Android";
            }
            else {
                osAndArch = "Linux";
            }
        }
        else if (osName.startsWith("AIX")) {
            osAndArch = "Aix";
        }
        else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
            osAndArch = "Mac";
        }
        else if (osName.startsWith("Windows CE")) {
            osAndArch = "WinCE";
        }
        else if (osName.startsWith("Windows")) {
            osAndArch = "Windows";
        }
        else if (osName.startsWith("Solaris") || osName.startsWith("SunOS")) {
            osAndArch = "Solaris";
        }
        else if (osName.startsWith("FreeBSD")) {
            osAndArch = "FreeBSD";
        }
        else if (osName.startsWith("OpenBSD")) {
            osAndArch = "OpenBSD";
        }
        else if (osName.equalsIgnoreCase("gnu")) {
            osAndArch = "gnu";
        }
        else if (osName.equalsIgnoreCase("gnu/kfreebsd")) {
            osAndArch = "kfreebsd";
        }
        else if (osName.equalsIgnoreCase("netbsd")) {
            osAndArch = "netbsd";
        }
        else {
            osAndArch = "Unspecified";
        }	

		String osBit = "";
		String model = System.getProperty("sun.arch.data.model",
                                          System.getProperty("com.ibm.vm.bitmode"));
        if (model != null) {
           if( "64".equals(model) == true )
		   {
			   osBit = "64";
		   }
		   else
		   {
			   osBit = "32";
		   }
        }
		else
		{
			String ARCH = System.getProperty("os.arch");
			if(	"x86-64".equals(ARCH)   || "ia64".equals(ARCH)    || 
			    "ppc64".equals(ARCH)    || "ppc64le".equals(ARCH) || 
				"sparcv9".equals(ARCH)  || "mips64".equals(ARCH)  || 
				"mips64el".equals(ARCH) || "amd64".equals(ARCH)) {
				osBit = "64";
		   }
		   else
		   {
			   osBit = "32";
		   }
		}
		
		osAndArch = osAndArch + osBit;
					
		return osAndArch;
	}	
}

```





#### 3.2  NativeUtils.java

```java
package  nativeso;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.StandardCopyOption;

public class NativeUtils {
    private static final int MIN_PREFIX_LENGTH = 3;
    public static final String NATIVE_FOLDER_PATH_PREFIX = "nativeutils";

    private static File temporaryDir;

    private NativeUtils() {
    }

    public static void loadLibraryFromJar(String path) throws IOException {
 
        if (null == path || !path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }
 
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;
 
        if (filename == null || filename.length() < MIN_PREFIX_LENGTH) {
            throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
        }
 
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory(NATIVE_FOLDER_PATH_PREFIX);
            temporaryDir.deleteOnExit();
        }

        File temp = new File(temporaryDir, filename);

        try (InputStream is = NativeUtils.class.getResourceAsStream(path)) {
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            temp.delete();
            throw e;
        } catch (NullPointerException e) {
            temp.delete();
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        try {
            System.load(temp.getAbsolutePath());
        } finally {
            if (isPosixCompliant()) {
                temp.delete();
            } else {
                temp.deleteOnExit();
            }
        }
    }

    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault()
                    .supportedFileAttributeViews()
                    .contains("posix");
        } catch (FileSystemNotFoundException
                | ProviderNotFoundException
                | SecurityException e) {
            return false;
        }
    }

    private static File createTempDirectory(String prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix + System.nanoTime());
        
        if (!generatedDir.mkdir())
            throw new IOException("Failed to create temp directory " + generatedDir.getName());
        
        return generatedDir;
    }
}

```



#### 3.3 HelloJni.c

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <memory.h>

#include "nativeso_NativeSO.h"


JNIEXPORT jstring JNICALL Java_nativeso_NativeSO_javaHello
  (JNIEnv *env, jclass jcla, jstring name)
{
	jstring result = NULL;
	static char Buff[ 1024 ];

	const  char *pName = (*env)->GetStringUTFChars(env,name, NULL);
	
	memset( Buff, 0x00, sizeof(Buff) );
	sprintf( Buff, "Hello %s!", pName);
	result = (*env)->NewStringUTF(env, Buff);
	
	(*env)->ReleaseStringUTFChars(env, name,  pName);
	
	 return result;
}
```



#### 3.4 HelloJniTest.java

```java
import static nativeso.NativeSO.*;

public class HelloJniTest
{
	public static void main(String[] args)
	{
		String msg = "";
		
		try
		{
			msg = sayHello("World");
			System.out.println("So Call Test : " + msg );
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```



#### 3.5 Makefile.linux64

```makefile
JDK_HOME= /usr/lib/jvm/java-8-openjdk-amd64

JBIN	= $(JDK_HOME)/bin

CC      = gcc

CP		= cp

CFLAGS  = -m64 -fPIC -DMACHINE64 -D_REENTRANT \
		-I$(JDK_HOME)/include \
		-I$(JDK_HOME)/include/linux

LFLAGS  = -lm -lrt


all: ./class/nativeso/NativeSO.class \
	nativeso_NativeSO.h libNativeSO.so NativeSO.jar HelloJniTest.class
	

./class/nativeso/NativeSO.class: ./src/nativeso/NativeSO.java
	$(JBIN)/javac -d ./class ./src/nativeso/NativeUtils.java ./src/nativeso/NativeSO.java 
	
nativeso_NativeSO.h: ./src/nativeso/NativeSO.java
	$(JBIN)/javah -classpath ./class -jni nativeso.NativeSO
	
libNativeSO.so: HelloJni.c nativeso_NativeSO.h
	$(CC) -c HelloJni.c $(CFLAGS)
	$(CC) $(CFLAGS) -Wall -o $@  HelloJni.o $(LFLAGS) -ldl -shared
	mv $@ ./class/nativeso/SO/Linux64

NativeSO.jar: ./class/nativeso/NativeSO.class
	$(JBIN)/jar cvf NativeSO.jar -C ./class nativeso

HelloJniTest.class: HelloJniTest.java
	$(JBIN)/javac -cp NativeSO.jar HelloJniTest.java
	

test:
	$(JBIN)/java -cp NativeSO.jar:. HelloJniTest
	
clean:
	rm -f *.class *.o *.a *.so *.jar
	rm -f nativeso_NativeSO.h

```





#### 3.6 Makefile.win64

```Makefile
JDK_HOME= C:\Tools\Java\jdk1.8.0_171

JBIN	= $(JDK_HOME)\bin

CC      = cl

CP		= cp

CFLAGS  = -I$(JDK_HOME)\include \
		-I$(JDK_HOME)\include\win32

LFLAGS  = /LD 


all: ./class/nativeso/NativeSO.class \
	nativeso_NativeSO.h NativeSO.dll NativeSO.jar HelloJniTest.class
	

./class/nativeso/NativeSO.class: ./src/nativeso/NativeSO.java
	$(JBIN)\javac -d ./class ./src/nativeso/NativeUtils.java ./src/nativeso/NativeSO.java 
	
nativeso_NativeSO.h: ./src/nativeso/NativeSO.java
	$(JBIN)\javah -classpath ./class -jni nativeso.NativeSO
	
NativeSO.dll: HelloJni.c nativeso_NativeSO.h
	$(CC) -c HelloJni.c $(CFLAGS)
	$(CC) $(LFLAGS) -o $@  HelloJni.obj 
	mv $@ ./class/nativeso/SO/Windows64

NativeSO.jar: ./class/nativeso/NativeSO.class
	$(JBIN)\jar cvf NativeSO.jar -C ./class nativeso

HelloJniTest.class: HelloJniTest.java
	$(JBIN)\javac -cp NativeSO.jar HelloJniTest.java
	
test:
	$(JBIN)\java -cp NativeSO.jar;. HelloJniTest
	
clean:
	rm -f *.class *.o *.a *.so *.jar
	rm -f nativeso_NativeSO.h
	rm -f .\class\nativeso\SO\Windows64\*.dll

```



위의 각 Source 및 Makefile 만 가지고 있으면 .jar 파일 내부에 .so 또는 .dll  을  포함하여 배포가 가능 할 것으로 보인다.



### - 끝 -


















