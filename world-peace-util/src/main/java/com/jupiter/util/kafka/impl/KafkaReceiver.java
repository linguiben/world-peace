/**
 * @className KafkaConsumerDemo
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:11
 */
package com.jupiter.util.kafka.impl;

import com.jupiter.util.kafka.Receiver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.Acknowledgment;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:11
 */
@Slf4j
//@Component
@Getter
public class KafkaReceiver implements Receiver {

    @Value("${kafka.srcTopic:#{null}}")
    private String srcTopic;

    @Autowired
    private KafkaListenerEndpointRegistry endpointRegistry;

    @KafkaListener(topics = "${kafka.srcTopic}",groupId = "jupiter" )
    //@KafkaListener(topics = "#{'${kafka.topic.ping}'.split(',')}") //从配置文件中获取，并按逗号分割
    //@KafkaListener(topics = "#{pingProperties.getTopic().split(',')}")//从bean属性中获取，并按逗号分割
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
