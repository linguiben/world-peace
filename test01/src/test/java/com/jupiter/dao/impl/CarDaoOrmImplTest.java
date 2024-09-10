package com.jupiter.dao.impl;

import com.jupiter.domain.Car;
import com.jupiter.spring.dao.impl.CarDaoOrmImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 13:29
 */
@ExtendWith(MockitoExtension.class)
class CarDaoOrmImplTest {

    @Mock
    CarDaoOrmImpl carDaoOrm;

    @Test
    void save() {
        // CarDaoOrmImpl orm = new CarDaoOrmImpl();
        when(carDaoOrm.save(any(Car.class))).thenReturn(1);
        int rows = carDaoOrm.save(new Car("路虎T1", "black", 99.99d));
        assertNotEquals(0,rows);
    }

    @Test
    void delete() {
        when(carDaoOrm.delete(any(Car.class))).thenReturn(1);
        save();
        int rows = carDaoOrm.delete(new Car("路虎T1", "black", 0));
        assertTrue( rows >= 1);
    }
}