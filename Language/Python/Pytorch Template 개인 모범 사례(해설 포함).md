# Pytorch Template 개인 모범 사례(해설 포함)

출처: https://qiita.com/takubb/items/7d45ae701390912c7629?utm_source=Qiita%E3%83%8B%E3%83%A5%E3%83%BC%E3%82%B9&utm_campaign=43e5072c2b-Qiita_newsletter_485_10_27_2021&utm_medium=email&utm_term=0_e44feaa081-43e5072c2b-34467781



# PyTorch

위키백과, 우리 모두의 백과사전.

**PyTorch**는 Python을 위한 오픈소스 머신 러닝 라이브러리이다. [Torch](https://ko.wikipedia.org/wiki/Torch)를 기반으로 하며[[2\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-2)[[3\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-3)[[4\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-4), 자연어 처리와 같은 애플리케이션을 위해 사용된다.[[5\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-5) GPU사용이 가능하기 때문에 속도가 상당히 빠르다. 아직까지는 Tensorflow의 사용자가 많지만, 비직관적인 구조와 난이도  때문에, Pytorch의 사용자가 늘어나고 있는 추세이다. 이는 Facebook의 인공지능 연구팀이 개발했으며, Uber의  “Pyro”(확률론적 프로그래밍 언어)소프트웨어가 Pytorch를 기반으로 한다

Pytorch는 두 개의 높은 수준의 파이선 패키지 형태로 제공한다.[[6\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-6)

1. 강력한 GPU가속화를 통한 Tensor계산 ex) NumPy
2. 테이프 기반 자동 삭제 시스템을 기반으로 구축된 심층 신경망

Facebook은 PyTorch와 Convolutional Architecture for Fast Feature  Embedding (Caffe2)을 모두 운영하고 있지만 비호환성으로 인해 PyTorch 정의 모델을 Caffe2로 변환하거나 그 반대로 변환하는 것이 어렵다. 개신경망 교환([ONNX, Open Neural Network Exchange](https://github.com/onnx/onnx)) 프로젝트는 Facebook과 Microsoft가 프레임워크 간 모델 전환을 위해 2017년 9월 만든 프로젝트다. Caffe2는 2018년 3월 말에 PyTorch으로 합병되었다.

## PyTorch Tensors

프로그래밍의 영역에서 Tensors는 단순히 다차원 배열로써 간주될 수 있다. Pytorch에서의 Tensors는 [NumPy](https://ko.wikipedia.org/wiki/NumPy)의 배열과 비슷한데, 추가로 Tensors도 CUDA를 지원하는 GPU에 사용할 수 있다. 또한, Pytorch는 다양한 타입의 Tensors를 지원한다.

## 모듈

### 자동 미분 모듈

PyTorch는 자동 미분이라는 기법을 사용한다. 레코더는 수행한 작업을 기록한 다음 거꾸로 재생하여 경사를 계산한다. 이 기술은 전방 패스에서 매개 변수의 미분을 계산하여 한 시대에서 시간을 절약하기 위해 신경 네트워크를 구축할 때 특히 강력하다.

### 최적화 모듈

torch.optim은 신경 네트워크를 구축하는 데 사용되는 다양한 최적화 알고리즘을 구현하는 모듈이다. 일반적으로 사용되는 대부분의 방법은 이미 지원되므로 처음부터 새로 만들 필요가 없다.

### nn 모듈

autograd 모듈을 사용하면 계산 그래프를 쉽게 정의하고 구배를 얻을 수 있지만, 원시 자동 그립은 복잡한 신경 네트워크를 정의하기에는  autograd 모듈을 직접 쓰기에는 너무 저수준의 작업이 될 것이다. 여기서 nn모듈이 도움이 된다.

## Tensorflow와의 비교[[7\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-7)

| 구분             | Tensorflow              | PyTorch                   |
| ---------------- | ----------------------- | ------------------------- |
| 패러다임         | Define and Run          | Define by Run             |
| 그래프 형태      | Static graph(정적)      | Dynamic graph(동적)       |
| 현재 사용자      | 많음                    | 적음                      |
| 자체 운영 포럼   | 없음                    | 있음                      |
| 한국 사용자 모임 | Tensorflow Korea(TF-KR) | Pytorch Korea(Pytorch-KR) |

### 딥러닝 구현 라이브러리, 파이토치

파이토치는 파이썬 기반의 오픈 소스 머신러닝 라이브러리로, 페이스북 인공지능 연구집단에 의해 개발되었다. 간결하고 구현이 빨리되며,  텐서플로우보다 사용자가 익히기 훨씬 쉽다는 특징이 있다. 또한 코드를 직접 다룬다 사람들에게 설명해 주기에도 효과적이다.

텐서플로우와 Pytorch의 가장 큰 차이점은 딥러닝을 구현하는 패러다임이 다르다는 것이다. 텐서플로우는 Define-and-Run 프레임워크인 반면에, Pytorch는 Define-by-Run이다.

Define and Run는코드를 직접 돌리는 환경인 세션을 만들고, placeholder를 선언하고 이것으로 계산  그래프를 만들고(Define), 코드를 실행하는 시점에 데이터를 넣어 실행하는(Run) 방식. 이는 계산 그래프를 명확히  보여주면서 실행시점에 데이터만 바꿔줘도 되는 유연함을 장점으로 갖지만, 그 자체로 비직관적이다. 그래서 딥러닝 프레임워크 중  난이도가 가장 높은 편이다.

Pytorch는 언어 자체에 대한 어려움은 없다. 일반적인 파이썬 코딩과 비슷하기 때문이다. 선언과 동시에 데이터를  집어넣고 세션도 필요없이 돌리면 끝이다. 덕분에 코드가 간결하고 난이도가 낮은 편이다.사용자가 아직 적어 구글링으로 공부하기 힘든 환경에 있지만 이는 시간이 해결해 줄 문제이다.

이 패러다임의 차이로 텐서플로우의 경우 먼저 모델을 만들고 값을 다 따로 넣어주어야 해서 직관적이지 않지만, Pytorch의 경우 간단하고 직관적이다.

두 프레임워크 모두 계산 그래프를 정의하고 자동으로 그래디언트를 계산하는 기능이 있다. 하지만 Tensorflow의  계산 그래프는 정적이고 Pytorch는 동적이다. 즉 tensorflow에서는 계산 그래프를 한 번 정의하고 나면 그래프에  들어가는 입력 데이터만 다르게 할 수 있을 뿐 같은 그래프만을 실행할 수 있다. 하지만 PyTorch는 각 순전파마다 새로운 계산 그래프를 정의하여 이용한다.

## PyTorch의 장점

1. 설치가 간편하다.
2. 이해와 디버깅이 쉬운 직관적이고 간결한 코드로 구성되었다.
3. Define by Run 방식을 기반으로 한 실시간 결과값을 시각화 한다.
4. 파이썬 라이브러리(Numpy, Scipy, Cython)와 높은 호환성을 가진다.
5. Winograd Convolution Algorithm 기본 적용을 통한 빠른 모델 훈련이 가능하다.
6. 모델 그래프를 만들 때 고정상태가 아니기 때문에 언제든지 데이터에 따라 조절이 가능하다(유연성).
7. Numpy스러운 Tensor연산이 GPU로도 가능하다.
8. 자동 미분 시스템을 이용해 쉽게 DDN(DataDirect Networks을 짤 수 있다.
9. 학습 및 추론 속도가 빠르고 다루기 쉽다.

## PyTorch 패키지[[8\]](https://ko.wikipedia.org/wiki/PyTorch#cite_note-8)

| 패키지                  | 기술                                                         |
| ----------------------- | ------------------------------------------------------------ |
| torch                   | 강력한 GPU 지원 기능을 갖춘 Numpy와 같은 라이브러리          |
| torch.autograd          | Torch에서 모든 차별화된 Tensor 작업을 지원하는 테이프 기반 자동 미분화 라이브러리 |
| torch.optim             | SGD, RMSProp, LBFGS, Adam 등과 같은 표준 최적화 방법으로 torch.nn과 함께 사용되는 최적화 패키지 |
| torch.nn                | 최고의 유연성을 위해 설계된 자동 그래프와 깊이 통합된 신경 네트워크 라이브러리 |
| torch.legacy(.nn/optim) | 이전 버전과의 호환성을 위해 Torch에서 이식된 레거시 코드     |
| torch.utils             | 편의를 위해 DataLoader, Trainer 및 기타 유틸리티 기능        |
| torch.multiprocessing   | 파이썬 멀티 프로세싱을 지원하지만, 프로세스 전반에 걸쳐 Torch Tensors의 마법같은 메모리 공유 기능을 제공. 데이터 로딩 및 호그 워트 훈련에 유용 |



## 소개

- Pytorch에서 코드를 작성하기 시작할 때 난수 고정, 데이터 로더, 모델 훈련 및 학습 결과 얻기 등 매번 다양한 사이트를 참조하는 것은 번거롭다고 생각하고 현시점의 개인적 모범 사례 템플릿을 작성하고 보았습니다.
- 향후의 버전 업이나 편리한 라이브러리의 등장으로 바뀔지도 모릅니다, 현재는 이것으로 침착하고 있습니다.
- 개인적인 비망록도 겸하고, 전반에 간단한 해설 첨부의 코드와 마지막에 전체 코드를 실어 있습니다.
- 보다 편리하게 쓰는 방법이나 라이브러리 등 있으면, 코멘트를 받을 수 있으면 기쁘겠습니다.



## 템플릿(해설 포함)

### 1. 라이브러리 가져오기 및 초기 설정

- torch와 자주 사용하는 라이브러리 (numpy, matplotlib) 가져 오기
- 모델 훈련 시 (for 문장)의 진행 상황을 표시하는 tqdm 라이브러리(jupyter notebook 및 명령줄 버전)
  - 진행 표시는 대기 시간 예상과 오류를 알 수 있으므로 기본적으로 설정합니다.
- torch.device에서는 GPU를 이용할 수 있으면 지정(GPU 이용이 불가능한 경우는 CPU를 지정)
- fix_seed와 worker_init_fn은 난수 고정을 위한 함수
  - pytorch에서는 다양한 난수가 배후에 존재하기 때문에 모델의 재현성을 담보할 때는 모두 고정한다
  - 참고: [기계 학습에서 랜덤 시드 연구](https://qiita.com/si1242/items/d2f9195c08826d87d6ad)

``` python
import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F

import random
import numpy as np
import matplotlib.pyplot as plt

# from tqdm import tqdm  # 명령줄에서 실행할 때
from tqdm.notebook import tqdm  # Jupyter로 실행할 때

# 리소스 지정(CPU/GPU)
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")

# 난수 시드 고정(재현성의 담보)
def fix_seed(seed):
    # random
    random.seed(seed)
    # numpy
    np.random.seed(seed)
    # pytorch
    torch.manual_seed(seed)
    torch.cuda.manual_seed_all(seed)
    torch.backends.cudnn.deterministic = True
    torch.backends.cudnn.benchmark = False

seed = 42
fix_seed(seed)

# 데이터 로더 서브 프로세스의 난수 seed 고정
def worker_init_fn(worker_id):
    np.random.seed(np.random.get_state()[1][0] + worker_id)
```



### 2. 데이터 준비(데이터 로더)

- Dataset 클래스에서는 데이터의 전처리 등을 정의해, 학습 데이터를 데이터 로더로서 준비
  - Dataset 클래스는 'def len()'과 'def getitem()'이 필수적인 점에 주의한다.
  - 그 외는 자유롭게 메소드를 정의할 수 있으므로, 전처리용의 함수등을 정의할 수 있다.
- 데이터 로더는 가속화를 위해 num_workers 및 pin_memory 설정
  - 참고: [PyTorch에서의 학습·추론을 고속화하는 요령집](https://qiita.com/sugulu_Ogawa_ISID/items/62f5f7adee083d96a587)

``` python
# 데이터세트 만들기
class Mydataset(torch.utils.data.Dataset):
    def __init__(self, X, y):
        self.X = X
        self.y = y

    def __len__(self):
        return len(self.X)

    def __getitem__(self, index):
        feature = self.X[index]
        label = self.y[index]
        # 전처리 등 쓰기-----

        # --------------------
        return feature, label

train_dataset = Mydataset(train_X, train_y)
test_dataset = Mydataset(test_X, test_y)


# 데이터 로더 만들기
train_loader = torch.utils.data.DataLoader(train_dataset,
                                           batch_size=16,  # 배치 크기
                                           shuffle=True,  # 데이터 셔플
                                           num_workers=2,  # 고속화
                                           pin_memory=True,  # 고속화
                                           worker_init_fn=worker_init_fn
                                           )
test_loader = torch.utils.data.DataLoader(test_dataset,
                                          batch_size=16,
                                          shuffle=False,
                                          num_workers=2,
                                          pin_memory=True,
                                          worker_init_fn=worker_init_fn
                                          )
```



### 3. 모델 설계 및 학습 준비

- 모델 클래스 정의
  - Convolution-Batch Normalization-Relu 와 같은 빈발하는 레이어의 조합은 Sequential로 정리하면 편리하다.
  - Forward에서 순전파를 정의할 때는 (네트워크가 깊지 않는 한) 1처리마다 써 두면 검토하기 쉽다.
- 손실 함수 · 최적화 알고리즘은 요구 사항에 따라 선택
  - 데이터를 일괄 적으로 검색하여 오차 계산 및 역 전파 처리를 정의합니다.
  - 배치경로의 로스를 취득해 두고, 최종적으로 배치 평균한 로스를 출력한다(로스의 하강 상태를 확인용)
  - 테스트 파트측의 루프 처리도 훈련 파트측과 거의 같지만, model.eval() 로 평가 모드로 해 두는 것을 잊지 않는다 (pytorch의 메소드에 따라서는 훈련 모드와 평가 모드로 거동이 다른 것이 존재한다)

``` python
# 모델 정의
class Mymodel(nn.Module):
    def __init__(self):
        super().__init__()
        self.conv1 = nn.Sequential(nn.Conv2d(3, 16, 3, 2, 1),
                                   nn.BatchNorm2d(16),
                                   nn.ReLU())
        self.conv2 = nn.Sequential(nn.Conv2d(16, 64, 3, 2, 1),
                                   nn.BatchNorm2d(64),
                                   nn.ReLU())

        self.fc1 = nn.Linear(2 * 2 * 64, 100)
        self.dropout = nn.Dropout(0.5)
        self.fc2 = torch.nn.Linear(100, 2)

    def forward(self, x):
        x = self.conv1(x)
        x = self.conv2(x)
        x = x.view(x.size(0), -1)
        x = self.fc1(x)
        x = self.dropout(x)
        x = self.fc2(x)
        return x

# 모델, 손실 함수, 최적화 알고리즘 설정
model = Mymodel().to(device)
criterion = nn.CrossEntropyLoss()
optimizer = optim.SGD(model.parameters(), lr=0.01)


# 모델 훈련 함수
def train_model(model, train_loader, test_loader):
    # Train loop ----------------------------
    model.train()  # 학습 모드 켜기
    train_batch_loss = []
    for data, label in train_loader:
        # GPU로 전송
        data, label = data.to(device), label.to(device)
        # 1. 그라디언트 리셋
        optimizer.zero_grad()
        # 2. 추론
        output = model(data)
        # 3. 오류 계산
        loss = criterion(output, label)
        # 4. 오류 반전
        loss.backward()
        # 5. 파라미터 업데이트
        optimizer.step()
        # train_loss 얻기
        train_batch_loss.append(loss.item())

    # Test(val) loop ----------------------------
    model.eval()  # 학습 모드 끄기
    test_batch_loss = []
    with torch.no_grad():  # 기울기를 계산하지 않음
        for data, label in test_loader:
            data, label = data.to(device), label.to(device)
            output = model(data)
            loss = criterion(output, label).item()
            test_batch_loss.append(loss.item())

    return model, np.mean(train_batch_loss), np.mean(test_batch_loss)

```



### 4. 모델 훈련 수행

- 방금 정의한 훈련 함수를 임의의 횟수로 반복하여 모델 학습
- 학습 상황(로스의 하강 상태)을 그래프로 가시화

``` python
# 훈련 수행
epoch = 100
train_loss = []
test_loss = []

for epoch in tqdm(range(epoch)):
    model, train_l, test_l = train_model(model)
    train_loss.append(train_l)
    test_loss.append(test_loss)
    # 10 에포크마다 로스 표시
    if epoch % 10 == 0:
        print("Train loss: {a:.3f}, Test loss: {b:.3f}".format(a=train_loss[-1], b = test_loss[-1]))

# 학습상황(로스) 확인
plt.plot(train_loss, label='train_loss')
plt.plot(test_loss, label='test_loss')
plt.legend()

```



### 5. 모델 평가

- 학습 된 모델을 사용하여 데이터 로더에서 예측 및 정답 값을 얻습니다.
- preds, labels에 예측 결과가 numpy 형식으로 들어 있기 때문에 평가 실시.
  - sklearn.metrics 모듈 등을 이용하면 지표 평가는 간단하게 실시할 수 있다.
  - 참고: [회귀 모델 평가](https://pythondatascience.plavox.info/scikit-learn/%E5%9B%9E%E5%B8%B0%E3%83%A2%E3%83%87%E3%83%AB%E3%81%AE%E8%A9%95%E4%BE%A1)
  - 참고: [분류 모델 평가](https://pythondatascience.plavox.info/)

``` python
# 학습된 모델에서 예측 결과와 정답 값을 얻습니다.
def retrieve_result(model, dataloader):
    model.eval()
    preds = []
    labels = []
    # Retreive prediction and labels
    with torch.no_grad():
        for data, label in dataloader:
            data, label = data.to(device), label.to(device)
            output = model(data)
            # Collect data
            preds.append(output)
            labels.append(label)
    # Flatten
    preds = torch.cat(preds, axis=0)
    labels = torch.cat(labels, axis=0)
    # Returns as numpy (CPU 환경의 경우 불필요)
    preds = preds.cpu().detach().numpy()
    labels = labels.cpu().detach().numpy()
    return preds, labels


# 예측 결과와 정답 값 획득
preds, labels = retrieve_result(model, test_loader)
```

### 6. 기타

- 학습한 모델 저장/로드
- 모델 요약(매개변수 수 등)

``` python
# 학습된 모델 저장 및 로드
path_saved_model = "./saved_model"
# 모델 저장
torch.save(model.state_dict(), path_saved_model)
# 모델 로드
model = Mymodel()
model.load_state_dict(torch.load(path_saved_model))


# Model summary
from torchsummary import summary
model = model().to(device)
summary(model, input_size=(1, 50, 50))
```



## 템플릿(모든 코드)

``` python
import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F

import random
import numpy as np
import matplotlib.pyplot as plt

# from tqdm import tqdm  # 명령줄에서 실행할 때
from tqdm.notebook import tqdm  # Jupyter로 실행할 때

# 리소스 선택(CPU/GPU)
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")

# 난수 시드 고정(재현성의 담보)
def fix_seed(seed):
    # random
    random.seed(seed)
    # numpy
    np.random.seed(seed)
    # pytorch
    torch.manual_seed(seed)
    torch.cuda.manual_seed_all(seed)
    torch.backends.cudnn.deterministic = True
    torch.backends.cudnn.benchmark = False

seed = 42
fix_seed(seed)

# 데이터 로더 서브 프로세스의 난수 seed 고정
def worker_init_fn(worker_id):
    np.random.seed(np.random.get_state()[1][0] + worker_id)


# Data preprocessing ----------------------------------------------------------

# 데이터세트 만들기
class Mydataset(torch.utils.data.Dataset):
    def __init__(self, X, y):
        self.X = X
        self.y = y

    def __len__(self):
        return len(self.X)

    def __getitem__(self, index):
        feature = self.X[index]
        label = self.y[index]
        # 前処理などを書く -----

        # --------------------
        return feature, label

train_dataset = Mydataset(train_X, train_y)
test_dataset = Mydataset(test_X, test_y)


# 전처리 등 쓰기
train_loader = torch.utils.data.DataLoader(train_dataset,
                                           batch_size=16,  # 배치 크기
                                           shuffle=True,  # 데이터 셔플
                                           num_workers=2,  # 고속화
                                           pin_memory=True,  # 고속화
                                           worker_init_fn=worker_init_fn
                                           )
test_loader = torch.utils.data.DataLoader(test_dataset,
                                          batch_size=16,
                                          shuffle=False,
                                          num_workers=2,
                                          pin_memory=True,
                                          worker_init_fn=worker_init_fn
                                          )


# Modeling --------------------------------------------------------------------

# 모델 정의
class Mymodel(nn.Module):
    def __init__(self):
        super().__init__()
        self.conv1 = torch.nn.Sequential(nn.Conv2d(3, 16, 3, 2, 1),
                                         nn.BatchNorm2d(16),
                                         nn.ReLU())
        self.conv2 = torch.nn.Sequential(nn.Conv2d(16, 64, 3, 2, 1),
                                         nn.BatchNorm2d(64),
                                         nn.ReLU())

        self.fc1 = nn.Linear(2 * 2 * 64, 100)
        self.dropout = nn.Dropout(0.5)
        self.fc2 = torch.nn.Linear(100, 2)

    def forward(self, x):
        x = self.conv1(x)
        x = self.conv2(x)
        x = x.view(x.size(0), -1)
        x = self.fc1(x)
        x = self.dropout(x)
        x = self.fc2(x)
        return x

# 모델, 손실 함수, 최적화 알고리즘 설정
model = Mymodel().to(device)
criterion = nn.CrossEntropyLoss()
optimizer = optim.SGD(model.parameters(), lr=0.01)


# 모델 훈련 함수
def train_model(model, train_loader, test_loader):
    # Train loop ----------------------------
    model.train()  # 학습 모드 켜기
    train_batch_loss = []
    for data, label in train_loader:
        # GPU로 전송
        data, label = data.to(device), label.to(device)
        # 1. 그라디언트 리셋
        optimizer.zero_grad()
        # 2. 추론
        output = model(data)
        # 3. 오류 계산
        loss = criterion(output, label)
        # 4. 오류 반전
        loss.backward()
        # 5. 파라미터 업데이트
        optimizer.step()
        # Train_loss 얻기
        train_batch_loss.append(loss.item())

    # Test(val) loop ----------------------------
    model.eval()  # 학습 모드 끄기
    test_batch_loss = []
    with torch.no_grad():  # 기울기를 계산하지 않음
        for data, label in test_loader:
            data, label = data.to(device), label.to(device)
            output = model(data)
            loss = criterion(output, label).item()
            test_batch_loss.append(loss.item())

    return model, np.mean(train_batch_loss), np.mean(test_batch_loss)


# 훈련 수행
epoch = 100
train_loss = []
test_loss = []

for epoch in tqdm(range(epoch)):
    model, train_l, test_l = train_model(model)
    train_loss.append(train_l)
    test_loss.append(test_loss)


# 학습상황(로스) 확인
plt.plot(train_loss, label='train_loss')
plt.plot(test_loss, label='test_loss')
plt.legend()

# Evaluation ----------------------------------------------------------------

# 학습된 모델에서 예측 결과와 정답 값을 얻습니다.
def retrieve_result(model, dataloader):
    model.eval()
    preds = []
    labels = []
    # Retreive prediction and labels
    with torch.no_grad():
        for data, label in dataloader:
            data, label = data.to(device), label.to(device)
            output = model(data)
            # Collect data
            preds.append(output)
            labels.append(label)
    # Flatten
    preds = torch.cat(preds, axis=0)
    labels = torch.cat(labels, axis=0)
    # Returns as numpy (CPU 환경의 경우 불필요)
    preds = preds.cpu().detach().numpy()
    labels = labels.cpu().detach().numpy()
    return preds, labels


# 예측 결과와 정답 값 획득
preds, labels = retrieve_result(model, test_loader)


# Other ----------------------------------------------------------------------

# 학습된 모델 저장 및 로드
path_saved_model = "./saved_model"

# 모델 저장
torch.save(model.state_dict(), path_saved_model)
# 모델 로드
model = Mymodel()
model.load_state_dict(torch.load(path_saved_model))


# Model summary
from torchsummary import summary
model = model().to(device)
summary(model, input_size=(1, 50, 50))
```

