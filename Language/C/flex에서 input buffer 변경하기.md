# flex에서 input buffer 변경하기

출처: https://kukuta.tistory.com/137

일반적으로 우리가 알고 있는 flex는 파일로 부터 스캔 내용을 읽는다. flex는 기본 stdin으로 설정되어 있는 yyin이라는 전역 파일 포인터 변수를 제공하고 있으며, 우리의 필요에 따라 yyin에 다른 파일 포인터를 맵핑함으로써 사용자가 지정하는 파일로 부터 입력을 받을 수 있다. 하지만 프로그램 실행시 넘겨지는 실행 파라메터나 문자열의 경우 파일이 아닌 메모리 상에 위치하고 있는 바이트 버퍼이므로, 이런 경우에는 yyin 같은 파일 포인터를 이용하여 스캔하는 것이 불가능하다.

이런 경우를 위해 flex에서는 입력 스트림(버퍼)을 변경할 수 있는 인터페이스를 제공한다.

— Function: void **yy_switch_to_buffer** ( YY_BUFFER_STATE new_buffer )

위 함수는 현재 스캐너가 바라보고 있는 입력 버퍼를 newbuffer로 변경 해 준다. 인자로 넘어가는 YY_BUFFER_STATE는 yy_buffer_state구조체의 포인터로써 입력 버퍼에 대한 이런저런 잡다한 정보들을 담고 있으며 yy_create_buffer(), yy_new_buffer(), yy_scan_string(), yy_scan_bytes(), yy_scan_buffer() 함수들을 통해 얻을 수 있다. 이 중, 우리가 오늘 주목해서 살펴봐야 할 함수는 yy_scan_string() 다.



— Function: YY_BUFFER_STATE **yy_scan_string** ( const char *str )

인자로써 NULL로 종료되는 문자열을 받으며, 문자열에 대한 YY_BUFFER_STATE 를 리턴한다.

이해를 돕기 위해 예제 코드를 살펴 보자 :

``` c
int main(int argc, char** argv) {
    for(int i=1; i<argc; i++) {
        // YY_BUFFER_STATE stack = YY_CURRENT_BUFFER;
        yy_delete_buffer(YY_CURRENT_BUFFER);
        yy_switch_to_buffer(yy_scan_string(argv[i]));
        yylex();
        yy_delete_buffer(YY_CURRENT_BUFFER);
        // yy_switch_to_buffer(stack);
        yy_switch_to_buffer(yy_create_buffer( yyin, YY_BUF_SIZE ));
    }
}
```

위의 예제는 현재 입력 버퍼와 읽어야 하는 문자열 버퍼를 치환했다가, 사용이 끝나고 난 후 원래대로 되 돌리는 코드다.

새롭게 주목해야 할 것은, **YY_CURRENT_BUFFER** 매크로와 **yy_delete_buffer()**함수다. YY_CURRENT_BUFFER 매크로는 현재 스캐너가 읽기 위해 바라보고 있는 버퍼의 YY_BUFFER_STATE를 리턴하며, yy_delete_buffer() 함수는 YY_BUFFER_STATE가 가리키는 yy_buffer_state를 삭제하는 역할을 한다. 

— Function: void **yy_delete_buffer** ( YY_BUFFER_STATE buffer )

yy_scan_string() 함수는 새로운 버퍼를 생성함으로, yy_scan_string() 사용 후 삭제를 하지 않는다면 메모리 누수가 발생한다.

예제 코드 중에 중간선을 그어 삭제 표시가 된 부분이 있다. 처음의 코드엔 붉게 표시된 라인이 없고 삭제된 두 라인이 있었으나, 원인은 모르겠지만 이전 버퍼로 switch가 정상적으로 되지 않아 다시 파일 포인터로 스캔 포인터를 변경 하려고 할 때 오류가 발생 했다.

그래서 기존 버퍼를 얻어와서 저장하고 있다가 swap 하는 방식 대신 기존 버퍼를 삭제 -> 새로운 버퍼 생성과 같은 방식으로 대신 했다. 성능면에서는 어떨지 모르겠으나 정상적으로 동작하는 것은 확실 하다.

지금까지 간단하게나마 flex에서 읽어들이는 버퍼를 파일에서 메모리 바이트 버퍼로 변경하는 방법에 대해서 알아 보았다. 이 외에도 다른 input buffer가 필요한 경우가 있다면, http://matrix.umcs.lublin.pl/dokumentacja/_usr_share_doc_flex-doc_html/Multiple-Input-Buffers.html 를 참고 하도록 하자.