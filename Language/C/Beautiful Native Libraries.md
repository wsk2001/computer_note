# Beautiful Native Libraries

출처: https://lucumr.pocoo.org/2013/8/18/beautiful-native-libraries/



### Beautiful Native Libraries

저는 좋은 API에 푹 빠져 있어요. 그러나 API뿐만 아니라 라이브러리 사용에 대한 전반적인 경험을 최대한 좋게 만드는 데도 도움이 됩니다. Python의 경우 지금까지 꽤 많은 모범 사례가 있지만 네이티브 라이브러리를 적절하게 구성하는 방법에 대해 사용할 수 있는 정보가 실제로 많지 않은 것 같습니다. 네이티브 라이브러리란 무엇을 의미하나요? 본질적으로 dylib/DLL/so입니다.

나는 현재 직장에서 Python보다 C 및 C++에 더 많은 시간을 보내고 있기 때문에 기회를 잡고 사용자를 괴롭히지 않는 적절한 공유 라이브러리를 작성하는 방법에 대한 내 생각을 수집할 수 있다고 생각했습니다.



### Shared or Static?

이 게시물에서는 정적으로 링크하는 것이 아니라 DLL 또는 공유 라이브러리를 구축한다고 가정합니다. 정적으로 링크된 라이브러리와 동적으로 링크된 라이브러리는 링크하는 방법만 다르다는 점에서 본질적으로 동일한 것처럼 들리지만 훨씬 더 많은 것이 있습니다.

동적으로 연결된 라이브러리를 사용하면 기호를 훨씬 효과적으로 제어할 수 있습니다. 동적으로 연결된 라이브러리는 서로 다른 프로그래밍 언어 간에도 훨씬 더 잘 작동합니다. C++로 라이브러리를 작성한 다음 Python에서 사용하는 것을 막을 수 있는 방법은 없습니다. 사실, 이것이 바로 제가 그러한 라이브러리에 대해 단위 테스트를 수행하는 것을 권장하는 방법입니다. 이에 대해서는 나중에 자세히 설명하겠습니다.



### Which Language?

따라서 DLL이나 그와 유사한 것으로 컴파일되는 라이브러리를 작성하려면 어느 정도 플랫폼 독립적이어야 합니다. 그곳에서 실제로 어떤 언어를 사용할 수 있나요? 지금은 C와 C++ 중에서 선택할 수 있으며 곧 해당 목록에 Rust를 추가할 수도 있습니다. 다른 사람들은 왜 안되나요? C는 쉽습니다. 실제로 어느 정도 안정적인 ABI를 정의하는 유일한 언어이기 때문입니다. 엄밀히 말하면 이를 정의하는 언어가 아니라 운영 체제이지만 어떤 식으로든 C는 라이브러리가 선택한 언어이고 C 호출 규칙은 공유 라이브러리의 공용어입니다.

'C가 해낸 가장 큰 트릭은 세상에 런타임이 없다는 것을 확신시키는 것입니다.' 이 인용문을 어디서 처음 들었는지는 잘 모르겠지만, 도서관에 관해 이야기할 때 매우 적절합니다. 본질적으로 C는 너무 흔해서 모든 것이 C 표준 라이브러리에서 일부 기본 기능을 제공한다고 가정할 수 있습니다. 그것이 존재한다는 것에 모두가 동의한 한 가지입니다. C++의 경우 상황은 더 복잡합니다. C++에는 C 표준 라이브러리에서 제공하지 않는 추가 기능이 많이 필요합니다. 주로 예외 처리에 대한 지원이 필요합니다. 그러나 C++는 C 호출 규칙으로 훌륭하게 저하되므로 C++가 배후에 있다는 사실을 완전히 숨기는 라이브러리를 작성하는 것이 매우 쉽습니다.

그러나 다른 언어의 경우에는 그렇게 쉽지 않습니다. 예를 들어 Go로 라이브러리를 작성하는 것이 왜 좋지 않습니까? 그 이유는 Go for에는 가비지 수집을 수행하고 코루틴에 대한 스케줄러를 제공하는 상당히 무거운 런타임이 필요하기 때문입니다. Rust는 C 표준 라이브러리 외에 라이브러리 작성을 가능하게 하는 런타임 요구 사항이 없는 상태에 가까워지고 있습니다.

그러나 지금 당장은 C++가 여러분이 사용하고 싶은 언어일 가능성이 가장 높습니다. C는 왜 안되나요? 그 이유는 Microsoft의 C 컴파일러가 언어 업데이트를 수신하는 데 악명 높으며 그렇지 않으면 C89에 갇힐 수 있기 때문입니다. 분명히 Windows에서 다른 컴파일러를 사용할 수 있지만 라이브러리 사용자가 스스로 컴파일하려는 경우 라이브러리 사용자에게 많은 문제가 발생합니다. 운영 체제에 고유하지 않은 도구 체인을 요구하는 것은 개발자 청중을 소외시키는 쉬운 방법입니다.

