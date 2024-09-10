/**
 * @className ProducerSendListener
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 09:00
 */
package com.jupiter.util.kafka.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @author Jupiter.Lin
 * @desc For logging while send to kafka
 * @date 2024-02-06 09:00
 */
@Component
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
@Slf4j
public class ProducerSendListener implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.debug("sent to kafka topic:{}. {}:{}", producerRecord.topic(), producerRecord.key(),
                producerRecord.value());
    }
}
