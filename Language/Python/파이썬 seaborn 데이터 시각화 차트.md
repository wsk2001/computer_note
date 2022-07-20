# 파이썬 seaborn 데이터 시각화 차트

출처: https://growthj.link/python-seaborn-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%8B%9C%EA%B0%81%ED%99%94-%EC%B4%9D%EC%A0%95%EB%A6%AC/

파이썬 seaborn 데이터 시각화 차트/그래프 총정리

[seaborn](https://seaborn.pydata.org/)은 matplotlib 처럼 그래프를 그리는 기능이다([matplotlib으로 그래프 그리는 꿀팁이 궁금하다면?](https://growthj.link/python-matplotlib으로-그래프-그릴-때-꿀팁/)). matplotlip으로도 대부분의 시각화는 가능하지만 아래와 같은 이유들로 seaborn을 더 선호하는 추세이다.

- seaborn에서만 제공되는 통계 기반 plot
- 특별하게 꾸미지 않아도 깔끔하게 구현되는 기본 color
- 더 아름답게 그래프 구현이 가능한 palette 기능
- pandas 데이터프레임과 높은 호환성
  : hue 옵션으로 bar 구분이 가능하며, xtick, ytick, xlabel, ylabel, legend 등이 추가적인 코딩 작업 없이 자동으로 세팅된다.

그럼 이제부터 seaborn 데이터 시각화를 위한 다양한 그래프와 차트들을 총정리해보자!

먼저 그래프 구현에 앞서 필요한 라이브러리를 import 해준다.

``` py
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
```

한글 폰트도 설치 해주자.

``` py
# 한글 폰트 적용
plt.rc('font', family='NanumBarunGothic') 
# 캔버스 사이즈 적용
plt.rcParams["figure.figsize"] = (12, 9)
```

seaborn 실습을 위해 샘플 데이터로 Titanic data-set을 로드한다.([seaborn.load_dataset](https://seaborn.pydata.org/generated/seaborn.load_dataset.html) : seaborn 연습이 가능한 샘플 데이터를 제공해준다)





