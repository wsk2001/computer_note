# 실시간 암호화 (On-the-fly encryption)

실시간 암호화 및 투명한 암호화라고도하는 OTFE (On-The-Fly Encryption)는 일부[ 디스크 암호화 소프트웨어](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/Disk_encryption_software.html)에서 사용되는 방법입니다. 'On-the-Fly'는 데이터가로드되거나 저장 될 때 데이터가 자동으로 암호화 또는 해독된다는 사실을 나타냅니다. [1] 저장 매체가 휴대 가능하거나 도난 당할 수 있으므로 저장 매체의 데이터를 항상 암호화해야 할 때 자주 사용됩니다. [2]

직접 암호화 (on-the-fly) 암호화를 사용하면 키가 제공된 직후 파일에 액세스 할 수 있으며 일반적으로 전체 볼륨은 물리적 드라이브처럼 마운트되어 암호화되지 않은 파일만큼 파일에 액세스 할 수 있습니다. 올바른 암호 / 키 파일이나 올바른 암호 키를 사용하지 않고는 암호화 된 볼륨에 저장된 데이터를 읽을 수 없습니다 (암호 해독). 볼륨 내의 전체 파일 시스템이 암호화됩니다 (파일 이름, 폴더 이름, 파일 내용 및 기타 메타 데이터 포함). [5]

최종 사용자에게 투명성을주기 위해 보통 암호화는 암호화 프로세스를 가능하게하기 위해 장치 드라이버를 사용해야합니다. 일반적으로 이러한 드라이버를 설치하려면 관리자 액세스 권한이 필요하지만 일반적으로 이러한 권한이없는 일반 사용자는 암호화 된 볼륨을 사용할 수 있습니다. [6]

일반적으로 데이터가 쓰기시에 투명하게 암호화되고 읽기시 암호 해독되는 모든 방법은 즉석 암호화라고 할 수 있습니다. [7]

## See also

- [TrueCrypt](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/TrueCrypt.html)
- [VeraCrypt](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/VeraCrypt.html)
- [FreeOTFE](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/FreeOTFE.html)
- [Disk encryption](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/Disk_encryption.html)
- [Disk encryption software](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/Disk_encryption_software.html)
- [Comparison of disk encryption software](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/Comparison_of_disk_encryption_software.html)



출처: <https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/On-the-fly_encryption.html>



## FreeOTFE

