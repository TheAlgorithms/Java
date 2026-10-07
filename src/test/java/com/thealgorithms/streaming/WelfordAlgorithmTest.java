package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WelfordAlgorithmTest {

    private static final double[] TEXTBOOK_SAMPLE = {2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0};

    private static double twoPassPopulationVariance(double... values) {
        double mean = 0.0;
        for (double value : values) {
            mean += value;
        }
        mean /= values.length;

        double sumOfSquares = 0.0;
        for (double value : values) {
            sumOfSquares += (value - mean) * (value - mean);
        }
        return sumOfSquares / values.length;
    }

    @Test
    void emptyAccumulatorReportsNothing() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        assertTrue(stats.isEmpty());
        assertEquals(0L, stats.count());
        assertEquals(0.0, stats.sum());
        assertEquals(0.0, stats.sumOfSquaredDeviations());
        assertTrue(Double.isNaN(stats.mean()));
        assertTrue(Double.isNaN(stats.populationVariance()));
        assertTrue(Double.isNaN(stats.sampleVariance()));
        assertTrue(Double.isNaN(stats.sampleStandardDeviation()));
        assertTrue(Double.isNaN(stats.standardError()));
    }

    @Test
    void singleSampleHasNoSpread() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.add(42.0);
        assertFalse(stats.isEmpty());
        assertEquals(1L, stats.count());
        assertEquals(42.0, stats.mean());
        assertEquals(42.0, stats.sum());
        assertEquals(0.0, stats.populationVariance());
        assertTrue(Double.isNaN(stats.sampleVariance()));
    }

    @Test
    @DisplayName("reproduces the textbook values of a known sample")
    void matchesKnownValues() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(TEXTBOOK_SAMPLE);

        assertEquals(8L, stats.count());
        assertEquals(5.0, stats.mean(), 1e-12);
        assertEquals(40.0, stats.sum(), 1e-12);
        assertEquals(32.0, stats.sumOfSquaredDeviations(), 1e-12);
        assertEquals(4.0, stats.populationVariance(), 1e-12);
        assertEquals(2.0, stats.populationStandardDeviation(), 1e-12);
        assertEquals(32.0 / 7.0, stats.sampleVariance(), 1e-12);
        assertEquals(Math.sqrt(32.0 / 7.0), stats.sampleStandardDeviation(), 1e-12);
        assertEquals(Math.sqrt(32.0 / 7.0 / 8.0), stats.standardError(), 1e-12);
    }

    @Test
    @DisplayName("stays accurate where the naive sum of squares collapses")
    void survivesLargeOffsets() {
        double offset = 1e9;
        double[] values = {offset + 4.0, offset + 7.0, offset + 13.0, offset + 16.0};

        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(values);

        // The naive formula computes sum(x^2) - n * mean^2 on numbers of order 1e18 and loses every
        // significant digit of the answer; Welford's recurrence never forms them.
        double naive = 0.0;
        for (double value : values) {
            naive += value * value;
        }
        naive = (naive - values.length * stats.mean() * stats.mean()) / (values.length - 1);

        assertEquals(30.0, stats.sampleVariance(), 1e-6);
        assertTrue(Math.abs(naive - 30.0) > 1.0, "the naive formula is expected to be far off, but returned " + naive);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 10, 1_000})
    void agreesWithATwoPassComputation(int sampleCount) {
        Random random = new Random(4242L + sampleCount);
        double[] values = new double[sampleCount];
        for (int i = 0; i < sampleCount; i++) {
            values[i] = random.nextGaussian() * 17.0 + 3.0;
        }

        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(values);

        double expectedVariance = twoPassPopulationVariance(values);
        assertEquals(expectedVariance, stats.populationVariance(), 1e-9 * Math.max(1.0, expectedVariance));
    }

    @Test
    void removeUndoesAdd() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(TEXTBOOK_SAMPLE);
        stats.add(1000.0);
        stats.remove(1000.0);

        assertEquals(8L, stats.count());
        assertEquals(5.0, stats.mean(), 1e-9);
        assertEquals(4.0, stats.populationVariance(), 1e-9);
    }

    @Test
    void removingTheLastSampleEmptiesTheAccumulator() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.add(3.0);
        stats.remove(3.0);
        assertTrue(stats.isEmpty());
        assertEquals(0.0, stats.sumOfSquaredDeviations());
    }

    @Test
    void removeOnAnEmptyAccumulatorFails() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        assertThrows(IllegalStateException.class, () -> stats.remove(1.0));
    }

    @Test
    @DisplayName("add plus remove turns the accumulator into a sliding window")
    void supportsSlidingWindows() {
        int windowSize = 20;
        Random random = new Random(1234L);
        double[] signal = new double[500];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = random.nextGaussian() * 5.0 + 100.0;
        }

        WelfordAlgorithm window = new WelfordAlgorithm();
        for (int i = 0; i < signal.length; i++) {
            window.add(signal[i]);
            if (window.count() > windowSize) {
                window.remove(signal[i - windowSize]);
            }
            if (i >= windowSize) {
                double[] expectedWindow = new double[windowSize];
                System.arraycopy(signal, i - windowSize + 1, expectedWindow, 0, windowSize);

                WelfordAlgorithm reference = new WelfordAlgorithm();
                reference.addAll(expectedWindow);
                assertEquals(windowSize, window.count());
                assertEquals(reference.mean(), window.mean(), 1e-9);
                assertEquals(reference.populationVariance(), window.populationVariance(), 1e-8);
            }
        }
    }

    @Test
    void mergeCombinesPartialSummaries() {
        Random random = new Random(20240517L);
        double[] values = new double[1_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextGaussian();
        }

        WelfordAlgorithm whole = new WelfordAlgorithm();
        WelfordAlgorithm left = new WelfordAlgorithm();
        WelfordAlgorithm right = new WelfordAlgorithm();
        for (int i = 0; i < values.length; i++) {
            whole.add(values[i]);
            if (i < 337) {
                left.add(values[i]);
            } else {
                right.add(values[i]);
            }
        }

        WelfordAlgorithm merged = WelfordAlgorithm.merge(left, right);
        assertEquals(whole.count(), merged.count());
        assertEquals(whole.mean(), merged.mean(), 1e-12);
        assertEquals(whole.sampleVariance(), merged.sampleVariance(), 1e-12);
    }

    @Test
    void mergeHandlesEmptyOperands() {
        WelfordAlgorithm empty = new WelfordAlgorithm();
        WelfordAlgorithm filled = new WelfordAlgorithm();
        filled.addAll(TEXTBOOK_SAMPLE);

        assertTrue(WelfordAlgorithm.merge(empty, empty).isEmpty());
        assertEquals(filled.mean(), WelfordAlgorithm.merge(empty, filled).mean(), 1e-12);
        assertEquals(filled.mean(), WelfordAlgorithm.merge(filled, empty).mean(), 1e-12);
        assertEquals(filled.sampleVariance(), WelfordAlgorithm.merge(filled, empty).sampleVariance(), 1e-12);
    }

    @Test
    void clearForgetsEverything() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(TEXTBOOK_SAMPLE);
        stats.clear();
        assertTrue(stats.isEmpty());
        assertTrue(Double.isNaN(stats.mean()));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        assertThrows(IllegalArgumentException.class, () -> stats.add(value));
        stats.add(1.0);
        assertThrows(IllegalArgumentException.class, () -> stats.remove(value));
    }

    @Test
    void toStringMentionsTheSummary() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.addAll(TEXTBOOK_SAMPLE);
        assertTrue(stats.toString().contains("count=8"), stats.toString());
    }

    @Test
    @DisplayName("removing a sample far from the mean cannot push the variance below zero")
    void removalNeverYieldsANegativeVariance() {
        WelfordAlgorithm stats = new WelfordAlgorithm();
        stats.add(1e16);
        stats.add(1.0);
        stats.remove(1e16);

        assertEquals(1L, stats.count());
        assertTrue(stats.sumOfSquaredDeviations() >= 0.0, "got " + stats.sumOfSquaredDeviations());
        assertTrue(stats.populationVariance() >= 0.0);
    }
}
