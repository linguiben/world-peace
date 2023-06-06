package com.jupiter.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastSender {
    public static void main(String[] args) throws Exception {
        InetAddress mcIPAddress = InetAddress.getByName("230.1.1.1");
        int mcPort = 12345;
        //DatagramSocket udpSocket = new DatagramSocket();
        MulticastSocket udpSocket = new MulticastSocket();
        udpSocket.setInterface(InetAddress.getByName("127.0.0.1"));
        byte[] msg = "Hello".getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length,mcIPAddress,mcPort);
        // packet.setAddress(InetAddress.getByName("127.0.0.1"));
        udpSocket.send(packet);

        System.out.println("Sent a  multicast message.");
        System.out.println("Exiting application");
        udpSocket.close();
    }
}