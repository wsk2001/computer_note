# TensorFlow - 구글 머신러닝 플랫폼

출처: https://codetorial.net/tensorflow/index.html


![_images/tensorflow_logo.png](.\Images\tensorflow_logo.png)



- **TensorFlow**는 구글에서 제공하는 **연구와 개발을 위한 오픈소스 머신러닝 플랫폼**입니다.
- **TensorFlow**는 초심자와 전문가 모두에게 데스크탑, 모바일, 웹, 클라우드 개발을 위한 API를 제공합니다.
- 공식 홈페이지에 의하면 올해 새롭게 공개된 “**TensorFlow 2.0은 사용자의 생산성을 향상시키기 위해서 많은 것을 바꾸었습니다. 불필요한 API를 제거하고 API의 일관성을 높였으며 파이썬 런타임과 즉시 실행을 통합하였다**”고 소개합니다.

![_images/tensorflow_intro_0.png](.\Images\tensorflow_intro_0.png)

이 튜토리얼에서는 **TensorFlow**의 기본적인 사용법을 다양한 예제와 함께 알아봅니다.

순서는 아래와 같습니다.

Contents

- [TensorFlow - 구글 머신러닝 플랫폼](https://codetorial.net/tensorflow/index.html#)
- [1. 텐서 기초 살펴보기](https://codetorial.net/tensorflow/basics_of_tensor.html)
- [2. 간단한 신경망 만들기](https://codetorial.net/tensorflow/simple_neural_network.html)
- [3. 손실 함수 살펴보기](https://codetorial.net/tensorflow/basics_of_loss_function.html)
- [4. 옵티마이저 사용하기](https://codetorial.net/tensorflow/basics_of_optimizer.html)
- [5. AND 로직 연산 학습하기](https://codetorial.net/tensorflow/training_logic_and_operation.html)
- [6. 뉴런층의 속성 확인하기](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html)
- [7. 뉴런층의 출력 확인하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html)
- [8. MNIST 손글씨 이미지 분류하기](https://codetorial.net/tensorflow/mnist_classification.html)
- [9. Fashion MNIST 이미지 분류하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html)
- [10. 합성곱 신경망 사용하기](https://codetorial.net/tensorflow/convolutional_neural_network.html)
- [11. 말과 사람 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html)
- [12. 고양이와 개 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html)
- [13. 이미지 어그멘테이션의 효과](https://codetorial.net/tensorflow/image_augmentation.html)
- [14. 전이 학습 활용하기](https://codetorial.net/tensorflow/transfer_learning.html)
- [15. 다중 클래스 분류 문제](https://codetorial.net/tensorflow/multiclass_classification.html)
- [16. 시냅스 가중치 얻기](https://codetorial.net/tensorflow/get_weights.html)
- [17. 시냅스 가중치 적용하기](https://codetorial.net/tensorflow/set_weights.html)
- [18. 모델 시각화하기](https://codetorial.net/tensorflow/visualize_model.html)
- [19. 훈련 과정 시각화하기](https://codetorial.net/tensorflow/visualize_training_history.html)
- [20. 모델 저장하고 복원하기](https://codetorial.net/tensorflow/save_load_model.html)
- [21. 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/index.html)
- [22. 자연어 처리하기 1](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html)
- [23. 자연어 처리하기 2](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html)
- [24. 자연어 처리하기 3](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html)
- [25. Reference](https://codetorial.net/tensorflow/reference/index.html)



### 다음글

다음글 : [1. 텐서 기초 살펴보기](https://codetorial.net/tensorflow/basics_of_tensor.html)



# 1. 텐서 기초 살펴보기

![basics_of_tensor](.\Images\basics_of_tensor_00.png)

■ **Table of Contents**

- [1) 텐서란?](https://codetorial.net/tensorflow/basics_of_tensor.html#id2)
- [2) 텐서의 차원 - 랭크 (Rank)](https://codetorial.net/tensorflow/basics_of_tensor.html#rank)
- [3) 텐서 만들기](https://codetorial.net/tensorflow/basics_of_tensor.html#id4)
- [4) 텐서의 자료형과 형태](https://codetorial.net/tensorflow/basics_of_tensor.html#id8)
- [5) 간단한 수학 연산](https://codetorial.net/tensorflow/basics_of_tensor.html#id11)
- [6) NumPy 호환성](https://codetorial.net/tensorflow/basics_of_tensor.html#id13)

## 1) 텐서란?

![basics_of_tensor](.\Images\basics_of_tensor_01.png)



**텐서 (Tensor)**는 **다차원 배열 (Multi-dimensional Array)**입니다.

**텐서 (Tensor)**는 벡터 (Vector)와 행렬 (Matrix)을 일반화한 것이며, 3차원 이상으로 확장할 수 있습니다.

**텐서 (Tensor)**는 TensorFlow의 가장 주요한 객체이며, TensorFlow의 작업은 주로 텐서의 연산으로 이루어집니다.

즉, TensorFlow는 **텐서 (Tensor)**를 정의하고 연산을 수행하도록 하는 프레임워크 (Framework)입니다.

## 2) 텐서의 차원 - 랭크 (Rank)

### 텐서의 랭크 확인하기

```
import tensorflow as tf

scalar = tf.constant(1)
vector = tf.constant([1, 2, 3])
matrix = tf.constant([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
tensor = tf.constant([[[1, 2, 3], [4, 5, 6], [7, 8, 9]],
                    [[1, 2, 3], [4, 5, 6], [7, 8, 9]],
                    [[1, 2, 3], [4, 5, 6], [7, 8, 9]]])

print(tf.rank(scalar))
print(tf.rank(vector))
print(tf.rank(matrix))
print(tf.rank(tensor))
```



```
tf.Tensor(0, shape=(), dtype=int32)
tf.Tensor(1, shape=(), dtype=int32)
tf.Tensor(2, shape=(), dtype=int32)
tf.Tensor(3, shape=(), dtype=int32)
```

텐서 (Tensor) 객체의 **랭크 (Rank)**는 차원의 수 (n-dimension)입니다.

**tf.rank()**는 텐서의 랭크를 반환합니다.

텐서 scalar, vector, matrix, tensor는 각각 랭크 0, 1, 2, 3를 가집니다.

## 3) 텐서 만들기

### 모듈 임포트하기

```
import tensorflow as tf
```



TensorFlow 프로그램을 만드는 가장 첫번째 단계는 tensorflow 라이브러리를 임포트하는 것입니다.

### tf.constant() 사용하기

```
a = tf.constant(1)
b = tf.constant([2])
c = tf.constant([[1, 2], [3, 4]])

print(a)
print(b)
print(c)
```



```
tf.Tensor(1, shape=(), dtype=int32)
tf.Tensor([2], shape=(1,), dtype=int32)
tf.Tensor(
[[1 2]
 [3 4]], shape=(2, 2), dtype=int32)
```



**tf.constant()**는 상수 텐서를 만듭니다.

### tf.zeros() 사용하기

```
a = tf.zeros(1)
b = tf.zeros([2])
c = tf.zeros([2, 3])

print(a)
print(b)
print(c)
```



```
tf.Tensor([0.], shape=(1,), dtype=float32)
tf.Tensor([0. 0.], shape=(2,), dtype=float32)
tf.Tensor(
[[0. 0. 0.]
[0. 0. 0.]], shape=(2, 3), dtype=float32)
```



**tf.zeros()**는 모든 요소가 0인 텐서를 만듭니다.

**tf.zeros()**에 만들어질 텐서의 형태 (shape)를 입력합니다.

텐서의 형태에 대해서는 [4) 텐서의 데이터 타입과 형태](https://codetorial.net/tensorflow/basics_of_tensor.html#id8)를 참고하세요.

### tf.ones() 사용하기

```
a = tf.ones(3)
b = tf.ones([4])
c = tf.ones([2, 2, 2])

print(a)
print(b)
print(c)
```



```
tf.Tensor([1. 1. 1.], shape=(3,), dtype=float32)
tf.Tensor([1. 1. 1. 1.], shape=(4,), dtype=float32)
tf.Tensor(
[[[1. 1.]
[1. 1.]]

[[1. 1.]
[1. 1.]]], shape=(2, 2, 2), dtype=float32)
```



**tf.ones()**는 모든 요소가 1인 텐서를 만듭니다.

**tf.ones()**에 만들어질 텐서의 형태 (shape)를 입력합니다.

텐서의 형태에 대해서는 [4) 텐서의 데이터 타입과 형태](https://codetorial.net/tensorflow/basics_of_tensor.html#id8)를 참고하세요.

### tf.range() 사용하기

```
a = tf.range(0, 3)
b = tf.range(1, 5, 2)

print(a)
print(b)
```



```
tf.Tensor([0 1 2], shape=(3,), dtype=int32)
tf.Tensor([1 3], shape=(2,), dtype=int32)
```



**tf.range()**는 파이썬 [range()](https://codetorial.net/python/builtin_function/range.html)와 비슷하게, 주어진 범위와 간격을 갖는 숫자들의 시퀀스를 만듭니다.

### tf.linspace() 사용하기

```
a = tf.linspace(0, 1, 3)
b = tf.linspace(0, 3, 10)

print(a)
print(b)
```



```
tf.Tensor([0.  0.5 1. ], shape=(3,), dtype=float64)
tf.Tensor(
[0.         0.33333333 0.66666667 1.         1.33333333 1.66666667
2.         2.33333333 2.66666667 3.        ], shape=(10,), dtype=float64)
```



**tf.linspace()**는 [numpy.linspace()](https://codetorial.net/numpy/functions/numpy_linspace.html)와 비슷하게, 주어진 범위를 균일한 간격으로 나누는 숫자의 시퀀스를 반환합니다.

[NumPy 다양한 함수들](https://codetorial.net/numpy/functions/index.html) 페이지를 참고하세요.

## 4) 텐서의 자료형과 형태

### 예제1

```
a = tf.constant(1)
b = tf.constant([2])
c = tf.constant([3, 4, 5])

print(a.dtype, a.shape)
print(b.dtype, b.shape)
print(c.dtype, c.shape)
```



```
<dtype: 'int32'> ()
<dtype: 'int32'> (1,)
<dtype: 'int32'> (3,)
```



**dtype**, **shape** 속성은 각각 텐서 (Tensor)의 자료형과 형태를 반환합니다.

텐서 a, b, c는 모두 정수형 (int32)의 데이터를 갖고, 각각 ( ), (1,), (3,)의 형태를 가짐을 알 수 있습니다.

### 예제2

```
d = tf.constant([1., 2.])
e = tf.constant([[1, 2., 3]])
f = tf.constant([[1, 2], [3, 4]])

print(d.dtype, d.shape)
print(e.dtype, e.shape)
print(f.dtype, f.shape)
```



```
<dtype: 'float32'> (2,)
<dtype: 'float32'> (1, 3)
<dtype: 'int32'> (2, 2)
```



텐서 d는 실수형 (float32)의 데이터를 갖고, (2,)의 형태를 가집니다.

텐서 e는 실수형 (float32)의 데이터를 갖고, (1, 3)의 형태를 가집니다.

(정수와 실수를 모두 포함하는 텐서의 자료형은 실수형이 됩니다.)

텐서 f는 정수형 (int32)의 데이터를 갖고, (2, 2)의 형태를 가집니다.

## 5) 간단한 수학 연산

### 예제

```
a = tf.add(1, 2)
b = tf.subtract(10, 5)
c = tf.square(3)
d = tf.reduce_sum([1, 2, 3])
e = tf.reduce_mean([1, 2, 3])

print(a)
print(b)
print(c)
print(d)
print(e)
```



```
tf.Tensor(3, shape=(), dtype=int32)
tf.Tensor(5, shape=(), dtype=int32)
tf.Tensor(9, shape=(), dtype=int32)
tf.Tensor(6, shape=(), dtype=int32)
tf.Tensor(2, shape=(), dtype=int32)
```



TensorFlow는 **텐서 (Tensor)**를 생성하고 연산하는 다양한 수학적 함수를 제공합니다.

## 6) NumPy 호환성



**텐서 (Tensor)**는 **NumPy 어레이**와 비슷하지만,

- 텐서는 GPU, TPU와 같은 가속기에서 사용할 수 있고,
- 텐서는 값을 변경할 수 없습니다.

### 텐서를 NumPy 어레이로

```
a = tf.constant([1, 2, 3])

print(a)
print(a.numpy())
```



```
tf.Tensor([1 2 3], shape=(3,), dtype=int32)
[1 2 3]
```



텐서의 **numpy()** 메서드를 사용해서 텐서를 NumPy 어레이로 변환할 수 있습니다.

### NumPy 어레이를 텐서로

```
import numpy as np

b = np.ones(3)

print(b)
print(tf.multiply(b, 3))
```



```
[1. 1. 1.]
tf.Tensor([3. 3. 3.], shape=(3,), dtype=float64)
```



반대로, 다양한 TensorFlow 연산은 자동으로 NumPy 어레이를 텐서로 변환합니다.



### 이전글/다음글

이전글 : [TensorFlow - 구글 머신러닝 플랫폼](https://codetorial.net/tensorflow/index.html)

다음글 : [2. 간단한 신경망 만들기](https://codetorial.net/tensorflow/simple_neural_network.html)





# 2. 간단한 신경망 만들기

![simple_neural_network](.\Images\simple_neural_network_00.png)

■ **Table of Contents**

- [1) Neural Network 구성하기](https://codetorial.net/tensorflow/simple_neural_network.html#neural-network)
- [2) Neural Network 컴파일하기](https://codetorial.net/tensorflow/simple_neural_network.html#id2)
- [3) Neural Network 훈련하기](https://codetorial.net/tensorflow/simple_neural_network.html#id3)
- [4) Neural Network 예측하기](https://codetorial.net/tensorflow/simple_neural_network.html#id4)

## 1) Neural Network 구성하기

아래의 예제는 Tensorflow의 가장 간단한 Neural Network를 구현합니다.

![simple_neural_network](.\Images\simple_neural_network_01.png)

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

model = keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])
```



**tf.keras** (tensorflow.keras)는 TensorFlow의 하이레벨 구현을 위한 **Keras API** 모듈입니다.

**tf.keras** 모듈의 **Sequential** 클래스는 Neural Network의 각 층을 순서대로 쌓을 수 있도록 합니다.

**tf.keras.layers** 모듈의 **Dense** 클래스는 (완전 연결된) 하나의 뉴런층을 구현합니다.

**units**는 뉴런 또는 출력 노드의 개수를 의미하며, 양의 정수로 설정합니다.

**input_shape**는 입력 데이터의 형태를 결정합니다.

![simple_neural_network_dense_units](.\Images\simple_neural_network_02.png)

## 2) Neural Network 컴파일하기

```
model.compile(loss='mean_squared_error', optimizer='sgd')
```



다음으로 Neural Network 모델을 컴파일하는 과정에서는,

모델의 학습에 필요한 **손실함수 (loss function)**와 **옵티마이저 (optimizer)**를 결정합니다.

손실함수는 Neural Network의 예측이 얼마나 잘 맞는지 측정하는 역할을 하고, 옵티마이저는 더 개선된 예측값을 출력하도록 최적화하는 알고리즘입니다.

예제에서는 각각 **mean_squared_error**와 **SGD (Stochastic Gradient Descent)**로 설정했습니다.

경우에 따라 다른 손실함수와 옵티마이저가 더 효과적일 수 있습니다.

더 자세한 내용은 [TensorFlow 공식 문서](https://www.tensorflow.org/)를 참고하세요.

## 3) Neural Network 훈련하기

**Sequantial** 클래스의 **fit()** 메서드는 주어진 입출력 데이터에 대해 지정한 횟수만큼 Neural Network를 훈련합니다.

훈련이 이루어질 때마다, Neural Network는 주어진 입력에 대해 주어진 출력값에 더 가까운 값을 출력하게 됩니다.

![simple_neural_network_fit_method](.\Images\simple_neural_network_03.png)

```
xs = np.array([-1.0, 0.0, 1.0, 2.0, 3.0, 4.0], dtype=float)
ys = np.array([-3.0, -1.0, 1.0, 3.0, 5.0, 7.0], dtype=float)

model.fit(xs, ys, epochs=500)
```



xs, ys는 Neural Network의 훈련에 사용할 입력과 출력 데이터이며, ‘y = 2x - 1’의 관계를 갖는 것처럼 보입니다.

**에포크 (epoch)**는 주어진 데이터를 한 번 훈련하는 단위입니다.

## 4) Neural Network 예측하기

**Sequantial** 클래스의 **predict()** 메서드를 사용하면 특정 입력에 대해 Neural Network가 출력 (예측)하는 값을 얻을 수 있습니다.

![simple_neural_network_predict_method](.\Images\simple_neural_network_04.png)

```
pred = model.predict([5.0])
print(pred)
```



```
[[8.993531]]
```



훈련이 끝난 Neural Network에 숫자 5.0을 입력하면, 약 8.99를 출력합니다.

비록 작은 오차가 있지만, 이제 이 간단한 Neural Network는 어떤 입력 x에 대해서 대략 2x - 1를 출력하도록 훈련되었습니다.

작은 오차가 발생하는 이유는 ‘여섯 개’라는 적은 양의 입출력 데이터를 훈련에 사용했기 때문이고,

또한 모든 x에 대해, 입출력의 관계가 ‘y = 2x - 1’이 아닐 가능성이 있기 때문입니다.

![simple_neural_network_predict_method](.\Images\simple_neural_network_05.png)

### 전체 코드

지금까지 작성한 전체 코드와 출력 결과는 아래와 같습니다.

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

model = keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])
model.compile(optimizer='sgd', loss='mean_squared_error')

xs = np.array([-1.0, 0.0, 1.0, 2.0, 3.0, 4.0], dtype=float)
ys = np.array([-3.0, -1.0, 1.0, 3.0, 5.0, 7.0], dtype=float)

model.fit(xs, ys, epochs=500)

pred = model.predict([5.0])
print(pred)
```



```
Epoch 1/500
1/1 [==============================] - 0s 1ms/step - loss: 49.7585
Epoch 2/500
1/1 [==============================] - 0s 880us/step - loss: 39.5407
Epoch 3/500
1/1 [==============================] - 0s 981us/step - loss: 31.4938
Epoch 4/500
1/1 [==============================] - 0s 921us/step - loss: 25.1550
Epoch 5/500
1/1 [==============================] - 0s 1ms/step - loss: 20.1601
Epoch 6/500
1/1 [==============================] - 0s 2ms/step - loss: 16.2229
Epoch 7/500
1/1 [==============================] - 0s 1ms/step - loss: 13.1178
Epoch 8/500
1/1 [==============================] - 0s 943us/step - loss: 10.6675
Epoch 9/500
1/1 [==============================] - 0s 3ms/step - loss: 8.7327
Epoch 10/500
1/1 [==============================] - 0s 2ms/step - loss: 7.2035

...

Epoch 200/500
1/1 [==============================] - 0s 3ms/step - loss: 0.0328
Epoch 201/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0321
Epoch 202/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0315
Epoch 203/500
1/1 [==============================] - 0s 1ms/step - loss: 0.0308
Epoch 204/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0302
Epoch 205/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0296
Epoch 206/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0290
Epoch 207/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0284
Epoch 208/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0278
Epoch 209/500
1/1 [==============================] - 0s 2ms/step - loss: 0.0272

...

Epoch 490/500
1/1 [==============================] - 0s 1ms/step - loss: 7.9806e-05
Epoch 491/500
1/1 [==============================] - 0s 5ms/step - loss: 7.8166e-05
Epoch 492/500
1/1 [==============================] - 0s 3ms/step - loss: 7.6561e-05
Epoch 493/500
1/1 [==============================] - 0s 13ms/step - loss: 7.4988e-05
Epoch 494/500
1/1 [==============================] - 0s 3ms/step - loss: 7.3448e-05
Epoch 495/500
1/1 [==============================] - 0s 3ms/step - loss: 7.1940e-05
Epoch 496/500
1/1 [==============================] - 0s 3ms/step - loss: 7.0462e-05
Epoch 497/500
1/1 [==============================] - 0s 2ms/step - loss: 6.9013e-05
Epoch 498/500
1/1 [==============================] - 0s 2ms/step - loss: 6.7596e-05
Epoch 499/500
1/1 [==============================] - 0s 2ms/step - loss: 6.6209e-05
Epoch 500/500
1/1 [==============================] - 0s 3ms/step - loss: 6.4849e-05
[[8.993531]]
```





### 이전글/다음글

이전글 : [1. 텐서 기초 살펴보기](https://codetorial.net/tensorflow/basics_of_tensor.html)

다음글 : [3. 손실 함수 살펴보기](https://codetorial.net/tensorflow/basics_of_loss_function.html)



# 3. 손실 함수 살펴보기

![손실 함수 살펴보기](.\Images\loss_functions_mse.png)



**손실 함수 (Loss function)**는 Neural Network의 예측이 얼마나 잘 맞는지 측정하는 역할을 합니다.

손실 함수로부터 얻어진 **손실값 (Loss value)**은 훈련 과정에서 Neural Network가 **얼마나 잘 훈련되었는지 확인하는 지표**가 됩니다.

**Mean Squared Error** 손실 함수를 사용해서 모델의 손실값을 확인하는 과정에 대해 소개합니다.

■ **Table of Contents**

- [1) Neural Network 구성하기](https://codetorial.net/tensorflow/basics_of_loss_function.html#neural-network)
- [2) Neural Network 컴파일하기](https://codetorial.net/tensorflow/basics_of_loss_function.html#id2)
- [3) Neural Network 예측하기](https://codetorial.net/tensorflow/basics_of_loss_function.html#id3)
- [4) Neural Network 손실 계산하기](https://codetorial.net/tensorflow/basics_of_loss_function.html#id4)

## 1) Neural Network 구성하기

아래의 코드는 하나의 입력을 받고 세 개의 출력 노드를 갖는 Neural Network를 구성합니다.

![basics_of_loss_function_01](.\Images\basics_of_loss_function_01.png)

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

model = keras.Sequential([keras.layers.Dense(units=3, input_shape=[1])])
```



**tf.keras** 모듈의 **Sequential** 클래스는 Neural Network의 각 층을 순서대로 쌓을 수 있도록 합니다.

**tf.keras.layers** 모듈의 **Dense** 클래스는 (완전 연결된) 하나의 뉴런층을 구현합니다.

**units**는 뉴런 또는 출력 노드의 개수를 의미하며, 양의 정수로 설정합니다.

**input_shape**는 입력 데이터의 형태를 결정합니다.

## 2) Neural Network 컴파일하기

모델을 구성했다면, 이 모델을 훈련하기 전에 손실 함수와 옵티마이저를 지정해주는 컴파일 과정이 필요합니다.

```
model.compile(loss='mse')
```



**compile()** 메서드의 **loss** 파라미터를 이용해서 손실 함수를 ‘mse’로 지정했습니다.

**mse**는 **Mean Squared Error**의 줄임말이며 아래의 수식을 이용해서 **평균 제곱 오차를 계산**하는 방식입니다.



MSE=1n∑i=1n(yi−y^i)2MSE=1n∑i=1n(yi−y^i)2

- nn은 출력값의 개수.
- yiyi는 관측값 (목표값).
- y^iy^i는 예측값.

예측치와 관측값의 차이인 오차의 제곱에 비례해서 손실 함수로부터 계산되는 손실값이 커집니다.

![loss_functions_mse](.\Images\loss_functions_mse.png)

## 3) Neural Network 예측하기

**predict()** 메서드를 이용해서 Neural Network의 예측값 (predicted value)을 얻을 수 있습니다.

![basics_of_loss_function_02](.\Images\basics_of_loss_function_02.png)

```
pred = model.predict([0])
print(pred)
```



```
[[0. 0. 0.]]
```



임의로 생성된 모델의 가중치 값 (weights)이 있지만, 입력이 0이므로 예측값도 모두 0을 출력합니다.

## 4) Neural Network 손실 계산하기

**evaluate()** 메서드는 예측값과 관측값 사이의 손실값을 반환합니다.

![basics_of_loss_function_03](.\Images\basics_of_loss_function_03.png)

```
model.evaluate([0], [[0, 1, 0]])
```



```
1/1 [==============================] - 0s 33ms/sample - loss: 0.3333
```



**evaluate()** 메서드를 호출하면 손실 값 (**loss**)을 출력합니다.

모델의 손실 함수를 **Mean Squared Error**로 지정했기 때문에, 손실 값 0.3333은 아래와 같이 계산된 결과입니다.



loss=(0−0)2+(1−0)2+(0−0)23=0.3333loss=(0−0)2+(1−0)2+(0−0)23=0.3333



### 이전글/다음글

이전글 : [2. 간단한 신경망 만들기](https://codetorial.net/tensorflow/simple_neural_network.html)

다음글 : [4. 옵티마이저 사용하기](https://codetorial.net/tensorflow/basics_of_optimizer.html)



# 4. 옵티마이저 사용하기

![옵티마이저 사용하기](.\Images\basics_of_optimizer_00.png)



**옵티마이저 (Optimizer)**는 손실 함수을 통해 얻은 **손실값으로부터 모델을 업데이트하는 방식**을 의미합니다.

TensorFlow는 SGD, Adam, RMSprop과 같은 다양한 종류의 [옵티마이저](https://www.tensorflow.org/api_docs/python/tf/keras/optimizers)를 제공합니다.

옵티마이저의 기본 사용법을 알아보고, 훈련 과정에서 옵티마이저에 따라 모델의 손실값이 어떻게 감소하는지 확인해 보겠습니다.

■ **Table of Contents**

- [1) Neural Network 구성하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#neural-network)
- [2) Neural Network 컴파일하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#id3)
- [3) Neural Network 훈련하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#id4)
- [4) 손실값 시각화하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#id5)
- [5) 출력값 시각화하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#id6)
- [6) 옵티마이저 비교하기](https://codetorial.net/tensorflow/basics_of_optimizer.html#id8)

## 1) Neural Network 구성하기

이전 페이지에서와 마찬가지로 1개의 입력, 3개의 출력 노드를 갖는 신경망 모델을 구성합니다.

![basics_of_optimizer_01](.\Images\basics_of_optimizer_01.png)

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

tf.random.set_seed(0)

model = keras.Sequential([keras.layers.Dense(units=3, input_shape=[1])])
```



이번에는 [tf.random](https://www.tensorflow.org/api_docs/python/tf/random) 모듈의 **set_seed()** 함수를 사용해서 랜덤 시드를 설정했습니다.

[tf.keras](https://www.tensorflow.org/api_docs/python/tf/keras) 모듈의 **Sequantial** 클래스는 Neural Network의 각 층을 순서대로 쌓을 수 있도록 합니다.

## 2) Neural Network 컴파일하기

구성한 모델의 손실 함수와 옵티마이저를 지정하기 위해 **compile()** 메서드를 사용합니다.

```
model.compile(loss='mse', optimizer='SGD')
```



손실 함수로 ‘mse’를, 옵티마이저로 ‘SGD’을 지정했습니다.

‘SGD’는 **Stochastic Gradient Descent**의 줄임말이며, 우리말로는 확률적 경사하강법이라고 부릅니다.

## 3) Neural Network 훈련하기

**fit()** 메서드는 컴파일 과정에서 지정한 손실 함수와 옵티마이저를 사용해서 모델을 훈련합니다.

```
model.fit([1], [[0, 1, 0]], epochs=1)
model.evaluate([1], [[0, 1, 0]])
```



```
1/1 [==============================] - 0s 1ms/step - loss: 1.0738
1/1 [==============================] - 0s 1ms/step - loss: 1.0453
1.0453256368637085
```



**fit()** 메서드는 훈련 진행 상황과 현재의 손실값을 반환합니다.

1회의 에포크 (epoch) 이후, **evaluate()** 메서드를 사용해서 손실값을 확인해보면

손실값이 1.0738에서 1.0453으로 감소했음을 알 수 있습니다.

```
history = model.fit([1], [[0, 1, 0]], epochs=100)
```



```
Epoch 1/100
1/1 [==============================] - 0s 1ms/step - loss: 1.0738
Epoch 2/100
1/1 [==============================] - 0s 1ms/step - loss: 1.0453
Epoch 3/100
1/1 [==============================] - 0s 982us/step - loss: 1.0176
.
.
.
Epoch 98/100
1/1 [==============================] - 0s 836us/step - loss: 0.0794
Epoch 99/100
1/1 [==============================] - 0s 1ms/step - loss: 0.0773
Epoch 100/100
1/1 [==============================] - 0s 1ms/step - loss: 0.0753
```



이번에는 훈련의 에포크를 100회로 지정했습니다.

100회 훈련 과정의 훈련 시간과 손실값이 출력됩니다.

## 4) 손실값 시각화하기

```
import matplotlib.pyplot as plt

plt.style.use('default')
plt.rcParams['figure.figsize'] = (4, 3)
plt.rcParams['font.size'] = 12

loss = history.history['loss']
plt.plot(loss)
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.show()
```



**fit()** 메서드는 **History** 객체를 반환합니다.

**History** 객체의 history 속성은 훈련 과정의 손실값 (loss values)과 지표 (metrics)를 포함합니다.

컴파일 과정에서 지표를 지정하지 않았기 때문에 이 예제의 history 속성은 지표 (metrics)를 포함하지 않습니다.

훈련 과정의 손실값을 [Matplotlib](https://codetorial.net/matplotlib/index.html)을 이용해서 그래프로 나타내면 아래와 같이 감소하는 경향을 확인할 수 있습니다.

![basics_of_optimizer_02](.\Images\basics_of_optimizer_02.png)

## 5) 출력값 시각화하기

```
import tensorflow as tf
from tensorflow import keras
import matplotlib.pyplot as plt
import numpy as np

plt.style.use('default')
plt.rcParams['figure.figsize'] = (4, 3)
plt.rcParams['font.size'] = 12

tf.random.set_seed(0)

model = keras.Sequential([keras.layers.Dense(units=3, input_shape=[1], use_bias=False)])
model.compile(loss='mse', optimizer='SGD')

pred = model.predict([1])
print(pred)
print(model.get_weights())

plt.bar(np.arange(3), pred[0])
plt.ylim(-1.1, 1.1)
plt.xlabel('Output Node')
plt.ylabel('Output')
plt.text(-0.4, 0.8, 'Epoch 0')
plt.tight_layout()
plt.savefig('./plt/pred000.png')
plt.clf()

epochs = 500
for i in range(1, epochs+1):
  model.fit([1], [[0, 1, 0]], epochs=1, verbose=0)
  pred = model.predict([1])

  if i % 25 == 0:
      plt.bar(np.arange(3), pred[0])
      plt.ylim(-1.1, 1.1)
      plt.xlabel('Output Node')
      plt.ylabel('Output')
      plt.text(-0.4, 0.8, 'Epoch ' + str(i))
      plt.tight_layout()
      plt.savefig('./plt/pred' + str(i).zfill(3) + '.png')
      plt.clf()

print(pred)
print(model.get_weights())
```



```
[[-0.5095548  -0.7187625   0.08668923]]
[array([[-0.5095548 , -0.7187625 ,  0.08668923]], dtype=float32)]
[[-0.0179761   0.9393657   0.00305823]]
[array([[-0.0179761 ,  0.9393657 ,  0.00305823]], dtype=float32)]
```



이 코드는 Matplotlib을 이용해서 500회의 에포크 동안 훈련에 의해 출력값이 변화하는 과정을 시각화합니다.

Matplotlib의 다양한 함수에 대해서는 [Matplotlib - 파이썬으로 그래프 그리기](https://codetorial.net/matplotlib/index.html)를 참고하세요.

아래 그림과 같이 훈련 과정 동안 출력값이 Target 값 [0, 1, 0]에 가까워지는 것을 알 수 있습니다.

![출력값 시각화하기](.\Images\basics_of_optimizer_04.gif)

## 6) 옵티마이저 비교하기

아래의 예제는 세가지 옵티마이저 **‘SGD’**, **‘Adam’**, **‘RMSprop’**이 모델을 업데이트하는 성능을 비교합니다.

```
import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt

plt.style.use('default')
plt.rcParams['figure.figsize'] = (4, 3)
plt.rcParams['font.size'] = 12

tf.random.set_seed(0)
model = keras.Sequential([keras.layers.Dense(units=3, input_shape=[1])])

tf.random.set_seed(0)
model2 = tf.keras.models.clone_model(model)

tf.random.set_seed(0)
model3 = tf.keras.models.clone_model(model)

model.compile(loss='mse', optimizer='SGD')
model2.compile(loss='mse', optimizer='Adam')
model3.compile(loss='mse', optimizer='RMSprop')

history = model.fit([1], [[0, 1, 0]], epochs=100, verbose=0)
history2 = model2.fit([1], [[0, 1, 0]], epochs=100, verbose=0)
history3 = model3.fit([1], [[0, 1, 0]], epochs=100, verbose=0)

loss = history.history['loss']
loss2 = history2.history['loss']
loss3 = history3.history['loss']
plt.plot(loss, label='SGD')
plt.plot(loss2, label='Adam')
plt.plot(loss3, label='RMSprop')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend(loc='lower left')
plt.show()
```



우선 세 개의 모델이 동일한 가중치 값을 갖도록 하기 위해 **set_seed()** 함수를 세번 호출했습니다.

**compile()** 메서드에 각각 다른 옵티마이저를 지정합니다.

100회 훈련 과정의 손실값을 시각화하면 아래와 같습니다.

옵티마이저에 따라 모델을 업데이트하는 방식과 손실값이 감소하는 경향에 차이가 있음을 알 수 있습니다.

![basics_of_optimizer_03](.\Images\basics_of_optimizer_03.png)



### 이전글/다음글

이전글 : [3. 손실 함수 살펴보기](https://codetorial.net/tensorflow/basics_of_loss_function.html)

다음글 : [5. AND 로직 연산 학습하기](https://codetorial.net/tensorflow/training_logic_and_operation.html)



# 5. AND 로직 연산 학습하기

![training_logic_and_operation](.\Images\training_logic_and_operation_00.png)



AND 연산은 논리 연산 (Logic operation)의 한 종류로 위의 그림과 같이 두 상태가 모두 참 (True, 1)일 때 참이고,

둘 중 하나라도 거짓 (False, 0)이라면 거짓이 되는 연산입니다.

전기 신호가 0과 1로 구성되어 있는 디지털 회로에서는 트랜지스터 게이트의 조합으로 구현할 수 있습니다.

![training_logic_and_operation](.\Images\training_logic_and_operation_01.png)



위 그림은 두 개의 입력값을 받고, 하나의 값을 출력하는 간단한 **인공신경망 (Artificial Neural Network)**을 나타냅니다.

이제 **TensorFlow**를 이용해서 두 입력값에 대해 AND 논리 연산의 결과를 출력하는 신경망을 구현해보겠습니다.

■ **Table of Contents**

- [1) 훈련 데이터 준비하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#id1)
- [2) Neural Network 구성하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#neural-network)
- [3) Neural Network 컴파일하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#id2)
- [4) Neural Network 훈련하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#id3)
- [5) 손실값 확인하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#id5)
- [6) 훈련 결과 확인하기](https://codetorial.net/tensorflow/training_logic_and_operation.html#id6)

## 1) 훈련 데이터 준비하기

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

tf.random.set_seed(0)

# 1. 훈련 데이터 준비하기
x_train = [[0, 0], [0, 1], [1, 0], [1, 1]]
y_train = [[0], [0], [0], [1]]
```



우선 [tf.random](https://www.tensorflow.org/api_docs/python/tf/random) 모듈의 **set_seed()** 함수를 사용해서 랜덤 시드를 설정했습니다.

예제에서 x_train, y_train은 각각 훈련에 사용할 입력값, 출력값입니다.

## 2) Neural Network 구성하기

```
# 2. 모델 구성하기
model = keras.Sequential([
    keras.layers.Dense(units=3, input_shape=[2], activation='relu'),
    keras.layers.Dense(units=1)
    ])
```



[tf.keras](https://www.tensorflow.org/api_docs/python/tf/keras) 모듈의 **Sequantial** 클래스는 Neural Network의 각 층을 순서대로 쌓을 수 있도록 합니다.

[tf.keras.layers](https://www.tensorflow.org/api_docs/python/tf/keras/layers) 모듈의 **Dense** 클래스는 완전히 연결된 뉴런층을 구성합니다.

두 개의 **Dense**를 사용해서 아래 그림과 같은 구조의 신경망을 구성했습니다.

은닉층 (Hidden layer)의 활성화함수로 **ReLU (Rectified Linear Unit)**를 사용했습니다.

![training_logic_and_operation](.\Images\training_logic_and_operation_02.png)

## 3) Neural Network 컴파일하기

```
# 3. 모델 컴파일하기
model.compile(loss='mse', optimizer='Adam')
```



손실 함수로 **‘mse’**를, 옵티마이저로 **‘Adam’**을 지정했습니다.

## 4) Neural Network 훈련하기

```
# 4. 모델 훈련하기
pred_before_training = model.predict(x_train)
print('Before Training: \n', pred_before_training)

history = model.fit(x_train, y_train, epochs=1000, verbose=0)

pred_after_training = model.predict(x_train)
print('After Training: \n', pred_after_training)
```



```
Before Training:
[[0.        ]
[0.6210649 ]
[0.06930891]
[0.6721569 ]]
After Training:
[[-0.00612799]
[ 0.00896954]
[ 0.00497065]
[ 0.9905546 ]]
```



[tf.keras](https://www.tensorflow.org/api_docs/python/tf/keras) 모듈의 **Model** 클래스는 **predict()** 메서드를 포함합니다.

**predict()** 메서드를 이용해서 Neural Network의 예측값 (predicted value)을 얻을 수 있습니다.

**Model** 클래스의 **fit()** 메서드는 모델을 훈련하고, 훈련 진행 상황과 현재의 손실값을 반환합니다.

모델 훈련의 전후로 입력 데이터에 대한 Neural Network의 예측값을 출력하도록 했습니다.

## 5) 손실값 확인하기

```
# 5. 손실값 확인하기
import matplotlib.pyplot as plt

loss = history.history['loss']
plt.plot(loss)
plt.xlabel('Epoch', labelpad=15)
plt.ylabel('Loss', labelpad=15)

plt.show()
```



**fit()** 메서드가 반환하는 손실값을 [Matplotlib](https://codetorial.net/matplotlib/index.html) 라이브러리를 사용해서 시각화했습니다.

결과는 아래와 같습니다.

![training_logic_and_operation](.\Images\training_logic_and_operation_03.png)

## 6) 훈련 결과 확인하기

```
import matplotlib.pyplot as plt
import numpy as np

plt.style.use('default')
plt.rcParams['figure.figsize'] = (6, 4)
plt.rcParams['font.size'] = 14

plt.plot(pred_before_training, 's-', markersize=10, label='pred_before_training')
plt.plot(pred_after_training, 'd-', markersize=10, label='pred_after_training')
plt.plot(y_train, 'o-', markersize=10, label='y_train')

plt.xticks(np.arange(4), labels=['[0, 0]', '[0, 1]', '[1, 0]', '[1, 1]'])
plt.xlabel('Input (x_train)', labelpad=15)
plt.ylabel('Output (y_train)', labelpad=15)

plt.legend()
plt.show()
```



[Matplotlib](https://codetorial.net/matplotlib/index.html) 라이브러리를 사용해서 훈련 전후의 입력값, 출력값을 나타냈습니다.

간단한 신경망에 대해 1000회의 훈련이 이루어지면, 네가지 경우의 0과 1 입력에 대해 1% 미만의 오차로

AND 연산을 수행할 수 있음을 확인할 수 있습니다.

![training_logic_and_operation](.\Images\training_logic_and_operation_04.png)

### 전체 예제 코드

전체 코드는 아래와 같습니다.

신경망을 구성하는 과정에서 뉴런의 개수, 활성화함수, 그리고 옵티마이저를 바꿔가면서

훈련의 횟수와 정확도에 미치는 영향을 확인해 볼 수 있습니다.

```
import tensorflow as tf
from tensorflow import keras
import numpy as np

tf.random.set_seed(0)


# 1. 훈련 데이터 준비하기
x_train = [[0, 0], [0, 1], [1, 0], [1, 1]]
y_train = [[0], [0], [0], [1]]


# 2. 모델 구성하기
model = keras.Sequential([
    keras.layers.Dense(units=3, input_shape=[2], activation='relu'),
    # keras.layers.Dense(units=3, input_shape=[2], activation='sigmoid'),
    keras.layers.Dense(units=1)
    ])


# 3. 모델 컴파일하기
# model.compile(loss='mse', optimizer='SGD')
model.compile(loss='mse', optimizer='Adam')


# 4. 모델 훈련하기
pred_before_training = model.predict(x_train)
print('Before Training: \n', pred_before_training)

history = model.fit(x_train, y_train, epochs=1000, verbose=0)

pred_after_training = model.predict(x_train)
print('After Training: \n', pred_after_training)


# 5. 손실값 확인하기
import matplotlib.pyplot as plt

loss = history.history['loss']
plt.plot(loss)
plt.xlabel('Epoch', labelpad=15)
plt.ylabel('Loss', labelpad=15)

plt.show()
```





### 이전글/다음글

이전글 : [4. 옵티마이저 사용하기](https://codetorial.net/tensorflow/basics_of_optimizer.html)

다음글 : [6. 뉴런층의 속성 확인하기](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html)



# 6. 뉴런층의 속성 확인하기

![get_attribute_of_neuron_layers](.\Images\get_attribute_of_neuron_layers_0.png)



[tf.keras.layers.Layer](https://www.tensorflow.org/api_docs/python/tf/keras/layers/Layer)는 Neural Network의 모든 레이어 객체가 상속하는 클래스입니다.

tf.keras.layers.Layer의 다양한 속성 (Attribute)을 이용해서 각 레이어에 대한 정보를 확인할 수 있습니다.

■ **Table of Contents**

- [1) 뉴런층의 이름 (name)과 자료형 (dtype)](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html#name-dtype)
- [2) 뉴런층의 입력 (input)과 출력 (output)](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html#input-output)
- [3) 뉴런층의 활성화함수 (activation)](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html#activation)
- [4) 뉴런층의 가중치 (weights)](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html#weights)
- [5) get_weights() 메서드](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html#get-weights)

## 1) 뉴런층의 이름 (name)과 자료형 (dtype)

```
import tensorflow as tf

tf.random.set_seed(0)


# 1. 뉴런층 만들기
input_layer = tf.keras.layers.InputLayer(input_shape=(3,))
hidden_layer = tf.keras.layers.Dense(units=4, activation='relu')
output_layer = tf.keras.layers.Dense(units=2, activation='softmax')


# 2. 모델 구성하기
model = tf.keras.Sequential([
    input_layer,
    hidden_layer,
    output_layer
    ])


# 3. 모델 컴파일하기
model.compile(loss='mse', optimizer='Adam')


# 4. 뉴런층의 이름과 자료형
print(input_layer.name, input_layer.dtype)
print(hidden_layer.name, hidden_layer.dtype)
print(output_layer.name, output_layer.dtype)
```



```
input_1 float32
dense float32
dense_1 float32
```



**name**은 뉴런층의 이름입니다.

**dtype**은 뉴런층의 연산과 웨이트 값에 사용되는 자료형입니다.

아래와 같은 방법으로도 뉴런층의 속성을 확인할 수 있습니다.

```
print(model.layers[0].name)
print(model.layers[1].name)
print(model.layers[2].name)
```



```
dense
dense_1
Traceback (most recent call last):
File "C:/Python/test.py", line 31, in <module>
  print(model.layers[2].name)
IndexError: list index out of range
```



**model.layers**는 구성한 Neural Network 모델의 **(입력층을 제외한)** 뉴런층 레이어 객체를 리스트의 형태로 반환합니다.

![get_attribute_of_neuron_layers](.\Images\get_attribute_of_neuron_layers_1.png)

**model.layers[0]**은 모델의 첫번째 뉴런층, 즉 은닉층 (hidden layer)입니다.

**model.layers[1]**은 모델의 두번째 뉴런층, 즉 출력층 (output_layer)입니다.

모델이 **(입력층을 제외한)** 두 개의 뉴런층을 포함하기 때문에

**model.layers[2].name**을 출력하면 에러를 발생합니다.

## 2) 뉴런층의 입력 (input)과 출력 (output)

```
print(input_layer.input)
print(input_layer.output)

print(hidden_layer.input)
print(hidden_layer.output)

print(hidden_layer.input.shape)
print(hidden_layer.output.shape)

print(output_layer.input)
print(output_layer.output)
```



```
Tensor("input_1:0", shape=(None, 3), dtype=float32)
Tensor("input_1:0", shape=(None, 3), dtype=float32)
Tensor("input_1:0", shape=(None, 3), dtype=float32)
Tensor("dense/Identity:0", shape=(None, 4), dtype=float32)
(None, 3)
(None, 4)
Tensor("dense/Identity:0", shape=(None, 4), dtype=float32)
Tensor("dense_1/Identity:0", shape=(None, 2), dtype=float32)
```



**input**은 뉴런층의 **입력 텐서 (input tensor)**입니다.

**output**은 뉴런층의 **출력 텐서 (output tensor)**입니다.

은닉층 (hidden_layer)의 입력과 출력의 형태 (shape)를 출력해보면

입력 텐서는 길이 3의 형태, 출력 텐서는 길이 4의 형태를 가짐을 알 수 있습니다.

예를 들어, (None, 3)은 **길이 3의 벡터의 시퀀스**형태가 될 수 있음을 의미합니다.

## 3) 뉴런층의 활성화함수 (activation)

```
print(hidden_layer.activation)
print(hidden_layer.activation.__name__)
print(output_layer.activation)
print(output_layer.activation.__name__)
```



```
<function relu at 0x000001F5D26EE288>
relu
<function softmax at 0x000001F5D26E54C8>
softmax
```



**activation**은 뉴런 노드의 **활성화함수 (Activation function)**를 나타냅니다.

**__name__**을 사용해서 활성화함수의 이름을 출력했습니다.

![get_attribute_of_neuron_layers](.\Images\get_attribute_of_neuron_layers_2.png)

## 4) 뉴런층의 가중치 (weights)

```
print(hidden_layer.weights)
print(output_layer.weights)
```



```
[<tf.Variable 'dense/kernel:0' shape=(3, 4) dtype=float32, numpy=
array([[-0.3851872 , -0.54333335,  0.0655309 ,  0.1134268 ],
     [-0.15428883,  0.5699866 , -0.01254469,  0.9223561 ],
     [ 0.36428273, -0.6936733 ,  0.38850498,  0.30073535]],
    dtype=float32)>, <tf.Variable 'dense/bias:0' shape=(4,) dtype=float32, numpy=array([0., 0., 0., 0.], dtype=float32)>]
[<tf.Variable 'dense_1/kernel:0' shape=(4, 2) dtype=float32, numpy=
array([[ 0.11082816, -0.55741405],
     [ 0.7298498 ,  0.5545671 ],
     [ 0.29023337,  0.0607245 ],
     [-0.971118  ,  0.74701834]], dtype=float32)>, <tf.Variable 'dense_1/bias:0' shape=(2,) dtype=float32, numpy=array([0., 0.], dtype=float32)>]
```



**weights**를 사용해서 각 뉴런층의 시냅스 가중치에 대한 정보를 얻을 수 있습니다.

![get_attribute_of_neuron_layers](.\Images\get_attribute_of_neuron_layers_3.png)

## 5) get_weights() 메서드

```
print(hidden_layer.get_weights())
print(output_layer.get_weights())
```



```
[array([[-0.3851872 , -0.54333335,  0.0655309 ,  0.1134268 ],
     [-0.15428883,  0.5699866 , -0.01254469,  0.9223561 ],
     [ 0.36428273, -0.6936733 ,  0.38850498,  0.30073535]],
    dtype=float32), array([0., 0., 0., 0.], dtype=float32)]
[array([[ 0.11082816, -0.55741405],
     [ 0.7298498 ,  0.5545671 ],
     [ 0.29023337,  0.0607245 ],
     [-0.971118  ,  0.74701834]], dtype=float32), array([0., 0.], dtype=float32)]
```



**get_weights()** 메서드를 사용하면 시냅스 가중치를 NumPy 어레이 형태로 얻을 수 있습니다.



### 이전글/다음글

이전글 : [5. AND 로직 연산 학습하기](https://codetorial.net/tensorflow/training_logic_and_operation.html)

다음글 : [7. 뉴런층의 출력 확인하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html)



# 7. 뉴런층의 출력 확인하기

![get_output_of_neuron_layers](.\Images\get_output_of_neuron_layers_0.png)



이전 페이지에서 각 뉴런층의 이름, 자료형, 활성화함수와 같은 다양한 정보를 확인하는 방법에 대해 알아보았습니다.

이 페이지에서는 **특정 입력 데이터에 대해 각 뉴런층이 출력하는 값을 확인하는 방법**에 대해 소개합니다.

■ **Table of Contents**

- [1) 훈련 데이터 준비하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#id2)
- [2) 뉴런층 만들기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#id3)
- [3) Neural Network 구성하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#neural-network)
- [4) Neural Network 컴파일하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#id4)
- [5) 은닉층의 출력값 확인하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#id5)
- [6) 출력층의 출력값 확인하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html#id7)

## 1) 훈련 데이터 준비하기

```
import tensorflow as tf
import numpy as np

tf.random.set_seed(0)


# 1. 훈련 데이터 준비하기
x_train = np.array([[1, 0, 0], [0, 1, 0], [0, 0, 1]])
y_train = np.array([[0], [1], [1]])
```



우선 [tf.random](https://www.tensorflow.org/api_docs/python/tf/random) 모듈의 **set_seed()** 함수를 사용해서 랜덤 시드를 설정했습니다.

예제에서 x_train, y_train은 각각 훈련에 사용할 입력값, 출력값입니다.

이 페이지에서는 훈련은 진행하지 않고 입력 데이터 (x_train)에 대해 각 뉴런층이 출력하는 값을 확인합니다.

## 2) 뉴런층 만들기

```
# 2. 뉴런층 만들기
input_layer = tf.keras.layers.InputLayer(input_shape=(3,))
hidden_layer = tf.keras.layers.Dense(units=4, activation='relu')
output_layer = tf.keras.layers.Dense(units=2, activation='softmax')
```



아래와 같은 간단한 신경망 모델을 구성하기 위한 뉴런층을 각각 만들었습니다.

길이 3을 갖는 벡터 입력을 받고, 은닉층은 네 개의 뉴런 노드를 가지며, 출력층은 두 개의 뉴런 노드를 가집니다.

은닉층의 활성화함수는 **‘relu’**, 출력층의 활성화함수는 **‘softmax’**로 지정했습니다.

![get_output_of_neuron_layers](.\Images\get_output_of_neuron_layers_1.png)

## 3) Neural Network 구성하기

```
# 3. 모델 구성하기
model = tf.keras.Sequential([
  input_layer,
  hidden_layer,
  output_layer
  ])
```



[tf.keras](https://www.tensorflow.org/api_docs/python/tf/keras) 모듈의 **Sequantial** 클래스는 Neural Network의 각 층을 순서대로 쌓을 수 있도록 합니다.

앞에서 만든 뉴런층을 순서대로 입력해서 신경망 모델을 구성합니다.

## 4) Neural Network 컴파일하기

```
# 4. 모델 컴파일하기
model.compile(loss='mse', optimizer='Adam')
```



손실 함수로 ‘mse’를, 옵티마이저로 ‘Adam’을 지정했습니다.

이 페이지의 예제에서는 훈련을 진행하지 않기 때문에 컴파일 과정은 생략할 수 있습니다.

## 5) 은닉층의 출력값 확인하기

```
# 5. 은닉층의 출력 확인하기
intermediate_layer_model = tf.keras.Model(inputs=model.input, outputs=model.layers[0].output)
intermediate_output = intermediate_layer_model(x_train)

print('======== Inputs ========')
print(x_train)

print('\n======== Weights of Hidden Layer ========')
print(hidden_layer.get_weights()[0])

print('\n======== Outputs of Hidden Layer ========')
print(intermediate_output)
```



```
======== Inputs ========
[[1 0 0]
[0 1 0]
[0 0 1]]

======== Weights of Hidden Layer ========
[[-0.3851872  -0.54333335  0.0655309   0.1134268 ]
[-0.15428883  0.5699866  -0.01254469  0.9223561 ]
[ 0.36428273 -0.6936733   0.38850498  0.30073535]]

======== Outputs of Hidden Layer ========
tf.Tensor(
[[0.         0.         0.0655309  0.1134268 ]
[0.         0.5699866  0.         0.9223561 ]
[0.36428273 0.         0.38850498 0.30073535]], shape=(3, 4), dtype=float32)
```



[tf.keras](https://www.tensorflow.org/api_docs/python/tf/keras) 모듈의 **Model** 클래스를 사용해서 새로운 모델 (intermediate_layer_model)을 하나 만들었습니다.

이 모델은 앞에서 구성한 전체 모델의 입력을 입력으로 하고,

첫번째 뉴런층 (hidden_layer)의 출력을 출력으로 하는 신경망 모델입니다.

이 모델에 훈련 데이터 (x_train)을 입력하면 첫번째 뉴런층의 출력을 반환합니다.

![get_output_of_neuron_layers](.\Images\get_output_of_neuron_layers_2.png)

첫번째 뉴런층, 즉 은닉층의 출력은 아래와 같이 계산됩니다.

![get_output_of_neuron_layers](.\Images\get_output_of_neuron_layers_3.png)

우선 입력 데이터 **[1, 0, 0]**에 대해 은닉층의 시냅스 가중치가 곱해집니다.

즉, 입력층 첫번째 노드의 입력 1에 시냅스 가중치 **[-0.3851872 -0.54333335 0.0655309 0.1134268 ]**가 곱해집니다.

다음으로 은닉층의 활성화함수인 **ReLU (Rectified Linear Unit)**가 적용되어서

0보다 작은 값은 0이되고, 0보다 큰 값은 그대로 출력값이 됩니다.

## 6) 출력층의 출력값 확인하기

출력층의 출력값은 전체 Neural Network 신경망의 출력값입니다.

```
# 6. 출력층의 출력 확인하기
pred = model.predict(x_train)

print('\n======== Outputs of Output Layer ========')
print(pred)
```



```
======== Outputs of Output Layer ========
[[0.45516005 0.5448399 ]
[0.18469977 0.8153002 ]
[0.4541021  0.5458979 ]]
```



전체 신경망의 출력값은 **Model** 클래스의 **predict()** 메서드를 사용해서 간단하게 얻을 수 있습니다.

세 개의 값을 갖는 세 개의 입력 데이터 벡터에 대해 두 개의 값을 갖는 벡터 세 개를 출력합니다.

### 전체 예제 코드

전체 코드는 아래와 같습니다.

```
import tensorflow as tf
import numpy as np

tf.random.set_seed(0)


# 1. 훈련 데이터 준비하기
x_train = np.array([[1, 0, 0], [0, 1, 0], [0, 0, 1]])
y_train = np.array([[0], [1], [1]])


# 2. 뉴런층 만들기
input_layer = tf.keras.layers.InputLayer(input_shape=(3,))
hidden_layer = tf.keras.layers.Dense(units=4, activation='relu')
output_layer = tf.keras.layers.Dense(units=2, activation='softmax')


# 3. 모델 구성하기
model = tf.keras.Sequential([
  input_layer,
  hidden_layer,
  output_layer
  ])

# 4. 모델 컴파일하기
model.compile(loss='mse', optimizer='Adam')


# 5. 은닉층의 출력 확인하기
intermediate_layer_model = tf.keras.Model(inputs=model.input, outputs=model.layers[0].output)
intermediate_output = intermediate_layer_model(x_train)

print('======== Inputs ========')
print(x_train)

print('\n======== Weights of Hidden Layer ========')
print(hidden_layer.get_weights()[0])

print('\n======== Outputs of Hidden Layer ========')
print(intermediate_output)

# 6. 출력층의 출력 확인하기
pred = model.predict(x_train)

print('\n======== Outputs of Output Layer ========')
print(pred)
```





### 이전글/다음글

이전글 : [6. 뉴런층의 속성 확인하기](https://codetorial.net/tensorflow/get_attribute_of_neuron_layers.html)

다음글 : [8. MNIST 손글씨 이미지 분류하기](https://codetorial.net/tensorflow/mnist_classification.html)



# 8. MNIST 손글씨 이미지 분류하기

![MNIST 이미지 데이터셋](.\Images\MNIST_sample.png)

MNIST 손글씨 이미지 데이터셋.

**MNIST 데이터셋**은 위 그림과 같이 0에서 9까지 10가지로 분류될 수 있는 손글씨 숫자 이미지 70,000개로 이루어져 있습니다.

각 이미지는 28×28 픽셀로 구성되고 각 픽셀은 아래와 같이 0~255 사이의 숫자 행렬로 표현됩니다.

```
[[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   3  18  18  18 126 136 175  26 166 255 247 127   0   0   0   0]
[  0   0   0   0   0   0   0   0  30  36  94 154 170 253 253 253 253 253 225 172 253 242 195  64   0   0   0   0]
[  0   0   0   0   0   0   0  49 238 253 253 253 253 253 253 253 253 251  93  82  82  56  39   0   0   0   0   0]
[  0   0   0   0   0   0   0  18 219 253 253 253 253 253 198 182 247 241   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0  80 156 107 253 253 205  11   0  43 154   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0  14   1 154 253  90   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0 139 253 190   2   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0  11 190 253  70   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0  35 241 225 160 108   1   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0  81 240 253 253 119  25   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0  45 186 253 253 150  27   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0  16  93 252 253 187   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0 249 253 249  64   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0  46 130 183 253 253 207   2   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0  39 148 229 253 253 253 250 182   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0  24 114 221 253 253 253 253 201  78   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0  23  66 213 253 253 253 253 198  81   2   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0  18 171 219 253 253 253 253 195  80   9   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0  55 172 226 253 253 253 253 244 133  11   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0 136 253 253 253 212 135 132  16   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]]
```



이러한 60,000개의 이미지는 인공 신경망의 **훈련 (Training)**에 사용되고, 10,000개의 이미지는 **테스트 (Test)**에 사용됩니다.

이번 페이지에서는 **Dense 층**들로 구성되는 완전 연결된 인공신경망 (Fully-Connected Neural Network)을 이용해서 **MNIST 데이터셋**을 분류해 보겠습니다.

## 예제

```
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

# 4. 모델 컴파일
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

# 5. 모델 훈련
model.fit(x_train, y_train, epochs=5)

# 6. 정확도 평가
test_loss, test_acc = model.evaluate(x_test, y_test)
print('테스트 정확도:', test_acc)
```



## 설명

### 0. tensorflow 불러오기

```
import tensorflow as tf
```



tensorflow 라이브러리를 불러옵니다.

### 1. MNIST 데이터셋 임포트

```
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()
```



tensorflow에서 직접 MNIST 손글씨 이미지 데이터셋을 불러와서 사용합니다.

load_data() 함수는 x_train, y_train, x_test, y_test 네 개의 NumPy 어레이를 반환합니다.

x_train, x_test는 28×28 픽셀의 각 손글씨 이미지 데이터이고, y_train, y_test는 분류에 사용되는 0~9 사이의 레이블 값을 갖습니다.

### 2. 데이터 전처리

```
x_train, x_test = x_train/255.0, x_test/255.0
```



0~255.0 사이의 값을 갖는 픽셀값들을 0~1.0 사이의 값을 갖도록 변환합니다.

### 3. 모델 구성

```
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])
```



tf.keras.models.Sequential()을 이용해서 인공신경망 모델을 구성합니다.

입력층 (Input layer)에서 Flatten()을 이용해서 28×28 픽셀의 값을 784개의 1차원 배열로 변환합니다.

다음 두 개의 뉴런 층 (Neuron layer)은 Dense()를 이용해서 완전 연결된 층 (Fully-connected layer)를 구성합니다.

각 층은 512개와 10개의 인공 뉴런 노드를 갖고 활성화 함수 (activation function)로는 각각 ReLU (tf.nn.relu)와 소프트맥스 (tf.nn.softmax)를 사용합니다.



### 4. 모델 컴파일

```
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
```



다음은 모델 컴파일 단계입니다. 학습 과정에서 손실 함수 (Loss function)를 줄이기 위해 사용되는 optimizer로는 Adam (Adaptive Momentum estimation)을 사용합니다.

손실 함수는 ‘sparse_categorical_crossentropy’를 지정하고, 평가 지표로는 정확도 (accuracy)를 사용합니다.

정확도는 테스트 이미지 중 올바르게 분류한 비율을 의미합니다.

### 5. 모델 훈련

```
model.fit(x_train, y_train, epochs=5)
```



model.fit() 메서드에 학습 데이터와, 레이블, 에포크를 순서대로 입력하면, 학습이 이루어집니다.

에포크(epoch)는 60,000개의 전체 학습 데이터를 몇 번 반복해서 학습할지를 의미합니다.

학습 과정은 아래와 같이 이루어지며, 학습 데이터에 대한 정확도가 출력됩니다.

```
Epoch 1/5
   32/60000 [..............................] - ETA: 9:14 - loss: 2.2976 - acc: 0.0938
  384/60000 [..............................] - ETA: 54s - loss: 1.6364 - acc: 0.5182
  832/60000 [..............................] - ETA: 28s - loss: 1.2119 - acc: 0.6478
 1280/60000 [..............................] - ETA: 20s - loss: 1.0081 - acc: 0.7055
 1728/60000 [..............................] - ETA: 17s - loss: 0.8794 - acc: 0.7373
 ...
 58368/60000 [============================>.] - ETA: 0s - loss: 0.2022 - acc: 0.9390
58784/60000 [============================>.] - ETA: 0s - loss: 0.2013 - acc: 0.9392
59232/60000 [============================>.] - ETA: 0s - loss: 0.2006 - acc: 0.9395
59744/60000 [============================>.] - ETA: 0s - loss: 0.1997 - acc: 0.9398
60000/60000 [==============================] - 8s 138us/step - loss: 0.1992 - acc: 0.9399

...

Epoch 5/5
   32/60000 [..............................] - ETA: 13s - loss: 0.0490 - acc: 0.9688
  480/60000 [..............................] - ETA: 7s - loss: 0.0152 - acc: 0.9938
  928/60000 [..............................] - ETA: 7s - loss: 0.0124 - acc: 0.9957
 1312/60000 [..............................] - ETA: 7s - loss: 0.0168 - acc: 0.9947
 1728/60000 [..............................] - ETA: 7s - loss: 0.0145 - acc: 0.9959
...
58656/60000 [============================>.] - ETA: 0s - loss: 0.0281 - acc: 0.9911
59072/60000 [============================>.] - ETA: 0s - loss: 0.0282 - acc: 0.9911
59520/60000 [============================>.] - ETA: 0s - loss: 0.0281 - acc: 0.9911
59968/60000 [============================>.] - ETA: 0s - loss: 0.0281 - acc: 0.9911
60000/60000 [==============================] - 7s 122us/step - loss: 0.0282 - acc: 0.9911
```



### 6. 정확도 평가

```
test_loss, test_acc = model.evaluate(x_test, y_test)
print('테스트 정확도:', test_acc)
```



model.evaluate()를 이용해서 10,000개의 테스트 샘플에 대해 손실 (loss)과 정확도 (accuracy)를 평가합니다.

결과는 아래와 같습니다.

```
테스트 정확도: 0.9802
```



간단한 인공신경망과 다섯 번 에포크의 학습만으로 98.02%의 정확도로 MNIST 이미지를 분류할 수 있음을 알 수 있습니다.

Matplotlib을 이용해서 에포크에 따른 정확도 (accuracy)와 손실 (loss) 값을 확인할 수 있습니다.

```
loss, accuracy = [], []
for i in range(10):
    model.fit(x_train, y_train, epochs=1)
    loss.append(model.evaluate(x_test, y_test)[0])
    accuracy.append(model.evaluate(x_test, y_test)[1])

print(accuracy)
```



1회의 에포크마다 model.evaluate()의 loss, accuracy 값을 저장합니다.

결과는 아래와 같습니다.

![_images/MNIST_accuracy_loss.png](.\Images\MNIST_accuracy_loss.png)

그림. 에포크에 따른 MNIST 패턴 인식 정확도와 손실.

이렇게 학습한 모델을 사용한 손글씨 인식 GUI 프로그램은 아래의 페이지를 참고하세요.

### 관련 페이지

- [PyQt5 프로그램 예제 - MNIST 손글씨 인식 프로그램](http://codetorial.net/pyqt5/examples/mnist_classifier.html)



### 이전글/다음글

이전글 : [7. 뉴런층의 출력 확인하기](https://codetorial.net/tensorflow/get_output_of_neuron_layers.html)

다음글 : [9. Fashion MNIST 이미지 분류하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html)



# 9. Fashion MNIST 이미지 분류하기

![Fashion MNIST 이미지 데이터셋](.\Images\fashion_MNIST_sample.png)

Fashion MNIST 이미지 데이터셋.

**Fashion MNIST 데이터셋**은 위 그림과 같이 운동화, 셔츠, 샌들과 같은 작은 이미지들의 모음이며, 기본 MNIST 데이터셋과 같이 열 가지로 분류될 수 있는 28×28 픽셀의 이미지 70,000개로 이루어져 있습니다.

이번 페이지에서는 **Dense 층**들로 구성되는 Fully-Connected Neural Network (완전 연결된 인공신경망)을 이용해서 **Fashion MNIST 데이터셋**을 분류해 보겠습니다.

순서는 아래와 같습니다.

- [Fashion MNIST 데이터셋 불러오기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id1)
- [Fashion MNIST 데이터셋 살펴보기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id3)
- [Fashion MNIST 데이터셋 전처리하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id4)
- [모델 구성하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id5)
- [모델 컴파일하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id7)
- [모델 훈련하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id8)
- [모델의 정확도 평가하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id9)
- [예측하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id10)
- [뉴런의 개수의 영향](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id11)
- [콜백 (Callback) 사용하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html#callback)
- [Fashion MNIST 이미지 인식 예제](https://codetorial.net/tensorflow/fashion_mnist_classification.html#id12)

## Fashion MNIST 데이터셋 불러오기

```
import tensorflow as tf

# 1. Fashion MNIST 데이터셋 불러오기
fashion_mnist = tf.keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()
```



**tf.keras.datasets** 모듈은 Neural Network의 훈련에 사용할 수 있는 여러 데이터셋을 포함합니다.

아래와 같은 7가지의 데이터셋이 있습니다.

- [boston_housing](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/boston_housing)
- [cifar10](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/cifar10)
- [cifar100](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/cifar100)
- [fashion_mnist](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/fashion_mnist)
- [imdb](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/imdb)
- [mnist](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/mnist)
- [reuters](https://www.tensorflow.org/api_docs/python/tf/keras/datasets/reuters)

예제에서는 **fashion_mnist** 데이터셋 모듈을 사용합니다.

**fashion_mnist** 모듈은 데이터셋을 반환하는 **load_data()** 함수를 포함하는데, **load_data()** 함수를 호출하면 NumPy 어레이의 튜플을 반환합니다.

train_images와 train_labels는 Neural Network 모델의 훈련 (training)에 사용되고,

test_images와 test_labels는 테스트 (test)에 사용됩니다.

## Fashion MNIST 데이터셋 살펴보기

train_images와 train_labels의 첫번째 요소를 각각 출력해 보겠습니다.

```
print(train_images[0])
print(train_labels[0])
```



```
[[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   1   0   0  13  73   0   0   1   4   0   0   0   0   1   1   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   3   0  36 136 127  62  54   0   0   0   1   3   4   0   0   3]
[  0   0   0   0   0   0   0   0   0   0   0   0   6   0 102 204 176 134 144 123  23   0   0   0   0  12  10   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0 155 236 207 178 107 156 161 109  64  23  77 130  72  15]
[  0   0   0   0   0   0   0   0   0   0   0   1   0  69 207 223 218 216 216 163 127 121 122 146 141  88 172  66]
[  0   0   0   0   0   0   0   0   0   1   1   1   0 200 232 232 233 229 223 223 215 213 164 127 123 196 229   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0 183 225 216 223 228 235 227 224 222 224 221 223 245 173   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0 193 228 218 213 198 180 212 210 211 213 223 220 243 202   0]
[  0   0   0   0   0   0   0   0   0   1   3   0  12 219 220 212 218 192 169 227 208 218 224 212 226 197 209  52]
[  0   0   0   0   0   0   0   0   0   0   6   0  99 244 222 220 218 203 198 221 215 213 222 220 245 119 167  56]
[  0   0   0   0   0   0   0   0   0   4   0   0  55 236 228 230 228 240 232 213 218 223 234 217 217 209  92   0]
[  0   0   1   4   6   7   2   0   0   0   0   0 237 226 217 223 222 219 222 221 216 223 229 215 218 255  77   0]
[  0   3   0   0   0   0   0   0   0  62 145 204 228 207 213 221 218 208 211 218 224 223 219 215 224 244 159   0]
[  0   0   0   0  18  44  82 107 189 228 220 222 217 226 200 205 211 230 224 234 176 188 250 248 233 238 215   0]
[  0  57 187 208 224 221 224 208 204 214 208 209 200 159 245 193 206 223 255 255 221 234 221 211 220 232 246   0]
[  3 202 228 224 221 211 211 214 205 205 205 220 240  80 150 255 229 221 188 154 191 210 204 209 222 228 225   0]
[ 98 233 198 210 222 229 229 234 249 220 194 215 217 241  65  73 106 117 168 219 221 215 217 223 223 224 229  29]
[ 75 204 212 204 193 205 211 225 216 185 197 206 198 213 240 195 227 245 239 223 218 212 209 222 220 221 230  67]
[ 48 203 183 194 213 197 185 190 194 192 202 214 219 221 220 236 225 216 199 206 186 181 177 172 181 205 206 115]
[  0 122 219 193 179 171 183 196 204 210 213 207 211 210 200 196 194 191 195 191 198 192 176 156 167 177 210  92]
[  0   0  74 189 212 191 175 172 175 181 185 188 189 188 193 198 204 209 210 210 211 188 188 194 192 216 170   0]
[  2   0   0   0  66 200 222 237 239 242 246 243 244 221 220 193 191 179 182 182 181 176 166 168  99  58   0   0]
[  0   0   0   0   0   0   0  40  61  44  72  41  35   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]
[  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0]]
9
```



train_images는 0에서 255 사이의 값을 갖는 28x28 크기의 NumPy 어레이를 갖는 어레이이고,

train_labels는 0에서 9까지의 정수 값을 갖는 어레이입니다.

0에서 9까지의 정수 값은 이미지(옷)의 클래스를 나타내는 레이블입니다. 각각의 레이블과 클래스는 아래와 같습니다.

0 : T-shirt/top

1 : Trouser

2 : Pullover

3 : Dress

4 : Coat

5 : Sandal

6 : Shirt

7 : Sneaker

8 : Bag

9 : Ankel boot

첫번째 train_images를 [Matplotlib](https://codetorial.net/matplotlib/index.html)을 이용해서 이미지로 나타내보면,

아래와 같은 이미지가 출력됩니다.

![Fashion MNIST 출력하기](.\Images\fashion_MNIST_01.png)

traing_images 샘플 출력하기.

```
print(train_images.shape)
print(train_labels.shape)
print(test_images.shape)
print(test_labels.shape)
```



```
(60000, 28, 28)
(60000,)
(10000, 28, 28)
(10000,)
```



각 어레이의 형태 (shape)를 출력해보면,

train_images와 test_images는 각각 (28x28)의 형태를 갖는 60000개, 10000개 이미지의 어레이,

train_labels와 test_labels는 각각 0에서 9 사이의 정수 60000개, 10000개의 어레이임을 알 수 있습니다.

## Fashion MNIST 데이터셋 전처리하기

```
# 2. 데이터 전처리
train_images, test_images = train_images / 255.0, test_images / 255.0
```



0에서 255 사이의 값을 갖는 훈련/테스트 데이터들을 0.0~1.0 사이의 값을 갖도록 변환합니다.

## 모델 구성하기

```
# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dense(10, activation='softmax')
])
```



[MNIST 인식 예제](https://codetorial.net/tensorflow/mnist_classification.html)와 같이 **Sequential()** 클래스를 이용해서 신경망 모델을 순서대로 구성합니다.

[tf.keras.layers.Flatten](https://www.tensorflow.org/api_docs/python/tf/keras/layers/Flatten) 클래스는 아래 그림과 같이 입력 데이터를 1차원으로 변환합니다.

![Flatten_Input](.\Images\fashion_MNIST_02.png)

## 모델 컴파일하기

**compile()** 메서드를 이용해서 모델을 훈련하는데 사용할 옵티마이저, 손실 함수, 지표를 설정합니다.

```
# 4. 모델 컴파일
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
```



**손실 함수 (loss function)**는 훈련 과정에서 모델의 오차를 측정하는데 사용됩니다.

**옵티마이저 (optimizer)**는 데이터와 손실 함수를 바탕으로 모델 (의 웨이트와 바이어스)을 업데이트하는 방식을 말합니다.

**지표 (metrics)**는 훈련과 테스트 단계를 평가하기 위해 사용됩니다.

‘accuracy’로 설정하면, 이미지를 올바르게 분류한 비율로 모델을 평가합니다.

## 모델 훈련하기

```
# 5. 모델 훈련
model.fit(train_images, train_labels, epochs=5)
```



**fit()** 메서드에 훈련에 사용할 이미지 데이터와 레이블을 입력해줍니다.

**에포크 (epochs)**는 60000개의 전체 이미지를 몇 번 학습할지 설정합니다.

아래와 같은 훈련 과정이 출력됩니다.

```
Epoch 1/5
   32/60000 [..............................] - ETA: 6:13 - loss: 2.4037 - accuracy: 0.0625
  352/60000 [..............................] - ETA: 42s - loss: 1.4676 - accuracy: 0.4830
  640/60000 [..............................] - ETA: 28s - loss: 1.2536 - accuracy: 0.5375
 1024/60000 [..............................] - ETA: 20s - loss: 1.0536 - accuracy: 0.6172
 1440/60000 [..............................] - ETA: 16s - loss: 0.9693 - accuracy: 0.6465
 1888/60000 [..............................] - ETA: 14s - loss: 0.9230 - accuracy: 0.6600
 2272/60000 [>.............................] - ETA: 13s - loss: 0.8733 - accuracy: 0.6787
 2720/60000 [>.............................] - ETA: 11s - loss: 0.8461 - accuracy: 0.6893
 3168/60000 [>.............................] - ETA: 11s - loss: 0.8126 - accuracy: 0.7027
 3584/60000 [>.............................] - ETA: 10s - loss: 0.7877 - accuracy: 0.7115
 3936/60000 [>.............................] - ETA: 10s - loss: 0.7695 - accuracy: 0.7200
 4320/60000 [=>............................] - ETA: 9s - loss: 0.7593 - accuracy: 0.7257
 4736/60000 [=>............................] - ETA: 9s - loss: 0.7494 - accuracy: 0.7314
 5056/60000 [=>............................] - ETA: 9s - loss: 0.7350 - accuracy: 0.7366
 5376/60000 [=>............................] - ETA: 9s - loss: 0.7242 - accuracy: 0.7398
 5760/60000 [=>............................] - ETA: 9s - loss: 0.7114 - accuracy: 0.7446
 6176/60000 [==>...........................] - ETA: 9s - loss: 0.7018 - accuracy: 0.7494
 6592/60000 [==>...........................] - ETA: 8s - loss: 0.6903 - accuracy: 0.7544
 6976/60000 [==>...........................] - ETA: 8s - loss: 0.6847 - accuracy: 0.7556
 7296/60000 [==>...........................] - ETA: 8s - loss: 0.6785 - accuracy: 0.7571
 7712/60000 [==>...........................] - ETA: 8s - loss: 0.6773 - accuracy: 0.7584
 8160/60000 [===>..........................] - ETA: 8s - loss: 0.6706 - accuracy: 0.7614
 8672/60000 [===>..........................] - ETA: 8s - loss: 0.6641 - accuracy: 0.7641
 9184/60000 [===>..........................] - ETA: 7s - loss: 0.6584 - accuracy: 0.7658
 9696/60000 [===>..........................] - ETA: 7s - loss: 0.6510 - accuracy: 0.7683
10176/60000 [====>.........................] - ETA: 7s - loss: 0.6465 - accuracy: 0.7710
10592/60000 [====>.........................] - ETA: 7s - loss: 0.6400 - accuracy: 0.7723
11040/60000 [====>.........................] - ETA: 7s - loss: 0.6378 - accuracy: 0.7739
```



## 모델의 정확도 평가하기

**evaluate()** 메서드를 이용해서 **손실 (loss)**과 **정확도 (accuracy)**를 각각 얻을 수 있습니다.

```
# 6. 정확도 평가하기
loss, accuracy = model.evaluate(test_images, test_labels)
print(loss, accuracy)
```



```
0.35106261382102966 0.8713
```



5회 epoch의 학습을 통해 10000개의 테스트 이미지를 87%의 정확도로 분류할 수 있음을 의미합니다.

## 예측하기

**predict()** 메서드를 사용하면 모델이 각 이미지의 클래스를 예측하는 결과를 확인할 수 있습니다.

```
# 7. 예측하기
predictions = model.predict(test_images)
print(predictions[0])
print(np.argmax(predictions[0]))
```



```
[3.3880130e-07 1.6222993e-08 1.0470003e-07 1.2482052e-07 2.4020338e-08 1.4695596e-04 9.3243568e-07 1.7916959e-02 1.7079768e-06 9.8193282e-01]
9
```



predictions는 각 test_images에 대한 신경망의 출력값을 나타내는 어레이입니다.

첫번째 예측 predictions[0]을 출력해보면 10개의 값을 갖는 어레이이며, 입력 이미지 데이터가 열 개의 숫자 중 어떤 숫자일 확률을 의미합니다.

[np.argmax()](https://codetorial.net/numpy/functions/numpy_argmax.html) 함수를 이용해서 가장 높은 값을 갖는 인덱스를 확인해보면 9를 출력합니다.

즉, 학습된 신경망은 이 이미지가 ankle boot라고 예측합니다.

## 뉴런의 개수의 영향

가운데 뉴런층의 뉴런 노드의 개수를 조절하면 훈련에 어떤 영향을 미치는지 알아봅니다.

아래 그림과 같이 뉴런 노드의 개수를 128, 512, 1024로 변화시키면서 세 가지 경우에 대해

훈련 과정의 손실과 정확도를 확인해보겠습니다.

![Effect of Neuron Nodes](.\Images\fashion_MNIST_03.png)

```
# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(128, activation='relu'),      ## CASE 1
    # tf.keras.layers.Dense(512, activation='relu'),    ## CASE 2
    # tf.keras.layers.Dense(1024, activation='relu'),   ## CASE 3
    tf.keras.layers.Dense(10, activation='softmax')
])
```



```
## CASE 1
Epoch 1/5
1875/1875 [==============================] - 3s 2ms/step - loss: 0.4990 - accuracy: 0.8238
Epoch 2/5
1875/1875 [==============================] - 3s 2ms/step - loss: 0.3752 - accuracy: 0.8650
Epoch 3/5
1875/1875 [==============================] - 3s 2ms/step - loss: 0.3379 - accuracy: 0.8763
Epoch 4/5
1875/1875 [==============================] - 3s 2ms/step - loss: 0.3107 - accuracy: 0.8853
Epoch 5/5
1875/1875 [==============================] - 4s 2ms/step - loss: 0.2938 - accuracy: 0.8930
313/313 [==============================] - 0s 1ms/step - loss: 0.3664 - accuracy: 0.8695
0.3664253056049347 0.8694999814033508

## CASE 2
Epoch 1/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.4760 - accuracy: 0.8302
Epoch 2/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.3585 - accuracy: 0.8672
Epoch 3/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.3239 - accuracy: 0.8812
Epoch 4/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.2972 - accuracy: 0.8896
Epoch 5/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.2797 - accuracy: 0.8965
313/313 [==============================] - 1s 2ms/step - loss: 0.3516 - accuracy: 0.8775
0.3515831232070923 0.8774999976158142

## CASE 3
Epoch 1/5
1875/1875 [==============================] - 12s 6ms/step - loss: 0.4747 - accuracy: 0.8292
Epoch 2/5
1875/1875 [==============================] - 12s 7ms/step - loss: 0.3587 - accuracy: 0.8689
Epoch 3/5
1875/1875 [==============================] - 12s 7ms/step - loss: 0.3187 - accuracy: 0.8818
Epoch 4/5
1875/1875 [==============================] - 13s 7ms/step - loss: 0.2984 - accuracy: 0.8892
Epoch 5/5
1875/1875 [==============================] - 12s 7ms/step - loss: 0.2797 - accuracy: 0.8959
313/313 [==============================] - 1s 3ms/step - loss: 0.3369 - accuracy: 0.8822
0.3369075059890747 0.8822000026702881
```



에포크에 따른 손실과 정확도를 그래프로 표현하면 아래와 같습니다.

![Effect of Neuron Nodes](.\Images\fashion_MNIST_04.png)

뉴런 노드의 개수가 증가하면 훈련 과정에서 **손실 값이 감소하고 테스트 정확도는 증가하는 경향**이 있습니다.

하지만 계산과 최적화를 필요로 하는 파라미터의 숫자가 증가하기 때문에 **훈련에 걸리는 시간은 증가**합니다.

512개와 1024개에서 손실과 정확도의 증가가 크지 않은 이유는 Fashion MNIST 분류 문제가 비교적 간단한 문제이기 때문입니다.

따라서 문제에 맞게 적절한 개수의 뉴런을 사용하면서 짧은 훈련 시간 동안 높은 정확도를 얻는 것이 좋습니다.

## 콜백 (Callback) 사용하기

**tf.keras.callbacks** 모듈의 **Callback** 클래스를 사용하면,

훈련 중에 손실 값이 특정 기준 미만이 되었을 때 훈련을 중단할 수 있습니다.

```
import tensorflow as tf

class myCallback(tf.keras.callbacks.Callback):
  def on_epoch_end(self, epoch, logs={}):
      if logs.get('loss') < 0.3:
          print('\n훈련을 중지합니다.')
          self.model.stop_training = True

callbacks = myCallback()

# 1. Fashion MNIST 데이터셋 임포트
fashion_mnist = tf.keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

# 2. 데이터 전처리
train_images, test_images = train_images / 255.0, test_images / 255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(512, activation='relu'),
  tf.keras.layers.Dense(10, activation='softmax')
])

# 4. 모델 컴파일
model.compile(optimizer='adam',
          loss='sparse_categorical_crossentropy',
          metrics=['accuracy'])

# 5. 모델 훈련
model.fit(train_images, train_labels, epochs=5, callbacks=[callbacks])
```



```
Epoch 1/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.4721 - accuracy: 0.8308
Epoch 2/5
1875/1875 [==============================] - 7s 4ms/step - loss: 0.3585 - accuracy: 0.8671
Epoch 3/5
1875/1875 [==============================] - 7s 3ms/step - loss: 0.3221 - accuracy: 0.8815
Epoch 4/5
1861/1875 [============================>.] - ETA: 0s - loss: 0.2990 - accuracy: 0.8909
훈련을 중지합니다.
1875/1875 [==============================] - 7s 4ms/step - loss: 0.2989 - accuracy: 0.8909
<tensorflow.python.keras.callbacks.History at 0x7fdbd2df17f0>
```



우선 myCallback 클래스에 조건식을 사용해서 훈련을 중단할 조건을 지정하고,

**fit()** 메서드에 **callbacks 파라미터**를 사용해서 이 클래스가 호출되도록 합니다.

**on_epoch_end**는 **각 에포크 (epoch)의 끝에 호출되는 메서드**입니다.

한 에포크가 끝나고 손실 값 (loss)이 0.3 미만이라면 훈련을 중단하도록 해주었습니다.

예제에서 총 5회 에포크의 훈련을 설정했음에도 4회 에포크에서 훈련이 중단되었습니다.

에포크 (epoch)에 따른 손실 값과 정확도는 아래와 같습니다.

![Using Callback](.\Images\fashion_MNIST_05.png)

## Fashion MNIST 이미지 인식 예제

전체 코드는 아래와 같습니다.

```
import tensorflow as tf
import numpy as np

# 1. Fashion MNIST 데이터셋 임포트
fashion_mnist = tf.keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()


# 2. 데이터 전처리
train_images, test_images = train_images / 255.0, test_images / 255.0


# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dense(10, activation='softmax')
])


# 4. 모델 컴파일
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])


# 5. 모델 훈련
model.fit(train_images, train_labels, epochs=5)


# 6. 정확도 평가하기
loss, accuracy = model.evaluate(test_images, test_labels)
print(loss, accuracy)


# 7. 예측하기
predictions = model.predict(test_images)
print(predictions[0])
print(np.argmax(predictions[0]))
```





### 이전글/다음글

이전글 : [8. MNIST 손글씨 이미지 분류하기](https://codetorial.net/tensorflow/mnist_classification.html)

다음글 : [10. 합성곱 신경망 사용하기](https://codetorial.net/tensorflow/convolutional_neural_network.html)



# 10. 합성곱 신경망 사용하기

![Typical Convolutional Neural Network](.\Images\convolutional_neural_network_00.png)

**합성곱 신경망 (Convolutional neural network, CNN)**은 **시각적 이미지 분석 및 분류에 가장 일반적으로 사용되는 인공신경망**입니다.

이번 페이지에서는 합성곱 신경망을 사용해서 MNIST 이미지 데이터셋을 분류해보겠습니다.

순서는 아래와 같습니다.

- [1. MNIST 데이터셋 불러오기](https://codetorial.net/tensorflow/convolutional_neural_network.html#mnist)
- [2. 데이터 전처리하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#id2)
- [3. 합성곱 신경망 구성하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#id3)
- [4. Dense 층 추가하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#dense)
- [5. 모델 컴파일하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#id4)
- [6. 훈련하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#id5)
- [7. 모델 평가하기](https://codetorial.net/tensorflow/convolutional_neural_network.html#id6)

## 1. MNIST 데이터셋 불러오기

```
import tensorflow as tf
from tensorflow.keras import datasets, layers, models

# 1. MNIST 데이터셋 불러오기
(train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()
```



앞의 예제에서와 같이 **load_data()**를 이용해서 keras.datasets에서 mnist 데이터셋을 불러옵니다.

![MNIST Dataset](.\Images\MNIST_sample.png)

## 2. 데이터 전처리하기

```
# 2. 데이터 전처리하기
train_images = train_images.reshape((60000, 28, 28, 1))
test_images = test_images.reshape((10000, 28, 28, 1))
train_images, test_images = train_images / 255.0, test_images / 255.0
```



mnist 데이터셋의 자료형을 출력해보면 **numpy.ndarray** 클래스임을 알 수 있습니다.

NumPy의 [reshape()](https://codetorial.net/numpy/functions/numpy_reshape.html) 함수를 이용해서 적절한 형태로 변환하고,

0~255 사이의 값을 갖는 데이터를 0.0~1.0 사이의 값을 갖도록, 255.0으로 나눠줍니다.

## 3. 합성곱 신경망 구성하기

```
# 3. 합성곱 신경망 구성하기
model = models.Sequential()
model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))

model.summary()
```



tf.keras.models 모듈의 **Sequential** 클래스를 사용해서 인공신경망의 각 층을 순서대로 쌓을 수 있습니다.

**add()** 메서드를 이용해서 합성곱 층 [Conv2D](https://www.tensorflow.org/api_docs/python/tf/keras/layers/Conv2D)와 Max pooling 층 [MaxPooling2D](https://www.tensorflow.org/api_docs/python/tf/keras/layers/MaxPool2D)를 반복해서 구성합니다.

첫번째 Conv2D 층의 첫번째 인자 32는 **filters** 값입니다.

합성곱 연산에서 사용되는 **필터 (filter)는 이미지에서 특징 (feature)을 분리해내는 기능**을 합니다.

**filters**의 값은 합성곱에 사용되는 필터의 종류 (개수)이며, 출력 공간의 차원 (깊이)을 결정합니다.

두번째 인자 (3, 3)은 **kernel_size** 값입니다.

**kernel_size**는 합성곱에 사용되는 필터 (=커널)의 크기입니다.

아래와 같은 3×3 크기의 필터가 사용되며, 합성곱 연산이 이루어지고 나면 이미지는 (28, 28) 크기에서 (26, 26)이 됩니다.

![Examples of 3×3 Filter (Kernel)](.\Images\convolutional_neural_network_01.png)

활성화함수 (Activation function)는 ‘relu’로 지정하고,

입력 데이터의 형태 (**input_shape**)는 아래와 같이 MNIST 숫자 이미지 하나의 형태에 해당하는 (28, 28, 1)로 설정합니다.

![Shape of a MNIST data image](.\Images\convolutional_neural_network_02.png)

**풀링 (Pooling)**은 합성곱에 의해 얻어진 **Feature map으로부터 값을 샘플링해서 정보를 압축하는 과정**을 의미합니다.

**맥스풀링 (Max-pooling)**은 특정 영역에서 가장 큰 값을 샘플링하는 풀링 방식이며,

예제에서는 풀링 필터의 크기를 2×2 영역으로 설정했습니다.

**strides**는 풀링 필터를 이동시키는 간격을 의미합니다.

**strides**를 지정해주지 않으면 (None), 풀링 필터의 크기와 같아서 영역의 오버랩 없이 풀링이 이루어집니다.

따라서 풀링이 이루어지고 나면, (26, 26) 크기의 이미지는 (13, 13) 크기가 됩니다.

![Max pooling and Stride](.\Images\convolutional_neural_network_03.png)

**summary()** 메서드를 이용해서 지금까지 구성한 신경망에 대한 정보를 출력할 수 있습니다.

```
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
conv2d (Conv2D)            (None, 26, 26, 32)        320
_________________________________________________________________
max_pooling2d (MaxPooling2 (None, 13, 13, 32)        0
_________________________________________________________________
conv2d_1 (Conv2D)            (None, 11, 11, 64)        18496
_________________________________________________________________
max_pooling2d_1 (MaxPooling2 (None, 5, 5, 64)          0
_________________________________________________________________
conv2d_2 (Conv2D)            (None, 3, 3, 64)          36928
_________________________________________________________________
max_pooling2d_2 (MaxPooling2 (None, 1, 1, 64)          0
=================================================================
Total params: 55,744
Trainable params: 55,744
Non-trainable params: 0
_________________________________________________________________
```



지금까지 구성한 신경망의 구조를 그림으로 나타내면 아래와 같습니다.

이러한 합성곱, 풀링 층은 **특성 추출 (Feature Extraction)**을 담당하며, 전체 합성곱 신경망의 앞부분을 구성합니다.

![Convolutional Neural Network Architecture Diagram](.\Images\convolutional_neural_network_04.png)

## 4. Dense 층 추가하기

```
# 4. Dense 층 추가하기
model.add(layers.Flatten())
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(10, activation='softmax'))

model.summary()
```



합성곱 층에 이어 뒷부분에는 분류를 담당하는 **Dense 층 (Fully-connected layer)**을 세 개 추가합니다.

마지막 출력층 노드의 개수는 열 개로 하고 ‘softmax’ 활성화함수를 사용합니다.

**summary()** 메서드를 이용해서 지금까지 구성한 신경망에 대한 정보를 다시 출력해 보겠습니다.

```
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
conv2d (Conv2D)           (None, 26, 26, 32)        320
_________________________________________________________________
max_pooling2d (MaxPooling (None, 13, 13, 32)        0
_________________________________________________________________
conv2d_1 (Conv2D)           (None, 11, 11, 64)        18496
_________________________________________________________________
max_pooling2d_1 (MaxPooling (None, 5, 5, 64)          0
_________________________________________________________________
conv2d_2 (Conv2D)           (None, 3, 3, 64)          36928
_________________________________________________________________
max_pooling2d_2 (MaxPooling (None, 1, 1, 64)          0
_________________________________________________________________
flatten (Flatten)          (None, 64)                0
_________________________________________________________________
dense (Dense)              (None, 64)                4160
_________________________________________________________________
dense_1 (Dense)              (None, 10)                650
=================================================================
Total params: 60,554
Trainable params: 60,554
Non-trainable params: 0
_________________________________________________________________
```



flatten, dense, dense_1 이라는 세 개의 층이 추가되고,

훈련 가능한 파라미터의 수가 55,744개에서 60,554개로 증가했습니다.

지금까지 구성한 전체 신경망의 구조를 그림으로 나타내면 아래와 같습니다.

여러 층의 합성곱, 풀링층과 Dense 층이 반복적으로 구성되었습니다.

![Convolutional Neural Network Architecture Diagram (Full)](.\Images\convolutional_neural_network_05.png)

아래의 코드는 앞에서 설명한 합성곱 신경망과 Dense 층을 구성하는 또 다른 방식을 보여줍니다.

```
model = tf.keras.models.Sequential([
  tf.keras.layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)),
  tf.keras.layers.MaxPooling2D(2, 2),
  tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2, 2),
  tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2, 2),
  tf.keras.layers.Flatten(),
  tf.keras.layers.Dense(64, activation='relu'),
  tf.keras.layers.Dense(10, activation='softmax')
])

model.summary()
```



## 5. 모델 컴파일하기

```
# 5. 모델 컴파일하기
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
```



앞의 예제와 마찬가지로 **compile()** 메서드를 이용해서 옵티마이저, 손실 함수, 지표를 각각 설정해줍니다.

## 6. 훈련하기

```
# 6. 훈련하기
model.fit(train_images, train_labels, epochs=5)
```



**fit()** 메서드에 미리 준비한 **train_images**와 **train_labels**, 5회의 **epochs**를 입력하고 훈련을 진행합니다.

아래와 같이 훈련의 진행 상황이 출력됩니다.

```
Epoch 1/5
   32/60000 [..............................] - ETA: 9:37 - loss: 2.2930 - accuracy: 0.1875
  128/60000 [..............................] - ETA: 2:51 - loss: 2.2807 - accuracy: 0.1328
  224/60000 [..............................] - ETA: 1:54 - loss: 2.2566 - accuracy: 0.1964
  320/60000 [..............................] - ETA: 1:30 - loss: 2.2268 - accuracy: 0.2688
  384/60000 [..............................] - ETA: 1:24 - loss: 2.2049 - accuracy: 0.3021
  480/60000 [..............................] - ETA: 1:13 - loss: 2.1633 - accuracy: 0.3083
  576/60000 [..............................] - ETA: 1:06 - loss: 2.1073 - accuracy: 0.3281
  672/60000 [..............................] - ETA: 1:01 - loss: 2.0411 - accuracy: 0.3571
  768/60000 [..............................] - ETA: 1:00 - loss: 1.9643 - accuracy: 0.3906
  864/60000 [..............................] - ETA: 58s - loss: 1.8911 - accuracy: 0.4201
  960/60000 [..............................] - ETA: 56s - loss: 1.8120 - accuracy: 0.4469
 1056/60000 [..............................] - ETA: 54s - loss: 1.7266 - accuracy: 0.4716
 1120/60000 [..............................] - ETA: 53s - loss: 1.6847 - accuracy: 0.4875
 1216/60000 [..............................] - ETA: 52s - loss: 1.6315 - accuracy: 0.5000
 1280/60000 [..............................] - ETA: 52s - loss: 1.5871 - accuracy: 0.5109
 1376/60000 [..............................] - ETA: 51s - loss: 1.5230 - accuracy: 0.5305
 1440/60000 [..............................] - ETA: 51s - loss: 1.4813 - accuracy: 0.5424
 1536/60000 [..............................] - ETA: 50s - loss: 1.4202 - accuracy: 0.5632
 1664/60000 [..............................] - ETA: 49s - loss: 1.3670 - accuracy: 0.5829
 1760/60000 [..............................] - ETA: 48s - loss: 1.3205 - accuracy: 0.5966
```



## 7. 모델 평가하기

```
# 7. 모델 평가하기
loss, acc = model.evaluate(test_images, test_labels, verbose=2)
```



```
10000/10000 - 2s - loss: 0.0248 - accuracy: 0.9923
```



**evaluate()** 메서드에 **test_images**와 **test_labels**를 입력함으로써 모델을 평가할 수 있습니다.

손실 함수 값이 **0.0248**, 그리고 정확도가 **0.9923**입니다.

합성곱 신경망을 사용해서 이미지 분류를 수행할 때, 단순한 Dense 층으로만 이루어진 신경망으로 학습한 경우의 정확도인 **0.9802**에 비해

더 높은 정확도를 얻을 수 있음을 알 수 있습니다. ([MNIST 손글씨 인식](https://codetorial.net/tensorflow/mnist_classification.html) 참고)

전체 코드는 아래와 같습니다.

### CNN을 이용한 MNIST 이미지 인식 예제

```
import tensorflow as tf
from tensorflow.keras import datasets, layers, models

# 1. MNIST 데이터셋 불러오기
(train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()

# 2. 데이터 전처리하기
train_images = train_images.reshape((60000, 28, 28, 1))
test_images = test_images.reshape((10000, 28, 28, 1))
train_images, test_images = train_images / 255.0, test_images / 255.0

# 3. 합성곱 신경망 구성하기
model = models.Sequential()
model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))

model.summary()

# 4. Dense 층 추가하기
model.add(layers.Flatten())
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(10, activation='softmax'))

model.summary()

# 5. 모델 컴파일하기
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

# 6. 훈련하기
model.fit(train_images, train_labels, epochs=5)

# 7. 모델 평가하기
loss, acc = model.evaluate(test_images, test_labels, verbose=2)
```



### 관련 페이지

- [NumPy 기초](https://codetorial.net/numpy/basics.html)
- [numpy.reshape](https://codetorial.net/numpy/functions/numpy_reshape.html)



### 이전글/다음글

이전글 : [9. Fashion MNIST 이미지 분류하기](https://codetorial.net/tensorflow/fashion_mnist_classification.html)

다음글 : [11. 말과 사람 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html)



# 11. 말과 사람 이미지 분류하기

![classifying_the_horse_and_human_00](.\Images\classifying_the_horse_and_human_00.png)

이제 MNIST와 Fashion MNIST보다 더 현실적인 분류 문제인 **“말과 사람 이미지 분류하기”** 문제를 다룹니다.

순서는 아래와 같습니다.

- [데이터셋 준비하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id2)
- [데이터셋 살펴보기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id3)
- [모델 구성하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id6)
- [모델 컴파일하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id7)
- [이미지 데이터 전처리하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id8)
- [모델 훈련하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html#id9)

## 데이터셋 준비하기

우선 아래의 주소에 접속해서 말과 사람 사진 데이터셋 파일을 다운로드하고, 압축을 풀어줍니다.

URL: https://storage.googleapis.com/laurencemoroney-blog.appspot.com/horse-or-human.zip

지정한 경로에 아래와 같은 폴더가 생성되었다면 준비가 된 것입니다.

![classifying_the_horse_and_human_01](.\Images\classifying_the_horse_and_human_01.png)

## 데이터셋 살펴보기

### 경로와 파일명

```
import os

# horses/humans 데이터셋 경로 지정
train_horse_dir = './tmp/horse-or-human/horses'
train_human_dir = './tmp/horse-or-human/humans'

# horses 파일 이름 리스트
train_horse_names = os.listdir(train_horse_dir)
print(train_horse_names[:10])

# humans 파일 이름 리스트
train_human_names = os.listdir(train_human_dir)
print(train_human_names[:10])

# horses/humans 총 이미지 파일 개수
print('total training horse images:', len(os.listdir(train_horse_dir)))
print('total training human images:', len(os.listdir(train_human_dir)))
```



```
['horse01-0.png', 'horse01-1.png', 'horse01-2.png', 'horse01-3.png', 'horse01-4.png', 'horse01-5.png', 'horse01-6.png', 'horse01-7.png', 'horse01-8.png', 'horse01-9.png']
['human01-00.png', 'human01-01.png', 'human01-02.png', 'human01-03.png', 'human01-04.png', 'human01-05.png', 'human01-06.png', 'human01-07.png', 'human01-08.png', 'human01-09.png']
total training horse images: 500
total training human images: 527
```



말/사람 이미지 데이터셋이 포함된 경로를 각각 train_horse_dir, train_human_dir에 지정합니다.

**os.listdir()**을 이용해서 경로에 포함된 파일 이름을 리스트 형태로 불러올 수 있습니다.

이 리스트의 길이를 각각 확인해보면 말 이미지가 500개, 사람 이미지가 527개 있음을 알 수 있습니다.

### 이미지 확인하기

```
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

nrows = 4
ncols = 4

pic_index = 0

fig = plt.gcf()
fig.set_size_inches(ncols * 4, nrows * 4)

pic_index += 8
next_horse_pix = [os.path.join(train_horse_dir, fname) for fname in train_horse_names[pic_index-8:pic_index]]
next_human_pix = [os.path.join(train_human_dir, fname) for fname in train_human_names[pic_index-8:pic_index]]

for i, img_path in enumerate(next_horse_pix+next_human_pix):
  sp = plt.subplot(nrows, ncols, i + 1)
  sp.axis('Off')

  img = mpimg.imread(img_path)
  plt.imshow(img)

plt.show()
```



Matplotlib을 이용해서 말과 사람의 이미지를 각각 8개씩 띄워보겠습니다.

결과는 아래와 같습니다.

![classifying_the_horse_and_human_02](.\Images\classifying_the_horse_and_human_02.png)

## 모델 구성하기

이제 이미지 분류와 훈련을 위한 **합성곱 신경망 (Convolutional Neural Network)**을 구성합니다.

```
import tensorflow as tf

model = tf.keras.models.Sequential([
    # The first convolution
    tf.keras.layers.Conv2D(16, (3, 3), activation='relu', input_shape=(300, 300, 3)),
    tf.keras.layers.MaxPool2D(2, 2),
    # The second convolution
    tf.keras.layers.Conv2D(32, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    # The third convolution
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    # The fourth convolution
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    # The fifth convolution
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    # Flatten
    tf.keras.layers.Flatten(),
    # 512 Neuron (Hidden layer)
    tf.keras.layers.Dense(512, activation='relu'),
    # 1 Output neuron
    tf.keras.layers.Dense(1, activation='sigmoid')
])

model.summary()
```



```
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
conv2d (Conv2D)              (None, 298, 298, 16)      448
_________________________________________________________________
max_pooling2d (MaxPooling2D) (None, 149, 149, 16)      0
_________________________________________________________________
conv2d_1 (Conv2D)            (None, 147, 147, 32)      4640
_________________________________________________________________
max_pooling2d_1 (MaxPooling2 (None, 73, 73, 32)        0
_________________________________________________________________
conv2d_2 (Conv2D)            (None, 71, 71, 64)        18496
_________________________________________________________________
max_pooling2d_2 (MaxPooling2 (None, 35, 35, 64)        0
_________________________________________________________________
conv2d_3 (Conv2D)            (None, 33, 33, 64)        36928
_________________________________________________________________
max_pooling2d_3 (MaxPooling2 (None, 16, 16, 64)        0
_________________________________________________________________
conv2d_4 (Conv2D)            (None, 14, 14, 64)        36928
_________________________________________________________________
max_pooling2d_4 (MaxPooling2 (None, 7, 7, 64)          0
_________________________________________________________________
flatten (Flatten)            (None, 3136)              0
_________________________________________________________________
dense (Dense)                (None, 512)               1606144
_________________________________________________________________
dense_1 (Dense)              (None, 1)                 513
=================================================================
Total params: 1,704,097
Trainable params: 1,704,097
Non-trainable params: 0
_________________________________________________________________
```



다섯 단계의 합성곱 뉴런층과 두 단계의 Dense 층으로 전체 합성곱 신경망을 구성했습니다.

**model.summary()**를 통해서 모델의 각 **뉴런층**과 **Output Shape**을 얻을 수 있습니다.

## 모델 컴파일하기

모델 컴파일 과정에서는 앞에서 구성한 합성곱 신경망의 **손실 함수 (loss function)**와 **옵티마이저 (optimizer)**를 설정합니다.

```
from tensorflow.keras.optimizers import RMSprop

model.compile(loss='binary_crossentropy',
            optimizer=RMSprop(lr=0.001),
            metrics=['accuracy'])
```



지금까지 다루었던 예제와 다르게 손실 함수로 ‘binary_crossentropy’를 사용했습니다.

출력층의 활성화함수로 ‘sigmoid’를 사용했고, 이는 0과 1 두 가지로 분류되는 ‘binary’ 분류 문제에 적합하기 때문입니다.

또한, 옵티마이저로는 [RMSprop](https://en.wikipedia.org/wiki/Stochastic_gradient_descent#RMSProp)을 사용했습니다.

**RMSprop (Root Mean Square Propagation) Algorithm**은 훈련 과정 중에 학습률을 적절하게 변화시킵니다.

## 이미지 데이터 전처리하기

훈련을 진행하기 전, **tf.keras.preprocessing.image** 모듈의 **ImageDataGenerator** 클래스를 이용해서 데이터 전처리를 진행합니다.

```
from tensorflow.keras.preprocessing.image import ImageDataGenerator

train_datagen = ImageDataGenerator(rescale=1/255)

train_generator = train_datagen.flow_from_directory(
  './tmp/horse-or-human',
  target_size=(300, 300),
  batch_size=128,
  class_mode='binary'
)
```



```
Found 1027 images belonging to 2 classes.
```



**rescale** 파라미터는 이미지 데이터에 곱해질 값을 설정합니다.

**ImageDataGenerator** 클래스의 **flow_from_directory** 메서드는 이미지 데이터셋의 경로를 받아서, 데이터의 배치를 만들어냅니다.

## 모델 훈련하기

**fit()** 메서드에 train_generator 객체를 입력하고 훈련을 시작합니다.

```
history = model.fit(
  train_generator,
  steps_per_epoch=8,
  epochs=15,
  verbose=1
)
```



```
Train for 8 steps
Epoch 1/15
1/8 [==>...........................] - ETA: 35s - loss: 0.7054 - accuracy: 0.4766
2/8 [======>.......................] - ETA: 27s - loss: 0.8511 - accuracy: 0.4961
3/8 [==========>...................] - ETA: 21s - loss: 0.8232 - accuracy: 0.4844
4/8 [==============>...............] - ETA: 16s - loss: 0.7903 - accuracy: 0.4883
```



훈련이 시작됩니다.



### 이전글/다음글

이전글 : [10. 합성곱 신경망 사용하기](https://codetorial.net/tensorflow/convolutional_neural_network.html)

다음글 : [12. 고양이와 개 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html)



# 12. 고양이와 개 이미지 분류하기

![classifying_the_cats_and_dogs_00](.\Images\classifying_the_cats_and_dogs_00.png)

이전의 예제에서 다루었던 MNIST, Fashion MNIST 데이터셋은 이미지 패턴이 비교적 단순하며 흑백의 색상을 가지고, 이미지가 모두 같은 크기를 가지기 때문에 상대적으로 쉬운 문제로 알려져 있습니다.

하지만 현실의 이미지 분류 문제는 이와 달리 더 복잡한 패턴을 가지거나 다양한 색상을 가지고, 또한 이미지의 크기가 달라질 수 있습니다.

이번 페이지에서는 잘 알려진 이미지 분류 문제인 [Kaggle Dogs Vs. Cats 데이터셋](https://www.kaggle.com/c/dogs-vs-cats)을 살펴보고, Neural Network를 구성하고 훈련시켜서, 정확도를 확인하는 과정에 대해 순서대로 소개합니다.

또한 이번에는 **Google Colab (Colaboratory)**을 사용해서 웹브라우저 상에서 특별한 환경 구성없이 머신러닝 코드를 작성해 보겠습니다. **Google Colab**에 대해서는 [Google Colab 소개 페이지](https://colab.research.google.com/notebooks/intro.ipynb)를 참고하세요.

순서는 아래와 같습니다.

- [Kaggle Dogs Vs. Cats 데이터셋 준비하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id2)
- [Kaggle Dogs Vs. Cats 데이터셋 살펴보기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id6)
- [모델 구성하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id9)
- [모델 컴파일하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id10)
- [이미지 데이터 전처리하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id12)
- [모델 훈련하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id13)
- [정확도와 손실 확인하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id14)
- [테스트 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html#id16)

## Kaggle Dogs Vs. Cats 데이터셋 준비하기

[Kaggle (캐글)](https://www.kaggle.com/)은 데이터 사이언스와 머신러닝 경진대회를 위한 플랫폼입니다.

머신러닝 학습과 해결, 경쟁을 위한 다양한 데이터셋을 제공하며, 2017년 3월 Google에 인수되었습니다.

[Dogs Vs. Cats 데이터셋](https://www.kaggle.com/c/dogs-vs-cats)은 Kaggle에서 다루었던 Challenge 중 하나였습니다.

현재 이 Challenge는 종료되었고, 순위는 아래와 같습니다.

![classifying_the_cats_and_dogs_01](.\Images\classifying_the_cats_and_dogs_01.png)

### 다운로드하기

```
!wget --no-check-certificate \
https://storage.googleapis.com/mledu-datasets/cats_and_dogs_filtered.zip \
-O /tmp/cats_and_dogs_filtered.zip
```



우선 Colab 코드셀에 위의 명령어를 입력해서 데이터셋을 다운로드합니다.

아래 그림과 같이 페이지 왼쪽의 목차 탭을 열어서 tmp 폴더에

cats_and_dogs_filtered.zip 파일이 다운로드되어 있는지 확인합니다.

cats_and_dogs_filtered 데이터셋은 25,000개의 이미지를 포함하는 원본 Dogs Vs. Cats 데이터셋에서 약 3,000개의 이미지를 추출한 간소화된 버전의 데이터셋입니다.

![classifying_the_cats_and_dogs_02](.\Images\classifying_the_cats_and_dogs_02.png)

### 압축풀기

```
import os
import zipfile

local_zip = '/tmp/cats_and_dogs_filtered.zip'

zip_ref = zipfile.ZipFile(local_zip, 'r')

zip_ref.extractall('/tmp')
zip_ref.close()
```



**os** 라이브러리를 통해 파일시스템에 접근할 수 있습니다.

**zipfile** 라이브러리의 ZipFile 클래스로 ZIP 파일을 연 후에

**extractall()** 메서드를 이용해서 tmp 폴더에 압축을 풉니다.

아래 그림과 같이 cats_and_dogs_filtered 폴더가 만들어졌다면 준비가 된 것입니다.

![classifying_the_cats_and_dogs_03](.\Images\classifying_the_cats_and_dogs_03.png)

### 경로 지정하기

```
# 기본 경로
base_dir = '/tmp/cats_and_dogs_filtered'

train_dir = os.path.join(base_dir, 'train')
validation_dir = os.path.join(base_dir, 'validation')

# 훈련에 사용되는 고양이/개 이미지 경로
train_cats_dir = os.path.join(train_dir, 'cats')
train_dogs_dir = os.path.join(train_dir, 'dogs')
print(train_cats_dir)
print(train_dogs_dir)

# 테스트에 사용되는 고양이/개 이미지 경로
validation_cats_dir = os.path.join(validation_dir, 'cats')
validation_dogs_dir = os.path.join(validation_dir, 'dogs')
print(validation_cats_dir)
print(validation_dogs_dir)
```



```
/tmp/cats_and_dogs_filtered/train/cats
/tmp/cats_and_dogs_filtered/train/dogs
/tmp/cats_and_dogs_filtered/validation/cats
/tmp/cats_and_dogs_filtered/validation/dogs
```



기본 경로와 훈련에 사용되는 고양이/개 이미지의 경로를 각각 지정해줍니다.

## Kaggle Dogs Vs. Cats 데이터셋 살펴보기

### 파일 이름과 개수

```
train_cat_fnames = os.listdir( train_cats_dir )
train_dog_fnames = os.listdir( train_dogs_dir )

print(train_cat_fnames[:5])
print(train_dog_fnames[:5])
```



```
['cat.702.jpg', 'cat.589.jpg', 'cat.788.jpg', 'cat.951.jpg', 'cat.265.jpg']
['dog.776.jpg', 'dog.573.jpg', 'dog.575.jpg', 'dog.405.jpg', 'dog.439.jpg']
```



**os.listdir()** 메서드는 경로 내에 있는 파일의 이름을 리스트의 형태로 반환합니다.

각각 다섯 개씩 출력했습니다.

```
print('Total training cat images :', len(os.listdir(train_cats_dir)))
print('Total training dog images :', len(os.listdir(train_dogs_dir)))

print('Total validation cat images :', len(os.listdir(validation_cats_dir)))
print('Total validation dog images :', len(os.listdir(validation_dogs_dir)))
```



```
Total training cat images : 1000
Total training dog images : 1000
Total validation cat images : 500
Total validation dog images : 500
```



각 경로에 있는 파일명 리스트의 길이를 통해 파일의 개수를 확인합니다.

### 이미지 확인하기

```
%matplotlib inline

import matplotlib.image as mpimg
import matplotlib.pyplot as plt

nrows, ncols = 4, 4
pic_index = 0

fig = plt.gcf()
fig.set_size_inches(ncols*3, nrows*3)

pic_index+=8

next_cat_pix = [os.path.join(train_cats_dir, fname)
                for fname in train_cat_fnames[ pic_index-8:pic_index]]

next_dog_pix = [os.path.join(train_dogs_dir, fname)
                for fname in train_dog_fnames[ pic_index-8:pic_index]]

for i, img_path in enumerate(next_cat_pix+next_dog_pix):
  sp = plt.subplot(nrows, ncols, i + 1)
  sp.axis('Off')

  img = mpimg.imread(img_path)
  plt.imshow(img)

plt.show()
```



[Matplotlib 라이브러리](https://codetorial.net/matplotlib/index.html)를 이용해서 이미지를 출력해보면, 데이터셋에 아래와 같은 이미지들이 포함되어 있음을 알 수 있습니다.

![classifying_the_cats_and_dogs_04](.\Images\classifying_the_cats_and_dogs_04.png)

## 모델 구성하기

```
import tensorflow as tf


model = tf.keras.models.Sequential([
  tf.keras.layers.Conv2D(16, (3,3), activation='relu', input_shape=(150, 150, 3)),
  tf.keras.layers.MaxPooling2D(2,2),
  tf.keras.layers.Conv2D(32, (3,3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2,2),
  tf.keras.layers.Conv2D(64, (3,3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2,2),
  tf.keras.layers.Flatten(),
  tf.keras.layers.Dense(512, activation='relu'),
  tf.keras.layers.Dense(1, activation='sigmoid')
])

model.summary()
```



이제 TensorFlow를 이용해서 합성곱 신경망의 모델을 구성합니다.

**summary()** 메서드를 이용해서 신경망의 구조를 확인할 수 있습니다.

![classifying_the_cats_and_dogs_05](.\Images\classifying_the_cats_and_dogs_05.png)

## 모델 컴파일하기

```
from tensorflow.keras.optimizers import RMSprop

model.compile(optimizer=RMSprop(lr=0.001),
            loss='binary_crossentropy',
            metrics = ['accuracy'])
```



모델 컴파일 단계에서는 **compile()** 메서드를 이용해서 손실 함수 (loss function)와 옵티마이저 (optimizer)를 지정합니다.

[말과 사람 이미지 분류하기 예제](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html)에서와 같이 손실 함수로 ‘binary_crossentropy’를 사용했습니다.

출력층의 활성화함수로 ‘sigmoid’를 사용했고, 이는 0과 1 두 가지로 분류되는 ‘binary’ 분류 문제에 적합하기 때문입니다.

또한, 옵티마이저로는 [RMSprop](https://en.wikipedia.org/wiki/Stochastic_gradient_descent#RMSProp)을 사용했습니다.

**RMSprop (Root Mean Square Propagation) Algorithm**은 훈련 과정 중에 학습률을 적절하게 변화시킵니다.

## 이미지 데이터 전처리하기

훈련을 진행하기 전, **tf.keras.preprocessing.image** 모듈의 **ImageDataGenerator** 클래스를 이용해서 데이터 전처리를 진행합니다.

```
from tensorflow.keras.preprocessing.image import ImageDataGenerator


train_datagen = ImageDataGenerator( rescale = 1.0/255. )
test_datagen  = ImageDataGenerator( rescale = 1.0/255. )

train_generator = train_datagen.flow_from_directory(train_dir,
                                                  batch_size=20,
                                                  class_mode='binary',
                                                  target_size=(150, 150))
validation_generator =  test_datagen.flow_from_directory(validation_dir,
                                                       batch_size=20,
                                                       class_mode  = 'binary',
                                                       target_size = (150, 150))
```



```
Found 2000 images belonging to 2 classes.
Found 1000 images belonging to 2 classes.
```



우선 **ImageDataGenerator** 객체의 rescale 파라미터를 이용해서 모든 데이터를 255로 나누어준 다음,

**flow_from_directory()** 메서드를 이용해서 훈련과 테스트에 사용될 이미지 데이터를 만듭니다.

첫번째 인자로 이미지들이 위치한 경로를 입력하고, **batch_size**, **class_mode**를 지정합니다.

**target_size**에 맞춰서 이미지의 크기가 조절됩니다.

## 모델 훈련하기

**fit()** 메서드는 앞에서 구성한 Neural Network 모델을 훈련합니다.

```
history = model.fit(train_generator,
                    validation_data=validation_generator,
                    steps_per_epoch=100,
                    epochs=100,
                    validation_steps=50,
                    verbose=2)
```



훈련과 테스트를 위한 데이터셋인 **train_generator, validation_generator**를 입력합니다.

**epochs**는 데이터셋을 한 번 훈련하는 과정을 의미합니다.

**steps_per_epoch**는 한 번의 에포크 (epoch)에서 훈련에 사용할 배치 (batch)의 개수를 지정합니다.

**validation_steps**는 한 번의 에포크가 끝날 때, 테스트에 사용되는 배치 (batch)의 개수를 지정합니다.

아래와 같은 훈련 과정을 확인할 수 있습니다.

![classifying_the_cats_and_dogs_06](.\Images\classifying_the_cats_and_dogs_06.png)

## 정확도와 손실 확인하기

```
import matplotlib.pyplot as plt

acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(acc))

plt.plot(epochs, acc, 'bo', label='Training accuracy')
plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
plt.title('Training and validation accuracy')
plt.legend()

plt.figure()

plt.plot(epochs, loss, 'go', label='Training Loss')
plt.plot(epochs, val_loss, 'g', label='Validation Loss')
plt.title('Training and validation loss')
plt.legend()

plt.show()
```



[Matplotlib 라이브러리](https://codetorial.net/matplotlib/index.html)를 이용해서 훈련 과정에서 에포크에 따른 정확도와 손실을 출력합니다.

아래와 같은 이미지가 출력됩니다.

20회 에포크에서 훈련 정확도는 1.0에 근접한 반면, 테스트의 정확도는 100회 훈련이 끝나도 0.7 수준에 머물고 있습니다.

이러한 현상을 **과적합 (Overfitting)**이라고 합니다.

![classifying_the_cats_and_dogs_07](.\Images\classifying_the_cats_and_dogs_07.png)

![classifying_the_cats_and_dogs_08](.\Images\classifying_the_cats_and_dogs_08.png)

## 테스트 이미지 분류하기

아래의 테스트 이미지를 사용해서 훈련된 모델이 개와 고양이 이미지를 분류할 수 있는지 확인해 보겠습니다.

![classifying_the_cats_and_dogs_09](.\Images\classifying_the_cats_and_dogs_09.png)

test_image.png

```
import numpy as np
from google.colab import files
from keras.preprocessing import image

uploaded=files.upload()

for fn in uploaded.keys():

  path='/content/' + fn
  img=image.load_img(path, target_size=(150, 150))

  x=image.img_to_array(img)
  x=np.expand_dims(x, axis=0)
  images = np.vstack([x])

  classes = model.predict(images, batch_size=10)

  print(classes[0])

  if classes[0]>0:
    print(fn + " is a dog")
  else:
    print(fn + " is a cat")
```



이 코드는 하나 이상의 이미지를 업로드하고, 훈련된 모델을 사용해서 개/고양이 분류 결과를 출력합니다.

결과는 아래와 같습니다.

![classifying_the_cats_and_dogs_10](.\Images\classifying_the_cats_and_dogs_10.png)



### 이전글/다음글

이전글 : [11. 말과 사람 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_horse_and_human.html)

다음글 : [13. 이미지 어그멘테이션의 효과](https://codetorial.net/tensorflow/image_augmentation.html)



# 13. 이미지 어그멘테이션의 효과

적은 수의 이미지 데이터를 가지고 Neural Network를 훈련할 때 **과적합 (overfitting)** 문제가 발생할 수 있습니다. **과적합**이란 훈련에 사용되는 이미지에 과도하게 학습되어서 새로운 이미지를 제대로 인식하지 못하는 현상입니다.

또한 훈련에 사용되는 이미지가 적기 때문에 훈련 과정에서 보지 못한 유형의 이미지를 인식하지 못할 수 있습니다. 예를 들어, 아래와 같이 훈련 과정에서 왼쪽의 신발들을 사용한 후에 오른쪽의 하이힐을 인식하면 신발로 분류하지 못할 수 있습니다.

![Image_Augmentation](.\Images\image_augmentation_01.png)

또한, 훈련 과정에서 왼쪽의 고양이 이미지들을 학습한 후에 오른쪽의 누워있는 고양이의 이미지를 인식하지 못할 수 있습니다.

![Image_Augmentation](.\Images\image_augmentation_02.png)

**이미지 어그멘테이션 (Image augmentation)**은 이미지 인식에 있어서 **과적합** 문제를 해결하기 위한 매우 간단하면서 강력한 이미지 전처리 기법입니다.

이미지 어그멘테이션 기법을 사용하면 훈련 과정에서 즉석해서 이미지를 회전 (rotation)시키는 등의 변화를 적용합니다.

이 페이지에서는 이미지 어그멘테이션 기법을 이용해서 데이터를 전처리함으로써

적은 수의 이미지 데이터셋을 이용해서 이미지 인식의 정확도를 높이고 과적합 문제를 개선하는 과정에 대해 소개합니다.

순서는 아래와 같습니다.

- [이미지 데이터 전처리하기](https://codetorial.net/tensorflow/image_augmentation.html#id2)
- [정확도와 손실 확인하기](https://codetorial.net/tensorflow/image_augmentation.html#id5)

## 이미지 데이터 전처리하기

이미지 어그멘테이션을 위해서 이전의 예제에서 다루었던 **tf.keras.preprocessing.image** 모듈의 **ImageDataGenerator** 클래스를 수정합니다.

**ImageDataGenerator** 클래스를 이용한 이미지 데이터 전처리에 대해서는 먼저 [고양이와 개 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html) 페이지를 참고하세요.

```
from tensorflow.keras.preprocessing.image import ImageDataGenerator


# train_datagen = ImageDataGenerator(rescale = 1.0/255.)
train_datagen = ImageDataGenerator(rescale = 1.0/255.,
                                 rotation_range=40,
                                 width_shift_range=0.2,
                                 height_shift_range=0.2,
                                 shear_range=0.2,
                                 zoom_range=0.2,
                                 horizontal_flip=True,
                                 fill_mode='nearest')
test_datagen  = ImageDataGenerator(rescale = 1.0/255.)
```



**ImageDataGenerator** 클래스의 **rescale** 파라미터의 값을 1.0/255로 지정하면 모든 값을 255로 나누게 됩니다.

**rotation_range**는 이미지를 임의로 회전시키는 각도를 지정합니다. 0~180 사이의 값을 입력합니다.

**width_shift**, **height_shift**는 이미지를 임의로 수직 또는 수평 방향으로 이동시키는 범위를 지정합니다. 이미지의 너비 또는 높이에 대한 비율로 지정합니다.

**shear_range**는 전단변환 (shearing transformation)을 위한 파라미터입니다. 이미지를 어긋나 보이도록 변환합니다.

**zoom_range**는 이미지를 임의로 확대하는 정도를 지정합니다.

**horizontal_flip**은 이미지를 임의로 (수평 방향으로) 뒤집을지 여부를 결정합니다.

**fill_mode**는 회전 또는 이동 변환 후 빈 픽셀을 채우는 방식을 지정합니다. 디폴트는 ‘nearest’이며, {‘constant’, ‘nearest’, ‘reflect’, ‘wrap’} 중 하나의 값으로 지정합니다.

더 자세한 내용은 [링크](https://keras.io/api/preprocessing/image/)를 참고하세요.

## 정확도와 손실 확인하기

```
import matplotlib.pyplot as plt

acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(acc))

plt.plot(epochs, acc, 'bo', label='Training accuracy')
plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
plt.title('Training and validation accuracy')
plt.legend()

plt.figure()

plt.plot(epochs, loss, 'go', label='Training Loss')
plt.plot(epochs, val_loss, 'g', label='Validation Loss')
plt.title('Training and validation loss')
plt.legend()

plt.show()
```



앞의 예제에서와 마찬가지로 [Matplotlib 라이브러리](https://codetorial.net/matplotlib/index.html)를 이용해서 훈련 과정에서의 정확도와 손실을 확인해보겠습니다.

아래와 같은 결과가 출력됩니다.

![classifying_the_cats_and_dogs_07_1](.\Images\classifying_the_cats_and_dogs_07_1.png)

![classifying_the_cats_and_dogs_08_1](.\Images\classifying_the_cats_and_dogs_08_1.png)

**이미지 어그멘테이션 (Image augmentation)**을 사용하지 않았던 결과와 비교해보면,

훈련 데이터와 테스트 데이터에 대한 정확도, 손실 값이 일치하는 경향을 보이고, **과적합 (Overfitting) 현상**이 두드러지게 감소했음을 알 수 있습니다.

또한 **이미지 어그멘테이션** 과정에서 이미지 변환의 **임의성 (Randomness)**이 반영되어서 최종 정확도 (약 0.80)는 이미지 어그멘테이션을 사용하지 않은 경우의 훈련 데이터에 대한 정확도 (약 1.0)보다는 감소했음을 알 수 있습니다.

**이미지 어그멘테이션** 기법을 적용하면 메모리 저장 공간을 차지하지 않고, **적은 수의 이미지 데이터로 매우 다양한 훈련 데이터를 사용**하는 효과가 있습니다. 하지만 훈련 과정에서 **즉시 이미지 프로세싱이 이루어지기 때문에 훈련 시간이 증가**하게 됩니다.



### 이전글/다음글

이전글 : [12. 고양이와 개 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html)

다음글 : [14. 전이 학습 활용하기](https://codetorial.net/tensorflow/transfer_learning.html)



# 14. 전이 학습 활용하기

**전이 학습 (Transfer learning)**은 사전 훈련된 모델을 그대로 불러와서 활용하는 학습 방식입니다.

**전이 학습**을 사용하면 **직접 다루기 힘든 대량의 데이터셋으로 사전 훈련된 특성들을 손쉽게 활용**할 수 있습니다.

이 페이지에서는 ImageNet 데이터셋을 잘 분류하도록 사전 훈련된 **InceptionV3** 모델의 가중치를 불러와서 개와 고양이 이미지를 분류하는데 활용하는 과정에 대해 소개합니다.

순서는 아래와 같습니다.

- [사전 훈련된 가중치 다운로드하기](https://codetorial.net/tensorflow/transfer_learning.html#id2)
- [사전 훈련된 가중치 불러오기](https://codetorial.net/tensorflow/transfer_learning.html#id3)
- [마지막 층 출력 확인하기](https://codetorial.net/tensorflow/transfer_learning.html#id7)
- [모델 구성/컴파일하기](https://codetorial.net/tensorflow/transfer_learning.html#id8)
- [데이터셋 다운로드하기](https://codetorial.net/tensorflow/transfer_learning.html#id9)
- [데이터 어그멘테이션](https://codetorial.net/tensorflow/transfer_learning.html#id11)
- [모델 훈련하기](https://codetorial.net/tensorflow/transfer_learning.html#id13)
- [훈련 결과 확인하기](https://codetorial.net/tensorflow/transfer_learning.html#id14)

## 사전 훈련된 가중치 다운로드하기

**Google Colab**환경에서 아래의 명령어를 실행하면, tmp 폴더에 미리 훈련된 가중치가 다운로드됩니다.

주소에 직접 접속해서 로컬 환경에 다운로드 받을 수도 있습니다.

```
!wget --no-check-certificate \
  https://storage.googleapis.com/mledu-datasets/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5 \
  -O /tmp/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5
```



```
--2020-08-25 13:54:33--  https://storage.googleapis.com/mledu-datasets/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5
Resolving storage.googleapis.com (storage.googleapis.com)... 172.217.204.128, 172.217.203.128, 74.125.31.128, ...
Connecting to storage.googleapis.com (storage.googleapis.com)|172.217.204.128|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 87910968 (84M) [application/x-hdf]
Saving to: ‘/tmp/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5’

/tmp/inception_v3_w 100%[===================>]  83.84M  79.3MB/s    in 1.1s

2020-08-25 13:54:34 (79.3 MB/s) - ‘/tmp/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5’ saved [87910968/87910968]
```



**Google Colab** 환경에서 아래와 같이 tmp 폴더에 파일이 생겼다면 **전이 학습 (Transfer learning)**을 활용할 준비가 되었습니다.

![transfer_learning_01](.\Images\transfer_learning_01.png)

## 사전 훈련된 가중치 불러오기

```
import os
from tensorflow.keras import layers
from tensorflow.keras import Model
from tensorflow.keras.applications.inception_v3 import InceptionV3

local_weights_file = '/tmp/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5'

pre_trained_model = InceptionV3(input_shape=(150, 150, 3),
                              include_top=False,
                              weights=None)

pre_trained_model.load_weights(local_weights_file)

for layer in pre_trained_model.layers:
  layer.trainable = False

pre_trained_model.summary()
```



```
Model: "inception_v3"
__________________________________________________________________________________________________
Layer (type)                    Output Shape         Param #     Connected to
==================================================================================================
input_1 (InputLayer)            [(None, 150, 150, 3) 0
__________________________________________________________________________________________________
conv2d (Conv2D)                 (None, 74, 74, 32)   864         input_1[0][0]
__________________________________________________________________________________________________
batch_normalization (BatchNorma (None, 74, 74, 32)   96          conv2d[0][0]
__________________________________________________________________________________________________
activation (Activation)         (None, 74, 74, 32)   0           batch_normalization[0][0]
__________________________________________________________________________________________________
conv2d_1 (Conv2D)               (None, 72, 72, 32)   9216        activation[0][0]
__________________________________________________________________________________________________
batch_normalization_1 (BatchNor (None, 72, 72, 32)   96          conv2d_1[0][0]
__________________________________________________________________________________________________
activation_1 (Activation)       (None, 72, 72, 32)   0           batch_normalization_1[0][0]
__________________________________________________________________________________________________
conv2d_2 (Conv2D)               (None, 72, 72, 64)   18432       activation_1[0][0]
__________________________________________________________________________________________________
batch_normalization_2 (BatchNor (None, 72, 72, 64)   192         conv2d_2[0][0]
__________________________________________________________________________________________________
activation_2 (Activation)       (None, 72, 72, 64)   0           batch_normalization_2[0][0]
__________________________________________________________________________________________________
max_pooling2d (MaxPooling2D)    (None, 35, 35, 64)   0           activation_2[0][0]
__________________________________________________________________________________________________
conv2d_3 (Conv2D)               (None, 35, 35, 80)   5120        max_pooling2d[0][0]
__________________________________________________________________________________________________
batch_normalization_3 (BatchNor (None, 35, 35, 80)   240         conv2d_3[0][0]
__________________________________________________________________________________________________
activation_3 (Activation)       (None, 35, 35, 80)   0           batch_normalization_3[0][0]
__________________________________________________________________________________________________
conv2d_4 (Conv2D)               (None, 33, 33, 192)  138240      activation_3[0][0]
__________________________________________________________________________________________________
batch_normalization_4 (BatchNor (None, 33, 33, 192)  576         conv2d_4[0][0]
__________________________________________________________________________________________________
activation_4 (Activation)       (None, 33, 33, 192)  0           batch_normalization_4[0][0]
__________________________________________________________________________________________________
max_pooling2d_1 (MaxPooling2D)  (None, 16, 16, 192)  0           activation_4[0][0]
__________________________________________________________________________________________________
conv2d_8 (Conv2D)               (None, 16, 16, 64)   12288       max_pooling2d_1[0][0]
__________________________________________________________________________________________________
batch_normalization_8 (BatchNor (None, 16, 16, 64)   192         conv2d_8[0][0]
__________________________________________________________________________________________________
activation_8 (Activation)       (None, 16, 16, 64)   0           batch_normalization_8[0][0]
__________________________________________________________________________________________________
conv2d_6 (Conv2D)               (None, 16, 16, 48)   9216        max_pooling2d_1[0][0]
__________________________________________________________________________________________________
conv2d_9 (Conv2D)               (None, 16, 16, 96)   55296       activation_8[0][0]
__________________________________________________________________________________________________
batch_normalization_6 (BatchNor (None, 16, 16, 48)   144         conv2d_6[0][0]
__________________________________________________________________________________________________
batch_normalization_9 (BatchNor (None, 16, 16, 96)   288         conv2d_9[0][0]
__________________________________________________________________________________________________

...
...

__________________________________________________________________________________________________
activation_87 (Activation)      (None, 3, 3, 384)    0           batch_normalization_87[0][0]
__________________________________________________________________________________________________
activation_88 (Activation)      (None, 3, 3, 384)    0           batch_normalization_88[0][0]
__________________________________________________________________________________________________
activation_91 (Activation)      (None, 3, 3, 384)    0           batch_normalization_91[0][0]
__________________________________________________________________________________________________
activation_92 (Activation)      (None, 3, 3, 384)    0           batch_normalization_92[0][0]
__________________________________________________________________________________________________
batch_normalization_93 (BatchNo (None, 3, 3, 192)    576         conv2d_93[0][0]
__________________________________________________________________________________________________
activation_85 (Activation)      (None, 3, 3, 320)    0           batch_normalization_85[0][0]
__________________________________________________________________________________________________
mixed9_1 (Concatenate)          (None, 3, 3, 768)    0           activation_87[0][0]
                                                               activation_88[0][0]
__________________________________________________________________________________________________
concatenate_1 (Concatenate)     (None, 3, 3, 768)    0           activation_91[0][0]
                                                               activation_92[0][0]
__________________________________________________________________________________________________
activation_93 (Activation)      (None, 3, 3, 192)    0           batch_normalization_93[0][0]
__________________________________________________________________________________________________
mixed10 (Concatenate)           (None, 3, 3, 2048)   0           activation_85[0][0]
                                                               mixed9_1[0][0]
                                                               concatenate_1[0][0]
                                                               activation_93[0][0]
==================================================================================================
Total params: 21,802,784
Trainable params: 0
Non-trainable params: 21,802,784
__________________________________________________________________________________________________
```



[tensorflow.keras.applications](https://www.tensorflow.org/api_docs/python/tf/keras/applications) 모듈은 사전 훈련된 가중치를 갖고 있는 다양한 신경망 구조를 포함합니다.

예제에서는 이 **tensorflow.keras.applications** 모듈의 **inception_v3** 모듈로부터 불러온 InceptionV3 함수를 사용해서 **InceptionV3** 모델을 구성합니다. InceptionV3 모델에 대해서는 [링크1](https://en.wikipedia.org/wiki/Inceptionv3), [링크2](https://arxiv.org/pdf/1512.00567.pdf)를 참고하세요.

**InceptionV3** 모델은 상단 (출력층과 가까운 신경망의 끝부분)에 완전 연결된 뉴런층을 포함합니다.

**include_top**는 이러한 완전 연결된 뉴런층의 포함 여부를 설정합니다.

**weights**는 적용할 가중치를 지정합니다.

- **None**: 임의로 초기화 (random initialization)된 가중치를 적용합니다.
- **imagenet**: ImageNet에 대해 사전 훈련된 가중치를 적용합니다. (Default)

그 다음으로 **load_weights()** 메서드를 사용해서 미리 다운로드한 가중치를 **InceptionV3** 모델에 적용합니다.

**layer.trainable**는 뉴런층의 가중치의 훈련 가능 여부를 설정합니다.

**summary()** 메서드로 구성한 모델의 구조를 출력했습니다.

매우 많은 수의, 그리고 다양한 뉴런층이 포함되어 있음을 알 수 있습니다.

**InceptionV3** 모델의 구조를 그림으로 나타내면 아래와 같습니다.

![transfer_learning_02](.\Images\transfer_learning_02.png)

**InceptionV3** 모델의 구조 ([이미지 참고](https://cloud.google.com/tpu/docs/inception-v3-advanced?hl=ko)).

## 마지막 층 출력 확인하기

```
last_layer = pre_trained_model.get_layer('mixed7')
print('last layer output shape: ', last_layer.output.shape)
last_output = last_layer.output
```



```
last layer output shape:  (None, 7, 7, 768)
```



**TensorFlow**로 구성한 모델의 모든 뉴런층에는 이름이 있습니다.

앞에서 **summary()** 메서드로 모든 뉴런층의 이름을 출력했습니다.

‘mixed7’라는 이름을 갖는 층을 가져와서 사전 훈련된 신경망 모델의 마지막 층으로 지정했습니다.

## 모델 구성/컴파일하기

```
from tensorflow.keras.optimizers import RMSprop

x = layers.Flatten()(last_output)
x = layers.Dense(1024, activation='relu')(x)
x = layers.Dense(1, activation='sigmoid')(x)

model = Model(pre_trained_model.input, x)

model.compile(optimizer=RMSprop(lr=0.0001),
            loss='binary_crossentropy',
            metrics=['accuracy'])
```



**Flatten()**는 앞에서 지정한 **InceptionV3** 모델의 마지막 층의 출력을 1차원으로 변환합니다.

**Dense()**는 완전 연결된 뉴런층을 추가합니다.

두 개의 뉴런층을 추가하고 활성화 함수를 각각 ‘relu’와 ‘sigmoid’로 지정했습니다.

**Model()** 클래스에 **InceptionV3** 모델의 입력과 새롭게 구성한 뉴런층을 입력함으로써 새로운 모델을 만들었습니다.

**compile()**을 사용해서 구성한 모델을 컴파일합니다.

## 데이터셋 다운로드하기

사전 훈련된 가중치를 평가하기 위한 Kaggle Dogs Vs. Cats 데이터셋을 준비합니다.

Kaggle Dogs Vs. Cats 데이터셋에 대해서는 [고양이와 개 이미지 분류하기](https://codetorial.net/tensorflow/classifying_the_cats_and_dogs.html) 페이지를 참고하세요.

```
!wget --no-check-certificate \
        https://storage.googleapis.com/mledu-datasets/cats_and_dogs_filtered.zip \
       -O /tmp/cats_and_dogs_filtered.zip

import os
import zipfile

local_zip = '//tmp/cats_and_dogs_filtered.zip'

zip_ref = zipfile.ZipFile(local_zip, 'r')
zip_ref.extractall('/tmp')
zip_ref.close()
```



```
--2020-08-25 14:07:51--  https://storage.googleapis.com/mledu-datasets/cats_and_dogs_filtered.zip
Resolving storage.googleapis.com (storage.googleapis.com)... 173.194.216.128, 172.217.193.128, 172.217.204.128, ...
Connecting to storage.googleapis.com (storage.googleapis.com)|173.194.216.128|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 68606236 (65M) [application/zip]
Saving to: ‘/tmp/cats_and_dogs_filtered.zip’

/tmp/cats_and_dogs_ 100%[===================>]  65.43M  83.2MB/s    in 0.8s

2020-08-25 14:07:52 (83.2 MB/s) - ‘/tmp/cats_and_dogs_filtered.zip’ saved [68606236/68606236]
```



우선 Colab 코드셀에 위의 명령어를 입력해서 데이터셋을 다운로드합니다.

아래 그림과 같이 페이지 왼쪽의 목차 탭을 열어서 tmp 폴더에 cats_and_dogs_filtered.zip 파일이 다운로드되어 있는지 확인합니다.

cats_and_dogs_filtered.zip 파일이 준비되어 있다면 압축을 풀어줍니다.

![transfer_learning_03](.\Images\transfer_learning_03.png)

## 데이터 어그멘테이션

```
from tensorflow.keras.preprocessing.image import ImageDataGenerator

# Define our example directories and files
base_dir = '/tmp/cats_and_dogs_filtered'

train_dir = os.path.join(base_dir, 'train')
validation_dir = os.path.join(base_dir, 'validation')

train_cats_dir = os.path.join(train_dir, 'cats')
train_dogs_dir = os.path.join(train_dir, 'dogs')
validation_cats_dir = os.path.join(validation_dir, 'cats')
validation_dogs_dir = os.path.join(validation_dir, 'dogs')

train_cat_fnames = os.listdir(train_cats_dir)
train_dog_fnames = os.listdir(train_dogs_dir)

# Add our data-augmentation parameters to ImageDataGenerator
train_datagen = ImageDataGenerator(rescale=1./255.,
                                 rotation_range=40,
                                 width_shift_range=0.2,
                                 height_shift_range=0.2,
                                 shear_range=0.2,
                                 zoom_range=0.2,
                                 horizontal_flip=True)

# Note that the validation data should not be augmented!
test_datagen = ImageDataGenerator(rescale=1./255.)

# Flow training images in batches of 20 using train_datagen generator
train_generator = train_datagen.flow_from_directory(train_dir,
                                                  batch_size=20,
                                                  class_mode='binary',
                                                  target_size=(150, 150))

# Flow validation images in batches of 20 using test_datagen generator
validation_generator = test_datagen.flow_from_directory(validation_dir,
                                                      batch_size=20,
                                                      class_mode='binary',
                                                      target_size=(150, 150))
```



```
Found 2000 images belonging to 2 classes.
Found 1000 images belonging to 2 classes.
```



[이미지 어그멘테이션의 효과](https://codetorial.net/tensorflow/image_augmentation.html) 페이지에서 다루었던대로 훈련 데이터에 대해 어그멘테이션을 활용합니다.

테스트 이미지에 대해서는 어그멘테이션을 수행하지 않고, 데이터 리스케일만 수행합니다.

## 모델 훈련하기

```
history = model.fit(
  train_generator,
  validation_data=validation_generator,
  steps_per_epoch=100,
  epochs=20,
  validation_steps=50,
  verbose=2
)
```



```
Epoch 1/20
100/100 - 24s - loss: 0.3288 - accuracy: 0.8760 - val_loss: 0.1874 - val_accuracy: 0.9310
Epoch 2/20
100/100 - 22s - loss: 0.2110 - accuracy: 0.9135 - val_loss: 0.0937 - val_accuracy: 0.9640
Epoch 3/20
100/100 - 23s - loss: 0.1942 - accuracy: 0.9245 - val_loss: 0.1157 - val_accuracy: 0.9580
Epoch 4/20
100/100 - 22s - loss: 0.1660 - accuracy: 0.9395 - val_loss: 0.1292 - val_accuracy: 0.9600
Epoch 5/20
100/100 - 23s - loss: 0.1758 - accuracy: 0.9340 - val_loss: 0.1013 - val_accuracy: 0.9570
Epoch 6/20
100/100 - 22s - loss: 0.1537 - accuracy: 0.9405 - val_loss: 0.0996 - val_accuracy: 0.9670
Epoch 7/20
100/100 - 23s - loss: 0.1583 - accuracy: 0.9440 - val_loss: 0.1099 - val_accuracy: 0.9620
Epoch 8/20
100/100 - 22s - loss: 0.1403 - accuracy: 0.9455 - val_loss: 0.0987 - val_accuracy: 0.9640
Epoch 9/20
100/100 - 23s - loss: 0.1583 - accuracy: 0.9450 - val_loss: 0.1143 - val_accuracy: 0.9640
Epoch 10/20
100/100 - 22s - loss: 0.1473 - accuracy: 0.9515 - val_loss: 0.0904 - val_accuracy: 0.9700
Epoch 11/20
100/100 - 22s - loss: 0.1444 - accuracy: 0.9485 - val_loss: 0.1099 - val_accuracy: 0.9640
Epoch 12/20
100/100 - 22s - loss: 0.1336 - accuracy: 0.9545 - val_loss: 0.1065 - val_accuracy: 0.9690
Epoch 13/20
100/100 - 22s - loss: 0.1250 - accuracy: 0.9570 - val_loss: 0.1657 - val_accuracy: 0.9550
Epoch 14/20
100/100 - 22s - loss: 0.1291 - accuracy: 0.9520 - val_loss: 0.1170 - val_accuracy: 0.9680
Epoch 15/20
100/100 - 22s - loss: 0.1268 - accuracy: 0.9585 - val_loss: 0.1100 - val_accuracy: 0.9630
Epoch 16/20
100/100 - 23s - loss: 0.1229 - accuracy: 0.9535 - val_loss: 0.1318 - val_accuracy: 0.9650
Epoch 17/20
100/100 - 22s - loss: 0.1062 - accuracy: 0.9545 - val_loss: 0.1317 - val_accuracy: 0.9700
Epoch 18/20
100/100 - 23s - loss: 0.1020 - accuracy: 0.9630 - val_loss: 0.1404 - val_accuracy: 0.9620
Epoch 19/20
100/100 - 22s - loss: 0.1184 - accuracy: 0.9580 - val_loss: 0.1313 - val_accuracy: 0.9670
Epoch 20/20
100/100 - 23s - loss: 0.1018 - accuracy: 0.9615 - val_loss: 0.1349 - val_accuracy: 0.9640
```



**fit()** 메서드를 사용해서, 20 에포크의 훈련을 수행합니다.

## 훈련 결과 확인하기

```
import matplotlib.pyplot as plt

acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(acc))

plt.plot(epochs, acc, 'r', label='Training accuracy')
plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
plt.title('Training and validation accuracy')
plt.legend(loc=0)
plt.figure()
plt.show()
```



[Matplotlib](https://codetorial.net/matplotlib/index.html) 라이브러리를 이용해서 훈련 과정을 시각화합니다.

20 에포크의 훈련 과정 동안 **과적합 (Overfitting)** 현상 없이 95% 이상의 훈련/테스트 정확도를 달성했습니다.

![transfer_learning_04](.\Images\transfer_learning_04.png)



### 이전글/다음글

이전글 : [13. 이미지 어그멘테이션의 효과](https://codetorial.net/tensorflow/image_augmentation.html)

다음글 : [15. 다중 클래스 분류 문제](https://codetorial.net/tensorflow/multiclass_classification.html)



# 15. 다중 클래스 분류 문제

![multiclass_classification_00](.\Images\multiclass_classification_00.png)

[Rock Paper Scssors Datasets](http://www.laurencemoroney.com/rock-paper-scissors-dataset/)은 2,892개의 **가위, 바위, 보 손동작 제스처 이미지**를 포함하는 데이터셋입니다.

다양한 손동작, 인종, 나이, 성별의 가위, 바위, 보 이미지를 포함하며, 이미지에 해당하는 레이블을 포함합니다.

이미지들은 모두 CGI (Computer-Generated Imagery) 기술을 이용해서 생성되었습니다.

아래의 주소에서 훈련용 이미지와 테스트용 이미지를 다운로드받을 수 있습니다.

```
https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps.zip
https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps-test-set.zip
```



모든 이미지는 흰색 배경을 가지며, 24비트 색상의 300×300 픽셀로 이루어져 있습니다.

이 페이지에서는 세 개의 클래스를 갖는 데이터셋을 분류하는 방법에 대해서 소개합니다.

순서는 아래와 같습니다.

- [데이터셋 다운로드하기](https://codetorial.net/tensorflow/multiclass_classification.html#id2)
- [압출 풀기](https://codetorial.net/tensorflow/multiclass_classification.html#id3)
- [경로 지정하기](https://codetorial.net/tensorflow/multiclass_classification.html#id4)
- [이미지 확인하기](https://codetorial.net/tensorflow/multiclass_classification.html#id5)
- [Neural Network 구성/훈련하기](https://codetorial.net/tensorflow/multiclass_classification.html#neural-network)
- [훈련 결과 확인하기](https://codetorial.net/tensorflow/multiclass_classification.html#id6)

## 데이터셋 다운로드하기

**Google Colab** 환경에서 아래의 명령어를 실행하면, tmp 폴더에 **Rock Paper Scssors Datasets**이 다운로드됩니다.

주소에 직접 접속해서 로컬 환경에 다운로드 받을 수도 있습니다.

```
# 훈련용 이미지
!wget --no-check-certificate \
  https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps.zip \
  -O /tmp/rps.zip
```



```
--2020-09-01 12:56:07--  https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps.zip
Resolving storage.googleapis.com (storage.googleapis.com)... 173.194.69.128, 108.177.126.128, 108.177.127.128, ...
Connecting to storage.googleapis.com (storage.googleapis.com)|173.194.69.128|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 200682221 (191M) [application/zip]
Saving to: ‘/tmp/rps.zip’

/tmp/rps.zip        100%[===================>] 191.38M  25.6MB/s    in 7.5s

2020-09-01 12:56:16 (25.6 MB/s) - ‘/tmp/rps.zip’ saved [200682221/200682221]
```



훈련용 이미지 데이터셋을 다운로드합니다.

```
# 테스트용 이미지
!wget --no-check-certificate \
  https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps-test-set.zip \
  -O /tmp/rps-test-set.zip
```



```
--2020-09-01 12:56:18--  https://storage.googleapis.com/laurencemoroney-blog.appspot.com/rps-test-set.zip
Resolving storage.googleapis.com (storage.googleapis.com)... 173.194.69.128, 108.177.126.128, 108.177.127.128, ...
Connecting to storage.googleapis.com (storage.googleapis.com)|173.194.69.128|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 29516758 (28M) [application/zip]
Saving to: ‘/tmp/rps-test-set.zip’

/tmp/rps-test-set.z 100%[===================>]  28.15M  18.4MB/s    in 1.5s

2020-09-01 12:56:20 (18.4 MB/s) - ‘/tmp/rps-test-set.zip’ saved [29516758/29516758]
```



테스트용 이미지 데이터셋을 다운로드합니다.

## 압출 풀기

```
import os
import zipfile

local_zip = '/tmp/rps.zip'
zip_ref = zipfile.ZipFile(local_zip, 'r')
zip_ref.extractall('/tmp/')
zip_ref.close()

local_zip = '/tmp/rps-test-set.zip'
zip_ref = zipfile.ZipFile(local_zip, 'r')
zip_ref.extractall('/tmp/')
zip_ref.close()
```



**os** 라이브러리를 통해 파일시스템에 접근할 수 있습니다.

**zipfile** 라이브러리의 ZipFile 클래스로 ZIP 파일을 연 후에

**extractall()** 메서드를 이용해서 tmp 폴더에 압축을 풉니다.

아래 그림과 같이 **rps**와 **rps-test-set** 폴더가 만들어졌다면 준비가 된 것입니다.

![multiclass_classification_02](.\Images\multiclass_classification_02.png)

## 경로 지정하기

```
rock_dir = os.path.join('/tmp/rps/rock')
paper_dir = os.path.join('/tmp/rps/paper')
scissors_dir = os.path.join('/tmp/rps/scissors')

rock_files = os.listdir(rock_dir)
paper_files = os.listdir(paper_dir)
scissors_files = os.listdir(scissors_dir)

print('Total number of training rock images:', len(rock_files))
print('Total number of training paper images:', len(paper_files))
print('Total number of training scissors images:', len(scissors_files))

print(rock_files[:10])
print(paper_files[:10])
print(scissors_files[:10])
```



```
Total number of training rock images: 840
Total number of training paper images: 840
Total number of training scissors images: 840
['rock06ck02-084.png', 'rock07-k03-080.png', 'rock06ck02-023.png', 'rock07-k03-041.png', 'rock05ck01-103.png', 'rock02-046.png', 'rock06ck02-104.png', 'rock07-k03-088.png', 'rock02-031.png', 'rock03-116.png']
['paper03-095.png', 'paper07-064.png', 'paper04-028.png', 'paper05-081.png', 'paper04-016.png', 'paper03-020.png', 'paper03-031.png', 'paper03-067.png', 'paper06-017.png', 'paper04-101.png']
['testscissors03-047.png', 'testscissors02-040.png', 'scissors03-073.png', 'scissors04-081.png', 'scissors02-020.png', 'scissors04-117.png', 'testscissors03-069.png', 'scissors01-062.png', 'testscissors01-104.png', 'testscissors01-038.png']
```



훈련에 사용되는 가위, 바위, 보 (Scissors, Rock, Paper) 이미지의 경로를 각각 지정해줍니다.

각 클래스 별로 840개의 훈련용 이미지가 있음을 알 수 있습니다.

## 이미지 확인하기

```
%matplotlib inline

import matplotlib.pyplot as plt
import matplotlib.image as mpimg

pic_index = 2

next_rock = [os.path.join(rock_dir, fname) for fname in rock_files[pic_index-2:pic_index]]
next_paper = [os.path.join(paper_dir, fname) for fname in paper_files[pic_index-2:pic_index]]
next_scissors = [os.path.join(scissors_dir, fname) for fname in scissors_files[pic_index-2:pic_index]]

for i, img_path in enumerate(next_rock + next_paper + next_scissors):
  print(img_path)
  img = mpimg.imread(img_path)
  plt.imshow(img)
  plt.axis('Off')
  plt.show()
```



Matplotlib 라이브러리를 이용해서 각 클래스 별로 두 개의 이미지를 출력합니다.

아래와 같은 이미지들이 출력됩니다.

![multiclass_classification_03](.\Images\multiclass_classification_03.png)

## Neural Network 구성/훈련하기

```
import tensorflow as tf
import keras_preprocessing
from keras_preprocessing import image
from keras_preprocessing.image import ImageDataGenerator

TRAINING_DIR = "/tmp/rps/"
training_datagen = ImageDataGenerator(
    rescale = 1./255,
    rotation_range=40,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest')

VALIDATION_DIR = "/tmp/rps-test-set/"
validation_datagen = ImageDataGenerator(rescale = 1./255)

train_generator = training_datagen.flow_from_directory(
TRAINING_DIR,
target_size=(150,150),
class_mode='categorical',
batch_size=126
)

validation_generator = validation_datagen.flow_from_directory(
VALIDATION_DIR,
target_size=(150,150),
class_mode='categorical',
batch_size=126
)

model = tf.keras.models.Sequential([
  # Note the input shape is the desired size of the image 150x150 with 3 bytes color
  # This is the first convolution
  tf.keras.layers.Conv2D(64, (3,3), activation='relu', input_shape=(150, 150, 3)),
  tf.keras.layers.MaxPooling2D(2, 2),
  # The second convolution
  tf.keras.layers.Conv2D(64, (3,3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2,2),
  # The third convolution
  tf.keras.layers.Conv2D(128, (3,3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2,2),
  # The fourth convolution
  tf.keras.layers.Conv2D(128, (3,3), activation='relu'),
  tf.keras.layers.MaxPooling2D(2,2),
  # Flatten the results to feed into a DNN
  tf.keras.layers.Flatten(),
  tf.keras.layers.Dropout(0.5),
  # 512 neuron hidden layer
  tf.keras.layers.Dense(512, activation='relu'),
  tf.keras.layers.Dense(3, activation='softmax')
])


model.summary()

model.compile(loss = 'categorical_crossentropy', optimizer='rmsprop', metrics=['accuracy'])

history = model.fit(train_generator, epochs=25, steps_per_epoch=20, validation_data = validation_generator, verbose = 1, validation_steps=3)

model.save("rps.h5")
```



```
Found 2520 images belonging to 3 classes.
Found 372 images belonging to 3 classes.
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
conv2d (Conv2D)              (None, 148, 148, 64)      1792
_________________________________________________________________
max_pooling2d (MaxPooling2D) (None, 74, 74, 64)        0
_________________________________________________________________
conv2d_1 (Conv2D)            (None, 72, 72, 64)        36928
_________________________________________________________________
max_pooling2d_1 (MaxPooling2 (None, 36, 36, 64)        0
_________________________________________________________________
conv2d_2 (Conv2D)            (None, 34, 34, 128)       73856
_________________________________________________________________
max_pooling2d_2 (MaxPooling2 (None, 17, 17, 128)       0
_________________________________________________________________
conv2d_3 (Conv2D)            (None, 15, 15, 128)       147584
_________________________________________________________________
max_pooling2d_3 (MaxPooling2 (None, 7, 7, 128)         0
_________________________________________________________________
flatten (Flatten)            (None, 6272)              0
_________________________________________________________________
dropout (Dropout)            (None, 6272)              0
_________________________________________________________________
dense (Dense)                (None, 512)               3211776
_________________________________________________________________
dense_1 (Dense)              (None, 3)                 1539
=================================================================
Total params: 3,473,475
Trainable params: 3,473,475
Non-trainable params: 0
_________________________________________________________________
Epoch 1/25
20/20 [==============================] - 18s 885ms/step - loss: 1.4556 - accuracy: 0.3496 - val_loss: 1.0823 - val_accuracy: 0.4651
Epoch 2/25
20/20 [==============================] - 18s 890ms/step - loss: 1.1098 - accuracy: 0.4103 - val_loss: 1.0265 - val_accuracy: 0.3952
Epoch 3/25
20/20 [==============================] - 18s 890ms/step - loss: 1.1098 - accuracy: 0.4675 - val_loss: 1.0414 - val_accuracy: 0.3333
Epoch 4/25
20/20 [==============================] - 18s 890ms/step - loss: 0.9462 - accuracy: 0.5381 - val_loss: 0.6964 - val_accuracy: 0.6452
Epoch 5/25
20/20 [==============================] - 18s 888ms/step - loss: 0.8694 - accuracy: 0.6702 - val_loss: 0.8906 - val_accuracy: 0.5134
Epoch 6/25
20/20 [==============================] - 18s 886ms/step - loss: 0.6766 - accuracy: 0.6972 - val_loss: 0.4299 - val_accuracy: 0.7204
Epoch 7/25
20/20 [==============================] - 18s 894ms/step - loss: 0.5805 - accuracy: 0.7429 - val_loss: 0.1932 - val_accuracy: 1.0000
Epoch 8/25
20/20 [==============================] - 18s 889ms/step - loss: 0.4697 - accuracy: 0.8147 - val_loss: 0.0851 - val_accuracy: 1.0000
Epoch 9/25
20/20 [==============================] - 18s 888ms/step - loss: 0.3671 - accuracy: 0.8460 - val_loss: 0.4870 - val_accuracy: 0.6935
Epoch 10/25
20/20 [==============================] - 18s 889ms/step - loss: 0.3455 - accuracy: 0.8631 - val_loss: 0.0311 - val_accuracy: 1.0000
Epoch 11/25
20/20 [==============================] - 18s 892ms/step - loss: 0.3319 - accuracy: 0.8813 - val_loss: 0.1506 - val_accuracy: 0.9274
Epoch 12/25
20/20 [==============================] - 18s 889ms/step - loss: 0.2329 - accuracy: 0.9115 - val_loss: 0.0231 - val_accuracy: 1.0000
Epoch 13/25
20/20 [==============================] - 18s 888ms/step - loss: 0.2997 - accuracy: 0.9083 - val_loss: 0.0118 - val_accuracy: 1.0000
Epoch 14/25
20/20 [==============================] - 18s 888ms/step - loss: 0.1999 - accuracy: 0.9258 - val_loss: 0.0142 - val_accuracy: 1.0000
Epoch 15/25
20/20 [==============================] - 18s 895ms/step - loss: 0.1527 - accuracy: 0.9504 - val_loss: 0.0109 - val_accuracy: 1.0000
Epoch 16/25
20/20 [==============================] - 18s 902ms/step - loss: 0.4454 - accuracy: 0.8722 - val_loss: 0.0479 - val_accuracy: 0.9839
Epoch 17/25
20/20 [==============================] - 18s 890ms/step - loss: 0.1210 - accuracy: 0.9595 - val_loss: 0.0065 - val_accuracy: 1.0000
Epoch 18/25
20/20 [==============================] - 18s 886ms/step - loss: 0.2171 - accuracy: 0.9190 - val_loss: 0.0083 - val_accuracy: 1.0000
Epoch 19/25
20/20 [==============================] - 18s 885ms/step - loss: 0.1154 - accuracy: 0.9552 - val_loss: 0.0375 - val_accuracy: 0.9892
Epoch 20/25
20/20 [==============================] - 18s 891ms/step - loss: 0.1721 - accuracy: 0.9377 - val_loss: 0.1403 - val_accuracy: 0.9301
Epoch 21/25
20/20 [==============================] - 18s 886ms/step - loss: 0.1019 - accuracy: 0.9667 - val_loss: 0.0147 - val_accuracy: 1.0000
Epoch 22/25
20/20 [==============================] - 18s 886ms/step - loss: 0.2504 - accuracy: 0.9278 - val_loss: 0.0369 - val_accuracy: 0.9892
Epoch 23/25
20/20 [==============================] - 18s 887ms/step - loss: 0.0775 - accuracy: 0.9734 - val_loss: 0.0141 - val_accuracy: 1.0000
Epoch 24/25
20/20 [==============================] - 18s 894ms/step - loss: 0.0672 - accuracy: 0.9770 - val_loss: 0.0072 - val_accuracy: 1.0000
Epoch 25/25
20/20 [==============================] - 18s 888ms/step - loss: 0.2173 - accuracy: 0.9278 - val_loss: 0.0118 - val_accuracy: 1.0000
```



25회 에포크의 훈련을 진행합니다.

## 훈련 결과 확인하기

```
import matplotlib.pyplot as plt
acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(acc))

plt.plot(epochs, acc, 'r', label='Training accuracy')
plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
plt.title('Training and validation accuracy')
plt.legend(loc=0)
plt.figure()
plt.show()
```



훈련용, 테스트용 데이터에 대한 정확도를 나타내면 아래와 같습니다.

![multiclass_classification_04](.\Images\multiclass_classification_04.png)



### 이전글/다음글

이전글 : [14. 전이 학습 활용하기](https://codetorial.net/tensorflow/transfer_learning.html)

다음글 : [16. 시냅스 가중치 얻기](https://codetorial.net/tensorflow/get_weights.html)



# 16. 시냅스 가중치 얻기

![get_weights](.\Images\get_weights_00.png)

**시냅스 가중치 또는 웨이트 값**은 Neural Network의 뉴런과 뉴런 노드 사이를 연결하는 강도를 나타내는 숫자입니다.

[tf.keras.layers](https://www.tensorflow.org/api_docs/python/tf/keras/layers) 모듈의 모든 레이어는 **get_weights()** 메서드를 포함합니다.

**get_weights()**메서드를 이용해서, 미리 구성한 Neural Network에서 뉴런층의 시냅스 가중치를 얻어보겠습니다.

## 예제

```
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

# 4. 웨이트 얻기
weights = model.get_weights()

print(weights)
print(len(weights))
```



구성한 Neural Network 모델에는 입력층을 제외하고 두 개의 뉴런층이 있습니다.

**get_weights()** 메서드를 이용해서 모델의 시냅스 가중치 (weights)를 weights 변수에 저장하고 출력했습니다.

출력 결과는 아래와 같습니다.

```
[array([[ 0.01922707,  0.01191682, -0.06732179, ..., -0.04985018,
      0.04876792, -0.06567953],
    [-0.02828409,  0.00119013, -0.02863812, ..., -0.0467992 ,
     -0.00200056, -0.05697531],
    [ 0.06635921, -0.02362482, -0.00820123, ...,  0.03124215,
      0.00039714, -0.00920851],
    ...,
    [-0.05759408,  0.06655261,  0.0457562 , ...,  0.0062023 ,
      0.05761695, -0.04720653],
    [ 0.04525699,  0.06081389, -0.0400049 , ..., -0.06753884,
     -0.0037506 ,  0.02214601],
    [ 0.02717116,  0.0228968 , -0.04719903, ...,  0.00125863,
     -0.03331475, -0.04829547]], dtype=float32), array([0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
    0., 0.], dtype=float32), array([[ 0.06985401,  0.02613375, -0.0551643 , ...,  0.06326195,
      0.08241054,  0.07814605],
    [-0.04089634,  0.03981646, -0.09707076, ...,  0.08483479,
     -0.08038449,  0.0651061 ],
    [ 0.01726168,  0.05927653, -0.08869828, ..., -0.05225329,
     -0.00716908,  0.09950536],
    ...,
    [-0.05344408, -0.03864221, -0.05626503, ..., -0.01657531,
      0.00561044, -0.054561  ],
    [-0.075242  , -0.06006503,  0.01800562, ...,  0.09833171,
     -0.0370728 , -0.06306928],
    [-0.1053426 ,  0.04195554,  0.03840356, ...,  0.06571037,
     -0.05478839,  0.00051194]], dtype=float32), array([0., 0., 0., 0., 0., 0., 0., 0., 0., 0.], dtype=float32)]
4
```



이 모델의 시냅스 가중치는 모두 네 개의 NumPy 어레이로 구성되어 있음을 알 수 있습니다.

각 어레이의 형태를 출력해보면

```
print(weights[0].shape)
print(weights[1].shape)
print(weights[2].shape)
print(weights[3].shape)
```



```
(784, 512)
(512,)
(512, 10)
(10,)
```



첫번째 어레이는 784x512개의 값을 갖는 2차원 어레이로서 **입력층 (input layer)과 은닉층 (hidden layer)을 연결하는 가중치**를 나타내는 값입니다.

두번째 어레이는 512개의 0으로 이루어져 있으며, **은닉층 (hidden layer)의 바이어스 (bias) 값**을 나타냅니다.

세번째 어레이는 512x10개의 값을 갖는 2차원 어레이로서 **은닉층 (hidden layer)과 출력층 (output layer)을 연결하는 가중치**를 나타내는 값입니다.

네번째 어레이는 10개의 0으로 이루어져 있으며, **출력층 (output layer)의 바이어스 (bias) 값**을 나타냅니다.

## 웨이트 저장하기

```
import numpy as np

np.savetxt('weights[0].csv', weights[0])
np.savetxt('weights[1].csv', weights[1])
np.savetxt('weights[2].csv', weights[2])
np.savetxt('weights[3].csv', weights[3])
```



NumPy의 [np.savetxt()](https://codetorial.net/numpy/functions/numpy_savetxt.html) 함수를 이용하면, 각각의 가중치 값을 csv 파일로 저장할 수 있습니다.

### 관련 페이지

- [NumPy 기초](https://codetorial.net/numpy/basics.html)
- [numpy.savetxt](https://codetorial.net/numpy/functions/numpy_savetxt.html)



### 이전글/다음글

이전글 : [15. 다중 클래스 분류 문제](https://codetorial.net/tensorflow/multiclass_classification.html)

다음글 : [17. 시냅스 가중치 적용하기](https://codetorial.net/tensorflow/set_weights.html)



# 17. 시냅스 가중치 적용하기

![set_weights](.\Images\set_weights_00.png)

[tf.keras.layers](https://www.tensorflow.org/api_docs/python/tf/keras/layers) 모듈의 모든 레이어는 **set_weights()** 메서드를 포함합니다.

**set_weights()** 메서드를 이용해서 **미리 생성, 가공하거나, 불러온 가중치 값 (weights)들을 모델에 적용**할 수 있습니다.

아래 예제에서는 임의의 가중치 값을 갖는 NumPy 어레이를 생성하고 모델에 적용해보겠습니다.

## 예제

```
import numpy as np
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

# 4. 웨이트 생성하기
weight0 = np.random.rand(784, 512) * 0.1
weight1 = np.zeros(512)
weight2 = np.random.rand(512, 10) * 0.05
weight3 = np.zeros(10)

weights = np.array([weight0, weight1, weight2, weight3])

# 5. 웨이트 적용하기
model.set_weights(weights)

print(model.get_weights())
```



생성한 가중치 값들이 모델에 적용됩니다.

## 설명

```
# 4. 웨이트 생성하기
weight0 = np.random.rand(784, 512) * 0.1
weight1 = np.zeros(512)
weight2 = np.random.rand(512, 10) * 0.05
weight3 = np.zeros(10)

weights = np.array([weight0, weight1, weight2, weight3])
```



각 레이어별로 적용할 가중치 값과 바이어스 값을 각각의 어레이로 만들었습니다.

NumPy의 [random.rand()](https://codetorial.net/numpy/random.html#random-rand) 함수를 이용해서

첫번째 레이어의 가중치 값은 0에서 0.1 사이의 임의의 값을 갖도록,

두번째 레이어의 가중치 값은 0에서 0.05 사이의 임의의 값을 갖도록 만들어주었습니다.

또한 NumPy의 [zeros()](https://codetorial.net/numpy/functions/numpy_zeros.html) 함수를 이용해서 바이어스 값은 모두 0으로 합니다.

그리고 네 개의 어레이를 하나의 NumPy 어레이로 합쳐 주었습니다.

```
# 5. 웨이트 적용하기
model.set_weights(weights)
```



이제 **set_weights()**를 이용해서 생성한 weights를 모델에 적용해줍니다.

```
print(model.get_weights())
```



[시냅스 가중치 얻기](https://codetorial.net/tensorflow/get_weights.html) 페이지에서 다루었던 **get_weights()** 메서드를 이용해서 모델의 가중치 값을 출력해보면

생성한 가중치 값들이 적용되어 있음을 알 수 있습니다.

## 바이어스 사용하지 않기

```
import numpy as np
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(512, activation=tf.nn.relu, use_bias=False),
  tf.keras.layers.Dense(10, activation=tf.nn.softmax, use_bias=False)
])

weights = model.get_weights()

print(weights)
print(len(weights))
print(weights[0].shape)
print(weights[1].shape)
```



```
[array([[-0.00180037,  0.00632494,  0.05195455, ..., -0.01135769,
        -0.00360435, -0.00013197],
       [ 0.01351992, -0.00184936, -0.06347489, ...,  0.06174915,
        -0.04220404, -0.03083536],
       [ 0.0582615 ,  0.01855589, -0.06096812, ...,  0.0082844 ,
         0.05198504, -0.04045105],
       ...,
       [-0.01063943,  0.06117912, -0.0423585 , ..., -0.02725973,
        -0.0287867 , -0.02058563],
       [-0.031974  , -0.00435832,  0.01853658, ...,  0.06000179,
        -0.05476063, -0.03798168],
       [ 0.01977099,  0.05540701,  0.06362468, ...,  0.02298732,
         0.04441025, -0.04893865]], dtype=float32), array([[ 0.01927812, -0.08453314, -0.05655076, ...,  0.01809663,
        -0.02808041,  0.10296542],
       [ 0.01096758,  0.07996217, -0.02063442, ...,  0.05331417,
         0.01113722,  0.05506157],
       [-0.07804362, -0.01005847, -0.08122149, ...,  0.01433475,
         0.06766728, -0.1058412 ],
       ...,
       [-0.02241903, -0.07390198,  0.04372018, ...,  0.10399926,
        -0.04324216, -0.08352485],
       [ 0.01169502, -0.08287773, -0.03229887, ...,  0.07274764,
         0.07055562, -0.08828036],
       [ 0.0526795 ,  0.06339648, -0.09365696, ...,  0.01743795,
        -0.08578204,  0.0449848 ]], dtype=float32)]
2
(784, 512)
(512, 10)
```



각 레이어를 구성할 때, **use_bias=False**로 설정하면 바이어스를 나타내는 NumPy 어레이가 생성되지 않습니다.

모델의 시냅스 가중치를 출력해보면 (784, 512)와 (512, 10) 형태의 두 개의 NumPy 어레이가 생성되었음을 알 수 있습니다.

[Matplotlib](https://codetorial.net/matplotlib/heatmap.html) 라이브러리를 이용해서 시냅스 가중치 값을 히트맵으로 나타내보면 아래와 같습니다.

![set_weights](.\Images\set_weights_01.png)

![set_weights](.\Images\set_weights_02.png)

## tf.keras.initializers.RandomNormal 사용하기

[tf.keras.initializers.RandomNormal](https://www.tensorflow.org/api_docs/python/tf/keras/initializers/RandomNormal)는 표준분포를 갖는 텐서 (tensor)를 생성합니다.

```
import numpy as np
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. tf.keras.initializers.RandomNormal 사용하기
initializer1 = tf.keras.initializers.RandomNormal(mean=2.0, stddev=0.05, seed=None)
initializer2 = tf.keras.initializers.RandomNormal(mean=-2.0, stddev=0.01, seed=None)

# 4. 모델 구성
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(512, activation=tf.nn.relu, use_bias=False, kernel_initializer=initializer1),
  tf.keras.layers.Dense(10, activation=tf.nn.softmax, use_bias=False, kernel_initializer=initializer2)
])

# 5. 시냅스 가중치 출력
weights = model.get_weights()
print(weights)

# 6. Matplotlib 시각화하기
import matplotlib.pyplot as plt

plt.hist(weights[0], bins=100, alpha=0.7, histtype='step')
plt.hist(weights[1], bins=100, alpha=0.7, histtype='step')
plt.show()
```



```
[array([[2.0707498, 2.0582442, 2.0279617, ..., 2.0003266, 1.9494209,
      1.9950501],
     [2.053631 , 1.9367449, 2.0498245, ..., 2.0626428, 2.077433 ,
      1.8980446],
     [1.9524959, 1.9671254, 2.0178082, ..., 2.0075676, 2.0454142,
      1.9336971],
     ...,
     [1.9904697, 1.9591775, 1.9642094, ..., 1.9809738, 2.011998 ,
      1.8940853],
     [2.0789752, 2.0541403, 1.989919 , ..., 1.9354533, 1.9982094,
      1.9887308],
     [1.9499259, 2.0538788, 1.9788142, ..., 2.0026433, 2.0417168,
      1.9619868]], dtype=float32), array([[-1.9974184, -1.9957153, -2.00028  , ..., -2.0191674, -2.0111377,
      -1.9899257],
     [-2.0003097, -2.018554 , -2.0133874, ..., -1.9968629, -2.0008864,
      -1.9960991],
     [-1.9808995, -1.9996533, -2.0080128, ..., -2.0065885, -1.9875172,
      -1.9999095],
     ...,
     [-1.9964446, -2.0137677, -1.9882427, ..., -2.0074763, -2.0203407,
      -2.0071764],
     [-2.0125241, -1.9982183, -2.0022962, ..., -2.022005 , -1.9981374,
      -1.9985487],
     [-2.009314 , -2.0061378, -2.0122364, ..., -1.9981507, -1.9900427,
      -1.9930578]], dtype=float32)]
```



**tf.keras.layer** 모듈의 각 레이어를 구성하면서 **kernel_initializer** 인자를 사용해서 미리 생성한 표준분포를 갖는 텐서를 모델의 시냅스 가중치로 적용했습니다.

[Matplotlib](https://codetorial.net/matplotlib/histogram.html) 을 이용해서 히스토그램으로 나타내보면 아래와 같습니다.

각각 평균 2.0, 표준편차 0.05, 평균 -2.0, 표준편차 0.01인 시냅스 가중치 값들이 생성되었음을 알 수 있습니다.

![set_weights](.\Images\set_weights_03.png)

## tf.keras.initializers.RandomUniform 사용하기

[tf.keras.initializers.RandomUniform](https://www.tensorflow.org/api_docs/python/tf/keras/initializers/RandomUniform)은 균등분포를 갖는 텐서 (tensor)를 생성합니다.

```
import numpy as np
import tensorflow as tf

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. tf.keras.initializers.RandomNormal 사용하기
initializer1 = tf.keras.initializers.RandomUniform(minval=-0.05, maxval=0.05, seed=None)
initializer2 = tf.keras.initializers.RandomUniform(minval=-0.1, maxval=0.1, seed=None)

# 4. 모델 구성
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(512, activation=tf.nn.relu, use_bias=False, kernel_initializer=initializer1),
  tf.keras.layers.Dense(10, activation=tf.nn.softmax, use_bias=False, kernel_initializer=initializer2)
])

# 5. 시냅스 가중치 출력
weights = model.get_weights()
print(weights)

# 6. Matplotlib 시각화하기
import matplotlib.pyplot as plt

plt.hist(weights[0], bins=100, alpha=0.7, histtype='step')
plt.hist(weights[1], bins=100, alpha=0.7, histtype='step')
plt.show()
```



```
[array([[-0.04813321,  0.03850188, -0.04333941, ...,  0.03577845,
      -0.02961006, -0.03295499],
     [-0.01708596,  0.0058869 , -0.00387685, ..., -0.02265617,
      -0.00891232, -0.03500197],
     [-0.04790206,  0.00961652, -0.03043391, ..., -0.0473738 ,
       0.00498282, -0.00555975],
     ...,
     [ 0.00313902, -0.03869805,  0.02545181, ..., -0.02569604,
       0.00126978,  0.01914135],
     [-0.00128641, -0.02937489,  0.04575497, ..., -0.02505137,
      -0.01237155, -0.01361365],
     [ 0.0145659 , -0.01967684, -0.02684872, ..., -0.04327303,
      -0.0002812 ,  0.01977504]], dtype=float32), array([[-0.07793639,  0.07992528,  0.07283423, ...,  0.04714014,
      -0.07861056,  0.08535979],
     [-0.01000409,  0.05313633,  0.09388087, ..., -0.04752097,
      -0.00120325,  0.04486213],
     [ 0.03404623,  0.03290916,  0.03367629, ...,  0.08172724,
       0.00491893, -0.00589781],
     ...,
     [ 0.06921809, -0.08647346, -0.06854935, ...,  0.06935558,
      -0.08771922,  0.07440331],
     [-0.05893927, -0.02979596, -0.08479095, ..., -0.06812584,
       0.00100436,  0.0872466 ],
     [-0.0245528 ,  0.06422883,  0.03848126, ..., -0.0373769 ,
      -0.0915236 , -0.01972015]], dtype=float32)]
```



tf.keras.initializers.RandomUniform의 minval, maxval은 균등분포의 범위를 지정합니다.

[Matplotlib](https://codetorial.net/matplotlib/histogram.html) 라이브러리를 이용해서 히스토그램으로 나타내보면 아래와 같습니다.

각각 최소 -0.05, 최대 0.05, 그리고 최소 -0.1, 최대 0.1 범위를 갖는 임의의 시냅스 가중치가 생성되었음을 알 수 있습니다.

![set_weights](.\Images\set_weights_04.png)

### 관련 페이지

- [NumPy 기초](https://codetorial.net/numpy/basics.html)
- [numpy.array](https://codetorial.net/numpy/functions/numpy_array.html)
- [numpy.random.rand](https://codetorial.net/numpy/functions/numpy_random_rand.html)
- [numpy.zeros](https://codetorial.net/numpy/functions/numpy_zeros.html)



### 이전글/다음글

이전글 : [16. 시냅스 가중치 얻기](https://codetorial.net/tensorflow/get_weights.html)

다음글 : [18. 모델 시각화하기](https://codetorial.net/tensorflow/visualize_model.html)



# 18. 모델 시각화하기

plot_model()을 이용하면 Sequential()으로 구성한 신경망 모델을 시각화할 수 있습니다.

에러가 발생하는 경우 페이지 아래 내용을 참고하세요.

## 예제

```
import tensorflow as tf
from tensorflow.keras.utils import plot_model

# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

plot_model(model, to_file='model.png')
plot_model(model, to_file='model_shapes.png', show_shapes=True)
```



plot_model()에 모델 (model)과 파일명을 지정해줌으로써 시각화된 결과를 이미지 파일로 저장할 수 있습니다.

show_shapes을 True로 설정하면 층의 형태를 함께 시각화합니다.

결과는 아래와 같습니다.

![_images/model.png](.\Images\model.png)

그림. 모델 시각화하기 (without shapes).

![_images/model_shapes.png](.\Images\model_shapes.png)

그림. 모델 시각화하기 (with shapes).

## 에러가 발생하는 경우

### pydot

```
Traceback (most recent call last):
  File "/Users/test.py", line 35, in <module>
    plot_model(model, to_file='model.png')
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 132, in plot_model
    dot = model_to_dot(model, show_shapes, show_layer_names, rankdir)
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 55, in model_to_dot
    _check_pydot()
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 20, in _check_pydot
    'Failed to import `pydot`. '
ImportError: Failed to import `pydot`. Please install `pydot`. For example with `pip install pydot`.

Process finished with exit code 1
```



위와 같은 에러가 발생하는 경우

```
pip install pydot
```



‘pip install pydot’을 이용해서 ‘pydot’을 설치하세요.

### GraphViz

```
Traceback (most recent call last):
  File "/Users/test.py", line 35, in <module>
    plot_model(model, to_file='model.png')
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 132, in plot_model
    dot = model_to_dot(model, show_shapes, show_layer_names, rankdir)
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 55, in model_to_dot
    _check_pydot()
  File "/Users/anaconda3/lib/python3.5/site-packages/keras/utils/vis_utils.py", line 29, in _check_pydot
    '`pydot` failed to call GraphViz.'
OSError: `pydot` failed to call GraphViz.Please install GraphViz (https://www.graphviz.org/) and ensure that its executables are in the $PATH.
```



위와 같은 에러가 발생하는 경우

```
brew install graphviz
```



‘brew install graphviz’을 이용해서 ‘GraphViz’을 설치하세요.

### import plot_model

```
from keras.utils import plot_model
from tensorflow.keras.utils import plot_model
```



plot_model을 임포트할 때, tenforflow와 keras를 혼용하지 않고, 아래와 같이 사용하세요.



### 이전글/다음글

이전글 : [17. 시냅스 가중치 적용하기](https://codetorial.net/tensorflow/set_weights.html)

다음글 : [19. 훈련 과정 시각화하기](https://codetorial.net/tensorflow/visualize_training_history.html)



# 19. 훈련 과정 시각화하기

신경망 모델의 훈련에 사용되는 fit() 메서드는 History 객체를 반환합니다.

History.history 속성은 훈련 과정에서 에포크 (epoch)에 따른 **정확도 (accuracy)** 와 같은 지표와 **손실값** 을 기록합니다.

또한 검증 (validation)의 지표와 손실값도 기록합니다.

아래의 코드는 10회의 훈련 과정에서 기록된 훈련 정확도 & 손실값 그리고 검증 정확도 & 손실값을 시각화합니다.

## 예제

```
import tensorflow as tf
import matplotlib.pyplot as plt


# 1. MNIST 데이터셋 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

# 4. 모델 컴파일
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

# 5. 모델 훈련
history = model.fit(x_train, y_train, validation_split=0.25, epochs=10, verbose=1)
print(history.history)

# 6 훈련 과정 시각화 (정확도)
plt.plot(history.history['accuracy'])
plt.plot(history.history['val_accuracy'])
plt.title('Model accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend(['Train', 'Test'], loc='upper left')
plt.show()

# 7 훈련 과정 시각화 (손실)
plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('Model loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend(['Train', 'Test'], loc='upper left')
plt.show()
```



model.fit()에 훈련 데이터를 입력해줍니다.

validation_split은 0에서 1 사이의 값입니다. 0.25로 설정해주면 훈련 데이터의 25%를 검증에 사용합니다.

따라서 아래와 같이 60,000개의 데이터 중 45000개를 훈련에 사용하고 15,000개를 검증에 사용합니다.

```
Train on 45000 samples, validate on 15000 samples
Epoch 1/10
   32/45000 [..............................] - ETA: 3:20 - loss: 2.3440 - accuracy: 0.0938
  416/45000 [..............................] - ETA: 21s - loss: 1.6479 - accuracy: 0.5721
  736/45000 [..............................] - ETA: 14s - loss: 1.3261 - accuracy: 0.6467
 1088/45000 [..............................] - ETA: 12s - loss: 1.0996 - accuracy: 0.7040
 1408/45000 [..............................] - ETA: 11s - loss: 0.9780 - accuracy: 0.7294
 1632/45000 [>.............................] - ETA: 11s - loss: 0.9111 - accuracy: 0.7445
 1792/45000 [>.............................] - ETA: 11s - loss: 0.8627 - accuracy: 0.7589
 2048/45000 [>.............................] - ETA: 10s - loss: 0.8037 - accuracy: 0.7739
 2432/45000 [>.............................] - ETA: 10s - loss: 0.7423 - accuracy: 0.7903
```



print(history.history)을 통해 출력된 결과는 아래와 같습니다.

```
{'accuracy': [0.9335778, 0.9725556, 0.9815556, 0.9874222, 0.9913778, 0.99322224, 0.9945111, 0.99597776, 0.99635553, 0.99664444],
 'val_loss': [0.13596169148484866, 0.10232374267478785, 0.09159363598103325, 0.08637172984133164, 0.09674505230680419, 0.09105636464593311, 0.09010037897935448, 0.09504081677481688, 0.09993200332935667, 0.10999317096428277],
  'loss': [0.2266461635907491, 0.09062119808826181, 0.0589320162879924, 0.04020388817155165, 0.027329568712951408, 0.02126024063843199, 0.017342214744481155, 0.013346235364758307, 0.012164281833562482, 0.009792077704742164],
   'val_accuracy': [0.95993334, 0.9709333, 0.9734667, 0.976, 0.9734, 0.9754, 0.9766, 0.9781333, 0.9758667, 0.9764]}
```



‘accuracy’, ‘val_accuracy’, ‘loss’, ‘val_loss’와 같은 키 값을 이용해서 훈련 과정의 에포크에 따른 정확도, 손실값을 얻을 수 있습니다.

그래프로 나타낸 훈련 과정의 정확도와 손실은 아래와 같습니다.

![_images/visualize_training_history_01.png](.\Images\visualize_training_history_01.png)

그림. 훈련 과정의 정확도 시각화하기.

![_images/visualize_training_history_02.png](.\Images\visualize_training_history_02.png)

그림. 훈련 과정의 손실 시각화하기.

### 관련 페이지

- [Matplotlib](https://codetorial.net/matplotlib/index.html)



### 이전글/다음글

이전글 : [18. 모델 시각화하기](https://codetorial.net/tensorflow/visualize_model.html)

다음글 : [20. 모델 저장하고 복원하기](https://codetorial.net/tensorflow/save_load_model.html)



# 20. 모델 저장하고 복원하기

학습이 이루어진 모델을 저장하고 복원할 수 있습니다.

케라스는 HDF5 (.h5) 표준 포맷을 제공해서, 모델의 가중치, 모델 구성, 옵티마이저 설정까지 저장합니다.

저장한 모델을 TensorFlow.js로 불러와서 웹 브라우저에서 모델을 다시 훈련하고 실행하거나, 모바일 장치에 맞도록 변환해서 사용할 수 있습니다.

아래의 [MNIST 손글씨 이미지 인식](https://codetorial.net/tensorflow/mnist_classification.html) 예제에서 save()와 load_model() 메서드의 사용에 대해 알아봅니다.

## 모델 저장

### 예제

```
import tensorflow as tf

# 1. MNIST 데이터넷 임포트
mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. 데이터 전처리
x_train, x_test = x_train/255.0, x_test/255.0

# 3. 모델 구성
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(512, activation=tf.nn.relu),
  tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

# 4. 모델 컴파일
model.compile(optimizer='adam',
            loss='sparse_categorical_crossentropy',
            metrics=['accuracy'])

# 5. 모델 훈련
model.fit(x_train, y_train, epochs=5)

# 6. 모델 저장
model.save('model1.h5')
```



```
Train on 60000 samples
Epoch 1/5
60000/60000 [==============================] - 7s 119us/sample - loss: 0.2002 - accuracy: 0.9414
Epoch 2/5
60000/60000 [==============================] - 7s 123us/sample - loss: 0.0798 - accuracy: 0.9754
Epoch 3/5
60000/60000 [==============================] - 6s 107us/sample - loss: 0.0518 - accuracy: 0.9835
Epoch 4/5
60000/60000 [==============================] - 6s 104us/sample - loss: 0.0384 - accuracy: 0.9872
Epoch 5/5
60000/60000 [==============================] - 6s 106us/sample - loss: 0.0259 - accuracy: 0.9914
```



5회의 에포크 동안 학습이 이루어진 모델을 save() 메서드를 이용해서 저장했습니다.

이제 ‘model1.h5’ 파일이 생성됩니다.

## 모델 복원하기

### 예제

```
import tensorflow as tf

mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train, x_test = x_train/255.0, x_test/255.0

# 모델 복원
loaded_model = tf.keras.models.load_model('model1.h5')
loaded_model.summary()

loss, acc = loaded_model.evaluate(x_test, y_test, verbose=2)
print('Loss: ', loss)
print('Acc: ', acc)
```



```
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
flatten (Flatten)            (None, 784)               0
_________________________________________________________________
dense (Dense)                (None, 512)               401920
_________________________________________________________________
dense_1 (Dense)              (None, 10)                5130
=================================================================
Total params: 407,050
Trainable params: 407,050
Non-trainable params: 0
_________________________________________________________________

10000/1 - 1s - loss: 0.0348 - accuracy: 0.9807
Loss:  0.06882412914167507
Acc:  0.9807
```



load_model() 메서드를 이용해서 저장한 모델 (‘model1.h5’)을 불러왔습니다.

summary()를 이용해서 모델에 대한 정보를 확인합니다.

그리고 evaluate()를 이용해서 이전의 훈련의 결과를 확인합니다.

공식 문서에 의하면, 모델의 구조를 저장할 수 있지만 모델을 로드한 후에 다시 컴파일 해야합니다.

즉, 옵티마이저의 상태는 유지되지 않습니다.



### 이전글/다음글

이전글 : [19. 훈련 과정 시각화하기](https://codetorial.net/tensorflow/visualize_training_history.html)

다음글 : [21. 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/index.html)



# 21. 시계열 데이터 예측하기

![time_series_data](.\Images\time_series_data.png)



지금까지 [MNIST](https://codetorial.net/tensorflow/mnist_classification.html)와 [Fashion MNIST](https://codetorial.net/tensorflow/fashion_mnist_classification.html) 데이터셋을 이용해서 분류 문제에 **Tensorflow**를 사용했습니다.

이번에는 **시계열 (Time series)** 데이터를 예측하는 문제에 **Tensorflow**를 사용해 보겠습니다.

시계열 데이터는 **주식 가격이나 날씨 정보와 같이 시간의 경과에 따라서 얻어진 관측치 또는 통계치** 등을 의미합니다.

지금까지 **완전 연결된 신경망 (Fully-Connected Neural Network)** 또는 **합성곱 신경망 (Convolutional Neural Network)**을 사용했다면,

이번에는 **순환 신경망 (Recurrent Neural Network)**을 사용하는 방법에 대해 알아봅니다.

Contents

- [시계열 데이터 기초](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html)
- [시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html)
- [날씨 데이터셋](https://codetorial.net/tensorflow/time_series_forecasting/weather_dataset.html)
- [단변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_univariate_time_series.html)
- [순환 신경망 사용하기](https://codetorial.net/tensorflow/time_series_forecasting/recurrent_neural_network.html)
- [다변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_multivariate_time_series.html)



### 이전글/다음글

이전글 : [20. 모델 저장하고 복원하기](https://codetorial.net/tensorflow/save_load_model.html)

다음글 : [시계열 데이터 기초](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html)



#  시계열 데이터 기초

![시계열 데이터 기초](.\Images\time_series_data_00.png)


이 페이지에서는 **시계열 데이터 (Time Series Data)**의 기본적인 특징에 대해 소개합니다.

그리고 NumPy를 이용해서 시계열 데이터를 만들고, Matplotlib를 이용해서 시각화합니다.

■ **Table of Contents**

- [시계열 데이터 시각화 함수](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html#id2)
- [경향성을 갖는 시계열 데이터](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html#id5)
- [계절성을 갖는 시계열 데이터](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html#id7)
- [노이즈를 갖는 시계열 데이터](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html#id10)
- [자기상관성을 갖는 시계열 데이터](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html#id13)

## 시계열 데이터 시각화 함수

### 예제

```
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf
from tensorflow import keras

plt.style.use('default')
plt.rcParams['figure.figsize'] = (6, 3)
plt.rcParams['font.size'] = 12

def plot_series(time, series, format="-", start=0, end=None, label=None):
    plt.plot(time[start:end], series[start:end], format, label=label)
    plt.xlabel("Time")
    plt.ylabel("Value")
    if label:
        plt.legend(fontsize=14)
    plt.grid(True)
```



**plot_series()** 함수는 임의의 시간 값 (time), 시계열 데이터 (series)를 입력받아 Matplotlib 그래프로 나타내는 함수입니다.

X, Y축 레이블을 각각 ‘Time’, ‘Value’로 지정하고, 데이터 영역에 그리드를 표시했습니다.

[Matplotlib 축 레이블 설정하기](https://codetorial.net/matplotlib/set_label.html), [그리드 설정하기](https://codetorial.net/matplotlib/set_grid.html) 페이지를 참고하세요.

아래의 예제에서 계속해서 이 함수를 사용합니다.

## 경향성을 갖는 시계열 데이터

### 예제

```
def trend(time, slope=0):
    return slope * time

time = np.arange(4 * 365 + 1)
series = trend(time, slope=0.1)

plot_series(time, series)
plt.show()
```



**trend()** 함수는 경향성을 갖는 시계열 데이터를 반환합니다.

**slope** 값에 따라서 시간에 따라 양의 경향성, 음의 경향성을 가질 수 있습니다.

예제에서는 길이 4 * 365 + 1의 시간 동안 시간에 따라 0.1의 기울기를 갖는 시계열 데이터를 만들었습니다.

아래와 같은 데이터가 만들어집니다.

![시계열 데이터 기초 - 경향성을 갖는 시계열 데이터](.\Images\time_series_data_01.png)

시계열 데이터 기초 - 경향성을 갖는 시계열 데이터

## 계절성을 갖는 시계열 데이터

### 예제1

```
def seasonal_pattern(season_time):
    return np.where(season_time < 0.6,
                    np.cos(season_time * 2 * np.pi),
                    1 / np.exp(3 * season_time))

def seasonality(time, period, amplitude=1, phase=0):
    season_time = ((time + phase) % period) / period
    return amplitude * seasonal_pattern(season_time)

amplitude = 40
series = seasonality(time, period=365, amplitude=amplitude)

plot_series(time, series)
plt.show()
```



**seasonal_pattern()** 함수는 입력 **season_time**에 대해서 0.6보다 작은 경우에는 np.cos(season_time * 2 * np.pi) 값을,

그렇지 않은 경우에는 1 / np.exp(3 * season_time)을 반환합니다.

[NumPy 다양한 함수들 - numpy.where](https://codetorial.net/numpy/functions/numpy_where.html) 페이지를 참고하세요.

**seasonality()** 함수는 주어진 주기 **period**에 대해 특정 값을 반복하는 시계열 데이터를 반환하는 함수입니다.

아래와 같은 시계열 데이터가 만들어졌습니다.

![시계열 데이터 기초 - 계절성을 갖는 시계열 데이터](.\Images\time_series_data_02.png)

시계열 데이터 기초 - 계절성을 갖는 시계열 데이터

### 예제2

```
baseline = 10
slope = 0.05
series = baseline + trend(time, slope) + seasonality(time, period=365, amplitude=amplitude)

plot_series(time, series)
plt.show()
```



**trend()**, **seasonality()** 함수를 사용해서 경향성 (Trend)과 계절성 (Seasonality)을 모두 갖는 시계열 데이터를 만들었습니다.

아래와 같은 시계열 데이터가 만들어졌습니다.

![시계열 데이터 기초 - 경향성/계절성을 갖는 시계열 데이터](.\Images\time_series_data_03.png)

시계열 데이터 기초 - 경향성/계절성을 갖는 시계열 데이터

## 노이즈를 갖는 시계열 데이터

### 예제1

```
def white_noise(time, noise_level=1, seed=None):
    rnd = np.random.RandomState(seed)
    return rnd.rand(len(time)) * noise_level

noise_level = 5
noise = white_noise(time, noise_level, seed=42)

plot_series(time, noise)
plt.show()
```



**white_noise()** 함수는 0에서 noise_level 값 사이의 임의의 실수를 갖는 시계열 데이터를 반환합니다.

아래와 같은 시계열 데이터가 만들어졌습니다.

![시계열 데이터 기초 - 노이즈를 갖는 시계열 데이터](.\Images\time_series_data_04.png)

시계열 데이터 기초 - 노이즈를 갖는 시계열 데이터

### 예제2

```
baseline = 10
slope = 0.05
noise_level = 5
series = baseline + trend(time, slope) + seasonality(time, period=365, amplitude=amplitude) \
        + white_noise(time, noise_level, seed=42)

plot_series(time, series)
plt.show()
```



이번에는 경향성 (Trend), 계절성 (Seasonality)과 노이즈 (Noise)를 모두 갖는 시계열 데이터를 만들었습니다.

아래와 같습니다.

![시계열 데이터 기초 - 경향성, 계절성, 노이즈를 갖는 시계열 데이터](.\Images\time_series_data_05.png)

시계열 데이터 기초 - 경향성, 계절성, 노이즈를 갖는 시계열 데이터

## 자기상관성을 갖는 시계열 데이터

### 예제1

```
split_time = 1000
time_train, x_train = time[:split_time], series[:split_time]
time_valid, x_valid = time[split_time:], series[split_time:]

def autocorrelation(time, amplitude, seed=None):
    rnd = np.random.RandomState(seed)
    pi = 0.8
    ar =  rnd.randn(len(time) + 1)
    for step in range(1, len(time) + 1):
        ar[step] += pi * ar[step - 1]       ## 이전의 값의 0.8배를 더하기
    return ar[1:] * amplitude

series = autocorrelation(time, 10, seed=42)
plot_series(time[:200], series[:200])
plt.show()
```



**autocorrelation()** 함수는 자기상관성 (Autocorrelation)을 갖는 시계열 데이터를 반환합니다.

**ar**은 정규분포를 갖는 임의의 데이터입니다.

이전 시간 스텝 값의 0.8배를 더해주고, 크기 **amplitude**를 곱한 시계열 데이터를 반환합니다.

![시계열 데이터 기초 - 자기상관성을 갖는 시계열 데이터](.\Images\time_series_data_06.png)

시계열 데이터 기초 - 자기상관성을 갖는 시계열 데이터

### 예제2

```
series = autocorrelation(time, 10, seed=42) + trend(time, 2)
plot_series(time[:200], series[:200])
plt.show()
```



자기상관성 (Autocorrelation)과 경향성 (Trend)을 갖는 시계열 데이터를 만들었습니다.

결과는 아래와 같습니다.

![시계열 데이터 기초 - 자기상관성과 경향성을 갖는 시계열 데이터](.\Images\time_series_data_07.png)

시계열 데이터 기초 - 자기상관성과 경향성을 갖는 시계열 데이터

### 예제3

```
series = autocorrelation(time, 10, seed=42) + seasonality(time, period=50, amplitude=150) + trend(time, 2)
plot_series(time[:200], series[:200])
plt.show()
```



이번에는 자기상관성 (Autocorrelation), 경향성 (Trend)과 함께 계절성 (Seasonality)을 갖는 시계열 데이터를 만들었습니다.

아래와 같습니다.

![시계열 데이터 기초 - 자기상관성, 경향성, 계절성을 갖는 시계열 데이터](.\Images\time_series_data_08.png)

시계열 데이터 기초 - 자기상관성, 경향성, 계절성을 갖는 시계열 데이터

### 예제4

```
series = autocorrelation(time, 10, seed=42) + seasonality(time, period=50, amplitude=150) + trend(time, 2)
series2 = autocorrelation(time, 5, seed=42) + seasonality(time, period=50, amplitude=2) + trend(time, -1) + 550
series[200:] = series2[200:]        # 자기상관 amp 10->5, 계절성 amp 150->2, 경향성 slope 2->-1 + 550
series += white_noise(time, 30)
plot_series(time[:300], series[:300])
plt.show()
```



이번에는 특정 시점 이후로 다른 특성을 갖는 시계열 데이터를 만들어보겠습니다.

2/3 지점 이후로 크기 (amplitude)와 주기 (period), 경향성 (slope)이 모두 달라진 특성을 갖는 시계열 데이터입니다.

또한 전체 구간에서 노이즈 (Noise)를 갖습니다.

결과는 아래와 같습니다.

![시계열 데이터 기초 - 특정 시점 이후 변화 추세를 갖는 시계열 데이터](.\Images\time_series_data_09.png)

시계열 데이터 기초 - 특정 시점 이후 변화 추세를 갖는 시계열 데이터



### 이전글/다음글

이전글 : [21. 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/index.html)

다음글 : [시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html)



# 시계열 데이터 예측하기

![시계열 데이터 예측하기](.\Images\basics_of_time_series_forecast_00.jpg)

Photo by Chris Liverani on Unsplash



[이전 페이지](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html)에서는 **시계열 데이터 (Time Series Data)**의 기본적인 특징에 대해 알아보았습니다.

이번에는 시계열 데이터를 예측하는 몇가지 방식에 대해 알아보고, 예측의 오차를 정량화하는 방법을 소개합니다.

■ **Table of Contents**

- [시계열 데이터 만들기](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html#id3)
- [Naive Forecast](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html#naive-forecast)
- [지난 30개의 평균값으로 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html#id9)

## 시계열 데이터 만들기

### 예제1

```
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf
from tensorflow import keras

# 1. 기본 스타일 설정
plt.style.use('default')
plt.rcParams['figure.figsize'] = (6, 3)
plt.rcParams['font.size'] = 12


def plot_series(time, series, format="-", start=0, end=None):
    plt.plot(time[start:end], series[start:end], format)
    plt.xlabel("Time")
    plt.ylabel("Value")
    plt.grid(True)

def trend(time, slope=0):
    return slope * time

def seasonal_pattern(season_time):
    return np.where(season_time < 0.4, np.cos(season_time * 2 * np.pi), 1 / np.exp(3 * season_time))

def seasonality(time, period, amplitude=1, phase=0):
    season_time = ((time + phase) % period) / period
    return amplitude * seasonal_pattern(season_time)

def noise(time, noise_level=1, seed=None):
    rnd = np.random.RandomState(seed)
    return rnd.randn(len(time)) * noise_level

time = np.arange(4 * 365 + 1, dtype="float32")
baseline = 10
series = trend(time, 0.1)
baseline = 10
amplitude = 40
slope = 0.05
noise_level = 5

# Create the series
series = baseline + trend(time, slope) + seasonality(time, period=365, amplitude=amplitude)

# Update with noise
series += noise(time, noise_level, seed=42)

plot_series(time, series)
plt.show()
```



이전 페이지에서 소개했던대로 **trend()**, **seasonality()**, **noise()** 함수는 각각 경향성, 계절성, 노이즈를 갖는 시계열 데이터를 반환합니다.

이 함수들을 사용해서 시계열 데이터를 만들고 아래와 같이 시각화했습니다.

![시계열 데이터 예측하기 - 시계열 데이터 만들기](.\Images\basics_of_time_series_forecast_01.png)

시계열 데이터 예측하기 - 시계열 데이터 만들기

### 예제2

```
split_time = 1000
time_train = time[:split_time]
x_train = series[:split_time]

time_valid = time[split_time:]
x_valid = series[split_time:]

plot_series(time_train, x_train)
plt.show()

plot_series(time_valid, x_valid)
plt.show()
```



시계열 데이터의 앞부분 1000개를 훈련용, 그리고 나머지를 검증용 데이터로 분리했습니다.

아래와 같이 두 부분으로 나눠졌습니다.

![시계열 데이터 예측하기 - 시계열 데이터 만들기](.\Images\basics_of_time_series_forecast_02.png)

시계열 데이터 예측하기 - 시계열 데이터 만들기 (Train)

![시계열 데이터 예측하기 - 시계열 데이터 만들기](.\Images\basics_of_time_series_forecast_03.png)

시계열 데이터 예측하기 - 시계열 데이터 만들기 (Validation)

## Naive Forecast

### 예제1

```
naive_forecast = series[split_time - 1: -1]
plot_series(time_valid, x_valid)
plot_series(time_valid, naive_forecast)
```



**naive_forecast**는 말그대로 이전의 데이터를 다음 값으로 예측하는 시계열 데이터입니다.

우선 한 스텝 앞의 데이터를 가져와서 나타내면 아래와 같습니다.

![시계열 데이터 예측하기 - Naive Forecast](.\Images\basics_of_time_series_forecast_04.png)

시계열 데이터 예측하기 - Naive Forecast

### 예제2

```
plot_series(time_valid, x_valid, start=0, end=150)
plot_series(time_valid, naive_forecast, start=1, end=151)
```



그래프 플롯의 시작 시점을 한 스텝 뒤로 했습니다.

앞에서 150개의 데이터를 나타내면 아래와 같습니다.

![시계열 데이터 예측하기 - Naive Forecast](.\Images\basics_of_time_series_forecast_05.png)

시계열 데이터 예측하기 - Naive Forecast

### 예제3

```
print(keras.metrics.mean_squared_error(x_valid, naive_forecast).numpy())
print(keras.metrics.mean_absolute_error(x_valid, naive_forecast).numpy())
```



```
61.827534
5.937908
```



**keras.metrics** 모듈의 **mean_squared_error()**, **mean_absolute_error()** 함수는 두 시계열 데이터 간의 오차를 정량화합니다.

각각 61.827534, 5.937908입니다.

## 지난 30개의 평균값으로 예측하기

### 예제1

```
def moving_average_forecast(series, window_size):
    forecast = []
    for time in range(len(series) - window_size):
        forecast.append(series[time: time + window_size].mean())
    return np.array(forecast)

moving_avg = moving_average_forecast(series, 30)[split_time - 30:]

plot_series(time_valid, x_valid)
plot_series(time_valid, moving_avg)
```



**moving_average_forecast()** 함수는 **window_size** 동안의 평균값을 다음 예측값으로 사용합니다.

아래 그림의 주황색 곡선으로 나타냈습니다.

![시계열 데이터 예측하기 - 지난 30개의 평균값으로 예측하기](.\Images\basics_of_time_series_forecast_06.png)

시계열 데이터 예측하기 - 지난 30개의 평균값으로 예측하기

### 예제2

```
print(keras.metrics.mean_squared_error(x_valid, moving_avg).numpy())
print(keras.metrics.mean_absolute_error(x_valid, moving_avg).numpy())
```



```
106.674576
7.1424184
```



**mean_squared_error()**, **mean_absolute_error()** 함수로 예측의 오차를 확인해보면 각각 106.674576와 7.1424184입니다.



### 이전글/다음글

이전글 : [시계열 데이터 기초](https://codetorial.net/tensorflow/time_series_forecasting/time_series_data.html)

다음글 : [날씨 데이터셋](https://codetorial.net/tensorflow/time_series_forecasting/weather_dataset.html)



#  날씨 데이터셋

예측에 사용할 시계열 데이터로 독일의 [Max Planck Institute for Biogeochemistry](https://www.bgc-jena.mpg.de/index.php/Main/HomePage)에서 관측한 날씨 데이터셋을 사용합니다.

이 날씨 데이터셋은 2003년 이후 10분 간격으로 관측된 온도, 대기압, 습도와 같은 14종류의 다양한 관측치를 포함하고 있습니다.

편의를 위해 2009년부터 2016년 사이의 데이터만 사용합니다.

## 데이터셋 준비하기

아래의 코드와 같이 필요한 라이브러리들을 불러온 다음, 날씨 데이터셋을 다운로드합니다.

```
import tensorflow as tf
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import os
import pandas as pd

mpl.rcParams['figure.figsize'] = (8, 6)
mpl.rcParams['axes.grid'] = False

zip_path = tf.keras.utils.get_file(
  origin='https://storage.googleapis.com/tensorflow/tf-keras-datasets/jena_climate_2009_2016.csv.zip',
  fname='jena_climate_2009_2016.csv.zip',
  extract=True)
csv_path, _ = os.path.splitext(zip_path)
```



```
Downloading data from https://storage.googleapis.com/tensorflow/tf-keras-datasets/jena_climate_2009_2016.csv.zip
13574144/13568290 [==============================] - 1s 0us/step
```



이제 날씨 데이터가 준비되었습니다.

## 데이터셋 살펴보기

Pandas를 이용해서 csv 형식의 데이터를 잠시 들여다 보겠습니다.

```
df = pd.read_csv(csv_path)
print(df.head())
print(df.columns)
```



```
Date Time  p (mbar)    ...     max. wv (m/s)  wd (deg)
0  01.01.2009 00:10:00    996.52    ...              1.75     152.3
1  01.01.2009 00:20:00    996.57    ...              1.50     136.1
2  01.01.2009 00:30:00    996.53    ...              0.63     171.6
3  01.01.2009 00:40:00    996.51    ...              0.50     198.0
4  01.01.2009 00:50:00    996.51    ...              0.63     214.3

[5 rows x 15 columns]
Index(['Date Time', 'p (mbar)', 'T (degC)', 'Tpot (K)', 'Tdew (degC)',
'rh (%)', 'VPmax (mbar)', 'VPact (mbar)', 'VPdef (mbar)', 'sh (g/kg)',
'H2OC (mmol/mol)', 'rho (g/m\*\*3)', 'wv (m/s)', 'max. wv (m/s)',
'wd (deg)'],
dtype='object')
```



**df.head()**는 DataFrame 객체의 작은 샘플을 보기 위해 사용합니다. 입력한 숫자만큼의 행을 출력하지만 기본적으로 5개의 행을 출력하도록 되어있습니다.

출력 결과를 보면 날짜-시간, 대기압, 온도 등의 순서로 15개의 열 (column)을 갖고, 10분 간격으로 기록된 데이터임을 알 수 있습니다.

따라서 하루에 144 (6×24)개의 데이터들이 있습니다.

뉴럴네트워크 모델을 훈련하는데 사용할 특정한 시간 윈도우의 데이터를 얻기 위해 아래의 함수를 사용합니다.

```
def univariate_data(dataset, start_index, end_index, history_size, target_size):
    data = []
    labels = []

    start_index = start_index + history_size
    if end_index is None:
        end_index = len(dataset) - target_size

    for i in range(start_index, end_index):
        indices = range(i - history_size, i)
        # Reshape data from (history_size,) to (history_size, 1)
        data.append(np.reshape(dataset[indices], (history_size, 1)))
        labels.append(dataset[i+target_size])
    return np.array(data), np.array(labels)
```



**history_size**는 과거 데이터의 크기를 의미합니다.

**target_size**는 모델이 얼마 동안의 미래를 예측할지를 의미합니다.

```
TRAIN_SPLIT = 300000
tf.random.set_seed(13)
```



이제부터 데이터셋의 앞에서부터 300,000개 행의 데이터를 훈련에 사용하고, 이후의 데이터들을 검증 (validation)에 사용합니다.

300,000개의 데이터는 약 2,083일 정도의 데이터입니다.

재현성을 보장하기 위해 시드를 설정합니다.

지금까지 작성한 코드는 아래와 같습니다.

```
import tensorflow as tf
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import os
import pandas as pd

mpl.rcParams['figure.figsize'] = (8, 6)
mpl.rcParams['axes.grid'] = False

zip_path = tf.keras.utils.get_file(
  origin='https://storage.googleapis.com/tensorflow/tf-keras-datasets/jena_climate_2009_2016.csv.zip',
  fname='jena_climate_2009_2016.csv.zip',
  extract=True)
csv_path, _ = os.path.splitext(zip_path)

df = pd.read_csv(csv_path)
print(df.head())
print(df.columns)


def univariate_data(dataset, start_index, end_index, history_size, target_size):
  data = []
  labels = []

  start_index = start_index + history_size
  if end_index is None:
      end_index = len(dataset) - target_size

  for i in range(start_index, end_index):
      indices = range(i - history_size, i)
      # Reshape data from (history_size,) to (history_size, 1)
      data.append(np.reshape(dataset[indices], (history_size, 1)))
      labels.append(dataset[i+target_size])
  return np.array(data), np.array(labels)


TRAIN_SPLIT = 300000
tf.random.set_seed(13)
```





### 이전글/다음글

이전글 : [시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/basics_of_time_series_forecast.html)

다음글 : [단변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_univariate_time_series.html)



# 단변량 시계열 데이터 예측하기

문제를 단순하게 하기 위해서 우선 한 종류의 데이터만 사용해서 예측을 진행해 보겠습니다.

아래의 코드를 이전에 작성한 코드에 이어서 작성합니다.

## 온도 데이터 추출

```
uni_data = df['T (degC)']
uni_data.index = df['Date Time']
print(uni_data.head())
```



```
Date Time
01.01.2009 00:10:00   -8.02
01.01.2009 00:20:00   -8.41
01.01.2009 00:30:00   -8.51
01.01.2009 00:40:00   -8.31
01.01.2009 00:50:00   -8.27
Name: T (degC), dtype: float64
```



이제 온도 데이터만 추출되어서 날짜-시간과 온도, 두 개의 열 (column)을 갖는 데이터 (uni_data)를 얻었습니다.

```
uni_data.plot(subplots=True)
plt.show()
```



시간에 따른 온도의 변화를 그래프로 나타내보면 아래의 그림과 같습니다.

![../_images/dataset_temp.png](.\Images\dataset_temp.png)

## 표준화 (Standardization)

온도 데이터에 대해서, 평균을 빼고 표준편차로 나누어줌으로써 표준화를 진행합니다.

```
uni_data = uni_data.values
uni_train_mean = uni_data[:TRAIN_SPLIT].mean()
uni_train_std = uni_data[:TRAIN_SPLIT].std()
uni_data = (uni_data - uni_train_mean) / uni_train_std  # Standardization

print(uni_data)
```



```
[-1.99766294 -2.04281897 -2.05439744 ... -1.43494935 -1.55883897 -1.62715193]
```



표준화된 데이터가 출력됩니다.

## 첫번째 예측

이제 단변량 모델을 위한 데이터를 생성합니다.

우선 20개의 온도 관측치를 입력하면 다음 시간 스텝의 온도를 예측하도록 합니다.

```
univariate_past_history = 20
univariate_future_target = 0

x_train_uni, y_train_uni = univariate_data(uni_data, 0, TRAIN_SPLIT,
                                         univariate_past_history,
                                         univariate_future_target)
x_val_uni, y_val_uni = univariate_data(uni_data, TRAIN_SPLIT, None,
                                     univariate_past_history,
                                     univariate_future_target)

print('Single window of past history')
print(x_train_uni[0])
print('\n Target temperature to predict')
print(y_train_uni[0])
```



```
Single window of past history
[[-1.99766294]
[-2.04281897]
[-2.05439744]
[-2.0312405 ]
[-2.02660912]
[-2.00113649]
[-1.95134907]
[-1.95134907]
[-1.98492663]
[-2.04513467]
[-2.08334362]
[-2.09723778]
[-2.09376424]
[-2.09144854]
[-2.07176515]
[-2.07176515]
[-2.07639653]
[-2.08913285]
[-2.09260639]
[-2.10418486]]

Target temperature to predict
-2.1041848598100876
```



위의 출력 결과는 univariate_data() 함수가 만든 20개의 과거 온도 데이터와 1개의 목표 예측 온도를 나타냅니다.

아래의 코드를 통해 이 데이터들을 그래프로 플롯하기 위한 함수를 만듭니다.

```
def create_time_steps(length):
    return list(range(-length, 0))


def show_plot(plot_data, delta, title):
    labels = ['History', 'True Future', 'Model Prediction']
    marker = ['.-', 'rx', 'go']
    time_steps = create_time_steps(plot_data[0].shape[0])
    if delta:
        future = delta
    else:
        future = 0

    plt.title(title)
    for i, x in enumerate(plot_data):
        if i:
            plt.plot(future, plot_data[i], marker[i], markersize=10, label=labels[i])
        else:
            plt.plot(time_steps, plot_data[i].flatten(), marker[i], label=labels[i])
    plt.legend()
    plt.axis('auto')
    plt.xlim([time_steps[0], (future+5)*2])
    plt.xlabel('Time-Step')
    return plt
```



create_time_steps() 함수는 데이터의 길이를 이용해서 시간 스텝 값들을 만듭니다.

(참고: [Python 내장함수 - list](http://codetorial.net/python/builtin_function/list.html))

그리고 show_plot() 함수는 이 온도 데이터들을 matplotlib 그래프로 반환합니다.

(참고: [Matplotlib - 파이썬 그래프 플롯 라이브러리](http://codetorial.net/matplotlib/index.html))

그리고 앞에서 만든 훈련용 데이터 [x_train_uni[0], y_train_uni[0]]를 그래프로 나타냅니다.

```
show_plot([x_train_uni[0], y_train_uni[0]], 0, 'Sample Example').show()
```



결과는 아래와 같습니다.

![../_images/prediction_sample.png](.\Images\prediction_sample.png)

파란 마커가 20개의 과거 온도 데이터, 빨간 마커가 예측해야 할 미래의 (실제) 온도 데이터를 나타냅니다.

이제 첫번째 예측을 해보겠습니다.

```
def baseline(history):
    return np.mean(history)


show_plot([x_train_uni[0], y_train_uni[0], baseline(x_train_uni[0])], 0, 'Sample Example').show()
```



baseline 함수는 과거 20개의 데이터를 받아서 그 평균을 반환하는 함수입니다.

이 평균값을 첫번째 예측으로 사용해서 그래프로 나타내보겠습니다.

(참고: [NumPy 다양한 함수들 - numpy.mean](http://codetorial.net/numpy/functions/numpy_mean.html))

[![../_images/prediction_baseline.png](.\Images\prediction_baseline.png)](https://codetorial.net/tensorflow/_images/prediction_baseline.png)

녹색 마커는 과거 온도 데이터의 평균값을 이용해서 예측한 지점을 나타냅니다.

실제 데이터와 꽤 차이가 있음을 알 수 있습니다.

다음 페이지에서는 순환 신경망 (recurrent neural network)을 이용해서 미래의 데이터를 예측해 보겠습니다.

지금까지 작성한 코드는 아래와 같습니다.

```
import tensorflow as tf
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import os
import pandas as pd

mpl.rcParams['figure.figsize'] = (8, 6)
mpl.rcParams['axes.grid'] = False

zip_path = tf.keras.utils.get_file(
    origin='https://storage.googleapis.com/tensorflow/tf-keras-datasets/jena_climate_2009_2016.csv.zip',
    fname='jena_climate_2009_2016.csv.zip',
    extract=True)
csv_path, _ = os.path.splitext(zip_path)

df = pd.read_csv(csv_path)
# print(df.head())
# print(df.columns)


def univariate_data(dataset, start_index, end_index, history_size, target_size):
    data = []
    labels = []

    start_index = start_index + history_size
    if end_index is None:
        end_index = len(dataset) - target_size

    for i in range(start_index, end_index):
        indices = range(i - history_size, i)
        # Reshape data from (history_size,) to (history_size, 1)
        data.append(np.reshape(dataset[indices], (history_size, 1)))
        labels.append(dataset[i+target_size])
    return np.array(data), np.array(labels)


TRAIN_SPLIT = 300000
tf.random.set_seed(13)

uni_data = df['T (degC)']
uni_data.index = df['Date Time']
# print(uni_data.head())

# uni_data.plot(subplots=True)
# plt.show()

uni_data = uni_data.values
uni_train_mean = uni_data[:TRAIN_SPLIT].mean()
uni_train_std = uni_data[:TRAIN_SPLIT].std()
uni_data = (uni_data - uni_train_mean) / uni_train_std  # Standardization

# print(uni_data)

univariate_past_history = 20
univariate_future_target = 0

x_train_uni, y_train_uni = univariate_data(uni_data, 0, TRAIN_SPLIT,
                                           univariate_past_history,
                                           univariate_future_target)
x_val_uni, y_val_uni = univariate_data(uni_data, TRAIN_SPLIT, None,
                                       univariate_past_history,
                                       univariate_future_target)

# print('Single window of past history')
# print(x_train_uni[0])
# print('\n Target temperature to predict')
# print(y_train_uni[0])


def create_time_steps(length):
    return list(range(-length, 0))


def show_plot(plot_data, delta, title):
    labels = ['History', 'True Future', 'Model Prediction']
    marker = ['.-', 'rx', 'go']
    time_steps = create_time_steps(plot_data[0].shape[0])
    if delta:
        future = delta
    else:
        future = 0

    plt.title(title)
    for i, x in enumerate(plot_data):
        if i:
            plt.plot(future, plot_data[i], marker[i], markersize=10, label=labels[i])
        else:
            plt.plot(time_steps, plot_data[i].flatten(), marker[i], label=labels[i])
    plt.legend()
    plt.axis('auto')
    plt.xlim([time_steps[0], (future+5)*2])
    plt.xlabel('Time-Step')
    return plt


# show_plot([x_train_uni[0], y_train_uni[0]], 0, 'Sample Example').show()


def baseline(history):
    return np.mean(history)


show_plot([x_train_uni[0], y_train_uni[0], baseline(x_train_uni[0])], 0, 'Sample Example').show()
```





### 이전글/다음글

이전글 : [날씨 데이터셋](https://codetorial.net/tensorflow/time_series_forecasting/weather_dataset.html)

다음글 : [순환 신경망 사용하기](https://codetorial.net/tensorflow/time_series_forecasting/recurrent_neural_network.html)



# 순환 신경망 사용하기

순환 신경망 (Recurrent Neural Network)은 시계열 데이터에 적합한 형태의 신경망입니다.

순환 신경망은 입력된 데이터들에 대한 정보를 유지하면서 시계열 데이터를 처리합니다.

아래의 코드를 앞에서 작성한 코드에 이어서 작성합니다.

## shuffle, batch, cache

[tf.data](https://www.tensorflow.org/api_docs/python/tf/data?hl=ko)를 이용해서 데이터셋을 shuffle, batch, cache하는 작업을 수행합니다.

```
train_univariate = tf.data.Dataset.from_tensor_slices((x_train_uni, y_train_uni))
train_univariate = train_univariate.cache().shuffle(BUFFER_SIZE).batch(BATCH_SIZE).repeat()

val_univariate = tf.data.Dataset.from_tensor_slices((x_val_uni, y_val_uni))
val_univariate = val_univariate.batch(BATCH_SIZE).repeat()
```



tf.data.Dataset의 [from_tensor_slices()](https://www.tensorflow.org/api_docs/python/tf/data/Dataset?hl=ko#from_tensor_slices)는 주어진 텐서들 ((x_train_uni, y_train_uni))을 첫번째 차원을 따라 슬라이스합니다.

모든 입력 텐서는 첫번째 차원과 같은 크기를 가져야합니다.

[from_tensor_slices()](https://www.tensorflow.org/api_docs/python/tf/data/Dataset?hl=ko#from_tensor_slices)를 사용하는 간단한 예를 보면,

```
x_train_uni = [[[1], [2]], [[3], [4]], [[5], [6]]]
y_train_uni = [0.1, 0.2, 0.3]

train_univariate = tf.data.Dataset.from_tensor_slices((x_train_uni, y_train_uni))
print(train_univariate)
print(list(train_univariate))
```



```
<TensorSliceDataset shapes: ((2, 1), ()), types: (tf.int32, tf.float32)>
[(<tf.Tensor: id=8, shape=(2, 1), dtype=int32, numpy=
array([[1],
       [2]], dtype=int32)>, <tf.Tensor: id=9, shape=(), dtype=float32, numpy=0.1>), (<tf.Tensor: id=10, shape=(2, 1), dtype=int32, numpy=
array([[3],
       [4]], dtype=int32)>, <tf.Tensor: id=11, shape=(), dtype=float32, numpy=0.2>), (<tf.Tensor: id=12, shape=(2, 1), dtype=int32, numpy=
array([[5],
       [6]], dtype=int32)>, <tf.Tensor: id=13, shape=(), dtype=float32, numpy=0.3>)]
```



x_train_uni = [[[1], [2]], [[3], [4]], [[5], [6]]]와 y_train_uni = [0.1, 0.2, 0.3]에 대해,

각각의 텐서를 슬라이스해서 새로운 데이터셋이 만들어졌습니다.

[cache()](https://www.tensorflow.org/api_docs/python/tf/data/Dataset?hl=ko#cache)는 데이터셋을 캐시, 즉 메모리 또는 파일에 보관합니다. 따라서 두번째 이터레이션부터는 캐시된 데이터를 사용합니다.

[shuffle()](https://www.tensorflow.org/api_docs/python/tf/data/Dataset?hl=ko#shuffle)는 데이터셋을 임의로 섞어줍니다. BUFFER_SIZE개로 이루어진 버퍼로부터 임의로 샘플을 뽑고, 뽑은 샘플은 다른 샘플로 대체합니다. 완벽한 셔플을 위해서 전체 데이터셋의 크기에 비해 크거나 같은 버퍼 크기가 요구됩니다.

[batch()](https://www.tensorflow.org/api_docs/python/tf/data/Dataset?hl=ko#batch)는 데이터셋의 항목들을 하나의 배치로 묶어줍니다.

아래의 그림은 배치 작업이 이루어진 후의 데이터가 어떻게 구성되는지 나타냅니다.

![../_images/recurrent_neural_network_01.png](.\Images\recurrent_neural_network_01.png)

(그림: [tensorflow.org](https://www.tensorflow.org/tutorials/structured_data/time_series?hl=ko))

## LSTM (Long Short Term Memory)

LSTM은 순환 신경망 (RNN)의 특수한 형태입니다. 아래의 코드를 통해 간단한 LSTM 모델을 구성합니다.

```
simple_lstm_model = tf.keras.models.Sequential([
    tf.keras.layers.LSTM(8, input_shape=x_train_uni.shape[-2:]),
    tf.keras.layers.Dense(1)
])

simple_lstm_model.compile(optimizer='adam', loss='mae')
```



그리고 모델을 훈련합니다.

```
EVALUATION_INTERVAL = 200
EPOCHS = 10

simple_lstm_model.fit(train_univariate, epochs=EPOCHS,
                      steps_per_epoch=EVALUATION_INTERVAL,
                      validation_data=val_univariate, validation_steps=50)
```



```
Epoch 1/10
200/200 [==============================] - 5s 23ms/step - loss: 0.4075 - val_loss: 0.1351
Epoch 2/10
200/200 [==============================] - 3s 16ms/step - loss: 0.1118 - val_loss: 0.0359
Epoch 3/10
200/200 [==============================] - 3s 14ms/step - loss: 0.0489 - val_loss: 0.0290
Epoch 4/10
200/200 [==============================] - 3s 14ms/step - loss: 0.0443 - val_loss: 0.0258
Epoch 5/10
200/200 [==============================] - 3s 14ms/step - loss: 0.0299 - val_loss: 0.0235
Epoch 6/10
200/200 [==============================] - 3s 14ms/step - loss: 0.0317 - val_loss: 0.0224
Epoch 7/10
200/200 [==============================] - 3s 13ms/step - loss: 0.0286 - val_loss: 0.0206
Epoch 8/10
200/200 [==============================] - 3s 13ms/step - loss: 0.0263 - val_loss: 0.0200
Epoch 9/10
200/200 [==============================] - 3s 13ms/step - loss: 0.0254 - val_loss: 0.0182
Epoch 10/10
200/200 [==============================] - 3s 13ms/step - loss: 0.0228 - val_loss: 0.0173
```



훈련이 이루어졌습니다.

이제 이 간단한 LSTM 모델을 이용한 예측을 확인합니다.

```
for x, y in val_univariate.take(3):
    plot = show_plot([x[0].numpy(), y[0].numpy(),
                      simple_lstm_model.predict(x)[0]], 0, 'Simple LSTM model')
    plot.show()
```



결과는 아래와 같습니다.

![../_images/prediction_lstm_01.png](.\Images\prediction_lstm_01.png)

![../_images/prediction_lstm_02.png](.\Images\prediction_lstm_02.png)

![../_images/prediction_lstm_03.png](.\Images\prediction_lstm_03.png)

간단한 LSTM을 모델을 이용한 예측.

baseline() 함수가 했던 과거 20개의 온도 데이터의 평균을 사용한 예측보다 개선된 결과를 확인할 수 있습니다.

이제 온도 데이터만이 아닌 다변량 시계열 (multivariate time series) 데이터를 다뤄 보겠습니다.

지금까지 작성한 코드는 아래와 같습니다.

```
import tensorflow as tf
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import os
import pandas as pd

mpl.rcParams['figure.figsize'] = (8, 6)
mpl.rcParams['axes.grid'] = False

zip_path = tf.keras.utils.get_file(
    origin='https://storage.googleapis.com/tensorflow/tf-keras-datasets/jena_climate_2009_2016.csv.zip',
    fname='jena_climate_2009_2016.csv.zip',
    extract=True)
csv_path, _ = os.path.splitext(zip_path)

df = pd.read_csv(csv_path)
# print(df.head())
# print(df.columns)


def univariate_data(dataset, start_index, end_index, history_size, target_size):
    data = []
    labels = []

    start_index = start_index + history_size
    if end_index is None:
        end_index = len(dataset) - target_size

    for i in range(start_index, end_index):
        indices = range(i - history_size, i)
        # Reshape data from (history_size,) to (history_size, 1)
        data.append(np.reshape(dataset[indices], (history_size, 1)))
        labels.append(dataset[i+target_size])
    return np.array(data), np.array(labels)


TRAIN_SPLIT = 300000
tf.random.set_seed(13)

uni_data = df['T (degC)']
uni_data.index = df['Date Time']
# print(uni_data.head())

# uni_data.plot(subplots=True)
# plt.show()

uni_data = uni_data.values
uni_train_mean = uni_data[:TRAIN_SPLIT].mean()
uni_train_std = uni_data[:TRAIN_SPLIT].std()
uni_data = (uni_data - uni_train_mean) / uni_train_std  # Standardization

# print(uni_data)

univariate_past_history = 20
univariate_future_target = 0

x_train_uni, y_train_uni = univariate_data(uni_data, 0, TRAIN_SPLIT,
                                           univariate_past_history,
                                           univariate_future_target)
x_val_uni, y_val_uni = univariate_data(uni_data, TRAIN_SPLIT, None,
                                       univariate_past_history,
                                       univariate_future_target)

# print('Single window of past history')
# print(x_train_uni[0])
# print('\n Target temperature to predict')
# print(y_train_uni[0])


def create_time_steps(length):
    return list(range(-length, 0))


def show_plot(plot_data, delta, title):
    labels = ['History', 'True Future', 'Model Prediction']
    marker = ['.-', 'rx', 'go']
    time_steps = create_time_steps(plot_data[0].shape[0])
    if delta:
        future = delta
    else:
        future = 0

    plt.title(title)
    for i, x in enumerate(plot_data):
        if i:
            plt.plot(future, plot_data[i], marker[i], markersize=10, label=labels[i])
        else:
            plt.plot(time_steps, plot_data[i].flatten(), marker[i], label=labels[i])
    plt.legend()
    plt.axis('auto')
    plt.xlim([time_steps[0], (future+5)*2])
    plt.xlabel('Time-Step')
    return plt


# show_plot([x_train_uni[0], y_train_uni[0]], 0, 'Sample Example').show()


def baseline(history):
    return np.mean(history)


# show_plot([x_train_uni[0], y_train_uni[0], baseline(x_train_uni[0])], 0, 'Sample Example').show()

BATCH_SIZE = 256
BUFFER_SIZE = 10000

train_univariate = tf.data.Dataset.from_tensor_slices((x_train_uni, y_train_uni))
train_univariate = train_univariate.cache().shuffle(BUFFER_SIZE).batch(BATCH_SIZE).repeat()

val_univariate = tf.data.Dataset.from_tensor_slices((x_val_uni, y_val_uni))
val_univariate = val_univariate.batch(BATCH_SIZE).repeat()

simple_lstm_model = tf.keras.models.Sequential([
    tf.keras.layers.LSTM(8, input_shape=x_train_uni.shape[-2:]),
    tf.keras.layers.Dense(1)
])

simple_lstm_model.compile(optimizer='adam', loss='mae')

EVALUATION_INTERVAL = 200
EPOCHS = 10

simple_lstm_model.fit(train_univariate, epochs=EPOCHS,
                      steps_per_epoch=EVALUATION_INTERVAL,
                      validation_data=val_univariate, validation_steps=50)

for x, y in val_univariate.take(3):
    plot = show_plot([x[0].numpy(), y[0].numpy(),
                      simple_lstm_model.predict(x)[0]], 0, 'Simple LSTM model')
    plot.show()
```





### 이전글/다음글

이전글 : [단변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_univariate_time_series.html)

다음글 : [다변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_multivariate_time_series.html)



# 다변량 시계열 데이터 예측하기

![multivariate_time_series_data](.\Images\forecast_a_multivariate_time_series_00.png)

각 시간 단위마다 여러 개의 값을 가지는 데이터를 **다변량 시계열 데이터 (Multivariate Time Series Data)**라고 합니다.

시간 단위는 시 (hour), 분 (minute), 초 (second) 또는 월 (month), 연도 (year) 등 다양한 단위를 가질 수 있습니다.

이번에는 TensorFlow를 이용해서 **다변량 시계열 데이터**에 대한 예측을 진행해 보겠습니다.

[날씨 데이터셋](https://codetorial.net/tensorflow/time_series_forecasting/weather_dataset.html) 페이지에서 소개했던 원본 날씨 데이터셋은 14개의 날씨 속성을 갖고 있습니다.

예제에서는 문제를 간단하게 하게 위해 14개 중 **온도 (air temperature), 기압 (atmospheric pressure), 공기밀도 (air density) 데이터** 세가지 속성을 다룹니다.

앞의 예제에서와 같이 데이터 프레임 (df)에서 세 개의 열을 얻습니다.

```
features_considered = ['p (mbar)', 'T (degC)', 'rho (g/m**3)']
features = df[features_considered]
features.index = df['Date Time']
print(features.head())
```



```
                     p (mbar)  T (degC)  rho (g/m\*\*3)
Date Time
01.01.2009 00:10:00    996.52     -8.02       1307.75
01.01.2009 00:20:00    996.57     -8.41       1309.80
01.01.2009 00:30:00    996.53     -8.51       1310.24
01.01.2009 00:40:00    996.51     -8.31       1309.19
01.01.2009 00:50:00    996.51     -8.27       1309.00
```



기압, 온도, 공기밀도의 순서로 데이터가 출력되었습니다.

```
features.plot(subplots=True)
plt.show()
```



그래프로 나타내면 아래와 같습니다.

![../_images/dataset_temp_press_dens.png](.\Images\dataset_temp_press_dens.png)

## 표준화 (Standardization)

앞에서와 마찬가지로, 세가지 종류의 데이터에 대해서 평균을 빼고 표준편차로 나누어줌으로써 표준화를 진행합니다.

```
dataset = features.values
data_mean = dataset[:TRAIN_SPLIT].mean(axis=0)
data_std = dataset[:TRAIN_SPLIT].std(axis=0)
dataset = (dataset-data_mean)/data_std
print(dataset)
```



```
[[ 0.95547359 -1.99766294  2.2350791 ]
 [ 0.96154485 -2.04281897  2.28524007]
 [ 0.95668784 -2.05439744  2.29600633]
 ...
 [ 1.35617678 -1.43494935  1.76136375]
 [ 1.35496252 -1.55883897  1.88786728]
 [ 1.35617678 -1.62715193  1.95686921]]
```



## 한 스텝 예측 (Single step model)

이제 신경망 모델은 주어진 과거의 자료로부터 하나의 포인트를 예측하는 것을 학습합니다.

아래의 multivariate_data 함수는 univariate_data 함수와 비슷하게 동작하지만 step 값이 주어진다면 step에 맞게 데이터를 샘플링합니다.

```
def multivariate_data(dataset, target, start_index, end_index, history_size, target_size, step, single_step=False)
    data = []
    labels = []

    start_index = start_index + history_size
    if end_index is None:
        end_index = len(dataset) - target_size

    for i in range(start_index, end_index):
        indices = range(i - history_size, i, step)
        data.append(dataset[indices])

        if single_step:
            labels.append(target[i + target_size])
        else:
            labels.append(target[i:i + target_size])
    return np.array(data), np.array(labels)
```



```
past_history = 720
future_target = 72
STEP = 6

x_train_single, y_train_single = multivariate_data(dataset, dataset[:, 1], 0, TRAIN_SPLIT, past_history,
                                                   future_target, STEP, single_step=True)
x_val_single, y_val_single = multivariate_data(dataset, dataset[:, 1], TRAIN_SPLIT, None, past_history,
                                               future_target, STEP, single_step=True)

print('Single window of past history : {}'.format(x_train_single[0].shape))
```



```
Single window of past history : (120, 3)
```



past_history는 지난 5일간의 데이터입니다. (720 = 5일 * 144개, 144 = 6개 * 24시간)

future_target은 72시간 스텝 이후 (12시간)를 예측할 것임을 의미합니다.

STEP은 샘플링 간격을 의미합니다.

```
train_data_single = tf.data.Dataset.from_tensor_slices((x_train_single, y_train_single))
train_data_single = train_data_single.cache().shuffle(BUFFER_SIZE).batch(BATCH_SIZE).repeat()

val_data_single = tf.data.Dataset.from_tensor_slices((x_val_single, y_val_single))
val_data_single = val_data_single.batch(BATCH_SIZE).repeat()

single_step_model = tf.keras.models.Sequential()
single_step_model.add(tf.keras.layers.LSTM(32, input_shape=x_train_single.shape[-2:]))
single_step_model.add(tf.keras.layers.Dense(1))
single_step_model.compile(optimizer=tf.keras.optimizers.RMSprop(), loss='mae')

for x, y in val_data_single.take(1):
    print(single_step_model.predict(x).shape)

single_step_history = single_step_model.fit(train_data_single, epochs=EPOCHS,
                                            steps_per_epoch=EVALUATION_INTERVAL,
                                            validation_data=val_data_single,
                                            validation_steps=50)


def plot_train_history(history, title):
    loss = history.history['loss']
    val_loss = history.history['val_loss']

    epochs = range(len(loss))

    plt.figure()

    plt.plot(epochs, loss, 'b', label='Training loss')
    plt.plot(epochs, val_loss, 'r', label='Validation loss')
    plt.title(title)
    plt.legend()

    plt.show()

plot_train_history(single_step_history,
                   'Single Step Training and Validation Loss')
```



다변량 시계열 데이터의 한 스텝 예측을 위한 인공신경망 모델을 구성하고,

10회 에포크의 학습을 진행합니다.

plot_train_history 함수는 학습 과정을 그래프로 표헌합니다.

결과는 아래와 같습니다.

![../_images/single_step_training_and_validation_loss.png](.\Images\single_step_training_and_validation_loss.png)

```
for x, y in val_data_single.take(3):
    plot = show_plot([x[0][:, 1].numpy(), y[0].numpy(),
                      single_step_model.predict(x)[0]], 12,
                     'Single Step Prediction')
    plot.show()
```



모델이 학습되었다면 한 스텝 예측을 진행할 수 있습니다.

모델에는 지난 5일 간 매시간 측정된 세가지 종류의 각 120개의 데이터가 주어지고, 12시간 후의 온도를 예측하는 것을 목표로 합니다.

결과는 아래와 같습니다.

![../_images/single_step_prediction_01.png](.\Images\single_step_prediction_01.png)

![../_images/single_step_prediction_02.png](.\Images\single_step_prediction_02.png)

![../_images/single_step_prediction_03.png](.\Images\single_step_prediction_03.png)

```
past_history = 720
future_target = 72
STEP = 6

x_train_multi, y_train_multi = multivariate_data(dataset, dataset[:, 1], 0,
                                                 TRAIN_SPLIT, past_history, future_target, STEP)
x_val_multi, y_val_multi = multivariate_data(dataset, dataset[:, 1],
                                             TRAIN_SPLIT, None, past_history, future_target, STEP)

print('Single window of past history : {}'.format(x_train_multi[0].shape))
print('\n Target temperature to predict : {}'.format(y_train_multi[0].shape))
```



```
Single window of past history : (120, 3)

 Target temperature to predict : (72,)
```





### 이전글/다음글

이전글 : [순환 신경망 사용하기](https://codetorial.net/tensorflow/time_series_forecasting/recurrent_neural_network.html)

다음글 : [22. 자연어 처리하기 1](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html)



#  22. 자연어 처리하기 1

이제 TensorFlow를 이용해서 자연어를 처리하는 방법에 대해서 알아봅니다.

이 페이지에서는 우선 [tensorflow.keras.preprocessing.text](https://www.tensorflow.org/api_docs/python/tf/keras/preprocessing/text/) 모듈의 **Tokenizer** 클래스를 사용해서

**텍스트를 단어 기반으로 토큰화**함으로써 Neural Network에 사용하기 적합한 형태로 변환하는 방법에 대해 소개합니다.

순서는 아래와 같습니다.

- [문자 기반 인코딩](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html#id2)
- [단어 기반 인코딩](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html#id3)
- [텍스트를 시퀀스로 변환하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html#id4)
- [토큰화되지 않은 단어 처리하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html#id5)
- [패딩 설정하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html#id6)

## 문자 기반 인코딩

지금까지 이미지 인식 예제에서는 이미지를 픽셀 단위의 숫자 기반으로 입력값으로 사용했습니다.

ASCII 코드를 이용해서 **문자 기반 인코딩 (encoding)**을 하면 어떠한 문제가 있을까요.

같은 문자로 이루어진 두 단어 ‘LISTEN’과 ‘SILENT’를 아래와 같은 ASCII 코드로 인코딩할 수 있습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_00.png)

전혀 다른 의미를 갖는 이 두 단어는 같은 문자들의 집합이기 때문에 비슷한 숫자들로 인코딩되고,

따라서 Neural Network의 입력으로 사용하기에는 부적합합니다.

## 단어 기반 인코딩

아래의 예제는 TensorFlow를 이용해서 두 문장 ‘I love my dog’과 ‘I love my cat’을 단어 기반으로 인코딩하는 방법을 보여줍니다.

```
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.preprocessing.text import Tokenizer

sentences = [
  'I love my dog',
  'I love my cat'
]

tokenizer = Tokenizer(num_words = 100)
tokenizer.fit_on_texts(sentences)
word_index = tokenizer.word_index
print(word_index)
```



```
{'i': 1, 'love': 2, 'my': 3, 'dog': 4, 'cat': 5}
```



TensorFlow와 Keras를 이용해서 단어를 인코딩하는 다양한 방식이 있지만 이 예제에서는 **Tokenizer**를 사용합니다.

**Tokenizer**는 문장으로부터 단어를 토큰화하고 숫자에 대응시키는 딕셔너리를 사용할 수 있도록 합니다.

예제에서 우선 두 개의 문장을 리스트 (sentences)의 형태로 만들었습니다.

**Tokenizer**의 인스턴스를 만들면서 num_words 파라미터를 이용해서 단어의 개수를 제한했습니다.

가장 자주 사용되는 num_words-1 개의 단어가 고려됩니다.

**fit_on_texts()** 메서드는 문자 데이터를 입력받아서 리스트의 형태로 변환합니다.

tokenizer의 **word_index** 속성은 단어와 숫자의 키-값 쌍을 포함하는 딕셔너리를 반환합니다.

출력 결과를 보면 대문자 ‘I’는 소문자 ‘i’로 변환된 것을 알 수 있습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_01.png)

```
sentences = [
  'I love my dog',
  'I love my cat',
  'You love my dog!'
]

tokenizer = Tokenizer(num_words = 100)
tokenizer.fit_on_texts(sentences)
word_index = tokenizer.word_index
print(word_index)
```



```
{'love': 1, 'my': 2, 'i': 3, 'dog': 4, 'cat': 5, 'you': 6}
```



sentences 리스트에 ‘You love my dog!’을 하나 추가했습니다.

출력 결과를 보면 ‘you’ 단어의 키가 추가되었고, ‘dog!’ 부분의 느낌표가 제거되어서 같은 단어로 인덱싱되었습니다.

느낌표, 마침표와 같은 구두점은 인코딩에 영향을 주지 않습니다.

## 텍스트를 시퀀스로 변환하기

앞에서 **Tokenizer**를 이용해서 문장을 토큰화했습니다.

이제 **texts_to_sequences()** 메서드를 이용해서 이러한 단어들을 시퀀스의 형태로 변환해 보겠습니다.

```
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.preprocessing.text import Tokenizer

sentences = [
  'I love my dog',
  'I love my cat',
  'You love my dog!',
  'Do you think my dog is amazing?'
]

tokenizer = Tokenizer(num_words = 100)
tokenizer.fit_on_texts(sentences)
word_index = tokenizer.word_index

sequences = tokenizer.texts_to_sequences(sentences)

print(word_index)
print(sequences)
```



```
{'my': 1, 'love': 2, 'dog': 3, 'i': 4, 'you': 5, 'cat': 6, 'do': 7, 'think': 8, 'is': 9, 'amazing': 10}
[[4, 2, 1, 3], [4, 2, 1, 6], [5, 2, 1, 3], [7, 5, 8, 1, 3, 9, 10]]
```



**texts_to_sequences()** 메서드는 텍스트 안의 단어들을 숫자의 시퀀스의 형태로 변환합니다.

word_index를 출력해보면 ‘amazing’, ‘think’와 같은 새로운 토큰화된 단어들을 포함하는 딕셔너리를 확인할 수 있습니다.

sequences를 출력해보면 네 개의 문장이 숫자의 시퀀스로 변환된 것을 확인할 수 있습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_02.png)

```
test_sentences = [
  'i really love my dog',
  'my dog loves my friend'
]

test_sequences = tokenizer.texts_to_sequences(test_sentences)
print(test_sequences)
```



```
[[4, 2, 1, 3], [1, 3, 1]]
```



새로운 단어들이 포함된 두 개의 문장을 **texts_to_sequences()**를 이용해서 시퀀스로 변환해보면,

미리 토큰화되어 있지 않은 ‘really’, ‘loves’와 같은 단어들은 숫자들의 시퀀스에 포함되지 않은 것을 알 수 있습니다.

## 토큰화되지 않은 단어 처리하기

자연어 처리에 있어서 많은 단어들을 학습하게 되고, 미리 토큰화되어 있지 않은 단어들을 만나게 됩니다.

앞의 예제와 같이 이런 단어들을 무시하기보다 **oov_token** 인자를 사용해서 특수한 값으로 처리할 수 있습니다.

```
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.preprocessing.text import Tokenizer

sentences = [
    'I love my dog',
    'I love my cat',
    'You love my dog!',
    'Do you think my dog is amazing?'
]

tokenizer = Tokenizer(num_words = 100, oov_token="<OOV>")
tokenizer.fit_on_texts(sentences)
word_index = tokenizer.word_index

sequences = tokenizer.texts_to_sequences(sentences)

test_sentences = [
    'i really love my dog',
    'my dog loves my friend'
]

test_sequences = tokenizer.texts_to_sequences(test_sentences)
print(test_sequences)
print(word_index)
```



```
[[5, 1, 3, 2, 4], [2, 4, 1, 2, 1]]
{'<OOV>': 1, 'my': 2, 'love': 3, 'dog': 4, 'i': 5, 'you': 6, 'cat': 7, 'do': 8, 'think': 9, 'is': 10, 'amazing': 11}
```



**Tokenizer**의 **oov_token** 인자를 사용하면 미리 인덱싱하지 않은 단어들은 ‘<OOV>’로 인덱싱됩니다.

## 패딩 설정하기

서로 다른 개수의 단어로 이루어진 문장을 같은 길이로 만들어주기 위해 패딩을 사용할 수 있습니다.

패딩을 사용하기 위해서는 [tensorflow.keras.preprocessing.sequence](https://www.tensorflow.org/api_docs/python/tf/keras/preprocessing/sequence) 모듈의 **pad_sequences** 함수를 사용합니다.

### pad_sequences() 함수

```
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences

sentences = [
  'I love my dog',
  'I love my cat',
  'You love my dog!',
  'Do you think my dog is amazing?'
]

tokenizer = Tokenizer(num_words = 100, oov_token="<OOV>")
tokenizer.fit_on_texts(sentences)
word_index = tokenizer.word_index

sequences = tokenizer.texts_to_sequences(sentences)
padded = pad_sequences(sequences)

print(word_index)
print(sequences)
print(padded)
```



```
{'<OOV>': 1, 'my': 2, 'love': 3, 'dog': 4, 'i': 5, 'you': 6, 'cat': 7, 'do': 8, 'think': 9, 'is': 10, 'amazing': 11}
[[5, 3, 2, 4], [5, 3, 2, 7], [6, 3, 2, 4], [8, 6, 9, 2, 4, 10, 11]]
[[ 0  0  0  5  3  2  4]
[ 0  0  0  5  3  2  7]
[ 0  0  0  6  3  2  4]
[ 8  6  9  2  4 10 11]]
```



sequences는 정수의 시퀀스로 변환된 텍스트 문장입니다.

**pad_sequences** 함수에 이 시퀀스를 입력하면 숫자 0을 이용해서 같은 길이의 시퀀스로 변환합니다.

가장 긴 시퀀스의 길이가 7이기 때문에 모두 같은 길이의 시퀀스를 포함하는 NumPy 어레이로 변환되었습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_03.png)

### padding 파라미터

```
padded = pad_sequences(sequences, padding='post')

print(padded)
```



```
[[ 5  3  2  4  0  0  0]
[ 5  3  2  7  0  0  0]
[ 6  3  2  4  0  0  0]
[ 8  6  9  2  4 10 11]]
```



**padding** 파라미터를 ‘post’로 지정하면 시퀀스의 뒤에 패딩이 채워집니다. 디폴트는 ‘pre’입니다.

### maxlen 파라미터

```
padded = pad_sequences(sequences, padding='pre', maxlen=6)

print(padded)
```



```
[[ 0  0  5  3  2  4]
[ 0  0  5  3  2  7]
[ 0  0  6  3  2  4]
[ 6  9  2  4 10 11]]
```



**maxlen** 파라미터는 시퀀스의 최대 길이를 제한합니다.

최대 길이를 6으로 지정하면 길이를 넘는 시퀀스는 잘라냅니다.

### truncating 파라미터

```
padded = pad_sequences(sequences, padding='pre', maxlen=6, truncating='post')

print(padded)
```



```
[[ 0  0  5  3  2  4]
[ 0  0  5  3  2  7]
[ 0  0  6  3  2  4]
[ 8  6  9  2  4 10]]
```



**truncating** 파라미터는 최대 길이를 넘는 시퀀스를 잘라낼 위치를 지정합니다.

‘post’로 지정하면 뒷부분을 잘라냅니다.



### 이전글/다음글

이전글 : [다변량 시계열 데이터 예측하기](https://codetorial.net/tensorflow/time_series_forecasting/forecast_a_multivariate_time_series.html)

다음글 : [23. 자연어 처리하기 2](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html)



# 23. 자연어 처리하기 2

이제 자연어 처리 데이터셋 [News headlines dataset for sarcasm detection](https://rishabhmisra.github.io/publications/)을 이용해서 앞에서 다루었던 텍스트 토큰화를 진행해 보겠습니다.

이 페이지에서는 **Google Colab (Colaboratory)**을 사용해서 웹브라우저 상에서 특별한 환경 구성없이 머신러닝 코드를 작성합니다.

**Google Colab**에 대해서는 [Google Colab 소개 페이지](https://colab.research.google.com/notebooks/intro.ipynb)를 참고하세요.

순서는 아래와 같습니다.

- [데이터셋 다운로드하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html#id2)
- [데이터셋 준비하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html#id3)
- [데이터 토큰화하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html#id4)

## 데이터셋 다운로드하기

```
!wget --no-check-certificate \
  https://storage.googleapis.com/laurencemoroney-blog.appspot.com/sarcasm.json \
  -O /tmp/sarcasm.json
```



우선 Colab 코드셀에 위의 명령어를 입력해서 데이터셋을 다운로드합니다.

아래 그림과 같이 페이지 왼쪽의 목차 탭을 열어서 tmp 폴더에

sarcasm.json 파일이 다운로드되어 있는지 확인합니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_04.png)

## 데이터셋 준비하기

```
import json

with open("/tmp/sarcasm.json", 'r') as f:
  datastore = json.load(f)

sentences = []
labels = []
urls = []
for item in datastore:
  sentences.append(item['headline'])
  labels.append(item['is_sarcastic'])
  urls.append(item['article_link'])

print(sentences)
print(labels)
```



```
["former versace store clerk sues over secret 'black code' for minority shoppers", "the 'roseanne' revival catches up ...
[0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, ...
```



json 형식의 데이터를 불러오기 위해 파이썬 기본 내장 패키지인 json 모듈을 사용합니다.

News headlines dataset for sarcasm detection 데이터셋은 아래와 같은 세 가지 속성에 대한 데이터를 리스트 형태로 제공합니다.

- **headline** : 뉴스 기사의 헤드라인.
- **is_sarcastic** : 뉴스 헤드라인이 Sarcastic하다면 1, 그렇지 않다면 0.
- **article_link** : 뉴스 기사 원문 링크.

각각의 데이터를 sentences, labels, urls 리스트에 담아줍니다.

sentences와 labels를 출력하면 아래와 같습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_05.png)

## 데이터 토큰화하기

```
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences

tokenizer = Tokenizer(oov_token="<OOV>")
tokenizer.fit_on_texts(sentences)

word_index = tokenizer.word_index
print(len(word_index))
print(word_index)

sequences = tokenizer.texts_to_sequences(sentences)
padded = pad_sequences(sequences, padding='post')
print(padded[0])
print(padded.shape)
```



```
29657
{'<OOV>': 1, 'to': 2, 'of': 3, 'the': 4, 'in': 5, 'for': 6, 'a': 7, 'on': 8, 'and': 9, 'with': 10, 'is': 11, 'new': 12, ...
[  308 15115   679  3337  2298    48   382  2576 15116     6  2577  8434
     0     0     0     0     0     0     0     0     0     0     0     0
     0     0     0     0     0     0     0     0     0     0     0     0
     0     0     0     0]
(26709, 40)
```



앞 페이지에서 다뤘던 **Tokenizer**를 이용해서 헤드라인 문장 (sentences)을 토큰화합니다.

word_index를 출력해보면, 총 29657개의 단어가 토큰화되었음을 알 수 있습니다.

**texts_to_sequences**를 이용해서 문장을 숫자의 시퀀스로 변환하고,

**pad_sequences**를 이용해서 시퀀스에 패딩을 설정합니다.

첫번째 시퀀스 (padded[0])를 출력해보면, 12개의 단어로 이루어진 문장이 길이 40의 시퀀스로 변환되었음을 알 수 있습니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_06.png)


### 이전글/다음글

이전글 : [22. 자연어 처리하기 1](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html)

다음글 : [24. 자연어 처리하기 3](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html)



#  24. 자연어 처리하기 3

지금까지 **Tokenizer**를 이용해서 텍스트 토큰화를 진행했습니다.

이제 **임베딩 (Embedding)**에 대해 소개합니다. 임베딩은 단어에 대한 토큰을 고차원 공간의 벡터로 매핑 (mapping)하는 방법입니다. 레이블된 예제를 이용해서 비슷한 의미를 갖는 단어들이 벡터 공간에서 비슷한 방향을 가리키도록 벡터를 조절할 수 있습니다.

임베딩은 텍스트에서 감정 (sentiment)을 훈련하는 과정의 출발점이 됩니다.

‘positive’와 ‘negative’로 레이블된 영화 리뷰 텍스트를 가지고 Neural Network를 훈련하면서, 문장 안에서 어떤 단어들이 긍정적인, 부정적인 의미를 가지는지 학습할 수 있습니다.

이 페이지에서는 **Google Colab (Colaboratory)**을 사용해서 웹브라우저 상에서 특별한 환경 구성없이 머신러닝 코드를 작성합니다.

**Google Colab**에 대해서는 [Google Colab 소개 페이지](https://colab.research.google.com/notebooks/intro.ipynb)를 참고하세요.

순서는 아래와 같습니다.

- [데이터셋 준비하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id2)
- [데이터셋 살펴보기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id3)
- [리뷰 문장 토큰화하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id4)
- [모델 구성하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id6)
- [모델 컴파일하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id7)
- [모델 훈련하기](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html#id8)

## 데이터셋 준비하기

[IMDB 리뷰 데이터셋](http://ai.stanford.edu/~amaas/data/sentiment/)은 텍스트로부터 감정을 분석하고, 학습 및 분류하기 위한 데이터셋입니다.

긍정적 (positive) 또는 부정적 (negative)로 분류된 50,000개의 영화 리뷰 텍스트를 포함하며, 25,000개는 훈련에 사용되고, 25,000개는 테스트에 사용합니다.

우선 **TensorFlow Datasets (TFDS)**을 설치합니다.

```
!pip install -q tensorflow-datasets
```



**Google Colab**을 사용하는 경우 **TensorFlow Datasets**이 이미 설치되어 있습니다. 자신의 환경에서 **TensorFlow Datasets**이 설치되어 있지 않다면 명령 프롬프트에서 아래의 명령어를 통해 **TensorFlow Datasets**를 설치합니다.

```
import tensorflow_datasets as tfds
imdb, info = tfds.load('imdb_reviews', with_info=True, as_supervised=True)
```



**TensorFlow Datasets**의 설치가 완료되었다면, 아래와 같이 tensorflow_datasets 모듈을 불러옵니다.

**Google Colab** 환경에서 처음으로 모듈을 불러와서 코드셀을 실행했을 때, 아래와 같이 출력된다면 데이터셋이 준비된 것입니다.

![Natural_Language_Processing_in_Tensorflow](.\Images\natural_language_processing_in_tensorflow_07.png)

## 데이터셋 살펴보기

이제 준비한 영화 리뷰 데이터셋을 훈련과 테스트에 적절한 형태로 처리하고, 첫번째 데이터를 출력해 보겠습니다.

```
import numpy as np

train_data, test_data = imdb['train'], imdb['test']
train_sentences = []
train_labels = []
test_sentences = []
test_labels = []

for s, l in train_data:
  train_sentences.append(str(s.numpy()))
  train_labels.append(l.numpy())

for s, l in test_data:
  test_sentences.append(str(s.numpy()))
  test_labels.append(l.numpy())

print(train_sentences[0])
print(train_labels[0])
print(test_sentences[0])
print(test_labels[0])
```



```
b"This was an absolutely terrible movie. Don't be lured in by Christopher Walken or Michael Ironside. Both are great actors, but this must simply be their worst role in history. Even their great acting could not redeem this movie's ridiculous storyline. This movie is an early nineties US propaganda piece. The most pathetic scenes were those when the Columbian rebels were making their cases for revolutions. Maria Conchita Alonso appeared phony, and her pseudo-love affair with Walken was nothing but a pathetic emotional plug in a movie that was devoid of any real meaning. I am disappointed that there are movies like this, ruining actor's like Christopher Walken's good name. I could barely sit through it."
0
b"There are films that make careers. For George Romero, it was NIGHT OF THE LIVING DEAD; for Kevin Smith, CLERKS; for Robert Rodriguez, EL MARIACHI. Add to that list Onur Tukel's absolutely amazing DING-A-LING-LESS. Flawless film-making, and as assured and as professional as any of the aforementioned movies. I haven't laughed this hard since I saw THE FULL MONTY. (And, even then, I don't think I laughed quite this hard... So to speak.) Tukel's talent is considerable: DING-A-LING-LESS is so chock full of double entendres that one would have to sit down with a copy of this script and do a line-by-line examination of it to fully appreciate the, uh, breadth and width of it. Every shot is beautifully composed (a clear sign of a sure-handed director), and the performances all around are solid (there's none of the over-the-top scenery chewing one might've expected from a film like this). DING-A-LING-LESS is a film whose time has come."
1
```



리스트 train_sentences, train_labels, test_sentences, test_labels는 각각 훈련과 테스트에 사용할 리뷰 텍스트 문장과 레이블입니다.

리뷰가 긍정적이라면 1, 부정적이라면 0으로 레이블되어 있습니다.

```
train_labels = np.array(train_labels)
test_labels = np.array(test_labels)
```



[np.array()](https://codetorial.net/numpy/functions/numpy_array.html)를 사용해서 레이블을 NumPy 어레이로 변환해줍니다.

## 리뷰 문장 토큰화하기

이제 자연어 처리 [첫 페이지](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_01.html)에서 설명했던대로 문장을 토큰화하고 시퀀스로 변환합니다.

```
vocab_size = 10000
embedding_dim = 16
max_length = 120
trunc_type = 'post'
oov_tok = "<OOV>"

from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences

tokenizer = Tokenizer(num_words=vocab_size, oov_token=oov_tok)
tokenizer.fit_on_texts(train_sentences)
word_index = tokenizer.word_index

sequences = tokenizer.texts_to_sequences(train_sentences)
padded = pad_sequences(sequences, maxlen=max_length, truncating=trunc_type)

test_sequences = tokenizer.texts_to_sequences(test_sentences)
test_padded = pad_sequences(test_sequences, maxlen=max_length)

print(sequences[0])
print(padded[0])

print(test_sequences[0])
print(test_padded[0])
```



```
[59, 12, 14, 35, 439, 400, 18, 174, 29, 1, 9, 33, 1378, 3401, 42, 496, 1, 197, 25, 88, 156, 19, 12, 211, 340, 29, 70, 248, 213, 9, 486, 62, 70, 88, 116, 99, 24, 5740, 12, 3317, 657, 777, 12, 18, 7, 35, 406, 8228, 178, 2477, 426, 2, 92, 1253, 140, 72, 149, 55, 2, 1, 7525, 72, 229, 70, 2962, 16, 1, 2880, 1, 1, 1506, 4998, 3, 40, 3947, 119, 1608, 17, 3401, 14, 163, 19, 4, 1253, 927, 7986, 9, 4, 18, 13, 14, 4200, 5, 102, 148, 1237, 11, 240, 692, 13, 44, 25, 101, 39, 12, 7232, 1, 39, 1378, 1, 52, 409, 11, 99, 1214, 874, 145, 10]
[   0    0   59   12   14   35  439  400   18  174   29    1    9   33
1378 3401   42  496    1  197   25   88  156   19   12  211  340   29
 70  248  213    9  486   62   70   88  116   99   24 5740   12 3317
657  777   12   18    7   35  406 8228  178 2477  426    2   92 1253
140   72  149   55    2    1 7525   72  229   70 2962   16    1 2880
  1    1 1506 4998    3   40 3947  119 1608   17 3401   14  163   19
  4 1253  927 7986    9    4   18   13   14 4200    5  102  148 1237
 11  240  692   13   44   25  101   39   12 7232    1   39 1378    1
 52  409   11   99 1214  874  145   10]
[59, 44, 25, 109, 13, 97, 4115, 16, 742, 4370, 10, 14, 316, 5, 2, 593, 354, 16, 1864, 1212, 1, 16, 680, 7499, 5595, 1, 773, 6, 13, 1037, 1, 1, 439, 491, 1, 4, 1, 334, 3610, 20, 229, 3, 15, 5796, 3, 15, 1646, 15, 102, 5, 2, 3597, 101, 11, 1450, 1528, 12, 251, 235, 11, 216, 2, 377, 6429, 3, 62, 95, 11, 174, 105, 11, 1528, 180, 12, 251, 37, 6, 1144, 1, 682, 7, 4452, 1, 4, 1, 334, 7, 37, 8367, 377, 5, 1420, 1, 13, 30, 64, 28, 6, 874, 181, 17, 4, 1050, 5, 12, 224, 3, 83, 4, 353, 33, 353, 5229, 5, 10, 6, 1340, 1160, 2, 5738, 1, 3, 1, 5, 10, 175, 328, 7, 1319, 3989, 4, 798, 1946, 5, 4, 250, 2710, 158, 3, 2, 361, 31, 187, 25, 1170, 499, 610, 5, 2, 122, 2, 356, 1398, 7725, 30, 1, 881, 38, 4, 20, 39, 12, 1, 4, 1, 334, 7, 4, 20, 634, 60, 48, 214]
[  11 1450 1528   12  251  235   11  216    2  377 6429    3   62   95
 11  174  105   11 1528  180   12  251   37    6 1144    1  682    7
4452    1    4    1  334    7   37 8367  377    5 1420    1   13   30
 64   28    6  874  181   17    4 1050    5   12  224    3   83    4
353   33  353 5229    5   10    6 1340 1160    2 5738    1    3    1
  5   10  175  328    7 1319 3989    4  798 1946    5    4  250 2710
158    3    2  361   31  187   25 1170  499  610    5    2  122    2
356 1398 7725   30    1  881   38    4   20   39   12    1    4    1
334    7    4   20  634   60   48  214]
```



우선 토큰화할 단어의 수, 최대 길이와 같은 하이퍼 파라미터들을 먼저 지정하고,

**Tokenizer**와 **pad_sequences()** 함수를 불러옵니다.

**fit_on_texts**를 이용해서 단어를 토큰화하고, **texts_to_sequences**를 이용해서 숫자의 시퀀스로 다시 변환합니다.

**pad_sequences**는 Neural Network의 훈련에 적합하도록 이 시퀀스의 길이를 일정하게 만들어줍니다.

## 모델 구성하기

```
import tensorflow as tf

model = tf.keras.Sequential([
  tf.keras.layers.Embedding(vocab_size, embedding_dim, input_length=max_length),
  tf.keras.layers.Flatten(),
  tf.keras.layers.Dense(6, activation='relu'),
  tf.keras.layers.Dense(1, activation='sigmoid')
])

model.summary()
```



```
Model: "sequential"
_________________________________________________________________
Layer (type)                 Output Shape              Param #
=================================================================
embedding (Embedding)      (None, 120, 16)           160000
_________________________________________________________________
flatten (Flatten)          (None, 1920)              0
_________________________________________________________________
dense (Dense)              (None, 6)                 11526
_________________________________________________________________
dense (Dense)              (None, 1)                 7
=================================================================
Total params: 171,533
Trainable params: 171,533
Non-trainable params: 0
_________________________________________________________________
```



**Embedding** 레어이는 텍스트의 감정 분석에 있어서 핵심적인 부분입니다.

임베딩의 결과는 (vocab_size, embedding_dim)의 형태를 갖는 2차원 어레이가 되고,

이미지 분류 문제에서와 마찬가지로 **Flatten** 레이어를 사용해서 이 2차원 어레이를 1차원으로 변환합니다.

## 모델 컴파일하기

```
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
```



**compile()** 메서드를 이용해서 손실 함수와 옵티마이저를 지정해줍니다.

## 모델 훈련하기

```
num_epochs = 10
model.fit(padded, train_labels, epochs=num_epochs,
        validation_data=(test_padded, test_labels))
```



```
Epoch 1/10
782/782 [==============================] - 5s 7ms/step - loss: 0.4902 - accuracy: 0.7495 - val_loss: 0.3442 - val_accuracy: 0.8478
Epoch 2/10
782/782 [==============================] - 5s 7ms/step - loss: 0.2430 - accuracy: 0.9068 - val_loss: 0.3682 - val_accuracy: 0.8388
Epoch 3/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1016 - accuracy: 0.9715 - val_loss: 0.4751 - val_accuracy: 0.8201
Epoch 4/10
782/782 [==============================] - 5s 6ms/step - loss: 0.0269 - accuracy: 0.9962 - val_loss: 0.5274 - val_accuracy: 0.8249
Epoch 5/10
782/782 [==============================] - 5s 6ms/step - loss: 0.0058 - accuracy: 0.9997 - val_loss: 0.5924 - val_accuracy: 0.8276
Epoch 6/10
782/782 [==============================] - 5s 6ms/step - loss: 0.0019 - accuracy: 1.0000 - val_loss: 0.6433 - val_accuracy: 0.8300
Epoch 7/10
782/782 [==============================] - 5s 7ms/step - loss: 9.1601e-04 - accuracy: 1.0000 - val_loss: 0.6944 - val_accuracy: 0.8282
Epoch 8/10
782/782 [==============================] - 5s 6ms/step - loss: 5.0897e-04 - accuracy: 1.0000 - val_loss: 0.7287 - val_accuracy: 0.8299
Epoch 9/10
782/782 [==============================] - 5s 6ms/step - loss: 2.8224e-04 - accuracy: 1.0000 - val_loss: 0.7691 - val_accuracy: 0.8293
Epoch 10/10
782/782 [==============================] - 5s 6ms/step - loss: 1.6944e-04 - accuracy: 1.0000 - val_loss: 0.8078 - val_accuracy: 0.8293
```



10회의 에포크 (epoch) 동안 훈련을 거치고 나면,

훈련 데이터에 대해 1.0의 정확도, 테스트 데이터에 대해 0.8293의 정확도를 보이는 것을 알 수 있습니다.

```
import tensorflow as tf

model = tf.keras.Sequential([
  tf.keras.layers.Embedding(vocab_size, embedding_dim, input_length=max_length),
  # tf.keras.layers.Flatten(),
  tf.keras.layers.GlobalAveragePooling1D(),
  tf.keras.layers.Dense(6, activation='relu'),
  tf.keras.layers.Dense(1, activation='sigmoid')
])

model.summary()
```



```
Epoch 1/10
782/782 [==============================] - 6s 7ms/step - loss: 0.5611 - accuracy: 0.7525 - val_loss: 0.4015 - val_accuracy: 0.8394
Epoch 2/10
782/782 [==============================] - 6s 8ms/step - loss: 0.3441 - accuracy: 0.8585 - val_loss: 0.3357 - val_accuracy: 0.8572
Epoch 3/10
782/782 [==============================] - 5s 6ms/step - loss: 0.2784 - accuracy: 0.8872 - val_loss: 0.3306 - val_accuracy: 0.8571
Epoch 4/10
782/782 [==============================] - 5s 6ms/step - loss: 0.2410 - accuracy: 0.9070 - val_loss: 0.3413 - val_accuracy: 0.8545
Epoch 5/10
782/782 [==============================] - 5s 6ms/step - loss: 0.2142 - accuracy: 0.9189 - val_loss: 0.3589 - val_accuracy: 0.8500
Epoch 6/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1928 - accuracy: 0.9301 - val_loss: 0.3845 - val_accuracy: 0.8443
Epoch 7/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1760 - accuracy: 0.9370 - val_loss: 0.4063 - val_accuracy: 0.8393
Epoch 8/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1605 - accuracy: 0.9434 - val_loss: 0.4356 - val_accuracy: 0.8340
Epoch 9/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1470 - accuracy: 0.9494 - val_loss: 0.4679 - val_accuracy: 0.8316
Epoch 10/10
782/782 [==============================] - 5s 6ms/step - loss: 0.1354 - accuracy: 0.9545 - val_loss: 0.5020 - val_accuracy: 0.8264
```



**Flatten** 레이어 대신, **GlobalAveragePooling1D** 레이어를 사용할 수 있습니다.

훈련 과정이 조금 더 빨라지는 대신, 정확도가 조금 감소합니다.



### 이전글/다음글

이전글 : [23. 자연어 처리하기 2](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_02.html)

다음글 : [25. Reference](https://codetorial.net/tensorflow/reference/index.html)



#  25. Reference

![../_images/tensorflow_logo1.png](.\Images\tensorflow_logo1.png)



**Tensorflow**의 여러가지 함수와 클래스의 사용법에 대해 소개합니다.

Contents

- [tf.cast](https://codetorial.net/tensorflow/reference/tf_cast.html)
- [tf.constant_initializer](https://codetorial.net/tensorflow/reference/tf_constant_initializer.html)
- [tf.constant](https://codetorial.net/tensorflow/reference/tf_constant.html)
- [tf.keras.activations.exponential](https://codetorial.net/tensorflow/reference/tf_keras_activations_exponential.html)
- [tf.keras.activations.linear](https://codetorial.net/tensorflow/reference/tf_keras_activations_linear.html)
- [tf.keras.activations.relu](https://codetorial.net/tensorflow/reference/tf_keras_activations_relu.html)
- [tf.keras.activations.sigmoid](https://codetorial.net/tensorflow/reference/tf_keras_activations_sigmoid.html)
- [tf.keras.activations.softmax](https://codetorial.net/tensorflow/reference/tf_keras_activations_softmax.html)
- [tf.keras.activations.tanh](https://codetorial.net/tensorflow/reference/tf_keras_activations_tanh.html)
- [tf.keras.datasets](https://codetorial.net/tensorflow/reference/tf_keras_datasets.html)
- [tf.keras.layers.Conv2D](https://codetorial.net/tensorflow/reference/tf_keras_layers_conv2d.html)
- [tf.keras.layers.Dense](https://codetorial.net/tensorflow/reference/tf_keras_layers_dense.html)
- [tf.keras.layers.Dropout](https://codetorial.net/tensorflow/reference/tf_keras_layers_dropout.html)
- [tf.keras.layers.Flatten](https://codetorial.net/tensorflow/reference/tf_keras_layers_flatten.html)
- [tf.keras.layers.GlobalAveragePooling2D](https://codetorial.net/tensorflow/reference/tf_keras_layers_globalaveragepooling2d.html)
- [tf.keras.layers.InputLayer](https://codetorial.net/tensorflow/reference/tf_keras_layers_inputlayer.html)
- [tf.keras.layers.Maximum](https://codetorial.net/tensorflow/reference/tf_keras_layers_maximum.html)
- [tf.keras.layers.Minimum](https://codetorial.net/tensorflow/reference/tf_keras_layers_minimum.html)
- [tf.keras.layers.ZeroPadding2D](https://codetorial.net/tensorflow/reference/tf_keras_layers_zeropadding2d.html)
- [tf.keras.metrics.Accuracy](https://codetorial.net/tensorflow/reference/tf_keras_metrics_accuracy.html)
- [tf.keras.metrics.BinaryAccuracy](https://codetorial.net/tensorflow/reference/tf_keras_metrics_binaryaccuracy.html)
- [tf.keras.Sequential](https://codetorial.net/tensorflow/reference/tf_keras_sequential.html)
- [tf.linspace](https://codetorial.net/tensorflow/reference/tf_linspace.html)
- [tf.ones_initializer](https://codetorial.net/tensorflow/reference/tf_ones_initializer.html)
- [tf.ones](https://codetorial.net/tensorflow/reference/tf_ones.html)
- [tf.random_normal_initializer](https://codetorial.net/tensorflow/reference/tf_random_normal_initializer.html)
- [tf.random.normal](https://codetorial.net/tensorflow/reference/tf_random_normal.html)
- [tf.random.set_seed](https://codetorial.net/tensorflow/reference/tf_random_set_seed.html)
- [tf.random_uniform_initializer](https://codetorial.net/tensorflow/reference/tf_random_uniform_initializer.html)
- [tf.random.uniform](https://codetorial.net/tensorflow/reference/tf_random_uniform.html)
- [tf.range](https://codetorial.net/tensorflow/reference/tf_range.html)
- [tf.rank](https://codetorial.net/tensorflow/reference/tf_rank.html)
- [tf.TensorShape](https://codetorial.net/tensorflow/reference/tf_tensorshape.html)
- [tf.zeros_initializer](https://codetorial.net/tensorflow/reference/tf_zeros_initializer.html)
- [tf.zeros](https://codetorial.net/tensorflow/reference/tf_zeros.html)



### 이전글/다음글

이전글 : [24. 자연어 처리하기 3](https://codetorial.net/tensorflow/natural_language_processing_in_tensorflow_03.html)

다음글 : [tf.cast](https://codetorial.net/tensorflow/reference/tf_cast.html)



#  tf.cast



**tf.cast** 함수는 텐서를 새로운 자료형으로 변환합니다.

## 예제1

```
import tensorflow as tf

x = tf.constant([1.8, 2.2], dtype=tf.float32)
y = tf.cast(x, tf.int32)

print(x)
print(y)
```



```
tf.Tensor([1.8 2.2], shape=(2,), dtype=float32)
tf.Tensor([1 2], shape=(2,), dtype=int32)
```



x는 (2,) 형태를 갖고, tf.float32 자료형을 갖는 텐서입니다.

**tf.cast()**를 사용해서 tf.int32 자료형을 갖도록 변환했습니다.

## 예제2

```
import tensorflow as tf

x = tf.constant([1.8, 2.2], dtype=tf.float32)
y = tf.dtypes.cast(x, tf.int32)

print(x)
print(y)
```



```
tf.Tensor([1.8 2.2], shape=(2,), dtype=float32)
tf.Tensor([1 2], shape=(2,), dtype=int32)
```



**tf.dtypes.cast()**는 **tf.cast()**의 **alias**입니다.

**tf.dtypes.cast()**를 사용해서 tf.int32 자료형을 갖도록 변환했습니다.



### 이전글/다음글

이전글 : [25. Reference](https://codetorial.net/tensorflow/reference/index.html)

다음글 : [tf.constant_initializer](https://codetorial.net/tensorflow/reference/tf_constant_initializer.html)

