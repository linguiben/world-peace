package com.jupiter.dao.impl;

import com.jupiter.domain.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 13:29
 */
class CarDaoOrmImplTest {

    @Test
    void save() {
        CarDaoOrmImpl orm = new CarDaoOrmImpl();
        int rows = orm.save(new Car("路虎T1", "black", 99.99d));
        assertNotEquals(0,rows);
    }

    @Test
    void delete() {
        save();
        CarDaoOrmImpl orm = new CarDaoOrmImpl();
        int rows = orm.delete(new Car("路虎T1", "black", 0));
        assertTrue( rows >= 1);
    }
}