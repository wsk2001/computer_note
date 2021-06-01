# java File 클래스

출처: http://www.devkuma.com/books/pages/82

File 클래스는 입출력에 필요한 파일 및 디렉토리에 관한 정보를 다를 수 있다. File 클래스는 파일과 디렉토리의 접근 권한, 생성된 시간, 마지막 수정 일자, 크기, 경로 등의 정보를 얻을 수 메소드를 가지고 있으며, 새로운 파일과 디렉토리 생성 및 삭제, 이름 변경 등의 조작 메소드를 가지고 있다.

## File 클래스 변수

| 변수          | 설명                                                      |
| :------------ | :-------------------------------------------------------- |
| pathSeparator | 경로 구분자 문자(윈도우: "", 리눅스 및 유니스 계열 : "/") |
| separator     | 파일간이 구분자 문자(윈도우: ";", 리눅스 : ":")           |

자바는 앞서 설명을 했듯이, 플랫폼에 대해서 독립적이다. 이는 한번 컴파일을 하여 OS에 상관 없이 구동이 되기 때문에 각 OS에 따라 다르게 동작을 해야 할 필요가 있다. 파일은 OS마다 경로 및 파일간의 구분자가 다르기 때문에 문자열을 직접 입력하기 보다는 이 File 클래스 변수를 사용해야 OS에 상관 없이 오류 없는 프로그램을 구현할 수 있다.

## File 생성자

| 생성자                            | 설명                                                      |
| :-------------------------------- | :-------------------------------------------------------- |
| File(String pathname)             | 입력한 pathname(파일명 포함) 경로 파일의 객체를 생성한다. |
| File(String parent, String child) | parent 디렉토리 경로의 child 파일에 대한 객체를 생성한다. |
| File(File parent, String child)   | 파일 객체 parent의 child 파일에 대한 객체를 생성한다.     |
| File(URI uri)                     | uri 경로에 대한 파일 객체를 생성한다.                     |

## File 메소드

#### 파일 조회 관련 메소드

| 메소드                                  | 설명                                                         |
| :-------------------------------------- | :----------------------------------------------------------- |
| File getAbsoluteFile()                  | 파일의 절대 경로를 반환한다.                                 |
| String getAbsolutePath()                | 파일의 절대 경로를 문자열로 반환한다.                        |
| File getCanonicalFile()                 | 파일의 정규 경로를 반환한다.                                 |
| String getCanonicalPath()               | 파일의 정규 경로를 문자열로 반환한다.                        |
| String getName()                        | 파일이나 폴더의 이름을 넘겨준다.                             |
| String getParent()                      | 부모 경로에 대한 경로명을 문자열로 반환한다.                 |
| File getParentFile()                    | 부모 폴더를 File의 형태로 반환한다.                          |
| String getPath()                        | 파일의 경로를 문자열의 형태로 반환한다.                      |
| long getTotalSpace()                    | 하드디스크의 총 용량을 반환한다.                             |
| long getUsableSpace()                   | 하드디스크의 사용 가능한 용량을 반환한다.                    |
| long getFreeSpace()                     | 하드디스크의 남은 공간을 반환한다.                           |
| int hashCode()                          | hash code를 반환한다.                                        |
| long lastModified()                     | 해당 경로 파일의 최종 수정 일자를 반환한다.                  |
| long length()                           | 해당 경로 파일의 길이를 반환한다.                            |
| Path toPath()                           | java.nio.file.Path 객체로 반환한다.                          |
| URI toURI()                             | URI 형태로 파일 경로를 반환한다.                             |
| File[] listRoots()                      | 하드디스크의 루트 경로를 반환한다.                           |
| String[] list()                         | 경로의 파일들과 폴더를 문자열 배열로 반환한다.               |
| String[] list(FilenameFilter filter)    | filter에 만족되는 파일들과 폴더 이름을 문자열 배열로 반환한다. |
| File[] listFiles()                      | 해당 경로의 파일들과 폴더의 파일을 배열로 반환한다.          |
| File[] listFiles(FileFilter filter)     | filter에 만족되는 파일들과 폴더를 File 배열로 반환한다.      |
| File[] listFiles(FilenameFilter filter) | filter에 만족되는 파일들과 폴더를 File 배열로 반환한다.      |

#### 파일 조작(생성, 수정, 삭제) 관련 메소드

| 메소드                                                       | 설명                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| boolean createNewFile()                                      | 주어진 이름의 파일이 없으면 새로 생성한다.                   |
| static File createTempFile(String prefix, String suffix)     | 파일명에 prefix와 suffix(null이면 .tmp)를 붙여 임시 파일을 기본 임시 파일 디렉토리에 생성한다. |
| static File createTempFile(String prefix, String suffix, File directory) | 파일명에 입력한 prefix와 suffix(null이면 .tmp)를 붙여 directory에 임시 파일을 생성한다. |
| boolean delete()                                             | 파일이나 디렉토리를 삭제한다. 단, 디렉토리가 비어있지 않으면 삭제할 수 없다. |
| void deleteOnExit()                                          | 자바 가상 머신이 끝날 때 파일을 삭제한다.                    |
| boolean mkdir()                                              | 해당 경로에 폴더를 만든다. 생성 성공하면 true, 실패하면 false을 반환한다. |
| boolean mkdirs()                                             | 존재하지 않는 부모 디렉토리까지 포함하여 해당 경로에 폴더를 만든다. |
| boolean renameTo(File dest)                                  | 파일를 dest 로 변경한다. 이름뿐 아니라 경로도 변경된다.      |

#### 파일 체크 관련 메소드

