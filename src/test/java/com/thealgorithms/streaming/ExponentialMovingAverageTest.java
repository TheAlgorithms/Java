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

class ExponentialMovingAverageTest {

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -0.5, 1.5, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidAlpha(double alpha) {
        assertThrows(IllegalArgumentException.class, () -> ExponentialMovingAverage.ofAlpha(alpha));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.5, -3.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidSpan(double span) {
        assertThrows(IllegalArgumentException.class, () -> ExponentialMovingAverage.ofSpan(span));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidHalfLife(double halfLife) {
        assertThrows(IllegalArgumentException.class, () -> ExponentialMovingAverage.ofHalfLife(halfLife));
    }

    @Test
    void rejectsNonFiniteSeed() {
        assertThrows(IllegalArgumentException.class, () -> ExponentialMovingAverage.ofAlpha(0.5, Double.NaN));
    }

    @Test
    void spanAndHalfLifeTranslateIntoAlpha() {
        assertEquals(0.2, ExponentialMovingAverage.ofSpan(9.0).alpha(), 1e-12);
        assertEquals(1.0, ExponentialMovingAverage.ofSpan(1.0).alpha(), 1e-12);
        assertEquals(0.5, ExponentialMovingAverage.ofHalfLife(1.0).alpha(), 1e-12);
        assertEquals(1.0 - Math.pow(0.5, 0.1), ExponentialMovingAverage.ofHalfLife(10.0).alpha(), 1e-12);
    }

    @Test
    void queriesBeforeTheFirstSampleFail() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.5);
        assertFalse(average.isInitialized());
        assertThrows(IllegalStateException.class, average::value);
        assertThrows(IllegalStateException.class, average::variance);
        assertThrows(IllegalStateException.class, average::standardDeviation);
    }

    @Test
    @DisplayName("the first sample seeds the average instead of being pulled towards zero")
    void seedsWithTheFirstSample() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.1);
        assertEquals(100.0, average.add(100.0));
        assertTrue(average.isInitialized());
        assertEquals(1L, average.count());
    }

    @Test
    void followsTheRecurrence() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.5);
        assertEquals(1.0, average.add(1.0), 1e-12);
        assertEquals(1.5, average.add(2.0), 1e-12);
        assertEquals(2.25, average.add(3.0), 1e-12);
        assertEquals(3L, average.count());
    }

    @Test
    void anExplicitSeedIsUsedRightAway() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.5, 0.0);
        assertTrue(average.isInitialized());
        assertEquals(0.0, average.value());
        assertEquals(0.5, average.add(1.0), 1e-12);
        assertEquals(1L, average.count(), "the seed is not counted as a sample");
    }

    @Test
    void alphaOfOneKeepsOnlyTheLatestSample() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(1.0);
        average.addAll(1.0, 2.0, 3.0);
        assertEquals(3.0, average.value(), 1e-12);
        assertEquals(0.0, average.variance(), 1e-12);
    }

    @Test
    void trackedVarianceFollowsTheRecurrence() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.5);
        average.add(0.0);
        assertEquals(0.0, average.variance(), 1e-12);
        average.add(2.0);
        assertEquals(1.0, average.variance(), 1e-12);
        average.add(0.0);
        assertEquals(0.75, average.variance(), 1e-12);
        assertEquals(Math.sqrt(0.75), average.standardDeviation(), 1e-12);
    }

    @Test
    void aConstantSignalHasNoSpread() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofSpan(10.0);
        for (int i = 0; i < 100; i++) {
            average.add(7.0);
        }
        assertEquals(7.0, average.value(), 1e-12);
        assertEquals(0.0, average.variance(), 1e-12);
    }

    @Test
    @DisplayName("a step in the signal is tracked, the faster the larger alpha is")
    void tracksAStep() {
        ExponentialMovingAverage fast = ExponentialMovingAverage.ofAlpha(0.5);
        ExponentialMovingAverage slow = ExponentialMovingAverage.ofAlpha(0.05);
        fast.add(0.0);
        slow.add(0.0);
        for (int i = 0; i < 10; i++) {
            fast.add(10.0);
            slow.add(10.0);
        }
        assertTrue(fast.value() > slow.value(), "fast=" + fast.value() + " slow=" + slow.value());
        assertEquals(10.0, fast.value(), 0.05);
        assertTrue(slow.value() < 5.0);

        for (int i = 0; i < 500; i++) {
            slow.add(10.0);
        }
        assertEquals(10.0, slow.value(), 1e-6);
    }

    @Test
    @DisplayName("on stationary noise the estimates sit close to the true mean and variance")
    void approximatesTheStationaryMoments() {
        Random random = new Random(987L);
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.01);
        for (int i = 0; i < 200_000; i++) {
            average.add(5.0 + 2.0 * random.nextGaussian());
        }
        assertEquals(5.0, average.value(), 0.5);
        assertEquals(4.0, average.variance(), 2.0);
    }

    @Test
    void resetReturnsToTheInitialState() {
        ExponentialMovingAverage plain = ExponentialMovingAverage.ofAlpha(0.5);
        plain.addAll(1.0, 2.0, 3.0);
        plain.reset();
        assertFalse(plain.isInitialized());
        assertEquals(0L, plain.count());

        ExponentialMovingAverage seeded = ExponentialMovingAverage.ofAlpha(0.5, 42.0);
        seeded.addAll(1.0, 2.0);
        seeded.reset();
        assertTrue(seeded.isInitialized());
        assertEquals(42.0, seeded.value());
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.3);
        assertThrows(IllegalArgumentException.class, () -> average.add(value));
    }

    @Test
    void toStringMentionsTheState() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.25);
        average.add(4.0);
        assertTrue(average.toString().contains("alpha=0.25"), average.toString());
    }

    @Test
    void toStringWorksBeforeTheFirstSample() {
        ExponentialMovingAverage average = ExponentialMovingAverage.ofAlpha(0.25);
        assertTrue(average.toString().contains("value=NaN"), average.toString());
    }
}
