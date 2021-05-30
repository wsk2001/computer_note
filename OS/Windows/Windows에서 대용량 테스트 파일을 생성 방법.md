# Windows에서 대용량 테스트 파일을 생성 방법

출처: https://tweaks.com/windows/62755/quickly-generate-large-test-files-in-windows/

다른 100MB 테스트 파일을 다운로드하거나 대용량 파일을 검색하는 데 시간을 낭비하지 마십시오. 때로는 데이터 전송 또는 디스크 성능을 테스트하기 위해 대용량 파일이 필요합니다. Windows에는 모든 크기의 파일을 즉시 생성 할 수있는 유틸리티가 포함되어 있습니다.

관리자 권한의 명령 프롬프트를 엽니다.

다음 명령을 실행하십시오.

``` cmd
fsutil file createnew <file> <size in bytes>
```

예를 들어, 이 명령은 내 데스크탑에 1gb.test라는 1GB 파일을 생성합니다.

``` cmd
fsutil file createnew c:\users\steve\desktop\1gb.test 1073741824
```

핵심은 파일 크기를 바이트 단위로 입력하는 것이므로 다음은 계산 에서 절약 할 수있는 몇 가지 일반적인 파일 크기입니다.

- 1 MB  = 1048576 bytes
- 100 MB = 104857600 bytes
- 1 GB = 1073741824 bytes
- 10 GB = 10737418240 bytes
- 100 GB =107374182400 bytes
- 1 TB = 1099511627776 bytes
- 10 TB =10995116277760 bytes



### 끝

