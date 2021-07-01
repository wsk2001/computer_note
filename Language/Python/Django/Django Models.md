# Django Models 



# Django Models 1편 - Quick Example

## Models - Quick example



Django 공식문서에 나와있는, models 에 대한 부분을 둘러봅니다.


공식 문서에는 완벽한 번역본이 존재하지 않고, 설명이 너무 불충분해서, 초반에 공부할때에 많은 어려움이 있었습니다.

물론,, 지금도 쉽지는 않지만.. 어쨋든, Django models 에 대한 포스팅을 시작합니다.





> **Models (모델)**
>
> 모델은 당신의 데이터에 대한 정보를 가지고 있고, 당신의 데이터에 대한 정의를 내리는 하나의 소스 입니다.
>
> 모델은 당신에게 필요하고 저장하고자 하는 필드들과 동작들에 대한 정보를 포함하고 있습니다
>
> 각 모델들은, 보통 하나의 데이터 베이스 테이블로 매핑 됩니다.﻿





## 기본 사항들



1. 각 모델은 Python 클래스 이고, `django.db.models.Model` 의 서브 클래스 이다
2. 각 모델의 속성들은 데이터베이스 필드를 표현 합니다
3. 이 모든 정보들을 가지고, Django 는 자동으로 생성된 database-access API 를 제공합니다.





## 빠른 예제



아래 예제는, first_name, last_name 정보를 가지는 Person 모델을 정의 합니다.



```
from django.db import models 


class Person(models.Model);
	first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
```



first_name 과 last_name 은 모델의 필드들입니다. 각 필드들은, 클래스의 속성들로 표현되어 있습니다.



그리고, 각 속성들은 database 컬럼들과 메칭 됩니다.



> 처음에 공부할때, 데이터베이스의 Column 그리고 Field 에 대해서 잘 몰라서
>
> 이해가 안갔었는데, 아래 그림을 가져와서 참고 합니다.


