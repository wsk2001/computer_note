### jsoncpp 설치 및 사용법



#### 1. jsoncpp 설치

##### 1.1 파일 받기 위치

<https://github.com/open-source-parsers/jsoncpp>

에서 `Clone or download` 버튼을 눌러 zip 파일을 다운 받는다.

download 된 파일의 이름은 'jsoncpp-master.zip'  이다.



##### 1.2 압축 해제 

압축을 해제 하면 '.\jsoncpp-master\jsoncpp-master' directory 가 생성 되고 그 아래 압축된 파일들이 위치 하게 된다.  그럼 

```
cd .\jsoncpp-master\jsoncpp-master
```

명령을 수행 하여 작업 할 directory 로 이동 한다.



##### 1.3 source 파일 및 header 파일 생성

작업 할 경로로 이동 했으면 다음 명령으로 jsoncpp 파일 을 생성 한다.

```
python amalgamate.py
```



다음은 `작업 결과` 이다.

```
Amalgamating header...
Writing amalgamated header to 'dist\\json/json.h'
Amalgamating forward header...
Writing amalgamated forward header to 'dist\\json/json-forwards.h'
Amalgamating source...
Writing amalgamated source to 'dist/jsoncpp.cpp'
Source successfully amalgamated
```

현재 작업 경로 아래 "dist/json"  directory 가 생성 되고 그아래 `json.h`,  `json-forwards.h` 파일이 생성 된다.

그리고 `dist/` 에는 `jsoncpp.cpp` 파일이 생성 된다.



##### 1.4  jsoncpp 사용

json 기능이 필요한 project 에 json.h, json.cpp 를 포함 시키고,  json.h 를 include 한 소스 파일에서 cppjson 에서 지원하는 함수를 사용 하면 된다.



##### 1.5 테스트를 위한 공통 함수

테스트를 위해서 json 을 파일에 쓰고, 읽는 테스트 함수들. 

``` hpp
// util.hpp

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>

using namespace std;

bool WriteToFile(const char* filename, const char* buffer, int len)
{
    FILE* fp = NULL;

    fp = fopen(filename, "wb");
    
    if (fp == NULL)
        return false;
 
    size_t fileSize = fwrite(buffer, 1, len, fp);
 
    fclose(fp);
 
    return true;
}

bool ReadFromFile(const char* filename, char* buffer, int len)
{
    FILE* fp = NULL;
    
	fp = fopen( filename, "rb");
 
    if (fp == NULL) 
        return false;
 
    size_t fileSize = fread(buffer, 1, len, fp);
 
    fclose(fp);
 
    return true;
}
```





##### 1.6 json 파일 생성 예제.

```cpp
// write_json.cpp

#include <iostream>
#include "json/json.h"
#include "util.hpp"

std::string str;

int main(int argc, char** argv)
{
	Json::Value root;
	root["BaseDir"] = "/home/user";
	root["BinDir"] = "/home/project/bin";
	root["ConfigDir"] = "/home/project/";

	Json::Value DBMS;
	Json::Value run_db1;
	run_db1["host"]     = "run_db1";
	run_db1["ip"]       = "192.168.1.11";
	run_db1["port"]     = 1521;
	run_db1["user"]     = "scott";
	run_db1["password"] = "tiger";
	run_db1["sid"]      = "ORCL1";
	run_db1["maker"]    = "oracle";
	run_db1["version"]  = "12c";

	Json::Value run_db2;
	run_db2["host"]     = "run_db2";
	run_db2["ip"]       = "192.168.1.21";
	run_db2["port"]     = 1521;
	run_db2["user"]     = "scott";
	run_db2["password"] = "tiger";
	run_db2["sid"]      = "ORCL2";
	run_db2["maker"]    = "oracle";
	run_db2["version"]  = "12c";

	Json::Value dev_db;
	dev_db["host"]     = "dev_db";
	dev_db["ip"]       = "192.168.2.11";
	dev_db["port"]     = 1521;
	dev_db["user"]     = "scott";
	dev_db["password"] = "tiger";
	dev_db["sid"]      = "ORDEV";
	dev_db["maker"]    = "oracle";
	dev_db["version"]  = "12c";
	
	DBMS.append(run_db1);
	DBMS.append(run_db2);
	DBMS.append(dev_db);
	
	root["DBMS"] = DBMS;
	
	// Json::StyledWriter writer;
	// str = writer.write(root);	
	// warning: ‘StyledWriter’ is deprecated: Use StreamWriterBuilder instead
	// StyledWriter 는 쓰지 말라고 합니다. 대신 아래 Class 로 대체하여 사용.
	
	Json::StreamWriterBuilder builder;

	// 4칸 들여 쓰기
	// 들여 쓰기 없이 한줄에 표현 하려면 builder["indentation"] = ""; 로 하면 된다.
	builder["indentation"] = "    ";
	
	str = Json::writeString(builder, root);
	WriteToFile("sysconf.json", str.c_str(), str.length() );
	
	std::cout << str << std::endl << std::endl;
}
```



