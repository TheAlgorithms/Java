package com.thealgorithms.sorts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GnomeSortTest {

    private GnomeSort gnomeSort = new GnomeSort();

    @Test
    @DisplayName("GnomeSort empty Array")
    public void gnomeSortEmptyArray() {
        Integer[] inputArray = {};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEmpty();
    }

    @Test
    @DisplayName("GnomeSort single Integer Array")
    public void singleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] expectedOutput = {4};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort non duplicate Integer Array")
    public void gnomeSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, 3, 87, 99, 27, 4};
        Integer[] expectedOutput = {3, 4, 6, 27, 87, 99};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort Integer Array with duplicates")
    public void gnomeSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, 3, 87, 3, 99, 27, 4, 27};
        Integer[] expectedOutput = {3, 3, 4, 6, 27, 27, 87, 99};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }
}
