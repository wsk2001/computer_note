# java.nio.channels.FileChannel Example

출처: https://examples.javacodegeeks.com/core-java/nio/filechannel/java-nio-channels-filechannel-example/

이 기사에서는 FileChannel 클래스와 기본 사용법을 소개합니다. 이 클래스는 Java NIO(New IO) 파일 API의 일부로 Java 1.4부터 사용할 수 있습니다. 이 문서에서는 파일 채널을 사용하여 파일을 읽고 파일에 쓰는 방법을 보여줍니다.

이 기사의 예제는 Windows OS 환경에서 컴파일되고 실행됩니다. 코드를 실행하려면 Java SE 7이 필요합니다.



### 1. 소개

NIO를 사용하면 Java 프로그래머가 사용자 지정 네이티브 코드를 작성하지 않고도 고속 I/O를 구현할 수 있습니다. NIO는 가장 시간이 많이 걸리는 I/O 활동(예: 버퍼 채우기 및 배출)을 운영 체제로 다시 이동하여 속도를 높입니다. NIO는 기존 I/O보다 I/O를 더 빠르게 만듭니다.

Java NIO 라이브러리는 NIO API를 구현합니다. Java NIO는 Java IO를 대체하는 것이 아니라 보완하는 것입니다. Java 1.4 이후 Java SE 5, 6 및 7 버전의 NIO(및 IO)가 향상되었습니다.

버퍼와 채널은 NIO의 주요 개념입니다.



### 2. 버퍼 및 채널

버퍼는 메모리 내 컨테이너에 고정된 양의 기본 데이터 요소를 저장하는 메커니즘을 제공합니다. NIO에서 모든 데이터는 버퍼로 처리됩니다. 데이터를 읽을 때 버퍼로 직접 읽습니다. 데이터가 기록되면 버퍼에 기록됩니다.

버퍼는 채널과 함께 작동합니다. 채널은 I/O 전송이 이루어지는 포털이며 버퍼는 이러한 데이터 전송의 소스 또는 대상입니다.

이 문서의 예제에서는 ByteBuffer 및 FileChannel 클래스를 사용합니다. ByteBuffer는 java.nio 패키지에, FileChannel은 java.nio.channels 패키지에 정의되어 있습니다. 파일을 읽고 대상으로 데이터를 이동하려면 채널을 통해 파일을 버퍼로 읽은 다음 버퍼에서 대상으로 데이터를 이동합니다. 소스에서 파일에 쓰기 – 소스 데이터는 버퍼로 이동한 다음 채널을 통해 파일에 씁니다.



### 3. FileChannel을 사용하여 파일 읽기 및 쓰기

파일 채널은 읽기/쓰기 채널이며 항상 차단됩니다. 파일 읽기, 쓰기, 매핑 및 조작을 위한 채널입니다. FileChannel 개체는 스레드로부터 안전합니다.

FileChannel 인스턴스는 열린 파일 객체(RandomAccessFile, FileInputStream 또는 FileOutputStream)에서 getChannel() 메서드를 호출하거나 파일 경로가 제공되는 FileChannel의 open() 정적 메서드에서 가져올 수 있습니다. 메서드는 기본 파일에 연결된 FileChannel 개체를 반환합니다. 이 문서에서는 채널을 만드는 두 가지 방법을 모두 보여줍니다.

다음 섹션에서는 파일 채널을 사용하여 파일을 읽고 파일에 쓰는 두 가지 예를 설명합니다.



### 4. 파일에서 읽기

#### 4.1. Input file:

이것은 내용이 있는 기존 텍스트 파일입니다: 1234567890

#### 4.2. Create a channel:

파일 채널의 open() 정적 메서드는 채널을 만드는 데 사용됩니다. 이 메서드는 파일을 열고 FileChannel을 반환하여 제공된 파일에 액세스합니다.

``` java
Path path = Paths.get("readfile.txt");
FileChannel fileChannel = FileChannel.open(path);
```

#### 4.3. Create a buffer:

