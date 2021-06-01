## How to make a PHP extension

참조: [PHP at the Core: A Hacker's Guide](http://php.net/manual/en/internals2.php#internals2)

참조: [stackoverflow.com]( https://stackoverflow.com/questions/3632160/how-to-make-a-php-extension)



### 1. 단계별 따라 하기

단계별 따라하기는 stackoverflow 에 있는 예제를 그대로 실행 해 보는 과정으로 Linux Ubuntu 16.04 에서 실행 해 보았다. 

#### 1.1 Setup Build Environment / Requirements

PHP extension 은 컴파일 된 C 코드입니다. 따라서 컴파일러와 PHP  개발자 모듈이 설치되어 있어야 합니다.

```
sudo apt-get install build-essential php7.0 php7.0-dev
```

내 경우는 이미 `PHP 7.0.32` 가 설치되어 있는 관계로 이 과정은 skip.



#### 1.2 Config

확장 기능 기본 구성 파일 작성

`config.m4`

```
PHP_ARG_ENABLE(php_helloworld, Whether to enable the HelloWorldPHP extension, [ --enable-helloworld-php Enable HelloWorldPHP])

if test "$PHP_HELLOWORLD" != "no"; then
    PHP_NEW_EXTENSION(php_helloworld, php_helloworld.c, $ext_shared)
fi
```

세부 설명은 차 후 작성 예정



#### 1.3 Source code 작성

`php_helloworld.h`

``` c
// we define Module constants
#define PHP_HELLOWORLD_EXTNAME "php_helloworld"
#define PHP_HELLOWORLD_VERSION "0.0.1"

// then we declare the function to be exported
PHP_FUNCTION(helloworld_php);
```



`php_helloworld.c`

```c
// include the PHP API itself
#include <php.h>
// then include the header of your extension
#include "php_helloworld.h"

// register our function to the PHP API 
// so that PHP knows, which functions are in this module
zend_function_entry helloworld_php_functions[] = {
    PHP_FE(helloworld_php, NULL)
    {NULL, NULL, NULL}
};

// some pieces of information about our module
zend_module_entry helloworld_php_module_entry = {
    STANDARD_MODULE_HEADER,
    PHP_HELLOWORLD_EXTNAME,
    helloworld_php_functions,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    PHP_HELLOWORLD_VERSION,
    STANDARD_MODULE_PROPERTIES
};

// use a macro to output additional C code, to make ext dynamically loadable
ZEND_GET_MODULE(helloworld_php)

// Finally, we implement our "Hello World" function
// this function will be made available to PHP
// and prints to PHP stdout using printf
PHP_FUNCTION(helloworld_php) {
    php_printf("Hello World! (from our extension)\n");
}
```



 #### 1.4  Build

먼저 PHP 확장을위한 빌드 환경을 준비

##### 1.4.1 phpize 실행

```
phpize
```

- phpize 는 위에서 작성한 `config.m4` 를 참조 하여 작업 한다.

`실행 결과`

```
Configuring for:
PHP Api Version:         20151012
Zend Module Api No:      20151012
Zend Extension Api No:   320151012
```



`phpize 실행 전 파일들`

```
config.m4
php_helloworld.h
php_helloworld.c
```



`phpize 실행 후 파일들`

```
acinclude.m4
aclocal.m4
autom4te.cache/
build/
config.guess*
config.h.in
config.m4
config.sub*
configure*
configure.in
install-sh
ltmain.sh
Makefile.global
missing
mkinstalldirs
php_helloworld.c
php_helloworld.h
run-tests.php
```



##### 1.4.2 빌드를 구성하고 확장을 활성화

```
./configure --enable-php-helloworld
```

- 컴파일 환경을 Check 하고 Makefile 을 만든다.

이때 추가로 생성되는 파일은 다음과 같다.

```
config.nice
Makefile.fragments
include/
Makefile.objects
Makefile
modules/
.deps
config.status
config.h
libtool
config.log
```



##### 1.4.3 Build and Install

```
make

sudo make install
```

- make 가 정상적으로 실행 되면 ./modules/에 `php_helloworld.so` 파일이 생성 된다.
- sudo make install 이 정상 수행 되면 `/usr/lib/php/20151012/` 에  `php_helloworld.so`  파일이 복사 된다. `/usr/lib/php/20151012/` 는 설치된 PHP Version 에 따라 다르게 표시 된다.




#### 1.5 실행

PHP Extension 을 테스트 하려면 helloworld_php.so Shared Object 파일을 로드 하고 helloworld_php () 함수를 실행 한다.

```
php -d extension=php_helloworld.so -r 'helloworld_php();'
```

`실행 결과`

```
Hello World! (from our extension)
```

위 명령 중 `-d extension=php_helloworld.so` 는 php.ini 에 `extension=php_helloworld.so` 이 추가 되어 있는 경우는 생략 한다.



#### 1.6 Windows 인 경우 Build

windows 인 경우는 (1.2 의Config 와 1.4.3 의 build 부분) 다음과 같이 수정 한다.

##### 1.6.1 (1.2 의 Config 수정)

`config.w32`

```
ARG_ENABLE("helloworld", "helloworld support", "yes");

if (PHP_HELLOWORLD == "yes") {
    EXTENSION("helloworld", "php_helloworld.c");
}
```



#### 1.6.2 (1.4.3 의 build 부분 수정)

make 대신 Visual Studio 의 make utility 인 `nmake` 를 사용 한다.



### 2. Parameter 를 주고 받는 함수 작성 예제.

실제로 PHP Extension 을 사용 할 때 는  Ext 함수 호출 시 Parameter 를 전달 하고, 해당 함수의 실행 결과를 return 받는 과정이 일반적 이다. 이번 장 에서는 Parameter 를 주고 결과를 받는 Extension 함수를 작성 해 본다.

작성 할 함수는 다음 표와 같다.

| 함수명   | parameter | 리턴값                       |
| -------- | --------- | ---------------------------- |
| sayHello | username  | 문자열 ('Hello ' + username) |
| addIntNum | a, b  | 숫자 a + b |



작성중: /home/wskang/WSKANG/PHP_EXTENSION

