# Docker Toolbox 에서 dir 공유 하는 방법

출처 : https://bryan7.tistory.com/797

C:\Users\wonsool\shared

```
docker run -it --name test1 -v /c/Users/{자신의 Windows 계정 폴더}/{공유할 디렉터리명}:{Docker Image에서 공유폴더와 Mount할 폴더 경로} {Docker Image 이름}
```

예)

``` bash
docker run --name ubuntu -v /c/Users/wonsool/shared:/var/host-dir -dit ubuntu:20.04

docker exec -it ubuntu /bin/bash
```



자세한 내용은 출처 참조

