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
     * 1. 用CopyOnWriteArraySet解决增强for循环里删除元素出现fail-fast的问题
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
     */
    @Test
    public void test_remove2() {
         Map<String, String> map = this.initMap;
        map = new ConcurrentHashMap<>(this.initMap); // 解决并发修改异常
        // 此处编译错误 local variables referenced from a lambda expression must be final or effectively final
        // https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.12.4
        map.forEach((k, v) -> {
            if (k.startsWith("J")) {
                map.remove(k);
            }
        });
        this.initMap = map;
    }


    /**
     * 3.0 迭代器 能正常删除map里的元素，但多线程情况下会出现并发异常。
     */
    @Test
    public void test_remove3() {
//         Iterator<Map.Entry<String, String>> it = initMap.entrySet().iterator(); // single thread

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

    /**
     * 5. steam().filter().collect();
     */

    @AfterEach
    public void printInitMap(){
        log.info("initMap:{}",initMap);
    }
}
