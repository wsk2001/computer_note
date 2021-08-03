# GLib Doubly-Linked Lists (GList)

출처: https://developer.gimp.org/api/2.0/glib/glib-Doubly-Linked-Lists.html

wonsool: 예제는 문서의 끝쪽에 있음.



## Doubly-Linked Lists

Doubly-Linked Lists —  데이터에 대한 포인터 또는 정수 값을 포함하는 linked list, 양방향으로 list 를 반복할 수 있는 기능



## Synopsis

``` c

#include <glib.h>

                    GList;

GList*              g_list_append                       (GList *list,
                                                         gpointer data);
GList*              g_list_prepend                      (GList *list,
                                                         gpointer data);
GList*              g_list_insert                       (GList *list,
                                                         gpointer data,
                                                         gint position);
GList*              g_list_insert_before                (GList *list,
                                                         GList *sibling,
                                                         gpointer data);
GList*              g_list_insert_sorted                (GList *list,
                                                         gpointer data,
                                                         GCompareFunc func);
GList*              g_list_remove                       (GList *list,
                                                         gconstpointer data);
GList*              g_list_remove_link                  (GList *list,
                                                         GList *llink);
GList*              g_list_delete_link                  (GList *list,
                                                         GList *link_);
GList*              g_list_remove_all                   (GList *list,
                                                         gconstpointer data);
void                g_list_free                         (GList *list);

GList*              g_list_alloc                        (void);
void                g_list_free_1                       (GList *list);
#define             g_list_free1

guint               g_list_length                       (GList *list);
GList*              g_list_copy                         (GList *list);
GList*              g_list_reverse                      (GList *list);
GList*              g_list_sort                         (GList *list,
                                                         GCompareFunc compare_func);
gint                (*GCompareFunc)                     (gconstpointer a,
                                                         gconstpointer b);
GList*              g_list_insert_sorted_with_data      (GList *list,
                                                         gpointer data,
                                                         GCompareDataFunc func,
                                                         gpointer user_data);
GList*              g_list_sort_with_data               (GList *list,
                                                         GCompareDataFunc compare_func,
                                                         gpointer user_data);
gint                (*GCompareDataFunc)                 (gconstpointer a,
                                                         gconstpointer b,
                                                         gpointer user_data);
GList*              g_list_concat                       (GList *list1,
                                                         GList *list2);
void                g_list_foreach                      (GList *list,
                                                         GFunc func,
                                                         gpointer user_data);
void                (*GFunc)                            (gpointer data,
                                                         gpointer user_data);

GList*              g_list_first                        (GList *list);
GList*              g_list_last                         (GList *list);
#define             g_list_previous                     (list)
#define             g_list_next                         (list)
GList*              g_list_nth                          (GList *list,
                                                         guint n);
gpointer            g_list_nth_data                     (GList *list,
                                                         guint n);
GList*              g_list_nth_prev                     (GList *list,
                                                         guint n);

GList*              g_list_find                         (GList *list,
                                                         gconstpointer data);
GList*              g_list_find_custom                  (GList *list,
                                                         gconstpointer data,
                                                         GCompareFunc func);
gint                g_list_position                     (GList *list,
                                                         GList *llink);
gint                g_list_index                        (GList *list,
                                                         gconstpointer data);

void                g_list_push_allocator               (gpointer allocator);
void                g_list_pop_allocator                (void);
```



## Description

GList 구조 및 관련 기능은 표준 이중 연결 목록 데이터 구조를 제공합니다.

목록의 각 요소에는 목록의 이전 및 다음 요소에 연결하는 포인터와 함께 데이터 조각이 포함됩니다. 이 포인터를 사용하면 목록을 양방향으로 이동할 수 있습니다(순방향으로만 목록을 이동할 수 있는 단일 연결 목록과 달리).

각 요소에 포함된 데이터는 유형 변환 매크로 중 하나를 사용하여 정수 값이거나 단순히 모든 유형의 데이터에 대한 포인터일 수 있습니다.

목록 요소는 슬라이스 할당자에서 할당되며, 이는 요소를 개별적으로 할당하는 것보다 더 효율적입니다.

대부분의 GList 함수는 목록의 첫 번째 요소에 대한 포인터가 전달될 것으로 예상합니다. 요소를 삽입하는 함수는 변경되었을 수 있는 목록의 새로운 시작을 반환합니다.

GList를 생성하는 기능은 없습니다. NULL은 빈 목록으로 간주되므로 GList*를 NULL로 설정하기만 하면 됩니다.

