package com.thealgorithms.datastructures.bloomfilter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BloomFilterTest {

    @Test
    public void test1() {
        BloomFilter<Integer> bloomFilter = new BloomFilter<>(3, 10);
        bloomFilter.insert(3);
        bloomFilter.insert(17);

        Assertions.assertTrue(bloomFilter.contains(3));
        Assertions.assertTrue(bloomFilter.contains(17));
    }

    @Test
    public void test2() {
        BloomFilter<String> bloomFilter = new BloomFilter<>(4, 20);
        bloomFilter.insert("omar");
        bloomFilter.insert("mahamid");

        Assertions.assertTrue(bloomFilter.contains("omar"));
        Assertions.assertTrue(bloomFilter.contains("mahamid"));
    }
}
