package com.thealgorithms.maths;

import java.util.Random;

public class Ceil {

    /**
     * Returns the smallest (closest to negative infinity)
     *
     * @param number the number
     * @return the smallest (closest to negative infinity) of given
     * {@code number}
     */
    public static double ceil(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) (number + 1);
        } else {
            return (int) number;
        }
    }
}
