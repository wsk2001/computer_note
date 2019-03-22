# Get Windows NTFS Block Size

출처: https://www.bytesizedalex.com/get-windows-ntfs-block-size/

Windows 시스템은 기본 블록에 데이터 블록을 기록합니다. 블록의 크기에는 다양한 용어가 사용됩니다 (예 :)

- Block size
- Allocation unit
- Cluster size

고려해야 할 중요한 점은이 할당 단위가 시스템 성능에 영향을 미칠 수 있다는 것입니다. 기본적으로 Windows는 표준 4KB 블록 크기의 디스크를 포맷합니다. 예를 들어 Microsoft Exchange 서버에서는 장치 크기를 64KB로 권장합니다. Microsoft는 SQL 서버 및 가상 시스템에도이 기능을 권장합니다. 기본 디스크에 값을 올바르게 설정하지 않으면 시스템 성능 문제가 발생할 수 있습니다. 기록되는 데이터 세트와 블록 크기를 맞추면 효율적인 IO 처리를 보장 할 수 있습니다. 64KB 범위 (8KB, 8KB 페이지로 구성된 기본 단위)를 사용하는 SQL 서버를 고려하면 64KB 블록 크기의 디스크를 가장 효과적으로 포맷 할 수 있습니다. 로컬로 연결된 디스크라고 가정하면 Windows가 4KB의 기본 크기 블록으로 분할하지 않고 하나의 연속 블록으로 익스텐트를 쓰게 할 수 있습니다. 64KB 범위를 차지하고 그 단일 IO를 16 개의 작은 IO 쓰기로 분할해야한다고 상상해보십시오 - 그리 효율적이지 않습니다.

우리가 SAN 스토리지에 글을 쓰고 있다면, 블럭 크기 (또는 벤더가 호출하는 곳)가 적절한 지 다시 한번 확인하고, IO 입출력 쓰기를 피하고 성능을 보장해야합니다. 논의 할 큰 주제이지만 여기서 끝내고 서버에서이 정보를 검색하는 방법에 대해 설명하겠습니다.



### PowerShell Commands

Microsoft는 Get-wmiObject cmdlet 대신 Get-cimInstance cmdlet을 사용하지 않습니다. 이전 시스템에서이 작업을 수행 할 경우 두 가지를 모두 참조로 제공했습니다. 값은 'BlockSize'로 설명됩니다.

```powershell
PS C:\> Get-WmiObject -Class Win32_Volume | Select-Object Label, BlockSize | Format-Table -AutoSize
```

```powershell
PS C:\> Get-CimInstance -ClassName Win32_Volume | Select-Object Label, BlockSize | Format-Table -AutoSize
```



Example output –

```powershell
PS C:\> Get-CimInstance -ClassName Win32_Volume | Select-Object Label, BlockSize | Format-Table -AutoSize

Label                  BlockSize

-----                  ---------

4096

bsa_sqlinfra01_db_vv        4096
bsa_sqlinfra01_logs_vv      4096
bsa_sqlsp_db_vv            65536
bsa_sqlsp_logs_vv          65536
Recovery                    4096
```



### DOS Commands

fsutil 및 DiskPart 명령을 사용할 수 있습니다. fsutil에서 값은 64KB 값을 가진 'Byte Per Cluster'이고 DiskPart에서는 'Allocation Unit Size'라고합니다.

```powershell
PS C:\> fsutil fsinfo ntfsinfo p:

NTFS Volume Serial Number :       0x5840d42e40d4151a
NTFS Version   :                  3.1
LFS Version    :                  2.0
Number Sectors :                  0x000000007ffbefff
Total Clusters :                  0x0000000000fff7df
Free Clusters  :                  0x0000000000459424
Total Reserved :                  0x0000000000000000
Bytes Per Sector  :               512
Bytes Per Physical Sector :       512
Bytes Per Cluster :               65536
Bytes Per FileRecord Segment    : 1024
Clusters Per FileRecord Segment : 0
Mft Valid Data Length :           0x000000000e840000
Mft Start Lcn  :                  0x000000000000c000
Mft2 Start Lcn :                  0x0000000000000001
Mft Zone Start :                  0x0000000000cf4180
Mft Zone End   :                  0x0000000000cf4c80
Resource Manager Identifier :     0C567257-18F9-11E5-80CE-00155DAEB03C
```

DiskPart 유틸리티를 사용하여이 서버에 연결된 모든 볼륨을 나열한 다음 볼륨을 선택하고 'filesystems'명령을 사용하여 필요한 정보를 표시합니다. 언급 한 바와 같이이 값은 '할당 단위 크기'로 설명됩니다.

```powershell
DISKPART> list vol

Volume ###  Ltr  Label        Fs     Type        Size     Status     Info

----------  ---  -----------  -----  ----------  -------  ---------  --------

Volume 0     C                NTFS   Partition     59 GB  Healthy    Boot
Volume 1         Recovery     NTFS   Partition    300 MB  Healthy    Hidden
Volume 2                      FAT32  Partition     99 MB  Healthy    System
Volume 3     P   bsa_sqlsp_d  NTFS   Partition   1023 GB  Healthy
Volume 4     Q   bsa_sqlsp_l  NTFS   Partition    199 GB  Healthy
Volume 5     F   bsa_sqlinfr  NTFS   Partition    499 GB  Healthy
Volume 6     G   bsa_sqlinfr  NTFS   Partition    199 GB  Healthy

DISKPART> select vol 3

Volume 3 is the selected volume.

DISKPART> filesystems

Current File System

Type                 : NTFS
Allocation Unit Size : 64K
Flags : 00000000

File Systems Supported for Formatting

Type                 : NTFS (Default)
Allocation Unit Sizes: 512, 1024, 2048, 4096, 8192, 16K, 32K, 64K (Default)

Type                 : REFS
Allocation Unit Sizes: 64K (Default)

DISKPART>
```

보시다시피 Microsoft는이 속성을 설명하는 일관성이 부족합니다. 이는 다소 실망 스럽지만 IT 세상의 방식입니다. IO 및 분할 정렬 문제를 피하기 위해 올바른 블록 크기로 저장 장치를 포맷하는지 확인하는 것이 중요합니다. 일부 시스템 공급 업체 (특히 모든 플래시 어레이 판매자)는 관리자가 시스템이 '너무 빨라서'오버 헤드 나 비 효율성이 시스템 속도로 인해 보상 될 수 있으므로 걱정하지 말라고 말했습니다. 개인적으로 나는이 개념이 싫고 모든 것이 최선의 방법에 따라 최적화되고 배치되어 시스템을 최대한 활용해야한다고 완전히 믿습니다.