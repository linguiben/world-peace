/**
 * @className UtilTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-31 03:13
 */
package com.jupiter.db.kafka;

import com.jupiter.util.kafka.annotation.ToKafkaTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-03-31 03:13
 */
@SpringBootApplication
public class KafkaUtilTest {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(KafkaUtilTest.class, args);
        KafkaUtilTest bean = ctx.getBean(KafkaUtilTest.class);
        bean.toKafkaTopic(LocalDateTime.now().toString());
        bean.toKafkaTopic(LocalDateTime.now().toString());
    }


    @Value("kafka.producer.input-topic")
    private String inputTopic;
    /**
     * @param message 方法入参
     * @return 只需两步即可将数据写入kafka
     * 1. pom.xml引入依赖world-peace-util
     * 2. 在方法上增加@ToKafkaTopic，当方法被调用时，会自动将返回值写入指定的kafka topic。
     */
    @Bean
    @ToKafkaTopic("input-topic")
    public String toKafkaTopic(String message) {
        System.out.println("bean created.");
        return "test toKafkaTopic " + message;
    }
}

