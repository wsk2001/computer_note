# java Jackson으로 json - object 변환 하기

출처: https://ramees.tistory.com/33



# 1. 개발환경

- JDK 1.8
- Jackson 2.8.8
- Spring 4
- IntelliJ IDEA 2018.1

# 2. Object -> Json (String)

Object -> Json 변환과정은 Serialization 이라고 한다. Jackson은 String 이외에 file이나 stream에 쓰는것도 가능하다.

 

```java
public class Person {
    Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    Person(){
    }

    String name;
    int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person("Nicole", 20);

        String personJson = objectMapper.writeValueAsString(person);

        System.out.println(personJson);

    }
}
```

 

이렇게 작성하고 main 함수를 돌려보면 

 

```json
{"name":"Nicole","age":20}  
```

 

이와 같은 결과를 얻을 수 있다.

 

 

# 3. Json (String) -> Object

Json -> Object 변환 과정은 Deserialization 이라고 한다. Serialization과 마찬가지로 file이나 stream에 쓰는것도 가능하다.

 

```java
public static void main(String[] args) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Person person = new Person("Nicole", 20);

    String personJson = objectMapper.writeValueAsString(person);

    Person jsonToPerson = objectMapper.readValue(personJson, Person.class);
    System.out.println(jsonToPerson.getName());
    System.out.println(jsonToPerson.getAge());

}
```

 

밑에 세줄을 추가했다.

 

```bash
Nicole
20
```

 

이와 같은 결과를 얻을 수 있다.