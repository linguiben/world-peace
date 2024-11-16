package com.jupiter.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-11-09
 */
@RunWith(PowerMockRunner.class)
//@ExtendWith(PowerMockExtension.class)
@PrepareForTest(MyClass.class)
public class PowerMockTest {
    @Test
    public void testPrivateMethod() throws Exception {
        MyClass myClass = new MyClass();
        // 获取私有方法
        Method privateMethod = MyClass.class.getDeclaredMethod("privateMethod");
        // 设置可访问
        privateMethod.setAccessible(true);
        // 调用私有方法
        String result = (String) privateMethod.invoke(myClass);
        // 验证结果
        assertEquals("Private Method Result", result);
    }

    @Test
    public void testPrivateMethodUsingPoerMock() throws Exception {
        MyClass myClass = new MyClass();

        // 模拟私有方法
        String[] params = {"abc", "123"};
//        String result = Whitebox.invokeMethod(myClass, "privateMethod", new Class[]{String[].class},
//                new String[]{"abc", "123"});
        String result = (String) PowerMockito.method(MyClass.class, "privateMethod", String[].class)
                .invoke(myClass, (Object) new String[]{"abc", "123"});

        // 验证结果
        assertEquals("Private Method Result", result);
    }
}


/**
 * 私有方法
 */
class MyClass {
    private String privateMethod(String[] args) {
        System.out.println("This is a private method. " + args);
        return "Private Method Result";
    }
}