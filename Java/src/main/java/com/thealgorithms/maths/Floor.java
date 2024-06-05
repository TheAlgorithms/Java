package com.thealgorithms.maths;

public final class Floor {

    private Floor() {
    }

    /**
     * Returns the largest (closest to positive infinity)
     *
     * @param number the number
     * @return the largest (closest to positive infinity) of given
     * {@code number}
     */
    public static double floor(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) number;
        } else {
            return (int) number - 1;
        }
    }
}
