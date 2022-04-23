package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class WiggleSortTest {
    @Test
    void WiggleTestNumbersEven(){
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = {1, 2, 3, 4};
        Integer[] result = {2, 4, 1, 3};
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersOdd(){
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = {1, 2, 3, 4, 5};
        Integer[] result = {3, 4, 2, 5, 1};
        wiggleSort.sort(values);
        assertArrayEquals(values, result);

    }

    @Test
    void WiggleTestNumbersOddDuplicates(){
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = {7, 2, 2, 2, 5};
        Integer[] result = {2, 7, 2, 5, 2};
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersEvenDuplicates(){
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = {1, 2, 4, 4};
        Integer[] result = {2, 4, 1, 4};
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestStrings(){
        WiggleSort wiggleSort = new WiggleSort();
        String[] values = {"a", "b", "d", "c"};
        String[] result = {"b", "c", "a", "d"};
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }
}
