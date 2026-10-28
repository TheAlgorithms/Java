package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class P2QuantileEstimatorTest {

    /**
     * Exact quantile of a sample, by linear interpolation between the surrounding order statistics.
     */
    private static double exactQuantile(double[] sortedValues, double probability) {
        double rank = probability * (sortedValues.length - 1);
        int lower = (int) Math.floor(rank);
        int upper = Math.min(lower + 1, sortedValues.length - 1);
        return sortedValues[lower] + (rank - lower) * (sortedValues[upper] - sortedValues[lower]);
    }

    private static double[] shuffledUniformSample(int count, long seed) {
        Random random = new Random(seed);
        double[] values = new double[count];
        for (int i = 0; i < count; i++) {
            values[i] = random.nextDouble();
        }
        return values;
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, -0.1, 1.5, Double.NaN})
    void rejectsInvalidProbabilities(double probability) {
        assertThrows(IllegalArgumentException.class, () -> new P2QuantileEstimator(probability));
    }

    @Test
    void queriesBeforeTheFirstSampleFail() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        assertTrue(estimator.isEmpty());
        assertEquals(0L, estimator.count());
        assertEquals(0.5, estimator.probability());
        assertThrows(IllegalStateException.class, estimator::quantile);
        assertThrows(IllegalStateException.class, estimator::min);
        assertThrows(IllegalStateException.class, estimator::max);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        assertThrows(IllegalArgumentException.class, () -> estimator.add(value));
    }

    @Test
    @DisplayName("the first five samples are answered exactly")
    void isExactDuringWarmUp() {
        P2QuantileEstimator median = new P2QuantileEstimator(0.5);
        median.add(3.0);
        assertEquals(3.0, median.quantile());
        median.add(1.0);
        assertEquals(2.0, median.quantile(), 1e-12);
        median.add(2.0);
        assertEquals(2.0, median.quantile(), 1e-12);
        median.add(10.0);
        assertEquals(2.5, median.quantile(), 1e-12);
        assertEquals(1.0, median.min());
        assertEquals(10.0, median.max());
        assertEquals(4L, median.count());
    }

    @Test
    void tracksTheExtremesExactly() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        double[] values = shuffledUniformSample(5_000, 7L);
        estimator.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[0], estimator.min(), 0.0);
        assertEquals(sorted[sorted.length - 1], estimator.max(), 0.0);
        assertEquals(5_000L, estimator.count());
    }

    @Test
    void aConstantStreamHasAConstantQuantile() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.9);
        for (int i = 0; i < 1_000; i++) {
            estimator.add(7.0);
        }
        assertEquals(7.0, estimator.quantile(), 1e-12);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.05, 0.25, 0.5, 0.75, 0.9, 0.99})
    @DisplayName("estimates a uniform stream within a percent of the exact quantile")
    void approximatesUniformQuantiles(double probability) {
        double[] values = shuffledUniformSample(100_000, 20240517L);
        P2QuantileEstimator estimator = new P2QuantileEstimator(probability);
        estimator.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        assertEquals(exactQuantile(sorted, probability), estimator.quantile(), 0.01);
    }

    @Test
    @DisplayName("estimates the tail of a skewed stream")
    void approximatesTheTailOfAnExponentialStream() {
        Random random = new Random(31337L);
        double[] values = new double[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = -Math.log(1.0 - random.nextDouble()) * 10.0;
        }

        P2QuantileEstimator estimator = new P2QuantileEstimator(0.95);
        estimator.addAll(values);

        double[] sorted = values.clone();
        Arrays.sort(sorted);
        double expected = exactQuantile(sorted, 0.95);
        assertEquals(expected, estimator.quantile(), 0.05 * expected);
    }

    @Test
    @DisplayName("stays sane even when the stream arrives already sorted, the worst case for P-square")
    void handlesMonotonicInput() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        for (int i = 1; i <= 10_000; i++) {
            estimator.add(i);
        }
        assertEquals(5_000.0, estimator.quantile(), 500.0);
        assertEquals(1.0, estimator.min());
        assertEquals(10_000.0, estimator.max());
    }

    @Test
    void estimatesOfDifferentQuantilesStayOrdered() {
        double[] values = shuffledUniformSample(50_000, 99L);
        P2QuantileEstimator low = new P2QuantileEstimator(0.25);
        P2QuantileEstimator middle = new P2QuantileEstimator(0.5);
        P2QuantileEstimator high = new P2QuantileEstimator(0.75);
        low.addAll(values);
        middle.addAll(values);
        high.addAll(values);

        assertTrue(low.quantile() < middle.quantile());
        assertTrue(middle.quantile() < high.quantile());
    }

    @Test
    void resetForgetsEverything() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        estimator.addAll(shuffledUniformSample(1_000, 5L));
        estimator.reset();

        assertTrue(estimator.isEmpty());
        assertThrows(IllegalStateException.class, estimator::quantile);

        estimator.addAll(1.0, 2.0, 3.0);
        assertEquals(2.0, estimator.quantile(), 1e-12);
    }

    @Test
    void toStringMentionsTheEstimate() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        assertTrue(estimator.toString().contains("count=0"), estimator.toString());
        estimator.addAll(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        assertFalse(estimator.isEmpty());
        assertTrue(estimator.toString().contains("quantile="), estimator.toString());
    }

    @Test
    @DisplayName("violent jumps force the parabolic prediction to fall back to a linear one")
    void survivesWildlyJumpingInput() {
        P2QuantileEstimator estimator = new P2QuantileEstimator(0.5);
        double value = 1.0;
        for (int i = 0; i < 5_000; i++) {
            value = i % 2 == 0 ? value * 3.0 + 1.0 : 1.0 / (i + 1);
            estimator.add(value);
            assertTrue(estimator.quantile() >= estimator.min(), "the estimate fell below the minimum");
            assertTrue(estimator.quantile() <= estimator.max(), "the estimate rose above the maximum");
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.5, 0.99})
    @DisplayName("lands exactly on the textbook quantile of a ramp, which pins the marker convention")
    void isExactOnARamp(double probability) {
        // The markers are numbered from one, as in the paper. That origin is arithmetically irrelevant
        // - only differences of positions are ever used - but the desired positions are grown by
        // repeated addition, so a different origin rounds differently and flips the strict comparisons
        // that pick between the parabolic and the linear prediction. On a ramp, whose quantiles are
        // whole numbers, that shows up as an answer off by one.
        P2QuantileEstimator estimator = new P2QuantileEstimator(probability);
        for (int i = 0; i <= 1_700; i++) {
            estimator.add(i);
        }
        assertEquals(probability * 1_700, estimator.quantile(), 1e-9);
    }
}
