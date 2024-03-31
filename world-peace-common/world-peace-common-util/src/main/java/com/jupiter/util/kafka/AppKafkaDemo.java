/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter.util.kafka;

import com.jupiter.annotation.KafkaSendTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 01:32
 */
@SpringBootApplication
@Slf4j
public class AppKafkaDemo {

    public static void main(String[] args) {
        SpringApplication.run(AppKafkaDemo.class, args);
    }

    // @Bean
    public CommandLineRunner commandLineRunner(KafkaProducerDemo kafkaProducerDemo) {
        return args -> {
            kafkaProducerDemo.sendMessage("Hello, Kafka!--"+ LocalDateTime.now());
            log.info("msg sent to topic {}", kafkaProducerDemo.getDstTopic());
        };
    }

    @Bean
    @KafkaSendTo({"input-topic","output-topic"})
    public String test1() {

        return "Hello Kafka!" + "--" + LocalDateTime.now();
    }
}
