package com.thealgorithms.maths;

import java.util.Arrays;

public class AbsoluteMax {

    /**
     * Compares the numbers given as arguments to get the absolute max value.
     *
     * @param numbers The numbers to compare
     * @return The absolute max value
     */
    public static int getMaxValue(int... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty");
        }

        var absMaxWrapper = new Object() {
            int value = numbers[0];
        };

        Arrays
            .stream(numbers)
            .skip(1)
            .filter(number -> Math.abs(number) > Math.abs(absMaxWrapper.value))
            .forEach(number -> absMaxWrapper.value = number);

        return absMaxWrapper.value;
    }
}
