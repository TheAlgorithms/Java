package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.machinelearning.Clustering.ClusteringResult;
import org.junit.jupiter.api.Test;

class ClusteringTest {

    // ------------------------------------------------------------------
    // K-Means
    // ------------------------------------------------------------------

    @Test
    void kMeansClustersTwoWellSeparatedGroups() {
        double[][] points = {
                {0.0, 0.0},
                {0.5, 0.5},
                {1.0, 0.0},
                {10.0, 10.0},
                {10.5, 10.5},
                {11.0, 10.0},
        };
        double[][] initialCentroids = {{0.0, 0.0}, {10.0, 10.0}};

        ClusteringResult result = Clustering.kMeans(points, initialCentroids, 100, 1e-9);
        int[] labels = result.getLabels();

        assertEquals(labels[0], labels[1]);
        assertEquals(labels[0], labels[2]);
        assertEquals(labels[3], labels[4]);
        assertEquals(labels[3], labels[5]);
        assertNotEquals(labels[0], labels[3]);
        assertTrue(result.hasConverged());
    }

    @Test
    void kMeansWithKEqualsOneReturnsMean() {
        double[][] points = {{0.0, 0.0}, {2.0, 0.0}, {1.0, 3.0}};
        double[][] initialCentroids = {{0.0, 0.0}};

        ClusteringResult result = Clustering.kMeans(points, initialCentroids, 50, 1e-9);

        assertArrayEquals(new int[] {0, 0, 0}, result.getLabels());
        assertArrayEquals(new double[] {1.0, 1.0}, result.getCenters()[0], 1e-9);
    }

    @Test
    void kMeansWithKEqualsNKeepsEveryPointItsOwnCluster() {
        double[][] points = {{0.0, 0.0}, {5.0, 5.0}, {10.0, 10.0}};
        double[][] initialCentroids = {{0.0, 0.0}, {5.0, 5.0}, {10.0, 10.0}};

        ClusteringResult result = Clustering.kMeans(points, initialCentroids, 50, 1e-9);

        assertArrayEquals(new int[] {0, 1, 2}, result.getLabels());
        assertEquals(1, result.getIterations());
        assertTrue(result.hasConverged());
    }

    @Test
    void kMeansSeededRandomInitializationIsReproducible() {
        double[][] points = {
                {0.0, 0.0},
                {0.1, 0.2},
                {8.0, 8.0},
                {8.2, 7.9},
                {4.0, 0.0},
                {4.1, 0.1},
        };

        ClusteringResult r1 = Clustering.kMeans(points, 3, 7L, 100, 1e-9);
        ClusteringResult r2 = Clustering.kMeans(points, 3, 7L, 100, 1e-9);

        assertArrayEquals(r1.getLabels(), r2.getLabels());
        for (int c = 0; c < r1.getCenters().length; c++) {
            assertArrayEquals(r1.getCenters()[c], r2.getCenters()[c], 1e-9);
        }
    }

    @Test
    void emptyClusterKeepsItsPreviousCenterUnchanged() {

        double[][] points = {
                {0.0, 0.0},
                {0.1, 0.1},
                {0.2, 0.0},
                {10.0, 10.0},
                {10.1, 10.1},
                {10.2, 10.0},
        };
        double[][] initialCenters = {{0.0, 0.0}, {10.0, 10.0}, {10.0, 10.0}};

        ClusteringResult result = Clustering.kMeans(points, initialCenters, 1, 1e-9);

        assertEquals(1, result.getIterations());
        assertArrayEquals(new double[] {10.0, 10.0}, result.getCenters()[2], 1e-9);
        for (int label : result.getLabels()) {
            assertNotEquals(2, label);
        }
    }

    // ------------------------------------------------------------------
    // K-Medians
    // ------------------------------------------------------------------

    @Test
    void kMediansClustersTwoWellSeparatedGroups() {
        double[][] points = {
                {0.0, 0.0},
                {0.5, 0.5},
                {1.0, 0.0},
                {10.0, 10.0},
                {10.5, 10.5},
                {11.0, 10.0},
        };
        double[][] initialCenters = {{0.0, 0.0}, {10.0, 10.0}};

        ClusteringResult result = Clustering.kMedians(points, initialCenters, 100, 1e-9);
        int[] labels = result.getLabels();

        assertEquals(labels[0], labels[1]);
        assertEquals(labels[0], labels[2]);
        assertEquals(labels[3], labels[4]);
        assertEquals(labels[3], labels[5]);
        assertNotEquals(labels[0], labels[3]);
        assertTrue(result.hasConverged());
    }

