package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CombinationsTest {

    @Test
    void testCombination() {
        assertEquals(1, Combinations.combinations(1, 1));
        assertEquals(252, Combinations.combinations(10, 5));
        assertEquals(20, Combinations.combinations(6, 3));
        assertEquals(15504, Combinations.combinations(20, 5));
    }

    @Test
    void testCombinationOptimised() {
        assertEquals(100, Combinations.combinationsOptimized(100, 1));
        assertEquals(1, Combinations.combinationsOptimized(1, 1));
        assertEquals(252, Combinations.combinationsOptimized(10, 5));
        assertEquals(20, Combinations.combinationsOptimized(6, 3));
        assertEquals(15504, Combinations.combinationsOptimized(20, 5));
        assertEquals(2535650040L, Combinations.combinationsOptimized(200, 5));
    }
}
