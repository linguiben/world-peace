/**
 * @className AppRedisLockTestIT
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 09:56
 */
package com.jupiter;

import com.jupiter.util.distribute.lock.redis.Lock;
import com.jupiter.util.distribute.lock.redis.RedissonConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *@desc 测试用注解实现Redis分布式锁
 *@author Jupiter.Lin
 *@date 2024-04-28 09:56
 */
@EnableAutoConfiguration
@SpringBootTest(classes = {RedissonConfiguration.class, AppRedisLockTestIT.MyTestConfig.class})
@Slf4j
public class AppRedisLockTestIT {

    @Autowired(required = false)
    String testLock;

    @Test
    public void testLockDemo() {
        System.out.println("com.jupiter.AppRedisLockTestIT: " + testLock);
        String result = Optional.ofNullable(testLock).orElse("Jupiter");
        assertEquals("Jupiter", result);
    }


    @Configuration
    static class MyTestConfig {
        @Bean
        public Student student() {
            return new Student("123456","Jupiter",18);
        }

        @Bean
        @ConditionalOnProperty(prefix = "redis.distribution.lock", name = "enabled", havingValue = "true")
        @Lock(value="'test_'+#student.stuNo",expireTime = 10000, waitTime = 3000)
        public String testLock(Student student) throws InterruptedException {
            // 业务代码
            System.out.println("testLock");
            Thread.sleep(100);
            return student.getName();
        }
    }
}

@Data@AllArgsConstructor
class Student {
    private String stuNo;
    private String name;
    private Integer age;
}

