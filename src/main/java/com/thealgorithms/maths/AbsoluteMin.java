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
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty");
        }

        int minValue = numbers[0];
        for (int number : numbers) {
            long absoluteNumber = Math.abs((long) number);
            long absoluteMinValue = Math.abs((long) minValue);
            if (absoluteNumber < absoluteMinValue || (absoluteNumber == absoluteMinValue && number < minValue)) {
                // For equal absolute values, consistently choose the numerically smaller value.
                minValue = number;
            }
        }
        return minValue;
    }
}
