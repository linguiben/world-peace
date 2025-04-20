package com.jupiter;

import com.jupiter.mvc.service.MapTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * 1. 在测试类上标记@ExtendWith 或者 方法内使用MockitoAnnotations.openMocks(this)，（二选一）
 * 2. 模拟对象，使用mock(Class)或@Mock,对象的方法返回默认值; 或spy(Class), 对象方法返回真实值。
 * 3. 模拟方法返回值，使用when(mock.method()).thenReturn(value)
 *
 * mock和spy区别：mock对象不会走真实的方法，除非调用thenCallRealMethod()，
 * spy对象会走真实的方法，但也可以进行打桩调用thenReturn()。
 */
@ExtendWith(MockitoExtension.class) // 1
class MockitoTest {

    // @InjectMocks // 注入所有mock对象
    @Mock(answer = Answers.RETURNS_SMART_NULLS) // mock对象不会走真实的方法
    private MapTest mapMock;

    @Spy // spy对象会走真实的方法
    private MapTest mapSpy;

    private List<String> list;

    static HashMap<String, String> hashMap;

    @BeforeAll
    static void init() {
        hashMap = new HashMap();
        hashMap.put("key1", "abc");
        hashMap.put("key2", "def");
    }

    @BeforeEach
    public void setup() {
        // MockitoAnnotations.openMocks(this); // 等效于@ExtendWith(MockitoExtension.class)
        this.list = mock(ArrayList.class);
    }

    @AfterEach
    void tearDown() {
        reset(mapMock);
    }

    @Test
    void test01() {
        when(mapMock.getMaps()).thenReturn(hashMap);
        // System.out.println(mapTest.getMaps());
        assertEquals(2, mapMock.getMaps().size());
    }

    @Test
    void test02() {
        when(list.get(anyInt())).thenThrow(new RuntimeException("not support"));
        // in fact, we will not invoke get(0) directly, generally will invoke as a property in a service or controller
        assertThrows(RuntimeException.class, () -> list.get(0));
    }

    @Test
    void test03() {
        when(list.get(anyInt())).thenReturn("abc");
        assertEquals("abc", list.get(0));
    }

    @Test
    void testStatic() {
        // mock静态方法，需使用mockStatic，此mock对象会放到ThreadLocal中，所以每次使用完需要reset，否则影响其他测试用例
        // 这里使用 try-with-resource 来确保在测试用例执行完毕后，mock对象被释放
        try (MockedStatic<MapTest> mockedStatic = mockStatic(MapTest.class)) {
            OngoingStubbing<Object> stubbing = mockedStatic.when(MapTest::getInstance).thenReturn(mapMock);
            Assertions.assertSame(mapMock, MapTest.getInstance());
        }
    }
}
