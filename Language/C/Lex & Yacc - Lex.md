# Lex & Yacc - Lex

출처: https://kukuta.tistory.com/167



## Lex 와 Yacc 의 용도

\- Lex: 패턴매칭

\- Yacc: 문법검사



크게 이렇게 볼 수 있을 것 같다.  패턴매칭이라 함은 입력된 문자가 어떤 규칙으로 나열되어 있는가?를 따져보는 것이고,  문법검사라 함은 입력이 내가 정한 규칙과 맞아떨어지는가?를 따져보는 것이다.

ex) 사랑한다 너를 나는 엄청나게 : 한국어 패턴매칭에는 맞겠으나, 문법에는 맞지않는다.



# <span style="color:blue">Lex & Yacc Tutorial</span>

출처: https://kukuta.tistory.com/167

> 들어 가기 전에..
>
> 이 문서는 Lex & Yacc를 처음 시작하려는 분께 도움이 될만한 내용입니다.
> C/C++, 정규표현식, BNF에 대한 기본적 지식을 알고 있다는 가정하에 쓰여 졌습니다.
>
> 아래에 사용된 예제 코드의 추가 정보는 아래 링크에서 확인 가능합니다.
>
> Lex & Yacc를 이용한 프로젝트 메뉴얼 : [[도구의발견\] - [idlc] 객체 직렬화(Object Serialize) 컴파일러](https://kukuta.tistory.com/243)
> Lex & Yacc를 이용한 프로젝트 소스(Github) : https://github.com/ChoiIngon/gamnet/tree/master/idlc



## <span style="color:blue">1. Lex & Yacc 의 목적</span>

- 일반적으로 Lex & Yacc는 컴파일러 또는 인터프리터를 만들기 위해 많이 사용되어지고 있다.
- Lex & Yacc 는 독립된 어플리케이션이며, <span style="color:red">Lex는 특정 패턴의 문자열 토큰을 찾는 것, Yacc는 해당 토큰들의 관계를 분석하여 구문 검사를 하는 것</span>으로 구분 되어있다.
- 사용자는 Lex와 Yacc를 적절히 이용하여 주어진 입력(stdin, file 등등)에서 특정 토큰과, 구문을 찾아 낼 수 있으며, 이를 위해서는 정규 표현식과, BNF 문법에 대해서 기본적 지식을 가지고 있어야 한다.
- 정규 표현식과, BNF를 이용하는 Lex & Yacc는 파싱에 대한 룰을 직접 작성하는 것보다 보다 나은 개발 속도 향상과 유지 보수 비용 절감에 도움을 준다.

## <span style="color:blue">2. Lex</span>

- 이 장에서 우리는 Lex 코드의 구성과, 작성 방법에 대해서 알아보도록 하겠다.
- Lex는 특정 패턴의 토큰을 찾기 위해 정규 표현식을 이용한다. 정규표현 식에 대한 자세한 설명은 [정규 표현식(Regular Expressions)](http://kukuta.tistory.com/62) 를 참고 하도록 한다.
- Lex는 <span style="color:blue">**definition section**, **rules section**, **user-subrutine section**.</span> 이렇게 세개의 섹션으로 나뉘어 지며 각 섹션은 **%%**로 구분된다.

```cpp
... definition section ...

%%

... rules section ...

%%

... user-subrutine section ...
```

### <span style="color:blue">2.1 Definition Section</span>

- Definition Section은 <span style="color:blue">literal block, start state code, definitions</span> 파트 등으로 구성된다.
- <span style="color:green">literal block</span>
  - **%{ 와 %}** 로 구분된 블록을 literal block이라고 하며, 내부에 위치한 코드들은 Lex에 의해 검사 되지 않고 그대로 결과 C파일의 초반부에 기록된다.
  - include, 변수 선언, 함수 선언등을 literal block내에 적으면 된다.
- <span style="color:green">start state code</span>
  - Lex 룰에 start state를 지정한다는 것은, 특정 상태에서만 해당 룰이 적용되라는 것을 의미하고, start state code의 정의는 definition section에서 담당한다.
  - %s STATE1 STATE2 ... 로 표현 되며, Lex의 현재 상태를 나타내는데 사용 될 수 있다.
  - Lex의 상태(state)를 변경하기 위해서는 BEGIN 매크로를 사용해야 하며, 사용 방법은 아래와 같다.
    **BEGIN STATE1**
  - 원래의 기본 상태로 되돌아가기 위해서는 BEGIN 0 혹은 BEGIN INITIAL을 지정한다. 실 사용 예는 Rules Sectiondmf 참고 하도록 한다.
- <span style="color:green">definitions</span>
  - definition 파트는 각 정규표현식에 이름을 지정하여 동일한 정규 표현식을 재작성 필요 없이 사용 할 수 있도록 한다.
  - NAME expressions과 같은 형태로 표현 될수 있으며 공백 문자로 구분된다.
  - Definition Section의 끝은 %% 마크로 표시한다.

```cpp
// Lex.l file
%{ // literal block begin

#include <stdio.h>

char _gStr[4096];
int _gInt;

%} // literal block end

%s S_COMMENT S_INT // state declaration

// definitions begin
varname [A-Za-z_][A-Za-z0-9_]*
integer [0-9]+
// definitions end
%%
```

**NOTE** - [[진리는어디에\] - 정규 표현식(Regular Expressions)](https://kukuta.tistory.com/62)



### <span style="color:blue">2.2 Rules Section</span>

- Rules Section은 지정된 토큰을 발견 하였을 때 어떻게 동작해야 하는 지에 대해서 기술한다.
- 각 룰은 토큰에 대한 정규표현식을 기술한 패턴 파트와 해당 토큰의 처리를 기술한 액션 파트로 구성된다.
- 패턴 파트와 액션 파트는 공백 문자로 구분 된다.
- Lex는 룰에 표현된 패턴의 토큰을 발견시 액션 파트에 있는 C코드를 결과 파일에 복사한다.
- Rules Section의 끝은 %% 마크로 표시 한다.

```cpp
<S_INT> { varname } {
printf("This is a Varname which is behind of the Integer\n");
BEGIN INITIAL;
}

{ integer } {
printf("This is an Integer\n");
BEGIN S_INT;
}

{ varname } {
printf("This is a Varname\n");
}

%%
```

### <span style="color:blue">2.3 User-subroutine Section</span>

- User-subroutine Section은 결과 c 파일 끝 부분 Lex 관련 코드 뒤에 복사된다.

### <span style="color:blue">2.4 Lex 주요 함수</span>

- yylex()

- - Lex에 의해서 만들어지는 스캐너는 yylex() 함수를 엔트리 포인트로 가진다.
  - yylex() 함수 리턴 후에 다시 yylex() 함수를 호출하게 되면 이전 스캔 했던 다음 부분부터 스캔하기 시작한다.



## <span style="color:blue">3. Yacc</span>

- 이 장에서는 Yacc 코드의 구성과, 작성 방법에 대해서 알아보도록 하겠다.
- <span style="color:red">Yacc의 기본 구조는 Lex와 동일하며, 다만 각 Definition Section 에서의 선택적인 선언 사항들과, Rule Section 에서의 룰을 기술하는 방법이 다르다.</span>
- Yacc는 Lex와 마찬가지로 크게 세개의 섹션으로 구성되어 있으며, 각 섹션의 구분은 %% 마크를 이용한다.

```cpp
... definition section ...

%%

... rules section ...

%%

... user-subrutine section ...
```

- <span style="color:red">Yacc는 Lex의 엔트리 포인트인 yylex() 함수를 내부적으로 호출하여 스캔된 결과물을 넘겨 받으며, 이 결과물을 토큰이라고 말한다.</span>
- 실제로 yylex() 함수가 리턴하는 것은 단순 정수 값이다. Lex와 Yacc 간의 데이터 교환에 관한 내용은 다음 장에 다루도록 하겠다.
- 각 토큰은 각자의 타입이 있을 수 있으며, 각 토큰 타입에 적절한 데이터 타입을 가질 수 있다.
- Yacc에서는 각 토큰에 맞는 데이터 타입을 자동 혹은 편하게 지정할 수 있도록 하기 위해 몇가지 도움이 되는 옵션과 방법을 제공하고 있고, 다음에서 자세히 설명하도록한다.

### <span style="color:blue">3.1 Definition Section</span>

- Yacc도 Lex와 같은 literal block을 가지고 있으며, 사용법 또한 Lex와 동일하다. literal block 내부에 있는 코드들은 Yacc에 의해 검사 되지 않으며 그대로 결과 C 파일에 복사 된다.

#### <span style="color:blue">%union</span>

- Yacc에는 다양한 토큰들이 존재 할 수 있고, 각 토큰에 관계되는 데이터를 저장하기 위해 각각에 맞는 데이터 타입을 사용해야 할 수 도 있다.
- Yacc는 union 구조체를 이용하여 토큰에 관계되는 데이터 타입을 등록하고 사용 할 수 있도록 지원하고 있으며, 기본 사용법은 아래와 같다.

```c
%union {
... field declarations ...
}
```

- field declarations 에는 Yacc에서 각 토큰들에 적용되는 모든 데이터 타입들을 기술 할 수 있다.
- field declarations 에 기술된 변수들은 union 구조체로로 변환되고, 결과 C 파일에 선언된다.
- <span style="color:red">위에서 선언된 union 구조체는 **YYSTYPE** 라는 티입으로 typedef 되어 Lex와의 통신 및 기타 토큰 데이터 저장등에 사용 될 수 있다.</span>
- %union을 사용하지 않는 경우에는 Yacc는 디폴트로 int 값을 정의 한다.

#### <span style="color:blue">%union 사용 예</span>

```c
%union {
const char* str;
Token* tok;
}
```

- 여기에서 지정된 각 타입의 이름들을 이용해 뒤에 설명되는 %token, %type에서 각 토큰을 값을 담을 변수의 타입을 지정 할 수 있다.

#### <span style="color:blue">%token</span>

- Yacc는 Lex와 통신하기 위해 여러 개의 토큰 아이디를 정의 할 수 있으며, 문법은 아래와 같다.

```
%token <str> ADDCODE VARNAME COMMENT INTEGER INCLUDE
%token CLASS CHOICE OPENB CLOSEB SEMI COLON LESS MORE COMMA OPENBRACKET CLOSEBRACKET TYPEDEF
```

- - 이렇게 선언된 토큰 아이디들은 자동으로 정수를 부여 받아 define 형태로(또는 enum 형태로) C 파일에 기록된다.
  - %token<str> 과 같이 토큰에 대해 데이터 타입을 지정 할 때는, %union 섹션에서 등록된 이름만 사용 할 수 있으며, 그렇지 않은 경우 결과 *.c 파일을 컴파일 할 때 오류가 발생하게 된다.
  - 이 토큰 아이디들은 yylex() 함수에서 리턴 값으로 사용 될 수 있으며, Yacc는 아이디에 따라 액션을 달리 지정 할 수 있다.
  - 자세한 사용예는 Yacc Rules Section 을 다루는 장에서 다시 살펴 보도록 하겠다.

#### <span style="color:blue">%type</span>

- - Yacc는 토큰외에도 토큰과 토큰의 관계를 정의하고 있는 많은 규칙들이 있을 수 있고, 이 규칙 혹은 규칙의 집합을 statement라고 한다.
  - %type은 statement의 데이터를 저장 할 수 있는 데이터 타입을 지정하는데 사용되며 문법은 %token과 비슷하다.

```
%type <tok> classStmt choiceStmt setStmt includeStmt typedefStmt
%type <tok> vartype varDeclare VarDeclares
```

- - %type 역시 %union에 기술된 데이터 타입만을 사용 할 수 있으며, 사용법 역시 %token 처럼 이름을 <, > 사이에 기술한다.
  - Yacc의 Definition Section 역시 Lex의 그것 처럼 %% 마크를 이용하여 종료 표시를 한다.

#### <span style="color:blue">Definition Section 예</span>

```c
%{ // literal block

#include "stdafx.h"
#include "lexer.h"

int bFile = 1;
char* y_str = NULL;
int _mylineno = 1;

void yyerrorEx( int nLineNo, const char *text, const char* szErrCode );

%}

%include {

#include "absyn.h"
#include "ParseTree.h"

}

%union { // union block. 여기에 기술된 타입과 변수는 union 구조체 형태로 .c 파일에 생성된다.

const char* str;
Token* tok;

}

// place any declarations here
%type <tok> Stmts
%type <tok> stmt Stmts
%type <tok> classStmt choiceStmt setStmt includeStmt typedefStmt
%type <tok> vartype varDeclare VarDeclares

%token <str> ADDCODE VARNAME COMMENT INTEGER INCLUDE
%token SET CLASS CHOICE OPENB CLOSEB SEMI COLON LESS MORE COMMA OPENBRACKET CLOSEBRACKET TYPEDEF

%%
```

### <span style="color:blue">3.2 Rules Section</span>

- Yacc의 Rules Section은 Lex의 그것과 비슷한 역할을 한다. 단, 차이점은 Lex의 Rules Section은 특정 토큰을 스캔 했을 때 지정된 액션을 실행 했지만, Yacc에서는 Lex로 부터 넘어온 각 토큰간의 적절한 관계(문법)을 발견 할 경우에만 액션을 실행한다는 것이다.
- Rules Section은 지정된 문법을 파싱 하였을 때 어떻게 동작해야 하는 지에 대해서 기술한다.
- 각 룰은 룰을 대표하는 이름과 콜론, 토큰 간의 관계를 정의하고 있는 정의 부분, 해당 문법을 파싱 했을 때 실행되는 액션, 룰의 끝을 나타내는 세미콜론으로 이루어 진다.

```c
STATEMENTNAME
: TOKEN1 TOKEN2 TOKEN3 { printf("yacc : action1 : %s, %d, %s\n", $1, $2, $3); $$ = $1; }
| TOKEN1 TOKEN3 TOKEN2 { printf("yacc : action2 : %s, %s, %d\n", $1, $2, $3); $$ = $1; }
;
```

- STATEMENTNAME 는 해당 룰의 이름을 나타낸다. 여기에서 기술된 이름 이용하여 또 다른 상위 룰의 하위으로 사용 할 수 있다.
- TOKEN1 TOKEN2 TOKEN3 은 Lex가 리턴하는 토큰 아이디들이다. 이 아이디들에 대한 정보는 Lex의 definition section에서 %s 에 정의된 값들을 참조하게 된다.
- TOKEN1 TOKEN2 TOKEN3 .. 은 각 토큰 간의 관계를 기술 한 것이다.
- <span style="color:red">**이 예에서는 TOKEN1 뒤에 TOKEN2가, TOKEN2 뒤에 TOKEN3이 나오는 경우 action1 이라고 출력하고, TOKEN3 이 먼저 나오고 TOKEN2가 나오는 경우는 action2 를 출력하도록 정의 되어 있다.**</span>
- 위의 예제에서 $$, $1, $2, ... 와 같이 앞에 $ 마크가 붙은 것들은 각 토큰의 값을 저장하고 있는 변수들을 나타내는 것이다.
- $1은 가장 첫번째 토큰인 TOKEN1을 $2는 두 번째인 TOKEN2를 의미한다. $$는 STATEMENTNAME의 데이터 타입 즉, 결과로 생성되는 non-terminal symbol의 데이터를 저장하는 변수를 나타낸다.
- Yacc는 각 $x 에 필요한 데이터 타입을 결정하기 위해, 이전에 살펴 보았던 %token, %type을 참조하게 된다. 예를 들어 %type<tok> TOKEN1 이라고 type이 정의 했다면, $1은 c 코드로 변환 될 때 Token* 타입으로 인식되며, token 역시 비슷한 과정을 가지게 된다.

#### <span style="color:blue">Rules Section 예</span>

```c
// token과 type에 대한 정의는 이전의 definition section을 살펴 볼 것
varDeclare
: vartype VARNAME ';' { ;
printf("yacc : varDecl : Ordinary type : vartype VARNAME ;\n");
}
| vartype VARNAME '[' INTEGER ']' ';' { ;
printf("yacc : varDecl : Array type : vartype VARNAME [ INTEGER ] ;\n");
}
| COMMENT {
printf("yacc : varDecl : Comment type : COMMENT\n");
};
vartype
: VARNAME { ;
TRACE("yacc:varname:%s\n", $1);
$$ = MakeVariableToken($1);
}
| VARNAME LESS VARNAME MORE { ;
TRACE("yacc:container<data_type>\n");
$$ = MakeListToken(($1, $3);
}
| VARNAME LESS VARNAME COMMA VARNAME MORE { ;
TRACE("yacc:container<data_type1,data_type2>\n");
$$ = MakeMapToken($1, $3, $5);
};
```

### <span style="color:blue">3.3 User-subrutine Section</span>

- Lex와 동일

### <span style="color:blue">3.4 Yacc 주요 함수</span>

- yyparse()
  - 파싱을 하기위해서는 이 함수를 호출한다. <span style="color:red">**yyparse()는 내부적으로 yylex() 호출**</span>하여 위에서 정의한 문법 규칙에 따라 적절한 결과 C 파일을 생성해 낸다.

## <span style="color:blue">4. Lex 와 Yacc 간의 통신</span>

- Lex와 Yacc 간의 통신에는 **yylval 이라는 미리 정의된 전역변수**가 이용된다.
- yylval은 YYSTYPE 로 typedef 된 union 구조체로써 Yacc의 Definition Section의 %union 블록에 기술된 내용을 토대로 결과 파일에 생성된다.
- Lex에서는 yylval을 이용하기 위해 Yacc의 결과 헤더 파일을 include 하여 yylval에 정의된 변수를 이용하여 토큰에 필요한 데이터를 전달한다.
- yylex()에서 특정 패턴의 토큰을 발견했다고 Yacc에게 알리기 위해서는 Lex의 definition section 의 %s 에 정의된 선언들을 이용한다.
- Yacc는 Lex에서 리턴하는 토큰 아이디를 이해하기 위해 Lex의 결과 헤더 파일을 include해야 한다.
- Lex에서 Yacc로 넘어온 토큰의 데이터들은 $$, $1, $2 같이 $x의 형태로 Yacc에게 전달 된다.

## <span style="color:blue">5. Example of Lex & Yacc</span>

- 설명에 들어가기 앞서 이전 장에서 살펴 보았던 몇가지를 짚고 넘어 가도록 하자. 이전 장에서는 Lex는 스캐너, Yacc는 파서라고 간단히 정의하고 넘어갔다. 하지만 좀 더 정확하게 말하자면, Lex & Yacc 는 스캐너 코드와 파서 코드를 만들어 내는 일종의 컴파일러다.

  lex 에 의해 생성되는 sanner를 lexer라고도 하며, yacc에서 만들어 지는 것을 parser라고 한다.

> Lexer(scanner) -------> Parser --------------> code generation
>                             ^ token            ^ (syntax tree)
>                             |                       |
>                             regular exp.    grammer

- 개략적인 순환 구조는 위와 같으며, lexer가 정규표현식을 이용하여 특정 토큰을 찾아 내면,  파서는 그 토큰들을 모아 하나의 syntax tree를 구성하는 역할을 한다.그리고 전체 syntax tree가 완성되면 사용자는 그 트리를 이용하여 다양한(자기가 원하는 어떠한 일이든) 일을 할 수 있다.

- 역자 주)  토큰의 종류 및 토큰의 순서에 따라 원하는 기능 수행 하게 하면 된다.

- 다음과 같이 TOKEN 이 들어 왔을 경우 처리 하는 예.

  ``` c
  %token	NUMBER PLUS MINUS
  
  // NUMBER PLUS NUMBER 가 들어 왔을 경우
  |	NUMBER PLUS NUMBER {
      function_plus($1, $2);
  }
  // NUMBER MINUS NUMBER 가 들어 왔을 경우
  |	NUMBER MINUS NUMBER {
      function_minus($1, $2);
  }
  
  int function_plus(int fst, int snd)
  {
      return fst + snd;
  }
  
  int function_minus(int fst, int snd)
  {
      return fst - snd;
  }
  ```

  

### <span style="color:blue">5.1 Lex Definition Section Code Sample</span>

> 1 %{
> 2 /****************************************************************************
> 3 Lexer.l
> 4 ****************************************************************************/
> 5 #include "y.tab.h"
> 6
> 7 char _addcode_string[4096];
> 8 int _addcode_pos;
> 9 %}
> 10
> 11 %s S_ADDCODE
> 12
> 13 varname [A-Za-z_][A-Za-z0-9_]*
> 14 integer [0-9]+
> 15 class "class"
> 16 choice "choice"
> 17 openb "\{"
> 18 closeb "\}"
> 19 semi ";"
> 20 addcode "%%"
> 21 newline "\n"
> 22 whitespace [ \t\n\r]
> 23 %%

- - 1~9 : %{ 와 %}로 감싸져 있는 부분을 literal block 이다. 이 안의 내용은 아무런 여과 없이 c 파일로 복사된다.
  - 11 : %s 는 start state를 기술 한다. 여기서는 S_ADDCODE 하나만을 선언했으나, 공백을 두어 여러개의 start state를 선언 할 수도 있다.
  - 13 ~ 2 : 복잡한 정규 표현식을 특정 이름과 맵핑 시켜 놓은 일종의 '정규식 선언 테이블'이다. 여기에 선언된 이름들은 rules section에서 사용 될 수 있다.
  - 위의 lex 코드를 lex 컴파일러로 컴파일 하게 되면, 헤더 파일에는 S_ADDCODE define 형태로 기록되고, c 파일에는 literal block에 있는 내용이 복사 된다. 아래는 결과 파일의 일부를 복사한 것이다 :

> file : lex.yy.c
> {{{
> \#include "y.tab.h"
> char _addcode_string[409600];
> int _addcode_pos;
> }}}
>
> file : lex.yy.h
> {{{
> \#define S_ADDCODE 1000
> }}}

※ 참고로 여기서 lex.yy.h, lex.yy.c 와 같은 결과 파일이름은 lex의 버젼, 입력 파라메터에 따라 다를 수 있다.

### <span style="color:blue">5.2 Lex Rules Section Code Sample</span>

> .. ... Definition section ...
> 23 %%
> 24
> 25 <S_ADDCODE> {
> 26 _addcode_string[_addcode_pos] = 0;
> 27 BEGIN INITIAL;
> 28 yylval.str = _addcode_string;
> 29 _addcode_pos = 0;
> 30 return ADDCODE;
> 31 }
> 32 <S_ADDCODE>.|"\n" { _addcode_string[_addcode_pos++] = (unsigned char)yytext[0]; }
> 33 { addcode } { _addcode_pos = 0; BEGIN S_ADDCODE; }
> 34 { class } { return CLASS; }
> 35 { choice } { return CHOICE; }
> 36 { openb } { return OPENB; }
> 37 { closeb } { return CLOSEB; }
> 38 { semi } { return SEMI; }
> 39 { colon } { return COLON; }
> 40 { varname } { yylval.str = strdup(yytext); return VARNAME; }
> 41 + { ; }
> 42 .|\n { printf("Something wrong!") }
> 43 %%

- - 33 : 라고 적혀 있는 패턴 부분을 보도록 하자. { ... }는 definition section에서 그 정의를 찾아 치환 하겠다라는 의미이며, 이와 같은 경우에는 "%%" 패턴이 여기에 적용된다.
  - 33 라인의 액션 파트에서 lexer의 현재 start state가 S_ADDCODE로 변경 되며, 이로써 start state가 다른 것으로 변경 되기 전까지는 앞에 <S_ADDCODE>가 붙은 조건만이 적용된다.
  - 32 : < ... > 는 스캐너가 특정 start state에 있는 경우만 뒤의 패턴을 검사한다고 했다. 이와 같은 경우 33라인에서 start state가 변경 되었으므로 이제 부터는 32라인과 25 라인의 두 조건 중에 하나가 선택되게 된다.
  - '.' 문자는 개행 문자를 제외한 모든 문자에 매칭 되는 표현 식이며, \n는 개행 문자를 의미 한다. 단, 25라인에서 %% 가 있는지 먼저 검사하고 있으므로, 결론적으로 %%를 제외한 스캔되는 모든 문자들을 _addcode_string 이라는 배열에 저장한다.
  - 25 ~ 31 : 이 조건은 다시 한번더 '%%'를 스캔하는 경우에 만족하게 된다. 액션 파트에서는 33라인에서 변경된 start state(S_ADDCODE)를 초기 상태로 돌린다(BEGIN INITIAL).
    그리고 지금 까지 저장했던 _addcode_string의 값을 yylval.str 이라는 전역 변수를 이용하여 yacc에게 전달하는 동시에 ADDCODE 를 리턴함으로써 yacc에게 어떤 토큰이 스캔 되었는지 알려 준다.
  - 33 ~ 39 : 특정한 조건 없이 위에서 패턴 파트에 기술된 문자열을 만나면 지정된 코드를 리턴하거나 해당 토큰을 무시한다.
  - <span style="color:blue">위의 예제에서 ADDCODE, CLASS 같은 리턴 코드들과, yylval 같은 변수들은 lex 코드의 그 어디를 찾아 보아도 해당 내용에 대한 설명이나 흔적은 찾을 수 없다. 이 변수와 define 매크로는 yacc 파일에 정의된 것으로써 lex의 definition section에서 include 했던 "y.tab.h" 파일에 선언되어 있다. (yacc에 의해 생성되는 헤더 파일의 이름은 각 버젼, 사용자의 입력 파라메터에 따라 다를 수 있다.)</span>

### <span style="color:blue">5.3 yacc Definition Section Code Sample</span>

- 이전 장에서 lex 코드에서 yacc 쪽을 참조한다고 말했다. 지금 부터는 yacc의 Definition section을 살펴보도록 하겠다.

> 1 %{
> 2 /****************************************************************************
> 3 Parser.y
> 4 ****************************************************************************/
> 5 #include "lexer.h"
> 6 %}
> 7
> 8 %include {
> 9 #include "ParseTree.h"
> 10 }
> 11
> 12 %union {
> 13 const char* str;
> 14 Token* tok;
> 15 }
> 16
> 17 %type <tok> Stmts
> 18 %type <tok> stmt Stmts
> 19 %type <tok> classStmt choiceStmt
> 20 %type <tok> vartype varDeclare VarDeclares
> 21 %token <str> ADDCODE VARNAME
> 22 %token CLASS CHOICE OPENB CLOSEB SEMI
> 23 %%

- 위의 코드에서 literal block (1~6 라인) 내에 있는 코드들은 yacc가 생성하는 c 파일의 처음 부분에 기록되며, 나머지는 모두 header파일에 기록 된다.
- 8~9 : <span style="color:blue">기본적인 역할은 literal block과 동일하다. 하지만 차이점은 %include 블록 안의 내용은 헤더파일의 처음에 기록되고, literal block의 내용은 c 파일의 처음에 기록된다는 것이다.</span>
- yacc의 밴더나 버젼별로 지원하는 경우도 있고, 그렇지 않은 경우가 있다. 사용하기 전에 지원 여부를 미리 확인하고 사용하여야 한다. 만일 지원하지 않는 경우라면 ParseTree.h 에 있는 타입을 쓰는 모든 파일에서 include를 각자 해야 하며, 전역 변수의 경우는 extern 과 같은 방법으로 우회 할 수도 있다.
- 12 ~ 15 : %union 블록 내의 코드는 아래와 같은 코드로 재 탄생 된다.

> union tagYYSTYPE {
>   const char* str;
>   Token* tok;
> };
>
> \#define YYSTYPE union tagYYSTYPE
>
> extern YYSTYPE YYNEAR yylval;

 

- 자동적으로 전역변수 yylval이 선언되고, lex에서 yacc로 토큰의 데이터를 yacc로 전달하기 위해 사용되어질 수 있다.
- 여기서 사용된 Token* 과 같은 자료 구조는 %include에서 추가 되는 ParseTree.h 와 같은 헤더 파일에 정의 될 수 있다.
- 17 ~ 22 : %type 과 %token들은 define 형태로 변환되어 헤더파일에 기록된다. 역시 lex에서 특정 토큰, 즉 terminal-symbol을 스캔 했다는 의미로 리턴되는 코드로 사용될 수 있다.

### <span style="color:blue">5.4 yacc rules section.</span>

> .... Definition section ....
> 23 %%
> 24 Stmts : stmt { g_pRoot->append($1); // 'g_pRoot' is a global variable declared somewhere in ParseTree.h }
> 25   | Stmts stmt { g_pRoot->append($2) }
> 26   ;
> 27 stmt : classStmt { $$ = new Token($1); }
> 28   | choiceStmt { $$ = new Token($1); }
> 29   | ADDCODE { $$ = new Token($1); }
> 30   ;
> 31 classStmt : CLASS VARNAME OPENB VarDeclares CLOSEB SEMI { $$ = new Token("class", $2, "{", $4, "}", ";"); }
> 32   | CLASS VARNAME OPENB CLOSEB SEMI { $$ = new Token("class", $2, "{", NULL, "}", ";"); }
> 33   ;
> 34 choiceStmt : CHOICE VARNAME OPENB VarDeclares CLOSEB SEMI { $$ = new Token("choice", $2, "{", $4, "}", ";"); }
> 35   ;
> 36 VarDeclares : varDeclare{ $$ = new Token($1); }
> 37   | VarDeclares varDeclare { $$->append($2); }
> 38   ;
> 39 varDeclare : vartype VARNAME SEMI { $$ = new Token($1, $2, ";"); }
> 40   | ADDCODE { $$ = new Token($1); }
> 41   | vartype VARNAME varDeclare { ; yyerrorEx(_mylineno-1,"missing ';'","C2143" ); }
> 42   ;
> 43 vartype : VARNAME { $$ = new Token($1); }
> 44 ;
> 45
> 46 %%

- 위는 축약된 형태의 rules section이다. 원래는 액션 파트에 syntax tree를 만드는 c 좀더 정확하고 복잡한 코드가 추가 되어야 하지만, 설명을 위해 간단하게 축약된 형태로 기록했다.

  그리고 아래는 설명을 위해 임의적으로 만든 유사 C++ 코드다. 아래 코드를 어떻게 스캔하고 파싱하는지 위에서 작성한 룰들이 어떻게 동작하는지 알아보도록 하자.

> 1 %%
> 2 /**
> 3 This is Just a Comment!!
> 4 */
> 5 %%
> 6
> 7 class FooQuestion {
> 8   int trackingNumber;
> 9   string question;
> 10 };
> 11
> 12 class FooAnswer {
> 13   int questionNumber;
> 14   BOOL answer;
> 15 };
> 16
> 17 choice FooProtocol {
> 18   FooQuestion msgFooQuestion;
> 19   FooAnswer msgFooAnswer;
> 20 };

- 1 ~ 5 : lexer가 이 부분을 스캔하게 되면 ADDCODE 를 리턴한다. yacc는 ADDCODE를 리턴 받는 경우, 이것이 28 라인에서의 ADDCODE인지 39 라인 varDeclare 에서의 ADDCODE인지 다음에 리턴되는 코드를 보고 판단하게 된다. 이와 같은 경우 다음 symbol로 class 가 리턴 되므로 Definition section 27 라인 stmt 룰을 적용하게 된다.
- 7 ~ 10 : stmt 룰 적용 후, CLASS가 리턴되고 그 후 VARNAME OPENB VarDeclares CLOSEB SEMI 순서대로 리턴된다.
- VarDeclares 는 36 ~ 37 라인 조건에서 재귀적으로 자신을 호출함으로써 무한 길이의 varDeclare 를 받아 들일 수 있다.
- varDeclare 는 vartype VARNAME SEMI 이며 이는 클래스 내에 선언되어 있는 멤버 변수 선언과 동일한 순서다.만일 symbol 들이 리턴 되는 순서가 틀린다 던지, 예를 들어 class 뒤에 바로 choice가 나온 경우, 존재 하지 않는 토큰이 발견되는 경우, lex나 yacc에서 적절한 에러를 발생 시킨다.
- 이렇게 FooQuestion, FooAnswer를 위한 파서 트리를 만들고, choice 에 대한 것 역시 비슷한 수순으로 파싱이 끝나면 적절한 syntax tree를 생성 할 수 있게 된다.
- 일반적으로 syntax tree를 구성하고 접근하기 위해 사용되는 포인터 같은 경우, 전역 변수로 선언되어 전체 프로그램에서 사용되는 경우가 많다. yyparse() 를 호출하는 경우를 보자면, 아래와 같이 yacc의 user sub-routine section에 기술 될 수 있다 :

> ... yacc rules section ...
> 48 %%
> 49 Token* MakeParseTree(const string& file)
> 50 {
> 51   bFile = 1;
> 52
> 53   yyin = fopen(szFilePath.c_str(), "r");
> 54   if(yyin == NULL)
> 55   {
> 56     yyerror("can't open file");
> 57     return NULL;
> 58   }
> 59   yyparse();
> 60   fclose(yyin);
> 61   return g_pRoot; // g_pRoot is a global variable declared somewhere in ParseTree.h
> 62 }

- 53 : yacc 에서 제공 하고 있는 입력 파일 포인터 yyin에 기본 stdin 대신 특정 파일의 포인터로 교체 한다.
- 59 : yyparse() 함수를 호출 하고 있다. yyparse() 함수는 파일 스트림을 다 읽을 때 까지 내부적으로 루프를 돌며, 파싱 과정에서 각 룰에 맞는 syntax가 발견 될 때 마다 해당 룰의 action part를 실행한다.
- 각 action part 에서는 Token이라는 구조체에 각자의 데이를 저장하며 최종에는 전역 변수인 g_pRoot에 syntax tree의 root를 저장하는 형태다. 여기서 g_pRoot는 임의적으로 지정한 전역변수로써 yacc에서 우리가 작성하는 어플리케이션으로 데이터를 전송하기 위해 사용되어 진다.
- yacc를 사용하는 어플리케이션은 g_pRoot를 이용하여 적적한 syntax tree에 대한 오퍼레이션을 할 수 있다.

이후 생성된 syntax tree를 어떻게 사용하는 지는 각 프로그램의 용도와 환경에 따라 여러 다양한 방법이 나올 수 있기에 본 문서에서는 여기까지 설명을 마치 마무리 하도록 하겠다.

## <span style="color:blue">6. 마치며</span>

이상 lex & yacc 의 기본 개념 부터 기초 사용 방법까지 알아보았다. 어떻게 lex&yacc를 설치하고, 빌드하는지에 대해서는 밴더마다, 버젼마다 차이가 있으므로 자신이 쓰는 lex&yacc의 문서를 참고하도록 한다.

윈도우, 리눅스에서 어떻게 빌드 하는지 다음 링크에 정리해 두었다. lex & yacc 의 컴파일 방법은 [[진리는어디에\] - Lex & Yacc 컴파일](https://kukuta.tistory.com/235) 를 참고 하도록 하자.

예제 코드 : [github.com/ChoiIngon/gamnet/tree/master/idlc](https://github.com/ChoiIngon/gamnet/tree/master/idlc)

출처: https://kukuta.tistory.com/167 [HardCore in Programming]



### - 끝 -

