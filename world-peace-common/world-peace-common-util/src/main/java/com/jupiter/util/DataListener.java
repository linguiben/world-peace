/**
 * @className DataListener
 * @description TODO
 * @author Jupiter.Lin
 * @date 2023-12-10 03:17
 */
package com.jupiter.util;

/**
 *@desc 监听器的功能是可以接受数据
 *@author Jupiter.Lin
 *@date 2023-12-10 03:17
 */
public interface DataListener<D extends Data> {

    /**
     * Receive data from distributor.
     */
    void receiveData(final D data);

}
