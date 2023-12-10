package com.jupiter.observer;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-12-10 01:10
 */
public class Policeman implements Observer{

    private final String name = "Policeman AAA";
    @Override
    public void action() {
        System.out.println(this.name+": Catch you! Bad man!");
    }
}
