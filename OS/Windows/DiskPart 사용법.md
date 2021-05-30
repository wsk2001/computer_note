# DiskPart 사용법

출처: https://ss64.com/nt/diskpart.html



# DiskPart

디스크 관리, 디스크 파티션. 이 페이지는 Windows 8/10 + Server 2008-2016 버전의 Diskpart를 문서화합니다.

`Syntax`

​	DISKPART



### DISKPART 프롬프트에서 실행할 수있는 명령

* **ACTIVE**  

  기본 디스크에서 포커스가있는 파티션을 활성으로 표시합니다.

  DiskPart는 파티션의 내용을 확인하지 않습니다. 실수로 파티션을 활성으로 표시 한 경우
  운영 체제 시작 파일이 포함되어 있지 않으면 컴퓨터가 시작되지 않을 수 있습니다.
  이는 BIOS (Basic Input / Output System) 또는 EFI (Extensible Firmware Interface)에 다음을 알립니다.
  파티션 또는 볼륨이 유효한 시스템 파티션 또는 시스템 볼륨입니다.
  파티션 만 활성으로 표시 할 수 있습니다. 이 작업이 성공하려면 파티션을 선택해야합니다.
  select partition 명령을 사용하여 파티션을 선택하고 포커스를 이동합니다.

* **ADD disk=n [align=n] [wait] [noerr]**

  포커스가있는 단순 볼륨을 지정된 디스크에 미러링합니다.

* **ASSIGN  [{LETTER=D  | mount=path}] [noerr]**

  포커스가있는 볼륨에 드라이브 문자 또는 탑재 지점을 할당합니다. <span style="color:red">(서버 2012-2016 만 해당)</span>

  드라이브 문자 또는 탑재 지점을 지정하지 않으면 사용 가능한 다음 드라이브 문자가 할당됩니다.
  Assign 명령을 사용하여 이동식 드라이브와 관련된 드라이브 문자를 변경할 수 있습니다.
  <span style="color:blue">시스템 볼륨, 부팅 볼륨 또는 페이징 파일이 포함 된 볼륨에는 드라이브 문자를 할당 할 수 없습니다.</span>
  또한 기본 데이터 파티션이 아닌 OEM (Original Equipment Manufacturer) 파티션이나 gpt (GUID 파티션 테이블) 파티션에 드라이브 문자를 할당 할 수 없습니다.
  이 작업이 성공하려면 볼륨을 선택해야합니다.

  \## 예제 포커스가있는 볼륨에 문자 E를 할당하려면 다음을 입력합니다. assign letter = e

* **ATTRIBUTES DISK [{set | clear}] [readonly] [noerr]**

* **ATTRIBUTES VOLUME [{set | clear}] [{hidden | readonly | nodefaultdriveletter | shadowcopy}] [noerr]**

  디스크 또는 볼륨의 속성을 표시, 설정 또는 지웁니다.

* **AUTOMOUNT [enable] [disable] [scrub] [noerr]**

  자동 마운트 기능을 활성화 또는 비활성화합니다.

* **BREAK disk=n [nokeep] [noerr]**

  포커스가있는 미러링 된 볼륨을 두 개의 간단한 볼륨으로 나눕니다.

* **CLEAN [all]       (Only Server 2012-2016)**

  포커스가있는 디스크에서 모든 파티션 또는 볼륨 포맷을 제거하면 'all'이 디스크를 0으로 만듭니다.

* **COMPACT vdisk       (Only Windows 7 + Windows Server 2008R2-2016)**

  동적 확장 가상 하드 디스크 (VHD) 파일의 물리적 크기를 줄입니다.

  이 매개 변수는 파일을 추가 할 때 동적으로 확장되는 VHD의 크기가 증가하지만 파일을 삭제할 때 크기가 자동으로 줄어들지 않기 때문에 유용합니다.

* **CONVERT**

  FAT (파일 할당 테이블) 및 FAT32 볼륨을 NTFS 파일 시스템으로 변환합니다.

- **CREATE partition efi [size=n] [offset=n] [noerr]**
  **CREATE partition extended [size=n] [offset=n] [align=n] [noerr]**
  **CREATE partition logical [size=n] [offset=n] [align=n] [noerr]**
  **CREATE partition msr [size=n] [offset=n] [noerr]**
  **CREATE partition primary [size=n] [offset=n] [id={ byte | guid }] [align=n] [noerr]**

  파티션을 만듭니다.