FreeOTFE는 OTFE (on-the-fly disk encryption)를위한 단종 된 오픈 소스 컴퓨터 프로그램입니다. Microsoft Windows 및 Windows Mobile (FreeOTFE4PDA 사용)에서는 파일이나 파티션 내에 가상 드라이브를 만들 수 있습니다. 가상 드라이브는 컴퓨터 하드 또는 USB 드라이브에 저장되기 전에 자동으로 암호화됩니다. 이 기능은 TrueCrypt와 Microsoft의 [BitLocker](https://ipfs.io/ipfs/QmXoypizjW3WknFiJnKLwHCnL72vedxjQkDDP1mXWo6uco/wiki/BitLocker.html) 를 포함한 다른 디스크 암호화 프로그램과 유사합니다. [2]

저자 인 사라 딘 (Sarah Dean)은 2011 년에 결석했습니다. FreeOTFE 웹 사이트는 2013 년 6 월 현재 도달 할 수 없으며 이제 도메인 스쿼터에 의해 도메인 이름이 등록됩니다. 원본 프로그램은 [Sourceforge의 미러](http://sourceforge.net/projects/freeotfe.mirror/)에서 다운로드 할 수 있습니다. 2014 년 6 월에 <span style="color:red">**LibreCrypt**</span> 라는 프로젝트의 포크가 GitHub에 나타났습니다. [3]

- <https://sourceforge.net/projects/freeotfe.mirror/>

  



### Overview

FreeOTFE는 2004 년 Sarah Dean에 의해 처음 출시되었으며, 필요할 경우 제 3자가 추가 알고리즘을 구현할 수있는 모듈 식 아키텍처를 제공하는 최초의 오픈 소스 코드 디스크 암호화 시스템이었습니다. 이전 FreeOTFE 라이센스는 프로그램의 모든 수정 사항을 공개 도메인에 배치해야했습니다. 이것은 오픈 소스 정의의 3 절과 기술적으로 부합하지 않습니다. 새로운 프로그램 라이센스는이 조건을 생략합니다. FreeOTFE 라이센스는 오픈 소스 이니셔티브에 의해 승인되지 않았으며 오픈 소스 인증 마크의 라벨이 부착되어 있지 않습니다.

이 소프트웨어는 Linux 암호화 볼륨 (예 : LUKS, cryptoloop, dm-crypt)과 호환되므로 Linux에서 암호화 된 데이터를 자유롭게 읽고 쓸 수 있습니다. Windows Vista 및 PDA를 지원하는 최초의 오픈 소스 투명 디스크 암호화 시스템이었습니다. [5] [6] [7]

RSA Laboratories에서 개발 한 PKCS11 (Cryptoki) 표준을 사용하여 v4.0에서 스마트 카드 및 / 또는 하드웨어 보안 모듈 (HSM, 보안 토큰이라고도 함) [8]을 사용한 선택적 2 단계 인증이 도입되었습니다.

FreeOTFE는 또한 '숨겨진 볼륨'을 생성하여 그럴듯한 거부 가능성과 부인할 수있는 암호화를 허용하며, 전체 파티션이나 디스크 (시스템 파티션 제외)를 암호화 할 수있는 옵션도 제공합니다.



### Portable use

FreeOTFE는 '휴대용'(또는 '여행자') 모드로 사용할 수 있습니다.이 모드는 암호화 된 데이터와 함께 USB 드라이브 또는 기타 휴대용 미디어에 보관되어 운반 될 수 있습니다. 따라서 '마운트'하고 가상 디스크를 통해 암호화 된 데이터에 액세스 할 수있는 전체 프로그램을 설치하지 않고 Microsoft Windows에서 사용할 수 있습니다.

이 모드를 사용하려면 가상 디스크를 만들기 위해 (최소한 일시적으로) 장치 드라이버를 설치해야하며, 결과적으로이 여행자 모드를 시작하려면 관리자 권한이 필요합니다. 장치 드라이버를 사용하는 대부분의 오픈 소스 소프트웨어와 마찬가지로 사용자는 Windows Vista x64 및 Windows 7 x64 시스템에서 실행될 때 테스트 서명 [10]을 활성화해야합니다. [11]



### Driverless operation

FreeOTFE와 함께 패키지 된 'FreeOTFE Explorer'[12]라는 또 다른 프로그램은 관리자 권한없이 암호화 된 디스크를 사용할 수있는 무인 시스템을 제공합니다.

이를 통해 관리자 권한을 사용할 수없는 라이브러리 또는 컴퓨터 키오스크 (대화 형 키오스크)에있는 공용 컴퓨터 (예 : 공용 컴퓨터)에서 FreeOTFE 암호화 데이터를 사용할 수 있습니다.

FreeOTFE와 달리 FreeOTFE 탐색기는 가상 드라이브를 통한 즉석 암호화를 제공하지 않습니다. [12] 대신 ZIP 및 RAR 아카이브와 비슷한 방식으로 Windows 탐색기 인터페이스를 사용하여 암호화 된 디스크 이미지에서 파일을 저장하고 추출 할 수 있습니다.



### Source Forge 에 있는 설명

이 미러의 원본 파일이있는 업스트림 소스 사이트는 더 이상 사용할 수 없습니다.

이 소프트웨어를 사용하면 PC / PDA에 하나 이상의 '가상 디스크'를 만들 수 있습니다. 이 디스크는 일반 디스크와 똑같이 작동하지만 컴퓨터의 하드 드라이브에 저장되기 전에 암호화 된 내용 중 하나라도 투명하고 안전하게 암호화됩니다.



#### 특징

- 이 소프트웨어를 사용하면 PC / PDA에 하나 이상의 '가상 디스크'를 만들 수 있습니다. 이 디스크는 일반 디스크와 똑같이 작동하지만 컴퓨터의 하드 드라이브에 저장되기 전에 암호화 된 내용 중 하나라도 투명하고 안전하게 암호화됩니다.
- 간편한 사용 - 새 볼륨을 생성하기위한 전체 마법사가 포함되어 있습니다.
- PC 및 PDA 버전을 사용할 수 있습니다. PC에서 암호화 된 데이터는 PDA에서 읽거나 쓸 수 있으며 그 반대의 경우도 마찬가지입니다.
- 암호화 된 Linux 볼륨 지원 (Cryptoloop 'losetup', dm-crypt 및 LUKS)
- 여러 언어로 제공됩니다.
- 스마트 카드 및 보안 토큰에 대한 선택적 지원.
- 강력한 - 여러 가지 모드 (CBC, LRW 및 XTS)에 다양한 해시 (SHA-512, RIPEMD-320, Tiger 포함) 및 암호화 알고리즘 (AES, Twofish 및 Serpent 포함)을 지원하여 다른 OTFE 시스템의 수.
- 암호화 된 볼륨은 파일, 파티션 또는 디스크 기반 일 수 있습니다.



#### 다운로드 파일

- <span style="color:green">**FreeOTFE_5_21.zip**</span>
- <span style="color:green">**FreeOTFE_5_21.exe**</span>
- <span style="color:green">**FreeOTFE_PC5_21_PDA5_21_Explorer3_51_src.zip**</span>







## LibreCrypt

FreeOTFE 를 fork 하여 만든 Project

위치: <https://github.com/t-d-k/LibreCrypt>

git: https://github.com/t-d-k/LibreCrypt.git



### 특징

- 완전 투명 암호화, 컨테이너는 Windows 탐색기에서 이동식 디스크로 나타납니다.
- Linux 암호화와 호환 : dm-crypt 및 LUKS. Linux 셸 스크립트는 Linux에서 거부 할 수있는 암호화를 지원합니다.
- Explorer 프로그램을 사용하면 관리자 권한이 없을 때 컨테이너를 찾을 수 있습니다.
- 스마트 카드 및 보안 토큰을 지원합니다.
- 암호화 된 컨테이너는 파일, 파티션 또는 전체 디스크 일 수 있습니다.
- FreeOTFE로 만든 레거시 볼륨을 엽니 다.
- Windows Vista 이상에서 실행됩니다 (아래 64 비트 버전 참고).
- 여러 가지 모드 (CBC, LRW 및 XTS)에서 많은 해시 (SHA-512, RIPEMD-320, Tiger 포함) 및 암호화 알고리즘 (AES, Twofish 및 Serpent 포함)을 지원합니다.
- 선택적 '키 파일'을 사용하여 썸 드라이브를 키로 사용할 수 있습니다.
- 휴대용 모드를 설치할 필요가 없으며 타사 PC (관리자 권한 필요)에 거의 추적을 남기지 않습니다.
- Deniable encryption in case of 'rubber hose cryptanalysis'.
- Windows 용으로 가장 사용하기 쉬운 암호화 프로그램으로 간주됩니다.

**이 버전은 몇 가지 알려진 제한 사항이있는 베타 버전입니다. 특히 64 비트 Windows에서 텍스트 'Test Mode'가 바탕 화면에 표시됩니다.**

<span style="color:red">드라이버가 등록 되어 있지 않아 Windows 테스트 모드 에서만 동작됨.</span>

<span style="color:red">설치후 컨테이너 이미지는 잘 만들어 지지만 마운트 하려면 오류가 발생 함.</span>











