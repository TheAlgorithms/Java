package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HammingDistanceTest {

    @Test
    public void testHammingDistance() {
        assertEquals(3, HammingDistance.hammingDistance(9, 14)); // 1001 vs 1110, Hamming distance is 3
        assertEquals(0, HammingDistance.hammingDistance(10, 10)); // Same number, Hamming distance is 0
        assertEquals(1, HammingDistance.hammingDistance(1, 0)); // 0001 vs 0000, Hamming distance is 1
        assertEquals(2, HammingDistance.hammingDistance(4, 1)); // 100 vs 001, Hamming distance is 2
        assertEquals(4, HammingDistance.hammingDistance(0, 15)); // 0000 vs 1111, Hamming distance is 4
    }
}
