package com.jupiter.test;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/3/12
 */
public class T02 {
    public static void main(String[] args) {
        while(true) {
            System.out.println("Hello, World!" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
