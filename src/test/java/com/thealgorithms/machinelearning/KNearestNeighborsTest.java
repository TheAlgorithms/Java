package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class KNearestNeighborsTest {

    @Test
    void predictsCorrectClassOnSeparableDataset() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        assertEquals(0, knn.predict(new double[] {1.5, 1.5}));
        assertEquals(1, knn.predict(new double[] {8.5, 8.5}));
    }

    @Test
    void predictsBatchClassesOnSeparableDataset() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        double[][] samples = {{1.5, 1.5}, {8.5, 8.5}};
        int[] predictions = knn.predict(samples);

        assertArrayEquals(new int[] {0, 1}, predictions);
    }

    @Test
    void throwsExceptionWhenKIsNotPositive() {
        assertThrows(IllegalArgumentException.class, () -> new KNearestNeighbors(-4));
        assertThrows(IllegalArgumentException.class, () -> new KNearestNeighbors(0));
    }

    @Test
    void throwsExceptionWhenKIsGreaterThanNumberOfTrainingSamples() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};
        KNearestNeighbors knn = new KNearestNeighbors(7);
        knn.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> knn.predict(new double[] {1.5, 1.5}));
    }

    @Test
    void nullFeaturesArrayThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(null, new int[] {0, 1}));
    }

    @Test
    void emptyFeaturesArrayThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {}, new int[] {}));
    }

    @Test
    void nullLabelsArrayThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{1, 1}, {2, 2}}, null));
    }

    @Test
    void emptyLabelsArrayThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{1, 1}, {2, 2}}, new int[] {}));
    }

    @Test
    void mismatchedFeatureAndLabelLengthsThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(3);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{0, 0}, {1, 1}, {8, 8}, {9, 9}}, new int[] {0, 0, 1}));
    }

    @Test
    void firstFeatureVectorNullThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {null, {1, 1}}, new int[] {0, 1}));
    }

    @Test
    void emptyFeatureVectorThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(1);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{}}, new int[] {0}));
    }

    @Test
    void nullFeatureSampleThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(2);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{0, 0}, null, {8, 8}}, new int[] {0, 0, 1}));
    }

    @Test
    void mismatchedDimensionThrowsIllegalArgumentException() {
        KNearestNeighbors knn = new KNearestNeighbors(2);

        assertThrows(IllegalArgumentException.class, () -> knn.fit(new double[][] {{0, 0}, {1, 1, 2}, {8, 8}}, new int[] {0, 0, 1}));
    }

    @Test
    void predictBeforeFitThrowsIllegalStateException() {
        KNearestNeighbors knn = new KNearestNeighbors(3);

        assertThrows(IllegalStateException.class, () -> knn.predict(new double[] {1, 1}));
    }

    @Test
    void nullTestPointThrowsIllegalArgumentException() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> knn.predict((double[]) null));
        assertThrows(IllegalArgumentException.class, () -> knn.predict((double[][]) null));
    }

    @Test
    void mismatchedTestPointLengthThrowsIllegalArgumentException() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> knn.predict(new double[] {1.5, 1.5, 1.5}));
    }

    @Test
    void tieBreakReturnsSmallerLabel() {
        double[][] features = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};

        int[] labels = {0, 1, 0, 1};

        KNearestNeighbors knn = new KNearestNeighbors(4);
        knn.fit(features, labels);

        assertEquals(0, knn.predict(new double[] {1, 1}));
    }

    @Test
    void tieBreakKeepsCurrentWinnerWhenLabelIsGreater() {
        double[][] features = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};

        int[] labels = {1, 0, 1, 0};

        KNearestNeighbors knn = new KNearestNeighbors(4);
        knn.fit(features, labels);

        assertEquals(0, knn.predict(new double[] {1, 1}));
    }

    @Test
    void nullBatchSampleThrowsIllegalArgumentException() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> knn.predict(new double[][] {{1.5, 1.5}, null}));
    }

    @Test
    void invalidBatchSampleThrowsIllegalArgumentException() {
        double[][] features = {
            {0, 0},
            {1, 1},
            {8, 8},
            {9, 9},
        };

        int[] labels = {0, 0, 1, 1};

        KNearestNeighbors knn = new KNearestNeighbors(3);
        knn.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> knn.predict(new double[][] {{1.5, 1.5}, {8.5, 8.5, 8.5}}));
    }
}
