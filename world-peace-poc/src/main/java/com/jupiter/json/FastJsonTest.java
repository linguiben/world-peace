/**
 * @className FastJsonTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-04 10:22
 */
package com.jupiter.json;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-04 10:22
 */
@Slf4j
public class FastJsonTest {

    @Test
    public void testOOMBug1(){
        String json = "[2022,2023"; // missing ], hit OOM, FastJson version: 2.0.32
        String json1 = "[2023,2024,abcd]"; // OOM
        String json2 = "[2023,2023,2024]abcd"; // Ignore the string after ']'
        List<Integer> list = new ArrayList();
        list = JSON.parseArray(json, Integer.class);

        log.info("list:{}",list);
    }
}
