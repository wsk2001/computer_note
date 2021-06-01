# Python Blockchain Tutorial

 출처: https://www.tutorialspoint.com/python_blockchain/index.htm

Blockchain은 소프트웨어 개발 동향을 지배하고있는 현재의 소문입니다. Blockchain의 개발 및 설계에는 **클라이언트**, **광부** 및 **블록 체인**의 세 가지 주요 구성 요소가 포함됩니다. 이 튜토리얼은 자신 만의 블록 체인을 만드는 과정을 명확하게 이해하기위한 것입니다.



# Audience

Blockchain 개발의 최근 추세에 발 맞춰 가고 싶어하는 프로그래밍 애호가라면 누구나이 튜토리얼을 통해 얻을 수 있습니다. Blockchain Development의 기초를 배우는 데 관심이있는 학습자 인 경우이 자습서는 사용자의 필요에 적절하게 맞습니다.



# Prerequisites

이 튜토리얼은 학습자가 Python 프로그래밍에 대한 아이디어와 Blockchain에 대한 기본 아이디어를 가지고 있다고 가정하여 작성되었습니다. 이러한 개념을 처음 사용하는 경우이 자습서에 들어가기 전에 먼저 이러한 개념을 기반으로 자습서를 선택하는 것이 좋습니다.



# Python Blockchain - Introduction

Blockchain에 대한 튜토리얼에서 블록 체인의 이론에 대해 자세히 배웠습니다. 블록 체인은 세계에서 가장 널리 사용되는 디지털 통화 Bitcoin의 기본 구성 요소입니다. 이 튜토리얼은 블록 체인 아키텍처를 완전히 설명하는 Bitcoin의 복잡함을 깊이 다뤘다. 다음 단계는 자체 블록 체인을 만드는 것입니다.

Satoshi Nakamoto는 Bitcoin이라는 세계 최초의 가상 화폐를 만들었습니다. Bitcoin의 성공을 살펴보면 많은 사람들이 자체 가상 화폐를 만들었습니다. 몇 가지 예를 들자면 Litecoin, Zcash 등입니다.

이제 자신의 통화를 시작하실 수 있습니다. 이것을 TPCoin (TutorialsPoint Coin)이라고합니다. TPCoin을 다루는 모든 트랜잭션을 기록하는 블록 체인을 작성합니다. TPCoin은 피자, 버거, 샐러드 등을 구입하는 데 사용할 수 있습니다. 네트워크에 가입하고 서비스 제공을위한 통화로 TPCoin을 수락하는 다른 서비스 제공 업체가있을 수 있습니다. 가능성은 무한합니다.

이 자습서에서는 이러한 시스템을 구성하고 시장에서 자신의 디지털 통화를 시작하는 방법을 이해합시다.



### Components Involved in Blockchain Project Development

 전체 블록 체인 프로젝트 개발은 세 가지 주요 구성 요소로 구성됩니다.

- Client
- Miners
- Blockchain

### Client

고객은 다른 공급 업체로부터 제품을 구매할 사람입니다. 클라이언트 그 자체는 공급 업체가 될 수 있으며 그가 제공하는 제품에 대해 다른 사람들로부터 돈을받을 것입니다. 여기서는 클라이언트가 TPCoins의 공급자이자받는 사람이 될 수 있다고 가정합니다. 따라서 우리는 돈을 송수신 할 수있는 클라이언트 클래스를 코드에 작성합니다.

### Miner

Miner는 트랜잭션 풀에서 트랜잭션을 선택하여 블록으로 어셈블합니다. 광부는 광산 보상을 받기 위해 유효한 작업 증명을 제공해야합니다. 광부가 모으는 모든 돈은 그가 지키기위한 것입니다. 그는 네트워크에서 등록 된 다른 공급 업체의 제품이나 서비스를 구매할 때이 돈을 사용할 수 있습니다.

### Blockchain

마지막으로 Blockchain은 모든 채굴 된 블록을 연대순으로 연결하는 데이터 구조입니다. 이 사슬은 불변이고 따라서 내성이 없습니다.

새 Jupyter 노트북의 각 단계에 제시된 코드를 입력하여이 자습서를 수행 할 수 있습니다. 또는 www.anaconda.com에서 전체 Jupyter 노트북을 다운로드 할 수 있습니다.

다음 장에서는 블록 체인 시스템을 사용하는 클라이언트를 개발할 것입니다.



# Python Blockchain - Developing Client

클라이언트는 TPCoins를 보유하고 자신의 것을 포함하여 네트워크상의 다른 공급 업체의 재화/서비스를 처리하는 사람입니다. 이를 위해 Client 클래스를 정의해야합니다. 클라이언트에 대해 전 세계적으로 고유 한 ID를 만들려면 PKI (공개 키 인프라)를 사용합니다. 이 장에서는 이에 대해 자세히 설명하겠습니다.

