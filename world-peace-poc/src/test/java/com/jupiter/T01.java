/**
 * @className T01
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-22 23:02
 */
package com.jupiter;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-08-22 23:02
 */
public class T01 {
    // test Optional
    @Test
    public void testOptional() {
        String str = null;
        System.out.println(Optional.ofNullable(str).orElse("default"));
        str = "hello";
        System.out.println(Optional.ofNullable(str).orElseGet(() -> "default"));
        System.out.println(Optional.ofNullable(str).orElseThrow(() -> new RuntimeException("String is null")));

        //
        User user = new User();
        Optional.ofNullable(user).ifPresent(u -> user.setName("ZSan"));
        System.out.println(user);

        assertThrows(RuntimeException.class, () -> {
            Optional.ofNullable(user)
                    .map(User::getAddress)
                    .orElseThrow(() -> new RuntimeException("地址为空"));
        });
    }

    @Data
    static class User {

        private String name;
        private int age;
        private String address;
    }

}
