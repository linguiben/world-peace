package com.jupiter.util.distribute.lock.redis;/**
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:02
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@className Lock
 *@desc 分布式锁注解
 *@author Jupiter.Lin
 *@date 2024-04-28 10:02
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    /**
     * lock key
     */
    String value();

    /**
     * 超时时间
     */
    long expireTime() default 5000L;

    /**
     * 获取锁超时时间
     */
    long waitTime() default 100L;
}