그러나 나는 일반적으로 C++의 하위 집합과 매우 유사한 C를 권장합니다. 예외를 사용하지 말고, RTTI를 사용하지 말고, 미친 생성자를 구축하지 마십시오. 게시물의 나머지 부분에서는 C++가 실제로 선택한 언어라고 가정합니다.



### Public Headers

구축 중인 라이브러리에는 이상적으로 정확히 하나의 공개 헤더 파일이 있어야 합니다. 내부적으로 미쳐서 원하는 만큼 헤더를 생성하세요. 라이브러리가 C가 아닌 항목에 대해서만 링크될 것이라고 생각하더라도 하나의 공개 헤더 파일이 존재하기를 원합니다. 예를 들어 Python의 CFFI 라이브러리는 헤더 파일을 구문 분석하고 그로부터 바인딩을 구축할 수 있습니다. 모든 언어의 사람들은 헤더가 어떻게 작동하는지 알고 있으며 헤더를 살펴보고 자신만의 바인딩을 구축할 것입니다.

헤더에는 어떤 규칙을 따라야 하나요?



### Header Guards

다른 사람들이 사용하는 각 공개 헤더에는 여러 번 안전하게 포함될 수 있도록 충분히 고유한 헤더 가드가 있어야 합니다. 경비원을 너무 창의적으로 대하지 말고, 너무 일반적으로 대하지도 마세요. 상단에 매우 일반적인 포함 가드(UTILS_H 등)가 있는 헤더를 포함하는 것은 재미가 없습니다. 또한 C++에 대한 외부 'C' 마커가 있는지 확인하려고 합니다.

이것은 최소한의 헤더입니다:

``` c++
#ifndef YOURLIB_H_INCLUDED
#define YOURLIB_H_INCLUDED
#ifdef __cplusplus
extern "C" {
#endif

/* code goes here */

#ifdef __cplusplus
}
#endif
#endif
```



### Export Markers

당신 자신도 헤더 파일을 포함할 것이기 때문에 함수를 내보내는 매크로가 정의되어 있는지 확인해야 합니다. 이는 Windows에서 필요하며 다른 플랫폼에서도 매우 좋은 아이디어입니다. 기본적으로 기호의 가시성을 변경하는 데 사용할 수 있습니다. 나중에 이에 대해 설명하겠습니다. 당분간은 다음과 같은 내용을 추가하기만 하면 됩니다.

``` c++
#ifndef YL_API
#  ifdef _WIN32
#     if defined(YL_BUILD_SHARED) /* build dll */
#         define YL_API __declspec(dllexport)
#     elif !defined(YL_BUILD_STATIC) /* use dll */
#         define YL_API __declspec(dllimport)
#     else /* static library */
#         define YL_API
#     endif
#  else
#     if __GNUC__ >= 4
#         define YL_API __attribute__((visibility("default")))
#     else
#         define YL_API
#     endif
#  endif
#endif
```

Windows에서는 설정된 플래그에 따라 DLL에 대해 YL_API(여기서는 'Your Library'의 짧은 버전으로 YL을 사용했습니다. 자신에게 맞는 접두사 선택)를 설정합니다. 이전에 아무 것도 하지 않고 헤더를 포함하는 사람은 누구나 자동으로 그 자리에 __declspec(dllimport)을 갖게 됩니다. 이는 Windows에서 정말 좋은 기본 동작입니다. 다른 플랫폼의 경우 기본 가시성 마커가 추가되는 최신 GCC/clang 버전을 사용하지 않는 한 아무것도 설정되지 않습니다. 보시다시피 일부 매크로를 정의하여 어떤 분기를 수행할지 변경할 수 있습니다. 예를 들어 라이브러리를 빌드할 때 컴파일러에게 YL_BUILD_SHARED도 정의하도록 지시합니다.

Windows에서 DLL의 기본 동작은 항상 다음과 같습니다. __declspec(dllexport)로 표시하지 않는 한 모든 기호는 기본값으로 내보내지지 않습니다. 불행하게도 다른 플랫폼에서는 항상 모든 것을 내보내는 동작이 이루어졌습니다. 이 문제를 해결하는 방법은 여러 가지가 있습니다. 하나는 GCC 4의 가시성 제어입니다. 이는 정상적으로 작동하지만 고려해야 할 몇 가지 추가 사항이 있습니다.

