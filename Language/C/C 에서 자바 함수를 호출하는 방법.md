# C 에서 자바 함수를 호출하는 방법.

C 에서 Java 함수를 호출하려면 JDK(Java Development Kit)에서 제공하는 JNI(Java Native Interface)를 사용할 수 있습니다. 일반적인 단계는 다음과 같습니다.

- Java 코드 작성: 
  - C 에서 호출하려는 메소드로 Java 클래스를 작성하십시오. j avac로 클래스를 컴파일하여 클래스 파일을 작성하십시오.

- 헤더 파일 생성: 
  - javah 도구를 사용하여 Java 클래스에 대한 헤더 파일을 생성합니다. 
  - 이 헤더 파일에는 C에서 호출해야 하는 함수 시그니처가 포함되어 있습니다.

- C 코드 작성: 
  - Java 함수를 호출할 C 코드를 작성하십시오. 
  - 코드는 javah에 의해 생성된 헤더 파일을 포함해야 합니다. 
  - JNI는 JVM을 로드하고, 호출하려는 클래스와 메소드를 찾고, 메소드를 호출하는 기능을 제공합니다.

- C 코드 컴파일: 
  - C 코드를 컴파일하고 JNI 라이브러리와 연결합니다.

- 프로그램 실행: 
  - C 프로그램을 실행하면 Java 함수가 호출됩니다.

다음은 C에서 문자열 인수를 사용하여 hello라는 Java 함수를 호출하는 샘플 코드입니다.

자바 코드:

``` java
public class HelloWorld {
    public static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
```



C 코드:

``` c
#include <jni.h>

int main() {
    JavaVM *jvm;               // JVM(자바 가상 머신)에 대한 포인터
    JNIEnv *env;               // 네이티브 인터페이스에 대한 포인터
    JavaVMInitArgs vm_args;    // 초기화 인수
    JavaVMOption options[1];
    options[0].optionString = "-Djava.class.path=<path-to-class>"; // 클래스 파일 경로 설정
    vm_args.version = JNI_VERSION_1_6;
    vm_args.nOptions = 1;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_TRUE;
    // JVM 만들기
    jint rc = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
    if (rc != JNI_OK) {
        // handle error
    }
    // 클래스 찾기
    jclass cls = (*env)->FindClass(env, "HelloWorld");
    if (cls == NULL) {
        // handle error
    }
    // 함수 찾기
    jmethodID mid = (*env)->GetStaticMethodID(env, cls, "hello", "(Ljava/lang/String;)V");
    if (mid == NULL) {
        // handle error
    }
    // 함수 호출
    jstring name = (*env)->NewStringUTF(env, "World");
    (*env)->CallStaticVoidMethod(env, cls, mid, name);
    // JVM 종료
    (*jvm)->DestroyJavaVM(jvm);
    return 0;
}
```

C 코드의 **path-to-class를 클래스 파일(파일 자체가 아님)이 포함된 디렉토리의 경로로** 바꿔야 합니다.  또한 JVM 및 JNI 라이브러리가 시스템에 올바르게 설치 및 구성되어 있는지 확인하십시오.



---

C에서 Java 함수를 호출하려면 JNI(Java Native Interface)를 사용해야 합니다. JNI는 Java 코드가 C, C++ 또는 기타 프로그래밍 언어로 작성된 네이티브 코드를 호출할 수 있도록 하는 프로그래밍 프레임워크이며 그 반대의 경우도 마찬가지입니다.

다음은 C에서 Java 함수를 호출하는 일반적인 단계입니다.

- C에서 호출하려는 메소드로 Java 클래스를 작성하십시오. 메소드는 C에서 구현됨을 의미하는 '네이티브'로 선언되어야 합니다.
- JNI(Java Native Interface) 도구 'javah'를 사용하여 Java 클래스를 컴파일하고 헤더 파일을 생성합니다. 헤더 파일에는 C에 대한 함수 서명이 포함됩니다.
- Java 클래스에 선언된 네이티브 메서드를 구현하는 C 함수를 작성합니다. 함수는 JNI API를 사용하여 JVM(Java Virtual Machine)에 액세스하고 Java 메서드를 호출해야 합니다.
- JVM(Java Virtual Machine)을 로드하고 JNI 함수를 사용하여 호출할 클래스와 메서드를 찾습니다.
- JNI API를 사용하여 Java 메서드를 호출합니다.



### 다음은 C에서 Java 함수를 호출하는 방법의 예입니다.

- 기본 메서드를 사용하여 Java 클래스를 만듭니다.

  ``` java
  public class MyJavaClass {
      public native void myJavaMethod();
  }
  ```

  

- Java 클래스를 컴파일하고 헤더 파일을 생성합니다.

  ``` bash
  javah MyJavaClass
  ```

  

- 네이티브 메서드를 구현하는 C 함수를 작성합니다.

  ``` c
  #include <jni.h>
  #include "MyJavaClass.h"
  
  JNIEXPORT void JNICALL Java_MyJavaClass_myJavaMethod(JNIEnv *env, jobject obj) {
      // Implementation code goes here
  }
  
  ```

  

- JVM을 로드하고 클래스와 메서드를 찾습니다.

  ``` c
  JavaVM *jvm; // Pointer to the JVM (initialized elsewhere)
  JNIEnv *env; // Pointer to native interface (initialized elsewhere)
  
  jint res = jvm->AttachCurrentThread((void**) &env, NULL);
  if (res != JNI_OK) {
      // Error handling
  }
  
  jclass cls = env->FindClass("MyJavaClass");
  if (cls == NULL) {
      // Error handling
  }
  
  jmethodID method = env->GetMethodID(cls, "myJavaMethod", "()V");
  if (method == NULL) {
      // Error handling
  }
  ```

  

- Java 메서드를 호출합니다.

  ``` c
  env->CallVoidMethod(obj, method);
  ```

  

이것은 단지 기본적인 예일 뿐이며 JNI 사용과 관련된 더 많은 세부 정보가 있습니다. 자세한 내용은 JNI의 공식 Java 문서(https://docs.oracle.com/en/java/javase/16/docs/specs/jni/index.html)를 참조하십시오.

