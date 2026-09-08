package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HampelFilterTest {

    private static double median(double[] values) {
        double[] sorted = values.clone();
        Arrays.sort(sorted);
        int middle = sorted.length / 2;
        return sorted.length % 2 != 0 ? sorted[middle] : 0.5 * (sorted[middle - 1] + sorted[middle]);
    }

    /**
     * Median absolute deviation of the window ending at {@code index}, computed the obvious way.
     */
    private static double bruteForceMad(double[] signal, int index, int windowSize) {
        int from = Math.max(0, index - windowSize + 1);
        double[] window = Arrays.copyOfRange(signal, from, index + 1);
        double windowMedian = median(window);
        double[] deviations = new double[window.length];
        for (int i = 0; i < window.length; i++) {
            deviations[i] = Math.abs(window[i] - windowMedian);
        }
        return median(deviations);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void rejectsNonPositiveWindowSizes(int windowSize) {
        assertThrows(IllegalArgumentException.class, () -> new HampelFilter(windowSize));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidThresholds(double threshold) {
        assertThrows(IllegalArgumentException.class, () -> new HampelFilter(5, threshold));
    }

    @Test
    void queriesBeforeTheFirstSampleFail() {
        HampelFilter filter = new HampelFilter(5);
        assertEquals(5, filter.windowSize());
        assertEquals(HampelFilter.DEFAULT_THRESHOLD, filter.threshold());
        assertEquals(0, filter.size());
        assertEquals(0L, filter.outlierCount());
        assertFalse(filter.lastWasOutlier());
        assertThrows(IllegalStateException.class, filter::median);
        assertThrows(IllegalStateException.class, filter::medianAbsoluteDeviation);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        HampelFilter filter = new HampelFilter(5);
        assertThrows(IllegalArgumentException.class, () -> filter.accept(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 8, 15})
    @DisplayName("the median absolute deviation agrees with a brute force computation")
    void madMatchesBruteForce(int windowSize) {
        Random random = new Random(20240517L + windowSize);
        double[] signal = new double[2_000];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = random.nextInt(100) - 50;
        }

        HampelFilter filter = new HampelFilter(windowSize, 3.0);
        for (int i = 0; i < signal.length; i++) {
            filter.accept(signal[i]);
            assertEquals(bruteForceMad(signal, i, windowSize), filter.medianAbsoluteDeviation(), 1e-12, "at index " + i);
        }
    }

    @Test
    @DisplayName("a single spike is flagged and replaced by the local median")
    void replacesASpike() {
        HampelFilter filter = new HampelFilter(5, 3.0);
        double[] signal = {5.0, 5.1, 4.9, 5.0, 5.05, 50.0, 5.0, 5.1};
        double[] filtered = filter.filter(signal);

        assertEquals(1L, filter.outlierCount());
        assertTrue(filtered[5] < 6.0, "the spike should have been replaced, but stayed at " + filtered[5]);
        for (int i = 0; i < signal.length; i++) {
            if (i != 5) {
                assertEquals(signal[i], filtered[i], 0.0, "clean sample at index " + i + " must pass through");
            }
        }
    }

    @Test
    void detectOutliersReportsWithoutChangingTheSignal() {
        HampelFilter filter = new HampelFilter(5, 3.0);
        double[] signal = {5.0, 5.1, 4.9, 5.0, 5.05, 50.0, 5.0, 5.1};
        boolean[] flags = filter.detectOutliers(signal);

        boolean[] expected = new boolean[signal.length];
        expected[5] = true;
        Assertions.assertArrayEquals(expected, flags);
        assertEquals(1L, filter.outlierCount());
    }

    @Test
    @DisplayName("a clean ramp contains no outliers, since the MAD grows with the slope")
    void leavesASmoothRampAlone() {
        HampelFilter filter = new HampelFilter(5, 3.0);
        double[] ramp = new double[200];
        for (int i = 0; i < ramp.length; i++) {
            ramp[i] = i;
        }
        Assertions.assertArrayEquals(ramp, filter.filter(ramp), 0.0);
        assertEquals(0L, filter.outlierCount());
    }

    @Test
    void leavesAConstantSignalAlone() {
        HampelFilter filter = new HampelFilter(7);
        for (int i = 0; i < 100; i++) {
            assertEquals(3.0, filter.accept(3.0));
        }
        assertEquals(0L, filter.outlierCount());
        assertEquals(0.0, filter.medianAbsoluteDeviation());
    }

    @Test
    @DisplayName("only a few samples of clean Gaussian noise are flagged")
    void staysQuietOnCleanNoise() {
        Random random = new Random(4242L);
        HampelFilter filter = new HampelFilter(11, 3.0);
        for (int i = 0; i < 5_000; i++) {
            filter.accept(random.nextGaussian());
        }
        assertTrue(filter.outlierCount() < 500, "flagged " + filter.outlierCount() + " of 5000 clean samples");
    }

    @Test
    @DisplayName("spikes buried in noise are caught")
    void catchesSpikesInNoisyData() {
        Random random = new Random(7L);
        double[] signal = new double[1_000];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = 20.0 + random.nextGaussian();
        }
        int[] spikes = {100, 300, 700};
        for (int spike : spikes) {
            signal[spike] = 200.0;
        }

        HampelFilter filter = new HampelFilter(9, 3.0);
        double[] filtered = filter.filter(signal);
        for (int spike : spikes) {
            assertTrue(filtered[spike] < 30.0, "spike at " + spike + " survived as " + filtered[spike]);
        }
    }

    @Test
    @DisplayName("a zero MAD makes the identifier maximally strict")
    void flagsAnyDeviationWhenTheMadIsZero() {
        HampelFilter filter = new HampelFilter(5, 3.0);
        for (int i = 0; i < 5; i++) {
            filter.accept(5.0);
        }
        assertEquals(0.0, filter.medianAbsoluteDeviation());
        assertEquals(5.0, filter.accept(5.5));
        assertTrue(filter.lastWasOutlier());
    }

    @Test
    void aThresholdOfZeroFlagsEverythingOffTheMedian() {
        HampelFilter filter = new HampelFilter(3, 0.0);
        filter.accept(1.0);
        filter.accept(2.0);
        assertTrue(filter.lastWasOutlier());
        assertEquals(1.5, filter.median(), 1e-12);
    }

    @Test
    void resetForgetsTheWindowAndTheCounters() {
        HampelFilter filter = new HampelFilter(5, 3.0);
        filter.filter(new double[] {5.0, 5.1, 4.9, 5.0, 5.05, 50.0});
        assertEquals(1L, filter.outlierCount());

        filter.reset();
        assertEquals(0, filter.size());
        assertEquals(0L, filter.outlierCount());
        assertFalse(filter.lastWasOutlier());
        assertThrows(IllegalStateException.class, filter::median);
    }

    @Test
    void toStringMentionsTheState() {
        HampelFilter filter = new HampelFilter(5, 2.5);
        assertTrue(filter.toString().contains("threshold=2.5"), filter.toString());
    }
}
