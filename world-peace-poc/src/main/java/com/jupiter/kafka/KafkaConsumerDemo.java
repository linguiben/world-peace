/**
 * @className KafkaConsumerDemo
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:11
 */
package com.jupiter.kafka;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.Acknowledgment;
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

    @Autowired
    private KafkaListenerEndpointRegistry endpointRegistry;

    @KafkaListener(topics = "${kafka.srcTopic}",groupId = "jupiter" )
    public void onMessage(String message,Acknowledgment acknowledgment) {
        log.info("Received message: {}" , message);
        // manually commit
        acknowledgment.acknowledge();
    }

//    @KafkaListener(topics = "input-topic", groupId = "consumer-group2")
//    public void onConsum(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
//        log.info("[KafkaConsumerDemo consumer-group2][Thread:{} msg:{}:{}]", Thread.currentThread().getId(), record.key(),
//                record.value());
//        // manually commit
//        acknowledgment.acknowledge();
//    }
}
