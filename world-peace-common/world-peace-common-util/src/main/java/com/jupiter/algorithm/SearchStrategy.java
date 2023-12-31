/**
 * @className SearchStrategy
 * @description TODO
 * @author Jupiter.Lin
 * @date 2023-12-14 00:12
 */
package com.jupiter.algorithm;

import java.util.List;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-14 00:12
 */
public interface SearchStrategy<T> {

    T search(List<T> list,T target);
}
