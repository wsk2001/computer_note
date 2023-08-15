# Java 암호화 관련 Class



### javah.crypto.SecretKey



javah.crypto.SecretKey 클래스는 자바의 암호화 및 복호화를 위해 사용되는 키 클래스입니다. 이 클래스는 암호화 및 복호화를 위한 알고리즘 및 키 크기를 지정하여 생성할 수 있습니다. 생성된 키는 Cipher 클래스를 사용하여 암호화 및 복호화에 사용할 수 있습니다.

SecretKey 클래스는 다음과 같은 속성을 제공합니다.

- algorithm: 암호화 및 복호화를 위해 사용되는 알고리즘입니다.
- keySize: 키의 크기입니다.
- keyMaterial: 키의 자료입니다.

SecretKey 클래스는 다음과 같은 메서드를 제공합니다.

- getAlgorithm(): 암호화 및 복호화를 위해 사용되는 알고리즘을 반환합니다.
- getKeySize(): 키의 크기를 반환합니다.
- getEncoded(): 키의 자료로 인코딩된 바이트 배열을 반환합니다.

SecretKey 클래스는 암호화 및 복호화를 위한 키를 생성하고 관리하는 데 사용되는 중요한 클래스입니다.



### java.security.spec.AlgorithmParameterSpec

java.security.spec.AlgorithmParameterSpec은 암호화 알고리즘에 대한 매개변수를 지정하는 인터페이스입니다. 이 인터페이스는 구현 클래스가 매개변수의 유형을 지정하지 않고도 매개변수를 전달할 수 있도록 하는 추상화 계층을 제공합니다.

AlgorithmParameterSpec 인터페이스는 다음과 같은 메서드를 제공합니다.

- getAlgorithm(): 매개변수의 알고리즘을 반환합니다.
- getEncoded(): 매개변수를 인코딩된 바이트 배열로 반환합니다.

AlgorithmParameterSpec 인터페이스는 암호화 알고리즘에 대한 매개변수를 지정하는 데 사용되는 중요한 인터페이스입니다.

다음은 AlgorithmParameterSpec 인터페이스를 구현하는 몇 가지 클래스입니다.

- javax.crypto.spec.DHParameterSpec: Diffie-Hellman 키 교환 알고리즘에 대한 매개변수를 지정합니다.
- javax.crypto.spec.IvParameterSpec: 블록 암호에 대한 초기화 벡터(IV) 매개변수를 지정합니다.
- javax.crypto.spec.RSAKeyGenParameterSpec: RSA 키 생성 알고리즘에 대한 매개변수를 지정합니다.

AlgorithmParameterSpec 인터페이스는 암호화 알고리즘에 대한 매개변수를 지정하는 데 사용되는 중요한 인터페이스입니다. 구현 클래스는 AlgorithmParameterSpec 인터페이스를 구현하여 특정 알고리즘에 대한 매개변수를 지정할 수 있습니다.



### javah.crypto.Cipher

Cipher 클래스는 자바의 암호화 및 복호화를 위해 사용되는 클래스입니다. 이 클래스는 암호화 및 복호화를 위한 알고리즘, 키 및 모드(암호화 모드)를 지정하여 생성할 수 있습니다. 생성된 Cipher 인스턴스는 암호화 및 복호화에 사용할 수 있습니다.

javah.crypto.Cipher.*getInstance*() 를 사용하여 인스턴스를 만드는데,  싱글톤 객체가 아니다.

Cipher 클래스는 다음과 같은 메서드를 제공합니다.

- init(int mode, Key key): 암호화 또는 복호화를 위한 Cipher 인스턴스를 초기화합니다.
- update(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset): Cipher 인스턴스를 사용하여 입력 바이트 배열을 출력 바이트 배열로 암호화 또는 복호화합니다.
- doFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset): Cipher 인스턴스를 사용하여 입력 바이트 배열을 출력 바이트 배열로 암호화 또는 복호화합니다.

Cipher 클래스는 암호화 및 복호화를 위해 사용되는 중요한 클래스입니다. Cipher 인스턴스를 사용하여 데이터를 암호화하거나 복호화할 수 있습니다.

다음은 Cipher 클래스의 몇 가지 사용 예입니다.

- 데이터를 암호화하려면 다음과 같이 할 수 있습니다.

```java
Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key);
byte[] encryptedData = cipher.doFinal(plaintextData);
```

- 데이터를 복호화하려면 다음과 같이 할 수 있습니다.

```java
Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] decryptedData = cipher.doFinal(encryptedData);
```