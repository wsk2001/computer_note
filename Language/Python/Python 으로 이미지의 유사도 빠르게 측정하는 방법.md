# Python 으로 이미지의 유사도 빠르게 측정하는 방법

출처: https://qiita.com/kagami_t/items/a1cae07c9565ce501ced?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=1932107b24-Qiita_newsletter_570_06_14_2023&utm_medium=email&utm_term=0_e44feaa081-1932107b24-34467781

# 소개

데이터 세트를 추가하고 이름 바꾸기를 반복하면 동일한 이미지가 여러 장 섞여 있음을 알게 되었습니다.

효율적으로 제거하는 방법으로 `imgsim`라이브러리를 사용하여 이미지의 유사도를 측정했습니다.

과 학습의 원인이 되는 동일 화상의 삭제, 유사한 화상의 분류 등에 도움이 됩니다.

**※자세한 것은 아래와 같이 GitHub의 쪽에서 확인해 주세요.**



**본 기사가 조금이라도 독자님의 배움에 연결되면 다행입니다!**
**「좋아요」를 해 주시면 향후의 격려가 되므로, 부디 부탁드립니다!**

## 환경

우분투22.04
파이썬3.11.1

## imgsim이란?

다른 이미지의 특징 벡터 사이의 거리와 유사도의 차이를 계산합니다.
`AugNet`라는 딥 러닝 학습 패러다임을 이용합니다.
차이가 0이면 동일 화상, 값이 커질수록 특징량이 다른 화상입니다.

## AugNet이란?

교사 없음 학습을 사용하여 이미지의 표현 학습을 수행하는 방법입니다.
data augmentation을 사용하여 확장된 이미지 간의 차이를 측정합니다.

## 구현

1. 라이브러리를 설치합니다.

   ```
   pip install imgsim
   ```

   `conda`없었기 때문에 `pip`설치하십시오.
   **※안이하게 섞는 것은 위험합니다. [1](https://qiita.com/kagami_t/items/a1cae07c9565ce501ced?utm_source=Qiitaニュース&utm_campaign=1932107b24-Qiita_newsletter_570_06_14_2023&utm_medium=email&utm_term=0_e44feaa081-1932107b24-34467781#fn-1)**

2. 이미지를 준비합니다.

   [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567)[![penguin_lr](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2F8001fdf8-84df-a588-5ab2-ad7357de4297.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=62ade039e374caaffd2c9474e96b3e21)](https://camo.qiitausercontent.com/fc12556b42f6d1ce777c0b7d87062342b64f48b1/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f38303031666466382d383464662d613538382d356162322d6164373335376465343239372e6a706567)
   [![another_penguin](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fca782e55-a3c6-39ab-8ae1-a19afcb8354e.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=4d05bc25c65c72a6c1b8d87bf8fc97ee)](https://camo.qiitausercontent.com/1ed0463d543202f8769529387c1884a0d9d06e9a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f63613738326535352d613363362d333961622d386165312d6131396166636238333534652e6a706567)[![벌새](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2F6937560d-1637-a4c9-e468-fdebd033f913.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=d6d95cb28b033e2715b9c816ff6116fc)](https://camo.qiitausercontent.com/a4204d003f2ddda7734a81eaf22622e184817e1c/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f36393337353630642d313633372d613463392d653436382d6664656264303333663931332e706e67)

3. 유사도를 측정합니다.
   [GitHub](https://github.com/chenmingxiang110/AugNet"GitHub") 의 샘플 코드를 기반으로 시도합니다.

measure_distance.py

```
import imgsim
import cv2


vtr = imgsim.Vectorizer()

penguin_img = cv2.imread("./input/king_penguin_00001.jpg")
penguin_lr_img = cv2.imread("./input/king_penguin_00001_flip_lr.jpg")
another_penguin_img = cv2.imread("./input/king_penguin_00019.jpg")
hummingbird_img = cv2.imread("./input/hummingbird_00010.png")


penguin_vec = vtr.vectorize(penguin_img)
penguin_lr_vec = vtr.vectorize(penguin_lr_img)
another_penguin_vec = vtr.vectorize(another_penguin_img)
hummingbird_vec = vtr.vectorize(hummingbird_img)

dist0 = imgsim.distance(penguin_vec, penguin_vec)
print("Same Distance =", round(dist0, 2))
dist1 = imgsim.distance(penguin_vec, penguin_lr_vec)
print("Reversal Distance =", round(dist1, 2))
dist2 = imgsim.distance(penguin_vec, another_penguin_vec)
print("Another Distance =", round(dist2, 2))
dist3 = imgsim.distance(penguin_vec, hummingbird_vec)
print("Other Distance =", round(dist3, 2))
```

다음과 같은 실행 결과가 되었습니다.

```
Same Distance = 0.0
Reversal Distance = 11.53
Another Distance = 26.67
Other Distance = 32.2
```

결과를 표로 하면 다음과 같이 됩니다.

| 베이스                                                       | 표적                                                         | 거리      |
| :----------------------------------------------------------- | :----------------------------------------------------------- | :-------- |
| [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567) | [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567) | **0.0**   |
| [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567) | [![penguin_lr](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2F8001fdf8-84df-a588-5ab2-ad7357de4297.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=62ade039e374caaffd2c9474e96b3e21)](https://camo.qiitausercontent.com/fc12556b42f6d1ce777c0b7d87062342b64f48b1/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f38303031666466382d383464662d613538382d356162322d6164373335376465343239372e6a706567) | **11.53** |
| [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567) | [![another_penguin](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fca782e55-a3c6-39ab-8ae1-a19afcb8354e.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=4d05bc25c65c72a6c1b8d87bf8fc97ee)](https://camo.qiitausercontent.com/1ed0463d543202f8769529387c1884a0d9d06e9a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f63613738326535352d613363362d333961622d386165312d6131396166636238333534652e6a706567) | **26.67** |
| [![펭귄](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2Fe39dadb5-fc18-b615-827d-453baa59c606.jpeg?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=e099cc42c6e747b5d700d43db150601d)](https://camo.qiitausercontent.com/f49deb6f3ed540cbfcf958b99c74c08a4969a26a/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f65333964616462352d666331382d623631352d383237642d3435336261613539633630362e6a706567) | [![벌새](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F3292052%2F6937560d-1637-a4c9-e468-fdebd033f913.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=d6d95cb28b033e2715b9c816ff6116fc)](https://camo.qiitausercontent.com/a4204d003f2ddda7734a81eaf22622e184817e1c/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f333239323035322f36393337353630642d313633372d613463392d653436382d6664656264303333663931332e706e67) | **32.2**  |

결과를 요약합니다.

- 동일한 이미지의 차이는 0입니다.
- 좌우 반전은 차이가 작다.
- 유사한 이미지보다 다른 이미지는 차이가 크다.

당연한 결과라고 생각되지만, 이 4장에 대해서는 신뢰할 수 있는 결과였습니다.

## 마지막으로

읽어 주셔서 감사합니다.

실은 샘플 코드에 조금 어려웠습니다만, 데이터 세트의 정리에는 무엇인가 도움이 될 것 같았습니다.

유사도는 다른 수법에서도 측정할 수 있기 때문에 비교해 보더라도 재미있을지도 모릅니다.

이 기사가 도움이되면 다행입니다!