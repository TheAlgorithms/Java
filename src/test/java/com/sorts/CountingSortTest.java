package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.CountingSort;

public class CountingSortTest {

    @Test
    public void testCountingSort() {

        CountingSort countingSort = new CountingSort();

        // Unsorted integer array
        Integer[] unsorted = new Integer[]{1, 4, 1, 2, 7, 5, 2};

        // Sorted integer array
        Integer[] sorted = new Integer[]{1, 1, 2, 2, 4, 5, 7};

        // Comparing the two integer arrays
        Assert.assertArrayEquals(sorted, countingSort.sort(unsorted));
    }
}
