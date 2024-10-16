package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedianFinderTest {
    @Test
    public void testMedianMaintenance() {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        assertEquals(1.5, mf.findMedian());
        mf.addNum(3);
        assertEquals(2.0, mf.findMedian());
    }
}
