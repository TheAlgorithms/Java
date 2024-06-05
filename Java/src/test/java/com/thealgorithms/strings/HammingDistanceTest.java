package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class HammingDistanceTest {

    @Test
    void testHammingDistance() throws Exception {
        assertEquals(HammingDistance.calculateHammingDistance("", ""), 0);
        assertEquals(HammingDistance.calculateHammingDistance("java", "java"), 0);
        assertEquals(HammingDistance.calculateHammingDistance("karolin", "kathrin"), 3);
        assertEquals(HammingDistance.calculateHammingDistance("kathrin", "kerstin"), 4);
        assertEquals(HammingDistance.calculateHammingDistance("00000", "11111"), 5);
    }

    @Test
    void testNotEqualStringLengths() {
        Exception exception = assertThrows(Exception.class, () -> HammingDistance.calculateHammingDistance("ab", "abc"));
        assertEquals("String lengths must be equal", exception.getMessage());
    }
}
