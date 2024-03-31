/**
 * @className ProxyServer
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-06 23:48
 */
package com.jupiter.proxy;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-03-06 23:48
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ProxyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080); // 设置代理服务器监听的端口号为8080
        System.out.println("Waiting for client on port 8080....");
        while (true) {
            Socket clientSocket = server.accept(); // 等待客户端连接

            Thread thread = new Thread(() -> handleClientRequest(clientSocket)); // 处理每个客户端请求的线程
            thread.start();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        String requestLine = null;
        try {
            InputStream inputFromClient = clientSocket.getInputStream();
            OutputStream outputToClient = clientSocket.getOutputStream();

            requestLine = readHTTPHeader(inputFromClient).split(" ")[1];

            System.out.println("请求的url: " + requestLine);

            URL url = new URL(requestLine);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 打开与目标网站的连接

            copyResponseHeaders(connection, outputToClient); // 将目标网站返回的头部信息复制到客户端输出流中

            int responseCode = connection.getResponseCode(); // 获取目标网站的响应状态码
            if (responseCode == HttpURLConnection.HTTP_OK) {
                copyContent(connection, outputToClient); // 如果响应成功（200）则将内容从目标网站复制到客户端输出流中
            } else {
                writeErrorMessage(outputToClient, responseCode); // 否则向客户端输出错误消息
            }

            closeConnections(connection, clientSocket); // 关闭所有连接
        } catch (IOException e) {
            System.out.println("An error occurred during handling the client's request. url: " + requestLine);
            e.printStackTrace();
        }
    }

    private static String readHTTPHeader(InputStream inputStream) throws IOException {
        StringBuilder headerBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()){
            headerBuilder.append(line + "\r\n");
        }

        return headerBuilder.toString();
    }

    private static void copyResponseHeaders(HttpURLConnection connection, OutputStream outputStream) throws IOException {
        Map<String, List<String>> headersMap = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            for (String value : values) {
                outputStream.write((key + ": " + value + "\r\n").getBytes());
            }
        }

        outputStream.write("\r\n".getBytes()); // 添加空行表示头部结束
    }

    private static void copyContent(HttpURLConnection connection, OutputStream outputStream) throws IOException {
        InputStream contentInput = connection.getInputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = contentInput.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

    private static void writeErrorMessage(OutputStream outputStream, int statusCode) throws IOException {
        String message = "Proxy Error: HTTP Status Code " + statusCode;
        outputStream.write(message.getBytes());
    }

    private static void closeConnections(HttpURLConnection connection, Socket socket) throws IOException {
        connection.disconnect();
        socket.close();
    }
}