첫 번째는 소스 내 가시성 제어가 만능이 아니라는 것입니다. 처음에는 라이브러리가 -fvisibility=hidden으로 컴파일되지 않으면 마커는 아무 작업도 수행하지 않습니다. 그러나 그보다 더 중요한 것은 이것이 자신의 라이브러리에만 영향을 미친다는 것입니다. 라이브러리에 대해 정적으로 링크하는 경우 해당 라이브러리는 노출하고 싶지 않은 기호를 노출할 수 있습니다. 예를 들어 정적으로 연결하려는 다른 라이브러리에 의존하는 라이브러리를 작성한다고 상상해 보십시오. 이를 방지하지 않는 한 이 라이브러리의 기호도 라이브러리에서 내보내집니다.

이는 플랫폼마다 다르게 작동합니다. Linux에서는 --exclude-libs ALL을 ld에 전달할 수 있으며 링커는 해당 기호를 자동으로 제거합니다. OS X에서는 링커에 그러한 기능이 없기 때문에 더 까다롭습니다. 가장 쉬운 해결책은 모든 기능에 공통 접두사를 갖는 것입니다. 예를 들어 모든 함수가 yl_로 시작하는 경우 링커에게 다른 모든 것을 숨기도록 지시하는 것은 쉽습니다. 기호 파일을 만든 다음 -exported_symbols_list 기호.txt를 사용하여 링커를 지정하면 됩니다. 이 파일의 내용은 _yl_* 한 줄로 구성될 수 있습니다. Windows에서는 DLL에 명시적인 내보내기 마커가 필요하므로 무시할 수 있습니다.



### Careful with Includes and Defines

한 가지 주의할 점은 헤더에 너무 많은 내용을 포함해서는 안 된다는 것입니다. 일반적으로 나는 일반적인 정수 유형을 얻기 위해 헤더에 stdint.h와 같은 항목을 포함하는 것이 좋다고 생각합니다. 그러나 당신이 해서는 안 되는 일은 영리하게 유형을 직접 정의하는 것입니다. 예를 들어 msgpack에는 stdint.h 헤더가 없기 때문에 Visual Studio 2008에 대해 int32_t 및 몇 가지 다른 유형을 정의하는 훌륭한 아이디어가 있었습니다. 그러면 하나의 라이브러리만 해당 유형을 정의할 수 있으므로 문제가 됩니다. 대신 더 나은 해결책은 사용자에게 이전 Visual Studio 버전에 대한 대체 stdint.h 헤더를 제공하도록 요청하는 것입니다.

특히 라이브러리 헤더에 windows.h를 포함하지 마십시오. 해당 헤더는 Microsoft가 더 간결하게 만들기 위해 추가 정의(WINDOWS_LEAN_AND_MEAN, WINDOWS_EXTRA_LEAN 및 NOMINMAX)를 추가한 많은 항목을 가져옵니다. windows.h를 포함해야 하는 경우 .cpp 파일에만 포함되는 전용 헤더 파일을 준비하세요.



### Stable ABI

구조체를 절대 변경하지 않을 것이라고 100% 확신하지 않는 한 공개 헤더에 구조체를 넣지 마십시오. 구조체를 노출하고 나중에 추가 멤버를 추가하려는 경우 사용자가 해당 헤더를 할당할 필요가 없는지 확인하세요. 사용자가 해당 헤더를 할당해야 하는 경우 버전 또는 크기 정보를 구조체의 첫 번째 멤버로 추가하세요.

Microsoft는 일반적으로 나중에 멤버를 추가할 수 있도록 구조체의 크기를 구조체에 넣지만 이로 인해 API가 사용하기에 재미없게 됩니다. 헤더에 너무 많은 구조체가 포함되는 것을 방지할 수 있다면 최소한 API를 덜 짜증나게 만드는 대체 방법을 생각해 보십시오.

구조체를 사용하면 컴파일러마다 정렬이 다를 수 있다는 문제가 발생할 수도 있습니다. 불행하게도 전체 프로젝트에 대해 정렬을 다르게 강제하고 헤더 파일의 구조체에도 분명히 영향을 미치는 프로젝트를 다루는 경우가 있습니다. 구조체가 적을수록 좋습니다 :-)

말할 필요도 없이 매크로를 API의 일부로 만들지 마세요. 매크로는 기호가 아니며 C 기반이 아닌 언어 사용자는 매크로가 있는 것을 싫어할 것입니다.

ABI 안정성에 대한 추가 참고 사항: 헤더에 라이브러리 버전을 포함하고 바이너리로 컴파일하는 것은 매우 좋은 생각입니다. 이렇게 하면 헤더가 바이너리와 일치하는지 쉽게 확인할 수 있으므로 많은 어려움을 겪을 필요가 없습니다.

헤더에 다음과 같은 내용이 있습니다.

