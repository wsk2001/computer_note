# C Snippet



### 1. GCC로 Queue 만들기

``` C
#include <stdio.h>

// 큐의 노드 구조체
struct node {
  int data;
  struct node *next;
};

// 큐 구조체
struct queue {
  struct node *head;
  struct node *tail;
};

// 큐 생성
struct queue *queue_create(void) {
  struct queue *q = malloc(sizeof(struct queue));
  q->head = NULL;
  q->tail = NULL;
  return q;
}

// 큐에 데이터 삽입
void queue_enqueue(struct queue *q, int data) {
  struct node *new_node = malloc(sizeof(struct node));
  new_node->data = data;
  new_node->next = NULL;

  if (q->head == NULL) {
    q->head = new_node;
    q->tail = new_node;
  } else {
    q->tail->next = new_node;
    q->tail = new_node;
  }
}

// 큐에서 데이터 삭제
int queue_dequeue(struct queue *q) {
  if (q->head == NULL) {
    return -1;
  }

  struct node *node = q->head;
  int data = node->data;

  q->head = q->head->next;
  if (q->head == NULL) {
    q->tail = NULL;
  }

  free(node);
  return data;
}

int main() {
  struct queue *q = queue_create();

  queue_enqueue(q, 10);
  queue_enqueue(q, 20);
  queue_enqueue(q, 30);

  int data = queue_dequeue(q);
  printf("dequeued data: %d\n", data);

  data = queue_dequeue(q);
  printf("dequeued data: %d\n", data);

  data = queue_dequeue(q);
  printf("dequeued data: %d\n", data);

  return 0;
}

```

이 예제에서는 큐를 노드 구조체를 사용하여 구현했습니다. 노드 구조체는 데이터와 다음 노드에 대한 포인터를 포함합니다. 큐 구조체는 헤드와 꼬리를 가리키는 포인터를 포함합니다.

큐를 생성하려면 `queue_create()` 함수를 사용합니다. 이 함수는 새 큐 구조체를 할당하고 헤드와 꼬리를 NULL로 초기화합니다.

큐에 데이터를 삽입하려면 `queue_enqueue()` 함수를 사용합니다. 이 함수는 새 노드를 할당하고 데이터를 삽입한 다음 노드를 큐의 꼬리에 연결합니다.

큐에서 데이터를 삭제하려면 `queue_dequeue()` 함수를 사용합니다. 이 함수는 큐의 헤드에서 데이터를 제거하고 반환합니다.

이 예제에서는 큐에 3개의 데이터를 삽입한 다음 3개의 데이터를 삭제합니다.

이 코드를 컴파일하고 실행하면 다음 출력이 나타납니다.

```
dequeued data: 10
dequeued data: 20
dequeued data: 30
```



### 2. GCC로 해시맵을 만드는 방법

``` C
#include <stdio.h>
#include <stdlib.h>

typedef struct node {
  int key;
  int value;
  struct node *next;
} node;

typedef struct hashmap {
  node **buckets;
  int size;
  int capacity;
} hashmap;

hashmap *hashmap_create(int capacity) {
  hashmap *map = malloc(sizeof(hashmap));
  map->buckets = malloc(capacity * sizeof(node *));
  memset(map->buckets, 0, capacity * sizeof(node *));
  map->size = 0;
  map->capacity = capacity;
  return map;
}

void hashmap_insert(hashmap *map, int key, int value) {
  int index = key % map->capacity;
  node *new_node = malloc(sizeof(node));
  new_node->key = key;
  new_node->value = value;
  new_node->next = map->buckets[index];
  map->buckets[index] = new_node;
  map->size++;
}

int hashmap_get(hashmap *map, int key) {
  int index = key % map->capacity;
  node *current = map->buckets[index];
  while (current) {
    if (current->key == key) {
      return current->value;
    }
    current = current->next;
  }
  return -1;
}

void hashmap_delete(hashmap *map, int key) {
  int index = key % map->capacity;
  node *current = map->buckets[index];
  node *prev = NULL;
  while (current) {
    if (current->key == key) {
      if (prev == NULL) {
        map->buckets[index] = current->next;
      } else {
        prev->next = current->next;
      }
      free(current);
      map->size--;
      return;
    }
    prev = current;
    current = current->next;
  }
}

void hashmap_destroy(hashmap *map) {
  for (int i = 0; i < map->capacity; i++) {
    node *current = map->buckets[i];
    while (current) {
      node *next = current->next;
      free(current);
      current = next;
    }
  }
  free(map->buckets);
  free(map);
}

int main() {
  hashmap *map = hashmap_create(10);
  hashmap_insert(map, 1, 10);
  hashmap_insert(map, 2, 20);
  hashmap_insert(map, 3, 30);
  hashmap_insert(map, 4, 40);
  hashmap_insert(map, 5, 50);
  printf("The value of key 1 is: %d\n", hashmap_get(map, 1));
  printf("The value of key 2 is: %d\n", hashmap_get(map, 2));
  printf("The value of key 3 is: %d\n", hashmap_get(map, 3));
  printf("The value of key 4 is: %d\n", hashmap_get(map, 4));
  printf("The value of key 5 is: %d\n", hashmap_get(map, 5));
  hashmap_delete(map, 1);
  hashmap_delete(map, 2);
  hashmap_delete(map, 3);
  hashmap_delete(map, 4);
  hashmap_delete(map, 5);
  hashmap_destroy(map);
  return 0;
}

```

