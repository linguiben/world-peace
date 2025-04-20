/**
 * @className KafkaService
 * @description TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 17:18
 */
package com.jupiter.mvc.service;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 17:18
 */
public interface KafkaService {
    void send(String message);
    void receive(String message);

    void stop();
}