- 요소를 추가하려면 g_list_append(), g_list_prepend(), g_list_insert() 및 g_list_insert_sorted()를 사용하십시오.
- 요소를 제거하려면 g_list_remove()를 사용하십시오.
- 목록에서 요소를 찾으려면 g_list_first(), g_list_last(), g_list_next(), g_list_previous(), g_list_nth(), g_list_nth_data(), g_list_find() 및 g_list_find_custom()을 사용하십시오.
- 요소의 인덱스를 찾으려면 g_list_position() 및 g_list_index()를 사용하십시오.
- 목록의 각 요소에 대한 함수를 호출하려면 g_list_foreach()를 사용하십시오.
- 전체 목록을 해제하려면 g_list_free()를 사용하십시오.



## Details

### GList

``` c
typedef struct {
  gpointer data;
  GList *next;
  GList *prev;
} GList;
```

GList 구조체는 이중 연결 목록의 각 요소에 사용됩니다.

- [gpointer](https://developer.gimp.org/api/2.0/glib/glib-Basic-Types.html#gpointer) *`data`*; 모든 종류의 데이터에 대한 포인터가 될 수 있는 요소의 데이터 또는 유형 변환 매크로를 사용하는 모든 정수 값을 보유합니다.
- GList *next; 목록의 다음 요소에 대한 링크를 포함합니다.
- GList *prev; 목록의 이전 요소에 대한 링크를 포함합니다.



### g_list_append ()

``` c
GList* g_list_append (GList *list,
                      gpointer data);
```

목록 끝에 새 요소를 추가합니다.

**Note :** 반환 값은 변경되었을 수 있는 목록의 새로운 시작이므로 새 값을 저장해야 합니다.
**Note:** g_list_append()는 끝을 찾기 위해 전체 목록을 탐색해야 하므로 여러 요소를 추가할 때 비효율적입니다. 비효율성을 피하기 위한 일반적인 관용구는 요소를 앞에 추가하고 모든 요소가 추가되었을 때 목록을 뒤집는 것입니다.

``` c
/* Notice that these are initialized to the empty list. */
GList *list = NULL, *number_list = NULL;

/* This is a list of strings. */
list = g_list_append (list, "first");
list = g_list_append (list, "second");

/* This is a list of integers. */
number_list = g_list_append (number_list, GINT_TO_POINTER (27));
number_list = g_list_append (number_list, GINT_TO_POINTER (14));
```

- *`list`* : GList에 대한 포인터
- *`data`* : 새 요소에 대한 데이터
- *Returns* : GList의 새로운 시작



### g_list_prepend ()

``` C
GList* g_list_prepend (GList *list,
                       gpointer data);
```

목록의 시작 부분에 새 요소를 추가합니다.

**Note:** 반환 값은 변경되었을 수 있는 목록의 새로운 시작이므로 새 값을 저장해야 합니다.

``` c
/* Notice that it is initialized to the empty list. */
GList *list = NULL;
list = g_list_prepend (list, "last");
list = g_list_prepend (list, "first");
```

- *`list`* : GList에 대한 포인터
- *`data`* : 새 요소에 대한 데이터
- *Returns* : GList의 새로운 시작



### g_list_insert ()

``` c
GList* g_list_insert (GList *list,
                      gpointer data,
                      gint position);
```

지정된 위치의 목록에 새 요소를 삽입합니다.

- *`list`* : GList에 대한 포인터
- *`data`* : 새 요소에 대한 데이터
- *`position`* :  요소를 삽입할 위치입니다. 이것이 음수이거나 목록의 요소 수보다 크면 새 요소가 목록 끝에 추가됩니다.
- *Returns* : GList의 새로운 시작



### g_list_insert_before ()

``` c
GList* g_list_insert_before (GList *list,
                             GList *sibling,
                             gpointer data);
```

주어진 위치 앞에 목록에 새 요소를 삽입합니다.

- *`list`* :  GList에 대한 포인터
- *`sibling`* : 새로운 요소가 삽입되기 전의 목록 요소 또는 목록의 끝에 삽입할 NULL
- *`data`* : 새 요소에 대한 데이터
- *Returns* : GList의 새로운 시작



### g_list_insert_sorted ()

``` c
GList* g_list_insert_sorted (GList *list,
                             gpointer data,
                             GCompareFunc func);
```

주어진 비교 함수를 사용하여 위치를 결정하는 새 요소를 목록에 삽입합니다.

- *`list`* : GList에 대한 포인터
- *`data`* : 새 요소에 대한 데이터
- *`func`* : 목록의 요소를 비교하는 함수입니다. 첫 번째 매개변수가 정렬 순서에서 두 번째 매개변수 뒤에 오는 경우(>) 숫자 0을 반환해야 합니다.
- *Returns* : GList의 새로운 시작



### g_list_remove ()

``` c
GList* g_list_remove (GList *list,
                      gconstpointer data);
```

GList에서 요소를 제거합니다. 두 요소에 동일한 데이터가 포함되어 있으면 첫 번째 요소만 제거됩니다. 데이터를 포함하는 요소가 없으면 GList는 변경되지 않습니다.

- *`list`* : GList
- *`data`* : 제거할 요소의 데이터
- *반환:* GList의 새로운 시작



### g_list_remove_link ()

``` c
GList* g_list_remove_link (GList *list,
                           GList *llink);
```

요소를 해제하지 않고 GList에서 요소를 제거합니다. 제거된 요소의 이전 및 다음 링크는 NULL로 설정되어 하나의 요소가 있는 자체 포함 목록이 됩니다.

- *`list`* :  a [GList](https://developer.gimp.org/api/2.0/glib/glib-Doubly-Linked-Lists.html#GList)
- *`llink`* : GList의 요소
- *Returns* : 요소가 없는 GList의 새로운 시작



### g_list_delete_link ()

``` c
GList*              g_list_delete_link                  (GList *list,
                                                         GList *link_);
```

목록에서 link_ 노드를 제거하고 해제합니다. 이것을 노드를 해제하지 않고 제거하는 g_list_remove_link()와 비교하십시오.

- *`list`* : GList
- *`link_`* : 목록에서 삭제할 노드
- *Returns* :  목록의 새로운 머리



### g_list_remove_all ()

``` c
GList* g_list_remove_all (GList *list,
                          gconstpointer data);
```

data 가 data와 동일한 모든 목록 노드를 제거합니다. 목록의 새 헤드를 반환합니다. 주어진 데이터와 일치하는 첫 번째 노드만 제거하는 g_list_remove()와 대조됩니다.

- *`list`* : GList
- *`data`* : 제거할 데이터
- *Returns* :  새 목록 header



### g_list_free ()

``` c
void g_list_free (GList *list);
```

GList에서 사용하는 모든 메모리를 해제합니다. 해제된 요소는 슬라이스 할당자로 반환됩니다.

**Note**

```
목록 요소에 동적으로 할당된 메모리가 포함되어 있으면 먼저 해제해야 합니다.
```

- *`list`* : GList



### g_list_alloc ()

``` c
GList* g_list_alloc (void);
```

하나의 GList 요소에 대한 공간을 할당합니다. g_list_append(), g_list_prepend(), g_list_insert() 및 g_list_insert_sorted()에 의해 호출되므로 단독으로 거의 사용되지 않습니다.

*Returns* : 새로 할당된 GList 요소에 대한 포인터.



### g_list_free_1 ()

``` c
void g_list_free_1 (GList *list);
```

하나의 GList 요소를 해제합니다. 일반적으로 g_list_remove_link() 다음에 사용됩니다.

*`list`* : GList 요소



### g_list_free1

```
#define             g_list_free1
```

g_list_free_1()의 다른 이름입니다.



### g_list_length ()

``` c
guint g_list_length (GList *list);
```

GList의 요소 수를 가져옵니다.

**Note**

```
이 함수는 전체 목록을 반복하여 요소를 계산합니다.
```

- *`list`* : GList
- *Returns* : GList의 요소 수



### g_list_copy ()

``` c
GList* g_list_copy (GList *list);
```

GList를 복사합니다.

**Note**

```
이것은 '얕은' 사본이라는 점에 유의하십시오. 목록 요소가 데이터에 대한 포인터로 구성된 경우 포인터는 복사되지만 실제 데이터는 복사되지 않습니다.
```

- *`list`* : GList
- *Returns* : 목록의 사본



### g_list_reverse ()

``` c
GList* g_list_reverse (GList *list);
```

GList를 뒤집습니다. 단순히 각 요소의 다음 및 이전 포인터를 전환합니다.

- *`list`* : GList
- *Returns* : 반전된 GList의 시작



### g_list_sort ()

``` c
GList* g_list_sort (GList *list,
                    GCompareFunc compare_func);
```

주어진 비교 함수를 사용하여 GList를 정렬합니다.

- *`list`* : 
- *`compare_func`* : GList를 정렬하는 데 사용되는 비교 함수입니다. 이 함수는 GList의 2개 요소로부터 데이터를 전달받았고, 같으면 0을 반환해야 하며, 첫 번째 요소가 두 번째 요소보다 먼저 오면 음수 값을 반환하고, 두 번째 요소 다음에 첫 번째 요소가 오면 양수 값을 반환해야 합니다.
- *Returns* : 정렬된 GList의 시작



### GCompareFunc ()

``` c
gint (*GCompareFunc) (gconstpointer a,
                      gconstpointer b);
```

두 값을 비교하는 데 사용되는 비교 함수의 유형을 지정합니다. 함수는 첫 번째 값이 두 번째 값보다 먼저(<) 오면 음의 정수를 반환하고, 같으면(==) 0을 반환하고, 첫 번째 값이 두 번째 값보다 뒤에(>) 오면 양의 정수를 반환해야 합니다.

- *`a`* :  a value.
- *`b`* : 비교할 값입니다.
- *Returns* :  '*`a`* < *`b`*;' 이면 음수 값입니다. A = b이면 0;  '*`a`* > *`b`*.' 인 경우 양수 값.



### g_list_insert_sorted_with_data ()

``` c
GList* g_list_insert_sorted_with_data (GList *list,
                                       gpointer data,
                                       GCompareDataFunc func,
                                       gpointer user_data);
```

주어진 비교 함수를 사용하여 위치를 결정하는 새 요소를 목록에 삽입합니다.

- *`list`* : GList에 대한 포인터
- *`data`* :  새 요소에 대한 데이터
- *`func`* : 목록의 요소를 비교하는 함수입니다. 첫 번째 매개변수가 정렬 순서에서 두 번째 매개변수 뒤에 오는 경우 숫자 0보다 큰 값을 반환해야 합니다.
- *`user_data`* : 비교 함수에 전달할 사용자 데이터.
- *Returns* :  GList의 새로운 시작



### g_list_sort_with_data ()

``` c
GList* g_list_sort_with_data (GList *list,
                              GCompareDataFunc compare_func,
                              gpointer user_data);
```

g_list_sort()와 비슷하지만 비교 함수는 사용자 데이터 인수를 허용합니다.

- *`list`* : GList
- *`compare_func`* :  비교 함수
- *`user_data`* : 비교 함수에 전달할 사용자 데이터
- *Returns* : 목록의 새로운 header



### GCompareDataFunc ()

``` c
gint (*GCompareDataFunc) (gconstpointer a,
                          gconstpointer b,
                          gpointer user_data);
```

두 값을 비교하는 데 사용되는 비교 함수의 유형을 지정합니다. 함수는 첫 번째 값이 두 번째 값보다 먼저 오면 음의 정수를 반환하고, 같으면 0을 반환하고, 첫 번째 값이 두 번째 값보다 뒤에 오면 양의 정수를 반환해야 합니다.

- *`a`* : a value.
- *`b`* : 비교할 값입니다.
- *`user_data`* : 비교 함수에 전달할 사용자 데이터.
- *Returns* : negative value if *`a`* < *`b`*; zero if *`a`* = *`b`*; positive value if *`a`* > *`b`*.



### g_list_concat ()

``` c
GList* g_list_concat (GList *list1,
                      GList *list2);
```

첫 번째 GList의 끝에 두 번째 GList를 추가합니다. 두 번째 GList의 요소는 복사되지 않습니다. 그들은 직접 사용됩니다.

- *`list1`* : GList
- *`list2`* : 첫 번째 GList의 끝에 추가할 GList
- *Returns* : 새로운 GList의 시작



### g_list_foreach ()

``` c
void g_list_foreach (GList *list,
                     GFunc func,
                     gpointer user_data);
```

GList의 각 요소에 대한 함수를 호출합니다.

- *`list`* : GList
- *`func`* : 각 요소를 데이터로 호출하는 함수
- *`user_data`* :  함수에 전달할 사용자 데이터



### GFunc ()

``` c
void (*GFunc) (gpointer data,
               gpointer user_data);
```

g_list_foreach() 및 g_slist_foreach()에 전달되는 함수의 유형을 지정합니다.

- *`data`* : 요소의 데이터.
- *`user_data`* : g_list_foreach() 또는 g_slist_foreach()에 전달된 사용자 데이터.



### g_list_first ()

``` c
GList* g_list_first (GList *list);
```

GList의 첫 번째 요소를 가져옵니다.

- *`list`* : GList
- *Returns* : GList의 첫번째 요소,  또는 GList에 요소가 없는 경우 NULL



### g_list_last ()

``` c
GList* g_list_last (GList *list);
```

GList의 마지막 요소를 가져옵니다.

- *`list`* : GList
- *Returns* : GList의 마지막 요소, 또는 GList에 요소가 없는 경우 NULL



### g_list_previous()

``` c
#define g_list_previous(list)
```

GList의 이전 요소를 가져오는 편리한 매크로입니다.

- *`list`* : GList의 요소.
- *Returns* : 이전 요소, 또는 이전 요소가 없는 경우 NULL입니다.



### g_list_next()

``` c
#define             g_list_next(list)
```

GList의 다음 요소를 가져오는 편리한 매크로입니다.

- *`list`* : GList의 요소.
- *Returns* : 다음 요소 또는 더 이상 요소가 없으면 NULL입니다.



### g_list_nth ()

```c
GList* g_list_nth (GList *list,
                   guint n);
```

GList의 지정된 위치에 있는 요소를 가져옵니다.

- *list :* GList
- *n:* 요소의 위치, 0부터 계산
- *Returns* : 요소,  또는 위치가 GList의 끝에서 벗어난 경우 NULL



### g_list_nth_data ()

```c
gpointer g_list_nth_data (GList *list,
                          guint n);
```

주어진 위치에 있는 요소의 데이터를 가져옵니다.

- *`list`* : GList
- *`n`* : 요소의 위치
- *Returns* : 요소의 데이터 또는 위치가 GList의 끝에서 벗어난 경우 NULL



### g_list_nth_prev ()

```
GList* g_list_nth_prev (GList *list,
                        guint n);
```

목록 앞의 n 번째 자리 요소를 가져옵니다.

- *`list`* : GList
- *`n`* :  요소의 위치, 0부터 계산
- *Returns* :  요소,  또는 위치가 GList의 끝에서 벗어난 경우 NULL



### g_list_find ()

```c
GList* g_list_find (GList *list,
                    gconstpointer data);
```

주어진 데이터를 포함하는 GList의 요소를 찾습니다.

- *`list`* : GList
- *`data`* : 찾을 요소 데이터
- *Returns* : 발견된 GList 요소, 또는 발견되지 않은 경우 NULL



### g_list_find_custom ()

```c
GList* g_list_find_custom (GList *list,
                           gconstpointer data,
                           GCompareFunc func);
```

원하는 요소를 찾기 위해 제공된 함수를 사용하여 GList에서 요소를 찾습니다. 

원하는 요소가 발견되면 0을 반환해야 하는 주어진 함수를 호출하여 목록을 반복합니다. 

이 함수(func)는 두 개의 gconstpointer 인수를 사용합니다. 

GList 요소의 데이터를 첫 번째 인수로 사용하고 주어진 사용자 데이터(data)를  두번째 인수로 사용합니다.

- *`list`* : GList
- *`data`* : 함수에 전달된 사용자 데이터
- *`func`* : 각 요소에 대해 호출하는 함수입니다. 원하는 요소가 발견되면 0을 반환해야 합니다.
- *Returns* : 발견된 GList 요소, 또는 발견되지 않은 경우 NULL

------



### g_list_position ()

```c
gint g_list_position (GList *list,
                      GList *llink);
```

GList에서 주어진 요소의 위치를 가져옵니다(0부터 시작).

- *`list`* : GList
- *`llink`* : GList의 요소
- *Returns* : GList에서 요소의 위치, 또는 요소를 찾을 수 없는 경우 -1



### g_list_index ()

```c
gint g_list_index (GList *list,
                   gconstpointer data);
```

주어진 데이터를 포함하는 요소의 위치를 가져옵니다(0부터 시작).

- *`list`* : GList
- *`data`* : 찾을 데이터
- *Returns* : 데이터를 포함하는 요소의 인덱스,  또는 데이터를 찾을 수 없는 경우 -1



### g_list_push_allocator ()

```c
void g_list_push_allocator (gpointer allocator);
```

### <span style="color:red">Warning</span>

<span style="color:red">g_list_push_allocator는 버전 2.10부터 더 이상 사용되지 않으며 새로 작성된 코드에서 사용하면 안 됩니다.</span> GList가 슬라이스 할당자로 변환되었으므로 아무 작업도 수행하지 않습니다.  GList 요소를 할당하는 데 사용할 할당자를 설정합니다. G_list_pop_allocator()를 사용하여 이전 할당자를 복원합니다.

GLib가 `--disable-mem-pools`로 컴파일된 경우 이 기능을 사용할 수 없습니다.

- *`allocator`* :  GList 요소를 할당할 때 사용할 GAllocator.



### g_list_pop_allocator ()

```
void                g_list_pop_allocator                (void);
```

### <span style="color:red">Warning</span>

<span style="color:red">g_list_push_allocator는 버전 2.10부터 더 이상 사용되지 않으며 새로 작성된 코드에서 사용하면 안 됩니다.</span> GList가 슬라이스 할당자로 변환되었으므로 아무 작업도 수행하지 않습니다. 

GList 요소를 할당할 때 사용된 이전 GAllocator를 복원합니다.

GLib가 `--disable-mem-pools`로 컴파일된 경우 이 기능을 사용할 수 없습니다.



---

**예제)**

`list.c`

``` c
// list.c
#include <stdio.h>
#include <stdlib.h>
#include <glib.h>
#include <glib/gprintf.h>

typedef struct tsg_acl 
{
    char ip[24]; // include ip v6
    int allow;   // non zero is allow
}TSG_ACL;

// list 에 data 가 있는지 검색 할 callback function
gint list_find (gconstpointer lvalue,
            gconstpointer cvalue)
{
    gint res = -1;
    TSG_ACL *lv = (TSG_ACL *) lvalue;
    TSG_ACL *cv = (TSG_ACL *) cvalue;

    if(!strcmp(lv->ip, cv->ip))
        res = 0;

    return res;
}

// list 에 존재하는 data 의 member 를 print 할 callback function
void list_print (gpointer data, gpointer user_data)
{
    TSG_ACL *acl = (TSG_ACL *) data;
    printf("ip=%s, allow=%d\n"
            , acl->ip
            , acl->allow
            );        
}

// list 에 존재하는 data 의 할당받은 memory 를 해제 하는 callback function
void list_free (gpointer data, gpointer user_data)
{
    if(data != NULL)
    {
        free(data);
        data = NULL;
    }
        
}


// GList 테스트 함수
void st_test_glist(void)
{
    int cnt = 0;
    GList *list = NULL;
    TSG_ACL *acl = malloc(sizeof(TSG_ACL));
    TSG_ACL cAcl;

    sprintf(acl->ip,"123.234.213.224");
    acl->allow = 1;
    list = g_list_append(list, acl);

    acl = malloc(sizeof(TSG_ACL));
    sprintf(acl->ip,"127.0.0.1");
    acl->allow = 0;
    list = g_list_append(list, acl);

    acl = malloc(sizeof(TSG_ACL));
    sprintf(acl->ip,"192.168.60.211");
    acl->allow = 1;
    list = g_list_append(list, acl);

    cnt = g_list_length (list);

    sprintf(cAcl.ip, "127.0.0.5");
    GList *fnd = g_list_find_custom (list, &cAcl, list_find);
    if(fnd != NULL)
        printf("data found: ip=%s, allow=%d\n"
                , ((TSG_ACL *)fnd->data)->ip
                , ((TSG_ACL *)fnd->data)->allow);
    else
        printf("data not found: ip=%s\n", cAcl.ip );

    printf("list count=%d\n\n", cnt);

    g_list_foreach(list, list_print, NULL);
    g_list_foreach(list, list_free, NULL);

    g_list_free(list);
    printf("\n");
}

int main(void)
{
    printf("\n\n");
    st_test_glist();
    printf("\n\n");

    return 0;
}
```

`Makefile`

``` makefile
CC=gcc -Og

CFLAGS = -Wall -O2 -g -Wno-format-truncation 

INCLUDES = -I/usr/include \
		-I/usr/include/glib-2.0 \
		-I/usr/lib/x86_64-linux-gnu/glib-2.0/include

LFLAGS = -L/usr/lib -L/usr/lib/x86_64-linux-gnu -lglib-2.0

CFILES = list.c

OBJ = $(CFILES:.c=.o)


.c.o:
	$(CC) $(CFLAGS) $(INCLUDES)  -c $*.c

all: list

list: $(OBJ)
	$(CC) -o $@ $< $(LFLAGS)

clean:
	rm -f $(OBJ)
	rm -f list

```



### - 끝 -

