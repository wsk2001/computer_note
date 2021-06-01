# Django 문서

장고에 대해 알아야할 모든것



## 첫걸음[¶](https://docs.djangoproject.com/ko/3.1/#first-steps)

Django 또는 프로그래밍이 처음이신가요? 이곳에서 시작하세요!

- **바닥부터 시작하기:** [개요](https://docs.djangoproject.com/ko/3.1/intro/overview/) | [설치](https://docs.djangoproject.com/ko/3.1/intro/install/)
- **튜토리얼:** [Part 1: 요청과 응답](https://docs.djangoproject.com/ko/3.1/intro/tutorial01/) | [Part 2: 모델과 관리자 페이지](https://docs.djangoproject.com/ko/3.1/intro/tutorial02/) | [Part 3: 뷰와 템플릿](https://docs.djangoproject.com/ko/3.1/intro/tutorial03/) | [Part 4: 폼과 기본 뷰](https://docs.djangoproject.com/ko/3.1/intro/tutorial04/) | [Part 5: 테스팅](https://docs.djangoproject.com/ko/3.1/intro/tutorial05/) | [Part 6: 정적 파일](https://docs.djangoproject.com/ko/3.1/intro/tutorial06/) | [Part 7: 관리자 페이지 커스터마이징](https://docs.djangoproject.com/ko/3.1/intro/tutorial07/)
- **심화 튜토리얼:** [재사용 가능한 앱을 작성하는 방법](https://docs.djangoproject.com/ko/3.1/intro/reusable-apps/) | [Django의 첫 패치를 작성하기](https://docs.djangoproject.com/ko/3.1/intro/contributing/)



## 도움말[¶](https://docs.djangoproject.com/ko/3.1/#getting-help)

문제가 있나요? 도와드리고 싶어요!

- [FAQ](https://docs.djangoproject.com/ko/3.1/faq/)를 참고하세요 – 가장 흔한 질문에 대한 답변들입니다.
- 특정 정보를 찾으시나요? [색인](https://docs.djangoproject.com/ko/3.1/genindex/), [모듈 목록](https://docs.djangoproject.com/ko/3.1/py-modindex/) 또는 [상세한 목차](https://docs.djangoproject.com/ko/3.1/contents/) 를 이용하세요.
- 해결법을 못 찾으셨나요? [자주 묻는 질문: 도움을 받는법](https://docs.djangoproject.com/ko/3.1/faq/help/) 에서 도움을 받는 방법과 커뮤니티에 질문하는 법을 확인하세요.
- [ticket tracker](https://code.djangoproject.com/) 를 통해 장고로 버그를 레포트 하세요



## 이 문서의 구조[¶](https://docs.djangoproject.com/ko/3.1/#how-the-documentation-is-organized)

Django는 문서가 아주 많습니다. 높은 수준에서의 개요를 알면 원하는 정보를 어떤 문서에서 찾을지 알 수 있습니다.

- [튜토리얼](https://docs.djangoproject.com/ko/3.1/intro/)은 단계별로 친절하게 웹 애플리케이션을 제작을 도와줍니다. 처음 Django를 접하거나 웹 개발에 입문 단계라면 여기서 시작하세요. 또한 하단의 《[첫걸음](https://docs.djangoproject.com/ko/3.1/#index-first-steps)》을 읽어보세요.
- [주제 안내](https://docs.djangoproject.com/ko/3.1/topics/) 주요 주제와 개념을 높은 수준에서 논의하고 유용한 배경 지식과 설명을 제공합니다.
- [레퍼런스 가이드](https://docs.djangoproject.com/ko/3.1/ref/) API와 Django의 여러 기능들에 대한 기술적 레퍼런스를 담고 있습니다. 이 가이드는 독자가 기본적인 주요 개념에 대해 이해하고 있다고 가정하고, 어떻게 동작하고 사용하는지 설명합니다.
- [How-to 가이드](https://docs.djangoproject.com/ko/3.1/howto/)는 레시피입니다. 주요 문제와 사용 사례들의 해결 방법들을 단계별로 안내해 줍니다. 여기서는 Django가 어떻가 동작하는지 알고 있다고 가정하고 튜토리얼보다 심화된 내용을 다룹니다.



## 모델 계층[¶](https://docs.djangoproject.com/ko/3.1/#the-model-layer)

Django는 웹 애플리케이션의 데이터를 구조화하고 조작하기 위한 추상적인 계층(《모델》)을 제공합니다. 더 알아보세요.

- **모델:** [모델 소개](https://docs.djangoproject.com/ko/3.1/topics/db/models/) | [필드 타입](https://docs.djangoproject.com/ko/3.1/ref/models/fields/) | [인덱스](https://docs.djangoproject.com/ko/3.1/ref/models/indexes/) | [메타 옵션](https://docs.djangoproject.com/ko/3.1/ref/models/options/) | [모델 클래스](https://docs.djangoproject.com/ko/3.1/ref/models/class/)
- **쿼리셋:** [쿼리 실행하기](https://docs.djangoproject.com/ko/3.1/topics/db/queries/) | [쿼리셋 메소드 레퍼런스](https://docs.djangoproject.com/ko/3.1/ref/models/querysets/) | [룩업 표현식](https://docs.djangoproject.com/ko/3.1/ref/models/lookups/)
- **모델 인스턴스:** [인스턴스 메소드](https://docs.djangoproject.com/ko/3.1/ref/models/instances/) | [관련 객체에 접근하기](https://docs.djangoproject.com/ko/3.1/ref/models/relations/)
- **마이그레이션:** [마이그레이션 소개](https://docs.djangoproject.com/ko/3.1/topics/migrations/) | [연산자 레퍼런스](https://docs.djangoproject.com/ko/3.1/ref/migration-operations/) | [스키마에디터](https://docs.djangoproject.com/ko/3.1/ref/schema-editor/) | [마이그레션 작성하기](https://docs.djangoproject.com/ko/3.1/howto/writing-migrations/)
- **심화:** [매니저](https://docs.djangoproject.com/ko/3.1/topics/db/managers/) | [Raw SQL](https://docs.djangoproject.com/ko/3.1/topics/db/sql/) | [트랜잭션](https://docs.djangoproject.com/ko/3.1/topics/db/transactions/) | [집계](https://docs.djangoproject.com/ko/3.1/topics/db/aggregation/) | [검색](https://docs.djangoproject.com/ko/3.1/topics/db/search/) | [맞춤 필드](https://docs.djangoproject.com/ko/3.1/howto/custom-model-fields/) | [다중 데이터베이스](https://docs.djangoproject.com/ko/3.1/topics/db/multi-db/) | [맞춤 룩업](https://docs.djangoproject.com/ko/3.1/howto/custom-lookups/) | [쿼리 표현식](https://docs.djangoproject.com/ko/3.1/ref/models/expressions/) | [조건식](https://docs.djangoproject.com/ko/3.1/ref/models/conditional-expressions/) | [데이터베이스 함수](https://docs.djangoproject.com/ko/3.1/ref/models/database-functions/)
- **그 외:** [지원되는 데이터베이스](https://docs.djangoproject.com/ko/3.1/ref/databases/) | [레거시 데이터베이스](https://docs.djangoproject.com/ko/3.1/howto/legacy-databases/) | [초기 데이터 제공](https://docs.djangoproject.com/ko/3.1/howto/initial-data/) | [데이터베이스 접근 최적화](https://docs.djangoproject.com/ko/3.1/topics/db/optimization/) | [PostgreSQL만의 기능](https://docs.djangoproject.com/ko/3.1/ref/contrib/postgres/)



## 뷰 계층[¶](https://docs.djangoproject.com/ko/3.1/#the-view-layer)

Django는 사용자의 요청을 처리하고 결과를 반환하기 위한 로직을 캡슐화한 《뷰》의 개념을 갖고 있습니다. 뷰에 대하여 알아야할 모든 것을 아래 링크에서 찾아보세요.

- **기본 사항:**:doc:입니다.URL confs <항목/http/urls> | :doc:보기 함수 | :doc : 《보기 함수 [|](https://docs.djangoproject.com/ko/3.1/#id1)/http/views | :doc:〉바로 가기〉 | :doc: 《toptics/http/shortcuts | :doc:》Decorators》 | :doc: 《Decorators | topics/http/decorators | :doc:비동기식 지원 <주제/비동기>입니다.
- **레퍼런스** [내장된 뷰](https://docs.djangoproject.com/ko/3.1/ref/views/) | [Request/response 객체](https://docs.djangoproject.com/ko/3.1/ref/request-response/) | [TemplateResponse 객체](https://docs.djangoproject.com/ko/3.1/ref/template-response/)
- **파일 업로드** [개요](https://docs.djangoproject.com/ko/3.1/topics/http/file-uploads/) | [File 객체](https://docs.djangoproject.com/ko/3.1/ref/files/file/) | [스토리지 API](https://docs.djangoproject.com/ko/3.1/ref/files/storage/) | [파일 다루기](https://docs.djangoproject.com/ko/3.1/topics/files/) | [커스텀 스토리지](https://docs.djangoproject.com/ko/3.1/howto/custom-file-storage/)
- **클래스 기반 뷰:** [개요](https://docs.djangoproject.com/ko/3.1/topics/class-based-views/) | [내장된 디스플레이 뷰](https://docs.djangoproject.com/ko/3.1/topics/class-based-views/generic-display/) | [내장된 편집 뷰](https://docs.djangoproject.com/ko/3.1/topics/class-based-views/generic-editing/) | [믹스인 사용하기](https://docs.djangoproject.com/ko/3.1/topics/class-based-views/mixins/) | [API 레퍼런스](https://docs.djangoproject.com/ko/3.1/ref/class-based-views/) | [납작한 인덱스](https://docs.djangoproject.com/ko/3.1/ref/class-based-views/flattened-index/)
- **심화:** [CSV 생성](https://docs.djangoproject.com/ko/3.1/howto/outputting-csv/) | [PDF 생성](https://docs.djangoproject.com/ko/3.1/howto/outputting-pdf/)
- **미들웨어:** [개요](https://docs.djangoproject.com/ko/3.1/topics/http/middleware/) | [내장된 미들웨어 클래스](https://docs.djangoproject.com/ko/3.1/ref/middleware/)



## 템플릿 계층[¶](https://docs.djangoproject.com/ko/3.1/#the-template-layer)

템플릿 계층은 사용자에게 표시할 정보를 표현하기 위해 디자이너에게 친숙한 문법을 제공합니다. 디자이너들이 이 문법을 사용하는 방법과 프로그래머들이 확장하기 위한 방법을 알아보세요.

- **기초:** [개요](https://docs.djangoproject.com/ko/3.1/topics/templates/)
- **디자이너:** [언어 개요](https://docs.djangoproject.com/ko/3.1/ref/templates/language/) | [빌트인 태그와 필터](https://docs.djangoproject.com/ko/3.1/ref/templates/builtins/) | [휴머나이제이션](https://docs.djangoproject.com/ko/3.1/ref/contrib/humanize/)
- **프로그래머의 경우:**:doc:입니다.템플릿 API <ref/템플릿/api> | :doc:사용자 지정 태그 및 필터<방법/사용자 지정 템플릿 태그> [|](https://docs.djangoproject.com/ko/3.1/#id1):doc:〉사용자 지정 템플릿 백엔드는 <사용 방법/사용자 지정 템플릿 백엔드>입니다.



## 폼[¶](https://docs.djangoproject.com/ko/3.1/#forms)

Django는 쉽게 폼을 만들고 폼 데이터를 다루기 위한 풍부한 프레임워크를 제공합니다.

- **기초:** [개요](https://docs.djangoproject.com/ko/3.1/topics/forms/) | [폼 API](https://docs.djangoproject.com/ko/3.1/ref/forms/api/) | [내장된 필드](https://docs.djangoproject.com/ko/3.1/ref/forms/fields/) | [내장된 위젯](https://docs.djangoproject.com/ko/3.1/ref/forms/widgets/)
- **고급:** [모델을 위한 폼](https://docs.djangoproject.com/ko/3.1/topics/forms/modelforms/) | [미디어 통합](https://docs.djangoproject.com/ko/3.1/topics/forms/media/) | [폼셋](https://docs.djangoproject.com/ko/3.1/topics/forms/formsets/) | [맞춤 유효성 검증](https://docs.djangoproject.com/ko/3.1/ref/forms/validation/)



## 개발 프로세스[¶](https://docs.djangoproject.com/ko/3.1/#the-development-process)

여러 구성 요소들과 Django 애플리케이션의 개발과 테스트를 도와줄 다양한 도구들을 알아보세요.

- **설정:** [개요](https://docs.djangoproject.com/ko/3.1/topics/settings/) | [전체 설정 목록](https://docs.djangoproject.com/ko/3.1/ref/settings/)
- **애플리케이션.** [개요](https://docs.djangoproject.com/ko/3.1/ref/applications/)
- **예외:** [개요](https://docs.djangoproject.com/ko/3.1/ref/exceptions/)
- **django-admin과 manage.py:** [개요](https://docs.djangoproject.com/ko/3.1/ref/django-admin/) | [맞춤 명령 추가하기](https://docs.djangoproject.com/ko/3.1/howto/custom-management-commands/)
- **테스팅:** [소개](https://docs.djangoproject.com/ko/3.1/topics/testing/) | [테스트를 작성하고 실행하기](https://docs.djangoproject.com/ko/3.1/topics/testing/overview/) | [내장된 테스팅 도구](https://docs.djangoproject.com/ko/3.1/topics/testing/tools/) | [심화 주제](https://docs.djangoproject.com/ko/3.1/topics/testing/advanced/)
- **배포:** [Django 배포 개요](https://docs.djangoproject.com/ko/3.1/howto/deployment/) | [WSGI 서버](https://docs.djangoproject.com/ko/3.1/howto/deployment/wsgi/) | ASGI 서버 | [이메일로 에러 코드 추적하기](https://docs.djangoproject.com/ko/3.1/howto/error-reporting/) | [배포 전 체크리스트](https://docs.djangoproject.com/ko/3.1/howto/deployment/checklist/)



## 관리자[¶](https://docs.djangoproject.com/ko/3.1/#the-admin)

Django의 가장 인기 있는 기능 중 하나인 자동적 관리 인터페이스를 이해하기 위한 모든 것들을 찾아보세요:

- [Admin 사이트](https://docs.djangoproject.com/ko/3.1/ref/contrib/admin/)
- [Admin 액션](https://docs.djangoproject.com/ko/3.1/ref/contrib/admin/actions/)
- [Admin 문서 생성기](https://docs.djangoproject.com/ko/3.1/ref/contrib/admin/admindocs/)



## 보안[¶](https://docs.djangoproject.com/ko/3.1/#security)

보안은 웹 애플리케이션 개발에서 다른 무엇보다 중요한 문제이며 Django는 다양한 보안도구와 방법들을 제공합니다.

- [보안 개요](https://docs.djangoproject.com/ko/3.1/topics/security/)
- [Django에서 알려진 보안 문제](https://docs.djangoproject.com/ko/3.1/releases/security/)
- [클릭재킹 방어](https://docs.djangoproject.com/ko/3.1/ref/clickjacking/)
- [사이트 간 요청 위조](https://docs.djangoproject.com/ko/3.1/ref/csrf/)
- [암호화 서명](https://docs.djangoproject.com/ko/3.1/topics/signing/)
- [보안 미들웨어](https://docs.djangoproject.com/ko/3.1/ref/middleware/#security-middleware)



## 국제화와 지역화[¶](https://docs.djangoproject.com/ko/3.1/#internationalization-and-localization)

Django는 강력한 국제화와 지역화 프레임워크를 제공하여, 여러 언어와 세계 여러 지역을 위한 어플리케이션 개발을 지원합니다:

- [개요](https://docs.djangoproject.com/ko/3.1/topics/i18n/) | [국제화](https://docs.djangoproject.com/ko/3.1/topics/i18n/translation/) | [지역화](https://docs.djangoproject.com/ko/3.1/topics/i18n/translation/#how-to-create-language-files) | [지역화 된 웹 UI 형식과 폼 입력](https://docs.djangoproject.com/ko/3.1/topics/i18n/formatting/)
- [표준 시간대](https://docs.djangoproject.com/ko/3.1/topics/i18n/timezones/)



## 성능과 최적화[¶](https://docs.djangoproject.com/ko/3.1/#performance-and-optimization)

코드를 더욱 빠르고 더 적은 시스템 자원을 사용하여 효율적으로 실행 할 수 있는 다양한 기법과 도구들이 있습니다.

- [성능과 최적화 개요](https://docs.djangoproject.com/ko/3.1/topics/performance/)



## 지리 프레임워크[¶](https://docs.djangoproject.com/ko/3.1/#geographic-framework)

[GeoDjango](https://docs.djangoproject.com/ko/3.1/ref/contrib/gis/)는 세계적 수준의 지리 웹 프레임워크가 되려고 합니다. 가능한 GIS 웹 애플리케이션을 쉽게 만들고, 공간 데이터의 성능을 충분히 활용하는 것이 목표입니다.



## 일반적인 웹 애플리케이션 도구[¶](https://docs.djangoproject.com/ko/3.1/#common-web-application-tools)

Django는 웹 애플리케이션 개발에 공통적으로 요구되는 다양한 도구를 제공합니다.

- **인증:** [개요](https://docs.djangoproject.com/ko/3.1/topics/auth/) | [인증 시스템 사용하기](https://docs.djangoproject.com/ko/3.1/topics/auth/default/) | [비밀번호 관리](https://docs.djangoproject.com/ko/3.1/topics/auth/passwords/) | [맞춤 인증](https://docs.djangoproject.com/ko/3.1/topics/auth/customizing/) | [API 참조](https://docs.djangoproject.com/ko/3.1/ref/contrib/auth/)
- [캐싱](https://docs.djangoproject.com/ko/3.1/topics/cache/)
- [로깅](https://docs.djangoproject.com/ko/3.1/topics/logging/)
- [이메일 보내기](https://docs.djangoproject.com/ko/3.1/topics/email/)
- [신디케이션 피드 (RSS/Atom)](https://docs.djangoproject.com/ko/3.1/ref/contrib/syndication/)
- [페이지네이션](https://docs.djangoproject.com/ko/3.1/topics/pagination/)
- [메세지 프레임워크](https://docs.djangoproject.com/ko/3.1/ref/contrib/messages/)
- [직렬화](https://docs.djangoproject.com/ko/3.1/topics/serialization/)
- [세션](https://docs.djangoproject.com/ko/3.1/topics/http/sessions/)
- [사이트맵](https://docs.djangoproject.com/ko/3.1/ref/contrib/sitemaps/)
- [정적 파일 관리](https://docs.djangoproject.com/ko/3.1/ref/contrib/staticfiles/)
- [데이터 유효성 검증](https://docs.djangoproject.com/ko/3.1/ref/validators/)



## 다른 핵심 기능들[¶](https://docs.djangoproject.com/ko/3.1/#other-core-functionalities)

Django 프레임워크의 다른 핵심 기능들에 대해 좀 더 배우기:

- [조건부 콘텐츠 처리](https://docs.djangoproject.com/ko/3.1/topics/conditional-view-processing/)
- [콘텐츠 타입과 제네릭 릴레이션](https://docs.djangoproject.com/ko/3.1/ref/contrib/contenttypes/)
- [플랫페이지](https://docs.djangoproject.com/ko/3.1/ref/contrib/flatpages/)
- [리다이렉션](https://docs.djangoproject.com/ko/3.1/ref/contrib/redirects/)
- [시그널](https://docs.djangoproject.com/ko/3.1/topics/signals/)
- [시스템 점검 프레임워크](https://docs.djangoproject.com/ko/3.1/topics/checks/)
- [sites 프레임워크](https://docs.djangoproject.com/ko/3.1/ref/contrib/sites/)
- [Django의 유니코드](https://docs.djangoproject.com/ko/3.1/ref/unicode/)



## Django 오픈 소스 프로젝트[¶](https://docs.djangoproject.com/ko/3.1/#the-django-open-source-project)

Django의 개발과정을 알아보고 어떻게 기여할 수 있는지 알아보세요.

- **커뮤니티:** [참여 방법](https://docs.djangoproject.com/ko/3.1/internals/contributing/) | [릴리스 과정](https://docs.djangoproject.com/ko/3.1/internals/release-process/) | [팀 구성](https://docs.djangoproject.com/ko/3.1/internals/organization/) | [Django 소스 코드 저장소](https://docs.djangoproject.com/ko/3.1/internals/git/) | [보안 정책](https://docs.djangoproject.com/ko/3.1/internals/security/) | [메일링 리스트](https://docs.djangoproject.com/ko/3.1/internals/mailing-lists/)
- **디자인 철학:** [개요](https://docs.djangoproject.com/ko/3.1/misc/design-philosophies/)
- **문서:** [이 문서에 대하여](https://docs.djangoproject.com/ko/3.1/internals/contributing/writing-documentation/)
- **써드파티 분포:** [개요](https://docs.djangoproject.com/ko/3.1/misc/distributions/)
- **Django의 장기 계획:** [API 안정성](https://docs.djangoproject.com/ko/3.1/misc/api-stability/) | [릴리스 노트와 업그레이드 지침](https://docs.djangoproject.com/ko/3.1/releases/) | [디프리케이션 타임라인](https://docs.djangoproject.com/ko/3.1/internals/deprecation/)