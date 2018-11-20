package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

type responseEnc struct {
	Cipher string
}

type responseDec struct {
	Plain string
}

func main() {
	res := responseEnc{}
	jsonData := map[string]string{"alias": "normal", "plain": "1234567890123"}
	jsonValue, _ := json.Marshal(jsonData)
	response, err := http.Post("http://127.0.0.1:8080/encrypt", "application/json", bytes.NewBuffer(jsonValue))
	if err != nil {
		fmt.Printf("The HTTP request failed with error %s\n", err)
	} else {
		data, _ := ioutil.ReadAll(response.Body)
		json.Unmarshal(data, &res)
	}
	fmt.Println("Cipher string : " + res.Cipher)

	resDec := responseDec{}
	jsonData = map[string]string{"alias": "normal", "cipher": res.Cipher}
	jsonValue, _ = json.Marshal(jsonData)
	response, err = http.Post("http://127.0.0.1:8080/decrypt", "application/json", bytes.NewBuffer(jsonValue))
	if err != nil {
		fmt.Printf("The HTTP request failed with error %s\n", err)
	} else {
		data, _ := ioutil.ReadAll(response.Body)
		json.Unmarshal(data, &resDec)
	}
	fmt.Println("Plain string : " + resDec.Plain)

	fmt.Println("Terminating the application...")
}

/**
 * run : go run GoRestPost.go
 */
