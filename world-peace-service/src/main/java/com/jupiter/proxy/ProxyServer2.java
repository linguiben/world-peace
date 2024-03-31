/**
 * @className ProxyServer2
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-06 23:49
 */
package com.jupiter.proxy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-03-06 23:49
 */
public class ProxyServer2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Proxy Server started on port 8888");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ProxyThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ProxyThread extends Thread {
    private Socket clientSocket;

    public ProxyThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String request = clientReader.readLine();
            System.out.println("Received request from client: " + request);

            String[] tokens = request.split(" ");
            String method = tokens[0];
            String url = tokens[1];

            Socket serverSocket = new Socket("www.example.com", 80);
            BufferedWriter serverWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            serverWriter.write(request);
            serverWriter.flush();

            String response;
            while ((response = serverReader.readLine()) != null) {
                clientWriter.write(response);
                clientWriter.flush();
            }

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
