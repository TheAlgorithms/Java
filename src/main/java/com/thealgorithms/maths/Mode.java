package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class to calculate the mode(s) of an array of integers.
 * <p>
 * The mode of an array is the integer value(s) that occur most frequently.
 * If multiple values have the same highest frequency, all such values are returned.
 * </p>
 */
public final class Mode {
    private Mode() {
    }

    /**
     * Computes the mode(s) of the specified array of integers.
     * <p>
     * If the input array is empty, this method returns {@code null}.
     * If multiple numbers share the highest frequency, all are returned in the result array.
     * </p>
     *
     * @param numbers an array of integers to analyze
     * @return an array containing the mode(s) of the input array, or {@code null} if the input is empty
     */
    public static int[] mode(final int[] numbers) {
        if (numbers.length == 0) {
            return null;
        }

        Map<Integer, Integer> count = new HashMap<>();

        for (int num : numbers) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int max = Collections.max(count.values());
        List<Integer> modes = new ArrayList<>();

        for (final var entry : count.entrySet()) {
            if (entry.getValue() == max) {
                modes.add(entry.getKey());
            }
        }
        return modes.stream().mapToInt(Integer::intValue).toArray();
    }
}
