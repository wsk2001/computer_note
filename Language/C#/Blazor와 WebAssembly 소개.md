# Blazor와 WebAssembly 소개

출처: https://dev.grapecity.co.kr/bbs/board.php?bo_table=Insight&wr_id=16



### Blazor란?

Blazor는 JavaScript 대신 C#을 사용하여 대화형 웹 UI를 구축할 수 있는 새로운 프레임워크입니다. 완전히 새로운 언어를 배우지 않고 웹 개발에 활용하려는 .NET 개발자에게 탁월한 옵션입니다. 현재 Blazor를 사용하는 두 가지 방법이 있습니다. 신 클라이언트(thin client)가 있는 ASP.NET Core 서버에서 실행하거나 JavaScript 대신 WebAssembly를 사용하여 클라이언트의 웹 브라우저에서 완전히 실행하는 것입니다.



이 글에서는 Blazor와 WebAssembly를 소개하고 .NET 개발자용 Blazor 생성에 대해 학습한 다음 샘플 Blazor 애플리케이션와 구성 요소를 빌드합니다.



많은 .NET 개발자는 최근까지도 손이 닿지 않는 것처럼 보이는 꿈을 공유하고 있었습니다. 바로 어디에서나 .NET 앱을 실행할 수 있는 능력입니다! 이 꿈의 씨앗은 2001년에 .NET Framework가 1년이 되지 않았고 Mono라는 오픈 소스 프로젝트가 발표되었을 때 심어졌습니다. Ximian 프로젝트 팀은 2004년에 Mono를 출시하고 그 꿈을 Linux 플랫폼에 옮겨 왔습니다. 몇 년 후, 플로그인의 도움으로 인터넷 익스플로러에서 Silverlight가 개발자들에게 C# 및 VB.NET을 실행할 수 있게 함으로써 그 꿈은 더 커졌습니다. 꿈은 추진력을 얻었고, 다른 브라우저에 도달할 뿐만 아니라, 심지어 짧게는 모바일까지 손을 뻗었습니다.



2016년 .NET Core가 출시되면서 꿈은 현실에 가까워졌고 Windows, Linux 및 MacOS를 수용하기 위해 .NET의 범위가 확대되었습니다. 최근에는 개발자가 데스크탑, 랩탑, 태블릿, 전화기, 게임, 센서 및 클라우드에서 .NET을 실행할 수있었습니다.



그러나 한 개척자는어디에서나 .NET을 실행하려는 꿈의 길에 지속적으로 서있었습니다. : 브라우저입니다. ActiveX와 플러그인은 한 쪽으로 치워졌고 개발자들은 JavaScript라는 최신 브라우저에서 실행되는 코드를 작성해야 하는 단일의 선택권과 함께 남겨졌습니다.



JavaScript가 정말 유일한 방법일까요? 정답은: 아니오!





### 새로운 희망 : WebAssembly

몇 년 전만 해도 자바스크립트에 대한 대안이 출시되어 Chrome, Edge, Firefox 및 Opera를 포함한 최신 주요 브라우저 버전으로 구현되었습니다. 이 대안은 32비트와 64비트 플랫폼 간에운영 체제, 주요 브라우저 브랜드 내부에서 재컴파일없이 일관되게 코드를 실행하는 스택 기반 가상 머신이며 모바일 장치에서도 지원됩니다.



WebAssembly (또는 Wasm)는 바이트코드 명령어를 실행하는 샌드 박스입니다. 지시사항은 미리 정의되어 있고, 강력하게 형식화되어 있으며 JavaScript의 한계를 초월하는 일련의 작업을 포함합니다. 결과적으로 Wasm은 다양한 언어와 런타임에 적합한 컴파일러 대상입니다.



WebAssembly가 출시된 직후, 2001년에 시작된 Mono 프로젝트는 브라우저에서 성공적으로 실행되어 .NET 코드를 가져 왔습니다. 꿈은 거의 실현되었습니다.





##### 브라우저에서 .NET을 실행해야 하는 이유는 무엇입니까?

- .NET은 C#과 같은 최신 언어를 지원합니다.
- 솔루션의 대규모 .NET 생태계가 이미 존재합니다.
- .NET은 성능 향상을 위해 지속적으로 조정되었습니다.
- .NET 솔루션을 구축하기 위한 강력한 도구 모음이 있습니다.
- 프레임워크는 안정적이고 발전됐습니다.



그러나 브라우저 내에서 .NET 자체로는 그림을 완성하지 못합니다. WebAssembly는 브라우저의 DOM (Document Object Model)과 직접적으로 HTML을 렌더링하기 위해 상호 작용할 수 없으며 JavaScript를 "접착제"로 사용해야 합니다. 그리고 .NET에는 HTML용 렌더링 엔진이 포함되어 있지 않습니다.



