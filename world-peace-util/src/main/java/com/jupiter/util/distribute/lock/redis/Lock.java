package com.jupiter.util.distribute.lock.redis;/**
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:02
 */

/**
 *@className Lock
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-04-28 10:02
 */
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
