import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaObjectMapperExample {
    public static void main(String[] args) throws Exception {
        // JSON string to convert
        String json = "{\"name\":\"John Doe\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"Anytown\",\"state\":\"CA\",\"zip\":\"12345\"}}";

        // Create an instance of ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // Convert JSON string to POJO
        Person person = mapper.readValue(json, Person.class);


		person.setName("GilDong");
        // Print the properties of the POJO
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Address: " + person.getAddress());

        // Convert POJO to JSON string
        json = mapper.writeValueAsString(person);

        // Print the JSON string
        System.out.println(json);
    }
}

class Person {
    private String name;
    private int age;
    private Address address;

    public Person() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address() { }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}


// javac -cp .;jackson-core-2.14.1.jar;jackson-databind-2.14.1.jar;jackson-annotations-2.14.1.jar JavaObjectMapperExample.java

// java -cp .;jackson-core-2.14.1.jar;jackson-databind-2.14.1.jar;jackson-annotations-2.14.1.jar JavaObjectMapperExample