# Flask 블로그 제작기

출처: https://panda5176.tistory.com/6



# Flask 블로그 제작기 (0) - 왜 Flask인가?

플라스크로 서비스되던 구버전 블로그에서 옮겨왔습니다.

------

개발자라면 모름지기 개발 블로그, devlog 하나 정도는 운영해야 제맛. 원래 [panda5176.github.io](http://panda5176.github.io/) 라는 GitHub pages에서 제공하는 정적인, 상호작용없는 블로그를 운영하다가, 회사에서 웹개발을 조금 하게 되기도 했고, 원래 블로그 디자인에 한계를 느껴서 **이 참에 아예 동적인 블로그를 운영해보기로 했습니다.**

C++를 조금 할 줄 알고 js를 배우긴 했지만, 그래도 근본적으로 python 개발자인 **나는 역시 python으로 블로그를 개발해야겠다고 생각했습니다.** 그 이유로는 몇 가지가 있는데,

1. 회사에서 python으로 웹개발을 하기 시작했기 때문에 공부에 시너지가 난다.
2. 예전에 취미로 django와 flask 등 python web framework를 배웠던 경험이 있다.
3. js는 python보다도 더 가벼운, 나쁘게 말하면 더 근본 없는 형태라 정이 안간다.
4. 예전에 node.js, 즉 요즘 가장 많이 쓰는 js backend framework를 건드려봤는데 어려웠다.
5. 나중에 데이터나 머신러닝 관련 프레임워크를 연동하기 편할 것 같다.

등이 있었습니다. Front-end에서 js를 안 쓸 수는 없겠지만, 최대한 python으로 웹을 꾸려보자는 마음에 python web framework를 다시 찾아보았고, 요즘은 **django - flask - fastapi**의 3파전인 걸로 보였습니다. 요약해본 세 가지의 장단점은 다음과 같습니다.

1. Django
   1. 장점: 백엔드를 만들어놓으면 관리자페이지까지 제공한다. 즉 직접 해야되는게 적다. 사람들이 많이 사용하고 있다.
   2. 단점: 제공되는 관리자페이지까지 관리해야한다. 나같은 미니멀리스트가 보기에 너무 과하다. 셋 중 제일 느리다.
2. Flask
   1. 장점: 밑바닥부터 웹을 짤 수 있다. 미니멀 api부터 설계 가능하다.
   2. 단점: 밑바닥부터 웹을 짜야 한다. 다른 framework에서는 당연히 제공하는 기능도 제공하지 않을 수 있다.
3. FastAPI
   1. 장점: 제일 최근에 나와서 가장 빠르고 유저 친화적이다. 셋 중 가장 빠르고, 비동기 프로그래밍을 지원한다.
   2. 단점: 제일 최근에 나와서 공부할 수 있는 방법이 별로 없다. 한국에는 나온 책도 별로 없고, 기능을 추가하려면 영어로 된 api들을 뒤져봐야한다.

그래서 저는 플라스크로 정했습니다. 일단 미니멀리스트라 관리자페이지를 주는 django는 부담스러워서 뺐고, fastapi는 탐나긴 하지만 웹에 대한 기본 지식도 부족하다고 자평하므로 한글로 설명된 가이드들이 있는 flask로 정했습니다.

물론 블로그, 블로그니까 블로그 만들기 튜토리얼부터 다시 시작했습니다. 플라스크 공식 튜토리얼을 예전에 한 번 해봤지만, 2.0 버전이 나오고 나서 다시 보니 또 새로웠습니다. 역시 한글로 번역된 플라스크 공식 튜토리얼은 잘 없어서, 블로그를 어느정도 안정화시킨 지금, **플라스크 공식 튜토리얼부터 시작해서 이 블로그를 만들기까지 공부한 내용을 하나하나 정리해볼 생각입니다.** 다음 포스트에서 뵙겠습니다.





# Flask 블로그 제작기 (1) - 공식 튜토리얼 따라잡기

플라스크로 서비스되던 구버전 블로그에서 옮겨왔습니다.

------

사실 [플라스크 공식 튜토리얼](https://flask.palletsprojects.com/en/2.0.x/tutorial/)을 제가 하나하나 번역해가면서 정리하기보다는, 이 튜토리얼을 갓-구글번역기로 번역해서 보는게 더 편하실 겁니다ㅋㅋ 저는 튜토리얼에서 이해하기 힘든, 플라스크에서 가장 기본이 되는 개념 몇 가지만 정리해보도록 하겠습니다.

일단, 기본적으로 플라스크로 만든 웹 어플리케이션(웹사이트)은 다음과 같은 프로젝트 구조를 가집니다.

```
Root 폴더
+- instance 폴더: 어플리케이션을 실행하고 나서 채워지는, 또는 운영자에 따라 다르게 설정될 수 있는 데이터들.
|   +- config.json 또는 config.yaml 등
|   +- database 파일 (sqlite DB의 경우)
+- source code 폴더
    +- static 폴더: CSS 스타일 같은, python과 상관 없는 정적인 데이터들.
        |  +- style.css: 웹페이지의 디자인을 결정
        +- templates 폴더: python에서 사용하는 html 템플릿들.
        |  +- auth 폴더: 아래의 auth blueprint에서 사용하는 html.
        |  +- blog 폴더: 이하동문
        |  +- base.html: blueprint와 관계없이 같은 사이트의 모든 페이지에서 공통으로 쓰는 html.
    +- __init__.py: python3부터는 사용하지 않는 코드인데 flask는 쓴다. 어플리케이션을 초기화하는 용도.
        +- db.py: database의 초기화와 관련된 코드들
        +- auth.py: blueprint1. 사용자 인증과 관련된 코드들. 도메인/auth로 접속하는 페이지들.
        +- blog.py: blueprint2. 블로그 포스팅할 때 쓰는 코드들. 도메인/blog로 접속하는 페이지들.
```

구조를 크게 네 가지로 나누면 다음과 같습니다. **1) Flask 웹 어플리케이션, 2) DB, 3) 블루프린트, 4) 템플릿.**

또는 플라스크 웹 앱이 돌아가는 단계를 크게 두 가지로 나눌 수 있습니다.

1) **GET:** 웹 앱에서 DB와 연결하고 데이터를 가져와 블루프린트에 뿌려주면, 블루프린트는 템플릿을 가져와 사용자에게 보여준다.
2) **POST:** 사용자가 블루프린트에 데이터를 입력하면, 웹 앱에서 데이터를 DB에 저장한다.

여기서 제일 궁금한 것 하나만 꼽자면, **블루프린트**일 것입니다. 블루프린트는 한글로 하면 청사진이며, 하나의 웹사이트 안에서 여러 가지 다양한 형태의 세부 사이트를 구현하기 위해 나눠놓은 것이라고 생각하시면 됩니다. 참고로 이 블로그에서는 직접적으로 포스팅을 하는 blog 블루프린트와, 로그인/회원가입 등의 auth 블루프린트를 나눠두었습니다.

이 복잡한 (사실 다른 web framework에 비하면 매우 단순한) 구조에서 제일 핵심이 되는 파일 하나를 꼽자면 `__init__.py`입니다. 이 파일은 flask run이라는 간단한 실행명령어로 우리가 웹사이트를 실행할 때 직접적으로 실행되는 유일한 코드입니다. 이 코드의 대략적인 구조는 다음과 같습니다.

``` py
from flask import Flask  # Flask framework를 불러온다.
from . import auth, blog, db  # auth, blog 블루프린트와 db 모듈을 불러온다.

def create_app():  # flask run 시에 실행할 메써드.
    app = Flask(__name__, instance_relative_config=True)  # 플라스크 앱을 초기화.
    app.config.from_json("config.json")  # instance/config.json 파일에서 config를 읽어옴.

    db.init_app(app)  # db 모듈에서 정의된 함수를 실행.

    app.register_blueprint(auth.bp)  # auth, blog 블루프린트와 연결
    app.register_blueprint(blog.bp)
    app.add_url_rule("/", endpoint="index")  # 도메인으로 접속 시 index 템플릿을 호출

    return app

create_app()
```

진짜 단순합니다. 여기서 더 알아야하는 부분은 db 모듈의 `init_app()` 메써드와, 각 블루프린트의 구조밖에 없습니다.
이제 db 모듈로 가보겠습니다.

``` python
import click  # flask run 처럼, python3를 통해 실행하지 않고도 python code 내 메써드를 실행할 수 있게 하는 라이브러리.
from flask import current_app, g
from flask.cli import with_appcontext

def init_db():
    conn = get_conn()  # python을 통해 database와 connect하는 메써드. DB 종류에 따라 다르게 정의될 수 있다.
    cur = get_cur()  # python을 통해 database의 cursor를 가져오는 메써드. 이하동문.

    with current_app.open_resource("schema.sql") as sql_fh:  # schema.sql 이라는 SQL 코드를 읽어와서,
        cur.execute(sql_fh.read().decode("utf8"))  # 실행한다. DB 스키마가 담긴 코드가 실행되면서 DB가 초기화된다.
        conn.commit()

@click.command("init-db")  # flask init-db 라는 실행명령어가 아래 메써드를 실행한다. 자세히는 지금 몰라도 됨.
@with_appcontext
def init_db_command():
    init_db()
    click.echo("Initialized the database.")

def init_app(app):  # __init__.py에서 가져다쓰는 메써드.
    app.teardown_appcontext(close_conn)  # 혹시 DB가 열려있었다면 닫은 다음,
    app.cli.add_command(init_db_command)  # DB를 새로 열고 앱과 DB를 연결한다.
```

여기는 조금 복잡합니다. 웹 어플리케이션이 블로그 글이나 사용자 정보를 DB에서 가져오거나 쓰기 위해서 DB와 연결하는 파트이기 때문입니다.

