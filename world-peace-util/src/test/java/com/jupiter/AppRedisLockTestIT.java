/**
 * @className AppRedisLockTestIT
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 09:56
 */
package com.jupiter;

import com.jupiter.util.distribute.lock.redis.Lock;
import com.jupiter.util.distribute.lock.redis.LockManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 *@desc 测试用注解实现Redis分布式锁
 *@author Jupiter.Lin
 *@date 2024-04-28 09:56
 */
@EnableAutoConfiguration
@SpringBootTest(classes = {KafkaUtilConfiguration.class, AppKafkaTestIT.MyTestConfig.class})
@Slf4j
public class AppRedisLockTestIT {

    @Resource
    private LockManager lockManager;

    @Lock(value="'test_'+#student.stuNo",expireTime = 3000, waitTime = 3000)
    public String testLock(Student student) {
        // 业务代码
        return "";
    }
}

@Data
class Student {
    private String stuNo;
    private String name;
    private Integer age;
}