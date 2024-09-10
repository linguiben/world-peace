/**
 * @ClassName MathUtil
 * @Desc TODO
 * @Author Jupiter.Lin
 * @Date 2024-09-03 23:39
 */
package com.jupiter.util.math;

import java.math.BigDecimal;

/**
 * Author: Jupiter.Lin
 * Date: 2024-09-03
 * Description: 
 */
public class MathUtil {

    // 私有构造方法，防止实例化
    private MathUtil() {
        //throw new UnsupportedOperationException("MathUtil is a utility class and cannot be instantiated.");
        throw new AssertionError("MathUtil is a utility class and cannot be instantiated.");
    }

    // 获取随机数
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    // 返回一系列BigDecimal的最大者，允许输入null
    public static BigDecimal max(BigDecimal... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        BigDecimal max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] != null && (max == null || values[i].compareTo(max) > 0)) {
                max = values[i];
            }
        }
        return max;
    }
}
