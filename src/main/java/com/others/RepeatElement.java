package com.others;

import java.util.*;

/**
 * Given an array full of integers, you need to find the elements repeated k times.
 *
 * @author Duc Tran Tien.
 */
public class RepeatElement {
    /**
     * This method give array and repeated times, return satisfy elements. If not throw an IllegalArgumentException
     *
     * @param numbers : an array full of integer
     * @param times   : repeated times
     * @return : array of elements
     */
    public static int [] findRepeatElement(int[] numbers, int times) {
        if (numbers == null || times <= 0) {
            throw new IllegalArgumentException(
                    "You can't pass null arrays or times values minor than 0 as parameter.");
        }
        List<Integer> _result = new ArrayList<>();
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int i : numbers) {
            if (counterMap.get(i) == null) {
                counterMap.put(i, 1);
            } else {
                counterMap.put(i, counterMap.get(i) + 1);
            }
        }
        for (Integer candidate : counterMap.keySet()) {
            if (counterMap.get(candidate) == times) {
                _result.add(candidate);
            }
        }
        int [] result = new int[_result.size()];
        for (int i = 0; i < _result.size(); i++) {
            result[i] = _result.get(i);
        }
        return result;
    }
}
