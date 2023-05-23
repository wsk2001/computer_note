# scikit-learn 튜토리얼

출처: https://scikit-learn.org/stable/tutorial/index.html

- scikit-learn을 사용한 기계 학습 소개
  - [기계 학습: 문제 설정](https://scikit-learn.org/stable/tutorial/basic/tutorial.html#machine-learning-the-problem-setting)
  - [예시 데이터세트 로드](https://scikit-learn.org/stable/tutorial/basic/tutorial.html#loading-an-example-dataset)
  - [학습 및 예측](https://scikit-learn.org/stable/tutorial/basic/tutorial.html#learning-and-predicting)
  - [컨벤션](https://scikit-learn.org/stable/tutorial/basic/tutorial.html#conventions)
- 과학적 데이터 처리를 위한 통계 학습 튜토리얼
  - [통계적 학습: scikit-learn의 설정 및 추정기 객체](https://scikit-learn.org/stable/tutorial/statistical_inference/settings.html)
  - [감독 학습: 고차원 관찰에서 출력 변수 예측](https://scikit-learn.org/stable/tutorial/statistical_inference/supervised_learning.html)
  - [모델 선택: 추정량 및 매개변수 선택](https://scikit-learn.org/stable/tutorial/statistical_inference/model_selection.html)
  - [비지도 학습: 데이터 표현 찾기](https://scikit-learn.org/stable/tutorial/statistical_inference/unsupervised_learning.html)
  - [함께 모아서](https://scikit-learn.org/stable/tutorial/statistical_inference/putting_together.html)
- 텍스트 데이터 작업
  - [튜토리얼 설정](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#tutorial-setup)
  - [20개의 뉴스 그룹 데이터 세트 로드](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#loading-the-20-newsgroups-dataset)
  - [텍스트 파일에서 특징 추출](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#extracting-features-from-text-files)
  - [분류기 훈련](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#training-a-classifier)
  - [파이프라인 구축](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#building-a-pipeline)
  - [테스트 세트에 대한 성능 평가](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#evaluation-of-the-performance-on-the-test-set)
  - [그리드 검색을 이용한 파라미터 튜닝](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#parameter-tuning-using-grid-search)
  - [연습 1: 언어 식별](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#exercise-1-language-identification)
  - [연습 2: 영화 리뷰에 대한 감정 분석](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#exercise-2-sentiment-analysis-on-movie-reviews)
  - [연습 3: CLI 텍스트 분류 유틸리티](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#exercise-3-cli-text-classification-utility)
  - [여기에서 어디로](https://scikit-learn.org/stable/tutorial/text_analytics/working_with_text_data.html#where-to-from-here)
- [올바른 추정기 선택](https://scikit-learn.org/stable/tutorial/machine_learning_map/index.html)
- 외부 리소스, 비디오 및 대화
  - [사이언티픽 파이썬이 처음이신가요?](https://scikit-learn.org/stable/presentations.html#new-to-scientific-python)
  - [외부 튜토리얼](https://scikit-learn.org/stable/presentations.html#external-tutorials)
  - [비디오](https://scikit-learn.org/stable/presentations.html#videos)



## [scikit-learn을 사용한 기계 학습 소개](https://scikit-learn.org/stable/tutorial/basic/tutorial.html)

섹션 내용

이 섹션에서는 scikit-learn에서 사용하는 [기계 학습](https://en.wikipedia.org/wiki/Machine_learning) 어휘를 소개 하고 간단한 학습 예제를 제공합니다.

## 기계 학습: 문제 설정 

일반적으로 학습 문제는 n개의 데이터 [샘플](https://en.wikipedia.org/wiki/Sample_(statistics)) 집합을 고려한 다음 알려지지 않은 데이터의 속성을 예측하려고 시도합니다. 각 샘플이 단일 숫자 이상이고 예를 들어 다차원 항목(일명 [다변량 데이터)인 경우 여러 속성 또는 ](https://en.wikipedia.org/wiki/Multivariate_random_variable)**기능이** 있다고 합니다 .

학습 문제는 몇 가지 범주로 나뉩니다.

> - 예측하려는 추가 속성과 함께 데이터가 제공되는 [지도 학습 ( ](https://en.wikipedia.org/wiki/Supervised_learning)[여기를 클릭하여](https://scikit-learn.org/stable/supervised_learning.html#supervised-learning) scikit-learn 지도 학습 페이지로 이동). 이 문제는 다음 중 하나일 수 있습니다.
>
>   > - [분류](https://en.wikipedia.org/wiki/Classification_in_machine_learning) : 샘플은 둘 이상의 클래스에 속하며 **레이블이 지정되지 않은 데이터의 클래스를 예측하는 방법**을 이미 레이블이 지정된 데이터에서 학습하려고 합니다. 분류 문제의 예로 손으로 쓴 숫자 인식이 있는데, 여기서 목표는 각 입력 벡터를 유한한 수의 이산 범주 중 하나에 할당하는 것입니다. 분류를 생각하는 또 다른 방법은 제한된 수의 범주가 있고 제공된 n개의 샘플 각각에 대해 올바른 범주 또는 클래스로 레이블을 지정하는 지도 학습의 이산(연속적이지 않은) 형태로 생각하는 것입니다. .
>   > - [회귀](https://en.wikipedia.org/wiki/Regression_analysis) : 원하는 출력이 하나 이상의 연속 변수로 구성된 경우 작업을 *회귀* 라고 합니다 . 회귀 문제의 예는 나이와 무게의 함수로 연어의 길이를 예측하는 것입니다.
>
> - 훈련 데이터가 해당 목표 값이 없는 입력 벡터 세트 x로 구성되는 [비지도 학습 . ](https://en.wikipedia.org/wiki/Unsupervised_learning)[이러한 문제의 목표는 클러스터링](https://en.wikipedia.org/wiki/Cluster_analysis) 이라고 하는 데이터 내에서 유사한 예의 그룹을 발견하거나 [밀도 추정](https://en.wikipedia.org/wiki/Density_estimation) 으로 알려진 입력 공간 내의 데이터 분포를 결정하거나 고차원에서 데이터를 투영하는 것일 수 있습니다. *시각화를* 위해 2차원 또는 3차원으로 공간을 줄입니다 ( Scikit-Learn 비지도 학습 페이지로 이동하려면 [여기를 클릭 ).](https://scikit-learn.org/stable/unsupervised_learning.html#unsupervised-learning)

트레이닝 세트와 테스트 세트

기계 학습은 데이터 세트의 일부 속성을 학습한 다음 해당 속성을 다른 데이터 세트에 대해 테스트하는 것입니다. 기계 학습의 일반적인 관행은 데이터 세트를 둘로 분할하여 알고리즘을 평가하는 것입니다. 우리는 그 세트 중 하나를 **training set 이라고** 부르며 여기에서 몇 가지 속성을 학습합니다. 학습된 속성을 테스트하는 다른 세트를 **테스트 세트 라고 합니다.**



## 예시 데이터셋 불러오기 

`scikit-learn`[예를 들어 분류를 위한 홍채](https://en.wikipedia.org/wiki/Iris_flower_data_set) 및 [숫자](https://archive.ics.uci.edu/ml/datasets/Pen-Based+Recognition+of+Handwritten+Digits) 데이터 세트와 회귀를 위한 [당뇨병 데이터 세트](https://www4.stat.ncsu.edu/~boos/var.select/diabetes.html) 와 같은 몇 가지 표준 데이터 세트가 함께 제공됩니다 .

다음에서는 셸에서 Python 인터프리터를 시작한 다음 `iris`및 `digits`데이터 세트를 로드합니다. 우리의 표기 규칙은 `$`셸 프롬프트를 나타내고 `>>>`Python 인터프리터 프롬프트를 나타냅니다.

```
$ python
>>> from sklearn import datasets
>>> iris = datasets.load_iris()
>>> digits = datasets.load_digits()
```

데이터세트는 모든 데이터와 데이터에 대한 일부 메타데이터를 보유하는 사전과 같은 객체입니다. `.data`이 데이터는 배열 인 구성원 에 저장됩니다 . 감독 문제의 경우 하나 이상의 응답 변수가 멤버에 저장됩니다. 다양한 데이터 세트에 대한 자세한 내용은 [전용 섹션](https://scikit-learn.org/stable/datasets.html#datasets) 에서 확인할 수 있습니다 .`n_samples, n_features``.target`

예를 들어 숫자 데이터셋의 경우 `digits.data`숫자 샘플을 분류하는 데 사용할 수 있는 기능에 대한 액세스를 제공합니다.

\>>>

```
>>> print(digits.data)
[[ 0.   0.   5. ...   0.   0.   0.]
 [ 0.   0.   0. ...  10.   0.   0.]
 [ 0.   0.   0. ...  16.   9.   0.]
 ...
 [ 0.   0.   1. ...   6.   0.   0.]
 [ 0.   0.   2. ...  12.   0.   0.]
 [ 0.   0.  10. ...  12.   1.   0.]]
```

그리고 `digits.target`우리가 배우려고 하는 각 숫자 이미지에 해당하는 숫자인 숫자 데이터 세트에 대한 실측 정보를 제공합니다.

\>>>

```
>>> digits.target
array([0, 1, 2, ..., 8, 9, 8])
```

데이터 배열의 형태

원본 데이터의 모양이 다를 수 있지만 데이터는 항상 2D 배열인 shape 입니다 . 숫자의 경우 각 원본 샘플은 모양의 이미지 이며 다음을 사용하여 액세스할 수 있습니다.`(n_samples, n_features)``(8, 8)`

\>>>

```
>>> digits.images[0]
array([[  0.,   0.,   5.,  13.,   9.,   1.,   0.,   0.],
       [  0.,   0.,  13.,  15.,  10.,  15.,   5.,   0.],
       [  0.,   3.,  15.,   2.,   0.,  11.,   8.,   0.],
       [  0.,   4.,  12.,   0.,   0.,   8.,   8.,   0.],
       [  0.,   5.,   8.,   0.,   0.,   9.,   8.,   0.],
       [  0.,   4.,  11.,   0.,   1.,  12.,   7.,   0.],
       [  0.,   2.,  14.,   5.,  10.,  12.,   0.,   0.],
       [  0.,   0.,   6.,  13.,  10.,   0.,   0.,   0.]])
```

[이 데이터 세트의 간단한 예는](https://scikit-learn.org/stable/auto_examples/classification/plot_digits_classification.html#sphx-glr-auto-examples-classification-plot-digits-classification-py) 원래 문제에서 시작하여 scikit-learn에서 사용할 데이터를 형성하는 방법을 보여줍니다.

외부 데이터세트에서 로드

[외부 데이터 세트에서 로드하려면 외부 데이터 세트 로드를](https://scikit-learn.org/stable/datasets/loading_other_datasets.html#external-datasets) 참조하십시오 .

## 학습 및 예측 

숫자 데이터 세트의 경우 작업은 주어진 이미지가 나타내는 숫자를 예측하는 것입니다. *보이지 않는 샘플이 속하는 클래스를 예측할* 수 있도록 추정기 [에 ](https://en.wikipedia.org/wiki/Estimator)*적합한* 10개의 가능한 클래스(숫자 0에서 9까지) 각각의 샘플이 제공됩니다 .

scikit-learn에서 분류를 위한 추정기는 메서드 및 를 구현하는 Python 개체입니다 .`fit(X, y)``predict(T)`

추정기의 예는 [지원 벡터 분류를](https://en.wikipedia.org/wiki/Support_vector_machine)`sklearn.svm.SVC` 구현하는 클래스입니다 . 추정기의 생성자는 모델의 매개변수를 인수로 사용합니다.

지금은 추정기를 블랙 박스로 간주합니다.

\>>>

```
>>> from sklearn import svm
>>> clf = svm.SVC(gamma=0.001, C=100.)
```

모델의 매개변수 선택

이 예에서는 값을 `gamma`수동으로 설정합니다. [이러한 매개변수에 대한 좋은 값을 찾기 위해 그리드 검색](https://scikit-learn.org/stable/modules/grid_search.html#grid-search) 및 [교차 검증](https://scikit-learn.org/stable/modules/cross_validation.html#cross-validation) 과 같은 도구를 사용할 수 있습니다 .

(분류기용) 추정기 인스턴스 `clf`가 먼저 모델에 맞춰집니다. 즉, 모델에서 *배워야 합니다.* 이는 교육 세트를 메서드에 전달하여 수행됩니다 `fit`. 학습 세트의 경우 예측을 위해 예약할 마지막 이미지를 제외하고 데이터 세트의 모든 이미지를 사용합니다. `[:-1]`다음에서 마지막 항목을 제외한 모든 항목을 포함하는 새 배열을 생성하는 Python 구문 으로 훈련 세트를 선택합니다 `digits.data`.

\>>>

```
>>> clf.fit(digits.data[:-1], digits.target[:-1])
SVC(C=100.0, gamma=0.001)
```

이제 새 값을 *예측할* 수 있습니다 . 이 경우 의 마지막 이미지를 사용하여 예측합니다 `digits.data`. 예측을 통해 훈련 세트에서 마지막 이미지와 가장 일치하는 이미지를 결정합니다.

\>>>

```
>>> clf.predict(digits.data[-1:])
array([8])
```

해당 이미지는 다음과 같습니다.

[![../../_images/sphx_glr_plot_digits_last_image_001.png](https://scikit-learn.org/stable/_images/sphx_glr_plot_digits_last_image_001.png)](https://scikit-learn.org/stable/auto_examples/datasets/plot_digits_last_image.html)

보시다시피 어려운 작업입니다. 결국 이미지의 해상도가 좋지 않습니다. 분류 기준에 동의하십니까?

이 분류 문제의 완전한 예는 실행하고 학습할 수 있는 예로 사용할 수 있습니다. [손으로 쓴 숫자 인식](https://scikit-learn.org/stable/auto_examples/classification/plot_digits_classification.html#sphx-glr-auto-examples-classification-plot-digits-classification-py) .

## 규약 

scikit-learn 추정기는 특정 규칙을 따라 동작을 보다 예측 가능하게 만듭니다. [이러한 내용은 일반 용어 및 API 요소 용어집](https://scikit-learn.org/stable/glossary.html#glossary) 에 자세히 설명되어 있습니다 .

### 타입 캐스팅 

가능한 경우 유형의 입력은 `float32`해당 데이터 유형을 유지합니다. 그렇지 않으면 입력이 다음으로 변환됩니다 `float64`.

\>>>

```
>>> import numpy as np
>>> from sklearn import kernel_approximation

>>> rng = np.random.RandomState(0)
>>> X = rng.rand(10, 2000)
>>> X = np.array(X, dtype='float32')
>>> X.dtype
dtype('float32')

>>> transformer = kernel_approximation.RBFSampler()
>>> X_new = transformer.fit_transform(X)
>>> X_new.dtype
dtype('float32')
```

이 예에서 `X`는 이고 `float32`는 에 의해 변경되지 않습니다 `fit_transform(X)`.

유형이 지정된 교육(또는 테스트) 데이터를 사용하는 것이 `float32`보통 사용하는 것보다 더 효율적인 경우가 많습니다 `float64` `dtype`. 메모리 사용량을 줄이고 CPU의 벡터 명령을 활용하여 처리 시간을 줄이는 경우도 있습니다. 그러나 때때로 알고리즘이 값의 척도에 더 민감해지고 [적절한 사전 처리가 필요한](https://scikit-learn.org/stable/modules/preprocessing.html#preprocessing-scaler) 수치적 안정성 문제로 이어질 수 있습니다 .

그러나 모든 scikit-learn 추정기가 `float32`모드에서 작업을 시도하는 것은 아닙니다. 예를 들어 일부 변환기는 항상 입력을 변환 `float64`하고 `float64`결과로 변환된 값을 반환합니다.

회귀 대상은 캐스트되고 `float64`분류 대상은 유지됩니다.

\>>>

```
>>> from sklearn import datasets
>>> from sklearn.svm import SVC
>>> iris = datasets.load_iris()
>>> clf = SVC()
>>> clf.fit(iris.data, iris.target)
SVC()

>>> list(clf.predict(iris.data[:3]))
[0, 0, 0]

>>> clf.fit(iris.data, iris.target_names[iris.target])
SVC()

>>> list(clf.predict(iris.data[:3]))
['setosa', 'setosa', 'setosa']
```

여기서 첫 번째는 (정수 배열)이 에서 사용되었기 `predict()`때문에 정수 배열을 반환합니다 . 두 번째는 피팅을 위한 것이기 때문에 문자열 배열을 반환합니다 .`iris.target``fit``predict()``iris.target_names`

### 매개변수 수정 및 업데이트 

[추정기의 하이퍼 매개변수는 set_params()](https://scikit-learn.org/stable/glossary.html#term-set_params) 메서드 를 통해 구성한 후 업데이트할 수 있습니다 . `fit()`두 번 이상 호출하면 이전에 배운 내용을 덮어씁니다 `fit()`.

\>>>

```
>>> import numpy as np
>>> from sklearn.datasets import load_iris
>>> from sklearn.svm import SVC
>>> X, y = load_iris(return_X_y=True)

>>> clf = SVC()
>>> clf.set_params(kernel='linear').fit(X, y)
SVC(kernel='linear')
>>> clf.predict(X[:5])
array([0, 0, 0, 0, 0])

>>> clf.set_params(kernel='rbf').fit(X, y)
SVC()
>>> clf.predict(X[:5])
array([0, 0, 0, 0, 0])
```

여기서 기본 커널은 estimator가 생성된 후 먼저 via `rbf`로 변경되고 , estimator를 다시 맞추고 두 번째 예측을 하기 위해 다시 로 변경됩니다.`linear`[`SVC.set_params()`](https://scikit-learn.org/stable/modules/generated/sklearn.svm.SVC.html#sklearn.svm.SVC.set_params)`rbf`

### 다중 클래스 대 다중 레이블 피팅 

를 사용할 때 수행되는 학습 및 예측 작업은 다음에 맞는 대상 데이터의 형식에 따라 다릅니다.[`multiclass classifiers`](https://scikit-learn.org/stable/modules/classes.html#module-sklearn.multiclass)

\>>>

```
>>> from sklearn.svm import SVC
>>> from sklearn.multiclass import OneVsRestClassifier
>>> from sklearn.preprocessing import LabelBinarizer

>>> X = [[1, 2], [2, 4], [4, 5], [3, 2], [3, 1]]
>>> y = [0, 0, 1, 1, 2]

>>> classif = OneVsRestClassifier(estimator=SVC(random_state=0))
>>> classif.fit(X, y).predict(X)
array([0, 0, 1, 1, 2])
```

위의 경우 분류자는 다중 클래스 레이블의 1d 배열에 적합하므로 `predict()`메서드는 해당 다중 클래스 예측을 제공합니다. 이진 레이블 표시기의 2d 배열에 맞출 수도 있습니다.

\>>>

```
>>> y = LabelBinarizer().fit_transform(y)
>>> classif.fit(X, y).predict(X)
array([[1, 0, 0],
       [1, 0, 0],
       [0, 1, 0],
       [0, 0, 0],
       [0, 0, 0]])
```

여기에서 분류자는 를 사용하여 `fit()` 의 2d 이진 레이블 표현에 있습니다 . 이 경우 해당 다중 레이블 예측을 나타내는 2d 배열을 반환합니다.`y`[`LabelBinarizer`](https://scikit-learn.org/stable/modules/generated/sklearn.preprocessing.LabelBinarizer.html#sklearn.preprocessing.LabelBinarizer)`predict()`

네 번째와 다섯 번째 인스턴스는 모두 0을 반환하여 세 레이블 중 어느 것도 일치하지 않았음을 나타냅니다 `fit`. 다중 레이블 출력을 사용하면 유사하게 인스턴스에 여러 레이블을 할당할 수 있습니다.

\>>>

```
>>> from sklearn.preprocessing import MultiLabelBinarizer
>>> y = [[0, 1], [0, 2], [1, 3], [0, 2, 3], [2, 4]]
>>> y = MultiLabelBinarizer().fit_transform(y)
>>> classif.fit(X, y).predict(X)
array([[1, 1, 0, 0, 0],
       [1, 0, 1, 0, 0],
       [0, 1, 0, 1, 0],
       [1, 0, 1, 0, 0],
       [1, 0, 1, 0, 0]])
```

이 경우 분류자는 각각 여러 레이블이 할당된 인스턴스에 적합합니다. 는 [`MultiLabelBinarizer`](https://scikit-learn.org/stable/modules/generated/sklearn.preprocessing.MultiLabelBinarizer.html#sklearn.preprocessing.MultiLabelBinarizer)다중 레이블의 2d 배열을 `fit`on으로 이진화하는 데 사용됩니다. 결과적으로 `predict()`각 인스턴스에 대해 여러 예측 레이블이 있는 2d 배열을 반환합니다.