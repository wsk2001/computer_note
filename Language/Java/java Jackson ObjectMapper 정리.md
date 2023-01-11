# java Jackson ObjectMapper 정리



## **개요**

Java 개발자라면 Jackson에서 제공하는 ObjectMapper와 자주 마주치게 됩니다.

Java 클래스 내용을 확인하거나 내용물의 Parsing 과정에 필요한 커스터마이징이 존재하기 때문입니다.

물론 중요한 기능은 (1) "Java Object" =Serialize=> "JSON", (2) "JSON" =Deserialize=> "Java Object" 입니다.

개발 중에 매 번 찾아보기 지쳐서 이제는 한 곳에 정리가 필요하지 않을까 생각되어서 이 글을 쓰게 되었습니다.

 

------

## **목차**

1. Add Dependency for "Jackson ObjectMapper"
2. Basic Features
   1. Convert "Java Object" to "JSON"
   2. Convert "JSON" to "Java Object"
   3. Convert "JSON" to "Jackson JsonNode"
   4. Convert "JSON Array String" to "Java List"
   5. Convert "JSON String" to "Java Map"
3. Advanced Features
   1. Configuring Serialization or Deserialization Feature
   2. Creating Custom Serializer or Deserializer
   3. Handling Date Formats
   4. Handling Collections

 

------

## **1. Add Dependency for "Jackson ObjectMapper"**

라이브러리의 사용에 앞서서 일단 프로젝트에 종속성을 추가하고 시작하겠습니다.

 

Maven의 경우)

아래 종속성을 pom.xml의 <dependencies>...</dependencies>에 추가해줍니다.

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version>
</dependency>
```

Gradle의 경우)

아래 종속성을 build.gradle의 dependencies { ... }에 추가해줍니다.

```xml
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
implementation 'com.fasterxml.jackson.core:jackson-databind:2.12.3'
```

 

다음 링크를 통해서 원하는 버전을 사용할 수 있습니다.

[Jackson Databind 버전 확인 링크](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

 

## **2. Basic Features**

아래 User라는 Java Object가 존재한다고 가정하고, 2. Basic Features의 예시 코드를 보이겠습니다.

```java
public class User {

    private String name;
    private int age;
    
    public User(String name, int age) {
    	this.name = name;
        this.age = age;
    }
    
    public String getName() {
    	return name;
	}
    
    public int getAge() {
    	return age;
	}
}
```

 

### **2.1. Convert "Java Object" to "JSON"**

```java
ObjectMapper objectMapper = new ObjectMapper();
User user = new User("Ryan", 30);

objectMapper.writeValue(new File("user.json"), user);
// 파일 출력: user.json
{"name":"Ryan","age":30}
// 문자열 출력
String userAsString = objectMapper.writeValueAsString(user);
{"name":"Ryan","age":30}
```

 

### **2.2. Convert "JSON" to "Java Object"**

```java
// String to Object
String json = "{ \"name\" : \"Ryan\", \"age\" : 30 }";
User user = objectMapper.readValue(json, User.class);
// JSON File to Object
User user = objectMapper.readValue(new File("user.json"), User.class);
// JSON URL to Object
User user = objectMapper.readValue(new URL("file:user.json"), User.class);
```

 

### **2.3. Convert "JSON" to "Jackson JsonNode"**

```java
String json = "{ \"name\" : \"Ryan\", \"age\" : 30 }";
JsonNode jsonNode = objectMapper.readTree(json);
String name = jsonNode.get("name").asText();// Ryan
int age = jsonNode.get("age").asInt();// 30
```

 

### **2.4. Convert "JSON Array String" to "Java List"**

```java
String jsonArr = "[{\"name\":\"Ryan\",\"age\":30},{\"name\":\"Jake\",\"age\":20}]";
List<User> users = objectMapper.readValue(jsonArr, new TypeReference<>() {});
```

 

### **2.5. Convert "JSON String" to "Java Map"**

```java
String jsonArr = "{\"name\":\"Ryan\",\"age\":30}";
Map<String, Object> user = objectMapper.readValue(jsonArr, new TypeReference<>() {
});
```

 

## **3. Advanced Features**

위에서 설명한 Jackson Library의 Basic Features에 추가적으로 유용한 기능를 사용할 수 있습니다.

JSON과 Object에 대해서 커스터마이징한 Serialize/Deserialize가 가능한 기능을 사용할 수 있습니다.

 

### **3.1. Configuring Serialization or Deserialization Feature**

**3.1.1. JSON에는 있지만 Mapping될 Object에는 없는 필드를 무시해야하는 경우**

```java
String json = "{\"name\":\"Ryan\",\"age\":30,\"sex\":\"M\"}";
// User Object에서는 "sex" field가 없습니다. 아래 설정을 안하게되면 익셉션이 발생합니다.
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
User user = objectMapper.readValue(json, User.class);
```

**3.1.2. JSON에 있는 propety가 Mapping될 Object에 primitive인데 null 값이 전달을 무시해야하는 경우**

```java
String json = "{\"name\":\"Ryan\",\"age\":null}";
// 기본적으로 FAIL_ON_NULL_FOR_PRIMITIVES 옵션은 false 상태입니다. 의도적으로 옵션을 설정해주었습니다.
// 옵션이 true가 되게되면, age가 int인 primitive 자료형이 null인 JSON이 전달되는 경우 익셉션을 발생합니다.
objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
User user = objectMapper.readValue(json, User.class);
```

 

### **3.2. Creating Custom Serializer or Deserializer**

JSON이 Object로 Deserialize될 때, 서로 다른 정보로 전달되고 있는데 Object나 JSON이 전달하는 모델을 변경하지 못하는 경우,

"Custom Deserializer"를 사용합니다.

 

Object이 JSON으로 Serialize될 때, 서로 다른 정보로 전달되고 있는데 Object나 JSON이 전달하는 모델을 변경하지 못하는 경우,

"Custom Serializer"를 사용합니다.

 

아래 Serializer와 Deserializer에 예시에 사용될 User Class입니다.

```java
class User {
    private final int age;
	
