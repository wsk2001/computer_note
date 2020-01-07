# Secure Boot

출처: https://docs.microsoft.com/ko-kr/windows-hardware/drivers/bringup/secure-boot

보안 부팅은 PC 제조업체가 신뢰하는 소프트웨어 만 사용하여 PC를 부팅하는 과정입니다. 보안 부팅은 Microsoft에만 국한되지 않으며 UEFI 사양 문서에 정의되어 있지만 Microsoft에는 아래에 포함 된 링크에 특정 요구 사항이 정의되어 있습니다.

PC가 시작되면 펌웨어는 펌웨어 드라이버 (옵션 ROM) 및 운영 체제를 포함하여 각 부팅 소프트웨어의 서명을 확인합니다. 서명이 양호하면 PC가 부팅되고 펌웨어가 운영 체제를 제어합니다.

Windows 운영 체제에는 보안 부팅이 필요합니다. Windows 8, 8.1 및 10이며 UEFI 사양 문서의 일부이기도합니다. 추가 정보는 UEFI 사양 문서의 [27.1 보안 부팅](https://uefi.org/sites/default/files/resources/UEFI_2_3_1_C.pdf) 섹션을 참조하십시오.

보안 부팅을위한 Windows 요구 사항에 대한 자세한 내용은 아래 WHCP-Systems-Specification-1607 링크의 System.Fundamentals.Firmware.UEFISecureBoot를 참조하십시오.



### 관련 자료

[Hardware Security Testability Specification](https://docs.microsoft.com/windows-hardware/test/hlk/testref/hardware-security-testability-specification)

[Windows Hardware Compatibility Program Specifications and Policies](https://docs.microsoft.com/windows-hardware/design/compatibility/whcp-specifications-policies)

[WHCP-Systems-Specification-1607](https://go.microsoft.com/fwlink/?linkid=866948)

[Secured Boot and Measured Boot: Hardening Early Boot Components Against Malware](https://docs.microsoft.com/previous-versions/windows/hardware/design/dn653311(v=vs.85))

[Windows 8.1 Secure Boot Key Creation and Management Guidance](https://docs.microsoft.com/previous-versions/windows/it-pro/windows-8.1-and-8/dn747883(v=win.10))



