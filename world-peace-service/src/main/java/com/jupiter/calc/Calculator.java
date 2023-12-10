package com.jupiter.calc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
//@Scope("prototype")
//@ConfigurationProperties(prefix = "HSI00001")
@Data
public class Calculator {

    private String name;

    @Value("${HSI.lastTradeMv}")
    private int lastTradeMv;
    @Value("${HSI.lastTradIndexValue}")
    private BigDecimal lastTradIndexValue;
    private int mv;
    private BigDecimal indexValue ;

//    @Autowired
//    private Map<String,Integer> stockPriceMap;

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

    public int sum(String ... input){
        int mvSum = 0;
        for (int i = 0; i < input.length; i++) {
            mvSum += Integer.parseInt(input[i]);
        }
        this.mv = mvSum;
        return mvSum;
    }


    public Calculator(String name) {
        this.name = name;
    }

    public Calculator() {
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "name='" + name + '\'' +
                ", mv=" + mv +
                ", indexValue=" + indexValue +
                ", stockPriceMap=" +
                '}';
    }
}