이러한 격차를 해소하기 위해 마이크로소프트의 수석 소프트웨어 엔지니어인 Steve Sanderson은[ Blazor를 만들었습니다](https://blog.stevensanderson.com/2017/11/05/blazor-on-mono/). 꿈이 실현됐습니다!





### 풀 스택 개발에서 Blazor 사용

Blazor는 브라우저에서 .NET으로 실행되는 싱글 페이지 애플리케이션(SPA) 프레임워크입니다. Blazor는 .NET 위에 있으며 전체 스택은 아래 그림에 설명되어 있습니다.



[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/image1_edited.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)


 

Blazor는 .NET과 브라우저간 격차를 해소하기 위한 다음을 포함한 여러 서비스를 제공합니다.



- 브라우저로 전송되는 페이로드를 줄이기위한 **어셈블리 트리밍(Assemly trimming)**
- 재사용 가능한 **컴포넌트 모델(component model)**
- **디버깅(debugging)** 경험
- **의존성 주입(Dependency injection)**
- **양식 및 검증****(Forms and validation)**
- **IntelliSense** 및 **툴링****(tooling)**
- **자바스크립트 상호 운용성(JavaScript interoperability)**
- **레이아웃(Layouts)**
- **라우팅** 서비스(Routing service)
- **서버 사이드 렌더링(Server-side rendering)**
- **정적 파일 게시(Static file publishing)**
- **단위 테스트(Unit testing)**



“Blazor”라는 이름은“browser”와“Razor”에서 파생되었습니다. Razor는 Blazor가 사용자 인터페이스를 정의하고 렌더링하기 위해 사용하는 템플릿 엔진을 말합니다. Blazor는 새로운 것이지만 Razor는 그렇지 않습니다. 실제로 Razor는 2011년 처음 릴리즈된 이후로, ASP.NET MVC 개발자들에게 사용되었던 view 엔진입니다. 또한, ASP. NET Core와 Razor 클래스 라이브러리를 통해 기존 ASP.NET MVC 애플리케이션 간에 동일한 구성 요소를 공유 할 수 있습니다. [Microsoft Docs](https://docs.microsoft.com/aspnet/core/blazor?WT.mc_id=link-article-jeliknes)에서 온라인으로 전체 Blazor 설명서를 읽어보세요.





### Razor

Razor는 view를 Razor 마크업 구문, HTML와 C#의 조합으로 정의합니다. View 구성 요소와 페이지는 일반적으로 .cshtml 확장명으로 정의되는 반면 재사용 가능한 Razor 구성 요소는 .razor 확장명을 갖습니다. Razor 파일에는 HTML이 포함되어 있습니다.



코드 파일 내에서 @ 기호는 HTML에서 C# 코드로의 전환을 나타냅니다. C#이 평가되고 결과가 템플릿에 삽입됩니다. Razor는 다음과 같은 간단한 문구로 작성될 수 있습니다.



```
<h1>@DateTime.Now</h1>  
```



이것은 다음을 렌더링합니다.



[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/Picture2a.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)



또한 표현식과 루프를 포함하여 더 복잡한 명령문을 작성할 수도 있습니다. 다음 템플릿에는 배열을 초기화하는 구문과 코드가 포함되어 있습니다.



```
<ul>  
    @foreach(int num in Fibonacci)  
    {  
        <li>@num * 2 = @(num*2)</li>  
    }  
</ul>  
@code {  
    public int[] Fibonacci = new int[] { 1, 1, 2, 3, 5, 8 };  
}
```



결과는 다음과 같습니다.


[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/image2b.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)



Blazor 아키텍처는 ASP.NET MVC 개발자에게 매우 친숙합니다. 더 나아가 Blazor는 Razor 구성 요소를 공유할 수 있을 뿐만 아니라 비슷한 라이프 사이클를 따르고 종속성 주입 및 미들웨어를 지원합니다. Startup 클래스에는 ConfigureServices 함수를 포함하여 MVC 개발자가 기대하는 것과 동일한 메소드가 포함되어 있습니다.



```
public void ConfigureServices(IServiceCollection services)  
{  
    services.AddRazorPages();  
    services.AddServerSideBlazor();  
    services.AddSingleton<WeatherForecastService>();  
}  
…and a Configure method:  
public void Configure(IApplicationBuilder app, IWebHostEnvironment env)  
{  
    if (env.IsDevelopment())  
    {  
        app.UseDeveloperExceptionPage();  
    }  
    else  
    {  
        app.UseExceptionHandler("/Error");  
        app.UseHsts();  
    }

    app.UseHttpsRedirection();  
    app.UseStaticFiles();

    app.UseRouting();

    app.UseEndpoints(endpoints =>  
    {  
        endpoints.MapBlazorHub();  
        endpoints.MapFallbackToPage("/_Host");  
    });  
}
```



앞의 예는 "ServerSideBlazor"구현을 나타냅니다. 이 부분은 좀 더 설명이 필요합니다.





### 서버 사이드와 클라이언트 사이드 Blazor

2017년에 소개된 Blazor는 빠르게 추진력을 얻었습니다. “실험적인 프로젝트”로 출시된 Blazor는 ASP.NET 팀에 의해 공식적인 채택 이전까지는 미래가 불확실했습니다. Blazor가 ASP.NET Core로 인수된 후, 클라이언트 사이드(Blazor WebAssembly)가 제작을 준비하기 전에 많은 작업이 필요하다고 신속하게 결정되었습니다.



성능 최적화, 어셈블리 트리밍, 디버그 경험 등을 위해서는 여전히 작업이 필요합니다. 따라서 클라이언트 사이드 Blazor는 미리 공개된 기능으로 출시되었습니다. Visual Studio 2019의 미리 공개된 버전 (또는 미리 공개된 버전이 아니라면 설정에서 "미리 고갱 사용" 옵션을 설정한 경우) 에서만 사용할 수 있습니다.



다행히도 Blazor의 아키텍처는 Blazor 로직과 실제 렌더링 엔진을 분리합니다. 왜냐하면 클라이언뿐만 아니라 서버에서 실행되고 참조될 수 있는 .NET 기준 라이브러리이기 때문에 팀은 서버 사이드 버전으로 만들기로 결정합니다. [Microsoft Docs](https://docs.microsoft.com/aspnet/core/blazor/hosting-models/?WT.mc_id=link-article-jeliknes) 에서 Blazor 호스팅 모델에 대해 자세히 알아보도록 합니다.



서버 사이드 버전은 동일한 규칙과 Razor 템플릿을 사용하며 클라이언트 사이드와 동일한 지원을 갖습니다. 차이점은 코드가 클라이언트의 WebAssembly가 아니라 서버에서 완전히 실행된다는 것입니다.



서버 측 Blazor가 실행되면 서버와 실시간 통신을 설정하는 브라우저로 경량 JavaScript 클라이언트가 전송됩니다. 서버는 렌더링을 포함하여 로직을 처리한 다음 결과를 클라이언트로 전송합니다. 결과는 경량 또는 작업을 서버에 사용하는 "씬 클라이언트"(thin client)로 만들어집니다. Blazor의 이 버전은 생산 준비가 완료되었으며 ASP.NET Core 3.0의 일부로 제공됩니다.



중요한 것은 Blazor 엔진을 사용하기 때문에 대부분의 경우 서버 측 코드는 구성 설정으로 간단하게 전환되어 클라이언트에서 "있는 그대로" 실행됩니다. “생산 준비(production-ready)”와“미리 공개(preview)”의 차이점 외에도 두 버전을 볼 때 고려해야 할 몇 가지 장단점이 있습니다.



| **블레이저 웹 어셈블리**                                     | **서버 측 블레이저**                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Thick client. 작업부하는 각 사용자에게 분배됩니다.           | Thin client. 서버는 리소스를 담당합니다.                     |
| 대부분의 .NET Standard 2.0 API를 지원하는 WebAssembly mono 구현으로 제한됩니다. | 전체 런타임을 지원합니다.                                    |
| DLL은 브라우저에서 로드되고 실행됩니다. 지적 재산은 사용자에게 노출됩니다. | 모든 코드는 서버에서 실행되고 보안됩니다. UI 업데이트만 클라이언트와 교환됩니다. |
| Progressive Web Application(PWA)을 완벽하게 지원합니다. 오프라인으로 설치 및 실행할 수 있습니다. | 오프라인 모드가 없습니다. 클라이언트가 작동하려면 활성 네트워크 연결이 필요합니다. |
| 정적 자산으로 구성됩니다. 정적 웹 사이트로 호스팅 할 수 있으며 CDN (콘텐츠 전송 네트워크)에서 서비스할 수 있습니다. | ASP .NET이 작동하도록 지원하는 활성 서버가 필요합니다.       |
| 대부분의 논리는 클라이언트에서 수행됩니다. 대부분의 네트워크 요청은 대기 시간이 짧습니다. | 렌더링을 위해 서버 측 통신에 크게 의존하며 지연 시간이 짧은 경우에는 성능이 저하 될 수 있습니다. |



장단점을 기반으로 클라이언트 측 Blazor에 중점을 둔 많은 개발자는 WebAssembly와 서버 사이드 프로젝트간에 공유되는 구성 요소를 갖춘 구현을 사용하고 있으며 서버 사이드는 생산과 디버깅에 사용됩니다. 클라이언트 측은 프로덕션으로 제공되며 클라이언트가 미리보기를 종료할 때 서버 사이드를 대체하는 데 사용됩니다.





### 기본 Blazor UI 컨트롤 vs. 랩핑된 JavaScript

UI 구성 요소 라이브러리는 Blazor보다 훨씬 이전에 생성되었습니다. 웹 애플리케이션을 대상으로 하는 대부분의 기존 프레임워크는 JavaScript를 기반으로합니다. JavaScript와의 상호 운용성으로 인해 여전히 Blazor와 호환됩니다. 기본적으로 JavaScript를 기반으로 하는 구성 요소는 *Blazor 컨트롤* 이라고하는 Blazor로 작성된 구성 요소와 달리 *랩핑된 JavaScript 컨트롤* 이라고 *합니다* .



Native Blazor 컨트롤은 Razor 구문을 분석하여 해당 컨트롤의 UI 및 동작을 나타내는 *렌더링 트리*를 생성합니다. 렌더 트리는 서버 사이드 Blazor를 실행할 수 있는 이유입니다. 트리는 분석되어 렌더링을 위해 클라이언트로 전송되는 서버에서 HTML을 생성하는 데 사용됩니다. Blazor WebAssembly의 경우, 클라이언트에서 렌더 트리는 분석되고 렌더링됩니다. 어느 쪽이든 Blazor는 컨트롤을 구성하는 요소에 대한 내부 참조를 유지하고 필요할 때 다시 렌더링하여 UI 새로고침을 처리합니다.



랩핑된 JavaScript 컨트롤에는 다음과 같은 주의사항이 있습니다.



- 랩핑된 컨트롤은 필요할 때 JavaScript를 호출하여 UI 새로고침을 작동시키는 것을 주의해야합니다.
- 내부 Blazor 렌더 트리가 없으므로 요소를 참조하려면 종종 DOM을 통과해야 합니다.
- 중첩 및 동적 컨트롤에 대한 지원이 제한될 수 있습니다.
- JavaScript는 Blazor 폼 및 유효성 검사 시스템에 직접 바인딩되지 않으므로 사용자 지정 유효성 검사 또는 Blazor와 JavaScript 간 브리지를 구축해야 합니다.

반면에 랩핑된 컨트롤의 이점은 다음과 같습니다.

- HTML, CSS 및 JavaScript를 기반으로 기존 구성 요소를 완벽하게 지원합니다.
- 기본 JavaScript 렌더링 덕분에 대부분은 더 좋은 퍼포먼스를 보여줍니다. 
- 참조 또는 패키지를 업데이트하고 앱을 재컴파일하지 않고 제어 라이브러리를 업데이트하는 것처럼 업데이트로 유지 관리가 향상될 수 있습니다.



랩핑된 JavaScript 컨트롤에 대한 자세한 내용은 [Blazor ](https://www.grapecity.com/blogs/javascript-interop-inside-blazor-component)[구성 요소 ](https://www.grapecity.com/blogs/javascript-interop-inside-blazor-component)[내부에서 JavaScript Interop을 사용하는 방법을 참조](https://www.grapecity.com/blogs/javascript-interop-inside-blazor-component)합니다.





### Visual Studio에서 Blazor WebAssembly 시작하기

Blazor는 짧은 기간에 불구하고 매우 발전된 툴링과 지원을 받았습니다. Visual Studio 2019의 최신 안정화된 버전은 즉시 사용할 수 있는 완전한 서버 사이드 Blazor를 완벽하게 지원합니다. 여기에는 프로젝트 및 구성 요소 생성과 IntelliSense 사용, 빌드, 디버그, 게시 및 배포 기능이 포함됩니다.



Visual Studio에서 Blazor WebAssembly에 대한 미리 공개 지원은 설정(Settings) → 환경(Environment) → 미리 공개 기능(Preview Features)으로 이동하여 ".NET Core SDK의 미리보기 사용(Use previews of the .NET Core SDK)"을 체크하여 사용할 수 있습니다. 대부분의 주요 구성 요소 공급 업체도 Blazor를 지원합니다.



예를 들어 GrapeCity는 기본 및 랩핑된 JavaScript Blazor 컨트롤을 모두 제공합니다. 차이점은 랩핑한 프로세스가 더 빠르기 때문에 랩핑된 컨트롤이 더 완벽하다는 점이며, 회사는 수년간의 개발을 통해 전체 컨트롤 제품군을 활용할 수있었습니다. GrapeCity의[ ](https://www.grapecity.co.kr/wijmojs)[JavaScript UI 컨트롤](https://www.grapecity.co.kr/wijmojs) 전체 제품군인 [Wijmo는 WijmoBlazor](https://github.com/Bernardo-Castilho/WijmoBlazor)를 통해 사용할 수 있습니다. 기본 컨트롤 제품군은 크기는 작지만 향후 몇 년 동안 빠르게 확장할 수 있습니다. [ComponentOne Blazor Edition](https://www.grapecity.co.kr/componentone-blazor)을 다운로드하여 베타 버전을 사용해 볼 수 있습니다 .



크로스 플랫폼, 오픈 소스 편집기인 Visual Studio Code를 사용하여 Blazor 프로젝트를 작업 할 수도 있습니다. Blazor 프로젝트를 열면 IntelliSense와 Blazor 앱 빌드, 디버깅 및 Blazor 앱 게시에 필요한 지원을 설치하라는 메시지가 표시됩니다. 단 3줄의 스크립트만으로 .NET Core command-line interface(CLI)로부터 Blazor 앱을 만들고 실행할 수 있습니다. 소스 디렉토리에서 다음을 입력합니다.



```
dotnet new blazorserver -o MyFirstBlazorApp  
cd MyFirstBlazorApp  
dotnet run  
```



내장된 샘플 앱은 ASP.NET MVC 템플릿과 동일한 예를 사용하여 쉽게 비교합니다.





### 첫 Blazor 앱

Blazor가 어떤 도움을 주는지 보여줄 수 있는 실제 앱을 만들어 보겠습니다.



이 예에서는 웹 개발 워크 플로우와 함께 Visual Studio 2019 16.3 이상이 설치되어 있어야 합니다. 새로운 프로젝트를 만들고 “Blazor App”을 선택합니다. 이름과 코드의 위치를 지정한 다음 작성을 클릭합니다. 모든 기본값을 유지합니다. 대화 상자는 다음과 유사해야 합니다. : 



[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/image2_edited.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)



프로젝트가 초기화 될 때까지 기다린 다음, CTRL + F5를 눌러 빌드 및 실행을 확인하십시오.



첫 번째 Blazor 프로젝트 생성을 한 후, 첫 번째 컴포넌트를 만듭니다. 공유 폴더에 마우스 오른쪽 버튼으로 클릭하고 추가(Add)를 클릭한 다음 새 항목(New Item)을 선택하거나 Ctrl + Shift + A를 누릅니다. 대화 상자에서 Razor Component를 선택하고 이름을 "Info.razor"로 지정합니다.



기본 템플릿을 다음 마크업과 코드로 바꿉니다.



```
<h3>@TheDate: @Environment</h3>  
<button **@onclick**="RefreshDate">Refresh</button>

@code {  
    public string Environment { get; set; }  
    public DateTime TheDate { get; set; }

    protected override void OnInitialized()  
    {  
        Environment = System.Environment.OSVersion.VersionString;  
        RefreshDate();  
    }

    public void RefreshDate()  
    {  
        TheDate = DateTime.Now;  
    }  
}
```



@code 블록에는 구성 요소 클래스의 일부로 작성된 속성 및 메소드가 포함되어 있습니다. @TheDate 규칙은 구성 요소의 관련 속성에 바인딩됩니다. 버튼 요소는 어떻게 JavaScript 이벤트에 바인딩하는지에 대한 방법을 보여줍니다. 이벤트 앞에 @를 붙이면 대상이 JavaScript가 아닌 C# 방식으로 해석됩니다. 코드 본문에서 구성 요소가 초기화될 때 라이프 사이클 이벤트가 유발되고 OS 버전이 서버에서 실행 중임을 표시하도록 설정됩니다.



@code 태그 앞에 마크업을 추가하여 구성 요소를 Pages 폴더의 Counter.razor 페이지에 놓습니다.



```
<Info></Info> 
```



애플리케이션을 실행하고 디버깅 콘솔 (F12)을 열어 네트워크(Network) 탭으로 이동합니다. 앱 (F5)을 새로 고치고 다음과 같이 WebSocket 연결을 찾습니다.



[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/image3_edited.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)



그런 다음 WebSocket을 클릭하여 메시지를 표시합니다. 카운터(Counter) 페이지로 이동하여 새로 고침 버튼을 클릭합니다. 서버 발송 업데이트를 볼 수 있습니다.



[![Blazor 및 WebAssembly 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191126-intro-to-blazor-and-web-assembly/image4_edited.png)](https://www.grapecity.com/blogs/intro-to-blazor-and-web-assembly#)



축하합니다! Blazor 앱을 만들고 구성 요소를 구축하고 첫 번째 라이프 사이클 및 데이터 바인딩 코드를 작성했습니다.





### Blazor와 WebAssembly : 미래

Blazor는 .NET을 어디에서나 실행하려는 꿈을 실현하고 마침내 C#을 사용하여 싱글 페이지 애플리케이션을 작성할 수 있게 되었습니다. Blazor 아키텍처는 종속성 주입, 미들웨어 구성과 같은 서버 측 ASP.NET MVC와 Blazor 기능과 유사하고 Razor 구성 요소는 숙달된 개발자들에게 이미 친숙합니다. Blazor WebAssembly는 2020년 5월에 출시되었으며 개발자는 지금 시작하여 다른 프로젝트에서 사용하는 것과 동일한 웹 구성 요소를 즉시 활용할 수 있습니다.







## JavaScript 및 ASP.NET 개발자를 위한 Blazor 소개

출처: https://dev.grapecity.co.kr/bbs/board.php?bo_table=Insight&wr_id=15



Blazor는 JavaScript 대신 C#을 사용하여 상호적인 웹 UI를 구축할 수 있게 해주는 새로운 프레임워크입니다. 완전히 새로운 언어를 배우지 않고 웹 개발에 활용하려는 .NET 개발자에게 탁월한 옵션입니다. 그러나 JavaScript 개발자라면 어떨까요? Blazor가 JavaScript 개발자에게 제공할 것이 있을까요? 아니면 Vue, React, Angular를 고수하는 것이 더 좋을까요?



JavaScript 개발자의 관점에서 Blazor를 살펴보도록 하겠습니다.



이 글에서는 다음을 다룹니다.



- Blazor와 JavaScript 비교
- Blazor와 ASP.NET Core MVC 애플리케이션 비교
- 클라이언트 측 Blazor의 장점
- 클라이언트 측 Blazor의 단점
- 현재 Blazor 도구 및 지원



Blazor 앱의 구조, 작동 방식, 잠재적 용도 및 JavaScript와의 비교 분석을 해보겠습니다. 마지막으로 Blazor의 실제 작동 방식을 보여주는 간단한 앱을 살펴 보겠습니다.



### Blazor란?

우리는 Blazor를 [ **WebAssembly**](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers) [언급하지 않고](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers) 소개할 수 없습니다.



WebAssembly (또는 간단히 "wasm")는 2015년에 발표된 웹 브라우저에 대한 개방형 사양입니다. 클라이언트와 서버 애플리케이션을 위해 웹에서 컴파일할 수 있게 설계된 작은 이진 코드 형식을 가집니다.



이 발표 이후 일부 조직에서는 WebAssembly를 바탕으로 고급 프로그래밍 언어를 실행할 수있는 프로젝트를 시작했습니다. Microsoft는 Blazor 프로젝트를 시작하여 주도권을 잡았으며 이제 클라이언트 측 웹 앱에서 Blazor를 사용하여 C#을 실행할 수 있습니다. WebAssembly 개방형 표준 사양 덕분에 .NET 코드는 플러그인없이 브라우저에서 실행될 수 있습니다.



Blazor는 풀스택으로 C#을 사용하는 SPA(Single-Page Applications)용 프런트엔드 개발 프레임워크입니다. 일반적으로 jQuery, React 또는 Angular와 함께 JavaScript에 의존하지 않고도 Blazor가 모든 것을 대신하기 때문에 C#으로 클라이언트 측 애플리케이션 코드를 작성할 수 있습니다. ASP.NET Core 서버 코드와 결합이 되면 .NET의 모든 부분을 가질 수 있습니다.





[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/image1_edited.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



Blazor 구성 요소는 C# 클래스로 작성된 다음 .NET 어셈블리에 내장되어 있습니다. 구성 요소는 사용자 이벤트를 처리하고 UI를 렌더링하는 입출력 작업을 담당합니다. 구성 요소는 Razor 클래스 라이브러리 또는 NuGet 패키지와 같은 여러 앱 및 플랫폼에서 재사용, 공유 및 배포되는 컨테이너로 사용할 수 있습니다.(Razor는 HTML와 C#을 사용하여 동적 웹 페이지 프로그래밍을 허용하는 웹 개발 구문입니다.)



클라이언트측 로직을 수행하기 위한 JavaScript 함수가 포함된 HTML 페이지는 프론트엔드 개발자에게 친숙합니다. 마찬가지로 일반적인 Blazor 구성 요소는 HTML 렌더링 및 이벤트 처리를 담당하는 C# 코드와 함께 HTML 요소가 포함된 마크업 파일(.razor 확장명)로 선언됩니다.



### Blazor와 JavaScript를 비교하는 방법

Blazor는 재사용 가능한 웹 UI 구성 요소를 통해 코드 캡슐화를 제공합니다. Blazor는 클라이언트와 서버 C# 코드 모두 코드와 라이브러리를 공유할 수 있도록 합니다.



컴포넌트화는 React, Angular 및 기타 JavaScript 프레임워크를 사용하는 사람들에게 친숙한 개념입니다. 구성 요소는 앱의 구축 단위이며 일반적인 앱에는 이러한 구성 요소가 많이 있습니다. 선택적으로 입력을 허용하고 UI 섹션이 표시되는 방법을 설명하는 HTML 요소를 반환합니다.



ASP.NET Core는 Razor 구성 요소 형태로 아이디어를 제공합니다. Razor 구성 요소도 Blazor의 구성 요소 모델입니다. 따라서 Blazor 구성 요소와 Razor 구성 요소는 서로 바꿔 사용할 수 있습니다.



각 Blazor 구성 요소는 쇼핑 카트, 뉴스 피드 또는 설명 섹션과 같은 애플리케이션 UI 구조에서 다른 요소를 나타냅니다.

###  

### Blazor와 ASP.NET Core MVC 애플리케이션의 차이점

일반적인 ASP. NET Core MVC는 문자열 블록으로 UI를 렌더링합니다. 반면에 Blazor는 문자열이 아니라 메모리에 보관된 DOM (Document Object Model)의 표현인 렌더 트리를 만듭니다. Blazor는 그 표현을 유지합니다. 예를 들어, 바인딩 값이 업데이트될 때와 같이 변화가 생기면 영향을 받는 DOM 요소에 대한 UI 업데이트가 트리거됩니다. 이는 문자열을 렌더링하는 ASP.NET Core HTML 도우미 및 Razor View와 큰 차이점입니다. 



그렇다면 왜 Blazor는 문자열 대신 렌더 트리를 사용할까요? Blazor 코드 (그리고 일반적으로 wasm 코드)는 DOM 요소에 직접 액세스할 수 없습니다. 이 제한은 UI 요소에 완전히 접근할 수 있게 JavaScript에 의존하는 ASP.NET Core HTML 도우미(Helpers)와 Razor View와는 다른 점입니다. 따라서 Blazor는 트리를 렌더링하여 DOM 표현으로 돌아가 특정 DOM 부분을 찾은 다음 업데이트, 편집, 삭제 등의 작업을 수행합니다. Blazor 프레임워크는 데이터 모델의 변경 사항을 보고 렌더 트리를 조작하여 변경사항을 적용합니다. 이 메커니즘은 C#이 클라이언트 쪽에서 작동하도록합니다.



그런데 순수 클라이언트 측 JavaScript 또는 ASP.NET Core MVC/Razor가 아닌 Blazor로 개발하도록 선택해야하는 이유는 무엇일까요? 이들 간의 차이점에 대해 논의해보도록 하겠습니다. 그러면 새로운 Blazor 프로젝트를 개발하기 전에 정보에 따른 결정을 내릴 수 있습니다.





### 클라이언트 측 Blazor의 장점

1. Blazor를 사용하면 .NET 코드를 브라우저에서 직접 실행할 수 있습니다. .NET 개발자가 여러 언어를 사용하여 프로그래밍 할 필요가 없기 때문에 풀스택 전문가의 JavaScript / Node.JS 독점을 깰 수 있습니다. JavaScript 코드를 작성하지 않고도 완벽한 솔루션을 만들 수 있습니다.
2. Blazor 코드는 .NET 중급 언어로 컴파일되므로 동등한 JavaScript 코드보다 빠를 가능성이 있습니다. 이 컴파일 모델은 게임과 같은 성능 중심 브라우저 응용 프로그램을 차별화할 수 있습니다.
3. 클라이언트와 서버 앱간에 유효성 검사 코드를 공유할 수 있습니다. 브라우저와 백엔드 모두에 적용할 폼 유효성 검사 논리가 있다고 가정합니다. Blazor를 사용하면 .NET Standard 2.0에서 클래스 라이브러리를 만들고 클라이언트 및 서버 앱간에 공유를 할 수 있습니다. 유효성 검사 논리의 변경 사항은 두 플랫폼 모두 자동으로 적용됩니다.

### 클라이언트 측 Blazor의 단점

1. 브라우저에서 Blazor를 실행하려면 애플리케이션의 .wasm 및 .NET 라이브러리를 브라우저로 다운로드해야 할뿐만 아니라 Blazor가 실행되는 .NET 런타임인 Mono.wasm도 다운로드해야 합니다. Mono.wasm은 캐시될 수 있지만 이러한 자산을 처음 다운로드를 하면 애플리케이션의 시작이 지연됩니다. 따라서 작은 Blazor 응용 프로그램에서도 메가 바이트의 코드를 다운로드해야 합니다. JavaScript에는 이 오버헤드가 없습니다.
2. Blazor는 DOM 요소를 직접 조작할 수 없습니다. 경우에 따라 클라이언트 앱은 HTML 요소를 많이 제어해야 합니다. Blazor는 이 기능을 자체적으로 제공하지 않기 때문에 이 간격을 메우려면 JavaScript Interop을 사용해야합니다.
3. 전반적인 클라이언트 측 Blazor는 현재 앱 실행 측면에서(시작뿐만 아니라) 대부분 현재 JavaScript보다 느립니다. Microsoft는 성능을 개선하기 위해 가장 확실히 노력하고 있으며 다른 플랫폼에서 성능 튜닝 작업을 수행한 이력이 감안할 때 시간이 지남에 따라 해결할 수 있을 것입니다.



현재 디버깅 기능이 제한되어 있다는 점도 주목할 가치가 있습니다. 이 영역에서 Blazor는 여전히 개선되어야합니다. Chrome의 디버깅 탭을 사용하여 int, bool 및 string 유형의 값을 디버깅 및 검사 할 수 있습니다. 더 효과적으로 디버깅하려면 클라이언트 응용 프로그램 전체에 콘솔 로깅을 적용해야 합니다.



### Blazor의 두 얼굴

우리는 보통 브라우저에서 WebAssembly 위에서 실행되는 C# 코드로 Blazor에 대해 이야기하지만 그것은 이야기의 절반에 지나지 않습니다. 전체 그림을 보려면 이러한 앱을 Blazor 클라이언트 측 또는 Blazor 서버 측으로 실행하도록 만들 수 있음을 이해해야합니다. 앱을 만들 때 먼저 호스팅 모델 중 하나를 선택합니다. 이 구성은 응용 프로그램이 동일하게 유지되더라도 응용 프로그램이 밑에서 작동하는 방식을 크게 변경합니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/2.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



Blazor 클라이언트 측은 JavaScript 개발자에게 친숙한 호스팅 모델입니다. 개발자는 웹 서버에 앱 에셋을 배포하면 일련의 정적 파일 집합으로 클라이언트 브라우저에 서비스를 제공 할 수 있습니다. 브라우저가 이러한 파일을 다운로드 한 후 앱은 WebAssembly 위에서 실행될 수 있습니다. 이 동작은 JavaScript 대신에 WebAssembly에서 실행된다는 점을 제외하고 클라이언트 측 JavaScript 앱과 크게 다르지 않습니다.





다음으로 서버 측 Blazor에 대해 이야기하겠습니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/image3_edited.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



Blazor 서버 측 앱은 서버에서만 실행되며 웹 소켓 기반 실시간 통신 라이브러리인 ASP.NET Core SignalR 기반으로 구축됩니다. Blazor 서버 측은 애플리케이션이 브라우저에서 실행될 때 WebAssembly에 의해 부과되는 제한없이 전체 ASP.NET Core 웹 프레임워크 위 서버에서 실행될 수 있습니다. 



### 현재 Blazor 도구 및 지원

Microsoft가 Blazor를 시작했을 때 브라우저에서 실행될 수 있는 .NET 싱글 페이지 앱을 위한 실험적인 프레임워크였습니다. 그러나 Microsoft는 이를 지원되는 웹 UI 프레임워크로 제공하기로 약속하고 있습니다. Windows, Linux 및 Mac OS에서 지원되며 Microsoft에서 웹 개발의 대표 제품인 ASP.NET의 기능으로 Microsoft에서 배포합니다. ASP.NET Core 파이프라인은 이제 Blazor를 공식 롤아웃에 포함합니다.



### 샘플 Blazor 앱 만들기 

실습 준비가 되셨습니까? Blazor의 작동 방식을 보다 명확하게 이해하려면 다음 단계를 수행합니다.



최신 [.NET Core 3.0 Preview SDK 릴리즈를](https://dotnet.microsoft.com/download/dotnet-core/3.0) 설치합니다.



명령 쉘에 다음 명령을 실행하여 Blazor 템플릿을 설치합니다.



```
dotnet new -i Microsoft.AspNetCore.Blazor.Templates::3.0.0-preview9.19424.4 
```



[비주얼 스튜디오 코드](https://code.visualstudio.com/)와 최신판 [Visual Studio Code ](https://marketplace.visualstudio.com/items?itemName=ms-vscode.csharp)C# 확장 프로그램을 설치하거나 Visual Studio 2019를 설치합니다.



Blazor의 가장 기본적인 형태는 클라이언트 프로젝트만 필요합니다. 그러나 서버 측 백엔드 프로젝트와 함께 클라이언트 측도 원하므로 명령 쉘에서 다음 명령을 실행합니다.



```
dotnet new blazorwasm -o BlazorClientServer --hosted 
```



Visual Studio에서 BlazorClientServer.sln 솔루션을 열거나 Visual Studio Code에서 BlazorClientServer 폴더를 엽니다. 



솔루션을 실행합니다.



브라우저에서 [https://localhost:5001로](https://localhost:5001/) 이동합니다.



여기서 애플리케이션 홈페이지를 볼 수 있습니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/image4_edited.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



이제 다른 두 메뉴 항목인 카운터(Counter)및 데이터 가져오기(Fetch data)로 이동합니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/5.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)

[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/6.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



이것은 일반적인 ASP.NET Core MVC 웹사이트와 비슷합니다. Blazor 솔루션은 우리가 보려는 것처럼 JavaScript를 C# 코드로 대체하는 ASP.NET Core의 새로운 방식이라고 생각할 수 있습니다.



이제 이전에 생성된 솔루션에 포함된 프로젝트를 자세히 살펴보겠습니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/7.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



BlazorClientServer.Client는 .NET Standard 2.0으로 작성된 프로젝트입니다. 브라우저 내부에서 실행되는 Blazor 페이지 및 구성 요소를 포함하고 사용 중에 페이지를 다시 로드할 필요가없는 SPA입니다.



BlazorClientServer.Server는 HTTP API 서비스를 위한 ASP.NET Core 프로젝트입니다. 브라우저에서 요청한대로 일기 예보 데이터를 반환하기 위한 컨트롤러와 작업을 보유합니다.



BlazorClientServer.Shared는 클라이언트 및 서버 프로젝트 모두에서 사용되는 WeatherForecast 클래스를 포함하는 .NET Standard 2.0 프로젝트입니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/8_1.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



응용 프로그램을 실행하면 BlazorServer.Client.dll 어셈블리가 컴파일되어 localhost에 배포되지만 웹 서버는 이를 실행하지 않습니다. 대신 localhost는 브라우저가 이 파일을 요청할 때까지 기다립니다. 다운로드 시 이 어셈블리는 브라우저에서 웹 어셈블리로 로컬로 실행됩니다.



그러나 브라우저는 언제 이 파일을 다운로드할까요? index.html을 보면 BlazorServer.Client.dll을 참조하지 않는 것을 볼 수 있습니다. 대신 blazor.webassembly.js의 URL을 지정합니다.



```
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width" /> 
  <title>BlazorClientServer</title> 
  <base href="/" /> 
  <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet"> />
  <link href="css/site.css" rel="stylesheet" />
</head>
<body>
  <app>Loading...</app>
  <script src="_framework/blazor.webassembly.js"></script>
</body>
</html>
```



하지만 Blazor에는 JavaScript가 필요하지 않습니다. 그러나 여전히 blazor.webassembly.js 파일은 Mono.wasm 런타임, .NET DLL 및 응용 프로그램 DLL을 다운로드하기 위해 필요합니다. 이러한 자산을 다운로드하면 애플리케이션은 WebAssembly로 실행할 수 있습니다.





Blazor 구성 요소 트리의 최상위 요소는 App.razor 파일에 정의되어 있습니다. Blazor 템플릿에서 제공하는 라우터 구성 요소 아래에 기본 레이아웃을 MainLayout으로 설정합니다.



```
<Router AppAssembly="@typeof(Program).Assembly"> 
  <Found Context="routeData">
    <RouteView RouteData="@routeData"
DefaultLayout="@typeof(MainLayout)" />
  </Found>
  <NotFound> 
    <LayoutView Layout="@typeof(MainLayout)">
      <p>Sorry, there's nothing at this address.</p>
    </LayoutView>
  </NotFound>
</Router>
```



위에서 보이는 것과 같이 React, Angular 또는 Vue.JS와 같은 컴포넌트 기반 프레임워크에서 예상되는 것과 같은 구성 요소로 구성된 구조입니다. 각 구성 요소는 분리된 .razor 파일에 정의되어 있습니다.



MainLayout 구성 요소는 CSS 클래스와 함께 일반 HTML 코드로 레이아웃 구조를 정의합니다. 애플리케이션 레이아웃은 사이드 바 메뉴와 중앙 페이지 본문으로 표시됩니다.



```
@inherits LayoutComponentBase
<div class="sidebar">
  </NavMenu>
</div>
<div class="main">
  <div class="top-row px-4">
      <a href="http://blazor.net" target="_blank" class="ml-md-auto">About</a>
    </div>
    <div class="content px-4">
      @Body
    </div>
</div>
```



위의 MainLayout 구성 요소는 다른 LayoutComponentBase에서 상속됩니다. 상속은 구성 요소가 기본적인 구성 요소를 기반으로 구축할 수 있게 해줍니다. 코드 중복을 줄이고 앱 개발을 단순화합니다. 본문은 레이아웃 내에서 렌더링되는 RenderFragment입니다.



```
public abstract class LayoutComponentBase : ComponentBase 
{ 
        protected LayoutComponentBase(); 
        // 
        // Summary: 
        //      Gets the content to be rendered inside the layout. [Parameter] 
        public RenderFragment Body { get; set; } 
} 
```



사용자 인터페이스의 일부를 렌더링하는 구성 요소 외에도 Blazor는 일반 HTML 응용 프로그램과 같이 페이지 개념을 가지고 있습니다. 여기서 카운터와 IncrementCount 버튼이 있는 간단한 페이지를 볼 수 있습니다.



```
@page " /counter"
<h1>Counter</h1>
<p>Current count: @currentCount</p>
<button class="btn btn-primary" @onclick="IncrementCount">Click me</button>
@code {
    int currentCount = 0;
    void IncrementCount ()
    {
      currentCount++;
    }
}
```



그러나 이 페이지는 일반 HTML 페이지와 어떻게 다를까요?



첫 번째 줄에는 @page“/counter”와 같은 페이지 지시문이 있습니다. 페이지로 취급될 파일을 선언할 뿐만 아니라 사용자에 의해 "/count"라우트가 요청될 때마다 이 페이지가 렌더링되도록 정의합니다.



@currentCount는 HTML 내에서 currentCount 변수를 간단히 렌더링하는 바인딩 표현식을 정의합니다. 그리고 @onclick은 버튼을 누를 때 IncrementCount를 트리거하는 이벤트 콜백입니다.



그러나 이 Blazor 클라이언트 측 앱은 서버 백엔드와 어떻게 상호 작용될까요? 이제 FetchData.razor 페이지를 조사하여 알아봅니다.



```
@page "/fetchdata" 
@using BlazorClientServer.Shared 
@inject HttpClient Http
<h1>Weather forecast</h1>
<p>This component demonstrates fetching data from the server.</p>
@if (forecasts == null) 
{
  <p><em>Loading...</em></p>

} 
else 
{ 
  <table class="table">
    <thead>
      <tr>
        <th>Date</th>
        <th>Temp. (C)</th>  
        <th>Temp. (F)</th>
        <th>Summary</th>
      </tr>
    </thead>
<tbody>
        @foreach (var forecast in forecasts) 
        { 
            <tr>  
                  <td>@forecast.Date.ToShortDateString()</td>
                  <td>@forecast.TemperatureC</td> 
                  <td>@forecast.TemperatureF</td>
                  <td>@forecast.Summary</td>
            </tr>
        }
    </tbody>
} @code { 
    WeatherForecast[] forecasts; 
    protected override async Task OnInitializedAsync() 
    { 
      forecasts = await 
Http.GetJsonAsync<WeatherForecast[]>("WeatherForecast"); 
    } 
}
```



초기화될 때, FetchData 페이지가 HTTP GET 요청을 통해 일기 예보 데이터를 비동기적으로 검색합니다. 일반적으로 JavaScript는 이 요구 사항을 AJAX 함수로 구현합니다. 여기서 이 역할은 Http.GetJsonAsync() 메소드에 의해 수행됩니다.



백엔드 애플리케이션 (BlazorClientServerApp.Server 프로젝트)은 그다지 많지 않습니다. localhost에서 서버 애플리케이션을 호스팅하고 일기 예보 함수를 위한 서버 엔드포인트 구현을 제공합니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/9.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



다음은 일기 예보 기능 자체인 WeatherForecastController.cs입니다.



```
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        //code block removed for the sake of brevity
        //...
        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {
            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateTime.Now.AddDays(index),
                TemperatureC = rng.Next(-20, 55),
                Summary = Summaries[rng.Next(Summaries.Length)]
            })
            .ToArray();
        }
    }
```



마지막으로 WeatherForecast 클래스가 포함된 BlazorClientServerApp.Shared 프로젝트가 있습니다. 이것은 중요하지는 않은 것처럼 보이지만 브라우저, WebAssembly 또는 서버의 전체 ASP.NET Core 애플리케이션 내에서 동일한 클래스를 실행할 수 있습니다. 



Blazor는 서로 다른 .NET 구현간에 공유되는 API 사양인 .NET Standard 2.0을 구현합니다. .NET 표준 클래스 라이브러리는 Blazor, .NET Framework, .NET Core, Xamarin 및 Mono를 포함한 다른 플랫폼에 배포될 수 있습니다.



[![JavaScript 및 ASP.NET 개발자를위한 Blazor 소개](https://global-cdn.grapecity.com/blogs/grapecity/20191028-intro-to-blazor-for-javascript-and-aspdotnet-developers/10.png)](https://www.grapecity.com/blogs/introduction-blazor-for-javascript-and-aspdotnet-developers#)



WeatherForecast 클래스는 매우 간단합니다.



```
  public class WeatherForecast 
  { 
      public DateTime Date { get; set; } 
      public int TemperatureC { get; set; } 
      public string Summary { get; set; } 
      public int TemperatureF => 32 + (int)(TemperatureC / 0.5556); 
  } 
```

###  

### Blazor 및 JavaScript 앱 : 다음은 무엇일까요?



이 글에서는 Blazor와 WebAssembly 개념을 소개하고 이 프레임워크가 기존 JavaScript 및 ASP.NET 프로그래밍과 비교되는 방식에 대해 설명했습니다. 



우리는 동일한 애플리케이션이 클라이언트 측 또는 서버 측 호스팅을 사용하는지에 따라 어떻게 다르게 작동하는지 확인하여 Blazor 클라이언트 측 접근 방식의 장단점을 확인하였습니다.



마지막으로, 우리는 샘플 Blazor 클라이언트-서버 앱의 여러 부분을 둘러보고 실제로 JavaScript 애플리케이션과 다른 부분을 지적하여 실제로 보여줍니다.



Blazor는 흥미진진하지만 특히 툴링 및 디버깅과 관련하여 개선의 여지가 많습니다. JavaScript 프로그래밍은 활성화되어 있으며 오래 지속될 것으로 보이지만 WebAssembly는 프론트엔드 개발자가 너무 오랫동안 무시할 수 없는 기술입니다. Blazor는 .NET 전문가가 새로운 프레임워크를 활용하고 새로운 언어를 배우지 않고도 풀스택 개발자가 될 수 있는 훌륭한 기회입니다.