- **CREATE volume raid [size=n] disk=n,n,n[,n,...] [align=n] [noerr]**
  **CREATE volume simple [size=n] [disk=n] [align=n] [noerr]**
  **CREATE volume stripe [size=n] disk=n,n[,n,...] [align=n] [noerr]**
  **CREATE volume mirror [size=n] disk=n,n[,n,...] [align=n] [noerr]**

  볼륨을 만듭니다.

- **CREATE vdisk file=filepath {[type={fixed|expandable}] | [parent=filepath] | [source=filepath]}  [maximum=n] [sd=SDDL] [noerr]**

  가상 디스크 (VHD)를 만듭니다.

- **DELETE disk [noerr] [override]**
  **DELETE partition [noerr] [override]**
  **DELETE volume [noerr]**

  디스크 목록에서 파티션, 볼륨 또는 동적 디스크를 삭제합니다.

- **DETAIL Disk**
  **DETAIL Partition**
  **DETAIL volume**

  선택한 디스크 / 볼륨 / 파티션의 속성을 표시하고 현재 볼륨이있는 디스크를 표시합니다.

- EXIT

  Diskpart 종료

- **EXPAND vdisk maximum=n**

  가상 디스크 (Win7 + Windows Server 2008R2-2016)에서 사용할 수있는 최대 크기 (MB)를 확장합니다.

- **EXTEND [size=n] [disk=n] [noerr]**
  **EXTEND filesystem [noerr]**

  포커스가있는 볼륨 또는 파티션과 해당 파일 시스템을 디스크의 여유 공간으로 확장합니다. (서버 2012-2016)

  ```
  size= MB 단위의 공간
  disk= 볼륨 또는 파티션이 확장 된 디스크입니다. 기본값은 현재 디스크입니다.
  ```

- **FILESYSTEMS**

  볼륨에서 현재 및 지원되는 파일 시스템 표시 (Use 'Select Volume' first)

-  **FORMAT [[FS=FS] [REVISION=X.XX] | RECOMMENDED] [LABEL="label"] [UNIT=N] [QUICK] [COMPRESS] [OVERRIDE] [DUPLICATE] [NOWAIT] [noerr]**

  Windows에서 사용할 디스크를 포맷합니다.

- **GPT attributes=n     (Only Server 2012-2016)  OEM use only.**

  선택한 GPT (GUID 파티션 테이블) 파티션에 특성을 할당합니다.

- **HELP [command]**

- **IMPORT**

  로드 된 메타 데이터 파일에서 시스템으로 전송 가능한 섀도 복사본을 가져옵니다.
  가져 오기를 사용하려면 먼저 load metadata 명령을 사용하여 DiskShadow 메타 데이터 파일을로드해야합니다.

- **INACTIVE**

  기본 마스터 부트 레코드 (MBR) 디스크에서 포커스가있는 시스템 파티션 또는 부팅 파티션을 비활성 [부팅 안 함]으로 표시합니다.
  먼저 '파티션 선택'을 사용하십시오.
  활성 파티션 없이는 컴퓨터가 시작되지 않을 수 있습니다.

- **LIST Disk**
  **LIST Partition**
  **LIST Volume**
  **LIST vdisk   (Windows 7/10 / Server 2008 R2)**
  **LIST writers [metadata | detailed | status]**
  **LIST shadows {all | set SetID | id ShadowID}**
  **LIST providers**

  개체 목록 표시
  SetID 지정된 섀도 복사본 세트 ID에 속하는 섀도 복사본을 나열합니다.
  ShadowID 지정된 섀도 복사본 ID를 가진 모든 섀도 복사본을 나열합니다.

- **MERGE vdisk depth=n (Windows 7/Server 2008R2-2016)**

  차이점 보관 용 가상 하드 디스크 (VHD)를 해당하는 상위 VHD와 병합합니다.
  depth = 1은 부모와 병합됩니다.
  부모 VHD는 차이점 보관 용 VHD의 수정 사항을 포함하도록 수정됩니다.

- **OFFLINE disk [noerr]**
  **OFFLINE volume [noerr]**

  온라인 디스크 또는 볼륨을 오프라인 상태로 전환합니다.  (먼저 'Select Disk'을 사용하십시오.)

