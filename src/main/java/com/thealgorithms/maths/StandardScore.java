package com.thealgorithms.maths;

public class StandardScore {

    public static double zScore(double num, double mean, double stdDev) {
        double z = (num - mean) / stdDev;
        return z;
    }
}
