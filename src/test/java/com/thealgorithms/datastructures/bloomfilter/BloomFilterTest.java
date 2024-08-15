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
}
