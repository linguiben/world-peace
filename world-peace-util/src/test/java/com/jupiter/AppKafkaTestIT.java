/**
 * @className AppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 01:32
 */
package com.jupiter;

import com.jupiter.util.kafka.annotation.ToKafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 *@desc 测试类
 *@author Jupiter.Lin
 *@date 2024-02-06 01:32
 */
@EnableAutoConfiguration
@SpringBootTest(classes = {KafkaUtilConfiguration.class, AppKafkaTestIT.MyTestConfig.class})
@Slf4j
public class AppKafkaTestIT {

    @Resource
    String test1;

    @Test
    public void testKafkaDemo() {
        System.out.println("ok: "+test1);
         Assertions.assertEquals("Hello Kafka!",test1.substring(0,12));
    }

    @Configuration
    static class MyTestConfig {
        @Bean
        @ToKafkaTopic({"input-topic", "output-topic"})
        public String test1() {
            System.out.println("test kafka util.");
            return "Hello Kafka!" + "--" + LocalDateTime.now();
        }
    }
}
