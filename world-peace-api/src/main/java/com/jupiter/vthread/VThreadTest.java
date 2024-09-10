package com.jupiter.vthread;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-09-09
 */
public class VThreadTest {
//    public static void main(String[] args) {
//        // 1. 创建一个虚拟线程池，若不以try-with方式执行，
//        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();) {
//            // 2. 提交任务
//            executorService.submit(() -> {
//                for(int i = 0; i < 10; i++)
//                    System.out.println(Thread.currentThread().getName() + " Hello world " + i);
//            });
//            System.out.println(Thread.currentThread().getName() + " completed.");
//
//        }
//    }
}

class VThreadTest2 {
//    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " hello world1");
//            System.out.println(Thread.currentThread().getName() + " hello world2");
//            System.out.println(Thread.currentThread().getName() + " hello world3");
//        },"new Thread.");
//        Thread.startVirtualThread(thread);
//        System.out.println(Thread.currentThread().getName() + " hello world");
//
//        thread.start();
//        // Wait for the virtual thread to complete
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
}