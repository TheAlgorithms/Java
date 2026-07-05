package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
}
