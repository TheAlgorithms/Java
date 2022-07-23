package com.thealgorithms.maths;

public class FastSqrt {

    /**
     * To better understand this algorithm, please refer to the following link: https://en.wikipedia.org/wiki/Fast_inverse_square_root
     */

    /**
     * Returns the inverse square root of the given number.
     *
     * @param number the number
     * @return the inverse square root of the given number
     */
    public static float inverseSqrt(float number) {
        float x = number;
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x = x * (1.5f - xhalf * x * x);
        return x;
    }

    /**
     * Returns the inverse square root of the given number in a more accurate way.
     *
     * @param number the number
     * @return the inverse square root of the given number
     */
    public static double inverseSqrt(double number) {
        double x = number;
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        for (int it = 0; it < 4; it++) {
            x = x * (1.5d - xhalf * x * x);
        }
        x *= number;
        return x;
    }
}
