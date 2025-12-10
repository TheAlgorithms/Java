package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReservoirSamplingTest {

    @Test
    public void testSampleSizeEqualsStreamLength() {
        int[] stream = {1, 2, 3, 4, 5};
        int sampleSize = 5;

        List<Integer> result = ReservoirSampling.sample(stream, sampleSize);

        assertEquals(sampleSize, result.size());
        assertTrue(Arrays.stream(stream).allMatch(result::contains));
    }

    @Test
    public void testSampleSizeLessThanStreamLength() {
        int[] stream = {10, 20, 30, 40, 50, 60};
        int sampleSize = 3;

        List<Integer> result = ReservoirSampling.sample(stream, sampleSize);

        assertEquals(sampleSize, result.size());
        for (int value : result) {
            assertTrue(Arrays.stream(stream).anyMatch(x -> x == value));
        }
    }

    @Test
    public void testSampleSizeGreaterThanStreamLengthThrowsException() {
        int[] stream = {1, 2, 3};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ReservoirSampling.sample(stream, 5));

        assertEquals("Sample size cannot exceed stream size.", exception.getMessage());
    }
}
