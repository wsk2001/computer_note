# Python 간편하게 얼굴 인증 구현

출처: https://qiita.com/f-paico/items/e27e4eefc8c009ecdeab?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=20a844be7d-Qiita_newsletter_603_01_31&utm_medium=email&utm_term=0_e44feaa081-20a844be7d-34467781



### 【Python】 간편하게 얼굴 인증을 구현한다

## 소개

Python 라이브러리를 사용하면 쉽게 얼굴 인증을 구현할 수 있습니다. 아직 얼굴 인증을 구현한 적이 없는 얼굴 인증을 사용한 앱을 만들고 싶은 분들에게 꼭 읽어 주셨으면 합니다.

## 행동 환경

- M1 맥
- 파이썬 3.9.6

## 라이브러리 설치

이번에는 무료로 사용할 수있는 파이썬 라이브러리로 [face-recognition](https://pypi.org/project/face-recognition/) 을 사용합니다. face-recognition은 dlib이라는 기계 학습 및 이미지 처리 관련 오픈 소스 라이브러리에 의해 만들어진 Python 라이브러리입니다. Labeled Faces in the Wild라는 데이터 세트를 사용하여 99.38%의 정확도가 나오는 것 같습니다. 다음 명령을 사용하여 라이브러리를 설치합니다. 또한 얼굴 인증에 필요한 얼굴 검출에 대해서도 설명하므로 matplotlib도 함께 설치해 둡시다.

```
pip3 install face-recognition 
pip3 install matplotlib
```

## 구현 준비

먼저 Python 프로그램을 작성하는 test.py를 작성합니다. 그리고 학습 데이터로 사용하는 이미지 train.jpg와 테스트 데이터로 사용할 test.jpg를 준비하고 test.py와 같은 계층에 놓습니다. 나는 오바마의 얼굴 이미지를 2 종류 준비하고 train.jpg, test.jpg로하고 있습니다.

```
Sample
├── test.py
├── train.png
└── test.png
```

### 얼굴 검출 구현

이미지에서 얼굴 인증을 하려면 먼저 이미지에서 얼굴을 감지하고 그 위에 얼굴 인증을 해야 합니다. 그 때문에, 우선은 얼굴 검출로부터 실장해 갑니다. test.py를 다음과 같이 설명합니다.

```python
# 라이브러리 로드
import face_recognition
import matplotlib.pyplot as plt

# 학습하고 싶은 (등록하고 싶은) 얼굴 이미지의 파일 이름을 목록에 저장
train_img_names = ["train.jpg", ]
# 학습 된 이미지에 대한 인증 또는 테스트에 사용되는 얼굴 이미지의 파일 이름을 목록에 저장
test_img_name = "test.jpg"

# 학습 데이터의 얼굴 이미지 로드
train_imgs = []
for name in train_img_names:
    train_img = face_recognition.load_image_file(name)
    train_imgs.append(train_img)

# 테스트 데이터(인증하는 사람의 얼굴 이미지) 로드
test_img = face_recognition.load_image_file(test_img_name)

# 학습 데이터의 얼굴 이미지에서 얼굴 영역만 감지
train_img_locations = []
for img in train_imgs:
    # 모델은 hog와 cnn을 지정할 수 있고, cnn은 무겁지만 정밀도 좋고, hog는 경량이지만 정밀도는 보통
    train_img_location = face_recognition.face_locations(img, model="hog")
    # 얼굴 감지에 실패하면 train_img_location의 길이는 1입니다.
    # 얼굴 검출에 성공하면 얼굴을 검출하고 사각형으로 둘러싸인 네 모퉁이의 좌표를 취득할 수 있다
    assert len(train_img_location) == 1, "이미지에서 얼굴 감지에 실패했거나 두 명 이상의 얼굴이 감지되었습니다."
    train_img_locations.append(train_img_location)

# 테스트 데이터의 얼굴 이미지에서 얼굴 영역만 감지
test_img_location = face_recognition.face_locations(test_img, model="hog")
assert len(test_img_location) == 1, "이미지에서 얼굴 감지에 실패했거나 두 명 이상의 얼굴이 감지되었습니다."

# 얼굴 검출 결과를 시각화하는 함수 정의
def draw_img_locations(imgs, locations):
    fig, ax = plt.subplots()
    ax.imshow(imgs)
    ax.set_axis_off()
    for i, (top, right, bottom, left) in enumerate(locations):
        # 사각형을 그리기
        w, h = right - left, bottom - top
        ax.add_patch(plt.Rectangle((left, top), w, h, ec="r", lw=2, fill=None))
    plt.show()

# 학습 데이터로 얼굴 검출 결과를 시각화
for img, location in zip(train_imgs, train_img_locations):
    draw_img_locations(img, location)

# 테스트 데이터로 얼굴 검출 결과를 시각화
draw_img_locations(test_img, test_img_location)
```

위의 프로그램을 실행하면 다음과 같이 얼굴 감지가 이루어졌는지 확인할 수 있습니다. 얼굴 검출을 할 수 있었는지 동작 확인해 둡시다.

학습 데이터

[![그림_1.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F674933%2F61166bde-32ec-3c84-2b3b-76692c202aee.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=f7827923409faab3f4c1238edf8575cb)](https://camo.qiitausercontent.com/8bf61ecf1322fcbe7fb96117423ff53603d074ee/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f3637343933332f36313136366264652d333265632d336338342d326233622d3736363932633230326165652e706e67)

테스트 데이터

[![Figure_2.png](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F674933%2F53b3ad71-13c8-6129-63c9-c1aaa6df5652.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&s=3c542185cb10611a0c4998f2c1689750)](https://camo.qiitausercontent.com/292cb1dd9ce625f403d95b8b2d45dbc6df069765/68747470733a2f2f71696974612d696d6167652d73746f72652e73332e61702d6e6f727468656173742d312e616d617a6f6e6177732e636f6d2f302f3637343933332f35336233616437312d313363382d363132392d363363392d6331616161366466353635322e706e67)

### 얼굴 인증 구현

방금의 test.py의 계속으로부터, 이하와 같이 얼굴 인증의 프로그램을 기술합니다.

```python
# 학습 데이터의 특징량 추출
train_img_encodings = []
for img, location in zip(train_imgs, train_img_locations):
    (encoding, ) = face_recognition.face_encodings(img, location)
    train_img_encodings.append(encoding)

# 테스트 데이터의 특징량 추출
(test_img_encoding, ) = face_recognition.face_encodings(test_img, test_img_location)

# 학습 데이터와 테스트 데이터의 특징량을 비교하여 유클리드 거리를 얻습니다.
# 거리를 보면 얼굴이 얼마나 비슷한지 알 수 있습니다.
dists = face_recognition.face_distance(train_img_encodings, test_img_encoding)

# 학습 데이터와 테스트 데이터 사이의 거리가 0.40 이하일 때 얼굴이 일치한다고 판단
answer = False
for dist in dists:
    if dist < 0.40:
        answer = True

# 얼굴 인증 결과 출력
print(answer)
```

이상의 프로그램을 실행하는 것으로, 얼굴 인증을 실현할 수 있습니다. 실제로 동작 확인해 둡시다.

## 결론

파이썬 라이브러리로 face-recognition을 사용하여 손쉽게 얼굴 인증을 구현해 보았습니다. 이 프로그램을 확장하여 다양한 앱에 활용할 수 있다면 기쁩니다. 다음 번에는 스마트 폰 앱에서 얼굴 이미지를 업로드하고 업로드 한 얼굴 이미지를 사용하여 얼굴 인증을 해 보겠습니다.

## 참고자료

- https://github.com/ageitgey/face_recognition/blob/master/README_Korean.md

