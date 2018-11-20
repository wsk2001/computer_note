import requests
import json

def encrypt( url, enc_data):
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post(url, data=json.dumps(enc_data), headers=headers)
    res_data = json.loads( r.text )
    return res_data['Cipher']
    
def decrypt( url, dec_data):
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post(url, data=json.dumps(dec_data), headers=headers)
    res_data = json.loads( r.text )
    return res_data['Plain']

def main():
    enc_url = "http://127.0.0.1:8080/encrypt"
    dec_url = "http://127.0.0.1:8080/decrypt"

    enc_data = {'alias': 'normal', 'plain': '1234567890123'}
    cipher_txt = encrypt( enc_url, enc_data)
    print( 'cipher_text : ' + cipher_txt)

    dec_data = {'alias': 'normal', 'cipher': cipher_txt }
    plain_text = decrypt( dec_url, dec_data)

    print( 'plain_text : ' + plain_text)

if __name__ == "__main__":
    main()
