package com.thealgorithms.maths;

public final class SquareRootWithBabylonianMethod {
    private SquareRootWithBabylonianMethod() {
    }

    /**
     * get the value, return the square root
     *
     * @param num contains elements
     * @return the square root of num
     */
    public static float squareRoot(float num) {
        float a = num;
        float b = 1;
        double e = 0.000001;
        while (a - b > e) {
            a = (a + b) / 2;
            b = num / a;
        }
        return a;
    }
}
