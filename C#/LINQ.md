### C# 에서 LINQ 사용 하기

<br/>

C# 에서 LINQ 를 사용하는 방법을 기술 한다.



#### 1. LINQ 작업 개요.

모든 LINQ 쿼리 작업은 다음과 같은 세 가지 고유한 작업으로 구성됩니다.

```
1. 데이터 소스 가져오기.
2. 쿼리 만들기.
3. 쿼리 실행.
```



 #### 2. 간단한 LINQ 예제

``` C#
class IntroToLINQ
{        
	static void Main()
	{
		// The Three Parts of a LINQ Query:
		//  1. Data source.
		int[] numbers = new int[7] { 0, 1, 2, 3, 4, 5, 6 };

		// 2. Query creation.
		// numQuery is an IEnumerable<int>
		var numQuery =
			from num in numbers
			where (num % 2) == 0
			select num;

		// 3. Query execution.
		foreach (int num in numQuery)
		{
			Console.Write("{0,1} ", num);
		}
	}
}
```



#### 3. Database 의 SQL 과 차이점

###### 3.1 Database 의 SELECT 예.

```sql
SELECT  NUM 
FROM    TABLE_NAME
WHERE   NUM > 1;
```



###### 3.2 C# LINQ 예

``` C#
from num in numbers
where (num % 2) == 0
select num;
```



일반 SQL 에서는 SELECT 문장이 앞에 나오지만 LINQ 에서는 뒤쪽에 나온다.

그외 여러가지 차이가 있지만 아래 에서 LINQ 사용 법 및 예제를 통해서 하나씩 읽혀 나가기로 한다.



#### 4. LINQ 예제

###### 4.1 간단한 쿼리 식 및 메서드 기반 쿼리로서 작성된, 의미상 동등한 쿼리

```C#
class QueryVMethodSyntax
{
	static void Main()
	{
		int[] numbers = { 5, 10, 8, 3, 6, 12};

		//Query syntax:
		IEnumerable<int> numQuery1 = 
			from num in numbers
			where num % 2 == 0
			orderby num
			select num;

		//Method syntax:
		IEnumerable<int> numQuery2 = numbers.Where(num => num % 2 == 0).OrderBy(n => n);

		foreach (int i in numQuery1)
		{
			Console.Write(i + " ");
		}
        
		Console.WriteLine(System.Environment.NewLine);
        
		foreach (int i in numQuery2)
		{
			Console.Write(i + " ");
		}
		
		// Keep the console open in debug mode.
		Console.WriteLine(System.Environment.NewLine);
		Console.WriteLine("Press any key to exit");
		Console.ReadKey();
	}
}
/*
	Output:
	6 8 10 12
	6 8 10 12
 */
```



사용 예제 및 관련 자료는 계속 Update 예정.