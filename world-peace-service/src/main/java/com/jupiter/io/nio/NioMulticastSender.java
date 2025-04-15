package com.jupiter.io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.Scanner;

/**
 * @desc: NioMulticastClient类实现了基于Java NIO的多播客户端功能
 * 它负责加入多播组，并进行数据的发送和接收
 * @author: Jupiter.Lin
 * @date: 2025/2/22
 */
public class NioMulticastSender {

    private final InetAddress multicastGroup;
    private final int port;
    private final NetworkInterface networkInterface;
    private DatagramChannel datagramChannel;
    private MembershipKey membershipKey;

    public NioMulticastSender(String multicastGroup, int port, NetworkInterface networkInterface) throws IOException {
        this.multicastGroup = InetAddress.getByName(multicastGroup);
        this.port = port;
        this.networkInterface = networkInterface;
        this.datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
        this.datagramChannel.setOption(java.net.StandardSocketOptions.SO_REUSEADDR, true);
        this.datagramChannel.bind(new InetSocketAddress(port));
        this.membershipKey = datagramChannel.join(this.multicastGroup, this.networkInterface);
    }

    public static void main(String[] args) {
        try {
            NioMulticastSender nioMulticastSender = new NioMulticastSender("230.0.0.1", 8888, NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));

            // 创建并启动发送数据到多播组的线程
            NioMulticastClientSender sender = new NioMulticastClientSender(nioMulticastSender);
            sender.setDaemon(true);
            sender.setName("NioMulticastClientSender");
            sender.start();

            // 启动客户端，接收多播数据
            nioMulticastSender.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动客户端，接收多播数据
     */
    public void start() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            // 接收数据
            byteBuffer.clear();
            try {
                InetSocketAddress remoteAddress = (InetSocketAddress) datagramChannel.receive(byteBuffer);
                byteBuffer.flip(); // 切换到读模式
                String _received = new String(byteBuffer.array(), 0, byteBuffer.limit(), "UTF-8"); // 从buffer读取数据
                System.out.println("Received from " + remoteAddress + "    " + _received);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * NioMulticastClientSender类是一个内部类，用于在单独的线程中发送数据到多播组
     * 它从控制台读取用户输入，并将其发送到多播组
     */
    public static class NioMulticastClientSender extends Thread {
        private final NioMulticastSender nioMulticastSender;
        private final Scanner scanner = new Scanner(System.in);

        public NioMulticastClientSender(NioMulticastSender nioMulticastSender) {
            this.nioMulticastSender = nioMulticastSender;
        }

        @Override
        public void run() {
            while (true) {
                // 接受键盘输入
                String line = scanner.nextLine();
                // 将输入的内容发送到多播组
                try {
                    ByteBuffer buffer = ByteBuffer.wrap(line.getBytes());
                    nioMulticastSender.datagramChannel.send(buffer, new InetSocketAddress(nioMulticastSender.multicastGroup, nioMulticastSender.port));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
