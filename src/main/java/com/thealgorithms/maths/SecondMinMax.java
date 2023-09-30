package com.thealgorithms.maths;

public final class SecondMinMax {

    /**
     * @brief Finds the Second minimum / maximum value from the array
     * @param int array
     * @exception IllegalArgumentException => if input array is of length less than 2 also if all elements are same
     * @return the second minimum / maximum value from the input array
     * @author Bharath Sanjeevi ( https://github.com/BharathSanjeeviT )
     */

    private SecondMinMax() {
    }

    public static int findSecondMin(final int[] arr) {
        checkInput(arr);
        int secMin = Integer.MAX_VALUE, min = Integer.MAX_VALUE;
        for (final int num : arr)
            if (num < min) {
                secMin = min;
                min = num;
            } else if ((num < secMin) && (num != min)) {
                secMin = num;
            }
        return checkOutput(secMin);
    }

    public static int findSecondMax(final int[] arr) {
        checkInput(arr);
        int secMax = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for (final int num : arr)
            if (num > max) {
                secMax = max;
                max = num;
            } else if ((num > secMax) && (num != max)) {
                secMax = num;
            }
        return checkOutput(secMax);
    }

    private static void checkInput(final int[] arr) {
        if (arr.length < 2) throw new IllegalArgumentException("Input array must have length of at least two");
    }

    private static int checkOutput(final int secNum) {
        if ((secNum == Integer.MAX_VALUE) || (secNum == Integer.MIN_VALUE)) throw new IllegalArgumentException("Input array should have at least 2 distinct elements");
        return secNum;
    }
}