``` c++
#define YL_VERSION_MAJOR 1
#define YL_VERSION_MINOR 0
#define YL_VERSION ((YL_VERSION_MAJOR << 16) | YL_VERSION_MINOR)

unsigned int yl_get_version(void);
int yl_is_compatible_dll(void);
And this in the implementation file:

unsigned int yl_get_version(void)
{
    return YL_VERSION;
}

int yl_is_compatible_dll(void)
{
    unsigned int major = yl_get_version() >> 16;
    return major == YL_VERSION_MAJOR;
}
```



### Exporting a C API

C++ API를 C에 노출할 때 고려해야 할 사항은 많지 않습니다. 일반적으로 각 내부 클래스에 대해 필드가 없는 외부 불투명 구조체를 갖게 됩니다. 그런 다음 내부 함수를 호출하는 함수를 제공하십시오. 다음과 같은 수업을 상상해 보세요.

``` c++
namespace yourlibrary {
    class Task {
    public:
        Task();
        ~Task();

        bool is_pending() const;
        void tick();
        const char *result_string() const;
    };
}
```

내부 C++ API는 매우 분명합니다. 그런데 C를 통해 이를 어떻게 노출합니까? 이제 외부 ABI는 구조체의 크기를 더 이상 알 수 없으므로 외부 호출자를 위해 메모리를 할당하거나 할당할 메모리 양을 파악하는 메서드를 제공해야 합니다. 나는 일반적으로 외부 사용자를 위해 할당하고 무료 기능도 제공하는 것을 선호합니다. 메모리 할당 시스템을 여전히 유연하게 만드는 방법은 다음 부분을 살펴보세요.

현재 이것은 외부 헤더입니다(외부 'C' 중괄호 안에 있어야 함).

``` c++
struct yl_task_s;
typedef struct yl_task_s yl_task_t;

YL_API yl_task_t *yl_task_new();
YL_API void yl_task_free(yl_task_t *task);
YL_API int yl_task_is_pending(const yl_task_t *task);
YL_API void yl_task_tick(yl_task_t *task);
YL_API const char *yl_task_get_result_string(const yl_task_t *task);
```

구현 시 심 레이어는 다음과 같습니다.

``` c++
#define AS_TYPE(Type, Obj) reinterpret_cast<Type *>(Obj)
#define AS_CTYPE(Type, Obj) reinterpret_cast<const Type *>(Obj)

yl_task_t *yl_task_new()
{
    return AS_TYPE(yl_task_t, new yourlibrary::Task());
}

void yl_task_free(yl_task_t *task)
{
    if (!task)
        return;
    delete AS_TYPE(yourlibrary::Task, task);
}

int yl_task_is_pending(const yl_task_t *task)
{
    return AS_CTYPE(yourlibrary::Task, task)->is_pending() ? 1 : 0;
}

void yl_task_tick(yl_task_t *task)
{
    AS_TYPE(yourlibrary::Task, task)->tick();
}

const char *yl_task_get_result_string(const yl_task_t *task)
{
    return AS_CTYPE(yourlibrary::Task, task)->result_string();
}
```

생성자와 소멸자가 어떻게 완전히 래핑되는지 확인하세요. 이제 표준 C++에는 한 가지 문제가 있습니다. 예외가 발생한다는 것입니다. 생성자에는 뭔가 잘못되었음을 외부에 알리는 반환 값이 없기 때문에 할당이 실패하면 예외가 발생합니다. 그러나 이것이 유일한 문제는 아닙니다. 이제 라이브러리가 메모리를 할당하는 방식을 어떻게 사용자 정의할 수 있나요? C++은 그런 점에서 꽤 추악합니다. 그러나 대부분 고칠 수 있습니다.

계속하기 전에: 어떤 경우에도 일반적인 이름으로 네임스페이스를 오염시키는 라이브러리를 만들지 마십시오. 네임스페이스 충돌 위험을 낮추려면 항상 모든 기호(예: yl_) 앞에 공통 접두사를 추가하세요.



### Context Objects

글로벌 상태가 최악인데 해결책은 무엇일까요? 일반적으로 해결책은 상태를 유지하는 '컨텍스트' 객체를 갖는 것입니다. 이러한 객체에는 전역 변수에 넣을 중요한 모든 항목이 포함됩니다. 그렇게 하면 라이브러리 사용자가 여러 개의 라이브러리를 가질 수 있습니다. 그런 다음 각 API 함수가 해당 컨텍스트를 첫 번째 매개변수로 사용하도록 만듭니다.

이는 라이브러리가 스레드로부터 안전하지 않은 경우 특히 유용합니다. 이렇게 하면 적어도 스레드당 하나를 가질 수 있으며, 이는 이미 코드에서 일부 병렬 처리를 수행하기에 충분할 수 있습니다.

