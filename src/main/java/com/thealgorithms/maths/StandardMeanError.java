package com.thealgorithms.maths;

public class StandardMeanError {

    public static double stdMeanError(double[] data) {
        return StandardDeviation.stdDev(data) / SquareRootWithBabylonianMethod.square_Root(data.length);
    }
}
