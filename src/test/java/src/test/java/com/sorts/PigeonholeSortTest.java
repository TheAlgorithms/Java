package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.PigeonholeSort;

public class PigeonholeSortTest {

    @Test
    public void testPigeonholeSort() {

        PigeonholeSort pigeonholeSort = new PigeonholeSort();

        // Test Case 1
        Integer[] unsorted1 = new Integer[]{5, 1, 7, 2, 9, 6, 3, 4, 8};
        Integer[] sorted1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertArrayEquals(sorted1, pigeonholeSort.sort(unsorted1));

        // Test Case 2
        Integer[] unsorted2 = new Integer[]{-5, 1, 7, 2, -9, 6, -3, 4, 8};
        Integer[] sorted2 = new Integer[]{-9, -5, -3, 1, 2, 4, 6, 7, 8};
        Assert.assertArrayEquals(sorted2, pigeonholeSort.sort(unsorted2));

        // Test Case 3
        Integer[] unsorted3 = new Integer[]{-5, 1, 7, 2, -9, 6, -3, 4, 1, 8, 1, 1};
        Integer[] sorted3 = new Integer[]{-9, -5, -3, 1, 1, 1, 1, 2, 4, 6, 7, 8};
        Assert.assertArrayEquals(sorted3, pigeonholeSort.sort(unsorted3));

    }
}