이상적으로 각 컨텍스트 개체는 서로 다른 할당자를 사용할 수도 있지만 C++에서 이를 수행하는 복잡성을 고려하면 해당 작업을 수행하지 않더라도 크게 실망하지는 않을 것입니다.



### Memory Allocation Customization

앞서 언급했듯이 생성자는 실패할 수 있으며 메모리 할당을 사용자 정의하고 싶은데 어떻게 해야 할까요? C++에는 메모리 할당을 담당하는 두 가지 시스템이 있습니다. 할당 연산자인 연산자 new 및 연산자 new[]와 컨테이너 할당자입니다. 할당자를 사용자 정의하려면 두 가지를 모두 처리해야 합니다. 먼저 다른 사람이 할당자 기능을 재정의할 수 있도록 하는 방법이 필요합니다. 가장 간단한 방법은 공개 헤더에 다음과 같은 내용을 제공하는 것입니다.

``` c++
YL_API void yl_set_allocators(void *(*f_malloc)(size_t),
                              void *(*f_realloc)(void *, size_t),
                              void (*f_free)(void *));
YL_API void *yl_malloc(size_t size);
YL_API void *yl_realloc(void *ptr, size_t size);
YL_API void *yl_calloc(size_t count, size_t size);
YL_API void yl_free(void *ptr);
YL_API char *yl_strdup(const char *str);
```

그런 다음 내부 헤더에 내부 구조체로 설정된 함수 포인터로 리디렉션되는 여러 인라인 함수를 추가할 수 있습니다. 사용자가 calloc 및 strdup을 제공하는 것을 허용하지 않기 때문에 아마도 해당 기능을 다시 구현하고 싶을 수도 있습니다.

``` c++
struct yl_allocators_s {
    void *(*f_malloc)(size_t);
    void *(*f_realloc)(void *, size_t);
    void (*f_free)(void *);
};
extern struct yl_allocators_s _yl_allocators;

inline void *yl_malloc(size_t size)
{
    return _yl_allocators.f_malloc(size);
}

inline void *yl_realloc(void *ptr, size_t size)
{
    return _yl_allocators.f_realloc(ptr, size);
}

inline void yl_free(void *ptr)
{
    _yl_allocators.f_free(ptr);
}

inline void *yl_calloc(size_t count, size_t size)
{
    void *ptr = _yl_allocators.f_malloc(count * size);
    memset(ptr, 0, count * size);
    return ptr;
}

inline char *yl_strdup(const char *str)
{
    size_t length = strlen(str) + 1;
    char *rv = (char *)yl_malloc(length);
    memcpy(rv, str, length);
    return rv;
}
```

할당자 자체를 설정하려면 아마도 이를 별도의 소스 파일에 저장하고 싶을 것입니다.

``` c++
struct yl_allocators_s _yl_allocators = {
    malloc,
    realloc,
    free
};

void yl_set_allocators(void *(*f_malloc)(size_t),
                       void *(*f_realloc)(void *, size_t),
                       void (*f_free)(void *))
{
    _yl_allocators.f_malloc = f_malloc;
    _yl_allocators.f_realloc = f_realloc;
    _yl_allocators.f_free = f_free;
}
```



### Memory Allocators and C++

이제 해당 함수가 설정되었으므로 C++에서 해당 함수를 사용하도록 하려면 어떻게 해야 할까요? 이 부분이 까다롭고 짜증납니다. yl_malloc을 통해 사용자 정의 클래스를 할당하려면 모든 클래스에 할당 연산자를 구현해야 합니다. 이는 상당히 반복적인 프로세스이기 때문에 클래스의 비공개 섹션에 배치할 수 있는 매크로를 작성하는 것이 좋습니다. 구현하는 기능이 공개임에도 불구하고 비공개로 전환해야 한다는 관례를 선택했습니다. 주로 데이터가 정의된 위치(내 경우에는 일반적으로 비공개) 근처에 있도록 그렇게 했습니다. 모든 수업의 비공개 섹션에 해당 매크로를 추가하는 것을 잊지 마세요.

``` c++
#define YL_IMPLEMENTS_ALLOCATORS \
public: \
    void *operator new(size_t size) { return yl_malloc(size); } \
    void operator delete(void *ptr) { yl_free(ptr); } \
    void *operator new[](size_t size) { return yl_malloc(size); } \
    void operator delete[](void *ptr) { yl_free(ptr); } \
    void *operator new(size_t, void *ptr) { return ptr; } \
    void operator delete(void *, void *) {} \
    void *operator new[](size_t, void *ptr) { return ptr; } \
    void operator delete[](void *, void *) {} \
private:
```

