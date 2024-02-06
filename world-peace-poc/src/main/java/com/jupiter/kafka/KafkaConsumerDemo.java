/**
 * @className KafkaConsumerDemo
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:11
 */
package com.jupiter.kafka;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:11
 */
@Slf4j
@Component
@Getter
public class KafkaConsumerDemo {

    @Value("${kafka.srcTopic}")
    private String srcTopic;

    @KafkaListener(topics = "input-topic",groupId = "jupiter" )
    public void consumer(String message) {
        log.info("Received message: {}" , message);
    }
}