    User(int age) {
    	this.age = age;
    }
}
```

 

**3.2.1. Custom Serializer Example**

"JSON" =Custom Deserialize=> "Object"

```java
public class CustomUserSerializer extends StdSerializer<Car> {
    public CustomUserSerializer() {
        this(null);
    }

    public CustomUserSerializer(Class<Car> t) {
        super(t);
    }

    @Override
    public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user_age", user.getAge());
        jsonGenerator.writeEndObject();
    }
}
ObjectMapper mapper = new ObjectMapper();
SimpleModule module = new SimpleModule("CustomUserSerializer", new Version(1, 0, 0, null, null, null));
module.addSerializer(User.class, new CustomUserSerializer());
mapper.registerModule(module);
User user = new User(20);
String userJson = mapper.writeValueAsString(user);
System.out.println(userJson);// {"user_age":20}
```

 

**3.2.2. Custom Deserializer Example**

"Object" =Custom Serialize=> "JSON"

```java
public class CustomUserDeserializer extends StdDeserializer<User> {
    public CustomUserDeserializer() {
        this(null);
    }

    public CustomUserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext deserializer) {
        User user = new User();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        
        // try catch block
        JsonNode ageNode = node.get("user_age");
        String age = ageNode.asText();
        user.setAge(age);
        return user;
    }
}
String json = "{ \"user_age\" : 30}";
ObjectMapper mapper = new ObjectMapper();
SimpleModule module = new SimpleModule("CustomUserDeserializer", new Version(1, 0, 0, null, null, null));
module.addDeserializer(User.class, new CustomUserDeserializer());
mapper.registerModule(module);
User user = mapper.readValue(json, User.class);
```

 

### **3.3. Handling Date Formats**

Serialize를 하는 과정에서 Date로 구성된 Object field의 Format을 변경할 수 있습니다.

```java
class User {
    private int age;
    private Date createdAt;

    // getters, setters
}
User user = new User();
user.setAge(30);
user.setCreatedAt(Date.from(Instant.now()));

ObjectMapper objectMapper = new ObjectMapper();
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
objectMapper.setDateFormat(df);
String userAsString = objectMapper.writeValueAsString(user);
System.out.println(userAsString);// {"age":30,"createdAt":"2021-06-10 11:43 AM CEST"}
```

 

### **3.4. Handling Collections**

JSON에 존재하는 복수개의 모델을 Collections 자료형으로 Deserialize할 수 있습니다.

 

Deserialize JSON to Array

```java
String jsonUserArray = "[{\"age\":30,\"createdAt\":\"2021-06-10T11:43:12\"},{\"age\":30,\"createdAt\":\"2021-06-10T11:43:12\"}]";
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
User[] users = objectMapper.readValue(jsonCarArray, User[].class);
// print users
```

또는 Deserialize JSON to List

```java
String jsonUserArray = "[{\"age\":30,\"createdAt\":\"2021-06-10T11:43:12\"},{\"age\":30,\"createdAt\":\"2021-06-10T11:43:12\"}]";
ObjectMapper objectMapper = new ObjectMapper();
List<User> listUser = objectMapper.readValue(jsonCarArray, new TypeReference<List<User>>(){});
// print users
```

 

------

## **결론**

- 잘 정리되어있던 참고자료에 있는 포스트를 통해서 하나씩 코드를 돌려보면서 정리하였습니다.
- Java를 쓰다보면 Jackson 라이브러리를 직접적인 코드를 사용하거나 Annotation을 사용하는 경우가 많습니다.
- 그중에서 ObjectMapper를 직접 사용하는 것에 대한 정리를 이 포스트에 업데이트해나가겠습니다.

 

------

## **참고자료**

- https://www.baeldung.com/jackson-object-mapper-tutorial