/**
 * @className WebSocketConfig
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-22 21:35
 */
package com.jupiter.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *@desc 管理websocket服务
 *@author Jupiter.Lin
 *@date 2024-02-22 21:35
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}

