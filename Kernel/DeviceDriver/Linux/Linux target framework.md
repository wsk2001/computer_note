# Linux target framework

출처: https://github.com/bobphill/tgt



## 소개

Linux target framework (tgt)는 다양한 SCSI target을 단순화하는 것을 목표로합니다.
드라이버 (iSCSI, Fibre Channel, SRP, etc) 생성 및 유지 관리

현재 tgt는 다음 대상 드라이버를 지원합니다.

- 이더넷 NIC 용 iSCSI 소프트웨어 대상 드라이버
- Infiniband 및 RDMA NIC 용 iSER 소프트웨어 대상 드라이버
- IBM System p VIO 서버
- 이더넷 NIC 용 FCoE 소프트웨어 대상 드라이버 (진행 중)
- Qlogic qla2xxx FC 대상 드라이버 (진행 중)

Tgt는 커널 모듈, 사용자 공간 데몬 및 사용자 공간 도구로 구성됩니다.
ISCSI, iSER 및 FCoE 대상 드라이버는 사용자 공간 데몬 및 도구 만 사용합니다 (즉, 사용자 공간 응용 프로그램이므로 커널 지원이 필요하지 않습니다).

Tgt는 다음 장치 유형을 에뮬레이션 할 수 있습니다.

- ```
  SBC: 파일을 사용하여 내용을 저장할 수있는 가상 디스크 드라이브
  ```

- ```
  SMC: 'mtx'에 의해 제어 될 수있는 가상 미디어 주크 박스 툴.
  ```

- ```
  MMC: DVD-ROM ISO 파일을 읽고 레코딩 가능한 DVD + R을 생성 할 수있는 가상 DVD 드라이브
  SMC와 결합하여 DVD 주크 박스를 완벽하게 작동시킬 수 있습니다.
  ```

- ```
  SSC: 파일을 사용하여 내용을 저장할 수있는 가상 테이프 장치 (일명 VTL)
  ```

- ```
  OSD: 파일을 사용하여 내용을 저장할 수있는 가상 객체 기반 저장 장치 (진행 중).
  ```



이 코드는 GNU General Public License 버전 2를 따릅니다.



## 준비

Tgt가 signalfd로 더 나은 성능을 얻을 수 있기 때문에 Linux 커널 2.6.22 이상이 권장됩니다.

대상 드라이버에는 자체 빌드, 구성 등의 방법이 있습니다.
Doc 디렉토리에서 적절한 문서를 찾으십시오.
Tgt 사이트에서 다른 유용한 정보를 찾을 수 있습니다.

http://stgt.sourceforge.net/



## 개발자 노트
Tgt 개발의 중심 자원은 메일 링리스트입니다. (stgt@vger.kernel.org).
먼저 다음 문서를 읽으십시오 (짧게 말하면 Linux 커널 개발 규칙을 따르십시오).
http://lxr.linux.no/source/Documentation/CodingStyle
http://lxr.linux.no/source/Documentation/SubmittingPatches

다음 예제와 같이 제출하기 전에 패치 스타일 검사기로 패치를 확인하십시오 (scripts / checkpatch.pl).

```
fujita@arbre:~/git/tgt$ ./scripts/checkpatch.pl ~/0001-add-bidi-support.patch
Your patch has no obvious style problems and is ready for submission.
```



## member

https://github.com/wsk2001/tgt/network/members

