# Scikit-learn 용 Python 치트 시트

기계 학습을위한 강력한 라이브러리를 찾고있는 Python 프로그래머입니까? 그렇다면 scikit-learn을 고려해야합니다. Scikit은 Python에서 학습하는 것이 기계 학습의 개념에서 필수적인 역할을하며 Python for ***Data Science 인증\*** 을 획득하는 데 필요합니다 . 이 scikit-learn 치트 시트는 이미 Python 패키지에 대해 배우기 시작했지만 편리한 참조 시트를 원하는 사람을 위해 설계되었습니다. 초보자이고 scikit-learn의 작동 방식에 대해 전혀 모르더라도 걱정하지 마십시오. 기계 학습을위한이 scikit-learn 치트 시트는 시작하기 위해 알아야 할 기본 사항에 대한 빠른 참조를 제공합니다.

# Scikit-learn 용 Python 치트 시트

![img](https://ichi.pro/assets/images/max/724/1*vGMly_LJYujmKKkiZRuHLw.png)

Scikit-learn은 기계 학습, 전처리, 교차 검증 및 시각화 알고리즘에 사용되는 오픈 소스 Python 라이브러리입니다. Python에서 다양한지도 및 비지도 학습 알고리즘을 제공합니다.

![img](https://ichi.pro/assets/images/max/724/1*ebqtgwbBj193lBM4Of0tfQ.png)

# 기본 예

scikit-learn 라이브러리를 사용하여 기본 예제를 만들어 보겠습니다.

- 데이터로드
- 데이터를 훈련 및 테스트로 나누고,
- KNN 알고리즘을 사용하여 데이터를 훈련시키고,
- 결과 예측
- 

- ```
  >>> from sklearn import neighbors, datasets, preprocessing
  >>> from sklearn.model_selection import train_test_split
  >>> from sklearn.metrics import accuracy_score
  >>> iris = datasets.load_iris() 
  >>> X, y = iris.data[:, :2], iris.target
  >>> X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=33)
  >>> scaler = preprocessing.StandardScaler().fit(X_train)
  >>> X_train = scaler.transform(X_train) 
  >>> X_test = scaler.transform(X_test)
  >>> knn = neighbors.KNeighborsClassifier(n_neighbors=5)
  >>> knn.fit(X_train, y_train)
  >>> y_pred = knn.predict(X_test)
  >>> accuracy_score(y_test, y_pred)
  ```

- 



```
>>> import numpy as np
>>> X = np.random.random((10,5))
>>> y = np.array(['M','M','F','F','M','F','M','M','F','F','F'])
>>> X[X < 0.7] = 0
```



데이터가로드되면 다음 작업은 데이터 세트를 훈련 데이터와 테스트 데이터로 분할합니다.



```
>>> from sklearn.model_selection import train_test_split
>>> X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=0)
```



## 표준화

데이터 **표준화** 는 속성의 평균값이 0이고 표준 편차가 1이되도록 하나 이상의 속성을 다시 조정하는 데 사용되는 데이터 전처리 단계 중 하나입니다. **표준화** 는 데이터에 가우스 (종형 곡선) 분포가 있다고 가정합니다.



```
>>> from sklearn.preprocessing import StandardScaler
>>> scaler = StandardScaler().fit(X_train)
>>> standardized_X = scaler.transform(X_train)
>>> standardized_X_test = scaler.transform(X_test)
```





**정규화** 는 **기계 학습을** 위한 데이터 준비에 일반적으로 사용되는 기술 **입니다** . **정규화** 의 주요 목표 는 정보 손실이나 값 범위의 차이를 왜곡하지 않고 공통 척도를 가질 수 있도록 데이터 세트의 숫자 열 값을 변경하는 것입니다.



```
>>> from sklearn.preprocessing import Normalizer
>>> scaler = Normalizer().fit(X_train)
>>> normalized_X = scaler.transform(X_train)
>>> normalized_X_test = scaler.transform(X_test)
```



이진화는 텍스트 개수 데이터에서 수행되는 일반적인 작업입니다. 이진화를 사용하여 분석가는 예를 들어 수량화 된 발생 횟수를 갖는 대신 특성의 존재 여부를 고려할 수 있습니다.



```
>>> from sklearn.preprocessing import Binarizer
>>> binarizer = Binarizer(threshold=0.0).fit(X)
>>> binary_X = binarizer.transform(X)
```





LabelEncoder는 클래스 수준 인코딩을위한 데이터 전처리에 사용되는 또 다른 클래스입니다. 또한 숫자가 아닌 레이블을 숫자 레이블로 변환하는 데 사용할 수도 있습니다.



```
>>> from sklearn.preprocessing import LabelEncoder
>>> enc = LabelEncoder()
>>> y = enc.fit_transform(y)
```



Python의 Imputer 클래스는 누락 된 값을 대치 / 채우기위한 기본 전략을 제공합니다. 평균, 중앙값 또는 결 측값이있는 행 또는 열의 가장 빈번한 값을 사용하여이를 수행합니다. 이 클래스는 또한 다른 결 측값을 인코딩 할 수 있습니다.



```
>>> from sklearn.preprocessing import Imputer
>>> imp = Imputer(missing_values=0, strategy='mean', axis=0)
>>> imp.fit_transform(X_train)
```





Polynomial Feature는 지정된 차수보다 작거나 같은 차수를 가진 특성의 모든 다항식 조합으로 구성된 새로운 특성 행렬을 생성합니다. 예를 들어 입력 샘플이 2 차원이고 [a, b] 형식 인 경우 2 차 다항식 특징은 [1, a, b, a², ab, b²]입니다.



```
>>> from sklearn.preprocessing import PolynomialFeatures
>>> poly = PolynomialFeatures(5)
>>> poly.fit_transform(X)
```



# 지도 학습 추정기

**지도 학습** 은 모델이 **레이블이 지정된 데이터** 에 대해 학습 **된** 후 향후 결과를 예측할 수 있도록하는 기계 학습 유형입니다 .



```
# Linear Regression Algorithm
>>> from sklearn.linear_model import LinearRegression
>>> lr = LinearRegression(normalize=True)
# Naive Bayes Algorithm
>>> from sklearn.naive_bayes import GaussianNB
>>> gnb = GaussianNB()
# KNN Algorithm
>>> from sklearn import neighbors
>>> knn = neighbors.KNeighborsClassifier(n_neighbors=5)
# Support Vector Machines (SVM)
>>> from sklearn.svm import SVC
>>> svc = SVC(kernel='linear’)
```



**자율 학습은** 미래의 결과를 예측하는 모델을 가능하게 기계 학습의 유형입니다 **없이** 되고 **훈련** 온 **표시된 데이터** .



```
# Principal Component Analysis (PCA)
>>> from sklearn.decomposition import PCA
>>> pca = PCA(n_components=0.95)
# K Means Clustering Algorithm
>>> from sklearn.cluster import KMeans
>>> k_means = KMeans(n_clusters=3, random_state=0)
```





**피팅** 은 **기계 학습 모델이 학습** 된 데이터와 유사한 데이터로 일반화 되는 정도를 측정하는 것 **입니다.**





```
# For Supervised learning
>>> lr.fit(X, y) #Fits data into the model
>>> knn.fit(X_train, y_train)
>>> svc.fit(X_train, y_train)
# For Unsupervised Learning
>>> k_means.fit(X_train)#Fits data into the model
>>> pca_model = pca.fit_transform(X_train) #Fit to data, then transform it
```



**피팅** 은 **기계 학습 모델이 학습** 된 데이터와 유사한 데이터로 일반화 되는 정도를 측정하는 것 **입니다.**



```
# For Supervised learning
>>> y_pred=svc.predict(np.random((2,5))) #predict label
>>> y_pred=lr.predict(x_test) #predict label
>>> y_pred=knn.predict_proba(x_test)#estimate probablity of a label
# For Unsupervised Learning
>>> y_pred=k_means.predict(x_test) #predict labels in clustering algorithm
```



## 분류 메트릭

이 `sklearn.metrics`모듈은 분류 성능을 측정하기 위해 몇 가지 손실, 점수 및 유틸리티 함수를 구현합니다.



```
# Mean Absolute Error
>>> from sklearn.metrics import mean_absolute_error
>>> y_true = [3, -0.5, 2]
>>> mean_absolute_error(y_true, y_pred)
# Mean Squared Error
>>> from sklearn.metrics import mean_squared_error
>>> mean_squared_error(y_test, y_pred)
# R² Score
>>> from sklearn.metrics import r2_score
>>> r2_score(y_true, y_pred)
```





```
# Adjusted Rand Index
>>> from sklearn.metrics import adjusted_rand_score
>>> adjusted_rand_score(y_true, y_pred)
# Homogeneity
>>> from sklearn.metrics import homogeneity_score
>>> homogeneity_score(y_true, y_pred)
# V-measure
>>> from sklearn.metrics import v_measure_score
>>> metrics.v_measure_score(y_true, y_pred)
```



이 `sklearn.metrics`모듈은 회귀 성능을 측정하기 위해 여러 손실, 점수 및 유틸리티 함수를 구현합니다.





```
# Accuracy Score
>>> knn.score(X_test, y_test)
>>> from sklearn.metrics import accuracy_score
>>> accuracy_score(y_test, y_pred)
# Classification Report
>>> from sklearn.metrics import classification_report
>>> print(classification_report(y_test, y_pred))
# Confusion Matrix
>>> from sklearn.metrics import confusion_matrix
>>> print(confusion_matrix(y_test, y_pred))
```





```
>>> from sklearn.cross_validation import cross_val_score 
>>> print(cross_val_score(knn, X_train, y_train, cv=4)) 
>>> print(cross_val_score(lr, X, y, cv=2))
```



## 그리드 검색

GridSearchCV는 "fit"및 "score"메서드를 구현합니다. 또한 사용 된 추정기에서 구현되는 경우 "predict", "predict_proba", "decision_function", "transform"및 "inverse_transform"을 구현합니다.



```
>>> from sklearn.grid_search import GridSearchCV
>>> params = {"n_neighbors": np.arange(1,3), "metric": ["euclidean", "cityblock"]}
>>> grid = GridSearchCV(estimator=knn, param_grid=params)
>>> grid.fit(X_train, y_train)
>>> print(grid.best_score_)
>>> print(grid.best_estimator_.n_neighbors)
```



RandomizedSearchCV는 하이퍼 매개 변수에 대해 무작위 검색을 수행합니다. GridSearchCV와 달리 모든 매개 변수 값이 시도되는 것이 아니라 지정된 분포에서 고정 된 수의 매개 변수 설정이 샘플링됩니다. 시도되는 매개 변수 설정의 수는 n_iter에 의해 제공됩니다.



```
>>> from sklearn.grid_search import RandomizedSearchCV
>>> params = {"n_neighbours": range(1,5), "weights":["uniform", "distance"]}
>>>rserach = RandomizedSearchCV(estimator=knn,param_distribution=params, cv=4, n_iter=8, random_state=5)
>>> rsearch.fit(X_train, Y_train)
>>> print(rsearch.best_score)
```



인공 지능, DevOps, Ethical Hacking과 같은 시장에서 가장 유행하는 기술에 대한 더 많은 기사를 확인하려면 [Edureka의 공식 사이트를](https://www.edureka.co/blog/?utm_source=medium&utm_medium=content-link&utm_campaign=python-scikit-learn-cheat-sheet) 참조하십시오 [.](https://www.edureka.co/blog/?utm_source=medium&utm_medium=content-link&utm_campaign=python-scikit-learn-cheat-sheet)

Python 및 데이터 과학의 다양한 다른 측면을 설명하는이 시리즈의 다른 기사를 찾아보세요.

\1. [Python의 기계 학습 분류기](https://ichi.pro/ko/python-eulo-cheos-beonjjae-gigye-hagseub-bunlyugi-bildeu-211311275540681)

\2. [파이썬 인터뷰 질문](https://ichi.pro/ko/2019-nyeon-top-100-python-inteobyu-jilmun-mich-dabbyeon-178268384538783)

\3. [기계 학습 도구](https://ichi.pro/ko/deiteo-gwahag-mich-gigye-hagseub-eul-wihan-choegoui-python-laibeuleoli-31130581791351)

\4. [데이터 과학 및 기계 학습을위한 Python 라이브러리](https://ichi.pro/ko/deiteo-gwahag-mich-gigye-hagseub-eul-wihan-choegoui-python-laibeuleoli-31130581791351)

\5. [Python의 챗봇](https://ichi.pro/ko/paisseon-eseo-chaesbos-eul-mandeuneun-bangbeob-200728846053913)

\6. [파이썬 컬렉션](https://ichi.pro/ko/paisseon-keollegsyeon-e-daehae-al-aya-hal-modeun-geos-229506121521464)

\7. [파이썬 모듈](https://ichi.pro/ko/paisseon-modyul-eul-mandeulgo-sayonghaneun-bangbeob-188772744059235)

\8. [Python 개발자 기술](https://ichi.pro/ko/al-aya-hal-10-gaji-python-gaebalja-gisul-60565542575073)

\9. [OOP 인터뷰 질문 및 답변](https://ichi.pro/ko/50-gaji-jung-yohan-oops-jilmun-mich-dabbyeon-107888658009588)

\10. [Python 개발자를위한 이력서](https://ichi.pro/ko/python-gaebaljaleul-wihae-ilyeogseoleul-mandeuneun-bangbeob-al-abogi-245017039553417)

\11. [파이썬에서 탐색 적 데이터 분석](https://ichi.pro/ko/python-eseo-tamsaeg-jeog-deiteo-bunseog-ui-iyuwa-bangbeob-69160036115566)

\12. [Python의 Turtle 모듈을 사용한 스네이크 게임](https://ichi.pro/ko/python-ui-turtle-modyullo-yumyeonghan-baem-geim-bildeu-59477080707984)

\13. [Python 개발자 연봉](https://ichi.pro/ko/indowa-migug-ui-pyeong-gyun-python-gaebalja-yeonbong-eun-eolmaibnikka-204711016419374)

\14. [주성분 분석](https://ichi.pro/ko/yejeleul-sayonghayeo-python-eseo-pca-juseongbun-bunseog-hagseub-116374902586518)

\15. [Python 대 C ++](https://ichi.pro/ko/python-dae-c-chaijeom-i-mueos-inji-paag-215503182241516)

\16. [Scrapy 튜토리얼](https://ichi.pro/ko/scrapy-jaseubseo-scrapyleul-sayonghayeo-web-keulolleoleul-mandeuneun-bangbeob-94026790754555)

\17. [파이썬 SciPy](https://ichi.pro/ko/python-scipy-lan-mueos-imyeo-eotteohge-sayonghabnikka-62063139469899)

\18. [최소 제곱 회귀 방법](https://ichi.pro/ko/choeso-jegob-hoegwi-bangbeob-e-daehan-101-gaideu-71148763778727)

\19. [Jupyter 노트북 치트 시트](https://gpv6mo6tdipncgdvli5eyrokfa-adwhj77lcyoafdy-ichi-pro.translate.goog/edureka/jupyter-notebook-cheat-sheet-88f60d1aca7)

\20. [파이썬 기초](https://ichi.pro/ko/paisseon-gicho-paisseon-eul-geuleohge-ganglyeoghage-mandeuneun-geos-eun-mueos-ibnikka-267670280470612)

\21. [파이썬 패턴 프로그램](https://ichi.pro/ko/yejelo-paisseon-paeteon-peulogeulaem-eul-mandeuneun-bangbeob-129613110223919)

\22. [파이썬 생성기](https://ichi.pro/ko/python-ui-saengseong-gi-41296679130111)

\23. [파이썬 데코레이터](https://ichi.pro/ko/paisseon-dekoleiteo-paisseon-eseo-dekoleiteoleul-sayonghaneun-bangbeob-baeugi-210535558120804)

\24. [파이썬 스파이더 IDE](https://ichi.pro/ko/python-spyder-ideneun-mueos-imyeo-eotteohge-sayonghabnikka-46805658914374)

\25. [Python에서 Kivy를 사용하는 모바일 애플리케이션](https://ichi.pro/ko/python-eseo-kivyleul-sayonghayeo-gandanhan-mobail-aepeullikeisyeon-eul-mandeuneun-bangbeob-eun-mueos-ibnikka-169389265409013)

\26. [파이썬을 배우고 연습하기위한 10 가지 최고의 책](https://ichi.pro/ko/python-eul-baeugo-silseubhagiwihan-10-gaji-choegoui-chaeg-18775271390903)

\27. [Python을 사용한 로봇 프레임 워크](https://ichi.pro/ko/python-eul-sayonghan-lobos-peuleim-wokeue-daehae-al-aya-hal-modeun-geos-273397664857341)

\28. [PyGame을 사용하는 Python의 스네이크 게임](https://ichi.pro/ko/pygameeul-sayonghayeo-python-eseo-snake-gameeul-guhyeonhaneun-bangbeob-eun-mueos-ibnikka-80810187419306)

\29. [장고 인터뷰 질문 및 답변](https://ichi.pro/ko/al-aya-hal-top-50-django-inteobyu-jilmun-mich-dabbyeon-181279764953064)

\30. [상위 10 개의 Python 애플리케이션](https://ichi.pro/ko/silje-segyeeseo-al-aya-hal-10-gaji-python-aepeullikeisyeon-27176419610427)

\31. [파이썬의 해시 테이블과 해시 맵](https://ichi.pro/ko/python-ui-haesi-teibeul-mich-haesi-maeb-65798833635508)

\32. [파이썬 3.8](https://gpv6mo6tdipncgdvli5eyrokfa-adwhj77lcyoafdy-ichi-pro.translate.goog/edureka/whats-new-python-3-8-7d52cda747b)

\33. [서포트 벡터 머신](https://ichi.pro/ko/python-eseo-jiwon-begteo-meosin-91937169588842)

\34. [파이썬 튜토리얼](https://ichi.pro/ko/python-101-chobojaleul-wihan-python-gaideu-209024196892485)