예제 사용법은 다음과 같습니다.

``` c++
class Task {
public:
    Task();
    ~Task();

private:
    YL_IMPLEMENTS_ALLOCATORS;
    // ...
};
```

이제 모든 클래스가 할당자 함수를 통해 할당됩니다. 하지만 STL 컨테이너를 사용하고 싶다면 어떻게 해야 할까요? 해당 컨테이너는 아직 함수를 통해 할당되지 않습니다. 특정 문제를 해결하려면 STL 프록시 할당자를 작성해야 합니다. 본질적으로 아무것도 하지 않기 때문에 인터페이스가 얼마나 복잡하기 때문에 이는 엄청나게 짜증나는 프로세스입니다.

``` c++
#include <limits>

template <class T>
struct proxy_allocator {
    typedef size_t size_type;
    typedef ptrdiff_t difference_type;
    typedef T *pointer;
    typedef const T *const_pointer;
    typedef T& reference;
    typedef const T &const_reference;
    typedef T value_type;

    template <class U>
    struct rebind {
        typedef proxy_allocator<U> other;
    };

    proxy_allocator() throw() {}
    proxy_allocator(const proxy_allocator &) throw() {}
    template <class U>
    proxy_allocator(const proxy_allocator<U> &) throw() {}
    ~proxy_allocator() throw() {}

    pointer address(reference x) const { return &x; }
    const_pointer address(const_reference x) const { return &x; }

    pointer allocate(size_type s, void const * = 0) {
        return s ? reinterpret_cast<pointer>(yl_malloc(s * sizeof(T))) : 0;
    }

    void deallocate(pointer p, size_type) {
        yl_free(p);
    }

    size_type max_size() const throw() {
        return std::numeric_limits<size_t>::max() / sizeof(T);
    }

    void construct(pointer p, const T& val) {
        new (reinterpret_cast<void *>(p)) T(val);
    }

    void destroy(pointer p) {
        p->~T();
    }

    bool operator==(const proxy_allocator<T> &other) const {
        return true;
    }

    bool operator!=(const proxy_allocator<T> &other) const {
        return false;
    }
};
```

그럼 계속하기 전에, 이 혐오스러운 것을 어떻게 사용합니까? 이와 같이:

``` c++
#include <deque>
#include <string>

typedef std::deque<Task *, proxy_allocator<Task *> > TaskQueue;
typedef std::basic_string<char, std::char_traits<char>,
                          proxy_allocator<char> > String;
```

사용하려는 모든 컨테이너를 정의하는 헤더를 만든 다음 올바른 할당자를 사용하기 위해 형식 정의 없이 STL의 다른 항목을 사용하지 않도록 하는 것이 좋습니다. 주의: 전역 new 연산자를 호출하는 것처럼 new TaskQueue()를 수행하지 마세요. 대신 사용자 지정 할당자가 있는 개체의 일부로 할당이 발생하도록 자신의 구조체에 멤버로 배치합니다. 아니면 그냥 스택에 올려두세요.

### Memory Allocation Failures

내 생각에는 메모리 할당 실패를 처리하는 가장 좋은 방법은 이를 처리하지 않는 것입니다. 할당이 실패하지 않도록 하세요. 달성하기 쉬운 라이브러리의 경우 최악의 시나리오에서 할당할 메모리 양을 알고 제한이 없는 경우 라이브러리 사용자에게 상황이 얼마나 나쁜지에 대한 아이디어를 얻을 수 있는 방법을 제공하십시오. 그 이유는 아무도 할당 실패를 다루지 않기 때문입니다.

처음에 STL은 전적으로 new 연산자에서 발생하는 std::bad_alloc에 의존하며(위에서는 수행하지 않았습니다. 헤헤) 오류를 처리할 수 있도록 오류를 표시합니다. 예외 처리 없이 라이브러리를 컴파일하면 라이브러리는 프로세스를 종료합니다. 꽤 끔찍한 일이지만, 조심하지 않으면 어쨌든 그런 일이 일어날 것입니다. 나는 malloc을 적절하게 처리하는 코드보다 malloc의 반환 값을 무시하는 코드를 더 많이 보았습니다.

그 외에도 일부 시스템에서는 malloc이 어쨌든 사용 가능한 메모리 양에 대해 전적으로 거짓말을 합니다. Linux는 실제 물리적 메모리로 백업할 수 없는 메모리에 대한 포인터를 기꺼이 제공합니다. 이 법정화폐 메모리 동작은 매우 유용하지만 일반적으로 할당 실패가 발생하지 않을 수 있다고 이미 가정해야 함을 의미합니다. 따라서 할당 오류를 보고하는 대신 C++를 사용하고 STL을 고수하고 싶다면 이를 포기하고 메모리가 부족해지지 않도록 하세요.

