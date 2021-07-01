# Django TodoList

출처: https://djangojeng-e.github.io/2020/05/15/TodoList-1%ED%8E%B8-%EC%B4%88%EA%B8%B0%EC%84%A4%EC%A0%95/

# TodoList 1편 - 초기설정

## 미니프로젝트 시작

간단한 todolist 프로젝트를 진행하며, 이제까지 공부한 내용을 복습도 하고, 그동안 구현하지 못했던것들도 찾으면서 구현해 보려 합니다.
내가 할일을 생성하고, 업데이트하고, 삭제하는, Todolist 사이트를 만들어 봅니다.



## 개발 개요

| **개발 정보**  | **디테일**              |
| :------------- | :---------------------- |
| python version | 3.8.0                   |
| os             | ubuntu18.04             |
| django version | 3.06                    |
| CSS Framework  | Bulma                   |
| 개발 툴        | visual studio code      |
| 배포           | AWS                     |
| 데이터베이스   | sqlite3 혹은 postgreSQL |

- 개발내역은, 프로젝트의 진행 상황과 숙련도에 따라서 달라질수 있음.



## 가상환경 세팅

터미널에서 아래 명령어를 실행하여, 가상환경을 설정하고, 프로젝트 폴더에 적용해 줍니다.

```
pyenv virtualenv 3.8.0 to-do-list
```

스크린샷,

