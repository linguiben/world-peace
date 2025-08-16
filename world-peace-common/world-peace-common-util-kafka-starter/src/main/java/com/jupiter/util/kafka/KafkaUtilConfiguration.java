/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter.util.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
@ComponentScan(
        basePackages="com.jupiter.util.kafka",
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
//        SpringApplication.run(KafkaUtilConfiguration.class, args);
//    }
//
//    @Bean
//    public CommandLineRunner commandLineRunner(KafkaSender kafkaProducerDemo) {
//        return args -> {
//            kafkaProducerDemo.sendMessage("Hello, Kafka!--" + LocalDateTime.now());
//            log.info("msg sent to topic {}", kafkaProducerDemo.getDstTopic());
//        };
//    }
}
