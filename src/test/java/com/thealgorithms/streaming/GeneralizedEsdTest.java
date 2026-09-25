package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GeneralizedEsdTest {

    /**
     * The sample Rosner published with the test, also used as the worked example in the NIST
     * engineering statistics handbook.
     */
    private static final double[] ROSNER_SAMPLE = {-0.25, 0.68, 0.94, 1.15, 1.20, 1.26, 1.26, 1.34, 1.38, 1.43, 1.49, 1.49, 1.55, 1.56, 1.58, 1.65, 1.69, 1.70, 1.76, 1.77, 1.81, 1.91, 1.94, 1.96, 1.99, 2.06, 2.09, 2.10, 2.14, 2.15, 2.23, 2.24, 2.26, 2.35, 2.37, 2.40, 2.47, 2.54, 2.62, 2.64, 2.90,
        2.92, 2.92, 2.93, 3.21, 3.26, 3.30, 3.59, 3.68, 4.30, 4.64, 5.34, 5.42, 6.01};

    private static double[] gaussianSample(int size, long seed) {
        Random random = new Random(seed);
        double[] sample = new double[size];
        for (int i = 0; i < size; i++) {
            sample[i] = 100.0 + 5.0 * random.nextGaussian();
        }
        return sample;
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, -0.1, 1.5, Double.NaN})
    void rejectsInvalidSignificanceLevels(double level) {
        assertThrows(IllegalArgumentException.class, () -> new GeneralizedEsd(level));
    }

    @Test
    void rejectsSamplesThatAreTooSmall() {
        GeneralizedEsd test = new GeneralizedEsd();

        assertThrows(IllegalArgumentException.class, () -> test.findOutliers(new double[] {1.0, 2.0}, 1));
    }

    @Test
    void rejectsAnOutlierBoundOutOfRange() {
        GeneralizedEsd test = new GeneralizedEsd();
        double[] sample = gaussianSample(20, 1L);

        assertThrows(IllegalArgumentException.class, () -> test.findOutliers(sample, 0));
        assertThrows(IllegalArgumentException.class, () -> test.findOutliers(sample, 19));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        GeneralizedEsd test = new GeneralizedEsd();
        double[] sample = gaussianSample(20, 2L);
        sample[7] = value;

        assertThrows(IllegalArgumentException.class, () -> test.findOutliers(sample, 3));
    }

    @Test
    void exposesItsConfiguration() {
        assertEquals(0.05, new GeneralizedEsd().significanceLevel());
        assertEquals(0.01, new GeneralizedEsd(0.01).significanceLevel());
    }

    @Test
    @DisplayName("the critical values match the table published with the test")
    void reproducesThePublishedCriticalValues() {
        GeneralizedEsd test = new GeneralizedEsd(0.05);

        assertEquals(3.159, test.criticalValue(54, 1), 5e-4);
        assertEquals(3.151, test.criticalValue(54, 2), 5e-4);
        assertEquals(3.144, test.criticalValue(54, 3), 5e-4);
        assertEquals(3.085, test.criticalValue(54, 10), 5e-4);
    }

    @Test
    @DisplayName("Rosner's own sample of 54 points holds exactly three outliers")
    void reproducesTheWorkedExample() {
        GeneralizedEsd test = new GeneralizedEsd(0.05);

        int[] outliers = test.findOutliers(ROSNER_SAMPLE, 10);

        assertArrayEqualsSorted(new int[] {51, 52, 53}, outliers);
        assertEquals(5.34, ROSNER_SAMPLE[outliers[0]]);
        assertEquals(5.42, ROSNER_SAMPLE[outliers[1]]);
        assertEquals(6.01, ROSNER_SAMPLE[outliers[2]]);
    }

    @Test
    @DisplayName("the answer is the last test that succeeds, not the first")
    void findsOutliersThatMaskEachOther() {
        double[] sample = gaussianSample(40, 0L);
        sample[10] = 160.0;
        sample[20] = 161.0;
        sample[30] = 162.0;

        int[] outliers = new GeneralizedEsd(0.05).findOutliers(sample, 5);

        assertArrayEqualsSorted(new int[] {10, 20, 30}, outliers);
    }

    @Test
    void findsASingleOutlier() {
        double[] sample = gaussianSample(30, 4L);
        sample[13] = 200.0;

        int[] outliers = new GeneralizedEsd().findOutliers(sample, 5);

        assertArrayEqualsSorted(new int[] {13}, outliers);
    }

    @Test
    @DisplayName("clean data holds no outliers")
    void findsNothingInCleanData() {
        for (long seed = 0; seed < 12; seed++) {
            double[] sample = gaussianSample(60, seed);

            int[] outliers = new GeneralizedEsd(0.001).findOutliers(sample, 6);

            assertEquals(0, outliers.length, "seed " + seed + " reported " + Arrays.toString(outliers));
        }
    }

    @Test
    @DisplayName("the significance level is a rate, not a promise: clean data does report the odd outlier")
    void falseAlarmsFollowTheSignificanceLevel() {
        int reporting = 0;
        for (long seed = 0; seed < 12; seed++) {
            if (new GeneralizedEsd(0.05).findOutliers(gaussianSample(60, seed), 6).length > 0) {
                reporting++;
            }
        }

        assertTrue(reporting > 0, "at five percent some clean sample is expected to report something");
        assertTrue(reporting <= 4, "but it must stay rare, " + reporting + " of 12 samples reported");
    }

    @Test
    @DisplayName("a sample with no spread at all has no outliers")
    void handlesAConstantSample() {
        double[] sample = new double[20];
        Arrays.fill(sample, 3.0);

        assertEquals(0, new GeneralizedEsd().findOutliers(sample, 5).length);
    }

    @Test
    void leavesTheSampleUntouched() {
        double[] sample = gaussianSample(30, 5L);
        sample[3] = 500.0;
        double[] copy = Arrays.copyOf(sample, sample.length);

        new GeneralizedEsd().findOutliers(sample, 4);

        Assertions.assertArrayEquals(copy, sample);
    }

    @Test
    @DisplayName("a stricter level reports at most as many outliers as a looser one")
    void aStricterLevelIsMoreConservative() {
        double[] sample = gaussianSample(50, 6L);
        sample[5] = 125.0;
        sample[6] = 126.0;

        int loose = new GeneralizedEsd(0.10).findOutliers(sample, 8).length;
        int strict = new GeneralizedEsd(0.001).findOutliers(sample, 8).length;

        assertTrue(strict <= loose, "strict found " + strict + ", loose found " + loose);
    }

    @Test
    @DisplayName("the bound on the number of outliers is respected")
    void neverReportsMoreThanTheBound() {
        double[] sample = gaussianSample(40, 7L);
        for (int i = 0; i < 6; i++) {
            sample[i] = 300.0 + i;
        }

        int[] outliers = new GeneralizedEsd().findOutliers(sample, 2);

        assertTrue(outliers.length <= 2);
    }

    @Test
    void findsOutliersOnBothSides() {
        double[] sample = gaussianSample(40, 8L);
        sample[4] = 300.0;
        sample[24] = -150.0;

        int[] outliers = new GeneralizedEsd().findOutliers(sample, 4);

        assertArrayEqualsSorted(new int[] {4, 24}, outliers);
    }

    @Test
    void toStringMentionsTheLevel() {
        String text = new GeneralizedEsd(0.05).toString();

        assertTrue(text.contains("GeneralizedEsd"));
        assertTrue(text.contains("0.05"));
    }

    private static void assertArrayEqualsSorted(int[] expected, int[] actual) {
        Assertions.assertArrayEquals(expected, actual, "expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
    }
}
