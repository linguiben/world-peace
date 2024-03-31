/**
 * @className SimpleHttpProxyServer
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-07 00:19
 */
package com.jupiter.proxy;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-03-07 00:19
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class SimpleHttpProxyServer {
    public static void main(String[] args) throws IOException {
        int port = 8888; // 代理服务器监听的端口
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("代理服务器已启动，监听端口：" + port);

        while (true) {
            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接");

            // 处理客户端请求
            handleRequest(clientSocket);
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try {
            // 获取客户端输入流和输出流
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            // 读取客户端发送的第一行HTTP请求
            String requestLine = clientReader.readLine();

            // 解析请求行，获取目标URL
            String[] parts = requestLine.split(" ");
            if (parts.length < 3) {
                clientWriter.println("HTTP/1.1 400 Bad Request");
                clientWriter.println("Content-Length: 0");
                clientWriter.println();
                clientWriter.flush();
                return;
            }

            String targetUrl = parts[1];
            URL url = new URL(targetUrl);
            System.out.println("targetUrl:" + targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置代理连接的相关参数
            connection.setRequestMethod(parts[0]);
            connection.setDoOutput(true);

            // 转发请求头到目标服务器
            String headerLine;
            while ((headerLine = clientReader.readLine()) != null && !headerLine.isEmpty()) {
                if (headerLine.trim().equals("")) {
                    break;
                }
                connection.setRequestProperty(headerLine.split(": ", 2)[0], headerLine.split(": ", 2)[1]);
            }

            // 获取目标服务器的响应
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            // 将响应码和响应消息发送给客户端
            clientWriter.println("HTTP/1.1 " + responseCode + " " + responseMessage);

            // 转发目标服务器的响应头到客户端
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            for (String headerName : headerFields.keySet()) {
                for (String headerValue : headerFields.get(headerName)) {
                    clientWriter.println(headerName + ": " + headerValue);
                }
            }

            clientWriter.println();
            clientWriter.flush();

            // 转发目标服务器的响应体到客户端
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                clientSocket.getOutputStream().write(buffer, 0, bytesRead);
                clientSocket.getOutputStream().flush();
            }

            // 关闭连接
            inputStream.close();
            connection.disconnect();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}