# OpenMP

#include <omp.h>      // OpenMP 에 대한 함수가 정의된 헤더파일

컴파일 방법 
  - Visual C/CPP  : cl /openmp test.c //  /openmp 스위치 를 이용한 컴파일
  - linux : gcc -fopenmp -o openmp_test openmp_test.c

directive 
  - #pragma omp parallel for        // for문을 병렬로 수행하기 위한 
    -- private, reduction, shredule 등 sub option 이 있다.


function
    •	omp_set_num_threads : 병렬 영역에서 사용할 스레드 개수 설정
    •	omp_get_num_threads() : 병렬 영역 안에서 호출되어 생성된 스레드의 개수를 리턴
    •	omp_get_thread_num() : 병렬 영역 안에서 생성된 스레드들의 ID를 리턴
    •	omp_get_max_threads() : 병렬 영역에서 사용 가능한 최대 스레드 개수 리턴
    •	omp_set_nested() : nested 병렬성 지원 여부 결정
    •	omp_set_dynamic() : 순차 영역에서 호출되어 이어지는 병렬 영역 들의 스레드 개수 할당을 동적으로 수행
    •	omp_get_nested() : 호출되는 시점의 nested 병렬성 설정 여부 확인
    •	omp_get_dynamic() : 스레드 할당 방식이 동적인지 확인
    •	omp_in_parallel() : 호출된 지점이 순차 영역인지 병렬 영역인지 확인
    •	omp_get_num_procs() : 프로그램에서 사용 가능한 물리적 프로세서의 총 개수 확인


- 상호 배제 동기화
    •	critical : 병렬영역 안에서 critical section을 지정하면 그 영역은 하나의 스레드에서 실행 된다.
    •	atomic : 하나의 스칼라 변수를 경신하는 지정된 단일 문장(single statement)에 대해 여러 스레드가 접근하는 것을 방지한다. 
        mini-critical section의 역할과 비슷하다.
    •	flush : 하나의 메모리에 여러 개의 스레드가 경합을 벌이는 경우 그 정합성을 유지시켜 주는 지시어


- 이벤트 동기화
    •	barrier : 모든 스레드들이 barrier에 도달할 때까지 대기하게 된다.
    •	ordered : ordered section 내부의 루프 실행을 순차적으로 진행한다.
    •	master : master section을 마스터 쓰레드에서만 실행시킨다.
    •	taskwait : 모든 task가 taskwait에 도달할 때까지 대기한다.

- 데이터의 유효 범위 지시자
    •	private : 지정된 변수를 스레드끼리 공유하는 것 방지한다. 주로 스칼라값, 여러 쓰레드들이 동시에 접근해서 쓰기를 할경우 사용.
    •	shared : 지정된 변수를 모든 스레드가 공유하도록 한다. 디폴트.
    •	default : private 또는 shared로 선언되지 않은 변수의 기본적인 유효범위 지정한다.
    •	firstprivate : private 변수처럼 각 쓰레드에 개별적으로 변수 생성하고 각 스레드 마다 순차 영역에서 가져온 값으로 초기화한다.
    •	lastprivate : private 변수처럼 각 스레드에 개별적으로 변수 생성하고 순차 실행에서 마지막 계산에 해당되는 값. 즉, 
                  마지막 반복실행의 값을 마스터 스레드에게 넘겨준다.
    •	reduction : reduction 변수는 shared이고 다중 스레드에서 병렬로 수행된 계산 결과를 환산해 최종 결과를 마스터 스레드로 내 놓는다.
    •	schedule : 작업의 균등 분배를 위해 schedule 을 사용한다.

- Environment Variables
    •	OMP_NUM_THREADS : 병렬영역에서 사용 가능한 최대 스레드 개수 지정
    •	OMP_SCHEDULE : schedule type이 runtime으로 지정된 루프들에게 scheduling 방식 지정
    •	OMP_DYNAMIC : 스레드 개수의 동적할당 여부 결정
    •	OMP_NESTED : nested 병렬성 지원 여부 결정



## Open MP 예제

```c++
#include <windows.h>
#include <time.h>

/*
   Compile and test : cl /openmp OpenMPtest.c
   OpenMpTest
 */

int main( int argc, char** argv)
{
	int i = 0;
	int Loop = 10000;

	clock_t startTime = clock(); 
	omp_set_num_threads(4);

	printf( "openmp get max threads: [%d]\n", omp_get_max_threads() );

    //#pragma omp parallel for schedule(static, 10)
    #pragma omp parallel for
	for( i = 0; i < 80; i++ )
	{
		printf( "[%d-%d] Hello World\n", omp_get_thread_num(), i );
	}

	printf("Duration Time: %d\n", clock() - startTime);

	return 0;
}

```



