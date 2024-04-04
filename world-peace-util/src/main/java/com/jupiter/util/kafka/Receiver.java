/**
 * @className KafkaConsumerDemo
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:11
 */
package com.jupiter.util.kafka;

import org.springframework.kafka.support.Acknowledgment;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:11
 */
public interface Receiver {

    public void onMessage(String message,Acknowledgment acknowledgment);

}