    @Test
    void kMediansIsMoreRobustToOutliersThanKMeans() {
        // One tight group plus a single extreme outlier attached to it.
        double[][] points = {
                {1.0, 1.0},
                {1.1, 0.9},
                {0.9, 1.1},
                {1.0, 1.0},
                {100.0, 100.0}, // outlier
        };
        double[][] initialCenter = {{1.0, 1.0}};

        ClusteringResult meansResult = Clustering.kMeans(points, initialCenter, 50, 1e-9);
        ClusteringResult mediansResult = Clustering.kMedians(points, initialCenter, 50, 1e-9);

        // The mean is dragged noticeably toward the outlier; the median is not.
        double meanX = meansResult.getCenters()[0][0];
        double medianX = mediansResult.getCenters()[0][0];

        assertTrue(meanX > medianX);
        assertEquals(1.0, medianX, 1e-9);
    }

    @Test
    void kMediansWithKEqualsNKeepsEveryPointItsOwnCluster() {
        double[][] points = {{0.0, 0.0}, {5.0, 5.0}, {10.0, 10.0}};
        double[][] initialCenters = {{0.0, 0.0}, {5.0, 5.0}, {10.0, 10.0}};

        ClusteringResult result = Clustering.kMedians(points, initialCenters, 50, 1e-9);

        assertArrayEquals(new int[] {0, 1, 2}, result.getLabels());
        assertTrue(result.hasConverged());
    }

    @Test
    void kMediansSeededRandomInitializationIsReproducible() {
        double[][] points = {
                {0.0, 0.0},
                {0.1, 0.2},
                {8.0, 8.0},
                {8.2, 7.9},
                {4.0, 0.0},
                {4.1, 0.1},
        };

        ClusteringResult r1 = Clustering.kMedians(points, 3, 11L, 100, 1e-9);
        ClusteringResult r2 = Clustering.kMedians(points, 3, 11L, 100, 1e-9);

        assertArrayEquals(r1.getLabels(), r2.getLabels());
        for (int c = 0; c < r1.getCenters().length; c++) {
            assertArrayEquals(r1.getCenters()[c], r2.getCenters()[c], 1e-9);
        }
    }

    // ------------------------------------------------------------------
    // Shared validation (exercised through kMeans; identical path for kMedians)
    // ------------------------------------------------------------------

    @Test
    void rejectsNonPositiveMaxIterations() {
        double[][] points = {{0.0, 0.0}, {1.0, 1.0}};
        double[][] centers = {{0.0, 0.0}};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, centers, 0, 1e-9));
    }

    @Test
    void rejectsNegativeTolerance() {
        double[][] points = {{0.0, 0.0}, {1.0, 1.0}};
        double[][] centers = {{0.0, 0.0}};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, centers, 10, -1.0));
    }

    @Test
    void rejectsKGreaterThanNumberOfPoints() {
        double[][] points = {{0.0, 0.0}, {1.0, 1.0}};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, 3, 42L, 10, 1e-9));
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMedians(points, 3, 42L, 10, 1e-9));
    }

    @Test
    void rejectsEmptyDataset() {
        double[][] points = {};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, 1, 42L, 10, 1e-9));
    }

    @Test
    void rejectsInconsistentDimensions() {
        double[][] points = {{0.0, 0.0}, {1.0, 1.0, 1.0}};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, 1, 42L, 10, 1e-9));
    }

    @Test
    void rejectsEmptyInitialCenters() {
        // k is derived from initialCenters.length, so a zero-length array means k = 0.
        double[][] points = {{0.0, 0.0}, {1.0, 1.0}, {2.0, 2.0}};
        double[][] initialCenters = {};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, initialCenters, 10, 1e-9));
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMedians(points, initialCenters, 10, 1e-9));
    }

    @Test
    void rejectsInitialCenterWithMismatchedDimension() {
        double[][] points = {{0.0, 0.0}, {1.0, 1.0}, {2.0, 2.0}};
        double[][] initialCenters = {{0.0, 0.0}, {1.0, 1.0, 1.0}};
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMeans(points, initialCenters, 10, 1e-9));
        assertThrows(IllegalArgumentException.class, () -> Clustering.kMedians(points, initialCenters, 10, 1e-9));
    }
}