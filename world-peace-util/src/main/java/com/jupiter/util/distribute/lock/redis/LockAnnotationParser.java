/**
 * @className LockAnnotationParser
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:03
 */
package com.jupiter.util.distribute.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * @author Jupiter.Lin
 * @desc 切面类
 * @date 2024-04-28 10:03
 */
@Aspect
@Component
@Slf4j
public class LockAnnotationParser {
    @Resource
    private LockManager lockManager;

    /**
     * @Desc 定义切点
     */
    @Pointcut(value = "@annotation(com.jupiter.util.distribute.lock.redis.Lock)")
    private void pointCut() {
    }

    /**
     * 切点逻辑具体实现
     */
    @Around(value = "pointCut() && @annotation(lock)")
    public Object parser(ProceedingJoinPoint point, Lock lock) throws Throwable{
        String value = lock.value();
        if (isEl(value)) {
            value = getByEl(value, point);
        }
        LockResult lockResult = lockManager.lock(getRealLockKey(value), lock.expireTime(), lock.waitTime(), new Supplier<BizException>() {
            @Override
            public BizException get() {
                return null;
            }
        });
        try{
            return point.proceed();
        } finally {
            lockManager.unlock(lockResult.getRLock());
        }

    }

    /**
     * 解析 SpEl 表达式并返回其值
     */
    private String getByEl(String value, ProceedingJoinPoint point) {
        // Add code here to parse SpEl expression and return its value
        return value;
    }

    private boolean isEl(String str) {
        return str.contains("#");
    }

    /**
     * 获取锁的键值
     */
    private String getRealLockKey(String value) {
        return String.format("lock:%s",value);
    }

}