ByteBuffer의 allocate() 정적 메서드를 사용하여 ByteBuffer를 만듭니다. 새 버퍼의 위치는 0이고 한계는 용량이며 요소는 0으로 초기화됩니다. 이 예에서 초기 용량은 6으로 설정됩니다.

``` java
ByteBuffer buffer = ByteBuffer.allocate(6);
```

#### 4.4. 채널에서 버퍼로 읽기:

FileChannel의 read() 메서드는 바이트 시퀀스를 주어진 버퍼로 읽습니다. 이 메서드는 읽은 바이트 수를 반환하거나 채널이 스트림 끝에 도달한 경우 -1을 반환합니다.

``` java
int noOfBytesRead = fileChannel.read(buffer);
```

바이트는 채널의 현재 파일 위치(처음에는 0)에서 시작하여 읽은 다음 파일 위치는 실제로 읽은 바이트 수로 업데이트됩니다(예에서 위치는 초기 읽기 후 6임). 채널의 position() 메서드는 현재 위치를 반환합니다.

ByteBuffer에는 position() 메서드도 있습니다. 처음에는 0입니다. 첫 번째 읽기 후 값은 6입니다. 버퍼의 flip() 메서드는 상대적 get 작업의 새로운 시퀀스를 위해 버퍼를 준비합니다. 현재 위치(이 예에서는 6)로 제한을 설정한 다음 위치를 다음으로 설정합니다. 0.

``` java
buffer.flip();
```

#### 4.5. 버퍼 내용을 인쇄합니다.

``` java
while (buffer.hasRemaining()) {
    System.out.print((char) buffer.get());
}
```

버퍼의 clear() 메서드는 채널 읽기의 새로운 시퀀스를 위해 버퍼를 준비합니다. 용량 제한(6)과 위치를 0으로 설정합니다.

``` java
buffer.clear();
```

#### 4.6. Close:

파일 채널의 close() 메서드는 이 채널을 닫습니다.

예외: 예제에서 FileChannel의 open(), close(), position() 및 read() 메소드는 IOException을 발생시킵니다.

다음은 파일 채널을 사용하여 파일에서 읽는 것을 보여주는 예제의 전체 코드입니다.

FileChannelRead.java

``` java
import java.io.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileReader;
import java.io.BufferedReader;
 
public class FileChannelRead {
    public static void main (String [] args)
            throws Exception {
 
        new FileChannelRead().readFile();
    }
    private void readFile()
            throws IOException {
 
        String filePath = "readfile.txt";
        printFileContents(filePath);
        Path path = Paths.get(filePath);
 
        FileChannel fileChannel =  FileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(6);
        int noOfBytesRead = fileChannel.read(buffer);
         
        while (noOfBytesRead != -1) {
            System.out.println("Number of bytes read: " + noOfBytesRead);
            buffer.flip();
            System.out.print("Buffer contents: ");
 
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());                    
            }
 
            System.out.println(" ");
            buffer.clear();
            noOfBytesRead = fileChannel.read(buffer);
        }
        fileChannel.close();
    }
    private void printFileContents(String path)
            throws IOException {
 
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String textRead = br.readLine();
        System.out.println("File contents: ");
 
        while (textRead != null) {
            System.out.println("     " + textRead);
            textRead = br.readLine();
         }
         fr.close();
         br.close();
    }
}
```

출력은 다음과 같습니다.

``` cmd
File contents: 1234567890
Number of bytes read: 6
Buffer contents: 123456
Number of bytes read: 4
Buffer contents: 7890
```

출력에서:

- File contents: 1234567890; 이것은 파일의 텍스트입니다..
- Number of bytes read: 6, Buffer contents: 123456. 이것은 첫 번째 읽기입니다. 처음 6바이트는 채널에서 버퍼로 읽히고 버퍼의 내용은 123456입니다..
  NOTE: 이 시점에서 버퍼와 파일 채널 위치는 동일합니다. 6. 버퍼의 flip() 메서드는 위치를 0으로 변경합니다..
- Number of bytes read: 4, Buffer contents: 7890. 이것은 후속(및 마지막) 읽기입니다. 나머지 4바이트를 읽고 인쇄합니다..
  NOTE: 이제 채널의 위치는 10이고 버퍼의 위치는 4입니다.



