package com.thealgorithms.audiofilters;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class EMAFilterTest {

    @Test
    public void testApplyBasicSignal() {
        EMAFilter emaFilter = new EMAFilter(0.2);
        double[] audioSignal = {0.1, 0.5, 0.8, 0.6, 0.3, 0.9, 0.4};
        double[] expectedOutput = {0.1, 0.18, 0.304, 0.3632, 0.35056, 0.460448, 0.4483584};
        double[] result = emaFilter.apply(audioSignal);
        assertArrayEquals(expectedOutput, result, 1e-5);
    }

    @Test
    public void testApplyEmptySignal() {
        EMAFilter emaFilter = new EMAFilter(0.2);
        double[] audioSignal = {};
        double[] expectedOutput = {};
        double[] result = emaFilter.apply(audioSignal);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testAlphaBounds() {
        EMAFilter emaFilterMin = new EMAFilter(0.01);
        EMAFilter emaFilterMax = new EMAFilter(1.0);
        double[] audioSignal = {1.0, 1.0, 1.0, 1.0};

        // Minimal smoothing (alpha close to 0)
        double[] resultMin = emaFilterMin.apply(audioSignal);
        assertArrayEquals(audioSignal, resultMin, 1e-5);

        // Maximum smoothing (alpha = 1, output should match input)
        double[] resultMax = emaFilterMax.apply(audioSignal);
        assertArrayEquals(audioSignal, resultMax, 1e-5);
    }
}
