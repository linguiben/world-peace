package com.jupiter.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastReceiver {
    public static void main(String[] args) throws Exception {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        mcIPAddress = InetAddress.getByName(mcIPStr);
        mcSocket = new MulticastSocket(mcPort);
        mcSocket.setInterface(InetAddress.getByName("127.0.0.1"));
        System.out.println("Multicast Receiver running at:" + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        System.out.println("Waiting for a  multicast message...");
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);

        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }
}