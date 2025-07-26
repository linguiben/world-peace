package com.jupiter.io.tcp;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/16
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class TCPForwarder {

    public static void main(String[] args) throws IOException {
        String localHost = "0.0.0.0"; // 本地监听地址
        int localPort = 12345;         // ip1:portA
        String remoteHost = "127.0.0.1";
        int remotePort = 9000;        // ip2:portB

        new TCPForwarder().start(localHost, localPort, remoteHost, remotePort);
    }

    public void start(String localHost, int localPort, String remoteHost, int remotePort) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(localHost, localPort));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("TCP Proxy started on " + localHost + ":" + localPort +
                " -> " + remoteHost + ":" + remotePort);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (key.isAcceptable()) {
                    acceptConnection(key, remoteHost, remotePort, selector);
                } else if (key.isReadable()) {
                    forwardData(key);
                }
            }
        }
    }

    private void acceptConnection(SelectionKey key, String remoteHost, int remotePort, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);

        // Connect to remote server
        SocketChannel remoteChannel = SocketChannel.open();
        remoteChannel.connect(new InetSocketAddress(remoteHost, remotePort));
        remoteChannel.configureBlocking(false);

        // Register both channels
        ProxyConnection connection = new ProxyConnection(clientChannel, remoteChannel);
        connection.registerWithSelector(selector);
    }

    private void forwardData(SelectionKey key) {
        ProxyConnection connection = (ProxyConnection) key.attachment();
        try {
            connection.readAndForward(key);
        } catch (IOException e) {
            System.err.println("Connection closed: " + e.getMessage());
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class ProxyConnection {
        private final SocketChannel inbound;
        private final SocketChannel outbound;
        private final ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);

        public ProxyConnection(SocketChannel inbound, SocketChannel outbound) {
            this.inbound = inbound;
            this.outbound = outbound;
        }

        public void registerWithSelector(Selector selector) throws IOException {
            inbound.register(selector, SelectionKey.OP_READ, this);
            outbound.register(selector, SelectionKey.OP_READ, this);
        }

        public void readAndForward(SelectionKey key) throws IOException, ClassNotFoundException {
            SocketChannel source = (SocketChannel) key.channel();
            SocketChannel target = source == inbound ? outbound : inbound;

            int numRead = source.read(buffer);
            if (numRead == -1) {
                close();
                return;
            }

            if (numRead > 0) {
                buffer.flip();
                target.write(buffer);
                buffer.compact();
            }
        }

        public void close() {
            try {
                inbound.close();
            } catch (IOException ignored) {
            }
            try {
                outbound.close();
            } catch (IOException ignored) {
            }
        }
    }
}
