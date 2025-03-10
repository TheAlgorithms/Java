package com.thealgorithms.sorts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("GnomeSort negative Integer Array with duplicates")
    public void gnomeSortNegativeDuplicateIntegerArray() {
        Integer[] inputArray = {6, 3, -87, 3, 99, -27, 4, -27};
        Integer[] expectedOutput = {-87, -27, -27, 3, 3, 4, 6, 99};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort single String Array")
    public void singleStringArray() {
        String[] inputArray = {"b"};
        String[] expectedOutput = {"b"};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort non duplicate String Array")
    public void gnomeSortNonDuplicateStringArray() {
        String[] inputArray = {"He", "A", "bc", "lo", "n", "bcp", "mhp", "d"};
        String[] expectedOutput = {"A", "He", "bc", "bcp", "d", "lo", "mhp", "n"};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort String Array with duplicates")
    public void gnomeSortDuplicateStringArray() {
        String[] inputArray = {"He", "A", "bc", "lo", "n", "bcp", "mhp", "bcp"};
        String[] expectedOutput = {"A", "He", "bc", "bcp", "bcp", "lo", "mhp", "n"};
        gnomeSort.sort(inputArray);
        assertThat(inputArray).isEqualTo(expectedOutput);
    }
}
