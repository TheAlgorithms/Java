/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find out the inverse square root of the given number*/

/** Wikipedia Link - https://en.wikipedia.org/wiki/Fast_inverse_square_root */

package com.thealgorithms.maths;

public class FastInverseSqrt {

    public static boolean inverseSqrt(float number) {
        float x = number;
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x = x * (1.5f - xhalf * x * x);
        return x == (float) ((float) 1 / (float) Math.sqrt(number));
    }

    /**
     * Returns the inverse square root of the given number upto 6 - 8 decimal places.
     * calculates the inverse square root of the given number and returns true if calculated answer matches with given answer else returns false
     */

    public static boolean inverseSqrt(double number) {
        double x = number;
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        for (int it = 0; it < 4; it++) {
            x = x * (1.5d - xhalf * x * x);
        }
        x *= number;
        return x == 1 / Math.sqrt(number);
    }
    /**
     * Returns the inverse square root of the given number upto 14 - 16 decimal places.
     * calculates the inverse square root of the given number and returns true if calculated answer matches with given answer else returns false
     */
}
/**
 * OUTPUT :
 * Input - number = 4522
 * Output: it calculates the inverse squareroot of a number and returns true with it matches the given answer else returns false.
 * 1st approach Time Complexity : O(1)
 * Auxiliary Space Complexity : O(1)
 * Input - number = 4522
 * Output: it calculates the inverse squareroot of a number and returns true with it matches the given answer else returns false.
 * 2nd approach Time Complexity : O(1)
 * Auxiliary Space Complexity : O(1)
 */