| 메소드                | 설명                                       |
| :-------------------- | :----------------------------------------- |
| boolean exists()      | 파일의 존재 여부를 반환한다.               |
| boolean isAbsolute()  | 해당 경로가 절대 경로인지 여부를 반환한다. |
| boolean isDirectory() | 해당 경로가 디렉토리인지 여부를 반환한다.  |
| boolean isFile()      | 해당 경로가 file 인지 여부를 반환한다.     |
| boolean isHidden()    | 해당 경로가 숨김 파일인지 여부를 반환한다. |

#### 파일 권한 관련 메소드

| 메소드                                                       | 설명                                                 |
| :----------------------------------------------------------- | :--------------------------------------------------- |
| boolean canExecute()                                         | 파일 실행 권한이 있는지 여부를 반환한다.             |
| boolean canRead()                                            | 파일을 읽기 권한이 있는지 여부를 반환한다.           |
| boolean canWrite()                                           | 파일을 씨기 권한이 있는지 여부를 반환한다.           |
| boolean setExecutable(boolean executable)                    | 파일 소유자의 실행 권한을 설정한다.                  |
| boolean setExecutable(boolean executable, boolean ownerOnly) | 파일의 실행 권한을 소유자 또는 모두에 대해 설정한다. |
| boolean setReadable(boolean readable)                        | 파일의 소유자의 읽기 권한을 설정한다.                |
| boolean setReadable(boolean readable, boolean ownerOnly)     | 파일의 읽기 권한을 소유자 또는 모두에 대해 설정한다. |
| boolean setReadOnly()                                        | 파일을 읽기 전용으로 변경한다.                       |
| boolean setWritable(boolean writable)                        | 파일의 소유자의 쓰기 권한을 설정한다.                |
| boolean setWritable(boolean writable boolean ownerOnly)      | 파일의 쓰기 권한을 소유자 또는 모두에 대해 설정한다. |

## File 예제

#### 예제 1)

아래 예제는 특정 파일에 대한 정보를 화면에 표시해 준다.

```java
package com.devkuma.tutorial.java.io;

import java.io.File;
import java.io.IOException;

public class FileClass {

    public static void main(String[] args) {

        // file.txt 객체 생성한다.
        File file = new File("file.txt");

        // 파일이 존재 여부를 체크한다.
        if (file.exists()) {

            try {
                // 파일 이름을 표시한다.
                System.out.println("getName: " + file.getName());
                // 파일 경로을 표시한다.
                System.out.println("getPath: " + file.getPath());
                // 파일 절대 경로 표시한다.
                System.out.println("getAbsolutePath : " + file.getAbsolutePath());
                // 파일 정규 경로 표시한다.
                System.out.println("getCanonicalPath : " + file.getCanonicalPath());
                // 상위 디렉토리를 표시한다.
                System.out.println("getParent : " + file.getParent());

                // 파일의 쓰기/읽기 권한 체크한다.
                if (file.canWrite())
                    System.out.println(file.getName() + " can write.");
                if (file.canRead())
                    System.out.println(file.getName() + " can read.");

                // 객체의 파일, 폴더 여부 체크한다.
                if (file.isFile()) {
                    System.out.println(file.getName() + " is file");
                }
                if (file.isDirectory()) {
                    System.out.println(file.getName() + " is directory.");
                }

                // 파일 내용 길이를 표시한다.
                System.out.println(file.getName() + " length=" + file.length());

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("not found file");
        }
    }
}
```

실행 결과는 아래와 같습니다.

```sh
getName: file.txt
getPath: file.txt
getAbsolutePath : /Users/{user}/workspace/java-tutorial/file.txt
getCanonicalPath : /Users/{user}/workspace/java-tutorial/file.txt
getParent : null
file.txt can write.
file.txt can read.
file.txt is file
file.txt length=26
```

#### 예제 2)

아래 예제는 특정 디렉토리에 대한 파일 및 디렉토리리 목록을 표시해 준다.

```java
package com.devkuma.tutorial.java.io;

import java.io.File;

public class FileClass2 {

    public static void main(String[] args) {

        // 프로젝트 현재 디렉토리를 객체로 생성한다.
        File file = new File(".");

        // 폴더의 파일/폴더 목록을 문자열 배열로 반환
        String[] files = file.list();

        // 출력
        for (String fileName : files) {
            System.out.println(fileName);
        }
    }
}
```

실행 결과는 아래와 같습니다.

```sh
.classpath
.project
.settings
bin
file.txt
src
```

#### 예제 3)

아래 예제는 루트 경로를 표시해 주고 하드 디스크의 용량을 화면에 표시해 준다.

```java
package com.devkuma.tutorial.java.io;

import java.io.File;

public class FileClass3 {

    public static void main(String[] args) {

        // 하드디스크의 루트 경로(드라이브)들을 배열로 반환한다.
        File[] roots = File.listRoots();

        for (File root : roots) {

            // 루트 경로(드라이브)의 절대 경로
            String drive = root.getAbsolutePath();
            // 하드디스크 전체 용량
            long totalSpace = root.getTotalSpace();
            // 사용가능한 디스크 용량
            long usableSpace = root.getUsableSpace();
            // 여유 디스크 용량
            long freeSpace = root.getFreeSpace();
            // 사용한 디스크 용량
            long usedSpace = totalSpace - usableSpace;

            System.out.println("root : " + drive);
            System.out.println("Total Space : " + totalSpace);
            System.out.println("Usable Space : " + usableSpace);
            System.out.println("Free Space : " + freeSpace);
            System.out.println("Used Space : " + usedSpace);
        }
    }
}
```

실행 결과는 아래와 같습니다.

```shell
root : /
Total Space : 598000009216
Usable Space : 100067610624
Free Space : 100329754624
Used Space : 497932398592
```