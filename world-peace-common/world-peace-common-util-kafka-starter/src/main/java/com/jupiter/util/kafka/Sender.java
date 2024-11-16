/**
 * @className KafkaTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:03
 */
package com.jupiter.util.kafka;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:03
 */
public interface Sender {

    void sendMessage(String message);

    void sendMessage(String dstTopic, String message);

    void sendMessage(String dstTopic, String key, String message);
}
