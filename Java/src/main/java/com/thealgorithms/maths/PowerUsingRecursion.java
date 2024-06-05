package com.thealgorithms.maths;

/**
 * calculate Power using Recursion
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class PowerUsingRecursion {
    private PowerUsingRecursion() {
    }

    public static double power(double base, int exponent) {
        // Base case: anything raised to the power of 0 is 1
        if (exponent == 0) {
            return 1;
        }

        // Recursive case: base ^ exponent = base * base ^ (exponent - 1)
        // Recurse with a smaller exponent and multiply with base
        return base * power(base, exponent - 1);
    }
}
