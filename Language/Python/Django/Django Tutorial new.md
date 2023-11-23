# Django Tutorial

출처: https://www.pythontutorial.net/django-tutorial/

이 Django 튜토리얼은 실용적이고 실제적인 예제를 통해 처음부터 Django 웹 프레임워크를 사용하는 방법을 배우는 데 도움이 됩니다.



### Prerequisites

Django 튜토리얼을 따라 하려면 다음 지식이 있어야 합니다.

- [Python basics](https://www.pythontutorial.net/python-basics/)
- HTML/CSS. [JavaScript](https://www.javascripttutorial.net/) 지식은 필요하지 않습니다.
- Relational databases ([SQLite](https://www.sqlitetutorial.net/), [MySQL](https://www.mysqltutorial.org/), [PostgreSQL](https://www.postgresqltutorial.com/), …).

튜토리얼은 Django 4.x와 관련이 있습니다.



## Section 1. Django Basics

이 섹션에서는 사용자 가입, 로그인, 블로그 게시물을 게시할 수 있는 블로그를 구축하는 기본 Django 개념을 배우게 됩니다.



- [Getting Started with Django](https://www.pythontutorial.net/django-tutorial/getting-started-with-django/) – Django 프레임워크가 무엇인지, Django 패키지 설치 방법, 프로젝트 설정 방법, 개발 웹 서버를 사용하여 Django 애플리케이션 시작 방법을 설명하여 Django를 시작하는 데 도움을 줍니다.
- [Creating an application](https://www.pythontutorial.net/django-tutorial/django-create-app/) – Django에서 블로그 애플리케이션을 생성하고 URL을 뷰와 매핑하는 방법을 보여줍니다.
- [Building Django Templates](https://www.pythontutorial.net/django-tutorial/django-templates/) – 템플릿을 생성하고 뷰 함수에서 템플릿으로 변수를 전달하는 방법을 알아보세요.
- [Defining Django models](https://www.pythontutorial.net/django-tutorial/django-models/) – 간단한 Django 모델을 만드는 방법을 보여줍니다.
- [Making & Applying Migrations](https://www.pythontutorial.net/django-tutorial/django-migrations/) – 마이그레이션을 만들고 적용하는 방법을 알아보세요.
- [Django Admin page](https://www.pythontutorial.net/django-tutorial/django-admin-page/) – Django 관리 페이지를 사용하는 방법을 보여줍니다.
- [Defining Django form](https://www.pythontutorial.net/django-tutorial/django-form/) – 새 게시물을 생성하고 데이터베이스에 저장하는 ModelForm을 정의합니다.
- [Using Flash messages](https://www.pythontutorial.net/django-tutorial/django-flash-messages/) – 플래시 메시지를 만들고 표시하는 방법을 보여줍니다.
- [Creating an Edit Form](https://www.pythontutorial.net/django-tutorial/django-edit-form/) –게시물을 업데이트하는 편집 양식을 만드는 방법을 알아보세요.
- [Creating a Delete Form](https://www.pythontutorial.net/django-tutorial/django-delete-form/) – 게시물을 삭제하는 삭제 양식을 만드는 방법을 보여줍니다.
- [Login/Logout](https://www.pythontutorial.net/django-tutorial/django-login/) – Django 애플리케이션에 대한 로그인/로그아웃 시스템 만들기
- [Creating Registration Form](https://www.pythontutorial.net/django-tutorial/django-registration/) – 사용자가 계정을 만들 수 있는 등록 양식을 만드는 방법을 보여줍니다.





## 섹션 2. Django 클래스 기반 뷰

이 섹션에서는 사용자 등록, 로그인, 비밀번호 재설정, 프로필 생성 및 자신의 작업을 관리할 수 있는 Todo List 앱을 구축하는 클래스 기반 보기를 학습합니다.

- [Django Todo App](https://www.pythontutorial.net/django-tutorial/django-todo-app/) – show you how to create the Todo App project structure from scratch.
- [ListView](https://www.pythontutorial.net/django-tutorial/django-listview/) – learn how to use the ListView class to display a list of tasks.
- [DetailView](https://www.pythontutorial.net/django-tutorial/django-detailview/) – show you how to use DetailView class to display a task.
- [CreateView](https://www.pythontutorial.net/django-tutorial/django-createview/) – learn how to use the CreateView class to create a form that creates a task.
- [UpdateView](https://www.pythontutorial.net/django-tutorial/django-updateview/) – guide you on how to use the UpdateView class to create a form that edits a task.
- [DeleteView](https://www.pythontutorial.net/django-tutorial/django-deleteview/) – walk you through the steps of using the DeleteView class to delete an existing task.
- [LoginView](https://www.pythontutorial.net/django-tutorial/django-loginview/) – show you how to use LoginView to create a login page for the Todo App.
- [FormView](https://www.pythontutorial.net/django-tutorial/django-formview/) – learn how to use FormView to create a registration page that allows users to sign up.
- [Password Reset](https://www.pythontutorial.net/django-tutorial/django-password-reset/) – show you how to implement the password reset feature for a Django application.
- [User Profile](https://www.pythontutorial.net/django-tutorial/django-user-profile/) – walk you through the steps of implementing a user profile feature for the Todo app



## Section 3. Django ORM

이 섹션에서는 Django ORM에 대해 자세히 알아보고 이를 효과적으로 사용하여 관계형 데이터베이스와 상호 작용하는 방법을 알아봅니다.

[
Django ORM](https://www.pythontutorial.net/django-tutorial/django-orm/) – an introduction to the Django ORM and set up a base project for the next tutorials in this section.

[One-to-One relationship](https://www.pythontutorial.net/django-tutorial/django-one-to-one/) – explain to you the one-to-one relationship and how to use OneToOne to create a one-to-one relationship in Django.

[One-to-Many relationship](https://www.pythontutorial.net/django-tutorial/django-one-to-many/) – show you how to use ForeignKey to create a one-to-many relationship in Django.

[Many-to-Many relationship](https://www.pythontutorial.net/django-tutorial/django-many-to-many/) – learn how to use the ManyToMany to create a many-to-many relationship in Django.

[Adding extra fields to a many-to-many relationship](https://www.pythontutorial.net/django-tutorial/django-manytomanyfield-through/) – show you how to add extra fields to a many-to-many relationship.

[Limit/Offset](https://www.pythontutorial.net/django-tutorial/django-limit-offset/) – show you how to use slicing to limit the number of objects returned by a QuerySet.

[Order_by](https://www.pythontutorial.net/django-tutorial/django-order_by/) – learn how to use the order_by() method to sort the result returned by a QuerySet. (`ORDER BY`)

[Startswith, endswith, and contains](https://www.pythontutorial.net/django-tutorial/django-orm-like/) – sort data based on pattern matching in Django using the `startswith`, `endswith`, and `contains` (`LIKE`).

[In](https://www.pythontutorial.net/django-tutorial/django-orm-in/) – check if a value is in a list of values (`IN`).

[Range](https://www.pythontutorial.net/django-tutorial/django-range/) – use Django range to check if a value is in a range inclusively (`BETWEEN`).

[Isnull](https://www.pythontutorial.net/django-tutorial/django-isnull/) – check if a value is NULL or not using `isnull` and query data accordingly (`IS NULL`).

[Exists](https://www.pythontutorial.net/django-tutorial/django-exists/) – return `True` if a query contains any object or `False` otherwise.

[Aggregate](https://www.pythontutorial.net/django-tutorial/django-aggregate/) – show you how to perform aggregate functions like avg, count, max, min, and sum.

[Group By](https://www.pythontutorial.net/django-tutorial/django-group-by/) – group objects into groups



## 섹션 4. Django REST Framework를 사용하여 API 구축

이 섹션에서는 Django 애플리케이션에서 REST API를 구축하는 방법을 단계별로 학습합니다.

[
Building an API using Django](https://www.pythontutorial.net/django-tutorial/django-rest-api/) – show you how to use pure Django to build an API.

[Introduction to the Django REST Framework (DRF)](https://www.pythontutorial.net/django-tutorial/django-rest-framework/) – learn how to use the DFR to quickly build an API.

[Permissions](https://www.pythontutorial.net/django-tutorial/django-rest-framework-permissions/) – guide you on how to set up permissions for the API.

[JWT Authentication](https://www.pythontutorial.net/django-tutorial/django-rest-framework-jwt/) – introduce the JSON Web Token and show you how to integrate JWT authentication in DRF.

[Paginations](https://www.pythontutorial.net/django-tutorial/django-rest-framework-pagination/) – show you how to set up pagination for datasets returned by API.





## 섹션 5. Django 쿠키 및 세션

이 섹션에서는 브라우저에서 쿠키가 작동하는 방식과 Django에서 쿠키를 설정하고 읽는 방법을 알아봅니다. 그런 다음 Django에서 세션을 지원하기 위해 쿠키를 사용하는 방법을 살펴봅니다.

[
Cookies ](https://www.pythontutorial.net/django-tutorial/django-cookies/)– learn about cookies and how to manage them in Django.

[Sessions](https://www.pythontutorial.net/django-tutorial/django-sessions/) – explain how the Django sessions work and how to use them to persist data across requests.