## #pragma omp parallel

### Purpose

omp parallel 지시문은 컴파일러에게 선택된 코드 블록을 병렬화하도록 지시합니다.



### Syntax

```
                            .-,------.   
                            V        |   
>>-#--pragma--omp parallel----clause-+-------------------------><
```



### Parameters

parameter 는 다음 중 하나입니다.

#### **if (exp)**

If 인수가 지정되면 exp로 표시되는 스칼라 표현식이 런타임시 0이 아닌 값으로 평가되는 경우에만 프로그램 코드가 병렬로 실행됩니다. 하나의 if 절만 지정할 수 있습니다.



#### **private (list)**

목록에서 데이터 변수의 범위가 각 스레드에 대해 개인임을 선언합니다. 목록의 데이터 변수는 쉼표로 구분됩니다.



#### **firstprivate (ist)**

목록에서 데이터 변수의 범위가 각 스레드에 대해 개인임을 선언합니다. 각각의 새로운 개인 객체는 명령문 블록 내에 묵시적 선언이있는 것처럼 원래 변수의 값으로 초기화됩니다. 목록의 데이터 변수는 쉼표로 구분됩니다.



#### **num_threads (int_exp)**

Int_exp의 값은 병렬 영역에 사용할 스레드 수를 지정하는 정수 표현식입니다. 스레드 수의 동적 조정도 사용 가능한 경우 int_exp는 사용할 최대 스레드 수를 지정합니다.



#### **shared (list)**

목록에서 쉼표로 구분 된 데이터 변수의 범위가 모든 스레드에서 공유되도록 선언합니다.



#### **default (shared | none)**

각 스레드에서 변수의 기본 데이터 범위를 정의합니다. Omp 병렬 지시문에는 하나의 기본 절만 지정할 수 있습니다. Default (shared)를 지정하는 것은 shared (list) 절에 각 변수를 표시하는 것과 같습니다.

Default (none)을 지정하려면 병렬화 된 명령문 블록에 표시되는 각 데이터 변수가 다음과 같은 변수를 제외하고 데이터 범위 절에 명시 적으로 나열되어야합니다.

- const-qualified,
- 동봉 된 데이터 범위 속성 절에 지정되거나
- 지시어에 대한 해당 omp 또는 omp 병렬에 의해서만 참조되는 루프 제어 변수로 사용됩니다.



#### **copyin (list)**

List에 지정된 각 데이터 변수에 대해 마스터 스레드의 데이터 변수 값이 병렬 영역의 시작 부분에서 스레드 개인 사본에 복사됩니다. 목록의 데이터 변수는 쉼표로 구분됩니다.

Copyin 절에 지정된 각 데이터 변수는 threadprivate 변수 여야합니다.



#### **reduction (operator: list)**

지정된 연산자를 사용하여 목록의 모든 스칼라 변수에 대해 축소를 수행합니다. 목록의 감소 변수는 쉼표로 구분됩니다.
각 스레드에 대해 목록에있는 각 변수의 개인 사본이 작성됩니다. 명령문 블록의 끝에서, 감소 변수의 모든 개인 사본의 최종 값은 운영자에게 적절한 방식으로 결합되고 결과는 공유 감소 변수의 원래 값으로 다시 배치됩니다. 예를 들어, max 연산자가 지정되면 원래 축소 변수 값은 다음 표현식을 사용하여 개인 사본의 최종 값과 결합됩니다.

```
original_reduction_variable = original_reduction_variable < private_copy ?
private_copy : original_reduction_variable; 
```

감소 절에 지정된 변수의 경우 다음 조건을 만족해야합니다.

- 운영자에게 적합한 유형이어야합니다. Max 또는 min 연산자가 지정되면 변수는 long, short, signed 또는 unsigned가 있거나없는 다음 유형 중 하나 여야합니다.

  - _Bool
  - char
  - int
  - float
  - double
  
- 둘러싼 컨텍스트에서 공유해야합니다.
  
- Const 한정되지 않아야합니다.
  
- 포인터 유형이 없어야합니다.



  ### Usage

병렬 영역이 발견되면 논리 스레드 팀이 형성됩니다. 팀의 각 스레드는 작업 공유 구성을 제외하고 병렬 영역 내의 모든 명령문을 실행합니다. 작업 공유 구성 내의 작업은 팀의 스레드간에 분산됩니다.

루프를 병렬화하려면 루프 반복이 독립적이어야합니다. 병렬화 된 명령문 블록 끝에는 내재 된 장벽이 있습니다.

기본적으로 중첩 된 병렬 영역은 직렬화됩니다.



  

  

  

  

  