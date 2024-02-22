/**
 * @className AdminAppTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-21 15:55
 */
package com.jupiter.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-21 15:55
 */

@SpringBootApplication
@RestController
public class AdminAppTest {

    @GetMapping("/helloworld")
    public String hello() {
        return "Hello World!";
    }
}