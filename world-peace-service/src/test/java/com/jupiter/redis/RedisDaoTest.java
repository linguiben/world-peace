package com.jupiter.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-14 22:30
 */

@SpringBootTest(properties="application-dev.yml",classes = RedisDao.class)
class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Test
    void testDemo(){
        this.redisDao.demo();
    }
}