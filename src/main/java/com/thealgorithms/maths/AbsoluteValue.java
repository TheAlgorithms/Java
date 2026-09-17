package com.thealgorithms.maths;

public final class AbsoluteValue {
    private AbsoluteValue() {
    }

    /**
     * Returns the absolute value of a number.
     *
     * @param number The number to be transformed
     * @return The absolute value of the {@code number}
     */
    public static long getAbsValue(long number) {
        return Math.abs(number);
    }
}
