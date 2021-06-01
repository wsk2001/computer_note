# C ++ 애드온으로 Node.js 생산성을 향상시키는 방법

출처: https://apiko.com/blog/node-js-performance-c-addons/

node.js는 JavaScript 프로그래밍 언어로 애플리케이션을 개발하기위한 오픈 소스 플랫폼입니다. Node.js는 JavaScript 코드를 실행하는 V8 엔진을 기반으로합니다. Node.js는 JavaScript를 브라우저 개발을 넘어 다음과 같은 다양한 플랫폼으로 가져갑니다.

- 서버 측 플랫폼
- Electron, NW.js, Squoosh 등을 사용하는 데스크톱 애플리케이션
- 마이크로 컨트롤러, 자동화, 빌드, 테스트 등을위한 명령 줄 도구

그렇다면 Node.js 개발의 주요 특징은 무엇입니까? 네트워크 애플리케이션 구축입니다. Node.js는 빠르고 확장 가능한 네트워크 애플리케이션 개발의 진정한 스타입니다. 그러나 Node.js가 다른 기술보다 덜 효과적인 시나리오가 있습니다.

- 고정밀 계산

자바 스크립트는 정확도가 높은 계산 언어가 아니기 때문에 일부 계산 부정확성 (특히 부동 숫자의 경우)이 발생할 수 있습니다.

- 병렬 계산

Node.js는 네트워크 애플리케이션을위한 단일 스레드 계산에서 빛을 발합니다. 그러나 Node.js에는 병렬 처리를위한 즉시 사용 가능한 솔루션이 없습니다. 이를 위해 추가 패키지를 설치해야합니다.

- 저수준 계산

JavaScript는 높은 수준의 프로그래밍 언어이므로 RAM 또는 특정 하드웨어 작업과 같은 낮은 수준의 작업을위한 효율적인 도구가 없습니다.

물론 이러한 문제는 패키지 또는 확장을 통해 해결됩니다. 그러나이 기사에서는 이러한 경우 Node.js 성능을 확장하는 방법을 보여주고 싶습니다.

뛰어 들자.



## Node.js and C++

노드 기술은 낮은 수준의 작업이나 무거운 계산을위한 응용 프로그램 개발에서 매우 빠르고 강력한 C++로 작성되었습니다.

결과적으로 Node는 C++ 친화적이며 개발자가 Node.js 애플리케이션에서 C++ 코드를 실행할 수 있습니다. 이것은 Node 애드온의 도움으로 수행됩니다.

Node.js 애드온은 C++로 작성된 동적으로 연결된 공유 객체입니다. require () 함수를 사용하여 Node.js에 로드 할 수 있으며 일반 Node.js 모듈처럼 사용할 수 있습니다. 주로 Node.js에서 실행되는 JavaScript와 C/C++ 라이브러리 간의 인터페이스를 제공하는 데 사용됩니다.

Node.js 애드온이 우리에게주는 힘은 무엇입니까?

- 집중적이고 병렬 적이며 정확한 계산을 할 수있는 기회
- C++ 라이브러리를 사용할 수있는 기회

이론적으로는 흥미로울 것 같지만 연습은 어떻습니까?

이 튜토리얼에서는 먼저 JavaScript와 C++ 애드온으로 동일한 작업을 수행하고 성능의 차이를 보여줍니다.



## Node JS 애드온 성능 테스트

먼저 전통에 대한 경의를 표하고“Hello world!”를 발전시킬 것입니다. C++ 애드온이있는 애플리케이션.

프로젝트 설정은 여러 단계로 구성됩니다.

- 사전 설정

작업을 시작하려면 컴퓨터에 다음 소프트웨어를 설치하십시오.

- Node JS
- Npm

