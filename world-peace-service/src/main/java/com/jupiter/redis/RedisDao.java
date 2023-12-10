package com.jupiter.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author Jupiter.Lin
 * @desc Test SpringBoot.RedisTemplate and redis function
 * @date 2023-07-14 20:43
 */

@Repository
@Slf4j
public class RedisDao {

    //@Resource
    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate redisTemplate;

    public void demo(){
        ValueOperations<String,String> strOps = this.redisTemplate.opsForValue();
        Object marks = strOps.get("Jupiter");
        log.info("Jupiter: {}",marks);
    }
}
