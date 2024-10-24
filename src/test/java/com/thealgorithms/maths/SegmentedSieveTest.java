package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SegmentedSieveTest {

    @Test
    public void testPrimesInRange() {
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7);
        assertEquals(expectedPrimes, SegmentedSieve.segmentedSieve(2, 10));

        List<Integer> expectedPrimes2 = List.of(11, 13, 17, 19);
        assertEquals(expectedPrimes2, SegmentedSieve.segmentedSieve(10, 20));
    }

    @Test
    public void testLargeRange() {
        List<Integer> expectedPrimes = List.of(99991, 99997);
        assertEquals(expectedPrimes, SegmentedSieve.segmentedSieve(99990, 100000));
    }

    @Test
    public void testSinglePrime() {
        List<Integer> expectedPrime = List.of(7);
        assertEquals(expectedPrime, SegmentedSieve.segmentedSieve(7, 7));

        List<Integer> expectedEmpty = new ArrayList<>();
        assertEquals(expectedEmpty, SegmentedSieve.segmentedSieve(8, 8));
    }

    @Test
    public void testInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> SegmentedSieve.segmentedSieve(-10, 10));
        assertThrows(IllegalArgumentException.class, () -> SegmentedSieve.segmentedSieve(20, 10)); // Invalid range
    }
}
