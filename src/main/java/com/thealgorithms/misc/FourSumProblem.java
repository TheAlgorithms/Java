package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
/**
 * Four Sum Problem
 * 
 * This algorithm finds all unique quadruplets in an array that sum up to a given target.
 * 
 * URL: https://en.wikipedia.org/wiki/Subset_sum
 */
public class FourSumProblem {
    
    public static List<Pair<Integer, Integer>> fourSum(int[] values, int target) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        if (values == null || values.length < 4) {
            return result;
        }

        Arrays.sort(values);

        for (int i = 0; i < values.length - 3; i++) {
            if (i > 0 && values[i] == values[i - 1]) {
                continue; // Skip duplicates
            }
            for (int j = i + 1; j < values.length - 2; j++) {
                if (j > i + 1 && values[j] == values[j - 1]) {
                    continue; // Skip duplicates
                }

                int left = j + 1;
                int right = values.length - 1;

                while (left < right) {
                    int sum = values[i] + values[j] + values[left] + values[right];
                    if (sum == target) {
                        result.add(Pair.of(left, right));
                        
                        // Skip duplicates
                        while (left < right && values[left] == values[left + 1]) left++;
                        while (left < right && values[right] == values[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
