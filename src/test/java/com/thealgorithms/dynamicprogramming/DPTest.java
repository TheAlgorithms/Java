package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DPTest {

    @Test
    void testSumLessThanMinimumFaceValue() {
        // When the sum is less than the minimum possible face value
        // There are 0 ways to achieve the sum
        assertEquals(0, DP.findWays(4, 2, 1)); // 4 faces, 2 dice, sum = 1
    }

    @Test
    void testTwoDiceWithSumEqualToTwo() {
        // When there are 2 dice and the sum is equal to the number of dice
        // The only way is to have both dice showing 1
        assertEquals(1, DP.findWays(2, 2, 2)); // 2 faces, 2 dice, sum = 2
    }

    @Test
    void testTwoDiceWithSumThree() {
        // When there are 2 dice and the sum is equal to 3
        // Possible combinations are (1,2) and (2,1)
        assertEquals(2, DP.findWays(2, 2, 3)); // 2 faces, 2 dice, sum = 3
    }

    @Test
    void testThreeDiceWithSumEight() {
        // Test for 3 dice, each having 6 faces
        // Possible combinations to make sum of 8
        assertEquals(21, DP.findWays(6, 3, 8)); // 6 faces, 3 dice, sum = 8
    }

    @Test
    void testTwoDiceWithSumFive() {
        // Test for 2 dice, with 4 faces to make sum of 5
        // Possible combinations: (1,4), (2,3), (3,2), (4,1)
        assertEquals(4, DP.findWays(4, 2, 5)); // 4 faces, 2 dice, sum = 5
    }

    @Test
    void testThreeDiceWithSumFive() {
        // Test for 3 dice, with 4 faces to make sum of 5
        // Possible combinations: (1,1,3), (1,2,2), (1,3,1), (2,1,2), (2,2,1), (3,1,1)
        assertEquals(6, DP.findWays(4, 3, 5)); // 4 faces, 3 dice, sum = 5
    }

    @Test
    void testEdgeCaseZeroSum() {
        // Test for 0 sum with 0 dice
        assertEquals(0, DP.findWays(4, 0, 0)); // 4 faces, 0 dice, sum = 0
    }
}
