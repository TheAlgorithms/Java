package com.thealgorithms.maths;

import java.util.function.BiPredicate;

public final class SecondMinMax {

    /**
     * Utility class for finding second maximum or minimum based on BiPredicate
     * @exception IllegalArgumentException => if input array is of length less than 2 also if all elements are same
     * @return the second minimum / maximum value from the input array
     * @author Bharath Sanjeevi ( https://github.com/BharathSanjeeviT )
     */

    private SecondMinMax() {
    }

    private static int secondBest(final int[] arr, final int initialVal, final BiPredicate<Integer, Integer> isBetter) {
        checkInput(arr);
        int best = initialVal;
        int secBest = initialVal;
        for (final int num : arr) {
            if (isBetter.test(num, best)) {
                secBest = best;
                best = num;
            } else if ((isBetter.test(num, secBest)) && (num != best)) {
                secBest = num;
            }
        }
        checkOutput(secBest, initialVal);
        return secBest;
    }

    /**
     * @brief Finds the Second minimum / maximum value from the array
     * @param arr the input array
     * @exception IllegalArgumentException => if input array is of length less than 2 also if all elements are same
     * @return the second minimum / maximum value from the input array
     * @author Bharath Sanjeevi ( https://github.com/BharathSanjeeviT )
     */

    public static int findSecondMin(final int[] arr) {
        return secondBest(arr, Integer.MAX_VALUE, (a, b) -> a < b);
    }

    public static int findSecondMax(final int[] arr) {
        return secondBest(arr, Integer.MIN_VALUE, (a, b) -> a > b);
    }

    private static void checkInput(final int[] arr) {
        if (arr.length < 2) {
            throw new IllegalArgumentException("Input array must have length of at least two");
        }
    }

    private static void checkOutput(final int secNum, final int initialVal) {
        if (secNum == initialVal) {
            throw new IllegalArgumentException("Input array should have at least 2 distinct elements");
        }
    }
}
