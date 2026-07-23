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

        long absMin = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            long current = numbers[i];
            if (Math.abs(current) < Math.abs(absMin) || (Math.abs(current) == Math.abs(absMin) && current < absMin)) {
                absMin = current;
            }
        }
        return (int) absMin;
    }
}
