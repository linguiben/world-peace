package com.jupiter.calc;

import java.math.BigDecimal;

public class Calculator {

    private String name;
    private int mv;
    private BigDecimal indexValue ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMv() {
        return mv;
    }

    public void setMv(int mv) {
        this.mv = mv;
    }

    public BigDecimal getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(BigDecimal indexValue) {
        this.indexValue = indexValue;
    }

    public int sum(String ... mv){
        int mvSum = 0;
        for (int i = 0; i < mv.length; i++) {
            mvSum += Integer.parseInt(mv[i]);
        }
        return mvSum;
    }

    private int avg(int a, int b){
        return (a+b)/2;
    }

    public Calculator(String name) {
        this.name = name;
    }

    public Calculator() {
    }


}
