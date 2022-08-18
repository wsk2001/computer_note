# libavl

바이너리 트리 조작을 위한 라이브러리.



### avl_create

``` c
struct avl_table *
avl_create (avl_comparison_func *compare, void *param,
            struct libavl_allocator *allocator, BIN *pstEncKey, BIN *pstSignKey, BIN *pstPubKey )
```

비교 함수 |compare|를 사용하여 새 테이블을 만들고 반환합니다. 매개변수 |param| 사용 및 메모리 할당자 |할당자|.
반환 |NULL| 메모리 할당이 실패한 경우.