고객은 그의 지갑에서 다른 알려진 사람에게 돈을 보낼 수 있어야합니다. 마찬가지로 고객은 제 3 자로부터 돈을 수령 할 수 있어야합니다. 돈을 지출하기 위해 고객은 송금 자의 이름과 지불 할 금액을 지정하는 거래를 생성합니다. 돈을 수령하기 위해서, 고객은 본질적으로 돈을 보낸 제 3 자에게 자신의 신원을 제공 할 것입니다. 우리는 고객이 그의 지갑에 보유하고있는 잔금을 저장하지 않습니다. 거래하는 동안 고객이 지불하기에 충분한 균형을 유지할 수 있도록 실제 잔액을 계산합니다.

클라이언트 클래스를 개발하고 프로젝트의 나머지 코드를 개발하려면 많은 Python 라이브러리를 가져와야합니다. 아래에 나열되어 있습니다.

```python
# import libraries
import hashlib
import random
import string
import json
import binascii
import numpy as np
import pandas as pd
import pylab as pl
import logging
import datetime
import collections
```

위의 표준 라이브러리 외에도 트랜잭션에 서명하고 객체의 해시 등을 만들 예정입니다.이를 위해 다음 라이브러리를 가져와야합니다

```python
# following imports are required by PKI
import Crypto
import Crypto.Random
from Crypto.Hash import SHA
from Crypto.PublicKey import RSA
from Crypto.Signature import PKCS1_v1_5
```

 다음 장에서는 클라이언트 클래스에 대해 설명하겠습니다.



# Python Blockchain - Client Class

Client 클래스는 내장 된 Python RSA 알고리즘을 사용하여 개인 키와 공개 키를 생성합니다. 관심있는 독자는 RSA 구현을 위해이 자습서를 참조 할 수 있습니다. 객체를 초기화하는 동안 private 및 public 키를 만들고 인스턴스 변수에 값을 저장합니다.

```python
self._private_key = RSA.generate(1024, random)
self._public_key = self._private_key.publickey()
```

개인 키를 잃어 버리지 않아야합니다. 기록 보관을 위해 생성 된 개인 키가 보안 외부 저장소에 복사되거나 단순히 종이에 ASCII 표현을 적어 둘 수 있습니다.

생성 된 공개 키는 클라이언트의 ID로 사용됩니다. 이를 위해 공개 키의 HEX 표현을 반환하는 identity라는 속성을 정의합니다.

```python
@property
   def identity(self):
      return
binascii.hexlify(self._public_key.exportKey(format='DER'))
.decode('ascii')
```

ID는 각 클라이언트마다 고유하며 공개적으로 사용할 수 있습니다. 누구든지 이 신원을 사용하여 가상 화폐를 보낼 수 있으며 지갑에 추가됩니다.

Client 클래스의 전체 코드가 여기에 표시됩니다.

```python
class Client:
   def __init__(self):
      random = Crypto.Random.new().read
      self._private_key = RSA.generate(1024, random)
      self._public_key = self._private_key.publickey()
      self._signer = PKCS1_v1_5.new(self._private_key)

   @property
   def identity(self):
      return
binascii.hexlify(self._public_key.exportKey(format='DER')).decode('ascii')
```



### Testing Client

이제 Client 클래스를 사용하는 방법을 설명하는 코드를 작성합니다.

```python
Dinesh = Client()
print (Dinesh.identity)
```

위의 코드는 Client의 인스턴스를 만들고 변수 Dinesh에 할당합니다. Dinesh의 공개 키는 ID 메서드를 호출하여 인쇄합니다. 출력은 여기에 표시됩니다.

```
30819f300d06092a864886f70d010101050003818d0030818902818100b547fafceeb131e07
0166a6b23fec473cce22c3f55c35ce535b31d4c74754fecd820aa94c1166643a49ea5f49f72
3181ff943eb3fdc5b2cb2db12d21c06c880ccf493e14dd3e93f3a9e175325790004954c34d3
c7bc2ccc9f0eb5332014937f9e49bca9b7856d351a553d9812367dc8f2ac734992a4e6a6ff6
6f347bd411d07f0203010001
```

이제 다음 장에서 트랜잭션을 생성 해 보겠습니다.



# Python Blockchain - Transaction Class

이 장에서는 클라이언트가 누군가에게 돈을 보낼 수 있도록 Transaction 클래스를 생성 해 보겠습니다. 클라이언트는 송금자나 수령인이 될 수 있습니다. 돈을 받고자 할 때, 다른 발신자는 거래를 생성하고 거래 내 공개 주소를 지정합니다. 우리는 다음과 같이 트랜잭션 클래스의 초기화를 정의한다.

```python
def __init__(self, sender, recipient, value):
   self.sender = sender
   self.recipient = recipient
   self.value = value
   self.time = datetime.datetime.now()
```

