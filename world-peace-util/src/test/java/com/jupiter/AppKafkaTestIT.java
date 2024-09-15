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
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *@desc 测试类
 *@author Jupiter.Lin
 *@date 2024-02-06 01:32
 */
@EnableAutoConfiguration
@SpringBootTest(classes = {KafkaUtilConfiguration.class, AppKafkaTestIT.MyTestConfig.class})
@Slf4j
public class AppKafkaTestIT {

    @Disabled
//    @Test
    public void testKafkaDemo() {
        System.out.println("com.jupiter.AppKafkaTestIT: " + test1);
        String result = Optional.ofNullable(test1).orElse("Hello Kafka!--xxxxxx");
        Assertions.assertEquals("Hello Kafka!", result.substring(0, 12));
    }

    @Autowired(required = false)
    String test1; // 必须要通过bean的方式调用，否则AOP不生效

     /**
      * @Desc 用配置类和@Bean的方式往kafka发送数据
      * @Params  @param null
      * @Return  写入kafka的字符串
      * @Author  Jupiter.Lin
      * @Date  2024-04-28 09:58
      */
    @Configuration
    static class MyTestConfig {
        @Bean
        @ToKafkaTopic({"input-topic", "output-topic"})
        @ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
        public String test1() {
            System.out.println("test kafka util.");
            return "Hello Kafka!" + "--" + LocalDateTime.now();
        }
    }
}
