package com.jupiter.dao.impl;

import com.jupiter.spring.dao.CarDao;
import com.jupiter.domain.Car;
import com.jupiter.spring.dao.impl.CarDaoImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 12:26
 */
class CarDaoImplTest {

    static Car car = null;

    @BeforeAll
    static void createCar(){
        car = new Car("奔驰X7","pink",88.88d);
    }

    @Test
    void save() {
        CarDao carDao = new CarDaoImpl();
        int save = carDao.save(this.car);
        assertEquals(1,save,"save error");
    }

    @Test
    void delete() {
        CarDao carDao = new CarDaoImpl();
        int save = carDao.delete(this.car);
    }
}