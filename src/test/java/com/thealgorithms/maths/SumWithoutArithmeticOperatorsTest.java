package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SumWithoutArithmeticOperatorsTest {
    SumWithoutArithmeticOperators obj = new SumWithoutArithmeticOperators();

    @Test
    void addZerotoZero() {
        assertEquals(0, obj.getSum(0, 0));
    }

    @Test
    void addZerotoNumber() {
        assertEquals(5, obj.getSum(0, 5));
        assertEquals(28, obj.getSum(28, 0));
    }

    @Test
    void addOddtoEven() {
        assertEquals(13, obj.getSum(3, 10));
        assertEquals(55, obj.getSum(49, 6));
    }

    @Test
    void addEventoOdd() {
        assertEquals(13, obj.getSum(10, 3));
        assertEquals(41, obj.getSum(40, 1));
    }

    @Test
    void addRandoms() {
        assertEquals(88, obj.getSum(44, 44));
        assertEquals(370, obj.getSum(100, 270));
        assertEquals(3, obj.getSum(1, 2));
        assertEquals(5, obj.getSum(2, 3));
    }
}
