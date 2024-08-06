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
public class KafkaProducerDemo {

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
    /**
     * @Desc TODO
     * @Params  @param null
     * @Return
     * @Author  Jupiter.Lin
     * @Date  2024-04-12 18:20
     */
    public void sendMessage(String dstTopic, String message) {
        if (null == dstTopic && this.dstTopic != null) {
            dstTopic = this.dstTopic;
        }
        kafkaTemplate.send(dstTopic, message);
    }

    /**
     * 发送消息到指定的主题。
     *
     * @param dstTopic 目标主题名称，消息将被发送到此主题。
     * @param key 消息的键，用于分区和检索消息。
     * @param message 要发送的消息内容。
     */
    public void sendMessage(String dstTopic, String key, String message) {
        kafkaTemplate.send(dstTopic, key, message); // 使用KafkaTemplate发送消息到指定主题
    }

}
