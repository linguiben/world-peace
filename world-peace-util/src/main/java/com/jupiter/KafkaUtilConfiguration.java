/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Jupiter.Lin
 * @desc The Configuration Class for KafkaUtil
 * @date 2024-02-06 01:32
 */
@Slf4j
@Configuration
@ComponentScan(
        basePackages="com.jupiter",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {TypeExcludeFilter.class}
        ), @ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {AutoConfigurationExcludeFilter.class}
        )}
)
public class KafkaUtilConfiguration {

    //    public static void main(String[] args) {
//        SpringApplication.run(AppKafkaDemo.class, args);
//    }
//
//    // @Bean
//    public CommandLineRunner commandLineRunner(KafkaSender kafkaProducerDemo) {
//        return args -> {
//            kafkaProducerDemo.sendMessage("Hello, Kafka!--"+ LocalDateTime.now());
//            log.info("msg sent to topic {}", kafkaProducerDemo.getDstTopic());
//        };
//    }
//
}
