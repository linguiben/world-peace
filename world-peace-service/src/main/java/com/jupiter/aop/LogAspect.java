package com.jupiter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * 环绕通知，用于拦截服务层方法的执行，以记录方法执行时间
     * 该方法主要关注com.jupiter.mvc.service包下的所有服务类的方法执行情况
     *
     * @param point 切入点对象，提供了关于方法执行的详细信息
     * @return 方法执行结果
     * @throws Throwable 如果方法执行过程中抛出异常
     */
    @Around("execution(* com.jupiter.mvc.service.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 记录方法开始执行的时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法，并获取执行结果
        Object result = point.proceed();

        // 记录方法执行结束的时间
        long endTime = System.currentTimeMillis();

        // 计算并记录方法执行所花费的时间
        log.info("Method: {} executed in {}ms",
                point.getSignature().getName(),
                (endTime - startTime));

        // 返回目标方法的执行结果
        return result;
    }
}