package com.thealgorithms.datastructures.bloomfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BloomFilterTest {
    private BloomFilter<String> bloomFilter;

    @BeforeEach
    void setUp() {
        bloomFilter = new BloomFilter<>(3, 100);
    }

    @Test
    public void testIntegerContains() {
        BloomFilter<Integer> bloomFilter = new BloomFilter<>(3, 10);
        bloomFilter.insert(3);
        bloomFilter.insert(17);

        Assertions.assertTrue(bloomFilter.contains(3));
        Assertions.assertTrue(bloomFilter.contains(17));
    }

    @Test
    public void testStringContains() {
        bloomFilter.insert("omar");
        bloomFilter.insert("mahamid");

        Assertions.assertTrue(bloomFilter.contains("omar"));
        Assertions.assertTrue(bloomFilter.contains("mahamid"));
    }

    @Test
    void testInsertAndContains() {
        bloomFilter.insert("hello");
        bloomFilter.insert("world");

        Assertions.assertTrue(bloomFilter.contains("hello"));
        Assertions.assertTrue(bloomFilter.contains("world"));
        Assertions.assertFalse(bloomFilter.contains("java"));
    }

    @Test
    void testFalsePositive() {
        bloomFilter.insert("apple");
        bloomFilter.insert("banana");

        Assertions.assertFalse(bloomFilter.contains("grape"));
        Assertions.assertFalse(bloomFilter.contains("orange"));
    }

    @Test
    void testMultipleInsertions() {
        for (int i = 0; i < 100; i++) {
            bloomFilter.insert("key" + i);
        }

        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(bloomFilter.contains("key" + i));
        }

        Assertions.assertFalse(bloomFilter.contains("key" + 200));
    }

    @Test
    void testEmptyFilterContains() {
        Assertions.assertFalse(bloomFilter.contains("notInserted"), "Filter should not contain any elements when empty");
        Assertions.assertFalse(bloomFilter.contains(null), "Filter should not contain null elements");
    }

    @Test
    void testDifferentTypes() {
        BloomFilter<Object> filter = new BloomFilter<>(3, 100);
        filter.insert("string");
        filter.insert(123);
        filter.insert(45.67);

        Assertions.assertTrue(filter.contains("string"), "Filter should contain the string 'string'");
        Assertions.assertTrue(filter.contains(123), "Filter should contain the integer 123");
        Assertions.assertTrue(filter.contains(45.67), "Filter should contain the double 45.67");
        Assertions.assertFalse(filter.contains("missing"), "Filter should not contain elements that were not inserted");
    }

    @Test
    void testFalsePositiveAfterInsertions() {
        bloomFilter.insert("cat");
        bloomFilter.insert("dog");
        bloomFilter.insert("fish");

        // Checking for an element that was not added
        Assertions.assertFalse(bloomFilter.contains("bird"), "Filter should not contain 'bird' which was never inserted");

        // To increase chances of false positives, we can add more items
        for (int i = 0; i < 100; i++) {
            bloomFilter.insert("item" + i);
        }

        Assertions.assertFalse(bloomFilter.contains("nonexistent"), "Filter should not contain 'nonexistent' which was never inserted");
    }

    @Test
    void testBoundaryConditions() {
        BloomFilter<String> filter = new BloomFilter<>(3, 10);
        filter.insert("a");
        filter.insert("b");
        filter.insert("c");
        filter.insert("d");

        Assertions.assertTrue(filter.contains("a"), "Filter should contain 'a'");
        Assertions.assertTrue(filter.contains("b"), "Filter should contain 'b'");
        Assertions.assertTrue(filter.contains("c"), "Filter should contain 'c'");
        Assertions.assertTrue(filter.contains("d"), "Filter should contain 'd'");
        Assertions.assertFalse(filter.contains("e"), "Filter should not contain 'e' which was not inserted");
    }

    @Test
    void testLongDataType() {
        BloomFilter<Long> filter = new BloomFilter<>(5, 1000);
        Long[] values = {Long.MIN_VALUE, Long.MAX_VALUE};

        for (Long value : values) {
            filter.insert(value);
        }

        for (Long value : values) {
            Assertions.assertTrue(filter.contains(value), "Filter should contain " + value);
        }
    }

    @Test
    void testFloatDataType() {
        BloomFilter<Float> filter = new BloomFilter<>(3, 200);
        Float[] values = {1.5f, -3.7f, 0.0f, Float.MAX_VALUE, Float.MIN_VALUE};

        for (Float value : values) {
            filter.insert(value);
        }

        for (Float value : values) {
            Assertions.assertTrue(filter.contains(value), "Filter should contain " + value);
        }

        Assertions.assertFalse(filter.contains(88.88f), "Filter should not contain uninserted value");
    }

    @Test
    void testBooleanDataType() {
        BloomFilter<Boolean> filter = new BloomFilter<>(2, 50);
        filter.insert(Boolean.TRUE);
        filter.insert(Boolean.FALSE);

        Assertions.assertTrue(filter.contains(Boolean.TRUE), "Filter should contain true");
        Assertions.assertTrue(filter.contains(Boolean.FALSE), "Filter should contain false");
    }

    @Test
    void testListDataType() {
        BloomFilter<List<String>> filter = new BloomFilter<>(4, 200);
        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = Arrays.asList("cat", "dog");
        List<String> emptyList = new ArrayList<>();

        filter.insert(list1);
        filter.insert(list2);
        filter.insert(emptyList);

        Assertions.assertTrue(filter.contains(list1), "Filter should contain list1");
        Assertions.assertTrue(filter.contains(list2), "Filter should contain list2");
        Assertions.assertTrue(filter.contains(emptyList), "Filter should contain empty list");
        Assertions.assertFalse(filter.contains(Arrays.asList("elephant", "tiger")), "Filter should not contain uninserted list");
    }

    @Test
    void testMapDataType() {
        BloomFilter<Map<String, Integer>> filter = new BloomFilter<>(3, 150);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("key1", 1);
        map1.put("key2", 2);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("key3", 3);

        Map<String, Integer> emptyMap = new HashMap<>();

        filter.insert(map1);
        filter.insert(map2);
        filter.insert(emptyMap);

        Assertions.assertTrue(filter.contains(map1), "Filter should contain map1");
        Assertions.assertTrue(filter.contains(map2), "Filter should contain map2");
        Assertions.assertTrue(filter.contains(emptyMap), "Filter should contain empty map");
    }

    @Test
    void testSetDataType() {
        BloomFilter<Set<Integer>> filter = new BloomFilter<>(3, 100);
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5));
        Set<Integer> emptySet = new HashSet<>();

        filter.insert(set1);
        filter.insert(set2);
        filter.insert(emptySet);

        Assertions.assertTrue(filter.contains(set1), "Filter should contain set1");
        Assertions.assertTrue(filter.contains(set2), "Filter should contain set2");
        Assertions.assertTrue(filter.contains(emptySet), "Filter should contain empty set");
        Assertions.assertFalse(filter.contains(new HashSet<>(Arrays.asList(6, 7, 8))), "Filter should not contain uninserted set");
    }

    @Test
    void testArrayDataType() {
        BloomFilter<int[]> filter = new BloomFilter<>(3, 100);
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5};
        int[] emptyArray = {};

        filter.insert(array1);
        filter.insert(array2);
        filter.insert(emptyArray);

        Assertions.assertTrue(filter.contains(array1), "Filter should contain array1");
        Assertions.assertTrue(filter.contains(array2), "Filter should contain array2");
        Assertions.assertTrue(filter.contains(emptyArray), "Filter should contain empty array");
        Assertions.assertFalse(filter.contains(new int[] {6, 7, 8}), "Filter should not contain different array");
    }

    @Test
    void testSpecialFloatingPointValues() {
        BloomFilter<Double> filter = new BloomFilter<>(3, 100);
        Double[] specialValues = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, -0.0, 0.0};

        for (Double value : specialValues) {
            filter.insert(value);
        }

        for (Double value : specialValues) {
            Assertions.assertTrue(filter.contains(value), "Filter should contain " + value);
        }
    }

    @Test
    void testVerySmallBloomFilter() {
        BloomFilter<String> smallFilter = new BloomFilter<>(1, 5);
        smallFilter.insert("test1");
        smallFilter.insert("test2");

        Assertions.assertTrue(smallFilter.contains("test1"));
        Assertions.assertTrue(smallFilter.contains("test2"));
    }
}
