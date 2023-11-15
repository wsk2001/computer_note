# Linux 에서 Test Image 생성 및 마운트

참조: https://github.com/IvanPonzio/BigBrother-File-System-BBFS-



빈 file.img 파일을 만듭니다(FAT32의 경우 최소 파일 크기는 약 33MB입니다).

``` bash
$ dd if=/dev/zero of=file.img count=35 bs=1M
or
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



다음 명령은 file.img 이미지를 mnt 디렉터리에 마운트하고 모든 권한을 부여합니다.

``` bash
$ mount file.img mnt
```



이미지에 뭔가(파일)를 추가 하는 방법

``` bash
$ echo "Hello, this is a file" > mnt/test_file
```



마지막으로 이미지를 마운트 해제 하는 방법.

``` bash
$ sudo umount mnt/
```



이미지를 다시 마운트하면 test_file이 다시 표시됩니다.