![img](https://djangojeng-e.github.io/2020/07/30/Django-Models-1%ED%8E%B8-Quick-Example/image1.png)

- 위의 모델에서 class Person 이라는 클래스는 데이터베이스 테이블명이 됩니다
- first_name 은 해당 테이블의 필드명이 되고, 최대 길이는 30자 입니다
- last_name 은 해당 테이블의 필드명이 되고, 최대 길이는 30자 입니다



> 위의 Person 모델은 아래와 같은 데이터베이스 테이블을 생성합니다.



```
CREATE TABLE myapp_person (
    "id" serial NOT NULL PRIMARY KEY,
    "first_name" varchar(30) NOT NULL,
    "last_name" varchar(30) NOT NULL
);
```



## 기술적인 노트



- 테이블의 이름은, myapp_person 으로 지정되어 있는데, 이것은 자동적으로 메타데이터에서 따옵니다. 하지만, 이 이름은 오버라이드 될수 있습니다
- id 필드는 자동으로 추가됩니다. 이 동작 방식도 수정될수는 있습니다
- 이번 예제에서 사용된 CREATE TABLE SQL 구문은, PostgreSQL 구문을 사용한 형식입니다. 하지만, django 는 데이터베이스 백앤드를 당신의 settings.py 파일에 명시되어 있는 데이터베이스에 맞추어 사용합니다.



# Django Models 2편 - using models

## Using models (모델 사용하기)



### 앱 등록하기



모델을 정의 한 후에 우리는, Django 에게 이 모델을 사용할것이라고 얘기해주어야 합니다.


Django 에게 이 모델을 사용할것이라고 알려주기 위해서는,



> settings.py 에 있는 INSTALLED_APPS 에 해당 모듈명을 추가해 주어야 합니다



예를들어, `python manage.py startapp usemodel` 명령어를 통해, usemodel 이라는 앱을 생성하였다고 가정하면, settings.py 파일에 INSTALLED_APPS 안에, 생성된 모델이 아래와 같이 등록 되어 있어야 합니다.



```
INSTALLED_APPS = [
    #...
    'usemodel'
]
```





### makemigrations



INSTALLED_APPS 에 새로운 앱을 추가해 주고, 해당 앱에 대한 모델을 작성해 주었으면,

데이터베이스에 적용을 해주어야 합니다. 하지만, 적용전에 `python manage.py makemigrations` 명령어를 통해서, 데이터베이스 적용 전에 migrations 파일을 만들어 줍니다.



```
python manage.py makemigrations
```



> 해당 앱의 모델을 아직 작성하지 않아도, 위의 makemigrations 명령어는 정상으로 실행됩니다.
>
> 왜냐하면, 새로운 모델이 생성되었기 때문입니다.
>
> 이렇게 앱과 모델을 등록한다음에는, 앱의 모델을 변경할때마다,
>
> makemigrations 와 migrate 명령어를 실행하여, 데이터베이스에 변경사항을 적용 해주어야 합니다.



### migrate



makemigrations 명령어를 실행 시켜 주었으면, `migrate` 명령어를 통해서, 데이터 베이스에 실제 적용을 합니다.



```
python manage.py migrate
```





## 모델을 사용하는 순서



1. 앱생성
2. 앱을 settings.py 에 등록
3. `python manage.py makemigrations`
4. `python manage.py migrate`



# Django Models 3편 - Fields (Field Types)

## Fields



모델에서 가장 중요하고, 가장 요구되는 사항중 하나는.


모델에서 정의하는 데이터베이스 필드들의 리스트 입니다.



필드는 클래스 속성으로 정의되어 있습니다.



> 주의사항,
>
> 필드명은, clean, save, delete 와 같은 model API 와 충돌할수 있는 이름으로 지으면 안됩니다.





예시 코드는 아래와 같습니다.



```
from django.db import models 

class Musician(models.Model):
    first_name = models.CharField(max_length=50)  # Musician class 의 속성 
    last_name = models.CharField(max_length=50)
    instrument = models.CharField(max_length=100)
    
class Album(models.Model):
    artist = models.ForiengKey(Musician, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    release_date = models.DateField()
    num_stars = models.IntegerField()
```





## Field Types



각 모델안에 필드들은 적절한 필드클래스의 인스턴스여야 합니다.



Django 는 필드 클래스에 정의되어 있는 type 들을 사용해서 여러가자 사항들을 결정합니다.



- INTEGER, VARCHAR, TEXT 같은 컬럼 타입을 결정하여, 데이터베이스가 어떤 종류의 데이터를 저장할건지 결정합니다
- 폼 필드를 출력할때 어떤 기본 HTML 위젯을 사용할지 결정합니다
- DJango 의 어드민과 자동 생성되는 폼에서 사용할 최소한의 검증사항들을 결정합니다.



Django 는 여러개의 내장 필드 타입들을 지원합니다. 필드타입의 리스트를 확인하고 싶다면, model field reference 문서를 확인하면 됩니다. Django 의 내장 필드타입으로 충분하지 않다면, 본인 스스로 커스텀 필드를 작성할수도 있습니다. wrting custom model fields 문서를 참조하면 됩니다.



## 마치며..



Django 에서 모델을 작성할때, models.py 안에 작성하는 클래스는 데이터베이스 테이블이 되고.


클래스 안의 속성들을 통해서 필드명과 데이터 타입을 정의할수 있습니다.



# Django Models 4편 - Fields (Field Options)

## Field Options



각각의 필드들은, 해당 필드에 특화된 독립 변수들을 갖습니다.



> 예를들면,
>
> CharField 와 CharField 의 서브클래스들은,
>
> max_length 같은, 데이터를 저장하기 위해서 길이를 정의하는 독립된 변수들을 갖게 됩니다.



또한, 모든 필드 타입들에 유효한, 공통 독립 변수들도 존재합니다.


모든 변수들은, 선택적으로 사용할수 있고, reference 문서에 완벽하게 설명이 되어 있지만.


**가장 공통적으로 많이 사용되는것들을 정리하고 넘어갑니다.**





### null



`null=True` 일 경우에, Django 는 빈값을 NULL 로 데이터베이스에 저장합니다.

기본값은 False 입니다.



### blank



`blank=True` 일 경우에, 필드는 비어있는 값이 허용됩니다. 기본값은 False 입니다.





## NULL vs Blank?



> blank 와 null 은 다르다는것을 숙지해야 합니다.
>
> null 은 순수하게 데이터베이스 측면에서 생각하는 값이고,
>
> blank 는 검증 혹은 유효성 검사 측면에서 바라보는것입니다.
>
> null 은 필드의 값이 NULL (정보없음) 으로 저장되는것을 허용하고,
>
> blank 는 필드가 폼(입력 양식) 에서 비어있는 채로 저장되는것을 허용합니다.
>
> blank = false 이면, 해당 필드가 비어있는채로 저장되는것을 허용하지 않습니다.
>
> 비슷하게도, null=false 일 경우에, 필드의 값이 NULL (정보없음) 으로 저장되는것을 허용하지 않습니다.



### choices



2개로된 튜플은, 필드의 선택값으로 사용할수 있습니다.


튜플이 주어지면, 기본 양식 폼 위젯은 문자열 필드 대신에 select box 가 될것이고, 튜플에 들어있는 선택들로, 선택이 제한됩니다.



```
YEAR_IN_SCHOOL_CHOICES = [
    ('FR', 'Freshman'), 
    ('SO', 'Sophomore'), 
    ('JR', 'Junior'), 
    ('SR', 'Senior'), 
    ('GR', 'Graduate'),
]
```



여기서 알아야 할게,



> Choices 의 순서가 바뀔대마다, 새로운 migration 이 생겨납니다.



> 또한,
>
> 튜플의 첫번째 값은, 데이터베이스에 저장되는 값이고,
>
> 튜플의 두번째 값은, 필드의 입력양식 위젯에 표시됩니다. 즉, 이 모델을 가지고 폼 (입력양식) 을 만들었을때, 입력양식 위젯에 표시되는것은, 튜플의 두번째 값이고.
>
> 실제 사용자가 이 입력양식을 통해서 값을 입력하여 데이터베이스에 저장할시에는, 튜플의 첫번째 값으로 저장이 됩니다.





모델 인스턴스가 주어졌을때, Choices 를 가진 필드의 표시값은, get_shirt_size_display() 메서드를 사용해서 접근할수 있습니다. 아래 예시를 통해서 알아봅니다.



```
from django.db import models 

class Person(models.Model):
    SHIRT_SIZES = (
    	('S', 'Small'),
        ('M', 'Medium'),
        ('L', 'Large'),
    )
    name = models.CharField(max_length=60)
    shirt_size = models.CharField(max_length=1, choices=SHIRT_SIZES)
```



#### choices 값의 표시





- shirt_size 가 데이터베이스에 저장될때에는, 튜플의 첫번째 값들인, S, M, L 로 저장이 되고
- shirt_size 가 폼에 표시될때는, Small, Medium, Large 로 표시가 됩니다
- 이렇게 데이터베이스 외의 위치에서 표시되는 값들은, get_shirt_size_display() 로 호출할수 있습니다.





> 실제로 model 을 작성해서 테스트 해봅니다
>
> shell 에 접속하여, 아래 코드를 입력합니다.



```
# shell 에 접속 
python manage.py shell 

# shell 접속후에 실습 

In [1]: p = Person(name="Superman", shirt_size="L")                                   

In [2]: # Person의 name이 Superman, 셔츠 사이즈가 L 인 인스턴스 생성                                                                                                      

In [3]: p.save()                                                                                                                                                          

In [4]: #  p.save() 를 호출하여, p 를 데이터베이스에 저장                                                                                                                 

In [5]: p.shirt_size                                                                                                                                                      
Out[5]: 'L'

In [6]: p.get_shirt_size_display()                                                                                                                                        
Out[6]: 'Large'

In [7]: # p의 shirt_size의 display 값을 표시
```



어드민에서 SHIRT_SIZE 는 Large 라고 표시됩니다.


![img](https://djangojeng-e.github.io/2020/08/02/Django-Models-4%ED%8E%B8-Fields-Field-Options/image1.png)

하지만, 정작 DB 에는, L 로 저장되어 있는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/08/02/Django-Models-4%ED%8E%B8-Fields-Field-Options/image2.png)



#### 간단하게 choices 정의하기



enumeration 클래스들을 사용해서 간단한 방법으로 choices 들을 정의할수 있습니다



```
from django.db import models

class Runner(models.Model);
	MedalType = models.TextChoices('MedalType', 'GOLD SILVER BRONZE')
	# 튜플에서 두번째 값인, 'GOLD SILVER BRONZE' 가 선택할수 있는값이 됨 
	name = models.CharField(max_length=60)
	medal = models.CharField(blank=True, choices=MedalType.choices, max_length=10)
```



> Runner 를 추가할때, GOLD SILVER BRONZE 셋중 하나를 선택해서 추가할수 있습니다.


![img](https://djangojeng-e.github.io/2020/08/02/Django-Models-4%ED%8E%B8-Fields-Field-Options/image3.png)





### default



필드의 기본값을 지정합니다. 기본값은 특정 값일수도 있고, 호출 가능한 객체일수도 있습니다.

호출 가능한 객체라면, 새로운 객체가 생성될때마다, 호출이 됩니다.

예)

```
from django.db import models 

class Djangojeng_e(models.Model);
	# 이름이 장고쟁이인 객체만 생성할수 있음 
	name = models.CharFied(max_length=20, default='장고쟁이')
```



### help_text



추가적인 help 텍스트가 입력양식 위젯과 함께 표시가 됩니다. 필드가 입력양식에서 사용되지 않더라도.


문서화 할때에 유용합니다



### primary_key



`primary_key = True` 일때, 해당 필드는 모델의 primary key 가 됩니다.



모델안에 어떻나 필드도 primary_key = True 라고 명시하지 않은 경우에는, django가 자동적으로 IntegerField 를 추가하여, primary key 를 지니게 합니다. 딸사ㅓ, primary_key = True 라고 어떠한 필드에도 설정해줄 필요는 없습니다. 하지만, 기본 primary-key 동작을 오버라이드 하고 싶으면, 명시적으로 설정해 주면 됩니다.



primary key 필드는 read-only 입니다.
만약, 이미 존재하는 객체의 primary key 값을 바꾸고 저장하면, 새로운 객체가 기존의 객체와 같이 생성이 됩니다. 예를들면,



```
from django.db import models


class Fruit(models.Model):
    name = models.CharField(max_length=100, primary_key=True)
    # 이름이 primary_key 값으로 되어있음
```





```
# 1. name 이 '사과' 인 객체를 생성 
fruit = Fruit.objects.create(name='사과')

# 2. name 을 '배' 로 바꾸어줌 
fruit.name = '배'

# 3. 바뀐 name 을 데이터베이스에 저장 
fruit.save() 

# 4. 값 list 를 조회 
Fruit.objects.values_list('nam', flat=True)
Out[4]: <QuerySet ['사과', '배']>

In [5]: Fruit.objects.all()                               # 모든 객체 조회                                                                                                                 
Out[5]: <QuerySet [<Fruit: 사과>, <Fruit: 배>]>

# primary key 값인 name 을 바꾸고 저장했기 때문에, 새로운 객체인 '배' 가 생겨난것을 확인
```



### unique



만일 True 일 경우, 해당 필드는 테이블 전체에서 고유한 값이여야 합니다.



## 마치며..



공통된 필드 옵션에 대한 짧은 설명이었습니다. 더 많은 디테일들은, common model field option reference 문서에서 확인할수 있습니다.



# Django Models 5편 - Fields (Automatic primary key fields)

## Models - Fields(Automatic primary key)



기본값으로, Django 는 각 모델에 다음과 같은 field 를 부여 합니다.

아래 필드는, 해당 모델에 명시적으로 정의해 놓지 않아도, 자동으로 적용이 되어 있습니다.



```
id = models.AutoField(primary_key=True)
```



이는, 자동으로 증가하는 primary key 입니다.



만약 사용자 정의의 primary key 를 명시하고 싶으면, primary_key 로 사용하고 싶은 필드에, `primary_key=True` 를 명시해주면 됩니다.



만약, 여러분들이 명시적으로, 어떠한 필드에 `primary_key = True` 를 설정한것을 보면, django 는 자동으로 id 컬럼을 추가하지 않을것입니다.



각 모델은, 정확히 하나의 필드가 primary_key = True 를 가질것을 요구합니다 (두개 이상의 필드가 primary_key=True 를 가질수는 없다는 이야기 입니다)



# Django Models 6편 - Fields (verbose field names)

## Models - Fields (verbose field names)



`ForeignKey`, `ManyToManyField` 그리고 `OneToOneField` 들을 제외한, 각 필드 타입은 선택적으로 첫번째 위치인자로, `verbose name` 을 받습니다.



`verbose name` 이 주어지지 않았을때는, Django 는 자동으로 field 의 속성명의 언더스코어를 공백으로 전환하여 verbose name 을 생성합니다.



> verbose name 은
>
> 사용자가 사용하는 자세한 이름을 의미합니다



`verbose name` 을 지정하지 않으면, 어드민에 표시되는 이름이 이상하다는것을 느낄수 있을겁니다.





> 아래 예시에서, verbose name 은 “person’s first name” 입니다



```
first_name = models.CharField("person's first name", max_length=30)
```



> 아래 예시에서는, verbose name 이 “first name” 입니다.
>
> 필드 타입의 첫번째 인자로, verbose name 이 명시되지 않았기 때문에,
>
> 필드명 first_name 에서, 언더스코어를 공백으로 전환하여 verbose name 을 표시합니다.



```
first_name = models.CharField(max_length=30)
```



> verbose_name 을 정의한 모델의 예시입니다.



```
class Members(models.Model):
    name = models.CharField(max_length=50, verbose_name='이름')
    nick_name = models.CharField(max_length=50, verbose_name="닉네임")
    birth_date = models.DateField(verbose_name="생년월일", null=True)
```



위 Members 모델에서는, name, nick_name, birth_date 이 각각 한글로 표시된 verbose_name 들을 어드민에 표시할것입니다.



## 외래키 모델들



아직 살펴보지는 않았지만, `ForeignKey`, `ManyToManyField` 그리고 `OneToOneField` 들은 첫번째 인자로 모델 클래스가 옵니다.

> 따라서, `verbose_name`을 키워드 인자로 사용해줍니다.



```
poll = models.ForeignKey(Poll, on_delete=models.CASCADE, verbose_name="the related poll")
# poll 은 ForeignKey (외래키) 로 Poll 이라는 클래스를 첫번째 인자로 받음 
# verbose_name 은 키워드 인자로 넣어줌 

sites = models.ManyToManyField(Site, verbose_name="list of sites")
# sites 는 ManyToManyField 로 Site 라는 클래스를 첫번째 인자로 받음 
# verbose_name 은 키워드 인자로 넣어줌 

place = models.OneToOneField(Place, on_delete=models.CASCADE, verbose_name='related place')
# place 는 OneToOneField 로 Place 라는 클래스를 첫번째 인자로 받음 
# verbose_name 은 키워드 인자로 넣어줌
```



위의 예시에서는, 단지 `ForeignKey`, `ManyToManyField` 그리고 `OneToOneField` 를 사용할때에, verbose_name 이 어떻게 키워드 인자로 들어가는지에 대한 코딩 스타일만 보여주고 있습니다.



> 관례적으로,
>
> verbose_name 의 첫번째 글자는 대문자로 써주지 않습니다.
>
> Django 가 필요할때 자동으로 첫번째 글자를 대문자화 해줍니다.



## 마치며..



verbose_name 에 대해서 알아보았습니다.
verbose_name 을 사용하면, 어떤것이 달라지는지 완벽한 예시가 있으면 좋았을텐데,



현재까지는, verbose_name 을 사용할경우, 해당 필드가 어드민에 표시되는 바가 달라지게 된다는점을 알고 넘어가면 될것 같습니다.



지난 포스팅에서 Runner 모델을 작성했었는데, 아래와 같이,

name 필드에 verbose_name 을 추가해주었습니다. “러너 이름”



```
class Runner(models.Model):
    MedalType = models.TextChoices('MedalType', 'GOLD SILVER BRONZE')
    name = models.CharField("러너이름", max_length=60)
    medal = models.CharField(blank=True, choices=MedalType.choices, max_length=10)
    
    def __str__(self):
        return self.name + " " + self.medal
```



위와같이 verbose_name 을 모델 필드에 추가하면, 아래처럼 어드민에 “러너이름” 이라고 표시가 됩니다.


![img](https://djangojeng-e.github.io/2020/08/02/Django-Models-6%ED%8E%B8-Fields-verbose-field-names/image1.png)



# Django Models 7편 - Relationships (Many-to-one)

## Models - Relationships (Many-to-one)





확실히, 관계형 데이터베이스의 장점은, 테이블들끼리 관계를 생성하는데에서 옵니다.



> **관계형 데이터베이스란?**
>
> 데이터베이스계의 주류로,
>
> 데이터를 로우 (Row:행, 줄) 과 컬럼 (Column: 열, 칸) 이라는 일종의 표 형태로 저장하고,
>
> 데이터의 종속성은 관계 (Relation) 으로 표현한다.
>
> 한 테이블에 있는 모든 로우는 같은 길이의 컬럼을 가지고 있으며, 이 컬럼의 구조와 데이터의 관계가 테이블 스키마 (Schema) 로 사전 정의 된다.





Django 는 가장 공통적인 relational database 3가지를 지원합니다.



- many-to-one (1:M, 1 대 다수)
- many-to-many (M:M, 다수 대 다수)
- one-to-one (1:1, 일 대 일 )



## Many-to-one relationships



다수 : 1 혹은 1: 다수 관계를 정의하기 위해서는, django.db.models.ForeignKey 를 사용합니다.

모델내에 클래스 속성으로 다른 필드 타입을 사용하는것과 비슷하게 사용할수 있습니다.



> ForeignKey 는 위치 인자를 필수적으로 필요로 합니다
>
> ForeignKey 를 사용하면, 다른 모델과 해당 필드에 관계를 만들어 놓을수 있습니다.
>
> ForeignKey 를 사용할때에는, 위치인자에 관계를 만들고 싶은 다른 class 이름을 넣어줍니다.
>
> 예를들면,



```
from django.db import models

class Manufuacturer(models.Model):
    # ... 
    pass 


class Car(models.Model):
    # 여러대의 Car 는 1개의 Manufacturuer 를 가집니다 
    # ForeginKey 를 사용해서, 이 관계를 모델링 해줄수 있습니다. 
    # 위의 Manufacturer 클래스를 소문자화한 이름으로 필드명을 짓는것이 권장 됩니다. 
    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
```



- Car 모델은 하나의 Manufacturer 를 가집니다
- 하나의 Manufacturer (브랜드 공장) 은 여러개의 자동차를 생산합니다
- 하지만, 각 자동차는 하나의 Manufacturer 만 가질수 있습니다
- 다수 : 1 의 데이터베이스 관계를 성립하여, ForeignKey 를 이용해 위의 예시처럼 정의해주었습니다.



또한, 재귀적인 관계를 생성할수도 있습니다 (many-to-one 관계를 스스로 갖는 객체)



필수는 아니지만, 권장되는 사항은, ForeignKey 필드 이름은 해당 모델의 소문자로 표현하는게 좋습니다.


하지만, 당연히 필드명은 우리가 원하는데로 지을수 있습니다.



```
class Car(models.Model):
    company_that_makes_it = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)

    # 필드명을 company_that_makes_it 으로, 사용자가 사용하기 편하게 지어줬습니다 
    # 하지만, 이 필드명은 Manufacturer 의 소문자 형태인 manufacturer 로 짓는것이 권장됩니다.
```



## 예시



```
class Dev_Department(models.Model):
    team = models.CharField(max_length=40)
    
class Dev_Staff(models.Model):
    name = models.CharField(max_length=40, verbose_name="직원이름")
    department = models.ForeignKey(Dev_Department, on_delete=models.CASCADE)
```



- 개발 조직에 있는 하나의 팀에는, 여러명의 직원이 소속될수 있습니다
- Dev_Staff 는, 직원의 이름과 부서 팀을 정의합니다. ForeignKey 를 사용하여, Dev_Department 와 관계를 가지게 정의되었습니다.



# Django Models 8편 - Relationships (Many-to-many) A

## Many-to-many Relationships



many-to-many 관계를 정의하기 위해서는, ManyToManyField 를 사용해 주면 됩니다.


다른 Field 타입을 사용하는것과 비슷하게 사용합니다. 클래스 속성에 포함해주는 방식으로 사용하면 됩니다.



ManyToManyField 는 위치 인자를 필요로 합니다.



> 관계된 모델을
>
> ManyToManyField 의 위치인자로 넣어주어야 합니다



예를들면, 하나의 피자가 다수의 토핑 객체를 가진다면,


\- 토핑은 다수의 피자에 있을수 있고, 각 피자는 다수의 토핑들을 가질수 있습니다.

이러한 관계는 아래의 형태로 표현될수 있습니다.



```
from django.db import models 

class Topping(models.Model):
    # ... 
    pass 


class Pizza(models.Model):
    # ... 
    # toppings = models.ManyToManyField(Topping)
```



ForeignKey 와 마찬가지로, 재귀적인 관계를 생성할수도 있습니다. (객체가 many-to-many 관계를 자기 자신에게 갖는것). 그리고, 아직 정의되지 않은 모델과도 관계를 생성할수도 있습니다.



> 필수는 아니지만, 권장 사항으로는,
>
> ManyToManyField 필드명은, 관계된 모델 객체의 복수형으로 표현합니다.



```
class Pizza(models.Model):
    # ... 
    toppings = models.ManyToManyField(Topping)
    # toppings 라고 복수형의 필드로 사용
```



위 예시에서 Pizza 와 Topping, 두 모델을 예시로 들어봤는데.

두 모델중 한쪽에만 ManyToManyField 가 사용되어야 합니다. 양쪽 다 사용하지는 않습니다!



보통 ManyToManyField 인스턴스들은, Form 에서 수정될 객체에 생성됩니다.


위 예시에서는, Pizza 안에 toppings 가 들어가 있는데. Pizza 가 여러개의 topping 들을 가지고, 피자에 들어간 토핑을 고치는 개념이 Topping 이 Pizza 를 가지고, 토핑에 들어간 피자를 고치는것보다 자연스럽기 때문에.


Pizza 클래스안에 ManyToManyField 를 넣어준것입니다.



## Extra fields on many-to-many relationships



many-to-many 관계에는, 추가적인 필드들이 존재합니다.


피자와 토핑같은 피자와 토핑을 섞고 매칭하는 다대다 관계를 다룰때는, 일반적인 ManyToManyField 만으로도 충분히 다룰수 있습니다.



하지만, 때때로, 우리는 두개의 모델 사이의 관계를 관련지어서 데이터들을 생각할 필요가 생깁니다.



예를들어서, 뮤지컬 그룹과 뮤지컬 그룹들에 속해 있는 뮤지션들의 관계를 생각해 봅시다.





한 사람과, 사람이 멤버로 속해 있는 그룹 사이에는 many-to-many 관계를 사용할수 있습니다. 따라서, ManyToManyField 를 사용하여 해당 관계를 표현할수 있습니다. 그렇지만, 멤버쉽에 대한 조금 더 많은 세부 사항들을 수집하고 싶어질지 모릅니다. 예를들면, 아래와 같은 사항들을 따로 기록해 놓고 싶을지 모릅니다.



- 사람이 어떤 날짜에 그룹에 합류 하였는지
- 언제 떠나갔는지
- 가입 장소는 어디인지
- 기타 정보



이러한 상황들에서, Django 는 many-to-many 관계를 관리하기 위한 모델을 명시할수 있게 해줍니다.


`extra field` 를 중간 모델에 넣어둘수 있습니다. 여기서 이야기 하는 중간 모델은, 새로운 모델로써, ManyToManyField 와 연관되어 있고, through 인자를 사용하여, 해당 모델이 중간 모델 역할을 할거라고 명시할수 있습니다.



> 조금 더 복잡한 many-to-many 관계를 관리하기 위해, 두 모델 사이에 어떠한 모델을 생성해 주고.
>
> extra field 들을 중간 모델에 넣어주고 사용할수 있습니다.
>
> 중간 모델은 ManyToManyField 와 연관되어 있고, through 인자를 사용하여,
>
> 해당 모델이 중간 모델 역할을 할것이라고 명시할수 있습니다.
>
> 코드를 보면서 이해하는것이 더 빠를것입니다. 뮤지션 예시를 보면 아래와 같습니다.



```
from django.db import models 


class Person(models.Model):
    name = models.CharField(max_length=128)
    
    def __str__(self):
        return self.name 
    
class Group(models.Model):
    name = models.CharField(max_length=128)
    members = models.ManyToManyField(Person, through='Membership')
    # through = "Membership" 을 넣어줌으로, 
    # Membership 이 Person 과 Group 사이의 중간 모델 역할을 하게 만들수 있음 
    
    def __str__(self):
        return self.name 
    
    
class Membership(models.Model):
    # 중간 모델에서는, Person 과 Group 이 ForeginKey 로 등록 되어 있어야 함 
    person = models.ForeignKey(Person, on_delete=models.CASCADE)
    group = models.ForeignKey(Group, on_delete=models.CASCADE)
    date_joined = models.DateField()
    invite_reason = models.CharField(max_length=64)
```





## 중간모델의 제약사항





- 중간 모델은 반드시 하나의 소스모델을 가르키는 foreign Key 를 가져야 합니다 (예시에서는 Group 이 됩니다)
- Group 모델이 바로 소스 모델인데, Group class 안에 `through='Membership'` 을 사용해서, Membership 이 Group 모델에 대한 중간 모델이 되게 만들어 주었습니다.
- 따라서, 중간 모델인 Membership 에서 1개의 Foreign Key 를 소스 모델인 Group 에 지정해 주었습니다.



**유의사항**



소스모델이 두개 이상이라면, ManyToManyField.through_fields 를 사용해서, Django 가 데이터베이스 관계를 위해 사용할 foreign key 들을 명시적으로 지정해 줘야 합니다.

만약 foreign key 가 한개 이상 있고, through_fields 가 지정되어 있지 않으면, 유효성 에러가 발생하게 됩니다.


비슷한 제약 사항이 타겟 모델에 쓰이는 Foreign key 에도 적용됩니다 (예시에서는, Person 모델이 타겟 모델입니다)



\- 중간 모델을 통해서 many-to-many 관계를 자기자신에 갖는 모델들은, 두개의 Foreign key 들을 같은 모델에 지정하는것이 허용됩니다. 하지만, 이것은 many-to-many 관계의 양쪽 사이드로 취급됩니다. 만일 2개 이상의 foreign key 들이 존재한다면, through_fields 를 사용해서 지정을 해줘야 합니다. 그렇지 않으면, 유효성 에러가 발생 합니다.



# Django Models 8편 - Relationships (Many-to-many) B

## Many-to-Many-2편



Many-To-Many 인스턴스들 생성하기



지난 포스팅에서, through 를 사용하여, 중간 모델을 가진 Many-to-Many 관계에 대한 모델을 작성했고.



중간모델 Membership 을 사용하는 ManyToManyField 를 설정하였습니다.


이제 몇가지 many-to-many relationship 을 생성할 준비가 되어 있습니다. 중간 모델의 인스턴스들을 생성하면서 Membership 을 사용하는 Many-to-Many 관계를 생성해 봅시다.



```
class Person(models.Model):
    name = models.CharField(max_length=128)
    
    def __str__(self):
        return self.name
    
class Group(models.Model):
    name = models.CharField(max_length=128)
    members = models.ManyToManyField(Person, through='Membership')
    # through="Membership" 을 넣어줌으로, 
    # Membership 이 Person 과 Group  사이의 중간 모델 역할을 하게 만들수 있음 
    
    def __str__(self):
        return self.name 
    

class Membership(models.Model):
    # 중간 모델에서는, Person과 Group이 ForeignKey 로 등록 되어 있어야 함 
    person = models.ForeignKey(Person, on_delete=models.CASCADE)
    group = models.ForeignKey(Group, on_delete=models.CASCADE)
    date_joined = models.DateField() 
    invite_reason = models.CharField(max_length=64)
```



위 모델을 바탕으로, shell 에서 객체를 만들어 봅니다.



> shell 에 접속하려면,
>
> python manage.py shell 명령어를 커맨드 라인에서 실행 시키면 됩니다.





```
ringo = Person.objects.create(name="Ringo Starr")
paul = Person.objects.create(name="Paul McCartney")
beatles = Group.objects.create(name="The Beatles")

import datetime 

m1 = Membership(person=ringo, group=beatles, date_joined=datetime.datetime(1962,8, 16), invite_reason="Needed a new drummer.")

m1.save()

In [7]: beatles.members.all()                                                                                                                                             
Out[7]: <QuerySet [<Person: Ringo Starr>]>

In [8]: ringo.group_set.all()                                                                                                                                             
Out[8]: <QuerySet [<Group: The Beatles>]>

In [9]: m2 = Membership.objects.create(person=paul, group=beatles, date_joined=datetime.datetime(1960, 8, 1), invite_reason="Wanted to form a band.")                     

In [10]: beatles.members.all()                                                                                                                                            
Out[10]: <QuerySet [<Person: Ringo Starr>, <Person: Paul McCartney>]>
```



> add(), create() 혹은 set() 을 사용해서 관계들을 생성할수도 있습니다.
>
> 요구되는 필드에 through_defaults 만 명시해주면 됩니다.



공식문서에는, 아래와 같은 예제가 있는데. shell 에서 쳐보면, 하나도 제대로 동작하지 않습니다.


공식문서를 무슨생각으로 만들어 놓은건지;;;



john 이라는 객체를 생성하면 잘 동작할것입니다만. 우선, 아래와 같이 연습 내역을 담습니다.



```
In [11]: beatles.members.add(john, through_defaults={'date_joined': date(1960, 8, 1)})                                                                                    
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-11-47e459b083b9> in <module>
----> 1 beatles.members.add(john, through_defaults={'date_joined': date(1960, 8, 1)})

NameError: name 'john' is not defined

In [12]: beatles.members.create(name="George Harrison", through_defaults={'date_joined': datetime.datetime(1960, 8, 1)} 
    ...: )                                                                                                                                                                
Out[12]: <Person: George Harrison>

In [13]: beatles.members.set([john, paul, ringo, george], through_defaults={'date_joined': date(1960, 8, 1)})                                                             
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-13-cad5aee581b9> in <module>
----> 1 beatles.members.set([john, paul, ringo, george], through_defaults={'date_joined': date(1960, 8, 1)})

NameError: name 'john' is not defined
```





만약 중간 모델에 의해서, through 테이블이 커스텀 정의가 되었는데, (model1, model2 ) 페어에 강제적으로 고유성을 요구하지 않고, 다중의 값을 허용한다면, remove() 를 호출해서 모든 중간 모델 인스턴스들을 제거할수 있습니다.



```
In [14]: Membership.objects.create(person=ringo, group=beatles, date_joined=datetime.datetime(1968, 9, 4), invite_reason="You've been gone for a month and we miss you.") 
Out[14]: <Membership: Membership object (9)>

In [15]: beatles.members.all()                                                                                                                                            
Out[15]: <QuerySet [<Person: Ringo Starr>, <Person: Paul McCartney>, <Person: George Harrison>, <Person: Ringo Starr>]>

In [16]: beatles.members.remove(ringo)                                                                                                                                    

In [17]: beatles.members.all()                                                                                                                                            
Out[17]: <QuerySet [<Person: Paul McCartney>, <Person: George Harrison>]>
```





> clear() 메서드는,
>
> 하나의 인스턴스를 위한 모든 Many-to-Many 관계들을 없애줍니다.



```
In [18]: # 비틀즈가 해산                                                                                                                                                  

In [19]: beatles.members.clear()                                                                                                                                          

In [20]: # 이 방식은 중간 모델 인스턴스를 삭제해줍니다                                                                                                                    

In [21]: Membership.objects.all()                                                                                                                                         
Out[21]: <QuerySet []>
```



한번 many-to-many 관계를 성립하였으면, 쿼리들을 발행할수 있습니다. 보통의 many-to-many 관계와 같이, many-to-many 로 관계된 모델들의 속성들을 사용하여 쿼리를 사용할수 있습니다.



```
# 멤버의 이름이 'Paul' 로 시작하는 그룹 찾기                                                                                                                     

In [39]: Group.objects.filter(members__name__startswith='Paul')                                                                                                           
Out[39]: <QuerySet [<Group: The Beatles>]>
```



중간 모델을 사용하면서, 중간 모델의 속성을 쿼리 조회 할수 있습니다.



```
# Person 중에, group 이름이 'The Beatles' 이면서 
# Membership에 든 날짜가 1961년 1월 1일 보다 이후인 객체를 조회 
In [40]: Person.objects.filter( 
    ...:     group__name='The Beatles', 
    ...:     membership__date_joined__gt=datetime.datetime(1961, 1, 1))                                                                                                   
Out[40]: <QuerySet [<Person: Ringo Starr>]>
```



> membership 의 정보를 바로 접근하고 싶으면,
>
> 바로 Membership 모델에 쿼리를 보내면 됩니다.



```
In [41]: ringos_membership = Membership.objects.get(group=beatles, person=ringo)                                                                                          

In [42]: ringos_membership.date_joined                                                                                                                                    
Out[42]: datetime.date(1962, 8, 16)

In [43]: ringos_membership.invite_reason                                                                                                                                  
Out[43]: 'Needed a new drummer.'
```





> Person 객체에서 부터 거꾸로 가는
>
> Many-To-Many reverse 관계도 존재합니다



```
In [44]: ringos_membership = ringo.membership_set.get(group=beatles)                                                                                                      

In [45]: ringos_membership.date_joined                                                                                                                                    
Out[45]: datetime.date(1962, 8, 16)

In [46]: ringos_membership.invite_reason                                                                                                                                  
Out[46]: 'Needed a new drummer.'
```





## 마치며..



공식문서가 예시도 너무 불충분하고,, 뭐라 설명은 하는지는 잘 모르겠습니다.


다음 포스팅에서는, ManyToMany 예제를 좀 들어서, 데이터베이스 검색 및 쿼리문 사용을 좀 연습 해봐야 할것 같습니다.



# Django Models 8편 - Many-to-many 예시

## Django Models - ManyToMany 예시



### 예시 개요



대학교에서 학생들이 듣는 과정들과 학생들에 대한 모델링을 합니다.


과정은, Course 로 표현하고, 과목들은 아래와 같이 몇개만 정리 합니다.



| CourseID | CourseName                  |
| :------- | :-------------------------- |
| 1        | Algorithm Design            |
| 2        | Systems Programming         |
| 3        | Object-Oriented Programming |



아래 학생들이 재학하고 있다고 가정합니다.



| StudentID | Name              |
| :-------- | :---------------- |
| 1         | djangojeng-e      |
| 2         | Jennifer Lawrence |
| 3         | Will Smith        |



> 학생들은, 여러개의 과목들을 수강할수 있고.
>
> 한 과목은 여러명의 수강생들이 들을수 있습니다.
>
> 따라서, Students - to - Courses 는 Many-to-Many, 다대다 관계가 성립됩니다
>
> 일반적인 Many-to-Many 관계로는, 학생의 수강날짜, 수강종료날짜, 그리고 성적 같은것들을 모두 기록할수 없기 때문에. 중간 모델을 하나 만들어서 관리할수 있습니다.



### 모델링



Student 와 Course 모델들의 중간 모델인, Enrolment 를 생성하고.


Enrolment 에 수강날짜, 수강종료날짜, 그리고 성적을 관리 할겁니다.



| Student          | Course                                                       | Enrolment                      |
| :--------------- | :----------------------------------------------------------- | :----------------------------- |
| name (CHARFIELD) | name (CHARFIELD)                                             | student (ForeignKey, Student)  |
|                  | enrolments - Enrolment 라는 중간 모델을 통해서 Student 와 M2M을 성립 | enrolment (ForeignKey, Course) |
|                  | - 중간모델을 성립하기 위해 through 를 사용해야 함            | enrolled_date (수강시작 날짜)  |
|                  |                                                              | finished_date (수강종료 날짜)  |
|                  |                                                              | academic_record (성적)         |



> 이렇게 정리된 사항을, Django 모델로 작성을 해보면, 아래와 같습니다.



```
from django.db import models

# Create your models here. 


class Student(models.Model);
	name = models.CharField(max_length=40)
    
    def __str__(self):
        return self.name 
    
    
class Course(models.Model):
    name = models.CharField(max_length=50)
    enrolments = models.ManyToManyField(Student, through='Enrolment')
    
    def __str__(self):
    	return self.name 
    	
        
class Enrolment(models.Model):
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    course = models.ForeignKey(Course, on_delete=models.CASCADE)
    enrolled_date = models.DateTimeField()
    finished_date = models.DateTimeField(blank=True)
    
    # 학점을 관리 하기 위해서, 등급을 ABCDEF 로 나눕니다 
    # 학점 선택을 위해서, models.TextChoice 로 선택할수 있는 학점을 밑에 넣어줍니다. 
    
    AcademicType = models.TextChoices('AcademicType', 'A B C D E F')
    
    academic_record = models.CharField(blank=True, choices=AcademicType.choices, 					   max_length=10)
    
    def __str__(self):
        return (self.student + " "
               + self.course + " "
               + self.enrolled_date + " "
               + self.finished_date + " "
               + self.academic_record)
```



## 객체 생성 연습



아래 테이블에 정리된 데이터 처럼, 객체 생성 연습을 해봅니다.



| CourseID | CourseName                  | StudentID | StudentName       |
| :------- | :-------------------------- | :-------- | :---------------- |
| 1        | Algorithm Design            | 1         | djangojeng-e      |
| 2        | Systems Programming         | 2         | Jennifer Lawrence |
| 3        | Object-Oriented Programming | 3         | Will Smith        |



> shell_plus 를 사용해서, 객체 생성을 해보았습니다.
>
> Student 와 Course 데이터 생성



### Student 와 Course 객체 생성



```
In [1]: djangojeng_e = Student.objects.create(name="장고쟁이")                                                                                                            

In [2]: jennifer = Student.objects.create(name="Jennifer Lawrence")                                                                                                       

In [3]: will = Student.objects.create(name="Will Smith")                                                                                                                  

In [4]: algorith_design = Course.objects.create(name="Algorith Design")                                                                                                   

In [5]: systems_programming = Course.objects.create(name="Systems Programming")                                                                                           

In [6]: object_oriented_programming = Course.objects.create(name="Object Oriented Programming")
```



> db 에 아래와 같이 저장 됩니다


![img](https://djangojeng-e.github.io/2020/08/05/Django-Models-8%ED%8E%B8-Many-to-many-%EC%98%88%EC%8B%9C/image1.png)
![img](https://djangojeng-e.github.io/2020/08/05/Django-Models-8%ED%8E%B8-Many-to-many-%EC%98%88%EC%8B%9C/image2.png)

### Student 객체 둘러보기



shell 에서, 아래와 같이 Student 객체를 생성후, `dir(인스턴스)`를 쳐보면, 해당 인스턴스의 메서드들이 모두 나열됩니다. 이 메서드중에, 가장 중요한것만 나열해 봅니다. (`__메서드` 들은 모두 빼주고 나열함)



```
djangojeng_e = Student.objects.create(name='장고쟁이')     

dir(djangojeng_e)                                                                                                                         
['DoesNotExist',
 'MultipleObjectsReturned',
 'course_set',
 'delete',
 'enrolment_set',
 'id',
 'name',
 'pk',
 'save',
 ]
```



```
# djangojeng_e 인스턴스로 호출가능한것들
djangojeng_e.id 
djangojeng_e.name 

# 해당 인스턴스와 연관된 enrolment 객체에 접근 가능 
djangojeng_e.enrolment_set   
# 예를들면, 
djangojeng_e.enrolment_set.all() 

# 해당 인스턴스와 연관된 course 객체에 접근 가능 
djangojeng_e.course_set 
# 예를들면, 
djangojeng_e.course_set.all()
```



### Course 객체 둘러보기



```
oop = Course.objects.create(name='객체지향 프로그래밍')
dir(oop)

['DoesNotExist',
 'MultipleObjectsReturned',
 'delete',
 'enrolment_set',
 'enrolments',
 'id',
 'name',
 'pk',
 'save',
 ]
```



```
oop.id 
oop.name
oop.pk

# oop 인스턴스와 연결된 enrolment 객체 조회 가능 
oop.enrolment_set.all() 

# enrolments 
oop.enrolments
```





### Enrolment 객체 생성



아래 테이블에 정리된대로, Enrolment 객체를 생성해 줍시다.



| EnrolmentID | Student           | Course                      | enrolled_date | finished_date | academic_record |
| :---------- | :---------------- | :-------------------------- | :------------ | :------------ | :-------------- |
| 1           | djangojeng-e      | Object-Oriented Programming | 2020.04.20    |               |                 |
| 2           | Jennifer Lawrence | Object-Oriented Programming | 2020.04.20    |               |                 |
| 3           | Will Smith        | Systems Programming         | 2019.04.21    | 2020.04.21    | A               |





```
# import datetime 을 먼저 하고 명령어를 실행해줘야 합니다. 


In [9]: e1 = Enrolment(student=djangojeng_e, 
                       course=oop, 
                       enrolled_date=datetime.datetime(2020, 4, 20))                                            
In [10]: e1.save()          

In [11]: oop.enrolments.all()                                                                                                                     
Out[11]: <QuerySet [<Student: 장고쟁이>]>

In [12]: djangojeng_e.course_set.all()     
# 장고쟁이 학생에 관련된 course 를 조회하려면, course_set.all() 하면 됩니다.                                                                                                   # 참고로, djangojeng_e.enrolment_set.all() 하면, enrol 된 객체가 조회 됩니다.                             
Out[12]: <QuerySet [<Course: 객체지향 프로그래밍>]>
```



```
In [15]: e2 = Enrolment.objects.create(student=jennifer, 
course=oop, 
enrolled_date=datetime.datetime(2020, 4, 20))    


In [17]: e3 = Enrolment.objects.create(student=will,  
    ...: course=systems_programming,  
    ...: enrolled_date=datetime.datetime(2019, 4, 19), 
    ...: finished_date=datetime.datetime(2020, 4, 20),  
    ...: academic_record="A")
```



데이터베이스 데이터를 저장할때, datetime 관련 에러가 날수가 있는데. 이 점에 대해서는 추후에 해결해봐야 할것 같습니다.


![img](https://djangojeng-e.github.io/2020/08/05/Django-Models-8%ED%8E%B8-Many-to-many-%EC%98%88%EC%8B%9C/image3.png)





> add(), create() 혹은 set() 을 사용해서,
>
> 관계들을 생성 할수 있습니다. through_defaults 를 통해, 요구되는 필드값을 넣어줄수 있습니다.



```
In [18]: john = Student.objects.create(name="John Snow")   

In [19]: systems_programming.enrolments.add(john, through_defaults={'enrolled_date': datetime.datetime(2020, 4, 19)})

In [20]: object_oriented_programming.enrolments.create(name="Arya Stark", through_defaults={'enrolled_date': datetime.datetime(2020, 4, 19)}) 

In [21]: algorithm_design.enrolments.set([djangojeng_e, jennifer, will], through_defaults={'enrolled_date': datetime.datetime(2020, 4, 19)}) 

In [23]: algorithm_design.enrolments.all()                                                                                                                                
Out[23]: <QuerySet [<Student: 장고쟁이>, <Student: Jennifer Lawrence>, <Student: Will Smith>]>
john = Student.objects.create(name="John Snow")
systems_programming.enrolments.add(jogn, through_defaults=
	{'enrolled_date': datetime.datetime(2020, 4, 19) })

object_oriented_programming.enrolments.create(name="Arya Stark", through_defaults={'enrolled_date': datetime.datetime(2020, 4, 19)})

algorithm_design.enrolments.set(
	[djangojeng_e, jennifer, will],
	through_defaults={'enrolled_date': datetime.datetime(2020, 4, 19)}
)

algorith_design.enrolments.all() 

  
Out[23]: <QuerySet [<Student: 장고쟁이>, <Student: Jennifer Lawrence>, <Student: Will Smith>]>
```


![img](https://djangojeng-e.github.io/2020/08/05/Django-Models-8%ED%8E%B8-Many-to-many-%EC%98%88%EC%8B%9C/image4.png)



> remove() 사용하기



```
# oop 에서 장고쟁이 빼기 

In [25]: object_oriented_programming.enrolments.all()                                                                                                             
Out[25]: <QuerySet [<Student: 장고쟁이>, <Student: Jennifer Lawrence>, <Student: Arya Stark>]>

In [26]: object_oriented_programming.enrolments.remove(djangojeng_e)                                                                                                 
In [27]: object_oriented_programming.enrolments.all()                                                                                                             
Out[27]: <QuerySet [<Student: Jennifer Lawrence>, <Student: Arya Stark>]>
```





> many-to-many 로 관계된 모델들의 속성을 사용하여
>
> 쿼리 생성하기



```
# 과목중에서, 수강중인 학생 이름이 will 로 시작하는 과목 찾기 

In [28]: Course.objects.filter(enrolments__name__startswith='Will')           
Out[28]: <QuerySet [<Course: Systems Programming>, <Course: Algorith Design>]>
```



> 중간 모델을 사용하여, 쿼리 작성하기
>
> Student 가 중간 모델을 이용해서, 찾고자 하는 데이터를 조회



```
# Student 중에서, 아래 조건들을 충족하는 데이터 검색 
# 수강 과목 이름이 "Systems Programming' 인것 
# 수강 시작 날짜가 2018년 4월 19일 이후인것들을 조회 

In [30]: Student.objects.filter( 
    ...:                 course__name='Systems Programming', 
    ...:                 enrolment__enrolled_date__gte=datetime.datetime(2018, 4, 19))  

Out[30]: <QuerySet [<Student: Will Smith>, <Student: John Snow>]>
```





> Enrolment 의 정보를 바로 접근
>
> Enrolment 모델에 직접 쿼리를 사용해서 데이터를 조회할수 있습니다



```
In [32]: will_enrolment = Enrolment.objects.get(course=systems_programming, student=will)                                                                                 
In [33]: will_enrolment.enrolled_date                                                                                                                               
Out[33]: datetime.datetime(2019, 4, 19, 0, 0, tzinfo=<UTC>)

In [34]: will_enrolment.finished_date                                                                                                                               
Out[34]: datetime.datetime(2020, 4, 20, 0, 0, tzinfo=<UTC>)

In [35]: will_enrolment.academic_record                                                                                                                             
Out[35]: 'A'
```



> Student 객체에서부터, enrolment 에 접근하는 many-to-many reverse



```
n [36]: will_enrolment = will.enrolment_set.get(course=systems_programming)                                                                                              

In [37]: will_enrolment.enrolled_date                                                                                                                                     
Out[37]: datetime.datetime(2019, 4, 19, 0, 0, tzinfo=<UTC>)

In [38]: will_enrolment.finished_date                                                                                                                                     
Out[38]: datetime.datetime(2020, 4, 20, 0, 0, tzinfo=<UTC>)

In [39]: will_enrolment.academic_record                                                                                                                                   
Out[39]: 'A'
```



reverse, back_ward relationship, forward relationship 에 대한 정리도 필요할것으로 보입니다!



# Django Models 9편 - One-to-One

## Models - Relationships (One-to-One)



### one-to-one relationships



one-to-one 관계, 즉 1대 1 관계를 정의하기 위해서는,


`OneToOneField`를 사용해 줍니다. 다른 필드 타입을 사용하는것과 비슷하게, 모델 클래스 안에 클래스 속성으로 정의해 줍니다.



OneToOneField 는 위치 인자를 필요로 합니다.



> OneToOneField 는 연결된 모델 클래스를, 필수 위치 인자로 받습니다



예를들어, “places” 라는 데이터베이스를 만들고 있었는데, `address` 나 `phone number` 같이 매우 보편적인 것들을 데이터베이스에 만드려고 하는 상황이 올지 모릅니다.



places 라는 장소위에 restaurant 이라는 데이터베이스를 만들고 싶으면, Place 를 만들때 포함하였던



> 데이터베이스 필드들을 Restaurant 모델에 반복하여 생성해 주기 보다는,
>
> Restaurant 이 OneToOneField 를 Place 에 갖게 하면 됩니다.



이게 무슨 이야기냐면,,, 하나의 장소 (Place)는 `address` 나 `phone number` 같은 보편적인 정보를 가지고 있을지 모릅니다. 그런데 만약, 하나의 장소 위에 식당 (restaurant) 을 만들고 싶다면?



restaurant 모델을 만들어 줄때에 `address` 나 `phone number` 같은 보편적인 필드들을 다시 정의하지 않고,


Restaurant 이 OneToOneField 를 사용하여 Place 모델과 연결되면 됩니다.





사실, Restaurant 도 하나의 Place 이기 때문에, 이런 상황을 해결 하기 위해서는 보통 상속 개념을 사용합니다.

클래스를 상속 하면, 상속받는 클래스의 필드와 메서드들을 사용할수 있다는것을 숙지해둡시다.



데이터베이스 상속은 암묵적으로, `one-to-one` 관계를 형성합니다.



## OneToOneField 예시



```
from django.db import models 

# Create your models here 


class Place(models.Model):
    name = models.CharField(max_length=50)
    address = models.CharField(max_length=80)
    
    def __str__(self):
        return "%s the place" % self.name 
    
    
class Restaurant(models.Model):
    # OneToOneField 의 첫번째 인자로, Place 클래스를 받음 
    place = models.OneToOneField(Place, on_delete=models.CASCADE, 		 primary_key=True)
    serves_hot_dogs = models.BooleanField(default=False)
    serves_pizza = models.BooleanField(default=False)
    
    def __str__(self):
        return "%s the restaurant" % self.place.name 
    
class Waiter(models.Model):
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)
    name = models.CharField(max_length=50)
    
    def __str__(self):
        return "%s the waiter at %s" % (self.name, self.restaurant)
```





위의 예시 모델에서, 하나의 장소 `Place` 에는 하나의 `Restaurant` 이 존재하고, 이는 `OneToOneField` 로 연결되어 있습니다. 하나의 `Restaurant` 에는 `ForeignKey` 로 연결되어 있는, `Waiter` 라는 모델이 존재합니다.


이는, 하나의 Restaurant 에 일하는 웨이터가 여러명 있을수 있는 관계를 모델링 한것입니다.



> `OneToOneField` 는 선택 인자로 parent_link 를 받습니다.



## Python API 사용해보기



장소 (Place) 2 곳을 생성해 줍니다.



```
In [1]: p1 = Place(name="명랑핫도그", address="성남시 분당구 정자1동")                                                                                                    

In [2]: p1.save()                                                                                                                                                         

In [3]: p2 = Place(name="피자헛", address="성남시 분당구 금곡동 불정로 87")                                                                                               

In [4]: p2.save()
```



식당을 생성해 주고, 부모 객체를 해당 객체의 primary key 로 전달해줍니다.



```
In [5]: r = Restaurant(place=p1, serves_hot_dogs=True, serves_pizza=False)                                                                                                

In [6]: r.save()
```



r 은 Restaurant 의 인스턴스 이니까, 하나의 Restaurant 인스턴스는, 해당 장소에 접근할수 있습니다.



```
In [7]: r.place                                                                                                                                                           
Out[7]: <Place: 명랑핫도그 the place>
```



반대로, 하나의 Place 는 restaurant 에 접근할수 있습니다.



```
In [8]: p1.restaurant                                                                                                                                                     
Out[8]: <Restaurant: 명랑핫도그 the restaurant>
```



> p2 는 연결된 restaurant 이 아직 없습니다.
>
> 따라서, 아래 코드를 shell 에서 실행 하면, p2 에는 restaurant 이 없다고 나옵니다



```
In [9]: from django.core.exceptions import ObjectDoesNotExist                                                                                                             

In [10]: try: 
    ...:     p2.restaurant 
    ...: except ObjectDoesNotExist: 
    ...:     print("There is no restaurant here.") 
    ...:                                                                                                                                                                  
# Shift + Enter 를 실행하면, 실행됨. 

There is no restaurant here.
```



> `hasattr` 메서드를 사용하여, 예외처리를 하지 않아도 됩니다.
>
> - `hasattr` 는 파이썬 내장함수 입니다.
> - 객체가 주어진 이름의 어트리뷰트를 가지고 있을때 True 를 반환하고, 그렇지 않을때에 False 를 반환합니다.
> - `hasatt(object, name)`



```
In [11]: hasattr(p2, 'restaurant')                                                                                                                                        
Out[11]: False
```



할당 기호 (`=`) 를 사용하여, place 를 설정해 줍니다. place 는 Restaurant 의 primary key 이므로, save 는 새로운 restaurant 을 생성합니다



```
# 위에서 r = Restaurant(place=p1, serves_hot_dogs=True, serves_pizza=False)
# r.place = p2 로 바꿔줍니다. 


In [12]: r.place = p2                                                                                                                                               
In [13]: r.save()                                                                                                                                                   

In [14]: p2.restaurant                                                                                                                                                    
Out[14]: <Restaurant: 피자헛 the restaurant>

In [15]: r.place                                                                                                                                                          
Out[15]: <Place: 피자헛 the place>
```



Place 를 다시 역으로 바꿔줍니다.



```
In [16]: p1.restaurant = r                                                                                                                                                

In [17]: p1.restaurant                                                                                                                                                    
Out[17]: <Restaurant: 명랑핫도그 the restaurant>
```



> One-To-One 관계가 할당 되기 이전에
>
> 객체가 저장 되어 있어야 ValueError 가 나질 않습니다.



```
In [18]: p3 = Place(name='Daemon Dogs', address='944 W. Fullerton')                                                                                                       

In [19]: Restaurant.objects.create(place=p3, serves_hot_dogs=True, serves_pizza=False)                                                                                    
---------------------------------------------------------------------------
ValueError                                Traceback (most recent call last)
<ipython-input-19-18b4cdec5239> in <module>
----> 1 Restaurant.objects.create(place=p3, serves_hot_dogs=True, serves_pizza=False)
```



> Restaurant.objects.all() 은 Restaurants 만 반환합니다. Place 는 반환하지 않습니다.
>
> Place.objects.all() 은 Places 들을 반환합니다. Restaurant을 가지고 있던 안있던, 결과를 반환합니다.



```
In [20]: Restaurant.objects.all()                                                                                                                                         
Out[20]: <QuerySet [<Restaurant: 명랑핫도그 the restaurant>, <Restaurant: 피자헛 the restaurant>]>

In [21]: Place.objects.order_by('name')                                                                                                                                   
Out[21]: <QuerySet [<Place: 명랑핫도그 the place>, <Place: 피자헛 the place>]>
```



> 관계간 룩업들을 사용하여, 해당 모델에 쿼리문을 작성할수 있습니다.



```
In [22]: Restaurant.objects.get(place=p1)                                                                                                                                 
Out[22]: <Restaurant: 명랑핫도그 the restaurant>

In [23]: Restaurant.objects.get(place__pk=1)                                                                                                                              
Out[23]: <Restaurant: 명랑핫도그 the restaurant>

In [24]: Restaurant.objects.filter(place__name__startswith="명")                                                                                                          
Out[24]: <QuerySet [<Restaurant: 명랑핫도그 the restaurant>]>

In [25]: Restaurant.objects.exclude(place__address__contains="헛")                                                                                                        
Out[25]: <QuerySet [<Restaurant: 명랑핫도그 the restaurant>, <Restaurant: 피자헛 the restaurant>]>

# 한글은 조회 필터가 안걸리는지, exclude 를 했는데도. 조회가 되서 나옵니다;;;;
```



물론 거꾸로도 작동 합니다.



```
In [27]: Place.objects.get(pk=1)                                                                                                                                          
Out[27]: <Place: 명랑핫도그 the place>

In [28]: Place.objects.get(restaurant__place=p1)                                                                                                                          
Out[28]: <Place: 명랑핫도그 the place>

In [29]: Place.objects.get(restaurant=r)                                                                                                                                  
Out[29]: <Place: 명랑핫도그 the place>

In [30]: Place.objects.get(restaurant__place__name__startswith="명")                                                                                                      
Out[30]: <Place: 명랑핫도그 the place>
```





> Waiter 를 Restaurant 에 추가해 줍니다.
>
> Waiter는 Restaurant 에 ForeignKey 로 등록 되어 있습니다.



```
In [31]: w = r.waiter_set.create(name="장고쟁이")                                                                                                                         

In [32]: w                                                                                                                                                                
Out[32]: <Waiter: 장고쟁이 the waiter at 명랑핫도그 the restaurant>
```



웨이터들을 쿼리를 통해 조회합니다.



```
In [33]: Waiter.objects.filter(restaurant__place=p1)                                                                                                                      
Out[33]: <QuerySet [<Waiter: 장고쟁이 the waiter at 명랑핫도그 the restaurant>]>

In [34]: Waiter.objects.filter(restaurant__place__name__startswith="명")                                                                                                  
Out[34]: <QuerySet [<Waiter: 장고쟁이 the waiter at 명랑핫도그 the restaurant>]>
```



## 데이터베이스 리뷰


![img](https://djangojeng-e.github.io/2020/08/06/Django-Models-9%ED%8E%B8-One-to-One/image1.png)![img](https://djangojeng-e.github.io/2020/08/06/Django-Models-9%ED%8E%B8-One-to-One/image2.png)![img](https://djangojeng-e.github.io/2020/08/06/Django-Models-9%ED%8E%B8-One-to-One/image3.png)



# Django Models 10편 - Fields (Models across files)

## 파일사이에 모델들



> 다른 앱에 존재하는 모델끼리 관계를 형성해도 괜찮습니다.
>
> 다른 앱에 존재하는 모델을 사용하려면, 관계된 모델을 파일 상단에 가져와서 사용합니다.



다른 모델 클래스를 가져온 다음에는, 필요한곳에 사용하면 됩니다.



공식문서에서는 예시가 많이 빈약한데, 프로젝트 구조가 아래처럼 되어 있다고 가정합니다.



> order 라는 앱이 있고, product 라는앱이 있습니다
>
> 이런 구조안에서, order/models.py 파일에서, product/models.py 에 있는 모델을
>
> 가져다 사용할수 있습니다.



```
├── config
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── db.sqlite3
├── manage.py
├── order
│   ├── admin.py
│   ├── apps.py
│   ├── forms.py
│   ├── __init__.py
│   ├── migrations
│   ├── models.py
│   ├── tests.py
│   └── views.py
├── product
│   ├── admin.py
│   ├── apps.py
│   ├── forms.py
│   ├── __init__.py
│   ├── migrations
│   ├── models.py
│   ├── tests.py
│   └── views.py
```





## 예시



> 파일 상단에, 다른 앱에 존재하는 클래스를 가져와서 사용하면 됩니다.



```
from django.db import models
from order.models import Order 	

# order 앱에 있는 models.py 에서 Order 라는 모델을 불러옴

class Product(models.Model):
    # ... 
    order = models.ForeignKey(Order, on_delete=models.CASCADE)
```



## 공식문서 예시



공식문서 예시에서는, geography 에 있는 models 를 불러오고, 그 안에 있는 ZipCode 클래스를 가져와서 사용하는것을 보여주고 있습니다.



```
from django.db import models 
from geography.models import ZipCode 


class Restaurant(models.Model):
    # ... 
    zip_code = models.ForeignKey(
    ZipCode, on_delete=models.SET_NULL,
    blank=True,
    null=True, 
    )
```



파일 상단에 다른 앱에 존재하는 모델을 불러오기 위해서는, 아래 형식으로 불러와주면 됩니다.



```
from <앱이름>.models import <가져올클래스이름>
```



# Django Models 11편 - Fields (Field name restrictions)

## Models - Fields(Field Name Restrictions)



### Field name restrictions



Django 는 모델 필드 이름들에 대한 제약이 있습니다.



1. 필드명은 파이썬 예약어들이 될수 없습니다. 만약 사용한다면, 파이썬 문법 에러를 발생 시킵니다.

   ```
   class Example(models.Model):
   	pass = models.IntegerField()
   
   # pass 는 파이썬에서 reversed word (예약어) 입니다 
   # 따라서, 모델의 필드명이 될수 없습니다
   ```



1. 필드명은 하나 이상의 언더스코어를 사용할수 없습니다. Django 의 쿼리 조회문이 언더스코어를 2개 사용하기 때문에, 필드명은, `__` 를 가질수 없습니다.

   ```
   class Example(models.Model):
   	foo__bar = models.IntegerField() 
   	
   # foo__bar 는 언더스코어 2개를 가지고 있습니다. 잘못된 필드명입니다.
   ```



1. 비슷한 이유로, 언더스코어로 끝나면 안됩니다. 쿼리 구문과 겹치기 때문입니다

   ```
   class Example(models.Model):
   	foobar_ = models.InegerField() 
   	
   # foobar_ 처럼 언더스코어로 끝나는 필드명은 사용하면 안됩니다.
   ```





하지만, 이런 제약들은 유도리 있게 잘 사용될수 있습니다. 필드명은 데이터베이스 컬럼명과 꼭 같지 않아도 되기 때문입니다.



join, where 혹은 select 같은 SQL 예약어들은 모델 필드명에 사용해도 됩니다.


Django 는 모든 SQL 쿼리 기반에서의 데이터베이스 테이블 이름들과 컬럼명들에서 자유롭습니다.



# Django Models 12편 - Meta Options

## Models - Meta Options



> Metadata
>
> 데이터의 집합체로 다른 데이터에 대한 설명과 정보를 제공합니다



모델에 metadata 를 부여할수 있습니다. 클래스 안에 Meta 클래스를 사용해서 부여할수 있습니다.


아래 예시와 같이 모델 클래스 안에 부가적으로, Meta 클래스를 넣어서 metadata 를 넣을수 있습니다.



```
from django.db import models 

class Ox(models.Model):
    horn_length = models.IntegerField()
    
    # 아래와 같이 Ox 모델 클래스 안쪽에 Meta 클래스를 넣어서 metadata 를 부여 할수 있습니다 
    class Meta:
        ordering = ["horn_length"]
        verbose_name_plural = "oxen"
```



필드가 아닌 무엇이든, 모델의 메타데이터가 될수 있습니다.


필드는, 메타 데이터가 될수 없습니.





> 정렬 옵션인 ordering,
>
> 데이터 베이스 테이블명, db_table
>
> 혹은 사람이 읽기 편한 단수와 복수 이름들인, verbose_name, verbose_name_plural
>
> 이런것들이 Meta 클래스안에 정의 될수 있습니다.



모델에 대한 class Meta 적용은 전적으로 선택 사항입니다.



Meta Option 에 가능한 모든 옵션들에 대해서는, model option reference 문서에서 보면 됩니다.



## Meta option 리스트



메타 옵션에는 아래와 같은 항목들이 존재합니다.

모든 리스트를 확인하고 싶다면, https://docs.djangoproject.com/en/3.0/ref/models/options/


링크를 확인 하면 됩니다.

| Meta Options         | Function                                                     | Remarks                                                      |
| :------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| abstract             | 모델이 abstract base class 가 됩니다                         | `abstract = True`                                            |
| app_label            | 모델이 INSTALLED_APPS 밖에 있는 어플리케이션에 존재할때에 선언합니다 | `app_label = myapp`                                          |
| base_manager_name    | 메니저의 속성 이름을 지정합니다. 예를들면, objects           |                                                              |
| db_table             | 해당 모델을 위한 데이터베이스 테이블 명을 지정합니다         | `db_table = 'music_album'`                                   |
| db_tablespace        | 데이터베이스 테이블 스페이스의 이름                          |                                                              |
| default_manager_name | 해당 모델을 위해 사용할 메니저의 이름을 지정합니다           |                                                              |
| default_related_name | 관련된 객체에서 역으로 접근할 이름을 지정합니다. 기본값은, `모델명_set` 입니다 |                                                              |
| get_latest_by        | 보편적으로 ,DateField, DateTimeField 혹은 IntegerField 같은 모델명에 사용합니다. | get_latest_by = “order_date”, get_latest_by = [‘-priority’, ‘order_date’] |
|                      |                                                              |                                                              |



# Django Models 13편 - Model Attributes

## Model Attributes



모델의 가장 중요한 속성은 Manager 입니다.



> Manager 는
>
> Django 데이터베이스 쿼리 운영을 제공 하는 인터페이스로,
>
> 데이터베이스에서 인스턴스들을 검색할때 사용 됩니다.
>
> 만약, 사용자 정의 Manager 가 정의되어 있지 않다면, 기본값으로, Manager 의 이름은 objects 입니다.



Manager 는 모델 클래스들을 통해서만 접근 가능합니다. 모델 인스턴스로는 접근이 가능하지 않습니다.



## Manager 의 사용



우리가 Django 에서 해당 모델의 데이터베이스 모든 데이터를 조회할때에는, 아래와 같이 ORM 을 작성합니다.


이때 사용되는, objects 가 Manager의 기본 이름입니다.

```
<model명>.objects.all()
```



예시를 들어보자면,



```
from django.db import models 

class Student(models.Model):
    name = models.CharField(max_length=50, verbose_name='학생 이름')
```



위의 Student 모델이 있다고 가정할때에, 해당 모델의 데이터베이스를 조회하는 명령어들은 아래와 같습니다.



```
Student.objects.all()   # Student 의 모든 객체를 조회 
Student.objects.get(name='장고쟁이') # 이름이 장고쟁이인 Student 를 찾음
```



# Django Models 14편 - Model Methods

## Model Methods



모델 클래스에는, 사용자 정의 메서드를 추가할수 있습니다. (row-level, 즉, 줄 단위)


Manager 메서드 (objects) 는, table 전체에 적용되고. 모델 메서드들은, 특정한 모델 인스턴스에 적용됩니다.



해당 모델의 인스턴스에 대해서, 특별한 비지니스 로직을 동작하고 싶을때에, 이런 모델 메서드를 사용해서. 하나의 모델에 모아놓고 사용할수 있습니다.



아래 예시에는, 몇가지 사용자 정의 메서드가 정의되어 있습니다.



```
from django.db import models 


class Person(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    birth_date = models.DateField()
    
    def baby_boomer_status(self):
        # "Returns the person's baby-boomer status. "
        # 해당 사람이, baby_boomer 세대인지 아닌지 판단합니다. 
        import datetime 
        if self.birth_date < datetime.datetime(1945, 8, 1):
            return "Pre-boomer"
        elif self.birth_date < datetime.datetime(1965, 1, 1):
            return "Baby boomer"
        else:
            return "Post-boomer"
        
    @property 
    def full_name(self):
        # "Returns the person's full name"
        # 해당 사람의 이름을 리턴합니다 
        return '%s %s' % (self.first_name, self.last_name)
```





예시에서, 마지막으로 작성된 `def full_name(self)` 메서드는, property 입니다.



모델 인스턴스는 자동으로 각 모델에 부여된 메서드의 목록을 가지고 있고.


아래 예시에서 보면, colin 이라는 인스턴스가 모델에 정의된 메서드들을 사용할수 있다는것을 확인합니다.

```
colin = Person.objects.create(first_name="colin", last_name="firth", birth_date="1961-10-07")

# colin 이라는 객체를 가져옴 
colin = Person.objects.get(first_name="colin")

# colin 이라는 인스턴스의 baby_boomer_status 메서드를 호출 
colin.baby_boomer_status 

# colin 이라는 인스턴스의 full_name 메서드를 호출 
colin.full_name
```





## `__str__()` 메서드



객체의 문자열 표시를 반환하는 파이썬 `magic method` (매직 메서드) 입니다. 이 메서드는 파이썬과 장고가 언제든 모델 인스턴스가 순수한 문자열로 표시되길 원할때에 사용됩니다.



> `__str__()` 메서드를 사용하면, 해당 모델 인스턴스를 문자열로 표시해서 반환할수 있습니다.



이 메서드는 아주 많이, 많이, 많이, 그리고 자주, 사용되는 메서드입니다.



## `get_absolute_url()`



이 메서드는 Django 에게 어떻게 객체에 대한 URL 을 계산해야 하는지 얘기해 줍니다.


Django 는 이 메서드를 어드민 인터페이스에서 사용합니다. 또는 언제든 객체의 URL 을 알아내야 할때 사용합니다.



어떤 객체든 고유한 URL 이 식별하는 객체일때, 이 메서드로 정의 합니다.



## Model Method 연습



```
In [1]: import datetime                                                                                                                                                   

In [2]: colin = Person.objects.create(first_name="Colin", 
                                      last_name="Firth", 
                                      birth_date=datetime.date(1960, 9, 10))                                                       

In [3]: colin.first_name                                                                                                                                                  
Out[3]: 'Colin'

In [4]: colin.last_name                                                                                                                                                   
Out[4]: 'Firth'

In [5]: colin.birth_date                                                                                                                                                  
Out[5]: datetime.date(1960, 9, 10)

In [6]: colin.baby_boomer_status                                                                                                                                          
Out[6]: <bound method Person.baby_boomer_status of <Person: Person object (4)>>

In [7]: colin.full_name                                                                                                                                                   
Out[7]: 'Colin Firth'
```



# Django Models 15편 - 이미 정의된 모델 메서드 오버라이드 하기

## Overriding predefined model methods



여러분들이 커스터마이즈 하고 싶은 데이터베이스의 동작을 캡슐화 해주는 모델 메서드들도 존재 합니다.



특히 우리는 종종 `save()` 와 `delete()` 의 동작 방식을 바꾸고 싶어 집니다.



> save() 나 delete() 같은 메서드들을 override 해서 동작 방식을 바꾸는것도 가능하고,
>
> 다른 어떤 모델 메서드들을 override 해서 사용하는것도 가능합니다.



내장된 메서드, `save()` 같은 메서드를 override 하여 사용하는 고전적인 예시는,


하나의 객체를 저장할때마다 다른 무언가를 발생시키고 싶을 때입니다.





## Method override 예시



예시 코드를 둘러봅니다.



```
from django.db import models 


class Blog(models.Model):
	name = models.CharField(max_length=100)
    tagline = models.TextField()
    
    def save(self, *args, **kwargs):
        do_something()
        super().save(*args, **kwargs)  # 실제 save() 를 호출 
        do_something_else()
        
# save()를 호출할때, do_something() 부분에 어떠한 작업을 하는 코드를 써주면, 
# 실제 save() 가 동작하기 전에, do_something() 을 수행합니다 
# 그리고, save()가 호출 되고 나서는, do_something_else() 부분에 있는 또다른 
# 작업을 해줄수 있습니다.
```



`def save(self, *args, **kwargs)` 를 사용하여, 평범하게 객체를 저장하기 보다는, 다른 작업을 실행하고 저장할수 있는 기능을 가질수 있습니다.



> 저장하는것을 방지할수도 있습니다



```
from django.db import models 


class Blog(models.Model):
    name = models.CharField(max_length=100)
    tagline = models.TextField() 
    
    def save(self, *args, **kwargs):
        if self.name == "Yoko One's blog":
            return 
        # 블로그 이름이 "Yoko One's blog" 이면, save 가 호출 되지 않고 
        # 비어있는값이 return 됩니다 
        # "Yoko One's blog" 가 제목이 아닐경우, else 로 넘어갑니다. 
        else:
            super().save(*args, **kwargs)	# 실제 save()를 호출해서 save() 진행
```





## `super().save(*args, **kwargs)`



처음에는 생소해 보일지 모르지만,



> `super().save(*args, **kwargs)` 를 통해서,
>
> superclass 의 메서드를 호출하는것이 중요합니다.
>
> 이것을 호출하여, 객체가 여전히 데이터베이스에 저장되게 하는것이 중요합니다.



superclass 메서드를 호출하지 않는다면, 기본적인 저장 동작이 일어나지 않을것이고.


이는 데이터베이스를 건들지 않습니다.



또한, 모델 메서드에 전달된 인자들을 전달하는것도 중요합니다.


`*args, **kwargs` 부분이 이 역할을 수행합니다.

Django 는 때때로, 내장 모델 메서드들의 능력을 확장하고, 새로운 인자들을 추가해줄수 있습니다.



> 정의된 모델안에서, `*args` 와 `**kwargs` 를 사용하면,
>
> 인자들이 추가 되었을때, 코드가 자동으로 해당 인자들을 지원합니다.



## Quick Note



> override 된 모델 메서드들은, 대량으로 운영되는 상황에서는 호출되지 않습니다.
>
> 객체를 위한, `delete()` 메서드는 쿼리셋을 이용해서 대량으로 객체를 지울때에는 호출 되지 않습니다. 혹은, cascading delete 일때도 호출되지 않습니다.
>
> 사용자 정의 delete 로직의 실행을 확실시 하고 싶으면,
>
> pre_delete 그리고/혹은 post_delete 신호를 사용합니다.
>
> 안타깝게도, 객체를 대량으로 creating 혹은 updating 하는 작업은 존재하지 않습니다.
>
> save(), pre_save() 그리고 post_save() 들이 대량으로 작업해야 하는 상황에서는 호출되지 않기 때문입니다.



# Django Models 16편 - Model Inheritance

## Model Inheritance (모델 상속)



Django 에서 모델 상속은, 파이썬에서 일반적인 클래스 상속을 하는것과 거의 비슷하게 작동합니다.



다만, django 의 기본에는 충실해야 합니다. 이것이 의미하는것은, Django 모델 클래스들은 `django.db.models.Model` 의 서브 클래스가 되어야 한다는 이야기 입니다.



여러분들이 결정해야 할 사항은 아래와 같습니다.



1. 부모 모델이 자신의 모델이 될지
2. 부모 모델은 단지 공통된 정보를 가지고, 자식 모델들을 통해서만 정보가 보이게 하게 할지



이 개념을 쉽게 이해하기 위해서는, 파이썬에서 클래스의 상속 개념을 잘 알고 있어야 합니다.





> Django 에서는 상속에 대한 3가지 스타일들이 존재 합니다.



1. 보통은, 자식 모델들의 필드에 대해서 타이핑 하기 싫을때, 부모 클래스가 정보만 가지게끔 사용할수 있습니다. 이 클래스는 절대로 분리되서 사용될수 없습니다. 따라서, `Abstract base classes` 들이 당신이 원하는것이 될겁니다.
2. 이미 존재하는 모델 (다른 어플리케이션에 있는 모델)을 상속을 받고 있고, 각 모델이 각자의 데이터베이스 테이블을 가지고 있다면, `Multi-table inheritance` 를 사용할수 있습니다.
3. 모델 필드의 수정 없이, 모델의 파이썬 레벨 동작을 고치고 싶다면, `proxy models` 를 사용하면 됩니다.





정리하자면,



> Django 에서 모델 상속에 대해, 아래 3가지 스타일이 존재합니다.
>
> 1) Abstract base classes
>
> 2) Multi-table inheritance
>
> 3) Proxy models



# Django Models 17편 - Model 상속 (Abstract Base Class) 1

## Model Inheritance (Abstract Base Classes)



```
Abstract Base classes
```



Abstract base 클래스들은, 여러분들이 어떠한 공통된 정보들을, 다른 모델들에 넣고 싶을때에 유용하게 쓸수 있습니다.



Base 클래스를 작성한다음, `Meta class` 에다가, `abstract=True` 로 설정합니다.


그러면, 해당 모델은, 데이터베이스를 생성하는데에 사용되지 않습니다.



> 다른 모델들의 Base 클래스로 사용될때에는,
>
> Base 클래스의 필드들이 자식 클래스들에 추가 됩니다.



예시를 들자면,



```
from django.db import models 

class CommonInfo(models.Model):
    name = models.CharField(max_length=100)
    age = models.PositiveIntegerField()
    
    class Meta:
        abstract = True 
        # class Meta 를 선언함으로써, 다른 모델들이 상속 받을수 있는 모델이 됨 
        
        
class Student(CommonInfo):
    # 공통된 정보를 가지고 있는 CommonInfo 클래스를 상속받는 Student 클래스 모델 
    home_group = models.CharField(max_length=5)
    
    
class Teacher(CommonInfo):
    # 공통된 정보를 가지고 있는 CommonInfo 클래스를 상속받는 Teacher 클래스 모델 
    teaching_subject = models.CharField(max_length=20)
```



위의 예시에서, CommonInfo 클래스에 있는 name 과 age 필드는,

Student 모델과, Teacher 모델이 상속받는 필드입니다.



따라서, Student 모델은 3가지 필드들을 갖게 됩니다. name, age 그리고 home_group.

비슷한 의미로, Teacher 모델은 name, age 그리고 teaching_subject 필드를 갖게 됩니다.



> CommonInfo 모델은 abstract base 클래스 이기 때문에,
>
> CommonInfo 모델은 일반 Django 모델로 사용될수 없습니다.
>
> CommonInfo 모델은 데이터베이스 테이블을 생성하지도 않고, manager 도 갖지 않습니다 (object 메서드를 가지지 않습니다). 그리고, 직접적인 인스턴스 생성과 저장도 되지 않습니다.



abstract base class 에서 상속받은 필드들은 다른 필드 혹은 값으로 override 될수 있습니다.


혹은, None 으로 제거 될수 있습니다.



`abstract base` 클래스 상속 방식은, 공통된 정보를 파이썬 레벨에 두고,

각각의 자식 모델마다 한개의 데이터베이스 테이블을 데이터베이스에 생성합니다.



실제로 위의 예시에서 CommonInfo 클래스로 Shell 에 들어가서, 몇가지 명령어를 실행해보면, 에러가 납니다.



```
In [1]: common = CommonInfo.objects.create(name="django", age=22)                                                                                                         
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-1-8d6637697465> in <module>
----> 1 common = CommonInfo.objects.create(name="django", age=22)

NameError: name 'CommonInfo' is not defined

In [2]: CommonInfo.objects.all()                                                                                                                                          
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-2-fc35ec638311> in <module>
----> 1 CommonInfo.objects.all()

NameError: name 'CommonInfo' is not defined

In [3]: c = CommonInfo()                                                                                                                                                  
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-3-2542378fc97f> in <module>
----> 1 c = CommonInfo()

NameError: name 'CommonInfo' is not defined
```



# Django Models 17편 - Model 상속 (Abstract Base Class) 2

## Meta Inheritance



`abstract base class`가 생성될때에, django 는 base class 안에 선언된 Meta 클래스를 속성으로 만들어 줍니다. 만약 하나의 자식 클래스안에, 해당 자식 클래스를 위한 Meta class 가 정의되어 있지 않다면, 부모의 Meta 를 상속 받을것 입니다.



> 만약 자식 클래스가, 부모의 Meta 클래스를 확장하고 싶다면,
>
> 자식 클래스는, 부모의 Meta 클래스를 자식 클래스 내에 있는 Meta 에서 상속 받으면 됩니다.



코드 예시를 들자면,



```
from django.db import models

class CommonInfo(models.Model):
    name = models.CharField(max_length=100)
    age = models.PositiveIntegerField()
    
    class Meta:
        abstract = True 
        ordering = ['name']
        
class Student(CommonInfo):
    home_group = models.CharField(max_length=5)
    
    class Meta(CommonInfo.Meta):
        db_table = 'student_info'
```



Django 는 기본적으로, abstract base 클래스의 Meta class 에 대한 조정사항을 가집니다. Meta 속성을 설치하기 이전에, abstract=False 로 지정합니다.



이것이 의미하는바는,

abstract 클래스의 자녀들은 자동으로 그리고 스스로는 abstract 클래스가 되지 않는다는것을 의미합니다.



물론, 다른 abstract 클래스를 상속받는 Abstract 클래스를 만드는것도 가능합니다.

단지 만들때마다, 명시적으로, abstract=True 를 지정해주는것만이 필요할 뿐입니다.



어떠한 속성들은 Abstract base class 의 Meta 안에 포함되는것이 이상해 보일때가 있습니다.


예를들면, db_table 을 포함한다면, 모든 자식 클래스들이 같은 데이터베이스 테이블을 사용하게 된다는 의미가 됩니다. 이렇게 설정하면, 매우 일이 이상해 집니다.



## 마치며..



자식 클래스에 있는 Meta Class 도 부모 클래스에 있는 Meta 클래스를 상속 받을수 있습니다.



연습도 해볼겸, 인스턴스 생성후, 속성값들을 확인해 봅니다.



```
In [1]: colin = Student.objects.create(name="Colin Firth", age=60, home_group="king")                                                                                     

In [2]: colin.Meta                                                                                                                                                        
Out[2]: abstractbaseclasses.models.CommonInfo.Meta

In [4]: colin.name                                                                                                                                                        
Out[4]: 'Colin Firth'

In [5]: colin.age                                                                                                                                                         
Out[5]: 60
```



Student class 안에 Meta 클래스에 db_table 이 데이터베이스 테이블 명이 된것을 확인할수 있습니다.



CommonInfo 클래스 안에 존재하는 Meta 클래스에, 이 db_table 속성을 넣어두면,

CommonInfo 클래스를 상속하는 모든 자식 클래스들의 데이터베이스 테이블명이 모두 하나로 동일하게 될겁니다. 그렇게 되면, 조금 이상한 일이 될겁니다.



# Django Models 18편 - Model 상속 related_name, related_query_name

## Be careful with related_name and related_query_name



만약 우리가 ForeginKey 나 ManyToManyField 에 related_name 혹은 related_query_name 을 사용하고 있다면, 우리는 언제나 고유한 reverse name 과 query name 들을 해당 필드에 지정해 주어야 합니다.



이 related_name 과 related_query_name 부분에 대해서, 아주 심도있게 clarification 한 곳이 없으므로.



예시만 보고 지나갑니다.



common/models.py



```
from django.db import models 

class Base(models.Model):
    m2m = models.ManyToManyField(
    OtherModel, 
    related_name = "%(app_label)s_%(class)s_related",
    related_query_name = "%(app_label)s_%(class)ss",
    )
    
  	class Meta:
        abstract = True 
        
class ChilA(Base):
    pass 

class ChildB(Base);
	pass
```



다른 앱도 같이 주어 집니다 rare/models.py



```
from common.models import Base

class ChildB(Base):
    pass
```



> common.ChilA.m2m 필드의 역방향 이름은,
>
> common_childa_related 이고,
>
> 역방향 쿼리 이름은, common_childas 가 될것입니다.
>
> common.ChildB.m2m 필드의 역방향 이름은,
>
> common_childb_related 이고,
>
> 역방향 쿼리 이름은, common_childbs 가 될것입니다.
>
> 마지막으로,
>
> rare.childB.m2m 의 역방향 이름은, rare_childb_related 가 되고,
>
> 역방향 쿼리 이름은, rare_childbs 가 될것입니다.



# Django Models 19편 - Model 상속 Multi-table inheritance 1편

## Model 상속 (Multi-table inheritance )



Django 모델을 상속 받는데에 있어서 3가지 스타일이 존재합니다.



1. Abstract base classes
2. Multi-table inheritance
3. Proxy Models



### Multi-table inheritance



Django 모델에서 지원하는 두번째 모델 상속 방식은, Multi-table inheritance 입니다.


각 모델이 계층구조에서, 자체적으로 모델일 경우 입니다.

각 모델은 각자의 데이터베이스 테이블에 해당 되고, 각각 쿼리문을 보내거나 생성될수 있습니다.



상속 관계는, 자식 모델과 부모 모델의 연결고리를 만들어 놓습니다.



예를들면,



```
from django.db import models 

class Place(models.Model):
    name = models.CharField(max_length=50)
    address = models.CharField(max_length=80)
    
class Restaurant(Place):
    serves_hot_dogs = models.BooleanField(default=False)
    serves_pizza = models.BooleanField(default=False)
```



Place 의 모든 필드들은, Restaurant 에서도 사용할수 있습니다.


하지만, 데이터는 다른 데이터베이스 테이블에 존재하게 됩니다. 따라서, 아래 상황들 둘다 가능합니다.



```
>>> Place.objects.filter(name="Bob's Cafe")
>>> Restaurant.objects.filter(name="Bob's Cafe")

In [1]: bobscafe = Place.objects.create(name="Bob's Cafe", address="Bob ba di rara")                                                                                      

In [2]: Place.objects.filter(name="Bob's Cafe")                                                                                                                           
Out[2]: <QuerySet [<Place: Place object (1)>]>

In [3]: Restaurant.objects.filter(name="Bob's Cafe")                                                                                                                      
Out[3]: <QuerySet []>
```



만약 Place 이면서, Restaurant 인 경우,


Place 객체에서 Restaurant 객체를 얻을수 있는데, 모델 이름의 소문자 형태를 사용하여 얻을수 있습니다.



```
In [4]: p = Restaurant.objects.create(name="pizza hut", address="789 Hume Highway", serves_hot_dogs=False, serves_pizza=True)                                             

In [5]: Place.objects.all()                                                                                                                                               
Out[5]: <QuerySet [<Place: Place object (1)>, <Place: Place object (2)>]>

In [7]: Restaurant.objects.all()                                                                                                                                          
Out[7]: <QuerySet [<Restaurant: Restaurant object (2)>]>

In [8]: p = Place.objects.get(id=2)    # p 가 Restaurant 객체 일때, 자식 클래스를 제공한다                                                                                                                                    

In [10]: p.restaurant                                                                                                                                                     
Out[10]: <Restaurant: Restaurant object (2)>
```



하지만, 위 예제에서, p 라는 인스턴스가 Restaurant 이 아니라, Place 객체로 직접 생성 되었다면,

`p.restaurant` 은 Restaurant DoesNotExist 예외를 발생 시켰을 겁니다.


Restaurant 을 Place 에 연결해 주는, 자동 생성된 OneToOneField 는 아래와 같이 생겼습니다.



```
Place_ptr = models.OneToOneField(
    Place, on_delete=models.CASCADE, 
    parent_link = True, 
    primary_key = True, 
)
```



Restaurant 에서 OneToOneField 의 paraent_link=True 로 설정해 줌으로써, 필드를 오버라이드 할수 있습니다.



### Meta and Multi-table inheritance



multi-table 상속 상황속에서는, 자식 클래스가 부모의 Meta 클래스를 상속 받는게 있을수 없는 일입니다.


모든 Meta 옵션들은 이미 부모 클래스에 적용 되어 있고, 이것을 다시 적용 한다는것은 단지 모순된 동작으로 이어지게 할 뿐입니다.



따라서, 자식 모델은, 부모의 Meta 클래스에 접근할수 없습니다. 그렇지만, 자식 모델이 부모 모델의 동작을 상속받는 몇가지 제한적 경우가 있습니다.



> 만약,
>
> 자식 모델이 ordering 속성 혹은 get_latest_by 속성을 지정하고 있지 않다면,
>
> 이 속성들을 부모 클래스에서 상속 받을수 있습니다.



만약 부모 모델이 ordering 을 가지고 있고, 자식 모델이 자연스러운 ordering 을 가지게 하고 싶으면,


명시적으로 비활성화를 시켜주면 됩니다.



```
class ChildModel(ParentModel):
    # ... 
    class Meta:
        # 부모 모델의 ordering 을 제거 해주기 
        ordering = []
```



# Django Models 19편 - Model 상속 Multi-table inheritance 2편

## Multi-table inheritance 2편



### Inheritance and reverse relations



multi-table 상속은 암시적으로, OneToOneField 를 사용하여 자식 클래스와 부모 클래스를 연결해 주기 때문에, 부모 클래스에서 자식 클래스로 이동이 가능해 집니다.



하지만, 이는 ForeignKey 와 ManyToManyField 관계들을 위한 기본 related_name 값을 사용합니다.



만일 우리가, ForeignKey 나 ManyToManyField 관게들을 부모의 서브클래스에 넣어준다면, 각 필드에 related_name 속성들을 지정해 주어야 합니다.



각 필드에 이 relate_name 속성을 지정해 주지 않는다면, Django는 유효성 에러를 발생 시킬겁니다.





예시를 들면,



```
from django.db import models 

class Place(models.Model):
    name = models.CharField(max_length=50)
    address = models.CharField(max_length=80)
    
class Restaurant(Place):
    serves_hot_dogs = models.BooleanField(default=False)
    serves_pizza = models.BooleanField(default=False)
    
# 새로운 서브 클래스 추가 

class Supplier(Place):
    customers = models.ManyToManyField(Place)
```



이렇게 코드를 짜면, 에러를 발생 시킵니다.


새로운 클래스를 추가하고, python manage.py makemigraitons 를 실행하면, 아래와 같은 에러가 출력 됩니다.



```
(django-tutorial)  dhkang  ~/django_tutorial/django_docs master python manage.py makemigrations
SystemCheckError: System check identified some issues:

ERRORS:
multitableinheritance.Supplier.customers: (fields.E305) Reverse query name 
for 'Supplier.customers' clashes with reverse query name for 'Supplier.place_ptr'.
        HINT: Add or change a related_name argument to the definition for 
'Supplier.customers' or 'Supplier.place_ptr'.
```





> related_name 을 아래와 같이 customers 필드에 추가를 하면, 에러가 해결됩니다.



```
from django.db import models

class Place(models.Model):
    name = models.CharField(max_length=50)
    address = models.CharField(max_length=80)
    
class Restaurant(Place):
    serves_hot_dogs = models.BooleanField(default=False)
    serves_pizza = models.BooleanField(default=False)
    

# 새로운 서브 클래스 추가 
class Supplier(Place):
    customers = models.ManyToManyField(Place, related_name='provider')
    # related_name='provider' 라고 지정을 해줌
```



`related_name` 을 지정해주고, python manage.py makemigrations 를 실행해주면, migrations 가 생성됩니다.



### Specifying the parent link field



이전에도 언급되었듯이, django 는 자동적으로 OneToONeField 를 사용하여, 자식 클래스를 Abstract base 클래스가 아닌 부모 클래스 모델에 연결해 줍니다. 만약 부모와 연결되는 속성들의 이름을 제어하고 싶다면, 스스로 OneToOneField 를 생성하고, parent_link = True 로 설정하여, 당신의 필드가 부모 클래스에 뒤쪽으로 연결 된다는것을 표시할수 있습니다.



# Django Models 20편 - Model 상속 (Proxy models - 1)

## Model 상속 (Proxy models)



### Proxy models



Multi-table 상속을 사용할때에는, 새로운 데이터베이스 테이블이 각 서브 클래스 마다 생성 됩니다.



이는 보통 우리가 의도하는 동작이고, 서브 클래스들은, 베이스 클래스에 존재하지 않는 추가적인 데이터 필드들을 저장하기 위한 공간이 필요합니다.



하지만, 때때로 모델의 파이썬 동작을 바꾸고 싶을때가 있습니다 - 기본 메니저를 변경하거나 혹은 새로운 메서드를 추가할때 처럼 말이죠.



이것이 바로 프록시 모델 상속이 존재하는 이유 입니다. 기존 모델을 위해서 새로운 proxy 를 생성하는것 입니다.



> 모델의 파이썬 동작을 바꾸고 싶을때, 기존 모델을 위한 새로운 proxy 를 생성 합니다.
>
> 예) 기본 메니져 변경, 새로운 메서드 추가 등등



> 프록시 (proxy) 라는 개념은 영문으로 직역하면, 일종의 대리권, 대리인 이라 생각하면 되고.
>
> 일종의 부모 클래스의 복제품이라고 생각하면 됩니다.
>
> 다만, 부모 클래스와 동일한 데이터베이스 테이블을 사용합니다.



여러분들은 프록시 모델의 인스턴스들을 생성, 삭제, 그리고 업데이트를 해줄수 있고.


모든 데이터들은 마치 기존 모델을 사용하느것처럼 저장될수 있습니다.



차이점은, 여러분들이 proxy 의 기본 모델 정렬 혹은 기본 메니져 같은 것들을 뭔본 모델을 바꿀 필요 없이 수정할수 있다는 점 입니다.



> 프록시 모델은 일반 모델처럼 생성 됩니다
>
> 하지만, proxy 속성 값을 Meta 클래스 안에서, True 로 지정해주어야 합니다 .



아래 코드를 예시로 들면,



```
from django.db import models 

class Person(models.Model):
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    
class MyPerson(Person):
    # Meta 클래스 안에, proxy = True 로 설정해줌 
    class Meta:
        proxy = True 
        
    def do_something(self):
        # ... 
        pass
```



MyPerson 클래스는, 부모 클래스인 Person 클래스와 같은 데이터베이스 테이블에서 동작 합니다.



특히, Person 의 어떤 새로운 인스턴스들도 MyPerson 을 통해서 접근이 가능합니다. MyPerson 의 새로운 인스턴스들도 Person 을 통해서 접근이 가능합니다.



```
In [1]: p = Person.objects.create(first_name="Colin", last_name="Firth")                                                                                                  
In [2]: MyPerson.objects.get(first_name="Colin")                                                                                                                          
Out[2]: <MyPerson: MyPerson object (1)>

In [3]: Person.objects.get(first_name="Colin")                                                                                                                            
Out[3]: <Person: Person object (1)>
```



```
class OrderPerson(Person):
	class MEta:
		ordering = ["last_name"]
		proxy = True
```



일반적인 Person 쿼리들은 정렬이 되지 않겠지만,


OrderedPerson 쿼리들은 last_name 기준으로 정렬이 될겁니다.



```
In [4]: a = Person.objects.create(first_name="동원", last_name="강")                                                                                                      

In [5]: b = Person.objects.create(first_name="지석", last_name="강")                                                                                                      

In [6]: c = Person.objects.create(first_name="문희", last_name="나")                                                                                                      

In [7]: d = Person.objects.create(first_name="민영", last_name="박")                                                                                                      

In [8]: e = Person.objects.create(first_name="태현", last_name="차")                                                                                                      

In [9]: f = Person.objects.create(first_name="성경", last_name="이")                                                                                                      

In [10]: Person.objects.all()                                                                                                                                             
Out[10]: <QuerySet [<Person: Firth>, <Person: 강>, <Person: 강>, 
<Person: 나>, <Person: 박>, <Person: 차>, <Person: 이>]>

# last_name 즉 성씨를 기준으로 정렬 
In [11]: OrderedPerson.objects.all()                                                                                                                                      
Out[11]: <QuerySet [<OrderedPerson: Firth>, <OrderedPerson: 강>, <OrderedPerson: 강>, 
<OrderedPerson: 나>, <OrderedPerson: 박>, <OrderedPerson: 이>, <OrderedPerson: 차>]>
```



프록시 모델들은, Meta 속성을 일반 모델들이 상속 받는 방식과 동일하게 상속 받습니다.



# Django Models 20편 - Model 상속 (Proxy models - 2)

## Model Inheritance proxy models - 2



### Base class restrictions



프록시 모델은 하나의 모델 클래스 (abstract 가 아닌것)을 상속 받아야 합니다


하지만, 다중의 모델 (abstract 가 아닌것)들을 상속 받을수 없습니다.

> 프록시 모델은, 다른 데이터 베이스 테이블들에 존재하는 행(row) 들끼리 연결을 지원하지 않습니다.



프록시 모델은 abstract 모델이 어떤 모델 필드도 정의하지 않는다는 전제하에, 여러개의 abstract 모델 클래스들을 상속 받을수 있습니다.



프록시 모델은 또한, 공통된 부모 클래스 (단, abstract 클래스가 아니여야 함)를 공유한다는 전제하에, 몇개라도 프록시 모델들을 상속 받을수 있습니다.



### Proxy model managers



프록시 모델에 어떤 model manager 도 지정하지 않는다면, 부모 모델의 manager 를 상속 받습니다.



만약 프록시 모델에 manager 를 정의 한다면, 정의된 manager 가 기본값이 됩니다.


물론, 어떤 manager 든지 부모 클래스에 정의된 manager 도 여전히 사용 가능합니다.



이전 포스팅에서 진행한 예시에 이어서, Person 모델의 쿼리에서 사용되는 기본 manager 를 바꿀수 있습니다.



```
from django.db import models

class NewManager(models.Manager):
    # ...
    pass 


class MyPerson(Person):
    objects = NewManager()
    
    class Meta:
        proxy = True
```



만약 새로운 manager 를 기존에 존재하는 기본값을 바꾸지 않고 프록시에 추가하고 싶으면, custom manager 문서에 나와 있는 기술들을 사용해서 바꿀수 있습니다.



새로운 manager 를 가지고 있는 클래스를 생성하고, 기본 베이스 클래스 뒤에, 생성한 클래스를 상속 시켜 주면 됩니다.



```
# 새로운 Manager 를 위한 Abstract 클래스를 생성해줍니다.
class ExtraManagers(models.Model):
    secondary = NewManager() 

    class Meta:
        abstract = True 

class MyPerson(Person, ExtraManagers):
    class Meta:
        proxy = True
```





### 프록시 상속과 관리되지않은 모델의 차이점



프록시 모델 상속은 Meta 클래스에 managed 속성을 사용한, 관리되지 않은 모델을 생성하는것과 비슷해 보일지 모릅니다.



조심스럽게 Meta.db_table 을 설정하여, 기존에 존재하는 모델의 그림자 같은 관리되지 않은 모델을 생성할수 있고, 파이썬 메서드도 추가할수 있습니다.



하지만, 그것은 매우 반복적이고 깨지기 쉬운 구조 입니다. 수정할때 마다, 모든 복사본들을 동기화 해야 하기 때문입니다.



한편으로는, 프록시 모델들은 정확하게 프록싱 하는 기존 모델의 동작 처럼 동작하게 만들어 졌습니다. 프록시 모델은 언제나 부모 모델과 동기화 되어 있습니다 (직접 필드와 메니져들을 부모에게서 상속 받기 때문).



보통 법칙들은

1. 기존 모델 혹은 데이터베이스를 미러링 하고, 모든 오리지널 데이터베이스 테이블 열들을 원하지 않을때, Meta.mangled=False 를 사용해 줍니다. 이 옵션은 데이터베이스 뷰들을 모델링 할때와 Django 로 제어되지 않는 테이블들을에 유용합니다.

1. 만약 모델의 파이썬 동작만 바꾸고 싶은데, 같은 필드들을 오리지널과 같이 유지하고 싶을때, Meta.proxy=True 로 설정해 줍니다. 이 설정은, 데이터가 저장될때, 프록시 모델이 정확한 오리지널 모델 저장구조의 복사본이 됩니다.



# Django Models 21편 - Multiple Inheritance

## Multiple Inheritance



파이썬에서 서브클래스를 받는것과 같이, DJango 모델은 다수의 부모 모델들을 상속 받는게 가능합니다.


하지만, 파이썬에서 일반적으로 이름짓는 방식들이 이 Django 모델 클래스에 적용된다는점을 염두해 두고 있어야 합니다 (예, 예약어들은 클래스명으로 사용할수 없음)



특정 이름, (예, Meta) 가 나타나는 첫번째 기본 클래스가 사용 될것입니다. 예를들면, 이것의 의미는 만약 다수의 부모 클래스들이 Meta 클래스를 하나씩 가지고 있는다면, 오직 첫번째 것만 사용이 되고, 나머지들은 무시 됩니다.



보편적으로 우리는, 다수의 부모 클래스로부터 상속 받을 필요가 없는 경우가 많습니다. 주 사용처는, “mix-in” 클래스들을 사용할때 유용하다는 점입니다. 특정 추가 필드를 추가하거나 “mix-in” 을 상속받는 모든 클래스에 메서드를 추가할때 유용합니다.



당신의 상속 구조를 가능한한 최대로 간단하고 명료하게 유지하도록 노력해야 합니다. 그래야, 특정 정보가 어디서 왔는지 알아내기 위해 고생할 필요가 없어집니다.





> 다수의 모델에서 상속받을때,
>
> 공통된 id, primary key 필드를 가지고 있는 모델들로부터 상속받는것은 에러를 발생 시킬겁니다.
>
> 제대로 다중 상속을 사용하기 위해서는,
>
> 명시적으로 AutoField 를 base 모델들 안에 사용합니다.



```
class Article(models.Model):
    article_id = models.AutoField(primary_key=True)
    

class Book(models.Model):
    book_id = models.AutoField(primary_key=True)
    
class BookReview(Book, Article):
    pass
```



혹은 AutoField 를 가지기 위해서, 공통 조상을 사용합니다. 이것은 각 부모 모델과 공통 조상 사이에 명시적으로 OneToOneField 를 사용하는것을 필요로 합니다.



그래야, 자식 클래스에 자동으로 생성되었거나 상속이 된 필드들 간에 충돌을 피할수 있습니다.



```
class Piece(models.Model):
    pass 

class Article(Piece):
    article_piece = models.OneToOneField(Piece, on_delete=models.CASCADE, parent_link=True)
    
class Book(Piece):
    book_piece = models.OneToOneField(Piece, on_delete=models.CASCADE, parent_link=True)
    
class BookReview(Book, Article):
    pass
```



### Field name “hiding” is not permitted



> 일반 파이썬 클래스 상속에서,
>
> 자녀 클래스가 부모 클래스의 속성을 override 하는것이 허용됩니다.
>
> 하지만, Django 에서는, 모델 필드들에 허용되지 않습니다.



만약에 Abstract 모델 베이스 클래스가 author 라는 필드를 가지고 있으면, 이 베이스 클래스를 상속받는 어떤 클래스도 author 라는 필드 혹은 Author 라는 속성을 정의할수 없습니다.



이런 제약은, Abstract 모델에서 상속받은 모델 필드들에는 적용되지 않습니다. 이런 경우에 필드들은 다른 필드나 값으로 override 될수 있고, field_name = None 으로 제거 될수도 있습니다.



> 주의사항
>
> 모델 managers 는 Abstract base 클래스들에서 상속됩니다.
>
> 상속된 Manager 로 참조되는 상속된 필드를 Override 하는것은 버그를 일으킬수 있습니다.



> 노트
>
> 어떤 필드들은 모델에 추가 속성들을 정의 합니다
>
> 예를들어,
>
> ForeignKey 는 _id 가 필드명에 추가된 추가 속성을 정의하고, related_name 과 related_query_name 을 Foreign 모델에 정의합니다.
>
> 이 추가 속성들은 override 될수 없습니다. 만약에 해당 필드를 저으이하는 필드가 수정 되었거나,
>
> 삭제되어서 해당 추가 속성을 더이상 정의하지 않는한, 이 추가 속성들은 override 될수 없습니다.



부모 모델안에 필드들을 overriding 하는것은, 새로운 인스턴스들을 시작하거나 직렬화 같은 영역 안에서 어려움을 초래할수 있습니다. 이런 기능들은, 일반 파이썬 클래스 상속과 같은 방식으로 처리하지 않아도 되는것입니다. 따라서, Django 모델 상속과 Python 클래스 상속의 차이점은 임의적이지 않습니다.



이 제약은 필드 인스턴스들인 속성에만 적용됩니다.



표준 파이썬 속성들은, 원한다면, override 될수 있습니다.



속성 이름에도 적용이 됩니다. 만약 수동으로 데이터베이스 컬럼명을 바꾸고 있다면, multi-table 상속을 위해서 같은 컬럼명을 자식 모델과 조상 모델에 나타나게 할수 있습니다. (두개의 다른 데이터베이스 테이블에 있는 컬럼들 입니다.)



만약, 어떤 조상 모델이던지간에, 어떠한 모델 필드를 override 한다면, django 는 FieldError 를 발생 시킵니다.



# Django Models 22편 - 패키지안 모델 정리하기

## Models - Organising models in a package

### Organising models in a package



`manage.py startapp` 커맨드는 models.py 를 포함한 어플리케이션 구조를 생성합니다. 많은 모델들을 가지고 있다면, 별도의 파일에 정리를 해두는것도 유용한 방법입니다.



그렇게 하기 위해서는, models 패키지를 생성해야 합니다.



1. models.py 를 없애고
2. app이름/models/디렉토리 를 생성합니다
3. app이름/models/디렉토리 안에, `__init__.py` 파일을 생성하고, 당신의 모델을 저장할 파일들을 생성합니다
4. `__init__.py` 파일에 import 해주어야 합니다



예를들어, organic.py , synthetic.py 가 모델 디렉토리 안에 있었다면,



> `__init__.py` 파일에 import 해주는것을 잊지 않아야 합니다.
>
> ```
> myapp/models/__init__.py
> ```





```
from .organic import Person 
from .synthetic import Robot
```



명시적으로 각 모델을 가져오는것은, `from .models import *` 을 사용하는것보다 이점이 있습니다.


이름 공간을 어수선하게 하지 않게되고, 코드를 좀더 읽기 좋게 하고, 코드 분석 툴들을 더 유용하게 해줍니다.



