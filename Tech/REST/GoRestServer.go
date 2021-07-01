package main

/*
	#cgo CFLAGS: -I./include
	#cgo LDFLAGS: -L./lib -lXdbAdapter

	#include "XdbAdapter.h"
*/
import "C"

import (
	"log"
	"net/http"
	"sync"
	"unsafe"

	"github.com/ant0ine/go-json-rest/rest"
)

// IniFileName 설정파일 경로
var IniFileName = "XdbAdapter.ini"

var lock = sync.RWMutex{}

func main() {
	api := rest.NewApi()
	api.Use(rest.DefaultDevStack...)
	router, err := rest.MakeRouter(
		rest.Post("/encrypt", encrypt),
		rest.Post("/encryptmulti", encryptMulti),
		rest.Post("/decrypt", decrypt),
		rest.Post("/decryptmulti", decryptMulti),
		rest.Post("/hash", getHash),
	)
	if err != nil {
		log.Fatal(err)
	}
	api.SetApp(router)
	log.Fatal(http.ListenAndServe(":8080", api.MakeHandler()))
}

type policyEnc struct {
	Alias string
	Plain string
}

type responseEnc struct {
	Cipher string
}

type policyDec struct {
	Alias  string
	Cipher string
}

type responseDec struct {
	Plain string
}

type reqHash struct {
	Algo  int
	Plain string
}

type resHash struct {
	Hash string
}

