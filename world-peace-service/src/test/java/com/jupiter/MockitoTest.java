package com.jupiter;

import com.jupiter.service.MapTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 使用 @ExtendWith 或者 MockitoAnnotations.openMocks(this)，二选一
 */
@ExtendWith(MockitoExtension.class)
class MockitoTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private MapTest mapTest;

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
        // MockitoAnnotations.openMocks(this);
        this.list = mock(ArrayList.class);
    }

    @Test
    void test01() {
        when(mapTest.getMaps()).thenReturn(hashMap);
        // System.out.println(mapTest.getMaps());
        assertEquals(2, mapTest.getMaps().size());
    }

    @Test
    void test02() {
        when(list.get(anyInt())).thenThrow(new RuntimeException("not support"));
        // in fact, we will not invoke get(0) directly, generally will invoke as a property in a service or controller
        assertThrows(RuntimeException.class, () -> list.get(0));
    }

}
