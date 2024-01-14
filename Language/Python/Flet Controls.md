# Flet Controls

출처: https://flet.dev/docs/controls

### 컨트롤 참조

Flet UI는 컨트롤로 구성됩니다. 컨트롤은 계층 구조 또는 트리로 구성됩니다. 여기서 각 컨트롤에는 상위(페이지 제외)가 있고 열, 드롭다운과 같은 컨테이너 컨트롤에는 하위 컨트롤이 포함될 수 있습니다. 예를 들면 다음과 같습니다.

```
Page
 ├─ TextField
 ├─ Dropdown
 │   ├─ Option
 │   └─ Option
 └─ Row
     ├─ ElevatedButton
     └─ ElevatedButton
```



### 카테고리별 컨트롤

- ### Layout

  | 컨트롤             | 설명                                                         |
  | ------------------ | ------------------------------------------------------------ |
  | Card               | 머티리얼 디자인 카드: 약간 둥근 모서리와 입면 그림자가 있는 패널입니다.<br>![img](https://flet.dev/img/docs/controls/card/card.gif) |
  | Column             | 자식을 수직 배열로 표시하는 컨트롤입니다.<br>하위 컨트롤이 확장되어 사용 가능한 수직 공간을 채우게 하려면 해당 확장 속성을 설정하세요.<br>![img](https://flet.dev/img/docs/controls/column/column-spacing.gif) |
  | Container          | 컨테이너를 사용하면 배경색과 테두리로 컨트롤을 장식하고 패딩, 여백 및 정렬을 사용하여 위치를 지정할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/container/containers-background-color.png) |
  | DataTable          | 머티리얼 디자인 데이터 테이블.<br>![img](https://flet.dev/img/docs/controls/datatable/datatable-minimal.png) |
  | Dismissible        | 표시된 해제_방향을 드래그하여 해제할 수 있는 컨트롤입니다. 지정된 dismiss_direction으로 끌거나 던지는 경우 콘텐츠가 부드럽게 미끄러져 시야 밖으로 사라집니다.<br>![img](https://flet.dev/img/docs/controls/dismissible/dismissible-listview.gif) |
  | Divider            | 양쪽에 패딩이 있는 얇은 수평선입니다.<br>머티리얼 디자인 언어에서 이는 구분선을 나타냅니다.<br>![img](https://flet.dev/img/docs/controls/divider/divider.png) |
  | ExpansionPanelList | 하위 항목을 배치하고 확장에 애니메이션을 적용하는 재질 확장 패널 목록입니다.<br>![img](https://flet.dev/img/docs/controls/expansion-panel/expansion-panel.gif) |
  | ExpansionTile      | 타일을 확장하거나 축소하여 하위 항목을 표시하거나 숨기는 확장 화살표 아이콘이 있는 단일 행 ListTile입니다.<br>![img](https://flet.dev/img/docs/controls/expansion-tile/expansion-tile.png) |
  | GridView           | 스크롤 가능한 2D 컨트롤 배열입니다.<br>GridView는 대규모 목록(수천 개의 항목)에 매우 효과적입니다. 부드러운 스크롤을 위해 열이나 행을 래핑하는 것보다 선호합니다. GridView 사용 예는 Flet Icons Browser를 참조하세요.<br>![img](https://flet.dev/img/docs/controls/gridview/photo-gallery.png) |
  | ListTile           | 일반적으로 일부 텍스트와 선행 또는 후행 아이콘을 포함하는 단일 고정 높이 행입니다.<br>![img](https://flet.dev/img/docs/controls/listtile/listtiles.png) |
  | ListView           | 선형으로 배열된 스크롤 가능한 컨트롤 목록입니다.<br>ListView는 가장 일반적으로 사용되는 스크롤 컨트롤입니다. 스크롤 방향으로 자식을 차례로 표시합니다. 교차축에서 하위 항목은 ListView를 채워야 합니다.<br>ListView는 대규모 목록(수천 개의 항목)에 매우 효과적입니다. 부드러운 스크롤을 위해서는 열이나 행보다 선호합니다.<br>![img](https://flet.dev/img/docs/controls/listview/custom-listview.gif) |
  | Page               | 페이지는 보기 컨트롤의 컨테이너입니다.<br>새 사용자 세션이 시작되면 페이지 인스턴스와 루트 보기가 자동으로 생성됩니다. |
  | ResponsiveRow      | ResponsiveRow는 Bootstrap 웹 프레임워크에서 그리드 레이아웃 아이디어를 차용했습니다.<br>ResponsiveRow를 사용하면 하위 컨트롤을 가상 열에 정렬할 수 있습니다. 기본적으로 가상 그리드에는 12개의 열이 있지만 ResponsiveRow.columns 속성을 사용하여 사용자 정의할 수 있습니다.<br>확장 속성과 유사하게 이제 모든 컨트롤에는 컨트롤이 확장되어야 하는 열 수를 지정할 수 있는 col 속성이 있습니다. 예를 들어 각각 6개의 가상 열에 걸쳐 있는 2개의 열로 구성된 레이아웃을 만들려면 다음을 수행하세요.<br>import flet as ft<br/><br/>ft.ResponsiveRow([<br/>    ft.Column(col=6, controls=[ft.Text("Column 1")]),<br/>    ft.Column(col=6, controls=[ft.Text("Column 2")])<br/>])<br>![img](https://flet.dev/img/docs/controls/responsive-row/responsive-layout.gif) |
  | Row                | 자식을 가로 배열로 표시하는 컨트롤입니다.<br>하위 컨트롤이 확장되어 사용 가능한 가로 공간을 채우게 하려면 해당 확장 속성을 설정하세요.<br>![img](https://flet.dev/img/docs/controls/row/row-spacing.gif) |
  | SafeArea           | 운영 체제의 침입을 방지하기 위해 충분한 패딩을 사용하여 콘텐츠를 삽입하는 컨트롤입니다.<br>예를 들어 화면 상단의 상태 표시줄을 피할 수 있을 만큼 콘텐츠를 들여쓰기합니다.<br>또한 iPhone X의 노치(Notch) 또는 디스플레이의 기타 유사한 창의적 물리적 기능을 방지하는 데 필요한 만큼 콘텐츠를 들여쓰기합니다.<br>최소 패딩이 지정되면 최소 패딩과 안전 영역 패딩 중 더 큰 값이 적용됩니다. |
  | Stack              | 자식을 서로의 위에 배치하는 컨트롤입니다.<br>이 컨트롤은 간단한 방법으로 여러 하위 항목을 겹치려는 경우에 유용합니다. 예를 들어 텍스트와 이미지를 그라데이션으로 오버레이하고 하단에 버튼을 추가하는 경우입니다.<br>스택은 대상 값의 절대 위치를 알아야 하는 암시적 애니메이션을 구현하려는 경우에도 유용합니다.<br>![img](https://flet.dev/img/docs/controls/stack/image-title.png) |
  | Tabs               | 탭 컨트롤은 자주 액세스하는 고유한 콘텐츠 카테고리를 탐색하는 데 사용됩니다. 탭을 사용하면 두 개 이상의 콘텐츠 보기 사이를 탐색할 수 있으며 텍스트 헤더를 사용하여 콘텐츠의 다양한 섹션을 명확하게 표현할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/tabs/tabs-simple.gif) |
  | VerticalDivider    | 양쪽에 패딩이 있는 얇은 수직선입니다.<br>머티리얼 디자인 언어에서 이는 구분선을 나타냅니다.<br>![img](https://flet.dev/img/docs/controls/vertical-divider/vertical-divider.png) |
  | View               | 뷰는 다른 모든 컨트롤의 최상위 컨테이너입니다.<br>새 사용자 세션이 시작되면 루트 보기가 자동으로 생성됩니다. 레이아웃 관점에서 보기는 열 컨트롤을 나타내므로 유사한 동작을 가지며 동일한 속성을 공유합니다. |



- #### Navigation

  | 컨트롤                 | 설명                                                         |
  | ---------------------- | ------------------------------------------------------------ |
  | AppBar                 | 머티리얼 디자인 앱 바.<br>![img](https://flet.dev/img/docs/controls/app-bar/app-bar.gif) |
  | BottomAppBar           | 머티리얼 디자인 하단 앱 바.<br>![img](https://flet.dev/img/docs/controls/bottom-app-bar/bottom-app-bar.png) |
  | CupertinoNavigationBar | iOS 스타일의 하단 탐색 탭 표시줄입니다. <br>탐색 표시줄은 앱의 기본 대상 간을 전환하는 지속적이고 편리한 방법을 제공합니다.<br>![img](https://flet.dev/img/docs/controls/cupertino-navigation-bar/cupertino-navigation-bar-sample.png) |
  | NavigationBar          | 머티리얼 3 탐색 모음 구성요소.<br>탐색 표시줄은 앱의 기본 대상 간을 전환하는 지속적이고 편리한 방법을 제공합니다.<br>![img](https://flet.dev/img/docs/controls/navigation-bar/navigation-bar-sample.gif) |
  | NavigationDrawer       | 머티리얼 디자인 탐색 창 구성요소.<br>탐색 창은 페이지의 왼쪽 또는 오른쪽 가장자리에서 수평으로 미끄러져 들어가 앱의 기본 대상을 표시하는 패널입니다. NavigationDrawer를 페이지에 추가하려면 page.drawer 및 page.end_drawer 속성을 사용하세요. 마찬가지로 NavigationDrawer를 뷰에 추가할 수 있습니다. 서랍을 표시하려면 open 속성을 True로 설정하세요.<br>![img](https://flet.dev/img/docs/controls/navigationdrawer/navigation-drawer-start.gif) |
  | NavigationRail         | 소수의 뷰(일반적으로 3 ~ 5개) 사이를 탐색하기 위해 앱의 왼쪽이나 오른쪽에 표시되는 머티리얼 위젯입니다.<br>![img](https://flet.dev/img/docs/controls/navigation-rail/custom-navrail.png) |

  

- #### Information Displays

  | 컨트롤       | 설명                                                         |
  | ------------ | ------------------------------------------------------------ |
  | Badge        | 머티리얼 디자인 '배지'.<br>배지는 일반적으로 NavigationBar, NavigationRail 대상 또는 버튼 아이콘의 일부인 아이콘인 하위 항목에 대한 알림, 개수 또는 상태 정보를 표시하는 데 사용됩니다.<br>정보는 배지 라벨에 텍스트로 표시됩니다. 텍스트가 제공되지 않으면 배지는 지름이 작은 원으로 표시됩니다.<br>텍스트가 제공되면 레이블은 높이가 Large_size와 동일한 StadiumBorder 모양의 배지입니다.<br>![img](https://flet.dev/img/docs/controls/badge/badge-navigation-bar.png) |
  | Canvas       | 캔버스는 선, 호, 경로 및 텍스트와 같은 기본 요소 또는 '모양' 세트를 사용하여 임의의 그래픽을 그리기 위한 컨트롤입니다.<br>![img](https://flet.dev/img/docs/controls/canvas/canvas-face.png) |
  | CircleAvatar | 사용자를 나타내는 원입니다.<br>일반적으로 사용자 프로필 이미지와 함께 사용되며, 해당 이미지가 없는 경우 사용자 이니셜과 함께 사용됩니다. 특정 사용자의 이니셜은 일관성을 위해 항상 동일한 배경색과 쌍을 이루어야 합니다.<br>foreground_image_url이 실패하면 background_image_url이 사용됩니다. background_image_url도 실패하면 bgcolor가 사용됩니다.<br>![img](https://flet.dev/img/docs/controls/circle-avatar/circle-avatar.png) |
  | Icon         | 재질 아이콘을 표시합니다.<br>![img](https://flet.dev/img/docs/controls/icon/custom-icons.png) |
  | Image        | 이미지는 사물(예: 사진 또는 일러스트레이션)을 그래픽으로 표현한 것입니다.<br>![img](https://flet.dev/img/docs/controls/image/custom-images.gif) |
  | Markdown     | 마크다운 형식의 텍스트 렌더링을 제어합니다.<br>![img](https://flet.dev/img/docs/controls/markdown/custom-markdown.gif) |
  | Text         | 텍스트는 텍스트를 표시하기 위한 컨트롤입니다.<br>![img](https://flet.dev/img/docs/controls/text/custom-text-styles.gif) |
  | ProgressBar  | 진행률 표시줄이라고도 알려진 머티리얼 디자인 선형 진행률 표시기입니다.<br>![img](https://flet.dev/img/docs/controls/progress-bar/custom-progress-bars.gif) |
  | ProgressRing | 애플리케이션이 사용 중임을 나타내기 위해 회전하는 머티리얼 디자인 순환 진행률 표시기입니다.<br>![img](https://flet.dev/img/docs/controls/progress-ring/custom-progress-rings.gif) |
  | WebView      | 사용자 상호 작용을 허용하면서 웹 페이지를 쉽게 로드할 수 있습니다. |

  

- #### Buttons

  | 컨트롤               | 설명                                                         |
  | -------------------- | ------------------------------------------------------------ |
  | ElevatedButton       | ElevatedButton 은 본질적으로 그림자로 채워진 톤 버튼입니다. 그림자 크리프를 방지하려면 버튼이 패턴이 있는 배경과 시각적으로 분리되어야 하는 경우와 같이 꼭 필요한 경우에만 사용하십시오. 자세한 내용은 머티리얼 3 버튼을 참조하세요.<br>![img](https://flet.dev/img/docs/controls/elevated-button/basic-elevated-buttons.png) |
  | FilledButton         | 채워진 버튼은 FloatingActionButton 다음으로 시각적으로 가장 큰 영향을 미치며 저장, 지금 참여 또는 확인과 같은 흐름을 완료하는 중요한 최종 작업에 사용해야 합니다. 자세한 내용은 머티리얼 3 버튼을 참조하세요.<br>![img](https://flet.dev/img/docs/controls/filled-button/basic-filled-buttons.png) |
  | FilledTonalButton    | 채워진 색조 버튼은 FilledButton과 OutlinedButton 버튼 사이의 대안적인 중간 지점입니다. 온보딩 흐름의 '다음'과 같이 우선순위가 낮은 버튼이 개요에서 제공하는 것보다 약간 더 강조해야 하는 상황에서 유용합니다. 색조 버튼은 보조 색상 매핑을 사용합니다. 자세한 내용은 머티리얼 3 버튼을 참조하세요.<br>![img](https://flet.dev/img/docs/controls/filled-tonal-button/basic-filled-tonal-buttons.png) |
  | FloatingActionButton | 머티리얼 디자인 플로팅 작업 버튼입니다. 플로팅 작업 버튼은 콘텐츠 위에 마우스를 올려 애플리케이션의 기본 작업을 승격시키는 원형 아이콘 버튼입니다. 플로팅 작업 버튼은 일반적으로 page.floating_action_button으로 설정되지만 페이지의 어느 위치에나 일반 컨트롤로 추가할 수도 있습니다.<br>![img](https://flet.dev/img/docs/controls/floatingactionbutton/custom-fab.gif) |
  | IconButton           | 아이콘 버튼은 색상(잉크)을 채워 터치에 반응하는 중앙에 아이콘이 있는 둥근 버튼입니다.<br>아이콘 버튼은 도구 모음에서 일반적으로 사용되지만 다른 여러 위치에서도 사용할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/icon-button/icon-buttons.gif) |
  | OutlinedButton       | 윤곽선 버튼은 중간 강조 버튼입니다. 여기에는 중요한 작업이 포함되어 있지만 앱의 기본 작업은 아닙니다. 윤곽선 버튼은 채워진 버튼과 잘 어울리며 대체 보조 작업을 나타냅니다. 자세한 내용은 머티리얼 3 버튼을 참조하세요.<br>![img](https://flet.dev/img/docs/controls/outlined-button/basic-outlined-buttons.png) |
  | PopupMenuButton      | 클릭하면 메뉴를 표시하는 아이콘 버튼입니다.<br>![img](https://flet.dev/img/docs/controls/popup-menu-button/popup-menu-button-with-custom-content.gif) |
  | SegmentedButton      | 사용자가 제한된 옵션 세트 중에서 선택할 수 있게 해주는 머티리얼 버튼은 일반적으로 옵션이 2<br/>5개만 있는 경우에 사용됩니다.<br>![img](https://flet.dev/img/docs/controls/segmented-button/segmented-button.png) |
  | TextButton           | 텍스트 버튼은 특히 여러 옵션을 표시할 때 우선순위가 가장 낮은 작업에 사용됩니다. 텍스트 버튼은 다양한 배경에 배치될 수 있습니다. 버튼과 상호작용할 때까지 해당 컨테이너는 표시되지 않습니다. 자세한 내용은 머티리얼 3 버튼을 참조하세요.<br>![img](https://flet.dev/img/docs/controls/text-button/basic-text-buttons.png) |

  

- #### Input and Selections

  | 컨트롤            | 설명                                                         |
  | ----------------- | ------------------------------------------------------------ |
  | Checkbox          | 확인란을 사용하면 그룹에서 하나 이상의 항목을 선택하거나 상호 배타적인 두 옵션(선택 또는 선택 취소, 설정 또는 해제) 간에 전환할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/checkbox/basic-checkbox.gif) |
  | Chip              | 칩은 속성, 텍스트, 엔터티 또는 작업을 나타내는 압축 요소입니다.<br>칩은 사람들이 정보를 입력하고, 선택하고, 콘텐츠를 필터링하고, 작업을 실행하는 데 도움이 됩니다. 칩을 사용하여 특정 상황에 대한 옵션을 표시하세요.<br>![img](https://flet.dev/img/docs/controls/chip/assist-chips.png) |
  | CupertinoCheckbox | macOS 스타일 체크박스. 확인란을 사용하면 그룹에서 하나 이상의 항목을 선택하거나 상호 배타적인 두 옵션(선택 또는 선택 취소, 설정 또는 해제) 간에 전환할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/cupertinocheckbox/cupertinocheckbox.png) |
  | CupertinoRadio    | macOS 스타일 라디오 버튼입니다. 라디오 버튼을 사용하면 두 개 이상의 선택 사항 중에서 단일 옵션을 선택할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/cupertinoradio/cupertino-radio-basic.png) |
  | CupertinoSlider   | macOS 스타일 슬라이더. 이는 조정 가능한 콘텐츠와 전체 콘텐츠 범위의 현재 설정을 시각적으로 표시합니다.<br>사람들이 정의된 값(예: 볼륨 또는 밝기)을 설정하도록 하려는 경우 또는 설정 변경 효과에 대한 즉각적인 피드백을 통해 사람들이 이점을 얻을 수 있는 경우 슬라이더를 사용하십시오.<br>![img](https://flet.dev/img/docs/controls/cupertinoslider/cupertino-slider-with-events.gif) |
  | CupertinoSwitch   | iOS 스타일 스위치.<br>단일 설정의 켜기/끄기 상태를 전환하는 데 사용됩니다.<br>토글은 상호 배타적인 두 가지 옵션 중에서 선택할 수 있는 물리적 스위치를 나타냅니다.<br>예를 들어 '켜기/끄기', '표시/숨기기' 등이 있습니다. 옵션을 선택하면 즉각적인 결과가 나타나야 합니다.<br>![img](https://flet.dev/img/docs/controls/cupertinoswitch/cupertino-switch.gif) |
  | Dropdown          | 항목 목록에서 선택하기 위한 머티리얼 디자인 버튼입니다.<br>드롭다운을 사용하면 사용자가 여러 항목 중에서 선택할 수 있습니다. 드롭다운에는 현재 선택한 항목과 다른 항목을 선택할 수 있는 메뉴를 여는 화살표가 표시됩니다.<br>![img](https://flet.dev/img/docs/controls/dropdown/basic-dropdown.gif) |
  | Radio             | 라디오 버튼을 사용하면 두 개 이상의 선택 사항 중에서 단일 옵션을 선택할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/radio/basic-radio.gif) |
  | RangeSlider       | 머티리얼 디자인 범위 슬라이더. 값 범위에서 범위를 선택하는 데 사용됩니다. 범위 슬라이더를 사용하여 연속 또는 불연속 값 집합 중에서 선택할 수 있습니다. 기본값은 최소에서 최대까지 연속적인 값 범위를 사용하는 것입니다.<br>![img](https://flet.dev/img/docs/controls/rangeslider/rangeslider.gif) |
  | SearchBar         | 머티리얼 디자인 검색창. 시각적으로 TextField처럼 보이지만 탭하면 검색 보기가 열린다는 차이점이 있습니다.<br>![img](https://flet.dev/img/docs/controls/search-bar/searchbar-basic.gif) |
  | Slider            | 슬라이더는 조정 가능한 콘텐츠와 전체 콘텐츠 범위의 현재 설정을 시각적으로 표시합니다.<br>사람들이 정의된 값(예: 볼륨 또는 밝기)을 설정하도록 하려는 경우 또는 설정 변경 효과에 대한 즉각적인 피드백을 통해 사람들이 이점을 얻을 수 있는 경우 슬라이더를 사용하십시오.<br>![img](https://flet.dev/img/docs/controls/slider/slider-with-custom-content.gif) |
  | Switch            | 토글은 상호 배타적인 두 가지 옵션 중에서 선택할 수 있는 물리적 스위치를 나타냅니다.<br>예를 들어 '켜기/끄기', '표시/숨기기' 등이 있습니다. 옵션을 선택하면 즉각적인 결과가 나타나야 합니다.<br>![img](https://flet.dev/img/docs/controls/switch/basic-switch.gif) |
  | TextField         | 머티리얼 디자인 텍스트 필드.<br>텍스트 필드를 사용하면 사용자는 하드웨어 키보드나 화면 키보드를 사용하여 텍스트를 입력할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/textfield/basic-textfield.gif) |

  

- #### Dialogs, Alerts and Panels

  | 컨트롤      | 설명                                                         |
  | ----------- | ------------------------------------------------------------ |
  | AlertDialog | 머티리얼 디자인 경고 대화상자. <br>경고 대화 상자는 확인이 필요한 상황을 사용자에게 알려줍니다. 경고 대화 상자에는 선택적 제목과 선택적 작업 목록이 있습니다. 제목은 콘텐츠 위에 표시되고 작업은 콘텐츠 아래에 표시됩니다.<br>![img](https://flet.dev/img/docs/controls/alertdialog/alertdialog-with-custom-content.gif) |
  | Banner      | 배너는 중요하고 간결한 메시지를 표시하며 사용자가 해결하거나 배너를 닫을 수 있는 작업을 제공합니다. 이를 닫으려면 사용자 작업이 필요합니다.<br>배너는 화면 상단, 상단 앱 바 아래에 표시됩니다. 이는 지속적이고 비모달이므로 사용자가 언제든지 이를 무시하거나 상호 작용할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/banner/banner-with-custom-content.gif) |
  | BottomSheet | 모달 머티리얼 디자인 하단 시트를 표시합니다.<br>모달 하단 시트는 메뉴나 대화 상자의 대안이며 사용자가 앱의 나머지 부분과 상호 작용하는 것을 방지합니다.<br>![img](https://flet.dev/img/docs/controls/bottom-sheet/bottom-sheet-sample.gif) |
  | DatePicker  | 머티리얼 스타일 날짜 선택기 대화상자.<br>page.overlay에 추가되고 pick_date() 메소드를 사용하여 호출됩니다.<br>date_picker_entry_mode에 따라 날짜 선택을 위한 달력 또는 입력(TextField)이 표시됩니다.<br>![img](https://flet.dev/img/docs/controls/datepicker/basic-datepicker.png) |
  | SnackBar    | 화면 하단에 간략하게 표시되는 선택적 작업이 포함된 간단한 메시지입니다.<br>![img](https://flet.dev/img/docs/controls/snackbar/snackbar-with-custom-content.gif) |
  | TimePicker  | 머티리얼 스타일 시간 선택 도구 대화상자.<br> page.overlay에 추가되고 pick_time() 메서드를 사용하여 호출됩니다.<br>time_picker_entry_mode에 따라 시간 선택을 위한 다이얼 또는 입력(시간 및 분 텍스트 필드)이 표시됩니다.<br>![img](https://flet.dev/img/docs/controls/timepicker/time-picker.png) |

  

- #### Charts

  | 컨트롤          | 설명                                                         |
  | --------------- | ------------------------------------------------------------ |
  | BarChart        | 막대 차트를 그립니다.<br>![img](https://flet.dev/img/docs/controls/charts/barchart-sample-1.png) |
  | LineChart       | 꺾은선형 차트를 그립니다.<br>![img](https://flet.dev/img/docs/controls/charts/linechart-sample-1.gif) |
  | MatplotlibChart | Matplotlib 차트를 표시합니다.<br>![img](https://flet.dev/img/docs/controls/charts/matplotlib-barchart.png) |
  | PieChart        | 원형 차트를 그립니다.<br>![img](https://flet.dev/img/docs/controls/charts/piechart-sample-1.gif) |
  | PlotlyChart     | Plotly 차트를 표시합니다.<br>![img](https://flet.dev/img/docs/controls/charts/plotly-linechart.png) |

  

- #### Animations

  | 컨트롤           | 설명                                                         |
  | ---------------- | ------------------------------------------------------------ |
  | AnimatedSwitcher | 기본적으로 새 컨트롤과 이전에 AnimatedSwitcher에 콘텐츠로 설정된 컨트롤 간에 크로스페이드를 수행하는 컨트롤입니다.<br>![img](https://flet.dev/img/docs/controls/animated-switcher/animated-switcher.gif) |

  

- #### Utility

| 컨트롤             | 설명                                                         |
| ------------------ | ------------------------------------------------------------ |
| Audio              | 여러 오디오 파일을 동시에 재생하는 컨트롤입니다. macOS, Linux, Windows, iOS, Android 및 웹에서 작동합니다. 오디오 플레이어 Flutter 위젯을 기반으로 합니다.<br>오디오 컨트롤은 시각적이지 않으며 page.overlay 목록에 추가되어야 합니다. |
| Draggable          | DragTarget으로 드래그할 수 있는 컨트롤입니다.<br>드래그 가능한 컨트롤이 드래그 동작의 시작을 인식하면 화면에서 사용자의 손가락을 추적하는 content_feedback 컨트롤을 표시합니다. 사용자가 DragTarget 위에서 손가락을 떼면 해당 대상에 드래그 앤 드롭 흐름을 완료할 수 있는 기회가 제공됩니다.<br>이 컨트롤은 제로 드래그가 진행 중일 때 콘텐츠를 표시합니다. content_when_draging이 null이 아닌 경우 이 컨트롤은 하나 이상의 드래그가 진행 중일 때 대신 content_when_draging을 표시합니다. 그렇지 않으면 이 위젯은 항상 콘텐츠를 표시합니다.<br>![img](https://flet.dev/img/docs/controls/drag-and-drop/drag-and-drop-colors.gif) |
| DragTarget         | Draggable 위젯을 놓을 때 드래그 작업을 완료하는 컨트롤입니다.<br>드래그 대상 위에 드래그 가능 항목을 드래그하면 드래그 대상에 드래그 가능 항목이 전달하는 데이터를 허용할지 여부를 묻는 메시지가 표시됩니다. 드래그 대상은 드래그 가능과 동일한 그룹에 속하는 경우 들어오는 드래그를 허용합니다. 사용자가 드래그 대상 위에 드래그 가능 항목을 놓으면(그리고 드래그 대상이 드래그 가능 데이터를 허용한다고 표시한 경우) 드래그 대상은 드래그 가능 데이터를 허용하도록 요청됩니다.<br>![img](https://flet.dev/img/docs/controls/drag-and-drop/drag-and-drop-colors.gif) |
| FilePicker         | 확장 필터링 지원 및 업로드를 통해 기본 파일 탐색기를 사용하여 단일 또는 여러 파일을 선택할 수 있는 컨트롤입니다.<br>Linux에서 Flet을 앱으로 실행할 때 FilePicker 컨트롤은 Zenity에 따라 달라집니다. 이는 브라우저에서 Flet을 실행할 때 요구 사항이 아닙니다.<br>![img](https://flet.dev/img/docs/controls/file-picker/file-picker-all-modes-demo.png) |
| FletApp            | HTML IFrame과 유사하지만 Flet용으로 현재 앱에서 다른 Flet 앱을 렌더링합니다. |
| GestureDetector    | 제스처를 감지하는 컨트롤입니다.<br>null이 아닌 콜백에 해당하는 동작을 인식하려고 시도합니다.<br>이 컨트롤에 콘텐츠가 있는 경우 크기 조정 동작은 해당 자식 컨트롤에 따릅니다. 컨텐츠가 없으면 대신 상위에 맞게 커집니다.<br>![img](https://flet.dev/img/docs/controls/gesture-detector/gesture-detector-two-containers.gif) |
| HapticFeedback     | 기기의 햅틱 피드백 인터페이스에 대한 액세스를 허용합니다.<br>시각적이지 않으며 page.overlay 목록에 추가해야 합니다. |
| SelectionArea      | Flet 컨트롤은 기본적으로 선택할 수 없습니다. SelectionArea는 콘텐츠 선택을 활성화하는 데 사용됩니다. |
| Semantics          | 위젯의 의미에 대한 설명으로 컨트롤 트리에 주석을 추가하는 컨트롤입니다.<br>접근성 도구, 검색 엔진 및 기타 의미 분석 소프트웨어에서 애플리케이션의 의미를 결정하는 데 사용됩니다. |
| ShaderMask         | 셰이더에 의해 생성된 마스크를 해당 하위 항목에 적용하는 컨트롤입니다.<br>예를 들어 ShaderMask를 사용하면 LinearGradient 마스크를 사용하여 자식의 가장자리를 점차적으로 페이드 아웃할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/shader-mask/shader-mask-pink-glow.png) |
| ShakeDetector      | 휴대폰 흔들림을 감지합니다.<br>시각적이지 않으며 page.overlay 목록에 추가해야 합니다. |
| Tooltip            | 머티리얼 디자인 툴팁.<br>도구 설명은 버튼의 기능이나 기타 사용자 인터페이스 작업을 설명하는 데 도움이 되는 텍스트 레이블을 제공합니다. 도구 설명 컨트롤에 버튼을 래핑하고 컨트롤을 길게 누를 때 표시될 메시지를 제공합니다.<br>![img](https://flet.dev/img/docs/controls/tooltip/custom-tooltip.gif) |
| TransparentPointer | TransparentPointer는 '스택의 두 위젯 사이의 모든 제스처를 통과하는 방법' 문제에 대한 솔루션입니다.<br>예를 들어 GestureDetector가 포함된 컨테이너 내부에 ElevatedButton이 있는 경우 버튼을 탭하면 뒤에 있는 제스처 감지기에 '표시'되지 않습니다. TransparentPointer를 사용하면 탭 이벤트가 버튼에서 멈추지 않고 HTML/JS의 이벤트 버블링과 유사하게 상위 이벤트로 이동합니다. |
| WindowDragArea     | 응용 프로그램 창을 이동, 최대화 및 복원하기 위한 드래그 컨트롤입니다.<br>page.window_title_bar_hidden을 사용하여 제목 표시줄을 숨긴 경우 이 컨트롤을 추가하여 창 위치를 이동할 수 있습니다.<br>![img](https://flet.dev/img/docs/controls/window-drag-area/no-title-draggable-window.png) |





