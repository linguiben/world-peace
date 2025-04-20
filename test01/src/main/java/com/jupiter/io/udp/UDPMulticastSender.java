package com.jupiter.io.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastSender {
    public static void main(String[] args) throws Exception {
        /*
         * 公共多播地址  （224.0.1.0 - 238.255.255.255）
         * 局域网多播地址（239.0.0.0 - 239.255.255.255）
         * 需要配置路由器：大多数家庭和企业路由器默认会阻止多播流量通过互联网。你需要配置路由器以允许多播流量。
         * 这通常涉及设置IGMP（Internet Group Management Protocol）代理或静态多播路由。
         */
        InetAddress mcIPAddress = InetAddress.getByName("230.1.1.1");
        int mcPort = 12345;

        //DatagramSocket udpSocket = new DatagramSocket();
        MulticastSocket udpSocket = new MulticastSocket();
        // 设置本地接口(公网IP地址)，确保数据包从正确的网络接口发出去，本地测试时可以注释掉
        // udpSocket.setInterface(InetAddress.getByName("192.168.6.75"));

        byte[] msg = "Hello Kitty.".getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, mcIPAddress, mcPort);

        // packet.setAddress(InetAddress.getByName("127.0.0.1"));
        udpSocket.send(packet);

        System.out.println("MulticastSocket sent. packet: " + packet.getAddress() + ":" + packet.getPort());
        udpSocket.close();
    }
}