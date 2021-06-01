import select, socket, sys, json, signal

def main():
    signal.signal(signal.SIGINT,signal_handling) 

    if len(sys.argv) == 2:
        port = eval(sys.argv[1])
    else:
        usage()

    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setblocking(0)
    server.bind(('', port))
    server.listen(5)
    inputs = [server]

    while inputs:
        readable, _, _ = select.select(inputs, [], [])
        for s in readable:
            if s is server:
                connection, client_address = s.accept()
                connection.setblocking(0)
                inputs.append(connection)
            else:
                data = s.recv(1024).decode('utf-8')
                if data:
                    print (data)
                    #s.send(data)
                else:
                    inputs.remove(s)
                    s.close()

# ctrl+c handler
def signal_handling(signum,frame):           
    sys.exit(0)

def usage():
    sys.stdout = sys.stderr
    print ('Usage: python select_server.py <port>')
    print ('ex   : python select_server.py 9090')
    sys.exit(2)

# execute only if run as a script
if __name__ == "__main__":
    main()
