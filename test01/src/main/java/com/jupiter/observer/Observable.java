package com.jupiter.observer;

/**
 * @author Jupiter.Lin
 * @desc TODO 可被观察的对象
 * @date 2023-12-10 01:04
 */
public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
