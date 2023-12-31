/**
 * @className Search
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-14 00:09
 */
package com.jupiter.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-14 00:09
 */
public class Search implements ISearch<Integer>{
    private List<Integer> list = Arrays.asList(1,2,3,4,5,6);
    private int target = 2;

    @Override
    public int search(SearchStrategy<Integer> searchStrategy) {
        return searchStrategy.search(list,target);
    }

    public static void main(String[] args) {
        BinarySearchStrategy binarySearchStrategy = new BinarySearchStrategy();
        Search search = new Search();
        int location = search.search(binarySearchStrategy);
        System.out.println(location);
    }
}
