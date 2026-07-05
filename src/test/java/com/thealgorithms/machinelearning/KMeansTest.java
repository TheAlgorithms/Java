package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class KMeansTest {

    @Test
    void testSimpleClustering() {

        double[][] points = {
            {1.0, 1.0},
            {1.2, 1.1},
            {8.0, 8.0},
            {8.2, 8.1}
        };

        double[][] centroids = {
            {1.0, 1.0},
            {8.0, 8.0}
        };

        int[] expected = {0, 0, 1, 1};

        assertArrayEquals(
                expected,
                KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testNullCentroids() {
        double[][] points = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, null, 100, 0.0001)
        );

    }

    @Test
    void testEmptyDataset() {
        double[][] points = {};
        double[][] centroids = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testEmptyPoints() {
        double[][] points = {};
        double[][] centroids = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testNoCentroids() {
        double[][] points = {
            {1.0, 1.0}
        };

        double[][] centroids = {};

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testNonPositiveMaxIterations() {
        double[][] points = {
            {1.0, 1.0}
        };

        double[][] centroids = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 0, 0.0001)
        );
    }

    @Test
    void testNegativeTolerance() {
        double[][] points = {
            {1.0, 1.0}
        };
        double[][] centroids = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, -1.0)
        );
    }

    @Test
    void testTooManyCentroids() {
        double[][] points = {
            {1.0, 1.0}
        };

        double[][] centroids = {
            {1.0, 1.0},
            {2.0, 2.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testDimensionMismatchInPoints() {
        double[][] points = {
            {1.0, 1.0},
            {2.0}
        };

        double[][] centroids = {
            {1.0, 1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testDimensionMismatchInCentroids() {
        double[][] points = {
            {1.0, 1.0}
        };

        double[][] centroids = {
            {1.0}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testZeroDimensionPoints() {
        double[][] points = {
            {}
        };

        double[][] centroids = {
            {}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testSingleCluster() {
        double[][] points = {
            {1.0, 1.0},
            {2.0, 2.0},
            {3.0, 3.0}
        };

        double[][] centroids = {
            {2.0, 2.0}
        };

        int[] expected = {
            0, 0, 0
        };

        assertArrayEquals(
                expected,
                KMeans.cluster(points, centroids, 100, 0.0001)
        );
    }

    @Test
    void testEmptyClusterHandling() {
        double[][] points = {
            {0.0, 0.0},
            {0.1, 0.1},
            {10.0, 10.0}
        };

        double[][] centroids = {
            {0.0, 0.0},
            {100.0, 100.0}
        };

        int[] result = KMeans.cluster(points, centroids, 100, 0.0001);

        assertArrayEquals(
                new int[]{0, 0, 0},
                result
        );
    }

    @Test
    void testImmediateConvergence() {
        double[][] points = {
            {1.0, 1.0},
            {9.0, 9.0}
        };

        double[][] centroids = {
            {1.0, 1.0},
            {9.0, 9.0}
        };

        int[] expected = {
            0, 1
        };

        assertArrayEquals(
                expected,
                KMeans.cluster(points, centroids, 100, 0.000001)
        );
    }
}
