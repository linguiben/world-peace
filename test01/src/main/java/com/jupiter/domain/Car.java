package com.jupiter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jupiter.Lin
 * @desc Create table t_car(cno bigint, cname varchar(50), color varchar(50), price decimal);
 * @date 2023-06-18 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String cname;
    private String color;
    private double price;

}
