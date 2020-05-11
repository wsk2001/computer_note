# FUSE를 사용하여 구현 된 파일 시스템 목록

출처: https://github.com/libfuse/libfuse/wiki/Filesystems

출처 에서 일부 관심있는 목록만 발췌함.



### **limit-fs**

Homepage: https://github.com/piuma/limit-fs

```
설명 limit-fs는 사용 된 공간이 설정된 제한 백분율에 도달 할 때마다 가장 오래된 파일을 제거하는 파일 시스템입니다. 빈 디렉토리가 아닌 곳에서 사용할 수 있습니다. 작성한 파일은 기본 파일 시스템에 기록됩니다. 마운트 해제 한 후에는 모든 파일이 마운트 해제 된 디렉토리에 남아 있습니다.
```



### **mcachefs**

Homepage: http://lists.samba.org/archive/linux/2004-March/010211.html

```
설명 mcachefs는 FUSE를 사용하는 Linux 용 간단한 캐싱 파일 시스템입니다. 파일을 열 때 요청한 파일을 복사 한 다음 파일의 모든 후속 요청에 해당 사본을 사용하여 작동합니다. 이는 캐싱에 대한 매우 순진한 접근 방식이며 향후 개선 될 것입니다.
```



### **afuse**

Homepage: http://afuse.sourceforge.net/  : https://github.com/pcarrier/afuse

```
설명 FUSE 기반 자동 마운터.
```

뒤쪽 주소로 바뀜.



### **fuse_adfs**

Homepage: http://www.boddie.org.uk/david/Projects/Python/FUSE/

```
설명 fuse_adfs 모듈은 FUSE를 사용하여 ADFS 읽기 기능의 내용을 나타냅니다. 파일 시스템은 ADFS 스타일 파일 이름을 Unix 친화적 인 파일 이름으로 변환하고 파일 이름에 접미사를 추가하여 응용 프로그램이 특정 유형의 파일을 인식하도록합니다.
```



### **BindFS**

Homepage: https://bindfs.org/

```
설명 디렉토리를 다른 디렉토리로 미러링합니다 (--bind 마운트와 유사). 상당히 유연한 방식으로 미러 된 디렉토리 내부의 권한을 변경할 수 있습니다.
```



### **ClamFS**

Homepage: http://clamfs.sourceforge.net/

```
설명 ClamFS는 clamd 데몬을 통한 온 액세스 안티 바이러스 파일 검색 기능을 갖춘 Linux 용 FUSE 기반 사용자 공간 파일 시스템입니다.
```



### **Ramfuse**

Homepage: http://notabilis.org/code/?ramfuse

```
설명 Ramfuse는 펄로 작성된 동적으로 성장하는 RAM 파일 시스템입니다. Perl의 메모리 처리로 인해 동적으로 축소되지는 않습니다. :-)
```



### **rotatefs**

Homepage: https://github.com/frt/rotatefs

```
설명 : 일부 작업을 수행 할 공간이 없을 때마다 가장 오래된 파일을 제거하는 FUSE 파일 시스템.
```



### **1fichierfs**

Homepage: https://gitlab.com/BylonAkila/astreamfs

```
이는 1fichier에서 제공하는 API를 사용하여 1fichier 클라우드 스토리지에 astreamfs를 전문화 한 것입니다. 따라서 더 이상 스크립트가 필요하지 않으며 API 키와 탑재 지점 만 제공하면됩니다 (다른 옵션은 편리함).
```