### 5. 파일에 쓰기

이 예는 버퍼 소스에서 파일 채널을 통해 파일에 쓰는 단계를 보여줍니다. 그런 다음 파일 내용이 인쇄됩니다.

#### 5.1. The input:

입력은 문자열입니다. 문자열은 버퍼에 제공되는 바이트 배열로 변환됩니다.

``` java
String input = "file channel example";
byte [] inputBytes = input.getBytes();
```

#### 5.2. 버퍼 생성:

파일 채널의 wrap() 정적 메서드는 바이트 배열을 버퍼로 래핑합니다. 새 버퍼의 용량과 제한은 array.length가 되고 초기 위치는 0이 됩니다.

``` java
ByteBuffer buffer = ByteBuffer.wrap(inputBytes);
```

#### 5.3. 채널을 만듭니다.

FileOutputStream의 getChannel() 메서드는 채널을 만드는 데 사용됩니다. 이 메서드는 기본 파일에 연결된 파일 채널을 반환합니다.

``` java
String filePath = "writefile.txt";
FileOutputStream fos = new FileOutputStream(filePath);
FileChannel fileChannel = fos.getChannel();
```

이 예에서는 처음에는 파일이 존재하지 않습니다. 위의 코드 스니펫에 표시된 대로 이 프로그램에 의해 생성됩니다.

#### 5.4. 버퍼를 채널의 파일에 씁니다.

파일 채널의 write() 메서드는 주어진 버퍼에서 이 채널에 일련의 바이트를 씁니다. 바이트는 이 채널의 현재 파일 위치(이 경우 0)에서 시작하여 기록됩니다.

``` java
int noOfBytesWritten = fileChannel.write(buffer);
```

#### 5.5. 리소스 닫기:

파일 채널과 파일 출력 스트림을 닫습니다.

``` java
fileChannel.close();
fos.close();
```

#### 5.6. 파일 내용을 인쇄합니다.

파일 내용이 터미널 출력에 인쇄됩니다.

예외:

- FileOutputStream의 생성자는 FileNotFoundException을 발생시킵니다.

- FileChannel의 write(), close() 및 FileOutputStream의 close() 메서드는 IOException을 발생시킵니다.

다음은 파일 채널을 사용하여 파일에 쓰는 것을 보여주는 예제의 전체 코드입니다.

FileChannelWrite.java

``` java
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileReader;
import java.io.BufferedReader;
 
public class FileChannelWrite {
    public static void main (String [] args)
            throws Exception {
 
        new FileChannelWrite().writeFile();
    }
    private void writeFile()
            throws IOException {
 
        String input = "file channel example";
        System.out.print("Input string: " + input);
 
        byte [] inputBytes = input.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(inputBytes);
 
        String filePath = "writefile.txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        FileChannel fileChannel = fos.getChannel();
        fileChannel.write(buffer);
        fileChannel.close();
        fos.close();
 
        printFileContents(filePath);
    }
    private void printFileContents(String path)
            throws IOException {
 
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String textRead = br.readLine();
        System.out.println("File contents: ");
         
        while (textRead != null) {
         
            System.out.println("     " + textRead);
            textRead = br.readLine();
        }
        fr.close();
        br.close();
    }
}
```

출력은 다음과 같습니다.

``` sh
Input string: file channel example
File contents: file channel example
```

출력에서:

- Input string: file channel example; 이것은 파일에 기록되는 버퍼에 대한 입력입니다.
- File contents: file channel example; 이것은 소스 버퍼에서 쓰여진 파일의 내용입니다. 입력 문자열과 파일의 내용은 동일합니다 – 파일 채널 예



### 6. 자바 소스 코드 다운로드
이것은 java.nio.channels.FileChannel의 예입니다.

이 예제의 전체 소스 코드는 [FileChannelExamples.zip](https://examples.javacodegeeks.com/wp-content/uploads/2014/11/FileChannelExamples.zip) 에서 다운로드할 수 있습니다.