init 메소드는 송신자의 공개 키, 수신자의 공개 키 및 전송할 양의 세 가지 매개 변수를 취합니다. 이들은 다른 메소드에서 사용하기 위해 인스턴스 변수에 저장됩니다. 또한 트랜잭션 시간을 저장할 변수를 하나 더 만듭니다.

다음으로 위에서 언급 한 네 개의 인스턴스 변수를 모두 사전 객체에 결합하는 to_dict라는 유틸리티 메소드를 작성합니다. 이렇게하면 전체 트랜잭션 정보를 단일 변수를 통해 액세스 할 수 있습니다.

이전 튜토리얼에서 알 수 있듯이 블록 체인의 첫 번째 블록은 창세기 블록입니다. Genesis 블록은 블록 체인 작성자가 시작한 첫 번째 트랜잭션을 포함합니다. 이 사람의 신원은 Bitcoins의 경우처럼 비밀로 유지 될 수 있습니다. 따라서이 첫 번째 거래가 생성되면 작성자는 자신의 신원을 Genesis로 보낼 수 있습니다. 따라서 사전을 생성하는 동안 발신자가 Genesis인지 확인한 다음 일부 문자열 값을 ID 변수에 할당하기 만하면됩니다. 그렇지 않은 경우에는 보낸 사람의 ID를 ID 변수에 할당합니다.

```python
if self.sender == "Genesis":
   identity = "Genesis"
else:
   identity = self.sender.identity
```

다음 코드 행을 사용하여 사전을 구성합니다.

```python
return collections.OrderedDict({
   'sender': identity,
   'recipient': self.recipient,
   'value': self.value,
   'time' : self.time})
```

to_dict 메소드의 전체 코드는 다음과 같습니다.

```python
def to_dict(self):
   if self.sender == "Genesis":
      identity = "Genesis"
   else:
      identity = self.sender.identity

   return collections.OrderedDict({
      'sender': identity,
      'recipient': self.recipient,
      'value': self.value,
      'time' : self.time})
```

마지막으로 우리는 보낸 사람의 개인 키를 사용하여 이 사전 개체에 서명합니다. 이전과 마찬가지로 SHA 알고리즘이있는 내장 PKI를 사용합니다. 생성 된 서명은 디코딩되어 인쇄용 ASCII 표현을 가져 와서 블록 체인에 저장합니다. 여기에 sign_transaction 메소드 코드가 표시됩니다.

```python
def sign_transaction(self):
   private_key = self.sender._private_key
   signer = PKCS1_v1_5.new(private_key)
   h = SHA.new(str(self.to_dict()).encode('utf8'))
   return binascii.hexlify(signer.sign(h)).decode('ascii')
```

## Testing Transaction Class

이를 위해 Dinesh 및 Ramesh라는 두 명의 사용자를 생성합니다. Dinesh는 Ramesh에게 TPCoins 5 개를 보냅니다. 먼저 Dinesh 및 Ramesh라는 클라이언트를 만듭니다.

```
Dinesh = Client()
Ramesh = Client()
```

Client 클래스를 인스턴스화하면 클라이언트에 고유 한 공개 키와 개인 키가 만들어집니다. Dinesh는 Ramesh에게 지불금을 보내고 있기 때문에 클라이언트의 ID 속성을 사용하여 얻은 Ramesh의 공개 키가 필요합니다.

따라서 다음 코드를 사용하여 트랜잭션 인스턴스를 만듭니다.

```python
t = Transaction(
   Dinesh,
   Ramesh.identity,
   5.0
)
```

첫 번째 매개 변수는 보낸 사람이고 두 번째 매개 변수는받는 사람의 공개 키이며 세 번째 매개 변수는 전송할 양입니다. sign_transaction 메서드는 트랜잭션을 호출하기 위해 첫 번째 매개 변수에서 보낸 사람의 개인 키를 검색합니다.

트랜잭션 객체를 만든 후에는 sign_transaction 메서드를 호출하여 서명합니다. 이 메서드는 생성 된 서명을 인쇄 가능한 형식으로 반환합니다. 다음 두 줄의 코드를 사용하여 서명을 생성하고 인쇄합니다.

```python
signature = t.sign_transaction()
print (signature) 
```

위의 코드를 실행하면 다음과 비슷한 출력이 표시됩니다.

```
7c7e3c97629b218e9ec6e86b01f9abd8e361fd69e7d373c38420790b655b9abe3b575e343c7
13703ca1aee781acd7157a0624db3d57d7c2f1172730ee3f45af943338157f899965856f6b0
0e34db240b62673ad5a08c8e490f880b568efbc36035cae2e748f1d802d5e8e66298be826f5
c6363dc511222fb2416036ac04eb972
```

이제 클라이언트를 만드는 기본 인프라와 트랜잭션이 준비되면서 실제 상황 에서처럼 여러 클라이언트가 여러 트랜잭션을 수행하게됩니다.

