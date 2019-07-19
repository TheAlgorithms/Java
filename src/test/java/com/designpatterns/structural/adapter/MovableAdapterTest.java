package com.designpatterns.structural.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovableAdapterTest {
    @Test
    void testMovableAdapter() {
        Movable bugattiVeyron = new BugattiVeyron();
        MovableAdapter bugattiVeyronAdapter = new MovableAdapterImpl(bugattiVeyron);
        Assertions.assertEquals(bugattiVeyronAdapter.getSpeed(), 431.30312, 0.00001);
    }
}
