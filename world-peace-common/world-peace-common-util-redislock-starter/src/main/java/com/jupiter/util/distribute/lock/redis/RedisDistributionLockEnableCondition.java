package com.jupiter.util.distribute.lock.redis;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @desc: redis 分布式锁是否启用
 * @author: Jupiter.Lin
 * @date: 2024-09-11
 */
public class RedisDistributionLockEnableCondition  implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 从环境变量或配置文件中获取配置值
        String propertyValue = context.getEnvironment().getProperty("redis.distribution.lock.enabled");

        // 判断配置项是否存在
        return propertyValue != null && !propertyValue.trim().isEmpty();
    }
}
