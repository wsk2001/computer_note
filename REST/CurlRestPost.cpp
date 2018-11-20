#include <iostream>
#include <string>
#include <curl/curl.h>
#include <string.h>

using namespace std;

/**
 * @bref write_to_string url 요청 후 응답 Data 를 memory 에 저장 한다.
 *
 * @param	ptr		[in]	응답 data 의 pointer
 * @param	size	[in]	응답 data 의 size
 * @param	count	[in]	응답 data 의 count
 * @param	stream	[out]	응답 data 를 저장 할 memory 주소
 *
 * @return	응답 data 의 길이
 */
size_t write_to_string(void * ptr, size_t size, size_t count, void * stream)
{
    ((string *)stream)->append((char *)ptr, 0, size * count);
    return size * count;
}

/**
 * @bref http_get	request for rest api (GET method)
 *
 * @param	url		[in]	request url address
 * @param	str		[out]	store variable for response data
 * @return	if success is true, else false
 */
bool http_get(char * url, string & str)
{
    CURL * curl;
    CURLcode res;

    curl = curl_easy_init();

    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_URL, url);

        res = curl_easy_perform(curl);

        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_string);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &str);

        curl_easy_cleanup(curl);
        return true;
    }
    else
    {
        return false;
    }
}

/**
 * @bref http_post rest api 를 통하여 POST 요청을 한다.
 *
 * @param	url			[in]	요청 url 주소
 * @param	jsonData	[in]	json 형태의 문자열 data
 * @param	str			[out]	결과 값을 저장 할 변수의 주소
 * @return	성공시 true, 실패시 false
 */
bool http_post(char * url, const char * jsonData, string & str)
{
    CURL	*	curl;
    CURLcode	res;
    struct curl_slist * headers = NULL;

    curl = curl_easy_init();

    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonData);
        headers = curl_slist_append(headers, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        res = curl_easy_perform(curl);

        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_string);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &str);

        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
        return true;
    }
    else
    {
        return false;
    }
}

int main(int argc, char * argv[])
{
    string str;

    const char * jsonPlain  = "{\"Alias\" : \"normal\", \"Plain\" : \"1234567890123\"}";
    const char * jsonCipher = "{\"Alias\" : \"normal\", \"Cipher\" : \"AAF46P2Fcv1zhJOe++Ytv/VY\"}";

    if (http_post((char *) "127.0.0.1:8080/encrypt", jsonPlain, str))
    {
        cout << str << endl;
    }

    if (http_post((char *) "127.0.0.1:8080/decrypt", jsonCipher, str))
    {
        cout << str << endl;
    }

    return 0;
}

/*
g++ -o CurlRestPost CurlRestPost.cpp -IC:\Tools\CURL\include -LC:\Tools\CURL\lib -lcurl
*/
