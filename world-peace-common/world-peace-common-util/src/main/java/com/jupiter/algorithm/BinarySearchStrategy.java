/**
 * @className BinerySearchStrategy
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-14 00:07
 */
package com.jupiter.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-14 00:07
 */
@Slf4j
public class BinarySearchStrategy implements SearchStrategy<Integer>{
    @Override
    public Integer search(List<Integer> list,Integer target) {
        Integer[] nums = list.toArray(new Integer[0]); // 创建数组必须指定长度
        int start=0, end=nums.length-1;
        while (start <= end){
            int mid = (start + end)/2;
            log.info("[start,mid,end]: {},{},{}",start,mid,end);
            if(nums[mid]==target)
                return mid;
            if(nums[mid] < target)
                start = mid +1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        BinarySearchStrategy instant = new BinarySearchStrategy();
        System.out.println(instant.search(list, 7));
    }

}