이 코드는 다음과 같이 작동합니다.

- `hashmap_create()` 함수는 새 해시맵을 생성합니다. 해시맵은 `buckets` 배열을 사용하여 키를 저장합니다. `buckets` 배열의 각 요소는 `node` 구조체의 포인터입니다. `node` 구조체는 키, 값 및 다음 노드



### 3. GCC 로 ini 파싱 하는 방법

``` C
#include <stdio.h>
#include <stdlib.h>

struct ini_entry {
  char *key;
  char *value;
};

struct ini_file {
  struct ini_entry *entries;
  int num_entries;
};

struct ini_file *ini_parse(const char *filename) {
  struct ini_file *file = malloc(sizeof(struct ini_file));
  file->entries = NULL;
  file->num_entries = 0;

  FILE *fp = fopen(filename, "r");
  if (fp == NULL) {
    return NULL;
  }

  char line[1024];
  while (fgets(line, sizeof(line), fp)) {
    // 주석을 제거합니다.
    char *comment = strchr(line, '#');
    if (comment != NULL) {
      *comment = '\0';
    }

    comment = strstr(line, "//");
    if (comment != NULL) {
      *comment = '\0';
    }

    // key/value 쌍을 찾습니다.
    char *key = strtok(line, "=");
    char *value = strtok(NULL, "=");
    if (key != NULL && value != NULL) {
      struct ini_entry *entry = malloc(sizeof(struct ini_entry));
      entry->key = key;
      entry->value = value;
      file->entries = realloc(file->entries, sizeof(struct ini_entry) * (file->num_entries + 1));
      file->entries[file->num_entries++] = entry;
    }
  }

  fclose(fp);
  return file;
}

char *ini_get(struct ini_file *file, const char *key, const char *default_value) {
  for (int i = 0; i < file->num_entries; i++) {
    if (strcmp(file->entries[i].key, key) == 0) {
      return file->entries[i].value;
    }
  }

  return default_value;
}

int main() {
  struct ini_file *file = ini_parse("example.ini");
  if (file == NULL) {
    printf("파일을 읽을 수 없습니다.\n");
    return 1;
  }

  printf("name: %s\n", ini_get(file, "name", "default_name"));
  printf("age: %d\n", atoi(ini_get(file, "age", "0")));

  free(file->entries);
  free(file);
  return 0;
}

```

이 코드는 다음과 같은 방식으로 작동합니다.

1. `ini_parse()` 함수는 파일을 읽고 각 줄을 처리하여 key/value 쌍을 추출합니다.
2. `ini_get()` 함수는 key 값을 이용하여 value 를 조회합니다. key 가 존재하지 않으면 default value 를 리턴합니다.

예를 들어, 다음과 같은 ini 파일이 있다고 가정해 보겠습니다.

``` ini
[section1]
name = John Doe
age = 30

[section2]
name = Jane Doe
age = 25
```

이 경우 `ini_get()` 함수는 다음과 같이 동작합니다.

``` C
char *value = ini_get(file, "name", "default_name");
printf("name: %s\n", value);
```

이 코드는 다음과 같은 출력을 생성합니다.

```
name: John Doe
```

C++ 을 사용하지 않고 ini 파일을 파싱하는 방법은 다음과 같습니다.

- 각 줄을 처리하기 전에 주석을 제거합니다.
- key/value 쌍을 추출하기 위해 `strtok()` 함수를 사용합니다.
- key 값을 이용하여 value 를 조회하기 위해 `bsearch()` 함수를 사용할 수 있습니다.

`bsearch()` 함수는 주어진 key 값과 일치하는 value 의 포인터를 반환합니다. key 가 존재하지 않으면 NULL 을 반환합니다.

