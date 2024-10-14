package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KCentersTest {

    @Test
    public void testFindKCenters() {
        int[][] distances = {{0, 2, 3, 4}, {2, 0, 5, 1}, {3, 5, 0, 7}, {4, 1, 7, 0}};
        assertEquals(4, KCenters.findKCenters(distances, 2));
        assertEquals(2, KCenters.findKCenters(distances, 4));
    }
}
