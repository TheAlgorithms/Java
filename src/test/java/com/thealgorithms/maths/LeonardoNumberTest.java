package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link LeonardoNumber} class.
 * <p>
 * Tests both recursive and iterative implementations with various input values
 * including edge cases and boundary conditions.
 */
class LeonardoNumberTest {

    // Tests for recursive implementation

    @Test
    void testLeonardoNumberNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LeonardoNumber.leonardoNumber(-1));
    }

    @Test
    void testLeonardoNumberNegativeLarge() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LeonardoNumber.leonardoNumber(-100));
    }

    @Test
    void testLeonardoNumberZero() {
        Assertions.assertEquals(1, LeonardoNumber.leonardoNumber(0));
    }

    @Test
    void testLeonardoNumberOne() {
        Assertions.assertEquals(1, LeonardoNumber.leonardoNumber(1));
    }

    @Test
    void testLeonardoNumberTwo() {
        Assertions.assertEquals(3, LeonardoNumber.leonardoNumber(2));
    }

    @Test
    void testLeonardoNumberThree() {
        Assertions.assertEquals(5, LeonardoNumber.leonardoNumber(3));
    }

    @Test
    void testLeonardoNumberFour() {
        Assertions.assertEquals(9, LeonardoNumber.leonardoNumber(4));
    }

    @Test
    void testLeonardoNumberFive() {
        Assertions.assertEquals(15, LeonardoNumber.leonardoNumber(5));
    }

    @Test
    void testLeonardoNumberSix() {
        Assertions.assertEquals(25, LeonardoNumber.leonardoNumber(6));
    }

    @Test
    void testLeonardoNumberSeven() {
        Assertions.assertEquals(41, LeonardoNumber.leonardoNumber(7));
    }

    @Test
    void testLeonardoNumberEight() {
        Assertions.assertEquals(67, LeonardoNumber.leonardoNumber(8));
    }

    @Test
    void testLeonardoNumberTen() {
        Assertions.assertEquals(177, LeonardoNumber.leonardoNumber(10));
    }

    @Test
    void testLeonardoNumberFifteen() {
        Assertions.assertEquals(1973, LeonardoNumber.leonardoNumber(15));
    }

    @Test
    void testLeonardoNumberTwenty() {
        Assertions.assertEquals(21891, LeonardoNumber.leonardoNumber(20));
    }

    // Tests for iterative implementation

    @Test
    void testLeonardoNumberIterativeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LeonardoNumber.leonardoNumberIterative(-1));
    }

    @Test
    void testLeonardoNumberIterativeNegativeLarge() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LeonardoNumber.leonardoNumberIterative(-50));
    }

    @Test
    void testLeonardoNumberIterativeZero() {
        Assertions.assertEquals(1, LeonardoNumber.leonardoNumberIterative(0));
    }

    @Test
    void testLeonardoNumberIterativeOne() {
        Assertions.assertEquals(1, LeonardoNumber.leonardoNumberIterative(1));
    }

    @Test
    void testLeonardoNumberIterativeTwo() {
        Assertions.assertEquals(3, LeonardoNumber.leonardoNumberIterative(2));
    }

    @Test
    void testLeonardoNumberIterativeThree() {
        Assertions.assertEquals(5, LeonardoNumber.leonardoNumberIterative(3));
    }

    @Test
    void testLeonardoNumberIterativeFour() {
        Assertions.assertEquals(9, LeonardoNumber.leonardoNumberIterative(4));
    }

    @Test
    void testLeonardoNumberIterativeFive() {
        Assertions.assertEquals(15, LeonardoNumber.leonardoNumberIterative(5));
    }

    @Test
    void testLeonardoNumberIterativeSix() {
        Assertions.assertEquals(25, LeonardoNumber.leonardoNumberIterative(6));
    }

    @Test
    void testLeonardoNumberIterativeSeven() {
        Assertions.assertEquals(41, LeonardoNumber.leonardoNumberIterative(7));
    }

    @Test
    void testLeonardoNumberIterativeEight() {
        Assertions.assertEquals(67, LeonardoNumber.leonardoNumberIterative(8));
    }

    @Test
    void testLeonardoNumberIterativeTen() {
        Assertions.assertEquals(177, LeonardoNumber.leonardoNumberIterative(10));
    }

    @Test
    void testLeonardoNumberIterativeFifteen() {
        Assertions.assertEquals(1973, LeonardoNumber.leonardoNumberIterative(15));
    }

    @Test
    void testLeonardoNumberIterativeTwenty() {
        Assertions.assertEquals(21891, LeonardoNumber.leonardoNumberIterative(20));
    }

    @Test
    void testLeonardoNumberIterativeTwentyFive() {
        Assertions.assertEquals(242785, LeonardoNumber.leonardoNumberIterative(25));
    }

    // Consistency tests between recursive and iterative implementations

    @Test
    void testConsistencyBetweenImplementations() {
        for (int i = 0; i <= 15; i++) {
            Assertions.assertEquals(LeonardoNumber.leonardoNumber(i), LeonardoNumber.leonardoNumberIterative(i), "Mismatch at index " + i);
        }
    }
}