컴퓨터 게임의 일반적인 개념은 하위 시스템에 자체 할당자를 제공하고 주어진 것보다 더 많이 할당하지 않도록 하는 것입니다. EA는 할당 실패를 처리하기 위해 할당자를 권장하는 것 같습니다. 예를 들어 더 많은 메모리를 로드하지 못하는 경우 호출자에게 메모리 오류가 있음을 알리는 대신 필요하지 않은 일부 리소스(예: 캐시)를 확보할 수 있는지 확인합니다. 이는 C++ 표준이 할당자와 함께 제공하는 제한된 설계에서도 작동합니다.



### Building

이제 코드를 작성했으므로 사용자를 만족시키지 않고 어떻게 라이브러리를 구축할 수 있습니까? 당신이 나와 같다면 당신은 makefile이 소프트웨어를 구축하는 Unix 배경에서 왔습니다. 그러나 그것은 모두가 원하는 것이 아닙니다. Autotools/autoconf는 끔찍하고 끔찍한 소프트웨어이며 Windows 사용자에게 이를 제공하면 그들은 당신을 온갖 이름으로 부를 것입니다. 대신 Visual Studio 솔루션이 있는지 확인하세요.

선택한 도구 체인이 아니기 때문에 Visual Studio를 다루고 싶지 않다면 어떻게 해야 합니까? 솔루션과 makefile을 동기화 상태로 유지하려면 어떻게 해야 합니까? 해당 질문에 대한 대답은 premake 또는 cmake입니다. 둘 중 어느 것을 사용하느냐에 따라 크게 달라집니다. 둘 다 간단한 정의 스크립트에서 Makefiles, XCode 또는 Visual Studio 솔루션을 생성할 수 있습니다.

나는 cmake의 열렬한 팬이었지만 지금은 premake로 전환했습니다. 그 이유는 cmake에 사용자 지정해야 하는 일부 항목이 하드코딩되어 있기 때문입니다(예를 들어 Xbox 360용 Visual Studio 솔루션을 구축하는 것은 스톡 cmake로는 수행할 수 없는 작업입니다). Premake에는 cmake와 동일한 문제가 많이 있지만 거의 전적으로 lua로 작성되었으며 쉽게 사용자 정의할 수 있습니다. Premake는 본질적으로 Lua 인터프리터와 여러 Lua 스크립트를 포함하는 하나의 실행 파일입니다. 재컴파일하는 것은 쉽고 원하지 않는 경우 방법만 알면 premake 파일이 모든 것을 무시할 수 있습니다.



### Testing

마지막으로, 라이브러리를 어떻게 테스트합니까? C 및 C++로 작성된 테스트 도구는 분명히 사용할 수 있지만 실제로는 최고의 도구는 다른 곳에 있다고 생각합니다. 공유 라이브러리는 C나 C++만 즐기는 것이 아니라 다양한 언어로 사용할 수 있습니다. C++가 아닌 언어에서 API를 사용하여 API를 테스트하는 더 좋은 방법이 있을까요?

내 경우에는 Python을 사용하여 라이브러리를 테스트하고 있습니다. 더 중요한 점은 py.test와 CFFI를 사용하여 라이브러리를 테스트하고 있다는 것입니다. 이는 C/C++에서 직접 수행하는 것보다 몇 가지 큰 장점이 있습니다.

가장 큰 장점은 반복 속도가 빨라진다는 것입니다. 테스트를 전혀 컴파일할 필요가 없으며 그냥 실행됩니다. 컴파일 단계가 생략될 뿐만 아니라 Python의 동적 타이핑과 py.test의 우수한 Assert 문을 활용할 수도 있습니다. 나는 정보를 인쇄하고 내 라이브러리와 Python 간에 데이터를 변환하기 위해 도우미를 직접 작성하며 좋은 오류 보고의 모든 이점을 얻습니다.

두 번째 장점은 좋은 격리입니다. pytest-xdist는 별도의 프로세스에서 각 테스트를 실행하는 py.test에 --boxed 플래그를 추가하는 py.test용 플러그인입니다. 이는 세그폴트 때문에 충돌이 발생할 수 있는 테스트가 있는 경우 매우 유용합니다. 시스템에서 코어 덤프를 활성화하면 나중에 gdb에서 segfault를 로드하고 무엇이 잘못되었는지 알아낼 수 있습니다. 이는 또한 어설션이 실패하고 코드가 정리를 건너뛰기 때문에 발생하는 메모리 누수를 처리할 필요가 없기 때문에 정말 잘 작동합니다. OS는 각 테스트마다 별도로 정리합니다. 불행하게도 이는 fork() 시스템 호출을 통해 구현되었으므로 현재 Windows에서는 제대로 작동하지 않습니다.

