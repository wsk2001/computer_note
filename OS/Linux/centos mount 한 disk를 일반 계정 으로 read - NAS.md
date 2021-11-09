# centos mount 한 disk를 일반 계정 으로 read

출처: https://www.2cpu.co.kr/bbs/board.php?bo_table=QnA&wr_id=541968



**Q)** plex(192.168.0.27) 에서 synology nas(192.168.0.20) 에서 nfs 파일 공유를 mount 하려 하였습니다. 

처음에는 smb 를 연결하려고했는데..  2시가 이상 시도하다가 포기하고... nfs 공유하고 연결하려니까 root 로 연결되는거 보이더라구요....

그래서 계속 시도하는데... 질문 사항 이 생겼습니다. 

 **sudo mount -o user 192.168.0.20:/volume1/video /mnt/nas/media** 

위 명령어로 mount 했는데 root  또는 sudo 를 사용해야만 /mnt/nas/media  접근이 가능합니다. 

plex 계정으로는 ls /mnt/nas/media 하면 

**ls: cannot open directory /mnt/nas/media: 허가 거부** 

라고 나옵니다. ㅠㅠ 

제가 준 옵션이 틀린건가요?  혹시 참고할만한 문서가 있을까요? 



**A)** Disk mount는 시스템 관리자 권한의 명령입니다.
일반사용자들이 disk를 맘대로 붙일 수 있으면 문제가 다양하게 생기거든요.



**Q)** plex 계정으로 해당 disk 를 read 하고 싶은데.. 어떻게 해야할까요? ㅠㅠ

**A)** 권한주는게 있어요. Mount를 할 때.

**A)** 그건
    nfs 서버쪽 설정을 봐야됩니다.
    클라이언트보다는...

**A)** 저도 nas에 물린 리눅스에서 nfs로 마운트해서 사용중입니다만..
    nas종류는 틀리긴하지만 그래도 아마
   그 설정은 nas쪽에 찾아보시면 권한 설정하는 부분이 있을겁니다.
   anonymous그룹이 접근가능하도록 해주세요.

**A)** nfs 방식 포기하고 다시 삼바 연결 시도로 해결했어요~

​    smbclient -L 192.168.0.20

​    sudo mount -t cifs -o user=****,pass=*** //192.168.0.20/video /mnt/nas/media

​    삼바서버 마운트 하니까 모든 계정이 접근 가능하게 나타나네여

​    답변해주신 분들 감사합니다 ^^