MySQL이나 SQLite, PostgreSQL처럼 많은 종류의 데이터베이스가 있고, 이에 따라 조금씩 코드의 형태가 달라질 수 있겠지만, 결국 데이터베이스는 1) 초기화한 다음, 2) 열어서 앱과 연결하고, 3) 쓰거나 지우거나 업데이트합니다. 이 db.py 파일에서는 데이터베이스를 초기화한 다음 열어서 앱과 연결하는 단계를 담고 있으며, [PyMySQL](https://pypi.org/project/PyMySQL/)이나 [psycopg2](https://pypi.org/project/psycopg2/)와 익숙한 사람이라면 connection과 cursor를 어떻게 다루는지 알테니 더욱 코드를 친숙하게 받아들일 수 있었을 겁니다. 그렇지 않은 사람들을 위해 다음 포스트 중 한 번을 제가 사용중인 PostgreSQL - psycopg2 - Python의 설치 및 연결방식으로 꾸며보도록 하겠습니다.

아무튼 플라스크로 웹 어플리케이션을 실행하고, DB와 연결했으니, 그 DB에서 가져온 정보를 보여줄 블루프린트가 필요합니다. 블루프린트로 넘어가봅시다.

``` python
from flask import Blueprint, g, render_template, url_for

from .db import get_conn, get_cur

bp = Blueprint("blog", __name__, url_prefix="/blog")  # 블루프린트를 초기화한다. 도메인/blog로 접속할 수 있다.

@bp.route("/", methods=("GET",))  # 도메인/blog/로 접속 시에 메써드를 실행한다.
def index():
    cur = get_cur()  # db.py에서 정의한, DB의 커서를 가져오는 메써드.
        cur.execute(  # DB에서 포스트들을 가져오는 SQL query문.
                "SELECT p.id, title, body, created, modified, author_id, views, username "
                "FROM posts p JOIN users u ON p.author_id = u.id "
                "ORDER BY created DESC;"
        )
    posts = cur.fetchall()

    return render_template("blog/index.html", posts=posts)  # blog/index.html 템플릿에 posts 변수에 저장된 포스트들을 넘겨준다.

# 도메인/blog/create로 접속 시, 또는 해당 url에서 글을 쓰고 저장했을 시 메써드 실행.
@bp.route("/create", methods=("GET", "POST"))
def create():
    if request.method == "POST":  # POST는 단순 접속인 GET과 다르게, 해당 url에서 사용자가 DB에 저장할 값이 있을 때 실행된다.
        title = request.form["title"]  # 해당 url의 템플릿에서 전달된 값들은 request.form에 저장된다.
        body = request.form["body"]

                conn = get_conn()
                cur = get_cur()
                cur.execute(  # 가져온 값들을 DB에 저장
                        "INSERT INTO posts (title, body, author_id)"
                        " VALUES (%s, %s, %s);",
                        (title, body, g.user["id"]),
                )
                conn.commit()
                return redirect(url_for("blog.detail"))  # POST를 했을 때에는 이런 템플릿을 보여준다.

    return render_template("blog/create.html")  # GET을 했을 때에는 이런 템플릿을 보여준다.
```

정말 단순합니다. 사실 `get_cur()`에서 `cur.fetchall()` 까지는 Flask가 아니라 PyMySQL과 같은 데이터베이스 라이브러리의 몫입니다. Url에 접속하기 위해서 Flask에서 해주는 부분은 **1) 블루프린트를 초기화, 2) 특정 url로 접속 시 해당 메써드를 실행, 3) 템플릿을 렌더링하되, 우리가 주고 싶은 값을 넘겨줌**, 이렇게 세 단계 밖에 없습니다. 반대로 url에서 DB에 값을 입력할 때는 Flask에서 **1) 템플릿을 통해 값을 전달받음, 2) DB에 값을 저장, 3) 템플릿을 렌더링해서 다시 원하는 url로 돌아가기** 밖에 없습니다.

마지막으로 알아야할 것은 템플릿입니다. 템플릿은 html 파일이므로, 사실상 플라스크도 아닙니다. 플라스크가 템플릿과 소통하는 부분은 **Jinja2**라는 다른 프로그래밍언어로 적혀져있는 매우 협소한 부분 뿐입니다.

``` html
<!doctype html>
<html lang="en">
<head>
    <title>Dwarf in the Flask</title>
</head>
<body>
{% for post in posts %}
<h2>{{ post['title'] }}</h2>
<p>{{ post['body'] }}</p>
{% endfor %}
</body>
</html>
```

{% %}로 둘러싼 구문에서 조건문이나 반복문을 처리하고, {{ }}로 둘러싼 구문에서 변수를 가져오는 것이 Jinja의 기본입니다.

진짜 이것뿐입니다! 이제 플라스크를 실행하는 것만이 남았습니다. 만약 pip install flask로 플라스크를 이미 다운받았다면, 해야할 것이 몇 가지가 더 있긴 합니다ㅎㅎ

1. **환경변수 설정하는 법 익히기.** 윈도우에서 환경변수를 클릭클릭해서 설정해둬도 되고, powershell에서 `$env:FLASK_ENV='development'` 또는 linux bash에서 `export FLASK_ENV='development'` 등을 실행하거나.
2. **FLASK_APP 환경변수에 init.py에서 저장한 웹 어플리케이션 이름을 저장.** 여기서는 `Flask(__name__)`을 통해 `__init__.py`가 위치한 dwarf-in-the-flask라는 폴더의 이름을 웹 어플리케이션 이름으로 지정해두었습니다.
3. **FLASK_ENV 환경변수에 development를 할당.** 개발용 서버라는 것인데, 실제로 배포용도로 사용할 서버는 이렇게 하지 않고, 배포용 환경을 따로 설정해줘야합니다. Python은 **WSGI(Web Server Gateway Interface)**라는, python 전용 간단한 웹서버 인터페이스를 사용해서 웹과 프로그램을 연결해주는데, 플라스크에는 기본적으로 **werkzeug**라는 WSGI가 설치되어있습니다. 그러나 배포할 때는 **gunicorn**이나 **uWSGI** 등의 배포용 WSGI를 설치해줘야 웹 어플리케이션이 실행될 수 있습니다.
4. **DB설치한 다음 flask init-db 실행하기.** DB는 플라스크에서 기본 제공해주는 SQLite를 사용해도 되지만, 큰 사이트는 MySQL이나 PostgreSQL 등을 사용합니다. 자세한 내용은 다음에 따로 포스트하겠습니다. `flask init-db`를 실행하면 위의 `db.py`에 있는 `init_db()` 메써드가 실행되면서 웹 어플리케이션에 필요한 DB 스키마를 쫘르륵 설정해줄 수 있습니다.
5. **flask run 실행하고, localhost:5000 url으로 접속하기.** 기본적으로 플라스크 개발 테스트 용으로 localhost의 5000 포트를 열어주게 설정되어있습니다. 나중에 배포용으로 dwarfintheflask.xyz같은 도메인을 localhost 대신에 써줄 수도 있고, 포트를 5000이 아닌 5176 등으로 열어줄 수도 있습니다.

거듭 말하지만 자세한 내용은 플라스크 튜토리얼에서 상세히 설명해주고 있기 때문에, 여기서는 제가 공부하면서, 이 블로그를 만들면서 플라스크 튜토리얼에 상세한 설명이 부족했던 부분들 위주로 서술해보았습니다. 다음으로는 플라스크 튜토리얼에서도 설명해주지 않는 부분들, 이를테면 배포, 도메인 연결, PostgreSQL 연결, CDN, 플라스크의 다양한 3rd-party library 등에 대해 포스팅해볼 생각입니다.



# Flask 블로그 제작기 (2) - Nginx와 Gunicorn로 Flask 배포

