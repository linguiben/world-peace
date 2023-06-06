package com.jupiter.udp;

import java.io.IOException;
import java.net.*;

public class DemoSender {
    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket();
        socket.setInterface(InetAddress.getByName("127.0.0.1"));
        InetAddress groupAddr = InetAddress.getByName("224.0.1.0");

        String str = "Hello Multicast";
        byte[] bytes = str.getBytes();
        int port = 10000;

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, groupAddr, port);
        socket.send(packet);

        socket.close();
    }
}
