package com.jupiter.calc;

import com.jupiter.basic.branch.SwitchAndIf;
import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor()
public class Calculator {

    private String name;
    private int mv;
    private BigDecimal indexValue ;
    private SwitchAndIf largeOrSmall;

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

    public String dice(int input){
        return largeOrSmall.dice(input);
    }

    public Calculator(String name) {
        this.name = name;
    }

}
