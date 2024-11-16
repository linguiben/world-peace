/**
 * @className BizException
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-28 10:51
 */
package com.jupiter.util.distribute.lock.redis;

/**
 *@desc Business Exception
 *@author Jupiter.Lin
 *@date 2024-04-28 10:51
 */
public class BizException extends RuntimeException{
    public BizException(String message) {
        super(message);
    }
}

enum ResponseEnum {
    COMMON_FREQUENT_OPERATION_ERROR;
}

class BizExceptionTest {
    public static void main(String[] args) {
        throw new BizException("test");
    }
}
