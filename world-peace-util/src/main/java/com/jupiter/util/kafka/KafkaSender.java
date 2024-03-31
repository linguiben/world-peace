/**
 * @className KafkaTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:03
 */
package com.jupiter.util.kafka;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:03
 */
@Slf4j
@Component
@Getter
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ProducerSendListener producerSendListener;

    @Value("${spring.kafka.template.default-topic:#{null}}")
    private String dstTopic;

    @PostConstruct
    public void setProducerListener() {
        kafkaTemplate.setProducerListener(this.producerSendListener);
    }

    public void sendMessage(String message) {
        this.sendMessage(dstTopic, message);
    }

    public void sendMessage(String dstTopic, String message) {
        this.sendMessage(dstTopic,null, message);
    }
    public void sendMessage(String dstTopic, String key, String message) {
        if (null == dstTopic && this.dstTopic != null) {
            dstTopic = this.dstTopic;
        }
        kafkaTemplate.send(dstTopic,key, message);
    }
}
