package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DynamicTimeWarpingTest {

    private static double manhattan(double[] first, double[] second) {
        double sum = 0.0;
        for (int i = 0; i < first.length; i++) {
            sum += Math.abs(first[i] - second[i]);
        }
        return sum;
    }

    private static double[] sine(int length, double period, double phase) {
        double[] series = new double[length];
        for (int i = 0; i < length; i++) {
            series[i] = Math.sin(2 * Math.PI * i / period + phase);
        }
        return series;
    }

    @Test
    void rejectsEmptySeries() {
        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.distance(new double[0], new double[] {1.0}));
        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.distance(new double[] {1.0}, new double[0]));
        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.path(new double[0], new double[] {1.0}));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        double[] good = {1.0, 2.0, 3.0};
        double[] bad = {1.0, value, 3.0};

        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.distance(bad, good));
        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.distance(good, bad));
    }

    @Test
    @DisplayName("a band narrower than the difference in length admits no alignment")
    void rejectsABandThatIsTooNarrow() {
        double[] shorter = {1.0, 2.0, 3.0};
        double[] longer = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

        assertThrows(IllegalArgumentException.class, () -> DynamicTimeWarping.distance(shorter, longer, 2));
        assertEquals(6.0, DynamicTimeWarping.distance(shorter, longer, 3), 1e-12, "the last point of the short series has to carry 3, 4, 5 and 6");
    }

    @Test
    void aSeriesIsAtNoDistanceFromItself() {
        double[] series = {1.0, 4.0, 2.0, 8.0, 3.0};

        assertEquals(0.0, DynamicTimeWarping.distance(series, series));
    }

    @Test
    @DisplayName("two flat series a fixed distance apart cost that distance once per sample")
    void measuresAConstantOffset() {
        double[] first = {1.0, 1.0, 1.0, 1.0};
        double[] second = {3.0, 3.0, 3.0, 3.0};

        assertEquals(8.0, DynamicTimeWarping.distance(first, second), 1e-12, "the diagonal is the shortest path and every cell costs 2");
    }

    @Test
    @DisplayName("a series shifted in value is cheaper than sample by sample, because warping reuses points")
    void warpingBeatsTheStraightComparisonOnARamp() {
        double[] first = {1.0, 2.0, 3.0, 4.0};
        double[] second = {3.0, 4.0, 5.0, 6.0};

        assertEquals(6.0, DynamicTimeWarping.distance(first, second), 1e-12);
        assertEquals(8.0, manhattan(first, second), 1e-12);
    }

    @Test
    void isSymmetric() {
        Random random = new Random(3L);
        for (int trial = 0; trial < 20; trial++) {
            double[] first = new double[10 + random.nextInt(20)];
            double[] second = new double[10 + random.nextInt(20)];
            for (int i = 0; i < first.length; i++) {
                first[i] = random.nextGaussian();
            }
            for (int i = 0; i < second.length; i++) {
                second[i] = random.nextGaussian();
            }

            assertEquals(DynamicTimeWarping.distance(first, second), DynamicTimeWarping.distance(second, first), 1e-9);
        }
    }

    @Test
    @DisplayName("warping never costs more than matching sample by sample")
    void neverExceedsTheStraightComparison() {
        Random random = new Random(11L);
        for (int trial = 0; trial < 50; trial++) {
            double[] first = new double[30];
            double[] second = new double[30];
            for (int i = 0; i < first.length; i++) {
                first[i] = random.nextGaussian();
                second[i] = random.nextGaussian();
            }

            assertTrue(DynamicTimeWarping.distance(first, second) <= manhattan(first, second) + 1e-9);
        }
    }

    @Test
    @DisplayName("a band of zero forces the straight comparison")
    void aBandOfZeroIsTheStraightComparison() {
        double[] first = {1.0, 5.0, 2.0, 8.0};
        double[] second = {2.0, 4.0, 4.0, 7.0};

        assertEquals(manhattan(first, second), DynamicTimeWarping.distance(first, second, 0), 1e-12);
    }

    @Test
    @DisplayName("a narrower band can only cost more")
    void aNarrowerBandCostsAtLeastAsMuch() {
        double[] first = sine(60, 12.0, 0.0);
        double[] second = sine(60, 12.0, 0.9);

        double free = DynamicTimeWarping.distance(first, second);
        double banded = DynamicTimeWarping.distance(first, second, 3);
        double tight = DynamicTimeWarping.distance(first, second, 1);

        assertTrue(banded >= free - 1e-9, "banded " + banded + " should not be below free " + free);
        assertTrue(tight >= banded - 1e-9, "tight " + tight + " should not be below banded " + banded);
    }

    @Test
    @DisplayName("a shift in time costs almost nothing, where a straight comparison is fooled")
    void absorbsAShiftInTime() {
        double[] first = sine(60, 12.0, 0.0);
        double[] second = sine(60, 12.0, Math.PI / 3);

        double warping = DynamicTimeWarping.distance(first, second);
        double straight = manhattan(first, second);

        assertTrue(warping < 0.25 * straight, "warping " + warping + " against straight " + straight);
    }

    @Test
    @DisplayName("a series stretched in time still matches the original")
    void absorbsAStretch() {
        double[] original = sine(40, 10.0, 0.0);
        double[] stretched = new double[80];
        for (int i = 0; i < stretched.length; i++) {
            stretched[i] = original[i / 2];
        }

        double warping = DynamicTimeWarping.distance(original, stretched);

        assertTrue(warping < 1.0, "a stretched copy should be close, but was " + warping);
    }

    @Test
    @DisplayName("the path runs from corner to corner without ever going backwards")
    void thePathIsMonotoneAndComplete() {
        double[] first = sine(30, 8.0, 0.0);
        double[] second = sine(45, 12.0, 0.4);

        int[][] alignment = DynamicTimeWarping.path(first, second);

        assertEquals(0, alignment[0][0]);
        assertEquals(0, alignment[0][1]);
        assertEquals(first.length - 1, alignment[alignment.length - 1][0]);
        assertEquals(second.length - 1, alignment[alignment.length - 1][1]);
        for (int step = 1; step < alignment.length; step++) {
            int rowStep = alignment[step][0] - alignment[step - 1][0];
            int columnStep = alignment[step][1] - alignment[step - 1][1];
            assertTrue(rowStep >= 0 && rowStep <= 1, "the path stepped " + rowStep + " rows");
            assertTrue(columnStep >= 0 && columnStep <= 1, "the path stepped " + columnStep + " columns");
            assertTrue(rowStep + columnStep > 0, "the path stood still");
        }
    }

    @Test
    @DisplayName("the cost of the path is the distance")
    void thePathCostsWhatTheDistanceSays() {
        double[] first = sine(25, 7.0, 0.0);
        double[] second = sine(33, 9.0, 0.2);

        int[][] alignment = DynamicTimeWarping.path(first, second);
        double cost = 0.0;
        for (int[] pair : alignment) {
            cost += Math.abs(first[pair[0]] - second[pair[1]]);
        }

        assertEquals(DynamicTimeWarping.distance(first, second), cost, 1e-9);
    }

    @Test
    void handlesSeriesOfOneSample() {
        assertEquals(3.0, DynamicTimeWarping.distance(new double[] {1.0}, new double[] {4.0}), 1e-12);
        assertEquals(1, DynamicTimeWarping.path(new double[] {1.0}, new double[] {4.0}).length);
    }

    @Test
    void leavesTheSeriesUntouched() {
        double[] first = {1.0, 2.0, 3.0};
        double[] second = {4.0, 5.0};
        double[] firstCopy = first.clone();
        double[] secondCopy = second.clone();

        DynamicTimeWarping.distance(first, second);
        DynamicTimeWarping.path(first, second);

        org.junit.jupiter.api.Assertions.assertArrayEquals(firstCopy, first);
        org.junit.jupiter.api.Assertions.assertArrayEquals(secondCopy, second);
    }
}
