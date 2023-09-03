# libcurl 사용법.

### 1. Description

libcurl 은 callback 방식으로 작동하는 라이브러리이다. callback 함수에서는 리턴된 data 를 메모리 또는 파일로 저장할 수 있다.

callback 함수를 지정하지 않으면 stdout 으로 리턴 데이타가 출력된다.



### 2. Installation

`CentOS 기준`

``` bash
yum install curl-devel
```



`Ubuntu 기준`

``` bash
sudo apt-get install libcurl4-openssl-dev
```



### 3. Usage

`GET Example`

``` c
#include <stdio.h>
#include <curl/curl.h>

int main() {

    char *url_post = "https://google.com/";
    char *url_get = "https://google.com/";

    CURL *curl;
    CURLcode res;
    curl = curl_easy_init();

    struct curl_slist *list = NULL;

    // =========================================
    // METHOD : GET
    if(curl) {

        // URL
        curl_easy_setopt(curl, CURLOPT_URL, url_get);

        // METHOD
        // DEFAULT : GET

        // EXECUTE
        res = curl_easy_perform(curl);

        curl_easy_cleanup(curl);

        // callback 함수가 없으면 stdout 으로 출력된다.
    }
}
```



`POST Example`

``` c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

int main() {

    char *url_post = "https://google.com/";
    char *url_get = "https://google.com/";

    CURL *curl;
    CURLcode res;
    curl = curl_easy_init();

    struct curl_slist *list = NULL;

    curl = curl_easy_init();

    // =========================================
    // METHOD : POST
    if(curl) {

        list = NULL;

        // URL
        curl_easy_setopt(curl, CURLOPT_URL, url_post);

        // METHOD
        curl_easy_setopt(curl, CURLOPT_POST, 1L);

        // HEADERS
        list = curl_slist_append(list, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, list);

        // SSL
        curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 1L);
        curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 1L);

        // DATA
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, "data");

        // EXECUTE
        res = curl_easy_perform(curl);

        curl_easy_cleanup(curl);

        // callback 함수가 없으면 stdout 으로 출력된다.
    }
}
```



`POST & CALLBACK Example`

``` c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

struct MemoryStruct {
    char *memory;
    size_t size;
};

size_t write_to_memory_callback(void *buffer, size_t size, size_t nmemb, void *userp) {

    size_t realsize = size * nmemb;
    struct MemoryStruct *mem = (struct MemoryStruct *) userp;
    char *ptr = (char *) realloc(mem->memory, mem->size + realsize + 1);

    if(!ptr) {
        printf("not enough memory (realloc returned NULL)\n");
        return 0;
    }

    mem->memory = ptr;
    memcpy(&(mem->memory[mem->size]), buffer, realsize);
    mem->size += realsize;
    mem->memory[mem->size] = 0;

    return realsize;
}

int main() {

    char *url_post = "https://google.com/";
    char *url_get = "https://google.com/";

    CURL *curl;
    CURLcode res;
    curl = curl_easy_init();

    struct curl_slist *list = NULL;

    // =========================================
    // METHOD : POST & CALLBACK
    if(curl) {

        list = NULL;

        struct MemoryStruct chunk;
        chunk.memory = (char *) malloc(1);
        chunk.size = 0;

        // URL
        curl_easy_setopt(curl, CURLOPT_URL, url_post);

        // METHOD
        curl_easy_setopt(curl, CURLOPT_POST, 1L);

        // HEADERS
        list = curl_slist_append(list, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, list);

        // SSL
        curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 1L);
        curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 1L);

        // DATA
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, "data");

        // CALLBACK
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_memory_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *) &chunk);

        // EXECUTE
        res = curl_easy_perform(curl);

        curl_easy_cleanup(curl);

        if (chunk.size > 0) {
            printf("111111111 : %s", chunk.memory);
        }
        free(chunk.memory);
    }
}
```



` 컴파일 및 실행`

``` bash
gcc test.c -lcurl
./a.out
```





### curl_easy_perform() 함수에서 60 오류가 발생 하는 원인 및 해결 방법.

CURL 오류 코드 60은 SSL 인증서 관련 문제를 나타냅니다. 서버의 SSL 인증서가 유효하지 않거나 신뢰할 수 없는 인증기관에 의해 발행된 경우 발생할 수 있습니다. 이를 해결하기 위해 다음 단계를 따를 수 있습니다:

1. **날짜 및 시간 설정**: 컴퓨터의 날짜 및 시간 설정을 확인하고 올바르게 설정되어 있는지 확인합니다.
2. **인증서 검증 옵션 변경**: curl_easy_perform() 호출 전에 SSL 검증을 무시하도록 옵션을 변경할 수 있습니다. 하지만 이는 보안상 취약점을 유발할 수 있으므로 주의가 필요합니다.
3. **신뢰할 수 있는 인증서 사용**: 서버에서 신뢰할 수 있는 인증서를 사용하도록 설정하거나 업데이트합니다.
4. **CA 인증 기관 추가**: 서버의 인증서가 특정 CA(Certificate Authority)에 의해 발행되었는지 확인하고, 필요한 경우 해당 CA의 인증서를 시스템에 추가합니다.

#### 인증서 검증 옵션 변경 방법

``` C
CURL* curl = curl_easy_init();

// set timeout 
curl_easy_setopt(curl, CURLOPT_TIMEOUT, 300);
	
// SSL 검증 무시
curl_easy_setopt(curl,CURLOPT_SSL_VERIFYPEER,0);
curl_easy_setopt(curl,CURLOPT_SSL_VERIFYHOST,0);

// 이 다음에 코드 작성.
```



