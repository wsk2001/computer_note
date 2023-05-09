import socket
import logging

# �α� ����
logging.basicConfig(filename='app.log', level=logging.INFO)

# TCP ����
def tcp_server():
    # ���� ���� ����
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('localhost', 8000))
    server_socket.listen(1)

    # ���� ���
    conn, addr = server_socket.accept()
    logging.info(f'Connected by {addr}')

    # ������ ���� �� �α�
    while True:
        data = conn.recv(1024)
        if not data:
            break
        logging.info(data.decode())

    # ���� ����
    conn.close()

# UDP ����
def udp_server():
    # ���� ���� ����
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind(('localhost', 8000))

    # ������ ���� �� �α�
    while True:
        data, addr = server_socket.recvfrom(1024)
        logging.info(data.decode())

# ���� �Լ�
def main():
    # TCP ���� ����
    tcp_server()

    # UDP ���� ����
    # udp_server()

if __name__ == '__main__':
    main()
