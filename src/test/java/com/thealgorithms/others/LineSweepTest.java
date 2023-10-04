package com.thealgorithms.others;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class LineSweepTest {

    @Test
    void testForOverlap() {
        int[][] arr = {{0, 10}, {7, 20}, {15, 24}};
        assertTrue(LineSweep.isOverlap(arr));
    }

    @Test
    void testForNoOverlap() {
        int[][] arr = {{0, 10}, {11, 20}, {21, 24}};
        assertFalse(LineSweep.isOverlap(arr));
    }
    @Test
    void testForOverlapWhenEndAEqualsStartBAndViceVersa() {
        int[][] arr = {{0, 10}, {10, 20}, {21, 24}};
        assertTrue(LineSweep.isOverlap(arr));
    }
    @Test
    void testForMaximumEndPoint() {
        int[][] arr = {{10, 20}, {1, 100}, {14, 16}, {1, 8}};
        assertEquals(100, LineSweep.FindMaximumEndPoint(arr));
    }
}
