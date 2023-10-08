package com.thealgorithms.maths;

public class StandardScore {

    public static double zScore(double num, double mean, double stdDev) {
        return (num - mean) / stdDev;
    }
}
