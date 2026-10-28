package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KalmanFilterTest {

    private static double rootMeanSquareError(double[] values, double truth) {
        double sum = 0.0;
        for (double value : values) {
            sum += (value - truth) * (value - truth);
        }
        return Math.sqrt(sum / values.length);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsNonFiniteInitialEstimate(double initialEstimate) {
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(initialEstimate, 1.0, 0.1, 1.0));
    }

    @Test
    void rejectsInvalidVariances() {
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, -1.0, 0.1, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, 1.0, -0.1, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, 1.0, 0.1, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, 1.0, 0.1, Double.NaN));
    }

    @Test
    void rejectsNonFiniteInputs() {
        KalmanFilter filter = new KalmanFilter(0.0, 1.0, 0.1, 1.0);
        assertThrows(IllegalArgumentException.class, () -> filter.predict(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> filter.update(Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> filter.update(1.0, 0.0));
    }

    @Test
    @DisplayName("prediction adds uncertainty, correction removes it")
    void covarianceGrowsOnPredictAndShrinksOnUpdate() {
        KalmanFilter filter = new KalmanFilter(0.0, 1.0, 0.25, 1.0);
        assertEquals(0.0, filter.lastGain());

        filter.predict();
        assertEquals(1.25, filter.errorCovariance(), 1e-12);

        filter.update(2.0);
        assertTrue(filter.errorCovariance() < 1.25);
        assertTrue(filter.lastGain() > 0.0 && filter.lastGain() < 1.0);
    }

    @Test
    @DisplayName("the gain is the share of the measurement that gets believed")
    void gainFollowsTheClosedForm() {
        KalmanFilter filter = new KalmanFilter(0.0, 1.0, 0.0, 1.0);
        filter.update(10.0);
        assertEquals(0.5, filter.lastGain(), 1e-12);
        assertEquals(5.0, filter.estimate(), 1e-12);
        assertEquals(0.5, filter.errorCovariance(), 1e-12);
    }

    @Test
    void aControlInputShiftsTheEstimate() {
        KalmanFilter filter = new KalmanFilter(100.0, 1.0, 0.1, 1.0);
        assertEquals(103.0, filter.predict(3.0), 1e-12);
        assertEquals(103.0, filter.estimate(), 1e-12);
    }

    @Test
    @DisplayName("with no process noise and no prior information the filter is the running mean")
    void degeneratesIntoTheRunningMean() {
        KalmanFilter filter = new KalmanFilter(0.0, 1e12, 0.0, 1.0);
        Random random = new Random(2024L);

        double sum = 0.0;
        for (int i = 1; i <= 200; i++) {
            double measurement = random.nextGaussian();
            sum += measurement;
            filter.update(measurement);
            assertEquals(sum / i, filter.estimate(), 1e-6, "after " + i + " measurements");
        }
    }

    @Test
    @DisplayName("filtering a noisy constant beats using the raw measurements")
    void reducesNoiseOnAConstantSignal() {
        double truth = 12.5;
        double noise = 2.0;
        Random random = new Random(555L);

        double[] measurements = new double[400];
        for (int i = 0; i < measurements.length; i++) {
            measurements[i] = truth + noise * random.nextGaussian();
        }

        KalmanFilter filter = new KalmanFilter(0.0, 100.0, 1e-4, noise * noise);
        double[] filtered = filter.filter(measurements);

        assertEquals(measurements.length, filtered.length);
        assertEquals(truth, filter.estimate(), 0.3);
        assertTrue(rootMeanSquareError(filtered, truth) < rootMeanSquareError(measurements, truth) / 2.0);
    }

    @Test
    @DisplayName("a moving quantity is tracked, and more process noise means faster tracking")
    void tracksAMovingSignal() {
        Random random = new Random(31L);
        double[] measurements = new double[200];
        for (int i = 0; i < measurements.length; i++) {
            measurements[i] = i * 0.5 + random.nextGaussian();
        }

        KalmanFilter agile = new KalmanFilter(0.0, 1.0, 1.0, 1.0);
        KalmanFilter sluggish = new KalmanFilter(0.0, 1.0, 1e-6, 1.0);
        agile.filter(measurements);
        sluggish.filter(measurements);

        double truth = 199 * 0.5;
        assertTrue(Math.abs(agile.estimate() - truth) < Math.abs(sluggish.estimate() - truth), "agile=" + agile.estimate() + " sluggish=" + sluggish.estimate());
        assertEquals(truth, agile.estimate(), 3.0);
    }

    @Test
    @DisplayName("updating once per sensor reproduces the closed form inverse-variance fusion")
    void sequentialUpdatesFuseSensors() {
        double preciseReading = 10.0;
        double preciseVariance = 0.25;
        double coarseReading = 14.0;
        double coarseVariance = 4.0;

        KalmanFilter filter = new KalmanFilter(0.0, 1e15, 0.0, 1.0);
        filter.update(preciseReading, preciseVariance);
        filter.update(coarseReading, coarseVariance);

        KalmanFilter.Estimate fused = KalmanFilter.fuse(new double[] {preciseReading, coarseReading}, new double[] {preciseVariance, coarseVariance});
        assertEquals(fused.value(), filter.estimate(), 1e-6);
        assertEquals(fused.variance(), filter.errorCovariance(), 1e-6);
    }

    @Test
    void fusionWeightsSensorsByTheirPrecision() {
        KalmanFilter.Estimate equal = KalmanFilter.fuse(new double[] {1.0, 3.0}, new double[] {1.0, 1.0});
        assertEquals(2.0, equal.value(), 1e-12);
        assertEquals(0.5, equal.variance(), 1e-12);

        KalmanFilter.Estimate skewed = KalmanFilter.fuse(new double[] {1.0, 3.0}, new double[] {0.01, 1.0});
        assertTrue(skewed.value() < 1.1, "the precise sensor should dominate, but got " + skewed.value());
        assertTrue(skewed.variance() < 0.01, "fusing can only reduce the variance, but got " + skewed.variance());
    }

    @Test
    void fusionOfASingleSensorReturnsIt() {
        KalmanFilter.Estimate single = KalmanFilter.fuse(new double[] {7.0}, new double[] {2.0});
        assertEquals(7.0, single.value(), 1e-12);
        assertEquals(2.0, single.variance(), 1e-12);
    }

    @Test
    void fusionRejectsMalformedInput() {
        assertThrows(IllegalArgumentException.class, () -> KalmanFilter.fuse(new double[0], new double[0]));
        assertThrows(IllegalArgumentException.class, () -> KalmanFilter.fuse(new double[] {1.0}, new double[] {1.0, 2.0}));
        assertThrows(IllegalArgumentException.class, () -> KalmanFilter.fuse(new double[] {1.0}, new double[] {0.0}));
        assertThrows(IllegalArgumentException.class, () -> KalmanFilter.fuse(new double[] {Double.NaN}, new double[] {1.0}));
    }

    @Test
    @DisplayName("fusing two sensors is better than trusting either of them alone")
    void fusionBeatsEitherSensorAlone() {
        double truth = 3.0;
        Random random = new Random(97L);
        double barometerNoise = 1.0;
        double gpsNoise = 3.0;

        KalmanFilter filter = new KalmanFilter(0.0, 100.0, 1e-6, barometerNoise * barometerNoise);
        double[] fused = new double[300];
        KalmanFilter barometerFilter = new KalmanFilter(0.0, 100.0, 1e-6, barometerNoise * barometerNoise);

        for (int i = 0; i < fused.length; i++) {
            double barometer = truth + barometerNoise * random.nextGaussian();
            double gps = truth + gpsNoise * random.nextGaussian();

            filter.predict();
            filter.update(barometer, barometerNoise * barometerNoise);
            filter.update(gps, gpsNoise * gpsNoise);
            fused[i] = filter.estimate();

            barometerFilter.predict();
            barometerFilter.update(barometer);
        }

        assertTrue(filter.errorCovariance() < barometerFilter.errorCovariance(), "fusing must not increase the uncertainty");
        assertEquals(truth, fused[fused.length - 1], 0.5);
    }

    @Test
    void resetRestoresTheInitialState() {
        KalmanFilter filter = new KalmanFilter(5.0, 2.0, 0.1, 1.0);
        assertEquals(0.1, filter.processNoise());
        assertEquals(1.0, filter.measurementNoise());

        filter.filter(100.0);
        filter.reset();

        assertEquals(5.0, filter.estimate());
        assertEquals(2.0, filter.errorCovariance());
        assertEquals(0.0, filter.lastGain());
    }

    @Test
    void toStringMentionsTheState() {
        KalmanFilter filter = new KalmanFilter(1.0, 1.0, 0.1, 1.0);
        assertTrue(filter.toString().contains("estimate=1.0"), filter.toString());
    }

    @Test
    void rejectsNonFiniteVariances() {
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, Double.NaN, 0.1, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, Double.POSITIVE_INFINITY, 0.1, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, 1.0, Double.NaN, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new KalmanFilter(0.0, 1.0, 0.1, Double.POSITIVE_INFINITY));
    }
}
