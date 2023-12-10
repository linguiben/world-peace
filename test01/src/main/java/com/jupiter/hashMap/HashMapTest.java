package com.jupiter.hashMap;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-11 22:46
 */
//@Data
@Slf4j
public class HashMapTest {

        Map<String, String> initMap = new HashMap<>();
    {
        initMap.put("Diana","England");
        initMap.put("Jack","HongKong");
        initMap.put("Jupiter","China");
        initMap.put("Jay","TaiWan");
        initMap.put("Lisa","Fujian");
    }

    @Test
    public void testDel0(){
        initMap.remove("abcd");

    }

    /**
     * 0. 测试快速失败(并发修改异常) fail-fast，增强for循环的底层原理是迭代器
     */
    // java.util.ConcurrentModificationException
    @Test
    public void test_fast_fail() {
        for (Map.Entry<String, String> entry : initMap.entrySet()) {
            if (entry.getKey().startsWith("J")) {
                initMap.remove(entry.getKey());
            }
        }
    }

    /**
     * 1. 使用增强for循环
     *    用CopyOnWriteArraySet解决增强for循环里删除元素出现fail-fast的问题
     */
    @Test
    public void test_remove1() {
        // ok by using CopyOnWriteArraySet
        Set<Map.Entry<String, String>> entries = new CopyOnWriteArraySet<>(initMap.entrySet());
        for (Map.Entry<String, String> entry : entries) {
            if (entry.getKey().startsWith("J")) {
                initMap.remove(entry.getKey());
            }
        }
    }

    /**
     * 2. forEach 也会出现并发修改异常，需要用ConcurrentHashMap
     * lambda表达式和局部内部类使用 局部变量时 ，要求标注为final或有效final
     */
    @Test
    public void test_remove2() {
        // Map<String, String> map = this.initMap;
        Map<String, String> map = new ConcurrentHashMap<>(this.initMap); // 解决并发修改异常
        // 此处编译错误 local variables referenced from a lambda expression must be final or effectively final
        // https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.12.4
        // 局部内部类 和 lambda表达式中使用的局部变量，必须为final或有效final，
        // 这是为了防止不同线程执行时，局部内部类、lambda表达式未执行完时，局部变量已被回收(若此变量所在的方法已执行完)
        map.forEach((k, v) -> {
            if (k.startsWith("J")) {
                map.remove(k);
            }
        });
        this.initMap = map;
    }

    /**
     * delete from haspMap
     */

    /**
     * 3.0 迭代器 能正常删除map里的元素，但多线程情况下会出现并发异常。
     */
    @Test
    public void test_remove3() {
        //Iterator<Map.Entry<String, String>> it = initMap.entrySet().iterator(); // single thread

        // multi thread
        Map<String, String> map = new ConcurrentHashMap<>(initMap);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            if (next.getKey().startsWith("J"))
                it.remove();
        }
        initMap = map;
    }

    /**
     * 4. removeIf
     */
    @Test
    public void test_remove4(){
        initMap.entrySet().removeIf(entry -> entry.getKey().startsWith("J"));
    }

    /**
     * 5. steam().filter().collect();
     */
    @Test
    public void test_remove5() {
        initMap = initMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("J"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @AfterEach
    public void printInitMap(){
        log.info("initMap:{}",initMap);
    }
}
