package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoinTowerTest {
    @Test
    void coinTowerTest1() {
        assertEquals("A", CoinTower.coinTower(4, 2, 3));
    }

    @Test
    void coinTowerTest2() {
        assertEquals("B", CoinTower.coinTower(10, 2, 4));
    }

    @Test
    void coinTowerTest3() {
        assertEquals("B", CoinTower.coinTower(5, 3, 4));
    }
}