생성된 json

```json
{
    "BaseDir" : "/home/user",
    "BinDir" : "/home/project/bin",
    "ConfigDir" : "/home/project/",
    "DBMS" : 
    [
        {
            "host" : "run_db1",
            "ip" : "192.168.1.11",
            "maker" : "oracle",
            "password" : "tiger",
            "port" : 1521,
            "sid" : "ORCL1",
            "user" : "scott",
            "version" : "12c"
        },
        {
            "host" : "run_db2",
            "ip" : "192.168.1.21",
            "maker" : "oracle",
            "password" : "tiger",
            "port" : 1521,
            "sid" : "ORCL2",
            "user" : "scott",
            "version" : "12c"
        },
        {
            "host" : "dev_db",
            "ip" : "192.168.2.11",
            "maker" : "oracle",
            "password" : "tiger",
            "port" : 1521,
            "sid" : "ORDEV",
            "user" : "scott",
            "version" : "12c"
        }
    ]
}
```





##### 1.7 json 파일 읽는 예제

```cpp
#include <iostream>
#include <string>
#include <cstdlib>
#include "json/json.h"
#include "util.hpp"

using namespace std;

int main(int argc, char** argv)
{
	Json::CharReaderBuilder  jsonReader;
	
	Json::Value root;
	bool parsingRet = false;
	char Buff[ 4096 ];
	std::stringstream  str;
	std::string errs;
	
	memset(Buff, 0x00, sizeof(Buff) );
	
	if( !ReadFromFile("sysconf.json", Buff, sizeof(Buff) -1 ) )
	{
		fprintf( stderr, "Can not open file: %s\n", "sysconf.json" );
		fflush(stderr );
		exit(-1);
	}
	
	str = std::stringstream (Buff);
	
	parsingRet = Json::parseFromStream(jsonReader, str, &root, &errs);
	if (!parsingRet)
	{
		std::cout << "Failed to parse Json : sysconf.json" << endl;
		exit(-1);
	}

	std::cout << "BaseDir   : " << root["BaseDir"].asString().c_str() << endl;
	std::cout << "BinDir    : " << root["BinDir"].asString().c_str() << endl;
	std::cout << "ConfigDir : " << root["ConfigDir"].asString().c_str() << endl;
	
	std::cout << endl << endl;

	Json::Value dbms = root["DBMS"];
	for (auto it = dbms.begin(); it != dbms.end(); it++)
	{
		if (it->isObject())
		{
			std::cout << "host     : " << (*it)["host"].asString().c_str() << endl;
			std::cout << "ip       : " << (*it)["ip"].asString() << endl;
			std::cout << "port     : " << (*it)["port"].asInt() << endl;
			std::cout << "user     : " << (*it)["user"].asString() << endl;
			std::cout << "password : " << (*it)["password"].asString() << endl;
			std::cout << "maker    : " << (*it)["maker"].asString() << endl;
			std::cout << "version  : " << (*it)["version"].asString() << endl;
			std::cout << endl;	
		}
	}
	// (*it)["version"] 이렇게 호출 하면 "" 사이에 값이 표시된다. (Ex: version  : "12c" )
}
```



##### 1.8 참조 & 주의

JsonCpp가 Windows에서 동적 라이브러리로 빌드 된 경우 프로젝트에서 매크로 JSON_DLL을 정의해야합니다.

deprecated 된 기능이 많이 있어 compile 시 warning message 가 나와서 새로 정리함.





