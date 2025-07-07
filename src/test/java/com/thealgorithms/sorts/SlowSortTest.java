package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author Rebecca Velez (https://github.com/rebeccavelez)
 * @see SlowSort
 */

public class SlowSortTest {

    private SlowSort slowSort = new SlowSort();

    @Test
    public void slowSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortSingleIntegerElementArray() {
        Integer[] inputArray = {5};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {5};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortSingleStringElementArray() {
        String[] inputArray = {"k"};
        String[] outputArray = slowSort.sort(inputArray);
        String[] expectedOutput = {"k"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortIntegerArray() {
        Integer[] inputArray = {8, 84, 53, -683, 953, 64, 2, 202, 98, -10};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {-683, -10, 2, 8, 53, 64, 84, 98, 202, 953};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortDuplicateIntegerArray() {
        Integer[] inputArray = {8, 84, 8, -2, 953, 64, 2, 953, 98};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {-2, 2, 8, 8, 64, 84, 98, 953, 953};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortStringArray() {
        String[] inputArray = {"g", "d", "a", "b", "f", "c", "e"};
        String[] outputArray = slowSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "e", "f", "g"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortDuplicateStringArray() {
        String[] inputArray = {"g", "d", "a", "g", "b", "f", "d", "c", "e"};
        String[] outputArray = slowSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "d", "e", "f", "g", "g"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void slowSortStringSymbolArray() {
        String[] inputArray = {"cbf", "auk", "ó", "(b", "a", ")", "au", "á", "cba", "auk", "(a", "bhy", "cba"};
        String[] outputArray = slowSort.sort(inputArray);
        String[] expectedOutput = {"(a", "(b", ")", "a", "au", "auk", "auk", "bhy", "cba", "cba", "cbf", "á", "ó"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = slowSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = slowSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    /**
     * Custom Comparable class for testing.
     **/
    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.age, o.age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    @Test
    public void testSortCustomObjects() {
        Person[] inputArray = {
            new Person("Alice", 32),
            new Person("Bob", 25),
            new Person("Charlie", 28),
        };
        Person[] expectedOutput = {
            new Person("Bob", 25),
            new Person("Charlie", 28),
            new Person("Alice", 32),
        };
        Person[] outputArray = slowSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
