/**
 * @className MyConfig
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-23 00:12
 */
package com.jupiter.websocket.config;

import com.jupiter.websocket.bean.MyWebSocketClient;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-23 00:12
 */
@Configuration
public class MyConfig {

    @Value("${web-socket.server.ip}")
    private String wsServerIP;
    @Value("${web-socket.server.port}")
    private String wsServerPort;

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            String uri = "ws://"+this.wsServerIP+":"+this.wsServerPort+"/api/websocket/12/owner";
            MyWebSocketClient webSocketClient = new MyWebSocketClient(new URI(uri));
            // webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
