/**
 * @className Queuess
 * @description TODO
 * @author Jupiter.Lin
 * @date 2023-09-09 11:39
 */
package com.jupiter.util;

import java.util.List;

/**
 *@desc A Common util of queue
 *@author Jupiter.Lin
 *@date 2023-09-09 11:39
 */
public interface Queue<D extends Object> {

    void put(D d);

    D get() throws InterruptedException;

    List<D> getAll() throws InterruptedException;

    /**
     * 返回Queue中剩余的数量
     * @return
     */
    int remains();

    void clear();

    void shutdown();
}
