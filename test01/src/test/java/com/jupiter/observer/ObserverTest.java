package com.jupiter.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-12-10 01:12
 */
class ObserverTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void notifyObserver() {
    }


    @Test
    void testObserver(){
        BadMan badMan = new BadMan();
        Policeman policeman = new Policeman();
        badMan.addObserver(policeman);
        badMan.escape();
    }
}