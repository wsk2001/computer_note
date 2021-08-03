# Django Widgets

출처: https://docs.djangoproject.com/en/3.2/ref/forms/widgets/



## Widgets

위젯은 Django의 HTML 입력 요소 표현입니다. 위젯은 HTML의 렌더링 및 위젯에 해당하는 GET / POST 사전에서 데이터 추출을 처리합니다.

내장 위젯에 의해 생성 된 HTML은! DOCTYPE html을 대상으로하는 HTML5 구문을 사용합니다. 예를 들어, 이는 checked = 'checked'의 XHTML 스타일이 아닌 check와 같은 부울 속성을 사용합니다.

```
Tip
위젯을 양식 필드와 혼동해서는 안됩니다. 양식 필드는 입력 유효성 검사 논리를 처리하며 템플릿에서 직접 사용됩니다. 위젯은 웹 페이지에서 HTML 양식 입력 요소의 렌더링 및 원시 제출 데이터 추출을 처리합니다. 그러나 양식 필드에 위젯을 할당해야합니다.
```



### 위젯 지정

양식에 필드를 지정할 때마다 Django는 표시 할 데이터 유형에 적합한 기본 위젯을 사용합니다. 어떤 필드에 어떤 위젯이 사용되는지 알아 보려면 [내장 필드 클래스](https://docs.djangoproject.com/en/3.2/ref/forms/fields/#built-in-fields)에 대한 문서를 참조하십시오.

그러나 필드에 다른 위젯을 사용하려는 경우 필드 정의에서 위젯 인수를 사용할 수 있습니다. 예를 들면 :

``` python
from django import forms

class CommentForm(forms.Form):
    name = forms.CharField()
    url = forms.URLField()
    comment = forms.CharField(widget=forms.Textarea)
```

이것은 기본 TextInput 위젯이 아닌 더 큰 Textarea 위젯을 사용하는 주석이있는 양식을 지정합니다.



### 위젯에 대한 인수 설정

많은 위젯에는 선택적 추가 인수가 있습니다. 필드에서 위젯을 정의 할 때 설정할 수 있습니다. 다음 예에서는 SelectDateWidget에 대해 years 속성이 설정됩니다.

``` python
from django import forms

BIRTH_YEAR_CHOICES = ['1980', '1981', '1982']
FAVORITE_COLORS_CHOICES = [
    ('blue', 'Blue'),
    ('green', 'Green'),
    ('black', 'Black'),
]

class SimpleForm(forms.Form):
    birth_year = forms.DateField(widget=forms.SelectDateWidget(years=BIRTH_YEAR_CHOICES))
    favorite_colors = forms.MultipleChoiceField(
        required=False,
        widget=forms.CheckboxSelectMultiple,
        choices=FAVORITE_COLORS_CHOICES,
    )
```

사용 가능한 위젯과 허용하는 인수에 대한 자세한 정보는 [내장 위젯](https://docs.djangoproject.com/en/3.2/ref/forms/widgets/#built-in-widgets)을 참조하십시오.



### 선택 위젯에서 상속 된 위젯

선택 위젯에서 상속 된 위젯은 선택 사항을 처리합니다. 사용자에게 선택할 수있는 옵션 목록을 제공합니다. 다른 위젯은 이 선택을 다르게 표시합니다. 선택 위젯 자체는 선택 HTML 목록 표현을 사용하는 반면 RadioSelect 는 라디오 버튼을 사용합니다.

선택 위젯은 ChoiceField 필드에서 기본적으로 사용됩니다. 위젯에 표시된 선택 사항은 ChoiceField에서 상속되며 ChoiceField.choices를 변경하면 Select.choices가 업데이트됩니다. 예를 들면 :

``` bash
>>> from django import forms
>>> CHOICES = [('1', 'First'), ('2', 'Second')]
>>> choice_field = forms.ChoiceField(widget=forms.RadioSelect, choices=CHOICES)
>>> choice_field.choices
[('1', 'First'), ('2', 'Second')]
>>> choice_field.widget.choices
[('1', 'First'), ('2', 'Second')]
>>> choice_field.widget.choices = []
>>> choice_field.choices = [('1', 'First and only')]
>>> choice_field.widget.choices
[('1', 'First and only')]
```

그러나 선택 속성을 제공하는 위젯은 선택을 기반으로하지 않는 필드 (예 : CharField)와 함께 사용할 수 있지만 선택 항목이 표현 위젯뿐 아니라 모델에 내재 된 경우 ChoiceField 기반 필드를 사용하는 것이 좋습니다.



### 위젯 인스턴스 사용자 정의

Django는 위젯을 HTML로 렌더링 할 때 매우 최소한의 마크업 만 렌더링합니다. Django는 클래스 이름이나 기타 위젯 관련 속성을 추가하지 않습니다. 예를 들어, 모든 TextInput 위젯이 웹 페이지에서 동일하게 나타납니다.

위젯을 사용자 정의하는 방법에는 위젯 인스턴스 당 및 위젯 클래스 당 두 가지가 있습니다.

#### 위젯 인스턴스 스타일 지정

하나의 위젯 인스턴스를 다른 것과 다르게 보이게하려면 위젯 개체가 인스턴스화되고 양식 필드에 할당 될 때 추가 속성을 지정해야합니다 (그리고 CSS 파일에 일부 규칙을 추가 할 수도 있음).

예를 들어, 다음 양식을 사용하십시오.

``` python
from django import forms

class CommentForm(forms.Form):
    name = forms.CharField()
    url = forms.URLField()
    comment = forms.CharField()
```

이 양식에는 기본 렌더링 (CSS 클래스 없음, 추가 속성 없음)이 있는 세 가지 기본 TextInput 위젯이 포함됩니다. 이는 각 위젯에 제공된 입력 상자가 정확히 동일하게 렌더링된다는 것을 의미합니다.

``` bash
>>> f = CommentForm(auto_id=False)
>>> f.as_table()
<tr><th>Name:</th><td><input type="text" name="name" required></td></tr>
<tr><th>Url:</th><td><input type="url" name="url" required></td></tr>
<tr><th>Comment:</th><td><input type="text" name="comment" required></td></tr>
```

실제 웹 페이지에서는 모든 위젯이 똑같이 보이기를 원하지 않을 것입니다. 주석에 대해 더 큰 입력 요소를 원할 수 있으며 '이름'위젯에 특수 CSS 클래스가 있기를 원할 수 있습니다. 새로운 HTML5 입력 유형을 활용하기 위해 'type'속성을 지정할 수도 있습니다. 이렇게하려면 위젯을 만들 때 Widget.attrs 인수를 사용합니다.

``` python
class CommentForm(forms.Form):
    name = forms.CharField(widget=forms.TextInput(attrs={'class': 'special'}))
    url = forms.URLField()
    comment = forms.CharField(widget=forms.TextInput(attrs={'size': '40'}))
```

양식 정의에서 위젯을 수정할 수도 있습니다.

``` python
class CommentForm(forms.Form):
    name = forms.CharField()
    url = forms.URLField()
    comment = forms.CharField()

    name.widget.attrs.update({'class': 'special'})
    comment.widget.attrs.update(size='40')
```

또는 필드가 양식에서 직접 선언되지 않은 경우 (예 : 모델 양식 필드) Form.fields 속성을 사용할 수 있습니다.

``` python
class CommentForm(forms.ModelForm):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['name'].widget.attrs.update({'class': 'special'})
        self.fields['comment'].widget.attrs.update(size='40')
```

Django는 렌더링 된 출력에 추가 속성을 포함합니다.

``` bash
>>> f = CommentForm(auto_id=False)
>>> f.as_table()
<tr><th>Name:</th><td><input type="text" name="name" class="special" required></td></tr>
<tr><th>Url:</th><td><input type="url" name="url" required></td></tr>
<tr><th>Comment:</th><td><input type="text" name="comment" size="40" required></td></tr>
```

Attrs를 사용하여 HTML ID를 설정할 수도 있습니다. 예제는 [BoundField.id_for_label](https://docs.djangoproject.com/en/3.2/ref/forms/api/#django.forms.BoundField.id_for_label)을 참조하십시오.



#### 스타일링 위젯 클래스

위젯을 사용하면 자산 (css 및 javascript)을 추가하고 모양과 동작을보다 심도있게 사용자 지정할 수 있습니다.

요컨대, 위젯을 하위 클래스로 만들고 'Media'내부 클래스를 정의하거나 'media'속성을 만들어야합니다.

이러한 메서드는 다소 고급 Python 프로그래밍을 포함하며 [Form Assets](https://docs.djangoproject.com/en/3.2/topics/forms/media/) 항목 가이드에 자세히 설명되어 있습니다.



### 기본 위젯 클래스

기본 위젯 클래스 Widget 및 MultiWidget은 모든 기본 제공 위젯에 의해 하위 클래스로 지정되며 사용자 정의 위젯의 기반으로 사용할 수 있습니다.

### `Widget`

#### class Widget (attrs=None)

이 추상 클래스는 렌더링 할 수 없지만 기본 속성 속성을 제공합니다. 사용자 정의 위젯에서 render () 메서드를 구현하거나 재정의 할 수도 있습니다.

**attrs**

렌더링 된 위젯에 설정할 HTML 속성이 포함 된 dictionary  입니다.

``` python
>>> from django import forms
>>> name = forms.TextInput(attrs={'size': 10, 'title': 'Your name'})
>>> name.render('name', 'A name')
'<input title="Your name" type="text" name="name" value="A name" size="10">'
```

True 또는 False 값을 속성에 할당하면 HTML5 부울 속성으로 렌더링됩니다.

``` bash
>>> name = forms.TextInput(attrs={'required': True})
>>> name.render('name', 'A name')
'<input name="name" type="text" value="A name" required>'
>>>
>>> name = forms.TextInput(attrs={'required': False})
>>> name.render('name', 'A name')
'<input name="name" type="text" value="A name">'
```

**supports_microseconds**

기본값이 True 인 속성입니다. False로 설정하면 datetime 및 시간 값의 마이크로 초 부분이 0으로 설정됩니다.

**format_value(value)**

위젯 템플릿에서 사용할 값을 정리하고 반환합니다. 값이 유효한 입력이라고 보장 할 수 없으므로 하위 클래스 구현은 방어 적으로 프로그래밍해야합니다.

**get_context(name, value, attrs)**

위젯 템플릿을 렌더링 할 때 사용할 값 사전을 반환합니다. 기본적으로 사전에는 다음 키를 포함하는 위젯의 사전 표현 인 단일 키 'widget'이 포함됩니다.

- `'name'`:   Name 인수의 필드 이름입니다.
- `'is_hidden'`:  이 위젯이 숨겨져 있는지 여부를 나타내는 부울입니다.
- `'required'`:  이 위젯의 필드가 필요한지 여부를 나타내는 부울입니다.
- `'value'`:  format_value ()에서 반환 된 값입니다.
- `'attrs'`:  렌더링 된 위젯에 설정할 HTML 속성입니다. attrs 속성과 attrs 인수의 조합.
- `'template_name'`: self.template_name의 값입니다.

위젯 하위 클래스는이 메서드를 재정 의하여 사용자 지정 컨텍스트 값을 제공 할 수 있습니다.



