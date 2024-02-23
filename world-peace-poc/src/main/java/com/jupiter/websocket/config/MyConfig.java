/**
 * @className MyConfig
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-23 00:12
 */
package com.jupiter.websocket.config;

import com.jupiter.websocket.bean.MyWebSocketClient;
import org.java_websocket.client.WebSocketClient;
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
    @Bean
    public WebSocketClient webSocketClient() {
        try {
            MyWebSocketClient webSocketClient = new MyWebSocketClient(new URI("ws://127.0.0.1:8081/api/websocket/12/owner"));
            // webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
