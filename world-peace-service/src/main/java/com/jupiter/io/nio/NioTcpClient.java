package com.jupiter.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @desc: NioTcpClient类实现了基于Java NIO的TCP客户端功能
 * 它负责与服务器建立连接，并进行数据的发送和接收
 * @author: Jupiter.Lin
 * @date: 2025/2/22
 */
public class NioTcpClient {

    // SocketChannel用于与服务器之间的通信
    private SocketChannel socketChannel;

    public static void main(String[] args) {
        NioTcpClient nioTcpClient = new NioTcpClient();

        // 创建并启动发送数据到服务器的线程
        NioTCPClientSender sender = new NioTCPClientSender(nioTcpClient);
        sender.setDaemon(true);
        sender.start();

        // 启动客户端，连接到指定的主机和端口
        nioTcpClient.start("localhost", 8888);
    }

    /**
     * 启动客户端，连接到指定的主机和端口
     *
     * @param hostname 服务器主机名
     * @param port     服务器端口号
     */
    public void start(String hostname, int port) {
        // 连接上服务端
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(hostname, port));
            SocketAddress remoteAddress = socketChannel.getRemoteAddress();
            System.out.println("Connected to server at " + remoteAddress);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                // 接收数据
                byteBuffer.clear();
                int len = socketChannel.read(byteBuffer);// 从channel读取数据到buffer
                if (len == -1) {
                    socketChannel.close();
                    System.out.println("Connection closed by server " + remoteAddress);
                    break;
                }
                byteBuffer.flip(); // 切换到读模式
                String _received = new String(byteBuffer.array(), 0, byteBuffer.limit(), "UTF-8");// 从buffer读取数据
                System.out.println("Received from " + remoteAddress + "    " + _received);
            }

            // 关闭连接
            // socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NioTCPClientSender类是一个内部类，用于在单独的线程中发送数据到服务器
     * 它从控制台读取用户输入，并将其发送到服务器
     */
    public static class NioTCPClientSender extends Thread {
        private final NioTcpClient nioTcpClient;
        private final Scanner scanner = new Scanner(System.in);

        public NioTCPClientSender(NioTcpClient nioTcpClient) {
            this.nioTcpClient = nioTcpClient;
        }

        @Override
        public void run() {
            while (true) {
                // 接受键盘输入
                String line = scanner.nextLine();
                // System.out.println("Read from console: " + line);
                // 将输入的内容发送到服务端
                try {
                    nioTcpClient.socketChannel.write(ByteBuffer.wrap(line.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
