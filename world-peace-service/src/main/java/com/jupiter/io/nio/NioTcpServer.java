package com.jupiter.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc: NioTCPServer类负责处理基于Java NIO的TCP服务器逻辑。
 * 它使用选择器（Selector）来监听来自客户端的连接请求和数据发送。
 * 该服务器在接收到客户端消息时，会打印出消息内容，并允许通过控制台输入消息发送给所有已连接的客户端。
 * @author: Jupiter.Lin
 * @date: 2025/2/21
 */
public class NioTcpServer {

    // 单例模式，保证NioTCPServer只有一个实例
    private static final NioTcpServer NIO_TCP_SERVER = new NioTcpServer();
    // 用于同步控制台输出，避免多线程同时访问控制台造成输出混乱
    private final ReentrantLock lock = new ReentrantLock();

    // 选择器，用于监听和处理I/O事件
    private Selector selector;
    // 服务器套接字通道，用于接受客户端连接请求
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        // 创建一个线程，用于接受键盘输入
        NioTCPServerSender sender = new NioTCPServerSender(NIO_TCP_SERVER);
        sender.start();

        try {
            System.out.println("NioTCPServer started");
            NIO_TCP_SERVER.start("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务器，监听指定主机和端口。
     *
     * @param hostname 服务器主机名
     * @param port     服务器端口号
     * @throws IOException 如果在打开通道或选择器时发生I/O错误
     */
    public void start(String hostname, int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(hostname, port));
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        while (!Thread.currentThread().isInterrupted()) {
            this.selector.select();
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = this.serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(this.selector, SelectionKey.OP_READ);
                    // 获取客户端的地址和端口
                    SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                    System.out.println((selector.keys().size() - 1) + ", Accepted connection from " + remoteAddress);
                } else if (key.isReadable()) {
                    handleReadableKey(this.selector, key);
                }
            }
        }
    }

    /**
     * 处理可读的SelectionKey，读取客户端发送的数据。
     *
     * @param selector 选择器
     * @param key      可读的SelectionKey
     * @throws IOException 如果在读取数据时发生I/O错误
     */
    private void handleReadableKey(Selector selector, SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        SocketAddress remoteAddress = socketChannel.getRemoteAddress();
        ByteBuffer buffer = ByteBuffer.allocate(2); // 2 bytes for test each read
        StringBuilder stringBuilder = new StringBuilder(10240);
        int len = socketChannel.read(buffer);
        if (len == -1) {
            // 当客户端断开时，会触发OP_READ事件，但是读取到的数据长度为-1，此时应该关闭连接
            System.out.println("Disconnected from " + remoteAddress);
            key.cancel(); // 注销通道
            socketChannel.close();
        } else {
            // 从buffer读取数据，直到读取到-1为止
            while (len > 0) {
                buffer.flip(); // Prepare the buffer for reading
                String _received = new String(buffer.array(), 0, buffer.limit(), "UTF-8");
                stringBuilder.append(_received);
                buffer.clear(); // Clear the buffer for the next read
                len = socketChannel.read(buffer);
            }
            String received = stringBuilder.toString().trim();
            System.out.println((selector.keys().size() - 1) + ", Received from " + remoteAddress + "    " + received);
            if (received.equals("exit")) {
                key.cancel(); // 注销通道
                socketChannel.close();
            }
        }
    }

    /**
     * NioTCPServerSender类是一个内部类，负责从控制台读取输入，并将其发送给所有已连接的客户端。
     */
    public static class NioTCPServerSender extends Thread {
        private final NioTcpServer server;
        private final Scanner scanner = new Scanner(System.in);

        public NioTCPServerSender(NioTcpServer server) {
            this.server = server;
        }

        @Override
        public void run() {
            while (true) {
                // 接受键盘输入
                String line = scanner.nextLine();
                // System.out.println("Read from console: " + line);
                // 将输入的内容发送到已经连接上的客户端
                server.lock.lock();
                try {
                    Set<SelectionKey> keys = server.selector.keys();
                    for (SelectionKey key : keys) {
                        if (!key.isValid() || key.isAcceptable()) {
                            continue;
                        }
                        SocketChannel socketChannel = (SocketChannel) key.channel();
//                        if (key.isWritable()) {
                        try {
                            socketChannel.write(ByteBuffer.wrap(line.getBytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        }
                    }
                } finally {
                    server.lock.unlock();
                }
            }
        }

        @Override
        public void interrupt() {
            super.interrupt();
            scanner.close();
        }
    }

}
