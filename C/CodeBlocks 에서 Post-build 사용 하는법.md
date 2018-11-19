### CodeBlocks 에서 Post-build 사용 하는법

<br/>

CodeBlocks 에서 컴파일 후, output file 을 특정 directory 로 복사 하기 위해서는 Post-build Step 을 사용하여 원하는 위치로 복사 하면 된다.

<br/>

Post-build 를 사용하는 방법은 project 에서 우측 mouse 를 click 한 후 Build Option 선택 -> Pre/post build step -> Post-build steps 창에 명령어를 입력 하면 된다.

<br/>

다음은 사용 예 임.

```
cmd /c copy "$(PROJECT_DIR)$(TARGET_OUTPUT_FILE)" "..\bin"
```

- 컴파일된 결과 파일을 "..\bin" 경로로 복사 한다.

- 참고로 Code::Blocks 의 project 파일을 이용하여 Makefile 을 만들어 주는 tool 이 있다. cbp2make
- 파일은 https://sourceforge.net/projects/cbp2make/ 에서 내려 받아 사용 하면 된다.
- 사용 방법은 https://sourceforge.net/p/cbp2make/wiki/Home/ 에 잘 나와 있다.

사용 예
```
cbp2make -in my_project.cbp -windows

cbp2make -in my_project.cbp -unix

cbp2make -in my_project.cbp -unix -windows -mac

cbp2make -in my_project.cbp --all-os
```

### - 끝 -


