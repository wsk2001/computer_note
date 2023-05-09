import socket
import logging

# 로깅 설정
logging.basicConfig(filename='app.log', level=logging.INFO)

# TCP 서버
def tcp_server():
    # 서버 소켓 생성
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('localhost', 8000))
    server_socket.listen(1)

    # 연결 대기
    conn, addr = server_socket.accept()
    logging.info(f'Connected by {addr}')

    # 데이터 수신 및 로깅
    while True:
        data = conn.recv(1024)
        if not data:
            break
        logging.info(data.decode())

    # 연결 종료
    conn.close()

# UDP 서버
def udp_server():
    # 서버 소켓 생성
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind(('localhost', 8000))

    # 데이터 수신 및 로깅
    while True:
        data, addr = server_socket.recvfrom(1024)
        logging.info(data.decode())

# 메인 함수
def main():
    # TCP 서버 실행
    tcp_server()

    # UDP 서버 실행
    # udp_server()

if __name__ == '__main__':
    main()
