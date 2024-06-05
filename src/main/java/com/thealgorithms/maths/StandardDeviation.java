package com.thealgorithms.maths;

public final class StandardDeviation {
    private StandardDeviation() {
    }

    public static double stdDev(double[] data) {
        double variance = 0;
        double avg = 0;
        for (int i = 0; i < data.length; i++) {
            avg += data[i];
        }
        avg /= data.length;
        for (int j = 0; j < data.length; j++) {
            variance += Math.pow((data[j] - avg), 2);
        }
        variance /= data.length;
        return Math.sqrt(variance);
    }
}
