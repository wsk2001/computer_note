# visual studio 스펙터(spectre) 설정 끄기

해결방법
-프로젝트마다 스펙터 완화 기능을 끄거나 솔루션 폴더 또는 프로젝트 루트 폴더에 Directory.Build.props 파일을 하나 만들고 스펙터 관련 설정값을 넣어줌.

```
<Project>
  <PropertyGroup Label="Configuration">
    <SpectreMitigation>false</SpectreMitigation>
  </PropertyGroup>
</Project>
```

