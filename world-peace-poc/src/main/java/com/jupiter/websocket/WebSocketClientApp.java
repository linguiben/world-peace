/**
 * @className WebSocketClientApp
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-23 00:11
 */
package com.jupiter.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *@desc 1. import Java-WebSocket
 * 2. extends WebSocketClient in MyWebSocketClient
 * 3. Create WebSocketClient in MyConfig
 * 4. send message in WebSocketClientController
 *@author Jupiter.Lin
 *@date 2024-02-23 00:11
 */
@SpringBootApplication
public class WebSocketClientApp {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketClientApp.class,args);
    }
}
