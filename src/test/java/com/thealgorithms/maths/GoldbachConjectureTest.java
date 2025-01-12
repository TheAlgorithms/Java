package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.thealgorithms.maths.GoldbachConjecture.getPrimeSum;

public class GoldbachConjectureTest {
    @Test
    void GoldbachTestWithZero() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(0));
    }
    @Test
    void GoldbachTestWithNegative() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(-50));
    }
    @Test
    void GoldbachTestWith2() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(2));
    }
    @Test
    void GoldbachTestWithOdd() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(25));
    }
    @Test
    void GoldbachTestEven1() {
        Assertions.assertEquals("3 + 5 = 8", getPrimeSum(8));
    }
    @Test
    void GoldbachTestEven2() {
        Assertions.assertEquals("3 + 19 = 22", getPrimeSum(22));
    }
    @Test
    void GoldbachTest1() {
        Assertions.assertEquals("3 + 7 = 10", getPrimeSum(10));
    }
}
