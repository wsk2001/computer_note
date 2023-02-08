# CentOS-8 Device Driver 설치 방법



CentOS-8 Device Driver module 설치 방법 입니다.
설치하는 모듈은 hello.ko 라고 가정 하고 설명 드립니다.

1. root user 로 login 후 hello.ko 파일이 있는 위치로 이동 합니다.
2. mkdir -p /usr/lib/modules/$(uname -r)/kernel/drivers/hello
3. cp hello.ko /usr/lib/modules/$(uname -r)/kernel/drivers/hello/
4. modprobe -i hello
5. echo "hello" > /etc/modules-load.d/hello.conf
6. reboot
7. lsmod | grep hello



### - 끝 -

