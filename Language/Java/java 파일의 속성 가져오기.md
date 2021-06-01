# java 파일의 속성 가져오기 

출처: http://forum.falinux.com/zbxe/index.php?document_srl=580020&mid=lecture_tip

이번 시간은 파일 속성을 가져오는 방법에 대해 알아보겠습니다. **Files.readAttributes**로 전체 속성 가져오는 방법과 **Files.getAttributes**로개별 속성을 가져오는 방법에대해 알아보도록 하겠습니다.



**Files.readAttributes(파일 패스, View 클래스);**

View클래스는 여섯 가지가 있습니다.

- BasicFileAttributeView 
- DosFileAttributeView
- PosixFileAttributeView
- FileOwnerFileAttributeView
- AclFileAttributeView
- UserDefinedFileAttributeView

여기에서는 기본속성(마지막 수정일, 파일 크기, 생성날짜등...)을 지원하는 BasicFileAttributeView대한 것만 다루도록 하겠습니다.



**Files.getAttributes(파일패스, 뷰이름:속성, 링크옵션);**

BasicFileAttributeView의 속성뷰 이름은 basic입니다. BasicFileAttributeView에서 사용할 수 있는 속성은

- size
- creationTime
- lastAccessTime
- lastModifiedTime
- isDirectory
- isRegularFile
- isSymbolicLink
- isOther
- fileKey

링크옵션은 심볼 링크를 해석하지 않는 LinkOption.NOFOLLOW_LINKS을 사용한다.

[샘플 소스]

``` java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
 
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
 
public class FileAttributes {
 
	public static void main(String[] args) {
 
		BasicFileAttributes attrb = null;
		Path path = Paths.get("D:/test/", "Hellow.txt");
 
		try {
			attrb = Files.readAttributes(path, BasicFileAttributes.class);
 
			System.out.println("----------- 파일 전체 속성 -------------");
			System.out.printf("파일 크기           : %d \n", attrb.size()); // 단위는 바이트
			System.out.printf("파일 생성 날짜       : %s \n", attrb.creationTime());
			System.out.printf("파일 마지막 읽은 날짜 : %s \n", attrb.lastAccessTime());
			System.out.printf("파일 마지막 수정일    : %s \n", attrb.lastModifiedTime());
 
			System.out.printf("디렉토리 입니까?      %s \n", attrb.isDirectory());
			System.out.printf("파일 입니까?         %s \n", attrb.isRegularFile());
			System.out.printf("심볼릭 링크 입니까?   %s \n", attrb.isSymbolicLink());
			System.out.printf("Other?             %s \n", attrb.isOther());
			System.out.println();
 
			///////////////////////
			// 각 개별 속성 가져오기
			///////////////////////
			System.out.println("----------- 파일 개별 속성 -------------");
			// 파일 크기 속성
			long size = (Long) Files.getAttribute(path, "basic:size", NOFOLLOW_LINKS);
			System.out.printf("파일 크기                   : %d \n", size); // 단위는 바이트
 
			// 파일 생성 날짜
			FileTime creationTime = (FileTime)Files.getAttribute(path, "basic:creationTime", NOFOLLOW_LINKS);
			System.out.printf("파일 생성 날짜            : %s \n", creationTime);
 
			// 파일 마지막 읽은 날짜
			FileTime lastAccessTime = (FileTime)Files.getAttribute(path, "basic:lastAccessTime", NOFOLLOW_LINKS);
			System.out.printf("파일 마지막 읽은 날짜  : %s \n", lastAccessTime);
 
			// 파일 마지막 수정일
			FileTime lastModifiedTime = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS);
			System.out.printf("파일 마지막 수정일      : %s \n", lastModifiedTime);
 
			// 디렉토리 인지
			boolean isDirectory = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
			System.out.printf("디렉토리 입니까?      %s \n", isDirectory);
 
			// 파일 인지
			boolean isRegularFile = (Boolean) Files.getAttribute(path, "basic:isRegularFile", NOFOLLOW_LINKS);
			System.out.printf("파일 입니까?         %s \n", isRegularFile);
 
			// 심볼릭 링크
			boolean isSymbolicLink = (Boolean) Files.getAttribute(path, "basic:isSymbolicLink", NOFOLLOW_LINKS);
			System.out.printf("심볼릭 링크 입니까?     %s \n", isSymbolicLink);
 
			// other
			boolean isOther = (Boolean) Files.getAttribute(path, "basic:isOther", NOFOLLOW_LINKS);
			System.out.printf("Other?             %s \n", isOther);
 
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```



