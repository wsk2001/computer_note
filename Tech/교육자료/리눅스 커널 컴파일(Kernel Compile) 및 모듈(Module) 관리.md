# 리눅스 커널 컴파일(Kernel Compile) 및 모듈(Module) 관리

출처: https://meongj-devlog.tistory.com/80

### [**커널(Kernel)**](https://meongj-devlog.tistory.com/80#%EC%BB%A-%EB%--%---Kernel-)

컴퓨터 자원을 관리하는 운영체제의 핵심적인 프로그램이다.

### [ ](https://meongj-devlog.tistory.com/80#%C-%A-)

### [**모듈(Module)**](https://meongj-devlog.tistory.com/80#%EB%AA%A-%EB%--%---Module-)

커널에서 사용되는 네트워크 드라이브를 모두 가지고 있으면 발생하는 비효율적 문제를 해결하기 위해 만들어짐

모듈이라는 파일로 커널 외부에 별로로 가지고 있음으로써, 새로운 장치 추가시 커널 바꿀 필요없이 해당 모듈만 적재 후 제거 한 후 사용한다. 

  

**리눅스 커널 모듈**

C컴파일러로 만들어진 오브젝트 파일로서 .*ko 형태로 생성됨

/lib/modules/커널버전/kernel 디렉터리에 위치

  

> **모듈(module) 관련 명령어**

 

**lsmod**

리눅스 커널에 적재된 모듈 정보 출력



![img](https://blog.kakaocdn.net/dn/blt0E1/btrhAhkdwPt/jew01I5bnpbp3D4UWYeOI1/img.png)



 **modinfo [모듈명]**

특정 모듈에 대한 라이센스, 버전 등의 관련 정보를 출력 (module info)



![img](https://blog.kakaocdn.net/dn/baUsL0/btrhzrHmXrI/ktkgEPGsHk44COAE4qZ8Bk/img.png)



**insmod [모듈명]**

모듈명에 해당하는 모듈을 커널에 적재 (insert module)

 

**rmmod [모듈명]**

모듈명에 해당하는 모듈을 커널에서 제거 (remove module)

  

**modprobe [옵션] [모듈명]**

- 의존성있는 모듈 적재 및 제거
- 사용 가능한 모듈 출력 (modinfo와 동일한 기능)

모듈의 최상위 디렉터리에 있는 modules.dep 파일 참고하여 의존성있는 모듈 모두 커널에서 적재, 삭제 할 수 있음

 

| option      | 설명                                           |
| ----------- | ---------------------------------------------- |
| -l (list)   | 사용 가능한 모듈 정보 출력                     |
| -a (all)    | 모듈과 의존성으로 관련된 다른 모듈도 같이 적재 |
| -r (remove) | 모듈을 제거 혹은 atuclean 모듈 제거            |

 

> 사용 가능한 모듈 목록 정보 출력
> \# modprobe -l
>
> p6table_filter 모듈을 제거하면서 관련 모듈도 같이 제거한다
> \# modprobe -r
>
> 3c59x.ko 모듈 관련 정보를 출력한다.
> \# modinfo 3c59x.ko

#### [ ](https://meongj-devlog.tistory.com/80#%C-%A-)

#### [**■ 컴파일 관련 명령어**](https://meongj-devlog.tistory.com/80#%E-%--%A-%--%EC%BB%B-%ED%-C%-C%EC%-D%BC%--%EA%B-%--%EB%A-%A-%--%EB%AA%--%EB%A-%B-%EC%--%B-)

 

**depmod**

- 커널 모듈 간의 의존성을 관리하는 명령
- modules.dep 파일을 생성하고 갱신 및 관리
- 맵 파일 생성

 

**make modules**

커널 컴파일 과정에서 선택한 모듈을 생성하는 명령

 

 

**make modules_install**

커널 컴파일 과정에서 생성한 모듈을 설치하는 명령

 

**커널 컴파일 파일 제거**

| 명령어         | 설명                                                         |
| -------------- | ------------------------------------------------------------ |
| make mrproper  | 이전 컴파일 시 설정되었던 설정 정보 제거 및 초기화 새로운 커널 소스 컴파일 할때 사용 |
| make distclean | mrproper 명령 수행하고, 추가로 커널 컴파일 후 생성된 모든 오브젝트 파일, 백업 파일 등 삭제 제거 명령어 중 가장 강력한 명령어 |

  

**커널 컴파일 관련 옵션 설정**

| 명령어          | 설명                                                         |
| --------------- | ------------------------------------------------------------ |
| make menuconfig | 텍스트 메뉴 기반에서 커서를 이용하여 커널 컴파일 관련 옵션 설정 작업함 |

  

#### [**< 모듈 관련 작업 및 커널 컴파일 진행 과정 >**](https://meongj-devlog.tistory.com/80#%-C%--%EB%AA%A-%EB%--%--%--%EA%B-%--%EB%A-%A-%--%EC%-E%--%EC%--%--%--%EB%B-%-F%--%EC%BB%A-%EB%--%--%--%EC%BB%B-%ED%-C%-C%EC%-D%BC%--%EC%A-%--%ED%--%--%--%EA%B-%BC%EC%A-%--%--%-E)

> \1. 커널 정보 확인
>
>  \# uname -a
>
> ![img](https://blog.kakaocdn.net/dn/bzh6fu/btrhykvh4BR/AsIEwwkh6TOqPCU2NFldJK/img.png)커널버전, 빌드날짜, 플랫폼 정보 출력
>
> \2. 커널 컴파일 패키지 설치 및 압축해제
>
> \3. 커널 소스 설정값 초기화
>
>  \# make mrproper
>
> \4. 커널 컴파일 옵션 설정 작업
>
>  \# make menuconfig
>
> \5. 커널 이미지 생성 작업
>
>  \# make bzlmage
>
> \6. 모듈 간의 의존성이 기록된 파일 확인한다
>
>  \# cat /lib/modules/[커널버전]/modules.dep
>
> \7. 커널 모듈 간의 의존성을 관리하기 위해 modules.dep 파일과 맵 파일을 생성한다
>
>  \# depmod
>
> \8. 커널 모듈 생성을 위한 컴파일 작업
>
>  \# make modules
>
> \9. 커널 모듈 설치 작업
>
>  \# make modules_install
>
> \10. 커널 모듈 파일 복사 및 grub.conf 파일 수정
>
>  \# make install
>
> \11. 시스템 재부팅
>
>  \# reboot

 

 

 

 

> **모듈(module) 관련 파일**

 

#### **/lib/modules/[커널 버전\]/modules.dep**

- 모듈 간의 의존성이 기록된 파일명
- modprobe 명령 실행시 모듈의 최상위 경로에 있는 modules.dep 파일 참조하여 모듈의 의존성 확인

 



![img](https://blog.kakaocdn.net/dn/S9yEL/btrhB2mybEW/xXmbMIqYoi0EDlr0EJ3WO1/img.png)



 

#### [**/etc/modprobe.d**](https://meongj-devlog.tistory.com/80#%-Fetc%-Fmodprobe-d)

리눅스의 모듈에 관련한 설정 파일 있는 경로



![img](https://blog.kakaocdn.net/dn/pSmgK/btrhykovzgF/hkrXFhJ2YdMC3IweDKf611/img.png)



 

 