Npm은 Node.js의 패키지 관리자입니다. 라이브러리 및 모듈과 같은 준비된 솔루션을로드하고 관리합니다. 일반적으로 Node는 Npm과 함께 설치됩니다.
[여기](https://nodejs.org/uk/download/package-manager/) 에서 가장 널리 사용되는 운영 체제의 명령 줄을 사용하여 Node를 설치하는 방법에 대한 자습서를 찾을 수 있습니다.



### Step 1

새 Npm 디렉토리를 초기화하려면 다음 명령 코드를 실행하십시오. npm init. 프로젝트를 설정하려면 몇 가지 질문에 답해야합니다. 마지막으로 프로젝트에 대한 기본 정보가 포함 된 package.json 파일이 프로젝트 디렉토리에 생성됩니다. 필요한 패키지 목록도이 파일에 저장됩니다.

``` bash
> $ npm init 
```



### Step 2

프로젝트를 설정 한 후 필요한 패키지를 설치하십시오. 이 프로젝트에서는 'node-gyp'패키지 하나만 필요합니다. Node.js 애드온을 컴파일하는 도구입니다.

간단히 말해서 node-gyp은 Node.js와 함께 사용하기 위해 C/C++를 컴파일합니다. 이 패키지는 컴파일해야하는 코드가 패키지와 함께로드되는 경우가 많기 때문에 많은 앱에서 필수입니다.

node-gyp의 또 다른 특징은 운영 체제에 관계없이 코드를 컴파일한다는 것입니다. 이 기능을 사용하면 노드 기반 프로젝트의 운영 체제 차이와 관련된 많은 실수를 방지 할 수 있습니다.

node-gyp를 설정하려면 npm install g node-gyp 명령을 실행합니다.

``` bash
> $ npm install g node-gyp
```

설명

- npm: 프로젝트 이름 ( 유틸리티 이름)
- install:  설치 옵션
- node-gyp: 설치할 package 이름
- g:  프로젝트가 전역 적으로 설치되고 이 운영 체제의 모든 노드 프로젝트에 사용되어야 함을 나타냅니다.



### Step 3

C/C ++ 코드 컴파일 용 패키지를 설치 한 후 프로젝트 내에서 node-gyp 를 구성합니다. 먼저 프로젝트 내부에 binding.gyp 파일을 만듭니다. 이 파일에는 node-gyp 기능에 대한 정보가 포함됩니다.

`binding.gyp`

``` gyp
{
   "targets": [
       {
           "target_name": "addon",
           "sources": [ "addon.cpp" ]
       }
   ]
}
```

설명

- "target_name":  C++ 의 컴파일 결과 파일의 이름 (addon.node 라는 이름으로 갱성된다) 
- “Sources” : C++ 코드를 포함하는 * .cpp 파일 이름의 배열



### Step 4

애드온 코드 작성 및 컴파일

애드온 파일을 작성해 보겠습니다. 내 프로젝트의 루트에서‘addon.cpp’파일을 만듭니다.

모듈을 포함하는 것부터 시작합니다.

```c++
#include <node.h>
#include <iostream>
```

node.h는 Node 애플리케이션과 어떤 종류의 인터페이스를 구축하는 데 필요합니다.

C/C++ 언어에는 기본 제공 입력/출력이 없습니다. 대신 표준 C++에 포함 된 iostream 라이브러리를 사용하고 입출력을 관리하는 메서드를 포함합니다.

``` c++
using namespace v8;
using namespace std;
```

이 코드의 도움으로 네임 스페이스를 파일에 연결할 수 있습니다. 위에 나열된 네임 스페이스에있는 변수를 이제 파일에서 사용할 수 있습니다.

예를 들어, "std"네임 스페이스의 "cout"및 "endl"함수와 V8 네임 스페이스의 "FunctionCallbackInfo", "Value", "Local", "Object"도 사용합니다. 이러한 함수 나 변수는 가져 오지 않습니다. 네임 스페이스 연결 후 파일에 나타납니다.

```c++
void HelloWorld(const FunctionCallbackInfo<Value>& args) {
   cout << "Hello, world!" << endl;
}
```

이 코드 부분은 이해하기 쉽습니다. 표준 출력 스레드에 동음 이의어 줄을 제공하는 함수 "HelloWorld"를 설명합니다. 또한이 함수에는 callback과 args 배열이라는 두 개의 매개 변수가 있지만 아직 필요하지 않습니다.

```c++
void Initialize(Local<Object> exports) {
   NODE_SET_METHOD(exports, "helloWorld", HelloWorld);
}
```

이 함수는 HelloWorld 함수 내보내기를 담당합니다.

"helloWorld"라는 이름으로 내보내집니다. NODE_SET_METHOD는 3 개의 인수를 허용합니다.

- the exports object,
- 함수를 내보낼 이름,
- 함수 이름.

보시다시피 임의의 이름으로 함수를 내보낼 수 있습니다.

공통 노드 애플리케이션을 사용하여 컴파일 된 C++ 코드를 실행합니다.

마지막 단계는 애드온을 초기화하는 것입니다. 첫 번째 인수는 세 번째 단계의 구성 파일에서와 동일한 이름을 가져야합니다. 애드온 파일의 전체 코드는 다음과 같습니다.

```c++
#include <node.h>
#include <iostream>

using namespace v8;
using namespace std;

void HelloWorld(const FunctionCallbackInfo<Value>& args) {
   cout << "Hello, world!" << endl;
}

void Initialize(Local<Object> exports) {
   NODE_SET_METHOD(exports, "helloWorld", HelloWorld);
}

NODE_MODULE(addon, Initialize);
```

그 후 node-gyp configure build 명령을 사용하여 애드온을 컴파일 할 수 있습니다.

``` bash
> $ node-gyp configure build 

[root addons]# node-gyp configure build
gyp info it worked if it ends with ok
... 생략
gyp info spawn args [ 'BUILDTYPE=Release', '-C', 'build' ]
make: Entering directory `/home/root/workspace/NODE/addons/build'
make: Nothing to be done for `all'.
make: Leaving directory `/home/root/workspace/NODE/addons/build'
gyp info ok
[root addons]# 
```



### Step 5

마지막 단계는 컴파일 된 애드온이 작동하는지 테스트하기 위해 일반 JS 파일을 만드는 것입니다.

프로젝트의 루트에 my_app.js 파일을 생성합니다.

3 줄의 코드를  작성 합니다.

`my_app.js`

``` javascript
const addon = require('./build/Release/addon');

const runAddon = () => addon.helloWorld();

runAddon();
```

첫 번째 줄-애드온 가져 오기.

두 번째 줄 (선택 사항)-애드온 "helloWorld"내보내기 함수를 호출하는 함수 래퍼;

세 번째 줄-함수 호출.

모든 작업을 올바르게 수행하면 'node my_app'명령으로이 파일을 실행할 수 있습니다.

예상되는 결과는 다음과 같습니다.

``` bash
[root addons]# node my_app
Hello, world!
[root addons]#
```

보시다시피 앱은 Hello world 메시지를 콘솔에 넣고 이 작업은 C++ 언어의 "addon.cpp"파일에 프로그래밍되었습니다.



## Java Script vs C++ performance

우리는 이미 Node.js 애플리케이션에서 C++ 코드를 실행하는 방법을 알고 있습니다. 이제 좀 더 흥미 진진한 작업에이 기술을 사용하여 재미를 느낄 때입니다.

JS와 C++ 모두에서 높은 정확도를 요구하는 무거운 계산을 수행하고 실행 시간을 숫자로 비교해 보겠습니다.

먼저 하나 이상의 기능으로 애드온을 확장 해 보겠습니다.

```c++
#include <node.h>
#include <iostream>

using namespace v8;
using namespace std;

void HelloWorld(const FunctionCallbackInfo<Value>& args) {
   cout << "Hello, world!" << endl;
}

void AddNumbers(const FunctionCallbackInfo<Value>& args) {
   Isolate* isolate = args.GetIsolate();
   double valueToSum = args[0].As<Number>()->Value();
   double result = 0;
   int sumCount = args[1].As<Integer>()->Value();
   int i;

   for (i = 0; i < sumCount; i++) {
       result = result + valueToSum;
   }

   args.GetReturnValue().Set(result);
}


void Initialize(Local<Object> exports) {
   NODE_SET_METHOD(exports, "helloWorld", HelloWorld);
   NODE_SET_METHOD(exports, "addNumbers", AddNumbers);
}

NODE_MODULE(addon, Initialize);
```

args 객체의 도움으로 우리는 함수를 호출 할 때 전송 될 두 개의 매개 변수를 얻습니다.

- double valueToSum = args[0]->NumberValue(); :  더할 값을 얻어 실수로 바꿉니다.
- int sumCount = args[1]->IntegerValue(); : 두 번째 매개 변수는 숫자가 합산되는 횟수입니다. 우리는 그것을 실수로도 바꿉니다.

주기에 숫자를 추가하고 결과를 변수 "result"에 저장합니다.

유사한 기능이 Js 파일에 작성되었습니다. 실행 시간을 확인하고 이전 시간과 비교해 보겠습니다.

`my_app.js`

``` javascript
const addon = require('./build/Release/addon');

const runAddon = () => addon.helloWorld();

runAddon();

const addNumbersAddon = () => addon.addNumbers(3.14, 1000000);

const addNumbersNode = (num, count) => {
 let result = 0;
 for (let i = 0; i < count; i++) {
   result = result + num;
 }
 return result;
};

const addNumbers = (number, addingTimes) => {
 console.time('C++');
 console.log(addNumbersAddon(number, addingTimes));
 console.timeEnd('C++');

 console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');

 console.time('JS');
 console.log(addNumbersNode(number, addingTimes));
 console.timeEnd('JS');
};

addNumbers(3.14, 100000000);
```

성능 테스트를 위해 우리는 수억 번에 3.14 숫자를 추가 할 것입니다.

Here are the results:

`실행`

``` bash
[root addons]# node --version
v12.18.3

[root addons]# node-gyp configure build

[root addons]# node-gyp configure build

-- 결과
[root addons]# node my_app
Hello, world!
3140000.000013152
C++: 8.384ms
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
313999999.2044479
JS: 120.571ms

```

이 경우 C++는 훨씬 더 빠르게 작동하고 더 정확한 결과를 제공합니다.



## Сonclusion

나는 멋진 C++의 배경에 JS가 얼마나 나쁜지 보여주지 않기 위해 이 글을 썼다. 각 프로그래밍 언어는 잘 다루는 특정 응용 프로그램을 위해 설계되었습니다. 이 기사의 목적은 C++ 애드온의 도움으로 Node 앱을 확장하고 사용자 정의 계산 집약적 애플리케이션에서 더 나은 생산성을 얻을 수있는 방법을 보여주는 것입니다.



## 참조 binding.gyp

```gyp
{
    "targets": [
        {
            "target_name": "test",
            "sources": [
                "src/test.cpp"
            ],
            "include_dirs": [
                "<!@(node -p \"require('node-addon-api').include\")",
                "<(module_root_dir)/native/include"
            ],
            "conditions": [
                ["OS in \"linux\"", {
                    "libraries": [
                        "-lgeometry",
                        "-L<(module_root_dir)/native/lib/linux/libgeometry",
                        "-Wl,-rpath,$$ORIGIN/../../native/lib/linux/libgeometry"
                    ],
                    "defines": [
                        "NAPI_CPP_EXCEPTIONS"
                    ]
                }],
                ["OS in \"mac\"", {
                    "libraries": [
                        "-lgeometry",
                        "-L<(module_root_dir)/native/lib/darwin/libgeometry",
                        "-Wl,-rpath,@loader_path/../../native/lib/darwin/libgeometry"
                    ],
                    "xcode_settings": {
                        "GCC_ENABLE_CPP_EXCEPTIONS": "YES",
                        "CLANG_CXX_LIBRARY": "libc++"
                    }
                }]
            ],
            "dependencies": ["<!(node -p \"require('node-addon-api').gyp\")"],
            "cflags": ["-fexceptions"],
            "cflags_cc": ["-fexceptions"],
            "msvs_settings": {
                "VCCLCompilerTool": {
                    "ExceptionHandling": 1
                }
            }
        }
    ]
}
```



---

## CentOS7 에 node.js 설치 후 C++ 함수 호출 하는 절차

### 1. 준비

``` bash
[root ~]# yum groupinstall "Development Tools"
[root ~]# yum install -y epel-release
[root ~]# yum -y update
[root ~]# curl -sL https://rpm.nodesource.com/setup_12.x | sudo bash -
[root ~]# yum install yarn
[root ~]# curl -sL https://dl.yarnpkg.com/rpm/yarn.repo | sudo tee /etc/yum.repos.d/yarn.repo
[root ~]# yum install yarn
[root ~]# npm install g node-gyp
```



### 2. 프로젝트 생성

``` bash
# 작업 directory 생성
[root ~]# mkdir -p /home/root/workspace/node_api
[root ~]# cd /home/root/workspace/node_api

[root node_api]# npm init
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help init` for definitive documentation on these fields
and exactly what they do.

Use `npm install <pkg>` afterwards to install a package and
save it as a dependency in the package.json file.

Press ^C at any time to quit.
package name: (node_api)
version: (1.0.0)
description: node.js api test
entry point: (index.js)
test command:
git repository:
keywords:
author: wonsool
license: (ISC)
About to write to /home/root/workspace/node_api/package.json:

{
  "name": "node_api",
  "version": "1.0.0",
  "description": "node.js api test",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "wonsool",
  "license": "ISC"
}


Is this OK? (yes)
[root node_api]#
```



### 3. 파일 생성

`concatstr.c`

```c++
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "concatstr.h"


int xdb_enc(char* policy, char* src, char* dst)
{
    int res = 0;
    int i = 0, len = strlen(src);

    for(i = 0; i < len; i++, src++, dst++)
        *dst = *src+1;
    return res;
}

int xdb_dec(char* policy, char* src, char* dst)
{
    int res = 0;
    int i = 0, len = strlen(src);

    for(i = 0; i < len; i++, src++, dst++)
        *dst = *src-1;
    return res;
}

```



`concatstr.h`

```c
#pragma once

int xdb_enc(char* policy, char* src, char* dst);
int xdb_dec(char* policy, char* src, char* dst);

```





`Makefile`

``` makefile
CC              = gcc

CFLAGS          = -m64 -fPIC -DMACHINE64 -D_REENTRANT \
		  		-I. 

LFLAGS          = -L. -L../lib -lm -lrt -ldl -shared -nostdlib
				  
SOURCES		    = concatstr.c

OBJS		    = $(SOURCES:.c=.o )

TARGET			= $(SOURCES:.c=.so )

.c.o:
	$(CC) $(CFLAGS) -c $*.c


.SUFFIXES: .o .c


all:  $(OBJS) $(TARGET)


$(TARGET):
	$(CC) -m64 -fPIC -o $@ $(OBJS) $(LFLAGS)
	cp $@ ../lib

clean:
	rm -f *.o
	rm -f $(TARGET)
 


```



`binding.gyp`

``` gyp
{
   "targets": [
       {
           "target_name": "cpp_util",
           "sources": [ "cpp_util.cpp" ],
            "include_dirs": [
                "./",
                "./C_MODULE"
            ],
            "libraries": [
                "-lconcatstr",
                "-L./lib"
            ]
       }
   ]
}
```



`cpp_util.cpp`

``` c++

```



`cpp_api_test.js`

``` javascript

```

