package com.jupiter.dao.impl;

import com.jupiter.domain.Car;
import com.jupiter.spring.dao.CarDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 12:26
 */
@ExtendWith(MockitoExtension.class)
class CarDaoImplTest {

    static Car car = null;
    @Mock
    CarDao carDao;

    @BeforeAll
    static void createCar(){
        car = new Car("奔驰X7","pink",88.88d);
    }

    @Test
    void save() {
        // CarDao carDao = new CarDaoImpl();
        when(carDao.save(any(Car.class))).thenReturn(1);
        int save = carDao.save(this.car);
        assertEquals(1,save,"save error");
    }

    @Test
    void delete() {
        when(carDao.delete(any(Car.class))).thenReturn(1);
        int save = carDao.delete(this.car);
    }
}