이 Dwarf in the Flask 웹사이트를 Flask로 만든 것은 이름으로부터도 알 수 있습니다만, 이러한 웹 사이트, 다른 말로는 웹 어플리케이션은 Flask와 같은 웹 프레임워크만으로 구동되지는 않습니다. [Nginx](https://www.nginx.com/)나 [Apache](https://httpd.apache.org/)같은 웹서버는 필수적이고, Flask나 Django, FastAPI같은 python 웹 프레임워크의 경우에는 [Gunicorn](https://gunicorn.org/)이나 [uWSGI](https://uwsgi-docs.readthedocs.io/en/latest/)같은 [WSGI](http://dwarfintheflask.xyz/Web Server Gateway Interface) 서버가, 웹서버의 기능을 일부 대체해주기 위해 필요합니다.

#### 웹서버, WSGI 서버, 어플리케이션

즉 **(1) 웹서버, (2) WSGI 서버, (3) Flask 어플리케이션**이 모두 실행되어야 웹사이트를 배포할 수 있습니다. Flask 튜토리얼을 따라가면서 flask run 명령어를 통해 웹사이트를 실행시켜보긴 했지만, Flask에서 내부적으로 구동하는, Flask 개발팀이 만든 [Werkzeug](https://werkzeug.palletsprojects.com/en/2.0.x/) WSGI는 테스트용이라 조금이라도 큰 어플리케이션에서는 잘 구동되지 않습니다. 자세한 사항은 제가 웹사이트를 만들 때 참고했던 [레퍼런스](https://velog.io/@jimin_lee/Nginx와-Gunicorn-둘-중-하나만-써도-될까)를 참고해주세요.

그래서 우리는 웹서버와 WSGI 서버를 설치하고 실행한 뒤 Flask와 연결해야하는데요, **저는 웹서버로는 Nginx(엔진엑스), WSGI 서버로는 Gunicorn(유니콘)을 선택했습니다.** Nginx VS Apache의 대결에서는 Nginx의 가벼움을 선택했고, Gunicorn VS uWSGI의 대결에서는 Gunicorn의 속도가 미세하게 빠르다는 레퍼런스를 어디선가 봤기 때문에 Gunicorn을 선택했습니다. 그와 별개로, Flask는 많은 경우에 Nginx와 Gunicorn의 조합을 선택하는 경우가 많아, 참고하기에도 좋았습니다. 대표적인 예시로 이 [튜토리얼](https://www.digitalocean.com/community/tutorials/how-to-serve-flask-applications-with-gunicorn-and-nginx-on-ubuntu-18-04)이 있었는데요, 비록 영어지만 정말 상세히 설명되어있어 해당 사이트만 참조하더라도 Flask를 거의 배포할 수 있었습니다.

물론 지금 설명은 **도메인이 연결되었다는 전제** 하에 이루어졌긴 합니다. 이 웹사이트의 도메인 연결은 [이 튜토리얼](https://cocobi.tistory.com/14)을 따라 **GoDaddy에서 도메인을 사서 Route53으로 호스팅**했습니다. **도메인이 연결되지 않았다면, 도메인이 적혀있는 모든 부분을 서버의 IP로 바꾸면 됩니다.**

#### Gunicorn 세팅

pip install gunicorn을 통해 Gunicorn을 설치하고 나면, 사실 Gunicorn이 해줄 것은 별로 없습니다. 일단 이런 명령어가 있다는 것만 알고 넘어갑시다. 실행은 아래에서 따로 해줍니다. 다음과 같은 명령어인데요,

```bash
gunicorn --workers 3 --bind 0.0.0.0:5000 -m 007 "dwarf_in_the_flask:create_app()"
```

여기서 `dwarf_in_the_flask`는 Flask 어플리케이션을 실행하는 메인 모듈 `dwarf_in_the_flask.py`의 basename이고, `create_app()`은 Flask 어플리케이션을 반환하는 메인 메써드의 이름입니다. 이를`0.0.0.0:5000` IP에 실행한다는 뜻이겠죠. `-m 007`은 bitmask 007, 즉 gunicorn이 이제부터 해당 앱으로 작성하는 파일들의 권한을 chmod로 제한한다는 것이고, `--workers 3`은 프로세스를 3개만 쓴다는 뜻입니다. 조금 복잡해보이지만, **사실 Flask application을 Gunicorn이 wrapping해서 실행한다고 생각해도 무방합니다.**

#### Service 세팅

**이제 여러분의 ubuntu 서버가 실행되면 자동으로 gunicorn 명령어가 실행되도록 세팅합니다.** `/etc/systemd/system/dwarf-in-the-flask.service`와 같은 경로에 .service 파일을 만들어줍니다. 내용은 다음과 같이 작성합니다.

``` service
[Unit]
Description=Gunicorn instance to serve dwarf-in-the-flask
After=network.target  # 이 서버가 네트워크 접속의 타겟이 되었을 때,

[Service]
User=ubuntu
Group=www-data  # www-data 그룹에게 Gunicorn 커뮤니케이션 권한을 주고,
WorkingDirectory=/home/ubuntu/dwarf-in-the-flask
Environment="PATH=/home/ubuntu/miniconda3/bin/"  # 이 python 환경에서, 다음과 같은 명령어를 실행한다.
ExecStart=/home/ubuntu/miniconda3/bin/gunicorn --workers 3 --bind unix:dwarf-in-the-flask.sock -m 007 "ditf:create_app()"

[Install]
WantedBy=multi-user.target  # ubuntu 실행 시 자동실행
```

이제 systemctl 명령어를 이용해서 이 파일을 서비스로 실행시켜보겠습니다. Ubuntu 서버를 껐다 켤 때마다 자동실행될 것이고, 잘 실행되고 있는지 상태도 확인할 수 있습니다.

``` bash
sudo systemctl start dwarf-in-the-flask  # 서비스 실행
sudo systemctl enable dwarf-in-the-flask  # 서비스 자동실행가능
sudo systemctl status dwarf-in-the-flask  # 서비스 상태 확인
```

#### Nginx 세팅

**위와 같이 Gunicorn을 세팅하고 나면, 이제 Gunicorn이 외부로부터 들어오는 네트워크 접속 요청에 반응해 Flask를 건드릴 수 있습니다.** 네트워크 접속 요청을 받아와주는 Nginx를 세팅해봅시다. `/etc/nginx/sites-available/dwarf-in-the-flask`와 같은 경로에 파일을 하나 만들어줍니다.

```
server {
        listen 80 default_server;
        return 403;  # 도메인이 아닌 IP로 접속하고자 하는 시도는 차단
}
server {
        listen 80;  # 80번, 즉 HTTP 포트로 들어오는 접속에 대해,
        listen [::]:80;
        server_name dwarfintheflask.xyz www.dwarfintheflask.xyz;  # 다음과 같은 도메인 요청이라면,
        access_log /var/log/nginx/dwarf-in-the-flask-access.log;  # 이 파일에 로그를 할 것이며,

        location / {  # 프록시 정보를 소켓 형태로 전달
                include proxy_params;
                proxy_pass http://unix:/home/ubuntu/dwarf-in-the-flask/dwarf-in-the-flask.sock;
        }
}
```

`ln -s /etc/nginx/sites-available/dwarf-in-the-flask /etc/nginx/sites-enabled` 와 같이 sites-enabled 폴더에 링크를 생성해주면, **이제 해당 ubuntu 서버에 dwarfintheflask.xyz 도메인을 타고 들어오는 요청은 소켓에 저장되며, 해당 소켓을 통해 Gunicorn이 소통하게 됩니다.**

``` bash
sudo systemctl daemon-reload
sudo systemctl restart nginx
sudo systemctl status nginx
```

Nginx도 마찬가지로 systemctl로 서비스를 재시작하면, 이제 해당 도메인 접근 시 `/var/log/nginx/dwarf-in-the-flask-access.log` 로그가 쌓이면서 접속이 가능해질 것입니다.

#### 방화벽(ufw)

**접속이 불가능하다면 아마 방화벽 차단 문제일 것입니다.** 저처럼 AWS EC2를 구매하시고 접속하신 분들은, 처음에는 방화벽이 활짝 열려있을 것입니다. 당연히 열려있으니 SSH, 즉 22번 port 접속도 가능하실 거구요. 이제 방화벽을 활성화해보도록 합시다.

``` bash
sudo ufw enable
sudo ufw allow 22
```

22번 포트는 바로 열어주도록 합시다. 저는 닫혀있는 상태로 작업하다가 다시 EC2에 못들어가는 낭패를 보았습니다... 해결책은 [이 블로그 포스트](https://yvvyoon.github.io/ubuntu/ufw-enable-ssh-timeout/)에 있지만, 여러분들은 저처럼 바보짓을 하지 않도록 합시다.

그 외의 건드려야할 방화벽 설정은 다음과 같습니다. 만약 HTTP allow인데도 웹사이트 접속이 안된다면, AWS EC2 보안그룹 설정에 `0.0.0.0:80`이 열려있는지 확인해주세요.

```bash
sudo ufw status  # 방화벽 현재 상태
sudo ufw allow 5000  # gunicorn 테스트를 위한 5000번 포트
sudo ufw allow 'Nginx HTTP'  # HTTP 포트
sudo ufw allow 'Nginx HTTPS'  # HTTPS 포트, 지금은 SSL 인증이 없어 불필요합니다.
sudo ufw allow 'Nginx Full'  # HTTP + HTTPS
sudo ufw delete allow 'Nginx HTTP'  # 방화벽 허용을 다시 차단합니다.
```

웹사이트를 만드는 과정에서 가장 복잡한 부분이지만, 한 번 이해하면 은근 단순한 부분이니 여러분 웹사이트에도 예쁘게 적용해보도록 합시다!



# Flask 블로그 제작기 (3) - PostgreSQL 연결

#### 왜 PostgreSQL?

Python에서 기본적으로 사용하고 있는 DB 미들웨어는 [SQLite3](https://docs.python.org/ko/3/library/sqlite3.html)이며, Flask 튜토리얼에서도 DB는 SQLite3를 사용하고 있습니다. 다만, [이 포스트 시리즈](https://smoh.tistory.com/368?category=706280)에서도 볼 수 있듯이, **SQLite3는 서버가 아닌 파일 기반의 시스템이라, DB를 한꺼번에 여러 사용자가 사용하는 경우에 알맞지 않습니다.**

따라서 보통 [MySQL](https://www.mysql.com/)이나 [PostgreSQL](https://www.postgresql.org/)을 사용하게 되는데, MySQL이 더 오래되고 개발이력이 오래되어 튼튼한 감은 있지만, PostgreSQL은 오픈소스로서의 잠재력이 높아 요즘 사람들이 많이 사용하는 추세입니다. 이 웹사이트, **Dwarf in the Flask 또한 PostgreSQL을 사용하고 있습니다.** 사용에 참조한 튜토리얼은 [이것](https://www.digitalocean.com/community/tutorials/how-to-use-a-postgresql-database-in-a-flask-application)과 [이것](https://bbd531.tistory.com/entry/postgres-설치-및-초기-설정)입니다.

#### Postgres 명령어들

PostgreSQL 설치는 [공식 다운로드](https://www.postgresql.org/download/) 또는 sudo apt-get install postgresql 명령어를 통해 그냥 설치하면 되지만, 파일 기반으로 소통하는 SQLite와 달리 PostgreSQL은 서버를 실행하는 형식으로 DB와 소통하는 데서 어려움이 찾아옵니다.

```bash
psql -U postgres
```

처음 설치 후 postgres라는 계정이 자동 생성되며, postgres라는 database와 자동 연결되어 접속할 수 있게 됩니다. 비밀번호가 있다면 아마도 postgres일 것입니다. 접속되면 help를 입력해보도록 합시다.

```bash
postgres=# help
You are using psql, the command-line interface to PostgreSQL.
Type:  \copyright for distribution terms
       \h for help with SQL commands
       \? for help with psql commands
       \g or terminate with semicolon to execute query
       \q to quit
```

\h는 SQL 명령어들에 대한 도움말이니, 아마도 저보다 SQL에 익숙하실 여러분들께 필요한건 \?를 통해 psql 특수 명령어들에 대한 도움말일 것입니다. MySQL을 통해 show desc use 등의 명령어에 익숙하셨던 여러분은 이제 \d \l \q등에 익숙해지셔야 합니다.

웹사이트에서 사용할 유저 이름과 비밀번호를 설정해보도록 합시다.

```bash
CREATE USER username WITH PASSWORD 'password';
```

\q 명령어를 통해 서버에서 나간 다음, psql -U username으로 접속하려고하면 접속되지 않는 것을 알 수 있습니다. 이는 [PostgreSQL은 접속 시에 username과 일치하는 database로 자동접속하기 때문](https://stackoverflow.com/questions/17633422/psql-fatal-database-user-does-not-exist)이므로, 다시 psql -U postgres로 접속한 다음, createdb username으로 username과 일치하는 database를 만들어주면 접속가능해집니다.

참고로, PostgreSQL database 백업을 위한 pg_dump 명령어 예시는 다음과 같습니다.

```bash
sudo pg_dump -d db_name -h 127.0.0.1 -U username -p port > backup.sql
```

#### Psycopg2 어댑터

PostgreSQL 서버와 소통하려면, python에서 서버와 소통하는 [Psycopg2](https://www.psycopg.org/docs/) 데이터베이스 어댑터를 사용해야합니다.

```bash
pip install psycopg2
```

설치가 완료되면, DB와 소통하는 파일, 예를 들면 db.py같은 파일로 들어가서 SQLite가 점유하고 있던 코드를 모두 Psycopg2를 사용하는 코드로 바꿔주도록 합시다.

```python
from flask import current_app, g
import psycopg2, psycopg2.extras

def get_conn():
    if "conn" not in g:
        db_config = current_app.config["DATABASE"]
        g.conn = psycopg2.connect(
            host=db_config["HOST"],
            user=db_config["USER"],
            password=db_config["PASSWORD"],
            port=db_config["PORT"],
        )
    return g.conn

def get_cur():
    if "cur" not in g:
        g.cur = get_conn().cursor(cursor_factory=psycopg2.extras.DictCursor)
    return g.cur

...
```

SQLite와 PostgreSQL의 코드를 자세히 살펴보면, 3가지 정도의 차이점이 있습니다.

1. 파일을 통해 DB를 여는 SQLite와 달리, 서버를 통해 DB를 여는 **PostgreSQL은 host와 port를 설정해줘야 합니다.**
2. 동일한 이유로, **PostgreSQL은 connection과 cursor 객체를 따로 만들어 DB에 commit해야합니다.**
3. **SQL query의 formatting 시에 문법 차이가 다소 있습니다.** 특히, prepared statement를 만들 때 SQLite는 ? 물음표에 변수를 할당할 수 있지만, PostgreSQL은 할 수 없기 때문에 SQL injection 보안 공격에 취약해질 수 있습니다.



# Flask 블로그 제작기 (4) - 페이지네이션 구현

#### 페이지네이션이란?

이 블로그, Dwarf in the Flask에는 **페이지네이션(pagination)** 기능이 있습니다. 이렇게 말하면 뭔가 싶겠지만, 사실 우리에게 익숙한 기능이죠, 바로 **글 목록이 너무 길면 다음 페이지로 분리시켜주는 기능**입니다. [Bootstrap Pagination](https://getbootstrap.com/docs/5.0/components/pagination/)의 예시를 보면 더 와닿겠죠.

이 블로그는 Bootstrap도 사용하고있긴 하지만, 페이지네이션은 디자인적 측면보다는 **구현 자체가 조금 어렵습니다.** 총 25개의 포스트가 있고 한 페이지당 10개의 포스트를 보여주고싶다면, 25 / 10의 몫인 2만큼의 페이지를 만들고, 나머지인 5개의 포스트를 보여줄 페이지를 하나 더 만든 다음, 각 페이지에 대한 링크들과, 1페이지와 끝페이지에 대한 링크도 만들어줘야하죠. 물론 못할 일은 아니지만, 귀찮음을 재사용으로 극복하는 것이 소프트웨어공학을 잘 이해하고 있는 개발자의 자세입니다ㅋㅋㅋ

#### Flask-paginate

제가 [왜 Flask인가?](http://dwarfintheflask.xyz/5)라는 예전 포스트에서, FastAPI가 탐나긴 하지만 Flask로 이 블로그를 만든 이유가 바로 여러가지 공부하기 좋은 한글 레퍼런스가 많아서라고 했었죠. Flask에는 **[Flask-paginate](https://pythonhosted.org/Flask-paginate/)**라는 페이지네이션을 쉽게 구현할 수 있는 좋은 라이브러리가 있고, 제가 처음 Flask를 배웠던 점프 투 플라스크 강의에 [Flask-paginate 한글 레퍼런스](https://wikidocs.net/81054)도 있습니다. **이 블로그의 페이지네이션도, 한글 레퍼런스를 토대로 Flask-paginate 공식문서를 참조해가며 만들었습니다.**

#### HTML template

Python 코드보다는 html template부터 보는게 마음이 편하겠죠, 아래는 이 블로그에서 페이지네이션을 구현한 html 코드입니다. 댓글 표시나 저자 표시 등 기능이 몇 가지 더 있지만, 페이지네이션과 관련없는 부분은 이해를 위해 일단 제거했습니다.

```html
{% for post in posts %}
<article>
    <a href="{{ url_for('blog.detail', id=post['id'])}}">
        {{ post['title'] }}
    </a>
</article>
{% endfor %}
{{ pagination.links | safe }}
```

생각보다 html에서 구현할 부분은 많지 않죠? Flask-paginate를 사용하지 않았다면 아마 조금 더 복잡해졌을겁니다. 여기서 주목할 부분은 두 가지가 있습니다.

1. `{% for post in posts %}` 반복문: Jinja 언어로 구현된 이 반복문에서는, **Flask에서 보내준 posts를 순회하면서 post를 가져옵니다.** 만약 페이지네이션을 해주지 않았다면 posts는 몇십 개겠지만, 10개씩 페이지네이션을 해준다고 치면 posts는 항상 10개 길이일 것입니다.
2. `pagination.links`: Flask-paginate에서 **pagination 객체**를 만들어서 넘겨주면, 객체가 갖고있는 links라는 attribute가 바로 우리가 원하는 그 **각 페이지로 갈 수 있는 링크 부분**입니다. 내부적으로는 **우리가 구현하고싶은 html 덩어리를 추상화**해놓았습니다. 만약 Flask-paginate를 사용하지 않았다면 여기가 엄청 복잡해졌겠죠. 다만, **safe를 걸어주어서, 안전한 객체가 전달되었으니 브라우저 상에서 실행해도 괜찮다~** 라고 브라우저를 안심시켜줘야 html 위에 보이게 할 수 있습니다.

#### Python 구현부

그럼 저 pagination.links 하나를 만들기 위해 Flask에서는 무엇을 해줘야할까요? 아래는 제가 이 블로그에서 페이지네이션을 구현한 Python 코드입니다. 코드를 따라가보면서 Flask-paginate의 인터페이스를 확인해봅시다.

```python
from flask_paginate import Pagination, get_page_args

@BP.route("/", methods=("GET",))  # index 페이지를 호출하면
def index():
    per_page = 10
    page, _, offset = get_page_args(per_page=per_page)  # 포스트 10개씩 페이지네이션을 하겠다.
    # 이 때 두 번째 return값은 per_page입니다.
    # 저는 per_page를 따로 get_page_args에 넣어줘서, per_page를 받아서 사용하지는 않았습니다.
    # page는 현재 위치한 page입니다. 기본적으로 1이고, 페이지 링크를 누르면 2, 3, ...입니다.
    # offset은 page에 따라 몇 번째 post부터 보여줄지입니다.
    # 기본적으로 0이고, 2페이지라면 10, 3페이지라면 20이겠죠.

    cur = get_cur()
        cur.execute("SELECT COUNT(*) FROM posts;")  # 일단 총 몇 개의 포스트가 있는지를 알아야합니다.
        total = cur.fetchone()[0]
    cur.execute(
        "SELECT * FROM posts ORDER BY created "  # SQL SELECT로 포스트를 가져오되,
        "DESC LIMIT %s OFFSET %s;",  # offset부터 per_page만큼의 포스트를 가져옵니다.
        (per_page, offset),
    )
    posts = cur.fetchall()

    return render_template(
        "blog/index.html",
        posts=posts,
        pagination=Pagination(
            page=page,  # 지금 우리가 보여줄 페이지는 1 또는 2, 3, 4, ... 페이지인데,
            total=total,  # 총 몇 개의 포스트인지를 미리 알려주고,
            per_page=per_page,  # 한 페이지당 몇 개의 포스트를 보여줄지 알려주고,
            prev_label="<<",  # 전 페이지와,
            next_label=">>",  # 후 페이지로 가는 링크의 버튼 모양을 알려주고,
            format_total=True,  # 총 몇 개의 포스트 중 몇 개의 포스트를 보여주고있는지 시각화,
        ),
        search=True,  # 페이지 검색 기능을 주고,
        bs_version=5,  # Bootstrap 사용시 이를 활용할 수 있게 버전을 알려줍니다.
    )
```

이를 요약하면 다음과 같은 단계로 진행됩니다.

1. Flask에 N번째 페이지(page)를 보여달라는 요청이 들어옵니다.
2. **N번째 페이지라면 10 \* (N-1) 번째 포스트(offset)부터** 보여줘야겠죠, 예를 들면 1번 페이지면 0번 포스트, 2번 페이지면 10번 포스트처럼요. 그걸 `get_page_args()`함수로 계산해옵니다.
3. SQL로 DB에 있는 포스트를 가져올 때, 10 * (N-1) 번째 포스트에서부터 10개만큼 가져오도록 LIMIT와 OFFSET을 줍니다.
4. `render_template()`할 때, **Pagination객체**를 같이 전달해서, html에서 뭘 잘 몰라도 이 객체에서 전달해준 페이지 수라거나, 기호라거나, 내장된 html 형태 등을 잘 사용하게끔 돕습니다.

#### 약간의 커스터마이징 팁

이 때 `prev_label/next_label`은 링크의 버튼 모양으로 미리 설정된 html에서의 문자를 바꿔줄 수 있고, `format_total=True`를 설정해준 다음 html에서 `{{ pagination.info }}`라는 attribute를 사용해주면 Total 25 records, displaying 11-20과 같은 글자를 보여줄 수 있습니다. 저는 디자인이 맘에 안들어서 사용하고 있지 않지만, [Flask-paginate 공식문서](https://pythonhosted.org/Flask-paginate/)를 참조해보세요.

그리고 저는 css로 Pagination 객체에서 제공해주는 html 영역을 입맛에 맞춰 바꿔서 사용하고 있습니다. 특히 파란색을 적극적으로 사용하는 기본 디자인을, Bootstrap과 잘어울리게 검은색을 좀 사용하게끔 아래와 같이 바꾸었습니다.

```css
.pagination a { text-decoration: none; color: inherit;}
.page-item .page-link:hover { color: #6c757d; }
.page-item.active .page-link { background-color: #6c757d; border-color: #6c757d; }
.page-item.active .page-link:hover { color: white; }
```

Flask-paginate는 잘 만든 라이브러리이지만, 분명 디자인적 측면에서 추상화된 부분이 너무 많아 이를 커스터마이즈할 수 있음에도 불구하고 아쉬움이 약간 있습니다. 저는 언젠가 [Bootstrap Pagination](https://getbootstrap.com/docs/5.0/components/pagination/)을 적극적으로 활용해서 직접 Flask에서 페이지네이션을 구현해볼 생각이니, 그 내용도 업데이트되면 포스팅해보도록 하겠습니다.



# Flask 블로그 제작기 (5) - 파비콘(Favicon)

매 주 Flask로 블로그 제작에 대한 글을 쓰며 구글에 블로그를 노출시키려 애쓰고 있지만, 사실 블로그 운영을 해보신 분들은 아시겠지만 매우 귀찮습니다... 그래도 꾸준히 하나씩 올릴 때마다 구글 검색에 개선이 있음을 [구글서치콘솔](https://search.google.com/search-console)을 통해 보고 있기 때문에, 오늘 같은 날은 가벼운 마음으로 **파비콘(favicon)을 어떻게 플라스크 블로그에 띄웠는지**를 보여드리고자 합니다.

#### 파비콘이란?

**파비콘(favicon)**은 [위키백과](https://ko.wikipedia.org/wiki/파비콘)에 따르면, **favorites와 icon의 합성어**로, **웹사이트를 대표하는 아이콘**이라고 합니다. 우리가 자주 보는, 브라우저의 탭에 있는 아이콘, 예를 들면 위키백과의 "W" 모양과 같은 것이 파비콘인데요, 보통 **16 x 16 픽셀의, .ico 확장자를 갖는 작은 이미지**입니다.

#### 플라스크에 파비콘 띄우기

플라스크로 만든 웹페이지에 파비콘을 띄우는 것은 어렵지 않습니다. 저도 [플라스크 공식문서](https://flask.palletsprojects.com/en/2.1.x/patterns/favicon/)를 통해 쉽게 배울 수 있었는데요, 바로 다음과 같은 세 단계입니다.

1. 16 x 16 픽셀의 .ico 확장자 아이콘 이미지를 준비한다.
2. **플라스크 프로젝트에 static 폴더를 만들고, 여기에 이미지를 넣는다.** 아마 저처럼 플라스크 튜토리얼을 잘 따라오신 분들은 static/style.css 라는 파일이 이미 static 폴더에 있을 것입니다. static 폴더는 이미지나 스타일 등 정적 페이지를 구성하는 데에 필요한 파일들, 예를 들면 이미지나 .css 파일들을 넣어두는 곳이죠.
3. **base.html 상단부분에 아래와 같은 코드를 추가한다.** favicon.ico에는 파비콘 파일 이름을 넣는다.

```html
<link rel="shortcut icon" href="{{ url_for('static', filename='favicon.ico') }}">
```

끝입니다. 쉽죠?

html의 link 태그는 다른 사이트에 링크를 거는 a 태그와 달리, 문서에서 다른 문서로 링크를 거는 태그입니다. link 태그의 rel에 쓰이는 shortcut icon 어트리뷰트는 이 링크가 아이콘을 가리킨다는 것이고, `{{ url_for('static', filename='favicon.ico') }}`라는 주소는 플라스크 사용자라면 이제는 알 수 있는, 특정 폴더(static)의 특정 파일(favicon.ico)을 가리키는 Jinja2 구문이죠.

#### 추가) 그럼 파비콘은 어떻게 만들지?

그러면 대체 파비콘 .ico 그림은 어떻게 만드냐? 하면 당연히 포토샵으로 하거나, 그림판으로도 가능합니다. 저 같은 경우는 예전에 게임 만들던 때에 쓰던 도트, 또는 **픽셀 아트(pixel art) 찍는 프로그램 [Aesprite](https://www.aseprite.org/)**로 도트를 찍었습니다. 플라스크 속 난쟁이를 테라리움처럼 표현해봤는데, 좀 괜찮은가요?

한 줄 코드(와 몇 시간의 도트 노가다)면 괜찮으니, 여러분도 플라스크 웹사이트에 괜찮은 파비콘 하나 놓아보면 좋을 것 같습니다.



# Flask 블로그 제작기 (6) - 검색 기능

**검색(search)**과 **정렬(sort)**은 알고리즘계의 양대산맥입니다. 자료구조에 따라 다양한 방식으로 검색과 정렬을 구현할 수 있지만, 그 때 그 때마다 메모리적으로나 시간적으로 적합한 알고리즘은 따로 있죠. 이 블로그와 같은 플라스크 블로그에서도 1) **직접 검색 알고리즘을 데이터베이스 상대로 구현**하거나, 2) 처음부터 **[Elasticsearch](https://www.elastic.co/kr/elasticsearch/)같은 검색 전문 데이터베이스**에 데이터를 저장하는 등 복잡한 방법들이 있겠지만, 사실 가장 쉬운 방법은 **RDBMS에 LIKE SQL 쿼리를 날리는 방법**입니다. 한 번 쉽게 구현해보도록 합시다.

#### HTML view

일단 html 부분입니다. 저는 **블로그 랜딩 페이지에서만 검색 기능이 있으면 충분**하다고 생각해서 templates/blog/index.html에만 검색 기능을 구현했지만, 모든 페이지에서 필요하다고 생각되면 templates/base.html에 구현한 다음 템플릿 상속하게끔 하면 됩니다.

``` html
<!-- templates/blog/index.html -->
{% extends 'base.html' %}
{% block header %} <!-- 검색 기능을 헤더에 넣겠습니다 -->
<form method="post">
        <small class="text-muted text-nowrap mx-2">
                총 {{ total }}개 <!-- 검색 결과가 몇 개인지 출력 -->
        </small>
        <!-- 검색창 -->
        <input name="query" id="query" value="{{ request.form['query'] }}"
                placeholder="검색" class="form-control form-control-sm" required>
        <button type="submit" class="btn"></button> <!-- 검색버튼 -->
</form>
{% endblock %}
{% block content %}
{% for post in posts %}
<article class="my-2">
        <h4 class="my-0">
                {{ post['title'] }} <!-- 검색 결과 포스트 제목들 -->
        </h4>
</article>
{% endfor %}
{% endblock %}
```

1. `<form method="post">` 태그를 걸어 POST HTTP request를 웹서버에 보낼 수 있게끔 합니다.
2. `<small>` 태그 안에는 검색 결과 포스트가 총 몇 개인지를 나타낼 수 있게끔 `{{ total }}`이라는 변수를 jinja로 보여줍니다.
3. `<input>` 태그는 검색할 내용을 입력할 수 있는 입력칸입니다. "query"라는 이름으로 플라스크에 전달할 것이고, placeholder는 "검색"으로 검색할 내용을 입력할 수 있게 사용자를 가이드해줍니다. 그리고 required로 내용 없이는 버튼을 누를 수 없게 강제합니다.
4. `<button>` 태그는 `<form>`의 내용이 다 작성되었을 경우 이를 플라스크에 보내주는 역할을 합니다.

#### Flask controller

중요한 지점은 이제 랜딩 페이지가 GET뿐만 아니라 **POST HTTP request**도 받는다는 점입니다. GET request에 해당할 때, 즉 사용자가 블로그를 처음 봤을 때에는 모든 포스트를 보여주고, POST request에 해당할 때, 즉 사용자가 검색을 했을 때에는 검색 결과만 보여주게끔 SQL 쿼리를 날려주어야 합니다.

```python
from .db import get_conn, get_cur

BP = Blueprint("blog", __name__)

@BP.route("/", methods=("GET", "POST"))
def index():
    cur = get_cur()  # DB와 연결
    if request.method == "POST":  # POST request라면,
        query = request.form["query"]  # "query" 입력칸에서 받은 글자
        query_for_like = ("%" + query + "%").lower()  # 검색 편의를 위해 소문자로 변환
        cur.execute(
            "SELECT COUNT(*) FROM posts "
            "WHERE (LOWER(title) LIKE %s) OR (LOWER(body) LIKE %s);",
            (query_for_like, query_for_like),  # 포스트 제목과 내용도 소문자 변환해서 검색
        )
        total = cur.fetchone()[0]
        cur.execute(
            "SELECT p.id, title, body, created, modified, author_id, views, username "
            "FROM posts p JOIN users u ON p.author_id = u.id "
            "WHERE (LOWER(title) LIKE %s) OR (LOWER(body) LIKE %s) "
            "ORDER BY created DESC;",  # POST라면 LIKE 검색 결과를 보여줌
            (query_for_like, query_for_like),
        )
    else: // POST가 아니라면, 즉 GET request라면,
        cur.execute("SELECT COUNT(*) FROM posts p;")
        total = cur.fetchone()[0]
        cur.execute(
            "SELECT p.id, title, body, created, modified, author_id, views, username "
            "FROM posts p JOIN users u ON p.author_id = u.id "
            "ORDER BY created DESC;",  # GET이라면 검색 없이 모든 포스트를 보여줌
            (per_page, offset),
        )
    posts = cur.fetchall()

    return render_template(  # 검색 결과를 페이지에 보여줌
        "blog/index.html", posts=posts, total=total
    )
```

POST request일 경우에 집중하자면, DB에 `"WHERE (LOWER(title) LIKE %s) OR (LOWER(body) LIKE %s);", (query_for_like, query_for_like)`와 같은 식의 SQL 쿼리를 날립니다.

예를 들어 "Python"이라는 글자를 검색했다고 하면, SQL 쿼리는 `SELECT * FROM posts WHERE (LOWER(title) LIKE %python%) OR (LOWER(body) LIKE %python%);`와 같은 형식이 됩니다. posts 테이블의 title과 body 속성을 소문자로 치환해서 둘 중 하나라도 있는 python이라는 글자가 있는 포스트를 모두 가져오겠다는 뜻이죠.
이 때 `query_for_like` 변수는 1) 대문자는 소문자로 변환, 2) 글자 앞뒤로 % 기호가 붙어있습니다. 글자 앞뒤로 미리 % 기호를 붙인 이유는 Python에서 SQL 구문을 편집할 때의 편의성 때문입니다.

#### 아직 갈 길이 멀다

하지만 이런 방식의 검색 기능은 이해가 쉬운 반면에 개선의 여지가 많습니다.

1. **속도가 느립니다.** posts 테이블의 title과 body 속성은 인덱스(index)가 걸려있지 않아 검색 속도가 매우 느리죠.
2. **속성을 한정해서 검색할 수 없습니다.** title만 한정해서, 또는 user만 한정해서 검색하려면? 따로 방법을 마련해야겠죠. 드롭다운(dropdown) 박스를 만들어서 검색 범위를 정해줘야할 것입니다.
3. **여러 검색어를 한꺼번에 검색할 수 없습니다.** 예를 들어 "파이썬 python"을 검색하고싶다면? 두 단어 사이 공백에 split()을 걸어 두 번 검색해야 할까요?
4. **비슷한 단어를 검색할 수 없습니다.** 지금은 대문자와 소문자만 교차검색할 수 있지만, 예를 들어 "pyton"을 잘못 검색해도 "python"을 검색한 결과가 나오게 하려면? 지금 구조론 힘들겠죠.

그래서 앞서 설명드린 [Elasticsearch](https://www.elastic.co/kr/elasticsearch/)같은 검색 전문 데이터베이스를 여러 검색엔진에서 사용하고있습니다. 물론 검색 전문 데이터베이스를 사용한 상용 검색엔진 API, 예를 들면 [Google Programmable Search Engine](https://programmablesearchengine.google.com/about/)같은 것을 가져와서 쓸 순 있겠죠. 하지만 블로그를 직접 만드는 백엔드 개발자라면 **직접 검색 엔진을 만들어보는 것**이 더 좋을 것 같습니다. 저도 더 노력해야겠죠ㅋㅋㅋ

마지막으로 제 블로그에는 태그 기능이나 페이지네이션 기능 등이 적용되어있지만, 그런 내용들은 일단 빼고 최대한 검색 기능만 한정해서 코드를 개편해서 보여드렸습니다. 페이지네이션이 궁금하시면 [예전 포스트: Flask 블로그 제작기 (4) - 페이지네이션 구현](https://panda5176.tistory.com/12)을 참고하시고, 태그 기능은 나중에 따로 포스트를 작성해보도록 하겠습니다. 혹시 DB 모델과 사용법에 대한 부분이 궁금하시면 역시 [예전 포스트: Flask 블로그 제작기 (3) - PostgreSQL 연결](https://panda5176.tistory.com/9)을 참고해주세요.



# Flask Microservice 구축 - Zappa로 AWS Lambda에 Flask 띄우기

### 마이크로서비스란?

**마이크로서비스**란 뭘까요? [위키백과](https://ko.wikipedia.org/wiki/마이크로서비스)를 보시면, "마이크로서비스(microservice)는 애플리케이션을 느슨하게 결합된 서비스의 모임으로 구조화하는 서비스 지향 아키텍처(SOA) 스타일의 일종인 소프트웨어 개발 기법이다." 라고 소개하고 있습니다. 기존의 애플리케이션은 하나의 프론트엔드를 보여주기 위해 DB, 웹서버, 메시지큐, ... 등의 서비스를 모두 하나의 서버 위에 띄워 만들어 왔습니다. 이와 다르게, 2010년쯤 부터는 각각의 서비스를 별도로 운영/유지보수하고, 서비스 간 통신으로 애플리케이션을 구성해나가기 시작한 것이 바로 마이크로서비스, 또는 **마이크로서비스 아키텍처(MicroService Architecture, MSA)**입니다.

요즘은 많은 기업들이 사업 초기부터 **[AWS(Amazon Web services)](https://aws.amazon.com/)**나 **[GCP(Google Cloud Platform)](https://console.cloud.google.com/)** 에서 제공하는 여러 클라우드 서비스를 연결해 MSA를 구축합니다. 클라우드 서비스는 EC2와 같은 단순 컴퓨팅 서버뿐만 아니라, **RDS/DynamoDB** 같은 SQL/NoSQL DB, **Route53** 같은 도메인 서버, **람다(Lambda)** 같은 서버리스 컴퓨팅 서비스까지 제공하므로, 회사에서 **온프레미스(on-premise)**로 서버를 여러 대 사서 마이크로서비스를 구축하는 것보다 훨씬 효율적입니다. 사업 초기에는 하나의 서버 위에 단일 서비스, 즉 **모노리스(monolith)** 구조로만 애플리케이션을 구성하다가, 사업이 커지면서 클라우드 서버 위 마이크로서비스 아키텍처로 전환해나가는 회사도 많습니다.

### 플라스크 마이크로서비스? 람다? 자파?

**[플라스크(Flask)](https://flask.palletsprojects.com/)**는 파이썬(Python) 몇 줄만으로 웹 애플리케이션을 만들 수 있는 웹 프레임워크(web framework)입니다. [장고(Django)](https://www.djangoproject.com/) 및 [패스트API(FastAPI)](https://fastapi.tiangolo.com/)와 함께 파이썬 웹 프레임워크 삼대장으로 불리며, 업계에서도 실제로 많이 사용됩니다. 저도 회사에서 많이 사용하고, 사용도 간단하기 때문에 플라스크로 마이크로서비스를 구축해보고자 했고, 클라우스 서비스는 역시 회사에서 사용하고 익숙한 AWS를 사용하고자 했습니다.

마이크로서비스의 제일 첫 단계는 무엇보다도 웹 애플리케이션을 띄워보는 것입니다. EC2처럼 온전한 컴퓨팅 서버를 대여해서 보통의 서버에서 [엔진엑스(NGINX)](https://www.nginx.com/)같은 웹 서버를 깔고, [구니콘(Gunicorn)](https://gunicorn.org/)같은 WSGI(Web Server Gateway Interface) 위에 플라스크 웹 애플리케이션 서버(Web Application Server, WAS)를 띄워도 되지만, 이렇게 설명만 들어도 귀찮습니다;; 이런 방식도 나름의 장단점이 있어 이렇게 구성하는 서비스도 많으니, 궁금하시면 [제 포스트 - Flask 블로그 제작기 (2) - Nginx와 Gunicorn로 Flask 배포](https://panda5176.tistory.com/8)를 보시고 구축해보세요!

요즘 널리 사용되는 방법은, AWS 람다와 같은 **서버리스(serverless)** 컴퓨팅 서비스를 사용하는 것입니다. 서버리스 서비스는 이름 그대로 사용자가 서버에 대해 고민할 필요 없이, 애플리케이션을 구성하는 코드만 서비스에 맡기면 애플리케이션을 띄워주고, 외부에서 애플리케이션에 접근할 때마다 적절한 서버 자원을 클라우드에서 할당해줍니다. 서비스 이름이 람다인 이유도 한 줄 짜리 람다 함수처럼 한 줄짜리 코드도 애플리케이션으로 만들어주기 때문이겠죠?

람다가 사용할 수 있는 애플리케이션 코드를 짜려면, 람다가 구성한 애플리케이션이 외부에서 들어오는 HTTP 요청을 받아서 던져주는 변수 이름인 `event`를 받는 코드를 짜야 합니다. 하지만 일반적인 플라스크 애플리케이션은 `Flask` 객체를 만들어서 해당 객체에서 직접 HTTP 요청을 받는 형식이기 때문에, 람다에서 `event`를 받으면 이를 잘 변환해서 `Flask` 객체에 HTTP 요청으로 던져주는 추가적인 코드가 필요합니다. 이를 해주는 서드파티(third-party) 프레임워크가 바로 **[자파(Zappa)](https://github.com/zappa/Zappa)**입니다. 자파는 람다와 플라스크(또는 장고) 애플리케이션 사이의 다리 역할뿐 아니라, 플라스크 애플리케이션을 람다에 대신 배포(deploy)해주는 기능도 있습니다.

저는 [gnyiii님 블로그](https://velog.io/@gnyiii/Python-Zappa를-이용한-AWS-Lambda에-flask-Webapp-서버리스-구현하기)와 [자파 GitHub](https://github.com/zappa/Zappa) 설명을 참고해서 플라스크 애플리케이션을 성공적으로 람다에 배포할 수 있었습니다.

### 그럼 해보자! 일단 가상환경 설치부터

자파는 파이썬 가상환경(virtual environment)를 만들어 그 가상환경 째로 람다에 배포하므로, **가상환경 구성이 무엇보다 중요**합니다(저도 여기서 많이 헤맸습니다). 공식 설명에는 [pyenv](https://github.com/pyenv/pyenv) 로 가상환경을 만들지만, 저는 익숙하고 가벼운 **[virtualenv](https://virtualenv.pypa.io/)** 로 만들었습니다. [콘다(conda)](https://docs.conda.io/)나 [pipenv](https://pipenv.pypa.io/)도 가능하다고는 하는데... 저는 처음에는 콘다로 하려다가 피를 봤습니다ㅠ [pip(package installer for python)](https://pip.pypa.io/)는 파이썬 사용자라면 다들 있다고 생각하고 진행하겠습니다.

```
$ pip3 install virtualenv  # virtualenv 라이브러리 설치
$ virtualenv venv  # venv라는 이름의 가상환경 생성
$ source venv/Scripts/activate  # 가상환경 실행
```

이제 `venv`라는 이름의, 여러분의 컴퓨터 기본적인 운영체제(Operating System, OS) 위에 별도의 가상환경이 생겼습니다(종료는 `deactivate`라고 입력하면 됩니다). `venv/Scripts/`에 여러분의 파이썬과 pip가 복사되어 들어갔기 때문에, 가상환경 위에서 설치되는 모든 pip 라이브러리는 여러분의 원래 pip 라이브러리와 독립하여 실행됩니다. 따라서 플라스크와 자파가 이미 설치되어있더라도 다시 설치해야합니다.

```
$ pip3 install Flask
$ pip3 install zappa
```

+) 자파 설치 도중에 `cp949 codec ...` 어쩌구 에러가 나시는 분들은, 자파에 필요한 카파(kappa) 라이브러리 설치 도중에 한국어 운영체제 코덱을 지원하지 않기 때문에 에러가 나는 것입니다. 다음과 같이 하시면 해결됩니다.

```
$ git clone https://github.com/garnaat/kappa -b 0.6.0 # 카파 GitHub에서 직접 소스를 받습니다
$ cd kappa
$ # setup.py의 return open(os.path.join(os.path.dirname(__file__), fname)) 줄을
$ # return open(os.path.join(os.path.dirname(__file__), fname), encoding='utf-8')로 변경
$ # 이제 카파는 운영체제가 한국어 코덱이어도 한국어로 읽지 않습니다
$ pip install .  # 직접 카파를 설치합니다
$ cd ..
$ pip install zappa # 자파를 다시 설치합니다
```

### 세상 간단한 플라스크 웹 애플리케이션 제작

플라스크는 정말 간단하게 웹 애플리케이션을 만들 수 있습니다. `flask`라는 프로젝트 이름을 한 빈 폴더를 하나 만들고, `flask.py`(이름이 달라도 됩니다) 라는 이름을 한 파일을 만든 다음 아래와 같이 작성해줍니다.

```
from flask import Flask

app = Flask(__name__)  # 파일 이름과 같은 Flask 앱 객체를 만듭니다

@app.route("/")  # "/" 경로로 들어오면 이 함수를 마주칩니다
def hello():
    return "Hello, World!"  # "/" 경로로 들어오면 "Hello, World!"를 출력합니다
```

이제 플라스크 웹 애플리케이션을 실행해봅시다.

```
$ export FLASK_APP=flask  # Flask 객체가 선언된 파일명을 앱으로 지정합니다
$ flask run
```

`127.0.0.1:5000` 또는 `localhost:5000`으로 접속하면, `Hello, World!`가 출력됩니다. 웹 브라우저가 내 컴퓨터 로컬호스트(localhost)의 5000번 포트(기본값입니다)로 접속해서 그 루트(root, `/`) 경로로 라우팅(routing)되는 `hello()` 함수를 실행합니다.

### AWS IAM 설정

이제 이 플라스크 애플리케이션을 자파를 통해 AWS 람다에 띄우고 싶은데요, 이를 위해 자파가 내 AWS 계정을 조작할 수 있도록 권한을 주어야합니다.

AWS에는 **AWS IAM(Identity and Access Management)**이라는 AWS 권한 관리 서비스가 있습니다. AWS에 회원가입 및 로그인 후, 화면 우측 상단의 내 아이디를 클릭하여 나오는 `보안 자격 증명`에 들어가 하단 `액세스 키 만들기` 에서 루트 액세스 키(root access key)를 만들어봅시다. 이 때 **`Access Key ID`**와 **`Secret Access Key`**를 발급받아 어딘가에 메모해두도록 합시다.

자파는 **AWS CLI(Command Line Interface)**를 통해 내 AWS 계정을 조작합니다. [AWS CLI를 설치](https://aws.amazon.com/ko/cli/)한 뒤, 커맨드라인에서 다음과 같이 AWS 계정 정보를 입력하면 됩니다.

```
$ aws configure
AWS Access Key ID [None]: QWER********
AWS Secret Access Key [None]: asdf********
Default region name [None]: ap-northeast-2  # 한국은 기본이 ap-northeast-2입니다
Default output format [None]: json  # 입력하지 않아도 됩니다
```

### (마침내) 자파로 람다에 플라스크 애플리케이션 배포

이제 자파는 `aws` 커맨드를 이용해 우리의 AWS 계정을 조작할 수 있습니다. 우리가 만들었던 플라스크 웹 애플리케이션을 자파를 통해 람다로 배포해봅시다.

우선 웹 애플리케이션 `flask.py`가 있는 폴더에서, 다음과 같이 간단한 설정이 필요합니다.

```
$ zappa init
...  # 1. 람다 함수의 이름에 사용될, 가상환경의 이름을 정합니다
What do you want to call this environment (default 'dev'): dev
...  # 2. 람다 함수 코드가 저장될 S3 버킷을 자동으로 생성합니다
Your Zappa deployments will need to be uploaded to a private S3 bucket.
If you don't have a bucket yet, we'll create one for you too.
What do you want to call your bucket? (default 'zappa-qwerty1234')
...  # 3. 플라스크 애플리케이션을 인식합니다
It looks like this is a Flask application.
What's the modular path to your app's function?
This will likely be something like 'your_module.app'.
We discovered: flask.app
"Where is your app's function? (default 'flask.app'): flask.app
...  # 4. ap-northeast-2 외의 다른 곳에서 서비스할지 정합니다
Would you like to deploy this application globally? (default 'n') [y/n/(p)rimary]: n
...  # 5. 설정을 만들어 저장합니다
Okay, here's your zappa_settings.json:
{
    "dev": {
        "aws_region": "ap-northeast-2",
        "app_function": "flask.app",
        "profile_name": "default",
        "project_name": "flask",
        "runtime": "python3.9",
        "s3_bucket": "zappa-qwerty1234"
    }
}
Does this look okay? (default 'y') [y/n]: y
```

여기서 몇 가지 알고 넘어가야 하는 사항이 있습니다.

1. 람다 함수의 이름은 **`{프로젝트명}-{가상환경명}`**이 됩니다. 프로젝트명은 프로젝트 폴더 이름, 여기서는 `flask`이고, 가상환경명은 지정해준 값, 여기서는 `dev`이므로, 람다 함수 이름은 `flask-dev`가 됩니다.
2. 람다 함수 코드는 [AWS S3](https://aws.amazon.com/ko/s3/) 버킷에 저장됩니다. AWS S3는 아무 파일이나 저장할 수 있는 서비스이고, 버킷은 코드를 담는 바구니입니다.
3. 자파가 플라스크 애플리케이션을 자동 인식하지만, 장고가 설치되어있을 경우 장고로 인식하지만, 그냥 아무 값이나 입력하고 넘어가도 나중에 바꿀 수 있으므로 상관없습니다. `zappa_settings.json` 파일을 열어서 위와 같은 서식으로 변경하고, `"app_function": "{파일명}.{플라스크 객체명}"`으로 적어주세요.

이제 마지막 단계입니다. 자파를 배포해봅시다!

```
$ zappa deploy dev  # 아까 설정한 가상환경 이름입니다
Downloading and Installing dependencies..
...
Uploading flask-dev-******.zip (*.*MiB)..  # 전체 가상환경이 zip 압축 업로드됩니다
...
Deploying API Gateway..  # 람다 함수를 AWS API Gateway에 연결해 URL을 자동으로 만들어줍니다
...
Deployment complete! https://q1w2e3r4t5y6.execute-api.ap-northeast-2.amazonaws.com/dev
```

주어진 `https://q1w2e3r4t5y6.execute-api.ap-northeast-2.amazonaws.com/dev`로 접속하면, `Hello, World!`가 출력되는 걸 확인할 수 있습니다. 이제 여러분의 웹 애플리케이션이 자파를 통해 AWS S3에 업로드된 뒤 AWS 람다를 통해 서비스되기 때문에, 이를 [AWS API Gateway](https://aws.amazon.com/ko/api-gateway/)로 접근할 수 있는 것입니다. AWS 콘솔을 통해 AWS 람다로 접속하면 서비스를 확인할 수 있습니다.만약 URL을 받지 못했거나 제대로 출력되지 않았다면 가상환경 설정 문제일 것이므로, 제일 위의 가상환경 셋팅부터 차근차근 진행해보도록 합시다. 저는 콘다 가상환경과 virtualenv 가상환경의 pip 라이브러리 간에 충돌이 있어서 헤맸던 경험이 있습니다.

하지만 이와 같은 업로드는 동봉된 라이브러리가 조금만 크더라도 람다의 용량 제한 250MB에 걸린다는 한계가 있습니다. 따라서 다음 시간에는 플라스크 웹 애플리케이션이 담긴 **도커(Docker)** 이미지를 자파를 통해 **AWS ECR(Elastic Container Registry)**에 업로드하여 AWS 람다로 서비스해봄으로써, 람다의 도커 이미지 용량 제한이 10GB라는 점을 이용해 라이브러리 용량 제한 문제를 극복해보도록 하겠습니다.



# Flask Microservice 구축 - Zappa로 AWS Lambda에 Flask Docker 띄우기

### 도커가 필요한 이유는?

저번 포스트에서도 말씀드렸지만, AWS 람다의 **함수 서비스 하나는 최대 250MB의 용량**을 담을 수 있습니다. 하지만 람다를 소스 코드가 아니라 **도커 이미지로 띄우게 되면, 최대 10GB 용량**의 서비스를 띄울 수 있습니다. 파이썬(Python) 웹 서비스가 종종 텐서플로우(Tensorflow)나 케라스(Keras), 파이토치(PyTorch)처럼 대규모 딥러닝 라이브러리를 동반하기 때문에, 도커 이미지를 만들어서 람다에 띄우는건 필수라고 할 수도 있겠습니다.

[**도커(Docker)**](https://www.docker.com/)는 저번 포스트에서 나왔던 가상환경(virtual environment)처럼, 운영체제 위 별도의 환경 위에서 프로그램들이 동작하게끔 하는 플랫폼입니다. 가상환경과는 다르게 운영체제 영역까지 건드리기 때문에, 아예 운영체제를 새로 깔 수 있어서 서버의 구조와 관계 없이 모든 소프트웨어를 균일한 환경에서 구동할 수 있습니다. 도커로 운영체제와 각종 프로그램을 합쳐 돌아가는 환경을 **도커 컨테이너(container)**라 하고, 이를 한 번의 실행으로 구동할 수 있게 압축시켜놓은 일종의 원터치 캡처본을 **도커 이미지(image)**라 합니다.

### 그럼 도커를 띄워봅시다

[도커를 공식 홈페이지에서 설치](https://www.docker.com/)하면, 커맨드라인에서 `docker`라는 명령어로 도커를 실행할 수 있습니다. **`Dockerfile`**이라는 이름의 도커파일을 만들어 그 안에 이미지 첫 실행 시 필요한 명령어들을 도커만의 문법으로 작성한 뒤, `docker build` 및 `docker run` 명령어를 실행하면 도커 이미지를 만들고 실행할 수 있습니다. 첫 번째 도커파일을 앞서 만들었던 `flask` 폴더에 작성해봅시다.

```
FROM python:3.8.16  # 파이썬 이미지를 가져옵니다
WORKDIR /app  # 도커 실행 시 작업환경 경로를 설정합니다
COPY . /app  # 도커 실행 시 작업환경에 도커 외부의 현재 폴더의 파일들을 복사해둡니다
RUN pip3 install Flask  # 도커에 플라스크를 새로 설치합니다
ENV FLASK_APP flask  # 플라스크 애플리케이션을 환경변수로 지정합니다
ENTRYPOINT flask run --host 0.0.0.0  # 플라스크를 모든 호스트에 대해 실행합니다
```

처음 작성하는 도커 문법이니 특이한 것이 많습니다. 짚고 넘어가야할 점이 몇 가지 있습니다.

1. 도커 이미지는 여러 다른 이미지를 쌓아서 만들 수 있습니다. 이미지는 도커 공식 저장소인 **[도커 허브(Docker Hub)](https://hub.docker.com/)**에서 내려받습니다. 이 이미지를 다른 우리가 만들 이미지에 넣어서 배포하므로, 이미지 사용자는 어디서나 도커 이미지를 실행하면 균일한 파이썬 버전을 실행할 수 있습니다.
2. 도커 이미지 실행 시 작업환경 경로를 설정합니다. 우리가 직접 도커 이미지 안에 들어가서 실행하는 것이 아니라 정해진 순서를 따라 컨테이너가 실행되므로 작업환경 시작 위치가 필요합니다. 보통은 `/app` 위치에서 시작합니다.
3. 현재 폴더의 파일들, 즉 `flask` 폴더 위에 있는 모든 파일들을 `/app` 위로 복사합니다.
4. 가상환경과 마찬가지로 도커도 별도의 환경이므로 필요한 라이브러리를 새로 설치해야합니다.
5. 플라스크를 실행할 때 `--host`라는 파라미터가 추가되었습니다. `--host 0.0.0.0`, 즉 모든 호스트(host)에 대한 접속을 허용한다는 파라미터가 필요한 이유는, 도커의 격리된 환경에서 별도로 포트를 연 다음, 우리의 브라우저에서 특정 포트로 접속했을 때 도커의 포트로 다시 연결을 해주는 방식으로 도커 내 플라스크에 접근하기 때문입니다. 이를 **포트 포워딩(port forwarding)**이라고 합니다.

이제 도커 이미지를 만들고, 도커에 담긴 플라스크 애플리케이션을 실행해 접속해보도록 합시다.

```
$ docker build -t flask .  # 이 폴더 위 Dockerfile로 flask라는 이름의 이미지를 만듭니다
$ docker run -p 5000:5000 flask  # 도커 이미지를 실행하고, 5000번 포트와 도커 5000번 포트를 연결합니다
$ docker ps  # 현재 실행 중인 도커 컨테이너를 찾습니다
$ docker stop {컨테이너ID}  # 컨테이너ID를 입력하면 해당 컨테이너를 종료합니다
```

이 때 이미지 이름을 `flask`가 아니라 `flask:latest` 처럼 별도의 버전 태그를 달 수 있습니다(기본은 `latest`로 설정됩니다). 브라우저에서 똑같이 `127.0.0.1:5000`에 접속하면, 5000번 포트를 통해 도커 컨테이너 내부의 5000번 포트로 연결돼서 플라스크 애플리케이션에 접근할 수 있습니다. 도커 컨테이너에 임의의 컨테이너 ID가 부여되므로, 따로 커맨드라인을 통해 실행 중인 컨테이너를 종료할 수 있습니다.

### 람다에 맞게 도커파일을 고쳐야한다

하지만 우리는 람다에 이 도커를 띄워야합니다. 람다는 파이썬 코드를 실행할 뿐인데, 도커 이미지는 그럼 어떻게 실행할 수 있을까요? AWS는 **[AWS ECR(Elastic Container Registry)](https://aws.amazon.com/ko/ecr/)**이라는, 도커 이미지를 업로드해서 실행해주는 서비스를 별도로 마련해두고 있습니다. 플라스크를 사용하는 우리들에겐 구세주나 다름없는 [**자파(Zappa)**](https://github.com/zappa/Zappa)는, 도커 이미지를 ECR에 업로드해주고 이를 람다와 연결해주는 방법 또한 갖추고 있습니다.

우선 람다 환경에 걸맞게 도커파일을 수정해봅시다.

```
FROM amazon/aws-lambda-python:3.8  # 람다에서 제공하는 이미지로 바꿔주면 더 간단합니다
ARG FUNCTION_DIR="/var/task/"  # 람다는 /var/task를 작업환경으로 사용합니다
COPY ./ ${FUNCTION_DIR}  # 똑같이 작업환경에 모든 코드를 복사해줍니다
RUN pip3 install flask  # 똑같이 플라스크를 설치합니다
RUN pip3 install zappa  # 자파도 설치해줍니다
RUN ZAPPA_HANDLER_PATH=$( \  # 자파의 핸들러를 작업환경으로 빼주어야 람다가 인식해줍니다
    python -c "from zappa import handler; print (handler.__file__)" \
    ) \
    && echo $ZAPPA_HANDLER_PATH \
    && cp $ZAPPA_HANDLER_PATH ${FUNCTION_DIR}
CMD [ "handler.lambda_handler" ]  # 플라스크 대신 이 핸들러를 실행해줍니다
```

조금 달라진 점이 몇 가지 있습니다.

1. 파이썬 이미지를 람다에서 제공하는 공식 이미지로 바꾸었습니다. 다른 파이썬 이미지를 사용하면 작업환경 경로를 맞춰주는 등 복잡한 작업이 더 필요합니다.
2. 람다는 임의의 작업환경을 사용하면 안되고, 도커 컨테이너 내의 `/var/task`에 있는 파일만 실행할 수 있습니다.
3. 람다가 작업환경의 자파 핸들러(handler)에 `event`를 전달하면, 핸들러가 플라스크 애플리케이션에 이를 전달해줍니다.

### 자파야 ECR에 있는 도커를 람다로 서비스해줘

이제 자파가 담긴 도커 이미지를 AWS ECR에 배포해봅시다.

```
$ zappa save-python-settings-file flask  # zappa_settings.py라는 파일이 별도로 필요하므로 만들어줍니다
$ docker build -t flask .  # 똑같이 도커 이미지를 만들어줍니다
$ aws ecr create-repository --repository-name flask  # flask라는 이름의 ECR 저장소를 만듭니다
{
    "repository": {
        ...
        "repositoryName": "flask",
        "repositoryUri": "1234567890.dkr.ecr.ap-northeast-2.amazonaws.com/flask",
        ...
    }
}
$ docker tag flask 1234567890.dkr.ecr.ap-northeast-2.amazonaws.com/flask  # 도커 태그를 ECR 저장소로 바꿉니다
$ aws ecr get-login-password | docker login --username AWS --password-stdin 1234567890.dkr.ecr.ap-northeast-2.amazonaws.com/flask
$ docker push 1234567890.dkr.ecr.ap-northeast-2.amazonaws.com/flask  # 도커가 직접 태그 URL이 가리키는 ECR에 이미지를 업로드해줍니다
```

도커가 AWS CLI(CommandLine Interface)와 소통하여 ECR에 이미지를 업로드해주었습니다. AWS 콘솔을 통해 ECR로 들어가면 이미지가 업로드되어있는 것을 확인할 수 있습니다. 이제 자파에게 람다를 통해 ECR에 있는 이미지를 배포해달라는 요청만 하면 끝입니다.

```
$ zappa deploy flask -d 1234567890.dkr.ecr.ap-northeast-2.amazonaws.com/flask:latest
```

성공하였다면, 저번 포스트와 같이 AWS API Gateway에서 배정해준 AWS 람다 서비스의 URL이 출력됩니다. 접속해서 확인해보면 `Hello, World!`가 출력될 것입니다. **우리는 파이썬 코드 한 줄 바꾸지 않고, 도커 이미지에 담은 플라스크 웹 애플리케이션을 AWS 서버리스 서비스로 만들었습니다!**

아직도 갈 길이 멉니다. DB로 사용되는 AWS RDS나 DynamoDB도 마이크로서비스로 연결해주어야하고, 이미지 파일 저장소로 사용되는 AWS S3도 연결해주어야 하고, 언제까지 AWS API Gateway에서 알려주는 URL로 접속할 수는 없으니 도메인도 AWS Route53으로 연결해주어야 합니다. 이제는 파이썬 코드를 바꿔서 출력 외의 기능이 있는 애플리케이션도 만들어나가야겠죠. 다음 포스트에서 이들 중 하나로 찾아뵙겠습니다.



