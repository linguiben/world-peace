/**
 * @className BIOServer
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-03 13:27
 */
package com.jupiter.netty.bio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@desc Bio Server
 *@author Jupiter.Lin
 *@date 2024-02-03 13:27
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BIOServer {
    // 1.创建一个线程池
    // 2.当客户端连接时，创建一个新的线程与之通讯( communicateHandler)
    private int port;

    public void serverStart() throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // the serverSocket
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("BIOServer Started, listening: {}", port);

        while (true){
            // listening, awaiting client
            Socket socket = serverSocket.accept();
            log.info("client {}:{} connected!",socket.getInetAddress(),socket.getPort());
            threadPool.execute(()->{
                communicateHandler(socket);
            });
        }
    }

    /**
    * @desc print the msg that client sent
    * @author  Jupiter.Lin
    * @date  2024-02-03 23:41
    *
    */
    private void communicateHandler(Socket socket) throws RuntimeException{
        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        // jdk 1.8 try with resources
        try {
            // socket get inputStream
            inputStream = socket.getInputStream();
            while(true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    log.info("client {}:{}: {}",socket.getInetAddress(),socket.getPort(),new String(bytes,0,read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            log.error("communication error with {}:{}",socket.getInetAddress(),socket.getPort(),e);
            throw new RuntimeException(e.getMessage());
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BIOServer bioServer = new BIOServer();
        bioServer.setPort(6666);
        bioServer.serverStart();
    }

}
