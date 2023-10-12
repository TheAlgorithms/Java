package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

public class MaximumSumWithoutAdjacentTest {
    @Test
    public void testfindMaxSumWithoutAdjacent() {
        int[][] sampleInput = new int[][]{{1, 2, 4, 3, 2}, {4, 2, 1, 3, 2}};
        assert MaximumSumWithoutAdjacent.findMaxSumWithoutAdjacent(sampleInput) == 10;
    }
}
