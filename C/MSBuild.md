# MSBuild

출처: https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017

### 이 문서의 내용

1. [명령 프롬프트에서 MSBuild 사용](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#use-msbuild-at-a-command-prompt)
2. [프로젝트 파일](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#project-file)
3. [빌드 로그](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#build-logs)
4. [Visual Studio에서 MSBuild 사용](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#use-msbuild-in-visual-studio)
5. [멀티 타기팅](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#BKMK_Multitargeting)
6. [참고 항목](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#see-also)
7. [참조](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild?view=vs-2017#reference)

Microsoft Build Engine은 응용 프로그램을 작성하기위한 플랫폼입니다. MSBuild라고도하는이 엔진은 빌드 플랫폼이 소프트웨어를 처리하고 빌드하는 방법을 제어하는 프로젝트 파일 용 XML 스키마를 제공합니다. Visual Studio는 MSBuild를 사용하지만 Visual Studio에 의존하지 않습니다. 프로젝트 또는 솔루션 파일에서 msbuild.exe를 호출하면 Visual Studio가 설치되지 않은 환경에서 제품을 조율하고 구축 할 수 있습니다.

Visual Studio는 MSBuild를 사용하여 관리되는 프로젝트를로드하고 구축합니다. Visual Studio (.csproj, .vbproj, .vcxproj 및 기타)의 프로젝트 파일에는 IDE를 사용하여 프로젝트를 빌드 할 때 실행되는 MSBuild XML 코드가 들어 있습니다. Visual Studio 프로젝트는 일반적인 개발 작업을 수행하는 데 필요한 모든 설정과 프로세스를 가져 오지만 Visual Studio 또는 XML 편집기를 사용하여 확장하거나 수정할 수 있습니다.

C ++ 용 MSBuild에 대한 자세한 내용은 [MSBuild (Visual C ++)](https://docs.microsoft.com/ko-kr/cpp/build/msbuild-visual-cpp)를 참조하십시오.

다음 예제에서는 Visual Studio IDE 대신 MSBuild 명령 줄을 사용하여 빌드를 실행할 수있는 경우를 보여줍니다.

- Visual Studio가 설치되어 있지 않습니다. (Visual Studio없이 MSBuild 다운로드)
- MSBuild의 64 비트 버전을 사용하려고합니다. 이 버전의 MSBuild는 일반적으로 불필요하지만 MSBuild가 더 많은 메모리에 액세스 할 수있게합니다.
- 여러 프로세스에서 빌드를 실행하려고합니다. 그러나 IDE를 사용하여 C ++ 및 C# 프로젝트에서 동일한 결과를 얻을 수 있습니다.
- 빌드 시스템을 수정하려고합니다. 예를 들어 다음 작업을 활성화 할 수 있습니다.
  - 컴파일러에 도달하기 전에 파일을 사전 처리합니다.
  - 빌드 출력을 다른 위치로 복사하십시오.
  - 빌드 출력에서 압축 파일을 작성하십시오.
  - 후 처리 단계를 수행하십시오. 예를 들어 다른 버전의 어셈블리를 스탬프 할 수 있습니다.

Visual Studio IDE에서 코드를 작성할 수 있지만 MSBuild를 사용하여 빌드를 실행할 수 있습니다. 또 다른 대안으로 개발 컴퓨터에서 IDE에 코드를 만들 수 있지만 MSBuild 명령 줄을 사용하여 여러 개발자가 통합 한 코드를 작성할 수 있습니다.

```
Team Foundation Build를 사용하여 응용 프로그램을 자동으로 컴파일, 테스트 및 배포 할 수 있습니다. 개발자가 코드를 체크인 (예 : 지속적인 통합 전략의 일환)하거나 일정 (예 : 야간 빌드 검증 테스트 빌드)에 따라 빌드 시스템이 자동으로 빌드를 실행할 수 있습니다. Team Foundation Build는 MSBuild를 사용하여 코드를 컴파일합니다. 자세한 내용은 Azure 파이프 라인을 참조하십시오.
```

이 항목에서는 MSBuild에 대한 개요를 제공합니다. 소개 자습서는 연습 : [MSBuild 사용을](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/walkthrough-using-msbuild?view=vs-2017) 참조하십시오.



## Use MSBuild at a command prompt

명령 프롬프트에서 MSBuild를 실행하려면 적절한 명령 행 옵션과 함께 MSBuild.exe에 프로젝트 파일을 전달하십시오. 명령 줄 옵션을 사용하면 속성을 설정하고 특정 대상을 실행하며 빌드 프로세스를 제어하는 다른 옵션을 설정할 수 있습니다. 예를 들어 다음 명령 줄 구문을 사용하여 Configuration 속성이 Debug로 설정된 MyProj.proj 파일을 빌드합니다.

```powershell
MSBuild.exe MyProj.proj -property:Configuration=Debug
```

MSBuild 명령 줄 옵션에 대한 자세한 내용은 명령 줄 참조를 참조하십시오.

```
프로젝트를 다운로드하기 전에 코드의 신뢰성을 결정하십시오.
```



## Project file

MSBuilduses는 간단하고 확장 가능한 XML 기반 프로젝트 파일 형식입니다. MSBuild 프로젝트 파일 형식을 사용하면 개발자가 작성해야 할 항목을 설명하고 다양한 운영 체제 및 구성에 맞게 작성하는 방법을 설명 할 수 있습니다. 또한 프로젝트 파일 형식을 사용하면 개발자가 재사용 가능한 빌드 규칙을 작성하여 별도의 파일로 통합 할 수 있으므로 제품의 여러 프로젝트에서 일관되게 빌드를 수행 할 수 있습니다.

다음 섹션에서는 MSBuild 프로젝트 파일 형식의 몇 가지 기본 요소에 대해 설명합니다. 기본 프로젝트 파일을 만드는 방법에 대한 자습서는 연습 : [처음부터 MSBuild 프로젝트 파일 만들기](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/walkthrough-creating-an-msbuild-project-file-from-scratch?view=vs-2017)를 참조하십시오.



### Properties

속성은 빌드를 구성하는 데 사용할 수있는 키 / 값 쌍을 나타냅니다. 속성은 PropertyGroup 요소의 자식으로 속성의 이름을 가진 요소를 만들어 선언합니다. 예를 들어 다음 코드에서는 Build 값이있는 BuildDir이라는 속성을 만듭니다.

```XML
<PropertyGroup>
    <BuildDir>Build</BuildDir>
</PropertyGroup>
```

요소에 Condition 특성을 배치하여 조건부로 속성을 정의 할 수 있습니다. 조건이 true로 평가되지 않으면 조건부 요소의 내용이 무시됩니다. 다음 예제에서 Configuration 요소는 아직 정의되지 않은 경우 정의됩니다.

```XML
<Configuration  Condition=" '$(Configuration)' == '' ">Debug</Configuration>
```

속성은 $ (<propertyname>) 구문을 사용하여 프로젝트 파일 전체에서 참조 할 수 있습니다. 예를 들어 $ (BuildDir) 및 $ (Configuration)을 사용하여 이전 예제의 속성을 참조 할 수 있습니다.</propertyname>

속성에 대한 자세한 내용은 [MSBuild 속성](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild-properties?view=vs-2017)을 참조하십시오.



### Items

항목은 빌드 시스템에 대한 입력이며 일반적으로 파일을 나타냅니다. 항목은 사용자 정의 항목 이름에 따라 항목 유형으로 그룹화됩니다. 이러한 항목 유형은 개별 항목을 사용하여 빌드 프로세스의 단계를 수행하는 작업의 매개 변수로 사용될 수 있습니다.

항목은 항목 유형의 이름이 ItemGroup 요소의 자식으로있는 요소를 만들어 프로젝트 파일에 선언됩니다. 예를 들어, 다음 코드는 두 파일을 포함하는 Compile이라는 항목 유형을 만듭니다.

```xml
<ItemGroup>
    <Compile Include = "file1.cs"/>
    <Compile Include = "file2.cs"/>
</ItemGroup>
```

항목 유형은 (<itemtype>) 구문을 사용하여 프로젝트 파일 전체에서 참조 할 수 있습니다. 예를 들어, 예제의 항목 유형은 (Compile)을 사용하여 참조됩니다.</itemtype>

MSBuild에서 요소 및 특성 이름은 대 / 소문자를 구분합니다. 그러나 특성, 항목 및 메타 데이터 이름은 그렇지 않습니다. 다음 예제에서는 항목 유형 Compile, comPile 또는 기타 사례 변형을 만들고 항목 유형에 값 'one.cs; two.cs'를 제공합니다.

```xml
<ItemGroup>
  <Compile Include="one.cs" />
  <comPile Include="two.cs" />
</ItemGroup>
```

항목은 와일드 카드 문자를 사용하여 선언 할 수 있으며 고급 빌드 시나리오에 대한 추가 메타 데이터를 포함 할 수 있습니다. 항목에 대한 자세한 내용은 항목을 참조하십시오.



### Tasks

작업은 MSBuild 프로젝트가 빌드 작업을 수행하는 데 사용하는 실행 가능 코드의 단위입니다. 예를 들어, 태스크가 입력 파일을 컴파일하거나 외부 도구를 실행할 수 있습니다. 작업은 재사용 할 수 있으며 다른 프로젝트의 다른 개발자가 작업을 공유 할 수 있습니다.

작업의 실행 논리는 관리 코드로 작성되고 UsingTask 요소를 사용하여 MSBuild에 매핑됩니다. ITask 인터페이스를 구현하는 관리되는 유형을 제작하여 고유 한 작업을 작성할 수 있습니다. 작업 작성 방법에 대한 자세한 내용은 작업 작성을 참조하십시오.

MSBuild에는 요구 사항에 맞게 수정할 수있는 일반적인 작업이 포함되어 있습니다. 예를 들어, 파일을 복사하는 Copy, 디렉터리를 만드는 MakeDir 및 Visual C 소스 코드 파일을 컴파일하는 Csc가 있습니다. 사용 가능한 작업 목록과 사용 정보는 작업 참조를 참조하십시오.

작업은 Target 요소의 자식으로 작업 이름이있는 요소를 만들어 MSBuild 프로젝트 파일에서 실행됩니다. 작업은 일반적으로 요소의 속성으로 전달되는 매개 변수를 허용합니다. MSBuild 속성과 항목은 모두 매개 변수로 사용할 수 있습니다. 예를 들어 다음 코드는 MakeDir 작업을 호출하고 앞의 예제에서 선언 한 BuildDir 속성 값을이 작업자에게 전달합니다.

```xml
<Target Name="MakeBuildDirectory">
    <MakeDir  Directories="$(BuildDir)" />
</Target>
```

작업에 대한 자세한 내용은 [작업](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild-tasks?view=vs-2017)을 참조하십시오.



### Targets

프로젝트를 특정 순서로 그룹화하고 빌드 프로세스의 진입 점으로 프로젝트 파일의 섹션을 표시합니다. 대상은 종종 가독성을 높이고 확장을 허용하기 위해 논리 섹션으로 그룹화됩니다. 빌드 단계를 타겟으로 분해하면 코드 타겟을 모든 타겟에 복사하지 않고도 다른 타겟에서 빌드 프로세스를 호출 할 수 있습니다. 예를 들어 빌드 프로세스의 여러 진입 점에서 참조를 작성해야하는 경우 참조를 작성한 후 대상이 필요한 모든 진입 점에서 해당 대상을 실행할 수 있습니다.

대상은 대상 요소를 사용하여 프로젝트 파일에 선언됩니다. 예를 들어 다음 코드에서는 Compile이라는 대상을 만들고이 예제에서는 앞의 예제에서 선언 한 항목 목록이있는 Csc 작업을 호출합니다.

```xml
<Target Name="Compile">
    <Csc Sources="@(Compile)" />
</Target>
```

보다 고급 시나리오에서는 대상을 사용하여 서로 간의 관계를 설명하고 종속성 분석을 수행하여 해당 대상이 최신 상태 인 경우 빌드 프로세스의 전체 섹션을 건너 뛸 수 있습니다. 대상에 대한 자세한 내용은 대상을 참조하십시오.



## Build logs

빌드 오류, 경고 및 메시지를 콘솔이나 다른 출력 장치에 기록 할 수 있습니다. 자세한 내용은 빌드 로그 가져 오기 및 MSBuild에서 로그인을 참조하십시오.





## Use MSBuild in Visual Studio

VisualBasic은 관리되는 프로젝트에 대한 빌드 정보를 저장하기 위해 MSBuild 프로젝트 파일 형식을 연구합니다. Visual Studio 인터페이스를 사용하여 추가되거나 변경된 프로젝트 설정은 모든 프로젝트에 대해 생성 된. * proj 파일에 반영됩니다. VisualBasic은 MSBuild 인스턴스를 호스팅하여 관리되는 프로젝트를 작성합니다. 즉, Visual Studio가 설치되어 있지 않은 경우에도 관리되는 프로젝트를 Visual Studio 또는 명령 프롬프트에서 빌드 할 수 있으며 결과는 동일합니다.

Visual Studio에서 MSBuild를 사용하는 방법에 대한 자습서는 연습 : [MSBuild 사용](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/walkthrough-using-msbuild?view=vs-2017)을 참조하십시오.



## Multitargeting

Visual Studio를 사용하면 .NET Framework의 여러 버전 중 하나에서 실행되도록 응용 프로그램을 컴파일 할 수 있습니다. 예를 들어 32 비트 플랫폼의 .NET Framework 2.0에서 실행되도록 응용 프로그램을 컴파일하고 64 비트 플랫폼의 .NET Framework 4.5에서 실행되도록 동일한 응용 프로그램을 컴파일 할 수 있습니다. 하나 이상의 프레임 워크로 컴파일 할 수있는 기능은 멀티 타게팅이라고합니다.

다음은 멀티 타겟팅의 이점 중 일부입니다.

- 버전 2.0, 3.0 및 3.5와 같은 이전 버전의 .NET Framework를 대상으로하는 응용 프로그램을 개발할 수 있습니다.
- Silverlight와 같은 .NET Framework 이외의 프레임 워크를 대상으로 할 수 있습니다.
- 미리 정의 된 대상 프레임 워크의 하위 집합 인 프레임 워크 프로필을 대상으로 지정할 수 있습니다.
- 현재 버전의 .NET Framework 용 서비스 팩이 릴리스되면이를 대상으로 할 수 있습니다.
- 멀티 타게팅은 애플리케이션이 타겟 프레임 워크 및 플랫폼에서 사용 가능한 기능 만 사용한다는 것을 보장합니다.

자세한 내용은 [멀티 타게팅](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild-multitargeting-overview?view=vs-2017)을 참조하십시오.



## Reference

- [MSBuild reference](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild-reference?view=vs-2017) Links to topics that contain reference information.
- [Glossary](https://docs.microsoft.com/ko-kr/visualstudio/msbuild/msbuild-glossary?view=vs-2017) Defines common MSBuild terms.





# What's new in MSBuild 15

https://docs.microsoft.com/ko-kr/visualstudio/msbuild/what-s-new-in-msbuild-15-0?view=vs-2017

