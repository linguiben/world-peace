/**
 * Create table t_car(cno bigint, cname varchar(50), color varchar(50), price double);
 */

package com.jupiter.domain;

import java.io.Serializable;

public class Car implements Serializable {
    private Long cno ;
    private String cname ;
    private String color ;
    private double price  ;

    public long getCno() {
        return cno;
    }

    public void setCno(long cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car(Long cno, String cname, String color, double price) {
        this.cno = cno;
        this.cname = cname;
        this.color = color;
        this.price = price;
    }

    public Car() {
    }
}
