package com.jupiter.logic;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter.Lin
 * @desc TODO  动态规划算法，计算最长的递增序列的长度
 * @date 2023-09-17 09:40
 */
public class Dynamic {
    int[] arr = {2,1,5,3,4};

    // 1. 暴力枚举 (递归算法)
    public int ran(int[] arr, int i){
        // ArrayList<Integer> result = new ArrayList<>(arr.length);
        int maxLength = 1;
        for(int j=i+1;j<arr.length;j++){
            if(arr[i] < arr[j]){
                maxLength = Math.max(maxLength,ran(arr,j)+1);
            }
        }
        return maxLength;
    }

    private Map<Integer, List<Integer>> memo = new HashMap<>();
    public int ranMem(int[] arr, int i){
        // ArrayList<Integer> result = new ArrayList<>(arr.length);
        int maxLength = 1;
        for(int j=i+1;j<arr.length;j++){
            if(arr[i] < arr[j]){
                maxLength = Math.max(maxLength,ran(arr,j)+1);

            }
        }
        return maxLength;
    }

    @Test
    public void testRan(){
        Dynamic instance = new Dynamic();
        System.out.println(instance.ran(instance.arr, 0));
    }

    public static void main(String[] args) {
        Dynamic instance = new Dynamic();
        System.out.println(instance.ran(instance.arr, 0));
    }
}