![img](https://djangojeng-e.github.io/2020/05/15/TodoList-1%ED%8E%B8-%EC%B4%88%EA%B8%B0%EC%84%A4%EC%A0%95/image1.png)

프로젝트 폴더앞에, (to-do-list) 라고 써지면, 잘 적용 된겁니다.



## VisualStudioCode 열기

프로젝트 폴더에 가상환경이 잘 적용 되었으면, Visual Studio Code 로 해당 프로젝트 폴더를 열어줍니다.


![img](https://djangojeng-e.github.io/2020/05/15/TodoList-1%ED%8E%B8-%EC%B4%88%EA%B8%B0%EC%84%A4%EC%A0%95/image2.png)



### 가상환경에 장고 설치





가장 중요한 Django 를 설치해 줍니다.



```
pip install django
```


![img](https://djangojeng-e.github.io/2020/05/15/TodoList-1%ED%8E%B8-%EC%B4%88%EA%B8%B0%EC%84%A4%EC%A0%95/image3.png)



> pip list 를 통하여,
>
> 현재 가상환경에 설치된 패키지를 확인해 봅니다.
>
> Django 3.0.6 이 정상적으로 설치된것을 확인 할수 있습니다.



```
Package    Version
---------- -------
asgiref    3.2.7  
Django     3.0.6  
pip        19.2.3 
pytz       2020.1 
setuptools 41.2.0 
sqlparse   0.3.1  
WARNING: You are using pip version 19.2.3, however version 20.1 is available.
You should consider upgrading via the 'pip install --upgrade pip' command.
```





## 가상환경의 패키지 목록





> pip freeze > requirements.txt 명령어를 실행하여,
>
> 해당 가상환경에 설치된 모든 패키지들을 리스트 해줍니다.



```
pip freeze > requirements.txt
```





> 이 프로젝트가 나중에 누군가와 공유된다면,
>
> 이 프로젝트를 사용하고 싶은 사람들은, 나중에 아래 명령어를 통해서
>
> 프로젝트에 들어간 패키지들을 설치하고 사용합니다.





```
pip install -r requirements.txt
```





## 마치며..



기본적인 개발 환경 설정을 마쳤습니다.



# TodoList 2편 - 프로젝트와 앱 생성하기

## 프로젝트 생성하기



프로젝트 폴더에서, 아래 터미널 명령어를 실행하여, todo list 프로젝트 설정 폴더를 생성해 줍니다.



```
django-admin startproject config .
```



아래와 같은 구조가 나오면 됩니다.



```
 ~/mini_projects/todolist   master  django-admin startproject config .
~/mini_projects/todolist   master  ls
config  manage.py  requirements.txt
~/mini_projects/todolist   master  tree
.
├── config
│   ├── asgi.py
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── manage.py
└── requirements.txt
```



프로젝트 설정 폴더가 잘 생성되었으면, 앱을 생성해 줍니다.



## 프로젝트 앱 생성하기



> todo 앱을 생성하여,
>
> todo list 의 등록, 관리, 삭제를 수행하는 어플리케이션을 만들것입니다.



아래 명령어를 실행하여, 프로젝트 폴더안에 todo 라는 앱을 만들어 줍시다.



```
python manage.py startapp todo
```





생성후의 디렉토리 구조는 아래와 같습니다.



```
.
├── config
│   ├── asgi.py
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── manage.py
├── requirements.txt
└── todo
    ├── admin.py
    ├── apps.py
    ├── __init__.py
    ├── migrations
    │   └── __init__.py
    ├── models.py
    ├── tests.py
    └── views.py
```





## 마치며..



프로젝트와 앱이 생성되었으니,

이제 개발을 위한 모든 준비가 완료 되었습니다.



# TodoList 3편 - 모델설계하기

## Todo List 모델 설계하기





### INSTALLED_APPS 에 앱등록하기



config/settings.py 파일에 INSTALLED_APPS 안에, todo 앱을 등록해 줍니다.


![img](https://djangojeng-e.github.io/2020/05/18/TodoList-3%ED%8E%B8-%EB%AA%A8%EB%8D%B8%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0/image1.png)

> settings.py 파일을 수정해주는 김에,
>
> 언어설정과, 타임존 세팅까지 해줍니다.



```
LANGUAGE_CODE - 'ko-kr'
TIME_ZONE = 'Asia/Seoul'
```



![img](https://djangojeng-e.github.io/2020/05/18/TodoList-3%ED%8E%B8-%EB%AA%A8%EB%8D%B8%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0/image2.png)



## 모델 계획하기



> 내가 할일 이라는, Todo List 의 모델을 설계해 봅니다.
>
> 기본적으로, 아래와 같이 테이블로 정리된 사항들을 가지고 있었으면 좋겠습니다.



| **번호** | **필드명**     | **설명**                   | **데이터타입** |
| :------- | :------------- | :------------------------- | :------------- |
| 1        | name           | 내가 할일에 대한 짧은 이름 | 문자           |
| 2        | description    | 내가 할일에 대한 정보      | 긴 문자열      |
| 3        | date_created   | 할일 생성 날짜             | 날짜           |
| 4        | date_deadline  | 데드라인 날짜              | 날짜           |
| 5        | images_related | 관련된 이미지 (선택사항)   | 이미지         |
| 6        | files_related  | 관련된 파일들 (선택사항)   | 파일           |





하나의 TodoList 는, 관련된 images 와 files 필드들을 가지어,

할일에 대한 더 많은 세부정보를 첨부할수 있게 해주었습니다. 이를 모두 한 테이블에 넣기 보다는,





아래와 같이, 3가지 테이블로 나누고, 필드들을 정리해보았습니다.



| **TodoList**  |             | **TodoList_images** |             | **TodoList_files** |             |
| :------------ | :---------- | :------------------ | :---------- | :----------------- | :---------- |
| 필드명        | 데이터 타입 | 필드명              | 데이터 타입 | 필드명             | 데이터 타입 |
| name          | CharField   | todo                | ForeignKey  | todo               | ForeignKey  |
| description   | TextField   | image               | ImageField  | file               | FileField   |
| date_created  | DateField() |                     |             |                    |             |
| date_deadline | DateField() |                     |             |                    |             |





> 하나의 Todo 는 여러개의 이미지들을 가질수 있고,
>
> 하나의 Todo 는 여러개의 파일들을 가질수 있습니다.
>
> 두 테이블은 하나의 Todo 에 ForeignKey 로 연결 됩니다.





## models.py 파일 작성하기





위에서 계획했던 모델링 대로, models.py 파일을 실제로 작성해 봅니다.


저는 아래와 같이 작성해 주었습니다.





```
from django.db import models
from datetime import date
import datetime

# Create your models here.


class TodoList(models.Model):
    name = models.CharField(max_length=40, verbose_name="할일제목")
    description = models.TextField(max_length=200, verbose_name="할일세부사항")
    date_created = models.DateField(auto_now_add=True, verbose_name="생성날짜")
    date_deadline = models.DateField(verbose_name="데드라인")
    
    def remaining_days(self):
        delta = self.date_deadline - date.today()
        days = delta.days 
        return days


    def __str__(self):
        return f'{self.name} | {self.description} | {self.date_created} | 		  {self.date_deadline}'


class TodoList_images(models.Model):
    todo = models.ForeignKey(TodoList, on_delete=models.CASCADE)
    image = models.ImageField(upload_to='todo/images/%Y/%m', blank=True)


class TodoList_files(models.Model):
    todo = models.ForeignKey(TodoList, on_delete=models.CASCADE)
    files = models.FileField(upload_to='todo/files/%Y/%m', blank=True)
```





## Images 를 위한 패키지 Pillow 설치





모델을 다 작성한 다음, migrations 를 시도하면, 아래와 같은 오류 메시지가 뜹니다.



```
python manage.py makemigrations
SystemCheckError: System check identified some issues:

ERRORS:
todo.TodoList_images.image: (fields.E210) Cannot use ImageField because Pillow is not installed.
        HINT: Get Pillow at https://pypi.org/project/Pillow/ or run command "python -m pip install Pillow".
```



이 에러를 해결하기 위해서는, 이미지 파일을 위한 패키지인, pillow 를 설치해야만 합니다.


아래 명령어를 실행하여, 패키지를 설치해 줍니다.

```
pip install Pillow
```





설치가 완료되었으면, makemigrations 와 migrate 를 실행하여, 데이터베이스를 생성해 줍니다.



## makemigrations, migrate 해주기





python manage.py makemigrations 를 실행,


![img](https://djangojeng-e.github.io/2020/05/18/TodoList-3%ED%8E%B8-%EB%AA%A8%EB%8D%B8%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0/image3.png)

python manage.py migrate 를 실행,


![img](https://djangojeng-e.github.io/2020/05/18/TodoList-3%ED%8E%B8-%EB%AA%A8%EB%8D%B8%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0/image4.png)



# TodoList 4편 - 모델 확인하기

## Todo List 모델 확인하기





지난 포스팅에 작성했던 모델이 잘 작동하는지 확인을 해보아야 겠습니다.



어드민을 통해 확인을 진행 해보려 합니다.


어드민에 todo 앱을 등록해 주는데서 부터 시작해 봅니다.



## 어드민 페이지에 todo 앱 등록하기



todo/admin.py 파일을 열고, 아래와 같이 어드민에, TodoList 모델을 등록해 줍니다.





```
from django.contrib import admin 
from .models import TodoList, TodoList_files, TodoLies_images 
# Register your models here. 


class TodoListAdmin(admin.ModelAdmin):
    pass 


class TodoList_imagesAdmin(admin.ModelAdmin):
    pass 


class TodoList_filesAdmin(admin.ModelAdmin):
    pass 


admin.site.register(TodoList, TodoListAdmin)
admin.site.register(TodoList_images, TodoList_imagesAdmin)
admin.site.register(TodoList_images, TodoList_filesAdmin)
```





## 어드민 관리자 계정 생성하기



아래 명령어를 실행해서, 관리자 계정 하나를 생성합니다.



```
python manage.py createsuperuser
```



계정 실행을 완료 하였으면, python manage.py runserver 를 실행하여,
서버를 실행한다음, 어드민 사이트로 접속 합니다.



```
python manage.py runserver
```





http://127.0.0.1:8000/admin/ 에 접속하여 로그인 하면,

아래와 같은 화면을 볼수 있을겁니다.



![img](https://djangojeng-e.github.io/2020/05/19/TodoList-4%ED%8E%B8-%EB%AA%A8%EB%8D%B8-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0/image1.png)



## Todo List 하나 추가해 보기



Todo Lists 에 Add 버튼을 눌러서, todo list 하나를 추가해 보려 합니다.


아래와 같이, 디테일을 입력후, SAVE 버튼을 눌러봅니다.


![img](https://djangojeng-e.github.io/2020/05/19/TodoList-4%ED%8E%B8-%EB%AA%A8%EB%8D%B8-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0/image2.png)

아래와 같이 잘 생성이 됩니다





![img](https://djangojeng-e.github.io/2020/05/19/TodoList-4%ED%8E%B8-%EB%AA%A8%EB%8D%B8-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0/image3.png)





## 마치며..



아쉬운대로, 일단 모델의 기본 동작은 잘 되고 있는것 같습니다.

하지만, 프로젝트 진행중에, 모델을 다시 수정해 줘야 할일이 올지 몰릅니다.



# TodoList 5편 - 어드민 수정하기

## Todo List 어드민 수정하기



지난 포스팅에서, 모델이 잘 작동하는것을 확인 해 보았습니다.


하지만, 어드민 페이지에서 todo 를 추가할때 뭔가 아쉬운점이 있습니다.



분명히, Todo 에는 연관된 이미지도 파일도 올라갈수 있어야 하는데.


어드민에 등록되어 잇는 Todo 는, 아주 기본적인 필드들만 가진 TodoList 를 생성할수 있습니다.



![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image1.png)

> TodoList 를 생성할때,
>
> 이미지와 파일도 추가할수 있게 어드민에 등록해 보겠습니다.





## todo/admin.py 파일 수정하기





inline 클래스들을 작성해주고,

TodoListAdmin 클래스의 inlines = [] 에 넣어주면 됩니다.



```
from django.contrib import admin 
from .models import TodoList, TodoList_files, TodoList_images 
# Register your models here. 


class TodoList_filesInline(admin.StackedInline):
    model = TodoList_files 
    
    
class TodoList_imageInline(admin.StackedInline):
    model = TodoList_images
    

class TodoListAdmin(admin.ModelAdmin):
    inlines = [TodoList_filesInline, TodoList_imageInline]
    list_display = ('name', 'description', 'date_created', 'date_deadline', 	                       'remaining_days')
    list_filter = ['date_created']
    
    
admin.site.register(TodoList, TodoListAdmin)
```



어드민을 위와 같이 고쳐주면, 어드민에서 TodoList 의 표시가 마법처럼 바뀝니다.


![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image2.png)





> 그리고, 추가할때
>
> 파일과 이미지도 넣을수 있습니다.


![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image3.png)![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image4.png)![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image5.png)





파일 하나, 이미지 하나를 추가한뒤에, 저장 버튼을 눌러줍니다.


파일과 이미지를 가지고 있는 투두리스트 생성에 성공 했습니다.



![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image6.png)





모델에서 파일이나, 이미지를 upload 할시에, 저장할 폴더를 생성해 주었기 때문에,

todo/images 폴더와, todo/files 폴더가 생성된것을 확인 할수 있습니다.



![img](https://djangojeng-e.github.io/2020/05/20/TodoList-5%ED%8E%B8-%EC%96%B4%EB%93%9C%EB%AF%BC-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0/image7.png)



## 마치며..



admin.py 파일을 어떻게 수정해 주었는지 복습하고 넘어갑니다.



Inline 클래스들을 생성해주고 (model 을 지정함).



기존 TodoListAdmin(models.ModelAdmin) 클래스에서, inlines = [] 에 넣어줍니다.

list_display 에 모델에 있는 필드명들을 넣어주고, list_filter 는 생성날짜 기준으로 해주었습니다.



# TodoList 6편 - 리스트 페이지 만들기

Todo 리스트가 출력될 화면을 만들어 볼겁니다.


현재까지는, 서버를 실행하고, 브라우저로 접속하면, 아래와 같은 화면이 뜹니다.


![img](https://djangojeng-e.github.io/2020/05/21/TodoList-6%ED%8E%B8-%EB%A6%AC%EC%8A%A4%ED%8A%B8-%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0/image1.png)



## Todo리스트를 화면에 출력해줄 View 작성하기



현재 데이터베이스에는, Todo List 두개가 추가 되어 있습니다. (수동으로 생성해줌)


이것을 화면에 뿌려줄 페이지가 필요한데, 페이지에 화면을 출력해 주기 위해서는,


이를 처리하는 함수나 클래스가 필요합니다.


이 함수나 클래스들은, todo/views.py 파일에 작성 합니다. 이를 클래스뷰를 사용하여 진행해 봅니다.


todo/views.py 파일을 아래와 같이 작성해 줍니다.



```
from django.shortcuts import render
from .models import TodoList, TodoList_files, TodoList_images 
from django.views import generic 

# Create your views here. 


class IndexView(generic.ListView):
    context_object_name = 'to_do_list'
    template_name = 'todo/todo-lists.html'
    
    def get_queryset(self):
        return TodoList.objects.all()
```





## View 를 통해 그려줄 페이지 (템플릿) 생성



todo/templates/todo/ 폴더를 생성한 후에,


폴더 안에, todo-lists.html 파일을 생성하고, 아래와 같이 작성해 줍니다.





```
<ul>
    {% for todo in to_do_list %}   
    # to_do_list 는 클래스에서 지정된 객체 리스트, context_object_name 에 지정된 값 
    # 이 객체에는, 모든 TodoList 를 포함한, TodoList.objects.all() 을 포함 
    # def get_queryset(self) 를 통해서 객체를 포함함 
    
    <li>할일 제목 : {{ todo.name }}</li>
    <li>생성 날짜 : {{ todo.date_created }}</li>
    <li>데드라인 날짜 : {{ todo.date_deadline }}</li>
    <li>남은 일수 : {{ todo.remaining_days }}</li>
    
    {% endfor %}
    
</ul>
```





## 요청을 보낼 URL 설정





todo/urls.py 파일을 설정해 줍니다



```
from django.contrib import admin 
from django.urls import path 
from .views import IndexView 


urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
]
```



config/urls.py 파일에서, todo/urls.py 파일을 연결해 줍니다.



```
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('todo/', include('todo.urls')),
]
```





## 투두리스트 화면 확인하기





python manage.py runserver 를 실행하여, 서버를 시작한 후에,

http://127.0.0.1:8000/todo/ 에 접속합니다.



view 에서 투두리스트를 작업하여, 화면에 그려주고 있다는것을 확인할수 있습니다



![img](https://djangojeng-e.github.io/2020/05/21/TodoList-6%ED%8E%B8-%EB%A6%AC%EC%8A%A4%ED%8A%B8-%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0/image2.png)

# TodoList 7편 - BULMA CSS 적용하기

## Bulma CSS Framework 적용



![img](https://djangojeng-e.github.io/2020/05/22/TodoList-7%ED%8E%B8-BULMA-CSS-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image1.png)



부트스트랩과 비슷한 CSS Framework 의 하나인 BULMA 를,


이번 투두 리스트 미니 프로젝트에 적용해 보려 합니다.
처음 접해보는 CSS 프레임워크지만, 시도한번 해보려 합니다.





## 참고 사이트



CSS 를 프로젝트에 적용하기 위한 참고 사이트는 아래와 같습니다.



1. django-bulma 패키지 https://github.com/timonweb/django-bulma
2. bulma 공식 페이지 [https://bulma.io](https://bulma.io/)





## django-bulma 패키지 설치





아래 명령어를 실행하여, bulma 프레임워크를 장고 프로젝트에 설치해 줍니다.



```
pip install django-bulma
```


![img](https://djangojeng-e.github.io/2020/05/22/TodoList-7%ED%8E%B8-BULMA-CSS-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image2.png)

## settings.py 파일 INSTALLED_APPS 등록





config/settings.py 파일안, INSTALLED_APPS 에 아래와 같이,


bulma 를 등록해줍니다.



```
# Application definition

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    
    'todo',
    'django_extensions',

    # Bulma CSS Framework 
    'bulma',
]
```





## Bulma 프레임워크를 사용할 static 폴더



프레임워크 파일들을 가지고 있을, 정적파일 폴더를 생성하고 설정해 줍니다.



```
.
├── config
│   ├── asgi.py
│   ├── __init__.py
│   ├── urls.py
│   └── wsgi.py
├── db.sqlite3
├── manage.py
├── requirements.txt
├── static            # 프로젝트 폴더에, static 폴더를 생성 (manage.py와 같은 위치)
└── todo
    ├── admin.py
    ├── apps.py
    ├── __init__.py
    ├── migrations
    ├── models.py
    ├── templates
    │   └── todo
    │       └── todo-lists.html
    ├── tests.py
    ├── urls.py
    └── views.py
```





> static 폴더 생성을 완료 하였으면,
>
> settings.py 파일을 열고, STATICFILES_DIRS = [] 을 설정해주어야 합니다.



```
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/3.0/howto/static-files/

STATIC_URL = '/static/'
STATICFILES_DIRS = [os.path.join(BASE_DIR, 'static')]
```





## Bulma 프레임워크 복사하기





static 폴더에, 수동으로 BULMA 프레임워크 파일들을 복사해주어도 괜찮지만,


아래 명령어를 실행해서, bulma css 파일들을 static 폴더로 복사해 올수 있습니다.



```
python manage.py copy_bulma_static_into_project
```



명령어를 실행하면,
static 폴더안에, bulma 폴더가 생기고, 그 안에 css 파일들이 들어 있는것을 확인 할수 있습니다.



![img](https://djangojeng-e.github.io/2020/05/22/TodoList-7%ED%8E%B8-BULMA-CSS-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image3.png)





## bulma CSS 를 템플릿에 적용하기



CSS 파일들을 프로젝트에 가져왔으면, 이제 적용해 보아야 겟죠?



### base.html 파일 생성하기



프로젝트 전반에 걸쳐서, 이 프레임워크를 적용시킬수 있는 base.html 파일을


templates/base.html 경로에 생성을 합니다



이 base.html 파일에, static 폴더에 있는 bulma css 파일들을 적용하고.
이 base.html 파일이 다른 페이지들에 확장되서 쓰여질겁니다.



templates/base.html 파일을 아래와 같이 생성하고 작성해줍니다.



```
{% load static %}
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    
    <title>To do List - {% block title %}{% endblock %}</title>
    <link rel="stylesheet" type="text/css" href="{% static 'bulma/css/style.css' %}"> 

    {% block head %} {% endblock %}
</head>
    
<body>
{% block contents %}
{% endblock %}
</body>
```



### todolist 템플릿을 수정


이전 포스팅에 만들어 놓은 투두리스트 - 리스트페이지인

todo/templates/todo/todo-lists.html 파일 상단에 아래와 같은 내용을 추가해주고, 수정해 줍니다



base.html 파일을 상속 받습니다.



```
{% extends 'base.html' %}

{% block title %}내가 할일 뭐지?{% endblock %}

{% block content %}
  
<ul>
{% for todo in to_do_list %}

<li>할일 제목 : {{ todo.name }}</li>


<li>생성 날짜 : {{ todo.date_created }}</li>

<li>데드라인 날짜 : {{ todo.date_deadline }}</li>

<li>남은 일수 : {{ todo.remaining_days }}</li>

{% endfor %}
</ul>

{% endblock content %}
```





## Bulma 프레임워크 적용 확인



투두리스트를 리스트 해주는 페이지에 다시 접속해서,


CSS 프레임워크가 잘 적용 되었을지 확인 해봅니다



적용전,


![img](https://djangojeng-e.github.io/2020/05/22/TodoList-7%ED%8E%B8-BULMA-CSS-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image4.png)





적용후,


![img](https://djangojeng-e.github.io/2020/05/22/TodoList-7%ED%8E%B8-BULMA-CSS-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image5.png)





해당 이미지는, 기본 템플릿만 적용한 이미지이지만,
어쨋든, 글씨꼴이나 색깔이 느낌이 전반적으로 바뀌었다면, 잘 적용 된것입니다.



# TodoList 8편 - 프로젝트 Templates 설정

이전 포스팅에서,



BULMA CSS를 프로젝트에서 사용하기 위한 준비를 하였습니다.


static 폴더안에 Bulma 폴더가 생성되고, 그안에 CSS 폴더안에, style.css 파일이 생성되었습니다.





이제 이 CSS 를 적용한 기본 템플릿을 생성하고,


이 기본 템플릿을 다른 페이지들이 상속받아 사용하게 해봅니다.



## Templates 폴더 생성





프로젝트 폴더안에, templates 폴더를 생성합니다.


그리고, 그 폴더안에, base.html 파일을 생성합니다. 아래 트리 구조 형태로 생성합니다.



```
.
├── config
│   ├── asgi.py
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── db.sqlite3
├── manage.py
├── requirements.txt
├── static
│   └── bulma
│       ├── css
│       │   ├── style.css
│       │   ├── style.css.map
│       │   └── style.min.css
│       └── sass
│           ├── package.json
│           ├── package-lock.json
│           └── style.sass
├── templates
│   └── base.html
└── todo
    ├── admin.py
    ├── apps.py
    ├── __init__.py
    ├── migrations
    │   ├── 0001_initial.py
    │   ├── 0002_auto_20200511_1242.py
    │   ├── __init__.py
    ├── models.py
    ├── templates
    │   └── todo
    │       └── todo-lists.html
    ├── tests.py
    ├── urls.py
    └── views.py
```





## 생성된 templates 폴더 설정하기



생성된 templates 폴더를,


프로젝트의 templates 폴더로 사용하기 위해서,
config/settings.py 파일에 templates 폴더를 지정해주어야 합니다.



settings.py 파일안에, TEMPLATES 부분에, DIR 값을 아래와 같이 설정해 줍니다.



```
# settings.py
TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [os.path.join(BASE_DIR, 'templates')], # Templates folder 설정 
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]
```





## base.html 파일 꾸미기



templates/base.html 파일은, 다른 템플릿 페이지들이 상속받아서 사용할 페이지 입니다.

base.html 파일을 아래와 같이 생성해 줍니다. 이 파일은 BULMA CSS 프레임워크가 적용되어 있고,
이 파일을 상속받는 다른 페이지들도, BULMA CSS 적용이 될것입니다.





```
{% load static %}
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    {% block title %}
    <title>To do List</title>
    {% endblock %}

    {% block head %} {% endblock %}
</head>
<body>
    
    {% block content %} 

    {% endblock %}
    
</body>
</html>
```





위와 같은 구조를 만들었으면,

이제 BULMA CSS 프레임워크를 이용하여, 기본 레이아웃 구조를 만들어 줍니다.





첫번째로, 네비게이션 바를 만들어 놓을겁니다.

이 네비게이션 바를 base.html 파일에 만들어놓고,
다른 페이지들이 이 base.html 파일을 확장하여 사용하면,

base.html 파일에 있는 네비게이션 바가, 기본으로 다른 페이지들에도 보이게 될것입니다.





Bulma CSS Navbar 를 적용하기 위해서, 아래 사이트를 참조합니다.



https://bulma.io/documentation/components/navbar/



이곳에 나와 있는 Basic Navbar 를 조금 수정하여, 사용할것입니다.

위에 작성한, base.html 파일에, 이 Bulma Navbar 를 아래와 같이 수정하여 붙여 놓습니다.





```
{% load static %}
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    
    <title>To do List - {% block title %}{% endblock %}</title>
    <link rel="stylesheet" type="text/css" href="{% static 'bulma/css/style.css' %}"> 

    {% block head %} {% endblock %}
</head>
<body>

  <nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a class="navbar-item" href="#">
        <img src="{% static 'images/todo_list.jpg' %}" width="112" height="28">
      </a>
  
      <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>
  
    <div id="navbarBasicExample" class="navbar-menu">
      <div class="navbar-end">
        
  
      <div class="navbar-end">
        <a class="navbar-item">
          Home
        </a>
  
        <a class="navbar-item">
          To Do Lists
        </a>
  
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">
            More
          </a>
  
          <div class="navbar-dropdown">
            <a class="navbar-item">
              About
            </a>
            <a class="navbar-item">
              Jobs
            </a>
            <a class="navbar-item">
              Contact
            </a>
            <hr class="navbar-divider">
            <a class="navbar-item">
              Report an issue
            </a>
          </div>
        </div>
      </div>
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-primary">
              <strong>Sign up</strong>
            </a>
            <a class="button is-light">
              Log in
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>

  <img src="{% static 'images/main.jpg' %}" width="100%">
  
    {% block content %} 

    {% endblock %}
   
</body>
</html>
```





## todolist_list.html 을 메인페이지로 사용



base.html 파일을 확장하는, templates/todollist_list.html 파일을 만들고,

여기에 Todo list 들이 나오게 할겁니다.





1. templates/todolist_list.html 파일 생성
2. todo/views.py 파일 수정
3. todo/urls.py 파일 수정



### templates/todolist_list.html 파일 생성후 작성





```
{% extends 'base.html' %}

{% block title %}할일 목록{% endblock %}

{% block content %}
  
<ul>
{% for todo in to_do_list %}

<li>할일 제목 : {{ todo.name }}</li>


<li>생성 날짜 : {{ todo.date_created }}</li>

<li>데드라인 날짜 : {{ todo.date_deadline }}</li>

<li>남은 일수 : {{ todo.remaining_days }}</li>

{% endfor %}
</ul>

{% endblock content %}
```





### todo/views.py 파일 수정



```
from django.shortcuts import render
from .models import TodoList, TodoList_files, TodoList_images
from django.views import generic

# Create your views here.


class IndexView(generic.ListView):
    context_object_name = 'to_do_list'
    

    def get_queryset(self):
        return TodoList.objects.all()
```





### config/urls.py 파일 수정





```
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('todo.urls')),
]
```





위와 같은 작업을 마치고 나서,



> 서버를 시작하고
>
> http://127.0.0.1:8000/ 으로 접속하면,
>
> 아래와 같은 화면이 나옵니다.


![img](https://djangojeng-e.github.io/2020/05/23/TodoList-8%ED%8E%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-Templates-%EC%84%A4%EC%A0%95/image1.png)





## 마치며..





아직 익숙치 않아서, 여러가지로 꼬이고 꼬였지만, 레슨이 하나 있었습니다.



View 에서 ListView 클래스를 사용하는데.

예네는 templates 을, templates/todolist_list.html 파일에서 찾습니다;;;

이 파일이 없으면, 렌더가 안되서.

이 파일을 생성하고 메인 url 로 설정해 주었습니다.



# TodoList 9편 - 리스트페이지 카드형태로 꾸미기

템플릿 작업을 마친 메인 페이지 하단에 보면,


할일 목록들이 나오긴 하는데, 이쁘지가 않습니다. 이것을, BULMA CSS 를 사용하여,


카드형태로 나오게 할겁니다.





## todolist_list.html 파일 수정하기



templates/todo/todolist_list.html 파일을 아래와 같이 수정해 줍니다.



```
{% extends 'base.html' %}

{% block title %}할일 목록{% endblock %}

{% block content %}
  

<div class="columns">
{% for todo in to_do_list %}

<div class="card column">
    <header class="card-header">
      <p class="card-header-title">
        할일 : {{ todo.name }}
      </p>
      <a href="#" class="card-header-icon" aria-label="more options">
        <span class="icon">
          <i class="fas fa-angle-down" aria-hidden="true"></i>
        </span>
      </a>
    </header>
    <div class="card-content">
      <div class="content">
        해야 할일 : {{ todo.description|truncatechars:30 }}
        <br>
        <hr>
        생성 날짜 : {{ todo.date_created }}
        <br>
        데드 라인 : {{ todo.date_deadline }}
        <hr>
        남은 일수 : {{ todo.remaining_days }}
      </div>
    </div>
    <footer class="card-footer">
      <a href="#" class="card-footer-item"><button class="button is-primary is-fullwidth">더보기</button></a>
      <a href="#" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
      <a href="#" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
    </footer>
  </div>

{% endfor %}

</div>
{% endblock content %}
```



파일을 수정하고, 서버를 시작한다음,


http://127.0.0.1:8000/ 으로 접속해 보면, 아래와 같이 투두 리스트 정보가 카드 형태로 나옵니다.


![img](https://djangojeng-e.github.io/2020/05/24/TodoList-9%ED%8E%B8-%EB%A6%AC%EC%8A%A4%ED%8A%B8%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%B9%B4%EB%93%9C%ED%98%95%ED%83%9C%EB%A1%9C-%EA%BE%B8%EB%AF%B8%EA%B8%B0/image2.png)



## 마치며..



디자인이 많이 아쉽습니다..

역시 부트스트랩이 컴포넌트도 더 많은거 같고, 예제도 더 많아서 사용하기 편리한거 같습니다.



Bulma 는 예제도, 설명도, CSS 사이즈 자체도 좀 빈약한 감이 있습니다.



더보기, 수정하기, 삭제하기 버튼을 추가하게 되어서. 매우 일이 커지게 된것 같습니다만..

진행 하면서 알아가는것이 클테니.. 계속 진행 합니다.



# TodoList 10편 - 리스트 더보기 (디테일) 기능 구현


하나의 TodoList 는, 아래의 필드들을 가지고 있습니다.





| **Field**       | **비고**                             |
| :-------------- | :----------------------------------- |
| name            | 할일의 제목                          |
| description     | 할일에 대한 설명                     |
| date_created    | 생성 날짜                            |
| date_deadline   | 데드라인 날짜                        |
| TodoList_images | 외래키로, 관련된 이미지파일들을 보유 |
| TodoList_files  | 외래키로, 관련된 파일들을 보유       |



> 리스트만 보여주는 페이지에서는,
>
> 간략하게만 보여주기 때문에, 관련 이미지와 파일들을 보여주고 있지 않지만,
>
> 더보기 버튼을 눌러서, 디테일을 보여줄때에는, 관련 이미지와 파일도 보여져야 될것 같습니다.





이를 진행하기 위해서는, 아래와 같은 순서를 거쳐야 합니다.



1. templates/todo/todolist_detail.html 파일을 생성후 작성
2. todo/views.py 파일에 detailview 를 추가
3. todo/urls.py 파일에서 detailview 를 호출할 url 설정
4. templates/todo/todolist_list.html 에서 todollist_detail 로 가는 URL 링크 추가





## templates/todo/todolist_detail.html





templates/todo/todolist_detail.html 파일을 생성 후 아래와 같이 작성합니다.



```
{% extends 'base.html' %}

{% block title %}할일 정보{% endblock %}

{% block content %}

<div class="columns">

    
    <div class="card column">
        <header class="card-header">
          <p class="card-header-title">
            할일 : {{ todolist.name }}
          </p>
          <a href="#" class="card-header-icon" aria-label="more options">
            <span class="icon">
              <i class="fas fa-angle-down" aria-hidden="true"></i>
            </span>
          </a>
        </header>
        <div class="card-content">
          <div class="content">
            해야 할일 : {{ todolist.description }}
            <br>
            <hr>
            생성 날짜 : {{ todolist.date_created }}
            <br>
            데드 라인 : {{ todolist.date_deadline }}
            <hr>
            남은 일수 : {{ todolist.remaining_days }}
            <hr>
            관련 이미지 : 
            {% for image in todolist_images_set.all %}
            <img src="{{ image }}">
            {% endfor %}
            <hr>
            관련 파일 : 
            {% for file in todolist_files_set.all %}
            <a href="#">{{ file }}</a>
            {% endfor %}
          </div>
        </div>
        <footer class="card-footer">
          <a href="{% url 'todo:todolist' %}" class="card-footer-item"><button class="button is-link is-fullwidth">뒤로가기</button></a>
          <a href="#" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
          <a href="#" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
        </footer>
      </div>
    
{% endblock content %}
```





## todo/views.py 파일에 DetailView 추가



```
from django.shortcuts import render 
from .models import TodoList, TodoList_files, TodoList_images 
from django.views import generic 

# Create your views here. 


class IndexView(generic.ListView):
    context_object_name = 'to_do_list'
    
    def get_queryset(self):
        return TodoList.objects.all() 
    
class DetailView(generic.DetailView):
    model = TodoList 
    contxt_object_name = 'todolist'
    
    def get_queryset(self):
        return TodoList.objects.all()
```





## DetailView 를 호출할 URL 설정





todo/urls.py 파일을 아래와 같이 DetailView 를 호출하는 URL 을 등록해줍니다.



```
from django.contrib import admin 
from django.urls import path 
from .views import IndexView, DetailView 

app_name = 'tdodo'

urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
    path('detail/<int:pk>/', DetailView.as_view(), name="todolist_detail"),
]
```





## todolist_detail 로 가는 URL 링크를 추가



templates/todo/todolist_list.html 에서 todolist_detail 로 가는 링크를 추가 해줍니다.



```
<footer class="card-footer">
     <a href="/detail/{{ todo.id }}" class="card-footer-item"><button class="button is-primary is-fullwidth">더보기</button></a>
     <a href="#" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
     <a href="#" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
   </footer>
```





## 마치며..



투두리스트의 디테일 보기 구현이 간단하게 되었습니다.


순서대로 하면 잘 구현 되는것을 확인할수 있었습니다. 하지만, 문제가 하나 발생합니다.


투두리스트에 이미지와 파일이 있어도, 이것이 디테일 페이지에서 표시가 되지 않습니다.


이를 위해서는, MEDIA 경로를 설정하고, URL 패턴에 MEDIA_URL 을 추가해주어야 합니다.



# TodoList 11편 - 디테일 페이지에 이미지와 파일 표시

## 디테일 페이지에 이미지와 파일 표시



## MEDIA 경로 설정





개발 단계에서 MEDIA 파일을 업로드 해줄 MEDIA 경로를 설정해 주어야 합니다.


confit/settings.py 파일로 가서, 아래와 같이 MEDIA 경로를 설정해 줍니다.



```
# MEDIA_ROOT 설정 
# 사용자가 업로드하는 미디어 정적 파일들의 기본 경로 
MEDIA_ROOT = os.path.join(BASE_DIR, '.media')
MEDIA_URL = '/media/'
```





## image 와 file URL 을 위한 쿼리셋 연습



쿼리셋을 연습하기 위해서


두개의 이미지 파일과, 두개의 파일을 가지고 있는 투두리스트 하나를 어드민에서 생성해 주었습니다.


image 의 URL을 얻기위해서, Shell_plus 에서 연습한 내용은 아래와 같습니다.





```
In [1]: t = TodoList.objects.get(pk=3)   
In [2]: images = TodoList_images.objects.filter(todo=t) 

# images 에, 해당 todo에 관련된 TodoList_images images 들을 담고, 
# 이를 순회하면서, 각 image 가 어떤 속성이 있는지 프린트 해봅니다. 
 

In [3]: for image in images: 
   ...:     print(dir(image))    


# 각 image 마다, image 라는 속성이 존재 하는것을 확인 할수 있습니다 
In [4]: for image in images: 
   ...:     print(dir(image.image)) 


# image.image 에는 url 속성이 있어서, image.image.url 을 출력하면, 
# 각 이미지들의 url 을 얻을수 있습니다. 

In [6]: for image in images: 
   ...:     print(image.image.url)
/media/todo/images/2020/05/todolist_logo.jpg
/media/todo/images/2020/05/main.jpg
```





## 이미지 URL 적용하기



이를 잘 활용하여, templates/todo/todo_list.html 파일에 적용해봅니다.



```
<hr>
       남은 일수 : {{ todolist.remaining_days }}
       <hr>
       관련 이미지 : 
       {% for image in todolist.todolist_images_set.all %}
       <img src="{{ image.image.url }}" width="300px" height="350px" alt="reference_image">
       {% endfor %}
       <hr>
```





이렇게 까지 작업을 하고, 다시 투두 리스트의 디테일을 확인해보면,
이미지가 화면에 나올것이라고 생각하고 확인해 보면, 이미지가 화면에 표시되지 않는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/05/26/TodoList-11%ED%8E%B8-%EB%94%94%ED%85%8C%EC%9D%BC-%ED%8E%98%EC%9D%B4%EC%A7%80%EC%97%90-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%99%80-%ED%8C%8C%EC%9D%BC-%ED%91%9C%EC%8B%9C/image1.png)

브라우저를 열어서 요소 검사를 해보면, 아래와 같이 HTML 은 잘 작성이 되어 있는데,


이미지 표시가 안됩니다.


![img](https://djangojeng-e.github.io/2020/05/26/TodoList-11%ED%8E%B8-%EB%94%94%ED%85%8C%EC%9D%BC-%ED%8E%98%EC%9D%B4%EC%A7%80%EC%97%90-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%99%80-%ED%8C%8C%EC%9D%BC-%ED%91%9C%EC%8B%9C/image2.png)

## URL patterns 에 MEDIA_URL 추가



이 문제를 해결하기 위해서는,


config/urls.py 파일을 수정해 주어야 합니다.
기존 urlpatterns 에, MEDIA_URL 그리고, 루트를 설정해 주어야 합니다.



### config/urls.py 파일 수정





1. from django.conf import settings
2. from django.conf.urls.static import static





```
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static 

urlpatterns = [
    path('admin/', admin.stie.urls),
    path('', include('todo.urls')),
]

urlpatterns += static(
	prefix=settings.MEDIA_URL, 
    document_root=settings.MEDIA_ROOT, 
)
```





> 위와 같이 수정을 해주었다면,
>
> 브라우저에서 한번 확인 해 봅니다. 투두리스트의 디테일을 확인하면,
>
> 해당 투두리스트의 관련 이미지가 화면에 표시되는것을 확인할수 있습니다.


![img](https://djangojeng-e.github.io/2020/05/26/TodoList-11%ED%8E%B8-%EB%94%94%ED%85%8C%EC%9D%BC-%ED%8E%98%EC%9D%B4%EC%A7%80%EC%97%90-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%99%80-%ED%8C%8C%EC%9D%BC-%ED%91%9C%EC%8B%9C/image3.png)





## 파일 정보도 표시해주기



매우매우 비슷한 방식으로,


파일 정보도 디테일 페이지에 표시해줄수 있습니다.



일단, templates/todo/todolist_detail.html 파일에, 파일정보 부분을 아래와 같이 고쳐줍니다.



```
<hr>
          관련 이미지 : 
          {% for image in todolist.todolist_images_set.all %}
          <img src="{{ image.image.url }}" width="300px" height="350px" alt="reference_image">
          {% endfor %}
          <hr>
          관련 파일 : <br><br>
          {% for file in todolist.todolist_files_set.all %}
          {{ file.files.url }}
          <a href="{{ file.files.url }}">(파일보기)</a><br>

          {% endfor %}
```



파일과 이미지를 표시하는 templates/todo/todolist_detail.html 을 위와 같이 수정해 주었다면,

브라우저로 어떻게 표시되는지 확인하러 갑니다.



![img](https://djangojeng-e.github.io/2020/05/26/TodoList-11%ED%8E%B8-%EB%94%94%ED%85%8C%EC%9D%BC-%ED%8E%98%EC%9D%B4%EC%A7%80%EC%97%90-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%99%80-%ED%8C%8C%EC%9D%BC-%ED%91%9C%EC%8B%9C/image4.png)

관련 파일이라고 하는 항목에, 파일 주소가 표시되고,
파일보기 링크가 표시되는것을 확인 할수 있습니다.



# TodoList 12편 - 투두리스트 삭제 기능 구현

## Todo List - 투두리스트 삭제 기능 구현



투두 리스트 삭제 기능을 추가해 보려 합니다.



삭제 버튼은, 투두리스트 페이지와,


투두리스트 디테일 페이지에 추가해 줄겁니다.


DeleteView 를 사용하기 때문에,

삭제 버튼을 누르면, ‘정말 삭제하겠습니까?’ 라는 문구를 가진 컨펌 페이지가 뜨고,
한번 더 삭제하기 버튼을 누르면,
삭제가 완료되고.

취소 버튼을 누르면, 다시 투두리스트 리스트 페이지로 가게 됩니다.





> DeleteView 는 클래스 기반 뷰로,
>
> 기본적으로, 정말로 삭제할것인지에 대한 컨펌 페이지를 가지고 있어서
>
> 삭제 버튼을 눌렀을때, 이 컨펌 페이지를 거쳐서 삭제를 눌러야
>
> 객체가 삭제 됩니다.



구현 과정은 아래와 같습니다.



1. todo/views.py 파일에 DeleteView 클래스를 추가
2. templates/todo/delete.html 파일 생성및 작성
3. templates/todo/todolist_list.html 파일 수정 (삭제버튼 설정)
4. templates/todo/todolist_detail.html 파일 수정 (삭제버튼 설정)
5. todo/urls.py 파일에서 delete URL 설정





## todo/views.py 파일에 DeleteView 클래스 추가





```
class DeleteView(generic.DeleteView):
    model = TodoList 
    success_url = '/'
    tempalte_name = 'todo/delete.html'
```





## templates/todo/delete.html 파일 생성 및 작성





```
base.html 상속받는 대신에, {% load static %} 을 사용하여, 
static 폴더 안에 있는 style.css 파일을 직접 가져와 사용했습니다. 

DeleteView 를 사용하기 때문에, {{ form }} 을 사용하여, 삭제 폼을 만들어 주었고, 

삭제 버튼과 취소 버튼을 만들어 주었습니다.
```





```
{% load static %}

<link rel="stylesheet" type="text/css" href="{% static 'bulma/css/style.css' %}">

<div class="field is-grouped is-grouped-centered">
    <p>{{ object }}</p><br><br>
    
</div>

<div class="field is-grouped is-grouped-centered">
    
    <form action="" method="POST">
    
    {{ form }}
    {% csrf_token %}

    <p class="control">
        
      <input type="submit" class="button is-danger" value="삭제">
      <a class="button is-light" href="{% url 'todo:todolist' %}">
        취소
      </a>
    </p>
    </form>
</div>

<div class="field is-grouped is-grouped-centered">
    
    <p class="control">
        해당 투두리스트를 삭제 하시겠습니까?
      </p>
  </div>
```



## templates/todo/todolist_list.html 파일 수정



```
삭제하기 버튼을 누르면, "/delete/{{ todo.id }}" url 로 넘어가서, 
DeleteView 를 호출할것입니다. 

<footer class="card-footer">
      <a href="/detail/{{ todo.id }}" class="card-footer-item"><button class="button is-primary is-fullwidth">더보기</button></a>
      <a href="#" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
      <a href="/delete/{{ todo.id }}" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
    </footer>
```





## templates/todo/todolist_detail.html 파일 수정



```
삭제하기 버튼을 누르면, "/delete/{{ todo.id }}" url 로 넘어가서, 
DeleteView 를 호출하게 해서, 


Detail 페이지에서도 삭제가 가능하게 하려 했지만, 


DeleteView 를 Detail 페이지에서 호출하면, 에러가 납니다. (이에 대한 해결 방법을 찾지 못하고 있음) 

따라서, 삭제 버튼을 누르면, 다시 리스트 페이지로 되돌아오게 해주었습니다..
```



```
<footer class="card-footer">
          <a href="{% url 'todo:todolist' %}" class="card-footer-item"><button class="button is-link is-fullwidth">뒤로가기</button></a>
          <a href="#" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
          <a href="{% url 'todo:todolist' %}" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
        </footer>
```



## todo/urls.py 파일에서 delete URL 설정



‘delete/[int:pk](int:pk)/‘ 라는 URL 을 가지면, DeleteView 를 호출하고,

삭제 페이지로 넘어가게 됩니다.



```
from django.contrib import admin
from django.urls import path
from .views import IndexView, DetailView, DeleteView

app_name = 'todo'

urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
    path('detail/<int:pk>/', DetailView.as_view(), name="todolist_detail"),
    path('delete/<int:pk>/', DeleteView.as_view(), name="todolist_delete"),
]
```





## 마치며..


서버를 시작하고, 삭제 테스트를 해봅니다.



투두 리스트 페이지에서 삭제 진행


![img](https://djangojeng-e.github.io/2020/05/27/TodoList-12%ED%8E%B8-%ED%88%AC%EB%91%90%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%82%AD%EC%A0%9C-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84/image1.png)

삭제 버튼을 누르면, 아래 컨펌 페이지가 뜨고, 여기서 한번 더 삭제를 누르면 객체가 삭제 완료 됩니다.


![img](https://djangojeng-e.github.io/2020/05/27/TodoList-12%ED%8E%B8-%ED%88%AC%EB%91%90%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%82%AD%EC%A0%9C-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84/image2.png)

삭제 버튼을 누르면, 투두리스트 삭제가 진행 되는것을 확인 하였습니다만.

디테일 페이지에서 삭제 동작을 구현하지 못한것이 아쉽습니다.
클래스 베이스 뷰에 있는 DeleteView 를 더 많이 경험해 봐야 알것 같습니다.





**만약, DeleteView 를 사용하지 않고, 함수형으로 이 기능을 구현하고 싶다면,**

------


views.py 에 아래와 같은 함수를 작성하고, 적절히 URL 을 연결하여 사용하면 됩니다.



```
# views.py 
def delete_todo(request, pk):
    t = TodoList.objects.get(pk=pk)
    t.delete() 
    return redirect('/')
```



```
# todo/urls.py 
from django.contrib import admin
from django.urls import path
from .views import IndexView, DetailView, DeleteView, UpdateView, TodoCreate
from .views import delete_todo
app_name = 'todo'

urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
    path('detail/<int:pk>/', DetailView.as_view(), name="todolist_detail"),
    path('delete/<int:pk>/', DeleteView.as_view(), name="todolist_delete"),
    path('delete_todo/<int:pk>/', delete_todo, name="delete_todo"),
    path('update/<int:pk>/', UpdateView.as_view(), name='todolist_update'),
    path('create/', TodoCreate, name='todolist_create'),
]
```



다만, 이렇게 구현 하면, DeleteView 를 사용했을때랑은 다르게,

Confirm page 가 나오지 않고. 바로 삭제가 됩니다.



# TodoList 13편 - 업데이트 기능 구현

## Todolist - 업데이트 기능 구현





투두 리스트를 수정할수 있는 업데이트 기능을 구현해 봅니다.


클래스 기반 뷰인 UpdateView 를 사용하여, 기능을 구현해 볼것입니다.





이를 구현하기 위해선, 대략적으로, 아래의 과정을 거쳐야 합니다.



1. views.py 에 generic 뷰를 상속받는 UpdateView 클래스 작성
2. templates/todo/update.html 파일 생성 및 작성
3. templates/todo/todolist_list.html 파일 수정
4. templates/todo/todolist_detail.html 파일 수정
5. todo/urls.py 파일 수정





## UpdateView 클래스 작성



todo/views.py 파일에 아래와 같이, UpdateView 를 추가해 줍니다.



```
class UpdateView(generic.UpdateView):
    model = TodoList 
    fields = ['name', 'description', 'date_deadline']
    template_name = 'todo/update.html'
    success_url = "/"
```





## update.html 파일 생성 및 작성



templates/todo/update.html 파일을 아래와 같이 생성해주고 작성해 줍니다.



```
{% load static %}
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    
    <title>To do List - 
      {% block title %}할일 정보{% endblock %}</title>
    <link rel="stylesheet" type="text/css" href="{% static 'bulma/css/style.css' %}"> 




</head>
<body>

  <nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a class="navbar-item" href="#">
        <img src="{% static 'images/todo_list.jpg' %}" width="112" height="28">
      </a>
  
      <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>
  
    <div id="navbarBasicExample" class="navbar-menu">
      <div class="navbar-end">
        
  
      <div class="navbar-end">
        <a class="navbar-item">
          Home
        </a>
  
        <a class="navbar-item">
          To Do Lists
        </a>
  
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">
            More
          </a>
  
          <div class="navbar-dropdown">
            <a class="navbar-item">
              About
            </a>
            <a class="navbar-item">
              Jobs
            </a>
            <a class="navbar-item">
              Contact
            </a>
            <hr class="navbar-divider">
            <a class="navbar-item">
              Report an issue
            </a>
          </div>
        </div>
      </div>
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-primary">
              <strong>Sign up</strong>
            </a>
            <a class="button is-light">
              Log in
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>



<form method="post">
    {% csrf_token %}
    {{ form.as_p }}
    <input type="submit" value="Update">
</form>
    </body>
</html>
```



UpdateView를 통해서, form 을 html 에 표시할수 있습니다.



## todolist_list.html 파일 수정



templates/todo/todolist_list.html 파일을 수정해 줍니다.



```
<footer class="card-footer">
      <a href="/detail/{{ todo.id }}" class="card-footer-item"><button class="button is-primary is-fullwidth">더보기</button></a>
      <a href="/update/{{ todo.id }}" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
      <a href="/delete/{{ todo.id }}" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
    </footer>
```





### todolist_detail.html 파일 수정



```
# Detail 페이지에서도 update 가 가능하게끔, 
# update/{{ todolist.pk }} 링크를 추가해 줍니다. 
<footer class="card-footer">
          <a href="{% url 'todo:todolist' %}" class="card-footer-item"><button class="button is-link is-fullwidth">뒤로가기</button></a>
          <a href="/update/{{ todolist.pk }}" class="card-footer-item"><button class="button is-warning is-fullwidth">수정하기</button></a>
          <a href="{% url 'todo:todolist' %}" class="card-footer-item"><button class="button is-danger is-fullwidth">삭제하기</button></a>
        </footer>
```





## todo/urls.py 파일 수정



`update/<int:pk>/` 로 접속하면, UpdateView 를 호출하게 되는 URL 세팅을 해줍니다



```
from django.contrib import admin
from django.urls import path
from .views import IndexView, DetailView, DeleteView, UpdateView

app_name = 'todo'

urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
    path('detail/<int:pk>/', DetailView.as_view(), name="todolist_detail"),
    path('delete/<int:pk>/', DeleteView.as_view(), name="todolist_delete"),
    path('update/<int:pk>/', UpdateView.as_view(), name='todolist_update'),
]
```





## 마치며..



좀 아쉬운 부분들이 많이 존재하긴 하지만, 일단 잘 작동하는지 테스트를 진행 해보았습니다.


![img](https://djangojeng-e.github.io/2020/05/29/TodoList-13%ED%8E%B8-%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84/image1.png)



**수정하기 버튼을 누르면, todo/update.html 파일을 읽어오고,**

**그안에는 해당 폼이 있습니다. 내용을 추가하고, update 버튼을 누르면, 해당 투두 리스트의 정보가 변경 됩니다.**


![img](https://djangojeng-e.github.io/2020/05/29/TodoList-13%ED%8E%B8-%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84/image2.png)

마찬가지로, Detail 페이지에서 수정 버튼을 누르면, 위의 폼이 뜨고,
update 버튼을 누르면 투두리스트가 변경됩니다.





아직까지 구현이 너무너무 딸립니다만… 일단 기능 구현만 되게 해놓고 나중에 천천히 고치려 합니다…



# TodoList 14편 - 투두리스트 생성 구현

## TodoList Create 페이지 구현



투두리스트를 생성하는것을 구현해 보려 합니다.


클래스 기반 뷰인 CreateView 를 사용해 보려 했었는데. 잘 모르겠어서. 함수형 으로 진행 했습니다.


forms 적용 하는것에서 꼬이고, 함수기반 뷰 작성할때 꼬이고, 클래스 기반 뷰에서도 꼬이고,


Bulma CSS 적용할때 꼬이고, URL 설정할때 꼬여서. 매우 힘들게 구현한것 같습니다;;;



아래와 같이 진행 해봅니다.





## todo_list.html 파일 수정



templates/todo/todo_list.html 파일을 수정해줍니다.


생성하기 버튼을 추가 하고, 링크를 연결해줍니다.



```
{% extends 'base.html' %}

{% block title %}할일 목록{% endblock %}

{% block content %}
<div class="box has-text-centered">
  <a href="{% url 'todo:todolist_create' %}">
<button class="button is-info is-rounded is-fullwidth">할일 추가하기</button>
</a>
</div>

<div class="columns">
{% for todo in to_do_list %}
....
```



## todo/forms.py 파일 생성 후 작성





```
from django import forms 

class TodoCreateForm(forms.Form):
    name = forms.CharField(
    	max_length=20,
    	widget=forms.TextInput(
        	attrs={
                'class': 'input',
                'placeholder': '해야할일',
            }
        )
    )
    
description = forms.CharField(
	max_length=200,
	widget=forms.Textarea(
    	attrs={
            'class': 'textarea',
            'placeholder': '할일에 대한 디테일',
        }
    )
)

date_deadline = forms.DateField(
	widget=forms.DateInput(
    	attrs={
            'id': 'datepicker',
            'placeholder': 'YYYY-MM-DD',
        }
    )
)

images = forms.ImageField(
	widget=forms.ClearableFileInput(
    	attrs={
            'multiple': True,
            'class': 'file-input',
        }
    ), required=False,
)

files = forms.FileField(
	widget=forms.ClearableFileInput(
    	attrs={
            'multiple': True,
            'class': 'file-input',
        }
    ), required=False,
)
```





## todo/views.py 파일 수정



함수기반 뷰인 TodoCreate 를 추가해줌



```
from .forms import TodoCreateForm

def TodoCreate(request):
    if request.method == "POST":
        form = TodoCreateForm(request.POST)
        
        name = request.POST['name']
        description = request.POST['description']
        date_deadline = request.POST['date_deadline']
        images = request.FILES.getlist('images')
        files = request.FILES.getlist('files')
        date_created = date.today()
        
        # valid 한 date_deadline value 를 넣지 않았을때 막을 방법이 없음.
        # date_created 가 date_deadline 보다 지난 날짜라도, Todo는 생성되는 문제점도 있음 
        
        t = TodoList.objects.create(
        	name=name,
        	description=description,
        	date_created=date_created,
        	date_deadline=date_deadline,
        )
        
        t.save()
        
        for image in images:
            TodoList_images.objects.create(todo=t, image=image)
            
        for file_in_list in files:
            TodoList_files.objects.create(todo=t, files=file_in_list)
            
        return redirect('/')
    
    else:
        form = TodoCreateForm()
        return render(request, 'todo/create.html', {'form': form})
```



## create.html 파일 생성후 작성 (폼 작성)



templates/todo/create.html 파일을 생성하고, 작성해 줍니다.



BULMA 공식 페이지에서, components 에 대한 섹션을 참고해 가면서. 하나하나 구현 해 봅니다.


포인트는, forms.py 에 작성된 필드들의 클래스가 Bulma css 에 존재하는 클래스명으로 나와야 합니다.



- 그리고, image 인풋과 file 인풋은, 다른 클래스를 가지고 있기 때문에,

  field.name 이 image 혹은 file 이라면, 일반 form div 가 아닌, 클래스명을 가진 div 로 표시되게 만들었습니다. (이게 if field.name == ‘image’ or filed.name == ‘file’ 이 하는 일입니다.)

- `script`태그 안에, datepicker() 함수를 넣어서, deadline_date 을 선택할때, 날짜를 선택하는 위젯을 구현해 주었습니다



```
{% extends 'base.html' %}
{% block head %}



<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {
  $( "#datepicker" ).datepicker();
} );
</script>

{% endblock %}

{% block content %}


<article class="message is-info">

<div class="message-header">
      <p>Todo List 추가</p>
      <button class="delete" aria-label="delete"></button>
    </div>
    <div class="message-body">
      새로운 투두 리스트를 추가해 보세요! 
    </div>

</article>


<div class="columns is-centered">
    
    <form action="" method="POST" enctype="multiplart/form-data" style="margin-top: 50px;">
        {% csrf_token %}
        {% for field in form %}
        
        {% if field.name == 'images' or field.name == 'files' %}
        
        {{ field.name }}
        <div class="file has-name" style="margin-top: 10px;">
            <label class="file-label">
            {{ field }}
                <span class="file-cta">
        <span class="file-icon">
          <i class="fas fa-upload"></i>
        </span>
        <span class="file-label">
          Choose {{ field.name }} to upload
        </span>
      </span>
      <span class="file-name">
        Upload your {{ field.name }} here
      </span>
    </label>

            
        </div>
        
        {% else %}

    <div class="field" style="margin-top: 10px;">
        {{ field.name }}
        <div class="control">
        {{ field }}
    </div>
    </div>
    {% endif %}
    {% endfor %}

    <div class="field is-grouped is-grouped-centered" style="margin-top: 30px;">
        <div class="control">
          <button class="button is-link">Submit</button>
        </div>
        <div class="control">
          <button class="button is-danger is-light">Cancel</button>
        </div>
      </div>
</form> 
</div>
{% endblock %}
```



여기서 한가지 정말 아쉬운것 하나는, 날짜를 입력해야하는 필드에 date picker 를
자바스크립트를 사용하여 구현하고 싶었지만, 짧은 지식으로 인하여,


아직까지 제대로 구현하지 못했다는 점입니다.





## URL 설정



todo/urls.py 파일에서, url 을 설정해주어야 합니다.


create/ 로 접속하면, TodoCreate 함수가 호출되게 URL 설정을 해줍니다.



```
from django.contrib import admin
from django.urls import path
from .views import IndexView, DetailView, DeleteView, UpdateView, TodoCreate

app_name = 'todo'

urlpatterns = [
    path('', IndexView.as_view(), name='todolist'),
    path('detail/<int:pk>/', DetailView.as_view(), name="todolist_detail"),
    path('delete/<int:pk>/', DeleteView.as_view(), name="todolist_delete"),
    path('update/<int:pk>/', UpdateView.as_view(), name='todolist_update'),

    path('create/', TodoCreate, name='todolist_create'),
]
```





## 마치며..



많은것이 찝찝한,,, 그리고 많이 아쉬운 부분이 많습니다;;


일단, 아래 같이 추가가 제대로 동작하긴 하지만, 개선점이 많아 보입니다.



![img](https://djangojeng-e.github.io/2020/05/29/TodoList-14%ED%8E%B8-%ED%88%AC%EB%91%90%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%83%9D%EC%84%B1-%EA%B5%AC%ED%98%84/image1.png)

![img](https://djangojeng-e.github.io/2020/05/29/TodoList-14%ED%8E%B8-%ED%88%AC%EB%91%90%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%83%9D%EC%84%B1-%EA%B5%AC%ED%98%84/image2.png)


submit 버튼을 누르면, 메인페이지 이동후, 방금 생성한 투두리스트가 나타나는것을 볼수 있습니다.


![img](https://djangojeng-e.github.io/2020/05/29/TodoList-14%ED%8E%B8-%ED%88%AC%EB%91%90%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%83%9D%EC%84%B1-%EA%B5%AC%ED%98%84/image3.png)



# TodoList 15편 - allauth 패키지 설치

## allauth 패키지 설치



django 패키지중 하나인, allauth 를 사용하여,


기본 로그인, 로그아웃을 구현하고, 가능하면, 네이버 로그인 까지 구현해 볼까 합니다.



로그인, 로그아웃을 직접 구현하기 보다는,


아무래도 이런 편리한 패키지를 이용하여 소셜 로그인까지 구현이 가능해 보여서 사용해 보려 합니다.





순서는 아래와 같습니다.



1. allauth 설치
2. config/settings.py 파일 수정
   - TEMPLATES
   - AUTHENTICATION_BACKENDS
   - INSTALLED_APPS
   - SITE_ID
3. config/urls.py 파일 수정
4. python mange.py migrate
5. 어드민에 접속해서 도메인을 위한 Site 추가



## allauth 설치



```
pip install django-allauth
```



## config/settings.py 파일 수정



- AUTHENTICATION_BACKENDS
- INSTALLED_APPS
- SITE_ID



```
AUTHENTICATION_BACKENDS = (
    # allauth 와 상관없이 username 으로 장고 어드민에 로그인이 필요할때 
    'django.contrib.auth.backends.ModelBackend',

    # 'allauth' 특화 인증 방법, e-mail로 로그인 하는것 같은것 
    'allauth.account.auth_backends.AuthenticationBackend',
)

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    
    'todo',
    'django_extensions',

    # Bulma CSS Framework 
    'bulma',

    # Allauth를 위한 Apps 
    'django.contrib.sites',

    'allauth',
    'allauth.account',
    'allauth.socialaccount', 

    # ... 소셜로그인을 할 제공자 리스트를 아래에 포함 
    # 'allauth.socialaccount.providers.naver'
]

SITE_ID = 1
```





## config/urls.py 파일 수정



allauth 의 urls 들의 경로를 추가해줍니다.



```
urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('todo.urls')),
    path('accounts/', include('allauth.urls')),
]
```





## python manage.py migrate



```
(to-do-list)  python manage.py migrate
Operations to perform:
  Apply all migrations: account, admin, auth, contenttypes, sessions, sites, socialaccount, todo
Running migrations:
  Applying account.0001_initial... OK
  Applying account.0002_email_max_length... OK
  Applying sites.0001_initial... OK
  Applying sites.0002_alter_domain_unique... OK
  Applying socialaccount.0001_initial... OK
  Applying socialaccount.0002_token_max_lengths... OK
  Applying socialaccount.0003_extra_data_default_dict... OK
```



## 어드민에 접속해서 설치 확인



설치후에, 어드민에 로그인하면,


아래와 같이 이전에는 볼수 없었던 메뉴들이 생겨납니다.


![img](https://djangojeng-e.github.io/2020/05/30/TodoList-15%ED%8E%B8-allauth-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%84%A4%EC%B9%98/image1.png)





# TodoList 16편 - allauth를 사용하여 로그인, 로그아웃



## allauth 사용 로그인, 로그아웃 구현

지난 포스팅에서, allauth 패키지 설치를 완료 하였고,

패키지를 통하여, 로그인, 로그아웃 기능을 구현 해보려 합니다.



로그인 / 로그아웃을 사용하기 위해서는, allauth 에서 제공하는 로그인 / 로그아웃 URL 들을 알아야 합니다.



> allauth 에서,
>
> 로그인 / 로그아웃을 위해 기본으로 제공하는 URL 은 아래와 같습니다
>
> http://127.0.0.1:8000/accounts/login
>
> http://127.0.0.1:8000/accounts/logout



이 URL 들을 사용하여, 미니프로젝트에 적용하는 방법은 아래와 같습니다.



1. settings.py 파일에 LOGIN_REDIRECT_URL 설정
2. templates 페이지들에 회원가입 / 로그인 / 로그아웃 링크 추가



### LOGIN_REDIRECT_URL 설정



settings.py 파일에, LOGIN_REDIRECT_URL 설정을 해 놓아야지만,


회원가입 / 로그인 / 로그아웃 기능이 잘 작동 합니다.



config/settings.py 파일에 아래 한줄을 추가해 줍니다.



```
# Allauth 관련 세팅 
LOGIN_REDIRECT_URL = '/'
```





## 템플릿 파일에 회원가입, 로그인, 로그아웃 링크 추가



- templates/base.html 파일,
- templates/todo/todolist.html 파일
- templates/todo/update.html 파일



위 3개의 파일들을 수정해 줍니다.



```
<div class="navbar-item">
         <div class="buttons">
           {% if user.is_authenticated %}
           # 사용자가 로그인 되어 있으면, 로그아웃 버튼을 화면에 표시 
           <a class="button is-primary" href="{% url 'account_logout' %}">
             Log Out
           </a>
           {% else %}
           # 그렇지 않다면, 회원가입과 로그아웃 버튼 표시 
           <a class="button is-primary" href="{% url 'account_signup' %}">
             <strong>Sign up</strong>
           </a>
           <a class="button is-light" href="{% url 'account_login' %}">
             Log in
           </a>
           {% endif %}
         </div>
```





서버를 시작하고, 메인 페이지로 들어가서,


signup 을 눌러 새로운 사용자를 생성해 봅니다.


CSS 를 따로 적용해야 하겠지만, 그래도 BULMA 스타일로 출력되어 나오긴 합니다.


디테일을 입력하고, 회원가입을 진행해봅니다.



![img](https://djangojeng-e.github.io/2020/06/01/TodoList-16%ED%8E%B8-allauth%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83/image1.png)

회원가입을 완료하면, 자동으로 로그인이 되고,


화면 상단에 로그아웃 버튼이 표시되는것을 볼수 있습니다.


![img](https://djangojeng-e.github.io/2020/06/01/TodoList-16%ED%8E%B8-allauth%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83/image2.png)

로그아웃 버튼도 눌러보면, 로그아웃 컨펌 페이지가 표시가 되고,


컨펌 페이지에서, 로그아웃 버튼을 누르면, 다시 메인페이지로 이동이 되는데.





![img](https://djangojeng-e.github.io/2020/06/01/TodoList-16%ED%8E%B8-allauth%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83/image3.png)

로그인 되어 있는 상태가 아니니까, Sign Up 과 Log in 만 표시되어 나옵니다.





로그인도 한번 진행해 보면, 화면 상단에 로그인 버튼을 눌러서


아래와 같이 아이디와 비밀번호를 화면에 입력하고, 로그인을 진행 해 보면

![img](https://djangojeng-e.github.io/2020/06/01/TodoList-16%ED%8E%B8-allauth%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83/image4.png)





아이디와 비밀번호를 입력하면,


메인 페이지로 다시 들어오는데, 화면 상단에 Logout 버튼이 표시가 됩니다.



![img](https://djangojeng-e.github.io/2020/06/01/TodoList-16%ED%8E%B8-allauth%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83/image5.png)





## 마치며..





일단, 직접 구현 하는것 보다는,

훨씬 간단하게 회원가입, 로그인, 로그아웃 기능을 구현해 보았습니다.
그렇지만, 패키지의 내부 동작을 파악하는것은 아직 다 마친것이 아니라 무엇인가 찝찝하긴 합니다



회원가입 / 로그인 / 로그아웃 폼이 좀 맘에 들지 않는다면, allauth 의 템플릿을 수정해 주면 되는데.



이는, 다음 미니 프로젝트때 진행해보려 합니다.



# TodoList 17편 - allauth로 네이버로 로그인 구현

allauth 를 사용하여, 소셜 로그인중 하나인 네이버로 로그인 하기를 구현 하고 싶습니다.



이를 구현하기 위해서는, 네이버 개발자 센터를 방문하여


네이버 아이디로 로그인 API 키를 발급 받아야 합니다.



https://developers.naver.com/apps/#/register


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image1.png)







## 네이버 아이디 로그인 API 키 발급





![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image2.png)



서비스 URL : [http://127.0.0.1:8000](http://127.0.0.1:8000/)

네이버아이디로그인 Callback URL : http://127.0.0.1:8000/accounts/naver/login/callback/


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image3.png)

등록하기 버튼을 누르면, 앱이 등록 됩니다.

그리고, 클라이언트 ID 와 클라이언트 secret 생성이 됩니다.



![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image4.png)

## INSTALLED_APPS 에 네이버 추가



settings.py 안에, INSTALLED_APPS 에

소셜로그인 제공자로 네이버를 추가해줍니다.

```
INSTALLED_APPS = [
    ...
    # Allauth를 위한 Apps 
    'django.contrib.sites',

    'allauth',
    'allauth.account',
    'allauth.socialaccount', 

    # ... 소셜로그인을 할 제공자 리스트를 아래에 포함 
    'allauth.socialaccount.providers.naver'
]
```





## 어드민에서 social application 모델 추가





어드민에 접속하여, 소셜 계정 / 소셜 어플리케이션을 추가 해줍니다.


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image5.png)





이름은, 아래와 같이 네아로 라고 쓰고,

클라이언트 아이디와 비밀 키는, 네이버 개발자 센터에서 앱을 등록했을때 발급받은,

아이디와 키값을 입력합니다.





![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image6.png)

그리고, 하단에 있는, 이용 가능한 Sites 에, example.com 을 선택하여,


오른쪽으로 옮겨 줍니다


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image7.png)

여기까지 설정을 마쳤으면, 저장 버튼을 눌러줍니다.



## 마치며..



네이버 아이디로 로그인 하기 기능을 위한 기본 세팅을 마쳤습니다.


이제부터 잘 작동하는지 확인해 봅니다. 우선, 서버를 실행하고, 로그인 페이지로 갑니다.


기존 로그인 폼 위에, Naver 라고 텍스트 링크가 생겨난걸 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image8.png)

naver 텍스트를 클릭하면, 아래와 같이 네이버 로그인 페이지가 뜹니다.


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image9.png)

네이버 페이지에서 OK 버튼을 누르면, 로그인이 완료 됩니다.



![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image10.png)





다시 로그아웃 하고, 어드민으로 접속해서 소셜 계정 탭을 확인하면,

네이버 로그인을 사용해서 로그인 했던 사용자의 정보가 들어와 있는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/06/02/TodoList-17%ED%8E%B8-allauth%EB%A1%9C-%EB%84%A4%EC%9D%B4%EB%B2%84%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84/image11.png)



# TodoList 18편 - 미니프로젝트 중간 정리

## 프로젝트 중간정리



매우 간단한,



그리고 빈약한 미니프로젝트 진행 상황을 중간 정리 합니다.





**아쉬운 점**

------

**가장 아쉬운점 :**

------

**forms.py 를 사용하여 폼을 그리고, 폼에서 넘어오는 데이터 처리 등. 전반적으로 폼이 문제인것 같음..;;**

------

------



1) 자바스크립트를 몰라서 동적으로 브라우저에서 값을 바꾸거나, 좀더 다이나믹하게 만들수가 없었다.

2) 디자인 - Bulma CSS 를 사용하여, 어느정도 생으로 프론트 작업 하는것보단 편했지만, 여전히 무언가 아쉽다

3) 클래스 기반 뷰의 사용이 아직 서툴다

4) allauth 를 사용해서 로그인 로그아웃 그리고 네이버 로그인 기능까지 구현 하긴 했지만. 내부 동작을 파악하고 한것이 아니다.

5) models.py 파일을 보면, 무언가 효율적으로 쓴것 같지가 않다



**현재까지 좋았던 점**



**가장 좋았던점:**

------

**어떻게든 꾸역꾸역 하려고 하면서, 문서도 많이 보게 되고, 다른 사람들이 짜놓은 코드 예시도 많이 접할수 있었다.**

**참고해서 구현한 기능들이 동작하는것을 볼때, 묘한 성취감과 기쁨을 느낀다.**

------

****



1) Bulma CSS 를 적용하여, 프론트가 생으로 하는거보단 이쁘게 된것 같다.

2) Django 패키지 - Django-bulma 와 allauth 를 사용해 보았다.

3) 간편하게 로그인, 로그아웃, 회원가입 기능을 구현하였다.

4) 관리자 페이지에 모델을 등록하는 방법에 대해 조금 더 익숙해 졌다.

5) 여기저기 찾아서, javascript 를 구현해 보았다.



------



마치며..

아주 빈약한 프로젝트지만, 다음 포스팅부터, AWS에 배포를 진행해보려 합니다.

배포를 진행하기 전에, 중간 정리를 한번 해보았네요.



# TodoList 19편 - AWS EC2 인스턴스 생성하기

## AWS EC2 인스턴스 생성



**AWS (AMAZON WEB SERVICES)**



투두리스트가 완전되었다고 가정하고, AWS 서버에 올려서 배포하는 작업을 시작해 보려 합니다.


EC2 인스턴스를 하나 생성해서, 사용해 보려 합니다.





## EC2 인스턴스 생성하기



aws.com 콘솔에 접속해서,


EC2 탭으로 이동합니다. 거기서, 인스턴스 시작을 눌러줍니다.


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image1.png)

왼쪽 빠른 시작 탭에, 프리티어 옵션에 체크를 해줍니다. (프리 티어만 사용)


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image2.png)

Ubuntu Server 18.04 LTS(HVM), SSD Volume Type 을 선택하고,


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image3.png)



단계 2에서, 인스턴스 유형을 선택하고,


다음 인스턴스 세부 정보 구성으로 넘어갑니다


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image4.png)

1. 인스턴스 구성
2. 스토리지 추가
3. 태그 추가



이 메뉴들은 그닥 중요하지 않으니, 넘어갑니다.



**하지만, 6. 보안그룹 구성에서 보안그룹을 아래와 같이 설정해 줘봅시다.**


규칙 추가를 클릭하여, 아래와 같이 만들어 줄수 있습니다.


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image5.png)

단계 7로 넘어가고, 인스턴스 시작 검토를 합니다. 검토를 다 마쳤으면,


시작버튼을 눌러서, EC2 생성을 마칩니다.



![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image6.png)

시작하기 버튼을 누르면, 기존 키페어 선택 또는 새 키 페어 생성 탭이 나오는데,

새 키 페이 생성을 누르고, 키페어 이름을 TodoList 로 정해 줍니다.


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image7.png)



> **주의사항**
>
> 키 페어 다운로드를 눌러서, 키페어를 다운로드 해주는것을 절대로 잊으면 안됩니다.
>
> 그리고, 이 다운로드 된 파일의 경로를 정확히 잘 알고 있어야 합니다!





인스턴스 시작을 눌러서, 인스턴스 생성을 마칩니다.

아래와 같이 인스턴스 생성이 완료 되었습니다.



![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image8.png)





## 생성된 인스턴스에 접속



생성한 인스턴스로 접속하려면, 터미널에서 ssh 명령어를 실행하여 접속할수 있습니다.



### pem 파일 권한 바꿔주기



인스턴스에 접속을 시도하기 전에, 위 과정에서 다운로드 받았던, pem 파일.


즉, 키페어 파일의 권한을 수정해 주어야 합니다.



```
# pem 파일이 있는 폴더로 가서, 아래와 같이 chmod 명령어를 사용하여 
# 파일의 권한 수정 

chmod 400 newtodolist.pem
```





```
ssh -i <newtodolist.pem 이 다운로드 되있는 경로> ubuntu@<해당인스턴스public IP>

# 에시는 아래와 같음. pem 파일의 경로를 잘 설정해주는것이 중요! 
ssh ~i ~/home/pemkeys/newkeys/newtodolist.pem ubuntu@12.111.87.111
```



yes/no 메시지가 나오면, yes 라고 타이핑을 해주고 엔터를 눌러줍니다

모든것이 정상적으로 이루어 졋으면, 아래와 같이 EC2 우분투에 접속 완료가 됩니다.


![img](https://djangojeng-e.github.io/2020/06/04/TodoList-19%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image9.png)



# TodoList 20편 - AWS EC2 인스턴스에 접속, 파일 전송

## AWS EC2 접속, 파일전송



지난 포스팅에서, AWS EC2 인스턴스를 생성 하였으니,


이제 해당 인스턴스에 자유롭게 접속도 하고, 작업한 프로젝트도 업로드 해보려 합니다.



해당 인스턴스에 접속하는 명령어는 아래와 같습니다.



```
ssh -i <newtodolist.pem 이 다운로드 되있는 경로> ubuntu@<해당인스턴스public IP>

# 에시는 아래와 같음. pem 파일의 경로를 잘 설정해주는것이 중요! 
ssh ~i ~/home/pemkeys/newkeys/newtodolist.pem ubuntu@12.111.87.111
```





해당 인스턴스에 프로젝트 폴더를 전송하여, 배포 작업을 시작할것 입니다.





## EC2 에 파일전송 하기



터미널에서 scp 명령어를 사용하여,
생성한 인스턴스에 TodoList 프로젝트 폴더를 업로드 해봅니다.



```
scp -i ~/downloads/keys/newtodolist.pem -r ~/projects/todolist ubuntu@15.164.97.104:/home/ubuntu/
```





> [ubuntu@15.164.87.452](mailto:ubuntu@15.164.87.452), 뒤에
>
> `:`을 꼭 붙여주어야 합니다.



ssh 명령어를 실행하여, EC2 로 접속해 보면,


파일이 업로드 되어 있는것을 확인 할수 있습니다.



![img](https://djangojeng-e.github.io/2020/06/05/TodoList-20%ED%8E%B8-AWS-EC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4%EC%97%90-%EC%A0%91%EC%86%8D-%ED%8C%8C%EC%9D%BC-%EC%A0%84%EC%86%A1/image1.png)



# TodoList 21편 - AWS EC2에 배포 시작

## AWS EC2에 배포 시작



지난 포스팅에서,


EC2 인스턴스에 연결도 진행하였고, 프로젝트 폴더도 해당 인스턴스에 업로드도 가능했습니다.



이제 기본 배포 진행을 해보려 합니다. 우선 EC2 인스턴스에 접속해서, 기본 우분투 세팅을 해줍니다.



```
ubuntu@ip-172-31-32-46:~$ sudo apt update 
ubuntu@ip-172-31-32-46:~$ sudo apt install python3-pip vim
```





## 관련 패키지들 설치



```
sudo apt install python3-pip vim
```



python3, pip, vim 을 설치해줍니다.



## nginx 설치 및 세팅



```
sudo apt install nginx
```



> nginx 란?
>
> 2002년부터 러시아의 프로그래머 이고르 시쇼브(Игорь Сысоев)가 Apache HTTPd를 코딩하다 Apache의 C10K 문제[1]를 보고, 이를 극복하기 위해서 네이티브 Win32 환경에도 돌아갈 무설치 웹 서버 데몬 프로그램에 대한 개발을 시작하여 2004년 스푸트니크 1호 발사일에 발표한 **오픈 소스 웹 서버 프로그램**. 현재 이고르 시쇼브와 그가 설립한 회사인 NGINX, Inc.가 이 프로젝트를 운영 중에 있다.[2] 목표는 가벼우면서도 강력한 프로그램이라고 한다. HTTP와 리버스 프록시, IMAP/POP3 등의 서버 구동이 가능하다. Java 서블릿은 대개 Apache의 톰캣을 연동해서 구동하고, PHP의 경우 PHP-FPM(FastCGI Process Manager)을 연동해서 구동한다.



nginx 설치가 완료되었으면, 환경 설정을 위해서, sudo su 를 사용. 루트로 들어가준다



```
cd /etc/nginx/sites-available/ 폴더에 접속해본다 

접속한 후에, ls 를 실행하여, 폴더안에 무엇이 있는지 확인 해보면, 
default 라는 항목만 존재하는것을 확인 할수 있다. 

ubuntu@ip-172-31-32-46:~$ sudo su
root@ip-172-31-32-46:/home/ubuntu# cd /etc/nginx/sites-available/
root@ip-172-31-32-46:/etc/nginx/sites-available# ls
default
root@ip-172-31-32-46:/etc/nginx/sites-available# mv default default.bak
root@ip-172-31-32-46:/etc/nginx/sites-available# ls
default.bak
root@ip-172-31-32-46:/etc/nginx/sites-available# vim default
```





default 파일을 vim 을 통해서, 아래와 같은 내용을 추가후 저장 종료



```
upstream django{
    server 127.0.0.1:8000;
}

server {
  listen 80;
  proxy_set_header X-Forwarded-Photo $scheme;

  # gunicorn app
  location / {
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Url-Scheme $scheme;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header Host $http_host;

    proxy_redirect off;

    proxy_pass http://django;

}

location /static/ {
  autoindex on;
}


location /media/ {
  autoindex on;
}
}
```



service nginx restart 명령어 실행



```
root@ip-172-31-32-46:/etc/nginx/sites-available# service nginx restart
```





EC2 에서 일반 우분투 터미널로 복귀

```
root@ip-172-31-32-46:/etc/nginx/sites-available# exit
exit
ubuntu@ip-172-31-32-46:~$ ls
ubuntu@ip-172-31-32-46:~$
```





## 업로드된 프로젝트로 이동



지난 포스팅에서 프로젝트 폴더를 EC2 에 업로드 해주었었는데.


해당 폴더로 EC2에서 들어가 줍니다.



```
ubuntu@ip-172-31-32-46:~$ ls
todolist
ubuntu@ip-172-31-32-46:~$ cd todolist
ubuntu@ip-172-31-32-46:~/todolist$ ls
config      deploy.sh  requirements.txt  static     todo
db.sqlite3  manage.py  secrets.json      templates
```



pip3 install -r requirements.txt 명령어를 실행하여, 패키지를 설치해줍니다.



```
ubuntu@ip-172-31-32-46:~/todolist$ pip3 install -r requirements.txt 

# 실행후, makemigrations, migrate 를 해줍니다. 
ubuntu@ip-172-31-32-46:~$ python3 manage.py makemigrations 
ubuntu@ip-172-31-32-46:~$ python3 manage.py migrate
```



## settings.py 파일 수정



settings.py 파일안에, ALLOWED_HOSTS 에, EC2 주소를 추가해 줍니다.



```
ALLOWED_HOSTS = [
    'localhost',
    '127.0.0.1',
    '15.164.97.104',]  # EC2 주소
```





## EC2에서 서버 실행 작동 테스트



```
ubuntu@ip-172-31-32-46:~$ python3 manage.py runserver
System check identified no issues (0 silenced).
May 20, 2020 - 02:11:11
Django version 3.0.6, using settings 'config.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```



서버를 실행 시켜 놓은 상태로, 브라우저에 접속해서, EC2 IP 주소를 입력해 보면

투두리스트가 뜨긴 뜨는데.. CSS와 이미지 적용이 전혀 되지 않은 내용이 브라우저에 표시가 되는걸 확인할수 있었습니다.



![img](https://djangojeng-e.github.io/2020/06/08/TodoList-21%ED%8E%B8-AWS-EC2%EC%97%90-%EB%B0%B0%ED%8F%AC-%EC%8B%9C%EC%9E%91/image1.png)

일단 잘 작동하는것은 확인 하였으니, 서버를 종료 시켜 줍니다.

## 마치며..



Django 에서 가장 큰 어려움은, 배포인것 같습니다.

특히, 위의 nginx 는 사용하지 않아도 서버를 실행시키면, 배포가 완료 되긴 하는데;;; 왜 사용하는지도 잘 모르겠고.

nginx 에 대한 설명과 자료들도 굉장히 빈약합니다. 무엇보다 초심자를 위한 설명은 없는듯 합니다;;;



# TodoList 22편 - AWS RDS 생성하기

## AWS RDS 생성하기



지난 포스팅에서는, 진행한건 아래와 같습니다.



1. AWS EC2 생성
2. EC2 인스턴스에 프로젝트 폴더 업로드
3. EC2 인스턴스 안에서 서버 실행
4. 브라우저로 EC2 접속
5. 프로젝트 동작 확인



하지만, 문제가 하나 있었습니다.



> static 파일을 읽어오지 못해서, 이미지와 CSS 적용이 되질 않았었습니다.
>
> 일단 이 부분에 대해서는, 나중에 찾아가면서 해결해 보도록 하고,
>
> 데이터 베이스를 기본 데이터베이스인 Sqlite3 대신에, 다른 DB 를 적용 해 보려 합니다.





## AWS RDS 사이트 접속



[https://ap-northeast-2.console.aws.amazon.com/rds/](https://ap-northeast-2.console.aws.amazon.com/rds/home?region=ap-northeast-2#databases:)



AWS에 접속하여, RDS 인스턴스 생성 과정으로 넘어갑니다.

데이터베이스 인스턴스 창으로 가서, 우측 상단에 데이터베이스 생성 버튼을 눌러줍니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image1.png)
![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image2.png)

PostgreSQL 로 설정해 주고, 프리티어를 선택합니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image3.png)

DB 계정 정보, 인스턴스 식별자, 사용자 이름, 암호를 설정해 줍니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image4.png)

쭈욱 내리면서, 바꿀 내용이 있는지 확인해 봅니다. 딱히 바꿀것은 없습니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image5.png)
![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image6.png)

손댈곳은 없는것 같고, 데이터베이스 생성 버튼을 눌러서, 생성 진행을 해줍니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image7.png)

생성 버튼을 누르면, 아래와 같이 생성중 이라고 표시가 됩니다.



생성이 완료 된 후에 사용 시작 하면 됩니다.


![img](https://djangojeng-e.github.io/2020/06/09/TodoList-22%ED%8E%B8-AWS-RDS-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0/image8.png)





# TodoList 23편 - AWS RDS 사용하기

## AWS RDS 사용하기



지난 포스팅에서, 데이터베이스 인스턴스 생성하기 버튼을 눌러 RDS 인스턴스 생성을 하였습니다.



이제, 사용 가능한 RDS 인스턴스가 있습니다.


![img](https://djangojeng-e.github.io/2020/06/10/TodoList-23%ED%8E%B8-AWS-RDS-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/image1.png)

이 인스턴스를 클릭하면, 엔드포인트가 생성 되어 있는것을 확인 할수 있는데.


이 앤드포인트를 Django에서 데이터베이스 설정할때 사용할것 입니다.



## settings.py 파일에 DATABASES



settings.py 파일의 DATABASES 설정으로 가서, 아래와 같이 DATABASES 설정을 해줍니다.



```
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': 'todolist',
        'USER': 'djangojenge',
        'PASSWORD': 'django12!',
        'HOST': 'todolist-django.r2tcewdefdf1.ap-northeast-2.rds.amazonaws.com',
        'PORT': 5432,
    }
}
```



> 위 DATABASES 설정에서,
>
> ‘NAME’ 과 ‘PASSWORD’는 아직 정해지지 않았습니다.
>
> NAME 과 PASSWORD 는 직접 설정해 주어야 합니다.





## RDS 보안그룹 생성하기



EC2 Management Console 로 가서,


네트워크 및 보안탭에 가장 첫번째 메뉴인, 보안그룹을 클릭하여


아래와 같이 보안그룹을 하나 생성 해줍니다.



![img](https://djangojeng-e.github.io/2020/06/10/TodoList-23%ED%8E%B8-AWS-RDS-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/image2.png)

이 보안 그룹을, RDS 인스턴스에 적용해 줍니다.


RDS 인스턴스 수정 버튼을 눌러서, 네트워크 및 보안 섹션에, 아래와 같이 EC2 에서 생성한 보안그룹을 정하고,


퍼블릭 엑세스 가능성을 ‘예’ 로 표시합니다.



![img](https://djangojeng-e.github.io/2020/06/10/TodoList-23%ED%8E%B8-AWS-RDS-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/image3.png)
![img](https://djangojeng-e.github.io/2020/06/10/TodoList-23%ED%8E%B8-AWS-RDS-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/image4.png)

보안그룹이 활성이라고 하는 상태가 보이면 적용이 된것입니다.





## postgres 계정 설정





위의 설정을 다 마쳤으면, 터미널에서, psql 명령어를 사용하여, postgres 계정 설정을 진행합니다.



```
psql --host=todolist-django.ctskq7umc1ur.ap-northeast-2.rds.amazonaws.com --username=todo-list123456 --dbname=postgres
Password for user todo-list123456:
```



여기서 사용되는 username 과 password 는 RDS를 처음 생성할때 입력했던,


username 과 password 입니다. 알맞는 username 과 password 를 입력하면,


아래와 같이 postgres 에 접속이 됩니다.





```
psql (10.12 (Ubuntu 10.12-0ubuntu0.18.04.1), server 11.6)
WARNING: psql major version 10, server major version 11.
         Some psql features might not work.
SSL connection (protocol: TLSv1.2, cipher: ECDHE-RSA-AES256-GCM-SHA384, bits: 256, compression: off)
Type "help" for help.


# 아래 명령어를 실행하여, 데이터베이스를 생성 해줍니다. 

postgres=> CREATE DATABASE todolist OWNER=todolist1234 TEMPLATE template0 LC_COLLATE 'C';

```





## settings.py 파일에 DATABASES 값 수정



settings.py 파일에 DATABASES 값을 고쳐 줍니다



```
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': 'todolist',
        'USER': 'todolist1234',
        'PASSWORD': '<데이터베이스 생성할때 사용한 패스워드>',
        'HOST': '<데이터베이스 앤드포인트 주소>',
        'PORT': 5432,
    }
}
```





## pyscopg2 설치하기



settings.py 파일에 DATABASES 설정을 마쳤으니, 서버를 시작해보면

psycopg2 를 설치해야 한다는 메시지가 나옵니다. 설치를 진행 해 줍시다.



```
pip install psycopg2-binary
```



설치뒤에, 서버를 실행해 보면, 아래와 같은 화면이 나옵니다.



```
System check identified no issues (0 silenced).

You have 29 unapplied migration(s). Your project may not work properly until you apply the migrations for app(s): account, admin, auth, contenttypes, sessions, sites, socialaccount, todo.
Run 'python manage.py migrate' to apply them.

May 20, 2020 - 14:09:47
Django version 3.0.6, using settings 'config.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```





python manage.py migrate 를 실행하여, migrate 를 해줍니다.



```
python manage.py migrate
```



migrate 를 해준뒤에, 서버를 실행 시키고, 브라우저로 접속해 보면,


이제까지 개발한 main 페이지가 나옵니다


![img](https://djangojeng-e.github.io/2020/06/10/TodoList-23%ED%8E%B8-AWS-RDS-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/image5.png)



# TodoList 24편 - AWS S3 적용하기

## Amazon S3 적용하기



> Amazon S3 란?
>
> Amazon S3 는 인터넷상 어디서나 원하는 양의 데이터를 저장하고 검색할수 있도록 구축된,
>
> 객체 스토리지 입니다. 안정성이 매우 뛰어나고, 가용성이 높으며,
>
> 무제한으로 확장 가능한 데이터 스토리지 인프라를 매우 저렴한 비용으로 제공하는,
>
> 간단한 스토리지 서비스 입니다.





## Amazon S3로 무엇을 할수 있나요?



> 스토리지를 이용하여, 간편한 웹 서비스 인터페이스를 사용할수 있습니다.
>
> 인터넷 스토리지를 이용한 어플리케이션을 쉽게 개발할수 있습니다.
>
> 원하는 형식의 데이터를 원하는 만큼 저장하고, 동일한 데이터를 수백만번 읽거나 비상 재해 복구 용도로만 사용하고, 간단한 FTP 어플리케이션 또는, Amazon.com 소매 웹사이트와 같은 복잡한 웹 어플리케이션을 구축할수 있습니다.
>
> Amazon S3 를 사용하는 개발자는 데이터 저장 방법에 대한 고민을 하기 보다,
>
> 좀더 혁신적인 것들에 집중할수 있습니다.





## AWS S3 생성하기



우선, S3 를 생성하기 위해서, AWS S3 Management 콘솔에 접속합니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image1.png)

버킷 만들기를 클릭합니다


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image2.png)

버킷 이름을 정하고, 버킷 만들기 버튼을 눌러서 버킷을 생성해 줍니다.





## 필요한 패키지 설치





터미널에서, 아래 명령어를 실행해서, `django-storages` 와 `boto3` 를 설치해 줍니다.



```
pip install django-storages
pip install boto3
```



그리고 나서, settings.py 에 INSTALLED_APPS 에 storage 를 등록해 줍니다.



```
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    
    'todo',
    'django_extensions',

    # storages 설정 
    'storages',

    # Bulma CSS Framework 
    'bulma',

    # Allauth를 위한 Apps 
    'django.contrib.sites',

    'allauth',
    'allauth.account',
    'allauth.socialaccount', 

    # ... 소셜로그인을 할 제공자 리스트를 아래에 포함 
    'allauth.socialaccount.providers.naver'
]
```





## settings.py 에 S3 기본설정



생성된 버킷을 사용하기 위한 패키지들을 모두 설치했으면, S3를 Django 에서 사용하기 위해서,


settings.py 파일에, 아래 내용을 추가해 줍니다.



```
# django-storages 
# Django의 FilesStorage로 S3Boto3Storage (AWS S3) 
DEFAULT_FILE_STORAGE = 'storages.backends.s3boto3.S3Boto3Storage'
AWS_ACCESS_KEY_ID = ''
AWS_SECRET_ACCESS_KEY = ''
AWS_STORAGE_BUCKET_NAME = 'todolist-djangojenge'
AWS_S3_CUSTOM_DOMAIN = '%s.s3.amazonaws.com' % AWS_STORAGE_BUCKET_NAME
AWS_AUTO_CREATE_BUCKET = True
AWS_S3_REGION_NAME = 'ap-northeast-2'
AWS_DEFAULT_ACL = None
```



여기서 중요한 값들은, 아래 두가지 AWS 관련 값들입니다.



1. AWS_ACCESS_KEY_ID
2. AWS_SECRET_ACCESS_KEY



이 값들을 받아오기 위한 과정을 아래에 설명합니다.



## AWS IAM 에서 ACCESS_KEY 값들 받아오기



AWS_ACCESS_KEY_ID 와 AWS_SECRET_ACCESS_KEY 를 가져오기 위해서는,


AWS IAM 서비스에 접속을 해줍니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image3.png)



> IAM (Idnetity and Access Management (IAM)) 에서,
>
> 사용자 추가를 해줍니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image4.png)

사용자 추가 버튼을 누르면, 아래와 같이. 세부정보 설정 페이지가 나오고,


사용자 이름을 쓴 다음, 엑세스 유형을 프로그래밍 방식 엑세스로 선택 합니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image5.png)


****

**다음 버튼을 누르면, 권한 설정 페이지로 넘어갑니다.**


아래와 같이, **기존 정책 직접연결 탭을 누르고** , 정책 필터에 AmazonS3FullAccess 를 검색하여,

정책이름을 클릭합니다. AmazonS3FullAccess 에 표시를 하고, 다음으로 넘어갑니다.



![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image6.png)

다음은 태그를 설정하는 페이지인데, 그다지 중요하지 않습니다.


그냥 다음으로 넘어갑니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image7.png)

검토 사항을 체크하고, 사용자 만들기를 클릭합니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image8.png)

사용자 만들기를 클릭하면, 아래 화면 엑세스키 ID 와 비밀엑세스 키 밑에,

각각의 값들이 표시 되어서 나옵니다.



> ACCESS_KEY_ID 와 SECRET_ACCESS_KEY 를 여기에서 잘 복사해 둡니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image9.png)



> 이제 이 값들을,
>
> settings.py 파일에 AWS_ACCESS_KEY_ID 값과 AWS_SECRET_ACCESS_KEY 값에 넣어줍니다.



```
# django-storages 
# Django의 FilesStorage로 S3Boto3Storage (AWS S3) 
DEFAULT_FILE_STORAGE = 'storages.backends.s3boto3.S3Boto3Storage'
AWS_ACCESS_KEY_ID = ''         # ACCESS_KEY_ID 입력 
AWS_SECRET_ACCESS_KEY = ''     # SECRET_ACCESS_KEY 입력 
AWS_STORAGE_BUCKET_NAME = 'todolist-djangojenge'
AWS_S3_CUSTOM_DOMAIN = '%s.s3.amazonaws.com' % AWS_STORAGE_BUCKET_NAME
AWS_AUTO_CREATE_BUCKET = True
AWS_S3_REGION_NAME = 'ap-northeast-2'
AWS_DEFAULT_ACL = None
```



## 정적파일 경로 설정





settings.py 파일에 STATIC 관련 내용을 아래와 같이 바꿔줍니다.



```
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/3.0/howto/static-files/
PROJECT_DIR = os.path.dirname(os.path.abspath(__file__))
STATIC_ROOT = os.path.join(PROJECT_DIR, 'static')
AWS_LOCATION = 'static'
STATIC_URL = 'https://%s/%s/' % (AWS_S3_CUSTOM_DOMAIN, AWS_LOCATION)
STATICFILES_STORAGE = 'storages.backends.s3boto3.S3Boto3Storage'
STATICFILES_DIRS = [os.path.join(BASE_DIR, 'static')]
```



위와 같이 STATIC 관련 내용을 설정해주었으면, 아래 명령어를 실행하여, staticfiles 들을 모아줍니다.



```
python manage.py collectstatic
```



```
$ python manage.py collectstatic 
You have requested to collect static files at the destination
location as specified in your settings.

This will overwrite existing files!
Are you sure you want to do this?

Type 'yes' to continue, or 'no' to cancel: yes
Found another file with the destination path 'bulma/sass/package.json'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.
Found another file with the destination path 'bulma/sass/package-lock.json'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.
Found another file with the destination path 'bulma/sass/style.sass'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.
Found another file with the destination path 'bulma/css/style.css.map'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.
Found another file with the destination path 'bulma/css/style.min.css'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.
Found another file with the destination path 'bulma/css/style.css'. It will be ignored since only the first encountered file is collected. If this is not what you want, make sure every static file has a unique path.

144 static files copied.
```





## 마치며..



S3에 로그인해서 들어가보면, static 하고 todo 폴더가 생성되어 있는것을 확인 할수 있었습니다.


정적파일과, 미디어 파일을 관리하는 폴더입니다.



![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image10.png)

그런데, 문제가 하나 생깁니다.. 정적파일을 모으고 서버를 실행시켜도.


정적파일들이 적용되지 않는것을 확인할수 있습니다.


![img](https://djangojeng-e.github.io/2020/06/12/TodoList-24%ED%8E%B8-AWS-S3-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0/image11.png)



# TodoList 25편 - AWS S3 정적 이미지와 CSS 파일 적용



## AWS S3 정적 이미지와 CSS 파일 적용



이전 포스팅에서,

S3 를 사용해 보았는데, 그전까지 잘 보이던 CSS 와 이미지 파일들이 보이지 않았습니다.



이미지와 CSS가 적용되지 않은 사이트를 보게되니, 살짝 당황 했었습니다.





아래 두가지 사유로 인해서, 적용이 되지 않는다는것을 알아냈습니다.



1. 경로
2. 권한 (엑세스 설정)





## 경로 (settings.py 파일에 S3관련 세팅 재확인)



정적파일이 있는 URL 을 잘 찾을수 있도록, 경로를 다시 한번 확인해 줍니다.



```
# django-storages
# django FIleStorage로 S3Boto3Storage (AWS S3) 사용 
SECRETS = json.load(open(os.path.join(BASE_DIR, 'secrets.json'), 'rt'))
DEFAULT_FILE_STORAGE = 'storages.backends.s3boto3.S3BotoStorage'
AWS_ACCESS_KEY_ID = SECRETS['AWS_ACCESS_KEY_ID']
AWS_SECRET_ACCESS_KEY = SECRETS['AWS_SECRET_ACCESS_KEY']
AWS_STORAGE_BUCKET_NAME = 'todolist-djangojenge'
AWS_S3_REGION_NAME = 'ap-northeast-2'
AWS_S3_CUSTOM_DOMAIN = '%s.s3.%s.amazonaws.com' % (AWS_STORAGE_BUCKET_NAME, AWS_S3_REGION_NAME)
AWS_AUTO_CREATE_BUCKET = True
AWS_DEFAULT_ACL = 'public-read'
AWS_S3_OBJECT_PARAMETERS = {'CacheControl': 'max-age=86400'}


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/3.0/howto/static-files/
PROJECT_DIR = os.path.dirname(os.path.abspath(__file__))
STATIC_ROOT = os.path.join(PROJECT_DIR, 'static')
AWS_LOCATION = 'static'

# 필자의 경우, 아래 STATIC_URL 이 'https://%s/' %(AWS_S3_CUSTOM_DOMAIN) 으로만 되어 있었음
# S3에 있는 정적파일들의 Full 주소는, 아래 조합과 같음. 
STATIC_URL = 'https://%s/%s/' % (AWS_S3_CUSTOM_DOMAIN, AWS_LOCATION) 

STATICFILES_STORAGE = 'storages.backends.s3boto3.S3Boto3Storage'
STATICFILES_DIRS = [os.path.join(BASE_DIR, 'static')]


# MEDIA_ROOT 설정 
# user-uploaded static files 의 기본 경로 
MEDIA_ROOT = os.path.join(BASE_DIR, '.media')
MEDIA_URL = '/media/'
```



참고로, secrets.json 파일은, 프로젝트 폴더 내에 존재하는 json 파일로,


이 파일에 AWS_ACCESS_KEY 값과, AWS_SECRET_ACCESS_KEY 값이 json 형태로 저장되어 있습니다.


이를, json.loads 를 사용하여, settings.py 파일에 적용해 줍니다.



## 권한 (엑세스 설정)



> 기본적으로, S3 버킷은, private 으로 설정이 되어 있어서,
>
> 그냥 정적파일을 읽어 오려 하면, 안 읽어 집니다.



따라서, S3 버킷 접근 속성을, Public 으로 바꾸어줘야.


서버를 실행 시켰을때, 정적파일들을 읽을수 있게 되어, 이 파일들을 이용해서 화면에 렌더해줄수 있습니다.


아래 사이트에 아주 자세히 나와 있어서, 참조하고 권한을 고쳐주면 됩니다.



https://www.simplified.guide/aws/create-public-s3-bucket





## 마치며..



S3 적용후에, 정적파일들이 적용 되지 않아서 당황했었지만,

다행히, 에러를 해결할수 있었습니다. 이제 정적파일들과 미디어 파일들은, 로컬 위치가 아닌, S3에서 불러 옵니다.

S3 에서 불러와도 로컬에서 불러온것처럼, 잘 적용되고 표시되는것을 확인할수 있었습니다.


![img](https://djangojeng-e.github.io/2020/06/16/TodoList-25%ED%8E%B8-AWS-S3-%EC%A0%95%EC%A0%81-%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%99%80-CSS-%ED%8C%8C%EC%9D%BC-%EC%A0%81%EC%9A%A9/image1.png)



# TodoList 26편 - Docker 설치 (Ubuntu18.04) 기준

## Docker 설치 (ubuntu18.04)





> **Docker 란?**
>
> Linux 컨테이너라는 커널 컨테이너 기술을 이용하여 만든 컨테이너 기술 중 하나.
>
> - 운영체제를 가상화 하지 않는 컨테이너 기술이라, 가상머신에 비해 가볍다
> - VM 을 포함하여, 한대의 서버에 여러개의 서비스를 구동하기 좋다
> - 가상머신 (VM) 과는 달리, 기존 리눅스 자원 (디스크, 네트워크) 들을 그대로 활용할수 있어서, 여러 서비스들을 한 서버에 돌기기가 좋은 편이다.
>
> 구글과 아마존에서도 도커를 지원하고 있기 떄문에, 많은 사용자들이 작성해둔 소프트웨어 패키지/이미지들이 많아서, 사용성과 접근성이 좋다.
>
> ```
> 다시 한번 정리하면, 도커란,
> ```
>
> 컴퓨터안에 다른 공간들과 분리된, 컨테이너 라는 가상의 공간을 만들어 주는 프로그램으로,
>
> 리눅스 기반으로 되어 있다.
>
> 컨테이너를 통해, 여러 환경들을 관리할수 있고, 같은 세팅을 공유하는 일도,
>
> 이미지 파일 하나만 공유한다면, 쉽게 작업할수 있다.





## Docker 설치하기 (Ubuntu18.04)



이전 도커의 버전들은, `docker`, `docker.io` 혹은 `docker-engine`으로 불리웁니다.


만약 이들이 설치되어 있다면, 언인스톨 해주어야 합니다. 터미널에서 아래 명령어를 실행해줍니다.



```
$ sudo apt-get remove docker docker-engine docker.io containerd runc
```





### Repository 셋업하기





1. apt 패키지 인덱스를 업데이트 해주고, apt 가 HTTPS 를 통해 저장소를 사용할수 있게 해줍니다

   ```
   $ sudo apt-get update
   
   $ sudo apt-get install \
       apt-transport-https \
       ca-certificates \
       curl \
       gnupg-agent \
       software-properties-common
   ```

2. 도커의 공식 GPG 키를 추가해 줍니다

   ```
   $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
   ```

3. 아래 명령어를 실행하여, 안정적인 저장소를 설정 합니다.

   ```
   $ sudo add-apt-repository \
      "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
      $(lsb_release -cs) \
      stable"
   ```

### Docker Engine 인스톨 하기



apt 패키지 인덱스를 업데이트 해주고, 가장 최근의 도커 엔진을 설치해 줍니다



```
$ sudo apt-get update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
```



> 도커 엔진이 알맞게 설치 되었는지 확인 하기 위해,
>
> hello-world 이미지를 실행 시켜 봅니다



이 커멘드를 실행 시키면, 테스트 이미지를 다운 받아서, 컨테이너 안에 실행 시킵니다.


컨테이너가 실행되면, 정보 메시지를 출력하고 종료됩니다.


![img](https://djangojeng-e.github.io/2020/07/05/TodoList-26%ED%8E%B8-Docker-%EC%84%A4%EC%B9%98-Ubuntu18-04-%EA%B8%B0%EC%A4%80/image1.png)

Docker 설치가 완료 되었습니다!



# TodoList 27편 - Docker 리뷰

## Docker 리뷰



> **도커란?**
>
> 컨테이너 기반의 오픈소스 가상화 플랫폼으로,
>
> 컨테이너는 다양한 프로그램, 실행환경을 추상화 하고. 동일한 인터페이스를 제공하여 프로그램의 배포 및 관리를 단순하게 해줍니다.



### 컨테이너 (Container)



격리된 공간에서 프로세스가 동작하는 기술로, 가상화 기술이지만 기존 가상화 기술과는 약간의 차이가 있습니다.


추가적인 OS를 설치하여 가상화 하는 방법 대신에, 프로세스를 격리 하는 방식을 사용합니다


리눅스 컨테이너를 사용하여, 단순히 프로세스를 격리시켜서 가볍고 빠르게 동작하게 할수 있습니다.


CPU 나 메모리는 딱 프로세스가 필요한 만큼만 추가로 사용하고, 성능적으로도 손실이 별로 없습니다.



### 이미지 (image)



**이미지는 컨테이너 실행에 필요한 파일과 설정값들을 포함하고 있는것으로.**


상태값을 가지게 되며, immutable 합니다 (즉, 한번 선언되면 수정될수는 없음). 같은 이미지를 가지고 여러개의 컨테이너를 생성할수 있고, 컨테이너의 상태가 바뀌거나 컨테이너가 삭제 되더라도, 이미지는 변하지 않고, 그대로 남습니다.



새로운 서버가 추가되면, 미리 만들어 놓은 이미지를 다운받고 컨테이너를 생성만 하면 됩니다.



한 서버에 여러개의 컨테이너를 실행할수 있고, 수십, 수백, 수천대의 컨테이너 실행도 괜찮습니다.



> **Docker Image (도커 이미지)는**
>
> Docker Hub 와 Docker Registry 저장소를 직접 만들어 관리할수 있습니다



# TodoList 28편 - Docker 컨테이너 생성 연습

## Docker 컨테이너 연습



이 포스팅은 Docker 컨테이너를 실행해보는 개인적인 과정을 담고 있습니다.


https://subicura.com/2017/01/19/docker-guide-for-beginners-2.html 에 있는 내용을 기반으로 따라한 과정과 정리 내용입니다.



도커에 대해 자세하게 알고 싶다면, 위 블로그를 참조하시면 됩니다.





## Docker 설치 확인





이전 포스팅에서 도커 설치를 진행 했었는데, 잘 설치되었는지 확인해 봅니다.


터미널에서, `docker version` 명령어를 실행해 봅니다.



```
docker version
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image1.png)

정상적인 설치가 완료 된것으로 보입니다.


버전 정보는 Client 와 서버로 나누어져 있습니다.

이는, 클라이언트와 서버 역할을 할수 있다는것을 의미 합니다.



## 컨테이너 실행 하기



도커를 실행하는 명령어의 구조는 아래와 같습니다.



```
docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG...]
```



자주 사용하는 옵션들은, 아래와 같습니다.



| 옵션  | 설명                                                     |
| :---- | :------------------------------------------------------- |
| -d    | detached mode 흔히 말하는 백그라운드 모드                |
| -p    | 호스트와 컨테이너의 포트를 연결 (포워딩)                 |
| -v    | 호스트와 컨테이너의 디렉토리를 연결 (마운트)             |
| -e    | 컨터이너 내에서 사용할 환경변수 설정                     |
| -name | 컨테이너 이름 설정                                       |
| -rm   | 프로세스 종료시 컨테이너 자동 제거                       |
| -it   | -i 와 -t 를 동시에 사용한 것으로 터미널 입력을 위한 옵션 |
| -link | 컨터이너 연결 [컨테이너명:별칭]                          |





## 컨테이너 생성하기



터미널에서 아래 명령어를 실행하여, 컨테이너를 생성해 봅니다



```
docker run ubuntu:18.04
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image2.png)

`docker run ubuntu:18.04` 를 실행하면,


ubuntu:18.04 를 로컬에서 찾을수가 없으니, 다운로드를 한 뒤에 컨테이너를 생성하고 시작합니다.





중요한점은, 컨테이너는 생성되었지만, 바로 종료가 된다는 점인데.


컨테이너는 잘 생성되어 실행 되었지만, 명령어가 전달되지 않았기 때문에,
생성되자마자 바로 종료가 됩니다. 컨테이너는 프로세스이기 때문에, 실행중인 프로세스가 없으면 컨테이너는 종료됩니다.



## 컨테이너 생성후 bin/bash/ 실행



위와 같은 명령어지만, 몇가지 옵션을 추가하여 컨테이너를 실행 해 볼수 있습니다.



```
docker run --rm -it ubuntu:18.04 /bin/bash
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image3.png)

`--rm` 프로세스가 종료되면, 컨테이너가 자동으로 삭제되도록 하는 옵션



`--it` 컨테이너 내부에 들어가기 위한 bash 쉘을 실행하고, 키보드 입력을 할수 있게 해줌



> bash 쉘을 `exit` 명령어를 사용하여 종료하면, 컨테이너도 같이 종료가 됩니다 (`--rm` 옵션으로 인해서..)





## Redis Container



redis 는 메모리기반의 다양한 기능을 가진 스토리지로,

6379 포트로 통신하여, telnet 명령어로 테스트해볼수 있습니다



일단 아래 명령어를 실행하여, detached mode 로 redis 컨테이너를 실행해줍니다

```
docker runi -d -p 1234:6379 redis
```



-d 와 -p 옵션을 추가하여, 컨테이너의 포트를 호스트 포트로 연결해 줍니다.


-d 가 없으면, 프로세스가 foreground 로 실행되어, 아무키도 입력할수 없습니다.


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image4.png)

redis 에 접속하여 새로운 키를 저장하고 불러와 보았습니다.


호스트의 포트만 다르게 하며느 하나의 서버에 여러개의 redis 서버를 띄우는것도 간단합니다.





## MySQL 5.7 Container 생성



MySQL 서버 컨테이너를 생성 후 실행해봅니다.


여기에 들어가는 옵션들이 중요한데.



| 옵션  | 설명                                      |
| :---- | :---------------------------------------- |
| -d    | detached mode 흔히 말하는 백그라운드 모드 |
| -p    | 호스트와 컨테이너의 포트를 연결 (포워딩)  |
| -e    | 환경변수 설정                             |
| –name | 읽기 어려운 ID 대신, 쉬운 이름을 부여     |



```
docker run -d -p 3306:3306 \
	-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
	--name mysql \
	mysql:5.7
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image5.png)

해당 시스템에는, mysql이 설치가 되질 않아서, mysql에는 접속이 되지 않습니다;;;



## tensorflow 컨테이너



머신러닝을 할수 있는 툴, tensorflow 를 만들어 실행해 봅니다



```
docker run -d -p 8888:8888 -p 6006:6006 teamlab/pydata-tensorflow:0.1
```



## 도커 기본 명령어 - 컨테이너 목록 확인



`ps` 는 실행중인 컨테이너 목록을 보여줍니다.



```
# 컨테이너 목록 확인 
docker ps [options]

docker ps
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image6.png)

혹은, `-a` 옵션을 주어도 됩니다



```
docker ps -a
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image7.png)

## 도커 기본 명령어 - 컨테이너 중지



```
docker stop [options] CONTAINER [CONTAINER...]
```



생성되어 있던 tensorflow 를 중지해봅니다



```
docker ps  # get container ID
docker stop TENSORFLOW_CONTAINER_ID 
docker ps -a
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image8.png)

## 도커 기본 명령어 - 컨테이너 제거



종료된 컨테이너를 완전하게 제거하는 명령어는 아래와 같습니다.



```
docker rm [options] CONTAINER [CONTAINER..]
```



종료된 ubuntu 컨테이너와 tensorflow 컨테이너를 삭제해봅니다



```
docker ps -a   # get container ID 
docker rm #ubuntu_CONTAINER_ID #TENSORFLOW_CONTAINER_ID 

docker ps -a  # 위 컨테이너들이 존재하는지 확인
```





## 도커 기본 명령어 - 이미지 제거



이미지 목록 확인 하기 (images)

```
docker images
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image9.png)

컨테이너가 실행중인 이미지는 삭제되지 않지만, 아래 명령어를 사용해서, tensorflow 를 제거해봅니다



```
hello-world                     latest              fce289e99eb9        16 months ago       1.84kB
teamlab/pydata-tensorflow       0.1                 7bdf5d7e0191        3 years ago         3.08GB
 dhkang  ~ $ docker rm 7bdf5d7e0191
```


![img](https://djangojeng-e.github.io/2020/07/07/TodoList-28%ED%8E%B8-Docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%83%9D%EC%84%B1-%EC%97%B0%EC%8A%B5/image10.png)

이미지가 삭제 됩니다



# TodoList 29편 - Dockerfile 작성후 이미지 생성

## Dockerfile 작성



배포를 위해 현재까지 진행한 단계는, 아래와 같습니다.


각 단계마다 매우 꼬이고 있어서, 순서를 좀 정리 해봅니다.



1. AWS EC2 생성
2. AWS RDS 생성 - PostgreSQL 을 Django 프로젝트에 적용
3. AWS S3 생성 및 Django 프로젝트에 적용
4. AWS S3 정적 이미지와 CSS 파일 적용
5. Docker 설치
6. Dockerfile 작성 후 이미지 생성
7. DockerHub Push



> **Dockerfile 이란?**
>
> Dockerfile 은 Docker 이미지를 생성하기 위해 필요한 명령어를 가지고 있는 파일로,
>
> 이 파일이 있는 디렉토리에서, Docker 이미지를 만들수 있습니다.
>
> Dockerfile 에 나열되어 있는 명령어들을 자동으로 실행하여, 이미지를 빌드할때 해당 이미지에 필요한
>
> 파일과 설정값들을 정할수 있습니다.
>
> 확장자 없이도 동작하고,
>
> `RUN`, `COPY`, `WORKDIR` 과 같은, Dockerfile 언어로 작성할수 있습니다.





잘 작성된 Dockerfile 을 사용하여, 이미지를 생성할수 있고, 이미지는 컨테이너 실행에 필요한 파일들과 설정값들을 포함하고 있습니다.



**일단 dockerfile 과 start 파일을 작성해 보려 합니다. 작성을 마친뒤에는, 작성된 Dockerfile 을 사용하여, 이미지를 생성해 볼겁니다.
**



## 프로젝트 구조 확인하기



```
.
├── config
│   ├── asgi.py
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── db.sqlite3
├── Dockerfile     # 확장자가 없는 Dockerfile 파일 
├── manage.py
├── requirements.txt
├── secrets.json
├── start          # 확장자가 없는 start 파일 
├── static
└── todo
```



확장자가 없는, `Dockerfile` 과 `start` 파일을 생성해 줍니다



## Dockerfile 파일과 start 파일



**컨테이너 실행에 필요한 이미지는 아래 사항들을 바탕으로 작성되어야 합니다**



- 기본 이미지는 Python 3.8 을 베이스로 합니다
- 이 이미지는, 로컬에 존재하는 todolist 프로젝트 파일들을 복사해서 가지고 있습니다
- 이미지 안에, todo/requirements.txt 를 가지고 있기 때문에, `pip install -r /todo/requirements.txt`를 실행하여, 필요 패키지들을 설치 합니다
- 이 이미지를 사용하여 실행되는 컨테이너는, runserver 를 실행 시킵니다 (runserver 명령어를 실행하기 이전에 공통된 명령어들, `python manage.py makemigrations`, `python manage.py migrate` 그리고 `python manage.py runserver` 같은 명령어들은, todo/start 파일에 담겨져 잇어서, 해당 이미지는 이 start 파일을 실행시켜서 서버를 시작합니다.)



> todolist/Dockerfile 을 아래와 같이 작성



```
# 기본설정 
RUN apt-get update \ 
	&& apt-get install -y --no-install-recommends \
		postgresql-client \
	&& rm -rf /var/lib/apt/lists/* 
	
# Django 프로젝트 파일들을 이미지로 복사 
# 이미지 안에서도 todolist/ 폴더로 복사되게 지정 
COPY . /todolist 

# 이미지에 복사된 requirements.txt 를 사용하여 패키지 설치 
RUN pip install -r /todolist/requirements.txt 

# start 파일을 실행할수 있게 파일 설정 
RUN chmod 755 /todolist/start 

# 워킹 디렉토리를 /todolist 로 설정 
WORKDIR /todolist 

# 8000 포트를 expose 
EXPOSE 8000 

# app/start 파일을 실행 
ENTRYPOINT ["/todolist/start"]
```





> todolist/start 파일을 아래와 같이 작성
>
> - 참고 : start 파일은 확장자가 없음



```
#!/bin/bash 

python manage.py makemigrations 
python manage.py migrate 

python manage.py runserver 0.0.0.0:8000
```



## Dockerfile 로 이미지 생성하기



Dockerfile 과 start 파일을 작성해 주었으니,


이 파일들을 이용하여, 이미지를 하나 생성해 볼수 있습니다.



프로젝트 폴더인, todolist/폴더로 이동하여, 아래 명령어를 사용합니다.



```
docker build -t todolist .
```



`-t` : 생성될 이미지의 이름을 지정



`.`: Dockerfile 과 프로젝트 파일들이 있는 경로를 표시, Dockerfile 이 있는 위치이므로, 현재경로인 `.` 을 입력



> docker images 명령어를 사용하여,
>
> todolist:latest 와 Base 이미지인, python 3.8 이 있는것을 확인 할수 있습니다.
>
> 영상참고



https://blog.naver.com/headfat1218/221975245936





## Docker 실행 시키기



이미지가 생성되었으니, Docker 를 로컬에서 실행 시켜 보려 합니다.


8000번 포트를 열어 주기 때문에, `-p 8000:8000` 옵션을 넣어줍니다.



```
docker run -d -p 8000:8000 --name todolist todolist:latest
```



| **옵션**        | **descriptions**                   |
| :-------------- | :--------------------------------- |
| -d              | 백그라운드 실행                    |
| -p 8000:8000    | 호스트와 컨테이너 포트를 매핑      |
| –name todolist  | 컨테이너의 이름을 todolist 로 지정 |
| todolist:latest | 이미지의 이름과 버전을 지정        |


![img](https://djangojeng-e.github.io/2020/07/21/TodoList-29%ED%8E%B8-Dockerfile-%EC%9E%91%EC%84%B1%ED%9B%84-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%83%9D%EC%84%B1/image1.png)

`todolist:latest` 이미지를 사용하여, 컨테이너를 하나 실행 시켜 주었습니다.


이 컨테이너는, 백그라운드로 실행이 되고, dockerfile 에서 지정해준대로, runserver 를 구동하고 있습니다

브라우저로, 서버에 접속해 봅니다 ([http://127.0.0.1:8000](http://127.0.0.1:8000/))



로컬에서는 잘 작동하는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/07/21/TodoList-29%ED%8E%B8-Dockerfile-%EC%9E%91%EC%84%B1%ED%9B%84-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%83%9D%EC%84%B1/image2.png)

## 마치며..



배포가 제일 어려운것 같습니다. 틀린 순서일지도 모르지만,


지나온 순서를 다시 리마인드 합니다.



1. AWS EC2 생성
2. AWS RDS 생성 - PostgreSQL 적용
3. AWS S3 생성 및 적용
4. AWS S3 정적 이미지와 CSS 파일 적용
5. Docker 설치
6. **Dockerfile 작성 후 이미지 생성**
7. DockerHub Push



# TodoList 30편 - 생성된 이미지 DockerHub 에 푸쉬

## DockerHub 에 이미지 푸쉬



현재까지 진행한 순서대로 정리하면,



1. AWS EC2 생성
2. AWS RDS 생성 - PostgreSQL 적용
3. AWS S3 생성 및 적용
4. AWS S3 정적 이미지와 CSS 파일 적용
5. Docker 설치
6. Dockerfile 작성 후 이미지 생성
7. 생성된 이미지를 DockerHub 에 push



## 이미지 저장소



도커는 빌드한 이미지를 서버에 배포하기 위한 파일을 도커 레지스트리, 즉, Docker registery 라는 저장소를 사용해 복사합니다. 빌드가 완료된 이미지를 레지스트리에 푸쉬 (push) 하고, 다른 서버에서 (pull) 하여 사용하는 구조로 되어 있습니다.



살짝, git 과 비슷한 느낌입니다.


![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image1.png)

## Docker Hub



도커 허브는, 도커에서 제공하는 이미지 저장소 입니다.

ubuntu, centos, debian 등의 베이스 이미지와, ruby, golang, java, python 등의 공식 이미지가 저장되어 있습니다.



회원 가입 이후에, 대용량 이미지를 무료로 저장할수 있고, 다운로드 트래픽도 무료 입니다.



하지만, 모든 이미지는 공개되어 누구나 접근 가능하고, 비공개로 설정하려면 유료 서비스를 이용해야 합니다.

도커 헙 페이지에 접속하여, 회원가입을 마칩니다.
회원가입을 다 마치면, 로그인을 하여, 사용 준비를 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image2.png)

## Docker Hub 로그인



계정을 생성해주었으면, 터미널에서 아래 명령어를 실행하여,


도커 허브 계정을 사용할수 있게 로그인 합니다.

```
docker login
```



아래와 같은 메시지가 뜨고, 성공적으로 로그인이 되는걸 확인할수 있습니다.


로그아웃 하기 전까지, 로그인 정보가 유지됩니다.

```
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded
```



## Docker Hub Push



지난 포스팅에서, Dockerfile 을 이용하여, docker image 를 생성해 주었었습니다.



`docker images`명령어를 실행하여, 이미지 목록을 확인해 보면, 지난 시간에 생성해 주었었던,

`todolist:latest` 이미지가 있는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image3.png)

이제 이 이미지를, DockerHub 에 전송해 보도록 하겠습니다.



> 우선, tag 명령어를 사용하여,
>
> 업로드할 이미지의 이름을 지정해 줍니다.



```
docker tag todolist:latest headfat1218/todolist:latest
```



> 이미지를 지정해주었으면, 이제 push 명령어를 사용하여,
>
> push 를 진행 합니다.



```
docker push headfat1218/todolist:latest
```



명령어를 실행하면, 아래와 같이 진행이 되고


![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image4.png)
![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image5.png)

> push 가 완료 된 후에, DockerHub 페이지로 가보면,
>
> repository 에 전송 되어 있는것을 확인 할수 있습니다.


![img](https://djangojeng-e.github.io/2020/07/24/TodoList-30%ED%8E%B8-%EC%83%9D%EC%84%B1%EB%90%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-DockerHub-%EC%97%90-%ED%91%B8%EC%89%AC/image6.png)

## 이미지를 푸쉬 하는 이유



DockerHub 에 업로드 된 이미지는, 어디서든 pull 받아서 사용할수 있습니다.


아래 명령어를 예로 들어보면, DockerHub repository 저장소, headfat1218/todolist:latest 를 다운 받아와서 사용하겠다는 명령어 입니다.



```
docker run -d -p 8000:8000 --name todolist headfat1218/todolist:latest
```



터미널에서, 위 명령어를 실행한뒤에, 브라우저에 접속해서 127.0.0.1:8000 으로 접속하면,


프로젝트가 잘 작동합니다.

로컬에서는 배포가 잘 이루어 지는것 같아 보이는데… 그 다음 스텝은 무엇이 될까요…? ㅠㅠ



# TodoList 31편 - Docker를 이용하여 EC2에 배포

현재까지 배포를 위해 진행한 순서는,

1. AWS EC2 생성
2. AWS RDS 생성 - PostgreSQL 적용
3. AWS S3 생성 및 적용
4. AWS S3 정적 이미지와 CSS파일 적용
5. Docker 설치
6. Dockerfile 작성후 이미지 생성
7. 생성된 이미지를 DockerHub 에 Push
8. Docker 를 이용하여 EC2 배포하기





지난 포스팅에서, 로컬에서 생성한 Docker 를 실행하여, 서버가 잘 작동하는것을 확인 하였습니다.

이제 EC2 에다가, 생성하였던 Docker 를 이용하여, 서버를 실행 시켜 보는 작업을 진행합니다.





> EC2에 배포를 하려면,
>
> EC2에 접속하여, EC2 터미널에서 생성한 Docker 를 실행시키면 됩니다.





정확히 이야기하면, 아래 단계들을 거쳐야 하는데.


이 단계들을 명령어화 하여 진행해 보면 됩니다.



1. EC2에 접속, 서버 초기 설정을 해줍
2. 로컬에서 pip freeze > requirements.txt 를 실행, requirements.txt 를 갱신
3. 로컬에서 Dokerfile 을 이용하여, Docker 이미지 빌드
4. 로컬에서 Docker image tag 지정
5. 로컬에서 DockerHub 로 docker push
6. EC2 에서, DockerHub 에 push 된 이미지 pull
7. EC2 에서, pull 한 Docker 이미지를 실행하기



> 이전 포스팅에서 생성한 Docker 이미지는,
>
> 자동으로 python manage.py runserver 를 실행해 주므로.
>
> Docker 이미지를 실행 시키는것만으로도 runserver 를 실행한 상태로 유지해 줍니다.



## 1. EC2에 접속, 서버 초기 설정



터미널에서, EC2 에 접속합니다. 접속하여서, 기본 세팅을 해줍니다.

```
ssh -i ~/.ssh/newtodolist.pem ubuntu@52.79.175.188
*** System restart required ***
Last login: Sat May 23 13:06:22 2020 from 49.194.199.4
ubuntu@ip-172-31-35-66:~$ sudo apt update && sudo DEBIAN_FRONTEND=noninteractive apt dist-upgrade -y && apt -y autoremove

# Docker 도 설치해 줍니다. 
ubuntu@ip-172-31-35-66:~$ sudo apt -y install docker.io
```



## 2. 로컬 컴퓨터 터미널에서 requiremnts.txt 갱신



```
~/mini_projects   master ●  
"$HOME"/.pyenv/versions/3.8.0/envs/to-do-list/bin/pip freeze > "$HOME"/mini_projects/todolist/requirements.txt
```



## 3. 로컬에서 Dockerfile 을 이용, Docker 이미지 빌드



```
docker build -t todolist ~/mini_projects/todolist
```



todolist 라는 이미지를, ~/mini_projects/todolist 경로에 있는 Dockerfile 을 이용하여 생성.



## 4. 로컬에서 docker image tag 지정



```
docker tag todolist:latest headfat1218/todolist:latest
```



todolist:latest 를 테그로 지정



## 5. 로컬에서 DockerHub 로 docker push



```
docker push headfat1218/todolist:latest
```



headfat1218/todolist:latest 로 테그된 이미지를 DockerHub 로 푸쉬



## 6. EC2 에서, DockerHub 에 push 된 이미지 pull



```
ubuntu@ip-172-31-35-66:~$ sudo docker pull headfat1218/todolist:latest
```



## 7. EC2 에서, Pull 한 이미지를 실행하기



```
sudo docker run --rm -it -p 80:8000 --name todolist headfat1218/todolist:latest
```



이미지를 실행하면, 이미지가 빌드된 대로,



`python manage.py makemigrations`를 알아서 해주고

`python manage.py migrate` 도 알아서 해주고,



`python manage.py runserver` 도 알아서 해줍니다.



![img](https://djangojeng-e.github.io/2020/07/27/TodoList-31%ED%8E%B8-Docker%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-EC2%EC%97%90-%EB%B0%B0%ED%8F%AC/image1.png)



> 이제, EC2 인스턴스에서 RUNSERVER 를 실행하고 있으니까
>
> 브라우저에서, 해당 EC2 인스턴스의 퍼블릭 IP 로 접속해 봅니다.
>
> 퍼블릭 IP 로 접속을 하면, 브라우저에서, 현재까지 개발한 Django 프로젝트가 돌아가는것을 확인할수 있을겁니다.



# TodoList 32편 - .dockerignore 파일

## dockerignore 파일



현재까지 배포를 위해 진행한 스텝입니다



1. AWS EC2 생성
2. AWS RDS 생성 - PostgreSQL 적용
3. AWS S3 생성 및 적용
4. AWS S3 정적 이미지와 CSS 파일 적용
5. Docker 설치
6. Dockerfile 작성 후 이미지 생성
7. 생성된 이미지를 DockerHub 에 Push
8. Docker 를 이용하여 EC2 에 배포하기 (.dockerigrnoe파일)



지난 포스팅까지, Docker 를 이용하여, EC2 에 배포하는것 까지 진행 하였고.

EC2 에 잘 배포가 되는것까지 확인 하였습니다.



> 하지만, Docker 를 사용하여,
>
> EC2 에 배포할때에, .dockerignore 파일에 대해서
>
> 신경 써야 한다는 점을 잊지 말아야 합니다



## .dockerignore 파일이란?





`.gitignore` 파일과 비슷하게, `.dockerignore` 파일은 하나의 도구로써,

정말로 필요한 Docker 빌드 내용을 정의 하는데에 사용됩니다.



이 파일을 사용함으로써, 어떤 폴더와 파일들이 도커를 빌드할때에 무시되고, 예외처리가 될지 기준을 정합니다

마치, git 에 무언가를 작업하여 올릴때, gitignore 에 올리지 말아야 할 파일과 디렉토리를 정하는것 처럼 말입니다.

`.dockerignore` 파일에 포함된 파일들과 폴더들은,
빌드내용에 포함되지 않게 되어서 도커 서버에 업로드가 되지 않을것입니다.



## 왜 신경을 써야 하나요?



실제로, 요즘 컴퓨터들은 빠르고, 네트워크도 꽤나 안정적이고 빠른데다가 저장소를 사용하는것도 많이 저렴해 졌습니다. 그렇다면,

> `.dockerignore`를 사용하여, 파일들과 폴더들을 업로드 하지 않는 이유가.
>
> 용량때문만은 아닐것입니다.



어째서 .dockerignore 를 사용해야 하는지에 대해서 몇가지 예를 들어 봅니다.





### 도커 이미지 사이즈



요즘 컴퓨터들이 아무리 빠르고, 네트워크도 안정적이고, 저장소가 저렴해졌다고 해도,


도커 이미지의 용량에 대해서 신경쓰지 않아도 된다는 이야기는 아닙니다. 여전히 용량은 중요한 포인트 이고, 어떤 상황에서는, 하나의 도커 이미지를 여러개의 서버에 올려서 사용해야 할때도 있습니다.



이런 상황에서는, 이미지 사이즈와 이미지 생성 시간이 중요해 집니다.



예를들면, 5 MB 짜리 도커 이미지를 100개의 서버에 배포하는것이, 700MB 짜리 도커 이미지를 100개의 서버에 배포하는것보다 훨씬 빠르고 저렴할것입니다.



따라서, .dockerignore 를 사용하여, 용량관리를 해보는것도 나쁘지 않은 생각입니다.



### 의도하지 않은 비밀값 노출



빌드 내용을 제어하지 않는다면,


의도치 않게 우리의 코드, 커밋 내역 그리고 비밀값들 (키와 인증키 등등) 이 노출 될수 있습니다.



`ADD .` 혹은 `COPY .` 커맨드를 사용하여 파일들을 도커 이미지 안에 복사를 한다면, 우리는 의도하지 않게 소스 파일들을 포함하고, 모든 깃 내역과, 비밀값들 (.aws, env, 혹은 개인정보 키같은 값들), 캐시, 그리고 기타 파일들을 도커 빌드 내용에 넣어, 결국에 도커 이미지 안에 의도치 않게 넣을수 있습니다.



DockerHub 에 존재하는 도커 이미지중에 여러개의 이미지들은, 어플리케이션 소스 코드, 비밀번호, 키값들 그리고 인증값들을 노출하고 있습니다. `.git` 폴더를 도커 이미지안에 실수로라도 복사하는것은 특히나 좋지 않습니다.



따라서, `.dockerignore` 를 사용하여, 이런 비밀값들이나 민감한 정보 관리를 할수 있습니다.


`.git folder`를 `.dockerignore` 파일에 항상 표시해두는것이 좋습니다.



### Cache 무효



어플리케이션의 전체 코드베이스를 이미지에 넣는 방식은 대체로 아래 명령어를 사용하는것이 일반적입니다.



```
COPY . /user/src/app
```



이런 경우에는, 전체 빌드 내용을 이미지에 복사하게 됩니다.


그리고 한가지 더 중요한점은, 각 Dockerfile 명령어는 새로운 레이어를 생성한다는 점입니다.



따라서, 포함된 파일들에 대한 수정 사항들은, `COPY . /opt/myapp` 레이어와 새로운 이미지 레이어가 다음 빌드에 생성되게 될때, 빌드 캐시를 무효화 합니다 (빌드를 훨씬 더 느리게 한다는 이야기 입니다)





만약 우리의 작업 폴더들이 매우 빈번히 업데이트가 되는 파일들을 가지고 있다면, (로그, 테스트 결과, 깃내역, 임시 캐시파일등등), 우리는 이 레이어를 매번 dockr build 를 실행할때마다 생성하게 될것입니다.



따라서, 빌드 내용을 맞게 관리 하지 않는다면, 빌드들은 캐시를 맞는 방법으로 사용할수가 없어서, 매우 느려질겁니다.





### .dockerignore 문법



이제 우리는 어째서 도커 빌드 내용을 제어해야만 하고, 이것이 어떻게 이루어지는지 살펴 보았습니다.



> .dockerignore 파일은,
>
> 깃툴에서 사용되는, .girignore 파일과 비슷합니다.



깃헙에서 사용되는 `.gitignore` 파일들과 비슷하게, 도커 빌드 내용을 생성할때 무시되어야 할 파일들과 폴더들을 특정 패턴을 통해 설정할수 있습니다.



> `.dockerignore` 파일 문법이, `.gitignore` 파일과 비슷한 문법이다 하더라도,
>
> 그것들이 똑같지는 않습니다!



예시)



```
# .git 과 .cache 폴더를 무시 
.git 
.cache 

# ignore all *.class files in all folders, including build root
# 모든 폴더안에 있는 모든, *.class 파일들을 무시   
**/*.class 
 
# 모든 마크다운 파일들 (md) 파일들을 무시, 
# 모든 README*.md 파일 무시 
*.md 
IREADME*.md
```





### Dockerfile 을 .dockerignore 에 포함되어야 하나요?



이 질문에 대한 명확한 답은 존재하지 않습니다.


대부분 개인 취향에 달려 있긴 하지만. `.dockerfile` 을 `Dockerfile` 안에 포함하는것이 권장 됩니다

그리고, 그전에, Dockerfile 이 어떠한 민감한 정보도 갖고 있지 않도록 합니다.





## 마치며..



`.dockerignore` 파일을 도커 이미지들을 생성하는 모든 프로젝트에 사용해야 합니다.


`.dockerignore`는 도커 이미지들을 작고, 빠르고, 안전하게 만들어 줍니다.
또한, 로컬 개발에서 도커 캐시를 도와주기도 합니다.



최소한, git 폴더는 .dockerignore 파일에 포함되어야만 합니다. 그리고 아래 프로젝트 특화 추가 파일들도 추가해주어야 합니다.



- 빌드로그
- 스크립트 / 결과 테스트
- 임시 파일들
- 캐싱
- 로컬 비밀값들
- docker-compose.yml 같은 로컬 개발 파일들



# TodoList 33편 - Docker로 배포 (nginx 와 gunicorn)

## Docker로 배포 (nginx, gunicorn)



연습 차원에서, Docker 에 nginx 와 gunicorn 을 적용하여 배포 연습을 진행 해 봅니다.


이전 포스팅하고는 조금 다른 방식으로 쓰는 예제를 찾아서, 이 프로젝트에 적용해 보려 합니다. 아래 내용대로 진행해보았지만, 배포 결과는 실패했습니다.


이 포스팅에서는, 그냥 배포 시도 과정만을 담고 있습니다.



## Docker Compose 설치하기



터미널에서 아래 명령어를 실행하여, Docker compose 를 설치해 줍니다.



```
sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```



바이너리에 실행 권한을 적용합니다.



```
sudo chmod +x /usr/local/bin/docker-compose
```



정상적으로 설치가 되었는지, 아래 버전 확인 명령어를 통해서 확인 합니다.



```
docker-compose --version 
docker-compose version 1.25.5, build 8a1c60f6
```





## gunicorn 설치하기



```
pip install gunicorn
```





## requirements.txt 파일 업데이트



프로젝트 폴더에서 터미널 명령어를 통해, requirements.txt 파일을 업데이트 해줍니다



```
pip freeze > requirements.txt
```



## settings.py 파일 수정



```
ALLOWED_HOSTS = ['web']
```



## Dockerfile 재작성



```
FROM python:3.8-slim
COPY . /todo 
WORKDIR /todo 
RUN pip install -r /todo/requirements.txt
```



1. `FROM python:3.8-slim` - 파이썬 3.8-slim 을 베이스 이미지로 사용합니다
2. `COPY . /todo` - 컨테이너 안에 /todo 디렉토리 안으로, 로컬에서의 현재 위치에 있는 모든 파일 및 디렉토리를 복사합니다
3. `WORKDIR /todo` - ~/todo 디렉토리로, 워킹 디렉토리를 변경합니다
4. `RUN pip install -r /todo/requirements.txt` - 프로젝트에 필요한 파이썬 패키지들을 설치합니다.



## docker-compose.yml 작성



docker-compose 를 사용하여, Dockerfile 빌드 준비를 합니다.


프로젝트 폴더 내에, docker-compose.yml 파일을 생성해 주고, 아래와 같이 작성해 줍니다.



```
version: '3'
services:
	web:
		build:
		  context: . 
		  dockerfile: Dockerfile 
		command: python manage.py runserver 0.0.0.0:8000
		volumes:
		  - .:todo
		ports:
		  - "8000:8000"
```



1. version - docker compose 정의 파일의 버전
2. services - 서비스 정의
3. web - 서비스 명
4. build - 빌드 지정
5. context - Dockerfile 이 있는 디렉토리의 경로
6. dockerfile - 도커파일명
7. command - 컨테이너 안에서 작동하는 명령 지정, 베이스 이미지에 지정되어 있을 경우 덮어씁니다
8. volumes - 컨테이너에 볼륨을 마운트 합니다
9. ports - 컨테이너가 공개하는 포트는 ports 로 지정, `<호스트 머신의 포트번호>:<컨테이너의 포트번호>`



## docker-compose 명령어로 이미지를 빌드하여 실행



```
docker-compose up -- build
```



위 명령어를 실행한뒤에, 터미널에는, Attaching to todolist_web_1 이라고 표시 되는데. 이때, 웹 브라우저에서 django 어플리케이션이 정상적으로 실행 되는지 확인 합니다.


![img](https://djangojeng-e.github.io/2020/07/29/TodoList-33%ED%8E%B8-Docker%EB%A1%9C-%EB%B0%B0%ED%8F%AC-nginx-%EC%99%80-gunicorn/image1.png)

우선, django 어플리케이션은 브라우저에서 동작하는것을 확인할수 있습니다.


![img](https://djangojeng-e.github.io/2020/07/29/TodoList-33%ED%8E%B8-Docker%EB%A1%9C-%EB%B0%B0%ED%8F%AC-nginx-%EC%99%80-gunicorn/image2.png)





## nginx, gunicorn, django



nginx 와 gunicorn 을 사용하여 django 배포 작업을 해줍니다.

위의 docker-compose.yml 파일을 아래와 같이 수정해 줍니다.



```
version: '3'
services:
  nginx:
    image: nginx:latest
    ports:
      - "80:80"
    volumes:
      - .:/todo
      - ./config/nginx:/etc/nginx.conf.d
    depends_on:
      - web 
  web:
    build:
      context: .
      dockerfile: Dockerfile 
    command: gunicorn todolist.wsgi:application == bind 0.0.0.0:8000 
    volumes: 
      - .:/todo 
    expose: 
      - "8000"
```



1. version - docker compose 정의 파일의 버전
2. services - 서비스 정의
3. nginx - 서비스명
4. image - 도커이미지 (d2hub 에서 최신버전)
5. ports - <호스트 머신 번호>:<컨테이너의 포트 번호>
6. volumes - 컨테이너에 볼륨을 마운트 할때 사용, <호스트의 디렉토리 경로>:<컨테이너의 디렉토리 경로>
7. depends_on
   - 서비스의 의존 관계 정의
   - nginx 컨테이너 시작전, web 컨테이너 시작
   - depends_on 으로 컨테이너 시작 순서를 제어
   - 컨테이너 상 어플리케이션이 이용 가능해질때까지 기다리고, 제어 하지 않음

1. web - 서비스명
2. build - 빌드지정
3. context - Dockerfile이 있는 디렉토리의 경로
4. dockerfile - 도커파일 명
5. command
   - 컨테이너 안에서 작동하는 명령어 지정
   - gunicorn 으로 django 서버를 실행 시킴

1. volumens - 현재 디렉토리 전부를 컨테이너 /todo 에 마운트
2. ports
   - 컨테이너 포트 번호만 지정
   - 호스트 머신의 포트는 랜덤한 값으로 설정
   - 호스트 머신에서 직접 접근하지 않고 웹서버 기능을 갖고 있는 컨테이너를 경유해서 접근



## nginx.conf 파일 생성



todolist/config/nginx/nginx.conf 파일을 생성해 줍니다.



```
upstream web {
  ip_hash;
  server web:8000;
}

server {
  location /static/ {
      alias /todo/static;
  }

  location / {
      proxy_pass http://web/;
  }

  listen 80;
  server_name localhost;
}
```





## docker-compose 명령어로 이미지 빌드후 실행



```
docker-compose up --build
```



## 마치며..



django 어플리케이션을 배포하는일은 매우 어렵고 비효율적입니다.

이렇게 복잡하게 할거면, 왜 만들었는지도 의심 스럽네요;;; 위 작업을 다 마쳤지만, 실제 배포랑은 완전 거리가 멀고. 되지도 않았습니다.

다른 배포 방법을 찾아봅시다!

