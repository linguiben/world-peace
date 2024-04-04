/**
 * @className WebSocketConfig
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-01 23:38
 */
package com.jupiter.admin.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *@desc The configuration class for Websocket
 *@author Jupiter.Lin
 *@date 2024-04-01 23:38
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}