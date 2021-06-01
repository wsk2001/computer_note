### Golang으로 간단한 RESTful API 만들기

대부분의 최신 응용 프로그램은 백엔드와 프론트 엔드 계층을 분리합니다. 
백엔드는 일반적으로 RESTful API이며 전체 스택 개발의 중요한 부분입니다. 
이러한 API는 일반적으로 EndPoint 라고도 하는 Router 모음으로 일반적으로 세분화됩니다. 
이와 같이 Application 을 Build 하는것은 모든 기능을 하나의 응용 프로그램으로 작성하는 것과 비교하여 종종 매우 깨끗하고 유지 보수가 용이합니다.

Go 프로그래밍 언어를 사용하여 기본 CRUD 작업을 수행하는 간단한 API를 작성하는 데 필요한 것이 무엇인지 살펴 보겠습니다.

간단히 하기 위해 이 예제에서는 데이터베이스를 사용하지 않을 것입니다. 
우리는 모의 데이터라고 할 수있는 일부 데이터를 하드코딩 할 것입니다. 
이를 통해 엔드 포인트 정의 및 기본 작업 수행과 같은 핵심 자료에 집중할 수 있습니다.

이 시점에서 Go는 컴퓨터에 이미 설치되고 구성되어 있다고 가정합니다. 

아래의 코드를 `myproject.go` 파일로 저장 하십시요.
```go
package main

import (
    "encoding/json"
    "log"
    "net/http"

    "github.com/gorilla/mux"
)

type Person struct {
    ID        string   `json:"id,omitempty"`
    Firstname string   `json:"firstname,omitempty"`
    Lastname  string   `json:"lastname,omitempty"`
    Address   *Address `json:"address,omitempty"`
}

type Address struct {
    City  string `json:"city,omitempty"`
    State string `json:"state,omitempty"`
}

var people []Person

func GetPersonEndpoint(w http.ResponseWriter, req *http.Request) {
    params := mux.Vars(req)
    for _, item := range people {
        if item.ID == params["id"] {
            json.NewEncoder(w).Encode(item)
            return
        }
    }
    json.NewEncoder(w).Encode(&Person{})
}

func GetPeopleEndpoint(w http.ResponseWriter, req *http.Request) {
    json.NewEncoder(w).Encode(people)
}

func CreatePersonEndpoint(w http.ResponseWriter, req *http.Request) {
    params := mux.Vars(req)
    var person Person
    _ = json.NewDecoder(req.Body).Decode(&person)
    person.ID = params["id"]
    people = append(people, person)
    json.NewEncoder(w).Encode(people)
}

func DeletePersonEndpoint(w http.ResponseWriter, req *http.Request) {
    params := mux.Vars(req)
    for index, item := range people {
        if item.ID == params["id"] {
            people = append(people[:index], people[index+1:]...)
            break
        }
    }
    json.NewEncoder(w).Encode(people)
}

func main() {
    router := mux.NewRouter()
    people = append(people, Person{ID: "1", Firstname: "Nic", Lastname: "Raboy", Address: &Address{City: "Dublin", State: "CA"}})
    people = append(people, Person{ID: "2", Firstname: "Maria", Lastname: "Raboy"})
    router.HandleFunc("/people", GetPeopleEndpoint).Methods("GET")
    router.HandleFunc("/people/{id}", GetPersonEndpoint).Methods("GET")
    router.HandleFunc("/people/{id}", CreatePersonEndpoint).Methods("POST")
    router.HandleFunc("/people/{id}", DeletePersonEndpoint).Methods("DELETE")
    log.Fatal(http.ListenAndServe(":12345", router))
}
```

`github.com/gorilla/mux` 의 Download 는 다음 명령을 참조 하세요.
```
go get github.com/gorilla/mux
```

가져온 종속성을 사용하여 데이터를 저장할 구조체 개체를 만들려고합니다. 저장하려는 데이터는 Person 데이터 입니다.
```go
type Person struct {
    ID        string   `json:"id,omitempty"`
    Firstname string   `json:"firstname,omitempty"`
    Lastname  string   `json:"lastname,omitempty"`
    Address   *Address `json:"address,omitempty"`
}
```
구조체에 어떤 속성이 존재하는지 정의하고 있지만 구조체가 JSON으로 표시되는 방법을 설명하는 태그도 정의하고 있습니다. 
각 태그 안에는 생략 부호 매개 변수가 있습니다. 
즉, 속성이 null 인 경우 빈 문자열 이나 값으로 표시되는 대신 JSON 데이터에서 제외됩니다.

Person 구조체에는 포인터 인 Address 속성이 있습니다. 
이것은 중첩 된 JSON 객체를 나타내며 포인터가 되어야합니다. 
그렇지 않으면 omitempty가 작동하지 않습니다. 
Address 구조체는 다음과 같습니다.
```go
type Address struct {
    City  string `json:"city,omitempty"`
    State string `json:"state,omitempty"`
}
```
나머지 설명을 출처를 link 하여 참조 하시기 바랍니다.
Programming language 를 하는 사람은 쉽게 이해 할 수 있는 예제 입니다. 

출처: https://www.thepolyglotdeveloper.com/2016/07/create-a-simple-restful-api-with-golang/

