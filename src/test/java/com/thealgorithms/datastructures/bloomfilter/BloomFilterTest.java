package com.thealgorithms.datastructures.bloomfilter;

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
}
