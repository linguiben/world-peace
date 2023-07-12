package com.jupiter.dao;

import com.jupiter.domain.Car;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 12:03
 */
public interface CarDao {
    /**
     * @desc insert into db using a Car
     * @param car
     * @return
     */
    public int save(Car car);

    /**
     * @desc delete a Car from db
     * @param car
     * @return
     */
    public int delete(Car car);
}
