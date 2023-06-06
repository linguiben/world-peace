package com.jupiter.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class DemoReceiver {
    public static void main(String[] args) throws IOException {
        // System.setProperty("java.net.preferIPv4Stack","true");
        MulticastSocket socket = new MulticastSocket(10000);
        socket.setInterface(InetAddress.getByName("127.0.0.1"));
        socket.joinGroup(InetAddress.getByName("224.0.1.0"));

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        socket.receive(packet);

        byte[] data = packet.getData();
        int length = packet.getLength();
        System.out.println(new String(data, 0, length));
        socket.close();
    }
}



