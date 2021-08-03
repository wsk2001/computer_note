# Linux 테스트 파일 생성 스크립트

Linux 에서 파일 읽기 성능 측정 을  위한 테스트 파일 생성 스크립트



``` bash
for num in `seq 1 100`; 
	do dd if=/dev/urandom of=file-${num}.dat bs=1024k count=5; 
done
```

