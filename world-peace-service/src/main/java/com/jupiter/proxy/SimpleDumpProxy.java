/**
 * @className SimpleDumpProxy
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-07 01:44
 */
package com.jupiter.proxy;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-03-07 01:44
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 代理服务器
 */
@Slf4j
public final class SimpleDumpProxy {

    static final int LOCAL_PORT = 8000;
    static final String REMOTE_HOST = "www.163.com";
    static final int REMOTE_PORT = 80;

    public static void main(String[] args) throws Exception {
        log.info("Proxying *:" + LOCAL_PORT + " to " + REMOTE_HOST + ':' + REMOTE_PORT + " ...");

        // 配置 eventloop
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new SimpleDumpProxyInitializer(REMOTE_HOST, REMOTE_PORT))
                    .childOption(ChannelOption.AUTO_READ, false)
                    .bind(LOCAL_PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
