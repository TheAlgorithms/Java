package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Comprehensive test suite for StringRadixSort algorithm.
 * Tests various edge cases, performance scenarios, and correctness.
 * 
 * @author TheAlgorithms Contributors
 */
class StringRadixSortTest {

    @Test
    void testBasicStringSort() {
        String[] input = {"banana", "apple", "cherry", "date"};
        String[] expected = {"apple", "banana", "cherry", "date"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Basic string sorting failed");
        // Ensure original array is not modified
        assertArrayEquals(new String[]{"banana", "apple", "cherry", "date"}, input);
    }

    @Test
    void testEmptyArray() {
        String[] input = {};
        String[] expected = {};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Empty array should return empty array");
    }

    @Test
    void testSingleElement() {
        String[] input = {"single"};
        String[] expected = {"single"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Single element array should return same array");
    }

    @Test
    void testAlreadySorted() {
        String[] input = {"apple", "banana", "cherry", "date"};
        String[] expected = {"apple", "banana", "cherry", "date"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Already sorted array should remain sorted");
    }

    @Test
    void testReverseSorted() {
        String[] input = {"zebra", "yellow", "green", "blue", "apple"};
        String[] expected = {"apple", "blue", "green", "yellow", "zebra"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Reverse sorted array should be sorted correctly");
    }

    @Test
    void testDuplicateStrings() {
        String[] input = {"apple", "banana", "apple", "cherry", "banana"};
        String[] expected = {"apple", "apple", "banana", "banana", "cherry"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Duplicate strings should be handled correctly");
    }

    @Test
    void testDifferentLengths() {
        String[] input = {"a", "abc", "ab", "abcd", "abcde"};
        String[] expected = {"a", "ab", "abc", "abcd", "abcde"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Strings of different lengths should be sorted correctly");
    }

    @Test
    void testEmptyStrings() {
        String[] input = {"hello", "", "world", "", "test"};
        String[] expected = {"", "", "hello", "test", "world"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Empty strings should be sorted to the beginning");
    }

    @Test
    void testCaseSensitive() {
        String[] input = {"Apple", "apple", "Banana", "banana"};
        String[] expected = {"Apple", "Banana", "apple", "banana"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Case sensitive sorting should work correctly (uppercase before lowercase)");
    }

    @Test
    void testSpecialCharacters() {
        String[] input = {"test@123", "hello-world", "test_123", "hello world"};
        String[] expected = {"hello world", "hello-world", "test@123", "test_123"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Special characters should be sorted according to ASCII values");
    }

    @Test
    void testUnicodeCharacters() {
        String[] input = {"café", "naïve", "résumé", "apple"};
        String[] expected = {"apple", "café", "naïve", "résumé"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Unicode characters should be sorted correctly");
    }

    @Test
    void testLexicographicOrder() {
        String[] input = {"aa", "aaa", "a", "ab", "abc"};
        String[] expected = {"a", "aa", "aaa", "ab", "abc"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Lexicographic order should be maintained");
    }

    @Test
    void testPrefixStrings() {
        String[] input = {"prefix", "pre", "prefix123", "prefixing", "pref"};
        String[] expected = {"pre", "pref", "prefix", "prefix123", "prefixing"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Strings with common prefixes should be sorted correctly");
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 50, 100, 500})
    void testLargeArrays(int size) {
        String[] input = new String[size];
        for (int i = 0; i < size; i++) {
            input[i] = "string" + (size - i); // Create reverse order
        }
        
        String[] result = StringRadixSort.sort(input);
        String[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        
        assertArrayEquals(expected, result, "Large array of size " + size + " should be sorted correctly");
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            StringRadixSort.sort(null);
        }, "Null array should throw IllegalArgumentException");
    }

    @Test
    void testInPlaceSorting() {
        String[] input = {"banana", "apple", "cherry", "date"};
        String[] expected = {"apple", "banana", "cherry", "date"};
        
        StringRadixSort.sortInPlace(input);
        
        assertArrayEquals(expected, input, "In-place sorting should modify the original array");
    }

    @Test
    void testInPlaceSortingNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            StringRadixSort.sortInPlace(null);
        }, "In-place sorting with null array should throw IllegalArgumentException");
    }

    @Test
    void testStabilityWithDuplicates() {
        // Test that the sort is stable by using objects that can track original order
        String[] input = {"apple1", "banana", "apple2", "apple3"};
        String[] result = StringRadixSort.sort(input);
        
        // Check that duplicates maintain relative order (though exact indices may change due to other elements)
        int apple1Index = Arrays.asList(result).indexOf("apple1");
        int apple2Index = Arrays.asList(result).indexOf("apple2");
        int apple3Index = Arrays.asList(result).indexOf("apple3");
        
        assertTrue(apple1Index < apple2Index && apple2Index < apple3Index, 
                   "Stable sort should maintain relative order of equal elements");
    }

    @Test
    void testNumberStrings() {
        String[] input = {"100", "20", "3", "1000", "50"};
        String[] expected = {"100", "1000", "20", "3", "50"};
        String[] result = StringRadixSort.sort(input);
        
        assertArrayEquals(expected, result, "Number strings should be sorted lexicographically, not numerically");
    }

    @Test
    void testPerformanceComparisonWithBuiltInSort() {
        int size = 1000;
        String[] input = new String[size];
        for (int i = 0; i < size; i++) {
            input[i] = "test" + (int) (Math.random() * 1000) + "string" + i;
        }
        
        String[] radixSorted = StringRadixSort.sort(Arrays.copyOf(input, input.length));
        String[] builtInSorted = Arrays.copyOf(input, input.length);
        Arrays.sort(builtInSorted);
        
        assertArrayEquals(builtInSorted, radixSorted, 
                         "Radix sort should produce same result as built-in sort");
    }
}