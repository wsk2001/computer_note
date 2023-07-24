# SSL Server And Client in cpp

Reference: Google Bard



`ssl_server.cpp`

``` cpp
#include <iostream>
#include <string>
#include <ssl.h>

int main() {
  // Create a new SSL context.
  SSL_CTX* ctx = SSL_CTX_new(TLS_method());

  // Set the ciphers to use.
  SSL_CTX_set_cipher_list(ctx, "DEFAULT");

  // Create a new SSL socket.
  SSL* ssl = SSL_new(ctx);

  // Bind the socket to port 443.
  int sockfd = socket(AF_INET, SOCK_STREAM, 0);
  sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_port = htons(443);
  addr.sin_addr.s_addr = INADDR_ANY;
  bind(sockfd, (sockaddr*)&addr, sizeof(addr));
  listen(sockfd, 10);

  // Accept connections.
  while (true) {
    int clientfd = accept(sockfd, NULL, NULL);

    // Handshake with the client.
    SSL_accept(ssl);

    // Send a message to the client.
    std::string message = "Hello, world!";
    SSL_write(ssl, message.c_str(), message.length());

    // Close the connection.
    SSL_close(ssl);
    close(clientfd);
  }

  return 0;
}

```



`ssl_client.cpp`

``` cpp
#include <iostream>
#include <string>
#include <ssl.h>

int main() {
  // Create a new SSL context.
  SSL_CTX* ctx = SSL_CTX_new(TLS_method());

  // Set the ciphers to use.
  SSL_CTX_set_cipher_list(ctx, "DEFAULT");

  // Create a new SSL socket.
  SSL* ssl = SSL_new(ctx);

  // Connect to the server.
  int sockfd = socket(AF_INET, SOCK_STREAM, 0);
  sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_port = htons(443);
  addr.sin_addr.s_addr = inet_addr("localhost");
  connect(sockfd, (sockaddr*)&addr, sizeof(addr));

  // Handshake with the server.
  SSL_connect(ssl);

  // Send a message to the server.
  std::string message = "Hello, world!";
  SSL_write(ssl, message.c_str(), message.length());

  // Receive a message from the server.
  char buffer[1024];
  int bytes_received = SSL_read(ssl, buffer, sizeof(buffer));
  std::string response(buffer, buffer + bytes_received);

  // Close the connection.
  SSL_close(ssl);
  close(sockfd);

  // Print the response from the server.
  std::cout << response << std::endl;

  return 0;
}

```

