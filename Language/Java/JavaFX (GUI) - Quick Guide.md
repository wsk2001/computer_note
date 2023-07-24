# JavaFX - 빠른 안내서

출처: https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm



# JavaFX - 개요

**리치 인터넷 애플리케이션은** 데스크톱 애플리케이션과 유사한 기능 및 경험을 제공하는 웹 애플리케이션입니다. 일반 웹 애플리케이션과 비교할 때 사용자에게 더 나은 시각적 경험을 제공합니다. 이러한 응용 프로그램은 브라우저 플러그인 또는 가상 머신으로 제공되며 기존의 정적 응용 프로그램을 보다 향상되고 유동적이며 애니메이션이 적용된 매력적인 응용 프로그램으로 변환하는 데 사용됩니다.

기존의 데스크톱 응용 프로그램과 달리 RIA는 추가 소프트웨어를 실행할 필요가 없습니다. 대안으로 애플리케이션에 따라 ActiveX, Java, Flash와 같은 소프트웨어를 설치해야 합니다.

RIA에서 그래픽 프리젠테이션은 풍부한 그래픽을 지원하는 플러그인이 있으므로 클라이언트 측에서 처리됩니다. 간단히 말해서 RIA의 데이터 조작은 서버 측에서 수행되는 반면 관련 객체 조작은 클라이언트 측에서 수행됩니다.

RIA를 개발할 수 있는 세 가지 주요 기술이 있습니다. 여기에는 다음이 포함됩니다.

- 어도비 플래시
- 마이크로 소프트 실버 라이트
- 자바FX

### 어도비 플래시

이 소프트웨어 플랫폼은 Adobe Systems에서 개발했으며 리치 인터넷 애플리케이션을 만드는 데 사용됩니다. 이와 함께 벡터, 애니메이션, 브라우저 게임, 데스크톱 응용 프로그램, 모바일 응용 프로그램 및 게임 등과 같은 다른 응용 프로그램도 만들 수 있습니다.

이것은 96%의 데스크톱 브라우저 보급률로 RIA를 개발하고 실행하는 데 가장 일반적으로 사용되는 플랫폼입니다.

### 마이크로 소프트 실버 라이트

Adobe 플래시와 마찬가지로 Microsoft Silverlight는 리치 인터넷 응용 프로그램을 개발하고 실행하기 위한 소프트웨어 응용 프로그램 프레임워크이기도 합니다. 처음에 이 프레임워크는 스트리밍 미디어에 사용되었습니다. 현재 버전은 멀티미디어, 그래픽 및 애니메이션도 지원합니다.

이 플랫폼은 데스크톱 브라우저 보급률이 66%로 거의 사용되지 않습니다.

### 자바FX

JavaFX는 풍부한 인터넷 응용 프로그램을 개발할 수 있는 Java 라이브러리입니다. 이러한 애플리케이션은 Java 기술을 사용하여 브라우저 침투율이 76%입니다.

## JavaFX란 무엇입니까?

JavaFX는 리치 인터넷 애플리케이션을 구축하는 데 사용되는 Java 라이브러리입니다. 이 라이브러리를 사용하여 작성된 애플리케이션은 여러 플랫폼에서 일관되게 실행될 수 있습니다. JavaFX를 사용하여 개발된 응용 프로그램은 데스크톱 컴퓨터, 휴대폰, TV, 태블릿 등과 같은 다양한 장치에서 실행할 수 있습니다.

**Java 프로그래밍 언어를 사용하여 GUI 응용 프로그램을** 개발하기 위해 프로그래머는 **Advanced Windowing Toolkit** 및 **Swing** 과 같은 라이브러리에 의존합니다 . JavaFX가 등장한 후 이러한 Java 프로그래머는 이제 풍부한 콘텐츠로 GUI 응용 프로그램을 효과적으로 개발할 수 있습니다.

## JavaFX의 필요성

**풍부한 기능을 갖춘 클라이언트 측 애플리케이션을** 개발하기 위해 프로그래머는 다양한 라이브러리에 의존하여 미디어, UI 컨트롤, 웹, 2D 및 3D 등과 같은 기능을 추가했습니다. JavaFX는 이러한 모든 기능을 단일 라이브러리에 포함합니다. 이 외에도 개발자는 **Swing** 과 같은 Java 라이브러리의 기존 기능에 액세스할 수도 있습니다 .

JavaFX는 풍부한 그래픽 및 미디어 API 세트를 제공하며 하드웨어 가속 그래픽을 통해 최신 **그래픽 처리 장치를** 활용합니다 . JavaFX는 또한 개발자가 그래픽 애니메이션과 UI 제어를 결합할 수 있는 인터페이스를 제공합니다.

Java, Groovy 및 JRuby와 같은 JVM 기반 기술과 함께 JavaFX를 사용할 수 있습니다. 개발자가 JavaFX를 선택하는 경우 위에서 언급한 기술에 대한 사전 지식이 있으면 JavaFX를 사용하여 RIA를 개발하기에 충분하므로 추가 기술을 배울 필요가 없습니다.

## JavaFX의 기능

다음은 JavaFX의 중요한 기능 중 일부입니다.

- **Java로 작성 - JavaFX 라이브러리는 Java로 작성되었으며 JVM에서 실행할 수 있는 언어(** **Java, Groovy 및 JRuby** 포함)에서 사용할 수 있습니다 . 이러한 JavaFX 응용 프로그램은 또한 플랫폼 독립적입니다.
- **FXML** - JavaFX는 선언적 마크업 언어와 같은 HTML인 FXML로 알려진 언어를 특징으로 합니다. 이 언어의 유일한 목적은 사용자 인터페이스를 정의하는 것입니다.
- **Scene Builder** - JavaFX는 Scene Builder라는 애플리케이션을 제공합니다. 이 애플리케이션을 Eclipse 및 NetBeans와 같은 IDE에 통합할 때 사용자는 FXML 애플리케이션(Swing 드래그 앤 드롭 및 DreamWeaver 애플리케이션과 같은)을 개발하는 데 사용되는 드래그 앤 드롭 디자인 인터페이스에 액세스할 수 있습니다.
- **Swing 상호 운용성** - JavaFX 애플리케이션에서 **Swing Node** 클래스를 사용하여 Swing 콘텐츠를 포함할 수 있습니다. 마찬가지로 임베디드 웹 콘텐츠 및 리치 그래픽 미디어와 같은 JavaFX 기능으로 기존 Swing 애플리케이션을 업데이트할 수 있습니다.
- **내장 UI 컨트롤** − JavaFX 라이브러리는 완전한 기능을 갖춘 애플리케이션을 개발할 수 있는 UI 컨트롤을 제공합니다.
- **스타일링과 같은 CSS** - JavaFX는 스타일과 같은 CSS를 제공합니다. 이를 사용하면 CSS에 대한 간단한 지식으로 응용 프로그램의 디자인을 개선할 수 있습니다.
- **캔버스 및 인쇄 API** - JavaFX는 즉시 모드 스타일의 렌더링 API인 캔버스를 제공합니다. **javafx.scene.canvas** 패키지 내에 는 JavaFX 장면의 영역 내에서 직접 그릴 수 있는 캔버스용 클래스 집합이 포함되어 있습니다. **JavaFX는 또한 javafx.print** 패키지에서 인쇄용 클래스를 제공합니다 .
- **풍부한 API 세트** - JavaFX 라이브러리는 GUI 애플리케이션, 2D 및 3D 그래픽 등을 개발하기 위한 풍부한 API 세트를 제공합니다. 이 API 세트에는 Java 플랫폼의 기능도 포함됩니다. 따라서 이 API를 사용하여 Generics, Annotations, Multithreading 및 Lambda Expressions와 같은 Java 언어의 기능에 액세스할 수 있습니다. 기존 Java Collections 라이브러리가 향상되었으며 관찰 가능한 목록 및 맵과 같은 개념이 포함되었습니다. 이를 사용하여 사용자는 데이터 모델의 변화를 관찰할 수 있습니다.
- **통합 그래픽 라이브러리** - JavaFX는 **2d** 및 **3d** 그래픽용 클래스를 제공합니다.
- **그래픽 파이프라인** - JavaFX는 Prism으로 알려진 하드웨어 가속 그래픽 파이프라인을 기반으로 그래픽을 지원합니다. 지원되는 그래픽 카드 또는 GPU와 함께 사용하면 부드러운 그래픽을 제공합니다. 시스템이 그래픽 카드를 지원하지 않는 경우 프리즘은 소프트웨어 렌더링 스택으로 기본 설정됩니다.

## JavaFX의 역사

JavaFX는 원래 2005년에 **Sun Microsystems** 에 인수된 **See Beyond Technology Corporation** 이라는 회사에서 일하던 **Chris Oliver 가 개발했습니다.**

다음 사항은 이 프로젝트에 대한 자세한 정보를 제공합니다.

- 처음에 이 프로젝트는 F3 **(Form Follows Functions)** 로 이름이 지정되었으며 GUI 응용 프로그램 개발을 위한 보다 풍부한 인터페이스를 제공하려는 의도로 개발되었습니다.
- **Sun Microsystems는** 2005년 6월에 See Beyond 회사를 인수하여 F3 프로젝트를 **JavaFX** 로 채택했습니다 .
- 2007년 JavaFX는 매년 개최되는 월드 와이드 웹 컨퍼런스인 **Java One** 에서 공식적으로 발표되었습니다 .
- 2008년에는 JavaFX와 통합된 **Net Beans를** 사용할 수 있었습니다. 같은 해에 JavaFX 1.0용 Java **표준 개발 키트가** 출시되었습니다.
- 2009년 Oracle Corporation은 Sun Microsystems를 인수했으며 같은 해 JavaFX(1.2)의 차기 버전도 출시되었습니다.
- 2010년에 JavaFX 1.3이 나왔고 2011년에 JavaFX 2.0이 출시되었습니다.
- 최신 버전인 JavaFX8은 2014년 3월 18일에 Java의 통합 부분으로 릴리스되었습니다.

# JavaFX - 환경

Java8부터 JDK **(Java Development Kit)** 에는 **JavaFX** 라이브러리가 포함되어 있습니다 . 따라서 JavaFX 응용 프로그램을 실행하려면 시스템에 Java8 이상 버전을 설치하기만 하면 됩니다.

그 외에도 Eclipse 및 NetBeans와 같은 IDE는 JavaFX에 대한 지원을 제공합니다. 이 장에서는 다양한 방법으로 JavaFX 응용 프로그램을 실행하기 위한 환경을 설정하는 방법을 설명합니다.

## 자바8 설치

먼저 명령 프롬프트를 열고 "Java" 명령을 입력하여 시스템에 Java가 설치되어 있는지 확인해야 합니다.

시스템에 Java를 설치하지 않은 경우 명령 프롬프트에 다음 스크린샷에 표시된 메시지가 표시됩니다.

![자바 설치](.\images\installing_java.jpg)

그런 다음 아래 제공된 단계에 따라 Java를 설치하십시오.

