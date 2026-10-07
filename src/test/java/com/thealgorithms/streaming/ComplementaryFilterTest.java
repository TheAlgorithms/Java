package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ComplementaryFilterTest {

    private static final double COEFFICIENT = 0.98;
    private static final double INTERVAL = 0.01;

    private static double standardDeviation(double[] values, double around) {
        double sum = 0.0;
        for (double value : values) {
            sum += (value - around) * (value - around);
        }
        return Math.sqrt(sum / values.length);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, -0.5, 1.5, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsACoefficientOutsideTheUnitInterval(double coefficient) {
        assertThrows(IllegalArgumentException.class, () -> new ComplementaryFilter(coefficient));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidTimeConstants(double value) {
        assertThrows(IllegalArgumentException.class, () -> ComplementaryFilter.ofTimeConstant(value, INTERVAL));
        assertThrows(IllegalArgumentException.class, () -> ComplementaryFilter.ofTimeConstant(0.5, value));
        assertThrows(IllegalArgumentException.class, () -> new ComplementaryFilter().timeConstant(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteReadings(double value) {
        ComplementaryFilter filter = new ComplementaryFilter();

        assertThrows(IllegalArgumentException.class, () -> filter.accept(value, 0.0));
        assertThrows(IllegalArgumentException.class, () -> filter.accept(0.0, value));
        assertThrows(IllegalArgumentException.class, () -> filter.reset(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsAnInvalidElapsedTime(double elapsed) {
        ComplementaryFilter filter = new ComplementaryFilter();

        assertThrows(IllegalArgumentException.class, () -> filter.accept(0.0, 0.0, elapsed));
    }

    @Test
    void rejectsMismatchedRecordings() {
        ComplementaryFilter filter = new ComplementaryFilter();

        assertThrows(IllegalArgumentException.class, () -> filter.scan(new double[] {1.0, 2.0}, new double[] {1.0}));
    }

    @Test
    void exposesItsConfiguration() {
        ComplementaryFilter filter = new ComplementaryFilter(0.9);

        assertEquals(0.9, filter.coefficient());
        assertEquals(0.98, new ComplementaryFilter().coefficient());
        assertEquals(0, filter.count());
        assertEquals(0.0, filter.value());
    }

    @Test
    @DisplayName("the first pair is answered with the reference, because there is nothing to integrate yet")
    void seedsWithTheFirstReference() {
        ComplementaryFilter filter = new ComplementaryFilter();

        assertEquals(7.5, filter.accept(100.0, 7.5), 1e-12);
        assertEquals(1, filter.count());
    }

    @Test
    @DisplayName("the coefficient and the time constant are two ways of saying the same thing")
    void theTimeConstantRoundTrips() {
        ComplementaryFilter filter = ComplementaryFilter.ofTimeConstant(0.5, INTERVAL);

        assertEquals(0.5, filter.timeConstant(INTERVAL), 1e-12);
        assertEquals(0.5 / (0.5 + INTERVAL), filter.coefficient(), 1e-12);
        assertEquals(COEFFICIENT * INTERVAL / (1 - COEFFICIENT), new ComplementaryFilter(COEFFICIENT).timeConstant(INTERVAL), 1e-12);
    }

    @Test
    @DisplayName("with nothing to integrate the estimate decays onto the reference geometrically")
    void convergesOnTheReference() {
        ComplementaryFilter filter = new ComplementaryFilter(COEFFICIENT);
        filter.reset(10.0);

        for (int step = 1; step <= 200; step++) {
            double value = filter.accept(0.0, 0.0);
            assertEquals(10.0 * Math.pow(COEFFICIENT, step), value, 1e-9, "step " + step);
        }
    }

    @Test
    @DisplayName("a biased rate leaves a bounded error of tau times the bias, where integration alone would run away")
    void boundsTheDriftOfTheRate() {
        double bias = 0.1;
        ComplementaryFilter filter = new ComplementaryFilter(COEFFICIENT);
        filter.reset(0.0);

        double integrated = 0.0;
        for (int step = 0; step < 5000; step++) {
            filter.accept(bias, 0.0, INTERVAL);
            integrated += bias * INTERVAL;
        }

        double expected = filter.timeConstant(INTERVAL) * bias;
        assertEquals(expected, filter.value(), 1e-9, "the steady state error must be exactly tau * bias");
        assertEquals(0.049, filter.value(), 1e-6);
        assertEquals(5.0, integrated, 1e-9, "plain integration of the same bias walks away");
    }

    @Test
    @DisplayName("noise on the reference is cut down, roughly by the factor the theory promises")
    void rejectsNoiseOnTheReference() {
        Random random = new Random(7L);
        ComplementaryFilter filter = new ComplementaryFilter(COEFFICIENT);
        double[] references = new double[20000];
        double[] estimates = new double[references.length];

        for (int i = 0; i < references.length; i++) {
            references[i] = random.nextGaussian();
            estimates[i] = filter.accept(0.0, references[i], INTERVAL);
        }

        double referenceSpread = standardDeviation(references, 0.0);
        double estimateSpread = standardDeviation(estimates, 0.0);
        double promised = Math.sqrt((1 - COEFFICIENT) / (1 + COEFFICIENT));

        assertEquals(promised, estimateSpread / referenceSpread, 0.02, "the spread should shrink by sqrt((1-a)/(1+a))");
    }

    @Test
    @DisplayName("a moving signal is followed without lag, which a low pass on the reference alone cannot do")
    void followsARampWithoutLag() {
        Random random = new Random(11L);
        double slope = 1.0;
        ComplementaryFilter filter = new ComplementaryFilter(COEFFICIENT);

        double lowPass = 0.0;
        double filterError = 0.0;
        double lowPassError = 0.0;
        int steps = 4000;

        for (int i = 0; i < steps; i++) {
            double truth = slope * i * INTERVAL;
            double reference = truth + 0.05 * random.nextGaussian();
            double estimate = filter.accept(slope, reference, INTERVAL);
            lowPass = i == 0 ? reference : COEFFICIENT * lowPass + (1 - COEFFICIENT) * reference;

            if (i > steps / 2) {
                filterError += Math.abs(estimate - truth);
                lowPassError += Math.abs(lowPass - truth);
            }
        }

        int counted = steps - steps / 2 - 1;
        double filterMean = filterError / counted;
        double lowPassMean = lowPassError / counted;

        assertTrue(filterMean < 0.02, "the complementary filter should sit on the ramp, it was off by " + filterMean);
        assertEquals(filter.timeConstant(INTERVAL) * slope, lowPassMean, 0.02, "the low pass should lag by tau * slope");
        assertTrue(lowPassMean > 10 * filterMean, "the lag should dwarf the error of the complementary filter");
    }

    @Test
    @DisplayName("the coefficient decides how quickly the reference takes over")
    void theCoefficientDecidesWhichSensorWins() {
        ComplementaryFilter trustsTheRate = new ComplementaryFilter(0.999);
        ComplementaryFilter trustsTheReference = new ComplementaryFilter(0.001);
        trustsTheRate.reset(0.0);
        trustsTheReference.reset(0.0);

        for (int i = 0; i < 100; i++) {
            trustsTheRate.accept(0.0, 100.0, INTERVAL);
            trustsTheReference.accept(0.0, 100.0, INTERVAL);
        }

        assertEquals(100.0 * (1 - Math.pow(0.999, 100)), trustsTheRate.value(), 1e-9);
        assertTrue(trustsTheRate.value() < 10.0, "a second is a tenth of its time constant, so it has barely moved");
        assertEquals(100.0, trustsTheReference.value(), 1e-9, "the other one is on the reference from the first sample");
        assertTrue(trustsTheRate.timeConstant(INTERVAL) > 1000 * trustsTheReference.timeConstant(INTERVAL));
    }

    @Test
    @DisplayName("the elapsed time scales how much of the rate is taken in")
    void theElapsedTimeScalesTheIntegration() {
        ComplementaryFilter fast = new ComplementaryFilter(COEFFICIENT);
        ComplementaryFilter slow = new ComplementaryFilter(COEFFICIENT);
        fast.reset(0.0);
        slow.reset(0.0);

        fast.accept(2.0, 0.0, 0.5);
        slow.accept(2.0, 0.0, 1.0);

        assertEquals(COEFFICIENT * 1.0, fast.value(), 1e-12);
        assertEquals(COEFFICIENT * 2.0, slow.value(), 1e-12);
    }

    @Test
    void scanReportsOneEstimatePerSample() {
        ComplementaryFilter filter = new ComplementaryFilter();

        double[] estimates = filter.scan(new double[] {0.0, 0.0, 0.0}, new double[] {1.0, 1.0, 1.0});

        assertEquals(3, estimates.length);
        assertEquals(3, filter.count());
        assertEquals(1.0, estimates[2], 1e-12);
    }

    @Test
    void resetForgetsEverything() {
        ComplementaryFilter filter = new ComplementaryFilter();
        filter.scan(new double[] {1.0, 1.0}, new double[] {5.0, 5.0});

        filter.reset();

        assertEquals(0, filter.count());
        assertEquals(0.0, filter.value());
        assertEquals(9.0, filter.accept(100.0, 9.0), 1e-12, "the next reference seeds the estimate again");
    }

    @Test
    @DisplayName("restarting from a known estimate keeps it instead of waiting for the next reference")
    void resetCanCarryOnFromAValue() {
        ComplementaryFilter filter = new ComplementaryFilter(COEFFICIENT);

        filter.reset(4.0);

        assertEquals(4.0, filter.value(), 1e-12);
        assertEquals(1, filter.count());
        assertEquals(COEFFICIENT * 4.0, filter.accept(0.0, 0.0), 1e-12);
    }

    @Test
    void toStringMentionsTheState() {
        ComplementaryFilter filter = new ComplementaryFilter(0.9);
        filter.accept(0.0, 2.0);

        String text = filter.toString();

        assertTrue(text.contains("ComplementaryFilter"));
        assertTrue(text.contains("coefficient=0.9"));
        assertTrue(text.contains("samples=1"));
    }
}
