/**
 * @className LockManager
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:03
 */
package com.jupiter.util.distribute.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-04-28 10:03
 */
@Slf4j
public class LockManager {
    /**
     * 最小等待时间
     */
    private static final int MIN_WAIT_TIME = 10;

    @Resource
    private RedissonClient redissonClient;

    public LockResult lock(String key, long expireTime, long waitTime, Supplier<BizException> exceptionSupplier) {
        if (waitTime < MIN_WAIT_TIME) {
            waitTime = MIN_WAIT_TIME;
        }
        LockResult result = new LockResult();
//        try {
//            if (rlock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS)) {
//
//            }
//        }

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
