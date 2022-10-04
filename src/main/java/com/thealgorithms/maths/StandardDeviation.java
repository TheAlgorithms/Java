package com.thealgorithms.maths;

public class StandardDeviation {

    public static double stdDev(double[] data) {
        double var = 0;
        double avg = 0;
        for (int i = 0; i < data.length; i++) {
            avg += data[i];
        }
        avg /= data.length;
        for (int j = 0; j < data.length; j++) {
            var += Math.pow((data[j] - avg), 2);
        }
        var /= data.length;
        return Math.sqrt(var);
    }
}
