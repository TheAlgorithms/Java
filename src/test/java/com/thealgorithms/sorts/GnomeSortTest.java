package com.thealgorithms.sorts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Objects;
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

    @Test
    @DisplayName("GnomeSort for sorted Array")
    public void testSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = gnomeSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort for reversed sorted Array")
    public void testSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = gnomeSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort for All equal Array")
    public void testSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = gnomeSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    @DisplayName("GnomeSort String Array with mixed cases")
    public void testSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = gnomeSort.sort(inputArray);
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
    @DisplayName("GnomeSort Custom Object Array")
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
        Person[] outputArray = gnomeSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
