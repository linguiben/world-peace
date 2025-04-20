/**
 * @className AppWebSocket
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-01 23:56
 */
package com.jupiter.websocket;

import com.jupiter.mvc.controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-04-01 23:56
 */
@SpringBootApplication // (scanBasePackageClasses={com.jupiter.controller.IndexController.class})
@Import(IndexController.class)
public class AppWebSocket {
    public static void main(String[] args) {
        SpringApplication.run(AppWebSocket.class, args);
    }
}
