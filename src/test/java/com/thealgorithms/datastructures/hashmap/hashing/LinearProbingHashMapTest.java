package com.thealgorithms.datastructures.hashmap.hashing;

class LinearProbingHashMapTest extends MapTest {
    @Override
    <Key extends Comparable<Key>, Value> Map<Key, Value> getMap() {
        return new LinearProbingHashMap<>();
    }
}
