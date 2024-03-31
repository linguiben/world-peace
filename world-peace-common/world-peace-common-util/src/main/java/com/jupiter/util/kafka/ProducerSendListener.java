/**
 * @className ProducerSendListener
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 09:00
 */
package com.jupiter.util.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 *@desc For logging while send to kafka
 *@author Jupiter.Lin
 *@date 2024-02-06 09:00
 */
@Component
@Slf4j
public class ProducerSendListener implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("msg sent: {}:{}",producerRecord.key(),producerRecord.value());
    }
}