**1단계** - [JavaSE 다운로드](https://www.oracle.com/technetwork/java/javase/downloads/index.html) 페이지를 방문하여 다음 스크린샷에 강조 표시된 JDK **다운로드 버튼을 클릭합니다.**

![자바 SE 다운로드](.\images\java_se_downloads.jpg)

**2단계 - 다운로드 버튼을 클릭하면** **Java SE Development Kit 8 다운로드** 페이지 로 리디렉션됩니다 . 이 페이지는 다양한 플랫폼용 JDK 링크를 제공합니다.

라이센스 계약에 동의하고 해당 링크를 클릭하여 필요한 소프트웨어를 다운로드하십시오.

예를 들어 Windows 64비트 운영 체제에서 작업하는 경우 다음 스크린샷에 강조 표시된 JDK 버전을 다운로드해야 합니다.

![Java SE 개발 키트 8 다운로드 페이지](.\images\java_se_development_kit_8_downloads_page.jpg)

강조 표시된 링크를 클릭하면 Windows 64비트 운영 체제에 적합한 Java8 개발 키트가 시스템에 다운로드됩니다.

**3단계** - 다운로드한 바이너리 실행 파일을 실행하여 JDK8 설치를 시작합니다.

![자바 SE 개발 키트 8 다음](.\images\java_se_development_kit_8_next.jpg)

**4단계** - 설치 디렉토리를 선택합니다.

![설치 디렉토리](.\images\installation_directory.jpg)

**5단계** - 대상 폴더를 선택하고 다음을 클릭하면 JavaFX 설치 프로세스가 다음 스크린샷과 같이 진행률 표시줄을 표시하기 시작합니다.

![인스톨러 추출](.\images\extracting_installer.jpg)

**6단계** - 필요한 경우 설치 디렉토리를 변경하고 그렇지 않으면 기본 디렉토리를 유지하고 계속 진행합니다.

![목적지_폴더](.\images\destination_folder.jpg)

**7단계** - 다음 스크린샷과 같이 닫기 버튼을 클릭하여 설치 프로세스를 마칩니다.

![자바 SE 설치됨](.\images\java_se_installed.jpg)

## Windows용 경로 설정

Java를 설치한 후 경로 변수를 설정해야 합니다. **C:\Program Files\java\jdk1.8.0_91** 디렉토리 에 Java를 설치했다고 가정합니다 .

이제 아래에 제공된 단계를 따를 수 있습니다.

- '내 컴퓨터'를 마우스 오른쪽 버튼으로 클릭하고 '속성'을 선택합니다.
- '고급' 탭에서 '환경 변수' 버튼을 클릭합니다.
- 이제 Java 실행 파일에 대한 경로도 포함하도록 'Path' 변수를 변경하십시오. 예를 들어 현재 경로가 'C:\WINDOWS\SYSTEM32'로 설정되어 있는 경우 경로를 'C:\WINDOWS\SYSTEM32'로 변경합니다. C:\Program Files\java\jdk1.8.0_91\bin'.

![환경 변수 편집](.\images\edit_environment_variable.jpg)

## JavaFX의 NetBeans 환경 설정

**NetBeans8은** JavaFX에 대한 기본 지원을 제공합니다. 이것을 설치하면 추가 플러그인이나 JAR 파일 없이 JavaFX 응용 프로그램을 만들 수 있습니다. NetBeans 환경을 설정하려면 아래 제공된 단계를 따라야 합니다.

**1단계** - [NetBeans 웹사이트](https://netbeans.org/) NetBeans 웹사이트를 방문하고 다운로드 버튼을 클릭하여 NetBeans 소프트웨어를 다운로드합니다.

![NetBeans 웹사이트](.\images\netbeans_website.jpg)

**2단계 -** **다운로드를** 클릭하면 다양한 Java 애플리케이션용 NetBeans 번들을 제공하는 NetBeans 소프트웨어의 다운로드 페이지로 이동합니다. 다음 스크린샷과 같이 **JavaSE** 용 NetBeans 소프트웨어를 다운로드합니다 .

![NetBeans 다운로드 페이지](.\images\netbeans_download_page.jpg)

**3단계 - 이 버튼을 클릭하면** **netbeans-8.0-windows.exe** 라는 파일이 시스템에 다운로드됩니다. 설치하려면 이 파일을 실행하십시오. 이 파일을 실행하면 다음 스크린샷과 같이 NetBeans 설치 프로그램이 시작됩니다.

![설치 프로그램 구성](.\images\configuring_installer.jpg)

구성이 완료되면 **설치 프로그램의 시작 페이지가** 표시됩니다 .

**4단계** - 다음 버튼을 클릭하고 설치를 진행합니다.

![NetBeans IDE 설치 프로그램](.\images\netbeans_ide_installer.jpg)

**5단계** - 다음 창에는 **NETBEANS IDE 8.0 라이센스 계약이** 있습니다 . 주의 깊게 읽고 "I accept the terms in the license agreement" 확인란을 선택하여 계약에 동의한 후 **Next** 버튼을 클릭합니다.

![NetBeans 라이선스 계약](.\images\netbeans_license_agreement.jpg)

**6단계 - 다음 창에서** **Junit** 에 대한 사용권 계약이 표시됩니다 . "사용권 계약의 조건에 동의합니다. JUnit 설치" 라디오 버튼을 선택하여 동의하고 **다음을** 클릭합니다 .

![Junit 라이선스 계약 동의](.\images\accept_junit_license_agreement.jpg)

**7단계** - Netbeans 8.0을 설치해야 하는 대상 디렉토리를 선택합니다. 또한 시스템에서 **Java Development Kit가** 설치된 디렉토리를 탐색 하고 **다음** 버튼을 클릭할 수도 있습니다.

![NetBeans 설치 다음](.\images\netbeans_installation_next.jpg)

**8단계 - 마찬가지로** **Glassfish Server** 설치 를 위한 대상 디렉토리를 선택합니다 . Java Development Kit 디렉토리(현재는 Glassfish Reference용)를 탐색한 후 **다음 을** 클릭하십시오 .

![Glassfish 서버 설치](.\images\glassfish_server_installation.jpg)

**9단계** - **업데이트 확인** 상자에 자동 업데이트를 체크하고 설치 버튼을 클릭하여 설치를 시작합니다.

![설치 버튼 클릭](.\images\click_install_button.jpg)

**10단계** - 이 단계는 NetBeans IDE 8.0 설치를 시작하며 시간이 걸릴 수 있습니다.

![NetBeans IDE 8.0 설치](.\images\installation_of_netbeans_ide_8.jpg)

**Step 11** - 프로세스가 완료되면 **마침** 버튼을 클릭하여 설치를 마칩니다.

**12단계** - NetBeans IDE를 시작하면 다음 스크린샷과 같은 시작 페이지가 표시됩니다.

![시작 페이지](.\images\start_page.jpg)

**13단계** - 파일 메뉴에서 **새 프로젝트를** 선택하여 다음 스크린샷과 같이 새 프로젝트 마법사를 엽니다.

![NetBeans 파일](.\images\netbeans_file.jpg)

**14단계** - **새 프로젝트 마법사에서** **JavaFX를** 선택 하고 **다음을** 클릭합니다 . 새로운 JavaFX 애플리케이션 생성이 시작됩니다.

![새 프로젝트 JavaFX](.\images\new_project_select_javafx.jpg)

**15단계 -** **NewJavaFX 애플리케이션** 창 에서 프로젝트 이름과 프로젝트 위치를 선택한 다음 **마침을** 클릭합니다 . 주어진 이름으로 샘플 애플리케이션을 생성합니다.

![프로젝트 이름](.\images\project_name.jpg)

**이 경우 이름이 javafxsample** 인 애플리케이션이 생성됩니다. 이 애플리케이션 내에서 NetBeans IDE는 이름이 **Javafxsample.java** 인 Java 프로그램을 생성합니다 . 다음 스크린샷과 같이 이 프로그램은 NetBeans 소스 패키지 **→ javafxsample** 내부에 생성됩니다 .

![Javafx샘플 자바](.\images\javafxsample_java.jpg)

**16단계** - 파일을 마우스 오른쪽 버튼으로 클릭하고 **파일 실행을** 선택하여 다음 스크린샷과 같이 이 코드를 실행합니다.

![RunFile](.\images\runfile.jpg)

**자동으로 생성된 이 프로그램에는 Say 'Hello World'** 레이블이 있는 버튼이 있는 간단한 JavaFX 창을 생성하는 코드가 포함되어 있습니다 . 이 버튼을 클릭할 때마다 아래와 같이 **Hello World** 문자열이 콘솔에 표시됩니다.

![안녕 세계를 말해](.\images\say_hello_world.jpg)

## Eclipse에 JavaFX 설치

**e(fx)** clipse 라는 플러그인 도 JavaFX에서 사용할 수 있습니다. 다음 단계를 사용하여 Eclipse에서 JavaFX를 설정할 수 있습니다. 우선 시스템에 Eclipse가 있는지 확인하십시오. 그렇지 않은 경우 시스템에 Eclipse를 다운로드하여 설치합니다.

Eclipse가 설치되면 아래 단계에 따라 시스템에 **e(fx)clipse를 설치합니다.**

**1단계** - **도움말** 메뉴에서 Eclipse를 열고 아래와 같이 **새 소프트웨어 설치** ... 옵션을 선택합니다 .

![자바 이클립스](.\images\java_eclipse.jpg)

클릭하면 다음 스크린샷과 같이 **사용 가능한 소프트웨어** 창이 표시됩니다 .

![사용 가능한 소프트웨어 창](.\images\available_software_window.jpg)

**이 창의 작업** 텍스트 상자에서 필요한 소프트웨어에 대한 플러그인 링크를 제공해야 합니다.

**2단계** - **추가** … 버튼을 클릭합니다. 플러그인 이름을 **e(fx)clipse** 로 제공하십시오 . 다음으로 다음 링크를 위치로 제공하십시오. http://download.eclipse.org/efxclipse/updates-released/2.3.0/site/

**3단계** - 플러그인의 이름과 위치를 지정한 후 다음 스크린샷에 강조 표시된 대로 확인 버튼을 클릭합니다.

![저장소 추가](.\images\add_repository.jpg)

**4단계** - 플러그인을 추가한 직후 **e(fx)clipse – 설치** 및 **e(fx)clipse – 단일 구성 요소** 에 대한 두 개의 확인란을 찾을 수 있습니다 . 이 두 확인란을 모두 선택하고 다음 스크린샷과 같이 **추가 … 버튼을 클릭합니다.**

![e(fx)clipse 단일 구성 요소](.\images\e(fx)clipse_single_components.jpg)

**5단계** - 다음으로 Eclipse IDE를 엽니다. 파일 메뉴를 클릭하고 다음 스크린샷과 같이 프로젝트를 선택합니다.

![Eclipse IDE 파일 메뉴 클릭](.\images\eclipse_ide_click_file_menu .jpg)

**6단계** - 그런 다음 프로젝트를 생성하기 위해 Eclipse에서 제공하는 마법사 목록을 볼 수 있는 창이 나타납니다. **JavaFX** 마법사를 확장하고 **JavaFX 프로젝트를** 선택한 후 다음 스크린샷과 같이 **다음** 버튼을 클릭합니다 .

![새 프로젝트 선택 마법사](.\images\new_project_select_wizard.jpg)

**7단계** - **다음을** 클릭하면 새 프로젝트 마법사가 열립니다. 여기에서 필요한 프로젝트 이름을 입력하고 **완료를** 클릭할 수 있습니다 .

![프로젝트 이름 및 완료 클릭](.\images\project_name_and_click_finish.jpg)

**8단계** - 마침을 클릭하면 지정된 이름(샘플)으로 애플리케이션이 생성됩니다. **application** 이라는 하위 패키지에서 **Main.java** 라는 이름의 프로그램이 아래와 같이 생성됩니다.

![메인 자바](.\images\main_java.jpg)

**9단계** - 자동으로 생성된 이 프로그램에는 빈 JavaFX 창을 생성하는 코드가 포함되어 있습니다. 이 파일을 마우스 오른쪽 버튼으로 클릭하고 다음 스크린샷과 같이 **Run As → Java Application을 선택합니다.**

![자바 애플리케이션으로 실행](.\images\run_as_java_application.jpg)

이 응용 프로그램을 실행하면 아래와 같이 빈 JavaFX 창이 나타납니다.

![빈 JavaFX 창](.\images\empty_javafx_window.jpg)

**참고** - 이후 장에서 코드에 대해 자세히 설명합니다.

# JavaFX - 아키텍처

JavaFX는 풍부한 그래픽으로 GUI 응용 프로그램을 구축할 수 있는 풍부한 클래스 및 인터페이스 세트와 함께 완전한 API를 제공합니다. 이 API의 중요한 패키지는 다음과 같습니다.

- **javafx.animation** - 채우기, 페이드, 회전, 크기 조정 및 변환과 같은 전환 기반 애니메이션을 JavaFX 노드에 추가하는 클래스가 포함되어 있습니다.
- **javafx.application** - JavaFX 애플리케이션 수명 주기를 담당하는 클래스 세트를 포함합니다.
- **javafx.css** - JavaFX GUI 응용 프로그램에 CSS 스타일을 추가하는 클래스가 포함되어 있습니다.
- **javafx.event** - JavaFX 이벤트를 전달하고 처리하기 위한 클래스와 인터페이스를 포함합니다.
- **javafx.geometry** - 2D 개체를 정의하고 작업을 수행하는 클래스를 포함합니다.
- **javafx.stage** - 이 패키지는 JavaFX 애플리케이션의 최상위 컨테이너 클래스를 포함합니다.
- **javafx.scene** - 이 패키지는 장면 그래프를 지원하는 클래스와 인터페이스를 제공합니다. 또한 캔버스, 차트, 컨트롤, 효과, 이미지, 입력, 레이아웃, 미디어, 페인트, 모양, 텍스트, 변환, 웹 등과 같은 하위 패키지도 제공합니다. JavaFX의 풍부한 API를 지원하는 여러 구성 요소가 있습니다. .

다음 그림은 JavaFX API의 아키텍처를 보여줍니다. 여기에서 JavaFX API를 지원하는 구성 요소를 볼 수 있습니다.

![JavaFX API의 아키텍처](.\images\architecture_of_javafx_api.jpg)

## 장면 그래프

JavaFX에서 GUI 응용 프로그램은 장면 그래프를 사용하여 코딩되었습니다. 장면 그래프는 GUI 응용 프로그램 구성의 시작점입니다. 노드라고 하는 (GUI) 응용 프로그램 프리미티브를 보유합니다.

노드는 시각적/그래픽 개체이며 다음을 포함할 수 있습니다.

- **기하학적(그래픽) 객체** - 원, 직사각형, 다각형 등과 같은 (2D 및 3D)
- 버튼, 체크박스, 선택 상자, 텍스트 영역 등과 같은 **UI 컨트롤**
- **컨테이너** - 테두리 창, 격자 창, 흐름 창 등과 같은 (레이아웃 창)
- 오디오, 비디오 및 이미지 개체와 같은 **미디어 요소 .**

일반적으로 노드 모음은 장면 그래프를 만듭니다. 이러한 모든 노드는 아래와 같이 계층적 순서로 정렬됩니다.

![장면 그래프](.\images\scene_graph.jpg)

장면 그래프의 각 노드에는 단일 부모가 있으며 부모를 포함하지 않는 노드를 **루트 노드** 라고 합니다 .

같은 방식으로 모든 노드에는 하나 이상의 자식이 있으며 자식이 없는 노드를 **리프 노드** 라고 합니다 . **자식이 있는 노드를 분기 노드** 라고 합니다 .

노드 인스턴스는 장면 그래프에 한 번만 추가할 수 있습니다. 장면 그래프의 노드에는 효과, 불투명도, 변환, 이벤트 처리기, 이벤트 처리기, 응용 프로그램별 상태가 있을 수 있습니다.

## 프리즘

Prism은 JavaFX에서 그래픽을 렌더링하는 데 사용되는 **고성능 하드웨어 가속 그래픽 파이프라인 입니다.** 2D 및 3D 그래픽을 모두 렌더링할 수 있습니다.

그래픽을 렌더링하기 위해 Prism은 −를 사용합니다.

- Windows XP 및 Vista의 DirectX 9.
- Windows 7의 DirectX 11.
- Mac 및 Linux의 OpenGL, 임베디드 시스템.

시스템의 그래픽에 대한 하드웨어 지원이 충분하지 않은 경우 Prism은 소프트웨어 렌더링 경로를 사용하여 그래픽을 처리합니다.

지원되는 그래픽 카드 또는 GPU와 함께 사용하면 더 부드러운 그래픽을 제공합니다. 시스템이 그래픽 카드를 지원하지 않는 경우를 대비하여 Prism은 기본적으로 소프트웨어 렌더링 스택(위의 두 가지 중 하나)을 사용합니다.

## GWT(유리 창 도구 키트)

이름에서 알 수 있듯이 GWT는 Windows, 타이머, 표면 및 이벤트 대기열을 관리하는 서비스를 제공합니다. GWT는 JavaFX 플랫폼을 기본 운영 체제에 연결합니다.

## 양자 툴킷

Prism, Glass, Media Engine 및 Web Engine의 하위 수준 구성 요소에 대한 추상화입니다. Prism과 GWT를 함께 묶어 JavaFX에서 사용할 수 있게 합니다.

## 웹뷰

JavaFX를 사용하면 장면 그래프에 HTML 콘텐츠를 포함할 수도 있습니다. WebView는 이 콘텐츠를 처리하는 데 사용되는 JavaFX의 구성 요소입니다. 내부 오픈 소스 웹 브라우저 엔진인 **Web Kit** 라는 기술을 사용합니다 . 이 구성 요소는 HTML5, CSS, JavaScript, DOM 및 SVG와 같은 다양한 웹 기술을 지원합니다.

WebView를 사용하여 다음을 수행할 수 있습니다.

- 로컬 또는 원격 URL에서 HTML 콘텐츠를 렌더링합니다.
- 기록을 지원하고 뒤로 및 앞으로 탐색을 제공합니다.
- 콘텐츠를 다시 로드합니다.
- 웹 구성 요소에 효과를 적용합니다.
- HTML 콘텐츠를 편집합니다.
- JavaScript 명령을 실행합니다.
- 이벤트를 처리합니다.

일반적으로 WebView를 사용하면 Java에서 웹 콘텐츠를 제어할 수 있습니다.

## 미디어 엔진

JavaFX **미디어 엔진은** **Streamer** 로 알려진 오픈 소스 엔진을 기반으로 합니다 . 이 미디어 엔진은 비디오 및 오디오 콘텐츠 재생을 지원합니다.

JavaFX 미디어 엔진은 다음 파일 형식에 대한 오디오 지원을 제공합니다.

| **오디오** | MP3WAVAIFF |
| :--------: | ---------- |
| **동영상** | FLV        |

패키지 **javafx.scene.media** 에는 JavaFX에서 미디어 기능을 제공하는 클래스와 인터페이스가 포함되어 있습니다. 다음과 같은 세 가지 구성 요소의 형태로 제공됩니다.

- **Media Object** - 미디어 파일을 나타냅니다.
- **Media Player** - 미디어 콘텐츠를 재생합니다.
- **Media View** - 미디어를 표시합니다.

# JavaFX - 애플리케이션

이 장에서는 JavaFX 응용 프로그램의 구조에 대해 자세히 설명하고 예제를 통해 JavaFX 응용 프로그램을 만드는 방법도 배웁니다.

## JavaFX 애플리케이션 구조

일반적으로 JavaFX 응용 프로그램에는 다음 다이어그램과 같이 **스테이지, 장면** 및 **노드** 라는 세 가지 주요 구성 요소가 있습니다 .

![JavaFX 애플리케이션 구조](.\images\javafx_application_structure.jpg)

### 단계

스테이지(창)에는 JavaFX 애플리케이션의 모든 객체가 포함됩니다. **javafx.stage** 패키지의 **Stage** 클래스 로 표현됩니다 . 기본 단계는 플랫폼 자체에서 생성됩니다. **생성된 스테이지 객체는 Application 클래스의** **start()** 메서드 에 인수로 전달됩니다 (다음 섹션에서 설명).

스테이지에는 위치를 결정하는 두 개의 매개변수인 **Width** 및 **Height** 가 있습니다 . 콘텐츠 영역과 장식(제목 표시줄 및 테두리)으로 나뉩니다.

5가지 유형의 스테이지를 사용할 수 있습니다.

- 훌륭하게 꾸민
- 꾸미지 않은
- 투명한
- 통합
- 공익사업

스테이지의 내용을 표시하려면 **show()** 메서드를 호출해야 합니다 .

### 장면

장면은 JavaFX 응용 프로그램의 물리적 내용을 나타냅니다. 여기에는 장면 그래프의 모든 내용이 포함됩니다. **javafx.scene** 패키지의 **Scene** 클래스는 장면 객체를 나타냅니다. 인스턴스에서 장면 개체는 하나의 단계에만 추가됩니다.

장면 클래스를 인스턴스화하여 장면을 만들 수 있습니다. **루트 노드** 와 함께 크기(높이 및 너비)를 생성자에 전달하여 장면의 크기를 선택할 수 있습니다 .

### 장면 그래프 및 노드

장면 **그래프는** 장면의 내용을 나타내는 트리형 데이터 구조(계층적)입니다. 반대로 **노드는** 장면 그래프의 시각적/그래픽 개체입니다.

노드는 다음을 포함할 수 있습니다.

- − 원, 사각형, 다각형 등과 같은 기하학적(그래픽) 개체(2D 및 3D)
- − 버튼, 체크박스, 선택 상자, 텍스트 영역 등과 같은 UI 컨트롤
- Border Pane, Grid Pane, Flow Pane 등과 같은 컨테이너(레이아웃 창)
- 오디오, 비디오 및 이미지 개체와 같은 미디어 요소.

패키지 **javafx.scene** 의 노드 클래스 는 JavaFX의 노드를 나타내며 이 클래스는 모든 노드의 수퍼 클래스입니다 **.**

앞에서 설명한 것처럼 노드는 세 가지 유형입니다.

- **루트 노드** - 첫 번째 장면 그래프는 루트 노드로 알려져 있습니다.
- **분기 노드/부모 노드** - 자식 노드가 있는 노드를 분기/부모 노드라고 합니다. **javafx.scene** 패키지의 **Parent** 라는 추상 클래스는 모든 부모 노드의 기본 클래스이며 해당 부모 노드는 다음과 같은 유형입니다.
  - **그룹** - 그룹 노드는 하위 노드 목록을 포함하는 집단 노드입니다. 그룹 노드가 렌더링될 때마다 모든 하위 노드가 순서대로 렌더링됩니다. 그룹에 적용된 모든 변환, 효과 상태는 모든 하위 노드에 적용됩니다.
  - **Region** - Chart, Pane 및 Control과 같은 모든 JavaFX 노드 기반 UI 컨트롤의 기본 클래스입니다.
  - **WebView** - 이 노드는 웹 엔진을 관리하고 그 내용을 표시합니다.
- **리프 노드** - 자식 노드가 없는 노드를 리프 노드라고 합니다. 예를 들어 Rectangle, Ellipse, Box, ImageView, MediaView는 리프 노드의 예입니다.

장면 그래프에 루트 노드를 전달하는 것은 필수입니다. 그룹이 루트로 전달되면 모든 노드가 장면에 잘리고 장면 크기의 변경은 장면의 레이아웃에 영향을 미치지 않습니다.

## JavaFX 애플리케이션 생성

**JavaFX 애플리케이션을 만들려면 Application 클래스를 인스턴스화하고 해당 추상 메서드 start() 를** 구현해야 합니다 . 이 방법에서는 JavaFX 응용 프로그램에 대한 코드를 작성합니다.

### 애플리케이션 클래스

패키지 **javafx.application** 의 애플리케이션 **클래스** 는 JavaFX에서 애플리케이션의 진입점입니다. **JavaFX 응용 프로그램을 만들려면 이 클래스를 상속하고 해당 추상 메서드 start() 를** 구현해야 합니다 . 이 방법에서는 JavaFX 그래픽에 대한 전체 코드를 작성해야 합니다.

**기본** 메서드 에서는 **launch()** 메서드를 사용하여 애플리케이션을 시작해야 합니다 . 이 메서드는 다음 프로그램과 같이 Application 클래스의 **start() 메서드를 내부적으로 호출합니다.**

```java
public class JavafxSample extends Application {  
   @Override     
   public void start(Stage primaryStage) throws Exception { 
      /* 
      Code for JavaFX application. 
      (Stage, scene, scene graph) 
      */       
   }         
   public static void main(String args[]){           
      launch(args);      
   } 
} 
```

**start()** 메서드 내에서 일반적인 JavaFX 응용 프로그램을 만들려면 다음 단계를 따라야 합니다.

- 필요한 노드로 장면 그래프를 준비합니다.
- 필요한 크기의 장면을 준비하고 여기에 장면 그래프(장면 그래프의 루트 노드)를 추가합니다.
- 무대를 준비하고 무대에 장면을 추가하고 무대의 내용을 표시합니다.

### 장면 그래프 준비

애플리케이션에 따라 필요한 노드가 있는 장면 그래프를 준비해야 합니다. 루트 노드는 첫 번째 노드이므로 루트 노드를 생성해야 합니다. 루트 노드로서 **Group, Region 또는 WebView** 중에서 선택할 수 있습니다 .

**그룹** - 그룹 노드는 **javafx.scene** 패키지에 속하는 **그룹** 이라는 클래스로 표시되며 , 아래와 같이 이 클래스를 인스턴스화하여 그룹 노드를 만들 수 있습니다.

```java
Group root = new Group();
```

**Group** 클래스의 getChildren **()** 메서드는 노드를 보유하는 **ObservableList** 클래스 의 객체를 제공합니다 . 아래와 같이 이 개체를 검색하고 노드를 추가할 수 있습니다.

```java
//Retrieving the observable list object 
ObservableList list = root.getChildren(); 
       
//Setting the text object as a node  
list.add(NodeObject); 
```

아래와 같이 인스턴스화 시 Node 개체를 **Group** 클래스와 해당 생성자 에 전달하기만 하면 그룹에 Node 개체를 추가할 수도 있습니다 .

```java
Group root = new Group(NodeObject);
```

**지역** - 다음과 같은 모든 JavaFX 노드 기반 UI 컨트롤의 기본 클래스입니다.

- **차트 - 이 클래스는 모든 차트의 기본 클래스이며** **javafx.scene.chart** 패키지에 속합니다 .

  이 클래스에는 − **PieChart** 및 **XYChart** 라는 두 개의 하위 클래스가 있습니다 . 이 두 클래스에는 JavaFX에서 서로 다른 유형의 XY 평면 차트를 그리는 데 사용되는 **AreaChart, BarChart, BubbleChart** 등과 같은 하위 클래스가 있습니다 .

  이러한 클래스를 사용하여 애플리케이션에 차트를 포함할 수 있습니다.

- **Pane - Pane은** **AnchorPane, BorderPane, DialogPane** 등과 같은 모든 레이아웃 창의 기본 클래스입니다. 이 클래스는 - **javafx.scene.layout** 이라는 패키지에 속합니다 .

  이러한 클래스를 사용하여 애플리케이션에 미리 정의된 레이아웃을 삽입할 수 있습니다.

- **Control** - Accordion, ButtonBar, ChoiceBox, ComboBoxBase, HTMLEditor 등과 같은 사용자 인터페이스 컨트롤의 기본 클래스입니다. **이 클래스는 javafx.scene.control 패키지에 속합니다** .

  이러한 클래스를 사용하여 애플리케이션에 다양한 UI 요소를 삽입할 수 있습니다.

그룹에서 위에서 언급한 클래스를 인스턴스화하고 다음 프로그램과 같이 루트 노드로 사용할 수 있습니다.

```java
//Creating a Stack Pane 
StackPane pane = new StackPane();       
       
//Adding text area to the pane  
ObservableList list = pane.getChildren(); 
list.add(NodeObject);
```

**WebView** - 이 노드는 웹 엔진을 관리하고 그 내용을 표시합니다.

다음은 JavaFX의 노드 클래스 계층 구조를 나타내는 다이어그램입니다.

![웹뷰](.\images\webview.jpg)

### 장면 준비

**JavaFX 장면은 javafx.scene** 패키지의 **Scene** 클래스 로 표시됩니다 . 다음 코드 블록과 같이 이 클래스를 인스턴스화하여 장면을 만들 수 있습니다.

인스턴스화하는 동안 루트 개체를 장면 클래스의 생성자에 전달하는 것이 필수입니다.

```java
Scene scene = new Scene(root);
```

아래와 같이 장면의 높이와 너비를 나타내는 double 유형의 두 매개 변수를 전달할 수도 있습니다.

```java
Scene scene = new Scene(root, 600, 300);
```

### 무대 준비

이것은 모든 JavaFX 응용 프로그램의 컨테이너이며 응용 프로그램을 위한 창을 제공합니다. **javafx.stage** 패키지의 **Stage** 클래스 로 표현됩니다 . 이 클래스의 객체는 **Application 클래스의** **start()** 메서드 의 매개변수로 전달됩니다 .

이 개체를 사용하여 무대에서 다양한 작업을 수행할 수 있습니다. 주로 다음을 수행할 수 있습니다.

- **setTitle()** 메서드를 사용하여 스테이지의 제목을 설정합니다 .
- **setScene()** 메서드를 사용하여 장면 객체를 스테이지에 연결합니다 .
- 아래와 같이 **show()** 메서드를 사용하여 장면의 내용을 표시합니다 .

```java
//Setting the title to Stage. 
primaryStage.setTitle("Sample application"); 
       
//Setting the scene to Stage 
primaryStage.setScene(scene); 
       
//Displaying the stage 
primaryStage.show();
```

## JavaFX 애플리케이션의 라이프사이클

JavaFX 응용 프로그램 클래스에는 세 가지 수명 주기 메서드가 있습니다.

- **start()** - JavaFX 그래픽 코드가 작성되는 진입점 메서드입니다.
- **stop()** - 재정의할 수 있는 빈 메서드입니다. 여기에서 응용 프로그램을 중지하는 논리를 작성할 수 있습니다.
- **init()** - 재정의할 수 있는 빈 메서드이지만 이 메서드에서는 무대나 장면을 만들 수 없습니다.

이 외에도 JavaFX 응용 프로그램을 시작하기 위한 **launch() 라는 정적 메서드를 제공합니다.**

**launch()** 메서드는 정적 이므로 정적 컨텍스트(main 일반적으로)에서 호출해야 합니다. JavaFX 응용 프로그램이 시작될 때마다 다음 작업이 수행됩니다(동일한 순서로).

- 애플리케이션 클래스의 인스턴스가 생성됩니다.
- **Init()** 메서드가 호출됩니다.
- start **()** 메서드가 호출됩니다.
- 런처는 애플리케이션이 완료될 때까지 기다렸다가 **stop()** 메서드를 호출합니다.

### JavaFX 애플리케이션 종료

응용 프로그램의 마지막 창이 닫히면 JavaFX 응용 프로그램이 암시적으로 종료됩니다. 부울 값 "False"를 정적 메서드 **setImplicitExit()** (정적 컨텍스트에서 호출해야 함)에 전달하여 이 동작을 끌 수 있습니다.

**Platform.exit()** 또는 **System.exit** (int) 메서드를 사용하여 명시적으로 JavaFX 응용 프로그램을 종료할 수 있습니다 .

## 예제 1 – 빈 창 만들기

이 섹션에서는 빈 창을 표시하는 JavaFX 샘플 애플리케이션을 만드는 방법을 설명합니다. 다음은 단계입니다 -

### 1단계: 클래스 만들기

Java 클래스를 생성하고 **javafx.application** 패키지의 **Application** 클래스를 상속 하고 이 클래스의 start() 메서드를 다음과 같이 구현합니다.

```java
public class JavafxSample extends Application {  
   @Override     
   public void start(Stage primaryStage) throws Exception {      
   }    
} 
```

### 2단계: 그룹 개체 만들기

**start()** 메서드 에서 다음과 같이 **javafx.scene** 패키지에 속하는 Group이라는 클래스를 인스턴스화하여 그룹 개체를 만듭니다 .

```java
Group root = new Group();
```

### 3단계: 장면 개체 만들기

**javafx.scene** 패키지에 속하는 **Scene** 이라는 클래스를 인스턴스화하여 Scene을 만듭니다 . 이 클래스에 이전 단계에서 만든 그룹 개체 **(루트) 를 전달합니다.**

루트 객체 외에도 다음과 같이 Group 클래스의 객체와 함께 화면의 높이와 너비를 나타내는 두 개의 double 매개 변수를 전달할 수도 있습니다.

```java
Scene scene = new Scene(root,600, 300);
```

### 4단계: 스테이지 제목 설정

**Stage 클래스의** **setTitle()** 메서드를 사용하여 제목을 무대로 설정할 수 있습니다 . primaryStage **는** 장면 클래스의 시작 메서드에 매개 변수로 전달되는 Stage 객체입니다.

아래와 같이 **primaryStage** 객체를 사용하여 씬의 제목을 **Sample Application 으로 설정합니다.**

```java
primaryStage.setTitle("Sample Application");
```

### 5단계: 스테이지에 장면 추가

**Stage** 클래스의 **setScene()** 메서드를 사용하여 Scene 객체를 스테이지에 추가할 수 있습니다 . 아래와 같이 이 방법을 사용하여 이전 단계에서 준비한 Scene 객체를 추가합니다.

```java
primaryStage.setScene(scene);
```

### 6단계: 스테이지 콘텐츠 표시

다음과 같이 **Stage** 클래스 의 **show()** 메서드를 사용하여 장면의 내용을 표시합니다 .

```java
primaryStage.show();
```

### 7단계: 애플리케이션 실행

다음과 같이 기본 메서드에서 **Application** 클래스 의 정적 메서드 **launch()를 호출하여 JavaFX 응용 프로그램을 시작합니다.**

```java
public static void main(String args[]){   
   launch(args);      
}  
```

### 예

다음 프로그램은 빈 JavaFX 창을 생성합니다. **이 코드를 JavafxSample.java** 라는 이름으로 파일에 저장합니다.

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage;  

public class JavafxSample extends Application { 
   @Override     
   public void start(Stage primaryStage) throws Exception {            
      //creating a Group object 
      Group group = new Group(); 
       
      //Creating a Scene by passing the group object, height and width   
      Scene scene = new Scene(group ,600, 300); 
      
      //setting color to the scene 
      scene.setFill(Color.BROWN);  
      
      //Setting the title to Stage. 
      primaryStage.setTitle("Sample Application"); 
   
      //Adding the scene to Stage 
      primaryStage.setScene(scene); 
       
      //Displaying the contents of the stage 
      primaryStage.show(); 
   }    
   public static void main(String args[]){          
      launch(args);     
   }         
} 
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac JavafxSample.java 
java JavafxSample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![샘플 애플리케이션 일반](.\images\sample_application_plain.jpg)

## 예제 2 – 직선 그리기

이전 예제에서 빈 스테이지를 만드는 방법을 살펴보았으므로 이제 이 예제에서는 JavaFX 라이브러리를 사용하여 직선을 그려 보겠습니다.

다음은 단계입니다 -

### 1단계: 클래스 만들기

Java 클래스를 생성하고 **javafx.application** 패키지의 **Application 클래스를 상속하고 이 클래스의** **start()** 메서드를 다음과 같이 구현합니다 .

```java
public class DrawingLine extends Application {
   @Override     
   public void start(Stage primaryStage) throws Exception {     
   }    
} 
```

### 2단계: 라인 생성

**javafx.scene.shape** 패키지에 속하는 **Line** 클래스를 다음과 같이 인스턴스화하여 JavaFX에서 라인을 생성할 수 있습니다 .

```java
//Creating a line object         
Line line = new Line();
```

### 3단계: 속성을 선으로 설정

다음 코드 블록에 표시된 대로 각각의 setter 메서드를 사용하여 **startX, startY, endX** 및 **endY** 속성을 설정하여 XY 평면에 선을 그릴 좌표를 지정합니다 .

```java
line.setStartX(100.0); 
line.setStartY(150.0); 
line.setEndX(500.0); 
line.setEndY(150.0);
```

### 4단계: 그룹 개체 만들기

start() 메서드에서 javafx.scene 패키지에 속하는 Group이라는 클래스를 인스턴스화하여 그룹 개체를 만듭니다.

다음과 같이 그룹에 추가하기 위해 이전 단계에서 생성한 Line(노드) 개체를 Group 클래스의 생성자에 매개 변수로 전달합니다.

```java
Group root = new Group(line);
```

### 5단계: 장면 개체 만들기

**javafx.scene** 패키지에 속하는 **Scene** 이라는 클래스를 인스턴스화하여 Scene을 만듭니다 . 이 클래스에 이전 단계에서 만든 그룹 개체 **(루트) 를 전달합니다.**

루트 객체 외에도 다음과 같이 Group 클래스의 객체와 함께 화면의 높이와 너비를 나타내는 두 개의 double 매개 변수를 전달할 수도 있습니다.

```java
Scene scene = new Scene(group ,600, 300);
```

### 6단계: 스테이지 제목 설정

**Stage 클래스의** **setTitle()** 메서드를 사용하여 제목을 무대로 설정할 수 있습니다 . primaryStage **는** 장면 클래스의 시작 메서드에 매개 변수로 전달되는 Stage 객체입니다.

다음과 같이 **primaryStage** 객체를 사용하여 씬의 제목을 **Sample Application 으로 설정합니다.**

```java
primaryStage.setTitle("Sample Application");
```

### 7단계: 스테이지에 장면 추가

**Stage** 클래스의 **setScene()** 메서드를 사용하여 Scene 객체를 스테이지에 추가할 수 있습니다 . 다음과 같이 이 방법을 사용하여 이전 단계에서 준비한 Scene 객체를 추가합니다.

```java
primaryStage.setScene(scene);
```

### 8단계: 스테이지 콘텐츠 표시

다음과 같이 **Stage** 클래스 의 **show()** 메서드를 사용하여 장면의 내용을 표시합니다 .

```java
primaryStage.show();
```

### 9단계: 애플리케이션 실행

다음과 같이 기본 메서드에서 **Application** 클래스 의 정적 메서드 **launch()를 호출하여 JavaFX 응용 프로그램을 시작합니다.**

```java
public static void main(String args[]){   
   launch(args);      
} 
```

### 예

다음 프로그램은 JavaFX를 사용하여 직선을 생성하는 방법을 보여줍니다. **이 코드를 JavafxSample.java** 라는 이름으로 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.Line; 
import javafx.stage.Stage;  

public class DrawingLine extends Application{ 
   @Override 
   public void start(Stage stage) { 
      //Creating a line object 
      Line line = new Line(); 
         
      //Setting the properties to a line 
      line.setStartX(100.0); 
      line.setStartY(150.0); 
      line.setEndX(500.0); 
      line.setEndY(150.0); 
         
      //Creating a Group 
      Group root = new Group(line); 
         
      //Creating a Scene 
      Scene scene = new Scene(root, 600, 300); 
         
      //Setting title to the scene 
      stage.setTitle("Sample application"); 
         
      //Adding the scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of a scene 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
} 
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac DrawingLine.java 
java DrawingLine
```

실행 시 위 프로그램은 아래와 같이 직선을 표시하는 JavaFX 창을 생성합니다.

![드로잉 라인](.\images\drawing_line.jpg)

## 예 3 – 텍스트 표시

JavaFX 장면에 텍스트를 삽입할 수도 있습니다. 이 예제는 JavaFX에 텍스트를 삽입하는 방법을 보여줍니다.

다음은 단계입니다 -

### 1단계: 클래스 만들기

Java 클래스를 생성하고 **javafx.application** 패키지의 **Application 클래스를 상속하고 이 클래스의** **start()** 메서드를 다음과 같이 구현합니다 .

```java
public class DrawingLine extends Application {  
   @Override     
   public void start(Stage primaryStage) throws Exception {     
   } 
}
```

### 2단계: 텍스트 포함

**javafx.scene.shape** 패키지에 속하는 **Text** 라는 클래스를 인스턴스화하여 JavaFX 장면에 텍스트를 포함하고 이 클래스를 인스턴스화할 수 있습니다.

포함할 텍스트를 문자열 형식으로 전달하여 이 클래스를 인스턴스화할 수 있습니다. 또는 아래와 같이 기본 생성자를 사용하여 텍스트 개체를 만들 수 있습니다.

```java
//Creating a Text object 
Text text = new Text();
```

### 3단계: 글꼴 설정

**Text** 클래스 의 **setFont()** 메서드를 사용하여 글꼴을 텍스트로 설정할 수 있습니다 . 이 메서드는 글꼴 개체를 매개 변수로 허용합니다. 아래와 같이 주어진 텍스트의 글꼴을 45로 설정합니다.

```java
//Setting font to the text 
text.setFont(new Font(45)); 
```

### 4단계: 텍스트 위치 설정

다음과 같이 각각의 setter 메서드 **setX()** 및 **setY()** 를 사용하여 X,Y 좌표를 설정하여 XY 평면에서 텍스트의 위치를 설정할 수 있습니다 .

```java
//setting the position of the text 
text.setX(50); 
text.setY(150); 
```

### 5단계: 추가할 텍스트 설정

Text 클래스의 setText() 메서드를 사용하여 추가할 텍스트를 설정할 수 있습니다. 이 메서드는 추가할 텍스트를 나타내는 문자열 매개 변수를 허용합니다.

```java
text.setText("Welcome to Tutorialspoint");
```

### 6단계: 그룹 개체 만들기

**start()** 메서드 에서 **javafx.scene** 패키지에 속하는 Group이라는 클래스를 인스턴스화하여 그룹 개체를 만듭니다 .

다음과 같이 그룹에 추가하기 위해 이전 단계에서 만든 Text(노드) 개체를 Group 클래스의 생성자에 매개 변수로 전달합니다.

```java
Group root = new Group(text)
```

### 7단계: 장면 개체 만들기

**javafx.scene** 패키지에 속하는 **Scene** 이라는 클래스를 인스턴스화하여 Scene을 만듭니다 . 이 클래스에 이전 단계에서 만든 그룹 개체 **(루트) 를 전달합니다.**

루트 객체 외에도 다음과 같이 Group 클래스의 객체와 함께 화면의 높이와 너비를 나타내는 두 개의 double 매개 변수를 전달할 수도 있습니다.

```java
Scene scene = new Scene(group ,600, 300);
```

### 8단계: 스테이지 제목 설정

**Stage 클래스의** **setTitle()** 메서드를 사용하여 제목을 무대로 설정할 수 있습니다 . primaryStage **는** 장면 클래스의 시작 메서드에 매개 변수로 전달되는 Stage 객체입니다.

아래와 같이 **primaryStage** 객체를 사용하여 씬의 제목을 **Sample Application 으로 설정합니다.**

```java
primaryStage.setTitle("Sample Application");
```

### 9단계: 스테이지에 장면 추가

**Stage** 클래스의 **setScene()** 메서드를 사용하여 Scene 객체를 스테이지에 추가할 수 있습니다 . 다음과 같이 이 방법을 사용하여 이전 단계에서 준비한 Scene 객체를 추가합니다.

```java
primaryStage.setScene(scene);
```

### 10단계: 스테이지 콘텐츠 표시

다음과 같이 **Stage** 클래스 의 **show()** 메서드를 사용하여 장면의 내용을 표시합니다 .

```java
primaryStage.show();
```

### 11단계: 애플리케이션 실행

다음과 같이 기본 메서드에서 **Application** 클래스 의 정적 메서드 **launch()를 호출하여 JavaFX 응용 프로그램을 시작합니다.**

```java
public static void main(String args[]){ 
   launch(args);      
} 
```

### 예

다음은 JavaFX를 사용하여 텍스트를 표시하는 프로그램입니다. **이 코드를 이름이 DisplayingText.java** 인 파일에 저장하십시오 .

```java
import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
         
public class DisplayingText extends Application { 
   @Override 
   public void start(Stage stage) {       
      //Creating a Text object 
      Text text = new Text(); 
       
      //Setting font to the text 
      text.setFont(new Font(45)); 
       
      //setting the position of the text 
      text.setX(50); 
      text.setY(150);          
      
      //Setting the text to be added. 
      text.setText("Welcome to Tutorialspoint"); 
         
      //Creating a Group object  
      Group root = new Group(); 
       
      //Retrieving the observable list object 
      ObservableList list = root.getChildren(); 
       
      //Setting the text object as a node to the group object 
      list.add(text);       
               
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
       
      //Setting title to the Stage 
      stage.setTitle("Sample Application"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }   
   public static void main(String args[]){ 
      launch(args); 
   } 
} 
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```java
javac DisplayingText.java 
java DisplayingText
```

실행 시 위 프로그램은 아래와 같이 텍스트를 표시하는 JavaFX 창을 생성합니다.

![텍스트를 표시하는 JavaFX 창](.\images\javafx_window_displaying_text.jpg)

# JavaFX - 2D 도형

이전 장에서는 JavaFX의 기본 응용 프로그램을 살펴보았으며 빈 창을 만드는 방법과 JavaFX의 XY 평면에 선을 그리는 방법을 배웠습니다. 선 외에도 몇 가지 다른 2D 도형을 그릴 수 있습니다.

## 2D 모양

일반적으로 2D 도형은 XY 평면에 그릴 수 있는 기하학적 도형으로 Line, Rectangle, Circle 등이 있습니다.

JavaFX 라이브러리를 사용하여 다음을 그릴 수 있습니다.

- Line, Rectangle, Circle, Ellipse, Polygon, Polyline, Cubic Curve, Quad Curve, Arc와 같은 미리 정의된 모양.
- MoveTO 경로 요소, 선, 수평선, 수직선, 입방 곡선, 이차 곡선, 호와 같은 경로 요소.
- 이 외에도 SVG 경로를 파싱하여 2D 도형을 그릴 수도 있습니다.

위에서 언급한 각 2D 모양은 클래스로 표시되며 이러한 모든 클래스는 **javafx.scene.shape** 패키지에 속합니다 . **Shape** 라는 클래스는 JavaFX의 모든 2차원 도형의 기본 클래스입니다.

## 2D 모양 만들기

차트를 만들려면 다음이 필요합니다.

- 필요한 모양의 각 클래스를 인스턴스화합니다.
- 도형의 속성을 설정합니다.
- 모양 개체를 그룹에 추가합니다.

### 해당 클래스 인스턴스화

2차원 모양을 만들려면 먼저 해당 클래스를 인스턴스화해야 합니다.

예를 들어, 라인을 생성하려면 다음과 같이 Line이라는 클래스를 인스턴스화해야 합니다.

```java
Line line = new Line();
```

### 모양의 속성 설정

클래스를 인스턴스화한 후 setter 메서드를 사용하여 도형에 대한 속성을 설정해야 합니다.

예를 들어 선을 그리려면 선의 시작점과 끝점의 x 및 y 좌표를 전달해야 합니다. 다음과 같이 각각의 setter 메서드를 사용하여 이러한 값을 지정할 수 있습니다.

```java
//Setting the Properties of the Line 
line.setStartX(150.0f); 
line.setStartY(140.0f);         
line.setEndX(450.0f); 
line.setEndY(140.0f);
```

### 그룹에 도형 개체 추가

마지막으로 아래와 같이 도형의 객체를 생성자의 매개변수로 전달하여 그룹에 추가해야 합니다.

```java
//Creating a Group object  
Group root = new Group(line);
```

다음 표는 JavaFX에서 제공하는 다양한 셰이프(클래스) 목록을 제공합니다.

| S.No |                         모양 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [선](https://www.tutorialspoint.com/javafx/2dshapes_line.htm) 선은 두 점을 연결하는 기하학적 구조입니다. **javafx.scene.shape** 패키지의 Line 클래스는 XY **평면** 의 선을 나타냅니다. |
|  2   | [직사각형](https://www.tutorialspoint.com/javafx/2dshapes_rectangle.htm)일반적으로 직사각형은 모든 내각이 직각인 두 쌍의 평행하고 동시적인 면이 있는 4면 다각형입니다. **JavaFX에서 Rectangle은 Rectangle** 이라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  삼  | [둥근 사각형](https://www.tutorialspoint.com/javafx/2dshapes_rounded_rectangle.htm)JavaFX에서는 모서리가 뾰족하거나 아치형 모서리가 있는 직사각형을 그릴 수 있으며 모서리가 아치형인 직사각형을 둥근 직사각형이라고 합니다. |
|  4   | [원](https://www.tutorialspoint.com/javafx/2dshapes_circle.htm)원은 닫힌 루프를 형성하는 선이며 모든 점은 중심점에서 고정된 거리에 있습니다. **JavaFX에서 원은 Circle** 이라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  5   | [타원](https://www.tutorialspoint.com/javafx/2dshapes_ellipse.htm)타원은 각각 초점이라고 하는 두 점으로 정의됩니다. 타원의 어떤 점을 취하면 초점점까지의 거리의 합은 일정합니다. 타원의 크기는 이 두 거리의 합으로 결정됩니다.**JavaFX에서 타원은 Ellipse** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  6   | [다각형](https://www.tutorialspoint.com/javafx/2dshapes_polygon.htm)끝과 끝이 연결된 여러 동일 평면 선분에 의해 형성된 닫힌 모양. **JavaFX에서 폴리곤은 Polygon** 이라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  7   | [폴리라인](https://www.tutorialspoint.com/javafx/2dshapes_polyline.htm)폴리라인은 끝이 닫히지 않는다는 점을 제외하면 폴리곤과 동일합니다. 또는 하나 이상의 선분으로 구성된 연속 선입니다. **JavaFX에서 Polyline은 Polygon** 이라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  8   | [입방 곡선](https://www.tutorialspoint.com/javafx/2dshapes_cubic_curve.htm)**입방 곡선은 XY 평면의 베지어 파라메트릭 곡선으로 차수가 3인 곡선입니다. JavaFX에서 입방 곡선은 CubicCurve** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  9   | [쿼드커브](https://www.tutorialspoint.com/javafx/2dshapes_quad_curve.htm)2차 곡선은 XY 평면의 베지어 파라메트릭 곡선이며 차수가 2인 곡선입니다. JavaFX에서 QuadCurve는 QuadCurve라는 클래스로 표시됩니다. **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  10  | [호](https://www.tutorialspoint.com/javafx/2dshapes_arc.htm)호는 곡선의 일부입니다. **JavaFX에서 호는 Arc** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 .[아크의 종류](https://www.tutorialspoint.com/javafx/2dshapes_types_of_arc.htm)이 외에도 호의 **Open, Chord, Round** 세 가지 유형을 그릴 수 있습니다 . |
|  11  | [SVG경로](https://www.tutorialspoint.com/javafx/2dshapes_svgpath.htm)JavaFX에서는 SVG 경로를 구문 분석하여 이미지를 구성할 수 있습니다. **이러한 모양은 SVGPath** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . 이 클래스에는 String 데이터 유형의 **내용** 이라는 속성이 있습니다 . 이미지를 그려야 하는 SVG 경로 인코딩 문자열을 나타냅니다. |

## 경로 클래스를 통해 더 많은 모양 그리기

이전 섹션에서는 클래스를 인스턴스화하고 각 매개변수를 설정하여 미리 정의된 간단한 도형을 그리는 방법을 살펴보았습니다.

**그러나 이러한 미리 정의된 모양만으로는 javafx.shape 패키지** 에서 제공하는 프리미티브 이외의 복잡한 모양을 빌드하는 데 충분하지 않습니다 .

예를 들어 다음 다이어그램과 같이 그래픽 요소를 그리려는 경우 이러한 단순한 모양에 의존할 수 없습니다.

![경로 클래스](.\images\path_class.jpg)

### 경로 클래스

**이러한 복잡한 구조를 그리기 위해 JavaFX는 Path** 라는 클래스를 제공합니다 . 이 클래스는 도형의 기하학적 윤곽을 나타냅니다.

**moveTo, LineTo, HlineTo, VlineTo, ArcTo, QuadCurveTo, CubicCurveTo와 같은 다양한 경로 요소를** 포함하는 관찰 가능한 목록에 연결됩니다 .

인스턴스화할 때 이 클래스는 지정된 경로 요소를 기반으로 경로를 구성합니다.

다음과 같이 인스턴스화하는 동안 이 클래스에 경로 요소를 전달할 수 있습니다.

```java
Path myshape = new Path(pathElement1, pathElement2, pathElement3);
```

또는 다음과 같이 addAll() 메서드를 사용하여 관찰 가능한 목록을 얻고 모든 경로 요소를 추가할 수 있습니다.

```java
Path myshape = new Path(); 
myshape.getElements().addAll(pathElement1, pathElement2, pathElement3); 
```

다음과 같이 add() 메서드를 사용하여 요소를 개별적으로 추가할 수도 있습니다.

```java
Path myshape = new Path(); 
myshape.getElements().add(pathElement1);
```

### The Move to Path Element

경로 요소 MoveTo는 경로의 현재 위치를 지정된 지점으로 이동하는 데 사용됩니다. 일반적으로 경로 요소를 사용하여 그리는 도형의 시작점을 설정하는 데 사용됩니다.

이는 javafx.scene.shape 패키지의 LineTo라는 클래스로 표현됩니다. 이중 데이터 유형의 두 가지 속성, 즉 −

- **X** - 현재 위치에서 선을 그릴 점의 x 좌표.
- **Y** - 현재 위치에서 선을 그릴 점의 y 좌표.

다음과 같이 MoveTo 클래스를 인스턴스화하고 새 점의 x, y 좌표를 전달하여 경로로 이동 요소를 만들 수 있습니다.

```java
MoveTo moveTo = new MoveTo(x, y);
```

생성자에 값을 전달하지 않으면 새 점이 (0,0)으로 설정됩니다.

다음과 같이 각각의 setter 메서드를 사용하여 값을 x, y 좌표로 설정할 수도 있습니다.

```java
setX(value); 
setY(value); 
```

## 예 – 복잡한 경로 그리기

**이 예제에서는 Path, MoveTo** 및 **Line** 클래스를 사용하여 다음 도형을 그리는 방법을 보여줍니다 .

![복잡한 경로](.\images\complex_path.jpg)

**이름이 ComplexShape.java** 인 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
         
public class ComplexShape extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Creating a Path 
      Path path = new Path(); 
       
      //Moving to the starting point 
      MoveTo moveTo = new MoveTo(108, 71); 
        
      //Creating 1st line 
      LineTo line1 = new LineTo(321, 161);  
       
      //Creating 2nd line 
      LineTo line2 = new LineTo(126,232);       
       
      //Creating 3rd line 
      LineTo line3 = new LineTo(232,52);  
       
      //Creating 4th line 
      LineTo line4 = new LineTo(269, 250);   
       
      //Creating 4th line 
      LineTo line5 = new LineTo(108, 71);  
       
      //Adding all the elements to the path 
      path.getElements().add(moveTo); 
      path.getElements().addAll(line1, line2, line3, line4, line5);        
         
      //Creating a Group object  
      Group root = new Group(path); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Drawing an arc through a path");
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();         
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}       
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac ComplexShape.java 
java ComplexShape
```

위의 프로그램을 실행하면 아래와 같이 현재 위치에서 지정된 지점으로 호가 그려지는 JavaFX 창이 생성됩니다.

![경로를 통해 호 그리기](.\images\drawing_arc_through_path.jpg)

다음은 JavaFX에서 제공하는 다양한 경로 요소(클래스)입니다. **이러한 클래스는 javafx.shape** 패키지에 있습니다 . **이러한 모든 클래스는 PathElement** 클래스를 상속합니다 .

| S.No |                         모양 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [LineTo](https://www.tutorialspoint.com/javafx/2dshapes_lineto.htm)경로 요소 **선은** 현재 위치에서 지정된 좌표의 한 지점까지 직선을 그리는 데 사용됩니다. **LineTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  2   | [HlineTo](https://www.tutorialspoint.com/javafx/2dshapes_hlineto.htm)경로 요소 **HLineTo는** 현재 위치에서 지정된 좌표의 한 지점까지 수평선을 그리는 데 사용됩니다. **HLineTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  삼  | [VLineTo](https://www.tutorialspoint.com/javafx/2dshapes_vlineto.htm)경로 요소 **수직선은** 현재 위치에서 지정된 좌표의 한 지점까지 수직선을 그리는 데 사용됩니다. **VLineTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  4   | [QuadCurveTo](https://www.tutorialspoint.com/javafx/2dshapes_quadcurveto.htm)경로 요소 2차 곡선 은 현재 위치에서 지정된 좌표의 한 점까지 **2차 곡선을** 그리는 데 사용됩니다 . **QuadraticCurveTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  5   | [3차원 곡선으로](https://www.tutorialspoint.com/javafx/2dshapes_cubiccurveto.htm)경로 요소 **3차 곡선은** 현재 위치에서 지정된 좌표의 한 점까지 3차 곡선을 그리는 데 사용됩니다. **CubicCurveTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |
|  6   | [ArcTo](https://www.tutorialspoint.com/javafx/2dshapes_arcto.htm)경로 요소 **Arc는** 현재 위치에서 지정된 좌표의 한 점까지 호를 그리는 데 사용됩니다. **ArcTo** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 . |

## 2D 개체의 속성

모든 2차원 객체에 대해 채우기, 획, StrokeType 등과 같은 다양한 속성을 설정할 수 있습니다. 다음 섹션에서는 2D 객체의 다양한 속성에 대해 설명합니다.

- [스트로크 유형](https://www.tutorialspoint.com/javafx/stroke_type.htm)
- [스트로크 폭](https://www.tutorialspoint.com/javafx/stroke_width.htm)
- [획 채우기](https://www.tutorialspoint.com/javafx/stroke_fill.htm)
- [뇌졸중](https://www.tutorialspoint.com/javafx/stroke.htm)
- [스트로크 라인](https://www.tutorialspoint.com/javafx/stroke_line_join.htm)
- [스트로크 마이터 제한](https://www.tutorialspoint.com/javafx/stroke_miter_limit.htm)
- [스트로크 라인 캡](https://www.tutorialspoint.com/javafx/stroke_line_cap.htm)
- [매끄러운](https://www.tutorialspoint.com/javafx/smooth.htm)

## 2D 개체에 대한 작업

그룹에 두 개 이상의 도형을 추가하면 아래와 같이 첫 번째 도형이 두 번째 도형과 겹칩니다.

![2D 개체에 대한 작업](.\images\operations_on_2d_objects.jpg)

변환(회전, 크기 조정, 변환 등), 전환(애니메이션) 외에도 2D 개체에 합집합, 빼기 및 교차라는 세 가지 작업을 수행할 수 있습니다.

| S.No |                   Operation & Description                    |
| :--: | :----------------------------------------------------------: |
|  1   | Union Operation: 이 작업은 두 개 이상의 모양을 입력으로 사용하고 해당 모양이 차지하는 영역을 반환합니다. |
|  2   | Intersection Operation: 이 작업은 두 개 이상의 모양을 입력으로 사용하고 이들 사이의 교차 영역을 반환합니다. |
|  3   | [Subtraction Operation](https://www.tutorialspoint.com/javafx/2dshapes_subtraction_operation.htm)이 작업은 두 개 이상의 모양을 입력으로 사용합니다. 그런 다음 두 번째 도형과 겹치는 영역을 제외한 첫 번째 도형의 영역을 반환합니다. |

# JavaFX - Text

다양한 모양과 마찬가지로 JavaFX에서도 텍스트 노드를 만들 수 있습니다. 텍스트 노드는 javafx.scene.text 패키지에 속하는 Text라는 클래스로 표시됩니다.

이 클래스에는 JavaFX에서 텍스트를 만들고 모양을 수정하는 여러 속성이 포함되어 있습니다. 이 클래스는 javafx.scene.shape 패키지에 속하는 Shape 클래스도 상속합니다.

따라서 글꼴, 정렬, 줄 간격, 텍스트 등과 같은 텍스트의 속성 외에도 strokeFill, stroke, strokeWidth, strokeType 등과 같은 기본 도형 노드 속성도 상속합니다.

## Creating a Text Node

**javafx.scene.text** 패키지의 Text 클래스는 JavaFX의 텍스트 노드를 나타내므로 다음과 같이 이 클래스를 인스턴스화하여 텍스트를 만들 수 있습니다.

```java
Text text = new Text();
```

Text 클래스 는 생성될 텍스트를 나타내는 문자열 유형의 **text** 라는 속성을 포함합니다 .

Text 클래스를 인스턴스화한 후 아래와 같이 **setText()** 메서드 를 사용하여 이 속성에 값을 설정해야 합니다 .

```java
String text = "Hello how are you" 
Text.setText(text);
```

또한 다음 코드 블록에 표시된 것처럼 각각의 setter 메서드, 즉 **setX()** 및 **setY()** 를 사용하여 속성 x 및 y에 값을 지정하여 텍스트의 위치(원점)를 설정할 수 있습니다.

```java
text.setX(50); 
text.setY(50);
```

### 예

다음 프로그램은 JavaFX에서 텍스트 노드를 만드는 방법을 보여주는 예제입니다. **이름이 TextExample.java** 인 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.scene.text.Text; 
         
public class TextExample extends Application { 
   @Override 
   public void start(Stage stage) {       
      //Creating a Text object 
      Text text = new Text();      
      
      //Setting the text to be added. 
      text.setText("Hello how are you"); 
       
      //setting the position of the text 
      text.setX(50); 
      text.setY(50); 
         
      //Creating a Group object  
      Group root = new Group(text);   
               
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Sample Application"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
} 
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac TextExample.java 
java TextExample
```

실행 시 위의 프로그램은 다음과 같이 지정된 텍스트를 표시하는 JavaFX 창을 생성합니다.

![샘플 애플리케이션 텍스트](.\images\sample_application_text.jpg)

## 텍스트의 위치 및 글꼴

기본적으로 텍스트 클래스에 의해 생성된 텍스트는 글꼴…, 크기… 및 검은색입니다.

**setFont()** 메서드를 사용하여 텍스트의 글꼴 크기와 색상을 변경할 수 있습니다 . **이 메서드는 Font** 클래스 의 개체를 허용합니다 .

**javafx.scene.text** 패키지의 **Font** 클래스는 텍스트의 글꼴을 정의하는 데 사용됩니다. **이 클래스에는 font()** 라는 정적 메서드가 포함되어 있습니다 .

이 방법은 다음과 같은 네 가지 매개변수를 허용합니다.

- **family** - 문자열 유형이며 텍스트에 적용하려는 글꼴의 패밀리를 나타냅니다.
- **weight** - 이 속성은 글꼴의 두께를 나타냅니다. **− FontWeight.BLACK, FontWeight.BOLD, FontWeight.EXTRA_BOLD, FontWeight.EXTRA_LIGHT, LIGHT, MEDIUM, NORMAL, SEMI_BOLD, THIN** 9개의 값을 허용합니다 .
- **자세** - 이 속성은 글꼴 자세(일반 또는 기울임꼴)를 나타냅니다. **FontPosture.REGULAR** 및 **FontPosture.ITALIC** 두 값을 허용합니다 .
- **size** - 이 속성은 double 유형이며 글꼴의 크기를 나타냅니다.

다음 방법을 사용하여 텍스트에 글꼴을 설정할 수 있습니다.

```java
text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
```

### 예

다음 프로그램은 JavaFX에서 텍스트 노드의 글꼴을 설정하는 방법을 보여주는 예제입니다. 여기서는 글꼴을 Verdana로, 가중치를 굵게, 자세를 일반으로, 크기를 20으로 설정합니다.

**TextFontExample.java** 라는 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text; 
         
public class TextFontExample extends Application { 
   @Override 
   public void start(Stage stage) {       
      //Creating a Text object 
      Text text = new Text(); 
        
      //Setting font to the text 
      text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
       
      //setting the position of the text
      text.setX(50); 
      text.setY(130);          
      
      //Setting the text to be added. 
      text.setText("Hi how are you"); 
         
      //Creating a Group object  
      Group root = new Group(text);   
               
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Setting Font to the text"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}      
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac TextFontExample.java 
java TextFontExample 
```

실행 시 위의 프로그램은 다음과 같이 지정된 글꼴로 텍스트를 표시하는 JavaFX 창을 생성합니다.

![글꼴을 텍스트로 설정](.\images\setting_font_to_text.jpg)

## 획과 색상

Text 클래스는 패키지의 Shape 클래스도 상속합니다. 따라서 스트로크와 색상을 텍스트 노드로 설정할 수 있는 **javafx.scene.shape를 사용할 수 있습니다.**

다음과 같이 shape(상속) 클래스의 **setFill()** 메서드를 사용하여 텍스트에 색상을 설정할 수 있습니다 .

```java
text.setFill(Color.BEIGE); 
```

**마찬가지로 setStroke()** 메서드를 사용하여 텍스트의 획 색상을 설정할 수 있습니다 . 획의 너비는 다음과 같이 **setStrokeWidth() 메서드를 사용하여 설정할 수 있습니다.**

```java
//Setting the color 
text.setFill(Color.BROWN); 
        
//Setting the Stroke  
text.setStrokeWidth(2); 
       
//Setting the stroke color 
text.setStroke(Color.BLUE); 
```

### 예

다음 프로그램은 텍스트 노드의 색상, strokeWidth 및 strokeColor를 설정하는 방법을 보여주는 예제입니다. 이 코드에서는 획 색상을 파란색으로, 텍스트 색상을 갈색으로, 획 너비를 -2로 설정합니다.

**StrokeExample.java** 라는 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text; 
         
public class StrokeExample extends Application { 
   @Override 
   public void start(Stage stage) {       
      //Creating a Text object 
      Text text = new Text(); 
       
      //Setting font to the text 
      text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50)); 
       
      //setting the position of the text  
      text.setX(50); 
      text.setY(130);     
       
      //Setting the color 
      text.setFill(Color.BROWN); 
       
      //Setting the Stroke  
      text.setStrokeWidth(2); 
      
      // Setting the stroke color
      text.setStroke(Color.BLUE);        
      
      //Setting the text to be added. 
      text.setText("Hi how are you"); 
         
      //Creating a Group object  
      Group root = new Group(text);   
               
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Setting font to the text"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}      
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac StrokeExample.java 
java StrokeExample 
```

실행 시 위의 프로그램은 다음과 같이 지정된 스트로크 및 색상 속성을 가진 텍스트를 표시하는 JavaFX 창을 생성합니다.

![텍스트 스트로크 예](.\images\text_stroke_example.jpg)

## 텍스트에 장식 적용

취소선과 같은 장식을 적용할 수도 있습니다. 이 경우 한 줄이 텍스트를 통과합니다. **Text** 클래스 의 메서드를 사용하여 텍스트에 밑줄을 그을 수 있습니다 .

**setStrikethrough()** 메서드를 사용하여 텍스트를 취소할 수 있습니다 . 이것은 부울 값을 허용하고 다음 코드 상자에 표시된 대로 텍스트를 취소선으로 이 메서드에 값 **true를 전달합니다.**

```java
//Striking through the text 
text1.setStrikethrough(true); 
```

In the same way, you can underline a text by passing the value **true** to the method **setUnderLine()** as follows −

```java
//underlining the text     
text2.setUnderline(true);
```

### Example

다음 프로그램은 텍스트에 밑줄이나 취소선과 같은 장식을 적용하는 방법을 보여주는 예제입니다. 이 코드를 DecorationsExample.java라는 이름으로 파일에 저장합니다.

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text; 
         
public class DecorationsExample extends Application { 
   @Override 
   public void start(Stage stage) {       
      //Creating a Text_Example object 
      Text text1 = new Text("Hi how are you");       
      
      //Setting font to the text 
      text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
      
      //setting the position of the text 
      text1.setX(50); 
      text1.setY(75);     
      
      //Striking through the text 
      text1.setStrikethrough(true); 
       
      //Creating a Text_Example object  
      Text text2 = new Text("Welcome to Tutorialspoint");     
      
      //Setting font to the text 
      text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
      
      //setting the position of the text 
      text2.setX(50); 
      text2.setY(150);      
      
      //underlining the text     
      text2.setUnderline(true);  
         
      //Creating a Group object  
      Group root = new Group(text1, text2);   
               
      //Creating a scene object
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Decorations Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 Java 파일을 컴파일하고 실행합니다.

```bash
javac DecorationsExample.java 
java DecorationsExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다. −

![장식 예](.\images\decorations_example.jpg)

# JavaFX - Effects

효과는 그래픽의 모양을 향상시키는 작업입니다. JavaFX에서 효과는 노드의 모양을 시각적으로 향상시키기 위해 노드에 적용되는 알고리즘입니다. Node 클래스의 효과 속성은 효과를 지정하는 데 사용됩니다.

JavaFX에서는 bloom, blur 및 glow와 같은 다양한 효과를 노드에 설정할 수 있습니다. 이러한 각 효과는 클래스로 표시되며 이러한 모든 클래스는 javafx.scene.effect라는 패키지에서 사용할 수 있습니다..

## Applying Effects to a Node

setEffect() 메서드를 사용하여 노드에 효과를 적용할 수 있습니다. 이 메서드에는 효과의 개체를 전달해야 합니다.

노드에 효과를 적용하려면 다음을 수행해야 합니다. −

- 노드를 만듭니다.
- 적용해야 하는 효과의 각 클래스를 인스턴스화합니다.
- 효과의 속성을 설정합니다.
- setEffect() 메서드를 사용하여 노드에 효과를 적용합니다.

### Creating the Nodes

먼저 각각의 클래스를 인스턴스화하여 JavaFX 애플리케이션에서 노드를 생성합니다.

예를 들어 애플리케이션의 이미지에 글로우 효과를 적용하려는 경우입니다. 먼저 Image 클래스를 인스턴스화하여 이미지 노드를 만들고 아래와 같이 뷰를 설정해야 합니다.

```java
//Creating an image 
Image image = new Image("https://www.tutorialspoint.com/green/images/logo.png"); 
       
//Setting the image view 
ImageView imageView = new ImageView(image); 

//Setting the position of the image 
imageView.setX(100); 
imageView.setY(70);  

//setting the fit height and width of the image view 
imageView.setFitHeight(200);
imageView.setFitWidth(400); 

//Setting the preserve ratio of the image view 
imageView.setPreserveRatio(true);  
```

### 해당 클래스 인스턴스화

생성된 노드에 적용해야 하는 효과를 나타내는 클래스를 인스턴스화합니다.

예 - 글로우 효과를 적용하려면 다음 코드 상자에 표시된 대로 글로우 클래스를 인스턴스화해야 합니다. −

```java
Glow glow = new Glow();
```

### Setting the Properties of the Effect

클래스를 인스턴스화한 후 해당 setter 메서드를 사용하여 효과에 대한 속성을 설정해야 합니다.

예: 3차원 상자를 그리려면 너비, 높이 및 깊이를 전달해야 합니다. 아래와 같이 각각의 setter 메서드를 사용하여 이러한 값을 지정할 수 있습니다.

```java
//setting the level property 
glow.setLevel(0.9);
```

### 노드에 효과 추가

**마지막으로 setEffect()** 메서드를 사용하여 필요한 효과를 노드에 적용할 수 있습니다 . 예: 이미지 노드에 글로우 효과를 설정하려면 다음과 같이 글로우 클래스의 개체를 이 메서드에 전달해야 합니다.

```java
imageView.setEffect(glow); 
```

**JavaFX 효과** - 다음 표는 JavaFX에서 제공하는 다양한 효과(클래스) 목록을 제공합니다. **이러한 클래스는 javafx.scene.effect** 패키지에 있습니다 .

| S.No |                         모양 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [색상 조정](https://www.tutorialspoint.com/javafx/color_adjust_effect.htm)색상 조정 효과를 적용하여 이미지의 색상을 조정할 수 있습니다. 여기에는 각 픽셀의 **색조, 채도, 밝기** 및 **대비** 조정이 포함됩니다.**javafx.scene.effect** 패키지의 **ColorAdjust** 라는 클래스는 색상 조정 효과를 나타냅니다. |
|  2   | [색상 입력](https://www.tutorialspoint.com/javafx/color_input_effect.htm)색상 입력 효과는 직사각형을 그리고 색상으로 채우는 것과 동일한 출력을 제공합니다. 다른 효과와 달리 이 효과를 노드에 적용하면 사각형 상자만 표시됩니다(노드가 아님). 이 효과는 주로 다른 효과에 대한 입력으로 전달하는 데 사용됩니다.**javafx.scene.effect** 패키지의 **ColorInput** 이라는 클래스는 색상 입력 효과를 나타냅니다. |
|  삼  | [이미지 입력](https://www.tutorialspoint.com/javafx/image_input_effect.htm)JavaFX의 이미지 입력 효과는 이미지를 JavaFX 화면에 삽입하기만 하면 됩니다.색상 입력 효과와 마찬가지로(지정된 색상 사각형 영역을 다른 효과의 입력으로 전달하는 데 사용됨) 이미지 입력 효과는 지정된 이미지를 다른 효과의 입력으로 전달하는 데 사용됩니다.패키지 **javafx.scene.effect 의** **ImageInput** 이라는 클래스는 이미지 입력 효과를 나타냅니다. |
|  4   | [혼합하다](https://www.tutorialspoint.com/javafx/blend_effect.htm)일반적으로 블렌드는 두 가지 이상의 다른 사물이나 물질의 혼합물을 의미합니다. 이 블렌드 효과를 적용하면 동일한 위치에서 서로 다른 두 입력의 픽셀을 가져와 블렌드 **모드를** 기반으로 결합된 출력을 생성합니다 .**javafx.scene.effect** 패키지의 **Blend** 라는 클래스는 혼합 효과를 나타냅니다. |
|  5   | [꽃](https://www.tutorialspoint.com/javafx/bloom_effect.htm)블룸 효과를 적용하면 노드 일부의 픽셀이 빛납니다.**javafx.scene.effect** 패키지의 **Bloom** 이라는 클래스는 bloom 효과를 나타냅니다. |
|  6   | [불타는 듯한 빛깔](https://www.tutorialspoint.com/javafx/glow_effect.htm)블룸과 마찬가지로 글로우 효과는 주어진 입력 이미지를 빛나게 하며, 이 효과는 입력의 밝은 픽셀을 더 밝게 만듭니다.**javafx.scene.effect** 패키지의 **Glow** 라는 클래스는 글로우 효과를 나타냅니다. |
|  7   | [상자 흐림](https://www.tutorialspoint.com/javafx/box_blur_effect.htm)이 흐림 효과를 노드에 적용하면 명확하지 않게 됩니다. Box Blur는 JavaFX에서 제공하는 일종의 흐림 효과입니다. 이 효과에서는 노드에 흐림 효과를 적용할 때 간단한 상자 필터가 사용됩니다.**javafx.scene.effect** 패키지의 **BoxBlur** 라는 클래스는 boxblur 효과를 나타냅니다. |
|  8   | [가우스 흐림](https://www.tutorialspoint.com/javafx/gaussianblur_effect.htm)Just like Box Blur Gaussian is an effect to blur the nodes in JavaFX. The only difference in the **Gaussian Blur effect** is that a Gaussian convolution kernel is used to produce a blurring effect.The class named GaussianBlur of the package **javafx.scene.effect** represents the Gaussian Blur effect. |
|  9   | [MotionBlur](https://www.tutorialspoint.com/javafx/motionblur_effect.htm)Just like Gaussian Effects, Motion Blur is an effect to blur the nodes in JavaFX. It also uses a Gaussian convolution kernel to produce a blurring effect, but the difference is in this effect the Gaussian convolution kernel is used with a specified angle.The class named **MotionBlur** of the package **javafx.scene.effect** represents the Motion Blur effect. |
|  10  | [Reflection](https://www.tutorialspoint.com/javafx/reflection_effect.htm)JavaFX에서 노드에 리플렉션 효과를 적용하면 노드 하단에 리플렉션이 추가됩니다.패키지 **javafx.scene.effect 의** **Reflection** 이라는 클래스는 반사 효과를 나타냅니다. |
|  11  | [세피아톤](https://www.tutorialspoint.com/javafx/sepiatone_effect.htm)JavaFX(일반적인 이미지)에서 노드에 세피아 톤 효과를 적용하면 적갈색으로 톤이 지정됩니다.패키지 **javafx.scene.effect 의** **SepiaTone** 이라는 클래스는 세피아 톤 효과를 나타냅니다. |
|  12  | [그림자](https://www.tutorialspoint.com/javafx/shadow_effect.htm)이 효과는 가장자리가 흐린 지정된 노드의 복제본을 만듭니다.**javafx.scene.effect** 패키지 의 **Shadow** 클래스는 세피아 톤 효과를 나타냅니다. |
|  13  | [그림자](https://www.tutorialspoint.com/javafx/drop_shadow_effect.htm)이 효과를 노드에 적용하면 지정된 노드 뒤에 그림자가 생성됩니다.**javafx.scene.effect** 패키지의 **DropShadow** 라는 클래스는 그림자 효과를 나타냅니다. |
|  14  | [내부 그림자](https://www.tutorialspoint.com/javafx/inner_shadow_effect.htm)이 효과를 노드에 적용하면 노드 가장자리 내부에 그림자가 생성됩니다.패키지 **javafx.scene.effect 의** **InnerShadow** 라는 클래스는 내부 그림자 효과를 나타냅니다. |
|  15  | [조명](https://www.tutorialspoint.com/javafx/lighting_effect.htm)조명 효과는 광원의 빛을 시뮬레이션하는 데 사용됩니다. 광원에는 **point** , **distance** 및 **spot** 이라는 다양한 종류가 있습니다 .**javafx.scene.effect** 패키지의 **Lighting** 클래스는 조명 효과를 나타냅니다. |
|  16  | [빛.먼](https://www.tutorialspoint.com/javafx/lightdistant_effect.htm)이 효과를 노드에 적용하면 멀리 있는 광원에서 생성되는 것처럼 광원이 시뮬레이션됩니다.**원거리 광원** - 노드에서 멀리 떨어져 있는 광원입니다. 여기에서 빛은 소스에서 한 방향으로 감쇠됩니다.**javafx.scene.effect** 패키지의 **Light.Distant** 클래스는 원거리 광원을 나타냅니다. |
|  17  | [라이트.스팟](https://www.tutorialspoint.com/javafx/spot_light_source_effect.htm)이 효과를 노드에 적용하면 조명이 스포트라이트에 의해 생성되는 것처럼 조명이 시뮬레이트됩니다.**Spot 광원** - 이 광원의 빛은 모든 방향에서 감쇠됩니다. 빛의 강도는 광원에서 물체까지의 거리에 따라 달라집니다.**javafx.scene.effect** 패키지의 **Light.Spot** 클래스는 원거리 광원을 나타냅니다. |
|  18  | [포인트.스팟](https://www.tutorialspoint.com/javafx/point_spot_effect.htm)이 효과를 노드에 적용하면 점광원에서 생성되는 것처럼 조명이 시뮬레이션됩니다.**Point Light Source** - 이 광원의 빛은 단일 지점에서 모든 방향으로 감쇠됩니다. 빛의 강도는 광원에서 물체까지의 거리에 따라 달라집니다.**javafx.scene.effect 패키지의 Point.Spot** 클래스는 포인트 라이트를 나타냅니다. |

# JavaFX - 변환

변환은 규칙을 적용하여 일부 그래픽을 다른 것으로 변경하는 것을 의미합니다. **Translation, Scaling Up or Down, Rotation, Shearing** 등과 같은 다양한 유형의 변형을 가질 수 있습니다.

JavaFX를 사용하여 회전, 크기 조정 및 변환과 같은 노드에 변환을 적용할 수 있습니다. **이러한 모든 변환은 다양한 클래스로 표현되며 이들은 javafx.scene.transform** 패키지에 속합니다 .

| S.No |                         변환 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [회전](https://www.tutorialspoint.com/javafx/rotation_transformation.htm)회전 시 객체를 원점에서 특정 각도 **θ(세타) 로 회전합니다.** |
|  2   | [스케일링](https://www.tutorialspoint.com/javafx/scaling_transformation.htm)객체의 크기를 변경하기 위해 스케일링 변환이 사용됩니다. |
|  삼  | [번역](https://www.tutorialspoint.com/javafx/translation_transformation.htm)개체를 화면의 다른 위치로 이동합니다. |
|  4   | [깎기](https://www.tutorialspoint.com/javafx/shearing_transformation.htm)객체의 모양을 기울이는 변환을 전단 변환이라고 합니다. |

## 다중 변환

JavaFX의 노드에 여러 변환을 적용할 수도 있습니다. 다음 프로그램은 직사각형에 대해 **회전, 크기 조정** 및 **이동 변환을 동시에 수행하는 예제입니다.**

이 코드를 이름이 −인 파일에 저장합니다.

**MultipleTransformationsExample.java** .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.scene.transform.Rotate; 
import javafx.scene.transform.Scale; 
import javafx.scene.transform.Translate; 
import javafx.stage.Stage; 
         
public class MultipleTransformationsExample extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Rectangle
      Rectangle rectangle = new Rectangle(50, 50, 100, 75); 
      
      //Setting the color of the rectangle 
      rectangle.setFill(Color.BURLYWOOD); 
      
      //Setting the stroke color of the rectangle 
      rectangle.setStroke(Color.BLACK); 
       
      //creating the rotation transformation 
      Rotate rotate = new Rotate(); 
      
      //Setting the angle for the rotation 
      rotate.setAngle(20); 
      
      //Setting pivot points for the rotation 
      rotate.setPivotX(150); 
      rotate.setPivotY(225); 
       
      //Creating the scale transformation 
      Scale scale = new Scale(); 
      
      //Setting the dimensions for the transformation 
      scale.setX(1.5); 
      scale.setY(1.5); 
      
      //Setting the pivot point for the transformation 
      scale.setPivotX(300); 
      scale.setPivotY(135); 
       
      //Creating the translation transformation 
      Translate translate = new Translate();       
      
      //Setting the X,Y,Z coordinates to apply the translation 
      translate.setX(250); 
      translate.setY(0); 
      translate.setZ(0); 
       
      //Adding all the transformations to the rectangle 
      rectangle.getTransforms().addAll(rotate, scale, translate); 
        
      //Creating a Group object  
      Group root = new Group(rectangle); 
      
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Multiple transformations"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac MultipleTransformationsExample.java 
java MultipleTransformationsExample 
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![다중 변환](.\images\multiple_transformation.jpg)

## 3D 개체의 변환

3D 개체에 변형을 적용할 수도 있습니다. 다음은 3차원 상자를 회전하고 변환하는 예제입니다.

**이 코드를 RotationExample3D.java** 라는 이름으로 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.Box; 
import javafx.scene.transform.Rotate; 
import javafx.scene.transform.Translate; 
import javafx.stage.Stage; 
         
public class RotationExample3D extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Box 
      Box box = new Box();  
      
      //Setting the properties of the Box 
      box.setWidth(150.0); 
      box.setHeight(150.0);   
      box.setDepth(150.0);       
       
      //Creating the translation transformation 
      Translate translate = new Translate();       
      translate.setX(400); 
      translate.setY(150); 
      translate.setZ(25);  
       
      Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS); 
      Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS); 
      Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS); 
      rxBox.setAngle(30); 
      ryBox.setAngle(50); 
      rzBox.setAngle(30); 
      box.getTransforms().addAll(translate,rxBox, ryBox, rzBox); 
        
      //Creating a Group object  
      Group root = new Group(box); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a cylinder"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac RotationExample3D.java 
java RotationExample3D 
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![3D 회전](.\images\3d_rotation.jpg)

# JavaFX - 애니메이션

일반적으로 개체에 애니메이션을 적용하는 것은 빠른 표시를 통해 개체의 움직임에 대한 환상을 만드는 것을 의미합니다. JavaFX에서 노드는 시간 경과에 따라 속성을 변경하여 애니메이션을 적용할 수 있습니다. **JavaFX는 javafx.animation** 이라는 패키지를 제공합니다 . 이 패키지에는 노드를 애니메이션하는 데 사용되는 클래스가 포함되어 있습니다. 애니메이션은 이러한 모든 클래스의 기본 클래스입니다.

**JavaFX를 사용하여 Fade Transition** , **Fill Transition** , **Rotate Transition** , **Scale Transition** , **Stroke** Transition , **Translate Transition** , **Path Transition** , **Sequential Transition** , **Pause Transition** , **Parallel Transition** 등 의 애니메이션(전환)을 적용할 수 있습니다.

**이러한 모든 전환은 javafx.animation** 패키지의 개별 클래스로 표시됩니다 .

특정 애니메이션을 노드에 적용하려면 아래 단계를 따라야 합니다.

- 각 클래스를 사용하여 필수 노드를 만듭니다.
- 적용할 각각의 전환(애니메이션) 클래스를 인스턴스화합니다.
- 전환 속성을 설정하고
- **마지막으로 Animation** 클래스 의 **play()** 메서드를 사용하여 전환을 재생합니다 .

이 장에서는 기본 전환(Rotation, Scaling, Translation)의 예에 대해 설명합니다.

## 회전 전환

다음은 JavaFX에서 회전 전환을 보여주는 프로그램입니다. **RotateTransitionExample.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```java
import javafx.animation.RotateTransition; 
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Polygon; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class RotateTransitionExample extends Application { 
   @Override 
   public void start(Stage stage) {      
      //Creating a hexagon 
      Polygon hexagon = new Polygon();        
      
      //Adding coordinates to the hexagon 
      hexagon.getPoints().addAll(new Double[]{        
         200.0, 50.0, 
         400.0, 50.0, 
         450.0, 150.0,          
         400.0, 250.0, 
         200.0, 250.0,                   
         150.0, 150.0, 
      }); 
      //Setting the fill color for the hexagon 
      hexagon.setFill(Color.BLUE); 
       
      //Creating a rotate transition    
      RotateTransition rotateTransition = new RotateTransition(); 
      
      //Setting the duration for the transition 
      rotateTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      rotateTransition.setNode(hexagon);       
      
      //Setting the angle of the rotation 
      rotateTransition.setByAngle(360); 
      
      //Setting the cycle count for the transition 
      rotateTransition.setCycleCount(50); 
      
      //Setting auto reverse value to false 
      rotateTransition.setAutoReverse(false); 
      
      //Playing the animation 
      rotateTransition.play(); 
         
      //Creating a Group object   
      Group root = new Group(hexagon); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);   
      
      //Setting title to the Stage 
      stage.setTitle("Rotate transition example "); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
} 
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac RotateTransitionExample.java 
java RotateTransitionExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![회전 전환](.\images\rotate_transition.jpg)

## 스케일 전환

다음은 JavaFX에서 Scale Transition을 보여주는 프로그램입니다. **ScaleTransitionExample.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```java
import javafx.animation.ScaleTransition; 
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class ScaleTransitionExample extends Application {  
   @Override 
   public void start(Stage stage) {      
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(50.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20); 
       
      //Creating scale Transition 
      ScaleTransition scaleTransition = new ScaleTransition(); 
      
      //Setting the duration for the transition 
      scaleTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      scaleTransition.setNode(circle); 
      
      //Setting the dimensions for scaling 
      scaleTransition.setByY(1.5); 
      scaleTransition.setByX(1.5); 
      
      //Setting the cycle count for the translation 
      scaleTransition.setCycleCount(50); 
      
      //Setting auto reverse value to true 
      scaleTransition.setAutoReverse(false); 
      
      //Playing the animation 
      scaleTransition.play(); 
         
      //Creating a Group object  
      Group root = new Group(circle); 
         
      //Creating a scene object  
      Scene scene = new Scene(root, 600, 300); 
      
      //Setting title to the Stage 
      stage.setTitle("Scale transition example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac ScaleTransitionExample.java 
java ScaleTransitionExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![스케일 전환](.\images\scale_transition.jpg)

## 번역 전환

다음은 JavaFX에서 Translate Transition을 보여주는 프로그램입니다. **이 코드를 TranslateTransitionExample.java** 라는 이름으로 파일에 저장합니다 .

```java
import javafx.animation.TranslateTransition; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class TranslateTransitionExample extends Application { 
   @Override 
   public void start(Stage stage) {  
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(150.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(100.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20); 
       
      //Creating Translate Transition 
      TranslateTransition translateTransition = new TranslateTransition(); 
      
      //Setting the duration of the transition  
      translateTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      translateTransition.setNode(circle); 
      
      //Setting the value of the transition along the x axis. 
      translateTransition.setByX(300); 
      
      //Setting the cycle count for the transition 
      translateTransition.setCycleCount(50); 
      
      //Setting auto reverse value to false 
      translateTransition.setAutoReverse(false); 
      
      //Playing the animation 
      translateTransition.play(); 
         
      //Creating a Group object  
      Group root = new Group(circle); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Translate transition example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
javac TranslateTransitionExample.java 
java TranslateTransitionExample 
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![번역 전환](.\images\translate_transition.jpg)

이 외에도 JavaFX는 노드에서 더 많은 전환을 적용할 수 있는 클래스를 제공합니다. 다음은 JavaFX에서 지원하는 다른 종류의 전환입니다.

- [Fade, Fill, Stroke](https://www.tutorialspoint.com/javafx/javafx_geometrical_transitions.htm) 노드의 속성에 영향을 주는 전환
- 둘 이상의 기본 전환이 포함된 전환 [순차, 병렬, 일시 중지](https://www.tutorialspoint.com/javafx/javafx_sequential_parallel.htm)
- 지정된 경로를 따라 개체를 변환하는 전환 경로 [전환](https://www.tutorialspoint.com/javafx/javafx_path_transition.htm)

# JavaFX - 색상

애플리케이션에 색상을 적용하기 위해 JavaFX는 **javafx.scene.paint** 패키지 패키지에 다양한 클래스를 제공합니다. 이 패키지에는 Paint라는 추상 클래스가 포함되어 있으며 색상을 적용하는 데 사용되는 모든 클래스의 기본 클래스입니다.

이러한 클래스를 사용하여 다음 패턴으로 색상을 적용할 수 있습니다.

- **균일** - 이 패턴에서는 색상이 노드 전체에 균일하게 적용됩니다.
- **이미지 패턴** - 이미지 패턴으로 노드 영역을 채울 수 있습니다.
- **Gradient** − In this pattern, the color applied to the node varies from one point to the other. It has two kinds of gradients namely **Linear Gradient** and **Radial Gradient**.

All those node classes to which you can apply color such as **Shape, Text** (including Scene), have methods named **setFill()** and **setStroke()**. These will help to set the color values of the nodes and their strokes respectively.

These methods accept an object of type Paint. Therefore, to create either of these type of images, you need to instantiate these classes and pass the object as a parameter to these methods.

## Applying Color to the Nodes

노드에 균일한 색상 패턴을 설정하려면 다음과 같이 색상 클래스의 객체를 **setFill()** , **setStroke()** 메서드 에 전달해야 합니다.

```java
//Setting color to the text 
Color color = new Color.BEIGE 
text.setFill(color); 

//Setting color to the stroke 
Color color = new Color.DARKSLATEBLUE 
circle.setStroke(color);
```

위의 코드 블록에서 색상 클래스의 정적 변수를 사용하여 색상 개체를 만듭니다.

같은 방법으로 아래와 같이 색상의 RGB 값이나 HSB 표준 색상 또는 웹 해시 코드를 사용할 수도 있습니다.

```java
//creating color object by passing RGB values 
Color c = Color.rgb(0,0,255);   

//creating color object by passing HSB values
Color c = Color.hsb(270,1.0,1.0);  

//creating color object by passing the hash code for web 
Color c = Color.web("0x0000FF",1.0);
```

### 예

다음은 JavaFX에서 노드에 색상을 적용하는 방법을 보여주는 예입니다. 여기서는 원과 텍스트 노드를 만들고 색상을 적용합니다.

**ColorExample.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
         
public class ColorExample extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Circle 
      Circle circle = new Circle();    
      
      //Setting the properties of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(180.0f); 
      circle.setRadius(90.0f); 
       
      //Setting color to the circle 
      circle.setFill(Color.DARKRED);    
      
      //Setting the stroke width 
      circle.setStrokeWidth(3); 
      
      //Setting color to the stroke  
      circle.setStroke(Color.DARKSLATEBLUE);
      
      //Drawing a text 
      Text text = new Text("This is a colored circle"); 
      
      //Setting the font of the text 
      text.setFont(Font.font("Edwardian Script ITC", 50)); 
      
      //Setting the position of the text 
      text.setX(155); 
      text.setY(50); 
       
      //Setting color to the text 
      text.setFill(Color.BEIGE); 
      text.setStrokeWidth(2); 
      text.setStroke(Color.DARKSLATEBLUE); 
         
      //Creating a Group object  
      Group root = new Group(circle, text); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Color Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac ColorExample.java 
java ColorExample
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![색상 예](.\images\color_example.jpg)

## 노드에 이미지 패턴 적용

이미지 패턴을 노드에 적용하려면 **ImagePattern** 클래스를 인스턴스화하고 해당 객체를 **setFill()** , **setStroke()** 메서드에 전달합니다.

이 클래스의 생성자는 다음과 같은 6개의 매개변수를 허용합니다.

- **이미지** - 패턴을 만들려는 이미지의 개체입니다.
- **x 및 y** - 앵커 사각형의 원점 (x, y) 좌표를 나타내는 이중 변수.
- **높이 및 너비** - 패턴을 생성하는 데 사용되는 이미지의 높이와 너비를 나타내는 이중 변수.
- **isProportional** - 부울 변수입니다. 이 속성을 true로 설정하면 시작 및 끝 위치가 비례하도록 설정됩니다.

```java
ImagePattern radialGradient = new ImagePattern(dots, 20, 20, 40, 40, false); 
```

### 예

다음은 JavaFX의 노드에 이미지 패턴을 적용하는 방법을 보여주는 예입니다. 여기서는 원과 텍스트 노드를 만들고 이미지 패턴을 적용합니다.

**이름이 ImagePatternExample.java** 인 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image; 

import javafx.scene.paint.Color; 
import javafx.scene.paint.ImagePattern; 
import javafx.scene.paint.Stop; 

import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
         
public class ImagePatternExample extends Application { 
   @Override 
   public void start(Stage stage) {           
      //Drawing a Circle 
      Circle circle = new Circle();    
      
      //Setting the properties of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(180.0f); 
      circle.setRadius(90.0f); 
       
      //Drawing a text 
      Text text = new Text("This is a colored circle"); 
      
      //Setting the font of the text 
      text.setFont(Font.font("Edwardian Script ITC", 50)); 
      
      //Setting the position of the text
      text.setX(155); 
      text.setY(50); 
       
      //Setting the image pattern 
      String link = "https://encrypted-tbn1.gstatic.com" 
         + "/images?q=tbn:ANd9GcRQub4GvEezKMsiIf67U" 
         + "rOxSzQuQ9zl5ysnjRn87VOC8tAdgmAJjcwZ2qM";       
      
      Image image = new Image(link); 
      ImagePattern radialGradient = new ImagePattern(image, 20, 20, 40, 40, false); 
       
      //Setting the linear gradient to the circle and text 
      circle.setFill(radialGradient); 
      text.setFill(radialGradient); 
         
      //Creating a Group object  
      Group root = new Group(circle, text); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Image pattern Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac ImagePatternExample.java 
java ImagePatternExample 
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![이미지 패턴 예](.\images\image_pattern_example.jpg)

## 선형 그라데이션 패턴 적용

Linear Gradient Pattern을 노드에 적용하려면 **LinearGradient** 클래스를 인스턴스화하고 객체를 **setFill(), setStroke()** 메서드에 전달합니다.

이 클래스의 생성자는 다음과 같은 다섯 개의 매개변수를 허용합니다.

- **startX, startY** - 이러한 이중 속성은 그래디언트 시작점의 x 및 y 좌표를 나타냅니다.
- **endX, endY** - 이러한 이중 속성은 그래디언트 끝점의 x 및 y 좌표를 나타냅니다.
- **cycleMethod** - 이 인수는 시작점과 끝점으로 정의된 색상 그라데이션 범위 밖의 영역을 채우는 방법을 정의합니다.
- **비례** - 부울 변수입니다. **이 속성을 true** 로 설정하면 시작 위치와 끝 위치가 비율로 설정됩니다.
- **중지** - 이 인수는 그라데이션 선을 따라 색상 중지 지점을 정의합니다.

```java
//Setting the linear gradient 
Stop[] stops = new Stop[] { 
   new Stop(0, Color.DARKSLATEBLUE),  
   new Stop(1, Color.DARKRED)
};  
LinearGradient linearGradient = 
   new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops); 
```

### 예

다음은 JavaFX의 노드에 그래디언트 패턴을 적용하는 방법을 보여주는 예입니다. 여기서는 원과 텍스트 노드를 만들고 선형 그래디언트 패턴을 적용합니다.

**이름이 LinearGradientExample.java** 인 파일에 이 코드를 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 

import javafx.scene.paint.Color; 
import javafx.scene.paint.CycleMethod; 
import javafx.scene.paint.LinearGradient; 
import javafx.scene.paint.Stop; 

import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
         
public class LinearGradientExample extends Application { 
   @Override 
   public void start(Stage stage) {           
      //Drawing a Circle 
      Circle circle = new Circle();    
      
      //Setting the properties of the circle 
      circle.setCenterX(300.0f);  
      circle.setCenterY(180.0f); 
      circle.setRadius(90.0f); 
      
      //Drawing a text 
      Text text = new Text("This is a colored circle"); 
      
      //Setting the font of the text 
      text.setFont(Font.font("Edwardian Script ITC", 55)); 
      
      //Setting the position of the text 
      text.setX(140); 
      text.setY(50); 
       
      //Setting the linear gradient 
      Stop[] stops = new Stop[] { 
         new Stop(0, Color.DARKSLATEBLUE),  
         new Stop(1, Color.DARKRED)
      };  
      LinearGradient linearGradient = 
         new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops); 
       
      //Setting the linear gradient to the circle and text 
      circle.setFill(linearGradient); 
      text.setFill(linearGradient); 
         
      //Creating a Group object  
      Group root = new Group(circle, text); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Linear Gradient Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac LinearGradientExample.java 
java LinearGradientExample
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![선형 그라데이션](.\images\linear_gradient.jpg)

## 방사형 그라데이션 패턴 적용

Radial Gradient Pattern을 노드에 적용하려면 **GradientPattern** 클래스를 인스턴스화하고 객체를 **setFill(), setStroke()** 메서드에 전달합니다.

이 클래스의 생성자는 몇 가지 매개변수를 허용하며 그 중 일부는 다음과 같습니다.

- **startX, startY** - 이러한 이중 속성은 그래디언트 시작점의 x 및 y 좌표를 나타냅니다.
- **endX, endY** - 이러한 이중 속성은 그래디언트 끝점의 x 및 y 좌표를 나타냅니다.
- **cycleMethod** - 이 인수는 색상 그라디언트 범위 밖의 영역이 시작점과 끝점으로 정의되는 방법과 채워져야 하는 방법을 정의합니다.
- **비례** - 부울 변수입니다. **이 속성을 true** 로 설정하면 시작 및 끝 위치가 비율로 설정됩니다.
- **중지** - 이 인수는 그라데이션 선을 따라 색상 중지 지점을 정의합니다.

```java
//Setting the radial gradient 
Stop[] stops = new Stop[] { 
   new Stop(0.0, Color.WHITE),  
   new Stop(0.3, Color.RED), 
   new Stop(1.0, Color.DARKRED) 
};        

RadialGradient radialGradient = 
   new RadialGradient(0, 0, 300, 178, 60, false, CycleMethod.NO_CYCLE, stops);
```

### 예

다음은 JavaFX의 노드에 방사형 그래디언트 패턴을 적용하는 방법을 보여주는 예입니다. 여기에서는 원과 텍스트 노드를 만들고 그라디언트 패턴을 적용합니다.

**RadialGradientExample.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 

import javafx.scene.paint.Color; 
import javafx.scene.paint.CycleMethod; 
import javafx.scene.paint.RadialGradient;  
import javafx.scene.paint.Stop; 

import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text;   

public class RadialGradientExample extends Application {  
   @Override 
   public void start(Stage stage) { 
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the properties of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(180.0f); 
      circle.setRadius(90.0f);  
      
      //Drawing a text 
      Text text = new Text("This is a colored circle"); 
      
      //Setting the font of the text 
      text.setFont(Font.font("Edwardian Script ITC", 50)); 
      
      //Setting the position of the text 
      text.setX(155); 
      text.setY(50);  
      
      //Setting the radial gradient 
      Stop[] stops = new Stop[] { 
         new Stop(0.0, Color.WHITE),  
         new Stop(0.3, Color.RED), 
         new Stop(1.0, Color.DARKRED) 
      };        
      RadialGradient radialGradient = 
         new RadialGradient(0, 0, 300, 178, 60, false, CycleMethod.NO_CYCLE, stops);  
      
      //Setting the radial gradient to the circle and text 
      circle.setFill(radialGradient); 
      text.setFill(radialGradient);  
      
      //Creating a Group object  
      Group root = new Group(circle, text);  
      
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
      
      //Setting title to the Stage 
      stage.setTitle("Radial Gradient Example");  
      
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show(); 
   }
   public static void main(String args[]) { 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac RadialGradientExample.java 
java RadialGradientExample
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![방사형 그라데이션](.\images\radial_gradient.jpg)

# JavaFX - 이미지

**javafx.scene.image** 패키지의 JavaFX에서 제공하는 클래스를 사용하여 이미지를 로드하고 수정할 수 있습니다 . **JavaFX는 Bmp, Gif, Jpeg, Png** 와 같은 이미지 형식을 지원합니다 .

이 장에서는 JavaFX에 이미지를 로드하는 방법, 여러 보기에서 이미지를 투사하는 방법 및 이미지의 픽셀을 변경하는 방법을 설명합니다.

## 이미지 로드

**javafx.scene.image** 패키지의 **Image** 클래스를 인스턴스화하여 JavaFX에서 이미지를 로드할 수 있습니다 .

클래스 생성자에게 다음 중 하나를 전달해야 합니다.

- 로드할 이미지의 InputStream **개체 또는**
- 이미지의 URL을 포함하는 문자열 변수입니다.

```java
//Passing FileInputStream object as a parameter 
FileInputStream inputstream = new FileInputStream("C:\\images\\image.jpg"); 
Image image = new Image(inputstream); 
         
//Loading image from URL 
//Image image = new Image(new FileInputStream("url for the image));
```

**이미지를 로드한 후 다음과 같이 ImageView** 클래스를 인스턴스화하고 생성자에 이미지를 전달하여 이미지에 대한 보기를 설정할 수 있습니다 .

```java
ImageView imageView = new ImageView(image);
```

### 예

다음은 JavaFX에서 이미지를 로드하고 보기를 설정하는 방법을 보여주는 예입니다.

**이 코드를 ImageExample.java** 라는 이름으로 파일에 저장합니다 .

```java
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  

public class ImageExample extends Application {  
   @Override 
   public void start(Stage stage) throws FileNotFoundException {         
      //Creating an image 
      Image image = new Image(new FileInputStream("path of the image"));  
      
      //Setting the image view 
      ImageView imageView = new ImageView(image); 
      
      //Setting the position of the image 
      imageView.setX(50); 
      imageView.setY(25); 
      
      //setting the fit height and width of the image view 
      imageView.setFitHeight(455); 
      imageView.setFitWidth(500); 
      
      //Setting the preserve ratio of the image view 
      imageView.setPreserveRatio(true);  
      
      //Creating a Group object  
      Group root = new Group(imageView);  
      
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500);  
      
      //Setting title to the Stage 
      stage.setTitle("Loading an image");  
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show(); 
   }  
   public static void main(String args[]) { 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac ImageExample.java 
java ImageExample
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![이미지 로드 중](.\images\loading_image.jpg)

## 이미지의 여러 보기

동일한 장면에서 이미지에 대해 여러 보기를 설정할 수도 있습니다. 다음 프로그램은 JavaFX에서 장면의 이미지에 대한 다양한 보기를 설정하는 방법을 보여주는 예제입니다.

**이 코드를 MultipleViews.java 라는** 이름으로 파일에 저장합니다 .

```java
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;  
import javafx.scene.image.ImageView; 
import javafx.stage.Stage;  

public class MultipleViews extends Application {  
   @Override 
   public void start(Stage stage) throws FileNotFoundException {         
      //Creating an image 
      Image image = new Image(new FileInputStream("file path"));  
      
      //Setting the image view 1 
      ImageView imageView1 = new ImageView(image); 
      
      //Setting the position of the image 
      imageView1.setX(50); 
      imageView1.setY(25); 
      
      //setting the fit height and width of the image view 
      imageView1.setFitHeight(300); 
      imageView1.setFitWidth(250);         
      
      //Setting the preserve ratio of the image view 
      imageView1.setPreserveRatio(true); 
         
      //Setting the image view 2 
      ImageView imageView2 = new ImageView(image);
      
      //Setting the position of the image 
      imageView2.setX(350); 
      imageView2.setY(25); 
      
      //setting the fit height and width of the image view 
      imageView2.setFitHeight(150); 
      imageView2.setFitWidth(250);          
      
      //Setting the preserve ratio of the image view 
      imageView2.setPreserveRatio(true); 
         
      //Setting the image view 3 
      ImageView imageView3 = new ImageView(image);  
      
      //Setting the position of the image 
      imageView3.setX(350); 
      imageView3.setY(200); 
      
      //setting the fit height and width of the image view 
      imageView3.setFitHeight(100); 
      imageView3.setFitWidth(100);         
      
      //Setting the preserve ratio of the image view 
      imageView3.setPreserveRatio(true);  
      
      //Creating a Group object  
      Group root = new Group(imageView1, imageView2, imageView3);  
      
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 400);  
      
      //Setting title to the Stage 
      stage.setTitle("Multiple views of an image");  
      
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage
      stage.show(); 
   }  
   public static void main(String args[]) { 
      launch(args); 
   } 
}        
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac MultipleViews.java 
java MultipleViews
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![다중 보기](.\images\multiple_view.jpg)

## 픽셀 쓰기

**JavaFX는 PixelReader** 및 **PixelWriter** 클래스라는 클래스를 제공하여 이미지의 픽셀을 읽고 씁니다. WritableImage 클래스 **는** 쓰기 가능한 이미지를 만드는 데 사용됩니다.

다음은 이미지의 픽셀을 읽고 쓰는 방법을 보여주는 예입니다. 여기서는 이미지의 색상 값을 읽고 더 어둡게 만듭니다.

**이 코드를 WritingPixelsExample.java** 라는 이름으로 파일에 저장합니다 .

```java
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import javafx.application.Application; 

import javafx.scene.Group;  
import javafx.scene.Scene; 

import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 
import javafx.scene.image.PixelReader; 
import javafx.scene.image.PixelWriter; 
import javafx.scene.image.WritableImage; 

import javafx.scene.paint.Color; 
import javafx.stage.Stage;  

public class WritingPixelsExample extends Application {  
   @Override 
   public void start(Stage stage) throws FileNotFoundException {         
      //Creating an image 
      Image image = new Image(new FileInputStream("C:\\images\\logo.jpg")); 
      int width = (int)image.getWidth(); 
      int height = (int)image.getHeight(); 
         
      //Creating a writable image 
      WritableImage wImage = new WritableImage(width, height); 
         
      //Reading color from the loaded image 
      PixelReader pixelReader = image.getPixelReader(); 
      
      //getting the pixel writer 
      PixelWriter writer = wImage.getPixelWriter();           
      
      //Reading the color of the image 
      for(int y = 0; y < height; y++) { 
         for(int x = 0; x < width; x++) { 
            //Retrieving the color of the pixel of the loaded image   
            Color color = pixelReader.getColor(x, y); 
              
            //Setting the color to the writable image 
            writer.setColor(x, y, color.darker());              
         }     
      }      
      //Setting the view for the writable image 
      ImageView imageView = new ImageView(wImage); 
              
      //Creating a Group object  
      Group root = new Group(imageView);  
            
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500);  
            
      //Setting title to the Stage 
      stage.setTitle("Writing pixels ");  
            
      //Adding scene to the stage 
      stage.setScene(scene);  
            
      //Displaying the contents of the stage 
      stage.show();  
   }
   public static void main(String args[]) { 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```bash
Javac WritingPixelsExample.java 
java WritingPixelsExample 
```

실행 시 위 프로그램은 다음과 같이 JavaFX 창을 생성합니다.

![픽셀 쓰기](.\images\writing_pixels.jpg)

# JavaFX - 3D 도형

이전 장에서는 XY 평면에 2D 도형을 그리는 방법을 살펴보았습니다. 이러한 2D 도형 외에도 JavaFX를 사용하여 몇 가지 다른 3D 도형을 그릴 수 있습니다.

## 3D 모양

일반적으로 3D 도형은 XYZ 평면에 그릴 수 있는 기하학적 도형입니다. 여기에는 **Cylinder, Sphere** 및 **Box가** 포함됩니다 .

위에서 언급한 각 3D 모양은 클래스로 표시되며 이러한 모든 클래스는 **javafx.scene.shape** 패키지에 속합니다 . **Shape3D** 라는 클래스는 JavaFX의 모든 3차원 도형의 기본 클래스입니다.

## 3D 모양 만들기

3차원 모양을 만들려면 다음이 필요합니다.

- 필요한 3D 모양의 각 클래스를 인스턴스화합니다.
- 3D 모양의 속성을 설정합니다.
- 3D 모양 개체를 그룹에 추가합니다.

### 해당 클래스 인스턴스화

3차원 모양을 만들려면 먼저 해당 클래스를 인스턴스화해야 합니다. 예를 들어, 3D 상자를 생성하려면 다음과 같이 Box라는 클래스를 인스턴스화해야 합니다.

```java
Box box = new Box();
```

### 모양의 속성 설정

클래스를 인스턴스화한 후 setter 메서드를 사용하여 도형에 대한 속성을 설정해야 합니다.

예를 들어 3D 상자를 그리려면 너비, 높이, 깊이를 전달해야 합니다. 다음과 같이 각각의 setter 메서드를 사용하여 이러한 값을 지정할 수 있습니다.

```java
//Setting the properties of the Box 
box.setWidth(200.0); 
box.setHeight(400.0);   
box.setDepth(200.0);
```

### 그룹에 도형 개체 추가

마지막으로 아래와 같이 도형의 객체를 생성자의 매개변수로 전달하여 그룹에 추가해야 합니다.

```java
//Creating a Group object  
Group root = new Group(box);
```

다음 표는 JavaFX에서 제공하는 다양한 3D 도형 목록을 제공합니다.

| S.No |                         모양 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [상자](https://www.tutorialspoint.com/javafx/3dshape_box.htm)직육면체는 **길이** (깊이), **너비** 및 **높이가** 있는 3차원 모양입니다 .**JavaFX에서 3차원 상자는 Box** 라는 클래스로 표현됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 .이 클래스를 인스턴스화하면 JavaFX에서 Box 노드를 만들 수 있습니다.이 클래스에는 double 데이터 유형의 3가지 속성, 즉 −가 있습니다.**너비** - 상자의 너비입니다.**높이** - 상자의 높이.**깊이** - 상자의 깊이. |
|  2   | [실린더](https://www.tutorialspoint.com/javafx/3dshape_cylinder.htm)원통은 두 개의 평행한(대부분 원형) 밑면이 곡면으로 연결된 닫힌 솔리드입니다.그것은 두 가지 매개변수, 즉 원형 밑면의 **반지름** 과 원통 **높이** 로 설명됩니다 .**JavaFX에서 실린더는 Cylinder** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 .이 클래스를 인스턴스화하면 JavaFX에서 실린더 노드를 만들 수 있습니다. 이 클래스는 double 데이터 유형의 2가지 속성, 즉 −를 가집니다.**height** - 실린더의 높이.**반경** - 실린더의 반경. |
|  삼  | [구체](https://www.tutorialspoint.com/javafx/3dshape_sphere.htm)구는 3D 공간의 주어진 점에서 모두 동일한 거리 r에 있는 점 집합으로 정의됩니다. 이 거리 r은 구의 반지름이고 주어진 점은 구의 중심입니다.**JavaFX에서 구는 Sphere** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.shape** 패키지에 속합니다 .이 클래스를 인스턴스화하면 JavaFX에서 구체 노드를 만들 수 있습니다.이 클래스에는 double 데이터 유형의 **반지름** 이라는 속성이 있습니다 . 구의 반지름을 나타냅니다. |

## 3D 개체의 속성

모든 3차원 객체에 대해 Cull Face, Drawing Mode, Material과 같은 다양한 속성을 설정할 수 있습니다.

다음 섹션에서는 3D 개체의 속성에 대해 설명합니다.

### 컬 페이스

일반적으로 컬링은 모양의 방향이 잘못된 부분(보기 영역에 표시되지 않음)을 제거하는 것입니다.

**Cull Face 속성은 CullFace** 유형 이며 3D 모양의 Cull Face를 나타냅니다. 아래와 같이 **setCullFace()** 메서드를 사용하여 도형의 Cull Face를 설정할 수 있습니다 .

```java
box.setCullFace(CullFace.NONE);
```

도형의 획 유형은 다음과 같습니다.

- **없음** - 컬링이 수행되지 않습니다(CullFace.NONE).
- **Front** - 전면을 향한 모든 폴리곤이 컬링됩니다. (CullFace.FRONT).
- **Back** - 뒷면을 향한 모든 폴리곤이 컬링됩니다. (StrokeType.BACK).

기본적으로 3차원 모양의 컬 면은 뒷면입니다.

### 예

다음 프로그램은 구체의 다양한 컬 면을 보여주는 예제입니다. **이 코드를 SphereCullFace.java** 라는 이름으로 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.CullFace; 
import javafx.stage.Stage; 
import javafx.scene.shape.Sphere; 
         
public class SphereCullFace extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing Sphere1 
      Sphere sphere1 = new Sphere();
      
      //Setting the radius of the Sphere 
      sphere1.setRadius(50.0);   
      
      //Setting the position of the sphere 
      sphere1.setTranslateX(100); 
      sphere1.setTranslateY(150); 
      
      //setting the cull face of the sphere to front 
      sphere1.setCullFace(CullFace.FRONT); 
       
      //Drawing Sphere2 
      Sphere sphere2 = new Sphere(); 
      
      //Setting the radius of the Sphere 
      sphere2.setRadius(50.0);   
      
      //Setting the position of the sphere 
      sphere2.setTranslateX(300);  
      sphere2.setTranslateY(150); 
      
      //Setting the cull face of the sphere to back 
      sphere2.setCullFace(CullFace.BACK); 
             
      //Drawing Sphere3 
      Sphere sphere3 = new Sphere(); 
      
      //Setting the radius of the Sphere 
      sphere3.setRadius(50.0);   
      
      //Setting the position of the sphere 
      sphere3.setTranslateX(500); 
      sphere3.setTranslateY(150); 
      
      //Setting the cull face of the sphere to none 
      sphere2.setCullFace(CullFace.NONE);          
       
      //Creating a Group object  
      Group root = new Group(sphere1, sphere2, sphere3); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage
      stage.setTitle("Drawing a Sphere"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 Java 파일을 컴파일하고 실행합니다.

```java
javac SphereCullFace.java 
java SphereCullFace 
```

실행 시 위의 프로그램은 다음과 같이 각각 **FRONT, BACK** 및 **NONE** 컬 얼굴 값을 가진 세 개의 구를 표시하는 JavaFX 창을 생성합니다.

![컬 페이스](.\images\cull_faces.jpg)

### 그리기 모드

**DrawMode** 유형의 속성 이며 현재 3D 모양을 그리는 데 사용되는 그리기 모드를 나타냅니다. 다음과 같이 setDrawMode() 메서드를 사용하여 3D 도형을 그리는 그리기 모드를 선택할 수 있습니다.

```java
box.setDrawMode(DrawMode.FILL); 
```

JavaFX에서는 두 가지 그리기 모드를 선택하여 3D 도형을 그릴 수 있습니다.

- **채우기** - 이 모드는 2D 도형을 그리고 채웁니다(DrawMode.FILL).
- **선** - 이 모드는 선(DrawMode.LINE)을 사용하여 3D 모양을 그립니다.

기본적으로 3차원 도형의 그리기 모드는 채우기입니다.

### 예

다음 프로그램은 3D 상자의 다양한 그리기 모드를 보여주는 예제입니다. **BoxDrawMode.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.scene.Scene;  
import javafx.scene.shape.Box; 
import javafx.scene.shape.DrawMode; 
import javafx.stage.Stage; 
         
public class BoxDrawMode extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Box 
      Box box1 = new Box(); 
      
      //Setting the properties of the Box 
      box1.setWidth(100.0); 
      box1.setHeight(100.0);   
      box1.setDepth(100.0); 
      
      //Setting the position of the box 
      box1.setTranslateX(200); 
      box1.setTranslateY(150); 
      box1.setTranslateZ(0);
      
      //Setting the drawing mode of the box 
      box1.setDrawMode(DrawMode.LINE); 
       
      //Drawing a Box 
      Box box2 = new Box(); 
      
      //Setting the properties of the Box 
      box2.setWidth(100.0); 
      box2.setHeight(100.0);   
      box2.setDepth(100.0); 
      
      //Setting the position of the box 
      box2.setTranslateX(450); //450 
      box2.setTranslateY(150);//150 
      box2.setTranslateZ(300); 
  
      //Setting the drawing mode of the box 
      box2.setDrawMode(DrawMode.FILL);     
         
      //Creating a Group object   
      Group root = new Group(box1, box2); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
       
      //Setting camera 
      PerspectiveCamera camera = new PerspectiveCamera(false); 
      camera.setTranslateX(0); 
      camera.setTranslateY(0); 
      camera.setTranslateZ(0); 
      scene.setCamera(camera);  
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a Box"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```java
javac BoxDrawMode.java 
java BoxDrawMode 
```

실행 시 위의 프로그램은 다음과 같이 각각 그리기 모드 값이 LINE 및 FILL인 두 개의 상자를 표시하는 JavaFX 창을 생성합니다.

![그리기 모드](.\images\drawing_modes.jpg)

### 재료

**Cull Face 속성은 재질** 유형 이며 3D 모양의 재질 표면을 선택하는 데 사용됩니다. 다음과 같이 **setCullFace()** 메서드를 사용하여 3D 도형의 재질을 설정할 수 있습니다 .

```
cylinder.setMaterial(material);
```

이 메서드에 대해 위에서 언급한 것처럼 Material 유형의 개체를 전달해야 합니다. **javafx.scene.paint** 패키지의 PhongMaterial 클래스 는 이 클래스의 하위 클래스이며 Phong 음영 재질을 나타내는 7개의 속성을 제공합니다 **.** 이러한 속성의 세터 메서드를 사용하여 이러한 모든 유형의 재료를 3D 모양의 표면에 적용할 수 있습니다.

다음은 JavaFX에서 사용할 수 있는 자료 유형입니다.

- **bumpMap** - 이것은 RGB 이미지로 저장된 노멀 맵을 나타냅니다.
- **diffuseMap** - 확산 맵을 나타냅니다.
- **selfIlluminationMap** - 이 PhongMaterial의 자체 발광 맵을 나타냅니다.
- **specularMap** - 이 PhongMaterial의 반사 맵을 나타냅니다.
- **diffuseColor** - 이 PhongMaterial의 확산 색상을 나타냅니다.
- **specularColor** - 이 PhongMaterial의 반사 색상을 나타냅니다.
- **specularPower** - 이것은 이 PhongMaterial의 specular power를 나타냅니다.

기본적으로 3차원 모양의 재질은 확산 색상이 밝은 회색인 PhongMaterial입니다.

### 예

다음은 실린더에 다양한 재질을 표시하는 예입니다. **이 코드를 CylinderMaterials.java** 라는 이름으로 파일에 저장합니다 .

```java
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.scene.Scene; 
import javafx.scene.image.Image; 
import javafx.scene.paint.Color; 
import javafx.scene.paint.PhongMaterial; 
import javafx.scene.shape.Cylinder; 
import javafx.stage.Stage;

public class CylinderMaterials extends Application {  
   @Override 
   public void start(Stage stage) { 
      //Drawing Cylinder1 
      Cylinder cylinder1 = new Cylinder();         
   
      //Setting the properties of the Cylinder 
      cylinder1.setHeight(130.0f); 
      cylinder1.setRadius(30.0f);   
     
      //Setting the position of the Cylinder 
      cylinder1.setTranslateX(100); 
      cylinder1.setTranslateY(75); 
        
      //Preparing the phong material of type bump map  
      PhongMaterial material1 = new PhongMaterial();  
      material1.setBumpMap(new Image
         ("http://www.tutorialspoint.com/images/tplogo.gif"));   
      
      //Setting the bump map material to Cylinder1 
      cylinder1.setMaterial(material1);    
       
      //Drawing Cylinder2 
      Cylinder cylinder2 = new Cylinder();         
      
      //Setting the properties of the Cylinder 
      cylinder2.setHeight(130.0f); 
      cylinder2.setRadius(30.0f);   
      
      //Setting the position of the Cylinder 
      cylinder2.setTranslateX(200); 
      cylinder2.setTranslateY(75); 
       
      //Preparing the phong material of type diffuse map 
      PhongMaterial material2 = new PhongMaterial();
      material2.setDiffuseMap(new Image
         ("http://www.tutorialspoint.com/images/tp-logo.gif")); 
      
      //Setting the diffuse map material to Cylinder2 
      cylinder2.setMaterial(material2);         
       
      //Drawing Cylinder3 
      Cylinder cylinder3 = new Cylinder();         
      
      //Setting the properties of the Cylinder 
      cylinder3.setHeight(130.0f); 
      cylinder3.setRadius(30.0f);   
  
      //Setting the position of the Cylinder 
      cylinder3.setTranslateX(300); 
      cylinder3.setTranslateY(75); 
       
      //Preparing the phong material of type Self Illumination Map 
      PhongMaterial material3 = new PhongMaterial();  
      material3.setSelfIlluminationMap(new Image
         ("http://www.tutorialspoint.com/images/tp-logo.gif"));  
      
      //Setting the Self Illumination Map material to Cylinder3 
      cylinder3.setMaterial(material3);  
       
      //Drawing Cylinder4 
      Cylinder cylinder4 = new Cylinder();         
      
      //Setting the properties of the Cylinder 
      cylinder4.setHeight(130.0f); 
      cylinder4.setRadius(30.0f);   
      
      //Setting the position of the Cylinder 
      cylinder4.setTranslateX(400); 
      cylinder4.setTranslateY(75); 
       
      //Preparing the phong material of type Specular Map  
      PhongMaterial material4 = new PhongMaterial();  
      material4.setSpecularMap(new Image
         ("http://www.tutorialspoint.com/images/tp-logo.gif")); 
      
      //Setting the Specular Map material to Cylinder4 
      cylinder4.setMaterial(material4);  
       
      //Drawing Cylinder5 
      Cylinder cylinder5 = new Cylinder();         
      
      //Setting the properties of the Cylinder 
      cylinder5.setHeight(130.0f); 
      cylinder5.setRadius(30.0f);   
      
      //Setting the position of the Cylinder 
      cylinder5.setTranslateX(100); 
      cylinder5.setTranslateY(300); 
       
      //Preparing the phong material of type diffuse color 
      PhongMaterial material5 = new PhongMaterial();  
      material5.setDiffuseColor(Color.BLANCHEDALMOND); 
      
      //Setting the diffuse color material to Cylinder5 
      cylinder5.setMaterial(material5);   
       
      //Drawing Cylinder6  
      Cylinder cylinder6 = new Cylinder();         
      
      //Setting the properties of the Cylinder 
      cylinder6.setHeight(130.0f); 
      cylinder6.setRadius(30.0f);   
      
      //Setting the position of the Cylinder 
      cylinder6.setTranslateX(200); 
      cylinder6.setTranslateY(300); 
       
      //Preparing the phong material of type specular color 
      PhongMaterial material6 = new PhongMaterial();  
      
      //setting the specular color map to the material 
      material6.setSpecularColor(Color.BLANCHEDALMOND); 
      
      //Setting the specular color material to Cylinder6 
      cylinder6.setMaterial(material6);    
       
      //Drawing Cylinder7 
      Cylinder cylinder7 = new Cylinder();
      
      //Setting the properties of the Cylinder 
      cylinder7.setHeight(130.0f); 
      cylinder7.setRadius(30.0f);   
      
      //Setting the position of the Cylinder 
      cylinder7.setTranslateX(300); 
      cylinder7.setTranslateY(300); 
       
      //Preparing the phong material of type Specular Power 
      PhongMaterial material7 = new PhongMaterial();  
      material7.setSpecularPower(0.1); 
      
      //Setting the Specular Power material to the Cylinder 
      cylinder7.setMaterial(material7);         
      
      //Creating a Group object  
      Group root = new Group(cylinder1 ,cylinder2, cylinder3, 
      cylinder4, cylinder5, cylinder6, cylinder7); 
          
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 400); 
       
      //Setting camera 
      PerspectiveCamera camera = new PerspectiveCamera(false); 
      camera.setTranslateX(0); 
      camera.setTranslateY(0); 
      camera.setTranslateZ(-10); 
      scene.setCamera(camera); 
       
      //Setting title to the Stage 
      stage.setTitle("Drawing a cylinder"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
Javac CylinderMaterials.java 
java CylinderMaterials 
```

실행 시 위의 프로그램은 다음 스크린샷과 같이 각각 Materials, Bump Map, Diffuse Map, Self-Illumination Map, Specular Map, Diffuse Color, Specular Color, (BLANCHEDALMOND) Specular Power가 있는 7개의 실린더를 표시하는 JavaFX 창을 생성합니다. -

![실린더 재질](.\images\cylinder_material.jpg)

# JavaFX - 이벤트 처리

JavaFX에서는 GUI 응용 프로그램, 웹 응용 프로그램 및 그래픽 응용 프로그램을 개발할 수 있습니다. 이러한 애플리케이션에서는 사용자가 애플리케이션(노드)과 상호 작용할 때마다 이벤트가 발생했다고 합니다.

예를 들어, 버튼 클릭, 마우스 이동, 키보드로 문자 입력, 목록에서 항목 선택, 페이지 스크롤 등은 이벤트를 발생시키는 활동입니다.

## 이벤트 유형

이벤트는 크게 다음 두 가지 범주로 분류할 수 있습니다.

- **Foreground Events** - 사용자의 직접적인 상호 작용이 필요한 이벤트. 그래픽 사용자 인터페이스에서 그래픽 구성 요소와 상호 작용하는 사람의 결과로 생성됩니다. 예를 들어, 버튼 클릭, 마우스 이동, 키보드를 통한 문자 입력, 목록에서 항목 선택, 페이지 스크롤 등.
- **백그라운드 이벤트** - 최종 사용자의 상호 작용이 필요하지 않은 이벤트를 백그라운드 이벤트라고 합니다. 운영 체제 중단, 하드웨어 또는 소프트웨어 오류, 타이머 만료, 작업 완료는 백그라운드 이벤트의 예입니다.

## JavaFX의 이벤트

JavaFX는 다양한 이벤트를 처리하기 위한 지원을 제공합니다. **javafx.event** 패키지의 **Event** 라는 클래스는 이벤트의 기본 클래스입니다.

하위 클래스의 인스턴스는 이벤트입니다. JavaFX는 다양한 이벤트를 제공합니다. 그들 중 일부는 아래에 나열되어 있습니다.

- **마우스 이벤트** - 마우스를 클릭했을 때 발생하는 입력 이벤트입니다. **MouseEvent** 라는 클래스로 표현됩니다 . 여기에는 마우스 클릭, 마우스 눌림, 마우스 해제, 마우스 이동, 마우스 입력 대상, 마우스 종료 대상 등과 같은 작업이 포함됩니다.
- **키 이벤트** - 이것은 노드에서 발생한 키 스트로크를 나타내는 입력 이벤트입니다. **KeyEvent** 라는 클래스로 표현됩니다 . 이 이벤트에는 키 누름, 키 떼기 및 키 입력과 같은 작업이 포함됩니다.
- **Drag Event** - 마우스를 드래그할 때 발생하는 입력 이벤트입니다. **DragEvent** 라는 클래스로 표시됩니다 . 여기에는 드래그 입력, 드래그 드롭, 드래그 입력 대상, 드래그 종료 대상, 드래그 오버 등과 같은 작업이 포함됩니다.
- **Window Event** - 윈도우 보이기/숨기기 동작과 관련된 이벤트입니다. **WindowEvent** 라는 클래스로 표현됩니다 . 여기에는 창 숨기기, 창 표시, 창 숨김, 창 표시 등과 같은 작업이 포함됩니다.

## 이벤트 처리

이벤트 처리는 이벤트를 제어하고 이벤트가 발생하면 어떤 일이 발생해야 하는지 결정하는 메커니즘입니다. 이 메커니즘에는 이벤트가 발생할 때 실행되는 이벤트 핸들러로 알려진 코드가 있습니다.

JavaFX는 이벤트를 처리하기 위한 핸들러와 필터를 제공합니다. JavaFX에서 모든 이벤트는 -

- **대상** - 이벤트가 발생한 노드입니다. 대상은 창, 장면 및 노드일 수 있습니다.
- **소스** - 이벤트가 생성되는 소스가 이벤트의 소스가 됩니다. 위의 시나리오에서 마우스는 이벤트의 소스입니다.
- **유형** - 발생한 이벤트의 유형; 마우스 이벤트의 경우 – 마우스 눌림, 마우스 떼기가 이벤트의 종류입니다.

다음과 같이 그룹 개체를 사용하여 삽입된 Circle, Stop 및 Play 버튼이 있는 응용 프로그램이 있다고 가정합니다.

![샘플 애플리케이션](.\images\sample_application.jpg)

플레이 버튼을 클릭하면 소스는 마우스, 타겟 노드는 플레이 버튼, 생성되는 이벤트의 종류는 마우스 클릭이 됩니다.

## JavaFX의 이벤트 처리 단계

이벤트가 발생할 때마다 JavaFX는 다음 단계를 거칩니다.

### 노선 건설

이벤트가 생성될 때마다 이벤트의 기본/초기 경로는 **Event Dispatch 체인** 구성에 의해 결정됩니다 . 스테이지에서 소스 노드까지의 경로입니다.

다음은 위의 시나리오에서 재생 버튼을 클릭할 때 생성된 이벤트에 대한 이벤트 디스패치 체인입니다.

![재생 버튼](.\images\play_button.jpg)

### 이벤트 캡처 단계

이벤트 디스패치 체인이 생성된 후 애플리케이션의 루트 노드가 이벤트를 디스패치합니다. 이 이벤트는 디스패치 체인의 모든 노드로 이동합니다(위에서 아래로). 이러한 노드 중 하나라도 생성된 이벤트에 대해 등록된 **필터가** 있으면 실행됩니다. 디스패치 체인의 노드 중 생성된 이벤트에 대한 필터가 없는 경우 대상 노드로 전달되고 마지막으로 대상 노드가 이벤트를 처리합니다.

### 이벤트 버블링 단계

이벤트 버블링 단계에서 이벤트는 대상 노드에서 단계 노드로(아래에서 위로) 이동합니다. 이벤트 디스패치 체인의 노드 중 하나라도 생성된 이벤트에 대해 등록된 **핸들러가** 있으면 실행됩니다. 이러한 노드 중 이벤트를 처리할 핸들러가 없는 경우 이벤트는 루트 노드에 도달하고 최종적으로 프로세스가 완료됩니다.

### 이벤트 처리기 및 필터

이벤트 필터 및 핸들러는 이벤트를 처리하기 위한 애플리케이션 로직을 포함하는 필터 및 핸들러입니다. 노드는 둘 이상의 핸들러/필터에 등록할 수 있습니다. 부모-자식 노드의 경우 모든 자식 노드에 대해 기본적으로 처리되는 부모에 대한 공통 필터/핸들러를 제공할 수 있습니다.

위에서 언급한 바와 같이 이벤트 동안 처리는 실행되는 필터이고 이벤트 버블링 단계에서는 핸들러가 실행됩니다. 모든 핸들러와 필터는 **javafx.event** 패키지의 **EventHandler** 인터페이스를 구현합니다 .

## 이벤트 필터 추가 및 제거

**노드에 이벤트 필터를 추가하려면 Node** 클래스 의 **addEventFilter()** 메서드를 사용하여 이 필터를 등록해야 합니다 .

```
//Creating the mouse event handler 
EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
   @Override 
   public void handle(MouseEvent e) { 
      System.out.println("Hello World"); 
      circle.setFill(Color.DARKSLATEBLUE);  
   } 
};   
//Adding event Filter 
Circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
```

같은 방식으로 아래와 같이 removeEventFilter() 메서드를 사용하여 필터를 제거할 수 있습니다.

```
circle.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
```

## 이벤트 처리 예

다음은 이벤트 필터를 사용하여 JavaFX에서 이벤트 처리를 보여주는 예입니다. **이름이 EventFiltersExample.java** 인 파일에 이 코드를 저장합니다 .

```
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.event.EventHandler; 

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 

import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; 
import javafx.stage.Stage; 
         
public class EventFiltersExample extends Application { 
   @Override 
   public void start(Stage stage) {      
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(25.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20);      
       
      //Setting the text 
      Text text = new Text("Click on the circle to change its color"); 
      
      //Setting the font of the text 
      text.setFont(Font.font(null, FontWeight.BOLD, 15));     
      
      //Setting the color of the text 
      text.setFill(Color.CRIMSON); 
  
      //setting the position of the text 
      text.setX(150); 
      text.setY(50); 
       
      //Creating the mouse event handler 
      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
         @Override 
         public void handle(MouseEvent e) { 
            System.out.println("Hello World"); 
            circle.setFill(Color.DARKSLATEBLUE);
         } 
      };  
      //Registering the event filter 
      circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);   
       
      //Creating a Group object  
      Group root = new Group(circle, text); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
       
      //Setting the fill color to the scene 
      scene.setFill(Color.LAVENDER);  
      
      //Setting title to the Stage 
      stage.setTitle("Event Filters Example");       
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac EventFiltersExample.java 
java EventFiltersExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![색상 변경](.\images\change_color.jpg)

## 이벤트 처리기 추가 및 제거

노드에 이벤트 핸들러를 추가하려면 아래와 같이 **Node** 클래스 의 **addEventHandler() 메서드를 사용하여 이 핸들러를 등록해야 합니다.**

```
//Creating the mouse event handler 
EventHandler<javafx.scene.input.MouseEvent> eventHandler = 
   new EventHandler<javafx.scene.input.MouseEvent>() { 
   
   @Override 
   public void handle(javafx.scene.input.MouseEvent e) { 
      System.out.println("Hello World"); 
      circle.setFill(Color.DARKSLATEBLUE);             
   } 
};    
//Adding the event handler 
circle.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
```

같은 방식으로 아래와 같이 removeEventHandler() 메서드를 사용하여 이벤트 핸들러를 제거할 수 있습니다.

```
circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
```

### 예

다음 프로그램은 이벤트 핸들러를 사용하여 JavaFX에서 이벤트 처리를 보여주는 예제입니다.

**이름이 EventHandlersExample.java** 인 파일에 이 코드를 저장합니다 .

```
import javafx.animation.RotateTransition; 
import javafx.application.Application; 
import javafx.event.EventHandler; 

import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.scene.Scene; 
import javafx.scene.control.TextField; 
import javafx.scene.input.KeyEvent; 
import javafx.scene.paint.Color; 
import javafx.scene.paint.PhongMaterial; 

import javafx.scene.shape.Box; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;  
import javafx.scene.transform.Rotate; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class EventHandlersExample extends Application { 
   
   @Override 
   public void start(Stage stage) {
      //Drawing a Box 
      Box box = new Box(); 
      
      //Setting the properties of the Box 
      box.setWidth(150.0); 
      box.setHeight(150.0);   
      box.setDepth(100.0); 
       
      //Setting the position of the box 
      box.setTranslateX(350);  
      box.setTranslateY(150); 
      box.setTranslateZ(50); 
       
      //Setting the text 
      Text text = new Text("Type any letter to rotate the box, 
         and click on the box to stop the rotation"); 
      
      //Setting the font of the text 
      text.setFont(Font.font(null, FontWeight.BOLD, 15));     
      
      //Setting the color of the text 
      text.setFill(Color.CRIMSON); 
      
      //setting the position of the text 
      text.setX(20); 
      text.setY(50); 
       
      //Setting the material of the box 
      PhongMaterial material = new PhongMaterial();  
      material.setDiffuseColor(Color.DARKSLATEBLUE);  
      
      //Setting the diffuse color material to box 
      box.setMaterial(material);       
       
      //Setting the rotation animation to the box    
      RotateTransition rotateTransition = new RotateTransition(); 
      
      //Setting the duration for the transition 
      rotateTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      rotateTransition.setNode(box);       
      
      //Setting the axis of the rotation 
      rotateTransition.setAxis(Rotate.Y_AXIS); 
      
      //Setting the angle of the rotation
      rotateTransition.setByAngle(360); 
      
      //Setting the cycle count for the transition 
      rotateTransition.setCycleCount(50); 
      
      //Setting auto reverse value to false 
      rotateTransition.setAutoReverse(false);  
      
      //Creating a text filed 
      TextField textField = new TextField();   
      
      //Setting the position of the text field 
      textField.setLayoutX(50); 
      textField.setLayoutY(100); 
       
      //Handling the key typed event 
      EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() { 
         @Override 
         public void handle(KeyEvent event) { 
            //Playing the animation 
            rotateTransition.play(); 
         }           
      };              
      //Adding an event handler to the text feld 
      textField.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField); 
       
      //Handling the mouse clicked event(on box) 
      EventHandler<javafx.scene.input.MouseEvent> eventHandlerBox = 
         new EventHandler<javafx.scene.input.MouseEvent>() { 
         
         @Override 
         public void handle(javafx.scene.input.MouseEvent e) { 
            rotateTransition.stop();  
         } 
      }; 
      //Adding the event handler to the box  
      box.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerBox);
       
      //Creating a Group object
      Group root = new Group(box, textField, text); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);      
      
      //Setting camera 
      PerspectiveCamera camera = new PerspectiveCamera(false); 
      camera.setTranslateX(0); 
      camera.setTranslateY(0); 
      camera.setTranslateZ(0); 
      scene.setCamera(camera);  
      
      //Setting title to the Stage 
      stage.setTitle("Event Handlers Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac EventHandlersExample.java 
java EventHandlersExample
```

실행 시 위 프로그램은 아래와 같이 텍스트 필드와 3D 상자를 표시하는 JavaFX 창을 생성합니다.

![텍스트 필드](.\images\text_field.jpg)

여기서 텍스트 필드에 문자를 입력하면 3D 상자가 x축을 따라 회전하기 시작합니다. 상자를 다시 클릭하면 회전이 중지됩니다.

## 이벤트 처리를 위한 편의 메서드 사용

JavaFX의 일부 클래스는 이벤트 핸들러 속성을 정의합니다. 각각의 setter 메서드를 사용하여 이러한 속성에 값을 설정하면 이벤트 핸들러에 등록할 수 있습니다. 이러한 메서드를 편의 메서드라고 합니다.

이러한 메서드의 대부분은 Node, Scene, Window 등과 같은 클래스에 존재하며 모든 하위 클래스에서 사용할 수 있습니다.

예를 들어 버튼에 마우스 이벤트 리스너를 추가하려면 아래와 같이 편리한 메서드 **setOnMouseClicked()를 사용할 수 있습니다.**

```
playButton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
   public void handle(MouseEvent event) { 
      System.out.println("Hello World"); 
      pathTransition.play(); 
   } 
}));
```

### 예

다음 프로그램은 편의 메서드를 사용하여 JavaFX에서 이벤트 처리를 보여주는 예제입니다.

**이 코드를 ConvinienceMethodsExample.java** 라는 이름으로 파일에 저장합니다 .

```
import javafx.animation.PathTransition; 
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.event.EventHandler; 

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.paint.Color; 

import javafx.scene.shape.Circle; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class ConvinienceMethodsExample extends Application { 
   @Override 
   public void start(Stage stage) {      
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(25.0f);  
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20);      
       
      //Creating a Path 
      Path path = new Path(); 
      
      //Moving to the staring point 
      MoveTo moveTo = new MoveTo(208, 71);               
      
      //Creating 1st line 
      LineTo line1 = new LineTo(421, 161);        
      
      //Creating 2nd line 
      LineTo line2 = new LineTo(226,232); 
      
      //Creating 3rd line 
      LineTo line3 = new LineTo(332,52);        
      
      //Creating 4th line 
      LineTo line4 = new LineTo(369, 250);        
      
      //Creating 5th line 
      LineTo line5 = new LineTo(208, 71);       
      
      //Adding all the elements to the path 
      path.getElements().add(moveTo); 
      path.getElements().addAll(line1, line2, line3, line4, line5);     
      
      //Creating the path transition 
      PathTransition pathTransition = new PathTransition(); 
      
      //Setting the duration of the transition 
      pathTransition.setDuration(Duration.millis(1000));       
      
      //Setting the node for the transition 
      pathTransition.setNode(circle); 
      
      //Setting the path for the transition 
      pathTransition.setPath(path); 
      
      //Setting the orientation of the path 
      pathTransition.setOrientation(
         PathTransition.OrientationType.ORTHOGONAL_TO_TAN GENT);
      
      //Setting the cycle count for the transition 
      pathTransition.setCycleCount(50); 
      
      //Setting auto reverse value to true 
      pathTransition.setAutoReverse(false);
      
      //Creating play button 
      Button playButton = new Button("Play"); 
      playButton.setLayoutX(300); 
      playButton.setLayoutY(250); 
       
      circle.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>() { 
         @Override 
         public void handle(javafx.scene.input.MouseEvent e) { 
            System.out.println("Hello World"); 
            circle.setFill(Color.DARKSLATEBLUE);             
         } 
      });   
      playButton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) { 
            System.out.println("Hello World");  
            pathTransition.play(); 
         } 
      })); 
       
      //Creating stop button 
      Button stopButton = new Button("stop"); 
      stopButton.setLayoutX(250); 
      stopButton.setLayoutY(250); 
      
      stopButton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) { 
            System.out.println("Hello World"); 
            pathTransition.stop(); 
         } 
      }));
      //Creating a Group object  
      Group root = new Group(circle, playButton, stopButton); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
      scene.setFill(Color.LAVENDER);  
      
      //Setting title to the Stage 
      stage.setTitle("Convenience Methods Example");  
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac ConvinienceMethodsExample.java 
java ConvinienceMethodsExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다. 여기에서 재생 버튼을 클릭하여 애니메이션을 시작하고 중지 버튼을 클릭하여 애니메이션을 중지합니다.

![편리한 방법](.\images\convinience_method.jpg)

# JavaFX - UI 컨트롤

모든 사용자 인터페이스는 다음 세 가지 주요 측면을 고려합니다.

- **UI 요소** - 사용자가 결국 보고 상호 작용하는 핵심 시각적 요소입니다. JavaFX는 이 튜토리얼에서 다룰 기본적인 것부터 복잡한 것까지 다양한 널리 사용되는 공통 요소 목록을 제공합니다.
- **레이아웃** - 화면에서 UI 요소를 구성하는 방법을 정의하고 GUI(그래픽 사용자 인터페이스)에 최종 모양과 느낌을 제공합니다. 이 부분은 레이아웃 장에서 다룰 것입니다.
- **동작** - 사용자가 UI 요소와 상호 작용할 때 발생하는 이벤트입니다. 이 부분은 이벤트 처리 장에서 다룰 것입니다.

**JavaFX는 javafx.scene.control** 패키지에서 여러 클래스를 제공합니다 . 다양한 GUI 구성 요소(컨트롤)를 생성하기 위해 JavaFX는 날짜 선택기, 버튼 텍스트 필드 등과 같은 여러 컨트롤을 지원합니다.

각 컨트롤은 클래스로 표시됩니다. 해당 클래스를 인스턴스화하여 컨트롤을 만들 수 있습니다.

다음은 GUI가 JavaFX를 사용하여 설계되는 동안 일반적으로 사용되는 컨트롤 목록입니다.

| S.No |                         제어 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | **상표**Label 개체는 텍스트를 배치하기 위한 구성 요소입니다. |
|  2   |      **단추**이 클래스는 레이블이 있는 단추를 만듭니다.      |
|  삼  | **색상 선택기**ColorPicker는 사용자가 색상을 조작하고 선택할 수 있도록 설계된 컨트롤 창을 제공합니다. |
|  4   | **체크박스**CheckBox는 on(true) 또는 off(false) 상태일 수 있는 그래픽 구성 요소입니다. |
|  5   | **라디오 버튼**RadioButton 클래스는 그룹에서 ON(참) 또는 OFF(거짓) 상태일 수 있는 그래픽 구성 요소입니다. |
|  6   | **목록보기**ListView 구성 요소는 사용자에게 텍스트 항목의 스크롤 목록을 제공합니다. |
|  7   | **텍스트 필드**TextField 객체는 한 줄의 텍스트를 편집할 수 있는 텍스트 구성 요소입니다. |
|  8   | **비밀번호 필드**PasswordField 개체는 암호 입력에 특화된 텍스트 구성 요소입니다. |
|  9   | **스크롤 바**Scrollbar 컨트롤은 사용자가 값 범위에서 선택할 수 있도록 스크롤 막대 구성 요소를 나타냅니다. |
|  10  | **파일 선택기**FileChooser 컨트롤은 사용자가 파일을 선택할 수 있는 대화 상자 창을 나타냅니다. |
|  11  | **진행 표시 줄**작업이 완료될 때까지 진행률 표시줄에 작업의 완료율이 표시됩니다. |
|  12  | **슬라이더**슬라이더를 사용하면 제한된 간격 내에서 손잡이를 밀어 사용자가 그래픽으로 값을 선택할 수 있습니다. |

## 예

다음 프로그램은 JavaFX에서 로그인 페이지를 표시하는 예제입니다. 여기에서는 제어 **레이블, 텍스트 필드, 비밀번호 필드** 및 **버튼을** 사용하고 있습니다 .

**이 코드를 LoginPage.java 라는** 이름으로 파일에 저장합니다 .

```
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
         
public class LoginPage extends Application { 
   @Override 
   public void start(Stage stage) {      
      //creating label email 
      Text text1 = new Text("Email");       
      
      //creating label password 
      Text text2 = new Text("Password"); 
       
      //Creating Text Filed for email        
      TextField textField1 = new TextField();       
      
      //Creating Text Filed for password        
      PasswordField textField2 = new PasswordField();  
       
      //Creating Buttons 
      Button button1 = new Button("Submit"); 
      Button button2 = new Button("Clear");  
      
      //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      
      //Setting size for the pane 
      gridPane.setMinSize(400, 200); 
      
      //Setting the padding  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
      gridPane.add(text1, 0, 0); 
      gridPane.add(textField1, 1, 0); 
      gridPane.add(text2, 0, 1);       
      gridPane.add(textField2, 1, 1); 
      gridPane.add(button1, 0, 2); 
      gridPane.add(button2, 1, 2); 
       
      //Styling nodes  
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
       
      text1.setStyle("-fx-font: normal bold 20px 'serif' "); 
      text2.setStyle("-fx-font: normal bold 20px 'serif' ");  
      gridPane.setStyle("-fx-background-color: BEIGE;"); 
       
      //Creating a scene object 
      Scene scene = new Scene(gridPane); 
       
      //Setting title to the Stage 
      stage.setTitle("CSS Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac LoginPage.java 
java LoginPage 
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![CSS 예제](.\images\css_example.jpg)

다음 프로그램은 **Date Picker, Radio Button, Toggle Button, Check Box, List View, Choice List** 등과 같은 JavaFX의 컨트롤을 보여주는 등록 양식의 예입니다.

**Registration.java** 라는 이름으로 이 코드를 파일에 저장합니다 .

```
import javafx.application.Application; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList; 

import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.DatePicker; 
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.scene.control.ToggleGroup;  
import javafx.scene.control.ToggleButton; 
import javafx.stage.Stage; 
         
public class Registration extends Application { 
   @Override 
   public void start(Stage stage) {    
      //Label for name 
      Text nameLabel = new Text("Name"); 
      
      //Text field for name 
      TextField nameText = new TextField(); 
       
      //Label for date of birth 
      Text dobLabel = new Text("Date of birth"); 
      
      //date picker to choose date 
      DatePicker datePicker = new DatePicker(); 
       
      //Label for gender
      Text genderLabel = new Text("gender"); 
      
      //Toggle group of radio buttons       
      ToggleGroup groupGender = new ToggleGroup(); 
      RadioButton maleRadio = new RadioButton("male"); 
      maleRadio.setToggleGroup(groupGender); 
      RadioButton femaleRadio = new RadioButton("female"); 
      femaleRadio.setToggleGroup(groupGender); 
       
      //Label for reservation 
      Text reservationLabel = new Text("Reservation"); 
      
      //Toggle button for reservation 
      ToggleButton Reservation = new ToggleButton(); 
      ToggleButton yes = new ToggleButton("Yes"); 
      ToggleButton no = new ToggleButton("No"); 
      ToggleGroup groupReservation = new ToggleGroup(); 
      yes.setToggleGroup(groupReservation);   
      no.setToggleGroup(groupReservation); 
       
      //Label for technologies known 
      Text technologiesLabel = new Text("Technologies Known"); 
      
      //check box for education 
      CheckBox javaCheckBox = new CheckBox("Java"); 
      javaCheckBox.setIndeterminate(false); 
      
      //check box for education 
      CheckBox dotnetCheckBox = new CheckBox("DotNet"); 
      javaCheckBox.setIndeterminate(false); 
       
      //Label for education 
      Text educationLabel = new Text("Educational qualification"); 
      
      //list View for educational qualification 
      ObservableList<String> names = FXCollections.observableArrayList( 
         "Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd"); 
      ListView<String> educationListView = new ListView<String>(names); 
      
      //Label for location 
      Text locationLabel = new Text("location"); 
      
      //Choice box for location 
      ChoiceBox locationchoiceBox = new ChoiceBox(); 
      locationchoiceBox.getItems().addAll
         ("Hyderabad", "Chennai", "Delhi", "Mumbai", "Vishakhapatnam"); 
       
      //Label for register 
      Button buttonRegister = new Button("Register");  
      
      //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      
      //Setting size for the pane 
      gridPane.setMinSize(500, 500); 
       
      //Setting the padding    
      gridPane.setPadding(new Insets(10, 10, 10, 10));  
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
      gridPane.add(nameLabel, 0, 0); 
      gridPane.add(nameText, 1, 0); 
       
      gridPane.add(dobLabel, 0, 1);       
      gridPane.add(datePicker, 1, 1); 
      
      gridPane.add(genderLabel, 0, 2); 
      gridPane.add(maleRadio, 1, 2);       
      gridPane.add(femaleRadio, 2, 2); 
      gridPane.add(reservationLabel, 0, 3); 
      gridPane.add(yes, 1, 3);       
      gridPane.add(no, 2, 3);  
       
      gridPane.add(technologiesLabel, 0, 4); 
      gridPane.add(javaCheckBox, 1, 4);       
      gridPane.add(dotnetCheckBox, 2, 4);  
       
      gridPane.add(educationLabel, 0, 5); 
      gridPane.add(educationListView, 1, 5);      
       
      gridPane.add(locationLabel, 0, 6); 
      gridPane.add(locationchoiceBox, 1, 6);    
       
      gridPane.add(buttonRegister, 2, 8);      
      
      //Styling nodes   
      buttonRegister.setStyle(
         "-fx-background-color: darkslateblue; -fx-textfill: white;"); 
       
      nameLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      dobLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      genderLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      reservationLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      technologiesLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      educationLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      locationLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
       
      //Setting the back ground color 
      gridPane.setStyle("-fx-background-color: BEIGE;");       
       
      //Creating a scene object 
      Scene scene = new Scene(gridPane); 
      
      //Setting title to the Stage 
      stage.setTitle("Registration Form"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac Registration.java 
java Registration 
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![등록 양식](.\images\registration_form.jpg)

# JavaFX - 차트

일반적으로 차트는 데이터를 그래픽으로 표현한 것입니다. **Bar Chart, Pie Chart, Line Chart, Scatter Chart** 등 데이터를 표현하는 다양한 종류의 차트가 있습니다.

**JavaFX는 다양한 원형 차트** 및 **XY 차트를** 지원합니다 . XY 평면에 표시되는 차트에는 **AreaChart, BarChart, BubbleChart, LineChart, ScatterChart, StackedAreaChart, StackedBarChart** 등이 있습니다.

각 차트는 클래스로 표시되며 이러한 모든 차트는 **javafx.scene.chart** 패키지에 속합니다 . **Chart** 라는 클래스 는 JavaFX에 있는 모든 차트의 기본 클래스이고 **XYChart는** XY 평면에 그려지는 모든 차트의 기본 클래스입니다.

![차트](.\images\charts.jpg)

## 차트 만들기

차트를 만들려면 다음이 필요합니다.

- 차트의 축 정의
- 해당 클래스 인스턴스화
- 데이터 준비 및 차트에 전달

### 해당 클래스 인스턴스화

차트를 만들려면 해당 클래스를 인스턴스화하십시오. 예를 들어 라인 차트를 만들려면 다음과 같이 **Line 이라는 클래스를 인스턴스화해야 합니다.**

```
LineChart linechart = new LineChart(xAxis, yAxis);
```

위의 코드에서 볼 수 있듯이 인스턴스화하는 동안 차트의 X축과 Y축을 각각 나타내는 두 개의 개체를 전달해야 합니다.

### 축 정의

일반적으로 차트의 축은 다음과 같이 나타낼 수 있습니다.

- 인구, 연령 및
- 요일, 국가와 같은 범주.

JavaFX에서 축은 X 또는 Y축을 나타내는 추상 클래스입니다. 다음 다이어그램과 같이 각 유형의 축, 즉 **CategoryAxis** 및 **NumberAxis를** 정의하는 두 개의 하위 클래스가 있습니다 .

![축 정의](.\images\defining_axis.jpg)

**범주 축** - 이 클래스를 인스턴스화하여 각 값이 범주를 나타내는 X 또는 Y 축을 정의(생성)할 수 있습니다. 아래와 같이 이 클래스를 인스턴스화하여 범주 축을 정의할 수 있습니다.

```
CategoryAxis xAxis = new CategoryAxis();
```

이 축에는 아래와 같이 범주 및 레이블 목록을 축으로 설정해야 합니다.

```
//setting the list of categories.  
xAxis.setCategories(FXCollections.<String>observableArrayList
   (Arrays.asList("n ame1", "name2"….)));  

//Setting label to the axis  
xAxis.setLabel("name of the axis ");
```

**NumberAxis** - 이 클래스를 인스턴스화하여 각 값이 숫자 값을 나타내는 X 또는 Y축을 정의(생성)할 수 있습니다. **이 Axis, Long, Double, BigDecimal** 등과 함께 모든 숫자 유형을 사용할 수 있습니다 . 다음과 같이 이 클래스를 인스턴스화하여 숫자 축을 정의할 수 있습니다.

```
//Defining the axis 
NumberAxis yAxis = new NumberAxis();  

//Setting labelto the axis 
yAxis.setLabel("name of the axis");
```

### XY 차트에 데이터 전달

모든 XY 차트는 XY 평면을 따라 표시됩니다. 차트에 일련의 점을 그리려면 일련의 XY 좌표를 지정해야 합니다.

**javafx.scene.chart** 패키지 의 **<X,Y>** 클래스 는 데이터를 차트로 보낼 때 사용하는 클래스입니다. 이 클래스는 명명된 계열의 관찰 가능한 목록을 보유합니다. 아래와 같이 **XYChart.Series** 클래스 의 **getData()** 메서드를 사용하여 이 목록을 가져올 수 있습니다 .

```
ObservableList list = series.getData();
```

여기서 **series는** **XYChart.Series** 클래스 의 개체입니다 . 다음과 같이 **add()** 메서드를 사용하여 이 목록에 데이터를 추가할 수 있습니다 .

```
list.add(new XYChart.Data(x-axis data, y-axis data));
```

이 두 줄은 아래와 같이 함께 쓸 수 있습니다.

```
series.getData().add(new XYChart.Data(x-axis data, y-axis data));
```

다음 표는 JavaFX에서 제공하는 다양한 차트(클래스)에 대한 설명입니다.

| S.No |                         차트 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [파이 차트](https://www.tutorialspoint.com/javafx/pie_chart.htm)원형 차트는 다양한 색상의 원 조각으로 값을 표현한 것입니다. 이러한 조각에는 레이블이 지정되고 각 조각에 해당하는 값이 차트에 표시됩니다.**JavaFX에서 원형 차트는 PieChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . |
|  2   | [라인 차트](https://www.tutorialspoint.com/javafx/line_chart.htm)라인 차트 또는 라인 그래프는 직선 세그먼트로 연결된 일련의 데이터 포인트(마커)로 정보를 표시합니다. 꺾은선형 차트는 데이터가 동일한 시간 빈도로 어떻게 변경되는지 보여줍니다.**JavaFX에서 라인 차트는 LineChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 LineChart 노드를 만들 수 있습니다. |
|  삼  | [영역 차트](https://www.tutorialspoint.com/javafx/area_chart.htm)영역 차트는 영역 기반 차트를 그리는 데 사용됩니다. 주어진 일련의 점과 축 사이의 영역을 플로팅합니다. 일반적으로 이 차트는 두 수량을 비교하는 데 사용됩니다.**JavaFX에서 영역 차트는 AreaChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 AreaChart 노드를 만들 수 있습니다. |
|  4   | [막대 차트](https://www.tutorialspoint.com/javafx/bar_chart.htm)막대 차트는 직사각형 막대를 사용하여 그룹화된 데이터를 나타내는 데 사용됩니다. 이 막대의 길이는 값을 나타냅니다. 막대형 차트의 막대는 세로 또는 가로로 그릴 수 있습니다.**JavaFX에서 막대 차트는 BarChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 BarChart 노드를 만들 수 있습니다. |
|  5   | [거품형 차트](https://www.tutorialspoint.com/javafx/bubble_chart.htm)거품형 차트는 3차원 데이터를 표시하는 데 사용됩니다. 세 번째 차원은 거품의 크기(반지름)로 표시됩니다.**JavaFX에서 거품형 차트는 BubbleChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 BubbleChart 노드를 만들 수 있습니다. |
|  6   | [분산형 차트](https://www.tutorialspoint.com/javafx/scatter_chart.htm)산점도는 데카르트 평면에 표시된 두 변수의 값을 사용하는 그래프 유형입니다. 일반적으로 두 변수 간의 관계를 찾는 데 사용됩니다.**JavaFX에서 분산형 차트는 ScatterChart** 라는 클래스로 표시됩니다 . **이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 ScatterChart 노드를 만들 수 있습니다. |
|  7   | [누적 영역 차트](https://www.tutorialspoint.com/javafx/stacked_area_chart.htm)**JavaFX에서 누적 영역 차트는 StackedAreaChart** 라는 클래스로 표시됩니다 .**이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 StackedAreaChart 노드를 만들 수 있습니다. |
|  8   | [누적 막대 차트](https://www.tutorialspoint.com/javafx/stacked_bar_chart.htm)**JavaFX에서 누적 막대 차트는 StackedBarChart** 라는 클래스로 표시됩니다 .**이 클래스는 javafx.scene.chart** 패키지에 속합니다 . 이 클래스를 인스턴스화하면 JavaFX에서 StackedBarChart 노드를 만들 수 있습니다. |

# JavaFX - 레이아웃 창(컨테이너)

장면에 필요한 모든 노드를 구성한 후 일반적으로 순서대로 정렬합니다.

컨테이너 내 구성 요소의 이러한 배열을 컨테이너의 레이아웃이라고 합니다. 우리는 또한 컨테이너 내의 특정 위치에 모든 구성 요소를 배치하는 것을 포함하는 레이아웃을 따랐다고 말할 수 있습니다.

**JavaFX 는 HBox, VBox, Border Pane, Stack Pane, Text Flow, Anchor Pane, Title Pane, Grid Pane, Flow Panel** 등과 같은 미리 정의된 여러 레이아웃을 제공합니다 .

위에서 언급한 각 레이아웃은 클래스로 표시되며 이러한 모든 클래스는 **javafx.layout** 패키지에 속합니다 . **Pane** 이라는 클래스는 JavaFX의 모든 레이아웃의 기본 클래스입니다.

## 레이아웃 만들기

레이아웃을 만들려면 다음이 필요합니다.

- 노드를 생성합니다.
- 필요한 레이아웃의 각 클래스를 인스턴스화합니다.
- 레이아웃의 속성을 설정합니다.
- 생성된 모든 노드를 레이아웃에 추가합니다.

## 노드 생성

먼저 각각의 클래스를 인스턴스화하여 JavaFX 애플리케이션의 필수 노드를 생성합니다.

예를 들어, HBox 레이아웃에서 텍스트 필드와 두 개의 버튼, 즉 재생 및 중지를 갖고 싶다면 처음에 다음 코드 블록에 표시된 대로 해당 노드를 만들어야 합니다.

```
//Creating a text field 
TextField textField = new TextField();       

//Creating the play button 
Button playButton = new Button("Play");       

//Creating the stop button 
Button stopButton = new Button("stop");
```

## 해당 클래스 인스턴스화

노드를 만들고 모든 작업을 완료한 후 필요한 레이아웃의 클래스를 인스턴스화합니다.

예를 들어 Hbox 레이아웃을 생성하려면 다음과 같이 이 클래스를 인스턴스화해야 합니다.

```
HBox hbox = new HBox();
```

## 레이아웃 속성 설정

클래스를 인스턴스화한 후 해당 setter 메서드를 사용하여 레이아웃 속성을 설정해야 합니다.

예를 들어 HBox 레이아웃에서 생성된 노드 사이에 간격을 설정하려면 간격이라는 속성에 값을 설정해야 합니다. 이는 아래와 같이 setter 메서드 **setSpacing()을 사용하여 수행할 수 있습니다.**

```
hbox.setSpacing(10);
```

## 그룹에 도형 개체 추가

마지막으로 아래와 같이 도형의 객체를 생성자의 매개변수로 전달하여 그룹에 추가해야 합니다.

```
//Creating a Group object  
Group root = new Group(line);
```

## 레이아웃 창

다음은 JavaFX에서 제공하는 다양한 레이아웃 창(클래스)입니다. **이러한 클래스는 javafx.scene.layout** 패키지에 있습니다 .

| S.No |                         모양 및 설명                         |
| :--: | :----------------------------------------------------------: |
|  1   | [에이치박스](https://www.tutorialspoint.com/javafx/layout_panes_hbox.htm)HBox 레이아웃은 애플리케이션의 모든 노드를 단일 수평 행에 정렬합니다.**javafx.scene.layout** 패키지의 **HBox** 라는 클래스는 텍스트 수평 상자 레이아웃을 나타냅니다. |
|  2   | [VBox](https://www.tutorialspoint.com/javafx/layout_panes_vbox.htm)VBox 레이아웃은 애플리케이션의 모든 노드를 단일 수직 열에 정렬합니다.**javafx.scene.layout** 패키지의 **VBox** 라는 클래스는 세로 상자 레이아웃이라는 텍스트를 나타냅니다. |
|  삼  | [테두리판](https://www.tutorialspoint.com/javafx/layout_borderpane.htm)테두리 창 레이아웃은 응용 프로그램의 노드를 위쪽, 왼쪽, 오른쪽, 아래쪽 및 중앙 위치에 정렬합니다.**javafx.scene.layout** 패키지의 **BorderPane** 클래스는 테두리 창 레이아웃을 나타냅니다. |
|  4   | [StackPane](https://www.tutorialspoint.com/javafx/layout_stackpane.htm)스택 창 레이아웃은 스택에서처럼 애플리케이션의 노드를 다른 노드 위에 정렬합니다. 처음 추가된 노드는 스택의 맨 아래에 배치되고 다음 노드는 그 위에 배치됩니다.**javafx.scene.layout** 패키지의 **StackPane** 클래스는 스택 창 레이아웃을 나타냅니다. |
|  5   | [TextFlow](https://www.tutorialspoint.com/javafx/layout_panes_textflow.htm)텍스트 흐름 레이아웃은 단일 흐름에서 여러 텍스트 노드를 배열합니다.패키지 **javafx.scene.layout 의** **TextFlow** 라는 클래스는 텍스트 흐름 레이아웃을 나타냅니다. |
|  6   | [AnchorPane](https://www.tutorialspoint.com/javafx/layout_anchorpane.htm)앵커 창 레이아웃은 창에서 특정 거리에 애플리케이션의 노드를 고정합니다.**javafx.scene.layout** 패키지의 **AnchorPane** 클래스는 Anchor Pane 레이아웃을 나타냅니다. |
|  7   | [TilePane](https://www.tutorialspoint.com/javafx/layout_tilepane.htm)Tile Pane 레이아웃은 애플리케이션의 모든 노드를 균일한 크기의 타일 형태로 추가합니다.**javafx.scene.layout** 패키지의 **TilePane** 클래스는 TilePane 레이아웃을 나타냅니다. |
|  8   | [GridPane](https://www.tutorialspoint.com/javafx/layout_gridpane.htm)그리드 창 레이아웃은 응용 프로그램의 노드를 행과 열의 그리드로 정렬합니다. 이 레이아웃은 JavaFX를 사용하여 양식을 만드는 동안 유용합니다.패키지 **javafx.scene.layout 의** **GridPane** 이라는 클래스는 GridPane 레이아웃을 나타냅니다. |
|  9   | [FlowPane](https://www.tutorialspoint.com/javafx/layout_flowpane.htm)흐름 패널 레이아웃은 흐름의 모든 노드를 래핑합니다. 수평 흐름판은 창의 요소를 높이로 둘러싸는 반면 세로 흐름판은 요소를 너비로 감쌉니다.**javafx.scene.layout** 패키지의 **FlowPane** 이라는 클래스는 흐름 패널 레이아웃을 나타냅니다. |

# JavaFX-CSS

CSS라고도 하는 **캐스케이딩 스타일 시트는 웹 페이지를 표현 가능하게 만드는 프로세스를 단순화하기 위한 간단한 디자인 언어입니다.**

CSS는 웹 페이지의 모양과 느낌 부분을 처리합니다. CSS를 사용하여 텍스트 색상, 글꼴 스타일, 단락 간 간격, 열 크기 및 레이아웃을 제어할 수 있습니다. 이 외에도 사용되는 배경 이미지 또는 색상, 레이아웃 디자인, 다양한 장치 및 화면 크기에 대한 디스플레이 변형 및 기타 다양한 효과를 제어할 수 있습니다.

## JavaFX의 CSS

JavaFX는 CSS를 사용하여 응용 프로그램의 모양과 느낌을 향상시키는 기능을 제공합니다. 패키지 **javafx.css** 에는 JavaFX 응용 프로그램에 CSS를 적용하는 데 사용되는 클래스가 포함되어 있습니다.

CSS는 브라우저에서 해석한 다음 문서의 해당 요소에 적용되는 스타일 규칙으로 구성됩니다.

스타일 규칙은 다음과 같은 세 부분으로 구성됩니다.

- **선택기** - 선택기는 스타일이 적용될 HTML 태그입니다. **<h1>** 또는 **<table>** 등과 같은 태그가 될 수 있습니다 .
- **속성** - 속성은 HTML 태그의 속성 유형입니다. 간단히 말해서 모든 HTML 속성은 CSS 속성으로 변환됩니다. **색상, 테두리** 등 이 될 수 있습니다.
- **값** - 값은 속성에 할당됩니다. 예를 들어 색상 속성은 **red** 또는 **#F1F1F1** 등의 값을 가질 수 있습니다.

다음과 같이 CSS 스타일 규칙 구문을 입력할 수 있습니다.

```
selector { property: value }
```



![CSS 스타일](.\images\css_style.jpg)

JavaFX에서 사용하는 기본 스타일시트는 **modena.css** 입니다 . JavaFX 런타임 jar에 있습니다.

### 나만의 스타일 시트 추가

다음과 같이 JavaFX의 장면에 자신만의 스타일 시트를 추가할 수 있습니다.

```
Scene scene = new Scene(new Group(), 500, 400); 
scene.getStylesheets().add("path/stylesheet.css");
```

### 인라인 스타일 시트 추가

**setStyle()** 메서드를 사용하여 인라인 스타일을 추가할 수도 있습니다 . 이러한 스타일은 키-값 쌍으로만 구성되며 설정된 노드에 적용할 수 있습니다. 다음은 인라인 스타일 시트를 버튼으로 설정하는 샘플 코드입니다.

```
.button { 
   -fx-background-color: red; 
   -fx-text-fill: white; 
}
```

### 예

텍스트 필드, 암호 필드, 두 개의 버튼이 있는 양식을 표시하는 JavaFX 애플리케이션을 개발했다고 가정합니다. 기본적으로 이 양식은 다음 스크린샷과 같이 표시됩니다.

![그리드 창](.\images\grid_pane.jpg)

다음 프로그램은 JavaFX에서 위의 애플리케이션에 스타일을 추가하는 방법을 보여주는 예제입니다.

**이 코드를 CssExample.java** 라는 이름으로 파일에 저장합니다.

```
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  

public class CssExample extends Application { 
   @Override 
   public void start(Stage stage) {      
      //creating label email 
      Text text1 = new Text("Email");       
      
      //creating label password 
      Text text2 = new Text("Password"); 
       
      //Creating Text Filed for email        
      TextField textField1 = new TextField();       
      
      //Creating Text Filed for password        
      PasswordField textField2 = new PasswordField();  
       
      //Creating Buttons 
      Button button1 = new Button("Submit"); 
      Button button2 = new Button("Clear");  
      
      //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      
      //Setting size for the pane 
      gridPane.setMinSize(400, 200);
      
      //Setting the padding  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
      gridPane.add(text1, 0, 0); 
      gridPane.add(textField1, 1, 0); 
      gridPane.add(text2, 0, 1);       
      gridPane.add(textField2, 1, 1); 
      gridPane.add(button1, 0, 2); 
      gridPane.add(button2, 1, 2); 
       
      //Styling nodes  
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
       
      text1.setStyle("-fx-font: normal bold 20px 'serif' "); 
      text2.setStyle("-fx-font: normal bold 20px 'serif' ");  
      gridPane.setStyle("-fx-background-color: BEIGE;"); 
       
      // Creating a scene object 
      Scene scene = new Scene(gridPane); 
       
      // Setting title to the Stage   
      stage.setTitle("CSS Example"); 
         
      // Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
```

다음 명령을 사용하여 명령 프롬프트에서 저장된 java 파일을 컴파일하고 실행합니다.

```
javac CssExample.java 
java CssExample
```

실행 시 위 프로그램은 아래와 같이 JavaFX 창을 생성합니다.

![CSS 예제](.\images\css_example.jpg)



### - 끝 -



