package com.thealgorithms.misc;

import java.util.HashMap;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

public final class TwoSumProblem {
    private TwoSumProblem() {
    }

    /**
     * The function "twoSum" takes an array of integers and a target integer as input, and returns an
     * array of two indices where the corresponding elements in the input array add up to the target.
     * @param values An array of integers.
     * @param target The target is the sum that we are trying to find using two numbers from the given array.
     * @return A pair or indexes such that sum of values at these indexes equals to the target
     * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
     */

    public static Optional<Pair<Integer, Integer>> twoSum(final int[] values, final int target) {
        HashMap<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            final var remainder = target - values[i];
            if (valueToIndex.containsKey(remainder)) {
                return Optional.of(Pair.of(valueToIndex.get(remainder), i));
            }
            if (!valueToIndex.containsKey(values[i])) {
                valueToIndex.put(values[i], i);
            }
        }
        return Optional.empty();
    }
}
