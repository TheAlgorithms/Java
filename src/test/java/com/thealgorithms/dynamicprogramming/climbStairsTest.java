package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class climbStairsTest {

    @Test
    void climbStairsTestForTwo() {
        assertEquals(2, ClimbingStairs.numberOfWays(2));
    }

    @Test
    void climbStairsTestForZero() {
        assertEquals(0, ClimbingStairs.numberOfWays(0));
    }

    @Test
    void climbStairsTestForOne() {
        assertEquals(1, ClimbingStairs.numberOfWays(1));
    }

    @Test
    void climbStairsTestForFive() {
        assertEquals(8, ClimbingStairs.numberOfWays(5));
    }

    @Test
    void climbStairsTestForThree() {
        assertEquals(3, ClimbingStairs.numberOfWays(3));
    }
}
