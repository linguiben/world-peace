/**
 * @className WebSocketClientController
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-23 00:00
 */
package com.jupiter.websocket.controllor;

import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-23 00:00
 */
@RestController
public class WebSocketClientController {
    @Autowired
    private WebSocketClient webSocketClient;

    // curl http://127.0.0.1:8080/ws/teapot
    @RequestMapping("ws/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        if (!webSocketClient.isOpen()) {
            webSocketClient.connect();
        }
        webSocketClient.send("hello sever，i want " + message);
        return "发送成功:hello sever，i want "+message;
    }
}
