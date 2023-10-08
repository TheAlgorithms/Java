package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeCheckTest {

    @Test
    void test1() {
        Assertions.assertTrue(PrimeCheck.isPrime(2));
    }

    @Test
    void test2() {
        Assertions.assertFalse(PrimeCheck.isPrime(-1));
    }

    @Test
    void test3() {
        Assertions.assertFalse(PrimeCheck.isPrime(4));
    }

    @Test
    void test4() {
        Assertions.assertTrue(PrimeCheck.isPrime(5));
    }

    @Test
    void test5() {
        Assertions.assertFalse(PrimeCheck.isPrime(15));
    }

    @Test
    void test6() {
        Assertions.assertTrue(PrimeCheck.isPrime(11));
    }

    @Test
    void test7() {
        Assertions.assertFalse(PrimeCheck.isPrime(49));
    }
}