- **ONLINE disk [noerr]**
  **ONLINE volume [noerr]**

  오프라인 디스크 또는 볼륨을 온라인 상태로 만듭니다.

- **RECOVER [noerr]**

  선택한 팩의 모든 디스크 상태를 새로 고치고 유효하지 않은 팩의 디스크에서 복구를 시도하고 오래된 플렉스 또는 패리티 데이터가있는 미러링 된 볼륨과 RAID-5 볼륨을 다시 동기화합니다.
  동적 디스크에만 적용됩니다.

- **REM  (remark/comment)**

  

- **REMOVE letter=E [dismount] [noerr]**    포커스가있는 파티션에서 드라이브 문자 E를 제거합니다.

- **REMOVE mount=path [dismount] [noerr]**  focus가 있는 파티션에서 마운트 지점을 제거합니다.

- **REMOVE all [dismount] [noerr]**         모든 현재 드라이브 문자와 마운트 지점을 제거합니다.

  볼륨에서 드라이브 문자 또는 탑재 지점을 제거합니다.

- **REPAIR disk=n [align=N] [noerr]**

  지정된 동적 디스크로 교체하여 실패한 구성원이있는 RAID-5 볼륨을 복구합니다.
  align = N 모든 볼륨 범위를 가장 가까운 정렬 경계에 정렬합니다.
  범위 오프셋은 N의 배수가됩니다.

- **RESCAN**  컴퓨터에 추가 된 새 디스크와 볼륨을 찾습니다.

- **RETAIN**  부팅 또는 시스템 볼륨으로 사용할 기존 동적 단순 볼륨을 준비합니다.

- **SAN [policy={OnlineAll | OfflineAll | OfflineShared}] [noerr]**

  현재 부팅 된 OS에 대한 SAN 정책을 표시하거나 설정합니다.
  디스크를 공유하는 서버에서 onlineAll을 지정하면 데이터가 손상 될 수 있습니다.
  서버가 클러스터의 일부가 아닌 경우 디스크가 서버간에 공유되는 경우이 정책을 설정하지 마십시오.

- **SELECT Disk={ n | diskpath | system | next }**
  **SELECT Partition=n   (Volume number)**
  **SELECT Volume={ n | d } [noerr] (Volume number or Drive Letter)**
  **SELECT vdisk file=fullpath [noerr]**

  focus 를 개체로 이동합니다.

- **SET ID={ byte | GUID } [override] [noerr]**

  포커스가있는 파티션의 파티션 유형 변경 (OEM 용) :

- **SHRINK [desired=n] [minimum=n] [nowait] [noerr]**  (focus 가 있는 볼륨의 크기 줄이기)

- **SHRINK querymax [noerr]**

  선택한 볼륨의 크기를 줄이십시오.
  Desired = 원하는 공간 (MB).
  최소값 = 볼륨을 줄일 공간 (MB).
  QueryMax = 볼륨을 줄일 수있는 최대 바이트 수를 반환합니다.

- **UNIQUEID disk [id={dword | GUID}] [noerr]**

  포커스가있는 (기본 또는 동적) 디스크에 대한 GUID 파티션 테이블 식별자 또는 MBR 서명을 표시하거나 설정합니다.
  Id = MBR 디스크의 경우 서명에 대해 16 진수 형식의 4 바이트 DWORD를 지정합니다.



### 기본 디스크 관리 명령

- **ASSIGN MOUNT=path**  (볼륨의 마운트 지점 경로를 선택하십시오.)

  CREATE PARTITION Primary Size=50000  (50 GB)
  CREATE PARTITION Extended Size=25000
  CREATE PARTITION logical Size=25000
  DELETE Partition
  EXTEND Size=10000
  GPT attributes=n   (assign GUID Partition Table attributes)
  SET id=byte|GUID [override] [noerr]   (Change the partition type)



### 동적 디스크를 관리하는 명령

