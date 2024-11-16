package com.jupiter.lombok;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @desc: 测试Lombok的骚操作
 * @author: Jupiter.Lin
 * @date: 2024-09-29
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 私有化构造函数
public class LombokTest {
    public static void main(String[] args) {
        User user = User.builder().name("jupiter").age(18).addHobby("football").addHobby("basketball").build();
        log.info(user.toString());
    }

    @Builder // 构建者模式，用于构建复杂的对象
    // @Data @Value 两者有点类似，又有些区别。@Value是final的，不能修改。
    @ToString(of = {"name", "age"}) // 指定ToString的字段
    @EqualsAndHashCode(of = {"name", "age"}) // 指定EqualsAndHashCode的字段
    @Accessors(chain = true) // 链式调用，setter方法返回this。目前属于实验性功能，可能会发生变化。不建议使用。
    public static class User {
        private String name;
        private Integer age;

        @Singular("addHobby") // 指定方法名称
        private List<String> hobbies;

        @Synchronized // 增加同步锁
        @SneakyThrows // 忽略异常
        public void doSomething() {
            Thread.sleep(1000);
        }
    }
}

