# C parson (JSON 파일을 이용해서 데이터 받아오기)

Parson은 C로 작성된 경량 json 라이브러리입니다.

git 저장소 위치: https://github.com/kgabis/parson 



## 특징

- 경량(파일 2개만)
- 간단한 API
- 점 표기법으로 json 값 처리(대부분의 OO 언어의 C 구조체 또는 객체와 유사(예: 'objectA.objectB.value'))
- C89 호환
- 테스트 스위트



## 설치

``` cmd
git clone https://github.com/kgabis/parson.git
```

parson.h와 parson.c를 소스 코드 트리에 복사하세요.

테스트를 컴파일하고 실행하려면 make test를 실행하세요.



## 예제

`example.json`

``` json
{
    "Num": 1,
    "Greeting": "Hello",
    "DNum": 1.5,
    "array": [1,2,3,4,5],
    "boolean": true
}
```

``` c
#include <stdio.h>
#include "parson.h"
 
int main()
{
	JSON_Value *rootValue;
	JSON_Object *rootObject;
 
	rootValue = json_parse_file("example.json");    
	rootObject = json_value_get_object(rootValue);
 
	printf("Greeting: %s\n", json_object_get_string(rootObject, "Greeting"));
	printf("Num: %d\n", (int)json_object_get_number(rootObject, "Num"));
	printf("DNum: %.1lf\n", json_object_get_number(rootObject, "DNum"));
 
	printf("array: ");
	JSON_Array *array = json_object_get_array(rootObject, "array");
	for (int i = 0; i < json_array_get_count(array); i++)    
	{
		printf("%d ", (int)json_array_get_number(array, i));
	}
 
	printf("\nboolean: %d\n", json_object_get_boolean(rootObject, "boolean"));
 
	json_value_free(rootValue);
	return 0;
}
```



