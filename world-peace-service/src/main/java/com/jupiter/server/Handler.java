package com.jupiter.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Handler implements Runnable{

    Logger logger = LoggerFactory.getLogger(Server.class);

    public Handler(ServerSocket serverSocket, Integer id) {
        this.serverSocket = serverSocket;
        this.id = id;
    }

    public Handler() {
    }

    private ServerSocket serverSocket;

    private Socket socket;
    private Integer id;


    public void acceptConnect(){
        InputStream inputStream;
        try {
            socket = serverSocket.accept();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            this.logger.info("Accepted connect from: " + remoteSocketAddress);
            inputStream = socket.getInputStream();
            receiveRequest(inputStream);
        } catch (IOException e) {
            this.logger.info("connection error",e);
        }
    }

    public void receiveRequest(InputStream inputStream){
        StringBuffer sb = new StringBuffer();
        byte[] bytes = new byte[0X10];
        try {
            int len;
            do {
                len = inputStream.read(bytes);
                String str = new String(bytes, 0, len);
                sb.append(str);
                if(str.endsWith("\n")){
                    break;
                }
            } while (len != -1);
        } catch (IOException e) {
            this.logger.error("receive Request error: {}",e);
        }
        String uri = sb.toString();
        this.logger.info("Server-【" + this.id + "】received request: " + sb);
        executeRequest(uri);
    }

    public void executeRequest(String uri){
        String response = uri + " [DONE]";
        sendResponse(response);
    }

    public void sendResponse(String response){
        try (OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(response.getBytes());
            outputStream.flush();
            logger.info("send response: {}",response);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void disconnect (){
//        socket.close();
//    }

    @Override
    public void run() {
        System.out.println("Server-【"+this.id + "】is ready!");
        while(true){
            acceptConnect();

        }
    }
}
