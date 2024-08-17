/**
 * @className MokitoTest
 * @Description: Mokito demo
 */
package com.jupiter.mokito;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *@desc Mokito 常用方法有:
 * 1. @InjectMocks @Mock, @Stub, @Spy, when() thenReturn
 * 2. @Captor 用户捕获方法的参数，后续用于verify()
 */
public class MokitoDemo {
    static{
        System.setProperty("user.timezone", "Asia/Shanghai");
        System.out.println("这是一个Mockito的Demo");
    }
}

@Slf4j
@RequiredArgsConstructor // 此注解会自动生成构造函数, 参数为final的属性
class MyService {
    private final MyRepository myRepository;
    private final MyMapper myMapper;
    public void save(String name) {
        myRepository.save(name);
        String nameFromMapper = myMapper.getName();
        log.info("nameFromMapper: {}", nameFromMapper);
    }
    public String getName() {
        return myMapper.getName();
    }
}

@Slf4j
class MyRepository {
    public void save(String name) {
        log.info("save name: {}", name);
    }
}

class MyMapper {
    public String getName() {
        return "Jupiter";
    }
}

// 测试类
@Slf4j
@ExtendWith(MockitoExtension.class) // JUnit5提供的注解，使用MockitoExtension扩展类
class MyServiceTest {
    @Test
    public void testSave() {
        MyService myService = new MyService(new MyRepository(), new MyMapper());
        myService.save("Jupiter");
        assertEquals("Jupiter", myService.getName());
    }

    // 只在测试类中执行一次
    @BeforeAll
    public static void init() {
        // 初始化Mock对象，与@ExtendWith(MockitoExtension.class)效果相同
         // MockitoAnnotations.openMocks(this);
    }

    // 每个@Test方法执行前执行
    @BeforeEach
    public void setUp() {}

    @InjectMocks // 创建一个待测试的实例，并自动注入Mock对象
    private MyService myService;
    @Mock // 与Spy不同的是，此注解mock出来的对象不会调用真实的方法
    private MyRepository myRepository;
    @Spy // 创建一个真实对象的包装，与Mock不同的是，此注解mock出来的对象会调用真实的方法，除非调用when()方法
    private MyMapper myMapper;
    @Test
    public void testCase() {
        myService.save("Jupiter");
        assertEquals("Jupiter", myService.getName());

        // 模拟myMapper的getName方法返回值
        when(myMapper.getName()).thenReturn("张柏芝");
        assertEquals("张柏芝", myService.getName());

        // 验证myRepository的save方法是否被调用
        verify(myRepository).save("Jupiter");

    }
}