func encrypt(w rest.ResponseWriter, r *rest.Request) {
	req := policyEnc{}
	res := responseEnc{}

	err := r.DecodeJsonPayload(&req)
	if err != nil {
		rest.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	if req.Alias == "" {
		rest.Error(w, "Policy Alias required", 400)
		return
	}
	if req.Plain == "" {
		rest.Error(w, "Policy Plain required", 400)
		return
	}

	lock.Lock()
	res.Cipher = xdbEncPtr(IniFileName, req.Alias, req.Plain)
	lock.Unlock()

	w.WriteJson(&res)
}

func encryptMulti(w rest.ResponseWriter, r *rest.Request) {
	reqs := make([]policyEnc, 10)
	count := 0

	err := r.DecodeJsonPayload(&reqs)
	if err != nil {
		rest.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	for num, req := range reqs {
		if (req.Alias == "") || (req.Plain == "") {
			break
		}
		count = num
	}

	ress := make([]responseEnc, count+1)

	for num, req := range reqs {
		if (req.Alias == "") || (req.Plain == "") {
			break
		}
		lock.Lock()
		ress[num].Cipher = xdbEncPtr(IniFileName, req.Alias, req.Plain)
		lock.Unlock()
	}

	w.WriteJson(&ress)
}

func decrypt(w rest.ResponseWriter, r *rest.Request) {
	req := policyDec{}
	res := responseDec{}

	err := r.DecodeJsonPayload(&req)
	if err != nil {
		rest.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	if req.Alias == "" {
		rest.Error(w, "Policy Alias required", 400)
		return
	}
	if req.Cipher == "" {
		rest.Error(w, "Policy Cipher required", 400)
		return
	}
	lock.Lock()
	res.Plain = xdbDecPtr(IniFileName, req.Alias, req.Cipher)
	lock.Unlock()

	w.WriteJson(&res)
}

func decryptMulti(w rest.ResponseWriter, r *rest.Request) {
	reqs := make([]policyDec, 10)
	count := 0

	err := r.DecodeJsonPayload(&reqs)
	if err != nil {
		rest.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	for num, req := range reqs {
		if (req.Alias == "") || (req.Cipher == "") {
			break
		}
		count = num
	}

	ress := make([]responseDec, count+1)

	for num, req := range reqs {
		if (req.Alias == "") || (req.Cipher == "") {
			break
		}
		lock.Lock()
		ress[num].Plain = xdbDecPtr(IniFileName, req.Alias, req.Cipher)
		lock.Unlock()
	}

	w.WriteJson(&ress)
}

func getHash(w rest.ResponseWriter, r *rest.Request) {
	req := reqHash{}
	res := resHash{}

	err := r.DecodeJsonPayload(&req)
	if err != nil {
		rest.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	if req.Algo <= 0 || 9 <= req.Algo {
		rest.Error(w, "Policy Algo required", 400)
		return
	}
	if req.Plain == "" {
		rest.Error(w, "Policy Plain required", 400)
		return
	}

	lock.Lock()
	_, res.Hash = xdbHash(req.Algo, req.Plain)
	lock.Unlock()

	w.WriteJson(&res)
}

func xdbEncPtr(iniFIle string, policy string, input string) string {
	strRes := C.GoString(C.xdb_enc_ptr(C.CString(iniFIle), C.CString(policy), C.CString(input)))
	return strRes
}

func xdbDecPtr(iniFIle string, policy string, input string) string {
	strRes := C.GoString(C.xdb_dec_ptr(C.CString(iniFIle), C.CString(policy), C.CString(input)))
	return strRes
}

func xdbEnc(iniFIle string, policy string, input string) (int, string) {
	cipher := make([]byte, 256)

	nRes := C.xdb_enc(C.CString(iniFIle), C.CString(policy), C.CString(input), (*C.char)(unsafe.Pointer(&cipher[0])))

	if nRes <= 0 {
		return int(nRes), input
	}

	str := CToGoString(cipher)

	return len(str), str
}

func xdbDec(iniFIle string, policy string, input string) (int, string) {
	cipher := make([]byte, 256)

	nRes := C.xdb_dec(C.CString(iniFIle), C.CString(policy), C.CString(input), (*C.char)(unsafe.Pointer(&cipher[0])))

	if nRes <= 0 {
		return int(nRes), input
	}

	str := CToGoString(cipher)

	return len(str), str
}

func xdbHash(algo int, input string) (int, string) {
	cipher := make([]byte, 256)

	_ = C.xdb_hash(C.int(algo), C.CString(input), (*C.char)(unsafe.Pointer(&cipher[0])))

	str := CToGoString(cipher)

	return len(str), str
}

// CToGoString byte 배열을 string 으로 변경 한다.
func CToGoString(c []byte) string {
	n := -1
	for i, b := range c {
		if b == 0 {
			break
		}
		n = i
	}
	return string(c[:n+1])
}

/*
[test]
- encrypt
curl -i -X POST -H "Content-Type: application/json; " -d "{\"Alias\":\"normal\",\"Plain\":\"abcdefghi\"}" http://127.0.0.1:8080/encrypt

curl -i -X POST -H "Content-Type: application/json; " -d "[{\"Alias\":\"normal\",\"Plain\":\"abcdefghi\"}, {\"Alias\":\"pattern7\",\"Plain\":\"1234567890123\"}]" http://127.0.0.1:8080/encryptmulti

- decrypt
curl -i -X POST -H "Content-Type: application/json; " -d "{\"Alias\":\"normal\",\"Cipher\":\"AAGw+Aj3IwztG0llP8KamDEU\"}" http://127.0.0.1:8080/decrypt

curl -i -X POST -H "Content-Type: application/json; " -d "[{\"Alias\":\"normal\",\"Cipher\":\"AAGw+Aj3IwztG0llP8KamDEU\"}, {\"Alias\":\"pattern7\",\"Cipher\":\"1234567AAHN76RzQhy5D11+VFqWoH4S\"}]" http://127.0.0.1:8080/decryptmulti

- hash
curl -i -X POST -H "Content-Type: application/json; " -d "{\"Algo\":6,\"Plain\":\"123qwer\"}" http://127.0.0.1:8080/hash


curl -i -H "Content-Type: application/json; " -d "{\"Alias\":\"normal\",\"Plain\":\"abcdefghi\"}" http://127.0.0.1:8080/encrypt

*/

/*

func encrypt(w rest.ResponseWriter, r *rest.Request) {
	// get 을 사용하고 parameter 로 받으려면
	// rest.Post("/encrypt/:alias/:plain", encrypt),
	// 처럼 선언 하고 받아야 한다. 이때의 문제점은 url 인코딩 문제가 발생 할 수 있다.
	// 암호화 문자열 또는 결과 문자열 모두 문제가 될 수 있으니 반드시 json 으로 요청 하고
	// 받을 수 있도록 구현 하는게 좋다.
	alias := r.PathParam("alias")
	plain := r.PathParam("plain")
	res := policyDec{}

	fmt.Println("alias: " + alias)
	fmt.Println("plain: " + plain)

	if alias == "" {
		rest.Error(w, "Policy alias required", 400)
		return
	}
	if plain == "" {
		rest.Error(w, "Policy plain required", 400)
		return
	}

	lock.Lock()
	res.Cipher = xdbEncPtr(IniFileName, alias, plain)
	res.Alias = alias
	lock.Unlock()

	w.WriteJson(&res)
}
*/
