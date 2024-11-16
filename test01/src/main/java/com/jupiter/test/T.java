package com.jupiter.test;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-09-28
 */
public class T {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread thread = new Thread(processor, "process-thread");
        thread.start();
        // 当JVM退出时，通过添加关闭钩子来确保清理逻辑被执行
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            processor.runFlag = false;
            try {
                thread.join();
            } catch (InterruptedException e) {}
        }));
    }

    static class Processor implements Runnable {
        public volatile boolean runFlag = true;
        @Override
        public void run() {
            System.out.println("Processor started. thread name: " + Thread.currentThread().getName());
            while (runFlag) {
                System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Processor finished.");
        }
    }
}
