# Linux 캐시 메모리 비우기



안녕하세요. 오늘은 Linux 메모리를 지우는 법에 대하여 글을 작성해봅니다.
Linux는 /Proc 를 사용하여 캐시(pagecache, dentries, inode)를 직접 지울 수 있습니다 .

지워야 하는이유는 캐시는 느린 장치의 데이터를 빠른 장치에 임시로 보관하여
성능을 높이는데 활용됩니다.

단, 메모리와 같은 빠른 장치는 공간이 한정되어 있어 모든 정보를 캐시할 수 없기 때문에
캐시를 제거하여 캐시 관리가 필요합니다.

삭제하기전 메모리 확인이 필요한데,
free -m 명령어를 통하여 메모리를 확인 후 캐시 삭제를 시작합니다.

**리눅스에서 시스템 블록에 있는 캐시 삭제 (버퍼 캐시 삭제)**
sudo sysctl -w vm.drop_caches=2
sudo echo 2 > sudo /proc/sys/vm/drop_caches

drop_chches란?
커널이 깨끗한 캐시, 덴트리 및 메모리에서 inode를 제거하여 해당 메모리를 해제합니다.

아래는 각 번호에 대한 뜻입니다. 

pagecache 해제
echo 1 > proc/sys/vm/drop_caches

dentries , innode 해제
echo 2 > proc/sys/vm/drop_caches

pagecache,dentries,inodes 모두 해제 
echo 3 > proc/sys/vm/drop_caches

해당 부분을 실습해보면 아래와 같습니다.

실행예시 

cat /proc/sys/vm/drop_caches 
![img](http://idchowto.com/wp-content/uploads/2022/11/1-300x17.png)
-> drop_caches의 기본값은 0이며, 캐시메모리를 drop하지 않은 상태입니다.

![img](http://idchowto.com/wp-content/uploads/2022/11/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7-2022-11-10-16-51-06-300x33.png)
-> 버퍼,캐시 합쳐서 2758 사용 확인됩니다.

echo 3으로 설정후 버퍼 및 캐시를 확인해보면 줄어든 모습을 확인할 수 있습니다.
![img](http://idchowto.com/wp-content/uploads/2022/11/3-300x51.png)
 
Linux는 공간을 캐시 영역으로 잡아두는 습성이 있기 때문에
실 메모리 사용률 계산입니다. 

명목메모리 사용률 = used / total =
( total – free ) / total

실질메모리 사용률 = used2 / total =
( total – free2 ) / total = ( total – free – buffers – cached) / total

감사합니다. 