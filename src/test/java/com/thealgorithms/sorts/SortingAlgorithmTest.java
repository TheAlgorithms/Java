package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public abstract class SortingAlgorithmTest {
    abstract SortAlgorithm getSortAlgorithm();

    protected int getGeneratedArraySize() {
        return 10_000;
    }

    @Test
    void shouldAcceptWhenEmptyArrayIsPassed() {
        Integer[] array = new Integer[] {};
        Integer[] expected = new Integer[] {};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenEmptyListIsPassed() {
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenSingleValuedArrayIsPassed() {
        Integer[] array = new Integer[] {2};
        Integer[] expected = new Integer[] {2};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenSingleValuedListIsPassed() {
        List<Integer> list = List.of(2);
        List<Integer> expected = List.of(2);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithAllPositiveValuesIsPassed() {
        Integer[] array = new Integer[] {60, 7, 55, 9, 999, 3};
        Integer[] expected = new Integer[] {3, 7, 9, 55, 60, 999};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllPositiveValuesIsPassed() {
        List<Integer> list = List.of(60, 7, 55, 9, 999, 3);
        List<Integer> expected = List.of(3, 7, 9, 55, 60, 999);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllNegativeValuesIsPassed() {
        Integer[] array = new Integer[] {-60, -7, -55, -9, -999, -3};
        Integer[] expected = new Integer[] {-999, -60, -55, -9, -7, -3};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithAllNegativeValuesIsPassed() {
        List<Integer> list = List.of(-60, -7, -55, -9, -999, -3);
        List<Integer> expected = List.of(-999, -60, -55, -9, -7, -3);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithRealNumberValuesIsPassed() {
        Integer[] array = new Integer[] {60, -7, 55, 9, -999, -3};
        Integer[] expected = new Integer[] {-999, -7, -3, 9, 55, 60};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithRealNumberValuesIsPassed() {
        List<Integer> list = List.of(60, -7, 55, 9, -999, -3);
        List<Integer> expected = List.of(-999, -7, -3, 9, 55, 60);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithDuplicateValueIsPassed() {
        Integer[] array = new Integer[] {60, 7, 55, 55, 999, 3};
        Integer[] expected = new Integer[] {3, 7, 55, 55, 60, 999};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithDuplicateValueIsPassed() {
        List<Integer> list = List.of(60, 7, 55, 55, 999, 3);
        List<Integer> expected = List.of(3, 7, 55, 55, 60, 999);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenStringValueArrayIsPassed() {
        String[] array = {"z", "a", "x", "b", "y"};
        String[] expected = {"a", "b", "x", "y", "z"};

        String[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenStringValueListIsPassed() {
        List<String> list = List.of("z", "a", "x", "b", "y");
        List<String> expected = List.of("a", "b", "x", "y", "z");

        List<String> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenRandomArrayIsPassed() {
        int randomSize = SortUtilsRandomGenerator.generateInt(getGeneratedArraySize());
        Double[] array = SortUtilsRandomGenerator.generateArray(randomSize);
        Double[] sorted = getSortAlgorithm().sort(array);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void shouldAcceptWhenRandomListIsPassed() {
        int randomSize = SortUtilsRandomGenerator.generateInt(getGeneratedArraySize());
        Double[] array = SortUtilsRandomGenerator.generateArray(randomSize);
        List<Double> list = List.of(array);
        List<Double> sorted = getSortAlgorithm().sort(list);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    public void shouldAcceptWhenArrayWithAllIdenticalValuesIsPassed() {
        Integer[] array = {1, 1, 1, 1};
        Integer[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Integer[] {1, 1, 1, 1}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithAllIdenticalValuesIsPassed() {
        List<Integer> list = Arrays.asList(1, 1, 1, 1);
        List<Integer> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(1, 1, 1, 1), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithMixedPositiveAndNegativeValuesIsPassed() {
        Integer[] array = {-1, 3, -2, 5, 0};
        Integer[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Integer[] {-2, -1, 0, 3, 5}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithMixedPositiveAndNegativeValuesIsPassed() {
        List<Integer> list = Arrays.asList(-1, 3, -2, 5, 0);
        List<Integer> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(-2, -1, 0, 3, 5), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithLargeNumbersIsPassed() {
        Long[] array = {10000000000L, 9999999999L, 10000000001L};
        Long[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Long[] {9999999999L, 10000000000L, 10000000001L}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithLargeNumbersIsPassed() {
        List<Long> list = Arrays.asList(10000000000L, 9999999999L, 10000000001L);
        List<Long> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(9999999999L, 10000000000L, 10000000001L), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithMaxIntegerValuesIsPassed() {
        Integer[] array = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        Integer[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Integer[] {Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithMaxIntegerValuesIsPassed() {
        List<Integer> list = Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        List<Integer> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithMinIntegerValuesIsPassed() {
        Integer[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        Integer[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Integer[] {Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithMinIntegerValuesIsPassed() {
        List<Integer> list = Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        List<Integer> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithSpecialCharactersIsPassed() {
        String[] array = {"!", "@", "#", "$"};
        String[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new String[] {"!", "#", "$", "@"}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithSpecialCharactersIsPassed() {
        List<String> list = Arrays.asList("!", "@", "#", "$");
        List<String> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList("!", "#", "$", "@"), sortedList);
    }

    @Test
    public void shouldAcceptWhenArrayWithMixedCaseStringsIsPassed() {
        String[] array = {"apple", "Banana", "cherry", "Date"};
        String[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new String[] {"Banana", "Date", "apple", "cherry"}, sortedArray);
    }

    @Test
    public void shouldAcceptWhenListWithMixedCaseStringsIsPassed() {
        List<String> list = Arrays.asList("apple", "Banana", "cherry", "Date");
        List<String> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList("Banana", "Date", "apple", "cherry"), sortedList);
    }

    @Test
    public void shouldHandleArrayWithNullValues() {
        Integer[] array = {3, null, 2, null, 1};
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> getSortAlgorithm().sort(array));
    }

    @Test
    public void shouldHandleListWithNullValues() {
        List<Integer> list = Arrays.asList(3, null, 2, null, 1);
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> getSortAlgorithm().sort(list));
    }

    static class CustomObject implements Comparable<CustomObject> {
        int value;

        CustomObject(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(CustomObject o) {
            return Integer.compare(this.value, o.value);
        }

        @Override
        public String toString() {
            return "CustomObject{"
                + "value=" + value + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CustomObject that = (CustomObject) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }

    @Test
    public void shouldHandleArrayOfCustomObjects() {
        CustomObject[] array = {new CustomObject(3), new CustomObject(1), new CustomObject(2)};
        CustomObject[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new CustomObject[] {new CustomObject(1), new CustomObject(2), new CustomObject(3)}, sortedArray);
    }

    @Test
    public void shouldHandleListOfCustomObjects() {
        List<CustomObject> list = Arrays.asList(new CustomObject(3), new CustomObject(1), new CustomObject(2));
        List<CustomObject> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(new CustomObject(1), new CustomObject(2), new CustomObject(3)), sortedList);
    }

    @Test
    public void shouldHandleArrayOfFloatingPointNumbers() {
        Double[] array = {3.3, 2.2, 1.1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        Double[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new Double[] {Double.NEGATIVE_INFINITY, 1.1, 2.2, 3.3, Double.POSITIVE_INFINITY, Double.NaN}, sortedArray);
    }

    @Test
    public void shouldHandleListOfFloatingPointNumbers() {
        List<Double> list = Arrays.asList(3.3, 2.2, 1.1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
        List<Double> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList(Double.NEGATIVE_INFINITY, 1.1, 2.2, 3.3, Double.POSITIVE_INFINITY, Double.NaN), sortedList);
    }

    @Test
    public void shouldHandleArrayWithEmptyStrings() {
        String[] array = {"apple", "", "banana", ""};
        String[] sortedArray = getSortAlgorithm().sort(array);
        assertArrayEquals(new String[] {"", "", "apple", "banana"}, sortedArray);
    }

    @Test
    public void shouldHandleListWithEmptyStrings() {
        List<String> list = Arrays.asList("apple", "", "banana", "");
        List<String> sortedList = getSortAlgorithm().sort(list);
        assertEquals(Arrays.asList("", "", "apple", "banana"), sortedList);
    }
}
