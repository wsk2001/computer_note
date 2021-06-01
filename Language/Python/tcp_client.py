# TCP Client Socket
#
# Usage: tcp_client.py <ip> <port> <message>

import sys
from socket import *

# 메인 함수 
def main():
    if len(sys.argv) < 3:
        usage()

    client()

def usage():
    sys.stdout = sys.stderr
    print ('Usage: python tcp_client.py <ip> <port> <message>')
    print ('ex   : python tcp_client.py 127.0.0.1 9090 "test message"')
    sys.exit(2)

def client():
    if len(sys.argv) < 3:
        usage()

    host = sys.argv[1]
    port = eval(sys.argv[2])
    msg  = sys.argv[3]

    addr = host, port

    s = socket(AF_INET, SOCK_STREAM)
    
    try:
        s.connect(addr)
    except Exception as e:
        print('connection failed')
        sys.exit(2)

    print ('connected!')

    # 입력받은 텍스트를 서버로 발송
    # 입력받은 텍스트를 서버로 발송
    sent = s.send(msg.encode())
    if sent == 0:
        print("socket connection broken")
    else:
        print("data send " + msg)

    s.close()
       
main()