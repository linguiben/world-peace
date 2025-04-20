/**
 * @className KafkaServiceImpl
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 17:20
 */
package com.jupiter.mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 17:20
 */
@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService{

    @Value("${kafka.topic.source}")
    private String sourceTopic;

    @Value("${kafka.topic.target}")
    private String targetTopic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry endpointRegistry;

    /**
    * @desc send message to kafka
     *sample: curl "http://127.0.0.1:8080/kafka/send/Hello,message1"
    * @author  Jupiter.Lin
    * @date  2024-02-06 17:56
    */
    @Override
    public void send(String message) {
        kafkaTemplate.send(this.targetTopic,message);
        log.info("message sent to kafka:{}",message);
    }

    @Override
    @KafkaListener(id = "",topics = "${kafka.topic.source}",groupId = "${kafka.topic.groupId}")
    public void receive(String message) {
        log.info("message received:{}",message);
    }

    @Override
    public void stop() {
        log.info("stopping kafka listener...");
        this.endpointRegistry.stop();
    }
}
