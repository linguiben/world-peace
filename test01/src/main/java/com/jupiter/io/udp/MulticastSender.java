package com.jupiter.io.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    private DatagramSocket socket;
    private InetAddress dstAddr;
    private byte[] bytes;

    public void multicastSend(String multicastMessage) throws  IOException {
        socket = new MulticastSocket();
        dstAddr = InetAddress.getByName("224.0.1.0");
        bytes = multicastMessage.getBytes();
        DatagramPacket packet
                = new DatagramPacket(bytes, bytes.length, dstAddr, 4446);
        socket.send(packet);
        System.out.println("sent..");
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        new MulticastSender().multicastSend("abdedf");
    }
}