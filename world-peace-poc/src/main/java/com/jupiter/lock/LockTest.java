package com.jupiter.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc: Use String as lock
 * @author: Jupiter.Lin
 * @date: 2025/3/3
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();

//        // 线程安全
//        Thread thread1 = new Thread(() -> lockTest.saving("清华"));
//        Thread thread2 = new Thread(() -> lockTest.saving("北大"));
//        Thread thread3 = new Thread(() -> lockTest.saving("北大"));

        // 线程吧安全
        Thread thread1 = new Thread(() -> lockTest.saving(new String("清华")));
        Thread thread2 = new Thread(() -> lockTest.saving(new String("北大")));
        Thread thread3 = new Thread(() -> lockTest.saving(new String("北大")));

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(values);
//        values.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public void saving(String school) {
//        synchronized (inputString.intern()) {
        synchronized (school) {
            System.out.println(school + " 学生交卷");
            save(school);
            System.out.println(school + " 学生交卷成功");
        }
    }

    ConcurrentHashMap<String, Object> lockMap = new ConcurrentHashMap();
    public void saving2(String inputString) {
        Object lock = lockMap.computeIfAbsent(inputString, k -> new Object());
        synchronized (lock) {
            System.out.println(inputString + " success");
        }
    }

    // 模拟数据库保存
    static Map<String, Integer> values = new ConcurrentHashMap<>();
    private void save(String account) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 同一个学校，账户数加1
        if (values.containsKey(account)) {
            values.put(account, values.get(account) + 1);
        } else {
            values.put(account, 1);
        }

    }
}
