/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter;

import com.jupiter.kafka.KafkaProducerDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:32
 */
@SpringBootTest
@Slf4j
public class KafkaConsumerDemoTest {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoTest.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(KafkaProducerDemo kafkaProducerDemo) {
        return args -> {
            kafkaProducerDemo.sendMessage("Hello, Kafka!");
        };
    }
}
