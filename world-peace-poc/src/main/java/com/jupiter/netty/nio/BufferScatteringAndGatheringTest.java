/**
 * @className BufferScatteringAndGatheringTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-04 18:12
 */
package com.jupiter.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *@desc Socket buffer size 优化的参数: see in java.nio.channels.SocketChannel
 * SO_SNDBUF The size of the socket send buffer
 * SO_RCVBUF The size of the socket receive buffer
 *@author Jupiter.Lin
 *@date 2024-02-04 18:12
 */
@Slf4j
public class BufferScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. 创建 ServerSocketChannel and SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 2. 绑定端口并启动服务端
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        serverSocketChannel.bind(inetSocketAddress);

        // 3. 创建buffer数组
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(5);
        buffers[1] = ByteBuffer.allocate(3);

        // 4. 等待客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 5. 循环读取 socketChannel.read(buffers)
        int messageLength = 8; // 假定客户端发送过来的长度是8
        while (true){
            int byteRead = 0;

            // 循环读取，直至读满buffers
            while (byteRead < messageLength){
                long read = socketChannel.read(buffers);
                byteRead += read;
                // 查看buffer.position and limit
                Arrays.asList(buffers).stream().map(buffer -> "position="+buffer.position()+",limit="+buffer.limit()).forEach(System.out::println);
            }

            // buffer.flip switch to read mode
            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            // 将数组写回buffer (显示到客户端) (未实现)
            Arrays.asList(buffers).stream().map(buffer -> "content="+new String(buffer.array())).forEach(log::info);
            //

            // buffer.clear
            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            Thread.sleep(1000);
        }
        // 6. close()
//        socketChannel.close();
//        serverSocketChannel.close();

    }
}
