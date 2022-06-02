package com.thealgorithms.maths;

import java.util.Arrays;
import java.util.Random;

public class FindMin {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Random random = new Random();

        /* random size */
        int size = random.nextInt(100) + 1;
        int[] array = new int[size];

        /* init array with random numbers */
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt() % 100;
        }

        assert Arrays.stream(array).min().getAsInt() == findMin(array);
    }

    /**
     * Find the minimum number of an array of numbers.
     *
     * @param array the array contains element
     * @return min value
     */
    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}
public class ArrayValuesTest {

ArrayValues array = new ArrayValues();

@Test
public void returnsSmallestValue() {

    assertEquals(array.findSmallestArrayValue(new int[]{5,11,3,6,8}),3);
    assertEquals(array.findSmallestArrayValue(new int[]{5}),5);
    assertEquals(array.findSmallestArrayValue(new int[]{0}),0);
    assertEquals(array.findSmallestArrayValue(new int[]{-5,-8,-3,-6,-11}),-11);
    assertEquals(array.findSmallestArrayValue(new int[]{-20,11,-3,6,-8}),-20);

}

@Test(expected = ArrayIndexOutOfBoundsException.class)
public void emptyArrayIsNotAcceptedArgument() {
    array.findSmallestArrayValue(new int[]{});
}

@Test(expected = NullPointerException.class)
public void nullArrayIsNotAcceptedArgument() {
    array.findSmallestArrayValue(null);
}
