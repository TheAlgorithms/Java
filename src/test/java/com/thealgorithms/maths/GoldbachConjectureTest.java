package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.thealgorithms.maths.GoldbachConjecture.getPrimeSum;

public class GoldbachConjectureTest {
    @Test
    void goldbachTestWithZero() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(0));
    }
    @Test
    void goldbachTestWithNegative() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(-50));
    }
    @Test
    void goldbachTestWith2() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(2));
    }
    @Test
    void goldbachTestWithOdd() {
        Assertions.assertEquals("Wrong Input", getPrimeSum(25));
    }
    @Test
    void goldbachTestEven1() {
        Assertions.assertEquals("3 + 5 = 8", getPrimeSum(8));
    }
    @Test
    void goldbachTestEven2() {
        Assertions.assertEquals("3 + 19 = 22", getPrimeSum(22));
    }
    @Test
    void goldbachTest1() {
        Assertions.assertEquals("3 + 7 = 10", getPrimeSum(10));
    }
}
