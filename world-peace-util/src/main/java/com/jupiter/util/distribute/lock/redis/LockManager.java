package com.jupiter.util.distribute.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @desc The class RedisLockManager
 * @author Jupiter.Lin
 * @date 2024-04-28 10:03
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "redis.distribution.lock", name = "enabled", havingValue = "true")
public class LockManager {
    /**
     * 最小等待时间
     */
    private static final int MIN_WAIT_TIME = 10;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取锁，加锁失败时抛出异常
     *
     * @param key
     * @param expireTime
     * @param waitTime
     * @return LockResult
     * @author Jupiter.Lin
     * @date 2024-09-10
     */
    public LockResult lock(String key, long expireTime, long waitTime) {
        return lock(key, expireTime, waitTime, () -> new BizException("ResponseEnum.COMMON_FREQUENT_OPERATION_ERROR"));
    }

    private LockResult lock(String key, long expireTime, long waitTime, Supplier<BizException> exceptionSupplier) {
        if (waitTime < MIN_WAIT_TIME) {
            waitTime = MIN_WAIT_TIME;
        }
        LockResult result = new LockResult();
        try {
            RLock rLock = redissonClient.getLock(key);
            try {
                if (rLock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS)) {
                    result.setLockResultStatus(LockResultStatus.SUCCESS);
                    result.setRLock(rLock);
                } else {
                    result.setLockResultStatus(LockResultStatus.FAIL);
                }
            } catch (InterruptedException e) {
                log.error("Redis 获取分布式锁失败, key: {}, e: {}", key, e.getMessage());
                result.setLockResultStatus(LockResultStatus.TIMEOUT);
                rLock.unlock();
            }
        } catch (Exception e) {
            log.error("Redis 获取分布式锁失败, key: {}, e: {}", key, e.getMessage());
            result.setLockResultStatus(LockResultStatus.TIMEOUT);
        }

        if (exceptionSupplier != null && LockResultStatus.FAIL.equals(result.getLockResultStatus())) {
            log.warn("Redis 加锁失败, key: {}", key);
            throw exceptionSupplier.get();
        }

        log.info("Redis 加锁结果：{}, key: {}", result.getLockResultStatus(), key);

        return result;
    }


    /**
     * 解锁
     */
    public void unlock(RLock lock) {
        if (lock != null && lock.isHeldByCurrentThread()) {
            try {
                lock.unlock();
            } catch (Exception e) {
                log.warn("Redis 解锁失败", e);
            }
        }
    }

}
