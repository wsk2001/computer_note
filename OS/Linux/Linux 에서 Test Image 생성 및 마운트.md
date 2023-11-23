# Linux 에서 Test Image 생성 및 마운트

참조: https://github.com/IvanPonzio/BigBrother-File-System-BBFS-



빈 file.img 파일을 만듭니다(FAT32의 경우 최소 파일 크기는 약 33MB입니다).

``` bash
$ fallocate -l 35M file.img
```



Image 파일 format.

``` bash

$ mkfs.ext4 -F ./file.img

```



다음으로, 이미지를 마운트하고 item(file)을 추가하려면 시스템의 마운트 기능을 사용하고 마운트 지점의 모든 사용자에게 쓰기 권한을 부여해야 합니다(Sudo 권한 필요). 먼저 마운트된 파일을 볼 디렉터리를 만듭니다. 그런 다음 이미지를 마운트합니다.

``` bash
$ mkdir mnt
```



다음 명령은 file.img 이미지를 mnt 디렉터리에 마운트하고 모든 권한을 부여합니다. (root user 가 실행 해야함)

``` bash
$ mount file.img mnt

# 아래 처럼 가능
$ sudo mount file.img mnt
$ sudo chmod o+w mnt
```



이미지에 파일을 추가 하는 방법

``` bash
$ echo "Hello, this is a file" > mnt/test_file
```



파일 내용 확인 하는 방법

``` bash
$ cat mnt/test_file
```



마지막으로 이미지를 마운트 해제 하는 방법.  (root user 가 실행 해야함)

``` bash
$ umount mnt/

# 아래 처럼 가능
$ sudo umount mnt/
```



이미지를 다시 마운트하면 test_file이 다시 표시됩니다.

``` bash
$ sudo mount file.img mnt
$ cat mnt/test_file
```



이미지 파일 내용 확인 하는 방법

```bash
$ strings file.img
```

