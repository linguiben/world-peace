package com.jupiter.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-12-10 01:07
 */
public class BadMan implements Observable{

    private final List<Observer> observerList = new ArrayList<>();

    public void escape(){
        System.out.println("Bad man: I'm going to escape!");
        notifyObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.action();
        }
    }
}
