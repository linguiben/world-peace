package com.jupiter.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc: Test Stream API in Java 8+.
 * This class contains test methods to demonstrate the usage of Stream API in Java.
 * It includes examples of using map() and flatMap() methods.
 *
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */

public class TestStream {

    @Test
    // 1. Test Stream.map() 用于对流中的每个元素进行转换，返回一个新的流。
    public void testMap() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(String::length) // 将每个名字转换为其长度
                .collect(Collectors.toList());
        System.out.println(nameLengths); // 输出: [5, 3, 7]
    }

    @Test
    // 2. Test Stream.flagMap() 用于将流中的每个元素映射为一个流，并将所有这些流合并为一个流，通常用于处理嵌套结构。
    public void testFlagMap() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F")
        );

        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream) // 将每个子列表转换为流并扁平化
                .collect(Collectors.toList());
        System.out.println(flatList); // 输出: [A, B, C, D, E, F]
    }
}
