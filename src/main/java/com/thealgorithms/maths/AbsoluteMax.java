package com.thealgorithms.maths;

public final class AbsoluteMax {
    private AbsoluteMax() {
    }

    /**
     * Finds the absolute maximum value among the given numbers.
     *
     * @param numbers The numbers to compare.
     * @return The absolute maximum value.
     * @throws IllegalArgumentException If the input array is empty or null.
     */
    public static int getMaxValue(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }
        int absMax = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (Math.abs(numbers[i]) > Math.abs(absMax) || (Math.abs(numbers[i]) == Math.abs(absMax) && numbers[i] > absMax)) {
                absMax = numbers[i];
            }
        }
        return absMax;
    }
}
