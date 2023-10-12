package com.thealgorithms.maths;

public final class MinValue {
    private MinValue() {
    }
    /**
     * Returns the smaller of two {@code int} values. That is, the result the
     * argument closer to the value of {@link Integer#MIN_VALUE}. If the
     * arguments have the same value, the result is that same value.
     *
     * @param a an argument.
     * @param b another argument.
     * @return the smaller of {@code a} and {@code b}.
     */
    public static int min(int a, int b) {
        return a <= b ? a : b;
    }
}
