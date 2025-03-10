package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 * Find the mode of an array of numbers
 *
 * The mode of an array of numbers is the most frequently occurring number in the array,
 * or the most frequently occurring numbers if there are multiple numbers with the same frequency
 */
public final class Mode {
    private Mode() {
    }

    /*
     * Find the mode of an array of integers
     *
     * @param numbers array of integers
     * @return mode of the array
     */
    public static int[] mode(final int[] numbers) {
        if (numbers.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : numbers) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            } else {
                count.put(num, 1);
            }
        }

        int max = Collections.max(count.values());
        ArrayList<Integer> modes = new ArrayList<>();

        for (final var entry : count.entrySet()) {
            if (entry.getValue() == max) {
                modes.add(entry.getKey());
            }
        }
        return modes.stream().mapToInt(n -> n).toArray();
    }
}
