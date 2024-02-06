/**
 * @className KafkaTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:03
 */
package com.jupiter.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:03
 */
@Component
@Slf4j
public class KafkaProducerDemo {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("input-topic", message);
    }
}
