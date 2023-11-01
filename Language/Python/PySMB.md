# PySMB 

출처: https://pysmb.readthedocs.io/en/latest/

pysmb는 클라이언트 측 SMB/CIFS 프로토콜(SMB1 및 SMB2)을 순수 Python으로 구현한 것입니다. 이는 Samba 서버 애플리케이션을 통해 Linux 시스템뿐만 아니라 Windows 시스템 간의 파일 공유 및 인쇄를 용이하게 하는 기본 프로토콜입니다. pysmb는 Python 2.7.x 및 Python 3.8.x에서 개발되었으며 Windows 7, Windows 10 및 Samba 4.x의 공유 폴더에 대해 테스트되었습니다.



## License

pysmb 자체는 오픈소스 라이선스에 따라 라이선스가 부여됩니다. 상업적 목적을 포함한 모든 애플리케이션에서 pysmb를 자유롭게 사용할 수 있습니다. 사용 약관에 대한 자세한 내용은 pysmb 소스와 함께 제공되는 LICENSE 파일을 읽어보세요.

pysmb는 사용 약관이 pysmb에 포함되지 않는 다른 타사 모듈에 의존합니다. 이러한 모듈을 사용하면 라이센스 요구 사항과 충돌할 수 있습니다. 자신의 재량에 따라 적합성을 결정하십시오. 다음 섹션에 이러한 모듈을 나열했습니다.



## Credits

pysmb는 혼자가 아닙니다. 이는 다른 모듈의 지원을 통해 가능해졌습니다.

- **pyasn1** : ASN.1 구문 분석 및 인코딩의 순수 Python 구현(pysmb와 함께 포함되지 않음, 별도로 설치해야 함)
- **md4** and **U32** : Dmitry Rozmanov가 작성한 MD4 해싱 알고리즘 및 32비트 부호 없는 정수의 순수 Python 구현입니다. LGPL에 따라 라이센스가 부여되고 pysmb와 함께 포함됩니다.
- **pyDes** : Todd Whiteman이 작성한 DES 암호화 알고리즘의 순수 Python 구현입니다. 무료 도메인이며 pysmb와 함께 포함되어 있습니다.
- **sha256** : Thomas Dixon이 작성한 SHA-256 메시지 다이제스트의 순수 Python 구현입니다. MIT에 따라 라이센스가 부여되었으며 pysmb와 함께 포함되어 있습니다. 이 모듈은 Python 표준 라이브러리(일반적으로 Python 2.4)가 SHA-256 해시 알고리즘을 제공하지 않는 경우에만 가져옵니다.



## 패키지 내용물 및 설명

pysmb는 smb와 nmb의 두 가지 주요 패키지로 구성됩니다. smb 패키지에는 SMB(서버 메시지 블록) 구현과 관련된 모든 기능이 포함되어 있습니다. 애플리케이션 개발자로서 귀하는 이 모듈을 애플리케이션으로 가져오게 됩니다. 따라서 시간을 내어 smb 패키지 내용을 숙지하시기 바랍니다.



- **nmb/base.py** : 네트워크 전송 관련 사항 없이 NetBIOS 세션 및 NetBIOS 이름 서비스 통신을 구현하는 NetBIOSSession 및 NBNS 추상 클래스를 포함합니다.
- **nmb/NetBIOS.py**: IP 주소에서 컴퓨터 이름을 쿼리하는 NBNS 구현을 제공합니다. 모든 작업이 I/O를 차단하고 있습니다.
- **nmb/NetBIOSProtocol.py** : Twisted 프레임워크에서 사용할 수 있는 NBNS 프로토콜 구현을 제공합니다.
- **smb/base.py** : 네트워크 전송 관련 세부 사항 없이 SMB 통신을 구현하는 SMB 추상 클래스를 포함합니다.
- **smb/ntlm.py** : NTLMv1 및 NTLMv2 인증 루틴과 NTLM의 디코딩/인코딩이 포함되어 있습니다.
- **smb/securityblob.py** : SMB 메시지의 NTLMSSP 보안 Blob을 인코딩/디코딩하는 루틴을 제공합니다.
- **smb/smb_constants.py** : All the constants used in the smb package for SMB1 protocol
- **smb/smb_structs.py** : Contains the internal classes used in the SMB package for SMB1 protocol. These classes are usually used to encode/decode the parameter and data blocks of specific SMB1 message.
- **smb/smb2_constants.py** : All the constants used in the smb package for SMB2 protocol
- **smb/smb2_structs.py** : Contains the internal classes used in the SMB package for SMB2 protocol. These classes are usually used to encode/decode the parameter and data blocks of specific SMB2 message.
- **smb/SMBConnection.py** : Contains a SMB protocol implementation. All operations are blocking I/O.
- **smb/SMBProtocol.py** : Contains the SMB protocol implementation for use in the Twisted framework.
- **smb/SMBHandler.py** : Provides support for “[smb://](smb://)” URL in the urllib2 python package.

