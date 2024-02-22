/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter.kafka;

import com.jupiter.kafka.bean.KafkaProducerDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:32
 */
@SpringBootApplication
@Slf4j
public class KafkaDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(KafkaProducerDemo kafkaProducerDemo) {
        return args -> {
            kafkaProducerDemo.sendMessage("Hello, Kafka!");
            log.info("msg sent to topic");
        };
    }
}
