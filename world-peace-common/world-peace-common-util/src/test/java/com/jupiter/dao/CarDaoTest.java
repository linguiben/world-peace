package com.jupiter.dao;

import com.jupiter.domain.Car;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CarDaoTest {

    @Test
    void save() throws IOException {
        Car car =new Car(null,"benz","red",400000.0);
        CarDao dao =new CarDao();

        dao.save(car);
    }
}