package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KNNTest {
    @Test
    public void testClassifyStandard() {
        List<KNN.DataPoint> dataset = new ArrayList<>();
        dataset.add(new KNN.DataPoint(new double[] {1.0, 1.0}, "GroupA"));
        dataset.add(new KNN.DataPoint(new double[] {1.5, 2.0}, "GroupA"));
        dataset.add(new KNN.DataPoint(new double[] {8.0, 9.0}, "GroupB"));

        double[] target = new double[] {1.2, 1.3};
        String result = KNN.classify(dataset, target, 2);
        Assertions.assertEquals("GroupA", result);
    }
}
