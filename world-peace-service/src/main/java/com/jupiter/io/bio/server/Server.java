package com.jupiter.io.bio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * BIO Server class, responsible for listening on a specified port and handling client connections.
 */
@Component
public class Server {

    Logger logger = LoggerFactory.getLogger(Server.class);
    private final int CONNECTION_AMOUNT = 2;
    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            this.logger.error("Server start error: ",e);
        }
        int i = 1;
        while(i <= CONNECTION_AMOUNT){
            Handler handler = new Handler(serverSocket, i);
            new Thread(handler,"server-"+i).start();
            i++;
        }
    }

    // Test
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
