package com.jupiter.io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 * @desc: NioMulticastServer类实现了基于Java NIO的多播服务器功能
 * 它负责加入多播组，并进行数据的接收和发送
 * @author: Jupiter.Lin
 * @date: 2025/2/22
 */
public class NioMulticastReceiver {

    private final InetAddress multicastGroup;
    private final int port;
    private final NetworkInterface networkInterface;
    private DatagramChannel datagramChannel;
    private MembershipKey membershipKey;

    public NioMulticastReceiver(String multicastGroup, int port, NetworkInterface networkInterface) throws IOException {
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
            NioMulticastReceiver nioMulticastReceiver = new NioMulticastReceiver("230.0.0.1", 8888, NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));

            // 启动服务器，接收多播数据
            nioMulticastReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务器，接收多播数据
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

                // 回显消息给所有多播组成员
//                byteBuffer.clear();
//                byteBuffer.put(("Echo: " + _received).getBytes());
//                byteBuffer.flip();
//                datagramChannel.send(byteBuffer, new InetSocketAddress(multicastGroup, port));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
