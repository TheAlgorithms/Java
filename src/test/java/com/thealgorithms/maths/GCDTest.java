package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GCDTest {

    @Test
    void test1() {
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> GCD.gcd(-1, 0)
        );
    }

    @Test
    void test2() {
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> GCD.gcd(10, -2)
        );
    }

    @Test
    void test3() {
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> GCD.gcd(-5, -3)
        );
    }

    @Test
    void test4() {
        Assertions.assertEquals(GCD.gcd(0, 2), 2);
    }

    @Test
    void test5() {
        Assertions.assertEquals(GCD.gcd(10, 0), 10);
    }

    @Test
    void test6() {
        Assertions.assertEquals(GCD.gcd(1, 0), 1);
    }

    @Test
    void test7() {
        Assertions.assertEquals(GCD.gcd(9, 6), 3);
    }
}
