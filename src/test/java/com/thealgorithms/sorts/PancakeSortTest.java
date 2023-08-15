package com.thealgorithms.sorts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PancakeSortTest {

    private PancakeSort pancakeSort = new PancakeSort();

    @Test
    @DisplayName("Empty Array pancakeSort")
    public void pancakeSortEmptyArray(){
        Integer[] inputArray = {};
        Integer[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEmpty();
    }

    @Test
    @DisplayName("PancakeSort single Integer Array")
    public void pancakeSort(){
        Integer[] inputArray = {2};
        Integer[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(inputArray);
    }

    @Test
    @DisplayName("PancakeSort non duplicate Integer Array")
    public void pancakeSortNonDuplicateIntegerArray(){
        Integer[] inputArray = {2, 1, 77, 34, 14, 56, 8};
        Integer[] expectedOutput = {1, 2, 8, 14, 34, 56, 77};
        Integer[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("PancakeSort Integer Array with duplicates")
    public void pancakeSortDuplicateIntegerArray(){
        Integer[] inputArray = {2, 1, 77, 34, 14, 77, 56, 14, 8};
        Integer[] expectedOutput = {1, 2, 8, 14, 14, 34, 56, 77, 77};
        Integer[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("PancakeSort negative Integer Array with duplicates")
    public void pancakeSortNegativeDuplicateIntegerArray(){
        Integer[] inputArray = {2, 1, 77, -34, -14, 77, 56, -14, 8};
        Integer[] expectedOutput = {-34, -14, -14, 1, 2, 8, 56, 77, 77};
        Integer[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("PancakeSort single String Array")
    public void pancakeSortSingleSringArray(){
        String[] inputArray = {"W"};
        String[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(inputArray);
    }

    @Test
    @DisplayName("PancakeSort non duplicate String Array")
    public void pancakeSortNonDuplicateStringArray(){
        String[] inputArray = {"W", "A", "d", "be", "jk", "hb", "bgh"};
        String[] expectedOutput = {"A", "W", "be", "bgh", "d", "hb", "jk"};
        String[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("PancakeSort String Array with duplicates")
    public void pancakeSortDuplicateStringArray(){
        String[] inputArray = {"W", "A", "d", "be", "jk", "hb", "bgh", "bgh", "W"};
        String[] expectedOutput = {"A", "W", "W", "be", "bgh", "bgh", "d", "hb", "jk"};
        String[] outputArray = pancakeSort.sort(inputArray);
        assertThat(outputArray).isEqualTo(expectedOutput);
    }

}
