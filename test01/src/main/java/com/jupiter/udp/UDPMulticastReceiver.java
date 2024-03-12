package com.jupiter.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastReceiver {
    public static void main(String[] args) throws Exception {
        // System.setProperty("java.net.preferIPv4Stack", "true");
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        mcIPAddress = InetAddress.getByName(mcIPStr);
        mcSocket = new MulticastSocket(mcPort);

        System.setProperty("java.net.preferIPv4Stack", "true");
        // setInterface using network IP instead of 127.0
        mcSocket.setInterface(InetAddress.getByName("192.168.6.75"));
        System.out.println("Multicast Receiver running at:" + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);
        System.out.println("UdpReceiver joinGroup: " + mcIPAddress + "\t, listen to "+mcSocket.getInterface() +":"+ mcSocket.getLocalPort() );

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);

        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }
}