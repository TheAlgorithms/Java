package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EggDroppingTest {

    @Test
    void hasMultipleEggSingleFloor() {
        assertEquals(1, EggDropping.minTrials(3, 1));
    }

    @Test
    void hasSingleEggSingleFloor() {
        assertEquals(1, EggDropping.minTrials(1, 1));
    }

    @Test
    void hasSingleEggMultipleFloor() {
        assertEquals(3, EggDropping.minTrials(1, 3));
    }

    @Test
    void hasMultipleEggMultipleFloor() {
        assertEquals(7, EggDropping.minTrials(100, 101));
    }
}