- ADD disk=n    (지정된 디스크의 포커스가있는 SIMPLE 볼륨에 미러를 추가합니다. 자세한 내용은 'Diskpart 도움말'을 참조하십시오.)
- BREAK disk=n  (Break the current in-focus mirror)
- CREATE VOLUME Simple Size=n Disk=n
- CREATE VOLUME Stripe Size=n Disk=n,n,...
- CREATE VOLUME Raid Size=n Disk=n,n,...
- DELETE DISK
- DELETE PARTITION
- DELETE VOLUME
- EXTEND Disk=n [Size=n]
- EXTEND Filesystem [noerr]
- IMPORT [noerr]   (외부 디스크 그룹 가져 오기, 먼저 '디스크 선택'사용)
- RECOVER [noerr]  (디스크 팩 상태를 새로 고치고 유효하지 않은 팩에서 복구를 시도하고 오래된 플렉스 / 패리티 데이터를 다시 동기화합니다.)
- REPAIR disk=n [align=n] [noerr]  (포커스가있는 RAID-5 볼륨을 복구하고 지정된 동적 디스크로 교체합니다.)
- RETAIN   (부팅 또는 시스템 볼륨으로 사용할 기존 동적 단순 볼륨을 준비합니다.)

새 드라이브를 설정할 때 파티션 생성, 드라이브 포맷, 드라이브 문자 할당 순서로 생성하십시오.

diskpart 명령은 텍스트 파일 (한 줄에 하나의 명령)에 배치하고 diskpart.exe에 대한 입력 파일로 사용할 수 있습니다.

``` cmd
DiskPart.exe < myscript.txt
```

볼륨이나 파티션을 선택할 때 번호 나 드라이브 문자 또는 마운트 지점 경로를 사용할 수 있습니다.

Windows GUI 인터페이스를 사용하여 드라이브에 탑재 지점 폴더 경로를 할당 할 수도 있습니다. 디스크 관리자에서 파티션이나 볼륨을 마우스 오른쪽 단추로 클릭하고 드라이브 문자 및 경로 변경을 클릭 한 다음 추가를 클릭하고 NTFS 볼륨의 빈 폴더 경로를 입력합니다.

Windows 복구 콘솔에는 단순화 된 DISKPART 명령이 포함되어 있습니다. 파티션 추가 및 삭제 기능 만 제공하고 활성 파티션 설정 기능은 제공하지 않습니다.

diskpart를 실행하기 전에 항상 하드 디스크를 백업하십시오.

예 :

``` 
SELECT DISK=0
CREATE PARTITION PRIMARY
SELECT PARTITION=1
FORMAT FS=NTFS LABEL="New Volume" QUICK
ASSIGN LETTER=E
EXIT 
```

`주의 잘못 하면 OS 가 있는 disk 가 format 됨`

Windows Server 2008 / R2의 기본 SAN 정책은 이제 모든 비 부팅 SAN 디스크에 대해 VDS_SP_OFFLINE_SHARED입니다.
즉, 드라이브에 페이징 파일이 포함 된 경우에도 서버 시작시 디스크가 오프라인 상태가됩니다.

이 디스크 관리 오류 메시지는 드라이브가 오프라인 상태임을 나타냅니다.

```
"the disk is offline because of policy set by an administrator".
```

현재 SAN 정책을 쿼리하여 오프라인 공유 여부 확인

``` cmd
DISKPART.EXE
DISKPART> san
SAN Policy : Offline Shared
```

수동으로 디스크를 온라인 상태로 만들려면 :

```
컴퓨터 관리 -> 스토리지 -> 디스크 관리 에서 디스크를 마우스 오른쪽 버튼으로 클릭하고 온라인을 선택합니다.
```

이것이 클러스터의 일부가 아닌 경우 대안은 SAN 정책을 변경하는 것보다 오프라인 디스크를 선택하고 읽기 전용 플래그를 지우고 온라인 상태로 만드는 것입니다.

``` cmd
DISKPART> san policy=OnlineAll 
DiskPart successfully changed the SAN policy for the current operating system. 
 
DISKPART> LIST DISK 
Disk ### Status        Size    Free    Dyn Gpt
-------- ------------- ------- ------- --- ---
Disk 0   Online         80 GB     0 B
* Disk 1 Offline        20 GB  1024 KB 
 
DISKPART> select disk 1 
Disk 1 is now the selected disk. 
 
DISKPART> ATTRIBUTES DISK CLEAR READONLY
DISKPART> ONLINE DISK
DiskPart successfully onlined the selected disk.
```



### 끝