그렇다면 CFFI와 함께 라이브러리를 어떻게 사용합니까? 두 가지 작업을 수행해야 합니다. 공개 헤더 파일에 다른 헤더가 포함되어 있지 않은지 확인해야 합니다. 그렇게 할 수 없다면 포함을 비활성화하는 정의(예: YL_NOINCLUDE)를 추가하세요.

이것이 CFFI가 작동하는 데 필요한 전부입니다.

``` c++
import os
import subprocess
from cffi import FFI

here = os.path.abspath(os.path.dirname(__file__))
header = os.path.join(here, 'include', 'yourlibrary.h')

ffi.cdef(subprocess.Popen([
    'cc', '-E', '-DYL_API=', '-DYL_NOINCLUDE',
    header], stdout=subprocess.PIPE).communicate()[0])
lib = ffi.dlopen(os.path.join(here, 'build', 'libyourlibrary.dylib'))
```

테스트 옆에 testhelpers.py라는 파일을 배치하세요.

분명히 이는 OS X에서만 작동하는 간단한 버전이지만 다른 운영 체제로 확장하는 것도 간단합니다. 본질적으로 이것은 C 전처리기를 호출하고 몇 가지 추가 정의를 추가한 다음 그 반환 값을 CFFI 파서에 제공합니다. 그러면 작업할 수 있는 아름다운 라이브러리가 만들어집니다.

다음은 그러한 테스트가 어떻게 생겼는지 보여주는 예입니다. test_something.py라는 파일에 넣고 py.test가 실행하도록 하세요.

``` c++
import time
from testhelpers import ffi, lib

def test_basic_functionality():
    task = lib.yl_task_new()
    while lib.yl_task_is_pending(task)
        lib.yl_task_process(task)
        time.sleep(0.001)
    result = lib.yl_task_get_result_string(task)
    assert ffi.string(result) == ''
    lib.yl_task_free(task)
```

py.test에는 다른 장점도 있습니다. 예를 들어 테스트 간에 재사용할 수 있는 공통 리소스를 설정할 수 있는 고정 장치를 지원합니다. 예를 들어, 라이브러리를 사용하려면 일종의 컨텍스트 개체를 생성하고 이에 대한 공통 구성을 설정한 후 나중에 파기해야 하는 경우 매우 유용합니다.

그렇게 하려면 다음 내용으로 conftest.py 파일을 생성하세요.

``` py
import pytest
from testhelpers import lib, ffi

@pytest.fixture(scope='function')
def context(request):
    ctx = lib.yl_context_new()
    lib.yl_context_set_api_key(ctx, "my api key")
    lib.yl_context_set_debug_mode(ctx, 1)
    def cleanup():
        lib.yl_context_free(ctx)
    request.addfinalizer(cleanup)
    return ctx
```

지금 이것을 사용하려면 테스트 함수에 context라는 매개변수를 추가하기만 하면 됩니다.

``` python
from testhelpers import ffi, lib

def test_basic_functionality(context):
    task = lib.yl_task_new(context)
    ...
```



### Summary

평소보다 길이가 길어지므로 기본 공유 라이브러리를 구축할 때 명심해야 할 가장 중요한 사항에 대한 간략한 요약은 다음과 같습니다.

- C나 C++로 작성하세요. CPU와 메모리를 차지하는 전체 런타임을 가져오는 언어로 작성하는 데 열중하지 마세요.
- 피할 수 있다면 전역 상태는 없습니다!
- 공개 헤더에 일반 유형을 정의하지 마세요.
- 공개 헤더에 windows.h와 같은 이상한 헤더를 포함하지 마세요.
- 헤더에 포함 내용을 모두 가볍게 살펴보세요. 정의를 통해 모든 포함을 비활성화하는 방법을 추가하는 것을 고려해보세요.
- 네임스페이스를 잘 관리하세요. 노출되기를 원하지 않는 기호를 노출하지 마십시오.
- 노출하려는 각 기호 앞에 YL_API와 같은 매크로를 만듭니다.
- 안정적인 ABI를 구축해 보세요.
- 구조체에 너무 열중하지 마세요
- 사람들이 메모리 할당자를 사용자 정의할 수 있게 해주세요. '컨텍스트' 개체별로 수행할 수 없다면 최소한 라이브러리별로 수행하십시오.
- STL을 사용할 때는 항상 할당자를 추가하는 typedef를 통해서만 주의하십시오.
- 사용자에게 선호하는 빌드 도구를 사용하도록 강요하지 마세요. 항상 라이브러리 사용자가 Visual Studio 솔루션과 메이크파일을 찾을 수 있는지 확인하세요.

That's it! Happy library building!