package com.thealgorithms.maths;

public final class AbsoluteMin {
    private AbsoluteMin() {
    }

    /**
     * Compares the numbers given as arguments to get the absolute min value.
     *
     * @param numbers The numbers to compare
     * @return The absolute min value
     */
    public static int getMinValue(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }

        int absMin = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (Math.abs(numbers[i]) < Math.abs(absMin) || (Math.abs(numbers[i]) == Math.abs(absMin) && numbers[i] < absMin)) {
                absMin = numbers[i];
            }
        }
        return absMin;
    }
}
