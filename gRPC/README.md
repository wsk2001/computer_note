## gRPC 설치 및 사용법

gRPC 의 개념 및 소개는 `gRPC소개.md` 파일을 참조 한다.<br/>

본 문서 에서는 gRPC 설치 방법 및 간단한 사용법을 위주로 기술 하도록 한다. 사용 예제는 Linux(Ubuntu 16.04.5) 를 기준으로 하고 필요시 Windows 에서 사용하는 방법을 추가 하도록 한다.  gRPC 설치 및 사용 방법을 기술 하기 전에 지원하는 플랫폼 을 알아보면 아래 표와 같다.  공식적으로 지원하는 언어 및 플랫폼은 [Officially Supported Platforms](http://www.grpc.io/about/#osp)를 참고 한다.

| Language | Platform | Compiler                                                     |
| -------- | -------- | ------------------------------------------------------------ |
| C/C++    | Linux    | GCC 4.4 <br/>GCC 4.6<br/>GCC 5.3<br/>Clang 3.5<br/>Clang 3.6<br/>Clang 3.7 |
| C/C++ | Windows 7+ | Visual Studio 2013+ |
| C# | Windows 7+<br/>Linux<br/>Mac | .NET Core, .NET 4.5+<br/>.NET Core, Mono 4+<br/>.NET Core, Mono 4+ |
| Dart * | Windows/Linux/Mac | Dart 1.24.3+ |
| Go | Windows/Linux/Mac | Go 1.6+ |
| Java | Windows/Linux/Mac | JDK 8 recommended. Gingerbread+ for Android |
| Node.js | Windows/Linux/Mac | Node v4+ |
| PHP * | Linux/Mac | PHP 5.5+ and PHP 7.0+ |
| Python | Windows/Linux/Mac | Python 2.7 and Python 3.4+ |
| Ruby | Windows/Linux/Mac | |

<br/>


### 1. Ubuntu 16.04 에 gRPC 설치 하기 (C++)

다음은 Ubuntu 에 gRPC(c++) 을 설치 하기 위한 방법을 소개 한다.

`설치 명령어`

```
sudo apt-get update
sudo apt-get upgrade -y

sudo apt-get install -y libtool
sudo apt-get install -y autoconf --fix-missing
sudo apt-get install -y automake
sudo apt-get install -y autogen
sudo apt install -y git
sudo apt install -y curl

git clone -b $(curl -L http://grpc.io/release) https://github.com/grpc/grpc

cd grpc

git submodule update --init

make; sudo make install

# grpc 컴파일시 protobuf 가 컴파일 되므로 별도의 make 는 하지 않고 설치를 위해
# sudo make install 만 수행 한다.
# make install 을 수행 하지 않으면 examples 에 있는 파일을 컴파일 할때 오류가 발생 한다.
cd third_party/protobuf
sudo make install

cd ~/grpc/examples/cpp/helloworld
make
./greeter_server &
./greeter_client
output >> Greeter received: Hello world

pkill greeter_server
```

위에 사용한 명령중 이미 package 가 설치 되어 있는 경우는 생략 할 수 있다.

- gcc 및 make utility 는 설치 되어 있는것 으로 한다.
- 위의 설치로 <span style="color:blue">**C++, Python, Node.js, Ruby, PHP, Objective-C 및 C와 같은 모든 C 기반**  </span>gRPC 구현의 코드 예제를 포함 하여 설치가 완료 됩니다. 
- examples 디렉토리에서 원하는 언어와 관련된 예제 및 지침을 찾을 수 있습니다.
- 각 언어의 개발 도구(gcc, node, python,...)는 별도로 설치 하여야 한다.
- Go 와 Java 는 별도의 저장소에 설치 모듈이 존재 한다.



### 2. Python 에서 gRPC 사용하기

#### 2.1 시작 하기 전에

gRPC Python은 Python 2.7 또는 Python 3.4 이상에서 사용하도록 지원됩니다.

pip 버전 9.0.1 이상이 있는지 확인하십시오. 이 문서 에서는 Python 3.5.2 를 기준으로 작성 되어 있습니다. python version 확인은 `python --version` 또는 `python3 --version` 으로 확인 할 수 있습니다.

pip 가 설치되어 있지 않기 때문에 pip 를 설치 합니다.

```
sudo apt install -y python3-pip
sudo python3 -m pip install --upgrade pip
```



#### 2.2 Install gRPC

```
sudo python3 -m pip install grpcio
```



#### 2.3 Install gRPC tools

```
sudo python3 -m pip install grpcio-tools googleapis-common-protos
```



#### 2.4 run 

```
cd ~/grpc/examples/python/helloworld
python3 greeter_server.py

python3 greeter_client.py

output >> Greeter client received: Hello you
```

- 서버 및 client 는 ~/grpc/examples/cpp/helloworld 에 있는 greeter_server 및 greeter_client 를 이용하여 상호 연동 태스트를 수행 하여도 된다.



### 3. Node.js 에서 gRPC 사용하기

#### 3.1 시작 하기 전에

gRPC 는 Node.js 4.0.0 이상에서 사용하도록 지원됩니다. node.js 가 설치되어 있지 않으면 다음 명령을 이용하여 node.js 를 설치 합니다.

##### 3.1.1 Node.js 와 npm 설치

```
sudo apt install -y nodejs-legacy npm
```

 

##### 3.1.2 run test

```
cd ~/grpc/examples/node/dynamic_codegen

node greeter_client.js
```



error:

Error: Failed to load gRPC binary module because it was not installed for the current system
Expected directory: node-v46-linux-x64-glibc
Found: [node-v51-linux-x64-glibc.]
This problem can often be fixed by running "npm rebuild" on the current system
Original error: Cannot find module '/home/wskang/grpc/examples/node/node_modules/grpc/src/node/extension_binary/node-v46-linux-x64-glibc/grpc_node.node'
​    at Object.<anonymous> (/home/wskang/grpc/examples/node/node_modules/grpc/src/grpc_extension.js:53:17)



##### 3.1.3 Node.js 와 npm remove

```
sudo apt remove -y nodejs-legacy npm
```



##### 3.1.4 Node.js 소스를 이용한 설치

```
sudo curl -sL https://deb.nodesource.com/setup_8.x -o nodesource_setup.sh

sudo bash nodesource_setup.sh
```

- cdrom 관련 오류 발생, /etc/apt/sources.list 수정

- deb cdrom:[Ubuntu-Server 16.04.5 LTS _Xenial Xerus_ - Release amd64 (20180731)]/ xenial main restricted 를 주석 처리

```
sudo bash nodesource_setup.sh
```



##### 3.1.5 run 재 실행

```
cd ~/grpc/examples/node/dynamic_codegen

node greeter_client.js
```

- 오류 발생
```
Error: Failed to load gRPC binary module because it was not installed for the current system
Expected directory: node-v57-linux-x64-glibc
Found: [node-v51-linux-x64-glibc.]
This problem can often be fixed by running "npm rebuild" on the current system

```

다음 명령으로 재 빌드 후 실행

```
sudo npm rebuild

node greeter_client.js

-- 정상 실행됨
output >> Greeting: Hello world
```

- Node.js 에서 정상 테스트가 되긴 하였으나 Version 충돌이 발생하는 문제는 확인이 필요하고 재 설치 테스트를 할 필요가 있음.

node.js 에서 gRPC 지원은 grpc(C-based Client and Server ) 과 Pure JavaScript Client 버전 두가지가 있습니다.

Pure JavaScript Client 은 현재 불안전한 상태 에서 시험적으로 개발 되고 있으며, gRPC Server 는 만들 수 없습니다. 그리고 node.js도 최신 버전만 지원 하고 제약 사항이 있지만 차후 안정적인 Version 이 나오면 설치 및 사용은 현재 보다 쉬워질 것으로 보입니다.  Pure JavaScript Client 버전 테스트는 skip



### 4. Ruby 에서 gRPC 사용하기

#### 4.1 시작 하기 전에

gRPC 는 ruby 2.0 이상에서 사용하도록 지원됩니다.



#### 4.2 Install gRPC

```
gem install grpc
```



#### 4.3 Install gRPC tools

```
gem install grpc-tools
```



#### 4.4 run 

```
cd ~/grpc/examples/ruby

ruby greeter_server.rb

ruby greeter_client.rb

output >> "Greeting: Hello world"
```



### 5. PHP 에서 gRPC 사용하기

#### 5.1 시작 하기 전에

gRPC PHP는 php 5.5 이상 또는 php 7.0 이상 에서 사용하도록 지원됩니다. 그리고 pecl, composer, phpunit 이 설치되어 있어야 합니다.



#### 5.2 Install PHP and PECL on Ubuntu/Debian

```
sudo apt-get install -y php php-dev php-pear phpunit
```



#### 5.3 Install Composer

```
curl -sS https://getcomposer.org/installer | php

sudo mv composer.phar /usr/local/bin/composer
```



#### 5.4 Install the gRPC PHP extension

```
cd ~

sudo pecl install grpc

# 한참 컴파일을 한다. (5.4 는 이미 1 에서 설치가 되어 있기 때문에 생략 가능 한 것으로 보임, 다시 확인 필요)
```



#### 5.5 Build and install gRPC PHP extension

##### 5.5.1 Compile the gRPC PHP extension

```
cd grpc/src/php/ext/grpc
phpize
./configure
make
sudo make install
```



##### 5.5.2 Update php.ini

```
ls -al /etc/php/7.0/cli/php.ini
sudo vi /etc/php/7.0/cli/php.ini

다음 행 추가

extension=grpc.so


# php.ini 파일의 위치는 각 플랫폼(OS, php version) 에 따라 다른 위치에 존재 한다.
etc/php5/cli/php.ini, /etc/php5/apache2/php.ini, or /usr/local/etc/php/5.6/php.ini
```



##### 5.5.3 Add the gRPC PHP library as a Composer dependency

project 의 composer.json 파일에 다음을 추가

```json
"require": {
    "grpc/grpc": "v1.7.0"
  }
```



다음은 실제 ~/grpc/examples/php/composer.json 파일 이다

```json
{
  "name": "grpc/grpc-demo",
  "description": "gRPC example for PHP",
  "require": {
    "grpc/grpc": "^v1.3.0",
    "google/protobuf": "^v3.3.0"
  },
  "autoload": {
    "psr-4": {
      "": "route_guide/"
    }
  }
}
```



##### 5.5.4 run

사전에 다른 언어로 개발된 `greeter_server` 가 기동 되어 있어야 테스트가 된다.

```
cd ~/grpc/examples/php

./greeter_proto_gen.sh

composer install

run_greeter_client.sh
```

- 위의 run_greeter_client.sh 를 실행 시키면 `PHP Warning:  Module 'grpc' already loaded in Unknown on line 0` message 가 보이는데 무시 하던지, `run_greeter_client.sh` 파일을 열어서 `-d extension=grpc.so` 부분을 제거 하면 Warning 이 보이지 않는다.
- Warning 의 이유는 php.ini 에서 이미 `extension=grpc.so` 이 추가 되어 있는데 중복 지정 하여 발생 하는 Message 이다.



### 6. Objective-C 에서 gRPC 사용하기

**오브젝티브-C**([영어](https://ko.wikipedia.org/wiki/%EC%98%81%EC%96%B4): Objective-C)는 [C 프로그래밍 언어](https://ko.wikipedia.org/wiki/C_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4))에 [스몰토크](https://ko.wikipedia.org/wiki/%EC%8A%A4%EB%AA%B0%ED%86%A0%ED%81%AC) 스타일의 메시지 구문을 추가한 [객체 지향](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5) 언어이다. 현재, 이 언어는 [애플](https://ko.wikipedia.org/wiki/%EC%95%A0%ED%94%8C_(%EA%B8%B0%EC%97%85))의 [매킨토시](https://ko.wikipedia.org/wiki/%EB%A7%A4%ED%82%A8%ED%86%A0%EC%8B%9C)의 [운영 체제](https://ko.wikipedia.org/wiki/%EC%9A%B4%EC%98%81_%EC%B2%B4%EC%A0%9C)인 [OS X](https://ko.wikipedia.org/wiki/OS_X)과 [아이폰](https://ko.wikipedia.org/wiki/%EC%95%84%EC%9D%B4%ED%8F%B0)의 [운영 체제](https://ko.wikipedia.org/wiki/%EC%9A%B4%EC%98%81_%EC%B2%B4%EC%A0%9C)인 [iOS](https://ko.wikipedia.org/wiki/IOS)에서 사용되고 있다. 오브젝티브-C는 애플의 [코코아](https://ko.wikipedia.org/wiki/%EC%BD%94%EC%BD%94%EC%95%84_(API))를 사용하기 위한 기본 언어이며, 원래는 [넥스트](https://ko.wikipedia.org/wiki/%EB%84%A5%EC%8A%A4%ED%8A%B8_%EC%82%AC)의 NeXTSTEP [운영 체제](https://ko.wikipedia.org/wiki/%EC%9A%B4%EC%98%81_%EC%B2%B4%EC%A0%9C)에서 주 언어였다. 일반적인(Generic) 오브젝티브-C는 앞에서 언급한 라이브러리를 사용하지 않는다.(출처: 위키백과)



내가 매킨토시 운영체제와 아이폰 운영체제를 가지고 있지 않은 상황 에서 Objective-C 를 테스트 하는 방법이 없어서 Ubuntu 에 Objective-C 를 설치하여 gRPC 를 테스트 하기로 하였다.

- gRPC quickstart 에는 iOS platform 에 설치하는 Guide 만 있어서 더 이상 테스트를 할 수 있는 환경이 되지 않는다. 대신 Ubuntu 에 objective-c 컴파일 환경 구축 및 테스트 하는것 으로 아쉬움을 달래본다.



#### 6.1  Ubuntu 에 GNU objective-c 컴파일러 설치

##### 6.1.1 gobjc 설치

```
sudo apt-get install -y gobjc 
```



##### 6.1.2 Verify the version

```
gcc --version
```



##### 6.1.3 GNUStep 개발 라이브러리 설치

```
sudo apt-get install -y gnustep-devel
```



##### 6.1.4 HelloWorld 를 이용한 컴파일 환경 테스트

`HelloWorld.m`

```objective-c
#import <Foundation/Foundation.h>

int main (int argc, const char * argv[]) {
    //AutoreleasePool을 할당하고 초기화
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];  

    // helloWorldStr에 "Hello World!" 대입
    NSString *helloWorldStr = [NSString stringWithString:@"Hello World!"]; 
    
    NSLog(@"%@",helloWorldStr); // 출력
    [pool drain];  //AutoreleasePool Drain
    return 0;
}

// Whence : 위키백과
```

```objective-c
// 최근의 문법 변경으로 쉬워짐
#import <Foundation/Foundation.h>

int main (int argc, const char * argv[]) {
    @AutoreleasePool //AutoreleasePool을 할당하고 초기화

    NSLog(@"  Hello World");
    return 0;
}
```



`컴파일`

```
gcc HelloWorld.m `gnustep-config --objc-flags` `gnustep-config --base-libs` -o HelloWorld
```

`실행 및 결과`

```
./HelloWorld

-- 결과 (시간은 차이가 남-17 시간 이전 으로 표시)
-- TZ=Asia/Seoul 로 설정 되어 있는데 현재 원인은 확인 하지 못함.
2018-11-21 23:12:36.066 HelloWorld[111075] Hello World!
```



아래는 내일 설치 및 테스트 예정

### 7. Go 에서 gRPC 사용하기
link: https://github.com/grpc/grpc-go
https://developer.ibm.com/kr/cloud/blockchain/2017/03/26/grpc_overview_01/



### 8. Java 에서 gRPC 사용하기
link: https://github.com/grpc/grpc-java



기본적인 설치 및 테스트가 완료 되면 암호화 Server 를 gRPC 를 이용하여 구현 할 예정 입니다.

