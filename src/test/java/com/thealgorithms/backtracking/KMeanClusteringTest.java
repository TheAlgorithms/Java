package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class KMeansTest {

    @Test
    void testSimpleKMeans() {
        KMeans kMeans = new KMeans();

        double[][] data = {
            {1.0, 2.0}, {1.5, 1.8}, {5.0, 8.0}, {8.0, 8.0},
            {1.0, 0.6}, {9.0, 11.0}, {8.0, 2.0}, {10.0, 2.0}, {9.0, 3.0}
        };

        List<List<double[]>> clusters = kMeans.kMeans(data, 2, 100);
        assertEquals(2, clusters.size());
    }
}
